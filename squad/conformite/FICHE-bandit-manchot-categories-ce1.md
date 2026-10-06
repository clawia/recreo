# Fiche de conformité - bandit-manchot-categories-ce1

- **Mécanique** : bandit manchot (mécanique générique, partagée avec les autres jeux du catalogue).
- **Contenu(s)** : à créer, type `question`, au moins 20 items (`TRV-CE1-7`). Story `S52-FRA-CATEGORIES`
  (`docs/backlog/backlog.md`), décision `DEC-0111` (section 4), développeur : `marc-dev` (planning de s52).
  Nom de fichier de contenu et identifiant d'entrée de catalogue fixés par le développeur au premier commit ;
  le nom de cette fiche est proposé par `camille-po` et renommé si l'identifiant diffère.
- **Statut global** : en construction - aucun contenu écrit à l'ouverture de cette fiche.
  **VALIDÉ** le 2026-10-06 par `nadia-prof` (s52, instance A), sur `bd56d39b` : voir le bloc de validation.
- **Dernière mise à jour** : 2026-10-06 (ouverture, `camille-po`, s52 ; verdict, `nadia-prof`, s52).

## Compétences visées

| Matière | Niveau | Domaine | Compétence | Référence au référentiel |
|---|---|---|---|---|
| français | CE1 (cycle 2) | Vocabulaire | Classer des mots par catégorie et trouver le mot qui les désigne tous (*fruit* pour *pomme*, *poire*, *cerise*) | `squad/programme/francais/competences-ce1.md`, `FRA-CE1-26` |

