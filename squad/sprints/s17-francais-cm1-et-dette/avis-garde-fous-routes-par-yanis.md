# Avis de mecanisme - les trois garde-fous routes par `yanis-qa`, et la bascule d'`ADR-0049`

- **Auteur** : theo-archi. **Date** : 2026-08-19.
- **Branche** : `s17-theo-gardefous`, worktree dedie, forkee de `round-two` a `150c428` ; lignee
  verifiee par `outils/verifier-lignee.sh`.
- **Repond a** : `squad/sprints/s17-francais-cm1-et-dette/rapport-qualite.md` (`yanis-qa`, branche
  `s17-yanis`), points 1, 2 et 3 ; plus la story de bascule de la graphie accentuee ouverte au backlog
  par `camille-po` (execution d'`ADR-0049`).
- **Chaque constat a ete verifie a sa source**, code et contenu, jamais sur le resume du routage. Les
  trois sont **exacts**. Deux etaient sous-estimes plutot que surestimes : le point 1 pour ce qu'il
  faut en conclure, le point 3 pour la condition qui le rend atteignable.
- **Porte technique** : `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, JDK 21.0.10 -
  **BUILD SUCCESS**, **567 tests** (48 + 13 + 275 + 138 + 87 en surefire, 6 en failsafe), contre 559
  a `150c428` : **+8**, tous des clauses ou des morsures neuves de ce lot.

## Verdict en une page

| Point | Verdict |
|---|---|
| 1. Le garde-fou du rang exclut son objet en silence | **Confirme, et remesure : 4 paquets sur 12, 88 items, zero contribution.** Corrige par une declaration executable (`ADR-0051`). **Non comble** : sur du texte, la propriete n'est pas gardable sans un arbitrage de `nadia-prof`, et c'est ecrit comme tel |
| 2. Le garde-fou de graphie est muet sur les blancs | **Confirme.** Corrige : le repliage absorbe les blancs de bord et les suites internes, **espace insecable comprise** - un blanc que `Character.isWhitespace` ne voit pas et qu'une relecture humaine ne voit pas non plus. `ADR-0049` amende, trois jeux fautifs neufs dont un qui doit rester vert |
| 3. Le faux rouge non nomme de la dette 8.2 | **Confirme, et sa condition precisee par la mesure : il n'est atteignable qu'en supprimant la compilation.** Conserve, motif ecrit ; les deux sens sont nommes dans la Javadoc des trois copies **et dans le message d'echec**, avec une clause qui empeche le second de disparaitre |
| 4. La bascule d'`ADR-0049` | **Aucun garde-fou neuf n'est du** : la regle d'unicite existante est le blocage, des que le corpus est uniforme. Ce que la bascule exige : **un seul commit**, 13 fichiers de paquet, 2 entrees d'inventaire, 0 ligne de production - et deux pieges nommes |

## 1. Le rang de la bonne reponse : ce qui est corrige, ce qui est declare non gardable

### Ce que j'ai verifie a la source

Le code d'abord : `verifierQueLeRangNEstPasPredictible` porte bien `if (repartition.total() == 0)
continue;`, et `rangDe` rend `null` des qu'un item n'a pas exactement deux distracteurs, qu'une des
trois propositions n'est pas numerique, ou que deux valeurs coincident. Un paquet dont **tous** les
items tombent dans un de ces cas ne contribue rien, et rien ne le dit.

**Ma propre mesure, et sa methode.** Je n'ai pas repris le tableau de `yanis-qa` : j'ai ecrit la clause
de declaration **avant** de remplir sa table, et je l'ai laissee me rendre la liste. C'est le garde-fou
lui-meme, sur les 12 paquets du repertoire servi et par le chargeur reel, qui a repondu
(`bash outils/mvn-jdk21.sh -pl adapters/adapter-content -am test -Dtest=...`, JDK 21.0.10, `-am` porte
parce que la mesure est partielle) :

| Paquet | Items tires | Items classes |
|---|---|---|
| `cm1-conjugaison-present` | 25 | 0 |
| `cm1-homophones` | 24 | 0 |
| `cp-comparer-nombres` | 24 | 0 |
| `cp-le-bon-son` | 15 | 0 |

**4 paquets sur 12, 88 items, zero contribution** - identique au releve de `yanis-qa`, paquet par paquet
et item par item. J'ai en plus verifie **le motif** de chacun fichier par fichier, plutot que de le
deduire du total nul (comptage des cles `distracteurs`, repartition du nombre d'entrees par item) :
`cm1-conjugaison-present` porte **trois** distracteurs sur 25 items sur 25, `cm1-homophones` en porte
**un** sur 24 sur 24 - ces deux-la sortent du domaine par le **nombre** de propositions, pas par leur
nature ; `cp-comparer-nombres` (`<`, `>`, `=`) et `cp-le-bon-son` (graphies de phonemes) en sortent par
leur **nature**. Deux familles, deux motifs, ecrits separement dans la declaration.

### Le point que le routage nomme a moitie, et qui decide de la reponse

Le rapport dit que le garde-fou frere declare ses exclusions partielles et que celui-ci n'a rien pour
les totales. C'est exact, et il faut aller un cran plus loin pour voir pourquoi la correction est due :
**une exclusion partielle laisse une trace, une exclusion totale n'en laisse aucune.** Un paquet
partiellement examine produit encore un taux qu'un lecteur peut lire et discuter ; un paquet
entierement ecarte ne produit rien du tout. Ce n'est pas la meme famille de defaut a un degre pres,
c'est le passage d'un chiffre discutable a un silence.

### Ce que je corrige : la declaration, avec quatre morsures

`ADR-0051`, regle dans le meme commit
(`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, 15 tests avant, **20 apres**) :

