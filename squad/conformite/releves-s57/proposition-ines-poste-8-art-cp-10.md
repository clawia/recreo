# Proposition de forme - poste 8 de s57, reprise d'ART-CP-10 : d'ou viennent 125 px a 744 et 17 px a 992

- Auteure : `ines-design`.
- Date : 2026-10-14, 19:21 (UTC+02:00), lu par `date`.
- Branche : `s57-ines-125`, issue de `round-two` a `6ca84317`, worktree dedie.
- Demande : `lecture-nadia-palier-1.md` (`D5`, `D6`, `D7`, section 1.4 et section 4.2) ; `avis-faisabilite.md` de `theo-archi` (section 2, `C1` a `C4`) ;
  `FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md` (verdict `REFUSE` du 2026-10-11, `RDO-CP-R1`).
- Fichiers de ce dossier : ce document, `candidat-poste-8.css` (les regles candidates, texte exact injecte pour la mesure), `pilote-poste-8.mjs`
  (le pilote de mesure) et `agreger-poste-8.mjs` (le calcul des cotes les plus basses).

## 0. Resume

**Les 125 px viennent de la pile verticale, pas d'un seul element** : 48 px de decoration du meuble (bandeau 32, socle 16), 34 px de rang du score,
64 px de bloc de message empile sous le meuble, plus 28 px de cadre du meuble qui ne sont pas de la decoration. `theo-archi` a raison : le retrait de
la decoration seule ne suffit pas (48 px sur le jar, 86 px au plus en retirant aussi le cadre).

**Proposition P, trois leviers, 146 px mesures sur le jar a 744 x 551** :

| Levier | Regle | Gain a 744 | Gain a 992 sur la croix |
|---|---|---|---|
| L1 | `display: none` sur `.cabinet-bandeau` et `.cabinet-socle`, sous `.ecran-deux-oeuvres` | 48 | 32 |
| L2 | le score passe a droite de la consigne (grille a trois colonnes) | 34 | 34 |
| L3 | le bloc de message en deux colonnes : le signal a gauche, le texte a droite | 64 | 0 |
| P | L1 + L2 + L3 | **146** | **66** |

| Barre | Cote avant | Cote avec P | Marge sous 551 |
|---|---|---|---|
| Refus : bas de `.reponse-revelee` a la revelation, 744 | 656 | **510** | 41 |
| `RDO-CP-R1` : bas de `.indice` a l'echec, 744 | 676 | **530** | 21 |
| `RDO-CP-R1` : bas de la croix de chaque oeuvre essayee, 992 | 568 | **502** | 49 |

Mesure sur les six items, par `getBoundingClientRect()`, sur un jar construit avec ces regles dans `recreo.css`. Rien n'est verse dans le depot : le gabarit et la
feuille sont intacts sur cette branche.

**Ce que P ne tient pas** : a 992, le verdict (bas a 567) et l'indice (bas a 574) restent coupes par le pli, et a 744 l'explication de la revelation (514 a 594)
est coupee par le pli. La cible de `D5` a 992 est la croix seule. **`L3` est le seul levier qui donne de la marge**, et c'est celui qui deplace le plus le dessin
(section 5) : `nadia-prof` le juge d'abord.

## 1. Ce que ce document est, et ce qu'il n'est pas

Une proposition. **Aucun gabarit, aucune feuille de style, aucun controleur n'est modifie** dans le depot. Elle ne prononce aucun Done pedagogique : `nadia-prof`
a le veto, `lea-dev` implemente, `theo-archi` lit `C1` a `C4` sur le diff. Aucun identifiant n'est alloue : `L1` a `L3` et `P` sont des reperes de ce fichier.

## 2. Methode et perimetre

