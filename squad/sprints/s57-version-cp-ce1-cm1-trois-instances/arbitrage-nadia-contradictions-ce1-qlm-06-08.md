# s57 - arbitrage des contradictions relevees sur `-06` (`ce1-qlm-aimant`) et `-08` (`ce1-qlm-dangers-electricite`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-27, debut 08:03:38 (UTC+01:00), lu par `date` ; fin en section 6.
- **Arbre** : branche `s57-p5-nadia-c0608`, base `round-two` a `fb689ee2`, lue par `git rev-parse`.
- **Repond a** : `docs/backlog/backlog.md`, annotation du 2026-10-26, huitieme, `Q8-2`, `Q8-3`, `Q8-4` ;
  `avis-theo-palier-5-gardes-ce1-qlm-06-08.md`, section 5.2 (`Q1` a `Q6`), section 5.3 (information sur
  `cp-emc-alerte`) et action `A3` (`LON-CE1-5`).
- **Type** : arbitrage avant premier jet. **Aucun Done, aucun verdict de lot, aucun identifiant neuf, aucune condition
  neuve ni reecrite.** Ce sont des lectures de `AIM-CE1-1`, `AIM-CE1-2`, `DEL-CE1-1`, `DEL-CE1-2`, `NRC-CE1-1`,
  `TRV-CE1-2`, `LON-CE1-5` et `FER-1`. Les reperes `P1`... et `T1`... sont locaux a ce fichier. Je ne modifie ni story,
  ni paquet, ni test : `camille-po` reporte les textes de la section 4, `theo-archi` traite la section 5.
- **Audit degrade, dit** : rien a jouer, aucun des deux paquets n'existe. Je juge des regles, pas des items.
- **Lu** : mon `arbitrage-nadia-premiers-jets-ce1-qlm-06-08.md` en entier ; l'avis de `theo-archi` sur les gardes de
  `-06` et `-08` en entier ; l'annotation huitieme (`Q8-1` a `Q8-7`, seuls `Q8-2` a `Q8-4` sont a moi ici) ;
  `avis-theo-palier-5-exemption-aim-ce1-2.md` ; `reponse-nadia-aim-ce1-2-q7.md` ;
  `ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md` (`NRC-CE1-1`, 3.6, 3.8) ;
  `ARBITRAGE-PREALABLE-s48-francais-ce1.md` (`LON-CE1-5`) ; `PaquetLu.mots` ; `cp-emc-alerte.yaml` en entier ; un `grep`
  des bonnes reponses des paquets `cp-*` sur *toucher*, *approcher*, *brancher*, *sécher*, *adulte*.

## Resume

| Repere | Point | Reponse | En une ligne |
|---|---|---|---|
| `P1` | `Q8-2`, `-06` : deux objets en fer en distracteur de A *n'attire pas* | **admis, regle reformulee** | ce que les deux distracteurs partagent parce que la question le demande n'est pas un trait partage ; la regle 3 porte sur un mot de leur nom, une forme, une taille ou un usage |
| `P2` | `Q8-3`, `-08` : *jamais un geste sur l'appareil* contre les formes admises | **reformulee, deux corrections de mon texte** | une forme ne demande aucune action sur l'objet electrique ; *"garder l'appareil loin de l'eau"* est retire ; *"ne pas toucher l'appareil"* est admis |
| `P3` | `Q8-4`, `-08` : *attendre* ; abstention contre distracteur negatif | **reformulee** | *attendre* seul n'est ni bonne reponse ni distracteur ; un distracteur negatif est admis quand ce qu'il refuse est une precaution (*"ne rien dire à un adulte"*) ; une abstention laisse l'enfant hors du danger et reste refusee |
| `P4` | `cp-emc-alerte` et `NRC-CE1-1` | **non, je ne le tiens pas a ma lecture : je l'applique avant le premier jet** | l'alerte a un adulte est la bonne reponse de 8 items de `cp-emc-alerte` a la meme tache ; elle sort des propositions de `-08` |
| `P5` | `Q1` enonces de `-06` fermes | **confirme, une garde ajoutee** | la premisse *"... sont en métal."* n'est ecrite que si tous les objets le sont (`T1`) |
| `P6` | `Q2` *papier d'aluminium* / *feuille de papier* | **confirme** | c'est ma regle 3 et mon motif de 1.2 (*"pour lui, c'est du papier"*) |
| `P7` | `Q3` trois sondes de `theo-archi` | **confirmees toutes les trois** | elles disent ce que mes textes disent |
| `P8` | `Q4` mains mouillees | **confirme pour les trois formes ; situation declaree : `eau`** | avec un plancher : un item de B `eau` au moins ne parle pas de mains mouillees (`T4`) |
| `P9` | `Q5` *pièce*, *couvert*, *or* dans tout sens | **confirme, reporte dans la regle** | dans un paquet sur l'aimant, *pièce* se lit *pièce de monnaie* |
| `P10` | `Q6` *blessé* quel que soit le sujet ; *on se blesse* | **confirme, une garde ajoutee** | un personnage nomme qui *se blesse* au present passe la forme actuelle (`T5`) |
| `P11` | `LON-CE1-5` et les groupes de B | **aucune autre forme** | la liste a virgules reste ; six mots au plus contraint la composition, pas la tache |