- un paquet hors du domaine et **non declare** est rouge ;
- un paquet **declare** qui contribue des items est rouge (declaration perimee) ;
- un nom qui ne designe **plus aucun paquet** est rouge (declaration fantome - la boucle sur les
  paquets ne peut pas le voir, il a donc sa propre lecture) ;
- un **motif vide** est rouge.

Les quatre morsures mutent la **declaration** et non un paquet invente : l'objet juge est la
declaration, le corpus reste les paquets reellement servis. **Pas de plafond** sur cette table,
contrairement aux deux dettes du meme fichier, et le motif est dans l'`ADR` : un plafond dirait qu'un
paquet textuel de plus est une degradation, alors que c'est le programme scolaire qui le demande.

### Ce que je declare non gardable, en le nommant

**Sur des propositions textuelles, la propriete n'est pas gardable en l'etat, et ce n'est pas un
manque d'effort : c'est une frontiere de mandat.** Il n'existe aucun ordre intrinseque sur trois mots.
Toute strategie qu'on encoderait - la proposition la plus longue, la premiere par ordre alphabetique,
celle qui reprend un mot de l'enonce - est une **hypothese sur ce qu'un enfant de CP ou de CM1 fait**.
La propriete d'origine ne vient pas d'un raisonnement de conception : elle vient d'un taux que
`nadia-prof` a mesure **en jouant** (64,6 % sur `cm1-tables-multiplication`), et le seuil minimal de
10 % est ecrit dans le fichier comme "celui de nadia-prof, pas un choix d'ingenieur". Encoder une
strategie textuelle de ma seule autorite imposerait une contrainte de contenu que personne n'a
demandee et ferait rougir du contenu pedagogiquement valide - ce qu'`ADR-0028` a deja refuse pour un
plafond de longueur.

**La question qui debloquerait un garde-fou sur du texte, routee a `nadia-prof` en une phrase** :
*devant trois propositions textuelles, existe-t-il une strategie qu'un enfant de ce niveau exploite
sans lire l'enonce, et laquelle ?* Le jour ou elle nomme une strategie et un taux, un second calcul
entre dans ce meme garde-fou, sur le modele de la majorite et du seuil minimal - un chiffre d'elle,
pas de moi.

