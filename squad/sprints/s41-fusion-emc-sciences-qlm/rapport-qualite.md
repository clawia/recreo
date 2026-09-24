# Rapport qualite de cloture - s41

**Auteur** : `yanis-qa`. **Date** : 2026-09-24. **Arbre mesure** : `s41-yanis` a `b5b07966`,
worktree dedie (`git worktree add ../recreo-s41-yanis -b s41-yanis round-two`), lignee verifiee
(`bash outils/verifier-lignee.sh` -> *"le worktree sur la branche 's41-yanis' descend de la lignee
de round-two (ancetre commun trouve dans son historique)"*), separe de tout autre worktree
partage.

Contenu du sprint : six lots de contenu statique, deux terrains, deux instances paralleles de
`nadia-prof` (`DEC-0095`) - `S41-EMC-SYMBOLES` et `S41-EMC-DROITS` (`[MARC]`), `S41-EMC-ROLES` et
`S41-SCI-CLASSER` (`[LEA]`), `S41-QLM-HYGIENE` et `S41-QLM-SAISONS` (`[SOF]`), fusionnes dans
`round-two` par trois commits de fusion successifs (`[LEA]` `2d6b83bc`, `[SOF]` `721b94bd`, `[MARC]`
`ddd7d3f5`/`6d7a7492`/`b5b07966`).

Aucun verrou `mvnd` transitoire rencontre : `bash outils/mvn-jdk21.sh -B clean verify`, lance une
seule fois sur ce worktree, a rendu `BUILD SUCCESS` du premier coup.

---

## 1. Compte de tests autoritaire

**1701 tests : 1695 unitaires + 6 tests d'integration**, sur `s41-yanis` a `b5b07966`. Methode :
somme des blocs `[INFO] Results:` apres `clean` sur `bash outils/mvn-jdk21.sh -B clean verify`,
recoupee par la somme independante des attributs `tests="..."` des 231 rapports XML
Surefire/Failsafe du meme lancement. Les deux methodes convergent a 1701.

JDK constate en interrogeant le JDK resolu par `outils/mvn-jdk21.sh` (jamais la toolchain
declaree) : **21.0.10**, coherent avec les lignes de demarrage Spring Boot du meme lancement
(`using Java 21.0.10`) et avec le diagnostic independant d'`outils/verifier-jdk.sh` (`java du PATH`
mesure a une version 17, distincte du JDK de compilation resolu a 21.0.10 - les deux verifications
convergent sur la meme valeur sans partager la meme source). `BUILD SUCCESS`, 6 modules sur 6, 0
echec, 0 erreur.

**Resultats par module** :

| Module | Tests run |
|---|---|
| recreo-domain | 120 |
| recreo-application | 17 |
| recreo-adapter-web | 400 |
| recreo-adapter-content | 881 |
| recreo-bootstrap (surefire, unitaires) | 277 |
| recreo-bootstrap (failsafe, integration) | 6 |
| **Total unitaires** | **1695** |
| **Total integration** | **6** |

`adapter-content` passe de 850 (mesure de `yanis-qa`, rapport s40, sur `s40-yanis` a `652cafc8`) a
881, soit **+31 tests**, portes par les six paquets neufs. `domain`, `application`, `adapter-web`
et `bootstrap` sont inchanges - coherent avec l'avis de `theo-archi` (section 3) : *"zero ligne de
Java de production"* attendue sur les six lots. Base de comparaison : `652cafc8` (dernier commit
mesure par `yanis-qa` a s40).

**Couverture par module** (fichiers `*Test.java`/`*IT.java` presents, aucun module vide) :

| Module | Fichiers de test |
|---|---|
| domain | 11 |
| application | 2 |
| adapter-web | 49 |
| adapter-content | 121 |
| bootstrap | 50 |

`adapter-content` passe de 115 (s40) a 121, soit exactement **+6 fichiers** - un par lot
(`PaquetCpEmcSymbolesTest`, `PaquetCpEmcDroitsEnfantTest`, `PaquetCpEmcRolesEcoleCommuneTest`,
`PaquetCpQlmHygieneTest`, `PaquetCpQlmSaisonsTest`, `PaquetCm1SciencesClasserEtresVivantsTest`),
confirmant qu'aucun module n'est reste sans test au premier commit de son contenu.

Ce chiffre est un compte sur mon propre worktree, pas un total sur l'arbre fusionne `round-two`
au-dela de ce qui est deja fusionne dans `b5b07966` : l'integration ulterieure reste a `sofia-sm`.

