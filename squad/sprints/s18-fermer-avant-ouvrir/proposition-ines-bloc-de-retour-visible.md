# Proposition - mecanisme de forme pour SYN-11 (bloc de retour visible sans defiler)

- **Auteur** : ines-design. **Date** : 2026-08-23.
- **Worktree dedie** : `recreo-s18-ines`, branche `s18-ines`, forkee depuis `round-two` a
  `8046256` (verifie par `git rev-parse HEAD` sur le worktree neuf, et par
  `bash outils/verifier-lignee.sh` : "le worktree sur la branche 's18-ines' descend de la lignee
  de round-two").
- **Sources lues avant d'ecrire** : `squad/sprints/s18-fermer-avant-ouvrir/planning.md`,
  `avis-faisabilite.md` (sections 2.2, 2.3, 2.4, et l'ouverture d'`ADR-0055`),
  `squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md` (verdict du 2026-08-22, `SYN-11` a
  `SYN-13`, section 6 sur l'incertitude de police), `ADR-0054` et `ADR-0055` en entier, et le
  fichier `recreo.css` servi (regles `.zone-jeu`, `.propositions`, `.proposition`, `.cabinet`) pour
  verifier sur quoi une declaration porterait reellement avant de la proposer.
- **Ce que ce document fait** : il propose une famille de mecanisme et son motif, et nomme ce
  qu'elle coute. Il ne tranche rien - c'est `nadia-prof` qui arbitre, sous son veto pedagogique.
- **Ce que ce document ne fait pas** : je ne mesure aucun rendu (aucun navigateur, aucun chiffre de
  pixel qui ne soit deja publie par `nadia-prof` ou `theo-archi`), je n'ecris aucune feuille de
  style ni gabarit de production, et je ne calcule aucun contraste - aucune couleur n'est touchee
  par cette proposition.

## 0. Le cadre dans lequel je choisis, en une phrase

Le defaut n'est pas une propriete du contenu (les seize phrases sont justes et creditees) : c'est
qu'a la reference 744 x 551, cinq items sur seize demandent une largeur de colonnes legerement
superieure aux 500 px que la rangee offre, ce qui la fait replier sur deux lignes et pousse le bloc
de retour juste/faux 90 px plus bas - hors du pli. Deux familles de leviers existent pour agir sur
cette comparaison (`ADR-0054` pour ce qui est offert, `ADR-0055` pour ce qui est demande) ; une
troisieme (`ADR-0054`, section 2.2 de l'avis) est fermee - retirer `.container` de la zone de jeu
n'est pas une option de forme, c'est un echec d'assertion. Je choisis entre les deux familles
restantes.

## 1. Variante recommandee - elargir ce que la rangee offre, pas ce qu'elle demande

**En une phrase** : donner a la zone de jeu, sur le seul palier de reference (576-767 px de
viewport, qui contient 744 px), la meme largeur de rangee que celle qu'elle offre deja a partir de
768 px - 680 px au lieu de 500 px - par une declaration maison scopee a ce palier, sans toucher a
aucun terme de colonne (rembourrage, police, ecart entre boutons).

### Pourquoi cette valeur precisement, et pas une valeur nouvelle

Ce n'est pas un chiffre que j'invente pour l'occasion. `nadia-prof` a deja mesure, au clavier et
dans un navigateur reel, que **les seize items du paquet tiennent sur une seule rangee des que la
rangee dispose de 680 px** (`avis-faisabilite.md`, section 2.2 : "l'escalier tiers rend 680 px de
rangee des 768 px, et nadia-prof a mesure que les seize items y tiennent sur une rangee"). Reprendre
cette valeur, c'est reprendre un palier deja eprouve par sa propre mesure - pas un calcul de ma main
sur une largeur de texte que je ne peux pas verifier moi-meme.

### Pourquoi c'est le levier que le critere de robustesse de `theo-archi` designe

Le deficit des cinq items va de 14 a 38 px ; l'incertitude de police que `nadia-prof` nomme elle-meme
vaut 15 a 35 px sur cette rangee. Tout correctif qui ne recupere que quelques dizaines de pixels
grappille sous ce bruit. **180 px offerts pour un pire deficit de 38 px** n'est pas un ajustement fin,
c'est un changement de categorie : les cinq items ne passent pas d'un repli "de justesse" a un repli
"un peu moins juste", ils cessent de replier du tout, avec une marge d'un facteur 4 a 5 au-dessus de
l'incertitude nommee. C'est exactement le "levier discret, qui change le nombre de rangees" que
l'avis de faisabilite recommande plutot que le grappillage continu.

### Ce que cette variante ne touche pas, et pourquoi ca compte

Aucun terme de colonne (rembourrage de `.proposition`, sa base flexible, sa police) n'est modifie :
la table d'`ADR-0055` reste intacte, rien n'y rougit. Seule la table d'`ADR-0054` gagne une ligne
(le plafond de `.zone-jeu` sur ce palier), ce qui est le canal de remesure voulu par cet `ADR`. Les
deux planchers pedagogiques deja arbitres par `nadia-prof` - l'ecart minimal entre deux boutons, la
police minimale d'une proposition - ne sont ni touches ni approches. La bordure du cabinet, objet
ferme sur ses quatre cotes (S8-1, condition C1, deja arbitree comme non negociable), n'est pas
concernee : cette variante agit en amont du cabinet, sur ce que la chaine lui offre, jamais sur lui.

### Ce que l'enfant voit, et ce qu'il ne voit pas

**Ce qu'il voit en plus, sur les cinq items concernes** : rien qui manquait avant ne lui manque - au
contraire, la rangee de propositions dispose de plus d'air, et les boutons (qui se partagent
l'espace disponible, `flex: 1 1 auto`) s'elargissent legerement pour occuper la largeur offerte en
plus. Trois colonnes plus larges plutot que trois colonnes suivies d'une quatrieme en dessous : rien
de retire, rien de retreci, une cible de doigt legerement plus genereuse.

**Ce qu'il voit disparaitre, et c'est le but** : la seconde rangee de propositions. Comme elle
disparait, tout ce qui la suivait remonte de 90 px - le cabinet se referme plus haut, et le bloc de
retour (bandeau `.signal`, texte du verdict) se retrouve dans la meme position relative que sur les
onze autres items, c'est-a-dire au-dessus du pli. Rien de nouveau n'est ajoute a l'ecran : ni texte,
ni icone, ni couleur. Le defaut ne se corrige pas en ajoutant un signal, il se corrige en retirant la
cause qui poussait le signal existant hors de l'ecran.

**Ce qu'il doit faire en plus** : rien. Aucun geste nouveau, aucune information nouvelle a lire,
aucun changement de comportement au clic ou au clavier.

### Sur les onze items qui n'ont pas le defaut

Ils tenaient deja sur une rangee a 500 px ; leur affichage sur trois colonnes n'est contraint par
aucun plafond dans cette variante, seulement autorise a disposer de plus de place. Le risque de
regression que je peux nommer sans le mesurer : les boutons de ces onze items, eux aussi soumis au
meme `flex: 1 1 auto`, s'elargiraient dans la meme proportion que ceux des cinq items concernes -
c'est un effet secondaire attendu et non un defaut, mais c'est un changement d'aspect (des boutons un
peu plus larges qu'aujourd'hui) sur des items que `nadia-prof` a deja credites (`SYN-1` a `SYN-4`,
`SYN-6` a `SYN-9`, `SYN-5` tenue). Ce changement d'aspect ne touche ni le texte, ni la couleur, ni
l'ecart entre boutons, ni la police - seule la largeur de la boite du bouton bouge - mais je ne peux
pas affirmer qu'il est invisible sans un rendu reel, et c'est a `nadia-prof` de le confirmer en
mesurant les onze en plus des cinq.

### Ce que je ne suis pas sure de tenir, et que je nomme plutot que de le lisser

1. **La zone de jeu (`.zone-jeu`) porte a la fois la liste des jeux de l'accueil et chaque ecran de
   partie** (verifie sur `accueil.html` : c'est le seul gabarit qui declare cette classe, et
   `main#jeu` la garde a travers tous les fragments echanges). Elargir son plafond sur ce palier
   elargit donc **tous** les ecrans a 744 px, pas seulement ceux qui portent des propositions. Je ne
   vois aucun risque de defaut sur la liste des jeux (une colonne empilee, pas une rangee qui replie)
   mais je ne l'ai pas mesuree.
2. **Un ecart de largeur apparaitrait entre l'en-tete et la zone de jeu sur ce palier precis.**
   L'en-tete (`nav.container.entete-contenu`) reste gouverne par `.container` seul et garderait son
   plafond actuel (540 px) ; la zone de jeu, elle, passerait a un plafond plus large (720 px, pour
   rendre 680 px de rangee) sur le meme palier de viewport. **C'est le cout visuel reel de cette
   variante** : entre 576 et 767 px de viewport, la zone de jeu deviendrait visiblement plus large
   que la barre d'en-tete au-dessus d'elle, la ou les deux sont alignees aujourd'hui. Je ne peux pas
   mesurer si cet ecart se voit ou se lit mal sans un rendu reel - je le nomme pour que `nadia-prof`
   le regarde specifiquement, elle qui mesure toujours a `scrollY = 0` et pourrait donc ne pas le
   remarquer si elle ne regarde que la position verticale des blocs.
3. **La valeur exacte du plafond a poser** (720 px, pour rendre 680 px de rangee une fois les termes
   lateraux retires) suppose que l'arithmetique d'`ADR-0054` reste valable telle quelle sur ce
   nouveau palier - c'est un calcul, pas une mesure de ma part, et c'est a `theo-archi` et a
   `nadia-prof` de le confirmer sur le rendu reel.

## 2. Alternative envisagee et ecartee - reduire ce que les colonnes demandent

**En une phrase** : reduire le rembourrage lateral des boutons de proposition (aujourd'hui 1,25 rem
par cote) recupererait, si on le portait a une valeur suffisamment basse, plus de largeur que le
pire deficit de 38 px - `theo-archi` chiffre a 96 px le gain d'un rembourrage ramene a 0,25 rem sur
trois colonnes.

### Pourquoi je ne la recommande pas

- **Le perimetre touche est l'application entiere, pas ce paquet.** `.proposition` est une regle de
  la mecanique partagee : tout bouton de reponse, de tous les jeux du catalogue, en herite. Une
  variante qui corrige `SYN-11` en touchant cette regle change l'aspect de chaque ecran de reponse
  deja juge par `nadia-prof` - additions CP, comparer des nombres, complements a 10, "le bon verbe",
  et tous les autres. Cela n'est pas seulement une question de perimetre technique : cela remettrait
  en question chaque Done pedagogique deja prononce sur la taille et l'espacement des boutons, pour
  corriger un defaut mesure sur un seul paquet de seize items.
- **Ca retrecit une cible de doigt** au lieu de l'agrandir. Mon mandat sur les tailles va dans le
  sens inverse : le signal et le texte que l'enfant doit lire ou presser restent au moins aussi
  grands que les elements secondaires, jamais plus petits qu'aujourd'hui sans un motif qui le
  justifie. Reduire un rembourrage de 1,25 rem a 0,25 rem est un choix qui va a l'encontre de ce
  principe pour tous les enfants du produit, y compris les onze items de ce paquet qui n'ont pas le
  defaut et les vingt (ou plus) items des autres jeux qui n'ont jamais ete mesures a 744 x 551.
- **Une valeur intermediaire retomberait sous le seuil de bruit.** Le motif de robustesse de
  `theo-archi` s'applique dans les deux sens : une reduction moderee (par exemple a 0,75 rem, qui
  recupere environ 24 px sur trois colonnes) resterait dans la meme fourchette que l'incertitude de
  police (15-35 px) - un correctif fragile, du type que l'avis de faisabilite met en garde contre.
  Seule une reduction drastique (proche de 0,25 rem) sort de cette fourchette, et c'est precisement
  la valeur qui coute le plus cher en cible de doigt.
- **Elle rougit `ADR-0055` par construction** (c'est voulu par cet `ADR`, pas un defaut), mais elle
  le fait sur une table qui decrit la largeur demandee par **toutes** les rangees du produit, pas
  seulement celle de ce paquet - la remesure qu'elle declenche chez `nadia-prof` porterait donc,
  en toute rigueur, sur plus que les seize items de `cm1-synonymes`.

Je ne l'exclus pas parce qu'elle serait inefficace au sens strict - le chiffre de `theo-archi` montre
qu'elle peut recuperer plus de largeur que la variante recommandee n'en recupere en proportion du
deficit. Je l'ecarte parce que son cout n'est pas scope au defaut qu'elle corrige : il se paie sur
tout le catalogue, pour un gain qui ne beneficie qu'a un paquet.

## 3. Ce que cette proposition rend a `SYN-12` et `SYN-13`, sans le garantir

**`SYN-13`** (revelation par elimination visuelle, observation datee) : `theo-archi` ecrit qu'elle
"disparaitra d'elle-meme si `SYN-11` est fermee par la largeur" - c'est exactement le mecanisme de
la variante recommandee, qui ferme `SYN-11` en donnant plus de largeur, pas en deplacant un bloc.
Si `nadia-prof` confirme la fermeture de `SYN-11` par cette voie, `SYN-13` devrait se fermer avec
elle, sans mecanisme supplementaire.

**`SYN-12`** (l'indice sous le pli, non bloquante) : si la seconde rangee disparait, l'indice - qui
vit dans le meme flux vertical, avant le bloc de retour - remonte lui aussi de 90 px sur les cinq
items concernes. Je ne l'affirme pas comme certain : je n'ai aucun rendu sous les yeux, et
`nadia-prof` a deja ecrit que ce qui compte est mesure, jamais deduit d'une hauteur de document.
C'est une consequence attendue de la variante, pas une garantie que j'ecris a sa place.

## 4. Ce que je ne tranche pas

- **La reponse a la question de mise en oeuvre precise** (media query exacte, forme de la
  declaration) : c'est le lot du developpeur affecte par `sofia-sm`, une fois le mecanisme arbitre.
- **Si l'ecart visuel entre l'en-tete et la zone de jeu (section 1, point 2) est acceptable** :
  c'est une mesure sur rendu reel, donc `nadia-prof`, et potentiellement une question de coherence
  d'ensemble pour `camille-po` si elle se revele genante.
- **Le sort de la variante alternative** si `nadia-prof` refuse la variante recommandee pour un
  motif que je n'ai pas anticipe : je n'ai pas prepare de troisieme option, sur consigne explicite
  (une variante recommandee, au plus une alternative).

## Ce que je remonte

- **A `nadia-prof`** : la variante recommandee (section 1) est la mesure que je demande en premier ;
  si elle la retient, je demande explicitement qu'elle regarde aussi l'ecart de largeur entre
  l'en-tete et la zone de jeu (section 1, point 2), qu'aucune de ses mesures precedentes n'avait a
  observer puisque les deux etaient toujours alignees jusqu'ici.
- **A `theo-archi`** : la valeur exacte du plafond a poser (720 px suppose, section 1, point 3)
  merite sa confirmation arithmetique avant l'implementation, sur le meme modele que celui qui a
  produit 500 et 680 px dans `ADR-0054`.
- **A `sofia-sm`** : le mecanisme est propose, pret pour l'arbitrage de `nadia-prof`, puis pour
  l'implementation de `lea-dev` selon le sequencement du planning.

Je ne fusionne pas, je ne pousse pas.