- Jar construit depuis un worktree jetable (`--detach` sur `6ca84317`, supprime apres la mesure) qui ajoute deux choses **non commitees** : l'entree de catalogue du
  paquet (celle de `f37e33dd`, absente de `round-two` depuis `13d00bfe`, equivalent de la branche B2 de `avis-faisabilite.md` 3.2) et les regles de `candidat-poste-8.css`
  dans `recreo.css`. Commande de construction : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package -DskipTests` (avec `-am`, mesure partielle, sans test). JDK 21.0.10.
- Jar lance sur un port dedie, arrete par son PID apres lecture de sa ligne de commande. Journal de demarrage lu : `Tomcat started on port <port>` avant toute mesure.
- Chrome sans tete, profil jetable, pilote par son port de deverminage (`pilote-poste-8.mjs`), fenetre fixee par `Emulation.setDeviceMetricsOverride` a 551 de haut,
  echelle 1, `mobile: false`. Chaque appui est un `click()`, la lecture attend la fin de l'echange. Cotes en px, origine en haut de la page, pli a 551.
- **Avant de mesurer P, la base a ete rejouee** : a 744 et 992 elle redonne les cotes de `Q7-releve-gabarit-livre.md` et de `nadia-prof` (indice 676 et 720,
  croix 524 et 568, `M4` 656 et 700), sur les six items.
- Les leviers ont d'abord ete mesures un a un, par injection du CSS apres la feuille reelle sur le jar de base. P a ensuite ete mesure **deux fois** : par injection,
  puis sur un second jar qui porte les regles dans `recreo.css`. Les deux rendent les memes cotes au pixel a 744 et 992.

## 3. D'ou viennent les 125 px

Pile verticale du gabarit livre, a 744, apres une erreur (cotes mesurees sur le jar, six items identiques sauf la hauteur de la reference) :

| De y a y | Element | Hauteur | Regle qui la fixe | Decoration |
|---|---|---|---|---|
| 0 a 114 | en-tete et rembourrage de `.zone-jeu` | 114 | hors `.ecran-deux-oeuvres` | non |
| 114 a 152 | `.score` et ses marges | 38 | `.score` : 1,25 rem, marges 0,25 rem | non |
| 152 a 186 | `.consigne` et sa marge | 34 | `.consigne` : 1,25 rem | non |
| 186 a 286 | `.enonce`, deux lignes, et sa marge | 100 | `.enonce` : 2 rem | non |
| 286 a 294 | bordure haute de `.cabinet` | 8 | `.cabinet` : bordure de 0,5 rem | cadre |
| 294 a 326 | `.cabinet-bandeau` | 32 | `.cabinet-bandeau` : 2 rem | **oui** |
| 326 a 338 | rembourrage haut de la rangee | 12 | `.propositions-oeuvres` : 0,75 rem | cadre |
| 338 a 482 | les trois cases | 144 | `.proposition` : carre, largeur par la grille | non |
| 482 a 534 | marque d'echec (484 a 524) et rembourrage bas | 52 | `.propositions-oeuvres:has(.deja-essayee)` : 3,25 rem | non |
| 534 a 550 | `.cabinet-socle` | 16 | `.cabinet-socle` : 1 rem | **oui** |
| 550 a 558 | bordure basse de `.cabinet` | 8 | `.cabinet` | cadre |
| 558 a 622 | `.signal` | 64 | icone de 3 rem plus rembourrage de 0,5 rem | non |
| 622 a 626 | marge haute de `.indice` | 4 | `.indice` : 0,25 rem | non |
| 626 a 676 | `.indice` | 50 | 1,25 rem, une ligne | non |

Bas de l'indice : 676. Pli : 551. **Ecart : 125.** A la revelation, `.reponse-revelee` prend la place de l'indice (626 a 656) : 105.

Dans ces 676 px, le meuble pese 48 px de decoration et 28 px de cadre (bordures 8 + 8, rembourrage haut 12). Les 86 px de `theo-archi` sont ces 76 px plus 10 px sous la croix
(524 a 534) ; ils supposent de retirer le cadre, donc `.cabinet` lui-meme (`C3`), ce que je ne propose pas.

**A 992** : memes lignes, cases de 188. Les cases vont de 338 a 526, la marque de 528 a 568. La croix depasse le pli de 17 px ; le bandeau seul (32 px) suffit a la
ramener a 536.

## 4. Les leviers, un par un

### 4.1 L1 - la decoration du meuble (48 px)

`.cabinet-bandeau` (le visage et le fronton) et `.cabinet-socle` (les deux etoiles) sont `aria-hidden`, sans texte, freres de `.propositions` (`manche.html`). Les masquer
retire 32 px au-dessus des cases et 16 en dessous. **`.cabinet` reste** : sa bordure de 0,5 rem, son fond, son rembourrage et le levier de flanc (`position: absolute`, zero
pixel de hauteur) ne bougent pas. Le meuble reste un objet ferme sur ses quatre cotes (`ADR-0030`, `C1`). Hors de tout `@media` : le meuble est le meme a toute largeur, y
compris a 390 (la regle y gagne aussi 48 px, non demandes, sans effet sur ce qui est exige).

### 4.2 L2 - le rang du score (34 px)

Le score prend aujourd'hui un rang entier au-dessus de la consigne. A 720 et plus, la consigne (575 px de texte) et le score (80 px) tiennent sur une ligne : 41 px
d'air entre eux a 720, la plus etroite des largeurs de la grille. La grille passe a trois colonnes (`11rem`, `minmax(0, 1fr)`, `auto`) ; l'ordre du document ne change pas.
Le meuble garde sa place en largeur (x = 216,5 a 712,5 a 744, comme avant) : les cases ne changent pas de taille (144, mesure). La reference reste a gauche, a la hauteur de l'enonce.

### 4.3 L3 - le bloc de message en deux colonnes (64 px)

Sous le meuble, le signal (64) puis l'indice (50) ou la phrase de `M4` (30) s'empilaient : 118 px a l'echec, 98 a la revelation. En deux colonnes (le signal sur 2/5 de la
largeur, le texte sur 3/5), le texte demarre a la hauteur du signal. **Le message reste sous le meuble, jamais au-dessus** : son haut est a 4 px sous la bordure basse du meuble.
Le bas de la phrase de `M4` passe de 656 a 510 : elle n'est plus a 105 px sous le pli mais a 41 px au-dessus. Meme position a 992 (haut du texte a 4 px sous le meuble : 524 pour un meuble qui finit a 520).

Le verdict de l'echec (*"Ce n'est pas ça. Essaie encore !"*) passe sur deux lignes dans la colonne de 274 px (60 px de texte) ; `.verdict` perd sa marge basse pour que la boite
du signal ne grandisse pas de 16 px de plus (76 px au lieu de 92).

### 4.4 Leviers examines et non retenus

| Levier | Gain | Pourquoi je ne le propose pas |
|---|---|---|
| Reduire les cases ou les reproductions | variable | `nadia-prof` conseille de prendre les pixels ailleurs (regle 6, `Q8`) : la femme au luth tient *"de justesse"* a 117 et 154 px. Le plafond de 176 px ne bouge pas |
| Reduire l'icone du signal (3 rem) ou `.enonce` (2 rem) | 16 a 48 | Le signal et le texte lus en premier restent au moins aussi grands que les elements secondaires (mandat). L'icone a ete doublee en s01 pour cette raison |
| Retirer la bordure et le rembourrage de `.cabinet` | 28 | `C3` et `ADR-0030` : c'est le cadre qui dit *"ici on appuie"* |
| Le message a gauche, sous la reference | 118 | `M1`, `M4` : le message est sous le meuble. A gauche il est a cote, sur 176 px de large |
| Interligne de `.enonce` a 1,25 (environ 19 px), rembourrage du signal a 0,25 rem (8 px) | 27 | **Calcul, non mesure.** Avec L1 et L2 : 82 + 27 = 109 pour 105 demandes, soit 4 px de marge, et 125 n'est pas atteint |

## 5. Regles candidates

`candidat-poste-8.css` contient le texte exact. Il se verse dans `recreo.css` ainsi : une regle hors de tout `@media` (L1), et dans le bloc
`@media (min-width: 720px)` deja ecrit pour `.ecran-deux-oeuvres` (celui qui pose la grille), le remplacement de `grid-template-columns` et de `grid-template-areas`
(L2) et quatre regles ajoutees (L3).

Lecture des conditions de `theo-archi` :

| Condition | Lecture de cette proposition |
|---|---|
| `C1` : aucune regle ne depend de l'etat | L1 et L2 ne nomment aucun etat. **L3 nomme `.message-jeu` et `.signal`**, que `C1` cite. Elle ne lit ni `.est-juste`, ni `.est-faux`, ni `data-oeuvre-revelee` : la place du signal et celle du texte sont les memes apres une bonne reponse, une erreur et une revelation (signal a x = 16,5, texte a x = 302,1, mesure). Le bloc n'existe qu'avec une reponse, par construction. **Si `theo-archi` lit `C1` a la lettre, L3 tombe, et le refus n'est plus leve par aucune regle mesuree ici** (L1 + L2 : 86 px mesures sur la variante du score a gauche, `M4` a 570) |
| `C2` : pas de `@media` sur l'image d'une proposition | Aucun `@media` neuf. L2 et L3 entrent dans le bloc existant, qui ne vise aucune image. Le plafond de 176 px et sa regle ne sont pas touches |
| `C3` : jamais `.cabinet` masque | Seuls `.cabinet-bandeau` et `.cabinet-socle` sont masques. `.cabinet`, `.cabinet-levier` et le formulaire restent |
| `C4` : les cotes se jugent sur le jar | Section 6 |

## 6. Mesures sur le jar

### 6.1 Resultat aux largeurs exigees

Six items, trois etats chacun (echec, deux echecs, revelation), plus la bonne reponse. Cotes les plus basses sur l'ensemble des items.

| Largeur | Cote | Avant | Avec P | Marge sous 551 |
|---|---|---|---|---|
| 744 | bas de `.reponse-revelee`, revelation | 656 | 510 | 41 |
| 744 | bas de `.indice`, echec | 676 | 530 | 21 |
| 744 | bas du verdict, echec | 597 | 544 | 7 |
| 744 | bas de la boite `.signal`, echec | 622 | 552 | -1 |
| 744 | bas de la croix de la case, echec | 524 | 458 | 93 |
| 992 | bas de la croix de la case, echec | 568 | 502 | 49 |
| 992 | bas de `.reponse-revelee`, revelation | 700 | 554 | -3 |
| 992 | bas de `.indice`, echec | 720 | 574 | -23 |

Aucun debordement horizontal, aux six largeurs, dans les quatre etats. **Les trois cases ne bougent ni en haut ni en largeur** entre la manche fraiche, l'echec, la revelation
et la bonne reponse (`N6`), sur les six items, a 744 et 992. Le haut des cases passe de 338 a 272 ; ni leur taille (144 et 188) ni celle des reproductions (plafond de 176) ne change.

### 6.2 Autres largeurs, avec P

| Largeur | Bas de `M4` | Bas de l'indice | Bas de la croix | Avant (`M4`, indice, croix) |
|---|---|---|---|---|
| 720 | 505 | 525 | 453 | 651, 671, 519 |
| 768 | 510 | 530 | 458 | 656, 676, 524 |
| 1200 | 506 | 526 | 454 | 652, 672, 520 |
| 390 | 913,7 | 961,7 | 767,7 | 961,7, 1009,7, 799,7 |

A 390 la disposition reste empilee (le bloc `@media` ne s'applique pas) : seul L1 agit, 48 px, et rien n'est exige a cette largeur.

### 6.3 P et ses sous-ensembles, a 744

| Combinaison | Gain | `M4` (barre 105) | Indice (barre 125) | Croix a 992 (barre 17) |
|---|---|---|---|---|
| L1 | 48 | 608, non | 628, non | 536, oui |
| L1 + L2, score a gauche de l'enonce (variante abandonnee) | 86 | 570, non | 590, non | 498, oui |
| L1 + L3 | 112 | 544, oui, 7 de marge | 564, non, 13 de moins | 536, oui |
| **L1 + L2 + L3 (P)** | **146** | **510, oui, 41** | **530, oui, 21** | **502, oui, 49** |

Toutes ces lignes sont des mesures sur le jar de base par injection, sauf P, mesure aussi sur le jar qui porte les regles. **L1 + L3 leve le refus avec 7 px de marge et ne leve pas
`RDO-CP-R1` a 744** : c'est la combinaison la plus legere qui tienne la barre de 105.

## 7. Forme et couleur

**Aucune couleur neuve, aucune forme neuve.** Inchanges, a la meme taille : la croix de 40 px sous la case (disque clair, anneau d'encre, croix `#c62828`), l'anneau d'encre de
la revelation, la coche et la croix du signal (48 px, `#2e7d32` et `#c62828` sur `#e8f5e9` et `#ffebee`), le texte du signal et de l'indice (1,25 rem), l'enonce (2 rem), les cases
(144 et 188), l'attenuation a 50 % de l'image essayee, les contrastes de la section 1.5 de `proposition-ines-forme-art-cp-10.md`. La forme porte toujours le sens : la croix et la
coche disent *"faux"* et *"juste"* sans la couleur.

