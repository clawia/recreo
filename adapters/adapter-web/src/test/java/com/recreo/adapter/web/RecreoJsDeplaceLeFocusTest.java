package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S10 : le seul test de ce depot qui execute reellement {@code recreo.js}, plutot que d'inspecter son
 * texte ({@link FrontieresDuJavaScriptTest}) ou la position de sa balise ({@link
 * LeScriptDAgrementSExecuteApresLeCorpsTest}). nadia-prof a note en reserve qu'aucun test ne le
 * faisait ; c'est ce qui a laisse invisible, depuis que le fichier existe, une TypeError levee des la
 * lecture du script (M13 jamais operant, cf. javadoc de {@code LeScriptDAgrementSExecuteApresLeCorpsTest}).
 *
 * <p>Le moteur est Nashorn, un interpreteur JavaScript pur JVM (test seulement, aucun navigateur,
 * aucun build Node - CLAUDE.md, ADR-0008). Le DOM qu'il execute contre est un faux ecrit a la main
 * ({@link FakeElement}, {@link FakeDocument}), pas un vrai navigateur : il ne modelise que les quatre
 * selecteurs que {@code recreo.js} appelle reellement aujourd'hui ({@code [data-focus]}, {@code
 * .enonce}, {@code [data-focus-cible="true"]}, {@code .signal}) et leve une erreur nommee si un
 * cinquieme apparait un jour sans que ce faux DOM ne soit mis a jour.
 *
 * <p><b>Ce que ce test couvre</b> : que la fonction enregistree sur {@code htmx:afterSwap} lit
 * {@code data-focus} et deplace le focus vers l'element attendu par les clauses 2, 4 et 5 de M13
 * (bouton presse ou revele, enonce d'une manche neuve), avec repli sur {@code .signal} quand aucun
 * {@code data-focus} n'est present (ecran de fin de partie) - et que, sans {@code <body>}, l'appel
 * leve exactement l'erreur qui a rendu ces clauses inoperantes.
 *
 * <p><b>Ce que ce test ne couvre pas</b> : le vrai focus clavier d'un navigateur (ordre de
 * tabulation reel, lecteur d'ecran), le vrai evenement {@code htmx:afterSwap} tel qu'htmx le
 * declenche, et tout selecteur CSS que le faux DOM ne modelise pas. La clause 1 de M13 (Tabulation
 * puis Entree ne detruit jamais la partie) reste verifiee au clavier, dans un vrai navigateur, hors de
 * la CI.
 *
 * <p>S18, S17-C : {@code tabindex} n'est plus une propriete que ce script mute - le serveur le
 * declare desormais dans les gabarits ({@code fragments/manche.html}, {@code
 * fragments/confirmation.html}, {@code fragments/retour.html}) sur leurs seules cibles non
 * focalisables nativement. Ce que ce test verifie a la place : que le script n'ajoute {@code
 * tabindex} sur aucune cible qu'il focalise, jamais qu'il en pose une valeur donnee.
 */
class RecreoJsDeplaceLeFocusTest {

    private static final Path SOURCE = Path.of("src/main/resources/static/js/recreo.js");

