# [Sprint s08] Confirmation ArchUnit - theo-archi

> **Amendement du 2026-08-11, second passage.** Ce document a d'abord ete ecrit sur `533967d`, avant
> la fusion de `S8-5` et des lots de `camille-po`, d'`ines-design` et de `nadia-prof`. Les sections 1
> a 4 restent telles qu'elles ont ete mesurees alors, et ne decrivent plus l'arbre final. La
> **section 5** porte la confirmation qui vaut pour la review : arbre final, comptes refaits, position
> sur la condition 7 et ecarts constates. En cas de divergence entre les sections 1-2 et la section 5,
> c'est la section 5 qui dit l'etat du depot.

- **Date** : 2026-08-11
- **Auteur** : theo-archi
- **Arbre juge** : `round-two` a `533967d` - l'increment du sprint fusionne, les quatre lots compris -
  **plus mon commit `1143464`**, qui ajoute la clause structurelle de la condition C4 et ses deux jeux
  fautifs. Le present commit n'ajoute que des fichiers de `squad/` et n'execute rien.
- **Provisionnement** : worktree dedie **hors de l'arbre du depot**, branche `s08-theo-archi-c4`
  forkee de `round-two`. Lignee verifiee avant toute ecriture, par le script et non a la main :
  ```
  $ outils/verifier-lignee.sh
  le worktree sur la branche 's08-theo-archi-c4' descend de la lignee de round-two (ancetre commun
  trouve dans son historique).
  ```
- **Prerequis** : `JAVA_HOME` sur un JDK 21, positionne le temps de chaque commande et jamais ecrit
  dans un fichier commite. Constat citable rendu par `outils/verifier-jdk.sh` sur ce poste :
  `java du PATH : 1.8.0_202 | JDK de compilation : 21.0.10 | JAVA_HOME : vide | toolchains lu par
  Maven : non (fichier present sous un nom non lu)`. **Toutes les mesures de ce document sont prises
  sur le JDK 21.0.10** ; le `java` du `PATH` ne compile pas.
- **Ce que je ne fais pas ici** : aucun Done pedagogique ni son anticipation - il appartient a
  `nadia-prof` seule, et le cabinet n'a pas encore ete joue. Aucun arbitrage produit. Aucune ligne
  dans `rapport-qualite.md`, dans `squad/conformite/` ni dans le planning de `sofia-sm`.

## 1. Le resultat de la commande

`mvn -B clean verify`

```
recreo ............................................. SUCCESS [  0.322 s]
recreo-domain ...................................... SUCCESS [ 10.723 s]
recreo-application ................................. SUCCESS [  2.418 s]
recreo-adapter-web ................................. SUCCESS [ 20.154 s]
recreo-adapter-content ............................. SUCCESS [  5.876 s]
recreo-bootstrap ................................... SUCCESS [ 57.797 s]
BUILD SUCCESS
Total time:  01:37 min
```

**342 tests, 0 echec, 0 erreur, 0 ignore.**

**Methode de ce comptage, dans le meme paragraphe que le chiffre.** Somme de l'attribut `tests` de la
balise racine `<testsuite>` des **59 rapports XML** produits par cette seule execution, sous
`*/target/surefire-reports/` et `*/target/failsafe-reports/` des cinq modules declares dans
`<modules>` du pom agregateur - jamais un balayage lance depuis la racine du depot, qui verrait les
worktrees des autres agents. Repartition : `adapter-web` 173, `bootstrap` 80 (dont 3 en failsafe),
`domain` 43, `adapter-content` 39, `application` 7. **Exclus explicitement** : les rapports `.txt` de
Surefire, qui ne conservent qu'un seul moteur JUnit par classe ; et toute execution anterieure, le
comptage etant fait **apres le `clean` de cette commande** - j'ai mute cette feuille trois fois pour
prouver la morsure de ma clause, et des rapports laisses par une suite mutee donneraient un total
juste avec des echecs faux.

**Rapprochement avec le repere de reference.** `339` sur `round-two` a `533967d`, `342` ici : les
**3** de plus sont les trois cas de `UnEcranNeGouvernePasLApparenceDUnAutreTest` - un cas sur la
production, un jeu fautif, son jumeau corrige. Aucun autre chiffre ne bouge.

## 2. Regles ArchUnit : 28, inchange - ma clause n'en fait pas une 29e

**Le compte est de 28, exactement comme a `533967d`.** Deux methodes, qui doivent donner le meme
chiffre et le donnent :

