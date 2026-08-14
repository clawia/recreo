# DEC-0026 - Levee de la clause 1 de M13 de nadia-prof, bornee au seul ecran de revelation : une perte de partie sans confirmation, assumee par ecrit

- **Type** : levee d'une condition bloquante pedagogique, bornee a un seul ecran ; denouement du
  second refus du sprint 10
- **Date** : 2026-08-14
- **Auteur** : camille-po
- **Sprint** : s10 (cloture)
- **Competence programme visee** : transverse - aucune ligne de
  `squad/programme/MATRICE-couverture.md` ne bouge par cette decision.
- **Statut** : Acceptee

## Contexte

`nadia-prof` a rendu un **second verdict** sur le chantier du bandit manchot, apres le lot qui
repondait a ses deux conditions bloquantes du matin (M4, levee par `DEC-0025` ; M13 clause 1,
routee a `theo-archi` et `marc-dev`) :
**REFUSE**, sur **un seul motif bloquant** (`squad/sprints/s10-la-forme-du-bandit-manchot/
review.md`, section "Second verdict du 2026-08-13", branche `s10-nadia-verdict-2` ;
`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Second verdict du 2026-08-13").

**Increment audite** : `round-two` a `643c6f4`, arbre propre. `nadia-prof` a reconstruit le jar
depuis son propre worktree avant de mesurer, apres avoir constate qu'un faux negatif de
`marc-dev` et une mesure d'elle-meme la veille venaient toutes deux d'un jar perime. `mvn -B
verify` : **BUILD SUCCESS**, aucun test en echec. **Le Done technique est acquis.**

Les quatorze cas de son premier refus (Tabulation puis Entree, apres une bonne et une mauvaise
reponse, sur les sept jeux) sont **tous verts** : la panne de `recreo.js` charge avant `<body>` est
corrigee, et les clauses 2, 3, 4 et 5 de M13 sont **tenues pour la premiere fois de ce projet**,
mesurees au clavier. Sur trente-huit conditions au total, **trente-cinq sont tenues ou sans
objet**, une est levee par la porte produit sans etre reparee (M4, `DEC-0025`), une porte un
defaut non bloquant (M14 clause 5, versee au backlog plus bas), et **une seule bloque encore**.

### Le motif, et il n'est pas de meme nature que M4

**M13 clause 1, sur l'ecran de revelation de M5.** Le focus va bien sur la proposition qui porte
la reponse revelee (clause 5, tenue 25 fois sur 25). Mais **quand cette proposition occupe la
derniere position de l'ordre de tabulation, la premiere tabulation depuis elle atteint le bouton
"Les jeux", et Entree detruit la partie sans confirmation.**

La loi que `nadia-prof` a verifiee sans exception sur 25 revelations : la partie est detruite **si
et seulement si** la proposition portant la reponse revelee occupe la derniere position de l'ordre
des propositions. Aucune autre variable n'intervient - ni le jeu, ni le contenu, ni le nombre
d'essais.

| Mesure | revelations | revelee en derniere position | partie detruite |
|---|---|---|---|
| une par jeu, sept jeux | 7 | 3 | **3** |
| "Calcul CP", revelations repetees | 10 | 5 | **5** |
| "Le bon verbe", revelations repetees | 8 | 2 | **2** |
| **total** | **25** | **10** | **10** |

**Les sept jeux sont concernes**, a une frequence qui suit la position tiree de la bonne reponse.

## Ce que ce motif coute, et je ne l'adoucis pas

Ce n'est pas un defaut de mise en page comme le pli de M4, ou tout remede mesure coute quelque
chose ailleurs dans la page. **C'est un enfant qui navigue au clavier, ou avec une aide technique -
contacteur, lecteur d'ecran -, qui perd sa partie.** L'ecran de revelation est le moment ou cet
enfant a deja epuise ses essais, celui ou il est le plus en difficulte, et celui ou le jeu lui
demande explicitement un geste ("Appuie sur 8."). Presser Tabulation pour aller chercher la sortie
puis Entree, geste ordinaire d'un enfant qui explore un clavier, **detruit sa partie et son score,
sans qu'il ait rien demande de tel.**

C'est exactement le motif que `nadia-prof` porte depuis son veto sur la manette, ou elle ecrivait
qu'*"un enfant de CP qui essaie de tirer une manette et voit que rien ne se passe se croit en
faute"*, et depuis qu'elle a refuse toute mise en oeuvre - *"quelle que soit sa forme"* - qui
retirerait une proposition du rendu, *"parce qu'un appui peut alors tomber sur une reponse que
l'enfant n'a pas choisie"*. Le fil commun aux trois refus est le meme : un enfant qui agit sur cet
ecran ne doit jamais subir une consequence qu'il n'a pas choisie et ne comprend pas.

**Ce qu'elle ecrit sur elle-meme, et je le reprends sans le reformuler.** Sa clause 1 disait
*"Jamais la sortie, sur aucun chemin. Apres une reponse, quel qu'en soit le verdict"*, et sa
clause 5 ajoutait que le geste demande par M5 doit rester atteignable *"sans traverser la sortie -
et la clause 1 continue de s'appliquer."* Le chemin etait donc ecrit d'avance. **"Je ne deplace
pas mes poteaux, et je dis ou etait mon manque" : elle avait ecrit "quatorze cas sur quatorze" et
mesure quatorze cas, pas vingt-cinq.** `marc-dev` ne pouvait pas lire ce defaut restant dans son
releve du matin ; il pouvait le lire dans sa clause. Elle ne le compte pas contre lui, et je ne le
compte pas non plus : ce qui manquait est sa mesure, pas sa barre.

## Les deux remedes ecartes, avec leur cout - pour qu'ils ne soient pas re-instruits

`nadia-prof` designe la propriete a tenir (depuis le point de focus, la premiere tabulation ne
mene jamais a la sortie) et refuse explicitement de choisir le moyen : *"le moyen appartient a
`theo-archi` - ordre du document, ordre de tabulation, place du bouton de sortie, confirmation
avant d'abandonner : je n'en designe aucun."* Ce n'est pas a moi non plus de choisir la mise en
oeuvre. Ce que j'ecris ici est le cout de chacune des deux pistes qu'elle nomme et que sa reserve O
(`squad/conformite/FICHE-bandit-manchot-additions-cp.md`) recoupe, pour qu'aucune des deux ne soit
redebattue depuis zero :

1. **Demander confirmation avant de quitter une partie en cours, sur tout chemin de sortie.**
   Traite la cause a la racine - `abandonner()` ne detruirait plus jamais la partie d'un seul
   geste, qu'il vienne du clavier, de la souris ou de `GET /`. **Ce remede ferme aussi la reserve O
   de `nadia-prof`** : *"il n'existe aucun chemin vers le catalogue qui ne detruise pas la partie
   [...] c'est le meme enjeu que M13 clause 1, vu depuis la souris au lieu du clavier, et M13 ne le
   couvre pas."* Cout : une decision d'ergonomie neuve (forme de la confirmation, ce qu'elle
   affiche, comment un enfant de CP la lit) qui n'existe dans aucun ecran du produit aujourd'hui -
   route a `ines-design` pour la forme, a `nadia-prof` pour le jugement pedagogique du texte.
2. **Corriger l'ordre de tabulation, seulement sur l'ecran de revelation.** Remede etroit : il fait
   tenir la clause 1 exactement la ou `nadia-prof` l'a mesuree non tenue, sans toucher au reste de
   l'application. **Il laisse la reserve O entierement ouverte** - tout chemin de sortie hors
   clavier (souris sur "Les jeux", `GET /` pendant une partie) continue de detruire la partie sans
   confirmation, exactement comme aujourd'hui. Cout : un remede qui referme le symptome mesure ce
   jour sans traiter la cause que `nadia-prof` nomme elle-meme au meme endroit.

## Decision

**1. La clause 1 de M13 est levee comme condition bloquante pour la livraison de l'increment du
sprint 10, sur decision expresse de l'utilisatrice qui a joue l'increment et choisit de le livrer
en connaissance du defaut ci-dessus - je reprends le meme principe de gouvernance que
`DEC-0025` a applique a M4, que `nadia-prof` a elle-meme pose a plusieurs reprises ce sprint :
*"si l'utilisatrice tranche [...] mon veto ne survit pas a sa decision - je ne detiens pas la
porte produit."***

**2. La levee est bornee au seul ecran de revelation de M5, et a rien d'autre.** Les quatorze cas
du premier refus de `nadia-prof` restent tenus et ne sont pas rouverts ; les clauses 2, 3, 4 et 5
de M13 restent tenues et ne sont pas rouvertes ; la clause 1 elle-meme reste tenue partout ailleurs
qu'a la revelation - elle n'est levee que sur cet unique chemin, ou une proposition en derniere
position de tabulation conduit a "Les jeux" puis, sur Entree, a la destruction de la partie.

**3. Cette levee ne prononce, ne simule et ne remplace aucun Done pedagogique.** Le verdict
`REFUSE` de `nadia-prof` du 2026-08-13 reste ecrit, entier et non conteste sur le fond. Aucune
ligne de `squad/programme/MATRICE-couverture.md` ne passe a `couvert-valide` du fait de cette
decision.

**4. Aucun des deux remedes ecartes n'est choisi par cette decision.** Le choix de mise en oeuvre
reste a `theo-archi`, route par `sofia-sm` ; voir "La dette, en stories" plus bas.

## Declencheur de reouverture, et il est exigeant

**Ce n'est pas un pixel, c'est une perte de donnee sans confirmation** - le declencheur ne peut
donc pas etre aussi lache que celui de M4. Trois conditions, cumulatives :

- **Au moins une des deux stories de la section suivante est planifiee pour le sprint 11**, pas
  laissee "non affectee a un sprint" comme la dette de M4 : `sofia-sm` en fait un engagement du
  prochain sprint, pas une intention.
- **La reverification se fait au protocole exact de `nadia-prof`** : au clavier reel, Tabulation
  puis Entree, sur les 25 revelations qu'elle a rejouees (sept jeux, tirages repetes sur "Calcul
  CP" et "Le bon verbe"), pas sur un echantillon reduit - c'est exactement le manque qu'elle vient
  de corriger sur elle-meme, il ne doit pas se reproduire dans la verification de son propre
  correctif.
- **Si aucune des deux stories n'est livree a la cloture du sprint 11**, cette levee revient devant
  l'utilisatrice comme un report a nommer explicitement, plutot que de se reconduire en silence :
  un report isole est un arbitrage, mais un deuxieme report sur une perte de donnee deviendrait une
  direction que personne n'a choisie - le meme constat que celui fait sur trois sprints de report
  successifs de la couverture programme sous `DEC-0007`.

## La dette, en stories

Deux stories sont versees a `docs/backlog/backlog.md`, section "Dette ouverte par DEC-0026" :

1. **Story bloquante** - depuis le point de focus, la premiere tabulation ne mene jamais a la
   sortie, sur l'ecran de revelation comme sur les autres ecrans de manche. Moyen non prescrit.
2. **Story** - confirmation avant d'abandonner une partie en cours, sur tout chemin de sortie
   (clavier, souris, `GET /`). Ferme, si elle est retenue, la story 1 et la reserve O dans le meme
   geste.

`sofia-sm` et `theo-archi` choisissent laquelle des deux (ou les deux) planifier pour le sprint 11 ;
je ne designe pas le moyen technique.

## Ce qui est acquis et ne doit pas etre rejuge

- **Les quatorze cas du premier refus sont tous verts**, et les clauses 2, 3, 4 et 5 de M13 sont
  tenues pour la premiere fois de ce projet, mesurees au clavier.
- **La reserve L de `nadia-prof` est levee** : `RecreoJsDeplaceLeFocusTest` execute reellement
  `recreo.js`, reproduit la panne historique et nomme lui-meme ses angles morts - dont le clavier
  reel, qui est exactement ou elle a trouve le defaut restant. Elle leve cette reserve **meme si
  son verdict du jour est un refus** : un garde-fou qui nomme sa propre limite a fait son travail.
- **M21 est TENUE** : plus aucun indice sur "Le bon verbe" ne nomme la reponse ; perimetre porte
  par une cle du paquet et non par un identifiant de jeu en dur. La plainte de l'utilisatrice sur
  ce point est fermee.
- **L'accent peint du fronton est ACCEPTE**, cout nul confirme par mesure (M18 tenue).
- Aucune de ces acquisitions n'est rouverte ni durcie par la presente decision.

## Ce que je verse au backlog par ailleurs, hors du perimetre de cette decision

Trois reserves neuves et une priorite deja portee par `DEC-0025`, toutes non bloquantes, sont
versees a `docs/backlog/backlog.md` a la suite de cette decision - detail dans le backlog, pas
repete ici :

- la marque "deja essayee" n'est pas cumulative apres la revelation sur "Le bon verbe" (M14
  clause 5) ;
- le fondu de l'accent peint est du mauvais cote, arete mesuree par `nadia-prof` ;
- "Le bon son" dit deux fois la meme chose sur son ecran de revelation - seule des trois avec un
  effet pedagogique direct, et cause du non-tenue de M4 sur ce jeu (voir la correction de
  `DEC-0025` ci-dessous).

**Deux reserves de `nadia-prof` restent ouvertes et ne sont pas levables par du code**, et cette
decision ne pretend pas les traiter :

- **F11** - l'emplacement accessible de l'enseigne, faute d'ecoute d'un lecteur d'ecran que
  personne dans cette equipe n'a jamais faite ;
- **sa reserve P** - le budget vertical d'un ecran de jeu a 360 x 640, que personne n'a jamais
  instruit ni reparti.

Je ne les ferme pas et je n'ouvre pas de story de code a leur place : la premiere attend une
ecoute reelle, la seconde un arbitrage de repartition qui n'existe pas encore.

## Correction portee dans DEC-0025

`nadia-prof` a rejoue la mesure du resserrement de marges que `DEC-0025` attribuait a tort. Une
note datee est ajoutee directement dans
`DEC-0025-levee-de-la-condition-m4-le-sprint-10-est-livre-avec-un-defaut-de-pli-assume.md`, sans
rien effacer du texte d'origine - voir la section "Amendement du 2026-08-14 (s10, cloture)" de ce
fichier pour le detail chiffre. En resume : le resserrement ferme trois jeux ("Comparer les
nombres", "Les complements a 10" a 2,81 px pres, "Soustractions CP"), pas les deux que `DEC-0025`
nommait ("Calcul CP", deja ferme avant ce levier ; "Le bon son", dont l'ecran de revelation deborde
de 23,19 px, parce que l'explication et l'indice y disent deux fois la meme chose). **M4 reelle :
non tenue sur trois jeux sur sept** ("Le bon son", "Petits problemes", "Le bon verbe"), et non six
comme ecrit initialement. Cette correction ne rouvre pas la levee de M4 : `DEC-0025` reste en
vigueur, seule la mesure qui l'accompagnait est corrigee.

## Justification pedagogique

Cette decision ne juge aucun contenu et ne renverse aucun jugement pedagogique : le verdict
`REFUSE` de `nadia-prof` reste vrai et entier sur la clause 1 de M13. Le seul geste que je pose est
un arbitrage produit - livrer malgre un defaut nomme, precisement circonscrit a un seul chemin d'un
seul ecran - que `nadia-prof` a elle-meme situe hors de sa porte a plusieurs reprises ce sprint.
Le jugement sur le moyen technique de le corriger reste integralement a `theo-archi`.

## Impact

- `docs/backlog/backlog.md` : deux stories neuves sous "Dette ouverte par DEC-0026" ; trois
  reserves neuves versees a la suite.
- `squad/decisions/produit/DEC-index.md` : ligne DEC-0026 ajoutee ; note d'amendement sur
  `DEC-0025`.
- `squad/decisions/produit/DEC-0025-levee-de-la-condition-m4-le-sprint-10-est-livre-avec-un-defaut-
  de-pli-assume.md` : amendement date ajoute, rien efface.
- `squad/programme/MATRICE-couverture.md` : inchangee. Aucune ligne ne bouge.
- `squad/conformite/FICHE-bandit-manchot-*.md` (sept fiches) : inchangees par cette decision ; le
  verdict de `nadia-prof` et ses trente-huit conditions y restent tels qu'elle les a ecrits.
- `adapters/adapter-content/src/main/resources/catalogue.yaml` : inchange.

## Ce que cette decision ne fait pas

- Elle ne prononce aucun Done pedagogique et ne modifie le verdict d'aucune fiche de conformite.
- Elle ne leve la clause 1 de M13 que sur l'ecran de revelation ; partout ailleurs, la clause reste
  tenue et n'a pas besoin d'etre levee.
- Elle ne choisit pas entre les deux remedes ecartes, ni aucun autre moyen technique : `theo-archi`.
- Elle ne fait bouger aucune ligne de `squad/programme/MATRICE-couverture.md`.
- Elle ne ferme pas la reserve F11 ni la reserve P de `nadia-prof` : toutes deux restent ouvertes,
  hors de portee d'une story de code a ce jour.
- Elle ne rouvre pas la levee de M4 par `DEC-0025` : seule la mesure qui l'accompagnait est
  corrigee, dans une note datee au sein de ce meme fichier.
