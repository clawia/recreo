# [Sprint s03] Rapport qualite (partiel) - yanis-qa

- **Perimetre de ce rapport** : les trois obstacles routes par `theo-archi` a `yanis-qa` dans
  `planning.md` (dette orpheline de `TexteAccentueDesGabaritsTest`, comptage divergent de
  `LesReglesMordentTest`, concordance des tags d'agent), traites dans un worktree isole avant la
  fusion des trois worktrees de developpement (`s03-maths`, `s03-forme`, `s03-viewport`). Ce
  rapport **ne couvre pas** les stories S3-1 a S3-9 : leur tracabilite et le rapport qualite
  complet du sprint seront ecrits une fois l'arbre fusionne, comme prevu par `planning.md`
  ("Prochaines etapes").
- **Commit audite** : `5ee8ca7`.
- **Aucun Done pedagogique n'est prononce ici.** Ce rapport ne juge que le Done technique.

## 1. `mvn verify` - vert, constate et non suppose

Commande executee dans ce worktree, JDK 21 (`java.version` du pom parent) compile via un JDK 25
local (aucun JDK 21 n'etait installe sur ce poste ; `--release 21` impose par le pom, aucune
API posterieure a 21 utilisee) :

```
mvn -B clean verify
```

Resultat : **BUILD SUCCESS**, **141 tests, 0 echec** -
`31` (`recreo-domain`) + `7` (`recreo-application`) + `39` (`recreo-adapter-web`) +
`5` (`recreo-adapter-content`) + `57` (`recreo-bootstrap`, unitaires) +
`2` (`recreo-bootstrap`, integration Failsafe).

## 2. Dette orpheline - `TexteAccentueDesGabaritsTest` omettait `fragments/erreur.html`

**Constat de depart** : le test enumerait trois gabarits sur quatre
(`accueil.html`, `fragments/manche.html`, `fragments/retour.html`) et n'a jamais lu
`fragments/erreur.html` - precisement l'ecran qui fondait l'un des trois motifs de refus
pedagogique de s00. Une faute d'accentuation y serait passee sans rouge.

**Correction** : le perimetre balaye derive desormais de `templates/` (`Files.walk`, filtre
`.html`, tri), au lieu d'une liste ecrite a la main. La derivation est totale : chaque gabarit
trouve produit une lecture et une assertion, aucun n'est exempte par une propriete facultative.
Le critere reproche (la liste des quatre fautes deja corrigees) reste une liste ecrite a la
main - c'est un critere, ADR-0013 le permet explicitement.

**Preuve de morsure sur un objet neuf (ADR-0013 obligation 3)** : un jeu fautif commite dans
`adapters/adapter-web/src/test/resources/contreexemple/TexteAccentueDesGabaritsTest/gabarit-neuf-fautif.html`,
absent de la production, contient la faute `"ca."`. Le test
`leGardeFouMordSurUnGabaritAbsentDeLaProduction` execute la meme fonction de derivation sur ce
jeu fautif et exige le rouge - c'est un objet neuf, pas la mutation d'un gabarit existant.

**Protocole rouge-puis-vert, execute avant ce commit sur le fichier reel** (pas seulement sur le
jeu fautif) : une faute `"On verra ca. plus tard."` a ete ajoutee temporairement, sans commit,
dans `fragments/erreur.html`. `mvn -Dtest=TexteAccentueDesGabaritsTest test` a alors echoue :
`Tests run: 3, Failures: 1`, citant exactement
`src\main\resources\templates\fragments\erreur.html ne doit pas reintroduire une faute deja
corrigee`. La faute a ensuite ete retiree (aucune trace dans le diff commite - `git diff --stat`
verifie apres coup) et la suite est repassee verte. Ce protocole demontre que le garde-fou
precedent aurait laisse passer cette meme faute en silence : il ne lisait pas ce fichier.

**Dette d'`ADR-0013`** : `TexteAccentueDesGabaritsTest` sort de `DETTE_DE_BALAYAGE` dans
`LesGardeFousSontMisALEpreuveTest` ; le plafond baisse de 6 a 5. C'est une entree retiree, pas
ajoutee, conformement a la demande du planning.

## 3. Comptage divergent de `LesReglesMordentTest` - 22 et 21 sont tous les deux corrects, et ne
   mesurent pas la meme chose

**Reproduction, sur ce commit, protocole complet archive.**

`mvn -B -pl bootstrap -am -Dtest=LesReglesMordentTest -Dsurefire.failIfNoSpecifiedTests=false test`
sur `LesReglesMordentTest` seul produit, dans le journal Maven, **deux** lignes
`Running com.recreo.archi.LesReglesMordentTest` successives :

```
Running com.recreo.archi.LesReglesMordentTest
Tests run: 1, ...
Running com.recreo.archi.LesReglesMordentTest
Tests run: 21, ...
...
Results:
Tests run: 22, Failures: 0, Errors: 0, Skipped: 0
```

**Cause, verifiee et non supposee** : la classe est analysee par **deux moteurs JUnit Platform
distincts**, parce qu'elle combine `@AnalyzeClasses`/`@ArchTest` (traite par le moteur
`archunit-junit5-engine`, qui execute le champ `aucunContreExempleEnProduction` comme son unique
cas - 1 execution) et des methodes `@Test` classiques (traitees par le moteur standard
`junit-jupiter`, 21 executions). Le rapport XML Surefire de la classe
(`TEST-com.recreo.archi.LesReglesMordentTest.xml`) le confirme : `tests="22"`, 22 balises
`<testcase>`, dont une nommee `aucunContreExempleEnProduction` (moteur ArchUnit) et 21 nommees
d'apres les methodes `@Test` (moteur Jupiter). Le rapport texte (`.txt`) de la meme classe,
lui, n'affiche que `Tests run: 21` : Surefire ecrit un seul fichier `.txt` par nom de classe, et
la seconde execution (Jupiter) ecrase le resultat de la premiere (ArchUnit) dans ce fichier -
sans que le total agrege par la commande (`Results: Tests run: 22`) ni le XML ne perdent
l'information.

