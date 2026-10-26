# s57 - palier 5 : avis de theo-archi sur la garde de rotation de S57-BR-QLM-CE1-01 (DVP-CE1-2, tache ordre)

- **Auteur** : `theo-archi`. Date : 2026-10-26 11:10 (UTC+01:00), lue par `date`.
- **Arbre** : worktree `s57-p5-theo-rotation`, base `round-two` a `99383ac4` (`git rev-parse`). Le paquet lu est le premier jet de `marc-dev` (`7c922786`), inchange a cette base.
- **Demande** : `lecture-nadia-premier-jet-ce1-qlm-etapes-de-developpement.md`, section 3 (sondes) et section 5, points 3 et 4 (corrections des items 12 et 15).
- **Lu** : `PaquetCe1QlmEtapesDeDeveloppementTest.java` (en entier) ; `ce1-qlm-etapes-de-developpement.yaml` (en entier) ; `etapes-fautives.yaml` (en-tete, sondes 09 et 10) ; les stories `S57-BR-QLM-CE1-01` a `-12` (`docs/backlog/backlog.md:24040-24215`) ; mon avis `avis-theo-palier-5-premier-jet-ce1-qlm-01.md`.
- **Corrections de `marc-dev`** : sa branche `s57-p5-marc-01-corr` est a `99383ac4`, sans commit propre et sans modification non commitee (`git log round-two..s57-p5-marc-01-corr` vide, `git status --short` vide dans son worktree). Je mesure donc les corrections **telles que `nadia-prof` les propose** (section 5, points 3 et 4), les deux options de chaque item. Ce n'est pas le fichier de `marc-dev`.
- **Ce que ce fichier ne fait pas** : il n'ecrit ni test, ni jeu fautif, ni contenu, et ne prononce aucun Done. Aucun `mvn verify` lance.

## 0. Verdict

| Code | Question | Reponse |
|---|---|---|
| `V1` | Definition | Un distracteur est refuse s'il est **une rotation non triviale de la bonne suite** : la bonne suite decalee de 1 a n-1 places, l'ordre circulaire conserve |
| `V2` | Condition de lecture | Lue sur **tout item de la tache *ordre***, sans condition d'espece : les quatre especes de la liste fermee sont des cycles (`DVP-CE1-1`) |
| `V3` | Lieu | **Le test du paquet de `-01`**, dans la branche *ordre* de `verifierLesReponsesEtLesDistracteurs`. Pas le test transverse |
| `V4` | Sondes | Les trois sondes de `nadia-prof` sont appliquees, plus une quatrieme tiree de sa section 3. Chaque mutant de la garde est mordu par au moins une sonde (section 3) |
| `V5` | Paquet reel | **Items 12 et 15 refuses**, les deux distracteurs que `nadia-prof` refuse. Apres ses corrections (l'une ou l'autre option) : **aucun refus** |
| `V6` | Ordre des commits | La garde et les corrections des items 12 et 15 entrent **dans le meme commit**, sinon `dvp1EtDvp2...` rougit sur `round-two` |

## 1. La definition, et pourquoi elle suffit

**Definition.** Soit `bonne` la suite de la bonne reponse, deja verifiee en ordre croissant sur la liste fermee (`PaquetCe1QlmEtapesDeDeveloppementTest.java:283-286`). Un distracteur `autre` de la tache *ordre* est refuse si `autre` est egal a `bonne` decalee circulairement de `k` places, pour `k` de 1 a `bonne.size() - 1`. Le cas `k = 0` est deja refuse par `isNotEqualTo(bonne)` (`:293`).

**Pourquoi cette definition ne lit pas la liste de l'espece.** Les trois etapes d'une bonne suite sont trois places sur le cycle de l'espece, meme quand la suite saute une etape (item 4 saute *jeune grenouille*, item 8 saute *oeuf*, item 12 saute *jeune pousse*). Parcourir le cycle dans son sens a partir de l'une des trois donne toujours une rotation de la suite croissante, et seulement elles. Les rotations de la bonne suite sont donc exactement les ordres que le cycle rend defendables. Les trois autres ordres, ceux qui parcourent le cycle a l'envers, restent les distracteurs admis : c'est la phrase de `nadia-prof`, *"trois ordres sur six sont des rotations du bon ordre, dont la bonne reponse ; les trois autres seuls sont des distracteurs"*.

**Ce qui change par rapport a la garde par distance** (`:325-328`) : celle-ci lit les rangs sur la liste fermee et ne vaut que pour la tache *voisine*. La rotation ne lit que la bonne suite. Les deux gardes ne partagent rien, et aucune extraction commune n'est a faire.

## 2. Ou elle s'ecrit et quand elle est lue

### 2.1 Le test du paquet, pas le test transverse

