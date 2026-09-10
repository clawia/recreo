# [Sprint 33] Retrospective - 2026-09-10

- **Anime par** : sofia-sm

## Ce qui a marche

- **Le tarif de lot (`PAR-14`) a fait exactement ce pour quoi il a ete ecrit.** `S33-EPS-JEU`
  touchait six items sur onze d'un meme paquet ; la clause l'a fait chiffrer au tarif de lot (1,0)
  plutot qu'au tarif de rejeu (0,5), sans discussion au moment de la fusion. Une regle de budget
  neuve a mordu correctement des son premier cas d'usage.
- **Un rouge trouve sur un checkout partage a ete prouve, pas suppose, par deux agents
  independants.** `yanis-qa` et `theo-archi` ont chacun construit un worktree isole ou jetable
  positionne sur `b45e5927`, l'un avec le reacteur complet, l'autre avec son seul fichier, pour
  etablir que le rouge de `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` venait de
  documents non commites d'une instance soeur et non du commit mesure. La methode a marche ; ce
  qu'elle a coute est nomme plus bas.
- **`nadia-prof` continue de corriger ses propres constats avant de s'en servir**, et de le dater :
  motif de `HG-R9`/`HG-R10` corrige, chiffre de `REG-R3` corrige (17 contre 6, pas 17 contre 1),
  perimetre de `EMC-CP-07` corrige ("aucun item neuf" etait faux), portee de `PAR-14` corrigee.
  Quatrieme sprint consecutif ou cette discipline tient. Le prix de ce qu'elle corrige est nomme
  dans "ce qui a coince" ; la discipline elle-meme reste un acquis.
- **La porte technique a ete relancee et nommee sur l'arbre de travail reel, pas deduite.**
  `sofia-sm` a relance les quatre jobs a la review sur le commit `b45e5927` plus les huit documents
  de sprint pas encore commites, conformement a l'amendement de `CLAUDE.md` du 2026-09-08 - la
  lecon de s30 (`chemins` rouge un sprint entier faute de ce geste) n'a pas eu besoin d'etre
  reapprise.

## Ce que la retro de s32 avait demande d'eviter - le bilan exact, pas la version d'une seule instance

Le planning de s33, section 11, nommait deux classes de defaut et demandait de les eviter. Les
trois verdicts permettent de trancher terrain par terrain, pas seulement sur celui qui les cite
explicitement.

### Classe 1 - la compression qui casse la langue (subordonnee retiree pour tenir une longueur)

**Sur les quatre corrections que la section 11 visait nommement, elle ne s'est pas reproduite sous
sa forme d'origine.** `verdicts-nadia-instance-C.md`, section 8, le verifie explicitement sur les
quatorze textes reecrits d'`EPS-CP-04`/`EPS-CP-08` : "le francais tient partout". Aucun des deux
autres verdicts ne signale de subordonnee retiree sur `S33-HG-02`, `S33-HG-04` ou l'item 4 de
`S33-EMCCP07`.

**Mais la famille plus large - un texte remplace ou restructure sans que le francais soit repris
autour - s'est reproduite deux fois, sous une forme differente de celle de s32 :**

| Ou | Story | Ce qui casse |
|---|---|---|
| `HG-R14` (contenu neuf, `HG-CM1-08`) | `S33-HG-08-12` | *"Avant de devenir empereur en 1804, quel regime..."* : le seul sujet de la phrase est *la Revolution* - Napoleon n'est nomme nulle part dans l'item. `verdicts-nadia-instance-A.md` la nomme elle-meme "exactement la classe de defaut que la retro de s32 a nommee" |
| `EXC-R8` (reecriture de consigne) | `S33-EXC-R1` | La question de la tache B a change, les trois propositions ont garde la forme de l'ancienne question - recollees, ce n'est plus du francais. `verdicts-nadia-instance-B.md` la nomme elle-meme "la meme classe... la cause est la meme" |

**Le fait a retenir sans l'adoucir** : deux des quatre corrections nommement visees par le planning
ont tenu ; une cinquieme situation, non visee par le planning parce qu'elle porte sur du contenu
neuf ou une reecriture de consigne plutot que sur une "compression", a reproduit la meme famille de
defaut deux fois, sur deux terrains differents.

### Classe 2 - le correctif qui reproduit la classe de defaut qu'il paie

