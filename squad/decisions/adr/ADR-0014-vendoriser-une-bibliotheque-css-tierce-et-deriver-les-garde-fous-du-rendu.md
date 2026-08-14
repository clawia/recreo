# ADR-0014 - Faire entrer une bibliotheque CSS tierce par vendorisation tracee, et faire deriver un garde-fou pedagogique du rendu plutot que du fichier

- **Statut** : Accepte
- **Date** : 2026-08-06
- **Auteur** : theo-archi
- **Sprint** : s03 (lots fusionnes sur `round-two`, sprint non cloture)

## Contexte

La demande est tranchee par l'utilisateur et je ne la rediscute pas : le design de `recreo` ne
plait pas, une bibliotheque CSS embarquee porte desormais le **rendu de base**, et on ne garde en
propre que le strict minimum. Ce que je tranche est le **comment** : par quel mecanisme une
bibliotheque tierce entre dans ce depot sans casser ce qui a ete construit, et ce qu'il advient des
garde-fous qui vont la rencontrer.

Quatre contraintes du `CLAUDE.md` encadrent la reponse, aucune n'est negociable : zero build Node et
zero serveur front separe, aucune ressource externe a l'execution, le comportement de jeu sur le
serveur (`ADR-0008`), et rien en dehors d'`adapters/adapter-web`.

### Fait 0 - ce n'est pas la premiere dependance front, c'est la deuxieme

`htmx.min.js` vit dans `static/js/` depuis le commit `d355cab`, soit 50 Ko de code tiers servis a
chaque page. Le mecanisme existe donc deja. Ce qui n'existe pas, c'est sa regle :

- **aucun fichier du depot ne dit de quelle version il s'agit.** Je l'ai identifiee par mesure et
  non par lecture : `sha256 = 71ea6718...c0de`, identique octet pour octet a `dist/htmx.min.js` de
  `htmx.org` **2.0.10** publie sur npm, et different de toutes les versions 2.0.1 a 2.0.9 et 1.9.x
  que j'ai comparees. Une mise a jour de securite etait jusqu'ici impossible a instruire : personne
  ne savait de quoi partir ;
- **le seul garde-fou qui le connaissait l'excluait par son nom de fichier.**
  `FrontieresDuJavaScriptTest.javaScriptMaison()` filtrait `!nom.startsWith("htmx")`. Un deuxieme
  fichier tiers depose a cote aurait ete juge par le budget d'`ADR-0008`, qui n'a aucun sens pour
  du code tiers : faux rouge, du bon cote, mais symptome d'un depot ou "tiers" n'est pas une
  categorie, seulement un nom qu'on a pense a citer.

Bootstrap ne cree donc pas le probleme, il le rend visible.

### Fait 1 - deux garde-fous passent au rouge, et ils protegent quelque chose de reel

Mesures faites sur `bootstrap.min.css` **5.3.8**, le fichier reellement candidat, pas sur une
impression :

