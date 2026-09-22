# Rapport qualite de cloture - s39

**Auteur** : `yanis-qa`. **Date** : 2026-09-22. **Arbre mesure** : `s39-yanis` a `5e867bc5`,
worktree dedie (`git worktree add ../recreo-s39-yanis -b s39-yanis round-two`), lignee verifiee
(`git rev-parse --abbrev-ref HEAD` -> `s39-yanis`, HEAD egal a `5e867bc5`), separe de tout autre
worktree partage.

Contenu du sprint : `S39-MONNAIE`, `S39-ENERGIE`, `S39-OUTILS` (`[MARC]`, fusion `b9838450`),
`S39-SOLIDES`, `S39-LONGUEUR`, `S39-DEN` (`[LEA]`, fusion `5e867bc5`).

Note sur le verrou `mvnd` transitoire signale par les deux devs : non reproduit ici. Mon propre
`bash outils/mvn-jdk21.sh -B clean verify`, lance une seule fois sur ce worktree, a rendu
`BUILD SUCCESS` du premier coup - je ne recopie pas leur mesure, je constate la mienne.

---

## 1. Compte de tests autoritaire

**1612 tests : 1606 unitaires + 6 tests d'integration**, sur `s39-yanis` a `5e867bc5`. Methode :
somme des blocs `[INFO] Results:` apres `clean` sur `bash outils/mvn-jdk21.sh -B clean verify`,
recoupee par la somme independante des attributs `tests="..."` de tous les rapports XML
Surefire/Failsafe du meme lancement. Les deux methodes convergent a 1612 ; la somme des `.txt`
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
| recreo-adapter-content | 809 |
| recreo-bootstrap (surefire, unitaires) | 269 |
| recreo-bootstrap (failsafe, integration) | 6 |
| **Total unitaires** | **1606** |
| **Total integration** | **6** |

`adapter-content` passe de 762 (mesure de `yanis-qa`, rapport s38) a 809, soit +47 tests portes par
les six lots de s39.

**Couverture par module** (fichiers `*Test.java`/`*IT.java` presents, aucun module vide) :

| Module | Fichiers de test |
|---|---|
| domain | 10 |
| application | 2 |
| adapter-web | 49 |
| adapter-content | 108 (+6 sur s38 : un par lot neuf, dont le tableau d'allocation d'`ADR-0097`/`ADR-0098`) |
| bootstrap | 49 |

Ce chiffre est un compte sur mon propre worktree, pas un total sur l'arbre fusionne `round-two`
au-dela de ce qui est deja fusionne dans `5e867bc5` : l'integration ulterieure reste a `sofia-sm`.

**ArchUnit** : 33 classes de test sous `bootstrap/src/test/java/com/recreo/archi/` executees dans
le meme lancement, aucune en echec (le bloc `Results:` du module `bootstrap` les inclut avec le
reste du surefire de ce module).

## 2. Les quatre jobs de `.github/workflows/ci.yml`, nommes un par un

| Job | Commande executee | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` (equivalent du `mvn -B verify` du job, sur le JDK que la toolchain resout) | **Vert** - `BUILD SUCCESS`, 0 echec |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` puis `git ls-files ... \| bash .github/scripts/verifier-chemins.sh` | **Vert** - 22 cas de test passent (compte des lignes `ok` du garde-fou), aucun chemin de poste ni nom de compte trouve |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis `git log --no-merges --format=%s \| bash .github/scripts/verifier-prefixes.sh` sur les 20 derniers commits | **Vert** - tous les cas de test passent, tous les commits portent un prefixe d'agent connu |
| `outils` | balayage des trois motifs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) | **Vert** - 9 juges purs executes, aucun motif vide, 0 echec |

Aucun des quatre n'est deduit des autres : chacun a ete lance separement dans le worktree.

## 3. Mutation de garde-fous sur materiau reel

Regle appliquee : le garde-fou est mis a l'epreuve en mutant le fichier reellement servi, jamais
une fixture. Fichier restaure par `git checkout --` apres chaque mutation, diff avec une copie de
sauvegarde verifie identique et `git status --short` verifie vide dans les deux cas.

### 3.1 Allocation `ADR-0098` (`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`)

