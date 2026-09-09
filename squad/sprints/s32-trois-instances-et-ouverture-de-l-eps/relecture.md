# Relecture pedagogique et QA de contenu - Sprint 32

- **Auteur** : `relecteur-pedagogique`.
- **Arbre relu** : `round-two` a `17024fab`, propre.
- **Perimetre** : `planning.md`, `rapport-qualite.md`, `confirmation-archunit.md` ; `ADR-0089`,
  l'amendement de `ADR-0086` (2026-09-08, s32), l'amendement de `ADR-0080` (2026-09-08, s32),
  `ADR-0088`, `ADR-index.md` ; `DEC-0083` et son amendement de section 13, `DEC-index.md` ;
  `ARBITRAGE-PREALABLE-s32-...` sections 16 et 17, `REGISTRE-identifiants.md` ; `docs/backlog/backlog.md`
  (sections Sprint 32 et backlog s33) ; les commentaires et le contenu enfant des paquets touches ce
  sprint (`cm1-histoire-geographie.yaml`, `cm1-anglais.yaml`, les six paquets d'EMC CP,
  `cp-eps-regles-de-securite.yaml`, `cp-eps-regles-jeu-collectif.yaml`, entrees neuves de
  `catalogue.yaml`).

## 1. Ce que j'ai corrige

