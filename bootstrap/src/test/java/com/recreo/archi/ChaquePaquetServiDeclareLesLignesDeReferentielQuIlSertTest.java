package com.recreo.archi;

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
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0081, condition HIS-4 de nadia-prof (2026-09-03) : <b>un paquet servi declare les lignes de
 * referentiel qu'il sert, par leur code, et un item ne sert qu'une ligne.</b> C'est ce qui rend
 * {@code FER-2} - <i>"au moins dix items servent la ligne, tous paquets confondus"</i> - calculable
 * par autre chose que la main de nadia-prof.
 *
 * <p><b>Le fait, et il est d'elle.</b> Le 2026-09-03 elle a mesure que
 * {@code cm1-histoire-geographie.yaml} sert <b>onze lignes de referentiel avec vingt items</b>, soit
 * 1,8 item par competence, six de ces lignes n'etant servies que par <b>un</b> item. Elle ne l'avait
 * pas vu a s27 <i>"alors que le chiffre etait sous mes yeux"</i>, parce que le paquet porte une
 * seule chaine de competence qui enumere onze lignes en toutes lettres : l'attribution item par
 * ligne etait la sienne, faite a la main, <i>"verifiee seulement par le fait que sa somme retombe
 * sur vingt"</i>. Personne d'autre ne pouvait la refaire.
 *
 * <p><b>L'identite d'une ligne est un code alloue, jamais un intitule ni un rang.</b> Un marqueur
 * qui recopierait l'intitule casserait au premier reformatage ; un marqueur qui porterait le rang de
 * la ligne dans son referentiel <b>changerait de sens en silence</b> a la premiere insertion, ce qui
 * est la famille de defaut que ce depot sanctionne. Le code est ecrit entre crochets en tete de la
 * ligne du referentiel ({@code - [HG-CM1-11] Identifier les caracteristiques d'un lieu de vie...}),
 * il est alloue une fois et n'est jamais reutilise, comme un numero d'ADR.
 *
 * <p><b>Population derivee des deux cotes (ADR-0013 obligation 1).</b> Les paquets viennent du
 * repertoire de contenu servi de chaque module declare au pom agregateur, par
 * {@link AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest#repertoiresDeContenu(Path)} - ce
 * garde-fou lit donc {@code src/main/resources/contenu} sans en tenir une seconde notion. Les lignes
 * viennent des referentiels de {@code squad/programme}, jamais d'une liste ecrite ici : aucune
 * matiere, aucun niveau et aucun code ne sont nommes dans ce fichier hors de ses jeux fautifs.
 *
 * <p><b>Ce que ce garde-fou interdit vraiment</b>, ecrit avant qu'on demande a quoi il sert :
 * <ol>
 *   <li>un paquet servi qui ne declare aucune ligne - donc invisible a tout comptage ;</li>
 *   <li>un code cite que <b>aucun referentiel ne declare</b> : une faute de frappe rendrait le
 *       compte d'une ligne reelle silencieusement nul ;</li>
 *   <li>un code <b>declare deux fois</b> : deux competences fondues en un seul compte ;</li>
 *   <li>un code d'un <b>autre niveau</b> que celui du paquet qui le cite - le niveau du code est
 *       verifie contre le nom du fichier de referentiel qui le declare et contre le descripteur du
 *       paquet, sans qu'aucun niveau soit ecrit ici ;</li>
 *   <li>un paquet qui declare <b>plusieurs</b> lignes sans dire, item par item, laquelle chacun
 *       sert - c'est exactement le paquet d'histoire-geographie de s27, et c'est le cas qui a rendu
 *       {@code FER-2} invisible ;</li>
 *   <li>une ligne declaree par un paquet et servie par <b>aucun</b> de ses items : une couverture
 *       revendiquee que rien ne porte.</li>
 * </ol>
 *
 * <p><b>Ce qu'il ne juge pas</b> : si un item sert vraiment la ligne qu'il declare. Une declaration
 * menteuse passe, comme une marque {@code HIS-1} menteuse passe (ADR-0079). Ce qui est tenu est que
 * <b>chaque item soit attribue a une ligne et une seule</b>, et que <b>l'identite de cette ligne
 * resolve</b> ; le sens reste a nadia-prof, en jouant et en lisant.
 *
 * <p><b>Il ne verifie pas non plus le seuil de dix items de {@code FER-2}, et c'est delibere</b> :
 * une ligne peut legitimement vivre sous dix items tant qu'elle est {@code en cours}. Le seuil est
 * une <b>condition de fermeture</b>, prononcee par nadia-prof et ecrite dans la matrice par
 * camille-po ; un garde-fou qui l'imposerait ferait un verdict pedagogique. Ce que la CI garantit
 * est que le compte sur lequel elles s'appuient est calculable et fiable, et
 * {@link #leComptePourUneLigneEstCeluiDesItemsQuiLaCitent()} le prouve.
 *
 * <p><b>Le cliquet, et son plafond vaut exactement la dette.</b> L'utilisatrice a arbitre le
 * 2026-09-03 que le marqueur est obligatoire sur <b>tous</b> les paquets et que la reprise se fait
 * en s28. Les paquets non encore repris sont nommes dans {@link #DETTE_DE_DECLARATION} : la liste ne
 * peut pas grandir - un paquet neuf sans declaration est rouge -, elle ne peut pas pourrir - un nom
 * qui ne designe plus un paquet servi non declarant est rouge -, et la vider oblige a baisser le
 * plafond dans le meme diff. Meme mecanique que {@code DETTE_DE_BALAYAGE} d'ADR-0013.
 */
class ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest {

    /** Les referentiels de competences, seule source de l'identite d'une ligne. */
    private static final String PROGRAMME = "squad/programme";

    /** Ce qui fait d'un fichier de {@link #PROGRAMME} un referentiel : son nom. */
    private static final String REFERENTIEL = "competences-";

    /** La cle du paquet : les lignes que ce paquet sert, au moins une. */
    private static final String CLE_DU_PAQUET = "lignesDeReferentiel";

    /** La cle de l'item : la ligne que cet item sert, exactement une. */
    private static final String CLE_DE_L_ITEM = "ligneDeReferentiel";

    /**
     * La forme d'un code : trois segments, un prefixe de matiere, un niveau, un numero alloue. Le
     * niveau n'est pas enumere ici - il est compare au nom du fichier qui declare le code et au
     * descripteur du paquet qui le cite.
     */
    private static final Pattern CODE = Pattern.compile("[A-Z]{2,4}-[A-Z0-9]{2,4}-[0-9]{2}");

    /** Un code ecrit en tete de ligne de referentiel : {@code - [HG-CM1-11] Identifier ...}. */
    private static final Pattern LIGNE_DECLAREE = Pattern.compile("^- \\[([^]]+)] .+");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of("src", "test", "resources", "contreexemple",
            "ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest");

    /**
     * Les paquets servis ecrits avant l'arbitrage du 2026-09-03, qui ne declarent pas encore leurs
     * lignes. Reprise faite en s28 (marc-dev) : les 42 paquets en dette au 2026-09-03 declarent
     * desormais leur ligne, par transcription depuis {@code MATRICE-couverture.md} resolue via
     * {@code catalogue.yaml} - sauf {@code cm1-histoire-geographie}, dont l'attribution item par
     * ligne reprend celle de nadia-prof (section 3.2 de
     * {@code ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md}). La
     * dette est donc vide ; {@code PLAFOND_DE_DETTE} descend avec elle, dans le meme diff.
     */
    private static final Set<String> DETTE_DE_DECLARATION = Set.of();

    /**
     * Etat au 2026-09-04, s28, apres la reprise arbitree par l'utilisatrice : 45 paquets servis,
     * 45 declarants, 0 en dette. Ce nombre ne remonte jamais ; le remonter demanderait un diff
     * visible, comme {@code PLAFOND_DE_DETTE} d'ADR-0013.
     */
    private static final int PLAFOND_DE_DETTE = 0;

    /** Une ligne de referentiel : son code, et le fichier qui la declare. */
    record Ligne(String code, String fichier) {

        /** Le niveau que le nom du fichier porte : {@code competences-cm1.md} -> {@code cm1}. */
        String niveauDuFichier() {
            int debut = fichier.indexOf(REFERENTIEL) + REFERENTIEL.length();
            int fin = fichier.lastIndexOf('.');
            return normalise(fichier.substring(debut, fin < debut ? fichier.length() : fin));
        }

        /** Le segment de niveau du code lui-meme : {@code HG-CM1-11} -> {@code cm1}. */
        String niveauDuCode() {
            return niveauDuCode(code);
        }

        static String niveauDuCode(String code) {
            String[] segments = code.split("-");
            return segments.length == 3 ? normalise(segments[1]) : "";
        }
    }

    /**
     * Un paquet servi, reduit a ce que cette regle lit. {@code lignesDesItems} porte une entree par
     * item, dans l'ordre du fichier, vide quand l'item ne declare rien.
     */
    record Paquet(String fichier, String niveau, List<String> lignes, List<String> lignesDesItems) {

        int items() {
            return lignesDesItems.size();
        }
    }

    /**
     * Accents deposes, blancs rognes, casse repliee. Meme motif qu'ADR-0079 amende : un descripteur
     * ecrit a la main porte un jour un espace final, et une comparaison de chaines brutes coupe
     * alors en silence ce qui devait se rejoindre.
     */
    static String normalise(String declare) {
        String sansAccent = Normalizer.normalize(declare, Normalizer.Form.NFD);
        StringBuilder serre = new StringBuilder(sansAccent.length());
        for (int rang = 0; rang < sansAccent.length(); rang++) {
            char lettre = sansAccent.charAt(rang);
            if (Character.getType(lettre) == Character.NON_SPACING_MARK || Character.isWhitespace(lettre)) {
                continue;
            }
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

    private static List<Path> fichiers(Path repertoire, String extension) {
        if (!Files.isDirectory(repertoire)) {
            return List.of();
        }
        try (Stream<Path> arborescence = Files.walk(repertoire)) {
            return arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(extension))
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Les lignes declarees par les referentiels d'une arborescence, dans l'ordre de lecture. */
    static List<Ligne> lignesDe(Path repertoire) {
        List<Ligne> lignes = new ArrayList<>();
        for (Path fichier : fichiers(repertoire, ".md")) {
            String nom = fichier.getFileName().toString();
            if (!nom.startsWith(REFERENTIEL)) {
                continue;
            }
            lire(fichier).lines()
                    .map(LIGNE_DECLAREE::matcher)
                    .filter(java.util.regex.Matcher::matches)
                    .forEach(marque -> lignes.add(new Ligne(marque.group(1), nom)));
        }
        return lignes;
    }

    /**
     * <b>Les codes de ligne qu'un arbre alloue, pour le garde-fou du registre d'identifiants</b>
     * (ADR-0085, clause 6 ; ADR-0088, clause D).
     *
     * <p>ADR-0081 alloue les codes de ligne <b>ici</b> et ADR-0085 alloue les identifiants de
     * condition dans son registre. Les deux espaces de noms se ressemblent, et le garde-fou du
     * registre doit savoir lequel possede un code a trois segments pour ne pas exiger l'allocation
     * d'un code que ce fichier-ci gouverne deja. Il appelle donc cette methode plutot que de tenir
     * une seconde notion de ce qu'est un code de ligne : ADR-0061, un second exemplaire d'une
     * association rancit.</p>
     */
    static Set<String> codesDeLigneAlloues(Path racine) {
        return lignesDe(racine.resolve(PROGRAMME)).stream()
                .map(Ligne::code)
                .collect(java.util.stream.Collectors.toCollection(LinkedHashSet::new));
    }

    /** Les paquets d'une arborescence de contenu, lus par la bibliotheque du chargeur servi. */
    static List<Paquet> paquetsDe(Path repertoire) {
        List<Paquet> paquets = new ArrayList<>();
        for (Path fichier : fichiers(repertoire, ".yaml")) {
            if (!(new Yaml().load(lire(fichier)) instanceof Map<?, ?> corps)
                    || !(corps.get("descripteur") instanceof Map<?, ?> descripteur)) {
                continue;
            }
            List<String> declarees = new ArrayList<>();
            if (corps.get(CLE_DU_PAQUET) instanceof List<?> brutes) {
                brutes.forEach(code -> declarees.add(String.valueOf(code)));
            } else if (corps.get(CLE_DU_PAQUET) != null) {
                declarees.add(String.valueOf(corps.get(CLE_DU_PAQUET)));
            }
            List<String> parItem = new ArrayList<>();
            if (corps.get("items") instanceof List<?> items) {
                for (Object item : items) {
                    Object declare = item instanceof Map<?, ?> champs ? champs.get(CLE_DE_L_ITEM) : null;
                    parItem.add(declare == null ? "" : String.valueOf(declare));
                }
            }
            String nom = fichier.getFileName().toString();
            paquets.add(new Paquet(nom.substring(0, nom.length() - ".yaml".length()),
                    String.valueOf(descripteur.get("niveau")), List.copyOf(declarees), List.copyOf(parItem)));
        }
        return paquets;
    }

    static List<Paquet> paquetsServis(Path racine) {
        List<Paquet> paquets = new ArrayList<>();
        AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu(racine)
                .forEach(repertoire -> paquets.addAll(paquetsDe(repertoire)));
        return paquets;
    }

    /**
     * Le compte de {@code FER-2} : combien d'items servent chaque ligne, tous paquets confondus. Un
     * paquet qui declare une seule ligne y verse tous ses items ; un paquet qui en declare plusieurs
     * y verse chaque item sur la ligne que cet item nomme. Un paquet genere, qui n'enumere aucun
     * item, verse zero - c'est l'exception nommee par nadia-prof, l'espace engendre par ses
     * parametres tenant le role du compte.
     */
    static Map<String, Long> itemsParLigne(List<Paquet> paquets) {
        Map<String, Long> comptes = new LinkedHashMap<>();
        for (Paquet paquet : paquets) {
            for (String declare : paquet.lignesDesItems()) {
                String code = paquet.lignes().size() == 1 ? paquet.lignes().get(0) : declare;
                if (!code.isEmpty()) {
                    comptes.merge(code, 1L, Long::sum);
                }
            }
        }
        return comptes;
    }

    /**
     * Le juge, pur : il ne lit ni disque ni classpath et recoit exactement ce que les deux
     * recensements produisent. C'est ce qui permet de le mordre sur du materiau reel mute en
     * memoire plutot que sur une fixture qui ressemblerait a ce que j'imagine.
     */
    static void verifier(List<Ligne> lignes, List<Paquet> paquets, Set<String> dette) {
        List<String> fautes = new ArrayList<>();
        Map<String, Ligne> declarees = new LinkedHashMap<>();

        for (Ligne ligne : lignes) {
            if (!CODE.matcher(ligne.code()).matches()) {
                fautes.add(ligne.fichier() + " : le code '" + ligne.code() + "' n'a pas la forme"
                        + " PREFIXE-NIVEAU-NUMERO (ADR-0081)");
                continue;
            }
            if (!ligne.niveauDuCode().equals(ligne.niveauDuFichier())) {
                fautes.add(ligne.fichier() + " : le code '" + ligne.code() + "' annonce le niveau '"
                        + ligne.niveauDuCode() + "' dans un referentiel de niveau '"
                        + ligne.niveauDuFichier() + "' (ADR-0081)");
                continue;
            }
            Ligne premiere = declarees.putIfAbsent(ligne.code(), ligne);
            if (premiere != null) {
                fautes.add("le code '" + ligne.code() + "' est declare deux fois, par "
                        + premiere.fichier() + " et par " + ligne.fichier() + ". Un code fondu"
                        + " compte deux competences pour une seule (ADR-0081)");
            }
        }

        int declarants = 0;
        for (Paquet paquet : paquets) {
            if (paquet.lignes().isEmpty()) {
                if (!dette.contains(paquet.fichier())) {
                    fautes.add(paquet.fichier() + " : aucune ligne de referentiel declaree sous la"
                            + " cle '" + CLE_DU_PAQUET + "'. Un paquet qui ne dit pas quelles lignes"
                            + " il sert n'entre dans aucun compte, et FER-2 redevient invisible"
                            + " (HIS-4, ADR-0081)");
                }
                continue;
            }
            declarants++;

            for (String code : paquet.lignes()) {
                Ligne ligne = declarees.get(code);
                if (ligne == null) {
                    fautes.add(paquet.fichier() + " : la ligne '" + code + "' n'est declaree par"
                            + " aucun referentiel de " + PROGRAMME + ". Le compte de la ligne"
                            + " reellement servie resterait nul sans que rien ne le dise"
                            + " (ADR-0081)");
                    continue;
                }
                if (!Ligne.niveauDuCode(code).equals(normalise(paquet.niveau()))) {
                    fautes.add(paquet.fichier() + " : le paquet est de niveau '" + paquet.niveau()
                            + "' et cite la ligne '" + code + "', d'un autre niveau (ADR-0081)");
                }
            }

            if (paquet.lignes().size() == 1) {
                for (int rang = 0; rang < paquet.items(); rang++) {
                    if (!paquet.lignesDesItems().get(rang).isEmpty()) {
                        fautes.add(paquet.fichier() + " : l'item " + rang + " porte '"
                                + CLE_DE_L_ITEM + "' alors que le paquet ne sert qu'une ligne. Deux"
                                + " sources pour une meme attribution divergent un jour ; la ligne"
                                + " du paquet vaut pour tous ses items (ADR-0081)");
                    }
                }
                continue;
            }

            Set<String> servies = new LinkedHashSet<>();
            for (int rang = 0; rang < paquet.items(); rang++) {
                String declare = paquet.lignesDesItems().get(rang);
                if (declare.isEmpty()) {
                    fautes.add(paquet.fichier() + " : l'item " + rang + " ne declare pas la ligne"
                            + " qu'il sert, alors que le paquet en sert " + paquet.lignes().size()
                            + ". C'est le paquet d'histoire-geographie de s27 : onze lignes, vingt"
                            + " items, et une attribution que seule nadia-prof pouvait refaire"
                            + " (HIS-4, ADR-0081)");
                    continue;
                }
                if (!paquet.lignes().contains(declare)) {
                    fautes.add(paquet.fichier() + " : l'item " + rang + " sert la ligne '" + declare
                            + "', que le paquet ne declare pas (ADR-0081)");
                    continue;
                }
                servies.add(declare);
            }
            for (String code : paquet.lignes()) {
                if (!servies.contains(code)) {
                    fautes.add(paquet.fichier() + " : la ligne '" + code + "' est declaree par le"
                            + " paquet et servie par aucun de ses items - une couverture revendiquee"
                            + " que rien ne porte (ADR-0081)");
                }
            }
        }

        if (declarees.isEmpty()) {
            fautes.add("ADR-0051 : aucun referentiel de " + PROGRAMME + " ne declare de code de"
                    + " ligne, cette regle ne porte alors sur rien");
        }
        if (declarants == 0) {
            fautes.add("ADR-0051 : aucun paquet servi ne declare de ligne de referentiel, cette"
                    + " regle ne porte alors sur rien");
        }
        if (!fautes.isEmpty()) {
            throw new AssertionError("declaration de ligne de referentiel : " + fautes);
        }
    }

    @Test
    void chaquePaquetServiDeclareLesLignesQuIlSert() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();

        verifier(lignesDe(racine.resolve(PROGRAMME)), paquetsServis(racine), DETTE_DE_DECLARATION);
    }

    /** Le paquet servi qui declare, choisi par le calcul et non par son nom. */
    private static Paquet declarant(List<Paquet> paquets) {
        return paquets.stream().filter(paquet -> !paquet.lignes().isEmpty()).findFirst()
                .orElseThrow(() -> new AssertionError("aucun paquet servi ne declare de ligne : la"
                        + " morsure ne prouverait rien (ADR-0051)"));
    }

    private static void verifierLeDepot(List<Paquet> paquets) {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        verifier(lignesDe(racine.resolve(PROGRAMME)), paquets, DETTE_DE_DECLARATION);
    }

    /**
     * <b>Le compte de {@code FER-2} existe, et il est celui des items qui citent la ligne.</b> Deux
     * proprietes, sur le materiau reel : aucun item d'un paquet declarant ne tombe hors du compte,
     * et un item de plus fait exactement un point de plus sur sa ligne et sur aucune autre.
     *
     * <p>Aucun nombre n'est fige ici : un compte fige devrait etre remonte a chaque lot de contenu,
     * ce qui en ferait une corvee plutot qu'une preuve.
     */
    @Test
    void leComptePourUneLigneEstCeluiDesItemsQuiLaCitent() {
        List<Paquet> servis = paquetsServis(LesGardeFousSontMisALEpreuveTest.racineDuDepot());
        Map<String, Long> comptes = itemsParLigne(servis);

        assertThat(comptes)
                .as("ADR-0051 : sans une ligne servie au moins, ce compte ne prouve rien")
                .isNotEmpty();
        assertThat(comptes.values().stream().mapToLong(Long::longValue).sum())
                .as("aucun item d'un paquet declarant ne tombe hors du compte")
                .isEqualTo(servis.stream().filter(paquet -> !paquet.lignes().isEmpty())
                        .mapToLong(Paquet::items).sum());

        Paquet declarant = declarant(servis);
        List<String> avecUnItemDePlus = new ArrayList<>(declarant.lignesDesItems());
        avecUnItemDePlus.add("");
        List<Paquet> mute = new ArrayList<>(servis);
        mute.set(servis.indexOf(declarant), new Paquet(declarant.fichier(), declarant.niveau(),
                declarant.lignes(), avecUnItemDePlus));
        Map<String, Long> apres = itemsParLigne(mute);

        String ligneMutee = declarant.lignes().get(0);
        assertThat(apres.get(ligneMutee))
                .as("un item de plus sur '%s' vaut exactement un point de plus", ligneMutee)
                .isEqualTo(comptes.get(ligneMutee) + 1);
        apres.forEach((code, compte) -> {
            if (!code.equals(ligneMutee)) {
                assertThat(compte)
                        .as("'%s' : le compte d'une autre ligne ne bouge pas", code)
                        .isEqualTo(comptes.get(code));
            }
        });
    }

    /**
     * <b>Morsure 1 - le paquet muet.</b> Un paquet servi neuf n'entre dans aucun compte tant qu'il
     * ne declare rien, et la dette ne le couvre pas : elle nomme les paquets ecrits avant
     * l'arbitrage, jamais ceux qui viennent apres.
     */
    @Test
    void unPaquetNeufSansDeclarationFaitEchouerLeGardeFou() {
        List<Paquet> paquets = new ArrayList<>(
                paquetsServis(LesGardeFousSontMisALEpreuveTest.racineDuDepot()));
        paquets.add(new Paquet("paquet-neuf-sans-ligne", "CP", List.of(),
                List.of("", "", "")));

        assertThatThrownBy(() -> verifierLeDepot(paquets))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-neuf-sans-ligne")
                .hasMessageContaining("aucune ligne de referentiel declaree");
    }

    /**
     * <b>Morsure 2 - le code qui ne designe rien.</b> C'est la faute la plus silencieuse de la
     * famille : le paquet declare, le compte se fait, et la ligne reellement servie reste a zero.
     */
    @Test
    void uneLigneQueAucunReferentielNeDeclareFaitEchouerLeGardeFou() {
        List<Paquet> servis = paquetsServis(LesGardeFousSontMisALEpreuveTest.racineDuDepot());
        Paquet declarant = declarant(servis);
        String inconnue = declarant.lignes().get(0).replaceAll("[0-9]{2}$", "99");
        List<Paquet> paquets = new ArrayList<>(servis);
        paquets.set(servis.indexOf(declarant), new Paquet(declarant.fichier(), declarant.niveau(),
                List.of(inconnue), declarant.lignesDesItems()));

        assertThatThrownBy(() -> verifierLeDepot(paquets))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(inconnue)
                .hasMessageContaining("n'est declaree par aucun referentiel");
    }

    /**
     * <b>Morsure 3 - le code cite par un paquet d'un autre niveau.</b> Le niveau n'est ecrit nulle
     * part dans ce fichier : il est lu dans le nom du referentiel et dans le descripteur du paquet.
     */
    @Test
    void unPaquetQuiCiteUneLigneDUnAutreNiveauFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<Paquet> servis = paquetsServis(racine);
        Paquet declarant = declarant(servis);
        String autreNiveau = lignesDe(racine.resolve(PROGRAMME)).stream()
                .map(Ligne::code)
                .filter(code -> !Ligne.niveauDuCode(code).equals(normalise(declarant.niveau())))
                .findFirst()
                .orElseThrow(() -> new AssertionError("aucun referentiel d'un autre niveau que '"
                        + declarant.niveau() + "' : la morsure ne prouverait rien"));
        List<Paquet> paquets = new ArrayList<>(servis);
        paquets.set(servis.indexOf(declarant), new Paquet(declarant.fichier(), declarant.niveau(),
                List.of(autreNiveau), declarant.lignesDesItems()));

        assertThatThrownBy(() -> verifierLeDepot(paquets))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(autreNiveau)
                .hasMessageContaining("d'un autre niveau");
    }

    /**
     * <b>Morsure 4 - le paquet de s27, reproduit.</b> Plusieurs lignes declarees, aucun item
     * attribue : c'est l'etat exact de {@code cm1-histoire-geographie.yaml}, celui qui a laisse
     * passer six competences servies par un seul item.
     */
    @Test
    void unPaquetAPlusieursLignesDontLesItemsNAttribuentRienFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<Paquet> servis = paquetsServis(racine);
        Paquet declarant = declarant(servis);
        List<String> deuxLignes = lignesDe(racine.resolve(PROGRAMME)).stream()
                .map(Ligne::code)
                .filter(code -> Ligne.niveauDuCode(code).equals(normalise(declarant.niveau())))
                .limit(2).toList();
        assertThat(deuxLignes).as("il faut deux lignes reelles du meme niveau pour mordre").hasSize(2);
        List<Paquet> paquets = new ArrayList<>(servis);
        paquets.set(servis.indexOf(declarant), new Paquet(declarant.fichier(), declarant.niveau(),
                deuxLignes, declarant.lignesDesItems()));

        assertThatThrownBy(() -> verifierLeDepot(paquets))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne declare pas la ligne qu'il sert");
    }

    /**
     * <b>Morsure 5 - la couverture revendiquee que rien ne porte.</b> Un paquet declare deux lignes
     * et n'en sert qu'une : sans cette clause, la seconde entrerait dans la matrice sans un item.
     */
    @Test
    void uneLigneDeclareeQueAucunItemNeSertFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<Paquet> servis = paquetsServis(racine);
        Paquet declarant = declarant(servis);
        List<String> deuxLignes = lignesDe(racine.resolve(PROGRAMME)).stream()
                .map(Ligne::code)
                .filter(code -> Ligne.niveauDuCode(code).equals(normalise(declarant.niveau())))
                .limit(2).toList();
        List<String> tousSurLaPremiere = declarant.lignesDesItems().stream()
                .map(item -> deuxLignes.get(0)).toList();
        List<Paquet> paquets = new ArrayList<>(servis);
        paquets.set(servis.indexOf(declarant), new Paquet(declarant.fichier(), declarant.niveau(),
                deuxLignes, tousSurLaPremiere));

        assertThatThrownBy(() -> verifierLeDepot(paquets))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(deuxLignes.get(1))
                .hasMessageContaining("servie par aucun de ses items");
    }

    /**
     * <b>Morsure 6 - le code declare deux fois.</b> Deux competences fondues en un seul compte
     * feraient tenir {@code FER-2} a deux lignes avec les items d'une seule.
     */
    @Test
    void unCodeDeLigneDeclareDeuxFoisFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<Ligne> lignes = new ArrayList<>(lignesDe(racine.resolve(PROGRAMME)));
        Ligne premiere = lignes.get(0);
        lignes.add(new Ligne(premiere.code(), "competences-" + premiere.niveauDuCode() + ".md"));

        assertThatThrownBy(() -> verifier(lignes, paquetsServis(racine), DETTE_DE_DECLARATION))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(premiere.code())
                .hasMessageContaining("declare deux fois");
    }

    /**
     * <b>L'autre borne : un paquet qui ne sert qu'une ligne ne la repete pas sur ses items.</b> La
     * declaration du paquet vaut pour tous ses items ; deux sources pour une meme attribution
     * divergent un jour, et c'est la moitie de la propriete que les morsures ne prouvent pas.
     */
    @Test
    void unPaquetAUneSeuleLigneDontUnItemLaRepeteFaitEchouerLeGardeFou() {
        List<Paquet> servis = paquetsServis(LesGardeFousSontMisALEpreuveTest.racineDuDepot());
        Paquet declarant = declarant(servis);
        List<String> repetee = new ArrayList<>(declarant.lignesDesItems());
        repetee.set(0, declarant.lignes().get(0));
        List<Paquet> paquets = new ArrayList<>(servis);
        paquets.set(servis.indexOf(declarant), new Paquet(declarant.fichier(), declarant.niveau(),
                declarant.lignes(), repetee));

        assertThatThrownBy(() -> verifierLeDepot(paquets))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("alors que le paquet ne sert qu'une ligne");
    }

    /** La clause de non-vacuite mord elle aussi, des deux cotes (ADR-0051). */
    @Test
    void unePopulationVideFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(List.of(), List.of(), Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }

    /**
     * <b>Le cliquet.</b> Le plafond vaut exactement la taille de la dette - un plafond superieur
     * serait une place libre, donc pas un cliquet -, et chaque nom doit encore designer un paquet
     * servi qui ne declare pas. Reprendre un paquet oblige a le sortir de la liste et a baisser le
     * plafond dans le meme diff.
     */
    @Test
    void laDetteDeDeclarationNePeutNiGrandirNiPourrir() {
        assertThat(DETTE_DE_DECLARATION)
                .as("ADR-0013, amendement du 2026-08-15 : le plafond vaut exactement la taille de la"
                        + " dette")
                .hasSize(PLAFOND_DE_DETTE);

        List<String> nonDeclarants = paquetsServis(LesGardeFousSontMisALEpreuveTest.racineDuDepot())
                .stream().filter(paquet -> paquet.lignes().isEmpty()).map(Paquet::fichier).toList();
        assertThat(nonDeclarants)
                .as("une dette qui nomme un paquet disparu, renomme ou deja repris exempte quelque"
                        + " chose qui n'existe plus (ADR-0081)")
                .containsExactlyInAnyOrderElementsOf(DETTE_DE_DECLARATION);
    }

    /**
     * ADR-0013 obligation 3, morsure sur le <b>jeu fautif commite</b>. Elle n'est pas une redite des
     * precedentes : celles-la mutent la sortie des recensements, celle-ci les traverse. C'est le
     * seul test du fichier qui prouve que les deux lectures savent trouver un referentiel et des
     * paquets sur disque, y lire un code entre crochets et une cle de declaration, et que le paquet
     * sain du meme repertoire n'est pas emporte avec les fautifs.
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSesTroisPaquetsEtPasSurLeQuatrieme() {
        List<Ligne> lignes = lignesDe(CONTRE_EXEMPLE);
        List<Paquet> paquets = paquetsDe(CONTRE_EXEMPLE);

        assertThat(lignes).as("le jeu fautif ne prouve rien sans referentiel a citer").isNotEmpty();
        assertThat(paquets).as("le jeu fautif ne prouve rien sans paquet").hasSizeGreaterThan(3);

        assertThatThrownBy(() -> verifier(lignes, paquets, Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("fautif-sans-declaration")
                .hasMessageContaining("fautif-ligne-inconnue")
                .hasMessageContaining("fautif-item-sans-ligne")
                .hasMessageNotContaining("paquet-sain");
    }
}