| Garde-fou | Ce qu'il refuse | Ce que contient `bootstrap.min.css` 5.3.8 |
|---|---|---|
| `ViewportEtLargeurTelephoneTest` | toute declaration `width` en px, tout `min-width`/`max-width` au-dela de 375 px | **103** declarations `width` en px, **57** `min-width`, **33** `max-width` (valeurs jusqu'a 1400) |
| `TailleDesTextesEtBoutonsTest` | un bouton rendu dont la classe ne declare pas `font-size` >= 1,25 rem **et** `min-height` >= 2,75 rem, tous deux en rem | `.btn` declare `--bs-btn-font-size:1rem` et **aucun** `min-height` ; les titres passent par `calc(1.375rem + 1.5vw)`, qui n'est meme pas une valeur en rem |

Ces deux garde-fous ne sont pas des preferences de style. Ils transcrivent le refus de `nadia-prof`
sur S2-1 : *"la cible tactile d'un bouton de reponse mesure 46 x 41 px [...] c'est sous les 44 px
communement retenus comme minimum, pour des enfants dont la motricite fine est en construction. Un
doigt qui derape d'un demi-centimetre appuie sur la reponse du dessous, et le jeu enregistre alors
une erreur de calcul la ou il y a eu une erreur de geste."* Les desactiver pour faire passer
Bootstrap serait rendre le refus, pas le respecter.

Mais les laisser balayer du code tiers ne vaut pas mieux. Un garde-fou qui reproche a Bootstrap
`.carousel-indicators [data-bs-target]{width:30px}` produit du bruit sur un carrousel qu'on ne sert
pas, ecrit par des gens qui ne connaissent pas nos contraintes. Un garde-fou bruyant se fait
desactiver, et c'est ainsi qu'on perd une garantie.

### La vraie question, et pourquoi elle n'a pas une seule reponse

`ADR-0013` a pose qu'un garde-fou **derive** son perimetre de sa source et ne l'enumere jamais. La
question posee est donc exacte : quelle est la source, maintenant que tout le CSS servi n'est plus
du CSS ecrit ici ?

En cherchant une reponse unique, j'ai tourne en rond pendant un moment, jusqu'a voir que la question
recouvre **deux familles de garde-fous qui n'ont pas le meme objet**.

| | Garde-fou de style | Garde-fou pedagogique |
|---|---|---|
| **Exemple** | `ViewportEtLargeurTelephoneTest` | `TailleDesTextesEtBoutonsTest` |
| **Ce qu'il juge** | une **declaration** : "aucun `width` en px nulle part" | un **element rendu** : "ce bouton-la atteint 44 px" |
| **Sa source** | la **provenance du fichier** : on ne se reproche que ce qu'on ecrit | le **balisage** : la population des boutons vient des gabarits |
| **Ce qu'est le CSS pour lui** | son perimetre | sa **matiere**, jamais son perimetre |

Le second point est celui que je n'avais pas vu, et il dissout le faux dilemme "balayer le tiers ou
l'exempter". Un garde-fou pedagogique ne reproche jamais rien a une feuille de style. Il reproche a
**un bouton** de ne pas atteindre le plancher une fois toutes les feuilles servies appliquees. Lire
le CSS tiers pour calculer ce qui s'applique a notre bouton n'est pas le juger ; c'est refuser de
prononcer un verdict sur une matiere qu'on n'a pas regardee. Ce qui est interdit, c'est de lui
reprocher ses propres declarations prises isolement.

### Ce qui rend la provenance declarative sans en faire un trou

Declarer "`static/vendor/` est tiers" ressemble a une liste d'exemptions, et `ADR-0013` s'en mefie a
juste titre. Ce n'en est pas une, et la raison tient en une phrase : **le defaut est "juge"**. Une
bibliotheque deposee ailleurs que sous `vendor/` n'echappe a rien, elle est traitee comme du CSS
maison et echoue immediatement. Ce n'est pas un raisonnement, c'est une mesure - `bootstrap.min.css`
copie dans `static/css/` :

```
ViewportEtLargeurTelephoneTest.aucuneLargeurNestFixeeAuDelaDeLaCibleTelephone
[src/main/resources/static/css/bootstrap.min.css (.carousel-indicators [data-bs-target]) :
 width fixe une largeur absolue sur le conteneur, utiliser max-width pour rester capable de
 retrecir sous 375px]
```

Un oubli de declaration produit un **rouge**, jamais un vert silencieux. C'est le critere que
`ADR-0013` demande a une liste d'exemptions, et il est tenu.

## Decision

**1. Une bibliotheque front tierce entre par vendorisation, jamais par une dependance qui apporte
tout un paquet.** Le fichier exact que l'application sert, copie octet pour octet, sous
`adapters/adapter-web/src/main/resources/static/vendor/`, et **rien d'autre du paquet d'origine**.

> **Amendement du 2026-08-14, `ADR-0037`** : ce point parle de **dependances**, et se lisait donc
> comme s'il gouvernait toute dependance Maven de ce depot - y compris un outil de test qui n'est
> servi a personne. Le perimetre de cet ADR est **l'octet servi au navigateur**. Une dependance de
> portee `test` n'en releve pas ; une dependance qui apporte un octet servi en releve et est refusee.
> C'est `ADR-0037` qui dit cette frontiere et qui l'encode.

**2. `static/vendor/` est le seul emplacement tiers du depot.** Tout le reste de `static/` est
maison et juge comme tel.

**3. Chaque fichier vendorise est trace dans `static/vendor/provenance.txt`**, une ligne de quatre
champs : chemin servi, origine sous forme de coordonnees completes, chemin dans l'artefact
d'origine, empreinte SHA-256. La trace est exigee dans les deux sens : un fichier sans ligne echoue,
une ligne sans fichier echoue, une empreinte qui ne correspond plus echoue.

**4. Un fichier vendorise ne se retouche jamais.** Il se remplace et se retrace. Les conventions de
redaction du `CLAUDE.md` portent sur ce que ce depot **ecrit** ; `bootstrap.min.css` contient un
tiret cadratin dans une regle de citation que nous ne servons pas, et ce n'est pas une faute de
redaction de ce depot. Le retoucher casserait l'empreinte et rendrait toute mise a jour illisible en
diff.

**5. La feuille maison est chargee en dernier dans tout gabarit qui charge des feuilles.** Ce n'est
pas cosmetique : c'est ce qui fait qu'une regle maison de meme specificite l'emporte sur la regle
tierce, donc que les planchers declares ici sont ceux que l'enfant voit.

**6. Une garantie pedagogique est toujours portee par une regle maison.** Un bouton rendu doit etre
couvert par au moins une regle ecrite ici, qui declare le plancher. Une bibliotheque tierce porte le
rendu de base ; elle ne porte jamais une exigence de `nadia-prof`, parce que sa prochaine version
peut la retirer sans que ce depot le sache. Un bouton qui n'a qu'une classe tierce echoue.

**7. Le JavaScript de Bootstrap n'entre pas.** Ni modales, ni menus, ni replis. `ADR-0008` loge le
comportement sur le serveur, et la vendorisation fichier par fichier fait de cette absence l'etat
par defaut : il faudrait copier le fichier volontairement. Un garde-fou refuse ce geste.

**8. Version retenue : `bootstrap.min.css` de `org.webjars:bootstrap:5.3.8`.** Verifie et non
suppose : c'est la derniere version publiee de cet artefact (`maven-metadata.xml` de Maven Central,
`lastUpdated` 2025-08-27) ; le jar telecharge porte le sha1 publie `b70464c4...f668` ; le fichier
extrait porte le sha256 `d85327d9...dede` inscrit dans `provenance.txt`. `htmx.min.js`, present
depuis s00 sans version connue, est identifie et trace au meme titre : `npm:htmx.org:2.0.10`.