Arbitrage préalable : `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section 6.4, verdict
**accepté sous conditions `CTG-CE1-1` à `CTG-CE1-3`, plafond `couvert-valide`**.

## Critères pédagogiques attendus - camille-po

- [ ] Mécanique `bandit-manchot`, forme d'item `question`, sans illustration, sans `texteSupport`, sans
  champ à voix haute (`TRV-CE1-1`).
- [ ] Au moins la moitié des items demandent le mot qui désigne trois mots donnés ; les autres demandent
  lequel est (ou n'est pas) de la catégorie (`CTG-CE1-1`).
- [ ] L'en-tête du fichier de contenu déclare la liste fermée des catégories ; chaque item en utilise une et
  une seule, de la vie courante (`CTG-CE1-2`).
- [ ] Aucune classification scientifique (vivant et non vivant, classement des êtres vivants) ; aucun membre
  discuté (*tomate*, *champignon*, *baleine*) (`CTG-CE1-2`).
- [ ] Chaque distracteur est de la famille (a) catégorie soeur, (b) mot trop étroit ou l'un des mots donnés,
  ou (c) mot associé qui n'est pas une catégorie, déclarée à côté de l'item (`CTG-CE1-3`, `TRV-CE1-2`).
- [ ] Chaque item porte `FRA-CE1-26` et elle seule ; trois propositions, la bonne réponse strictement la
  plus longue dans la moitié des items au plus (`TRV-CE1-2`) ; chaque proposition compte au plus six mots
  (`LON-CE1-5`) ; l'indice respecte `TRV-CE1-5`.
- [ ] Homographe *marche* : invisible au garde-fou d'accents, relu par `nadia-prof` (Q3 de l'avis de
  faisabilité, R7 du planning).
- [ ] Texte servi à l'enfant intégralement accentué, orthographe et ponctuation irréprochables (`DEC-0002`).
- [ ] Mention de couverture attendue : aucune.

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pré-rempli ni anticipé par un autre agent.

### Verdict du 2026-10-06 (`nadia-prof`, s52, instance A) : **VALIDÉ**

**Done pédagogique prononcé.** `camille-po` peut passer `FRA-CE1-26` à `couvert-valide`, sans mention de
couverture partielle. Aucune réserve.

#### 1. Ce que j'ai fait

**Audit joué.** Paquet `ce1-categories.yaml` et entrée de catalogue `bandit-manchot-categories-ce1` au
commit `bd56d39b`, joués sur le jar construit au sommet de branche `befc4128` (arbre propre avant et
après la construction, `-DskipTests package`), copié hors du worktree et lancé sur le port 18830.
**Rendu non vu au navigateur** : appels HTTP réels, HTML servi lu.

**Porte technique : non mesurée par moi sur `befc4128`.** `marc-dev` lance des `clean verify` dans le même
worktree ; la consigne m'interdit d'en lancer un en parallèle. Le jeu se lance, sert et termine ses
parties sans erreur. Le Done technique reste à juger par la CI au sha de fusion ; ce verdict ne le
remplace pas.

**Lecture masquée d'abord** : les 24 énoncés lus sans les propositions ; j'ai répondu moi-même aux 14
items *"Quel mot désigne"*. Mes 14 réponses sont celles du fichier. Les 10 items *"Lequel est un"* ne
se répondent pas sans propositions ; je les ai jugés ensuite, propositions lues.

**Quarante parties complètes de dix manches** : une jouée juste, une avec une erreur par manche, une
avec trois erreurs (révélation) par manche, trente-sept en conduite mêlée. **Les 24 items tirés**
(8 à 25 fois chacun), chacun avec au moins une erreur et une révélation. Sur les 400 manches : aucune
anomalie - propositions servies identiques au fichier, indice servi identique au fichier, aucune
révélation avant la troisième erreur, réponse révélée juste. Position de la bonne réponse : 131, 128
et 141 aux trois places. Écran de fin atteint ; après une révélation, il redonne l'énoncé et la bonne
réponse.

#### 2. Ancrage au programme

Le paquet travaille **cette** ligne, dans ses deux sens : trouver le mot qui désigne trois mots
(14 items) et reconnaître un membre d'une catégorie (10 items). Aucune classification scientifique ;
huit catégories de la vie courante, déclarées en tête.

#### 3. Les conditions

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `CTG-CE1-1` | **oui** | 14 *"Quel mot désigne"* sur 24, 10 *"Lequel est un"* |
| `CTG-CE1-2` | **oui** | fruit, légume, animal, vêtement, meuble, outil, instrument de musique, moyen de transport ; ni *tomate*, ni *champignon*, ni *baleine* ; aucun membre que je juge discuté (*salade* et *écharpe* sont rangées ainsi en classe) |
| `CTG-CE1-3` | **oui** | familles déclarées sur chaque item et vérifiées une à une ; voir 4 pour les deux formes |
| `TRV-CE1-1` | **oui** | `CE1`, une ligne, aucun champ à voix haute, aucun texte support, aucune illustration |
| `TRV-CE1-2` | **oui** | trois propositions distinctes, une seule acceptable ; bonne réponse strictement la plus longue sur **9** items sur 24 (voir 5) |
| `TRV-CE1-3` | **sans objet** | aucun son |
| `TRV-CE1-4` | **oui** | aucun mot exclu |
| `TRV-CE1-5` | **oui** | 7 à 10 mots, huit formes, aucune ne donne la réponse, aucune n'est fausse |
| `TRV-CE1-6` | **sans objet** | aucun personnage, aucun *"tu"* qui suppose un foyer |
| `TRV-CE1-7` | **oui** | 24 items |
| `LON-CE1-5` | **oui** | trois mots au plus par proposition |

#### 4. Les doutes de `marc-dev`, tranchés

- **Familles (a) et (c) dans la forme *"Lequel est un"*.** Dans cette forme, une catégorie en distracteur
  n'aurait pas de sens (*"Lequel est un fruit ? légume"*). Je retiens la lecture déclarée en tête du
  fichier : (a) est un membre d'une autre catégorie de la liste (*une carotte* pour *fruit*), (c) un mot
  associé qui n'est membre d'aucune (*un panier*, *un nid*). C'est ce que `CTG-CE1-3` demande, transposé
  à la forme qu'elle autorise.
- **Homographes** (Q3 de mon arbitrage de s52) : *marché* est écrit avec son accent sur ses deux
  occurrences, *flûte* garde son circonflexe, seule graphie du paquet. *Scie* n'a pas de forme accentuée
  concurrente. Aucun mot du paquet n'a perdu un accent.

#### 5. Exactitude, niveau, ton

**Une seule réponse acceptable sur chaque item** : vérifié en lisant chaque énoncé avec chacune des trois
propositions. Aucun distracteur ne désigne aussi les trois mots donnés.

**Un compte faux dans l'en-tête du fichier, sans effet sur la condition.** L'en-tête annonce la bonne
réponse strictement la plus longue sur *"huit items sur vingt-quatre"*. Je compte **9**, avec la mesure
de `PaquetCe1CategoriesTest` (longueur de chaîne) : *animal*, *vêtement* deux fois, *instrument de
musique* deux fois, *moyen de transport* deux fois, *un pantalon*, *une guitare*. 9 sur 24 reste sous la
moitié ; `TRV-CE1-2` est tenue. Le commentaire est à corriger, par `marc-dev`.

**Deux observations, qui ne sont pas des réserves.** Je les écris pour qu'on ne les croie pas vues et
acceptées sans y penser.

1. Plusieurs distracteurs (a) viennent d'une catégorie éloignée (*meuble* pour *pull, jupe, manteau* ;
   *une chaise* pour *vêtement*). Ils tiennent `CTG-CE1-3`, qui ne demande pas une catégorie proche ;
   ces items sont faciles. Les items *fruit* / *légume* portent la confusion réelle.
2. Sur 4 items, *"Quel mot désigne"* a pour réponse un groupe de trois mots (*instrument de musique*,
   *moyen de transport*) parmi des propositions d'un mot. J'ai écrit cette forme et cette liste
   moi-même à s48 ; je ne les retourne pas contre le paquet.

**Niveau** : vocabulaire de la maison, de la classe et de la ferme ; consigne de catalogue reprise
d'entrées existantes.

#### 6. Ce dont je reste la seule garde sur cette ligne

`PaquetCe1CategoriesTest` tient les formes, la liste fermée, les familles déclarées, la longueur et le
nombre d'items. **Il ne lit pas le sens** : qu'un distracteur ne soit pas lui aussi une réponse juste,
qu'un membre ne soit pas discuté, et l'accent des homographes (*marché*) restent à moi seule, en jouant.

### Mentions de non-couverture opposables

> **`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire défiler la page, un élément
> quelconque du jeu, y compris le retour qui suit sa réponse - verdict, indice, proposition. Cette
> propriété est sortie de la porte pédagogique par arbitrage de l'utilisatrice du 2026-08-28
> (`DEC-0053`) ; sa dette est ouverte, croissante et assumée. Aucune mesure ne l'a instruite sur ce
> paquet.

**Identifiants** : aucun neuf, aucun renommé.

**Signé** : `nadia-prof`, s52, instance A, 2026-10-06.