**Elle n'a pas ete evitee : c'est le troisieme sprint consecutif ou elle apparait.**
`verdicts-nadia-instance-C.md`, section 8, l'ecrit en toutes lettres : `CAT-1` a s32, `EMC-11` a
s33 sur l'EMC, `JEU-R9` ici. Le cas nomme : `JEU-R4` a ete corrigee en reproduisant `JEU-R2` (une
regle de jeu jugee sans qu'aucun enonce ne la rappelle), dans le meme fichier, le meme commit,
quatre items plus loin, alors que `JEU-R2` etait payee correctement a cote. **`CAT-1` elle-meme**,
tout en fermant enfin au cinquieme sprint de report, produit `PER-R3` dans la meme famille
qu'elle payait : une raison dont la faussete tient a une inversion entre la regle et ce qu'elle
protege, servie deux fois comme distracteur - `nadia-prof` le nomme "previsible" et "le prix de
`CAT-1`", pas un reproche au correctif, mais c'est bien la meme famille qui revient.

**Conclusion pour l'equipe** : la classe la plus dangereuse des deux n'a pas bouge d'un sprint a
l'autre. Un correctif qui corrige des items un par un plutot qu'un paquet entier continue de
reproduire, localement, le defaut qu'il est charge de faire disparaitre.

## Ce qui a coince

### Le motif transverse : trois instances trouvent chacune un defaut dans une consigne que `nadia-prof` avait ecrite elle-meme

Ce n'est pas trois incidents isoles, c'est le meme motif sur trois terrains :

- **`HG-R11`**, dictee mot pour mot par `nadia-prof` a s32 ("reprendre... la formulation que
  l'item 66 porte deja"), appliquee a la lettre par `marc-dev`, produit une proposition verbale
  placee dans une liste de groupes nominaux qui ne se recolle plus a sa question - `HG-R13`,
  reserve neuve. `nadia-prof` l'ecrit elle-meme : "le defaut est de moi".
- **Le chiffrage de `EMC-CP-07`** ("propositions seules, aucun item neuf", prix 0,5) etait faux le
  jour ou `nadia-prof` l'a publie. C'est ce chiffrage, pas le contenu, qui a fait declarer la
  sortie qu'elle prescrivait infaisable et bloquer la ligne une quatrieme fois - `lea-dev` a mesure
  l'impasse, `nadia-prof` corrige son propre chiffrage a la cloture.
- **L'exemple de la trottinette de `SEC-R1`**, repris mot pour mot par `nadia-prof` elle-meme a s32
  pour dire ce qu'elle attendait, est fautif a la source : un enfant qui traverse en trottinette la
  pousse a la main, il ne la traverse pas roulant. `sofia-sm` (casquette dev) l'a applique tel
  quel ; le refus (`SEC-R4`) porte sur l'exemple, pas sur l'execution.

**Ce que ce motif coute** : au moins un sprint entier sur `EMC-CP-07` (quatrieme refus, ligne
toujours `non couvert`), une reserve neuve sur une ligne qui vient de fermer (`HG-R13`), et un
exemple faux qui reste dans le referentiel programme tant qu'il n'est pas corrige. Dans les trois
cas, l'execution a ete fidele au texte recu ; le texte recu etait la source du defaut. Une consigne
"mot pour mot" venant de `nadia-prof` n'est pas plus fiable qu'une consigne de developpeur des lors
qu'elle porte sur un fait ou un chiffrage, et elle merite la meme mefiance avant d'etre executee a
la lettre.

### Deux stories du meme commit se defont l'une l'autre

`S33-EXC-R1` reecrit la consigne de `bandit-manchot-exclusion-cp` en *"...ou a quel adulte en
parler"*, nommant une tache. `S33-EXC-R5`, dans le meme commit, retire cette tache du paquet (les
cinq items de la tache B demandent desormais *qui est present*, pas *a qui en parler*). Le paquet
se contredit avec lui-meme des le premier jour ou il est servi (`EXC-R9`, non bloquante mais "la
plus lourde des trois" selon `nadia-prof`). Chaque story, prise seule, respectait son propre
critere d'acceptation ; aucun garde-fou, aucune relecture et aucun developpeur ne l'a lu comme un
tout avant l'audit - `PaquetCpEmcExclusionTest` lit la tache d'un item dans sa bonne reponse
(`ADR-0089`), jamais dans l'enonce ni dans la consigne, donc il ne pouvait pas le voir.

### Le budget : au moins 4,0 sur 11,5 inemployes, et un seul poste a achete les trois fermetures

| Instance | Depense reelle sur borne | Idle jamais engage | Lignes fermees achetees |
|---|---|---|---|
| A | 2,5 / 4,0 | 1,5 | 3 (les trois seules du sprint) |
| B | non auto-audite | au moins 0,5 | 0 |
| C | 2,0 / 4,0 | 2,0 | 0 |

Deux lignes financees (`EPS-CP-10`, `EPS-CP-11`, 1,5 reserve) n'ont jamais recu de story : le
planning l'avait ecrit en section 10 et route a `camille-po` des l'ouverture. Elle a repondu, avec
une position rendue et convergente avec `nadia-prof` - mais apres l'arret du developpement du
sprint (planning, section 14), donc trop tard pour qu'une des trois instances engage quoi que ce
soit dessus. **Le routage a fonctionne comme concu ; le calendrier ne l'a pas suivi.** Router un
obstacle a l'ouverture ne suffit pas si la reponse peut arriver apres que le sprint a cesse
d'avancer.

### Les rouges transitoires du registre partage - cout reel de la clause 3 d'`ADR-0085`

Trois instances de `nadia-prof` ecrivant `squad/decisions/REGISTRE-identifiants.md` en parallele
ont fait rougir `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` sur le checkout partage, a
deux reprises mesurees a dix minutes d'intervalle par `theo-archi` (24 puis 26 identifiants non
alloues). Le defaut n'appartenait a aucun commit reel - un fichier non commite ne peut pas voir
l'allocation qu'une instance soeur est en train d'ecrire dans un autre fichier non commite - mais
deux agents (`yanis-qa`, `theo-archi`) ont chacun du batir un worktree isole ou jetable pour le
prouver plutot que de le supposer. C'est la charge que la clause 3 d'`ADR-0085` fait porter a
chaque sprint a trois instances tant que le defaut structurel qu'elle nomme n'est pas leve.

