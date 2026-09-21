package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.EmplacementDuLibelleDeZone;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.ReponseAEcrire;
import com.recreo.domain.content.Zone;
import com.recreo.domain.content.ZoneADesigner;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.RecordComponent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0032 : <b>deux items d'un meme paquet fini ne presentent jamais le meme ecran.</b>
 *
 * <p>Ce garde-fou ne ferme pas un defaut constate, il ferme un <b>trou de morsure</b> dans
 * {@code ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest} (ADR-0023). Cette regle-la compte des
 * {@code ItemId} distincts, et {@code QuestionContentSourceFactory} derive l'identifiant d'un item de
 * sa <b>position</b> dans le YAML : deux items rigoureusement identiques portent donc deux
 * identifiants distincts et comptent pour deux. Un paquet peut ainsi satisfaire l'inegalite stricte
 * d'ADR-0023 en se remplissant de copies, c'est-a-dire verdir la regle sans livrer la propriete pour
 * laquelle elle existe - la variete d'une partie a l'autre.</p>
 *
 * <p><b>Ce que ce trou couterait, et il ne coute pas une preference de forme.</b> {@code nadia-prof}
 * a verifie a la main, en jouant, qu'une partie ne repete jamais un item :
 * {@code squad/conformite/FICHE-bandit-manchot-additions-cp.md}, section "0 bis" point C - vingt-quatre
 * parties jouees sans jamais reussir ni jamais rater, aucune repetition. Cette propriete ne tient que
 * parce que {@code ListContentSource.next} ecarte les {@code ItemId} deja tires. Elle devient fausse,
 * en silence et sans qu'aucun diff ne le montre, des que deux items d'un meme paquet portent le meme
 * ecran : le tirage reste sans remise sur les identifiants et redevient avec remise sur ce que
 * l'enfant lit.</p>
 *
 * <p><b>Le fait qui rend ce trou pressant maintenant.</b> L'entree de catalogue de chacun des quatre
 * jeux declare {@code nombreDeManches: 10}, donc ADR-0023 exige au moins onze items distincts. Une
 * competence d'arithmetique dont l'espace d'items est du meme ordre - "Complements a 10" en admet
 * <b>onze</b> sous la forme canonique {@code a + ? = 10} pour {@code a} de 0 a 10, neuf si les deux
 * cas degeneres sont ecartes - met donc l'auteur de contenu exactement sous la pression que ce
 * garde-fou refuse : completer le paquet par une copie plutot que par une forme d'item de plus. Le
 * chemin vert restant est de faire varier l'item ou de revoir {@code nombreDeManches} (ADR-0012,
 * decision produit), jamais de dupliquer.</p>
 *
 * <p><b>Ce que "le meme ecran" veut dire, et pourquoi pas "le meme enonce".</b> L'ecran de manche
 * montre l'enonce puis les propositions ; deux items de meme enonce mais de propositions differentes
 * sont deux ecrans differents, et cette forme est legitime ("Quel nombre est le plus grand ?" avec
 * deux nombres qui changent). Un critere sur le seul enonce l'aurait refusee. L'identite retenue est
 * donc le couple <b>enonce + jeu de propositions</b>, en ensemble et non en liste parce que la
 * mecanique melange l'ordre a chaque manche ({@code BanditManchotSession.melanger}) : deux items qui
 * ne differeraient que par l'ordre sont le meme ecran. L'indice n'entre pas dans cette identite, et
 * le motif ecrit ici etait faux : il disait "il ne s'affiche qu'apres trois echecs consecutifs".
 * L'indice s'affiche <b>des le premier echec</b> sur la manche bloquante et jusqu'a sa resolution
 * (M1, {@code DEC-0004} point 4, {@code BanditManchotSession.indice()}) - constate en jouant par
 * {@code nadia-prof} au sprint 14, corrige ici. Le motif juste est le meme dans sa conclusion : un
 * indice n'apparait que sur une manche que l'enfant n'a pas encore resolue, il ne distingue donc pas
 * deux items l'un de l'autre au moment ou l'ecran se presente. Le critere n'a pas bouge d'un
 * caractere ; c'est sa justification qui portait un chiffre faux.</p>
 *
 * <p><b>ADR-0050 (s17) : cette identite est une enumeration, et elle le sait desormais.</b> Le couple
 * enonce + propositions decrit ce que l'ecran de manche montre <b>tant que</b> {@link Question} ne
 * porte que ces composantes-la. Une composante rendue de plus - un texte-support au-dessus de la
 * question (S17-2, COMP-5) - ferait juger deux ecrans differents comme un seul, et le message
 * d'erreur nommerait une cause fausse. {@link #verifierQueChaqueComposanteDUnItemEstClassee} refuse
 * desormais le silence : une composante de plus rend ce garde-fou rouge tant que personne n'a ecrit
 * si l'ecran la montre.</p>
 *
 * <p><b>Amendement du 2026-08-31 (sofia-sm, lot 5b de s24), sur la portee de cette promesse et non
 * sur le critere.</b> Le classement reflechissait sur {@code Question.class} seule. Il tenait donc sa
 * promesse pour une composante de plus sur {@link Question}, et <b>pas</b> pour les composantes d'une
 * <b>forme d'item nouvelle</b> : mesure du 2026-08-31 sur une sonde de saisie chargeable, sept
 * composantes traversent sans etre classees et la clause reste verte. Le classement derive desormais
 * de la scellure d'{@link Item} (ADR-0004). Aucun critere n'a bouge, aucune composante n'a change de
 * camp : c'est la population jugee qui etait plus etroite que la phrase qui la decrivait.</p>
 *
 * <p><b>Mesure de l'etat au 2026-08-11 (s10), avec sa methode et sa date, parce qu'un garde-fou
 * ecrit avant son defaut doit dire ce qu'il trouvait le jour ou il est entre.</b> Sur les 397 items
 * lisibles des 65 paquets du depot d'alors (les 4 paquets servis de
 * {@code adapters/adapter-content/src/main/resources/contenu/} et les 61 paquets de reserve de
 * {@code contenu-repris/}), <b>aucun couple enonce + jeu de propositions n'apparaissait deux fois</b>
 * dans un meme paquet. Methode : lecture ligne a ligne des cles {@code enonce}/{@code question},
 * {@code bonneReponse}/{@code bonne} et {@code distracteurs}, le decoupage des listes en ligne
 * respectant les guillemets ; les 429 cles {@code distracteurs} d'alors sont toutes ecrites en ligne,
 * donc aucune n'echappait a ce comptage. Cette clause est donc partie verte sur du materiau reel,
 * jamais sur une fixture ecrite pour elle - et elle l'est restee, c'est {@code mvn verify} qui le
 * dit a chaque execution, pas cette phrase.</p>
 *
 * <p><b>Ce que cette mesure disait de trop, et qui n'est plus vrai.</b> Elle ajoutait que "meme le
 * critere plus strict de l'enonce seul est tenu partout". Ce n'est plus le cas du contenu de
 * production, et le contenu qui l'a rendu faux est <b>valide</b> : {@code cm1-tables-multiplication}
 * (S13-4/S13-5) porte 99 items dont <b>16 enonces apparaissent deux fois</b>, avec le meme
 * {@code bonneReponse} et des {@code distracteurs} differents - par exemple {@code "2 x 4 = ?"} avec
 * {@code ["12", "10"]} puis avec {@code ["4", "6"]}. C'est <b>exactement la forme legitime</b> que le
 * paragraphe precedent decrit et que le critere retenu autorise a dessein : deux ecrans que l'enfant
 * ne confond pas, parce que ce qu'il compare change. La phrase supprimee n'etait pas une clause -
 * aucune assertion n'a jamais porte sur l'enonce seul - c'etait une observation de circonstance, et
 * une observation de circonstance qui survit a son contenu devient un mensonge sur ce que le
 * garde-fou garde. Mesure du 2026-08-17, methode : lecture ligne a ligne des cles
 * {@code enonce}/{@code question} des paquets servis et de {@code contenu-repris/}, comptage des
 * valeurs apparaissant plus d'une fois dans un meme fichier ; {@code cm1-tables-multiplication} est
 * le <b>seul</b> paquet du depot dans ce cas. Signale par {@code nadia-prof}, qui precise que le
 * garde-fou lui-meme est intact - il l'est, et rien de son code n'a change ici.</p>
 *
 * <p>ADR-0013, les trois obligations :</p>
 * <ul>
 *   <li><b>perimetre derive</b> : les paquets viennent du balayage de {@code contenu/}, jamais d'une
 *       liste ecrite ici, et les items viennent du <b>chargeur reel</b> - c'est la chaine que l'enfant
 *       traverse, et l'identifiant positionnel qui ouvre ce trou n'existe qu'a l'interieur d'elle ;</li>
 *   <li><b>derivation totale</b> : un paquet qui ne rend aucun item est un defaut, pas une population
 *       vide toleree. Un paquet <b>combinatoire</b> est hors perimetre, et c'est dit plutot que
 *       silencieux : la distinction de ses tirages est une propriete de son algorithme, tenue par
 *       {@code AdditionsBorneesContentSourceTest} la ou l'algorithme vit, et un tel paquet finit
 *       toujours par rendre deux fois le meme couple des que son espace est presque epuise - ce qui
 *       est licite au-dela de la longueur d'une partie et ferait rougir cette clause pour rien. Le
 *       depart entre les deux n'est pas lu dans une cle du YAML mais <b>constate</b> : une source
 *       finie s'epuise et rend {@code Optional.empty()} avant le budget de tirages ;</li>
 *   <li><b>mise a l'epreuve</b> : trois paquets fautifs neufs, dont un corrige qui doit redevenir
 *       vert, et un cas qui doit rester vert (le meme enonce avec des propositions differentes) sans
 *       lequel cette clause serait une interdiction de reposer une question deguisee en clause de
 *       non-repetition.</li>
 * </ul>
 */
class AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest {

    private static final Path CONTENU_DE_PRODUCTION = Path.of("src/main/resources");

    /** ADR-0013 : les paquets fautifs de ce garde-fou, jamais servis a un enfant. */
    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest";

    /**
     * Budget de tirages par paquet. Il sert a distinguer une source finie, qui s'epuise bien avant
     * (le plus gros paquet du depot en compte 99 items, cm1-tables-multiplication, mesure du
     * 2026-08-17), d'une source combinatoire, qui ne s'epuise jamais.
     */
    private static final int BUDGET_DE_TIRAGES = 500;

    /**
     * Ce que l'enfant voit ou entend d'un item sur l'ecran de manche : son enonce, ses
     * propositions, la part que le contenu a designee (ADR-0062), le texte-support qu'il porte
     * (ADR-0050), l'illustration qu'il montre (ADR-0073), l'ensemble de ses zones designables
     * (ADR-0080) et le texte que l'adulte lui lit a voix haute (ADR-0075). Deux items de meme enonce
     * et de memes propositions qui designent deux mots differents, qui portent deux textes-support
     * differents, qui montrent deux dessins differents, dont les zones designables different, ou
     * dont l'adulte lit deux textes differents, sont deux ecrans differents.
     *
     * <p>ADR-0075, decision 3 : l'identite d'un tour est ce qui le distingue <b>pour l'enfant</b> -
     * vu ou entendu. {@code texteAVoixHaute} n'est jamais montre a l'ecran, mais l'enfant l'entend,
     * et c'est ce qui distingue deux tours sur les lignes de la famille a voix : leur enonce et
     * leurs propositions y sont invariants par construction (VOI-13 chasse le mot travaille de ce
     * que l'enfant lit), donc seul ce texte separe deux manches.</p>
     *
     * <p>ADR-0080 : {@code zones} porte l'ensemble complet des {@link Zone} d'un item de designation
     * - id, libelle et cadre compris, l'egalite de record etant deja profonde. Deux items qui
     * montrent le meme plan et posent la meme question sont le meme ecran pour l'enfant, exactement
     * comme deux items de choix de meme enonce et memes propositions le sont ; {@code propositions}
     * reste vide sur un item de designation, comme il l'est deja sur un item de saisie.</p>
     *
     * <p>ADR-0094 : {@code emplacementDuLibelleDeZone} y entre. Deux items qui montrent le meme plan,
     * posent la meme question et designent les memes zones, mais dont l'un rend le libelle dans la
     * boite du bouton et l'autre hors d'elle (numero servi et legende), presentent un ecran
     * different pour l'enfant - le texte du bouton et la presence d'une legende changent. C'est le
     * meme motif structurel qu'{@code illustration} et {@code zones}.</p>
     */
    private record Ecran(String enonce, Set<String> propositions, Optional<String> partDesignee,
                          Optional<String> texteSupport, Optional<String> illustration,
                          Set<Zone> zones, Optional<String> texteAVoixHaute,
                          Optional<EmplacementDuLibelleDeZone> emplacementDuLibelleDeZone) {
    }

