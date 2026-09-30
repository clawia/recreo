# Rapport qualite de cloture - s44

**Auteur** : `yanis-qa`. **Date** : 2026-09-30. **Arbre mesure** : `s44-yanis` a `00c9139b`
(hash complet `00c9139be9bb55397d7f621e3bd2f2ad4f3ee2ff`), worktree dedie
(`git worktree add ../recreo-s44-yanis -b s44-yanis round-two`), lignee verifiee
(`bash outils/verifier-lignee.sh round-two`, lance depuis le worktree lui-meme -> *"le worktree
sur la branche 's44-yanis' descend de la lignee de round-two (ancetre commun trouve dans son
historique)"*).

Contenu du sprint : trois lots de contenu statique, instance unique de `nadia-prof` (`DEC-0099`,
section 9) - `S44-MUS-VOCABULAIRE` (`MUS-CP-11`, `[MARC]`, `5720c07a`/`f0fbebd0`),
`S44-MAT-DIVISION` (`MAT-CE1-18`, `[LEA]`, `04e05532`/`2e712d77`, complete par `[CAM]`
`8053bfc3`), `S44-SCI-OBJET-TECHNIQUE` (`SCI-CM1-13`, `[SOF]`, `b5cd63dd`/`00c9139b`). Deux
fusions sans conflit (`S44-MAT-DIVISION`, `S44-MUS-VOCABULAIRE`) et une fusion **conflictuelle**
(`00c9139b`, fusion de `S44-SCI-OBJET-TECHNIQUE` alors que `S44-MUS-VOCABULAIRE` etait deja sur
`round-two`), sur trois fichiers transverses : `catalogue.yaml`,
`TexteAccentueExposeParLePortEntrantTest.java`,
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java`. Aucun `ADR` ni regle ArchUnit
neuve n'est du sur ce perimetre (`squad/sprints/s44-.../planning.md`, section 10).

`bash outils/mvn-jdk21.sh -B clean verify`, lance une seule fois sur ce worktree, a rendu
`BUILD SUCCESS` du premier coup, en 2 min 54 s. Aucun verrou `mvnd` transitoire rencontre.

---

## 1. Compte de tests autoritaire

**1805 tests : 1799 unitaires + 6 tests d'integration**, sur `s44-yanis` a `00c9139b`. Methode :
somme des six blocs `[INFO] Results:` du meme lancement de `bash outils/mvn-jdk21.sh -B clean
verify`, recoupee par la somme independante des attributs `tests="..."` des 243 rapports XML
Surefire/Failsafe produits par ce meme lancement. Les deux methodes convergent a **1805**.

JDK constate en interrogeant le JDK resolu par `outils/mvn-jdk21.sh` (jamais la toolchain
declaree) : **21.0.10** (Apache Maven 3.9.9), coherent avec les lignes de demarrage Spring Boot
du meme lancement (`using Java 21.0.10`) et avec le diagnostic independant d'`outils/verifier-jdk.sh`
(`java du PATH` mesure a une version **17.0.4.1**, distincte du JDK de compilation resolu a
21.0.10 - les deux verifications convergent sur la meme valeur sans partager la meme source).
`BUILD SUCCESS`, 6 modules sur 6, 0 echec, 0 erreur.

**Resultats par module** :

| Module | Tests run |
|---|---|
| recreo-domain | 120 |
| recreo-application | 17 |
| recreo-adapter-web | 400 |
| recreo-adapter-content | 974 |
| recreo-bootstrap (surefire, unitaires) | 288 |
| recreo-bootstrap (failsafe, integration) | 6 |
| **Total unitaires** | **1799** |
| **Total integration** | **6** |

`domain`, `application`, `adapter-web` et `bootstrap` (unitaires et integration) sont **inchanges**
depuis la cloture de s43 (`9e1622dc` : 120, 17, 400, 288, 6) - coherent avec les trois avis de
`theo-archi` : les trois lots sont du contenu statique, zero ligne de Java de production. Seul
`adapter-content` bouge.

**Delta sur `adapter-content` et sa methode.** Base mesuree directement sur un second worktree
dedie, `5e83a1f8` (dernier commit avant le premier paquet du sprint - fusion des reponses de
`nadia-prof` a Q1/Q3) :
`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am clean test`, **module mesure** :
`adapters/adapter-content` et ses deux dependances amont, pas le depot entier : **944**. Ce
chiffre est exactement celui de la cloture de s43, confirmant l'absence de derive entre les deux
sprints. Worktree supprime apres mesure (`git worktree remove --force`), aucune trace laissee sur
`round-two`. `adapter-content` passe donc de **944** a **974**, soit **+30 tests** - exactement la
somme des `@Test` des trois fichiers neufs (`PaquetCe1DivisionTest` : 7, `PaquetCpMusiqueVocabulaireTest` :
11, `PaquetCm1SciencesEvolutionObjetTechniqueTest` : 12). Aucun test perdu, aucun test deplace en
silence, le delta total (1775 -> 1805, s43 -> s44) correspond a ce meme +30.

**Couverture par module** (fichiers `*Test.java`/`*IT.java` presents, aucun module vide) :

| Module | Fichiers de test (s43 -> s44) |
|---|---|
| domain | 11 -> 11 |
| application | 2 -> 2 |
| adapter-web | 49 -> 49 |
| adapter-content | 128 -> 131 |
| bootstrap | 51 -> 51 |

`adapter-content` passe de 128 a 131, soit exactement **+3 fichiers** - un par paquet neuf,
confirmant qu'aucun paquet neuf n'est reste sans test a son premier commit. `bootstrap` est
inchange : aucun `ADR` ni garde-fou neuf n'est du sur ce sprint, confirme par
`git diff --stat 5e83a1f8..HEAD -- bootstrap/src/main/java bootstrap/src/test/java` qui ne rend
aucune ligne.

**ArchUnit** : **36** `static final ArchRule` et **35** `@ArchTest`, comptes par occurrences
litterales sous `src/test/java` des cinq modules declares au pom agregateur, `target/` exclu,
methode d'`ADR-0095`/`ADR-0098`. Compte **identique a s43** - coherent, aucun des trois lots
n'introduit de regle d'architecture. Aucune classe ArchUnit en echec dans le lancement complet.

## 2. Les quatre jobs de `.github/workflows/ci.yml`, nommes un par un

| Job | Commande executee | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` (equivalent du `mvn -B verify` du job, sur le JDK que la toolchain resout) | **Vert** - `BUILD SUCCESS`, 0 echec |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` (22 cas) puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **Vert** - tous les cas passent, aucun chemin de poste ni nom de compte trouve sur l'ensemble des fichiers suivis |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh` (15 cas), `bash .github/scripts/commit-atteignable-test.sh` (5 cas), puis `git log --no-merges --format=%s ca277753..00c9139b \| bash .github/scripts/verifier-prefixes.sh` | **Vert** - tous les cas passent ; les 7 commits sans fusion du perimetre de s44 portent tous un prefixe d'agent connu (`[THEO]`, `[SOF]`, `[CAM]`, `[NAD]`, `[LEA]`, `[MARC]`) |
| `outils` | balayage des trois motifs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) | **Vert** - 9 juges purs executes, aucun motif vide, 0 echec |

