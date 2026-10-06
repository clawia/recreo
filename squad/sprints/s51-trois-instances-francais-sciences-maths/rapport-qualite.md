# Rapport qualite de cloture - s51

**Auteur** : `yanis-qa`. **Date** : 2026-10-06. **Arbre mesure** : `round-two` a `ca4c934a` (hash complet
`ca4c934a84bfc9258ed3bac598d7400199d52553`, `git rev-parse HEAD`), arbre fusionne des deux instances, mesure
dans un worktree detache dedie. `git status --porcelain` vide apres le build.

**Perimetre** : les quatre jobs de `.github/workflows/ci.yml`, relu avant la mesure. Le job `build` est
mesure en reacteur complet, sans `-pl`. Ce rapport porte la porte **technique** uniquement. Il ne dit rien
du Done pedagogique, prononce par `nadia-prof` seule dans les `FICHE-<jeu>.md`.

Sur ce poste, deux commandes du workflow sont adaptees, sans effet sur le verdict : `python3` est un
raccourci Windows qui echoue, les juges Python sont lances avec l'interpreteur reel (la boucle du workflow
retombe sur `python` si `python3` est absent) ; `mvn -B verify` est lance par l'enveloppe
`outils/mvn-jdk21.sh -B clean verify` (JDK de la toolchain, `clean` en plus).

## 1. Les quatre jobs

### `prefixes` : vert

- `bash .github/scripts/verifier-prefixes-test.sh` : tous les cas passent, code 0.
- `bash .github/scripts/commit-atteignable-test.sh` : tous les cas passent, code 0.
- `git log --no-merges --format=%s e4d8ba3b..ca4c934a | bash .github/scripts/verifier-prefixes.sh` :
  `Tous les commits portent un prefixe d'agent connu.`, code 0.

Methode : la plage `e4d8ba3b..ca4c934a` est celle de s51 (18 sujets, 0 merge), pas la plage
`github.event.before..GITHUB_SHA` d'un push reel ; la branche `BASE` du workflow (atteignabilite de la
base) n'est donc pas exercee sur un evenement CI, seulement ses deux scripts.

### `chemins` : vert

- `bash .github/scripts/verifier-chemins-test.sh` : tous les cas passent, code 0.
- `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh` :
  `Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.`, code 0.

Perimetre : tous les fichiers suivis du depot, comme le job CI (pas seulement les fichiers de s51).

### `outils` : vert

Boucle du workflow rejouee a l'identique, trois motifs : `outils/*-test.sh` 5 juges,
`squad/outils/*/*-test.sh` 2 juges, `outils/*-test.py` 2 juges (5 et 10 cas `unittest`, OK). **9 juges
purs executes, 0 echec**, aucun motif vide.

### `build` : vert

