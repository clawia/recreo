# Rapport qualite de cloture - s38

**Auteur** : `yanis-qa`. **Date** : 2026-09-22. **Arbre mesure** : `s38-yanis` a `65f03285`,
worktree dedie (`git worktree add ../recreo-s38-yanis -b s38-yanis round-two`), lignee verifiee
(`git rev-parse --abbrev-ref HEAD` -> `s38-yanis`, HEAD egal a `65f03285`), separe de tout autre
worktree partage.

Contenu du sprint : `S38-CORR` (corrections `ADD-CE1-R1`/`ADD-CE1-R2`, `[MARC]` `8f852c85`),
`S38-SOUSTRACTIONS` (`[MARC]` `3b734fd5`), `S38-COMPLEMENTS-DIZAINE` (`[LEA]` `b2746352`),
`S38-MOITIES` (`[LEA]` `179a0c93`), fusionnes par `65f03285`.

---

## 1. Compte de tests autoritaire

**1561 tests : 1555 unitaires + 6 tests d'integration**, sur `s38-yanis` a `65f03285`. Methode :
somme des blocs `[INFO] Results:` apres `clean` sur `bash outils/mvn-jdk21.sh -B clean verify`,
recoupee par la somme independante des attributs `tests="..."` de tous les rapports XML
Surefire/Failsafe du meme lancement. Les deux methodes convergent a 1561 ; la somme des `.txt`
Surefire n'a pas ete utilisee (proscrite depuis l'amendement de s11, cf. rapport s37 section 1).

JDK constate en interrogeant le JDK resolu par `outils/mvn-jdk21.sh` (jamais la toolchain
declaree) : **21.0.10**, coherent avec les lignes de demarrage Spring Boot du meme lancement
(`using Java 21.0.10`). `BUILD SUCCESS`, 6 modules sur 6, 0 echec, 0 erreur.

**Resultats par module** :

| Module | Tests run |
|---|---|
| recreo-domain | 111 |
| recreo-application | 17 |
| recreo-adapter-web | 400 |
| recreo-adapter-content | 762 |
| recreo-bootstrap (surefire, unitaires) | 265 |
| recreo-bootstrap (failsafe, integration) | 6 |
| **Total unitaires** | **1555** |
| **Total integration** | **6** |

`adapter-content` passe de 725 (mesure de reference de `theo-archi`, section 3 de l'avis de
faisabilite, avant les trois lots poses a l'arbitrage) a 762, soit +37 tests portes par
`S38-SOUSTRACTIONS`, `S38-COMPLEMENTS-DIZAINE` et `S38-MOITIES`.

**Couverture par module** (fichiers `*Test.java`/`*IT.java` presents, aucun module vide) :

| Module | Fichiers de test |
|---|---|
| domain | 10 |
| application | 2 |
| adapter-web | 49 |
| adapter-content | 102 (+4 sur s37 : un garde-fou de repartition par forme par lot neuf) |
| bootstrap | 49 |

Ce chiffre est un compte sur mon propre worktree, pas un total sur l'arbre fusionne `round-two`
au-dela de ce qui est deja fusionne dans `65f03285` : l'integration ulterieure reste a `sofia-sm`.

## 2. Les quatre jobs de `.github/workflows/ci.yml`, nommes un par un

| Job | Commande executee | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` (equivalent du `mvn -B verify` du job, sur le JDK que la toolchain resout) | **Vert** - `BUILD SUCCESS`, 0 echec |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` puis `git ls-files ... \| bash .github/scripts/verifier-chemins.sh` | **Vert** - 4 cas de test passent, aucun chemin de poste ni nom de compte trouve |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis `git log --no-merges --format=%s \| bash .github/scripts/verifier-prefixes.sh` sur les 20 derniers commits | **Vert** - tous les cas de test passent, tous les commits portent un prefixe d'agent connu |
| `outils` | balayage des trois motifs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) | **Vert** - 9 juges purs executes, aucun motif vide, 0 echec |

Aucun des quatre n'est deduit des autres : chacun a ete lance separement dans le worktree.

## 3. Mutation de garde-fous sur materiau reel

Regle appliquee : le garde-fou est mis a l'epreuve en mutant le fichier reellement servi, jamais
une fixture. Fichier restaure par `git checkout --` apres chaque mutation, `git status --short`
verifie vide dans les deux cas.