Aucun des quatre n'est deduit des autres : chacun a ete lance separement dans le worktree, et
`git status --short` est reste vide apres chacun.

## 3. Resolution du conflit de fusion `00c9139b` - verification ligne par ligne

Risque concret : une fusion a trois lots sur les memes fichiers d'inventaire peut perdre une
entree, en dupliquer une, ou faire ecraser l'une par l'autre. Verifie sur les trois fichiers
cites dans le message de fusion.

**`catalogue.yaml`** : 106 entrees (`- id: ...`), **aucun identifiant duplique**
(`grep -E "^\s*- id:" ... | sort | uniq -d` rend vide). Les trois entrees du sprint sont toutes
presentes, distinctes et correctement formees :
- `bandit-manchot-division-ce1` -> `paquet: ce1-division`
- `bandit-manchot-vocabulaire-musical-cp` -> `paquet: cp-musique-vocabulaire`
- `bandit-manchot-evolution-objet-technique-cm1` -> `paquet: cm1-sciences-evolution-objet-technique`

Confirme independamment par `CatalogueChargeableIT`, vert dans le lancement complet : un YAML
mal fusionne (cle dupliquee, indentation cassee) aurait fait echouer le chargement, pas seulement
un `grep`.

**`TexteAccentueExposeParLePortEntrantTest.java`** : les trois chaines neuves
(`bandit-manchot-division-ce1`, `bandit-manchot-vocabulaire-musical-cp`,
`bandit-manchot-evolution-objet-technique-cm1`) sont presentes, chacune avec son commentaire de
tracabilite (`S44-MAT-DIVISION`, `S44-MUS-VOCABULAIRE`, `S44-SCI-OBJET-TECHNIQUE`), aucune
n'ecrase une entree de s41/s42/s43 adjacente (lues en contexte, lignes 290-334).

