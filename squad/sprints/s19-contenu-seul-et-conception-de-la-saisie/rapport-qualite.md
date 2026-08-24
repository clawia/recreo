Rapport qualite - s19 (contenu seul et conception de la saisie), yanis-qa

Premiere intervention du sprint : aucune invocation de moi n'avait ete mesuree avant ce rapport, et
s18 s'etait deja clos sans moi. Mesure sur worktree dedie `../recreo-s19-yanis`, branche `s19-yanis`
(`git worktree add ../recreo-s19-yanis -b s19-yanis round-two`, lignee verifiee par
`bash outils/verifier-lignee.sh` - verdict : "le worktree sur la branche 's19-yanis' descend de la
lignee de round-two"), forkee de `round-two` a `51301d7`. Un commit ajoute par moi pendant
l'instruction (`c9a5052`, garde-fou de look-alikes d'espace insecable - section 6). Rien n'est
pousse, rien n'est fusionne.

## Verdict sur la porte technique

**`bash outils/mvn-jdk21.sh -B clean verify` sur `c9a5052` (racine du reacteur, JDK 21.0.10 de la
toolchain declaree) : BUILD SUCCESS.** Reacteur complet : `recreo`, `recreo-domain`,
`recreo-application`, `recreo-adapter-web`, `recreo-adapter-content`, `recreo-bootstrap`. Aucun
`-am` a citer : c'est une mesure du reacteur entier, jamais partielle. Aucun test instable
rencontre sur l'ensemble des executions de ce rapport (chaque commande listee ci-dessous a ete
executee une seule fois pour son chiffre final ; les executions intermediaires de mise a l'epreuve
sont explicitement decrites comme telles).

Ce rapport ne porte pas de Done pedagogique : ce n'est pas mon role, et rien ici ne s'y substitue.
Les quatre paquets de contenu neufs du sprint (`cm1-accords-groupe-nominal`,
`cm1-accord-sujet-verbe`, `cm1-antonymes`, `cp-nature-mot`) restent sous l'autorite exclusive de
`nadia-prof`.

## 1. Mon compte de tests, avec sa methode

**Methode** : somme des blocs `[INFO] Results:` du journal Maven d'un `clean verify` a la racine,
verifiee par la somme independante de l'attribut `tests="..."` des rapports XML de surefire et de
failsafe (`target/surefire-reports/TEST-*.xml`, `target/failsafe-reports/TEST-*.xml`) sur le meme
arbre. Les deux methodes s'accordent, ce qui exclut la troisieme cause de divergence qu'a nommee
`theo-archi` ce sprint (un test execute par deux moteurs JUnit, dont le rapport texte de surefire
ne garde qu'un) : cette divergence-la affecterait la somme XML autant que la somme des blocs
`Results:` si elle jouait, or les deux chiffres coincident exactement, a chaque mesure.

**Avant mon commit (arbre `51301d7`, tel que fourni par `sofia-sm`)** :

| Module | Tests |
|---|---|
| `recreo-domain` (surefire) | 48 |
| `recreo-application` (surefire) | 13 |
| `recreo-adapter-web` (surefire) | 328 |
| `recreo-adapter-content` (surefire) | 170 |
| `recreo-bootstrap` (surefire) | 92 |
| `recreo-bootstrap` (failsafe) | 6 |
| **Total** | **657** |

**657 confirme le chiffre annonce par `sofia-sm`, par ma propre mesure sur mon propre arbre**, et
non relaye : je l'ai reconstruit par les deux methodes ci-dessus avant de le comparer au chiffre
annonce, pas l'inverse.

**Apres mon commit (`c9a5052`)** : `recreo-adapter-content` passe de 170 a 177 (+7, le nouveau
fichier `AucunTexteServiNePorteUnLookAlikeDEspaceInsecableTest`, section 6). **Total : 664.**
Delta de mon lot : **+7**, sur l'arbre de base `51301d7`. Je n'annonce pas de total pour la squad :
`sofia-sm` l'etablira sur l'arbre fusionne.

## 2. Mon compte de regles ArchUnit, avec sa methode

**Methode** : balayage de tous les fichiers `.java` du worktree (`target/` et `.git/` exclus) pour
la declaration d'un champ `ArchRule` (`static final ArchRule` ou `static ArchRule`), en excluant
tout chemin contenant un segment `contreexemple` - ce segment designe soit les jeux fautifs des
regles reelles (qui ne declarent pas de champ `ArchRule` eux-memes, seulement des classes que les
regles reelles inspectent), soit le faux depot complet de
`LesGardeFousSontMisALEpreuveTest/faux-module`, qui lui en declare deux et qui ne doit pas etre
compte comme une regle de ce depot :

```
grep -rn "static final ArchRule\|static ArchRule" --include=*.java . | grep -v "/contreexemple/" | wc -l
```

**Resultat : 30 champs `ArchRule` reels**, repartis sur 12 fichiers de test : dans `bootstrap`,
`AbstractionsCiteesTest` (4), `AccesALEtatDeSessionTest` (2), `ArchitectureEnCouchesTest` (2),
`ChargementDuContenuTest` (1), `DecouplageMecaniqueContenuTest` (8), `DifficulteTest` (3),
`GestionDesErreursTest` (4), `LeChoixDesJeuxResteDerriereLePortTest` (1), `LesReglesMordentTest`
(1), `PureteDesCouchesTest` (2), `ScanDeComposantsTest` (1) ; dans `adapter-web`,
`AssertionsWebTest` (1). **Sur ces 30, 29 portent l'annotation `@ArchTest`** (verifie
par un second grep, `grep -c "@ArchTest"` par fichier, compare champ par champ) ; **la trentieme**,
`aucuneClasseDeTestSurLeClasspathDeLApplicationNePorteDeStereotype` de `ScanDeComposantsTest`, n'est
verifiee que par un `@Test` ordinaire qui appelle `.check(...)` sur elle a la main.