    /** ADR-0013 obligation 1 : les paquets viennent du repertoire, jamais d'une liste. */
    static List<String> paquets(Path racine) {
        Path repertoire = racine.resolve("contenu");
        if (!Files.isDirectory(repertoire)) {
            return List.of();
        }
        try (Stream<Path> arborescence = Files.walk(repertoire)) {
            return arborescence.filter(Files::isRegularFile)
                    .map(fichier -> fichier.getFileName().toString())
                    .filter(nom -> nom.endsWith(".yaml"))
                    .map(nom -> nom.substring(0, nom.length() - ".yaml".length()))
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Tous les items que ce contenu sait rendre, tires par la chaine reelle avec le tirage deja-vu
     * qui grossit. Rend vide si la source ne s'epuise pas dans le budget : elle est combinatoire, et
     * l'appelant la laisse hors perimetre.
     */
    private static Optional<List<Item>> itemsDUneSourceFinie(ContentSource contenu) {
        RandomSource hasard = new Random(13)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Item> items = new ArrayList<>();
        for (int tirage = 0; tirage < BUDGET_DE_TIRAGES; tirage++) {
            Optional<Item> tire = contenu.next(hasard, dejaVus);
            if (tire.isEmpty()) {
                return Optional.of(items);
            }
            Item item = tire.orElseThrow();
            items.add(item);
            dejaVus.add(item.id());
        }
        return Optional.empty();
    }

    /**
     * <b>Lot 5a de s24, decision 3 : ce garde-fou est etendu a l'item de saisie, et son identite
     * d'ecran est definie pour lui.</b> La propriete d'ADR-0032 ne parle pas de propositions, elle
     * parle de <b>ce que l'ecran montre</b> ; elle vaut donc mot pour mot en saisie, et la pression
     * qu'elle refuse y est plus forte - la Javadoc de cette classe nomme elle-meme les complements a
     * 10 comme le cas ou l'espace d'items est du meme ordre que le nombre de manches.
     *
     * <p><b>L'identite d'ecran d'un item de saisie est son enonce seul.</b> La forme de reference
     * n'apparait qu'apres la reponse, au verdict (SAI-8) ; les formes acceptees ne sont jamais
     * montrees ; l'exigence de forme gouverne la comparaison, pas l'affichage. Le jeu de
     * propositions est donc vide, et cela ne cree aucune collision avec un item de choix : celui-ci
     * porte toujours au moins sa bonne reponse.</p>
     *
     * <p>Le {@code switch} est exhaustif sur la scellure d'{@link Item} (ADR-0004) : une troisieme
     * forme d'item ne compilera pas tant que son ecran n'aura pas ete decrit ici. C'est le meme
     * refus du silence que la clause de classement, tenu cette fois par le compilateur.</p>
     *
     * <p><b>ADR-0080 : la troisieme forme, l'item de designation, a rejoint ce {@code switch}.</b>
     * Son identite d'ecran est son enonce, son illustration et l'ensemble complet de ses zones - la
     * demonstration exacte que la Javadoc de la classe et ADR-0080 lui-meme demandent : deux items
     * qui montrent le meme plan et posent la meme question sont le meme ecran pour l'enfant. Le jeu
     * de propositions reste vide, comme pour un item de saisie : ce n'est pas parmi des textes que
     * l'enfant choisit ici.</p>
     */
    private static Ecran ecranDe(Item item) {
        return switch (item) {
            case Question question -> {
                Set<String> propositions = new HashSet<>();
                propositions.add(question.bonneReponse());
                propositions.addAll(question.distracteurs());
                yield new Ecran(question.enonce(), Set.copyOf(propositions), question.partDesignee(),
                        question.texteSupport(), question.illustration(), Set.of(),
                        question.texteAVoixHaute(), Optional.empty());
            }
            case ReponseAEcrire reponse -> new Ecran(reponse.enonce(), Set.of(), Optional.empty(),
                    Optional.empty(), Optional.empty(), Set.of(), reponse.texteAVoixHaute(),
                    Optional.empty());
            case ZoneADesigner zone -> new Ecran(zone.enonce(), Set.of(), Optional.empty(),
                    Optional.empty(), Optional.of(zone.illustration()), Set.copyOf(zone.zones()),
                    zone.texteAVoixHaute(), Optional.of(zone.emplacementDuLibelleDeZone()));
        };
    }

    /**
     * ADR-0050, clause 2 : les composantes d'un item que l'ecran de manche montre <b>au moment ou il
     * se presente</b>, et qui font donc partie de son identite d'ecran ci-dessus.
     *
     * <p>ADR-0062 : {@code partDesignee} y entre. Deux items de meme enonce et de memes propositions
     * qui designent deux mots differents - {@code "La maitresse ecrit au tableau"} en designant
     * {@code maitresse}, puis en designant {@code ecrit} - sont deux ecrans differents et deux
     * questions differentes. Les classer hors de l'identite ferait refuser la seconde comme une
     * copie de la premiere.</p>
     *
     * <p>ADR-0050 : {@code texteSupport} y entre pour le meme motif. Deux items de meme enonce et de
     * memes propositions poses sur deux textes-support differents - "Que ressent le personnage ?"
     * avec les memes trois choix, une fois sur un texte et une fois sur un autre - sont deux ecrans
     * differents : le support fait partie de ce que l'enfant lit au moment ou l'ecran se presente.</p>
     *
     * <p>ADR-0073 : {@code illustration} y entre, et c'est structurel plutot que prudent. Un paquet
     * illustre pose la meme question sur des dessins differents - meme enonce, memes propositions,
     * un dessin par item : la classer hors de l'identite refuserait tous les items sauf le premier
     * comme des copies, alors que l'enfant voit un ecran different a chaque fois. C'est exactement le
     * faux rouge que la clause de classement decrit pour le texte-support, et il serait ici la regle
     * et non le cas limite.</p>
     *
     * <p>ADR-0075, decision 3 : {@code texteAVoixHaute} y entre, et le motif elargit celui du texte-
     * support et de l'illustration a ce que l'enfant <b>entend</b>. Deux items de meme enonce et de
     * memes propositions dont l'adulte lit deux textes differents sont deux ecrans differents - c'est
     * la forme ordinaire des cinq paquets de la famille a voix, ou l'enonce et les propositions sont
     * invariants par construction (VOI-13 chasse le mot travaille de ce que l'enfant lit) : les
     * classer hors de l'identite refuserait tous les items d'un tel paquet sauf le premier comme des
     * copies.</p>
     *
     * <p>ADR-0080 : {@code zones} y entre, meme motif structurel qu'{@code illustration}. Deux items
     * de designation de meme enonce et de meme illustration mais de zones differentes montrent des
     * boutons differents, a des endroits differents : les classer hors de l'identite refuserait tous
     * les items sauf le premier comme des copies, alors que l'enfant voit un ecran different a chaque
     * fois.</p>
     *
     * <p>ADR-0094 : {@code emplacementDuLibelleDeZone} y entre, meme motif structurel. Deux items de
     * designation de meme enonce, meme illustration et memes zones, dont l'un rend le libelle dans la
     * boite du bouton et l'autre hors d'elle, montrent des boutons dont le texte differe (le libelle,
     * ou un numero de repere) et l'un porte une legende que l'autre ne porte pas : deux ecrans
     * differents pour l'enfant.</p>
     */
    private static final Set<String> COMPOSANTES_DANS_L_IDENTITE_D_ECRAN =
            Set.of("enonce", "bonneReponse", "distracteurs", "partDesignee", "texteSupport",
                    "illustration", "zones", "texteAVoixHaute", "emplacementDuLibelleDeZone");

    /**
     * ADR-0050, clause 2 : les composantes d'un item qui n'entrent pas dans son identite d'ecran, et
     * le motif de chacune. Ce ne sont pas des exemptions de confort : chacune dit pourquoi deux items
     * qui ne differeraient que par elle presentent quand meme le meme ecran.
     */
    private static final Map<String, String> COMPOSANTES_HORS_IDENTITE_D_ECRAN = Map.of(
            "id", "il est derive de la position dans le YAML : deux items rigoureusement identiques en"
                    + " portent deux distincts, et c'est exactement le trou que ce garde-fou ferme",
            "indice", "il ne s'affiche que sur une manche que l'enfant n'a pas encore resolue, jamais"
                    + " au moment ou l'ecran se presente",
            "explication", "elle ne s'affiche qu'avec la revelation, apres trois echecs, jamais au"
                    + " moment ou l'ecran se presente",
            "formeDeReference", "elle n'apparait qu'apres la reponse, au verdict, quand la mecanique"
                    + " montre la forme attendue (SAI-8) : l'enfant ne l'a jamais sous les yeux au"
                    + " moment ou il repond, et deux items qui ne differeraient que par elle lui"
                    + " presentent bien le meme ecran",
            "formesAcceptees", "elles ne sont jamais montrees : c'est le bareme de l'exercice, ce a"
                    + " quoi la mecanique compare ce que l'enfant ecrit. Deux items de meme enonce"
                    + " dont seules les tolerances differeraient sont, pour l'enfant, la meme manche"
                    + " posee deux fois",
            "exigenceDeForme", "elle gouverne la comparaison, jamais l'affichage : rien a l'ecran ne"
                    + " dit a l'enfant si son accent comptera. Ce fait est lui-meme une propriete que"
                    + " nadia-prof juge en jouant (SAI-2), pas une composante d'ecran",
            "illustrationTexteAlternatif", "il n'est jamais montre : c'est ce qu'un lecteur d'ecran"
                    + " annonce de l'image, et l'image, elle, est deja dans l'identite par son"
                    + " identifiant (ADR-0073 clause 5). Deux items de meme enonce, de memes"
                    + " propositions et de meme dessin presentent le meme ecran quel que soit le"
                    + " libelle de ce texte - le classer dans l'identite laisserait un mot de plus"
                    + " dans l'alternatif suffire a faire passer une repetition d'ecran",
            "bonneZone", "elle n'ajoute aucune information visible : la zone qu'elle designe fait deja"
                    + " partie de l'ensemble 'zones', montre integralement des que l'ecran se presente"
                    + " (ADR-0080). Deux items de designation de meme enonce, meme illustration et"
                    + " memes zones qui ne differeraient que par leur bonneZone montrent exactement les"
                    + " memes boutons aux memes endroits - le meme ecran pour l'enfant, qui ne voit"
                    + " laquelle est correcte qu'en jouant");

    /**
     * <b>ADR-0050, clause 2 : aucune composante d'un item n'echappe au classement de l'identite
     * d'ecran.</b>
     *
     * <p>L'identite retenue par ADR-0032 - {@code enonce} + jeu de propositions - est une
     * <b>enumeration ecrite a la main</b> de ce que l'ecran de manche montre. Elle etait exacte le
     * jour ou elle a ete ecrite, et elle le reste tant que {@link Question} ne porte que ces
     * composantes-la. Le jour ou un champ de plus est rendu a l'enfant sur le meme ecran - un
     * texte-support au-dessus de la question, par exemple (S17-2, COMP-5) - cette identite juge deux
     * ecrans differents comme un seul : deux items poses sur deux textes distincts, dont la question
     * et les propositions coincident ("Que ressent le personnage ?" avec les memes trois choix), sont
     * alors refuses comme une copie. La defaillance est un <b>faux rouge</b>, donc visible, mais son
     * message nomme une cause fausse et pousse l'auteur a reecrire du contenu qui n'a rien.</p>
     *
     * <p>Cette clause ne devine pas ce que le gabarit rend - il vit dans un autre module, et deduire
     * "cette composante est a l'ecran" d'un nom d'attribut de modele serait une devinette. Elle fait
     * la seule chose qu'un test puisse faire ici, et elle la fait entierement : <b>elle refuse le
     * silence</b>. Une composante de plus sur {@link Question} rend ce test rouge tant que personne
     * n'a ecrit, dans l'un des deux ensembles ci-dessus, si l'ecran la montre. C'est un classement a
     * ecrire, relu comme n'importe quelle ligne de diff, la ou aujourd'hui l'omission ne laisse
     * aucune trace.</p>
     *
     * <p><b>Ce qu'elle n'empeche pas, dit sans l'habiller</b> : elle ne verifie pas que
     * {@link #ecranDe} lit vraiment les composantes classees a l'ecran. Un auteur qui classerait a
     * tort une composante rendue dans {@link #COMPOSANTES_HORS_IDENTITE_D_ECRAN} la verrait verte.
     * Il aurait alors ecrit une affirmation fausse et signee, pas oublie quelque chose - la
     * difference que cette clause achete.</p>
     */
    static void verifierQueChaqueComposanteDUnItemEstClassee(
            Set<String> dansLIdentite, Set<String> horsIdentite) {
        verifierQueChaqueComposanteDUnItemEstClassee(
                dansLIdentite, horsIdentite, Item.class.getPermittedSubclasses());
    }

    /**
     * <b>Lot 5a de s24, decision 3 : le classement porte sur toutes les formes permises par
     * {@link Item}, jamais sur {@link Question} seule.</b>
     *
     * <p>La clause ci-dessus promettait qu'"une composante de plus rend ce garde-fou rouge tant que
     * personne n'a ecrit si l'ecran la montre". Elle tenait cette promesse par reflexion sur
     * {@code Question.class} <b>uniquement</b>. Une composante de plus portee par une <b>forme
     * d'item nouvelle</b> n'etait donc classee par personne, et la clause restait <b>verte</b> :
     * mesure du 2026-08-31 sur une sonde de saisie chargeable, cette classe rougit par transtypage
     * et jamais par cette clause-la. C'est un vert silencieux, de la meme famille que l'omission par
     * enumeration de types que ce depot a deja payee, et la promesse etait donc plus large que ce
     * que le code tenait.</p>
     *
     * <p>Les formes sont derivees de la <b>scellure</b> d'{@link Item} (ADR-0004), jamais enumerees
     * ici : la deuxieme forme d'item la fait rougir le jour ou elle entre, et la troisieme aussi -
     * l'operation posee est deja nommee par ADR-0058. Le parametre n'existe que pour la mise a
     * l'epreuve : c'est le seul moyen de prouver que cette derivation voit plus que
     * {@link Question}, tant qu'une seule forme est permise.</p>
     */
    static void verifierQueChaqueComposanteDUnItemEstClassee(
            Set<String> dansLIdentite, Set<String> horsIdentite, Class<?>[] formesDItem) {
        Set<String> communes = new TreeSet<>(dansLIdentite);
        communes.retainAll(horsIdentite);
        assertThat(communes)
                .as("une composante ne peut pas etre a la fois dans l'identite d'ecran et hors d'elle")
                .isEmpty();

        assertThat(formesDItem)
                .as("ADR-0050 : ce classement ne prouve rien s'il ne voit aucune forme d'item. Item"
                        + " est scelle (ADR-0004) et permet au moins une forme")
                .isNotEmpty();

        Set<String> classees = new TreeSet<>(dansLIdentite);
        classees.addAll(horsIdentite);
        Set<String> portees = new TreeSet<>();
        for (Class<?> forme : formesDItem) {
            RecordComponent[] composantes = forme.getRecordComponents();
            assertThat(composantes)
                    .as("%s : une forme d'item qui n'est pas un record ne rend pas ses composantes"
                            + " lisibles, et ce classement ne peut alors rien affirmer d'elle",
                            forme.getSimpleName())
                    .isNotNull();
            Arrays.stream(composantes).map(RecordComponent::getName).forEach(portees::add);
        }

        assertThat(classees)
                .as("ADR-0050 : chaque composante de chaque forme permise par Item est classee - dans"
                        + " l'identite d'ecran"
                        + " d'ADR-0032, ou hors d'elle avec son motif. Une composante portee et non"
                        + " classee est une composante dont personne n'a dit si l'enfant la voit au"
                        + " moment ou l'ecran se presente : si elle est rendue, ecranDe doit la lire"
                        + " dans le meme commit, sans quoi deux ecrans differents seront refuses comme"
                        + " une copie ; si elle ne l'est pas, son motif s'ecrit a cote des trois"
                        + " autres. Une composante classee et non portee est l'inverse - un classement"
                        + " qui a survecu a son champ")
                .isEqualTo(portees);
    }

    @Test
    void chaqueComposanteDUnItemEstClasseeDansLIdentiteDEcranOuHorsDElle() {
        verifierQueChaqueComposanteDUnItemEstClassee(
                COMPOSANTES_DANS_L_IDENTITE_D_ECRAN, COMPOSANTES_HORS_IDENTITE_D_ECRAN.keySet());
    }

    /**
     * ADR-0013 obligation 3 : la morsure est prouvee sur le <b>vrai record</b>, avec un classement
     * ampute d'un nom - la forme exacte que prendrait l'oubli le jour ou une composante de plus
     * entrerait dans {@link Question}. Muter le classement plutot qu'inventer un faux record garde
     * l'objet juge identique a celui de la production : une fixture de ma main ressemblerait a la
     * composante que j'imagine, pas a celle que quelqu'un ajoutera.
     */
    @Test
    void uneComposanteQueLeClassementNeNommePasFaitEchouerLeGardeFou() {
        Set<String> horsIdentiteAmpute = new TreeSet<>(COMPOSANTES_HORS_IDENTITE_D_ECRAN.keySet());
        horsIdentiteAmpute.remove("explication");
        assertThatThrownBy(() -> verifierQueChaqueComposanteDUnItemEstClassee(
                COMPOSANTES_DANS_L_IDENTITE_D_ECRAN, horsIdentiteAmpute))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("explication");
    }

    /**
     * Le meme classement augmente d'un nom que {@link Question} ne porte pas doit rougir aussi : une
     * liste qui ne peut que grandir finirait par decrire un record disparu.
     */
    @Test
    void unClassementQuiSurvitASonChampFaitEchouerLeGardeFou() {
        Set<String> horsIdentiteEnTrop = new TreeSet<>(COMPOSANTES_HORS_IDENTITE_D_ECRAN.keySet());
        horsIdentiteEnTrop.add("graphie");
        assertThatThrownBy(() -> verifierQueChaqueComposanteDUnItemEstClassee(
                COMPOSANTES_DANS_L_IDENTITE_D_ECRAN, horsIdentiteEnTrop))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("graphie");
    }

    /**
     * Jeu fautif du lot 5b : une seconde forme d'item, jamais livree, qui porte deux composantes que
     * le classement d'ADR-0050 ne nomme pas. Elle n'implemente pas {@link Item} - la scellure
     * l'interdit, et c'est bien ce qu'on veut - mais la derivation ne lit d'une forme que ses
     * composantes de record, donc l'objet juge est de la meme nature que celui de la production.
     */
    private record SecondeFormeDItemContreExemple(ItemId id, String enonce, String formeDeReference,
                                                   List<String> formesAcceptees) {
    }

    /**
     * ADR-0013 obligation 3, pour la decision 3 du lot 5a : sans cette clause, "le classement ne voit
     * que Question" et "le classement voit toutes les formes" seraient <b>le meme vert</b> tant
     * qu'une seule forme est permise. C'est exactement la forme du defaut mesure le 2026-08-31 : la
     * clause promettait de rougir sur une composante de plus et restait verte sur les composantes
     * d'une forme neuve.
     */
    @Test
    void uneComposanteDUneSecondeFormeDItemQueLeClassementNeNommePasFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQueChaqueComposanteDUnItemEstClassee(
                COMPOSANTES_DANS_L_IDENTITE_D_ECRAN, COMPOSANTES_HORS_IDENTITE_D_ECRAN.keySet(),
                new Class<?>[] {Question.class, SecondeFormeDItemContreExemple.class}))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("formeDeReference")
                .hasMessageContaining("formesAcceptees");
    }

