# Rapport qualite de cloture - s40

**Auteur** : `yanis-qa`. **Date** : 2026-09-23. **Arbre mesure** : `s40-yanis` a `652cafc8`,
worktree dedie (`git worktree add ../recreo-s40-yanis -b s40-yanis round-two`), lignee verifiee
(`git rev-parse --abbrev-ref HEAD` -> `s40-yanis`, HEAD egal a `652cafc8`), separe de tout autre
worktree partage.

Contenu du sprint : `S40-NUTRITION`, `S40-REGLES`, `S40-LOI`, `S40-DEVISE` (`[MARC]`), `S40-HDA`,
`S40-EXTRACTION` (`[LEA]`, fusion `652cafc8`), avec un amendement `[THEO]` d'`ADR-0099` en cours de
route (deux garde-fous faux rouges corriges le jour meme ou le premier paquet a grille est arrive).

Aucun verrou `mvnd` transitoire rencontre : `bash outils/mvn-jdk21.sh -B clean verify`, lance une
seule fois sur ce worktree, a rendu `BUILD SUCCESS` du premier coup.

---

## 1. Compte de tests autoritaire

**1670 tests : 1664 unitaires + 6 tests d'integration**, sur `s40-yanis` a `652cafc8`. Methode :
somme des blocs `[INFO] Results:` apres `clean` sur `bash outils/mvn-jdk21.sh -B clean verify`,
recoupee par la somme independante des attributs `tests="..."` de tous les rapports XML
Surefire/Failsafe du meme lancement. Les deux methodes convergent a 1670.

JDK constate en interrogeant le JDK resolu par `outils/mvn-jdk21.sh` (jamais la toolchain
declaree) : **21.0.10**, coherent avec les lignes de demarrage Spring Boot du meme lancement
(`using Java 21.0.10`). `BUILD SUCCESS`, 6 modules sur 6, 0 echec, 0 erreur.

**Resultats par module** :

| Module | Tests run |
|---|---|
| recreo-domain | 120 |
| recreo-application | 17 |
| recreo-adapter-web | 400 |
| recreo-adapter-content | 850 |
| recreo-bootstrap (surefire, unitaires) | 277 |
| recreo-bootstrap (failsafe, integration) | 6 |
| **Total unitaires** | **1664** |
| **Total integration** | **6** |

