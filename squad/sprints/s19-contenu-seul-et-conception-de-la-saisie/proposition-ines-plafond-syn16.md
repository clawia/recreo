# Proposition Ines - la valeur du plafond de largeur de rangee (SYN-16 / S19-6)

- **Date** : 2026-08-24. **Auteure** : ines-design.
- **Branche** : `s19-ines`, worktree dedie, lignee verifiee par `outils/verifier-lignee.sh`.
- **Ce que je tranche** : rien. Je propose une valeur et je montre son cout mesure ; `nadia-prof`
  garde le veto pedagogique sur le plafond retenu, `camille-po` decide la tolerance de couverture
  qu'accepte le produit (141/144 largeurs contre 144/144).
- **Ce que je ne fais pas** : je n'ecris aucune ligne de `recreo.css` ni de garde-fou. La regle
  concrete, sa table epinglee et son `@media`, reviennent a `lea-dev`/`theo-archi` une fois la
  valeur arbitree.
- **Methode** : application servie depuis le fat jar de ce worktree (`round-two` a `d607df4`, code
  identique a celui que `nadia-prof` a mesure en `74ad820` - son lot ne touche aucun fichier de
  code), Chrome reel pilote par son protocole de deverminage, `deviceScaleFactor` a 1, mode non
  mobile. J'ai reemploye ses deux scripts commit (`squad/outils/nadia-prof/balayage-largeur-rangee.py`,
  `confirmer-largeur-dans-la-bande.py`) pour verifier ma propre lecture avant d'en tirer quoi que ce
  soit : a 640 x 551, plafond injecte 579, je retombe exactement sur ses chiffres au centieme
  (`pireBasDuVerdict` 504,19 px, `itemsADeuxRangees` vide, `rangeeAttendue` 539). Au-dela de cette
  verification, j'ai ecrit deux scripts d'exploration courts, non commits (ils ne sont pas une
  methode de campagne au sens de `squad/outils/`, seulement des lectures de boite ponctuelles),
  pour mesurer ce que ni son avis ni le mien ne couvraient encore : la marge laterale a plusieurs
  plafonds candidats, et la pile verticale reelle au pixel.

## 1. Comment le plafond agit reellement - ce qu'il faut lire avant de choisir un chiffre

Le mecanisme est un simple `max-width` injecte sur `.entete-contenu` et `.zone-jeu` dans la bande
576-719,98 px. A un viewport `V` et un plafond `P`, la largeur reelle de la zone vaut `min(V, P)`,
et la rangee vaut cette largeur moins 40 px (`ADR-0054`).

**Consequence qui n'est dite nulle part encore, et qui change la lecture du probleme** : pour
`V <= P` (le plafond ne mord pas), la rangee vaut `V - 40`, **independamment de la valeur de `P`**.
Le point bas de la bande (579 px) est donc a marge nulle **quel que soit le plafond choisi** -
c'est exactement ce que `nadia-prof` ecrit ("nulle par construction"), et aucune valeur de `P` n'y
change rien. Ce que `P` gouverne, c'est uniquement ce qui se passe **au-dessus de `P`** : la rangee
y devient constante (`P - 40`), donc **la reserve de largeur a un viewport donne ne depend que de
combien `P` depasse 579 px**, et cette reserve est plate (la meme partout au-dessus de `P`) plutot
que croissante avec le viewport.

Verifie sur le rendu, plafond strict a 579 px (reproduction exacte du releve de `nadia-prof`) :

| Viewport | Largeur de rangee | Reserve sur 539 px necessaires |
|---|---|---|
| 579 | 539 | 0 |
| 600 | 539 | 0 |
| 640 | 539 | 0 |
| 719 | 539 | 0 |

**Un plafond strict a 579 px ne donne aucune reserve nulle part dans la bande, pas seulement a son
pied.** C'est le point que je crois plus important que celui que le brief formulait : ce n'est pas
seulement "le pied de la bande cede en premier" - c'est que **toute la bande cede a la fois** des
qu'un ecart de police depasse zero, puisque la rangee y est plafonnee a exactement 539 px partout,
jamais plus. Le defaut SYN-16 se rouvrirait alors sur les 141 largeurs corrigees en un seul coup,
pas seulement sur les trois qui restent hors de portee aujourd'hui.

