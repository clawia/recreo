# [Sprint s03] Rapport qualite - yanis-qa

- **Etat de ce rapport** : complete le 2026-08-06. Les sections 1 a 4 ci-dessous ont ete ecrites
  avant la fusion des worktrees de developpement, dans un worktree isole audite au commit `5ee8ca7`
  - elles portent sur les trois obstacles routes par `theo-archi` a `yanis-qa` dans `planning.md`
  (dette orpheline de `TexteAccentueDesGabaritsTest`, comptage divergent de `LesReglesMordentTest`,
  concordance des tags d'agent) et restent inchangees ci-dessous. Les sections 5 a 11 sont ecrites
  a la cloture, sur l'arbre fusionne (`HEAD` a `4c62f60`, qui inclut aussi le lot Bootstrap hors
  sprint - `ADR-0014`, `DEC-0008` - et le lot francais de s04, S4-1/S4-2) : elles couvrent la
  tracabilite S3-1 a S3-9 annoncee comme manquante par la version precedente de ce rapport, la
  tracabilite S4-1/S4-2, la verification independante du lot Bootstrap, le balayage des assertions
  fragiles et le compte de tests reel.
- **Commit audite (sections 1 a 4)** : `5ee8ca7`. **Commit audite (sections 5 a 11)** : `4c62f60`
  (`HEAD` de `round-two` au moment de ce rapport).
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

## 5. `mvn verify` sur l'arbre fusionne - vert, deux fois, JDK 21.0.10 reel

`JAVA_HOME` pointe sur le JDK 21 reel du poste (`21.0.10`, LTS, verifie par `java -version` avant
chaque commande - pas sur le JDK 25 qu'un fichier de toolchains mal nomme avait fait utiliser a
tort par trois agents en s03 selon le constat de `lea-dev`). Deux executions completes et
independantes de `mvn -B clean verify` depuis la racine, sur `4c62f60` :

- Execution 1 : **BUILD SUCCESS**, **180 tests, 0 echec** - 57 s.
- Execution 2 (nouveau `clean`) : **BUILD SUCCESS**, **180 tests, 0 echec** - 55 s.

Repartition identique aux deux executions (detail au tableau de la section 11).

**Une troisieme execution, la toute premiere de cette session, a echoue** avant ces deux vertes -
voir section 10 : ce n'est pas cache ici, c'est traite comme l'instabilite qu'elle est.

## 6. Tracabilite S3-1 a S3-9, critere par critere, sur les criteres amendes

Verifie contre la version de `docs/backlog/backlog.md` en vigueur a `4c62f60`, donc contre les
criteres de S3-1 amendes au commit `57b2183` (et son amendement du 2026-08-05 cite dans le
backlog) - pas contre les quatre criteres d'origine que `theo-archi` avait montres intenables.

### S3-1 - additions qui travaillent tout l'intervalle

| Critere amende | Test qui le nomme | Statut |
|---|---|---|
| 6500 tirages, chaque somme dans sa fourchette (table `DEC-0007` point 2 amende) | `AdditionsBorneesContentSourceTest.surSixMilleCinqCentsTiragesChaqueSommeResteDansSaFourchettePourVingtGraines` | Couvert - et renforce : verifie sur 20 graines declarees (0 a 19), suivant la note de methode non bloquante de `theo-archi`, pas sur une seule |
| "0 + 0" n'apparait jamais | meme test (`occurrencesZeroPlusZero` isole par graine) | Couvert |
| 50 parties independantes, jamais "0 + 0" en premiere manche | `AdditionsBorneesContentSourceTest.surCinquantePartiesIndependantesLaPremiereMancheNEstJamais0Plus0` | Couvert |
| Le seuil S1-7 (`DEC-0004` point 5, 5-15 %, cible 12,9 %) reste vert | `AdditionsBorneesContentSourceTest.surDeuxCentsTiragesAGraineFixeeEntre10Et30OntAuMoinsUnOperandeNul` (test S1-7 preexistant, non modifie, toujours vert sur `4c62f60`) | Couvert |
| Jugement pedagogique (equilibre reellement educatif) | - | Hors mandat technique, releve de `nadia-prof`, non couvert par un test et ce n'est pas attendu |

### S3-2 - indice sur trois echecs consecutifs, quel que soit l'item