**Ce que cet ADR ne fait pas.** Il ne decide d'aucune classe Bootstrap a adopter, d'aucune couleur,
d'aucune mise en page. Le rendu appartient a `ines-design`, et le verdict sur ce rendu appartient a
`nadia-prof`. Cet ADR pose le contenant et les garanties qui doivent survivre au changement de
contenu.

## Mecanisme : webjar ou fichier vendorise - comparaison mesuree

Les deux mecanismes tiennent la contrainte hors ligne. Ils se separent ailleurs.

| Critere | Dependance Maven (`org.webjars:bootstrap:5.3.8`) | Fichier vendorise sous `static/vendor/` |
|---|---|---|
| **Hors ligne a l'execution** | oui, les octets sont dans le fat jar | oui, idem |
| **Reproductibilite du build** | version epinglee dans le pom, artefact verifie par somme de controle par Maven ; suppose le depot d'artefacts atteignable au moins une fois | l'octet est dans le depot Git : le build ne depend de rien de reseau, et l'empreinte est reverifiee a chaque `mvn verify` |
| **Poids du fat jar** | **+2,4 Mo** : le webjar entre comme jar imbrique, stocke sans compression par `spring-boot-maven-plugin`. Il contient **11,4 Mo** decompresses et **325 fichiers** - sources `scss`, `.map`, variantes `rtl`, `jsf` et `gz`, et **tout le JavaScript**. Nous en servons **un** | **+31 Ko** : mesure dans le jar produit, `232 111` octets compresses a `31 101`. Un fichier, exactement ce qui est servi |
| **Distinguer le tiers du maison** | parfait mais **invisible** : le CSS tiers n'est pas dans l'arbre, aucun garde-fou ne peut deriver quoi que ce soit de sa presence, et aucun relecteur ne le voit en diff | l'emplacement **est** la distinction, et elle est lisible par un test comme par un humain |
| **Facilite de mise a jour** | un numero a changer dans le pom | recuperer le fichier, le remplacer, recalculer l'empreinte. **Plus lourd, c'est le cout reel de ce choix** |
| **Sort du JavaScript tiers** | **impossible a trancher** : `js/bootstrap.bundle.min.js` est dans le jar et servi a `/webjars/bootstrap/5.3.8/js/...`. On peut interdire le `<script>` dans un gabarit ; on ne peut pas empecher l'octet d'etre la et joignable | tranche par **absence** : le fichier n'existe pas |

**Je recommande la vendorisation**, pour deux raisons qui pesent plus que le confort de mise a jour.

La premiere est la ligne "sort du JavaScript tiers". Le webjar rendrait `ADR-0008` inverifiable sur
ce point : la regle deviendrait "personne ne l'appelle", alors qu'elle doit rester "il n'est pas la".
La seconde est la ligne "distinguer le tiers du maison". `ADR-0013` exige qu'un garde-fou derive son
perimetre de la source ; si le CSS tiers vit dans un jar et le CSS maison dans l'arbre, la seule
source de derivation disponible redevient une liste de noms ecrite dans un test - exactement ce que
l'exclusion `startsWith("htmx")` faisait deja, et exactement ce qu'`ADR-0013` interdit.

Le cout assume : une mise a jour de Bootstrap est un geste manuel de trois etapes au lieu d'un
numero a changer. Sur une bibliotheque CSS dont on ne suit pas les versions mineures, c'est un cout
annuel, pas hebdomadaire. Si ce cout devient penible, la section "ce qui me ferait changer d'avis"
dit a quoi le reconnaitre.

## Regle executable

Trois fichiers portent la decision. Aucune ligne de cet ADR n'est laissee au papier.

### 1. `adapters/adapter-web/src/test/java/com/recreo/adapter/web/RessourcesTiercesTest.java`

Nouveau garde-fou, cinq criteres, perimetre derive de bout en bout : les ressources tierces viennent
du **contenu du repertoire** `static/vendor/`, les feuilles et les gabarits d'un **balayage** de
l'arborescence. Aucun nom de bibliotheque n'y figure comme objet a controler.

La tracabilite est totale dans les deux sens (`ADR-0013` obligation 2) : chaque fichier present
produit une assertion, et chaque ligne declaree doit designer un fichier present.

```java
    static void verifierProvenanceDesRessourcesTierces(Path racine) {
        Map<String, Ressource> declarees = provenanceDeclaree(racine);
        List<String> presentes = new ArrayList<>();

        for (Path fichier : ressourcesTierces(racine)) {
            String chemin = cheminSousStatique(racine, fichier);
            presentes.add(chemin);
            Ressource declaree = declarees.get(chemin);
            assertThat(declaree)
                    .as("ADR-0014 : %s est servi sans etre trace [...]", chemin)
                    .isNotNull();
            assertThat(empreinte(fichier))
                    .as("ADR-0014 : %s ne correspond plus a l'empreinte declaree [...]", chemin)
                    .isEqualTo(declaree.empreinte());
        }

        assertThat(declarees.keySet())
                .as("ADR-0014 : une ligne de provenance qui ne designe aucun fichier present est "
                        + "une trace pourrie [...]")
                .isSubsetOf(presentes);
    }
```

