# Rapport qualite de cloture - s47

**Auteur** : `yanis-qa`. **Date** : 2026-10-02. **Arbre mesure** : `s47-yanis-qualite` a
`06449adc` (hash complet `06449adcf555de8b3247bda1deb5f35737d861d8`, strictement egal a
`round-two` au moment de la mesure), worktree dedie
(`git worktree add ../recreo-s47-yanis-qualite -b s47-yanis-qualite round-two`), lignee verifiee
(`bash outils/verifier-lignee.sh round-two`, lance depuis le worktree lui-meme -> *"le worktree sur
la branche 's47-yanis-qualite' descend de la lignee de round-two (ancetre commun trouve dans son
historique)"*).

Contenu du sprint : cinq lots de contenu statique, dispositif a deux instances paralleles de
`nadia-prof` (`DEC-0104`) - instance A : `S47-MAT-TABLES` (`MAT-CE1-07`, `[MARC]`, merge
`b799cce6`), `S47-QLM-AUTREFOIS` (`QLM-CP-15`, `[LEA]`, merge `7d3cc9ee`), `S47-LVE-CULTURE-CP`
(`LVE-CP-06`, `[SOF]`, merge `3a56f9d1`) ; instance B : `S47-EMC-ALERTE-CP` (`EMC-CP-14`, `[LEA]`,
merge `7d3cc9ee`), `S47-EMC-VOTE-CM1` (`EMC-CM1-10`, `[SOF]`, merge `3a56f9d1`). Base de sprint :
`9e3527ed` (dernier commit sur `round-two` avant s47, "s46: review + retro + rapport de sprint +
rapport de tokens" - la cloture de s46).

**Un defaut d'integration trouve par l'orchestrateur, deja corrige avant ma mesure.** Le dernier
commit du sprint, `5015d96c` (`[SOF]`, merge `06449adc`), corrige un caractere hors Latin-1 :
`cp-anglais-culture.yaml` (`LVE-CP-06`) portait la ligature "œufs" (U+0153) dans un distracteur,
faisant echouer `PropositionsAccentueesJouablesIT` (bootstrap), qui rejoue chaque proposition
non-ASCII du catalogue reel contre le serveur embarque en Latin-1 et refuse tout caractere hors de
ce plan. Corrige par remplacement en "oeufs" sans ligature. Verifie ci-dessous par ma propre
mutation (section 2) : ce n'est pas une affirmation reprise de confiance, et j'ai en plus balaye
moi-meme les cinq paquets neufs entiers (enonce, bonne reponse, distracteurs, indice) pour
m'assurer qu'aucune autre occurrence ne s'y est glissee (section 2.1).

`bash outils/mvn-jdk21.sh -B clean verify`, lance une seule fois sur ce worktree, a rendu
`BUILD SUCCESS` du premier coup, en 8 min 27 s. JDK constate par `outils/verifier-jdk.sh` (sortie
standard, jamais un chemin de poste) :

```
java du PATH : 21.0.12.1 | JDK de compilation : 21.0.10 | JAVA_HOME : renseignee | toolchains lu par ce script : oui | toolchains lu par Maven : oui (le meme que celui lu par ce script)
```

---

## 1. Compte de tests autoritaire

**1938 tests : 1932 unitaires + 6 tests d'integration**, sur `s47-yanis-qualite` a `06449adc`.
Methode (amendement s11) : somme de l'attribut `tests="..."` des 256 rapports XML de
surefire/failsafe apres ce `clean verify` - jamais les lignes `Tests run:` des fichiers `.txt`.

**Resultats par module** :

| Module | Tests run |
|---|---|
| recreo-domain | 120 |
| recreo-application | 17 |
| recreo-adapter-web | 400 |
| recreo-adapter-content | 1107 |
| recreo-bootstrap (surefire, unitaires) | 288 |
| recreo-bootstrap (failsafe, integration) | 6 |
| **Total unitaires** | **1932** |
| **Total integration** | **6** |

`domain`, `application`, `adapter-web` et `bootstrap` (unitaires et integration) sont **inchanges**
depuis la cloture de s46 (120, 17, 400, 288, 6). Seul `adapter-content` bouge : **1067 -> 1107, soit
+40**, exactement la somme des `@Test` des cinq fichiers neufs, comptee directement dans chacun
(`grep -c "@Test"`) : `PaquetCe1TablesMultiplicationTest` (8), `PaquetCpQlmAutrefoisAujourdhuiTest`
(6), `PaquetCpAnglaisCultureTest` (10), `PaquetCpEmcAlerteTest` (8),
`PaquetCm1EmcVoteElectionTest` (8) = 40. Les trois fichiers transverses modifies
(`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`,
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, `TexteAccentueExposeParLePortEntrantTest`,
tous dans le `git diff --stat 9e3527ed..HEAD -- '*.java'`) n'ajoutent aucun `@Test` (verifie :
`git diff ... | grep -c "^+.*@Test"` rend 0) - leurs diffs n'ajoutent que des entrees de donnees
(une entree d'enum, des entrees de `Map`, des chaines de l'inventaire), consommees par des methodes
de test deja existantes. Zero fichier de `src/main/java` n'est touche par ce sprint sur les cinq
modules (`git diff --stat 9e3527ed..HEAD -- '*.java' | grep src/main` rend vide) : les cinq lots
sont du contenu statique pur (YAML), conforme a l'avis de `theo-archi` ("zero code de production"
sur quatre des cinq lots, et aucune ligne de production non plus sur `MAT-CE1-07`).

