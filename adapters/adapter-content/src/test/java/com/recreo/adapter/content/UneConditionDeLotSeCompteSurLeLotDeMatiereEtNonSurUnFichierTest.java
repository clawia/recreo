package com.recreo.adapter.content;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0079 : une condition qui parle d'un <b>lot de matiere</b> se compte sur le lot, jamais sur le
 * fichier qui en porte une part.
 *
 * <p><b>Le fait, et il est de nadia-prof.</b> Le 2026-09-03 elle a mesure l'extension de sa propre
 * borne d'audit sur l'objet, ce qu'elle n'avait jamais fait : <i>"a s26 les cinq paquets etaient
 * cinq fichiers, la question ne se posait pas, et j'ai pris pour une definition ce qui n'etait
 * qu'une coincidence."</i> Son unite est <b>le lot de matiere</b> - jusqu'a vingt items d'une meme
 * matiere et d'un meme niveau - et le nombre de fichiers ne compte pas. Elle route la consequence
 * technique dans la meme phrase : <i>"si un garde-fou verifie HIS-1 ou BIA-2, il doit le faire sur
 * le lot de matiere, pas sur un fichier. Le test de marc-dev est juste aujourd'hui parce que
 * l'histoire tient en un seul fichier ; BIA-2 sur questionner le monde porte sur les trois fichiers
 * ensemble, et un test qui l'evaluerait fichier par fichier serait <b>vert a tort</b>."</i>
 *
 * <p><b>Ou etait exactement le vert a tort, parce qu'un mode de defaillance nomme vaut mieux qu'un
 * mode de defaillance craint.</b> {@code PaquetCm1HistoireGeographieTest} designait sa population
 * par <b>un chemin de ressource ecrit en dur</b> et son plafond par <b>une constante egale au tiers
 * de vingt</b>. Un second fichier d'histoire-geographie CM1 n'aurait ete juge par <b>rien</b> : pas
 * refuse, pas compte, simplement invisible - et le tiers du lot aurait pu passer a la moitie sans
 * qu'une seule assertion bouge. Ce n'est pas une faiblesse du test de marc-dev, c'est la meme
 * famille que {@code FIG-4} etendue hors de son paquet la veille (ADR-0077) : <b>un perimetre
 * enumere</b>, juste tant que le monde ressemble a l'enumeration.
 *
 * <p><b>La population est derivee des deux cotes.</b> Les paquets viennent du repertoire de contenu
 * servi, jamais d'une liste. Le lot est le couple <b>(matiere, niveau)</b> que chaque paquet declare
 * dans son descripteur : rien n'est ecrit ici qui nomme une matiere. <b>Et le fait qu'un lot soit
 * soumis a une condition de lot est lui aussi derive</b> : un lot y est soumis des qu'<b>un</b> de
 * ses items porte le marqueur de cette condition. Personne n'a a declarer quelque part que
 * l'histoire est soumise a {@code HIS-1} ; c'est le contenu qui le dit.
 *
 * <p><b>Mesure de la derivation sur l'arbre du 2026-09-03</b>, et elle retrouve les comptes de
 * nadia-prof par un autre chemin que le sien : <b>questionner le monde CP = 3 fichiers, 24
 * items</b> ; <b>histoire-geographie CM1 = 1 fichier, 20 items</b>. Ce sont les nombres de sa
 * section 2.3, obtenus ici sans qu'aucun fichier ne soit nomme.
 *
 * <p><b>La condition gardee aujourd'hui est {@code HIS-1}</b> - <i>"au plus un tiers des items
 * demande une date, un nom ou un chiffre a restituer"</i> - parce qu'elle a une population reelle.
 * <b>{@code BIA-2} n'est pas gardee ici, et c'est delibere</b> : <i>"un pays n'est jamais represente
 * par un seul trait"</i> ne porte que sur les lignes <i>ici et ailleurs</i> et <i>aspects
 * culturels</i>, qui ne sont servies par aucun paquet. Une clause verte sur une population vide est
 * ce qu'ADR-0051 refuse, et elle serait ici pire que decorative : elle donnerait a croire que le
 * biais est garde. <b>Le mecanisme de lot est ecrit pour qu'elle s'y branche le jour ou sa ligne
 * entre</b>, et le marqueur qu'elle emploiera se declare de la meme facon.
 *
 * <p><b>Ce que ce garde-fou ne juge pas</b> : si un item <i>est</i> de la restitution. Le marqueur
 * est pose a la main dans le YAML servi, par l'auteur du paquet, et une marque menteuse passe. Ce
 * qui est tenu est que <b>chaque item du lot en porte une</b> - donc qu'aucun item, et surtout aucun
 * fichier entier, n'echappe au classement - et que <b>le compte se fasse sur le lot</b>. Le sens de
 * chaque marque reste a nadia-prof, en jouant.
 */
class UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest {

    /** Ecrit sous sa forme a barre oblique, celle que le recensement des garde-fous cherche. */
    private static final Path CONTENU_SERVI = Path.of("src/main/resources/contenu");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of("src/test/resources/contreexemple"
            + "/UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest");

    /**
     * HIS-1, le marqueur que l'auteur d'un paquet pose a cote de l'enonce. Il vit dans un
     * commentaire YAML, donc {@code QuestionContentSourceFactory} ne le voit pas : c'est une
     * annotation d'audit, jamais une donnee de jeu.
     */
    private static final String MARQUE_HIS_1 = "# HIS-1:";

    private static final String MARQUE_RESTITUTION = MARQUE_HIS_1 + " restitution";

    /** La ligne a laquelle un item se compte dans le texte du paquet (ADR-0073 clause 6). */
    private static final String LIGNE_D_ITEM = "- enonce:";

    /**
     * HIS-1 : <i>"au plus un tiers"</i>, arrondi inferieur, <b>calcule sur le lot</b>. Le diviseur
     * est ecrit ici, le plafond ne l'est pas : une constante egale au tiers d'une taille donnee
     * pourrit des que le lot grandit, et c'est exactement ce qui devait etre corrige.
     */
    private static final int PART_DE_RESTITUTION = 3;

    /** Un paquet servi, reduit a ce que les conditions de lot lisent. */
    record Paquet(String fichier, String matiere, String niveau, int items, long marques,
                  long restitutions) {

        /**
         * La cle de regroupement, <b>normalisee</b>. Elle ne sert qu'a decider quels fichiers
         * forment un meme lot ; ce qui s'affiche dans un message est {@link #lotDeclare()}.
         */
        String lot() {
            return normalise(matiere) + " / " + normalise(niveau);
        }

        /** Le couple tel que le descripteur l'ecrit, a l'espace et a l'accent pres. */
        String lotDeclare() {
            return matiere + " / " + niveau;
        }
    }

