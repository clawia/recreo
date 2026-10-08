# Fiche de conformité - bandit-manchot-synonymes-contraires-ce1

- **Mécanique** : bandit manchot (mécanique générique, partagée avec les autres jeux du catalogue).
- **Contenu(s)** : à créer, type `question`, au moins 20 items (`TRV-CE1-7`). Story
  `S52-FRA-SYNONYMES-CONTRAIRES` (`docs/backlog/backlog.md`), décision `DEC-0111` (section 4), développeur :
  `marc-dev` (planning de s52). Nom de fichier de contenu et identifiant d'entrée de catalogue fixés par le
  développeur au premier commit ; le nom de cette fiche est proposé par `camille-po` et renommé si
  l'identifiant diffère.
- **Statut global** : en construction - aucun contenu écrit à l'ouverture de cette fiche. La rédaction de la
  question attend la réponse de `nadia-prof` à Q2 de l'avis de faisabilité (le mot *synonyme* dans un énoncé
  de `FRA-CE1-25`, `TRV-CE1-4` ; R6 du planning).
  **VALIDÉ SOUS RÉSERVE** le 2026-10-06 par `nadia-prof` (s52, instance A), sur `46e69b13` : une réserve,
  `SYC-CE1-R1`. Voir le bloc de validation.
  **`SYC-CE1-R1` levée** le 2026-10-08 par `nadia-prof` (s54, instance A), sur `0a1f2b80` ; observation 1
  (item 16) traitée le même jour. Aucune réserve ouverte.
- **Dernière mise à jour** : 2026-10-08 (ouverture, `camille-po`, s52 ; verdict, `nadia-prof`, s52 ; rejeu,
  `nadia-prof`, s54).

## Compétences visées

| Matière | Niveau | Domaine | Compétence | Référence au référentiel |
|---|---|---|---|---|
| français | CE1 (cycle 2) | Vocabulaire | Trouver un synonyme ou un contraire d'un mot fréquent | `squad/programme/francais/competences-ce1.md`, `FRA-CE1-25` |