**Total du sprint** : 1067 (s46) + 40 (cinq paquets) = **1107**, et le total depot (1898 -> 1938)
rend bien **+40** sur `adapter-content` seul, sans mouvement dans aucun autre module.

**Couverture par module** (fichiers `*Test.java`/`*IT.java` suivis par Git) :

| Module | Fichiers de test (s46 -> s47) |
|---|---|
| domain | 11 -> 11 |
| application | 2 -> 2 |
| adapter-web | 49 -> 49 |
| adapter-content | 140 -> 145 |
| bootstrap | 51 -> 51 |

`adapter-content` passe de 140 a 145, soit exactement **+5 fichiers**, un par paquet neuf. Aucun
paquet neuf n'est reste sans test a son premier commit. Note de methode sur `adapter-web` (49
fichiers, inchange) : ce compte inclut `ConfigurationDeTest.java`, une classe de support sans
aucune methode `@Test` (verifie : zero annotation `@Test` dans ce fichier), qui ne produit donc
aucun rapport XML - c'est un ecart pre-existant du nom de fichier par rapport a la convention
`*Test.java`, trace depuis `s00` (`d355cab3`), sans rapport avec ce sprint ; je le note ici parce
que le compte de 49 fichiers ne correspond qu'a 48 classes reellement executees par surefire sur ce
module, et qu'un chiffre sans cette precision se compare mal au compte autoritaire de la section 1.

**ArchUnit** : **36** `static final ArchRule` et **35** `@ArchTest`, comptes par occurrences
litterales sur les fichiers de test suivis par Git **a l'exclusion de
`src/test/resources/contreexemple/LesGardeFousSontMisALEpreuveTest/faux-module`** (un faux module
Maven fixture, jamais compile comme source de test de ce depot, que `LesGardeFousSontMisALEpreuveTest`
copie et compile a part pour eprouver le garde-fou lui-meme - sans cette exclusion, un grep naif
rend 38, en comptant deux `ArchRule` qui ne sont jamais executees par ArchUnit sur ce module).
**Identique au chiffre verifie a la cloture de s46 et a celui annonce par l'avis de faisabilite de
`theo-archi`** ("36 `static final ArchRule`, 35 `@ArchTest`, compte inchange depuis s46") : aucune
regle ArchUnit neuve n'est due a s47, confirme par `git diff 9e3527ed..HEAD` sur les fichiers de
test, qui ne touche aucune ligne contenant `static final ArchRule`. Aucune classe ArchUnit en echec
dans le lancement complet.

**Catalogue** : **120 entrees** (`- id:`), contre 115 a la cloture de s46 - exactement **+5**.
Aucun identifiant duplique, aucun paquet duplique (`grep | sort | uniq -d` rend vide sur les deux
cles). Les cinq entrees du sprint sont toutes presentes et distinctes :
`bandit-manchot-tables-multiplication-ce1`, `bandit-manchot-autrefois-aujourdhui-cp`,
`bandit-manchot-culture-anglophone-cp`, `bandit-manchot-alerte-cp`,
`bandit-manchot-vote-election-cm1`.

## 2. Preuve de morsure - deux garde-fous mutes sur le materiau de production reel