**`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java`** : ce fichier tient une liste
d'**exemption** (paquets dont aucune proposition n'est numerique, donc hors du domaine de la
propriete de rang), pas un inventaire complet des paquets. `cp-musique-vocabulaire` et
`cm1-sciences-evolution-objet-technique` y figurent, chacun avec son motif chiffre et sa date de
mesure (2026-09-30). `ce1-division` en est **absent, et c'est correct** : ses propositions sont
numeriques (des quantites), il n'est donc pas exempte et reste soumis au garde-fou de rang pour
de vrai. Verifie directement sur les vingt et un items du paquet (lecture de `ce1-division.yaml`,
pas seulement confiance dans le vert) : la bonne reponse y occupe tour a tour les trois rangs
possibles parmi les trois propositions (ex. item 1 : rang median ; item 9 : rang maximal ; item
19 : rang minimal) - le garde-fou de rang examine reellement ce paquet, il n'est pas vert par
omission.

Aucune entree perdue, aucun doublon, aucune entree ecrasee : le conflit de fusion `00c9139b` est
resolu correctement sur les trois fichiers qu'il touchait.

## 4. Tracabilite - criteres d'acceptation encodes, lot par lot

Chaque story de `docs/backlog/backlog.md`, section "Sprint 44", est verifiee methode par methode :
le fichier de test du paquet porte-t-il une methode nommee sur le critere, et l'assertionne-t-elle
reellement.

- **`S44-MAT-DIVISION`** (`PaquetCe1DivisionTest`, 7 methodes) : `DIV-CE1-1` a `DIV-CE1-6` toutes
  citees en assertion, y compris la forme amendee du 2026-09-25 de `DIV-CE1-5` (diviseur recopie)
  et de `DIV-CE1-6` (extension a `tableauSupport`, `explication`, `texteSupport`,
  `texteAVoixHaute`). Le quotient est recalcule dans le test a partir des deux nombres lus dans
  l'enonce, jamais recopie du YAML.
- **`S44-MUS-VOCABULAIRE`** (`PaquetCpMusiqueVocabulaireTest`, 11 methodes) : `FER-1` (les quatre
  termes de la parenthese du referentiel en bonne reponse), `FER-3` (deux taches, la moins servie
  a au moins trois items), `MUS-1`/`MUS-2` (aucun texte a voix haute, lexique interdit proscrit
  y compris le verbe "taper").
- **`S44-SCI-OBJET-TECHNIQUE`** (`PaquetCm1SciencesEvolutionObjetTechniqueTest`, 12 methodes) :
  `SCI-1` sur l'**indice** (une seule phrase, jamais "ta reponse", ne nomme pas la bonne reponse)
  et non plus sur l'explication - la correction de `camille-po` demandee en planning section 5
  est bien celle qui est testee. `SCI-4` (deux distracteurs par item), `FER-1` a `FER-3`,
  `EVO-1`/`EVO-2` (arbitrage `nadia-prof` du 2026-09-30) toutes citees.

Aucun critere d'acceptation des trois stories n'est reste sans test.

## 5. Matrice de couverture - etat constate, non modifie

Les trois lignes du perimetre de `DEC-0099` restent `non couvert`, comme attendu avant le Done
pedagogique de `nadia-prof` :

| Ligne | Fichier, position | Etat constate |
|---|---|---|
| `MUS-CP-11` | `squad/programme/MATRICE-couverture.md:1852` | `non couvert` |
| `SCI-CM1-13` | `squad/programme/MATRICE-couverture.md:2136` | `non couvert` |
| `MAT-CE1-18` | `squad/programme/MATRICE-couverture.md:689` | `non couvert` |

Aucune des trois lignes n'a ete modifiee par ce rapport.

## 6. Tests instables

Aucun test instable observe. Le lancement complet cite dans ce rapport n'a ete execute qu'une
fois ; un test lance une seule fois n'est pas qualifie de stable pour autant, et ce rapport ne
l'affirme pas. Aucun verrou `mvnd` transitoire rencontre sur ce worktree.

## 7. Ce que ce rapport ne fait pas

Il ne prononce aucun Done pedagogique - `nadia-prof`, seule, le fait, sur les trois lots. Il ne
juge le niveau scolaire d'aucun des trois lots. Il n'etablit aucun total sur l'arbre fusionne
`round-two` au-dela de ce qui est deja fusionne dans `00c9139b`. Il ne modifie aucun contenu ni
aucun code de production. Aucun `ADR` ni garde-fou neuf n'etant du sur ce sprint (section 10 du
planning), aucune mise a l'epreuve par mutation d'un garde-fou de dette n'est due ici - la seule
verification de fond qui s'imposait, celle du garde-fou de rang sur `ce1-division`, a ete faite
en lisant le materiau reel plutot qu'en se fiant au vert (section 3).

---

**Conclusion** : les quatre jobs de la CI sont verts, mesures individuellement sur ce worktree.
La fusion conflictuelle `00c9139b` est resolue sans perte, doublon ni ecrasement sur les trois
fichiers qu'elle touchait. Le compte de tests (1805, +30 depuis s43) est explique test par test.
**La porte technique de s44 est verte.**