| Critere | Test | Statut |
|---|---|---|
| Trois echecs consecutifs sur des items differents declenchent l'indice | `BanditManchotTest.troisEchecsConsecutifsSurDesItemsDifferentsDeclenchentAussiLIndice` | Couvert |
| L'indice est atteignable au plus tard a la 4e manche | `BanditManchotTest.publieSaLongueurMinimalePourQueLAideResteAtteignable` (longueur minimale = 4) et `unItemRateTroisFoisEstRepresenteAvecUnIndiceLaFoisSuivante` (l'indice apparait a la manche qui suit trois echecs) ; `CatalogueChargeableIT` compare `nombreDeManches` a `longueurMinimale()` pour chaque jeu du catalogue reel | Couvert |
| Le compteur se remet a zero apres une reussite, l'indice reapparait sur un second groupe de trois echecs | `BanditManchotTest.lIndiceReapparaitApresUnSecondGroupeDeTroisEchecsConsecutifs` | Couvert |
| Manche plus simple et indice se declenchent ensemble sans s'annuler | `BanditManchotTest.laMancheSimplifieeEtLIndiceSeDeclenchentEnsembleSansSAnnuler` | Couvert |

### S3-3 - l'indice vit dans le YAML, pas dans une constante du domaine

| Critere | Test | Statut |
|---|---|---|
| `AdditionsBorneesContentSource.java` ne porte plus de constante de texte enfant | - | Verifie par lecture directe du fichier (aucune constante `INDICE` ; la seule constante restante, `ACCEPTATION_OPERANDE_NUL_SUR`, est numerique) : **aucun test automatise ne garantit qu'une constante de texte ne sera pas reintroduite**, c'est une lacune reelle mais mineure - une regression demanderait un geste delibere, pas un oubli silencieux |
| Le paquet YAML porte l'entree de l'indice | - | Verifie par lecture directe de `cp-additions-jusqu-a-10.yaml` (`parametres.indice`) |
| Le texte reellement affiche reste identique caractere pour caractere ("Compte sur tes doigts.") | `YamlContentPackLoaderTest.lIndiceDuPaquetReelDesAdditionsCPResteIdentiqueApresSonDeplacementVersLeYaml`, nomme explicitement S3-3, charge le paquet de **production** reel (pas une fixture) | Couvert, et c'est le bon niveau : preuve de bout en bout, pas seulement de construction |
| `DifficulteTest` et les regles ArchUnit existantes restent vertes | Constate par l'execution complete (section 5) : `DifficulteTest`, 3 tests, 0 echec | Couvert |

### S3-4 a S3-9 (visuel, `ines-design`, `adapters/adapter-web`)

| Story | Critere | Test | Statut |
|---|---|---|---|
| S3-4 | Le chiffre de reponse pese au moins 2 rem et au moins autant que `.enonce` | `TailleDesTextesEtBoutonsTest.leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul` | Couvert |
| S3-5 | `.propositions` porte un `gap` d'au moins 1 rem | `TailleDesTextesEtBoutonsTest.lEcartEntreDeuxBoutonsDeReponseEstAuMoinsUnRem` | Couvert (proxy assume, voir la story elle-meme) |
| S3-6 | Sortie pleine (aplat) sur l'ecran de fin | `HabillageDesEcransTest.laSortiePleineEstUnAplatColoreDeLaPalette` **et**, au rendu reel via HTTP, `PartieControllerTest.lEcranDeFinDePartieRendLaSortieCommeUnBoutonPlein` | Couvert, aux deux niveaux (declaration CSS et rendu) |
| S3-6 | "Recommencer" en aplat sur l'ecran d'erreur | `HabillageDesEcransTest.recommencerResteUnAplatColoreSurLEcranDErreur` | Couvert |
| S3-6 | Sortie reste en contour sur jeu/retour (non-regression) | `HabillageDesEcransTest.laSortieResteEnContourSurLEcranDeJeuEtDeRetour` | Couvert |
| S3-7 | Favicon toujours 200/image (inchange depuis S2-3) | `FaviconTest.laRequeteFaviconRepond200AvecUnTypeDImage` | Couvert |
| S3-7 | Vignette 16 px dessinee a plat, pas reduite | `FaviconTest.laVignetteSeizePixelsEstDessineeAPlatEtNonReduiteDUnGrandDessin` | Couvert |
| S3-7 | Jugement pedagogique (dessin reconnaissable) | - | Hors mandat technique, `nadia-prof` |
| S3-8 (prealable) | `<meta name="viewport">` sur `accueil.html` | `ViewportEtLargeurTelephoneTest.accueilPorteLaBaliseViewport` | Couvert |
| S3-8 (prealable) | Aucune largeur fixee au-dela de 375 px, gabarits et CSS maison | `ViewportEtLargeurTelephoneTest.aucuneLargeurNestFixeeAuDelaDeLaCibleTelephone` | Couvert |
| S3-9 | Titre affiche "Récréo", accents compris | - | **Non couvert par un test.** Le fait est exact (`accueil.html:6` et `:22` portent "Récréo"), verifie par lecture et par `relecteur-pedagogique` (`relecture.md`, ligne 145), mais aucun test automatise ne l'assertionne : une regression vers "Recreo" non accentue ne serait pas attrapee par `mvn verify`. C'est un critere testable (comparaison de sous-chaine litterale, exactement du meme type que `TexteAccentueDesGabaritsTest`) qui n'a simplement pas ete ecrit. Je le signale comme un trou de tracabilite reel, pas comme un critere non testable a reformuler |
| S3-9 | Pas d'autre gabarit qui garde "Recreo" non accentue | - | Meme constat : verifie par lecture (aucune autre occurrence trouvee), non couvert par un test |

