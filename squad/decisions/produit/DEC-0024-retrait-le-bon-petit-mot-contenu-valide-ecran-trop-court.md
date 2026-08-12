# DEC-0024 - "Le bon petit mot" quitte le catalogue jouable : contenu valide, ecran trop court, condition de retour executable

- **Type** : perimetre produit, retrait temporaire d'un jeu du catalogue
- **Date** : 2026-08-12
- **Auteur** : camille-po
- **Sprint** : s10
- **Competence programme visee** : francais, CM1 (cycle 3), domaine "Orthographe et grammaire" -
  "Distinguer et utiliser correctement les principaux homophones grammaticaux (et/est, son/sont,
  a/à...)" (`squad/programme/francais/competences-cm1.md`). Cette decision fait bouger le statut de
  cette ligne de `squad/programme/MATRICE-couverture.md` (section "Impact" ci-dessous) ; elle ne
  rouvre aucun jugement pedagogique sur le contenu lui-meme.
- **Statut** : Acceptee

## Contexte

`DEC-0023` a laisse son point 6 ouvert : la disparition de l'ecran intermediaire, en attente
d'arbitrage prealable de `nadia-prof` avant toute ligne de code (`squad/README.md` section 10).
Ce point est desormais ferme par deux arbitrages successifs, rendus le meme jour :