    /**
     * L'autre moitie de la morsure : la derivation part bien de la scellure d'{@link Item} et non
     * d'un nom ecrit ici. Une forme permise que ce fichier ignorerait rendrait la clause muette sur
     * elle, ce qui est le defaut que la clause precedente eprouve.
     */
    @Test
    void lesFormesDItemJugeesSontCellesQueLaScellureDItemPermet() {
        assertThat(Item.class.getPermittedSubclasses())
                .as("ADR-0004 : Item est scelle, et c'est cette liste-la qui donne les formes a"
                        + " classer - jamais une enumeration ecrite dans un test")
                .isNotEmpty()
                .contains(Question.class);
    }

    static void verifierQuAucunItemNeRepeteLEcranDUnAutre(Path racine, String racineClasspath) {
        List<String> paquets = paquets(racine);
        assertThat(paquets)
                .as("ce garde-fou ne prouve rien s'il ne trouve aucun paquet sous %s", racine)
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        List<String> paquetsFinisJuges = new ArrayList<>();

        for (String paquet : paquets) {
            ContentSource contenu = new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet);
            Optional<List<Item>> finis = itemsDUneSourceFinie(contenu);
            if (finis.isEmpty()) {
                continue;
            }
            List<Item> items = finis.orElseThrow();
            assertThat(items)
                    .as("%s : un paquet qui ne rend aucun item ne peut rien promettre", paquet)
                    .isNotEmpty();
            paquetsFinisJuges.add(paquet);

            Map<Ecran, String> premierPorteur = new HashMap<>();
            for (Item item : items) {
                Ecran ecran = ecranDe(item);
                String precedent = premierPorteur.putIfAbsent(ecran, item.id().value());
                if (precedent != null) {
                    defauts.add("paquet " + paquet + ", item " + item.id().value() + " : il presente le"
                            + " meme ecran que l'item " + precedent + " - meme enonce \"" + ecran.enonce()
                            + "\" et meme jeu de propositions. Le tirage reste sans remise sur les"
                            + " identifiants, qui sont derives de la position dans le YAML, et redevient"
                            + " donc avec remise sur ce que l'enfant lit : une partie peut poser deux"
                            + " fois cette manche, ce que nadia-prof a verifie a la main comme ne devant"
                            + " jamais arriver. Ces deux items comptent pourtant pour deux dans"
                            + " ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest, qui compte des"
                            + " identifiants : completer un paquet trop court par une copie le verdit"
                            + " sans livrer la variete pour laquelle il existe. Les deux sorties sont de"
                            + " faire varier l'item, ou de revoir nombreDeManches dans le catalogue -"
                            + " decision produit, ADR-0012 (ADR-0032)");
                }
            }
        }

