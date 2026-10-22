# s57 - palier 4 : releves d'ecran de LP1, LP2 et LP4 au pli 744 x 551

- **Auteure** : `ines-design`. **Date** : 2026-10-22, 11:49 (UTC+02:00), lue par `date`.
- **Ce que ce fichier est** : des cotes en pixels sur le rendu reel des trois lots, et des propositions. Il ne modifie aucun gabarit ni aucune feuille, ne prononce aucun Done, ne dit pas si une reserve est recevable : le veto pedagogique est a `nadia-prof`.
- **Codes** : `MP4-1` a `MP4-8` sont provisoires, nommes dans ce texte, non alloues au registre. `theo-archi` les alloue ou en retient d'autres.

## Resume

| Code | Constat a 744 x 551 | Section |
|---|---|---|
| MP4-1 | **LP4** `ce1-emc-regle-sanction` : apres une erreur, le bas de `.indice` depasse 551 pour **8 items sur 24** dans au moins un ordre des propositions (13, 14, 15, 18, 20, 21, 22 dans les six ordres, 24 dans 2 ordres sur 6). Cote : **580**, soit 29 px sous le pli. Le gabarit de reference (`EMC-CE1-01`, meme jar) : 0 item sur 24, maximum 540 | 3 |
| MP4-2 | **LP4** : le signal "Bravo !" de la manche qui suit une bonne reponse finit a 564 (13 px sous le pli) pour 9 items. Les boutons de reponse finissent a 476 au plus | 3 |
| MP4-3 | **LP1** `ce1-mots-invariables` : apres une erreur, 0 item sur 24 depasse (bas de l'indice 462 ou 510). **A la revelation, 21 items sur 24 depassent** (indice a 564 au plus, soit 13 px). La bonne reponse (bas 507 au plus) et le bouton "Continuer" (bas 360 au plus) restent au-dessus du pli. Reference saisie : 546 au plus | 4 |
| MP4-4 | **LP2** `ce1-masses-contenances` : 0 item sur 21 depasse, dans les six ordres. Bas de l'indice 338 a 408. Propositions sur une seule rangee pour les 21 items | 5 |
| MP4-5 | Aucun debordement horizontal, de 360 a 1200 de large, dans les trois lots. Chemin de sortie : bas a 58 dans tous les etats a 720 et plus | 6 |
| MP4-6 | Contrastes et tailles de police identiques aux deux gabarits de reference, aucun ecart. Plus petit bouton : 50 px de haut. Espaces insecables : 0 espace ordinaire devant `? ! : ; »` ni apres `«` dans les trois paquets | 7, 8 |
| MP4-7 | **LP1**, apres une erreur : le champ de saisie descend de **34 px** a la premiere erreur (la liste des tentatives s'insere au-dessus). Meme valeur sur le gabarit de reference : ce n'est pas un ecart du lot | 4 |
| MP4-8 | La tuile du jeu est la derniere de sa matiere : **sous le pli** pour LP1 (haut a 972) et LP2 (haut a 798), au-dessus pour LP4 (haut a 224) | 9 |

## 1. Arbres, jars, methode

- **Arbre de ce fichier** : branche `s57-ines-p4`, creee a `eb267ba7`. Aucun fichier de `domain`, `application`, `adapters/*/src/main` ni `bootstrap` n'est touche.
- **Les trois lots ne changent aucun gabarit ni aucune feuille.** `git diff --stat eb267ba7 <sommet> -- adapters/adapter-web/src/main` rend 0 ligne pour les trois sommets. Les trois jars servent donc le meme `recreo.css` (SHA-256 `1fa775cadc55f9b7a2252312340aae077c36218b019cdbf9ad2ebc11dc3654b5`, egal a celui du worktree) et les memes gabarits.
- **Jars reutilises en lecture seule** (`bootstrap/target/recreo.jar` du worktree de chaque lot, plus recent que le dernier fichier de `src/main` modifie). Controle de correspondance : le YAML du paquet, `catalogue.yaml` et `recreo.css` lus **dans** chaque jar sont egaux, octet pour octet (SHA-256), a ceux du worktree du lot.

| Lot | Branche | Sommet | SHA-256 du jar |
|---|---|---|---|
| LP1 | `s57-marc-fra-ce1-21` | `56e9fd1b` | `3a460c55b9f653a447dbbd9ecc65170c8722ee8742ef203851db90263582635b` |
| LP2 | `s57-lea-mat-ce1-10` | `fc6ee0a1` | `f6c0fe0a3a28fd1ac4043037ab4a424206dfbe58a3c42bfa39a112659d8982f0` |
| LP4 | `s57-sofia-emc-ce1-02` | `9f3fdd4b` | `7042a1527c05486ea06150731a793e19f3169f9482b79f7357e3a3df91f0c96a` |

- **Lancement** : JDK 21.0.10, un jar a la fois sur chacun de deux ports, PID lu dans le journal du jar, arret par PID (le port ne repond plus ensuite). Un seul Chrome 155 sans tete par le protocole devtools, profil jetable, Node 24. Aucune regle de style injectee.
- **Pli** : 744 x 551 (`mobile: false`, facteur 1). Cote = `getBoundingClientRect().bottom + scrollY`. Critere repris du palier 3 : bas de `.indice` au plus a 551.
- **Parcours** : pour chaque paquet, des parties de 10 manches jusqu'a couvrir tous les items par la mauvaise reponse. Deux parties sur trois par la mauvaise reponse, une sur trois par la bonne. Mauvaise reponse : une premiere erreur (`echec1`), une seconde (`echec2`), puis la revelation (saisie : trois erreurs puis "Continuer") ou la bonne reponse (choix). A chaque etat, sur le meme DOM : cotes, tailles de police, contrastes (WCAG, fond reconstruit par composition des fonds parents), nombre de lignes, rangees de propositions, focus.
- **Largeurs** : a `echec1`, sur le meme DOM, 360, 600, 720, 744, 768, 992, 1200 ; a 720 et 744 avec la barre de defilement classique et avec `scrollbar-width: none`.
- **Six ordres** (LP2, LP4, reference) : a `echec1`, les trois boutons de reponse sont permutes dans leurs six ordres sur le meme DOM, a 744. Le serveur tire l'ordre au hasard et le repli en rangees en depend.
- **Couverture** : LP1 24 items sur 24 (10 parties), LP2 21 sur 21 (9), LP4 24 sur 24 (9). Items non retrouves par leur enonce : 0.
- **Captures** : prises a 744 x 551 (accueil des jeux, une manche, apres une mauvaise reponse, deuxieme erreur, revelation, apres une bonne reponse, fin de partie, ecran d'erreur). Elles restent hors depot.

### Gabarit de reference

Le gabarit de reference est mesure **sur le meme jar et dans la meme session**, avec le meme pilote, pas repris du palier 3 :

- choix : `bandit-manchot-biens-communs-ce1` (`EMC-CE1-01`, 24 items, six ordres) ;
- saisie : `saisie-complements-10-cp` (11 items, un seul gabarit de saisie deja servi).

**Les cotes du palier 3 ne sont pas comparables.** `mesure-ines-palier-3-pli-744x551.md` donne 16 items sur 24 sous le pli pour `EMC-CE1-01`. Depuis, `5302af53` (correctif du pli : en-tete a 0,5 rem, interligne 1,25 sur l'enonce et les propositions) est dans `eb267ba7`. Le meme paquet rend maintenant 0 item sur 24 sous le pli, bas de l'indice 404 a 540, six ordres compris.

## 2. Ce qui est identique dans les trois lots (le gabarit)

Cotes a 744, identiques pour LP1, LP2, LP4 et pour les deux references :

| Element | Taille de police | Dimensions | Contraste |
|---|---|---|---|
| Enonce | 32 px | interligne 40 px en choix, 48 px en saisie | 14,09 |
| Consigne | 20 px | - | 14,09 |
| Bouton de reponse (choix) | 32 px | 58 px de haut, 154 a 680 de large | 9,75 a 10,03 (trois fonds) |
| Champ de saisie | 32 px | 68 x 384 | 15,14 |
| "Je reponds" / "Continuer" | 20 px | 56 x 144 / 56 x 135 | 5,41 |
| Verdict faux / juste | 20 px | icone 48 x 48 | 13,24 / 13,46 |
| Indice | 20 px | 1 ligne = 30 px (hauteur de la boite 50, deux lignes 80) | 13,93 |
| Bonne reponse revelee (saisie) | 32 px | 43 de haut | 14,09 |
| Sortie (`a.lien-accueil`) / niveau (`a.chip-niveau`) | 20 px | 109 x 50 / 66 x 50, bas a 58 | 9,25 |

- Pas d'une rangee de propositions : 66 px (58 de bouton, 8 d'ecart). Une ligne d'enonce : 40 px en choix, 48 px en saisie. Une ligne d'indice : 30 px.
- Ce que l'enfant lit en premier (enonce 32, propositions 32, champ 32, bonne reponse 32) est plus grand que les boutons secondaires (20) : aucun renversement.
- Signal juste/faux : second canal (icone 48 x 48, coche ou croix, plus texte) present dans tous les etats ; la couleur ne porte pas seule le sens.

## 3. LP4 `ce1-emc-regle-sanction` (choix, 24 items)

### 3.1 Apres une erreur, 744 x 551, six ordres

| Items | Lignes enonce | Rangees | Lignes indice | Bas de `.indice` | Pli |
|---|---|---|---|---|---|
| 1, 3, 4, 7, 9, 10, 11, 12 | 3 | 1 | 2 | 448 | au-dessus |
| 2, 6 | 2 | 1 | 1 | 378 | au-dessus |
| 5 | 2 | 1 | 2 | 408 | au-dessus |
| 8 | 3 | 1 | 1 | 418 | au-dessus |
| 16 | 3 | 2 | 2 | 514 | au-dessus |
| 17 | 2 | 3 | 1 | 510 | au-dessus |
| 19 | 3 | 2 ou 3 | 1 | 484 a 550 | au-dessus (1 px de marge dans les ordres a 3 rangees) |
| 23 | 3 | 2 | 1 | 484 | au-dessus |
| **13, 14, 15, 18, 20, 21, 22** | 3 | 3 | 2 | **580** | **29 px sous le pli, six ordres** |
| **24** | 3 | 2 ou 3 | 2 | 514 ou **580** | **sous le pli dans 2 ordres sur 6** |

- Total : **8 items sur 24** sous le pli dans au moins un ordre, **7 dans les six**. Reference `EMC-CE1-01` : 0 sur 24, maximum 540.
- Les 7 items a 580 ont trois lignes d'enonce, trois rangees de propositions et un indice de deux lignes. Une seule de ces trois cotes en moins suffit : une rangee de moins (66 px) donne 514 ; une ligne d'enonce de moins (40 px) donne 540, soit 11 px de marge ; une ligne d'indice de moins (30 px) donne 550, soit **1 px de marge**.
- Les boutons de reponse finissent a **476 au plus** : aucun bouton de reponse ne passe sous le pli. Ce qui passe dessous est le verdict ("Ce n'est pas ca. Essaie encore !") et la fin de l'indice. Capture de l'item 15 : le haut du signal et de l'indice est visible (haut a 500), le texte du verdict est coupe a la deuxieme ligne.
- Le chemin de sortie finit a 58 dans tous les etats.

### 3.2 Apres une bonne reponse (manche suivante, signal "Bravo !")

Haut du signal : 328 a 500. Bas : 392 a **564**. 9 items sur 24 ont un signal qui finit sous le pli (13, 14, 15, 18, 19, 20, 21, 22, 24), de 13 px. Tous les autres : 392 a 524. Les propositions de ces manches finissent a 476 au plus.

### 3.3 Longueur des propositions

- 20 propositions distinctes servies en `echec1`, 8 a 33 caracteres, 2 a 6 mots, hauteur 58 px.
- Items 1 a 12 : trois noms de 8 a 11 caracteres (`la regle`, `la sanction`, `la faute`), une seule rangee, largeur 198 a 249.
- Items 13 a 24 : 22 a 33 caracteres. 17 propositions distinctes occupent une rangee entiere (largeur 680). C'est ce qui produit les trois rangees.
- Apres une erreur, la proposition essayee est barree (trait) et garde son fond ; le focus passe sur elle. Les trois boutons ne bougent pas : position identique avant et apres l'erreur (60 paires arrivee/erreur, ecart 0).

## 4. LP1 `ce1-mots-invariables` (saisie, 24 items)

### 4.1 Cotes

| Etat, 744 x 551 | Enonce (lignes ; bas) | Bas du champ | Bas de `.indice` | Pli |
|---|---|---|---|---|
| Arrivee | 1 a 2 ; 218 a 266 | 298 a 346 | - | au-dessus |
| Apres une erreur (`echec1`) | idem | 332 a 380 | 462 (items 1, 17, 22) ou 510 (les 21 autres) | au-dessus, 24 sur 24 |
| Deuxieme erreur (`echec2`) | idem | 332 a 380 | 462 ou 510 | au-dessus, 24 sur 24 |
| Revelation (trois erreurs) | idem | champ retire | **516 a 564** | **21 items sur 24 sous le pli** (5, 11, 18, 15, 3, 23, 4, 21, 24, 12, 6, 19, 8, 9, 10, 20, 16, 14, 7, 13, 2) |

- A la revelation, les elements que l'enfant doit lire ou presser sont au-dessus du pli dans les 24 items : bouton "Continuer" (bas 312 a 360), "Tu as ecrit" (20 px), bonne reponse (32 px, bas 459 a 507). Seul `.indice`, qui reste affiche sous la bonne reponse, passe jusqu'a 564.
- Reference saisie (`saisie-complements-10-cp`, enonce d'une ligne) : revelation 516 a 546, aucun item sous le pli. L'ecart de LP1 vient des enonces a deux lignes (21 items sur 24, une ligne de 48 px de plus que les items a une ligne) : le gabarit n'a pas change.
- Champ de saisie : 384 x 68, police 32 px, mot attendu le plus long `aujourd'hui` (11 caracteres) ; aucune troncature observee. Le champ n'a pas de texte fantome.

### 4.2 Comportement apres une mauvaise reponse

- Le champ garde la tentative refusee (`xx` relue dans le champ) et le focus reste dans le champ (`document.activeElement` = `champ-reponse`), alors que `data-focus` du fragment vaut `bouton`.
- **La premiere erreur decale le champ et le bouton de 34 px vers le bas** (la liste `.tentatives`, 30 px de haut, s'insere au-dessus). Aucun decalage a la deuxieme erreur. Meme valeur (34) sur le gabarit de reference : ce n'est pas un ecart du lot.
- Le verdict, la chaine barree, le champ et "Je reponds" tiennent tous au-dessus du pli dans les 24 items (bas de `.indice` 510 au plus).
- A la revelation, le champ et "Je reponds" disparaissent, un seul bouton reste ("Continuer", 135 x 56).
- Quand la page depasse 551, une barre de defilement apparait et la largeur utile passe de 744 a 729 : le contenu se decale de 7 px (visible sur la capture de la revelation). Ce comportement existe sur le gabarit de reference.

### 4.3 Fin de partie

Un seul bouton "Les jeux" (117 x 56, haut a 98, bas a 154). Les libelles de score ("Tu as trouve, tout seul :", "Tu as joue toutes les questions :") sont a **16 px**, leurs nombres a 20 px, "C'est fini !" a 24 px : meme gabarit que LP2 et LP4. Quand la derniere manche a ete revelee, la fin affiche la phrase et la bonne reponse (32 px) : la page fait 558 de haut (7 px au-dela du pli). LP2 et LP4 : 551.

## 5. LP2 `ce1-masses-contenances` (choix, 21 items)

- Apres une erreur, 744 x 551, six ordres : **0 item sur 21** sous le pli. Bas de `.indice` : 338 (items 10, 11), 368 (item 9), 378 (items 1 a 4, 13, 16), 408 (les 12 autres).
- Propositions : 4 a 13 caracteres, 1 a 2 mots, **une seule rangee dans les 21 items et les six ordres**, largeur 154 a 275, bas 264 a 304.
- Enonce : 1 ligne (items 9, 10, 11), 2 lignes (les 18 autres). Aucun enonce a trois lignes.
- Apres une bonne reponse : bas du signal 352 a 392.
- Reference `EMC-CE1-01` : 2 a 3 rangees, bas de l'indice 404 a 540. LP2 finit au plus a 408, la reference a 540.
- Position des boutons avant et apres l'erreur : identique (60 paires, ecart 0).

## 6. Largeurs et debordement horizontal (apres une erreur, tous les items)

Nombre d'items dont le bas de `.indice` depasse 551, dans l'ordre servi a la mesure de largeur (LP4 : l'ordre servi a l'item 24 est un des deux ordres a trois rangees, d'ou 8 et non 7) :

| Largeur, mode | LP1 (24) | LP2 (21) | LP4 (24) | Ref. choix (24) | Ref. saisie (11) | Debordement horizontal |
|---|---|---|---|---|---|---|
| 360, barre | 24 | 21 | 24 | 24 | 11 | 0 |
| 600, barre | 24 | 21 | 24 | 24 | 11 | 0 |
| 720, barre | 0 | 0 | 8 | 0 | 0 | 0 |
| 720, sans barre | 0 | 0 | 8 | 0 | 0 | 0 |
| 744, barre et sans barre | 0 | 0 | 8 | 0 | 0 | 0 |
| 768, barre | 0 | 0 | 8 | 0 | 0 | 0 |
| 992 et 1200 | 0 | 0 | 0 | 0 | 0 | 0 |

- A 360 et 600 tous les items passent sous le pli, **gabarits de reference compris** : ce ne sont pas des largeurs du perimetre de mesure, je les donne pour la limite basse. A 360 l'en-tete passe sur deux rangees (bas de la sortie a 124 au lieu de 58).
- `scrollWidth` egal a `clientWidth` dans tous les etats, aux sept largeurs, deux modes : aucun element ne depasse horizontalement la fenetre.
- LP4, 720 avec barre : la largeur utile tombe a 705 (barre de defilement) sans changer la liste des items sous le pli.
- A 992 et 1200 : bas de l'indice 378 a 444 (LP4), 338 a 378 (LP2), 462 a 510 (LP1).

## 7. Contraste et couleur

Tous les contrastes de la section 2 sont au-dessus de 4,5 (plus bas : 5,41, bouton plein blanc sur `rgb(46,111,158)`). Le signal faux est une croix rouge dans une boite rose et le signal juste une coche dans une boite verte : le texte du verdict et l'icone portent le sens, la couleur est un troisieme canal. La proposition deja essayee est marquee par un trait, pas par une couleur.

Limite : le contraste est calcule sur les fonds unis composes ; le fond sombre du cabinet du choix (degrade et etoiles) n'est pas un fond de texte, les boutons de reponse ont leur propre fond.

## 8. Espaces insecables

- **Rendu** : sur tous les enonces et indices servis, 0 espace ordinaire devant `? ! : ; »` et 0 espace ordinaire apres `«`.
- **Source** : lecture statique de toutes les valeurs de chaque YAML (enonce, bonne reponse, distracteurs, indice, forme de reference). Espace ordinaire a corriger : **0** dans les trois paquets. Espaces insecables ecrits : 42 (LP1), 23 (LP2), 46 (LP4).
- LP1 : `aujourd'hui` porte une apostrophe droite en forme de reference et une apostrophe typographique en forme acceptee (voir le commentaire du paquet) ; les 20 autres apostrophes du paquet sont droites.
- Non mesure : un signe de ponctuation seul en debut de ligne apres un retour a la ligne, ligne par ligne.

## 9. Ecrans hors manche

- **Ecran d'erreur** (jeu inexistant) : "Oups ! Le jeu a un souci." et un seul bouton "Recommencer" (175 x 56, 20 px), 744 de large, sans debordement. Identique dans les trois jars.
- **Page des jeux de la matiere** (744 x 551) : la tuile du jeu est la derniere de sa matiere, 342 x 56, 20 px, titre sur une ligne.
  - LP1 (Francais, 23 tuiles) : haut a **972**, sous le pli.
  - LP2 (Maths, 18 tuiles) : haut a **798**, sous le pli.
  - LP4 (Vivre ensemble, 2 tuiles) : haut a 224, au-dessus.
  - Toutes les tuiles ont 56 px de haut, sauf une tuile existante de Maths a 86 (titre sur deux lignes, "Memoriser ses tables de multiplication"). Aucun des trois titres de lot ne passe sur deux lignes.

## 10. Ce qui sort du gabarit de reference

1. **LP4** : 8 items sous le pli apres une erreur, la reference 0. Cause mesuree : enonces a trois lignes (20 items sur 24, la reference 1 a 2) et trois rangees de propositions (items 13 a 24, la reference 2 a 3 mais avec un enonce plus court). `MP4-1`, `MP4-2`.
2. **LP1** : revelation sous le pli pour 21 items, la reference 0. Cause mesuree : enonces a deux lignes. `MP4-3`.
3. **LP2** : rien ne sort du gabarit.
4. Tailles, contrastes, boutons, signal, debordement, insecables : aucun ecart dans les trois lots.

## 11. Propositions

Je propose, je ne tranche pas. Les trois premieres portent sur du contenu : elles reviennent a `nadia-prof` (veto) et a `sofia-sm` (affectation).

- **PR-1 (LP4, MP4-1, MP4-2)** : amener les 8 items concernes sous la cote de 551 par une seule des trois leviers de contenu, chacun suffisant seul : un enonce de deux lignes au lieu de trois, un indice d'une ligne au lieu de deux, ou des propositions qui tiennent sur deux rangees au lieu de trois. Cote obtenue : 540 pour une ligne d'enonce (11 px de marge), 550 pour une ligne d'indice (1 px), 514 pour une rangee (37 px). Apres correction, la cote est a refaire item par item (la rangee depend de la longueur des trois propositions).
- **PR-2 (LP1, MP4-3)** : si `nadia-prof` juge que l'indice n'a pas a rester affiche a la revelation, ne plus l'afficher donne un bas maximal de 507 (la bonne reponse). Cela passe par `fragments/saisie.html` : je ne l'ai pas modifie, la question est pedagogique avant d'etre une question de forme. Si l'indice reste, l'ecart est de 13 px sur 21 items, avec la bonne reponse et le bouton au-dessus du pli.
- **PR-3 (LP1, MP4-7)** : le decalage de 34 px a la premiere erreur est celui du gabarit de saisie, pas du lot. Reserver la hauteur de la liste des tentatives des l'arrivee le supprimerait, au prix de 30 px de vide au-dessus du champ a la premiere manche. Hors perimetre du lot : a porter comme une story de gabarit si `camille-po` la retient.
- **PR-4 (MP4-8)** : l'ordre des tuiles dans la page de matiere releve de `camille-po`. Constat seulement.
- **Pas de proposition de feuille de style.** Un levier CSS existe (rembourrage de l'en-tete ou des boutons, interligne), mais chaque changement de ce type a deplace la hierarchie de saillance entre ce qui fait avancer l'enfant et ce qui le fait sortir du jeu, et c'est le motif des deux refus de `nadia-prof` ; je ne le propose pas pour gagner 29 px sur un seul paquet.

## 12. Limites

- Un navigateur (Chrome 155 sans tete), une police (celle du poste), pas d'appareil reel, pas de clavier virtuel : la hauteur utile reelle sur tablette est plus petite que 551 des qu'un clavier est ouvert, ce que ce fichier ne mesure pas.
- Les six ordres sont releves sur LP2, LP4 et la reference choix ; en saisie il n'y a pas d'ordre.
- LP4 a 720 et 768 : l'ordre est celui du service, les six ordres ne sont releves qu'a 744.
- Les tableaux des sections 3 a 5 viennent d'une partie par item en moyenne 3 fois : les cotes d'un item sont les memes dans tous les releves de cet item (hormis l'ordre des propositions pour LP4 item 24 et 19).
- Pas mesure : les neuf polices imposees, le focus clavier, le toucher, le zoom du navigateur, un enonce rendu avec une police de remplacement.
- Pas de saisie de formes acceptees (majuscules, accents, apostrophe typographique) : c'est du bareme, pas de la forme.

## 13. Isolation

Deux repertoires du scratchpad (`s57-p4-ines-design-t` et `s57-p4-ines-design-t2`), un jar a la fois par port 18543 et 18544, Chrome sur 19243 et 19244, profils jetables dans ces repertoires. Tout processus arrete par son PID ; aucun arret par nom d'image ou de Chrome. Rien dans `round-two` ni dans les worktrees des lots.

## Decisions prises

**Aucune.** Ce fichier ne cree ni n'amende aucune `ADR` ni `DEC`, ne leve aucune reserve et ne prononce aucun Done.

---

# Remesure du 2026-10-22 (apres les corrections des trois lots), 744 x 551

- **Auteure** : `ines-design`. **Date** : 2026-10-22, 13:23 (UTC+02:00), lue par `date`. Cette section complete les sections 1 a 13, qui restent la mesure des sommets `56e9fd1b`, `fc6ee0a1` et `9f3fdd4b`.
- **Ce que cette section est** : des cotes en pixels et des propositions. Aucun Done, aucun gabarit touche : le veto est a `nadia-prof`.

## R1. Sommets, jars, methode

| Lot | Branche | Sommet | Dernier commit de contenu | SHA-256 du jar |
|---|---|---|---|---|
| LP1 | `s57-marc-fra-ce1-21` | `24a9315b` | `ea34c750` | `76e52ea6bffd1ad5008ef23ed21d2a4915d7aa7763e14ffea6be810976815928` |
| LP2 | `s57-lea-mat-ce1-10` | `c29d1d26` | `a733e582` | `ee6c14db9cd464e703862427ffcd290796c4946df5feca81e029151ca6e90c81` |
| LP4 | `s57-sofia-emc-ce1-02` | `1152fdc9` | `1152fdc9` | `78c08cd952af21b162d5e8cf5624f331cc03f2d1b295763f253e207ebb4edf6e` |

- Chaque jar est posterieur a son dernier commit de contenu, et le YAML du paquet, `catalogue.yaml` et `recreo.css` lus dans le jar sont egaux a ceux du worktree (SHA-256). `git diff --stat eb267ba7 <sommet> -- adapters/adapter-web/src/main` : 0 ligne pour les trois. Meme feuille, memes gabarits : seul le contenu a change.
- Methode de la section 1, inchangee : 744 x 551, mauvaise reponse puis deuxieme erreur puis revelation, balayage de 360 a 1200 avec et sans barre, six ordres pour LP2 et LP4. Couverture : LP1 24 items sur 24, LP2 21 sur 21, LP4 24 sur 24 (13 parties).

## R2. LP4 `ce1-emc-regle-sanction`, sommet `1152fdc9` : 0 item sur 24 sous le pli

Apres une erreur, 744 x 551, **six ordres des propositions** : **0 item sur 24 sous le pli**, dans aucun ordre. Bas de `.indice` maximal : **484** (67 px de marge). Avant : 8 items sur 24, 580.

| Items | Lignes enonce | Rangees | Bas de `.indice` |
|---|---|---|---|
| 1, 4, 5, 7, 10, 11, 12 | 3 | 1 | 448 |
| 6 | 3 | 1 | 418 |
| 3, 8, 9 | 2 | 1 | 408 |
| 2 | 2 | 1 | 378 |
| 13 a 16, 18 a 24 (11 items) | 3 | **2** | 484 |
| 17 | 2 | 2 | 444 |

- **Ce qui a fait baisser la cote** : les items 13 a 24 tiennent sur **deux rangees** (avant : trois pour 7 items), avec des propositions de 20 a 22 caracteres (avant : 22 a 33) et un indice d'une ligne. Les propositions de la tache 1 (items 1 a 12) font 11 caracteres au plus.
- **Seuils de sofia** : propositions de la tache 2 a 58 caracteres au plus, indice a 46 au plus. Mesure : propositions de la tache 2 **22 caracteres au plus**, indice de la tache 2 **44 au plus** (32 a 44). Les deux seuils sont respectes avec une grande marge. Je n'ai pas mesure un item a 58 caracteres : ce seuil n'est ni confirme ni infirme par ce releve. Le nombre de rangees depend de la largeur de chaque bouton, pas du nombre de caracteres seul.
- **Enonces** : trois lignes pour **19 items sur 24** ; les items 2, 3, 8, 9 et 17 ont deux lignes. La consigne recue parle de 20 : je mesure 19. Avec des propositions sur deux rangees, trois lignes d'enonce ne font plus passer l'indice sous le pli.
- Aux largeurs 720, 744 et 768 (avec et sans barre), 992 et 1200 : 0 item sous le pli. A 360 et 600, tous les items sont sous le pli, la reference aussi (section 6).
- **Apres une bonne reponse** (signal "Bravo !" de la manche suivante) : bas du signal **498 au plus**, 0 item sous le pli (avant : 9 items, 564).
- Propositions : 58 px de haut, 198 a 680 de large, 8 a 22 caracteres, 2 a 5 mots, 1 a 2 rangees. Tailles, contrastes, boutons, sortie a 58 : identiques a la section 2. Aucun debordement horizontal.

## R3. LP1 `ce1-mots-invariables`, sommet `24a9315b` : 18 items sous le pli a la revelation

La revelation affiche l'indice (decision de l'utilisatrice). Mesure sur les 24 items, 744 x 551, trois erreurs puis revelation :

- **18 items sur 24 ont le bas de `.indice` a 564, soit 13 px sous le pli** : 2, 4, 5, 6, 8, 9, 10, 11, 12, 13, 14, 16, 18, 19, 20, 21, 23, 24. Ce sont exactement les 18 items dont l'enonce tient sur deux lignes (bas de l'enonce 266). Les 6 autres (1, 3, 7, 15, 17, 22) ont un enonce d'une ligne et un indice a 516, au-dessus du pli.
- **Le releve de marc-dev (18 items, 13 px) est le bon pour le jeu d'enonces actuel.** Mes 21 items de la section 4 etaient justes pour l'ancien jeu : l'ancien jeu avait 3 enonces d'une ligne sur 24, le jeu actuel en a 6. Je compare les comptes, pas les enonces un a un.
- Au-dessus du pli, dans les 24 items : "Continuer" (bas 312 a 360), "Tu as ecrit" et la bonne reponse (32 px, bas 459 a 507).
- Apres une ou deux erreurs : bas de l'indice 462 ou 510, 0 item sous le pli. Le champ descend de 34 px a la premiere erreur (inchange).
- Largeurs 720 a 1200, avec et sans barre : 0 item sous le pli apres une erreur. Aucun debordement horizontal.

## R4. LP2 `ce1-masses-contenances`, sommet `c29d1d26` : le contenu n'a pas deplace le pli

Cotes identiques, item par item, a celles de la section 5, y compris pour les comparaisons 14, 15, 16, 18 et 19 : une seule rangee de propositions, bas de `.indice` 408, 408, 378, 408 et 408. Six ordres : 0 item sur 21 sous le pli, maximum 408. Apres une bonne reponse : bas du signal 392 au plus. Aucun debordement horizontal.

## R5. Propositions

Je propose, je ne tranche pas.

- **LP4** : rien a corriger au pli. Marge de 67 px.
- **LP1** : l'indice reste affiche, 13 px sous le pli pour 18 items, la bonne reponse et le bouton restant visibles. Deux voies : (a) laisser tel quel ; (b) raccourcir les enonces a deux lignes pour qu'ils tiennent sur une, ce qui ramene l'indice a 516 et releve de `nadia-prof` et de `marc-dev`. Je ne propose pas de reduire l'interligne de 48 px de l'enonce en saisie ni un rembourrage : la hierarchie de saillance en depend.
- **LP2** : rien.

## R6. Limites et isolation

- Meme navigateur, meme police et memes limites que la section 12.
- Memes deux repertoires du scratchpad (`s57-p4-ines-design-t`, `s57-p4-ines-design-t2`), un jar par port (18543 pour LP1 puis LP4, 18544 pour LP2), arret par PID, aucun arret par nom d'image.

**Decisions prises : aucune.**
