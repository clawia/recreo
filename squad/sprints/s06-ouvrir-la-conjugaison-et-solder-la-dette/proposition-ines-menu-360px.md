# Proposition ines-design - repli du menu de l'en-tete sous 1200 px

- **Auteure** : ines-design
- **Date** : 2026-08-07
- **Obstacle route** : commit `4b63f62` ([SOF] s06: obstacle nouveau route et arbitrage de
  capacite - debordement du menu a 360 px)
- **Pieces lues** : `docs/backlog/backlog.md` ; commit `4b63f62` ; `ADR-0014` (point 7, pas de
  JavaScript de Bootstrap) ; `ADR-0008` (comportement de jeu sur le serveur) ; `DEC-0012` (menu
  minimal, une seule entree) ; `squad/conformite/FICHE-bandit-manchot-additions-cp.md` (les quatre
  reserves du 2026-08-07 sur ce meme en-tete) ; `recreo.css` et `accueil.html` a `round-two`
  (`4b63f62`).
- **Mandat** : je propose un comportement de repli en CSS pur. Je ne tranche pas, je ne touche pas
  au balisage ni a la couleur. `lea-dev` implemente, `theo-archi` instruit la question du
  garde-fou automatique, `nadia-prof` verifie en jouant.
- **Ce que je ne touche pas** : aucune valeur de couleur (le plancher `#1d4b6b` reste celui que
  `nadia-prof` a donne pour S6-10, en cours d'implementation par `lea-dev` en parallele) ; aucun
  element gele par l'arbitrage S6-9 (`.btn.retour-accueil`, `.btn.sortie-pleine`, "Recommencer") ;
  aucune ligne de `recreo.js` ; aucun gabarit.

## Provisionnement et lignee

Worktree neuf provisionne hors de l'arbre du depot, branche dediee a cette tache, forkee depuis
`round-two` a `4b63f62`. Verifie avec le script livre ce sprint :

```
$ outils/verifier-lignee.sh
le worktree sur la branche '<ma branche>' descend de round-two.
```

Sortie correcte sur mon cas : le script identifie la bonne branche et le bon verdict, rien a
signaler a `sofia-sm` sur son comportement.

## Un avertissement d'abord : comment j'ai verifie, et un piege d'outillage que j'ai trouve en cours de route

Je dis cela avant les propositions parce que ca change ce que mes chiffres veulent dire.

**Le piege.** J'ai d'abord verifie avec Chrome reel en mode headless, invoque directement en
ligne de commande avec `--window-size=<L>,<H> --screenshot=...`. Sur une page de test minimale
(`document.write(window.innerWidth)`), demander une fenetre de 320 x 480 rend une fenetre
**500 x 385** : ce mode d'invocation impose un plancher de largeur d'environ 500 px, quelle que
soit la valeur demandee en dessous. Mes premieres captures a 360 px, 375 px, 400 px etc.
montraient donc toutes la meme mise en page (celle a 500 px), simplement recadree a la largeur
demandee - un debordement qui n'existe pas a mentir en debordement qui existe. Je l'ai repere
parce que les nombres ne bougeaient pas d'une capture a l'autre, et je l'ai verifie explicitement
avec la page minimale ci-dessus avant de faire confiance a une seule mesure.

**Le contournement.** J'ai place la page reelle dans une iframe dont je fixe la largeur CSS
(un fichier HTML local avec plusieurs iframes cote a cote, chacune a une largeur differente,
pointant vers le jar lance en local), et capture la page conteneur avec Chrome headless a une
largeur qui, elle, depasse le plancher de 500 px. La largeur CSS d'une iframe n'est pas soumise au
plancher de fenetre du navigateur : c'est elle qui a servi de vraie largeur de viewport pour
chaque mesure ci-dessous. J'ai aussi ajoute, temporairement, un script de mesure
(`getBoundingClientRect` sur les cinq elements de l'en-tete, affiche en texte visible) directement
dans `accueil.html` pour lire des nombres plutot que d'estimer des pixels sur une capture -
**revert avant de committer**, comme le CSS explore.

**Ce que je signale a `theo-archi`** pour son obstacle technique routes en parallele sur le
garde-fou automatique : `--window-size` seul, en ligne de commande, ne suffit pas a obtenir un
rendu Chrome reel sous ~500 px de large. Un garde-fou qui utiliserait Chrome headless de cette
facon directe passerait a cote du defaut qu'il pretend surveiller, exactement de la meme maniere
que `ViewportEtLargeurTelephoneTest` est passe a cote du debordement de flex. Une iframe de
largeur fixee, ou le pilotage par le protocole DevTools (qui expose un reglage de viewport
independant de la fenetre), evite le probleme.

## Ce que j'ai mesure, avec la methode qui marche

### Le defaut est reel dans le code, verifie a la lecture

`.entete-contenu { display: flex; align-items: center; gap: 1rem; }` ne porte aucun `flex-wrap`
(defaut `nowrap`). `.nom-site` porte `margin-right: auto`, qui pousse `.menu` a l'extreme droite
de la meme ligne, quelle que soit la largeur disponible. Rien ne prevoit qu'un jour cette ligne ne
tienne plus.

### La marge est deja tres etroite avant tout correctif - mesure sur mon environnement

Sur mon rendu (Chrome reel, pile de polices systeme de Windows), la ligne complete (icone 44 px +
espace 16 px + "Recreo" 99 px + espace 16 px + "Les jeux" 109 px = 284 px de contenu, plus 12 px de
rembourrage de chaque cote du conteneur apres S6-8) tient tout juste jusqu'a **308 px de largeur de
viewport**, puis deborde en dessous. C'est une marge de moins de 50 px sous la cible de 360 px
demandee ici et sous les 375 px de `DEC-0007` : le dessin actuel n'a jamais eu de reelle reserve,
il tenait par la seule police de mon poste. Je n'ai **pas reproduit** de debordement a 360 px
precisement dans mon propre environnement (le calcul montre que ca tient, de peu) : je ne mets
pas cela en doute contre la mesure de `lea-dev`, faite sur Chrome headless reel elle aussi - je
constate que la marge est si faible qu'une pile de polices differente (une autre plateforme, un
Chrome sans "Segoe UI" disponible, repli plus loin dans la chaine de polices systeme que
`ADR-0014` impose) suffit a faire basculer le meme dessin dans le debordement. **C'est exactement le
type de defaut qu'un repli en dur (une largeur fixe ou tout rentre pile) ne resout pas : il ne
fait que deplacer le point de rupture vers un autre nombre, tout aussi fragile.** C'est pour cette
raison que ma proposition ne fixe aucune largeur de bascule dans la feuille.

### La proposition : un repli qui s'adapte au contenu reel, jamais a un nombre fige

| Selecteur | Avant | Propose |
|---|---|---|
| `.entete-contenu` | `display: flex; align-items: center; gap: 1rem;` | ajoute `flex-wrap: wrap;` |
| `.nom-site` | `margin: 0; margin-right: auto;` | `margin: 0;` (le `margin-right: auto` est retire) |
| `.menu` | `display: flex;` | ajoute `margin-left: auto;` |

**Comportement obtenu** :

1. Tant que l'icone, le titre et "Les jeux" tiennent sur une ligne, rien ne change : `.menu` reste
   pousse a l'extreme droite (la marge automatique se lit desormais sur lui plutot que sur le
   titre, ce qui ne deplace rien sur grand ecran - verifie ci-dessous).
