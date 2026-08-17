# Proposition ines-design - la forme d'une barre de points d'entree par niveau, permanente dans l'en-tete

- **Auteur** : ines-design
- **Date** : 2026-08-17
- **Branche** : `s14-ines`, worktree dedie, cree depuis la tete de `round-two` a `5761e48`
  (`git merge-base --is-ancestor round-two HEAD` confirme, aucun autre commit intercale).
- **Ne modifie aucun fichier de production.** Ce document propose une forme pour un point
  d'entree "par niveau" (CP, CM1) dans l'en-tete permanent, et decrit comment il grandirait a
  s15 par matiere. Il ne tranche ni le contrat du controleur (l'existence d'un parametre de
  niveau, la route qu'un clic appelle, la persistance du filtre en session), ni le texte des
  boutons au-dela des sigles deja donnes par le besoin, ni le vocabulaire `data-role` que
  `theo-archi` fait vivre dans `LActionNeDeclarePasMoinsQueLaSortieTest` (je propose une valeur,
  je ne modifie pas ce test).
- **Ce que je reprends de ma proposition de s13** (`proposition-ines-catalogue-en-partie.md`,
  jamais codee) : la methode - mockup statique servi par les feuilles reelles du jar reconstruit,
  mesure par `outils/mesurer-rendu.sh` avec sa commande citee a cote de chaque chiffre, plusieurs
  variantes chiffrees plutot qu'une seule forme decidee seule - et le principe de ne jamais
  inventer un composant de boite neuf quand un composant deja juge par `nadia-prof` peut etre
  reutilise a l'identique. Point de compatibilite direct : S13-6 (non livre) posait
  `class="ecran-de-jeu"` sur son propre fragment racine ; la barre de niveaux que je propose ici
  ne masque rien sur cette classe (section 1), donc le jour ou S13-6 sera code, la barre s'y
  affichera sans aucun travail supplementaire - verifie par construction, pas suppose.

## 0. Le fait qui rend ce sprint difficile, mesure et non suppose

L'en-tete d'aujourd'hui (`accueil.html`, `recreo.css` `.entete`/`.entete-contenu`) tient sur un
seul rang : icone (44 px), titre "Recreo", bouton de sortie "Les jeux" (`.lien-accueil`, masque
sur un ecran de jeu par `ADR-0022`). Mesure sur l'application reelle (jar reconstruit par
`bash outils/mvn-jdk21.sh -B clean package -DskipTests`, servi par
`java -jar recreo.jar --server.port=<port>`), `outils/mesurer-rendu.sh 360 <url-accueil> <png> 640` :
**la bande d'en-tete mesure exactement 85 px** (transition de couleur `#1d4b6b` -> `#f4f7fb`
relevee colonne par colonne sur l'image). Ce chiffre est aussi celui qu'`ADR-0022` derive par le
calcul de boite (`font-size` 1,25rem + `padding` 0,6rem/1,1rem + `border` 0,125rem + `line-height`
1,5 de `.btn` = 53,2 px de bouton, plus les 2 x 1rem de `padding` de `.entete` = 85,2 px) : les
deux methodes se recoupent.

**Le budget est deja serre avant tout changement.** `ADR-0028` a mesure qu'a 360 x 640 px, un item
du paquet homophones CM1 avec son indice affiche laisse ses propositions a `y = 447` a `y = 520`
- **120 px de marge au-dessus du pli** - pendant qu'un autre item du meme paquet a ses
propositions entierement sous le pli (`y = 717` a `y = 790`). Toute hauteur que j'ajoute a
l'en-tete permanent descend ce budget d'autant, sur **tous** les ecrans de jeu a la fois, puisque
l'en-tete est un frere permanent de `#jeu` (`DEC-0012`).

## 1. Ce qui est fixe dans les deux variantes