    /** ADR-0013 : jeu fautif, un script qui n'enregistre aucun ecouteur sur htmx:afterSwap. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/RecreoJsDeplaceLeFocusTest/recreo-sans-ecouteur.js");

    private static String lireSource() throws IOException {
        return Files.readString(SOURCE);
    }

    private static ScriptEngine moteur() {
        ScriptEngine moteur = new ScriptEngineManager().getEngineByName("nashorn");
        assertThat(moteur).as("le moteur JavaScript Nashorn doit etre sur le classpath de test").isNotNull();
        return moteur;
    }

    @Test
    void sansCorpsLeScriptLeveUneErreurEtNEnregistreAucunEcouteur() {
        ScriptEngine moteur = moteur();
        FakeDocument document = new FakeDocument();
        document.body = null;
        moteur.put("document", document);

        assertThatThrownBy(() -> moteur.eval(lireSource()))
                .as("c'est exactement l'erreur qui a rendu M13 inoperant depuis que recreo.js existe : "
                        + "charge avant que <body> existe, document.body.addEventListener echoue")
                .isInstanceOf(ScriptException.class);
    }

    /**
     * ADR-0013 : ce garde-fou lit un artefact de production ({@code recreo.js}), il doit donc se
     * prouver mordant sur un jeu fautif. Un script qui n'enregistre aucun ecouteur laisse {@code
     * document.body.ecouteur} a {@code null} : c'est exactement ce que {@link
     * #surUneMancheNeuveLeFocusVaSurLEnonce()} et les deux tests voisins detecteraient si
     * {@code recreo.js} regressait de cette facon.
     */
    @Test
    void unScriptQuiNEnregistreAucunEcouteurEstRefuse() throws Exception {
        ScriptEngine moteur = moteur();
        FakeDocument document = new FakeDocument();
        document.body = new FakeElement();
        moteur.put("document", document);

        moteur.eval(Files.readString(CONTRE_EXEMPLE));

        assertThatThrownBy(() -> assertThat(document.body.ecouteur)
                .as("un script qui n'enregistre aucun ecouteur sur htmx:afterSwap doit faire "
                        + "echouer ce garde-fou")
                .isNotNull())
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void surUneMancheNeuveLeFocusVaSurLEnonce() throws Exception {
        FakeElement enonce = new FakeElement("enonce");
        FakeElement ecran = new FakeElement();
        ecran.setAttribute("data-focus", "enonce");
        ecran.enfants.add(enonce);
        FakeElement cible = new FakeElement();
        cible.enfants.add(ecran);

        declencherEchange(cible);

        assertThat(enonce.focalise).as("clause 4 de M13 : apres une bonne reponse, le focus va sur "
                + "l'enonce de la question neuve").isTrue();
        assertThat(enonce.getAttribute("tabindex"))
                .as("S18, S17-C : le script ne mute plus aucun attribut, tabindex=\"-1\" est desormais "
                        + "declare par le gabarit lui-meme, jamais devine ici")
                .isNull();
    }

    @Test
    void surUneMancheDesignantUnBoutonLeFocusVaSurCeBouton() throws Exception {
        FakeElement boutonDesigne = new FakeElement("proposition");
        boutonDesigne.setAttribute("data-focus-cible", "true");
        FakeElement autreBouton = new FakeElement("proposition");
        FakeElement ecran = new FakeElement();
        ecran.setAttribute("data-focus", "bouton");
        ecran.enfants.add(autreBouton);
        ecran.enfants.add(boutonDesigne);
        FakeElement cible = new FakeElement();
        cible.enfants.add(ecran);

        declencherEchange(cible);

        assertThat(boutonDesigne.focalise)
                .as("clauses 2 et 5 de M13 : la proposition pressee sur echec, ou celle qui porte la "
                        + "reponse revelee, recoit le focus")
                .isTrue();
        assertThat(autreBouton.focalise).isFalse();
        assertThat(boutonDesigne.getAttribute("tabindex"))
                .as("critere S17-C : une cible deja nativement focalisable (un bouton) ne recoit "
                        + "jamais tabindex=\"-1\" du script - c'est exactement le defaut mesure par "
                        + "nadia-prof, qui sortait ce bouton de l'ordre de tabulation")
                .isNull();
    }

    @Test
    void sansAttributDataFocusLeFocusReplieSurLeSignal() throws Exception {
        FakeElement signal = new FakeElement("signal");
        FakeElement cible = new FakeElement();
        cible.enfants.add(signal);

        declencherEchange(cible);

        assertThat(signal.focalise)
                .as("ecran de fin de partie (fragments/retour.html) : aucun data-focus, repli sur "
                        + ".signal, inchange depuis le 2026-08-07 (arbitrage nadia-prof)")
                .isTrue();
    }

    private void declencherEchange(FakeElement cible) throws Exception {
        ScriptEngine moteur = moteur();
        FakeDocument document = new FakeDocument();
        document.body = new FakeElement();
        moteur.put("document", document);

        moteur.eval(lireSource());

        FakeEvenement evenement = new FakeEvenement();
        evenement.detail = new FakeDetail();
        evenement.detail.target = cible;
        assertThat(document.body.ecouteur)
                .as("document.body.addEventListener('htmx:afterSwap', ...) doit avoir enregistre "
                        + "un ecouteur")
                .isNotNull();
        document.body.ecouteur.gerer(evenement);
    }

    /**
     * Interface a methode unique : Nashorn y convertit automatiquement la fonction JavaScript passee
     * a {@code addEventListener}, sans code d'adaptation supplementaire.
     */
    public interface EcouteurEvenement {
        void gerer(FakeEvenement evenement);
    }

    public static final class FakeEvenement {
        public FakeDetail detail;
    }

    public static final class FakeDetail {
        public FakeElement target;
    }

    public static final class FakeDocument {
        public FakeElement body;
    }

    /**
     * Faux element DOM minimal. {@link #querySelector(String)} ne modelise que les selecteurs que
     * {@code recreo.js} appelle a ce jour et leve {@link UnsupportedOperationException} pour tout
     * autre, plutot que de rendre silencieusement {@code null} comme un vrai navigateur : un
     * cinquieme selecteur doit mettre a jour ce faux DOM, jamais passer inapercu.
     */
    public static final class FakeElement {
        private final Map<String, String> attributs = new LinkedHashMap<>();
        private final List<String> classes = new ArrayList<>();
        final List<FakeElement> enfants = new ArrayList<>();
        boolean focalise;
        EcouteurEvenement ecouteur;

        public FakeElement() {
        }

        FakeElement(String classe) {
            classes.add(classe);
        }

        public String getAttribute(String nom) {
            return attributs.get(nom);
        }

        public void setAttribute(String nom, String valeur) {
            attributs.put(nom, valeur);
        }

        public void focus() {
            focalise = true;
        }

        public void addEventListener(String type, EcouteurEvenement gestionnaire) {
            this.ecouteur = gestionnaire;
        }

        public FakeElement querySelector(String selecteur) {
            for (FakeElement enfant : enfants) {
                if (correspond(enfant, selecteur)) {
                    return enfant;
                }
                FakeElement trouve = enfant.querySelector(selecteur);
                if (trouve != null) {
                    return trouve;
                }
            }
            return null;
        }

        private static boolean correspond(FakeElement element, String selecteur) {
            return switch (selecteur) {
                case "[data-focus]" -> element.attributs.containsKey("data-focus");
                case ".enonce" -> element.classes.contains("enonce");
                case "[data-focus-cible=\"true\"]" -> "true".equals(element.attributs.get("data-focus-cible"));
                case ".signal" -> element.classes.contains("signal");
                default -> throw new UnsupportedOperationException(
                        "selecteur non modelise par ce faux DOM : " + selecteur);
            };
        }
    }
}