    /**
     * La forme sur laquelle deux descripteurs sont <b>le meme lot</b> : accents deposes, blancs
     * ramenes a un seul et rognes aux extremites, casse repliee.
     *
     * <p><b>Le motif est un defaut vivant, trouve par yanis-qa le 2026-09-03 et reproduit sur une
     * machine virtuelle.</b> La cle etait la concatenation brute des deux champs. Un descripteur
     * portant {@code "CM1 "} - un espace final, dans du YAML ecrit a la main - produisait
     * <b>deux</b> lots la ou il n'y en a qu'un, et la moitie sans marqueur retombait dans la
     * branche non soumise : <b>ni refusee, ni comptee, invisible</b>. C'est mot pour mot le vert a
     * tort que cet ADR ferme, <b>rouvert par la cle qui calcule sa population</b> - le remede avait
     * change la population et laisse la porte a cote.
     *
     * <p><b>La normalisation traite une famille et non le cas mesure</b> : l'espace final est celui
     * qu'il a reproduit, mais l'espace initial, l'espace double interne, la casse et l'accent
     * cassent la cle exactement pareil, et une famille mesuree sur un seul cas est un incident, pas
     * un perimetre.
     */
    static String normalise(String declare) {
        String sansAccent = Normalizer.normalize(declare, Normalizer.Form.NFD);
        StringBuilder serre = new StringBuilder(sansAccent.length());
        boolean blancPrecedent = false;
        for (int rang = 0; rang < sansAccent.length(); rang++) {
            char lettre = sansAccent.charAt(rang);
            if (Character.getType(lettre) == Character.NON_SPACING_MARK) {
                continue;
            }
            if (Character.isWhitespace(lettre)) {
                blancPrecedent = true;
                continue;
            }
            if (blancPrecedent && serre.length() > 0) {
                serre.append(' ');
            }
            blancPrecedent = false;
            serre.append(Character.toLowerCase(lettre));
        }
        return serre.toString();
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Les paquets d'un repertoire de contenu. Le compte d'items vient de <b>SnakeYAML</b>, la
     * bibliotheque avec laquelle le chargeur de production lit ces memes fichiers ; le compte de
     * marques vient du <b>texte</b>, parce qu'un commentaire n'existe pas pour un analyseur YAML.
     * Les deux sont rapprochees par la clause 1 : un paquet dont le texte ne montre pas autant de
     * lignes d'item que la donnee en declare est rouge, sans quoi le compte de marques porterait
     * sur une population differente de celle qu'il pretend couvrir.
     */
    static List<Paquet> paquetsDe(Path repertoire) {
        List<Paquet> paquets = new ArrayList<>();
        if (!Files.isDirectory(repertoire)) {
            return paquets;
        }
        try (Stream<Path> arborescence = Files.walk(repertoire)) {
            List<Path> fichiers = arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(".yaml"))
                    .sorted().toList();
            for (Path fichier : fichiers) {
                String texte = lire(fichier);
                if (!(new Yaml().load(texte) instanceof Map<?, ?> corps)
                        || !(corps.get("descripteur") instanceof Map<?, ?> descripteur)
                        || !(corps.get("items") instanceof List<?> items)) {
                    continue;
                }
                List<String> lignes = texte.lines().filter(ligne -> ligne.contains(LIGNE_D_ITEM))
                        .toList();
                paquets.add(new Paquet(fichier.getFileName().toString(),
                        String.valueOf(descripteur.get("matiere")),
                        String.valueOf(descripteur.get("niveau")),
                        items.size(),
                        lignes.stream().filter(ligne -> ligne.contains(MARQUE_HIS_1)).count(),
                        lignes.stream().filter(ligne -> ligne.contains(MARQUE_RESTITUTION)).count()));
            }
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
        return paquets;
    }

    /** Les paquets groupes par lot de matiere, dans l'ordre ou les lots apparaissent. */
    static Map<String, List<Paquet>> lotsDeMatiere(List<Paquet> paquets) {
        Map<String, List<Paquet>> lots = new LinkedHashMap<>();
        for (Paquet paquet : paquets) {
            lots.computeIfAbsent(paquet.lot(), lot -> new ArrayList<>()).add(paquet);
        }
        return lots;
    }

    /**
     * Le juge, pur : il ne lit ni disque ni classpath et recoit exactement ce que le recensement
     * ci-dessus produit. C'est ce qui permet de le mordre sur du materiau reel mute en memoire
     * plutot que sur une fixture qui ressemblerait a ce que j'imagine.
     */
    static void verifier(List<Paquet> paquets) {
        List<String> fautes = new ArrayList<>();
        int lotsSoumis = 0;

        for (Map.Entry<String, List<Paquet>> lot : lotsDeMatiere(paquets).entrySet()) {
            String nomDuLot = lot.getValue().get(0).lotDeclare();

            Set<String> ecritures = new LinkedHashSet<>();
            lot.getValue().forEach(paquet -> ecritures.add(paquet.lotDeclare()));
            if (ecritures.size() > 1) {
                fautes.add(nomDuLot + " : un seul lot de matiere, ecrit de " + ecritures.size()
                        + " facons dans les descripteurs servis - " + ecritures + ". La cle de"
                        + " regroupement les reunit, mais deux ecritures d'un meme lot sont un"
                        + " defaut de donnee et non une variante : c'est par la qu'un lot se coupe"
                        + " en silence des qu'un garde-fou compare des chaines brutes (ADR-0079,"
                        + " amendement du 2026-09-03). Fichiers : "
                        + lot.getValue().stream().map(Paquet::fichier).toList());
            }

            long marques = lot.getValue().stream().mapToLong(Paquet::marques).sum();
            if (marques == 0) {
                continue;
            }
            lotsSoumis++;
            int itemsDuLot = lot.getValue().stream().mapToInt(Paquet::items).sum();
            long restitutions = lot.getValue().stream().mapToLong(Paquet::restitutions).sum();

            for (Paquet paquet : lot.getValue()) {
                if (paquet.marques() < paquet.items()) {
                    fautes.add(paquet.fichier() + " : " + (paquet.items() - paquet.marques())
                            + " item(s) sur " + paquet.items() + " ne portent pas de marqueur '"
                            + MARQUE_HIS_1 + " <type>', alors que leur lot de matiere (" + nomDuLot
                            + ") est soumis a HIS-1. Un fichier entier qui rejoint un lot sans"
                            + " marqueur n'est refuse par rien tant que la population est un chemin"
                            + " ecrit en dur : c'est le vert a tort que cette regle ferme"
                            + " (ADR-0079)");
                }
            }

            long plafond = itemsDuLot / PART_DE_RESTITUTION;
            if (restitutions > plafond) {
                fautes.add(nomDuLot + " : " + restitutions + " items de restitution sur "
                        + itemsDuLot + " (" + lot.getValue().size() + " fichier(s)), au-dessus du"
                        + " tiers du LOT, soit " + plafond + ". HIS-1 (nadia-prof) : au plus un"
                        + " tiers des items demande une date, un nom ou un chiffre a restituer, et"
                        + " ce tiers se compte sur le lot de matiere - un ratio calcule fichier par"
                        + " fichier ne veut rien dire (ADR-0079)");
            }
        }

        if (lotsSoumis == 0) {
            fautes.add("ADR-0051 : aucun lot de matiere servi ne porte de marqueur '" + MARQUE_HIS_1
                    + "', les conditions de lot ne portent alors sur rien");
        }
        if (!fautes.isEmpty()) {
            throw new AssertionError("conditions de lot : " + fautes);
        }
    }

    @Test
    void chaqueLotDeMatiereServiTientSesConditionsDeLot() {
        verifier(paquetsDe(CONTENU_SERVI));
    }

    /**
     * Le lot est bien ce que nadia-prof a compte, et par un autre chemin que le sien : elle a lu les
     * fichiers annonces, ce recensement lit les descripteurs servis. <b>Un compte qui concorde par
     * deux chemins vaut mieux qu'un compte recopie.</b>
     */
    @Test
    void lesLotsDeMatiereServisSontCeuxQueNadiaProfACompte() {
        Map<String, List<Paquet>> lots = lotsDeMatiere(paquetsDe(CONTENU_SERVI));

        assertThat(lots.get(normalise("Questionner le monde") + " / " + normalise("CP")))
                .as("nadia-prof, 2026-09-03, section 2.3 : questionner le monde, 3 fichiers a la"
                        + " cloture de s28 ; S29-1 ajoute deux fichiers neufs (cp-qlm-cycle-de-vie,"
                        + " cp-qlm-objet-technique) pour fermer QLM-CP-02 et QLM-CP-07, soit 5"
                        + " fichiers a la cloture de s29 ; S33-PAL2-1 (s34) ajoute"
                        + " cp-frise-de-la-journee (QLM-CP-14), soit 6 fichiers ; S35-QLM11-PLANCLASSE"
                        + " (s35) ajoute cp-plan-de-la-classe (QLM-CP-11), premiere livraison reelle"
                        + " de cette ligne, soit 7 fichiers")
                .hasSize(7);
        assertThat(lots.get(normalise("Questionner le monde") + " / " + normalise("CP")).stream().mapToInt(Paquet::items).sum())
                .as("S28-1 (ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-"
                        + "lots.md, section 3.1) : 24 items a la cloture de s27, +6 par ligne pour"
                        + " fermer les lignes 1, 3 et 5, soit 42 items a la cloture de s28 ; S29-1"
                        + " (ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-"
                        + "plafond-a-huit-lignes.md, section 4.1) ajoute onze items par ligne neuve"
                        + " (QLM-CP-02, QLM-CP-07), soit 64 items a la cloture de s29 ; S33-PAL2-1"
                        + " (s34) ajoute onze items pour le seul paquet de designation servi"
                        + " (cp-frise-de-la-journee, QLM-CP-14), soit 75 items ; S35-QLM11-PLANCLASSE"
                        + " (s35) ajoute six items (cp-plan-de-la-classe, QLM-CP-11), soit 81 items")
                .isEqualTo(81);
        assertThat(lots.get(normalise("Histoire-géographie") + " / " + normalise("CM1")))
                .as("1 fichier a la cloture de s27, inchange a S28-1 qui n'a touche que la matiere"
                        + " questionner le monde ; S28-2 et S29-2 etendent ce meme fichier, motif"
                        + " dans son commentaire (ADR-0081 reserve le decoupage a nadia-prof et"
                        + " camille-po) ; S35-CONS-4 (DEC-0088) scinde ce fichier unique en quatre"
                        + " paquets, un par tache reellement distincte, sans changer le lot de"
                        + " matiere ni son total d'items (ADR-0081 alternative E, decision de grain"
                        + " qui appartient a nadia-prof et camille-po, pas a un decoupage technique)")
                .hasSize(4);
        assertThat(lots.get(normalise("Histoire-géographie") + " / " + normalise("CM1")).stream().mapToInt(Paquet::items).sum())
                .as("S29-2 (ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-"
                        + "a-huit-lignes.md, section 3.3) : 37 items a la cloture de s28, +6 sur"
                        + " HG-CM1-06, +7 sur HG-CM1-07 et +7 sur HG-CM1-14 pour fermer la"
                        + " composition A, soit 57 items ; S32-1 (arbitrage prealable de s32,"
                        + " sections 1.2, 7.2 et 13 ; story S32-1) ajoute huit items sur HG-CM1-01,"
                        + " neuf sur HG-CM1-02, neuf sur HG-CM1-03 et neuf sur HG-CM1-04, soit 92"
                        + " items ; S33-HG-08-12 (docs/backlog/backlog.md, story S33-HG-08-12) ajoute"
                        + " neuf items sur HG-CM1-08 et neuf sur HG-CM1-12, soit 110 items")
                .isEqualTo(110);
    }

    /** Le paquet servi qui porte les marqueurs de HIS-1, choisi par le calcul et non par son nom. */
    private static Paquet paquetSoumis(List<Paquet> paquets) {
        return paquets.stream().filter(paquet -> paquet.marques() > 0).findFirst()
                .orElseThrow(() -> new AssertionError("aucun paquet servi ne porte de marqueur de"
                        + " condition de lot : la morsure ne prouverait rien (ADR-0051)"));
    }

    /**
     * <b>Le defaut de yanis-qa, ferme comme une famille et non comme un cas.</b> Il a reproduit
     * l'espace final ; l'espace initial, l'espace double interne, la casse et l'accent cassaient la
     * cle exactement pareil. Une famille mesuree sur un seul cas est un incident, pas un perimetre.
     *
     * <p>Chaque ecriture fautive du descripteur est appliquee <b>a un second fichier du lot reel</b>
     * et doit tenir deux choses a la fois : le lot reste <b>un seul lot</b> - donc le fichier ne
     * s'echappe pas dans une branche non soumise -, et le garde-fou <b>refuse</b> en nommant les
     * deux ecritures. La premiere moitie est celle qui manquait et qui rendait le defaut muet ; la
     * seconde est ce qui empeche la normalisation d'absorber en silence un defaut de donnee.
     */
    @Test
    void aucuneDivergenceDeCasseDAccentOuDEspaceNeScindeUnLot() {
        List<Paquet> servis = paquetsDe(CONTENU_SERVI);
        Paquet soumis = paquetSoumis(servis);
        int lotsAvant = lotsDeMatiere(servis).size();

        List<Paquet> ecrituresFautives = new ArrayList<>();
        for (String matiere : List.of(soumis.matiere(), soumis.matiere() + " ",
                " " + soumis.matiere(), soumis.matiere().toUpperCase(Locale.ROOT),
                soumis.matiere().toLowerCase(Locale.ROOT), normalise(soumis.matiere()))) {
            for (String niveau : List.of(soumis.niveau(), soumis.niveau() + " ",
                    " " + soumis.niveau(), soumis.niveau().toLowerCase(Locale.ROOT))) {
                Paquet variante = new Paquet("second-fichier-du-lot.yaml", matiere, niveau,
                        3, 3, 0);
                if (!variante.lotDeclare().equals(soumis.lotDeclare())) {
                    ecrituresFautives.add(variante);
                }
            }
        }

        assertThat(ecrituresFautives)
                .as("une famille mesuree sur un seul cas est un incident, pas un perimetre : ce"
                        + " test ne prouve rien s'il n'exerce pas plusieurs ecritures REELLEMENT"
                        + " distinctes de celle du descripteur servi")
                .hasSizeGreaterThanOrEqualTo(4);

        for (Paquet variante : ecrituresFautives) {
            List<Paquet> paquets = new ArrayList<>(servis);
            paquets.add(variante);

            assertThat(lotsDeMatiere(paquets))
                    .as("'%s' : le lot ne se scinde pas, sans quoi la moitie sans marqueur"
                            + " retomberait dans la branche non soumise et ne serait jugee par"
                            + " rien", variante.lotDeclare())
                    .hasSize(lotsAvant);
            assertThatThrownBy(() -> verifier(paquets))
                    .as("'%s' : et la divergence est nommee plutot qu'absorbee",
                            variante.lotDeclare())
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("ecrit de 2 facons")
                    .hasMessageContaining("second-fichier-du-lot.yaml");
        }
    }

    /**
     * <b>L'autre moitie du constat de yanis-qa, et la reponse est : oui, c'est voulu.</b> Un lot
     * dont <b>aucun</b> item ne porte de marqueur n'est pas juge, et ce test l'epingle comme une
     * propriete plutot que de le laisser vivre dans un {@code continue} muet.
     *
     * <p><b>Pourquoi c'est correct</b> : la clause 3 d'ADR-0079 pose que la soumission a une
     * condition de lot est <b>derivee du marqueur</b>. {@code Questionner le monde / CP} n'a rien a
     * voir avec HIS-1 et ne doit pas etre compte contre un tiers de restitution ; l'exiger
     * reviendrait a nommer les matieres soumises, c'est-a-dire a enumerer ce que la regle derive.
     *
     * <p><b>Ce qui rend cette branche sure, et c'est la seule chose qui la rendait dangereuse</b> :
     * le cas redoutable n'est pas <i>un lot sans marqueur</i>, c'est <i>un fichier qui aurait du
     * rejoindre un lot soumis et qui ne l'a pas fait</i>. La cle normalisee ferme toutes les
     * versions ou le descripteur ecrit le meme lot autrement. <b>Reste une version qu'aucune chaine
     * ne peut attraper</b> : un paquet qui declare une matiere franchement differente forme son
     * propre lot, et aucun automate ne sait que {@code "Histoire"} aurait du s'ecrire
     * {@code "Histoire-géographie"} sans un vocabulaire ecrit a la main - ce qui serait un critere
     * enumere sous une promesse universelle. <b>Celui-la est vu par nadia-prof et par la matrice</b>,
     * qui comptent des lignes de referentiel et non des chaines, et il est nomme ici plutot que
     * laisse a trouver.
     */
    @Test
    void unLotDontAucunItemNePorteDeMarqueurNEstPasSoumisEtCEstVoulu() {
        List<Paquet> paquets = new ArrayList<>(paquetsDe(CONTENU_SERVI));
        paquets.add(new Paquet("matiere-neuve-sans-condition-de-lot.yaml", "Matière neuve", "CE2",
                3, 0, 0));

        verifier(paquets);
    }

    /**
     * <b>Morsure 1 - le vert a tort, reproduit.</b> Un second fichier rejoint le lot de matiere sans
     * porter un seul marqueur. C'est le geste exact que la version par fichier ne voyait pas : son
     * chemin de ressource ecrit en dur ne le designait pas, et rien d'autre ne le jugeait.
     */
    @Test
    void unFichierQuiRejointUnLotSoumisSansMarqueurFaitEchouerLeGardeFou() {
        List<Paquet> paquets = new ArrayList<>(paquetsDe(CONTENU_SERVI));
        Paquet soumis = paquetSoumis(paquets);
        paquets.add(new Paquet("second-fichier-du-meme-lot.yaml", soumis.matiere(),
                soumis.niveau(), 9, 0, 0));

        assertThatThrownBy(() -> verifier(paquets))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("second-fichier-du-meme-lot.yaml")
                .hasMessageContaining("ne portent pas de marqueur");
    }

    /**
     * <b>Morsure 2 - le tiers du lot.</b> Le lot reel depasse sa part de restitution d'un item.
     * L'ancrage est le materiau : le nombre d'items ne bouge pas, seule la marque change.
     */
    @Test
    void unLotQuiDepasseLeTiersDeRestitutionFaitEchouerLeGardeFou() {
        List<Paquet> paquets = new ArrayList<>(paquetsDe(CONTENU_SERVI));
        Paquet soumis = paquetSoumis(paquets);
        int itemsDuLot = paquets.stream().filter(paquet -> paquet.lot().equals(soumis.lot()))
                .mapToInt(Paquet::items).sum();
        paquets.set(paquets.indexOf(soumis), new Paquet(soumis.fichier(), soumis.matiere(),
                soumis.niveau(), soumis.items(), soumis.marques(),
                itemsDuLot / PART_DE_RESTITUTION + 1));

        assertThatThrownBy(() -> verifier(paquets))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("au-dessus du tiers du LOT");
    }

    /**
     * <b>L'autre borne, et elle compte autant que les morsures : le meme lot reparti sur deux
     * fichiers, avec toutes ses restitutions dans le plus petit, reste vert.</b> Une lecture par
     * fichier serait ici <b>rouge a tort</b> - le petit fichier depasse largement son propre tiers -
     * alors que le lot tient le sien. C'est la moitie de la propriete que les deux morsures ne
     * prouvent pas : le tiers est une propriete du lot, pas une propriete de chacune de ses parts.
     */
    @Test
    void leMemeLotRepartiSurDeuxFichiersResteVertQuandLeLotTientSonTiers() {
        List<Paquet> paquets = new ArrayList<>(paquetsDe(CONTENU_SERVI));
        Paquet soumis = paquetSoumis(paquets);
        int garde = soumis.items() - 6;
        paquets.set(paquets.indexOf(soumis), new Paquet(soumis.fichier(), soumis.matiere(),
                soumis.niveau(), garde, garde, 0));
        paquets.add(new Paquet("six-items-tous-de-restitution.yaml", soumis.matiere(),
                soumis.niveau(), 6, 6, 6));

        verifier(paquets);
    }

    /** La clause de non-vacuite mord elle aussi (ADR-0051). */
    @Test
    void unContenuSansAucunLotSoumisFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(List.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }

    /**
     * ADR-0013 obligation 3, morsure sur le <b>jeu fautif commite</b>, et elle n'est pas une redite
     * des precedentes : celles-la mutent la sortie du recensement, celle-ci la traverse. C'est le
     * seul test du fichier qui prouve que {@link #paquetsDe(Path)} sait lire un repertoire de
     * paquets sur disque, <b>grouper deux fichiers par leur descripteur</b> et compter des marqueurs
     * qui vivent dans des commentaires que l'analyseur YAML ne rend pas.
     *
     * <p>Le jeu fautif porte <b>deux fichiers d'un meme lot</b> : l'un marque, l'autre pas. C'est la
     * forme exacte du vert a tort, commitee.
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurLeFichierQuiRejointLeLotSansMarqueur() {
        List<Paquet> fautifs = paquetsDe(CONTRE_EXEMPLE.resolve("contenu"));

        assertThat(lotsDeMatiere(fautifs))
                .as("ADR-0051 : ce jeu fautif ne prouve rien si ses deux fichiers ne forment pas un"
                        + " seul lot de matiere")
                .hasSize(1);

        assertThatThrownBy(() -> verifier(fautifs))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("lot-fautif-sans-marqueur.yaml")
                .hasMessageNotContaining("lot-fautif-marque.yaml");
    }
}