**Rien.** Aucune faute d'orthographe, de ponctuation ou d'accentuation trouvee sur le contenu lu
par l'enfant dans les paquets touches ce sprint. Aucun tiret cadratin, aucune guillemet
typographique, aucun emoji ni symbole decoratif, aucun chemin de poste de travail ni nom de compte
dans les fichiers du perimetre (`bash .github/scripts/verifier-chemins.sh` execute sur la liste
ci-dessus, verdict : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers
verifies."*). Aucun commentaire qui paraphrase le code trouve dans les blocs de commentaires
ajoutes ce sprint (`cm1-histoire-geographie.yaml` lignes 214 a 286, `cm1-anglais.yaml`, les six
paquets d'EMC CP, les deux paquets d'EPS CP).

**References verifiees, toutes resolvent et portent ce qu'on leur prete** : les 16 SHA cites dans
`planning.md` (`043b8503`, `b1ee89f4`, `cb0b191e`, `a613c38d`, `7ed471a7`, `63590b33`, `7c800261`,
`17024fab`, `f503b4e5`, `60bebbce`, `ad5f307c`, `167a4421`, `90b01fdd`, `b7a79e34`, `ba2871ea`,
`559648c6`) existent tous et portent le message annonce. Les entrees `ADR-0087` a `ADR-0089` de
`ADR-index.md`, l'entree `DEC-0083` de `DEC-index.md`, les sections 16 et 17 de l'arbitrage
prealable, les entrees `PAR-12`, `PAR-13`, `LON-1`, `LON-2`, `CAT-1`, `EPS-1` a `EPS-3`, `BIA-4` du
registre d'identifiants existent et portent le contenu annonce. La note sur `s32-theo` non fusionnee
dans `docs/backlog/backlog.md` est deja corrigee (ligne 12208, "fusionnee dans round-two au planning
de s32, commit `cb0b191e`") - pas d'affirmation perimee restante trouvee sur ce point.

**Chiffres verifies contre le code, tous exacts sauf un (section 2.2)** : le plancher `FER-3` (code
`MINIMUM_PAR_TACHE = 3`, confirme dans neuf fichiers de test dont
`PaquetCm1HistoireGeographieTest`), le delta de tests +10 sur le lot de `marc-dev` (commit
`90b01fdd`, message d'origine et mesure independante concordants, aucune trace residuelle de "+12"
dans le perimetre), le compte du registre 547/94 (`REGISTRE-identifiants.md`, ligne 16, verifie par
sa clause 5 selon `ADR-0088`), le nombre d'items des deux paquets d'EPS (onze chacun, `grep -c
"enonce:"`), le nombre d'entrees du catalogue (68), les cinq comptes de fichiers de test par module
de `rapport-qualite.md` (domain 15, application 3, adapter-web 49, adapter-content 96, bootstrap
84). Les trois comptes de la section 16.5 de l'arbitrage prealable, dements par la mesure de
`theo-archi` (`EMO-R1`, `EXC-R4`, `POL-R1`) : traites correctement partout ou je les ai trouves -
routes a `nadia-prof`, aucune reserve modifiee, aucune affirmation qui les presente comme clos.

## 2. Points a trancher

### 2.1 `cp-emc-regle-souhait-loi.yaml`, item 11 - "vendre l'alcool" au lieu de "vendre de l'alcool"

**Constat.** L'enonce sert desormais : *"Une loi interdit de vendre l'alcool aux moins de dix-huit
ans. Qui peut en acheter ?"* Le commentaire du fichier (ligne ajoutee par `167a4421`) explique que
cet enonce et celui de l'item 9 sont *"raccourcis d'un mot chacune"* pour tenir `LON-1`. Sur
l'item 11, le mot retire est la preposition "de" du partitif ("vendre **de l'**alcool" devient
"vendre l'alcool") : un enfant de CP lit "l'alcool" comme un alcool defini plutot que la
substance en general, la forme n'est pas le francais correct qu'on attend d'un enonce enfant.

**Pourquoi je ne le corrige pas moi-meme.** Reinserer "de" porte l'enonce a 16 mots sous la
convention de comptage de l'arbitrage prealable (section 16.1), soit un de plus que `LON-1`
n'autorise - une regle desormais executee par un test vert
(`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`). Corriger la grammaire ferait donc rougir un
garde-fou ; reformuler autrement pour gagner un mot ailleurs est un choix de redaction pedagogique,
pas une correction d'orthographe sans ambiguite.

**Route** : `nadia-prof` (source de `LON-1`, seule autorite sur la formulation du contenu enfant) et
`lea-dev` (auteure de l'item, `167a4421`).

### 2.2 `rapport-qualite.md` - le compte des `ArchRule` est faux d'une unite

**Constat, verifie contre le code.** `rapport-qualite.md` (section 1) annonce *"36 declarations
`ArchRule`... excluant... le faux depot de test-ressources `RegleContreExemple.java` qui en declare
**une** factice... : **35** regles reelles."* `RegleContreExemple.java` declare en realite **deux**
`ArchRule` factices (`uneRegleEprouvee` et `uneRegleJamaisEprouvee`, verifie par lecture directe du
fichier), pas une seule. Le compte reel est donc **34** (36 - 2), pas 35.

**Ce chiffre corrige est celui que le reste du sprint porte deja, sans contradiction** : `34` est
exactement ce qu'`ADR-index.md` annonce pour `ADR-0088` (*"la clause 12 ... fait passer le compte de
33 a 34"*) et ce qu'`ADR-0089` confirme en n'ajoutant aucune regle ArchUnit. Le "35" de
`rapport-qualite.md` est donc le seul chiffre du perimetre qui se contredit avec le reste du depot
sur ce point, par une erreur de methode (compte du faux depot) et non par une mesure differente.

**Route** : `yanis-qa`, auteur du rapport, pour correction du chiffre et de la description du faux
depot ("une factice" -> "deux factices").

### 2.3 `DEC-0083` - la citation de commit `dcbcd65a` (avis de faisabilite de `theo-archi`) pointe vers un commit devenu inaccessible

**Constat.** La section "Lien" et la section 13 de `DEC-0083`, ainsi que
`docs/backlog/backlog.md` (Backlog ouvert pour s33), citent
`squad/sprints/s32-instruction-prealable/avis-faisabilite-theo-achevement-du-palier-2.md` sur la
branche `s32-theo`, **commit `dcbcd65a`**. Ce commit existe encore comme objet Git (`git cat-file -t
dcbcd65a` repond `commit`) mais **n'est atteignable depuis aucune branche ni aucun tag du depot**
(`git branch -a --contains dcbcd65a` et `git tag --contains dcbcd65a` ne rendent rien). Un second
commit, `96c8efbe`, porte le meme message mot pour mot et le meme contenu de fichier, environ une
heure plus tard sur un arbre different (celui qui est effectivement devenu `043b8503` puis fusionne
sous `cb0b191e`) : `dcbcd65a` est un residu d'un geste anterieur (fork puis rebase sur `round-two`
apres que `DEC-0083` a atterri), jamais rattache a la branche finalement fusionnee.

**Consequence** : la citation resout aujourd'hui (l'objet n'a pas encore ete elague), mais elle
pointe vers un commit orphelin qui peut disparaitre au prochain `git gc`, alors que le meme contenu
est verifiable, en pratique, sous `96c8efbe`/`043b8503`, sur la branche reellement fusionnee. C'est
la meme famille de defaut que la reference qui ne resolvait pas - ici elle resout encore, mais sur
un objet fragile plutot que sur l'historique reel.

**Route** : `theo-archi`, auteur des deux commits et de la citation dans `DEC-0083` (section
"Lien" et section 13), pour remplacer la reference par un commit atteignable depuis `round-two`
(par exemple `96c8efbe` ou `043b8503`).

## 3. Ce que je n'ai pas fait

Aucune ligne de `squad/programme/MATRICE-couverture.md` modifiee. Aucun Done prononce, technique ou
pedagogique. Aucun jugement sur le niveau scolaire, le ton ou le fond du contenu enfant - seule
l'orthographe et la grammaire de ce contenu relevent de moi, et je n'y ai rien trouve a corriger en
dehors du point 2.1, qui est routee plutot que corrigee pour le motif explique.

## 4. Les quatre jobs de la CI, lances sur mon propre diff avant ce commit

| Job | Commande | Verdict |
|---|---|---|
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh` ; `bash .github/scripts/commit-atteignable-test.sh` ; `git log --no-merges --format=%s -1 \| bash .github/scripts/verifier-prefixes.sh` | Vert |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` ; `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | Vert. *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | boucle du workflow sur `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py` | Vert, 9 juges executes |
| `build` | Non relance : cette relecture ne touche aucun fichier de code, de test ni de contenu servi (elle ajoute uniquement ce fichier `relecture.md`), donc `mvn verify` n'est pas affecte par mon diff. Le dernier verdict connu du job `build` sur l'arbre relu est celui de `rapport-qualite.md` (`17024fab`) : vert. |
