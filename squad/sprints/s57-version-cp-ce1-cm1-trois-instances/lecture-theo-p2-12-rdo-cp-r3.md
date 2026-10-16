# [Sprint s57] Palier 2 - lecture du diff de p2-12 (`S57-RDO-CP-R3`, voie (iii)), condition de fusion `Q-T15`

- **Date** : 2026-10-16 19:13 (UTC+02:00), lu par `date`.
- **Auteur** : `theo-archi`. Branche `s57-theo-p2-12`, worktree dedie, base `0ea24709`, `git status --short` vide avant ecriture.
- **Objet** : `Q-T15` (`planning.md`) : la fusion de p2-12 exige une trace de lecture qui nomme le commit lu, avec un diff de code vide entre ce commit et la tete de branche.
- **Ce que ce fichier ne fait pas** : il ne juge ni la cote de la phrase a l'ecran (`ines-design`) ni ce que l'enfant lit (`nadia-prof`), ne prononce aucun Done, ne fusionne rien, ne touche aucun fichier de `lea-dev`.

## 1. Commit lu

| Element | Valeur | Commande |
|---|---|---|
| Branche | `s57-lea-rdo-cp-r3` | `git log --oneline -3` dans son worktree |
| Base | `0ea24709` | `git merge-base --is-ancestor 0ea24709 23222465` : sortie 0 ; `git rev-parse 23222465^` : `0ea24709f62c...` |
| **Commit lu** | **`23222465`** (gabarit et quatre tests) | `git diff 0ea24709 23222465`, lu en entier |
| Tete de branche | `5a1d76fa` (rapport de `lea-dev`) | `git rev-parse HEAD` : `5a1d76faf2181ad713e65647a7e3cbcf8243a459` |
| Delta entre le commit lu et la tete | **vide sur le code** | ci-dessous |

```
$ git diff --stat 23222465 5a1d76fa -- adapters domain application bootstrap pom.xml outils .github
(aucune ligne)
$ git diff --stat 23222465 5a1d76fa
 .../rapport-lea-dev-palier-2-rdo-cp-r3.md          | 76 ++++++++++++++++++++++
 1 file changed, 76 insertions(+)
```

**Si la branche recoit un commit de plus avant la fusion**, la premiere commande se relance entre `23222465` et la nouvelle tete ; une sortie non vide demande une relecture de ma main.

## 2. Ce que j'ai compare

1. **Le diff entier** de `23222465` : `manche.html` (+1, -1), le garde de D.4 (+13, -13), `PartieControllerPropositionsEnImageTest` (+1, -1), `TexteAccentueDesGabaritsTest` (+1, -1), `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest` (+1, -1).
2. **Le texte du gabarit et celui de `nadia-prof`.** Ligne 122 de `reponse-nadia-pa7-pa8.md` (balises Markdown retirees) et noeud de texte de `.reponse-revelee` a `23222465` (`&nbsp;` ramene a une espace) : **egaux octet pour octet**, `œ` en `c5 93`. Script hors depot.
3. **Les commandes `RD3-1` a `RD3-13` de la story**, rejouees par moi sur `23222465` : 1, 0, 0, 0, 3, 5, 0, 0, 2, 0, 9, 6, 2, soit **les treize attendus**. `grep -c 'assertThat('` sur les trois autres tests : 9, 22, 23 a `0ea24709` comme a `23222465`. Precondition de forme : `git log --oneline 78270b4c..23222465 -- <gabarit> | wc -l` rend 1 ; entre `78270b4c` (base ecrite par la story) et `0ea24709` (base reelle) le gabarit n'a recu aucun commit (0).
4. **Le releve du `relecteur-pedagogique`** (`releve-relecteur-palier-2-corrections.md`, `14ca643f`, branche `s57-relecteur-p2b`, section 3.3) dit les constantes des quatre tests correctes. Mes lectures 2 et 3 le confirment par une mesure independante.

## 3. Conformite a `ADR-0067`, decision 1 et amendement du 2026-10-15 (D.4)

