# Rapport de marc-dev - s55, postes S54-EXC-CATEGORIE (EXC-R15) et S54-ASCII-APOSTROPHES-TESTS

Branche `s55-marc-exc`, issue de `round-two` au commit de base `fa4ddc7e`. `bash outils/verifier-lignee.sh` : la branche descend de la lignee de `round-two`. Rien n'est pousse, rebase ni fusionne.

## 1. Livre

| Poste | Commit | Fichiers |
|---|---|---|
| `S54-EXC-CATEGORIE` (`EXC-R15`) | `d2b317a8` | `cp-emc-exclusion.yaml` (3 lignes), `catalogue.yaml` (1), `PaquetCpEmcExclusionTest.java`, `TexteAccentueExposeParLePortEntrantTest.java` (1) : 4 fichiers |
| `S54-ASCII-APOSTROPHES-TESTS` | `51c81763` | `PaquetCm1EmcAtteinteAutruiTest.java` ligne 57, `PaquetCm1SciencesActiviteHumaineTest.java` ligne 65 : 2 fichiers, 1 ligne chacun |

Les quatre lignes de `nadia-prof` (fiche d'`EXC-R15`, bloc du 2026-10-08, section 4) sont posees mot pour mot. Aucune ne contient de `?`, de `:` ni de guillemets francais : la regle TYP-1 (espace insecable) ne s'applique a aucune des chaines livrees. Aucun mot lu par l'enfant n'est de moi.

## 2. S54-EXC-CATEGORIE : mesures avant/apres

Commandes de `ARBITRAGE-QC4-s55.md` section 6 (P = paquet, C = catalogue, T = `PaquetCpEmcExclusionTest`, I = inventaire du port entrant). Avant mesure sur `fa4ddc7e`, apres sur `d2b317a8`.

| Critere | Avant | Apres | Attendu |
|---|---|---|---|
| `bonneReponse: "une moquerie qui se répète"` sur P | 3 | 1 | 1 |
| `bonneReponse: "une mise à l'écart qui se répète"` sur P | 0 | 2 | 2 |
| `une mise à l'écart qui se répète` sur P | 0 | 3 | 3 |
| `dis si c'est une moquerie qui se répète` sur C | 1 | 0 | 0 |
| nouvelle consigne sur C | 0 | 1 | 1 |
| `dis si c'est une moquerie qui se répète` sur I | 1 | 0 | 0 |
| nouvelle consigne sur I | 0 | 1 | 1 |
| titre `"Une moquerie qui se répète",` sur I | 1 | 1 | 1 |
| `une mise à l'écart qui se répète` sur T | 0 | 1 | au moins 1 |
| `@Test` sur T | 9 | 9 | au moins 9 |

Forme du test (QC4 section 5) : `CLASSEMENTS_VALIDES` contient les trois noms ; `fab1a...` compte le cote "qui se répète" comme la somme des deux noms (3 contre 3 avec `un accident isolé`) et exige la mise a l'ecart servie au moins une fois. Trois constantes nommees remplacent les litteraux de ces deux usages. Aucune assertion retiree.

### Morsures (T modifie, mutation seule, puis restauration ; perimetre : `PaquetCpEmcExclusionTest` via `-pl adapters/adapter-content -am`)

| Mutation | Resultat |
|---|---|
| M1 : items 1 et 3 remis a `une moquerie qui se répète` | 9 tests, 1 echec : `fab1a...` (rouge) |
| M2 : bonne reponse de l'item 10 (tache B) = `une mise à l'écart qui se répète` | 9 tests, 2 echecs : `aucuneBonneReponseDeLaTacheBNEstUneCategorieDeClassement` et `exc11...` (rouge, alors qu'elle etait verte avant) |
| M3 : categorie neuve retiree de `CLASSEMENTS_VALIDES`, paquet corrige | 9 tests, 0 echec (vert) |

M3 est vert et c'est attendu : la table ne sert qu'a la clause de la tache B, et sur le paquet corrige aucun item de tache B ne porte le nom neuf. La table n'est mordue que par M2 (paquet mute). Les morsures M1 et M2 de QC4 section 6 sont donc tenues. Elles sont a rejouer par `yanis-qa` sur l'arbre fusionne.

## 3. S54-ASCII-APOSTROPHES-TESTS

`grep -cP '[\x{2018}\x{2019}]'` (avec `LC_ALL=C.UTF-8`) : 1 et 1 avant, **0 et 0 apres**. Forme : `['\\u2019]` dans la chaine Java, comme `BLANC` (precedent cite par `theo-archi`, T4). `PaquetCm1EmcAtteinteAutruiTest` 10 tests verts, `PaquetCm1SciencesActiviteHumaineTest` 12 tests verts, dans le `clean verify` complet.

## 4. Version, delta et jobs

- Version : JDK 21.0.10, rapportee par `outils/mvn-jdk21.sh` ("Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10"). `bash outils/mvn-jdk21.sh -B clean verify` (reacteur complet, sans `-pl`) : BUILD SUCCESS avant chaque commit.
- Delta de tests, mesure sur `fa4ddc7e` plus mes seuls postes : **+0 test** (9 `@Test` avant et apres dans `PaquetCpEmcExclusionTest`, une assertion de plus dans `fab1a...`, aucun test ajoute dans `bootstrap`). Total apres fusion : a etablir par `sofia-sm`.
- Jobs de `.github/workflows/ci.yml` lances : **build** (`clean verify`, deux fois) ; **chemins** (`verifier-chemins.sh` sur `git ls-files`, vert ; `verifier-chemins-test.sh` non relance) ; **prefixes** (`verifier-prefixes.sh` sur les sujets de mes deux commits, vert) ; **outils** (balayage local de `outils/*-test.sh`, `squad/outils/*/*-test.sh` et `outils/rapport-tokens-horodatage-test.py` : tous a 0).
- Jobs non lances : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `commit-atteignable.sh` sur une plage de PR, `verifier-chemins-test.sh`. Le workflow lui-meme n'a pas tourne sur GitHub.

## 5. Ecarts

- **E1. Critere du planning insatisfiable, non contourne.** Le planning (amendement section D) demande `grep -c "mise . l.cart" PaquetCpEmcExclusionTest.java` au moins 1. Sur l'arbre final la commande rend **0** (deux caracteres entre `l` et `cart`, le motif n'en admet qu'un : defaut deja note par `yanis-qa`, `lecture-testabilite.md` section 2.3). J'ai mesure a la place le critere de `ARBITRAGE-QC4-s55.md` section 6, qui rend 1. Le critere du planning n'est pas reecrit par moi.
- **E2. "`git diff` du paquet : trois lignes, du catalogue : une"** tient (3 et 1). Le diff total est de 4 fichiers, comme QC4 le dit.
- **E3.** Fins de ligne : git avertit "LF will be replaced by CRLF" sur plusieurs fichiers de test a l'ajout (reglage du poste). Le diff de chaque commit ne montre que les lignes voulues.

## 6. Non verifie

- Rejeu a l'ecran des items 1, 3 et 4 : la consigne neuve est affichee (jar lance sur le JDK 21, `POST /parties/bandit-manchot-exclusion-cp`, la page contient `Lis bien, puis dis ce qui se passe, ou à quel adulte en parler.` et plus le mot moquerie). Sur la manche tiree, aucun des items 1, 3 ou 4 n'est sorti : le nom `mise à l'écart` n'a pas ete vu a l'ecran. Le titre du jeu (`Une moquerie qui se répète`) reste affiche, hors perimetre. C'est le rejeu de `nadia-prof`.
- Que `une mise à l'écart qui se répète` soit le bon nom : garde de `nadia-prof` (QC4 section 7), aucun garde-fou.
- Les morsures sont rejouees par moi, pas encore par `yanis-qa`.
- Aucun Done prononce, ni pedagogique ni technique.
