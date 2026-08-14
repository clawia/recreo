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

## Amendement du 2026-08-14 (s10, cloture, apres verification de theo-archi) - la clause 1 n'est pas tenue seulement a la revelation ; l'ecran de reprise porte le meme defaut, et la levee le couvre

`theo-archi` a mesure l'invariante qu'il formalise dans `ADR-0038` non sur le gabarit, mais sur le
document assemble par le controleur reel, pour tout ecran ou une partie est en cours. J'ai verifie
moi-meme les deux faits qu'il rapporte, en lisant `accueil.html`, `fragments/manche.html` et
`PartieController.java` :

- **Le document porte deux sorties.** `a.btn.lien-accueil` dans l'en-tete permanent d'`accueil.html`
  (`hx-post="/retour"`) et `button.btn.retour-accueil` dans `fragments/manche.html`
  (`hx-post="/retour"`). La premiere est masquee par la regle de la feuille maison
  `body:has(.ecran-de-jeu) .lien-accueil { display: none }`, des que la zone de contenu porte le
  marqueur `ecran-de-jeu` - ce qui est vrai sur tout ecran de manche, y compris quand ce fragment est
  rendu a l'interieur d'un `accueil.html` complet. Le premier arret de tabulation reel est donc, sur
  tout ecran de manche, **le meme bouton** : `button.btn.retour-accueil`.
- **`GET /` pendant une partie en cours rend exactement ce fragment.** Dans `PartieController`, la
  methode `accueil()` teste `etat.portePartieEnCours()` ; si vrai, elle pose `reprise=true` et rend
  `accueil.html`, qui inclut `fragments/manche :: manche` par `th:replace`. C'est un chargement de
  page complet : rien n'y est focalise au depart. La clause 1 d'`ADR-0038` - "`A[0]` n'est pas
  destructeur" - couvre exactement ce cas, au meme titre que le rebouclage depuis la derniere
  proposition sur l'ecran de revelation : sur les deux ecrans, une premiere Tabulation puis Entree
  atteint "Les jeux" et detruit la partie sans confirmation.

**Ce que j'ecrivais au point 2 de la decision ci-dessus n'etait donc pas exact : "la clause 1
elle-meme reste tenue partout ailleurs qu'a la revelation" ne l'etait pas au moment ou je l'ai
ecrit - je n'avais mesure que l'ecran que `nadia-prof` avait elle-meme mesure, pas celui qu'elle n'a
pas rejoue.** `theo-archi` signale ce second ecran comme un fait technique, sans le verser a son
releve : l'evaluation pedagogique lui revient, et il ne s'y substitue pas.

**La levee de la clause 1 de M13, prononcee au point 1 de la decision, couvre donc les deux
ecrans : celui de revelation et celui de reprise (`GET /` pendant une partie en cours).** C'est le
meme defaut structurel - le meme premier arret de tabulation, la meme requete destructrice -, et non
deux defauts distincts ; le restreindre au seul ecran mesure par `nadia-prof` donnerait une borne
fausse, pas une borne prudente. Rien d'autre n'est rouvert : les quatorze cas du premier refus, les
clauses 2, 3, 4 et 5 de M13, et la clause 1 elle-meme partout ailleurs qu'aux deux ecrans nommes ici,
restent tenus et ne sont pas remis en cause.

### Declencheur de reouverture, complete

Le declencheur ecrit plus haut exigeait une reverification "au protocole exact de `nadia-prof` [...]
sur les 25 revelations". Ce protocole ne porte que sur l'ecran de revelation ; une reverification qui
s'y limiterait ne verrait donc jamais l'ecran de reprise, alors que la levee couvre desormais les
deux. J'ajoute une quatrieme condition au declencheur, cumulative avec les trois deja ecrites :

