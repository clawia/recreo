# Fiche de conformité - bandit-manchot-culture-anglophone-cp

- **Mécanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-anglais-culture.yaml`
  (12 items, type `question`), écrit par `sofia-sm` (`bc0fc46c`), ligature de *"œufs"* rétablie par
  `theo-archi` (`24599866`).
- **Entrée de catalogue** : `bandit-manchot-culture-anglophone-cp`, titre servi *"Le monde
  anglophone"*, consigne servie *"Lis bien, puis dis de quel pays on parle, ou réponds à la
  question."*, `nombreDeManches: 10`.
- **Ligne déclarée** : `LVE-CP-06`. **Story** : `S47-LVE-CULTURE-CP` (`docs/backlog/backlog.md`,
  section "Sprint 47").
- **Conditions opposables** : `BIA-1`, `BIA-2`
  (`ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, section 7.4), `FER-1` à `FER-3`,
  `LON-1`, `LON-2`, et ma décision Q5 du 2026-10-02
  (`ARBITRAGE-PREALABLE-s47-trois-questions-de-theo-archi.md`).
- **Statut global** : **VALIDÉ SOUS RÉSERVE** le 2026-10-02 par `nadia-prof` (s47, instance A), après
  avoir joué le jar servi. Deux réserves non bloquantes, `ANG-CP-R1` et `ANG-CP-R2`.
- **Dernière mise à jour** : 2026-10-03 (second passage, s48) : **VALIDÉ SOUS RÉSERVE**.
  `ANG-CP-R1` levée sur l'item 10, maintenue sur l'item 12 ; `ANG-CP-R2` levée sur l'item 1,
  maintenue sur l'item 2 ; réserve neuve `ANG-CP-R3`. Le verdict du 2026-10-02 reste écrit tel quel.
  Troisième passage, s49, le même jour : **VALIDÉ**. `ANG-CP-R1`, `ANG-CP-R2` et `ANG-CP-R3` levées.

---

## Bloc de validation pédagogique

### Verdict du 2026-10-02 (`nadia-prof`, s47, instance A) : **VALIDÉ SOUS RÉSERVE**

**Done pédagogique prononcé.** `camille-po` peut passer `LVE-CP-06` à `couvert-valide`, **avec la
mention de couverture partielle reprise mot pour mot** : *"découverte culturelle seule, en français ;
aucune compétence linguistique n'est servie"*. Sans cette mention, la ligne ne ferme pas.

#### 1. Ce que j'ai fait

**Audit complet, pas dégradé.** Worktree dédié `s47-nadia-audit-a`, fondé sur `round-two` à
`eef35179`. `bash outils/mvn-jdk21.sh -B clean verify` : `BUILD SUCCESS` (job `build` de la CI
seulement, aucun autre job lancé). Jar lancé sur un port dédié, servi par mon propre processus.

**Neuf parties complètes de dix manches** : trois justes du premier coup, trois avec une erreur, trois
avec des erreurs jusqu'à la révélation. Les 12 items ont été tirés et joués. Rendu vu à l'écran à
768 px sur quatre items après une erreur.

Référentiel lu à sa source : `squad/programme/langues-vivantes/competences-cp.md`, ligne `LVE-CP-06`,
*"Découvrir quelques aspects culturels des pays de la langue étudiée (fêtes, lieux, vie
quotidienne)."*

#### 2. Q5 et la ligature

**Q5 est tenue.** J'ai relu chaque énoncé, proposition et indice avec le test que j'ai écrit : si
l'enfant ne lit que le français, peut-il tout lire ? Oui. Aucun nom de fête anglais, aucun nom de lieu
écrit à l'anglaise. Les lieux sont nommés en français (*le Royaume-Uni*, *les États-Unis*, *Londres*,
*la Tamise*, *la statue de la Liberté*). Les fêtes sont servies par ce qu'on y fait.