**Ce qui change a l'oeil** :

1. Le meuble perd son bandeau (le visage souriant, le fronton) et son socle (les deux etoiles). Il reste un panneau sombre cadre de la meme bordure, avec le levier sur le flanc.
2. Le score est en haut a droite, sur la ligne de la consigne.
3. Le bandeau rouge ou vert du signal passe de 696 a 274 px de large a 744 (369 px a 992), a gauche, sous la colonne de la reference.

**Saillance.** Ce qui fait avancer l'enfant (les trois cases) monte de 66 px et garde sa taille et son fond. Ce qui le fait sortir (*"Les jeux"* dans l'en-tete) ne bouge ni de
place, ni de taille, ni de couleur. Le signal garde son icone de 48 px ; c'est sa largeur de bandeau qui baisse. Une bonne reponse laisse donc un bandeau vert de 274 px, a
gauche, avec le reste de la ligne vide : je l'ai vu sur la capture, je ne l'ai pas fait juger par un enfant.

## 8. Ce que je n'ai pas pu mesurer

1. **Aucun enfant, aucun doigt, aucun survol reel.** Captures a densite 1 sur un ecran de poste, un seul moteur (Chrome sans tete), la police de ce poste. Les appareils de CP
   (tablette, autre police) ne sont pas mesures.