- **La reverification couvre aussi l'ecran de reprise** : un chargement de `GET /` pendant une
  partie en cours, ou aucun focus n'est pose au depart, suivi d'une Tabulation puis d'une Entree. Je
  ne prescris pas la methode - clavier reel comme le reste de son protocole, ou tout autre moyen
  qu'elle juge equivalent - cette methode reste a `nadia-prof`.

### La dette, en stories - perimetre corrige

Les deux stories versees a `docs/backlog/backlog.md` sous "Dette ouverte par DEC-0026" portaient,
dans leur redaction du 2026-08-13, sur le seul ecran de revelation. Une note distincte, dans ce
meme fichier de backlog, met a jour leur perimetre pour qu'il nomme les deux ecrans ; le detail n'est
pas repete ici.

### Impact de cet amendement

- Ce fichier : section ajoutee, rien efface des sections d'origine du 2026-08-14.
- `squad/decisions/produit/DEC-index.md` : note d'amendement ajoutee, ligne DEC-0026 mise a jour.
- `docs/backlog/backlog.md` : les deux stories sous "Dette ouverte par DEC-0026" sont mises a jour
  pour nommer les deux ecrans.
- Aucun Done pedagogique ni ligne de `squad/programme/MATRICE-couverture.md` n'est modifie par cet
  amendement.

## Report du 2026-08-14 (s11, cloture) - le declencheur de reouverture n'est pas satisfait ; la levee est reconduite a s12 sur decision de l'utilisatrice

### Ce que `sofia-sm` a mesure a la cloture de s11

