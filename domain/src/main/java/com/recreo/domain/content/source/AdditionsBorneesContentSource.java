package com.recreo.domain.content.source;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Tirage combinatoire d'additions dont les deux operandes et la somme restent dans les bornes
 * fournies. ADR-0006 : ces bornes (sommeMaximale, nombreDePropositions) vivent ici, declarees dans
 * son propre paquet YAML sous 'parametres' et lues par sa seule fabrique - aucun autre contenu ni
 * aucune mecanique ne les voit. DEC-0005 : l'indice est de meme nature, recu au constructeur.
 */
public final class AdditionsBorneesContentSource implements ContentSource {

    private static final int TENTATIVES_MAX = 20;

    /**
     * DEC-0007 point 2, amende le 2026-08-05 (s03) : le couple retenu est tire uniformement parmi
     * les couples (a, b) valides (a + b <= sommeMaximale), le couple (0, 0) excepte, plutot que
     * par le tirage en deux temps d'origine qui favorisait mecaniquement les grandes sommes.
     *
     * <p>S1-7 (DEC-0004, point 5) : ce tirage uniforme comporte un operande nul environ un tiers du
     * temps (l'un des deux operandes vaut 0, une propriete du programme qui ne demande pas de
     * calculer). Un tirage a operande nul n'est retenu qu'une fois sur ACCEPTATION_OPERANDE_NUL_SUR,
     * les autres sont retires. Amendement du 2026-08-05 a DEC-0004 : la valeur 3 (au lieu de 5)
     * produit la cible pedagogique de 12,9 %, et c'est la seule valeur qui tient a la fois ce
     * seuil et la table de repartition par somme de DEC-0007 point 2, calculee sur le tirage ainsi
     * compose.
     */
    private static final int ACCEPTATION_OPERANDE_NUL_SUR = 3;

    private final ContentDescriptor descripteur;
    private final int sommeMaximale;
    private final int nombreDePropositions;
    private final String indice;
    private final List<Couple> couplesValides;

    public AdditionsBorneesContentSource(ContentDescriptor descripteur, int sommeMaximale,
                                          int nombreDePropositions, String indice) {
        this.descripteur = descripteur;
        this.sommeMaximale = sommeMaximale;
        this.nombreDePropositions = nombreDePropositions;
        this.indice = indice;
        this.couplesValides = couplesValides(sommeMaximale);
    }

    /** DEC-0007 point 2 : tous les couples (a, b) avec a + b <= sommeMaximale, sauf (0, 0). */
    private static List<Couple> couplesValides(int sommeMaximale) {
        List<Couple> couples = new ArrayList<>();
        for (int a = 0; a <= sommeMaximale; a++) {
            for (int b = 0; a + b <= sommeMaximale; b++) {
                if (a == 0 && b == 0) {
                    continue;
                }
                couples.add(new Couple(a, b));
            }
        }
        return List.copyOf(couples);
    }

    private record Couple(int operandeA, int operandeB) {
    }

    @Override
    public ContentDescriptor descriptor() {
        return descripteur;
    }

    /**
     * Le tirage est combinatoire, pas fini : contrairement a ListContentSource, il ne renvoie
     * jamais Optional.empty(). dejaVus n'est utilise que pour limiter les repetitions litterales
     * a l'interieur d'une meme partie, pas pour arreter le jeu.
     */
    @Override
    public Optional<Item> next(RandomSource hasard, Set<ItemId> dejaVus) {
        Question tiree = tirer(hasard);
        for (int tentative = 1; tentative < TENTATIVES_MAX && dejaVus.contains(tiree.id()); tentative++) {
            tiree = tirer(hasard);
        }
        return Optional.of(tiree);
    }

    private Question tirer(RandomSource hasard) {
        Couple couple;
        int tentative = 0;
        do {
            couple = couplesValides.get(hasard.nextInt(couplesValides.size()));
            tentative++;
        } while (auMoinsUnOperandeNul(couple.operandeA(), couple.operandeB())
                && hasard.nextInt(ACCEPTATION_OPERANDE_NUL_SUR) != 0
                && tentative < TENTATIVES_MAX);
        int operandeA = couple.operandeA();
        int operandeB = couple.operandeB();
        int somme = operandeA + operandeB;
        return new Question(
                new ItemId("addition-" + operandeA + "-" + operandeB),
                operandeA + " + " + operandeB,
                String.valueOf(somme),
                distracteurs(hasard, somme),
                Optional.of(indice));
    }

    private static boolean auMoinsUnOperandeNul(int operandeA, int operandeB) {
        return operandeA == 0 || operandeB == 0;
    }

    /**
     * Les ecarts les plus petits sont ajoutes en premier, ce qui garantit qu'au moins un
     * distracteur retenu est proche de la bonne reponse. Jamais de valeur negative.
     */
    private List<String> distracteurs(RandomSource hasard, int bonneReponse) {
        List<Integer> candidats = new ArrayList<>();
        int ecart = 1;
        while (candidats.size() < 2 * (nombreDePropositions - 1) && ecart <= sommeMaximale + nombreDePropositions) {
            if (bonneReponse - ecart >= 0) {
                candidats.add(bonneReponse - ecart);
            }
            candidats.add(bonneReponse + ecart);
            ecart++;
        }
        melanger(candidats, hasard);
        return candidats.stream()
                .distinct()
                .limit(Math.max(0, nombreDePropositions - 1))
                .map(String::valueOf)
                .toList();
    }

    private static void melanger(List<Integer> valeurs, RandomSource hasard) {
        for (int i = valeurs.size() - 1; i > 0; i--) {
            int j = hasard.nextInt(i + 1);
            Integer tampon = valeurs.get(i);
            valeurs.set(i, valeurs.get(j));
            valeurs.set(j, tampon);
        }
    }
}