## 7. Tracabilite S4-1, S4-2, et verification independante de la preuve de decouplage exigee par `DEC-0008`

**Ce que `DEC-0008` exige de prouver** : que le francais (CM1 homophones, CP sons) entre au
catalogue sans qu'aucune ligne de `domain/mechanic` ni `domain/mechanics` n'ait ete modifiee.
`lea-dev` l'affirme dans son commit. Je ne l'ai pas cru sur parole :

```
git diff --stat 9af191c..4c62f60 -- domain/
```

**Sortie : vide.** Aucun fichier sous `domain/` n'apparait dans le diff entre la fin du lot
`marc-dev` (`9af191c`, avant le lot Bootstrap et avant le lot francais) et `4c62f60`. Le commit de
`lea-dev` seul (`a6c416c`) confirme la meme chose a lui seul : quatre fichiers touches, tous sous
`adapters/adapter-content` (`catalogue.yaml`, deux paquets YAML neufs,
`PaquetsFrancaisS04Test.java`), zero sous `domain`. Les deux paquets declarent `type: question`
(`ADR-0007`), le schema deja existant - aucun schema neuf non plus. **La demonstration que
`DEC-0008` demandait tient**, verifiee au diff et non affirmee : c'est la premiere preuve reelle,
sur du contenu livre et non sur un test de laboratoire (`laMemeMecaniqueJoueDeuxContenusDifferentsSansAucunChangementDeCode`
existait deja depuis S0-3), que le decouplage mecanique/contenu sert une matiere non
mathematique sans toucher au domaine.

### S4-1 - homophones grammaticaux CM1

| Critere | Test | Statut |
|---|---|---|
| Phrase a trou sur un couple parmi les cinq listes (a/à, et/est, on/ont, son/sont, ou/où) | `PaquetsFrancaisS04Test.homophonesCm1NePresenteQueLesCinqCouplesAttendusAvecUnUniqueDistracteurApparie` | Couvert |
| Bonne reponse une fois, unique distracteur = l'autre mot du couple | meme test | Couvert |
| Les cinq couples sont tous representes au moins une fois | meme test (`couplesCouverts` compare a la liste exacte) | Couvert |
| Le contenu demarre une partie via `CatalogueChargeableIT`, sans changement du domaine | `CatalogueChargeableIT.leCatalogueSeChargeEtChaqueJeuDemarreUnePartie` (boucle derivee sur tous les jeux du catalogue reel, `bandit-manchot-homophones-cm1` inclus, pas un cas nomme a part) + preuve au diff ci-dessus | Couvert |
| 20 manches, phrases non toutes identiques | `PaquetsFrancaisS04Test.homophonesCm1VarieSurVingtManchesConsecutives` | Couvert |
| Jugement pedagogique (niveau CM1, texte accentue) | - | Hors mandat technique, `nadia-prof` et `relecteur-pedagogique` |

