# DEC-0055 - `DEC-0045` section 3 amendee : une fenetre de grace peut s'ouvrir sur un `REFUSE`,
# sous un critere verifiable, jamais sans une borne de fermeture obligatoire

- **Type** : amendement d'une regle generale de gouvernance (execution du catalogue face a un
  verdict `REFUSE`)
- **Date** : 2026-08-30
- **Auteur** : camille-po
- **Sprint** : s22 (cloture)
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette decision statue
  sur une regle d'execution du catalogue, pas sur une competence du programme.
- **Statut** : Acceptee

## Contexte - pourquoi je tranche aujourd'hui et pas a s21

`DEC-0045` section 3 prescrit : un verdict `REFUSE` sur une fiche de conformite fait sortir
l'entree correspondante de `catalogue.yaml` **avec effet immediat**, execution routee dans le
meme geste que le verdict, sans attendre une story a planifier.

A s21 (`DEC-0052`, section 8), on m'a explicitement pose la question d'amender cette section pour
un regime de maintien conditionnel, sur la base de deux cas mesures (`cm1-reperes` a s20,
`cp-mot-manquant` a s21). **J'ai refuse**, au motif suivant, que je reprends parce qu'il fonde ce
que je fais aujourd'hui : *"cette conception a besoin de plus de deux cas, ou d'un arbitrage
delibere plutot que d'une regle inferee sous la pression de la cloture d'un sprint"* ; et,
au-dela du compte, que fixer une borne sur l'unique cas ou une fenetre non ecrite avait bien fini
reviendrait a ajuster une regle de gouvernance sur le meme mode que j'avais vu une condition
pedagogique echouer trois fois ce sprint - *"un exemple se revele faux, une propriete se
verifie."*

**J'ai desormais un troisieme cas**, verse au journal de gouvernance hors sprint par `sofia-sm`
le 2026-08-29 et porte comme obstacle ouvert dans `squad/sprints/s22-couverture-maximale-du-
catalogue/review.md` : les trois paquets `REFUSE` du 2026-08-29 (`cm1-multiplications-divisions-
10-100`, `cm1-problemes-durees`, `cm1-comprehension`) sont restes au catalogue par arbitrage
explicite de l'utilisatrice, contre ce que la section 3 prescrit. **Ce troisieme cas est le plus
net des trois** : ce n'est plus une discretion silencieuse d'execution (le second cas), c'est un
arbitrage delibere et assume, rendu par l'autorite la plus haute de ce depot, en connaissance de
la regle qu'il contredit.

### Les trois cas, mis cote a cote

| | `cm1-reperes` (s20) | `mot-manquant-cp` (s21) | trois paquets `S13-2`/`S13-3`/`S17-2` (s22) |
|---|---|---|---|
| Regime applique | Retrait immediat, execute | Retrait retenu, jamais execute | Retrait retenu, jamais execute |
| Qui decide de ne pas executer | - (execution conforme a la section 3) | `sofia-sm`, discretion d'execution **non ecrite** | l'utilisatrice elle-meme, **arbitrage explicite** |
| Correction deja engagee au moment du refus | Non (correction demandee apres coup) | Oui, `lea-dev` deja au travail sur deux items sur trois | Oui, `lea-dev`/`theo-archi` deja au travail sur deux lots sur trois |
| Duree entre le refus et le verdict qui le leve | 16 h 56 min | 1 h 35 min, dont plus d'une heure ou le paquet restait reellement defectueux | environ 24 h (refus le 2026-08-29, trois verdicts le 2026-08-30) |
| Cout materiel mesure | 2 editions de `catalogue.yaml`, 2 `DEC`, 1 test casse | 0 edition, aucun `DEC` d'execution | 0 edition, aucun `DEC` d'execution |
| Enfants exposes a un contenu refuse pendant la fenetre | Non, retrait execute tout du long | Oui, reellement, une fenetre non materialisee | Oui, reellement : **trois jeux defectueux jouables** pendant environ une journee |

