# Matrice de couverture - recreo

Cette matrice est l'outil de pilotage le plus important de `camille-po`. Elle liste, une ligne
par competence, l'integralite des quatre referentiels actuellement disponibles
(`squad/programme/mathematiques/competences-cp.md`, `competences-cm1.md`,
`squad/programme/francais/competences-cp.md`, `competences-cm1.md`), et l'etat reel du produit
face a chacune d'entre elles sur la branche `round-two`.

## Comment la lire

| Colonne | Sens |
|---|---|
| Matiere | La matiere du programme (mathematiques, francais, puis les suivantes a mesure que des referentiels existeront). |
| Niveau | CP (cycle 2) ou CM1 (cycle 3). |
| Domaine | Le regroupement tel qu'il apparait dans le referentiel source (ex. "Nombres et calculs"). |
| Competence | L'intitule de la competence, repris tel quel du referentiel. |
| Jeu(x) couvrant | Le ou les jeux (mecanique + contenu) qui la couvrent, ou `-` si aucun. |
| Mecanique | La mecanique de jeu reutilisable qui la sert (ex. "bandit manchot"), ou `-`. |
| Statut | `non couvert` / `en cours` / `couvert-valide` (voir ci-dessous). |

**Statuts** :

- `non couvert` : aucun jeu ne travaille cette competence.
- `en cours` : un jeu existe et cible cette competence, mais n'a pas encore recu le verdict
  `VALIDE` de `nadia-prof` sur `squad/conformite/FICHE-<jeu>.md` (Done technique atteint ou en
  cours, Done pedagogique pas encore prononce).
- `couvert-valide` : `nadia-prof` a rendu un verdict `VALIDE` sur la fiche de conformite
  correspondante. Ce statut n'est **jamais** attribue sur la seule foi d'une CI verte.
- `non couvrable en l'etat` (introduit au sprint 19, `DEC-0042`) : la competence exige quelque
  chose qu'aucune mecanique que ce produit pourrait construire ne peut fournir, et non faute d'un
  contenu, d'une mecanique ou d'un arbitrage pedagogique qui manquerait encore. Deux motifs
  distincts ont conduit a ce statut, et chaque ligne dans cet etat porte le sien en toutes lettres :
  soit la competence exige de revenir sur une contrainte fondatrice du produit lui-meme
  (`CLAUDE.md`) - c'est le cas de la reconnaissance vocale ; soit elle exige un jugement humain sur
  une production libre de l'enfant, qu'aucun verdict serveur testable ne peut rendre (`ADR-0008`),
  et que ni le choix ni la saisie ne peuvent automatiser (amendement du 2026-08-24 a `DEC-0042`, sur
  arbitrage de `nadia-prof`). Une ligne dans cet etat ne compte dans aucune reserve de contenu, dans
  aucun horizon de sprint, et dans aucun compte de report : elle est retiree du denominateur
  "atteignable" de cette matrice (voir la synthese de s19 en bas de fichier), sans etre retiree du
  referentiel source. Seule une decision future de l'utilisatrice - sur la contrainte fondatrice en
  cause, ou sur un amendement du referentiel qui reformulerait la competence - peut la faire sortir
  de cet etat ; ce n'est pas un geste de planning.

## Qui la met a jour, et quand

`camille-po` la met a jour :

- **au planning**, quand une story nouvelle ou modifiee cible une competence (colonnes Jeu(x)
  couvrant / Mecanique renseignees, statut passe a `en cours`) ;
- **a la review**, en fonction de la confirmation technique de `yanis-qa` et `theo-archi`
  (Done technique) et, seulement si `nadia-prof` a rendu un verdict `VALIDE`, en faisant passer
  la ligne a `couvert-valide`.

Un statut `couvert-valide` refuse ou remis en cause par `nadia-prof` (verdict `REFUSE` ou
`VALIDE SOUS RESERVE` avec reserves non levees) repasse a `en cours` et redevient du backlog.

## Etat de reconstruction (round-two)

Cette matrice est reconstruite **depuis zero** au sprint 0 de `round-two`. Aucune ligne n'herite
d'un statut de `main` : `main` n'est pas une reference, et rien n'y a ete rejoue ni revalide sur
cette branche.

