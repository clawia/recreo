# [Sprint s13] Confirmation ArchUnit - theo-archi

- **Date** : 2026-08-15
- **Auteur** : theo-archi
- **Arbre juge** : `round-two` a `9430cfd` - la tete du depot a l'ouverture de s13, arbre propre -
  **plus mon commit d'amendement d'`ADR-0013`**, qui resserre deux assertions de cliquet et n'ajoute
  aucun test.
- **Provisionnement** : worktree dedie hors de l'arbre du depot, branche `s13-theo-faisabilite`
  forkee de `round-two`.
- **Prerequis** : `JAVA_HOME` sur un JDK 21, positionne le temps de chaque commande et jamais ecrit
  dans un fichier commite. Constat citable rendu par `outils/verifier-jdk.sh` sur ce poste, inchange
  depuis s03 : `java du PATH : 1.8.0_202 | JDK de compilation : 21.0.10 | JAVA_HOME : vide |
  toolchains lu par Maven : non (fichier present sous un nom non lu)`. **Toutes les mesures de ce
  document sont prises sur le JDK 21.0.10** ; le `java` du `PATH` ne compile pas.
- **Ce que je ne fais pas ici** : aucun Done pedagogique ni son anticipation, aucun arbitrage
  produit, aucune ligne dans `rapport-qualite.md`, dans `squad/conformite/` ni dans le planning de
  `sofia-sm`.

## 0. Pourquoi ce fichier existe maintenant et pas a la review

`sofia-sm` l'a inscrit aux criteres de cloture de s13 et le verifiera physiquement. **Je le publie a
l'ouverture plutot qu'a la review, et c'est delibere** : la publication a manque cinq fois, dont
quatre d'affilee, toujours par la meme mecanique - une ceremonie de fin de sprint qui absorbe le
temps qui reste. Un fichier qui existe des l'ouverture, sur un arbre nomme, ne peut plus etre
absorbe. Il porte l'etat de `9430cfd` ; il sera **amende a la review** sur l'arbre fusionne, section
ajoutee et non reecrite, exactement comme la section 5 de `s08`.

Ce que ce choix coute, dit franchement : les chiffres ci-dessous ne decrivent pas l'increment de
s13, qui n'existe pas encore. Ils decrivent le point de depart. C'est moins que ce que `sofia-sm`
attendait ; c'est plus que ce que quatre sprints ont produit.

## 1. Le resultat de la commande

`mvn -B clean verify`, jamais un perimetre partiel : `sofia-sm` a obtenu un faux rouge ce sprint sur
un jar `recreo-domain` perime, et un `-pl` seul rejoue ce defaut.

```
recreo ............................................. SUCCESS [  0.122 s]
recreo-domain ...................................... SUCCESS [  4.544 s]
recreo-application ................................. SUCCESS [  1.024 s]
recreo-adapter-web ................................. SUCCESS [ 12.484 s]
recreo-adapter-content ............................. SUCCESS [  4.034 s]
recreo-bootstrap ................................... SUCCESS [ 25.356 s]
BUILD SUCCESS
Total time:  47.844 s
```

**464 tests, 0 echec, 0 erreur, 0 ignore. 28 regles ArchUnit.**

**Methode du comptage des tests, dans le meme paragraphe que le chiffre.** Somme de l'attribut
`tests` de la balise racine `<testsuite>` des **74 rapports XML** produits par cette seule execution,
sous `*/target/surefire-reports/` et `*/target/failsafe-reports/` des **cinq modules declares dans
`<modules>`** du pom agregateur, lus **apres le `clean`** de cette commande. Jamais un balayage lance
depuis la racine du depot, qui verrait les worktrees des autres agents. Repartition : `adapter-web`
226, `adapter-content` 98, `bootstrap` 86 (dont 6 en failsafe), `domain` 47, `application` 7.
**Exclus explicitement** : les resumes `.txt` de Surefire, qui ne conservent qu'un seul moteur JUnit
par classe et sous-comptent - ecart mesure a 7 tests sur 4 classes en s08, et cet ecart grandit avec
chaque classe qui melange `@ArchTest` et `@Test`.

**Rapprochement, sans ecart.** `sofia-sm` annonce 464 tests et 28 regles dans `planning.md`, mesures
par elle sur le meme arbre. Je retrouve les deux, par mes propres commandes et non en recopiant les
siennes. **Mon amendement n'ajoute aucun test** : il change la forme de deux assertions existantes,
pas leur nombre.

**Methode du comptage des regles, deux methodes qui doivent concorder et concordent.** Critere
`ArchRule <nom> =`.

1. `git ls-files '*.java'` filtre sur `src/test/java` : **30 brut**. Deux de ces trente vivent dans
   le **faux depot** de contre-exemple de `LesGardeFousSontMisALEpreuveTest`, sous
   `src/test/resources`, dont le chemin contient `src/test/java` sans etre un source de module : ce
   ne sont pas des regles du depot, c'est la matiere sur laquelle le recensement se prouve mordant.
   **30 - 2 = 28.**
2. Balayage des seuls `src/test/java` des cinq modules declares au pom : **28** directement,
   repartis en `bootstrap` **27** et `adapter-web` **1** (`AssertionsWebTest`). `domain`,
   `application` et `adapter-content` en portent zero.

## 2. Ce que mon amendement change, et ce qu'il ne change pas

Detail complet dans `ADR-0013`, amendement du 2026-08-15. En trois lignes pour la review :

