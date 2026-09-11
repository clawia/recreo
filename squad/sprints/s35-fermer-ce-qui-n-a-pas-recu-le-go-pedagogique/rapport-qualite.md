# Rapport qualite de cloture - s35

**Auteur** : `yanis-qa`. **Date** : 2026-09-11. **Arbre mesure** : `s35-integration` a `d22d24d1`,
worktree dedie (`git worktree add ../recreo-s35-yanis -b s35-yanis s35-integration`), separe du
worktree de confirmation de `theo-archi` (`s35-theo-confirm`, meme base).

Six stories livrees ce sprint, toutes fermant un refus ou une reserve pedagogique deja prononce :
`S35-SCO1-ECRAN-FIN`, `S35-EXC-INDICES`, `S35-ANG-R3` (`sofia-sm` dev), `S35-CONS-4` (`marc-dev`),
`S35-QLM14-FRISE`, `S35-QLM11-PLANCLASSE` (`lea-dev` + `ines-design`).

---

## 1. Done technique - CI verte, commande exacte et resultat

Commande : `bash outils/mvn-jdk21.sh -B clean verify`, lancee depuis la racine du worktree dedie.

Resultat : **BUILD SUCCESS**, reacteur complet (`recreo`, `recreo-domain`, `recreo-application`,
`recreo-adapter-web`, `recreo-adapter-content`, `recreo-bootstrap`), toutes phases jusqu'a
`failsafe:verify` incluse (compilation, tests unitaires, tests d'integration, ArchUnit).

Verdict de version rendu par l'enveloppe : Maven lance sur le JDK de la toolchain `jdk` `21`,
`21.0.10` (constate en interrogeant ce JDK, pas la toolchain declaree - `mvn-jdk21.sh`).

**Cet enonce ne dit que ce que j'ai constate sur ce lancement precis** ; je n'ai pas mesure la CI
GitHub Actions elle-meme (`.github/workflows/ci.yml` porte plusieurs jobs, dont `chemins` et les
juges de prefixe de commit, qu'aucun `mvn` ne couvre - voir `CLAUDE.md`, amendement du
2026-09-08). Ce rapport ne porte que sur le job `build`.

## 2. Compte de tests autoritaire, avec sa methode

**Methode 1** : somme des blocs `[INFO] Results:` du reacteur complet, extraits du journal de
lancement (`grep -A2 "^\[INFO\] Results:" | grep "Tests run:"`), six blocs (un par module qui
execute des tests) :

| Module | Tests run |
|---|---|
| recreo-domain | 111 |
| recreo-application | 13 |
| recreo-adapter-web | 397 |
| recreo-adapter-content | 705 |
| recreo-bootstrap (surefire) | 247 |
| recreo-bootstrap (failsafe) | 6 |
| **Total** | **1479** |

**Methode 2, independante** : somme de l'attribut `tests="..."` de chaque `<testsuite>` des
rapports XML Surefire et Failsafe generes par ce meme lancement (201 fichiers
`target/{surefire,failsafe}-reports/TEST-*.xml`, tous modules confondus) : **1479**.

Les deux methodes, sur le meme lancement, convergent sur **1479 tests, 0 echec, 0 erreur, 0
ignore**. Ce chiffre confirme, par reverification independante et non par recopie, celui de 1479
annonce par `sofia-sm` en cloture de fusion.

**Couverture par module** (fichiers de test presents, aucun module vide) :

| Module | Fichiers `*Test.java` / `*IT.java` |
|---|---|
| domain | 10 |
| application | 2 |
| adapter-web | 49 |
| adapter-content | 95 |
| bootstrap | 47 |

## 3. Mutation de garde-fous recents sur materiau reel

Regle appliquee : un garde-fou cite comme fermant un refus est mis a l'epreuve en mutant l'objet
reel qu'il pretend garder (le fichier de production servi), pas une fixture ni une copie en
memoire deja ecrite par le garde-fou lui-meme - les deux tests ci-dessous portent deja une mutation
interne (`laMutationDeLIndiceDeLItemUnDansSonEtatFautifDu20260911FaitRougirLeGardeFou`,
`unSeulNombreSurLEcranDeFinFaitEchouerLeGardeFou`), ce qui prouve leur forme mais pas leur
branchement reel sur le fichier servi ; je mute donc le fichier lui-meme, en dehors du test.

### 3.1 `ADR-0091` (`cp-frise-de-la-journee.yaml`), clauses 1 et 2

Fichier mute : `adapters/adapter-content/src/main/resources/contenu/cp-frise-de-la-journee.yaml`,
item 1 - `illustrationTexteAlternatif` remis a l'etat fautif du 2026-09-11 ("... six moments sur un
axe - le lever, le petit-dejeuner, ...") et `indice` remis a "C'est le tout premier moment de la
journee." (le texte que `nadia-prof` avait releve, `FRI-R1`).

Commande : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test
-Dtest=AucunTexteAlternatifDeDesignationNeNommeSesZonesTest,AucunIndiceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest`

- **Avant restauration (fichier mute)** : rouge, 2 echecs -
  `surLeContenuReelDeProductionAucunTexteAlternatifNeNommeSesZones` (4 zones nommees : `lever`,
  `petit-dejeuner`, `dejeuner`, `gouter`) et
  `surLeContenuReelDeProductionAucunIndiceNeNommeLeLibelleDeSaBonneZone` (jeton distinctif
  "premier" retrouve dans l'indice).
- **Apres restauration** (fichier remis a l'identique, `git diff` vide verifie) : vert, 7/7.

Les deux garde-fous mordent reellement sur le fichier de production, pas seulement sur leur propre
fixture ou leur propre mutation interne.

### 3.2 Garde-fou de `S35-SCO1-ECRAN-FIN` (`retour.html`)

Fichier mute : `adapters/adapter-web/src/main/resources/templates/fragments/retour.html` -
les deux elements distincts (`score-trouve-nombre`, `score-joue-nombre`) remplaces par la forme
concatenee refusee que le commentaire du fichier documente : `<p class="score" th:text="'Score :
' + ${score.reussites()}"></p>`, seul le nombre de reussites restant visible.

Commande : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
-Dtest=LEcranDeFinDePartiePorteLesDeuxNombresTest`

- **Avant restauration (fichier mute)** : rouge, 3/3 echecs sur les trois mecaniques du catalogue
  reel (`bandit-manchot-additions-cp`, `saisie-complements-10-cp`,
  `designation-frise-de-la-journee`), chacune signalant l'absence du nombre de manches jouees.
- **Apres restauration** (fichier remis a l'identique, `git diff` vide verifie) : vert, 3/3.

Ce garde-fou (test Spring Boot avec `MockMvc`, partie complete jouee) mord bien sur le fragment
HTML reellement servi, et pas seulement sur le document reconstruit en memoire par son propre test
de mutation interne.

## 4. Critere enumere auto-declare - `cp-emc-exclusion.yaml`

**Trouve** : `PaquetCpEmcExclusionTest.laPropositionAuRabaisAPersonneNApparaitDansAucuneTache` est
construit sur un **nom d'incident litteral**, pas une categorie generale -
`BOUTON_AU_RABAIS = "à personne"`, verifie par `proposition.startsWith(BOUTON_AU_RABAIS)`. Le
commentaire de la classe le dit lui-meme : "le bouton au rabais que `nadia-prof` a fait retirer de
la tache B a s31". Ce n'est pas une regle sur la categorie ("une proposition evasive, qui ne
resout rien"), c'est une regle sur la chaine exacte que l'incident de s31 a portee.

**Mise a l'epreuve** : mutation d'un distracteur reel de la tache B (item 7,
`cp-emc-exclusion.yaml`), de `"attendre que cela s'arrête tout seul"` a `"ne rien dire du tout"` -
un nouveau bouton au rabais, de la meme famille pedagogique (une conduite evasive qui ne resout
rien), absent de la liste que la clause verifie.

Commande : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test
-Dtest=PaquetCpEmcExclusionTest`

**Resultat : vert, 7/7, y compris `laPropositionAuRabaisAPersonneNApparaitDansAucuneTache`.**
C'est un **faux vert demontre** : le meme defaut pedagogique qu'`EMC-3`/`FAB-1a` a fait refuser en
s31, sous une formulation neuve, traverse ce garde-fou sans le faire rougir. Fichier restaure
ensuite, `git diff` verifie vide.

**Ce que je ne fais pas** : je ne reformule pas la clause moi-meme (ce serait une decision
d'architecture ou de contenu) ; je remonte le symptome. La clause devrait porter sur une categorie
verifiable sans liste ecrite a la main (par exemple : aucune proposition de la tache B ne doit
etre un synonyme de "ne rien faire / ne rien dire", ce qui redemande un jugement de sens, comme
`REC-5` deja ecarte de l'automatisable) - ou, a defaut, rester nommee comme un garde-fou de
regression sur un seul incident precis et ne pas laisser croire qu'elle couvre la famille.

## 5. Compte de catalogue - verification independante du 70 -> 73

Claim de `marc-dev` : le decoupage de `bandit-manchot-histoire-geographie-cm1` en quatre entrees
(`S35-CONS-4`) fait passer le catalogue de 70 a 73 lignes.

**Methode** : comptage direct des entrees (`grep -c "^  - id:"`) sur le blob Git de
`catalogue.yaml` avant et apres le commit `b16bbbcb` (`[MARC] s35: S35-CONS-4 - scission de
bandit-manchot-histoire-geographie-cm1 en quatre entrees`), sans passer par un `mvn` :

- `git show b16bbbcb~1:.../catalogue.yaml \| grep -c "^  - id:"` -> **70**
- `git show b16bbbcb:.../catalogue.yaml \| grep -c "^  - id:"` -> **73**

**Confirme** : le delta de `S35-CONS-4` est exactement +3 (une entree supprimee, quatre creees),
70 -> 73, et le diff du commit le montre explicitement (`bandit-manchot-histoire-geographie-cm1`
retiree, quatre `bandit-manchot-histoire-geographie-{situer-dans-le-temps,nommer-en-histoire,
nommer-en-geographie,lire-un-document}-cm1` ajoutees).

**A ne pas confondre avec le total du reacteur** : le catalogue courant, a `s35-integration`,
compte **74** entrees (`git show d22d24d1:.../catalogue.yaml \| grep -c "^  - id:"`), parce que le
commit suivant, `14a1b4e9` (`[LEA] s35: S35-QLM11-PLANCLASSE`), ajoute une entree (73 -> 74) dans
le meme sprint. Le 70 -> 73 de `marc-dev` porte sur son propre commit, pas sur le total du sprint -
c'est la distinction que `sofia-sm` etablit en cloture (`squad/README.md`, section 4).

## 6. Limite de mesure declaree

- Le jeu (mesure "moins de neuf items sur onze se resolvent sans regarder le dessin", verdict de
  `FRI-R1`) n'est verifiable qu'en jouant : aucun outil de ce depot ne le garde, et `ADR-0091` le
  dit lui-meme ("Ce que ces deux clauses ne gardent pas", point 3). Je ne l'ai pas mesure ; c'est
  la garde de `nadia-prof`.
- La lisibilite du pictogramme de lever (`FRI-R2`, "je ne trouve pas le lit") est un jugement
  visuel humain, non automatisable - je ne l'ai pas evalue.
- Le contenu semantique des cinq indices de `S35-EXC-INDICES` (`EXC-R10`) demande un jugement de
  sens ("pourquoi cette conduite ne marche pas") de la meme famille que `REC-5` sur
  `PaquetCm1AnglaisTest` : aucun automate general de ce depot ne le porte, et je ne l'ai pas
  invente. Voir section 7 pour ce qui, dans `S35-EXC-INDICES`, reste testable et ne l'est pas.

## 7. Defaut trouve - `S35-EXC-INDICES` sans traçabilité de test

Recherche de traçabilité (nom de test ou commentaire portant la reference de la story, exigee par
mes responsabilites) : `grep -rl "S35-EXC-INDICES\|EXC-R10\|EXC-R11\|EXC-R12" --include=*.java`
sur l'arbre entier -> **aucun fichier Java**.

`PaquetCpEmcExclusionTest` ne teste **aucun contenu du champ `indice`** de `cp-emc-exclusion.yaml`
(verifie en lisant le fichier de test entier, section 4 ci-dessus). Or deux des quatre criteres
d'acceptation de la story sont structurellement testables sans jugement de sens :

- `EXC-R11` ("les trois items sur cinq ou la bonne reponse est aujourd'hui la seule proposition a
  nommer un adulte... cette reserve est soldee ou explicitement laissee ouverte") est un
  **comptage** de propositions par item (combien de propositions, bonne reponse comprise, nomment
  un adulte). Le commentaire du fichier `.yaml` affirme "EXC-R11 ... soldee dans le meme geste" et
  donne un compte ("cinq items sur cinq" contre "trois sur cinq a la cloture de s34") - **cette
  affirmation n'est verifiee par aucun test**.
- `EXC-R12` (retrait du subjonctif passe et du pronom elide sur un distracteur precis) est une
  regression nommee, du meme genre que `laPropositionAuRabaisAPersonneNApparaitDansAucuneTache` -
  testable comme garde-fou de regression sur la forme exacte refusee, non ecrite.

`EXC-R10` lui-meme (le sens de chaque indice) reste, pour sa part textuelle, hors de portee d'un
automate general - comme `REC-5` - mais rien n'empeche de verifier au moins qu'aucun indice de la
tache B ne redonne, mot pour mot, le libelle de l'ancienne tache ("Quel adulte est la ?") ou le nom
d'un adulte de la bonne reponse, sur le meme patron que le garde-fou 2 d'`ADR-0091` (jeton
distinctif). Cette regle n'existe pas non plus.

**Consequence** : le Done technique ne peut pas repondre a la question "`EXC-R10`, `EXC-R11`,
`EXC-R12` sont-ils couverts ?" en cherchant dans le code - la reponse est non, pour les deux
derniers au moins. Ce n'est pas un jugement pedagogique que je prononce : `nadia-prof` reste seule
a dire si le texte est bon ; c'est un defaut de traçabilité que je remonte, a traiter avant que ce
lot referme `S34-EXC-TACHEB` sans garde-fou de regression.

## 8. Tests instables

Aucun test instable observe sur ce lancement unique (1479/1479 verts, un seul passage). Aucune
relance n'a ete effectuee - un test qui n'a ete lance qu'une fois ne peut pas etre qualifie de
stable pour autant ; ce rapport ne l'affirme pas.

## 9. Ce que ce rapport ne fait pas

Il ne prononce aucun Done pedagogique - `nadia-prof` seule le fait, sur un motif ecrit dans
`squad/conformite/`. Il ne juge le niveau scolaire d'aucun contenu. Il ne mesure que le job
`build` de la CI, pas les autres jobs de `.github/workflows/ci.yml`.
