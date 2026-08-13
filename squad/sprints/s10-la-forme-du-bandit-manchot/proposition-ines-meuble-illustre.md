# Proposition ines-meuble-illustre - un accent peint sur le fronton, dans le budget fige

- **Auteur** : ines-design
- **Date** : 2026-08-13
- **Branche** : `s10-ines-meuble-illustre`, descend de `round-two` a `2d34a15`
- **S'appuie sur** : `ADR-0036` (theo-archi, branche `s10-theo-images`, tete `1bdfbe8`) - la contrainte
  "aucune image" n'a jamais existe, et un chemin litteral est le seul autorise.
- **Ne modifie pas** : `fragments/manche.html`, aucun test, aucun autre gabarit. Deux fichiers
  touches : `recreo.css` (12 lignes) et un fichier neuf, `static/images/cabinet-fronton.svg`.

## Ce que l'utilisatrice a juge, et ce qui a motive ce lot

Le meuble geometrique livre ce sprint (visage, levier, corps en deux tons, etoiles du socle, tout
en CSS) est tres loin des deux references qu'elle a fournies - un meuble peint, fronton en arc,
enseigne, visage illustre. La cause etait ma premisse fausse ("aucun fichier image"), pas une
limite du depot : `theo-archi` a mesure qu'une image maison passe la suite complete et le passait
deja depuis S6-1 (`favicon.ico`).

## Ce que je n'ai pas pu faire, et pourquoi je le dis avant de dire ce que j'ai fait

Les deux traits les plus caracteristiques des references - le fronton qui bombe en arc au-dessus du
corps, les flancs decores plus larges que les colonnes - coutent des pixels de hauteur et de
largeur. Or **les deux budgets sont deja epuises** : le cabinet mesure 138 px a trois colonnes et
222 px a quatre, et il ne reste que 5 px avant le pli sur "Le bon verbe" ; le budget horizontal est
epuise a 360 px. Une image ne rend pas ces pixels disponibles. Je n'ai donc pas dessine d'arc ni de
flancs elargis : ce serait dessiner un cout que je n'ai pas le mandat de payer, et le nommer ne
suffit pas a l'annuler. Si l'utilisatrice ou `sofia-sm` rouvre le budget, ce chantier reste a
refaire avec plus de place ; en l'etat, la ressemblance aux references reste partielle et je ne le
maquille pas.

## Ce que j'ai fait : un seul accent peint, dans la boite existante

Un fichier, `cabinet-fronton.svg` (1 883 octets), applique en `background-image` sur
`.cabinet-bandeau`, ancre a droite (`background-position: right center`, `background-repeat:
no-repeat`, `background-size: auto 100%`). Contenu : un degrade vertical qui suggere un relief peint
plutot qu'un aplat, un arc de lumiere, un rivet et une petite etoile (meme silhouette a dix pointes
que celle deja acceptee sur le socle, reprise ici pour ne pas ajouter un second langage de forme).
Aucun texte dans le fichier.

**Decoupage et son motif.** Une seule image, pas trois, et voici pourquoi :
- Le corps du cabinet est entierement rempli par `.propositions` (aucune marge, aucun rembourrage) :
  une image de corps n'y serait visible que dans les 1 rem d'ecart entre boutons - illisible, donc
  pas fait.
- Le socle (1 rem, deja porteur de deux etoiles acceptees) n'apporte rien a habiller de plus a cette
  taille - pas fait.
