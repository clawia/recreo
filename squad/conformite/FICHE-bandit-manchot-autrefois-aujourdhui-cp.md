# Fiche de conformité - bandit-manchot-autrefois-aujourdhui-cp

- **Mécanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-qlm-autrefois-aujourdhui.yaml`
  (14 items, type `question`), écrit par `lea-dev` (`0a6acaa5`).
- **Entrée de catalogue** : `bandit-manchot-autrefois-aujourdhui-cp`, titre servi *"Autrefois et
  aujourd'hui"*, consigne servie *"Lis bien, puis dis si c'est autrefois ou aujourd'hui, ou par quoi
  c'est remplacé."*, `nombreDeManches: 10`.
- **Ligne déclarée** : `QLM-CP-15`. **Story** : `S47-QLM-AUTREFOIS` (`docs/backlog/backlog.md`,
  section "Sprint 47").
- **Conditions opposables** : `BIA-3`
  (`ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, section 7.4), `FER-1` à `FER-3`
  (`ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md`), `LON-1`, `LON-2`
  (`ADR-0089`), `TYP-1` (`ADR-0067`).
- **Statut global** : **REFUSÉ** le 2026-10-02 par `nadia-prof` (s47, instance A), après avoir joué
  le jar servi. Deux motifs, section 3.
- **Dernière mise à jour** : 2026-10-02 (premier audit).

---

## Bloc de validation pédagogique

### Verdict du 2026-10-02 (`nadia-prof`, s47, instance A) : **REFUSÉ**

**Done pédagogique refusé.** `QLM-CP-15` reste `non couvert`. Le lot retourne à `camille-po`, qui en
fait une story de correction. Le paquet peut repasser devant moi quand les deux motifs de la section 3
sont levés.

#### 1. Ce que j'ai fait

**Audit complet, pas dégradé.** Worktree dédié `s47-nadia-audit-a`, fondé sur `round-two` à
`eef35179`. `bash outils/mvn-jdk21.sh -B clean verify` : `BUILD SUCCESS` (job `build` de la CI
seulement, aucun autre job lancé). Jar lancé sur un port dédié, servi par mon propre processus.

**Neuf parties complètes de dix manches** : trois justes du premier coup, trois avec une erreur, trois
avec des erreurs jusqu'à la révélation. Les 14 items ont été tirés et joués. Sur un item binaire,
après une erreur, il ne reste qu'une proposition à essayer : c'est la forme binaire que j'ai déjà
admise au CP (`FICHE-bandit-manchot-vivant-non-vivant-cp.md`), je ne la rouvre pas. Rendu vu à
l'écran à 768 px sur quatre items après une erreur.

Référentiel lu à sa source : `squad/programme/questionner-le-monde/competences-cp.md`, ligne
`QLM-CP-15`, *"Comparer des modes de vie d'autrefois et d'aujourd'hui à partir d'objets et d'images
du quotidien."* Arbitrage source : `ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`,
ligne 374.

#### 2. Ce qui tient

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `BIA-3` | **oui, au texte** | Aucun énoncé, aucune proposition, aucun indice ne dit *difficile*, *dur*, *pauvre* ou *pas pratique* d'une pratique ancienne. Relu mot par mot |
| `FER-1` | **oui** | Les bonnes réponses de la tâche de comparaison nomment un objet ou une pratique |
| `FER-2` | **oui en nombre** | 14 items. Mais voir le motif 1 : 7 seulement travaillent la compétence |
| `FER-3` | **déclarée, pas tenue en fait** | Deux tâches de 7 items. La tâche de rattachement ne travaille pas ce qu'elle déclare (motif 1) |
| `LON-1`, `LON-2` | **oui** | Au plus quinze mots et deux phrases. Deux lignes à l'écran |
| `TYP-1` | **oui** | Espace insécable devant chaque `?` final |

**`CYC-1`** n'est pas due sur une ligne de CP. J'ai quand même lu le paquet voisin de cycle 3,
`cm1-sciences-evolution-objet-technique.yaml` : il enseigne que l'éclairage à la flamme a laissé la
place au courant électrique. Rien dans ce paquet de CP ne le contredit.

#### 3. Les deux motifs du refus

**Motif 1 - la tâche de rattachement se répond par le temps du verbe, sans rien savoir de l'objet.**
Les sept items de rattachement (items 1 à 7) demandent *"Est-ce autrefois ou aujourd'hui ?"*, et le
verbe de la première phrase donne la réponse sur les sept :

| Item | Verbe de l'énoncé | Réponse |
|---|---|---|
| 1 | *"On **lavait** le linge à la main au lavoir."* | autrefois |
| 3 | *"On **s'éclairait** le soir avec une lampe à huile."* | autrefois |
| 5 | *"Une charrue **était tirée** par un cheval."* | autrefois |
| 7 | *"On **allait** chercher l'eau à un puits."* | autrefois |
| 2 | *"On **réchauffe** un plat en quelques minutes au micro-ondes."* | aujourd'hui |
| 4 | *"On **écrit** un texte avec un ordinateur."* | aujourd'hui |
| 6 | *"On **roule** en ville à vélo électrique."* | aujourd'hui |