---

## 1. `-06` (`ce1-qlm-aimant`)

### 1.1 `P1` (`Q8-2`) : deux objets en fer en distracteur de A *n'attire pas*

**Admis.** `camille-po` a raison sur le fait : dans un item de A au negatif, une seule proposition n'est pas attiree, donc
les deux distracteurs sont deux objets en fer (1.3 de mon arbitrage, *"forcee"*). Ils ont en commun d'etre en fer, et
c'est exactement ce que l'enfant doit savoir pour les ecarter. **Un trait que la competence elle-meme donne n'est pas un
trait reconnaissable sans la competence** : c'est le seul cas que la regle 3 vise (*"un trait ... qui se voit sans savoir
ce qu'un aimant attire"*, 1.3). Ma regle ecrivait *"un nom de matiere"* ; elle voulait dire **un mot ecrit dans le nom**,
celui que l'enfant voit sans rien savoir. Deux objets en fer dont les noms ne portent pas le mot commun ne montrent rien.
Et *le fil de fer* ne peut jamais partager *fer* avec un autre distracteur : c'est le seul nom de la liste qui porte ce mot.

**Ce qui reste refuse dans ce cas**, a ma lecture (`QC9`) : deux objets en fer qui partagent une forme ou un usage que la
bonne reponse n'a pas, par exemple *la gomme* parmi *l'aiguille* et *l'épingle* (deux objets pointus de couture).

**Ce que ce cas ne fait pas compter** : si la bonne reponse est sans metal (*la gomme*), la croyance fausse et la bonne
connaissance donnent la meme reponse ; l'item sert `FER-1` et ne compte pas pour le quart (regle 1, inchangee). Si elle
est en aluminium ou en cuivre et que l'enonce porte la premisse, l'item compte : c'est l'item du quart le plus net.

**[corrige le 2026-10-27, `nadia-prof`, ce fichier, section 1.1]** : regle 3 de 1.6 de
`arbitrage-nadia-premiers-jets-ce1-qlm-06-08.md`, *"un nom de matiere"* devient *"un mot de leur nom"*, et la phrase sur
la polarite *n'attire pas* est ajoutee. Texte en 4.1.

### 1.2 `P5` (`Q1`) : enonces fermes

**Confirme.** Les enonces de `-06` sont fermes a `FORME_A` et `FORME_B` de l'avis (2.0), avec ou sans *"de ces objets"*,
avec ou sans l'une des deux premisses. Une autre formulation est refusee, visible, et entre dans le motif apres mon
accord ecrit, comme un objet hors de la liste. Motif : sur une ligne dont la condition interdit une phrase
(`AIM-CE1-2`), une formulation libre est la porte par laquelle la regle fausse entre sans le verbe *attirer* ; ces cas
sont ceux que la garde ne voit pas (`reponse-nadia-aim-ce1-2-q1-q2.md`, section 3).

**Ce que la forme fermee laisse passer, et que je refuse** : la premisse devant un objet sans metal.
*"Ces trois objets sont en métal. Lequel l'aimant attire-t-il ?"*, *le clou* ; *la gomme*, *le fil de cuivre* : l'enonce
affirme que la gomme est en metal. **C'est une erreur de contenu**, motif de refus a lui seul. `g4` ne compte pas cet
item, mais rien ne le refuse. Ce n'est pas une regle neuve, c'est l'exactitude ; je l'ecris dans la regle 1 (4.1) et je
demande la garde (`T1`).