## 2. Ce que differents plafonds donnent, mesure a huit viewports

Meme protocole que la table `SYN-14` de `nadia-prof` (largeur de zone, bord gauche de la premiere
proposition), reproduit a quatre plafonds :

| Viewport | 579 (strict) | 600 | 620 | 720 (= valeur deja acceptee au palier superieur) |
|---|---|---|---|---|
| 576 | rangee 536, bord 20,0 | rangee 536, bord 20,0 | rangee 536, bord 20,0 | rangee 536, bord 20,0 |
| 578 | rangee 538, bord 20,0 | rangee 538, bord 20,0 | rangee 538, bord 20,0 | rangee 538, bord 20,0 |
| 579 | rangee 539, bord 20,0 | rangee 539, bord 20,0 | rangee 539, bord 20,0 | rangee 539, bord 20,0 |
| 580 | rangee 539, bord 20,5 | rangee 540, bord 20,0 | rangee 540, bord 20,0 | rangee 540, bord 20,0 |
| 600 | rangee 539, bord 30,5 | rangee 560, bord 20,0 | rangee 560, bord 20,0 | rangee 560, bord 20,0 |
| 640 | rangee 539, bord 50,5 | rangee 560, bord 40,0 | rangee 580, bord 30,0 | rangee 600, bord 20,0 |
| 700 | rangee 539, bord 80,5 | rangee 560, bord 70,0 | rangee 580, bord 60,0 | rangee 660, bord 20,0 |
| 719 | rangee 539, bord 90,0 | rangee 560, bord 79,5 | rangee 580, bord 69,5 | rangee 679, bord 20,0 |

(les 576-578 sont hors de portee de toute colonne : `min(V, P) - 40 < 539` pour tout `P >= 579` des
que `V < 579`, verifie identique aux quatre plafonds - c'est une propriete de `V` seul, jamais de
`P`.)

**Lecture** : les colonnes 579/600/620 sont identiques jusqu'au viewport ou chacune plafonne (579,
600, 620 respectivement) - en dessous, `min(V,P)=V`, le choix de `P` ne change rien. Au-dela, la
rangee se foge a `P - 40` et le bord gauche de la premiere proposition (donc la marge laterale que
`SYN-14` protege) grandit avec le viewport. La colonne 720 reprend **exactement** la valeur deja en
vigueur au palier 720-767,98 px : elle ne plafonne jamais dans cette bande (719 < 720), la zone
reste fluide sur toute la largeur, et le bord reste au plancher de 20,0 px partout - le meme
plancher que celui deja mesure et accepte par `nadia-prof` le 2026-08-23 a la borne haute du palier
actuel.

**Aucune des quatre colonnes ne descend sous le plancher `SYN-14`** (12 px de rembourrage + 8 px de
bordure = 20 px, deja accepte "a la borne"). Le choix de `P` ne met donc jamais `SYN-14` en risque
de non-conformite ; il decide seulement combien de marge laterale reste **au-dessus** de ce
plancher aux viewports hauts de la bande, contre combien de reserve de largeur la rangee recoit.

## 3. Ma recommandation : plafond a **620 px**