2. Des que la ligne ne tient plus, `.entete-contenu` passe "Les jeux" seul sur une seconde ligne,
   sans qu'aucun nombre de largeur ne soit ecrit dans la feuille : c'est le contenu reel (la
   police effectivement rendue, la largeur effective du bouton) qui decide, pas une supposition
   sur sa largeur.
3. Sur cette seconde ligne, `margin-left: auto` pousse "Les jeux" a l'extreme droite - la meme
   position relative que sur une seule ligne, pour que l'oeil retrouve la sortie au meme endroit
   du bandeau qu'a n'importe quelle largeur.
4. Aucune taille, aucun rembourrage, aucune police de `.lien-accueil` n'est touche : la cible
   tactile (`min-height: 2,75rem`) et la lisibilite (`font-size: 1,25rem`) restent exactement ce
   qu'elles etaient, sur une ligne comme sur deux.

### Verifie sur le rendu reel, largeur par largeur (methode iframe, nombres lus par
`getBoundingClientRect`)

| Largeur testee | Etat de "Les jeux" | Position mesuree |
|---|---|---|
| 1280 px, 992 px, 768 px, 600 px | une ligne | inchangee par rapport a avant le correctif |
| 500 px a 309 px | une ligne | `right` = largeur - 12 px, a chaque palier teste (500, 480, 460, 440, 420, 400, 390, 375, 360, 320, 315, 312, 310, 309) |
| **308 px** | **bascule** | derniere largeur a une ligne dans mon environnement |
| **305 px** | **deux lignes** | premiere largeur ou "Les jeux" passe seul en dessous, `right` = largeur - 12 px sur cette seconde ligne |
| 300 px a 200 px | deux lignes | meme comportement a chaque palier teste (300, 280, 260, 240, 220, 200), "Les jeux" toujours entier, jamais retreci, jamais coupe |
| **360 px** (largeur demandee par cette tache) | une ligne dans mon environnement, deux lignes dans un environnement a police plus large - dans les deux cas, "Les jeux" est entierement visible, jamais coupe | c'est la propriete qui compte : peu importe ou la bascule tombe reellement, rien ne deborde plus jamais |

