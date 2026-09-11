# Frise du lever et cadres du plan de classe - proposition, pas un Done

- **Auteure** : `ines-design`. **Date** : 2026-09-11. **Sprint** : s35.
- **Stories** : `S35-QLM14-FRISE` (`FRI-R2`, mon lot), `S35-QLM11-PLANCLASSE` (geometrie des cadres
  `armoire` et `porte`, plus la part transverse du recouvrement des boutons de zone).
- **Destinataires** : `nadia-prof` (audit pedagogique - forme du pictogramme, ZON-15, ZON-9) ;
  `lea-dev` (integration de `cp-plan-de-la-classe`, mecanismes d'`ADR-0092`) ; `theo-archi`
  (obstacle transverse, section 3) ; `sofia-sm` (integration).
- **Ce que ce document est, et ce qu'il n'est pas** : une proposition de forme. Je ne tranche ni le
  Done pedagogique, ni le texte d'un libelle (`ZON-16`, `nadia-prof`), ni le cablage du contenu
  (`lea-dev`). Rien ici n'est acte tant que le depot ne porte pas le commit qui l'integre - pour
  `frise-journee-ecole.svg`, c'est deja fait (fichier livre, section 4) ; pour `plan-classe.svg`, ce
  fichier reste une proposition dans ce repertoire de sprint, comme le veut le precedent de s34.

---

## 1. `FRI-R2` - le pictogramme du lever refait

### 1.1 Le verdict exact, lu avant de dessiner

`nadia-prof`, `verdicts-nadia-instance-B.md`, section 4 : *"Regarde a la taille servie, zones
masquees, puis au triple agrandissement : un rectangle large avec un petit rectangle et un trait,
plus un trait vertical detache qui deborde au-dessus. Je ne trouve pas le lit."*

Relu sur le fichier servi (`frise-journee-ecole.svg`, avant correction) : la zone "lever" tracait
un sommier (rectangle 80 x 45), un oreiller (rectangle 22 x 16, contenu dans le sommier), un pli de
couverture (un trait), et **une tete de lit reduite a une ligne verticale isolee** qui commencait
30 unites au-dessus du bord haut du sommier et se terminait 20 unites sous son bord bas - un trait
sans aucune forme fermee, qui ne se raccorde a rien. C'est exactement ce que `nadia-prof` decrit :
un rectangle, un petit rectangle, un trait, et un quatrieme trait qui ne tient nulle part.

### 1.2 La forme retenue

Un lit vu de profil, deux rectangles pleins partageant la meme base (le sol de la frise) :

- **Un montant de tete de lit** : rectangle etroit et haut (12 x 70), a l'extremite gauche.
- **Un sommier** : rectangle plus bas et plus large (66 x 30), accole au montant sur son bord
  droit et partageant sa base - la silhouette classique d'un lit vu de cote, tete de lit plus
  haute que le matelas.
- **Un oreiller** : petit rectangle a coins arrondis, pose sur le sommier contre la tete de lit,
  qui chevauche legerement le bord haut du sommier (comme un coussin qui bombe) plutot que d'etre
  suspendu au-dessus dans le vide.
- **Un pli de couverture** : un trait horizontal, entierement a l'interieur du sommier, sans
  toucher ni l'oreiller ni les bords.

Ce que ce changement corrige precisement : la tete de lit devient une **forme fermee, accolee** au
sommier sur une base commune, plutot qu'un trait detache qui deborde. Verifie a la taille servie
(96 x 240 px de cadre, le pictogramme occupant l'essentiel de sa largeur) et au triple
agrandissement mental (le rapport des deux rectangles - un montant deux fois plus etroit que haut,
un sommier deux fois plus large que haut - reste lisible a toute echelle, puisqu'aucune des deux
formes ne depend d'un detail sous le plancher de trait).

### 1.3 Ce que je ne change pas

- **Aucun texte, aucun identifiant, aucun libelle** : la zone reste `lever`, "le premier" - `FRI-R1`
  (texte alternatif et indice) est le lot de `lea-dev`, je n'y touche pas.
- **Les cinq autres pictogrammes** ne sont pas retouches : `nadia-prof` n'a refuse que celui-la
  (*"L'item 7, qui vise la meme zone par une relation, n'est pas touche"* - donc pas davantage un
  motif d'y toucher).
- **Le cadre de la zone `lever`** (`x=0 y=10 largeur=15 hauteur=75`) ne bouge pas : le defaut etait
  dans le trace, pas dans la boite qui le contient.

### 1.4 Poids du fichier, cliquet mis a jour dans le meme commit

Le dessin corrige pese 649 octets de plus (deux formes fermees remplacent une ligne detachee et
regagnent en retracant l'oreiller et le pli a des coordonnees coherentes avec le nouveau sommier).
`frise-journee-ecole.svg` reste le plus lourd du repertoire des illustrations : le cliquet de poids
(`ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest`,
`POIDS_D_UN_DESSIN_MAXIMUM` et `POIDS_TOTAL_MAXIMUM`) est releve de 3 984 a 4 633 octets et de
37 812 a 38 461 octets, dans le meme commit que le dessin, avec le motif ecrit dans le Javadoc de
chaque constante (ADR-0036 clause 5).

---

## 2. `S35-QLM11-PLANCLASSE` - geometrie de `armoire` et `porte`

### 2.1 Ce que la mesure de `theo-archi` et de `nadia-prof` dit, et ce qu'elle ne dit pas

`ADR-0092`, section 4.1 et 4.2 : le pave de `.zone` (rembourrage + bordure) coutait 44 px de la
largeur d'un cadre avant le premier caractere d'un libelle. Sur les deux cadres les plus etroits du
plan de classe - `armoire` (56 x 77 px) et `porte` (63 x 56 px) -, cela ne laissait que 12 a 19 px
de boite de contenu, sous le plancher de lisibilite de `nadia-prof` (`ZON-15`, 1,25rem) quel que
soit le libelle choisi. `theo-archi` nomme trois leviers possibles : agrandir le cadre, raccourcir
le libelle (`ZON-16`, `nadia-prof`), ou reduire le pave de `.zone` - ce troisieme lui semblant
"gratuit" parce qu'il ne touche pas au plancher de cible tactile (garde-fou 3 d'`ADR-0080`).

### 2.2 Le troisieme levier essaye, puis ecarte - une regle que la mesure a trouvee avant moi

J'ai d'abord essaye la combinaison "cadre agrandi + pave de `.zone` retreci", pour ne pas pousser
le cadre seul a l'extreme. **`mvn test` l'a refusee, et il avait raison.**
`LActionNeDeclarePasMoinsQueLaSortieTest` (`ADR-0017` : sur un meme ecran, aucune composante de
boite d'une action ne peut etre inferieure a celle d'une sortie) a rougi des que j'ai ramene le
rembourrage horizontal de `.zone` a `0,25rem` : sur l'ecran de manche de designation, l'en-tete
porte `.chip-niveau` (rembourrage horizontal `0,9rem`) et `.lien-accueil` "Les jeux" (`1,1rem`),
tous deux des **sorties**, pendant que `.zone` est l'**action** qui fait avancer la manche.
`ADR-0017` interdit qu'une sortie declare une composante de boite superieure a celle de l'action du
meme ecran - exactement l'inverse de la hierarchie que `nadia-prof` a decrite pour `ADR-0017`
(*"ce qui appelle la main doit etre ce qui fait avancer le jeu, jamais ce qui en sort"*).

**Ce que cela change au troisieme levier de `theo-archi`** : il n'est pas "gratuit" comme la
section 4.2 de son avis le presente - cette section verifie seulement qu'il ne descend pas sous le
plancher de cible tactile, jamais qu'il reste au-dessus du plancher de salience d'`ADR-0017`. Le
plancher reel de `.zone` n'est donc pas 0 (limite du seul garde-fou 3) mais **1,1rem**, la plus
large des sorties d'en-tete servies sur un ecran de jeu. Y ramener le rembourrage ne gagnerait que
`(1,25 - 1,1) x 2 x 16 = 4,8 px` sur les 44 px d'origine - une marge trop mince pour justifier de
toucher une regle de mecanique partagee par tout ecran de jeu, pour un gain qui ne resout rien sur
les deux cadres en cause. **Je n'y touche pas** : `recreo.css` reste inchange, et le levier retenu
est le seul qui reste de mon ressort sans franchir `ADR-0017` - le cadre (section 2.3).

Je le remonte a `theo-archi` : son arithmetique de la section 4.2 de son avis reste juste sur le
garde-fou 3, mais elle ne mesure pas la contrainte d'`ADR-0017`, qui est la premiere a mordre.

### 2.3 Nouvelle geometrie, les deux cadres seulement

| id | avant (s34) | apres (s35) | largeur en px (352 = 100 %) | hauteur en px |
|---|---|---|---|---|
| `armoire` | x=6 y=55 largeur=16 hauteur=22 | x=4 y=48 largeur=30 hauteur=30 | 105,60 | 105,60 |
| `porte` | x=8 y=80 largeur=18 hauteur=16 | x=4 y=80 largeur=32 hauteur=17 | 112,64 | 59,84 |

Les quatre autres cadres (`tableau`, `bureau`, `fenetre`, `coin-lecture`) ne bougent pas - hors du
perimetre que la story me route, et deja assez larges (98 a 140 px) pour ne pas avoir motive la
reserve de `nadia-prof`.

**Non-chevauchement (`ZON-13`), verifie paire par paire sur les six rectangles** :
- `armoire` (x 4-34, y 48-78) vs `bureau` (x 15-43, y 26-46) : aucun recouvrement en y (48 > 46,
  marge de 2).
- `armoire` vs `porte` (x 4-36, y 80-97) : marge de 2 en y (78 a 80).
- `armoire` et `porte` vs `coin-lecture` (x 58-92, y 76-96) : aucun recouvrement en x (34 et 36
  restent sous 58).
- `porte` vs `bureau` : aucun recouvrement en y (80 > 46).
- Les six rectangles restent dans `[0,100]` sur les deux axes ; `porte` touche desormais le mur du
  bas exactement (y=97, le bord interieur du mur), au meme titre que `coin-lecture` le touchait
  deja a x=92.

**Plancher de cible tactile (garde-fou 3 d'`ADR-0080`)**, verifie sur le plus petit cote de chaque
cadre multiplie par le plafond de hauteur (352 px) : `armoire`, plus petit cote 30 % -> 105,60 px ;
`porte`, plus petit cote 17 % -> 59,84 px (inchange, la hauteur ne bouge pas). Les deux restent tres
au-dessus du plancher de 44 px.

**Boite de contenu resultante**, avec le pave de `.zone` **inchange** (`1,25rem` horizontal, 44 px
au total, section 2.2) :

| id | largeur de cadre | boite de contenu |
|---|---|---|
| `armoire` | 105,60 px | 61,60 px |
| `porte` | 112,64 px | 68,64 px |

Contre 12 px et 19 px avant ce lot - une multiplication par 5 et par 3,6, obtenue par la seule
geometrie, sans toucher a une regle partagee par tout ecran de jeu.

**Le dessin est agrandi avec le cadre** : l'armoire et la porte occupent desormais une part plus
grande du plan qu'avant - un choix assume, pas un effet de bord cache. La proportion entre meubles
cesse d'etre strictement realiste (une armoire n'occupe pas visuellement le tiers d'une salle de
classe), mais `ZON-12` (vue de dessus, schema et non perspective) n'a jamais demande une echelle
realiste entre meubles, seulement l'absence de perspective - ce que le dessin garde. Je le nomme
pour que `nadia-prof` le juge en le voyant, pas pour le justifier d'avance.

**Ce que je ne pretends pas** : `ADR-0092`, decision 2, est explicite - la propriete *"le libelle
tient entierement dans son cadre a 1,25rem"* ne depend pas seulement du cadre, elle depend de la
police de l'appareil de l'enfant, que ce depot ne versionne pas. Les chiffres ci-dessus sont une
estimation raisonnable pour un libelle court (un a deux mots), pas une garantie - `nadia-prof`
reste seule a la fermer, a l'oeil, sur l'ecran servi, comme `ADR-0092` le decide.

### 2.4 Fichier livre pour cette partie

`plan-classe.svg`, dans ce repertoire de sprint (pas encore integre a
`adapters/adapter-web/.../static/illustrations/`, puisque `QLM-CP-11` n'est pas encore servie) :
seuls les deux groupes `armoire` et `porte` changent, le reste du fichier est identique a la
proposition de s34. `lea-dev` l'integre avec le paquet `cp-plan-de-la-classe` et les deux
mecanismes d'`ADR-0092` qui lui sont dus dans le meme lot. `recreo.css` n'est pas touche par cette
story (section 2.2).

---

## 3. L'obstacle transverse - le recouvrement des boutons de zone sur le dessin

### 3.1 Ma position sur l'alternative (d) de `theo-archi`

`avis-faisabilite-theo.md`, section 5, et `ADR-0092`, alternative (d) : rendre le bouton de zone
elastique (`min-width`/`min-height` au lieu de `width`/`height` fixes), pour qu'un libelle ne soit
jamais coupe, quel que soit l'appareil.

**Je ne la retiens pas.** Trois motifs :

1. **Elle demande a `nadia-prof` de renverser sa propre condition.** `ZON-15` exige qu'un libelle
   *"ne deborde ni ne touche une zone voisine"* - un bouton qui grandit avec son texte peut, sur un
   plan a six zones rapprochees comme celui de la classe, empieter sur la zone d'a cote. Ce n'est
   pas une question de forme que je peux trancher a sa place.
2. **Elle casse la coherence visuelle entre les six zones d'un meme ecran.** Un enfant de CP lit
   l'ecran comme un tout ; six boutons de tailles differentes, dont la taille varie avec la longueur
   du mot qu'ils portent, introduisent une irregularite que rien ne motive pedagogiquement - la
   zone la plus large ne designe pas le meuble le plus important, elle designe simplement celui qui
   a le libelle le plus long.
3. **Elle ne resout rien pour l'ecran de manche lui-meme.** Le cadre reste positionne a l'endroit
   exact du meuble (`CadreDeZone`) ; un bouton qui deborde de ce cadre pour loger son texte
   recouvre alors une part du dessin **plus grande** que le cadre initial, ce qui aggrave plutot que
   n'attenue le fait le plus lourd de l'audit de `nadia-prof` - le bouton cache le dessin qu'il
   designe.

### 3.2 Ce que je retiens a la place

Ni (a) (une avance de caractere epinglee - `theo-archi` l'a deja ecartee par la mesure, aucune
police n'est versionnee dans ce depot pour lui servir de second terme), ni (b) (vendoriser une
police - un changement d'apparence sur tout l'ecran du produit, hors du perimetre d'un sprint qui
ferme des refus, et une porte pedagogique a elle seule sur la lisibilite d'une police pour un
enfant de CP), ni (c) (fondre la propriete dans le garde-fou de cible tactile - deja mesuree fausse
sur trois des six cadres du plan).

**Je retiens le seul des trois leviers de la decision 3 d'`ADR-0092` qui reste disponible sans
toucher `ADR-0017`** : agrandir les deux cadres les plus etroits (section 2.3). Le pave de `.zone`,
que `theo-archi` nommait comme troisieme levier, s'est revele borne par en dessous par la
hierarchie de salience d'`ADR-0017` (section 2.2) - je le laisse a `1,25rem`, et je le dis a
`theo-archi` plutot que de laisser son avis annoncer un levier plus genereux qu'il ne l'est. La
forme retenue reste fixe (le bouton n'a jamais une taille qui depend du texte qu'il porte),
coherente entre les six zones d'un meme ecran, et elle ne deplace le probleme sur aucun autre
paquet de designation.

### 3.3 Ce que je ne fais pas

Je ne choisis pas une forme qui supprimerait le recouvrement du dessin par le bouton lui-meme (le
fond translucide a 55 % qui reste, quelle que soit la taille du cadre, pose par-dessus le trait
qu'il designe) : `ADR-0092` le borne sans le trancher, et une forme qui changerait cela - un fond
plus transparent, un libelle pose hors de la boite de l'image, une zone qui ne serait plus un
bouton plein - est un changement plus large que la geometrie de deux cadres, hors du perimetre
route pour ce sprint. Je le nomme pour qu'il ne se perde pas une cinquieme fois.

---

## 4. Fichiers livres

- `adapters/adapter-web/src/main/resources/static/illustrations/frise-journee-ecole.svg` - modifie
  (pictogramme "lever", `FRI-R2`). Deja integre, commit direct.
- `adapters/adapter-web/src/test/java/com/recreo/adapter/web/ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest.java`
  - cliquet de poids releve dans le meme commit.
- `plan-classe.svg`, dans ce repertoire de sprint - proposition, pas encore integree. `lea-dev`
  l'integre avec le paquet `cp-plan-de-la-classe`.
- ce document.

**`recreo.css` n'est pas modifie** : le levier envisage en section 2.2 est ecarte par la mesure
(`ADR-0017`), et c'est ecrit ici plutot que silencieusement abandonne.
