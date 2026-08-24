package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Question;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.recreo.adapter.content.LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.itemsTires;
import static com.recreo.adapter.content.LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.paquets;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Garde-fou ecrit par {@code yanis-qa} en s19, en reponse au second obstacle route par
 * {@code sofia-sm} : un heredoc bash a ecrit un artefact de six caracteres ASCII (antislash, la
 * lettre u, puis quatre chiffres hexadecimaux) la ou {@code marc-dev} voulait un espace insecable
 * (U+00A0), sans qu'aucune erreur ne le signale. Le defaut a ete trouve par comparaison octet par
 * octet contre un paquet existant, jamais par une porte technique de ce depot - et c'est exactement
 * le trou que ce fichier ferme.
 *
 * <p><b>Avertissement pour quiconque relit ou modifie ce fichier</b> : je n'ecris cet artefact nulle
 * part ici sous sa forme litterale, y compris dans ce commentaire, et ce n'est pas une precaution
 * stylistique - javac traite les echappements Unicode (antislash suivi de la lettre u puis de
 * quatre chiffres hexadecimaux) comme une phase de traduction du fichier source qui precede la
 * lecture des commentaires et des chaines. L'ecrire tel quel le convertirait avant que je ne le
 * controle, ou ferait echouer la compilation si la sequence est malformee - les deux memes
 * disparitions dont cette classe existe pour prouver l'absence dans le contenu servi.
 * {@link #ARTEFACT_SIX_CARACTERES} le construit par concatenation de deux litteraux courts pour
 * cette raison precise ; le jeu fautif de
 * {@code src/test/resources/contreexemple/AucunTexteServiNePorteUnLookAlikeDEspaceInsecableTest}
 * le porte dans un fichier YAML, hors de portee de javac, ou aucune telle traduction n'a lieu.</p>
 *
 * <p><b>Pourquoi les tests par paquet qui existent deja</b> ({@code PaquetCm1AntonymesTest},
 * {@code PaquetCpNatureMotTest}) <b>ne suffisent pas</b> : chacun encode sa propre attente en dur
 * dans un litteral Java ({@code Pattern.compile("« ([^»]+?) »")}), ecrit
 * par la meme personne qui a ecrit le paquet. Si la meme confusion frappe les deux gestes - le YAML
 * et le test qui le verifie -, l'un et l'autre portent le meme artefact et le test reste vert. Ce
 * fichier ne recopie aucun litteral par paquet : il derive son perimetre du repertoire de contenu
 * (ADR-0013 obligation 1, meme geste que {@link LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest}),
 * et il porte a la fois un jeu fautif commite (obligation 3) et une mise a l'epreuve directe sur une
 * chaine reellement servie, mutee en memoire plutot que sur une fixture ecrite de ma main.</p>
 *
 * <p><b>Deux proprietes, toutes deux generiques a toute matiere et tout paquet</b> :</p>
 * <ul>
 *   <li>aucun champ de texte servi ne porte l'un des artefacts connus d'une confusion d'espace
 *       insecable - la sequence de six caracteres ASCII decrite ci-dessus, ou l'entite HTML
 *       {@code &nbsp;}, ni l'une ni l'autre jamais rendue par ce domaine (ADR-0008) ;</li>
 *   <li>tout guillemet francais ouvrant ou fermant qu'un champ de texte servi contient est
 *       immediatement flanque d'un espace insecable, jamais d'un espace ordinaire - convention deja
 *       en vigueur (SYN-6/SYN-9, ANT-5, NAT-5), verifiee ici une fois pour tout paquet present et a
 *       venir plutot qu'une fois par paquet qui pense a l'ecrire.</li>
 * </ul>
 *
 * <p><b>Ce qu'elle ne fait pas</b> : elle ne verifie aucune autre convention typographique
 * (espace avant deux-points, point-virgule, point d'exclamation ou d'interrogation), qui resterait
 * a chiffrer sur du materiau reel avant d'etre gardee - je ne l'invente pas ici.</p>
 */
class AucunTexteServiNePorteUnLookAlikeDEspaceInsecableTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/AucunTexteServiNePorteUnLookAlikeDEspaceInsecableTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/AucunTexteServiNePorteUnLookAlikeDEspaceInsecableTest";

    /**
     * Six caracteres ASCII : antislash, u, 0, 0, A, 0. Construit par concatenation de deux
     * litteraux courts - voir l'avertissement de la Javadoc de la classe - pour qu'aucune sequence
     * candidate a la traduction Unicode de javac n'apparaisse jamais telle quelle dans ce fichier.
     */
    private static final String ARTEFACT_SIX_CARACTERES = "\\" + "u00A0";

    private static final List<String> ARTEFACTS_CONNUS = List.of(ARTEFACT_SIX_CARACTERES, "&nbsp;");

    private static final char GUILLEMET_OUVRANT = '«';
    private static final char GUILLEMET_FERMANT = '»';
    private static final char ESPACE_INSECABLE = ' ';

    private static List<String> champsDeTexte(Question item) {
        List<String> champs = new ArrayList<>();
        champs.add(item.enonce());
        champs.add(item.bonneReponse());
        champs.addAll(item.distracteurs());
        item.indice().ifPresent(champs::add);
        item.explication().ifPresent(champs::add);
        return champs;
    }

    /**
     * Les defauts d'un champ de texte servi : chaque artefact connu present, puis chaque guillemet
     * mal flanque. Fonction pure, mise a l'epreuve directement par les trois tests de mutation en
     * memoire ci-dessous - c'est elle, et non le passage par un paquet charge, qui porte la
     * propriete.
     */
    static List<String> defautsDe(String origine, String champ) {
        List<String> defauts = new ArrayList<>();
        for (String artefact : ARTEFACTS_CONNUS) {
            if (champ.contains(artefact)) {
                defauts.add(origine + " : le champ \"" + champ + "\" porte l'artefact litteral \""
                        + artefact + "\" a la place d'un espace insecable (U+00A0)");
            }
        }
        for (int i = 0; i < champ.length(); i++) {
            char c = champ.charAt(i);
            if (c == GUILLEMET_OUVRANT && (i + 1 >= champ.length() || champ.charAt(i + 1) != ESPACE_INSECABLE)) {
                defauts.add(origine + " : le champ \"" + champ + "\" ouvre un guillemet francais sans"
                        + " espace insecable a sa suite");
            }
            if (c == GUILLEMET_FERMANT && (i == 0 || champ.charAt(i - 1) != ESPACE_INSECABLE)) {
                defauts.add(origine + " : le champ \"" + champ + "\" ferme un guillemet francais sans"
                        + " espace insecable qui le precede");
            }
        }
        return defauts;
    }

    /**
     * ADR-0013 obligation 1 : le perimetre vient du repertoire de contenu, jamais d'une liste
     * ecrite ici. Leve une {@link AssertionError} si un champ servi porte un defaut - c'est cette
     * forme, et non un simple retour de liste, qui permet a la mise a l'epreuve sur jeu fautif de
     * s'exprimer par {@code assertThatThrownBy}, au meme titre que les autres garde-fous de ce
     * paquet qui balaient un repertoire de contenu.
     */
    static void verifierQuAucunLookAlikeDEspaceInsecable(Path racine, String racineClasspath) {
        List<String> paquets = paquets(racine);
        assertThat(paquets)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun paquet sous %s", racine)
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        List<String> champsAvecGuillemets = new ArrayList<>();
        for (String paquet : paquets) {
            ContentSource contenu = new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet);
            for (Question item : itemsTires(contenu)) {
                for (String champ : champsDeTexte(item)) {
                    if (champ.indexOf(GUILLEMET_OUVRANT) >= 0 || champ.indexOf(GUILLEMET_FERMANT) >= 0) {
                        champsAvecGuillemets.add(paquet + " / " + item.id().value());
                    }
                    defauts.addAll(defautsDe(paquet + " / " + item.id().value(), champ));
                }
            }
        }

        assertThat(champsAvecGuillemets)
                .as("ce garde-fou ne prouve rien sur les guillemets francais si aucun champ servi"
                        + " sous %s n'en contient", racine)
                .isNotEmpty();
        assertThat(defauts)
                .as("aucun champ de texte servi a l'enfant ne doit porter un artefact d'espace"
                        + " insecable ni un guillemet francais mal flanque")
                .isEmpty();
    }

    @Test
    void aucunChampServiDeProductionNePorteUnLookAlikeDEspaceInsecable() {
        verifierQuAucunLookAlikeDEspaceInsecable(CONTENU_DE_PRODUCTION, "");
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un paquet que personne n'a jamais livre, dont
     * l'enonce du mot cible porte l'artefact litteral de six caracteres ASCII a la place d'un
     * espace insecable - le geste exact que {@code marc-dev} a corrige a la main, faute de porte
     * technique pour le lui dire.
     */
    @Test
    void unPaquetNeufDontUnChampPorteLArtefactLitteralFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQuAucunLookAlikeDEspaceInsecable(
                CONTRE_EXEMPLE, CONTRE_EXEMPLE_CLASSPATH))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-neuf-artefact-espace-insecable")
                .hasMessageContaining("a la place d'un espace insecable");
    }

    /** Le meme paquet neuf, une fois son espace insecable corrige, ne doit plus rien reprocher. */
    @Test
    void leMemePaquetUneFoisCorrigePasseLeGardeFou() {
        verifierQuAucunLookAlikeDEspaceInsecable(
                CONTRE_EXEMPLE.resolve("corrige"), CONTRE_EXEMPLE_CLASSPATH + "/corrige");
    }

    private static String unEnonceReelAvecEspaceInsecable(String paquet) {
        ContentSource contenu = new YamlContentPackLoader("").chargerPaquet(paquet);
        return itemsTires(contenu).stream()
                .map(Question::enonce)
                .filter(enonce -> enonce.indexOf(ESPACE_INSECABLE) >= 0)
                .findFirst()
                .orElseThrow(() -> new AssertionError(
                        paquet + " ne sert plus aucun enonce avec un espace insecable : cette mise"
                                + " a l'epreuve ne mute plus rien de reel"));
    }

    /**
     * Seconde mise a l'epreuve, en plus du jeu fautif ci-dessus : sur une chaine reellement servie
     * par {@code cm1-antonymes} et non sur une fixture ecrite de ma main. {@code String.replace}
     * est litteral sur ses deux arguments, contrairement a {@code replaceFirst} : aucune
     * reinterpretation d'antislash ne s'ajoute a celle, deja unique, de
     * {@link #ARTEFACT_SIX_CARACTERES}.
     */
    @Test
    void uneChaineReelleDontLEspaceInsecableEstRemplaceParLArtefactEstUnDefaut() {
        String enonceReel = unEnonceReelAvecEspaceInsecable("cm1-antonymes");

        String mute = enonceReel.replace(String.valueOf(ESPACE_INSECABLE), ARTEFACT_SIX_CARACTERES);
        assertThat(mute)
                .as("la mutation doit changer la chaine, sans quoi elle ne prouve rien")
                .isNotEqualTo(enonceReel);

        assertThat(defautsDe("cm1-antonymes / mutation", mute))
                .as("un espace insecable reel remplace par l'artefact litteral doit etre un defaut")
                .isNotEmpty();
    }

    /**
     * Meme mise a l'epreuve, sur le second artefact connu : l'entite HTML {@code &nbsp;}, jamais
     * rendue par ce domaine (ADR-0008 : le serveur calcule un verdict, il n'ecrit pas de HTML dans
     * une chaine de contenu).
     */
    @Test
    void uneChaineReelleDontLEspaceInsecableEstRemplaceParLEntiteHtmlEstUnDefaut() {
        String enonceReel = unEnonceReelAvecEspaceInsecable("cm1-antonymes");

        String mute = enonceReel.replace(String.valueOf(ESPACE_INSECABLE), "&nbsp;");
        assertThat(mute)
                .as("la mutation doit changer la chaine, sans quoi elle ne prouve rien")
                .isNotEqualTo(enonceReel);

        assertThat(defautsDe("cm1-antonymes / mutation", mute))
                .as("un espace insecable reel remplace par l'entite HTML doit etre un defaut")
                .isNotEmpty();
    }

    /**
     * Mise a l'epreuve du second critere (guillemets mal flanques), sur la meme chaine reelle,
     * mutee cette fois par le remplacement d'un espace insecable par un espace ordinaire - le
     * geste qu'un editeur de texte qui "normalise les espaces" ferait sans le vouloir.
     */
    @Test
    void uneChaineReelleDontLEspaceInsecableEstRemplaceParUnEspaceOrdinaireEstUnDefaut() {
        String enonceReel = unEnonceReelAvecEspaceInsecable("cm1-antonymes");

        String mute = enonceReel.replace(String.valueOf(ESPACE_INSECABLE), " ");
        assertThat(mute)
                .as("la mutation doit changer la chaine, sans quoi elle ne prouve rien")
                .isNotEqualTo(enonceReel);

        assertThat(defautsDe("cm1-antonymes / mutation", mute))
                .as("un guillemet flanque d'un espace ordinaire doit etre un defaut")
                .isNotEmpty();
    }

    /** Le meme champ reel, non mute, ne doit rien reprocher : le temoin des trois mutations. */
    @Test
    void laChaineReelleNonMuteeNeReprocheRien() {
        String enonceReel = unEnonceReelAvecEspaceInsecable("cm1-antonymes");

        assertThat(defautsDe("cm1-antonymes / temoin", enonceReel)).isEmpty();
    }
}