- **La boite du bouton de niveau reprend a l'identique celle de `.lien-accueil`/`.retour-accueil`**,
  deja jugee par `nadia-prof` comme inferieure ou egale a toute action de l'application
  (`ADR-0017`, `ADR-0022`) : `font-size: 1.25rem`, `min-height: 2.75rem` (44 px, le plancher de
  cible tactile de tout le depot), `padding: 0.5rem 0.9rem` (legerement resserre par rapport a
  `0.6rem 1.1rem`, toujours **inferieur**, jamais superieur, aux deux axes de l'action la plus
  proche), `border: 0.125rem solid`, `border-radius: 0.5rem`. Zero composante de boite neuve
  inventee.
- **`data-role="sortie"`** (`ADR-0017`). Un appui sur un niveau pendant une partie passe par
  l'ecran de confirmation existant (`fragments/confirmation.html`, `ADR-0038`/`DEC-0026`) - du
  point de vue de l'enfant, c'est une sortie de l'activite en cours, exactement comme "Les jeux".
  **Question que je route a `theo-archi` plutot que de trancher** : le vocabulaire de
  `LActionNeDeclarePasMoinsQueLaSortieTest` ne connait que `action`/`sortie` et refuse toute
  autre valeur (`ADR-0017`, derivation totale) ; je propose de reutiliser `sortie` tel quel plutot
  que d'ouvrir une troisieme valeur, parce que la consequence structurelle (passer par la
  confirmation, interrompre l'activite) est identique. Si une troisieme valeur est preferee, le
  garde-fou doit etre etendu dans le meme commit - ce n'est pas un changement que je fais depuis
  un gabarit.
- **Conséquence verifiee de ce choix de boite** : sur tout ecran de jeu, la puce de niveau reste
  confrontee par le garde-fou a **tous** les boutons d'action de l'ecran (`.proposition`,
  `.demarrer`, `.continuer-partie`), puisque rien ne la masque (`ADR-0022` point 5, "un habillage
  qu'aucun masquage ne nomme reste confronte a tous les ecrans"). `font-size` 1,25rem <= 1,25rem
  ou 2rem selon l'ecran, `min-height` 2,75rem = 2,75rem, `padding` 0,5rem/0,9rem < 0,75rem/1,25rem
  sur les trois familles d'action existantes : les trois composantes comparees passent sur
  **toutes** les paires possibles, verifie composante par composante contre `recreo.css`
  aujourd'hui, pas suppose.
- **Aucun masquage** : contrairement a `.lien-accueil`, la barre de niveaux ne porte pas la regle
  `body:has(.ecran-de-jeu) { display: none }`. C'est le point qui repond au besoin ("toujours
  visible, y compris sur un ecran de jeu") et qui ne coute aucune ligne de CSS neuve : ne pas
  ecrire une regle de masquage suffit, l'absence de regle est deja le comportement voulu.
- **Etat actif porte par deux canaux, jamais la couleur seule.** Niveau actif : pastille pleine
  (fond blanc, texte et pictogramme dans la teinte de l'en-tete) - niveau inactif : contour (fond
  transparent, texte et pictogramme blancs). C'est une difference de **forme** (plein contre
  contour), pas un simple changement de teinte a luminance egale : un enfant qui ne distingue pas
  les couleurs voit un bloc plein a cote d'un bloc a bordure, la meme distinction que
  `.demarrer`/`.recommencer` (aplat) face a `.retour-accueil` (contour) ailleurs dans
  l'application. Aucune paire vert/rouge n'entre ici.