### 3.1 Espace insecable U+00A0 (`AucunSigneDoubleNEstDetachableDeSonMotTest`, TYP-1, `ADR-0067`)

Mutation : dans `ce1-moities.yaml`, un U+00A0 devant un point d'interrogation remplace par une
espace ordinaire (un seul enonce sur les 32 du fichier).

- **Fichier mute** : rouge, `Tests run: 5, Failures: 1`.
- **Fichier restaure** : vert, `Tests run: 5, Failures: 0`.

Confirme que le point que `theo-archi` signalait comme le plus expose (section 12, "un enonce de
moities finissant par un point d'interrogation") est reellement garde, sur le fichier servi et
pas seulement sur un contre-exemple deja commite.

### 3.2 Seuil minimum de rang (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`,
`DETTE_DE_SEUIL_MINIMUM`)

`theo-archi` avait demontre en section 10 de son avis que la clause d'origine (second distracteur
= `d`, toujours superieur a la bonne reponse) rendait tout paquet de complements a la dizaine
rouge par construction (rang "la plus grande" a 0,0 %), avec `DETTE_DE_SEUIL_MINIMUM` deja plein
(3 noms pour un plafond de 3) : aucune exemption possible. `nadia-prof` a revise la formule du
second distracteur le meme jour (`unite(a) - unite(b)`, cf. commentaire de
`ce1-complements-dizaine.yaml`, ligne 4-9), ce qui rend le lot livrable sans toucher a la table de
dette.

Mutation : les 18 items de `ce1-complements-dizaine.yaml` reecrits avec le second distracteur
d'origine (`d`, superieur a la bonne reponse sur tout item).

- **Fichier mute** : rouge, `Tests run: 23, Failures: 2` - exactement les deux echecs que
  `theo-archi` avait predits par le calcul : rang "la plus grande" a 0,0 % (seuil 10,0 %), et une
  strategie a rang constant gagnant 55,6 % des manches (seuil de majorite 50,0 %).
- **Fichier restaure** : vert, `Tests run: 23, Failures: 0`.

`DETTE_DE_SEUIL_MINIMUM` reste a 3 entrees (`cm1-perimetre`, `cp-soustractions`,
`cp-complements-10`) : aucun paquet de s38 n'y figure, et la mutation confirme que c'est parce que
le paquet servi respecte reellement le seuil, pas parce que le garde-fou ne le regarde pas.

## 4. Repartition par forme des trois lots poses a l'arbitrage

Quatre classes de garde-fou neuves, une par lot plus une pour la declaration de sous-partie
(`ADR-0097`) : `ChaquePaquetDeSoustractionsDeCe1TientSaRepartitionParFormeTest`,
`ChaquePaquetDeComplementsALaDizaineDeCe1TientSaRepartitionTest`,
`ChaquePaquetDeMoitiesDeCe1TientSaRepartitionParFormeTest`,
`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`. Chacune porte un jeu fautif commite
(`ADR-0013` obligation 3, verifie par lecture : `leJeuFautifCommiteEstRefuseSurSonSeulPaquetFautif`
dans la classe soustractions) et reference explicitement la clause de story qu'elle encode
(`SOU-CE1-1`/`SOU-CE1-2`, `CDZ-CE1-1`/`CDZ-CE1-2`, `MOI-CE1-1`/`MOI-CE1-2`). Toutes vertes dans le
`BUILD SUCCESS` de la section 1 ; non re-mutees individuellement au-dela de la section 3, faute de
temps sur ce sprint - a traiter en priorite si un doute se manifeste sur un de ces trois lots.

## 5. Tests instables

Aucun test instable observe. Chaque lancement cite dans ce rapport n'a ete execute qu'une fois par
etat du fichier (mute, puis restaure) ; un test lance une seule fois par etat n'est pas qualifie
de stable pour autant, et ce rapport ne l'affirme pas.

## 6. Ce que ce rapport ne fait pas

Il ne prononce aucun Done pedagogique - `nadia-prof` seule le fait, en parallele sur ce meme
sprint. Il ne juge le niveau scolaire d'aucun des quatre lots. Il n'etablit aucun total sur
l'arbre fusionne `round-two` au-dela de `65f03285` : c'est `sofia-sm` qui l'etablit a
l'integration. Il ne modifie aucun contenu ni aucun code de production : les deux mutations de la
section 3 ont ete restaurees par `git checkout --` avant la redaction de ce rapport.