Ce compte **confirme independamment** celui de `theo-archi` dans `ADR-0058` ("30 regles reelles
apres son lot ... 29 portent `@ArchTest`, la trentieme etant appelee par un `@Test`") et celui
d'`ADR-0059` ("inchange par ce lot"), par une methode que j'ai etablie moi-meme avant de comparer,
pas en relisant la leur. **J'ai egalement verifie a la source** le troisieme piege qu'il nomme : en
lancant ensemble `DecouplageMecaniqueContenuTest` et `LesReglesMordentTest` (section 4),
le journal Maven porte bien deux lignes `[INFO] Running com.recreo.archi.LesReglesMordentTest`
distinctes (une pour le moteur ArchUnit avec 1 test, une pour Jupiter avec 28), confirmant que
cette classe est effectivement executee par deux moteurs et que `surefire-reports` n'en garderait
qu'un si on lisait le `.txt` plutot que le journal ou le XML.

Mon commit (`c9a5052`) n'ajoute aucun champ `ArchRule` : le compte reste **30** apres mon lot.

## 3. Les trois garde-fous de depot

```
bash .github/scripts/verifier-chemins-test.sh      -> tous les cas passent (22 cas nommes)
bash .github/scripts/verifier-prefixes-test.sh     -> tous les cas passent (15 cas nommes)
bash .github/scripts/commit-atteignable-test.sh    -> tous les cas passent (5 cas nommes)
```

**Mis a l'epreuve sur du materiau reel, pas seulement sur leur propre suite** :

- `verifier-chemins.sh` sur la liste complete des fichiers suivis du depot
  (`git ls-files -z | xargs -0 bash .github/scripts/verifier-chemins.sh`) : "Aucun chemin de poste
  de travail ni nom de compte dans les fichiers verifies", repete a chaque lot de fichiers passe en
  argument, aucune occurrence trouvee.
- `verifier-prefixes.sh` sur les sujets reels des 34 commits d'avance sur `origin/round-two`
  (`git log --no-merges --format=%s round-two..HEAD | bash .github/scripts/verifier-prefixes.sh`,
  mon propre commit inclus) : "Tous les commits portent un prefixe d'agent connu."

## 4. Tirage de garde-fous mutes sur du materiau reel

Regle d'engagement : casser le correctif que le garde-fou protege, constater le rouge, remettre le
correctif, constater le vert - jamais l'inverse, et jamais sur la fixture qui a servi a l'ecrire.

### 4.1 Clause 8 d'`ADR-0058` (`unContratNeDeclareAucunComportement`)

**Mutation** : `ContentSource.indicesNommentLaReponse()` (`domain/src/main/java/com/recreo/domain/
content/ContentSource.java`), production reelle, redevenue `default boolean ... { return false; }`
- exactement la forme qu'`ADR-0058` decrit comme le defaut d'origine, avant sa clause 8.

**Constat** (`bash outils/mvn-jdk21.sh -B -o -pl bootstrap -am test -Dtest=
DecouplageMecaniqueContenuTest,LesReglesMordentTest`, perimetre : `bootstrap` et ses dependances) :
**rouge**, `Method <com.recreo.domain.content.ContentSource.indicesNommentLaReponse()> does not
have modifier ABSTRACT`. Remis en l'etat (`boolean indicesNommentLaReponse();`), meme commande :
**vert**, 37 tests, 0 echec. La clause mord bien sur le contrat de production reel, pas seulement
sur les deux jeux fautifs commites (`NormalisationPartageeContreExemple`,
`EnonceNormaliseContreExemple`) qu'`ADR-0058` cite.

### 4.2 `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` (amendement de s11 sur les criteres enumeres)

Neuf paires aujourd'hui, quatre ajoutees ce sprint. Aucun membre de la liste ne nomme un incident
date et clos : chacun nomme un paquet de contenu actuellement servi, avec un motif structurel
(nombre de distracteurs, nature des propositions) - c'est une liste d'objets vivants, pas
d'incidents refermes, et l'amendement de s11 s'applique bien : mutation sur un objet **neuf absent
de la liste**, sur du materiau reel.

**Mutation** : retrait de l'entree `cm1-accords-groupe-nominal` (ajoutee ce sprint,
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java`), sur le paquet reel livre ce sprint
- pas sur une copie en memoire comme le font deja les quatre tests d'obligation 3 existants du
fichier.

**Constat** (`bash outils/mvn-jdk21.sh -B -o -pl adapters/adapter-content -am test
-Dsurefire.failIfNoSpecifiedTests=false -Dtest=LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`) :
**rouge**, trois echecs (le test principal et deux des quatre tests d'obligation 3, qui copient la
liste en memoire et heritent donc de l'absence) citant "paquet cm1-accords-groupe-nominal : 24
items tires, 0 classes - ce garde-fou ne l'a pas juge du tout, et rien ne le declare." Remis en
l'etat, meme commande : **vert**, 20 tests, 0 echec. La declaration mord bien sur le paquet reel
qu'elle est censee couvrir, pas seulement sur les fixtures fabriquees par les tests d'obligation 3
du meme fichier.

### 4.3 La regle d'`ADR-0059` (`UnPlafondMaisonNAffamePasLaRangeeTest`)

**Mutation** : `recreo.css` (`adapters/adapter-web/src/main/resources/static/css/recreo.css`,
production reelle), `max-width: 720px` -> `max-width: 540px` dans le bloc `.entete-contenu,
.zone-jeu` de la bande 720-767,98 px - exactement le chemin qu'`ADR-0059` nomme comme le plus
dangereux : recopier la valeur que la feuille tierce sert deja au palier.

**Constat** (`bash outils/mvn-jdk21.sh -B -o -pl adapters/adapter-web -am test
-Dsurefire.failIfNoSpecifiedTests=false -Dtest=UnPlafondMaisonNAffamePasLaRangeeTest`) : **rouge**
sur le test de production, `Expecting actual: 540.0 to be greater than or equal to: 579.0`. Les
quatre autres tests du fichier echouent aussi, en cascade, sur un message different
("l'ancrage ... n'y apparait pas une fois mais 0") : ils cherchent tous l'ancrage litteral
`max-width: 720px;` pour construire leurs propres mutations, et ma mutation directe du fichier de
production l'a deja consomme. Ce n'est pas un defaut du garde-fou, c'est la consequence attendue de
muter le meme fichier que celui que ces quatre tests copient et mutent a leur tour - remis en
l'etat, meme commande : **vert**, 7 tests, 0 echec. La regle mord bien sur la feuille de production
reelle, pas seulement sur les copies temporaires que ses propres tests fabriquent.

## 5. Obstacle 1 - `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` mord sans avoir ete annonce

**Verdict : defaut de documentation, pas defaut de la regle - et je l'ai reconstate en direct sur
mon propre code pendant ce meme rapport.**

La regle fait exactement ce qu'`ADR-0051` lui demande : refuser tout paquet dont aucun item ne
contribue au calcul de rang, sauf declaration explicite et motivee. Un "vert silencieux" y serait
le vrai defaut ; le rouge sur un paquet non declare est le comportement voulu, pas un accident. Ce
n'est donc pas la regle qui est en cause.

Ce qui est en cause, c'est qu'**aucun document que `marc-dev` ou `lea-dev` auraient consulte avant
d'ecrire leur paquet ne nomme cette contrainte** : ni `docs/backlog/backlog.md`, ni l'avis de
faisabilite du sprint, ni `planning.md` ne disent qu'un paquet de vocabulaire ou de grammaire (donc
sans propositions numeriques) doit etre ajoute a une liste declarative sous peine de rouge. La
regle est decouverte par le rouge, jamais par une lecture prealable - et c'est arrive deux fois,
independamment, le meme sprint, a deux developpeuses differentes.

**Ce sprint confirme que ce n'est pas isole a une regle.** `theo-archi` s'est fait mordre par
`LesGardeFousSontMisALEpreuveTest`, un garde-fou de **depot entier** (pas specifique au contenu) qui
impose la meme discipline a tout test qui lit un artefact de production : une mise a l'epreuve par
`assertThatThrownBy` et un jeu fautif commite, sous peine de rouge. **Je me suis fait mordre par ce
meme garde-fou en ecrivant le mien** (section 6) : mon premier jet, une fonction pure testee par
`assertThat(...).isNotEmpty()` sans jeu fautif commite, a fait echouer
`aucunGardeFouDeBalayageNEchappeALaMiseALEpreuve` en citant mon propre nom de classe - je n'ai lu
cette exigence nulle part avant de la heurter, exactement comme `marc-dev`, `lea-dev` et
`theo-archi` avant moi. **Quatre agents, trois regles differentes de la meme famille
(ADR-0013/ADR-0051), un seul sprint, un seul mode de decouverte : le rouge.**

Ce que je route a `sofia-sm` : les criteres auto-declaratifs d'`ADR-0013` (liste a completer,
mise a l'epreuve obligatoire) devraient etre nommes comme un cout connu dans l'avis de faisabilite
d'une story qui ouvre un paquet de contenu textuel ou un test qui balaie `src/main/`, pas seulement
dans la Javadoc du garde-fou lui-meme. Ce n'est ni une regle a assouplir, ni une regle a durcir :
c'est une case a cocher qui manque a l'endroit ou une developpeuse la lirait avant d'ecrire, pas
apres.

## 6. Obstacle 2 - le defaut d'encodage qu'aucune porte technique ne voyait

**Verdict : un garde-fou etait du, il ne l'etait sur aucun module avant ce rapport, et je l'ai
ecrit** - `adapters/adapter-content/src/test/java/com/recreo/adapter/content/
AucunTexteServiNePorteUnLookAlikeDEspaceInsecableTest.java`, commit `c9a5052`.

**Ce qu'il verifie exactement**, sur tout paquet present et a venir (perimetre derive du repertoire
`contenu/`, jamais d'une liste ecrite a la main, meme geste que `LeRangDeLaBonneReponse...`) :

1. aucun champ de texte servi (enonce, bonne reponse, distracteurs, indice, explication) ne porte
   l'un des deux artefacts connus d'une confusion d'espace insecable - l'entite HTML `&nbsp;`, et
   la sequence de six caracteres ASCII (antislash, la lettre u, quatre chiffres hexadecimaux) que
   `marc-dev` a corrigee a la main ;
2. tout guillemet francais ouvrant ou fermant qu'un champ de texte servi contient est immediatement
   flanque d'un espace insecable, jamais d'un espace ordinaire - la convention deja en vigueur pour
   `cm1-antonymes` et `cp-nature-mot`, verifiee ici une fois pour tout paquet plutot qu'une fois par
   paquet qui pense a l'ecrire.

**Pourquoi les tests par paquet qui existaient deja ne suffisaient pas** : `PaquetCm1AntonymesTest`
et `PaquetCpNatureMotTest` encodent chacun leur propre attente dans un litteral Java
(`Pattern.compile("« ([^»]+?) »")`), ecrit par la meme personne qui a ecrit le
paquet. Si la meme confusion frappe les deux gestes - le YAML et le regex qui le verifie -, l'un et
l'autre portent le meme artefact et le test reste vert. Verifie a la source : ces deux fichiers
utilisent bien le vrai caractere U+00A0 dans leur litteral (confirme par lecture des octets), donc
ils auraient mordu sur le defaut historique s'il avait survecu jusqu'au commit - mais rien ne
generalise cette protection aux deux autres paquets neufs du sprint (`cm1-accords-groupe-nominal`,
`cm1-accord-sujet-verbe`, qui n'utilisent pas cette convention et n'ont donc aucune protection de ce
genre), ni a un paquet futur dont l'auteur ne penserait pas a ecrire ce regex correctement.

**Preuve d'absence sur la production actuelle** : `aucunChampServiDeProductionNePorteUnLookAlikeDEspaceInsecable`
passe sur les douze paquets du repertoire (`cm1-antonymes` : 34 vraies occurrences de U+00A0,
`cp-nature-mot` : 32, confirmant les chiffres que `sofia-sm` avait releves apres coup).

**Mise a l'epreuve (ADR-0013 obligation 3), sur jeu fautif commite** :
`src/test/resources/contreexemple/AucunTexteServiNePorteUnLookAlikeDEspaceInsecableTest/`, un
paquet neuf jamais livre dont l'enonce porte l'artefact litteral entre guillemets, plus sa version
corrigee. **Piege trouve en l'ecrivant, et qui merite d'etre nomme explicitement** : mon premier
jet ecrivait cet artefact dans un champ YAML entre guillemets doubles - or une chaine
**double-quotee** en YAML est elle-meme sujette a l'echappement `\uXXXX` (comme en JSON), donc
SnakeYAML **corrigeait tout seul** l'artefact en un vrai U+00A0 au chargement, rendant le jeu fautif
silencieusement... correct. Rouge attendu absent, faux vert trouve avant meme d'etre commis. Corrige
en passant le champ en guillemets **simples** (YAML ne traite aucun echappement dans ce cas), qui
laisse l'artefact litteral atteindre effectivement l'objet `Question` charge. Constat brut :
**l'echappement `\uXXXX` existe a trois niveaux independants susceptibles de se corriger ou de se
casser silencieusement l'un l'autre - javac (comme rappele par l'avertissement en tete du fichier),
YAML, et le remplacement de motif d'un `Matcher` (`replaceFirst`, evite au profit de
`String.replace`, litteral sur ses deux arguments) - et j'ai personnellement heurte les trois en
ecrivant sept tests cense verifier l'absence exacte de ce genre de confusion.**

**Second piege trouve en l'ecrivant** : ma premiere version du fichier definissait la constante
`ESPACE_INSECABLE` avec un caractere que je croyais etre U+00A0 et qui s'est revele, a la
verification octet par octet, etre un espace ASCII ordinaire (U+0020) - trace la plus probable :
une normalisation silencieuse par l'outil d'edition que j'utilisais pour composer le fichier. Le
test sur la production reelle (`aucunChampServiDeProductionNePorteUnLookAlikeDEspaceInsecable`) l'a
revele immediatement en rougissant a tort sur `cm1-antonymes` et `cp-nature-mot`, qui sont pourtant
propres : **c'est en confrontant le garde-fou a du contenu reel deja correct que le defaut est
apparu, jamais en le lisant.** Corrige par ecriture programmatique du caractere (`chr(0x00A0)`,
hors de toute chaine tapee a la main) plutot que par retype. **Je n'ai donc pas seulement ecrit un
garde-fou contre cette famille de defaut : je l'ai moi-meme commise deux fois en l'ecrivant, dans
deux couches differentes (le fichier YAML de fixture, puis le fichier Java du garde-fou), ce qui
confirme au-dela du doute raisonnable que "rien dans ce depot ne l'aurait vu si les developpeuses
ne l'avaient pas cherche" decrivait exactement l'etat des lieux avant ce commit.**

Ce que ce garde-fou ne fait pas, et je le dis avant qu'on me le demande : il ne verifie aucune autre
convention typographique (espace avant deux-points, point-virgule, point d'exclamation ou
d'interrogation), qui resterait a chiffrer sur du materiau reel avant d'etre gardee.

## Compte rendu court

**Porte technique : verte.** `bash outils/mvn-jdk21.sh -B clean verify` sur le reacteur complet,
avant et apres mon commit, BUILD SUCCESS les deux fois.

**Mes deux comptes** : 657 tests avant mon lot (confirme le chiffre de `sofia-sm`, methode double
- somme des blocs `Results:` et somme des attributs XML, les deux concordent), 664 apres (+7, mon
seul lot) ; 30 regles ArchUnit reelles, inchange par mon lot, confirmant independamment le compte
d'`ADR-0058`/`ADR-0059`.

**Trois mutations sur materiau reel** (clause 8 d'`ADR-0058`, declaration de `LeRangDeLaBonneReponse...`,
regle d'`ADR-0059`) : les trois mordent bien sur la production, pas seulement sur leurs fixtures -
rouge constate, correctif remis, vert reconstate a chaque fois.

**Ce que je route** : les criteres auto-declaratifs d'`ADR-0013` sont un cout systemique non nomme
dans les documents qu'une developpeuse consulte avant d'ecrire (obstacle 1), confirme par quatre
agents distincts ce seul sprint - dont moi, en ecrivant ma propre reponse a l'obstacle 2.

**Ce que j'ai trouve et corrige** : un garde-fou de look-alike d'espace insecable manquait
completement (obstacle 2) ; je l'ai ecrit, et je l'ai vu produire un faux vert puis un faux rouge
sur mes propres tentatives avant d'aboutir a la version commise - deux incidents du meme genre que
celui qu'il existe pour prevenir, trouves par la meme methode que ce depot privilegie : le confronter
a du materiau reel plutot que de le relire.
