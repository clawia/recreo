# [Sprint s57] Rapport de developpeur, palier 3 - `marc-dev`, postes p3-5, p3-6 et p3-7

- **Auteur** : `marc-dev`, instance A du palier 4, poste p4-3 (`LP1`). **Date** : 2026-10-22.
- **Ce que ce rapport est** : le `K7` du palier 3 (`rapport-<developpeur>-palier-3.md`, `retro-palier-3.md` A-5), ecrit pendant le lot `LP1` sur decision de l'utilisatrice (`AP1`, `composition-palier-4.md` section 12, ses mots : *"note pour AP1 qu'on l'ouvre et que le rapport est écris pendant le lot, ne lance rien de plus"*). Il est commite sur la branche `s57-marc-fra-ce1-21`. L'utilisatrice n'a pas dit a quel moment il est du (avant la fusion du lot ou avant la cloture du palier 4) : je ne le tranche pas.
- **Ce que l'auteur n'est pas** : l'instance qui a ecrit les trois lots. Ce rapport decrit des commits existants, lus dans le depot le 2026-10-22 ; ce que cette lecture ne permet pas d'etablir est dit aux sections 2 et 4, et n'est pas reconstruit.
- **Arbre de lecture** : `83035447` (sommet de la branche du lot `LP1`, base `eb267ba7`, qui contient les trois lots du palier 3 et leurs corrections).

## 1. Ce qui a ete livre

| Poste | Story | Paquet | Commit livre | Fusion | `@Test` de la classe au commit livre |
|---|---|---|---|---|---|
| p3-5 | `S57-BR-FRA-CE1-06` | `ce1-inference` | `b4ab00d6` (2026-10-20 08:05) | `59d6f245` | 32, `PaquetCe1InferenceTest` |
| p3-6 | `S57-BR-FRA-CE1-07` | `ce1-texte-documentaire` | `fba84a0d` (08:07) | `a6b1bf1b` | 36, `PaquetCe1TexteDocumentaireTest` |
| p3-7 | `S57-BR-FRA-CE1-11` | `ce1-negation` | `9921ddee` (08:09) | `03acf639` | 31, `PaquetCe1NegationTest` |

Les trois commits ont le meme parent, `b9c6cb69`. Chacun touche `catalogue.yaml`, la classe de test du paquet, ses fixtures `contreexemple/Paquet<Nom>Test/` et `TexteAccentueExposeParLePortEntrantTest.java` (9, 13 et 14 fichiers selon `git diff-tree`, les autres etant des fixtures de jeux fautifs).

**Corrections apres verdict**, toutes de `marc-dev`, un fichier chacune : `954c3b09` (`ce1-inference`, item 19), `5c999686` (`ce1-texte-documentaire`, onze items), `85de7b45` (`ce1-negation`, dix indices), `62b65097` (`ce1-inference`, item 14), `ac986aca` (`ce1-texte-documentaire`, items 5, 9 et 17). Fusions : `27a8a56a`, `78ab8a5b`, `afd33e88`. Leurs motifs sont ceux de `lecture-nadia-palier-3.md` et de `verdict-nadia-palier-3-ce1.md`, que je n'ai pas relus ici.

**Delta de tests** : je ne l'annonce pas. Les trois classes portent 32, 36 et 31 `@Test` ; le delta de chaque lot sur sa base n'a pas ete mesure par cette instance, et le total apres fusion est a `sofia-sm`.

**Ordre des worktrees** : les trois commits sont dates de 08:05, 08:07 et 08:09 sur un meme parent. `RG4` demande un worktree a la fois ; le depot ne dit pas dans quel ordre ils ont ete ouverts. `cloture-palier-3.md` section 10 traite l'ecart, je n'y ajoute rien.

## 2. Jobs de `ci.yml` lances

**Au palier 3, par l'instance qui a ecrit les lots : non etabli.** Aucun journal, aucun rapport et aucun message de commit du depot ne nomme les jobs qu'elle a lances ni leur sortie (`cloture-palier-3.md` section 10 point 9 le dit aussi). Je ne les deduis pas de la fusion.

