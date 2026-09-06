package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S1-1 : filet de regression recommande par theo-archi (avis de faisabilite du sprint 1) sur les
 * trois fautes precises nommees par nadia-prof (FICHE-bandit-manchot-additions-cp.md, motif 1).
 * Comparaison de sous-chaine litterale, jamais une expression reguliere (ADR-0008,
 * AssertionsWebTest). Ne remplace pas la relecture de fond du relecteur-pedagogique.
 *
 * <p>ADR-0013 : le perimetre balaye (quels gabarits sont controles) derive du repertoire de
 * gabarits, jamais d'une liste ecrite a la main - c'est cette derivation qui manquait a
 * {@code fragments/erreur.html} avant ce commit.
 *
 * <p><b>ADR-0039.</b> Le critere, lui, etait une liste de cinq fautes deja corrigees, donc vert par
 * defaut sur tout texte que ces cinq motifs ne traversent pas. Mesure en s11 : {@code nadia-prof} a
 * fait remplacer entierement la phrase que l'enfant lit avant d'abandonner sa partie sans qu'un seul
 * test bouge, et {@code lea-dev} a ferme ce cas-la par un test nomme - le cas, pas la classe de
 * defaut. Le critere general est desormais total sur son complement : <b>tout</b> texte qu'un gabarit
 * ecrit en clair pour l'enfant doit figurer dans l'inventaire ci-dessous. Un texte neuf, ou un mot
 * change dans un texte existant, est rouge tant que l'inventaire ne le porte pas - et le mettre a
 * jour est un diff sur une ligne de texte destinee a un enfant, c'est-a-dire sur ce que DEC-0002 et
 * CLAUDE.md confient a {@code nadia-prof}.
 *
 * <p>La liste des cinq fautes reste, sous son nom : elle lit la <b>source entiere</b> du gabarit,
 * commentaires et attributs compris, la ou l'inventaire ne juge que les noeuds de texte. C'est elle
 * qui garde encore le nom du produit d'une accentuation ecrite dans un commentaire.
 *
 * <p><b>S12-5 (theo-archi, avis de faisabilite du sprint 13).</b> "L'inventaire ne juge que les
 * noeuds de texte" etait exactement la phrase qui a laisse passer {@code aria-label="Menu
 * principal"} huit sprints : {@code nadia-prof} l'a trouve en lisant, aucun garde-fou ne le
 * voyait, alors que son propre critere - "tout texte que le serveur envoie et qu'un enfant peut
 * recevoir, affiche ou annonce" - le couvrait deja en intention. Le perimetre s'etend donc aux
 * attributs que l'API d'accessibilite HTML/ARIA expose comme texte a une aide technique
 * ({@link #ATTRIBUTS_QUI_ANNONCENT_DU_TEXTE}), et ces textes-la rejoignent le meme inventaire,
 * jugee par la meme egalite - un texte annonce et jamais arbitre rougit exactement comme un texte
 * affiche et jamais arbitre, parce que le critere de {@code nadia-prof} ne distingue pas les deux
 * canaux.
 *
 * <p>Ce que cette extension ne prouve pas : elle lit ce que le gabarit <b>ecrit</b>, jamais ce
 * qu'un lecteur d'ecran <b>annonce</b> reellement - aucun calcul de nom accessible n'est execute,
 * seule la presence litterale d'un attribut connu est lue. Une forme dynamique de ces attributs
 * (un {@code th:aria-label} qui calculerait la valeur au rendu) resterait hors de portee, comme
 * {@code th:text} l'est deja pour le contenu. La reserve F11 - l'ecoute reelle d'un lecteur
 * d'ecran - reste entiere et n'appartient qu'a {@code nadia-prof}.
 */
class TexteAccentueDesGabaritsTest {

    private static final Path GABARITS_DE_PRODUCTION = Path.of("src/main/resources/templates");

    /** ADR-0013 : le jeu fautif de ce garde-fou, un gabarit absent de la production. */
    private static final Path GABARITS_DU_CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/TexteAccentueDesGabaritsTest");

    /**
     * Les deux attributs par lesquels Thymeleaf remplace au rendu le texte ecrit en clair. Ce
     * texte-la n'atteint jamais l'enfant sous la forme litterale ecrite ici : celui qu'il lit vient
     * du modele. Ensemble ferme par Thymeleaf, jamais par nous.
     *
     * <p><b>Amendement du 2026-08-19 (S16-5, theo-archi, avis de faisabilite du sprint 16).</b> La
     * phrase qui justifiait ce saut affirmait que ce texte-la, venant du modele, "dont les
     * garde-fous vivent dans adapter-content" - c'est vrai pour le texte d'un paquet, c'est faux
     * pour les libelles de niveau ("CP", "CM1") exposes par {@code PartieUseCase.niveauxDuCatalogue()}
     * et rendus ici meme par la puce de niveau et la phrase de filtre : rien dans
     * {@code adapter-content} ne juge leur accentuation. Ce trou est ferme par
     * {@code TexteAccentueExposeParLePortEntrantTest} (module {@code bootstrap}), dont le perimetre
     * se derive des composantes du port entrant plutot que d'un gabarit - ADR-0015. Ce fichier-ci
     * continue de sauter ce texte a bon droit, pour la seule raison qui reste vraie : il ne peut pas
     * le lire, ce n'est pas un texte ecrit en clair dans ce gabarit.</p>
     */
    private static final List<String> POSE_SON_TEXTE_AU_RENDU = List.of("th:text", "th:utext");

    /** Les elements dont le contenu n'est pas du texte lu, mais du code servi au navigateur. */
    private static final List<String> SANS_TEXTE_POUR_L_ENFANT = List.of("script", "style");

    /**
     * Les attributs par lesquels HTML et ARIA exposent une chaine litterale directement au calcul
     * du nom et de la description accessibles (W3C Accessible Name and Description Computation).
     * Ensemble ferme par cette specification, jamais par nous - le meme raisonnement que ce
     * fichier tient deja pour {@link #POSE_SON_TEXTE_AU_RENDU}, "ensemble ferme par Thymeleaf".
     *
     * <p>"aria-labelledby" et "aria-describedby" en sont exclus par construction : ils portent
     * l'identifiant d'un autre element, jamais du texte, et les noeuds de texte de l'element qu'ils
     * designent sont deja dans le perimetre par eux-memes. Un attribut "value" sur un bouton n'entre
     * pas non plus : ce depot n'ecrit que des {@code <button>}, dont le nom accessible vient de leur
     * contenu, deja couvert par les noeuds de texte.
     */
    private static final List<String> ATTRIBUTS_QUI_ANNONCENT_DU_TEXTE = List.of(
            "alt", "title", "aria-label", "placeholder", "aria-placeholder",
            "aria-roledescription", "aria-description");

    /**
     * ADR-0039 : l'inventaire du texte que les gabarits de production ecrivent en clair pour
     * l'enfant. Ce n'est pas une liste de fautes, c'est le <b>complement</b> du critere, et c'est ce
     * qui le rend total : un texte hors de cet inventaire est rouge. Une entree n'est jamais un motif
     * ni une sous-chaine - c'est le texte exact d'un noeud, compare par egalite.
     *
     * <p>La reference en commentaire est ce que le depot trace de la formulation, pas un arbitrage que
     * ce test constaterait : ce test ne juge aucun texte, il refuse qu'un texte change en silence.
     * Deux entrees n'ont aucun arbitrage nomme dans le depot et le disent ; c'est une question pour
     * {@code nadia-prof}, remontee et non tranchee ici.
     */
    private static final List<String> TEXTES_LUS_PAR_L_ENFANT = List.of(
            "Recreo",                              // DEC-0010, S5-8 : sans accent, nom de produit
            "Les jeux",                            // ADR-0022 / S6-9, DEC-0027 : le seul mot de sortie
            "Il n'y a pas de jeu pour le moment.", // aucun arbitrage nomme dans le depot
            "Si tu pars, ta partie s'arrête.",     // arbitrage du 2026-08-14, CONF-3
            "Continuer la partie",                 // arbitrage du 2026-08-14, CONF-3
            "Bravo, c'est juste !",                // FICHE-bandit-manchot-additions-cp.md
            // S29, REV-1 et ADR-0083. "Non, c'est" sort de cet inventaire : c'etait la seconde
            // occurrence du defaut que nadia-prof a mesure sur l'ecran de revelation - la reponse
            // suivait, posee par le modele, et "Non, c'est ce n'est pas un etre vivant." se lisait
            // sur les douze memes items. Les deux textes ci-dessous sont des CANDIDATS, au sens
            // exact ou les quatre entrees de l'ecran de saisie le sont : nadia-prof demande qu'un
            // enfant qui echoue trois fois lise "une phrase francaise correcte qui lui designe la
            // reponse, quelle que soit la forme grammaticale de cette reponse" et se reserve de la
            // juger a l'ecran (PASSE-TRANSVERSE-s29, section 1.4, reverification chiffree a 0,25).
            // Elle ne les a pas encore lus ; ils entrent ici pour que le prochain qui les change le
            // fasse en clair. Ce garde-fou ne les valide pas. "Ce n'est pas ça." sort a son tour de
            // cet inventaire par le meme critere que "Non, c'est" ci-dessus : REV-2, nadia-prof,
            // reverification du 2026-09-06 (FICHE-bandit-manchot-additions-cp.md) - ce texte
            // dementait sur fragments/retour.html un geste toujours juste (une manche resolue par
            // revelation ne se ferme que par l'appui sur la bonne reponse), et n'y est plus ecrit.
            // Il reste litteralement present sur l'ecran de manche (constante MESSAGE_REVELATION de
            // PartieController), mais y est pose par le modele (${message}), jamais par un noeud de
            // texte du gabarit - donc absent de cet inventaire, comme "Non, c'est" l'etait deja.
            // Son remplacement sur fragments/retour.html, ecrit par nadia-prof elle-meme et
            // "applique tel quel", n'est pas un candidat : c'est un texte arbitre.
            "Tu n'as pas trouvé.",
            "La bonne réponse :",                  // candidat, idem ; la reponse suit, seule
            "Appuie sur la bonne réponse :",       // candidat, idem ; M5 : le geste reste demande
            "C'est fini !",                        // S10 : remplace le message refuse a S02
            "Oups ! Le jeu a un souci.",           // aucun arbitrage nomme dans le depot
            "Recommencer",                         // arbitrage du 2026-08-07 : seule chose pressable
            // S24, fragments/saisie.html. Les trois textes de cet ecran sont des CANDIDATS au sens
            // ou ines-design l'emploie (proposition-ines-ecran-de-saisie.md, section 2.6) : elle
            // les propose et ecrit que nadia-prof seule arbitre ce qu'un enfant de six ans lit,
            // apres avoir joue l'increment. Ils entrent dans cet inventaire pour que le prochain
            // qui les changerait le fasse en clair, jamais en silence - c'est tout ce que ce
            // garde-fou dit, il ne les valide pas. Le quatrieme texte de cet ecran, "Ce n'est pas
            // ca.", est pose par le modele et n'est donc pas un noeud de texte de gabarit.
            "Je réponds",                          // candidat d'ines-design, section 2.6
            "Continuer",                           // candidat d'ines-design, section 2.4
            "On écrit :",                          // SAI-9b, exemple de nadia-prof du 2026-08-24
            // S26, fragments/manche.html et fragments/saisie.html, .bloc-adulte (ADR-0075 section
            // 7, VOI-12). Destinataire : l'adulte, pas l'enfant - nadia-prof ecrit elle-meme
            // qu'elle le juge "au meme titre que ce que l'enfant lit" (ARBITRAGE-PREALABLE-s26-
            // la-voix-est-un-adulte.md, section 5.3). Candidat d'ines-design (proposition-ines-
            // s26-ecran-voix.md, section 1) : elle le propose, nadia-prof seule l'arbitre en
            // jouant un paquet reel - aucun paquet n'existe encore a ce commit.
            "Adulte, à voix haute :");

    /** ADR-0013 obligation 1 : le perimetre balaye vient du repertoire, jamais d'une liste. */
    private static List<Path> tousLesGabarits(Path racine) {
        try (Stream<Path> arborescence = Files.walk(racine)) {
            return arborescence
                    .filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(".html"))
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * ADR-0013 obligation 2 : chaque gabarit derive produit une assertion, sans exception - la
     * lecture du fichier ne depend d'aucune propriete facultative qui pourrait en exempter un.
     */
    private static void aucunGabaritNeReintroduitUneFauteConnue(Path racineDesGabarits) {
        List<Path> gabarits = tousLesGabarits(racineDesGabarits);
        assertThat(gabarits)
                .as("au moins un gabarit doit exister sous %s", racineDesGabarits)
                .isNotEmpty();
        for (Path gabarit : gabarits) {
            String source;
            try {
                source = Files.readString(gabarit);
            } catch (IOException echec) {
                throw new UncheckedIOException(echec);
            }
            assertThat(source)
                    .as("%s ne doit pas reintroduire une faute deja corrigee", gabarit)
                    .doesNotContain("ca.", "reponse etait", "la partie est terminee", "d'avoir joue",
                            "Récréo");
        }
    }

    @Test
    void aucunGabaritDeProductionNeReintroduitUneFauteDejaCorrigee() {
        aucunGabaritNeReintroduitUneFauteConnue(GABARITS_DE_PRODUCTION);
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Un texte lu par l'enfant, avec le gabarit qui l'ecrit, pour que le message le nomme. */
    private record TexteLu(Path gabarit, String texte) {
    }

    /**
     * ADR-0039, derivation totale du critere : chaque noeud de texte et chaque attribut annonce de
     * chaque gabarit produisent une assertion. Rien n'est retire par une propriete facultative de
     * l'element qui le porte - ni sa classe, ni sa balise, ni sa position. Ce qui est retire l'est
     * pour une raison qui dit que l'enfant ne recoit pas ce texte : Thymeleaf le remplace au rendu,
     * ou c'est du code servi au navigateur.
     *
     * <p>Les commentaires ne sont pas des noeuds de texte et n'entrent donc pas ici : c'est la liste
     * des cinq fautes qui les couvre, en lisant la source entiere.
     *
     * <p>S12-5 : un attribut de {@link #ATTRIBUTS_QUI_ANNONCENT_DU_TEXTE} n'est pas exclu par
     * {@link #remplaceAuRendu(Element)} - ce garde-fou-la ne concerne que le contenu qu'un {@code
     * th:text}/{@code th:utext} porte sur un ancetre, jamais les attributs propres de l'element, que
     * Thymeleaf laisse intacts.
     */
    private static List<TexteLu> textesLusParLEnfant(Path racineDesGabarits) {
        List<TexteLu> textes = new ArrayList<>();
        for (Path gabarit : tousLesGabarits(racineDesGabarits)) {
            Document document = Jsoup.parse(lire(gabarit), gabarit.toString());
            for (Element element : document.getAllElements()) {
                if (SANS_TEXTE_POUR_L_ENFANT.contains(element.normalName())) {
                    continue;
                }
                for (String attribut : ATTRIBUTS_QUI_ANNONCENT_DU_TEXTE) {
                    String valeur = element.attr(attribut).strip();
                    if (element.hasAttr(attribut) && valeur.chars().anyMatch(Character::isLetter)) {
                        textes.add(new TexteLu(gabarit, valeur));
                    }
                }
                if (remplaceAuRendu(element)) {
                    continue;
                }
                for (TextNode noeud : element.textNodes()) {
                    String texte = noeud.text().strip();
                    if (texte.chars().anyMatch(Character::isLetter)) {
                        textes.add(new TexteLu(gabarit, texte));
                    }
                }
            }
        }
        return textes;
    }

    /**
     * Le remplacement porte sur tout le sous-arbre de l'element qui declare l'attribut, pas seulement
     * sur ses propres noeuds : Thymeleaf ecrase le contenu entier. La remontee se fait a la main parce
     * que l'analyseur de selecteurs de jsoup ne comprend pas la forme echappee du deux-points
     * (mesure citee dans RessourcesTiercesTest).
     */
    private static boolean remplaceAuRendu(Element element) {
        for (Element ancetre = element; ancetre != null; ancetre = ancetre.parent()) {
            for (String attribut : POSE_SON_TEXTE_AU_RENDU) {
                if (ancetre.hasAttr(attribut)) {
                    return true;
                }
            }
        }
        return false;
    }

    static void verifierQueToutTexteLuEstInventorie(Path racineDesGabarits) {
        List<TexteLu> textes = textesLusParLEnfant(racineDesGabarits);
        assertThat(textes)
                .as("ADR-0039 : cette clause ne prouve rien si aucun gabarit de %s n'ecrit de texte en "
                        + "clair. Si tout le texte lu par l'enfant venait desormais du modele, c'est ce "
                        + "garde-fou qu'il faudrait deplacer vers adapter-content, avec son motif ecrit",
                        racineDesGabarits)
                .isNotEmpty();

        for (TexteLu texte : textes) {
            assertThat(TEXTES_LUS_PAR_L_ENFANT)
                    .as("ADR-0039 et DEC-0002 : %s ecrit \"%s\", un texte que l'inventaire de ce "
                            + "garde-fou ne porte pas. Un texte lu par un enfant qui apprend a lire ne "
                            + "change pas en silence : soit nadia-prof l'a arbitre et l'inventaire "
                            + "s'etend dans le meme commit, avec la reference de l'arbitrage, soit ce "
                            + "texte n'aurait pas du entrer",
                            texte.gabarit().getFileName(), texte.texte())
                    .contains(texte.texte());
        }
    }

    /**
     * ADR-0039, la clause qui ferme la classe de defaut de s11 : un texte neuf, ou un mot change dans
     * un texte existant, ne peut plus passer vert par le seul fait de n'etre aucune des cinq fautes
     * connues.
     */
    @Test
    void aucunGabaritDeProductionNEcritUnTexteHorsDeLInventaire() {
        verifierQueToutTexteLuEstInventorie(GABARITS_DE_PRODUCTION);
    }

    /**
     * ADR-0039, le versant qui empeche l'inventaire de pourrir, et la lecon du plafond d'ADR-0016 :
     * une entree que plus aucun gabarit n'ecrit laisse croire qu'un texte est garde alors qu'il a
     * disparu, et elle laisse une place libre pour le remettre sans diff visible.
     */
    @Test
    void chaqueTexteDeLInventaireEstEncoreEcritParUnGabarit() {
        assertThat(textesLusParLEnfant(GABARITS_DE_PRODUCTION).stream().map(TexteLu::texte).toList())
                .as("ADR-0039 : une entree de l'inventaire ne correspond a aucun texte ecrit par un "
                        + "gabarit de production. Si le texte a disparu, l'entree part dans le meme "
                        + "commit ; un inventaire plus large que ce qu'il garde n'est pas un inventaire")
                .containsAll(TEXTES_LUS_PAR_L_ENFANT);
    }

    /**
     * ADR-0013 obligation 3 : la morsure se prouve sur un objet neuf, absent de la production. Le
     * gabarit du jeu fautif ecrit une phrase que personne n'a inventoriee - et c'est la phrase, non la
     * faute qu'elle contient, qui la fait refuser ici.
     */
    @Test
    void unTexteNeufHorsDeLInventaireFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueToutTexteLuEstInventorie(GABARITS_DU_CONTRE_EXEMPLE))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne porte pas");
    }

    /**
     * S12-5, ADR-0013 obligation 3 : la morsure de la dimension neuve (le texte porte par un
     * attribut) se prouve isolement, sur un gabarit qui ne porte <b>aucun</b> noeud de texte - si ce
     * test rougissait, ce serait forcement l'attribut qui l'a fait rougir, jamais le reste du
     * garde-fou. {@code nav-aria-label-non-inventorie/} est un sous-repertoire propre, pour que les
     * deux autres preuves de morsure de cette classe restent ce qu'elles etaient.
     */
    @Test
    void unTexteNeufPorteParUnAttributHorsDeLInventaireFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueToutTexteLuEstInventorie(
                GABARITS_DU_CONTRE_EXEMPLE.resolve("nav-aria-label-non-inventorie")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne porte pas")
                .hasMessageContaining("Menu jeunesse");
    }

    /**
     * ADR-0013 obligation 3 : la preuve de morsure porte sur un objet neuf, absent de la
     * production - pas sur la mutation d'un gabarit existant. gabarit-neuf-fautif.html n'a jamais
     * ete livre : seul un perimetre derive du repertoire peut le voir.
     */
    @Test
    void leGardeFouMordSurUnGabaritAbsentDeLaProduction() {
        assertThatThrownBy(() -> aucunGabaritNeReintroduitUneFauteConnue(GABARITS_DU_CONTRE_EXEMPLE))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("gabarit-neuf-fautif.html");
    }

    /**
     * S02, reformulation de nadia-prof (FICHE-bandit-manchot-additions-cp.md, verdict REFUSE) :
     * le message inconditionnel de fin de partie ne doit pas revenir, quel que soit le score. Vise
     * un gabarit nomme pour une propriete qui lui est propre, ce n'est pas un balayage (ADR-0013).
     */
    @Test
    void leMessageDeFinDePartieRefuseParNadiaNeRevientPas() throws IOException {
        String retour = Files.readString(GABARITS_DE_PRODUCTION.resolve("fragments/retour.html"));

        assertThat(retour)
                .contains("C'est fini !")
                .doesNotContain("Bravo, tu as bien joué");
    }

    /**
     * S11, arbitrage prealable de nadia-prof (FICHE-bandit-manchot-additions-cp.md, 2026-08-14,
     * CONF-3) : le texte de l'ecran de confirmation avant abandon est integralement accentue et ne
     * pose plus de question, les deux boutons etant le choix. Vise un gabarit nomme pour une
     * propriete qui lui est propre, ce n'est pas un balayage (ADR-0013) - aucune faute connue de
     * ce texte-la ne pouvait figurer dans la liste de la section precedente puisqu'il est neuf.
     */
    @Test
    void leTexteDeConfirmationEstCeluiArbitreParNadiaProf() throws IOException {
        String confirmation =
                Files.readString(GABARITS_DE_PRODUCTION.resolve("fragments/confirmation.html"));
        Document fragment = Jsoup.parse(confirmation);

        assertThat(fragment.select(".question-confirmation").text())
                .isEqualTo("Si tu pars, ta partie s'arrête.");
    }

    /**
     * S5-8 (DEC-0010) : l'utilisatrice corrige le nom du produit, "Recreo" sans accent, et
     * remplace ainsi le point 7 de DEC-0007 execute par S3-9. Un nom propre de produit n'est pas
     * un mot de vocabulaire que l'equipe redige pour apprendre a l'enfant a lire ou a
     * orthographier ; l'exigence d'accentuation de DEC-0002 ne le concerne pas. Vise un gabarit
     * nomme pour une propriete qui lui est propre, ce n'est pas un balayage (ADR-0013).
     */
    @Test
    void leTitreDuProduitSecritRecreoSansAccent() throws IOException {
        String accueil = Files.readString(GABARITS_DE_PRODUCTION.resolve("accueil.html"));
        Document page = Jsoup.parse(accueil);

        assertThat(page.title()).isEqualTo("Recreo");
        assertThat(page.select("h1").text()).isEqualTo("Recreo");
    }
}
