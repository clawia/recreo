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

> **Amendement date du 2026-08-27 (`theo-archi`, s21, story `S21-6`) - la mesure ci-dessus est
> intacte, c'est le constat cite qui est corrige.** Le chiffre **21.0.10 est exact**, et a la date
> de cette mesure c'etait bien le JDK qui compilait : `JAVA_HOME` etait pose a la main sur lui pour
> la duree de la commande, et l'enveloppe `outils/mvn-jdk21.sh` n'existait pas encore (creee le
> 2026-08-17, commit `a9c182d`). **Ce qui est faux, c'est le champ `toolchains lu par Maven` du
> constat cite** : `outils/verifier-jdk.sh` le renseigne en regardant `${HOME}/.m2/toolchains.xml`,
> ou `HOME` est la variable du shell POSIX, alors que Maven lit `${user.home}/.m2/toolchains.xml`,
> ou `user.home` est le profil utilisateur de la machine virtuelle Java. Sur ce poste ce sont
> **deux repertoires differents** : le constat repond donc sur un autre fichier que celui dont il
> parle, et son verdict `non (fichier present sous un nom non lu)` ne decrit pas ce que Maven a lu.
> Cela ne change rien au JDK employe ce jour-la - c'etait `JAVA_HOME` qui le designait - mais le
> constat ne peut pas servir de preuve sur ce point. Aucun chiffre n'est remplace ici.
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

## 5. Cloture sur l'arbre fusionne - section ajoutee le 2026-08-17 (s14), et pourquoi si tard

**La section 0 de ce fichier annoncait : "il sera amende a la review sur l'arbre fusionne, section
ajoutee et non reecrite".** Elle ne l'a pas ete. C'est une quatrieme absence de suite, d'une forme
nouvelle et pire que les precedentes : les quatre premieres etaient des fichiers qui n'existaient
pas, celle-ci est une promesse non tenue **a l'interieur** d'une ceremonie qui a bien eu lieu -
`retro.md` de s13 la nomme comme telle. Un fichier publie a l'ouverture pour echapper a la ceremonie
de cloture s'est fait absorber par cette ceremonie quand meme, sur la moitie qu'il lui avait laissee.
La section est ecrite maintenant, avec un sprint de retard, et le retard fait partie du compte-rendu.

- **Auteur** : `theo-archi`. **Date** : 2026-08-17.
- **Arbre juge** : `round-two` a **`e30566b`**, la tete du depot a l'ouverture de s14 - donc l'arbre
  fusionne de s13, increments de `nadia-prof`, `camille-po` et `ines-design` compris.
- **Provisionnement** : worktree dedie hors de l'arbre du depot, branche `s14-theo` forkee de
  `round-two`, lignee verifiee par `outils/verifier-lignee.sh`.
- **Prerequis** : le `java` du `PATH` de ce poste n'est pas un JDK 21. Toutes les mesures sont prises
  sur le **JDK 21.0.10** designe par la toolchain de l'utilisateur, via `bash outils/mvn-jdk21.sh`
  (constat citable rendu par `outils/verifier-jdk.sh` : `java du PATH : 1.8.0_202 | JDK de
  compilation : 21.0.10 | JAVA_HOME : vide | toolchains lu par Maven : oui`).

