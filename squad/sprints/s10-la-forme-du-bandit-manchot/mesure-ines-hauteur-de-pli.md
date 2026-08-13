# Mesure ines-design - la hauteur du pli, l'hypothese des 640 px, et ce qui reste au meuble

- **Auteur** : ines-design
- **Date** : 2026-08-13
- **Branche** : `s10-ines-hauteur`, cree depuis `s10-ines-meuble-illustre` a `49a6dfd`, lui-meme
  descendant direct verifie de `round-two` a `2d34a15` (`git merge-base` egal a la tete de
  `round-two`).
- **Ne modifie ni `recreo.css` ni aucun gabarit sur disque.** Ce document mesure et propose ; il ne
  tranche ni `DEC-0016` (camille-po) ni la convention d'audit de `nadia-prof`.

## Ce que je livre

1. La distribution reelle des hauteurs de viewport d'appareils courants, avec sa source.
2. Le tableau des sept jeux du catalogue, quatre etats, trois hauteurs (640/740/800), quatre
   niveaux de leviers cumules.
3. La hauteur et le jeu de leviers minimal qui ferme les sept jeux.
4. Ce que le meuble pourrait gagner si le pli descend.

---

## 1. L'hypothese : 640 px est-il un pire cas, un cas pessimiste, ou un cas qui ne correspond a
   aucun appareil ?

### Source

Le protocole DevTools de Chromium ne publie pas sa liste d'appareils emules par un appel du
protocole (aucune methode CDP ne la rend) : elle est portee par un fichier de donnees du frontend
DevTools, distribue tel quel par Puppeteer (projet Google, meme organisation que Chromium), qui
declare explicitement reprendre les preregalages du selecteur d'appareils de Chrome DevTools.
Recupere ce jour, source `packages/puppeteer-core/src/common/Device.ts` du depot
`puppeteer/puppeteer`, branche `main` : 66 entrees en orientation portrait apres filtrage des
doublons "landscape". J'exclus les neuf tablettes (iPad et ses variantes, Galaxy Tab S4, Kindle
Fire HDX, Nexus 7/10, Blackberry PlayBook) : `recreo` cible un enfant sur telephone, largeur de
reference 360-375 px (`DEC-0007` point 5), et une tablette n'est pas le pire cas de hauteur qui
nous occupe ici. **55 telephones restants.**

### Distribution complete (hauteur CSS, croissante)

| Hauteur CSS (px) | Largeur CSS (px) | Appareil |
|---|---|---|
| 320 | 240 | JioPhone 2 |
| 360 | 640 | Microsoft Lumia 550 |
| 480 | 320 | iPhone 4 |
| 533 | 320 | Nokia Lumia 520 |
| 568 | 320 | iPhone 5 |
| 568 | 320 | iPhone SE (1re generation) |
| 640 | 360 | BlackBerry Z30 |
| 640 | 360 | Galaxy Note 3 |
| 640 | 360 | Galaxy Note II |
| 640 | 360 | Galaxy S III |
| 640 | 360 | Galaxy S5 |
| 640 | 384 | LG Optimus L70 |
| 640 | 360 | Microsoft Lumia 950 |
| 640 | 384 | Nexus 4 |
| 640 | 360 | Nexus 5 |
| 640 | 360 | Moto G4 |
| 658 | 320 | Galaxy S9+ |
| 659 | 393 | iPhone 14 Pro |
| 659 | 393 | iPhone 15 |
| 659 | 393 | iPhone 15 Pro |
| 663 | 390 | iPhone 14 |
| 667 | 375 | iPhone 6 / 7 / 8 |
| 731 | 411 | Pixel 2 |
| 732 | 412 | Nexus 5X / 6 / 6P |
| 736 | 414 | iPhone 6 Plus / 7 Plus / 8 Plus |
| 739 | 430 | iPhone 14 Pro Max / 15 Plus / 15 Pro Max |
| 740 | 360 | Galaxy S8 |
| 745 | 428 / 353 | iPhone 14 Plus, Pixel 4 / 4a (5G) |
| 786 | 393 | Pixel 3 |
| 812 | 375 | iPhone X / 11 Pro / 12 Mini / 13 Mini |
| 823 | 411 | Pixel 2 XL |
| 828 | 414 | iPhone 11 |
| 844 | 390 | iPhone 12 / 12 Pro / 13 / 13 Pro |
| 851 | 393 | Pixel 5 |
| 854 | 480 | Nokia N9 |
| 896 | 414 | iPhone XR / 11 Pro Max |
| 926 | 428 | iPhone 12 Pro Max / 13 Pro Max |