### S4-2 - graphemes/phonemes CP

| Critere | Test | Statut |
|---|---|---|
| Mot ecrit, un seul son propose correspond effectivement au mot | `PaquetsFrancaisS04Test.leBonSonCpNePresenteQueDesSonsConnusEtCouvreLesTroisDigraphesCitesParLeReferentiel` | Couvert |
| Distracteurs absents du mot, jamais le meme phoneme sous deux formes | meme test (`doesNotContain`, `doesNotHaveDuplicates`) | Couvert |
| Les trois digraphes cites (ch, ou, on) + an/in/oi couverts | meme test (`sonsCouverts` compare a la liste exacte) | Couvert |
| Demarre une partie via `CatalogueChargeableIT`, sans changement du domaine | Idem S4-1, meme boucle derivee, `bandit-manchot-le-bon-son-cp` inclus | Couvert |
| 20 manches, mots non tous identiques | `PaquetsFrancaisS04Test.leBonSonCpVarieSurVingtManchesConsecutives` | Couvert |
| Jugement pedagogique (notation `[an]`, majuscules, choix des mots) | - | Hors mandat technique, `nadia-prof` |

## 8. Le lot Bootstrap (`ADR-0014`) - verification independante, pas la sienne

### Les deux dettes deja nommees par `theo-archi` lui-meme (fin de l'ADR, section "Consequences")

**`HabillageDesEcransTest.aucuneRessourceExterneNiPoliceTelechargeeALExecution` ne balaie que
`recreo.css`.** Verifie par lecture : exact, la constante `CSS` du fichier pointe uniquement
`static/css/recreo.css`. **Mais ce n'est pas un trou de couverture reel** : le critere general -
aucune ressource externe, aucune police telechargee - est tenu par
`RessourcesTiercesTest.aucuneFeuilleNiAucunGabaritNeVaChercherQuoiQueCeSoitSurLeReseau`, qui balaie
**toutes** les feuilles sous `static/` (donc `vendor/bootstrap.min.css` inclus) et tous les
gabarits, avec deux jeux fautifs prouvant sa morsure (`police-sur-le-reseau`, `gabarit-sur-cdn`). Le
defaut reel est un **nom qui promet plus que son perimetre**, pas une absence de garde-fou. Verifie
en plus sur le fichier reel : `bootstrap.min.css` ne contient que des `url()` en `data:` (icones SVG
encodees), zero `@font-face`, zero cible reseau - constate par lecture directe, pas suppose.

**`TailleDesTextesEtBoutonsTest` lit la source statique des gabarits, donc ne voit jamais
`.sortie-pleine`, rendu par `th:class` conditionnel.** Verifie par lecture de `retour.html` (l'attribut
`class` statique reste `"btn retour-accueil"`, seul `th:class` porte `'btn sortie-pleine'` a
l'execution) et confirme au code du test (`selecteursApplicables` lit `element.classNames()` sur
le DOM Jsoup du fichier source, jamais rendu). **Consequence reelle, au-dela du constat de
`theo-archi`** : le plancher de lisibilite et la cible tactile (S2-1) ne sont donc jamais verifies
sur `.sortie-pleine` par ce garde-fou - ils le sont aujourd'hui par coincidence, parce que
`.sortie-pleine` partage son bloc de declaration CSS avec `.demarrer, .recommencer, .encore`
(`recreo.css` lignes 75-77 et 94-96). Si quelqu'un retire un jour `.sortie-pleine` de ce
regroupement sans lui donner ses propres regles, aucun test ne le verrait. Le seul filet qui existe
sur ce bouton precis est `PartieControllerTest.lEcranDeFinDePartieRendLaSortieCommeUnBoutonPlein`,
qui verifie sa classe au rendu reel mais pas sa taille de police ni sa cible tactile.

### La preuve de morsure de `theo-archi` sur `!important` - reproduite a ma facon, et un trou trouve qu'il n'avait pas vu

`theo-archi` (`ADR-0014`) affirme avoir ferme un trou sur `!important` avec un jeu fautif
(`TailleDesTextesEtBoutonsTest/plancher-defait-par-le-tiers`) et quatre mutations du depot reel -
mais **aucune de ses quatre mutations ne porte sur `!important`** : ses quatre mutations testent le
mauvais emplacement d'un fichier vendorise, une empreinte alteree, l'ordre des feuilles et une
classe maison remplacee par `.btn` seul. Sa preuve de morsure sur `!important` repose donc
uniquement sur une fixture, jamais sur le CSS tiers reellement vendorise. J'ai reproduit la
mutation manquante moi-meme, sur le fichier reel :