2. **La phrase de `M4` a 11 px d'air** dans sa colonne a 744 (texte de 399,5 px dans une boite de 410,4, lu par `Range.getClientRects()`). Sur une autre police elle passe sur deux lignes : son bas passerait de 510 a
   540 par calcul, encore sous 551 de 11 px. Non verifie sur une autre police.
3. **Les largeurs 360, 576, 640 et 1440** ne sont pas mesurees. A 720, 768, 1200 et 390 la revelation n'a ete mesuree que sur 3 items (5 ou 6 items vus pour les autres etats) ; les
   cotes sont identiques d'un item a l'autre sur ce qui a ete joue.
4. **Une seule hauteur de fenetre, 551.**
5. **Le decalage lateral de 7,5 px des cases quand une barre de defilement apparait** (observation de `nadia-prof`, a 744) : P ne le corrige pas, et **il apparait a 992**, ou il
   n'existait pas (la manche fraiche passe de 594 a 551 de haut, donc tient dans la fenetre, puis depasse apres l'erreur : case a x = 302 puis 294,5). Une correction (`scrollbar-gutter`)
   serait hors de `.ecran-deux-oeuvres` et toucherait tous les jeux : je ne la propose pas, je la signale.
6. **Le clavier et le focus** : l'anneau de focus sur les cases n'a pas ete rejoue sur P.
7. **L'effet sur les autres jeux** est nul par construction (toutes les regles ont `.ecran-deux-oeuvres` en tete, `manche.html` ligne 34 ne le pose que si `propositionsEnImage`,
   et le catalogue n'a aucun autre jeu a images). Je n'ai pas rendu un autre jeu pour le verifier.
