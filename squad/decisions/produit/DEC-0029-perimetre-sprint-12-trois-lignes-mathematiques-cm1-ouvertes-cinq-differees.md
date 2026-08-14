# DEC-0029 - Perimetre du sprint 12 : trois lignes de mathematiques CM1 ouvertes, cinq differees dont deux sous reserve d'arbitrage prealable

- **Type** : portee
- **Date** : 2026-08-14
- **Auteur** : camille-po
- **Sprint** : s12
- **Competence programme visee** : mathematiques, CM1 (cycle 3) - douze lignes du referentiel
  (`squad/programme/mathematiques/competences-cm1.md`), toutes `non couvert` a ce jour
- **Statut** : Acceptee

## Contexte

L'utilisatrice veut que le sprint 12 couvre les lignes de mathematiques CM1 par la mecanique
existante (bandit manchot). La matrice compte douze lignes de mathematiques CM1, toutes
`non couvert` - le meilleur rendement disponible au backlog, une partie servable sans code neuf.

Un decoupage prealable de ces douze lignes distinguait huit lignes "servables par un choix parmi
trois ou quatre propositions" et quatre "hors d'atteinte" (multiplication posee, division
euclidienne, tracer des figures, symetrie axiale - une saisie multi-etapes ou du visuel, pas un
choix). Cette decision verifie ce decoupage ligne par ligne contre le referentiel et le schema de
contenu reellement code, et le corrige.

## Ce que le schema existant peut porter sans code neuf

Deux types de paquet existent (`ADR-0006`, `ADR-0007`), verifies dans
`adapters/adapter-content/src/main/java/com/recreo/adapter/content/` :

- `additions-bornees` (`AdditionsBorneesContentSourceFactory`) : combinatoire, **specifique a
  l'addition** (`sommeMaximale`, `nombreDePropositions`, `indice`). Rien dans son code ne generalise
  a la multiplication ou a la division ; l'etendre serait du code neuf, hors de mon perimetre et non
  demande ici.
- `question` (`QuestionContentSourceFactory`) : enumere, generique par matiere - `enonce`,
  `bonneReponse`, `distracteurs`, `indice` obligatoire par item (`ChaquePaquetPorteUnIndiceTest`),
  `explication` optionnelle. C'est ce type, deja utilise par quatre jeux du catalogue, qui porte
  toute ligne exprimable en un enonce texte et un choix parmi ses propositions - **sans ligne de
  Java neuve**, un fichier YAML et une ligne de `catalogue.yaml` suffisent (meme constat que
  `DEC-0019`).

Consequence directe et non anticipee par mon brouillon initial : **les deux fichiers
`contenu-repris/cm1-tables-multiplication.yml` et `contenu-repris/cm1-calcul-mental.yml` ne sont pas
reutilisables tels quels.** Leur schema (`difficulty: { minOperand, maxOperand, operations }`,
`bounds: { operandFloor, operandCeiling, allowedOperations }`) est exactement la configuration de
difficulte taillee pour l'arithmetique que `CLAUDE.md` nomme comme dette n°2 de la version
precedente ; aucune fabrique de `round-two` ne la lit. Servir ces deux lignes demande un contenu
neuf en `type: question` (items enumeres a la main), pas un chargement de ces deux fichiers.

## Le decoupage corrige, ligne par ligne

### Six lignes cleanement servables en `type: question`, sans ambiguite ni proxy pedagogique

