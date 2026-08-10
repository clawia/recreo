# [Sprint s06] Confirmation ArchUnit - theo-archi

- **Date** : 2026-08-10
- **Auteur** : theo-archi
- **Arbre juge** : `round-two`, commit `be60b10` - l'increment complet du sprint fusionne (les trois
  lots de la premiere vague, mon lot `ADR-0023`/`ADR-0024`, le lot "Le bon son" de `lea-dev`, le
  rapport de qualite de `yanis-qa`, les deux arbitrages de `nadia-prof` et la decision de capacite de
  `sofia-sm`) - **plus les fichiers du present commit `[THEO]`** : `ADR-0025`, `outils/mesurer-rendu.sh`
  et son test, l'etape `outils` de la CI, et la limite nommee dans la Javadoc de
  `ViewportEtLargeurTelephoneTest`.
- **Provisionnement** : worktree dedie **hors de l'arbre du depot**, branche forkee de `round-two` a
  `be60b10`. Lignee verifiee avant toute lecture, par le script et non a la main :
  ```
  $ outils/verifier-lignee.sh
  le worktree sur la branche 's06-theo-porte-technique' descend de round-two.
  ```
  Le correctif apporte a ce script apres mon signalement du 2026-08-07 rend le bon verdict sur les deux
  cas que j'ai rencontres : un worktree neuf sur la tete (ci-dessus) et un worktree sain dont la base a
  avance depuis le provisionnement, qui n'est plus refuse.
- **Prerequis** : `JAVA_HOME` sur un JDK 21, positionne pour la duree de chaque commande et jamais
  ecrit dans un fichier commite. Constat citable rendu par `outils/verifier-jdk.sh` sur ce poste :
  `java du PATH : 1.8.0_202 | JDK de compilation : 21.0.10 | JAVA_HOME : vide | toolchains lu par
  Maven : non (fichier present sous un nom non lu)`.
- **Ce que je ne fais pas ici** : aucun Done pedagogique ni son anticipation - il appartient a
  `nadia-prof` seule. Aucun arbitrage produit. Aucune remise en cause de la decision de capacite de
  `sofia-sm` sur le debordement de menu.

## 1. Le resultat de la commande

`mvn -B clean verify`

```
recreo ............................................. SUCCESS
recreo-domain ...................................... SUCCESS      43 tests
recreo-application ................................. SUCCESS       7 tests
recreo-adapter-web ................................. SUCCESS     121 tests
recreo-adapter-content ............................. SUCCESS      32 tests
recreo-bootstrap ................................... SUCCESS   73 + 3 tests
BUILD SUCCESS                                                   1 min 04 s
```

**279 tests, 0 echec, 0 erreur, 0 ignore.**

**Methode de ce comptage, dans le meme paragraphe que le chiffre, parce que celui de s05 a du etre
corrige deux fois faute de l'avoir ecrite.** Somme de l'attribut `tests` de la balise racine
`<testsuite>` des **52 rapports XML** produits par cette seule execution - **49** sous
`target/surefire-reports/` et **3** sous `target/failsafe-reports/`, soit 276 tests unitaires et 3 tests
d'integration. Perimetre parcouru : les repertoires `target` des cinq modules declares dans `<modules>`
du pom agregateur, jamais un `grep` lance depuis la racine du depot. **Exclu explicitement** : les
rapports `.txt` de Surefire, qui ne conservent qu'un seul moteur JUnit par classe et donnaient 221 au
lieu de 228 en s05 ; et toute execution anterieure, puisque **le comptage est fait apres le `clean` de
cette commande** - une suite mutee laisse des rapports qui produisent un total juste avec des echecs
faux, et je m'y suis pris moi-meme une fois au sprint 5. Repartition : `adapter-web` 121, `bootstrap`
73 + 3, `domain` 43, `adapter-content` 32, `application` 7. Ce total est **identique aux 279 annonces
par `sofia-sm`** sur `be60b10` : les deux comptages sont comparables parce qu'ils sont faits de la meme
facon, et non fusionnes.