**Ce que j'ai examine et ecarte, parce que c'etait la piste la plus tentante.** L'appariement de mots
entre l'enonce et la bonne reponse est la **seule** strategie textuelle que `nadia-prof` ait deja
nommee comme disqualifiante (`COMP-1` et `COMP-2` de son arbitrage de s17 : des items "resolubles par
appariement de mots avec le texte"). Elle est donc pedagogiquement fondee, et pourtant elle n'a pas sa
place ici : elle n'a de sens que sur une question posee **sur un texte**, et aucun sur des synonymes,
ou la bonne reponse ne partage par construction aucun mot avec l'enonce. Elle appartient a la story de
comprehension - sortie du sprint par `camille-po` - pas a une propriete qui traverse toutes les
matieres. L'y coller ferait une regle generale a partir d'un cas particulier.

### Le cout que cette correction fait porter a un autre lot, dit franchement

**Le jour ou `cm1-synonymes` est fusionne, le build est rouge jusqu'a ce que la ligne de declaration
soit ecrite.** C'est le comportement voulu - une exclusion totale ne doit plus pouvoir entrer en
silence - et c'est un cout reel sur la fusion du lot de `lea-dev`. Le message d'echec nomme la table,
le motif attendu et les deux issues ; il ne laisse pas deviner. **Route a `sofia-sm`** : que la fusion
de ce lot porte cette ligne dans le meme commit, comme n'importe quel garde-fou de contenu deja en
place. Je n'ecris pas la ligne moi-meme : le paquet n'est pas sur cette branche, et une declaration
ecrite d'avance pour un contenu que je n'ai pas mesure serait exactement le genre d'affirmation que
cette clause existe pour interdire.

## 2. La graphie de matiere : le repliage absorbe les blancs, insecable comprise

**Constat verifie a la source, il est exact** : `replie()` retirait les diacritiques
(`Normalizer` + `NON_SPACING_MARK`) et la casse, et **rien d'autre**. `"Francais"` et `"Francais "`
tombaient donc dans deux groupes de taille 1, et la clause d'unicite restait verte alors que le contenu
servi aurait porte deux chaines distinctes. C'est ma regle de ce sprint ; le defaut est de moi.

**Ce que je corrige** (`ADR-0049` amende, section "Amendement du 2026-08-19") : bords retires, suites
internes reduites a une seule espace.

**Ce que le routage ne pouvait pas voir, et qui etait le vrai risque de fixture.** `yanis-qa` a mute
une **espace ordinaire**. Si je n'avais corrige que ce cas, j'aurais ecrit une regle qui mord sur la
mutation qu'on m'a montree et reste muette sur la plus probable :
`Character.isWhitespace` **ne compte pas l'espace insecable** (U+00A0), par definition - elle ne compte
que les blancs qui autorisent un retour a la ligne. Or l'insecable est precisement le blanc qu'un
copier-coller depose dans un fichier de contenu, et le seul qu'une relecture humaine ne distingue pas
d'une espace. Le predicat retenu est `isWhitespace || isSpaceChar`.

**Mesure de la necessite du second predicat, pas affirmation.** Avec `isWhitespace` seule, sur les deux
jeux fautifs neufs, la suite rend **un echec et un seul** -
`unEspaceInsecableQuiSepareDeuxGraphiesFaitEchouerLeGardeFou` - tandis que le jeu du blanc ordinaire
mord toujours. Puis predicat restaure : 6 tests, 0 echec. Les deux clauses ne sont donc pas redondantes,
et ce n'est pas une opinion sur mes fixtures.

**Trois jeux fautifs neufs**, dont un **vert obligatoire** :

- `deux-graphies-separees-par-un-blanc` : la mutation de `yanis-qa` au caractere pres. L'espace vit
  **dans les guillemets** du YAML, jamais en fin de ligne, pour qu'aucun outil de mise en forme ne
  l'efface sans qu'on le voie ;
- `deux-graphies-separees-par-un-insecable` : le caractere est ecrit sous sa forme echappee `\u00A0`, echappement que la
  double quote de YAML interprete - **visible en relecture**, contrairement a l'octet ;
- `deux-matieres-differentes` : `Mathematiques` accentue et `Questionner le monde`, qui doivent rester
  **deux matieres**. Sans ce cas, les deux clauses precedentes seraient une interdiction d'ecrire deux
  matieres differentes deguisee en clause d'unicite - la lecon de la seconde mutation d'`ADR-0025`.

**La frontiere, ecrite dans la regle et dans l'`ADR`** : est absorbe ce qu'une relecture humaine ne
peut pas voir, rien de plus. `"Maths"` et `"Mathematiques"` restent deux matieres distinctes aux yeux du
repliage, et une divergence de ce genre passerait - mais elle se lit dans le diff du paquet, la ou un
blanc de bord ne se lit pas.

## 3. La dette 8.2 : le faux rouge est conserve, et voici la mesure qui le justifie

**Constat verifie et reproduit** : la comparaison ne lit que deux horodatages, jamais un contenu ni une
empreinte. Un `touch` sur `GameMechanic.java`, contenu strictement inchange, la fait rougir.

**Ce que j'ai mesure en plus, et qui change la reponse** (theo-archi, 2026-08-19, JDK 21.0.10, sur cet
arbre) :

| Geste | Resultat |
|---|---|
| `touch` sur `GameMechanic.java`, puis `mvn -pl application -am test -Dtest=Provenance...` | **vert, 2 tests** - Maven a recompile le fichier touche, l'horodatage du `.class` est passe apres celui du `.java` |
| meme `touch`, meme commande **plus `-Dmaven.main.skip=true`** | **rouge**, sur le message de fraicheur - le resultat de `yanis-qa`, reproduit |