**Minimum : 320 px** (JioPhone 2, un telephone a clapet non tactile - hors du perimetre realiste
d'un enfant qui joue a `recreo`). **Mediane : 732 px.** **Maximum : 926 px.**

Repartition par tranche : **16/55 (29 %) a 640 px ou moins**, **19/55 (35 %) entre 641 et 740 px**,
**20/55 (36 %) au-dessus de 740 px**.

### Ce que je lis dans cette distribution

**640 px n'est pas un pire cas : c'est le plancher des appareils les plus anciens de la liste, pas
celui de la generation en usage aujourd'hui.** Les dix appareils exactement a 640 px sont tous des
modeles dont le nom les situe au debut des annees 2010 (Galaxy S III, Note II, Nexus 4, BlackBerry
Z30) au plus tard au milieu des annees 2010 (Moto G4, Lumia 950) - aucun n'est un appareil qu'on
attend a neuf aujourd'hui. **Tout appareil de la lignee iPhone a partir de l'iPhone X (2017) est a
812 px ou plus**, a une exception notable et signalee ci-dessous. **Tout Pixel de la liste est a
731 px ou plus.**

**Anomalie que je signale sans la resoudre.** Les entrees iPhone 14/15 non-Plus/non-Pro Max (659 a
663 px) sont *plus courtes* que les iPhone 12/13 (844 px) qui les precedent dans la meme gamme, a
taille d'ecran physique comparable - une rupture qui casse la progression reguliere du reste de la
serie et qui ressemble a une donnee de source erronee ou a une convention de calcul differente
(peut-etre une hauteur qui exclut une zone que les generations precedentes incluaient). Je ne
tranche pas cette question : je la nomme, je garde les deux valeurs dans le tableau, et je
n'assois aucune conclusion du reste de ce document sur elle seule. Meme en l'ecartant entierement,
le reste de la serie iPhone recente (X a 13 Pro Max, 14 Plus, 15 Plus, 15 Pro Max) reste a 739 px
ou plus.

**Conclusion sur l'hypothese.** 640 px est un cas **pessimiste par rapport a la mediane** (732 px,
soit 92 px de plus) et **correspond a une generation d'appareils largement sortie d'usage**, pas au
telephone median d'un enfant en 2026. Le Galaxy S8 (2017), lui, tombe exactement a 740 px - la
deuxieme hauteur demandee dans cette mesure n'est donc pas arbitraire, elle correspond a un
appareil reel encore raisonnablement present. 800 px ne couvre pas la totalite de la distribution
(36 % des appareils listes sont au-dessus), mais couvre la totalite de la serie iPhone 12 a 13 Pro
Max et la quasi-totalite des Pixel listes.

---

## 2. Methode de mesure des sept jeux

**Jar reel, session HTTP reelle, jamais une maquette statique.** `bootstrap/target/recreo.jar`
reconstruit sur `s10-ines-hauteur` (JDK 21 constate par le mecanisme deja en place sur ce poste, non
ecrit ici), lance sur un port dedie. Chrome headless pilote par le protocole DevTools (port de
debogage et profil neufs a chaque instance, jamais reattache a une instance laissee derriere - le
piege deja nomme dans `mesure-ines-budget-vertical-m4.md`) execute, **dans la page reelle**, les
memes appels `fetch` que `recreo.js` ferait (`POST /parties/<jeu>`, `POST /parties/<jeu>/coups`) :
la session HTTP est donc portee par le cookie du navigateur, comme en jeu reel, jamais recree a la
main.