- **Un seul usage constate.** Parmi les neuf stories, seule `-01` porte une tache *ordre* sur un cycle. `-12` (`GEN-CE1-1`, `docs/backlog/backlog.md`, story `S57-BR-QLM-CE1-12`) ordonne des generations et des evenements d'une vie, qui sont lineaires. Les autres stories ne demandent aucun ordre.
- **Dans le test transverse, elle ferait un faux rouge par inclusion.** Sur `-12`, *"parents, enfants, grands-parents"* est une rotation de *"grands-parents, parents, enfants"* et c'est un distracteur juste : rien n'y revient au debut. La rotation n'est une faute que sur un cycle, et c'est un fait de la liste de `-01`, pas une condition transverse.
- **Ce qui me ferait changer d'avis** : une deuxieme ligne qui ordonne un cycle (les saisons, les moments de la journee). La garde se recopie alors, et l'extraction se discute a ce deuxieme usage.

### 2.2 La condition de lecture

La garde est lue la ou l'est deja la famille *c* : `enonce.tache()` vaut `ordre`, donc l'enonce a la forme `FORME_ORDRE` (`:107-108`). Elle ne porte aucune condition d'espece : les quatre especes de `ESPECES` (`:85-90`) sont des cycles, et `DVP-CE1-1` le dit (*"un developpement est un cycle"*, en-tete du paquet `:25`). Une condition d'espece ajouterait une piece mobile qui ne refuse rien de plus aujourd'hui, et le mutant `M5` (section 3) montre ce qu'elle couterait si elle etait mal ecrite.

**La lecture ne peut pas etre vide en silence.** Mon premier passage de mesure a lu 0 item sur 15 : mon motif omettait l'espace insecable avant `?` (TYP-1), et la garde etait verte sans rien lire. Le test reel n'a pas ce defaut : `analyser` echoue sur tout enonce qui n'a aucune des deux formes (`:191-192`), et `FER-3` exige au moins 3 items *ordre* (`:361-364`). Le jeu fautif garde cette protection par `assertThat(sonde(fautif, "sonde NN")).hasSize(1)`, comme `:522`.

### 2.3 La forme, a recopier par `marc-dev`

Dans la boucle `:287-295`, apres `.isNotEqualTo(bonne);` :

```java
                    assertThat(rotationsDe(bonne))
                            .as("DVP-CE1-2 : « %s » (distracteur de '%s') est la bonne suite qu'on fait tourner dans "
                                    + "le cycle : deux suites défendables", distracteur, item.enonce())
                            .doesNotContain(autre);
```

```java
    private static List<List<String>> rotationsDe(List<String> bonne) {
        List<List<String>> rotations = new ArrayList<>();
        for (int pas = 1; pas < bonne.size(); pas++) {
            List<String> tournee = new ArrayList<>(bonne);
            Collections.rotate(tournee, pas);
            rotations.add(tournee);
        }
        return rotations;
    }
```

Import a ajouter : `java.util.Collections`. Le message ne contient pas *"deux étapes défendables"*, que la sonde 03 attend (`:560`) : les deux gardes restent distinguables par leur message. La Javadoc `:49-51` et le commentaire du paquet `:41` disent la famille *c* avec les mots de `nadia-prof` (section 5, point 6).

## 3. Les sondes et les mutants

Sondes a poser dans `etapes-fautives.yaml`, numerotees a la suite de la derniere (`sonde 14`, ligne 121), chacune avec `DVP-CE1-1 espece`, `DVP-CE1-1 tache : ordre`, `TRV-CE1-2 : c, c` et un second distracteur admis, pour que seule la rotation morde :

| Code | Sonde | Origine | Attendu |
|---|---|---|---|
| `R1` | poule, bonne *"œuf, poussin, poule"*, distracteur *"poule, œuf, poussin"* | `nadia-prof`, section 3 | refuse, message *"fait tourner"* |
| `R2` | plante, bonne *"graine, plante, fleur"*, distracteur *"fleur, graine, plante"* | `nadia-prof`, section 3 | refuse, message *"fait tourner"* |
| `R3` | poule, distracteurs *"poussin, œuf, poule"* et *"poule, poussin, œuf"* | `nadia-prof`, section 3 | passe sans exception |
| `R4` | poule, distracteur *"poussin, poule, œuf"* | ajoutee par moi, tiree de la phrase de `nadia-prof` sur les trois rotations (section 3), pas une condition neuve | refuse, message *"fait tourner"* |

`R1` et `R2` decalent tous deux la suite dans le meme sens (la derniere etape passe en tete). **Sans `R4`, une garde qui ne teste que ce decalage-la passe les trois sondes de `nadia-prof`** : c'est le mutant `M1`.

Mutants de la garde, executes (methode en fin de section) ; "mord" veut dire que la sonde rend un verdict different de l'attendu, donc que le mutant est tue :

| Code | Mutant | `R1` | `R2` | `R3` | `R4` | Tue par |
|---|---|---|---|---|---|---|
| `G` | forme de 2.3 | - | - | - | - | (forme juste) |
| `M0` | garde absente (le test d'aujourd'hui) | mord | mord | - | mord | `R1`, `R2`, `R4` |
| `M1` | decalage d'une place vers la droite seul | - | - | - | mord | `R4` seule |
| `M2` | decalage d'une place vers la gauche seul | mord | mord | - | - | `R1`, `R2` |
| `M3` | refuse aussi la suite inversee | - | - | mord | - | `R3` seule |
| `M4` | compare les etapes en ensemble | - | - | mord | - | `R3` seule |
| `M5` | lue seulement si la suite contient *oeuf* (especes animales) | - | mord | - | - | `R2` seule |