**Le faux rouge n'est donc pas atteignable depuis une commande qui compile.** Il n'apparait qu'en
**supprimant la compilation**, et il apparait alors sur un `target/classes` dont plus rien ne garantit
qu'il correspond aux sources. Autrement dit, le seul geste qui produit ce faux rouge est celui qui rend
la mesure non verifiable : **le rouge est exact sur le fond**, meme quand sa cause immediate n'est pas
une modification de source. Il ne peut pas apparaitre dans `mvn -B clean verify`, la commande de la
porte technique, qui compile tout apres un `clean`.

**Verdict : conserve, et je ne le corrige pas.** Le corriger demanderait de comparer un contenu a un
binaire compile, donc de recompiler pour juger - un cout hors de proportion avec un rouge borne aux
invocations qui suppriment la compilation. `yanis-qa` a raison de dire qu'un rouge sans defaut est un
cout de confiance ; la reponse n'est pas de retirer la clause, c'est de faire en sorte que ce cout ne
soit jamais paye deux fois par le meme lecteur.

**Ce que je corrige, dans les trois copies** (`application`, `adapter-web`, `adapter-content`) :

- la **Javadoc de classe** nomme les **deux** sens - faux vert par `touch` sur le `.class`, faux rouge
  par avance d'horodatage du `.java` - et porte la mesure ci-dessus, y compris la condition qui rend le
  second atteignable ;
- la Javadoc de la methode extraite renvoie a cette mesure au lieu de ne nommer qu'un sens ;
- **le message d'echec** nomme les deux causes possibles et dit qu'il ne les distingue pas. C'est la
  que le correctif compte : un lecteur qui rencontre ce rouge doit savoir tout de suite qu'il peut
  chercher une modification de source qui n'existe pas ;
- une clause dans la morsure existante (`hasMessageContaining("Deux causes possibles")`) : sans elle,
  la seconde cause disparaitrait de la prochaine reecriture du message sans que rien ne le dise. **Une
  documentation qui n'a aucun test qui la tienne est exactement ce que ce depot appelle un souhait.**

**Sur 8.1, je n'ai rien a ajouter** : `yanis-qa` a rejoue les deux mutations et la clause tient par une
propriete generale (deux durees distinctes dans la meme epreuve, donc aucune constante cablee ne peut
satisfaire les deux). Verifie a la lecture, rien a corriger.

## 4. La bascule d'`ADR-0049` : ce qu'elle exige

**Aucun garde-fou neuf n'est du, et c'est le point principal.** La regle d'unicite d'`ADR-0049` **est**
le blocage demande, a une condition : que le corpus soit uniforme. Le mecanisme est symetrique et il
n'a pas besoin d'etre ecrit deux fois :

- **avant** la bascule, le corpus est en ASCII : un paquet neuf **accentue** est rouge ;
- **apres** la bascule, le corpus est accentue : un paquet neuf **en ASCII** est rouge.

Un garde-fou qui interdirait nommement l'ASCII demanderait de savoir quelle est la bonne orthographe
de chaque matiere, donc un dictionnaire ou une table de mots - ce qu'`ADR-0049` refuse explicitement
et ce qu'`ADR-0039` refuse en general. **L'unicite suffit ; ce qui l'arme, c'est l'uniformite du
corpus.**

**Consequence directe sur l'ordre : la bascule est un seul commit, jamais deux.** Tout etat
intermediaire est rouge par construction. Ce n'est pas une precaution, c'est une propriete de la regle.

**Ce que le commit doit porter**, mesure sur cet arbre :

| Objet | Cout | Verifie comment |
|---|---|---|
| Paquets servis | **12 fichiers**, une ligne chacun (`matiere: "Francais"` -> `"Français"`, `"Mathematiques"` -> `"Mathématiques"`) | 3 paquets en `Francais`, 9 en `Mathematiques` (lecture des 12 fichiers de `contenu/`) |
| Le treizieme paquet | `cm1-synonymes` (branche `s17-lea`) porte `matiere: "Francais"` : il entre dans le meme geste | `git show s17-lea:...cm1-synonymes.yaml` |
| Inventaire des chaines exposees | **2 entrees** de `TexteAccentueExposeParLePortEntrantTest` (S16-5) | ses deux clauses sont **bidirectionnelles** (toute chaine exposee est inventoriee, toute chaine inventoriee est encore exposee) : sans cette edition, les deux rougissent |
| Code de production | **zero ligne** | `grep -rn "\.matiere()" --include=*.java` sur `src/main` : aucune occurrence |
| Fiches, `MATRICE-couverture.md`, catalogue | hors de mon perimetre - `camille-po` | - |

