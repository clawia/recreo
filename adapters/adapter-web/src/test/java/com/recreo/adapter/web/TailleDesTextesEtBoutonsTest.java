package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static com.recreo.adapter.web.LHabillageNeRogneJamaisUnElementPressableTest.PRESSABLES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S2-1 : aucun texte lu par l'enfant ni aucun bouton actionnable ne doit rester sous la taille de
 * .consigne (1,25rem), reserves 6 et 7 de la fiche bandit-manchot-additions-cp. Lecture directe du
 * CSS par decoupage de blocs { }, jamais par expression reguliere (ADR-0008, AssertionsWebTest).
 *
 * <p>Correction demandee par nadia-prof sur le refus de S2-1
 * (squad/conformite/FICHE-bandit-manchot-additions-cp.md) : la liste des boutons actionnables
 * n'est pas ecrite ici, elle est relevee dans les gabarits eux-memes (tout &lt;button&gt; et tout
 * &lt;a href&gt;), pour qu'un bouton nouveau ou oublie soit attrape sans qu'on pense a l'ajouter.
 * Son deuxieme defaut, meme forme d'erreur : une derivation qui passait par les classes CSS
 * laissait sortir un bouton sans classe. La regle part des elements eux-memes et, pour chacun,
 * verifie les regles qui s'appliquent reellement a lui (ses classes si elles existent, la balise
 * nue sinon).
 *
 * <p>ADR-0014, entree d'une bibliotheque CSS tierce. Trois choses changent, et une seule question
 * les commande : ou ce garde-fou prend-il son perimetre, maintenant que tout le CSS servi n'est
 * plus ecrit ici ?
 * <ul>
 *   <li>les gabarits balayes viennent du repertoire de gabarits et non plus de quatre chemins
 *       ecrits a la main (ADR-0013, obligation 1) ;</li>
 *   <li>les regles maison viennent de toutes les feuilles de static/ hors static/vendor/, et non
 *       plus d'un fichier nomme ;</li>
 *   <li>le plancher exige par nadia-prof doit etre porte par une regle <b>maison</b>. Une
 *       bibliotheque tierce peut porter le rendu de base ; elle ne porte jamais une garantie
 *       pedagogique, parce que sa prochaine version pourrait la retirer sans que ce depot le
 *       sache. Un bouton qui n'a qu'une classe tierce echoue.</li>
 * </ul>
 * Le CSS tiers n'est jamais juge pour lui-meme. Il n'est lu que sur un point, parce que c'est le
 * seul par lequel il peut defaire un plancher declare ici : une declaration !important, qui gagne
 * quel que soit l'ordre de chargement.
 */
class TailleDesTextesEtBoutonsTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");
    private static final Path CONTRE_EXEMPLES =
            Path.of("src/test/resources/contreexemple/TailleDesTextesEtBoutonsTest");

    private static final String STATIQUE = "static";
    private static final String TIERS = "vendor";

    /** Les deux attributs Thymeleaf par lesquels une classe arrive au rendu sans etre dans class. */
    private static final String CLASSE_CALCULEE = "th:class";
    private static final String CLASSE_AJOUTEE = "th:classappend";

    private static final double PLANCHER_DE_LISIBILITE_REM = 1.25;
    /**
     * Visibilite de paquet plutot que privee (ADR-0059) depuis ADR-0080, garde-fou 3
     * ({@code AucuneZoneDesignableNeDescendSousLaCibleTactileTest}) : le meme plancher de cible
     * tactile, epingle ici une seule fois, gouverne aussi bien un pressable ordinaire qu'une zone
     * designable sur une image.
     */
    static final double CIBLE_TACTILE_REM = 2.75;

    /**
     * S8-5 : plancher accepte par nadia-prof pour .proposition, sous le seul repli a quatre
     * colonnes (arbitrage prealable du 2026-08-11, FICHE-bandit-manchot-conjugaison-present-cm1.md,
     * section C, condition 2 : "1,875rem et rien en dessous [...] un plancher, pas une pente").
     * Ce plancher est distinct de celui que verifie leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul
     * (2rem, sur le selecteur litteral .proposition) : il ne le remplace pas, il ne s'applique
     * que sous SELECTEUR_REPLI_QUATRE_COLONNES.
     */
    private static final double PLANCHER_REPLI_QUATRE_COLONNES_REM = 1.875;

    /** ADR-0026, S7-1/S7-2 : le selecteur deja existant du repli en deux rangees a quatre colonnes. */
    private static final String SELECTEUR_REPLI_QUATRE_COLONNES =
            ".propositions:has(.proposition:nth-child(4)) .proposition";

    /**
     * S8-5, dernier mot de theo-archi sur la condition 7 de nadia-prof : les seules regles maison qui
     * ont le droit de fixer la taille d'une proposition, et la valeur exacte de chacune. Ce n'est pas
     * une liste d'exemptions mais une enumeration complete - toute autre regle qui fixe cette taille
     * fait echouer {@link #verifierQueLesTaillesDePropositionSontToutesNommees}, et chacune de ces
     * deux-ci doit exister vraiment. C'est ce qui rend vraie la phrase "2rem partout, sauf sous le
     * repli a quatre colonnes ou c'est 1,875rem" : sans elle, les deux planchers sont deux valeurs
     * epinglees au milieu d'un nombre inconnu d'autres.
     */
    private static final Map<String, Double> TAILLES_NOMMEES_DES_PROPOSITIONS = Map.of(
            ".proposition", 2.0,
            SELECTEUR_REPLI_QUATRE_COLONNES, PLANCHER_REPLI_QUATRE_COLONNES_REM);

    /**
     * Les pseudo-classes dont l'argument decrit un autre element que le sujet - une condition sur un
     * descendant, une exclusion. {@code :is()} et {@code :where()} n'y sont pas : leur argument
     * <b>est</b> le sujet, et {@code .quelque-chose:is(.proposition)} habille donc bien une
     * proposition. Sans cette distinction, {@code .propositions:has(.proposition:nth-child(4))}
     * passerait pour une regle de taille des propositions alors qu'elle habille leur conteneur - la
     * meme forme d'erreur que la sous-chaine {@code font-size:} trouvee dans une variable Bootstrap
     * (ADR-0015).
     */
    private static final List<String> PSEUDOS_QUI_NE_DECRIVENT_PAS_LE_SUJET = List.of(":has(", ":not(");

    /**
     * La classe qui porte une proposition. Elle est toujours cherchee comme <b>jeton</b>, jamais comme
     * sous-chaine : {@code .propositions} contient {@code .proposition} et n'en est pas un. Pas
     * d'expression reguliere ici - ADR-0008 les interdit dans ce module, et le meme fichier resout
     * deja la meme difficulte a la main pour {@code font-size:} trouve dans une variable Bootstrap
     * (ADR-0015, {@link #debutDeDeclaration}).
     */
    private static final String CLASSE_PROPOSITION = ".proposition";

    /**
     * S3-5, reserve 2 de nadia-prof : l'ecart minimal entre deux boutons de reponse. Un plancher gagne
     * par un refus pedagogique, jamais une variable de mise en page - aucune contrainte de largeur ne
     * l'abaisse (ADR-0026).
     */
    private static final double ECART_MINIMAL_REM = 1.0;

    /**
     * Les proprietes dont depend une garantie donnee par nadia-prof sur un bouton. padding entre par
     * ADR-0017 : c'est la composante de boite par laquelle la hierarchie de saillance s'est inversee
     * au lot Bootstrap, et une classe tierce en !important la defairait de la meme facon que les deux
     * autres.
     */
    private static final List<String> PROPRIETES_GARDEES =
            List.of("font-size", "min-height", "padding");

    static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Un commentaire CSS peut contenir des virgules ; il ne doit jamais entrer dans un selecteur. */
    static String sansCommentaires(String source) {
        StringBuilder resultat = new StringBuilder();
        int position = 0;
        while (position < source.length()) {
            int debut = source.indexOf("/*", position);
            if (debut < 0) {
                resultat.append(source, position, source.length());
                break;
            }
            resultat.append(source, position, debut);
            int fin = source.indexOf("*/", debut);
            position = fin < 0 ? source.length() : fin + 2;
        }
        return resultat.toString();
    }

    private static List<Path> feuilles(Path racine, boolean tierces) {
        Path statique = racine.resolve(STATIQUE);
        if (!Files.isDirectory(statique)) {
            return List.of();
        }
        Path repertoireTiers = statique.resolve(TIERS);
        try (Stream<Path> arborescence = Files.walk(statique)) {
            return arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(".css"))
                    .filter(fichier -> fichier.startsWith(repertoireTiers) == tierces)
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static Map<String, String> blocsParSelecteur(List<Path> feuilles) {
        Map<String, String> blocs = new HashMap<>();
        for (Path feuille : feuilles) {
            String source = sansCommentaires(lire(feuille));
            for (String bloc : source.split("\\}")) {
                int accolade = bloc.indexOf('{');
                if (accolade < 0) {
                    continue;
                }
                String selecteurs = bloc.substring(0, accolade);
                String corps = bloc.substring(accolade + 1);
                for (String selecteur : LAnimationRetardeMaisNeDetermineRienTest.selecteurs(selecteurs)) {
                    blocs.merge(selecteur, corps, (ancien, nouveau) -> ancien + ";" + nouveau);
                }
            }
        }
        return blocs;
    }

    static Map<String, String> blocsMaison(Path racine) {
        return blocsParSelecteur(feuilles(racine, false));
    }

    /**
     * Les feuilles que ce depot ecrit : tout {@code static/} hors {@code static/vendor/} (ADR-0014).
     * Exposee parce qu'ADR-0026 lit les memes feuilles que celle-ci, avec leur structure de regles-@
     * plutot qu'aplaties en blocs par selecteur - deux lectures du meme perimetre, qui doit donc
     * n'etre derive qu'une fois.
     */
    static List<Path> feuillesMaison(Path racine) {
        return feuilles(racine, false);
    }

    /**
     * Les feuilles vendorisees : {@code static/vendor/} (ADR-0014). Exposee pour la meme raison que
     * {@link #feuillesMaison} - {@code LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest} lit ces
     * feuilles avec leur structure de regles-@ plutot qu'aplaties par selecteur, parce que la seule
     * declaration de {@code .container} qui borne une largeur vit sous {@code @media}. Le perimetre
     * ne doit etre derive qu'une fois.
     */
    static List<Path> feuillesTierces(Path racine) {
        return feuilles(racine, true);
    }

    static Map<String, String> blocsTiers(Path racine) {
        return blocsParSelecteur(feuilles(racine, true));
    }

    private static double tailleEnRem(Map<String, String> blocs, String selecteur) {
        return proprieteEnRem(blocs, selecteur, "font-size");
    }

    /**
     * L'index de la declaration de cette propriete dans un corps de bloc, ou -1. La recherche exige
     * un **debut de declaration** - debut du corps, ou apres un ';' - et non la premiere occurrence
     * litterale du nom.
     *
     * <p>Defaut releve par yanis-qa en s03 sur le fichier vendorise reel, corrige par ADR-0015 :
     * Bootstrap 5 nomme ses variables {@code --bs-<composant>-<propriete>}, si bien que
     * {@code --bs-btn-font-size:1rem} contient {@code font-size:} et etait trouve avant la vraie
     * regle {@code font-size:var(--bs-btn-font-size)} du meme bloc. Le garde-fou lisait alors la
     * valeur de la variable et ne voyait jamais la declaration reelle, !important compris. Ce n'est
     * pas un accident de fixture : c'est la convention de nommage de tous les composants Bootstrap.
     */
    static int debutDeDeclaration(String corps, String propriete) {
        String etiquette = propriete + ":";
        int position = corps.indexOf(etiquette);
        while (position >= 0) {
            if (estDebutDeDeclaration(corps, position)) {
                return position;
            }
            position = corps.indexOf(etiquette, position + 1);
        }
        return -1;
    }

    /**
     * L'index de la declaration <b>qui gagne la cascade</b> pour cette propriete dans ce corps, ou
     * -1. C'est la <b>derniere</b>, pas la premiere : a specificite egale et a origine egale, la
     * derniere declaration l'emporte, et {@link #blocsParSelecteur} fusionne les corps successifs
     * d'un meme selecteur <b>dans l'ordre du document</b> - fichiers tries, blocs dans l'ordre - si
     * bien que la derniere du corps fusionne est celle que le navigateur applique.
     *
     * <p><b>Defaut releve le 2026-08-27 (s21), et il n'est pas theorique.</b> Sur la feuille de la
     * branche {@code s21-marc}, {@code .proposition} recoit {@code padding: 0.75rem 1.25rem} par le
     * bloc partage des cinq boutons pleins, puis {@code padding: 0.5rem 1.25rem} par une regle qui
     * lui est propre et qui vient apres. {@link #debutDeDeclaration} rendait le {@code 0.75rem} -
     * une valeur que rien ne rend a l'ecran - et
     * {@code LActionNeDeclarePasMoinsQueLaSortieTest} restait <b>vert 11 sur 11 sans avoir lu la
     * declaration qu'il devait juger</b>, alors que le {@code 0.5rem} reel passe sous les
     * {@code 0.6rem} de {@code .lien-accueil} et viole ADR-0017. Ce test ne se trompait pas, il ne
     * regardait pas - la meme famille de defaut que le balayage qui se declarait termine en ayant
     * perdu 8 jeux sur 17 (s21, {@code S21-1}).
     *
     * <p><b>Ce que cette methode ne resout pas, et qu'il faut lire avant de s'y fier.</b> Elle
     * suppose l'egalite de specificite et l'absence de {@code !important} : une declaration
     * {@code !important} anterieure bat une declaration ordinaire posterieure, et le rang dans le
     * corps ne le dit pas. C'est pourquoi {@link #valeurDeclaree} refuse de rendre une valeur des
     * qu'un {@code !important} accompagne une propriete declaree plusieurs fois, plutot que de
     * rendre un chiffre qui aurait l'air juste. Elle ne resout pas non plus la question des
     * regles-@ : {@link #blocsParSelecteur} range le contenu d'un {@code @media} sous une cle qui
     * commence par {@code @media}, jamais sous le selecteur interieur, si bien qu'une surcharge
     * sous media n'entre pas dans ce corps-ci et n'est pas vue - limite ancienne, nommee ici plutot
     * que corrigee en passant.
     */
    static int derniereDeclaration(String corps, String propriete) {
        List<Integer> positions = declarationsDe(corps, propriete);
        return positions.isEmpty() ? -1 : positions.get(positions.size() - 1);
    }

    private static boolean estDebutDeDeclaration(String corps, int position) {
        for (int avant = position - 1; avant >= 0; avant--) {
            char caractere = corps.charAt(avant);
            if (Character.isWhitespace(caractere)) {
                continue;
            }
            return caractere == ';' || caractere == '{';
        }
        return true;
    }

    private static double proprieteEnRem(Map<String, String> blocs, String selecteur, String propriete) {
        String corps = blocs.get(selecteur);
        assertThat(corps).as("le selecteur %s doit exister dans le CSS maison", selecteur).isNotNull();
        // s21 : la declaration qui gagne la cascade, jamais la premiere rencontree.
        int position = derniereDeclaration(corps, propriete);
        assertThat(position).as("%s doit declarer explicitement %s", selecteur, propriete).isNotNegative();
        String valeur = valeurDeclaree(corps, propriete);
        assertThat(valeur).as("%s : %s doit etre exprime en rem", selecteur, propriete).endsWith("rem");
        return Double.parseDouble(valeur.substring(0, valeur.length() - "rem".length()));
    }

    private static Optional<Double> proprieteEnRemSiExiste(
            Map<String, String> blocs, String selecteur, String propriete) {
        String corps = blocs.get(selecteur);
        return corps == null ? Optional.empty() : proprieteEnRemSiExiste(corps, propriete);
    }

    /**
     * La valeur litterale en rem que ce corps de regle declare, ou vide. Vide couvre trois cas que
     * l'appelant ne distingue pas : la propriete est absente, ou sa valeur n'est pas exprimee en rem
     * - une variable et un {@code calc()} n'en sont pas, meme s'ils rendent les memes pixels.
     */
    static Optional<Double> proprieteEnRemSiExiste(String corps, String propriete) {
        // s21 : la declaration qui gagne la cascade, jamais la premiere rencontree.
        int position = derniereDeclaration(corps, propriete);
        if (position < 0) {
            return Optional.empty();
        }
        String valeur = valeurDeclaree(corps, propriete);
        if (!valeur.endsWith("rem")) {
            return Optional.empty();
        }
        return Optional.of(Double.parseDouble(valeur.substring(0, valeur.length() - "rem".length())));
    }

    /**
     * La valeur litterale que ce corps declare pour cette propriete : <b>celle qui gagne la
     * cascade</b>, jamais une autre. Il n'y a plus de parametre de position, et c'est le correctif
     * du 2026-08-27 (s21, second passage).
     *
     * <p><b>Pourquoi la position a disparu de la signature.</b> Elle etait une information
     * redondante - l'appelant passait deja le corps et la propriete - et elle etait la seule piece
     * qu'un appelant pouvait se tromper a calculer. Le premier passage de ce correctif avait rendu
     * ce lecteur strict : il refusait une position ecrasee au lieu de rendre un chiffre plausible.
     * Le refus a fonctionne - il a rougi -, mais il a rougi <b>en CI sur l'arbre fusionne</b>,
     * parce qu'un troisieme appelant, {@code LeConteneurTierGardeSonRembourrageLateralTest.lateralDeclare},
     * n'avait pas ete converti et que le materiau qui le revele - {@code .proposition} portant deux
     * declarations de rembourrage - n'existait sur aucune des deux branches prises separement. Un
     * refus a l'execution ne protege que sur le materiau qu'on a sous la main ; une signature qui ne
     * permet plus d'exprimer l'erreur protege partout, et le compilateur nomme les appelants a
     * convertir au lieu de laisser un recensement le faire.
     *
     * <p>Deux refus subsistent, et aucun n'invente une regle de style :</p>
     * <ul>
     *   <li>la propriete est declaree plusieurs fois et <b>l'une porte {@code !important}</b> : le
     *       rang dans le corps ne decide plus de la cascade, et ce lecteur ne sait pas la resoudre.
     *       Aucune valeur n'est rendue plutot qu'un chiffre sans methode ;</li>
     *   <li>la propriete n'est pas declaree du tout : l'appelant doit l'avoir constate avant, par
     *       {@link #debutDeDeclaration}, et un appel a l'aveugle est un defaut d'appelant.</li>
     * </ul>
     *
     * <p>Un appelant qui veut vraiment une occurrence precise - inventorier ce que l'auteur a ecrit
     * plutot que ce que le navigateur applique - passe par {@link #valeurALaPosition}, dont le nom
     * dit qu'il sait ce qu'il fait.</p>
     */
    static String valeurDeclaree(String corps, String propriete) {
        List<Integer> declarations = declarationsDe(corps, propriete);
        if (declarations.isEmpty()) {
            throw new AssertionError("ce corps ne declare pas " + propriete + " : constater la "
                    + "presence par debutDeDeclaration avant de demander une valeur");
        }
        if (declarations.size() > 1) {
            List<String> valeurs = declarations.stream()
                    .map(debut -> valeurALaPosition(corps, debut, propriete))
                    .toList();
            if (valeurs.stream().anyMatch(valeur -> valeur.contains("!important"))) {
                throw new AssertionError("ce corps declare " + propriete + " " + declarations.size()
                        + " fois (" + String.join(" | ", valeurs) + ") et l'une porte !important : le "
                        + "rang dans le corps ne decide plus de la cascade, et ce lecteur ne sait pas "
                        + "la resoudre. Aucune valeur n'est rendue plutot qu'un chiffre sans methode");
            }
        }
        return valeurALaPosition(corps, declarations.get(declarations.size() - 1), propriete);
    }

    /**
     * La valeur ecrite a cette position precise, <b>y compris si une declaration posterieure
     * l'ecrase</b>. Deliberement different de {@link #valeurDeclaree} : ce lecteur-ci sert a
     * inventorier ce que l'auteur d'une feuille a ecrit - une triplette de repli de prefixes doit
     * apparaitre en entier - jamais a comparer des valeurs appliquees. Son nom est ce qui empeche
     * de l'employer par distraction.
     */
    static String valeurALaPosition(String corps, int position, String propriete) {
        int debut = position + propriete.length() + ":".length();
        int fin = corps.indexOf(';', debut);
        return corps.substring(debut, fin < 0 ? corps.length() : fin).strip();
    }

    /** ADR-0013 obligation 1 : les gabarits balayes viennent du repertoire, jamais d'une liste. */
    static List<Path> gabarits(Path racine) {
        Path repertoire = racine.resolve("templates");
        if (!Files.isDirectory(repertoire)) {
            return List.of();
        }
        try (Stream<Path> arborescence = Files.walk(repertoire)) {
            return arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(".html"))
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Un bouton et l'un des jeux de classes qu'il peut porter une fois rendu. Un bouton dont la
     * classe est conditionnelle en produit un par branche.
     *
     * <p>Visible dans le paquet depuis ADR-0046, avec {@link #selecteursApplicables} et
     * {@link #etiquette} : la clause d'etat lit exactement la meme population de boutons rendus et
     * les memes selecteurs applicables que les planchers de ce fichier. Deux derivations libres de
     * divergier laisseraient un etat de bouton confronte a un plancher ici et pas a son etat
     * interactif la-bas - le meme motif que l'accord entre habillage et pressable d'ADR-0033.
     */
    record BoutonRendu(Element bouton, List<String> classes) {
    }

    /**
     * ADR-0015 : les valeurs litterales qu'une expression Thymeleaf peut prendre. Une expression
     * concatenee, ou qui ne contient aucun litteral, est refusee plutot qu'ignoree : on ne peut pas
     * confronter au plancher une classe qu'on ne sait pas lire (ADR-0013 obligation 2, meme posture
     * que RessourcesTiercesTest devant une ressource referencee par une expression calculee).
     */
    private static List<String> litteraux(Element element, String attribut) {
        String expression = element.attr(attribut);
        List<String> valeurs = new ArrayList<>();
        int position = 0;
        while ((position = expression.indexOf('\'', position)) >= 0) {
            int fin = expression.indexOf('\'', position + 1);
            if (fin < 0) {
                break;
            }
            valeurs.add(expression.substring(position + 1, fin).strip());
            position = fin + 1;
        }
        String origine = "<" + element.tagName() + "> " + attribut + "=\"" + expression + "\" ("
                + element.ownerDocument().location() + ")";
        assertThat(valeurs)
                .as("%s : aucune classe litterale a lire dans cette expression ; une classe calculee "
                        + "ne peut pas etre confrontee au plancher exige par nadia-prof", origine)
                .isNotEmpty();
        assertThat(expression)
                .as("%s : une expression concatenee ne dit pas quelles classes seront rendues ; "
                        + "ecrire chaque etat en litteral", origine)
                .doesNotContain("+");
        return valeurs;
    }

    private static List<String> motsDe(String litteral) {
        return Stream.of(litteral.split("\\s+")).filter(mot -> !mot.isEmpty()).toList();
    }

    /**
     * ADR-0015, ADR-0013 obligation 1 : le perimetre derive de la source qui gouverne le rendu.
     * L'attribut class du gabarit source cesse d'etre cette source des qu'un th:class le remplace,
     * et un th:classappend ajoute des classes qu'il ne porte pas non plus. Chaque branche du
     * conditionnel donne un etat rendu, et le plancher doit tenir dans chacun.
     */
    private static List<List<String>> etatsDe(Element bouton) {
        List<List<String>> etats = new ArrayList<>();
        if (bouton.hasAttr(CLASSE_CALCULEE)) {
            for (String litteral : litteraux(bouton, CLASSE_CALCULEE)) {
                etats.add(new ArrayList<>(motsDe(litteral)));
            }
        } else {
            etats.add(new ArrayList<>(bouton.classNames()));
        }
        if (bouton.hasAttr(CLASSE_AJOUTEE)) {
            List<String> ajouts = litteraux(bouton, CLASSE_AJOUTEE).stream()
                    .flatMap(litteral -> motsDe(litteral).stream())
                    .toList();
            etats.forEach(etat -> etat.addAll(ajouts));
        }
        return etats;
    }

    /** Tout bouton et tout lien-bouton reellement rendu, releve dans le balisage, etat par etat. */
    static List<BoutonRendu> etatsRendus(Path racine) {
        List<BoutonRendu> etats = new ArrayList<>();
        for (Path gabarit : gabarits(racine)) {
            Document document = Jsoup.parse(lire(gabarit), gabarit.toString());
            for (Element bouton : document.select(PRESSABLES)) {
                for (List<String> classes : etatsDe(bouton)) {
                    etats.add(new BoutonRendu(bouton, classes));
                }
            }
        }
        return etats;
    }

    /**
     * Les regles qui s'appliquent reellement a cet etat : ses classes s'il en porte, la balise nue
     * sinon. Un bouton sans classe qui ne correspond a aucune regle de balise nue tombe au defaut du
     * navigateur : c'est exactement ce que cette derivation doit detecter, plutot que de le laisser
     * passer parce qu'il n'a pas de classe a controler.
     */
    static List<String> selecteursApplicables(BoutonRendu etat) {
        List<String> classes = etat.classes().stream().map(classe -> "." + classe).toList();
        return classes.isEmpty() ? List.of(etat.bouton().tagName()) : classes;
    }

    /**
     * ADR-0014 : parmi les regles applicables a ce bouton, celles que ce depot ecrit. La liste doit
     * etre non vide - c'est la ou une bibliotheque tierce cesse de suffire.
     */
    private static List<String> selecteursMaisonApplicables(Map<String, String> maison, BoutonRendu etat) {
        List<String> applicables = selecteursApplicables(etat);
        List<String> maisonnee = applicables.stream().filter(maison::containsKey).toList();
        assertThat(maisonnee)
                .as("%s : aucune regle maison ne s'applique a ce bouton (selecteurs vus : %s). "
                        + "ADR-0014 : une bibliotheque tierce porte le rendu de base, elle ne porte "
                        + "jamais un plancher exige par nadia-prof - il faut une classe maison qui "
                        + "le declare", etiquette(etat), applicables)
                .isNotEmpty();
        return maisonnee;
    }

    static String etiquette(BoutonRendu etat) {
        Element bouton = etat.bouton();
        String description = etat.classes().isEmpty()
                ? "<" + bouton.tagName() + "> sans classe"
                : "." + String.join(".", etat.classes());
        return description + " \"" + bouton.text() + "\" (" + bouton.ownerDocument().location() + ")";
    }

    private static List<BoutonRendu> etatsPassesAuCrible(Path racine) {
        List<BoutonRendu> etats = etatsRendus(racine);
        assertThat(etats)
                .as("cette regle ne prouve rien si aucun bouton n'est passe au crible")
                .isNotEmpty();
        return etats;
    }

    /**
     * S2-1, motif du refus : "au minimum" est un plancher, pas une liste fermee, et un bouton de
     * reponse est un bouton actionnable comme un autre. La liste testee vient des gabarits.
     */
    static void verifierPlancherDeLisibilite(Path racine) {
        Map<String, String> maison = blocsMaison(racine);
        for (BoutonRendu etat : etatsPassesAuCrible(racine)) {
            for (String selecteur : selecteursMaisonApplicables(maison, etat)) {
                Optional<Double> taille = proprieteEnRemSiExiste(maison, selecteur, "font-size");
                assertThat(taille)
                        .as("%s : la regle %s doit fixer une taille de police en rem, sinon le bouton "
                                + "retombe au defaut du navigateur (environ 13px)",
                                etiquette(etat), selecteur)
                        .isPresent();
                assertThat(taille.get())
                        .as("%s ne doit jamais retomber au defaut du navigateur (environ 13px)",
                                etiquette(etat))
                        .isGreaterThanOrEqualTo(PLANCHER_DE_LISIBILITE_REM);
            }
        }
    }

    /**
     * Deuxieme motif du refus : une cible tactile de 46 x 41 px avec 8 px d'espacement est sous le
     * minimum de 44 px. min-height/min-width garantissent un plancher independant du contenu.
     */
    static void verifierCibleTactile(Path racine) {
        Map<String, String> maison = blocsMaison(racine);
        for (BoutonRendu etat : etatsPassesAuCrible(racine)) {
            for (String selecteur : selecteursMaisonApplicables(maison, etat)) {
                Optional<Double> hauteur = proprieteEnRemSiExiste(maison, selecteur, "min-height");
                assertThat(hauteur)
                        .as("%s : la regle %s doit fixer min-height en rem, sinon la cible tactile "
                                + "retombe au defaut du navigateur", etiquette(etat), selecteur)
                        .isPresent();
                assertThat(hauteur.get())
                        .as("%s : la hauteur minimale doit atteindre au moins 44px (2,75rem)",
                                etiquette(etat))
                        .isGreaterThanOrEqualTo(CIBLE_TACTILE_REM);
            }
        }
    }

    /**
     * ADR-0014 : la feuille maison est chargee en dernier (RessourcesTiercesTest), donc une regle
     * maison l'emporte sur une regle tierce de meme specificite. Une seule chose renverse cet ordre :
     * !important. Une classe utilitaire tierce posee sur un bouton peut ainsi defaire un plancher
     * declare ici sans qu'aucune feuille maison ne change. C'est le seul motif pour lequel ce
     * garde-fou lit le CSS tiers, et il ne lui reproche rien en soi : il reproche a <b>notre</b>
     * bouton de porter une classe qui rend notre garantie inoperante.
     */
    static void verifierQueLeTiersNeDefaitPasLesPlanchers(Path racine) {
        Map<String, String> tiers = blocsTiers(racine);
        for (BoutonRendu etat : etatsPassesAuCrible(racine)) {
            for (String selecteur : selecteursApplicables(etat)) {
                String corps = tiers.get(selecteur);
                if (corps == null) {
                    continue;
                }
                for (String propriete : PROPRIETES_GARDEES) {
                    assertThat(declareEnImportant(corps, propriete))
                            .as("%s : la classe tierce %s impose %s en !important et prend le pas sur "
                                    + "la regle maison ; le plancher exige par nadia-prof serait "
                                    + "declare mais pas rendu", etiquette(etat), selecteur, propriete)
                            .isFalse();
                }
            }
        }
    }

    /**
     * Vrai si <b>une</b> declaration de cette propriete porte {@code !important}, pas seulement la
     * derniere. Corrige le 2026-08-27 (s21) en meme temps que le lecteur de valeurs : la question
     * posee ici n'est pas "que vaut la propriete" mais "un !important est-il pose quelque part", et
     * un {@code !important} anterieur bat une declaration ordinaire posterieure. Lire la seule
     * gagnante manquerait exactement le cas que ce controle existe pour trouver - et la feuille
     * tierce, ou il s'applique, est precisement celle qui declare une meme propriete plusieurs fois.
     */
    private static boolean declareEnImportant(String corps, String propriete) {
        return declarationsDe(corps, propriete).stream()
                .anyMatch(position -> valeurALaPosition(corps, position, propriete)
                        .contains("!important"));
    }

    @Test
    void auMoinsUnBoutonEstReleveDansLesGabarits() {
        assertThat(etatsRendus(RACINE_PRODUCTION))
                .as("cette regle ne prouve rien si aucun bouton n'est passe au crible")
                .isNotEmpty();
    }

    /**
     * ADR-0015 : la sortie de fin de partie ne porte .sortie-pleine que par la branche d'un
     * th:class. Tant que le perimetre derivait du seul attribut class, cet etat n'etait jamais
     * confronte au plancher. Ce test pin le fait qu'il l'est maintenant, pour qu'une regression du
     * mecanisme de derivation redevienne rouge ici et pas seulement chez nadia-prof.
     */
    @Test
    void unEtatDeBoutonNeVenantQueDUnThClassEstPasseAuCrible() {
        assertThat(etatsRendus(RACINE_PRODUCTION).stream()
                .flatMap(etat -> etat.classes().stream())
                .toList())
                .as("le perimetre doit contenir une classe qu'aucun attribut class ne porte")
                .contains("sortie-pleine");
    }

    /**
     * <b>ADR-0050 : epinglage, plus une promesse generale.</b> Son nom dit "aucun texte" et son
     * perimetre est une liste de sept selecteurs ecrite a la main : {@code .texte-support},
     * {@code .verdict}, {@code .filtre-niveau} ou {@code .question-confirmation} n'y sont pas, et une
     * regle qui abaisserait l'un d'eux passait verte de bout en bout. La promesse generale est
     * desormais portee par {@link #aucuneTailleDeclareeParLaFeuilleMaisonNePasseSousLePlancher},
     * derivee de la feuille ; ces sept-la restent nommes comme ce qu'ils sont, l'epinglage de ce que
     * S2-1 et S3-4 ont gagne. Meme resolution qu'ADR-0015 et qu'ADR-0039 : la propriete generale
     * demenage dans un balayage derive, l'epinglage nomme reste nomme.
     */
    @Test
    void aucunTexteNePasseSousLaTailleDeLaConsigne() {
        Map<String, String> blocs = blocsMaison(RACINE_PRODUCTION);
        double consigne = tailleEnRem(blocs, ".consigne");
        assertThat(consigne).isEqualTo(PLANCHER_DE_LISIBILITE_REM);

        for (String selecteur : new String[] {
                ".score", ".indice", ".fin-de-partie", ".erreur", ".pas-de-jeu", ".message-reprise",
                ".explication"
        }) {
            assertThat(tailleEnRem(blocs, selecteur))
                    .as("%s ne doit jamais passer sous la consigne (%.2frem)", selecteur, consigne)
                    .isGreaterThanOrEqualTo(consigne);
        }
    }

    @Test
    void toutBoutonReellementRenduEstAuMoinsAUnRemVingtCinq() {
        verifierPlancherDeLisibilite(RACINE_PRODUCTION);
    }

    @Test
    void toutBoutonReellementRenduAUneCibleTactileDAuMoinsQuaranteQuatrePixels() {
        verifierCibleTactile(RACINE_PRODUCTION);
    }

    @Test
    void aucuneClasseTierceNeDefaitUnPlancherSurUnBoutonRendu() {
        verifierQueLeTiersNeDefaitPasLesPlanchers(RACINE_PRODUCTION);
    }

    @Test
    void lIndiceEstAuMoinsAussiGrandQueLaConsigne() {
        Map<String, String> blocs = blocsMaison(RACINE_PRODUCTION);
        assertThat(tailleEnRem(blocs, ".indice"))
                .isGreaterThanOrEqualTo(tailleEnRem(blocs, ".consigne"));
    }

    @Test
    void h1PorteUneRegleDeTailleExplicite() {
        assertThat(tailleEnRem(blocsMaison(RACINE_PRODUCTION), "h1")).isGreaterThan(0);
    }

    /**
     * S3-4, reserve 1 de nadia-prof sur S2-1 : le chiffre d'un bouton de reponse est ce qui
     * distingue les trois propositions, il doit donc peser au moins autant que le calcul affiche
     * a l'ecran (.enonce), et non rester le detail le plus petit d'une large barre.
     *
     * <p><b>Amendement S8-5, condition 7 de nadia-prof.</b> Ce que ce test lit n'est plus vrai
     * partout : sous le repli a quatre colonnes, l'enfant voit une proposition a 1,875rem, donc plus
     * petite que {@code .enonce} (2rem). nadia-prof a accorde cette entaille a son acquis 3
     * nommement, une fois, sur ce seul cas. Ce test-ci ne la voit pas et n'a pas a la voir : il lit le
     * selecteur litteral {@code .proposition}, dont le plancher general n'a pas bouge. Ce qui garantit
     * qu'il n'y a pas de deuxieme entaille est
     * {@link #lesSeulesTaillesDePropositionSontLesDeuxReglesNommees}, et non ce test.
     */
    @Test
    void leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul() {
        Map<String, String> blocs = blocsMaison(RACINE_PRODUCTION);
        double proposition = tailleEnRem(blocs, ".proposition");

        assertThat(proposition).isGreaterThanOrEqualTo(2.0);
        assertThat(proposition).isGreaterThanOrEqualTo(tailleEnRem(blocs, ".enonce"));
    }

    /**
     * S8-5, condition 7 de l'arbitrage prealable de nadia-prof du 2026-08-11
     * (FICHE-bandit-manchot-conjugaison-present-cm1.md, section C) : le test ci-dessus continue de
     * lire le selecteur litteral {@code .proposition} et reste vert a bon droit - son plancher
     * general de 2rem n'a pas change. Mais nadia-prof refuse qu'un lecteur croie ce chiffre encore
     * garde dans tous les cas, alors qu'un enfant voit desormais 1,875rem sous le repli a quatre
     * colonnes, et moins que {@code .enonce} sur ce seul cas (elle accepte les deux clauses de son
     * acquis 3 : la valeur et le rapport a l'enonce). Ce test encode l'exception au mot pres :
     * {@code .proposition} garde son plancher general de 2rem partout ailleurs, et
     * {@link #SELECTEUR_REPLI_QUATRE_COLONNES} porte un plancher distinct, strictement plus bas,
     * jamais une degradation silencieuse du premier.
     */
    @Test
    void laPoliceDuRepliAQuatreColonnesEstUneExceptionAuPlancherGeneralDeDeuxRem() {
        Map<String, String> blocs = blocsMaison(RACINE_PRODUCTION);

        assertThat(tailleEnRem(blocs, ".proposition"))
                .as(".proposition, hors du repli a quatre colonnes, reste au plancher general de "
                        + "2rem : cette exception ne le degrade pas")
                .isEqualTo(2.0);

        double exception = tailleEnRem(blocs, SELECTEUR_REPLI_QUATRE_COLONNES);
        assertThat(exception)
                .as("%s doit rester litteralement 1,875rem (condition 3 de nadia-prof : ni "
                        + "variable, ni calc)", SELECTEUR_REPLI_QUATRE_COLONNES)
                .isEqualTo(PLANCHER_REPLI_QUATRE_COLONNES_REM);
        assertThat(exception)
                .as("le plancher du repli a quatre colonnes doit rester strictement sous le "
                        + "plancher general de .proposition, sinon ce n'est plus l'exception "
                        + "nommee mais une confusion des deux selecteurs")
                .isLessThan(2.0);
    }

    /**
     * S8-5 : verification partagee du plancher de la condition 2 de nadia-prof, sous le repli a
     * quatre colonnes. Distincte de {@code verifierPlancherDeLisibilite} (1,25rem, tout texte) et
     * du plancher general de {@code .proposition} (2rem) : celle-ci ne porte que sur
     * {@link #SELECTEUR_REPLI_QUATRE_COLONNES}.
     */
    static void verifierPlancherDuRepliAQuatreColonnes(Path racine) {
        double taille = tailleEnRem(blocsMaison(racine), SELECTEUR_REPLI_QUATRE_COLONNES);
        assertThat(taille)
                .as("%s : nadia-prof accepte 1,875rem et rien en dessous (condition 2, arbitrage "
                        + "prealable du 2026-08-11 sur S8-5) - un plancher, pas une pente",
                        SELECTEUR_REPLI_QUATRE_COLONNES)
                .isGreaterThanOrEqualTo(PLANCHER_REPLI_QUATRE_COLONNES_REM);
    }

    /**
     * nadia-prof a elle-meme mesure et rejete 1,8125rem (29px) : "il donne plus d'air, il n'est
     * pas approuve" (meme section). Ce contre-exemple porte exactement la valeur qu'elle a nommee
     * comme refusee, pas une valeur arbitraire sous le plancher.
     */
    @Test
    void unePoliceSousUnRemHuitCentSoixanteQuinzeSousLeRepliFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("police-sous-le-plancher-du-repli");
        assertThatThrownBy(() -> verifierPlancherDuRepliAQuatreColonnes(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("rien en dessous");
    }

    /**
     * Une regle CSS telle qu'elle est ecrite : d'ou elle vient, ce qu'elle habille, ce qu'elle dit.
     *
     * <p>Visible dans le paquet depuis ADR-0053 : la clause de position lit les memes regles, dans le
     * meme ordre de fichier, que celles de ce garde-fou. Deux lectures libres de diverger finiraient
     * par juger deux feuilles differentes (le motif deja ecrit pour {@code BoutonRendu}).</p>
     */
    record RegleEcrite(String origine, String selecteur, String corps) {
    }

    /**
     * Toutes les regles de style des feuilles maison, chaque selecteur rendu separement, les regles
     * nichees dans une regle-@ comprises.
     *
     * <p>Ce balayage existe parce que {@link #blocsMaison} ne peut pas porter cette clause-ci.
     * blocsMaison aplatit la feuille en une table selecteur -&gt; corps : il fusionne les voisins
     * d'une liste separee par des virgules, et attribue le corps d'une regle nichee sous
     * {@code @media} a la cle {@code "@media (...)"}. C'est assez pour lire un plancher declare a un
     * selecteur qu'on nomme - ce que fait tout le reste de ce fichier -, jamais pour prouver
     * qu'<b>aucune autre</b> regle n'en declare un. La feuille de production porte deja deux regles-@
     * ({@code @keyframes} et {@code @media (prefers-reduced-motion: reduce)}) : le canal est ouvert,
     * pas hypothetique.
     */
    static List<RegleEcrite> reglesEcritesMaison(Path racine) {
        List<RegleEcrite> regles = new ArrayList<>();
        for (Path feuille : feuillesMaison(racine)) {
            String source = sansCommentaires(lire(feuille));
            List<String> preludes = new ArrayList<>();
            StringBuilder tampon = new StringBuilder();
            for (int position = 0; position < source.length(); position++) {
                char caractere = source.charAt(position);
                if (caractere == '{') {
                    preludes.add(tampon.toString().strip());
                    tampon.setLength(0);
                } else if (caractere == '}') {
                    String prelude = preludes.isEmpty() ? "" : preludes.remove(preludes.size() - 1);
                    if (!prelude.startsWith("@")) {
                        String origine = preludes.isEmpty()
                                ? feuille.toString()
                                : feuille + " sous " + String.join(" ", preludes);
                        for (String selecteur : LAnimationRetardeMaisNeDetermineRienTest.selecteurs(prelude)) {
                            regles.add(new RegleEcrite(origine, selecteur, tampon.toString()));
                        }
                    }
                    tampon.setLength(0);
                } else {
                    tampon.append(caractere);
                }
            }
        }
        return regles;
    }

    /**
     * Le sujet d'un selecteur : son dernier compose, le seul element qu'il habille reellement.
     * {@code .propositions:has(.proposition:nth-child(4)) .proposition} a pour sujet
     * {@code .proposition} ; {@code .propositions:has(.proposition:nth-child(4))} a pour sujet le
     * conteneur, jamais la proposition qu'il cite. Les combinateurs ne sont lus qu'au niveau zero de
     * parenthese, sans quoi une espace interieure a un {@code :has()} couperait le selecteur en deux.
     */
    static String sujetDuSelecteur(String selecteur) {
        int profondeur = 0;
        int debut = 0;
        for (int position = 0; position < selecteur.length(); position++) {
            char caractere = selecteur.charAt(position);
            if (caractere == '(') {
                profondeur++;
            } else if (caractere == ')') {
                profondeur--;
            } else if (profondeur == 0 && (Character.isWhitespace(caractere)
                    || caractere == '>' || caractere == '+' || caractere == '~')) {
                debut = position + 1;
            }
        }
        return selecteur.substring(debut).strip();
    }

    private static String sansArgumentsConditionnels(String selecteur) {
        String reste = selecteur;
        for (String pseudo : PSEUDOS_QUI_NE_DECRIVENT_PAS_LE_SUJET) {
            int debut;
            while ((debut = reste.indexOf(pseudo)) >= 0) {
                int position = debut + pseudo.length();
                int profondeur = 1;
                while (position < reste.length() && profondeur > 0) {
                    char caractere = reste.charAt(position);
                    if (caractere == '(') {
                        profondeur++;
                    } else if (caractere == ')') {
                        profondeur--;
                    }
                    position++;
                }
                reste = reste.substring(0, debut) + reste.substring(position);
            }
        }
        return reste;
    }

    /** Un caractere qui prolonge un nom de classe CSS : tant qu'il en vient un, le jeton continue. */
    private static boolean prolongeUnNomDeClasse(char caractere) {
        return Character.isLetterOrDigit(caractere) || caractere == '-' || caractere == '_';
    }

    /** Cette regle habille-t-elle une proposition, et non son conteneur ni son voisinage ? */
    static boolean habilleUneProposition(String selecteur) {
        String sujet = sansArgumentsConditionnels(sujetDuSelecteur(selecteur));
        int position = sujet.indexOf(CLASSE_PROPOSITION);
        while (position >= 0) {
            int apres = position + CLASSE_PROPOSITION.length();
            if (apres >= sujet.length() || !prolongeUnNomDeClasse(sujet.charAt(apres))) {
                return true;
            }
            position = sujet.indexOf(CLASSE_PROPOSITION, position + 1);
        }
        return false;
    }

    /**
     * S8-5, dernier mot de theo-archi sur la condition 7 de nadia-prof (arbitrage prealable du
     * 2026-08-11, FICHE-bandit-manchot-conjugaison-present-cm1.md, section C).
     *
     * <p>Sa condition demande que l'exception soit lisible, parce qu'elle refuse qu'un lecteur croie
     * 2rem encore garde partout. Les deux tests qui precedent la rendent lisible, et la mordent la ou
     * elle est ecrite. Il leur manquait la seule propriete qui rende la phrase vraie : que l'exception
     * soit <b>unique</b>. Ils epinglent deux valeurs a deux selecteurs nommes ; ils ne disent rien
     * d'une troisieme regle qui abaisserait la taille d'une proposition ailleurs. Une telle regle
     * passe aujourd'hui verte de bout en bout - elle reste au-dessus du plancher de lisibilite de
     * 1,25rem, elle ne touche ni {@code .proposition} ni {@link #SELECTEUR_REPLI_QUATRE_COLONNES} -
     * et l'enfant verrait alors une troisieme taille qu'aucun arbitrage n'a accordee. Cette clause
     * enumere : la feuille maison porte exactement les deux regles de
     * {@link #TAILLES_NOMMEES_DES_PROPOSITIONS}, a leur valeur exacte, et aucune autre.
     */
    static void verifierQueLesTaillesDePropositionSontToutesNommees(Path racine) {
        List<RegleEcrite> regles = reglesEcritesMaison(racine);
        assertThat(regles)
                .as("aucune regle maison relevee sous %s : ce garde-fou ne prouve rien", racine)
                .isNotEmpty();

        Set<String> nommees = new LinkedHashSet<>();
        for (RegleEcrite regle : regles) {
            if (!habilleUneProposition(regle.selecteur())
                    || debutDeDeclaration(regle.corps(), "font-size") < 0) {
                continue;
            }
            Double attendue = TAILLES_NOMMEES_DES_PROPOSITIONS.get(regle.selecteur());
            assertThat(attendue)
                    .as("%s : la regle %s fixe la taille d'une proposition sans etre l'une des deux "
                            + "que nadia-prof a nommees. Elle n'a accorde qu'une seule exception aux "
                            + "2rem, sous le repli a quatre colonnes (condition 7, arbitrage "
                            + "prealable du 2026-08-11) - une deuxieme est un arbitrage a lui "
                            + "demander, pas une ligne de feuille a ajouter",
                            regle.origine(), regle.selecteur())
                    .isNotNull();
            assertThat(proprieteEnRemSiExiste(regle.corps(), "font-size"))
                    .as("%s : la regle %s doit declarer %srem, litteralement - ni variable, ni calc, "
                            + "meme a pixels rendus identiques (condition 3 de nadia-prof)",
                            regle.origine(), regle.selecteur(), attendue)
                    .contains(attendue);
            nommees.add(regle.selecteur());
        }

        assertThat(nommees)
                .as("les deux regles nommees doivent etre celles que la feuille porte vraiment : une "
                        + "enumeration dont un membre a disparu n'enumere plus rien")
                .containsExactlyInAnyOrderElementsOf(TAILLES_NOMMEES_DES_PROPOSITIONS.keySet());
    }

    /**
     * Toutes les declarations de cette propriete dans un corps de regle, et non la premiere seule.
     * Une regle qui declarerait deux fois la taille du texte ne doit pas cacher la seconde derriere
     * la premiere : {@link #debutDeDeclaration} s'arrete au premier debut trouve, ce qui suffit pour
     * lire une valeur qu'on sait unique, jamais pour affirmer qu'aucune autre n'existe.
     */
    static List<Integer> declarationsDe(String corps, String propriete) {
        List<Integer> positions = new ArrayList<>();
        String etiquette = propriete + ":";
        int position = corps.indexOf(etiquette);
        while (position >= 0) {
            if (estDebutDeDeclaration(corps, position)) {
                positions.add(position);
            }
            position = corps.indexOf(etiquette, position + 1);
        }
        return positions;
    }

    /**
     * <b>ADR-0050, clause 1 : aucune regle de la feuille maison ne descend le texte sous le plancher
     * de lisibilite.</b>
     *
     * <p>Le plancher de 1,25rem est de nadia-prof, pas de moi, et il est deja garde deux fois : sur
     * tout bouton reellement rendu ({@link #verifierPlancherDeLisibilite}, perimetre derive des
     * gabarits) et sur sept selecteurs nommes a la main
     * ({@link #aucunTexteNePasseSousLaTailleDeLaConsigne}). Entre les deux, <b>un texte qui n'est
     * pas un bouton et qui n'est pas dans les sept</b> n'etait confronte a rien. Ce n'est pas une
     * hypothese : au 2026-08-19, la feuille servie declare 1,25rem sur {@code .signal .verdict}
     * (le juste/faux que l'enfant lit apres chaque reponse), {@code .filtre-niveau},
     * {@code .question-confirmation}, {@code .cabinet-enseigne} et {@code .chip-niveau} - cinq
     * regles dont aucun garde-fou ne lisait la valeur. Methode du releve : lecture des 20
     * declarations {@code font-size} de {@code static/css/recreo.css} sur cet arbre, confrontees a
     * la liste de sept et a la population de boutons rendus.</p>
     *
     * <p><b>Ce qu'elle juge, et ce qu'elle ne juge pas.</b> Elle lit des <b>declarations</b>, jamais
     * des pixels rendus : ADR-0025 et ADR-0028 valent ici comme ailleurs, la hauteur reelle d'un bloc
     * n'est pas gardable et reste a la porte de nadia-prof. Elle ne distingue pas non plus un texte
     * lu d'un signe decoratif - cette difference-la, aucun garde-fou ne la voit (ADR-0035), et c'est
     * pourquoi le plancher vaut pour toute taille declaree : une police plus petite se gagne par un
     * arbitrage, comme la seule entaille jamais accordee au plancher des propositions (condition 2
     * de nadia-prof, 2026-08-11), jamais par une ligne de feuille.</p>
     *
     * <p>ADR-0013 obligation 2 : une taille qu'elle ne sait pas lire - une variable, un
     * {@code calc()} - est un defaut, jamais un cas ignore. Le plancher illisible est un plancher
     * absent.</p>
     */
    static void verifierQuAucuneTailleDeclareeNePasseSousLePlancher(Path racine) {
        List<RegleEcrite> regles = reglesEcritesMaison(racine);
        assertThat(regles)
                .as("aucune regle maison relevee sous %s : ce garde-fou ne prouve rien", racine)
                .isNotEmpty();

        List<String> taillesRelevees = new ArrayList<>();
        for (RegleEcrite regle : regles) {
            for (int position : declarationsDe(regle.corps(), "font-size")) {
                String valeur = valeurDeclaree(regle.corps(), "font-size");
                taillesRelevees.add(regle.selecteur() + " -> " + valeur);
                assertThat(valeur)
                        .as("%s : la regle %s declare la taille du texte en \"%s\", que ce garde-fou "
                                + "ne sait pas confronter au plancher de %s rem. Une variable ou un "
                                + "calc() rendent peut-etre les memes pixels, mais ils rendent le "
                                + "plancher illisible ici, et un plancher illisible est un plancher "
                                + "absent (ADR-0050, ADR-0013 obligation 2)",
                                regle.origine(), regle.selecteur(), valeur, PLANCHER_DE_LISIBILITE_REM)
                        .endsWith("rem");
                double taille =
                        Double.parseDouble(valeur.substring(0, valeur.length() - "rem".length()));
                assertThat(taille)
                        .as("%s : la regle %s descend le texte a %s, sous le plancher de lisibilite "
                                + "de %s rem que nadia-prof tient pour non negociable. Ce plancher ne "
                                + "se defait pas par une ligne de feuille : il se defait par un "
                                + "arbitrage, comme la seule entaille jamais accordee - 1,875rem sous "
                                + "le repli a quatre colonnes (ADR-0050)",
                                regle.origine(), regle.selecteur(), valeur, PLANCHER_DE_LISIBILITE_REM)
                        .isGreaterThanOrEqualTo(PLANCHER_DE_LISIBILITE_REM);
            }
        }

        assertThat(taillesRelevees)
                .as("ADR-0003 : aucune declaration de taille de texte relevee sous %s ; ce garde-fou "
                        + "serait vert sans avoir rien examine", racine)
                .isNotEmpty();
    }

    @Test
    void aucuneTailleDeclareeParLaFeuilleMaisonNePasseSousLePlancher() {
        verifierQuAucuneTailleDeclareeNePasseSousLePlancher(RACINE_PRODUCTION);
    }

    /**
     * S24, ADR-0058 section 7 : la <b>mesure</b> de ce que l'extension du selecteur des pressables a
     * "input" ajoute a la population de ce garde-fou. Avant elle, le champ de saisie n'etait
     * confronte ni au plancher de lisibilite de 1,25 rem ni a la cible tactile de 44 px, et une CI
     * entierement verte l'aurait laisse passer. Ce test dit ce que le garde-fou voit ; le suivant dit
     * qu'il mord dessus.
     */
    @Test
    void leChampDeSaisieEntreDansLaPopulationDesPressables() {
        assertThat(etatsRendus(RACINE_PRODUCTION).stream()
                .filter(etat -> "input".equals(etat.bouton().tagName()))
                .map(BoutonRendu::classes)
                .toList())
                .as("le champ de saisie doit etre passe au crible comme les boutons")
                .contains(List.of("champ-reponse"));
    }

    /**
     * ADR-0013 obligation 3, sur du materiau reel mute : la feuille servie, avec la seule cible
     * tactile du champ de saisie abaissee sous le plancher. Sans "input" dans le selecteur des
     * pressables, cette mutation passait une CI verte - c'est l'omission par enumeration de types,
     * la lecon que ce depot a payee deux sprints.
     *
     * <p>La seconde moitie de l'assertion est celle qui rend la morsure non decorative : le message
     * doit nommer <b>le champ</b>, et pas seulement echouer. Une morsure qui se contenterait du rouge
     * serait verte sur une regle qui refuserait n'importe quelle feuille.</p>
     */
    @Test
    void unChampDeSaisieSousLaCibleTactileFaitEchouerLeGardeFou(@TempDir Path racine)
            throws IOException {
        String avant = "    min-height: 3.5rem;\n    min-width: 2.75rem;";
        String apres = "    min-height: 2rem;\n    min-width: 2.75rem;";
        Path feuille = preparerLaFeuilleMutee(racine, avant, apres);
        assertThat(feuille).isRegularFile();

        assertThatThrownBy(() -> verifierCibleTactile(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(".champ-reponse")
                .hasMessageContaining("la hauteur minimale doit atteindre au moins 44px");
    }

    /**
     * Le meme materiau, la meme population, mais la mutation porte cette fois sur la taille du
     * texte : les deux planchers sont mordus separement, parce qu'une extension qui n'en servirait
     * qu'un laisserait l'autre muet.
     */
    @Test
    void unChampDeSaisieSousLePlancherDeLisibiliteFaitEchouerLeGardeFou(@TempDir Path racine)
            throws IOException {
        preparerLaFeuilleMutee(racine,
                "    font-size: 2rem;\n    font-weight: bold;\n    color: var(--recreo-texte);",
                "    font-size: 1rem;\n    font-weight: bold;\n    color: var(--recreo-texte);");

        assertThatThrownBy(() -> verifierPlancherDeLisibilite(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("champ-reponse");
    }

    /**
     * Recopie les gabarits et la feuille reels sous {@code racine}, la feuille portant la mutation
     * demandee. La mutation doit porter sur une declaration que la feuille servie porte vraiment :
     * une fixture ecrite de ma main ressemblerait a ce que j'imagine (lecon d'ADR-0015).
     */
    private static Path preparerLaFeuilleMutee(Path racine, String avant, String apres)
            throws IOException {
        String reelle = lire(RACINE_PRODUCTION.resolve("static/css/recreo.css"))
                .replace("\r\n", "\n");
        assertThat(reelle)
                .as("la mutation doit porter sur une regle que la feuille servie porte vraiment")
                .contains(avant);
        Path feuille = racine.resolve("static/css/recreo.css");
        Files.createDirectories(feuille.getParent());
        Files.writeString(feuille, reelle.replace(avant, apres));

        for (Path gabarit : gabarits(RACINE_PRODUCTION)) {
            Path copie = racine.resolve(RACINE_PRODUCTION.relativize(gabarit));
            Files.createDirectories(copie.getParent());
            Files.writeString(copie, lire(gabarit));
        }
        Path tiers = racine.resolve("static/vendor");
        Files.createDirectories(tiers);
        for (Path feuilleTierce : Files.list(RACINE_PRODUCTION.resolve("static/vendor")).toList()) {
            Files.copy(feuilleTierce, tiers.resolve(feuilleTierce.getFileName()));
        }
        return feuille;
    }

    /**
     * ADR-0013 obligation 3 : la morsure est prouvee sur <b>du materiau reel mute</b>, jamais sur une
     * feuille ecrite de ma main - une fixture de l'auteur de la regle ressemble a ce qu'il imagine
     * (lecon d'ADR-0015, meme posture qu'ADR-0020 qui mute la sortie reelle du serveur). La mutation
     * porte sur {@code .signal .verdict}, le texte qui dit a l'enfant si sa reponse etait juste :
     * il n'est ni un bouton ni l'un des sept selecteurs nommes, donc les deux clauses de plancher qui
     * existaient avant celle-ci restent vertes sur cette feuille mutee. C'est la mesure exacte de ce
     * que cette clause ajoute.
     */
    @Test
    void uneTailleDeTexteAbaisseeHorsDesSeptSelecteursNommesFaitEchouerLeGardeFou(@TempDir Path racine)
            throws IOException {
        String reelle = lire(RACINE_PRODUCTION.resolve("static/css/recreo.css")).replace("\r\n", "\n");
        String avant = ".signal .verdict {\n    font-size: 1.25rem;\n}";
        String apres = ".signal .verdict {\n    font-size: 0.9rem;\n}";
        assertThat(reelle)
                .as("la mutation doit porter sur une regle que la feuille servie porte vraiment")
                .contains(avant);
        Path feuille = racine.resolve("static/css/recreo.css");
        Files.createDirectories(feuille.getParent());
        Files.writeString(feuille, reelle.replace(avant, apres));

        Map<String, String> blocs = blocsMaison(racine);
        assertThat(tailleEnRem(blocs, ".consigne"))
                .as("la feuille mutee garde intact ce que la clause enumeree lit : c'est la mesure de "
                        + "ce qu'elle ne voyait pas")
                .isEqualTo(PLANCHER_DE_LISIBILITE_REM);

        assertThatThrownBy(() -> verifierQuAucuneTailleDeclareeNePasseSousLePlancher(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(".signal .verdict")
                .hasMessageContaining("0.9rem");
    }

    @Test
    void lesSeulesTaillesDePropositionSontLesDeuxReglesNommees() {
        verifierQueLesTaillesDePropositionSontToutesNommees(RACINE_PRODUCTION);
    }

    /**
     * Le jeu fautif porte les deux regles nommees a leur valeur exacte et respecte le plancher de
     * lisibilite : les deux tests de S8-5 y sont verts. Seule une troisieme regle, jamais soumise a
     * nadia-prof, abaisse la taille des propositions dans un autre contexte. C'est la mesure de ce
     * que cette clause interdit et que le reste du fichier laissait passer.
     */
    @Test
    void uneTroisiemeRegleDeTailleSurLesPropositionsFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("troisieme-taille-de-proposition");
        assertThatThrownBy(() -> verifierQueLesTaillesDePropositionSontToutesNommees(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(".cabinet .proposition");
    }

    /**
     * Meme abaissement, mais niche dans une requete de media - le canal par lequel blocsMaison ne
     * voit rien, parce qu'il attribue ce corps a la cle {@code @media (...)}. Ce jeu fautif prouve que
     * le balayage descend dans les regles-@ ; sans lui, la clause tiendrait sa promesse au premier
     * niveau de la feuille seulement.
     */
    @Test
    void uneTailleDePropositionNicheeDansUneRequeteDeMediaFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("taille-de-proposition-sous-media");
        assertThatThrownBy(() -> verifierQueLesTaillesDePropositionSontToutesNommees(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("sous @media");
    }

    /**
     * S3-5, reserve 2 de nadia-prof sur S2-1 : un ecart d'au moins 1 rem entre deux boutons de
     * reponse, au-dessus du plancher de 0,75rem constate en s02, pour un geste d'enfant encore
     * imprecis.
     *
     * <p><b>Amendement ADR-0026.</b> Cette regle ne lisait que {@code gap}, et son commentaire dans
     * {@code recreo.css} disait la limite a voix haute : "proxy valide tant que les boutons restent
     * empiles dans ce conteneur flex en colonne". Le jour ou les propositions deviennent des colonnes
     * cote a cote, l'ecart que l'enfant doit franchir du doigt change d'axe, et deux formes le
     * defaisaient sans faire rougir quoi que ce soit :
     * <ul>
     *   <li>{@code gap: 1rem 0.5rem} - le raccourci a deux valeurs, dont seule la premiere etait
     *       lue ;</li>
     *   <li>{@code gap: 1rem; column-gap: 0.5rem} - une declaration ulterieure qui l'emporte, et que
     *       {@code debutDeDeclaration} ne confond pas avec {@code gap} (le caractere qui la precede
     *       est un tiret), donc ne voyait pas du tout. <b>Vert silencieux.</b></li>
     * </ul>
     * Les deux axes sont desormais resolus separement et confrontes au meme plancher. Le plancher
     * lui-meme ne bouge pas : c'est un acquis d'un refus pedagogique, pas une variable de mise en
     * page (ADR-0026).
     */
    @Test
    void lEcartEntreDeuxBoutonsDeReponseEstAuMoinsUnRemSurLesDeuxAxes() {
        verifierEcartEntreBoutonsDeReponse(RACINE_PRODUCTION);
    }

    /** Les deux axes de {@code gap} sur un conteneur, raccourci resolu puis surcharges appliquees. */
    private record Ecart(double vertical, double horizontal) {
    }

    private static Ecart ecartDe(Map<String, String> blocs, String selecteur) {
        String corps = blocs.get(selecteur);
        assertThat(corps).as("le selecteur %s doit exister dans le CSS maison", selecteur).isNotNull();

        List<Double> raccourci = valeursEnRem(corps, "gap", selecteur);
        assertThat(raccourci)
                .as("%s doit declarer gap, l'ecart entre deux boutons de reponse", selecteur)
                .isNotEmpty();
        double vertical = raccourci.get(0);
        double horizontal = raccourci.size() > 1 ? raccourci.get(1) : raccourci.get(0);

        List<Double> surchargeVerticale = valeursEnRem(corps, "row-gap", selecteur);
        List<Double> surchargeHorizontale = valeursEnRem(corps, "column-gap", selecteur);
        return new Ecart(
                surchargeVerticale.isEmpty() ? vertical : surchargeVerticale.get(0),
                surchargeHorizontale.isEmpty() ? horizontal : surchargeHorizontale.get(0));
    }

    /** Les composantes d'une declaration, chacune exigee en rem : une unite qui ne se compare pas au
     * plancher est un defaut, jamais une valeur ignoree (ADR-0013 obligation 2). */
    private static List<Double> valeursEnRem(String corps, String propriete, String selecteur) {
        int position = debutDeDeclaration(corps, propriete);
        if (position < 0) {
            return List.of();
        }
        List<Double> valeurs = new ArrayList<>();
        for (String brute : motsDe(valeurDeclaree(corps, propriete))) {
            assertThat(brute)
                    .as("%s : %s doit s'exprimer en rem pour se comparer au plancher de nadia-prof",
                            selecteur, propriete)
                    .endsWith("rem");
            valeurs.add(Double.parseDouble(brute.substring(0, brute.length() - "rem".length())));
        }
        return valeurs;
    }

    static void verifierEcartEntreBoutonsDeReponse(Path racine) {
        Ecart ecart = ecartDe(blocsMaison(racine), ".propositions");
        assertThat(ecart.vertical())
                .as(".propositions : l'ecart vertical entre deux boutons de reponse doit valoir au "
                        + "moins 1rem, pour un geste d'enfant encore imprecis")
                .isGreaterThanOrEqualTo(ECART_MINIMAL_REM);
        assertThat(ecart.horizontal())
                .as(".propositions : l'ecart lateral entre deux boutons de reponse doit valoir au "
                        + "moins 1rem. C'est l'axe qui porte le geste des que les propositions sont "
                        + "cote a cote, et le plancher y est le meme (ADR-0026)")
                .isGreaterThanOrEqualTo(ECART_MINIMAL_REM);
    }

    /**
     * ADR-0026 : le vert silencieux que l'amendement ferme. Le jeu fautif declare {@code gap: 1rem}
     * - ce que la regle precedente lisait, et acceptait - puis {@code column-gap: 0.5rem}, qui
     * l'emporte au rendu. L'ecart vertical reste conforme : seul l'axe lateral est defait, donc seul
     * l'axe lateral doit faire rougir.
     */
    @Test
    void unEcartLateralSousLePlancherFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("ecart-lateral-defait");
        assertThatThrownBy(() -> verifierEcartEntreBoutonsDeReponse(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("l'ecart lateral entre deux boutons de reponse");
    }

    /**
     * L'autre forme du meme defaut : le raccourci a deux valeurs, dont la seconde gouverne l'axe
     * lateral. La regle precedente ne lisait pas cette seconde valeur - elle tombait sur une
     * exception de format plutot que sur un motif lisible.
     */
    @Test
    void unRaccourciDontLaSecondeValeurEstSousLePlancherFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("raccourci-a-deux-valeurs");
        assertThatThrownBy(() -> verifierEcartEntreBoutonsDeReponse(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("l'ecart lateral entre deux boutons de reponse");
    }

    /**
     * Relecture adverse de l'inegalite, sur le bord exact : la production declare {@code gap: 1rem},
     * c'est-a-dire la valeur du plancher elle-meme. Une inegalite stricte rejetterait l'etat que
     * nadia-prof a accepte, et aucun cas nominal ne le dirait (lecon de la seconde mutation
     * d'ADR-0025). Ce cas epingle le bord bas comme accepte, l'autre bord comme refuse.
     */
    @Test
    void unEcartExactementEgalAuPlancherEstAccepteEtUnPoilMoinsEstRefuse() {
        assertThat(ecartDe(blocsMaison(RACINE_PRODUCTION), ".propositions").horizontal())
                .as("la production est exactement au plancher : c'est ce bord que l'inegalite accepte")
                .isEqualTo(ECART_MINIMAL_REM);
        assertThatThrownBy(() ->
                verifierEcartEntreBoutonsDeReponse(CONTRE_EXEMPLES.resolve("un-poil-sous-le-plancher")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("au moins 1rem");
    }

    /**
     * S7-1/S7-2 (ADR-0026 section 4, proposition-ines-machine-a-sous.md section 5) : la largeur de
     * colonne n'est divisee en deux qu'a partir de la quatrieme proposition. Le selecteur
     * {@code .proposition:nth-child(4)} ne matche que si une quatrieme proposition existe : ce
     * test verifie que cette division-la est bien scopee a ce cas, pas que le rendu tienne
     * (propriete du rendu, hors de portee d'un automate de ce depot - ADR-0025 -, mesuree par
     * nadia-prof).
     *
     * <p>S13, voie 4 (arbitrage prealable de nadia-prof du 2026-08-16, condition VR-5 ;
     * avis-jeton-en-image.md de theo-archi) : cette methode portait aussi une assertion sur
     * {@code flex-wrap: wrap} scope au meme selecteur. Le repli en rangees ne s'active plus
     * seulement a partir de la quatrieme proposition - {@code .propositions} se replie
     * desormais toujours (voir {@link #leConteneurSeReplieOuLaColonneGardeUneBaseNonNulle}) - et
     * l'ancien bloc CSS qui portait cette assertion a ete retire. La garder aurait laisse un test
     * vert affirmant une portee qui n'existe plus ; elle est retiree, seule reste l'assertion sur
     * la largeur de colonne, qui est bien encore scopee a la quatrieme proposition.
     */
    @Test
    void laLargeurDeColonneEstDiviseeParDeuxAPartirDeLaQuatriemePropositionSeulement() {
        Map<String, String> blocs = blocsMaison(RACINE_PRODUCTION);

        String colonne = blocs.get(".propositions:has(.proposition:nth-child(4)) .proposition");
        assertThat(colonne)
                .as("chaque colonne doit passer a une largeur de moitie de rangee a partir de la "
                        + "quatrieme proposition")
                .isNotNull();
        assertThat(colonne).contains("calc(50% - 0.5rem)");
    }

    /**
     * S13, voie 4 (arbitrage prealable de nadia-prof du 2026-08-16, condition VR-6 ;
     * avis-jeton-en-image.md de theo-archi). Le defaut refuse le 2026-08-16 tient dans un seul
     * couple : un conteneur qui ne se replie jamais ({@code .propositions} sans
     * {@code flex-wrap: wrap}) combine a une colonne dont la base de flexibilite est une longueur
     * nulle ({@code .proposition}, base a 0). Ce couple force les colonnes a se partager la
     * largeur quoi qu'elles contiennent, et un {@code <button>} laisse alors son texte deborder
     * plutot que d'exiger sa place.
     *
     * <p>Ce qu'elle ne prouve pas, et il faut le dire : elle garde une cause, pas un effet.
     * ADR-0025 le rappelle, le debordement de rendu reste non gardable par un automate de ce
     * depot - seule nadia-prof, en jouant, en repond. Un meme symptome de debordement reintroduit
     * par un autre chemin - une largeur fixee en pixels, un {@code white-space: nowrap}, une
     * police agrandie - resterait vert ici : cette regle ne verifie que ce couple precis, rien de
     * plus.
     */
    static void verifierQueLeConteneurSeReplieOuQueLaColonneGardeUneBaseNonNulle(Path racine) {
        Map<String, String> blocs = blocsMaison(racine);

        String conteneur = blocs.get(".propositions");
        assertThat(conteneur).as(".propositions doit exister dans le CSS maison").isNotNull();
        int positionRepli = debutDeDeclaration(conteneur, "flex-wrap");
        boolean seReplie = positionRepli >= 0
                && valeurDeclaree(conteneur, "flex-wrap").equals("wrap");

        String colonne = blocs.get(CLASSE_PROPOSITION);
        assertThat(colonne).as(".proposition doit exister dans le CSS maison").isNotNull();
        String base = baseDeFlexibilite(colonne);

        assertThat(seReplie || !estUneLongueurNulle(base))
                .as(".propositions ne declare pas flex-wrap: wrap et .proposition fixe sa base de "
                        + "flexibilite a une longueur nulle (%s) : c'est exactement la forme du "
                        + "defaut refuse par nadia-prof le 2026-08-16 - une base nulle partage la "
                        + "largeur quoi que la colonne contienne, sur un conteneur qui ne se "
                        + "replie jamais", base)
                .isTrue();
    }

    /** La base de flexibilite (troisieme composante du raccourci {@code flex}) d'une colonne. */
    private static String baseDeFlexibilite(String corps) {
        int position = debutDeDeclaration(corps, "flex");
        assertThat(position)
                .as(".proposition doit declarer flex, qui porte la base de flexibilite de la colonne")
                .isNotNegative();
        List<String> composantes = motsDe(valeurDeclaree(corps, "flex"));
        assertThat(composantes)
                .as("flex doit s'ecrire avec ses trois composantes (croissance, retrecissement, "
                        + "base), pour qu'une base nulle ne se dissimule pas dans un raccourci a "
                        + "une ou deux valeurs")
                .hasSize(3);
        return composantes.get(2);
    }

    /**
     * Une longueur nulle, quelle que soit son unite ({@code 0}, {@code 0px}, {@code 0rem}...).
     * Un mot-cle sans prefixe numerique (par exemple {@code auto}) n'est jamais une longueur
     * nulle.
     */
    private static boolean estUneLongueurNulle(String valeur) {
        int fin = 0;
        while (fin < valeur.length()
                && (Character.isDigit(valeur.charAt(fin)) || valeur.charAt(fin) == '.')) {
            fin++;
        }
        if (fin == 0) {
            return false;
        }
        return Double.parseDouble(valeur.substring(0, fin)) == 0;
    }

    @Test
    void leConteneurSeReplieOuLaColonneGardeUneBaseNonNulle() {
        verifierQueLeConteneurSeReplieOuQueLaColonneGardeUneBaseNonNulle(RACINE_PRODUCTION);
    }

    /**
     * ADR-0013 obligation 3, epreuve de morsure : le jeu fautif n'est pas une fixture inventee
     * pour l'occasion, c'est l'etat reel du depot a 34abbb7 (avis-jeton-en-image.md de
     * theo-archi, arbitrage prealable de nadia-prof du 2026-08-16) - celui qu'elle a refuse.
     * {@code .propositions} n'y declarait jamais flex-wrap, et {@code .proposition} y fixait
     * {@code flex: 1 1 0}.
     */
    @Test
    void unConteneurRigideAvecUneColonneABaseNulleFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("conteneur-rigide-et-colonne-a-base-nulle");
        assertThatThrownBy(() -> verifierQueLeConteneurSeReplieOuQueLaColonneGardeUneBaseNonNulle(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("c'est exactement la forme du defaut refuse par nadia-prof");
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un bouton que personne n'a inscrit nulle part, habille
     * par la seule bibliotheque tierce. C'est la forme que prendrait le defaut le jour ou quelqu'un
     * s'appuierait sur Bootstrap pour habiller un bouton, et c'est le mode de defaillance que
     * l'entree d'une bibliotheque tierce rend possible.
     */
    @Test
    void unBoutonHabilleParLaSeuleBibliothequeTierceFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("bouton-sans-regle-maison");
        assertThatThrownBy(() -> verifierPlancherDeLisibilite(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("aucune regle maison");
        assertThatThrownBy(() -> verifierCibleTactile(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("aucune regle maison");
    }

    @Test
    void uneClasseTierceEnImportantFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("plancher-defait-par-le-tiers");
        assertThatThrownBy(() -> verifierQueLeTiersNeDefaitPasLesPlanchers(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("!important");
    }

    /**
     * ADR-0015, defaut releve par yanis-qa en s03 : la fixture precedente ecrit
     * {@code font-size:1rem!important} en clair, ce que Bootstrap ne fait jamais. Il ecrit
     * {@code --bs-btn-font-size:1rem;font-size:var(--bs-btn-font-size)}, et la recherche par simple
     * sous-chaine tombait sur la variable. L'objet neuf est ce motif de nommage, celui de tous les
     * composants de la bibliotheque reellement vendorisee.
     */
    @Test
    void unPlancherDefaitDerriereUneVariableTierceFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("plancher-defait-derriere-une-variable");
        assertThatThrownBy(() -> verifierQueLeTiersNeDefaitPasLesPlanchers(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("!important");
    }

    /**
     * ADR-0015, ADR-0013 obligation 3 : l'objet neuf est une branche de th:class que personne n'a
     * declaree en CSS maison, sur un bouton dont l'attribut class, lui, est irreprochable. C'est la
     * forme exacte du defaut que le perimetre precedent laissait passer en vert.
     */
    @Test
    void uneBrancheDeThClassSansRegleMaisonFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("branche-conditionnelle-sans-plancher");
        assertThatThrownBy(() -> verifierPlancherDeLisibilite(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("aucune regle maison");
        assertThatThrownBy(() -> verifierCibleTactile(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("aucune regle maison");
    }

    /**
     * ADR-0015 : une classe entierement calculee ne se lit pas, donc ne se prouve pas. Elle est
     * refusee plutot qu'ignoree - sans quoi il suffirait d'ecrire th:class="${classe}" pour sortir
     * du perimetre sans qu'aucun test ne bronche.
     */
    @Test
    void uneClasseEntierementCalculeeFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("classe-calculee");
        assertThatThrownBy(() -> verifierPlancherDeLisibilite(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("aucune classe litterale a lire");
    }
}