- **Calcul mental - tables de multiplication (jusqu'a x10), rappel automatise.** Un seul verbe,
  entierement enumerable (les produits de 1 a 10). Aucune matiere premiere directement chargeable
  (voir ci-dessus) ; contenu neuf a ecrire.
- **Calcul mental - multiplications et divisions simples (multiples de 10, 100).** Enonces du type
  "23 x 10 = ?", "450 / 10 = ?", entierement textuels. `contenu-repris/cm1-calcul-mental.yml` porte
  la bonne idee (multiplier/diviser) mais ni le schema ni le perimetre exact (il melange addition et
  soustraction, absentes de cette ligne, et ne borne pas aux multiples de 10 et 100) : matiere
  premiere d'inspiration seulement, contenu neuf a ecrire.
- **Resoudre des problemes a plusieurs etapes mobilisant les 4 operations.** Enonces de situation,
  meme famille que `cp-problemes-additifs.yaml` en plus complexe. Aucune matiere premiere identifiee
  dans `contenu-repris/` (verifie, aucun fichier ne porte de probleme a plusieurs etapes pour le
  CM1).
- **Convertir des unites de longueur, masse, contenance dans le systeme metrique.** Les trois unites
  sont nommees explicitement par le referentiel : `contenu-repris/quiz/cm1-longueurs.yml`,
  `cm1-masses.yml` et `cm1-contenances.yml` couvrent chacune une des trois, en `question`/`bonne`/
  `distracteurs` - une transposition directe vers `type: question` (renommer les cles, ajouter un
  `indice` par item, absent de ces trois fichiers). Vigilance a l'ecriture : chacun de ces trois
  fichiers porte aussi des items hors conversion (estimation d'ordre de grandeur - "quelle est la
  masse d'une pomme ?" -, identification d'instrument - "avec quoi mesure-t-on une masse ?") que le
  verbe "convertir" ne couvre pas ; les garder melanges sans le dire reproduirait le motif de
  couverture partielle deja sanctionne sur ce produit (l'inverse ici : un paquet qui deborde du verbe
  qu'il pretend couvrir, plutot qu'un paquet qui n'en couvre qu'une part). La story ci-dessous
  restreint donc les criteres au sous-ensemble "conversion".
- **Calculer un perimetre de figures usuelles (carre, rectangle).** `contenu-repris/quiz/
  cm1-perimetre.yml` correspond presque item pour item : 8 de ses 11 questions calculent un
  perimetre de carre ou de rectangle a partir de cotes donnes en texte, aucune figure a tracer ni a
  observer. Les 2 questions sur un triangle equilateral sortent du perimetre exact de cette ligne
  ("figures usuelles (carre, rectangle)" - le referentiel ne nomme pas le triangle) ; les 2 questions
  definitionnelles ("le perimetre d'une figure, c'est...") sont conservables mais ne comptent pas
  comme du calcul. Meilleure matiere premiere identifiee ce sprint.
- **Resoudre des problemes de durees (calcul d'une duree, d'un horaire d'arrivee).**
  `contenu-repris/quiz/cm1-heure.yml` porte deux items reellement conformes ("de 14h a 16h, combien
  de temps s'est ecoule ?", "un film commence a 8h30 et finit a 10h, il dure...") mais aucun sur
  "horaire d'arrivee" (point de depart + duree -> heure d'arrivee), et melange des items de lecture
  de l'heure ("huit heures et quart, c'est...") qui relevent de la ligne CP "lire l'heure pleine sur
  une horloge" (`non couvert`, hors perimetre CM1), pas de cette ligne-ci. Matiere premiere partielle,
  contenu neuf necessaire sur la moitie "horaire d'arrivee".

### Deux lignes que mon brouillon initial classait "servables" a tort sans reserve : partiellement servables, arbitrage pedagogique prealable requis avant tout code

**Lire, ecrire, decomposer et comparer les nombres entiers jusqu'au million.** Quatre verbes
bundles en une seule ligne. "Decomposer" (choisir la bonne decomposition en valeurs de position
parmi des distracteurs) et "comparer" (meme patron que `cp-comparer-nombres.yaml`, a l'echelle du
million) sont servables sans aucune ambiguite. "Lire" et "ecrire", en revanche, ne peuvent etre
servis par ce mecanisme qu'au prix d'un **proxy** : transcrire un nombre entre son ecriture
chiffree et son ecriture en toutes lettres ("comment ecrit-on 305 421 en lettres ?"), plutot que de
faire lire le nombre a voix haute - la mecanique n'a et n'aura, sauf decision contraire, aucune
modalite orale. Ce proxy est une pratique courante d'evaluation ecrite de la lecture des nombres,
mais **ce n'est pas a moi de juger qu'il satisfait le verbe "lire" du referentiel** : c'est un
jugement pedagogique, pas un choix de perimetre. Je ne l'ouvre donc pas ce sprint, meme
partiellement, et je pose la question a `nadia-prof` avant tout developpement (voir "Ce que je
demande a nadia-prof avant le code" ci-dessous).

**Notion de fraction simple (unites fractionnaires, fractions inferieures a 1) et reperage sur une
droite numerique.** Deux composantes distinctes. "Notion de fraction simple" (nommer/reconnaitre une
fraction - "quelle fraction correspond a un quart ?") est entierement textuelle, sans ambiguite.
"Reperage sur une droite numerique" designe un placement visuel qu'aucun mecanisme actuel ne dessine
; un proxy textuel existe ("entre quels deux nombres entiers se trouve 7/4 ?") mais souleve la meme
question que ci-dessus : est-ce que localiser une fraction par un encadrement textuel satisfait
"reperage sur une droite numerique", ou est-ce que cette competence exige une ligne numerique
effectivement dessinee ? Matiere premiere verifiee : `contenu-repris/fractions/cm1-compare.yml` ne
sert ni l'une ni l'autre composante telle que nommee par le referentiel - il porte une liste de
fractions destinee a un jeu de **comparaison** (`compare deux fractions et choisis le bon signe`),
un troisieme verbe absent de cette ligne. Reutilisable comme source de valeurs de fractions pour
construire des items neufs, pas comme contenu pret. Comme pour la ligne precedente, je ne l'ouvre
pas ce sprint et je pose la question a `nadia-prof` avant tout developpement.

### Quatre lignes confirmees hors d'atteinte de cette mecanique

Verification faite, le motif du brouillon initial tient :

- **Effectuer une multiplication posee** et **effectuer une division euclidienne simple** :
  l'un et l'autre demandent de mener un algorithme a plusieurs etapes (poser les retenues, abaisser
  un chiffre) - une saisie structuree en plusieurs temps, pas un choix unique parmi des
  propositions. Rien dans le schema `question` ne porte une reponse en plusieurs etapes ; ouvrir ces
  lignes demanderait une mecanique neuve, question d'architecture qui n'est pas la mienne (a router
  a `sofia-sm` / `theo-archi` le jour ou elle se pose reellement).
- **Reconnaitre, nommer et tracer des figures planes avec outils** et **reconnaitre et utiliser la
  symetrie axiale** : le premier verbe de la seconde partie de chaque intitule ("tracer", et la
  symetrie qui s'evalue par un dessin) exige un geste graphique qu'aucun texte ne peut remplacer.
  Meme categorie que les competences visuelles deja differees en mathematiques CP (denombrement,
  monnaie - `DEC-0019`).

## Decision

### Trois lignes entrent au sprint 12

- **Story S12-1** - Calculer un perimetre de carre ou de rectangle a partir de ses cotes.
- **Story S12-2** - Convertir des unites de longueur, de masse et de contenance dans le systeme
  metrique.
- **Story S12-3** - Calcul mental, tables de multiplication jusqu'a x10.

Choisies parmi les six lignes cleanement servables pour leur matiere premiere la plus directement
exploitable (perimetre, conversions) ou leur perimetre le plus simple a borner sans jugement de
regularite complexe (tables de multiplication, un espace fini et deja connu du referentiel). Les
trois stories sont ecrites dans `docs/backlog/backlog.md`, section "Sprint 12", avec leurs criteres
d'acceptation. Chacune ouvre une fiche de conformite sous `squad/conformite/`, remplie a l'exception
du bloc de validation, reserve a `nadia-prof`.

### Trois lignes differees pour capacite, pas pour un motif de fond

Le sprint porte aussi la reverification de `nadia-prof` sur le declencheur de `DEC-0026` et deux
corrections de garde-fous (perimetre distinct de ce lot, hors de ma main). Je ne dimensionne donc
pas ce sprint au-dela de trois ouvertures de contenu neuf :

- **Calcul mental, multiplications et divisions simples (multiples de 10, 100)** : matiere premiere
  seulement inspirante, contenu integralement a ecrire et a borner (quel intervalle de multiples,
  quelle profondeur sur x100). Prochaine priorite si la capacite du sprint 12 le permet.
- **Resoudre des problemes de durees** : matiere premiere partielle (deux items seulement sur
  "horaire d'arrivee" absents), demande le plus de redaction neuve des trois lignes de "Grandeurs et
  mesures".
- **Resoudre des problemes a plusieurs etapes mobilisant les 4 operations** : aucune matiere
  premiere, et l'historique de ce produit (`DEC-0021`, reserve de regularite de magnitude sur les
  problemes additifs CP) montre que `nadia-prof` est exigeante sur la variete et la coherence de ce
  type de contenu - le paquet le plus risque a bien ecrire du premier coup, donc celui que je ne
  cale pas dans un sprint deja charge.

`sofia-sm` tranche au planning si la capacite reelle permet d'en ouvrir une quatrieme parmi ces
trois ; je recommande, dans cet ordre, multiplications/divisions simples en premier (le contenu le
plus proche de ce qui est deja code sur `AdditionsBorneesContentSourceFactory` en esprit, sinon en
schema).

### Deux lignes ne sont pas ouvertes, meme partiellement, avant arbitrage de `nadia-prof`

Voir la section precedente pour le detail. Je ne pose pas ces deux lignes en `en cours` sur la
matrice tant que l'arbitrage n'est pas rendu, pour ne pas laisser croire qu'un perimetre est deja
choisi.

## Ce que je demande a `nadia-prof` avant le code

Deux questions de jugement pedagogique, posees maintenant pour qu'un developpeur ne commence jamais
a ecrire du contenu sur un perimetre qu'elle refuserait ensuite :

1. **Sur "lire, ecrire, decomposer et comparer les grands nombres"** : un item qui demande de
   transcrire un nombre entre son ecriture chiffree et son ecriture en toutes lettres (dans un sens
   ou dans l'autre) constitue-t-il, a vos yeux, une facon valide de travailler "lire" et "ecrire" un
   grand nombre, en l'absence de toute modalite orale dans ce produit ? Si oui, une story ulterieure
   ouvre la ligne en entier. Si non, dites-nous ce qui, dans un mecanisme de type "question", pourrait
   ou non approcher ces deux verbes - ou si la ligne doit rester bornee a "decomposer" et "comparer"
   jusqu'a ce qu'une autre modalite existe.
2. **Sur "notion de fraction simple... et reperage sur une droite numerique"** : un item qui demande
   d'encadrer une fraction entre deux nombres entiers (par exemple "entre quels deux entiers se
   trouve 7/4 ?"), sans aucune ligne numerique dessinee, suffit-il a travailler "reperage sur une
   droite numerique", ou cette competence exige-t-elle selon vous une representation visuelle que ce
   produit ne sait pas encore rendre ? Si la premiere reponse convient, une story ulterieure ouvre la
   ligne en entier sur ce proxy. Sinon, la ligne restera bornee a "notion de fraction simple" seule
   jusqu'a ce qu'un mecanisme visuel existe - question qui serait alors routee a `theo-archi`.

## Justification pedagogique

Aucune - cette decision trace un perimetre de stories et le motif qui differe cinq lignes sur huit
candidates. Le jugement sur le contenu qui sera ecrit pour S12-1 a S12-3, et sur les deux questions
posees ci-dessus, reste entierement celui de `nadia-prof`.

## Impact

- `docs/backlog/backlog.md`, nouvelle section "Sprint 12" : trois stories (S12-1 a S12-3).
- Trois fiches ouvertes sous `squad/conformite/` : `FICHE-bandit-manchot-perimetre-cm1.md`,
  `FICHE-bandit-manchot-conversions-unites-cm1.md`,
  `FICHE-bandit-manchot-tables-multiplication-cm1.md`.
- `squad/programme/MATRICE-couverture.md`, tableau "Mathematiques - CM1" : les trois lignes visees
  passent `en cours` (jeu et mecanique renseignes), aucune ne passe `couvert-valide` par ce
  planning. Les neuf autres lignes restent `non couvert`, avec une note renvoyant a cette decision
  pour les cinq differees et les deux structurellement hors d'atteinte deja connues.
- Aucun changement de statut sur les deux lignes en attente d'arbitrage (lire/ecrire/decomposer/
  comparer, fractions) : elles restent `non couvert`, differees avec une question ecrite plutot
  qu'un silence.

## Ce que cette decision ne fait pas

- Elle ne tranche aucune question technique : la faisabilite sans code neuf qu'elle constate reste
  sous reserve d'un avis de `theo-archi`, s'il en rend un.
- Elle ne prejuge d'aucun Done pedagogique sur S12-1 a S12-3 : ces jugements restent entierement
  ceux de `nadia-prof`, sur les fiches ouvertes.
- Elle ne fait passer aucune ligne de `squad/programme/MATRICE-couverture.md` a `couvert-valide`.
- Elle ne ferme aucune des neuf lignes non ouvertes : elles restent au programme, differees avec un
  motif ecrit ou une question posee, jamais abandonnees.