**Une empreinte n'a de sens que si les octets survivent a l'extraction, et ils ne survivaient pas.**
Le depot n'avait aucun `.gitattributes`. Sur un poste configure en `core.autocrlf=true` - le defaut
de Git sous Windows - l'extraction convertit les fins de ligne, et les cinq sauts de ligne de
l'en-tete de licence de `bootstrap.min.css` suffisent a changer le fichier. Mesure, faite avec
`git checkout-index` sur l'index reel plutot que supposee : empreinte extraite `596a918e...`,
empreinte declaree `d85327d9...`. Un clone neuf serait donc parti **rouge**, pour un motif etranger a
toute alteration - c'est-a-dire exactement le bruit qui fait desactiver une regle. D'ou une ligne,
qui fait partie de la decision et non de son emballage :

```
adapters/adapter-web/src/main/resources/static/vendor/** -text
```

Remesure apres correction, meme protocole : `d85327d9...dede` pour `bootstrap.min.css` et
`71ea6718...c0de` pour `htmx.min.js`, tous deux identiques aux empreintes declarees.

Le critere "hors ligne" merite un mot, parce que sa forme naive ne marche pas. Chercher la
sous-chaine `https` dans une feuille tierce donne un **faux rouge immediat** : `bootstrap.min.css`
en contient 25 occurrences, dont 2 dans son en-tete de licence et 23 dans l'espace de noms
`http://www.w3.org/2000/svg` d'images encodees en `data:`, qui ne declenchent aucune requete. Le
critere porte donc sur ce qui provoque reellement un telechargement - la **cible** d'un `url()` ou
d'un `@import` - et il est total dans l'autre sens aussi : une cible qui n'est ni un `data:` ni un
fichier existant sous `static/` echoue, ce qui attrape aussi bien une police sur un CDN qu'une image
relative qu'on aurait oublie de copier.

```java
    private static void verifierCibleLocale(Path racine, Path depuis, String cible, String origine) {
        assertThat(cible)
                .as("ADR-0014 : %s va chercher %s sur le reseau ; l'application doit fonctionner "
                        + "hors ligne [...]", origine, cible)
                .doesNotStartWith("http://").doesNotStartWith("https://").doesNotStartWith("//");
        ...
                .isRegularFile();
    }
```

Ce critere est le seul que ce garde-fou applique au CSS tiers, et il ne lui reproche rien qui soit
propre a nous : ne rien aller chercher sur le reseau n'est pas un choix de style, c'est une
condition de deploiement qui vaut pour tout octet servi.

### 2. `TailleDesTextesEtBoutonsTest` - la garantie est portee par une regle maison

Le point 6 de la decision est ici, et c'est la regle qui mord le plus fort.

```java
    private static List<String> selecteursMaisonApplicables(Map<String, String> maison, Element bouton) {
        List<String> applicables = selecteursApplicables(bouton);
        List<String> maisonnee = applicables.stream().filter(maison::containsKey).toList();
        assertThat(maisonnee)
                .as("%s : aucune regle maison ne s'applique a ce bouton (selecteurs vus : %s). "
                        + "ADR-0014 : une bibliotheque tierce porte le rendu de base, elle ne porte "
                        + "jamais un plancher exige par nadia-prof - il faut une classe maison qui "
                        + "le declare", etiquette(bouton), applicables)
                .isNotEmpty();
        return maisonnee;
    }
```

Trois autres choses changent dans ce fichier, toutes commandees par la meme question de perimetre :
les gabarits balayes viennent du repertoire et non plus de quatre chemins ecrits a la main - c'est
la dette que `ADR-0013` lui reprochait nommement ; les regles maison viennent de **toutes** les
feuilles hors `vendor/` et non plus d'un fichier nomme ; et le garde-fou devient une fonction de sa
racine d'artefacts, ce qui lui permet enfin d'avoir un jeu fautif.

Un dernier point, et c'est celui qu'un raisonnement sur l'ordre de chargement seul aurait manque.
La regle maison gagne parce qu'elle est chargee en dernier - **sauf** contre `!important`. Une
classe utilitaire tierce posee sur un bouton (`.fs-6{font-size:1rem!important}` chez Bootstrap)
defait un plancher declare ici sans qu'aucune feuille maison ne change. C'est le seul motif pour
lequel ce garde-fou lit le CSS tiers, et il ne lui reproche rien : il reproche a **notre** bouton de
porter une classe qui rend notre garantie inoperante.