`sofia-sm` verifie les quatre conditions du declencheur ci-dessus contre l'etat reel du depot
(`squad/sprints/s11-la-tabulation-ne-detruit-plus-la-partie/review.md`, section "Declencheur de
reouverture de DEC-0026") :

- **Condition 1 (au moins une des deux stories planifiee pour s11) : satisfaite.** Les deux
  stories sont planifiees et livrees - `marc-dev` (`687b4be`, confirmation avant abandon, aucun
  arret de tabulation destructeur) puis `lea-dev` (`4eaddf0`, ecart de 44 px et texte arbitre sur
  l'ecran de confirmation).
- **Condition 3 (report a nommer si aucune story livree) : sans objet a la lettre.** Les deux
  stories sont livrees ; cette condition, telle qu'ecrite, ne se declenche donc pas telle quelle.
- **Conditions 2 et 4 (reverification au clavier reel, sur les 25 revelations et sur l'ecran de
  reprise, de `nadia-prof`) : non satisfaites, faute de preuve sur le depot.** Aucun fichier ni
  commit ne porte une reverification posterieure a `4eaddf0`. Le seul bloc de `nadia-prof` qui
  existe dans `squad/conformite/FICHE-bandit-manchot-additions-cp.md` pour ce sprint est un
  arbitrage prealable du 2026-08-14, rendu en jouant un jar reconstruit sur la forme et le texte
  **proposes avant leur correction** - il porte sur un texte que `nadia-prof` a elle-meme refuse
  (*"tu perds ta partie"*, quatre motifs), pas sur *"Si tu pars, ta partie s'arrete."*, le texte
  finalement livre par `lea-dev`. Ce n'est pas un Done pedagogique sur l'increment livre : ni
  `sofia-sm` ni moi ne le traitons comme tel.

### Ce qui est acquis, et ce qui ne l'est pas - a ne pas confondre

**Acquis, verifie et tenu :**

- Le remede technique est livre : `ADR-0038` (amende le 2026-08-14 par `theo-archi`) etablit que
  la clause 1 de M13 tient sur les cinq ecrans mesures, revelation et reprise compris.
- Ce remede est garde par la CI : 416 tests, 28 regles ArchUnit, garde-fou de chemins en code 0,
  confirmes independamment par `sofia-sm` et par `yanis-qa` sur l'arbre fusionne final (`14cd800`).
- `AucunArretDeTabulationNEstDestructeurTest` rougit sur mutation du corpus (5 echecs sur 6) et le
  test de texte de confirmation rougit sur mutation d'un mot : ce ne sont pas des garde-fous qui
  passent au vert par construction.
- La forme (44 px) et le texte (*"Si tu pars, ta partie s'arrete."*) livres par `lea-dev`
  correspondent aux deux conditions que `nadia-prof` a chiffrees et nommees dans son arbitrage
  prealable (CONF-1, CONF-2, CONF-3).

**Non acquis, et ce report ne le rend pas acquis :**

- **Aucun Done pedagogique n'est prononce sur l'increment de confirmation tel qu'il est livre.**
  Ce n'est pas un refus : c'est l'absence de tout verdict sur ce texte et cette forme precis, une
  fois corriges.
- **La reverification au clavier reel exigee par `DEC-0026` - sur les 25 revelations de son
  protocole et sur l'ecran de reprise - n'a pas ete faite ou n'a pas ete tracee sur le depot**,
  posterieurement a `4eaddf0`.
- Ces deux etats sont independants du Double Done du projet (`CLAUDE.md`) : la CI verte ne se
  substitue pas au verdict de `nadia-prof`, et ce report ne la fait pas parler a sa place.

### Ce que j'ai porte a l'utilisatrice, et sa decision

`DEC-0026` prevoit qu'a defaut de preuve a la cloture de s11, la levee revient devant
l'utilisatrice comme un report a nommer explicitement plutot que de se reconduire en silence. Je
lui ai porte ce manque, en proposant de faire rejouer `nadia-prof` immediatement sur l'increment
livre. **Sa decision, du 2026-08-14 : reporter la reverification a s12.**

**La levee de la clause 1 de M13, sur les deux ecrans (revelation et reprise), est donc
reconduite au sprint 12 par decision expresse de l'utilisatrice.** Le verdict `REFUSE` du
2026-08-13 de `nadia-prof` reste ecrit, entier et non conteste sur le fond ; la reverification de
`nadia-prof` sur l'increment tel qu'il est livre reste due. Rien n'est retire des deux sections
"Decision" et "Ce qui est acquis" ecrites plus haut dans ce fichier : elles restent en vigueur
sans changement.

### Un premier report, pas un troisieme : ma position sur son cout

`DEC-0026` a ecrit cette clause precisement pour empecher qu'un manque de preuve se reconduise en
silence sprint apres sprint, comme `DEC-0007` l'a laisse arriver trois fois sur la couverture
programme. Voici sa premiere invocation reelle - pas la troisieme. Je ne traite pas les deux
situations de la meme facon, et je dis pourquoi.

**Ce report-ci ne doit rien couter de plus qu'un premier report.** Sa cause n'est pas un manque de
capacite qui se represente identique sprint apres sprint (le motif de `DEC-0007`, ou deux
obstacles routes "au backlog" n'etaient repris par personne) : ici, les deux stories de dette ont
ete planifiees et livrees des le premier sprint ou `DEC-0026` l'exigeait, le remede technique est
reel et garde par 416 tests, et ce qui manque est une verification courte, deja protocolee par
`nadia-prof` elle-meme, qui n'a simplement pas ete rejouee apres la derniere correction de forme et
de texte. Traiter ce manque comme aussi grave qu'une dette non instruite pendant trois sprints
reviendrait a confondre un retard d'une ceremonie sur un protocole existant avec l'absence totale
de traitement d'un sujet.

**Mais accepter cela sans contrepartie ouvrirait exactement la porte que la clause devait fermer.**
Si ce meme manque se represente une seconde fois - la reverification toujours ni faite ni tracee
a la cloture de s12 - alors ce ne serait plus un retard d'une ceremonie : ce serait la meme cause
qui se reproduit malgre un signalement explicite, la definition meme de ce que `DEC-0007` nomme
"une direction que personne n'a choisie". **Je pose donc que le prochain manque, s'il se produit,
coute plus qu'un simple report** : voir le declencheur complete ci-dessous.

### Declencheur de reouverture, complete pour s12

Les quatre conditions deja ecrites (trois initiales, plus la quatrieme de l'amendement du
2026-08-14) restent la mesure de reference. J'y ajoute ce qui s'applique specifiquement au
sprint 12 :

- **Condition suffisante de cloture de la dette, quelle que soit son issue** : `nadia-prof`
  reverifie, au clavier reel, sur l'increment tel qu'il est livre (poste au commit `4eaddf0` ou
  posterieur), les 25 revelations de son protocole (sept jeux, tirages repetes sur "Calcul CP" et
  "Le bon verbe") et l'ecran de reprise, et elle l'ecrit dans
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md` (et dans la fiche de "Le bon verbe" si
  ce jeu est concerne). Que son verdict soit un Done pedagogique ou un refus motive sur ce point
  precis, la dette de gouvernance ouverte par ce report est fermee dans les deux cas : un refus
  motive n'est pas un manque de preuve, c'est une preuve. Un refus, le cas echeant, est traite
  comme n'importe quel refus pedagogique - transforme en story corrective ou en `DEC` par moi -
  sans rouvrir cette decision une fois de plus.
- **Si, a la cloture de s12, aucune reverification n'est encore tracee sur le depot** - le meme
  manque qu'a s11, malgre ce report nomme - **je ne le reporte pas une troisieme fois.** Je
  proposerai alors a l'utilisatrice, non pas un nouveau report, mais le basculement par defaut du
  statut de cette dette : de "levee assumee en attendant preuve" a "revertie faute de preuve", ce
  qui replace la clause 1 de M13 en condition bloquante de plein droit sur les deux ecrans, jusqu'a
  ce que la reverification existe. Le cout d'un second manque n'est donc plus une ligne de plus
  dans un fichier : c'est la levee elle-meme qui cesse d'etre assumee par defaut.

### La tache de reverification due a s12

Ce qui suit decrit ce que la tache doit couvrir, pas comment `nadia-prof` doit proceder : son
protocole lui appartient et n'est pas reecrit ici.

- **Perimetre** : l'increment de confirmation tel qu'il est livre a la cloture de s11, posterieur
  au commit `4eaddf0` (44 px d'ecart reellement vide entre les boutons, texte "Si tu pars, ta
  partie s'arrete.").
- **Ce qu'elle doit couvrir, au minimum, comme `DEC-0026` l'exige deja** : les 25 revelations de son
  protocole (une par jeu sur les sept jeux, plus les tirages repetes sur "Calcul CP" et "Le bon
  verbe") et l'ecran de reprise (`GET /` pendant une partie en cours, rien focalise au chargement),
  au clavier reel, Tabulation puis Entree depuis le point de focus.
- **Ce que la tache produit** : une section datee dans
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md` (et, le cas echeant,
  `FICHE-bandit-manchot-conjugaison-present-cm1.md`), portant un verdict explicite sur ce point -
  Done pedagogique ou refus motive - distinct de l'arbitrage prealable du 2026-08-14 qui portait sur
  un texte deja refuse.
- **A planifier par `sofia-sm`** comme un engagement du sprint 12, pas une intention : c'est
  exactement le defaut que `DEC-0026` nommait deja pour s11 ("planifiee, pas laissee non affectee
  a un sprint"), applique cette fois a la reverification elle-meme plutot qu'aux deux stories de
  code, qui sont livrees.

### Impact de ce report

- Ce fichier : section ajoutee, rien efface des sections precedentes.
- `squad/decisions/produit/DEC-index.md` : ligne DEC-0026 mise a jour, note de report ajoutee.
- Aucun Done pedagogique ni ligne de `squad/programme/MATRICE-couverture.md` n'est modifie par ce
  report. Aucun verdict ni releve de `nadia-prof` n'est modifie. Le `review.md` de `sofia-sm` sur
  s11 n'est pas modifie.
