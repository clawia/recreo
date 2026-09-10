# DEC-0086 - Strategie de s34 : dernier sprint de la fenetre du Plan 2, plafond 6 lignes pour
# 12,0, attente 2 a 4 ; la projection ponctuelle du registre de dette est abandonnee au profit
# d'un plancher ; le point de controle poste/story qui manquait est ecrit ; application de `RES-4`
# au vivier

- **Type** : execution du Plan 2 (`DEC-0083`) appliquee a s34, dernier sprint de sa fenetre ;
  reprise des chiffres et clauses de l'arbitrage prealable de `nadia-prof`, sans recalcul ; deux
  corrections de methode que mon propre recalcul de dette impose.
- **Date** : 2026-09-10.
- **Auteur** : `camille-po`.
- **Sprint** : s34 (ouverture).
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette decision de
  strategie ne ferme et ne rouvre aucune ligne elle-meme ; les competences ligne a ligne sont
  nommees dans `docs/backlog/backlog.md` et dans `squad/programme/MATRICE-couverture.md`.
- **Statut** : Acceptee, amendee le 2026-09-10 (meme jour, section 12 - arbitrage de l'utilisatrice
  engageant l'entree minimale du palier 2, et trois corrections issues de l'avis de faisabilite de
  `theo-archi` pour s34).
- **Lien** :
  `squad/conformite/ARBITRAGE-PREALABLE-s34-le-motif-transverse-l-objet-d-une-reserve-et-le-vivier-de-quatre-refus.md`
  (`nadia-prof`, non commite a la date de cette decision, chiffres et clauses repris sans etre
  recalcules), `squad/decisions/produit/RECALCUL-registre-de-dette-ouverture-s34.md` (`camille-po`),
  `DEC-0083` (Plan 2, fenetre de trois sprints, borne, palier 2), `DEC-0084` (cloture de s32),
  `DEC-0085` (strategie de s33, premier report du palier 2), `ADR-0081` (decision de grain confiee
  a `camille-po` et `nadia-prof` ensemble), `ADR-0085` (registre d'identifiants), `ADR-0089`
  (references de contenu), `ADR-0080` (palier 2, statut *Propose*), `squad/sprints/
  s33-plan-2-tenu-le-palier-2-differe-a-s34/review.md`, `RAPPORT.md`,
  `squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/avis-faisabilite-theo.md` (`theo-archi`, non commite a la date de cette
  decision, mesures et chiffres repris sans etre recalcules), `squad/programme/MATRICE-couverture.md`,
  `docs/backlog/backlog.md` (section "Backlog ouvert pour s34 (composition de `DEC-0086`)" et
  section "Backlog ouvert pour s34 - le palier 2 engage a son entree minimale").

---

## 1. L'etat d'entree, repris et non recalcule

**69 `couvert-valide`, 1 `en cours`, 68 `non couvert`, 71 `non couvrable en l'etat`. Denominateur
atteignable 138. 69/138 = 50,0 %.** Ce compte est celui de la review de s33, recompte
independamment sur les dix-huit tableaux de la matrice, confirme par l'arbitrage prealable de
`nadia-prof` (section 1) sans etre recalcule ici.

## 2. Le registre de dette a l'ouverture de s34 : 9,0, plus trois familles non chiffrees

Mon recalcul (`RECALCUL-registre-de-dette-ouverture-s34.md`) etablit un registre chiffrable de
**7,5** a partir de la seule methode que `nadia-prof` m'a laissee (un paquet est PAYE quand la
totalite de ses codes connus sont leves et que l'increment qui les leve n'est pas refuse ; sinon il
reste NON PAYE en entier - je choisis le binaire par paquet, faute d'une regle de conversion
fractionnaire, et je le dis pour qu'on puisse le contester). `nadia-prof` rend les trois tarifs que
j'avais laisses ouverts (troisieme passage sur `cp-eps-regles-de-securite`, 0,5 ; troisieme passage
sur `cp-eps-regles-jeu-collectif`, 0,5 ; `cp-emc-exclusion` au tarif de lot plutot qu'au tarif de
rejeu, `PAR-15`, +0,5) : **le registre passe de 7,5 a 9,0.**

**Le registre etait de 7,5 a l'ouverture de s32, de 9,0 a l'ouverture de s33, et il est de 9,0 a
l'ouverture de s34.** L'egalite des deux derniers chiffres est une coincidence, pas une stabilite :
s33 a ferme trois lignes, solde integralement trois paquets anciens (`cm1-histoire-geographie`,
`cp-eps-regles-de-securite`, `cp-eps-regles-jeu-collectif`) et rouvert une dette neuve de meme
montant sur chacun des trois, pendant que sept postes independants de toute fermeture de ligne pour
un total de 4,0 (`cm1-anglais`, `cp-emc-emotions-autrui`, `cp-emc-formules-de-politesse` jamais
touches ; `cp-emc-regles-de-vie`, `cp-emc-permis-interdit` touches mais soldes a moitie) restent
comptes non payes en entier sous ma methode binaire.

**Trois familles restent non chiffrees et ne bougent ni a la hausse ni a la baisse ce sprint** :
`CONS-1` (reliquat sur `cm1-histoire-geographie`, jamais tarife separement), `CONS-3` (non mesuree),
`CONS-4` (dont le grain se decide dans cette meme decision, section 7, mais dont la reecriture des
consignes reste due a `nadia-prof` sans etre chiffree comme une dette de registre).

## 3. La borne, sa composition, et ce qui n'a pas encore de story

**La borne reste 12,0** : trois instances a 4,0 chacune (`PAR-9`), plafond individuel dur 5,0
(`PAR-8`), reprise sans reouverture. `nadia-prof` ne la rediscute pas et je ne la rediscute pas non
plus.