        assertThat(paquetsFinisJuges)
                .as("ADR-0003 : ce garde-fou ne prouve rien si aucun paquet de %s n'est une source"
                        + " finie. Un paquet combinatoire est hors perimetre par construction, et le"
                        + " jour ou le depot n'en servirait plus que de ce genre, cette clause"
                        + " n'examinerait plus rien - c'est ce message qui le dirait", racine)
                .isNotEmpty();
        assertThat(defauts)
                .as("ADR-0032 : deux items d'un meme paquet fini ne presentent jamais le meme ecran")
                .isEmpty();
    }

    @Test
    void aucunItemDeProductionNeRepeteLEcranDUnAutreItemDeSonPaquet() {
        verifierQuAucunItemNeRepeteLEcranDUnAutre(CONTENU_DE_PRODUCTION, "");
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un paquet complete par une copie exacte, la forme
     * exacte que prendrait le defaut le jour ou un auteur remplirait un paquet trop court pour son
     * nombre de manches.
     */
    @Test
    void unPaquetCompleteParUneCopieFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQuAucunItemNeRepeteLEcranDUnAutre(
                CONTRE_EXEMPLE.resolve("copie-exacte"), CONTRE_EXEMPLE_CLASSPATH + "/copie-exacte"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-complete-par-une-copie")
                .hasMessageContaining("presente le meme ecran que l'item item-1");
    }

    /**
     * La copie ne se voit pas a l'ordre des propositions : la mecanique les melange a chaque manche,
     * deux items qui ne differeraient que par cet ordre sont le meme ecran pour l'enfant. Sans ce cas,
     * une identite ecrite en liste plutot qu'en ensemble laisserait passer la copie la plus facile a
     * ecrire - permuter deux distracteurs.
     */
    @Test
    void unePermutationDesPropositionsNeSuffitPasAFaireUnItemDeplusFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQuAucunItemNeRepeteLEcranDUnAutre(
                CONTRE_EXEMPLE.resolve("propositions-permutees"),
                CONTRE_EXEMPLE_CLASSPATH + "/propositions-permutees"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-aux-propositions-permutees");
    }

    /**
     * ADR-0013 obligation 3, pour l'extension du lot 5a a l'item de saisie : deux items de saisie qui
     * posent le meme enonce presentent le meme ecran, <b>meme si leur forme de reference et leurs
     * formes acceptees different</b> - l'enfant ne voit ni l'une ni les autres au moment ou il
     * repond. Sans ce cas, l'extension d'{@link #ecranDe} a la seconde forme d'item serait du code
     * que rien n'exerce, et la nouvelle branche du {@code switch} pourrait rendre n'importe quoi.
     */
    @Test
    void unPaquetDeSaisieQuiPoseDeuxFoisLeMemeEnonceFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierQuAucunItemNeRepeteLEcranDUnAutre(
                CONTRE_EXEMPLE.resolve("saisie-meme-enonce"),
                CONTRE_EXEMPLE_CLASSPATH + "/saisie-meme-enonce"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-saisie-au-meme-enonce")
                .hasMessageContaining("presente le meme ecran que l'item")
                .hasMessageContaining("Enonce de fixture partage");
    }

    /**
     * Le meme paquet de saisie, ses deux enonces distincts, ne doit plus rien reprocher : sans lui,
     * "l'extension refuse la repetition" et "l'extension refuse tout paquet de saisie" seraient le
     * meme rouge.
     */
    @Test
    void leMemePaquetDeSaisieAuxDeuxEnoncesDistinctsPasseLeGardeFou() {
        verifierQuAucunItemNeRepeteLEcranDUnAutre(
                CONTRE_EXEMPLE.resolve("saisie-meme-enonce/corrige"),
                CONTRE_EXEMPLE_CLASSPATH + "/saisie-meme-enonce/corrige");
    }

    /** Le meme paquet, sa copie remplacee par un item de plus, ne doit plus rien reprocher. */
    @Test
    void leMemePaquetAvecUnItemDePlusAuLieuDUneCopiePasseLeGardeFou() {
        verifierQuAucunItemNeRepeteLEcranDUnAutre(
                CONTRE_EXEMPLE.resolve("copie-exacte/corrige"),
                CONTRE_EXEMPLE_CLASSPATH + "/copie-exacte/corrige");
    }

    /**
     * Le cas qui doit rester vert, et sans lequel cette clause serait une interdiction de reposer une
     * question deguisee en clause de non-repetition : le meme enonce, des propositions differentes -
     * "Quel nombre est le plus grand ?" pose sur deux couples de nombres. C'est la lecon de la seconde
     * mutation d'ADR-0025, et c'est ce cas qui justifie que l'identite retenue ne soit pas l'enonce
     * seul.
     */
    @Test
    void leMemeEnonceAvecDesPropositionsDifferentesResteVert() {
        verifierQuAucunItemNeRepeteLEcranDUnAutre(
                CONTRE_EXEMPLE.resolve("meme-enonce-propositions-differentes"),
                CONTRE_EXEMPLE_CLASSPATH + "/meme-enonce-propositions-differentes");
    }

    /**
     * ADR-0062 : le meme enonce et le meme jeu de propositions, deux parts designees differentes -
     * "Le chat noir dort." en designant "chat", puis en designant "dort". Sans {@code partDesignee}
     * dans l'identite d'ecran, ce paquet serait refuse comme une copie ; ce sont deux ecrans
     * differents, et ce test doit rester vert.
     */
    @Test
    void leMemeEnonceEtLesMemesPropositionsAvecDesPartsDesigneesDifferentesRestentVert() {
        verifierQuAucunItemNeRepeteLEcranDUnAutre(
                CONTRE_EXEMPLE.resolve("meme-enonce-et-propositions-parts-designees-differentes"),
                CONTRE_EXEMPLE_CLASSPATH + "/meme-enonce-et-propositions-parts-designees-differentes");
    }

    /**
     * ADR-0073 : le meme enonce et le meme jeu de propositions, deux dessins differents - c'est la
     * forme ordinaire d'un paquet illustre, ou la question ne change pas d'un item a l'autre et ou
     * seul le dessin change. Sans {@code illustration} dans l'identite d'ecran, un tel paquet serait
     * refuse en entier des le second item ; ce sont deux ecrans differents, et ce test doit rester
     * vert.
     */
    @Test
    void leMemeEnonceEtLesMemesPropositionsAvecDesIllustrationsDifferentesRestentVert() {
        verifierQuAucunItemNeRepeteLEcranDUnAutre(
                CONTRE_EXEMPLE.resolve("meme-enonce-et-propositions-illustrations-differentes"),
                CONTRE_EXEMPLE_CLASSPATH + "/meme-enonce-et-propositions-illustrations-differentes");
    }

    /**
     * ADR-0050 : le meme enonce et le meme jeu de propositions, deux textes-support differents -
     * "Que ressent le personnage ?" pose une fois sur un texte, une fois sur un autre. Sans
     * {@code texteSupport} dans l'identite d'ecran, ce paquet serait refuse comme une copie ; ce
     * sont deux ecrans differents, et ce test doit rester vert.
     */
    @Test
    void leMemeEnonceEtLesMemesPropositionsAvecDesTextesSupportDifferentsRestentVert() {
        verifierQuAucunItemNeRepeteLEcranDUnAutre(
                CONTRE_EXEMPLE.resolve("meme-enonce-et-propositions-textes-support-differents"),
                CONTRE_EXEMPLE_CLASSPATH + "/meme-enonce-et-propositions-textes-support-differents");
    }

    /**
     * ADR-0075, decision 3 : le meme enonce et le meme jeu de propositions, deux textes que l'adulte
     * lit a voix haute - la forme ordinaire d'un paquet de la famille a voix, ou VOI-13 chasse le mot
     * travaille de ce que l'enfant lit et laisse l'enonce et les propositions invariants. Sans
     * {@code texteAVoixHaute} dans l'identite d'ecran, ce paquet serait refuse comme une copie apres
     * son premier item ; ce sont deux ecrans differents pour l'enfant, qui les entend l'un apres
     * l'autre, et ce test doit rester vert.
     */
    @Test
    void leMemeEnonceEtLesMemesPropositionsAvecDesTextesAVoixHauteDifferentsRestentVert() {
        verifierQuAucunItemNeRepeteLEcranDUnAutre(
                CONTRE_EXEMPLE.resolve("meme-enonce-et-propositions-textes-a-voix-haute-differents"),
                CONTRE_EXEMPLE_CLASSPATH + "/meme-enonce-et-propositions-textes-a-voix-haute-differents");
    }

    /**
     * ADR-0075, decision 5 : le meme enonce de saisie, deux textes que l'adulte lit a voix haute -
     * la forme ordinaire de la ligne "ecrire un mot en autonomie", ou l'enonce d'un item de saisie
     * est deja son identite d'ecran a lui seul (lot 5a de s24) et resterait invariant sur tout le
     * paquet. Sans {@code texteAVoixHaute} dans cette identite, ce paquet serait refuse comme une
     * copie apres son premier item ; ce sont deux ecrans differents, et ce test doit rester vert.
     */
    @Test
    void leMemeEnonceDeSaisieAvecDesTextesAVoixHauteDifferentsResteVert() {
        verifierQuAucunItemNeRepeteLEcranDUnAutre(
                CONTRE_EXEMPLE.resolve("saisie-textes-a-voix-haute-differents"),
                CONTRE_EXEMPLE_CLASSPATH + "/saisie-textes-a-voix-haute-differents");
    }

    /**
     * ADR-0003 : l'exclusion des paquets combinatoires est declaree, jamais silencieuse. Un depot qui
     * ne servirait que ce genre de paquet ferait echouer la clause de non-vacuite au lieu d'etre vert
     * sans rien examiner.
     */
    @Test
    void unPerimetreSansAucuneSourceFinieFaitEchouerLaClauseDeNonVacuite() {
        assertThatThrownBy(() -> verifierQuAucunItemNeRepeteLEcranDUnAutre(
                CONTRE_EXEMPLE.resolve("seulement-combinatoire"),
                CONTRE_EXEMPLE_CLASSPATH + "/seulement-combinatoire"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("aucun paquet")
                .hasMessageContaining("n'est une source finie");
    }
}