8. **Les contrastes ne sont pas recalcules** : aucune couleur ne change.
9. **Le test de plafond de `theo-archi`, `bootstrap` et `adapter-content` ne sont pas rejoues** : voir la section 9.

## 9. Tests, et routage

**Tests.** Avec les regles de P dans `recreo.css`, sur le worktree jetable, `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test` rend **486 tests, 1 echec** :
`CabinetDeLaMachineTest.chaqueSelecteurEpingleEstEprouveParLeJeuFautif`. Le test derive son attente des selecteurs a classe `cabinet*` de la feuille de production, et le jeu fautif
`contreexemple/CabinetDeLaMachineTest/dimension-en-pixels` ne porte pas `.ecran-deux-oeuvres .cabinet-bandeau` ni `.ecran-deux-oeuvres .cabinet-socle`. **Le correctif est dans le
meme commit que L1** : ajouter ces deux blocs, avec une dimension en pixels, au jeu fautif. `AucunTexteNeVitDansUnSousArbreMasqueTest`, `LHabillageResteImmobileTest`,
`UnEcranNeGouvernePasLApparenceDUnAutreTest`, `TailleDesTextesEtBoutonsTest` et `LePlafondDeRenduDUneReproductionEnPropositionEstGardeTest` sont verts. Perimetre : domain, application
et adapter-web (`-am`). Les tests de `bootstrap` et d'`adapter-content` ne sont pas lances.