**Composition, avec la distinction que le recalcul de la section 2 rend necessaire** : un poste
n'est compte comme engage que s'il porte une story dans `docs/backlog/backlog.md` a la date de
cette decision. Un poste sans story n'est pas un engagement, c'est une place disponible - c'est
exactement la lecon de mon propre recalcul (section 4 ci-dessous).

| Poste | Story | Instance proposee | Prix | Ligne |
|---|---|---|---|---|
| `HG-CM1-12`, reprise (`HG-R17` bloquante, `HG-R13` a `HG-R18`) | `S34-HG-12` | A | 0,5 | +1 |
| `cm1-anglais`, quatre reserves (`ANG-R3` a `R6`) | `S34-ANG-CORR` (reprise de s33, inchangee) | A | 0,5 | 0 |
| Passe transverse de l'instance d'accumulation | `S34-TRANSVERSE-1` (reprise de s33, inchangee) | A | 0,5 | 0 |
| Decoupage de `S33-CONS-4` en quatre entrees | `S34-CONS-4` | A | 1,0 | 0 |
| `EMC-CP-07`, cinquieme passage, perimetre corrige | `S34-EMCCP07` | B | 0,5 | +1, conditionnel |
| `cp-emc-exclusion`, tache B refondue (tarif de lot, `PAR-15`) | `S34-EXC-TACHEB` | B | 1,0 | 0 |
| `EPS-CP-04`, reprise (`SEC-R4` bloquante, `SEC-R5`) | `S34-EPS-SEC` | C | 0,5 | +1 |
| `EPS-CP-08`, reprise (`JEU-R9` bloquante, `JEU-R10` a `R12`) | `S34-EPS-JEU` | C | 0,5 | +1 |
| Lot de l'effort et du corps (`EPS-CP-10`, `EPS-CP-11`) | `S34-EPS-CORPS-EFFORT` (reprise de s33, ajustee) | C | 1,5 | +2 |
| Verification du remede de `SCO-1`, instruction 1 de `DEC-0061` rendue (`D7`) | `S34-SCO1-REMEDE` | A | 0,5 | 0 |
| **Sous-total engage (story ecrite a la date de cette decision)** | | | **7,0** | **+6** |
| `cp-emc-regles-de-vie` (`REG-R3` a `R5`) | aucune | non affecte | 0,5 | 0 |
| `cp-emc-permis-interdit` (`PER-R2`, `R3`) | aucune | non affecte | 0,5 | 0 |
| `cp-emc-emotions-autrui` (`EMO-R3`) | aucune | non affecte | 0,5 | 0 |
| `cp-emc-formules-de-politesse` (`POL-R3`, `R4`) | aucune | non affecte | 0,5 | 0 |
| Residu du groupe 2a (cinq paquets, sciences et EMC CM1) | aucune | non affecte | 2,5 | 0 |
| Groupe 2b (`CONS-2`, `SAI-R1`, `ERR-R1`) | aucune | **deconseille par `nadia-prof`, non repris** (section 8) | 1,0 | 0 |
| **Sous-total non engage a l'ouverture** | | | **5,5** | **0** |

**Le total nominal (12,5) depasse legerement la borne de 0,5, et ce n'est pas une erreur** : la
borne (`PAR-8`, `PAR-9`) est un plafond, pas une obligation de tout depenser a la fois. Le
sous-total non engage est la dette reelle du depot, plus grande que ce qu'une seule borne de 12,0
peut absorber en un sprint - c'est exactement ce que la section 6 nomme comme la vraie contrainte
de s34, et non un defaut d'arithmetique de cette table. Le point de controle de la section 5 est
l'endroit ou ce demi-point d'ecart se resout : au planning, `sofia-sm` choisit, parmi le
sous-total non engage, ce qui recoit une story dans la marge restante (0,5 au maximum sous la
borne de 12,0 si le sous-total engage ferme integralement) et ce qui reste explicitement libere.

**Plafond arithmetique : 6 lignes, pour 12,0 a 12,5 engage selon la marge retenue au planning.**
69 + 6 = 75/138 = **54,3 %**.

