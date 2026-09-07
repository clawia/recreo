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
 * <p><b>Ce que ces deux clauses ne jugent pas.</b> Si un distracteur est <i>plausible</i> - la
 * moitie portante d'{@code OBJ-R2} -, si une reponse est juste, si un item est au niveau de sa
 * classe. Le sens reste a nadia-prof, en jouant et en lisant. Ce qui est tenu est que la
 * <b>fabrication</b> des propositions ne donne pas la reponse a qui ne lit que leur forme.
 *
 * <p><b>Perimetre derive (ADR-0013 obligation 1).</b> Les paquets viennent du repertoire de contenu
 * servi de chaque module declare au pom agregateur, par
 * {@link AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest#repertoiresDeContenu(Path)}. Aucun
 * paquet, aucun item et aucun texte n'est nomme dans ce fichier hors de ses jeux fautifs et de
 * l'exemption datee de la clause 2. Un paquet neuf entre dans la population le jour ou il entre au
 * contenu servi.
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

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of("src", "test", "resources", "contreexemple",
            "LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest");

    /** Un paquet servi, reduit a ce que ces deux clauses regardent. */
    private record Paquet(String nom, List<Item> items) {
    }

    private record Item(List<String> propositions, String bonneReponse) {
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
                        membres.add(rang);
                    }
                }
                if (membres.size() < ITEMS_POUR_UNE_REPETITION) {
                    continue;
                }
                List<String> reponses = new ArrayList<>();
                for (int membre : membres) {
                    String reponse = items.get(membre).bonneReponse();
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

    static void verifierLesPaquets(List<Paquet> paquets, Set<String> dette) {
        assertThat(paquets)
                .as("ADR-0051 : sans paquet a propositions, ce garde-fou n'examine aucun objet")
                .isNotEmpty();
        List<String> defauts = new ArrayList<>(defautsDeRepetition(paquets));
        defauts.addAll(defautsDAmorce(paquets, dette));
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
     * 2026-09-07 : 55 paquets a propositions, 1077 items, sur le reacteur complet, JDK 21.0.10. Les
     * bornes sont posees sous cette mesure ; elles constatent qu'un vert porte sur le contenu servi
     * entier et non sur une poignee de paquets qu'un chargement aurait laisse tomber.
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
    }

    /**
     * ADR-0067 : le cliquet de la clause 2. La dette ne peut ni grandir - le plafond est ecrit et
     * compare - ni pourrir : chaque paquet exempte doit encore etre <b>reellement fautif</b>, sans
     * quoi l'exemption ne nomme plus rien de reel et doit disparaitre.
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
     */
    @Test
    void lePaquetQuiPorteLaReserveFaitRougirLaClauseDeuxSansSonExemption() {
        assertThatThrownBy(() -> verifierLesPaquets(paquetsDuDepot(), Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("L'amorce du bouton departage a la place de la competence");
    }

    /**
     * ADR-0013 obligation 3. Deux jeux fautifs de ma main, qui prouvent que chaque clause mord une
     * situation que l'historique n'a pas encore produite : un paquet ou trois items partagent le
     * moule de leurs propositions et une reponse d'une phrase, et un paquet ou l'amorce des
     * distracteurs les separe des bonnes reponses.
     */
    @Test
    void deuxPaquetsFautifsFontRougirChacuneLeurClause() {
        assertThatThrownBy(() -> verifierLesPaquets(
                paquetsServis(List.of(CONTRE_EXEMPLE.resolve("paquets-fautifs"))), Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ecrans pour un seul item")
                .hasMessageContaining("L'amorce du bouton departage a la place de la competence");
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
