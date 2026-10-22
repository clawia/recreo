# Fiche de conformite - bandit-manchot-droit-devoir-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-emc-droit-devoir.yaml` (24 items, type `question`,
  aucun texte support), paquet ecrit dans le lot.
- **Entree de catalogue** : `bandit-manchot-droit-devoir-ce1`, titre servi *"Les droits et les devoirs"*, consigne servie *"Lis
  bien, puis choisis la bonne réponse."*, `nombreDeManches: 10`.
- **Ligne declaree** : `EMC-CE1-03`. **Story** : `S57-BR-EMC-CE1-03`.
- **Competence programme visee** : `squad/programme/enseignement-moral-et-civique/competences-ce1.md`, `EMC-CE1-03`,
  *"Associer quelques droits de l'élève à l'école aux devoirs qui les accompagnent (le droit d'apprendre et le devoir de laisser
  les autres apprendre)."* Plafond `couvert-valide`.
- **Conditions opposables** : `DDE-CE1-1`, `DDE-CE1-2`, `NRC-CE1-1` et les transverses de la section 2
  (`ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`, 2 et 4.3) ; `EMC-1` a `EMC-7` ;
  `arbitrage-nadia-preparation-palier-3-ce1.md` 4.1 et 4.4 ; points d'audit de `lecture-nadia-palier-3.md` section 5 et de
  `FICHE-bandit-manchot-regle-sanction-ce1.md` (aucune forme ne donne la reponse, aucun distracteur absurde, pas de bonne reponse
  qui reprenne le verbe de l'enonce).
- **Statut global** : **VALIDE SOUS RESERVE**, prononce au rejeu du 2026-10-22 par `nadia-prof` (s57, palier 4, instance
  A'), sur `5b9bb648`. REFUSE au premier passage (verdict conserve ci-dessous). Fiche ouverte le 2026-10-22 par `nadia-prof`.
- **Derniere mise a jour** : 2026-10-22, rejeu.

---

## Lecture complete

**lecture complete : ce1-emc-droit-devoir, 24 items lus** - 2026-10-22, 18:48 (UTC+02:00), lu par `date`, sur la branche
`s57-sofia-emc-ce1-03` a `db5e0e6d` ; code et contenu a `edf2b03b` (`git diff --name-only edf2b03b db5e0e6d` ne nomme que le
rapport du lot). `grep -c "bonneReponse:"` rend 24. Lus : les 24 enonces, bonnes reponses, 48 distracteurs, 24 indices, les
couples et familles de l'en-tete, `rapport-sofia-dev-palier-4-lp5.md` sections 6 a 8. Paquets voisins relus sur leurs enonces et
bonnes reponses : `cp-emc-droits-enfant` (12), `cp-emc-regles-de-vie` (11), `cm1-emc-droit-devoir` (11), `cm1-emc-regle-loi` (11).

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-22 (`nadia-prof`, s57, palier 4, instance A') : **REFUSE**

**Commit joue** : `edf2b03b` (sommet `db5e0e6d`, seul le rapport differe), jar construit dans mon worktree d'audit
`recreo-s57-nadia-p4-lp5` (`-pl bootstrap -am package -DskipTests`, mesure partielle), port 18512, *"Tomcat started on port
18512"* et PID lus dans mon journal, arrete par PID. Jeu par moi, requetes HTTP : 12 parties de 10 manches, 24 enonces servis sur
24, aucun enonce inconnu du paquet ; deux erreurs et revelation a chaque partie ; les items 14 et 24 joues sur le distracteur que
je signale, indice lu a l'ecran. **Non fait par moi** : le releve d'ecran a 744 x 551 (`ines-design`).

**Motifs du refus, chacun actionnable sans me redemander :**

**M1 - item 14, deux reponses defendables** (`EMC-5`). *"Jade laisse Hugo finir son exercice. Quel droit de Hugo est ainsi
protégé ?"*, bonne reponse *"suivre la leçon"*, distracteur *"finir son travail"* (famille b). Ce que le geste de Jade protege,
c'est exactement que Hugo finisse son travail : le distracteur dit la situation mieux que la bonne reponse, et l'indice (*"Que
peut faire Hugo quand Jade agit ainsi ?"*) y mene. Joue : *finir son travail* recoit *"Ce n'est pas ça"*. Ce qu'il faut : une
situation dont le droit protege n'est pas le distracteur, ou un autre distracteur.

**M2 - item 24, un droit rendu en retour d'un bon geste** (`DDE-CE1-2`, refus ecrit d'avance : *"un droit gagne en faisant son
devoir"*). *"Zoé remercie Adam quand il l'aide. Quel droit d'Adam est ainsi protégé ?"*, bonne reponse *"être traité poliment"*,
indice *"Que reçoit Adam quand Zoé le remercie ?"*. Adam est traite poliment *parce qu'il aide*, et l'indice dit qu'il le
*recoit*. L'enfant lit qu'on est respecte quand on rend service. Ce qu'il faut : un geste de politesse qui ne depend d'aucune
action d'Adam (par exemple *"Zoé demande poliment à Adam de lui passer la colle."*), et un indice qui ne parle pas de recevoir.

**Ce qui tient** (verifie, a ne pas defaire au rejeu) :

- `DDE-CE1-1` : mes quatre couples, chacun dans les deux sens, 12 et 12, a l'ecole.
- Aucune forme ne donne la reponse : pas de chevrons ni de majuscule ; la bonne reponse ne reprend pas le verbe de l'enonce (items
  10 et 21 : le mot repris, *idée* et *parle*, est aussi dans un distracteur) ; pas de negation qui la designe. Aucun distracteur
  absurde : famille a (*lui dire la réponse*, *lui donner un cadeau*), un geste aimable qu'un enfant prend pour un devoir ; famille
  c (*emprunter un livre*, *se reposer*), un droit propose comme devoir ; famille f (*passer en premier*, *couper la parole*), une
  envie qu'un enfant prend pour un droit.
- `DDE-CE1-2` hors item 24 : aucun droit gagne ni perdu.
- `TRV-CE1-6` : 15 prenoms, 7 feminins et 8 masculins ; les gestes d'attention et de politesse ne sont pas distribues par genre.
  `EMC-1`, `EMC-6` : aucune adresse a l'enfant, aucun lieu de vie.
- Exactitude : les 22 autres situations, aucune erreur.

**Reserves, non bloquantes** :

1. Item 19, indice *"Pense à l'attitude des autres envers Hugo."* : *attitude* est un mot que l'enfant de CE1 lit mal seul.
2. Pli : non mesure sur ce paquet (`ines-design`).

**Reponses aux points de `sofia-sm`** :

- **J2, paraphrases** : recevables. `DDE-CE1-1` ferme les couples, pas les mots ; chaque formulation est sans ambiguite un cote de
  son couple. La forme affirmative du devoir du couple 4 (*faire attention à elle*) est la bonne : elle evite que la negation
  designe la reponse.
- **J3, `NRC-CE1-1`** : recevable. Les couples 3 et 4 sont les miens ; ce que la ligne de CE1 ajoute au CP, c'est l'association
  d'un droit et d'un devoir, et chaque item donne l'un et demande l'autre. Aucun item ne demande seulement la regle de vie du CP
  (*"Que doit-il faire ?"*) ni seulement un droit de `cp-emc-droits-enfant` (*"Quel droit est illustré ?"*) sans son devoir.
  `cm1-emc-droit-devoir` classe droit ou devoir et dit a quoi ils servent ; aucun item ne le contredit (`CYC-1`).
- **J4, distracteurs** : vraisemblables (voir *Ce qui tient*), sauf M1.
- **J5, *envers* aux items 11 et 21** : recevable. Le couple 2 est reciproque par nature, et l'item ne dit pas que le devoir paie
  le droit ; c'est l'item 24 qui le dit (M2).
- **J6, lisibilite** : *ainsi protégé*, repete a l'identique, devient un patron que l'enfant reconnait ; *chuchote*, *à l'abri du
  danger*, *se sentir en sécurité*, *avec prudence* sont lisibles au CE1. Reserve 1 pour *attitude*.
- **J7, exactitude** : M1 et M2.
- **J8, indices** : une methode, aucun ne contient une proposition ; celui de l'item 14 mene au distracteur (M1) et celui de
  l'item 24 dit *recevoir* (M2).
- **J9, `TRV-CE1-6`** : tenu.

**Ce qui suit** : le lot revient a `sofia-sm` (developpeuse) par `camille-po`. Prix du rejeu : 0,25 si seuls les items 14 et 24
changent.

**`LP6`** : la condition de sa composition (*"un verdict de `LP4` ou `LP5` sans refus"*) est remplie par `LP4` (VALIDE SOUS
RESERVE au rejeu 4) ; ce refus ne la defait pas. Ma recommandation de `arbitrage-nadia-palier-4.md` 3.3 (*"tant que A' n'a pas
montre un lot ecrit dans le palier sans refus"*) n'est pas tenue : `LP4` a ete refuse trois fois, `LP5` l'est ici. C'est une
recommandation, pas une condition : la decision est a `camille-po`, et l'ouverture suit la fusion de `LP5` (`RF5`), qui n'est
pas a moi.

**Je reste la seule garde** : qu'un droit ne se lise jamais comme merite ; qu'aucun item n'ait deux reponses defendables ;
`NRC-CE1-1`.

**Signe** : `nadia-prof`, 2026-10-22.

---

## Rejeu du 2026-10-22 (`nadia-prof`, s57, palier 4, instance A')

**lecture complete : ce1-emc-droit-devoir, 24 items lus** - 2026-10-22, 20:22 (UTC+02:00), lu par `date`, sur la branche
`s57-sofia-emc-ce1-03` a `ebdfa265` ; code et contenu a `5b9bb648` (`git diff --name-only 5b9bb648 ebdfa265` ne nomme que le
rapport du lot). Diff du paquet depuis `edf2b03b` : items 14 et 24, indice de l'item 19, en-tete.

### Verdict du rejeu, 2026-10-22 (`nadia-prof`) : **VALIDE SOUS RESERVE**

Le verdict du premier passage reste ecrit au-dessus ; celui-ci s'y ajoute.

**Done pedagogique prononce sur `5b9bb648`** (sommet `ebdfa265`). `camille-po` peut passer `EMC-CE1-03` a `couvert-valide` et
faire des stories des reserves ci-dessous, non bloquantes.

**Commit joue** : `5b9bb648`, jar construit dans mon worktree detache `recreo-s57-nadia-p4-lp5-jeu` a ce commit (`-pl bootstrap
-am package -DskipTests`, mesure partielle), port 18512, *"Tomcat started on port 18512"* et PID lus dans mon journal, arrete par
PID. Jeu par moi, requetes HTTP : 12 parties de 10 manches, 24 enonces servis sur 24 ; items 14, 19 et 24 servis, indice de
l'item 19 lu a l'ecran apres une erreur.

**Leve** :

- M1, item 14 : distracteurs *emprunter un livre* et *apporter son cahier* ; aucun ne dit ce que le geste de Jade protege.
  *suivre la leçon* est le seul droit du couple 1 propose. L'indice ne mene plus a un distracteur.
- M2, item 24 : *"Zoé demande poliment à Adam de lui passer la colle."* La politesse ne depend d'aucune action d'Adam ; l'indice
  ne parle plus de recevoir. `DDE-CE1-2` tenu.
- Reserve 1, indice de l'item 19 : *attitude* retire.

**Reserves, non bloquantes** :

1. **Neuve, datee du 2026-10-22** : le nouvel indice de l'item 19, *"Pense à ce que les autres font pour Hugo."*, oriente vers
   le distracteur *lui donner un cadeau* (quelque chose qu'on fait *pour* Hugo) plutot que vers *le respecter*. Le distracteur
   n'est pas une reponse defendable : ce n'est pas un refus. Un indice qui parle de la facon de traiter Hugo.
2. Pli : jamais mesure sur ce paquet ; personne ne le mesure aujourd'hui.

**`LP6`** : la condition de sa composition est remplie (`LP4`, puis ce verdict, sans refus). Il peut ouvrir apres la fusion de
`LP5` (`RF5`), qui n'est pas a moi. Ma recommandation de 3.3 n'est pas tenue a la lettre : aucun lot de A' n'a passe sans refus
au premier passage. C'est une recommandation ; la decision est a `camille-po`.

**Prix** : 0,25.

**Je reste la seule garde** : qu'un droit ne se lise jamais comme merite ; qu'aucun item n'ait deux reponses defendables ; que le
couple declare d'un item soit celui de sa situation (le garde de couple est une declaration, pas une lecture : `yanis-qa`).

**Signe** : `nadia-prof`, 2026-10-22.