**Ce que ces trois cas etablissent, que deux ne pouvaient pas etablir.** Le second cas montrait
qu'une fenetre pouvait bien se passer une fois ; il ne disait rien de si c'etait la discretion qui
avait bien fonctionne ou la chance. Le troisieme cas retire cette ambiguite sur un point precis :
il n'est plus le fait d'une personne qui choisit de ne pas executer une regle qu'elle connait mal
appliquer seule - c'est l'autorite qui a ecrit la regle, par la voix de l'utilisatrice, qui choisit
explicitement de ne pas l'appliquer. **Une regle que sa propre autorite contourne systematiquement
face au meme type de cas n'est plus une regle : elle devient une formalite qu'on invoque puis
qu'on ecarte.** C'est le signal que je n'avais pas a s21 et que j'ai maintenant.

## Decision

### 1. `DEC-0045` section 3 reste ce qu'elle est : le regime par defaut, inchange

Un verdict `REFUSE` fait toujours sortir l'entree du catalogue **avec effet immediat**, execution
dans le meme geste que le verdict, sans attendre une story a planifier. Ce n'est pas ce texte qui
change. Ce qui change est l'ajout d'une exception nommee, cadree et bornee - jamais une porte
ouverte a la discretion silencieuse que le second cas a montree.

### 2. Une fenetre de grace peut s'ouvrir, sous trois conditions cumulatives, toutes verifiables au moment du refus

Une entree peut rester exposee malgre un verdict `REFUSE` **si et seulement si**, au moment ou la
question se pose, les trois conditions suivantes sont toutes vraies et verifiables sans jugement
d'opportunite :

1. **Arbitrage explicite et ecrit**, rendu par l'utilisatrice ou par moi, **au moment de l'ouverture
   de la fenetre** - jamais une non-execution tacite de quiconque fusionne ou execute une decision.
   C'est exactement le defaut du second cas que cette condition ferme : une discretion d'execution
   ne peut plus, a elle seule, tenir lieu d'arbitrage.
2. **Une correction deja engagee, avec un proprietaire nomme et un travail identifiable** (branche,
   story ou commit en cours) au moment ou le refus est prononce - pas une intention de corriger
   "bientot". Les deux cas ou une fenetre a ete tenue (le second, le troisieme) partagent ce fait ;
   aucun cas mesure n'existe ou une fenetre a ete ouverte sans correction deja en cours, et je
   n'en autorise pas un aujourd'hui sur la seule foi du raisonnement.
3. **Le motif residuel est nomme et borne** : le verdict de `nadia-prof` designe un ensemble fini et
   ferme d'elements a corriger (des chaines, des items, un calcul), pas un doute ouvert sur la
   validite d'ensemble du paquet. Si le motif du refus met en cause le principe meme du paquet
   plutot qu'un defaut localise, cette condition n'est pas remplie et la fenêtre ne s'ouvre pas.

### 3. Une borne de fermeture obligatoire, fixee a l'ouverture, jamais decidee apres coup

**La fenetre se ferme au plus tot des deux evenements suivants** :

- le prochain verdict de `nadia-prof` sur le paquet concerne (`VALIDE`, `VALIDE SOUS RESERVE` sans
  reserve bloquante : la fenetre se referme d'elle-meme, le jeu reste expose sur ce nouveau
  fondement ; `REFUSE` maintenu : retrait immediat, sans arbitrage supplementaire, sur la section 3
  telle qu'ecrite) ;
- **une echeance ecrite, datee, fixee dans l'arbitrage qui ouvre la fenetre lui-meme** - jamais
  choisie apres coup sur la base de comment les choses se sont passees. Cette echeance ne peut
  jamais depasser la cloture du sprint en cours si un sprint est ouvert au moment du refus ; **hors
  sprint, elle ne peut jamais depasser 48 heures**. Ces deux plafonds ne sont pas des cibles a
  atteindre : ce sont des maximums, et l'arbitrage peut toujours fixer une echeance plus courte que
  le motif residuel justifie.

**Si l'echeance est atteinte sans qu'un verdict non-`REFUSE` n'ait ete rendu, le retrait s'execute
automatiquement, dans le meme geste que la cloture ou l'echeance qui le declenche, sans attendre
une decision supplementaire.** C'est le mecanisme qui manquait au second cas : une discretion sans
horloge peut toujours se prolonger d'un jour de plus pour un motif toujours defendable, comme
`DEC-0007` l'a deja montre sur un autre sujet, trois sprints de suite. Une borne qui s'execute
d'elle-meme ne connait pas ce mode de defaillance.

### 4. Pourquoi je fixe une regle plutot qu'un autre report

J'ai refuse cet amendement a s21 en nommant precisement ce qui manquait : plus de deux cas, ou un
arbitrage delibere. J'ai les deux aujourd'hui, et le troisieme cas ajoute un fait que les deux
premiers ne portaient pas seuls : **une regle que l'autorite qui l'a ecrite contourne, sans qu'un
critere d'ouverture ni une borne de fermeture n'existent, cesse de proteger ce qu'elle a ete
ecrite pour proteger** - un enfant qui ne rencontre pas un contenu refusable pendant que sa
correction traine sans horloge. Reporter une troisieme fois reviendrait exactement au defaut que
`DEC-0007` nomme : un report qui ne se compte pas devient une direction que personne n'a choisie.
Je ne le reproduis pas ici : ce report-ci est le premier que j'ecris sur ce sujet precis (le
premier passage, a s21, etait un refus motive et date, pas un report silencieux), et je choisis de
trancher plutot que d'en faire un second.