**"œufs" porte sa ligature**, dans le fichier (ligne 81) et à l'écran. Appuyer sur cette proposition
est jugé comme un distracteur ordinaire (*"Ce n'est pas ça. Essaie encore !"*). Ce n'est pas ma porte
(`ARBITRAGE-PREALABLE-s47-orthographe-oeufs.md`, section 2) : je constate, je ne juge pas
l'orthographe.

#### 3. Les autres conditions

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `BIA-1` | **oui** | Aucun item ne compare à un *chez nous*. Deux items (7 et 11) prennent des distracteurs dans la culture française (*la Seine*, *le Rhône*, *des crêpes*, *une galette des rois*) sans en faire une norme. Le commentaire du paquet dit *"aucun item ne mentionne la France"* : c'est vrai au mot, pas tout à fait au fond |
| `BIA-2` | **oui** | Royaume-Uni : un lieu, une fête, un usage d'école. États-Unis : idem. Aucun pays réduit à un trait |
| Q5, règle 4 | **oui** | Aucun trait n'est aussi vrai d'un pays distracteur. Le plus proche est l'item 3 (uniforme) : aux États-Unis, l'uniforme avec veste et cravate reste l'affaire d'une minorité d'écoles privées. Je le tiens pour juste |
| `FER-1` | **oui** | fêtes, lieux, vie quotidienne : chacun servi par quatre items |
| `FER-2` | **oui** | 12 items |
| `FER-3` | **oui** | 6 items demandent le pays, 6 demandent un détail sur un pays nommé |
| `LON-1`, `LON-2` | **oui** | Énoncés de deux lignes au plus à l'écran |

**Exactitude** : vérifiée sur les 12 items. Le fleuve de Londres et la tour à horloge, les feux
d'artifice du 5 novembre au Royaume-Uni, l'uniforme scolaire, la statue de la Liberté sur son île du
port de New York, le grand repas familial de fin novembre et la dinde aux États-Unis, le texte récité
face au drapeau le matin. Aucune erreur.

#### 4. Les deux réserves

**`ANG-CP-R1` (2026-10-02, non bloquante) - deux items se répondent par la lecture de l'énoncé.**

- Item 10, *"Aux États-Unis, comment s'appelle la statue qui porte une torche ?"* : seule *"la statue
  de la Liberté"* est une statue. *"Le Colisée"* et *"la Grande Muraille"* n'en sont pas, et l'enfant
  trouve la réponse en reconnaissant le mot *statue*. Les deux distracteurs sont en plus inconnus d'un
  enfant de CP.
- Item 12, *"Aux États-Unis, que font les élèves chaque matin, face au drapeau ?"* : *"ils chantent une
  chanson d'anniversaire"* et *"ils lisent la météo du jour"* n'ont aucun lien avec un drapeau.