1. `.btn` est la **seule** classe Bootstrap portee par un bouton reel (verifie par lecture de tous
   les gabarits : `btn demarrer`, `btn recommencer`, `btn retour-accueil`, `btn proposition`,
   `btn sortie-pleine`/`btn retour-accueil`, `btn encore` - jamais `btn-primary` ni une autre classe
   Bootstrap).
2. Copie de travail de `vendor/bootstrap.min.css`, ajout de `!important` sur la seule declaration
   `font-size` du bloc `.btn` reel (`font-size:var(--bs-btn-font-size);` devient
   `font-size:var(--bs-btn-font-size)!important;`), sans toucher a rien d'autre.
3. `mvn -pl adapters/adapter-web -am -Dtest=TailleDesTextesEtBoutonsTest test` : **BUILD SUCCESS,
   11 tests, 0 echec.** Le garde-fou reste vert alors que la garantie de lisibilite de `nadia-prof`
   vient d'etre defaite sur le seul bouton reel qui porte une classe Bootstrap.
4. Fichier restaure a l'identique (`cp` depuis la copie de sauvegarde), `git status` verifie apres
   coup : aucune trace, seul le fichier non suivi de `relecteur-pedagogique` (`relecture.md`,
   hors de mon perimetre) apparait.

**Cause identifiee, pas supposee.** `declareEnImportant(corps, "font-size")` cherche la premiere
occurrence litterale de la sous-chaine `"font-size:"` dans le bloc. Bootstrap 5 nomme ses variables
CSS `--bs-btn-font-size`, qui **contient** `"font-size:"` comme sous-chaine (`--bs-btn-font-size:1rem`).
Cette declaration de variable, ecrite avant la vraie regle `font-size:var(--bs-btn-font-size)`
dans le meme bloc `.btn`, est trouvee en premier, et sa valeur ("1rem", sans `!important`) est ce
que le garde-fou lit - la vraie regle, plus loin dans le meme bloc, n'est jamais atteinte. Ce n'est
pas un defaut de fixture : c'est la convention de nommage que Bootstrap 5 utilise pour **tous** ses
composants (`--bs-<composant>-<propriete>`), donc un motif qui se represente partout ou une classe
Bootstrap est appliquee a un bouton reel.

**Consequence** : la garantie du point 6 de la decision d'`ADR-0014` ("une garantie pedagogique est
toujours portee par une regle maison, jamais defaite par le tiers") **tient toujours en pratique
aujourd'hui**, parce qu'aucune classe Bootstrap actuellement utilisee ne porte reellement
`!important` sur `font-size` ou `min-height` - mais le garde-fou cense le garantir a l'avenir a un
angle mort exploitable par la bibliotheque tierce elle-meme sur son propre motif de nommage, pas
seulement par une fixture hypothetique. Ce n'est pas un `ADR` a rediscuter ni un incident a corriger
dans l'urgence ; je le remonte comme obstacle qualite nomme (mon mandat, pas celui de
`theo-archi`) pour que la detection soit corrigee (chercher `propriete + ":"` seulement en debut de
declaration, apres un `;` ou `{`, jamais en milieu de nom de variable).

### Les cinq proprietes de `nadia-prof` (tableau d'`ADR-0014`) - aucune orpheline

Verifie une par une par lecture de code, independamment du tableau de l'ADR :

- Cible tactile 44 px : `verifierCibleTactile` existe et mord (confirme par les jeux fautifs et par
  la mutation reelle "classe maison remplacee par `.btn` seul" que je n'ai pas eu besoin de refaire,
  deja documentee et coherente avec le mecanisme lu).
- Plancher de lisibilite 1,25 rem : `verifierPlancherDeLisibilite`, meme lecture, sujette au meme
  angle mort `!important` que ci-dessus pour la propriete `font-size`.
- Ecart >= 1 rem entre boutons de reponse : `.propositions { gap }`, proxy assume par `ines-design`
  elle-meme (S3-5), pas une regression.