```java
    static void verifierQueLeTiersNeDefaitPasLesPlanchers(Path racine) {
        Map<String, String> tiers = blocsTiers(racine);
        for (Element bouton : boutonsPassesAuCrible(racine)) {
            for (String selecteur : selecteursApplicables(bouton)) {
                String corps = tiers.get(selecteur);
                if (corps == null) {
                    continue;
                }
                for (String propriete : PROPRIETES_GARDEES) {
                    assertThat(declareEnImportant(corps, propriete))
                            .as("%s : la classe tierce %s impose %s en !important et prend le pas "
                                    + "sur la regle maison ; le plancher exige par nadia-prof serait "
                                    + "declare mais pas rendu", etiquette(bouton), selecteur, propriete)
                            .isFalse();
                }
            }
        }
    }
```

### 3. `ViewportEtLargeurTelephoneTest` et `FrontieresDuJavaScriptTest` - la provenance remplace le nom

Le garde-fou de style ne juge plus un fichier nomme, il juge toute feuille hors `vendor/`. Deux
effets voulus : une feuille maison neuve est jugee du jour ou elle existe, sans qu'on pense a
l'inscrire ; une feuille tierce ne recoit jamais le reproche d'un choix de mise en page qui n'est
pas le notre.

```java
    private static List<Path> feuillesMaison(Path racine) throws IOException {
        Path statique = racine.resolve("static");
        ...
        Path tierces = statique.resolve("vendor");
        try (Stream<Path> arborescence = Files.walk(statique)) {
            return arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(".css"))
                    .filter(fichier -> !fichier.startsWith(tierces))
                    .sorted().toList();
        }
    }
```

`FrontieresDuJavaScriptTest` recoit la meme correction, et y perd son exclusion par nom de fichier :
`!nom.startsWith("htmx")` devient `!fichier.startsWith(TIERS)`. Le budget d'`ADR-0008` s'applique
desormais a tout JavaScript maison ou qu'il soit sous `static/`, et a rien de tiers.

### Ce qui est prouve, mesure et non suppose

**Jeux fautifs** (`ADR-0013` obligation 3), sept pour le garde-fou neuf, deux pour celui qui sort de
la dette. Chacun contient un objet **absent de la production** :

| Jeu fautif | Objet neuf | Rouge attendu |
|---|---|---|
| `RessourcesTiercesTest/tiers-non-trace` | une feuille tierce que personne n'a inscrite | "sans etre trace" |
| `RessourcesTiercesTest/empreinte-fausse` | un fichier tiers retouche apres coup | "empreinte declaree" |
| `RessourcesTiercesTest/trace-pourrie` | une ligne qui survit au fichier supprime | "trace pourrie" |
| `RessourcesTiercesTest/police-sur-le-reseau` | un `@font-face` qui telecharge sa police | "sur le reseau" |
| `RessourcesTiercesTest/gabarit-sur-cdn` | la facon habituelle d'installer Bootstrap : un `<link>` vers un CDN | "sur le reseau" |
| `RessourcesTiercesTest/maison-avant-tiers` | l'ordre de chargement inverse | "doit passer apres" |
| `RessourcesTiercesTest/javascript-de-composants` | `bootstrap.bundle.min.js` copie comme on copie une feuille | "n'entre pas dans ce depot" |
| `TailleDesTextesEtBoutonsTest/bouton-sans-regle-maison` | un bouton habille par la seule classe tierce | "aucune regle maison" |
| `TailleDesTextesEtBoutonsTest/plancher-defait-par-le-tiers` | un bouton qui porte une classe maison **et** une classe utilitaire tierce en `!important` | "!important" |

**Quatre mutations de l'arbre reel**, executees puis annulees, pour verifier que les regles mordent
la ou elles le pretendent et pas seulement sur des fixtures :

| Mutation du depot reel | Resultat mesure |
|---|---|
| `bootstrap.min.css` copie dans `static/css/` au lieu de `static/vendor/` | rouge, `aucuneLargeurNestFixeeAuDelaDeLaCibleTelephone`, sur `.carousel-indicators [data-bs-target]` |
| une ligne ajoutee a la fin de `vendor/bootstrap.min.css` | rouge, `chaqueRessourceTierceEstTraceeEtIntacte`, "ne correspond plus a l'empreinte declaree" |
| les deux `<link>` d'`accueil.html` intervertis | rouge, `laFeuilleMaisonEstChargeeApresLaFeuilleTierce`, "la feuille maison doit passer apres" |
| `class="proposition"` remplace par `class="btn"` sur le bouton de reponse reel | rouge sur **les deux** planchers, `.btn "" (fragments/manche.html) : aucune regle maison ne s'applique a ce bouton` |

La derniere est la plus instructive : elle est exactement le geste que quelqu'un ferait de bonne foi
en adoptant Bootstrap, et c'est le geste qui reintroduirait le defaut refuse par `nadia-prof` en s02.

**Recensement d'`ADR-0013`** : `TailleDesTextesEtBoutonsTest` sort de la dette de balayage, qui
passe de **5 a 4**. Son perimetre de gabarits est desormais derive et il possede son jeu fautif -
les deux choses qu'`ADR-0013` lui reprochait nommement. Le plafond ne remonte jamais.

**Etat mesure du depot.** `mvn -B clean verify` sur JDK 21.0.10 : BUILD SUCCESS, **174 tests
unitaires et 2 tests d'integration, 0 echec** (38 `domain`, 7 `application`, 66 `adapter-web`,
6 `adapter-content`, 57 + 2 `bootstrap`). `adapter-web` passe de 51 a 66 tests.