**Le point precis de bascule (308/305 px) est propre a mon environnement et je ne le fige pas** -
c'est tout le sens de ne pas ecrire de largeur de media query : sur un poste ou la police de repli
est plus large que la mienne (cas probable de l'environnement ou `lea-dev` a trouve le defaut a
360 px), la bascule se produirait plus tot, disons vers 340 ou 350 px, et le resultat serait
identique - deux lignes propres, rien qui deborde. **C'est une propriete que je peux garantir
sans connaitre la police exacte de qui regarde l'ecran, ce qu'un seuil fixe ne permettait pas.**

### Ecran le plus etroit teste, pour la robustesse du mecanisme

A 200 px (bien en dessous de toute cible retenue par ce depot), le mecanisme continue de
fonctionner correctement : icone et titre sur la premiere ligne, "Les jeux" entier et non retreci
sur la seconde, aligne a droite. Je ne propose pas 200 px comme cible - je le mentionne parce que
c'est la preuve que le mecanisme n'a pas de point de rupture connu en dessous de 360 px, seulement
au-dessus de ce qu'aucun telephone ne fait plus depuis longtemps.

## Garde-fous verifies

`mvn -B test` sur `adapter-web` avec mon CSS explore : **117 tests, 0 echec, 0 erreur**. Aucune
classe neuve ajoutee au balisage (je ne touche que des proprietes sur des selecteurs deja
existants), donc rien a signaler a `HabillageDesEcransTest`. `LActionNeDeclarePasMoinsQueLaSortieTest`
et `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` restent verts : je ne touche a aucune classe qu'ils
lisent (`.lien-accueil`, `.btn`, `.ecran-de-jeu`).

## Ce que je laisse au jugement de `nadia-prof`

- Qu'a 360 px reellement, sur son propre navigateur, "Les jeux" soit visible en entier (sur une
  ligne ou sur deux, peu importe laquelle : la seule chose qui compte est qu'il ne soit jamais
  coupe ni hors cadre) - elle l'a deja inscrit dans sa liste de remesures.
- Que le passage a deux lignes, quand il se produit, reste lisible et ne fasse pas paraitre
  l'en-tete desequilibre a l'oeil d'un enfant de CP - je n'ai mesure que des positions et des
  tailles, pas un jugement esthetique.

## Ce que je remonte plutot que de trancher

- **A `theo-archi`** : le piege `--window-size` decrit plus haut, pour son obstacle technique sur
  un garde-fou automatique de ce defaut - un rendu Chrome pilote uniquement par cet argument ne
  verifierait rien sous ~500 px.
- **A `sofia-sm` / `nadia-prof`** : je ne peux pas garantir un nombre de pixels precis auquel la
  bascule se produira sur le poste de chacun, puisque c'est precisement la variable (la police
  systeme reellement disponible) que ce depot ne controle pas et ne doit pas fixer par une police
  telechargee (`ADR-0014`). Je garantis la propriete ("jamais de debordement, quelle que soit la
  largeur, quelle que soit la police"), pas le point ou elle change de forme.
