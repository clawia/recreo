# [Sprint s08] Confirmation ArchUnit - theo-archi

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