| Code | Point | Constat | Methode |
|---|---|---|---|
| `RL1` | Le caractere vit la ou le texte est ecrit | Le gabarit ecrit lui-meme les caracteres du noeud ; aucun `th:text`, filtre ou regle de style n'ajoute U+00A0. **Tenu** | lecture de la ligne 214 |
| `RL2` | Notation : `&nbsp;`, jamais le caractere tape | `RD3-3` : 0 U+00A0 tape dans le gabarit ; quatre `&nbsp;` dans la ligne (trois, puis un). **Tenu** | `RD3-3` ; lecture |
| `RL3` | U+00A0 entre les mots d'une phrase, une seule espace ordinaire entre les deux | Rendu par le controleur et le gabarit reels, le garde est vert (`M0`, section 4). **Tenu** | `M0` |
| `RL4` | Le garde de D.4 reste la regle executable de l'amendement | Meme structure, memes deux `@Test`, memes neuf `assertThat` et six morsures (`RD3-11` a `RD3-13`) ; seuls les textes et un compte litteral changent. Aucun amendement d'`ADR` n'est du : D.4 dit la forme, pas les mots (`avis-theo-pa7-pa8.md` 2.4). **Tenu** | diff lu |

### 3.1 Le compte de U+00A0 : 5 devient 3, est-ce la bonne regle ?

**Oui.** La forme de D.4 donne, pour une phrase de `n` mots, `n - 1` U+00A0. *"Voici la bonne œuvre."* a quatre mots, donc trois ; *"Appuie dessus."* deux, donc une. L'ancienne phrase en avait six, d'ou cinq. Avec `!= 5` sur le texte neuf, le garde rougit sur le texte juste : c'est `M3` (section 4), mesure.

**Ce que ce compte garde reellement, et ce qu'il ne garde pas.** Quand les trois conditions qui le precedent sont tenues (texte egal a la phrase une fois U+00A0 lue comme espace ; toute espace ordinaire suit `.`, `!` ou `?` ; la premiere phrase est suivie d'une espace ordinaire), chaque intervalle entre deux mots d'une meme phrase est forcement U+00A0 : le compte vaut alors `n - 1` par construction. **Le compte ne detecte donc aucun defaut du gabarit que les autres conditions laissent passer** : `M1` le montre, ou il rougit en meme temps que la condition de l'espace ordinaire. Son seul effet propre est de lier le texte des constantes au litteral : un changement de phrase qui oublie le compte rougit (`M3`), visible et non muet, comme mon avis 2.4 l'attendait. Ce n'est pas un ecart, il etait deja redondant sur l'ancienne phrase. **Ce qui me ferait changer d'avis** : un troisieme changement de cette phrase ; le compte se deriverait alors de `PREMIERE_PHRASE` et `SECONDE_PHRASE` au lieu d'etre recopie.

### 3.2 La morsure de `lea-dev` : *"la phrase de M4 ecrite sans aucune U+00A0"*

**Elle mord.** Le second test du garde est vert a `M0`, donc `defauts("Voici la bonne œuvre. Appuie dessus.")` est non vide : le texte est egal a la phrase, et les quatre espaces ordinaires (positions 5, 8, 14 et 28) ne suivent aucune fin de phrase ; les deux comptes rendent 0 au lieu de 3 et 1. Elle remplace la morsure *"la phrase telle que le gabarit l'ecrivait avant l'amendement"*, qui etait le meme defaut ecrit sur l'ancienne phrase : c'est la meme categorie transposee, et c'est la forme la plus probable d'une regression reelle (la phrase retapee au clavier). **Ce qu'elle ne fait pas** : elle juge la fonction `defauts()` sur une entree fabriquee, pas le gabarit rendu. La morsure sur le gabarit est celle du premier test, rejouee par `lea-dev` (*Voici la*) et par moi (`M1`, `M2`). Le choix de redaction qu'elle soumet a relecture (rapport, section 7) est **accepte**.

## 4. Morsures, de ma main, sur materiau reel

Copie de l'arbre de `23222465` (`git archive`) sous mon repertoire de scratchpad, hors de tout worktree. Fichier mute par un script qui exige une occurrence exacte du motif, puis restaure par copie de reference (`cmp` : identique, apres chaque mutation). Commande : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test -Dtest=LaPhraseDeRevelationDUneMancheEnImageNeSeCoupeJamaisDansUnePhraseTest -Dsurefire.failIfNoSpecifiedTests=false`, perimetre `adapter-web` et ses amonts, une seule classe lancee. JDK **21.0.10** (`java.version` des rapports Surefire d'`adapter-web`, une seule valeur).