**Sur l'arbre de cette branche**, qui contient les trois paquets du palier 3, par cette instance, aujourd'hui :

- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; `commit-atteignable.sh eb267ba7` sort 0 ; les 3 sujets de `eb267ba7..HEAD` passent dans `verifier-prefixes.sh`, sortie 0 (*"Tous les commits portent un prefixe d'agent connu."*) ; `verifier-garde-fou-accent.sh "eb267ba7..HEAD"` sort 0 (*"0 commit(s) le touchent dans la plage, tous [THEO]."*). Lance a `83035447`.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh`, sortie 0 (*"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*). Lance a `83035447`.
- **`outils`** : les trois balayages de l'etape, 6 juges `outils/*-test.sh`, 2 juges `squad/outils/*/*-test.sh`, 5 juges `outils/*-test.py`, 13 sortis 0. Lance a `3d3d9382` ; aucun fichier de `outils/` ni de `squad/outils/` n'est touche par les commits du lot.
- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine d'un worktree propre, sans `-pl`, a `83035447`, au jeton de `RF1` : `BUILD SUCCESS`, 04:59 min, sortie 0. Tests : 120 (domain), 31 (application), 523 (adapter-web), 1828 (adapter-content), 381 (bootstrap), plus 6 d'integration, soit 2889, 0 echec. Dans ce build, `PaquetCe1InferenceTest` 32, `PaquetCe1TexteDocumentaireTest` 36 et `PaquetCe1NegationTest` 31 tests, 0 echec. **JDK de mesure** : 21.0.10, `java.version` lu dans les 60 rapports Surefire de `bootstrap`. Etape *"Package du fat jar"* non lancee : `verify` a produit le jar joue.
- La CI distante n'est pas lue.

## 3. `TRV-CE1-5`, une ligne par paquet

Lecture faite aujourd'hui par cette instance, indice par indice, sur les fichiers au sommet de la branche. Elle ne vaut pas lecture de l'epoque.

| Paquet | Indices | Distincts | Plus long | Renvoi a l'oreille | **Ligne** |
|---|---|---|---|---|---|
| `ce1-inference` | 20 | 13 | 11 mots | 0 | **relu** : aucune regle fausse lue ; aucun indice ne nomme une proposition (garde du test du paquet, vert) |
| `ce1-texte-documentaire` | 20 | 19 | 7 mots | 0 | **relu** : les 14 mots cites entre chevrons figurent dans le texte support de leur item (0 absent, script de lecture) ; aucun indice ne nomme une proposition (garde, vert) |
| `ce1-negation` | 24 | 3 | 11 mots | 0 | **relu** : trois indices pour vingt-quatre items, la condition *"pas identique sur tout le paquet"* est tenue a la lettre ; aucune regle fausse lue |

Comptes par un script de lecture Python hors depot, pas par les gardes. **Ce que cette relecture n'a pas fait** : confronter chaque indice a son enonce et a sa bonne reponse pour `ce1-inference` et `ce1-negation` ; l'exactitude des phrases reste a `nadia-prof`, qui a rendu ses verdicts.

## 4. Racine du scratchpad et repertoire parent des worktrees

Au palier 3 : **non declaree**. Aucun fichier du depot ne porte la liste, et cette instance n'etait pas la.

Au moment de cette ecriture (2026-10-22, sur la racine du scratchpad de la session) :

```
find <racine> -maxdepth 1 -mindepth 1 -type f   -> aucune sortie
find <racine> -maxdepth 1 -mindepth 1 -type d   -> s57-p4-lea-dev-b, s57-p4-marc-dev-a, s57-p4-sofia-dev-ap
find <parent des worktrees> -maxdepth 1 -mindepth 1 -type f -newermt '2026-10-22 00:00'   -> aucune sortie
```

**racine : propre** pour ce lot (aucun fichier, trois repertoires de la table du palier 4 dont le mien).

## 5. Ce que ce rapport ne fait pas

Ne prononce aucun Done, ne modifie aucun critere, ne decrit aucun fichier que je n'ai pas lu, et ne reconstruit pas ce que le depot ne dit pas (jobs et racine du palier 3, ordre des worktrees).
