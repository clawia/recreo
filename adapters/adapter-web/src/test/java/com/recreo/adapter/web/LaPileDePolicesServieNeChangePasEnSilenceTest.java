package com.recreo.adapter.web;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.Regle;
import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.reglesDe;
import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.reglesTierces;
import static com.recreo.adapter.web.LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.documents;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.feuillesMaison;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.lire;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.sansCommentaires;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0054, amendement du 2026-10-20 ({@code DEC-0122} 3.5) : <b>la pile de polices servie est celle
 * de la feuille tierce, et ni la feuille maison ni un gabarit ne la changent.</b> Une cote mesuree par
 * {@code nadia-prof} ne vaut que pour la pile avec laquelle elle a ete mesuree ; une police declaree en
 * silence la rendrait fausse sans qu'aucun test le voie.
 *
 * <p><b>Ce qui est refuse.</b> Dans une feuille maison : une declaration {@code font-family} ou
 * {@code font} dont la valeur n'est pas {@code inherit}, une regle {@code @font-face}, ou la definition d'une propriete personnalisee que la
 * feuille tierce lit dans une declaration de police. Dans un gabarit : les memes declarations dans un
 * attribut {@code style} ou {@code th:style} (valeur et litteraux), ou dans une balise
 * {@code <style>}.
 *
 * <p><b>Perimetre derive (ADR-0013).</b> Les variables de police ne sont pas ecrites ici : elles sont
 * lues dans la feuille tierce, celles qu'une declaration {@code font} ou {@code font-family} lit par
 * {@code var(...)}. Nommer {@code --bs-body-font-family} a la main laisserait passer
 * {@code --bs-font-sans-serif}, que la feuille tierce lit elle aussi dans une declaration de police.
 * Une population vide est un echec. Une variable que la feuille tierce ne lirait qu'a travers une
 * autre variable n'est pas suivie : il n'y en a aucune aujourd'hui (ADR-0054, amendement du
 * 2026-10-20).
 *
 * <p><b>Ce qui n'est pas garde, nomme dans l'ADR.</b> Une police posee par le JavaScript apres le
 * rendu ; {@code letter-spacing}, {@code word-spacing}, {@code font-stretch} et toute propriete qui
 * change la largeur d'un texte sans changer la pile ; la feuille tierce elle-meme, epinglee par
 * empreinte (ADR-0014).
 */
