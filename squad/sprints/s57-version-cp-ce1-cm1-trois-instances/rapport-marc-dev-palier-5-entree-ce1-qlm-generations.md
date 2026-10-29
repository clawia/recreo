# Rapport de marc-dev, palier 5 : entree de catalogue de `ce1-qlm-generations` (`QLM-CE1-12`)

Base : `round-two` a `1d8df272`, branche `s57-p5-marc-12-entree`. Story : `S57-QLM-CE1-ENTREES-CATALOGUE`,
ligne `QLM-CE1-12`, apres la correction de l'accord (`S57-QLM-CE1-12-ACCORD`). Aucun Done n'est
prononce ici : le pedagogique est a `nadia-prof`, le technique est juge par la CI.

## 1. Le diff

Deux fichiers, un commit.

1. `adapters/adapter-content/src/main/resources/catalogue.yaml` : une entree en fin de fichier,
   dans la forme des neuf entrees precedentes.
   - identifiant `bandit-manchot-generations-ce1` (convention `bandit-manchot-<sujet>-ce1`) ;
   - titre `"Les générations et les moments de la vie"`, consigne
     `"Lis bien, puis choisis la bonne réponse."`, `mecanique: bandit-manchot`,
     `paquet: ce1-qlm-generations`, `nombreDeManches: 10`.
2. `bootstrap/src/test/java/com/recreo/bootstrap/catalogue/TexteAccentueExposeParLePortEntrantTest.java` :
   inventaire du port entrant etendu, aucune garde touchee. Ajoutes : 1 identifiant, 1 titre,
   1 competence (libelle du referentiel `QLM-CE1-12`, mot pour mot). La consigne y figurait deja.
   Le commentaire qui disait "la ligne -12 attend le releve" est mis a jour : il serait faux apres
   ce commit.

Titre egal a celui de `lecture-nadia-diff-accord-et-titre-ce1-qlm-generations.md`, section 3 :
compare par script (extraction du titre apres "Titre a retenir, mot pour mot", recherche de
`titre: "<titre>"` dans le catalogue, apostrophes droites) : egalite vraie.

## 2. Preuves d'ascendance

| Piece | Commit | Commande | Code de sortie |
|---|---|---|---|
| lecture de `nadia-prof` (titre et consigne) | `9a1e3406` | `git merge-base --is-ancestor 9a1e3406 HEAD` | 0 |
| releve du `relecteur-pedagogique` | `f1de3616` | `git merge-base --is-ancestor f1de3616 HEAD` | 0 |

Les SHA viennent de `git log --format=%H -n1 -- <fichier>` sur chacun des deux fichiers.

## 3. Mesures

Version : JDK 21.0.10, celle que `outils/mvn-jdk21.sh` rapporte pour la toolchain ; commande
`bash outils/mvn-jdk21.sh -B -pl <module> -am test`. Base de mesure `1d8df272` plus ce seul lot.

| Perimetre | Tests | Echecs | Resultat |
|---|---|---|---|
| `-pl bootstrap -am` : module `bootstrap` (toute la suite) | 395 | 0 | BUILD SUCCESS |
| meme reacteur : `domain` / `application` / `adapter-web` / `adapter-content` | 120 / 31 / 523 / 2569 | 0 | SUCCESS |
| `-pl adapters/adapter-content -am` : module `adapter-content` | 2569 | 0 | BUILD SUCCESS |

Delta de tests : +0. L'entree ajoute une ligne aux inventaires existants, pas de methode.
Les gardes de bootstrap (`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`,
`ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest`, le test des inventaires du port
entrant) et celles de `adapter-content` (tables par paquet) sont dans ces suites, vertes. Aucun
identifiant alloue par ce commit.

Hors mvn : `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash
.github/scripts/verifier-chemins.sh` : sortie 0 (forme CI). Prefixes : voir le commit lui-meme,
`[MARC]`.

## 4. Ce que je n'ai pas lance

- `mvn -B clean verify` complet (le lanceur s'en charge), ni les jobs `prefixes` sur la plage,
  `outils` de la CI.
- Le jar : titre et consigne ne sont pas joues a l'ecran ; `nadia-prof` les joue au geste que
  `sofia-sm` sequence.
- Pas de mise a jour de `MATRICE-couverture.md` ni du backlog (hors de mon perimetre).

## 5. Duree brute

Environ 9 minutes de bout en bout (lecture, edition, deux mesures `-am` de 4 min et 1,5 min).

## Questions

Aucune. Aucune garde n'a rougi.