- **Pictogramme : aucun sur "CP"/"CM1" ce sprint, et je dis pourquoi plutot que d'en inventer un
  par reflexe.** "CP" et "CM1" sont deja l'abreviation que l'enfant lit sur son propre cahier, son
  bulletin et les etiquettes de sa classe depuis le premier jour d'ecole - ce ne sont pas des mots
  a decoder, ce sont des sigles deja familiers dans ce contexte precis. Inventer un pictogramme
  neuf pour les accompagner (une silhouette, un nombre d'etoiles) ajouterait un symbole a
  apprendre plutot que d'en retirer un, et je n'ai trouve aucune forme qui porte un sens plus
  clair que le sigle lui-meme sans risquer une lecture fausse (un ordre de grandeur, un rang). Je
  **reserve en revanche l'espace** (le `gap: 0.4rem` avant le texte, deja cable dans la boite)
  pour un pictogramme sur les boutons de matiere de s15 : "Français" et "Mathematiques" sont des
  mots pleins, plus longs, plus abstraits a dechiffrer pour un lecteur de CP qui debute, et un
  pictogramme y rend un service reel (un livre ouvert pour le francais, une regle/equerre pour les
  mathematiques sont deux pistes, non tranchees - le choix precis reste a `nadia-prof`, exactement
  comme le triangle de `contenu-repris/associer/cp-formes.yml` sous `DEC-0002`). Sur "CP"/"CM1", le
  petit disque decoratif que mes mockups affichent a cote du texte (`aria-hidden="true"`,
  `background-color: currentColor`) n'est **pas** un pictogramme au sens de `DEC-0002` - il ne
  represente aucune notion, il n'existe que pour occuper visuellement la meme position que le
  futur pictogramme de matiere afin que les deux familles de bouton s'alignent visuellement des
  ce sprint. Je le montre dans les captures ci-dessous pour cette raison, et je pose la question a
  `nadia-prof` en section 7 : le retirer purement et simplement sur CP/CM1 est une option a cout
  nul si elle juge qu'il n'ajoute rien.

## 2. Variante 1 - bandeau empile, retour a la ligne

### Forme

```
[icone] Recreo                         [Les jeux]     <- rang 1, inchange, 85 px
[CP*] [CM1]                                            <- rang 2, bandeau de niveaux
```

Second `<nav>`, frere de `.entete-contenu` a l'interieur de `.entete`, fond distinct
(`var(--recreo-cabinet-fond)`, la teinte deja mesuree et jugee par `nadia-prof` en s10 pour le
cabinet - aucune teinte neuve). `display: flex; flex-wrap: wrap` : les boutons passent a la ligne
par groupe entier, jamais en cassant un libelle.

### Mesure, methode dans le meme paragraphe

Mockup statique, memes feuilles (`bootstrap.min.css`, `recreo.css`) chargees depuis le jar
reconstruit, seule la regle `.bandeau-niveaux`/`.chip-niveau` proposee ci-dessus est ajoutee en
CSS inline du mockup - aucune ligne de production modifiee. Commande, repetee pour 2, 4 et 6
boutons (les 4 sont "CP", "CM1", "Français", "Mathematiques" - le jeu de labels que s15 pose
explicitement ; les 6 ajoutent deux matieres plausibles pour chiffrer la suite) :
`outils/mesurer-rendu.sh 360 <url-mockup> <png>`, puis lecture colonne par colonne de la
transition de couleur `#1d4b6b`/`#081c2c`/`#f4f7fb` sur l'image :

| Boutons | Lignes de puces | Hauteur totale d'en-tete | Cout ajoute vs aujourd'hui (85 px) |
|---|---|---|---|
| 2 (CP, CM1) | 1 | **151 px** | +66 px |
| 4 (+ Français, Mathematiques) | 2 | **209 px** | +124 px |
| 6 (+ Sciences, Anglais) | 3 | **267 px** | +182 px |

**La formule se verifie analytiquement, pas seulement par lecture d'image.** Une puce mesure
30 px de texte (ligne de 1,25rem a interligne 1,5) + 16 px de `padding` vertical (0,5rem x 2) +
4 px de bordure (0,125rem x 2) = 50 px, plus haute que son `min-height` declare de 44 px - c'est
donc le contenu, pas le plancher, qui pilote la hauteur reelle, exactement comme
`ADR-0022` l'a deja constate pour `.lien-accueil` (53,2 px de contenu contre 44 px de
`min-height`). Le bandeau ajoute 8 px de `padding` vertical propre (0,5rem) et 8 px d'ecart entre
deux lignes empilees (0,5rem de `gap`). Cout d'une bande a *n* lignes : `8 + 58n` px. Verifie sur
les trois lignes du tableau : `8 + 58 = 66`, `8 + 116 = 124`, `8 + 174 = 182` - exact aux trois
points mesures.

### Preuve sur un ecran de jeu reel, pas seulement un mockup d'en-tete