**Reserve** : 620 - 40 - 539 = **41 px**, soit **7,6 % de largeur de texte en plus** que le
necessaire mesure par `nadia-prof` (539 px), applicable a tout viewport a partir de 620 px. C'est
plus de dix fois la marge que son propre essai de 0,6 % (4 px) a suffi a consommer entierement sur
un plafond strict - je choisis un multiple large de ce risque nomme parce que la police n'est que
l'un des ecarts possibles : un reglage de taille de texte du navigateur ou une autre pile de
polices systeme (l'application sert la police du systeme, `ADR-0014`) peuvent aisement depasser
0,6 %.

**Ce que ce choix coute, chiffre** :
- **A `SYN-14`** : rien de mesurable. Le bord gauche de la premiere proposition reste au plancher
  deja accepte (20,0 px) jusqu'a 620 px de viewport, puis grandit jusqu'a 69,5 px a 719 px - toujours
  au-dessus du plancher, jamais au-dessus de ce qu'un plafond de 579 px donnerait deja (90,0 px) :
  c'est **moins genereux qu'un plafond strict**, jamais moins conforme.
- **Aux autres jeux du catalogue** : c'est le point que je crois le plus important a nommer, et que
  ni l'avis de `theo-archi` ni la fiche de `nadia-prof` ne chiffrent, parce qu'il ne concerne pas
  les synonymes. `.entete-contenu` et `.zone-jeu` sont les conteneurs generiques de **tous** les
  jeux, pas seulement de celui-ci (c'est le meme principe que celui qui m'interdit de dessiner un
  signal pour un contenu precis). A 719 px, un plafond de 620 px elargit la carte de **tout autre
  jeu** de sa largeur native actuelle (540 px) a 620 px - un gain de 80 px, 15 %. Un plafond de
  720 px l'elargirait a 719 px - un gain de 179 px, 33 %, soit plus du double. Choisir 620 plutot
  que 720 n'est donc pas seulement une question de reserve : c'est aussi la valeur qui change le
  moins l'aspect des jeux qui n'ont jamais eu ce defaut.

**Couverture** : identique quel que soit le plafond retenu au-dessus de 579 px - **141 des 144
largeurs entieres de la bande**. Les trois largeurs 576, 577, 578 restent hors de portee de toute
valeur de plafond (section 1) ; seule la famille verticale pourrait les couvrir, et je conclus
en section 4 qu'elle ne le peut pas a un cout raisonnable.

**Ce qui me ferait changer d'avis** :
- si `nadia-prof` mesure un risque de derive de police reellement superieur a ~7-8 % (plus du
  double de son propre essai), je remonterais vers 650-680 px plutot que de sous-provisionner ;
- si `camille-po` juge qu'un elargissement visible de 80 px de la carte des **autres** jeux dans
  cette bande est un effet de bord non voulu pour un correctif cense ne toucher que les synonymes,
  la seule alternative qui ne l'introduit pas du tout est le plafond strict a 579 px - et dans ce
  cas la reserve nulle qu'il donne (section 1) doit etre actee comme un risque connu, pas comme un
  defaut ferme ;
- si `theo-archi` trouve un mecanisme qui derive la largeur necessaire du contenu reellement servi
  (une variable CSS posee par paquet, une mesure en `ch` plutot qu'en pixels fixes) plutot qu'un
  plafond fixe devine a l'avance, cette solution rend la question de reserve obsolete et je la
  prefererais a n'importe laquelle de mes quatre colonnes.

## 4. La famille verticale (43,19 px) - je ne recommande pas de la poursuivre

J'ai mesure, boite par boite, la pile verticale reelle de l'ecran de manche a 640 x 551 (repos et
apres une reponse, item "facile", plafond applique verifie sans effet sur les termes au-dessus du
meuble) :

| Element | Haut | Bas | Hauteur | Cout marge propre |
|---|---|---|---|---|
| `.entete` | 0 | 85,19 | 85,19 | 32 px de rembourrage (1 rem haut + 1 rem bas) - generique, tous les ecrans |
| `.zone-jeu` (rembourrage) | 85,19 | 117,19 | 32 | 32 px - generique, tous les ecrans |
| `.score` | 125,19 | 155,19 | 30 | 16 px de marge (0,5 rem haut + 0,5 rem bas) - generique, tous les ecrans |
| `.consigne` | 163,19 | 223,19 | 60 (deux lignes) | 4 px de marge - deja resserre au sprint 10 pour M4 |
| `.enonce` | 227,19 | 323,19 | 96 (deux lignes) | 4 px de marge - deja resserre au sprint 10 pour M4 |
| `.cabinet` (bordure) | 327,19 | 465,19 | 138 | 8 px de bordure haute - identite du meuble, trois arbitrages (S8-1, S10) |
| `.cabinet-bandeau` | 335,19 | 367,19 | 32 | dessin du visage calibre au pixel sur cette hauteur (S10) |
| `.propositions` (1 rangee) | 367,19 | 441,19 | 74 | - |
| `.cabinet-socle` | 441,19 | 457,19 | 16 | etoiles positionnees a 0,25 rem, marge quasi nulle |

**Chaque terme, passe en revue pour ce qu'il coute reellement a liberer, et pour qui l'a deja
arbitre** :
- `.propositions` gap (1 rem) : **exclu**, plancher nomme explicitement par `nadia-prof` a S3-5
  ("un ecart d'au moins 1 rem").
- `.consigne`/`.enonce` marges (4 px chacune) : **exclues**, deja resserrees a S10 au minimum
  mesure necessaire pour un **autre** jeu (M4) - les reduire encore reviendrait a leur donner une
  valeur pensee pour les synonymes, exactement ce que mon mandat m'interdit.
- `.cabinet` bordure, `.cabinet-bandeau`, `.cabinet-socle` : **exclus**. Ce sont les termes du
  meuble, geles depuis trois arbitrages successifs de `nadia-prof` (S8-1, puis trois rounds a S10).
  Le gain maximal theorique (8 + 32 + 16 = 56 px, et seulement en supprimant entierement le visage
  et le socle) couterait de rouvrir un chantier de forme que la squad a explicitement referme, pour
  un gain qui ne sert que 3 largeurs sur 144.
- `.icone-signal` (3 rem) : **exclu**. C'est le doublement de taille que mon propre mandat
  d'accessibilite a impose (visibilite du signal juste/faux). Le retrecir pour gagner des pixels
  irait directement contre la raison pour laquelle il existe.
- `.entete` (32 px de rembourrage) et `.zone-jeu` (32 px de rembourrage) : **seuls termes
  generiques jamais explicitement resserres**, mais leur cout est le plus large possible (tous les
  ecrans de l'application, accueil compris) pour le gain le plus etroit (3 largeurs).
- `.score` (16 px de marge) : **le seul terme que je qualifie de recuperable sans reserve** - jamais
  cite dans un arbitrage anterieur, generique mais modeste, et son texte (1,25 rem) resterait au
  plancher de lisibilite commun a `.indice`/`.explication`/`.consigne` si je ne touche qu'a la
  marge. Gain mesure : **16 px au maximum** (suppression complete des deux marges), **8 px** si je
  garde une respiration minimale entre le score et la consigne.

**Le total disponible sans rouvrir le meuble ni les planchers deja arbitres est donc au plus
16 + 32 + 32 = 80 px** en cumulant les trois termes generiques (score, zone-jeu, entete) - un
chiffre qui depasse 43,19 px sur le papier, mais qui suppose de vider entierement le rembourrage du
haut de **tous** les ecrans de l'application (accueil compris) et de supprimer toute respiration
autour du score. Je ne le recommande pas : le gain ne sert que les trois largeurs 576-578 px (les
memes que la famille horizontale echoue deja a couvrir), pour un cout qui change l'aspect de
**chaque** ecran de l'application, la ou la famille horizontale ne change que celui des jeux dans
une bande de largeur precise. Le rapport gain/etendue est a l'envers de celui de la section 3.

**Ma reponse a la question posee** : je ne vois pas de terme vertical recuperable a un cout
proportionne. Le seul candidat honnete (`.score`, 16 px) est trop etroit pour combler 43,19 px, et
franchir l'ecart demanderait de toucher des elements soit geles par un arbitrage anterieur (le
meuble), soit generiques a l'echelle de toute l'application pour un gain qui ne profite qu'a un
sliver de trois pixels de viewport. Je recommande de ne pas ouvrir ce chantier.

## 5. Ce que j'ai trouve de travers dans le brief qui m'a ete route

Le brief presentait le choix comme "579 px strict couvre le plus de largeurs [...] un plafond avec
reserve [...] coute de la largeur utile a chaque viewport de la bande". Mesure a l'appui (section
1), ce n'est pas tout a fait ce qui se passe : le nombre de largeurs couvertes (141/144) **ne
depend pas** de la valeur du plafond choisie au-dessus de 579 px - c'est une propriete du seul
point bas de la bande (579 px), invariante quel que soit `P`. Ce que la valeur du plafond change
reellement, c'est **ou** la reserve de largeur apparait dans la bande (jamais au point bas, toujours
au-dessus du plafond choisi) et **combien de marge laterale** cela retire aux viewports hauts - un
cout qui ne touche jamais la conformite `SYN-14` (elle reste tenue a toutes les valeurs testees),
seulement sa generosite au-dessus du plancher deja accepte.
