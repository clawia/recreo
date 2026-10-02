# Fiche de conformité - bandit-manchot-emotion-ressentir-agir-cm1

- **Mécanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-emc-emotion-ressentir-agir.yaml`
  (11 items, type `question`), écrit par `lea-dev` (commit `581581b4`).
- **Entrée de catalogue** : `bandit-manchot-emotion-ressentir-agir-cm1`, titre servi *"Ressentir et
  agir"*, consigne servie *"Lis bien, puis dis quelle émotion ressent la personne, ou distingue ce
  qu'elle ressent de ce qu'elle fait."*, `nombreDeManches: 10`.
- **Ligne déclarée** : `EMC-CM1-01`. **Story** : `S46-EMC-EMOTION-CM1` (`docs/backlog/backlog.md`,
  section "Sprint 46").
- **Compétence programme visée** : enseignement moral et civique, CM1 (cycle 3), domaine "Respecter
  autrui" - `squad/programme/enseignement-moral-et-civique/competences-cm1.md`, `EMC-CM1-01` :
  *"Identifier et nommer l'émotion d'une personne dans une situation donnée, et distinguer ce que
  l'on ressent de ce que l'on fait."*
- **Conditions opposables** : `EMC-1`, `EMC-2`, `EMC-5`, `EMC-8`
  (`squad/programme/enseignement-moral-et-civique/competences-cp.md` et `competences-cm1.md`),
  `FER-2`, `FER-3`, `CYC-1` contre `bandit-manchot-emotions-autrui-cp`.
- **Lien décision** : `DEC-0102`.
- **Statut global** : **VALIDE SOUS RESERVE**, prononcé le 2026-10-02 par `nadia-prof` (s46,
  instance A). Deux réserves ouvertes, `EMC-CM1-R5` et `EMC-CM1-R6` ; elles ne retiennent pas la
  ligne. Fiche ouverte par ce verdict : aucune fiche n'existait pour ce jeu.
- **Dernière mise à jour** : 2026-10-02.

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

- **Verdict** : **VALIDE SOUS RESERVE**
- **Date** : 2026-10-02
- **Auditrice** : `nadia-prof`, instance A (`DEC-0102`).
- **Audit joué** : **oui**

### 1. Ce que j'ai fait

Worktree dédié `s46-nadia-audit-a`, fondé sur `round-two` à `7a507b2a`. Jar construit par
`bash outils/mvn-jdk21.sh -pl bootstrap -am package -DskipTests`, lancé sur le JDK 21 de la
toolchain (21.0.10), sur un port dédié. Done technique acquis par `yanis-qa` sur le job `build`
(`squad/sprints/s46-soustraction-ce1-emc-et-sciences-cm1/rapport-qualite.md`) ; je n'ai lancé aucun
autre job de la CI.

**Six parties complètes de dix manches** par appels HTTP réels : un tiers des manches juste du
premier coup, un tiers après une erreur, un tiers après trois erreurs jusqu'à la révélation. **Les
onze items ont été tirés et joués.** Après une erreur, l'indice tombe ; après la troisième, *"Appuie
sur la bonne réponse : ..."*. Écran de fin atteint sur les six parties.

**`CYC-1`** : j'ai rejoué `bandit-manchot-emotions-autrui-cp` avant de juger, deux parties
complètes, ses onze items tirés, et j'ai lu `cp-emc-emotions-autrui.yaml` en entier.

### 2. Ancrage au programme

**Les deux moitiés de la ligne sont servies, et c'est bien cette compétence.**

- **Tâche A, identifier et nommer l'émotion** (items 1 à 6) : cinq émotions sur six sont absentes du
  CP - fierté, déception, honte, soulagement, jalousie. **C'est le gain de cycle 3** : le CP ne
  servait que joie, tristesse, colère et peur. Seul l'item 6 (Tom dans le sous-sol sombre, peur)
  refait une situation de CP.
- **Tâche B, distinguer ce que l'on ressent de ce que l'on fait** (items 7 à 11) : la question porte
  tantôt sur ce que la personne ressent, tantôt sur ce qu'elle fait, et les propositions mêlent une
  émotion et des gestes. **Elle est absente du CP** : le CP demandait *"Qu'est-ce que cela
  montre ?"*, c'est-à-dire de passer du geste à l'émotion, jamais de les séparer. Et quatre items
  sur cinq montrent une émotion suivie d'un geste maîtrisé (respirer pour se calmer, ranger
  calmement, applaudir, prévenir la maîtresse) : l'enfant lit qu'on peut ressentir de la colère sans
  agir sous elle, ce qui est le sens de cette moitié du programme.

### 3. Conditions, vérifiées une par une

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `EMC-1` | **oui** | Onze personnages nommés (Léa, Hugo, Aya, Malo, Tom), aucune question sur l'enfant qui joue |
| `EMC-2` | **oui, au sens de sa seconde phrase** | Sur chaque item, une seule des trois propositions est défendable devant la situation décrite. Sur les cinq items de la tâche B, une seule proposition est une émotion. Voir `EMC-CM1-R5` pour trois items qui ne passent que parce que l'émotion concurrente n'est pas proposée |
| `EMC-5` | **oui** | Aucun item à deux réponses défendables |
| `EMC-8` | **oui** | Aucune deuxième personne, aucun *"que fais-tu"* : les questions portent sur ce que la personne décrite ressent ou fait |
| `FER-2` | **oui** | Onze items, au-dessus de dix |
| `FER-3` | **oui** | Deux tâches, six et cinq items |
| `CYC-1` | **oui** | Aucun item ne contredit le CP : la colère reste portée par les poings serrés et le pied qui tape (items 7 du CM1, 7 et 11 du CP), la peur par le noir et la solitude. La seconde moitié de la ligne est une extension, pas une redite |

**Exactitude** : aucune émotion n'est mal nommée. Le vocabulaire (*fierté*, *déception*, *honte*,
*soulagement*, *jalousie*) est celui d'un CM1. **Lisibilité** : 18 à 23 mots sur la tâche A, 30 à
37 sur la tâche B, avec trois propositions longues. C'est long pour un enfant qui lit lentement, mais
lire les gestes dans l'énoncé est la tâche elle-même ; je ne le retiens pas. **Biais** : aucun
stéréotype de genre dans la répartition des émotions (la peur est donnée à un garçon, la colère aux
deux).

### 4. Réserves - à transformer en stories par `camille-po`, pas à corriger dans ce sprint

Forme copiée de `EMC-CM1-R4` (trois segments, marqueur `R` dans le dernier, `ADR-0100`), numéros
suivants de la même famille, allouées dans `squad/decisions/REGISTRE-identifiants.md`, section 4,
dans le même commit que cette fiche.

**`EMC-CM1-R5` - trois items ne tiennent `EMC-2` que parce que l'émotion la plus évidente n'est pas
proposée.**

- Item 3 (Aya a menti et *"pense que celle-ci va bientôt s'en apercevoir"*, bonne réponse *honte*) :
  la situation décrit d'abord la **peur d'être découverte**. Distracteurs *fierté* et *joie*.
- Item 5 (Léa n'est pas invitée par sa meilleure amie, bonne réponse *jalousie*) : l'émotion la plus
  évidente est la **tristesse**. Distracteurs *fierté* et *soulagement*.
- Item 11 (ballon crevé *"par accident"*, bonne réponse *colère*) : *"par accident"* rend la
  **tristesse** ou la **déception** plus évidente que la colère, et rend incohérent le dos tourné aux
  camarades.

Sur les items 3 et 5, les deux distracteurs sont **de valence opposée** à la situation : un enfant
qui se dit *"il lui arrive quelque chose de mauvais, je prends le seul mot triste"* gagne sans
distinguer honte de peur ni jalousie de tristesse - c'est-à-dire sans faire ce que le CM1 ajoute au
CP. **Ce qui est attendu** : réécrire ces trois situations pour que l'émotion visée y soit la seule
évidente (item 3 : ce qu'Aya ressent après avoir été découverte ; item 11 : retirer *"par
accident"* ou changer l'émotion visée), et, sur les items 3 et 5, prendre des distracteurs de même
valence que la bonne réponse sans être défendables devant la situation. **Ce n'est pas un
durcissement d'`EMC-2`** : sa seconde phrase est tenue, et je ne relève pas la barre après coup.
C'est une réserve neuve, datée de ce jour.

**`EMC-CM1-R6` - sur les deux items "fait" de la tâche B, la bonne réponse se reconnaît à son ton.**
Items 8 (Hugo) et 10 (Malo) : la bonne réponse est le seul geste calme et bienveillant (*"il range
calmement les pièces du jeu"*, *"il applaudit quand même ses camarades sélectionnés"*), face à un
geste emporté qui n'est pas dans l'énoncé (*"il se met à pleurer bruyamment"*, *"il quitte le gymnase
en courant"*). Un enfant qui choisit *"le geste gentil"* sans lire gagne : c'est le motif d'`EMC-8`
- la réponse socialement attendue se devine sans rien savoir. Les trois items "ressent" n'ont pas ce
défaut, parce que le geste calme y est un distracteur. **Ce qui est attendu** : sur un item "fait",
la bonne réponse n'est pas la seule conduite calme ou bienveillante des trois propositions.

### 5. Mention de couverture, à porter par `camille-po`

`EMC-CM1-01` passe à **`couvert-valide`**, avec la mention exigée d'avance sur toute ligne d'EMC CM1,
reprise mot pour mot : **"connaissances civiques et reconnaissance de situations seules ; aucun
comportement, aucun engagement et aucune participation à une décision collective ne sont servis
par ce produit"**. Vérifié item par item : le paquet ne demande ni comportement, ni engagement, ni
participation à une décision collective. **Les deux réserves ne retiennent pas la ligne.**

### 6. Ce dont je reste la seule garde sur cette ligne

1. **`EMC-2` et `EMC-5`** : qu'une situation n'admette qu'une émotion évidente parmi les
   propositions. Aucun test ne lit le sens d'une situation.
2. **La différence réelle avec le CP** (`CYC-1`) : aucun garde-fou ne compare les deux paquets.
3. **L'indice de ton** (`EMC-CM1-R6`) et **l'indice de valence** (`EMC-CM1-R5`) : rien d'automatique
   ne voit qu'une bonne réponse se devine sans lire.

**Signé** : `nadia-prof`, s46, instance A, 2026-10-02.