- Reponse plus saillante que la sortie : explicitement laisse a `nadia-prof` par `ADR-0014`,
  aucune regression constatee dans `recreo.css` (blocs `.signal`/`.icone-signal` intacts).
- Forme independante de la couleur (coche/croix) : CSS maison (`clip-path`, pseudo-elements),
  jamais touche par Bootstrap - confirme par lecture de `recreo.css` lignes 175-232.

Aucune des cinq n'est devenue orpheline (plus verifiee nulle part) avec l'arrivee de Bootstrap ;
une seule (le plancher de lisibilite, via `font-size`) porte un angle mort de detection reel,
documente ci-dessus.

**Note ecrite pendant la redaction de ce rapport, sur un travail en cours et non commite.** Au
moment ou j'ecris ceci, `theo-archi` modifie en direct, dans ce meme worktree,
`TailleDesTextesEtBoutonsTest.java` et `HabillageDesEcransTest.java` (constate par `git status` -
non indexe par moi, je n'y touche pas). Son travail non commite, tel que je le lis a l'instant,
traite les deux dettes que je viens de decrire dans cette section : la classe rendue par `th:class`
et `th:classappend` (nouveau perimetre `etatsRendus`/`BoutonRendu`, avec jeux fautifs
`branche-conditionnelle-sans-plancher` et `classe-calculee`) et le nom trop large de
`HabillageDesEcransTest.aucuneRessourceExterneNiPoliceTelechargeeALExecution`, retiree au profit de
`RessourcesTiercesTest`, sous une reference `ADR-0015` que je n'ai pas encore lue. Je ne juge pas ce
travail : il n'est ni fini ni commite, je ne l'ai pas fait tourner sous mon propre `mvn verify`, et
ce n'est pas a moi de le valider ici. Ce que je constate en revanche : **il ne touche pas a
`declareEnImportant`**, donc l'angle mort `!important`/variable CSS Bootstrap que j'ai trouve par
ma propre mutation ci-dessus reste, a cette heure, entierement ouvert.

## 9. Assertions fragiles (`ADR-0013`) - balayage de ce qui a ete ecrit depuis le dernier rapport

Applique la regle d'`ADR-0013` (le perimetre se derive, le critere peut s'enumerer) a
`TailleDesTextesEtBoutonsTest`, `RessourcesTiercesTest`, `ViewportEtLargeurTelephoneTest`,
`ConcordanceDesTagsDAgentTest`, `PaquetsFrancaisS04Test`, `HabillageDesEcransTest`,
`FaviconTest.laVignetteSeizePixelsEstDessineeAPlatEtNonReduiteDUnGrandDessin`.

- **Aucune assertion HTML par expression reguliere** parmi ce qui a ete ecrit ce cycle : grep cible
  sur `Pattern.compile` et `.matches("` dans les trois modules de test ne remonte que
  `AssertionRegexContreExemple.java` (le contre-exemple **voulu**, celui qui prouve que
  `AssertionsWebTest` mord sur ce motif) et `ConcordanceDesTagsDAgentTest` (regex sur une ligne de
  script shell, pas sur du HTML). Toutes les autres lectures de CSS ou de HTML passent par un
  decoupage de blocs `{ }` fait a la main ou par Jsoup.