Mutation : dans `ce1-solides.yaml`, reellement servi, `lignesDeReferentiel: ["MAT-CE1-16"]` remplace
par `["MAT-CE1-03"]` - la competence declaree ("Reconnaitre et nommer des solides usuels") reste
allouee sur `MAT-CE1-16`, mais le paquet ne declare plus cette ligne.

- **Fichier mute** : rouge, `Tests run: 14, Failures: 1`. Message exact : *"ce1-solides : la
  competence declaree [...] est allouee sur la ligne MAT-CE1-16, que ce paquet ne declare pas - il
  declare [MAT-CE1-03]. [...] (ADR-0098)"*.
- **Fichier restaure** : vert, `Tests run: 14, Failures: 0`.

Le garde-fou d'allocation d'`ADR-0098` mord reellement sur le contenu servi, pas seulement sur la
mutation en memoire deja portee par
`unPaquetDontLaCompetenceEstAlloueeSurUneLigneQuIlNeDeclarePasFaitEchouerLeGardeFou`.

### 3.2 Anti-cercle-nu sur la boule (`LIllustrationDeContenuResoutEtAucuneNeDortTest`)

Mutation : le SVG reellement servi `solide-boule-a.svg` (cercle + ellipse d'equateur, deux formes
rendues) remplace par un cercle seul (une forme rendue).

- **Fichier mute** : rouge, `Tests run: 15, Failures: 1`. Message exact : *"solide-boule-a
  (ce1-solides.yaml) : 1 forme rendue. Un dessin d'une seule forme EST cette forme (ADR-0077) :
  une boule livree en cercle nu est un cercle [...] (SOL-CE1-1, SOL-CE1-4)"*.
- **Fichier restaure** : vert, `Tests run: 15, Failures: 0`.

Confirme que la derivation d'`ADR-0077` (un dessin d'une seule forme est cette forme) est reellement
gardee sur le dessin servi de production, et pas seulement sur la mutation en memoire deja portee
par `unDessinDeSolideMuteEnCercleNuFaitEchouerLeGardeFou` ni sur le jeu fautif commite.

### 3.3 Cliquet de dette typographique `TYP-1` (`AucunSigneDoubleNEstDetachableDeSonMotTest`)

Mutation : dans `ce1-longueurs.yaml`, un des sept "m U+00A0 ?" reellement servis remplace par un
espace ordinaire (une seule occurrence sur les 94 lignes du fichier).

- **Fichier mute** : rouge, `Tests run: 5, Failures: 1`. Message exact : *"ce1-longueurs porte 1
  espace(s) ordinaire(s) devant un signe double et n'est pas dans la table de dette. Un contenu
  neuf n'herite d'aucune dette [...] (TYP-1, ADR-0067)"*.
- **Fichier restaure** : vert, `Tests run: 5, Failures: 0`.

Confirme qu'aucun des six paquets neufs de s39 n'herite de dette par construction du cliquet : les
six sont absents de `DETTE_PAR_FICHIER`, et le cliquet refuse reellement la premiere occurrence
qu'un paquet neuf y introduirait, plutot que de sortir en silence du perimetre mesure.

## 4. Tests instables

Aucun test instable observe. Chaque lancement cite dans ce rapport n'a ete execute qu'une fois par
etat du fichier (mute, puis restaure) ; un test lance une seule fois par etat n'est pas qualifie
de stable pour autant, et ce rapport ne l'affirme pas. Le verrou `mvnd` transitoire signale par
`marc-dev` et `lea-dev` ne s'est pas reproduit sur ce worktree - `outils/mvn-jdk21.sh` ne partage
pas leur daemon.

## 5. Ce que ce rapport ne fait pas

Il ne prononce aucun Done pedagogique - `nadia-prof` seule le fait, en parallele sur ce meme
sprint. Il ne juge le niveau scolaire d'aucun des six lots. Il n'etablit aucun total sur l'arbre
fusionne `round-two` au-dela de `5e867bc5` : c'est `sofia-sm` qui l'etablit a l'integration. Il ne
modifie aucun contenu ni aucun code de production : les trois mutations de la section 3 ont ete
restaurees par `git checkout --` avant la redaction de ce rapport, et chaque restauration a ete
verifiee par un diff identique a une copie de sauvegarde prise avant mutation.