Arbitrage préalable : `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section 6.3, verdict
**accepté sous conditions `SYC-CE1-1` à `SYC-CE1-3`, plafond `couvert-valide`**. Collision vérifiée avec
`FRA-CM1-11` : `cm1-synonymes` et `cm1-antonymes` emploient la même forme avec un vocabulaire de CM1 ; ici,
des mots fréquents.

## Critères pédagogiques attendus - camille-po

- [ ] Mécanique `bandit-manchot`, forme d'item `question`, sans illustration, sans `texteSupport`, sans
  champ à voix haute (`TRV-CE1-1`).
- [ ] Chaque question nomme la relation demandée (synonyme ou contraire) ; les items de synonyme et les
  items de contraire sont chacun au moins un tiers du paquet (`SYC-CE1-1`).
- [ ] Le mot cible est fréquent et placé dans une phrase courte qui fixe son sens (`SYC-CE1-1`, `LON-CE1-1`,
  `LON-CE1-2`).
- [ ] Au moins un tiers des items portent la relation inverse parmi leurs distracteurs (famille (a)) ; l'autre
  famille est (b) mot du même thème de sens différent ; la famille est déclarée à côté de l'item
  (`SYC-CE1-2`, `TRV-CE1-2`).
- [ ] Aucun distracteur qu'un dictionnaire donnerait aussi comme réponse (`SYC-CE1-2`).
- [ ] Aucun contraire formé par préfixe (*défaire*, *impossible*) : frontière avec `FRA-CE1-24`
  (`SYC-CE1-3`).
- [ ] Chaque item porte `FRA-CE1-25` et elle seule ; trois propositions, la bonne réponse strictement la plus
  longue dans la moitié des items au plus (`TRV-CE1-2`) ; chaque proposition compte au plus six mots et une
  phrase (`LON-CE1-5`) ; l'indice respecte `TRV-CE1-5`, les prénoms éventuels `TRV-CE1-6`.
- [ ] Homographes *mur*, *sur*, *près*, *marche* : invisibles au garde-fou d'accents, relus par `nadia-prof`
  (Q3 de l'avis de faisabilité, R7 du planning).
- [ ] Texte servi à l'enfant intégralement accentué, orthographe et ponctuation irréprochables (`DEC-0002`).
- [ ] Mention de couverture attendue : aucune.

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pré-rempli ni anticipé par un autre agent.

### Verdict du 2026-10-06 (`nadia-prof`, s52, instance A) : **VALIDÉ SOUS RÉSERVE**

**Done pédagogique prononcé.** `camille-po` peut passer `FRA-CE1-25` à `couvert-valide`, sans mention de
couverture partielle, et faire une story de la réserve `SYC-CE1-R1` (section 5).

#### 1. Ce que j'ai fait

**Audit joué.** Paquet `ce1-synonymes-contraires.yaml` et entrée de catalogue
`bandit-manchot-synonymes-contraires-ce1` au commit `46e69b13`, joués sur le jar construit au sommet de
branche `40989ba9` (arbre propre avant et après la construction, aucun Maven en cours dans le worktree,
`-DskipTests package`), copié hors du worktree et lancé sur le port 18831. **Rendu non vu au
navigateur** : appels HTTP réels, HTML servi lu.

**Porte technique : non mesurée par moi sur `40989ba9`.** Le jeu se lance, sert et termine ses parties
sans erreur. Le Done technique reste à juger par la CI au sha de fusion.

**Lecture masquée d'abord** : les 24 énoncés lus sans les propositions ; j'ai répondu moi-même à chacun.
Mes 24 réponses sont celles du fichier. Sur trois items (*difficile*, *neuf*, *gentil*), j'avais en tête
une autre réponse juste aussi (*compliqué*, *usé*, *sympathique*) : aucune n'est proposée.

**Quarante parties complètes de dix manches**, mêmes conduites qu'en phase 2 (une juste, une avec une
erreur par manche, une avec trois erreurs par manche, trente-sept mêlées). **Les 24 items tirés** (11 à
25 fois chacun), chacun avec au moins une erreur et une révélation. Sur les 400 manches : aucune
anomalie - propositions et indice servis identiques au fichier, aucune révélation avant la troisième
erreur, réponse révélée juste. Position de la bonne réponse : 138, 133 et 129 aux trois places. Écran
de fin atteint ; après une révélation, il redonne la phrase, la question et la bonne réponse.

#### 2. Ancrage au programme

Le paquet travaille **cette** ligne : 12 items de synonyme, 12 de contraire, chacun sur un mot fréquent
placé dans une phrase courte. Aucun contraire formé par préfixe (`FRA-CE1-24` intacte).

#### 3. Les conditions

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `SYC-CE1-1` | **oui** | 12 et 12 ; la question nomme la relation ; mot cible fréquent dans une phrase qui fixe son sens |
| `SYC-CE1-2` | **oui** | familles déclarées et vérifiées ; (a) sur 21 items sur 24 ; aucun distracteur qu'un dictionnaire donnerait comme réponse dans la phrase de l'item (section 4) |
| `SYC-CE1-3` | **oui** | aucune bonne réponse formée par préfixe ; *malheureux*, synonyme de *triste*, n'est pas un contraire |
| `TRV-CE1-1` | **oui** | `CE1`, une ligne, aucun champ à voix haute, aucun texte support, aucune illustration |
| `TRV-CE1-2` | **oui** | trois propositions distinctes, une seule acceptable ; bonne réponse strictement la plus longue sur 7 items sur 24 |
| `TRV-CE1-3` | **sans objet** | aucun son |
| `TRV-CE1-4` | **oui** | *synonyme* et *contraire* admis (mon arbitrage de s52, Q2) ; aucun mot exclu |
| `TRV-CE1-5` | **oui** | 6 à 8 mots, trois formes ; l'indice des synonymes donne le sens du mot *synonyme*, celui des contraires est autre ; aucun ne donne la réponse |
| `TRV-CE1-6` | **en partie, réserve `SYC-CE1-R1`** | aucun prénom, aucun *"tu"* ; **deux activités servies du côté du stéréotype**, section 5 |
| `TRV-CE1-7` | **oui** | 24 items |
| `LON-CE1-1`, `-2`, `-5` | **oui** | deux phrases par énoncé, 13 mots au plus ; propositions d'un mot |
| Q2 (forme) | **oui** | *un synonyme*, *le contraire*, phrase entre guillemets avant la question ; explication : section 4 |

#### 4. Les points tranchés

**L'explication que Q2 demandait n'est pas servie : elle ne ferme pas la ligne.** Décision (a), comme pour
`FRA-CE1-22` à s51 (`FICHE-bandit-manchot-graphie-ce1.md`, section 4), et pour le même motif. Rien n'est
routé à `theo-archi` : je n'exige pas d'explication.

- **Ce que l'explication devait faire** : nommer la relation sur le mot de l'item (*« joyeux » veut dire
  presque la même chose que « content »*).
- **Vérifié au jeu** : le gabarit ne rend l'explication qu'avec la révélation. Or l'écran de révélation
  montre déjà, ensemble, la question (*Quel mot est le contraire de « monte » ?*), *"Appuie sur la bonne
  réponse : descend"* et l'indice (*Un contraire dit l'inverse du mot.*). La relation, le mot cible, la
  réponse et le sens de la relation sont sous les yeux de l'enfant. Une explication n'ajouterait rien ;
  sur les autres chemins de sortie, elle ne serait jamais servie.
- **Ma correction** : j'ai écrit cette exigence dans mon arbitrage de s52 sans relire le garde-fou des
  explications (`ChaquePaquetExpliqueSesItemsAVariationTest`, `ADR-0024`), que j'avais pourtant déjà
  rencontré à s51. C'est la même erreur, une seconde fois. L'amendement daté est dans
  `ARBITRAGE-PREALABLE-s52-fra-ce1-questions-de-l-avis.md`.

**Les doutes de `marc-dev`** :

- **Familles (a)** : *vient* pour *arrive* (item de contraire), *vive* pour *rapide*, *pesant* pour *lourd* :
  ce sont des synonymes ou des quasi-synonymes, donc la relation inverse ; aucun dictionnaire ne les
  donne comme contraire. *Pousse* et *pose* pour *lance* (déclarés (b)) : dans *"Il lance le ballon"*, ni
  l'un ni l'autre n'est un synonyme ; *pousser* ne se donne pour *lancer* que dans d'autres emplois
  (*pousser un cri*). Tenu.
- **Cibles conjuguées** (*finit*, *rit*, *lance*, *monte*, *arrive*) : la bonne réponse est conjuguée comme
  la cible, et les adjectifs s'accordent avec leur nom (*basse*, *grande*, *lente*, *fermée*, *agitée*,
  *étroite*). L'enfant peut remettre chaque proposition dans la phrase. Juste.
- **Fréquence** : `SYC-CE1-1` porte sur le mot cible, et les 24 cibles sont fréquentes. *Costaud*,
  *aimable*, *rapidement* sont des réponses connues à l'oral au CE1. *Pesant* et *interminable* sont
  plus rares, mais ce sont des distracteurs : l'enfant trouve la réponse sans les connaître.
- **Homographes**, relus à la main : *brûlant*, *fâché*, *même*, *fatigué*, *fermée*, *cassée*, *agitée*
  portent leur accent ; *rit* n'en a pas ; les formes en *-e* (*colorie*, *marche*, *chante*, *pleure*,
  *pose*, *pousse*, *rigole*, *termine*, *tourne*, *grimpe*, *commence*, *monte*, *arrive*) sont des
  présents après *il* ou *elle* : justes sans accent ; *calme*, *rue*, *à*, *la*, *du*, *de* : justes.

**Deux observations, qui ne sont pas des réserves.**

1. *"Il arrive à l'école"* : la réponse *part* est bien le contraire du mot. Remise dans la phrase, elle
   donne *"Il part à l'école"*, qui veut dire qu'il y va. Un enfant qui vérifie par substitution peut
   douter ; une phrase comme *"Le train arrive."* ôterait ce doute. À considérer au prochain passage sur
   ce paquet, sans condition.
2. *de « auto »*, *de « ouverte »*, *de « arrive »* : élision devant un mot cité. C'est une question
   d'orthographe, qui appartient au `relecteur-pedagogique` (`DEC-0056`) ; je la route par `sofia-sm`,
   sans la trancher.

#### 5. La réserve, datée du 2026-10-06

**`SYC-CE1-R1` - deux activités sont servies du côté du stéréotype (`TRV-CE1-6`).**

| Item | Phrase |
|---|---|
| 19 | *"Il lance le ballon."* |
| 23 | *"Cet homme est fort."* |

Le ballon est donné à un garçon, la force à un homme. C'est le défaut que `TPS-CE1-R1` a fait corriger
dans le paquet du temps, pour le ballon, le même jour. *"Elle est rapide."* va contre le stéréotype ;
*"Elle est petite."* seule ne fait pas un partage.

**Pourquoi je ne refuse pas.** Les 24 réponses sont justes, les distracteurs tiennent `SYC-CE1-2`, aucun
indice n'est faux. Le défaut se répare en changeant deux sujets.

**Ce qui lève la réserve** : le ballon et la force sont chacun portés par un sujet féminin ou sans genre
(*"Elle lance le ballon."* ; *"Cette femme est forte."* avec *costaude*, ou *"Ce cheval est fort."*). Les
réponses ne changent pas, sauf l'accord. Rien d'autre ne change.

#### 6. Ce dont je reste la seule garde sur cette ligne

`PaquetCe1SynonymesContrairesTest` tient la forme de la question, les familles déclarées, l'absence de
préfixe, la longueur et le nombre d'items. **Il ne lit pas le sens** : qu'un distracteur ne soit pas lui
aussi une réponse dans la phrase de l'item, que la phrase fixe bien le sens du mot, l'accent des
homographes, et la répartition des activités par genre (`SYC-CE1-R1`) : je suis la seule garde, en jouant.

### Mentions de non-couverture opposables

> **`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire défiler la page, un élément
> quelconque du jeu, y compris le retour qui suit sa réponse - verdict, indice, proposition. Cette
> propriété est sortie de la porte pédagogique par arbitrage de l'utilisatrice du 2026-08-28
> (`DEC-0053`) ; sa dette est ouverte, croissante et assumée. Aucune mesure ne l'a instruite sur ce
> paquet.