**Attente raisonnable, reprise de `nadia-prof` sans recalcul : 2 a 4 lignes.** 69 + 2 = 71/138 =
**51,4 %** ; 69 + 4 = 73/138 = **52,9 %**. Motif ligne par ligne (arbitrage prealable, section 8) :
`HG-CM1-12` a 1 (le meme lot a deja ecrit dix distracteurs serieux sur dix a cote) ; `EMC-CP-07` a
1 ou 0 selon un fait nommable (section 5 ci-dessous) ; `EPS-CP-04` et `EPS-CP-08` a 0-1 penchant 1
(les deux causes anterieures sont reparees a leur source) ; le lot du corps a 0-1 penchant 0
(premier contact d'`EPS-2`, `EPS-3` et de la frontiere corps-et-effort avec du contenu reel, et le
seul taux mesure de l'EPS au premier contact est zero sur deux). L'ecart entre 2 et 4 mesure si la
discipline neuve de `nadia-prof` (`RES-1` a `RES-4`, section 5) porte son objet et pas seulement sa
lettre - c'est la premiere mesure de ce changement, et je ne le presume pas acquis.

## 4. Ce que mon recalcul impose de changer dans la methode de projection : je l'abandonne

**C'est la deuxieme projection de dette dementie de suite.** `DEC-0083` projetait 0,5 a la cloture
de s34 en supposant s32-s34 se comporte comme prevu ; `DEC-0085` projetait 2,5 a la cloture de s33
si le plafond de 9 lignes etait atteint. Aucune des deux ne tient : le registre est reste a 9,0.

Mon recalcul identifie deux mecanismes distincts, et aucun des deux n'est corrige en resserrant un
chiffre - ils exigent chacun un changement de methode :

1. **Aucun paquet reaudite dans ce depot n'a jamais solde sa dette a zero.** Les trois paquets
   integralement payes a s33 (`cm1-histoire-geographie`, `cp-eps-regles-de-securite`,
   `cp-eps-regles-jeu-collectif`) ont chacun rouvert une dette neuve de meme montant dans le
   meme geste qui les soldait. Une projection qui compte un paquet reaudite comme "solde" des que
   ses codes connus sont leves surestime donc systematiquement la descente, par construction et
   non par malchance.
2. **Un poste finance par une composition budgetaire n'est pas un poste engage.** A s33, sur les
   sept postes que la projection comptait comme "independants de la fermeture d'une ligne" (6,5 sur
   9,0), trois n'ont jamais recu de story et deux autres n'ont regle qu'une partie de leur
   registre : le paiement reel sur ces cinq postes a ete de 0,0, contre 2,5 supposes.

**Je n'ecris pas de chiffre de projection de descente pour s34, et je le fais pour un motif, pas
par prudence rhetorique.** Une methode qui a echoue deux fois de suite sur le meme mecanisme ne se
corrige pas en resserrant sa marge d'erreur : elle se remplace par une methode qui ne fait pas la
promesse qu'elle ne peut pas tenir. A partir de cette decision :

- **Le registre de dette n'est plus projete a l'ouverture d'un sprint.** Il est seulement mesure a
  la cloture, a partir des verdicts reels (comme fait en section 2 ci-dessus pour l'ouverture de
  s34, a partir des verdicts de s33). Ce que j'ecris a l'ouverture d'un sprint est **un plancher** :
  le registre ne descendra pas en dessous de sa valeur d'ouverture moins la somme des postes qui
  portent une story ET dont la ligne ferme reellement - jamais une valeur cible.
- **Un poste sans story a l'ouverture d'un sprint n'entre dans aucun calcul de descente.** C'est
  exactement ce que `PAR-16` (nouvelle clause de `nadia-prof`, section 6 de son arbitrage) fait de
  son cote pour son propre budget d'audit ; je fais l'equivalent cote produit, pour le registre de
  dette : le sous-total "non engage" de la table de la section 3 (5,5) ne baissera pas a la
  cloture de s34, sauf si une story lui est ecrite avant que le point de controle de la section 5
  ne se declenche.
- **Je ne comble pas le trou de gouvernance sur le reaudit d'un paquet ferme.** `nadia-prof` l'a
  nomme deux fois (`DEC-0085`, section 7 ; arbitrage prealable de s34, section 6) et refuse de le
  combler par une clause de son propre document, parce que cinq des sept reserves neuves de s32
  venaient de ses propres comptes incomplets - une clause qui ferait payer a l'equipe le
  recomptage de ses propres barres serait le durcissement qu'elle s'interdit. Je ne le comble pas
  non plus par une regle produit : c'est une question de gouvernance qui depasse une decision de
  strategie de sprint. Ce que je fais a la place est nomme ci-dessus : je cesse de projeter un
  chiffre que ce trou rend structurellement faux.

## 5. Le point de controle qui manquait : verification poste/story a la cloture du planning

**Le trou trouve par mon recalcul** : un poste finance par la composition d'une decision de
strategie ne fait baisser aucune dette tant qu'aucune story ne le porte, et `sofia-sm` avait deja
nomme un de ces postes sans story au planning de s33 (`cm1-anglais`, section 10 du planning) sans
que ce signal ne remonte a la projection de `DEC-0085`. Deux autres postes du meme groupe
(`cp-emc-emotions-autrui`, `cp-emc-formules-de-politesse`) n'ont meme pas ete detectes comme risque
a ce moment-la.

**Le point de controle, ecrit pour de bon** :

- **Moment** : a la cloture de la ceremonie de planning de `sofia-sm`, avant que le sprint ne
  commence a s'executer - c'est-a-dire avant le premier daily, pas a la review.
- **Qui le tient** : `sofia-sm`, dans `planning.md` lui-meme. Elle verifie, poste par poste, la
  table de composition de la `DEC` de strategie en vigueur (ici, la section 3 ci-dessus) : chaque
  poste porte-t-il une story dans `docs/backlog/backlog.md`, et cette story porte-t-elle une
  affectation nominative (instance, developpeur) ?
- **Ce qui se passe quand il declenche** : un poste qui n'a ni story ni affectation a la cloture du
  planning est ecrit explicitement dans `planning.md` sous deux issues, jamais une troisieme
  (silencieuse) : (a) `sofia-sm` lui ecrit une story et l'affecte avant de clore le planning, ou
  (b) elle l'ecrit "non engage, budget libere" avec la clause `PAR-16` citee comme motif, et ce
  budget redevient disponible pour un autre poste du meme sprint. Dans les deux cas, le fait est
  ecrit dans le fichier de ceremonie - jamais seulement constate a la review, comme cela a ete le
  cas pour `cm1-anglais`, `cp-emc-emotions-autrui` et `cp-emc-formules-de-politesse` a s33.
- **Ce que cela change en aval** : la review et le recalcul de dette de la prochaine ouverture de
  sprint lisent directement cette section de `planning.md` plutot que de reconstituer, apres coup
  et par verdict interpose, quels postes ont reellement ete engages - ce que mon recalcul de la
  section 2 a du faire a la main, faute de ce point de controle a s33.

**Je route ce point de controle a `sofia-sm`** pour qu'elle l'inscrive dans la structure de
`planning.md` (section 4 de `squad/README.md` decrit deja ce que `planning.md` porte
obligatoirement ; c'est a elle, pas a moi, d'y ajouter cette section et de l'amender si
necessaire). Je n'impose pas de forme de tableau : je pose le moment, le proprietaire et le
declenchement.

## 6. `RES-4` appliquee au vivier de s34 : ce qui est engageable et ce qui ne l'est pas encore

`RES-4` (nouvelle, `nadia-prof`, 2026-09-10) : une reserve sans objet ecrit n'est pas engageable, le
developpeur qui la recoit la renvoie, et le sprint ne la compte pas comme due. Elle n'est pas
retroactive - une reserve ecrite avant le 2026-09-10 ne devient pas inengageable faute d'objet.

**Six postes du vivier de s34 sont reexprimes par `nadia-prof`, dans son arbitrage prealable
lui-meme, au format `RES-1` complet - constat, objet, mode de defaillance, perimetre mesure sur
l'etat d'arrivee, regime.** Ce sont exactement les six postes que j'engage avec une story dans la
section 3 : `HG-CM1-12` (section 5.1), `EMC-CP-07` (5.2), `EPS-CP-04` (5.3), `EPS-CP-08` (5.4), la
refonte de la tache B de `cp-emc-exclusion` (5.5), et le lot de l'effort et du corps (5.6). **Six
sur six lignes prioritaires du vivier sont donc engageables en l'etat**, et je reprends leur objet
mot pour mot dans les stories ci-dessous plutot que de le reformuler - c'est le motif meme de
`RES-1` : une reserve qui degenere en texte a recopier plutot qu'en objet a servir a deja coute un
sprint entier a `EMC-CP-07`. **Un septieme poste engage, la verification du remede de `SCO-1`
(section 4.1 de l'arbitrage), n'est pas une reserve au sens de `RES-1`** - c'est une instruction de
`DEC-0061` que `nadia-prof` rend, avec son propre constat, son propre mode de defaillance refuse
d'avance (la troisieme voie, le remelange) et son propre prix - et `RES-4` ne s'y applique donc pas
faute d'objet a lui demander : elle porte deja tout ce que `RES-1` exigerait d'une reserve.

**Les postes du sous-total non engage (section 3) ne sont pas reexprimes au format `RES-1` dans ce
document** : `cp-emc-regles-de-vie`, `cp-emc-permis-interdit`, `cp-emc-emotions-autrui`,
`cp-emc-formules-de-politesse`, les cinq paquets du residu du groupe 2a. `RES-4` ne les rend pas
inengageables au sens strict - elle n'est pas retroactive - mais je choisis de ne pas leur ouvrir de
story tant qu'ils ne portent pas un objet au format `RES-1`, pour la meme raison qui a fait echouer
`EMC-CP-07` trois fois : une story ecrite contre un texte plutot que contre un objet se corrige a la
lettre et rate le fond. **Qui paie ce cout** : `nadia-prof`, dans son propre budget d'audit, comme
elle vient de le faire gratuitement pour les six postes prioritaires - ce n'est pas une story de
developpement, ce n'est pas facture au sprint qui la recoit, et ce n'est pas une condition que
j'invente : c'est la consequence directe qu'elle a elle-meme tiree de `RES-1` a `RES-4`.

## 7. Le grain de `S33-CONS-4` : decision rendue, executee sous `S34-CONS-4`

`ADR-0081` confiait cette decision a `camille-po` et `nadia-prof` ensemble. Les deux moities sont
rendues : ma position (decoupage par famille de tache, arbitrage-produit-camille.md de s33, section
1) et la correction de `nadia-prof` (trois taches reelles, pas cinq - situer dans le temps, nommer
en histoire, nommer en geographie, lire un document - arbitrage prealable de s34, section 4.2).
**La decision de grain est close.** `S33-CONS-4` est remplacee par `S34-CONS-4` (section 3
ci-dessus, `docs/backlog/backlog.md`), qui execute le decoupage en quatre entrees sans reouvrir le
choix du regroupement.

**Ce que je ne fais pas** : je n'ecris aucune des quatre consignes a la place de `nadia-prof` -
elle l'a explicitement refuse (`RES-2`) et je ne la contourne pas.

## 8. Ce que je ne reprends pas a s34, en suivant `nadia-prof`

`nadia-prof` recommande de ne pas reprendre cinq postes a s34 (arbitrage prealable, section 5.7).
Je la suis sur les cinq, pour son motif :

- **`REG-R3`** (genre de l'adulte d'ecole) : son propre chiffre etait faux (17 contre 6, pas 17
  contre 1) et le compte va bouger tout seul avec la reecriture de `cp-emc-exclusion` - depenser
  sur un chiffre qui va changer n'a pas de sens avant qu'il soit recompte.
- **`JEU-R12`** (genre des personnages qui enfreignent une regle) : population trop petite pour une
  barre, voyage gratuitement avec `S34-EPS-JEU`.
- **`MEL-R4`** : chiffree 0,0, ne justifie pas un rejeu seule.
- **Le groupe 2b** (`CONS-2`, `SAI-R1`, `ERR-R1`) : la decision de grain de la section 7 en change
  la forme, le reprendre avant serait payer deux fois.
- **`CONS-3`** : toujours non chiffree, ne s'estime pas.

## 9. Le palier 2 : pas de second report silencieux

**s34 est le dernier sprint de la fenetre que `DEC-0083` a ouverte pour le Plan 2 (s32 a s34).**
Le financement de son entree minimale (`QLM-CP-11`, `QLM-CP-14`, 2,0) a ete arbitre par
l'utilisatrice le 2026-09-08 ; `DEC-0085` en a fait le **premier report**, de s33 a s34, motive par
`nadia-prof` (`L-c` prend un developpeur pour le sprint entier, ce qui retirerait un troisieme
producteur de contenu au plafond de 9 lignes de s33).

`nadia-prof` nomme, dans son arbitrage prealable de s34 (section 9), que le plafond de 6 lignes de
ce sprint suppose lui aussi trois producteurs de contenu, et que **le choix d'engager `L-c` contre
une part de ce plafond est un arbitrage de `camille-po`**, distinct de la question de son
dimensionnement exact, qui reste a `sofia-sm`. Mais la question "engage-t-on `L-c` maintenant,
quitte a reduire le plafond de contenu" est exactement celle que l'utilisatrice a deja tranchee une
fois, a s33 - elle a vu l'option d'engager `L-c` avec un plafond rechiffre a la baisse et ne l'a pas
retenue (`DEC-0085`, section 1). Je n'ai pas d'element neuf qui me donne autorite pour trancher a sa
place une question qu'elle a explicitement arbitree la derniere fois qu'elle s'est posee dans les
memes termes.

**Je ne reporte donc pas silencieusement une troisieme fois : je route la question a
l'utilisatrice, explicitement, avant le planning de s34**, avec le fait nomme sans l'adoucir - si
elle ne tranche pas avant que `sofia-sm` cloture son planning, le defaut (le palier 2 est reporte
une seconde fois, sous les memes trois identifiants de story, `S33-PAL2-TROU-A`,
`S33-PAL2-TROU-B`, `S33-PAL2-1`) est reporte a s35 par defaut, et cette decision le compte comme
tel plutot que de le laisser glisser sans etre nomme. **Ce que je note pour la suite, sans le
trancher ici** : la fenetre de trois sprints de `DEC-0083` se clot avec s34 quelle que soit l'issue
du palier 2, et une strategie neuve, au-dela du Plan 2, sera due a la cloture de s34 - ce n'est pas
l'objet de cette decision.

## 10. Ce que je decide

1. **La borne de s34 reste 12,0** (`PAR-8`, `PAR-9`), non rediscutee. Plafond arithmetique 6
   lignes (75/138 = 54,3 %), attente raisonnable 2 a 4 lignes (71 a 73/138 = 51,4 % a 52,9 %),
   reprises de `nadia-prof` sans recalcul (section 3).
2. **Sept stories sont ouvertes ou reprises dans `docs/backlog/backlog.md`, section "Backlog
   ouvert pour s34 (composition de `DEC-0086`)"** : `S34-HG-12`, `S34-EMCCP07`, `S34-EPS-SEC`,
   `S34-EPS-JEU`, `S34-CONS-4`, `S34-EXC-TACHEB`, `S34-SCO1-REMEDE` (instruction 1 de `DEC-0061`
   rendue). Trois stories deja ecrites a la cloture de s33 sont confirmees dans la borne, sans
   modification de fond : `S34-ANG-CORR`, `S34-TRANSVERSE-1` ; une quatrieme, `S34-EPS-CORPS-EFFORT`,
   est ajustee pour porter `EPS-8` et le refus d'avance numero 8 (moment de l'effort) nommes par
   `nadia-prof` dans son arbitrage de s34.
3. **La methode de projection ponctuelle du registre de dette est abandonnee** (section 4) : plus
   aucune decision de strategie de sprint n'annoncera de chiffre de descente cible. Le registre
   est mesure a la cloture, a partir des verdicts reels, et rapporte a l'ouverture du sprint suivant
   comme un plancher, jamais comme une cible.
4. **Un point de controle poste/story est cree a la cloture du planning**, tenu par `sofia-sm`,
   route dans `planning.md` (section 5 ci-dessus).
5. **`RES-4` est appliquee au vivier** : six postes prioritaires sont engageables en l'etat et
   recoivent une story ; les postes du residu du groupe 2a et le reste de l'EMC CP n'en recoivent
   pas tant qu'ils ne portent pas un objet au format `RES-1`, ecrit par `nadia-prof` a son propre
   cout (section 6).
6. **Le grain de `S33-CONS-4` est clos** ; elle est remplacee par `S34-CONS-4` (section 7).
7. **Cinq postes recommandes par `nadia-prof` ne sont pas repris a s34** (section 8) :
   `REG-R3` seule, `JEU-R12` seul, `MEL-R4`, le groupe 2b, `CONS-3`.
8. **Le palier 2 n'est pas reporte silencieusement une seconde fois** : la question de l'engager a
   s34, dernier sprint de la fenetre du Plan 2, est explicitement routee a l'utilisatrice avant le
   planning (section 9). A defaut d'arbitrage avant la cloture du planning, le report est compte
   comme un second report de ce lot, nomme comme tel.

## 11. Ce que cette decision ne fait pas

- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` et n'en ferme aucune
  ligne : les statuts ne bougent qu'au verdict de `nadia-prof`.
- Elle ne prononce aucun Done pedagogique.
- Elle ne recalcule aucun chiffre de `nadia-prof` : les tarifs, les objets, les modes de
  defaillance et les attentes de son arbitrage prealable de s34 sont repris tels qu'elle les
  ecrit, sans etre revises.
- Elle ne comble pas le trou de gouvernance sur le reaudit d'un paquet ferme (section 4) : elle
  cesse de projeter un chiffre que ce trou rend faux, elle ne le referme pas.
- Elle n'ecrit aucune des quatre consignes de `S34-CONS-4` ni le texte de la tache B de
  `S34-EXC-TACHEB` : ce texte revient a qui l'ecrit dans la story elle-meme, jamais dicte a
  l'avance par `camille-po`.
- Elle ne fixe l'affectation nominative d'aucune instance ni d'aucun developpeur, y compris pour
  les postes du sous-total non engage de la section 3 : c'est le travail du planning de s34, par
  `sofia-sm`, sous le point de controle de la section 5.
- Elle ne tranche pas si le palier 2 est engage a s34 : elle route la question a l'utilisatrice et
  compte le defaut d'arbitrage comme un second report si aucune reponse n'arrive avant la cloture
  du planning (section 9).
- Elle ne decide d'aucune question technique ou d'architecture : la sortie technique retenue pour
  `EMC-CP-07` (deplacement d'item ou item ternaire supplementaire, choix pedagogique route a
  `nadia-prof` - corrige a la section 12.4.3), la forme technique du decoupage en quatre entrees de
  `S34-CONS-4`, et le chemin de developpement du palier 2 restent au ressort de `theo-archi`.

## 12. Amendement du 2026-09-10 (meme jour) - l'utilisatrice engage l'entree minimale du palier 2,
## et trois corrections issues de l'avis de faisabilite de `theo-archi` pour s34

Cette section amende la decision ci-dessus. **Elle ne l'efface pas** : les sections 1 a 11
restent l'etat que j'ai arrete a l'ouverture de s34, et c'est contre elles que je mesure ici ce qui
change - meme forme que `DEC-0083`, section 13, pour son propre amendement du meme jour.

### 12.1 L'arbitrage de l'utilisatrice, mot pour mot, rendu le 2026-09-10

*Elle engage l'entree minimale du palier 2 - un seul lot de designation, `QLM-CP-11` +
`QLM-CP-14`, 2,0 de la borne pour deux lignes fermees - a s34. Elle avait ete financee a s33 et
jamais engagee ; cette decision laissait 5,5 sur 12,0 non affectes, et les 2,0 y sont pris.*

**Ce que cet arbitrage repond.** La section 9 ci-dessus routait explicitement a l'utilisatrice la
question qu'elle vient de trancher : le palier 2 est-il engage a s34, dernier sprint de la fenetre
du Plan 2, quitte a prendre une part du plafond de contenu sur le poste `L-c` (indivisible, un
developpeur, un sprint entier) ? Elle avait deja vu cette meme question a s33 et ne l'avait pas
retenue (`DEC-0085`, section 1) ; elle la retient a s34. **La question n'a pas de reponse par
defaut cette fois** : ce n'est donc pas un troisieme report, silencieux ou non - c'est l'issue que
la section 9 nommait comme la seule alternative au report compte.

### 12.2 Ce que cela change a la composition de la section 3 : le sous-total engage passe de 7,0 a
### 9,0, le sous-total non affecte de 5,5 a 3,5

**La section 3 n'est pas reecrite** ; la ligne ci-dessous s'y ajoute, et les deux sous-totaux
qu'elle porte sont recalcules avec elle.

| Poste | Story | Instance proposee | Prix | Ligne |
|---|---|---|---|---|
| Lot de designation `QLM-CP-11` + `QLM-CP-14` (T6, `L-e` du chemin de jeu, conditionne par `L-a` a `L-d`) | `S33-PAL2-1` | non affectee - pas de terrain nomme, comme `POS-R3` et `FOP-R4`/`DRO-R4` | 2,0 | +2 |

**Sous-total engage, apres amendement : 9,0, pour +8 lignes** (7,0 et +6 lignes a la section 3,
plus 2,0 et +2 lignes ci-dessus). **Sous-total non affecte, apres amendement : 3,5** - les 2,0 du
lot de designation sont pris sur ce sous-total, qui n'etait deja qu'une reserve non engagee et non
une obligation de depense (section 3, meme logique). Aucune des six lignes de ce sous-total
(`cp-emc-regles-de-vie`, `cp-emc-permis-interdit`, `cp-emc-emotions-autrui`,
`cp-emc-formules-de-politesse`, le residu du groupe 2a, le groupe 2b) n'est nommement retiree pour
financer ce prelevement : je prends le montant sur le total du sous-total, comme la section 13.2 de
`DEC-0083` l'avait deja envisage pour ce meme lot ("un poste indicatif de s34 cede 2,0 de la borne
de l'instance qui herite du lot de designation"), sans presumer lequel.

**Le total nominal reste 12,5** (9,0 + 3,5), **inchange par cet amendement** : il depassait deja la
borne de 12,0 de 0,5 avant ce geste (section 3), et il la depasse toujours de 0,5 apres - ce n'est
pas un depassement nouveau, c'est le meme que celui deja arbitre comme un plafond et non une
obligation de tout depenser. La section 12.5 ci-dessous verifie ce chiffre plutot que de le
supposer.

**Le plafond arithmetique de la section 3 est recalcule** : il portait sur +6 lignes (69+6=75/138 =
54,3 %) ; il porte desormais sur **+8 lignes (69+8=77/138 = 55,8 %)**. C'est un recalcul de mon
propre total, pas de celui de `nadia-prof` - les deux lignes du lot de designation s'ajoutent au
compte de la meme facon que les six autres, par construction de la meme table.

**L'attente raisonnable de la section 3 (2 a 4 lignes, sur les six postes prioritaires du vivier)
n'est pas etendue a ces deux lignes, et je le fais expres.** Cette fourchette porte un jugement de
`nadia-prof` sur un risque pedagogique ligne par ligne (section 3, motif cite) ; la fermeture des
deux lignes du palier 2 depend d'abord d'une condition technique - les quatre lots `L-a` a `L-d`
livres - puis d'une premiere epreuve reelle des huit conditions `ZON-*` neuves du 2026-09-05,
**jamais mordues sur du contenu reel avant ce lot** (`PAR-13`). `nadia-prof` les qualifie de
`FERME` dans son arbitrage prealable de s32 (section 17.2), mais ce jugement portait sur le
contenu tel qu'elle le lisait sur le papier, avant que le premier dessin reel n'existe. Je ne
transforme donc pas cette qualification en un chiffre d'attente que je n'ai pas le mandat d'ecrire
a sa place ; je nomme les deux lignes comme un ajout distinct, avec sa propre condition, plutot que
de les fondre dans une fourchette qui mesure autre chose.

### 12.3 La question routee en section 9 est fermee

Section 9 se terminait par : *"si elle ne tranche pas avant que `sofia-sm` cloture son planning, le
defaut [...] est reporte a s35 par defaut"*. **Ce defaut ne se produit pas** : l'utilisatrice a
tranche avant l'ouverture du planning, le 2026-09-10, et l'engagement est celui de la section 12.1.
Ce que la section 9 notait sans le trancher reste vrai et n'est pas rouvert ici : la fenetre de
trois sprints de `DEC-0083` se clot avec s34, quelle que soit l'issue du palier 2, et une strategie
neuve, au-dela du Plan 2, reste due a la cloture de s34.

### 12.4 Trois corrections issues de l'avis de faisabilite de `theo-archi` pour s34
### (`squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/avis-faisabilite-theo.md`, non commite a la date de cet amendement)

#### 12.4.1 `RES-1` n'est pas opposable en l'etat : ce qui remplace le controle que la section 6
#### presumait

**Ce que la section 6 presumait, dit en clair pour pouvoir le corriger.** En ecrivant que six
postes du vivier "sont reexprimes... au format `RES-1` complet" et que `RES-4` rend les autres
"non engageables tant qu'ils ne portent pas un objet au format `RES-1`", je traitais implicitement
la presence d'un objet comme une propriete qu'un tiers - un developpeur qui recoit une reserve, un
automate qui la balaie - pourrait constater sur la fiche elle-meme. **Ce n'est pas le cas
aujourd'hui, et `theo-archi` l'a mesure plutot que suppose** : sur les 69 fiches de conformite, 131
titres de section nomment une reserve, mais seuls 8 blocs de citation l'enoncent dans une forme
reperable, et **aucun des huit ne porte de partie etiquetee `Objet`** - la population sur laquelle
une regle executable pourrait mordre est vide, ce qu'`ADR-0051` interdit d'ecrire comme regle.

**Ce qui remplace le controle executable, aujourd'hui : la lecture directe de `nadia-prof`, fiche
par fiche, sans filet automatique.** C'est deja ce qu'elle a fait pour les six postes prioritaires
du vivier de s34 : elle a ecrit leurs cinq parties (constat, objet, mode de defaillance, perimetre,
regime) dans son propre arbitrage prealable, pas dans les fiches, et rien dans le depot ne verifie
aujourd'hui que cette forme est tenue ailleurs qu'a l'endroit ou elle l'a ecrite. `RES-4` reste ce
qu'elle est - une regle que `nadia-prof` et les developpeurs appliquent en la lisant, pas une
`ArchRule` - et je ne la fais pas passer pour plus qu'elle n'est.

**Qui porte le cout des deux declarations manquantes que `theo-archi` propose** (sa section 4.4) :
une forme de bloc de reserve (une ligne d'ouverture normee, une ligne `Objet` en plus) et la regle
qui la verifierait une fois cette forme adoptee. **La forme est proposee par `theo-archi`, son
adoption est a `nadia-prof`** - c'est elle qui ecrit les fiches, et le cout d'y ajouter une ligne
`Objet` par reserve neuve est le sien, dans son propre budget d'audit, exactement comme la section
6 le dit deja pour l'ecriture des objets eux-memes. **La regle qui verifierait la presence de cette
ligne n'est pas engagee ni financee par cette decision** : `theo-archi` refuse explicitement de
l'ecrire avant qu'une reserve porte la forme proposee - une regle dont la population entiere serait
exemptee ne jugerait rien - et je ne la lui commande pas non plus : le jour ou une reserve adopte
la forme, l'ecriture de la regle est une story ordinaire, routee par `sofia-sm` comme n'importe
quelle autre dette technique, pas une condition de la composition de s34.

#### 12.4.2 Le remede de `DEC-0061` (`S34-SCO1-REMEDE`) coute plus que son seul prix d'audit

`theo-archi` mesure que le remede detruit l'assertion de `DeuxCoupsSimultanesTest`, le garde-fou de
non-regression d'`ADR-0019` (le double appui qui faisait perdre un point) : sous le remede, le
score qui fait foi peut valoir 0 apres deux appuis simultanes selon l'ordre reellement applique, ce
qui est exactement ce que la decision de `nadia-prof` demande, et non une regression de la
concurrence - mais le temoin que ce test avait choisi cesse d'en etre un, et il doit etre remplace
plutot que simplement complete. **Le prix d'audit de `nadia-prof`, 0,5, n'est pas recalcule** :
c'est son propre chiffre, sur son propre budget, et il ne bouge pas. **Ce qui s'ajoute est un cout
technique, nomme dans `docs/backlog/backlog.md`, `S34-SCO1-REMEDE`** : une expression par session
(trois sessions, dont une ecrite differemment), l'assertion de `DeuxCoupsSimultanesTest`
remplacee par un temoin qui survit au remede, une classe de test neuve pour la regle de
non-regression que le remede introduit, et les non-regressions `SCO-4` a `SCO-7` differees depuis
l'avis de s33. Ce cout ne consomme aucune part de la borne de 12,0 de `nadia-prof` : c'est un cout
de developpement, pas un cout d'audit, et il ne change donc rien aux totaux de la section 12.2.

#### 12.4.3 `EMC-CP-07` : la sortie technique retenue est un choix pedagogique, pas un choix de
#### `theo-archi`, et il reste a `nadia-prof`

**La derniere ligne de la section 11 se trompait**, et je la corrige plutot que de la laisser dire
une chose fausse : elle routait a `theo-archi` "la sortie technique retenue pour `EMC-CP-07`
(deplacement d'item ou item ternaire supplementaire)". `theo-archi` ecrit lui-meme, dans son avis
de s34, qu'il ne choisit pas entre les deux sorties et qu'il "chiffre les deux" - le choix est
pedagogique, et il revient a `nadia-prof`. `docs/backlog/backlog.md`, `S34-EMCCP07`, est corrigee
dans le meme geste que cet amendement.

`theo-archi` rend par ailleurs, dans le meme avis, la reprise des deux tests de paquet que
`nadia-prof` nommait comme le defaut d'`EMC-CP-07` (`fer1...`, `bin1...`) : vingt lignes dans un
seul fichier de test, zero production, zero contenu, verte des aujourd'hui sur le contenu servi.
Cette reprise est integree a `S34-EMCCP07` (`docs/backlog/backlog.md`) plutot que d'ouvrir une
story separee : elle ne porte aucune competence propre, elle corrige le seul defaut de mesure que
la ligne `EMC-CP-07` attendait, et une story distincte pour vingt lignes d'un seul fichier de test
n'ajouterait rien que la section "Prealable" de `S34-EMCCP07` ne dise deja.

### 12.5 Verification de la borne apres cet amendement

**9,0 (sous-total engage) + 3,5 (sous-total non affecte) = 12,5.** C'est le meme total nominal que
la section 3 arretait avant cet amendement, et le meme depassement de 0,5 au-dessus de la borne de
12,0, deja arbitre comme un plafond et non une obligation de tout depenser (section 3). **Cet
amendement ne fait pas sortir la composition de s34 de la borne** : il deplace 2,0 d'un sous-total
a l'autre, il n'en ajoute pas. Les deux corrections techniques de la section 12.4 (`RES-1`,
`S34-SCO1-REMEDE`) ne portent sur aucun poste de la borne de `nadia-prof` - ni l'une ni l'autre
n'est un cout d'audit - et la correction de `S34-EMCCP07` (section 12.4.3) ne change ni son prix
(0,5, section 3) ni sa ligne. **Rien n'est a sortir, rien n'est a arbitrer ici** : c'est exactement
la verification que `PAR-16` demande de faire a chaque changement de composition, et je l'ecris
plutot que de la presumer.

### 12.6 Ce que cet amendement decide

1. **L'entree minimale du palier 2 est engagee a s34** (section 12.1), sur l'arbitrage de
   l'utilisatrice du 2026-09-10 : ce n'est pas un troisieme report.
2. **La composition de la section 3 est amendee, sans etre reecrite** : sous-total engage 7,0 a
   9,0, sous-total non affecte 5,5 a 3,5, plafond arithmetique 6 a 8 lignes (54,3 % a 55,8 %),
   total nominal inchange a 12,5 (section 12.2).
3. **`RES-1` n'est pas opposable en l'etat** : la lecture directe de `nadia-prof`, fiche par
   fiche, remplace le controle executable que la section 6 presumait ; le cout des deux
   declarations manquantes que `theo-archi` propose revient a `nadia-prof` pour la forme adoptee,
   et n'est engage par personne pour la regle qui la verifierait (section 12.4.1).
4. **Le cout de `S34-SCO1-REMEDE` est complete d'un cout technique**, sans toucher au prix d'audit
   de `nadia-prof` (section 12.4.2), et la story est mise a jour dans `docs/backlog/backlog.md`.
5. **La sortie technique d'`EMC-CP-07` est un choix pedagogique de `nadia-prof`, pas de
   `theo-archi`** ; la reprise des deux tests de paquet est integree a `S34-EMCCP07` (section
   12.4.3), et la section 11 est corrigee en consequence.
6. **La borne de 12,0 n'est pas depassee au-dela de ce qu'elle l'etait deja** : 12,5 nominal, 0,5
   au-dessus, verifie et non suppose (section 12.5).

### 12.7 Ce que cet amendement ne fait pas

- Il ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` et n'en ferme aucune ligne :
  les statuts ne bougent qu'au verdict de `nadia-prof`, y compris pour `QLM-CP-11` et `QLM-CP-14`.
- Il ne prononce aucun Done pedagogique.
- Il ne recalcule aucun chiffre de `nadia-prof` ni de `theo-archi` : les prix, les mesures et les
  constats repris ici le sont sans etre revises.
- Il n'affecte aucune instance ni aucun developpeur au lot de designation : ce choix reste au
  planning de `sofia-sm`, comme la section 3 le dit deja des autres postes.
- Il n'ecrit pas la regle executable de `RES-1` ni la ligne `Objet` d'aucune fiche : la premiere
  est due par `theo-archi` le jour ou une reserve porte la forme proposee, la seconde est due par
  `nadia-prof` quand elle l'adopte (section 12.4.1).
- Il ne decide d'aucune question technique ou d'architecture : la forme technique de l'ecran de fin
  de `S34-SCO1-REMEDE`, le contenu de l'`ADR` qui le porte, et le chemin de developpement du palier
  2 restent au ressort de `theo-archi`.