Methode `ADR-0028` section 2 reprise a l'identique : une partie de `bandit-manchot-additions-cp`
jouee par requetes HTTP reelles, `GET /` en reprise pour obtenir le document assemble de l'ecran
de manche, la barre de niveaux injectee dans son en-tete (2 puces, balisage de la section 1),
servie avec une `base` resolvant les feuilles sur l'application reelle. Capture
`outils/mesurer-rendu.sh 360 <url> <png> 640` :

- **A 2 niveaux** : en-tete 151 px, puis "Les jeux" (le bouton de sortie du fragment, inchange),
  "Score : 0", l'enonce "3 + 5" et le meuble complet (cabinet) tiennent tous au-dessus du pli.
  Cet item n'est pas menace.
- **A 4 niveaux** (2 lignes, +124 px) : le meme item tient encore au-dessus du pli - il partait
  avec une marge confortable - **mais le cout de 124 px depasse deja a lui seul la marge de
  120 px que `ADR-0028` a mesuree sur un autre item du meme genre de paquet** (celui a
  `y = 447-520`). Un item qui tenait de justesse au-dessus du pli avant ce sprint **basculerait
  sous le pli avec 4 boutons de niveau, sans qu'aucun contenu n'ait change** : c'est le mode de
  defaillance concret de cette variante, chiffre contre une mesure deja actee plutot
  qu'invente.

### Ce qui casse en premier

Le budget vertical du pli (`DEC-0016`, `ADR-0028`), sur les items qui tenaient deja de justesse.
La mise en page elle-meme ne casse jamais (le `flex-wrap` est une propriete testee, aucun
debordement horizontal releve a aucun des trois paliers), c'est le contenu au-dessus du pli qui
recule d'autant de pixels que le bandeau en gagne.

## 3. Variante 2 - bandeau a defilement horizontal, hauteur bornee

### Forme

```
[icone] Recreo                         [Les jeux]     <- rang 1, inchange, 85 px
[CP*] [CM1] [Françai>                                  <- rang 2, une seule ligne, defile
```

Meme bandeau, memes puces, mais `flex-wrap: nowrap; overflow-x: auto` au lieu de `flex-wrap: wrap`.
La ligne ne grandit jamais ; les boutons qui ne tiennent pas restent accessibles par un geste de
defilement horizontal (et par la tabulation clavier, qui fait defiler automatiquement l'element
focalise dans la zone visible - propriete du navigateur, pas du CSS).

### Mesure

Meme methode que la variante 1, memes trois paliers (2, 4, 6 boutons) :

| Boutons | Lignes | Hauteur totale d'en-tete | Cout ajoute vs aujourd'hui |
|---|---|---|---|
| 2 | 1 | **151 px** | +66 px |
| 4 | 1 (defile) | **151 px** | +66 px |
| 6 | 1 (defile) | **151 px** | +66 px |

Formule de la section 2 avec `n = 1` constant : `8 + 58 = 66`, verifie identique aux trois
paliers - la capture a 4 et a 6 boutons est pixel pour pixel la meme sur les 360 premiers pixels
de largeur, seul ce qui depasse a droite change (non visible sans defiler).

### Ce qui casse en premier

Pas le pli - c'est le point fort de cette variante, son cout est fixe et independant du nombre de
niveaux ou de matieres. Ce qui casse est la **decouvrabilite** : des 4 boutons, "Mathematiques"
apparait deja coupe a mi-mot sur le bord droit a 360 px (mesure sur la capture : le mot est
tronque, seul un debut de pastille est visible), ce qui est a la fois un indice qu'il existe
autre chose a decouvrir et un risque - un enfant qui ne comprend pas ce signal peut ignorer qu'un
niveau ou une matiere existent au-dela de ce qu'il voit sans geste. Aucun garde-fou de ce depot ne
peut mesurer si un enfant reel percoit cet indice (meme famille de propriete non gardable
qu'`ADR-0028` le dit de la position d'un bouton par rapport au pli) : c'est une propriete que
`nadia-prof` devra constater en jouant, au doigt, sur un ecran reel.

## 4. Tableau comparatif