**Verification de bout en bout, hors du harnais Maven.** Le fat jar lance par `java -jar`, interroge
sur un port libre : `/vendor/bootstrap.min.css` repond `200`, `text/css`, `232 111` octets ;
`/vendor/htmx.min.js` repond `200` ; l'ancienne URL `/js/htmx.min.js` repond `404`, donc aucune
reference orpheline ne subsiste ; et la page servie porte bien les deux feuilles dans l'ordre, la
tierce puis la maison.

## Ce que cette regle ne couvre pas, dit avant qu'on le decouvre

- **La detection du `!important` tiers ne voit pas les regles imbriquees dans une media query.** Le
  decoupage en blocs `{ }` ne comprend pas l'imbrication ; un `@media (...){.fs-6{...!important}}`
  passerait. Les utilitaires de Bootstrap qui portent `!important` sont declares au premier niveau
  et sont donc vus. C'est une couverture partielle assumee, pas une couverture supposee.
- **Aucun moteur de rendu n'existe dans ce depot.** Ces garde-fous lisent des declarations et un
  arbre DOM statique ; ils ne mesurent aucun pixel. Le seul verdict sur ce que l'enfant voit
  reellement est celui de `nadia-prof`, qui rasterise. C'etait deja vrai avant Bootstrap, ce l'est
  davantage apres.
- **La specificite CSS n'est pas calculee.** La garantie repose sur la conjonction de deux regles :
  feuille maison chargee en dernier, et pas de `!important` tiers sur une propriete gardee. Un
  selecteur tiers de specificite superieure a une classe (par exemple `#id .btn`) l'emporterait sans
  etre vu. Bootstrap n'en contient pas ; le jour ou une bibliotheque en contiendrait, c'est cette
  limite qu'il faudrait lever.
- ~~**Le garde-fou lit la classe statique du gabarit, pas la classe calculee.**
  `fragments/retour.html` porte `th:class="${enSuite} ? 'retour-accueil' : 'sortie-pleine'"` : seule
  la valeur par defaut de l'attribut `class` est controlee. Limite anterieure a cet ADR, non
  aggravee par lui, et signalee ici parce qu'elle devient plus facile a rencontrer avec des classes
  utilitaires.~~
  **Amendement du 2026-08-06, `ADR-0015` : cette limite est fermee.** Le perimetre derive desormais
  de chaque branche litterale de `th:class` et de `th:classappend`, et une classe calculee est
  refusee au lieu d'etre ignoree. Mesure faite : avant correction, retirer `.sortie-pleine` de
  `recreo.css` laissait la suite verte. Nommer un trou dans un ADR ne le garde pas - c'est
  exactement ce qu'`ADR-0013` interdit, et cette puce en etait une occurrence. La forme reellement
  livree par `ines-design` porte de plus `btn` sur les deux branches, cet ADR ayant ete redige avant
  la mise en oeuvre.
- ~~**`JAVASCRIPT_TIERS_REFUSE` est un critere enumere, pas un perimetre.** Un fichier renomme
  echapperait au motif. Ce qui ne lui echappe pas : il faudrait le vendoriser, donc l'inscrire dans
  `provenance.txt` avec son origine, donc le montrer en diff.~~
  **Amendement du 2026-08-14, `ADR-0039` : cette limite ne gardait rien, et sa consolation etait
  fausse.** Le fichier renomme n'echappait pas "au motif" seulement : il echappait au garde-fou
  entier. Mesure par `yanis-qa` en s11 et rejouee avant correction - un `alpine.min.js` reel, copie
  sous `static/vendor/` avec une ligne de provenance complete et une empreinte exacte, laisse les
  seize tests de la classe verts, y compris celui qui promet de refuser le JavaScript d'une
  bibliotheque d'interface. Quant a "donc le montrer en diff" : un diff est une relecture, et
  l'axiome du depot range une relecture parmi les souhaits - c'est exactement ce qu'`ADR-0013`
  interdit, commis dans une puce qui se croyait prudente. Le critere est desormais une liste
  d'**autorisation**, ou une entree designe une origine declaree et une empreinte, jamais un nom de
  fichier.
- **`.gitattributes` ne couvre que `static/vendor/`.** Le reste du depot garde le comportement
  actuel. Le jour ou un autre fichier du depot devra survivre octet pour octet, c'est la meme ligne
  qu'il faudra ajouter, et rien ne le rappellera automatiquement.
- **`provenance.txt` prouve l'integrite, pas l'authenticite.** L'empreinte garantit que le fichier
  n'a pas bouge depuis qu'on l'a inscrit. Elle ne prouve pas que ce qu'on a inscrit venait bien de
  l'origine declaree ; cette verification-la a ete faite a la main a l'entree (sha1 du jar publie
  compare au telechargement) et ne se rejoue pas hors ligne.

## Ce qui, de s01 et s02, doit survivre a Bootstrap

`nadia-prof` a valide cinq proprietes aux points 2 et 2 bis de
`squad/conformite/FICHE-bandit-manchot-additions-cp.md`. Je ne les arbitre pas - elles sont a elle -
je dis lesquelles sont des contraintes techniques verifiables, et sous quelle forme elles survivent.

