# Matrice de couverture - recreo

Cette matrice est l'outil de pilotage le plus important de `camille-po`. Elle liste, une ligne
par competence, l'integralite des dix-neuf referentiels desormais disponibles
(`squad/programme/mathematiques/competences-cp.md`, `competences-cm1.md`, `competences-ce1.md`,
`squad/programme/francais/competences-cp.md`, `competences-cm1.md`,
`squad/programme/questionner-le-monde/competences-cp.md`,
`squad/programme/histoire-geographie/competences-cm1.md`,
`squad/programme/langues-vivantes/competences-cp.md`, `competences-cm1.md`,
`squad/programme/arts-plastiques/competences-cp.md`, `competences-cm1.md`,
`squad/programme/education-musicale/competences-cp.md`, `competences-cm1.md`,
`squad/programme/education-physique-et-sportive/competences-cp.md`, `competences-cm1.md`,
`squad/programme/enseignement-moral-et-civique/competences-cp.md`, `competences-cm1.md`,
`squad/programme/histoire-des-arts/competences-cm1.md`,
`squad/programme/sciences-et-technologie/competences-cm1.md`), et l'etat reel du produit face a
chacune d'entre elles sur la branche `round-two`.

**Note du 2026-09-23 (s40)** : six lignes fermees, aucune ouverture partielle, un refus leve dans
le sprint. **Fermetures (`non couvert` -> `couvert-valide`)** : `SCI-CM1-09` (nutrition CM1,
`FICHE-bandit-manchot-nutrition-cm1.md`), `EMC-CM1-04` (regles de la vie collective CM1,
`FICHE-bandit-manchot-regles-vie-collective-cm1.md`), `HDA-CM1-05` (relier une oeuvre a son
contexte CM1, premiere ligne d'histoire des arts du produit,
`FICHE-bandit-manchot-histoire-des-arts-relier-contexte-cm1.md`), `SCI-CM1-20` (extraire une
information d'un document texte et tableau CM1, couverture partielle,
`FICHE-bandit-manchot-sciences-extraire-information-cm1.md`) - les quatre **VALIDE, sans
reserve**. `EMC-CP-09` (devise CP, `FICHE-bandit-manchot-devise-cp.md`) - **VALIDE SOUS RESERVE**
(`DVS-CP-R1`, non bloquante : le verbe *illustre* est trop soutenu pour un enonce de CP). `EMC-CM1-08`
(distinguer une regle d'une loi CM1, `FICHE-bandit-manchot-regle-loi-cm1.md`) - **refuse au premier
audit** (le verbe de reponse attendu etait deja ecrit dans l'enonce de la tache B), corrige par
`marc-dev` et **VALIDE** au second audit le meme jour. Le taux passe de **82 / 155 = 52,9 %** a
**88 / 155 = 56,8 %**. Detail et recompte complets dans la section "Synthese apres les verdicts du
sprint 40".

**Note du 2026-09-22 (s39)** : quatre lignes fermees, deux ouvertes, sur six lots audites par
`nadia-prof` apres avoir joue le jar servi. **Fermetures (`non couvert` -> `couvert-valide`)** :
`MAT-CE1-13` (monnaie CE1, `FICHE-bandit-manchot-monnaie-ce1.md`), `SCI-CM1-04` (energie CM1,
`FICHE-bandit-manchot-energie-cm1.md`), `ART-CP-11` (outils arts plastiques CP,
`FICHE-bandit-manchot-outils-arts-cp.md`), `MAT-CE1-16` (solides CE1,
`FICHE-bandit-manchot-solides-ce1.md`) - les quatre **VALIDE, sans reserve**. **Ouvertures
partielles (`non couvert` -> `en cours`)** : `MAT-CP-01` (denombrement CP, sous-parties lire/ecrire/
decomposer **VALIDE**, verbe "denombrer" non servi par `DEN-2`,
`FICHE-bandit-manchot-nombres-jusqu-a-100-cp.md`) et `MAT-CE1-09` (longueurs CE1, sous-partie
comparer/utiliser cm-m **VALIDE**, verbe "mesurer avec une regle graduee" non servi,
`FICHE-bandit-manchot-longueurs-ce1.md`). Le taux passe de **78 / 155 = 50,3 %** a
**82 / 155 = 52,9 %** (quatre lignes au numerateur ; les deux ouvertures ne le touchent pas).
Detail et recompte complets dans la section "Synthese apres les verdicts du sprint 39".

**Note du 2026-09-22 (s38)** : fermeture de `MAT-CE1-03`. Les quatre pieces de la ligne (additions,
soustractions, complements a la dizaine pris pour eux-memes, moities) ont chacune recu un Done
pedagogique de `nadia-prof` apres avoir joue le jar servi : additions **VALIDE**, soustractions
**VALIDE SOUS RESERVE** (reserve non bloquante `SOU-CE1-R1`), complements-dizaine **VALIDE**,
moities **VALIDE**. `MAT-CE1-03` passe de `en cours` a `couvert-valide`. Le taux passe de
**77 / 155 = 49,7 %** a **78 / 155 = 50,3 %**. Detail et recompte complets dans la section
"Mathematiques - CE1 (cycle 2)", sous-section "Fermeture de `MAT-CE1-03` (s38)".

**Note du 2026-09-22 (s37)** : premier mouvement sur une ligne CE1. `MAT-CE1-03` (calcul mental,
additions et soustractions dans la limite de 100) passe de `non couvert` a `en cours` sur Done
pedagogique **VALIDE SOUS RESERVE** de `nadia-prof` pour le premier paquet CE1 du produit
(`S37-1`, sous-partie "additions" seule ; `squad/conformite/FICHE-bandit-manchot-additions-ce1.md`).
Le taux affiche, deja a **77 / 155 = 49,7 %** depuis la fermeture de `QLM-CP-11` a l'integration de
s36 (`f118c17a`, non reprise par la note du 2026-09-21 ci-dessous, qui date d'avant cette fermeture),
**n'est pas modifie par ce mouvement** : une ligne qui passe de `non couvert` a `en cours` ne touche
pas le numerateur. Detail et recompte complets dans la section "Mathematiques - CE1 (cycle 2)",
sous-section "Premier mouvement sur une ligne CE1".

**Note du 2026-09-21, mise a jour le meme jour** : le perimetre s'ouvre a un troisieme niveau, CE1
(cycle 2), sur decision de `DEC-0089` - CM2 reste explicitement differe. `nadia-prof` a ecrit le
premier referentiel CE1 (mathematiques, 17 competences, `squad/programme/mathematiques/
competences-ce1.md`) le jour meme ; ses dix-sept lignes sont desormais integrees ci-dessous
(section "Mathematiques - CE1 (cycle 2)"), a l'etat `non couvert`, exactement comme cette note
l'annoncait par avance. Le denominateur atteignable passe de 138 a 155, le taux de 76/138 = 55,1 %
a 76/155 = 49,0 % - **par le seul effet du denominateur**, avant qu'aucun paquet ou jeu CE1
n'existe ; ce recul n'est pas une regression du produit (detail et recompte complets dans la
section "Mathematiques - CE1 (cycle 2)" ci-dessous).

**Note du 2026-09-03** : le denominateur final du produit, que la note du 2026-09-02 (ci-dessous,
conservee pour l'historique) annoncait comme un evenement futur, **est atteint a cette date**. Les
dix referentiels des enseignements jusque-la absents (arts plastiques, education musicale,
education physique et sportive et enseignement moral et civique aux deux cycles, histoire des arts
et sciences et technologie au cycle 3 seul), ecrits par `nadia-prof` le 2026-09-03 (`DEC-0074`),
sont integres ci-dessous (section "Integration des dix referentiels absents du 2026-09-03"). **Les
huit enseignements du cycle 2 et les dix du cycle 3 sont desormais tous representes par au moins
une ligne de cette matrice.** Ce qui reste a faire n'est plus d'ouvrir des matieres : c'est de
servir les competences que ces dix-huit referentiels portent.

**Note du 2026-09-02** : jusqu'a cette date, cette matrice ne portait que les deux premiers
referentiels (mathematiques, francais). Le programme complet compte **huit enseignements au cycle
2, dix au cycle 3** (`nadia-prof`, `squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-
absentes-du-programme.md`, section 2, corrigee le 2026-09-02 apres un ecart trouve par
`camille-po` puis retrouve par `nadia-prof` elle-meme sur sa propre section 2.1 - voir `DEC-0072`).

**Le critere de comptage que cette matrice emploie, et pourquoi un autre existe** :
un enseignement compte pour un **chapitre de programme portant ses propres attendus de fin de
cycle** - c'est le seul critere qui produise des competences, donc des lignes de cette matrice.
Sur ce critere, *histoire des arts* est un enseignement a part entiere du cycle 3 (attendus
propres), sans avoir d'horaire propre. **Un autre critere existe et donne un autre nombre** :
compter par regroupement de l'arrete des horaires (qui fusionne les deux arts au cycle 2, adosse
l'EMC a *questionner le monde*/l'histoire-geographie, et ne donne aucun volume propre a l'histoire
des arts) rend **six enseignements au cycle 2, sept au cycle 3**. **Aucun des deux comptes n'est
faux ; ils ne comptent pas la meme chose.** Cette matrice emploie et continuera d'employer le
premier (par attendus de programme), parce que c'est celui qui correspond a une ligne de
competence - l'unite que cette matrice compte. **Ce choix a desormais un effet mesure sur le
denominateur** (section "Integration des dix referentiels absents du 2026-09-03" ci-dessous),
la ou il n'en avait aucun le 2026-09-02 puisqu'aucune ligne d'arts, d'EPS, d'EMC, de sciences et
technologie ou d'histoire des arts n'y etait encore entree.

Un taux de couverture cite avant le 2026-09-03 decrit un perimetre incomplet du programme : deux
matieres sur huit ou dix avant le 2026-09-02, quatre matieres sur huit ou dix entre le 2026-09-02
et le 2026-09-03.

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
| mathematiques | CP | Nombres et calculs | Denombrer, lire, ecrire et decomposer les nombres jusqu'a 100 | bandit-manchot-nombres-jusqu-a-100-cp | bandit manchot | **en cours** (2026-09-22, s39) - **couverture partielle.** `nadia-prof` prononce **VALIDE** sur la part servie (`squad/conformite/FICHE-bandit-manchot-nombres-jusqu-a-100-cp.md`) : les 21 items exacts, `DEN-1`, `DEN-4` a `DEN-8`, `GN-1`/`GN-2` toutes tenues, pour les verbes "lire", "ecrire" et "decomposer" seuls. **Le verbe "denombrer" (`DEN-2`) n'est pas servi et ne peut pas l'etre par une mecanique textuelle** (constat verifie sur le code par `theo-archi`, `ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md`, section 1) : la ligne ne passe pas a `couvert-valide` sur la foi de ce Done seul. |
| mathematiques | CP | Nombres et calculs | Comparer et ranger des nombres jusqu'a 100 (<, >, =) | bandit-manchot-comparer-nombres-cp, cp-ranger-nombres | bandit manchot | couvert-valide |
| mathematiques | CP | Nombres et calculs | Calcul mental - additions dans la limite de 10 (puis 20) | bandit-manchot-additions-cp | bandit manchot | couvert-valide |
| mathematiques | CP | Nombres et calculs | Calcul mental - soustractions dans la limite de 10 | bandit-manchot-soustractions-cp | bandit manchot | couvert-valide |
| mathematiques | CP | Nombres et calculs | Complements a 10 | bandit-manchot-complements-10-cp ; **saisie-complements-10-cp** (2026-08-31, s24) | bandit manchot ; **saisie** | couvert-valide - **statut inchange par le second jeu.** `CPL-1` : *"deux entrees sur une meme ligne ne sont pas une double couverture"*. La ligne etait deja verte avant s24 et le lot de saisie **n'ajoute aucune ligne** |
| mathematiques | CP | Nombres et calculs | Resoudre des problemes simples d'addition/soustraction en situation (1 etape) | bandit-manchot-problemes-additifs-cp | bandit manchot | couvert-valide |
| mathematiques | CP | Nombres et calculs | Reconnaitre et utiliser la monnaie (pieces/billets simples) dans des problemes concrets | bandit-manchot-monnaie-cp | bandit manchot ; **item illustre (palier 1)** | **couvert-valide** (rejoue et reconfirme le 2026-09-03, s27, `DEC-0072` section 9) - **ligne entiere, restauree a son etat d'avant le correctif de couleur, pas une fermeture neuve de s27.** `nadia-prof` a rejoue les douze items sur les trois proprietes annoncees (`COU-2`, `COU-3`, lisibilite en tant que monnaie) et confirme VALIDE. `COU-2` et `COU-3` restent sans garde executable |
| mathematiques | CP | Grandeurs et mesures | Comparer des longueurs, des masses, des contenances (sans instrument gradue) | bandit-manchot-comparer-cp | bandit manchot | couvert-valide |
| mathematiques | CP | Grandeurs et mesures | Se reperer dans la journee, la semaine ; lire l'heure pleine sur une horloge | bandit-manchot-semaine-cp | bandit manchot | **couvert-valide** (2026-09-01, s25) - **couverture partielle, mention exigee par `SEM` et reprise mot pour mot** : *"moitie 'se reperer dans la journee, la semaine' seule ; la moitie 'lire l'heure pleine sur une horloge' reste non couverte"*. **Sans cette mention, la ligne dirait plus que ce que le jeu sert** |
| mathematiques | CP | Espace et geometrie | Se reperer et decrire des deplacements sur un quadrillage simple | - | - | non couvert - palier 2 requis, pour une moitie seulement : *se reperer et se deplacer* sur un quadrillage est une designation sur un dessin et rien d'autre ne la sert ; *decrire un deplacement* n'est pas une designation et se sert en choix textuel, dans un second paquet. La ligne ne ferme donc pas par le palier 2 seul (`nadia-prof`, `ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md`, section 17.7). Mention de couverture partielle due, reprise mot pour mot : "moitie 'se reperer et se deplacer sur un quadrillage' seule ; la moitie 'decrire un deplacement' reste non couverte" - due tant que le second paquet textuel n'existe pas. Ecart signale par `ADR-0080` le 2026-09-03, non paye pendant cinq jours : cette cellule etait vide de tout motif. |
| mathematiques | CP | Espace et geometrie | Reconnaitre et nommer quelques figures planes (carre, rectangle, triangle, cercle) | bandit-manchot-figures-planes-cp | bandit manchot ; **item illustre (palier 1)** | **couvert-valide** (2026-09-01, s25) - **ligne entiere, sans mention de couverture partielle**, `nadia-prof` le dit en ces termes. Reserve d'indice **levee** a la cloture : designation de 8 sur 12 a **0 sur 12** |

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

**Cette precision est soldee le 2026-08-30 (s23), sans etre effacee : elle disait vrai a sa date, et
elle ne dit plus rien aujourd'hui.** `cp-ranger-nombres` (`S23-RAN`), un second jeu sur la meme
mecanique, couvre desormais la moitie "ranger" que `bandit-manchot-comparer-nombres-cp` ne couvrait
pas - `nadia-prof` prononce **VALIDE**, sans reserve, le 2026-08-30 (reaudit apres levee de
`RAN-7`, `squad/conformite/FICHE-bandit-manchot-ranger-nombres-cp.md`). La phrase "le 'ranger' [...]
reste hors du perimetre de ce jeu" ci-dessus reste exacte : elle parlait du seul jeu de comparaison,
qui n'a pas change. Ce qui a change est qu'un second jeu existe. La ligne passe `en cours` ->
`couvert-valide`, colonne "Jeu(x) couvrant" portant desormais les deux noms. Detail complet dans
l'amendement du 2026-08-30 (s23, en cours) en bas de fichier.

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
| mathematiques | CM1 | Nombres et calculs | Calcul mental - multiplications et divisions simples (multiples de 10, 100) | bandit-manchot-multiplications-divisions-cm1 | bandit manchot | couvert-valide |
| mathematiques | CM1 | Nombres et calculs | Effectuer une multiplication posee d'un nombre a 2-3 chiffres par un nombre a 1-2 chiffres | bandit-manchot-multiplication-posee-cm1 | bandit manchot | **couvert-valide** (2026-09-04, s28) - **`MAT-CM1-04` ferme.** Onze items, quatre combinaisons du libelle servies (2x1, 3x1, 2x2, 3x2, `POS-4`), chaque distracteur une erreur classique nommee et verifiee (retenue oubliee, produit partiel non decale, `POS-3`), item non resoluble sans poser l'operation (`POS-2`, mordue par un test). Onze produits recalcules un par un, aucune erreur. Aucune reserve. **Mention exigee d'avance par `nadia-prof`, a reprendre mot pour mot, sans laquelle elle ne ferme pas la ligne** : "le resultat atteste le calcul ; la technique posee elle-meme - disposition, retenues, produits partiels - n'est ni montree ni verifiee par le produit." |
| mathematiques | CM1 | Nombres et calculs | Effectuer une division euclidienne simple (quotient et reste) par un diviseur a 1 chiffre | bandit-manchot-division-posee-cm1 | bandit manchot | **couvert-valide** (2026-09-04, s28) - **`MAT-CM1-05` ferme.** Onze items, diviseur a un chiffre sur tous, quotient et reste demandes a chaque fois, reste nul sur deux items et non nul sur les neuf autres (`POS-4`). Chaque distracteur une erreur classique arithmetiquement coherente (reste superieur au diviseur, quotient sans reste, `POS-3`). Onze divisions recalculees (quotient et reste) une par une, aucune erreur. **Mention exigee d'avance par `nadia-prof`, a reprendre mot pour mot, sans laquelle elle ne ferme pas la ligne** : "le resultat atteste le calcul ; la technique posee elle-meme - disposition, retenues, produits partiels - n'est ni montree ni verifiee par le produit." **`POS-1` reste due, inchangee.** `POS-R1` (structure des distracteurs donnant la reponse sans diviser) **levee le 2026-09-06, s29**, apres rejeu et correction (`8644932`, `130a538`). Reserve non bloquante nouvelle, datee du 2026-09-06 : **`POS-R2`** - une troisieme lecture donne la bonne reponse sans poser de division sur 7 des 11 items (63,6 % contre 33,3 % au hasard, le reste correct de la bonne reponse etant toujours majoritaire ou unanime parmi les distracteurs) |
| mathematiques | CM1 | Nombres et calculs | Notion de fraction simple (unites fractionnaires, fractions inferieures a 1) et reperage sur une droite numerique | bandit-manchot-fractions-cm1 | bandit manchot | **couvert-valide** (2026-08-31, s24) - **couverture partielle, mention exigee par `FRA-9` et reprise telle quelle** : *"moitie 'notion de fraction simple' seule ; la moitie 'reperage sur une droite numerique' reste non couverte"* |
| mathematiques | CM1 | Nombres et calculs | Resoudre des problemes a plusieurs etapes mobilisant les 4 operations | bandit-manchot-problemes-etapes-cm1 | bandit manchot | couvert-valide |
| mathematiques | CM1 | Grandeurs et mesures | Convertir des unites de longueur, masse, contenance dans le systeme metrique | bandit-manchot-conversions-unites-cm1 | bandit manchot | couvert-valide |
| mathematiques | CM1 | Grandeurs et mesures | Calculer un perimetre de figures usuelles (carre, rectangle) | bandit-manchot-perimetre-cm1 | bandit manchot | couvert-valide |
| mathematiques | CM1 | Grandeurs et mesures | Resoudre des problemes de durees (calcul d'une duree, d'un horaire d'arrivee) | bandit-manchot-problemes-durees-cm1 | bandit manchot | couvert-valide |
| mathematiques | CM1 | Espace et geometrie | Reconnaitre, nommer et tracer des figures planes (carre, rectangle, triangle, cercle) avec outils (regle, equerre, compas) | - | - | non couvrable en l'etat |
| mathematiques | CM1 | Espace et geometrie | Reconnaitre et utiliser la notion de symetrie axiale simple | - | - | non couvrable en l'etat |

**Note du 2026-08-30 (ouverture s24, `camille-po`) - "Notion de fraction simple..." passe
`non couvert` -> `en cours`, avec une couverture partielle a nommer d'avance.** `DEC-0059` ouvre
`S24-FRA` sur la **moitie "notion" seule**, sur l'arbitrage prealable de `nadia-prof` du 2026-08-30
(`squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md`,
section 8bis, conditions `FRA-1` a `FRA-9`), qui rend actionnable son avis du 2026-08-14. **La
mention que `FRA-9` exige sur cette ligne le jour ou elle bougerait vers `couvert-valide`, ecrite par
elle et non par moi, et qui est une condition et non une remarque** : *"moitie 'notion de fraction
simple' seule ; la moitie 'reperage sur une droite numerique' reste non couverte"*. Son avis de fond
est inchange : le reperage exige une ligne reellement dessinee et graduee, famille visuelle. Je
l'ecris des l'ouverture pour qu'elle ne se perde pas entre le planning et l'audit.

**Note du 2026-08-25 (hors sprint, `camille-po`) - deux lignes reclassees `non couvert` ->
`non couvrable en l'etat`, nature reversible** (`DEC-0046`, sur avis de `nadia-prof`,
`squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`, amendement du
2026-08-25, section A) : l'utilisatrice a arbitre qu'aucun geste de trace n'est envisage pour ce
produit. **"Reconnaitre, nommer et tracer des figures planes ... avec outils"** exige le trace et
les outils - la seule part propre au CM1 selon `nadia-prof`, qui refuse qu'une reconnaissance et
une denomination seules comptent (facade dupliquant le CP). **"Reconnaitre et utiliser la notion de
symetrie axiale simple"** exige un trace pour sa moitie "utiliser" (completer une figure par
symetrie) ; `nadia-prof` n'a pas rendu, pour cette ligne, le jugement qu'elle a rendu pour la
precedente sur la valeur d'une couverture partielle du seul verbe "reconnaitre" - la ligne suit
neanmoins le meme classement, par le motif de capacite qui, lui, est etabli pour les deux verbes
que la ligne joint. **Cette ligne peut rouvrir `en cours` sur le seul verbe "reconnaitre" le jour
ou `nadia-prof` juge qu'une telle couverture partielle est honnete** ; ce jugement n'existe pas
aujourd'hui et je ne le presume pas.

**Nature "reversible", distincte de l'irreversible** (voir `DEC-0046`, section 4.1) : ces deux
lignes sont hors de portee parce que le produit a choisi de ne pas avoir le geste de trace - un
arbitrage, pas une loi, qui se revise si l'utilisatrice revise sa phrase. Cela les distingue de
"Lire a voix haute..." et de "Relire et corriger son texte..." ci-dessous, hors de portee parce que
juger la reponse demande un humain, ce qu'aucune decision produit ne debloquera jamais.

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

## Mathematiques - CE1 (cycle 2)

Source : `squad/programme/mathematiques/competences-ce1.md`, ecrit par `nadia-prof` le 2026-09-21
sur le routage de `DEC-0089` (section 3). Dix-neuvieme tableau de cette matrice - les dix-huit
precedents portaient tous CP ou CM1 - et le premier a porter le niveau CE1, troisieme niveau du
produit ouvert par `DEC-0089`, CM2 restant explicitement differe. Aucune ligne n'est encore
`couvert-valide` ni `en cours` : aucun paquet de contenu CE1 n'existe.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| mathematiques | CE1 | Nombres et calculs | Denombrer, lire, ecrire et decomposer les nombres jusqu'a 1000 (unites, dizaines, centaines) | - | - | non couvert |
| mathematiques | CE1 | Nombres et calculs | Comparer, ranger et encadrer les nombres jusqu'a 1000, et les placer sur une ligne graduee | - | - | non couvert |
| mathematiques | CE1 | Nombres et calculs | Calcul mental - additions et soustractions dans la limite de 100 (complements a la dizaine, doubles et moities) | bandit-manchot-additions-ce1 ; bandit-manchot-soustractions-ce1 ; bandit-manchot-complements-dizaine-ce1 ; bandit-manchot-moities-ce1 | bandit manchot | **couvert-valide** (2026-09-22, s38). **`MAT-CE1-03` ferme sur ses quatre pieces**, chacune auditee par `nadia-prof` apres avoir joue le jar servi. **Additions** (`S37-1`, deja `VALIDE SOUS RESERVE` a s37) : les deux reserves `ADD-CE1-R1` (indice des cinq items "ajout d'un nombre rond" s'arretant une etape trop tot) et `ADD-CE1-R2` (`48 + 26` sans la retenue oubliee en distracteur) sont **corrigees et fermees a s38** ; verdict desormais **VALIDE, sans reserve** (`squad/conformite/FICHE-bandit-manchot-additions-ce1.md`, sections 7, 8 et 11). **Soustractions** (`S38-SOUSTRACTIONS`) : `nadia-prof` prononce **VALIDE SOUS RESERVE** (32 differences exactes, `SOU-CE1-1`/`SOU-CE1-2` tenues) ; une reserve non bloquante, **`SOU-CE1-R1`** - sur quatre items de la forme "un chiffre soustrait" (`45 - 3`, `57 - 4`, `68 - 5`, `79 - 6`), le second distracteur ne correspond a aucune forme autorisee ni a une erreur de soustraction plausible ; correctif deja donne par `nadia-prof` (remplacer par la somme `a + b`, soit `48`, `61`, `73`, `85`), routee comme story de correction differee dans `docs/backlog/backlog.md`, section "Backlog - sprints suivants (intentions non detaillees)" (`squad/conformite/FICHE-bandit-manchot-soustractions-ce1.md`, section 4). **Complements a la dizaine pris pour eux-memes** (`S38-COMPLEMENTS-DIZAINE`, forme `47 + ? = 50`) : **VALIDE, aucune reserve** (18 items exacts, `CDZ-CE1-1`/`CDZ-CE1-2` tenues ; `squad/conformite/FICHE-bandit-manchot-complements-dizaine-ce1.md`). **Moities** (`S38-MOITIES`) : **VALIDE, aucune reserve** (21 items exacts, `MOI-CE1-1`/`MOI-CE1-2` tenues ; `squad/conformite/FICHE-bandit-manchot-moities-ce1.md`). **La reserve `SOU-CE1-R1`, non bloquante, n'empeche pas la fermeture de la ligne** : un Done pedagogique sous reserve non bloquante reste un Done pedagogique, exactement comme `ADD-CE1-R1`/`ADD-CE1-R2` ne l'empechaient pas a s37. |
| mathematiques | CE1 | Nombres et calculs | Poser et effectuer une addition avec retenue (nombres a deux ou trois chiffres) | - | - | non couvert |
| mathematiques | CE1 | Nombres et calculs | Poser et effectuer une soustraction (nombres a deux ou trois chiffres) | - | - | non couvert |
| mathematiques | CE1 | Nombres et calculs | Comprendre le sens de la multiplication (addition reiteree, disposition en lignes et en colonnes) | - | - | non couvert |
| mathematiques | CE1 | Nombres et calculs | Calcul mental - tables de multiplication de 2, 5 et 10, puis de 3 et 4 | - | - | non couvert |
| mathematiques | CE1 | Nombres et calculs | Resoudre des problemes d'addition, de soustraction et de multiplication a une ou deux etapes | - | - | non couvert |
| mathematiques | CE1 | Grandeurs et mesures | Mesurer et comparer des longueurs avec une regle graduee ; utiliser le centimetre et le metre | bandit-manchot-longueurs-ce1 | bandit manchot | **en cours** (2026-09-22, s39) - **couverture partielle.** `nadia-prof` prononce **VALIDE** sur la part servie (`squad/conformite/FICHE-bandit-manchot-longueurs-ce1.md`) : les 14 items exacts, `MES-CE1-1` a `MES-CE1-3` (version amendee) toutes tenues, pour "comparer des longueurs" et "utiliser le centimetre et le metre" (conversions) seuls. **Le verbe "mesurer... avec une regle graduee" n'est pas servi et ne peut pas l'etre par du texte seul** (aucune image de regle, aucune lecture de graduation) : la ligne ne passe pas a `couvert-valide` sur la foi de ce Done seul. |
| mathematiques | CE1 | Grandeurs et mesures | Comparer et mesurer des masses et des contenances (gramme, kilogramme, litre) | - | - | non couvert |
| mathematiques | CE1 | Grandeurs et mesures | Lire l'heure sur une horloge (heures, demi-heures, quarts d'heure) et se reperer dans le calendrier (mois, date) | - | - | non couvert |
| mathematiques | CE1 | Grandeurs et mesures | Calculer une duree simple en heures entieres ou en demi-heures | - | - | non couvert |
| mathematiques | CE1 | Grandeurs et mesures | Utiliser la monnaie : composer une somme en euros et en centimes, savoir ce qui est rendu | bandit-manchot-monnaie-ce1 | bandit manchot | **couvert-valide** (2026-09-22, s39) - **`MAT-CE1-13` ferme.** Done pedagogique **VALIDE, sans reserve**, prononce par `nadia-prof` apres avoir joue le jar servi (`squad/conformite/FICHE-bandit-manchot-monnaie-ce1.md`). 12 items exacts (composition et rendu recalcules), `MON-CE1-1` a `MON-CE1-4` toutes tenues. Ecriture exclusive en centimes jugee acceptable, sans reserve. |
| mathematiques | CE1 | Espace et geometrie | Se reperer, se deplacer et coder un deplacement sur un quadrillage | - | - | non couvert |
| mathematiques | CE1 | Espace et geometrie | Reconnaitre, decrire et tracer des figures planes (carre, rectangle, triangle, triangle rectangle, cercle) a la regle et sur quadrillage | - | - | non couvert |
| mathematiques | CE1 | Espace et geometrie | Reconnaitre et nommer des solides usuels (cube, pave droit, boule, cylindre, cone) | bandit-manchot-solides-ce1 | bandit manchot ; item illustre | **couvert-valide** (2026-09-22, s39) - **`MAT-CE1-16` ferme.** Done pedagogique **VALIDE, sans reserve**, prononce par `nadia-prof` apres avoir joue le jar servi et regarde les 15 dessins a l'ecran, a leur taille reelle de rendu (`squad/conformite/FICHE-bandit-manchot-solides-ce1.md`). `SOL-CE1-1` a `SOL-CE1-4` toutes tenues, attention particuliere portee a la lisibilite de l'ellipse d'equateur de la boule sur ses trois tailles - tenue sans reserve. |
| mathematiques | CE1 | Espace et geometrie | Reconnaitre un axe de symetrie et completer une figure par symetrie sur quadrillage | - | - | non couvert |

**Dix-sept lignes**, verifiees contre le referentiel source par recherche des dix-sept codes
`MAT-CE1-01` a `MAT-CE1-17` (aucun doublon, aucun trou) : `grep -oE "MAT-CE1-[0-9]+"
squad/programme/mathematiques/competences-ce1.md`, sur la branche `s36-nadia` (commit `eaaadb45`,
2026-09-21) ou `nadia-prof` l'a ecrit - ce fichier n'est pas encore fusionne sur cette branche-ci.

**Ce que ce referentiel precise et que cette matrice ne reprend pas en colonne** : deux bornes de
lisibilite hors competence (`LON-CE1-1`, au plus vingt mots ; `LON-CE1-2`, au plus trois phrases)
sont posees par `squad/conformite/ARBITRAGE-s36-longueur-d-enonce-au-ce1.md` et s'appliqueront a
tout paquet CE1 futur, en plus de la ligne de competence qu'il declare.

**Effet de perimetre, pose d'avance comme `DEC-0089` section 5 l'exige** : ce dix-neuvieme tableau
ajoute dix-sept lignes `non couvert` au denominateur atteignable de cette matrice. Aucune n'est
`couvert-valide` ni `non couvrable en l'etat` : les 17 entrent entierement dans le denominateur,
aucune n'entre au numerateur.

| | Avant ce referentiel (2026-09-11, cloture de s35) | Apres l'entree du referentiel mathematiques CE1 |
|---|---|---|
| `couvert-valide` | 76 | 76 |
| `en cours` | 0 | 0 |
| `non couvert` | 62 | **79** |
| `non couvrable en l'etat` | 71 | 71 |
| Total | 209 | **226** |
| Denominateur atteignable | 138 | **155** |
| **Taux** | **76 / 138 = 55,1 %** | **76 / 155 = 49,0 %** |

Methode, sur le meme patron que les recomptes de cloture de s34 et de s35 (sections "Le recompte,
independant de tout chiffre transmis" ci-dessous) : les colonnes "Avant" reprennent le recompte
independant deja verifie a la cloture de s35 (couvert-valide 76, en cours 0, non couvert 62, non
couvrable en l'etat 71, total 209, denominateur atteignable 138 - meme section, ci-dessous), pas un
chiffre recopie d'un autre document. La colonne "Apres" ajoute uniquement les dix-sept lignes de ce
tableau, aucun autre mouvement n'a lieu dans ce meme geste. **76 / 155 = 49,0 %, jamais le "155"
avance sans verification par `nadia-prof`** (chiffre juste par coincidence arithmetique - 138 + 17
- mais que je recalcule ici depuis ma propre lecture du referentiel plutot que de le recopier tel
quel) : le taux affiche recule de 6,1 points par le seul effet du denominateur, avant qu'aucun jeu
CE1 n'existe, exactement comme la note du 2026-09-21 en tete de ce fichier l'annoncait d'avance.
**Ce recul n'est pas une regression du produit.**

### Premier mouvement sur une ligne CE1 (2026-09-22, s37, `MAT-CE1-03`)

`MAT-CE1-03` passe de `non couvert` a `en cours` sur Done pedagogique **VALIDE SOUS RESERVE** de
`nadia-prof` (`squad/conformite/FICHE-bandit-manchot-additions-ce1.md`, section 8 : *"Le mouvement
est ecrit par `camille-po`, pas par moi."*). Detail du verdict et des deux reserves non bloquantes
(`ADD-CE1-R1`, `ADD-CE1-R2`) dans la ligne ci-dessus. **Pas `couvert-valide`** : seule la sous-partie
"additions dans la limite de 100" est servie ; les soustractions, les complements a la dizaine pris
pour eux-memes et les moities restent hors perimetre de `S37-1`, motif ecrit d'avance par
`nadia-prof` le 2026-09-21, avant qu'un seul item n'existe.

**Recompte, independant de tout chiffre transmis**, sur le meme patron que les recomptes de cloture
de s34 a s36 : recompte sur les dix-neuf tableaux de cette matrice, colonne `Statut`, avant et apres
ce mouvement unique - chaque ligne de donnees reclassee telle qu'elle est ecrite dans ce fichier,
jamais un total repris d'un autre document. Methode : `grep -cE "^\| [a-z].* \| (CP|CE1|CM1) \|"` sur
`squad/programme/MATRICE-couverture.md` pour isoler les 226 lignes de donnees des tableaux de
synthese (memes colonnes ailleurs dans ce fichier), puis classement du dernier champ (`Statut`) par
prefixe. Les deux lignes de sciences et technologie CM1 (`SCI-CM1-02`, `SCI-CM1-12`) sans pipe de
fermeture (defaut de forme deja signale a la cloture de s36, non corrige ici, hors perimetre de
cette tache) sont comptees a la main : leur cellule `Statut` commence par `**couvert-valide**` dans
les deux cas, verifie par lecture directe des deux lignes. Le chiffre "avant" recompte ainsi
(couvert-valide 77, non couvert 78) confirme, depuis ma propre lecture, celui annonce par le commit
d'integration de s36 (`f118c17a`, "couverture 76/138 -> 77/155") sans le reprendre tel quel.

| | Avant ce mouvement (2026-09-22, avant `S37-1`) | Apres le mouvement de `MAT-CE1-03` |
|---|---|---|
| `couvert-valide` | 77 | 77 |
| `en cours` | 0 | **1** |
| `non couvert` | 78 | **77** |
| `non couvrable en l'etat` | 71 | 71 |
| Total | 226 | 226 |
| Denominateur atteignable | 155 | 155 |
| **Taux** | **77 / 155 = 49,7 %** | **77 / 155 = 49,7 %** |

**Le taux n'est pas modifie par ce mouvement.** Une ligne qui passe de `non couvert` a `en cours` ne
touche pas le numerateur (`couvert-valide` seul) : c'est arithmetique, pas un arrondi qui masquerait
un ecart. Le denominateur atteignable (total moins `non couvrable en l'etat`) ne bouge pas non plus,
`en cours` y comptant deja comme `non couvert`. **Ce que ce recompte ne fait pas** : il ne prononce
aucun Done - le verdict est de `nadia-prof`, dans la fiche citee ; il ne cree ni ne ferme aucune
autre ligne CE1 ; il ne fixe aucun perimetre pour la suite de s37.

### Fermeture de `MAT-CE1-03` (s38)

`MAT-CE1-03` passe de `en cours` a `couvert-valide`. `nadia-prof` a joue le jar servi des quatre
pieces de la ligne et prononce un Done pedagogique sur chacune (verdicts et motifs complets dans la
ligne ci-dessus et dans les quatre fiches citees) : additions **VALIDE** (les deux reserves de s37
fermees), soustractions **VALIDE SOUS RESERVE** (`SOU-CE1-R1`, non bloquante), complements a la
dizaine **VALIDE**, moities **VALIDE**. Une reserve non bloquante est un Done pedagogique ouvert,
pas un Done refuse (`squad/conformite/FICHE-bandit-manchot-soustractions-ce1.md`, bloc de
validation) : elle ne retient donc pas la fermeture de la ligne.

**Recompte, independant de tout chiffre transmis**, sur le meme patron que le recompte de s37
ci-dessus : un seul mouvement de statut a ce sprint (`MAT-CE1-03`, `en cours` -> `couvert-valide`),
sur les dix-neuf tableaux de cette matrice, colonne `Statut`. Base "avant" reprise du recompte
verifie a la cloture de s37 ci-dessus (couvert-valide 77, en cours 1, non couvert 77, non couvrable
en l'etat 71, total 226, denominateur atteignable 155), pas un chiffre recopie d'un autre document.

| | Avant ce mouvement (2026-09-22, avant fermeture de `MAT-CE1-03`) | Apres la fermeture de `MAT-CE1-03` |
|---|---|---|
| `couvert-valide` | 77 | **78** |
| `en cours` | 1 | **0** |
| `non couvert` | 77 | 77 |
| `non couvrable en l'etat` | 71 | 71 |
| Total | 226 | 226 |
| Denominateur atteignable | 155 | 155 |
| **Taux** | **77 / 155 = 49,7 %** | **78 / 155 = 50,3 %** |

**Ce que ce recompte ne fait pas** : il ne prononce aucun Done - les quatre verdicts sont de
`nadia-prof`, dans les fiches citees ; il ne cree ni ne ferme aucune autre ligne CE1 ; il ne solde
pas la reserve `SOU-CE1-R1`, routee comme dette a un sprint futur dans `docs/backlog/backlog.md`,
section "Backlog - sprints suivants (intentions non detaillees)".

## Francais - CP (cycle 2)

Source : `squad/programme/francais/competences-cp.md`.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| francais | CP | Langage oral et conscience phonologique | Denombrer les syllabes orales d'un mot (segmentation syllabique) | bandit-manchot-syllabes-cp | bandit manchot | couvert-valide |
| francais | CP | Langage oral et conscience phonologique | Localiser une syllabe dans un mot (debut / milieu / fin) | bandit-manchot-position-syllabe-cp | bandit manchot ; champ d'item (`ADR-0075`) | **couvert-valide** (2026-09-01, s26) - `VOI-12` tenue. `VOI-3`, `VOI-5`, `VOI-8` (ce que l'adulte fait reellement de sa voix) restent sans garde executable (mention ajoutee le 2026-09-04, routee par `nadia-prof`, s28) |
| francais | CP | Langage oral et conscience phonologique | Identifier un son (phoneme) dans un mot | bandit-manchot-son-dans-mot-cp | bandit manchot ; champ d'item (`ADR-0075`) | **couvert-valide** (2026-09-01, s26) - `VOI-12` tenue. `VOI-3`, `VOI-5`, `VOI-8` restent sans garde executable (mention ajoutee le 2026-09-04, routee par `nadia-prof`, s28) - **la ligne la plus exposee** : son indice invite l'adulte a redire le mot "lentement", ce que `VOI-3` interdit ; correction ecrite, reportee a s29 (`docs/backlog/backlog.md`) |
| francais | CP | Lecture et comprehension de l'ecrit | Associer les graphemes aux phonemes (voyelles, consonnes simples, puis digraphes ch, ou, on...) | bandit-manchot-le-bon-son-cp ; bandit-manchot-lettre-du-son-cp ; bandit-manchot-digraphe-du-son-cp ; bandit-manchot-voyelle-du-son-cp | bandit manchot ; champ d'item a voix haute (`ADR-0075`) | **couvert-valide** (2026-09-03, s27) - **ligne fermee, seule fermeture du sprint.** `bandit-manchot-voyelle-du-son-cp` sert les quatre voyelles simples (`a, i, o, u`) en bonne reponse, seize sons verifies un par un par `nadia-prof` ; clauses 2, 3 et 4 de sa barre du 2026-08-10 deja remplies depuis s26. `VOI-9` (deux syllabes au plus) ne gouverne pas ce paquet - `nadia-prof` le dit d'avance dans `squad/conformite/FICHE-bandit-manchot-voyelle-du-son-cp.md`, section 6 : cette clause vise la ligne ou l'enfant ECRIT le mot, pas celle ou il designe la lettre du premier son. `VOI-3`, `VOI-5`, `VOI-8` restent sans garde executable (mention ajoutee le 2026-09-04, routee par `nadia-prof`, s28) ; consigne et enonce se repetent sur trois des quatre jeux, correction reportee a s29 |
| francais | CP | Lecture et comprehension de l'ecrit | Decoder un mot regulier inconnu par assemblage syllabique | - | - | non couvert |
| francais | CP | Lecture et comprehension de l'ecrit | Lire a voix haute un texte court et simple avec fluidite croissante | - | - | non couvrable en l'etat |
| francais | CP | Lecture et comprehension de l'ecrit | Comprendre un texte narratif tres court (questions explicites : qui, ou, quoi) | bandit-manchot-texte-narratif-cp | bandit manchot | **couvert-valide** (2026-08-31, s24, second passage - reserve levee) |
| francais | CP | Ecriture | Copier un mot, une phrase courte sans erreur, en respectant l'espacement | - | - | **non couvrable en l'etat** (2026-08-31, s25, DEC-0066) - **contrainte fondatrice, nature reversible** : la competence est graphomotrice, et DEC-0046 a ecarte tout geste de trace pour ce produit. nadia-prof, 2026-08-31 : *"ni un clavier ni un choix ne produisent un geste d'ecriture ; former des lettres est un geste de trace"* |
| francais | CP | Ecriture | Ecrire de maniere autonome un mot simple en respectant les correspondances grapheme/phoneme connues | saisie-mot-dicte-cp | saisie ; champ d'item (`ADR-0075`) | **couvert-valide** (2026-09-01, s26) - `VOI-12` tenue, reserve levee sans condition. `VOI-3`, `VOI-5`, `VOI-8` restent sans garde executable (mention ajoutee le 2026-09-04, routee par `nadia-prof`, s28) - ce jeu est conforme sur le fond (`VOI-8`), aucun defaut connu |
| francais | CP | Orthographe et grammaire | Reconnaitre et utiliser les marques du pluriel les plus frequentes (-s) | bandit-manchot-pluriel-cp | bandit manchot | **couvert-valide** (2026-08-31, s24) |
| francais | CP | Orthographe et grammaire | Distinguer les principaux types de mots (nom, verbe) dans une phrase tres simple | bandit-manchot-nature-mot-cp | bandit manchot | couvert-valide |
| francais | CP | Vocabulaire | Utiliser des mots precis pour designer des objets, actions, sensations du quotidien | bandit-manchot-mot-manquant-cp | bandit manchot | couvert-valide |

**Note du 2026-09-01 (ouverture s26, `camille-po`) - quatre lignes passent `non couvert` ->
`en cours`, ou gagnent un second paquet ; cinq stories, conditionnelles a `theo-archi`.**
`DEC-0068` (arbitree par l'utilisatrice) et l'arbitrage prealable de `nadia-prof`
(`squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md`, sections 3, 4, et son
addendum, sections 11 a 15) ouvrent cinq paquets sur quatre lignes du domaine le plus faible du
produit. Stories, criteres et routages complets : `docs/backlog/backlog.md`, section "Backlog -
perimetre s26" ; planning du sprint : `squad/sprints/s26-la-voix-et-la-dette/planning.md`.

- **"Localiser une syllabe..."** (`S26-L1`, `marc-dev`) et **"Identifier un son..."**
  (`S26-L2`, `marc-dev`) : `non couvert` -> `en cours`, chacune sur un paquet neuf et entier.
- **"Associer les graphemes aux phonemes..."** : reste `en cours` (elle l'est depuis s03), mais
  gagne deux paquets neufs, `S26-L3` et `S26-L4` (`lea-dev`), un par clause. **Fait de
  composition, pas de priorisation** : `nadia-prof` a mesure que les deux clauses ne tiennent pas
  dans un paquet unique (melange qui reproduit `CON-6` - `ch`, seule consonne parmi cinq voyelles,
  n'est jamais distracteur sur le materiel refuse, `P(bonne / presente) = 100 %`) ; **la ligne ne
  passe `couvert-valide` que si les deux paquets sont livres et valides ensemble**, sans quoi elle
  reste `en cours` comme aujourd'hui. Le fichier existant `cp-le-bon-son.yaml` (clause 3, trois
  nasales, deja acquise) n'est pas touche par ces deux stories.
- **"Ecrire de maniere autonome un mot simple..."** (`S26-L5`, `sofia-sm`) : `non couvert` ->
  `en cours`, sur un paquet neuf et entier - premier paquet a reponse alphabetique sur la mecanique
  de saisie, `SAI-11` se levant sur sa condition remplie.
- **"Decoder un mot regulier inconnu par assemblage syllabique"** : **ne bouge pas**, `nadia-prof`
  la refuse pour ce moyen (*"la seule des cinq dont les propositions sont des sons"*) et nomme deux
  voies qui ne sont pas prises ce sprint. Reste `non couvert`.

**Correction du 2026-09-01 (`sofia-sm`) : ces cinq stories etaient conditionnelles a une
reponse de `theo-archi` a l'ecriture de ce paragraphe, elles ne le sont plus.** Il a tranche
(`ADR-0075`) : la famille a voix est un champ d'item, pas une mecanique neuve. **Les quatre lignes
ferment donc entierement : 35/41 = 85,4 %.** La branche "mecanique" (borne a quatre paquets, une
ligne sacrifiee, 34/41 = 82,9 %) ne s'est pas realisee et n'est plus ouverte nulle part dans ce
depot.

**Quatre conditions de `nadia-prof` sont ecrites comme non gardees sur les cinq paquets** -
`VOI-3`, `VOI-5`, `VOI-8` et `VOI-13` - premiere fois sur ce depot qu'une condition pedagogique
n'est verifiable par personne, pas meme par elle en jouant : le manquement se produit dans la
bouche d'un adulte, sans trace. Detail et contrepartie (`VOI-12`, exigence bloquante) :
`docs/backlog/backlog.md`, section "Backlog - perimetre s26".

**Le denominateur ne bouge pas : 41.** Aucun statut ne passe `couvert-valide` par ce planning ;
ce statut n'est prononce que par `nadia-prof`, apres audit reel de chaque increment.

**Note de cloture du 2026-09-01 (s26, `camille-po`) - VALIDE sur les cinq paquets, trois lignes se
ferment, pas quatre. Le plafond que j'avais annonce ci-dessus n'est pas atteint.**
`nadia-prof` a rejoue sur `round-two` (`squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-
adulte.md`, section 18) et prononce le Done pedagogique : **VALIDE sur les cinq paquets**
(`bandit-manchot-position-syllabe-cp`, `bandit-manchot-son-dans-mot-cp`,
`bandit-manchot-lettre-du-son-cp`, `bandit-manchot-digraphe-du-son-cp`, `saisie-mot-dicte-cp`).
Trois lignes passent `couvert-valide` (localiser une syllabe, identifier un son, ecrire un mot
simple en autonomie) ; la ligne des graphemes reste `en cours`, motif ecrit sur sa ligne dans le
tableau ci-dessus.

Ses mots, a citer tels quels : *"Les deux moities sont VALIDE ; la ligne n'est pas couverte. Le
Done porte sur l'increment, la matrice sur la couverture d'une competence. Me taire ferait passer
la ligne a `couvert-valide` contre une barre non atteinte - et ma barre existe pour empecher
exactement cela."* Sa barre du 2026-08-10, clause 1, exige les voyelles simples **et** les
consonnes simples ; `bandit-manchot-lettre-du-son-cp` sert six consonnes (`p, m, t, r, l, s`) et
aucun paquet servi n'a de voyelle simple en bonne reponse.

**Ecart contre mon propre compte, a porter en toutes lettres plutot qu'a laisser filer.** J'avais
ecrit ci-dessus *"les quatre lignes ferment entierement, plafond 35/41 = 85,4 %"*. Ce plafond
n'est pas atteint : **34/41 = 82,9 %.** Le motif n'est ni un echec technique (1 090 tests verts,
non-regression verifiee sur 188 ecrans) ni un refus pedagogique (les cinq paquets sont VALIDE) :
c'est une **barre pedagogique que mon compte n'integrait pas** - je comptais des paquets et des
lignes de referentiel, `nadia-prof` comptait aussi ce que chaque paquet sert reellement contre sa
propre clause. Un plafond de couverture ne peut pas se calculer sur le seul denombrement des
lignes et des paquets livres ; il depend d'un jugement de contenu qui n'est jamais acquis avant
d'etre rendu. Je l'ecris ici pour la meme raison que je l'ai ecrite contre moi a la cloture de s25 :
une enumeration qui ne se declare pas partielle se lit comme complete, et mon "35/41" ne se
declarait pas conditionnel a un jugement de contenu qui restait a rendre.

**Precision de `nadia-prof`, a ne pas perdre** : ce 82,9 % **n'a rien a voir** avec celui qu'elle
avait price pour la branche *mecanique* de `theo-archi` (voir le complement ci-dessous, "champ
d'item : 5 paquets, 4 lignes, 85,4 % ; mecanique : 4 paquets, 3 lignes, 82,9 %") - **meme nombre,
autre cause**. Le premier decrivait une branche d'architecture qui n'a pas ete prise ; celui-ci
decrit une ligne de matrice qui reste `en cours` faute de voyelles simples servies. Les deux
figurent dans ce depot ; je le dis pour que personne ne recopie l'un a la place de l'autre.

**Ce qui est acquis, et qui n'a rien de conditionnel** : `nadia-prof` ecrit que ses clauses 2, 3
et 4 sont desormais toutes remplies, et qu'**un seul paquet de voyelles simples ferme la derniere
ligne**. Le domaine *"Langage oral et conscience phonologique"* passe de **1/3 a 3/3 et se ferme**
- premier domaine ferme du produit.

**Ce que ce Done ne couvre pas, et qui reste vrai apres la cloture** : la liste des conditions
pedagogiques sans garde executable ne diminue pas d'une unite - `VOI-3`, `VOI-5`, `VOI-8`, et la
moitie non calculable de `VOI-13`. `nadia-prof` : *"la cinquieme phrase ne fait pas respecter
VOI-13 : elle la dit. Un adulte qui laisse l'enfant lire la ligne fait echouer les cinq jeux, et
rien ne le saura."* Aucune de ces quatre conditions n'est fermee par ce sprint, et rien dans ce
fichier ne doit laisser croire le contraire.

**Note du 2026-08-30 (ouverture s24, `camille-po`) - deux lignes passent `non couvert` ->
`en cours`, sur le niveau le plus creux du produit.** `DEC-0059` ouvre `S24-NAR` sur "Comprendre un
texte narratif tres court" (conditions `NAR-1` a `NAR-10`) et `S24-PLU` sur "Reconnaitre et utiliser
les marques du pluriel les plus frequentes (-s)" (conditions `PLU-1` a `PLU-9`), toutes deux
arbitrees individuellement par `nadia-prof` le 2026-08-30 (meme arbitrage prealable, sections 3
et 4). **Motif du choix, verifie sur cette matrice avant d'etre ecrit** : le francais CP porte 3
lignes `couvert-valide` sur 11 atteignables (27,3 %), contre 54,5 % en mathematiques CP, 70,0 % en
mathematiques CM1 et 72,7 % en francais CM1 - c'est le point le plus faible du produit.

**Deux mentions a porter d'avance, pour qu'un statut vert futur ne les laisse pas dans l'ombre** :

- Sur "Comprendre un texte narratif tres court", la mention que `nadia-prof` exige le jour ou la
  ligne bougerait vers `couvert-valide` est *"textes de deux phrases, un texte par question,
  questions explicites seulement"*. Elle nomme aussi une limite qui n'est pas une mention de matrice
  mais qui vaut d'etre lue avec la ligne : ce paquet s'adresse a un enfant **qui decode deja** et
  n'est pas utilisable en debut de CP - ce produit n'ayant aucun niveau a l'interieur du CP.
- Sur "Reconnaitre et utiliser les marques du pluriel", **aucune mention de couverture partielle
  n'est due** : `nadia-prof` ecrit que la ligne se restreint deja d'elle-meme au `-s` et que `PLU-3`
  suit cette restriction au lieu de l'inventer.

**Ce que cette note ne fait pas** : aucune des deux lignes n'atteint `couvert-valide` par ce
planning, et je ne prejuge d'aucun audit.

**Note du 2026-08-24 (s19, `camille-po`) - arbitrage de l'utilisatrice : statut `non couvrable en
l'etat` sur "Lire a voix haute...".** Cette competence exige une reconnaissance vocale, hors
d'atteinte de ce produit tel qu'il est defini (trois contraintes fondatrices contredites a la fois,
plus une question de donnee personnelle sur la voix d'un enfant). Motif complet, sources et
consequence sur le denominateur de couverture : `DEC-0042`, section 3. **Nature "irreversible"**
(distinction posee par `DEC-0046`, section 4.1) : juger une lecture a voix haute demande un humain
qui ecoute ; aucune decision produit ne debloquera jamais cette ligne, a la difference des deux
lignes de geometrie CM1 reclassees le 2026-08-25 pour un motif "reversible".

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

**Note du 2026-08-25 (hors sprint, `camille-po`) - `NAT-9` levee, `NAT-5` seule reste bloquante, sur
un motif recentre** (`squad/conformite/FICHE-bandit-manchot-nature-mot-cp.md`, deux amendements du
2026-08-25, sur l'arbitrage elargi de l'utilisatrice - un eleve de CP est desormais considere
toujours accompagne par un adulte, `DEC-0046` section 3). `NAT-9` (consigne circulaire) est
**LEVEE en entier** : la consigne servie est acceptee telle quelle, aucune reformulation n'est due.
`NAT-5` **tient**, mais plus sur la comprehension des guillemets par l'enfant (affaiblie par la
presence durable de l'adulte) : le motif qui la porte desormais est l'asymetrie entre une tache
constante sur toute la partie et une designation qui change a chaque manche, dix fois par partie,
sans que rien dans le produit ne demande a l'adulte de la pointer. Le libelle de `NAT-5` ne change
pas. **`NAT-5` devient donc la seule condition de retour au catalogue** (`DEC-0045`, section 2) ;
le chantier de s20 qui l'instruit est `S20-1` (`docs/backlog/backlog.md`, `DEC-0047`), scope a
`cp-nature-mot` seul ce sprint - le detail du perimetre et son motif vivent dans la story, pas ici.

**Note du 2026-08-25 (s20, `camille-po`) - `NAT-5` VALIDE, ligne `couvert-valide`, verifiee avant de
bouger.** `nadia-prof` a reaudite l'increment de `S20-1` (`round-two` a `f4e0808`) et prononce
**VALIDE** : plus aucune reserve bloquante. J'ai relu sa fiche en entier avant de faire bouger cette
ligne (`squad/README.md`, section 9, etape 6) - detail de ma verification et perimetre exact de ce
que ce statut recouvre : `DEC-0048`. Restent ouvertes et non bloquantes : `NAT-10`, `NAT-11`
(inchangees), `NAT-12` (neuve, contraste de luminance de la boite de marquage). Non couvert et
nomme, sans etre une reserve : ce qu'un lecteur d'ecran annonce de l'enonce segmente par `ADR-0062`,
et toute largeur autre que la reference 744 x 551. Le jeu revient au catalogue dans le meme geste
(`DEC-0045`, section 2, condition remplie).

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
| francais | CM1 | Lecture et comprehension de l'ecrit | Comprendre un texte narratif ou documentaire (questions explicites et implicites, inferences simples) | bandit-manchot-comprehension-cm1 | bandit manchot | couvert-valide |
| francais | CM1 | Lecture et comprehension de l'ecrit | Reperer les substituts d'un personnage (pronoms, groupes nominaux) dans un texte | bandit-manchot-substituts-cm1 | bandit manchot | **couvert-valide** (2026-09-01, s25, **second passage - refus du 2026-08-31 leve**) - **ligne entiere**, avec la mention exigee depuis le 2026-08-30 et reprise telle quelle : *"textes de trois phrases au plus, un texte par question, pronoms et groupes nominaux servis tous les deux"* |
| francais | CM1 | Ecriture | Rediger un texte narratif court et coherent (quelques phrases, temps du recit respecte) | - | - | **non couvrable en l'etat** (2026-08-31, s25, DEC-0066) - **jugement humain sur une production libre (ADR-0008), nature irreversible** : la ligne dit *rediger*. nadia-prof, 2026-08-31 : *"R3 refusait par avance quelle que soit la qualite du lot ; je le reconduis et je l'etends au choix [...] l'enfant ne redige pas"* |
| francais | CM1 | Ecriture | Relire et corriger son texte a l'aide de reperes simples (majuscules, ponctuation) | - | - | non couvrable en l'etat |
| francais | CM1 | Orthographe et grammaire | Accorder le verbe avec son sujet (cas frequents, temps simples : present, futur, imparfait) | bandit-manchot-accord-sujet-verbe-cm1 | bandit manchot | couvert-valide |
| francais | CM1 | Orthographe et grammaire | Accorder en genre et en nombre dans le groupe nominal (determinant/nom/adjectif) | bandit-manchot-accords-groupe-nominal-cm1 | bandit manchot | couvert-valide |
| francais | CM1 | Orthographe et grammaire | Distinguer et utiliser correctement les principaux homophones grammaticaux (et/est, son/sont, a/à...) | bandit-manchot-homophones-cm1 | bandit manchot | couvert-valide |
| francais | CM1 | Orthographe et grammaire | Conjuguer les verbes frequents au present, futur et imparfait de l'indicatif | bandit-manchot-conjugaison-present-cm1 | bandit manchot | couvert-valide |
| francais | CM1 | Orthographe et grammaire | Reperer et corriger la majuscule et la ponctuation dans une phrase donnee (majuscule initiale, point final, majuscule au nom propre, point d'interrogation d'une question) | bandit-manchot-reperes-cm1 | bandit manchot | couvert-valide |
| francais | CM1 | Vocabulaire | Utiliser des synonymes et antonymes pour preciser ou nuancer un propos | bandit-manchot-synonymes-cm1, bandit-manchot-antonymes-cm1 | bandit manchot | couvert-valide |
| francais | CM1 | Vocabulaire | Utiliser a bon escient des mots de liaison simples pour structurer un texte court | bandit-manchot-mots-de-liaison-cm1 | bandit manchot | couvert-valide |

**Note du 2026-08-30 (s23, `camille-po`) - couverture partielle nommee, mention exacte de `nadia-
prof`, a porter avec cette ligne plutot qu'a laisser dans l'ombre d'un statut vert.** `nadia-prof`
fixe elle-meme la mention, avant tout jugement de ma part (`LIA-10`,
`squad/conformite/FICHE-bandit-manchot-mots-de-liaison-cm1.md`) :

> **choix du mot de liaison entre deux ou trois phrases, sans production de texte**

**Ce que le paquet couvre** : le choix d'un connecteur parmi trois sur un texte de deux ou trois
phrases deja ecrit ; quatre relations logiques (opposition, consequence, cause, succession) a parts
egales ; une population fermee de dix connecteurs simples ; le connecteur en tete de seconde phrase
comme en milieu de phrase.

**Ce que ce paquet ne couvre pas, dans les mots de `nadia-prof`, pour que ce statut vert ne laisse
rien dans l'ombre** :

- **la production de texte** - l'enfant presse un bouton, il ne redige jamais rien. *C'est la limite
  principale, et c'est elle qui justifie la mention partielle* ;
- la structuration d'un texte entier (trois phrases au maximum, un seul connecteur a la fois) ;
- les connecteurs de registre soutenu (`cependant`, `neanmoins`, `toutefois`, `en effet`, `par
  consequent`) et `et`, `ou`, `ni`, `or` ;
- l'arbitrage entre deux connecteurs egalement possibles - le paquet est construit pour qu'un seul
  convienne ;
- la place et la ponctuation du connecteur, toujours donnees par l'enonce, jamais choisies ;
- les relations non servies : le but, la condition, l'hypothese, l'enumeration.

**Note du 2026-08-25 (s20, `camille-po`) - question ouverte, routee a `nadia-prof`, statut
inchange.** `theo-archi` a mesure qu'a la reference du produit (744 x 551, `DEC-0040`), `bandit-
manchot-synonymes-cm1` porte 7 items sur 16 avec leur bloc de message sous le pli et `bandit-
manchot-antonymes-cm1` en porte 1 sur 17. **Ces deux jeux ne sont pas un cas isole** : la mesure
porte sur dix jeux au total (sur les quinze mesures), et **les dix sont `couvert-valide`** - verifie
ligne par ligne dans cette matrice plutot que suppose. Le detail complet, jeu par jeu, vit dans
`squad/sprints/s20-mot-cible-designe-et-conception-de-la-saisie/mesure-budget-vertical.md`, section
6, et le routage integral dans `DEC-0040` et `DEC-0047` (amendements du 2026-08-25, troisieme
passage). `theo-archi` refuse lui-meme de qualifier ces items : *"combien de ces items relevent
d'une reserve deja acceptee est la lecture de `nadia-prof`"*. Je ne juge aucun de ces items et je ne
fais bouger ni ce statut ni aucun autre sur la foi de cette mesure.

**Note du 2026-08-25 (hors sprint, `camille-po`) - competence detachee, acceptee par
l'utilisatrice, amendee au referentiel** (`DEC-0046`, section 2, sur proposition de `nadia-prof`,
`squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`, amendement du
2026-08-25, section B). "Reperer et corriger la majuscule et la ponctuation dans une phrase
donnee..." est detachee de "Relire et corriger son texte..." ci-dessus : la charniere "dans une
phrase donnee" isole la connaissance du repere de la relecture de son **propre** texte, qui reste
la seule difficulte de la ligne d'origine. **Couvrable par la mecanique existante, contenu seul,
sans saisie** - elle entre dans la reserve de contenu seul de `DEC-0042`. Quatre conditions
prealables `REP-1` a `REP-4` (composition du paquet, aucune tenue par un test) : detail complet
dans le document cite. La ligne d'origine, ci-dessus, reste `non couvrable en l'etat`, statut
inchange sur recommandation de `nadia-prof` : le detachement ne l'ameliore pas, il la rend
honnete. **Nature "irreversible"** (`DEC-0046`, section 4.1) : juger une relecture de soi demande
un adulte qui lit, distinct des deux lignes de geometrie CM1 ci-dessus, reclassees le meme jour
pour un motif "reversible".

**Note du 2026-08-25 (s20, `camille-po`) - ligne ouverte par `S20-2`** (`docs/backlog/
backlog.md`, `DEC-0047`) : contenu seul, mecanique `bandit-manchot` existante, aucun arbitrage
prealable supplementaire du - `nadia-prof` a deja instruit la forme (une phrase, quatre
propositions qui nomment ce qui manque) et les quatre conditions prealables `REP-1` a `REP-4`.
Jeu propose `bandit-manchot-reperes-cm1`, a confirmer par le developpeur. Aucune ne passe a
`couvert-valide` par ce planning : ce statut n'est prononce que par `nadia-prof`, apres audit reel
de l'increment livre.

**Note du 2026-08-25 (s20, `camille-po`) - increment audite, REFUSE, la ligne reste `en cours`.**
`nadia-prof` a audite `bandit-manchot-reperes-cm1` (`round-two` a `f4e0808`) et prononce **REFUSE**
sur deux motifs mesures dont elle declare elle-meme l'origine dans sa propre specification : le pli
(0 verdict sur 16 et 0 indice sur 16 entierement visibles apres un echec, pire cas 190,2 px sous le
pli) et `REP-4` non tenue (le meme libelle sert de bonne reponse au point final et au point
d'interrogation sur 6 items sur 16 - un enfant qui ignore l'existence du point d'interrogation
obtient 16 sur 16). Deux reserves non bloquantes consignees, `REP-5` et `REP-6`. Le jeu est hors
catalogue (`DEC-0048`, puis `DEC-0049`, ce second motif independant de la simple absence d'audit).
Detail complet, criteres correctifs verbatim et routage : `DEC-0049`, `docs/backlog/backlog.md`.

**Note du 2026-08-27 (s20, cloture, `camille-po`) - reaudition VALIDE SOUS RESERVE, la ligne passe
`couvert-valide`.** `nadia-prof` a reaudite le paquet corrige par `lea-dev` (`round-two` a
`77dd467`) et prononce **VALIDE SOUS RESERVE** le 2026-08-27
(`squad/conformite/FICHE-bandit-manchot-reperes-cm1.md`, verdict du 2026-08-27 ;
`squad/sprints/s20-mot-cible-designe-et-conception-de-la-saisie/avis-nadia-cloture.md`). Les deux
motifs du `REFUSE` du 2026-08-26 sont traites et mesures : `REP-4` est tenue (la simulation d'un
enfant qui ignore le point d'interrogation passe de 16 sur 16 a 13 sur 16, trois items exigeant
desormais la distinction, ce que `nadia-prof` exigeait au caractere pres) ; le pli est lisible sur
14 items sur 16 apres un echec (0 sur 16 au refus). **Le Done pedagogique est prononce et le motif
du retrait de `DEC-0049` est leve** : `DEC-0050` trace la reprise et route le geste sur
`catalogue.yaml` a `sofia-sm`. La ligne passe `en cours` -> `couvert-valide`.

**Perimetre exact de ce `couvert-valide`, dans les mots de `nadia-prof`.** Ce statut couvre la
connaissance des quatre reperes sur une phrase donnee, mesuree et tenue. Il ne couvre **pas** la
relecture par l'enfant de son propre texte : la ligne d'origine ("Relire et corriger son texte...")
reste `non couvrable en l'etat`, inchangee. Quatre reserves non bloquantes restent ouvertes,
`REP-7` a `REP-10` (2026-08-27) : `REP-7` (la composition des trois propositions par item predit la
reponse sans lire la phrase sur 4 items sur 16, esperance 10 sur 16 au lieu de 5,33 au hasard),
`REP-9` (le verdict est coupe en deux sur les 2 items a enonce de deux lignes) et `REP-10` (l'indice
de l'item 15 nomme la reponse de trop pres) sont propres a ce paquet, routees a `lea-dev` ;
`REP-8` (l'indice reste hors de vue apres un echec sur 16 items sur 16, 44,19 a 92,19 px sous le
pli) **n'est pas un defaut de ce paquet** - `nadia-prof` la qualifie elle-meme de premier defaut
pedagogique du catalogue aujourd'hui, mesure sur 8 jeux sur 17 et 43 items sur 157 le 2026-08-27,
et la route comme story de catalogue plutot que comme reprise de paquet. Detail des quatre
reserves et leur mise en backlog (non affectees) : `docs/backlog/backlog.md`, section "Sprint 20".

**Le chiffre, avec sa base, verifie ligne par ligne contre cette matrice avant d'etre ecrit
(2026-08-27).** Le denominateur atteignable reste **43** sur **47** : aucune ligne `non couvrable en
l'etat` ne bouge. Le compte de lignes `couvert-valide` passe de **14** a **15** (la seule ligne qui
bouge est celle-ci ; verifie en relisant les quatre tableaux de matiere ci-dessus terme a terme).
**14/43 (32,6 %) devient 15/43 (34,9 %)**.

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

## Questionner le monde - CP (cycle 2)

Source : `squad/programme/questionner-le-monde/competences-cp.md`. Referentiel ecrit par
`nadia-prof` le 2026-09-02 (`squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-
programme.md`, section 4), sur demande de l'utilisatrice du meme jour, verbatim : *"il n'y a pas
de jeux d'anglais (vocabulaire), histoire / geographie, questionner le monde ajoute les au
prochain sprint"*. Les 16 lignes sont copiees telles quelles, aucun mot change. Aucune ligne
n'a encore de jeu : toutes demarrent a `non couvert` ou `non couvrable en l'etat`, comme prescrit
par cette matrice pour toute competence sans increment (voir "Comment la lire" en tete de fichier).

*Questionner le monde* est une matiere de cycle 2 uniquement : elle se scinde en
histoire-geographie et en sciences et technologie au passage au cycle 3. Il n'y aura jamais de
ligne "questionner le monde / CM1".

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| questionner le monde | CP | Le vivant, la matiere et les objets | Distinguer les êtres vivants des objets et de la matière inerte sur des exemples du quotidien. | bandit-manchot-vivant-non-vivant-cp | bandit manchot | **couvert-valide** (2026-09-04, s28, troisieme passage) - **`QLM-CP-01` ferme.** Premier paquet (huit items, s27) `VALIDE` ; second paquet (six items neufs, s28) **REFUSE** le 2026-09-04 - trois items de matiere inerte (caillou, sable, neige) donnaient `un objet` en bonne reponse, ce qui est faux ; corrige dans le meme sprint (`0c383b9`, texte fourni mot pour mot par `nadia-prof`) et reaudite le meme jour : **VALIDE SOUS RESERVE**. Quatorze items au total, `nombreDeManches: 10` (le motif de `DEC-0073` a huit items ne s'applique plus, `VIV-R1` levee). Titre servi *"Vivant ou pas vivant"*. Reserve nouvelle non bloquante **`VIV-R2`** : sur les trois items de matiere inerte, les deux propositions (`un être vivant` / `ce n'est pas un être vivant`) se terminent par les memes mots et le seul mot distinctif est en tete de la plus longue - correction routee a s29 (`docs/backlog/backlog.md`). Aucun refus n'est efface : le motif du second passage reste ecrit dans `squad/conformite/FICHE-bandit-manchot-vivant-non-vivant-cp.md` |
| questionner le monde | CP | Le vivant, la matiere et les objets | Identifier ce qui caractérise un être vivant : il naît, se nourrit, grandit, a des petits, meurt. | bandit-manchot-cycle-de-vie-cp | bandit manchot | **couvert-valide** (2026-09-06, s29) - **`QLM-CP-02` ferme.** `FER-1` : les cinq caracteres du libelle chacun en bonne reponse, verifies un par un. `FER-2` : onze items. `FER-3` : deux operations mentales distinctes. Exactitude 11 sur 11. Lisibilite : mediane 7 mots. Aucune mention de couverture partielle n'est due (`nadia-prof` le dit explicitement). Deux reserves non bloquantes, datees du 2026-09-06 : **`CYV-R1`** (item de la plante, au-dessus du niveau et auto-repondant, correction fournie mot pour mot) et **`REV-1`** (phrase de revelation agrammaticale sur 5 des 11 items, defaut de portee depot, route a `theo-archi`) |
| questionner le monde | CP | Le vivant, la matiere et les objets | Nommer les principales parties du corps humain et les cinq sens. | bandit-manchot-corps-sens-cp | bandit manchot | **couvert-valide** (2026-09-04, s28, second passage) - **`QLM-CP-03` ferme.** Premier paquet (huit items, s27) `VALIDE SOUS RESERVE` (`COR-R1`, item du toucher trop long et hors niveau, levee le 2026-09-03 sur correction reprise mot pour mot). Second paquet (six items neufs, s28) porte le compte des "parties du corps" a neuf (bras, pieds, dos, ventre, genou, la peau) : `VALIDE SOUS RESERVE` sur `COR-R4` (item de la peau, dix-neuf mots, plus du double de la mediane) - **payee et levee le meme jour** (`0c383b9`, texte fourni mot pour mot, enonce ramene a douze mots). Quatorze items au total, `nombreDeManches: 10` (`COR-R3` levee, meme motif que `VIV-R1`). Reserve non bloquante ouverte, inchangee depuis s27 : **`COR-R2`** - *gouter -> la bouche* quand l'organe du gout est la langue |
| questionner le monde | CP | Le vivant, la matiere et les objets | Connaître quelques règles d'hygiène de vie (sommeil, alimentation, hygiène du corps) et leur effet sur la santé. | - | - | non couvert |
| questionner le monde | CP | Le vivant, la matiere et les objets | Reconnaître les états de l'eau (solide, liquide) et les changements d'état observables. | bandit-manchot-etats-eau-cp | bandit manchot | **couvert-valide** (2026-09-04, s28, second passage) - **`QLM-CP-05` ferme.** Premier paquet (huit items, s27) `VALIDE`. Second paquet (six items neufs, s28) porte les "changements d'etat" a quatre items sous deux formes (classer, nommer) et tient le perimetre au mot pres - aucun item n'introduit l'etat gazeux, la vapeur ou l'evaporation : `VALIDE`, sans reserve nouvelle bloquante. Quatorze items au total, `nombreDeManches: 10` (`EAU-R2` levee, meme motif que `VIV-R1`). Reserve non bloquante ouverte, inchangee depuis s27 : **`EAU-R1`** - l'indice de l'item "quand la glace fond" est abstrait (renvoi a "ce deuxieme etat" plutot qu'un critere direct) |
| questionner le monde | CP | Le vivant, la matiere et les objets | Identifier de quelle matière un objet est fait et trier des matériaux selon une propriété simple. | - | - | non couvert |
| questionner le monde | CP | Le vivant, la matiere et les objets | Identifier la fonction d'un objet technique du quotidien et nommer ses parties. | bandit-manchot-objet-technique-cp | bandit manchot | **couvert-valide** (2026-09-06, s29) - **`QLM-CP-07` ferme.** `FER-1` : les deux moities du libelle servies (fonction, cinq items ; parties, six items). `FER-2` : onze items. `FER-3` : deux taches par construction. Exactitude 11 sur 11. Lisibilite : mediane 7 mots. Aucune mention de couverture partielle n'est due (`nadia-prof` le dit explicitement). Trois reserves non bloquantes, datees du 2026-09-06 : **`OBJ-R1`** (titre *"Fonction d'un objet"* illisible et trop etroit au CP, remplacement fourni mot pour mot), **`OBJ-R2`** (les cinq items de fonction se repondent sans connaitre l'objet, rejeu borne) et **`REV-1`** (phrase de revelation maladroite sur les cinq items de fonction, defaut de portee depot, route a `theo-archi`) |
| questionner le monde | CP | Le vivant, la matiere et les objets | Reconnaître les objets numériques du quotidien et quelques règles simples de leur usage. | - | - | non couvert |
| questionner le monde | CP | Le vivant, la matiere et les objets | Mener une démarche d'investigation simple : observer, questionner, expérimenter, dire ce qu'on a trouvé. | - | - | **non couvrable en l'etat** (2026-09-02, `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, section 7.1) - jugement humain sur une production libre (`ADR-0008`), meme famille que *rediger* (`ADR-0008`) et *copier* (`DEC-0066`), nature irreversible. `nadia-prof` : "Geste pratique et compte rendu libre. Observer, experimenter et dire ce qu'on a trouve ne se produisent ni par un choix ni par une saisie." |
| questionner le monde | CP | Se situer dans l'espace | Utiliser le vocabulaire de position pour décrire l'espace proche (devant/derrière, gauche/droite, au-dessus/au-dessous, près/loin). | - | - | non couvert |
| questionner le monde | CP | Se situer dans l'espace | Situer un lieu ou un objet sur un plan simple de la classe, de l'école ou du quartier. | designation-plan-de-la-classe | designation | **couvert-valide** (2026-09-21, s36, `S36-QLM11-PLANCLASSE`, VALIDE SOUS RESERVE) - **`QLM-CP-11` ferme, cinquieme sprint sur cette ligne.** Historique jusqu'a s36 : palier 2 requis (designer un lieu sur un plan), refuse au palier 1 par `V1` (2026-08-24) - **non** `non couvrable en l'etat` : `nadia-prof` le distingue explicitement, "le palier 2 est constructible, il n'existe pas". **s34** : entree minimale du palier 2 engagee par l'utilisatrice (`DEC-0086`, section 12.1), lot de designation `QLM-CP-11` + `QLM-CP-14` (`S33-PAL2-1`) **jamais livre sur cette ligne** - la geometrie des cadres du plan de classe (`armoire`, `porte`) et le plancher de lisibilite d'un libelle de zone restent a trancher (`ZON-15`, `ZON-16`, routees a `ines-design` et `nadia-prof`). **s35** (`S35-QLM11-PLANCLASSE`) : premier lot reellement servi sur cette ligne (`designation-plan-de-la-classe`, 6 items) et **REFUSE** - `ZON-15` rompue sur les six zones sur six, de 17 a 95 px de debordement selon la zone, les libelles se recouvrant entre voisins et couvrant les traits du meuble qu'ils designent ; `ZON-16` en revanche **tenue sur les six libelles**, confirmes tels quels et non repris dans le perimetre de la reprise. Constat structurel de `nadia-prof`, cite mot pour mot (`FICHE-designation-plan-de-la-classe.md`, section 3) : *"Un libelle positionnel replie sur deux lignes demande, dans le pave de `.zone`, un cadre d'au moins 109 x 88 px, soit 31 % x 25 % du dessin servi. Il faut cela six fois. Or trois des six elements d'un plan de classe sont des bandes minces par nature : un tableau contre le mur du haut, une fenetre percee dans le mur de droite, une porte dans le mur du bas. On ne peut pas donner 25 % de hauteur au tableau sans mentir sur le plan - et mentir sur le plan est exactement ce que `QLM-CP-11` interdit, puisque la competence est de situer sur un plan. [...] le libelle d'une zone ne peut pas vivre a l'interieur d'un cadre quand ce cadre doit rester une bande mince."* Deux sorties acceptees par `nadia-prof`, aucune tranchee (forme a `ines-design`/`theo-archi`) : que le libelle sorte de la boite du bouton, ou que le pave de `.zone` soit reduit sur une zone de designation. Une sortie refusee explicitement : l'alternative `(d)` d'`ADR-0092` (accepter qu'un libelle deborde de son cadre), parce que le debordement mesure ici fait se recouvrir des libelles de zones voisines. **s36** (`S36-QLM11-PLANCLASSE`, reprise sur la geometrie seule, `ADR-0093`) : `nadia-prof` rejoue une partie complete de cinq manches plus une manche a trois erreurs volontaires, dans un navigateur reel, geometrie mesuree par `getBoundingClientRect` sur le document servi. **`ZON-15` premiere moitie tenue** : 0,00 px de debordement horizontal et vertical sur les six zones (contre 17 a 95 px au refus de s35). **`ZON-15` seconde moitie clarifiee** : trois chiffres de designation croisent encore un trait du dessin (21,1 % a 30,9 % de leur encre), mais `nadia-prof` precise elle-meme sa condition - elle protege la silhouette de l'objet, pas l'absence de tout croisement de trait - et juge les six silhouettes identifiables aux deux mesures ; assouplissement qu'elle nomme comme tel, pas une nouvelle barre atteinte par le contenu. La correspondance numero -> libelle est verifiee lisible sans ambiguite par un enfant de CP (chiffre et legende a 20 px gras, six correspondances bijectives, la legende jamais necessaire pour jouer). **Quatre reserves non bloquantes** : `PCL-R1`, `PCL-R2` (indices imprecis, ouvertes depuis le 2026-09-11, inchangees, deja classees non bloquantes a leur ouverture) ; `PCL-R3` (le chiffre croise un trait sur trois zones), `PCL-R4` (le vocabulaire positionnel s'est detache de la position qu'il nomme), neuves du 2026-09-21. **Deux defauts transverses neufs, non bloquants, dates de s34 par `git log -S` et non une regression de s36**, touchant aussi `designation-frise-de-la-journee` (`QLM-CP-14`, non rouverte) : `PCL-R5` (une zone deja essayee ne se distingue en rien d'une zone neuve) et `PCL-R6` (apres la troisieme erreur, la bonne zone n'est designee par rien a l'ecran, `:focus-visible` faux apres un appui de pointeur) - routes a `camille-po` pour le backlog, forme a `ines-design`, cause a `theo-archi`. Verdict : `squad/conformite/FICHE-designation-plan-de-la-classe.md`, bloc de validation pedagogique nº 2 (2026-09-21, s36) ; repris dans `squad/sprints/s36-ouverture-ce1-mathematiques-seule-cm2-differe/verdicts-nadia.md`, sections 1, 2 et 5. |
| questionner le monde | CP | Se situer dans l'espace | Situer la France et quelques espaces étudiés sur une carte ou sur un globe. | - | - | non couvert - meme motif que la ligne precedente - palier 2 requis, non construit a ce jour. |
| questionner le monde | CP | Se situer dans le temps | Nommer et ordonner les mois de l'année et les saisons. | - | - | non couvert |
| questionner le monde | CP | Se situer dans le temps | Situer des événements les uns par rapport aux autres sur une frise chronologique simple (avant, après, en même temps). | designation-frise-de-la-journee | designation | **couvert-valide** (2026-09-11, s35, `S35-QLM14-FRISE`) - **`QLM-CP-14` ferme.** Historique jusqu'a s35 : jouable sur la seule moitie textuelle (anteriorite/posteriorite), placement sur une frise reste du palier 2 (`nadia-prof`, section 7.1) ; **s34**, lot de designation `cp-frise-de-la-journee` (`S33-PAL2-1`) livre et **REFUSE** (`FRI-R1`, `FRI-R2`, bloquantes, instance B) - neuf items sur onze se gagnaient sans regarder le dessin et le pictogramme du lever ne se lisait pas comme un lit. **s35** : `FRI-R1` et `FRI-R2` **levees** - les deux textes alternatifs ne nomment plus aucune zone (passe image effacee a 5 sur 11, contre 9 sur 11 au refus de s34, le residu tenant desormais a la chronologie de la journee que l'enfant connait et non a une contrefacon) et le pictogramme du lever se lit comme un lit aux deux tailles. `FER-1` (les trois relations *avant*, *apres*, *en meme temps* chacune servies) et `FER-3` (6 contre 5) deja verifiees tenues a s34, non redemandees. **La mention de couverture partielle tombe** : la ligne n'est plus limitee a la moitie textuelle, elle est servie par le jeu de designation entier (11 items, deux dessins). **Reserves neuves, non bloquantes** : `FRI-R6` (le texte alternatif de `frise-soir-routine` annonce "quatre moments de gauche a droite" pour un dessin ou deux moments sont empiles sur trois places - la plus lourde des reserves de s35, a payer avant qu'un autre paquet de designation ne recopie la meme formule) et `FRI-R7` (l'indice de l'item 9 nomme un rang, "le tout dernier", que les libelles ne portent pas sur ce dessin a quatre moments et trois places). `FRI-R3`, `FRI-R4`, `FRI-R5` restent ouvertes, non bloquantes, hors perimetre de ce mouvement. Verdict : `squad/conformite/FICHE-designation-frise-de-la-journee.md`, bloc de validation du second passage (s35) ; repris dans `squad/sprints/s35-fermer-ce-qui-n-a-pas-recu-le-go-pedagogique/verdicts-nadia.md`, sections 1 et 5. |
| questionner le monde | CP | Se situer dans le temps | Comparer des modes de vie d'autrefois et d'aujourd'hui à partir d'objets et d'images du quotidien. | - | - | non couvert |
| questionner le monde | CP | Explorer les organisations du monde | Comparer des modes de vie ici et ailleurs (habitat, alimentation, école, déplacements). | - | - | non couvert - ligne signalee comme la plus a risque de biais du lot ; `BIA-1` et `BIA-2` (section 7.4 de l'arbitrage) sont opposables a tout paquet qui la sert. |

**Note du 2026-09-24 (s41, `camille-po`) - question ouverte, non routee, statut inchange.**
`nadia-prof` (instance B) signale un ecart entre son propre referentiel `SEM-1` (2026-08-31, qui
autorise "mois et leur ordre, saisons" dans le paquet de mathematiques `cp-semaine`) et la ligne
`QLM-CP-13` ci-dessus (ligne 1494), que le referentiel de questionner le monde reserve aux mois et
aux saisons. Quatre items de `cp-semaine` travaillent donc la meme competence que celle comptee sur
`QLM-CP-13` - question de double-compte entre deux paquets de matieres differentes, pas de defaut de
contenu sur l'un ou l'autre. Constat integral, cite en entier :
`squad/conformite/FICHE-bandit-manchot-saisons-cp.md`, section "Correction d'un de mes propres
constats, avant de m'en servir". Non bloquant pour le Done pedagogique de `S41-QLM-SAISONS`, non
tranche ici.

**Bilan, corrige le 2026-09-06 (`camille-po`, s29) - deux lignes de plus ferment** : 16 lignes,
10 `non couvert`, 5 `couvert-valide` (`QLM-CP-01`, `QLM-CP-02`, `QLM-CP-03`, `QLM-CP-05`,
`QLM-CP-07`), 1 `non couvrable en l'etat`. 15 lignes entrent au denominateur atteignable
(inchange : le nombre de lignes non couvrables en l'etat n'a pas bouge).

## Histoire-Geographie - CM1 (cycle 3)

Source : `squad/programme/histoire-geographie/competences-cm1.md`. Meme origine, meme date, meme
regle de copie conforme que la section precedente. *Histoire-geographie* est une matiere de cycle
3, issue de la scission de *questionner le monde* : il n'y aura jamais de ligne
"histoire-geographie / CP".

**Conditions bloquantes opposables a tout paquet de contenu sur ces lignes** (`nadia-prof`,
section 7.5 de son arbitrage) : `HIS-1` (au plus un tiers d'items de restitution), `HIS-2` (aucun
item a deux reponses defendables), `HIS-3` (chaque fait porte sa source dans le commentaire du
paquet), `BIA-5` sur les lignes 5 a 8 (aucun jugement de valeur sur un personnage).

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| histoire-geographie | CM1 | Se reperer dans le temps : construire des reperes historiques | Situer et ordonner les grandes périodes historiques sur une frise (Préhistoire, Antiquité, Moyen Âge, Temps modernes, époque contemporaine). | bandit-manchot-histoire-geographie-situer-dans-le-temps-cm1 | bandit manchot | **couvert-valide** (2026-09-09, s32, instance A) - **`HG-CM1-01` ferme.** `FER-1` : les cinq periodes de la parenthese chacune servies en bonne reponse. `FER-2` : 10 items. `FER-3` : deux operations mentales distinctes (ordonner, identifier a partir d'un fait decrit), la moins servie a 5 items. Exactitude verifiee sur les dix items. **Mention de couverture partielle, due, et ecrite ici mot pour mot depuis sa source (`nadia-prof`)** : "ordre et identification des cinq grandes periodes par la lecture seule ; le placement sur une frise reste non couvert." |
| histoire-geographie | CM1 | Se reperer dans le temps : construire des reperes historiques | Situer un fait dans un siècle ou un millénaire et ordonner des faits les uns par rapport aux autres. | bandit-manchot-histoire-geographie-situer-dans-le-temps-cm1 | bandit manchot | **couvert-valide** (2026-09-10, s33, instance A) - **`HG-CM1-02` ferme.** `HG-R11`, bloquante, **levee** : les deux occurrences de "le sacre de Clovis, roi des Francs, en 481" sont remplacees par "Clovis devient roi des Francs, en 481" ; verifie sur le fichier entier, plus aucune occurrence de "sacre de Clovis". `HG-R9`, non bloquante, **levee** : les cinq indices de la tache d'ordre nomment l'operation a faire (comparer les dates) et ne portent plus aucune date. `FER-1`, `FER-2` (10 items), `FER-3` (5 contre 5) reverifiees, non rediscutees. Exactitude verifiee sur les dix items, aucune inexactitude. **Reserve neuve, non bloquante** : `HG-R13` (trois propositions de la tache d'ordre sont des phrases a verbe conjugue qui ne se lisent pas recollees a leur question ; regime rejeu borne, 0,25 - la faute est celle de `nadia-prof` elle-meme, dans le texte qu'elle avait dicte). Story : `docs/backlog/backlog.md`, `S33-HG-02`. Verdict : `squad/sprints/s33-plan-2-tenu-le-palier-2-differe-a-s34/verdicts-nadia-instance-A.md`, section 2. Fiche : `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| histoire-geographie | CM1 | Et avant la France ? | Identifier les traces d'une occupation ancienne du territoire et ce que les Gaulois, les Grecs et les Romains y ont laissé. | bandit-manchot-histoire-geographie-nommer-en-histoire-cm1 | bandit manchot | **couvert-valide** (2026-09-09, s32, instance A) - **`HG-CM1-03` ferme.** `FER-1` : les trois peuples de la parenthese chacun servis en bonne reponse. `FER-2` : 10 items. `FER-3` : deux operations mentales distinctes (nommer le peuple a partir d'une trace decrite, dire ce qu'un peuple a laisse), la moins servie exactement au plancher structurel de trois items - la tache de nomination ne peut pas depasser trois sans repeter une bonne reponse (`FAB-1a`), le libelle enumerant trois peuples. Exactitude verifiee sur les dix items. Aucune mention de couverture partielle n'est due |
| histoire-geographie | CM1 | Et avant la France ? | Identifier les grands mouvements et déplacements de populations du IVe au Xe siècle et leurs effets sur le territoire. | bandit-manchot-histoire-geographie-nommer-en-histoire-cm1 | bandit manchot | **couvert-valide** (2026-09-10, s33, instance A) - **`HG-CM1-04` ferme.** `HG-R12`, bloquante, **levee** : l'item sur l'armee arretee a Poitiers en 732 demande desormais l'origine geographique de l'armee ("D'ou venait cette armee ?" -> "d'Espagne", contre "d'Italie" et "d'Angleterre"), et non plus un nom de peuple ; verifie sur les dix items, aucune bonne reponse ne designe plus un groupe humain par un exonyme non revendique. Aucun quatrieme item ajoute : la premiere des deux directions ecrites au refus de s32 est prise et suffit. `HG-R10`, non bloquante, **levee** : l'indice de l'item 87 n'attribue plus a 481 l'unification du royaume. `FER-1`, `FER-2` (10 items), `FER-3` (3 contre 7, minorite au plancher, non structurel) reverifiees. Exactitude verifiee sur les dix items, aucune inexactitude. Story : `docs/backlog/backlog.md`, `S33-HG-04`. Verdict : `squad/sprints/s33-plan-2-tenu-le-palier-2-differe-a-s34/verdicts-nadia-instance-A.md`, section 3. Fiche : `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| histoire-geographie | CM1 | Le temps des rois | Nommer et situer dans le temps quelques rois qui ont marqué la construction du royaume (Louis IX, François Ier, Henri IV, Louis XIV). | - | - | non couvert - ligne 5, laissee de cote par ce premier paquet (`nadia-prof`, `cm1-histoire-geographie.yaml`) ; a servir dans un paquet ulterieur. |
| histoire-geographie | CM1 | Le temps des rois | Associer chacun de ces rois à un fait ou à une réalisation qui le caractérise (la croisade, la Renaissance, l'édit de Nantes, Versailles). | bandit-manchot-histoire-geographie-nommer-en-histoire-cm1 | bandit manchot | **couvert-valide** (2026-09-06, s29, composition A) - **`HG-CM1-06` ferme.** `FER-1` : les quatre realisations de la parenthese enfin servies en bonne reponse - elles ne l'etaient pas avant ce lot. `FER-2` : 10 items. `FER-3` : deux taches a 4 items. Aucune tradition parmi les faits. Aucune mention de couverture partielle n'est due |
| histoire-geographie | CM1 | Le temps de la Revolution et de l'Empire | Situer les événements majeurs de la Révolution française (1789, l'abolition des privilèges, la Déclaration des droits de l'homme et du citoyen, la fin de la monarchie). | bandit-manchot-histoire-geographie-situer-dans-le-temps-cm1, bandit-manchot-histoire-geographie-nommer-en-histoire-cm1 | bandit manchot | **couvert-valide** (2026-09-06, s29, composition A) - **`HG-CM1-07` ferme.** `FER-1` : les quatre evenements de la parenthese. `FER-2` : 10 items. `FER-3` : 7 contre 3, exactement au plancher, `nadia-prof` le dit tel quel. Chronologie revolutionnaire juste sur les dix items. Aucune mention de couverture partielle n'est due |
| histoire-geographie | CM1 | Le temps de la Revolution et de l'Empire | Identifier le passage de la Révolution à l'Empire et ce que Napoléon Bonaparte y change. | bandit-manchot-histoire-geographie-nommer-en-histoire-cm1 | bandit manchot | **couvert-valide** (2026-09-10, s33, instance A) - **`HG-CM1-08` ferme, sur sa part servie.** Dix items neufs se partagent exactement cinq contre cinq entre "le passage de la Revolution a l'Empire" et "ce que Napoleon Bonaparte y change" (`FER-1` tenue sur les deux moities du libelle, en bonne reponse et non en decor). `FER-2` : 10 items. `FER-3` : minorite a 5, au-dessus du plancher de 3. `HG-R4` (mediane de charge de lecture du paquet) reverifiee a 16 mots avant comme apres les dix-huit items neufs du lot : tenue. `HIS-1` reverifiee sur le fichier entier (110 items) : 20,9 % de restitution, sous le tiers. `PLA-1` tenue sur les dix items, chacun portant au moins un distracteur serieux. Exactitude verifiee sur les neuf faits neufs. **Trois reserves neuves, non bloquantes, regime mot pour mot 0,25 chacune** : `HG-R14` (l'enonce sur le Consulat ne nomme pas celui qui devient empereur), `HG-R15` (l'indice du Code civil ne designe aucune des trois propositions), `HG-R16` (l'enonce sur la Banque de France lui attribue une mission - fabriquer la monnaie - qu'elle n'a pas recue a sa creation). Story : `docs/backlog/backlog.md`, `S33-HG-08-12`. Verdict : `squad/sprints/s33-plan-2-tenu-le-palier-2-differe-a-s34/verdicts-nadia-instance-A.md`, section 4 |
| histoire-geographie | CM1 | Se reperer dans l'espace : construire des reperes geographiques | Se repérer sur une carte de France et sur un planisphère (océans, continents, quelques repères physiques, quelques grandes villes). | - | - | non couvert - palier 2 requis (se reperer sur une carte) ; un quiz de noms ne sert pas la competence, `nadia-prof` le nomme explicitement. |
| histoire-geographie | CM1 | Se reperer dans l'espace : construire des reperes geographiques | Situer son lieu de vie à différentes échelles (commune, département, région, France, Europe, monde). | - | - | non couvert - meme motif : palier 2 requis, non construit a ce jour ; le lieu de vie de l'enfant n'est pas connu du produit (`DEC-0028`, aucune persistance). |
| histoire-geographie | CM1 | Decouvrir le(s) lieu(x) ou j'habite, se loger, travailler, se cultiver | Identifier les caractéristiques d'un lieu de vie et le distinguer d'un autre (espace urbain, espace périurbain, espace rural). | bandit-manchot-histoire-geographie-nommer-en-geographie-cm1 | bandit manchot | **couvert-valide** (2026-09-04, s28, second passage, composition B) - **`HG-CM1-11` ferme.** Le paquet passe de deux items a dix, exactement le plancher (`FER-2`) : les trois types d'espace (urbain, periurbain, rural) sont tous en bonne reponse (`FER-1`), sur deux taches distinctes - nommer le type a partir d'une description, retrouver une caracteristique d'un type donne - la moins servie a quatre items (`FER-3`). `HG-R3` (distracteurs absurdes sur l'item rural, reduction a un seul trait) **levee** : distracteurs remplaces par des activites plausibles, enonce nuance, item de contre-exemple ajoute (village de pecheurs). `HIS-1`, `HIS-2`, `HIS-3` verifies sur les dix-sept items neufs du lot ; `HIS-2` et l'exactitude factuelle restent sans garde executable. Reserves non bloquantes, section synthese de s28 : **`HG-R4`** (charge de lecture, mediane du fichier a 16 mots), **`HG-R5`** (mot d'indice "recolte" a corriger sur l'item du village de pecheurs) |
| histoire-geographie | CM1 | Decouvrir le(s) lieu(x) ou j'habite, se loger, travailler, se cultiver | Décrire comment on se loge, on travaille, on se cultive et on se déplace dans un espace urbain et dans un espace touristique. | bandit-manchot-histoire-geographie-nommer-en-geographie-cm1 | bandit manchot | **couvert-valide** (2026-09-11, s34, instance A) - **`HG-CM1-12` ferme.** `HG-R17`, bloquante, **levee** : sur les dix-huit distracteurs neufs de la ligne, chacun est desormais une chose reelle du meme genre que la bonne reponse (0 sur 18 venant du registre de la ferme, du champ, de la peche ou de la batellerie, contre 14 sur 18 au refus de s33) ; verifie item par item sur les dix items vus a l'ecran, un enfant qui ne connait pas la reponse ne peut plus l'ecarter par le registre. `HG-R18` levee : l'enonce du secteur d'activite n'enumere plus deux de ses trois propositions. `HIS-5`, `FER-1`, `FER-2` (10 items), `FER-3` reverifiees, non rediscutees. Exactitude verifiee sur les dix items. Aucune mention de couverture partielle n'est due. **Quatre reserves neuves, non bloquantes, regime mot pour mot 0,25 chacune (`PAR-10`)** : `HG-R19` (l'item des monuments historiques reste resolvable par un mot partage entre son enonce et sa bonne reponse), `HG-R20` (deux items, telesiege et festival, portent desormais un distracteur devenu un candidat serieux et vrai, revers de la correction), `HG-R21` (deux indices, logement de montagne et musee, ne separent plus les trois propositions depuis que les distracteurs sont devenus serieux), `HG-R22` (le dixieme item de la ligne, ecrit a s27, sorti par erreur du perimetre de reprise que `nadia-prof` avait elle-meme chiffre - a son propre cout). Story : `docs/backlog/backlog.md`, `S34-HG-12`. Verdict : `squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/verdicts-nadia-instance-A.md`, section 3. Fiche : `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` |
| histoire-geographie | CM1 | Consommer en France | Identifier d'où viennent l'eau, l'énergie et les aliments consommés, et les étapes de leur acheminement. | bandit-manchot-histoire-geographie-nommer-en-geographie-cm1 | bandit manchot | **couvert-valide** (2026-09-04, s28, second passage, composition B) - **`HG-CM1-13` ferme.** Le paquet passe de quatre items (energie seule) a dix, exactement le plancher (`FER-2`) : eau, energie et aliments sont desormais tous les trois servis (`FER-1`), sur deux taches distinctes - origine de la ressource, acheminement jusqu'a nous - la seconde, absente a s27, porte quatre items (`FER-3`). `HIS-1`, `HIS-2`, `HIS-3` verifies sur les dix-sept items neufs du lot ; `HIS-2` et l'exactitude factuelle restent sans garde executable. Reserves non bloquantes, section synthese de s28 : **`HG-R4`** (charge de lecture), **`HG-R5`** (sans objet sur cette ligne, item concerne sur la ligne 11) |
| histoire-geographie | CM1 | Comprendre un document | Comprendre un document (texte, carte, image) : identifier sa nature, sa date et son auteur, et en extraire une information. | bandit-manchot-histoire-geographie-lire-un-document-cm1 | bandit manchot | **couvert-valide** (2026-09-06, s29, composition A) - **`HG-CM1-14` ferme sur sa part servie.** `FER-1` : les quatre composantes du libelle, dont nature et auteur, enfin servies. `FER-2` : 10 items. `FER-3` : 6 contre 4. **Mention de couverture partielle exigee d'avance et reprise mot pour mot (`marc-dev`, `nadia-prof`) : "document textuel seul ; carte et image restent non couvertes"**. Deux reserves non bloquantes, datees du 2026-09-06 : **`HG-R6`** (deux items de nature offrent une proposition qui s'elimine sans rien savoir, rejeu borne) et **`HG-R7`** (inexactitude du texte-support sur la date ou le 14 juillet devient fete nationale, correction fournie mot pour mot) |

**Bilan du 2026-09-03 (s27)** : 14 lignes. Onze passent `non couvert` -> `en cours` (premier
paquet de la matiere, `bandit-manchot-histoire-geographie-cm1`, VALIDE). Trois restent `non
couvert` : la ligne 5 (les rois), volontairement laissee de cote par ce premier paquet, et les
lignes 9 et 10 (carte, planisphere, echelles), hors de portee du palier 1. Aucune ligne ne passe
`couvert-valide` : premier paquet de matiere neuve, il ouvre, il ne ferme pas.

**Bilan du 2026-09-04 (s28, second passage, composition B)** : deux des onze lignes `en cours`
ferment - `HG-CM1-11` (types d'espaces) et `HG-CM1-13` (eau, energie, aliments) passent
`couvert-valide`. Les neuf autres lignes `en cours` restent `en cours`, non touchees par ce lot
(`nadia-prof` : *"le lot est integralement consacre a la fermeture"*), et les trois lignes `non
couvert` restent inchangees. Bilan de la matiere apres s28 : 2 `couvert-valide`, 9 `en cours`,
3 `non couvert`, 14 au total.

**Bilan du 2026-09-06 (s29, composition A)** : trois des neuf lignes `en cours` ferment -
`HG-CM1-06` (rois et realisations), `HG-CM1-07` (Revolution) et `HG-CM1-14` (comprendre un
document, sur sa part servie) passent `couvert-valide`. **`nadia-prof` a ecrit deux fois "neuf
lignes en cours" (arbitrages de s28 et de s29) - le chiffre etait exact avant ce lot, elle le
corrige elle-meme apres l'avoir joue : il en reste six**, pas neuf : `HG-CM1-01`, `-02`, `-03`,
`-04`, `-08` et `-12`, dont cinq portent exactement un item. Les lignes `-05`, `-09` et `-10` ne
sont pas
`en cours` : `-05` n'a jamais ete servie (laissee de cote par le premier paquet) et `-09`/`-10`
sont hors de portee du palier 1. Bilan de la matiere apres s29 : 5 `couvert-valide`, 6 `en cours`,
3 `non couvert`, 14 au total.

**Bilan du 2026-09-09 (s32, instance A)** : deux des six lignes `en cours` ferment - `HG-CM1-01`
(frise des grandes periodes, sur sa part servie) et `HG-CM1-03` (traces d'occupation ancienne)
passent `couvert-valide`. Deux refus maintiennent `HG-CM1-02` et `HG-CM1-04` `en cours` (`HG-R11`,
`HG-R12`, bloquantes, reprises a s33 sous `S33-HG-02` et `S33-HG-04`). Bilan de la matiere apres
s32 : 7 `couvert-valide`, 4 `en cours` (`HG-CM1-02`, `-04`, `-08`, `-12`), 3 `non couvert`, 14 au
total.

**Bilan du 2026-09-10 (s33, instance A)** : trois des quatre lignes `en cours` ferment -
`HG-CM1-02` (fait faux du sacre de Clovis, corrige), `HG-CM1-04` (le terme "les Sarrasins",
remplace par l'origine geographique de l'armee) et `HG-CM1-08` (remplissage sur le passage de la
Revolution a l'Empire, sur sa part servie) passent `couvert-valide`. Un refus maintient
`HG-CM1-12` `en cours` (`HG-R17`, bloquante : le registre des dix-huit distracteurs neufs designe
la faussete sans que l'enfant lise la question ; reprise a ecrire pour s34). Bilan de la matiere
apres s33 : 10 `couvert-valide`, 1 `en cours` (`HG-CM1-12`), 3 `non couvert` (`HG-CM1-05`, `-09`,
`-10`), 14 au total.

**Bilan du 2026-09-11 (s34, instance A)** : la seule ligne `en cours` ferme - `HG-CM1-12` (les
dix-huit distracteurs neufs reecrits, `HG-R17` levee) passe `couvert-valide`. Bilan de la matiere
apres s34 : 11 `couvert-valide`, plus aucune `en cours`, 3 `non couvert` (`HG-CM1-05`, `-09`,
`-10`), 14 au total.

**Bilan du 2026-09-11 (s35, `S35-CONS-4`)** : **aucun mouvement de statut.** `bandit-manchot-histoire-geographie-cm1` est scinde en quatre entrees de catalogue neuves - `bandit-manchot-histoire-geographie-situer-dans-le-temps-cm1`, `bandit-manchot-histoire-geographie-nommer-en-histoire-cm1`, `bandit-manchot-histoire-geographie-nommer-en-geographie-cm1`, `bandit-manchot-histoire-geographie-lire-un-document-cm1` (`DEC-0088`) - et la colonne `Jeu(x) couvrant` des onze lignes deja `couvert-valide` est mise a jour ci-dessus pour nommer celle qui sert chacune, sur la repartition verifiee par `nadia-prof` (fidelite a la main sur les 110 items, aucun perdu, aucun neuf) : `HG-CM1-01`, `-02` -> *Situer dans le temps* ; `HG-CM1-03`, `-04`, `-06`, `-08` -> *Nommer, en histoire* ; `HG-CM1-07` -> les deux, ce que `nadia-prof` confirme explicitement souhaitable ; `HG-CM1-11`, `-12`, `-13` -> *Nommer, en geographie* ; `HG-CM1-14` -> *Lire un document*. **Aucun statut ne change** : les onze lignes restent `couvert-valide`, les trois lignes `non couvert` (`HG-CM1-05`, `-09`, `-10`) restent inchangees. **Reserve neuve, non bloquante** : `CONS-5` (quatre items sur 110 ne font pas ce que leur consigne annonce - deux paquets *Nommer* concernes). Verdict : `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md`, bloc de s35 ; `verdicts-nadia.md`, ligne `S35-CONS-4`.

## Langues vivantes (anglais) - CP (cycle 2)

Source : `squad/programme/langues-vivantes/competences-cp.md`. **Nommage de colonne `Matiere`
non tranche** : la matiere du programme est *langues vivantes*, l'anglais est la langue que le
produit choisit d'illustrer ; acter ce choix en silence serait une decision de produit non prise.
Le libelle `langues vivantes (anglais)` est retenu en attendant que l'utilisatrice confirme ou
change ce nom (`nadia-prof`, section 6 de son arbitrage ; routage complet en `DEC-0072`).

**Anglais CP refuse d'avance par `nadia-prof`, deux motifs autonomes, chacun suffisant** :
interference avec la ligne "Associer les graphemes aux phonemes" (francais CP, `en cours`), et
absence d'introduction orale prealable sur un programme de langue vivante oral d'abord. Consequence
: 5 des 6 lignes sont `non couvrable en l'etat`.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| langues vivantes (anglais) | CP | Ecouter et comprendre | Comprendre à l'oral les consignes de classe et des expressions très courantes. | - | - | **non couvrable en l'etat** (2026-09-02) - contrainte fondatrice : aucun son fourni par le produit (reconduit sans changement). `nadia-prof` : "Pas de son. Contrainte fondatrice, nature reversible." |
| langues vivantes (anglais) | CP | Parler en continu | Reproduire un modèle oral : un mot, une formule de politesse, une comptine. | - | - | **non couvrable en l'etat** (2026-09-02) - deux motifs independants, chacun suffisant : aucun son fourni, et jugement humain sur une production libre (`ADR-0008`). |
| langues vivantes (anglais) | CP | Prendre part a une conversation | Se présenter oralement et répondre à une question simple sur soi. | - | - | **non couvrable en l'etat** (2026-09-02) - memes deux motifs que la ligne precedente. |
| langues vivantes (anglais) | CP | Lire et comprendre | Reconnaître à l'écrit des mots familiers déjà rencontrés à l'oral, dans les champs lexicaux du cycle 2 (nombres, couleurs, animaux, corps, école, famille). | - | - | **non couvrable en l'etat** (2026-09-02) - refuse d'avance pour le CP specifiquement, deux motifs autonomes : interference avec la ligne "Associer les graphemes aux phonemes" (francais CP, en cours), et absence d'introduction orale prealable sur un programme oral d'abord. Nature reversible : une decision future sur le son la rouvrirait. `nadia-prof` : "le produit fait passer un examen qu'il n'a pas enseigne". |
| langues vivantes (anglais) | CP | Ecrire | Copier des mots isolés et des expressions courtes. | - | - | **non couvrable en l'etat** (2026-09-02, au titre de `DEC-0066`) - contrainte fondatrice, nature reversible : competence graphomotrice, aucun geste de trace pour ce produit (`DEC-0046`). |
| langues vivantes (anglais) | CP | Decouverte culturelle | Découvrir quelques aspects culturels des pays de la langue étudiée (fêtes, lieux, vie quotidienne). | - | - | non couvert - jouable en francais seulement (decouverte culturelle, aucune competence linguistique). Mention exigee d'avance : "decouverte culturelle seule, en francais ; aucune competence linguistique n'est servie". Conditions `BIA-1`, `BIA-2` opposables. |

**Bilan** : 6 lignes, 1 `non couvert`, 5 `non couvrable en l'etat`. 1 ligne entre au denominateur
atteignable.

## Langues vivantes (anglais) - CM1 (cycle 3)

Source : `squad/programme/langues-vivantes/competences-cm1.md`. Meme reserve de nommage que la
section precedente. **Reporte a s28** (voir `DEC-0072`) : aucun paquet n'est affecte a s27 sur ces
lignes, l'arithmetique de capacite de `nadia-prof` ne laissant tenir que deux matieres neuves sur
les trois demandees.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| langues vivantes (anglais) | CM1 | Ecouter et comprendre | Écouter et comprendre un message oral simple ou un court récit. | - | - | **non couvrable en l'etat** (2026-09-02) - contrainte fondatrice, aucun son fourni, nature reversible. |
| langues vivantes (anglais) | CM1 | Prendre part a une conversation | Prendre part à une conversation simple : saluer, se présenter, demander et donner une information. | - | - | **non couvrable en l'etat** (2026-09-02) - deux motifs independants : aucun son fourni, et jugement humain sur une production libre (`ADR-0008`). |
| langues vivantes (anglais) | CM1 | Parler en continu | Parler en continu : décrire, raconter ou présenter en quelques phrases. | - | - | **non couvrable en l'etat** (2026-09-02) - memes deux motifs que la ligne precedente. |
| langues vivantes (anglais) | CM1 | Lire et comprendre | Lire et comprendre des mots et des expressions familiers dans les champs lexicaux du cycle 3. | bandit-manchot-anglais-cm1 | bandit manchot | **couvert-valide** (2026-09-04, s28) - **`LVE-CM1-04` ferme.** Premier paquet, textuel (aucune illustration, `REC-1` a `REC-4` sans objet). Quinze items sur cette ligne (plancher dix, `FER-2`), trois champs lexicaux declares et servis a cinq mots chacun (`ANG-4`), deux taches de traduction - francais -> anglais (douze items), anglais -> francais (trois items), la moins servie exactement au plancher (`FER-3`). Quinze traductions verifiees une par une, aucune erreur. Aucun dispositif ne demande a un adulte de prononcer un mot d'anglais. Reserve non bloquante **`ANG-R1`** : l'item `montagne` -> `mountain` viole `REC-5` (mot transparent en bonne reponse) et `REC-6` (longueur non voisine des deux autres propositions) - correction fournie mot pour mot par `nadia-prof`, routee a s29 |
| langues vivantes (anglais) | CM1 | Lire et comprendre | Lire et comprendre un texte court et simple (consigne, message, courte description). | - | - | non couvert - jouable, sous condition que le lexique de la ligne 4 soit deja servi en choix. **Une condition sur deux est remplie depuis s28** : le lexique de `LVE-CM1-04` existe et est `couvert-valide` ; `nadia-prof` (2026-09-04) exige un second paquet lexical avant que cette ligne s'ouvre - refusee d'avance pour s28, non instruite pour s29. |
| langues vivantes (anglais) | CM1 | Ecrire | Écrire des mots et des expressions mémorisés (légender une image, renseigner une fiche). | - | - | non couvert - jouable par saisie, sous conditions `ANG-S1` et `ANG-S2` (lexique ferme deja servi en choix, indice sans jamais montrer le mot). |
| langues vivantes (anglais) | CM1 | Decouverte culturelle | Découvrir quelques aspects culturels des pays de la langue étudiée. | bandit-manchot-anglais-cm1 | bandit manchot | **couvert-valide** (2026-09-09, s32, instance A) - **`LVE-CM1-07` ferme, VALIDE SOUS RESERVE.** `FER-2` : 10 items (cinq de s28, cinq neufs). `FER-1` : la ligne n'enumere rien, condition acceptee sur la declaration du lot (trois aspects - fetes, lieux, vie quotidienne - chacun servi). `FER-3` : etablie par `nadia-prof` elle-meme faute de declaration de tache par item, 4 contre 6, au-dessus du plancher. `BIA-1`, `BIA-2` tenues. Exactitude verifiee sur les dix items. **Mention de couverture partielle, due, et reprise mot pour mot depuis sa source** : "decouverte culturelle seule, en francais ; aucune competence linguistique n'est servie". Quatre reserves non bloquantes, datees du 2026-09-09 : **`ANG-R3`** (trois des cinq items neufs se repondent par la seule lecture de l'enonce, sans savoir sur le pays), **`ANG-R4`** (l'enonce de l'item 23 coupe sa question principale par une incise de 14 mots), **`ANG-R5`** (l'indice de l'item 16 attribue a "Londres" le nom de la grande roue, qui est "London Eye"), **`ANG-R6`** (l'item 24 ecarte son seul candidat serieux, "en voiture avec un parent", au profit de deux propositions non concurrentes). **Une incoherence du referentiel, tracee et non corrigee ici** : la parenthese "(fetes, lieux, vie quotidienne)" que le lot invoque porte sur `LVE-CP-06`, pas sur cette ligne, qui n'en porte aucune - meme competence, deux niveaux, une seule parenthese ecrite. **Correction du 2026-09-11 (s34, instance A, `S34-ANG-CORR`) : VALIDE SOUS RESERVE, ne bouge aucune ligne** - la ligne est deja `couvert-valide` depuis s32. `ANG-R4`, `ANG-R5`, `ANG-R6` **levees**. `ANG-R3` **payee sur deux items des trois** : elle reste ouverte, non bloquante, perimetre reduit a l'item restant. Reserve neuve, non bloquante : `ANG-R7`. Verdict : `squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/verdicts-nadia-instance-A.md`, section 4. |

**Bilan, corrige le 2026-09-05 (`camille-po`) - le bilan precedent (4 `non couvert`) datait
d'avant le lot de s28 et n'avait pas ete mis a jour** : 7 lignes, 2 `non couvert`, 1 `en
cours` (`LVE-CM1-07`), 1 `couvert-valide` (`LVE-CM1-04`), 3 `non couvrable en l'etat`. 4
lignes entrent au denominateur atteignable (inchange).

**Bilan du 2026-09-09 (s32, instance A)** : `LVE-CM1-07` passe `en cours` -> `couvert-valide`.
Bilan de la matiere apres s32 : 7 lignes, 2 `non couvert`, plus aucune `en cours`, 2
`couvert-valide` (`LVE-CM1-04`, `LVE-CM1-07`), 3 `non couvrable en l'etat`. 4 lignes entrent au
denominateur atteignable (inchange).

## Arts plastiques - CP (cycle 2)

Source : `squad/programme/arts-plastiques/competences-cp.md`, ecrit par `nadia-prof` le 2026-09-03
(`DEC-0074`). Moyens du produit : mecanique de choix (`bandit-manchot`), mecanique de saisie,
palier 1 visuel - une image fixe, des propositions en texte (`ADR-0073`). Aucun son, aucun geste de
trace, aucune persistance (`DEC-0028`, `DEC-0046`, `DEC-0066`). Le palier 2 (designer un lieu ou un
objet sur une image) n'existe pas encore : il est constructible (ADR de `theo-archi` en s28), une
ligne qui l'attend est `non couvert`, jamais `non couvrable en l'etat`.

**Sept lignes sur onze sont hors de portee definitive du produit** : une production plastique ne se
produit ni par un choix ni par une saisie. Les deux lignes jouables des aujourd'hui (5, 11) portent
sur le vocabulaire et les connaissances de la pratique, jamais sur la pratique elle-meme. Les deux
lignes restantes (9, 10) exigent une image d'oeuvre que le depot ne sert pas - **question de droits
d'usage non instruite, ouverte formellement en `DEC-0075`, non tranchee.**

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| arts plastiques | CP | La representation du monde | Représenter le monde environnant ou donner forme à son imaginaire en explorant plusieurs domaines (dessin, peinture, collage, modelage). | - | - | **non couvrable en l'etat**, nature irreversible - production plastique ; aucun geste de trace fourni par ce produit (`DEC-0046`, `DEC-0066`) et jugement humain sur une production libre (`ADR-0008`). |
| arts plastiques | CP | La representation du monde | Employer divers outils, supports et matériaux pour représenter un objet, un paysage ou un personnage. | - | - | **non couvrable en l'etat**, nature irreversible - geste materiel sur une matiere physique, aucune mecanique de ce produit ne l'ouvre. |
| arts plastiques | CP | La representation du monde | Observer les effets produits par les gestes et les outils sur la matière et les nommer (frotter, gratter, tracer, superposer). | - | - | **non couvrable en l'etat**, nature irreversible - l'objet observe est la production de l'enfant lui-meme, sans production rien a observer. Recouvrement a ne pas double-compter avec la ligne 11 (part lexicale deja portee). |
| arts plastiques | CP | L'expression des emotions | Exprimer une émotion ou une sensation par le choix des couleurs, des formes et des matières. | - | - | **non couvrable en l'etat**, nature irreversible - production libre, jugement humain (`ADR-0008`). |
| arts plastiques | CP | L'expression des emotions | Reconnaître et nommer les couleurs, distinguer les couleurs primaires de celles obtenues par mélange. | - | - | non couvert - jouable au choix textuel, sous `ART-1`. Le melange des couleurs est une connaissance qui se pose en trois propositions ecrites et se verifie, sans demander a l'enfant de percevoir une couleur. |
| arts plastiques | CP | L'expression des emotions | Dire ce que l'on ressent devant une image ou une œuvre et écouter ce que d'autres en disent. | - | - | **non couvrable en l'etat**, nature irreversible - aucune bonne reponse : une mecanique dont le contrat est une bonne reponse parmi trois sanctionnerait le ressenti d'un enfant. |
| arts plastiques | CP | La narration et le temoignage par les images | Raconter une histoire ou un événement par une suite d'images. | - | - | **non couvrable en l'etat**, nature irreversible - production plastique et narrative ; ordonner des images deja faites demanderait une manipulation de collection (palier 3), refusee par `V3`. |
| arts plastiques | CP | La narration et le temoignage par les images | Transformer une image existante (découpage, ajout, association) pour en modifier le sens. | - | - | **non couvrable en l'etat**, nature irreversible - production plastique, memes deux motifs que la ligne 1. |
| arts plastiques | CP | Se reperer dans les domaines lies aux arts plastiques | Décrire une œuvre : dire ce qu'on y voit (sujet, couleurs, formes, matériaux). | - | - | non couvert - palier 1 illustre, image d'oeuvre requise. La forme existe (image fixe, propositions en texte, `ADR-0073`), mais aucune reproduction d'oeuvre n'est servie par le depot ; un item textuel qui decrirait l'oeuvre serait une contrefacon (`CON-1`). Question de droits d'usage ouverte en `DEC-0075`, non tranchee. |
| arts plastiques | CP | Se reperer dans les domaines lies aux arts plastiques | Reconnaître quelques œuvres déjà rencontrées et les rapprocher les unes des autres. | - | - | non couvert - meme motif que la ligne precedente (image d'oeuvre requise, `DEC-0075`). Un quiz de noms ("qui a peint la Joconde ?") servirait la culture generale, pas la reconnaissance d'une oeuvre. |
| arts plastiques | CP | Se reperer dans les domaines lies aux arts plastiques | Nommer quelques outils, supports et gestes du travail plastique (pinceau, craie, argile, esquisse). | bandit-manchot-outils-arts-cp | bandit manchot | **couvert-valide** (2026-09-22, s39) - **`ART-CP-11` ferme.** Done pedagogique **VALIDE, sans reserve**, prononce par `nadia-prof` apres avoir joue le jar servi (`squad/conformite/FICHE-bandit-manchot-outils-arts-cp.md`). Les quatre termes de la parenthese (pinceau, craie, argile, esquisse) chacun servis en bonne reponse sur les 12 items, `ART-1` et `ART-2` tenues, `ADR-0089` (lisibilite CP) verifie a l'ecran. |

**Conditions opposables, posees le 2026-09-03** : `ART-1` (aucun item ne fait porter la reponse par
une couleur percue - la ligne 5 se sert par le nom ecrit des couleurs et par la regle de melange,
pour ne pas exclure un enfant daltonien) ; `ART-2` (aucun item ne demande a l'enfant ce qu'il
ressent, ni ne qualifie une oeuvre de belle, reussie ou ratee).

**Mention exigee d'avance si une ligne bouge** : si les lignes 5 et 11 passent a `couvert-valide`,
la matrice porte la mention "vocabulaire et connaissances de la pratique plastique seuls ; aucune
production plastique n'est servie par ce produit".

**Bilan** : 11 lignes, 4 `non couvert`, 7 `non couvrable en l'etat`. 4 lignes entrent au
denominateur atteignable.

## Arts plastiques - CM1 (cycle 3)

Source : `squad/programme/arts-plastiques/competences-cm1.md`, ecrit par `nadia-prof` le
2026-09-03 (`DEC-0074`). Memes moyens du produit que la section precedente. **Regle de nature
posee ici et qui gouverne huit lignes** : quand une ligne cumule un motif reversible (le geste que
le produit ne fournit pas) et un motif irreversible (le jugement humain sur ce que l'enfant a
produit), c'est le motif irreversible qui donne la nature de la ligne.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| arts plastiques | CM1 | La representation plastique et les dispositifs de presentation | Représenter le monde environnant ou donner forme à son imaginaire en explorant plusieurs domaines (dessin, collage, modelage, sculpture, photographie). | - | - | **non couvrable en l'etat**, nature irreversible - production plastique ; aucun geste de trace fourni (`DEC-0046`) et jugement humain sur une production libre (`ADR-0008`). |
| arts plastiques | CM1 | La representation plastique et les dispositifs de presentation | Utiliser un procédé de représentation (ressemblance, écart, transformation) pour rendre compte d'un sujet. | - | - | **non couvrable en l'etat**, nature irreversible - la ligne dit utiliser, pas reconnaitre ; reconnaitre un procede est une autre competence, deja portee par la ligne 7. |
| arts plastiques | CM1 | La representation plastique et les dispositifs de presentation | Expérimenter l'effet des couleurs, des supports, des outils et des gestes sur une représentation. | - | - | **non couvrable en l'etat**, nature irreversible - experimenter est un geste sur une matiere ; la contrefacon par question de sensibilite tombe sous `ART-2`. |
| arts plastiques | CM1 | Les fabrications et la relation entre l'objet et l'espace | Fabriquer un objet ou une construction en volume et adapter sa réalisation à l'espace où elle sera vue. | - | - | **non couvrable en l'etat**, nature irreversible - geste materiel hors de tout ecran, juge par un adulte qui regarde l'objet. |
| arts plastiques | CM1 | Les fabrications et la relation entre l'objet et l'espace | Prendre en compte le lieu et le spectateur dans la présentation d'une production (accrochage, mise en espace). | - | - | **non couvrable en l'etat**, nature irreversible - suppose une production et un lieu reels (`ADR-0008`). |
| arts plastiques | CM1 | La materialite de la production plastique | Choisir et employer une matière, un outil ou un geste en fonction de l'effet plastique recherché. | - | - | **non couvrable en l'etat**, nature irreversible - l'effet recherche est celui de l'enfant, personne d'autre ne peut dire si le choix le sert. |
| arts plastiques | CM1 | La materialite de la production plastique | Repérer, dans une œuvre observée, les effets produits par la matière, la couleur, le geste et le support. | - | - | non couvert - palier 1 illustre, image d'oeuvre requise. Conditions `ART-1` a `ART-5` ; `ART-1` mord en premier (la couleur ne porte jamais seule la reponse). Question de droits d'usage ouverte en `DEC-0075`, non tranchee. |
| arts plastiques | CM1 | S'exprimer, analyser sa pratique et celle des artistes | Décrire une œuvre d'art avec un lexique élémentaire (support, format, matière, couleur, sujet, composition). | - | - | non couvert - meme motif que la ligne precedente (`DEC-0075`). Mention exigee d'avance si la ligne bouge : "support, format, matiere, couleur et sujet seuls ; la composition et le cadrage restent non couverts" - les decrire suppose de designer une zone de l'image (palier 2). |
| arts plastiques | CM1 | S'exprimer, analyser sa pratique et celle des artistes | Exprimer ce qu'on perçoit d'une œuvre, formuler un avis et écouter celui des autres. | - | - | **non couvrable en l'etat**, nature irreversible - jugement humain sur une production libre (`ADR-0008`), aucune bonne reponse unique (`ART-2`). |
| arts plastiques | CM1 | S'exprimer, analyser sa pratique et celle des artistes | Établir un lien entre sa propre production et des œuvres d'artistes rencontrées. | - | - | **non couvrable en l'etat**, nature irreversible - suppose une production de l'enfant, que le produit ne recueille ni ne conserve (`DEC-0028`). Recouvrement a ne pas double-compter avec histoire des arts (reconnaissance et situation d'oeuvres sans lien a une pratique personnelle). |

**Conditions opposables, posees le 2026-09-03** (numerotation continuee depuis les arts plastiques
CP, `ART-1` et `ART-2` s'appliquant a l'identique) : `ART-3` (toute oeuvre servie en image est
identifiee dans le commentaire du paquet - titre, auteur, date, lieu de conservation ; question des
droits d'usage signalee et non tranchee, `DEC-0075`) ; `ART-4` (`CON-1` applique au corpus
d'oeuvres - l'item ne se repond pas sans regarder l'image) ; `ART-5` (le corpus d'un paquet ne se
compose ni d'une seule epoque, ni d'une seule aire culturelle, ni d'artistes d'un seul genre).

**Mention exigee d'avance si une ligne bouge** : si les lignes 7 et 8 passent a `couvert-valide`,
la matrice porte la mention "reception d'oeuvres seule ; aucune pratique plastique n'est servie par
ce produit".

**Bilan** : 10 lignes, 2 `non couvert`, 8 `non couvrable en l'etat`. 2 lignes entrent au
denominateur atteignable.

## Education musicale - CP (cycle 2)

Source : `squad/programme/education-musicale/competences-cp.md`, ecrit par `nadia-prof` le
2026-09-03 (`DEC-0074`). Memes moyens du produit. **C'est la matiere la plus lourdement touchee par
la contrainte fondatrice** : le programme est ecrit autour de deux gestes, chanter et ecouter, et le
produit ne fournit ni l'un ni l'autre. La premisse de l'adulte accompagnant (`DEC-0068`) ne sauve
pas cette matiere : elle vaut sur les lignes ou la voix est la competence et ou l'adulte lit un
texte, jamais sur une hauteur, un timbre ou une pulsation a produire.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| education musicale | CP | Chanter et interpreter | Chanter une comptine ou une chanson par cœur en respectant la hauteur et le rythme. | - | - | **non couvrable en l'etat**, nature irreversible - deux motifs de nature differente : aucun son fourni (reversible seul) et jugement humain sur une production vocale libre (`ADR-0008`, irreversible) ; le second survit au premier. |
| education musicale | CP | Chanter et interpreter | Interpréter un chant avec d'autres en tenant sa place dans le groupe. | - | - | **non couvrable en l'etat**, nature irreversible - production collective en situation ; le produit est joue par un enfant seul, accompagne d'un adulte, il n'y a pas de groupe. |
| education musicale | CP | Chanter et interpreter | Mobiliser son corps pour interpréter : frapper une pulsation, marquer un rythme simple. | - | - | **non couvrable en l'etat**, nature irreversible - geste corporel dans le temps, aucun capteur ne mesure une pulsation frappee. |
| education musicale | CP | Ecouter, comparer et commenter | Repérer et nommer quelques caractéristiques d'un son entendu (fort/doux, aigu/grave, court/long, lent/rapide). | - | - | **non couvrable en l'etat**, nature reversible - contrainte fondatrice, aucun son ; seule une decision de l'utilisatrice sur le son rouvrirait la ligne. Une version textuelle serait une contrefacon (`CON-1`). |
| education musicale | CP | Ecouter, comparer et commenter | Identifier quelques instruments de musique et les familles auxquelles ils appartiennent (cordes, vents, percussions). | - | - | non couvert - jouable sur la seule moitie connaissance (nom des familles d'instruments) ; la reconnaissance a l'oreille reste hors de portee. Mention exigee d'avance si la ligne bouge : "familles d'instruments sur le nom ecrit seul ; la reconnaissance auditive reste non couverte". |
| education musicale | CP | Ecouter, comparer et commenter | Comparer deux extraits musicaux et dire ce qui les distingue. | - | - | **non couvrable en l'etat**, nature reversible - contrainte fondatrice, aucun son ; deux extraits ne se comparent pas par ecrit. |
| education musicale | CP | Ecouter, comparer et commenter | Dire ce que l'on ressent en écoutant une musique et écouter ce que d'autres en disent. | - | - | **non couvrable en l'etat**, nature irreversible - deux motifs autonomes chacun suffisant : aucun son fourni, et aucune bonne reponse (le ressenti n'en a pas). |
| education musicale | CP | Explorer et imaginer | Explorer des sons avec sa voix, son corps ou des objets et les organiser en une production sonore courte. | - | - | **non couvrable en l'etat**, nature irreversible - production sonore libre, jugement humain (`ADR-0008`). |
| education musicale | CP | Explorer et imaginer | Imaginer et coder une organisation simple de sons (plus fort, plus vite, silence). | - | - | **non couvrable en l'etat**, nature irreversible - production libre ; un codage donne a lire serait une autre competence, et il ne se lit pas sans son. |
| education musicale | CP | Echanger, partager et argumenter | Respecter les règles de l'écoute et de la production collective (silence, tour de parole, entrée à un signal). | - | - | **non couvrable en l'etat**, nature irreversible - comportement en situation, observe par un adulte. Recouvrement a ne pas double-compter avec les regles de vie de l'EMC. |
| education musicale | CP | Echanger, partager et argumenter | Nommer quelques repères du vocabulaire musical rencontré (refrain, couplet, pulsation, silence). | - | - | non couvert - jouable au choix textuel, sous `MUS-1`. Connaissance lexicale exacte, courte, du niveau du CP. |

**Conditions opposables, posees le 2026-09-03** : `MUS-1` (aucun item d'education musicale ne
suppose que l'enfant a entendu quoi que ce soit - `CON-1` transpose) ; `MUS-2` (aucun dispositif ou
un adulte chanterait, fredonnerait ou frapperait un rythme - la premisse de l'adulte accompagnant
couvre la lecture d'un texte, pas la production d'une hauteur ou d'une pulsation).

**Mention exigee d'avance si une ligne bouge** : si les lignes 5 et 11 passent a `couvert-valide`,
la matrice porte la mention "vocabulaire musical et familles d'instruments seuls, sur le nom ecrit ;
aucune competence auditive ni vocale n'est servie".

**Bilan** : 11 lignes, 2 `non couvert`, 9 `non couvrable en l'etat`. 2 lignes entrent au
denominateur atteignable.

## Education musicale - CM1 (cycle 3)

Source : `squad/programme/education-musicale/competences-cm1.md`, ecrit par `nadia-prof` le
2026-09-03 (`DEC-0074`). Memes moyens du produit ; `MUS-1` et `MUS-2` s'appliquent a l'identique.
**Le fait qui gouverne ce fichier tient en trois mots : aucun son.** Zero ligne sur dix entre au
denominateur atteignable, et `nadia-prof` a refuse par ecrit deux contrefacons qui l'auraient
maquille (reconnaitre un instrument a l'image, apparier des codages muets).

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| education musicale | CM1 | Chanter et interpreter | Chanter un chant mémorisé en respectant la justesse, le rythme et les nuances. | - | - | **non couvrable en l'etat**, nature irreversible - deux motifs : aucun son (contrainte fondatrice) et jugement humain sur une production libre (`ADR-0008`) ; le second donne la nature. |
| education musicale | CM1 | Chanter et interpreter | Interpréter un chant à plusieurs voix ou en canon, en tenant sa partie dans un ensemble. | - | - | **non couvrable en l'etat**, nature irreversible - memes motifs, plus un ensemble d'autres enfants que rien de ce produit ne fournit. |
| education musicale | CM1 | Chanter et interpreter | Mobiliser son corps et sa voix pour interpréter (posture, respiration, articulation). | - | - | **non couvrable en l'etat**, nature irreversible - geste corporel, juge par un adulte qui regarde et ecoute. |
| education musicale | CM1 | Ecouter, comparer et commenter | Décrire et comparer des éléments sonores d'une œuvre écoutée (timbre, hauteur, durée, intensité, tempo). | - | - | **non couvrable en l'etat**, nature reversible - contrainte fondatrice, aucun son ; se rouvrirait si le produit diffusait un son. |
| education musicale | CM1 | Ecouter, comparer et commenter | Repérer l'organisation d'une œuvre écoutée (thème, couplet et refrain, répétition, contraste). | - | - | **non couvrable en l'etat**, nature reversible - meme motif et meme condition de reouverture. |
| education musicale | CM1 | Ecouter, comparer et commenter | Identifier la famille et le timbre d'un instrument entendu. | - | - | **non couvrable en l'etat**, nature reversible - contrainte fondatrice, aucun son ; identifier un instrument a l'image serait une contrefacon (`CON-1`), refusee d'avance. |
| education musicale | CM1 | Ecouter, comparer et commenter | Situer une œuvre écoutée dans le temps, dans une aire géographique ou dans un genre musical. | - | - | **non couvrable en l'etat**, nature reversible - meme motif. Recouvrement a ne pas double-compter avec histoire des arts (situer une oeuvre connue par son titre, sans ecoute). |
| education musicale | CM1 | Explorer, imaginer et creer | Imaginer et réaliser une production sonore avec sa voix, son corps ou des objets sonores. | - | - | **non couvrable en l'etat**, nature irreversible - production libre jugee par un humain (`ADR-0008`), le produit ne recueille aucun son. |
| education musicale | CM1 | Explorer, imaginer et creer | Utiliser un codage simple pour représenter une organisation sonore, et la réaliser. | - | - | **non couvrable en l'etat**, nature reversible - le codage est une representation d'un son ; apparier des motifs graphiques sans jamais les entendre n'enseigne rien de musical. |
| education musicale | CM1 | Echanger, partager, argumenter et debattre | Exprimer et justifier un avis sur une musique écoutée, et écouter celui des autres. | - | - | **non couvrable en l'etat**, nature irreversible - jugement humain sur une production libre (`ADR-0008`), aucune bonne reponse (`ART-2`). |

**Bilan** : 10 lignes, 0 `non couvert`, 10 `non couvrable en l'etat`. 0 ligne entre au denominateur
atteignable.

## Education physique et sportive - CP (cycle 2)

Source : `squad/programme/education-physique-et-sportive/competences-cp.md`, ecrit par
`nadia-prof` le 2026-09-03 (`DEC-0074`). Memes moyens du produit. **La contrainte decisive n'est
meme pas dans la liste des moyens** : le produit ne recoit aucun mouvement du corps de l'enfant, et
rien dans son architecture ne s'y prete. Aucune des sept lignes hors de portee n'est reversible :
il faudrait un autre produit.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| education physique et sportive | CP | Produire une performance mesurable | Courir, sauter et lancer pour réaliser une performance et la mesurer (temps, distance). | - | - | **non couvrable en l'etat**, nature irreversible - geste moteur mesure, aucun capteur, aucune mecanique de ce produit n'observe un corps. |
| education physique et sportive | CP | Produire une performance mesurable | Comparer sa performance à la précédente et dire si elle progresse. | - | - | **non couvrable en l'etat**, nature irreversible - la performance elle-meme n'est pas mesurable (ligne 1, motif suffisant et irreversible) ; le fait que le produit ne conserve rien d'une session a l'autre (`DEC-0028`) n'ajoute rien. |
| education physique et sportive | CP | Adapter ses deplacements a des environnements varies | Se déplacer en s'adaptant au milieu (dans l'eau, sur un engin roulant, sur un parcours d'équilibre). | - | - | **non couvrable en l'etat**, nature irreversible - geste moteur dans un milieu physique ; une simulation a l'ecran serait une contrefacon (`CON-1`), pas une couverture. |
| education physique et sportive | CP | Adapter ses deplacements a des environnements varies | Connaître et respecter les règles de sécurité propres à l'activité et au lieu de pratique. | bandit-manchot-regles-securite-eps-cp | bandit manchot | **couvert-valide** (2026-09-11, s34, instance C) - **`EPS-CP-04` ferme.** `SEC-R4`, bloquante, **levee** : les items 3 et 8 sont remplaces par un couple sur la cage a grimper - la regle creditee est entiere (ce qu'on verifie avant de descendre) et les trois propositions decrivent toutes la meme situation, verifie a l'ecran. `SEC-R5`, non bloquante, **levee** : les trois indices nommes (items 8, 10, 11) apportent chacun le fait qui fait exister la regle. `FER-1`, `FER-2` (11 items), `FER-3`, `BIN-1`, `COH-1` reverifiees. Exactitude verifiee sur les onze items. **Mention de couverture partielle, due, reprise mot pour mot : "connaissance de la regle de securite seule ; son respect en situation n'est pas servi".** Reserve neuve, non bloquante, a mon cout de `nadia-prof` (perimetre incomplet a s33) : `SEC-R6` (l'indice de l'item 3 n'ecarte aucune des deux propositions fausses). Story : `docs/backlog/backlog.md`, `S34-EPS-SEC`. Verdict : `squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/verdicts-nadia-instance-C.md`, section 3.1. Fiche : `squad/conformite/FICHE-bandit-manchot-regles-securite-eps-cp.md` |
| education physique et sportive | CP | S'exprimer devant les autres par une prestation artistique ou acrobatique | Mobiliser son corps pour exprimer une intention devant des spectateurs (danse, ronde, acrobatie). | - | - | **non couvrable en l'etat**, nature irreversible - production corporelle libre devant des spectateurs, jugement humain (`ADR-0008`). |
| education physique et sportive | CP | S'exprimer devant les autres par une prestation artistique ou acrobatique | Regarder la prestation des autres et en dire quelque chose avec des mots respectueux. | - | - | **non couvrable en l'etat**, nature irreversible - production orale libre, aucune bonne reponse, pas d'autre enfant devant l'ecran. |
| education physique et sportive | CP | Conduire et maitriser un affrontement individuel ou collectif | Tenir un rôle dans un jeu collectif (attaquant, défenseur, arbitre). | - | - | **non couvrable en l'etat**, nature irreversible - role tenu en situation, dans un groupe qui n'existe pas ici. |
| education physique et sportive | CP | Conduire et maitriser un affrontement individuel ou collectif | Connaître et respecter les règles d'un jeu collectif simple. | bandit-manchot-regles-jeu-collectif-eps-cp | bandit manchot | **couvert-valide** (2026-09-11, s34, instance C) - **`EPS-CP-08` ferme.** `JEU-R9`, bloquante, **levee** : les items 3 et 8 forment desormais le couple attendu sur l'epervier (chacun credite "il/elle devient epervier a son tour"), verifie sur une partie ou les deux items sortent a six manches d'intervalle - l'enfant qui a repondu juste au premier ne peut plus etre marque faux sur ce qu'il vient d'apprendre. `JEU-R10`, non bloquante, **levee** : les trois propositions de l'item 10 sont des consequences de meme forme. `COH-1` tenue sur le paquet entier, jeu par jeu. `FER-1`, `FER-2` (11 items), `FER-3` reverifiees. Exactitude verifiee sur les onze items. **Mention de couverture partielle, due, reprise mot pour mot : "connaissance de la regle seule ; la tenue du role en situation reste non couverte".** Deux reserves reconduites, non bloquantes, aucune levee : `JEU-R11` (l'indice de l'item 6 reste au-dessus du vecu de l'enfant sur les demenageurs) et `JEU-R12` (aucune fille n'enfreint de regle sur le paquet). Story : `docs/backlog/backlog.md`, `S34-EPS-JEU`. Verdict : `squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/verdicts-nadia-instance-C.md`, section 3.2. Fiche : `squad/conformite/FICHE-bandit-manchot-regles-jeu-collectif-eps-cp.md` |
| education physique et sportive | CP | Conduire et maitriser un affrontement individuel ou collectif | Accepter le résultat d'une rencontre, gagner et perdre sans agressivité. | - | - | **non couvrable en l'etat**, nature irreversible - un item de choix sur une attitude mesure la reponse attendue, pas la conduite ; contrefacon refusee (`EPS-3`). |
| education physique et sportive | CP | Entretenir sa sante par la pratique | Reconnaître les effets de l'effort sur son corps (essoufflement, cœur qui bat vite, chaleur, soif). | bandit-manchot-corps-effort-eps-cp | bandit manchot | **couvert-valide** (2026-09-11, s34, instance C) - **`EPS-CP-10` ferme.** Quatre modes de defaillance nommes d'avance, tous evites, verifies sur les vingt-deux items joues un par un avec erreur volontaire sur chacun : aucune performance chiffree presentee comme une norme, aucune conduite a la deuxieme personne en bonne reponse, aucun indice qui decrit l'effort par une image, chaque enonce fixe son moment ("Pendant", "Juste apres", "Le lendemain", "Avant"). `EPS-2`, `EPS-3` (au sens du referentiel, conduite morale), `BIA-4` et la frontiere corps-et-effort (`DEC-0083`, section 9.2) tenues, verifiees item par item : aucun item ne porte sur le sommeil, l'alimentation ou l'hygiene du corps. Exactitude verifiee sur les vingt-deux faits credites. `FER-1` (les quatre effets aux items 1 a 4), `FER-2` (onze items par ligne), `FER-3`, `BIN-1`, `LON-1`, `LON-2` tenues. **Mention de couverture partielle, ecrite aujourd'hui, elle n'existait pas : "connaissance des effets de l'effort seule ; ce produit ne recoit aucun corps en mouvement et n'observe aucun effet reel sur l'enfant qui joue".** Sept reserves neuves, non bloquantes, regime rejeu 0,5 pour le paquet entier, solde a s35 (`EFF-R1` a `EFF-R7`), dont trois portant sur cette ligne (`EFF-R2`, `EFF-R3`, `EFF-R5`) et deux sur les deux lignes du paquet a la fois (`EFF-R1`, `EFF-R7`). Story : `docs/backlog/backlog.md`, `S34-EPS-CORPS-EFFORT`. Verdict : `squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/verdicts-nadia-instance-C.md`, section 3.3. Fiche : `squad/conformite/FICHE-bandit-manchot-corps-effort-eps-cp.md` |
| education physique et sportive | CP | Entretenir sa sante par la pratique | Connaître quelques règles d'échauffement, d'hydratation et de récupération autour de l'effort. | bandit-manchot-corps-effort-eps-cp | bandit manchot | **couvert-valide** (2026-09-11, s34, instance C) - **`EPS-CP-11` ferme.** Meme paquet que `EPS-CP-10` : les quatre modes de defaillance nommes d'avance sont tous evites (voir la ligne precedente pour le detail). `FER-1` (echauffement, hydratation, recuperation aux items 12, 13 et 14), `FER-2` (onze items), `FER-3`, `EPS-3` (au sens du referentiel, conduite morale - correction de `nadia-prof` a son propre abregement, le meme jour) tenues. Exactitude verifiee sur les onze items propres a la ligne. **Mention de couverture partielle, ecrite aujourd'hui, elle n'existait pas : "connaissance des regles qui entourent l'effort seule ; ce produit n'observe ni echauffement, ni boisson, ni repos reels".** Reserves neuves, non bloquantes, partagees avec `EPS-CP-10` (`EFF-R1` a `EFF-R7`, rejeu 0,5 pour le paquet entier, solde a s35), dont deux portant sur cette ligne (`EFF-R4`, `EFF-R6`) et deux sur les deux lignes a la fois (`EFF-R1`, `EFF-R7`). Story : `docs/backlog/backlog.md`, `S34-EPS-CORPS-EFFORT`. Verdict : `squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/verdicts-nadia-instance-C.md`, section 3.3. Fiche : `squad/conformite/FICHE-bandit-manchot-corps-effort-eps-cp.md` |

**Conditions opposables, posees le 2026-09-03** : `EPS-1` (un item sur la regle d'un jeu ne porte
que sur un jeu dont la regle est rappelee dans l'enonce ou universellement scolaire) ; `EPS-2`
(aucun item ne compare des corps, des performances ou des capacites entre enfants, et aucun ne
presente une performance chiffree comme une norme) ; `EPS-3` (aucun item ne fait porter une
attitude, une emotion ou une conduite morale par une bonne reponse).

**Mention exigee d'avance si une ligne bouge** : si les lignes 4, 8, 10 ou 11 passent a
`couvert-valide`, la matrice porte la mention "connaissances qui entourent la pratique physique
seules ; aucune activite physique, aucun geste et aucune performance ne sont servis par ce
produit".

**Bilan** : 11 lignes, 4 `non couvert`, 7 `non couvrable en l'etat`. 4 lignes entrent au
denominateur atteignable.

**Bilan du 2026-09-11 (s34, instance C)** : les quatre lignes visees par la mention ci-dessus
ferment dans le meme sprint - `EPS-CP-04` (regles de securite), `EPS-CP-08` (regles d'un jeu
collectif), `EPS-CP-10` et `EPS-CP-11` (lot de l'effort et du corps) passent `couvert-valide`,
chacune avec sa propre mention de couverture partielle reprise mot pour mot dans sa ligne.
**Mention d'ensemble du referentiel, due et reprise mot pour mot puisqu'une des lignes visees
bouge** : "connaissances qui entourent la pratique physique seules ; aucune activite physique,
aucun geste et aucune performance ne sont servis par ce produit". Quatre lignes vertes sur onze ne
doivent en aucun cas se lire comme une couverture partielle de l'EPS. Bilan de la matiere apres
s34 : 11 lignes, plus aucune `non couvert`, **4 `couvert-valide`** (`EPS-CP-04`, `EPS-CP-08`,
`EPS-CP-10`, `EPS-CP-11`), 7 `non couvrable en l'etat`. 4 lignes entrent au denominateur
atteignable (inchange).

## Education physique et sportive - CM1 (cycle 3)

Source : `squad/programme/education-physique-et-sportive/competences-cm1.md`, ecrit par
`nadia-prof` le 2026-09-03 (`DEC-0074`). `EPS-1` a `EPS-3` et `BIA-4` s'appliquent a l'identique.
Meme constat que le CP : la contrainte decisive est l'absence de tout mouvement recu par le
produit, huit lignes sur dix sont hors de portee definitive, toutes de nature irreversible.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| education physique et sportive | CM1 | Produire une performance optimale, mesurable a une echeance donnee | Réaliser une performance mesurée en course, en saut ou en lancer, et la comparer à ses résultats précédents. | - | - | **non couvrable en l'etat**, nature irreversible - geste moteur mesure, aucun capteur n'observe un corps ; la performance se juge par un adulte qui regarde et chronometre (`ADR-0008`). |
| education physique et sportive | CM1 | Produire une performance optimale, mesurable a une echeance donnee | Se déplacer dans l'eau sur une distance donnée en assurant sa propre sécurité. | - | - | **non couvrable en l'etat**, nature irreversible - geste moteur dans un milieu physique. |
| education physique et sportive | CM1 | Adapter ses deplacements a des environnements varies | Adapter ses déplacements à un milieu inhabituel (grimper, s'équilibrer, se déplacer en milieu naturel) en respectant les règles de sécurité. | - | - | **non couvrable en l'etat**, nature irreversible - geste moteur ; une simulation a l'ecran d'un parcours d'equilibre serait une contrefacon (`CON-1`), pas une couverture. |
| education physique et sportive | CM1 | Adapter ses deplacements a des environnements varies | Suivre un itinéraire à l'aide d'un plan ou d'une carte et y retrouver des repères. | - | - | **non couvrable en l'etat**, nature irreversible - le geste de la ligne est le deplacement, pas la lecture. Recouvrement a ne pas double-compter avec histoire-geographie CM1 (ligne 9, en attente du palier 2). |
| education physique et sportive | CM1 | S'exprimer devant les autres par une prestation artistique ou acrobatique | Composer et présenter devant les autres une courte chorégraphie ou un enchaînement acrobatique. | - | - | **non couvrable en l'etat**, nature irreversible - production corporelle libre devant des spectateurs, jugement humain (`ADR-0008`). |
| education physique et sportive | CM1 | S'exprimer devant les autres par une prestation artistique ou acrobatique | Observer la prestation d'un camarade et lui formuler un retour simple et respectueux. | - | - | **non couvrable en l'etat**, nature irreversible - production orale libre, aucune bonne reponse unique, pas d'autre enfant devant l'ecran (`EPS-3`). |
| education physique et sportive | CM1 | Conduire et maitriser un affrontement collectif ou interindividuel | Conduire un affrontement collectif : se placer, coopérer avec ses partenaires et atteindre un but commun. | - | - | **non couvrable en l'etat**, nature irreversible - se placer et cooperer se font en situation, dans une equipe qui n'existe pas ici ; contrefacon tactique refusee d'avance. |
| education physique et sportive | CM1 | Conduire et maitriser un affrontement collectif ou interindividuel | Conduire un affrontement interindividuel : adapter ses actions à celles de l'adversaire. | - | - | **non couvrable en l'etat**, nature irreversible - adapter ses actions a l'adversaire suppose un adversaire. |
| education physique et sportive | CM1 | Conduire et maitriser un affrontement collectif ou interindividuel | Tenir les rôles d'arbitre, d'observateur ou de marqueur dans un jeu, et en appliquer les règles. | - | - | non couvert - jouable sur la seule moitie connaissance, sous `EPS-1` ; la tenue du role en situation ne l'est pas. Mention exigee d'avance : "connaissance et application de la regle seules ; la tenue du role en situation reste non couverte". |
| education physique et sportive | CM1 | Apprendre a entretenir sa sante | Connaître les effets de l'activité physique sur le corps (échauffement, essoufflement, récupération, hydratation) et les règles de sécurité qui l'entourent. | - | - | non couvert - jouable au choix textuel, sous `EPS-2` et `BIA-4`. Frontiere a tenir avec les sciences (effets de l'effort, jamais l'hygiene de vie en general). |

**Mention exigee d'avance si une ligne bouge** : si les lignes 9 ou 10 passent a `couvert-valide`,
la matrice porte la mention "connaissances qui entourent la pratique physique seules ; aucune
activite physique, aucun geste et aucune performance ne sont servis par ce produit".

**Bilan** : 10 lignes, 2 `non couvert`, 8 `non couvrable en l'etat`. 2 lignes entrent au
denominateur atteignable.

## Enseignement moral et civique - CP (cycle 2)

Source : `squad/programme/enseignement-moral-et-civique/competences-cp.md`, ecrit par
`nadia-prof` le 2026-09-03 (`DEC-0074`). Memes moyens du produit. **C'est la matiere la plus
jouable des quatre matieres transverses, et c'est un risque, pas un confort** : une mecanique de
choix appliquee a l'EMC produit spontanement du catechisme civique - un item ou l'enfant coche la
reponse gentille sans que rien n'ait ete travaille. Les conditions ci-dessous sont la barre posee
avant qu'un lot ne soit ecrit.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| enseignement moral et civique | CP | Respecter autrui | Identifier et nommer les émotions d'autrui dans une situation simple (joie, tristesse, colère, peur). | bandit-manchot-emotions-autrui-cp | bandit manchot | **couvert-valide** (2026-09-07, s31, instance A) - **`EMC-CP-01` ferme.** Onze items, les quatre emotions de la parenthese chacune en bonne reponse, deux taches distinctes (inferer l'emotion d'un evenement decrit, lire un signe corporel decrit), `CAT-1` tenue sans compromis (les trois propositions sont trois noms d'emotion, jamais une attitude). Aucune mention de couverture partielle n'est due - les deux verbes du libelle, identifier et nommer, sont tous deux servis. Deux reserves non bloquantes, datees du 2026-09-07 : **`EMO-R1`** (deux enonces sur onze, 18 et 19 mots, au-dessus de la longueur des paquets d'EMC CP deja fermes) et **`EMO-R2`** (trois indices de la tache B emploient un vocabulaire au-dessus du CP - *posture*, *volontairement*, *brusques*) |
| enseignement moral et civique | CP | Respecter autrui | Distinguer ce qui est permis de ce qui est interdit dans les relations entre élèves (moquerie, insulte, violence). | bandit-manchot-permis-interdit-cp | bandit manchot | **couvert-valide** (2026-09-06, s29, instance B, apres reprise) - **`EMC-CP-02` ferme.** Refuse le 2026-09-05 (charge de lecture d'un autre niveau, item portant sa reponse dans son enonce), repris et valide sous reserve apres correction. Aucune mention de couverture partielle n'est due. Reserve non bloquante, datee du 2026-09-06 : **`PER-R1`** (question et bonne reponse ne s'enchainent pas toujours en francais correct sur les items de qualification) |
| enseignement moral et civique | CP | Respecter autrui | Reconnaître une situation de moquerie répétée ou d'exclusion et savoir à quel adulte en parler. | bandit-manchot-exclusion-cp | bandit manchot | **couvert-valide** (2026-09-08, s31, instance A, apres reprise) - **`EMC-CP-03` ferme.** Refusee le 2026-09-07 (moitie *reconnaitre* non travaillee, tache A a reponse invariante sur 6 items sur 10, `CAT-1` non tenue sur les deux taches) ; **valide sous reserve au reaudit** apres reecriture - la tache A devient un classement a deux categories (moquerie repetee / accident isole) et `CAT-1` verifiee tenue a l'ecran manche par manche sur les deux taches. `EMC-3` relue et corrigee par `nadia-prof` elle-meme au reaudit : c'est une interdiction sur les propositions, jamais une prescription figeant la chaine "en parler a un adulte". Aucune mention de couverture partielle n'est due. Cinq reserves non bloquantes, neuves et datees du 2026-09-08 : **`EXC-R1`** (la consigne du catalogue nomme encore l'ancienne tache), **`EXC-R2`** (les indices de la tache B ne font que redire le lieu deja donne dans l'enonce), **`EXC-R3`** (la troisieme proposition de la tache A n'est pas un candidat serieux sur trois items sur six), **`EXC-R4`** (deux enonces de la tache B en une seule proposition principale restent a raccourcir), **`EXC-R5`** (aucun item ne fait d'un adulte reel une reponse fausse) |
| enseignement moral et civique | CP | Respecter autrui | Reconnaître que les personnes sont différentes et qu'aucune différence ne justifie de traiter quelqu'un moins bien. | - | - | non couvert - jouable au choix, sous `EMC-4`, `BIA-1` et `BIA-2`, seulement sous la forme de l'interdit, jamais sous celle de l'enumeration des differences (contrefacon a stereotype). **Non retenue a s31** (`DEC-0081`, section 7 ; `nadia-prof`, refus d'avance n°1 de son arbitrage prealable de s31) : `EMC-4` n'autorise qu'une seule tache possible (l'interdit), ce qui epuise `FER-3` avant qu'un item ne soit ecrit - aucun lot de s31 n'y depense d'item. |
| enseignement moral et civique | CP | Respecter autrui | Employer les formules de politesse et de respect dans les échanges (saluer, remercier, demander, écouter sans couper). | bandit-manchot-formules-de-politesse-cp | bandit manchot | **couvert-valide** (2026-09-07, s31, instance A) - **`EMC-CP-05` ferme.** Onze items, les quatre elements de la parenthese chacun en bonne reponse, deux taches distinctes (choisir la formule qui convient, dire quelle regle d'echange un dialogue ne respecte pas), `CAT-1` tenue sans compromis, `FAB-1` sur ses trois branches. **Mention de couverture partielle exigee d'avance et reprise mot pour mot : "connaissance des formules seule ; leur emploi en situation n'est pas servi. Ce produit verifie ce qu'un enfant sait, jamais ce qu'il fait."** Deux reserves non bloquantes, datees du 2026-09-07 : **`POL-R1`** (les cinq enonces de la tache B, 20 a 26 mots, au-dessus de ce qui est deja accepte au CP) et **`POL-R2`** (l'enonce de l'item 6 est incoherent - un verre deja vide dont on demande "un peu plus d'eau") |
| enseignement moral et civique | CP | Acquerir et partager les valeurs de la Republique | Connaître les règles de vie de la classe et de l'école et dire à quoi elles servent. | bandit-manchot-regles-de-vie-cp | bandit manchot | **couvert-valide** (2026-09-05, s29, instance B) - **`EMC-CP-06` ferme.** `FER-1` : les deux moities du libelle (connaitre les regles, dire a quoi elles servent) chacune en bonne reponse. `FER-2` : 11 items. `FER-3` : deux operations mentales distinctes. Exactitude 11 sur 11. **Mention de couverture partielle exigee d'avance et reprise mot pour mot : "connaissance de la regle et de ce a quoi elle sert seule ; sa mise en oeuvre en situation n'est pas servie. Ce produit verifie ce qu'un enfant sait, jamais ce qu'il fait."** Reserve non bloquante, datee du 2026-09-05 : **`REG-R1`** (trois distracteurs hors registre, reduisant l'item a un choix entre deux) |
| enseignement moral et civique | CP | Acquerir et partager les valeurs de la Republique | Distinguer une règle qui vaut pour tous d'un simple souhait, et reconnaître qu'une loi s'impose à tous. | bandit-manchot-regle-souhait-loi-cp | bandit manchot | **couvert-valide** (2026-09-11, s34, instance B, cinquieme passage) - **`EMC-CP-07` ferme.** Trois modes de defaillance nommes d'avance par `nadia-prof`, tous evites, verifies a l'ecran : `FD-1` (l'item transpose credite desormais "tous les enfants concernes, meme pour un trajet tres court" contre deux propositions restrictives, sans que le mot *adulte* figure sur aucun des trois boutons) ; `FD-2` (les items 5 et 6 ne portent plus qu'une seule loi chacun) ; `FD-3` (`FAB-1c` recomptee sur six items, un item fautif sur six, sous le seuil de deux). Aucune mention de couverture partielle n'est due. **Deux reserves neuves, non bloquantes, datees du 2026-09-11** : `RSL-R1` (l'item transpose est le seul des six a ne nommer aucune source de droit) et `RSL-R2` (le mot *concerne* de l'item transpose est au-dessus du vocabulaire de CP). Story : `docs/backlog/backlog.md`, `S34-EMCCP07`. Verdict : `squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/verdicts-nadia-instance-B.md`, section 2. Fiche : `squad/conformite/FICHE-bandit-manchot-regle-souhait-loi-cp.md`. |
| enseignement moral et civique | CP | Acquerir et partager les valeurs de la Republique | Reconnaître et nommer les symboles de la République française (drapeau tricolore, Marianne, La Marseillaise, la fête nationale). | - | - | non couvert - jouable au choix textuel, sous `ART-1`. La reponse ne se porte jamais par une couleur percue ; on demande le nom, l'hymne, la devise, la date. |
| enseignement moral et civique | CP | Acquerir et partager les valeurs de la Republique | Connaître la devise de la République et donner un exemple concret de liberté, d'égalité et de fraternité. | bandit-manchot-devise-cp | bandit manchot | **couvert-valide** (2026-09-23, s40) - **`EMC-CP-09` ferme.** Done pedagogique **VALIDE SOUS RESERVE**, prononce par `nadia-prof` apres avoir joue le jar servi (`squad/conformite/FICHE-bandit-manchot-devise-cp.md`). Onze items, la devise restituee exactement et les dix exemples chacun rattaches sans ambiguite a une seule des trois valeurs, `EMC-5` tenue item par item, verifiee specifiquement sur les trois items d'egalite - les plus exposes au risque de confusion avec la fraternite. Aucune mention de couverture partielle n'est due. **Reserve non bloquante, datee du 2026-09-23 : `DVS-CP-R1`** (le verbe *illustre*, employe dans dix des onze enonces, est d'un registre plus soutenu que le vocabulaire habituel d'un enonce de CP dans ce depot ; correction demandee, non bloquante : le remplacer par un verbe concret deja rencontre en CP, par exemple *montre*). |
| enseignement moral et civique | CP | Acquerir et partager les valeurs de la Republique | Identifier les principaux droits de l'enfant (être protégé, être soigné, aller à l'école, jouer). | - | - | non couvert - jouable au choix. Connaissance exacte, terrain sur ; `BIA-4` s'applique (aucun item n'interroge l'enfant sur sa propre situation). |
| enseignement moral et civique | CP | Construire une culture civique | Participer à une décision collective par un vote et accepter le résultat du vote. | - | - | **non couvrable en l'etat**, nature irreversible - un vote sans collectif n'est pas un vote ; faire passer un clic pour un vote serait une contrefacon (`CON-1`). |
| enseignement moral et civique | CP | Construire une culture civique | Prendre part à un échange réglé sur une question simple : donner son avis, écouter celui des autres, en changer si on est convaincu. | - | - | **non couvrable en l'etat**, nature irreversible - production libre argumentee, jugement humain (`ADR-0008`) ; un debat suppose un interlocuteur et une parole tenue. |
| enseignement moral et civique | CP | Construire une culture civique | Identifier quelques rôles et services de l'école et de la commune (directeur, mairie, pompiers, médecin). | - | - | non couvert - jouable au choix, sous `EMC-6`. Aucun item ne suppose la commune de l'enfant (le produit ne la connait pas et ne peut pas la connaitre, `DEC-0028`). |
| enseignement moral et civique | CP | Construire une culture civique | Connaître les gestes d'alerte les plus simples et savoir donner l'alerte (prévenir un adulte, numéro d'urgence). | - | - | non couvert - jouable au choix, sous `EMC-7`. Piege d'exactitude pose d'avance : le numero d'urgence en cas d'incendie admet deux reponses defendables (18, 112). |

**Conditions opposables, posees le 2026-09-03** : `EMC-1` (aucun item n'interroge l'enfant sur ce
que lui ressent, vit, croit ou fait chez lui - extension de `BIA-4`) ; `EMC-2` (un item sur une
emotion ne retient que les situations a emotion unique et evidente) ; `EMC-3` (sur la ligne 3, la
bonne reponse est toujours "en parler a un adulte", jamais une solution que l'enfant appliquerait
seul, et aucun item ne detaille une violence) ; `EMC-4` (sur la ligne 4, les items portent sur
l'interdit de traiter quelqu'un moins bien, jamais sur la description des groupes de personnes) ;
`EMC-5` (aucun item n'a deux reponses defendables, extension de `HIS-2`) ; `EMC-6` (aucun item ne
presuppose un lieu de vie, une commune, un type d'ecole ou une configuration familiale) ; `EMC-7`
(les items sur les numeros d'urgence sont exacts et sans ambiguite, source dans le commentaire du
paquet).

**Mention exigee d'avance si une ligne bouge** : les lignes 5 et 6 sont des moities de competence ;
si elles passent a `couvert-valide`, la matrice porte la mention "connaissance de la regle et de la
formule seule ; leur mise en oeuvre en situation n'est pas servie". Aucune ligne d'EMC ne doit etre
presentee comme attestant une conduite.

**Bilan, corrige le 2026-09-06 (`camille-po`, s29)** : 14 lignes, 10 `non couvert`, 2
`couvert-valide` (`EMC-CP-02`, `EMC-CP-06`), 2 `non couvrable en l'etat`. 12 lignes entrent au
denominateur atteignable (inchange).

**Bilan, corrige le 2026-09-08 (`camille-po`, s31) - trois lignes de plus ferment** : 14 lignes,
7 `non couvert` (dont `EMC-CP-07`, refuse et maintenu au reaudit, et `EMC-CP-04`, non retenue a
s31), **5 `couvert-valide`** (`EMC-CP-01`, `EMC-CP-02`, `EMC-CP-05`, `EMC-CP-06`, `EMC-CP-03`),
2 `non couvrable en l'etat`. 12 lignes entrent au denominateur atteignable (inchange).

**Bilan du 2026-09-11 (s34, instance B, cinquieme passage)** : `EMC-CP-07` ferme, les trois modes
de defaillance nommes d'avance par `nadia-prof` etant tous evites. Bilan de la matiere apres
s34 : 14 lignes, 6 `non couvert` (`EMC-CP-04` non retenue a s31 comprise), **6 `couvert-valide`**
(`EMC-CP-01`, `EMC-CP-02`, `EMC-CP-03`, `EMC-CP-05`, `EMC-CP-06`, `EMC-CP-07`), 2 `non couvrable
en l'etat`. 12 lignes entrent au denominateur atteignable (inchange).

## Enseignement moral et civique - CM1 (cycle 3)

Source : `squad/programme/enseignement-moral-et-civique/competences-cm1.md`, ecrit par
`nadia-prof` le 2026-09-03 (`DEC-0074`). `EMC-1` a `EMC-7` s'appliquent a l'identique, `EMC-8` et
`EMC-9` s'y ajoutent. `ART-1` s'applique a la ligne des symboles ; `HIS-2` et `HIS-3` s'appliquent
(faits institutionnels dont `nadia-prof` n'est pas la source). **Onze lignes sur douze sont
jouables, et c'est precisement ce qui est dangereux** : le `bandit-manchot` produit spontanement
l'item dont la bonne reponse se devine par la seule politesse, que `EMC-8` refuse.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| enseignement moral et civique | CM1 | Respecter autrui | Identifier et nommer l'émotion d'une personne dans une situation donnée, et distinguer ce que l'on ressent de ce que l'on fait. | - | - | non couvert - jouable au choix, sous `EMC-1` et `EMC-2`. Risque propre : deux emotions souvent defendables (`HIS-2` transpose) ; la seconde moitie (ressentir / agir) se pose tres bien en trois propositions. |
| enseignement moral et civique | CM1 | Respecter autrui | Reconnaître ce qui porte atteinte à la personne d'autrui (moquerie, insulte, mise à l'écart, harcèlement) et savoir à qui s'adresser. | - | - | non couvert - jouable au choix, sous `EMC-3` et `EMC-9`. Ligne delicate : `EMC-9` interdit de laisser croire a l'enfant qu'il vient d'alerter quelqu'un. |
| enseignement moral et civique | CM1 | Respecter autrui | Identifier ce qu'interdit le respect d'autrui dans ses différences (apparence, origine, convictions, situation de handicap). | - | - | non couvert - jouable au choix, sous `EMC-4`, `BIA-1` et `BIA-2`, seulement sous la forme de l'interdit, jamais sous celle de l'enumeration des differences. |
| enseignement moral et civique | CM1 | Respecter autrui | Connaître les règles de la vie collective à l'école et dire à quoi elles servent. | bandit-manchot-regles-vie-collective-cm1 | bandit manchot | **couvert-valide** (2026-09-23, s40) - **`EMC-CM1-04` ferme.** Done pedagogique **VALIDE, sans reserve**, prononce par `nadia-prof` apres avoir joue le jar servi (`squad/conformite/FICHE-bandit-manchot-regles-vie-collective-cm1.md`). Onze items, deux taches distinctes (ce qu'il faut faire, pourquoi la regle existe), la moitie "a quoi elles servent" verifiee item par item comme portant une raison de fond et jamais une reformulation de la regle elle-meme - le risque de reglement recite explicitement leve. `EMC-1`, `EMC-6`, `EMC-8`, `EMC-10` tenues. **Mention exigee d'avance et reprise mot pour mot : "connaissances civiques et reconnaissance de situations seules ; aucun comportement, aucun engagement et aucune participation a une decision collective ne sont servis par ce produit."** |
| enseignement moral et civique | CM1 | Acquerir et partager les valeurs de la Republique | Reconnaître les symboles de la République française et dire ce qu'ils représentent (drapeau, devise, Marianne, hymne, fête nationale). | - | - | non couvert - jouable au choix textuel, sous `ART-1`. La reponse ne se porte jamais par une couleur percue. |
| enseignement moral et civique | CM1 | Acquerir et partager les valeurs de la Republique | Comprendre le sens des principes de la République : liberté, égalité, fraternité, laïcité, refus des discriminations. | - | - | non couvert - jouable au choix, ligne la plus dangereuse de la matiere. `EMC-4`, `EMC-5` et `BIA-5` mordent tous les trois ; la laicite se definit comme ce que l'ecole garantit, jamais comme un interdit visant un groupe. |
| enseignement moral et civique | CM1 | Acquerir et partager les valeurs de la Republique | Distinguer un droit d'un devoir, et connaître quelques droits et devoirs de l'élève et de l'enfant. | bandit-manchot-droit-devoir-cm1 | bandit manchot | **couvert-valide** (2026-09-06, s29, instance B, apres reprise) - **`EMC-CM1-07` ferme.** Refuse le 2026-09-05 (un item a deux reponses defendables sur l'axe meme de la ligne), repris et valide sous reserve apres remplacement de l'item. Sources verifiees une par une contre la Convention internationale des droits de l'enfant (`HIS-3`). **Mention exigee d'avance sur toute ligne d'EMC CM1 qui bouge (note posee le 2026-09-03, reprise mot pour mot faute de verdict specifique la levant) : "connaissances civiques et reconnaissance de situations seules ; aucun comportement, aucun engagement et aucune participation a une decision collective ne sont servis par ce produit."** Trois reserves non bloquantes, datees du 2026-09-06 : **`DRO-R1`** (trois items donnent la reponse d'un autre item du meme paquet), **`DRO-R2`** (un indice nomme la categorie au lieu du critere sur un item binaire) et **`DRO-R3`** (constat de serie : l'adulte nomme est une femme dans les quatre occurrences des paquets d'EMC de s29) |
| enseignement moral et civique | CM1 | Acquerir et partager les valeurs de la Republique | Distinguer une règle d'une loi, et identifier ce que la loi autorise, interdit et protège. | bandit-manchot-regle-loi-cm1 | bandit manchot | **couvert-valide** (2026-09-23, s40, second audit apres correction) - **`EMC-CM1-08` ferme.** **Refuse au premier audit** le 2026-09-23 (sur les cinq items de la tache B, "que fait cette loi ?", le verbe de reponse attendu - autorise / interdit / protege - etait deja ecrit dans l'enonce lui-meme, sur 5 items sur 5 : la tache devenait une copie du mot deja lu, pas une identification) ; **corrige par `marc-dev`** (les cinq enonces reecrits pour que le mot-reponse n'apparaisse plus) et **valide au second audit** le meme jour, apres avoir rejoue le jar reconstruit (`squad/conformite/FICHE-bandit-manchot-regle-loi-cm1.md`). La tache A (regle ou loi, six exemples a portee explicite) et la tache B (que fait la loi, cinq faits de droit exacts et desormais sans fuite du mot-reponse) sont toutes deux tenues ; un risque neuf ouvert par la reformulation (confusion "protege"/"autorise" entre les items 7 et 11) a ete controle specifiquement et leve. **Mention exigee d'avance et reprise mot pour mot : "connaissances civiques et reconnaissance de situations seules ; aucun comportement, aucun engagement et aucune participation a une decision collective ne sont servis par ce produit."** |
| enseignement moral et civique | CM1 | Construire une culture civique | Identifier le rôle de quelques institutions proches (mairie, conseil municipal, école) et à qui s'adresser selon la situation. | - | - | non couvert - jouable au choix, sous `EMC-6`. Aucun item ne suppose la commune de l'enfant. |
| enseignement moral et civique | CM1 | Construire une culture civique | Comprendre le sens du vote et de la représentation (délégué de classe, élection, suffrage universel). | - | - | non couvert - jouable au choix, sur la connaissance seule. Point de coherence avec le CP : la ligne 11 de CP reste `non couvrable en l'etat` (participer a une decision collective), celle-ci porte la connaissance que le programme du cycle 3 rend attendu propre. Mention exigee d'avance : "connaissance du vote et de la representation seule ; participer a une decision collective reste non couvrable". |
| enseignement moral et civique | CM1 | Construire une culture civique | Distinguer un fait d'une opinion, et vérifier l'origine d'une information avant de la partager. | bandit-manchot-fait-opinion-cm1 | bandit manchot | **couvert-valide** (2026-09-06, s29, instance B, apres reprise) - **`EMC-CM1-11` ferme.** Refuse le 2026-09-05 (tache non ecrite dans la consigne sur six items, une troisieme proposition melangeant deux axes), repris et valide sous reserve apres correction de la consigne et retrait de la troisieme proposition. **Mention exigee d'avance sur toute ligne d'EMC CM1 qui bouge (note posee le 2026-09-03, reprise mot pour mot faute de verdict specifique la levant) : "connaissances civiques et reconnaissance de situations seules ; aucun comportement, aucun engagement et aucune participation a une decision collective ne sont servis par ce produit."** Trois reserves non bloquantes, datees du 2026-09-06 : **`FOP-R1`** (la consigne neuve decrit une tache que cinq items sur onze ne demandent pas), **`FOP-R2`** (le catechisme civique est reduit, pas supprime : cinq distracteurs sur dix sont encore reconnaissables a leur seule deraison, quand le paquet en declare dix) et **`FOP-R3`** (sur un item, la question demande l'origine et la reponse donne la verite) |
| enseignement moral et civique | CM1 | Construire une culture civique | S'engager dans un projet collectif de la classe ou de l'école et y tenir son rôle. | - | - | **non couvrable en l'etat**, nature irreversible - engagement reel dans un collectif reel, juge par un adulte qui l'observe dans la duree (`ADR-0008`) ; `EMC-8` interdit par ailleurs la contrefacon evidente ("acceptes-tu d'aider ton groupe ?"). |

**Conditions opposables, posees le 2026-09-03** : `EMC-8` (aucun item ne demande a l'enfant de
declarer un comportement - la forme "que fais-tu si...", "acceptes-tu de..." est refusee) ; `EMC-9`
(sur la ligne 2, aucun item ne laisse croire a l'enfant qu'il vient d'alerter quelqu'un - le produit
ne recueille rien, ne transmet rien, ne conserve rien, `DEC-0028`).

**Mention exigee d'avance si une ligne bouge** : "connaissances civiques et reconnaissance de
situations seules ; aucun comportement, aucun engagement et aucune participation a une decision
collective ne sont servis par ce produit".

**Bilan, corrige le 2026-09-06 (`camille-po`, s29)** : 12 lignes, 9 `non couvert`, 2
`couvert-valide` (`EMC-CM1-07`, `EMC-CM1-11`), 1 `non couvrable en l'etat`. 11 lignes entrent au
denominateur atteignable (inchange).

## Histoire des arts - CM1 (cycle 3)

Source : `squad/programme/histoire-des-arts/competences-cm1.md`, ecrit par `nadia-prof` le
2026-09-03 (`DEC-0074`). **L'histoire des arts est un enseignement a part entiere du cycle 3,
distinct des arts plastiques et de l'education musicale : c'est un chapitre de programme portant
ses propres attendus de fin de cycle**, sans avoir d'horaire propre (critere de comptage arrete par
`nadia-prof` le 2026-09-02, voir la note en tete de ce fichier). **Elle n'existe pas au cycle 2** :
aucune ligne CP. `ART-1` a `ART-5` (poses sur les arts plastiques le meme jour) s'appliquent a
l'identique ; `HIS-2` et `HIS-3` s'appliquent (`nadia-prof` n'est pas la source des faits
d'histoire de l'art).

**Sept lignes sur dix entrent au denominateur, mais six d'entre elles exigent une image d'oeuvre
que le depot ne sert pas - meme question de droits d'usage, non tranchee, ouverte en `DEC-0075`.**
Une seule (ligne 5) est jouable des aujourd'hui, sans rien construire de neuf.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| histoire des arts | CM1 | Decrire une oeuvre et en degager les caracteristiques | Décrire une œuvre d'art en employant un lexique simple et adapté (support, format, matière, couleur, sujet). | - | - | non couvert - palier 1 illustre, image d'oeuvre requise (`DEC-0075`). Un item textuel qui decrirait l'oeuvre dans son enonce serait une contrefacon (`ART-4`, `CON-1`). |
| histoire des arts | CM1 | Decrire une oeuvre et en degager les caracteristiques | Dégager d'une œuvre observée ses principales caractéristiques techniques et formelles. | - | - | non couvert - meme motif que la ligne precedente (`DEC-0075`). Mention exigee d'avance si la ligne bouge : "oeuvres visuelles seules ; l'oeuvre ecoutee reste non couvrable, faute de son". |
| histoire des arts | CM1 | Decrire une oeuvre et en degager les caracteristiques | Identifier les matériaux d'une œuvre et la manière dont l'artiste leur a donné forme. | - | - | non couvert - meme motif (`DEC-0075`). Risque propre : le nom de l'oeuvre porte souvent le materiau ("la statue de bronze") ; `ART-4` mord exactement la (propositions masquees). |
| histoire des arts | CM1 | Situer une oeuvre dans le temps et dans son contexte | Associer une œuvre à une époque et à une civilisation à partir d'éléments observés. | - | - | non couvert - meme motif (`DEC-0075`). Meilleure forme d'item de la matiere : l'association a des distracteurs naturels et plausibles. Sans image, l'item deviendrait un quiz de titres. |
| histoire des arts | CM1 | Situer une oeuvre dans le temps et dans son contexte | Relier les caractéristiques d'une œuvre d'art à des usages et au contexte historique et culturel de sa création. | bandit-manchot-histoire-des-arts-relier-contexte-cm1 | bandit manchot | **couvert-valide** (2026-09-23, s40) - **`HDA-CM1-05` ferme. Premiere ligne d'histoire des arts du produit.** Done pedagogique **VALIDE, sans reserve**, prononce par `nadia-prof` apres avoir joue le jar servi (`squad/conformite/FICHE-bandit-manchot-histoire-des-arts-relier-contexte-cm1.md`). Onze items, six styles (gothique, roman, temple grec, temple romain, art funeraire egyptien, chateau fort), trois taches (epoque, civilisation, usage) ; `HDA-1` a `HDA-4` tenues, `HIS-2` verifiee sans deuxieme lecture defendable - la barre critique de la ligne -, le distracteur temple grec / temple romain ecarte par un marqueur specifique (langue de l'inscription, dieux nommes) plutot que par les seules colonnes. **Mention exigee d'avance et reprise mot pour mot : "reconnaissance et situation d'oeuvres seules ; aucun avis argumente ni aucune frequentation d'un lieu d'art n'est servi par ce produit."** |
| histoire des arts | CM1 | Situer une oeuvre dans le temps et dans son contexte | Identifier quelques œuvres et quelques repères majeurs du patrimoine, et les situer sur une frise. | - | - | non couvert - palier 1 illustre, sur la moitie identification seulement (`DEC-0075`). Mention exigee d'avance : "identification et ordre entre deux oeuvres seuls ; le placement sur une frise reste non couvert" - placer sur une frise est du palier 2. |
| histoire des arts | CM1 | Donner un avis argumente | Donner un avis argumenté sur ce qu'une œuvre représente ou exprime. | - | - | **non couvrable en l'etat**, nature irreversible - jugement humain sur une production libre (`ADR-0008`) ; argumenter est une production, et la question n'a pas de bonne reponse unique (`ART-2`). |
| histoire des arts | CM1 | Donner un avis argumente | Proposer une interprétation simple d'une œuvre et en discuter avec d'autres. | - | - | **non couvrable en l'etat**, nature irreversible - memes deux motifs, plus l'echange oral avec d'autres que rien de ce produit ne fournit. |
| histoire des arts | CM1 | Frequenter les lieux d'art et le patrimoine | Se repérer dans un musée, un lieu d'art ou un site patrimonial, et en respecter les règles. | - | - | non couvert - palier 2 (designer un lieu sur un plan), refuse au palier 1 par `V1`, constructible (ADR de `theo-archi` en s28) ; jamais `non couvrable en l'etat`. Contrefacon refusee d'avance : un quiz "que fait-on dans un musee ?" ne sert pas le reperage. |
| histoire des arts | CM1 | Frequenter les lieux d'art et le patrimoine | Rendre compte de la visite d'un lieu de conservation ou de la rencontre avec un métier du patrimoine. | - | - | **non couvrable en l'etat**, nature irreversible - compte rendu libre (`ADR-0008`), et la ligne suppose une visite reelle que ce produit ne fournit pas et ne remplace pas. |

**Mention exigee d'avance si une ligne bouge** : "reconnaissance et situation d'oeuvres seules ;
aucun avis argumente ni aucune frequentation d'un lieu d'art n'est servi par ce produit".

**Bilan** : 10 lignes, 7 `non couvert`, 3 `non couvrable en l'etat`. 7 lignes entrent au
denominateur atteignable.

## Sciences et technologie - CM1 (cycle 3)

Source : `squad/programme/sciences-et-technologie/competences-cm1.md`, ecrit par `nadia-prof` le
2026-09-03 (`DEC-0074`). *Sciences et technologie* est une matiere de cycle 3 issue de la scission
de *questionner le monde* avec l'histoire-geographie a la sortie du cycle 2 : aucune ligne CP
n'existe ni n'existera pour cette matiere, et reciproquement aucune ligne CM1 n'existe pour
*questionner le monde*. `BIA-2`, `BIA-3`, `BIA-4`, `HIS-3` et `CON-1` s'appliquent a l'identique.
**C'est la matiere la plus immediatement servable des dix, et de tres loin** : dix-neuf lignes sur
vingt sont des connaissances ou des raisonnements courts, jouables par le `bandit-manchot` sans
aucun mecanisme neuf. Une seule tombe, la demarche d'investigation elle-meme.

**Correction du 2026-09-06 (`camille-po`), avant l'ouverture de s30, sur signalement de
`nadia-prof`** (`squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-
prix-des-quinze-reserves.md`, section 1.6). Cette matrice portait encore, sur les deux lignes
"regimes alimentaires" et "grandes fonctions" ci-dessous, les deux libelles faux que le referentiel
avait corriges depuis le 2026-09-05 (`DEC-0077`, section 8.7) sans qu'elle le soit elle-meme :
*"vegetarien"* a la place d'*"herbivore"*, et *"nutrition"* rangee a tort parmi ses propres
composantes. Corrige ici au mot pres sur le referentiel : `FER-1` fait de chaque parenthese une
liste contractuelle, et un lot ecrit sur les anciens libelles aurait ete faux en obeissant au
document de pilotage.

**Codes de ligne alloues le 2026-09-06 (`camille-po`, `DEC-0079`), conformement a `ADR-0081`.** Ce
referentiel n'etait pas encore code : aucun paquet ne le citait avant s30. `SCI-CM1-01` a
`SCI-CM1-20` sont desormais ecrits en tete de chaque puce de
`squad/programme/sciences-et-technologie/competences-cm1.md`, dans l'ordre de lecture des vingt
lignes ci-dessous - memes rangs qu'au referentiel et qu'au jugement de faisabilite de `nadia-prof`.
Aucun intitule n'est modifie par cette allocation. `nadia-prof` ne prononce aucun Done pedagogique
sur une ligne de ce lot dont le code n'est pas cite par le paquet qui la sert.

| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| sciences et technologie | CM1 | Matiere, mouvement, energie, information | Décrire les états de la matière et les changements d'état (solide, liquide, gaz ; fusion, solidification, vaporisation, condensation). | bandit-manchot-etats-matiere-cm1 | bandit manchot | **couvert-valide** (2026-09-07, s31, instance B) - **`SCI-CM1-01` ferme.** Onze items exacts, les trois etats et les quatre changements d'etat chacun en bonne reponse, deux taches distinctes (identifier l'etat decrit, nommer le changement d'etat decrit). **Seul des quatre paquets de s31 ou chaque proposition est un candidat serieux** ; `SCI-1` tenue a son point le plus exigeant. Referme la couture ouverte par `MEL-R1` : aucun item n'ecrit que l'eau disparait. Aucune mention de couverture partielle n'est due. Aucune reserve. |
| sciences et technologie | CM1 | Matiere, mouvement, energie, information | Distinguer un mélange homogène d'un mélange hétérogène et identifier un moyen de séparer les constituants d'un mélange. | bandit-manchot-melanges-cm1 | bandit manchot | **couvert-valide** (2026-09-06, s30, instance B) - **`SCI-CM1-02` ferme.** Six classements exacts, cinq moyens de separation reels et bien apparies, une vraie progression de difficulte, indices portant le critere et non la categorie sur les cinq items binaires (`DRO-R2`). Aucune mention de couverture partielle n'est due (`nadia-prof` le dit explicitement, hors des lignes 6, 14 et 20). Trois reserves non bloquantes, datees du 2026-09-06 : **`MEL-R1`** (l'indice du sel dit "faire disparaitre l'eau", plante une conception fausse et nomme la reponse), **`MEL-R2`** (l'item de la decantation ecrit sa reponse dans sa question) et **`MEL-R3`** (troisieme proposition non plausible, meme defaut que `SIG-R2`). **Reaudit du 2026-09-07 (s31, instance A)** : `MEL-R1`, `MEL-R2` et `MEL-R3` **levees** (l'indice du sel dit desormais que le sel reste dans l'eau sans la faire disparaitre, l'item de decantation ne redit plus sa reponse dans son enonce, la troisieme proposition de l'item eau/huile est devenue un candidat plausible). Une reserve neuve et datee, non bloquante, ouverte le 2026-09-07 : **`MEL-R4`** (l'enonce de l'item de decantation reste resolvable a la duree et a l'absence de manipulation seules, sans savoir ce qu'est une decantation). Statut inchange, `SCI-CM1-02` reste `couvert-valide`.
| sciences et technologie | CM1 | Matiere, mouvement, energie, information | Décrire le mouvement d'un objet par sa trajectoire et par sa vitesse, par rapport à un point de repère donné. | - | - | non couvert - jouable au choix textuel, sur la description seule (le produit ne montre aucun mouvement). Recouvrement a ne pas double-compter avec mathematiques CM1 / Grandeurs et mesures (le calcul d'une vitesse). |
| sciences et technologie | CM1 | Matiere, mouvement, energie, information | Identifier des sources et des formes d'énergie, et un exemple de conversion d'une forme à une autre. | bandit-manchot-energie-cm1 | bandit manchot | **couvert-valide** (2026-09-22, s39) - **`SCI-CM1-04` ferme.** Done pedagogique **VALIDE, sans reserve**, prononce par `nadia-prof` apres avoir joue le jar servi (`squad/conformite/FICHE-bandit-manchot-energie-cm1.md`). 12 items exacts (sources, formes, conversions), `FER-1` a `FER-3` tenues, refus 3 de l'arbitrage prealable (electricite jamais bonne reponse a "source ou forme ?") respecte. |
| sciences et technologie | CM1 | Matiere, mouvement, energie, information | Identifier un signal et une information dans quelques objets du quotidien (feu tricolore, panneau, alarme, écran). | bandit-manchot-signal-information-cm1 | bandit manchot | **couvert-valide** (2026-09-06, s30, instance B) - **`SCI-CM1-05` ferme.** Onze items exacts, deux taches reellement distinctes (classer le signal, decoder l'information qu'il porte), aucun fait faux, aucun biais, `SCI-1` et `SCI-4` tenues, `SCI-3` mesuree sur le lot (zero item numerique sur 22). Aucune mention de couverture partielle n'est due (`nadia-prof` le dit explicitement, hors des lignes 6, 14 et 20). Deux reserves non bloquantes, datees du 2026-09-06 : **`SIG-R1`** (la tache de classement ne monte jamais, cinq items sur six donnent leur reponse dans l'enonce) et **`SIG-R2`** (troisieme proposition de l'item de l'ambulance non plausible, `SCI-4` formellement tenue mais sans effet reel) |
| sciences et technologie | CM1 | Le vivant, sa diversite et les fonctions qui le caracterisent | Classer des êtres vivants d'après un caractère commun observable, et repérer la diversité des espèces d'un milieu. | - | - | non couvert - jouable au choix textuel, item par item. Mention exigee d'avance : "raisonnement sur un caractere, item par item ; le tri d'une collection entiere reste non couvert" (refuse au palier 1 par `V3`). |
| sciences et technologie | CM1 | Le vivant, sa diversite et les fonctions qui le caracterisent | Identifier les besoins des êtres vivants pour se nourrir et distinguer les régimes alimentaires (herbivore, carnivore, omnivore). | bandit-manchot-regimes-alimentaires-cm1 | bandit manchot | **couvert-valide** (2026-09-07, s31, instance B) - **`SCI-CM1-07` ferme.** Sept animaux distincts, les trois regimes (herbivore, carnivore, omnivore) chacun en bonne reponse - jamais *vegetarien*, corrige au referentiel a s30. Deux taches distinctes, la tache A portant `FER-1` a elle seule. Aucune mention de couverture partielle n'est due. Deux reserves non bloquantes, datees du 2026-09-07 : **`RGA-R1`** (sur les quatre items de la tache B, la bonne reponse est la seule proposition serieuse, `PLA-1`) et **`RGA-R2`** (deux formulations hors du registre du CM1 - *esthetique*, et une discrimination qui ne tient qu'a l'adverbe *seulement*) |
| sciences et technologie | CM1 | Le vivant, sa diversite et les fonctions qui le caracterisent | Ordonner les étapes du développement d'un être vivant (naissance, croissance, reproduction, vieillissement, mort). | bandit-manchot-etapes-developpement-cm1 | bandit manchot | **couvert-valide** (2026-09-08, s31, instance B, apres reprise) - **`SCI-CM1-08` ferme.** Refusee le 2026-09-07 (un fait faux - *"un jeune arbre a fini de grandir"* -, une auto-contradiction entre les items 3 et 8, deux items clones sous deux noms d'animal) ; **valide sous reserve au reaudit** apres correction - les trois motifs sont payes. Six items, chacun comparant deux etapes, les cinq etapes couvertes ; `CYC-1` verifiee contre `cp-qlm-cycle-de-vie`, progression sans contradiction. Aucune mention de couverture partielle n'est due (barre posee d'avance : ordonner deux etapes se pose en texte). Deux reserves non bloquantes, neuves et datees du 2026-09-08 : **`DEV-R1`** (deux items enseignent le meme fait sous deux noms d'animal, le garde-fou neuf ne les distingue pas) et **`DEV-R2`** (sur l'item de l'arbre, *la croissance* est rangee en reponse fausse alors qu'un arbre continue de grandir) |
| sciences et technologie | CM1 | Le vivant, sa diversite et les fonctions qui le caracterisent | Nommer les grandes fonctions de nutrition du corps humain (digestion, respiration, circulation) et l'organe principal de chacune. | bandit-manchot-nutrition-cm1 | bandit manchot | **couvert-valide** (2026-09-23, s40) - **`SCI-CM1-09` ferme.** Done pedagogique **VALIDE, sans reserve**, prononce par `nadia-prof` apres avoir joue le jar servi (`squad/conformite/FICHE-bandit-manchot-nutrition-cm1.md`). Onze items, trois associations (digestion-estomac, respiration-poumons, circulation-coeur) chacune verifiee dans les deux sens, deux taches distinctes (organe a partir de la fonction, fonction a partir de l'organe). Conforme a l'arbitrage prealable (s32, section 17.2) : designer un organe sur un schema serait du palier 2, non exige - la ligne ferme en texte seul. Aucune mention de couverture partielle n'est due. |
| sciences et technologie | CM1 | Le vivant, sa diversite et les fonctions qui le caracterisent | Relier l'alimentation, l'activité physique, le sommeil et l'hygiène au bon fonctionnement du corps. | - | - | non couvert - jouable au choix textuel, sous `BIA-4`. Aucun item n'interroge l'enfant sur sa propre vie. |
| sciences et technologie | CM1 | Materiaux et objets techniques | Identifier les principales familles de matériaux et choisir un matériau d'après une propriété (résistance, isolation, souplesse, imperméabilité). | bandit-manchot-familles-materiaux-cm1 | bandit manchot | **couvert-valide** (2026-09-08, s31, instance B, apres reprise) - **`SCI-CM1-11` ferme.** Refusee le 2026-09-07 (sur les quatre proprietes que `FER-1` oblige a servir, une seule etait testee par un item offrant une alternative serieuse ; trois items sur six sans alternative serieuse ; un indice qui ne voulait rien dire ; une sixieme famille jamais enseignee) ; **valide sous reserve au reaudit** - les quatre motifs sont payes, les quatre proprietes du libelle desormais servies chacune par un item avec une alternative serieuse a ecarter. Aucune mention de couverture partielle n'est due. Deux reserves non bloquantes, neuves et datees du 2026-09-08 : **`MAT-R1`** (l'item de la bouteille annonce l'impermeabilite comme critere alors que c'est la retention du gout qui departage, et le plastique y est faux sous une phrase qui le rend juste ailleurs dans la meme partie) et **`MAT-R2`** (l'item du tuyau d'arrosage n'offre aucune alternative serieuse, `PLA-1`) |
| sciences et technologie | CM1 | Materiaux et objets techniques | Identifier la fonction d'un objet technique, nommer ses composants et décrire son fonctionnement. | bandit-manchot-objet-technique-cm1 | bandit manchot | **couvert-valide** (2026-09-06, s30, instance B, apres reprise) - **`SCI-CM1-12` ferme.** Refuse au premier audit (les deux distracteurs de la tache de fonction reproduisaient `OBJ-R2` : ils etaient toujours la fonction d'un autre objet du paquet, y compris sur un item en dessous du niveau CM1) ; **valide sous reserve au reaudit** apres correction - un enfant ignorant l'objet ne gagne plus aucun des quatre items de fonction. Tache B entiere conservee (six pieces reelles, distracteurs pris sur le meme objet) ; l'item 11 (le fonctionnement, chaine causale entiere) conserve et jugee obligatoire par `nadia-prof`. Aucune mention de couverture partielle n'est due (`nadia-prof` le dit explicitement, hors des lignes 6, 14 et 20). Deux reserves non bloquantes du reaudit, datees du 2026-09-06 : **`OBT-R1`** (l'indice de l'item du velo dement la bonne reponse de son propre item) et **`OBT-R2`** (deux indices ecartent nommement les deux distracteurs, defaut fabrique par la correction de `OBJ-R2`). **Reaudit du 2026-09-07 (s31, instance A)** : `OBT-R1` et `OBT-R2` **levees** (l'indice de l'item du velo ne dement plus sa bonne reponse ; les deux indices touches par la correction de `OBJ-R2` n'ecartent plus qu'un seul distracteur nomme, jamais les deux a la fois - barre confirmee par `nadia-prof` elle-meme, *"au plus un distracteur nomme"*, contre une transcription plus stricte trouvee fausse dans le backlog et corrigee). Aucune reserve neuve. Statut inchange, `SCI-CM1-12` reste `couvert-valide`.
| sciences et technologie | CM1 | Materiaux et objets techniques | Repérer l'évolution d'un objet technique au cours du temps et ce qui a changé dans sa fabrication ou dans son usage. | - | - | non couvert - jouable au choix textuel, sous `BIA-3`. On decrit un changement, aucun item ne dit que l'objet ancien etait moins bien. |
| sciences et technologie | CM1 | Materiaux et objets techniques | Connaître quelques règles d'usage d'un environnement numérique (identification, courriel, recherche d'une information, protection de ses données). | - | - | non couvert - jouable au choix textuel, sur les connaissances seules. Mention exigee d'avance : "regles et connaissances d'usage seules ; la pratique d'un environnement numerique reste non couverte". |
| sciences et technologie | CM1 | La planete Terre. Les etres vivants dans leur environnement | Situer la Terre dans le système solaire et relier les mouvements de la Terre au jour, à la nuit et aux saisons. | - | - | non couvert - jouable au choix textuel, sous `SCI-1`. Exigence d'exactitude : la cause des saisons est l'inclinaison de l'axe terrestre, pas la distance au Soleil. |
| sciences et technologie | CM1 | La planete Terre. Les etres vivants dans leur environnement | Identifier quelques phénomènes géologiques et météorologiques (séisme, éruption, tempête, inondation) et les risques associés. | - | - | non couvert - jouable au choix textuel, sous `BIA-2`. Un paquet ne doit pas situer tous les risques ailleurs. |
| sciences et technologie | CM1 | La planete Terre. Les etres vivants dans leur environnement | Décrire un milieu proche : les êtres vivants qui le peuplent et les relations alimentaires qui les relient (chaîne alimentaire). | bandit-manchot-chaine-alimentaire-cm1 | bandit manchot | **couvert-valide** (2026-09-06, s30, instance B, apres reprise) - **`SCI-CM1-17` ferme.** Refuse au premier audit (les cinq items de la tache de consequence etaient un seul item servi cinq fois avec un autre nom d'animal, meme question et memes bonne reponse et distracteurs) ; **valide sous reserve au reaudit** apres reecriture des cinq items - le tirage a servi les cinq items reecrits, plus aucun n'est resolu par memorisation d'une phrase. Six relations alimentaires exactes, milieu coherent, `V3`, `CYC-1`, `SCI-2`, `SCI-3` tenues. Aucune mention de couverture partielle n'est due (`nadia-prof` le dit explicitement, hors des lignes 6, 14 et 20). Reserve non bloquante du reaudit, datee du 2026-09-06 : **`CHA-R1`** (deux distracteurs indefendables sur la tache de classement, item de la mesange qui se donne entierement - `nadia-prof` precise que ce n'est pas un reproche a la reprise, la tache A n'etait pas concernee par le refus) |
| sciences et technologie | CM1 | La planete Terre. Les etres vivants dans leur environnement | Identifier un effet de l'activité humaine sur l'environnement et un geste de préservation des ressources. | - | - | non couvert - jouable au choix textuel, sous `SCI-2`, ligne la plus dangereuse du lot. Aucun item ne met en cause le foyer de l'enfant. |
| sciences et technologie | CM1 | Demarches et langages scientifiques | Mener une démarche d'investigation : formuler une hypothèse, proposer une expérience pour la tester, interpréter le résultat et communiquer sa démarche. | - | - | **non couvrable en l'etat**, nature irreversible - geste pratique et compte rendu libre, jugement humain (`ADR-0008`) ; la decoupe qui la sauverait ("choisir l'experience qui teste cette hypothese") est une autre competence, plus etroite, et son emploi ici serait une contrefacon par reduction. |
| sciences et technologie | CM1 | Demarches et langages scientifiques | Extraire une information d'un document scientifique (texte, tableau, schéma, graphique) et la restituer. | bandit-manchot-sciences-extraire-information-cm1 | bandit manchot | **couvert-valide** (2026-09-23, s40) - **`SCI-CM1-20` ferme, couverture partielle. Premier paquet a utiliser `tableauSupport` (`ADR-0099`).** Done pedagogique **VALIDE, sans reserve**, prononce par `nadia-prof` apres avoir joue le jar servi (`squad/conformite/FICHE-bandit-manchot-sciences-extraire-information-cm1.md`). Douze items, deux textes et deux tableaux, chaque reponse un extrait ou une cellule recopiable, jamais une deduction ; le tableau verifie sans fuite (rendu inspecte a la source HTML), bonne reponse et distracteurs verifies comme cellules du meme tableau. **Mention obligatoire, reprise mot pour mot : "document textuel et tableau seuls ; le schema et le graphique restent non couverts."** |

**Conditions opposables, posees le 2026-09-03** : `SCI-1` (apres une reponse fausse, le retour dit
pourquoi elle est fausse, en une phrase) ; `SCI-2` (sur les risques naturels et l'impact humain,
aucun item ne fait porter une responsabilite a l'enfant ni a son foyer) ; `SCI-3` (aucun item ne
demande une valeur numerique a restituer au-dela d'un item sur six, transposition de `HIS-1`).

**Bilan** : 20 lignes, **4 `couvert-valide`** (`SCI-CM1-02`, `SCI-CM1-05`, `SCI-CM1-12`,
`SCI-CM1-17`, s30, instance B), 15 `non couvert`, 1 `non couvrable en l'etat`. 19 lignes entrent au
denominateur atteignable.

**Bilan corrige le 2026-09-08 (`camille-po`, s31) - quatre lignes de plus ferment** : 20 lignes,
**8 `couvert-valide`** (`SCI-CM1-01`, `SCI-CM1-02`, `SCI-CM1-05`, `SCI-CM1-07`, `SCI-CM1-08`,
`SCI-CM1-11`, `SCI-CM1-12`, `SCI-CM1-17`), 11 `non couvert`, 1 `non couvrable en l'etat`. 19 lignes
entrent au denominateur atteignable (inchange).

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

## Amendement du 2026-08-25 (hors sprint) - une competence entre, deux lignes sortent du denominateur atteignable, 13/43 (30,2 %)

Trois arbitrages hors sprint de l'utilisatrice, traces dans `DEC-0046`. **Ce mouvement n'est pas un
mouvement de couverture** : aucun increment n'a ete joue ni audite, c'est un effet de perimetre du
referentiel.

- **Une competence entre** au referentiel francais CM1 (`non couvert`) : "Reperer et corriger la
  majuscule et la ponctuation dans une phrase donnee...", detachee de "Relire et corriger son
  texte...", acceptee par l'utilisatrice.
- **Deux lignes de mathematiques CM1 sortent du denominateur atteignable** : "Reconnaitre, nommer et
  tracer des figures planes... avec outils" et "Reconnaitre et utiliser la notion de symetrie
  axiale simple", `non couvert` -> `non couvrable en l'etat` (nature reversible - le produit a
  choisi de ne pas avoir le geste de trace, un arbitrage qui se revise, distinct de l'irreversible
  qui exige un humain pour juger).

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide | non couvrable en l'etat |
|---|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 1 | 4 | 0 |
| mathematiques CM1 | 12 | 4 | 2 | 4 | 2 |
| francais CP | 12 | 9 | 2 | 0 | 1 |
| francais CM1 | 12 | 6 | 0 | 5 | 1 |
| **Total** | **47** | **25** | **5** | **13** | **4** |

**Le chiffre, avec sa base.** Le total de competences passe de **46** a **47** (une ligne
detachee et amendee au referentiel). Le nombre de lignes `non couvrable en l'etat` passe de **2** a
**4** (deux lignes de geometrie CM1 s'y ajoutent, nature reversible). Le denominateur atteignable
passe de **44** (46 - 2) a **43** (47 - 4). Le compte de lignes `couvert-valide` ne bouge pas :
**13**. **13/44 (29,5 %) devient 13/43 (30,2 %)**, sans aucun increment joue ni audite.

**Ce qui ne bouge pas.** Aucune ligne ne passe `couvert-valide` par ce geste. Aucun Done
pedagogique n'est prononce. Le statut de "Nom ou verbe ?" (`en cours`, hors catalogue par
`DEC-0045`) et celui de la ligne "Distinguer les principaux types de mots" ne bougent pas : la
reexamination de `NAT-9` par `nadia-prof`, sur l'arbitrage elargi de l'utilisatrice concernant
l'accompagnement du CP, reste en attente (`DEC-0046`, section 3).

*Mise a jour du 2026-08-25, le jour meme : la reexamination annoncee ci-dessus a eu lieu pendant
que ce paragraphe s'ecrivait. Voir la note dediee, section francais CM1, "`NAT-9` levee, `NAT-5`
seule reste bloquante" : je ne reecris pas cette phrase pour que l'ecart entre l'attente et sa
levee reste lisible, sur le meme principe que les amendements de `DEC-0046` eux-memes.*

## Amendement du 2026-08-25 (s20, ouverture) - une ligne s'ouvre en contenu seul, 13/43 (30,2 %) inchange

`S20-2` (`docs/backlog/backlog.md`, `DEC-0047`) ouvre "Reperer et corriger la majuscule et la
ponctuation dans une phrase donnee..." : `non couvert` -> `en cours`, jeu propose `bandit-manchot-
reperes-cm1`. Aucun increment n'est encore livre ni audite.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide | non couvrable en l'etat |
|---|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 1 | 4 | 0 |
| mathematiques CM1 | 12 | 4 | 2 | 4 | 2 |
| francais CP | 12 | 9 | 2 | 0 | 1 |
| francais CM1 | 12 | 5 | 1 | 5 | 1 |
| **Total** | **47** | **24** | **6** | **13** | **4** |

**Le chiffre, avec sa base.** Le denominateur atteignable reste **43** sur **47**, inchange. Le
compte de lignes `couvert-valide` ne bouge pas : **13**. **13/43 reste 13/43 (30,2 %)** : ouvrir une
ligne en `en cours` n'est pas un mouvement de couverture, seul un Done pedagogique en est un.

**Ce qui ne bouge pas.** "Distinguer les principaux types de mots" reste `en cours`, `NAT-5` restant
la seule condition avant reaudit (note ci-dessus). Aucune autre cellule ne bouge.

## Amendement du 2026-08-25 (s20, deuxieme passage) - un increment VALIDE, un increment REFUSE, 14/43 (32,6 %)

Deux verdicts de `nadia-prof` sur les deux jeux ouverts par ce sprint. **`bandit-manchot-nature-
mot-cp`** (`S20-1`, `NAT-5`) : `VALIDE`, plus aucune reserve bloquante - detail complet et
verification personnelle avant de faire bouger la ligne : `DEC-0048`. **`bandit-manchot-reperes-
cm1`** (`S20-2`) : `REFUSE` sur deux motifs mesures - detail complet : `DEC-0049`. Une seule des
deux lignes bouge.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide | non couvrable en l'etat |
|---|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 1 | 4 | 0 |
| mathematiques CM1 | 12 | 4 | 2 | 4 | 2 |
| francais CP | 12 | 9 | 1 | 1 | 1 |
| francais CM1 | 12 | 5 | 1 | 5 | 1 |
| **Total** | **47** | **24** | **5** | **14** | **4** |

Francais CP : `en cours` passe de 2 a 1, `couvert-valide` de 0 a 1 - "Distinguer les principaux
types de mots" est la ligne qui bouge. Francais CM1 : inchangee - "Reperer et corriger la
majuscule et la ponctuation..." reste `en cours`, le `REFUSE` ne la fait pas reculer, il l'empeche
seulement d'avancer.

**Le chiffre, avec sa base.** Le denominateur atteignable reste **43** sur **47**, inchange. Le
compte de lignes `couvert-valide` passe de **13** a **14**. **13/43 (30,2 %) devient 14/43
(32,6 %)** - un mouvement de couverture reel, le premier increment joue et audite `VALIDE` depuis la
cloture de s19.

**Ce qui ne bouge pas.** Aucune ligne ne passe `couvert-valide` pour `cm1-reperes` : ce statut
n'est prononce que par `nadia-prof`, apres audit reel, et son verdict de ce jour est `REFUSE`.

## Amendement du 2026-08-27 (s20, cloture) - `cm1-reperes` VALIDE SOUS RESERVE, 15/43 (34,9 %)

`nadia-prof` a reaudite le paquet corrige par `lea-dev` (`round-two` a `77dd467`) et prononce
**VALIDE SOUS RESERVE** le 2026-08-27 - detail complet et verification personnelle avant de faire
bouger la ligne : `DEC-0050`. Les deux motifs du `REFUSE` du 2026-08-26 sont traites : `REP-4` est
tenue et mesuree (13 sur 16 au lieu de 16 sur 16 sur la simulation de l'enfant qui ignore le point
d'interrogation, trois items exigeant desormais la distinction) ; le pli est lisible sur 14 items
sur 16 apres un echec (0 sur 16 au refus). "Reperer et corriger la majuscule et la ponctuation dans
une phrase donnee..." passe `en cours` -> `couvert-valide`.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide | non couvrable en l'etat |
|---|---|---|---|---|---|
| mathematiques CP | 11 | 6 | 1 | 4 | 0 |
| mathematiques CM1 | 12 | 4 | 2 | 4 | 2 |
| francais CP | 12 | 9 | 1 | 1 | 1 |
| francais CM1 | 12 | 5 | 0 | 6 | 1 |
| **Total** | **47** | **24** | **4** | **15** | **4** |

Francais CM1 : `en cours` passe de 1 a 0, `couvert-valide` de 5 a 6 - "Reperer et corriger la
majuscule et la ponctuation..." est la seule ligne qui bouge. Aucune autre matiere n'est touchee par
ce geste.

**Le chiffre, avec sa base, verifie ligne par ligne contre les quatre tableaux de matiere de cette
matrice avant d'etre ecrit (2026-08-27), pas repris d'un autre document du depot.** Le denominateur
atteignable reste **43** sur **47**, inchange : aucune ligne `non couvrable en l'etat` ne bouge. Le
compte de lignes `couvert-valide` passe de **14** a **15**. **14/43 (32,6 %) devient 15/43
(34,9 %)** - un mouvement de couverture reel, le second increment joue et audite `VALIDE` (ou
`VALIDE SOUS RESERVE`) depuis la cloture de s19.

**Ce que ce mouvement ne couvre pas, nomme pour que personne ne le croie couvert.** La ligne
d'origine ("Relire et corriger son texte a l'aide de reperes simples") reste `non couvrable en
l'etat`, inchangee : ce paquet ne couvre que la connaissance des reperes sur une phrase donnee,
jamais la relecture par l'enfant de son propre texte. Quatre reserves non bloquantes restent
ouvertes sur ce Done (`REP-7`, `REP-8`, `REP-9`, `REP-10`, 2026-08-27) - detail dans la section
francais CM1 ci-dessus et dans `docs/backlog/backlog.md`, "Sprint 20". `REP-8` en particulier n'est
pas propre a ce paquet : `nadia-prof` la nomme premier defaut pedagogique du catalogue aujourd'hui
(8 jeux sur 17, 43 items sur 157, mesure du 2026-08-27), routee comme story de catalogue et non
comme reprise de ce paquet.

**Ce qui ne bouge pas.** Le geste materiel sur `catalogue.yaml` (retour de l'entree
`bandit-manchot-reperes-cm1`) est execute par `sofia-sm`, pas par ce document. Aucune autre ligne de
cette matrice ne bouge par ce mouvement.

## Amendement du 2026-08-28 (s21, cloture) - `cp-comparer` et `cp-syllabes` VALIDE SOUS RESERVE, 17/43 (39,5 %) ; une ligne reste decrite sans etre deplacee

`nadia-prof` a rendu son Done pedagogique de s21 le 2026-08-28 (branche `s21-nadia-2`, commit
`18d1220`) : `bandit-manchot-comparer-cp` **VALIDE SOUS RESERVE** (dix conditions sur dix tenues, 18
items sur 18 decidables, sept reserves non bloquantes) ; `bandit-manchot-syllabes-cp` **REFUSE**
puis, apres arbitrage de l'utilisatrice sur l'item `CHEMINEE` (*"cheminee vaut bien 3 syllabes"*) et
amendement de `SEG-1` clause (c), verdict repris le meme jour (commit `4326f3b`) en **VALIDE SOUS
RESERVE** ; `bandit-manchot-mot-manquant-cp` **REFUSE**, verdict inchange. Detail complet et
verification personnelle avant de faire bouger une ligne : `DEC-0052`.

**"Comparer des longueurs, des masses, des contenances (sans instrument gradue)"** (mathematiques
CP) et **"Denombrer les syllabes orales d'un mot (segmentation syllabique)"** (francais CP) passent
`non couvert` -> `couvert-valide`.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide | non couvrable en l'etat |
|---|---|---|---|---|---|
| mathematiques CP | 11 | 5 | 1 | 5 | 0 |
| mathematiques CM1 | 12 | 4 | 2 | 4 | 2 |
| francais CP | 12 | 8 | 1 | 2 | 1 |
| francais CM1 | 12 | 5 | 0 | 6 | 1 |
| **Total** | **47** | **22** | **4** | **17** | **4** |

**Le chiffre, avec sa base, verifie ligne par ligne contre les quatre tableaux de matiere de cette
matrice avant d'etre ecrit (2026-08-28), pas repris d'un autre document du depot.** Le denominateur
atteignable reste **43** sur **47**, inchange. Le compte de lignes `couvert-valide` passe de **15**
a **17**. **15/43 (34,9 %) devient 17/43 (39,5 %)**.

**Sur la ligne "Denombrer les syllabes orales d'un mot", dans les mots de `nadia-prof` que je
reprends sans les affaiblir** : elle ouvre **une ligne sur trois** du domaine "Langage oral et
conscience phonologique" ; les deux autres restent hors d'atteinte sans emission de son
(`DEC-0042`, arbitrage 2). **Ne pas lire ce mouvement comme l'ouverture du domaine.**

### La ligne 496 reste `non couvert` : ou elle en est, ce qui manque pour bouger

**Ligne 496 - "Utiliser des mots precis pour designer des objets, actions, sensations du
quotidien".** Le paquet `bandit-manchot-mot-manquant-cp` (`S21-10`) existe et a ete audite,
**REFUSE** sur deux motifs de contenu entiers (item 6 ; items 3 et 4, `PRE-2` et `PRE-3`) et
**retire du catalogue** (`DEC-0052`, section 3). `lea-dev` corrige les items concernes. **Ce qui
manque pour que cette ligne bouge** : la correction livree, puis un reaudit de `nadia-prof` qui ne
nomme plus les deux motifs.

**Ce que ce document ne fait pas.** Il ne prononce aucun Done pedagogique lui-meme, ne prejuge pas
du reaudit a venir sur `cp-mot-manquant`, et ne fait bouger aucune autre ligne que les deux nommees
ci-dessus.

## Amendement du 2026-08-28 (second, s21 cloture) - `cp-mot-manquant` VALIDE SOUS RESERVE au second reaudit, 18/43 (41,9 %)

**Rien ci-dessus n'est reecrit** : la section precedente decrivait un etat reel (paquet REFUSE,
ligne non deplacee), et cet etat a change par une correction intra-sprint, pas par une erreur de
lecture. Detail complet et verification personnelle avant de faire bouger la ligne : `DEC-0052`,
amendement du 2026-08-28 (second).

`lea-dev` a corrige l'item 3 de `cp-mot-manquant` (`4543d8d`, un seul champ, l'enonce). `nadia-prof`
a reaudite en jouant et pressant chaque proposition de l'item corrige, et prononce **`VALIDE SOUS
RESERVE`** (`2d2cea9`, 2026-08-28) : le motif unique restant (item 3, `PRE-3`) est ferme, `PRE-2`
reste tenue, le pli reste tenu sur seize items sur seize. Sept reserves non bloquantes restent
ouvertes.

**"Utiliser des mots precis pour designer des objets, actions, sensations du quotidien"** (francais
CP) passe `non couvert` -> `couvert-valide`.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide | non couvrable en l'etat |
|---|---|---|---|---|---|
| mathematiques CP | 11 | 5 | 1 | 5 | 0 |
| mathematiques CM1 | 12 | 4 | 2 | 4 | 2 |
| francais CP | 12 | 7 | 1 | 3 | 1 |
| francais CM1 | 12 | 5 | 0 | 6 | 1 |
| **Total** | **47** | **21** | **4** | **18** | **4** |

**Le chiffre, avec sa base, verifie ligne par ligne contre les quatre tableaux de matiere de cette
matrice avant d'etre ecrit (2026-08-28), pas repris d'un autre document du depot.** Le denominateur
atteignable reste **43** sur **47**, inchange. Le compte de lignes `couvert-valide` passe de **17**
a **18**. **17/43 (39,5 %) devient 18/43 (41,9 %)**.

**Ce que ce mouvement fait, et pas plus.** Les trois paquets de volume de s21 (`cp-syllabes`,
`cp-mot-manquant`, `cp-comparer`) ont chacun un Done pedagogique desormais prononce - deux `VALIDE
SOUS RESERVE` obtenus au premier verdict de fond (`cp-syllabes` apres arbitrage de l'utilisatrice,
`cp-comparer` directement), un troisieme apres deux refus et trois tours de correction
(`cp-mot-manquant`). Aucune ligne au-dela des trois ciblees par ces paquets ne bouge ici.

**Ce que ce document ne fait pas.** Il ne prononce aucun Done pedagogique lui-meme et ne fait
bouger aucune autre ligne que celle nommee ci-dessus.

## Note du 2026-08-28 (ouverture s22, `camille-po`) - le pli sort de la porte pedagogique, aucune ligne ne bouge par cette note

L'utilisatrice arbitre, a l'ouverture du sprint 22, que le pli (un element du jeu - indice, verdict,
proposition - qui reste hors de vue sans defiler) sort de la porte pedagogique sur **tout le
catalogue**, paquets neufs compris : `nadia-prof` ne prononce plus de `REFUSE` sur ce seul motif,
elle le consigne en reserve non bloquante. Detail complet, motif retenu et point de vigilance sur ce
que cet arbitrage ne touche pas (les garde-fous techniques `ADR-0017`, `ADR-0063` restent en
vigueur, independants de tout jugement pedagogique) : `DEC-0053`, arbitrage 1.

**Consequence pour la lecture de cette matrice, a garder a l'esprit a chaque ligne `couvert-valide`
future** : un statut `couvert-valide` prononce a partir de ce jour ne garantit plus qu'aucun element
du jeu ne reste sous le pli - cette dette est desormais explicitement croissante et assumee par
l'utilisatrice (`REP-8`, `docs/backlog/backlog.md`, reste ouverte et nommee, de meme que les 4 items
sans levier connu de l'obstacle 21 de s21). Ce n'etait deja pas garanti pour les statuts prononces
avant cette date sur un motif autre que le pli, mais la possibilite qu'un motif de pli seul ait
empeche un `REFUSE` disparait a partir de maintenant. **Aucune ligne de cette matrice ne change de
statut par cette note** : elle documente un changement de critere de validation, pas un mouvement de
couverture.

**Complement du 2026-08-28 (meme jour) - portee confirmee par l'utilisatrice, contrepartie ecrite.**
La lecture large ci-dessus (indice, verdict, proposition, tout element du meme bloc) a ete portee a
l'utilisatrice par `sofia-sm`, avec la consequence dans les mots de `nadia-prof` et le precedent de
son `REFUSE` sur `cm1-reperes` a s20 ; **elle la confirme** (`squad/conformite/PORTE-PEDAGOGIQUE-
apres-arbitrage-du-2026-08-28.md`, section 10, `nadia-prof`). La contrepartie qu'elle a demandee est
desormais une clause opposable et non une intention de sprint : **`MNC-1`**, obligatoire sur tout
Done pedagogique prononce a partir de ce jour, versee au gabarit de fiche de conformite
(`squad/TEMPLATES.md`, section 7) pour qu'elle ne depende pas de la memoire d'un agent. Un lecteur de
cette matrice qui voit un `couvert-valide` date d'apres le 2026-08-28 trouve `MNC-1` dans la fiche
correspondante ; s'il ne l'y trouve pas, c'est un defaut de la fiche a signaler, pas une garantie
acquise.

## Amendement du 2026-08-30 (s22, cloture) - S13-2, S13-3 et S17-2 passent `couvert-valide`, 21/43 (48,8 %)

`nadia-prof` a prononce trois Done pedagogique le 2026-08-30 (branche `s22-nadia-4`, ouverte sur
`round-two` a `9bb7b8a`) : `bandit-manchot-multiplications-divisions-cm1` (`S13-2`) **VALIDE**,
aucune reserve nouvelle ; `bandit-manchot-problemes-durees-cm1` (`S13-3`) **VALIDE SOUS RESERVE**,
cinq reserves non bloquantes ; `bandit-manchot-comprehension-cm1` (`S17-2`) **VALIDE**, deux reserves
non bloquantes. Aucune reserve bloquante sur aucun des trois. Elle l'ecrit elle-meme : *"`camille-po`
peut passer les trois lignes de matrice a `couvert-valide` - ce n'est pas ma main."* Detail complet,
motifs et methodes de mesure : les blocs de validation des trois `squad/conformite/FICHE-*.md`
correspondantes, et `squad/sprints/s22-couverture-maximale-du-catalogue/review.md`.

**Trois lignes passent** :

- **"Calcul mental - multiplications et divisions simples (multiples de 10, 100)"** (mathematiques
  CM1) : `en cours` -> `couvert-valide`.
- **"Resoudre des problemes de durees (calcul d'une duree, d'un horaire d'arrivee)"** (mathematiques
  CM1) : `en cours` -> `couvert-valide`.
- **"Comprendre un texte narratif ou documentaire (questions explicites et implicites, inferences
  simples)"** (francais CM1) : `non couvert` -> `couvert-valide`.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide | non couvrable en l'etat |
|---|---|---|---|---|---|
| mathematiques CP | 11 | 5 | 1 | 5 | 0 |
| mathematiques CM1 | 12 | 4 | 0 | 6 | 2 |
| francais CP | 12 | 7 | 1 | 3 | 1 |
| francais CM1 | 12 | 4 | 0 | 7 | 1 |
| **Total** | **47** | **20** | **2** | **21** | **4** |

**Le chiffre, avec sa base, verifie ligne par ligne contre les quatre tableaux de matiere de cette
matrice avant d'etre ecrit (2026-08-30), pas repris d'un autre document du depot.** Le denominateur
atteignable reste **43** sur **47**, inchange. Le compte de lignes `couvert-valide` passe de **18** a
**21**. **18/43 (41,9 %) devient 21/43 (48,8 %)**.

**Deux precisions a porter avec les lignes, pas a laisser dans l'ombre d'un statut vert.**

- **Sur la ligne des durees (`S13-3`)** : sur les douze items de la forme "horaire d'arrivee", la
  bonne reponse **n'est jamais la plus petite des trois propositions (0 sur 12), structurellement**
  - `ERR-INVERSE` et la recopie de l'heure de depart produisent toutes deux un horaire anterieur,
  jamais posterieur. `nadia-prof` le nomme comme une regularite **creee par la correction qu'elle a
  elle-meme exigee** sur `D-6` (reserve D, `squad/conformite/FICHE-bandit-manchot-problemes-durees-
  cm1.md`). Non bloquante, `D-7` reste tenue au chiffre (mesuree sur le paquet entier, pas sur cette
  seule forme), mais un enfant qui repere la forme peut ecarter la proposition la plus matinale sans
  calculer, sur la moitie du paquet.
- **Sur la ligne de comprehension (`S17-2`)** : cette ligne ouvre une competence sur trois du domaine
  "Lecture et comprehension de l'ecrit" en francais CM1. Les deux autres - **"Lire silencieusement un
  texte de longueur croissante et en restituer l'essentiel"** et **"Reperer les substituts d'un
  personnage (pronoms, groupes nominaux) dans un texte"** - restent `non couvert` : ce paquet ne les
  couvre pas et ne les ouvre pas. Ne pas lire ce mouvement comme l'ouverture du domaine, meme lecture
  que pour la segmentation syllabique a s21.

**`MNC-1` mord pour la premiere fois.** Les trois blocs de validation la portent, dans son texte
integral du 2026-08-28 (`DEC-0053`) : *"ce Done ne couvre pas que l'enfant voie, sans faire defiler
la page, un element quelconque du jeu, y compris le retour qui suit sa reponse - verdict, indice,
proposition"*. Aucune mesure ne l'a instruite sur les trois paquets ; la clause reste ouverte,
croissante et assumee, elle n'est plus vide de cas.

**Ce que ce document ne fait pas.** Il ne prononce aucun Done pedagogique lui-meme et ne fait bouger
aucune autre ligne que les trois nommees ci-dessus.

## Note du 2026-08-30 (ouverture s23, `camille-po`) - la reserve de contenu seul deja arbitree tombe
## a zero, huit arbitrages prealables poses, aucune ligne ne bouge par cette note

A l'ouverture de s22, six lignes de cette matrice etaient deja arbitrees pedagogiquement et pretes a
developper sans arbitrage supplementaire (`DEC-0053`, section 7). Trois sont desormais
`couvert-valide` (voir amendement ci-dessus). **Les trois autres restent `non couvert`/`en cours`,
mais aucune n'est developpable aujourd'hui pour un motif d'arbitrage pedagogique** : "Reconnaitre et
nommer des figures planes" (mathematiques CP) et la moitie "reperage sur une droite numerique" de
"Notion de fraction simple..." (mathematiques CM1) attendent une decision d'architecture pour un
item visuel qui n'existe pas encore ; "Decoder un mot regulier inconnu par assemblage syllabique"
(francais CP) attend une source d'enregistrement de voix humaine qu'aucun agent de cette squad ne
peut fournir. **La reserve de contenu seul reellement pret a developper est donc de zero ligne** au
2026-08-30, la premiere fois que ce backlog est vide a l'ouverture d'un sprint depuis s19. Detail
complet, huit arbitrages prealables poses en priorite pour s23 (six candidats de contenu seul,
jamais arbitres individuellement, plus deux candidats de niveau 2 de `DEC-0051` poses le 2026-08-28
et jamais repondus depuis) : `DEC-0057`.

**Aucune ligne de cette matrice ne change de statut par cette note.**

## Amendement du 2026-08-30 (s23, en cours) - `cm1-problemes-etapes` passe `couvert-valide`, 22/43 (51,2 %) ; deux refus, aucune ligne ne bouge sur eux

`nadia-prof` a rendu quatre verdicts le 2026-08-30 (branche `s23-nadia-3`, non fusionnee) : un Done
pedagogique neuf, deux refus, un reaudit maintenu. Detail complet, motifs et methodes de mesure :
les blocs de validation des quatre `squad/conformite/FICHE-bandit-manchot-*.md` correspondantes, et
`squad/sprints/s23-trois-paquets-parmi-sept/avis-nadia-done-pedagogique-s23.md`.

**Une seule ligne bouge.**

- **"Resoudre des problemes a plusieurs etapes mobilisant les 4 operations"** (mathematiques CM1,
  `S23-ETA`) : `non couvert` -> `couvert-valide`. `nadia-prof` prononce **VALIDE SOUS RESERVE**
  (trois reserves non bloquantes, aucune bloquante) et l'ecrit elle-meme en toutes lettres :
  *"`camille-po` peut passer la ligne a `couvert-valide`, sans mention de couverture partielle :
  'a deux etapes' est le 'plusieurs' du CM1, et c'est exactement ce que le paquet fait."* Contrairement
  a la ligne des durees (une competence sur trois du meme domaine) ou a celle de comprehension
  (idem), cette competence est unique dans son domaine : aucune mention de couverture partielle n'y
  est donc attachee.

**Deux refus ne font bouger aucune ligne**, par construction de la nomenclature de cette matrice
(un refus n'est pas un Done) :

- **"Comparer et ranger des nombres jusqu'a 100"** (mathematiques CP, `S23-RAN`) : `nadia-prof`
  refuse sur `RAN-7` (l'indice ne donne pas la methode de son item, cinq items en defaut). La ligne
  reste `en cours` - la moitie "comparer" y est deja validee depuis le 2026-08-12, seule la moitie
  "ranger" est concernee par ce refus.
- **"Utiliser a bon escient des mots de liaison simples pour structurer un texte court"** (francais
  CM1, `S23-LIA`) : `nadia-prof` refuse sur `LIA-3` (deux distracteurs sur 40 rendent le texte
  moins bon et non faux). La ligne reste `non couvert`.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide | non couvrable en l'etat |
|---|---|---|---|---|---|
| mathematiques CP | 11 | 5 | 1 | 5 | 0 |
| mathematiques CM1 | 12 | 3 | 0 | 7 | 2 |
| francais CP | 12 | 7 | 1 | 3 | 1 |
| francais CM1 | 12 | 4 | 0 | 7 | 1 |
| **Total** | **47** | **19** | **2** | **22** | **4** |

**Le chiffre, avec sa base, verifie ligne par ligne contre les quatre tableaux de matiere de cette
matrice avant d'etre ecrit (2026-08-30), pas repris d'un autre document du depot** - `nadia-prof`
mentionne elle-meme, dans son avis, un mouvement "21/43 vers 22/43" en precisant explicitement que
ce compte n'est pas le sien. Le denominateur atteignable reste **43** sur **47**, inchange. Le
compte de lignes `couvert-valide` passe de **21** a **22**. **21/43 (48,8 %) devient 22/43
(51,2 %)**.

**Ce que ce mouvement fait, et pas plus.** `cm1-problemes-durees` (`S13-3`) reste `couvert-valide` :
le reaudit de `nadia-prof` sur ce meme paquet le maintient (`VALIDE SOUS RESERVE, maintenu`), quatre
reserves non bloquantes levees, une (reserve D) non levee et non bloquante ; aucune ligne n'en
depend, voir `docs/backlog/backlog.md`, section "Sprint 23", pour le detail des reserves.

**Ce que ce document ne fait pas.** Il ne prononce aucun Done pedagogique lui-meme et ne fait
bouger aucune autre ligne que celle nommee ci-dessus.

## Amendement du 2026-08-30 (s23, en cours, reaudit) - `RAN-7` levee, "Comparer et ranger des nombres jusqu'a 100" passe `couvert-valide`, 23/43 (53,5 %) ; `LIA` refusee une seconde fois, `ETA` maintenue

`nadia-prof` a reaudite le 2026-08-30 (branche `s23-nadia-4`, non fusionnee) trois paquets corriges
depuis son premier verdict : `cp-ranger-nombres`, `cm1-mots-de-liaison`, `cm1-problemes-etapes`.
Detail complet, motifs et methodes de mesure : les blocs de reaudit des trois
`squad/conformite/FICHE-bandit-manchot-*.md` correspondantes, et l'amendement du meme jour a
`squad/sprints/s23-trois-paquets-parmi-sept/avis-nadia-done-pedagogique-s23.md`.

**Une ligne bouge.**

- **"Comparer et ranger des nombres jusqu'a 100 (`<`, `>`, `=`)"** (mathematiques CP, `S23-RAN`) :
  `en cours` -> `couvert-valide`. `nadia-prof` prononce **VALIDE, sans reserve** sur
  `cp-ranger-nombres` (`RAN-7` levee, cinq indices reapparies, verifiee sur les 24 items et par un
  garde-fou qu'elle a elle-meme confronte a 487 080 couples). Cette ligne portait, depuis le
  sprint 9, une precision disant que "ranger" restait hors du perimetre du jeu qui la couvrait
  alors (`bandit-manchot-comparer-nombres-cp`) : **cette precision ne disait pas autre chose que ce
  qu'elle disait a sa date, et elle est desormais soldee** (voir la note ajoutee au 2026-08-30 dans
  la section "Mathematiques - CP" ci-dessus, juste apres le texte d'origine, qui reste lisible).
  Ce n'est pas le jeu de comparaison qui s'est etendu ; c'est un second jeu, `cp-ranger-nombres`, qui
  couvre desormais la moitie manquante. La colonne "Jeu(x) couvrant" porte donc les deux noms.

**Deux paquets ne font bouger aucune ligne.**

- **`cm1-mots-de-liaison` (`S23-LIA`)** : `nadia-prof` ferme son premier motif (items 3 et 18) et
  en ouvre un second, sur un troisieme item (item 10, distracteur `mais`) qu'elle n'avait pas vu au
  premier audit. **REFUSE**, second refus. La ligne "Utiliser a bon escient des mots de liaison
  simples pour structurer un texte court" (francais CM1) **reste `non couvert`**.
- **`cm1-problemes-etapes` (`S23-ETA`)** : **VALIDE SOUS RESERVE (maintenu)**. Les reserves A, B et
  C du premier verdict sont levees ; deux reserves nouvelles s'ouvrent (D : trois indices nomment un
  evenement que le correctif a retire de leur enonce ; E : la reserve C d'origine ne nommait que
  deux des six items portant la meme ambiguite). La ligne "Resoudre des problemes a plusieurs
  etapes mobilisant les 4 operations" (mathematiques CM1) **reste `couvert-valide`**, sans mention
  de couverture partielle - le Done du 2026-08-30 n'est ni etendu ni retire par ce reaudit.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide | non couvrable en l'etat |
|---|---|---|---|---|---|
| mathematiques CP | 11 | 5 | 0 | 6 | 0 |
| mathematiques CM1 | 12 | 3 | 0 | 7 | 2 |
| francais CP | 12 | 7 | 1 | 3 | 1 |
| francais CM1 | 12 | 4 | 0 | 7 | 1 |
| **Total** | **47** | **19** | **1** | **23** | **4** |

**Le chiffre, avec sa base, verifie ligne par ligne contre les quatre tableaux de matiere de cette
matrice avant d'etre ecrit (2026-08-30), pas repris d'un autre document du depot.** Le denominateur
atteignable reste **43** sur **47**, inchange. Le compte de lignes `couvert-valide` passe de **22**
a **23**. **22/43 (51,2 %) devient 23/43 (53,5 %)**.

**Ce que ce mouvement fait, et pas plus.** Il ne prejuge d'aucune correction en cours sur `LIA`
(second refus, item 10) : la ligne concernee reste `non couvert` jusqu'a un reaudit qui ne la nomme
plus en defaut. Il ne change rien non plus a `cm1-problemes-durees`, hors perimetre de ce reaudit.

**Ce que ce document ne fait pas.** Il ne prononce aucun Done pedagogique lui-meme et ne fait
bouger aucune autre ligne que celle nommee ci-dessus.

## Amendement du 2026-08-30 (s23, en cours) - `cm1-mots-de-liaison` passe `couvert-valide` en couverture partielle nommee, 24/43 (55,8 %) ; `cm1-problemes-etapes` passe de "valide sous reserve" a "valide", sans mouvement de ligne

`nadia-prof` a rendu ses derniers verdicts du sprint le 2026-08-30 (branche `s23-nadia-5`, non
fusionnee, trois commits) : `cm1-mots-de-liaison` **VALIDE, aucune reserve** (`LIA-3` levee sur
l'item 10, troisieme et dernier compte sur les 40 distracteurs) ; `cm1-problemes-etapes` **VALIDE**
(reserves D et E levees, les cinq reserves A a E du paquet desormais fermees). Source complete :
les derniers blocs des deux `squad/conformite/FICHE-bandit-manchot-*.md` correspondantes.

**Une ligne bouge, en couverture partielle nommee.**

- **"Utiliser a bon escient des mots de liaison simples pour structurer un texte court"** (francais
  CM1, `S23-LIA`) : `non couvert` -> `couvert-valide`. La mention de couverture partielle, ecrite
  par `nadia-prof` avant tout jugement (`LIA-10`) et non par moi, est portee avec la ligne dans la
  section "Francais - CM1" ci-dessus, juste apres le tableau : *"choix du mot de liaison entre deux
  ou trois phrases, sans production de texte"*. La limite principale qu'elle nomme - l'enfant ne
  redige jamais, il choisit - est ecrite en toutes lettres a cet endroit, avec le reste de ce que le
  paquet ne couvre pas, pour qu'un statut vert ne la laisse pas dans l'ombre.

**Une ligne ne bouge pas, mais le Done qui la couvre change de verdict.**

- **"Resoudre des problemes a plusieurs etapes mobilisant les 4 operations"** (mathematiques CM1,
  `S23-ETA`) : la ligne est deja `couvert-valide` sans mention partielle depuis l'amendement du
  2026-08-30 precedent, et `nadia-prof` l'ecrit elle-meme - *"rien a ecrire sur la matrice"*. **Ce
  qui change est le verdict de la fiche, pas le statut de cette matrice** : les cinq reserves A a E
  sont desormais toutes fermees (A, B, C au premier reaudit ; D et E a celui-ci), et un increment
  sans reserve pendante n'est plus "sous reserve" - `squad/conformite/FICHE-bandit-manchot-
  problemes-etapes-cm1.md` porte donc **VALIDE** la ou elle portait **VALIDE SOUS RESERVE**. Je le
  note ici pour qu'un lecteur de cette matrice, qui ne voit qu'un statut `couvert-valide` inchange,
  sache que la fiche qui le fonde a fini de bouger.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide | non couvrable en l'etat |
|---|---|---|---|---|---|
| mathematiques CP | 11 | 5 | 0 | 6 | 0 |
| mathematiques CM1 | 12 | 3 | 0 | 7 | 2 |
| francais CP | 12 | 7 | 1 | 3 | 1 |
| francais CM1 | 12 | 3 | 0 | 8 | 1 |
| **Total** | **47** | **18** | **1** | **24** | **4** |

**Le chiffre, avec sa base, verifie ligne par ligne contre les quatre tableaux de matiere de cette
matrice avant d'etre ecrit (2026-08-30), pas repris d'un autre document du depot.** Le denominateur
atteignable reste **43** sur **47**, inchange. Le compte de lignes `couvert-valide` passe de **23**
a **24**. **23/43 (53,5 %) devient 24/43 (55,8 %)**.

**Sur la date de ce reaudit.** `nadia-prof` signale que ses blocs portent le 2026-08-30, verifie par
elle contre `git log --date=short` sur les onze commits du second tour de correction, tandis que des
commentaires ecrits dans les paquets par d'autres agents portent le 2026-09-01 pour le meme travail.
Je date cet amendement du 2026-08-30, sur la meme verification, et je n'ai trouve cette date fautive
dans aucun document que j'ai ecrit (voir `docs/backlog/backlog.md`, section "Sprint 23").

**Ce que ce mouvement fait, et pas plus.** Il ne fait bouger aucune ligne au-dela de celle nommee.
`cm1-problemes-durees` et `cp-ranger-nombres` restent hors du perimetre de ce reaudit.

**Ce que ce document ne fait pas.** Il ne prononce aucun Done pedagogique lui-meme et ne fait
bouger aucune autre ligne que celle nommee ci-dessus.

## Note du 2026-08-30 (ouverture s24, `camille-po`) - trois lignes passent `en cours`, aucune n'atteint `couvert-valide` par ce planning ; la mecanique de saisie ouvre sans debloquer aucune ligne

L'utilisatrice arbitre l'ouverture de s24 (*"ouvre s24 avec les trois paquets et la saisie"*).
Perimetre complet, motifs et questions ouvertes : `DEC-0059`. Stories et criteres :
`docs/backlog/backlog.md`, section "Backlog - perimetre s24".

**Trois lignes changent de colonne, toutes de `non couvert` vers `en cours`** - detail et mentions de
couverture partielle exigees par `nadia-prof` : les deux notes datees du 2026-08-30 ajoutees sous les
tableaux "Mathematiques - CM1" et "Francais - CP" ci-dessus, que je ne recopie pas ici.

- **"Comprendre un texte narratif tres court (questions explicites : qui, ou, quoi)"** (francais CP,
  `S24-NAR`) : `non couvert` -> `en cours`.
- **"Reconnaitre et utiliser les marques du pluriel les plus frequentes (-s)"** (francais CP,
  `S24-PLU`) : `non couvert` -> `en cours`.
- **"Notion de fraction simple (unites fractionnaires, fractions inferieures a 1) et reperage sur une
  droite numerique"** (mathematiques CM1, `S24-FRA`, moitie "notion" seule) : `non couvert` ->
  `en cours`.

| Matiere / niveau | Nombre de competences | non couvert | en cours | couvert-valide | non couvrable en l'etat |
|---|---|---|---|---|---|
| mathematiques CP | 11 | 5 | 0 | 6 | 0 |
| mathematiques CM1 | 12 | 2 | 1 | 7 | 2 |
| francais CP | 12 | 5 | 3 | 3 | 1 |
| francais CM1 | 12 | 3 | 0 | 8 | 1 |
| **Total** | **47** | **15** | **4** | **24** | **4** |

**Le chiffre, avec sa base, verifie ligne par ligne contre les quatre tableaux de matiere de cette
matrice avant d'etre ecrit (2026-08-30), pas repris d'un autre document du depot.** Le denominateur
atteignable reste **43** sur **47**, inchange. **Le compte de lignes `couvert-valide` ne bouge pas :
24/43 (55,8 %) reste 24/43 (55,8 %).** Un passage a `en cours` est un geste de planning, jamais un
mouvement de couverture, et je l'ecris pour qu'aucun rapport d'ouverture ne le presente autrement.

**Ce que ce perimetre pourrait rendre a la cloture, chiffre honnete et non acquis** : si
`nadia-prof` prononce trois Done pedagogiques sur les trois paquets, **24/43 (55,8 %) -> 27/43
(62,8 %)**, un gain de 7,0 points. Ce chiffre est **entierement conditionne** a des audits qui
n'existent pas, sur des paquets qui n'existent pas, et l'historique du produit ne le rend pas
probable a taux plein : a s23, trois paquets ouverts ont donne trois lignes, mais apres deux refus,
trois tours de correction et cinq reaudits.

**La quatrieme ligne `en cours` du tableau ci-dessus n'est pas neuve** : c'est "Associer les
graphemes aux phonemes" (francais CP), `en cours` depuis le sprint 3, inchangee par ce planning.

## Ce que ce planning ouvre et qui ne touche aucune ligne : la mecanique de saisie

`DEC-0059` ouvre aussi la **mecanique de saisie** (`S24-SAI-1`, `S24-SAI-2`), instruite depuis s19 et
jamais implementee. **Elle ne debloque aucune ligne de cette matrice, et ce fait est verifie ligne par
ligne, pas suppose** : les 18 lignes `non couvert` d'avant ce planning ont ete confrontees une par
une aux six refus prealables `R1` a `R6` de `nadia-prof`
(`squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md`) et a la separation d'avec
l'operation posee. Le tableau complet est en section 5.1 de `DEC-0059` ; ce qu'il faut retenir ici,
pour qu'un lecteur de cette matrice ne cherche pas un mouvement qui n'existera pas :

- `R1` ferme "Copier un mot, une phrase courte" ; `R2` ferme les deux lignes de comprehension et de
  restitution ; `R3` ferme "Rediger un texte narratif court" ; `R6` (2026-08-25) ferme "Ecrire de
  maniere autonome un mot simple", que `nadia-prof` retire elle-meme de la liste des candidats.
- Les deux lignes d'operation posee relevent d'une **troisieme** mecanique et jamais de la saisie -
  `theo-archi` et `nadia-prof` le disent separement, et elle demande de ne jamais les compter
  ensemble dans un arbitrage de sequencement.
- Le seul usage qu'elle recommande positivement - la reponse numerique courte sur des paquets de
  calcul **deja `couvert-valide`** - **n'est pas de la couverture neuve** : elle l'ecrit elle-meme,
  *"c'est une amelioration d'existant"*.

**Aucune ligne de cette matrice ne change de statut par ce lot, ni a l'ouverture ni a la cloture.**

## Ce que cette note ne fait pas

Elle ne prononce aucun Done pedagogique, n'en anticipe aucun, et ne fait bouger aucune ligne au-dela
des trois nommees ci-dessus. `SUB` ("Reperer les substituts d'un personnage", francais CM1) reste
`non couvert` : elle est arbitree et developpable, mais la regle de composition de `nadia-prof` (au
plus un paquet du type `COMP-2` par sprint) interdit de la prendre avec `S24-NAR`. **C'est un report,
compte comme le premier** (`DEC-0059`, section 2). `DEN` (moitie "lire, ecrire, decomposer" de
"Denombrer... jusqu'a 100", mathematiques CP) reste `non couvert` : elle est un quatrieme paquet
**conditionnel**, non engage, et ne ferait de toute facon pas bouger sa ligne au-dela d'`en cours`
par la condition `DEN-2` de `nadia-prof`.

---

# Synthese apres les verdicts du sprint 24 (2026-08-31, cloture)

Ecrite par `camille-po`, seule proprietaire de ce fichier (`squad/README.md`). `sofia-sm` a recompte
et **a refuse de l'ecrire**, ce qui est la bonne conduite : le comptage se verifie a plusieurs, le
fichier a une main.

## Le ratio, recompte par moi et non repris

**27/43 = 62,8 %.**

**Methode, nommee pour etre refaite par un tiers** : comptage de la colonne Statut des **quatre
tableaux de matiere** de ce fichier - Mathematiques CP, Mathematiques CM1, Francais CP, Francais CM1 -
sur l'arbre issu de la fusion de `s24-sofia-review` (`0cc4724`), **apres** l'ecriture des quatre
lignes ci-dessous et non avant. Sont exclues du comptage les lignes d'en-tete et de separation des
tableaux, et les tableaux de synthese historiques de ce fichier, qui ne sont pas des tableaux de
matiere. Une ligne portant `couvert-valide` est comptee dans ce statut meme quand la cellule porte en
plus une mention de couverture partielle.

| | Lignes | `couvert-valide` | `en cours` | `non couvert` | `non couvrable en l'etat` |
|---|---|---|---|---|---|
| Mathematiques CP | 11 | 6 | 0 | 5 | 0 |
| Mathematiques CM1 | 12 | 8 | 0 | 2 | 2 |
| Francais CP | 12 | 5 | 1 | 5 | 1 |
| Francais CM1 | 12 | 8 | 0 | 3 | 1 |
| **Total** | **47** | **27** | **1** | **15** | **4** |

Denominateur atteignable : **47 - 4 = 43**. **27/43 = 62,8 %**, contre 24/43 = 55,8 % a l'ouverture
de s24. **Mon compte est identique a celui de `sofia-sm`**, etabli separement.

**La zone la plus creuse du produit etait la cible, et elle a bouge** : le francais CP passe de
**3/11 (27,3 %)** a **5/11 (45,5 %)** d'atteignable. C'est le motif ecrit de `DEC-0059`, section 2,
pour avoir prefere `NAR` a `SUB` ; il se verifie.

## Les quatre lignes ecrites, et leur verdict source

Les quatre verdicts sont de `nadia-prof`, tous prononces apres avoir joue. **Je les ai lus dans les
fiches avant d'ecrire, plutot que de les reprendre d'un rapport.**

| Ligne | Jeu | Verdict, et ou je l'ai lu |
|---|---|---|
| Marques du pluriel en `-s` (francais CP) | `bandit-manchot-pluriel-cp` | **VALIDE**, `FICHE-bandit-manchot-pluriel-cp.md` |
| Comprendre un texte narratif tres court (francais CP) | `bandit-manchot-texte-narratif-cp` | **VALIDE**, reserve 1 levee au second passage, `FICHE-bandit-manchot-texte-narratif-cp.md` |
| Notion de fraction simple (mathematiques CM1) | `bandit-manchot-fractions-cm1` | **VALIDE**, reserve 1 levee au second passage, `FICHE-bandit-manchot-fractions-cm1.md` |
| Complements a 10 (mathematiques CP) - **deja verte, ne bouge pas** | `saisie-complements-10-cp` | **VALIDE**, refus du 2026-08-31 leve, `FICHE-saisie-complements-10-cp.md` |

**La mention de `FRA-9` est portee telle quelle et ce n'est pas une remarque, c'est une condition** :
la ligne des fractions dirait plus que ce que le jeu sert sans elle. Elle est recopiee mot pour mot
depuis la fiche, sans etre resumee.

**La ligne "Complements a 10" porte desormais deux jeux et garde son statut.** `CPL-1` :
*"deux entrees sur une meme ligne ne sont pas une double couverture"*. Je nomme le second jeu plutot
que de le taire - la colonne "Jeu(x) couvrant" est plurielle par construction, et une matrice qui
cacherait un jeu livre dirait moins que ce qui existe - **mais le ratio n'en tire rien.** Le lot de
saisie de s24 ajoute **zero ligne**, ce que j'avais etabli sur 18 lignes, que `nadia-prof` a etendu a
23, et qui se verifie ici pour la troisieme fois.

## Ce que le lot de saisie apporte, et qu'aucune colonne de ce fichier ne mesure

Ce fichier compte des lignes de programme. Il ne compte pas ceci, et `nadia-prof` demande que ce soit
ecrit la ou on lit la couverture :

> **Le catalogue contient desormais un jeu sur trente et un ou un enfant qui ne sait pas peut
> echouer.**

Sur les 582 items du catalogue qui portent deux ou trois propositions - 94,6 % des 615 items
enumeres, chiffre et methode de `nadia-prof` du 2026-08-30 - un enfant qui presse au hasard a
necessairement raison au dernier essai. **Un compteur de couverture ne verra jamais cette
difference-la.** Elle est instruite par `DEC-0061`, candidate de tete a l'ouverture de s25.

## La mention datee que `DEC-0063` prevoyait n'est pas ecrite, et c'est `nadia-prof` qui l'a rendue sans objet

`DEC-0063`, section 3, prevoyait qu'une ligne dont le jeu devient modifie par un changement de code
partage garde `couvert-valide` **et recoive une mention** *"reaudit du au titre de `DEC-0060`, `Q1`"*.
Sous `Q1`, les lots 1 et 3 de s24 rendaient bien les 27 jeux modifies.

**Cette mention n'est pas portee, parce qu'elle serait fausse.** `nadia-prof` a prononce le
2026-08-31, apres avoir reverifie elle-meme la garde et le corps :

> *"Les lots 3 et 4 sont discharges sous R-C. Les 27 jeux existants ne sont pas rejoues, et le chemin
> C est ecarte pour s24."*
> `squad/conformite/CLAUSE-PORTE-PEDAGOGIQUE-perimetre-apres-les-six-reponses-du-2026-08-31.md`,
> section 12.

**Aucun reaudit n'est donc du sur les 27, et aucune ligne ne porte de dette.** `DEC-0063` avait ecrit
que sa clause gouvernait la porte et que la matrice suivrait ; c'est ce qui se produit, dans l'autre
sens que celui que j'avais anticipe. **Le detail est dans l'amendement du 2026-08-31 a `DEC-0063`.**

**Ce que cette decharge ne cree pas, et `nadia-prof` l'ecrit** : aucune presomption pour le prochain
lot partage. Chaque lot est instruit pour lui-meme. **Un lot qui modifierait un corps qui s'execute
ferait rentrer les 27 dans la porte**, et ce jour-la la mention de `DEC-0063` sera due.

## Ce que cette synthese ne fait pas

- Elle **ne prononce aucun Done pedagogique** : les quatre verdicts sont de `nadia-prof` et je n'ai
  fait que les lire et les reporter.
- Elle **ne fait bouger aucune ligne au-dela des trois nommees**, et n'en retire aucune.
- La ligne **"Associer les graphemes aux phonemes"** reste `en cours` : c'est la derniere de ce
  statut, ouverte depuis s03, et rien de s24 ne la touche.
- `SUB` reste `non couvert` et son report reste **le premier**, compte a `DEC-0059`, section 2. Le
  motif de composition qui l'a ecartee - au plus un paquet du type `COMP-2` par sprint - a ete
  consomme par `NAR`, qui est desormais verte. **Le motif ne vaut plus pour s25.**

## Perimetre de s25 (2026-08-31) - le plafond calcule a s22 est atteint, et ce que cette matrice peut encore bouger

`DEC-0065` ouvre s25 sur l'objectif de l'utilisatrice : **"la plus grande couverture"**. Cette
section dit ce que cette matrice peut en attendre. **Elle ne deplace aucune ligne.**

### Le fait, avec sa methode

**Comptage automatise des quatre tableaux ci-dessus, colonne `Statut`, aucun module compile - la
question du `-am` ne se pose donc pas pour cette mesure** : 27 `couvert-valide`, 15 `non couvert`,
1 `en cours`, 4 `non couvrable en l'etat`, total 47. Denominateur 47 - 4 = 43. **27/43 = 62,8 %.**

**Ce chiffre est exactement celui que `DEC-0053`, section 7.1, avait calcule le 2026-08-28 comme le
"plafond realiste a court terme, ecartant l'audio et le visuel"** : *"18 + 9 = 27/43 (62,8 %) au
mieux, sans construire quoi que ce soit de neuf"*. Des 9 lignes de contenu seul qui composaient ce
plafond, **8 sont vertes et `SUB` est la neuvieme**. Le produit est a 27 et non a 26 parce que `FRA`
a rapporte une ligne qui n'etait pas dans ces 9 - la moitie *"notion"* des fractions, classee
visuelle a s22 et redirigee en contenu seul par l'arbitrage 8bis du 2026-08-30.

**Le mur de la mecanique unique, pose a l'utilisatrice par `DEC-0057`, est atteint a la date prevue.
s25 est le premier sprint qui se tient dessus.**

### Ce que s25 peut faire bouger, et rien de plus

| Ligne | Ce qui la ferait bouger a s25 | Plafond |
|---|---|---|
| **Reperer les substituts d'un personnage** (francais CM1) | **Rien a attendre.** Arbitree `SUB-1` a `SUB-10` le 2026-08-30, contenu seul, mecanisme existant. `S25-SUB-1` | **28/43 (65,1 %)** |
| **Reconnaitre et utiliser la monnaie** (maths CP), moitie *"utiliser"* | Un arbitrage de `nadia-prof` **qui n'existe pas** : une moitie textuelle d'une ligne classee visuelle peut-elle porter sa ligne au vert avec une mention de couverture partielle nommee ? `S25-MON-1` | **29/43** si oui |
| **Se reperer dans la journee, la semaine** (maths CP), premiere moitie de la ligne, separee par un point-virgule dans le referentiel | Meme question, meme reponse attendue. `S25-JOU-1` | **30/43 (69,8 %)** si oui pour les deux |
| **Denombrer, lire, ecrire et decomposer jusqu'a 100** (maths CP) | `S25-DEN-1` la ferait passer `non couvert` -> `en cours`. **Elle ne peut pas devenir `couvert-valide`** : `DEN-2`, reconfirmee le 2026-08-30 et declaree non negociable | inchange |

**La question de la ligne 2 et de la ligne 3 est la seule qui puisse elargir ce plafond a s25, et
elle est entierement a `nadia-prof`.** Deux precedents de sa main s'opposent : `FRA-9` et `LIA-10`
ont fait passer une ligne au vert avec une mention qu'elle a dictee ; `DEN-2` maintient `en cours`.
**Rien dans ces trois textes ne dit ce qui separe les deux cas, et l'inventer serait exactement ce
que ma charte m'interdit.** `DEC-0065`, section 5.1.

**Aucune des 12 autres lignes hors `couvert-valide` ne peut atteindre `couvert-valide` a s25** :
4 sont visuelles sans aucune architecture ecrite (dont 3 sans arbitrage individuel), 4 sont audio et
bloquees sur une source de voix humaine que seule l'utilisatrice peut trancher, 2 demandent une
troisieme mecanique jamais instruite, 4 butent sur une contrainte fondatrice de ce produit.
L'instruction ligne par ligne est dans
`squad/programme/INSTRUCTION-s25-les-16-lignes-hors-couvert-valide.md`.

### Quatre lignes qui gonflent le denominateur sans qu'aucun sprint puisse les atteindre

*"Copier un mot, une phrase courte"* (`R1`, graphomotrice), *"Ecrire de maniere autonome un mot
simple"* (`R6`, l'appareil produit les lettres), *"Lire silencieusement [...] et en restituer
l'essentiel"* et *"Rediger un texte narratif court"* (production libre, jugement humain) portent le
statut `non couvert` alors qu'aucune capacite de developpement ne les ouvrira.

**Les reclasser `non couvrable en l'etat` ferait passer le denominateur de 43 a 39 et la couverture
affichee de 62,8 % a 69,2 % sans qu'une ligne de code soit ecrite. C'est precisement pourquoi je ne
le fais pas dans le sprint ou l'utilisatrice me demande de maximiser cet indicateur** : la question
part a `nadia-prof` pour le motif et a l'utilisatrice pour l'arbitrage, avec le gain d'affichage
nomme dans la question. `DEC-0065`, section 5.4.

### Ce que cette section ne fait pas

- Elle **ne deplace aucune ligne**, n'en ajoute aucune, n'en retire aucune.
- Elle **ne prononce aucun Done** et n'en anticipe aucun. Les plafonds ci-dessus supposent qu'un
  paquet ecrit dans le sprint passe la porte du premier coup, ce qui n'est jamais arrive sur ce
  produit : a s23, trois paquets ouverts ont donne trois lignes, mais apres deux refus, trois tours
  de correction et cinq reaudits.
- Elle **ne reclasse pas les quatre lignes ci-dessus** et ne prejuge pas de la reponse.
- `SUB` **n'est pas reportee une seconde fois** : elle est au rang 1 de `DEC-0065`. Le report unique
  compte a `DEC-0059`, section 2, reste le premier et le seul.

## Amendement du 2026-08-31 (meme jour, apres deux arbitrages de l'utilisatrice) - la section precedente est corrigee sur deux points, et le denominateur change

**La section ci-dessus a ete ecrite le matin du 2026-08-31, avant l'arbitrage prealable de
`nadia-prof` et avant deux arbitrages de l'utilisatrice. Elle reste lisible telle quelle** - ce
depot n'efface pas - **et elle est fausse sur deux points que ce bloc corrige.**

### 1. Ce que la section precedente disait de faux

| Ce que j'ai ecrit le matin | Ce qui est vrai le soir | Source |
|---|---|---|
| La moitie *"utiliser"* de la **monnaie** pouvait porter sa ligne au vert | **REFUSE.** *"Sur les huit items, sept sont un jeu deja valide avec un symbole d'euro colle au resultat. Un enfant qui joue ce paquet ne travaille pas la monnaie ; il refait du calcul."* Et *reconnaitre* fonde *utiliser* : la ligne resterait `en cours` meme avec un bon paquet textuel | `nadia-prof`, arbitrage prealable de s25, section 2.2, `MON-1` a `MON-5` |
| *"Lire silencieusement [...] et en restituer l'essentiel"* butait sur une production libre et devait etre reclassee | **OUVRABLE**, `LIS-1` a `LIS-10`, sous une mesure de pli due a `ines-design`. J'avais lu *"restituer l'essentiel"* comme *"produire un resume"* ; le geste est servable par le choix | meme document, section 9.1 ; `DEC-0066`, section 2.3 |

**Un troisieme point n'etait pas faux mais etait incomplet** : la moitie *"se reperer dans la
journee, la semaine"* est bien ouvrable, et `nadia-prof` lui a donne ses conditions `SEM-1` a
`SEM-9`. **C'est le seul candidat neuf de contenu seul du sprint.**

### 2. Le denominateur change - les deux taux, avec leur date

`DEC-0066` applique l'arbitrage de l'utilisatrice du 2026-08-31 (*"Reclasser, taux revu"*) : **deux
lignes sortent du denominateur, deux candidates n'y satisfaisaient pas et y restent.**

| Date | Vertes | Denominateur | Taux |
|---|---|---|---|
| **2026-08-31, avant `DEC-0066`** | **27** | **43** | **62,8 %** |
| **2026-08-31, apres `DEC-0066`** | **27** | **41** | **65,9 %** |

> **Le numerateur n'a pas bouge. Le saut de 3,1 points est un changement de denominateur, pas un
> progres.** Toute lecture de la serie de couverture de ce produit doit **couper la serie au
> 2026-08-31** : avant, un pourcentage sur 43 ; apres, un pourcentage sur 41. Les deux ne se
> comparent pas directement.

*Methode : comptage automatise des quatre tableaux de matiere de ce fichier, colonne `Statut`, sur
ce seul fichier, aucun module compile - la question du `-am` ne se pose pas. Apres : 27
`couvert-valide`, 13 `non couvert`, 1 `en cours`, 6 `non couvrable en l'etat`, total 47.*

**Les deux lignes qui ne sont pas sorties, et pourquoi** (`DEC-0066`, sections 2.3 et 2.4) :
*"Lire silencieusement, restituer l'essentiel"* est **ouvrable** depuis le 2026-08-31 - la
reclasser aurait retire une ligne du produit et non de l'affichage ; *"Ecrire de maniere autonome un
mot simple"* attend **l'audio**, qui est un obstacle de production que l'utilisatrice peut lever par
une reponse, et non une contrainte fondatrice.

### 3. Ce que s25 peut faire bouger, corrige

| Ligne | Ce qui la ferait bouger | Cout en paquets |
|---|---|---|
| **Se reperer dans la journee, la semaine ; lire l'heure pleine** (maths CP) | `SEM-1` a `SEM-9`, contenu seul. **Vert avec mention** : *"moitie 'se reperer dans la journee, la semaine' seule ; la moitie 'lire l'heure pleine sur une horloge' reste non couverte"* | 1 |
| **Reperer les substituts d'un personnage** (francais CM1) | `SUB-1` a `SUB-10`, rien a attendre | 1 |
| **Lire silencieusement, restituer l'essentiel** (francais CM1) | `LIS-1` a `LIS-10`, **plus une mesure de pli d'`ines-design` due avant l'ecriture du paquet** | 1 |
| **Reconnaitre et nommer quelques figures planes** (maths CP) | `FIG-1` a `FIG-4` **plus** un rendu d'image reel - premier palier de la famille visuelle, ouvert par l'utilisatrice le 2026-08-31 | 1 paquet + une mecanique neuve |
| **Reconnaitre et utiliser la monnaie** (maths CP) | `MON-1` a `MON-5`, **image obligatoire** : les pieces sont montrees, pas nommees | **2 paquets** (visuel + textuel) |

**`LIS` et `SUB` ne tiennent pas dans le meme sprint** : `LIS-6` et `SUB-6` portent toutes deux la
propriete du type de `COMP-2`, et `nadia-prof` n'en accepte qu'une par sprint. **Le maximum de s25
sans mecanique neuve est donc de deux lignes, soit 29 sur 41 (70,7 %)** - son chiffre, corrige par
elle contre son propre tableau, transpose au denominateur de `DEC-0066`.

**Une interaction de comptage a ne pas rater** : la ligne de l'heure **ne compte qu'une fois**. `SEM`
la fait passer au vert avec mention ; le palier visuel ne ferait ensuite que **lever la mention**.
Un sprint qui prend `SEM` **et** le palier 1 ne gagne pas deux lignes sur cet objet.

### 4. Ce que cet amendement ne fait pas

- Il **ne fait passer aucune ligne a `couvert-valide`** et n'en retire aucune du vert.
- Il **ne reecrit pas la section precedente**, qui reste lisible avec sa date et son erreur.
- Il **ne presente le nouveau taux comme un progres a aucun endroit**.
- La **symetrie axiale CM1** reste `non couvrable en l'etat` : `nadia-prof` a rendu le 2026-08-31 le
  jugement que j'attendais depuis le 2026-08-25 et qu'elle refuse - *"reconnaitre une symetrie et
  l'utiliser pour completer une figure [...] c'est le meme objet a deux degres, et le degre servi
  serait le plus facile"*.

### 5. Correction du 2026-08-31 (meme jour, troisieme etat) - la monnaie rouvre, le tableau de la section 3 ci-dessus est faux sur une ligne

Le tableau de la section 3 porte *"`MON-1` a `MON-5`, image obligatoire [...] **2 paquets** (visuel +
textuel)"*. **Ce chiffre est faux et son autrice l'a corrige** (`nadia-prof`, amendement du
2026-08-31 a son arbitrage des seize lignes, section 12) : **un paquet, ligne entiere** - *"le paquet
sert reconnaitre par l'image et utiliser par la question"*. Sa methode ne fait appel a rien
d'exterieur : compter deux paquets revenait a compter comme second **celui qu'elle venait de refuser
trois pages plus haut**.

**Ce que cela change a cette matrice** : rien aujourd'hui, aucune ligne ne bouge. Ce que cela change
au plafond de s25 est dans `DEC-0065`, second amendement : **quatre paquets, quatre lignes
distinctes, 31 sur 41 (75,6 %)** au lieu de 30 sur 41.

**Le piege de comptage, recompte par `sofia-sm` sur les onze lignes du tableau *Mathematiques - CP***
et que cette matrice doit porter parce qu'il se reproduira : *"Se reperer dans la journee, la
semaine ; lire l'heure pleine sur une horloge"* est **une seule ligne**, que le point-virgule coupe
en deux moities. `SEM` la fait passer au vert par une moitie, `HOR` par l'autre. **Prendre les deux
dans le meme sprint est excellent pour l'enfant et ne rapporte qu'une ligne.**

---

## Synthese apres les verdicts du sprint 25 (2026-09-01) - quatre lignes, et deux chiffres qu'il ne faut pas additionner

**Quatre `VALIDE` prononces par `nadia-prof` le 2026-09-01 sur l'arbre de cloture `834c6bc`.** Je les
ai lus et reportes ; **je n'en prononce aucun.**

| Ligne | Jeu | Verdict |
|---|---|---|
| Reconnaitre et nommer quelques figures planes (maths CP) | `bandit-manchot-figures-planes-cp` | **VALIDE**, reserve d'indice levee a la cloture |
| Reconnaitre et utiliser la monnaie (maths CP) | `bandit-manchot-monnaie-cp` | **VALIDE**, reserve de vocabulaire levee a la cloture |
| Se reperer dans la journee, la semaine ; lire l'heure pleine (maths CP) | `bandit-manchot-semaine-cp` | **VALIDE**, avec mention de couverture partielle |
| Reperer les substituts d'un personnage (francais CM1) | `bandit-manchot-substituts-cm1` | **VALIDE**, **second passage** - le refus du 2026-08-31 est leve et reste lisible |

### Le compte, avec sa methode

*Methode : comptage automatise des quatre tableaux de matiere de ce fichier, colonne `Statut`, sur ce
seul fichier, apres report des quatre verdicts. Aucun module compile - la question du `-am` ne se
pose pas pour cette mesure.*

| Statut | Compte |
|---|---|
| `couvert-valide` | **31** |
| `non couvert` | 9 |
| `en cours` | 1 |
| `non couvrable en l'etat` | 6 |
| **Total** | **47** |

**Denominateur atteignable : 47 - 6 = 41. Ratio : 31 / 41 = 75,6 %.**

### Les deux chiffres du sprint, et pourquoi je refuse de les additionner

**C'est le point de cette synthese, et c'est le seul endroit du depot ou la confusion se produirait.**

| Etat | Vertes | Denominateur | Taux | Ce qui a change |
|---|---|---|---|---|
| **Ouverture de s25, 2026-08-31** | 27 | **43** | **62,8 %** | - |
| Apres `DEC-0066`, meme jour | 27 | **41** | 65,9 % | **le denominateur seul** |
| **Cloture de s25, 2026-09-01** | **31** | **41** | **75,6 %** | **le numerateur : quatre lignes** |

> **Le sprint affiche +12,8 points, et ce n'est pas ce qu'il a produit.**
>
> **3,1 points viennent du reclassement de `DEC-0066`** - deux lignes sorties du denominateur sur
> arbitrage de l'utilisatrice, **sans qu'un seul numerateur bouge**. Aucun enfant ne pouvait jouer
> quoi que ce soit de plus le soir du 2026-08-31 que le matin.
>
> **9,7 points viennent des quatre lignes**, et **elles sont le seul progres reel du sprint.**
>
> **Une serie de couverture qui joindrait 62,8 % a 75,6 % en ligne droite mesurerait deux choses
> differentes avec la meme unite.** La serie se coupe au 2026-08-31 : avant, un pourcentage sur 43 ;
> apres, un pourcentage sur 41.

*Le meme avertissement est ecrit dans `DEC-0066`, section 3, et dans l'amendement du 2026-08-31
ci-dessus. C'est la troisieme fois, et c'est ici qu'il servait.*

### Par matiere et par niveau, meme methode, meme perimetre

| Matiere / niveau | `couvert-valide` | atteignable | Part | Variation dans le sprint |
|---|---|---|---|---|
| **mathematiques CP** | **9** | 11 | **81,8 %** | 54,5 % -> 81,8 %, **trois lignes** |
| mathematiques CM1 | 8 | 10 | 80,0 % | inchangee |
| **francais CP** | 5 | **10** | **50,0 %** | 45,5 % -> 50,0 %, **par le seul denominateur** |
| francais CM1 | 9 | 10 | 90,0 % | 72,7 % -> 90,0 %, **une ligne plus le denominateur** |

**Le francais CP reste le point le plus faible du produit et il n'a rien gagne a s25** : ses 4,5
points viennent de la sortie de *"Copier un mot"* du denominateur, pas d'un jeu. **Les quatre lignes
du sprint sont trois en mathematiques CP et une en francais CM1**, c'est-a-dire dans les deux zones
qui etaient deja les mieux servies. C'est la consequence assumee d'un sprint dont l'objectif etait la
couverture et non l'equilibre ; je le porte ici pour que le rang de s26 l'ait sous les yeux.

### Ce que cette synthese ne fait pas

- Elle **ne prononce aucun Done** : les quatre verdicts sont de `nadia-prof`.
- Elle **n'additionne pas** le gain de denominateur et le gain de lignes, et elle nomme les deux.
- Elle **ne reprend aucun chiffre de la porte technique** : la mesure de `1 018` tests est celle de
  `sofia-sm`, `theo-archi`, `yanis-qa` et `nadia-prof`, je ne l'ai pas reproduite, et **aucune ligne
  de cette matrice ne repose dessus** - `nadia-prof` ecrit elle-meme *"aucun verdict fonde sur les
  1 018 tests verts"*.
- Elle **ne touche pas** a la ligne `en cours` des graphemes et phonemes, qui attend de l'audio.

---

## Note du 2026-09-01 (ouverture de s26, `DEC-0068`) - l'obstacle de production de la famille audio est leve, et **aucune ligne ne bouge**

**Aucun statut de cette matrice n'est modifie par cette note. Elle enregistre un fait, elle ne
prejuge d'aucun verdict.**

**Le fait** : la question de la source de la voix, ouverte depuis le 2026-08-24 et routee a chaque
sprint depuis, est tranchee.

> **De l'utilisatrice, 2026-09-01**, ses mots cites entre guillemets :
>
> **"pour les exercices avec la voix, un adulte va faire la lecture a l'enfant de cp il faudra juste
> distinguer ce qui doit etre lu de la consigne de l'enfant"**

**Ce que cela change a ce qui bloquait cinq lignes** : `DEC-0042`, arbitrage 2, avait nomme deux
voies - un corpus enregistre, ou une synthese embarquee - et etabli qu'aucune ne tenait. **La reponse
n'est aucune des deux : il n'y a pas de voix dans le produit.** L'obstacle de production - *"ce n'est
ni du code ni du contenu YAML : c'est un enregistrement"* - **disparait.**

**Ce que cela ne change pas, et c'est pourquoi rien ne bouge ici** :

- **Les cinq lignes concernees restent `non couvert` ou `en cours`** - localiser une syllabe,
  identifier un son, les graphemes et phonemes, decoder par assemblage, et ecrire un mot en autonomie.
  **`nadia-prof` instruit ce que ce moyen ouvre, et son verdict decide.** L'utilisatrice a arbitre un
  **moyen**, pas un resultat.
- **La ligne *"Lire a voix haute un texte court et simple avec fluidite croissante"* reste
  `non couvrable en l'etat`, et son motif est inchange.** Le critere de cette matrice exige une
  decision de l'utilisatrice **sur la contrainte fondatrice en cause** ; celle-ci porte sur la voix de
  l'enfant **recue** par l'application, et l'arbitrage porte sur la voix d'un adulte **emise** dans la
  piece. **Ce ne sont pas deux degres d'une meme chose. Motif complet et adjacence nommee :
  `DEC-0068`, section 5.3.**
- **Le denominateur est inchange : 41.** Aucune ligne n'entre dans le statut `non couvrable en
  l'etat` ni n'en sort.

**Etat de cette matrice a la date de cette note, inchange depuis la cloture de s25** : 31
`couvert-valide`, 9 `non couvert`, 1 `en cours`, 6 `non couvrable en l'etat`, total 47, denominateur
41, **31 / 41 = 75,6 %**. *Meme methode que la synthese de s25 : comptage automatise des quatre
tableaux de matiere de ce fichier, colonne `Statut`, sur ce seul fichier, aucun module compile.*

### Complement du 2026-09-01 (meme jour) - le verdict de `nadia-prof` sur les cinq lignes. **Aucune ligne ne bouge encore.**

La note ci-dessus disait que `nadia-prof` instruisait ce que ce moyen ouvre et que son verdict
deciderait. **Il est rendu** (`ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md`, sections 3 et 4).

**Une seule regle trie les cinq lignes** : *"l'adulte prononce le stimulus ; il ne prononce jamais
une proposition"* (`VOI-1`).

| Ligne | Verdict | Statut aujourd'hui |
|---|---|---|
| Localiser une syllabe dans un mot (fr. CP) | **SERVIE** | `non couvert`, **inchange** |
| Identifier un son (phoneme) dans un mot (fr. CP) | **SERVIE** | `non couvert`, **inchange** |
| Associer les graphemes aux phonemes (fr. CP) | **SERVIE - la ligne peut se fermer** | `en cours`, **inchange** |
| Decoder un mot regulier par assemblage syllabique (fr. CP) | **REFUSEE** - seule ligne dont les propositions sont des **sons** | `non couvert`, **inchange** |
| Ecrire de maniere autonome un mot simple (fr. CP) | **SERVIE** - `SAI-11` se leve, sa condition etant remplie | `non couvert`, **inchange** |

**Aucun statut ne bouge, et c'est normal : aucun paquet n'existe.** Une ligne passe au vert par un
verdict sur un increment joue, jamais par un arbitrage prealable.

**Ce que ce verdict rend au pilotage** : la reserve de lignes arbitrees passe de **une** a **cinq** -
`LIS` plus les quatre - et le plafond, si les quatre sont servies **et validees**, est **35 / 41 =
85,4 %**. *Meme methode de comptage que la synthese de s25. Detail, reserves et compte de paquets :
`DEC-0067`, amendement, sections B.2 a B.5.*

**Un fait de repartition que ce tableau rend visible et que le taux global masque** : **les quatre
lignes acceptees sont toutes en francais CP**, qui passerait de **5 / 10 (50,0 %)** a **9 / 10
(90,0 %)**, et le domaine *"Langage oral et conscience phonologique"* - **1 sur 3 depuis le sprint
0** - se fermerait entierement.

**La ligne refusee reste `non couvert` et n'est pas reclassee** : `nadia-prof` ecrit *"je n'oppose pas
un principe, je constate une impasse"* et nomme deux voies qui la debloqueraient. **Le critere de
`non couvrable en l'etat` n'est pas rempli.**

### Complement du 2026-09-01 (meme jour, second passage) - cinq paquets, pas quatre ; cinq stories ouvertes ; conditionnelles a `theo-archi`

Le complement ci-dessus disait que la reserve de lignes arbitrees passait a cinq et que le plafond,
si les quatre etaient servies et validees, etait 35/41 = 85,4 %. Deux precisions s'y ajoutent,
mesurees par `nadia-prof` le meme jour sur le materiel reel plutot que sur l'idee du contenu
(`ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md`, addendum, sections 11 a 15).

**La ligne des graphemes exige deux paquets, pas un** : le total du sprint est **cinq paquets pour
quatre lignes**, exactement la borne haute de `nadia-prof`, sans marge. Le pilotage complet - les
cinq stories, les criteres, les quatre conditions non gardees, et la reponse desormais tranchee de
`theo-archi` (`ADR-0075` : champ d'item, 5 paquets, 4 lignes, **85,4 %** ; la branche mecanique ne
s'est pas realisee) - vit dans `docs/backlog/backlog.md`, section "Backlog - perimetre s26", et
dans la note ajoutee ce jour sous le tableau "Francais - CP" ci-dessus. Je ne le duplique pas ici
une troisieme fois.

**Aucun statut ne bouge par cette mesure** : les quatre lignes passent `en cours` par les cinq
stories ouvertes (note ci-dessus, sous le tableau), pas par ce complement. Le denominateur reste
**41**.

### Complement du 2026-09-01 (meme jour, troisieme passage) - `theo-archi` tranche : champ d'item, plus deux faits sur `ADR-0032` et sur `cp-le-bon-son.yaml`

Le complement ci-dessus portait un tableau conditionnel a la reponse de `theo-archi` sur `ADR-0073`
(champ d'item ou mecanique neuve). **Sa reponse est rendue : c'est un champ d'item** (`ADR-0075`,
sonde de quatre paquets reels). **La branche haute se realise : cinq paquets, quatre lignes sur
quatre, plafond 35/41 = 85,4 %. La branche "mecanique" (34/41 = 82,9 %) est morte** et n'est plus
une lecture possible de ce sprint - je la retire de ce fichier partout ou elle figurait comme un
cas encore ouvert.

**Deux faits que sa sonde a trouves, sans faire bouger le plafond ni aucun statut** :

- `ADR-0032` (identite d'un tour) est amendee sur son critere par `ADR-0075` : la condition
  `VOI-13` retire de l'ecran de l'enfant la matiere qui distinguait deux tours, et c'est une
  garantie technique, pas un critere de contenu. **Aucun critere pedagogique de ce fichier ni de
  `docs/backlog/backlog.md` n'en dependait.**
- Le fichier `adapters/adapter-content/src/main/resources/contenu/cp-le-bon-son.yaml` **ne doit
  pas etre touche** par les deux paquets de graphemes : `theo-archi` chiffre le cout d'un geste de
  forme sur ce fichier a **15 items sur la borne de 115** de `nadia-prof`, pour rien - la borne
  etant deja au plafond, sans marge.

**Aucun statut ne bouge par ce complement.** Detail complet, criteres corriges :
`docs/backlog/backlog.md`, section "Backlog - perimetre s26".

---

## Synthese apres les verdicts du sprint 26 (2026-09-01, cloture) - trois lignes, un domaine ferme, un plafond manque

**Le Done pedagogique est prononce sur les cinq paquets** (`squad/conformite/ARBITRAGE-PREALABLE-
s26-la-voix-est-un-adulte.md`, section 18 ; les cinq fiches `FICHE-*.md` portent leur second
verdict). **VALIDE sur les cinq. Trois lignes se ferment, pas quatre** - detail et motif ecrit sur
la ligne des graphemes dans le tableau "Francais - CP" ci-dessus, et dans la note de cloture qui
suit ce tableau.

| Ligne | Jeu | Verdict de `nadia-prof` | Statut de matrice |
|---|---|---|---|
| Localiser une syllabe dans un mot | `bandit-manchot-position-syllabe-cp` | **VALIDE** | **couvert-valide** |
| Identifier un son (phoneme) dans un mot | `bandit-manchot-son-dans-mot-cp` | **VALIDE** | **couvert-valide** |
| Associer les graphemes aux phonemes | `bandit-manchot-lettre-du-son-cp`, `bandit-manchot-digraphe-du-son-cp` | **VALIDE sur les deux** | **reste `en cours`** - il manque un paquet de voyelles simples (clause 1 de la barre du 2026-08-10) |
| Ecrire de maniere autonome un mot simple | `saisie-mot-dicte-cp` | **VALIDE, sans reserve** | **couvert-valide** |

### Le compte, avec sa methode

*Methode : comptage automatise des quatre tableaux de matiere de ce fichier, colonne `Statut`,
sur ce seul fichier, apres report des trois verdicts qui bougent un statut. Aucun module compile -
la question du `-am` ne se pose pas pour cette mesure.*

| Statut | Avant s26 (cloture s25) | Apres s26 |
|---|---|---|
| `couvert-valide` | 31 | **34** |
| `non couvert` | 9 | **6** |
| `en cours` | 1 | 1 (inchangee - ligne des graphemes) |
| `non couvrable en l'etat` | 6 | 6 |
| **Total** | **47** | **47** |

**Denominateur atteignable : 47 - 6 = 41, inchange. Ratio : 34 / 41 = 82,9 %** (contre 75,6 % a
la cloture de s25, soit **+7,3 points, entierement des trois lignes livrees et validees** - aucun
mouvement de denominateur cette fois).

### Le plafond que j'avais annonce n'est pas atteint - et pourquoi

**Ouverture de s26, j'avais ecrit : "les quatre lignes ferment entierement, plafond 35/41 =
85,4 %."** Ce n'est pas ce qui s'est produit. Le motif n'est ni un echec technique, ni un refus
pedagogique - c'est une **barre pedagogique que mon compte n'integrait pas**. `nadia-prof` a rendu
`bandit-manchot-lettre-du-son-cp` et `bandit-manchot-digraphe-du-son-cp` chacun VALIDE, et pourtant
la ligne des graphemes reste `en cours` : sa clause 1 du 2026-08-10 exige les voyelles simples **et**
les consonnes simples, et le contenu servi ne sert que six consonnes. Le Done porte sur
l'increment ; la matrice porte sur la couverture d'une competence. J'avais compte des paquets
livres contre des lignes de referentiel sans reserver la place d'un jugement de contenu qui restait
a rendre - c'est le meme defaut que j'ai verse contre moi a la cloture de s25 : une enumeration qui
ne se declare pas partielle se lit comme complete. **34/41 = 82,9 %, pas 35/41 = 85,4 %.**

**Precision de `nadia-prof` a porter explicitement** : ce 82,9 % n'a **aucun rapport** avec celui
qu'elle avait price plus haut dans ce fichier pour la branche "mecanique" de `theo-archi` (5
paquets/4 lignes/85,4 % contre 4 paquets/3 lignes/82,9 %, complement du 2026-09-01, troisieme
passage). Cette branche-la decrivait un choix d'architecture qui n'a pas ete pris (`ADR-0075`
tranche "champ d'item"). Celle-ci decrit une ligne de matrice qui reste `en cours` faute de
voyelles simples servies dans le contenu livre. **Meme nombre, autre cause** - je le nomme pour
qu'aucun des deux ne soit recopie a la place de l'autre.

### Un domaine se ferme - le premier du produit

Le domaine **"Langage oral et conscience phonologique"** (francais CP) passe de **1/3 a 3/3** et
se ferme entierement : denombrer les syllabes (deja acquis), localiser une syllabe et identifier
un son (les deux lignes de ce sprint). C'est le premier domaine ferme du produit.

### Francais CP, recompte

| Matiere / niveau | `couvert-valide` | atteignable | Part | Avant s26 |
|---|---|---|---|---|
| **francais CP** | **8** | 10 | **80,0 %** | 50,0 % |

Le francais CP reste le domaine le moins couvert des quatre (mathematiques CP 81,8 %,
mathematiques CM1 80,0 %, francais CM1 90,0 %), mais c'est le seul a avoir gagne des lignes
reelles ce sprint, contre un gain de seul denominateur au sprint precedent.

### Un paquet ferme la derniere ligne, `nadia-prof` le dit d'avance

Ses clauses 2, 3 et 4 (digraphes `ou`/`oi`/`ch`, nasales inchangees, increment joue) sont
desormais toutes remplies. **Un seul paquet de voyelles simples couvrant les voyelles simples en
bonne reponse ferme la ligne des graphemes** - candidat direct pour s27, motif arithmetique plutot
que priorisation, voir la section "Rang recommande pour s27" ci-dessous.

### Ce que ce Done ne referme pas

**La dette du JavaScript est fermee ce sprint** (`ADR-0074`, trois porteurs), mais elle ne touche
aucune ligne de cette matrice - c'est un chantier de qualite technique, pas de couverture
pedagogique, et je ne la compte dans aucun des chiffres ci-dessus.

**La liste des conditions pedagogiques sans garde executable ne diminue pas d'une unite** :
`VOI-3`, `VOI-5`, `VOI-8`, et la moitie non calculable de `VOI-13` restent verifiables par
`nadia-prof` en jouant, jamais par un test. Aucun des cinq jeux VALIDE de ce sprint n'en est
dispense.

### Ce que cette synthese ne fait pas

- Elle **ne prononce aucun Done** : les cinq verdicts sont de `nadia-prof`.
- Elle **n'additionne pas** la fermeture de domaine et le gain de lignes : ce sont deux lectures
  du meme fait, pas deux gains distincts.
- Elle **ne compte pas** la fermeture de la dette JavaScript dans le taux de couverture : cette
  dette ne portait sur aucune ligne de cette matrice.

## Rang recommande pour s27

**Fait arithmetique, pas priorisation** : la ligne "Associer les graphemes aux phonemes" est a un
seul paquet de se fermer, sur un motif que `nadia-prof` a ecrit d'avance et non a decouvrir - un
paquet de voyelles simples (`a, e, i, o, u`, ou un sous-ensemble couvrant au moins une voyelle
simple en bonne reponse sur chaque item), construit sur le meme champ d'item que les quatre
paquets deja livres (`ADR-0075`), sans toucher `cp-le-bon-son.yaml` (cout chiffre a 15 items pour
rien par `theo-archi`, motif deja acquis ce sprint).

**Ce que je recommande** : ouvrir ce sixieme paquet en tete de s27. Le cout de composition est
connu (meme mecanique, meme champ d'item, un seul paquet), le motif de fermeture est ecrit
d'avance par `nadia-prof`, et c'est la seule ligne du domaine phonologique encore ouverte - la
fermer clot un second domaine consecutif.

**Ce que je ne tranche pas, et qui reste a l'utilisatrice** : combien de sprints le francais CP et
les deux lignes `non couvert` restantes de ce domaine (decoder un mot par assemblage syllabique,
refusee ce sprint pour ce moyen ; les deux voies que `nadia-prof` nomme dans son arbitrage
prealable, section 4) doivent attendre derriere ce sixieme paquet, et si la dette residuelle de
`VOI-3`, `VOI-5`, `VOI-8` et `VOI-13` (aucune garde executable, section 18.5 de l'arbitrage) appelle
un chantier propre plutot que de rester une reserve permanente. Ce sont des arbitrages de
priorisation et d'acceptation de risque, pas un calcul de couverture.

## Integration des matieres absentes du 2026-09-02 - le denominateur devient honnete, avant s27

**Sur arbitrage de l'utilisatrice** (2026-09-02, `DEC-0072`) : les 43 lignes de trois matieres
absentes (questionner le monde CP, histoire-geographie CM1, langues vivantes/anglais CP et CM1),
ecrites par `nadia-prof` dans `squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-
programme.md`, entrent dans cette matrice **maintenant**, avant l'ouverture de s27, pour que la
baisse du taux affiche se lise comme une correction de mesure et non comme la consequence d'un
sprint qui n'a encore rien livre. C'est la forme deja appliquee au sprint 19 de ce depot (section
"Synthese a l'ouverture de s19" ci-dessus), reprise ici a une echelle vingt fois plus grande.

### Le fait qui precede le chiffre

Jusqu'a cette date, cette matrice ne portait que deux matieres (mathematiques, francais) sur les
huit enseignements du cycle 2 et les dix du cycle 3 (compte etabli apres coup, voir la note en
tete de ce fichier ; `DEC-0072`). Le 82,9 % cite a la cloture de s26 (`DEC-0069`) etait vrai sur ce
perimetre reduit et ne disait rien des six autres enseignements de cycle 2 ni des huit autres de
cycle 3, faute d'y avoir jamais eu une seule ligne de referentiel.

### Methode de recompte, independante de tout chiffre annonce ailleurs

Comptage ligne a ligne des huit tableaux de matiere desormais presents dans ce fichier, colonne
`Statut`, sans reprendre aucun total d'un autre document. Chaque ligne des 43 nouvelles est
classee `non couvert` ou `non couvrable en l'etat` selon le critere de cette matrice (section
"Comment la lire") applique au jugement de faisabilite ecrit par `nadia-prof` pour cette ligne -
jamais selon un statut `en cours` ou `couvert-valide`, puisqu'aucun jeu n'existe encore pour
aucune des 43.

| Matiere / niveau | Total | `couvert-valide` | `non couvert` | `en cours` | `non couvrable en l'etat` |
|---|---|---|---|---|---|
| mathematiques CP | 11 | 9 | 2 | 0 | 0 |
| mathematiques CM1 | 12 | 8 | 2 | 0 | 2 |
| francais CP | 12 | 8 | 1 | 1 | 2 |
| francais CM1 | 12 | 9 | 1 | 0 | 2 |
| **questionner le monde CP (neuf)** | **16** | **0** | **15** | **0** | **1** |
| **histoire-geographie CM1 (neuf)** | **14** | **0** | **14** | **0** | **0** |
| **langues vivantes (anglais) CP (neuf)** | **6** | **0** | **1** | **0** | **5** |
| **langues vivantes (anglais) CM1 (neuf)** | **7** | **0** | **4** | **0** | **3** |
| **Total** | **90** | **34** | **40** | **1** | **15** |

**Avant** (quatre tableaux) : total 47, non couvrable 6, denominateur atteignable 41, `couvert-
valide` 34. **34 / 41 = 82,9 %.**

**Apres** (huit tableaux) : total 90, non couvrable 15, denominateur atteignable **90 - 15 = 75**,
`couvert-valide` **34, inchange**. **34 / 75 = 45,3 %.**

> **Denominateur : 41 -> 75. Numerateur : 34, inchange. Taux : 82,9 % -> 45,3 %.**
>
> Ce recompte est independant de celui de `nadia-prof` (section 3 de son arbitrage, qui annoncait
> le meme mouvement en ordre de grandeur avant l'ecriture des lignes dans cette matrice) : les
> deux methodes aboutissent au meme denominateur (75) et au meme taux (45,3 %), par deux chemins
> distincts - la sienne en amont sur son propre decompte des 43 lignes, celle-ci en aval sur les
> lignes telles qu'elles sont desormais ecrites dans ce fichier.

**Aucune regression du produit n'est en cause. Aucun jeu ne se casse, aucune ligne verte ne
retombe : les 34 lignes `couvert-valide` d'hier le restent toutes.** Le chiffre baisse parce qu'il
devient vrai, exactement comme au sprint 19, a une echelle differente.

### Ce que ce recompte ne fait pas

- Il ne prononce aucun Done pedagogique sur aucune des 43 lignes neuves : aucun increment
  n'existe.
- Il ne fixe aucune priorite pour s27 : le perimetre du sprint (voyelles simples, questionner le
  monde, histoire-geographie ; anglais CM1 reporte a s28) est un arbitrage separe de l'utilisatrice,
  trace dans `DEC-0072`.
- Il ne depend pas du compte total des enseignements de cycle 2 et de cycle 3 (huit et dix,
  corrige par `nadia-prof` le 2026-09-02, `DEC-0072` ; voir la note en tete de ce fichier pour le
  critere retenu) : aucune ligne d'arts, d'EPS, d'EMC, de sciences et technologie ou d'histoire
  des arts n'entre dans cette matrice a cette date, donc ce compte ne change rien au denominateur
  de 75. **Il changera entierement le denominateur final du produit**, le jour ou ces matieres y
  entreront.

## Mise a jour du 2026-09-03 - la ligne de la monnaie repasse a `en cours`, 33/75 = 44,0 %

### Le fait

Le correctif de couleur des illustrations de `cp-monnaie` est entre dans `round-two`
(`d7fab95`, qui integre `1e6e85d` d'`ines-design`) : les douze fichiers `monnaie-*.svg` portent
desormais un `fill` sur les pieces et les billets - verifie directement sur
`monnaie-en-tout-a.svg` (`fill="#e8a33d"` sur les deux cercles, absent avant ce commit).

Ma note du 2026-09-02 sur la ligne "Reconnaitre et utiliser la monnaie"
(`squad/decisions/produit/DEC-0072`, section 8) disait : *"Des que le correctif est merge, ce
statut doit repasser a `en cours` avant tout nouveau verdict."* **Il l'est. La ligne bascule.**

### Ce que ce n'est pas

**Ce n'est pas une regression.** Aucun jeu ne se casse, aucun item ne change, aucune reponse ne
bouge. C'est le mecanisme normal de cette matrice qui s'execute : un verdict `VALIDE` porte sur
l'objet qu'il a vu, et l'objet a change - `nadia-prof` l'a ecrit d'avance, avant que le correctif
n'existe, dans les mots memes de celle que ce rejeu va coter : *"ce paquet cesse d'etre Done le
jour ou ses illustrations bougent, et il le redevient quand je l'ai rejoue."* Le rejeu est deja
price (0,5 contre sa borne, `DEC-0072` section 8) et deja compte dans le total de s27 (section 9
ci-dessous).

### Le recompte, par ma methode, independant de tout chiffre transmis

Un seul changement dans les huit tableaux de cette matrice : la ligne "Reconnaitre et utiliser la
monnaie" (mathematiques CP) passe de `couvert-valide` a `en cours`. Aucune autre ligne ne bouge.

| | Avant (2026-09-02) | Apres (2026-09-03) |
|---|---|---|
| `couvert-valide` | 34 | **33** |
| `en cours` | 1 | **2** |
| `non couvert` | 40 | 40 |
| `non couvrable en l'etat` | 15 | 15 |
| Total | 90 | 90 |
| Denominateur atteignable (90 - 15) | 75 | 75 |
| **Taux** | **34 / 75 = 45,3 %** | **33 / 75 = 44,0 %** |

**33 / 75 = 0,44 exactement : 44,0 %.**

### Ce que cette mise a jour ne fait pas

- Elle ne prononce aucun verdict sur le paquet rejoue : c'est `nadia-prof`, quand elle aura pu
  jouer l'arbre monte.
- Elle ne touche aucune autre ligne de matiere : les 43 lignes des trois matieres neuves et les
  46 lignes de mathematiques/francais restent inchangees par ailleurs.
- Elle ne prejuge pas de l'issue du rejeu : si `nadia-prof` confirme `COU-2`, `COU-3` et la
  lisibilite de la monnaie, la ligne repasse a `couvert-valide` ; sinon elle reste `en cours` et
  redevient du backlog, comme toute ligne dans cet etat.

## Mise a jour du 2026-09-03 - l'unite de la borne de `nadia-prof` est le lot de matiere, jamais le fichier (`ADR-0079`)

`nadia-prof` a precise, dans
`squad/conformite/ARBITRAGE-s27-s1-8-retrecie-unite-de-ma-borne-et-trois-faits-d-histoire.md`,
section 2, que son unite de facturation n'a jamais ete le fichier de contenu mais **le lot d'une
meme matiere et d'un meme niveau, jusqu'a 20 items, prix au prorata au-dela**. Le decoupage d'un
lot en plusieurs fichiers (un fichier par competence, pour les besoins de cette matrice) est
**juste pour la matrice et neutre pour sa borne** (`ADR-0079`).

**Recompte de s27 avec cette unite, sa methode et sa source** :

| Lot | Items | Fichiers | Prix |
|---|---|---|---|
| `cp-voyelle-du-son` (matiere connue) | 16 | 1 | 1,0 |
| Questionner le monde (matiere neuve, 24 items sur 3 fichiers, prorata au-dela de 20) | 24 | 3 | 1,8 |
| `cm1-histoire-geographie` (matiere neuve) | 20 | 1 | 1,5 |
| Rejeu de `cp-monnaie` (trois proprietes seulement) | 12 | 1 | 0,5 |
| **Total** | **72** | **6** | **4,8** |

**s27 vaut 4,8 sur une borne de 5.** Aucune story ni aucune ligne de cette matrice ne comptait en
fichiers : verification faite sur les stories `S27-1` a `S27-3` de `docs/backlog/backlog.md` et
sur `DEC-0072`, aucune des deux ne suppose qu'un lot tienne dans un seul fichier. Rien n'y est donc
corrige par cette mise a jour.

## Synthese apres les verdicts du sprint 27 (2026-09-03) - six verdicts, aucun refus, une seule fermeture

`nadia-prof` a joue les six lots (branche `matieres-absentes`, commit `430648e`, arbre `round-two`
a `d259b5f`) et prononce six Done pedagogiques, aucun refus.

| Lot | Verdict | Mouvement de ligne |
|---|---|---|
| Voyelles (`bandit-manchot-voyelle-du-son-cp`) | **VALIDE** | `en cours` -> **`couvert-valide`** |
| Vivant ou objet (`bandit-manchot-vivant-non-vivant-cp`) | **VALIDE** | `non couvert` -> `en cours` |
| L'eau solide ou liquide (`bandit-manchot-etats-eau-cp`) | **VALIDE** | `non couvert` -> `en cours` |
| Le corps et les sens (`bandit-manchot-corps-sens-cp`) | **VALIDE SOUS RESERVE** (`COR-R1`, non bloquante) | `non couvert` -> `en cours` |
| Histoire et geographie (`bandit-manchot-histoire-geographie-cm1`) | **VALIDE** | onze lignes `non couvert` -> `en cours` |
| Rejeu de la monnaie (`bandit-manchot-monnaie-cp`) | **VALIDE**, Done rendu | `en cours` -> **`couvert-valide`** |

**La ligne des graphemes se ferme - la seule fermeture du sprint**, comme `nadia-prof` l'avait
annonce d'avance (`squad/programme/MATRICE-couverture.md`, section "Rang recommande pour s27"). Le
rejeu de la monnaie **restaure** une ligne deja fermee avant le sprint, suspendue en cours de route
par le correctif de couleur : ce n'est pas une seconde fermeture de s27.

**Discipline tenue, explicitement** : les deux matieres neuves (questionner le monde, histoire-
geographie) **ouvrent** leurs lignes, elles n'en **ferment** aucune. Quatorze lignes passent `non
couvert` -> `en cours` (trois de questionner le monde, onze d'histoire-geographie) ; **zero** passe
`couvert-valide`. Rien dans cette synthese ne doit suggerer que s27 a couvert deux matieres :
il les a ouvertes.

### Le recompte, par ma methode, independant de tout chiffre transmis

Comptage ligne a ligne des huit tableaux de cette matrice apres application des six verdicts.

| | Avant les verdicts (2026-09-03, matin) | Apres les six verdicts |
|---|---|---|
| `couvert-valide` | 33 | **35** |
| `en cours` | 2 | **14** |
| `non couvert` | 40 | **26** |
| `non couvrable en l'etat` | 15 | 15 |
| Total | 90 | 90 |
| Denominateur atteignable | 75 | 75 |
| **Taux** | **33 / 75 = 44,0 %** | **35 / 75 = 46,7 %** |

**35 / 75 = 0,4666... : 46,7 % arrondi.**

### Ce que le vert ne prouve pas, et qui doit rester ecrit

`nadia-prof` maintient **sept conditions sans garde executable**, sur les six lots de ce sprint :
`VOI-3`, `VOI-5`, `VOI-8` (ce que l'adulte fait reellement de sa voix, paquet des voyelles),
`COU-2`, `COU-3` (rendu en niveaux de gris et lisibilite du chiffre grave, paquet de la monnaie),
`HIS-2` (aucun item a deux reponses defendables, paquet d'histoire-geographie), et **l'exactitude
factuelle** sur les deux matieres ou elle n'est pas la source (histoire-geographie, et dans une
moindre mesure questionner le monde). **Rien dans cette matrice ne doit suggerer le contraire** :
un statut `couvert-valide` ou `en cours` obtenu ici ne signifie jamais que ces sept proprietes sont
gardees par un test.

### La borne de `nadia-prof`

**4,8 sur une borne de 5, tenue en pratique. Rien decline, rien echantillonne** - `cp-voyelle-du-
son` (1,0) + questionner le monde (1,8, prorata sur 24 items en trois fichiers) +
`cm1-histoire-geographie` (1,5) + rejeu de `cp-monnaie` (0,5), section precedente.

### Deux points portes dans les fiches de conformite, pas ici

- **`VOI-9` ne gouverne pas le paquet des voyelles** : ecrit d'avance par `nadia-prof` dans
  `squad/conformite/FICHE-bandit-manchot-voyelle-du-son-cp.md`, section 6, et repris dans la ligne
  des graphemes ci-dessus.
- **`COR-R1`** (item de douze mots, mot hors niveau *rugueux*, non bloquant) : ecrit dans
  `squad/conformite/FICHE-bandit-manchot-corps-sens-cp.md`, section 4, et repris dans la ligne
  "corps et sens" ci-dessus.

### Une reserve neuve, datee, qui n'est pas une dette de s27

`nadia-prof` signale que la consigne et l'enonce se repetent sur les trois paquets a voix
(`bandit-manchot-lettre-du-son-cp`, `bandit-manchot-digraphe-du-son-cp`,
`bandit-manchot-voyelle-du-son-cp`), et que l'indice d'un des trois frole une condition sur ce que
l'adulte doit dire. Ses mots : *"les deux textes sont identiques dans les paquets que j'ai valides
a s26 sans les voir. Je vois plus loin ; je l'ecris comme neuf, pas comme un durcissement de
l'ancien."* **C'est de la matiere pour s28**, tracee dans `docs/backlog/backlog.md`, pas une dette
de s27 : aucune des trois lignes concernees n'est remise en cause par cette reserve.

### Ce que cette synthese ne fait pas

- Elle ne prononce aucun Done : les six verdicts sont de `nadia-prof`.
- Elle ne ratifie pas elle-meme `nombreDeManches: 7` : c'est `DEC-0073`, deja rendue.
- Elle ne fixe aucun perimetre pour s28.

## Integration des dix referentiels absents du 2026-09-03 - le denominateur final est atteint

**Sur mandat de l'utilisatrice** (`DEC-0074`, section 3, amendee le meme jour) : les dix
referentiels des enseignements jusque-la absents (arts plastiques, education musicale, education
physique et sportive et enseignement moral et civique aux deux cycles ; histoire des arts et
sciences et technologie au cycle 3 seul), ecrits par `nadia-prof` le 2026-09-03 et commites en
`1cf80f4`, entrent dans cette matrice **maintenant**, avant l'ouverture de s28, pour que la baisse
du taux affiche se lise comme une correction de mesure et non comme la consequence d'un sprint qui
n'a encore rien livre. C'est la forme deja appliquee au sprint 19 de ce depot, puis au 2026-09-02
pour les 43 premieres lignes des matieres absentes (section "Integration des matieres absentes du
2026-09-02" ci-dessus) ; elle est reprise ici a l'identique.

### Le fait qui precede le chiffre

Jusqu'a cette date, cette matrice portait quatre matieres (mathematiques, francais, questionner le
monde, histoire-geographie, langues vivantes - cinq en comptant les deux niveaux de chaque
enseignement) sur les huit enseignements du cycle 2 et les dix du cycle 3. Le 46,7 % cite a la
cloture de s27 (35/75) etait vrai sur ce perimetre reduit et ne disait rien des six autres
enseignements de cycle 2 ni des cinq autres de cycle 3, faute d'y avoir jamais eu une seule ligne de
referentiel.

### Methode de recompte, independante de tout chiffre annonce ailleurs

Comptage ligne a ligne des dix tableaux neufs de ce fichier (sections "Arts plastiques - CP" a
"Sciences et technologie - CM1" ci-dessus), colonne `Statut`, tels qu'ils sont ecrits apres cette
integration - **je n'ai repris aucun total d'un autre document, j'ai transcrit chaque ligne du
referentiel avec la situation que `nadia-prof` lui attribue, puis compte les statuts obtenus.**
Chaque ligne neuve est classee `non couvert` ou `non couvrable en l'etat`, jamais `en cours` ni
`couvert-valide`, puisqu'aucun jeu n'existe encore sur aucune des dix matieres.

| Matiere / niveau | Total | `couvert-valide` | `non couvert` | `en cours` | `non couvrable en l'etat` |
|---|---|---|---|---|---|
| **arts plastiques CP (neuf)** | **11** | **0** | **4** | **0** | **7** |
| **arts plastiques CM1 (neuf)** | **10** | **0** | **2** | **0** | **8** |
| **education musicale CP (neuf)** | **11** | **0** | **2** | **0** | **9** |
| **education musicale CM1 (neuf)** | **10** | **0** | **0** | **0** | **10** |
| **education physique et sportive CP (neuf)** | **11** | **0** | **4** | **0** | **7** |
| **education physique et sportive CM1 (neuf)** | **10** | **0** | **2** | **0** | **8** |
| **enseignement moral et civique CP (neuf)** | **14** | **0** | **12** | **0** | **2** |
| **enseignement moral et civique CM1 (neuf)** | **12** | **0** | **11** | **0** | **1** |
| **histoire des arts CM1 (neuf)** | **10** | **0** | **7** | **0** | **3** |
| **sciences et technologie CM1 (neuf)** | **20** | **0** | **19** | **0** | **1** |
| **Total des dix referentiels neufs** | **119** | **0** | **63** | **0** | **56** |

**Verification contre l'ordre de grandeur transmis avant l'integration** (119 lignes neuves, 63
atteignables, 56 `non couvrable en l'etat`) : **mon compte converge exactement, aucun ecart**.
119 = 11+10+11+10+11+10+14+12+10+20 ; 63 = 4+2+2+0+4+2+12+11+7+19 ; 56 = 7+8+9+10+7+8+2+1+3+1.

**Avant** (huit tableaux, cloture de s27) : total 90, `couvert-valide` 35, `en cours` 14, `non
couvert` 26, `non couvrable en l'etat` 15, denominateur atteignable 90 - 15 = **75**. **35 / 75 =
46,7 %.**

**Apres** (dix-huit tableaux) : total 90 + 119 = **209**. `couvert-valide` **35, inchange**. `en
cours` **14, inchange**. `non couvert` 26 + 63 = **89**. `non couvrable en l'etat` 15 + 56 = **71**.
Denominateur atteignable **209 - 71 = 138**. **35 / 138 = 0,25362... = 25,4 % arrondi.**

> **Denominateur : 75 -> 138. Numerateur : 35, inchange. Taux : 46,7 % -> 25,4 %.**
>
> Ce recompte converge avec l'ordre de grandeur transmis avant l'integration (denominateur 75 + 63
> = 138, taux 35 / 138 = 25,4 %), par deux chemins distincts - celui-la sur une estimation prealable
> a l'ecriture des dix-huit tableaux, celui-ci sur les lignes telles qu'elles sont desormais
> ecrites dans ce fichier.

**Aucune regression du produit n'est en cause. Aucun jeu ne se casse, aucune ligne verte ne
retombe : les 35 lignes `couvert-valide` d'hier le restent toutes**, et les 14 lignes `en cours`
aussi. Le chiffre baisse parce qu'il devient vrai, exactement comme au sprint 19 et au 2026-09-02,
a une echelle differente.

### Le denominateur final du produit est desormais atteint

**Les huit enseignements du cycle 2 et les dix du cycle 3 sont tous representes par au moins une
ligne de cette matrice.** Ce fait est nouveau a cette date et il est ecrit dans la note de tete de
ce fichier, qui annoncait depuis le 2026-09-02 : *"il changera entierement le denominateur final du
produit, le jour ou ces matieres y entreront"*. **Ce jour est arrive.** Le denominateur atteignable
de 138 n'est plus un plafond partiel : c'est, sur le critere de comptage que cette matrice emploie
(un enseignement par chapitre de programme a attendus propres), le denominateur final du produit
tel qu'il est connu a ce jour - sous reserve, comme toujours, que chaque referentiel reste "un
socle realiste, non exhaustif et non officiel" (mention portee par les dix-huit fichiers sources) et
puisse etre complete plus tard par l'utilisatrice.

### Deux points routes par `nadia-prof`, traites ici

**1. La collision d'identifiant `ART-1`, deja trouvee et corrigee par `nadia-prof` elle-meme.**
Elle avait pose `ART-1` deux fois le meme jour, avec deux sens differents (arts plastiques CP puis
un premier jet d'arts plastiques CM1), l'a trouve et renumerote en `ART-3` a `ART-5` sur les arts
plastiques CM1 avant de me transmettre le fichier - verifie en lisant les deux fichiers tels
qu'ils sont commites : `ART-1` et `ART-2` n'existent que sur `competences-cp.md`, `ART-3` a `ART-5`
n'existent que sur `competences-cm1.md`, et `histoire-des-arts/competences-cm1.md` reutilise les
cinq a l'identique sans en redefinir aucun. **Verification faite qu'aucune autre collision n'existe** :
recherche de `ART-`, `MUS-`, `EPS-`, `EMC-` et `SCI-` suivis d'un chiffre dans l'integralite du
depot (hors les dix referentiels sources eux-memes) - zero occurrence. Ces cinq prefixes sont
neufs au depot, aucun ne recouvre un identifiant deja pose sur les matieres existantes (`BIA-*`,
`HIS-*`, `REC-*`, `CON-*`, `VOI-*`, `COR-*`, `FER-*`, `POS-*`, `ANG-*`, `RAN-*`, `FRA-*`, etc.).

**2. Le corpus de reproductions d'oeuvres et ses droits d'usage, non instruits - ouvert
formellement, non tranche.** `nadia-prof` l'a route trois fois, dans ses propres mots :
*"reproduire une oeuvre existante pose une question de droits qui n'est pas de ma competence"*
(arts plastiques CP, ligne 9), *"la question des droits d'usage des reproductions n'est pas de ma
competence : je la signale a `camille-po` et a `theo-archi`, je ne la tranche pas"* (`ART-3`, arts
plastiques CM1), et sur l'histoire des arts : *"ce n'est pas un cout de mecanique, c'est un cout de
contenu"*. **Neuf lignes atteignables sont concernees** (2 en arts plastiques CP, 2 en arts
plastiques CM1, 5 en histoire des arts CM1), toutes marquees `non couvert` ci-dessus et non `non
couvrable en l'etat` : ce n'est pas une contrainte fondatrice du produit, c'est un contenu qui
n'existe pas encore et un risque juridique non instruit. **Cette question est ouverte formellement
en `DEC-0075`** (statut `Proposee`), avec trois options ecrites sans recommandation ; elle n'est
tranchee dans aucun sens par cette matrice, et les neuf lignes restent `non couvert` tant que
l'utilisatrice n'a pas arbitre.

### Ce que cette integration ne fait pas

- Elle ne prononce aucun Done pedagogique sur aucune des 119 lignes neuves : aucun increment
  n'existe.
- Elle ne fixe aucune priorite pour s28 : le perimetre du sprint est un arbitrage separe de
  l'utilisatrice, trace dans `DEC-0074`.
- Elle ne tranche pas la question du corpus de reproductions d'oeuvres et de ses droits d'usage :
  `DEC-0075` reste `Proposee`.
- Elle ne prejuge d'aucun sprint au-dela de s28 : rien ici ne dit combien de sprints il faudra pour
  faire bouger 138 lignes.

## Synthese apres les verdicts du sprint 28 (2026-09-04) - huit fermetures, une ouverture, un refus corrige et reaudite dans le sprint

`nadia-prof` a joue les quatre lots (branche `round-two`, commits `537a0e1` puis `e06de4b` apres
correction et reaudit) et prononce sept verdicts au premier passage, un refus, puis un reaudit sur
le lot refuse.

| Lot | Verdict | Mouvement de ligne |
|---|---|---|
| Second paquet questionner le monde - etres vivants (`bandit-manchot-vivant-non-vivant-cp`) | **REFUSE** (2026-09-04, six items neufs faux), puis **VALIDE SOUS RESERVE** au reaudit du meme jour apres correction (`0c383b9`) | `en cours` -> **`couvert-valide`** (`QLM-CP-01`) |
| Second paquet questionner le monde - corps et sens (`bandit-manchot-corps-sens-cp`) | **VALIDE SOUS RESERVE** (`COR-R4`, payee et levee le meme jour) | `en cours` -> **`couvert-valide`** (`QLM-CP-03`) |
| Second paquet questionner le monde - etats de l'eau (`bandit-manchot-etats-eau-cp`) | **VALIDE** | `en cours` -> **`couvert-valide`** (`QLM-CP-05`) |
| Second paquet histoire-geographie, composition B (`bandit-manchot-histoire-geographie-cm1`) | **VALIDE SOUS RESERVE** (`HG-R4`, `HG-R5`) | deux lignes `en cours` -> **`couvert-valide`** (`HG-CM1-11`, `HG-CM1-13`) ; neuf lignes restent `en cours` |
| Premier paquet d'anglais (`bandit-manchot-anglais-cm1`) | **VALIDE SOUS RESERVE** (`ANG-R1`) | `LVE-CM1-04` : `non couvert` -> **`couvert-valide`** ; `LVE-CM1-07` : `non couvert` -> `en cours` (s'ouvre, ne ferme pas) |
| Lot de calcul pose - multiplication (`bandit-manchot-multiplication-posee-cm1`) | **VALIDE** | `non couvert` -> **`couvert-valide`** (`MAT-CM1-04`) |
| Lot de calcul pose - division (`bandit-manchot-division-posee-cm1`) | **VALIDE SOUS RESERVE** (`POS-R1`) | `non couvert` -> **`couvert-valide`** (`MAT-CM1-05`) |

**Huit lignes ferment, une s'ouvre.** Les neuf autres lignes d'histoire-geographie restent `en
cours`, exactement comme `nadia-prof` l'avait annonce d'avance dans son arbitrage prealable
(section 8.2 : *"six a sept lignes fermeront en s28, pas quatorze"*) : le lot livre est
integralement consacre a la fermeture de deux lignes, il n'en ouvre ni n'en ferme d'autre.

### Le refus et sa correction, dans le sprint - un fait de gestion, pas un fait pedagogique

Le second paquet de questionner le monde sur les etres vivants a d'abord ete **REFUSE** :
trois items neufs de matiere inerte (caillou, sable, neige) portaient `un objet` en bonne
reponse, ce qui est faux au regard du libelle de la ligne (qui distingue explicitement le vivant,
les objets **et** la matiere inerte). `nadia-prof` avait ecrit que ce refus ne serait pas rejoue
dans s28, le sprint etant deja a sa borne de 5,0 sans marge.

**L'utilisatrice a arbitre le contraire le 2026-09-04** : elle a demande que le paquet refuse soit
corrige et reaudite dans s28 plutot que de laisser trois items faux servis en production jusqu'a
s29. `nadia-prof` avait decline cette composition par ecrit avant le sprint (elle l'avait chiffree
a 5,25, au-dessus de sa borne, dans la meme famille que la dette des paquets a voix qu'elle avait
declinee) ; elle execute l'arbitrage de l'utilisatrice sans le rediscuter. **Ce fait de gestion est
acte dans `DEC-0076`**, qui ne revoque ni le refus ni le verdict qui l'a suivi - les deux blocs
restent lisibles dans `squad/conformite/FICHE-bandit-manchot-vivant-non-vivant-cp.md`, dans
l'ordre, aucun n'efface l'autre.

### Le recompte, par ma methode, independant de tout chiffre transmis

Comptage ligne a ligne des dix-huit tableaux de cette matrice, colonne `Statut`, apres application
des huit verdicts qui ferment une ligne et du verdict qui en ouvre une - **je n'ai repris aucun
total d'un autre document, j'ai reclasse chaque ligne des dix-huit tableaux telle qu'elle est
ecrite dans ce fichier apres mise a jour, puis compte les statuts obtenus.**

| | Avant les verdicts (2026-09-03, cloture de s27) | Apres les huit verdicts de s28 |
|---|---|---|
| `couvert-valide` | 35 | **43** |
| `en cours` | 14 | **10** |
| `non couvert` | 89 | **85** |
| `non couvrable en l'etat` | 71 | 71 |
| Total | 209 | 209 |
| Denominateur atteignable | 138 | 138 |
| **Taux** | **35 / 138 = 25,4 %** | **43 / 138 = 31,2 %** |

**43 / 138 = 0,31159... : 31,2 % arrondi.**

**Verification contre l'ordre de grandeur transmis avant ce recompte** (35 + 8 = 43 sur un
denominateur de 138, soit 31,2 %) : **mon compte converge exactement, aucun ecart.** Le detail par
matiere qui produit ce total : mathematiques CM1 passe de 8 a 10 `couvert-valide` (+2, calcul
pose) ; questionner le monde CP passe de 0 a 3 `couvert-valide` et de 3 a 0 `en cours` (les trois
lignes ouvertes a s27 ferment toutes) ; histoire-geographie CM1 passe de 0 a 2 `couvert-valide` et
de 11 a 9 `en cours` (deux lignes ferment sur les onze ouvertes) ; langues vivantes CM1 passe de 0
a 1 `couvert-valide`, de 0 a 1 `en cours` et de 4 a 2 `non couvert` (une ligne ferme, une s'ouvre).
Aucune autre matiere ne bouge.

**Ce que ce chiffre ne dit pas, et qui doit rester ecrit a cote de lui** : le taux monte de 25,4 %
a 31,2 % dans le meme sprint ou le denominateur, lui, ne bouge pas - contrairement aux deux
sprints precedents (s26 -> s27 : 75 inchange ; s27 -> ouverture de s28 : 75 -> 138). **Les deux
mouvements de ce sprint n'ont rien a voir l'un avec l'autre** : la hausse de 25,4 % a 31,2 % est
un gain reel de couverture, sur un denominateur devenu stable et honnete depuis l'integration du
2026-09-03. Rien dans cette hausse ne doit se lire comme un effet de mesure, et rien dans les
hausses passees (46,7 % -> 25,4 % au 2026-09-03) ne doit se lire comme un gain de couverture :
c'etaient deux mouvements de nature opposee, sur deux sprints distincts.

### Ce que le vert ne prouve pas, et qui doit rester ecrit

`nadia-prof` reste la seule garde de plusieurs proprietes sur les huit lignes fermees ce sprint,
et rien dans ce fichier ne doit suggerer le contraire :

- **`HIS-2` et l'exactitude factuelle** sur `HG-CM1-11` et `HG-CM1-13` : aucun test ne sait qu'un
  fait de geographie est juste ou qu'un item n'a qu'une reponse defendable.
- **`REC-5` (mot transparent) et `REC-6` (longueur voisine)** sur `LVE-CM1-04` : le test garde
  l'identite de graphie et l'appartenance au champ lexical, pas la ressemblance a l'oeil ni la
  longueur - c'est la lecture de `nadia-prof` qui a trouve `mountain`/`montagne` (`ANG-R1`).
- **`POS-3`** sur `MAT-CM1-04` et `MAT-CM1-05` : qu'un distracteur soit vraiment l'erreur classique
  qu'il declare n'est verifie que par elle.
- **La verite d'un enonce de matiere inerte** sur `QLM-CP-01` : le test qui garde la classification
  du paquet (`chacuneDesTroisReponsesEstRepresenteeParmiLesItemsDeCategorisation`) garde des
  chaines de caracteres, pas un fait - c'est ce que le refus puis le reaudit de ce sprint
  demontrent directement.

**La mention "sans garde executable" manque toujours sur les lignes servies par les paquets a
voix et sur la ligne de la monnaie a l'ouverture de s28** ; elle est desormais ecrite sur les
quatre lignes concernees (`squad/programme/MATRICE-couverture.md`, section "Francais - CP",
lignes des graphemes, de la position de syllabe, du son dans le mot et de l'ecriture autonome) -
la ligne de la monnaie la portait deja depuis s27.

### Deux mentions exigees d'avance, reprises mot pour mot dans les lignes ci-dessus

- **`MAT-CM1-04` et `MAT-CM1-05`** : *"le resultat atteste le calcul ; la technique posee elle-
  meme - disposition, retenues, produits partiels - n'est ni montree ni verifiee par le produit."*
- **`LVE-CM1-07`** : *"decouverte culturelle seule, en francais ; aucune competence linguistique
  n'est servie"*.

### Ce que DEC-0073 devient sur les trois paquets de questionner le monde

`DEC-0073` ratifiait `nombreDeManches: 7` pour les trois paquets a huit items de s27. **Les trois
paquets portent desormais quatorze items et `nombreDeManches: 10`**, la valeur par defaut de
`DEC-0064` : la derogation que `DEC-0073` ratifiait n'a plus d'objet sur ces trois paquets. `DEC-
0073` n'est pas remplacee ni invalidee - elle reste exacte pour l'etat qu'elle decrivait, a huit
items - mais elle ne s'applique plus a l'etat actuel du contenu.

### Reserves ouvertes pour s29, et reserves levees a s28

**Ouvertes** : `HG-R4` (charge de lecture), `HG-R5` (mot d'indice "recolte"), `ANG-R1` (mot
transparent `mountain`), `POS-R1` (structure repetee des distracteurs de la division), `COR-R2`
(*gouter -> la bouche*, non retouchee), `EAU-R1` (indice abstrait sur "fondre"), `VIV-R2` (les
deux propositions des items de matiere inerte se terminent par les memes mots). Stories
correctives : `docs/backlog/backlog.md`, section "Backlog ouvert pour s29 - sept reserves non
bloquantes issues des verdicts de s28".

**Levees a s28** : `VIV-R1`, `COR-R1` (deja levee a s27), `COR-R3`, `COR-R4`, `EAU-R2`, `HG-R2`,
`HG-R3`.

### Ce que cette synthese ne fait pas

- Elle ne prononce aucun Done : les sept verdicts (dont un refus suivi d'un reaudit) sont de
  `nadia-prof`.
- Elle ne fixe aucun perimetre pour s29.
- Elle n'acte pas elle-meme l'arbitrage de gestion sur le cout du sprint (5,25 sur une borne de
  5) : voir `DEC-0076`.

## Synthese apres les verdicts du sprint 29 (2026-09-06) - neuf lignes ferment, deux instances de `nadia-prof` en parallele pour la premiere fois

`nadia-prof` a joue en deux instances disjointes (`round-two` a `5c35f6c`, `56f8967` pour
l'Instance B ; `ba3f603` pour l'Instance A, plus son releve
`squad/sprints/s29-parallelisation-et-dettes-payees/verdicts-nadia-instance-A.md`).

| Ligne | Instance | Mouvement |
|---|---|---|
| `QLM-CP-02` (cycle de vie) | A | `non couvert` -> `couvert-valide` |
| `QLM-CP-07` (objet technique) | A | `non couvert` -> `couvert-valide` |
| `HG-CM1-06` (rois et realisations) | A | `en cours` -> `couvert-valide` |
| `HG-CM1-07` (Revolution) | A | `en cours` -> `couvert-valide` |
| `HG-CM1-14` (comprendre un document) | A | `en cours` -> `couvert-valide`, sur sa part servie |
| `EMC-CP-06` (regles de vie) | B, premier passage (2026-09-05) | `non couvert` -> `couvert-valide` |
| `EMC-CP-02` (permis et interdit) | B, reaudit apres refus | `non couvert` -> `couvert-valide` |
| `EMC-CM1-11` (fait ou opinion) | B, reaudit apres refus | `non couvert` -> `couvert-valide` |
| `EMC-CM1-07` (droit ou devoir) | B, reaudit apres refus | `non couvert` -> `couvert-valide` |

**Neuf lignes ferment**, pas huit.

### Le recompte, par ma methode, et l'ecart trouve contre le chiffre transmis

Comptage ligne a ligne des dix-huit tableaux de cette matrice, colonne `Statut`, apres application
des neuf verdicts ci-dessus - je n'ai repris aucun total d'un autre document, j'ai reclasse chaque
ligne telle qu'elle est ecrite dans ce fichier apres mise a jour, puis compte les statuts obtenus.

| | Avant les verdicts (2026-09-05, ouverture de s29) | Apres les neuf verdicts de s29 |
|---|---|---|
| `couvert-valide` | 43 | **52** |
| `en cours` | 10 | **7** |
| `non couvert` | 85 | **79** |
| `non couvrable en l'etat` | 71 | 71 |
| Total | 209 | 209 |
| Denominateur atteignable | 138 | 138 |
| **Taux** | **43 / 138 = 31,2 %** | **52 / 138 = 37,7 %** |

**52 / 138 = 0,37681... : 37,7 % arrondi.**

**Ecart trouve contre le chiffre transmis** : l'Instance A de `nadia-prof` annonce *"51 / 138 =
37,0 %"* (cinq lignes fermees par elle, "avec les trois de l'Instance B, huit pour le sprint").
**Mon compte differe d'une ligne, et c'est le mien qui fait foi ici** (le sien porte explicitement
sur son propre travail d'audit, pas sur la tenue de cette matrice - *"le compte de la matrice
appartient a `camille-po` ; je ne la touche pas"*). La ligne manquante est `EMC-CP-06`
(`bandit-manchot-regles-de-vie-cp`), fermee des le premier passage de l'Instance B le 2026-09-05
(*"EMC-CP-06 ferme, sous reserve non bloquante"*, Done pedagogique prononce, mention exigee
ecrite dans sa fiche) : elle n'est ni un refus, ni un verdict retire, et son statut n'a pas bouge
depuis. L'Instance A la nomme dans son releve, separement de son propre total de huit, sans
l'y ajouter - c'est la cause exacte de l'ecart. **Detaille et acte dans `DEC-0077`, section 9.**

**Ce chiffre converge exactement avec le plafond arithmetique que la composition de `DEC-0077`
(section 8.4) annoncait avant que le sprint ne soit joue** : les neuf lignes visees par les deux
instances ferment toutes les neuf. Le sprint atteint son plafond, pas seulement le haut de
l'attente raisonnable de `nadia-prof` (36,2 % a 37,0 %).

### Les mentions de couverture partielle, verifiees fiche par fiche et non reprises d'un releve

- **`HG-CM1-14`** : *"document textuel seul ; carte et image restent non couvertes"* - exigee
  d'avance, deja ecrite mot pour mot dans le commentaire du paquet par `marc-dev`, reprise ici.
- **`EMC-CP-06`** : *"connaissance de la regle et de ce a quoi elle sert seule ; sa mise en oeuvre
  en situation n'est pas servie. Ce produit verifie ce qu'un enfant sait, jamais ce qu'il fait."* -
  exigee d'avance dans la fiche, sans laquelle `nadia-prof` n'aurait pas ferme la ligne.
- **`EMC-CM1-07` et `EMC-CM1-11`** : la note posee sur la table "Enseignement moral et civique -
  CM1" exige une mention *"si une ligne bouge"*, sans la restreindre a des lignes nommees
  (contrairement a la note de la table CP, restreinte aux lignes 5 et 6). **Aucune des deux fiches
  de conformite de ces jeux ne mentionne, ni ne leve, cette exigence** : ni verdict qui la leve, ni
  mention specifique qui la remplace. Faute d'un verdict qui la leve explicitement, je l'applique
  par defaut, comme la note qui la pose le demande : *"connaissances civiques et reconnaissance de
  situations seules ; aucun comportement, aucun engagement et aucune participation a une decision
  collective ne sont servis par ce produit."*
- **`QLM-CP-02`, `QLM-CP-07`, `HG-CM1-06`, `HG-CM1-07`, `EMC-CP-02`** : aucune mention n'est due,
  `nadia-prof` le dit explicitement pour les quatre premieres ; la fiche de `EMC-CP-02` le dit tout
  aussi explicitement (*"Aucune mention de couverture partielle n'est exigee sur cette ligne"*).
- **`MAT-CM1-05`** : la mention *"le resultat atteste le calcul ; la technique posee elle-meme...
  n'est ni montree ni verifiee par le produit"* reste due et inchangee ; `POS-R1` est levee,
  `POS-R2` est ouverte, la ligne reste `couvert-valide` sans mouvement.

### Un chiffre corrige, deja signale par `nadia-prof` elle-meme

`nadia-prof` a ecrit deux fois *"neuf lignes en cours"* en histoire-geographie CM1 (arbitrages de
s28 et de s29) et se corrige elle-meme apres avoir joue : **il en reste six**, pas neuf -
`HG-CM1-01`, `-02`, `-03`, `-04`, `-08` et `-12`, dont cinq portent exactement un item. Verifie
directement sur le tableau : le chiffre etait exact avant la fermeture de s29 et ne l'est plus
apres ; la matrice porte desormais le compte a jour (section "Histoire-Geographie - CM1").

### Les reserves, tracees sans en perdre une

**Quatre reserves n'apparaissaient dans aucun releve qui m'a ete transmis pour ce sprint et sont
pourtant ecrites, datees et non levees dans les fiches de conformite** : `CYV-R1`, `OBJ-R1`,
`OBJ-R2` (toutes trois sur les paquets de questionner le monde de l'Instance A) et `HG-R7`
(inexactitude du texte-support sur la Bastille, histoire-geographie). Elles sont ouvertes pour s30
au meme titre que les autres, dans `docs/backlog/backlog.md`.

**Ouvertes pour s30** (quinze au total) : `CYV-R1`, `OBJ-R1`, `OBJ-R2`, `HG-R6`, `HG-R7`, `REG-R1`,
`PER-R1`, `FOP-R1`, `FOP-R2`, `FOP-R3`, `DRO-R1`, `DRO-R2`, `DRO-R3`, `POS-R2`, et `VIV-R2`
(ouverte depuis s28, desormais executable - `nadia-prof` a tranche sa propre piste et prescrit
trois enonces de remplacement mot pour mot). Detail complet, avec lien vers chaque fiche :
`docs/backlog/backlog.md`, section "Backlog ouvert pour s30".

**Levees a s29** : `COR-R2`, `EAU-R1`, `HG-R4`, `HG-R5`, `ANG-R1`, `POS-R1`, plus la dette des
paquets a voix (payee apres quatre sprints d'attente, six jeux verifies un par un).

### Ce que je fais des trois points routes par la passe transverse `S29-3`

- **`CONS-1`** (la consigne generique passee de 5 a 10 jeux en un sprint) : **tranchee**, pas
  seulement ouverte - `DEC-0078` pose une regle produit (toute entree neuve de catalogue nomme la
  tache de son jeu, a partir de s30) et planifie un correctif non bloquant sur les dix entrees
  existantes. Story dans `docs/backlog/backlog.md`.
- **La contradiction de `nadia-prof` sur son propre arbitrage** (sa section 7.2 pricait six
  reserves au regime du mot pour mot alors que deux, `VIV-R2` et `ANG-R1`, n'avaient pas de texte
  fourni) : **tracee ici, deja corrigee par elle-meme** dans la passe transverse (section 5.1),
  sans supplement facture. Rien a trancher de mon cote : c'est une coherence de ses propres
  conditions, pas une decision produit.
- **`REV-1`** (la phrase composee par la mecanique autour de la bonne reponse, fautive sur 12
  items de 4 paquets dont deux fermes ce sprint, maladroite sur 29 items de 5 autres) : **routee a
  `theo-archi` pour la cause technique, tracee ici et portee en story dans le backlog pour la
  partie produit** (`docs/backlog/backlog.md`, "Story a ecrire pour s30 - `REV-1`"). Elle touche
  des lignes deja fermees (`QLM-CP-02`, `cp-qlm-vivant-non-vivant`, `cm1-comprehension`,
  `cm1-histoire-geographie`) sans en remettre aucune en cause : aucune n'a jamais garanti la forme
  de cette phrase, et le defaut est anterieur a s29 sur les quatre. Je ne la traite pas
  au-dela de cette trace : la cause est technique et n'est pas mienne a corriger.

### Le fait de gestion, acte

**Total du sprint : 9,25 sur la borne de 8,0** (Instance A 4,0/5,0, aucun tirage ; Instance B
5,25/5,0, dont 2,0 absorbes par son plafond individuel et 0,25 tires sur la marge de rejeu du
sprint). **La marge de rejeu nommee a l'ouverture passe de 1,0 a 0,75.** Acte dans `DEC-0077`,
amendement de la section 9 (2026-09-06), plutot qu'en decision neuve : le fait decoule directement
du perimetre et de la borne que `DEC-0077` avait deja poses pour ce sprint.

### Ce que cette synthese ne fait pas

- Elle ne prononce aucun Done : les neuf verdicts sont de `nadia-prof`, dans les fiches
  correspondantes.
- Elle ne fixe aucun perimetre pour s30.
- Elle ne tranche pas la cause technique de `REV-1` ni la forme d'un eventuel garde-fou sur
  `CONS-1` : routees a `theo-archi`.

### Amendement du 2026-09-06 (meme jour) - la reverification de `REV-1` corrige le cout reel du
### sprint a 9,50 ; la matrice ne bouge pas

`nadia-prof` a rejoue apres la cloture ci-dessus, sur le correctif de `REV-1` livre entre-temps
(`theo-archi`, `ADR-0083`, commit `8ef4fb2`) : **VALIDE SOUS RESERVE**, deux reserves neuves
ouvertes (`REV-2`, refusee et remplacee par un texte prescrit mot pour mot ; `REV-3`, routee, cause
structurelle). Ecrit au long dans
`squad/sprints/s29-parallelisation-et-dettes-payees/verdicts-nadia-instance-A.md`, derniere
section, et dans `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section
"Reverification du 2026-09-06". **Prix : 0,25, annonce d'avance. L'Instance A passe de 4,0/5,0 a
4,25/5,0.**

**Le total du sprint passe de 9,25 a 9,50 sur la borne de 8,0** (4,25 + 5,25). **La marge de rejeu
ne bouge pas** : elle reste a **0,75**, `nadia-prof` l'ecrivant elle-meme (*"Rien de tire sur la
marge de rejeu du sprint, qui garde ses 0,75."*) - le supplement de 0,25 tient dans le plafond
individuel de l'Instance A, pas dans la marge du sprint. Corrige dans le meme geste dans
`DEC-0077` (section 9.4) et `DEC-index.md`.

**Aucune ligne de cette matrice ne bouge par cette reverification** : les neuf fermetures de s29
restent telles qu'ecrites ci-dessus, le compte de 52/138 = 37,7 % est inchange.

## Perimetre de s30 (2026-09-06) - sciences et technologie CM1 entre en bloc, deux libelles corriges,
## les codes SCI-CM1 sont alloues

`DEC-0079` ouvre s30 sur quatre arbitrages de l'utilisatrice : sciences et technologie CM1 comme
perimetre principal, deux instances de `nadia-prof` en parallele, les quinze reserves de s29 dans
s30, `CONS-1` et `REV-3` tous deux dans s30. Cette section dit ce que cette matrice peut en attendre
et enregistre les deux ecritures faites avant l'ouverture. **Elle ne deplace aucune ligne.**

### Le fait, avec sa methode

**Etat de depart, recompte ligne a ligne des dix-huit tableaux, colonne `Statut`** : inchange depuis
la cloture de s29 - 52 `couvert-valide`, 7 `en cours`, 79 `non couvert`, 71 `non couvrable en
l'etat`, 209 lignes, 138 au denominateur atteignable. **52 / 138 = 37,7 %.**

**Ce que s30 peut faire bouger, derive et non suppose** (`nadia-prof`, section 3.5 de son arbitrage
prealable) : la borne de 8,0 se decoupe en 5,00 de travail qui ne ferme aucune ligne (les treize
reprises retenues, `CONS-1`, `REV-3`, la passe transverse) et 3,00 pour les lignes de sciences, soit
deux lots a 1,5 fermant chacun deux lignes au plus (`FER-2`, `ADR-0081` clause 5, `DEC-0064`).
**Plafond arithmetique : 4 lignes. 52 + 4 = 56 / 138 = 40,6 %. Attente raisonnable : 3 a 4 lignes,
55 a 56 / 138, soit 39,9 % a 40,6 %.**

**Le chiffre a ne pas laisser dans l'ombre** : sur les 8,0 de la borne, **5,00 - 62,5 % - sont
engages sur du travail qui ne fait bouger aucun statut de cette matrice.** Ce n'est pas un
gaspillage : ce sont treize reserves qui portent toutes sur des paquets deja `couvert-valide`, une
dette transverse (`CONS-1`), un correctif de mecanique deja livre par `theo-archi` (`REV-3`,
`ADR-0084`) et une passe d'audit sans laquelle la parallelisation degraderait silencieusement des
proprietes deja tenues. Le detail chiffre est dans `DEC-0079`.

### Les deux ecritures faites avant l'ouverture, hors de toute story

1. **Deux libelles faux corriges au mot pres sur le referentiel** (`squad/programme/sciences-et-
   technologie/competences-cm1.md`, corrige le 2026-09-05, `DEC-0077` section 8.7) : la ligne des
   regimes alimentaires (*"vegetarien"* -> *"herbivore"*) et la ligne des grandes fonctions
   (*"nutrition"* retiree de sa propre liste de composantes). Les deux tables de ce fichier portaient
   encore les anciens libelles ; elles sont corrigees dans la section "Sciences et technologie -
   CM1" ci-dessus.
2. **Les vingt codes `SCI-CM1-01` a `SCI-CM1-20` alloues** dans le referentiel, conformement a
   `ADR-0081` : ce referentiel n'en portait aucun avant s30, et `nadia-prof` ne prononce aucun Done
   pedagogique sur une ligne de sciences dont le code n'est pas cite par le paquet qui la sert.

### Ce que cette section ne fait pas

- Elle **ne deplace aucune ligne**, n'en ajoute aucune, n'en retire aucune.
- Elle **ne prononce aucun Done** et n'en anticipe aucun. Le plafond ci-dessus suppose que chaque
  lot de sciences passe la porte du premier coup, ce qui n'est jamais arrive sur ce produit.
- Elle **ne differe pas elle-meme** `PER-R1` et `REG-R1` : c'est `nadia-prof` qui les differe a s31,
  avec son propre motif, repris dans `DEC-0079` et dans `docs/backlog/backlog.md`.

## Synthese apres les verdicts du sprint 30 (2026-09-07) - quatre lignes de sciences ferment,
## `CONS-1` n'est pas levee, troisieme depassement de borne assume

`nadia-prof` a joue en deux instances disjointes, chacune reauditee dans le sprint apres un premier
passage (Instance B : deux refus le 2026-09-06, leves au reaudit du meme jour ; Instance A :
reliquat de `CONS-1` rejuge le 2026-09-06). Sources :
`squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/verdicts-nadia-instance-A.md`,
`verdicts-nadia-instance-B.md` (corps et complements des deux documents), `DEC-0080` (arbitrages de
l'utilisatrice et amendement de cloture).

| Ligne | Instance | Mouvement |
|---|---|---|
| `SCI-CM1-05` (signal et information) | B, premier passage | `non couvert` -> `couvert-valide` |
| `SCI-CM1-02` (melanges) | B, premier passage | `non couvert` -> `couvert-valide` |
| `SCI-CM1-17` (chaine alimentaire) | B, reaudit apres refus | `non couvert` -> `couvert-valide` |
| `SCI-CM1-12` (objet technique) | B, reaudit apres refus | `non couvert` -> `couvert-valide` |

**Quatre lignes ferment**, toutes du seul lot de sciences et technologie CM1, qui n'avait recu
aucun item avant ce sprint. `MAT-CM1-05` (bloc D) reste `couvert-valide`, sans mouvement de statut :
`POS-R2` est levee, `POS-R3` est ouverte.

### Le recompte, par ma methode, independant de tout chiffre transmis

Comptage ligne a ligne des dix-huit tableaux de cette matrice, colonne `Statut`, apres application
des quatre verdicts ci-dessus - je n'ai repris aucun total d'un autre document, j'ai reclasse chaque
ligne telle qu'elle est ecrite dans ce fichier apres mise a jour, puis compte les statuts obtenus,
table par table.

| | Avant les verdicts (2026-09-06, ouverture de s30) | Apres les quatre verdicts de s30 |
|---|---|---|
| `couvert-valide` | 52 | **56** |
| `en cours` | 7 | 7 |
| `non couvert` | 79 | **75** |
| `non couvrable en l'etat` | 71 | 71 |
| Total | 209 | 209 |
| Denominateur atteignable | 138 | 138 |
| **Taux** | **52 / 138 = 37,7 %** | **56 / 138 = 40,6 %** |

**56 / 138 = 0,40579... : 40,6 % arrondi.**

**Aucun ecart trouve cette fois.** Mon compte, refait table par table (dix-huit tableaux verifies un
par un, y compris les dix qui ne bougent pas ce sprint), converge exactement avec le plafond
arithmetique que `DEC-0079` avait pose avant que le sprint soit joue (section 6, *"plafond
arithmetique : 4 lignes. 52 + 4 = 56 / 138 = 40,6 %"*) : **le sprint atteint son plafond, comme s29
avant lui**, et non un point de moins comme le laissait attendre le risque de refus que `nadia-prof`
avait elle-meme nomme le plus probable de s30 (la repetition d'une regle de fabrication a
l'interieur d'une tache, verifiee sur ses deux refus).

### Les mentions de couverture partielle, verifiees contre l'arbitrage prealable de `nadia-prof`

**Aucune mention de couverture partielle n'est due sur aucune des quatre lignes fermees.** Son
arbitrage prealable (section 2.4) n'exigeait une telle mention que sur trois lignes de sciences -
`SCI-CM1-06` (classement du vivant), `SCI-CM1-14` (environnement numerique) et `SCI-CM1-20`
(document scientifique), aucune des trois n'etant servie ce sprint. `nadia-prof` l'ecrit
explicitement dans les deux moities de son releve plutot que de laisser la question ouverte : *"je
n'en ajoute pas une apres coup."*

### `CONS-1` n'est pas levee, et la matrice n'en porte la trace nulle part ailleurs que dans le
### backlog

`CONS-1` (`DEC-0078`) et `REV-3` (`ADR-0084`) ne font bouger aucune ligne de cette matrice : les dix
jeux qu'ils touchent sont deja `couvert-valide` ou hors de son perimetre. **`CONS-1` n'est pas close
pour autant.** Rejugee entree par entree contre le paquet qu'elle introduit, elle tient desormais
sur neuf entrees de dix ; une seule, `bandit-manchot-histoire-geographie-cm1`, reste ouverte - sa
consigne nomme deux taches qui excluent 31 des 57 items du paquet, dont toute la geographie. Le
reliquat est routee au backlog de s31 (rejeu borne, 0,5), pas soldee ici : voir
`docs/backlog/backlog.md`, section "Backlog ouvert pour s31".

**Un onzieme fait, distinct de `CONS-1` et non compris dans son reliquat**, est trouve par
`nadia-prof` en recomptant sa propre population de reference : `bandit-manchot-semaine-cp` porte
*"Choisis la bonne reponse."*, le meme defaut que `CONS-1` vise sous une autre chaine que celle que
`DEC-0078` mesurait. **`DEC-0078` est amendee** (2026-09-07) pour corriger la population qu'elle
annoncait (43 conformes sur 54, pas 44) et pour router ce onzieme cas au backlog de s31 sous le code
`CONS-3`, prix non chiffre par `nadia-prof` a ce jour.

### Le fait de gestion, confirme par l'amendement de cloture de `DEC-0080`

**Total du sprint : 8,50 sur la borne de 8,0** (Instance A 4,00/4,00, aucun depassement ; Instance B
4,50/4,00, 0,50 au-dela de son budget opposable, sous son plafond dur individuel de 5,0). C'est le
**troisieme depassement consecutif** de ce produit (s28 : +0,25 ; s29 : +1,50 ; s30 : +0,50), ecrit
et arbitre par l'utilisatrice avant d'etre consomme (`DEC-0080`), non decouvert au comptage de
cloture comme celui de s29.

**`PAR-11`, clause neuve de `nadia-prof`, enregistree dans `DEC-0080` (section 7) et referencee
depuis `DEC-0079`** : la marge de rejeu se dimensionne par instance sur son exposition reelle (0,5
par lot neuf qu'elle audite), et la marge du sprint est la somme de ces marges, non un total
reparti a parts egales. Elle corrige l'application de `PAR-9` sans la remplacer.

### Les reserves, tracees sans en perdre une

**Neuf reserves ouvertes par l'Instance B pour s31, 2,5 au total** : les six du premier passage
(`SIG-R1`, `SIG-R2`, `MEL-R1`, `MEL-R2`, `MEL-R3`, `POS-R3`, 1,5) et les trois du reaudit (`CHA-R1`,
`OBT-R1`, `OBT-R2`, 1,0). **Sept reserves ou reliquats ouverts par l'Instance A pour s31, 2,5 au
total** : `HG-R8`, `FOP-R4`, `DRO-R4`, `CONS-2` (population portee de deux entrees a trois, second
motif ajoute), `SAI-R1`, `ERR-R1` (2,0), plus le reliquat de `CONS-1` sur `histoire-geographie-cm1`
(0,5). `ANG-R2` est **levee** dans le reaudit et sort du backlog price. `CONS-3` (`semaine-cp`,
`DEC-0078` amendee) s'ajoute sans prix chiffre. **Detail complet, avec lien vers chaque fiche ou
verdict** : `docs/backlog/backlog.md`, section "Backlog ouvert pour s31".

### Ce que je route, en plus du backlog

| Fait | A qui | Ce qu'il en fait |
|---|---|---|
| **La reprise des deux paquets refuses n'a ajoute aucun garde-fou** sur les proprietes que les deux refus ont fait payer (repetition d'une regle de fabrication a l'interieur d'une tache, distracteur pris chez le voisin) : le contenu est corrige, un commentaire d'exclusion d'un test existant est mis a jour, rien de plus | `theo-archi`, `yanis-qa` | `nadia-prof` le dit elle-meme pour qu'on ne croie pas couvert ce qui ne l'est pas : ces proprietes restent gardees par elle seule, en rejouant a chaque sprint |
| **Trois proprietes textuelles gardees par `nadia-prof` seule et mordables en principe** (plusieurs items d'un paquet portant la meme bonne reponse ; une bonne reponse recopiee mot pour mot dans son enonce ; un item de cycle 3 reprenant a l'identique un item de cycle 2 deja servi) | `theo-archi` | Troisieme sprint consecutif ou elle route une famille de ce genre. Je ne prescris aucune forme, je transmets la propriete |
| **Le registre d'identifiants de `PAR-5` n'existe toujours pas** ; s30 y ajoute dix identifiants de plus rien que sur l'Instance B (`SIG-R1`, `SIG-R2`, `MEL-R1`, `MEL-R2`, `MEL-R3`, `POS-R3`, `CHA-R1`, `OBT-R1`, `OBT-R2`, `PAR-11`) | `theo-archi` | Troisieme sprint consecutif. C'est l'une des deux conditions qui bloquent la levee de la retenue de defiance de 1,0 sur la parallelisation ; elle n'est pas levee |

### Ce que cette synthese ne fait pas

- Elle ne prononce aucun Done : les verdicts sont de `nadia-prof`, dans les fiches et les documents
  de verdict cites.
- Elle ne fixe aucun perimetre pour s31.
- Elle ne considere `CONS-1` levee sur aucune entree au-dela des neuf que `nadia-prof` a rejugees
  tenues : le reliquat d'une seule entree reste une dette ouverte, pas une nuance de redaction.
- Elle ne tranche pas le prix de `CONS-3` : routee a `nadia-prof` pour chiffrage au rejeu.


## Synthese apres les verdicts du sprint 31 (2026-09-08) - sept lignes ferment, `EMC-CP-07` ne
## ferme pas, le compte de reserves transmis est corrige

`nadia-prof` a joue en deux instances disjointes, chacune reauditee dans le sprint apres un premier
passage (Instance A : EMC CP et les deux reprises de sciences ; Instance B : les quatre paquets
neufs de sciences CM1). Sources : `squad/sprints/s31-second-passage-de-sciences-et-emc-cp/verdicts-
nadia-instance-A.md`, `reaudit-nadia-instance-A.md`, `verdicts-nadia-instance-B.md`, `reaudit-
nadia-instance-B.md`, les dix fiches de conformite citees dans les lignes ci-dessus, `DEC-0081`
(perimetre de s31) et `DEC-0082` (constat de cloture).

### Les sept mouvements

| Ligne | Instance | Mouvement |
|---|---|---|
| `EMC-CP-01` (emotions d'autrui) | A, premier passage | `non couvert` -> `couvert-valide` |
| `EMC-CP-05` (formules de politesse) | A, premier passage | `non couvert` -> `couvert-valide`, avec mention de couverture partielle due |
| `EMC-CP-03` (exclusion) | A, reaudit apres refus | `non couvert` -> `couvert-valide` |
| `SCI-CM1-01` (etats de la matiere) | B, premier passage | `non couvert` -> `couvert-valide` |
| `SCI-CM1-07` (regimes alimentaires) | B, premier passage | `non couvert` -> `couvert-valide` |
| `SCI-CM1-08` (etapes du developpement) | B, reaudit apres refus | `non couvert` -> `couvert-valide` |
| `SCI-CM1-11` (familles de materiaux) | B, reaudit apres refus | `non couvert` -> `couvert-valide` |

**`EMC-CP-07` (regle, souhait, loi) ne ferme pas** : refusee par l'instance A le 2026-09-07 sur trois
motifs (un fait de droit faux, `FAB-1a`, `FAB-1c`) ; la reprise paie le fait de droit et `FAB-1a` ;
**le refus est maintenu au reaudit du 2026-09-08 sur le seul motif `FAB-1c`** - sur les cinq items de
la tache B, la bonne reponse est la seule proposition depourvue du marqueur restrictif *seulement*
sur 3 a 4 items sur 5 selon la lecture, la ou la barre n'en autorise qu'un. Reste `non couvert`.
Story de reprise : `docs/backlog/backlog.md`, `S32-EMCCP07`.

**Les deux reprises de reserves de s30** (`S31-5` sur `SCI-CM1-02`, `S31-6` sur `SCI-CM1-12`) ne
deplacent aucune ligne : les deux etaient deja `couvert-valide`. Leur effet est trace dans les
lignes elles-memes (sections "Sciences et technologie - CM1" ci-dessus) : `MEL-R1`, `MEL-R2`,
`MEL-R3` et `OBT-R1`, `OBT-R2` levees, une reserve neuve ouverte (`MEL-R4`).

### Le recompte, independant de tout chiffre transmis

Recompte automatise sur les dix-huit tableaux de cette matrice, colonne `Statut`, apres application
des sept verdicts ci-dessus - chaque ligne de donnees a ete reclassee telle qu'elle est ecrite dans
ce fichier apres mise a jour, sans reprendre aucun total d'un autre document.

| | Avant les verdicts (2026-09-07, ouverture de s31) | Apres les sept verdicts de s31 |
|---|---|---|
| `couvert-valide` | 56 | **63** |
| `en cours` | 7 | 7 |
| `non couvert` | 75 | **68** |
| `non couvrable en l'etat` | 71 | 71 |
| Total | 209 | 209 |
| Denominateur atteignable | 138 | 138 |
| **Taux** | **56 / 138 = 40,6 %** | **63 / 138 = 45,7 %** |

**63 / 138 = 0,45652... : 45,7 % arrondi.**

**Position dans les deux fourchettes annoncees d'avance** (`DEC-0081`, section 2) : le plafond
arithmetique de s31 etait de 8 lignes (64/138 = 46,4 %) ; l'attente raisonnable de `nadia-prof`
etait de 5 a 7 lignes (61 a 63/138, soit 44,2 % a 45,7 %). **Le sprint ferme 7 lignes sur 8 visees,
au sommet exact de l'attente raisonnable et a une seule ligne du plafond arithmetique.** Aucun
arrondi n'est fait en faveur du sprint : 63/138 est le point haut de la fourchette annoncee, pas un
point au-dela.

### Le compte de reserves transmis a la cloture etait faux, corrige ici

`DEC-0082` le detaille : les trois lignes d'EMC CP qui ferment portent **neuf** reserves neuves
non bloquantes, et non huit comme annonce a l'ouverture de la cloture - `EMO-R1`, `EMO-R2`,
`POL-R1`, `POL-R2`, `EXC-R1`, `EXC-R2`, `EXC-R3`, `EXC-R4`, `EXC-R5`. L'enumeration citait deja les
neuf identifiants ; seul le total les precedant s'est trompe. Corrige des l'ecriture de cette
synthese pour qu'aucun document ulterieur ne reprenne le chiffre faux.

### Les mentions de couverture partielle, verifiees contre les fiches

**Une seule mention de couverture partielle est due, sur `EMC-CP-05`**, reprise mot pour mot depuis
`squad/conformite/FICHE-bandit-manchot-formules-de-politesse-cp.md` : *"connaissance des formules
seule ; leur emploi en situation n'est pas servi"*. Elle est portee dans la ligne `EMC-CP-05`
ci-dessus. **Aucune des six autres lignes fermees n'en porte** : verifie contre chacune des dix
fiches de conformite du sprint, aucune n'exige de mention au-dela de celle-ci.

### Ce que je route, en plus du backlog

| Fait | A qui | Ce qu'il en fait |
|---|---|---|
| **`CAT-1`, `REG-R1`, `PER-R1` restent impayees**, et le report atteint son quatrieme sprint consecutif sans paiement (s29, s30, s31, s32) | `sofia-sm` | Nomme comme priorite de planification pour s32 dans `DEC-0082` ; story deja ecrite, `S32-CAT1`, inchangee |
| **`EMC-CP-07` reste ouverte**, motif `FAB-1c` seul | `sofia-sm` | Story ecrite, `S32-EMCCP07` |
| **Le registre d'identifiants de `PAR-5`** ne peut pas voir une allocation faite dans une branche soeur non fusionnee (forme (c) de defaut de couture, constatee par `nadia-prof` a l'assemblage) | `theo-archi` | Route par `nadia-prof` elle-meme, section 5.5 de son verdict d'instance A ; je ne le retranche pas |

### Ce que cette synthese ne fait pas

- Elle ne prononce aucun Done : les sept verdicts sont de `nadia-prof`, dans les documents et les
  fiches cites.
- Elle ne fixe aucun perimetre pour s32 au-dela des deux priorites nommees ci-dessus.
- Elle ne rejuge ni le refus maintenu sur `EMC-CP-07`, ni aucun des sept verdicts qui ferment une
  ligne.
- Elle ne rouvre pas `DEC-0081` : le constat sur le resultat de la composition retenue est ecrit
  dans `DEC-0082`, a part de l'arbitrage lui-meme.

## Synthese apres les verdicts du sprint 32 (2026-09-09) - trois lignes ferment sur huit visees,
## quatre refus, le declencheur de la borne de s33 s'evalue sur un fait

`nadia-prof` a joue en trois instances disjointes pour la premiere fois (`DEC-0083`). Sources :
`squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/verdicts-nadia-instance-A.md`, `-B.md`,
`-C.md`, les dix fiches de conformite qu'elles citent, `planning.md`, `rapport-qualite.md`,
`confirmation-archunit.md`, `relecture.md` (meme repertoire de sprint), `DEC-0083` (perimetre de
s32) et `DEC-0084` (constat de cloture).

### Les trois mouvements

| Ligne | Instance | Mouvement |
|---|---|---|
| `HG-CM1-01` (frise des grandes periodes) | A | `en cours` -> `couvert-valide`, avec mention de couverture partielle due |
| `HG-CM1-03` (traces d'occupation ancienne) | A | `en cours` -> `couvert-valide` |
| `LVE-CM1-07` (decouverte culturelle CM1) | A | `en cours` -> `couvert-valide`, VALIDE SOUS RESERVE, avec mention de couverture partielle due |

### Les quatre refus, aucun ne deplace de ligne

- **`HG-CM1-02`** (instance A) - refusee sur `HG-R11`, bloquante : deux items donnent en bonne
  reponse et en distracteur "le sacre de Clovis, roi des Francs, en 481", fait faux que le meme
  fichier dement lui-meme ailleurs. Reste `en cours`. Story de reprise : `S33-HG-02`.
- **`HG-CM1-04`** (instance A) - refusee sur `HG-R12`, bloquante : l'item sur l'armee arretee a
  Poitiers en 732 donne "les Sarrasins" comme nom de peuple, terme qu'aucun groupe n'a jamais
  revendique pour lui-meme. Reste `en cours`. Story de reprise : `S33-HG-04`.
- **`EMC-CP-07`** (instance B, troisieme passage) - refusee sur un motif entierement neuf : l'item 4
  de la tache A marque fausse une proposition vraie ("le casque a velo obligatoire" **est** une
  regle qui vaut pour tous) et l'item 11 porte un distracteur mort. `FAB-1c`, le motif du deuxieme
  refus, est paye. Reste `non couvert`. Story de reprise : `S33-EMCCP07` (quatrieme passage).
- **`EPS-CP-04`** et **`EPS-CP-08`** (instance C, premier contact) - refusees toutes deux. Sur
  `EPS-CP-04`, la tache B se gagne au champ lexical du danger sur 4 items sur 5 (`SEC-R3`, `CAT-1`
  transposee au registre du danger). Sur `EPS-CP-08`, quatre items sur onze enseignent ou
  verifient quelque chose de faux (`JEU-R1` a `JEU-R5`), et deux de plus se gagnent a la forme de
  l'enonce (`JEU-R6`). Restent `non couvert`. Stories de reprise : `S33-EPS-SEC`, `S33-EPS-JEU`.

**`CAT-1`, paiement refuse sur `EMC-CP-02` et `EMC-CP-06`** (instance B) : le correctif rend une
proposition serieuse par item la ou la clause en demande trois, et remplace le repere de registre
par un repere lexical (*maitresse* dans 6 distracteurs sur 11, dans aucune bonne reponse). Aucune
des deux lignes n'est rouverte : toutes deux restent `couvert-valide`. Story de reprise :
`S33-CAT1`.

### Le recompte, independant de tout chiffre transmis

Recompte automatise sur les dix-huit tableaux de cette matrice, colonne `Statut`, apres application
des trois verdicts ci-dessus - chaque ligne de donnees a ete reclassee telle qu'elle est ecrite
dans ce fichier apres mise a jour, sans reprendre aucun total d'un autre document.

| | Avant les verdicts (2026-09-08, ouverture de s32) | Apres les trois verdicts de s32 |
|---|---|---|
| `couvert-valide` | 63 | **66** |
| `en cours` | 7 | **4** |
| `non couvert` | 68 | 68 |
| `non couvrable en l'etat` | 71 | 71 |
| Total | 209 | 209 |
| Denominateur atteignable | 138 | 138 |
| **Taux** | **63 / 138 = 45,7 %** | **66 / 138 = 47,8 %** |

**66 / 138 = 0,47826... : 47,8 % arrondi.**

**Position dans les deux fourchettes annoncees d'avance** (`planning.md`, section 3) : le plafond
arithmetique de s32 etait de 8 lignes (71/138 = 51,4 %) ; l'attente raisonnable de `nadia-prof`
etait de 6 a 7 lignes (69 a 70/138, soit 50,0 % a 50,7 %). **Le sprint ferme 3 lignes sur 8 visees,
sous les deux bornes basses des deux fourchettes annoncees.** Ce n'est pas un ecart de mesure : les
quatre refus de contenu neuf (`HG-CM1-02`, `HG-CM1-04`, `EMC-CP-07`, et les deux lignes d'EPS) et le
paiement refuse de `CAT-1` pesent plus que la seule instance C, seule annoncee sans calibration
(`PAR-13`), ne pouvait le laisser prevoir : l'instance A, sur un terrain deja mesure quatre fois,
ferme deux lignes sur quatre visees plutot que les quatre annoncees en plafond.

### Les mentions de couverture partielle, verifiees contre les fiches

**Deux mentions sont dues, toutes deux reprises mot pour mot depuis leur source** :

- `HG-CM1-01`, depuis `squad/conformite/FICHE-bandit-manchot-histoire-geographie-cm1.md` : *"ordre
  et identification des cinq grandes periodes par la lecture seule ; le placement sur une frise
  reste non couvert."*
- `LVE-CM1-07`, depuis `squad/conformite/FICHE-bandit-manchot-anglais-cm1.md` : *"decouverte
  culturelle seule, en francais ; aucune competence linguistique n'est servie"*.

**`HG-CM1-03` n'en porte aucune** : verifie contre sa fiche, `nadia-prof` ne l'exige pas.

### Ce que je route, en plus du backlog

| Fait | A qui | Ce qu'il en fait |
|---|---|---|
| **`HG-CM1-02`, `HG-CM1-04` restent `en cours`, refusees** | `sofia-sm` | Stories ecrites, `S33-HG-02` (fait faux sur Clovis, mot pour mot) et `S33-HG-04` (le terme "les Sarrasins") |
| **`EMC-CP-07` refusee au troisieme passage**, motif neuf | `sofia-sm` | Story ecrite, `S33-EMCCP07` |
| **`CAT-1` non payee** sur `EMC-CP-02` et `EMC-CP-06` | `sofia-sm` | Story ecrite, `S33-CAT1` |
| **`EPS-CP-04`, `EPS-CP-08` refusees au premier contact** | `sofia-sm` | Stories ecrites, `S33-EPS-SEC` et `S33-EPS-JEU` |
| **`EXC-R1`, `EXC-R5`, `CONS-4`, `SCO-1`** (dette de portee D et de contenu, non fermees par s32) | `sofia-sm` | Stories ecrites, `S33-EXC-R1`, `S33-EXC-R5`, `S33-CONS-4` ; `SCO-1` reste routee au remede technique de `theo-archi` |
| **Une incoherence du referentiel** (`LVE-CM1-07` ne porte pas la parenthese "fetes, lieux, vie quotidienne" que `LVE-CP-06` porte, meme competence, deux niveaux) | `nadia-prof` a signale, je trace | Non corrigee ici : `nadia-prof` est la source du referentiel, elle tranche si la parenthese doit etre ecrite aux deux niveaux |

### Ce que cette synthese ne fait pas

- Elle ne prononce aucun Done : les trois verdicts qui ferment une ligne, et les quatre qui n'en
  ferment aucune, sont de `nadia-prof`, dans les documents et les fiches cites.
- Elle ne fixe aucun perimetre pour s33 au-dela des priorites nommees ci-dessus.
- Elle ne rejuge aucun des sept verdicts de ce sprint, ni les trois qui ferment une ligne, ni les
  quatre refus, ni le paiement refuse de `CAT-1`.
- Elle ne rouvre pas `DEC-0083` : le constat sur le resultat contre le plan est ecrit dans
  `DEC-0084`, a part de l'arbitrage lui-meme.

## Synthese apres les verdicts du sprint 33 (2026-09-10) - trois lignes ferment en
## histoire-geographie CM1, quatre refus, aucune ligne d'EMC ni d'EPS ne bouge

Trois instances de `nadia-prof` ont audite en parallele, sur des terrains disjoints
(histoire-geographie et anglais CM1, EMC CP en entier, education physique et sportive CP).
Sources : `squad/sprints/s33-plan-2-tenu-le-palier-2-differe-a-s34/verdicts-nadia-instance-A.md`,
`-B.md`, `-C.md`, `planning.md` (sections 13 et 14), `rapport-qualite.md`,
`confirmation-archunit.md`, `arbitrage-produit-camille.md`, `relecture.md` (meme repertoire de
sprint), `review.md`.

### Les trois mouvements, tous sur le meme terrain (instance A)

| Ligne | Instance | Mouvement |
|---|---|---|
| `HG-CM1-02` (fait faux du sacre de Clovis) | A | `en cours` -> `couvert-valide` |
| `HG-CM1-04` (le terme "les Sarrasins") | A | `en cours` -> `couvert-valide` |
| `HG-CM1-08` (Revolution a l'Empire) | A | `en cours` -> `couvert-valide`, sur sa part servie |

### Les quatre refus, aucun ne deplace de ligne

- **`HG-CM1-12`** (instance A) - refusee sur `HG-R17`, bloquante : sur les dix-huit distracteurs
  neufs de la ligne, quatorze appartiennent au registre de la ferme, du champ, de la peche ou de
  la batellerie, et aucune des dix bonnes reponses n'y appartient - un enfant qui a joue deux
  parties repond au registre sans lire la question. Reste `en cours`. Story de reprise a ecrire
  pour s34.
- **`EMC-CP-07`** (instance B, quatrieme passage) - refusee sur un motif que `nadia-prof` avait
  elle-meme bloque par un chiffrage errone (section "Ce que je route" ci-dessous) : l'item 4 de la
  tache A marque toujours fausse une proposition vraie (`EMC-11`, `EMC-5`), verifie a l'ecran.
  `PLA-1` sur l'item 11 est payee. Reste `non couvert`. Le blocage qui empechait la reprise est
  leve par `nadia-prof` elle-meme dans son propre verdict (section 8) ; la ligne est fermable a
  s34.
- **`EPS-CP-04`** (instance C, second refus) - refusee sur `SEC-R4`, bloquante : sur deux items,
  l'enonce met l'enfant sur sa trottinette, les propositions le decrivent a pied, et la reponse
  creditee ("regarder des deux cotes") n'est pas la regle qui s'applique (descendre de sa
  trottinette et la pousser). Les onze motifs de s32 sont leves. Reste `non couvert`.
- **`EPS-CP-08`** (instance C, second refus) - refusee sur `JEU-R9`, bloquante : l'item 8 credite
  une sanction de l'epervier (elimination) que l'item 3 du meme paquet contredit cinq items plus
  haut (changement de role), et marque fausse une reponse defendable. Les onze motifs de s32 sont
  leves, `PAR-14` (tarif de lot) applique et toutes les autres conditions du paquet reverifiees.
  Reste `non couvert`.

**`CAT-1`, paiement sur `EMC-CP-02` et `EMC-CP-06`** (instance B) : **payee sous reserve** au
cinquieme sprint de report - 8 items sur 11 tiennent la clause entiere sur `regles-de-vie` (2 a
s32), 4 sur 4 sur `permis-interdit` (1 a s32). Aucune des deux lignes ne bouge : toutes deux
restent `couvert-valide`, deja fermees. Deux reserves neuves non bloquantes (`REG-R4`, `REG-R5`)
plus `PER-R3`.

**`EXC-R5` levee, l'increment `S33-EXC-R5` refuse** (instance B) : la reserve de s32 est executee
dans les termes exacts ou `nadia-prof` l'avait ecrite, mais sa propre formule cree trois defauts
neufs (`EXC-R7`, `EXC-R8`, `EXC-R9`) sur `bandit-manchot-exclusion-cp`, dont l'un retire du
paquet la seconde moitie du libelle de `EMC-CP-03`. **`EMC-CP-03` n'est pas rouverte** et reste
`couvert-valide` ; `nadia-prof` nomme que ce n'est plus le contenu qui l'a fermee qui est a
l'ecran aujourd'hui, sans en tirer de mouvement de matrice. `EXC-R1` non payee : la consigne
corrigee nomme une tache que le meme commit a fait disparaitre du paquet.

### Le recompte, independant de tout chiffre transmis

Recompte sur les dix-huit tableaux de cette matrice, colonne `Statut`, apres application des
trois mouvements ci-dessus - chaque ligne de donnees reclassee telle qu'elle est ecrite dans ce
fichier apres mise a jour, sans reprendre aucun total d'un autre document.

| | Avant les verdicts (2026-09-09, ouverture de s33) | Apres les verdicts de s33 |
|---|---|---|
| `couvert-valide` | 66 | **69** |
| `en cours` | 4 | **1** |
| `non couvert` | 68 | 68 |
| `non couvrable en l'etat` | 71 | 71 |
| Total | 209 | 209 |
| Denominateur atteignable | 138 | 138 |
| **Taux** | **66 / 138 = 47,8 %** | **69 / 138 = 50,0 %** |

**69 / 138 = 0,5 exactement : 50,0 %.**

**Position dans les deux fourchettes annoncees d'avance** (`planning.md`, section 7) : le plafond
arithmetique de s33 etait de 9 lignes (75/138 = 54,3 %) ; l'attente raisonnable de `nadia-prof`
etait de 4 a 6 lignes (70 a 72/138, soit 50,7 % a 52,2 %). **Le sprint ferme 3 lignes sur 9
visees au plafond, sous la borne basse de l'attente raisonnable**, exactement le comportement que
le planning avait ecrit comme scenario de reference si s33 se comportait comme s32 (section 7) :
"il ferme 3 lignes et la couverture atteint 69/138 = 50,0 %." Deux des neuf lignes visees n'ont
jamais recu de story (`docs/backlog/backlog.md`, section 10 du planning) et deux autres restent
bloquees par un arbitrage pedagogique non rendu (`S33-CONS-4`, `S33-SCO-1`) : le plafond
arithmetique de 9 ne comptait ni les unes ni les autres comme des refus, mais comme des lignes
jamais engagees.

### Aucune mention de couverture partielle neuve n'est due

Verifie contre les trois verdicts : aucune des trois lignes qui ferment ce sprint ne porte de
mention de couverture partielle exigee d'avance par `nadia-prof`. `HG-CM1-12` reste `en cours` et
n'en porte donc aucune non plus.

### Ce que je route, en plus du backlog

| Fait | A qui | Ce qu'il en fait |
|---|---|---|
| **`HG-CM1-12` reste `en cours`, refusee** (`HG-R17`, bloquante) | `sofia-sm` | Story a ecrire pour s34, reecriture des distracteurs de la ligne, aucun item neuf requis |
| **`EMC-CP-07` refusee au quatrieme passage**, blocage leve par `nadia-prof` elle-meme | `sofia-sm` | Perimetre corrige (propositions plus un mouvement d'item, plus deux tests de paquet a reprendre), ligne fermable a s34 |
| **`EPS-CP-04`, `EPS-CP-08` refusees au second passage** | `sofia-sm` | Stories a ecrire pour s34 ; `PAR-14` s'applique deja a `EPS-CP-04` (correction de chiffrage de `nadia-prof`, section 4.4 de son verdict) |
| **Six reserves neuves sur l'EMC CP** (`REG-R4`, `REG-R5`, `PER-R3`, `EXC-R7`, `EXC-R8`, `EXC-R9`) plus `EXC-R1` toujours impayee | `sofia-sm`, via `camille-po` | Stories a ecrire ; `EXC-R7` a `EXC-R9` et `EXC-R1` voyagent dans une seule story sur la tache B d'exclusion |
| **`EPS-8`, condition neuve sur les indices d'EPS** | toute l'equipe | Opposable au prochain lot d'EPS, allouee au registre |
| **La classe de defaut nommee par l'instance C** : une correction executee a la lettre de la reserve et pas a son objet, qu'aucun garde-fou ne voit | `sofia-sm`, pour la retro | Troisieme sprint consecutif ou cette classe apparait (`CAT-1` a s32, `EMC-11` a s33 sur l'EMC, `JEU-R9` ici) |
| **Le defaut structurel du registre d'identifiants** (une allocation ne peut pas voir un document non commite d'une instance soeur, meme sur un poste partage) | `theo-archi` (route par l'instance B, clause 3 d'`ADR-0085`) | Cause a traiter, non un defaut de personne |
| **Cinq motifs d'exemption a comptes perimes** sur `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | `theo-archi`, regle deja ecrite, non commitee | Dette datee pour s34 : la regle et les cinq corrections dans le meme commit |
| **L'instruction 1 de `DEC-0061`** (ce que trois essais doit vouloir dire quand l'elimination est possible) | `nadia-prof`, a son propre debit | Neuf sprints d'attente, portee a s34 comme dette de sa propre porte |
| **Le perimetre corrige d'`EMC-CP-07`** (0,5 -> ce que la sortie retenue coutera reellement) | `camille-po`, `sofia-sm` | Chiffrage a reprendre au planning de s34 |
| **Le littoral mort "evenement culturel"** dans `PaquetCm1HistoireGeographieTest` (la correction orthographique du relecteur a vide une branche du predicat) | `theo-archi` | Chaine litterale a mettre a jour dans un prochain diff du fichier |
| **L'hygiene de poste** : des sessions de jeu manuel sur le jar tournant sur le worktree partage plutot que sur un worktree dedie, serveur non arrete | `yanis-qa` (signale), `sofia-sm` | A rappeler a la squad, sans quoi une mesure de `build` peut echouer pour un motif qui n'appartient pas au commit mesure |
| **Trois questions de grain de `S33-CONS-4`** que `camille-po` a rendues et route a `nadia-prof` | `nadia-prof` | Sans reponse a la cloture de s33 : reste comme demande, pas comme acquis |
| **Le grain du lot de l'effort et du corps** (`EPS-CP-10`/`-11`, un seul paquet) | `nadia-prof` | Position de `camille-po` rendue et convergente avec la sienne ; se lit comme regle des qu'elle confirme |

### Ce que cette synthese ne fait pas

- Elle ne prononce aucun Done : les trois verdicts qui ferment une ligne, et les quatre refus, sont
  de `nadia-prof`, dans les documents cites.
- Elle ne fixe aucun perimetre pour s34 au-dela des priorites nommees ci-dessus.
- Elle ne rejuge aucun des sept verdicts de ce sprint (trois fermetures, quatre refus), ni le
  paiement de `CAT-1`, ni la levee de `EXC-R5`.
- Elle ne rouvre pas `DEC-0085` : le constat sur le resultat contre le plan est ecrit dans
  `review.md` et dans `RAPPORT.md`.

## Synthese apres les verdicts du sprint 34 (2026-09-11) - six lignes ferment, le plafond de base
## est atteint pour la premiere fois, le palier 2 ne ferme pas

Trois instances de `nadia-prof` ont audite en parallele, sur des terrains disjoints
(histoire-geographie et anglais CM1 plus la portee transverse, EMC CP et le premier lot du
palier 2, education physique et sportive CP). Sources :
`squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/verdicts-nadia-instance-A.md`, `-B.md`,
`-C.md`, `review.md`, `DEC-0086`.

### Les six mouvements, chacun avec le verdict qui l'autorise

| Ligne | Mouvement | Instance | Verdict |
|---|---|---|---|
| `HG-CM1-12` | `en cours` -> `couvert-valide` | A | `verdicts-nadia-instance-A.md`, section 3 |
| `EMC-CP-07` | `non couvert` -> `couvert-valide` | B, cinquieme passage | `verdicts-nadia-instance-B.md`, section 2 |
| `EPS-CP-04` | `non couvert` -> `couvert-valide` | C | `verdicts-nadia-instance-C.md`, section 3.1 |
| `EPS-CP-08` | `non couvert` -> `couvert-valide` | C | `verdicts-nadia-instance-C.md`, section 3.2 |
| `EPS-CP-10` | `non couvert` -> `couvert-valide` | C | `verdicts-nadia-instance-C.md`, section 3.3 |
| `EPS-CP-11` | `non couvert` -> `couvert-valide` | C | `verdicts-nadia-instance-C.md`, section 3.3 |

**Deux mentions de couverture partielle inedites, ecrites aujourd'hui par l'instance C parce
qu'elles n'existaient pas** (`verdicts-nadia-instance-C.md`, section 3.4) : celles de `EPS-CP-10`
et `EPS-CP-11`, reprises mot pour mot dans leurs lignes ci-dessus. Celles de `EPS-CP-04` et
`EPS-CP-08` etaient deja pre-ecrites dans cette matrice et sont reprises telles quelles. Aucune
mention n'est due sur `HG-CM1-12` ni sur `EMC-CP-07`.

### Trois refus, aucun ne deplace de ligne

- **`S34-SCO1-REMEDE`** (`SCO-R1`, bloquante, instance A) : le remede des trois essais est juste et
  verifie sur les trois mecaniques, mais l'ecran de fin de partie n'affiche que le score reduit,
  sans dire a l'enfant qu'il a fait dix questions - trou de perimetre nomme d'avance par
  `nadia-prof` et realise a l'endroit exact ou elle l'avait ecrit. Portee transverse : aucune ligne
  de cette matrice n'est concernee, par construction.
- **`S34-EXC-TACHEB`** (`EXC-R10`, bloquante, instance B) : les cinq indices de la tache B de
  `cp-emc-exclusion` n'ont pas ete touches par la refonte et repondent encore a la question que
  celle-ci supprime. `EMC-CP-03` **n'est pas rouverte** et reste `couvert-valide`.
- **`QLM-CP-14`** (`FRI-R1`, `FRI-R2`, bloquantes, instance B) : documente dans sa ligne
  ci-dessus (section "Questionner le monde - CP"). Reste `non couvert`.

### Ce qui ne bouge pas, et pourquoi

- **`QLM-CP-11`** : entree minimale du palier 2 engagee par l'utilisatrice (`DEC-0086`, section
  12.1), jamais livree - deux conditions de design et de pedagogie restent a trancher avant tout
  contenu (`ZON-15`, `ZON-16`). Note ajoutee dans sa ligne. Reste `non couvert`.
- **`LVE-CM1-07`** : deja `couvert-valide` depuis s32. La correction de ce sprint (`S34-ANG-CORR`)
  est VALIDE SOUS RESERVE et ne bouge aucune ligne - `ANG-R4` a `ANG-R6` levees, `ANG-R3` payee sur
  deux items des trois. Note ajoutee dans sa ligne.
- **`S34-SCO1-REMEDE`** : refuse, portee transverse, ne porte aucune ligne de cette matrice (voir
  ci-dessus).

### Le recompte, independant de tout chiffre transmis

Recompte sur les dix-huit tableaux de cette matrice, colonne `Statut`, avant et apres application
des six mouvements ci-dessus - chaque ligne de donnees reclassee telle qu'elle est ecrite dans ce
fichier apres mise a jour, sans reprendre aucun total d'un autre document. **Methode** : seules les
lignes des dix-huit tableaux de competences comptent (les tableaux recapitulatifs des sections de
synthese anterieures, sur `matiere niveau | total | ...`, n'en font pas partie) ; le statut d'une
ligne est celui que porte le **debut** de sa cellule `Statut`, non une mention qu'elle citerait
plus loin en la niant (`QLM-CP-11` ecrit explicitement "non `non couvrable en l'etat`" dans sa
propre cellule `non couvert` - une recherche de sous-chaine naive la classerait a tort).

| | Avant les verdicts (2026-09-10, ouverture de s34) | Apres les verdicts de s34 |
|---|---|---|
| `couvert-valide` | 69 | **75** |
| `en cours` | 1 | **0** |
| `non couvert` | 68 | **63** |
| `non couvrable en l'etat` | 71 | 71 |
| Total | 209 | 209 |
| Denominateur atteignable | 138 | 138 |
| **Taux** | **69 / 138 = 50,0 %** | **75 / 138 = 54,3 %** |

**Je confirme le chiffre de sortie de `sofia-sm` (75/138 = 54,3 %) : mon recompte independant sur
les dix-huit tableaux, apres correction de deux defauts de ma propre premiere passe (une
sous-chaine niee comptee comme positive, un statut en gras non reconnu), tombe exactement sur
69/138 a l'entree et 75/138 a la sortie.**

**Position dans les deux fourchettes annoncees d'avance** (`DEC-0086`, section 12.2, apres
amendement) : le plafond arithmetique de s34 etait de 8 lignes (77/138 = 55,8 %, palier 2 compris) ;
l'attente raisonnable de `nadia-prof`, non etendue au palier 2, etait de 2 a 4 lignes sur les six
postes prioritaires du vivier (71 a 73/138, soit 51,4 % a 52,9 %). **Le sprint ferme les six lignes
du plafond de base (75/138 = 54,3 %) - au-dessus de l'attente haute de 4, et pour la premiere fois
depuis l'ouverture de cette strategie le plafond de base est atteint a l'unite pres** (s32 : 3
lignes sur 8 visees ; s33 : 3 lignes sur 9 visees ; s34 : 6 lignes sur 6). **Les deux lignes du
palier 2 ne ferment pas** : l'extension que le palier 2 devait apporter (+2, jusqu'a 77/138 =
55,8 %) reste entierement non realisee.

### Ce que cette synthese ne fait pas

- Elle ne prononce aucun Done : les six verdicts qui ferment une ligne, et les trois refus, sont de
  `nadia-prof`, dans les documents cites.
- Elle ne fixe aucun perimetre pour s35.
- Elle ne rejuge aucun des neuf verdicts de ce sprint (six fermetures, trois refus).
- Elle ne mesure ni ne ratifie le registre de dette : cette mesure vit dans `DEC-0087` (cloture de
  s34), propriete de `camille-po`, pas dans cette matrice.
- Elle ne rouvre pas `DEC-0086` : le constat sur le resultat contre le plan, et la question de la
  strategie au-dela du Plan 2, vivent dans `DEC-0087`.

## Synthese apres les verdicts du sprint 35 (2026-09-11) - un seul mouvement, le refus attendu ne ferme pas

`nadia-prof`, une seule instance ce sprint, a rendu six verdicts sur des terrains disjoints (ecran
de fin de partie transverse, indices d'EMC CP, frise de la journee, plan de la classe, correction
d'anglais CM1, scission d'histoire-geographie CM1). Source :
`squad/sprints/s35-fermer-ce-qui-n-a-pas-recu-le-go-pedagogique/verdicts-nadia.md`.

### L'unique mouvement, avec le verdict qui l'autorise

| Ligne | Mouvement | Verdict |
|---|---|---|
| `QLM-CP-14` | `non couvert` -> `couvert-valide`, VALIDE SOUS RESERVE, mention de couverture partielle levee | `FICHE-designation-frise-de-la-journee.md`, bloc de validation du second passage (s35) ; `verdicts-nadia.md`, sections 1 et 5 |

**Un seul mouvement**, ce qui est exactement ce que l'objectif gele du sprint prevoyait (*"s35 ferme
des refus, il n'ouvre pas de couverture"*, `verdicts-nadia.md`, section 1).

### Un refus, qui ne deplace aucune ligne

- **`S35-QLM11-PLANCLASSE`** (`ZON-15`, bloquante, sur les six zones sur six) : documente dans sa
  ligne ci-dessus (section "Questionner le monde - CP"). `QLM-CP-11` **reste** `non couvert`,
  cinquieme sprint sur cette ligne a l'ouverture du sprint suivant. Le refus ne bloque pas la
  cloture de s35 (`verdicts-nadia.md`, section 7).

### Ce qui ne bouge pas, et pourquoi

- **`HG-CM1-01`, `-02`, `-03`, `-04`, `-06`, `-07`, `-08`, `-11`, `-12`, `-13`, `-14`** : deja
  `couvert-valide`. La scission de `bandit-manchot-histoire-geographie-cm1` en quatre entrees de
  catalogue neuves ne rejuge aucun item et ne deplace aucun statut - seule la colonne `Jeu(x)
  couvrant` de ces onze lignes est mise a jour ci-dessus pour nommer l'entree qui sert chacune.
  Reserve neuve, non bloquante : `CONS-5`.
- **`EMC-CP-03`** : deja `couvert-valide`. `EXC-R10` levee, `EXC-R11` soldee ; la ligne n'est pas
  rouverte.
- **`LVE-CM1-07`** : deja `couvert-valide`. `ANG-R3` payee sur son troisieme item et sur sa seconde
  clause, `ANG-R7` levee ; la ligne n'est pas rouverte. Reserve neuve, non bloquante : `ANG-R8`.
- **La portee transverse de l'ecran de fin de partie** : `SCO-R1` levee ; aucune ligne de cette
  matrice n'est concernee, par construction. Reserve neuve, non bloquante : `SCO-R4`.

### Le recompte, independant de tout chiffre transmis

Meme methode qu'a la cloture de s34 (section precedente) : recompte sur les dix-huit tableaux de
cette matrice, colonne `Statut`, avant et apres application de l'unique mouvement ci-dessus - chaque
ligne de donnees reclassee telle qu'elle est ecrite dans ce fichier apres mise a jour, sans reprendre
aucun total d'un autre document. Deux lignes du tableau de sciences et technologie CM1
(`SCI-CM1-02`, `SCI-CM1-12`) portent une cellule `Statut` sans le pipe de fermeture final - defaut de
forme pre-existant, sans effet sur leur classement (`couvert-valide` dans les deux cas), signale ici
plutot que corrige en silence puisqu'il n'est pas dans le perimetre de cette tache.

| | Avant les verdicts (2026-09-10, ouverture de s35) | Apres les verdicts de s35 |
|---|---|---|
| `couvert-valide` | 75 | **76** |
| `en cours` | 0 | 0 |
| `non couvert` | 63 | **62** |
| `non couvrable en l'etat` | 71 | 71 |
| Total | 209 | 209 |
| Denominateur atteignable | 138 | 138 |
| **Taux** | **75 / 138 = 54,3 %** | **76 / 138 = 55,1 %** |

### Ce que cette synthese ne fait pas

- Elle ne prononce aucun Done : le mouvement et le refus sont de `nadia-prof`, dans
  `verdicts-nadia.md` et dans les fiches citees.
- Elle ne fixe aucun perimetre pour le sprint suivant.
- Elle ne mesure ni ne ratifie le registre de dette : les six reserves neuves de ce sprint sont
  enregistrees dans `squad/decisions/produit/REGISTRE-reserves-neuves-s35.md`, qui ne recalcule pas
  non plus le total du registre - cette mesure reste due a la cloture, comme a s34 (`DEC-0087`).

## Synthese apres les verdicts du sprint 39 (2026-09-22) - quatre fermetures, deux ouvertures partielles, aucun refus

`nadia-prof` a joue le jar servi des six lots de `DEC-0092` et prononce six Done pedagogiques,
aucun refus. Quatre lignes ferment sans reserve ; deux lignes, deja limitees d'avance par un
arbitrage prealable a leur sous-partie textuelle, s'ouvrent sans pouvoir fermer sur ce seul Done.

| Lot | Ligne | Verdict | Mouvement de ligne |
|---|---|---|---|
| Monnaie CE1 (`bandit-manchot-monnaie-ce1`) | `MAT-CE1-13` | **VALIDE**, sans reserve | `non couvert` -> **`couvert-valide`** |
| Energie CM1 (`bandit-manchot-energie-cm1`) | `SCI-CM1-04` | **VALIDE**, sans reserve | `non couvert` -> **`couvert-valide`** |
| Outils arts plastiques CP (`bandit-manchot-outils-arts-cp`) | `ART-CP-11` | **VALIDE**, sans reserve | `non couvert` -> **`couvert-valide`** |
| Solides CE1 (`bandit-manchot-solides-ce1`) | `MAT-CE1-16` | **VALIDE**, sans reserve | `non couvert` -> **`couvert-valide`** |
| Nombres jusqu'a 100 CP (`bandit-manchot-nombres-jusqu-a-100-cp`) | `MAT-CP-01` | **VALIDE, limite a la part textuelle** (lire, ecrire, decomposer) | `non couvert` -> **`en cours`** |
| Longueurs CE1 (`bandit-manchot-longueurs-ce1`) | `MAT-CE1-09` | **VALIDE, limite a la part textuelle** (comparer, utiliser cm/m) | `non couvert` -> **`en cours`** |

**Les deux ouvertures ne sont pas des reserves de ma part.** Sur `MAT-CP-01`, le verbe "denombrer"
(`DEN-2`) n'est pas servi par ce paquet et ne peut pas l'etre par une mecanique textuelle - constat
verifie sur le code par `theo-archi` et reconfirme par `nadia-prof` le jour meme
(`squad/conformite/FICHE-bandit-manchot-nombres-jusqu-a-100-cp.md`). Sur `MAT-CE1-09`, le verbe
"mesurer... avec une regle graduee" n'est pas servi par ce paquet et ne peut pas l'etre par du
texte seul - aucun rendu de regle graduee n'existe a ce jour
(`squad/conformite/FICHE-bandit-manchot-longueurs-ce1.md`). Les deux fiches le disent dans les
memes mots : *"la ligne reste `en cours` quel que soit ce Done [...] ce n'est pas une reserve
actionnable dans ce sprint, c'est une limite deja actee"*.

### Le recompte, independant de tout chiffre transmis

Recompte sur les dix-neuf tableaux de cette matrice, colonne `Statut`, avant et apres application
des six mouvements ci-dessus - chaque ligne de donnees reclassee telle qu'elle est ecrite dans ce
fichier apres mise a jour. Base "avant" reprise du recompte verifie a la fermeture de `MAT-CE1-03`
(s38, section "Mathematiques - CE1 (cycle 2)", sous-section "Fermeture de `MAT-CE1-03` (s38)") :
couvert-valide 78, en cours 0, non couvert 77, non couvrable en l'etat 71, total 226, denominateur
atteignable 155 - pas un chiffre recopie d'un autre document.

| | Avant les verdicts (2026-09-22, avant s39) | Apres les six verdicts |
|---|---|---|
| `couvert-valide` | 78 | **82** |
| `en cours` | 0 | **2** |
| `non couvert` | 77 | **71** |
| `non couvrable en l'etat` | 71 | 71 |
| Total | 226 | 226 |
| Denominateur atteignable | 155 | 155 |
| **Taux** | **78 / 155 = 50,3 %** | **82 / 155 = 52,9 %** |

**82 / 155 = 0,529032... : 52,9 % arrondi.** Les quatre fermetures portent le numerateur de 78 a
82 ; les deux ouvertures partielles ne le touchent pas (une ligne qui passe de `non couvert` a
`en cours` ne touche pas le numerateur). Le denominateur atteignable ne bouge pas : `en cours` y
compte deja comme `non couvert`.

### Ce que cette synthese ne fait pas

- Elle ne prononce aucun Done : les six verdicts sont de `nadia-prof`, dans les six fiches citees.
- Elle ne fait passer `MAT-CP-01` ni `MAT-CE1-09` a `couvert-valide` : les deux fiches l'interdisent
  explicitement tant que `DEN-2` (denombrer) et le rendu d'une regle graduee ne sont pas servis.
- Elle ne fixe aucun perimetre pour le sprint suivant.

## Synthese apres les verdicts du sprint 40 (2026-09-23) - six fermetures, un refus leve dans le sprint

`nadia-prof` a joue le jar servi des six lots de `DEC-0093` et prononce six Done pedagogiques.
Quatre ferment sans reserve, un ferme sous reserve non bloquante, un ferme au second audit apres
refus et correction du premier.

| Lot | Ligne | Verdict | Mouvement de ligne |
|---|---|---|---|
| Nutrition CM1 (`bandit-manchot-nutrition-cm1`) | `SCI-CM1-09` | **VALIDE**, sans reserve | `non couvert` -> **`couvert-valide`** |
| Regles de la vie collective CM1 (`bandit-manchot-regles-vie-collective-cm1`) | `EMC-CM1-04` | **VALIDE**, sans reserve | `non couvert` -> **`couvert-valide`** |
| Relier une oeuvre a son contexte CM1 (`bandit-manchot-histoire-des-arts-relier-contexte-cm1`) | `HDA-CM1-05` | **VALIDE**, sans reserve | `non couvert` -> **`couvert-valide`** |
| Extraire une information CM1 (`bandit-manchot-sciences-extraire-information-cm1`) | `SCI-CM1-20` | **VALIDE**, sans reserve, couverture partielle (texte et tableau seuls) | `non couvert` -> **`couvert-valide`** |
| Devise CP (`bandit-manchot-devise-cp`) | `EMC-CP-09` | **VALIDE SOUS RESERVE** (`DVS-CP-R1`, non bloquante) | `non couvert` -> **`couvert-valide`** |
| Regle ou loi CM1 (`bandit-manchot-regle-loi-cm1`) | `EMC-CM1-08` | **REFUSE** au premier audit (verbe de reponse ecrit dans l'enonce de la tache B), **corrige par `marc-dev`**, **VALIDE** au second audit | `non couvert` -> **`couvert-valide`** |

**Le refus de `EMC-CM1-08` n'a pas laisse la ligne ouverte.** Trouve en jouant, pas en lisant le
YAML seul, corrige et rejoue dans le meme sprint : les deux audits sont conserves dans
`squad/conformite/FICHE-bandit-manchot-regle-loi-cm1.md`, le premier non efface.

### Le recompte, independant de tout chiffre transmis

Recompte sur les dix-neuf tableaux de cette matrice, colonne `Statut`, avant et apres application
des six mouvements ci-dessus - chaque ligne de donnees reclassee telle qu'elle est ecrite dans ce
fichier apres mise a jour. Base "avant" reprise du recompte verifie a la cloture de s39 (section
"Synthese apres les verdicts du sprint 39") : couvert-valide 82, en cours 2, non couvert 71,
non couvrable en l'etat 71, total 226, denominateur atteignable 155 - pas un chiffre recopie d'un
autre document.

| | Avant les verdicts (2026-09-23, avant s40) | Apres les six verdicts |
|---|---|---|
| `couvert-valide` | 82 | **88** |
| `en cours` | 2 | 2 |
| `non couvert` | 71 | **65** |
| `non couvrable en l'etat` | 71 | 71 |
| Total | 226 | 226 |
| Denominateur atteignable | 155 | 155 |
| **Taux** | **82 / 155 = 52,9 %** | **88 / 155 = 56,8 %** |

**88 / 155 = 0,567741... : 56,8 % arrondi.** Les six fermetures portent le numerateur de 82 a 88 ;
aucune ouverture partielle ce sprint, `en cours` reste a 2. Le denominateur atteignable ne bouge
pas.

### Ce que cette synthese ne fait pas

- Elle ne prononce aucun Done : les six verdicts sont de `nadia-prof`, dans les six fiches citees.
- Elle ne fixe aucun perimetre pour le sprint suivant.
- Elle ne deplace aucune ligne autre que les six citees ci-dessus.