- Le fronton est la seule bande ou un accent se voit vraiment (2 rem, plate aujourd'hui), et c'est
  la seule que je touche.

**Confinement volontaire au tiers droit.** L'image est ancree a droite et sa moitie gauche est un
fondu vers l'opacite zero de la meme teinte que le fond (`--recreo-cabinet-fond`, #081c2c) : elle se
fond dans l'aplat existant sans coupure visible, et surtout **elle ne recouvre jamais le visage ni
la zone reservee a l'enseigne**, qui restent sur un fond uni inchange. Mesure sur le rendu reel
(361 px de fenetre a 360 px CSS, jeu "Le bon verbe", quatre colonnes) : le cabinet fait 334 px de
large ; le seul ecart de pixels entre le rendu d'avant et celui d'apres, a l'interieur du bandeau,
est confine aux colonnes 263 a 326 (l'accent lui-meme). Rien ne change entre les colonnes 11 et 262,
la ou vivent le visage et, demain, l'enseigne.

## Ce que j'abandonne du meuble actuel, et ce que je garde

**Je ne touche a rien de ce que `nadia-prof` a deja arbitre.** Le visage (v2), le levier (variante
E), le corps en deux tons et les etoiles du socle restent en CSS, inchanges au pixel pres - je l'ai
verifie par capture avant/apres, socle strictement identique (aucun pixel different), visage non
recouvert. Je ne lui demande donc pas de rejuger ces quatre elements : ce lot ajoute une cinquieme
chose, il ne remplace aucune des quatre premieres.

**L'enseigne reste non cablee.** `fragments/manche.html` n'a pas change : "Le Copain Malin" ne vit
nulle part dans le document aujourd'hui, exactement comme avant ce lot. Ce n'est pas un oubli : F11
et `ADR-0035` bloquent son emplacement accessible tant qu'une ecoute reelle de lecteur d'ecran n'a
pas eu lieu (`nadia-prof`), et la question de savoir si un masque au niveau du conteneur suffit ou
s'il faut une exception nommee est une decision d'architecture (`theo-archi`) autant que
pedagogique (`nadia-prof`) - ni l'une ni l'autre ne m'appartient. Je n'ai pas cable de markup, meme
a titre d'essai, pour ne pas laisser un texte de gabarit entrer dans un sous-arbre masque avant que
ce blocage soit leve ailleurs.

## Poids

| Fichier | Octets |
|---|---|
| `images/cabinet-fronton.svg` (neuf) | 1 883 |
| `css/recreo.css` (avant / apres) | 36 321 / 37 127 (+806, commentaire compris) |

Total servi sur un ecran de jeu (meme perimetre qu'`ADR-0036`, tout sauf `vendor/provenance.txt`) :
**321 281 -> 323 964 octets**, soit **+2 689 octets (+0,8 %)**.

**Plafond que je propose** : aucune image de meuble, seule ou cumulee avec les suivantes, ne depasse
**20 Ko**. Ce n'est pas un plafond relatif a la bibliotheque vendorisee (le fait 2 de la reserve de
poids d'`ADR-0036` l'ecarte a bon droit : un garde-fou qui rougit sur une image parce qu'une
bibliotheque a maigri est fautif). C'est un chiffre absolu, choisi pour le telephone d'un enfant sur
un reseau d'ecole, et delibere large par rapport a ce que je livre ici (1,8 Ko) pour laisser de la
place a une meilleure resolution ou a une deuxieme image sans qu'on doive revenir dessus a chaque
sprint. **Ce chiffre est une proposition, pas une decision** : `ADR-0036` note explicitement que la
cible de poids revient a `sofia-sm` et `nadia-prof` ; je le pose comme point de depart a confirmer.
Je pose le cliquet a **1 883 octets** dans ce commit (la dette qu'`ADR-0036` decrit), pas a 20 Ko :
le plafond est une intention, le cliquet est ce qui existe reellement aujourd'hui.

## Cout vertical et horizontal, mesure

Aucune propriete de boite (`height`, `width`, `margin`, `padding`, `border`) n'est touchee dans mon
diff - seules des proprietes `background-*` sont ajoutees. Ce n'est pas qu'une lecture de diff :
mesure sur le rendu reel, jar reconstruit avant/apres, meme partie de "Le bon verbe" jouee par une
session HTTP a bocal a temoins, viewport pose a 360 px CSS par `outils/mesurer-rendu.sh` (jamais une
taille de fenetre, `ADR-0025`) :

| Grandeur | Avant | Apres |
|---|---|---|
| Hauteur totale du cabinet (bordure a bordure) | 214 px | 214 px |
| Largeur totale du cabinet | 334 px | 334 px |
| Socle (pixel a pixel) | - | identique, 0 different |

**Cout : 0 px, sur les deux axes, mesure et non suppose.** Verifie egalement a 1280 px de largeur
CSS (protocole DevTools de `mesurer-rendu.sh`) : l'accent reste dans le tiers droit du fronton, ne
touche ni le visage ni les propositions, sur "Le bon verbe" (4 colonnes) et "Calcul CP" (3
colonnes).

## Methode

- Worktree dedie sur `s10-ines-meuble-illustre`, verifie descendre de `round-two`.
- JDK 21 constate par le mecanisme deja en place sur ce poste (jamais ecrit ici).
- `mvn -B verify` : **BUILD SUCCESS**, 390 tests (45+7+195+63+77+3), inchange par rapport a
  `2d34a15` - ce lot n'ajoute et ne casse aucun test, parce qu'il ne touche qu'a des `background-*`.
- Garde-fou de chemins de poste (`verifier-chemins.sh`) rejoue sur l'arbre complet, y compris le
  fichier neuf : aucune occurrence.
- Rendu mesure sur fat jar reconstruit et lance localement, jamais sur les fichiers seuls : parties
  reelles jouees contre l'application ("Calcul CP", "Le bon verbe"), session portee par un bocal a
  temoins, en parlant aux vraies routes (`/parties/<jeu>`, `/parties/<jeu>/coups`). Six ecrans
  couverts - accueil, une manche, bonne reponse, mauvaise reponse (avec indice), fin de partie,
  ecran d'erreur - captures a 360 px et 1280 px par `outils/mesurer-rendu.sh`.
- Comparaison avant/apres : deux jars construits (l'un sur le commit d'avant ce lot, l'autre avec ce
  lot), servis sur deux ports dedies, memes routes jouees, captures comparees pixel a pixel
  (bibliotheque Pillow) plutot qu'a l'oeil pour la mesure de cout.
- Une erreur trouvee et corrigee en cours de route, nommee pour qu'elle ne se reproduise pas :
  un commentaire XML dans le SVG contenait involontairement deux tirets consecutifs
  (`--recreo-cabinet-fond`), ce qui est invalide dans un commentaire XML et rendait le fichier
  entier illisible par le navigateur - le `background-image` echouait donc en silence, sans que
  `mvn verify` ne le voie (ce garde-fou ne rend pas la page, il ne compile que le CSS et le HTML
  serveur). Corrige en reformulant le commentaire sans double tiret, et reverifie par un rendu
  autonome du SVG avant de reprendre la mesure.

## Ce que je laisse a nadia-prof

1. **Le jugement pedagogique de cet accent** : un decor de plus sur un ecran de jeu reste sa seule
   autorite, meme quand il ne touche ni taille ni contraste mesurable derriere un texte (rien ne
   sied derriere lui - le fond reste plat sous le visage et l'emplacement de l'enseigne).
2. **La condition C5.** Ce fichier ne varie jamais (chemin litteral, pose une fois dans la feuille,
   jamais dans un gabarit) : je considere la clause tenue par construction, comme les elements CSS
   deja acceptes. La moitie "gabarit" de C5 qu'`ADR-0036` laisse ouverte n'est pas concernee ici,
   puisque je n'ai touche aucun gabarit.
3. **L'enseigne** : toujours bloquee par F11/`ADR-0035`, inchangee par ce lot. Rien a rejuger ici
   tant que cette question reste ouverte ailleurs.
4. **Le plafond de poids et le budget vertical/horizontal** proposes ci-dessus, qui restent des
   propositions tant que `sofia-sm` ne les a pas confirmes.