| Propriete validee | Verifiable ? | Sous quelle forme elle survit |
|---|---|---|
| **Cible tactile d'au moins 44 px** | **oui**, entierement | `min-height` >= 2,75 rem declare par une regle **maison** applicable au bouton. `.btn` n'en declare aucun : Bootstrap ne peut pas porter cette propriete, quoi qu'on fasse. Regle : `verifierCibleTactile` |
| **Plancher de lisibilite (1,25 rem)** | **oui**, entierement | `font-size` >= 1,25 rem, en rem, par une regle maison. Bootstrap declare 1 rem sur `.btn` et `calc(... + ...vw)` sur les titres, donc **sous le plancher et pas meme en rem** : la aussi, seule une regle maison peut la porter. Regle : `verifierPlancherDeLisibilite` |
| **Ecart d'au moins 1 rem entre boutons de reponse** | **oui**, mais par approximation | `gap` >= 1 rem sur `.propositions`. C'est un proxy valide tant que les boutons restent empiles dans ce conteneur flex ; une classe utilitaire tierce de grille le rendrait faux sans le rendre rouge. Regle : `lEcartEntreDeuxBoutonsDeReponseEstAuMoinsUnRem`. **Point de vigilance pour `ines-design`** |
| **La reponse plus saillante que la sortie** | **non**, pas en l'etat | La saillance est un rapport de surface, de couleur et de position mesure sur un rendu. Ce qui est verifiable aujourd'hui n'en est qu'un symptome : la sortie est en contour et la reponse en aplat de la palette (`HabillageDesEcransTest`). Ce n'est pas la propriete, c'est sa trace actuelle. **Cette propriete reste sous la seule garde de `nadia-prof`, et Bootstrap augmente le risque : ses classes de bouton uniformisent l'aspect, ce qui va contre une hierarchie voulue** |
| **La forme porte le sens de juste et faux, independamment de la couleur** | **partiellement** | Le `clip-path` de la coche et les deux barres croisees de la croix sont du CSS maison que Bootstrap ne fournit sous aucune forme : rien ne les rend caducs et rien ne doit les remplacer. Ce qui est verifiable est leur presence ; que la forme soit **lisible pour un enfant de CP** ne l'est pas et reste a `nadia-prof` |

Deux consequences a remonter, que je ne tranche pas :

- **a `sofia-sm` et `nadia-prof`** : le changement de rendu de base declenche une nouvelle porte
  pedagogique. Le Done technique de ce commit ne dit rien du Done pedagogique, et deux des cinq
  proprietes ci-dessus ne sont pas automatisables. Un audit joue est necessaire avant de considerer
  le nouveau rendu acquis ;
- **a `nadia-prof`, rappel de sa propre reserve** : elle a ecrit ne pas se prononcer *"sur le rendu
  en dessous d'environ 500 px de large"*, faute d'un harnais capable de le rendre. C'est precisement
  la zone ou les breakpoints de Bootstrap changent de comportement.

## Ce que Bootstrap rend caduc dans `recreo.css`

Pour qu'`ines-design` **supprime plutot qu'empile** - la dette n. 4 de `main` etait du comportement
empile que personne n'osait retirer. Je nomme des declarations, pas des regles entieres, parce que
la distinction est le coeur du sujet.