Amendement s06/s10 : un garde-fou cite comme fermant une condition n'est declare complet qu'une
fois mute reellement, jamais sur la seule foi d'un vert.

**Morsure 1 - le garde-fou de la ligature, `PropositionsAccentueesJouablesIT` (bootstrap),
precisement celui que `5015d96c` vient de corriger.** Mutation du materiau de production reel
(`cp-anglais-culture.yaml`, pas une fixture de test) : le distracteur "oeufs" (corrige) est remis a
"œufs" (ligature U+0153). `bash outils/mvn-jdk21.sh -B -pl bootstrap -am verify
-Dit.test=PropositionsAccentueesJouablesIT -DfailIfNoTests=false`. **Rouge attendu, rouge obtenu** :
`chaqueRepresentationAccentueeDuCatalogueEstJouableSansPlanterLaManche` echoue avec
`IllegalStateException: caractere hors Latin-1, cette IT ne sait pas le reproduire : "on cherche
des �ufs en chocolat dans le jardin"` - le garde-fou refuse la ligature avant meme d'atteindre
le serveur, exactement la propriete que `5015d96c` corrige. Mutation annulee, meme classe relancee
en reacteur complet (section d'en-tete) : vert, `git status --short` vide.

**Morsure 2 - `TAB-CE1-6`, famille (e), `PaquetCe1TablesMultiplicationTest` (`MAT-CE1-07`).**
Mutation du materiau de production reel (`ce1-tables-multiplication.yaml`, pas une fixture de
test) : sur l'item `"2 × 1 = ?"` (marqueur `e,b`), le premier distracteur passe de `"3"` (valeur
`a + 1 = 2 + 1`, famille (e) valide) a `"5"`, sans toucher le marqueur. `bash outils/mvn-jdk21.sh -B
-pl adapters/adapter-content -am test -Dtest=PaquetCe1TablesMultiplicationTest
-Dsurefire.failIfNoSpecifiedTests=false`. **Rouge attendu, rouge obtenu** :
`tab6ChaqueDistracteurCorrespondASaFamilleDeclareeYComprisLaCinquieme` echoue - *"TAB-CE1-6 : '2 ×
1 = ?', distracteur 0 (5) ne correspond a aucune lecture valide de la famille 'e'"*. Mutation
annulee, meme classe relancee : **8 verts sur 8**, `git status --short` vide. Le garde-fou lit bien
la valeur declaree et la recalcule depuis le fait, il ne la recopie pas - verification choisie
precisement parce que la cinquieme famille est l'amendement pedagogique du 2026-10-02
(`nadia-prof`) qui a debloque ce lot, donc la regle la plus recente de ce sprint.

### 2.1. Balayage independant des cinq paquets neufs pour tout caractere hors Latin-1

Au-dela de la morsure 1, qui ne rejoue qu'un distracteur a la fois sur le catalogue reel, j'ai
balaye moi-meme chaque caractere des cinq fichiers YAML neufs (`enonce`, `bonneReponse`,
`distracteurs`, `indice` compris, pas seulement les propositions) a la recherche d'un point de code
superieur a U+00FF. **Aucune occurrence trouvee.** Ce que cela verifie, et ce que cela ne verifie
pas : mon balayage couvre l'integralite du texte des cinq fichiers, y compris l'enonce et l'indice
que `PropositionsAccentueesJouablesIT` ne regarde jamais (ce garde-fou ne rejoue que
`bonneReponse()` et `distracteurs()` d'une `Question`, jamais `enonce()` ni `indice()` - lu dans son
code, pas suppose). Les deux se completent : le garde-fou prouve qu'une proposition hors Latin-1
plante reellement une manche ; mon balayage prouve qu'aucun champ, meme hors de son perimetre, n'en
porte sur ce sprint.

## 3. Garde-fous dont le compte doit bouger (amendement s17)

Trois garde-fous nommes par la consigne, verifies un par un plutot que supposes avoir vu le
materiau neuf :

