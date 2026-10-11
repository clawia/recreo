# Proposition de forme - l'accueil en deux vues et le bouton de bascule (hors sprint, chantier du 2026-10-11)

- Auteure : `ines-design`.
- Date : 2026-10-11.
- Branche : `ines-accueil`, issue de `round-two` a `8ac33440`.
- Demande : l'utilisatrice, 2026-10-11 : *"il liste comme actuellement prend trop de place et on perd les enfants au scroll, on n'a aucune idee de la matiere associee"*. V1 = deux etages (tuiles de matiere, puis jeux de la matiere en cartes). V2 = une page de cartes groupees par matiere sous des bandeaux colles. V2 par defaut, un bouton de l'accueil bascule.
- Dossier : `squad/conformite/releves-accueil/` (aucun dossier fixe par `camille-po` n'etait lisible a l'ecriture de ce document : repli prevu par la consigne). Fichiers listes en section 8.

## 0. Ce que ce document est, et ce qu'il n'est pas

Une proposition de forme. **Aucun gabarit, aucune feuille de style, aucun controleur n'est modifie** : les regles candidates vivent dans `accueil-vues.css`, chargee apres les deux feuilles reelles (ordre d'`ADR-0014`). Je ne prononce aucun Done pedagogique : libelles, pictogrammes, ordre des matieres et choix de la vue par defaut sont a `nadia-prof` (section 7).

Ce qui est mesure l'est sur des maquettes statiques rendues avec les feuilles reelles, **sauf la liste actuelle, mesuree sur le jar** (construit depuis ce worktree) et recoupee avec sa maquette : meme boite, a l'unite pres, sur 12 cas (section 4.1). Aucun garde-fou du depot n'a ete lance sur les regles candidates.

## 1. En bref