### 1.3 `P6` (`Q2`) : *le papier d'aluminium* et *la feuille de papier*

**Confirme.** Deux propositions dont le nom porte *papier*, contre un objet qui ne le porte pas : l'enfant voit deux
papiers et un clou. C'est ma regle 3 lue sur le mot, et le motif de ma section 1.2 (*"pour lui, c'est du papier"*). Le
refus vaut dans les deux polarites ou la paire peut etre en distracteur, c'est-a-dire quand la bonne reponse est attiree.

### 1.4 `P7` (`Q3`), sonde du quart sans premisse

**Confirmee.** *"Lequel de ces objets l'aimant attire-t-il ?"*, *le clou* ; *le fil de cuivre*, *le papier d'aluminium* ne
compte pas : ma regle 1 dit *"que l'enonce le dit"*, et un enfant qui ne sait pas que l'aluminium est un metal voit un
clou, un fil et du papier. Elle est la seule a tuer le mutant qui ne lit pas la premisse ; elle entre dans le jeu fautif,
marquee de la main de `theo-archi`.

### 1.5 `P9` (`Q5`) : *pièce*, *couvert*, *or* dans tout sens

**Confirme.** Ma regle 5 dit *"ne sont nommes nulle part"* ; la garde refuse le mot, quel qu'en soit le sens. Dans un
paquet sur ce que l'aimant attire, *"la pièce"* se lit *pièce de monnaie* et *"l'or"* se lit metal, quoi que l'auteur ait
voulu dire. Les enonces et les propositions etant fermes, la garde ne touche en pratique que l'indice, ou ces mots ne
servent a rien. *Doré* n'est pas refuse par la garde : aucun objet de la liste ne l'est, et un indice qui l'emploierait
reste a ma lecture. Je l'ecris dans la regle 5 (4.1) pour que le developpeur n'ait pas a le deduire.

### 1.6 `P11` : `LON-CE1-5` et les groupes de B

