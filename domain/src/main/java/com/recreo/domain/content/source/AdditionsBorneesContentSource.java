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
 * aucune mecanique ne les voit.
 */
public final class AdditionsBorneesContentSource implements ContentSource {

    private static final int TENTATIVES_MAX = 20;

    /**
     * S1-7 (DEC-0004, point 5) : un tirage brut comporte un operande nul environ un tiers du temps
     * (l'un des deux operandes vaut 0, une propriete du programme qui ne demande pas de calculer).
     * Un tirage a operande nul n'est retenu qu'une fois sur ACCEPTATION_OPERANDE_NUL_SUR, les
     * autres sont retires : la proportion mesuree sur 200 tirages a graine fixee tombe alors entre
     * 5 % et 15 %, jamais a zero.
     */
    private static final int ACCEPTATION_OPERANDE_NUL_SUR = 5;

    private static final String INDICE = "Compte sur tes doigts.";

    private final ContentDescriptor descripteur;
    private final int sommeMaximale;
    private final int nombreDePropositions;

    public AdditionsBorneesContentSource(ContentDescriptor descripteur, int sommeMaximale,
                                          int nombreDePropositions) {
        this.descripteur = descripteur;
        this.sommeMaximale = sommeMaximale;
        this.nombreDePropositions = nombreDePropositions;
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
        int operandeA;
        int operandeB;
        int tentative = 0;
        do {
            operandeA = hasard.nextInt(sommeMaximale + 1);
            operandeB = hasard.nextInt(sommeMaximale - operandeA + 1);
            tentative++;
        } while (auMoinsUnOperandeNul(operandeA, operandeB)
                && hasard.nextInt(ACCEPTATION_OPERANDE_NUL_SUR) != 0
                && tentative < TENTATIVES_MAX);
        int somme = operandeA + operandeB;
        return new Question(
                new ItemId("addition-" + operandeA + "-" + operandeB),
                operandeA + " + " + operandeB,
                String.valueOf(somme),
                distracteurs(hasard, somme),
                Optional.of(INDICE));
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