Une precision s'imposait sur une ligne, et elle est desormais corrigee : le referentiel
`squad/programme/francais/competences-cp.md` portait une annotation heritee ("couvert par le
jeu Bandit manchot - Les syllabes, voir FICHE-francais-cp-syllabes.md, s02") qui datait de `main`.
Ce fichier de fiche, ce sprint `s02` et ce jeu n'ont jamais existe sur `round-two`. Cette
annotation avait ete ignoree par cette matrice depuis le sprint 0 (la ligne demarrait a
`non couvert` comme toutes les autres, voir `DEC-0001`), mais elle est restee ecrite dans le
referentiel lui-meme jusqu'a ce que `nadia-prof` la signale nommement dans son audit du
2026-08-06 (`squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`, "Suites hors de ma main") :
un referentiel qui declare couverte une competence qui ne l'est pas masque exactement le trou que
cette matrice existe pour rendre visible. L'annotation est retiree du referentiel a cette date ;
aucune autre annotation du meme genre n'a ete trouvee dans les trois autres fichiers de
`squad/programme/` apres relecture complete des quatre referentiels.

## Mathematiques - CP (cycle 2)

Source : `squad/programme/mathematiques/competences-cp.md`.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| mathematiques | CP | Nombres et calculs | Denombrer, lire, ecrire et decomposer les nombres jusqu'a 100 | - | - | non couvert |
| mathematiques | CP | Nombres et calculs | Comparer et ranger des nombres jusqu'a 100 (<, >, =) | bandit-manchot-comparer-nombres-cp | bandit manchot | en cours |
| mathematiques | CP | Nombres et calculs | Calcul mental - additions dans la limite de 10 (puis 20) | bandit-manchot-additions-cp | bandit manchot | couvert-valide |
| mathematiques | CP | Nombres et calculs | Calcul mental - soustractions dans la limite de 10 | bandit-manchot-soustractions-cp | bandit manchot | couvert-valide |
| mathematiques | CP | Nombres et calculs | Complements a 10 | bandit-manchot-complements-10-cp | bandit manchot | couvert-valide |
| mathematiques | CP | Nombres et calculs | Resoudre des problemes simples d'addition/soustraction en situation (1 etape) | bandit-manchot-problemes-additifs-cp | bandit manchot | couvert-valide |
| mathematiques | CP | Nombres et calculs | Reconnaitre et utiliser la monnaie (pieces/billets simples) dans des problemes concrets | - | - | non couvert |
| mathematiques | CP | Grandeurs et mesures | Comparer des longueurs, des masses, des contenances (sans instrument gradue) | - | - | non couvert |
| mathematiques | CP | Grandeurs et mesures | Se reperer dans la journee, la semaine ; lire l'heure pleine sur une horloge | - | - | non couvert |
| mathematiques | CP | Espace et geometrie | Se reperer et decrire des deplacements sur un quadrillage simple | - | - | non couvert |
| mathematiques | CP | Espace et geometrie | Reconnaitre et nommer quelques figures planes (carre, rectangle, triangle, cercle) | - | - | non couvert |

**Note du 2026-08-24 (s19, `camille-po`) - deux questions de validite de proxy dues depuis s13,
sans reponse a ce jour.** Les lignes "Denombrer, lire, ecrire et decomposer les nombres jusqu'a
100" et "Reconnaitre et nommer quelques figures planes" bloquent chacune sur une question posee a
`nadia-prof` par `DEC-0030` (section 5) le 2026-08-15 : un pictogramme-caractere suffit-il a
"denombrer" une quantite (ligne 1) ; un glyphe geometrique Unicode suffit-il a "reconnaitre" une
figure, ou faut-il un rendu reel (ligne 2). Six sprints se sont ecoules sans qu'aucun fichier
d'arbitrage n'y reponde - verifie par recherche des formulations exactes dans
`squad/conformite/` et `squad/decisions/produit/`, seul `DEC-0030` lui-meme les porte. Detail
complet et ce que je change pour que ce manque redevienne visible a chaque cloture :
`DEC-0042`, section 4.

**Amendement du 2026-08-24 (meme jour) - les deux questions sont tranchees par `nadia-prof`
pendant que la note ci-dessus s'ecrivait** (`squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-
proxys-en-attente-depuis-s13.md`, `DEC-0042` amendement du meme jour). La note ci-dessus reste
lisible telle qu'ecrite, a l'heure ou la reponse etait encore due.

- **Denombrer, lire, ecrire, decomposer jusqu'a 100** : **REFUSE sur "denombrer"** (un groupement
  par dix n'est pas controlable dans une chaine qui retourne a la ligne selon l'ecran, et le
  plafond reel de comptage a l'ecran est autour de dix objets pour une ligne qui va jusqu'a cent).
  **ACCEPTE SOUS CONDITIONS** (`DEN-1` a `DEN-8`) sur "lire, ecrire et decomposer", en couverture
  partielle nommee - candidat ouvrable sans arbitrage supplementaire des qu'une capacite de
  developpement lui est affectee, la ligne restant `en cours` meme si ce paquet est un jour
  valide (condition `DEN-2`, non negociable). "Denombrer" quitte la famille "contenu seul" et
  rejoint la famille **visuelle**, et n'y ouvrira qu'avec une interaction reelle sur la collection
  (deplacer, marquer, grouper), pas un simple dessin statique.
- **Reconnaitre et nommer des figures planes** : **REFUSE en entier**. La discrimination que la
  ligne demande (carre contre rectangle) tient a une proportion, et les proportions d'un glyphe
  sont celles de la police, pas du contenu - deja demontre en creux par
  `contenu-repris/associer/cp-formes.yml`, qui associe le glyphe carre a la cible "rectangle", seul
  materiau du depot sur cette competence. Cette ligne quitte entierement la famille "contenu seul"
  et rejoint la famille **visuelle**, sous conditions `FIG-1` a `FIG-4` (figures dessinees aux
  proportions declarees par le contenu, au moins trois exemples par forme variant orientation et
  taille, couleur jamais discriminante, cercle et disque jamais confondus).

Ligne reste `non couvert` sur les deux : aucune story n'est ouverte ce sprint, le perimetre de
developpement de s19 restant celui fixe par `DEC-0042`.

Note sur la ligne `en cours` (comparer et ranger) : `DEC-0019-perimetre-sprint-9-quatre-lignes-
textuelles-de-mathematiques-cp-denombrement-et-monnaie-differes.md` ouvre cette ligne au sprint 9
sur la seule comparaison de deux nombres par un signe (story S9-3, `docs/backlog/backlog.md`). Le
"ranger" nomme par cette meme competence (ordonner une suite de trois nombres ou plus) reste hors
du perimetre de ce jeu et ne suffit donc pas, a lui seul, a faire passer cette ligne en
`couvert-valide` meme si "comparer" est valide par `nadia-prof`.

**Etat au 2026-08-12, second audit** : `nadia-prof` leve son refus du 2026-08-12 sur
`bandit-manchot-comparer-nombres-cp` (`squad/conformite/FICHE-bandit-manchot-comparer-nombres-cp.md`,
verdict "second audit") et prononce **VALIDE SOUS RESERVE**, Done pedagogique **accorde sans
restriction sur son perimetre**. **La ligne reste `en cours`** : ce n'etait deja pas une condition
posee a l'audit, mais un perimetre pose a l'ouverture de la fiche, avant tout audit - "ranger" reste
differe par `DEC-0019` et hors de ce que ce jeu couvre. Elle demande explicitement que cette note le
dise, pour que la matrice ne perde pas une information que la fiche detient : **la moitie
"comparer" de cette competence est desormais validee pedagogiquement ; seule la moitie "ranger"
manque encore.** Aucun statut autre que `en cours` n'existe dans la nomenclature de cette matrice
pour porter cette nuance ; elle est donc ecrite en toutes lettres ici plutot que par un statut
invente.

Note sur les lignes `en cours` (soustractions, complements a 10, problemes) : `DEC-0019` les ouvre
au sprint 9 sur `docs/backlog/backlog.md`, stories S9-1, S9-2, S9-4, chacune sur un contenu neuf en
`type: question`, sans matiere premiere de `contenu-repris/` identifiee pour ces trois lignes.

Precision sur la ligne "complements a 10" (S9-2) : cette ligne a ete bloquee en planning, puis
debloquee par `DEC-0020-s9-2-debloquee-nombre-de-manches-abaisse-pour-les-complements-a-10.md`.
L'espace de la forme canonique de cette competence est fini a onze items au maximum (`ADR-0032`),
contre dix manches declarees pour les trois autres jeux du sprint : le jeu qui la couvre declare
`nombreDeManches: 7` au lieu de 10, une asymetrie de duree de partie assumee par cette decision
plutot qu'un elargissement des formes d'items, qui aurait ete une decision pedagogique.

Note sur les deux lignes qui restent `non couvert` (denombrer, monnaie) : `DEC-0019` les differe
explicitement plutot que de les laisser en silence, sur un motif corrige le 2026-08-12 (amendement
de `DEC-0019`, suite a l'avis de faisabilite de `theo-archi`) :

- **Denombrer** : un pictogramme-caractere (une face de de, un glyphe repete) est techniquement
  portable dans une chaine YAML ordinaire, sans code ni gabarit neuf, et `DEC-0002` place ce
  registre hors de la regle de redaction du depot. Ce qui reste non tranche, et qui garde cette
  ligne `non couvert` ce sprint, est pedagogique : la recevabilite d'un tel pictogramme pour tester
  reellement "denombrer" et la fiabilite de son rendu (un glyphe qui varie selon la police ou
  l'appareil devient une reponse fausse, pas seulement un defaut visuel) sont des jugements de
  `nadia-prof`, non encore rendus.
- **Monnaie** : le verbe "reconnaitre" designe une reconnaissance visuelle qu'aucun texte seul ne
  peut porter. Le verbe "utiliser", lui, est entierement textuel et prouve par du contenu deja joue
  en production sur `main` (`contenu-repris/quiz/cp-monnaie.yml`). Couvrir "utiliser" seul
  reproduirait neanmoins le motif de couverture partielle deja sanctionne sur ce produit (additions
  "jusqu'a 10 (puis 20)", homophones "principaux", conjugaison "un temps sur trois") : ce jeu
  n'exercerait jamais "reconnaitre". La ligne reste donc `non couvert` dans son ensemble.

Voir `DEC-0019` (decision d'origine et amendement du 2026-08-12) pour le detail complet, y compris
la revue de `contenu-repris/quiz/cp-monnaie.yml`, matiere premiere conservee pour une story future.

Note sur la ligne `en cours` (calcul mental additions) : le referentiel regroupe "jusqu'a 10 (puis
20)" en une seule competence. Le sprint 0 ne couvre que la borne jusqu'a 10 ; la borne jusqu'a 20
reste a traiter et ne suffit pas a elle seule a faire passer la ligne en `couvert-valide` meme si
la partie jusqu'a 10 est validee par Nadia.

Etat au sprint 0, apres la porte pedagogique : `nadia-prof` a prononce **REFUSE** sur
`squad/conformite/FICHE-bandit-manchot-additions-cp.md` (trois motifs bloquants sur le texte lu
par l'enfant et sur un ecran d'erreur, sept reserves non bloquantes). Le contenu mathematique
lui-meme etait deja juge exact et n'a jamais eu besoin de correction (61 manches auditees au
sprint 0, zero faute arithmetique).

Etat au sprint 1, apres la reprise de la porte pedagogique (2026-08-05) : les trois motifs de
refus sont leves, `nadia-prof` prononce **VALIDE SOUS RESERVE, Done pedagogique ACCORDE**. La
ligne **reste neanmoins `en cours`**, et non `couvert-valide` : `nadia-prof` motive explicitement
cet ecart dans son verdict - sur 171 manches reellement jouees, 43,9 % portent sur les sommes 9 ou
10 et 7,6 % seulement sur une somme inferieure ou egale a 4, ce qui revient a travailler surtout le
bord superieur de l'intervalle plutot que "les additions dans la limite de 10" dans leur ensemble.
Elle pose une condition ecrite pour que cette ligne passe a `couvert-valide` : cette repartition
corrigee. Le sprint 2 n'a pas touche a ce point (chantier d'identite visuelle uniquement,
`DEC-0006`) ; la condition est restee sans story jusqu'a `DEC-0007` et la story S3-1 du sprint 3
(`docs/backlog/backlog.md`), qui l'instruisent enfin, avec le seuil chiffre que `nadia-prof` devra
verifier a l'audit.

**Etat au 2026-08-06, audit de cloture de s03** : `nadia-prof` a joue 2 600 manches reelles sur
l'increment corrige (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section 1.1). Les
sommes 9 et 10 representent 34,2 % des manches (contre 43,9 % avant correction), les sommes
inferieures a 5 en representent 18,0 % (contre 7,6 %), les operandes nuls 13,7 %, et les 65 couples
valides sortent tous. **Sa condition est levee et la ligne passe a `couvert-valide`** - c'est la
premiere ligne de la matrice a atteindre ce statut. Reserve attachee, qui n'est pas une condition :
la mention "(puis 20)" de la competence reste hors du perimetre de ce jeu, dont la borne est 10 ;
si "puis 20" doit etre couvert, ce sera une autre story et un nouvel audit.

L'increment est suivi (Done pedagogique, pas la ligne de matrice) dans
`squad/validation/FILE-ATTENTE.md`.

## Mathematiques - CM1 (cycle 3)

Source : `squad/programme/mathematiques/competences-cm1.md`.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| mathematiques | CM1 | Nombres et calculs | Lire, ecrire, decomposer et comparer les nombres entiers jusqu'au million | bandit-manchot-grands-nombres-cm1 | bandit manchot | couvert-valide |
| mathematiques | CM1 | Nombres et calculs | Calcul mental - tables de multiplication (jusqu'a ×10), rappel automatise | bandit-manchot-tables-multiplication-cm1 | bandit manchot | couvert-valide |
| mathematiques | CM1 | Nombres et calculs | Calcul mental - multiplications et divisions simples (multiples de 10, 100) | bandit-manchot-multiplications-divisions-cm1 | bandit manchot | en cours |
| mathematiques | CM1 | Nombres et calculs | Effectuer une multiplication posee d'un nombre a 2-3 chiffres par un nombre a 1-2 chiffres | - | - | non couvert |
| mathematiques | CM1 | Nombres et calculs | Effectuer une division euclidienne simple (quotient et reste) par un diviseur a 1 chiffre | - | - | non couvert |
| mathematiques | CM1 | Nombres et calculs | Notion de fraction simple (unites fractionnaires, fractions inferieures a 1) et reperage sur une droite numerique | - | - | non couvert |
| mathematiques | CM1 | Nombres et calculs | Resoudre des problemes a plusieurs etapes mobilisant les 4 operations | - | - | non couvert |
| mathematiques | CM1 | Grandeurs et mesures | Convertir des unites de longueur, masse, contenance dans le systeme metrique | bandit-manchot-conversions-unites-cm1 | bandit manchot | couvert-valide |
| mathematiques | CM1 | Grandeurs et mesures | Calculer un perimetre de figures usuelles (carre, rectangle) | bandit-manchot-perimetre-cm1 | bandit manchot | couvert-valide |
| mathematiques | CM1 | Grandeurs et mesures | Resoudre des problemes de durees (calcul d'une duree, d'un horaire d'arrivee) | bandit-manchot-problemes-durees-cm1 | bandit manchot | en cours |
| mathematiques | CM1 | Espace et geometrie | Reconnaitre, nommer et tracer des figures planes (carre, rectangle, triangle, cercle) avec outils (regle, equerre, compas) | - | - | non couvert |
| mathematiques | CM1 | Espace et geometrie | Reconnaitre et utiliser la notion de symetrie axiale simple | - | - | non couvert |

Note sur les trois lignes `en cours` (tables de multiplication, conversions d'unites, perimetre) :
`DEC-0029-perimetre-sprint-12-trois-lignes-mathematiques-cm1-ouvertes-cinq-differees.md` les ouvre
au sprint 12 sur `docs/backlog/backlog.md`, stories S12-1 a S12-3, chacune sur un contenu neuf en
`type: question` (aucune matiere premiere de `contenu-repris/` n'est chargeable telle quelle - voir
`DEC-0029` pour le detail par ligne). Les criteres d'ouverture excluent explicitement les items hors
perimetre exact de chaque competence (triangle sur le perimetre ; estimation ou instrument sur les
conversions), pour ne pas faire l'inverse du defaut deja sanctionne sur ce produit - un paquet qui
deborde du verbe qu'il pretend couvrir plutot qu'un paquet qui n'en couvre qu'une part.

Note sur les cinq lignes qui restent `non couvert` en "Nombres et calculs" et "Grandeurs et
mesures" (hors les deux structurellement hors d'atteinte de "Espace et geometrie", inchangees) :
`DEC-0029` les differe explicitement.

- **Multiplications et divisions simples (multiples de 10, 100)**, **problemes de durees** et
  **problemes a plusieurs etapes (4 operations)** restent `non couvert` pour capacite : le sprint
  12 porte deja trois ouvertures, la reverification de `nadia-prof` sur le declencheur de
  `DEC-0026` et deux corrections de garde-fous. Aucune de ces trois lignes n'est structurellement
  hors d'atteinte de la mecanique ; elles sont candidates prioritaires du prochain sprint qui ouvre
  du contenu.
- **"Lire, ecrire, decomposer et comparer les grands nombres"** et **"notion de fraction simple...
  et reperage sur une droite numerique"** restent `non couvert` dans l'attente d'un arbitrage
  prealable de `nadia-prof` : chacune bundle un verbe servable sans ambiguite ("decomposer",
  "comparer" ; "notion de fraction simple") avec un verbe qui ne peut etre servi qu'au prix d'un
  proxy textuel ("lire"/"ecrire" un nombre par transcription chiffres/lettres ; "reperage sur une
  droite numerique" par un encadrement textuel) dont la validite pedagogique n'est pas mienne a
  juger. Les deux questions exactes posees a `nadia-prof` sont dans `DEC-0029`, section "Ce que je
  demande a nadia-prof avant le code".

**Note sur la ligne `couvert-valide` (tables de multiplication) - couverture partielle a ne pas
perdre.** `nadia-prof` a leve son refus du 2026-08-14 le 2026-08-15 (voir "Synthese apres les seconds
verdicts du sprint 12" ci-dessous) et la ligne porte desormais ce statut, **avec la mention
obligatoire "sans mesure d'automatisation"** qu'elle a posee des le refus et n'a pas rouverte a la
levee : la competence du referentiel dit "rappel **automatise**", l'automatisation est une affaire
de **vitesse**, et **ce produit ne mesure aucun temps de reponse**. Le paquet verifie que l'enfant
**sait** ses tables, pas qu'il les a automatisees. Ce n'est pas un reproche au paquet - savoir ses
tables est la condition pour les automatiser, et s'entrainer est le bon usage de ce jeu - mais c'est
une restriction reelle de perimetre, a porter avec ces mots chaque fois que cette ligne est citee,
pas a laisser dans l'ombre d'un statut vert.

**Arbitrage rendu le 2026-08-14** (`squad/conformite/ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`,
sections 1 et 2), sur les deux questions ci-dessus. Aucune des deux lignes n'est ouverte par cet
arbitrage lui-meme - ce n'est pas une story, et `nadia-prof` ne se prononce jamais sur un
increment qu'elle n'a pas joue - mais chacune recoit une reponse ecrite qui borne ce qu'une story future pourra
couvrir :

- **"Lire, ecrire, decomposer et comparer les grands nombres"** : le proxy de transcription
  chiffres/lettres est **VALIDE** par `nadia-prof` pour "lire" et "ecrire". Consequence pour la
  matrice : le jour ou `camille-po` ecrit la story, **la ligne peut ouvrir en entier**, sans mention
  de couverture partielle - les quatre verbes du referentiel (lire, ecrire, decomposer, comparer)
  sont alors tous servables par le schema `type: question` existant. Reste `non couvert` a ce jour,
  faute de story ecrite ce sprint (capacite, voir ci-dessus).
- **"Notion de fraction simple... et reperage sur une droite numerique"** : le proxy d'encadrement
  entre deux entiers (par exemple "entre quels deux entiers se trouve 7/4 ?") est **REFUSE** pour
  "reperage sur une droite numerique". Motif arithmetique, pas une opinion sur l'exercice : dans le
  perimetre exact que nomme le referentiel ("fractions **inferieures a 1**"), toute fraction du
  paquet serait encadree entre 0 et 1, donc tout item d'encadrement aurait **la meme reponse** -
  un exercice vide, pas un exercice facile. `nadia-prof` ajoute un motif de fond, independant de
  l'arithmetique : "reperer sur une droite numerique" est une competence de representation (une
  fraction a une place, qu'on trouve en partageant l'unite et en lisant une graduation), qu'aucun
  encadrement textuel ne travaille - "cette competence exige une ligne reellement dessinee et
  graduee". **Couverture partielle nommee**, sur le modele de `DEC-0019` : la moitie "notion de
  fraction simple" (nommer/reconnaitre une fraction, l'associer a son ecriture et a sa
  denomination) est entierement servable sans proxy et **pourrait ouvrir seule**, en couverture
  partielle explicite, si une story lui est ecrite ; la moitie "reperage sur une droite numerique"
  **reste non couverte** et le restera tant qu'aucun mecanisme ne dessine reellement une ligne
  numerique - une question d'architecture, a router a `theo-archi` le jour ou elle se pose. La
  ligne entiere reste `non couvert` a ce jour : aucune story n'est ecrite ce sprint sur la moitie
  ouvrable.

  Remarque versee au dossier, sans suite ce sprint : `nadia-prof` signale que la restriction
  "fractions inferieures a 1" du referentiel `squad/programme/mathematiques/competences-cm1.md`
  est plus etroite que ce qu'un CM1 travaille reellement (les fractions superieures a 1 et leur
  encadrement entre deux entiers sont au programme). Elle precise que si ce perimetre est elargi,
  l'encadrement redevient examinable pour la partie qui depasserait 1 - mais que son avis de fond
  sur le reperage visuel ne changerait pas pour autant. Aucun arbitrage du referentiel n'est fait
  ici ; la question reste ouverte pour l'utilisatrice si elle souhaite l'instruire.

## Perimetre de s13 (2026-08-15)

`DEC-0030-perimetre-sprint-13-trois-lignes-mathematiques-cm1-et-le-plafond-de-la-mecanique-
unique.md` : trois lignes de mathematiques CM1 differees pour capacite au sprint 12 entrent au
sprint 13 - stories S13-1 a S13-3, `docs/backlog/backlog.md`. **Ces trois lignes passent `en cours`
par ce planning**, jeu et mecanique renseignes dans le tableau ci-dessus. **Aucune ligne ne passe a
`couvert-valide` par ce planning** : ce statut n'est prononce que par `nadia-prof`, apres audit reel
de chaque increment, sur les trois fiches de conformite ouvertes dans le meme geste que `DEC-0030`.

- **Lire, ecrire, decomposer et comparer les nombres entiers jusqu'au million** ouvre **en entier**,
  sans mention de couverture partielle : l'arbitrage prealable rendu par `nadia-prof` au sprint 12
  (`squad/conformite/ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`, section 1) valide le proxy de
  transcription chiffres/lettres pour "lire" et "ecrire" sous deux conditions (GN-1, GN-2), et
  "decomposer"/"comparer" sont servables sans aucun proxy.
- **Calcul mental - multiplications et divisions simples (multiples de 10, 100)** et **resoudre des
  problemes de durees** ouvrent sur un contenu jamais instruit a ce niveau de detail : `DEC-0030`
  demande a `nadia-prof` un arbitrage prealable sur ces deux lignes avant tout code, sur le meme
  modele que le sprint 12. Les criteres poses dans les stories S13-2 et S13-3 sont ceux de
  `camille-po` et n'ont pas valeur de jugement pedagogique tant que cet arbitrage n'est pas rendu.

**Deux lignes de plus restent `non couvert`, differees avec un motif ecrit** (`DEC-0030`, sections 1
et 3) :

- **Resoudre des problemes a plusieurs etapes mobilisant les 4 operations** : **second report pour
  capacite** sur cette meme ligne, deja differee une premiere fois au sprint 12 par `DEC-0029`. Motif
  inchange (aucune matiere premiere, paquet le plus expose au risque de refus sur la variete et la
  coherence des enonces, meme famille que la reserve de `DEC-0021`).
- **Notion de fraction simple (unites fractionnaires, fractions inferieures a 1) et reperage sur une
  droite numerique** : **premier report pour capacite**, l'arbitrage qui debloquait sa moitie
  "notion" (`ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`, section 1) n'ayant ete rendu que le
  2026-08-14, apres la cloture du sprint 12. Candidate en couverture partielle nommee (moitie
  "notion" seule, sans proxy) pour un sprint suivant.

**Note sur le plafond de la mecanique unique** (`DEC-0030`, section 4) : sur les 35 lignes `non
couvert` avant ce planning, une relecture ligne par ligne, verifiee contre ce que le schema
`type: question` peut porter sans code neuf, denombre environ 19 lignes entieres et une partielle
servables par du contenu seul (dont 4 sous reserve d'un jugement pedagogique prealable de
`nadia-prof`, pas d'un obstacle technique), 6 lignes entieres et une partielle qui demandent une
mecanique visuelle neuve, 5 qui demandent une saisie, et 3 qui demandent de l'audio (plus une
quatrieme, "lire a voix haute", qui demanderait en plus une reconnaissance vocale). `camille-po`
n'ouvre aucune ligne de mecanique neuve ce sprint et estime a quatre a six sprints la marge de
contenu seul restante avant que cette mecanique unique ne devienne bloquante pour le reste du
programme.

**Etat au 2026-08-15, premier audit de l'increment S13-1** : `nadia-prof` prononce **REFUSE**
(`squad/conformite/FICHE-bandit-manchot-grands-nombres-cm1.md`, verdict du 2026-08-15) sur deux
motifs : le verbe "decomposer" n'est pas reellement travaille par les sept items qui le portent
(les distracteurs ne font varier que le premier terme de la decomposition, et trois items
opposent un chiffre substitue plutot qu'une erreur de rang) et deux indices sont faux sur l'item
`600 040`. La ligne **reste `en cours`** ; elle ne pouvait de toute facon pas atteindre
`couvert-valide` sur ce premier increment.

**Etat au 2026-08-16, second audit et retrait du catalogue jouable (sprint 13)** : `nadia-prof`
reaudite le lot de reprise et prononce **REFUSE une seconde fois**, sur **un motif neuf et un
seul** : ses deux motifs du 2026-08-15 sont **leves** sans reserve, mais a 360 px (largeur de
reference du produit, `DEC-0016`), le separateur de milliers rendu insecable produit des jetons
numeriques plus larges que leur colonne d'affichage (96,0 px) - jusqu'a 25,2 px de debord par
cote sur `1 000 000`, dont la bonne reponse se lit alors `000 000`. Une erreur de fait montree a
l'ecran est pour elle un motif de refus a elle seule, meme quand la cause est un rendu et non le
contenu. **Le contenu, lui, est juge bon** : les neuf criteres pedagogiques de `camille-po` sont
desormais tenus, les quatre verbes sont travailles a parts egales, les 28 bonnes reponses et les
28 indices sont exacts, et les 14 distracteurs de decomposition que son garde-fou de rang atteint
sont 14 erreurs reelles de valeur de position. Elle l'ecrit elle-meme : "si le rendu est corrige
sans toucher au YAML, je n'aurai plus rien a objecter sur le fond."

Sur decision de l'utilisatrice du 2026-08-16, le jeu **quitte le catalogue jouable**
(`DEC-0031-retrait-les-grands-nombres-du-catalogue-debordement-de-rendu.md`). **La ligne reste
`en cours`** : elle ne descend pas d'un niveau comme la ligne homophones au sprint 10 (`DEC-0024`),
qui partait de `couvert-valide` - celle-ci partait deja d'`en cours` et n'a jamais atteint
`couvert-valide`. La nuance a porter, faute d'un mot de plus dans la nomenclature de cette
matrice : cet "en cours" recouvre un contenu acheve et juge pedagogiquement bon sur ses neuf
criteres, bloque par un seul defaut de rendu, et non un contenu encore a l'etude ou refuse sur le
fond - ni `non couvert` (le contenu existe, cible la competence, et `nadia-prof` a ecrit qu'elle
n'aurait plus rien a objecter dessus une fois le rendu corrige), ni `couvert-valide` (aucun
verdict VALIDE n'a ete rendu, et aucun jeu jouable ne sert la competence aujourd'hui). Condition
de retour, executable et reprise dans les mots de `nadia-prof` : que les propositions du paquet
tiennent dans leur colonne a 360 px, sans qu'aucun chiffre sorte du bouton ni de l'ecran
(`DEC-0031`, section 3). Le defaut de rendu qui bloque ce retour ne lui est pas propre : sept des
vingt-huit items debordaient deja de leur colonne avant ce lot, a cause de mots francais longs
sans rapport avec le separateur de milliers, et la meme colonne de 96 px sert les onze jeux du
catalogue actuel. La dette est generale et attend le chiffrage de `theo-archi` sur les voies de
correction avant qu'une story ne soit ecrite (`DEC-0031`, section 4).

**Amendement du 2026-08-16 de `DEC-0031`.** `nadia-prof` a tranche la voie de correction (voie 4,
`squad/conformite/ARBITRAGE-PREALABLE-s13-voie-de-rendu-des-propositions.md`), livree par `lea-dev`
et fusionnee dans `round-two`. Elle pose un prealable que la decision d'origine ne prevoyait pas :
elle ne peut pas verifier "Les grands nombres" tant qu'il n'a pas de chemin pour etre joue, et sa
remise au catalogue est donc un prealable a sa reverification, pas une consequence de son arbitrage.
`DEC-0031` est amendee pour lever ce blocage circulaire : le jeu ne revient pas dans le
`catalogue.yaml` joue par un enfant, mais dans un chemin de verification dedie et jamais fusionne,
que `nadia-prof` joue comme tout autre increment. **La ligne reste `en cours`**, dans les memes
termes qu'a l'ouverture de cette section : aucun retour de chemin ni aucune fusion de code ne vaut
Done pedagogique, seul un verdict joue et ecrit dans
`squad/conformite/FICHE-bandit-manchot-grands-nombres-cm1.md` le peut. Detail complet, y compris ce
qui se passe en cas de second refus : `DEC-0031`, section "Amendement du 2026-08-16".

Le meme arbitrage rouvre et refuse une seconde fois le Done de la ligne "Calculer un perimetre de
figures usuelles (carre, rectangle)" : sur `bandit-manchot-perimetre-cm1`, un item sur 22 percute sa
bonne reponse contre la colonne voisine, motif identique a celui qui a fait sortir "Les grands
nombres" du catalogue. **La ligne repasse de `couvert-valide` a `en cours`** dans le tableau
ci-dessus. A la difference de "Les grands nombres", ce jeu **reste au catalogue jouable** : le
correctif qui ferme ce motif (la voie 4) est deja fusionne dans `round-two`, et `nadia-prof` ne
demande pas le retrait - retirer puis remettre le jeu au premier verdict favorable n'aurait pas de
sens operationnel. Ce que cette ligne `en cours` porte, exceptionnellement, differe donc de sa
soeur "Les grands nombres" : un jeu **joue aujourd'hui par un enfant** avec un Done **rouvert**, sur
le pari que le code deja livre referme le motif de refus - constat qui reste a confirmer par un
nouveau verdict de `nadia-prof`, pas par la fusion du correctif seule. Detail : `DEC-0031`, section
"Amendement du 2026-08-16", point 5 ; l'increment entre dans `squad/validation/FILE-ATTENTE.md`.

**Etat au 2026-08-16 (soir), verification apres livraison de la voie 4 - VALIDE SOUS RESERVE, "Les
grands nombres" passe a `couvert-valide`.** `nadia-prof` a rejoue `bandit-manchot-grands-nombres-cm1`
depuis un worktree dedie, sur la tete de `round-two` en reinstallant l'entree de catalogue pour
pouvoir le jouer par l'ecran reel
(`squad/conformite/FICHE-bandit-manchot-grands-nombres-cm1.md`, verdict "verification apres
livraison de la voie 4"). **Le refus du 2026-08-16 est leve** : 0 debordement sur les 28 items,
contre 20 sur 28 sur l'etat refuse, mesure sur les deux etats en parallele plutot que suppose. Le
Done pedagogique est **VALIDE SOUS RESERVE**. **La ligne mathematiques CM1 "Lire, ecrire, decomposer
et comparer les nombres entiers jusqu'au million" passe de `en cours` a `couvert-valide`**, ouverte
en entier, sans mention de couverture partielle : les quatre verbes restent tous servis dans les
proportions posees a l'ouverture de la fiche. Reserve non bloquante a porter avec ce statut -
**reserve J** : a 360 x 640, huit items sur 28 placent une proposition entierement sous le pli (les
cinq items "lire" et trois des sept items "decomposer", ceux dont les propositions sont les plus
longues), la bonne reponse y etant hors champ au premier coup d'oeil sur ces items environ une
manche sur dix. `nadia-prof` juge que ce n'est pas un motif de refus (un choix qu'on atteint en
defilant n'est pas un texte qu'on ne peut pas lire, tant qu'aucune boite ne parait completement
absente), mais une story de rendu reste a ecrire, commune a cette ligne et a "Le perimetre"
ci-dessous. **L'entree de catalogue `id: bandit-manchot-grands-nombres-cm1` est deja recablee sur
`round-two`** (`a948785`, `sofia-sm`, methode de test de chargement restituee dans le meme commit) :
le jeu est aujourd'hui reellement jouable, ce n'est pas un statut vert en attente d'une action
technique.

**Etat au 2026-08-16 (soir), meme verification - VALIDE SOUS RESERVE, "Le perimetre" revient a
`couvert-valide`.** Sur le meme lot de verification (voie 4 livree), `nadia-prof` rejoue
`bandit-manchot-perimetre-cm1` et leve son refus du 2026-08-16 sur l'item
`Le perimetre d'une figure, c'est...` (0 debordement contre 1 sur 22 sur l'etat refuse)
(`squad/conformite/FICHE-bandit-manchot-perimetre-cm1.md`, verdict du 2026-08-16, soir). **La ligne
mathematiques CM1 "Calculer un perimetre de figures usuelles (carre, rectangle)" repasse de
`en cours` a `couvert-valide`**, et `bandit-manchot-perimetre-cm1` sort de
`squad/validation/FILE-ATTENTE.md`. Une reserve neuve et non bloquante est versee au dossier par
`nadia-prof` elle-meme, qui corrige un de ses propres constats du matin : sur ce jeu, presser la
plus grande proposition gagne desormais 48,5 % des manches (les colonnes n'ayant plus la meme
largeur sous la voie 4), contre 33,3 % au hasard - meme famille que la reserve deja ouverte sur "Les
mesures" (candidate de story au backlog, section "Backlog - sprints suivants" de
`docs/backlog/backlog.md`), et non une condition de ce statut.

## Francais - CP (cycle 2)

Source : `squad/programme/francais/competences-cp.md`.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| francais | CP | Langage oral et conscience phonologique | Denombrer les syllabes orales d'un mot (segmentation syllabique) | - | - | non couvert |
| francais | CP | Langage oral et conscience phonologique | Localiser une syllabe dans un mot (debut / milieu / fin) | - | - | non couvert |
| francais | CP | Langage oral et conscience phonologique | Identifier un son (phoneme) dans un mot | - | - | non couvert |
| francais | CP | Lecture et comprehension de l'ecrit | Associer les graphemes aux phonemes (voyelles, consonnes simples, puis digraphes ch, ou, on...) | bandit-manchot-le-bon-son-cp | bandit manchot | en cours |
| francais | CP | Lecture et comprehension de l'ecrit | Decoder un mot regulier inconnu par assemblage syllabique | - | - | non couvert |
| francais | CP | Lecture et comprehension de l'ecrit | Lire a voix haute un texte court et simple avec fluidite croissante | - | - | non couvrable en l'etat |
| francais | CP | Lecture et comprehension de l'ecrit | Comprendre un texte narratif tres court (questions explicites : qui, ou, quoi) | - | - | non couvert |
| francais | CP | Ecriture | Copier un mot, une phrase courte sans erreur, en respectant l'espacement | - | - | non couvert |
| francais | CP | Ecriture | Ecrire de maniere autonome un mot simple en respectant les correspondances grapheme/phoneme connues | - | - | non couvert |
| francais | CP | Orthographe et grammaire | Reconnaitre et utiliser les marques du pluriel les plus frequentes (-s) | - | - | non couvert |
| francais | CP | Orthographe et grammaire | Distinguer les principaux types de mots (nom, verbe) dans une phrase tres simple | bandit-manchot-nature-mot-cp | bandit manchot | en cours |
| francais | CP | Vocabulaire | Utiliser des mots precis pour designer des objets, actions, sensations du quotidien | - | - | non couvert |

**Note du 2026-08-24 (s19, `camille-po`) - arbitrage de l'utilisatrice : statut `non couvrable en
l'etat` sur "Lire a voix haute...".** Cette competence exige une reconnaissance vocale, hors
d'atteinte de ce produit tel qu'il est defini (trois contraintes fondatrices contredites a la fois,
plus une question de donnee personnelle sur la voix d'un enfant). Motif complet, sources et
consequence sur le denominateur de couverture : `DEC-0042`, section 3.

**Note du 2026-08-24 (s19, `camille-po`) - ligne "Distinguer les principaux types de mots" ouverte
par `S19-4`** (`docs/backlog/backlog.md`) : adaptation reelle de `contenu-repris/mots/
cm1-nature-mot.yml` (quatre natures, mots isoles, niveau CM1) vers deux natures (nom, verbe) dans
une phrase tres simple, niveau CP - ce n'est pas une reprise au sens de `contenu-repris/README.md`.
Arbitrage prealable de `nadia-prof` demande avant tout code (`DEC-0042`, section 5).

**Amendement du 2026-08-24 (meme jour, second passage) - increment audite, deux reserves
bloquantes, la ligne reste `en cours`.** `nadia-prof` a joue l'increment livre (`FICHE-bandit-
manchot-nature-mot-cp.md`, verdict `ddb0fc4`) : le motif de son refus de la premiere version des
seize phrases est leve et remesure (`NAT-1` a `NAT-8` tenues, sauf deux). Deux reserves restent
**bloquantes** : `NAT-5` (aucun renfort visuel du mot cible, au-dela des guillemets seuls, n'a ete
propose ni ajoute - je l'avais routee a `ines-design`, qui n'a pas ete saisie de ce point, trou de
coordination que je corrige ci-dessous) et `NAT-9`, neuve (la consigne servie, "Le mot entre
guillemets est-il un nom ou un verbe ?", n'est pas lisible seul par un enfant de CP et est
circulaire - `nadia-prof` propose "Nom ou verbe ?", deja le titre du jeu). Elle ecrit que les deux
se reparent ensemble. Detail complet, criteres correctifs et routage : `DEC-0044`.

**Note du 2026-08-24 (s19, `camille-po`) - question de validite de proxy due depuis s13, sans
reponse a ce jour.** La ligne "Decoder un mot regulier inconnu par assemblage syllabique" bloque
sur une question posee a `nadia-prof` par `DEC-0030` (section 5) le 2026-08-15 : un choix a trois
propositions entre des decoupages syllabiques ecrits satisfait-il le verbe "decoder", ou cette
competence exige-t-elle que l'enfant assemble lui-meme les syllabes ? Six sprints se sont ecoules
sans reponse ecrite - verifie par recherche de la formulation exacte ("assemblage syllabique") dans
`squad/conformite/` et `squad/decisions/produit/`, seul `DEC-0030` lui-meme la porte. Detail
complet : `DEC-0042`, section 4.

**Amendement du 2026-08-24 (meme jour) - la question est tranchee par `nadia-prof`**
(`squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`, section 3).
**REFUSE** : le proxy ecrit teste la segmentation (reconnaitre une coupe deja faite), pas
l'assemblage que la ligne nomme, et un choix parmi des mots ecrits affiche toujours le mot qui doit
rester inconnu - le defaut est structurel, pas reparable par une regle d'ecriture des items. Cette
ligne quitte la famille "contenu seul" et rejoint la famille **audio, par emission de son
seulement** (`SYL-1` a `SYL-4` : mot rare ou pseudo-mot jamais rencontre, propositions **entendues**
et non lues, reecoute libre, aucune manche chronometree) - **explicitement sans reconnaissance
vocale**, a la difference de "Lire a voix haute...". Elle rejoint ainsi les trois lignes de
conscience phonologique deja hors perimetre de s19 (arbitrage 2 de `DEC-0042`) sous le meme motif
de production (aucun agent de cette squad ne peut fournir un enregistrement de voix humaine), la
famille audio comptant desormais quatre lignes et non trois. Ligne reste `non couvert` : aucune
story n'est ouverte ce sprint.

Note sur la ligne `en cours` : `DEC-0008-la-couverture-reprend-francais-cp-et-cm1.md` ouvre cette
competence au sprint 4, sur la matiere premiere `contenu-repris/mots/cp-le-bon-son.yml` (story
S4-2, `docs/backlog/backlog.md`).

**Etat au 2026-08-06, premier audit** : `nadia-prof` prononce **REFUSE**
(`squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`) sur cinq motifs cumules : une consigne
qui annonce un son qui n'est jamais joue ; des crochets phonetiques `[an]` montres a l'enfant ; 23
items sur 24 resolubles par simple appariement visuel des lettres, sans passer par le phoneme ;
trois items qui admettent deux reponses vraies mais n'en acceptent qu'une ; et aucune aide
disponible dans ce jeu, motif qu'elle qualifie de structurel plutot que d'oubli de contenu (un
paquet `type: question` ne peut porter d'indice, contrairement a `type: additions-bornees`). La
ligne **reste `en cours`**. Le jeu est retire du catalogue jouable en attendant sa correction
(`DEC-0009-retrait-le-bon-son-du-catalogue.md`) ; stories de correction S5-2 a S5-5 de
`docs/backlog/backlog.md`.

**Etat au 2026-08-10, audit de cloture de s06** : `nadia-prof` prononce **VALIDE SOUS RESERVE**,
Done pedagogique accorde (`squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`, verdict du
2026-08-10). **Le REFUSE du 2026-08-06 est leve sur ses cinq motifs**, apres recomposition
integrale du paquet (S6-6, S6-7) autour des seules trois voyelles nasales `an`, `on`, `in`. La
ligne **reste `en cours`** : la competence du referentiel nomme aussi les voyelles simples, les
consonnes simples et les digraphes `ch`, `ou`, `oi`, que ce paquet ne travaille pas et ne peut pas
travailler sans qu'un son soit reellement joue (`nadia-prof` ferme elle-meme cette piste le
2026-08-06 : "sans son, un digraphe transparent ne peut donner lieu qu'a une recherche de
lettres"). Sa condition ecrite pour `couvert-valide` (meme fiche, verdict du 2026-08-10, section
6) : les voyelles et consonnes simples couvertes par ce jeu ou un autre ; `ou`, `oi` et `ch`
couverts par un jeu qui emet un son ; les trois nasales gardent les proprietes deja acquises. Le
jeu peut rejoindre le catalogue (`DEC-0009` est levee de fait par ce verdict, `sofia-sm` fusionne
l'entree de catalogue).

## Francais - CM1 (cycle 3)

Source : `squad/programme/francais/competences-cm1.md`.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| francais | CM1 | Lecture et comprehension de l'ecrit | Lire silencieusement un texte de longueur croissante et en restituer l'essentiel | - | - | non couvert |
| francais | CM1 | Lecture et comprehension de l'ecrit | Comprendre un texte narratif ou documentaire (questions explicites et implicites, inferences simples) | - | - | non couvert |
| francais | CM1 | Lecture et comprehension de l'ecrit | Reperer les substituts d'un personnage (pronoms, groupes nominaux) dans un texte | - | - | non couvert |
| francais | CM1 | Ecriture | Rediger un texte narratif court et coherent (quelques phrases, temps du recit respecte) | - | - | non couvert |
| francais | CM1 | Ecriture | Relire et corriger son texte a l'aide de reperes simples (majuscules, ponctuation) | - | - | non couvrable en l'etat |
| francais | CM1 | Orthographe et grammaire | Accorder le verbe avec son sujet (cas frequents, temps simples : present, futur, imparfait) | bandit-manchot-accord-sujet-verbe-cm1 | bandit manchot | couvert-valide |
| francais | CM1 | Orthographe et grammaire | Accorder en genre et en nombre dans le groupe nominal (determinant/nom/adjectif) | bandit-manchot-accords-groupe-nominal-cm1 | bandit manchot | couvert-valide |
| francais | CM1 | Orthographe et grammaire | Distinguer et utiliser correctement les principaux homophones grammaticaux (et/est, son/sont, a/à...) | bandit-manchot-homophones-cm1 | bandit manchot | couvert-valide |
| francais | CM1 | Orthographe et grammaire | Conjuguer les verbes frequents au present, futur et imparfait de l'indicatif | bandit-manchot-conjugaison-present-cm1 | bandit manchot | couvert-valide |
| francais | CM1 | Vocabulaire | Utiliser des synonymes et antonymes pour preciser ou nuancer un propos | bandit-manchot-synonymes-cm1, bandit-manchot-antonymes-cm1 | bandit manchot | couvert-valide |
| francais | CM1 | Vocabulaire | Utiliser a bon escient des mots de liaison simples pour structurer un texte court | - | - | non couvert |

**Note du 2026-08-24 (s19, `camille-po`) - trois lignes ouvertes par le Lot A de `DEC-0042`**
(`docs/backlog/backlog.md`, section "Sprint 19") :

- **Accorder le verbe avec son sujet** (`S19-3`) : paquet neuf integralement, `contenu-repris/
  textes-trous/cm1-conjugaison.yml` etant hors sujet (il teste la conjugaison au present avec sujet
  pronominal colle au verbe, deja servie par `cm1-conjugaison-present.yaml`, pas l'accord avec un
  sujet eloigne ou compose).
- **Accorder en genre et en nombre dans le groupe nominal** (`S19-2`) : `contenu-repris/
  textes-trous/cm1-accords.yml` (5 items) ne teste que l'accord de l'adjectif, determinant et nom
  etant deja fixes dans chaque phrase - le paquet neuf doit couvrir les trois composantes du groupe
  nominal, sous peine de nommer explicitement la limite des l'ouverture (voir la story pour le
  detail).
- **Synonymes et antonymes** : `S19-1` ouvre un second jeu (`bandit-manchot-antonymes-cm1`),
  distinct de `bandit-manchot-synonymes-cm1`, motif de forme et pedagogique detaille dans la story.
  **Cette ligne ne passe a `couvert-valide` que le jour ou les deux jeux sont chacun `VALIDE` par
  `nadia-prof`** - le verdict deja rendu sur "Les synonymes" seul (`VALIDE SOUS RESERVE, aucune
  reserve bloquante`, s18) ne suffit toujours pas a lui seul (`DEC-0039`, section 3, confirmee
  inchangee).

Aucune des trois lignes ne passe a `couvert-valide` par ce planning : ce statut n'est prononce que
par `nadia-prof`, apres audit reel de chaque increment.

**Amendement du 2026-08-24 (meme jour, second passage) - deux des trois lignes passent
`couvert-valide`, verifie par `camille-po` avant de faire bouger la ligne**

`nadia-prof` a audite les quatre paquets neufs (branche `s19-nadia`, commit `ddb0fc4`, non
fusionnee). Je verifie moi-meme la condition de chaque ligne plutot que de la croire sur parole
(`squad/README.md`, section 9, etape 6), avant de faire bouger quoi que ce soit.

- **Synonymes et antonymes -> `couvert-valide`.** La condition posee par `DEC-0039` section 3 est
  : la ligne ne passe que le jour ou **les deux jeux sont chacun `VALIDE` par `nadia-prof`**. Verifie
  : `bandit-manchot-synonymes-cm1` est `VALIDE SOUS RESERVE, aucune reserve bloquante` depuis s18
  (`FICHE-bandit-manchot-synonymes-cm1.md`) ; `bandit-manchot-antonymes-cm1` recoit le meme verdict
  le 2026-08-24 (`FICHE-bandit-manchot-antonymes-cm1.md`, verdict `ddb0fc4` : 17 items, 4 parties,
  17/17 items vus a 744 x 551, verdict au pire 474,19 px, deux reserves non bloquantes `ANT-8` et
  `ANT-9`). Les deux conditions de `DEC-0039` sont tenues. **Perimetre exact de ce que ce
  `couvert-valide` recouvre** : les deux jeux ensemble ; aucun residu non couvert par les deux
  fiches - `ANT-8` (un item perd la fin de son indice sous le pli, 2,19 px, non bloquant, meme
  mecanisme que `SYN-12`) et `SYN-16`/`S19-6a` (bande 576-719 px, residu de largeur assume, renvoye
  a s20 par `DEC-0043`) restent ouverts et non bloquants, sans effet sur ce statut.
- **Accorder en genre et en nombre dans le groupe nominal -> `couvert-valide`.** Un seul jeu, la
  regle standard s'applique (`squad/README.md`, section 9, etape 6) : Done `VALIDE SOUS RESERVE,
  aucune reserve bloquante` prononce le 2026-08-24 (`FICHE-bandit-manchot-accords-groupe-nominal-
  cm1.md`, verdict `ddb0fc4` : 24 items, 6 parties, 24/24 items vus a 744 x 551, verdict au pire
  510,19 px). **Perimetre exact** : les trois composantes du groupe nominal (determinant, nom,
  adjectif) sont chacune testees, huit items chacune, comme la story l'exigeait des l'ouverture.
  Trois reserves non bloquantes restent ouvertes : `AGN-1` (huit items perdent leur indice sous le
  pli, meme mecanisme que `SYN-12`, verse au chantier vertical de s20 avec un chiffre revise -
  68,19 px et non 43,19 px, les items a quatre propositions coutant plus cher que les items a
  trois) ; `AGN-2` (huit items sur vingt-quatre n'ont que deux propositions, voir `DEC-0044` pour
  l'arbitrage du score) ; `AGN-3` (apostrophes droites au lieu de guillemets dans les vingt-quatre
  indices, correction mecanique non faite ici, laissee ouverte dans la fiche).
- **Distinguer les principaux types de mots (nom, verbe) -> reste `en cours`.** Le Done pedagogique
  n'est pas prononce : `nadia-prof` ecrit elle-meme *"la ligne ne peut pas passer `couvert-valide`
  tant que `NAT-5` et `NAT-9` restent ouvertes"* (`FICHE-bandit-manchot-nature-mot-cp.md`, verdict
  `ddb0fc4`). Je ne la fais pas bouger. Le detail de `NAT-5`/`NAT-9` et leur routage : `DEC-0044`.
- **Accorder le verbe avec son sujet -> `couvert-valide`, apres reaudition.** `nadia-prof` avait
  **REFUSE** l'increment le 2026-08-24 sur cinq motifs (`FICHE-bandit-manchot-accord-sujet-verbe-
  cm1.md`, premier verdict, non efface), dont un decisif mesure **a la reference du produit elle-
  meme (744 x 551)**, pas dans une bande differee. `marc-dev` a corrige, et `nadia-prof` a reaudite
  le meme jour l'arbre **fusionne** (`round-two` a `6d9c00a`, branche `s19-nadia`, commit
  `a81324c`) : **`VALIDE SOUS RESERVE`, aucune reserve bloquante**, les cinq motifs leves un par un
  et verifies par sa propre mesure, pas par l'annonce de `marc-dev`. Verifie avant de faire bouger
  la ligne : elle a controle que le seul fichier de production modifie entre les deux audits est
  `cm1-accord-sujet-verbe.yaml`, donc que ses trois autres verdicts du jour ne sont pas concernes.
  **Chiffre du motif decisif** : largeur necessaire maximale 647 px pour 680 px offerts (33 px de
  marge), zero item sur deux rangees (contre deux au refus), bas du bloc de verdict a 474,19 px
  (contre 564,19 px, sous un pli a 551 px). Audit : 6 parties, 108 lectures, 21/21 items vus,
  `scrollY = 0` sur les 108. **Perimetre exact de ce que ce `couvert-valide` recouvre** : trois
  reserves non bloquantes restent ouvertes et sont nommees, pas tues - `ASV-1` (vingt indices sur
  vingt-et-un sous le pli, meme mecanisme que `SYN-12`/`AGN-1`, verse au chantier vertical de s20 ;
  la premiere ligne de chaque indice reste lisible et porte le sujet et son nombre) ; `ASV-2` (sur
  dix-huit items, la terminaison annoncee dans l'indice se resout par appariement de fin de mot -
  le prix de donner la regle, aucune demande) ; `ASV-3` (un item sur vingt-et-un ou la reponse est
  contenue dans l'infinitif nomme, inherent au premier groupe). **Ce paquet ne couvre rien dans la
  bande 576-719 px** : sa largeur necessaire (647 px) depasse largement les 500 px qu'elle y offre
  aujourd'hui, et il s'y repliera tant que `S19-6a` (`DEC-0043`) n'est pas traitee - cette bande
  n'entrait pas non plus dans le refus initial, la portee de ce Done ne change donc pas sur cet
  axe.

**Note du 2026-08-24 (s19, `camille-po`) - question de validite de proxy due depuis s13, sans
reponse a ce jour.** La ligne "Relire et corriger son texte a l'aide de reperes simples" bloque sur
une question posee a `nadia-prof` par `DEC-0030` (section 5) le 2026-08-15 : un choix entre
plusieurs versions d'une phrase satisfait-il "relire et corriger **son** texte", ou cette
competence suppose-t-elle un texte que l'enfant a lui-meme produit, ce qui la deplacerait vers la
saisie ? Six sprints se sont ecoules sans reponse ecrite - verifie par recherche de la formulation
exacte ("relire et corriger") dans `squad/conformite/` et `squad/decisions/produit/`, seul
`DEC-0030` lui-meme la porte. Detail complet : `DEC-0042`, section 4.

**Amendement du 2026-08-24 (meme jour) - la question est tranchee, la ligne rejoint le statut
`non couvrable en l'etat`** (`squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-
depuis-s13.md`, section 4 ; `DEC-0042`, amendement du meme jour). **REFUSE, et la saisie ne la
debloque pas non plus** : `nadia-prof` ecrit elle-meme que cette ligne "rejoint la meme categorie
que 'Lire a voix haute...' : hors de portee, et pas pour une question de sprints." Deux motifs
cumules, distincts de celui de la lecture a voix haute - elle depend structurellement d'une autre
ligne non couverte ("Rediger un texte narratif court et coherent", elle-meme refusee a la saisie,
voir plus bas), et juger une correction suppose un jugement humain sur un texte que l'enfant a
lui-meme ecrit, qu'aucune comparaison de chaines ne peut rendre. **Le statut de cette ligne passe de
`non couvert` a `non couvrable en l'etat`** (tableau ci-dessus) : elle cesse de figurer dans une
famille ("contenu seul" chez `DEC-0030`, "production libre" chez `DEC-0039`) qui laisserait croire
qu'une mecanique la debloquera un jour. `nadia-prof` route par ailleurs une proposition
d'amendement du referentiel, distincte de ce statut et non tranchee ici : voir `DEC-0042` pour le
detail et le routage vers l'utilisatrice.

Note sur la ligne `en cours` (homophones) : `DEC-0008-la-couverture-reprend-francais-cp-et-cm1.md`
ouvre cette competence au sprint 4, sur la matiere premiere `contenu-repris/mots/cm1-homophones.yml`
(story S4-1, `docs/backlog/backlog.md`) - premiere ligne de matrice couverte pour le CM1, tous jeux
confondus.

Note sur la ligne `en cours` (conjugaison), ouverte au sprint 6 : `DEC-0013-perimetre-sprint-6.md`
ouvre cette competence sur `textes-trous/cm1-conjugaison.yml` (story S6-3,
`docs/backlog/backlog.md`), **sur le present de l'indicatif uniquement**. Le futur et l'imparfait,
les deux autres temps nommes par cette meme ligne, restent explicitement `non couvert` par ce
paquet : la part non couverte est assumee des l'ouverture, pas decouverte a l'audit (voir `DEC-0013`
pour le motif du choix entre les deux candidats de `textes-trous/`).

**Etat au 2026-08-06, premier audit** : `nadia-prof` prononce **VALIDE SOUS RESERVE**, Done
pedagogique **accorde** (`squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`) : le contenu
est exact item par item et le rattachement au referentiel est juste. La ligne **reste neanmoins
`en cours`**, pas `couvert-valide` : elle motive l'ecart elle-meme - la competence vise "les
**principaux** homophones grammaticaux", le paquet en couvre cinq (a/à, et/est, on/ont, son/sont,
ou/où), il en manque cinq parmi les plus courants (ce/se, ces/ses, la/là/l'a, mais/mes, peu/peut).
Sa condition ecrite pour `couvert-valide` : ces cinq couples ajoutes **et**, dans le meme geste,
des phrases qui obligent l'enfant a regarder ailleurs que juste avant le trou (profondeur CM1,
pas CE1/CE2) - story S5-6 de `docs/backlog/backlog.md`. Reserve distincte, non bloquante pour ce
statut mais nommee : dix items pour dix manches, donc aucune variete entre deux parties - story
S5-7.

**Etat au 2026-08-07, audit de l'increment de s05** : `nadia-prof` reconduit et elargit le Done
pedagogique (**VALIDE SOUS RESERVE**) aux douze items neufs et aux vingt-deux indices
(`squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`, verdict du 2026-08-07). **La condition
n'est PAS levee, la ligne reste `en cours`.** Quatre des six points de sa condition sont tenus ; il
manque deux items neufs de plus dont le mot decisif n'est pas voisin du trou, et la correction d'un
indice qui instruit une substitution qui n'est pas du francais - chiffres exacts a la section 3 du
meme verdict, repris sans les redurcir ni les affaiblir dans les stories S6-4 et S6-5
(`docs/backlog/backlog.md`).

**Etat au 2026-08-10, audit de cloture de s06** : `nadia-prof` prononce **VALIDE**, Done
pedagogique accorde sans reserve bloquante, sur le paquet porte a 24 items par S6-4 et S6-5
(`squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`, verdict du 2026-08-10). **Sa condition
en six points du 2026-08-06 est levee : la ligne passe a `couvert-valide`.** C'est la deuxieme
ligne du produit a atteindre ce statut, et la premiere en francais. Le perimetre exact de ce
`couvert-valide` est nomme par elle-meme pour ne pas etre confondu avec une couverture totale :
dix familles d'homophones (`a/à`, `et/est`, `son/sont`, `on/ont`, `ou/où`, `ce/se`, `ces/ses`,
`la/là/l'a`, `mais/mes`, `peu/peut`), chacune travaillee dans les deux sens, avec une profondeur de
CM1 sur la moitie des items neufs. **`c'est`/`s'est` (et `sait`/`sais`) n'y est pas** : elle
satisfait ses quatre criteres de "principal" et c'est l'une des fautes les plus frequentes au CM1,
mais `nadia-prof` refuse de grossir une condition deja engagee par l'equipe - elle la nomme comme
premier candidat d'une story ulterieure (voir "Backlog - sprints suivants").

