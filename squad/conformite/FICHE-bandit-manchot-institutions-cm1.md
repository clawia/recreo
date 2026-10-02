# Fiche de conformité - bandit-manchot-institutions-cm1

- **Mécanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-emc-institutions.yaml`
  (12 items, type `question`), écrit par `sofia-sm`, casquette dev (commit `daded833`).
- **Entrée de catalogue** : `bandit-manchot-institutions-cm1`, titre servi *"Les institutions de ma
  commune"*, consigne servie *"Lis bien, puis dis à quelle institution s'adresser, ou quel est son
  rôle."*, `nombreDeManches: 10`.
- **Ligne déclarée** : `EMC-CM1-09`. **Story** : `S46-EMC-INSTITUTIONS-CM1`
  (`docs/backlog/backlog.md`, section "Sprint 46").
- **Compétence programme visée** : enseignement moral et civique, CM1 (cycle 3), domaine "Construire
  une culture civique" - `squad/programme/enseignement-moral-et-civique/competences-cm1.md`,
  `EMC-CM1-09` : *"Identifier le rôle de quelques institutions proches (mairie, conseil municipal,
  école) et à qui s'adresser selon la situation."*
- **Conditions opposables** : `EMC-5`, `EMC-6`, `EMC-7` (précision du 2026-10-01, même fichier),
  `HIS-2` et `HIS-3` (*"cette matière porte des faits institutionnels dont je ne suis pas la source,
  et une erreur y est un motif de refus à elle seule"*, introduction du jugement de faisabilité),
  `FER-1`, `FER-2`, `FER-3`, `CYC-1` contre `bandit-manchot-roles-ecole-commune-cp`.
- **Lien décision** : `DEC-0102`.
- **Statut global** : **REFUSÉ** le 2026-10-02 au second passage par `nadia-prof` (s46, instance A),
  sur un motif neuf introduit par la correction (items 10 et 11). Les deux motifs du premier passage
  du même jour sont levés. Les deux verdicts sont conservés dans le bloc de validation. Fiche ouverte
  par le premier verdict : aucune fiche n'existait pour ce jeu.
- **Dernière mise à jour** : 2026-10-02.

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

- **Verdict** : **REFUSÉ**
- **Date** : 2026-10-02
- **Auditrice** : `nadia-prof`, instance A (`DEC-0102`).
- **Audit joué** : **oui**

**Motif, en une phrase** : l'item 4 enseigne un fait institutionnel faux - une limitation de vitesse
dans le centre d'une commune n'est pas votée par le conseil municipal, elle est fixée par arrêté du
maire - et aucun fait du paquet ne porte sa source (`HIS-3`), ce qui est la raison pour laquelle
l'erreur a traversé.

### 1. Ce que j'ai fait

Worktree dédié `s46-nadia-audit-a`, fondé sur `round-two` à `7a507b2a`. Jar construit par
`bash outils/mvn-jdk21.sh -pl bootstrap -am package -DskipTests`, lancé sur le JDK 21 de la
toolchain (21.0.10), sur un port dédié. Done technique acquis par `yanis-qa` sur le job `build`
(`squad/sprints/s46-soustraction-ce1-emc-et-sciences-cm1/rapport-qualite.md`) ; je n'ai lancé aucun
autre job de la CI.

**Six parties complètes de dix manches** par appels HTTP réels, un tiers des manches juste du premier
coup, un tiers après une erreur, un tiers après trois erreurs jusqu'à la révélation. **Les douze
items ont été tirés et joués.** L'item 4 a été joué jusqu'à la révélation : l'écran affiche *"Appuie
sur la bonne réponse : le conseil municipal"*, puis l'indice *"Une règle qui s'applique à toute la
commune est d'abord votée par les élus."*

**`CYC-1`** : j'ai rejoué `bandit-manchot-roles-ecole-commune-cp` avant de juger, deux parties
complètes, ses onze items tirés, et j'ai lu `cp-emc-roles-ecole-commune.yaml` en entier.

### 2. Ancrage au programme

La compétence est la bonne, et sa partie de cycle 3 est réelle : **distinguer la mairie, lieu des
démarches, du conseil municipal, assemblée élue qui vote** (items 2, 3, 7, 8, 9), ce que le CP ne
servait pas - le conseil municipal n'y apparaît nulle part. Les deux tâches de `FER-3` sont servies
(six items "à qui s'adresser", six items "que fait cette institution").

### 3. Motif 1 - l'item 4 est faux, et son indice généralise l'erreur

*"Une nouvelle règle doit limiter la vitesse des voitures dans le centre du village. Qui vote cette
règle ?"* - bonne réponse servie : **le conseil municipal**.

**Le fait.** À l'intérieur d'une agglomération, la police de la circulation appartient au **maire**
(code général des collectivités territoriales, article L2213-1), et c'est **par arrêté motivé du
maire** qu'une vitesse inférieure à celle du code de la route est fixée sur tout ou partie des voies
(même code, article L2213-1-1). Il n'y a pas de vote : le maire décide seul. Je ne suis pas la source
de ce fait, et ces deux articles sont la source que le paquet doit porter, vérifiée par son auteur.

**Ce que l'enfant apprend.** L'énoncé présuppose un vote (*"Qui vote cette règle ?"*), la révélation
couronne le conseil municipal, et l'indice affirme une règle générale fausse : *"Une règle qui
s'applique à toute la commune est d'abord votée par les élus."* Les arrêtés de police du maire -
circulation, stationnement - sont précisément des règles qui s'appliquent à toute la commune sans
aucun vote. **Une erreur factuelle dans un contenu scolaire est un motif de refus à elle seule**
(`HIS-3`, motif ; introduction du jugement de faisabilité d'EMC CM1).

**La cause dans le paquet.** Son commentaire fixe la convention *"la mairie est le lieu des démarches
administratives [...], le conseil municipal est l'organe élu qui vote les décisions (budget, projets,
règlements)"*. Cette convention omet **le maire**, qui exécute les décisions du conseil et détient
seul des pouvoirs propres. Tant qu'un item porte sur une règle de police, elle produit l'erreur.

### 4. Motif 2 - `HIS-3` n'est tenue sur aucun item

`HIS-3` : *"Chaque fait porte sa source dans le commentaire du paquet."* Le référentiel d'EMC CM1 la
rend applicable à toute la matière. **Aucun fait institutionnel du paquet n'a de source** : ni
l'inscription scolaire d'abord en mairie (item 2), ni le vote par le conseil municipal d'un
équipement (item 3), ni l'autorisation d'une sortie scolaire par le directeur (item 11), ni l'item 4.
La story ne citait pas `HIS-3`, mais j'ai écrit le 2026-10-01, sur cette même ligne, qu'une story qui
ne cite qu'une condition ne retire pas les autres. C'est la condition qui aurait arrêté le motif 1
avant moi.

### 5. Conditions, vérifiées une par une

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `HIS-3` | **non** | Section 4 |
| Exactitude (`HIS-3`, motif) | **non, item 4** | Section 3. Les faits des onze autres items sont exacts à ma connaissance - inscription d'abord en mairie, vote d'un équipement par le conseil municipal, sortie scolaire ordinaire autorisée par le directeur, réparation d'un lampadaire signalée à la mairie -, et c'est leur source qui doit le montrer, pas moi |
| `EMC-5`, `HIS-2` | **oui** | Une seule réponse défendable par item, dans la convention mairie / conseil municipal que le paquet tient constante. Aucun service d'urgence en distracteur d'un autre |
| `EMC-6` | **oui** | Habitants, famille, élève et classe génériques, jamais *"ta commune"* ni *"ton école"* |
| `EMC-7` | **oui, sans objet** | Aucun numéro d'urgence nulle part, ni énoncé, ni proposition, ni indice |
| `FER-1` | **oui** | Mairie (items 1, 2), conseil municipal (3, 4), école (5, 6). Une fois l'item 4 remplacé, le conseil municipal reste servi par l'item 3 |
| `FER-2`, `FER-3` | **oui** | Douze items ; deux tâches de six |
| `CYC-1` | **oui, à la lettre** | Aucun item ne contredit un rôle servi au CP. Voir la section 6 sur deux redites |

### 6. À reprendre dans le même passage, sans être des motifs de refus

1. **Item 3** : *"Les habitants veulent savoir si la commune va construire un nouveau terrain de
   sport. Qui a voté cette décision ?"* La première phrase dit que rien n'est décidé, la question
   dit que la décision est votée. Un enfant de CM1 qui lit bien bute dessus.
2. **Item 5** : *"À qui l'enseignant le signale-t-il ?"* - réponse *"l'école"*. L'enseignant fait
   partie de l'école : il le signale au directeur ou à la directrice. La question n'a pas de sens
   telle qu'écrite.
3. **Item 12, indice** : *"L'entretien courant de la rue est une démarche administrative"*. Réparer
   un lampadaire n'est pas une démarche administrative ; c'est un service de la commune.
4. **Items 10 et 11, redites du CP.** L'item 10 (*"Que fait le directeur ou la directrice de
   l'école au quotidien ?"*, *"il ou elle organise la vie de l'école et veille à son bon
   déroulement"*) reprend presque mot pour mot l'item 7 du paquet de CP. L'item 11 reprend l'item 3
   du CP (l'autorisation d'une sortie scolaire). `CYC-1` est tenue à sa lettre - rien n'est
   contredit -, mais ces deux items n'apprennent rien de neuf à un CM1. Le gain de cycle 3 de cette
   ligne est le conseil municipal et le maire.

### 7. Ce qui doit changer pour que le lot repasse

| Fait | À qui |
|---|---|
| Remplacer l'item 4 par une décision réellement votée par le conseil municipal (le budget, la construction d'un équipement, le nom d'une rue), ou par un item sur ce que décide le maire, et corriger son indice | `camille-po` en fait la story, développeur affecté par `sofia-sm` |
| Porter dans le commentaire du paquet la source de chaque fait institutionnel servi, item par item (`HIS-3`) | même story |
| Revoir la convention du commentaire du paquet pour y placer le maire à côté de la mairie et du conseil municipal | même story |
| Les quatre points de la section 6 | même story, au choix de `camille-po` |

Je rejouerai le paquet entier à son retour, pas seulement l'item corrigé.

### 8. Ce dont je reste la seule garde sur cette ligne

1. **L'exactitude institutionnelle de chaque item.** Aucun test ne sait qui fixe une limitation de
   vitesse. Si un item futur fait voter un arrêté de police, la CI reste verte - elle l'est
   aujourd'hui.
2. **La présence d'une source par fait** (`HIS-3`) : aucun test ne lit le commentaire du paquet.
3. **La différence réelle avec le CP** (`CYC-1`) : aucun garde-fou ne compare les deux paquets.

**Identifiants** : aucun neuf, aucun renommé.

**Signé** : `nadia-prof`, s46, instance A, 2026-10-02.

---

## Bloc de validation pédagogique, second passage - nadia-prof UNIQUEMENT

- **Verdict** : **REFUSÉ**
- **Date** : 2026-10-02
- **Auditrice** : `nadia-prof`, instance A (`DEC-0102`).
- **Audit joué** : **oui**, paquet entier.

**Motif, en une phrase** : les items 10 et 11, réécrits par la correction, enseignent que le
directeur ou la directrice organise la vie de l'école et autorise une sortie scolaire *"seul ou
seule"*, sans aucun vote. C'est faux : le règlement intérieur de l'école est voté par le conseil
d'école, et une sortie scolaire avec nuitée n'est pas autorisée par le directeur. Ces deux
affirmations portent un marqueur `HIS-3` sans source.

### 1. Ce que j'ai fait

Worktree dédié `s46-nadia-audit-a2`, fondé sur `round-two` à `7ff80b67`, qui porte la correction de
`sofia-sm` (`6f27d1e9`). Jar construit par `bash outils/mvn-jdk21.sh -pl bootstrap -am package
-DskipTests`, lancé sur le JDK 21 de la toolchain (21.0.10), sur un port dédié. Je n'ai lancé aucun
job de la CI et je n'en déduis rien.

**Six parties complètes de dix manches, sur le paquet entier** : un tiers des manches juste du
premier coup, un tiers après une erreur, un tiers après trois erreurs jusqu'à la révélation. **Les
douze items ont été tirés et joués**, et sur chacun les propositions affichées sont celles du paquet.
Écran de fin atteint sur les six parties. **`CYC-1`** : `bandit-manchot-roles-ecole-commune-cp`
rejoué, cinq parties complètes, ses onze items tirés.

### 2. Les deux motifs du premier passage sont levés

| Motif | Levé | Ce que j'ai vérifié |
|---|---|---|
| Item 4 faux | **oui** | *"La commune doit choisir le nom d'une nouvelle rue. Qui vote cette décision ?"*, réponse : le conseil municipal. C'est exact : la dénomination des voies communales relève du conseil municipal, qui règle par ses délibérations les affaires de la commune (CGCT, article L2121-29). L'indice ne généralise plus. Joué jusqu'à la révélation |
| `HIS-3` non tenue | **oui, sur dix items** | Chaque item porte un marqueur. Pour un fait de manuel, la précision du 2026-09-03 (famille a) me laisse signer sans source externe, et c'est ce que j'ai fait sur `cm1-emc-symboles.yaml`. Les marqueurs *"fonctionnement ordinaire ..."* des items 1, 2, 5, 6, 7 et 12 suffisent donc. **Ils ne suffisent pas pour les items 10 et 11** (section 3) |
| Convention sans le maire | **oui** | Le commentaire place le maire à côté de la mairie et du conseil municipal, avec ses pouvoirs propres de police (CGCT, L2213-1 et L2213-1-1). Aucun item ne le met en bonne réponse, ce que je n'avais pas demandé |
| Section 6, points 1 à 3 | **oui** | Item 3 : la contradiction a disparu. Item 5 : c'est maintenant un élève qui signale, la question a un sens. Item 12 : l'indice parle d'un service de la commune |

### 3. Le motif neuf : les items 10 et 11

La section 6, point 4, de mon premier passage signalait une redite du CP, *"sans être un motif de
refus"*. La correction y a répondu en donnant au directeur ou à la directrice un pouvoir exclusif :

- item 10 : *"Qui organise la vie quotidienne de l'école, sans que le conseil municipal ait à
  voter ?"*, réponse : *"le directeur ou la directrice de l'école, seul ou seule"* ;
- item 11 : *"Une sortie scolaire ne passe pas par un vote du conseil municipal. Pourquoi est-elle
  quand même autorisée ?"*, réponse : *"parce que le directeur ou la directrice de l'école
  l'autorise seul ou seule"*.

**Les faits.** Je ne suis pas la source de ce qui suit. Chaque point est à vérifier par l'auteur
dans le texte en vigueur avant d'écrire, comme j'ai procédé pour l'item 4.

1. **Item 10.** Le directeur ou la directrice n'organise pas la vie de l'école seul ou seule. Le
   **conseil d'école vote le règlement intérieur de l'école** (code de l'éducation, article D411-2),
   et le conseil des maîtres est consulté sur l'organisation. Il y a donc bien un vote, mais pas
   celui du conseil municipal. La commune, de son côté, décide de la cantine et du temps
   périscolaire quand elle les organise. L'item apprend à un enfant de CM1 qu'à l'école personne ne
   vote.
2. **Item 11.** Une sortie scolaire *avec nuitée* (classe de découverte) n'est pas autorisée par le
   directeur ou la directrice, mais par l'autorité académique (circulaire relative aux sorties
   scolaires, n° 99-136 du 21 septembre 1999, à vérifier dans sa version en vigueur). Le marqueur de
   l'item dit *"sortie scolaire ordinaire"*, l'énoncé dit *"une sortie scolaire"*, et la réponse dit
   *"seul ou seule"*.

**C'est la même erreur que celle de l'item 4 au premier passage.** Pour rendre le contraste plus net,
le paquet invente une règle exclusive (*"sans vote"*, *"seul ou seule"*) qu'aucune source ne porte.
Une erreur factuelle dans un contenu scolaire suffit à refuser (`HIS-3`, motif).

**Pourquoi `HIS-3` ne couvre pas ces deux items.** La précision du 2026-09-03 classe en famille (b)
un fait *"dont la formulation engage une couverture"*. Un tel fait demande une source, ou une
reformulation qui la rend inutile. *"Seul ou seule"* et *"sans vote"* sont des formulations de ce
genre. Le marqueur *"fonctionnement ordinaire d'une école enseigné au cycle 3"* n'est pas une source.

**Une correction de mon premier verdict.** Au premier passage, j'ai jugé exacte *"la sortie scolaire
ordinaire autorisée par le directeur"* (section 5). L'énoncé que j'ai joué disait *"une sortie
scolaire"*, sans *"ordinaire"*. La réponse de l'époque (*"le directeur ou la directrice organise et
autorise la vie de l'école"*) était assez large pour rester juste, et je ne la refuse pas après
coup. Ce que je refuse, c'est le *"seul ou seule"* ajouté par la correction.

### 4. À reprendre dans le même passage, sans être des motifs de refus

1. **Item 10, l'énoncé élimine un distracteur.** *"sans que le conseil municipal ait à voter"*
   exclut lui-même *"le conseil municipal, qui vote cette organisation en réunion"*. C'est le défaut
   de `HG-R23` sur une autre ligne.
2. **Item 11, deux distracteurs absurdes.** *"la mairie organise un vote à la place du conseil
   municipal"* et *"le conseil municipal l'autorise en dehors d'un vote"* ne correspondent à rien de
   ce qu'un enfant peut croire, et le premier contredit la convention du paquet. Il ne reste qu'une
   proposition lisible, et l'item devient une devinette.
3. **Item 10 ne sert plus la tâche B annoncée par le commentaire** (*"dire ce que fait une
   institution nommée"*, bonne réponse : une fonction). C'est maintenant une question *"qui ?"* dont
   la réponse est une personne. `FER-3` reste tenue (six items de tâche A, cinq de tâche B), mais le
   commentaire du paquet ne décrit plus le paquet.

### 5. Conditions, vérifiées une par une

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| Exactitude (`HIS-3`, motif) | **non, items 10 et 11** | Section 3. Les dix autres items sont exacts, dont le 4 |
| `HIS-3` | **non, items 10 et 11** | Section 2 et section 3 |
| `EMC-5`, `HIS-2` | **oui** | Une seule réponse défendable par item, dans la convention mairie / conseil municipal / maire |
| `EMC-6` | **oui** | Aucun *"ta commune"* ni *"ton école"* |
| `EMC-7` | **oui, sans objet** | Aucun numéro d'urgence |
| `FER-1` | **oui** | Mairie (items 1, 2), conseil municipal (3, 4), école (5, 6) |
| `FER-2`, `FER-3` | **oui** | Douze items. Six en tâche A, cinq en tâche B (section 4, point 3) |
| `CYC-1` | **oui, à la lettre** | Aucun rôle servi au CP n'est contredit. L'item 8 du CP (*"la mairie s'occupe des bâtiments et du matériel de l'école"*) est compatible avec le paquet |

### 6. Ce qui doit changer pour que le lot repasse

| Fait | À qui |
|---|---|
| Items 10 et 11 : retirer toute affirmation exclusive (*"seul ou seule"*, *"sans vote"*, *"sans que le conseil municipal ait à voter"*). Le plus simple est de revenir à leur version du premier passage, que j'avais jugée exacte : la redite du CP n'était pas un motif de refus. Si l'auteur garde une version différenciée du CP, chaque fait porte une source vérifiable dans son marqueur (`HIS-3`, famille b), et une sortie scolaire est qualifiée dans l'énoncé lui-même si la réponse ne vaut que pour certaines sorties | `camille-po` en fait la story, développeur affecté par `sofia-sm` |
| Les trois points de la section 4, si les items 10 et 11 sont réécrits au lieu d'être rétablis | même story |

Rien d'autre n'est à reprendre : les items 1 à 9 et 12 passent toutes les conditions. Je rejouerai
le paquet entier à son retour.

### 7. Ce dont je reste la seule garde sur cette ligne

1. **L'exactitude institutionnelle de chaque item.** Aucun test ne sait qui vote un règlement
   intérieur ou qui autorise une classe de découverte : la CI est verte sur les deux erreurs.
2. **La qualité des sources `HIS-3`.** Aucun test ne lit le commentaire. Un garde-fou qui exigerait
   un marqueur non vide aurait laissé passer celui des items 10 et 11.
3. **La différence réelle avec le CP** (`CYC-1`).

**Identifiants** : aucun neuf, aucun renommé.

**Signé** : `nadia-prof`, s46, instance A, 2026-10-02.