- **Les listes enumerees restantes sont des criteres, jamais des perimetres** :
  `TailleDesTextesEtBoutonsTest.aucunTexteNePasseSousLaTailleDeLaConsigne` enumere six selecteurs -
  mais c'est exactement la liste "au minimum" ecrite par `nadia-prof` dans le critere de S2-1, pas
  un perimetre de balayage (le perimetre des boutons, lui, est derive des gabarits). Meme lecture
  pour `SONS_ATTENDUS` et `COUPLES_HOMOPHONES_AUTORISES` de `PaquetsFrancaisS04Test` : ce sont les
  ensembles fermes que S4-1 et S4-2 definissent eux-memes comme critere ("les cinq couples
  suivants", "les trois digraphes cites").
- **Les deux dettes deja nommees et non corrigees, reprises en section 8**, sont reelles mais
  n'ont pas la forme d'un test vacuously vert : ce sont des perimetres qui ratent un objet
  precis (le CSS tiers pour l'une, la classe calculee pour l'autre), pas des tests qui passeraient
  quel que soit le comportement.
- **Un veritable trou trouve par ce rapport et absent des deux dettes deja nommees** : l'angle mort
  `!important`/variable CSS de la section 8, qui **est** de la forme qu'`ADR-0013` reproche - un
  garde-fou qui reste vert alors que le comportement qu'il pretend garantir est casse.

## 10. L'echec transitoire de `LesReglesMordentTest` - toujours pas reapparu ; une nouvelle instabilite, nommee

**Le fait signale par `theo-archi` a la cloture de s01** (`Tests run: 21, Failures: 2` une fois,
vert ensuite, huit puis douze reproductions vertes en s02) **n'est pas reapparu** dans les deux
executions completes de `mvn -B clean verify` de la section 5, ni dans l'execution ciblee de
`LesReglesMordentTest` faite pour la section 8 (`Tests run: 11` sur `TailleDesTextesEtBoutonsTest`
seul, sans rapport avec `LesReglesMordentTest`, mais l'ensemble du module `bootstrap` a aussi tourne
vert deux fois dans les executions completes). Constat coherent avec celui de `theo-archi` a la
cloture de s02 : aucune cause inventee, pas de reapparition mesuree a ce jour.

**Une instabilite distincte, observee par moi ce cycle et non par `theo-archi`.** La toute premiere
execution de `mvn -B clean verify` de cette session (avant les deux vertes de la section 5) a
echoue en **compilation de test** du module `recreo-application` :
`PartieServiceTest.java` ne trouvait plus les packages `com.recreo.application.catalog` et
`com.recreo.application.port.out`, ni les classes `GameDefinition`, `PartieService`,
`ContentCatalogPort`, `MechanicRegistryPort` - alors que ces cinq classes existent, compilent seules
sans erreur, et que le meme module a ensuite compile et teste vert (7 tests) deux fois de suite sans
qu'une seule ligne ne change. Je n'invente pas de cause : je note que main venait de compiler avec
succes (`Compiling 5 source files ... target\classes`) immediatement avant l'echec de test-compile
sur ces memes classes, ce qui exclut une erreur de code. Non reproduit sur deux tentatives
supplementaires. Je le signale nommement, comme le veut mon mandat sur un test instable, plutot que
de le relancer en silence jusqu'au vert et ne pas en parler : si un depot verifie sur un poste
different ou une CI le voit reapparaitre, ce sera la deuxieme instabilite non causee de ce depot,
et elle merite alors le meme traitement que `LesReglesMordentTest` en s02 (protocole avec archivage
systematique du rapport, pas une reproduction a la volee).

## 11. Compte de tests reel par module - les 180 annonces se verifient

Mesure directement sur les deux executions vertes de la section 5, identique aux deux :

| Module | Tests |
|---|---|
| `recreo-domain` | 38 |
| `recreo-application` | 7 |
| `recreo-adapter-web` | 66 |
| `recreo-adapter-content` | 10 |
| `recreo-bootstrap` (unitaires, Surefire) | 57 |
| `recreo-bootstrap` (integration, Failsafe) | 2 |
| **Total** | **180** |

**Les 180 tests annonces par `sofia-sm` se verifient exactement**, sur JDK 21.0.10 reel, deux
executions completes independantes. Le detail de `recreo-bootstrap` (57) inclut le double compte
deja reconcilie en section 3 : le champ `@ArchTest` de `LesReglesMordentTest` (1 execution, moteur
ArchUnit) et ses 21 methodes `@Test` (moteur Jupiter) comptent chacun separement dans ce total, ce
qui n'est pas une erreur - voir section 3.

## 12. Ce que ce rapport ne couvre pas

- Aucune confirmation ArchUnit regle par regle : c'est le mandat de `theo-archi`
  (`confirmation-archunit.md`), pas le mien. La section 3 ci-dessus est ecrite pour etre comparee
  avec la sienne sur le meme protocole, pas pour s'y substituer.
- Aucun jugement sur le fond pedagogique du contenu francais neuf (choix des phrases, des mots, de
  la notation des sons) : c'est `nadia-prof`, apres relecture de redaction par `relecteur-pedagogique`
  (`relecture.md`).
- Aucun Done pedagogique, ici comme partout : seule `nadia-prof` le prononce, apres avoir rejoue
  l'increment complet.