- **ferme** : la place libre dans un cliquet. Deux assertions passent de `hasSizeLessThanOrEqualTo`
  a `hasSize` - le plafond ne majore plus la dette, il vaut exactement sa taille. Sortir un nom de la
  dette oblige desormais a baisser le plafond dans le meme diff ;
- **ouvert, nomme** : rien n'oblige toujours une dette a etre payee. Aucune assertion ne le peut ;
- **ce qui ne bouge pas** : le compte de regles reste a **28** et le compte de tests a **464**. Une
  assertion resserree n'est ni une regle ArchUnit ni un test de plus.

**Morsure mesuree, sur le depot reel** : `PLAFOND_DE_DETTE` porte de 2 a 3, dette laissee a 2 -
l'etat exact qu'autorisait l'assertion precedente. `mvn -B -pl bootstrap -am test
-Dtest=LesGardeFousSontMisALEpreuveTest` : **1 echec sur 10**, `laDetteNePeutNiGrandirNiPourrir`,
message *"le plafond vaut exactement la taille de la dette. Un plafond superieur a la dette est une
place libre, donc pas un cliquet"*. Plafond restaure, suite verte, et la suite complete ci-dessus
tournee apres restauration.

## 3. L'inventaire que ce fichier existait pour porter

C'est la seule information que sa disparition emportait vraiment, et je l'ai cherchee avant de
decider : `ADR-0013` faisait descendre son plafond de dette **par la republication de cet
inventaire**. Rien d'autre dans le depot ne le republiait. Quatre sprints sans publication, plafond
inchange depuis le 2026-08-07.

Etat des **cinq cliquets** du depot au 2026-08-15. Chaque exemption nomme quelque chose de reel, et
ce n'est pas ma parole : les tests l'assertent eux-memes (`containsAll` sur les garde-fous existants,
`containsExactlyInAnyOrderElementsOf` sur les criteres declares).

| cliquet | dette | plafond | jeu | assertion |
|---|---|---|---|---|
| `LesGardeFousSontMisALEpreuveTest.DETTE_DE_BALAYAGE` (`FaviconTest`, `FrontieresDuJavaScriptTest`) | 2 | 2 | 0 | `hasSize` - **verrouille ce commit** |
| `LesGardeFousSontMisALEpreuveTest.CRITERES_ENUMERES_DECLARES` (`AucunVocabulaireDeMatiereTest`) | 1 | 1 | 0 | `hasSize` - **verrouille ce commit** |
| `LeRangDeLaBonneReponse...Test.DETTE_DE_RANG` (`cm1-perimetre`, `cm1-mesures`) | 2 | 2 | 0 | encore permissive |
| `LeRangDeLaBonneReponse...Test.DETTE_DE_SEUIL_MINIMUM` (`cm1-perimetre`, `cp-soustractions`, `cp-complements-10`) | 3 | 3 | 0 | encore permissive |
| `ChaquePaquetPorteUnIndiceTest.DETTE_DE_CONTENU` (vide) | 0 | 0 | 0 | nulle par construction |

**Aucun cliquet du depot ne porte de jeu aujourd'hui.** C'est le fait a retenir de cet inventaire, et
il est meilleur que ce que je craignais en l'ouvrant : le plafond n'a pas descendu depuis s06, mais
il n'a jamais depasse sa dette non plus. Les deux resserrements restants sont dus au lot de S13-1,
pour la raison de lot ecrite dans `ADR-0013`.

**Le compte des publications, avec sa methode**, puisque c'est le chiffre que ce sprint discute :
`git ls-files 'squad/sprints/*/confirmation-archunit.md'` rend **huit** fichiers (s00, s01, s02,
s03, s05, s06, s07, s08) ; `squad/sprints/` contient **treize** repertoires de sprints clos plus
celui-ci. Cinq absences : s04, puis s09 a s12. `DEC-0030` et `planning.md` disent "quatre", et
`planning.md` corrige en note "cinquieme tentative" : les deux comptent les absences **consecutives**
et ont raison sur ce compte-la. L'absence de s04 est anterieure, documentee, et d'une autre nature -
ce sprint n'avait aucun dossier de ceremonie, et j'avais ecrit ma part dans le fichier de s03 plutot
que d'inventer un dossier.

## 4. Ce que je n'ai pas fait, et pourquoi

- **Je n'ai pas retire `ADR-0013` de son appui sur ce fichier en le supprimant.** La seconde branche
  que `sofia-sm` laissait ouverte - etablir qu'il ne reviendra pas - etait recevable, et je ne l'ai
  pas prise. Motif : ce fichier porte une information qu'aucun autre document ne porte, et je viens
  de la mesurer plutot que de la supposer. `review.md` de s12 reconstruit les comptes de tests et de
  regles ; il ne republie **pas** l'etat des cinq cliquets, qui est la seule chose qu'`ADR-0013`
  attendait de cette publication. Supprimer le lieu sans deplacer l'inventaire aurait ferme le
  symptome et perdu le contenu.
- **Mais je n'ai pas non plus laisse la clause en dependre.** C'est l'objet de l'amendement : le
  fichier redevient un rapport - utile, lu, qui donne l'etat a un instant - et cesse d'etre le
  mecanisme d'une clause. Une clause dont l'execution depend d'une ceremonie n'est pas une clause,
  et cinq sprints l'ont demontre mieux qu'un raisonnement.
- **Aucun `ADR` neuf.** Cet amendement n'etablit aucune decision nouvelle : il substitue un mecanisme
  executable a un mecanisme humain, sur une clause deja acceptee. `ADR-index.md` porte la ligne de
  l'amendement, sans numero neuf.