Chaque sonde tue au moins un mutant qu'aucune autre ne tue, sauf `R1`, que `R2` et `R4` couvrent ; `R1` reste la sonde de `nadia-prof` sur l'item reel 15.

**Methode.** Programme Java a fichier unique, hors du depot et non commite, lance par le lanceur de sources du JDK 21.0.10 (propriete `java.version` imprimee par le programme), classpath `snakeyaml` 2.6 et `assertj-core` 3.27.7, les versions que `dependency:build-classpath` resout pour `adapters/adapter-content`. Il recopie a l'identique `ARTICLE`, `etape`, `suite` et `FORME_ORDRE` du test, lit le YAML du paquet avec `snakeyaml`, et applique `G` et chaque mutant aux sondes. **Ce n'est pas le test** : la preuve dans le depot sera l'execution de la classe de test avec les sondes commitees.

## 4. Ce que cela change pour le paquet reel

Meme programme, sur `ce1-qlm-etapes-de-developpement.yaml` a `99383ac4` : 15 items, **4 lus** par la garde (items 4, 8, 12, 15, les quatre items *ordre*).

| Item | Ligne | Bonne suite | Distracteurs | Verdict de `G` |
|---|---|---|---|---|
| 4 | `:77` | oeuf, tetard, grenouille | tetard, oeuf, grenouille ; grenouille, tetard, oeuf | passe |
| 8 | `:105` | chenille, chrysalide, papillon | chrysalide, chenille, papillon ; papillon, chrysalide, chenille | passe |
| 12 | `:133` | graine, plante, fleur | plante, graine, fleur ; **fleur, graine, plante** | **refuse** |
| 15 | `:154` | oeuf, poussin, poule | poussin, oeuf, poule ; **poule, oeuf, poussin** | **refuse** |

Les deux refus sont exactement les deux items que `nadia-prof` refuse en l'etat, sur les deux distracteurs qu'elle nomme, et aucun autre.

**Apres ses corrections** (section 5, points 3 et 4), chaque option mesuree : item 12 avec *"graine, fleur, plante"* ou *"fleur, plante, graine"* ; item 15 avec *"poule, poussin, œuf"* ou *"œuf, poule, poussin"*, le premier distracteur de chaque item inchange. **Aucun refus**, sur les quatre combinaisons, items 4 et 8 compris. Les corrections des items 3, 6, 7 et 11 portent sur la tache *voisine* : la garde ne les lit pas.

**Consequence d'ordre (`V6`)** : la garde, posee seule, fait rougir `dvp1EtDvp2LaBonneReponseEstCalculeeSurLaListeEtChaqueDistracteurEstDansSaFamille` (`:473-476`) sur l'item 12, puis 15. Garde, sondes et corrections des items 12 et 15 entrent dans un meme commit de `marc-dev`.

## 5. Ce qui reste sans test, et revient a `nadia-prof`

- **Que chaque espece de la liste soit un cycle.** La garde le suppose sans le lire. C'est un fait de sa liste (`QMT-CE1-2`), pas une propriete du YAML. Si une espece lineaire y entrait, la garde ferait un faux rouge visible, qu'il faudrait trancher chez elle.
- **La plausibilite des distracteurs admis.** Apres la garde, il reste trois ordres admis par item (les parcours a l'envers). Lequel des trois est un bon piege pour un enfant de CE1 n'est pas une propriete calculable.
- **L'operation mentale de la tache *ordre*** (`FER-3`) et l'exactitude de chaque ordre contre sa liste : inchanges, deja a elle (section 5 de sa lecture).
- **Hors de la demande, constate en lisant `:283-286`** : `isSorted` d'AssertJ accepte deux rangs egaux (`[0, 0, 2]` passe, mesure par le meme programme). Une bonne suite qui repete une etape passe donc `DVP-CE1-1`. Aucun item reel ne le fait ; je le signale a `marc-dev` sans le demander.

## 6. Actions et routage

| Code | Quoi | Qui |
|---|---|---|
| `A1` | Forme de 2.3 dans la branche *ordre* du test du paquet | `marc-dev` |
| `A2` | Sondes `R1` a `R4` dans `etapes-fautives.yaml` et un test qui attend *"fait tourner"* sur `R1`, `R2`, `R4` et aucune exception sur `R3` | `marc-dev` |
| `A3` | `A1`, `A2` et les corrections des items 12 et 15 dans un meme commit (`V6`) | `marc-dev` |
| `A4` | `R4` est ajoutee par moi, tiree de sa section 3 : la confirmer ou la retirer | `nadia-prof`, par `sofia-sm` |

Aucune regle ArchUnit n'est touchee et aucun `ADR` n'est demande : c'est une garde de contenu sur du YAML, dans la forme d'`ADR-0013` (jeu fautif commite) et du precedent `-01`.

**Signe** : `theo-archi`, s57, palier 5, 2026-10-26.