> **Amendement date du 2026-08-27 (`theo-archi`, s21, story `S21-6`) - la mesure ci-dessus est
> intacte, c'est la phrase qui l'explique qui est corrigee.** Le chiffre **21.0.10 est exact** :
> reverifie le 2026-08-27 a trois sources sur un `bash outils/mvn-jdk21.sh -B clean verify` a la
> racine du reacteur - les lignes `Toolchain in maven-compiler-plugin` et
> `Toolchain in maven-surefire-plugin` du journal, les proprietes `java.version` et
> `java.vm.version` d'un rapport surefire produit par cette execution, et la version majeure 65 de
> l'en-tete d'une classe du fat jar. **Ce qui est faux, c'est le raccordement affirme** :
> `outils/mvn-jdk21.sh` lit `${HOME}/.m2/toolchains.xml`, ou `HOME` est la variable du shell POSIX,
> tandis que Maven resout sa toolchain dans `${user.home}/.m2/toolchains.xml`, ou `user.home` est le
> profil utilisateur de la machine virtuelle Java. Sur le poste de mesure ce sont **deux fichiers
> differents** : le 2026-08-27, celui du shell declare un **JDK 25.0.2** et celui que Maven lit un
> **JDK 21.0.10**. L'enveloppe pose donc `JAVA_HOME` sur le 25.0.2, qui gouverne la machine
> virtuelle de **Maven lui-meme**, pendant que Maven compile et execute les tests sur le 21.0.10 de
> **sa** toolchain. Le script imprime pourtant `toolchain jdk 21 : 25.0.2` a chaque lancement, sans
> jamais comparer les deux valeurs. **Ce que la phrase d'origine aurait du dire** : le 21.0.10 est
> etabli par le journal du build et par les rapports surefire, jamais par la ligne de diagnostic de
> l'enveloppe. Aucun chiffre n'est remplace ici, et la phrase d'origine reste lisible au-dessus.

### 5.1 Le resultat de la commande

`bash outils/mvn-jdk21.sh -B clean verify`, jamais un perimetre partiel :

```
recreo ............................................. SUCCESS [  0.605 s]
recreo-domain ...................................... SUCCESS [ 10.075 s]
recreo-application ................................. SUCCESS [  3.828 s]
recreo-adapter-web ................................. SUCCESS [ 28.006 s]
recreo-adapter-content ............................. SUCCESS [  9.417 s]
recreo-bootstrap ................................... SUCCESS [ 46.913 s]
BUILD SUCCESS
Total time:  01:39 min
```

**489 tests, 0 echec, 0 erreur, 0 ignore. 28 regles ArchUnit.**

**Methode du comptage des tests, dans le meme paragraphe que le chiffre.** Somme de l'attribut
`tests` de la balise racine `testsuite` des **76 rapports XML** produits par cette seule execution,
sous `*/target/surefire-reports/` et `*/target/failsafe-reports/` des **cinq modules declares dans
`modules`** du pom agregateur, lus **apres le `clean`** de cette commande. Jamais un balayage lance
depuis la racine du depot, qui verrait les worktrees des autres agents. Repartition : `adapter-web`
233, `adapter-content` 120, `bootstrap` 88 (dont 6 en failsafe), `domain` 47, `application` 7.
**Exclus explicitement** : les resumes `.txt` de Surefire, qui ne conservent qu'un seul moteur JUnit
par classe et sous-comptent.

**Methode du comptage des regles, deux methodes qui doivent concorder et concordent.** Critere
`ArchRule <nom> =`.

1. tous les fichiers `.java` du depot dont le chemin contient `src/test/java`, suivis **et** non
   suivis : **30 brut**. Deux de ces trente vivent dans le **faux depot** de contre-exemple de
   `LesGardeFousSontMisALEpreuveTest`, sous `src/test/resources`, dont le chemin contient
   `src/test/java` sans etre un source de module. **30 - 2 = 28** ;
2. balayage des seuls `src/test/java` des cinq modules declares au pom : **28** directement,
   repartis en `bootstrap` **27** et `adapter-web` **1** (`AssertionsWebTest`). `domain`,
   `application` et `adapter-content` en portent zero.

**Rapprochement avec les chiffres annonces en s13, et il y a un ecart a expliquer.** La section 1 de
ce fichier annoncait **464 tests** et **28 regles** sur `9430cfd`, la tete a l'**ouverture** de s13.
L'arbre fusionne en porte **489** et **28**. L'ecart de tests, **+25**, est le travail du sprint et
non une erreur de comptage : les deux methodes sont identiques a celles de la section 1, jusqu'au
nombre de rapports XML lus (74 alors, 76 maintenant - deux classes de test de plus). Le compte de
regles n'a pas bouge, ce qui est coherent avec `ADR-0041` et `ADR-0042`, qui declarent tous deux "le
compte reste a 28" avec leur methode.

### 5.2 L'inventaire des cliquets sur l'arbre fusionne