**Identifiants** : un neuf, `SYC-CE1-R1`. Forme copiée de `TPS-CE1-R1` : trois segments, niveau au
milieu, marqueur `R` dans le dernier (`ADR-0100`). Préfixe `SYC-CE1` déjà alloué (`SYC-CE1-1` à `-3`).
Alloué dans `squad/decisions/REGISTRE-identifiants.md`, section 4, dans le même diff. Aucun renommé.

**Signé** : `nadia-prof`, s52, instance A, 2026-10-06.

### Rejeu du 2026-10-08 (`nadia-prof`, s54, instance A) : **`SYC-CE1-R1` levée**

Le Done du 2026-10-06 reste prononcé ; la ligne `FRA-CE1-25` reste `couvert-valide` et n'a pas été rouverte.
Le verdict devient **VALIDÉ**, sans réserve ouverte. La réserve et l'observation 1 restent écrites ci-dessus.

#### 1. Ce que j'ai fait

**Le diff, lu contre `46e69b13`** : `0a1f2b80` (`marc-dev`) change trois lignes `enonce` et rien d'autre
(3 insertions, 3 suppressions, un fichier). Item 16 : *"« Le train arrive. »"* ; item 19 : *"« Elle lance le
ballon. »"* ; item 23 : *"« Ce cheval est fort. »"*. C'est mot pour mot le texte de
`ARBITRAGE-PREALABLE-s54-instance-a-francais-ce1.md`, section 7 (`K5`, `K6`). Bonnes réponses, distracteurs,
familles et indices inchangés.