**Deux pieges, nommes pour qu'ils ne soient pas decouverts par un rouge.**

1. **Un remplacement global de `Francais` dans le depot casserait la preuve de morsure de la regle.**
   Les jeux fautifs de `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` portent **deliberement**
   les deux graphies : `paquet-servi-en-ascii.yaml` doit rester en ASCII, sans quoi le garde-fou ne
   mord plus. La bascule touche `src/main/resources/contenu/`, **jamais**
   `src/test/resources/contreexemple/`.
2. **`contenu-repris/` n'est pas concerne** : ses 61 fichiers portent deja la forme accentuee et ne
   sont pas servis (le perimetre de la regle est le repertoire de contenu de `adapter-content`). Rien a
   y toucher.

**Une observation que je route sans la trancher, parce qu'elle depasse `ADR-0049`.** Le meme inventaire
porte **trois autres chaines** de ces memes descripteurs encore ecrites sans leurs accents, et
`S16-5` les a nommees "CONNU ET NON CORRIGE ICI" : un domaine (`Lecture et comprehension de l'ecrit`)
et deux competences (`Associer les graphemes aux phonemes (...)`, `Resoudre des problemes simples
d'addition/soustraction en situation (1 etape)`). Elles ne sont montrees a aucun enfant aujourd'hui -
aucun gabarit ne les rend - donc rien n'oblige a les corriger maintenant. Mais elles vivent dans les
**memes fichiers** et dans le **meme inventaire** que les matieres : le commit de bascule est le moment
le moins couteux de les corriger, et le seul ou l'oubli ne coute rien de plus. **Decision de perimetre,
donc `camille-po`**, avec `nadia-prof` sur l'exigence de `DEC-0002` amende.

## 5. Ce que ce lot mesure, avec sa methode

- **Porte technique** : `bash outils/mvn-jdk21.sh -B clean verify` a la racine du worktree,
  **BUILD SUCCESS**, reacteur complet (6 modules), JDK 21.0.10 (toolchain declaree ; le `java` du PATH
  n'est pas concerne). **567 tests** : 48 (`domain`) + 13 (`application`) + 275 (`adapter-web`) + 138
  (`adapter-content`) + 87 (`bootstrap`, surefire) + 6 (`bootstrap`, failsafe). Comptes lus sur les six
  lignes de synthese de module du journal, jamais sur une somme de lignes par classe.
- **+8 par rapport a `150c428`** (559) : **5** dans
  `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (la clause et ses quatre morsures, 15 -> 20)
  et **3** dans `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` (les deux morsures de blanc et le
  cas vert, 3 -> 6). Les trois copies de `ProvenanceDesClassesAmontEstLeDepotTest` n'ajoutent aucun
  test : la clause de message entre dans une morsure existante.
- **Mesures partielles** : toutes portent `-am` (`CLAUDE.md`, prerequis de build) et nomment leur
  module a cote de leur chiffre. Aucune n'est citee comme un chiffre de depot.
- **Le compte des regles ArchUnit ne bouge pas** : il reste a **29**. Aucune des deux regles de ce lot
  n'est une regle ArchUnit - l'objet juge est une table de declaration confrontee au contenu servi pour
  l'une, une valeur de cle de ressources pour l'autre.
- **Mutations posees puis retirees** : le predicat de blanc reduit a `isWhitespace` (mesure de la
  section 2, restaure et reverifie), et deux `touch` sur `GameMechanic.java` (section 3, contenu
  strictement inchange, `target/` reconstruit par le `clean verify` final). `git status --short` est
  propre hors des fichiers de ce lot.

## 6. Ce que cet avis ne fait pas

- Il ne prononce **aucun Done**, ni technique ni pedagogique.
- Il ne juge ni le contenu de `cm1-synonymes`, ni sa pedagogie, ni sa forme.
- Il ne tranche aucun perimetre : la sortie de `S17-2` du sprint est de `camille-po` et je la prends
  comme donnee, y compris dans le motif d'ecarter l'appariement de mots (section 1).
- Il ne modifie aucun fichier des autres lots hors des trois copies de
  `ProvenanceDesClassesAmontEstLeDepotTest`, que `yanis-qa` me route nommement et dont je ne touche que
  la Javadoc, le message d'echec et une assertion de morsure - aucune clause n'est retiree ni
  affaiblie.