**Etat au 2026-08-12, retrait du catalogue jouable (sprint 10)** : le Done pedagogique du
2026-08-10 ci-dessus **n'est pas rouvert** - il n'y a aucun nouvel audit, aucun REFUSE. La ligne
**redescend neanmoins de `couvert-valide` a `en cours`**, sur un motif qui n'est pas pedagogique :
"Le bon petit mot" (`bandit-manchot-homophones-cm1`) quitte le catalogue jouable, son enonce le
plus long ne tenant pas au-dessus du pli sur l'ecran que le sprint 10 construit pour les sept
autres jeux (`DEC-0024-retrait-le-bon-petit-mot-contenu-valide-ecran-trop-court.md`). Un contenu
pedagogiquement valide qu'aucun jeu jouable ne sert n'est pas une couverture reelle du programme :
`couvert-valide` decrirait un enfant capable d'ouvrir l'application et de travailler cette
competence aujourd'hui, ce qui n'est plus le cas. La ligne remontera a `couvert-valide` quand le
paquet raccourci aura ete rejuge par `nadia-prof` (nouveau Done pedagogique, la barre en six points
ci-dessus n'etant pas rouverte pour autant) et que le jeu sera revenu au catalogue - voir `DEC-0024`
pour la condition de forme executable qui conditionne ce retour.

**Etat au 2026-08-10, premier audit de la ligne conjugaison** : `nadia-prof` prononce **REFUSE**
sur `bandit-manchot-conjugaison-present-cm1`
(`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict du 2026-08-10). Motif
unique et suffisant : cinq des douze indices du paquet enoncent une regle de conjugaison fausse, et
trois d'entre elles sont dementies par un autre item du meme paquet. Les douze enonces eux-memes
sont exacts et bien equilibres (les six personnes grammaticales representees deux fois chacune,
les trois groupes travailles). La ligne **reste `en cours`** - elle ne pouvait de toute facon pas
atteindre `couvert-valide` sur cet increment, qui ne couvre qu'un temps sur trois (`DEC-0013`). Le
jeu est retire du catalogue jouable en attendant sa correction
(`DEC-0014-retrait-le-bon-verbe-du-catalogue.md`).

**Etat au 2026-08-10, second audit, increment livre en s07** : `nadia-prof` prononce **VALIDE SOUS
RESERVE**, Done pedagogique **accorde**, sur le commit `10dd74d` (`HEAD` de `round-two`)
(`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict "second audit,
increment livre en s07"). **Le REFUSE du 2026-08-10 est leve** : les douze indices sont desormais
vrais et aucun ne dementit plus un autre item du meme paquet, verifie un par un et vu en jeu. La
ligne **reste `en cours`**, et le second audit ne change rien a cet etat : la barre qu'elle a
ecrite elle-meme pour `couvert-valide` (ouvrir le futur et l'imparfait, voir le refus du
2026-08-10, section 5) est inchangee et non atteinte, ce paquet ne couvrant qu'un temps sur trois.
Ce Done pedagogique leve, a lui seul, ne remet pas le jeu au catalogue : voir `DEC-0017` pour la
decision de remise, qui verifie en plus la seconde condition posee par `DEC-0015` (quatrieme
amendement) sur le repli de mise en page.

**Etat au 2026-08-11, troisieme audit, sur l'increment livre en s08 (S8-4, commit `533967d`)** :
`nadia-prof` prononce **REFUSE**
(`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict du 2026-08-11). Ce
refus porte sur l'activation de "Le bon verbe" au catalogue (deux propositions plus larges que
leur colonne de 152 px a 360 px, dans le cabinet livre le meme jour), pas sur le contenu : **sa
barre fermee de six points pour l'ouverture du futur et de l'imparfait est LEVEE**, verifiee point
par point, et le contenu de S8-4 est valide. `DEC-0018` tranche que le jeu reste au catalogue
pendant la correction de cette disposition (story `S8-5`), a la difference du precedent
`DEC-0014`, et fixe le denouement des deux issues possibles du prochain audit.

**La ligne restait `en cours` a ce stade.** La barre en six points levee autorise le passage a
`couvert-valide`, mais je choisis de ne pas le faire tant que le verdict global inscrit sur cette
fiche, pour ce commit, est `REFUSE` : voir `DEC-0018`, section "Decision" point 4, pour le motif
complet. La ligne passera a `couvert-valide` au premier verdict qui n'est pas un `REFUSE` sur
l'increment corrige de `S8-5`, sans que la barre en six points soit rouverte a cette occasion ; si
ce verdict est de nouveau `REFUSE`, la ligne reste `en cours` et le jeu sort du catalogue
(`DEC-0018`, section "Decision" point 3).

**Etat au 2026-08-11, quatrieme audit, sur l'increment corrige de S8-5 (commit `bd9a8f0`)** :
`nadia-prof` prononce **VALIDE SOUS RESERVE**, Done pedagogique **accorde**
(`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict du 2026-08-11,
"quatrieme audit"). **Le REFUSE du 2026-08-11 (troisieme audit) est LEVE** : son motif unique -
`mangeons` a 156,02 px de glyphes dans une boite de 152,00 px, ecrit sur la paroi du cabinet - est
ferme, mesure sur 880 propositions dans 220 etats et 22 parties reelles, minimum du paquet
2,86 px sur `mangeons`, zero proposition qui deborde et zero sous 2,0 px d'air. **La ligne passe
de `en cours` a `couvert-valide`**, conformement a la condition posee par `DEC-0018` section 4 :
le premier verdict sur l'increment corrige de `S8-5` qui n'est pas un `REFUSE`. La barre en six
points de S8-4, levee depuis le troisieme audit, n'est pas rouverte ni reauditee a cette occasion.

Le verdict est **sous reserve**, pour une seule raison que `nadia-prof` nomme explicitement comme
n'etant pas un motif : le defaut de pli (24 items sur 25 avec l'indice affiche), regi par
`DEC-0016` amende, traite comme story commune aux deux jeux concernes et non comme une condition
de cette ligne. Deux reserves non bloquantes, distinctes du pli, sont versees au backlog
(`docs/backlog/backlog.md`, section "Autres intentions") : l'inconfort des quatre colonnes de ce
paquet a 360 px (aucune taille acceptable par `nadia-prof` ne le rend confortable, 2,86 px d'air
reel contre 20 px demandes par sa propre definition d'une colonne lisible) et le risque qu'un
futur paquet a quatre propositions herite de 30 px sans qu'aucune ligne de test ne le signale.

**Note sur la ligne `en cours` (homophones) - resurrection au sprint 17, pas une nouvelle ouverture.**
`DEC-0039` affecte la story de raccourcissement de `cm1-homophones.yaml`, ouverte au backlog depuis
`DEC-0024` (s10) et jamais affectee depuis. Cette ligne est deja `en cours` depuis s10 (contenu
pedagogiquement valide, retire du catalogue jouable pour un motif de forme) : la traiter au sprint
17 fait avancer un jeu deja suivi vers un nouveau Done pedagogique et un retour au catalogue, ce
n'est pas une ouverture de competence neuve et cela ne compte pas dans le plafond de trois
ouvertures de `DEC-0030`. Voir `DEC-0039`, section dediee, pour la condition de retour (mesure de
rendu a 360 px, plus correction des 22 indices qui nomment la reponse, plus reprise de tout le
paquet comme un contenu neuf devant `nadia-prof`) et `docs/backlog/backlog.md`, story `S17-4`.

**Etat au 2026-08-23 (s18), la ligne remonte a `couvert-valide` - premier mouvement reel de
couverture depuis quatre sprints.** `nadia-prof` prononce **VALIDE SOUS RESERVE, aucune reserve
bloquante**, sur l'entree de catalogue de `S17-4` remesuree contre la reference 744 x 551 fixee par
`DEC-0040` (`squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`, verdict du 2026-08-23,
arbre `10e0e0f`) : `BPM-1` **TENUE** (24 items sur 24, aux trois etats d'echec, 23 parties, 624
lectures, `scrollY = 0` sur les 624) ; son refus du meme matin (`BPM-5`, huit indices sur 24 coupes
par le pli) est **FERME l'apres-midi** sur le correctif livre - **0 item sur 24** perd du texte
d'indice, contre 8 le matin meme, mesure au centieme sur l'increment reellement servi (246
lectures). **Ses trois refus successifs sur ce jeu sont ainsi leves, aucun n'est efface** : ils
restent lisibles a leur date, avec leur motif, plus haut dans cette meme note et dans la fiche de
conformite.

**La condition de retour de `DEC-0024` est desormais remplie dans son entier** : la mesure de forme
(`BPM-1`) **et** un Done pedagogique complet sur le paquet raccourci, dans le meme geste - ni l'un
ni l'autre ne suffisait seul depuis s10. **Je fais donc le geste que le process reserve a moi seule
face a un Done `VALIDE` ou `VALIDE SOUS RESERVE` sans reserve bloquante** (`squad/README.md`,
section 9, etape 6) : la ligne passe de `en cours` a `couvert-valide` (tableau ci-dessus). Elle y
retourne pour la seconde fois - la premiere le 2026-08-10, avant que `DEC-0024` ne la fasse
redescendre le 2026-08-12 pour un motif de forme, jamais pedagogique. Le perimetre exact de ce
`couvert-valide` reste celui que `nadia-prof` a nomme le 2026-08-10 (dix familles d'homophones,
`c'est`/`s'est` et `sait`/`sais` non couverts, candidats d'une story ulterieure) : cette ligne ne
pretend pas a une couverture totale de la competence, et je ne l'habille pas autrement.

**Le chiffre, avec sa base.** Avant ce mouvement : **9 lignes `couvert-valide` sur 46** (19,6 %),
inchange depuis s13 malgre quatre sprints (s14 a s17) que `DEC-0037` nomme elle-meme sans mouvement
reel de couverture. Apres ce mouvement : **10 lignes `couvert-valide` sur 46** (21,7 %), un gain de
2,1 points. Methode : meme comptage que la synthese de cloture de s17 ci-dessus (une ligne de
tableau par ligne de competence, quatre tableaux de cette matrice), une seule ligne change de
colonne, aucune n'ouvre ni ne ferme par ailleurs.

**Ce que cela fait au verdict de `DEC-0037`.** Le compte qu'elle pose - quatre sprints consecutifs
(s14 a s17) sans mouvement reel de couverture programme - est un fait historique, mesure a l'epoque
sur l'etat reel du produit, et je ne le reecris pas : il reste exact tel qu'ecrit. **Mais la serie
qu'il decrit s'arrete ici** : s18 est le premier sprint depuis s13 ou une ligne franchit
`couvert-valide` sur un increment reellement jouable par un enfant, audite par `nadia-prof` sur
l'application servie. Ce n'est pas une couverture neuve au sens ou `DEC-0030` plafonne les
ouvertures - `S17-4` faisait deja avancer une ligne `en cours` depuis s10, jamais une competence
neuve - mais c'est exactement le mouvement dont l'absence faisait le constat de `DEC-0037` et de
son amendement de cloture de s17 (*"aucune ligne n'atteint `couvert-valide` ; aucune ne produit, a
la cloture, un jeu qu'un enfant peut jouer sur une competence neuve ou avancee"*). Cette phrase-la
cesse d'etre vraie a la cloture de s18. Je ne prononce pas la fin de la serie comme une victoire
d'ensemble : `S17-3` (synonymes), refusee trois fois sur ce meme trimestre et desormais `VALIDE
SOUS RESERVE` elle aussi, reste `en cours` (note ci-dessous) - le mouvement de s18 porte sur une
seule ligne, pas sur l'ensemble du chantier francais CM1 ouvert depuis s17.

**Note sur les deux lignes `en cours` ouvertes au sprint 17 (comprehension de texte, synonymes).**
`DEC-0039` ouvre ces deux lignes, stories `S17-2` et `S17-3` de `docs/backlog/backlog.md`. **Aucune
ligne ne passe a `couvert-valide` par ce planning** : ce statut n'est prononce que par `nadia-prof`,
apres audit reel de chaque increment.

**Mise a jour du 2026-08-19, apres l'arbitrage prealable de `nadia-prof`**
(`squad/conformite/ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md`) :

- **Comprehension de texte (`S17-2`)** : principe **ACCEPTE** sous cinq conditions (COMP-1 a
  COMP-5, `squad/conformite/FICHE-bandit-manchot-comprehension-cm1.md`) ; la matiere premiere
  identifiee a l'ouverture de la fiche est **REFUSEE** (appariement de mots, zero inference sur huit
  items) - le paquet a ecrire est integralement neuf. Limite de couverture nommee par
  `nadia-prof`, a porter le jour ou cette ligne bouge : "textes courts, un texte par question, sans
  lecture longue" - le mecanisme ne fera jamais lire un texte long a l'enfant.
- **Synonymes (`S17-3`)** : proxy **ACCEPTE** sous condition de forme (le mot en contexte de phrase,
  jamais isole). Couverture partielle **ACCEPTEE**, mais avec un statut different des cinq
  partialites deja acceptees sur ce produit (additions, homophones, conjugaison, comparer, tables) :
  `nadia-prof` ecrit que les antonymes ne sont pas un prolongement du meme geste que les synonymes,
  ils sont l'autre operation. **Consequence pour cette matrice, ecrite ici pour qu'elle ne soit
  jamais oubliee a un audit futur : cette ligne reste `en cours` quel que soit le verdict
  pedagogique rendu sur le paquet synonymes seul, et elle n'entre jamais dans le numerateur du taux
  de couverture tant que les antonymes n'y sont pas ajoutes.** Un Done `VALIDE` sur ce paquet ne
  suffira pas a lui seul, a la difference de la ligne "tables de multiplication" (qui avait, elle,
  atteint `couvert-valide` avec une mention restrictive sur l'automatisation). Le mecanisme pour
  exprimer cette nuance est celui deja en usage depuis s09 (note "comparer/ranger" sous le tableau
  mathematiques CP) : une prose sous le tableau, pas un statut invente - la nomenclature de cette
  matrice n'en porte que trois (`non couvert` / `en cours` / `couvert-valide`) et ce n'est pas ce
  document qui doit en ajouter un quatrieme. Story compagne des antonymes ecrite au backlog,
  non affectee : `S17-5`, `docs/backlog/backlog.md`.

**Note du 2026-08-19 (camille-po) - `S17-3` REFUSE sur un rendu ; le statut de ligne ne bouge pas,
et je dis pourquoi ce statut inchange ne doit pas se lire comme un increment jouable.**
`nadia-prof` a refuse `S17-3` (`squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md`, "Done
pedagogique du 2026-08-19 (s17) sur S17-3") : contenu non en cause (`SYN-1` a `SYN-4` tenues, 0
indice sur 20 ne nomme la reponse), refus de forme mesure - aucune entree de catalogue, et la
derniere proposition tombe 63 a 159 px sous le pli sur 20 items sur 20 a 360 x 640. Detail complet,
ma decision de perimetre et ce que je route a `theo-archi` : `DEC-0039`, amendement du 2026-08-19
("`S17-3` refusee sur un rendu, meme famille que `S17-2`, mais pas son declencheur").

**Le statut de cette ligne reste `en cours` et je ne le change pas ici, par la regle que
`nadia-prof` ecrit dans son propre verdict : "le refus [...] ne fait pas bouger la ligne de la
matrice, qui reste `en cours`."** Ce n'est pas une omission de ma part : je l'applique telle
qu'elle l'a posee, et elle est seule a en avoir l'autorite.

**Ce que ce statut inchange ne veut pas dire, et que je nomme pour qu'un audit futur ne le
suppose pas.** `en cours` ne signifie pas ici qu'un enfant peut jouer ce jeu : `S17-3` n'a
aujourd'hui aucune entree dans `catalogue.yaml`, `nadia-prof` l'ecrit elle-meme ("ce jeu n'existe
pas pour un enfant"), et sa condition bloquante `SYN-5` reste ouverte. Le statut de matrice mesure
une ligne de programme suivie par une story active, pas la presence d'un increment jouable a la
date de cette note - les deux ont coincide pour cette ligne depuis l'ouverture de s17, ils ne
coincident plus depuis ce refus. Voir `DEC-0039`, meme amendement, pour le critere que je pose
avant la cloture de s17 : si `SYN-5` n'est pas levee et qu'aucune entree de catalogue n'existe pour
`S17-3` a la cloture, l'engagement de `DEC-0037` (au moins un mouvement reel de couverture par
sprint) n'est pas honore par ce statut inchange, et je l'ecrirai alors comme le changement de
direction que `DEC-0037` exige de nommer.

**Note du 2026-08-21 (camille-po) - le critere pose ci-dessus est tranche : `SYN-5` refusee une
seconde fois, `DEC-0037` s'ecrit comme changement de direction.** `nadia-prof` a mesure les seize
items retenus (apres retrait des quatre a trois rangees, voir plus bas) sur 80 tirages au clavier :
**sept sur seize** posent la derniere proposition sous le pli, de 39 a 87 px selon le tirage - le
comportement d'un item face au pli s'est revele etre une propriete du couple (item, tirage), pas de
l'item seul. **`SYN-5` reste refusee.** L'entree de catalogue existe, mais le second terme de mon
critere ne l'est pas : par ma propre regle, **l'engagement de `DEC-0037` n'est pas honore en
substance**. Detail complet, ma decision sur le mandat de la disposition des propositions (second
declencheur accepte, borne, route a `ines-design`/`theo-archi`) et le texte integral du changement
de direction : `DEC-0039`, amendement du 2026-08-21. Le statut de cette ligne reste `en cours`,
inchange une seconde fois, pour le meme motif qu'au refus precedent - ce n'est toujours pas un
increment jouable.

**Note du 2026-08-23 (camille-po, s18) - `S17-3` obtient `VALIDE SOUS RESERVE, aucune reserve
bloquante` ; la ligne reste `en cours`, exactement comme deja ecrit ci-dessus.** `nadia-prof`
remesure `S17-3` sur le correctif livre de `SYN-11` (`squad/conformite/FICHE-bandit-manchot-
synonymes-cm1.md`, verdict du 2026-08-23, arbre `10e0e0f`) : ses trois refus successifs sur cette
ligne sont leves, aucune condition bloquante ne reste ouverte. **Je ne fais pas passer cette ligne
a `couvert-valide`** : `SYN-4` (les antonymes manquent) l'en empeche, exactement comme la note du
2026-08-19 ci-dessus le nommait a l'avance et comme `nadia-prof` le confirme elle-meme dans son
verdict ("c'est elle, et elle seule, qui empeche la ligne de matrice de passer `couvert-valide`").
Ce Done ouvre la porte pedagogique de `S17-3` ; il ne fait pas bouger la ligne, qui n'entrera dans
le numerateur du taux de couverture que le jour ou `S17-5` (antonymes, non affectee) l'aura
completee et sera elle-meme jugee.

**Amendement du 2026-08-19 (camille-po) - `S17-2` sort du perimetre de s17, la ligne revient
`non couvert`.** `nadia-prof` a rejoue la mesure de `COMP-5` sur la mise en page reelle de la
manche (consigne, bloc de question et cabinet compris, pas seulement le bloc de texte-support) :
le budget compose tient a 4 lignes, environ 20 mots dans le meilleur assemblage encore realiste,
et elle l'ecrit elle-meme insuffisant pour porter l'inference que `COMP-3` exige - le paquet y
redeviendrait le reperage litteral qu'elle a deja refuse en s02. `ines-design` degage 44 px sans
cout pour l'enfant (ou 82 px par une voie qu'elle deconseille, en retirant le score de l'ecran) ;
`theo-archi` n'apporte aucun pixel de mecanisme. Il manque 90 a 120 px, aucune des trois
contributions ne les fournit, et aucune ne s'approche meme par la voie deconseillee. Detail complet
et motif de perimetre dans `DEC-0039`, amendement du 2026-08-19 ("`S17-2` sort du perimetre de
s17"). Aucun code de production n'existait pour cette ligne a la date de cet amendement (verifie :
aucune entree de catalogue, aucun fichier de contenu, aucun champ de texte-support dans le code) ;
la sortie de perimetre ne defait donc rien. Les cinq conditions `COMP-1` a `COMP-7`, le tableau
chiffre de `nadia-prof` et `ADR-0050` restent la matiere de travail du jour ou cette ligne rouvre.

## Synthese au sprint 0, mise a jour a l'ouverture du sprint 4

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 10 | 1 | 0 |
| mathematiques CM1 | 12 | 12 | 0 | 0 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 10 | 1 | 0 |
| **Total** | **46** | **43** | **3** | **0** |

Ces chiffres etaient inchanges depuis le sprint 0 apres trois sprints (s00 a s02) qui ont produit
un seul jeu sur une seule competence, encore `en cours` a ce jour - voir la note sous le tableau
mathematiques CP ci-dessus. `DEC-0007` avait tranche que s03 completerait cette ligne
(repartition des sommes) plutot que d'en ouvrir une nouvelle ; l'increment corrige est fusionne,
en attente de l'audit de cloture de `nadia-prof`. **`DEC-0008` renverse cette priorisation sur
demande explicite de l'utilisatrice** : le sprint 4 ouvre deux lignes de plus, l'une en CP et
l'une en CM1, toutes deux en francais (stories S4-1, S4-2, `docs/backlog/backlog.md`). Aucune des
trois lignes `en cours` ne passe a `couvert-valide` par cette seule ouverture : ce statut n'est
prononce que par `nadia-prof`, apres audit reel de chaque increment.

Le trou qui restait le plus visible - **francais, qui n'avait encore aucune ligne `en cours`** -
est celui que `DEC-0008` traite. Le CM1 sort egalement de son etat a zero ligne, tous jeux
confondus, avec la ligne homophones grammaticaux.

## Synthese apres les verdicts du 2026-08-06 (cloture de s03, premier audit de s04)

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 10 | 0 | 1 |
| mathematiques CM1 | 12 | 12 | 0 | 0 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 10 | 1 | 0 |
| **Total** | **46** | **43** | **2** | **1** |

`nadia-prof` a rendu son verdict le 2026-08-06 sur les trois increments soumis
(`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, `FICHE-bandit-manchot-le-bon-son-cp.md`,
`FICHE-bandit-manchot-homophones-cm1.md`) :

- **Mathematiques CP** obtient la premiere ligne `couvert-valide` du produit : la condition posee
  en s01 et reportee deux fois est levee.
- **Francais CM1** (homophones grammaticaux) obtient un Done pedagogique accorde (`VALIDE SOUS
  RESERVE`), mais la ligne reste `en cours` : cinq couples d'homophones courants manquent a "les
  principaux" (story S5-6, `docs/backlog/backlog.md`).
- **Francais CP** (graphemes/phonemes) est **REFUSE** sur cinq motifs, dont un structurel (un
  paquet `type: question` ne peut porter d'indice) ; la ligne reste `en cours`, et le jeu est
  retire du catalogue jouable jusqu'a correction (`DEC-0009-retrait-le-bon-son-du-catalogue.md`,
  stories S5-2 a S5-5).

Le trou qui demeure : le produit compte desormais une ligne `couvert-valide` sur 46, deux lignes
`en cours` dont l'une correspond a un jeu refuse et retire du catalogue, et 43 lignes `non
couvert`.

## Perimetre de s05 (2026-08-06)

`DEC-0011-perimetre-s05-reste-correctif.md` : le sprint 5 ne cree aucune ligne nouvelle. Les seuls
mouvements possibles ce sprint portent sur les trois lignes deja engagees, via l'audit des
corrections `docs/backlog/backlog.md` S5-2 a S5-7. Le motif n'est pas une regle generale (voir
`DEC-0011`, qui se distingue explicitement de `DEC-0007`) : aucun candidat de `contenu-repris/` ne
correspond integralement a une ligne non encore ouverte sans reproduire le defaut de couverture
partielle deja sanctionne sur les homophones CM1. L'ouverture suivante est nommee, pas indefinie :
sprint 6, sur `textes-trous/cm1-accords.yml` ou `textes-trous/cm1-conjugaison.yml`, deja
identifies par `DEC-0008`.

## Perimetre de s06 (2026-08-07)

`DEC-0013-perimetre-sprint-6.md` : le sprint 6 ouvre une ligne de plus, sur `textes-trous/
cm1-conjugaison.yml` (story S6-3, `docs/backlog/backlog.md`) - **la ligne
`francais | CM1 | Orthographe et grammaire | Conjuguer les verbes frequents au present, futur et
imparfait de l'indicatif` passe `en cours` par ce planning**, jeu et mecanique renseignes
ci-dessus. Ce paquet ne couvre que le present de l'indicatif ; le futur et l'imparfait restent
`non couvert` par construction, assumes des l'ouverture (`DEC-0013`, qui motive ce choix face a
l'autre candidat, `textes-trous/cm1-accords.yml`, non retenu ce sprint). **Aucune ligne ne passe a
`couvert-valide` par ce planning** : ce statut n'est prononce que par `nadia-prof`, apres audit
reel de l'increment - y compris pour la ligne homophones CM1, dont les deux points restants
(S6-4, S6-5) visent a lever la condition deja ecrite, sans prejuger du verdict.

Le sprint 6 porte par ailleurs le rhabillage du gabarit (S6-1, S6-2, deja livre et audite en s05,
`DEC-0012` amendee en consequence), la sortie de quarantaine de "Le bon son" (S6-6, S6-7, lot
indivisible) et une dette de forme et d'outillage (S6-8 a S6-14) : aucune de ces stories ne porte
de competence programme, elles n'ont donc pas d'impact sur cette matrice.

## Synthese apres l'ouverture de s06 (2026-08-07)

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 10 | 0 | 1 |
| mathematiques CM1 | 12 | 12 | 0 | 0 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 9 | 2 | 0 |
| **Total** | **46** | **42** | **3** | **1** |

Une ligne de plus qu'a la cloture de s05 : francais CM1 passe de une a deux lignes `en cours`
(homophones grammaticaux, conjugaison au present). Aucune ligne ne passe `couvert-valide` par ce
planning ; ce mouvement attend l'audit de `nadia-prof` sur les increments de s06.

## Synthese apres les verdicts du sprint 6 (2026-08-10)

`nadia-prof` a rendu trois verdicts a la cloture de s06, sur les fiches
`FICHE-bandit-manchot-conjugaison-present-cm1.md`, `FICHE-bandit-manchot-homophones-cm1.md` et
`FICHE-bandit-manchot-le-bon-son-cp.md` :

- **Francais CM1 (homophones grammaticaux)** obtient **`couvert-valide`** : sa condition en six
  points du 2026-08-06 est levee. Deuxieme ligne du produit a atteindre ce statut, premiere en
  francais.
- **Francais CM1 (conjugaison au present)** est **REFUSE** : cinq indices sur douze enoncent une
  regle de conjugaison fausse, trois dementies par un autre item du meme paquet. La ligne reste
  `en cours`. Le jeu, jouable au moment de l'audit, est retire du catalogue
  (`DEC-0014-retrait-le-bon-verbe-du-catalogue.md`). **Ce REFUSE est leve au sprint 7** : voir la
  section "Synthese apres les verdicts du sprint 7" ci-dessous.
- **Francais CP (graphemes/phonemes, "Le bon son")** obtient **VALIDE SOUS RESERVE** : le REFUSE du
  2026-08-06 est leve sur ses cinq motifs, apres recomposition complete du paquet autour des trois
  voyelles nasales. La ligne reste `en cours` : la competence nomme aussi les voyelles et consonnes
  simples et les digraphes `ch`, `ou`, `oi`, qu'aucun jeu ne couvre encore.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 10 | 0 | 1 |
| mathematiques CM1 | 12 | 12 | 0 | 0 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 9 | 1 | 1 |
| **Total** | **46** | **42** | **2** | **2** |

Le produit compte desormais deux lignes `couvert-valide` sur 46 (contre une a la cloture de s05),
deux lignes `en cours` dont l'une correspond a un jeu refuse et retire du catalogue
(conjugaison CM1), et 42 lignes `non couvert`, chiffre inchange : aucune ligne nouvelle n'a ete
ouverte ce sprint, le mouvement porte uniquement sur les deux lignes deja engagees.

## Synthese apres les verdicts du sprint 7 (2026-08-10)

Le sprint 7 (`DEC-0015`) est transverse - la mecanique de machine a sous, pas un contenu
pedagogique - et n'ouvre aucune ligne nouvelle. Il porte neanmoins un second audit sur une ligne
deja engagee : `nadia-prof` a rejoue `bandit-manchot-conjugaison-present-cm1` sur le commit
`10dd74d` (`HEAD` de `round-two`) apres la correction de ses cinq indices fautifs, sur une branche
jetable qui n'ajoutait que l'entree de catalogue retiree par `DEC-0014` (le jeu reste hors
catalogue, cet audit ne l'y remet pas) (`squad/conformite/FICHE-bandit-manchot-conjugaison-
present-cm1.md`, verdict "second audit, increment livre en s07").

- **Francais CM1 (conjugaison au present)** : le REFUSE du 2026-08-10 (premier audit, cinq indices
  fautifs) est **leve**. `nadia-prof` prononce **VALIDE SOUS RESERVE**, Done pedagogique
  **accorde**. La ligne **reste `en cours`** : elle ne pouvait de toute facon pas atteindre
  `couvert-valide` sur cet increment (un temps sur trois, `DEC-0013`), et la barre qu'elle a
  ecrite elle-meme pour ce statut (ouvrir le futur et l'imparfait) est inchangee et non atteinte.
  Ce chantier fait l'objet de la story de chantier 4 de `DEC-0017`.

Ce second audit ne deplace aucun chiffre du tableau de synthese ci-dessus : la ligne reste
`en cours` avant comme apres, et aucune autre ligne n'est touchee par le sprint 7. La decision de
remettre ce jeu au catalogue jouable, qui suppose en plus la seconde condition de `DEC-0015`
(quatrieme amendement) sur le repli de mise en page, est tranchee par `DEC-0017` et non par cette
matrice, qui ne porte que l'etat des competences.

## Synthese apres les verdicts du sprint 8 (2026-08-11)

`DEC-0017` ouvre au sprint 8 l'extension de "Le bon verbe" au futur et a l'imparfait (S8-4), sur
la barre en six points close par `nadia-prof` le 2026-08-10. Cette extension a traverse quatre
audits sur la meme fiche (`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`) :
levee de la barre en six points au troisieme audit, puis REFUSE sur un motif distinct (largeur des
colonnes du cabinet a 360 px), corrige par S8-5 (`DEC-0018`), puis quatrieme audit sur l'increment
corrige.

- **Francais CM1 (conjugaison au present, futur, imparfait)** obtient **`couvert-valide`** :
  `nadia-prof` prononce **VALIDE SOUS RESERVE**, Done pedagogique accorde, sur le quatrieme audit
  (commit `bd9a8f0`). Sa barre en six points, levee au troisieme audit et non rouverte, est la
  seule condition qui gouvernait ce statut ; elle est tenue. La reserve qui rend ce Done "sous
  reserve" - le defaut de pli - n'est pas une condition de cette ligne (voir la note detaillee
  ci-dessus, "Etat au 2026-08-11, quatrieme audit").

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 10 | 0 | 1 |
| mathematiques CM1 | 12 | 12 | 0 | 0 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 9 | 0 | 2 |
| **Total** | **46** | **42** | **1** | **3** |

Le produit compte desormais trois lignes `couvert-valide` sur 46 (contre deux a la cloture de s06
et s07), une seule ligne `en cours` (francais CP, graphemes/phonemes), et 42 lignes `non couvert`,
chiffre inchange : `DEC-0017` gele l'objectif du sprint et aucune ligne nouvelle n'est ouverte.

## Perimetre de s09 (2026-08-12)

`DEC-0019-perimetre-sprint-9-quatre-lignes-textuelles-de-mathematiques-cp-denombrement-et-monnaie-
differes.md` : sur demande de l'utilisatrice, le sprint 9 attaque mathematiques CP, domaine
"Nombres et calculs", sous un gel de tout travail de rendu (aucune mesure, aucun garde-fou CSS,
aucun arbitrage visuel). Quatre lignes entrent au sprint, toutes exprimables dans l'enveloppe
`type: question` existante sans code neuf (`ADR-0007`) - comparer deux nombres par un signe (S9-3,
partielle, "ranger" exclu), soustractions dans la limite de 10 (S9-1), complements a 10 (S9-2), et
resoudre un probleme simple d'addition ou de soustraction (S9-4). **Ces quatre lignes passent
`en cours` par ce planning**, jeu et mecanique renseignes dans le tableau mathematiques CP
ci-dessus. Deux lignes restent `non couvert`, differees avec un motif ecrit (denombrement,
monnaie) : chacune nomme un verbe de reconnaissance visuelle que le gel de rendu met hors de
portee ce sprint. **Aucune ligne ne passe a `couvert-valide` par ce planning** : ce statut n'est
prononce que par `nadia-prof`, apres audit reel de chaque increment, sur les quatre fiches de
conformite ouvertes dans le meme geste que `DEC-0019`.

**Correction du 2026-08-12, apres l'avis de faisabilite de `theo-archi`.** Deux arbitrages
supplementaires, tous deux traites sous `DEC-0019` (amendee) et `DEC-0020` :

- la story `S9-2` (complements a 10), bloquee au planning par un obstacle technique (`ADR-0023`
  contre un espace d'items fini), est debloquee par `DEC-0020` : le jeu qui la couvre declare
  `nombreDeManches: 7` plutot que 10, voir la note sous le tableau mathematiques CP ci-dessus ;
- le motif "reconnaissance visuelle qu'aucun texte seul ne peut porter", ecrit ci-dessus pour les
  deux lignes differees, s'est revele imprecis pour la ligne monnaie (son verbe "utiliser" est
  entierement textuel, prouve par du contenu deja joue en production) et incomplet pour la ligne
  denombrement (un pictogramme-caractere y est techniquement portable) - corrige dans la note sous
  le tableau mathematiques CP ci-dessus. Les deux lignes restent `non couvert`, sur un motif
  desormais pedagogique et non plus technique.

Aucun de ces deux points ne deplace un chiffre du tableau de synthese ci-dessous.

## Synthese apres l'ouverture de s09 (2026-08-12)

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 4 | 1 |
| mathematiques CM1 | 12 | 12 | 0 | 0 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 9 | 0 | 2 |
| **Total** | **46** | **38** | **5** | **3** |

Quatre lignes de plus qu'a la cloture de s08 passent `en cours` (toutes en mathematiques CP), pour
un total de cinq lignes `en cours` sur le produit. Aucune ligne ne passe `couvert-valide` par cette
seule ouverture ; ce mouvement attend l'audit de `nadia-prof` sur les increments du sprint 9.

## Synthese apres les verdicts du sprint 9 (2026-08-12)

`nadia-prof` a rendu quatre verdicts sur les quatre fiches ouvertes par `DEC-0019`
(`squad/conformite/FICHE-bandit-manchot-complements-10-cp.md`,
`FICHE-bandit-manchot-soustractions-cp.md`, `FICHE-bandit-manchot-problemes-additifs-cp.md`,
`FICHE-bandit-manchot-comparer-nombres-cp.md`) :

- **Mathematiques CP (complements a 10)** obtient **`couvert-valide`** : `nadia-prof` prononce
  **VALIDE**, Done pedagogique accorde sans reserve bloquante, les onze items verifies un par un
  (`DEC-0020`, deux items degeneres gardes sur motif pedagogique explicite, sept manches
  confirmees suffisantes pour que l'aide reste atteignable). Quatrieme ligne `couvert-valide` du
  produit, deuxieme en mathematiques CP.
- **Mathematiques CP (soustractions)** est **REFUSE** : les 24 indices du paquet ecrivent "Leve"
  au lieu de "Lève", et 23 sur 24 ecrivent "baisse-en" au lieu de "baisses-en" - un mot mal
  orthographie affiche a l'enfant qui a echoue trois fois de suite. La mathematique et la methode
  des indices sont exactes ; seule la langue est fautive. La ligne reste `en cours`.
- **Mathematiques CP (resoudre un probleme simple d'addition/soustraction)** est **REFUSE** : deux
  items sur 22 (le clown et ses ballons, le jardinier et ses fleurs) attendent une reponse qui
  contredit la lecture correcte de leur propre enonce (le "en" partitif), et la reponse juste sous
  cette lecture n'est proposee dans aucun des deux cas. Les 20 autres items sont bons. La ligne
  reste `en cours`.
- **Mathematiques CP (comparer deux nombres)** est **REFUSE** : l'indice de l'item `56 ___ 60`
  affirme que 56 vient juste avant 60 dans la suite des nombres, ce qui est faux (59 est son
  predecesseur immediat) - une faussete sur exactement ce que le jeu construit. Les 24
  comparaisons et les 23 autres indices sont exacts. La ligne reste `en cours`, et n'aurait de
  toute facon pas pu passer `couvert-valide` sur cet increment : le perimetre reste partiel,
  "ranger" restant differe par `DEC-0019`.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 3 | 2 |
| mathematiques CM1 | 12 | 12 | 0 | 0 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 9 | 0 | 2 |
| **Total** | **46** | **38** | **4** | **4** |

Le produit compte desormais quatre lignes `couvert-valide` sur 46 (contre trois a la cloture de
s08), quatre lignes `en cours` (trois refus de ce sprint plus la ligne francais CP restee ouverte
depuis s06) et 38 lignes `non couvert`, chiffre inchange. Les trois refus sont en cours de
correction sur la seule correction bornee que `nadia-prof` a ecrite dans chaque fiche ; leurs
lignes ne passeront a `couvert-valide` que si `nadia-prof` rejoue l'increment corrige et prononce un
verdict `VALIDE`.

## Synthese apres les seconds verdicts du sprint 9 (2026-08-12)

`nadia-prof` a rejoue les trois increments corriges des trois refus ci-dessus, sur les branches de
correctif fusionnees le meme jour (`squad/conformite/FICHE-bandit-manchot-soustractions-cp.md`,
`FICHE-bandit-manchot-problemes-additifs-cp.md`, `FICHE-bandit-manchot-comparer-nombres-cp.md`,
sections "Verdict courant - second audit du 2026-08-12"). Audit non degrade et plus large que le
premier : 60 parties completes par jeu, soit 1800 coups au total, tous joues volontairement faux
pour forcer l'affichage de l'indice a chaque manche, donc la totalite des items et des indices des
trois paquets lus a l'ecran et non au fichier.

- **Mathematiques CP (soustractions)** obtient **`couvert-valide`** : le REFUSE du 2026-08-12 est
  **leve**, `nadia-prof` prononce **VALIDE SOUS RESERVE**, Done pedagogique accorde. Sa barre
  ecrite au premier audit (deux substitutions orthographiques dans `cp-soustractions.yaml`) est
  tenue au mot, plus une troisieme correction trouvee par `marc-dev` et confirmee par elle
  (`leves` -> `levés`). Cinquieme ligne `couvert-valide` du produit, troisieme en mathematiques CP.
- **Mathematiques CP (resoudre un probleme simple d'addition/soustraction)** obtient
  **`couvert-valide`** : le REFUSE du 2026-08-12 est **leve**, `nadia-prof` prononce **VALIDE SOUS
  RESERVE**, Done pedagogique accorde, sur les deux enonces corriges exactement dans la forme
  qu'elle avait ecrite dans son refus (le clown, le jardinier). **A lire avec la reserve `DEC-0021`
  ouverte** : cette ligne dit que la competence est travaillee et validee, pas que le paquet est
  sans reserve - la regularite de magnitude sur les additions (reserve 1 de son premier audit) reste
  ouverte et differee hors sprint par `DEC-0021`. Sixieme ligne `couvert-valide` du produit,
  quatrieme en mathematiques CP.
- **Mathematiques CP (comparer deux nombres)** obtient un Done pedagogique **accorde** (**VALIDE
  SOUS RESERVE**) sur l'indice corrige de l'item `56 ___ 60` (le mot "juste" retire). **La ligne
  reste `en cours`** : ce n'est pas une consequence du verdict, c'est le perimetre pose a
  l'ouverture de la fiche, avant tout audit - "ranger" reste hors de ce que ce jeu couvre et differe
  par `DEC-0019`. Voir la note dediee sous le tableau mathematiques CP ci-dessus : la moitie
  "comparer" de cette competence est desormais validee pedagogiquement, seule la moitie "ranger"
  manque.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 1 | 4 |
| mathematiques CM1 | 12 | 12 | 0 | 0 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 9 | 0 | 2 |
| **Total** | **46** | **38** | **2** | **6** |

Le produit compte desormais six lignes `couvert-valide` sur 46 (contre quatre a la cloture du
premier audit de ce sprint), deux lignes `en cours` (comparer deux nombres, dont le perimetre
partiel empeche de toute facon `couvert-valide` sur cet increment ; et francais CP, graphemes/
phonemes, restee ouverte depuis s06) et 38 lignes `non couvert`, chiffre inchange : aucune ligne
nouvelle n'est ouverte par ce second tour d'audit, le mouvement porte uniquement sur les trois
lignes deja engagees ce sprint.

## Synthese apres le retrait du sprint 10 (2026-08-12)

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 1 | 4 |
| mathematiques CM1 | 12 | 12 | 0 | 0 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 9 | 1 | 1 |
| **Total** | **46** | **38** | **3** | **5** |

Un seul mouvement, sur une seule ligne : francais CM1 (homophones grammaticaux) redescend de
`couvert-valide` a `en cours` (`DEC-0024-retrait-le-bon-petit-mot-contenu-valide-ecran-trop-court.md`).
Aucun autre chiffre ne bouge - ni la ligne conjugaison CM1, dont les douze conditions de mecanique
et de forme du sprint 10 ne sont pas rouvertes, ni aucune ligne de mathematiques ou de francais CP.
Ce n'est pas un audit pedagogique : aucun Done n'est ni accorde ni retire par ce mouvement, c'est un
constat que le produit livre aujourd'hui ne sert plus cette competence par aucun jeu jouable.

## Perimetre de s12 (2026-08-14)

`DEC-0029-perimetre-sprint-12-trois-lignes-mathematiques-cm1-ouvertes-cinq-differees.md` : sur
demande de l'utilisatrice, le sprint 12 attaque mathematiques CM1, sur les trois lignes les plus
directement servables par le schema `type: question` existant (perimetre, conversions d'unites,
tables de multiplication). **Ces trois lignes passent `en cours` par ce planning**, jeu et mecanique
renseignes dans le tableau mathematiques CM1 ci-dessus. Cinq lignes de plus restent `non couvert`
avec un motif ecrit (trois pour capacite, deux dans l'attente d'un arbitrage prealable de
`nadia-prof`), et quatre restent confirmees hors d'atteinte de cette mecanique. **Aucune ligne ne
passe a `couvert-valide` par ce planning** : ce statut n'est prononce que par `nadia-prof`, apres
audit reel de chaque increment, sur les trois fiches de conformite ouvertes dans le meme geste que
`DEC-0029`.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 1 | 4 |
| mathematiques CM1 | 12 | 9 | 3 | 0 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 9 | 1 | 1 |
| **Total** | **46** | **35** | **6** | **5** |

Trois lignes de plus qu'a la cloture de s10 passent `en cours` (toutes en mathematiques CM1, premiere
ouverture de contenu sur ce niveau depuis le sprint 0 de la reconstruction), pour un total de six
lignes `en cours` sur le produit. Aucune ligne ne passe `couvert-valide` par cette seule ouverture ;
ce mouvement attend l'audit de `nadia-prof` sur les increments du sprint 12.

## Synthese apres les verdicts du sprint 12 (2026-08-15)

`nadia-prof` a rendu quatre verdicts a la cloture de s12 : trois sur les increments ouverts par
`DEC-0029` (`squad/conformite/FICHE-bandit-manchot-perimetre-cm1.md`,
`FICHE-bandit-manchot-conversions-unites-cm1.md`, `FICHE-bandit-manchot-tables-multiplication-cm1.md`)
et un quatrieme, non planifie, qui **retire** un Done pedagogique tenu depuis s03
(`FICHE-bandit-manchot-additions-cp.md`).

### Le fait le plus lourd : le Done pedagogique de la ligne "Calcul mental - additions dans la
### limite de 10" est retire

`nadia-prof` a rejoue `bandit-manchot-additions-cp` (1 320 manches reellement jouees, plus le calcul
exhaustif sur la fabrique) et prononce **REFUSE**, retirant le Done pedagogique qu'elle avait
accorde le 2026-08-05 (s01) et que la ligne portait depuis le 2026-08-06 (s03,
`couvert-valide`). Motif unique : **la bonne reponse est le nombre intermediaire des trois
propositions sur 66,5 % des manches**, calcul exhaustif et jeu reel concordants (66,54 % / 66,6 % /
67,3 % selon la methode) ; un enfant qui presse toujours la proposition du milieu gagne deux manches
sur trois sans additionner. La cause n'est pas le contenu, qui reste arithmetiquement exact (900
manches sans une faute) : c'est **la fabrique `AdditionsBorneesContentSource`**, dont la loi de
tirage des distracteurs (les plus proches ecarts d'abord, un de chaque cote de la bonne reponse)
place mecaniquement la bonne reponse au centre sur tout contenu qu'elle produira, quel que soit le
paquet YAML charge.

**Deux constats anterieurs de `nadia-prof` sont corriges par elle-meme, et les deux textes retires
restent lisibles a leur date, non effaces** :

- Son releve de s00 ("sa position varie... 16/14/11, aucune strategie de position exploitable")
  reste **exact**, mais il porte sur **la position a l'ecran**, battue avant rendu par
  `BanditManchotSession.melanger` et donc uniforme par construction - une mesure qui, par nature,
  ne pouvait pas reveler un biais sur le **rang par valeur**, une propriete differente jamais
  mesuree avant ce sprint. La conclusion de couverture que ce releve avait servi a porter est ce qui
  est retire ici, pas son exactitude.
- Le chiffre de 63,1 % porte par le releve de dette de `lea-dev` (`s12-lea-tables-rang`) est
  **sous-evalue** : sa methode compte un tirage par identifiant d'item, un echantillon de 65 valide
  sur un paquet enumere mais pas sur une fabrique combinatoire comme celle-ci. La valeur retenue est
  **66,5 %**.

**Ce que ce refus ne fait pas** : il ne touche aucun autre verdict de cette fiche (forme, texte,
signal, machine, cabinet, sortie - tous inchanges), et le jeu **reste au catalogue** : il n'est ni
faux ni dangereux, il est trop facile a contourner. Conformement a la regle de cette matrice, la
ligne mathematiques CP "Calcul mental - additions dans la limite de 10 (puis 20)" **repasse de
`couvert-valide` a `en cours`** et redevient du backlog - voir la story de correction ci-dessous
(`docs/backlog/backlog.md`, section "Sprint 12", S12-4). La ligne ne repassera `couvert-valide` que
sur un nouveau verdict de `nadia-prof`, la barre de repartition des sommes qu'elle avait posee en
s01 et levee en s03 n'etant pas rouverte par ce refus-ci.

**Reserve versee au dossier par `nadia-prof`, non bloquante** : le seuil de 50 % qu'elle emploie
comme barre de refus ne voit pas le rang presque vide (`cp-soustractions` a 8,3 %,
`cp-complements-10` a 9,1 %), et deux paquets du catalogue sont exactement sur cette barre. Aucune
action n'est demandee ce sprint ; nommee pour que l'equipe ne la decouvre pas plus tard sans
attribution.

### Les trois increments de mathematiques CM1 ouverts par `DEC-0029`

- **Calculer un perimetre de figures usuelles (carre, rectangle)** : `nadia-prof` prononce **VALIDE
  SOUS RESERVE**, Done pedagogique **accorde**, **sans mention de couverture partielle** - le
  referentiel ne nomme que le carre et le rectangle, et le paquet porte les deux. **La ligne passe
  de `en cours` a `couvert-valide`.** Trois reserves non bloquantes, nommees pour memoire : (1) la
  bonne reponse est le rang intermediaire sur 18 des 20 items de calcul (90,0 %), consequence
  mecanique de sa propre condition P-6 telle qu'ecrite a l'ouverture - `nadia-prof` corrige P-6 pour
  les paquets a venir (quatre familles de distracteurs au lieu de deux, plus une condition
  d'aboutissement portant sur le resultat : aucun rang majoritaire sur l'ensemble d'un paquet), sans
  rouvrir ni le verdict ni le paquet livre ; (2) a 360 x 640, le message d'erreur et l'indice sont
  sous le pli sur 100 % des echecs mesures - non bloquant, M4 ne bloque plus depuis `DEC-0025` ; (3)
  aucune mise en situation dans le paquet, conforme a la lettre de la condition qui l'autorisait sans
  l'exiger.
- **Convertir des unites de longueur, masse, contenance dans le systeme metrique** : `nadia-prof`
  prononce **VALIDE SOUS RESERVE**, Done pedagogique **accorde**, **sans mention de couverture
  partielle** - les trois grandeurs sont servies a parts egales (8 items chacune) et le verbe
  travaille est bien "convertir". **La ligne passe de `en cours` a `couvert-valide`.** Deux
  reserves non bloquantes : (1) a 360 x 640, l'indice est sous le pli sur 62 % des echecs mesures
  (le message d'erreur, lui, reste visible sur 100 %) ; (2) les 24 quantites de depart sont toutes
  un chiffre significatif unique suivi de zeros (7 cm, 3 km...), donc la tache se reduit a compter
  des zeros plutot qu'a multiplier un nombre reel - un point de depart correct mais qui laisse de
  cote la moitie de ce que la competence demande. Une reponse distincte est versee au meme geste :
  la moitie des distracteurs de mauvais sens valait initialement de 10 000 a 1 000 000 fois la bonne
  reponse (un remplissage plutot qu'un distracteur plausible) ; `nadia-prof` corrige sa propre
  condition C-7, trop courte sur ce point, et demande des erreurs de facteur a un ou deux crans
  seulement - correction deja versee au paquet et confirmee par elle.
- **Calcul mental - tables de multiplication (jusqu'a ×10), rappel automatise** : `nadia-prof`
  prononce **REFUSE**. Motif unique : sur les 99 items, la bonne reponse n'est **jamais** le plus
  petit des trois nombres, et elle est le plus grand sur 64 items sur 99 (64,6 % au calcul exhaustif,
  64,4 % en jouant) - un enfant qui presse toujours le plus grand nombre gagne sans avoir rappele
  une seule table. Cause : les deux distracteurs d'un enonce "a x b" sont systematiquement les
  produits voisins **inferieurs** (`(a-1) x b` et `a x (b-1)`), jamais les voisins superieurs. **La
  ligne reste `en cours`** : le paquet est par ailleurs le mieux construit des trois de ce sprint
  (99 produits exacts, indices verifies un par un, seul des trois a tenir M4). **Ce verdict n'a pas
  encore ete rejoue** : `lea-dev` a livre une reprise (branche `s12-lea-tables-rang`, garde-fou
  generique de rang ajoute) apres ce refus, mais aucun nouvel audit de `nadia-prof` n'est trace sur
  cet increment corrige a ce jour. La ligne ne passera `couvert-valide` que sur ce nouveau verdict.

**Couverture partielle a nommer des maintenant, pour que la ligne ne s'ouvre jamais verte sur ce
point sans que quelqu'un l'ait vu venir** (`nadia-prof`, meme fiche, section "Ce que la matrice doit
dire, et que camille-po ne doit pas perdre") : la competence du referentiel dit "rappel
**automatise**", et l'automatisation est une affaire de vitesse - un enfant automatise quand il
repond sans calculer. **Ce produit ne mesure aucun temps de reponse.** Le jour ou cette ligne
atteindra `couvert-valide`, ce sera donc sur une **couverture partielle** : le "sait" (l'enfant
connait le produit) est couvert par ce mecanisme, l'"automatise" (l'enfant le retrouve vite) ne
l'est par aucun. Ce n'est pas un reproche au paquet - savoir ses tables est la condition pour les
automatiser, et s'entrainer est le bon usage de ce jeu - mais la matrice devra porter cette
restriction avec ces mots, pas la laisser dans l'ombre d'une ligne verte.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 2 | 3 |
| mathematiques CM1 | 12 | 9 | 1 | 2 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 9 | 1 | 1 |
| **Total** | **46** | **35** | **5** | **6** |

Deux mouvements en sens opposes sur ce sprint : mathematiques CM1 gagne deux lignes
`couvert-valide` (perimetre, conversions), et mathematiques CP en perd une (additions, Done
pedagogique retire). Le nombre de lignes `non couvert` ne bouge pas : aucune ligne n'est ouverte ni
fermee au sens strict, seule "fractions" recoit une reponse nommee sans changer de statut (voir la
note dediee sous le tableau mathematiques CM1 ci-dessus). Le produit compte six lignes
`couvert-valide` sur 46 (contre cinq a la cloture de s10) et cinq lignes `en cours` (contre trois a
l'ouverture de s12), dont une - les tables - attend un verdict pas encore rejoue, et une autre - les
additions CP - redevient du backlog apres douze sprints de statut `couvert-valide`.

## Synthese apres les seconds verdicts du sprint 12 (2026-08-15)

`nadia-prof` a rejoue, le meme jour, les deux increments refuses a la cloture de s12 - le refus non
planifie sur une ligne `couvert-valide` depuis s03, et le refus planifie sur la troisieme ouverture
CM1 - sur les branches de correctif fusionnees le jour meme
(`squad/conformite/FICHE-bandit-manchot-additions-cp.md`,
`FICHE-bandit-manchot-tables-multiplication-cm1.md`, sections "Done pedagogique du 2026-08-15"). Audit
non degrade sur les deux fiches : jar reconstruit et joue par elle depuis son propre worktree, pas
un audit sur parole.

### Calcul mental - additions dans la limite de 10 : le refus du matin est leve, VALIDE sans reserve

`marc-dev` a corrige la fabrique `AdditionsBorneesContentSource`. `nadia-prof` prononce **VALIDE**,
sans reserve : le rang de la bonne reponse, mesure sur 12 000 manches reellement jouees (1 200
parties) et confirme par calcul exact sur la fabrique, tombe a **33,55 / 33,55 / 32,90** contre
17,10 / 66,45 / 16,45 avant correction - aucun rang n'est majoritaire, c'est la seule barre qu'elle
avait ecrite et elle est tenue. **Le REFUSE du 2026-08-15 (matin) est LEVE** ; il reste lisible en
entier a sa place sur la fiche, non efface.

**La ligne mathematiques CP "Calcul mental - additions dans la limite de 10 (puis 20)" repasse de
`en cours` a `couvert-valide`, sans mention restrictive.** C'est le retour de la ligne la plus
ancienne du produit, tombee le matin meme de ce sprint apres douze sprints de statut vert, sur une
propriete - le rang par valeur de la bonne reponse - que la mesure du sprint 0 portait sur la
position a l'ecran et ne pouvait, par construction, pas voir. Cette histoire n'est pas effacee par
le retour au vert : le REFUSE du 2026-08-15 reste lisible en entier sur la fiche, avec sa cause, et
c'est lui qui a le plus de valeur dans cette ligne. La reserve deja ancienne de cette ligne
subsiste, inchangee par ce verdict : la mention "(puis 20)" de la competence reste hors du perimetre
de ce jeu, dont la borne est 10 (voir plus haut, verdict de cloture de s03).

`nadia-prof` corrige au passage un de ses propres chiffres, a son desavantage : l'etat d'avant
qu'elle avait ecrit ce matin (66,54 %) etait legerement faux, la valeur exacte est **66,45 %** - son
echantillon du sprint 0 avait sous-pese le cas de somme 1 (0,78 % mesure contre 1,2903 % exact).
Aucun verdict n'en est change, ni celui de ce matin ni celui des tables ci-dessous ; c'est une
correction de sa propre mesure, versee au dossier.

### Calcul mental - tables de multiplication : le refus du 2026-08-14 est leve, VALIDE SOUS RESERVE

`lea-dev` a corrige la fabrique (28 items retournes vers les voisins du dessus). `nadia-prof`
prononce **VALIDE SOUS RESERVE** : le rang de la bonne reponse, calcule de facon exhaustive sur les
99 items, passe de 0 / 35,35 / 64,65 a **28,28 / 35,35 / 36,36**, confirme sur 6 000 manches
reellement jouees (27,57 / 35,83 / 36,60). Les deux moities du motif de refus tombent : plus aucun
rang n'est majoritaire, et le rang "la plus petite" n'est plus vide. **Le REFUSE du 2026-08-14 est
LEVE** ; il reste lisible en entier a sa place sur la fiche, non efface.

**La ligne mathematiques CM1 "Calcul mental - tables de multiplication (jusqu'a ×10), rappel
automatise" repasse de `en cours` a `couvert-valide`, avec la mention obligatoire "sans mesure
d'automatisation"** deja posee au refus du 2026-08-14 et non rouverte a la levee (voir la note dediee
sous le tableau mathematiques CM1 ci-dessus) : la competence dit "rappel **automatise**",
l'automatisation est une affaire de vitesse, et ce produit ne mesure aucun temps de reponse. Le
paquet verifie que l'enfant **sait** ses tables, pas qu'il les a automatisees - couverture
**partielle**, a porter avec ces mots chaque fois que cette ligne est citee.

Deux reserves non bloquantes, nommees par `nadia-prof` - ce sont ses deux points mineurs du
2026-08-14, non repris dans cette reprise, versees au dossier plutot que refusees puisqu'elle ne
deplace pas ses propres poteaux :

- **Reserve A - un meme enonce revient dans une partie sur cinq**, et non sur sept comme elle
  l'avait ecrit le 2026-08-14 : 99 items pour seulement **83 enonces distincts** (les 8 carres, de
  2 x 2 a 9 x 9, portent chacun trois items au meme enonce), mesure a **21,6 % des parties** (108
  parties sur 500 de dix manches), contre l'estimation initiale d'environ une fois sur sept (4
  parties sur 30). `nadia-prof` ecrit explicitement que cette reserve est **plus lourde qu'elle ne
  l'avait dite**.
- **Reserve B - deux distracteurs sortent de l'espace des tables** : 11 sur "1 x 10 = ?" et 110 sur
  "10 x 10 = ?". Un produit de deux nombres de 1 a 10 ne vaut jamais 11 ni 110 ; ils restent
  plausibles pour l'enfant qui additionne au lieu de multiplier.

Ces deux reserves ne bloquent pas cette ligne - c'est `nadia-prof` qui l'ecrit - et deviennent deux
stories a ecrire.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 1 | 4 |
| mathematiques CM1 | 12 | 9 | 0 | 3 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 9 | 1 | 1 |
| **Total** | **46** | **35** | **3** | **8** |

Deux mouvements, tous deux vers `couvert-valide` : mathematiques CP retrouve sa ligne d'additions,
le jour meme de sa chute, et mathematiques CM1 gagne la ligne des tables de multiplication, sous
reserve. Le nombre de lignes `non couvert` ne bouge pas. Le produit compte desormais huit lignes
`couvert-valide` sur 46 (contre six a la cloture du premier tour d'audit de ce sprint) et trois
lignes `en cours` (comparer et ranger en mathematiques CP, graphemes/phonemes en francais CP,
homophones en francais CM1) : aucune ligne nouvelle n'est ouverte par ce second tour, le mouvement
porte uniquement sur les deux lignes rejouees ce jour.

## Perimetre de s13 (2026-08-15)

`DEC-0030-perimetre-sprint-13-trois-lignes-mathematiques-cm1-et-le-plafond-de-la-mecanique-
unique.md` : trois lignes de mathematiques CM1 entrent au sprint 13 (voir la section dediee sous le
tableau mathematiques CM1 ci-dessus pour le detail par ligne). **Ces trois lignes passent `en cours`
par ce planning**, jeu et mecanique renseignes. **Aucune ligne ne passe a `couvert-valide` par ce
planning** : ce statut n'est prononce que par `nadia-prof`, apres audit reel de chaque increment.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 1 | 4 |
| mathematiques CM1 | 12 | 6 | 3 | 3 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 9 | 1 | 1 |
| **Total** | **46** | **32** | **6** | **8** |

Trois lignes de plus qu'a la cloture de s12 passent `en cours` (toutes en mathematiques CM1 : grands
nombres, multiplications et divisions simples, problemes de durees), pour un total de six lignes
`en cours` sur le produit. Le nombre de lignes `couvert-valide` ne bouge pas : ce mouvement attend
l'audit de `nadia-prof` sur les increments du sprint 13, y compris l'arbitrage prealable qui reste a
rendre sur deux des trois lignes (`DEC-0030`, section 5).

## Synthese apres les verdicts du sprint 13 (2026-08-15 a 2026-08-16, soir)

Cette section corrige une dette de gouvernance nommee dans `squad/sprints/s13-grands-nombres-et-
chemin-de-catalogue/RAPPORT.md` (section "Encore ouverts") : cette matrice etait restee sur l'etat
d'ouverture de s13 ci-dessus pendant tout le sprint, alors que quatre verdicts de `nadia-prof` s'y
sont succede le meme jour et le lendemain. Je les recapitule dans l'ordre, plutot que de n'ecrire
que l'etat final, pour que la trajectoire reste lisible (voir aussi le detail sous les tableaux
mathematiques CM1 ci-dessus) :

1. **2026-08-15** : premier audit de "Les grands nombres" - **REFUSE** (deux motifs de contenu).
   Aucun mouvement de statut : la ligne etait deja `en cours` depuis l'ouverture du planning.
2. **2026-08-16, journee** : second audit de "Les grands nombres" - **REFUSE une seconde fois**, sur
   un motif neuf de rendu, les deux motifs de contenu leves. Toujours aucun mouvement de statut.
   Dans le meme arbitrage, "Le perimetre" (`couvert-valide` depuis le 2026-08-14) est **rouvert et
   refuse** sur le motif exact : la ligne "Calculer un perimetre de figures usuelles (carre,
   rectangle)" **redescend de `couvert-valide` a `en cours`**.
3. **2026-08-16, soir** : apres livraison de la voie de rendu (voie 4), `nadia-prof` verifie les
   deux jeux en jouant l'increment reel. Les deux verdicts sont **VALIDE SOUS RESERVE** : "Les
   grands nombres" **passe de `en cours` a `couvert-valide`** (reserve J ouverte, non bloquante) et
   "Le perimetre" **repasse de `en cours` a `couvert-valide`** (refus du point 2 leve, une reserve
   neuve versee au dossier).

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 1 | 4 |
| mathematiques CM1 | 12 | 6 | 2 | 4 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 9 | 1 | 1 |
| **Total** | **46** | **32** | **5** | **9** |

Par rapport a l'ouverture de s13 (46 | 32 | 6 | 8), un seul mouvement net subsiste une fois la
journee du 2026-08-16 rejouee dans l'ordre : mathematiques CM1 gagne une ligne `couvert-valide`
("Les grands nombres", jamais couverte avant ce sprint) et en perd zero au net sur "Le perimetre"
(descendue puis remontee le meme jour). Les deux lignes qui restent `en cours` en mathematiques CM1
sont "Calcul mental - multiplications et divisions simples (multiples de 10, 100)" et "Resoudre des
problemes de durees", toutes deux ouvertes par `DEC-0030` et toujours en attente de l'arbitrage
prealable de `nadia-prof` sur leur contenu (section 5 de cette meme decision), non affecte par les
verdicts ci-dessus.

`squad/validation/FILE-ATTENTE.md` est mis a jour dans le meme geste : les deux increments sortent
de la file au titre de leurs Done pedagogiques respectifs, tous deux `VALIDE SOUS RESERVE`.

## Synthese au sprint 17 (2026-08-19) - verification demandee par l'utilisatrice, ecart trouve avec `DEC-0037`

Cette matrice n'avait plus de section de synthese depuis la cloture du sprint 13 ci-dessus, alors
que `DEC-0037` (perimetre de s16, `camille-po`) et les trois documents de ceremonie de s16 de
`sofia-sm` (`squad/sprints/s16-deux-defauts-et-dettes-de-len-tete/planning.md`, `RAPPORT.md`,
`review.md`) affirment tous les quatre un etat de couverture - "23 lignes `couvert-valide`, 5 `en
cours`, 46 `non couvert`, sur 74 lignes de competence au total" - incompatible avec le dernier
tableau de synthese de cette matrice (ci-dessus, s13 : 46 total, 32 non couvert, 5 en cours, 9
couvert-valide). C'est exactement la dette de gouvernance que la section de s13 disait corriger :
un chiffre sans methode ecrite a cote ne se compare a rien, meme juste.

**Methode de comptage, verifiee deux fois par deux chemins independants :**

1. **Une ligne de competence = une puce de premier niveau** des quatre referentiels actuellement
   disponibles (`squad/programme/mathematiques/competences-cp.md`, `competences-cm1.md`,
   `squad/programme/francais/competences-cp.md`, `competences-cm1.md`), reprise mot pour mot dans
   les quatre tableaux de cette matrice ci-dessus. Ne compte pas comme une ligne : les titres de
   domaine (`##`), les lignes des tableaux de synthese eux-memes, ni les phrases de la prose qui
   reprennent les memes mots sans etre une ligne de tableau. Compte de puce par referentiel :
   mathematiques CP 7+2+2=11 ; mathematiques CM1 7+3+2=12 ; francais CP 3+4+2+2+1=12 ; francais CM1
   3+2+4+2=11. **Total : 46.**
2. **Une ligne de tableau par une ligne de tableau**, comptee directement dans les quatre tableaux
   "Mathematiques - CP", "Mathematiques - CM1", "Francais - CP", "Francais - CM1" ci-dessus,
   statut par statut.

Les deux chemins rendent le meme total (46) et le meme detail par statut. Aucune des quatre
referentiels ne porte de sous-competence formellement distincte d'une puce : il n'existe donc
aujourd'hui aucun perimetre defendable qui rendrait plus de 46 lignes sans en inventer.

**Etat reel, inchange depuis le sprint 13 (2026-08-15/16) :**

| Matiere / niveau | competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 1 | 4 |
| mathematiques CM1 | 12 | 6 | 2 | 4 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 9 | 1 | 1 |
| **Total** | **46** | **32** | **5** | **9** |

Verifie a nouveau, pas seulement recopie de s13 : aucune story de `docs/backlog/backlog.md` sur
s14, s15, s16 ou s17 (a ce jour) ne cible une ligne de cette matrice. `DEC-0032` (s14), `DEC-0037`
(s16, section 3) et `DEC-0038` (s17) le disent chacune explicitement pour son sprint, et la story
`S17-1` du sprint 17 est elle-meme marquee "transverse - pas de contenu pedagogique". Les quatre
tableaux ci-dessus n'ont recu aucune modification de statut depuis les verdicts du sprint 13. Le
tableau ci-dessus est donc identique, ligne a ligne, a celui de la section "Synthese apres les
verdicts du sprint 13" plus haut.

**D'ou vient l'ecart avec `DEC-0037`.** Ce n'est pas un cas ou les deux chiffres compteraient des
objets differents et seraient chacun defendables sous leur propre definition : les quatre
referentiels ne portent pas de quoi faire 74 lignes sous aucun decoupage raisonnable, et 23 ne
correspond a aucun sous-ensemble nomme des neuf lignes `couvert-valide` reelles. `DEC-0037` est
simplement fausse sur trois des quatre nombres qu'elle porte. La lecture la plus probable de la
mecanique de l'erreur, batie pour rendre compte des quatre chiffres a la fois : le "46" reel (le
total juste) a ete recopie au mauvais endroit, dans la case "non couvert" plutot que dans la case
"total" ; le "32" reel (le vrai compte de lignes non couvertes) a ete transpose en "23" ; le "5"
(en cours) est le seul chiffre juste, et le seul que l'enonce de l'ecart, plus haut, avait deja
repere comme concordant ; et le "74" n'est la somme d'aucune mesure independante, seulement la
somme des trois chiffres deja fautifs (23 + 5 + 46 = 74), ce qui explique pourquoi il "boucle"
sans etre juste. Je ne peux pas retrouver la frappe exacte qui a produit cet ecart, et je ne
l'invente pas : ce que je peux etablir avec certitude, par verification directe des quatre
referentiels et des quatre tableaux, c'est que 46 / 32 / 5 / 9 est l'etat reel et que 74 / 23 / 5 /
46 ne decrit aucun perimetre existant.

**Origine tracee dans le depot.** `git log` sur
`squad/decisions/produit/DEC-0037-perimetre-sprint-16-fermeture-des-dettes-de-len-tete-zero-
ouverture-de-contenu.md` montre que les quatre chiffres fautifs sont deja presents dans son premier
commit (`f73ad41`, `camille-po`, 2026-08-19), avant toute autre modification de ce fichier. Les
trois documents de ceremonie de s16 de `sofia-sm` qui portent la meme phrase (`planning.md`,
commit `411a5ad`, posterieur ; `RAPPORT.md` ; `review.md`) l'ont donc reprise de `DEC-0037` sans la
verifier contre cette matrice - c'est `camille-po` qui a introduit l'erreur, pas `sofia-sm` qui
l'aurait inventee. Correction versee dans `DEC-0037` lui-meme, section "Correction du 2026-08-19
(camille-po, s17)" ; les trois fichiers de `sofia-sm` ne sont pas corriges ici (voir la section "Ce
que je route" de cette meme correction).

**Ce que cet ecart change a l'engagement pose par `DEC-0037`.** Cette decision posait que si le
sprint 17 ne rouvre aucune ligne de cette matrice, ce fait devra etre ecrit comme un changement de
direction plutot que comme une continuation silencieuse. Rien dans le travail fait ce jour sur
cette matrice n'ouvre de ligne : c'est une correction de mesure, pas un increment de contenu.
L'engagement de `DEC-0037` reste donc entier et n'est pas leve par cette section. Il est en
revanche plus pressant qu'il ne paraissait sous le chiffre fautif : neuf lignes `couvert-valide`
sur 46 (19,6 %) et vingt-trois sur 74 (31,1 %) ne racontent pas la meme histoire d'avancement, et
c'est la version optimiste, fausse, qui avait circule dans trois documents de ceremonie avant
d'etre lue par l'utilisatrice.

## Perimetre de s17 (2026-08-19) - francais CM1 non couvert

`DEC-0039-perimetre-sprint-17-francais-cm1-et-dette-de-len-tete.md` : l'utilisatrice tranche le
perimetre de s17 sur le francais CM1 non couvert et la dette. Deux lignes entrent au sprint, deux
autres sont differees nommement, une (`homophones`) est traitee sans etre une ouverture (voir la
note dediee sous le tableau francais CM1 ci-dessus) - detail complet, criteres et arbitrages
prealables dans `DEC-0039` et `docs/backlog/backlog.md`, section "Sprint 17".

**Ces deux lignes passent `en cours` par ce planning**, jeu et mecanique renseignes dans le tableau
francais CM1 ci-dessus :

- **Comprendre un texte narratif ou documentaire (questions explicites et implicites, inferences
  simples)** - story `S17-2`, sous arbitrage prealable bloquant de `nadia-prof` sur la validite de
  la forme (texte affiche avec la question) au regard de la competence visee.
- **Utiliser des synonymes et antonymes pour preciser ou nuancer un propos** - story `S17-3`, en
  couverture partielle nommee (synonymes seuls), sous double arbitrage prealable bloquant de
  `nadia-prof` (validite du proxy de reconnaissance pour un verbe de production, "utiliser" ;
  acceptabilite du perimetre partiel a l'ouverture).

**Aucune ligne ne passe a `couvert-valide` par ce planning** : ce statut n'est prononce que par
`nadia-prof`, apres audit reel de chaque increment, sur les deux fiches de conformite ouvertes dans
le meme geste que `DEC-0039`.

**Amendement du 2026-08-19, apres les trois arbitrages prealables rendus par `nadia-prof`**
(`squad/conformite/ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md`). Les deux arbitrages
bloquants nommes ci-dessus sont rendus :

- **`S17-2`** : principe accepte sous conditions (COMP-1 a COMP-5). La matiere premiere de
  `contenu-repris/quiz/cm1-comprehension.yml`, elle, est **refusee** - voir la note dediee sous le
  tableau francais CM1 ci-dessus et `DEC-0039`, amendement du 2026-08-19, pour ce que cela change au
  plafond et au cout estime. Le critere d'ouverture "sans gabarit ni feuille de style neuve" est
  **leve** par `camille-po` (COMP-5 l'exige).
- **`S17-3`** : proxy accepte sous condition de forme (mot en contexte de phrase, `SYN-1`).
  Partialite acceptee, mais **avec un statut plus strict que les cinq precedentes** : cette ligne ne
  passera jamais `couvert-valide` sur les seuls synonymes et ne compte jamais dans le taux de
  couverture tant que les antonymes n'y sont pas - voir la note dediee sous le tableau francais CM1
  ci-dessus. Story compagne des antonymes ecrite au backlog (`S17-5`), non affectee.

**Trois lignes de plus restent `non couvert`, differees avec un motif ecrit** (`DEC-0039`) :

- **Lire silencieusement un texte de longueur croissante et en restituer l'essentiel** : premier
  report, structurel - la competence demande une production (un resume), le mecanisme du bandit
  manchot ne sait que faire choisir une proposition. Route a `theo-archi` le jour ou une mecanique
  de production ou de saisie libre est envisagee pour le produit, sur le meme modele que "reperage
  sur une droite numerique" en mathematiques CM1.
- **Rediger un texte narratif court et coherent** et **Relire et corriger son texte a l'aide de
  reperes simples** : meme famille structurelle que ci-dessus (production d'ecriture libre), premier
  report pour chacune, meme routage a `theo-archi` le jour ou une mecanique de production se pose.
- **Reperer les substituts d'un personnage (pronoms, groupes nominaux) dans un texte**, **Accorder
  le verbe avec son sujet** et **Utiliser a bon escient des mots de liaison simples** : differees
  pour capacite, premier report chacune. Aucune matiere premiere identifiee dans `contenu-repris/`
  pour aucune des trois (verifie par recherche cible, `DEC-0039`) ; servables en principe par le
  schema `type: question` existant, sans arbitrage de principe bloquant identifie a ce jour, mais un
  contenu neuf integralement ecrit sans matiere premiere sur un sprint deja charge de dette
  technique et de deux ouvertures. Candidates prioritaires du sprint suivant.
- **Accorder en genre et en nombre dans le groupe nominal (determinant/nom/adjectif)** : differee
  pour capacite, premier report. Matiere premiere partielle identifiee
  (`contenu-repris/textes-trous/cm1-accords.yml`, 5 items, accord de l'adjectif seul) : elle ne
  couvre pas l'accord determinant/nom que la competence nomme aussi. Je le nomme des maintenant pour
  que la couverture partielle qu'un paquet fonde sur ce seul fichier porterait ne soit pas decouverte
  a l'audit si cette ligne est retenue telle quelle au sprint suivant.

**Le filtre par matiere - troisieme report, compte comme tel, mais pour la premiere fois sur
instruction expresse de l'utilisatrice plutot que par defaut.** `DEC-0032` l'a differe une premiere
fois (s14 vers s15), `DEC-0037` une seconde (s16). L'utilisatrice fixe elle-meme le perimetre de s17
sur le francais CM1 et la dette, sans y inclure le filtre par matiere : je ne le propose donc pas ce
sprint. Meme si elle l'avait inclus, les deux prealables poses par `DEC-0032` point 2 resteraient a
lever (la nomenclature de matiere accentuee, sans reponse ecrite de `theo-archi` depuis le
2026-08-17, et l'absence de toute proposition de forme d'`ines-design` pour un second axe de filtre
combine). A la difference des deux premiers, ce troisieme report n'est pas une continuation
silencieuse : c'est la premiere fois que la demande initiale de l'utilisatrice est explicitement
arbitree par elle plutot que reportee par la squad a son insu, ce qui est la difference que
`DEC-0007` nommait deja comme le point de bascule entre un arbitrage et une direction non choisie.
Je le compte neanmoins comme un troisieme report, sans en attenuer le chiffre.

**La dette de capacite sort du perimetre de la squad, prise en charge par l'utilisatrice.** Deux des
dix obstacles ouverts a l'entree de s17 (`squad/sprints/s16-deux-defauts-et-dettes-de-len-tete/
RAPPORT.md`, section "Obstacles / Ouverts a l'entree de s17") ne sont plus des obstacles de capacite
de l'equipe a ce jour :

- **La dependance a une seule personne pour toute mesure de rendu** (aucun autre agent que
  `nadia-prof` ne peut produire un chiffre de rendu sur ce poste, faute d'un outillage qui pilote un
  navigateur reel) - route jusqu'ici a `sofia-sm` et `camille-po` comme une decision de capacite
  jamais prise.
- **R-BARRE-4 / la reserve F11** (l'ecoute reelle au lecteur d'ecran) - nommee depuis s13 comme hors
  de la portee de la squad, sans proprietaire.

Les deux etaient nommees dans le tableau des obstacles sans jamais avoir de proprietaire capable de
trancher : l'utilisatrice les prend desormais explicitement a sa charge. Ce n'est plus un manque de
la squad a router au sprint suivant ; je le retire de mon perimetre et de celui de `sofia-sm`, et je
le route pour la derniere fois - a `sofia-sm`, pour qu'elle ne les reconduise plus dans le tableau
des obstacles de `RAPPORT.md` a compter de s17, sur cette seule base ecrite.

L'engagement pose par `DEC-0037` (si s17 ne rouvre aucune ligne, ce sera a ecrire comme un
changement de direction) est **leve par ce perimetre, une fois gele par `sofia-sm`** : deux lignes
passent `en cours`, la premiere ouverture de competence depuis le sprint 13 apres trois sprints de
stabilisation nommes comme tels (s14, s15, s16).

## Synthese apres l'ouverture de s17 (2026-08-19)

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 1 | 4 |
| mathematiques CM1 | 12 | 6 | 2 | 4 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 7 | 3 | 1 |
| **Total** | **46** | **30** | **7** | **9** |

Deux lignes de plus qu'a la cloture de s13 passent `en cours` (toutes deux en francais CM1 :
comprehension de texte, synonymes), pour un total de sept lignes `en cours` sur le produit. Le
nombre de lignes `couvert-valide` ne bouge pas : ce mouvement attend l'audit de `nadia-prof` sur les
increments du sprint 17, apres ses deux arbitrages prealables bloquants. Ce tableau n'est valide que
si `sofia-sm` gele ce perimetre au planning tel quel ; `camille-po` ne gele rien.

## Synthese apres l'amendement du 2026-08-19 - `S17-2` sort du perimetre de s17

`DEC-0039`, amendement du 2026-08-19 ("`S17-2` sort du perimetre de s17") : la ligne comprehension
de texte revient `non couvert` (voir la note dediee sous le tableau francais CM1 ci-dessus). Une
seule des deux lignes ouvertes a s17 reste `en cours` a ce jour.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 1 | 4 |
| mathematiques CM1 | 12 | 6 | 2 | 4 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 8 | 2 | 1 |
| **Total** | **46** | **31** | **6** | **9** |

Une seule ligne de plus qu'a la cloture de s13 passe `en cours` (synonymes, francais CM1), pour un
total de six lignes `en cours` sur le produit - contre sept dans la synthese precedente. Le nombre
de lignes `couvert-valide` ne bouge pas et n'a aucune chance de bouger a la cloture de s17 :
`S17-3` (synonymes) ne peut pas y atteindre `couvert-valide` seule (note dediee ci-dessus,
`DEC-0039` section 3) et `S17-4` (homophones) reste bloquee par la mesure `BPM-1` et le Done
pedagogique complet, tous deux absents (`DEC-0024`, amendement du 2026-08-19). **Aucune ligne
n'atteindra `couvert-valide` a la cloture de s17.** L'engagement de `DEC-0037` (au moins une ligne
rouverte, faute de quoi ce serait un changement de direction) reste leve par `S17-3` seule.

## Synthese apres le refus de `S17-3` (2026-08-19) - le tableau ne bouge pas, la phrase de cloture est corrigee

Le tableau de la section precedente reste exact : `S17-3` refusee ne change aucun total, par la
regle que `nadia-prof` pose dans son propre verdict et que la note dediee sous le tableau francais
CM1 explique (statut de ligne inchange, `en cours`). Je ne recopie donc pas un tableau identique.

**Ce que je corrige est la derniere phrase de la synthese precedente : "l'engagement de `DEC-0037`
[...] reste leve par `S17-3` seule" ne tient plus telle quelle.** Je ne l'efface pas, pour que la
trajectoire reste lisible : elle etait vraie au moment ou je l'ai ecrite, quand `S17-3` portait
encore la promesse d'un increment jouable a la cloture de s17. Depuis, `nadia-prof` a refuse cet
increment sur un rendu (63 a 159 px sous le pli, aucune entree de catalogue) - voir la note dediee
ci-dessus et `DEC-0039`, amendement du 2026-08-19. **La phrase juste, a lire a sa place** : le
statut de matrice de la ligne synonymes reste `en cours`, mais cela ne suffit plus a dire que
l'engagement de `DEC-0037` est honore en substance, puisqu'aucune ligne du produit n'a produit
d'increment jouable a ce jour du sprint (`S17-2` en est sortie sans code, `S17-4` reste bloquee sur
`BPM-1`, `S17-3` est refusee et injouable). `S17-3` reste activement poursuivie sous la branche du
mecanisme routee a `theo-archi` (`DEC-0039`) : le critere qui decidera, a la cloture de s17, si
l'engagement de `DEC-0037` est honore ou s'il faut l'ecrire comme un changement de direction est
celui pose dans ce meme amendement - `SYN-5` levee et une entree de catalogue pour `S17-3`, ou non.

## Synthese de cloture de s17 (2026-08-21) - le critere de `DEC-0037` tranche, changement de direction ecrit

`nadia-prof` a refuse `SYN-5` une seconde fois (`squad/conformite/FICHE-bandit-manchot-synonymes-
cm1.md`, "Done pedagogique du 2026-08-21 (s17) sur S17-3, paquet a seize items") : sept items sur
seize sous le pli selon le tirage, `SYN-10` neuve. Le critere que j'avais pose avant la cloture -
`SYN-5` levee **et** une entree de catalogue - n'est donc pas rempli : le second terme existe, le
premier non. **Conformement a ce critere, j'ecris que l'engagement de `DEC-0037` n'est pas honore
en substance.**

Le tableau de la section "Synthese apres l'amendement du 2026-08-19 - `S17-2` sort du perimetre de
s17" reste le tableau exact de cloture : aucun total ne bouge (francais CM1 8/2/1, total 31/6/9),
la ligne synonymes restant `en cours` sans jamais avoir produit d'increment jouable ce sprint. Ce
que j'ajoute ici, et que le tableau seul ne dit pas : **s17 est le quatrieme sprint consecutif sans
mouvement reel de couverture programme**, apres les trois sprints de stabilisation deja nommes
comme tels par `DEC-0037` (s14, s15, s16). Aucune ligne n'atteint `couvert-valide` ; aucune ne
produit, a la cloture, un jeu qu'un enfant peut jouer sur une competence neuve ou avancee. C'est un
changement de direction au sens de `DEC-0037`, ecrit ici et dans `DEC-0039` (amendement du
2026-08-21) plutot que laisse filer sur l'espoir d'un sprint suivant. Le mandat sur la disposition
des propositions, ouvert dans ce meme amendement et route a `ines-design`/`theo-archi`, est ce qui
pourrait, un jour, fermer `SYN-5` sur `S17-3` ; il n'est pas cense produire d'increment avant la
cloture de s17.

## Synthese a l'ouverture de s18 (2026-08-22) - perimetre propose, tableau inchange

`DEC-0041` (`camille-po`) propose un perimetre de trois actions de fermeture de dette pour s18,
sans ouvrir aucune ligne `non couvert` neuve. Le tableau reste exactement celui de la cloture de
s17 : je ne le recopie pas.

**Ce que ce perimetre pourrait rendre, si `nadia-prof` mesure et juge favorablement, et rien
avant** : la ligne francais CM1 "Distinguer et utiliser correctement les principaux homophones
grammaticaux" (`S17-4`) est la seule candidate a `couvert-valide` - `BPM-2` a `BPM-4` sont deja
tenues, seule `BPM-1` manque, et son transfert a l'utilisatrice est leve (`DEC-0041`, section 2).
**Chiffre honnete, non acquis** : 9/46 (19,6 %) -> au mieux 10/46 (21,7 %), un gain de 2,1 points,
conditionne a une mesure qui n'existe pas encore.

**Ce que ce perimetre ne rend pas** : `S17-3` (synonymes) reste `en cours` quel que soit le sort de
`SYN-11` a `SYN-13` - les antonymes manquent toujours et cette ligne n'entre jamais dans le
numerateur sur le seul paquet synonymes (`nadia-prof`, 2026-08-19). Aucune autre ligne ne bouge :
zero contenu neuf n'est ouvert a s18, deliberement (`DEC-0041`, section 1).

**Si `BPM-1` ne tient pas ou si son Done n'est pas complet a la cloture**, s18 rejoint la serie des
sprints sans mouvement reel de couverture deja nommee par `DEC-0037` - je le dis a l'ouverture
plutot qu'a la cloture, puisque c'est la seule action de ce perimetre qui porte un espoir de
mouvement.

## Synthese de cloture de s18 (2026-08-23) - premier mouvement reel de couverture depuis quatre sprints

`nadia-prof` a prononce deux Done pedagogiques sur l'increment livre de s18 (`round-two` a
`10e0e0f`, 621 tests verts, 29 regles ArchUnit) : `S17-4` (homophones) **VALIDE SOUS RESERVE,
aucune reserve bloquante**, et `S17-3` (synonymes) **VALIDE SOUS RESERVE, aucune reserve
bloquante**. Une seule des deux fait bouger cette matrice - voir les notes dediees sous chacune des
deux lignes concernees pour le detail et la methode ; je ne les recopie pas ici.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide |
|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 1 | 4 |
| mathematiques CM1 | 12 | 6 | 2 | 4 |
| francais CP | 12 | 11 | 1 | 0 |
| francais CM1 | 11 | 8 | 1 | 2 |
| **Total** | **46** | **31** | **5** | **10** |

**Le mouvement, avec sa base.** Une seule ligne change de colonne : francais CM1, homophones
grammaticaux, `en cours` -> `couvert-valide`. **9/46 (19,6 %) -> 10/46 (21,7 %)**, gain de 2,1
points. Aucune autre ligne ne bouge : `S17-3` (synonymes) obtient le meme type de verdict pedagogique
mais reste `en cours`, bloquee par `SYN-4` (les antonymes manquants) comme mon arbitrage de s17
l'avait pose et comme `nadia-prof` le confirme dans son propre verdict.

**Ce que cela fait au verdict de `DEC-0037`.** Le fait qu'elle pose - quatre sprints consecutifs
(s14 a s17) sans mouvement reel de couverture programme - reste un fait historique exact, et je ne
le reecris pas. **La serie s'arrete a s18** : c'est le premier sprint depuis s13 ou une ligne
franchit `couvert-valide` sur un increment reellement jouable, audite par `nadia-prof` sur
l'application servie. Je ne l'ecris pas comme un retournement d'ensemble - `S17-3`, malgre un
verdict pedagogique du meme ordre, reste `en cours`, et le chantier francais CM1 ouvert depuis s17
n'est donc qu'a moitie sorti de la stagnation qu'il visait a rompre. Mais le fait precis que
`DEC-0037` et son amendement de cloture de s17 nommaient - *"aucune ligne n'atteint
`couvert-valide` [...] aucune ne produit [...] un jeu qu'un enfant peut jouer sur une competence
neuve ou avancee"* - cesse d'etre vrai a compter de cette cloture.

## Synthese a l'ouverture de s19 (2026-08-24) - un quatrieme statut, un denominateur corrige, quatre lignes ouvertes en contenu seul

`DEC-0042` trace trois arbitrages de l'utilisatrice pour s19 : perimetre de contenu seul plus
conception de la saisie sans code, audio confirme hors perimetre, et la ligne "Lire a voix haute un
texte court et simple avec fluidite croissante" (francais CP) classee `non couvrable en l'etat` -
quatrieme statut de cette matrice, introduit ci-dessus dans "Comment la lire". Motif complet :
`DEC-0042`, section 3 ; note dediee sous le tableau francais CP.

**Le denominateur.** Le referentiel reste a **46** lignes (rien n'y est retire) ; le denominateur
que j'utilise desormais pour tout taux de couverture cite dans un rapport ou une decision devient
**45**, les lignes que ce produit peut en principe atteindre un jour. Aucune ligne
`couvert-valide` ne bouge a l'ouverture de s19 : le compte reste **10**. **10/46 (21,7 %) devient
10/45 (22,2 %)** par le seul effet du denominateur - ce n'est pas un mouvement de couverture, et je
l'ecris pour qu'aucun rapport futur ne le presente comme tel.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide | non couvrable en l'etat |
|---|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 1 | 4 | 0 |
| mathematiques CM1 | 12 | 6 | 2 | 4 | 0 |
| francais CP | 12 | 9 | 2 | 0 | 1 |
| francais CM1 | 11 | 6 | 3 | 2 | 0 |
| **Total** | **46** | **27** | **8** | **10** | **1** |

**Ce qui bouge par rapport a la cloture de s18, colonne par colonne, aucune vers `couvert-valide`** :

- **francais CP** : "Lire a voix haute..." quitte `non couvert` pour `non couvrable en l'etat`
  (arbitrage 3 de `DEC-0042`) ; "Distinguer les principaux types de mots (nom, verbe)..." quitte
  `non couvert` pour `en cours` (`S19-4`).
- **francais CM1** : "Accorder le verbe avec son sujet" et "Accorder en genre et en nombre dans le
  groupe nominal" quittent chacune `non couvert` pour `en cours` (`S19-2`, `S19-3`). La ligne
  synonymes/antonymes reste `en cours`, deja comptee comme telle depuis s17 - `S19-1` lui ajoute un
  second jeu, elle ne change pas de colonne.
- **mathematiques CP, mathematiques CM1** : inchangees, aucune story de ce sprint ne les touche.

**Quatre lignes ouvertes par le Lot A de contenu seul de `DEC-0042`** (`S19-1` a `S19-4`,
`docs/backlog/backlog.md`), **aucune ne passe `couvert-valide` par ce planning** : ce statut n'est
prononce que par `nadia-prof`, apres audit reel de chaque increment livre. Detail par ligne : les
notes ajoutees sous chaque tableau concerne, plus haut.

**Quatre questions de validite de proxy de `DEC-0030` (s13), dues depuis six sprints et jamais
repondues, reposees a `nadia-prof` et rendues visibles directement sous les lignes qu'elles
bloquent** (mathematiques CP : denombrer, figures planes ; francais CP : decoder un mot par
assemblage syllabique ; francais CM1 : relire et corriger son texte) - detail et methode de
verification : `DEC-0042`, section 4.

## Amendement du 2026-08-24 (meme jour) a la synthese d'ouverture de s19 - les quatre questions recoivent reponse, le denominateur se precise a 44/46

La synthese ci-dessus reste lisible telle qu'ecrite au moment de l'ouverture du sprint, avant que
`nadia-prof` ne reponde. Ce qui suit l'amende, sans la reecrire.

`nadia-prof` a repondu aux quatre questions de proxy pendant que la synthese ci-dessus s'ecrivait
(`squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`) : une
acceptation partielle sous conditions (denombrement, moitie "lire/ecrire/decomposer"), deux refus
routes vers une famille de mecanique future (figures planes -> visuel ; decodage syllabique ->
audio par emission), et un refus qui reclasse une ligne au statut `non couvrable en l'etat`
(relire et corriger son texte, qui rejoint "Lire a voix haute..." - meme statut, motif distinct,
detail dans `DEC-0042`).

**Consequence sur le tableau ci-dessus** : aucune des trois premieres reponses ne bouge une colonne
- aucune story n'est ouverte ce sprint sur ces trois lignes, le perimetre de developpement de s19
reste celui fixe par `DEC-0042` (`S19-1` a `S19-6`, inchange). La quatrieme reponse, elle, deplace
une ligne :

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide | non couvrable en l'etat |
|---|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 1 | 4 | 0 |
| mathematiques CM1 | 12 | 6 | 2 | 4 | 0 |
| francais CP | 12 | 9 | 2 | 0 | 1 |
| francais CM1 | 11 | 5 | 3 | 2 | 1 |
| **Total** | **46** | **26** | **8** | **10** | **2** |

Seule "Relire et corriger son texte" quitte `non couvert` pour `non couvrable en l'etat` dans
francais CM1 (6 -> 5 non couvert, 0 -> 1 non couvrable). Aucune autre cellule ne bouge par rapport
au tableau d'ouverture ci-dessus.

**Le denominateur, mis a jour.** Deux lignes portent desormais le statut `non couvrable en l'etat` :
"Lire a voix haute..." et "Relire et corriger son texte...". Le denominateur atteignable passe de
**45** a **44** sur **46**. Le compte de lignes `couvert-valide` ne change pas : **10**. **10/45
(22,2 %) devient 10/44 (22,7 %)** - troisieme valeur du meme chiffre en une seule journee (21,7 %
a la cloture de s18, 22,2 % a l'ouverture de s19, 22,7 % apres la reponse de `nadia-prof`), aucune
des trois etapes n'etant un mouvement de couverture reel : je le repete ici pour la meme raison
qu'a chaque fois, et parce que trois chiffres le meme jour est precisement le genre de situation ou
un rapport de sprint pourrait, par erreur, en garder un au hasard plutot que le dernier assume.

**Le recompte des familles de mecanique pour les 35 lignes `non couvert` de s13** (contenu seul,
visuel, saisie, audio, hors de portee) et l'horizon de reserve de contenu seul revise a quatre-cinq
sprints (au lieu de quatre-six) : `DEC-0042`, amendement du 2026-08-24, section dediee. Je ne les
reproduis pas ici, cette matrice n'etant pas le lieu de la methode de recomptage.

**Ce qui ne bouge pas** : les six stories du sprint (`S19-1` a `S19-6`) et leur perimetre de
developpement, geles par `DEC-0042` et non rouverts par cet amendement. Aucune ligne ne passe
`couvert-valide` par ce geste ; ce statut reste entierement celui de `nadia-prof`, apres audit reel
d'un increment livre.

## Amendement du 2026-08-24 (troisieme passage) - deux lignes passent `couvert-valide`, 12/44 (27,3 %)

`nadia-prof` a audite les quatre paquets neufs du Lot A de `DEC-0042` (branche `s19-nadia`, commit
`ddb0fc4`). Deux lignes francais CM1 passent `couvert-valide`, verifiees une par une ci-dessus dans
la section francais CM1 : "Utiliser des synonymes et antonymes pour preciser ou nuancer un propos"
et "Accorder en genre et en nombre dans le groupe nominal (determinant/nom/adjectif)". Deux autres
n'y passent pas et restent `en cours` : "Distinguer les principaux types de mots (nom, verbe)"
(deux reserves bloquantes, `NAT-5` et `NAT-9`) et "Accorder le verbe avec son sujet" (`REFUSE`,
correction en cours par `marc-dev`).

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide | non couvrable en l'etat |
|---|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 1 | 4 | 0 |
| mathematiques CM1 | 12 | 6 | 2 | 4 | 0 |
| francais CP | 12 | 9 | 2 | 0 | 1 |
| francais CM1 | 11 | 5 | 1 | 4 | 1 |
| **Total** | **46** | **26** | **6** | **12** | **2** |

Francais CM1 : `en cours` passe de 3 a 1 (les deux lignes qui bougent), `couvert-valide` de 2 a 4.
Aucune autre cellule ne bouge par rapport au tableau precedent de ce meme jour.

**Le chiffre.** Le denominateur atteignable reste **44** sur **46** (deux lignes `non couvrable en
l'etat`, inchangees par ce geste). Le compte de lignes `couvert-valide` passe de **10** a **12**.
**10/44 (22,7 %) devient 12/44 (27,3 %)**, un gain de 4,6 points. **C'est le premier mouvement reel
de couverture depuis s18** : deux increments joues et audites par `nadia-prof`, sur deux paquets
neufs distincts, avec verdict `VALIDE SOUS RESERVE` sans reserve bloquante chacun.

**Perimetre exact de ce qui est couvert, pour que ce chiffre ne soit pas cru sur parole plus tard**
que je ne l'ai moi-meme cru sur celle de `nadia-prof` : le detail jeu par jeu, reserve par reserve,
est dans la section francais CM1 ci-dessus (amendement du 2026-08-24, second passage). Rien de ce
gain ne depend d'une mesure hors la reference du produit (744 x 551, `DEC-0040`) ; les residus
connus dans la bande 576-719 px (`SYN-16`/`S19-6a`) et sous le pli a la reference (`SYN-12`,
`ANT-8`, `AGN-1`) sont tous non bloquants et deja ouverts avant ce geste.

**Ce qui ne bouge pas.** Le perimetre de developpement de s19 (`S19-1` a `S19-6`, `DEC-0042`) est
inchange. Aucune autre ligne ne passe `couvert-valide` par ce geste.

## Amendement du 2026-08-24 (quatrieme passage) - S19-3 reauditee, une troisieme ligne passe couvert-valide, 13/44 (29,5 %)

`nadia-prof` a reaudite `cm1-accord-sujet-verbe` sur l'arbre fusionne apres le correctif de
`marc-dev` (branche `s19-nadia`, commit `a81324c`) : **`VALIDE SOUS RESERVE`, aucune reserve
bloquante**, les cinq motifs de son refus du meme jour leves un par un et verifies par sa propre
mesure. Verifie moi-meme avant de faire bouger la ligne (`squad/README.md`, section 9, etape 6),
detail complet dans la section francais CM1 ci-dessus. La ligne "Accorder le verbe avec son sujet"
passe `en cours` -> `couvert-valide`.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide | non couvrable en l'etat |
|---|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 1 | 4 | 0 |
| mathematiques CM1 | 12 | 6 | 2 | 4 | 0 |
| francais CP | 12 | 9 | 2 | 0 | 1 |
| francais CM1 | 11 | 5 | 0 | 5 | 1 |
| **Total** | **46** | **26** | **5** | **13** | **2** |

Francais CM1 : `en cours` passe de 1 a 0, `couvert-valide` de 4 a 5. Aucune autre cellule ne bouge
par rapport au tableau precedent de ce meme jour.

**Le chiffre, avec sa base.** Le denominateur atteignable reste **44** sur **46**, inchange par ce
geste. Le compte de lignes `couvert-valide` passe de **12** a **13**. **12/44 (27,3 %) devient
13/44 (29,5 %)**, un gain de 2,3 points sur ce seul mouvement - troisieme et dernier mouvement de
couverture de la journee, apres 22,7 % a l'ouverture de s19 et 27,3 % au troisieme passage de cet
amendement.

**Perimetre exact de ce qui est couvert.** Trois reserves non bloquantes restent ouvertes et
nommees dans la section francais CM1 ci-dessus : `ASV-1` (vingt indices sur vingt-et-un sous le
pli, chantier vertical de s20), `ASV-2` (terminaison resolue par appariement de fin de mot sur
dix-huit items, aucune demande), `ASV-3` (un item ou la reponse est contenue dans l'infinitif
nomme). **Ce paquet ne couvre rien dans la bande 576-719 px** : sa largeur necessaire (647 px)
depasse les 500 px qu'elle y offre, et il s'y repliera tant que `S19-6a` n'est pas traitee - la
bande basse concerne desormais trois paquets nommes (`cm1-synonymes`, `cm1-antonymes`,
`cm1-accord-sujet-verbe`), pas un seul, donnee versee a l'arbitrage de perimetre de `DEC-0043`.

**Ce qui ne bouge pas.** Le perimetre de developpement de s19 (`S19-1` a `S19-6`, `DEC-0042`) est
inchange. Aucune autre ligne ne passe `couvert-valide` par ce geste.