## Amendements au process

**Amendement a `squad/README.md`, section 4** (edite dans le meme geste que cette retro) : quand un
commit paye plusieurs stories qui touchent le meme paquet de contenu, le paquet resultant est relu
comme un tout - consigne contre l'ensemble des taches reellement servies - avant de commiter, pas
seulement story par story. Fonde sur `S33-EXC-R1`/`S33-EXC-R5` ci-dessus : chaque story respectait
son propre critere, le paquet assemble se contredisait avec lui-meme, et aucun garde-fou structurel
ne peut le voir puisque la propriete porte sur le sens d'une consigne face a des taches, pas sur
une forme de test.

## Actions pour s34, nommees et affectees

| Action | Affectee a | Motif |
|---|---|---|
| Verifier une consigne "mot pour mot" contre le fait ou le chiffrage qu'elle porte avant de la dicter comme texte litteral a executer, sur toute reserve neuve | nadia-prof | Motif transverse : `HG-R11`, chiffrage `EMC-CP-07`, exemple `SEC-R1` |
| Relire le paquet assemble comme un tout avant de commiter, quand plusieurs stories du sprint le touchent (amendement ci-dessus) | marc-dev, lea-dev, sofia-sm (casquette dev) | `S33-EXC-R1`/`S33-EXC-R5` |
| Rendre la position sur un obstacle route en planning avant l'arret du developpement du sprint qui l'a route, pas apres | camille-po | Deux lignes financees (`EPS-CP-10`, `EPS-CP-11`) sans story engageable a temps |
| Lever le defaut structurel du registre partage (clause 3, `ADR-0085`) avant que trois instances n'ecrivent de nouveau en parallele | theo-archi | Deux rouges transitoires prouves a la main ce sprint |
| Fermer `EMC-CP-07` sur le perimetre corrige, `HG-CM1-12` (`HG-R17`) et les deux lignes d'EPS CP refusees, en s'appuyant sur les motifs deja ecrits dans les trois verdicts | nadia-prof (audit), sofia-sm (stories) | Quatre refus de s33, deja routes dans `review.md` |
| Recalculer le registre de dette exact (au-dessus de 2,5, chiffre precis a etablir) a l'ouverture du planning | camille-po | La descente projetee par `DEC-0085` ne tient pas telle quelle |
| Corriger l'exemple de la trottinette dans le referentiel programme d'EPS CP | nadia-prof | Elle l'a ecrit a sa propre charge (`SEC-R1`) |

## Decisions prises

Aucune decision produit, pedagogique ou d'architecture n'est prise pendant cette retro. Un
amendement de process est fait a `squad/README.md`, section 4 (voir ci-dessus) ; ce n'est ni un
`ADR` ni une `DEC`.
