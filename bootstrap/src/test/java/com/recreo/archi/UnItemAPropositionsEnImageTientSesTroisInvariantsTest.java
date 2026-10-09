package com.recreo.archi;

import com.recreo.archi.LIllustrationDeContenuResoutEtAucuneNeDortTest.ItemDeclare;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.recreo.archi.LIllustrationDeContenuResoutEtAucuneNeDortTest.PREFIXE_DE_REPRODUCTION;
import static com.recreo.archi.LIllustrationDeContenuResoutEtAucuneNeDortTest.itemsDeclares;
import static com.recreo.archi.LIllustrationDeContenuResoutEtAucuneNeDortTest.itemsDuDepot;
import static com.recreo.archi.LIllustrationDeContenuResoutEtAucuneNeDortTest.propositionsDe;
import static com.recreo.archi.LIllustrationDeContenuResoutEtAucuneNeDortTest.unItemReelQuiMontreUneReproduction;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * ADR-0104, decision 5 et decision 7 (garde-fou 2) : sur tout item servi dont une proposition porte
 * le prefixe {@code oeuvre-}, trois invariants de contenu.
 *
 * <ol>
 *   <li><b>tout ou rien</b> : toutes ses propositions le portent. Une rangee melangee rendrait un
 *   bouton-texte a cote de boutons-images et ferait juger la moitie de l'item par les garde-fous de
 *   texte ;</li>
 *   <li><b>la reference existe et est une reproduction</b> : l'item declare une {@code illustration}
 *   prefixee. Sans elle il n'y a pas deux oeuvres a rapprocher mais trois a comparer. Que le fichier
 *   existe est la clause de {@link LIllustrationDeContenuResoutEtAucuneNeDortTest} ;</li>
 *   <li><b>la reference n'est pas une proposition</b> : sinon l'item se resout en trouvant l'image
 *   identique, sans rien rapprocher. C'est la seule contrefacon par identite que la machine voie
 *   seule ; le recadrage, le detail et le miroir restent au jugement de nadia-prof.</li>
 * </ol>
 *
 * <p><b>Perimetre derive, jamais enumere</b> (ADR-0013 obligation 1) : tout item de tout paquet des
 * repertoires de contenu des modules declares dont une proposition porte le prefixe. Le prefixe est
 * lu ici, dans un test, et nulle part dans une mecanique (ADR-0104 decision 3, regle 13).
 *
 * <p><b>Population reelle exactement vide a s55</b> (ADR-0051, forme de
 * {@code LIllustrationDeContenuResoutEtAucuneNeDortTest.lesDeuxPopulationsIllustreesValentExactementCeQueLeDepotSert}) :
 * aucun paquet n'a de proposition-image. Les trois clauses se mordent donc sur un item <b>reel</b>
 * (le premier qui montre une reproduction), mute en memoire, et sur un jeu fautif commite. Le
 * commit du premier paquet a reproductions (s56, {@code cp-rapprocher-deux-oeuvres}) a releve
 * {@link #ITEMS_A_PROPOSITIONS_EN_IMAGE} a six.
 */
class UnItemAPropositionsEnImageTientSesTroisInvariantsTest {

    private static final Path CONTRE_EXEMPLE = Path.of("src", "test", "resources", "contreexemple",
            "UnItemAPropositionsEnImageTientSesTroisInvariantsTest");

    private static final String CLE_ILLUSTRATION = "illustration";

    private static final String CLE_BONNE_REPONSE = "bonneReponse";

    private static final String CLE_DISTRACTEURS = "distracteurs";

    private static final String TOUT_OU_RIEN = "tout ou rien";

    private static final String REFERENCE_EXISTE = "la reference existe et est une reproduction";

    private static final String REFERENCE_HORS_DU_CHOIX = "la reference n'est pas une proposition";

    /**
     * ADR-0051 : la taille exacte de la population reelle, 0 a s55. Elle monte dans le commit du
     * premier paquet a propositions-images, avec sa valeur mesuree : <b>0 vers 6, lea-dev, s56,
     * S55-ART-CP-10-PAQUET</b>, les six items de {@code cp-rapprocher-deux-oeuvres}.
     */
    private static final int ITEMS_A_PROPOSITIONS_EN_IMAGE = 6;

    private static boolean estDansLePerimetre(ItemDeclare item) {
        return propositionsDe(item.champs()).stream()
                .anyMatch(proposition -> proposition.startsWith(PREFIXE_DE_REPRODUCTION));
    }

    /** Le juge, pur : il recoit un item tel que le YAML le declare, reel ou mute en memoire. */
    static List<String> defautsDe(ItemDeclare item) {
        if (!estDansLePerimetre(item)) {
            return List.of();
        }
        List<String> propositions = propositionsDe(item.champs());
        String ou = item.paquet() + ", item \"" + item.champs().get("enonce") + "\"";
        List<String> defauts = new ArrayList<>();

        List<String> enTexte = propositions.stream()
                .filter(proposition -> !proposition.startsWith(PREFIXE_DE_REPRODUCTION))
                .toList();
        if (!enTexte.isEmpty()) {
            defauts.add(ou + " : " + TOUT_OU_RIEN + ". " + enTexte + " ne porte(nt) pas le prefixe "
                    + PREFIXE_DE_REPRODUCTION + " alors qu'une autre proposition le porte : la rangee"
                    + " rendrait un bouton-texte a cote de boutons-images (ADR-0104 decision 5, 1)");
        }

        String reference = item.champs().get(CLE_ILLUSTRATION) instanceof String illustration
                ? illustration : null;
        if (reference == null || !reference.startsWith(PREFIXE_DE_REPRODUCTION)) {
            defauts.add(ou + " : " + REFERENCE_EXISTE + ". L'illustration vaut " + reference
                    + " : sans reference, il n'y a pas deux oeuvres a rapprocher mais trois a comparer"
                    + " (ADR-0104 decision 5, 2)");
        } else if (propositions.contains(reference)) {
            defauts.add(ou + " : " + REFERENCE_HORS_DU_CHOIX + ". " + reference + " est aussi une"
                    + " proposition : l'item se resout en trouvant l'image identique, sans rien"
                    + " rapprocher (ADR-0104 decision 5, 3)");
        }
        return defauts;
    }

    private static List<String> defautsDuDepot(Path racine) {
        List<String> defauts = new ArrayList<>();
        for (ItemDeclare item : itemsDuDepot(racine)) {
            defauts.addAll(defautsDe(item));
        }
        return defauts;
    }

    /** Une copie de l'item, ordre des cles conserve, dont une cle est mutee ou retiree en memoire. */
    private static ItemDeclare mute(ItemDeclare reel, String cle, Object valeur) {
        Map<Object, Object> copie = new LinkedHashMap<>(reel.champs());
        if (valeur == null) {
            copie.remove(cle);
        } else {
            copie.put(cle, valeur);
        }
        return new ItemDeclare(reel.paquet(), copie);
    }

    /** L'item reel, mute pour proposer trois autres reproductions reelles que sa reference. */
    private static ItemDeclare unItemConformeMuteDepuisLeReel(Path racine) {
        ItemDeclare reel = unItemReelQuiMontreUneReproduction(racine);
        String reference = String.valueOf(reel.champs().get(CLE_ILLUSTRATION));
        Set<String> autres = new LinkedHashSet<>();
        for (ItemDeclare item : itemsDuDepot(racine)) {
            if (item.champs().get(CLE_ILLUSTRATION) instanceof String illustration
                    && illustration.startsWith(PREFIXE_DE_REPRODUCTION)
                    && !illustration.equals(reference)) {
                autres.add(illustration);
            }
        }
        List<String> trois = new ArrayList<>(autres).subList(0, 3);
        return mute(mute(reel, CLE_BONNE_REPONSE, trois.get(0)), CLE_DISTRACTEURS,
                List.of(trois.get(1), trois.get(2)));
    }

    @Test
    void chaqueItemServiAPropositionsEnImageTientSesTroisInvariants() {
        assertThat(defautsDuDepot(LesGardeFousSontMisALEpreuveTest.racineDuDepot()))
                .as("ADR-0104 decision 5 : tout item dont une proposition porte le prefixe tient ses"
                        + " trois invariants")
                .isEmpty();
    }

    /** ADR-0051, forme exacte : le perimetre reel est vide a s55, et le premier paquet le releve. */
    @Test
    void laPopulationReelleDItemsAPropositionsEnImageVautExactementCeQueLeDepotSert() {
        List<ItemDeclare> dansLePerimetre = itemsDuDepot(LesGardeFousSontMisALEpreuveTest.racineDuDepot())
                .stream().filter(UnItemAPropositionsEnImageTientSesTroisInvariantsTest::estDansLePerimetre)
                .toList();

        assertThat(dansLePerimetre)
                .as("ADR-0051 : le nombre d'items servis dont une proposition porte le prefixe vaut"
                        + " exactement %d. S'il monte, un paquet a propositions-images est entre :"
                        + " relever ce nombre dans le commit qui l'apporte, avec sa valeur mesuree",
                        ITEMS_A_PROPOSITIONS_EN_IMAGE)
                .hasSize(ITEMS_A_PROPOSITIONS_EN_IMAGE);
    }

    /** Temoin des morsures : l'item reel, mute en trois reproductions autres que sa reference, ne reproche rien. */
    @Test
    void unItemReelMuteEnTroisReproductionsAutresQueSaReferenceTientSesInvariants() {
        ItemDeclare conforme = unItemConformeMuteDepuisLeReel(LesGardeFousSontMisALEpreuveTest.racineDuDepot());

        assertThat(estDansLePerimetre(conforme))
                .as("la mutation doit entrer dans le perimetre, sans quoi les morsures ne prouvent rien")
                .isTrue();
        assertThat(defautsDe(conforme)).isEmpty();
    }

    /** Morsure de l'invariant 1 : une proposition reste un texte parmi des reproductions. */
    @Test
    void unePropositionEnTexteParmiDesReproductionsFaitEchouerLeGardeFou() {
        ItemDeclare conforme = unItemConformeMuteDepuisLeReel(LesGardeFousSontMisALEpreuveTest.racineDuDepot());
        ItemDeclare mute = mute(conforme, CLE_DISTRACTEURS, List.of("une peinture", "oeuvre-quelconque"));

        assertThat(defautsDe(mute))
                .anyMatch(defaut -> defaut.contains(TOUT_OU_RIEN) && defaut.contains("une peinture"));
    }

    /** Morsures de l'invariant 2 : pas de reference, puis une reference qui n'est pas une reproduction. */
    @Test
    void uneReferenceAbsenteOuQuiNEstPasUneReproductionFaitEchouerLeGardeFou() {
        ItemDeclare conforme = unItemConformeMuteDepuisLeReel(LesGardeFousSontMisALEpreuveTest.racineDuDepot());

        for (ItemDeclare mute : List.of(mute(conforme, CLE_ILLUSTRATION, null),
                mute(conforme, CLE_ILLUSTRATION, "figure-carre"))) {
            assertThat(defautsDe(mute))
                    .as("sans reference reproduction, l'invariant 2 est rouge : %s", mute.champs())
                    .anyMatch(defaut -> defaut.contains(REFERENCE_EXISTE));
        }
    }

    /** Morsure de l'invariant 3 : la reference est l'une des propositions. */
    @Test
    void uneReferenceQuiEstUnePropositionFaitEchouerLeGardeFou() {
        ItemDeclare conforme = unItemConformeMuteDepuisLeReel(LesGardeFousSontMisALEpreuveTest.racineDuDepot());
        String reference = String.valueOf(conforme.champs().get(CLE_ILLUSTRATION));

        for (String cle : List.of(CLE_BONNE_REPONSE, CLE_DISTRACTEURS)) {
            Object valeur = CLE_BONNE_REPONSE.equals(cle) ? reference : List.of(reference, "oeuvre-quelconque");
            ItemDeclare mute = mute(conforme, cle, valeur);
            assertThat(defautsDe(mute))
                    .as("la reference parmi les propositions (%s) rend l'invariant 3 rouge", cle)
                    .anyMatch(defaut -> defaut.contains(REFERENCE_HORS_DU_CHOIX));
        }
    }

    /**
     * ADR-0013 obligation 3, jeu fautif commite : traverse la lecture du YAML par le recensement
     * partage. Trois items, un par invariant ; chacun n'est refuse que par sa propre clause.
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSesTroisClauses() {
        List<ItemDeclare> items = itemsDeclares(List.of(CONTRE_EXEMPLE.resolve("paquet-fautif").resolve("contenu")));

        assertThat(items)
                .as("ADR-0051 : ce jeu fautif ne prouve rien si le recensement n'y lit pas ses trois items")
                .hasSize(3);
        List<String> defauts = new ArrayList<>();
        items.forEach(item -> defauts.addAll(defautsDe(item)));
        assertThat(String.join("\n", defauts))
                .contains(TOUT_OU_RIEN, REFERENCE_EXISTE, REFERENCE_HORS_DU_CHOIX);
        assertThat(defauts).as("une faute par item, pas davantage").hasSize(3);
    }
}