class LaPileDePolicesServieNeChangePasEnSilenceTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLES =
            Path.of("src/test/resources/contreexemple/LaPileDePolicesServieNeChangePasEnSilenceTest");

    private static final Set<String> PROPRIETES_DE_POLICE = Set.of("font", "font-family");

    private static final String REGLE_DE_POLICE = "@font-face";

    private static final String MOTIF = "La pile de polices servie est celle de la feuille tierce : une "
            + "cote mesuree ne vaut que pour la pile avec laquelle elle l'a ete (DEC-0122 3.5, ADR-0054, "
            + "amendement du 2026-10-20)";

    /**
     * La seule valeur d'une declaration de police admise : elle reprend la police du parent et n'en
     * pose aucune. La feuille tierce l'ecrit elle-meme sur les controles de formulaire.
     */
    private static final String VALEUR_HERITEE = "inherit";

    /**
     * Une declaration {@code propriete: valeur}, propriete et valeur decodees de leurs echappements
     * CSS, la propriete en minuscules ; {@code texte} est la declaration telle qu'elle est ecrite.
     */
    private record Declaration(String propriete, String valeur, String texte) {
    }

    private static final char BARRE_INVERSE = 92;

    private static boolean estHexadecimal(char caractere) {
        return Character.digit(caractere, 16) >= 0;
    }

    /**
     * Un identifiant CSS decode de ses echappements (ADR-0054, amendement R5) : une barre oblique inverse suivie de 1 a
     * 6 chiffres hexadecimaux et d'un blanc facultatif vaut le caractere de ce point de code ;
     * la barre oblique inverse suivie de tout autre caractere vaut ce caractere. Sans ce decodage,
     * {@code \66ont-family} et {@code @\66ont-face}, qu'un navigateur lit comme {@code font-family} et
     * {@code @font-face}, passeraient la comparaison de texte.
     */
    static String sansEchappements(String texte) {
        StringBuilder decode = new StringBuilder();
        int position = 0;
        while (position < texte.length()) {
            char caractere = texte.charAt(position);
            if (caractere != BARRE_INVERSE || position + 1 >= texte.length()) {
                decode.append(caractere);
                position++;
                continue;
            }
            int debut = position + 1;
            int fin = debut;
            while (fin < texte.length() && fin - debut < 6 && estHexadecimal(texte.charAt(fin))) {
                fin++;
            }
            if (fin == debut) {
                decode.append(texte.charAt(debut));
                position = debut + 1;
                continue;
            }
            decode.appendCodePoint(Integer.parseInt(texte.substring(debut, fin), 16));
            position = fin < texte.length() && Character.isWhitespace(texte.charAt(fin)) ? fin + 1 : fin;
        }
        return decode.toString();
    }

    private static List<Declaration> declarations(String corps) {
        List<Declaration> declarations = new ArrayList<>();
        for (String morceau : corps.split(";")) {
            int deuxPoints = morceau.indexOf(':');
            if (deuxPoints > 0) {
                declarations.add(new Declaration(
                        sansEchappements(morceau.substring(0, deuxPoints).strip()).toLowerCase(Locale.ROOT),
                        sansEchappements(morceau.substring(deuxPoints + 1).strip()).toLowerCase(Locale.ROOT),
                        morceau.strip()));
            }
        }
        return declarations;
    }

    /** Les noms de propriete personnalisee qu'une valeur lit par {@code var(...)}. */
    private static Set<String> variablesLues(String texte) {
        Set<String> lues = new LinkedHashSet<>();
        int position = 0;
        while ((position = texte.indexOf("var(", position)) >= 0) {
            int debut = position + "var(".length();
            int fin = debut;
            while (fin < texte.length() && texte.charAt(fin) != ',' && texte.charAt(fin) != ')') {
                fin++;
            }
            lues.add(texte.substring(debut, fin).strip());
            position = fin;
        }
        return lues;
    }

    /** Les variables que la feuille tierce lit dans une declaration de police. */
    static Set<String> variablesDePolice(List<Regle> tierces) {
        Set<String> variables = new LinkedHashSet<>();
        for (Regle regle : tierces) {
            for (Declaration declaration : declarations(regle.corps())) {
                if (PROPRIETES_DE_POLICE.contains(declaration.propriete())) {
                    variables.addAll(variablesLues(declaration.texte()));
                }
            }
        }
        return variables;
    }

    private static boolean declarePolice(Declaration declaration, Set<String> variables) {
        if (PROPRIETES_DE_POLICE.contains(declaration.propriete())) {
            return !declaration.valeur().equals(VALEUR_HERITEE);
        }
        return variables.contains(declaration.propriete());
    }

    /** Les en-tetes de regle-@ d'un texte de feuille : le lecteur partage n'en rend aucune regle. */
    private static List<String> entetesDeRegleAt(String feuille) {
        List<String> entetes = new ArrayList<>();
        StringBuilder prelude = new StringBuilder();
        for (char caractere : sansCommentaires(feuille).toCharArray()) {
            if (caractere == '{') {
                String entete = prelude.toString().strip();
                if (entete.startsWith("@")) {
                    entetes.add(entete);
                }
                prelude.setLength(0);
            } else if (caractere == '}' || caractere == ';') {
                prelude.setLength(0);
            } else {
                prelude.append(caractere);
            }
        }
        return entetes;
    }

    private static void defautsDeFeuille(String origine, String feuille, Set<String> variables,
                                         List<String> defauts) {
        for (String entete : entetesDeRegleAt(feuille)) {
            if (sansEchappements(entete).toLowerCase(Locale.ROOT).startsWith(REGLE_DE_POLICE)) {
                defauts.add(origine + " : declare une regle " + REGLE_DE_POLICE);
            }
        }
        for (Regle regle : reglesDe(origine, feuille)) {
            for (Declaration declaration : declarations(regle.corps())) {
                if (declarePolice(declaration, variables)) {
                    defauts.add(regle.origine() + " : declare " + declaration.texte());
                }
            }
        }
    }

    /** Les litteraux entre apostrophes d'une valeur d'attribut, plus la valeur elle-meme. */
    private static List<String> textesDeStyle(String valeur) {
        List<String> textes = new ArrayList<>();
        textes.add(valeur);
        int position = 0;
        while ((position = valeur.indexOf('\'', position)) >= 0) {
            int fin = valeur.indexOf('\'', position + 1);
            if (fin < 0) {
                break;
            }
            textes.add(valeur.substring(position + 1, fin));
            position = fin + 1;
        }
        return textes;
    }

    static void verifierQueLaPileNEstPasChangee(Map<String, String> feuillesMaison,
                                                Map<String, Document> gabarits, Set<String> variables) {
        assertThat(variables)
                .as("ADR-0003 : aucune variable de police derivee de la feuille tierce ; la clause des "
                        + "proprietes personnalisees passerait a vide")
                .isNotEmpty();
        assertThat(feuillesMaison).as("ADR-0003 : aucune feuille maison lue").isNotEmpty();
        assertThat(gabarits).as("ADR-0003 : aucun gabarit lu").isNotEmpty();

        List<String> defauts = new ArrayList<>();
        feuillesMaison.forEach((nom, texte) -> defautsDeFeuille(nom, texte, variables, defauts));
        for (Map.Entry<String, Document> gabarit : gabarits.entrySet()) {
            for (Element element : gabarit.getValue().getAllElements()) {
                if (element.tagName().equals("style")) {
                    defautsDeFeuille(gabarit.getKey() + " <style>", element.data(), variables, defauts);
                }
                for (Attribute attribut : element.attributes()) {
                    if (!attribut.getKey().equals("style") && !attribut.getKey().equals("th:style")) {
                        continue;
                    }
                    for (String texte : textesDeStyle(attribut.getValue())) {
                        for (Declaration declaration : declarations(texte)) {
                            if (declarePolice(declaration, variables)) {
                                defauts.add(gabarit.getKey() + " " + attribut.getKey() + " : declare "
                                        + declaration.texte());
                            }
                        }
                    }
                }
            }
        }
        assertThat(defauts).as(MOTIF).isEmpty();
    }

    private static Map<String, String> feuillesMaisonDe(Path racine) {
        Map<String, String> feuilles = new LinkedHashMap<>();
        for (Path feuille : feuillesMaison(racine)) {
            feuilles.put(feuille.getFileName().toString(), lire(feuille));
        }
        return feuilles;
    }

    private static void verifier(Path racine) {
        verifierQueLaPileNEstPasChangee(feuillesMaisonDe(racine), documents(racine),
                variablesDePolice(reglesTierces(racine)));
    }

    private static Map<String, String> feuillesMaisonDeProduction() {
        return feuillesMaisonDe(RACINE_PRODUCTION);
    }

    private static Set<String> variablesDeProduction() {
        return variablesDePolice(reglesTierces(RACINE_PRODUCTION));
    }

    @Test
    void niLaFeuilleMaisonNiUnGabaritNeChangentLaPileDePolices() {
        verifier(RACINE_PRODUCTION);
    }

    /**
     * ADR-0013 obligation 3 : un depot fictif dont la feuille tierce lit une variable de police que la
     * feuille maison redefinit, et dont un gabarit declare une police. Aucun nom du depot reel : la
     * variable fautive n'est connue que par la feuille tierce du jeu, ce qui prouve que la population
     * est derivee.
     */
    @Test
    void unDepotQuiRedefinitLaPileFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(CONTRE_EXEMPLES.resolve("police-declaree-en-silence")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("--tiers-police: Georgia, serif")
                .hasMessageContaining("th:style : declare font: 1rem serif");
    }

    /** Le meme depot, sans la redefinition ni la police du gabarit : une taille et une teinte restent. */
    @Test
    void leMemeDepotSansPoliceDeclareeResteVert() {
        verifier(CONTRE_EXEMPLES.resolve("pile-tierce-intacte"));
    }

    /** ADR-0054, amendement R5 : {@code \66ont-family} est {@code font-family} pour un navigateur. */
    @Test
    void uneProprieteDePoliceEcriteParEchappementFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(CONTRE_EXEMPLES.resolve("propriete-de-police-ecrite-par-echappement")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("body : declare \\66ont-family: Georgia, serif");
    }

    /** ADR-0054, amendement R5 : {@code @\66ont-face} est {@code @font-face} pour un navigateur. */
    @Test
    void uneRegleDePoliceEcriteParEchappementFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(CONTRE_EXEMPLES.resolve("regle-de-police-ecrite-par-echappement")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("declare une regle " + REGLE_DE_POLICE);
    }

    /**
     * Jumeau vert des deux precedents : des echappements qui decodent en {@code color} et en
     * {@code font-size}, et {@code font-family: inherit}, ne declarent aucune police. Le decodage ne
     * transforme pas tout echappement en faute.
     */
    @Test
    void desEchappementsQuiNeNommentAucunePoliceRestentVerts() {
        verifier(CONTRE_EXEMPLES.resolve("echappement-sans-police"));
    }

    /**
     * ADR-0054, amendement R6 : {@code inherit} reprend la police du parent et n'en pose aucune ; la
     * feuille tierce l'ecrit elle-meme. Ajoute a la feuille maison reelle, il reste vert ; une autre
     * valeur generique, {@code initial}, qui rend la police par defaut du navigateur, reste rouge.
     */
    @Test
    void uneDeclarationQuiHeriteLaPoliceDuParentResteVerteEtInitialResteRouge() {
        Map<String, String> heritees = feuillesMaisonDeProduction();
        heritees.replaceAll((nom, texte) -> texte
                + "\nbutton { font-family: inherit; }\n.consigne { font: INHERIT; }\n");
        verifierQueLaPileNEstPasChangee(heritees, documents(RACINE_PRODUCTION), variablesDeProduction());

        Map<String, String> initiales = feuillesMaisonDeProduction();
        initiales.replaceAll((nom, texte) -> texte + "\nbutton { font-family: initial; }\n");
        assertThatThrownBy(() -> verifierQueLaPileNEstPasChangee(initiales, documents(RACINE_PRODUCTION),
                variablesDeProduction()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("button : declare font-family: initial");
    }

    /**
     * Les quatre formes de la story, une a une, puis une variable derivee qu'elle ne nomme pas,
     * chacune ajoutee en fin de la feuille maison reelle : chacune rougit et le message cite son jeton.
     */
    @Test
    void chaqueFormeDeDeclarationDePoliceFaitEchouerLeGardeFou() {
        Map<String, String> mutations = new LinkedHashMap<>();
        mutations.put("body { font-family: Georgia, serif; }", "font-family");
        mutations.put(".consigne { font: 1rem Georgia, serif; }", "font:");
        mutations.put(":root { --bs-body-font-family: Georgia, serif; }", "--bs-body-font-family");
        mutations.put("@font-face { font-family: Zz; src: local(Arial); }", "@font-face");
        mutations.put(":root { --bs-font-sans-serif: Georgia, serif; }", "--bs-font-sans-serif");
        Set<String> variables = variablesDeProduction();
        mutations.forEach((regle, jeton) -> {
            Map<String, String> feuilles = feuillesMaisonDeProduction();
            feuilles.replaceAll((nom, texte) -> texte + "\n" + regle + "\n");
            assertThatThrownBy(() -> verifierQueLaPileNEstPasChangee(feuilles, documents(RACINE_PRODUCTION),
                    variables))
                    .as(regle)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining(jeton)
                    .hasMessageContaining("DEC-0122");
        });
    }

    /** Clause : les proprietes de texte qui ne changent pas la pile, et une variable maison, restent vertes. */
    @Test
    void uneTailleUneGraisseEtUneVariableMaisonRestentVertes() {
        Map<String, String> feuilles = feuillesMaisonDeProduction();
        feuilles.replaceAll((nom, texte) -> texte
                + "\n.consigne { font-size: 1.25rem; font-weight: 700; --teinte-font: red; }\n");
        verifierQueLaPileNEstPasChangee(feuilles, documents(RACINE_PRODUCTION), variablesDeProduction());
    }

    /**
     * Critere 4 : un {@code th:style} reel d'un gabarit, precede d'une police ecrite dans un litteral.
     * Lue sur la valeur brute, la propriete commencerait par l'apostrophe et passerait : ce sont les
     * litteraux qui la rendent visible.
     */
    @Test
    void unePoliceDansUnStyleDeGabaritFaitEchouerLeGardeFou() {
        Map<String, Document> gabarits = documents(RACINE_PRODUCTION);
        Element porteur = gabarits.values().stream()
                .flatMap(document -> document.getElementsByAttribute("th:style").stream())
                .findFirst()
                .orElseThrow();
        porteur.attr("th:style", "'font-family: Georgia; ' + " + porteur.attr("th:style"));
        assertThatThrownBy(() -> verifierQueLaPileNEstPasChangee(feuillesMaisonDeProduction(), gabarits,
                variablesDeProduction()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("th:style : declare font-family: Georgia");
    }

    /** Critere 4 : une balise {@code <style>} ajoutee a un gabarit reel, qui declare une police, rougit. */
    @Test
    void unePoliceDansUneBaliseStyleDeGabaritFaitEchouerLeGardeFou() {
        Map<String, Document> gabarits = documents(RACINE_PRODUCTION);
        gabarits.values().iterator().next().body().appendElement("style").appendChild(
                new org.jsoup.nodes.DataNode(".x { font: 1rem serif; }"));
        assertThatThrownBy(() -> verifierQueLaPileNEstPasChangee(feuillesMaisonDeProduction(), gabarits,
                variablesDeProduction()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("<style>")
                .hasMessageContaining("font: 1rem serif");
    }

    @Test
    void sansVariableDePoliceDeriveeLaClauseEchoueAuLieuDePasserAVide() {
        assertThatThrownBy(() -> verifierQueLaPileNEstPasChangee(feuillesMaisonDeProduction(),
                documents(RACINE_PRODUCTION), Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("aucune variable de police derivee");
    }
}