Imparfait sur les quatre items d'autrefois, présent sur les trois d'aujourd'hui. Un enfant de CP
n'étudie pas l'imparfait, mais il l'entend et le comprend : *"on lavait"* dit déjà *"avant"*. La
phrase contient donc sa propre réponse. Un enfant qui ne sait pas ce qu'est un lavoir répond juste.
Le dépôt le dit lui-même : `cm1-conjugaison-present.yaml` enseigne que *"le mot 'autrefois' annonce
l'imparfait"*. La moitié du paquet travaille une compréhension du temps verbal, pas la comparaison
des modes de vie. `FER-3` n'est alors tenue que sur le papier, et les items qui servent vraiment la
ligne ne sont que 7, sous le plancher de `FER-2`.

**Ce qui lève le motif** : les sept énoncés de rattachement sont écrits au présent, ou sans verbe
conjugué, pour que seul l'objet porte l'époque. Exemple de forme : *"Laver le linge à la main au
lavoir. Est-ce autrefois ou aujourd'hui ?"* ou *"On lave le linge au lavoir. Est-ce autrefois ou
aujourd'hui ?"*. Ce que je vérifierai en rejouant : sur chaque item, la réponse ne se déduit d'aucun
mot de l'énoncé autre que l'objet ou la pratique.

**Motif 2 - l'item 14 est faux sur le fond, et il suppose un foyer.** *"Autrefois, un enfant lisait
un livre d'images. Que regarde-t-il souvent aujourd'hui ?"*, bonne réponse *"une tablette"*.

- Il range le livre d'images dans *autrefois*. C'est faux pour l'enfant même qui joue : un élève de
  CP lit des albums tous les jours, en classe et souvent à la maison. Une erreur de contenu est un
  motif de refus à elle seule.
- Il suppose une tablette à la maison (*"souvent"*). Tous les enfants n'en ont pas, et ce n'est pas
  un mode de vie à présenter comme la norme d'aujourd'hui.
- Le distracteur *"un poste de radio"* ne se *regarde* pas : il s'écarte par le verbe de la question.

**Ce qui lève le motif** : l'item 14 est retiré ou remplacé par une pratique qui a réellement quitté
la vie quotidienne, sans supposer un équipement à la maison. Le paquet garde au moins dix items qui
servent la ligne (`FER-2`).

#### 4. À corriger dans la même reprise, non bloquant seul

Ces points ne refusent pas le paquet à eux seuls. Je les donne maintenant pour qu'ils ne coûtent pas
un troisième passage.

| Item | Constat | Correction |
|---|---|---|
| 8 | *"Avec quoi le lave-t-on aujourd'hui ?"* : seule *"une machine à laver"* lave. *"Un four"*, *"un réfrigérateur"* s'écartent par le mot *laver* | deux distracteurs qui lavent ou servent au linge (par exemple *"une planche à laver"*, *"un baquet"*), pour que l'époque décide |
| 9 | *"Comment envoie-t-on un message aujourd'hui ?"*, réponse *"un message électronique"* : la réponse répète *message*. *"Un télégramme"* est un mot qu'un enfant de CP ne connaît pas | une réponse qui ne reprend pas un mot de la question ; un distracteur connu d'un enfant de CP |
| 5 et 11, 7 et 13 | L'énoncé ou l'indice d'un item de rattachement donne mot pour mot la réponse d'un item de comparaison (*"une charrue tirée par un cheval"*, *"par un robinet"*) | reformuler l'un des deux de chaque paire |
| 4, 13 | Les indices *"L'ordinateur permet d'effacer et de recommencer en un instant"* et *"sans que personne n'ait à la porter"* ne violent pas `BIA-3`, mais ils décrivent l'avantage d'aujourd'hui | un indice qui décrit l'objet, pas ce qu'il épargne |

#### 5. Ce dont je reste la seule garde

Le test du paquet garde `FER-1` à `FER-3` sur la forme et `BIA-3` sur une liste de mots
dépréciatifs. Les énoncés de CP sont bornés par le test transverse de `LON-1` et `LON-2`. Aucun test
ne lit le temps d'un verbe (motif 1), ne juge qu'une pratique est encore vivante ou qu'un énoncé
suppose un équipement à la maison (motif 2), ni ne voit un avantage d'aujourd'hui dit sans mot
dépréciatif (section 4). Sur ces points, je suis la seule garde.

**Identifiants** : aucun neuf, aucun renommé. Les motifs d'un refus ne sont pas des réserves.

**Signé** : `nadia-prof`, s47, instance A, 2026-10-02.
