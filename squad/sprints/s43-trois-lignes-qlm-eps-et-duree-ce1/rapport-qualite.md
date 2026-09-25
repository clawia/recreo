# Rapport qualite de cloture - s43

**Auteur** : `yanis-qa`. **Date** : 2026-09-25. **Arbre mesure** : `s43-yanis` a `9e1622dc`
(hash complet `9e1622dc60ac309c692b7b92f807b11725e22ad7`), worktree dedie
(`git worktree add ../recreo-s43-yanis -b s43-yanis round-two`), lignee verifiee
(`bash outils/verifier-lignee.sh`, lance depuis le worktree lui-meme -> *"le worktree sur la
branche 's43-yanis' descend de la lignee de round-two (ancetre commun trouve dans son
historique)"*).

Contenu du sprint : trois lots de contenu statique, instance unique de `nadia-prof`
(`DEC-0099` section 9, amendee) - `S43-QLM-NUMERIQUE` (`QLM-CP-08`, `[MARC]`),
`S43-EPS-EFFETS-ACTIVITE` (`EPS-CM1-10`, `[LEA]`), `S43-MAT-DUREE` (`MAT-CE1-12`, `[SOF]`). Deux
`ADR` de `theo-archi` preparent le terrain avant les paquets : `ADR-0101` (barre de longueur CE1,
avec son cliquet exact) et `ADR-0102` (lecture des durees en toutes lettres). Contrairement a s42,
ce sprint ne fusionne rien - un seul terrain, aucune instance parallele. `git log --oneline` confirme
que `9e1622dc` est une fusion sans conflit de `s43-lea`, elle-meme un point de convergence des trois
branches de developpeurs (`e11a9325`, `f1dac400`, `e980f31a`) sur `round-two`.

`bash outils/mvn-jdk21.sh -B clean verify`, lance une seule fois sur ce worktree, a rendu
`BUILD SUCCESS` du premier coup, en 5 min 47 s. Aucun verrou `mvnd` transitoire rencontre.

---

## 1. Compte de tests autoritaire

**1775 tests : 1769 unitaires + 6 tests d'integration**, sur `s43-yanis` a `9e1622dc`. Methode :
somme des six blocs `[INFO] Results:` (un par module) du meme lancement de
`bash outils/mvn-jdk21.sh -B clean verify`, recoupee par la somme independante des attributs
`tests="..."` des **239** rapports XML Surefire/Failsafe produits par ce meme lancement. Les deux
methodes convergent a **1775** - jamais la somme des lignes `Tests run:` des fichiers `.txt`
(`squad/README.md`, amendement s11).

JDK constate en interrogeant le JDK resolu par `outils/mvn-jdk21.sh` (jamais la toolchain
declaree) : **21.0.10** (Apache Maven 3.9.9), coherent avec les lignes de demarrage Spring Boot du
meme lancement (`using Java 21.0.10`) et avec le diagnostic independant d'`outils/verifier-jdk.sh`
(`java du PATH` mesure a une version **17.0.4.1**, distincte du JDK de compilation resolu a
21.0.10 - les deux verifications convergent sur la meme valeur sans partager la meme source).
`BUILD SUCCESS`, 6 modules sur 6, 0 echec, 0 erreur.

**Resultats par module** :

| Module | Tests run |
|---|---|
| recreo-domain | 120 |
| recreo-application | 17 |
| recreo-adapter-web | 400 |
| recreo-adapter-content | 944 |
| recreo-bootstrap (surefire, unitaires) | 288 |
| recreo-bootstrap (failsafe, integration) | 6 |
| **Total unitaires** | **1769** |
| **Total integration** | **6** |

`domain`, `application` et `adapter-web` sont inchanges depuis le dernier rapport de cloture
(s42, `455b4c24` : 120, 17, 400) - coherent avec l'avis de `theo-archi` : les trois lots sont du
contenu statique, **zero ligne de Java de production**. Confirme directement :
`git diff --stat 1abc3157..HEAD -- '**/src/main/java'` (base = juste apres l'ecriture des
discriminants `EPS-9`/`DUR-CE1-7`, avant le premier paquet) ne rend **aucune ligne**.
`adapter-content` et `bootstrap` bougent - le premier par les trois paquets, le second par le
garde-fou neuf d'`ADR-0101`.

**Methode et perimetre de la mesure de base sur `adapter-content`** : mesure directe sur un second
worktree dedie a `1abc3157` (le dernier commit avant le premier paquet, juste apres les deux
discriminants pedagogiques et les deux `ADR`) :
`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am clean test`, **module mesure** :
`adapters/adapter-content` et ses deux dependances amont, pas le depot entier : **916**. Worktree
supprime apres mesure (`git worktree remove --force`), aucune trace laissee sur `round-two`.
`adapter-content` passe donc de **916** a **944**, soit **+28 tests** - exactement la somme des
`@Test` des trois fichiers neufs (`PaquetCe1ProblemesDureesTest` : 11, `PaquetCm1EpsEffetsActiviteTest` :
10, `PaquetCpQlmObjetsNumeriquesTest` : 7). Aucun test perdu, aucun test deplace en silence.

*Ce chiffre de base n'est pas celui de s42 (`881` a `cdf0229f`, puis `907` apres les cinq lots) :
sept commits de cloture de s42 (BIA-5, `ADR-0100`, corrections de contenu) s'intercalent entre la
fin de s42 et le debut de s43, et deux d'entre eux touchent des fichiers de test
(`PaquetCm1HistoireGeographieTest`, `PaquetCpDeplacementsQuadrillageTest`) sans rapport avec ce
sprint. Le delta cite ici (+28) est mesure sur la base la plus proche du premier paquet de s43,
pas sur celle de la cloture de s42.*

**Couverture par module** (fichiers `*Test.java`/`*IT.java` presents, aucun module vide) :

| Module | Fichiers de test |
|---|---|
| domain | 11 |
| application | 2 |
| adapter-web | 49 |
| adapter-content | 128 |
| bootstrap | 51 |

`adapter-content` passe de 125 (dernier compte connu, s42) a 128, soit exactement **+3 fichiers** -
un par paquet neuf (`PaquetCpQlmObjetsNumeriquesTest`, `PaquetCm1EpsEffetsActiviteTest`,
`PaquetCe1ProblemesDureesTest`), confirmant qu'aucun paquet neuf n'est reste sans test a son
premier commit. `bootstrap` passe de 50 a 51 : le seul fichier neuf est
`AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest` (`ADR-0101`), confirme par
`git diff --stat cdf0229f..HEAD -- bootstrap/src/test/java`.

**ArchUnit** : **36** `static final ArchRule` et **35** `@ArchTest`, comptes par occurrences
litterales de ces deux chaines sous `src/test/java` des cinq modules declares au pom agregateur,
`target/` exclu, methode d'`ADR-0095`/`ADR-0098`. Un balayage naif (sans exclure la fixture) rend
**38** et 35 : l'ecart de deux est porte par le meme fichier de contre-exemple qu'a s42,
`bootstrap/src/test/resources/contreexemple/.../faux-module/src/test/java/com/recreo/faux/
RegleContreExemple.java`. Compte **inchange depuis s42** - coherent avec les deux `ADR` de ce
sprint, qui declarent explicitement ne pas etre des `ArchRule` (la longueur d'un enonce YAML et la
valeur d'une proposition ne sont pas des proprietes de bytecode). Aucune classe ArchUnit en echec
dans le lancement complet.

## 2. Les quatre jobs de `.github/workflows/ci.yml`, nommes un par un

| Job | Commande executee | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` (equivalent du `mvn -B verify` du job, sur le JDK que la toolchain resout) | **Vert** - `BUILD SUCCESS`, 0 echec |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` (22 cas) puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **Vert** - tous les cas passent, aucun chemin de poste ni nom de compte trouve |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh` (15 cas), `bash .github/scripts/commit-atteignable-test.sh` (5 cas), puis `git log --no-merges --format=%s -20 \| bash .github/scripts/verifier-prefixes.sh` | **Vert** - tous les cas passent, les 20 derniers commits portent tous un prefixe d'agent connu |
| `outils` | balayage des trois motifs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) | **Vert** - 9 juges purs executes, aucun motif vide, 0 echec |

Aucun des quatre n'est deduit des autres : chacun a ete lance separement dans le worktree, et
`git status --short` est reste vide apres chacun.

## 3. Tracabilite - criteres d'acceptation encodes, lot par lot

Chaque story de `docs/backlog/backlog.md`, section "Sprint 43", cite des criteres d'acceptation et
deux discriminants ajoutes le jour meme par `nadia-prof` (`EPS-9`, `DUR-CE1-7`), avant l'ecriture
des paquets. Verification faite methode par methode : le fichier de test du paquet porte-t-il une
methode nommee sur le code, et cette methode l'assertionne-t-elle reellement.

| Lot | Criteres encodes en test, et methode | Ce qui reste garde de `nadia-prof` (non encodable) |
|---|---|---|
| `S43-QLM-NUMERIQUE` | `fer2AuMoinsDixItems` (DEC-0064), `lesDeuxMoitiesDuLibelleSontChacuneServiesParPlusieursItems` (les deux moities du libelle representees), `aucunItemNeSAdresseALEnfantALaDeuxiemePersonne` (proxy formel contre l'injonction : "tu/ton/ta/tes" absent), `chaqueItemDeRegleDUsagePorteUneConsequenceFactuelle` (bonne reponse en "parce que..."), `chaqueItemDeclareUniquementQlmCp08` | que chaque regle d'usage se lise comme un fait verifiable et non un jugement moral au-dela de la deuxieme personne (lecture de sens - une injonction a l'impersonnel echapperait au proxy) ; plausibilite de chaque distracteur |
| `S43-EPS-EFFETS-ACTIVITE` | `fer1LesEffetsAttendusSontEnBonneReponse`, `fer1LesConduitesDeSecuriteSontEnBonneReponse`, `eps2AucunChiffreDansLePaquet` (EPS-2, proxy formel), `eps9Clause1LesTroisTachesDiscriminantesPortentAuMoinsDeuxTiersDesItems` (proportions et plancher par tache), `eps9Clause2AucunEnonceNeReprendUnPatronDejaServiAuCp`, `eps9Clause3AucuneBonneReponseDuCpNeDevientUnDistracteurIci`, `eps9Clause3AucunItemNeDemandeDeNommerUnOrgane` (non-recoupement avec `SCI-CM1-09`), `chaqueItemDeclareUniquementEpsCm110` | `EPS-1` (regle rappelee dans l'enonce ou universelle - lecture de sens), `EPS-3` (aucune conduite morale au-dela du filtre formel), `BIA-4` (registre impersonnel - lecture de sens) ; et surtout la substance du discriminant `EPS-9` au-dela de sa forme : aucun garde-fou du depot ne compare deux paquets (avis de `theo-archi`, sections 6, 7.2, repris textuellement par `nadia-prof` dans le backlog, qui cite explicitement `S42-EMC-SYMBOLES-CM1` comme precedent d'un garde-fou textuel vert alors que le fond refaisait le CP) |
| `S43-MAT-DUREE` | `lePaquetCompteAuMoinsVingtItems` (`DUR-CE1-6`), `chaqueItemPoseLaSeuleFormeAutorisee` (`DUR-CE1-1`), `lesInstantsSontAbregesEtLesDureesPortentToujoursLeMotMin` (`DUR-CE1-3`), `aucunFranchissementDeMinuitAucuneSecondeAucuneAmbiguiteMatinApresMidi` + `lesMinutesDesInstantsValentZeroOuTrente` + `chaqueDureeRecalculeeResteEntreTrenteMinutesEtQuatreHeures` (`DUR-CE1-2`, duree **recalculee depuis les instants**, jamais recopiee), `leFranchissementDeLaDemiHeureEstEntreUnTiersEtDeuxTiersDuPaquet` (`DUR-CE1-4`), `auMoinsDeuxDistracteursPortentDesMinutesSuperieuresA59` (`DUR-CE1-5`/`DUR-CE1-7` clause 5), `auMoinsUnTiersDuPaquetPorteUneDureeDeDeuxHeuresOuPlus` (`DUR-CE1-7` clause 2), `aucunIndiceNeReprendUnIndiceDeCm1` (`DUR-CE1-7` clause 1), `aucunEnonceNeReprendUnPrenomDeCm1` (`DUR-CE1-7` clause 3) | `LON-CE1-1`/`LON-CE1-2` et le rang de `DUR-CE1-5` sont verifies par les garde-fous partages (`ADR-0101`, `ADR-0102`), non repetes dans le fichier du paquet (le fichier de test le dit lui-meme, en tete de classe) ; la nature des huit situations (pas d'avion, pas de depart en vacances, pas de retard dramatise) reste une lecture de sens, garde de `nadia-prof` |

Aucun critere d'acceptation des trois stories n'est reste sans test et sans motif explicite de
non-encodage.

## 4. Mutation de garde-fous sur materiau reel

Regle appliquee : le garde-fou est mis a l'epreuve en desactivant ou en niant ce qu'il protege, sur
le fichier ou la classe reellement servi(e) par ce sprint, jamais une fixture. Restauration par
`git checkout --` apres chaque mutation, `git status --short` verifie vide apres chaque
restauration, et le test mute re-verifie vert apres restauration.

### 4.1 `ADR-0101` - dette de longueur CE1, inchangee, mais le garde-fou voit-il le contenu neuf ?

La dette gelee (`DETTE_DE_LONGUEUR = { ce1-longueurs.yaml : 4 }`, `PLAFOND_DE_DETTE = 4`) est
**inchangee** par ce sprint - ni la table ni le plafond ne citent `ce1-problemes-durees.yaml`. Cela
ne prouve rien seul : un garde-fou qui n'examinerait pas le paquet neuf serait vert par omission,
pas par conformite.

Mutation : dans `ce1-problemes-durees.yaml`, l'enonce du premier item allonge de 12 a 37 mots
("Nina est a l'ecole [...] de 8 h 30 a 9 h" devient "Nina est a l'ecole primaire du quartier tous
les jours de la semaine sauf le mercredi et le week-end de 8 h 30 le matin bien tot jusqu'a 9 h").

- **Fichier mute** : rouge, `Tests run: 8, Failures: 2`. Messages exacts : *"ce1-problemes-durees.yaml
  : 1 enonces depassent 20 mots la ou la dette en tolere 0 - item 1 (37 mots)"* et l'assertion
  comparative CP/CE1 rend 5 au lieu de 4 sur le corpus mute.
- **Fichier restaure** : vert, `mvn -pl bootstrap -am test -Dtest=AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest`, `BUILD SUCCESS`.

Confirme que le garde-fou lit reellement le paquet neuf, avec une tolerance de zero enonce
supplementaire au-dela de la dette gelee de s39.

### 4.2 `ADR-0102` - lecture des durees, sur une ecriture non ajoutee aux motifs

Le paquet livre choisit le registre `H h MM min` (`DUR-CE1-7` clause 4, pour garder l'erreur de
base 100 ecrivable) : le motif `DUREE_ET_DEMIE` ajoute par `ADR-0102` n'est donc pas exerce par ce
contenu. C'est la clause de **ressemblance** (`RESSEMBLE_A_UNE_DUREE`, qui fait rougir toute
ecriture de duree non reconnue) qui est le garde-fou pertinent ici.

Mutation : dans `ce1-problemes-durees.yaml`, le distracteur `"1 h 00 min"` du premier item remplace
par `"1 heure et quart"` - une ecriture qui ressemble a une duree (`\d+\s*heures?\b`) mais qu'aucun
motif de lecture ne reconnait.

- **Fichier mute** : rouge, `Tests run: 28, Failures: 1` sur
  `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`. Message exact : *"paquet
  ce1-problemes-durees, item item-0 : la proposition '1 heure et quart' ecrit une duree ou un
  instant que la lecture de valeur ne reconnait pas - elle serait lue comme le nombre nu 1, donc
  comparee a des minutes, et le rang mesure ne serait pas celui qui est servi (ADR-0102)"*.
- **Fichier restaure** : vert, `mvn -pl adapters/adapter-content -am test -Dtest=LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, `BUILD SUCCESS`.

Confirme que la clause de ressemblance d'`ADR-0102`, motivee par un defaut trouve avant l'ecriture
de ce paquet, mord bien sur le paquet reellement servi - et pas seulement sur les deux ecritures
que l'`ADR` citait en exemple.

### 4.3 `EPS-9` clause 3 - absence de recoupement CM1/CP sur `EPS-CM1-10`

**Perimetre honnete de cette mise a l'epreuve, a lire avant le resultat.** `theo-archi` ecrit dans
son avis (sections 6, 7.2) et `nadia-prof` le reprend dans le backlog : *"aucun garde-fou du depot
ne compare deux paquets"*. Ce qui existe et que je mets a l'epreuve ici sont trois clauses
**formelles** ecrites pour cette ligne (proportions de taches, patrons d'enonce du CP, bonnes
reponses du CP devenues distracteurs) - pas un jugement sur la profondeur reelle du CM1. Le
backlog cite lui-meme le precedent qui interdit de confondre les deux : `S42-EMC-SYMBOLES-CM1`
etait vert sur son garde-fou textuel pendant que cinq items sur treize refaisaient le CP au fond
(`squad/conformite/FICHE-bandit-manchot-symboles-cm1.md`, reserve `EMC-CM1-R1`).

Mutation : dans `cm1-eps-effets-activite.yaml`, le distracteur `"le corps doit se refroidir"` du
premier item (tache 1, relier deux effets a une cause unique) remplace par `"essoufflé"` - une
bonne reponse deja servie au CP par `cp-eps-corps-effort.yaml`.

- **Fichier mute** : rouge, `Tests run: 10, Failures: 1`. Message exact : *"item item-0 : le
  distracteur 'essoufflé' est une bonne reponse d'un item du CP (EPS-9, clause 3, CYC-1)"*.
- **Fichier restaure** : vert, `mvn -pl adapters/adapter-content -am test -Dtest=PaquetCm1EpsEffetsActiviteTest`, `BUILD SUCCESS`.

Confirme que le garde-fou formel de non-recoupement mord reellement sur le materiau servi. Il ne
prouve rien au-dela de sa forme : la difference substantielle entre le CM1 et le CP - les trois
taches sont-elles reellement plus exigeantes, pas seulement absentes du CP par leur enonce - reste
entierement la garde de `nadia-prof`, qui l'a ecrit elle-meme dans le backlog avant le premier item
et qui la verifiera en lisant les items un par un.

## 5. Tests instables

Aucun test instable observe. Chaque lancement cite dans ce rapport n'a ete execute qu'une fois par
etat (mute, puis restaure) ; un test lance une seule fois par etat n'est pas qualifie de stable
pour autant, et ce rapport ne l'affirme pas. Aucun verrou `mvnd` transitoire rencontre sur ce
worktree.

## 6. Ce que ce rapport ne fait pas

Il ne prononce aucun Done pedagogique - `nadia-prof`, seule, le fait, sur les trois lots. Il ne
juge le niveau scolaire d'aucun des trois lots. Il n'etablit aucun total sur l'arbre fusionne
`round-two` au-dela de ce qui est deja fusionne dans `9e1622dc` : l'integration ulterieure reste a
`sofia-sm`. Il ne modifie aucun contenu ni aucun code de production : les trois mutations de la
section 4 ont ete restaurees par `git checkout --` avant la redaction de ce rapport, chaque
restauration verifiee par `git status --short` vide et par un second lancement du test mute, vert.
Il ne juge pas la substance des discriminants `EPS-9` et `DUR-CE1-7` au-dela de leurs clauses
formelles encodees - `nadia-prof` l'a ecrit elle-meme comme sa garde exclusive, et je ne la lui
retire pas.