- **L'inventaire du port entrant (`TexteAccentueExposeParLePortEntrantTest`)** : etendu de
  vingt et une chaines neuves (`git diff 9e3527ed..HEAD` sur ce fichier, 64 lignes ajoutees) -
  cinq identifiants, cinq titres, cinq consignes, un domaine ("Decouverte culturelle" pour le CP,
  distinct de la chaine combinee deja listee pour `cm1-anglais`) et cinq competences. **Le compte a
  bouge**, confirme vert dans le lancement complet (section d'en-tete).
- **Garde-fou de rang.** Deux effets distincts selon le paquet, verifies individuellement plutot
  que supposes homogenes :
  - `ce1-tables-multiplication` (MAT-CE1-07, domaine numerique) **n'entre dans aucune exemption** :
    tous ses items portent trois propositions numeriques, il releve donc de la population
    generique du garde-fou de rang - confirme absent de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` et de
    `PAQUETS_A_ITEMS_ECARTES` (grep des deux fichiers, aucune occurrence de
    `ce1-tables-multiplication`). Sa propre regle de rang (TAB-CE1-6, plancher/plafond 10 %/50 %
    sur trois rangs) est de surcroit verifiee nommement et plus finement par
    `PaquetCe1TablesMultiplicationTest` (section 1).
  - Sur les quatre paquets textuels restants, le compte bouge dans **deux maps distinctes** selon
    que le paquet est homogene ou melange :
    - `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`
      passe de **89 a 92 entrees** (compte verifie par decoupe du fichier avant/apres la premiere
      entree neuve : 89 `Map.entry(` jusqu'a la ligne 1045, 92 sur le fichier entier) - trois
      entrees neuves, `cp-anglais-culture`, `cm1-emc-vote-election`, `cp-qlm-autrefois-aujourdhui`,
      chacune avec son propre decompte chiffre et sa date de mesure (2026-10-02). Ce n'est pas une
      liste de noms d'incidents (amendement s11) mais une categorie (aucune proposition numerique).
    - `cp-emc-alerte` (EMC-CP-14) **n'entre pas** dans cette map : ses items melangent propositions
      numeriques (trois numeros nus) et textuelles - `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest.PAQUETS_A_ITEMS_ECARTES`
      porte desormais une entree `"cp-emc-alerte", 8` (huit items ecartes sur onze), exactement le
      piege de fabrication que le planning de s47 signalait d'avance au developpeur affecte.
- **Le registre des sous-parties de mathematiques CE1
  (`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`)** : une entree neuve,
  `TABLES_DE_MULTIPLICATION("MAT-CE1-07", "Calcul mental - tables de multiplication de 2, 5 et 10,
  puis de 3 et 4")`, verifiee par lecture directe du diff (7 insertions). **Le compte a bouge.**

Ce que ce point n'a pas trouve cette fois : aucun des trois garde-fous n'est reste muet sur le
materiau neuf, et le defaut qu'`ADR-0013` redoute - une entree nouvelle qu'une liste a maintenir a
la main oublie - ne s'est pas reproduit ce sprint (a la difference du defaut de methode de mesure
trouve a s46, qui touchait un garde-fou different).

## 4. Tracabilite - criteres d'acceptation encodes, lot par lot

Chaque story de `docs/backlog/backlog.md`, section "Sprint 47", est verifiee critere par critere
contre le fichier de test du paquet.

- **`S47-MAT-TABLES`** (`PaquetCe1TablesMultiplicationTest`, 8 methodes) : `TAB-CE1-1` (40 faits
  distincts, chacun servi une seule fois - une propriete plus forte que l'exigence),
  `TAB-CE1-2` (planchers par facteur de table), `TAB-CE1-3` (plafond 20 % sur facteur 1, unite de
  compte l'item, Q2 tranchee), `TAB-CE1-4` (tiers sur la population eligible, Q3 tranchee),
  `TAB-CE1-5` (forme de l'enonce, verifiee par expression reguliere sur une chaine technique, pas
  sur du HTML), `TAB-CE1-6` (cinq familles, y compris la cinquieme admise le jour meme, et les
  trois rangs de la bonne reponse entre 10 % et 50 %) toutes couvertes nommement. Le jeu fautif
  commite est refuse sur son marqueur errone (`leJeuFautifCommiteEstRefuseSurSonMarqueurErrone`,
  `ADR-0013` obligation 3). La ligne de referentiel exclusive est verifiee directement sur le YAML
  (`cePaquetDeclareMatCe107EtUniquementElle`, qui lit `lignesDeReferentiel` et l'egale a la liste a
  un seul element - une verification d'egalite, pas une simple sous-chaine). Seul ce qui reste du
  ressort de `nadia-prof` (jugement global des six conditions, Done pedagogique) n'est pas
  automatise.
- **`S47-QLM-AUTREFOIS`** (`PaquetCpQlmAutrefoisAujourdhuiTest`, 6 methodes) : `BIA-3` (mot entier,
  jamais une sous-chaine - verifie par `\b...\b` sur enonce, bonne reponse, distracteurs et
  indice), `FER-1`, `FER-2`, `FER-3` toutes couvertes. Le piege de biais nomme par le planning
  ("dur" dans "duree") est explicitement celui que le motif `\b(difficile|dur|pauvre)\b` evite.
- **`S47-LVE-CULTURE-CP`** (`PaquetCpAnglaisCultureTest`, 10 methodes) : `BIA-1` (garde de forme -
  aucune mention de "France", plus etroite que la clause complete qui interdirait toute comparaison
  implicite sans nommer la France ; le fichier le dit lui-meme en javadoc comme hors de son
  mandat), `BIA-2` (au moins deux traits distincts par pays, verifie par comptage), `FER-1`, `FER-2`
  (plancher 11, pas 10 - coherent avec `nombreDeManches: 10` du catalogue), `FER-3`, et la mention
  de couverture partielle exigee d'avance, verifiee presente mot pour mot dans le fichier brut.
  **Observation de tracabilite, sans consequence de couverture** : `chaqueItemDeclareUniquementLveCp06`
  ne verifie qu'une sous-chaine de `competence()`, pas l'exclusivite reelle de la ligne - la meme
  famille d'ecart deja notee au rapport de s46 sur deux fichiers d'EMC. L'exclusivite reelle reste
  tenue transversalement par `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest`
  (`ADR-0081`), verifiee section 3, donc sans trou de couverture - seul le nom de methode dit plus
  que le corps ne verifie.
- **`S47-EMC-ALERTE-CP`** (`PaquetCpEmcAlerteTest`, 8 methodes) : `EMC-7` (chaque item a numero
  porte un marqueur `# EMC-7:` avec sa source, verifie sur le texte brut), le piege Q4 tranche
  (`le112NApparaitNullePart`, sur enonce, bonne reponse, distracteurs et indice), `FER-1`
  (les trois numeros chacun en bonne reponse, et l'element "prevenir un adulte" servi), `FER-2`,
  `FER-3`, et la regle 1 de l'arbitrage Q4 (un item a numero porte exactement les trois numeros
  nus, jamais une quatrieme valeur ni une forme habillee). `EMC-1`, `EMC-2`, `EMC-5` (lecture
  semantique de chaque situation) restent explicitement a `nadia-prof`, comme le dit le
  commentaire de tete du fichier.
- **`S47-EMC-VOTE-CM1`** (`PaquetCm1EmcVoteElectionTest`, 8 methodes) : `FER-1` (les trois termes
  du libelle chacun en bonne reponse), `FER-2`, `FER-3`, et deux gardes de forme sur le piege Q6
  (le maire n'est pas elu par les habitants - "les habitants" absent comme corps electoral ; aucune
  bonne reponse ne decrit une decision du conseil municipal, sur les memes mots que
  `cm1-emc-institutions.yaml` sert pour ce role, s46). `EMC-1`, `EMC-2`, `EMC-5`, `EMC-8` (lecture
  semantique, declaration de comportement) restent a `nadia-prof`, dit explicitement par le
  commentaire de tete. **Contrairement aux deux fichiers d'EMC de s46**, le commentaire de
  `chaqueItemDeclareUniquementEmcCm110` dit lui-meme "deja tenu par ADR-0081, non repete ici" -
  l'incoherence de redaction notee au rapport de s46 n'est pas reproduite sur ce fichier.
  `CYC-1` (continuite cycle 2/cycle 3) n'est pas un critere teste par ce fichier : elle est verifiee
  **non due** par le planning (section 3), sur la ligne miroir du CP, et je l'ai revrifiee par
  lecture directe de `MATRICE-couverture.md` (section 6 ci-dessous) : aucun paquet ne la sert,
  confirme et non suppose.

## 5. Resolution des fusions successives sur les fichiers transverses

Deux fusions sur cinq portent un conflit textuel reel, trace en toutes lettres dans leur message de
merge : `b799cce6` (`catalogue.yaml`, `TexteAccentueExposeParLePortEntrantTest.java`) et `7d3cc9ee`
(`catalogue.yaml`, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java`,
`TexteAccentueExposeParLePortEntrantTest.java`). Verifie sur l'arbre final : **aucune marque de
conflit residuelle** (`git grep` sur `<<<<<<<`/`=======`/`>>>>>>>`, arbre entier suivi par Git,
vide). `catalogue.yaml` porte ses 120 entrees sans doublon (section 1). Les deux fichiers
transverses de test portent leurs entrees neuves en pure insertion, verifie par diff (section 3).
Un sixieme commit, `5015d96c` (fusionne par `06449adc`), corrige apres coup le defaut de ligature
signale en en-tete - ce correctif est lui-meme verifie, pas seulement lu, par la morsure 1 (section
2).

## 6. Matrice de couverture - etat constate, non modifie

Les cinq lignes du perimetre de `DEC-0104` restent `non couvert`, comme attendu avant le Done
pedagogique des deux instances de `nadia-prof` :

| Ligne | Fichier, position | Etat constate |
|---|---|---|
| `MAT-CE1-07` | `squad/programme/MATRICE-couverture.md:772` | `non couvert` |
| `QLM-CP-15` | `squad/programme/MATRICE-couverture.md:1818` | `non couvert` |
| `LVE-CP-06` | `squad/programme/MATRICE-couverture.md:1933` | `non couvert - jouable en francais seulement...` |
| `EMC-CP-14` | `squad/programme/MATRICE-couverture.md:2199` | `non couvert - jouable au choix, sous EMC-7...` |
| `EMC-CM1-10` | `squad/programme/MATRICE-couverture.md:2252` | `non couvert - jouable au choix, sur la connaissance seule...` |

Aucune des cinq lignes n'a ete modifiee par ce rapport. `CYC-1` reverifiee a cette occasion (section
4) : la ligne miroir de CP ("participer a une decision collective par un vote") reste
`non couvrable en l'etat`, confirme par lecture directe de la ligne "Construire une culture
civique", CP. Aucun fichier de conformite de ce sprint ne porte encore de verdict `VALIDE` : le
Done pedagogique n'est pas encore prononce.

## 7. Tests instables

Aucun test instable observe. Le lancement complet cite dans ce rapport (section d'en-tete) n'a ete
execute qu'une fois ; un test lance une seule fois n'est pas qualifie de stable pour autant, et ce
rapport ne l'affirme pas.

## 8. Ce que ce rapport ne fait pas

Il ne prononce aucun Done pedagogique - les deux instances de `nadia-prof`, seules, le font, sur
les cinq lots. Il ne juge le niveau scolaire d'aucun des cinq lots, ni l'exactitude des faits
culturels, historiques ou institutionnels qu'ils servent. Il n'etablit aucun total sur l'arbre
fusionne `round-two` au-dela de ce qui est deja fusionne dans `06449adc`. Il ne modifie aucun
contenu ni aucun code de production : les deux mutations de la section 2 ont ete ecrites, mesurees
puis integralement annulees avant la cloture (`git status --short` vide apres chacune).

---

**Conclusion** : `mvn clean verify` (via `outils/mvn-jdk21.sh`) est vert sur l'arbre fusionne des
cinq lots, `BUILD SUCCESS`, 0 echec, 0 erreur, 8 min 27 s. Le compte de tests (1938, +40 depuis s46)
est explique integralement par les cinq paquets neufs, sans reste, et zero ligne de `src/main/java`
n'est touchee sur l'ensemble du sprint. Deux garde-fous ont ete reellement mutes sur le materiau de
production et ont rougi puis reverdi comme attendu : celui que l'orchestrateur venait de corriger
(la ligature hors Latin-1), et celui qui protege l'amendement pedagogique le plus recent du sprint
(la cinquieme famille de `TAB-CE1-6`). Un balayage independant confirme qu'aucun autre caractere
hors Latin-1 ne s'est glisse dans les cinq paquets neufs, y compris dans des champs que le
garde-fou existant ne regarde pas. Les trois garde-fous dont le compte devait bouger ont tous
bouge, verifies un par un plutot que supposes homogenes. Chaque critere d'acceptation automatisable
des cinq stories est trace dans un nom de methode ou un commentaire d'assertion, a l'exception
explicite de ce qui revient a l'audit semantique de `nadia-prof` ; une observation de redaction sans
consequence de couverture est signalee sur un fichier (nom de methode plus large que son corps),
deja connue depuis s46 sur d'autres fichiers et non corrigee sur celui-ci. **La porte technique de
s47 est verte.**