Aucun echec rencontre en chemin sur cette execution. Un point de methode qui reste vrai et qui n'a pas
eu a jouer cette fois : une instance de l'application laissee en marche verrouille `recreo.jar` et fait
echouer le `clean` de `bootstrap` (`JarRecreoIT.arreter` le documente). J'ai lance une instance locale
pour prouver l'enveloppe de `mesurer-rendu.sh` (section 3) et je l'ai arretee **avant** le `clean
verify` ci-dessus, pas apres avoir vu un rouge.

## 2. Les regles reellement executees, et ce que ce nombre ne mesure pas

**28 `ArchRule` declarees, 28 reellement executees, dont 27 par le moteur ArchUnit et 1 par un
`@Test`.**

**Methode, perimetre, source de la liste, exclusions - dans ce paragraphe.** La liste n'est pas ecrite a
la main : les modules viennent de `<modules>` du pom agregateur (`domain`, `application`,
`adapters/adapter-web`, `adapters/adapter-content`, `bootstrap`), et dans chacun je parcours
`src/test/java` en cherchant l'expression `ArchRule <identifiant> =` - la meme derivation que
`LesGardeFousSontMisALEpreuveTest.reglesDeclarees` applique caractere par caractere. Resultat :
**27 dans `bootstrap/src/test/java/com/recreo/archi/`, 1 dans
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/archi/AssertionsWebTest.java`**, aucun nom en
double. **Exclusions explicites**, ce sont les trois pieges qui font diverger deux comptages honnetes et
que j'ai payes en s05 :

1. **Le faux depot de contre-exemple.**
   `bootstrap/src/test/resources/contreexemple/LesGardeFousSontMisALEpreuveTest/faux-module/` porte
   **2** `ArchRule` de plus, jamais compilees ni executees : c'est le depot fictif sur lequel le
   recensement se prouve mordant. Un `git grep` sur `*/src/test/*` les compte et rend 30 ; partir du pom
   ne les voit pas.
2. **Les worktrees d'agents.** Ils vivent hors de l'arbre du depot depuis la retro de s05, mais un
   `grep -r` depuis la racine d'un depot ou ils vivraient dedans double tout. Meme protection : partir
   du pom.
3. **Les occurrences en prose.** Le mot `ArchRule` apparait dans les `ADR` et dans `squad/` ; ces
   fichiers ne sont pas dans le perimetre.

**L'ecart entre "declarees" et "@ArchTest", qui est neuf ce sprint et qu'il faut nommer.** Le depot
compte **27** champs `@ArchTest` pour 28 `ArchRule`. Ce n'est pas une regle oubliee : la 28e est
`ScanDeComposantsTest.aucuneClasseDeTestSurLeClasspathDeLApplicationNePorteDeStereotype` (`ADR-0021`,
de ma main), declaree en `ArchRule` et executee explicitement par `.check(...)` depuis **deux** methodes
`@Test` - parce qu'elle a besoin d'un jeu de classes construit a la main (le classpath de l'application
seul, puis le perimetre de production pour son contre-exemple), ce qu'un `@ArchTest` ne permet pas. En
s05 les deux nombres coincidaient (25 et 25) ; ils ne coincident plus, et un futur comptage qui
prendrait `@ArchTest` pour la mesure des regles annoncerait 27. **Que les 28 soient toutes passees a un
`.check(` n'est pas verifie par moi mais par
`LesGardeFousSontMisALEpreuveTest.aucuneRegleArchUnitNEchappeAuContreExemple`, qui est vert.**

**Ce que ce nombre ne mesure pas, et c'est le point le plus important de cette section.** Le compte de
regles ArchUnit **n'est pas** la mesure de la couverture de garde-fous du sprint, et le lire ainsi
donnerait exactement le faux : il est passe de **27 a 28** entre la cloture de s05 (`506c2ab`) et
`be60b10`, soit **+1**, alors que le sprint a ajoute **neuf classes de garde-fou** (mesure : `git diff
--name-status 506c2ab..be60b10 -- "*/src/test/java/*.java"`, lignes en `A`), dont **huit ne declarent
aucune `ArchRule`** :

| Classe ajoutee en s06 | `ArchRule` | Ce qu'elle garde |
|---|---|---|
| `ScanDeComposantsTest` | **1** | ADR-0021 : aucune classe de test sur le classpath de l'application ne porte de stereotype Spring |
| `AucunContreExempleNEstUnBeanDeLApplicationTest` | 0 | ADR-0021, par un contexte assemble - propriete qu'aucune analyse statique n'atteint |
| `ToutEcranEchangeEstUnDocumentJouableTest` | 0 | ADR-0020 : le document assemble apres l'echange htmx |
| `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` | 0 | ADR-0022 : le couple marqueur d'etat / masquage declare |
| `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` | 0 | ADR-0023 : perimetre derive du catalogue |
| `ChaquePaquetExpliqueSesItemsAVariationTest` | 0 | ADR-0024 : les deux cles se gardent l'une l'autre |
| `LExplicationDUnItemReelAtteintLEcranDeRetourTest` | 0 | ADR-0024, part de `lea-dev` : le texte d'un item reel atteint l'ecran de retour |
| `LeConteneurTierGardeSonRembourrageLateralTest` | 0 | S6-8 : le rembourrage lateral declare du conteneur |
| `LeFondDeLIconePorteLaCouleurDuBandeauTest` | 0 | S6-10 : deux valeurs declarees comparees |

S'y ajoutent, hors Java, **trois scripts a juge pur** : `verifier-lignee.sh` et `verifier-jdk.sh`
(`sofia-sm`, S6-13/S6-14) et `mesurer-rendu.sh` (`ADR-0025`, ce commit), chacun avec son fichier de
contre-exemples. **Aucun n'est une `ArchRule`, et aucun ne pouvait l'etre** : ArchUnit lit du bytecode,
il ne rend pas une vue, ne lit pas une feuille de style, ne compte pas les items d'un YAML, n'assemble
pas un contexte Spring et ne juge pas un script shell. Les cinq `ADR` du sprint le disent chacun dans
leur section executable. Le bon resume d'une phrase : **le sprint a ajoute une regle ArchUnit et onze
garde-fous.**

## 3. Ce que j'ai mesure moi-meme avant de conclure, plutot que sur parole

| Mesure | Resultat |
|---|---|
| Plancher de `chrome --headless --window-size`, par `--dump-dom` sur une page qui ecrit `window.innerWidth` (methode differente de celle de `yanis-qa`, qui mesurait une boite englobante sur l'image) | 320, 400, 500 et **515** demandes rendent tous `innerWidth = 500` ; **516** rend 500 ; **520** rend 504 ; **600** rend 584. Plancher confirme, borne nette a 516 (= premier `L` ou `L - 16` atteint 500) |
| Viewport reel obtenu par la methode iframe de `mesurer-rendu.sh`, mesure en pixels sur la capture (barre noire de largeur `innerWidth` sur fond rouge) | largeur CSS 360 -> barre de **360 px**, fond du conteneur des `x = 360` ; largeur CSS 320 -> barre de **320 px**. Un viewport reel sous le plancher, ce que la commande directe ne sait pas produire |
| La meme methode sur l'application reelle (`recreo.jar` reconstruit a `be60b10`, port local), largeur CSS 360 | le bandeau d'en-tete occupe `x = 0` a `x = 359` dans l'iframe, fond du conteneur des `x = 360`. **Aucune conclusion tiree sur le debordement** : hors de mon sujet et hors du sprint |
| `ArchRule` a `506c2ab` puis a `be60b10`, meme methode aux deux commits | 27 puis 28 |
| Classes de garde-fou ajoutees en s06 | 9, dont 1 avec `ArchRule` |
| `flex-wrap` dans `recreo.css` a `be60b10` | **0**, pour 5 blocs `display: flex` dont 2 en colonne (donc 3 rangees). Chiffre qui motive un refus, section 5 |

## 4. Clean Architecture apres la fusion complete

Les regles de couches et de purete sont vertes sur l'arbre fusionne, et ce sprint a ajoute du code dans
les cinq modules - c'est la premiere fois que `domain`, `adapters` et `bootstrap` bougent ensemble en
une seule vague. Trois points que je verifie plutot que de les supposer :

1. **`ADR-0024` a fait entrer un champ dans `domain`** (`Question.explication`, `Verdict.explication`).
   `PureteDesCouchesTest` et `ArchitectureEnCouchesTest` sont verts, et surtout
   `AucunVocabulaireDeMatiereTest` - dont le perimetre s'etend depuis ce sprint aux types partages de
   `domain/content` - est vert avec "graphie", "phoneme" et "nasale" dans son vocabulaire. La frontiere
   posee par cet ADR n'est pas seulement redigee : le lot de `lea-dev` a livre quinze items declarant
   leur graphie **sans** qu'un mot de francais entre dans le domaine, et c'est l'automate qui le dit.
2. **Le plafond de dette de balayage vaut 2**, et la dette compte exactement deux noms
   (`FaviconTest`, `FrontieresDuJavaScriptTest`). Le plafond vaut la taille de la dette, pas une place
   de libre - la faute que le `relecteur-pedagogique` m'avait trouvee sur `ADR-0016` en s05 et que je ne
   veux pas rejouer.
3. **Le plafond de dette de contenu vaut 0** (`ADR-0016`), et le paquet recompose par `lea-dev` est
   passe sans y entrer.

## 5. Le trou de mesure de rendu - tranche par `ADR-0025`

L'obstacle route par `ines-design` puis `yanis-qa` est tranche, et il l'est en deux morceaux parce
qu'il en contient deux :

- **Le debordement de mise en page est declare non gardable** par un automate de ce depot, et confie a
  `nadia-prof`. Motif : c'est une propriete du rendu, que les memes declarations produisent ou non selon
  la police, la longueur du texte et l'algorithme de mise en page.
  `ViewportEtLargeurTelephoneTest` derive sa population des largeurs **declarees** : son perimetre n'est
  pas a elargir, il est complet pour ce qu'il annonce, et sa Javadoc porte desormais cette limite pour
  que son vert ne se lise pas comme "rien ne deborde". La piste du protocole DevTools est nommee avec
  les trois raisons chiffrees de l'ecarter aujourd'hui et le declencheur qui la rouvrirait.
- **Ce qui etait fermable est ferme** : la cause racine nommee par `yanis-qa` est qu'aucune des deux
  mesures de rendu du sprint n'a laisse de commande citable. `outils/mesurer-rendu.sh` porte desormais
  cette commande, refuse une fenetre parente sous le plancher de 516 px et une invocation sans profil
  isole, et son juge est une fonction pure testee par seize cas. **Je n'ai pas ecrit de garde-fou du
  debordement a 360 px** : ce defaut n'est pas etabli, `sofia-sm` a sorti la story du sprint pour ce
  motif, et un automate ecrit contre un defaut non etabli ne prouve rien. J'ai en revanche refuse par
  ecrit la seule regle declarative qui en approchait, avec le chiffre qui motive le refus (section 3,
  derniere ligne) : elle serait rouge sur trois blocs de production le jour de son ecriture, et la
  verdir demanderait de modifier la mise en page servie a un enfant pour satisfaire un test.

## 6. Une observation qui n'est pas de moi mais que je consigne, pour la retro

En ecrivant le test du juge de `mesurer-rendu.sh`, j'ai constate que **ni
`outils/verifier-lignee-test.sh` ni `outils/verifier-jdk-test.sh` n'etaient lances par aucun automate** :
`.github/workflows/ci.yml` ne les appelait pas. Deux juges purs, environnement-independants, ecrits et
testes en s06, que seule la main d'un agent executait. `ADR-0025` ajoute une etape `outils` a la CI qui
balaie `outils/*-test.sh` - perimetre derive du repertoire, pas une liste de trois noms. C'est le meme
motif que la dette 7 de la version precedente : un garde-fou annonce dont l'execution n'existait nulle
part.

## 7. Avis : porte architecture **ouverte**

La compilation, les tests unitaires, les tests d'integration Spring Boot, les regles ArchUnit et les
garde-fous de balayage sont verts sur l'arbre fusionne plus ce commit, avec les chiffres et les methodes
ci-dessus. Les cinq `ADR` du sprint portent chacun leur mecanisme executable ou, pour `ADR-0025`, une
clause "non gardable" motivee et nominative. Les deux plafonds de dette valent exactement la taille de
leur dette.

**Ce n'est pas le Done du sprint.** Le Done pedagogique appartient a `nadia-prof`, qui juge en jouant, et
deux proprietes de ce sprint lui reviennent entierement : le texte de chacune des explications du paquet
recompose, et tout ce qui se voit a l'ecran sous 500 px - pour quoi elle dispose desormais d'une commande
qui mesure ce qu'elle pretend mesurer.