1. **V2 gagne peu de cartes visibles, beaucoup de longueur.** A 744 x 551 : 8 cartes entieres au lieu de 5 dans les trois niveaux. Le document raccourcit de 28 % (CP), 38 % (CE1) et 29 % (CM1). **A 390 il rallonge** de 12 %, 6 % et 12 % : une colonne et onze bandeaux ne compensent pas.
2. **V1 etage 1 tient dans le pli** : 8 tuiles sur 8 en CP et en CM1, 2 sur 2 en CE1, a 744 comme a 992. A 390 : 4 tuiles entieres et 6 au moins en partie sur 8 (document de 838 px).
3. **V1 etage 2 ne tient pas pour les gros lots** : 8 cartes entieres sur 19 (Sciences CM1), 8 sur 16 (Mathématiques CE1), 8 sur 13 (Mathématiques CP). Les matières de 5 jeux ou moins tiennent entieres.
4. **Le catalogue ne compte pas 8 a 9 matieres dans chaque niveau.** CP 8, **CE1 2** (Français 14, Mathématiques 16), CM1 8. Methode : `descripteur.matiere` de chacun des 147 jeux, lu par `generer-maquettes.py`. Pour CE1, l'etage 1 de V1 propose un choix entre deux tuiles.
5. **Le colle fonctionne** : un seul bandeau est a `top: 0` apres defilement, aux trois largeurs et dans les trois niveaux. Il coute 48 px de fenetre en permanence (8,7 % de 551).
6. **Mes reserves principales** (section 6) : V2 montre une seule matiere au premier ecran a 744 ; l'accueil sans niveau melange les trois niveaux sous un meme bandeau ; le bouton de bascule ressemble a la sortie par l'article de son libelle si je garde "Les matières" (j'ai donc retenu "Par matière") ; quatre teintes sont voisines de couleurs de sens deja prises.

## 2. Ce qui est propose

### 2.1 Regles communes aux deux vues

- **La couleur de l'action ne change pas.** Le plein d'accent (`.demarrer`) reste le seul "lance un jeu", le bouton de la liste actuelle est reutilise tel quel. Une tuile de matiere est un **choix** (pastel, texte sombre, contour de la teinte) comme les propositions de reponse ; elle n'est pas pleine d'accent (meme motif que le refus nomme de `nadia-prof`, `FICHE-barre-entree-niveau.md`, arbitrage du 2026-08-18, section 7 point 3).
- **La phrase du filtre reste** ("Pour le CP", `S15-2`) a gauche du rang au-dessus de la liste, et la puce du niveau reste pleine.
- **Une seule action a la fois** : sur l'accueil, les cartes ou les tuiles, plus un seul bouton de vue. L'etage 2 de V1 n'a que le bouton de retour, pas la bascule.
- **Rien ne nomme une matiere dans une regle** : la teinte est un emplacement numerote (`data-teinte`, onze emplacements), comme les quatre teintes cycliques des propositions ; le pictogramme est une image dont le nom est une donnee. Qui occupe quel emplacement est une donnee, pas une regle de feuille.

### 2.2 V2, la vue par defaut

Un bandeau par matiere (`h2`, 1,5 rem gras, pictogramme de 2,25 rem, aplat de la teinte, liseret plein de 0,5 rem a gauche, ni rayon ni ombre pour ne pas se lire pressable), puis les jeux en grille `repeat(auto-fill, minmax(15rem, 1fr))` : **2 colonnes a 744, 1 a 390, 3 a 992**. Le bandeau est `position: sticky; top: 0` borne par sa section : il cede la place au suivant. `html:has(.bloc-matiere)` pose `scroll-padding-top: 4rem` pour qu'un focus clavier n'arrive pas sous le bandeau.

### 2.3 V1, deux etages

- **Etage 1** : une tuile par matiere du niveau, grille `minmax(9.5rem, 1fr)` : **4 colonnes a 744, 2 a 390, 5 ou plus a 992**. Pictogramme de 3,5 rem au-dessus du libelle (1,25 rem gras). La tuile est un lien (navigation complete, comme une puce de niveau).
- **Etage 2** : le meme bandeau et les memes cartes que V2, pour une seule matiere, et un bouton "Par matière" (chevron a gauche) qui revient a l'etage 1.

### 2.4 Le bouton de bascule

- **O1, ma proposition : un seul bouton, dont le libelle nomme la vue ou il mene.** Sur V2 : "Par matière" (icone de quatre carres). Sur l'etage 1 de V1 : "Tout voir" (icone de trois lignes). Meme boite que `.retour-accueil` : 1,25 rem, 2,75 rem de haut minimum, rembourrage 0,5 rem 1,1 rem, contour d'accent de 0,125 rem, fond transparent. Il ne depasse donc aucune composante d'une action de l'ecran (`ADR-0017`) et son contour dit "secondaire".
- **Place : au-dessus de la liste, a droite de la phrase du filtre, pas dans l'en-tete.** L'en-tete est permanent, y compris sur une manche, ou une bascule de vue n'a pas de sens ; il est deja plein a 744 et passe sur deux rangs a 390 (140 px) ; une troisieme zone pressable y ajouterait un pressable a la barre dont `nadia-prof` a deja arbitre le contenu (arbitrage du 2026-08-18).
- **Etat sans couleur** : le libelle dit la destination, l'icone a une forme propre a chaque vue, et l'ecran lui-meme montre la vue courante (cartes sous bandeaux, ou tuiles). `capture-v2-CP-gris-744x551.png` et `capture-v1-etage1-CP-gris-744x551.png` sont rendues avec `filter: grayscale(1)`. Que la distinction tienne est une lecture a l'oeil de ces captures, pas une mesure.
- **O2, non retenue** : deux segments, la vue courante portant une coche et un trait plein (`maquette-bascule.html`, `capture-bascule-744x560.png`). Elle montre un etat que l'enfant n'a pas produit (la vue par defaut), ce que `S15-5` n'acceptait pour la puce que parce que l'enfant venait de l'appuyer, et elle met une coche dans un controle (`FICHE-barre-entree-niveau.md`, section 7 point 6). **Boite non mesuree.**

## 3. Libelles courts et pictogrammes (DEC-0002 : contenu enfant, a arbitrer par `nadia-prof`)

Les 11 matieres du catalogue (`descripteur.matiere`), dans l'ordre que je propose pour les pages, sans autre source que mon choix (a arbitrer par `camille-po` et `nadia-prof`). Les pictogrammes sont des SVG de ma main (`pictos/`, 48 x 48, trait de 3 px a bouts ronds, encre `#1c2733`, interieur blanc), sans texte sauf "Hi". `maquette-pictos.html` les montre avec leur libelle complet.

| Matiere (graphie du depot) | Libelle court propose | Pictogramme | Emplacement de teinte |
|---|---|---|---|
| Français | Français | livre ouvert | 1 |
| Mathématiques | Maths | ardoise portant + et = (reprise du 2026-10-11, section 9) | 2 |
| Langues vivantes (anglais) | Anglais | bulle portant "Hi" | 3 |
| Sciences et technologie | Sciences | fiole d'Erlenmeyer | 4 |
| Questionner le monde | Questionner le monde | loupe sur une feuille | 5 |
| Histoire-géographie | Histoire-géographie | globe | 6 |
| Enseignement moral et civique | Vivre ensemble | deux personnes | 7 |
| Arts plastiques | Arts plastiques | palette | 8 |
| Éducation musicale | Musique | deux croches | 9 |
| Histoire des arts | Histoire des arts | tableau dans son cadre | 10 (teinte reprise, section 9) |
| Éducation physique et sportive | Sport | ballon de basket | 11 |

- **Pourquoi des libelles courts** : avec les noms complets, deux tuiles sur huit en CM1 ont leur texte dans le rembourrage a 744 ("Mathématiques" et "Enseignement", 3 lignes pour "Enseignement moral et civique") ; aucun avec les libelles courts. `capture-v1-etage1-CM1-libelles-longs-744x551.png`, `maquette-v1-etage1-CM1-libelles-longs.html`, `synthese-mesures.txt` section I (document de 572 px au lieu de 551 a 744, 8 tuiles entieres dans les deux cas).
- **Trois libelles s'ecartent du nom de programme** : "Maths", "Vivre ensemble", "Sport". Ce sont des propositions de longueur, pas de contenu ; `nadia-prof` dit si un enfant de CP les reconnait. Repli si elle refuse "Vivre ensemble" : le nom complet, trois lignes, mesure ci-dessus.
- **Ce que le pictogramme dit** : un objet que l'enfant connait de l'ecole ou de la cour pour chaque matiere ; la loupe (Questionner le monde) est mon choix le moins sur. La regle graduee de Maths a ete refusee et remplacee (section 9).

### 3.1 Contraste (WCAG 2.x, `contraste-teintes.py`, lu sur `accueil-vues.css` et `recreo.css`)

| Emplacement | Fond | Contour | Encre / fond | Contour / page |
|---|---|---|---|---|
| 1 | `#cfe3f6` | `#2f6c9f` | 11,51 | 5,19 |
| 2 | `#f4e3a1` | `#7d6208` | 11,78 | 5,40 |
| 3 | `#f8d3c2` | `#a5472a` | 10,88 | 5,53 |
| 4 | `#bfe8e4` | `#1f7770` | 11,44 | 4,97 |
| 5 | `#d4ecc0` | `#4a7a1c` | 11,94 | 4,78 |
| 6 | `#e8d6bf` | `#7a5230` | 10,67 | 6,35 |
| 7 | `#f6cde0` | `#a03a6b` | 10,62 | 5,90 |
| 8 | `#e2d4f3` | `#6e4ba3` | 10,77 | 6,11 |
| 9 | `#d2d6f6` | `#4a50a8` | 10,59 | 6,51 |
| 10 | `#f0baf3` | `#85188b` | 9,39 | 7,82 |
| 11 | `#fbdcb4` | `#a35a0b` | 11,53 | 4,86 |

Les planchers du depot (4,5:1 texte, 3:1 composant non textuel) sont tenus partout. **Le fond d'une teinte contre la page ne fait que 1,17 a 1,33:1** : c'est le contour (tuile) ou le liseret (bandeau) qui delimite la forme, pas l'aplat. Autres rapports lus : bouton de vue, accent sur page 5,03:1 ; carte, blanc sur accent 5,41:1 (inchange) ; pictogramme, encre sur blanc 15,14:1. Les memes rapports, lus en page par `getComputedStyle` sur l'emplacement 1 : 11,51 ; 5,19 ; 5,03 ; 5,41 (`mesures-accueil.json`, cle `couleurs`).

**La couleur est un doublon, jamais le signal** : pictogramme et mot portent le sens, l'aplat disparait presque en niveaux de gris (`capture-v1-etage1-CP-gris-744x551.png`). Onze teintes ne sont pas toutes distinguables pour un enfant daltonien et je ne le pretends pas : **aucune simulation de daltonisme n'a ete faite**.

## 4. Mesures

Commandes en section 8. Chrome 154.0.8037.98, headless, `Emulation.setDeviceMetricsOverride` a **744, 390 et 992 de large sur 551 de haut** (`mobile: false`, echelle 1, barre de defilement classique). Cotes en px, origine en haut de la page non defilee, pli a **551**. **Entiere** = bas de la boite a 551 ou moins ; **en partie** = haut de la boite au-dessus de 551. Chaque chiffre vient de `getBoundingClientRect()` ; `mesures-accueil.json` porte les 138 mesures brutes (46 pages : 42 maquettes et 4 pages du jar, a trois largeurs), `synthese-mesures.txt` les tableaux.

### 4.1 La liste actuelle, mesuree sur le jar, et sa maquette

| Niveau | Jeux | 744 : entieres (en partie), document | 390 | 992 |
|---|---|---|---|---|
| CP | 59 | 5 (6), 4216 px = 7,7 ecrans | 4 (5), 4394 | 5 (6), 4216 |
| CE1 | 30 | 5 (6), 2244 px = 4,1 ecrans | 4 (5), 2332 | 5 (6), 2244 |
| CM1 | 58 | 5 (6), 4148 px = 7,5 ecrans | 4 (5), 4326 | 5 (6), 4148 |
| Sans niveau (`/`) | 147 | 6 (7), 10162 px = 18,4 ecrans | 5 (6), 10490 | 6 (7), 10162 |

Chaque bouton fait 56 px de haut (86 px quand le titre passe sur deux lignes a 390 : 4 boutons en CP, 1 en CE1, 4 en CM1, 9 sans niveau). **La maquette de la liste actuelle rend les memes hauteurs de document et la meme cote de premiere carte que le jar, aux trois largeurs, pour les quatre pages (12 cas)** : le vehicule est valide sur ce qu'il remplace. L'ordre servi est celui du catalogue : les matieres s'y alternent ("Les petites additions", "Le bon son", "Les petites soustractions", `capture-actuelle-CP-744x551.png`), c'est la source du "aucune idee de la matiere".

### 4.2 V2 et V1 etage 1, aux trois largeurs

| Niveau | Largeur | Actuelle : entieres, document | V2 : entieres (en partie), document, ecart | V1 etage 1 : tuiles entieres (en partie) / total, document |
|---|---|---|---|---|
| CP | 744 | 5, 4216 | 8 (10), 3020, **-28,4 %** | 8 (8) / 8, 551 |
| CP | 390 | 4, 4394 | 3 (4), 4914, **+11,8 %** | 4 (6) / 8, 838 |
| CP | 992 | 5, 4216 | 12 (12), 2400, **-43,1 %** | 8 (8) / 8, 551 |
| CE1 | 744 | 5, 2244 | 8 (10), 1390, **-38,1 %** | 2 (2) / 2, 551 |
| CE1 | 390 | 4, 2332 | 3 (4), 2468, **+5,8 %** | 2 (2) / 2, 551 |
| CE1 | 992 | 5, 2244 | 9 (12), 1238, **-44,8 %** | 2 (2) / 2, 551 |
| CM1 | 744 | 5, 4148 | 8 (10), 2952, **-28,8 %** | 8 (8) / 8, 551 |
| CM1 | 390 | 4, 4326 | 3 (4), 4846, **+12,0 %** | 4 (6) / 8, 838 |
| CM1 | 992 | 5, 4148 | 10 (10), 2626, **-36,7 %** | 8 (8) / 8, 551 |

Un document de 551 px est une page qui ne defile pas. A 744, la premiere carte de V2 est a 240 px (168 px dans la liste actuelle) : en-tete 82, ligne du filtre et du bouton 122-172, bandeau 180-228. **Les 8 cartes entieres sont toutes dans la premiere matiere** (Français) ; la matiere suivante n'apparait qu'a 992 en CP et en CM1. Premier bandeau visible a 744 et a 390 : Français seul ; a 992 : Français et Maths (CP, CM1).

### 4.3 V1 etage 2, a 744 (cartes entieres sur total, hauteur du document)

- **CP** : Mathématiques 8 / 13 (752), Français 8 / 12 (684), Questionner le monde 8 / 12 (744), Enseignement moral et civique 8 / 11 (714), Arts plastiques 5 / 5, Éducation physique et sportive 3 / 3, Éducation musicale 2 / 2, Langues vivantes 1 / 1.
- **CE1** : Mathématiques 8 / 16 (850), Français 8 / 14 (752).
- **CM1** : Sciences et technologie 8 / 19 (1016), Enseignement moral et civique 8 / 11 (714), Français 8 / 10 (616), Mathématiques 8 / 10 (646), Histoire-géographie 4 / 4, Éducation physique et sportive 2 / 2, Histoire des arts 1 / 1, Langues vivantes 1 / 1.

Trois matieres n'ont qu'un jeu (Langues vivantes en CP et en CM1, Histoire des arts en CM1) : un bandeau et une tuile pour un seul bouton.

### 4.4 Sans niveau (`/`), mesure par precaution

Sans niveau, la page porte les 147 jeux. V2 : 6354 px a 744 (11,5 ecrans, 8 cartes entieres) contre 10162 pour la liste actuelle ; 11240 px a 390. V1 etage 1 : 11 tuiles, 8 entieres a 744, document de 652 px. Les gros lots y fusionnent les niveaux : **Mathématiques 39 cartes, Français 36, Enseignement moral et civique 22** (a 744, document de 1696, 1500 et 1084 px). Ni le bandeau ni la carte ne portent le niveau (`AucunTitreDeJeuNePorteSonNiveauTest`) : un titre qui se repete se voit deux fois sous le meme bandeau (un seul titre du catalogue est en double : "Un ou plusieurs ?").

### 4.5 Cibles, collisions, debordement

Mesure par `getBoundingClientRect()` sur chaque `button` et `a[href]`, a 744, 390 et 992, pour les 3 niveaux (V1 etage 1, V2 et V1 etage 2).

| Cible | Plus petite boite (largeur x hauteur) |
|---|---|
| Puce de niveau | 56,4 x 50 |
| "Les jeux" | 108,8 x 50 |
| Bouton de vue ("Par matière" 171,2 / "Tout voir" 148,1) | 148,1 x 50 |
| Carte | 304 x 56 (992), 342 x 56 (744), 351 x 56 (390) |
| Tuile | 165 x 116 (a 744 : 165 x 140 en CP, 165 x 116 en CE1 et CM1) |

Tout est a 44 px ou plus sur les deux axes. **Ecart minimal entre deux cibles : 12 px** (8 px a 390, ou l'en-tete passe sur deux rangs). **Zero chevauchement, zero texte coupe, zero texte dans le rembourrage, zero debordement horizontal** aux trois largeurs, pour les pages en libelles courts. Rapport de surface (744, V2, boites mesurees) : bouton de vue / carte = 171,2 x 50 / 342 x 56 = 0,45 ; "Les jeux" / bouton de vue = 0,64. Les cartes grandissent par rapport a la liste actuelle (leur largeur passe de la largeur du titre, 144 a 272 px, a la cellule, 342 px) : l'ecart de taille entre l'action et la sortie s'agrandit.

## 5. Ce que le bouton de bascule fait, concretement

| Ecran | Bouton | Mene a |
|---|---|---|
| V2 (defaut) | "Par matière", quatre carres | V1 etage 1, meme niveau |
| V1 etage 1 | "Tout voir", trois lignes | V2, meme niveau |
| V1 etage 2 | "Par matière", chevron a gauche | V1 etage 1 |

Les puces de niveau gardent la vue courante dans les maquettes (V1 reste V1, V2 reste V2) ; "Les jeux" mene a la vue par defaut. Aucune de ces deux regles n'est dans le produit : voir Q1 et Q2 en section 7.

## 6. Mes reserves

1. **V2 ne montre qu'une matiere au premier ecran a 744** (Français, 8 cartes sur 59). Le bandeau colle repond a "dans quelle matiere suis-je", pas a "quelles matieres existe-t-il". Seul l'etage 1 de V1 montre l'ensemble en un ecran. Une rangee de pictogrammes-ancres au-dessus de V2 reconstruirait cet ensemble ; je ne l'ai pas dessinee, elle ajoute une zone pressable.
2. **V2 ne regle pas "trop de place" sur un petit ecran** : +6 a +12 % de longueur a 390. Le gain de V2 est a 744 et plus.
3. **V1 coute un appui de plus** (matiere, puis jeu) et un aller-retour pour changer de matiere. En CE1 l'etage 1 est un choix entre deux tuiles.
4. **Quatre teintes sont voisines d'une couleur de sens deja prise** : l'emplacement 5 (vert tendre, contour `#4a7a1c`) et le vert du signal juste (`#2e7d32`) ; l'emplacement 11 (abricot, contour `#a35a0b`) et l'ambre de l'indice (`#b45309`) ; l'emplacement 8 (violet clair) et le violet du bloc adulte (`#5a3d8a`) ; l'emplacement 3 (corail) et le rouge du signal faux (`#c62828`) a moindre degre. Aucun de ces sens n'existe sur l'accueil, mais `ADR-0075` section 7 a choisi une cinquieme couleur plutot que d'en reprendre une de sens. **Repli sans teinte** : `body.teinte-neutre` ramene tout a l'aplat du bloc d'explication, pictogramme et mot seuls (`capture-v2-CP-neutre-744x551.png`).
5. **Le bouton de vue est large et se range sous "Les jeux"** : 171,2 px contre 108,8, a 56 px en dessous, tous deux a contour. `ADR-0017` ne voit pas la largeur. J'ai ecarte "Les matières" parce qu'il partage l'article du mot de sortie ; "Par matière" ne le partage pas. **La confusion exit/vue reste a juger en jouant.**
6. **Les cartes en 2 lignes sont des gelules** (rayon de `.demarrer` inchange, 999 px) : 86 px de haut, texte centre, aucune coupure. Je n'ai pas change la forme, qu'`S8-1` et `nadia-prof` ont validee pour dire "appuie".
7. **Pictogrammes : ma main, 48 px.** La reconnaissance par un enfant de CP n'est pas mesuree et ne peut pas l'etre ici. Les cinq que je juge le moins surs : regle (Maths), loupe sur feuille (Questionner le monde), deux personnes (Vivre ensemble), globe (Histoire-géographie contre Questionner le monde sur la page sans niveau), bulle "Hi" (Anglais).
8. **Mesures sur Chrome headless, fenetre emulee, sans toucher** : ni tablette reelle, ni Safari iOS, ni defilement a l'inertie, ni parcours clavier. Le colle est mesure par un defilement programme (`behavior: 'instant'`, la feuille tierce pose `scroll-behavior: smooth`).
9. **Garde-fous non lances** : `LActionNeDeclarePasMoinsQueLaSortieTest`, `TailleDesTextesEtBoutonsTest`, la derivation d'`ADR-0046`, `AucunGabaritNiFeuilleMaisonNeNommeUnContenuParticulierTest` et `LHabillageResteImmobileTest` lisent `templates/` et `static/css/`, pas ce dossier. Les rembourrages sont ecrits pour passer `ADR-0017` (tuile et carte `0,75rem 1,25rem` contre sortie `0,5rem 1,1rem` ; bouton de vue `0,5rem 1,1rem`), mais c'est une lecture, pas un vert.
10. **Un enfant de CP lit "Par matière" et "Tout voir" : non juge.** Ce sont deux libelles de moi.

## 7. Ce que je route et ce que je laisse

### 7.1 A `nadia-prof` (veto pedagogique)

- **N1** Libelles courts : "Maths", "Anglais", "Sciences", "Vivre ensemble", "Musique", "Sport" ; repli sur le nom complet.
- **N2** Les onze pictogrammes (section 3 et `pictos/`), un par un.
- **N3** Les deux libelles du bouton : "Par matière", "Tout voir" ; et la presence d'une icone dans un bouton (`BARRE-4` et `S15`, section 7 point 6, refusaient un pictogramme dans une puce).
- **N4** La vue par defaut (V2) : l'enfant arrive sur une vue qu'il n'a pas choisie ; avec O1 aucun controle ne la marque comme un etat de selection. Sa lecture de `S15-5` pour ce cas.
- **N5** L'ordre des matieres (section 3).
- **N6** Les onze teintes, ou le repli neutre (reserve 4).
- **N7** La page sans niveau : un bandeau qui reunit trois niveaux (section 4.4).
- **N8** Le cout de 48 px du bandeau colle sur un pli de 551.

### 7.2 A `sofia-sm`, puis `theo-archi` et `marc-dev` ou `lea-dev`

- **R1** Rien de ceci n'est dans le modele servi : `GameDefinition.descripteur().matiere()` existe, mais le regroupement par matiere, l'ordre des matieres, le libelle court, le nom du pictogramme et l'emplacement de teinte n'existent pas. Table indexee par la graphie exacte de `descripteur.matiere` (11 chaines accentuees) : **une matiere inconnue a besoin d'un repli** (tuile neutre, nom complet, sans pictogramme) ; `generer-maquettes.py` s'arrete dessus.
- **R2** `data-role` de la tuile (je mets `action`) et du bouton de vue (je mets `entree`) : a trancher par `theo-archi` (`ADR-0017`, `ADR-0043`). Aucun gabarit n'est modifie, donc le balisage de la maquette est un point de depart, pas un contrat.
- **R3** L'etat de la vue : dans l'URL (comme `/niveaux/{niveau}`, `DEC-0028`), dans la session, ou nulle part. Sans etat, un enfant qui prefere V1 la rebascule apres chaque partie (Q2).
- **R4** Les routes de l'etage 2 et le mode d'echange (navigation complete comme une puce, ou `hx-get` sur `#jeu`).
- **R5** SVG sous `static/images/` (precedent : `cabinet-fronton.svg`) et lecture du nom de pictogramme depuis la donnee.

### 7.3 A `camille-po`

- **Q1** Les puces de niveau gardent-elles la vue courante ? **Q2** "Les jeux" ramene-t-il a la vue par defaut, ou la vue survit-elle a la partie ? **Q3** Que montre `/` sans niveau (section 4.4) ? Le brief annonce 8 a 9 matieres par niveau et CE1 en compte 2 (section 1, point 4).

## 8. Fichiers et commandes

`CHROME` designe un Chrome ou Chromium ; racine du depot : `<racine>` ; `<profil>` un dossier de profil jetable.

- **Maquettes** : 48 fichiers `maquette-*.html` produits par `python -I squad/conformite/releves-accueil/generer-maquettes.py`, depuis `catalogue.yaml` et les descripteurs de paquets. Ils s'ouvrent sans le jar. `maquette-actuelle-<niveau>` (liste actuelle), `maquette-v1-etage1-<niveau>`, `maquette-v1-etage2-<niveau>-<matiere>`, `maquette-v2-<niveau>` (niveau = `CP`, `CE1`, `CM1` ou `tous`), plus `-gris`, `-neutre`, `-libelles-longs`, `maquette-bascule.html`, `maquette-pictos.html`. `accueil-vues.css` : les regles candidates. `pictos/` : 11 SVG.
- **Captures** (17 `capture-*.png`, 744 x 551 sauf mention), produites par `bash outils/mesurer-rendu.sh <largeur> "file:///<racine>/squad/conformite/releves-accueil/<maquette>.html" <png> <hauteur>` ; la liste actuelle sur le jar par `bash outils/mesurer-rendu.sh 744 http://localhost:<port>/niveaux/CP <png> 551`. `capture-v2-CP-defile-692-744x551.png` (bandeau Maths colle) : `node capture-colle.mjs <CHROME> <profil> maquette-v2-CP.html 692 <png>`.
- **Mesures** : `node pilote-accueil.mjs <CHROME> <profil> <racine>/squad/conformite/releves-accueil http://localhost:<port> mesures-accueil.json`, puis `python -I -X utf8 synthese-mesures.py mesures-accueil.json`. Le jar est celui de `bash outils/mvn-jdk21.sh -B -q -DskipTests package`, lance par `java -jar bootstrap/target/recreo.jar --server.port=<port>` sur un JDK 21 et arrete par son PID. `contraste-teintes.py` : section 3.1.
- Arbre de depart : `8ac33440`, 2026-10-11.

## Impact

- Ce document et le contenu de `squad/conformite/releves-accueil/` : crees.
- Aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/` n'est modifie.

## 9. Reprise du 2026-10-11, apres `ARBITRAGE-PREALABLE-accueil-par-matiere.md` (`APM-2`, `APM-6`)

Proposition, pas un Done : `nadia-prof` rejuge a l'ecran. Branche `ines-accueil-v2`, base `round-two`. Rien sous `domain/`, `application/`, `adapters/`, `bootstrap/`, aucun gabarit, `recreo.css` intact.

### 9.1 Pictogramme de Maths (`APM-2`)

`pictos/mathematiques.svg` : une ardoise blanche a contour d'encre portant `+` et `=`, sans chiffre, meme gabarit que les dix autres (48 x 48, trait de 3, bouts ronds, encre `#1c2733`, interieur blanc).

| Rendu | Taille du fichier a l'ecran | Epaisseur du trait | `+` (bras a bras) | Jour clair entre les deux barres de `=` |
|---|---|---|---|---|
| Bandeau (2,25 rem) | 36 px | 2,25 px | 10,5 px | 3,8 px |
| Fichier a sa taille native | 48 px | 3,0 px | 14 px | 5,0 px |
| Tuile et planche (3,5 rem) | 56 px | 3,5 px | 16,3 px | 5,8 px |

Le `-` : essaye et abandonne. Une ardoise de trois signes `+ - =` (`+` de 12 unites, `-` de 6, `=` de 12) fait disparaitre le jour entre les signes (a 36 px, `+-=` se lit comme un trait continu). Une seconde composition, `+` sur `=` dans un disque, se lit `±`. Aucun des deux n'est livre ; la regle de `nadia-prof` ("le `-` en plus si le dessin reste lisible a 48 px") est donc tenue par l'absence : il ne reste pas lisible.

Lu sur rendu reel : `capture-pictos-744x760.png` (56 px), `capture-v1-etage1-CP-390x551.png` et `capture-v1-etage1-CM1-744x551.png` (tuile), `capture-v2-CP-defile-692-744x551.png` (bandeau de 36 px). Le mot "Maths" est a cote dans les quatre cas (`APM-3`). Ce que l'enfant de CP y reconnait n'est pas mesure.

### 9.2 Teinte de l'emplacement 10 (`APM-6`)

Fond `#f0baf3`, contour `#85188b` (un magenta ; l'ancien gris `#d8dde4` / `#4a5a6b` est retire).

- Planchers du depot (`contraste-teintes.py`) : encre sur fond 9,39:1 (plancher 4,5), contour sur page 7,82:1 (plancher 3), contour sur fond 5,21:1. Fond contre page 1,50:1 (contre 1,17 a 1,33 pour les dix autres) : le contour delimite toujours la tuile.
- Ecarts CIEDE2000 (`ecarts-teintes.py`, sortie dans `ecarts-teintes.txt`), le plus petit de fond-fond et contour-contour. Contre les dix autres emplacements, en vue normale : 7 (rose) 10,6 ; 8 (violet clair) 10,8 ; 9 (indigo) 15,4 ; les sept autres entre 21,1 et 46,8. La paire la plus proche des onze emplacements reste 6-11 (5,9), qui ne concerne pas l'emplacement 10.
- Contre les couleurs de sens, en vue normale : bloc adulte 11,2 ; explication 20,3 ; indice 29,4 ; faux 34,0 ; accent 35,6 ; en-tete 29,2 ; juste 67,8.
- Non gris : saturation elevee, aucune des deux couleurs n'est sur l'axe neutre.

### 9.3 Reserves de la reprise

1. **Le magenta est la teinte la plus vive des onze** et le fond la plus chargee (1,50:1 contre la page). En CM1 il est voisin du rose de "Vivre ensemble" (`capture-v1-etage1-CM1-744x551.png`, ligne du bas), a 10,6 d'ecart : distinct a l'oeil sur la capture, a rejuger en jouant. Repli mesure, non applique : periwinkle `#adadeb` / contour `#191976`, ecart de 11,9 au plus proche en vue normale.
2. **Daltonisme, simulation seulement** (matrices de Machado 2009 recopiees dans `ecarts-teintes.py`, jamais comparees a une source dans cette session) : sous protanopie et deuteranopie le magenta tombe a 2,4-2,5 du violet du bloc adulte, et a 6,8 / 4,4 du violet clair (emplacement 8). La couleur n'est qu'un doublon (`APM-3`) et les deux couleurs de sens ne partagent jamais un ecran (`APM-5`). Pour mesure, l'ensemble d'origine contient deja des paires sous 1 en simulation (8-9, 2-11, 3-6).
3. **Constat hors demande** : le contour de l'emplacement 1 (`#2f6c9f`) est a 1,6 de l'accent du bouton (`#2e6f9e`), la tuile Francais et le bouton "Tout voir" portent presque le meme bleu. Non modifie.
4. **`generer-maquettes.py` ne tourne plus sur `round-two`** : `assert len(catalogue) == 147` echoue, le catalogue en compte 146. Les 48 maquettes HTML ne sont donc pas regenerees. Elles referencent les pictogrammes et `accueil-vues.css` par chemin, donc les captures refletent bien les deux changements. L'ordre des matieres de `N5` (Anglais au rang 7) n'est pas applique aux maquettes : elles gardent mon ordre initial (Anglais au rang 3).
5. **Mesures non rejouees** : `pilote-accueil.mjs` et `mesures-accueil.json` (138 mesures de boites) ne sont pas relances, aucune boite ne change (un fichier SVG de meme viewBox, deux couleurs). Les captures reprises sont celles ou le pictogramme de Maths ou l'emplacement 10 est visible ; les autres sont inchangees a l'octet pres.