| | Variante 1 - empilee | Variante 2 - defilement |
|---|---|---|
| Cout a 2 niveaux (s14) | +66 px | +66 px |
| Cout a 4 boutons (s15, niveaux + matieres) | +124 px | +66 px (constant) |
| Cout a 6 boutons | +182 px | +66 px (constant) |
| Ce qui casse en premier | le budget du pli sur les items deja proches (`ADR-0028`) | la decouvrabilite des boutons hors champ |
| Tous les boutons visibles sans geste | oui, toujours | non, des 4 boutons a 360 px |
| Nouveau risque introduit | aucun (proprietes deja gardees : `flex-wrap`, pas de debordement) | un geste de defilement horizontal, jamais requis ailleurs dans l'application aujourd'hui |

Les deux variantes partagent le meme cout a 2 niveaux (ce sprint) : le choix ne se joue donc pas
sur s14, il se joue sur ce que s15 doit garantir - un budget de pli stable (variante 2) ou une
liste toujours entierement visible (variante 1). C'est un arbitrage de produit et de pedagogie,
pas un arbitrage de forme, et je ne le tranche pas.

## 5. Ce qui est perdu si `nadia-prof` refuse la visibilite permanente

Le repli est a cout nul sur tout ce qui precede : ajouter la barre de niveaux a la regle de
masquage deja ecrite pour `.lien-accueil` (`body:has(.ecran-de-jeu) .bandeau-niveaux { display:
none }`, une ligne, la meme forme contractuelle qu'`ADR-0022`). La boite des puces, le calcul de
cout par ligne, l'etat actif a deux canaux et le choix de `data-role` restent inchanges - seule la
promesse "toujours visible, y compris sur un ecran de jeu" tombe, et le filtre de niveau
redeviendrait un geste que l'on ne peut faire que depuis l'accueil, exactement comme "Les jeux"
aujourd'hui ne peut etre lu qu'a l'accueil.

## 6. Comment la barre grandit a s15 sans casser - reponse explicite a la question posee

- **Variante 1** : chaque matiere de plus est une puce de plus dans le meme `flex-wrap`. Aucune
  regle ne change de forme quand le nombre de boutons grandit ; le cout se lit sur la formule de
  la section 2 (`8 + 58n` px, `n` le nombre de lignes que les libelles reels produisent a 360 px -
  chiffre a mesurer a nouveau des que les vrais libelles de matiere sont arretes, parce que la
  largeur d'un mot n'est pas derivable sans rendu, `ADR-0017` limite 1). Rien ne deborde jamais
  horizontalement ; le seul risque est vertical, et il est chiffrable a l'avance des que les
  libelles sont connus.
- **Variante 2** : la hauteur ne bouge jamais, quel que soit le nombre de matieres ajoutees. Ce
  qui grandit est la longueur du defilement, jamais la boite de l'en-tete.
- **Dans les deux cas**, le pictogramme reserve sur le bouton de matiere (section 1) est le
  levier qui reduit le risque de confusion quand le nombre de mots grandit - un enfant reconnait
  une forme avant de finir de lire un mot long comme "Mathematiques".
- **Ce qui ne change jamais entre s14 et s15** : la boite d'une puce (police, hauteur, `padding`),
  le mecanisme de non-masquage, le `data-role`. Un bouton de matiere de s15 est une puce de plus
  dans la meme population, pas un composant nouveau a faire rejuger par `ADR-0017`.

## 7. Questions pour `nadia-prof`

1. **Visibilite permanente, le principe.** Le besoin la demande deja tranchee par l'utilisatrice ;
   je pose neanmoins la question de la **forme** : la variante empilee (tout visible, cout
   variable sur le pli) ou la variante a defilement (cout fixe, decouvrabilite variable) - avec
   les chiffres ci-dessus, laquelle protege le mieux un enfant de CP qui joue sur un item deja
   proche du pli ?
2. **Le pictogramme.** Je propose de n'en poser aucun sur "CP"/"CM1" ce sprint (section 1,
   raisonnement complet) et de reserver l'espace pour un pictogramme de matiere a s15. Est-ce le
   bon niveau de decoupage, ou un signe distinctif serait-il utile des ce sprint pour distinguer
   visuellement "niveau" de "matiere" plus tot ?