- **l'arbitrage de mecanique** (`s10-nadia-mecanique`, tete `cc56d20`,
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`) pose douze conditions **M1 a M12**,
  fermees, dont une seule bloquante par elle-meme : **M4**, message, indice et revelation
  entierement au-dessus du pli, sur les huit jeux du catalogue ;
- **l'arbitrage de forme** (`s10-nadia-forme`, tete `353d53f`, meme fichier) pose douze conditions
  **F1 a F12** sur l'habillage du meuble (levier, visage, enseigne, teintes de boutons), dont deux
  bloquantes (**F5**, **F9**). Verdict rendu : **REFUSE sur la proposition telle qu'ecrite**, sur
  quatre motifs mesures, mais **le principe de chacun des cinq elements est accepte** - ce n'est
  pas un refus du produit du point 6, c'est un refus d'un dessin precis qui reste a corriger.

Entre les deux arbitrages, `ines-design` a mesure la condition M4 sur les huit jeux
(`s10-ines-meuble`, tete `e375cab`, `squad/sprints/s10-la-forme-du-bandit-manchot/
mesure-ines-budget-vertical-m4.md`) : **cinq jeux tiennent sans rien toucher** (Calcul CP, Le bon
son, Les petites soustractions, Compare les nombres, Les complements a 10) ; un resserrement de
marges de forme (consigne/enonce/bloc sous le meuble) rachete **"Petits problemes"** avec **47 px
de marge**, et rachete **"Le bon verbe"** mais **a 1 px de marge seulement** ; **"Le bon petit
mot" ne peut pas etre rachete par ce geste** : son enonce le plus long occupe a lui seul six
lignes a 2 rem (environ 288 px), cause deja tracee sous `DEC-0016` depuis s07, que ce chantier
rend bloquante plutot que de la creer.

`theo-archi` a chiffre la coexistence de deux enchainements de jeu (l'ancien pour "Le bon petit
mot", le nouveau pour les sept autres) - `1e63d824`, branche `s10-theo-domaine`,
`squad/sprints/s10-la-forme-du-bandit-manchot/avis-faisabilite-ecran-intermediaire.md`, section
10 : **1 `ADR` neuf, 4 amendements au lieu de 3, 1 cliquet, 10 sites de construction, 8 entrees de
catalogue, 2 corpus a etendre - neuf pieces la ou il y en avait trois**, et le catalogue cesse
d'etre une description de contenu pour devenir une configuration de comportement. Il nomme une
cinquieme issue, non presentee jusque-la : retirer le jeu du catalogue jouable, comme `DEC-0009`
et `DEC-0014` l'ont fait deux fois pour zero architecture, en nommant lui-meme la difference que
je reprends ci-dessous. L'utilisatrice retient cette cinquieme issue, sur l'observation de
`theo-archi` qui l'a emportee : **le retrait et le raccourcissement sont le meme travail dans un
ordre different** - la coexistence n'evite pas le raccourcissement du contenu, elle le differe en
payant des interets (un second chemin de code vivant, neuf pieces de garde-fou au lieu de trois,
un enfant qui rencontre deux comportements selon le jeu).

## Decision

### 1. "Le bon petit mot" quitte le catalogue jouable jusqu'a ce que son contenu soit raccourci

**Ce retrait n'est pas de la meme famille que `DEC-0009` et `DEC-0014`, et je l'ecris pour ce
qu'il est plutot que de laisser la ressemblance de forme masquer la difference de fond.**
`DEC-0009` (Le bon son) et `DEC-0014` (Le bon verbe) retiraient un jeu dont le contenu
**enseignait quelque chose de faux** - une regle de conjugaison contredite par un autre item du
meme paquet, un phoneme jamais joue. Ici, **le contenu de `cm1-homophones` est correct et
pedagogiquement valide** : `nadia-prof` lui a accorde un Done pedagogique **VALIDE** sans reserve
bloquante a la cloture de s06 (`squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`, verdict
du 2026-08-10), et ce verdict n'est rouvert par rien de ce qui precede. Ce que ce retrait sanctionne
n'est pas le contenu, c'est **la longueur de son enonce le plus long au regard de l'ecran que ce
sprint construit** - une question de forme, pas de fond pedagogique.

Sept jeux sur huit recoivent le meme enchainement (bonne reponse : la question suivante se charge ;
mauvaise reponse : l'enfant reste sur l'ecran de la machine, message sous le meuble). "Le bon petit
mot" n'entre pas dans ce chantier : il n'a ni le nouvel enchainement ni l'ancien tant que son
contenu n'a pas ete raccourci et rejuge.

### 2. Le mecanisme est l'absence d'entree dans `catalogue.yaml`, execution immediate, pas une intention differee

Ce mecanisme n'est pas ouvert a instruction : `theo-archi` l'a deja verifie contre le depot,
c'est celui reellement employe par `DEC-0009` et `DEC-0014`, aucun drapeau, aucun chemin mort,
aucune exemption dans une clause de garde-fou. Le paquet de contenu
(`adapters/adapter-content/src/main/resources/contenu/cm1-homophones.yaml`) reste au depot,
inchange : c'est la matiere de travail de la story de raccourcissement (point 4).

**Je verifie plutot que de supposer** : a la date de cette decision (`round-two`, `b15bda1`),
l'entree `id: bandit-manchot-homophones-cm1` est toujours presente dans
`adapters/adapter-content/src/main/resources/catalogue.yaml`. Le retrait n'a donc pas encore eu
lieu. Je ne modifie pas ce fichier moi-meme : il vit sous `adapters/`, hors de mon perimetre
d'ecriture. **Je le route a `sofia-sm` comme une action technique immediate de ce sprint, pas
comme une story a planifier** : une ligne a retirer d'un fichier YAML, zero arbitrage, zero
architecture, a executer dans le meme geste que la livraison des sept autres jeux sur le nouvel
enchainement, jamais laisse pour plus tard. C'est nommement le mode de defaillance que je refuse
de reproduire : `DEC-0009` a deja ete ecrite une fois sans que le catalogue ne bouge, et un enfant
a continue de jouer un contenu retire sur le papier jusqu'a ce qu'un tiers le remarque. Cette
decision n'est prise que si son execution l'accompagne dans le meme sprint.

### 3. Condition de retour, executable et non fondee sur un compte de caracteres

`DEC-0016` interdit nommement tout critere en nombre de caracteres, sur le motif qu'un
raisonnement par lettres manque son pire cas reel (une proposition de repli a ete refusee sur ce
seul motif en s07). La condition ci-dessous ne compte donc ni des lettres ni des mots.

**"Le bon petit mot" peut revenir au catalogue quand, sur le paquet raccourci, l'etat le plus
charge de chaque manche - message d'echec (M1/M6), indice (M1), revelation (M5) - rendu a la
largeur de reference de 360 px avec les feuilles reellement servies, mesure par
`outils/mesurer-rendu.sh 360 <url> <png> 640` (`DEC-0016`, `ADR-0028`), laisse les propositions de
reponse entierement visibles au-dessus du pli, sur la totalite des manches du paquet - la meme
methode et le meme viewport de reference qu'`ines-design` a appliques aux sept autres jeux
(`mesure-ines-budget-vertical-m4.md`).** Cette mesure porte sur le rendu, jamais sur une
declaration : elle ne devient verte qu'en jouant, ou par la meme maquette jetable qu'`ines-design`
a utilisee pour les sept autres, jamais par un garde-fou automatise qui bornerait la longueur d'un
enonce - `ADR-0028` a deja refuse cette forme de garde-fou pour le motif qu'il serait rouge sur un
contenu que `nadia-prof` a valide.

Cette condition ferme la question de **forme**. Elle ne prejuge pas du jugement pedagogique que
`nadia-prof` portera sur le contenu raccourci : un paquet raccourci est un contenu modifie, il
repasse devant elle comme un contenu neuf, sur sa barre de six points deja ecrite pour ce paquet
(`squad/programme/MATRICE-couverture.md`) et sur les criteres pedagogiques que `camille-po` aura
ecrits pour la story elle-meme (point 4). Le retour au catalogue suppose les deux : la mesure de
forme ci-dessus **et** un Done pedagogique accorde sur le paquet raccourci.

### 4. La story de raccourcissement : ouverte au backlog, non affectee

`docs/backlog/backlog.md`, section "Backlog - sprints suivants", porte une story neuve : rouvrir
`cm1-homophones.yaml` pour en raccourcir les enonces les plus longs, avec la condition de forme du
point 3 comme critere de sortie et un rappel explicite que `nadia-prof` rejuge l'ensemble du
paquet, pas seulement les items touches. Non affectee a un sprint : c'est une decision de capacite
qui appartient a `sofia-sm`, pas a moi.

### 5. Une seconde story de contenu, distincte de la premiere : les indices de "Le bon verbe" nomment la reponse

`nadia-prof` a nomme dans son arbitrage de mecanique (section 0 bis, point A) un manquement qui
lui est propre : **47 indices sur 49 des deux paquets de CM1 nomment la bonne reponse au lieu de
la manipulation qui y mene** - `cm1-conjugaison-present` 25 sur 25, `cm1-homophones` 22 sur 24 -
alors que sa condition **M2** (un indice ne nomme jamais la reponse) s'applique aux huit jeux du
catalogue. Elle avait valide ces textes en verifiant qu'ils etaient vrais, jamais qu'ils
revelaient plutot que d'aider.

Cette reserve touche deux paquets, mais je n'ouvre ici qu'**une** story neuve, distincte du
raccourcissement : celle des **25 indices de `cm1-conjugaison-present`** ("Le bon verbe", qui
reste au catalogue et n'a besoin d'aucun raccourcissement). Les 22 indices fautifs de
`cm1-homophones` ne fondent pas une troisieme story : ce paquet est deja rouvert dans son
integralite par la story du point 4, et `nadia-prof` le rejugera de toute facon comme un contenu
neuf - corriger ses indices dans le meme geste que le raccourcir evite de rouvrir le meme fichier
deux fois pour deux motifs distincts. La story ouverte ici porte donc uniquement sur le paquet qui
reste jouable sans autre changement.

Consequence immediate, valable des aujourd'hui et independante de toute story : **`cm1-conjugaison-
present` et `cm1-homophones` n'auront pas d'indice sur la question courante avant reecriture** -
c'est la condition M2 elle-meme, et elle s'applique sans attendre que la story soit livree. Ce
n'est pas un retrait de jeu : "Le bon verbe" reste au catalogue, son indice affiche simplement une
manipulation plutot qu'une reponse une fois la story livree ; en attendant, le defaut existant
n'est pas aggrave par ce sprint et n'est pas non plus corrige par lui.

### 6. Risque du sprint : la marge de "Le bon verbe" est un passage, pas une securite

**J'inscris ce point comme risque nomme de ce sprint, pas comme un acquis.** La mesure
d'`ines-design` (section 3 de sa mesure) fait tenir "Le bon verbe" sous M4 apres resserrement des
marges, mais **a un seul pixel de marge avant le pli**, sur son item le plus charge
(`"Le bon verbe"`, revelation, enonce et reponse les plus longs du paquet). Elle le dit
elle-meme : *"un passage, pas une marge de securite [...] un item futur dont l'enonce ou la
reponse serait ne serait-ce qu'un mot plus long ferait basculer cet etat sous le pli, sans
qu'aucun test du depot ne le voie"* - `ADR-0025` et `ADR-0028` confirment tous deux qu'aucun
automate de ce depot ne garde cette propriete.

Ce risque ne bloque rien aujourd'hui et ne change aucun statut de matrice : la ligne conjugaison
CM1 reste `couvert-valide`, ses douze conditions M1-M12 ne sont pas rouvertes
(`FICHE-bandit-manchot-additions-cp.md`, section 11 des deux arbitrages). Mais il doit etre lu et
non redecouvert : si une mesure ulterieure (un item ajoute au paquet, une correction de forme, un
changement de police) fait basculer cet etat sous le pli, ce sera une regression de M4 sur un jeu
deja au catalogue, a traiter avant tout Done sur ce chantier - exactement la reverification que
`nadia-prof` annonce elle-meme en section 9 de son arbitrage de mecanique. Je le nomme ici pour que
`sofia-sm` et `yanis-qa` en aient trace au sprint plutot que d'apprendre son existence au moment
ou il se realise.

### 7. Ce que cette decision ferme du point 6 de `DEC-0023`

Le point 6 de `DEC-0023` posait trois questions a l'arbitrage prealable de `nadia-prof` avant tout
code. Elles sont desormais fermees, chacune par une condition nommee de son arbitrage de mecanique
(`FICHE-bandit-manchot-additions-cp.md`, tete `cc56d20`) :

- **Question 1 (le message, et quand la reponse est revelee)** : fermee par **M5** (trois essais
  au maximum, revelation au troisieme, l'enfant presse la reponse pour avancer) et **M12** (les
  textes exacts - "Bravo !", "Ce n'est pas ça. Essaie encore !", "C'est `<reponse>`. Appuie sur
  `<reponse>`.").
- **Question 2 (l'explication, et son ecran)** : fermee par **M7** (l'explication n'apparait
  qu'apres que la reponse est acquise, jamais au-dessus d'une question neuve) - au prix d'une
  perte assumee et nommee par `nadia-prof` elle-meme (reserve B de son arbitrage) : l'enfant qui
  trouve du premier coup n'a plus d'explication sur les six items a variation de "Le bon son".
  Cette perte est versee au backlog comme reserve non bloquante, pas comme une story fermee : je ne
  cherche pas moi-meme la place que `nadia-prof` a deja refusee deux fois par ecrit.
- **Question 3 (la proposition fautive, cliquable ou non)** : fermee par **M9** (la proposition
  deja essayee est marquee, pressable, lisible, et ne nomme aucune reponse) - elle reste cliquable,
  aucun amendement d'`ADR-0026` n'est necessaire sur ce point.

**Un point que personne n'avait pose et que `nadia-prof` ajoute elle-meme, plus grave a ses yeux
que les trois questions** : sans ecran intermediaire, le jeu devient bavard sur l'echec et muet sur
la reussite. Fermee par **M11** (signal positif immediat, meme emplacement, meme force que le
signal d'echec ; une question fermee par la revelation n'est pas une reussite).

**Ce qui reste hors de ce document, explicitement** : la forme exacte du meuble (levier, visage,
enseigne, teintes) est **REFUSE sur la proposition telle qu'ecrite** par l'arbitrage de forme, sur
quatre motifs mesures (F1, F2, F5, F8, F9) - le principe de chaque element est accepte, le dessin
livre ne l'est pas encore. Ce n'est pas un jugement que je porte a la place de `nadia-prof` : la
story qui porte le meuble reste ouverte sur cette base, avec les douze conditions F1-F12 comme
critere de sortie, et le nom d'enseigne qu'elle retient ("Le Copain Malin", question 4 de son
arbitrage de forme) comme seule valeur a coder. Aucune palette n'est tranchee par ce document :
`ines-design` propose des teintes qui respectent F9 et F10, `nadia-prof` juge le rendu.

## Ce que cette decision ne fait pas

- Elle ne rejuge pas le contenu de `cm1-homophones` : son Done pedagogique du 2026-08-10 reste
  entierement valide et n'est pas rouvert par ce document.
- Elle ne prejuge pas du verdict que `nadia-prof` rendra sur le paquet raccourci, ni sur les
  indices corriges de `cm1-conjugaison-present`.
- Elle ne tranche aucune question technique : la mise en oeuvre exacte du retrait (au-dela du
  mecanisme deja verifie par `theo-archi`), le sort des amendements d'`ADR-0026`, `ADR-0027` et
  `ADR-0012` pour les sept autres jeux, et la forme exacte du meuble appartiennent a `theo-archi`,
  aux developpeurs et a `ines-design`, saisis par `sofia-sm`.
- Elle ne prononce aucun Done pedagogique et ne rouvre aucune des douze conditions M1-M12 ni des
  douze conditions F1-F12 : ces jugements restent entierement ceux de `nadia-prof`.
- Elle ne fixe pas de seuil chiffre de pixels comme condition universelle de retour au catalogue :
  la condition du point 3 porte sur la visibilite effective mesuree sur le paquet raccourci, pas
  sur une valeur inventee a l'avance.

## Impact

- `adapters/adapter-content/src/main/resources/catalogue.yaml` : l'entree
  `id: bandit-manchot-homophones-cm1` est retiree - action technique immediate de ce sprint,
  routee a `sofia-sm` dans le meme geste que cette decision, pas une story a planifier (point 2).
- `docs/backlog/backlog.md`, section "Backlog - sprints suivants" : deux stories neuves - le
  raccourcissement de `cm1-homophones.yaml` (non affectee, point 4) et la correction des 25 indices
  de `cm1-conjugaison-present.yaml` (non affectee, point 5) - et une reserve versee dans "Autres
  intentions" pour la marge d'un pixel de "Le bon verbe" (point 6).
- `squad/programme/MATRICE-couverture.md` : la ligne francais CM1 (homophones grammaticaux) passe
  de `couvert-valide` a `en cours` - le contenu reste pedagogiquement valide, mais aucun jeu
  jouable ne le couvre plus tant que le retrait n'est pas execute et le raccourcissement livre.
- `squad/decisions/produit/DEC-index.md` : ligne DEC-0024 ajoutee ; note d'amendement sur
  `DEC-0023` (point 6 ferme) et reference a `DEC-0022`, toutes deux portees par des branches
  soeurs non encore fusionnees sur `round-two` a la date de ce document.
- Aucune fiche de conformite n'est modifiee par cette decision : le bloc de validation pedagogique
  de `FICHE-bandit-manchot-homophones-cm1.md` reste tel que `nadia-prof` l'a ecrit, et n'entre pas
  dans mon perimetre d'ecriture.

## Ce qui reste non testable par construction

- Que le retrait soit la bonne reponse produit plutot que, par exemple, un raccourcissement
  accelere livre avant tout retrait, est un jugement d'opportunite que je retiens sur la base du
  chiffrage de `theo-archi` (neuf pieces de garde-fou contre une ligne de catalogue) et de son
  observation que la coexistence ne fait que differer le meme travail. Ce jugement n'est pas
  pedagogique : je ne pretends pas juger si un enfant perd plus a ne plus jouer "Le bon petit mot"
  qu'a le jouer sous une forme degradee - `theo-archi` le nomme lui-meme comme une perte
  pedagogique reelle sur les homophones CM1, et ce n'est pas a lui non plus d'en juger.
- Que la mesure du point 3 soit reellement atteinte sur le paquet raccourci ne peut se verifier
  qu'en rendant ce paquet, jamais sur une promesse : c'est la meme limite que `DEC-0016` et
  `ADR-0028` ont deja ecrite pour les sept autres jeux.

## Alternatives considerees

**Garder "Le bon petit mot" au catalogue avec l'ancien enchainement, en coexistence avec les sept
autres jeux sur le nouveau.** C'etait le choix initial de l'utilisatrice. Ecartee sur le chiffrage
de `theo-archi` (section 10 de son avis) : neuf pieces de garde-fou la ou il y en avait trois, un
second chemin de code vivant indefiniment, et l'observation que cette coexistence ne resout rien -
elle differe le raccourcissement du contenu en payant des interets d'architecture en attendant.

**Reduire `.enonce` a moins de 2 rem pour ce seul paquet, plutot que de raccourcir le contenu.**
Nommee par `ines-design` comme piste technique possible, explicitement non retenue ici : elle
toucherait l'acquis de `nadia-prof` selon lequel une proposition doit peser au moins aussi lourd
que l'enonce, une regle qui gouverne les huit jeux et pas seulement celui-ci. Une regle de forme
generale ne se defait pas pour un seul paquet sans que `nadia-prof` ne le juge elle-meme.

**Fixer un seuil chiffre de caracteres comme condition de retour.** Ecartee au meme motif que
`DEC-0016` : un compte de caracteres manque le pire cas reel, mesure par `ADR-0026` sur la largeur
de glyphes rendus plutot que sur un compte de lettres.