**Conclusion** : ni `yanis-qa` ni `theo-archi` ne se sont trompes. Chacun a mesure une chose
reelle avec un instrument different, sur le meme fichier, au meme etat de code :

- **21** = nombre de methodes `@Test` ecrites a la main dans la classe, chacune prouvant qu'une
  regle ArchUnit declaree ailleurs mord sur son contre-exemple designe. C'est le chiffre que lit
  quiconque ouvre le rapport texte (`.txt`) de Surefire pour cette classe, ou compte les methodes
  du fichier - et c'est le chiffre que porte `ADR-0013` ("vingt et une methodes ecrites a la
  main, une par regle").
- **22** = nombre de cas reellement executes par la JVM de test quand la classe tourne, tel que
  rapporte par le total agrege de Maven et par le XML Surefire (`tests="22"`) : les 21 methodes
  `@Test`, plus le champ `@ArchTest aucunContreExempleEnProduction` declare directement dans la
  classe, qui est un veritable garde-fou execute contre la production (pas seulement un
  contre-exemple prouve), execute par un second moteur JUnit Platform.

**Definition retenue pour la suite du depot** : "nombre de regles prouvees mordantes par une
methode ecrite a la main" (21) et "nombre de cas executes par la JVM quand la classe tourne"
(22) sont deux mesures legitimes et differentes du meme fichier. Ce rapport ne tranche pas
laquelle doit devenir *la* reference du depot - c'est une question de convention de
reporting, pas une erreur a corriger - mais il clot le desaccord de fond : les deux chiffres sont
exacts, la difference est un artefact de la maniere dont Surefire agrege deux moteurs
JUnit Platform sur une meme classe, pas une regle absente ni un double compte. Reconciliation
faite avec `theo-archi` sur le materiau ci-dessus (memes 141 tests de la section 1, meme
protocole).

## 4. Concordance des tags d'agent - trois endroits nommes par le planning, un quatrieme trouve

**Les emplacements verifies** : `.github/scripts/verifier-prefixes.sh` (le seul que la CI execute
reellement, via `.github/workflows/ci.yml`), `CLAUDE.md` (racine), `squad/README.md`. Un
**quatrieme emplacement**, non nomme dans le planning, recopie egalement les neuf tags :
`squad/EQUIPE.md` (table de roster ligne 10-18, et un rappel par tag dans chaque section
d'agent, lignes 30 a 119). Le depot duplique donc cette information a quatre endroits, pas trois.

**Concordance aujourd'hui** : les quatre emplacements citent exactement le meme ensemble de neuf
tags (`CAM, SOF, THEO, MARC, LEA, YAN, REL, NAD, INES`) - verifie par lecture et par le test
ci-dessous, qui passe au vert sur l'etat actuel du depot.

**Rendue verifiable plutot que constatee** : `ConcordanceDesTagsDAgentTest`
(`bootstrap/src/test/java/com/recreo/archi/`) derive l'ensemble canonique des tags de la ligne
`TAGS='...'` de `verifier-prefixes.sh` (la seule source que la CI execute), et verifie que
`CLAUDE.md`, `squad/README.md` et `squad/EQUIPE.md` citent chacun tous ces tags. Prouve mordant :
une mutation temporaire non commitee de `verifier-prefixes.sh` (ajout d'un tag `ZZZ` absent des
trois documents) a fait echouer le test en nommant exactement le document et le tag manquants ;
la mutation a ete revertee avant ce commit (`git diff --stat` verifie apres coup, aucune trace).

**Limite assumee, ecrite plutot que decouverte** : ce test verifie que chaque document cite
**tous** les tags canoniques ; il ne verifie pas l'absence de tag **surnumeraire** dans un
document (un texte de redaction contient d'autres jetons entre crochets - le placeholder `[TAG]`
dans l'exemple de format de `squad/README.md`, par exemple - qui ne sont pas des tags et
produiraient du bruit si on les traquait tous). Le sens de l'erreur retenu est celui d'`ADR-0013` :
une omission passe inapercue en silence, une citation surnumeraire se voit a la lecture normale
du document.

**Ce que je ne tranche pas seul** : `squad/EQUIPE.md` n'etait pas dans le perimetre initial du
planning. Je l'ai integre au garde-fou parce que c'est un test (pas une decision d'architecture),
mais je ne decide pas si le depot doit reduire ces quatre copies a une seule source generee - la
question depasse mon mandat et je la remonte a `sofia-sm` / `theo-archi` si elle doit etre
tranchee.

## 5. Ce que ce rapport ne couvre pas

- Aucune tracabilite de S3-1 a S3-9 : elle sera ecrite une fois les trois worktrees de
  developpement fusionnes, comme prevu par `planning.md`.
- Aucune confirmation ArchUnit regle par regle : c'est le mandat de `theo-archi`
  (`confirmation-archunit.md`), pas le mien. La section 3 ci-dessus est ecrite pour etre comparee
  avec la sienne sur le meme protocole, pas pour s'y substituer.
- Aucun Done pedagogique : seule `nadia-prof` le prononce, apres avoir rejoue l'increment complet
  du sprint.
