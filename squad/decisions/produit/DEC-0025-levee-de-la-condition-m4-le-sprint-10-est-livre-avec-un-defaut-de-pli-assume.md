# DEC-0025 - Levee de la condition M4 de nadia-prof : le sprint 10 est livre avec un defaut de pli assume par ecrit

- **Type** : perimetre de sprint, levee d'une condition bloquante pedagogique, denouement du refus
  du sprint 10
- **Date** : 2026-08-13
- **Auteur** : camille-po
- **Sprint** : s10 (cloture)
- **Competence programme visee** : transverse - aucune ligne de
  `squad/programme/MATRICE-couverture.md` ne bouge par cette decision.
- **Statut** : Acceptee

## Contexte

`nadia-prof` a rendu son verdict de cloture sur le chantier du bandit manchot, sur l'increment
`round-two` a `2d34a15` (arbre propre, 390 tests, 28 regles ArchUnit) :
**REFUSE** (`squad/sprints/s10-la-forme-du-bandit-manchot/review.md`, branche `s10-nadia-verdict` ;
`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Verdict courant - chantier du
bandit manchot", du 2026-08-13). Le Done technique est acquis et n'est pas en cause. **Deux de ses
conditions bloquantes ne sont pas tenues** :

1. **M4** - le message, l'indice et la revelation doivent rester entierement au-dessus du pli, sur
   un ecran de manche a 360 x 640. Non tenue sur **six jeux sur sept**.
2. **M13 clause 1** - un appui sur Tabulation puis sur Entree apres une reponse ne doit jamais
   detruire la partie. Non tenue sur **les sept jeux** ; cause isolee par `nadia-prof` :
   `recreo.js` est charge dans le `<head>` de `accueil.html` avant que `<body>` existe, leve une
   `TypeError` a l'execution et n'enregistre donc jamais l'ecouteur qui deplacerait le focus.

L'utilisatrice a joue l'increment elle-meme et decide de le livrer, en assumant le defaut par
ecrit plutot qu'en le passant sous silence. **Cette decision porte exclusivement sur M4.**

**Elle ne porte pas sur M13 clause 1, et je le dis pour que ce silence ne se lise pas comme un
oubli.** M13 clause 1 n'est pas un arbitrage produit : c'est une panne, de cause connue et unique
(l'ordre de deux balises dans un gabarit), qui ne suppose aucun arbitrage entre un cout pour
l'enfant et un cout de developpement - contrairement a M4, ou tout remede mesure coute quelque
chose ailleurs (voir plus bas). Sa correction reste routee a `theo-archi` et `marc-dev` par
`sofia-sm`, comme n'importe quel defaut de ce genre, et n'est pas levee ni close par cette
decision : le verdict `REFUSE` de `nadia-prof` reste entier sur ce point tant qu'elle n'a pas
reverifie au clavier que la partie survit a ces deux touches.

## Ce que la levee de M4 coute a l'enfant, jeu par jeu

Mesure a 360 x 640, sur le rendu reel de `2d34a15` (`bas du bloc de message` compare au pli a
`y = 640`) :

| Jeu | Etat | Ecart au pli |
|---|---|---|
| Calcul CP | echec avec indice | **tient**, marge de 18,81 px (son indice fait une seule ligne) |
| Comparer les nombres | echec avec indice | -11,19 px |
| Complements a 10 | echec avec indice | -11,19 px (revelation : -13,19 px) |
| Le bon son | echec avec indice | -11,19 px |
| Soustractions CP | echec avec indice | -11,19 px |
| Petits problemes | echec avec indice | -107,19 px (revelation : -109,19 px) |
| Le bon verbe | echec avec indice | -251,19 px, et jusqu'a **-321 px** sur son item le plus charge (enonce et indice les plus longs du paquet, mesure ulterieure et plus severe) ; revelation : -173,19 px ; **"Bravo !" : -19,19 px** |

**Sur "Le bon verbe", ni le message d'erreur, ni l'indice, ni la felicitation ne sont visibles sans
defiler.** Un enfant qui se trompe presse une reponse et ne voit rien changer a l'ecran : le seul
signal visible est le mot barre sur la proposition fautive, qui ne dit pas pourquoi. Un enfant qui
reussit ne recoit lui non plus aucun retour visible - le defaut de M4 touche le chemin juste aussi
bien que le chemin faux, et il y est pire, puisque sur le chemin faux la question reste au moins
affichee.

## Toutes les voies ont ete mesurees avant cette levee, et aucune ne se referme

Cinq pistes ont ete instruites ce sprint pour fermer M4 sans la lever. Elles sont citees ici pour
qu'aucune ne soit reinstruite comme si elle n'avait pas ete essayee.

1. **Remonter l'indice au-dessus du meuble** : deplacement d'un bloc dans le flux du document, sans
   effet sur la hauteur totale du flux. **Gain mesure : 0,00 px, sur les sept jeux.** Sur deux jeux,
   ce deplacement enterrerait en plus le cabinet lui-meme sous le pli - l'enfant ne pourrait plus
   meme presser une reponse. Ecartee.
2. **Le resserrement des marges** (`.consigne`/`.enonce` et le rembourrage du bloc de message) :
   gain de 24 a 28 px, mesure sur le rendu reel. **Ferme deux jeux sur sept** ("Calcul CP", "Le bon
   son"). Implemente dans le lot de cloture ; il reste insuffisant seul sur les cinq autres.
3. **La baisse du plancher de texte de `.indice`/`.explication` a 1 rem** (contre 1,25 rem
   aujourd'hui) : insuffisante meme cumulee au resserrement - trois jeux ne ferment dans aucune
   combinaison mesuree ("Le bon verbe", "Petits problemes", "Les complements a 10") - et elle
   entaille un plancher de lisibilite que `nadia-prof` a pose comme non negociable
   (`TailleDesTextesEtBoutonsTest`, `PLANCHER_DE_LISIBILITE_REM`), qui couvre aujourd'hui `.indice`
   et `.explication` au meme titre que `.consigne` et tous les boutons de l'application. Ecartee en
   l'etat.
4. **L'ampoule de `nadia-prof`** (l'enfant appelle l'indice au lieu de le recevoir de lui-meme) :
   retire 58 a 148 px du flux dans les etats d'echec, **ferme cinq jeux sur sept**, mais **laisse
   "Petits problemes" a -11 px et "Le bon verbe" a -95 px** sur leurs etats d'echec, et ne touche
   jamais les ecrans de revelation ni le "Bravo !" de "Le bon verbe" (qui reste a -19 a -67 px selon
   l'item). **Acceptee dans son principe par `nadia-prof`, sous six conditions M16 a M21 dont M17 est
   bloquante** (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, arbitrage prealable du
   2026-08-13, branche `s10-nadia-ampoule`). Ce n'est pas un remede a M4, elle le dit elle-meme, et
   c'est reporte au sprint 11 (voir "La dette, chiffree" plus bas).
5. **Relever la hauteur de viewport de reference** (640 px aujourd'hui, `DEC-0016`) : mesuree sur
   55 telephones reels (source Puppeteer/Chromium), **mediane 732 px**. **800 px, cumule aux deux
   leviers de forme (resserrement des marges et plancher a 1 rem), ferme les sept jeux du
   catalogue** - avec une marge la plus juste de seulement 28,8 px sur "Le bon verbe". Mais
   **concevoir pour 800 px abandonne plus de la moitie des telephones reels de la distribution
   mesuree** : une hauteur de reference est un minimum que le produit doit supporter, pas une
   moyenne ni un objectif a atteindre. Mon propre raisonnement m'avait fait envisager cette voie
   comme une simplification ; la mesure le retourne, et je l'ecris comme une correction de mon
   propre jugement, pas comme celle de qui que ce soit d'autre.

**Le constat qui domine tout ce qui precede.** Meme dans la meilleure combinaison mesuree (800 px de
hauteur de reference et les deux leviers de forme cumules), le meuble ne gagnerait que 28,8 px -
rien qui le rapproche des references illustrees de l'utilisatrice. Ce n'est ni un probleme de
permission (les images maison n'ont jamais ete interdites, voir plus bas), ni un probleme de dessin,
ni une question de hauteur de reference : **un ecran de 360 px de large qui porte a la fois un
enonce, jusqu'a quatre propositions, un message, un indice et une revelation n'a pas la place pour
le meuble qu'elle imagine.** C'est une contrainte d'espace, pas un choix qu'une nouvelle mesure
ferait ceder.

## Decision

**1. La condition M4 de `nadia-prof` est levee comme condition bloquante pour la livraison de
l'increment du sprint 10, sur decision expresse de l'utilisatrice, qui a joue l'increment et choisit
de le livrer en connaissance du defaut ci-dessus.** Je reprends ici le principe de gouvernance que
`nadia-prof` a elle-meme pose a plusieurs reprises ce sprint, sur un autre veto qu'elle a tenu et
cede dans les memes termes (la manette, `DEC-0022`, `DEC-0023`) : *"si l'utilisatrice tranche
[...] malgre [mes motifs], mon veto ne survit pas a sa decision - je ne detiens pas la porte
produit."* Cette decision applique ce meme principe a M4.

**2. Cette levee ne prononce, ne simule et ne remplace aucun Done pedagogique.** Le verdict
`REFUSE` de `nadia-prof` du 2026-08-13 reste ecrit, entier et non conteste sur le fond, dans
`squad/sprints/s10-la-forme-du-bandit-manchot/review.md` et dans les fiches de conformite des sept
jeux. Aucune ligne de `squad/programme/MATRICE-couverture.md` ne passe a `couvert-valide` du fait
de cette decision. Ce que cette decision autorise est de livrer un increment refuse, pas de
declarer qu'il ne l'est plus.

**3. M13 clause 1 n'est pas levee et n'est pas l'objet de cette decision** (voir Contexte). Elle
reste une condition bloquante non tenue, et le verdict `REFUSE` continue de porter sur elle au meme
titre que sur M4 tant qu'elle n'est pas corrigee et reverifiee au clavier par `nadia-prof`.

**4. Declencheur de reouverture de M4.** Le seul geste qui retranche reellement de la hauteur,
plutot que de la deplacer ou de gratter une police, est **le raccourcissement des textes** -
enonces et indices - des items les plus charges. C'est deja le sens de la voie 1 ci-dessus (0,00 px
de gain) : rien ne se gagne a deplacer un bloc, tout se gagne a raccourcir ce qu'il contient. Ce
geste est deja engage pour les indices de `cm1-conjugaison-present` (story existante, voir
plus bas). M4 revient devant `nadia-prof` pour remesure, sur le rendu reel a 360 x 640, des que
les trois stories de la section suivante sont livrees, en tout ou en partie ; elle n'est pas
close par une seule d'entre elles a elle seule sur les jeux qu'elle ne couvre pas.

## La dette, chiffree, et les stories qui la portent

- **Etat present** : 6 jeux sur 7 en defaut de M4 dans les etats d'echec ; 3 dans les etats de
  revelation ; 1 ("Le bon verbe") dans son etat de reussite. Deficit cumule le plus grave : -321 px
  sur l'item le plus charge de "Le bon verbe".
- **Trois stories ouvertes** dans `docs/backlog/backlog.md` :
  1. **Story neuve** - raccourcir les enonces et les indices des six jeux en defaut de M4
     (`cp-comparer-nombres`, `cp-complements-10`, `cp-le-bon-son`, `cp-soustractions`,
     `cp-problemes-additifs`, `cm1-conjugaison-present`), sur leurs items les plus charges,
     jusqu'a ce que le bas du bloc de message tienne au-dessus du pli sur les quatre etats. Non
     affectee a un sprint.
  2. **Story existante, priorite relevee** - "Les indices de 'Le bon verbe' nomment la manipulation,
     pas la reponse" (M2, deja au backlog, non affectee a un sprint). `nadia-prof` en fait
     explicitement une priorite dans sa reserve S du 2026-08-13 : *"la reserve A - reecrire les
     25 indices de `cm1-conjugaison-present` - cesse d'etre une amelioration et devient ce qui rend
     ce jeu enseignable. Priorite a `camille-po`."* Je la reprends telle quelle.
  3. **Story neuve, reportee au sprint 11** - implementer l'ampoule (l'aide appelee par l'enfant),
     sous les six conditions M16 a M21 de `nadia-prof` (M17 bloquante). Voir voie 4 ci-dessus.

Aucune de ces trois stories ne ferme M4 seule sur l'ensemble des sept jeux ; leur effet combine,
une fois livre et remesure par `nadia-prof`, est ce qui permettra de reevaluer la condition.

## Ce qui est acquis et ne doit pas etre rejuge

**Vingt-sept des trente-deux conditions de `nadia-prof` sont tenues**, et ne sont ni rouvertes ni
durcies par cette decision :

- **F16** (troisieme condition bloquante de ce chantier) est **fermee** : le mot barre de la marque
  "deja essayee" tient au moins 9,55:1 sur les quatre teintes, contre un plancher de 3:1.
- **Les quatre defauts du meuble refuses le 2026-08-13 sont corriges** : l'oeil est plus grand que
  la joue, la bouche est visible, la boite du visage contient ses ombres, la marque "deja essayee"
  se voit sur son fond.
- **Le levier**, dessine et immobile sur le flanc (S10-1), et **M10** (aucune animation sur un
  reessai, verifiee par echantillonnage avec temoin, pas seulement par une capture) sont tenus.
- Le travail de ce sprint n'est pas perdu : il est arrete par deux defauts nommes, dont l'un tient
  a une seule ligne de JavaScript mal placee.

## Un fait d'architecture a acter

**Il n'y a jamais eu d'interdit sur les images maison dans ce depot** (`ADR-0036`,
`theo-archi`, branche `s10-theo-images`). `mvn -B verify` passe deja vert avec une image maison
servie en `background-image` ou en `<img>`, et une image binaire (`favicon.ico`) est effectivement
servie depuis le sprint 6. J'ai affirme le contraire a plusieurs reprises pendant ce sprint, y
compris dans ce qui a conduit l'utilisatrice a choisir un meuble en CSS pur : le meuble geometrique
livre ce sprint est en partie le produit de cette erreur, que je corrige ici par ecrit plutot que
de la laisser silencieuse. `ines-design` a depuis livre une premiere image maison
(`cabinet-fronton.svg`, 1 883 octets) a un cout de rendu negligeable (+0,8 % du poids servi sur
l'ecran de jeu).

**La question des images reste ouverte pour le sprint 11**, et n'est pas tranchee par cette
decision. `ADR-0036` pose une dette explicite et non plafonnee : rien dans ce depot n'empeche
aujourd'hui une illustration de plusieurs megaoctets, `theo-archi` ayant motive son refus d'ecrire
un plafond avant qu'une cible de poids ne soit fixee par `sofia-sm`. Cette dette n'est pas fermee
ici.

## Justification pedagogique

Cette decision ne juge aucun contenu et ne renverse aucun jugement pedagogique : le verdict
`REFUSE` de `nadia-prof` reste vrai et entier. Le seul geste que je pose est un arbitrage produit -
livrer malgre un defaut nomme - que `nadia-prof` a elle-meme situe hors de sa porte a plusieurs
reprises ce sprint. Le jugement sur ce que raccourcir, comment le faire sans appauvrir le contenu,
et sur la forme de l'ampoule reste integralement le sien et celui d'`ines-design`.

## Impact

- `docs/backlog/backlog.md` : trois stories (une story neuve sur le raccourcissement des six jeux,
  une priorite relevee sur la story existante des indices de "Le bon verbe", une story neuve sur
  l'ampoule reportee au sprint 11), section ajoutee a la suite du sprint 10.
- `squad/programme/MATRICE-couverture.md` : inchangee. Aucune ligne ne bouge.
- `squad/decisions/produit/DEC-index.md` : ligne DEC-0025 ajoutee.
- `squad/conformite/FICHE-bandit-manchot-*.md` (sept fiches) : inchangees par cette decision ; le
  verdict `REFUSE` de `nadia-prof` et ses trente-deux conditions y restent tels qu'elle les a
  ecrits.
- `adapters/adapter-content/src/main/resources/catalogue.yaml` : inchange. Cette decision ne retire
  aucun jeu du catalogue ; elle autorise la livraison de l'increment tel quel, defaut assume.

## Ce que cette decision ne fait pas

- Elle ne prononce aucun Done pedagogique et ne modifie le verdict d'aucune fiche de conformite.
- Elle ne leve pas M13 clause 1, condition bloquante distincte, technique et non pedagogique par sa
  nature, routee a `theo-archi` et `marc-dev`.
- Elle ne choisit pas quels enonces ou indices raccourcir, ni comment les reformuler : jugement de
  `nadia-prof`.
- Elle ne tranche aucune question technique sur la mise en oeuvre du resserrement de marges, de
  l'ampoule ou de tout autre levier de forme : `theo-archi` et `ines-design`.
- Elle ne fait bouger aucune ligne de `squad/programme/MATRICE-couverture.md`.
- Elle ne tranche pas la question des images ouverte par `ADR-0036`, ni la dette de poids qui
  l'accompagne : reportee au sprint 11.
- Elle ne fixe pas une nouvelle hauteur de viewport de reference : `DEC-0016` (640 px) reste en
  vigueur, pour le motif explique dans la voie 5 ci-dessus.

## Amendement du 2026-08-14 (s10, cloture) - le resserrement de marges ferme trois jeux, pas deux, et pas ceux nommes ici

Pendant son second verdict, `nadia-prof` rejoue la mesure de la voie 2 ci-dessus (le resserrement
des marges), que personne n'avait rejouee depuis sa premiere mesure - `marc-dev` avait ecrit
explicitement qu'il ne l'avait pas rejouee, et il avait raison de l'ecrire
(`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section 4, "Second verdict du
2026-08-13"). Protocole identique a son refus du matin, applique cette fois au pire cas de chaque
jeu plutot qu'a un echantillon : 261 etats de message et 35 ecrans de revelation.

**Ce que la voie 2 ci-dessus affirme est faux dans les deux sens.** Elle disait : *"gain de 24 a
28 px [...] ferme deux jeux sur sept (Calcul CP, Le bon son)."*

- **"Calcul CP" n'est pas ferme par le resserrement : il l'etait deja.** Il apparaissait au-dessus
  du pli avec 18,81 px de marge des la premiere mesure de `nadia-prof`, avant meme que ce levier ne
  soit pris en compte - seul jeu du catalogue dans ce cas, parce que son indice tient sur une seule
  ligne.
- **"Le bon son" n'est pas ferme du tout.** Son ecran de revelation deborde de **23,19 px**, pour
  une cause de contenu et non de forme : l'explication et l'indice y disent deux fois la meme
  chose (voir la story versee au backlog par `DEC-0026`).
- **Le resserrement ferme en realite trois autres jeux** : "Comparer les nombres", "Soustractions
  CP", et "Les complements a 10" - ce dernier a **2,81 px** de marge seulement, sur les 36 etats
  mesures par `nadia-prof`, qui ne garantit rien sur les items qu'elle n'a pas tires.

**L'etat reel de M4 apres le lot de cloture : non tenue sur trois jeux sur sept** - "Le bon son"
(-23,19 px en revelation), "Petits problemes" (-63,19 et -83,19 px), "Le bon verbe" (-63,19 px, et
son "Bravo !" a -35,19 px) - et non six comme les sections "Ce que la levee de M4 coute a l'enfant"
et "La dette, chiffree" ci-dessus l'affirmaient avant cet amendement. C'est un progres reel de
trois jeux sur les sept, obtenu par un seul geste de forme, et je le corrige sans l'effacer.

**Ce que cet amendement ne change pas.** La levee de M4 prononcee ci-dessus reste en vigueur :
`nadia-prof` l'ecrit elle-meme, cette correction de sa propre mesure ne retire ni n'ajoute aucun
motif de refus - *"mon constat de refus n'est pas retire pour autant, la levee autorise a livrer
malgre lui, elle ne le transforme pas en acceptation."* Les trois stories ouvertes par cette
decision restent ouvertes dans les memes termes ; aucune n'est retiree ni ajoutee par cet
amendement.

### Impact de cet amendement

- Les sections "Ce que la levee de M4 coute a l'enfant, jeu par jeu" et "La dette, chiffree, et les
  stories qui la portent" de ce fichier restent telles qu'ecrites le 2026-08-13 ; elles ne sont pas
  reecrites, seule la lecture qu'en donne cet amendement est correcte a compter du 2026-08-14.
- `squad/decisions/produit/DEC-index.md` : note d'amendement ajoutee.
- Aucun Done pedagogique ni statut de catalogue n'est modifie par cet amendement.
