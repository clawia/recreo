# DEC-0091 - Objectif et regle de gouvernance de s38 : fermeture visee de `MAT-CE1-03`

- **Statut** : Acceptee. Le detail des reserves de sourcage figure en section 3 : les deux points
  ci-dessous sont formalises sur un enonce recu hors des neuf agents (forme 3), y compris pour
  l'affirmation qu'ils ont ete confirmes par l'utilisatrice - je ne possede pas ses mots a elle,
  cites entre guillemets, et je ne les invente pas pour completer le format que `DEC-0089` a suivi.
- **Date** : 2026-09-22
- **Auteur** : `camille-po`
- **Sprint** : s38 (`s38-fermeture-mat-ce1-03`, avant engagement des lots)
- **Prealable, en vigueur et non rouvert par ce document** : `DEC-0089` (perimetre CE1 ouvert,
  mathematiques seule, CM2 differe). Ce document ne rouvre aucune matiere ni aucun niveau hors de
  ce perimetre ; il porte uniquement sur l'objectif et la gouvernance d'un sprint qui travaille a
  l'interieur de ce perimetre deja accepte.
- **Lien** : `squad/sprints/s38-fermeture-mat-ce1-03/planning.md` (section 2, section 4) ;
  `squad/conformite/FICHE-bandit-manchot-additions-ce1.md` (sections 7.1, 7.2, 8) ;
  `squad/conformite/ARBITRAGE-s37-operande-nul-et-forme-du-tirage-a-l-echelle-100.md` ;
  `squad/programme/mathematiques/competences-ce1.md` (`MAT-CE1-03`) ;
  `squad/README.md`, section 2 bis (les trois formes d'attribution) ; `DEC-0062`.
- **Competence programme visee** : transverse - ce document porte sur l'objectif et la gouvernance
  d'un sprint, pas sur le contenu d'une ligne.

---

## 1. Ce que ce document fait

1. Formalise, en `DEC`, l'objectif operationnel que `planning.md` avait gele sous reserve (section
   2 du planning) : fermer `MAT-CE1-03` a `couvert-valide` a l'issue de s38, par deux volets.
2. Formalise la regle de gouvernance posee par le meme planning pour ce sprint : l'affectation
   nominative du developpement, distincte de l'avis de faisabilite.
3. Nomme, sans les masquer, les limites de sourcage de ce document - voir section 3.
4. Route, dans le meme geste, l'instruction des quatre lots (`docs/backlog/backlog.md`, section
   "Sprint 38") et, pour trois d'entre eux, un arbitrage pedagogique prealable a `nadia-prof`.

## 2. Les deux points, tels que recus

### 2.1 Objectif du sprint

`planning.md` section 2 cite mot pour mot, sous forme 3 (`recu de MASTER, sans source dans le
depot`, 2026-09-22) : *"Objectif du sprint que l'utilisatrice a choisi (option O3 parmi trois
proposees) : fermer MAT-CE1-03 a couvert-valide dans ce sprint."*

Deux volets disjoints en decoulent, egalement gele par le meme planning :

1. **Corrections** `ADD-CE1-R1` et `ADD-CE1-R2` sur le paquet deja livre
   (`bandit-manchot-additions-ce1`) : textes deja ecrits par `nadia-prof`
   (`squad/conformite/FICHE-bandit-manchot-additions-ce1.md`, sections 7.1 et 7.2), aucun nouvel
   arbitrage pedagogique du de ce cote.
2. **Instruction et livraison des trois sous-parties restantes** de `MAT-CE1-03` : soustractions
   dans la limite de 100, complements a la dizaine pris pour eux-memes (forme `47 + ? = 50`),
   moities.

### 2.2 Regle de gouvernance du sprint

`planning.md` section 2 cite mot pour mot, sous forme 3, 2026-09-22 : *"des qu'un avis de
faisabilite est rendu sur une des trois sous-parties, le developpement est affecte nominativement
(marc-dev, lea-dev, ou toi-meme sous ta casquette dev) et non livre directement par theo-archi."*

Fondement ecrit par le planning : la retro de s37, section 2, ou `theo-archi` a livre du code deux
fois de suite hors de son role habituel d'avis de faisabilite, sans qu'un planning ne l'ait decide.
**Consequence pour s38** : un avis de faisabilite rendu par `theo-archi` sur un des quatre lots ne
vaut jamais livraison ; `sofia-sm` affecte nominativement le developpement qui en decoule
(`marc-dev`, `lea-dev`, ou elle-meme sous sa casquette dev) au daily suivant.