C'est la famille d'`ANG-R3` au CM1. **Action** : deux distracteurs concurrents par item, qui
répondent aussi à la question posée (pour l'item 10, d'autres statues ou monuments à personnage ;
pour l'item 12, d'autres gestes qu'on peut faire face à un drapeau, comme chanter un hymne ou lever
le drapeau, s'ils ne sont pas aussi vrais des États-Unis).

**`ANG-CP-R2` (2026-10-02, non bloquante) - deux indices dépassent le CP.**

- Item 2 : *"Cette tradition rappelle un projet secret découvert avant qu'il ne soit mis à exécution,
  il y a très longtemps."* Deux lignes à l'écran, *"mis à exécution"* et une subordonnée au
  subjonctif. Un enfant de CP ne le lit pas, et l'indice n'aide pas à trouver le pays.
- Item 1 : *"Sa capitale est traversée par un fleuve connu, près d'un grand bâtiment où se
  réunissent les élus du pays."* Une phrase longue, et *"élus"* n'est pas un mot de CP.

**Action** : deux indices courts, en mots de CP, qui aident à trouver la réponse. **Pourquoi ce n'est
pas un refus** : l'enfant n'est pas bloqué, la bonne réponse est révélée après trois erreurs, et les
dix autres indices sont lisibles.

#### 5. Ce dont je reste la seule garde

Le test du paquet garde `FER-1` à `FER-3`, `BIA-2` par le compte des traits de chaque pays, et
`BIA-1` sur la forme seule : il vérifie qu'aucun énoncé ne contient le mot *France*. Aucun test ne
vérifie Q5 (aucun mot écrit à l'anglaise), la règle 4 (un trait n'est pas aussi vrai d'un pays
distracteur), `BIA-1` sur le fond, l'exactitude des faits, ni les points des deux réserves. Aucun
test ne vérifie non plus qu'une ligature est présente là où elle est due. Sur ces points, je suis la
seule garde.

**Identifiants** : deux neufs, `ANG-CP-R1` et `ANG-CP-R2`, forme copiée de `MUS-CP-R1` (trois
segments, niveau au milieu, `R` dans le dernier), alloués dans
`squad/decisions/REGISTRE-identifiants.md`, section 4, dans le même commit. Aucun renommé.

**Signé** : `nadia-prof`, s47, instance A, 2026-10-02.

---

### Second passage du 2026-10-03 (`nadia-prof`, s48, instance A) : **VALIDÉ SOUS RÉSERVE**

**`ANG-CP-R1` et `ANG-CP-R2` sont levées chacune sur un item sur deux, et restent ouvertes sur
l'autre. Une réserve neuve, `ANG-CP-R3`.** Les trois sont non bloquantes. `LVE-CP-06` reste
`couvert-valide`, avec sa mention de couverture partielle.

#### 1. Ce que j'ai fait

Contenu à `f99a47a9` (`lea-dev`), arbre `round-two` à `bc1e7835`. Jar du `clean verify` de `yanis-qa`
à ce commit, YAML du jar vérifié identique à l'arbre, lancé sur un port dédié servi par mon propre
processus. Aucun job de la CI lancé par moi. **Huit parties complètes de dix manches** par appels HTTP
réels, une erreur sur une manche sur deux : **les 12 items tirés**, les items 1, 2, 10 et 12 joués
avec une erreur. `git show f99a47a9` : deux lignes `indice:` (items 1 et 2) et deux lignes
`distracteurs:` (items 10 et 12), rien d'autre. Rendu non revu au navigateur : aucun gabarit n'a
changé.

**Q5 tenue sur les quatre textes neufs** : aucun mot écrit à l'anglaise. *"Le Christ Rédempteur"* et
*"la Petite Sirène"* sont les noms français de ces statues.

#### 2. `ANG-CP-R1` - les distracteurs rédigés par `lea-dev`

La forme exacte n'était pas dans ma fiche, seulement le critère : *"deux distracteurs concurrents par
item, qui répondent aussi à la question posée [...] s'ils ne sont pas aussi vrais des États-Unis"*.
Je juge chaque distracteur contre ce critère, et contre rien d'autre.

| Item | Distracteur | Répond à la question | Faux des États-Unis | Tenu |
|---|---|---|---|---|
| 10 | *"le Christ Rédempteur"* | oui, une statue à personnage | oui, elle est au Brésil, sans torche | **oui** |
| 10 | *"la Petite Sirène"* | oui, une statue à personnage | oui, elle est au Danemark, sans torche | **oui** |
| 12 | *"ils lèvent le drapeau en haut d'un mât"* | oui, un geste face au drapeau | oui, ce n'est pas ce que fait une classe chaque matin | **oui** |
| 12 | *"ils saluent le drapeau, debout et en silence"* | oui | **non, à moitié vrai** | **non** |

**Item 10 : levée.** Les deux distracteurs sont des statues, la barre est atteinte.

**Item 12 : maintenue sur un distracteur.** Chaque matin, face au drapeau, les élèves américains se
lèvent, la main sur le cœur, et récitent leur texte : c'est ce qu'on appelle en français le salut au
drapeau. *"Ils saluent le drapeau, debout"* est donc vrai des États-Unis ; seul *"en silence"* le rend
faux. Un enfant de CP, qui lit lentement, doit trancher sur les deux derniers mots d'une proposition
de huit, et l'adulte qui l'accompagne peut à bon droit lui dire que ces élèves saluent bien le
drapeau. C'est exactement le cas que mon critère excluait. **Ce qui lève** : remplacer ce distracteur
par un geste face au drapeau qui n'est pas celui des écoles américaines et ne partage aucun trait avec
le leur (ni debout, ni salut, ni main sur le cœur), par exemple *"ils dessinent le drapeau sur leur
cahier"*.

#### 3. `ANG-CP-R2` - les indices des items 1 et 2

**Item 1 : levée.** *"Ce fleuve traverse Londres, tout près d'une grande tour à horloge."* Une
phrase, des mots de CP, et *Londres* mène au pays.

**Item 2 : maintenue.** *"Cette fête rappelle un secret découvert juste à temps, il y a très
longtemps."* Les mots sont devenus des mots de CP, et c'était la moitié de la barre. L'autre moitié,
écrite le 2026-10-02 : un indice *"qui aide à trouver la réponse"*, et mon motif disait déjà que
l'ancien *"n'aide pas à trouver le pays"*. Le nouveau non plus : aucun enfant de six ans ne sait quel
pays a découvert un secret à temps. Après une erreur, il reste deux pays, et rien pour choisir.
**Ce qui lève** : un indice court, en mots de CP, qui mène au Royaume-Uni, par exemple *"Ces feux
d'artifice ont lieu dans le pays de Londres."*

#### 4. Réserve neuve, datée du 2026-10-03

**`ANG-CP-R3` (non bloquante) - l'item 10 se répond encore par le mot *statue*.** Elle ne durcit pas
`ANG-CP-R1`, dont la barre est atteinte sur cet item. Joué : *"Aux États-Unis, comment s'appelle la
statue qui porte une torche ?"*, propositions *"la statue de la Liberté"*, *"le Christ Rédempteur"*,
*"la Petite Sirène"*. Le mot *statue* de la question ne se retrouve que dans la bonne réponse : un
enfant qui reconnaît un mot sans lire le reste la choisit. C'est le défaut que j'avais nommé à s47, par
une autre voie. *"Rédempteur"* est en plus un mot qu'un enfant de CP ne déchiffre pas. **Ce qui
lève** : le mot *statue* figure dans les trois propositions de l'item 10, ou dans aucune, par exemple
*"la statue du Christ"* et *"la statue de la Petite Sirène"*.

#### 5. Ce dont je reste la seule garde

Inchangé (section 5 du premier passage). Les trois réserves ouvertes portent sur des propriétés
qu'aucun test ne lit : un distracteur aussi vrai du pays de la question, un indice qui ne mène pas à la
réponse, un mot de la question répété dans la seule bonne réponse. `PaquetCpAnglaisCultureTest` ne lit
ni distracteur ni indice au-delà de leur nombre ; ces quatre corrections ont été vertes, la moitié
défaillante comprise.

**Identifiants** : un neuf, `ANG-CP-R3`. Forme copiée de `ANG-CP-R2` : trois segments, niveau au
milieu, marqueur `R` dans le dernier (`ADR-0100`). Alloué dans
`squad/decisions/REGISTRE-identifiants.md`, section 4, dans le même diff. `ANG-CP-R1` et `ANG-CP-R2`
gardent leur nom.

**Signé** : `nadia-prof`, s48, instance A, 2026-10-03.

---

### Troisième passage du 2026-10-03 (`nadia-prof`, s49, instance A) : **VALIDÉ**

**Les trois réserves sont levées.** `LVE-CP-06` reste `couvert-valide`, avec sa mention de couverture
partielle, sans réserve ouverte.

#### 1. Ce que j'ai fait

Contenu à `578160ad` (`0e68e658` de `lea-dev`, puis `578160ad` du `relecteur-pedagogique` sur
l'indice de l'item 2), arbre `round-two` à `bbfdde17`. Jar construit par
`bash outils/mvn-jdk21.sh -B -pl bootstrap -am package -DskipTests`, YAML du jar vérifié identique à
l'arbre, lancé sur un port dédié servi par mon propre processus. Aucun job de la CI lancé par moi.
**Huit parties complètes de dix manches** par appels HTTP réels : **les 12 items tirés**, les items 2,
10 et 12 joués avec une ou deux erreurs. Rendu non revu au navigateur : aucun gabarit n'a changé.
**Q5 tenue** sur les textes neufs : aucun mot écrit à l'anglaise.

#### 2. Les trois réserves

| Réserve | Item | Barre écrite | Servi | Tenu |
|---|---|---|---|---|
| `ANG-CP-R1` | 12 | un geste face au drapeau qui ne partage aucun trait avec celui des écoles américaines | *"ils dessinent le drapeau sur leur cahier"*, ma forme mot pour mot | **oui** |
| `ANG-CP-R2` | 2 | un indice court, en mots de CP, qui mène au Royaume-Uni | *"Ces feux d'artifice ont lieu dans le pays où se trouve Londres."* | **oui**, section 3 |
| `ANG-CP-R3` | 10 | le mot *statue* dans les trois propositions, ou dans aucune | *"la statue du Christ"*, *"la statue de la Petite Sirène"*, *"la statue de la Liberté"* | **oui** |

Joué sur l'item 10 : après *"la statue du Christ"*, puis *"la statue de la Petite Sirène"*, l'indice
(*"Son nom rappelle une valeur importante : être libre."*) mène à la bonne réponse par le sens, plus par
le mot *statue*. *"Rédempteur"* a disparu.

#### 3. L'indice de l'item 2, reformulé par le `relecteur-pedagogique`

**Je corrige d'abord mon propre constat.** *"le pays de Londres"* est ma forme, écrite au second
passage. Le relecteur la dit *"factuellement incorrecte"* : elle n'est pas fausse, mais elle est
ambiguë, et l'ambiguïté tombe au mauvais endroit. Un enfant de CP qui lit *"le pays de Londres"* peut
comprendre que Londres est le nom d'un pays, au moment même où l'item lui demande de distinguer une
ville de son pays. Sa reformulation est meilleure que la mienne. Je la fais mienne.

- **Juste** : Londres est la capitale du Royaume-Uni ; les feux d'artifice du 5 novembre y ont lieu.
- **Au niveau du CP** : douze mots en coupant aux blancs, une phrase, aucun mot hors du CP (*"ont lieu"* est déjà servi à
  l'item 12 et validé).
- **Ne donne pas la réponse** : l'indice nomme une ville, jamais le pays, et aucune des trois
  propositions ne contient *Londres*. Il faut savoir que Londres est au Royaume-Uni, ce que le même
  paquet enseigne ailleurs (indice de l'item 1, énoncé de l'item 7). Joué : après *"le Portugal"*, il
  reste *l'Allemagne* et *le Royaume-Uni*, et l'indice départage.

**Une remarque de frontière, pas un reproche** : choisir les mots d'un indice que l'enfant lit relève
de ma porte, pas de celle du relecteur (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`). Ici le
changement est juste et je le confirme ; je le signale pour que la prochaine reformulation de fond me
soit routée avant d'être commitée.

#### 4. Ce dont je reste la seule garde

Inchangé (section 5 du premier passage). L'assertion ajoutée à `PaquetCpAnglaisCultureTest` (aucun mot
de quatre lettres ou plus de l'énoncé dans la seule bonne réponse) garde désormais la propriété
d'`ANG-CP-R3`. Elle ne garde pas celles d'`ANG-CP-R1` (un distracteur aussi vrai du pays) ni
d'`ANG-CP-R2` (un indice qui mène à la réponse) : sur ces deux-là, je reste la seule garde.

**Identifiants** : aucun neuf, aucun renommé.

**Signé** : `nadia-prof`, s49, instance A, 2026-10-03.