**ArchUnit** : **36** `static final ArchRule` et **35** `@ArchTest`, comptes par occurrences
litterales de ces deux chaines sous `src/test/java` des cinq modules declares au pom agregateur,
`target/` exclu, methode d'`ADR-0095`/`ADR-0098`. Un balayage naif (sans exclure la fixture) rend
**38** et 35 : l'ecart de deux est porte par un seul fichier,
`bootstrap/src/test/resources/contreexemple/.../faux-module/src/test/java/com/recreo/faux/
RegleContreExemple.java`, dont le chemin contient lui-meme `src/test/java` et qui est une fixture
de contre-exemple, pas une regle du depot - je l'ai retrouve et exclu explicitement plutot que de
recopier le compte de l'avis de `theo-archi`. Compte inchange depuis s40, coherent avec l'avis
(*"aucune regle ArchUnit neuve n'est due"*). Aucune classe ArchUnit en echec dans le lancement
complet.

## 2. Les quatre jobs de `.github/workflows/ci.yml`, nommes un par un

| Job | Commande executee | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` (equivalent du `mvn -B verify` du job, sur le JDK que la toolchain resout) | **Vert** - `BUILD SUCCESS`, 0 echec |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` (19 cas) puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **Vert** - tous les cas passent, aucun chemin de poste ni nom de compte trouve |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh` (15 cas), `bash .github/scripts/commit-atteignable-test.sh` (5 cas), puis `git log --no-merges --format=%s -20 \| bash .github/scripts/verifier-prefixes.sh` | **Vert** - tous les cas passent, les 20 derniers commits portent tous un prefixe d'agent connu |
| `outils` | balayage des trois motifs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) | **Vert** - 9 juges purs executes, aucun motif vide, 0 echec |

Aucun des quatre n'est deduit des autres : chacun a ete lance separement dans le worktree, et
`git status --short` est reste vide apres chacun (aucun effet de bord laisse par les scripts
d'enveloppe, y compris ceux qui invoquent Maven, comme `mvn-jdk21-test.sh`).

## 3. Tracabilite - criteres d'acceptation encodes, lot par lot

Chaque story de `docs/backlog/backlog.md`, section "Sprint 41", cite des codes de condition
(`FER-1`, `FER-2`, `FER-3`, `ART-1`, `BIA-4`, `EMC-1`, `EMC-6`, `BIN-1`). Verification faite
methode par methode : le fichier de test du paquet porte-t-il une methode nommee sur le code, et
cette methode l'assertionne-t-elle reellement.

| Lot | Criteres encodes en test, et methode | Ce qui reste garde de `nadia-prof` (non encodable, motif ecrit dans le fichier de test) |
|---|---|---|
| `S41-EMC-SYMBOLES` | `fer1LesQuatreSymbolesSontEnBonneReponseDeLaTacheDeReconnaissance`, `fer2AuMoinsDixItems`, `fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems`, `art1AucunMotDeCouleurDansLePaquet`, `chaqueItemDeclareUniquementEmcCp08` | que chaque distracteur soit un symbole ou une notion reelle du registre republicain (lecture de sens) |
| `S41-EMC-DROITS` | `fer1LesQuatreDroitsSontEnBonneReponseDeLaTacheDeReconnaissance`, `fer2AuMoinsDixItems`, `fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems`, `bia4Emc1AucuneAdresseDirecteALEnfant`, `chaqueItemDeclareUniquementEmcCp10` | idem, lecture de sens sur les distracteurs |
| `S41-EMC-ROLES` | `fer1LesQuatreRolesEtServicesSontChacunEnBonneReponse`, `fer2AuMoinsDixItems`, `fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems`, `laTacheQueFaitNeReditJamaisUnRoleEnBonneReponse` | `EMC-6` (aucun item ne suppose la commune, l'ecole ou le foyer de l'enfant) - non gardable par une regle generique, explicitement note *"ce que ce test ne juge pas"* dans le fichier |
| `S41-QLM-HYGIENE` | `fer2AuMoinsDixItems`, `lesTroisDomainesDuLibelleSontChacunServis`, `bia4AucunEnonceNeSAdresseALEnfantALaDeuxiemePersonne` | lecture de sens generale ; l'adjacence avec `cp-eps-corps-effort` nommee par l'avis de `theo-archi` (section 6.4) n'est gardee par aucun automate du depot |
| `S41-QLM-SAISONS` | `fer1LesDeuxMoitiesSontChacuneServiesParDesItemsDistincts`, `fer2AuMoinsDixItems`, `unItemDeNommerDemandeUnNomDeMoisOuDeSaisonSeul`, `unItemDOrdonnerPoseUneRelationDOrdreEntreDeuxMoisOuSaisonsNommes` | lecture de sens generale |
| `S41-SCI-CLASSER` | `fer2AuMoinsDixItems`, `bin1AuPlusLaMoitieDesItemsDuPaquetSontBinaires` (condition de faisabilite de `theo-archi`), `chaqueItemProposeExactementDeuxDistracteurs` (`V3`), `chaqueItemMetEnJeuTroisPropositionsDistinctes` | `CYC-1` (aucune contradiction avec `cp-qlm-vivant-non-vivant` et `cp-qlm-cycle-de-vie`) - demande de comparer deux paquets, ce qu'aucun garde-fou du depot ne fait ; verifie a la main dans le commentaire du fichier de contenu |

Aucun critere d'acceptation des six stories n'est reste sans test et sans motif explicite de
non-encodage. Les motifs de non-encodage sont ceux deja rendus par `theo-archi` dans son avis de
faisabilite (section 6) : je ne les recopie pas sans verification, je les ai retrouves dans le
code livre.

## 4. Mutation de garde-fous sur materiau reel

Regle appliquee : le garde-fou est mis a l'epreuve en desactivant ou en niant ce qu'il protege, sur
le fichier ou la classe reellement servi(e), jamais une fixture. Restauration par `git checkout --`
apres chaque mutation, `git status --short` verifie vide apres chaque restauration.

### 4.1 `BIN-1` sur `PaquetCm1SciencesClasserEtresVivantsTest` - condition de faisabilite de `theo-archi`

Le paquet reel ne porte aucun item binaire (tous les onze items du fichier de contenu portent deux
distracteurs) : l'assertion `bin1AuPlusLaMoitieDesItemsDuPaquetSontBinaires` est verte par
construction sur le contenu livre, ce qui ne prouve pas qu'elle mordrait si le contenu changeait.

Mutation : dans `cm1-sciences-classer-etres-vivants.yaml`, six des onze items (plus de la moitie)
voient leur second distracteur retire, les rendant binaires.

- **Fichier mute** : rouge, `Tests run: 5, Failures: 3`. L'assertion `BIN-1` echoue precisement sur
  le message attendu - *"Expecting actual: 6L to be less than or equal to: 5L"* - accompagnee de
  deux echecs collateraux attendus (`V3` et le nombre de propositions distinctes), qui confirment
  que la mutation est bien celle visee et pas une autre.
- **Fichier restaure** : vert, `Tests run: 5, Failures: 0`.

Confirme que `BIN-1`, encode selon la condition posee par `theo-archi` (avis de faisabilite,
section 6.6), mord reellement sur le paquet servi et pas seulement sur un jeu synthetique.

### 4.2 `ART-1` sur `PaquetCpEmcSymbolesTest` - critere d'acceptation de `S41-EMC-SYMBOLES`

Le paquet reel ne porte aucun mot de couleur : meme situation, une assertion verte par construction
sur le contenu livre.

Mutation : dans `cp-emc-symboles.yaml`, le mot "bleu" est ajoute a un distracteur du premier item
(`"Marianne bleu"`).

- **Fichier mute** : rouge, `Tests run: 6, Failures: 1` - *"item item-0 : un distracteur ne doit
  porter aucun mot de couleur (ART-1)"*.
- **Fichier restaure** : vert, `Tests run: 6, Failures: 0`.

Confirme que le critere d'acceptation *"elle ne demande jamais la couleur d'un element"* de la
story `S41-EMC-SYMBOLES` est verifie par un test qui mord, pas seulement par un enonce de critere.

### 4.3 `PAR-5` / `ADR-0085` - allocation du registre sur la citation `BIN-1`

Ce garde-fou transverse (`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`) n'est pas neuf a
ce sprint, mais il est mis a l'epreuve sur la citation reelle qu'un lot neuf introduit.

Mutation : dans `squad/decisions/REGISTRE-identifiants.md`, la ligne `| \`BIN-1\` | ... |` retiree.