| A qui | Quoi |
|---|---|
| `nadia-prof` | Les questions de la section 10 |
| `theo-archi` | La lecture de `C1` pour L3 (section 5) ; `C2` pour le bloc `@media` existant |
| `lea-dev` | Verser `candidat-poste-8.css` apres l'avis de `nadia-prof`, avec la mise a jour du jeu fautif de `CabinetDeLaMachineTest` ; rejouer `pilote-poste-8.mjs` sur le jar construit de son arbre |
| `sofia-sm` | Router ; `RDO-CP-R2` n'est pas dans ce document (voie (b) de `D6`, sans forme a proposer) |

## 10. Questions pour `nadia-prof`

1. **Le visage et les etoiles.** Le bandeau (visage souriant, fronton) et le socle (deux etoiles) disparaissent sur l'ecran a propositions en image, a toute largeur. Tu les avais
   voulus en `S10` ; le meuble garde cadre et levier. L1 est-il acceptable, ou le visage doit-il rester (alors il faut 32 px ailleurs, et je n'en ai pas) ?
2. **Le message en deux colonnes.** Le bloc reste sous le meuble, mais le bandeau du signal est a gauche, sous la colonne de la reference (274 px a 744), et le texte a droite. Est-ce
   toujours *"sous le meuble"* au sens de `M1` et `M4` ? Je vois un risque de lecture : un bandeau rouge a croix sous la reference peut se lire comme *"la reference est fausse"*. 140 px
   de blanc les separent a 744. Je ne peux pas le trancher.
3. **Le score a droite de la consigne.** Acceptable pour ce jeu, ou le score doit-il garder sa place ?
4. **Ce qui reste coupe par le pli.** A la revelation, a 744, l'explication (514 a 594) est coupee : on lit *"Dans ces deux œuvres, la lumière entre par"* puis le pli. A 992, le verdict et
   l'indice de l'echec sont coupes. Ta barre est la phrase de `M4` (744) et l'indice (744), et la croix (992) : confirmes-tu que ces coupes ne comptent pas ? Les lever a 992 demande
   23 px de plus, et je n'ai pas de levier qui ne touche ni la taille des cases ni le plafond.
5. **Le verdict sur deux lignes.** *"Ce n'est pas ça. / Essaie encore !"* passe sur deux lignes a l'echec, a 744. Le texte est le tien ; je ne touche qu'a la mise en ligne. La coupure
   te convient-elle ?
6. **La marge de `L1 + L3`.** Si L2 est refuse (question 3), `L1 + L3` leve le refus avec 7 px de marge mais pas `RDO-CP-R1` a 744 (indice a 564). Veux-tu juger le refus seul, ou rester
   a P ?

## 11. Jobs de `ci.yml` lances pour ce commit

Lances sur ce worktree avant le commit, chacun sorti 0 :

- **`chemins`** : `verifier-chemins-test.sh`, puis `verifier-chemins.sh` sur `git ls-files` et les quatre fichiers neufs.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-garde-fou-accent-test.sh` ; le sujet du commit passe dans `verifier-prefixes.sh`. Le controle du
  proprietaire du garde-fou de l'accent n'est pas lance.
- **`outils`** : les 11 juges purs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`).
- **`build`** : **non lance en entier.** Deux mesures partielles, avec `-am`, perimetre `bootstrap` et ses dependances : `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`
  (5 tests, 0 echec) et `com.recreo.archi.*Test` (262 tests, 0 echec). Ce commit n'ajoute que des fichiers sous `squad/`.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.
