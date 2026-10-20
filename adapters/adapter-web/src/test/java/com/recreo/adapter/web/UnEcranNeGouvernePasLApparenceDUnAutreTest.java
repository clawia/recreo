package com.recreo.adapter.web;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.Regle;
import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.classesDuSelecteur;
import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.reglesDe;
import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.reglesMaison;
import static com.recreo.adapter.web.LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.documents;
import static com.recreo.adapter.web.LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.zoneEchangee;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0031 : <b>aucune regle maison ne reunit dans un meme selecteur deux jetons que le serveur ne
 * peut jamais rendre ensemble.</b> C'est la forme structurelle de la condition C4 de
 * {@code nadia-prof} - "la machine ne dit jamais le resultat : le cabinet est rigoureusement
 * identique apres une bonne et apres une mauvaise reponse" (arbitrage prealable du 2026-08-11,
 * section 7).
 *
 * <p><b>Pourquoi cette forme, et pas une clause qui nomme le cabinet.</b> ADR-0030 avait refuse
 * d'ecrire C4 en test parce que la seule forme alors disponible etait d'enumerer
 * {@code .cabinet, .signal.est-juste, .signal.est-faux} - un perimetre enumere (ADR-0013), et des
 * noms cites dans un test alors que le code ne les portait pas encore (dette n°1 de CLAUDE.md).
 * Ce fichier ne cite aucun de ces noms. Il constate ce que le depot dit deja : la zone echangee
 * recoit <b>un</b> ecran a la fois, donc les jetons de deux ecrans inseres ne coexistent jamais dans
 * un document. Un selecteur qui melange le verdict d'un ecran et l'habillage d'un autre ne peut donc
 * ni s'appliquer ni etre innocent : il est soit du code mort, soit la trace d'une intention que la
 * structure interdit. Les deux valent un rouge.
 *
 * <p><b>Ce que cette clause interdit reellement.</b> La tentation nommee par {@code nadia-prof} -
 * "dans une machine a sous, l'etat de la machine <b>est</b> le resultat" - s'ecrit en une ligne de
 * feuille : {@code body:has(.est-juste) .cabinet-bandeau { background-color: gold }}. Le depot
 * possede deja la technique ({@code body:has(...)} porte le masquage d'ADR-0022) et deja le
 * marqueur ({@code est-juste} / {@code est-faux}). Cette ligne-la echoue desormais, et toute sa
 * famille avec elle - descendance, {@code >}, {@code +}, {@code ~}, {@code :has()}, n'importe quel
 * combinateur : ce qui est juge est l'ensemble des jetons nommes, jamais la forme du selecteur.
 * La mesure de morsure est au tableau d'ADR-0031.
 *
 * <p><b>Ce qu'elle ne garde pas, et qui reste a qui.</b> L'identite des <b>pixels</b> de deux ecrans
 * rendus reste a {@code nadia-prof} (ADR-0025) : aucun automate de ce depot ne rend une page. Le
 * couplage du verdict a un element du <b>meme</b> ecran n'est pas jugeable ici et ne doit pas
 * l'etre - {@code .signal.est-juste .icone-signal} est la coche que l'enfant attend, et rien ne
 * distingue structurellement cette regle-la d'une regle qui colorerait l'explication du meme
 * ecran. C'est pourquoi la neutralite de {@code .explication} reste gardee nommement par
 * {@code CabinetDeLaMachineTest.lExplicationResteNeutreQuelQueSoitLeVerdict} (S8-3), qui enumere,
 * et qui a raison d'enumerer la ou aucune structure ne tranche. Enfin, un verdict passe par le
 * <b>gabarit</b> plutot que par la feuille - une classe conditionnelle posee sur l'habillage a
 * partir d'une variable de modele - n'est pas vu ici : c'est la dette residuelle nommee dans
 * ADR-0031.
 *
 * <p><b>Amendement du 2026-10-16 : un cas du meme ecran est jugeable, celui du sujet hors du
 * message</b> (condition {@code C1'} point 1, {@code S57-GARDE-C1-SUJET-HORS-MESSAGE}). La phrase
 * ci-dessus reste vraie de la coche et de l'explication, qui vivent toutes deux dans le message.
 * Elle ne l'est pas d'une regle dont le <b>sujet</b> est hors du message et qui nomme ce que seul le
 * message pose : le meuble ou ce qui est au-dessus de lui dependrait alors de la presence du message
 * ou de son verdict. Le message d'un ecran est sa region d'annonce ({@code role="status"}) ; ce qu'il
 * pose est derive du gabarit, ce qu'il est seul a poser l'est du reste du document, coquille
 * permanente comprise. <b>Ce que cette clause ne garde pas</b> : {@code data-oeuvre-revelee}, que
 * {@code C1'} nomme, est pose dans le meuble et non dans le message, et une regle de la feuille le lit
 * legitimement sur la case (ADR-0104) : sa lecture reste au diff ({@code TP6}). Les points 2 et 3 de
 * {@code C1'} (la disposition du message ne lit aucun etat ; aucun element ne couvre la rangee du
 * message et une rangee au-dessus) ne sont pas gardes ici. Un ecran dont le message perdrait sa
 * region d'annonce sortirait de ce controle sans rouge, tant qu'un autre ecran la garde.
 *
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li><b>perimetre derive</b> : les ecrans viennent du repertoire de gabarits et la coquille est
 *       identifiee par le fait qu'elle contient la zone {@code hx-target} - meme derivation
 *       qu'ADR-0022, partagee plutot que recopiee. Les jetons viennent du balisage et des selecteurs
 *       maison. Aucun nom de classe, de fragment ni de marqueur n'est ecrit dans ce fichier, a une
 *       exception pres : l'exemple de la decision 1 d'ADR-0031, epingle nommement pour qu'un
 *       changement de jetons ne le fasse pas disparaitre sans rouge (amendement du 2026-10-20). Un
 *       ecran neuf, un habillage neuf ou un marqueur neuf entrent dans le controle le jour ou le
 *       gabarit les pose ;</li>
 *   <li><b>derivation totale</b> : aucun gabarit, aucune zone, plusieurs zones, aucune coquille,
 *       plusieurs coquilles, aucun ecran insere, aucune regle maison, ou aucune regle qui nomme deux
 *       jetons sont des <b>echecs</b>, jamais des cas ignores. Un jeton qu'aucun ecran ne peut poser
 *       est un echec lui aussi : un selecteur qui le nomme ne peut pas s'appliquer davantage ;</li>
 *   <li><b>mise a l'epreuve</b> : deux jeux d'artefacts neufs, un depot fictif dont le verdict
 *       colore la machine, et le meme corrige - et par mutation reelle de la feuille livree
 *       (ADR-0031).</li>
 * </ul>
 */
class UnEcranNeGouvernePasLApparenceDUnAutreTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLES =
            Path.of("src/test/resources/contreexemple/UnEcranNeGouvernePasLApparenceDUnAutreTest");

    /** Les attributs par lesquels une classe arrive au rendu, en clair ou par Thymeleaf. */
    private static final List<String> PORTEURS_DE_CLASSE = List.of("th:class", "th:classappend");

    private static final String PREFIXE_THYMELEAF = "th:";

    /**
     * Ce qu'un selecteur nomme et ce qu'un ecran pose : une classe, ou la valeur d'un attribut. Les
     * deux genres sont portes par le meme type mais jamais confondus - une valeur d'attribut qui
     * ressemble a un nom de classe ne satisfait pas un selecteur de classe.
     */
    private record Jeton(String genre, String valeur) {

        private static final String CLASSE = "la classe";
        private static final String VALEUR_D_ATTRIBUT = "la valeur d'attribut";

        static Jeton classe(String nom) {
            return new Jeton(CLASSE, nom);
        }

        static Jeton valeurDAttribut(String valeur) {
            return new Jeton(VALEUR_D_ATTRIBUT, valeur);
        }

        @Override
        public String toString() {
            return genre + " \"" + valeur + "\"";
        }
    }

    /**
     * Un ecran : ce que le serveur peut afficher d'un seul coup dans la zone echangee. La coquille
     * n'en est pas un - elle survit a tous les echanges (DEC-0012, S6-1) - et ses jetons entrent donc
     * dans chaque document.
     */
    private record Ecran(String nom, Set<Jeton> jetons) {
    }

    /** La coquille permanente et les ecrans qu'on insere dedans, un a la fois. */
    private record Composition(Set<Jeton> permanents, List<Ecran> ecrans) {

        /** Les jetons de chaque document que le serveur peut reellement produire. */
        List<Ecran> documentsJouables() {
            return ecrans.stream()
                    .map(ecran -> {
                        Set<Jeton> jetons = new LinkedHashSet<>(permanents);
                        jetons.addAll(ecran.jetons());
                        return new Ecran(ecran.nom(), jetons);
                    })
                    .toList();
        }

        /** Les ecrans qui peuvent poser ce jeton, coquille comprise, pour le message d'echec. */
        List<String> ouEstPose(Jeton jeton) {
            List<String> porteurs = new ArrayList<>();
            if (permanents.contains(jeton)) {
                porteurs.add("la coquille permanente");
            }
            for (Ecran ecran : ecrans) {
                if (ecran.jetons().contains(jeton)) {
                    porteurs.add(ecran.nom());
                }
            }
            return porteurs;
        }
    }

    /** Les valeurs litterales d'une expression Thymeleaf, entre apostrophes. */
    private static List<String> litteraux(String expression) {
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
        return valeurs;
    }

    /**
     * Les jetons qu'un element peut poser au rendu. Les classes viennent de l'attribut {@code class}
     * et des litteraux d'un {@code th:class} ou d'un {@code th:classappend} ; les valeurs
     * d'attribut, de tout attribut en clair et des litteraux de tout attribut Thymeleaf. Seuls les
     * litteraux sont lus, jamais une expression calculee : HabillageDesEcransTest refuse deja
     * qu'une classe soit entierement calculee, donc tout ce qui est rendu est litteral - et lire une
     * expression au mot pres ferait passer un nom de variable de modele pour un jeton posable, ce
     * qui affaiblirait cette clause au lieu de la renforcer.
     */
    private static Set<Jeton> jetonsDe(Element element) {
        Set<Jeton> jetons = new LinkedHashSet<>();
        for (String classe : element.classNames()) {
            jetons.add(Jeton.classe(classe));
        }
        for (String porteur : PORTEURS_DE_CLASSE) {
            for (String litteral : litteraux(element.attr(porteur))) {
                for (String mot : litteral.split(" ")) {
                    if (!mot.isBlank()) {
                        jetons.add(Jeton.classe(mot.strip()));
                    }
                }
            }
        }
        for (Attribute attribut : element.attributes()) {
            if (attribut.getKey().startsWith(PREFIXE_THYMELEAF)) {
                for (String litteral : litteraux(attribut.getValue())) {
                    jetons.add(Jeton.valeurDAttribut(litteral));
                }
            } else {
                jetons.add(Jeton.valeurDAttribut(attribut.getValue().strip()));
            }
        }
        return jetons;
    }

    private static Set<Jeton> jetonsDe(Iterable<Element> elements) {
        Set<Jeton> jetons = new LinkedHashSet<>();
        for (Element element : elements) {
            jetons.addAll(jetonsDe(element));
        }
        return jetons;
    }

    /**
     * Les jetons qu'un selecteur nomme : ses classes, et les valeurs comparees dans ses selecteurs
     * d'attribut. Une comparaison d'attribut sans valeur - la seule presence de l'attribut - n'est
     * pas un jeton et n'est donc pas jugee ; aucune regle maison n'en ecrit une a ce jour, et le
     * jour ou l'une en ecrira, cette limite est le declencheur pour reprendre ce fichier.
     */
    private static Set<Jeton> jetonsDuSelecteur(String selecteur) {
        Set<Jeton> jetons = new LinkedHashSet<>();
        for (String classe : classesDuSelecteur(selecteur)) {
            jetons.add(Jeton.classe(classe));
        }
        int position = 0;
        while ((position = selecteur.indexOf('[', position)) >= 0) {
            int fin = selecteur.indexOf(']', position);
            if (fin < 0) {
                break;
            }
            String comparaison = selecteur.substring(position + 1, fin);
            int egal = comparaison.indexOf('=');
            if (egal >= 0) {
                String valeur = comparaison.substring(egal + 1).strip();
                if (valeur.length() >= 2 && (valeur.startsWith("\"") || valeur.startsWith("'"))) {
                    valeur = valeur.substring(1, valeur.length() - 1).strip();
                }
                if (!valeur.isEmpty()) {
                    jetons.add(Jeton.valeurDAttribut(valeur));
                }
            }
            position = fin + 1;
        }
        return jetons;
    }

    /**
     * La composition des documents, derivee comme dans ADR-0022 : la coquille est le gabarit qui
     * contient la zone {@code hx-target}, tout autre gabarit est un ecran insere dans cette zone.
     * Un raffinement de plus ici, qui resserre la clause : le contenu ecrit <b>dans</b> la zone de la
     * coquille n'est pas permanent - un echange htmx l'efface - c'est donc un ecran de plus, et non
     * un jeton que tous les documents porteraient.
     */
    private static Composition composition(Map<String, Document> gabarits, List<String> defauts) {
        Set<String> defautsDeZone = new LinkedHashSet<>();
        Optional<String> zone = zoneEchangee(gabarits.values(), defautsDeZone);
        defauts.addAll(defautsDeZone);
        if (zone.isEmpty()) {
            return new Composition(Set.of(), List.of());
        }
        List<String> coquilles = gabarits.entrySet().stream()
                .filter(gabarit -> !gabarit.getValue().select(zone.get()).isEmpty())
                .map(Map.Entry::getKey)
                .toList();
        if (coquilles.size() != 1) {
            defauts.add("la zone " + zone.get() + " est declaree par " + coquilles.size()
                    + " gabarit(s) " + coquilles + " ; il en faut exactement un, sans quoi ce "
                    + "garde-fou ne sait pas quels jetons sont permanents et lesquels appartiennent "
                    + "a un ecran echange");
            return new Composition(Set.of(), List.of());
        }
        String coquille = coquilles.get(0);
        Document document = gabarits.get(coquille);
        Element elementDeZone = document.select(zone.get()).first();
        Set<Element> dansLaZone = new LinkedHashSet<>(elementDeZone.getAllElements());
        dansLaZone.remove(elementDeZone);

        List<Element> permanents = document.getAllElements().stream()
                .filter(element -> !dansLaZone.contains(element))
                .toList();
        List<Ecran> ecrans = new ArrayList<>();
        ecrans.add(new Ecran(coquille + " (contenu de " + zone.get() + ")", jetonsDe(dansLaZone)));
        for (Map.Entry<String, Document> gabarit : gabarits.entrySet()) {
            if (!gabarit.getKey().equals(coquille)) {
                ecrans.add(new Ecran(gabarit.getKey(), jetonsDe(gabarit.getValue().getAllElements())));
            }
        }
        return new Composition(jetonsDe(permanents), ecrans);
    }

    static void verifierQuAucuneRegleNeReunitDeuxEcrans(Path racine) {
        List<String> defauts = new ArrayList<>();
        Composition composition = composition(documents(racine), defauts);
        List<Ecran> documentsJouables = composition.documentsJouables();
        List<Regle> regles = reglesMaison(racine);

        assertThat(defauts)
                .as("ADR-0031 : la composition des documents doit etre derivable de %s", racine)
                .isEmpty();
        assertThat(documentsJouables)
                .as("ADR-0003 : cette clause ne prouve rien si aucun document n'est derive de %s",
                        racine)
                .isNotEmpty();
        assertThat(regles)
                .as("ADR-0003 : cette clause ne prouve rien si aucune regle maison n'est relevee "
                        + "sous %s", racine)
                .isNotEmpty();

        List<Regle> population = regles.stream()
                .filter(regle -> jetonsDuSelecteur(regle.selecteur()).size() >= 2)
                .toList();
        assertThat(population)
                .as("ADR-0003 : cette clause ne juge que les selecteurs qui nomment au moins deux "
                        + "jetons - c'est leur croisement qui dit s'ils peuvent coexister. Aucun "
                        + "selecteur de ce genre sous %s : le garde-fou passerait a vide", racine)
                .isNotEmpty();

        for (Regle regle : population) {
            Set<Jeton> jetons = jetonsDuSelecteur(regle.selecteur());
            boolean jouable = documentsJouables.stream()
                    .anyMatch(document -> document.jetons().containsAll(jetons));
            if (jouable) {
                continue;
            }
            List<String> origines = new ArrayList<>();
            for (Jeton jeton : jetons) {
                List<String> porteurs = composition.ouEstPose(jeton);
                origines.add(jeton + (porteurs.isEmpty()
                        ? " qu'aucun gabarit ne pose"
                        : " que seul(s) " + porteurs + " peut poser"));
            }
            defauts.add(regle.origine() + " : ce selecteur nomme " + String.join(", puis ", origines)
                    + ". Aucun document que le serveur peut rendre ne porte tous ces jetons a la "
                    + "fois : la zone echangee recoit un ecran a la fois, donc cette regle ne "
                    + "s'appliquera jamais. Si elle visait a faire dependre l'apparence d'un ecran "
                    + "de l'etat d'un autre - un habillage qui change apres une bonne ou une "
                    + "mauvaise reponse - c'est precisement ce que la condition C4 de nadia-prof "
                    + "interdit : la machine ne dit jamais le resultat (ADR-0031)");
        }

        assertThat(defauts)
                .as("ADR-0031 : aucune regle maison ne reunit des jetons de deux ecrans qui ne "
                        + "s'affichent jamais ensemble")
                .isEmpty();
    }

    @Test
    void aucuneRegleMaisonNeReunitDeuxEcransQuiNeSAffichentJamaisEnsemble() {
        verifierQuAucuneRegleNeReunitDeuxEcrans(RACINE_PRODUCTION);
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un depot fictif dont la feuille colore l'habillage de
     * l'ecran de tirage quand l'ecran de bilan porte son marqueur de partie gagnee. C'est la faute
     * que la condition C4 de nadia-prof nomme, ecrite avec la technique que ce depot emploie deja.
     */
    @Test
    void unVerdictQuiColoreLaMachineFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("verdict-qui-colore-la-machine");
        assertThatThrownBy(() -> verifierQuAucuneRegleNeReunitDeuxEcrans(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("Aucun document que le serveur peut rendre ne porte tous ces "
                        + "jetons a la fois");
    }

    /**
     * Le meme depot fictif, ou le marqueur de partie gagnee ne colore plus que l'ecran qui le porte.
     * Sans ce cas, la clause serait une interdiction du decor ou du signal juste/faux deguisee en
     * clause de C4, et aucun jeu fautif ne le dirait : l'habillage, le marqueur et la regle qui
     * l'emploie restent tous les trois en place ici.
     */
    @Test
    void leMemeVerdictQuiNeColoreQueSonEcranResteVert() {
        verifierQuAucuneRegleNeReunitDeuxEcrans(CONTRE_EXEMPLES.resolve("verdict-sur-son-ecran"));
    }

    /**
     * Le message d'un ecran est l'element par lequel le gabarit fait annoncer la reponse : sa region
     * d'annonce ARIA. C'est le seul nom que la clause du sujet hors du message ecrit, et ce n'est pas
     * un nom du depot.
     */
    private static final String ATTRIBUT_DE_REGION = "role";
    private static final String REGION_D_ANNONCE = "status";
    private static final String REGION_DU_MESSAGE = "[" + ATTRIBUT_DE_REGION + "=" + REGION_D_ANNONCE + "]";

    /** Une classe qu'aucun gabarit ne pose, pour la mise a l'epreuve de la derivation. */
    private static final String JETON_NEUF = "jeton-neuf-du-message";

    /** Ou se trouve, dans un ecran, l'element qu'un compose de selecteur designe. */
    private enum Place { HORS_DU_MESSAGE, SOMMET_DU_MESSAGE, DANS_LE_MESSAGE }

    /**
     * Le message d'un ecran, par ses jetons : ceux de son sommet, ceux de ses descendants, ceux que le
     * meme document pose hors de lui (coquille permanente comprise), et tous ceux du document.
     */
    private record Message(String ecran, Set<Jeton> sommet, Set<Jeton> descendants, Set<Jeton> dehors,
                           Set<Jeton> document) {

        /** Ce que seul le message pose dans son document : ce qu'une regle hors de lui ne nomme pas. */
        Set<Jeton> propres() {
            Set<Jeton> propres = new LinkedHashSet<>(sommet);
            propres.addAll(descendants);
            propres.removeAll(dehors);
            return propres;
        }

        Set<Jeton> seulementDehors() {
            Set<Jeton> seuls = new LinkedHashSet<>(dehors);
            seuls.removeAll(sommet);
            seuls.removeAll(descendants);
            return seuls;
        }

        Set<Jeton> seulementDescendants() {
            Set<Jeton> seuls = new LinkedHashSet<>(descendants);
            seuls.removeAll(sommet);
            seuls.removeAll(dehors);
            return seuls;
        }
    }

    /** Un compose de selecteur, sans ce qu'il ecrit entre parentheses, et le combinateur qui le precede. */
    private record Compose(char combinateur, String texte) {
    }

    /**
     * Les composes de la chaine principale d'un selecteur. Les combinateurs ne sont cherches ni entre
     * parentheses ni entre crochets, et le texte entre parentheses est retire du compose : un
     * {@code :has(...)} ou un {@code :not(...)} ne dit pas ou est l'element que le compose designe.
     * {@code LAnimationRetardeMaisNeDetermineRienTest.sujet} ne sert pas ici : il coupe a l'interieur
     * d'un {@code :has(> ...)}, ce que la feuille ecrit.
     */
    private static List<Compose> composes(String selecteur) {
        List<Compose> composes = new ArrayList<>();
        StringBuilder texte = new StringBuilder();
        char combinateur = ' ';
        int parentheses = 0;
        int crochets = 0;
        for (int position = 0; position < selecteur.length(); position++) {
            char caractere = selecteur.charAt(position);
            boolean auPremierNiveau = parentheses == 0 && crochets == 0;
            if (caractere == '(') {
                parentheses++;
            } else if (caractere == ')') {
                parentheses--;
                continue;
            } else if (caractere == '[') {
                crochets++;
            } else if (caractere == ']') {
                crochets--;
            }
            if (auPremierNiveau && (Character.isWhitespace(caractere) || caractere == '>'
                    || caractere == '+' || caractere == '~')) {
                if (texte.length() > 0) {
                    composes.add(new Compose(combinateur, texte.toString()));
                    texte.setLength(0);
                    combinateur = ' ';
                }
                if (!Character.isWhitespace(caractere)) {
                    combinateur = caractere;
                }
                continue;
            }
            if (parentheses == 0) {
                texte.append(caractere);
            }
        }
        if (texte.length() > 0) {
            composes.add(new Compose(combinateur, texte.toString()));
        }
        return composes;
    }

    private static boolean nomme(Set<Jeton> jetons, Set<Jeton> parmi) {
        return jetons.stream().anyMatch(parmi::contains);
    }

    /**
     * La place du sujet d'un selecteur par rapport au message, lue compose par compose. Un compose
     * qui nomme un jeton que le document ne pose que hors du message est hors du message ; un jeton
     * que seuls les descendants du message posent le place dedans ; un jeton propre au sommet, au
     * sommet. Sans jeton decisif, un compose herite : un descendant ou un enfant de ce qui est dans le
     * message ou a son sommet est dedans ; un frere de ce qui est dedans reste dedans, un frere du
     * sommet est dehors. Le premier compose sans jeton decisif est tenu pour dehors : le doute
     * rougit, il ne passe pas.
     */
    private static Place placeDuSujet(String selecteur, Message message) {
        Place place = Place.HORS_DU_MESSAGE;
        boolean premier = true;
        for (Compose compose : composes(selecteur)) {
            Place heritee;
            if (premier) {
                heritee = Place.HORS_DU_MESSAGE;
            } else if (compose.combinateur() == '+' || compose.combinateur() == '~') {
                heritee = place == Place.DANS_LE_MESSAGE ? Place.DANS_LE_MESSAGE : Place.HORS_DU_MESSAGE;
            } else {
                heritee = place == Place.HORS_DU_MESSAGE ? Place.HORS_DU_MESSAGE : Place.DANS_LE_MESSAGE;
            }
            Set<Jeton> jetons = jetonsDuSelecteur(compose.texte());
            if (nomme(jetons, message.seulementDehors())) {
                place = Place.HORS_DU_MESSAGE;
            } else if (nomme(jetons, message.seulementDescendants())) {
                place = Place.DANS_LE_MESSAGE;
            } else if (nomme(jetons, message.propres())) {
                place = heritee == Place.DANS_LE_MESSAGE ? Place.DANS_LE_MESSAGE : Place.SOMMET_DU_MESSAGE;
            } else {
                place = heritee;
            }
            premier = false;
        }
        return place;
    }

    /** Les messages de tous les gabarits, derives de leur region d'annonce. */
    private static List<Message> messages(Map<String, Document> gabarits, List<String> defauts) {
        Composition composition = composition(gabarits, defauts);
        List<Message> messages = new ArrayList<>();
        for (Map.Entry<String, Document> gabarit : gabarits.entrySet()) {
            for (Element region : gabarit.getValue().select(REGION_DU_MESSAGE)) {
                Set<Element> dansLaRegion = new LinkedHashSet<>(region.getAllElements());
                List<Element> horsDeLaRegion = gabarit.getValue().getAllElements().stream()
                        .filter(element -> !dansLaRegion.contains(element))
                        .toList();
                dansLaRegion.remove(region);
                Set<Jeton> dehors = jetonsDe(horsDeLaRegion);
                dehors.addAll(composition.permanents());
                Set<Jeton> document = new LinkedHashSet<>(dehors);
                document.addAll(jetonsDe(region.getAllElements()));
                messages.add(new Message(gabarit.getKey(), jetonsDe(region), jetonsDe(dansLaRegion),
                        dehors, document));
            }
        }
        return messages;
    }

    /**
     * ADR-0031, amendement du 2026-10-16 ({@code C1'} point 1) : aucune regle dont le sujet est hors
     * du message d'un ecran ne nomme ce que seul ce message pose, {@code :has(...)} compris. Une regle
     * n'est jugee que contre les documents ou elle peut s'appliquer : ceux qui posent tous ses jetons.
     */
    static void verifierQuAucuneRegleHorsDuMessageNeLeNomme(Map<String, Document> gabarits,
                                                         List<Regle> regles) {
        List<String> defauts = new ArrayList<>();
        List<Message> messages = messages(gabarits, defauts);
        assertThat(defauts)
                .as("ADR-0031 : la composition des documents doit etre derivable des gabarits")
                .isEmpty();
        assertThat(messages)
                .as("ADR-0003 : aucun gabarit ne pose de region %s ; la clause du sujet hors du "
                        + "message passerait a vide", REGION_DU_MESSAGE)
                .isNotEmpty();

        int jugees = 0;
        for (Regle regle : regles) {
            Set<Jeton> nommes = jetonsDuSelecteur(regle.selecteur());
            for (Message message : messages) {
                if (!message.document().containsAll(nommes)) {
                    continue;
                }
                Set<Jeton> duMessage = new LinkedHashSet<>(nommes);
                duMessage.retainAll(message.propres());
                if (duMessage.isEmpty()) {
                    continue;
                }
                jugees++;
                if (placeDuSujet(regle.selecteur(), message) == Place.HORS_DU_MESSAGE) {
                    defauts.add(regle.origine() + " : le sujet de ce selecteur est hors du message de "
                            + message.ecran() + " (sa region " + REGION_DU_MESSAGE + "), et le "
                            + "selecteur nomme " + duMessage + ", que ce document ne pose que dans "
                            + "le message. Condition C1' point 1 (ADR-0031, amendement du "
                            + "2026-10-16) : une regle dont le sujet est hors du message ne depend ni "
                            + "de sa presence ni de son verdict - le meuble et ce qui est au-dessus "
                            + "de lui restent les memes apres une bonne et apres une mauvaise reponse "
                            + "(condition C4 de nadia-prof)");
                }
            }
        }
        assertThat(jugees)
                .as("ADR-0003 : aucune regle maison ne nomme ce que seul un message pose ; la clause "
                        + "du sujet hors du message passerait a vide")
                .isPositive();
        assertThat(defauts)
                .as("ADR-0031 : aucune regle dont le sujet est hors du message ne le nomme")
                .isEmpty();
    }

    /**
     * Pour chaque message derive et chaque classe que lui seul pose, une regle dont le sujet est la
     * premiere classe que son document ne pose que hors de lui, et qui nomme cette classe du message
     * dans un {@code :has(...)} : la forme de la regle de {@code TP4}, sans aucun nom ecrit ici.
     */
    private static List<Regle> reglesQuiNommentLeMessageDepuisLeDehors(Map<String, Document> gabarits) {
        List<Regle> fautives = new ArrayList<>();
        for (Message message : messages(gabarits, new ArrayList<>())) {
            Optional<Jeton> sujet = message.seulementDehors().stream()
                    .filter(jeton -> jeton.genre().equals(Jeton.CLASSE))
                    .findFirst();
            if (sujet.isEmpty()) {
                continue;
            }
            for (Jeton propre : message.propres()) {
                if (propre.genre().equals(Jeton.CLASSE)) {
                    fautives.add(new Regle("mutation de " + message.ecran(), "",
                            "." + sujet.get().valeur() + ":has(." + propre.valeur() + ")",
                            " row-gap: 0; "));
                }
            }
        }
        return fautives;
    }

    private static void chacuneFaitEchouerLeGardeFou(Map<String, Document> gabarits, List<Regle> fautives) {
        List<Regle> production = reglesMaison(RACINE_PRODUCTION);
        for (Regle fautive : fautives) {
            List<Regle> regles = new ArrayList<>(production);
            regles.add(fautive);
            assertThatThrownBy(() -> verifierQuAucuneRegleHorsDuMessageNeLeNomme(gabarits, regles))
                    .as(fautive.selecteur())
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining(fautive.selecteur())
                    .hasMessageContaining("Condition C1' point 1");
        }
    }

    @Test
    void aucuneRegleDontLeSujetEstHorsDuMessageNeNommeCeQueSeulLeMessagePose() {
        verifierQuAucuneRegleHorsDuMessageNeLeNomme(documents(RACINE_PRODUCTION),
                reglesMaison(RACINE_PRODUCTION));
    }

    /**
     * Mise a l'epreuve sur la feuille et les gabarits reels : chaque classe propre a chaque message,
     * nommee depuis un sujet hors de lui et ajoutee aux regles de production, fait echouer la clause
     * en citant le selecteur fautif.
     */
    @Test
    void chaqueClasseDuMessageNommeeDepuisUnSujetHorsDeLuiFaitEchouerLeGardeFou() {
        Map<String, Document> gabarits = documents(RACINE_PRODUCTION);
        List<Regle> fautives = reglesQuiNommentLeMessageDepuisLeDehors(gabarits);
        assertThat(fautives).as("aucune regle fautive derivee : la mise a l'epreuve serait vide").isNotEmpty();
        chacuneFaitEchouerLeGardeFou(gabarits, fautives);
    }

    /**
     * ADR-0013 obligation 1, prouvee plutot qu'affirmee : une classe que le gabarit pose neuve sur le
     * message entre dans le controle sans que ce fichier change.
     */
    @Test
    void uneClasseNeuveDuMessageEntreDansLeControleSansQueCeFichierChange() {
        Map<String, Document> gabarits = documents(RACINE_PRODUCTION);
        gabarits.values().forEach(document -> document.select(REGION_DU_MESSAGE).addClass(JETON_NEUF));
        List<Regle> fautives = reglesQuiNommentLeMessageDepuisLeDehors(gabarits).stream()
                .filter(regle -> regle.selecteur().endsWith(":has(." + JETON_NEUF + ")"))
                .toList();
        assertThat(fautives).as("la classe neuve n'est pas derivee comme propre au message").isNotEmpty();
        chacuneFaitEchouerLeGardeFou(gabarits, fautives);
    }

    @Test
    void sansAucuneRegionDeMessageLaClauseEchoueAuLieuDePasserAVide() {
        Map<String, Document> gabarits = documents(RACINE_PRODUCTION);
        gabarits.values().forEach(document -> document.select(REGION_DU_MESSAGE).removeAttr(ATTRIBUT_DE_REGION));
        assertThatThrownBy(() -> verifierQuAucuneRegleHorsDuMessageNeLeNomme(gabarits,
                reglesMaison(RACINE_PRODUCTION)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("aucun gabarit ne pose de region " + REGION_DU_MESSAGE);
    }

    private static boolean estUneClasse(Jeton jeton) {
        return jeton.genre().equals(Jeton.CLASSE);
    }

    /**
     * ADR-0031, amendement du 2026-10-20 ({@code Z1}) : la regle de {@code TP4} avec la classe du
     * message en seconde position d'une liste de {@code :has(...)} ou de {@code :is(...)}, derriere
     * une seconde classe du dehors. Ecrite en texte et lue par le lecteur de la feuille : une
     * {@link Regle} construite a la main ne passerait pas par le decoupage de l'en-tete, qui est
     * precisement ce qui laissait passer cette forme.
     */
    private static List<Regle> fautivesEnListe(Map<String, Document> gabarits, String pseudo) {
        List<Regle> fautives = new ArrayList<>();
        for (Message message : messages(gabarits, new ArrayList<>())) {
            List<Jeton> dehors = message.seulementDehors().stream()
                    .filter(UnEcranNeGouvernePasLApparenceDUnAutreTest::estUneClasse)
                    .limit(2)
                    .toList();
            if (dehors.size() < 2) {
                continue;
            }
            for (Jeton propre : message.propres()) {
                if (estUneClasse(propre)) {
                    String texte = "." + dehors.get(0).valeur() + ":" + pseudo + "(."
                            + dehors.get(1).valeur() + ", ." + propre.valeur() + ") { row-gap: 0; }";
                    List<Regle> lues = reglesDe("mutation de " + message.ecran(), texte);
                    assertThat(lues)
                            .as("une virgule entre parentheses ne coupe pas la regle : %s", texte)
                            .hasSize(1);
                    fautives.addAll(lues);
                }
            }
        }
        return fautives;
    }

    @Test
    void uneClasseDuMessageEnSecondePositionDUnHasFaitEchouerLeGardeFou() {
        Map<String, Document> gabarits = documents(RACINE_PRODUCTION);
        List<Regle> fautives = fautivesEnListe(gabarits, "has");
        assertThat(fautives).as("aucune regle fautive derivee : la mise a l'epreuve serait vide").isNotEmpty();
        chacuneFaitEchouerLeGardeFou(gabarits, fautives);
    }

    @Test
    void uneClasseDuMessageEnSecondePositionDUnIsFaitEchouerLeGardeFou() {
        Map<String, Document> gabarits = documents(RACINE_PRODUCTION);
        List<Regle> fautives = fautivesEnListe(gabarits, "is");
        assertThat(fautives).as("aucune regle fautive derivee : la mise a l'epreuve serait vide").isNotEmpty();
        chacuneFaitEchouerLeGardeFou(gabarits, fautives);
    }

    /**
     * Clause de non-sur-detection de {@code Z1} : une liste de deux classes du message dans le
     * {@code :has(...)} d'un sujet qui est le sommet du message reste verte. Elle mord le jour ou le
     * decoupage de l'en-tete ou la lecture du sujet prendrait une virgule pour une sortie du message.
     */
    @Test
    void uneListeDeClassesDuMessageSousSonSommetResteVerte() {
        Map<String, Document> gabarits = documents(RACINE_PRODUCTION);
        List<Regle> regles = new ArrayList<>(reglesMaison(RACINE_PRODUCTION));
        int ajoutees = 0;
        for (Message message : messages(gabarits, new ArrayList<>())) {
            Optional<Jeton> sommet = message.sommet().stream()
                    .filter(UnEcranNeGouvernePasLApparenceDUnAutreTest::estUneClasse)
                    .filter(message.propres()::contains)
                    .findFirst();
            List<Jeton> dedans = message.seulementDescendants().stream()
                    .filter(UnEcranNeGouvernePasLApparenceDUnAutreTest::estUneClasse)
                    .limit(2)
                    .toList();
            if (sommet.isEmpty() || dedans.size() < 2) {
                continue;
            }
            regles.addAll(reglesDe("clause de " + message.ecran(), "." + sommet.get().valeur()
                    + ":has(." + dedans.get(0).valeur() + ", ." + dedans.get(1).valeur()
                    + ") { color: inherit; }"));
            ajoutees++;
        }
        assertThat(ajoutees).as("aucun message ne porte un sommet et deux classes interieures : la clause serait vide")
                .isPositive();
        verifierQuAucuneRegleHorsDuMessageNeLeNomme(gabarits, regles);
    }

    /**
     * ADR-0031, amendement du 2026-10-20 ({@code Z2}) : une regle imbriquee est refusee par le
     * lecteur de la feuille, et le refus cite la regle, donc la classe du message qu'elle nomme.
     */
    @Test
    void uneRegleImbriqueeQuiNommeLeMessageEstRefuseeParLeLecteur() {
        int eprouvees = 0;
        for (Message message : messages(documents(RACINE_PRODUCTION), new ArrayList<>())) {
            Optional<Jeton> sujet = message.seulementDehors().stream()
                    .filter(UnEcranNeGouvernePasLApparenceDUnAutreTest::estUneClasse)
                    .findFirst();
            if (sujet.isEmpty()) {
                continue;
            }
            for (Jeton propre : message.propres()) {
                if (estUneClasse(propre)) {
                    String texte = "." + sujet.get().valeur() + " { &:has(." + propre.valeur()
                            + ") { row-gap: 0; } }";
                    assertThatThrownBy(() -> reglesDe("mutation de " + message.ecran(), texte))
                            .as(texte)
                            .isInstanceOf(AssertionError.class)
                            .hasMessageContaining("regle imbriquee")
                            .hasMessageContaining("." + propre.valeur());
                    eprouvees++;
                }
            }
        }
        assertThat(eprouvees).as("aucune regle imbriquee derivee : la mise a l'epreuve serait vide").isPositive();
    }

    /**
     * ADR-0031, amendement du 2026-10-20 ({@code Z5}) : l'exemple de la decision 1, que la
     * co-occurrence laisse passer depuis que l'ecran de manche pose aussi le verdict, est rouge par la
     * clause du sujet hors du message. Seul cas nomme de ce fichier : si un changement de jetons le
     * rendait inapplicable, ce test rougirait au lieu de laisser l'exemple de l'ADR sans preuve.
     */
    @Test
    void lExempleDeLaDecisionUnEstRefuseParLaClauseDuSujetHorsDuMessage() {
        String exemple = "body:has(.est-juste) .cabinet-bandeau";
        List<Regle> regles = new ArrayList<>(reglesMaison(RACINE_PRODUCTION));
        regles.addAll(reglesDe("exemple de la decision 1", exemple + " { background-color: gold; }"));
        assertThatThrownBy(() -> verifierQuAucuneRegleHorsDuMessageNeLeNomme(
                documents(RACINE_PRODUCTION), regles))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(exemple)
                .hasMessageContaining("Condition C1' point 1");
    }

    /**
     * ADR-0031, amendement du 2026-10-20 ({@code Z4}) : le nombre de regions d'annonce derivees des
     * gabarits, en egalite. Retirer {@code role="status"} d'un message sortirait cet ecran du controle
     * sans rouge ; le cliquet oblige le commit qui le fait a le dire ici et dans l'ADR.
     */
    private static final int REGIONS_D_ANNONCE = 3;

    private static void verifierLeNombreDeRegionsDAnnonce(Map<String, Document> gabarits) {
        assertThat(messages(gabarits, new ArrayList<>()))
                .as("ADR-0031, amendement du 2026-10-20 : le nombre de regions %s des gabarits a "
                        + "change. Un message qui perd sa region sort du controle de la condition C1' "
                        + "sans rouge ; un message neuf y entre. Le commit qui fait l'un ou l'autre "
                        + "met ce nombre a jour et le dit dans ADR-0031", REGION_DU_MESSAGE)
                .hasSize(REGIONS_D_ANNONCE);
    }

    @Test
    void leNombreDeRegionsDAnnonceEstEpingle() {
        verifierLeNombreDeRegionsDAnnonce(documents(RACINE_PRODUCTION));
    }

    @Test
    void unMessageQuiPerdSaRegionDAnnonceFaitEchouerLeCliquet() {
        Map<String, Document> gabarits = documents(RACINE_PRODUCTION);
        Element region = gabarits.values().stream()
                .flatMap(document -> document.select(REGION_DU_MESSAGE).stream())
                .findFirst()
                .orElseThrow();
        region.removeAttr(ATTRIBUT_DE_REGION);
        assertThatThrownBy(() -> verifierLeNombreDeRegionsDAnnonce(gabarits))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("le nombre de regions");
    }
}