| Code | Mutation | Tests | Sortie |
|---|---|---|---|
| `M0` | aucune | 2 | `BUILD SUCCESS`, 0 echec |
| `M1` | gabarit : `bonne&nbsp;œuvre` devient `bonne œuvre` (une espace ordinaire dans la premiere phrase) | 2 | **1 echec** (`laPhraseDeRevelationRendueNeSeCoupeNiDansUnePhraseNiEntreLesDeux`) : *"espace ordinaire qui ne suit pas une fin de phrase, en position 14"* et *"la premiere phrase porte 2 U+00A0, trois attendues"* |
| `M2` | gabarit : `la&nbsp;bonne` devient `labonne` (un U+00A0 retire) | 2 | **1 echec**, meme test : *"le texte lu vaut "Voici~labonne~œuvre. Appuie~dessus.", la phrase de M4 est ..."* |
| `M3` | garde : `!= 3` redevient `!= 5`, gabarit juste | 2 | **2 echecs** : *"la premiere phrase porte 3 U+00A0, trois attendues"* (le message n'a pas ete mute, seule la condition) |

`M1` et `M2` sont les deux morsures demandees : elles rougissent par le controleur et le gabarit reels. `M2` rougit par la condition d'egalite du texte, pas par le compte. `M3` prouve section 3.1 : l'ancien compte rougit sur le texte neuf.

## 5. Perimetre : aucune feuille de style, aucun autre ecran

```
$ git diff --stat 0ea24709 23222465
 .../main/resources/templates/fragments/manche.html |  2 +-
 ...cheEnImageNeSeCoupeJamaisDansUnePhraseTest.java | 26 +++++++++++-----------
 .../PartieControllerPropositionsEnImageTest.java   |  2 +-
 .../adapter/web/TexteAccentueDesGabaritsTest.java  |  2 +-
 ...teignentLEcranSansJamaisSonIdentifiantTest.java |  2 +-
 5 files changed, 17 insertions(+), 17 deletions(-)
```

`git diff --name-only 0ea24709 5a1d76fa | grep -c '\.css$'` : 0. `git diff --name-only 0ea24709 5a1d76fa -- '*.html'` : `fragments/manche.html` seul. `RD3-9` = 2 et `RD3-10` = 0 : dans ce gabarit, seule la ligne de la revelation a propositions en image change. Aucun fichier de `domain`, `application`, `adapter-content`, aucun paquet, aucune `ADR`. Critere 7 tenu.

## 6. Garde-fou de l'accent, intact

- **Garde-fou d'`ADR-0065`** (`AucunMotServiNeSEcarteDeSonAccentTest`, `LexiqueFrancais`, son contre-exemple, le pom d'`adapter-content`, `ADR-0065`) : `bash .github/scripts/verifier-garde-fou-accent.sh 0ea24709..5a1d76fa` rend *"0 commit(s) le touchent dans la plage"*, sortie 0. Aucun de ces chemins dans `git diff --stat 0ea24709 5a1d76fa`.
- **`TexteAccentueDesGabaritsTest`** (inventaire d'`ADR-0039`) : une entree remplacee par le texte neuf, aucune autre ligne ; 9 `assertThat` avant et apres. Le test qui exige que chaque entree soit encore ecrite par un gabarit et celui qui exige que tout texte ecrit soit inventorie tiennent l'inventaire et le gabarit ensemble : une entree perimee ou un texte non inventorie rougit. Le diff ne retire ni n'affaiblit aucun des deux.

## 7. Regles ArchUnit

**Inchangees.** Methode d'`ADR-0104` : `git grep -hcE` des motifs `^\s*(public |private |protected )?static final ArchRule ` et `^\s*@ArchTest\s*$` sur les `*.java` suivis du commit nomme, sommes par `awk`, fichiers par `git grep -l`.

| Commit | `static final ArchRule` | `@ArchTest` | Fichiers |
|---|---|---|---|
| `0ea24709` | 41 | 38 | 16 |
| `23222465` | 41 | 38 | 16 |
| `5a1d76fa` | 41 | 38 | 16 |

## 8. Commentaires devenus inexacts : dette, routee

`lea-dev` en nomme deux ; **il y en a trois** qui citent une source ne portant plus ce texte (`git grep -n -E "M4|copie tel|mot pour mot"` sur les cinq fichiers a `23222465`) :

| Code | Fichier, ligne | Ce qui est faux |
|---|---|---|
| `RL5` | `manche.html` 207 a 213 | *"cette phrase, de nadia-prof (M4)"* : la phrase est de `nadia-prof`, mais sa source est `reponse-nadia-pa7-pa8.md` 2.4 (`NP4`), pas `M4` de s55 |
| `RL6` | `TexteAccentueDesGabaritsTest` 158 a 160 | *"M4 de ARBITRAGE-PREALABLE-s55... section 3.2 ..., copie tel quel"* : cet arbitrage porte l'ancienne phrase |
| `RL7` | `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest` 107 | *"M4 du meme arbitrage, mot pour mot"*, l'arbitrage de la ligne 104 etant `ARBITRAGE-PREALABLE-s55`. Non nomme par `lea-dev` |

Le nom `PHRASE_DE_M4` et les messages *"la phrase de M4"* du garde restent lisibles comme le nom de la case `M4` (le juge de pli parle aussi du *"bas de m4"*) ; je ne les compte pas comme faux.

**Dette, pas ecart avant fusion.** Motifs : (1) aucun garde ni aucun comportement n'est touche, les trois sont des commentaires ; (2) corriger `RL5` avant la fusion ferait passer `RD3-9` de 2 a plus de 2, un critere ecrit par `camille-po` que je n'amende pas ; (3) un commit de plus sur la branche rouvrirait `Q-T15`. **Le cout de ne pas la payer** est reel : une citation d'arbitrage fausse dans l'inventaire d'`ADR-0039` invite le prochain lecteur a "retablir" la phrase de s55. **Routee a `sofia-sm`** pour affectation, avec ma proposition : un commit de commentaires seul, sur les trois fichiers, citant `reponse-nadia-pa7-pa8.md` 2.4, apres la fusion et avant la cloture du palier 2. Ce commit touche le gabarit : `RD3-9` se lit alors sur la fusion `<F>` par la forme que la story ecrit deja (*"Forme valable apres fusion"*), pas sur `round-two`.

## 9. Verdict de fusion

**Fusionnable sur `23222465`, tete `5a1d76fa`.** Le texte est celui de `nadia-prof` octet pour octet ; la forme de D.4 est tenue et son garde mord par le gabarit reel (`M1`, `M2`) ; le compte 3 est le bon et l'ancien rougirait (`M3`) ; la morsure neuve de `lea-dev` mord ; aucune feuille, aucun autre ecran ; garde-fou de l'accent intact ; ArchUnit inchange ; le delta entre le commit lu et la tete ne touche aucun code. `RL5` a `RL7` sont une dette routee (section 8), pas une condition.

Ce verdict ne dit rien du critere 1 (36 mesures a l'ecran) ni du critere 3 : ils sont a `ines-design` et a `nadia-prof`.

## 10. Jobs de `ci.yml` lances pour ce commit

Lances sur ce worktree, ce fichier indexe, avant le commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`, arbre `0ea24709` plus ce fichier : `BUILD SUCCESS`, exit 0, 5 min 15. **2669 tests, 0 echec** (120, 31, 501, 1630, 381 et 6, lignes `Tests run` de la sortie Maven). JDK **21.0.10** (`java.version` des 60 rapports Surefire de `bootstrap`, une seule valeur). Ce build mesure mon commit, pas `23222465` ni la fusion. Le build a lu ce fichier avant l'ecriture de cette section, qui ne contient aucun tableau. Etape *"Package du fat jar"* non lancee.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` : sort 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; le sujet de ce commit passe dans `verifier-prefixes.sh` : sort 0. Aucun chemin du garde-fou de l'accent n'est touche.
- **`outils`** : trois balayages (6, 2 et 5 fichiers), 13 juges lances avec `python` ; **13 sortent 0**, `outils/mvn-jdk21-test.sh` compris (`TMPDIR` a sa valeur par defaut).

Limite : jobs lances sur mon poste, pas sur `ubuntu-latest`.