`adapter-content` passe de 809 (mesure de `yanis-qa`, rapport s39) a 850, soit +41 tests portes par
les six lots de s40. `domain` passe de 111 a 120 (+9, porte par `TableauSupport` et sa mise a
l'epreuve). `bootstrap` unitaires passe de 269 a 277 (+8). `adapter-web` et l'integration sont
inchanges.

**Couverture par module** (fichiers `*Test.java`/`*IT.java` presents, aucun module vide) :

| Module | Fichiers de test |
|---|---|
| domain | 11 |
| application | 2 |
| adapter-web | 49 |
| adapter-content | 115 |
| bootstrap | 50 |

Ce chiffre est un compte sur mon propre worktree, pas un total sur l'arbre fusionne `round-two`
au-dela de ce qui est deja fusionne dans `652cafc8` : l'integration ulterieure reste a `sofia-sm`.

**ArchUnit** : **36** `static final ArchRule` et **35** `@ArchTest`, comptes par occurrences
litterales de ces deux chaines sous `src/test/java` des cinq modules declares au pom agregateur,
`target/` exclu - methode d'`ADR-0095`/`ADR-0098`, confirmee independamment plutot que recopiee
d'`ADR-0099`. Compte inchange depuis s39, coherent avec la clause d'`ADR-0099` qui declare
n'ajouter aucune regle ArchUnit neuve. Aucune classe en echec dans le lancement complet.

## 2. Les quatre jobs de `.github/workflows/ci.yml`, nommes un par un

| Job | Commande executee | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` (equivalent du `mvn -B verify` du job, sur le JDK que la toolchain resout) | **Vert** - `BUILD SUCCESS`, 0 echec |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` puis `git ls-files ... \| bash .github/scripts/verifier-chemins.sh` | **Vert** - 22 cas de test passent, aucun chemin de poste ni nom de compte trouve |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh` (15 cas), `bash .github/scripts/commit-atteignable-test.sh` (5 cas), puis `git log --no-merges --format=%s \| bash .github/scripts/verifier-prefixes.sh` sur les 20 derniers commits | **Vert** - 20 cas de test passent, tous les commits portent un prefixe d'agent connu |
| `outils` | balayage des trois motifs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) | **Vert** - 9 juges purs executes, aucun motif vide, 0 echec |

Aucun des quatre n'est deduit des autres : chacun a ete lance separement dans le worktree.

## 3. Mutation de garde-fous sur materiau reel

Regle appliquee : le garde-fou est mis a l'epreuve en desactivant le correctif qu'il protege, sur
le fichier ou la classe reellement servi(e), jamais une fixture. Restauration par `git checkout --`
apres chaque mutation, `git status --short` verifie vide apres chaque restauration.

### 3.1 ADR-0099, decision 3 - "si et seulement si" sur le rattachement de colonne

C'est le premier des deux garde-fous qu'`ADR-0099` declare avoir corriges le jour meme (amendement
du 2026-09-23), et je le mets a l'epreuve independamment de l'affirmation de `theo-archi`.

Mutation : dans `manche.html`, reellement servi, `<th scope="col" ...>` remplace par
`<td ...>` - c'est exactement la mutation que la Javadoc du garde-fou nomme comme "celle qui compte
le plus".

- **Gabarit mute** : rouge, `Tests run: 8, Failures: 4`
  (`LeTableauSupportDUnItemAtteintLEcranDeMancheEnGrilleTest`). Les quatre echecs couvrent le jeu
  synthetique et le paquet reel `cm1-sciences-extraire-information` sur les deux clauses
  (`chaqueItemPorteurDUneGrilleLaVoitAtteindreLEcranEnTable`,
  `aucunJeuDuCatalogueReelNAfficheDeGrilleSansDeclaration`).
- **Gabarit restaure** : vert, `Tests run: 8, Failures: 0`.

Confirme que la clause "seulement si" corrigee juge reellement le premier paquet a grille du depot
(`S40-EXTRACTION`), et pas seulement un item synthetique.

### 3.2 ADR-0099, amendement - les cellules d'une grille sont des valeurs de contenu

Second garde-fou corrige le meme jour. Avant sa correction, chaque `<th>`/`<td>` d'une grille etait
pris pour un mot invariant de la mecanique.

Mutation : dans `AucunTexteServiNEnchasseLaReponseDansUnePhraseTest.java`, la ligne qui ajoute les
cellules de `tableauSupport` aux valeurs de contenu connues est retiree (remplacee par un
commentaire, sans autre changement).

- **Fixe desactive** : rouge, `Tests run: 3, Failures: 2`. **68 griefs** de la forme *"cet
  emplacement dit autre chose selon l'item joue"* sur les cellules `<td>` du paquet reel
  `cm1-sciences-extraire-information`, confirmant que sans la correction, le garde-fou aurait
  refuse ce paquet conforme pour un motif etranger a `ADR-0083`.
- **Fixe restaure** : vert, `Tests run: 3, Failures: 0`.

Confirme que le fixe n'est pas decoratif : desactive, il reproduit un faux rouge massif sur le
materiau reel, pas seulement sur un cas construit pour l'occasion.

### 3.3 `PAR-5` - allocation des identifiants `HDA-1` a `HDA-4`

Ce garde-fou n'a pas ete corrige lui-meme cette session : c'est le registre qui a ete repare par
`nadia-prof` (les quatre lignes d'allocation ajoutees). Je le mets a l'epreuve en retirant la ligne
`HDA-1` du registre plutot qu'en me fiant a l'affirmation du rapport de `theo-archi`.

Mutation : dans `squad/decisions/REGISTRE-identifiants.md`, la ligne
`| \`HDA-1\` | ... |` retiree.

- **Ligne retiree** : rouge, `Tests run: 1, Failures: 1`. Message exact : *"...cm1-histoire-des-arts-relier-contexte.yaml
  ecrit l'identifiant HDA-1, que squad/decisions/REGISTRE-identifiants.md n'alloue pas [...]
  (PAR-5 ; ADR-0085, clause 2)"* - le garde-fou trouve la citation dans le contenu servi lui-meme,
  pas seulement dans le document d'arbitrage.
- **Ligne restauree** : vert, `Tests run: 1, Failures: 0`.

Confirme que la reparation tient : `leDepotReelEstCoherent` est vert sur le depot reel, et il
redevient rouge si l'allocation est retiree.

## 4. Tests instables

Aucun test instable observe. Chaque lancement cite dans ce rapport n'a ete execute qu'une fois par
etat (mute, puis restaure) ; un test lance une seule fois par etat n'est pas qualifie de stable
pour autant, et ce rapport ne l'affirme pas. Aucun verrou `mvnd` transitoire rencontre sur ce
worktree.

## 5. Ce que ce rapport ne fait pas

Il ne prononce aucun Done pedagogique - `nadia-prof` seule le fait. Il ne juge le niveau scolaire
d'aucun des six lots. Il n'etablit aucun total sur l'arbre fusionne `round-two` au-dela de
`652cafc8` : c'est `sofia-sm` qui l'etablit a l'integration. Il ne modifie aucun contenu ni aucun
code de production : les quatre mutations de la section 3 ont ete restaurees par `git checkout --`
avant la redaction de ce rapport, chaque restauration verifiee par `git status --short` vide.