Commande : `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet. Resultat : `BUILD SUCCESS`, 3 min
55 s, code de sortie 0.

| Module | Resultat | Duree |
|---|---|---|
| recreo (parent) | SUCCESS | 0,5 s |
| recreo-domain | SUCCESS | 10,6 s |
| recreo-application | SUCCESS | 4,1 s |
| recreo-adapter-web | SUCCESS | 43,7 s |
| recreo-adapter-content | SUCCESS | 46,6 s |
| recreo-bootstrap | SUCCESS | 2 min 10 s |

**JDK** : la machine virtuelle des tests rapporte `Started ... using Java 21.0.10` (20 occurrences dans
le journal, une seule valeur) ; l'enveloppe annonce la toolchain `jdk 21` sur `21.0.10`, identique.

Zero echec, zero erreur : relu dans les rapports XML (somme des attributs `failures` et `errors`), pas
seulement sur `BUILD SUCCESS`. Le journal contient dix avertissements `The requested profile "..." could
not be activated` (profils demandes par la configuration Maven du poste, absents du depot) : sans effet.

Le second pas du workflow (`mvn -B -pl bootstrap -am package`, fat jar) n'a pas ete lance : `verify`
l'a deja produit et `JarRecreoIT` l'a exerce (voir ci-dessous), mais le pas lui-meme n'est pas rejoue ici.

## 2. Compte de tests

Methode : somme de l'attribut `tests` des rapports XML de surefire et failsafe sous chaque `target/`,
apres le `clean verify` ci-dessus, sur `ca4c934a`.

| Module | surefire | failsafe |
|---|---|---|
| domain | 120 | - |
| application | 17 | - |
| adapter-web | 401 | - |
| adapter-content | 1237 | - |
| bootstrap | 295 | 6 |

**2076 tests au total (2070 unitaires et ArchUnit, 6 d'integration), 0 echec, 0 erreur.**

## 3. Ecart de base : 2004 (`f68458de`) contre 2006 (`e4d8ba3b`)

Les +2 sont deux tests de `bootstrap`, ajoutes par `3ade55cb` (`[THEO]`, ADR-0104), seuls fichiers Java
touches entre les deux commits (`git diff --stat f68458de e4d8ba3b -- '*.java'` : 3 fichiers, 92 lignes,
aucun YAML) :

- `DecouplageMecaniqueContenuTest` : une regle `@ArchTest` de plus (la clause 13). Rapport : 13 tests.
- `LesReglesMordentTest` : un `@Test` de plus, `uneMecaniqueAPropositionsQuiLitUnePropositionEstRefusee`
  (36 `@Test` a `f68458de`, 37 a `e4d8ba3b` ; rapport : 37 tests), avec le contre-exemple
  `MecaniqueQuiLitSesPropositionsContreExemple`.

La base de 2004 est la mesure de s50 a `1db6348f` (rapport de s50, partie A) ; bootstrap y comptait 293
surefire, il en compte 295 ici. Le compte `2006` de `lea-dev` est donc coherent : il s'agit de `2004 + 2`.
Je n'ai pas relance de build a `f68458de` ni a `e4d8ba3b` : l'explication repose sur le diff et sur les
comptes de `@Test`, pas sur deux mesures.

**Delta de s51 sur `e4d8ba3b` (2006) : +70, tout dans `adapter-content`** (1167 a 1237 ; les trois autres
modules inchanges, bootstrap +0). Methode : `git diff --name-status e4d8ba3b ca4c934a -- '*.java'` donne six
classes de test neuves, mesurees dans les rapports :

| Classe neuve | Tests |
|---|---|
| `PaquetCe1AlphabetiqueTest` | 11 |
| `PaquetCe1GraphieTest` | 12 |
| `PaquetCe1TempsTest` | 10 |
| `PaquetCm1SciencesActiviteHumaineTest` | 12 |
| `PaquetCm1SciencesCorpsTest` | 13 |
| `PaquetCm1SciencesTerreTest` | 10 |

Soit 68. Les 2 restants viennent de classes existantes modifiees (`AucunMotServiNeSEcarteDeSonAccentTest`,
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, `TexteAccentueExposeParLePortEntrantTest` : deux
`@Test` ajoutes dans le diff). Cette ventilation des 2 n'est pas mesuree classe par classe contre une base :
je ne l'ai pas decomposee au-dela du diff.
Delta total sur la base 2004 : +72 (2 + 70), arbre a `ca4c934a`. `sofia-sm` etablit le total sur l'arbre
fusionne final.

## 4. Couvert, non couvert

- Les six classes `Paquet*Test` neuves couvrent les paquets livres (`FRA-CE1-20`, `-22`, `-23`,
  `SCI-CM1-10`, `-15`, `-18`) ; les contre-exemples sous `src/test/resources/contreexemple/` existent pour
  le temps, la graphie (deux) et l'ordre alphabetique, et pour la cedille d'`ADR-0065` amendement A1.
- Non verifie par ce rapport : la tracabilite critere d'acceptation vers test, story par story, et la mise
  a l'epreuve (correctif retire, rouge constate) des garde-fous cites comme fermant un refus de s51. Je ne
  les ai pas executes ici, je ne les declare donc pas eprouves.
- Tests instables : aucun constate sur ce lancement unique. Un seul passage ne prouve pas l'absence
  d'instabilite.
- Chaque module a des tests (domain 120, application 17, adapter-web 401, adapter-content 1237, bootstrap
  301).

## 5. Verdict de la porte technique

`prefixes` vert, `chemins` vert, `outils` vert, `build` vert, les quatre lances par moi sur `ca4c934a`.
Reserve : le pas `package` du job `build` n'est pas rejoue. Ce verdict est celui de ma mesure locale ;
le juge reste la CI sur le push.