1. `git ls-files '*.java'`, filtre sur `src/test/java`, critere `ArchRule <nom> =` : **30 brut**. Deux
   de ces trente sont dans le **faux depot** de contre-exemple
   (`bootstrap/src/test/resources/contreexemple/LesGardeFousSontMisALEpreuveTest/faux-module/.../RegleContreExemple.java`,
   dont le chemin contient `src/test/java` sans etre un source de module) : ce ne sont pas des regles
   du depot, mais la matiere sur laquelle le recensement se prouve mordant. **30 - 2 = 28.** C'est
   l'une des trois causes de divergence que j'avais identifiees en annoncant 29 regles sans methode ;
2. balayage des seuls repertoires `src/test/java` des cinq modules declares au pom, meme critere :
   **28** directement, dont **27** portees par une annotation `@ArchTest` et **1** executee par un
   `.check()` explicite. Repartition : `bootstrap` 27, `adapter-web` 1 (`AssertionsWebTest`).

**Ma clause neuve n'est pas une `ArchRule`, et ne peut pas en etre une.** ArchUnit lit du **bytecode** ;
ni une feuille de style ni un gabarit n'y sont visibles. Le compte de regles ArchUnit ne bouge donc
pas : ce qui bouge est le nombre de tests, +3. C'est le meme constat qu'`ADR-0028`, `ADR-0029` et
`ADR-0030` : quatre `ADR` de suite dont les clauses executables ne sont pas des regles ArchUnit, parce
que ce que ce sprint garde vit dans des artefacts que le compilateur ne produit pas.

**Corollaire pour la relecture de la review** : une clause ajoutee a un garde-fou existant ne change
pas davantage le compte. Le chiffre de 28 ne dit rien de la couverture du sprint ; ce qui la dit est le
nombre de garde-fous de balayage recenses par `LesGardeFousSontMisALEpreuveTest`, qui exige de chacun un
jeu fautif et l'execute - ma clause y entre sans exemption, avec ses deux jeux fautifs, et la dette de
ce recensement reste a **2** noms pour un plafond de **2**.

## 3. Ce que ma clause ferme, et ce qu'elle laisse ouvert

Detail complet dans `ADR-0031`. En trois lignes pour la review :

- **ferme** : le canal de feuille de la condition C4. Aucune regle maison ne peut plus faire dependre
  l'habillage d'un ecran d'un marqueur d'un autre ecran, sous aucun combinateur. Morsure mesuree par
  deux mutations reelles de `recreo.css`, annulees ;