## 3. Ce que ce document sourci, et ce qu'il ne sourci pas - a lire avant de s'appuyer dessus

**Les deux points de la section 2 sont recus sous forme 3** (`squad/README.md`, section 2 bis) :
enonces hors des neuf agents, cites mot pour mot, non fondes en eux-memes tant qu'ils ne sont pas
confirmes par l'utilisatrice sous forme citable (forme 2 - ses mots a elle, entre guillemets, et la
date).

L'instruction qui m'a route ce sprint affirme, en outre, que l'utilisatrice a confirme ces deux
points le 2026-09-22, "dans l'echange qui a produit ce planning". **Je recois cette affirmation par
le meme canal que les deux points eux-memes - hors des neuf agents - et elle ne me donne pas les
mots de l'utilisatrice entre guillemets.** L'ecrire comme une confirmation forme 2 reviendrait a
fabriquer une citation que je ne possede pas, exactement ce que `squad/README.md` section 2 bis
interdit ("choisir entre deux formulations reviendrait a fabriquer la citation que la convention
protege") et ce que ce depot a deja sanctionne a plusieurs reprises (une mesure recopiee plutot que
prise, une reference qui ne pointe vers rien). Je ne le fais pas.

**Ce que je retiens malgre cette reserve, et pourquoi ce document reste Acceptee plutot que
Proposee.** Contrairement a une regle pedagogique ou un critere d'acceptation qui fixerait un
contenu (ou la forme 3 ne fonde jamais rien, sans exception), ce document formalise un **objectif
et une regle de process de sprint**, du meme ordre que les arbitrages de strategie deja acceptes
sur enonce narre de l'utilisatrice sans citation entre guillemets (`DEC-0083`, `DEC-0085`,
`DEC-0086` : "Type : arbitrage de l'utilisatrice, enregistre et non rouvert", sans guillemets dans
le corps du document). Le precedent de `DEC-0089` - question posee mot pour mot, reponse citee mot
pour mot - reste la forme la plus rigoureuse et je ne pretends pas l'avoir atteinte ici : je le dis
explicitement pour que personne ne lise ce document comme s'il l'avait fait.

**Ce que cela change concretement, en aval de ce document** : les quatre lots de la section 4 sont
instruisibles et routables des ce document ecrit - c'est ce que `planning.md` demandait comme
condition pour livrer un lot. Si l'utilisatrice infirme ou ajuste l'un des deux points de la
section 2, ce document est amende ou remplace, jamais silencieusement contourne - meme clause que
`DEC-0089`, section 6.

## 4. Ce que ce document route

- **Instruction des quatre lots**, dans `docs/backlog/backlog.md`, section "Sprint 38" : fait dans
  le meme geste que ce document (voir le commit qui l'accompagne).
- **Arbitrage pedagogique prealable de `nadia-prof`**, avant tout avis de faisabilite de
  `theo-archi`, sur les trois sous-parties restantes de `MAT-CE1-03` (soustractions, complements a
  la dizaine pris pour eux-memes, moities) : aucune des trois n'est couverte par
  `ARBITRAGE-s37-operande-nul-et-forme-du-tirage-a-l-echelle-100.md`, qui porte exclusivement sur
  les additions et le dit explicitement ("Il ne dit rien des soustractions de MAT-CE1-03, hors
  perimetre de S37-1"). Motif detaille dans chaque story, section "Sprint 38" du backlog.
- **Avis de faisabilite de `theo-archi`**, sur chacun des quatre lots, une fois l'instruction faite
  et, pour les trois sous-parties, une fois l'arbitrage pedagogique prealable rendu.
- **Affectation nominative du developpement par `sofia-sm`**, jamais par `theo-archi` lui-meme -
  section 2.2.

## 5. Ce que ce document ne fait pas

- Il ne fixe aucune borne de tirage, aucune forme de distracteur, ni aucun contenu pedagogique pour
  les trois sous-parties : ce travail reste entierement a `nadia-prof`.
- Il ne choisit aucun mecanisme technique (paquet statique ou generatif, reutilisation d'une classe
  existante ou non) : ce travail reste a `theo-archi`.
- Il ne rouvre ni `DEC-0089`, ni CM2, ni `MAT-CE1-18` (sens de la division), explicitement hors
  perimetre de s38 (`planning.md`, section 2).
- Il ne prononce aucun Done, technique ou pedagogique.
- Il ne pretend pas detenir une confirmation forme 2 de l'utilisatrice qu'il n'a pas - section 3.
