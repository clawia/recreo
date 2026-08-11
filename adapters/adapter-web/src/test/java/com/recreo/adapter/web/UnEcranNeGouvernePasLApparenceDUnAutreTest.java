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
 * noms cites dans un test alors que le code ne les portait pas encore (dette n. 1 de CLAUDE.md).
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
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li><b>perimetre derive</b> : les ecrans viennent du repertoire de gabarits et la coquille est
 *       identifiee par le fait qu'elle contient la zone {@code hx-target} - meme derivation
 *       qu'ADR-0022, partagee plutot que recopiee. Les jetons viennent du balisage et des selecteurs
 *       maison. Aucun nom de classe, de fragment ni de marqueur n'est ecrit dans ce fichier ; un
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
    private static Composition composition(Path racine, List<String> defauts) {
        Map<String, Document> gabarits = documents(racine);
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
        Composition composition = composition(racine, defauts);
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
}