- **ouvert, nomme, mesure** : le verdict peut encore colorer la **coquille permanente**
  (`body:has(.est-juste) .entete` reste vert, mesure faite), et il peut encore passer par le
  **gabarit** plutot que par la feuille (une classe conditionnelle posee sur l'habillage a partir
  d'une variable de modele). La dette C4 est donc **reduite, pas fermee** ;
- **hors de portee de tout automate de ce depot** : l'identite des pixels de deux captures, qui est la
  facon dont `nadia-prof` verifie C4. Elle reste sienne (`ADR-0025`).

## 4. Le point de `lea-dev` que je ne traite pas : le lexique de jeu d'argent

`lea-dev` n'a pas ecrit de scan du lexique de jeu d'argent sur les textes et attributs, au motif que
son cabinet ne porte aucun texte. Le point est verse a `yanis-qa` et a `nadia-prof` ; je ne l'absorbe
pas dans mon geste, et **je ne decide pas de l'ecrire**. Mon avis de faisabilite, puisqu'il est
demande :

**Techniquement gardable, oui, et le depot a deja la machinerie** : `TexteAccentueDesGabaritsTest`
collecte les textes litteraux des gabarits, `AucunVocabulaireDeMatiereTest` juge un vocabulaire sur une
liste fermee. Une clause "aucun texte litteral ni aucune valeur d'attribut ne contient un mot du
lexique de jeu d'argent" s'ecrirait sur ces deux patrons.

**Mais elle ne vaudrait qu'a deux conditions, et sans elles je la refuserais** :

1. **son perimetre doit inclure les paquets de contenu**, pas seulement les gabarits. Le texte que
   l'enfant lit vient du contenu ; une clause qui ne balaie que `templates/` promettrait par son nom
   bien plus qu'elle ne verifie - c'est exactement le defaut qu'`ADR-0015` a corrige sur
   `HabillageDesEcransTest` ;
2. **sa liste de mots est son point faible connu, et il est documente** : la liste de mots interdits
   d'`ADR-0008` a du etre amendee en s07 parce qu'elle couvrait le verdict et pas le tirage. Une liste
   ecrite par son auteur ressemble a ce qu'il imagine. Elle doit donc etre eprouvee sur du materiau
   reel - les 61 paquets de `contenu-repris/` en sont un - et non sur une fixture de la main de celui
   qui l'ecrit.

**Et ce qu'elle ne garderait pas doit etre dit en meme temps** : C6 n'est pas un probleme de mots. Un
cabinet qui evoque une machine a sous le fait par sa forme, ses couleurs et son decor, qu'aucune liste
de mots ne voit. La moitie qui compte reste a `nadia-prof` sur le rendu. **Decision d'ecrire ou non :
a la review**, avec `yanis-qa` et l'arbitrage de capacite de `sofia-sm`.

## 5. Amendement du 2026-08-11 - confirmation sur l'arbre final

### 5.1 Perimetre et provisionnement

- **Arbre juge** : `round-two` a `bd9a8f0`, plus mon seul commit d'amendement. `bd9a8f0` porte, en plus
  de `533967d`, `S8-5`, `DEC-0018` et l'amendement de `DEC-0016`, la proposition chiffree
  d'`ines-design`, l'arbitrage prealable et les deux verdicts de `nadia-prof`, et mon `ADR-0031`.
- **Provisionnement** : worktree dedie hors de l'arbre du depot, branche `s08-theo-confirmation` forkee
  de `round-two`. Rien ecrit dans le depot principal, verifie apres coup : son arbre est reste propre a
  `bd9a8f0`. Lignee verifiee avant toute ecriture, par le script :
  ```
  $ outils/verifier-lignee.sh
  le worktree sur la branche 's08-theo-confirmation' descend de la lignee de round-two (ancetre commun
  trouve dans son historique).
  ```
- **Prerequis** : `JAVA_HOME` sur un JDK 21, positionne le temps de chaque commande, jamais ecrit dans
  un fichier commite. Constat de `outils/verifier-jdk.sh` sur ce poste, inchange depuis le premier
  passage : `java du PATH : 1.8.0_202 | JDK de compilation : 21.0.10 | JAVA_HOME : vide | toolchains lu
  par Maven : non (fichier present sous un nom non lu)`. **Toutes les mesures ci-dessous sont prises sur
  le JDK 21.0.10** ; le fichier de toolchains du poste est enregistre sous un nom mal orthographie et
  n'est donc jamais lu par Maven - il ne joue aucun role dans ces mesures, et le JDK est designe par
  `JAVA_HOME` seul.

### 5.2 Le dernier mot sur la condition 7 : je confirme le choix de `lea-dev` et j'ajoute une clause

**Je confirme les deux points sur lesquels `lea-dev` a tranche.** Ne pas toucher a
`leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul` est le bon geste : ce test lit le selecteur litteral
`.proposition`, dont le plancher general n'a pas bouge, et lui faire absorber l'exception l'aurait
transforme en une regle a deux branches dont aucune ne serait plus lisible. Ecrire l'exception comme un
**second plancher distinct**, avec un contre-exemple portant la valeur que `nadia-prof` avait nommement
refusee plutot qu'une valeur arbitraire, est exactement la forme que ce depot demande. `nadia-prof` l'a
elle-meme eprouve dans les deux sens, y compris sur le contournement par `calc(2rem * 0.9375)` : je n'ai
rien a y reprendre, et son observation que le garde-fou est une **egalite**, donc plus strict qu'elle ne
demandait, est juste et voulue - c'est sa condition 3 (ni variable, ni `calc`) qui l'impose.

**J'amende sur un point, et un seul : il manquait l'unicite.** Sa condition 7 refuse qu'un lecteur croie
2rem encore garde partout. Les deux tests de `S8-5` rendent l'exception lisible et la mordent la ou elle
est ecrite, mais ils **epinglent deux valeurs a deux selecteurs nommes** ; ils ne disent rien d'une
troisieme regle qui abaisserait la taille d'une proposition ailleurs. Or c'est precisement ce qui rend
la phrase "2rem partout, sauf 1,875rem sous le repli a quatre colonnes" vraie ou fausse. Mesure, pas
supposition : une regle `.cabinet .proposition { font-size: 1.5rem; }` ajoutee a la feuille de
production laissait **les 178 tests du module verts** - elle reste au-dessus du plancher de lisibilite
de 1,25rem et ne touche ni `.proposition` ni le selecteur du repli. L'enfant aurait vu une troisieme
taille qu'aucun arbitrage n'a accordee, et la suite aurait continue d'affirmer deux planchers.

**Ce que j'ajoute** :
`TailleDesTextesEtBoutonsTest.lesSeulesTaillesDePropositionSontLesDeuxReglesNommees`, dans
`adapters/adapter-web`. La feuille maison porte **exactement** les deux regles nommees, a leur valeur
exacte, et aucune autre ne fixe la taille d'une proposition. Trois choix meritent d'etre relus :

- **le sujet, pas la sous-chaine.** Une regle est jugee sur son **sujet** - son dernier compose -, et le
  jeton `.proposition` est cherche comme jeton. Sans cela,
  `.propositions:has(.proposition:nth-child(4))`, qui habille le **conteneur**, passerait pour une regle
  de taille des propositions parce qu'elle contient deux fois la sous-chaine. C'est la meme erreur que le
  `font-size:` trouve dans une variable Bootstrap (`ADR-0015`), et que le `!important` de s05 ;
- **le balayage descend dans les regles-@.** `blocsMaison`, que tout le reste du fichier utilise,
  aplatit la feuille en une table selecteur -> corps et attribue le corps d'une regle nichee sous
  `@media` a la cle `@media (...)`. C'est assez pour lire un plancher a un selecteur qu'on nomme ; jamais
  pour prouver qu'**aucune autre** regle n'en declare un. La feuille de production porte deja deux
  regles-@ : le canal etait ouvert, pas hypothetique ;
- **la liste est une enumeration, pas une exemption.** Si l'une des deux regles nommees disparait de la
  feuille, la clause rougit au lieu de se taire.

**Morsure mesuree sur la feuille de production reelle, mutee puis restauree** - pas sur une fixture de ma
main, seule facon de savoir si la clause voit ce que le monde ecrit :

| mutation de `recreo.css` | avant ma clause | apres |
| --- | --- | --- |
| `.cabinet .proposition { font-size: 1.5rem; }` ajoute | **178 tests verts** | rouge, la regle nommee dans le message |
| la meme, nichee dans `@media (max-width: 400px)` | verte | rouge, `sous @media (max-width: 400px)` dans le message |
| `.proposition { font-size: 2rem; }` retire | rouge (2 tests) | rouge (3 tests, dont l'enumeration incomplete) |
| exception reecrite `calc(2rem * 0.9375)`, memes pixels | rouge (1 test) | rouge (2 tests) |

Deux jeux fautifs commites accompagnent la clause, sous
`adapters/adapter-web/src/test/resources/contreexemple/TailleDesTextesEtBoutonsTest/` :
`troisieme-taille-de-proposition` et `taille-de-proposition-sous-media`. Le premier porte les deux
regles nommees **a leur valeur exacte** et respecte le plancher de lisibilite : les deux tests de `S8-5`
y sont verts, et c'est ce qui prouve que la clause n'est pas decorative.

**Un incident a relire, parce qu'il vaut plus que ma clause.** Ma premiere ecriture cherchait le jeton
par une expression reguliere. `AssertionsWebTest` l'a refusee au nom d'`ADR-0008` (quatre violations
nommees : le champ `Pattern`, sa compilation, l'appel a `matcher`, l'appel a `find`). Je n'ai pas touche
a la regle : j'ai reecrit la recherche a la main, sur le meme motif `indexOf` + test de frontiere que
`debutDeDeclaration` utilise deja dans ce fichier. **Un garde-fou que j'ai fait ecrire a mordu son
auteur avant la review** ; c'est le seul genre de preuve qui compte.

**Ce que je n'ai pas fait, et pourquoi** : aucun `ADR`. Cette clause n'etablit aucune decision neuve -
elle acheve l'encodage d'une exception que `nadia-prof` a accordee et que `DEC-0018` porte, sur un
garde-fou existant. `ADR-0026` (les colonnes) et `ADR-0031` (l'independance des ecrans) restent les
deux `ADR` du sujet, et `ADR-index.md` est a jour sans modification. Si quelqu'un veut une **deuxieme**
exception, elle se demande a `nadia-prof` ; la clause l'y renvoie dans son message d'echec.

### 5.3 Les comptes sur l'arbre final

`mvn -B clean verify`, JDK 21.0.10 :

```
recreo ............................................. SUCCESS [  0.293 s]
recreo-domain ...................................... SUCCESS [ 10.394 s]
recreo-application ................................. SUCCESS [  1.954 s]
recreo-adapter-web ................................. SUCCESS [ 23.598 s]
recreo-adapter-content ............................. SUCCESS [  4.852 s]
recreo-bootstrap ................................... SUCCESS [ 43.414 s]
BUILD SUCCESS
Total time:  01:25 min
```

**347 tests, 0 echec, 0 erreur, 0 ignore. 28 regles ArchUnit.**

**Methode du comptage des tests, dans le meme paragraphe.** Somme de l'attribut `tests` de la balise
racine `<testsuite>` des **59 rapports XML** produits par cette seule execution, sous
`*/target/surefire-reports/` et `*/target/failsafe-reports/` des **cinq modules declares dans
`<modules>`** du pom agregateur, lus **apres le `clean`** de cette commande. Jamais un balayage lance
depuis la racine du depot : `git worktree list` en rend **17** lignes au moment de cette mesure, soit le
depot principal et **16 worktrees**, tous de `s08`, repartis sur les huit agents - un balayage en verrait
tous les `target/`. Repartition : `adapter-web` 178,
`bootstrap` 80 (dont 3 en failsafe), `domain` 43, `adapter-content` 39, `application` 7. Exclus :
les resumes `.txt` de Surefire (voir 5.4).

**Methode du comptage des regles, deux methodes qui doivent concorder et concordent.** Critere
`ArchRule <nom> =`. (1) `git ls-files '*.java'` filtre sur `src/test/java` : **30 brut**, dont **2** dans
le faux depot de contre-exemple de `LesGardeFousSontMisALEpreuveTest`, dont le chemin contient
`src/test/java` sans etre un source de module - **30 - 2 = 28**. (2) balayage des seuls
`src/test/java` des cinq modules declares : **28**, repartis en `bootstrap` 27 et `adapter-web` 1
(`AssertionsWebTest`). **Ma clause n'est pas une `ArchRule` et ne peut pas en etre une** : ArchUnit lit
du bytecode, une feuille de style n'y est pas visible. Le compte de regles ne bouge donc pas ; ce qui
bouge est le nombre de tests, **+3**.

**Rapprochement avec les chiffres annonces - aucun ecart.** J'ai mesure le repere plutot que de le
deduire par soustraction : `round-two` a `bd9a8f0` construit dans un worktree detache jetable, hors de
l'arbre du depot, retire aussitot apres. Meme methode XML : **344 tests exactement** (`adapter-web` 175,
`bootstrap` 80, `domain` 43, `adapter-content` 39, `application` 7) et **28 regles exactement**. Les
**+3** de mon amendement sont les trois tests nommes en 5.2 - la clause sur la production et ses deux
jeux fautifs. Aucun autre chiffre ne bouge : 344 + 3 = 347, et les 344 sont mesures, pas supposes.

### 5.4 Ecarts constates

- **Le resume `.txt` de Surefire sous-compte de 7 tests sur 4 classes, et non sur 2.** Mesure sur
  `bd9a8f0` : XML 344, somme des `Tests run:` des `.txt` **337**. Les quatre classes et leur ecart :
  `LesReglesMordentTest` 26 contre 25, `AbstractionsCiteesTest` 5 contre 1, `AssertionsWebTest` 2 contre
  1, `ChargementDuContenuTest` 2 contre 1. Meme cause pour les quatre : deux moteurs JUnit dans la meme
  classe, dont le rapport texte ne garde qu'un. **Consequence pour quiconque compte** : le `.txt` n'est
  pas une source de comptage, et l'ecart n'est pas une constante a retenir - il grandit avec chaque
  classe qui melange `@ArchTest` et `@Test`. Le seul comptage stable est l'attribut `tests` du XML.
- **Le nombre de worktrees a bouge pendant la mesure, et il est plus grand qu'annonce** : huit
  annonces, **16** mesures par `git worktree list` a la fin de mon passage, contre **14** au debut - le
  mien et `s08-camille-matrice` sont apparus entre-temps. Le chiffre de huit compte les **agents**, pas
  les worktrees : plusieurs agents en ont deux, et un lot vit sous un nom de repertoire qui ne suit pas
  la convention `recreo-s08-<agent>-<sujet>`. Rien a corriger a personne, mais c'est exactement la
  raison pour laquelle un perimetre de comptage se derive du pom agregateur et non du systeme de
  fichiers, et pour laquelle j'ai mesure le repere `bd9a8f0` dans un worktree jetable a moi plutot que
  dans celui d'un autre agent.
- **Aucun ecart sur les chiffres annonces** : 344 tests et 28 regles a `bd9a8f0`, verifies par mesure.
