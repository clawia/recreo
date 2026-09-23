package com.recreo.adapter.content;

import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.TableauSupport;
import com.recreo.domain.content.source.ListContentSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * ADR-0007 : paquet enumere, type 'question'. Les items sont ecrits a la main dans le YAML sous
 * 'items' ; l'identifiant de chaque item est derive de sa position, le schema n'en porte pas.
 *
 * <p>ADR-0024 : cette fabrique lit deux textes optionnels et rien de plus, 'indice' et
 * 'explication'. Une cle qu'elle ne connait pas est toujours ignoree sans un mot - c'est la dette que
 * l'ADR nomme et n'a pas fermee. En particulier, la cle 'graphie' que le paquet cp-le-bon-son declare
 * pour son propre compte n'arrive jamais jusqu'ici et n'a pas a y arriver : elle est une declaration
 * d'auteur, relue par {@code ChaquePaquetExpliqueSesItemsAVariationTest}, jamais une donnee de jeu.
 * La faire entrer dans cette fabrique, partagee par toutes les matieres, obligerait chaque paquet de
 * mathematiques a connaitre un mot de francais.</p>
 *
 * <p>ADR-0016 : chaque item porte son propre indice, sous la cle 'indice'. Cette fabrique
 * construisait jusqu'ici ses items avec le constructeur a quatre arguments, celui qui laisse
 * l'indice vide : un paquet 'question' ne pouvait donc porter aucune aide, quoi qu'un auteur ecrive
 * dans son YAML, alors que la mecanique bandit manchot en promet une depuis S3-2. Le champ est lu
 * ici ; qu'il soit present sur tous les items est garde par
 * {@code ChaquePaquetPorteUnIndiceTest}.</p>
 *
 * <p>M21 (arbitrage de nadia-prof du 2026-08-13) : la cle facultative 'indicesNommentLaReponse',
 * au niveau du paquet et non d'un item, declare que les indices de ce paquet nomment la bonne
 * reponse - la mecanique ne les affichera alors jamais. Absente, elle vaut faux : un paquet neuf
 * n'a rien a declarer pour porter des indices utilisables.</p>
 *
 * <p>ADR-0062 : la cle facultative 'partDesignee', sur le meme patron qu''indice' et
 * 'explication', declare la sous-chaine de l'enonce qu'une mecanique de rendu doit distinguer
 * visuellement. Absente, elle vaut "rien a designer" - le cas ordinaire, jamais un cas particulier
 * a remplir d'une valeur factice.</p>
 *
 * <p>ADR-0050 : la cle facultative 'texteSupport', meme patron, declare un texte distinct de
 * l'enonce que le gabarit rend dans un style propre, plus petit (S17-2). Absente, elle vaut "aucun
 * texte-support" - le cas ordinaire.</p>
 *
 * <p>ADR-0073 : les deux cles facultatives 'illustration' et 'illustrationTexteAlternatif', meme
 * patron encore. La premiere porte un <b>identifiant</b> - jamais un chemin, jamais une extension,
 * jamais des octets - et cette fabrique ne l'interprete pas : elle le transmet tel qu'il est ecrit.
 * Ou le fichier est servi est une affaire de l'adapter web, et le decider ici ferait entrer
 * l'agencement du serveur dans un contenu que nadia-prof relit. Absentes, elles valent "cet item ne
 * montre rien" - le cas ordinaire. Qu'elles soient presentes ensemble est un invariant du record
 * {@link Question} (clause 5), et qu'un identifiant declare resolve vers un fichier reellement servi
 * est verifie par {@code LIllustrationDeContenuResoutEtAucuneNeDortTest}, sur la donnee et non sur
 * le gabarit.</p>
 *
 * <p>ADR-0075 : la cle facultative 'texteAVoixHaute', meme patron. Elle porte le texte que l'adulte
 * lit a voix haute a l'enfant (DEC-0068), distinct de 'enonce' et jamais interprete ici : cette
 * fabrique le transmet tel qu'il est ecrit, exactement comme 'texteSupport'. Absente, elle vaut
 * "cet item ne s'appuie sur aucune voix" - le cas ordinaire.</p>
 *
 * <p>ADR-0099 : la cle facultative 'tableauSupport', meme patron, est la premiere de cette fabrique
 * qui porte une <b>structure</b> et non une chaine - deux sous-cles obligatoires, 'enTete' (les
 * intitules de colonnes) et 'lignes' (les lignes de corps). Absente, elle vaut "cet item ne montre
 * aucune grille" - le cas ordinaire.
 *
 * <p><b>Ce bloc-ci est lu strictement, a la difference de tout le reste de cette fabrique</b>, et le
 * motif est un trou mesure : le balayage d'ADR-0070 ne descend dans un item que par une
 * <i>liste de correspondances</i>, jamais par une correspondance posee directement dessus
 * ('relationsDistracteurs' de cm1-mots-de-liaison porte des valeurs d'auteur en guise de cles). Une
 * sous-cle de 'tableauSupport' mal orthographiee ne serait donc vue par aucun garde-fou de cle
 * morte. Elle est ici refusee par un echec de chargement, nomme : c'est la dette d'ADR-0024 fermee
 * sur ce bloc et sur lui seul, la ou elle ne pouvait pas etre rattrapee ailleurs.</p>
 */
final class QuestionContentSourceFactory implements ContentSourceFactory {

    /** ADR-0099 : les seules sous-cles que 'tableauSupport' accepte ; toute autre est un echec. */
    private static final Set<String> SOUS_CLES_DU_TABLEAU = Set.of("enTete", "lignes");

    @Override
    public ContentSource creer(ContentDescriptor descripteur, Map<String, Object> corps) {
        Object brut = corps.get("items");
        if (!(brut instanceof List<?> items) || items.isEmpty()) {
            throw new IllegalStateException("champ obligatoire manquant ou vide : 'items'");
        }
        List<Question> questions = new ArrayList<>();
        int position = 0;
        for (Object itemBrut : items) {
            questions.add(question(itemBrut, position));
            position++;
        }
        boolean indicesNommentLaReponse = Boolean.TRUE.equals(corps.get("indicesNommentLaReponse"));
        return new ListContentSource(descripteur, questions, indicesNommentLaReponse);
    }

    private Question question(Object itemBrut, int position) {
        if (!(itemBrut instanceof Map<?, ?> item)) {
            throw new IllegalStateException("items[" + position + "] : doit etre une correspondance (map)");
        }
        String enonce = texte(item, "enonce", position);
        String bonneReponse = texte(item, "bonneReponse", position);
        Object distracteursBrut = item.get("distracteurs");
        if (!(distracteursBrut instanceof List<?> distracteurs)) {
            throw new IllegalStateException("items[" + position + "] : champ obligatoire manquant 'distracteurs'");
        }
        List<String> distracteursTexte = distracteurs.stream().map(String::valueOf).toList();
        Object indice = item.get("indice");
        Object explication = item.get("explication");
        Object partDesignee = item.get("partDesignee");
        Object texteSupport = item.get("texteSupport");
        Object illustration = item.get("illustration");
        Object illustrationTexteAlternatif = item.get("illustrationTexteAlternatif");
        Object texteAVoixHaute = item.get("texteAVoixHaute");
        Optional<TableauSupport> tableauSupport = tableauSupport(item.get("tableauSupport"), position);
        return new Question(new ItemId("item-" + position), enonce, bonneReponse, distracteursTexte,
                indice == null ? Optional.empty() : Optional.of(String.valueOf(indice)),
                explication == null ? Optional.empty() : Optional.of(String.valueOf(explication)),
                partDesignee == null ? Optional.empty() : Optional.of(String.valueOf(partDesignee)),
                texteSupport == null ? Optional.empty() : Optional.of(String.valueOf(texteSupport)),
                illustration == null ? Optional.empty() : Optional.of(String.valueOf(illustration)),
                illustrationTexteAlternatif == null
                        ? Optional.empty() : Optional.of(String.valueOf(illustrationTexteAlternatif)),
                texteAVoixHaute == null ? Optional.empty() : Optional.of(String.valueOf(texteAVoixHaute)),
                tableauSupport);
    }

    /**
     * ADR-0099 : la grille d'un item, ou rien. Chaque forme refusee est nommee dans le message -
     * c'est un chargement qui echoue, jamais une cle ignoree en silence.
     */
    private Optional<TableauSupport> tableauSupport(Object brut, int position) {
        if (brut == null) {
            return Optional.empty();
        }
        if (!(brut instanceof Map<?, ?> tableau)) {
            throw new IllegalStateException("items[" + position + "] : 'tableauSupport' doit etre une"
                    + " correspondance de deux cles, 'enTete' et 'lignes'");
        }
        for (Object cle : tableau.keySet()) {
            if (!SOUS_CLES_DU_TABLEAU.contains(String.valueOf(cle))) {
                throw new IllegalStateException("items[" + position + "] : 'tableauSupport' porte la"
                        + " sous-cle inconnue '" + cle + "' - les seules attendues sont "
                        + SOUS_CLES_DU_TABLEAU);
            }
        }
        List<String> enTete = cellules(tableau.get("enTete"), position, "enTete");
        Object lignesBrutes = tableau.get("lignes");
        if (!(lignesBrutes instanceof List<?> lignes)) {
            throw new IllegalStateException("items[" + position + "] : 'tableauSupport' doit porter"
                    + " une liste 'lignes'");
        }
        List<List<String>> corps = new ArrayList<>();
        int rang = 1;
        for (Object ligne : lignes) {
            corps.add(cellules(ligne, position, "lignes[" + rang + "]"));
            rang++;
        }
        return Optional.of(new TableauSupport(enTete, corps));
    }

    private List<String> cellules(Object brut, int position, String ou) {
        if (!(brut instanceof List<?> valeurs)) {
            throw new IllegalStateException("items[" + position + "] : 'tableauSupport." + ou
                    + "' doit etre une liste de cellules");
        }
        return valeurs.stream().map(String::valueOf).toList();
    }

    private String texte(Map<?, ?> item, String champ, int position) {
        Object valeur = item.get(champ);
        if (valeur == null) {
            throw new IllegalStateException("items[" + position + "] : champ obligatoire manquant '" + champ + "'");
        }
        return String.valueOf(valeur);
    }
}
