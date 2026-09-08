package com.recreo.archi;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0086, route par nadia-prof (verdicts de s30, instance A et instance B) : <b>la forme des
 * propositions d'un paquet ne suffit jamais a designer la bonne reponse.</b>
 *
 * <p><b>Pourquoi cette famille et pas une autre.</b> Les refus au premier passage sont la premiere
 * fuite de budget d'audit du depot - quatre paquets sur cinq refuses a s29, deux sur quatre a s30,
 * un reaudit a 0,5 chacun. Et la reprise de s30 <b>n'a ajoute aucun garde-fou</b> : le contenu a ete
 * corrige, un commentaire d'exclusion mis a jour, rien de plus. Les proprietes restaient gardees par
 * nadia-prof seule, ce qui est la definition d'une propriete que la CI ne tient pas.
 *
 * <p><b>Deux clauses, et deux seulement.</b> nadia-prof en a nomme cinq comme
 * <i>"textuelles donc mordables"</i>. Trois ne le sont pas, et ADR-0086 dit pourquoi avec les
 * chiffres : une regle a population vide est ce qu'ADR-0051 refuse, et un faux rouge sur une
 * formulation legitime couterait plus qu'il ne rapporte. <b>Les deux qui restent ont ete mesurees
 * avant d'etre ecrites</b>, sur les 55 paquets a propositions du contenu servi et sur l'etat de
 * l'historique ou nadia-prof a prononce ses refus.
 *
 * <p><b>Clause 1 - un item servi plusieurs fois sous plusieurs noms.</b> C'est le refus de
 * {@code SCI-CM1-17} : <i>"les cinq items de la seconde tache sont un seul item, servi cinq fois
 * avec un autre nom d'animal ; des la seconde occurrence l'enfant reconnait la phrase et la
 * presse"</i>. Le critere est le <b>moule</b> : trois items ou plus dont les propositions se
 * correspondent une a une - identiques, ou d'au moins quatre mots et ne differant qu'en une
 * position - et qui portent tous <b>la meme bonne reponse</b>.
 *
 * <p><b>Ce que ce critere a coute a trouver, et pourquoi la formulation evidente est fausse.</b> La
 * premiere ecriture cherchait des <i>enonces</i> coules dans le meme moule. Mesuree sur le contenu
 * servi, elle rend <b>rouge {@code cp-qlm-vivant-non-vivant}</b> - <i>"Un chat, qu'est-ce que
 * c'est ?"</i> contre <i>"Un arbre, qu'est-ce que c'est ?"</i> - et <b>elle ne mord pas le paquet
 * refuse</b>, dont les cinq enonces different de plus d'un mot. La forme textuelle du defaut est
 * exactement celle d'un exercice de classement legitime ; ce qui les separe est que le classement
 * <b>change de reponse</b> quand le mot substitue change, et que la repetition n'en change pas.
 * C'est la bonne reponse qui porte le critere, jamais l'enonce.
 *
 * <p><b>Et le plancher de trois mots sur la reponse repetee n'est pas un reglage de confort.</b>
 * Une reponse d'un ou deux mots est une <b>etiquette de categorie</b> - une lettre, un son,
 * {@code oui}, {@code non}, un symbole de comparaison - et la repeter d'un item a l'autre est la
 * tache elle-meme. Une phrase entiere repetee mot pour mot est une phrase que l'enfant reconnait.
 * Sans ce plancher, la clause rend rouges {@code cp-voyelle-du-son}, {@code cp-lettre-du-son} et
 * {@code cp-qlm-vivant-non-vivant}, tous valides par nadia-prof : le garde-fou prononcerait alors un
 * verdict pedagogique, ce qu'il n'a pas le droit de faire.
 *
 * <p><b>Clause 2 - la contrefacon par l'amorce.</b> C'est {@code FOP-R4}, ouverte le 2026-09-06 :
 * <i>"un enfant qui n'a rien compris trouve la bonne reponse en lisant les deux premiers mots de
 * chaque bouton"</i>. Le critere : quatre distracteurs ou plus d'un meme paquet partagent le moule
 * de leurs quatre premiers mots, et <b>aucune</b> bonne reponse du paquet ne le partage. Le moule,
 * la encore, et non l'amorce litterale : {@code cm1-problemes-durees} sert cinq distracteurs
 * commencant par <i>"1 heure 25 minutes"</i>, et sa bonne reponse <i>"1 heure 15 minutes"</i> coule
 * dans le meme moule - un critere litteral l'aurait declare fautif a tort, un critere de moule le
 * laisse vert.
 *
 * <p><b>Clause 3 - le marqueur de restriction, amendement du 2026-09-08.</b> C'est le fait mesure
 * par nadia-prof au reaudit de s31, sur ce garde-fou meme : ses deux clauses etaient <b>vertes</b>
 * sur {@code cp-emc-regle-souhait-loi} alors que {@code FAB-1c} y est fausse, et c'est le seul motif
 * de la seule ligne de programme que s31 ne ferme pas. Sa cause tenait en une phrase :
 * <i>"le marqueur qui trie l'ecran pour l'enfant est un mot unique en tete de proposition, pas un
 * moule de quatre mots"</i>. Le critere : un <b>mot de restriction</b> present sur <b>tous</b> les
 * distracteurs d'un item et sur <b>aucune</b> part de sa bonne reponse, sur au moins
 * {@link #ITEMS_POUR_UN_MARQUEUR} items du paquet. La bonne reponse est alors la seule proposition
 * qui ne restreint pas, et l'enfant la trie sans savoir de quoi elle parle.
 *
 * <p><b>La clause encode la propriete, pas la forme que nadia-prof en a decrite.</b> Elle dit
 * <i>"en tete"</i> ; le critere ici est positionnel-libre, et c'est ce qui lui fait compter
 * <b>quatre</b> items la ou une lecture en tete en compte trois - l'item 9 du paquet porte
 * <i>"les enseignants seulement"</i>, marqueur en fin de proposition. Sa fourchette annoncee etait
 * <i>"3 a 4 items sur 5"</i> ; la clause en nomme quatre, et ce sont les siens.
 *
 * <p><b>Ce que la clause 3 garde est un critere enumere assume</b> (ADR-0039) : la liste des
 * marqueurs est ecrite a la main, donc <b>verte par defaut</b> sur tout mot de restriction qu'elle
 * ne nomme pas. Un paquet qui ecrirait <i>"rien que les pietons"</i>, <i>"a part le conducteur"</i>
 * ou <i>"hormis les adultes"</i> passerait devant elle sans rougir. C'est ecrit ici, compte dans
 * {@code LesGardeFousSontMisALEpreuveTest} et motive dans ADR-0086 : mesure a l'appui, aucun critere
 * non enumere ne separe ce defaut du contenu valide - le meme critere sans lexique marque 86 items
 * dans 23 paquets, dont 22 valides.
 *
 * <p><b>Ce que ces trois clauses ne jugent pas.</b> Si un distracteur est <i>plausible</i> - la
 * moitie portante d'{@code OBJ-R2} -, si une reponse est juste, si un item est au niveau de sa
 * classe. Le sens reste a nadia-prof, en jouant et en lisant. Ce qui est tenu est que la
 * <b>fabrication</b> des propositions ne donne pas la reponse a qui ne lit que leur forme.
 *
 * <p><b>Perimetre derive (ADR-0013 obligation 1).</b> Les paquets viennent du repertoire de contenu
 * servi de chaque module declare au pom agregateur, par
 * {@link AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest#repertoiresDeContenu(Path)}. Aucun
 * paquet, aucun item et aucun texte n'est nomme dans ce fichier hors de ses jeux fautifs et de
 * l'exemption datee de la clause 2 - <b>la clause 3 n'en a plus depuis le 2026-09-08 (s32)</b>. Un
 * paquet neuf entre dans la population le jour ou il entre au contenu servi.
 *
 * <p><b>Ce n'est pas une {@code ArchRule}, et le compte de regles ArchUnit du depot est
 * inchange.</b> ArchUnit lit du bytecode ; la fabrication des propositions d'un paquet YAML n'y est
 * pas une propriete lisible.
 */
class LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest {

    /** Clause 1 : en deca de trois items, une ressemblance n'est pas une repetition. */
    private static final int ITEMS_POUR_UNE_REPETITION = 3;

    /** Clause 1 : en deca de trois mots, une reponse repetee est une etiquette de categorie. */
    private static final int MOTS_POUR_UNE_PHRASE = 3;

    /** Le moule : deux textes d'au moins quatre mots ne differant qu'en une position. */
    private static final int MOTS_POUR_UN_MOULE = 4;

    /** Clause 2 : la longueur de l'amorce comparee, en mots. */
    private static final int MOTS_DE_L_AMORCE = 4;

    /** Clause 2 : en deca de quatre distracteurs, une amorce partagee est une coincidence. */
    private static final int DISTRACTEURS_POUR_UNE_AMORCE = 4;

    /**
     * Clause 2, cliquet exact. {@code FOP-R4} est <b>ouverte et non payee</b> : nadia-prof l'a
     * qualifiee non bloquante le 2026-09-06, l'a routee a camille-po avec son regime (rejeu borne,
     * 0,5), et le paquet reste servi et valide. La clause serait donc rouge sur du contenu que la
     * porte pedagogique a laisse passer.
     *
     * <p><b>Le plafond vaut exactement la dette</b> : un paquet, nomme, date. La liste ne peut pas
     * grandir - un paquet neuf qui contrefait son amorce est rouge -, elle ne peut pas pourrir - un
     * nom qui ne designe plus un paquet fautif est rouge, et c'est
     * {@link #laDetteNePeutNiGrandirNiPourrir()} qui le tient -, et la vider oblige a baisser le
     * plafond dans le meme diff. C'est la mecanique d'ADR-0067.
     */
    private static final Set<String> DETTE_D_AMORCE = Set.of("cm1-emc-fait-opinion.yaml");

    private static final int PLAFOND_DE_DETTE = 1;

    /**
     * Clause 3 : les mots par lesquels une proposition restreint ce qu'elle designe. Liste ecrite a
     * la main - un critere enumere assume, declare comme tel ci-dessus.
     *
     * <p><b>Ce que la mesure du 2026-09-08 a retire de la premiere ecriture, et pourquoi ce n'est
     * pas un reglage de confort</b> : {@code que} et {@code qu} y etaient, et ils produisent un faux
     * rouge sur {@code cm1-emc-fait-opinion}, paquet valide, dont deux distracteurs portent
     * <i>"en la croyant parce que ..."</i>. La conjonction de subordination est bien plus frequente
     * que la particule restrictive, et un enfant ne trie pas un ecran sur {@code que}. Retires sur
     * ce chiffre - un item, un paquet valide -, pas sur une intuition. {@code juste} est retire pour
     * l'autre motif : il dit <i>correct</i> autant que <i>seulement</i>, et le contenu servi n'en
     * porte aucune occurrence en proposition, donc l'inclure n'aurait mordu que du futur ambigu.
     *
     * <p><b>Trois des sept entrees nomment quelque chose aujourd'hui</b> : {@code seulement}
     * (12 occurrences, 3 paquets), {@code seul} (5, 2), {@code seule} (2, 2). Les quatre autres ne
     * paraissent nulle part dans le contenu servi, et elles restent : le jeu fautif de la clause 3
     * les met a l'epreuve, et un lexique taille sur le corpus du jour laisserait passer le paquet de
     * demain qui ecrit <i>uniquement</i>.
     */
    private static final Set<String> MARQUEURS_DE_RESTRICTION = Set.of(
            "seulement", "uniquement", "seul", "seule", "seuls", "seules", "sauf");

    /**
     * Clause 3 : en deca de deux distracteurs, il n'y a pas d'intrus a reperer. Mesure du
     * 2026-09-08 : sans ce plancher, le critere marque 194 items dans 29 paquets, et son premier
     * rang est {@code cp-nature-mot} (16 items sur 16, valide), le paquet refuse venant au
     * neuvieme. Un item a deux propositions n'oppose pas une bonne reponse a des distracteurs
     * semblables : il oppose deux etiquettes d'une dichotomie fermee, et n'importe quel mot de l'une
     * est absent de l'autre.
     */
    private static final int DISTRACTEURS_POUR_UN_MARQUEUR = 2;

    /**
     * Clause 3 : un item isole n'est pas une habitude de fabrication. Un seul item dont la bonne
     * reponse est la seule a ne pas restreindre reste un item ou l'enfant doit savoir a qui la loi
     * s'applique ; c'est la repetition qui apprend a presser le bouton sans <i>seulement</i>.
     *
     * <p><b>Marge du reglage, dite plutot que tue</b> (mesure du 2026-09-08, 63 paquets,
     * 1 051 items a deux distracteurs) : le paquet fautif en porte <b>quatre</b>, et aucun autre
     * paquet servi n'en porte <b>un seul</b>. Le plancher a deux mots de marge de chaque cote, et il
     * n'est pose sur aucune valeur qui fait juste passer.
     */
    private static final int ITEMS_POUR_UN_MARQUEUR = 2;

    /**
     * <b>Clause 3, dette payee le 2026-09-08 (s32) : la clause n'a plus aucune exemption.</b>
     * {@code DETTE_DE_MARQUEUR} nommait {@code cp-emc-regle-souhait-loi.yaml}, seul paquet servi que
     * la clause rougissait, sous le refus maintenu d'{@code EMC-CP-07}. {@code S32-EMCCP07} a reecrit
     * sa tache B ; l'exemption a cesse de nommer quelque chose de reel, et le test d'obligation qui
     * la tenait est devenu rouge - <b>c'est ce rouge-la qui a programme son propre retrait</b>, et il
     * a fonctionne comme annonce.
     *
     * <p><b>Ce que le retrait coutait, et ou ce cout est paye.</b> L'exemption etait aussi le
     * materiau reel de la morsure : la clause etait mise a l'epreuve sur le contenu servi prive de son
     * exemption, donc sur le paquet que nadia-prof avait refuse. Le retirer sans rien d'autre aurait
     * laisse la clause 3 prouvee par la seule fixture de ma main - la faute de s05. L'etat refuse est
     * donc <b>fige octet pour octet</b> sous {@code etat-refuse-emc-cp-07}, comme l'etat de la
     * clause 1 l'est sous {@code etat-refuse-s30}, et la morsure porte desormais sur ce fige :
     * {@link #laClauseTroisMordLEtatRefuseDuMarqueurRestrictif()}.
     *
     * <p><b>Ce que la clause garde maintenant qu'elle n'exempte plus rien</b> (mesure du 2026-09-08,
     * arbre fusionne de s32, reacteur complet, JDK que la machine virtuelle des tests rapporte) :
     * <b>65 paquets a propositions, 1 227 items, 1 111 a deux distracteurs ou plus</b> - la
     * population de la clause -, et <b>zero item marque</b> dans tout le contenu servi. Le cliquet
     * est donc a <b>plafond zero sur une population de 1 111 items</b>, et non une regle a population
     * vide (ADR-0051). Le lexique, lui, nomme encore quelque chose de servi : {@code seulement} (8
     * occurrences en proposition), {@code seul} (6), {@code seule} (2) - aucune ne tombe sur tous les
     * distracteurs d'un item.
     */
    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of("src", "test", "resources", "contreexemple",
            "LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest");

    /** Un paquet servi, reduit a ce que ces deux clauses regardent. */
    private record Paquet(String nom, List<Item> items) {
    }

    private record Item(List<String> propositions, String bonneReponse) {

        /** Les propositions qui ne sont pas la bonne reponse, dans l'ordre du paquet. */
        List<String> distracteurs() {
            return propositions().stream()
                    .filter(proposition -> !proposition.equals(bonneReponse())).toList();
        }
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Les mots d'un texte servi, sans accent, sans casse et sans ponctuation. La comparaison porte
     * sur ce que l'enfant lit, pas sur la notation du fichier : {@code l'ecureuil} et
     * {@code l ecureuil} sont le meme mot pour qui reconnait une phrase (ADR-0060, meme esprit).
     */
    static List<String> mots(String texte) {
        String sansAccent = Normalizer.normalize(texte, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9]+", " ")
                .strip();
        return sansAccent.isEmpty() ? List.of() : List.of(sansAccent.split(" "));
    }

    /**
     * Deux textes coulent dans le meme moule : ils sont identiques, ou bien ils comptent le meme
     * nombre de mots, au moins {@link #MOTS_POUR_UN_MOULE}, et ne different qu'en une position. Le
     * plancher de longueur est ce qui empeche deux propositions d'un mot - deux lettres, deux sons,
     * deux nombres - d'etre declarees semblables parce qu'elles different partout.
     */
    static boolean memeMoule(List<String> gauche, List<String> droite) {
        if (gauche.equals(droite)) {
            return true;
        }
        if (gauche.size() != droite.size() || gauche.size() < MOTS_POUR_UN_MOULE) {
            return false;
        }
        int ecarts = 0;
        for (int rang = 0; rang < gauche.size(); rang++) {
            if (!gauche.get(rang).equals(droite.get(rang))) {
                ecarts++;
            }
        }
        return ecarts == 1;
    }

    /** Deux items presentent le meme moule de propositions : chacune trouve sa correspondante. */
    private static boolean memesPropositions(Item gauche, Item droite) {
        if (gauche.propositions().size() != droite.propositions().size()) {
            return false;
        }
        List<String> restantes = new ArrayList<>(droite.propositions());
        for (String proposition : gauche.propositions()) {
            int trouve = -1;
            for (int rang = 0; rang < restantes.size(); rang++) {
                if (memeMoule(mots(proposition), mots(restantes.get(rang)))) {
                    trouve = rang;
                    break;
                }
            }
            if (trouve < 0) {
                return false;
            }
            restantes.remove(trouve);
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    static List<Paquet> paquetsServis(List<Path> repertoires) {
        List<Paquet> paquets = new ArrayList<>();
        for (Path repertoire : repertoires) {
            try (Stream<Path> fichiers = Files.list(repertoire)) {
                for (Path fichier : fichiers.filter(chemin -> chemin.toString().endsWith(".yaml"))
                        .sorted().toList()) {
                    Object charge = new Yaml().load(lire(fichier));
                    if (!(charge instanceof Map<?, ?> corps)
                            || !(corps.get("items") instanceof List<?> items)) {
                        continue;
                    }
                    List<Item> lus = new ArrayList<>();
                    for (Object item : items) {
                        if (!(item instanceof Map<?, ?> champs)
                                || !(champs.get("bonneReponse") instanceof String reponse)
                                || !(champs.get("distracteurs") instanceof List<?> distracteurs)) {
                            continue;
                        }
                        List<String> propositions = new ArrayList<>();
                        propositions.add(reponse);
                        ((List<Object>) distracteurs).forEach(
                                valeur -> propositions.add(String.valueOf(valeur)));
                        lus.add(new Item(List.copyOf(propositions), reponse));
                    }
                    if (!lus.isEmpty()) {
                        paquets.add(new Paquet(fichier.getFileName().toString(), List.copyOf(lus)));
                    }
                }
            } catch (IOException echec) {
                throw new UncheckedIOException(echec);
            }
        }
        return paquets;
    }

    /**
     * Clause 1. Les groupes d'items d'un paquet coules dans le meme moule de propositions sont
     * formes par proche en proche ; un groupe d'au moins {@link #ITEMS_POUR_UNE_REPETITION} items
     * dont toutes les bonnes reponses coulent dans un seul moule d'au moins
     * {@link #MOTS_POUR_UNE_PHRASE} mots est un item servi plusieurs fois.
     *
     * <p>Les items sont nommes par leur <b>rang dans le paquet, a partir de un</b>, depuis le
     * 2026-09-08 : c'est la numerotation que nadia-prof emploie dans ses verdicts, et ce message
     * lui est destine. La clause 3 nomme les siens de la meme facon ; deux numerotations dans les
     * messages d'un meme garde-fou coutent un decalage a chaque lecture.
     */
    static List<String> defautsDeRepetition(List<Paquet> paquets) {
        List<String> defauts = new ArrayList<>();
        for (Paquet paquet : paquets) {
            List<Item> items = paquet.items();
            int[] groupe = new int[items.size()];
            for (int rang = 0; rang < groupe.length; rang++) {
                groupe[rang] = rang;
            }
            for (int gauche = 0; gauche < items.size(); gauche++) {
                for (int droite = gauche + 1; droite < items.size(); droite++) {
                    if (memesPropositions(items.get(gauche), items.get(droite))) {
                        int aFusionner = groupe[droite];
                        int conserve = groupe[gauche];
                        for (int rang = 0; rang < groupe.length; rang++) {
                            if (groupe[rang] == aFusionner) {
                                groupe[rang] = conserve;
                            }
                        }
                    }
                }
            }
            for (int tete : new TreeSet<>(java.util.Arrays.stream(groupe).boxed().toList())) {
                List<Integer> membres = new ArrayList<>();
                for (int rang = 0; rang < groupe.length; rang++) {
                    if (groupe[rang] == tete) {
                        membres.add(rang + 1);
                    }
                }
                if (membres.size() < ITEMS_POUR_UNE_REPETITION) {
                    continue;
                }
                List<String> reponses = new ArrayList<>();
                for (int membre : membres) {
                    String reponse = items.get(membre - 1).bonneReponse();
                    if (reponses.stream().noneMatch(vue -> memeMoule(mots(vue), mots(reponse)))) {
                        reponses.add(reponse);
                    }
                }
                if (reponses.size() == 1
                        && mots(reponses.get(0)).size() >= MOTS_POUR_UNE_PHRASE) {
                    defauts.add(String.format(
                            "%s : les items %s presentent le meme moule de propositions et portent"
                                    + " tous la meme bonne reponse \"%s\". Ce sont %d ecrans pour un"
                                    + " seul item : des le second, l'enfant reconnait la phrase et la"
                                    + " presse sans rien savoir du sujet (SCI-CM1-17, refus du"
                                    + " 2026-09-06 ; ADR-0086, clause 1)",
                            paquet.nom(), membres, reponses.get(0), membres.size()));
                }
            }
        }
        return defauts;
    }

    /**
     * Clause 2. Une amorce est le moule des {@link #MOTS_DE_L_AMORCE} premiers mots d'une
     * proposition ; elle est fautive quand au moins {@link #DISTRACTEURS_POUR_UNE_AMORCE}
     * distracteurs du paquet la partagent et qu'aucune bonne reponse ne la partage.
     */
    static List<String> defautsDAmorce(List<Paquet> paquets, Set<String> dette) {
        List<String> defauts = new ArrayList<>();
        for (Paquet paquet : paquets) {
            if (dette.contains(paquet.nom())) {
                continue;
            }
            List<List<String>> amorcesDesDistracteurs = new ArrayList<>();
            List<List<String>> amorcesDesReponses = new ArrayList<>();
            for (Item item : paquet.items()) {
                for (String proposition : item.propositions()) {
                    List<String> amorce = mots(proposition);
                    if (amorce.size() < MOTS_DE_L_AMORCE) {
                        continue;
                    }
                    List<String> tete = List.copyOf(amorce.subList(0, MOTS_DE_L_AMORCE));
                    if (proposition.equals(item.bonneReponse())) {
                        amorcesDesReponses.add(tete);
                    } else {
                        amorcesDesDistracteurs.add(tete);
                    }
                }
            }
            Set<String> deja = new LinkedHashSet<>();
            for (List<String> amorce : amorcesDesDistracteurs) {
                if (!deja.add(String.join(" ", amorce))) {
                    continue;
                }
                long partagees = amorcesDesDistracteurs.stream()
                        .filter(autre -> memeMoule(amorce, autre)).count();
                if (partagees < DISTRACTEURS_POUR_UNE_AMORCE) {
                    continue;
                }
                if (amorcesDesReponses.stream().anyMatch(reponse -> memeMoule(amorce, reponse))) {
                    continue;
                }
                defauts.add(String.format(
                        "%s : %d distracteurs sur %d commencent par \"%s\", qu'aucune de ses %d"
                                + " bonnes reponses assez longues ne commence. L'amorce du bouton"
                                + " departage a la place de la competence (FOP-R4, ouverte le"
                                + " 2026-09-06 ; ADR-0086, clause 2)",
                        paquet.nom(), partagees, amorcesDesDistracteurs.size(),
                        String.join(" ", amorce), amorcesDesReponses.size()));
            }
        }
        return defauts;
    }

    /**
     * Clause 3. Un item est <b>marque</b> quand un mot de {@link #MARQUEURS_DE_RESTRICTION} figure
     * dans chacun de ses distracteurs et dans aucune part de sa bonne reponse ; un paquet est fautif
     * quand au moins {@link #ITEMS_POUR_UN_MARQUEUR} de ses items a au moins
     * {@link #DISTRACTEURS_POUR_UN_MARQUEUR} distracteurs sont marques. La comparaison porte sur les
     * mots, jamais sur une position : un marqueur en fin de proposition trie l'ecran comme un
     * marqueur en tete.
     */
    static List<String> defautsDeMarqueur(List<Paquet> paquets) {
        List<String> defauts = new ArrayList<>();
        for (Paquet paquet : paquets) {
            List<String> rangsMarques = new ArrayList<>();
            Set<String> marqueursVus = new TreeSet<>();
            for (int rang = 0; rang < paquet.items().size(); rang++) {
                Item item = paquet.items().get(rang);
                List<String> distracteurs = item.distracteurs();
                if (distracteurs.size() < DISTRACTEURS_POUR_UN_MARQUEUR) {
                    continue;
                }
                Set<String> motsDeLaReponse = new LinkedHashSet<>(mots(item.bonneReponse()));
                for (String marqueur : new TreeSet<>(MARQUEURS_DE_RESTRICTION)) {
                    if (motsDeLaReponse.contains(marqueur)) {
                        continue;
                    }
                    if (distracteurs.stream().allMatch(autre -> mots(autre).contains(marqueur))) {
                        rangsMarques.add(String.valueOf(rang + 1));
                        marqueursVus.add(marqueur);
                        break;
                    }
                }
            }
            if (rangsMarques.size() >= ITEMS_POUR_UN_MARQUEUR) {
                defauts.add(String.format(
                        "%s : %d items portent un marqueur de restriction (%s) sur tous leurs"
                                + " distracteurs et sur aucune part de leur bonne reponse - items"
                                + " %s. La bonne reponse est alors la seule proposition qui ne"
                                + " restreint pas, et l'enfant la trie sans savoir de quoi elle"
                                + " parle (FAB-1c, refus maintenu du 2026-09-08 ; ADR-0086,"
                                + " clause 3)",
                        paquet.nom(), rangsMarques.size(), String.join(", ", marqueursVus),
                        String.join(", ", rangsMarques)));
            }
        }
        return defauts;
    }

    static void verifierLesPaquets(List<Paquet> paquets, Set<String> detteDAmorce) {
        assertThat(paquets)
                .as("ADR-0051 : sans paquet a propositions, ce garde-fou n'examine aucun objet")
                .isNotEmpty();
        List<String> defauts = new ArrayList<>(defautsDeRepetition(paquets));
        defauts.addAll(defautsDAmorce(paquets, detteDAmorce));
        defauts.addAll(defautsDeMarqueur(paquets));
        assertThat(defauts)
                .as("ADR-0086 : la forme des propositions d'un paquet ne designe jamais la bonne"
                        + " reponse")
                .isEmpty();
    }

    private static List<Paquet> paquetsDuDepot() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        return paquetsServis(
                AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu(racine));
    }

    @Test
    void leContenuServiEstConforme() {
        verifierLesPaquets(paquetsDuDepot(), DETTE_D_AMORCE);
    }

    /**
     * ADR-0051 : le compte d'objets reellement examines, ecrit plutot qu'espere. Mesure du
     * 2026-09-07 : 55 paquets a propositions, 1077 items, sur le reacteur complet, JDK 21.0.10.
     * Remesure du 2026-09-08, apres les quatre paquets de s31 : <b>63 paquets, 1 165 items, dont
     * 1 051 a deux distracteurs</b> - la population de la clause 3. Les bornes sont posees sous ces
     * mesures ; elles constatent qu'un vert porte sur le contenu servi entier et non sur une poignee
     * de paquets qu'un chargement aurait laisse tomber.
     */
    @Test
    void leBalayageExamineLeContenuServiEntier() {
        List<Paquet> paquets = paquetsDuDepot();
        assertThat(paquets)
                .as("ADR-0086 : le contenu servi porte des dizaines de paquets a propositions")
                .hasSizeGreaterThan(50);
        assertThat(paquets.stream().mapToInt(paquet -> paquet.items().size()).sum())
                .as("ADR-0086 : le contenu servi porte plus d'un millier d'items a propositions")
                .isGreaterThan(1_000);
        assertThat(paquets.stream().flatMap(paquet -> paquet.items().stream())
                .filter(item -> item.distracteurs().size() >= DISTRACTEURS_POUR_UN_MARQUEUR).count())
                .as("ADR-0086 : la clause 3 examine le millier d'items a deux distracteurs, et non"
                        + " une poignee")
                .isGreaterThan(1_000);
    }

    /**
     * ADR-0067 : le cliquet de la clause 2. Une dette ne peut ni grandir - le plafond est ecrit et
     * compare - ni pourrir : chaque paquet exempte doit encore etre <b>reellement fautif</b>, sans
     * quoi l'exemption ne nomme plus rien de reel et doit disparaitre.
     *
     * <p><b>La clause 3 n'a plus de moitie ici, et c'est le mecanisme qui a fonctionne.</b> Ce test
     * portait une seconde moitie sur {@code DETTE_DE_MARQUEUR} ; elle est devenue rouge le jour ou
     * {@code S32-EMCCP07} a paye {@code FAB-1c}, c'est-a-dire exactement quand elle devait. La
     * clause 3 n'exempte plus rien, son cliquet est desormais l'egalite a zero portee par
     * {@link #leContenuServiEstConforme()} sur 1 111 items, et sa morsure est un etat fige de
     * l'historique.
     */
    @Test
    void laDetteNePeutNiGrandirNiPourrir() {
        assertThat(DETTE_D_AMORCE)
                .as("ADR-0067 : le plafond vaut exactement la taille de la dette du 2026-09-07")
                .hasSize(PLAFOND_DE_DETTE);
        List<Paquet> paquets = paquetsDuDepot();
        for (String exempte : DETTE_D_AMORCE) {
            List<Paquet> seul = paquets.stream()
                    .filter(paquet -> paquet.nom().equals(exempte)).toList();
            assertThat(seul)
                    .as("%s est exempte de la clause 2 et n'est plus un paquet servi", exempte)
                    .hasSize(1);
            assertThat(defautsDAmorce(seul, Set.of()))
                    .as("%s est exempte de la clause 2 alors qu'il n'est plus fautif : une exemption"
                            + " qui ne nomme plus rien de reel n'est pas un cliquet", exempte)
                    .isNotEmpty();
        }
    }

    /**
     * ADR-0013 obligation 3, et la morsure qui compte. Le jeu fautif de la clause 1 n'est pas de ma
     * main : c'est <b>le paquet reellement refuse par nadia-prof le 2026-09-06</b>
     * ({@code cm1-sciences-chaine-alimentaire}), repris tel qu'il vivait dans le depot au commit
     * {@code 712c293b}, avant la reprise de lea-dev. Une fixture ecrite par l'auteur d'une regle
     * ressemble a ce qu'il imagine ; celle-ci ressemble a ce que le depot a reellement produit.
     */
    @Test
    void leContenuRefuseParNadiaProfFaitRougirLaClauseUn() {
        List<Paquet> refuses = paquetsServis(List.of(CONTRE_EXEMPLE.resolve("etat-refuse-s30")));
        assertThat(refuses)
                .as("le jeu fautif de la clause 1 est un etat reel de l'historique")
                .isNotEmpty();
        assertThat(defautsDeRepetition(refuses))
                .as("la clause 1 doit mordre le paquet que nadia-prof a refuse")
                .isNotEmpty();
        assertThatThrownBy(() -> verifierLesPaquets(refuses, Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("portent tous la meme bonne reponse")
                .hasMessageContaining("il risquerait de manquer de nourriture");
    }

    /**
     * ADR-0013 obligation 3. La clause 2 est mordue sur le paquet reel qui porte {@code FOP-R4},
     * en lui retirant son exemption : c'est la meme mesure que celle de nadia-prof, huit
     * distracteurs sur dix.
     *
     * <p><b>Declencheur ecrit d'avance, parce que la clause 3 vient d'en payer le prix.</b> Cette
     * morsure porte sur le <b>contenu vivant</b> prive de son exemption. Le jour ou {@code FOP-R4}
     * est payee sur {@code cm1-emc-fait-opinion}, elle devient rouge <b>sans avoir rien detecte</b> -
     * le defaut qu'{@code ADR-0087} a paye en cloture de s31, et celui que la clause 3 a paye ici le
     * 2026-09-08. <b>Le geste attendu dans le meme diff que le paiement</b> : figer l'etat porteur de
     * {@code FOP-R4} sous {@code CONTRE_EXEMPLE}, comme {@code etat-refuse-s30} et
     * {@code etat-refuse-emc-cp-07}, faire porter cette morsure sur le fige, retirer
     * {@code DETTE_D_AMORCE} et baisser {@code PLAFOND_DE_DETTE} a zero. Ce n'est pas fait d'avance :
     * l'etat vivant <b>est</b> aujourd'hui l'etat fautif, et le figer maintenant recopierait un
     * fichier pour ne rien prouver de plus.
     */
    @Test
    void lePaquetQuiPorteLaReserveFaitRougirLaClauseDeuxSansSonExemption() {
        assertThatThrownBy(() -> verifierLesPaquets(paquetsDuDepot(), Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("L'amorce du bouton departage a la place de la competence");
    }

    /**
     * ADR-0013 obligation 3, et <b>la morsure qui decide de la clause 3</b>. Le materiau n'est pas
     * de ma main : c'est {@code cp-emc-regle-souhait-loi} tel qu'il etait servi le 2026-09-08 quand
     * nadia-prof y a maintenu le refus d'{@code EMC-CP-07}, recopie octet pour octet depuis le
     * commit qui precede {@code S32-EMCCP07} ({@code 167a4421}, parent). La clause nomme
     * <b>quatre</b> items - les items 7, 8, 9 et 10 - et le mot qui les trie.
     *
     * <p><b>Pourquoi un etat fige, et pas le contenu vivant prive de son exemption.</b> C'est ainsi
     * que ce test etait ecrit jusqu'au 2026-09-08, et cette forme est morte avec la dette : le
     * paiement de {@code FAB-1c} l'a rendue rouge sans qu'elle ait rien detecte, ce qui est
     * exactement le defaut qu'{@code ADR-0087} a paye en cloture de s31 - <i>une preuve de mecanisme
     * qui ne vaut que pendant que le depot est en dette ne vaut plus rien quand il n'y est plus</i>.
     * L'etat fige, lui, survit a la correction, comme celui de la clause 1 (ADR-0087, meme
     * mecanique ; la lecon de s05 sur le materiau reel).
     *
     * <p><b>Et la moitie que ce test ne prouve pas</b> : il ne dit rien de l'etat vivant. C'est
     * {@link #leContenuServiEstConforme()} qui le tient, sans exemption, sur les 1 111 items a deux
     * distracteurs du contenu servi.
     */
    @Test
    void laClauseTroisMordLEtatRefuseDuMarqueurRestrictif() {
        List<Paquet> refuse = paquetsServis(
                List.of(CONTRE_EXEMPLE.resolve("etat-refuse-emc-cp-07")));
        assertThat(refuse)
                .as("le jeu fautif de la clause 3 est un etat reel de l'historique")
                .isNotEmpty();
        assertThatThrownBy(() -> verifierLesPaquets(refuse, Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("cp-emc-regle-souhait-loi.yaml : 4 items portent un marqueur"
                        + " de restriction (seulement)")
                .hasMessageContaining("items 7, 8, 9, 10");
    }

    /**
     * La derivation qui remplace un reglage, sur la clause 3. Un item a deux propositions sort de sa
     * population, et ce n'est pas une commodite : sur une dichotomie fermee, n'importe quel mot de
     * l'une est absent de l'autre, et la mesure du 2026-09-08 le chiffre - sans ce plancher, le
     * critere marque 194 items dans 29 paquets et met {@code cp-nature-mot} (16 sur 16, valide) au
     * premier rang. Les deux items ci-dessous portent le marqueur exactement comme le paquet refuse
     * le porte ; ils ne sont pas fautifs parce qu'il n'y a rien a trier.
     */
    @Test
    void unItemADeuxPropositionsNEntrePasDansLaPopulationDeLaClauseTrois() {
        Paquet binaire = new Paquet("paquet-binaire.yaml", List.of(
                new Item(List.of("tout le monde", "seulement les adultes"), "tout le monde"),
                new Item(List.of("chaque eleve", "seulement les grands"), "chaque eleve")));

        assertThat(defautsDeMarqueur(List.of(binaire)))
                .as("clause 3 : un item a deux propositions oppose deux etiquettes, pas une bonne"
                        + " reponse a des distracteurs semblables")
                .isEmpty();
    }

    /**
     * ADR-0013 obligation 3. Trois jeux fautifs de ma main, qui prouvent que chaque clause mord
     * une situation que l'historique n'a pas encore produite : un paquet ou trois items partagent le
     * moule de leurs propositions et une reponse d'une phrase, un paquet ou l'amorce des
     * distracteurs les separe des bonnes reponses, et un paquet qui trie ses ecrans par
     * {@code uniquement} et {@code sauf} - deux entrees du lexique que le contenu servi ne porte
     * nulle part, donc deux entrees dont ce jeu fautif est la seule preuve qu'elles mordent.
     */
    @Test
    void troisPaquetsFautifsFontRougirChacuneLeurClause() {
        assertThatThrownBy(() -> verifierLesPaquets(
                paquetsServis(List.of(CONTRE_EXEMPLE.resolve("paquets-fautifs"))), Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ecrans pour un seul item")
                .hasMessageContaining("L'amorce du bouton departage a la place de la competence")
                .hasMessageContaining("portent un marqueur de restriction (sauf, uniquement)");
    }

    /**
     * La derivation qui remplace un reglage, constatee et non supposee. Les trois formes que la
     * clause 1 doit laisser passer sont ecrites ici sous leur forme reelle, telles que le contenu
     * servi les porte : une etiquette de categorie repetee, un classement dont la reponse change, et
     * deux propositions d'un mot qui different partout.
     */
    @Test
    void lesFormesLegitimesNeSontPasDesRepetitions() {
        assertThat(memeMoule(mots("a"), mots("i")))
                .as("deux propositions d'un mot ne coulent jamais dans le meme moule")
                .isFalse();
        assertThat(memeMoule(mots("il risquerait de manquer de nourriture"),
                mots("elle risquerait de manquer de nourriture")))
                .as("SCI-CM1-17 : le pronom seul ne fait pas deux reponses")
                .isTrue();
        assertThat(memeMoule(mots("1 heure 25 minutes"), mots("1 heure 15 minutes")))
                .as("un distracteur numerique et une bonne reponse numerique partagent leur moule")
                .isTrue();
        assertThat(mots("un objet").size())
                .as("une etiquette de categorie tient sous le plancher de trois mots")
                .isLessThan(MOTS_POUR_UNE_PHRASE);
    }
}
