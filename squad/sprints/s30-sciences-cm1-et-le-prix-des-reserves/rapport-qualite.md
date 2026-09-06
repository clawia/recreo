# Rapport qualite - Sprint 30

**Auteur** : `yanis-qa`. **Depot mesure** : mon worktree dedie (`git worktree add
../recreo-s30-yanis -b s30-yanis dbbfe129`), lignee verifiee - `git log` de ce worktree pointe sur
`dbbfe129`, meme commit que la branche `round-two` fournie, arbre propre confirme par `git status`
avant toute mesure. Toute mesure de ce rapport est lancee depuis ce worktree, jamais depuis le
worktree principal ni celui d'un autre agent (regle generalisee au planning de s30, section 6).

**JDK mesure** : 21.0.10, rapporte par la JVM des tests elle-meme (log Spring Boot des tests
d'integration - `"using Java 21.0.10"`), pas par la toolchain declarative.

---

## 1. Porte technique : verdict

**VERT.** `bash outils/mvn-jdk21.sh -B clean verify` sur le reacteur complet (racine, pas de `-pl`) :
**BUILD SUCCESS**, 2 min 11 s.

**Compte de tests, mesure independamment, methode de l'amendement s11** : somme des attributs
`tests`/`failures`/`errors`/`skipped` de la balise `<testsuite>` de chaque rapport XML de
`surefire-reports` et `failsafe-reports` de tous les modules du reacteur, apres le `clean verify`
ci-dessus (exclut les copies sous `target/test-classes`, qui ne sont pas des rapports mais des
fixtures de test recopiees) :

- **179 fichiers `TEST-*.xml`** (`find . -path "*/target/surefire-reports/TEST-*.xml" -o -path
  "*/target/failsafe-reports/TEST-*.xml"`, filtre des copies `test-classes`).
- **1321 tests, 0 echec, 0 erreur, 0 ignore** (script Python sommant les attributs, executé sur ce
  worktree apres le build ci-dessus).

Ce chiffre **confirme exactement** celui de `theo-archi` (`squad/sprints/s30-sciences-cm1-et-le-
prix-des-reserves/confirmation-archunit.md`, branche `s30-theo-verif`, non fusionnee au moment de
cette mesure : 179 rapports, 1321 tests, 0/0/0) - mesure de nouveau par moi sur ma propre copie de
l'arbre plutot que relayee. C'est ma propre mesure ci-dessus qui fait foi de ce rapport, quel que
soit le document auquel elle converge.

**Tous les modules portent des tests** (`find <module>/src/test -iname "*.java" | wc -l`) : `domain`
14, `application` 3, `adapters/adapter-web` 49, `adapters/adapter-content` 88, `bootstrap` 78. Aucun
module sans test.

**Commits du sprint** (`git log a5f2812..dbbfe129 --oneline`) : 15 commits, tous prefixes par un tag
d'agent connu (`[LEA]`, `[MARC]`, `[SOF]`, `[CAM]`, `[THEO]`, `[NAD]`) - aucun rejet CI attendu sur ce
point.

---

## 2. Le defaut cherche une troisieme fois : paquet neuf sans test de matiere, declaration inexacte

**Non trouve, cette fois.** Les quatre paquets neufs de sciences portent chacun son test de matiere,
present dans le meme commit que le contenu (verifie par `ls
adapters/adapter-content/src/test/java/com/recreo/adapter/content/ | grep -i sciences`, quatre
fichiers pour quatre paquets) :

| Paquet | Ligne | Test de matiere |
|---|---|---|
| `cm1-sciences-signal-information.yaml` | `SCI-CM1-05` | `PaquetCm1SciencesSignalInformationTest` |
| `cm1-sciences-chaine-alimentaire.yaml` | `SCI-CM1-17` | `PaquetCm1SciencesChaineAlimentaireTest` |
| `cm1-sciences-melanges.yaml` | `SCI-CM1-02` | `PaquetCm1SciencesMelangesTest` |
| `cm1-sciences-objet-technique.yaml` | `SCI-CM1-12` | `PaquetCm1SciencesObjetTechniqueTest` |

Chaque test verifie `FER-2` (>=10 items, mesure : 11 items chacun par `grep -c '^  - enonce:'`),
`FER-1` (elements du libelle en bonne reponse, verifie contre les ensembles de reponses attendues) et
`FER-3` (deux taches, la moins servie a >=3 items) **contre le chargeur de production**
(`YamlContentPackLoader`), pas contre une fixture ecrite a la main.

**Declarations de commentaire verifiees contre le contenu reel, une par une** (le defaut de s29 etait
precisement une declaration fausse dans un commentaire YAML) :

- Les quatre commentaires "S30-1"/"S30-2" annoncent des comptes precis (6+5 items par tache, 4+6+1
  par element de libelle). Verifie par lecture directe et par les assertions `FER-1`/`FER-3` des
  quatre tests, qui recalculent ces memes comptes sur le contenu charge - concordant partout.
- `indicesNommentLaReponse` : les quatre commentaires affirment "absent, vaut faux". Verifie par
  `grep -n "^indicesNommentLaReponse"` sur les quatre fichiers - **aucune occurrence** : la cle
  n'est ecrite nulle part en tete de fichier, seulement citee dans le texte du commentaire.
- Les deux commentaires `SCI-1`/`SCI-4` (signal-information : item de l'ambulance ; melanges : item
  eau/huile) annoncent que l'item flatte designe porte trois propositions. Verifie par comptage des
  `distracteurs` : `signal-information` a 5 items a un distracteur (binaire) et 6 a deux distracteurs
  (dont l'item de l'ambulance) ; `melanges` a 5 items a un distracteur et 6 a deux (dont l'item
  eau/huile). Les deux items nommes portent bien deux distracteurs, donc trois propositions -
  declaration exacte.

**Sur les reprises du sprint, meme controle applique par echantillon** (le sprint nomme `FOP-R2` comme
exemple d'une declaration fausse corrigee) :

- `FOP-R2` (`cm1-emc-fait-opinion.yaml`) : le commentaire affirme que le chiffre de "huit" distracteurs
  deja conformes, ecrit a s29, etait faux - cinq seulement l'etaient, cinq disaient encore une absence
  litterale de verification. Verifie sur les dix distracteurs de verification (items 2, 4, 6, 8, 10) :
  aucun ne contient plus "sans rien verifier" ni "repeter sans verifier" - la correction annoncee est
  reellement dans le fichier.
- `HG-R7` : le texte-support requis mot pour mot par le backlog (`squad/conformite/ARBITRAGE-...`,
  story `S30-5`) est present a l'identique dans `cm1-histoire-geographie.yaml` (comparaison
  caractere pour caractere).
- `HG-R6`, `CYV-R1`, `VIV-R2`, `DRO-R1`, `DRO-R2`, `DRO-R3` : verifies par lecture directe contre le
  texte du backlog - tous conformes (le distracteur "une carte de France" a disparu des deux items de
  nature ; le mot "declaration" n'apparait plus dans le texte-support du second ; les six indices
  binaires de `droit-devoir` ne nomment plus la categorie ; l'adulte nomme devient masculin sur les
  items 3 et 4).

**Rien de faux trouve cette fois.** Le controle reste manuel sur les points que le code ne peut pas
juger (voir section 4) : je ne peux pas prouver qu'une declaration est juste au-dela de ce qu'un
comptage structurel verifie.

---

## 3. Le garde-fou mis a l'epreuve sur le materiau reel : `AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest`

**Mordu moi-meme, pas releve du chiffre annonce.** Mutation sur la sortie reelle du serveur, pas sur
une fixture : suppression de la ligne
`<p class="question-rappelee" th:unless="${juste}" th:text="${enonceRevele}"></p>` dans
`adapters/adapter-web/src/main/resources/templates/fragments/retour.html` (etat exact d'avant
`ADR-0084`), puis
`bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest -Dsurefire.failIfNoSpecifiedTests=false`.

**Resultat : ROUGE**, 2 des 3 tests de la classe echouent, avec **58 griefs sur 58 ecrans** (un par
paquet servi - `grep -c "sans porter la question"` sur le rapport surefire, 116 occurrences dans deux
listes identiques de 58, une par assertion echouee). Ce chiffre **differe du 54/54 annonce par
`theo-archi`**, et l'ecart est attendu et sain : le garde-fou derive sa population du catalogue reel
(`ADR-0013`, perimetre derive), et le catalogue est passe de 54 a 58 paquets servis entre l'ecriture
du garde-fou et cette mesure (les quatre paquets de sciences de `S30-1`/`S30-2`). Le garde-fou mord
donc bien sur **tout** le catalogue courant, sciences comprises, pas seulement sur la population qui
existait quand il a ete ecrit.

Restauration de la ligne (`git checkout --` sur le fichier), tree confirme propre
(`git status --porcelain` vide), reexecution : **VERT**, `Tests run: 3, Failures: 0`.

---

## 4. Les trois pieges nommes au planning

### `indicesNommentLaReponse: true` sur un paquet de sciences

**Non tombe.** `grep -n "^indicesNommentLaReponse"` sur les quatre paquets neufs : **aucune
occurrence** dans aucun des quatre fichiers - la cle n'est ecrite nulle part, donc absente et vaut
faux par defaut (`QuestionContentSourceFactory`, `Boolean.TRUE.equals(...)`). `SCI-1` reste tenable
par construction sur ces quatre paquets.

### `SCI-4` : au moins trois propositions sur tout item a conception fausse

**Verifie sur ce que le depot declare comme tel, pas au-dela.** Aucun champ de code ne marque un item
"a conception fausse" - `nadia-prof` le dit elle-meme dans son arbitrage prealable (section 8) :
"mordable des aujourd'hui" seulement si une declaration de contenu existe, ce qui n'est pas construit
ce sprint. Les deux seuls items que les commentaires YAML designent explicitement comme portant une
conception fausse (`signal-information`, item de l'ambulance ; `melanges`, item eau/huile) portent
chacun deux distracteurs, donc trois propositions - `SCI-4` tenue sur son seul perimetre verifiable.
Ce que je ne peux pas prouver, et je le dis plutot que de le taire : qu'aucun **autre** item, non
designe par un commentaire, ne porte implicitement une conception fausse sur un format binaire. C'est
un jugement de contenu, hors de portee d'un comptage structurel, et il reste la garde de `nadia-prof`.

### Le cliquet de `CONS-1`

**Aucun cliquet n'existe, ni avant ni apres.** Recherche exhaustive : `grep -rln "CONS-1"
--include=*.java .` ne trouve qu'une seule occurrence dans tout le depot,
`bootstrap/src/test/java/com/recreo/bootstrap/catalogue/TexteAccentueExposeParLePortEntrantTest.java`
ligne 398, un commentaire citant l'ancienne consigne generique comme exemple - pas un test de
comptage. Le backlog (`S30-9`) confirme explicitement : *"elle n'ecrit aucun garde-fou de comptage"*.
Le piege nomme par `theo-archi` (un cliquet ecrit avant le correctif, avec un plafond fige a 10) ne
peut donc pas s'etre produit, faute de cliquet du tout - le risque reste theorique, route et non
tranche, comme le backlog le dit lui-meme.

**Verifie separement, en positif** : la formule generique bannie par `DEC-0078`
(*"Lis bien la question, puis choisis la bonne réponse."*) n'apparait plus nulle part dans
`catalogue.yaml` (`grep -n "Lis bien la question, puis choisis la bonne" ...` : aucune occurrence).
Les dix entrees visees par `S30-9` (cinq de questionner le monde CP, une d'histoire-geographie CM1,
deux d'EMC CP, deux d'EMC CM1) et l'entree d'anglais CM1 portent desormais chacune une consigne qui
nomme sa tache reelle - lecture directe de `catalogue.yaml`, lignes 257-353. **Ce controle reste
manuel** : aucun test ne l'automatise (voir section 5, dernier point).

---

## 5. Declaration des lignes de referentiel : `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest`

**Confirme, et mis a l'epreuve, pas seulement lu.** Les quatre codes `SCI-CM1-05`, `-17`, `-02`,
`-12` sont declares dans `squad/programme/sciences-et-technologie/competences-cm1.md` (lignes 51, 72,
48, 64) et cites par les quatre paquets neufs sous `lignesDeReferentiel`.

Mutation reelle : suppression de la ligne `lignesDeReferentiel: ["SCI-CM1-05"]` dans
`cm1-sciences-signal-information.yaml`, puis
`-Dtest=ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest`. **Resultat : ROUGE**, 2 echecs -
*"cm1-sciences-signal-information : aucune ligne de referentiel declaree [...] FER-2 redevient
invisible"* et le cliquet de dette de declaration qui refuse un nom disparu. Restauration
(`git checkout --`), tree propre confirme, reexecution : **VERT**, 12/12 tests.

Le garde-fou lit le disque directement (referentiels sous `squad/programme`, paquets sous les
repertoires de contenu declares par les modules) : les quatre codes neufs entrent dans sa population
sans qu'aucune ligne de ce fichier de test n'ait ete touchee - c'est la propriete visee par
`ADR-0013` (perimetre derive), et je l'ai verifiee en la cassant plutot qu'en la lisant.

---

## 6. Criteres d'acceptation traduits en tests - ce qui est couvert, ce qui ne l'est pas

**Couvert par un test nomme, verifie present et vert dans le run de la section 1** : `FER-1`, `FER-2`,
`FER-3` de `S30-1`/`S30-2` (les quatre tests `PaquetCm1Sciences*Test`) ; declaration des lignes de
referentiel (`ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest`, mordu section 5) ;
appariement question/reponse de fin de partie pour `S30-10` (`AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest`,
mordu section 3) ; `POS-R2` (`PaquetCm1DivisionPoseeTest`, methodes `posR2LaBonneReponseNEstPasToujoursDuCoteDuResteQuiSePartage`
et `posR2LesItemsARestesEgauxNeSontPasTousSymetriques`) ; `OBJ-R2` (`PaquetCpQlmObjetTechniqueTest`,
methode `auMoinsDeuxItemsDeFonctionPortentUnDistracteurEtrangerAuxAutresObjetsDuPaquet`). Les six
paquets repris (`cp-qlm-cycle-de-vie`, `cp-qlm-vivant-non-vivant`, `cp-qlm-objet-technique`,
`cm1-histoire-geographie`, `cm1-division-posee`, `cm1-emc-fait-opinion`, `cm1-emc-droit-devoir`)
portent chacun son test de matiere, retrouve par nom dans le repertoire de test.

**Non couvert par un test - je le nomme plutot que de le laisser passer pour teste** :

- **`S30-1`, `S30-2`** : le critere *"sa consigne nomme la tache reelle du jeu et ne reprend pas la
  formule generique que `DEC-0078` interdit"* n'a aucun test dedie. Verifie manuellement (section 4)
  et vrai aujourd'hui, mais un futur ajout de paquet pourrait regresser sans qu'aucun rouge ne le
  signale - meme absence que le cliquet de `CONS-1`, deja route et non tranche par le backlog.
- **`S30-1`, `S30-2`** : `SCI-1` (l'indice corrige la conception fausse sans nommer la reponse),
  `CYC-1` (coherence avec le contenu de cycle 2), `HIS-3`/`CON-1` (source d'un fait, item non
  resoluble sans connaitre l'objet) - ces criteres sont **explicitement non juges** par les tests
  eux-memes (chaque javadoc de `PaquetCm1Sciences*Test` le dit : *"Ce que ce test ne juge pas..."*).
  Ce n'est pas un oubli de ce sprint : c'est la frontiere assumee entre le structurel (teste) et le
  pedagogique (`nadia-prof`, en jouant).
- **`S30-3` a `S30-8`** : les criteres de rejeu (*"quand `nadia-prof` le rejoue a l'ecran, elle
  confirme..."*) ne sont par nature couverts par aucun test - ce sont des criteres d'audit humain,
  pas des criteres de code.
- **`S30-9` (`CONS-1`)** : *"chaque consigne reecrite nomme la tache reelle"* et *"`nadia-prof` la
  rejoue contre le paquet qu'elle introduit"* n'ont pas de test - meme famille que ci-dessus, assumee
  par la story elle-meme (*"elle n'ecrit aucun garde-fou de comptage"*).
- **`S30-11`** (passe transverse) : **aucun document de resultat n'existe dans le depot** au moment
  de cette mesure - `squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/` ne contient que
  `planning.md` et `avis-faisabilite-theo.md`, et aucun fichier `PASSE-TRANSVERSE-s30-...md` n'est
  present (`ls` du repertoire de sprint et de `squad/conformite/`). Le dernier critere d'acceptation
  de `S30-11` exige explicitement que *"son resultat est ecrit [...] dans un document consultable"* :
  **ce critere n'est pas tenu a ce jour**, pas parce qu'un test manque, mais parce que le livrable
  lui-meme n'est pas encore dans le depot. Je ne peux pas juger le Done pedagogique de cette story
  puisqu'aucune trace n'existe a auditer.
- **Aucun fichier `FICHE-bandit-manchot-*.md` neuf** pour les quatre paquets de sciences, et aucune
  mise a jour de `squad/programme/MATRICE-couverture.md` sur les codes `SCI-CM1-05/17/02/12`
  (`grep` des quatre codes dans la matrice : aucune occurrence). **C'est attendu et je le nomme pour
  eviter toute confusion** : le Done pedagogique de ces lignes n'a pas encore ete prononce par
  `nadia-prof` au moment de cette mesure. Ce rapport ne prononce et ne prejuge aucun Done
  pedagogique ; il constate seulement que la porte technique est verte independamment de cette porte
  qui reste, elle, entierement a ouvrir.

---

## 7. Ce que je ne confonds pas

**Done technique** (ce rapport) : vert, sur la base de la section 1. **Done pedagogique** : non
prononce sur cet increment - aucune fiche de conformite, aucun verdict d'instance, aucune mise a jour
de matrice ne portent les quatre lignes de sciences a ce jour dans le depot. Les deux jugements sont
independants ; je ne laisse aucun des deux se lire comme une consequence de l'autre dans ce rapport.

---

## 8. Fichiers touches par cette review (tous restaures, tree propre a la fin)

Mutations temporaires, chacune restauree et verifiee (`git status --porcelain` vide apres chaque
restauration) :
- `adapters/adapter-web/src/main/resources/templates/fragments/retour.html` (section 3).
- `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-signal-information.yaml`
  (section 5).

Aucune autre modification n'est laissee dans l'arbre par cette review.

---

## 9. Trois chiffres de `theo-archi` confirmes par ma propre mesure, pas relayes

`theo-archi` a signale que le 54/54 de son avis de faisabilite n'est plus le bon point de comparaison
(le catalogue a grandi depuis sa date de mesure) et a cite trois autres chiffres. Je les ai mesures
moi-meme plutot que de les recopier :

**Le 54 -> 58 est confirme, sur les deux bornes.** `git show 5914c36e:.../catalogue.yaml | grep -c
"^  - id:"` rend **54** (etat au commit de planning, avant les deux lots de dev) ; la meme commande
sur le fichier courant rend **58**. C'est exactement l'ecart que ma section 3 explique : le garde-fou
de `REV-3` mord sur 58 ecrans parce que sa population est derivee du catalogue reel, pas d'un chiffre
fige. Le 54 de `theo-archi` etait juste a sa date ; le 58 que je mesure est juste a la mienne. Aucun
des deux chiffres n'est faux.

**33 regles `ArchRule`, zero ajoutee ce sprint - compte refait, pas recopie.** `grep -rn "ArchRule "
--include=*.java` sur les cinq arbres de test de production (hors `test-classes` et hors le faux
depot de `LesGardeFousSontMisALEpreuveTest`, qui declare un type `ArchRule` factice pour sa propre
mise a l'epreuve) rend 33 declarations `static final ArchRule <nom> = ...`. `grep -rn "@ArchTest"`
sur le meme perimetre en rend **32**. La 33e est
`ScanDeComposantsTest.aucuneClasseDeTestSurLeClasspathDeLApplicationNePorteDeStereotype` : declaree
sans `@ArchTest`, verifiee par un appel explicite a `.check(...)` dans deux `@Test` (`aucuneClasseDe
TestNeSeraEnregistreeParLeScanDeComposants` et sa mise a l'epreuve
`unStereotypeSpringSurUneClasseDuPerimetreEstRefuse`). Un comptage qui ne chercherait que `@ArchTest`
manquerait cette regle-la - c'est exactement le piege que `theo-archi` nomme, et je l'ai retrouve par
ma propre lecture du fichier, pas par sa description. Confirme : **32 + 1 = 33, zero ajoutee** (aucun
des 24 fichiers touches par les deux lots de dev, section 8 du present rapport, ne vit sous
`bootstrap/src/test/java/com/recreo/archi` ni sous `adapters/adapter-web/src/test/java/com/recreo/
adapter/web/archi`).

**Zero fichier Java de production touche, 24 fichiers au total - confirme sans passer par un tube.**
`git diff --name-only 5914c36e dbbfe129` ecrit dans un fichier puis relu (jamais compte au fil d'un
`| wc -l` sur ce poste, pour le motif meme que `theo-archi` nomme : la decoration de l'enveloppe de
shell fausserait un comptage en tube) : **24 fichiers**, 12 YAML (`catalogue.yaml` + 11 paquets de
contenu), 12 fichiers de test, **zero fichier sous `src/main/java`**. Confirme le decouplage
mecanique/contenu a l'echelle du sprint : les deux lots de dev n'ont ecrit que du contenu et des
tests de contenu, jamais de code de production.

**Consigne generique, avant/apres, sur le meme fichier.** `git show
5914c36e:.../catalogue.yaml | grep -c "Lis bien la question, puis choisis la bonne"` rend **10** ;
la meme commande sur le fichier courant rend **0**. C'est la preuve chiffree de `CONS-1`, sur les deux
bornes du sprint et non sur une seule lecture du fichier final.