**Contenu le plus charge de chaque paquet, jamais un tirage choisi au hasard** - meme convention
que `mesure-ines-budget-vertical-m4.md` et que les arbitrages de `nadia-prof` : pour chaque jeu,
l'enonce, l'indice, l'explication (si le paquet en porte) et la bonne reponse **les plus longs**
relus dans le YAML servi ce jour, injectes dans le rendu reel une fois l'etat structurel atteint
(le texte est remplace, la structure - quels elements existent, dans quel ordre, avec quelles
classes - reste celle que le serveur a reellement rendue pour cet etat).

**Quatre etats, mesures par jeu :**
- **1er echec** et **2e echec** : mesures separement, verifie identiques au pixel pres sur les
  sept jeux (le texte du message et de l'indice ne varie pas entre les deux, seule la proposition
  marquee change - ni la police ni le rembourrage n'en dependent). Le tableau ne porte donc plus
  qu'une colonne "1er/2e echec".
- **revelation** (troisieme echec) : le paquet `cp-le-bon-son` est le seul a porter une
  explication ; j'assure sa presence dans l'etat mesure meme quand le tirage reel ne l'a pas
  fournie, pour mesurer le pire cas reel du paquet et non un tirage qui l'esquive.
- **reussite** ("Bravo !") : bonne reponse au premier coup - `session.indice()` est vide sur une
  manche fraiche (verifie dans `BanditManchotSession.indice()` : non-vide seulement si
  `essaisSurLaManche > 0`), donc cet etat ne porte jamais d'indice, sur aucun jeu.

**Mesure : `getBoundingClientRect().bottom` du dernier enfant visible de `.message-jeu`**, jamais
une lecture de pixels sur une capture d'ecran. C'est la meme grandeur que le "bas du dernier bloc
qui reste" que `nadia-prof` releve dans ses propres arbitrages (`FICHE-bandit-manchot-additions-cp.md`,
arbitrage du 2026-08-13 sur l'ampoule) : verifie concordant au dixieme de pixel pres sur plusieurs
jeux ou nos deux mesures portent sur le meme contenu (par exemple Calcul CP, echec : 621,2 px ici,
621,19 px chez elle).

**La hauteur du viewport ne change aucune position mesuree.** Verifie par grep sur `recreo.css` et
sur la partie de `vendor/bootstrap.min.css` reellement utilisee par nos gabarits : aucune propriete
`vh`, `dvh` ni `min-height: 100%` n'atteint un element de `accueil.html` ou de
`fragments/manche.html` (les seules occurrences de `vh` dans Bootstrap sont des utilitaires
`.vh-100` / `.min-vh-100`, jamais references par nos gabarits). La position d'un bloc dans le
document ne depend donc que du contenu au-dessus de lui, jamais de la hauteur de la fenetre : une
seule mesure par etat et par jeu vaut pour les trois hauteurs demandees, seul le pli se deplace.
C'est pour cette raison que le tableau ci-dessous ne recalcule rien a 740 et 800 : il compare la
meme position mesuree aux trois hauteurs.

**Quatre niveaux de leviers, cumules** (jamais isoles - chaque niveau inclut les precedents, comme
demande) :

- **niveau 0, tel quel** : rendu du serveur sans aucune modification.
- **niveau 1, + resserrement des marges** : candidate jamais posee dans `recreo.css`, injectee en
  memoire pour la mesure seulement -
  `.consigne, .enonce { margin-bottom: 0.25rem; }` et
  `.message-jeu .indice, .message-jeu .explication { margin: 0.25rem 0 0; padding: 0.5rem 0.75rem; }`,
  `.signal { padding: 0.5rem 0.75rem; }` (reprise et adaptee de `mesure-ines-budget-vertical-m4.md`
  section 3 au balisage actuel, `.message-jeu` n'existant pas encore a cette date).
- **niveau 2, + baisse du plancher de texte a 1rem** : ajoute
  `.signal .verdict, .indice, .explication { font-size: 1rem; }` (aujourd'hui 1,25rem). Je ne
  touche pas `.enonce` (2rem, acquis 3 de `nadia-prof`) ni `.consigne`.
- **niveau 3, + ampoule** : sur les etats d'echec (1er/2e), `.indice` est retire du DOM (M16 :
  l'ampoule ne montre plus l'indice automatiquement, elle se marque seulement) ; sur la revelation,
  `.indice` reste affiche (M17 clause 2) - l'ampoule ne la touche donc jamais, exactement comme
  `nadia-prof` l'a deja mesure elle-meme.

---

## 3. Tableau des sept jeux, quatre etats, quatre niveaux de leviers

Valeur = position (en px CSS depuis le haut de la page) du bas du dernier bloc necessaire. Une
hauteur H "ferme" cet etat si la valeur est <= H.

| Jeu | Etat | tel quel | +marges | +plancher 1rem | +ampoule |
|---|---|---|---|---|---|
| Calcul CP | 1er/2e echec | 621,2 | 577,2 | 543,2 | 495,2 |
| Calcul CP | revelation | 593,2 | 549,2 | 543,2 | 543,2 |
| Calcul CP | reussite (Bravo) | 527,2 | 495,2 | 495,2 | 495,2 |
| Le bon verbe | 1er/2e echec | 891,2 | 847,2 | 771,2 | 675,2 |
| Le bon verbe | revelation | 891,2 | 847,2 | 771,2 | 771,2 |
| Le bon verbe | reussite (Bravo) | 707,2 | 675,2 | 675,2 | 675,2 |
| Le bon son | 1er/2e echec | 651,2 | 607,2 | 567,2 | 495,2 |
| Le bon son | revelation | 719,2 | 663,2 | 639,2 | 639,2 |
| Le bon son | reussite (Bravo) | 527,2 | 495,2 | 495,2 | 495,2 |
| Les petites soustractions | 1er/2e echec | 681,2 | 607,2 | 567,2 | 495,2 |
| Les petites soustractions | revelation | 653,2 | 579,2 | 567,2 | 567,2 |
| Les petites soustractions | reussite (Bravo) | 527,2 | 495,2 | 495,2 | 495,2 |
| Petits problemes | 1er/2e echec | 777,2 | 703,2 | 663,2 | 591,2 |
| Petits problemes | revelation | 749,2 | 675,2 | 663,2 | 663,2 |
| Petits problemes | reussite (Bravo) | 623,2 | 591,2 | 591,2 | 591,2 |
| Compare les nombres | 1er/2e echec | 681,2 | 637,2 | 567,2 | 495,2 |
| Compare les nombres | revelation | 653,2 | 609,2 | 567,2 | 567,2 |
| Compare les nombres | reussite (Bravo) | 527,2 | 495,2 | 495,2 | 495,2 |
| Les complements a 10 | 1er/2e echec | 681,2 | 637,2 | 591,2 | 495,2 |
| Les complements a 10 | revelation | 653,2 | 609,2 | 591,2 | 591,2 |
| Les complements a 10 | reussite (Bravo) | 527,2 | 495,2 | 495,2 | 495,2 |

### Synthese par hauteur (pire etat de chaque jeu compare a la hauteur)

**640 px**
- tel quel : 1/7 ferment - en defaut : Le bon verbe (+251,2 px), Petits problemes (+137,2 px),
  Le bon son (+79,2 px), Compare les nombres et Les complements a 10 (+41,2 px chacun), Les
  petites soustractions (+41,2 px).
- +marges : 4/7 ferment - en defaut : Le bon verbe (+207,2 px), Petits problemes (+63,2 px), Le
  bon son (+23,2 px).
- +plancher 1rem : 5/7 ferment - en defaut : Le bon verbe (+131,2 px), Petits problemes
  (+23,2 px).
- +ampoule : 5/7 ferment - en defaut : Le bon verbe (+131,2 px), Petits problemes (+23,2 px).
  **L'ampoule n'ajoute rien ici** : elle allege les etats d'echec, mais la revelation - jamais
  touchee par elle - reste la plus haute des deux sur ces deux jeux.

**740 px**
- tel quel : 5/7 ferment - en defaut : Le bon verbe (+151,2 px), Petits problemes (+37,2 px).
- +marges : 6/7 ferment - en defaut : Le bon verbe (+107,2 px).
- +plancher 1rem : 6/7 ferment - en defaut : Le bon verbe (+31,2 px).
- +ampoule : 6/7 ferment - en defaut : Le bon verbe (+31,2 px). Meme constat : l'ampoule ne
  deplace pas le seuil, parce que le point le plus haut de "Le bon verbe" a ce niveau est atteint
  a la fois par l'echec et par la revelation (771,2 px), et l'ampoule ne touche pas la revelation.

**800 px**
- tel quel : 6/7 ferment - en defaut : Le bon verbe (+91,2 px).
- +marges : 6/7 ferment - en defaut : Le bon verbe (+47,2 px).
- +plancher 1rem : **les sept jeux ferment**, marge la plus juste +28,8 px (Le bon verbe).
- +ampoule : **les sept jeux ferment**, meme marge la plus juste +28,8 px (Le bon verbe,
  desormais porte par sa revelation et non plus par son echec, l'ampoule ayant deplace le
  gagnant sans changer sa valeur).

---

## Reponse a la question posee : hauteur et jeu de leviers minimal

**800 px, avec deux leviers (resserrement des marges + plancher de texte a 1rem), ferme les sept
jeux du catalogue sur le contenu le plus charge de chaque paquet.** La marge la plus juste est de
**+28,8 px**, sur "Le bon verbe" (son item a indice le plus long, 133 caracteres). **L'ampoule
n'est pas necessaire pour fermer les sept jeux** : elle allege des etats d'echec deja larges a
800 px, mais elle ne touche jamais la revelation, qui est le veritable goulot sur ce jeu a ce
niveau de leviers - son adoption reste justifiee sur son propre motif pedagogique (arbitrage de
`nadia-prof` du 2026-08-13), pas comme condition de fermeture verticale.

**740 px ne suffit pas**, meme avec les trois leviers cumules : "Le bon verbe" y reste en defaut de
31,2 px sur sa revelation. **640 px ne suffit jamais**, quel que soit le nombre de leviers actionnes
- au mieux 5 jeux sur 7, et le meme jeu (Le bon verbe) et le meme "Petits problemes" y restent
structurellement hors du pli sur leur contenu le plus charge, pour la raison deja nommee sous
`DEC-0016` : la longueur du texte, pas la mise en page.

Ce plancher a lisibilite (1rem au lieu de 1,25rem) est une entaille reelle au plancher de
`nadia-prof`, pas un gain gratuit : je le mesure et je le chiffre, je ne le recommande pas a sa
place. **Si l'entaille du plancher de lisibilite n'est pas acceptable, aucune des trois hauteurs
testees ne ferme les sept jeux avec le seul resserrement des marges** (niveau 1 seul reste a 6/7 a
740 et 800 px, "Le bon verbe" y manque de 107,2 et 47,2 px). C'est un arbitrage qui revient a
`nadia-prof`, pas a moi.

---

## 4. Ce que le meuble pourrait gagner si le pli descend

**Le budget vertical du cabinet lui-meme (bandeau, corps, socle) est independant de la hauteur du
pli** : grandir le cabinet ne prend rien nulle part d'autre, il ajoute directement sa propre
hauteur au total mesure ci-dessus, pixel pour pixel - c'est le meme total qui contient a la fois le
cabinet et le message sous lui. Le budget disponible pour un cabinet plus grand est donc
exactement la marge la plus juste que le tableau ci-dessus etablit pour le jeu de leviers retenu,
et rien de plus : l'ajouter au cabinet revient a la retirer du message, jusqu'a epuisement.

**A 800 px, avec le jeu de leviers minimal (niveau 2), ce budget mesure est de 28,8 px** - avant de
retomber a zero de marge sur "Le bon verbe", exactement le meme risque de fragilite que le "1 px"
qu'`ines-design` avait refuse de vendre comme une marge de securite dans `mesure-ines-budget-vertical-m4.md`.
Le depenser en entier romprait la meme regle une seconde fois. **Une proposition raisonnable
consommerait une partie de ce budget, pas sa totalite** - par exemple la moitie (environ 14 px),
laissant une marge residuelle du meme ordre que celle deja jugee viable ailleurs sur ce chantier.

**Ce que 14 a 28 px permettent, concretement** : le bandeau (`.cabinet-bandeau`) est aujourd'hui a
2rem (32 px) de haut. Un gain de cet ordre porterait sa hauteur a 2,875-3,75rem (46 a 60 px) -
assez pour un arc de fronton modeste (une bombe de quelques pixels au centre du bandeau, pas un
dome large comme les deux references de l'utilisatrice), jamais assez pour l'arc prononce qu'elle a
montre : ces references-la bombent sur une fraction significative de la hauteur totale du meuble,
pas sur 15-20 % d'une bande de 32 px. Ce n'est donc pas un budget qui rapproche le meuble des
references au point de les egaler ; c'est un budget qui autorise un premier geste de relief la ou
`proposition-ines-meuble-illustre.md` n'avait droit qu'a un accent plat.

**La largeur reste hors de ce budget, integralement.** Rien dans cette mesure ne touche 360 px, le
cadre de 8 px ni les colonnes a 96/152 px : ce sont des grandeurs horizontales, et descendre le pli
ne rend aucun pixel sur cet axe. Des flancs elargis resteraient a chiffrer sur le seul budget
horizontal, inchange par ce chantier - c'est le meme constat que celui du dernier lot, non modifie
ici.

**Ce que je ne fais pas ici** : je ne dessine rien, je ne propose pas de valeur finale pour la
hauteur du bandeau, et je ne decide pas si 800 px devient la nouvelle reference - ce sont des
gestes qui reviennent a `nadia-prof` (convention d'audit) et a `camille-po` (`DEC-0016`). Ce
document mesure ce que ces deux choix rendraient disponible, pas plus.

---

## Ce que je remonte

- **A `camille-po` et a l'utilisatrice** : `DEC-0016` fixe 640 px au motif que c'etait "deja la
  convention que `nadia-prof` applique de fait" - or 640 px est plus court que 71 % des telephones
  de la distribution mesuree, et plus court que tout iPhone sorti depuis 2017 (a l'exception
  signalee et non resolue des iPhone 14/15 non-Plus/non-Pro Max). Si cette convention est revue,
  **800 px avec le resserrement des marges et la baisse du plancher de texte a 1rem** est la
  combinaison la plus econome que j'ai mesuree qui ferme les sept jeux du catalogue sur leur
  contenu le plus charge - avec une marge fragile de 28,8 px sur un seul jeu.
- **A `nadia-prof`** : le plancher de lisibilite a 1,25rem est necessaire aujourd'hui pour fermer
  "Le bon verbe" a 740 px, et son abaissement a 1rem est necessaire pour le fermer a 800 px. C'est
  une entaille a un plancher qui est le sien, je la chiffre, je ne la propose pas.
- **A `theo-archi`** : rien de nouveau sur le fond technique - ce document ne mesure que la forme
  et la position, aucune ligne de domaine ni d'application n'est concernee.

Je ne fusionne pas, je ne pousse pas.