3. **`data-role="sortie"` sur un bouton de niveau.** Interrompre une partie en cours pour changer
   de niveau produit le meme effet qu'appuyer sur "Les jeux" - est-ce la bonne lecture
   pedagogique, ou un enfant percoit-il "changer de niveau" comme un geste different de "quitter"
   qui devrait se distinguer autrement qu'en boite (texte, position) ?
4. **L'etat actif.** Le contraste plein/contour proposee (section 1) est-il suffisamment lisible
   pour un enfant de CP qui doit reperer "sur quel niveau je suis" en un coup d'oeil, sur un
   ecran deja charge (cabinet, score, enonce) ?
5. **La distinction avec "Les jeux".** Les niveaux vivent dans un bandeau separe, jamais adjacents
   au bouton de sortie sur la meme ligne (contrairement a une variante que j'ai ecartee en
   interne, ou "CM1" et "Les jeux" finissaient cote a cote sur la meme ligne apres un retour a la
   ligne non maitrise - non retenue precisement pour cette raison). Cette separation suffit-elle a
   eviter qu'un enfant confonde "changer de niveau" et "sortir du jeu" ?

## 8. Ce que je route, et a qui

- **`theo-archi`** : la valeur `data-role` a utiliser pour un bouton qui interrompt une partie
  sans etre le bouton de sortie principal de l'ecran (section 1). Je propose `sortie` par defaut ;
  une troisieme valeur exigerait d'etendre `LActionNeDeclarePasMoinsQueLaSortieTest` dans le meme
  commit que le gabarit qui la pose.
- **`marc-dev`/`lea-dev`** : le contrat que le controleur doit fournir - un parametre de niveau
  courant a lire pour marquer la puce active (`.chip-niveau--actif`), une route ou un attribut
  `hx-post` qui declenche, pendant une partie, le meme flux que le bouton de sortie
  (`fragments/confirmation.html`, `ADR-0038`). Je ne l'ecris pas, je le decris comme une donnee
  manquante au sens de mon mandat.
- **`nadia-prof`** : l'arbitrage complet de ce document (section 7), avant tout code.

## 9. Methode - commandes citees

- Reconstruction : `bash outils/mvn-jdk21.sh -B clean package -DskipTests` (JDK 21 designe par la
  toolchain de l'utilisateur, `java` du `PATH` de ce poste n'etant pas un JDK 21 -
  `outils/verifier-jdk.sh`).
- Lancement : `java -jar bootstrap/target/recreo.jar --server.port=<port>`, arrete par sa PID de
  JVM reelle une fois les mesures prises (jamais par nom d'image).
- Une partie jouee par requetes HTTP reelles (`POST /parties/bandit-manchot-additions-cp`, porte-
  cookies de session), `GET /` en reprise pour obtenir le document assemble de l'ecran de manche
  (methode `ADR-0028` section 2), la barre de niveaux injectee dans son en-tete pour la preuve de
  la section 2.
- Mesures : `outils/mesurer-rendu.sh 360 <url> <png> [640]`, largeur et pli conformes a `ADR-0025`/
  `ADR-0028`. Les mockups et le document assemble charge `recreo.css`/`bootstrap.min.css` depuis
  le jar reconstruit et en cours d'execution ; aucune feuille de style n'a ete dupliquee ni
  modifiee pour la mesure, seule la regle neuve `.bandeau-niveaux`/`.chip-niveau` proposee ici est
  ajoutee en style inline du mockup, jamais dans `recreo.css`.
- Lecture des captures par transition de couleur (colonne par colonne, comparaison aux valeurs
  hexadecimales de `recreo.css` : `#1d4b6b` l'en-tete, `#081c2c` le bandeau, `#f4f7fb` la page).
- Chiffre de repere (`ADR-0028`) : marge de 120 px au-dessus du pli sur un item du paquet
  homophones CM1, cite tel quel, sans nouvelle mesure sur ce paquet precis.

## 10. Tete de branche

`s14-ines` a ce commit, worktree dedie, descendant direct verifie de la tete de `round-two`
(`5761e48`) sans aucun autre commit intercale.