### 5. Ce que cette decision ne fait pas

- Elle ne retire a l'utilisatrice ni a moi la faculte d'arbitrer un cas que ces trois conditions ne
  couvriraient pas. Un arbitrage hors de ce cadre reste possible, mais il doit alors etre ecrit
  comme une exception nommee a cette section, datee, avec son propre motif - jamais silencieux.
- Elle ne rouvre aucun des trois cas passes. Les trois sont clos : `cm1-reperes` par `DEC-0049`/
  `DEC-0050`, `cp-mot-manquant` par `DEC-0052`, les trois paquets de s22 par leurs Done pedagogique
  du 2026-08-30 (`squad/programme/MATRICE-couverture.md`, amendement du 2026-08-30). Je verifie,
  sans le trancher a nouveau, que le troisieme cas aurait rempli les trois conditions de la section
  2 s'il avait existe au moment des faits : arbitrage explicite (oui, l'utilisatrice), correction
  deja engagee (oui, sur deux lots sur trois au moment de l'arbitrage), motif borne (oui, quatre
  chaines, quarante-huit distracteurs, trois questions et un mot - trois ensembles finis et nommes).
  Il n'aurait en revanche pas eu d'echeance ecrite a l'ouverture, puisque cette section n'existait
  pas encore : je ne le lui reproche pas retroactivement, et la cloture du sprint le meme jour que
  le second verdict rend la question theorique.
- Elle ne cree aucun quatrieme statut de matrice ni aucun mecanisme technique neuf dans
  `catalogue.yaml`. Le mecanisme d'execution reste celui deja etabli (absence d'entree, sans
  drapeau), inchange par ce texte.
- Elle ne prononce aucun Done pedagogique et ne rouvre aucun verdict de `nadia-prof`.

## Ce que je route

- **`sofia-sm`** : verser cette section dans la ceremonie de revue de cloture, a cote de la
  verification deja routee par `DEC-0045` section 4 (fiches portant une reserve bloquante croisees
  avec les entrees vivantes de `catalogue.yaml`) - desormais, une entree maintenue malgre un
  `REFUSE` doit pouvoir montrer, a toute cloture, les trois conditions de la section 2 et son
  echeance ecrite. Aucun changement de code n'est demande : c'est une verification de process,
  execution dans la ceremonie deja tenue par `sofia-sm`.
- **`nadia-prof`** : rien de nouveau sur sa porte. Ses verdicts continuent de nommer le motif et son
  cout de levee, comme elle le fait deja ; c'est ce qui alimente la condition 3 de la section 2.

## Impact

- `squad/decisions/produit/DEC-0055-...md` (ce fichier) : cree.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0055` ajoutee.
- `DEC-0045` : non remplacee. Sa section 3 reste le regime par defaut ; ce document lui ajoute une
  exception cadree, jamais une reecriture. Note ajoutee sous sa section 3 renvoyant ici.
- `DEC-0052` : non contredite. Le refus d'amender qu'elle porte en section 8 reste exact pour son
  motif et sa date : il manquait alors un troisieme cas, et je ne l'avais pas.
- `adapters/adapter-content/src/main/resources/catalogue.yaml` : aucun changement, aucune entree
  concernee aujourd'hui par cette decision.
- `squad/programme/MATRICE-couverture.md` : inchangee par ce document.