**Aucune autre forme de groupe.** La liste sans article separee par des virgules est celle que l'enfant a deja lue a
`-01` ; un autre format (*"le clou, le trombone et l'aiguille"*) ajoute des mots et ne gagne rien. Six mots au plus
est deja dans ma regle 2 (*"six mots au plus (`LON-CE1-5`)"*) : un groupe porte au plus un objet de trois mots
(*"fil de fer, papier d'aluminium, clou"*, 6, passe ; *"fil de fer, fil de cuivre, clou"*, 7, est refuse par la garde
transverse, compte lu sur `PaquetLu.mots`, qui compte *d'aluminium* comme un mot). C'est une contrainte de composition
pour `marc-dev` (`A3` de l'avis), pas une regle de contenu neuve : je n'ecarte aucun objet de B.

## 2. `-08` (`ce1-qlm-dangers-electricite`)

### 2.1 `P4` : l'alerte a un adulte et `NRC-CE1-1`

**Je ne le tiens pas a ma lecture : j'applique la condition avant le premier jet.** `theo-archi` compte 3 bonnes reponses
de `cp-emc-alerte` egales a *"prévenir un adulte"*. **Precision de mesure** : par `grep` des bonnes reponses du paquet,
**8 items sur 11** ont pour bonne reponse une alerte a un adulte (*"prévenir un adulte"* trois fois, *"refuser et le dire
à un adulte"*, *"avertir un adulte sans attendre"*, *"aller voir un adulte"*, *"prévenir un adulte tout de suite"*,
*"demander de l'aide à un adulte du magasin"*), tous a la tache que son en-tete nomme *"situation decrite"* : un
personnage, une situation, *"Que doit-il faire ?"*. C'est la tache B de `-08`, mot pour mot.

`NRC-CE1-1` dit : *"meme bonne reponse a la meme tache"* est une reprise, et *"un item qu'un enfant de CP resoudrait avec ce
que le paquet de CP voisin lui a appris, sans rien de plus, sert la ligne de CP et ne compte pas pour la ligne de CE1"*. Un
item *"Tom voit un fil électrique abîmé. Que doit-il faire ?"*, *prévenir un adulte*, se resout par le reflexe que
`cp-emc-alerte` enseigne, sans rien savoir de l'electricite. **La lettre le refuse.** Je ne le laisse pas au premier jet
pour le refuser a l'audit.

**Ce qui en suit** : aucune alerte a un adulte n'est bonne reponse de `-08`, et aucune n'est distracteur non plus, puisqu'un
geste juste en distracteur fait deux reponses (`TRV-CE1-2`, ma regle 3). Restent trois gestes de `DEL-CE1-1` en bonne
reponse : ne pas toucher, ne pas approcher de l'eau, ne pas brancher avec les mains mouillees. `DEL-CE1-1` les enumere
par *"ou"* : elle permet quatre bonnes reponses, elle n'exige pas que chacune soit servie ; `FER-1` porte sur les trois
situations, qui restent toutes servables (2.3). **Aucune condition n'est reecrite** : je lis `DEL-CE1-1` avec `NRC-CE1-1`,
qui sont toutes deux de moi et que j'avais lues separement.

**Ce qui reste admis** : l'alerte niee en distracteur (*"ne rien dire à un adulte"*, 2.4), et l'adulte dans une
explication. L'indice de B ne nomme toujours pas *adulte* (regle 4, inchangee).

**[corrige le 2026-10-27, `nadia-prof`, ce fichier, section 2.1]** : regle 2 de 2.6 de
`arbitrage-nadia-premiers-jets-ce1-qlm-06-08.md`, les trois formes d'alerte sont retirees ; *"un des quatre gestes"*
(regles 1 et 3) devient *"une forme de la regle 2"*.

### 2.2 `P2` (`Q8-3`) : *jamais un geste sur l'appareil*, *applique a l'objet de la situation*

**Les deux expressions etaient mal dites.** Ce qu'elles voulaient :

- *"applique a l'objet de la situation"* : **quand la forme nomme un objet, c'est celui de l'enonce** (*le fil* devant
  un fil abime, *la prise* devant une prise cassee, *la lampe* quand l'enonce nomme la lampe). Ce n'est pas un geste
  applique a l'objet au sens d'une action.
- *"jamais un geste sur l'appareil"* : **la forme ne demande a l'enfant aucune action sur l'objet electrique ou sur
  l'installation** : ni reparer, ni debrancher, ni brancher, ni deplacer, ni ouvrir. Une forme est une abstention
  (*ne pas*, *ne rien*, *laisser ... sans*) ou un soin de ses propres mains avant tout geste (*se sécher les mains*,
  *attendre d'avoir les mains sèches*). Le soin des mains n'est pas un geste sur l'appareil.

**Ce que cette lecture trouve dans mes propres formes** :

1. *"garder l'appareil loin de l'eau"* **est retire.** Devant un appareil deja branche pres de l'eau, la situation de
   `DEL-CE1-1`, *garder loin* se lit *eloigner l'appareil*, c'est-a-dire prendre un appareil branche au bord de l'eau :
   le geste dangereux. Je l'avais admis en pensant a l'enfant qui n'a pas encore approche l'appareil.
2. **La situation `eau` se dit de deux manieres, et chacune a sa forme.** Le personnage veut poser ou utiliser un
   appareil pres de l'eau : *"ne pas l'approcher de l'eau"*. L'appareil est deja branche pres de l'eau : aucune forme
   admise ne repondait, puisque l'alerte sort (2.1). **J'admets *"ne pas toucher l'appareil"*** (4 mots), qui est
   *ne pas toucher* de `DEL-CE1-1`.
3. *"ne pas brancher la lampe"* reste : une abstention, sous l'enonce des mains mouillees, qui nomme la lampe.

Ma phrase *"une abstention ou une alerte"* devient *"une abstention ou un soin de ses mains"*, l'alerte etant sortie (2.1).

**[corrige le 2026-10-27, `nadia-prof`, ce fichier, section 2.2]** : regle 2 de 2.6 de
`arbitrage-nadia-premiers-jets-ce1-qlm-06-08.md`, les trois conditions de formulation sont reecrites dans ce sens, une
forme retiree, une admise. Texte en 4.2.

### 2.3 `P8` (`Q4`) : les mains mouillees

**Confirme : la parenthese vaut pour les trois formes** de la ligne (*"attendre d'avoir les mains sèches"*, *"se sécher
d'abord les mains"*, *"ne pas brancher la lampe"*). Je l'avais ecrite apres la troisieme seule ; aucune des trois n'a de
sens sans mains mouillees dans l'enonce.

**La situation declaree est `eau`, et aucune autre.** Les mains mouillees et l'appareil au bord de l'eau sont le meme
danger, l'eau et l'electricite ensemble. Un enonce qui ajouterait une prise cassee ou un fil abime aux mains mouillees
porterait deux situations, donc deux gestes justes : refuse, une situation par enonce.

**Ce que je demande en plus, et pourquoi ce n'est pas un poteau deplace** : la situation de `DEL-CE1-1` est *"un appareil
branche pres de l'eau"*. Si tous les items de B `eau` parlaient de mains mouillees, le critere 1 serait tenu sur le
papier et la situation listee ne serait jamais servie en B. **Au moins un item de B `eau` ne parle pas de mains
mouillees.** C'est la lettre du critere 1, que la declaration `eau` seule ne garantit pas. Garde demandee : `T4`.

### 2.4 `P3` (`Q8-4`) : *attendre*, abstention, distracteur negatif

**Ce que les trois phrases voulaient, mises ensemble** :

- ***Attendre* seul** n'est pas une forme : il ne dit pas ce qu'on attend, et devant un fil abime il est une conduite sans
  danger mais incomplete. Il n'est donc **ni bonne reponse ni distracteur**, comme *s'eloigner* : en distracteur, il serait
  une seconde reponse defendable. *"attendre d'avoir les mains sèches"* n'est pas *attendre* : c'est le geste *ne pas
  brancher avec les mains mouillees*, dit a l'affirmatif. Aucune contradiction sur le fond ; ma phrase de 2.2 nommait le
  verbe la ou elle voulait dire *attendre sans objet*.
- **Une abstention**, au sens de ma regle 3, est une proposition qui laisse l'enfant hors du danger sans rien faire de
  risque : *s'éloigner*, *attendre*, *ne rien toucher*, *ne pas s'approcher*, *continuer à jouer ailleurs*. **Refusee en
  distracteur**, qu'elle soit negative ou non, parce qu'elle est juste.
- **Un distracteur negatif admis** est une proposition qui refuse une precaution : elle laisse le danger en place ou le
  cache. *"ne rien dire à un adulte"* (5), *"ne pas déranger un adulte"* (5) : un enfant de CE1 qui craint d'etre gronde les
  choisit vraiment, et elles sont fausses. **Il ne nie jamais la bonne reponse du meme item, ni une autre forme de la meme
  precaution** : *"ne pas attendre d'avoir les mains sèches"* a cote de *"se sécher d'abord les mains"* donne la reponse
  par la paire de contraires.

Le critere tient en une question, que je poserai a chaque distracteur : *"un enfant qui ferait cela serait-il hors de
danger ?"* Oui : abstention, refusee. Non : distracteur admissible, s'il est plausible.

**[corrige le 2026-10-27, `nadia-prof`, ce fichier, section 2.4]** : regle 3 de 2.6 de
`arbitrage-nadia-premiers-jets-ce1-qlm-06-08.md`, *"une abstention en distracteur"* est definie, et le distracteur
negatif de mon 2.5 est nomme avec ses limites. Texte en 4.2.

### 2.5 `P7` (`Q3`), deux sondes de `-08`

- **La situation declaree que l'enonce ne nomme pas** (declare `fil`, l'enonce ne nomme que la prise) : **confirmee,
  refusee**. *"Chaque item declare sa tache et sa situation"* ne vaut que si la declaration est vraie.
- **Le distracteur negatif *"ne rien dire à un adulte"*** (bonne reponse *"ne pas toucher la prise"*, autre distracteur
  *"toucher la prise pour voir"*) : **confirmee, passe**. C'est le distracteur negatif admis de 2.4, et l'item reste juste
  apres la sortie de l'alerte (2.1) : la bonne reponse n'est pas une alerte.

### 2.6 `P10` (`Q6`) : *blessé* et *on se blesse*

**Confirme.** *Blessé*, *blessée*, *blessés*, *blessées* sont refuses quel que soit le sujet, nomme ou non : un participe
dit qu'une blessure a eu lieu, c'est la scene que `DEL-CE1-2` ecarte (*"l'item enseigne le geste sans mettre en scene
l'accident"*). *"on se blesse"* passe : un present generique dit la consequence, comme *"pour ne pas se blesser"*, que j'ai
admis.

**Ce que la forme actuelle laisse passer, et que je refuse** : *"Lina se blesse avec le fil."*, *"Sami se fait mal."*. Le
present conjugue avec un personnage nomme raconte la blessure ; *blesse* sans accent y est la forme du present, que
`g7` lit justement comme generique. Ma regle 5 le dit deja (*"Aucun personnage nomme n'est blesse"*) ; je l'ecris en
toutes lettres (4.2) et je demande la garde (`T5`).

## 3. Ce que je corrige de mes propres ecrits, rassemble

1. `-06`, regle 3 : *"un nom de matiere"* voulait dire *un mot de leur nom* ; la polarite *n'attire pas* n'etait pas
   traitee (1.1).
2. `-06`, regle 1 : la premisse n'etait pas liee a la verite de ce qu'elle affirme (1.2).
3. `-08`, regle 2 : les trois formes d'alerte contredisaient `NRC-CE1-1`, que j'ai ecrite ; je les avais lues contre
   `DEL-CE1-1` seule (2.1).
4. `-08`, regle 2 : *"garder l'appareil loin de l'eau"* se lit comme deplacer un appareil branche au bord de l'eau (2.2).
5. `-08`, regle 2 : *"applique a l'objet de la situation"* et *"jamais un geste sur l'appareil"* etaient ambigus (2.2).
6. `-08`, regle 3 et 2.5 : *abstention* et *distracteur negatif* n'etaient definis ni l'un ni l'autre, et mon 2.2 nommait
   *attendre* la ou il voulait dire *attendre sans objet* (2.4).
7. `-08`, regle 2 : la parenthese des mains mouillees suivait une forme sur trois (2.3).

**Aucune de ces corrections ne releve une barre que l'equipe aurait atteinte** : aucun item n'existe. Celles qui
restreignent (3, 4) appliquent l'exactitude et une condition deja ecrite ; celles qui admettent (1, *"ne pas toucher
l'appareil"*) ouvrent ce que ma lettre fermait sans motif.

## 4. Textes a reporter dans les stories, par `camille-po`

### 4.1 `S57-BR-QLM-CE1-06`, critere 10 (regles de contenu de `nadia-prof`)

Remplacer la regle 3 par :

> 3. **Les distracteurs ne se reconnaissent pas sans la competence** [corrige le 2026-10-27, `nadia-prof`,
>    `arbitrage-nadia-contradictions-ce1-qlm-06-08.md`, section 1.1]. Dans un item de A, les deux distracteurs n'ont en
>    commun aucun mot de leur nom que la bonne reponse n'a pas (refuse : *le trombone* parmi *le papier d'aluminium* et
>    *la barquette en aluminium* ; *le clou* parmi *le papier d'aluminium* et *la feuille de papier*), ni une forme, une
>    taille ou un usage que la bonne reponse n'a pas (refuse : *la gomme* parmi *l'aiguille* et *l'épingle*, deux objets
>    pointus de couture). Ce que les deux distracteurs ont en commun parce que la question le demande n'est pas un trait
>    partage : au negatif (*"... n'attire-t-il pas ?"*), les deux distracteurs sont deux objets en fer, et c'est admis.
>    Dans un item de B, le bon groupe ne se reconnait pas a ce qu'il partage le plus d'objets avec les deux autres.

Ajouter a la fin de la regle 1 :

> La premisse (*"Ces trois objets sont en métal."*, *"Tous ces objets sont en métal."*) n'est ecrite que si tous les
> objets des propositions sont en metal : devant *la gomme* ou tout objet sans metal, elle est une erreur de contenu,
> refusee [ajoute le 2026-10-27, `nadia-prof`, `arbitrage-nadia-contradictions-ce1-qlm-06-08.md`, section 1.2]. Un
> enonce hors des formes de la regle 2 est refuse jusqu'a l'accord ecrit de `nadia-prof`.

Ajouter a la fin de la regle 5 :

> Les mots *pièce*, *monnaie*, *canette*, *clé*, *couvert*, *cuillère*, *fourchette*, *couteau*, *casserole*, *bijou*,
> *bague*, *collier*, *bracelet* et *or* ne sont employes dans aucun sens, meme quand ils ne designent pas l'objet exclu
> (*la pièce* d'une maison). *Doré* n'est employe nulle part [ajoute le 2026-10-27, `nadia-prof`,
> `arbitrage-nadia-contradictions-ce1-qlm-06-08.md`, section 1.5].

Ajouter a la fin de la regle 2, tache B :

> Un groupe porte au plus six mots (`LON-CE1-5`, compte de `PaquetLu.mots`) : *"fil de fer, fil de cuivre, clou"* (7)
> est refuse, *"fil de fer, papier d'aluminium, clou"* (6) passe [ajoute le 2026-10-27, `nadia-prof`,
> `arbitrage-nadia-contradictions-ce1-qlm-06-08.md`, section 1.6].

### 4.2 `S57-BR-QLM-CE1-08`, critere 9 (regles de contenu de `nadia-prof`)

Dans la regle 1, tache B, remplacer *"la bonne reponse est un des quatre gestes de `DEL-CE1-1`"* par *"la bonne reponse
est une forme de la regle 2"*, et ajouter :

> Un item des mains mouillees declare la situation `eau`. Un enonce porte une seule situation. Au moins un item de B de
> situation `eau` ne parle pas de mains mouillees [ajoute le 2026-10-27, `nadia-prof`,
> `arbitrage-nadia-contradictions-ce1-qlm-06-08.md`, section 2.3].

Remplacer la regle 2 par :

> 2. **Les gestes, liste fermee de formes** [corrige le 2026-10-27, `nadia-prof`,
>    `arbitrage-nadia-contradictions-ce1-qlm-06-08.md`, sections 2.1 a 2.3]. La bonne reponse de B est une de ces formes,
>    et aucune autre :
>    - situation `fil` : *"ne pas toucher le fil"*, *"laisser le fil sans le toucher"* ;
>    - situation `prise` : *"ne pas toucher la prise"*, *"ne rien mettre dans la prise"* ;
>    - situation `eau`, l'appareil est deja branche pres de l'eau : *"ne pas toucher l'appareil"* ;
>    - situation `eau`, le personnage veut poser ou utiliser un appareil pres de l'eau : *"ne pas l'approcher de l'eau"* ;
>    - situation `eau`, l'enonce dit que le personnage a les mains mouillees : *"attendre d'avoir les mains sèches"*,
>      *"se sécher d'abord les mains"*, et *"ne pas brancher la lampe"* quand l'enonce nomme la lampe.
>
>    Une forme qui nomme un objet nomme celui de l'enonce. Aucune forme ne demande a l'enfant d'agir sur l'objet
>    electrique ou sur l'installation (ni reparer, ni debrancher, ni brancher, ni deplacer, ni ouvrir) : c'est une
>    abstention ou un soin de ses propres mains. Les mains mouillees vont dans l'enonce ; *"ne pas brancher les mains
>    mouillées"* est refuse. *Attendre* seul, *s'éloigner*, *ne rien faire* ne sont pas des formes. Une autre forme se
>    soumet a `nadia-prof` avant le premier jet.
>
>    **Aucune alerte a un adulte n'est une proposition** (*"prévenir un adulte"*, *"appeler un adulte"*, *"le dire à un
>    adulte"* et toute autre forme), ni en bonne reponse ni en distracteur : `cp-emc-alerte` a cette bonne reponse a la
>    meme tache, et l'item servirait la ligne de CP (`NRC-CE1-1`). L'alerte niee en distracteur reste admise (regle 3).

Remplacer la regle 3 par :

> 3. **Distracteurs (`TRV-CE1-2`)** [corrige le 2026-10-27, `nadia-prof`,
>    `arbitrage-nadia-contradictions-ce1-qlm-06-08.md`, section 2.4]. Jamais deux formes de la regle 2 dans un meme item.
>    **Aucune abstention en distracteur** : une proposition qui laisse l'enfant hors du danger sans rien faire de risque
>    (*s'éloigner*, *attendre*, *ne rien toucher*, *ne pas s'approcher*, *continuer à jouer ailleurs*) serait une seconde
>    reponse. **Un distracteur negatif est admis quand ce qu'il refuse est une precaution** : il laisse le danger en place
>    ou le cache (*"ne rien dire à un adulte"*, *"ne pas déranger un adulte"*) ; il ne nie jamais la bonne reponse du
>    meme item ni une autre forme de la meme precaution. Les autres distracteurs de B sont des gestes de reparation, de
>    manipulation ou d'imprudence qu'un enfant pourrait faire. La bonne reponse n'est jamais la seule des trois
>    propositions a commencer par *ne pas*, *ne rien* ou *ne jamais*. En A, les deux distracteurs n'ont pas en commun un
>    mot ou un trait que la bonne reponse n'a pas.

Ajouter a la fin de la regle 5 :

> *Blessé*, *blessée*, *blessés*, *blessées* ne sont employes pour personne, nomme ou non. Un personnage nomme ne *se
> blesse* ni ne *se fait mal*, a aucun temps (*"Lina se blesse avec le fil."* est refuse). Le present generique (*"on se
> blesse"*, *"on peut se faire mal"*) et l'infinitif (*"pour ne pas se blesser"*) passent [ajoute le 2026-10-27,
> `nadia-prof`, `arbitrage-nadia-contradictions-ce1-qlm-06-08.md`, section 2.6].

## 5. Ajustements demandes a `theo-archi` (forme de test, a lui)

| Code | Lot, garde | Doit refuser | Doit passer |
|---|---|---|---|
| `T1` | `-06`, nouvelle, ou dans `g4` : premisse vraie | *"Ces trois objets sont en métal. Lequel l'aimant attire-t-il ?"*, *le clou* ; *la gomme*, *le fil de cuivre* | le meme enonce, *le clou* ; *le papier d'aluminium*, *le fil de cuivre* |
| `T2` | `-08`, table des formes (3.0 point 3) | *"garder l'appareil loin de l'eau"* en bonne reponse ; *"prévenir un adulte"*, *"appeler un adulte"*, *"le dire à un adulte"* en bonne reponse **et** en distracteur | *"ne pas toucher l'appareil"* sous un enonce `eau` ; *"ne rien dire à un adulte"* en distracteur |
| `T3` | `-08`, `g3` | - | remplacer sa sonde (*"ne pas toucher le fil"* et *"prévenir un adulte"*), que `T2` refuse desormais aussi, par *"ne pas toucher le fil"* et *"laisser le fil sans le toucher"* dans un meme item, pour que `g3` garde une sonde qu'elle seule mord |
| `T4` | `-08`, `g1` et `g2` | une forme des mains seches sous une situation declaree `prise` ou `fil` ; *"ne pas brancher la lampe"* sous un enonce de mains mouillees qui ne nomme pas la lampe ; le paquet reel prive de ses items de B `eau` dont l'enonce ne porte pas `mouill` | *"se sécher d'abord les mains"* sous *"Inès a les mains mouillées et veut brancher la lampe. Que doit-elle faire ?"*, situation `eau` |
| `T5` | `-08`, `g7` | *"Lina se blesse avec le fil."* ; *"Sami se fait mal."* (present conjugue de *se blesser*, *se faire mal*, sauf apres *on*) | *"On se blesse avec un fil abîmé."* ; *"On peut se faire mal."* ; *"pour ne pas se blesser"* ; *"pour ne pas se faire mal"* |

**Inchange, confirme** : `Q1` (formes fermees de `-06`), `Q2` (`g9`), les trois sondes de `Q3`, `Q4` dans sa partie deja
ecrite, `Q5` (`g2` dans tout sens, *doré* non garde), `Q6` (*blessé* quel que soit le sujet). Aucune garde neuve de
longueur : `LON-CE1-5` est tenue par le test transverse.

**Ce dont je reste la seule garde, en plus de 1.7 et 2.7 de mon arbitrage** : la forme et l'usage partages par deux
objets en fer en A negatif (1.1) ; qu'un distracteur de `-08` laisse ou non l'enfant hors du danger (2.4), au-dela de la
liste de `T2` ; qu'un enonce `eau` dise bien *deja branche* ou *veut poser* selon sa forme (2.2) ; une alerte a un adulte
ecrite autrement que les trois formes de `T2` (*"aller voir un adulte"*) ; un personnage nomme blesse par une periphrase.
**Aucune n'est couverte par un test.**

## 6. Cout et duree

**Duree**, mesuree par `date` : debut **2026-10-27 08:03:38**, ecriture de ce fichier a partir de 08:11:44, fin
**08:20:45**, avant le commit. **Duree : 17 min 07 s**, dont deux mesures : le garde-fou des identifiants
(`bash outils/mvn-jdk21.sh -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` :
*"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS`, ce fichier present dans l'arbre ; il n'ecrit
aucun identifiant neuf) et `.github/scripts/verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte
dans les fichiers verifies."*

**Cout : 0,00**, par la decision de l'utilisatrice du 2026-10-27 : les arbitrages avant premier jet ne sont pas chiffres.
Je ne chiffre rien d'autre.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