**Franchement redondant, supprimable sans rien perdre** (Bootstrap fournit l'equivalent, et aucune
garantie pedagogique n'en depend) :

- `body { font-family: sans-serif; }` : Reboot pose une pile de polices systeme, meilleure ;
- dans le bloc des boutons pleins : `border: none`, `border-radius: 0.5rem`, `padding: ...`,
  `text-decoration: none`, `cursor: pointer`. Tout cela vient de `.btn` ;
- `.jeux { list-style: none; padding: 0; }` : `.list-unstyled` fait exactement cela.

**Redondant en apparence, a garder quand meme** :

- `*, *::before, *::after { box-sizing: border-box; }` est textuellement identique a la ligne de
  Reboot. Mais c'est de lui que depend le fait que `min-height: 2.75rem` inclue le rembourrage,
  donc la cible tactile de 44 px. Point 6 de la decision : une garantie pedagogique ne se delegue
  pas a un tiers. **Il reste.**

**A ne surtout pas toucher** : `min-height` et `min-width` des boutons, les `font-size` en rem, la
palette `--recreo-*` et son usage, `.propositions { gap: 1rem }`, l'aplat contre le contour qui
distingue la reponse de la sortie, et tout le bloc `.signal` / `.icone-signal`. Chacun de ces points
porte une exigence ecrite de `nadia-prof` ; Bootstrap n'en fournit aucun equivalent.

**Candidats a instruire, pas a decider ici** : `.indice` et `.message-reprise` ressemblent a
`.alert.alert-warning`. L'adoption est tentante et elle changerait les couleurs, donc le contraste
que `nadia-prof` a mesure. Cela passe par une story et par elle, pas par une substitution silencieuse.

## Consequences

- Le fat jar grossit de **31 Ko** (le fichier est compresse dans le jar du module), mais la page
  d'accueil telecharge **227 Ko** de CSS la ou elle en telechargeait 7 : la compression HTTP n'est
  pas activee par defaut. Sans consequence en local, a surveiller le jour ou l'application sera
  servie a distance. Le fichier non minifie et les `.map` ne sont pas vendorises, donc le CSS tiers
  n'est pas deboguable a la source dans un navigateur. C'est le prix du choix, et il est reversible
  fichier par fichier.
- `htmx.min.js` change d'URL : `/js/htmx.min.js` devient `/vendor/htmx.min.js`. Un seul gabarit le
  reference.
- Ecrire un bouton coute maintenant une classe maison en plus de la classe Bootstrap. C'est
  volontaire, et c'est le seul endroit ou le depot resiste a la promesse "Bootstrap fait tout".
- Un cinquieme garde-fou vient s'ajouter dans `adapter-web`. Le cout est reel ; il est tres
  inferieur a celui d'une regression sur un plancher gagne par un refus.
- `HabillageDesEcransTest.aucuneRessourceExterneNiPoliceTelechargeeALExecution` ne balaie que
  `recreo.css` et son nom promet desormais plus que son perimetre - la maladie exacte d'`ADR-0013`.
  Le critere general est tenu par `RessourcesTiercesTest`, mais l'inexactitude subsiste. **Je ne
  reecris pas le fichier d'`ines-design` pendant qu'elle travaille** : c'est nomme ici comme dette a
  reprendre, au meme titre que les quatre garde-fous encore dans la dette de balayage.

## Alternatives considerees

- **Webjar Maven.** Ecarte pour les deux raisons du tableau : il rend le sort du JavaScript de
  Bootstrap intranchable, et il place le CSS tiers hors de l'arbre, donc hors de portee de toute
  derivation de perimetre. Ses avantages reels - mise a jour triviale, integrite geree par Maven -
  sont recuperes en partie par `provenance.txt`.
  **Amendement du 2026-08-14, `ADR-0037`** : cet ecart etait argumente et **jamais encode**. Rien
  n'empechait un webjar d'entrer, et `RessourcesTiercesTest` ne pouvait pas le voir - son perimetre
  descend de l'arbre `static/` d'un module, ou un webjar n'atterrit jamais. Nommer un trou dans un
  ADR ne le garde pas, ce que cet ADR reprochait lui-meme a une autre de ses puces : le refus est
  desormais porte par `LeLivrableNeSertQueLesRessourcesDeSesModulesIT`, qui lit le livrable produit.
- **CDN.** Ecarte par le `CLAUDE.md` : l'application doit fonctionner hors ligne. Un jeu fautif
  reproduit ce cas et exige le rouge, parce que c'est la facon la plus courante d'installer
  Bootstrap et donc la plus probable a etre proposee un jour.
- **Compiler les sources Sass pour ne garder que les composants utilises.** Ecarte : cela reintroduit
  une chaine de build front, que le `CLAUDE.md` exclut. Et cela produirait un fichier qui n'est plus
  une copie de rien, donc plus tracable par empreinte.
- **Exempter les deux garde-fous par une liste de fichiers a ignorer.** Ecarte par `ADR-0013` : une
  liste d'objets a ne pas controler est un perimetre enumere, dont le mode de defaillance est un vert
  silencieux. La provenance par emplacement fait la meme chose avec un mode de defaillance rouge, et
  c'est toute la difference.
- **Abaisser les seuils des garde-fous au niveau de Bootstrap** (accepter 1 rem, renoncer au
  `min-height`). Ecarte : ce serait rendre le refus de `nadia-prof` sur S2-1 sans le dire. Ces deux
  seuils sont les seules choses que ce sprint doit garantir de ne pas perdre.
- **Ne rien lire du CSS tiers, jamais.** Tentant, et c'est ce que j'ai failli ecrire. Mais une
  classe utilitaire en `!important` defait alors un plancher sans qu'aucun test ne bouge : le
  garde-fou resterait vert en ne gardant plus rien, la faute meme qu'`ADR-0013` nomme. Lire n'est
  pas juger.

## Ce qui me ferait changer d'avis

- **Si un deuxieme fichier de la meme bibliotheque devenait necessaire** (une police, une image, un
  second fichier CSS), la vendorisation fichier par fichier deviendrait du recopiage manuel a
  plusieurs pieces, et le webjar redeviendrait le bon choix - a condition d'ecrire d'abord la regle
  qui interdit de servir ses entrees JavaScript.
- **Si `provenance.txt` prend l'eau** - une ligne fausse qui passe, une mise a jour ou l'empreinte
  est recalculee sans que personne ne regarde d'ou vient le fichier - alors la tracabilite n'etait
  qu'un rituel, et un webjar dont Maven verifie la somme de controle vaut mieux qu'un rituel.
- **Si `nadia-prof` refuse le rendu Bootstrap sur une propriete que je viens de declarer
  verifiable**, c'est que ma colonne "verifiable" est fausse et que je l'ai ecrite trop large ; je
  la corrige plutot que de discuter son verdict.
- **Si la limite sur les media queries se revele exploitee en pratique**, je remplace le decoupage
  par blocs par une lecture qui comprend l'imbrication, dans les deux garde-fous a la fois.
