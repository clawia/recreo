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
- **Dernière mise à jour** : 2026-10-02 (premier audit).

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