C'est la seule information que ce fichier porte et qu'aucun autre document ne republie (`ADR-0013`,
amendement du 2026-08-15). Etat au 2026-08-17 sur `e30566b`, lu dans les sources et non recopie de la
section 3 :

| cliquet | dette | plafond | jeu | assertion |
|---|---|---|---|---|
| `LesGardeFousSontMisALEpreuveTest.DETTE_DE_BALAYAGE` (`FaviconTest`, `FrontieresDuJavaScriptTest`) | 2 | 2 | 0 | `hasSize` |
| `LesGardeFousSontMisALEpreuveTest.CRITERES_ENUMERES_DECLARES` (`AucunVocabulaireDeMatiereTest`) | 1 | 1 | 0 | `hasSize` |
| `LeRangDeLaBonneReponse...Test.DETTE_DE_RANG` (`cm1-perimetre`, `cm1-mesures`) | 2 | 2 | 0 | `hasSizeLessThanOrEqualTo` - encore permissive |
| `LeRangDeLaBonneReponse...Test.DETTE_DE_SEUIL_MINIMUM` (`cm1-perimetre`, `cp-soustractions`, `cp-complements-10`) | 3 | 3 | 0 | `hasSizeLessThanOrEqualTo` - encore permissive |
| `ChaquePaquetPorteUnIndiceTest.DETTE_DE_CONTENU` (vide) | 0 | 0 | 0 | nulle par construction |

**Aucun cliquet du depot ne porte de jeu, et chaque exemption nomme encore quelque chose de reel** -
les cinq paquets cites existent tous dans `adapters/adapter-content/src/main/resources/contenu/`, et
ce n'est pas ma parole : les tests l'assertent eux-memes (`containsAll`,
`containsExactlyInAnyOrderElementsOf`, et une boucle sur chaque nom de dette). Les deux
`hasSizeLessThanOrEqualTo` restants n'ont **pas** de place libre a ce jour : plafond egal a dette sur
les deux. Ils restent permissifs par leur forme d'assertion, pas par leur valeur.

**Ce que l'inventaire de la section 3 omettait, et il faut le dire.** Il annoncait "les cinq cliquets
du depot". Methode du recomptage : `grep -rn "static final int PLAFOND" --include=*.java` sur les
cinq modules, hors `target` - il rend **six** constantes de plafond. La sixieme est
`FrontieresDuJavaScriptTest.PLAFOND_LIGNES = 100`, doublee de `PLAFOND_OCTETS = 4096`, et
`recreo.js` mesure **16 lignes pour 597 octets**. Il y a donc 84 lignes et 3 499 octets de place
libre.

**Ce n'est pas un cliquet dont le plafond depasse la dette, et la distinction n'est pas une
echappatoire.** Un cliquet d'exemption plafonne une **liste de choses exemptees** : sa place libre
laisse entrer en silence l'exemption suivante, et c'est pourquoi `ADR-0013` exige que son plafond
vaille exactement la taille de sa dette. Un **budget** plafonne la taille d'un artefact de
production, ici le JavaScript maison : sa marge est ce qu'il autorise a dessein (`ADR-0008`), et la
reduire a la taille du fichier du jour interdirait la ligne suivante sans avoir rien decide. Les deux
familles ne se comparent pas, et c'est pour cela que l'inventaire de la section 3 ne comptait que les
cliquets d'exemption - mais il aurait du le dire, et il annoncait "les cinq cliquets du depot" sans
qualifier. Corrige ici : **cinq cliquets d'exemption, jeu nul sur les cinq ; deux budgets de
production, marge large et voulue.**

### 5.3 Ce que cette section ne fait pas

Aucun Done pedagogique ni son anticipation, aucun arbitrage produit, aucune ligne dans
`rapport-qualite.md`, dans `squad/conformite/` ni dans le planning de `sofia-sm`. Les chiffres du lot
de s14 ne sont pas ici : ils vivent dans
`squad/sprints/s14-points-d-entree-par-niveau/confirmation-archunit.md`, qui est le fichier de ce
sprint-ci.