**Joué** sur le jar construit au sommet de ma branche `5cad6650` (qui contient `0a1f2b80`), port 18854, JDK
21.0.10, HTML servi lu, rendu non vu au navigateur. **Quarante parties complètes de dix manches**, mêmes
conduites qu'au premier passage : les 24 items tirés (13 à 20 fois chacun), aucune anomalie de service sur
les 400 manches, aucune révélation avant la troisième erreur. Les trois items touchés tirés 17, 20 et 20
fois, avec 25, 40 et 34 erreurs et 5, 8 et 7 révélations. Écran de révélation de l'item 16 : *"« Le train
arrive. » Quel mot est le contraire de « arrive » ?"* / *"Appuie sur la bonne réponse : part"* / l'indice.

**Lecture masquée des trois items touchés** : mes trois réponses (*part*, *jette*, *costaud*) sont celles du
fichier.

#### 2. Contre la barre de la réserve

| Barre (section 5, mot pour mot) | Tenue |
|---|---|
| *"le ballon et la force sont chacun portés par un sujet féminin ou sans genre"* | **oui** : *Elle lance le ballon.* ; *Ce cheval est fort.* |
| *"Les réponses ne changent pas, sauf l'accord."* | **oui** : *jette*, *costaud* ; aucun accord ne bouge (*cheval* est masculin) |
| *"Rien d'autre ne change."* | **oui** : trois lignes au diff, dont l'item 16, que l'observation 1 nommait |

**Observation 1, item 16** : remise dans la phrase, *part* donne *"Le train part."*, qui dit l'inverse de
*"Le train arrive."* sans autre lecture possible. Le doute que je décrivais disparaît. *Vient* (a) et
*marche* (b) restent des distracteurs justes : *"Le train vient"* n'est pas un contraire, *"Le train marche"*
non plus.

**Exactitude** : les trois phrases sont correctes, *Ce cheval est costaud* aussi. **Personnages** : avec
*Elle lance le ballon*, le ballon est porté par un sujet féminin ; aucune autre activité du paquet n'a
changé de côté.

L'élision *de « arrive »* reste au `relecteur-pedagogique` (observation 2), je ne la tranche pas.

#### 3. Ce dont je reste la seule garde

Inchangé depuis le premier passage, section 6 : le sens d'un distracteur dans la phrase de son item, la
phrase qui fixe le sens du mot, l'accent des homographes et la répartition des activités par genre.

**Prix** : 0,25 (`PAR-10`, correction reprise mot pour mot d'un texte que j'ai fourni, puis revérification).

**Identifiants** : aucun neuf, aucun renommé.

**Signé** : `nadia-prof`, s54, instance A, 2026-10-08.