- **Ligne retiree** : rouge, `Tests run: 7, Failures: 1`. Message exact : *"...
  cm1-sciences-classer-etres-vivants.yaml ecrit l'identifiant BIN-1, que
  squad/decisions/REGISTRE-identifiants.md n'alloue pas [...] (PAR-5 ; ADR-0085, clause 2)"* - le
  garde-fou trouve la citation dans le contenu servi du sprint courant, pas seulement dans un
  arbitrage.
- **Ligne restauree** : vert, `Tests run: 7, Failures: 0`.

Confirme que l'allocation de `BIN-1`, deja verifiee par `theo-archi` dans son avis (section 5.4),
tient a l'epreuve et redevient rouge si elle est retiree.

## 5. Tests instables

Aucun test instable observe. Chaque lancement cite dans ce rapport n'a ete execute qu'une fois par
etat (mute, puis restaure) ; un test lance une seule fois par etat n'est pas qualifie de stable
pour autant, et ce rapport ne l'affirme pas. Aucun verrou `mvnd` transitoire rencontre sur ce
worktree.

## 6. Ce que ce rapport ne fait pas

Il ne prononce aucun Done pedagogique - `nadia-prof`, sur ses deux instances paralleles, seule le
fait. Il ne juge le niveau scolaire d'aucun des six lots. Il n'etablit aucun total sur l'arbre
fusionne `round-two` au-dela de `b5b07966` : c'est `sofia-sm` qui l'etablit a l'integration. Il ne
modifie aucun contenu ni aucun code de production : les trois mutations de la section 4 ont ete
restaurees par `git checkout --` avant la redaction de ce rapport, chaque restauration verifiee par
`git status --short` vide.
