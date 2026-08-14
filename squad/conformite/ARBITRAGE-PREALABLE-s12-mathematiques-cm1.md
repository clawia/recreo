# Arbitrage prealable - mathematiques CM1, sprint 12 - nadia-prof

> Ce fichier est un **arbitrage prealable**, pas un Done pedagogique. Aucun contenu des trois lignes
> ouvertes par `DEC-0029` n'existe encore : il n'y a donc rien a jouer et rien a valider. Ce que
> j'ecris ici, ce sont les conditions auxquelles je validerai, posees **avant** que les developpeurs
> ecrivent, pour qu'aucun lot ne soit jete apres coup.
>
> Les Done pedagogiques de S12-1, S12-2 et S12-3 seront prononces, chacun apres avoir joue son
> paquet, dans le bloc de validation de `FICHE-bandit-manchot-perimetre-cm1.md`,
> `FICHE-bandit-manchot-conversions-unites-cm1.md` et
> `FICHE-bandit-manchot-tables-multiplication-cm1.md`. Ces trois fiches sont ouvertes par
> `camille-po` sur un autre lot que le mien ; **elles doivent renvoyer a ce fichier** quand
> `sofia-sm` fusionne, et c'est en elles que mes verdicts seront ecrits.

- **Date** : 2026-08-14
- **Auteur** : nadia-prof
- **Sprint** : s12
- **Saisi par** : `DEC-0029-perimetre-sprint-12-trois-lignes-mathematiques-cm1-ouvertes-cinq-differees.md`
- **Referentiel** : `squad/programme/mathematiques/competences-cm1.md`

## 0. Ce que j'ai fait, et ce que je declare degrade

**Je n'ai rien joue, et c'est normal ici : il n'y a rien.** Aucun paquet de mathematiques CM1
n'existe dans le depot, aucun jeu de ce sprint n'est au catalogue. Un arbitrage prealable ne se joue
pas, il se lit. Mon audit porte donc sur :

- le referentiel `competences-cm1.md`, ligne par ligne, pour les douze lignes concernees ;
- les criteres d'acceptation de S12-1 a S12-3 tels que `camille-po` les a ecrits ;
- les six fichiers de `contenu-repris/` cites par `DEC-0029`, lus item par item, calcul par calcul ;
- l'enveloppe `type: question` telle qu'elle est reellement codee, pour savoir ce qu'un paquet peut
  porter et ce qu'aucun garde-fou ne verifie.

**Ce que ce mode ne me dit pas, et je le nomme plutot que de le taire** : je ne saurai pas si un
enonce de ces paquets est lisible a l'ecran, si un indice arrive au bon moment, ni si une partie de
dix manches sur ces contenus tient l'attention d'un CM1, avant d'avoir joue les paquets livres. Ces
questions-la restent entieres et je les tranche apres, sur l'increment. Aucune condition ecrite
ici ne vaut Done pedagogique par avance, et un lot qui tient mes vingt-sept conditions peut encore
etre refuse pour ce que seul le jeu montre.

## 1. Les deux proxys - ma reponse

`camille-po` a corrige mon propre decoupage sur ce point, et elle a eu raison de le faire : j'avais
classe ces deux lignes comme servables sans voir qu'elles empaquettent chacune deux verbes dont un
seul se sert par un choix parmi des propositions. Je reponds sur les deux, separement, parce que ma
reponse n'est pas la meme.

### Proxy 1 - transcription entre chiffres et lettres, pour "lire, ecrire, decomposer et comparer les nombres entiers jusqu'au million" : **OUI, sous deux conditions nommees**

**Le proxy tient, et je le dis sans reserve de principe.** "Ecrire un nombre entier", au CM1, c'est
produire son ecriture chiffree a partir de sa designation orale ou litterale : un item qui donne
"trois cent cinq mille quatre cent vingt-et-un" et demande l'ecriture en chiffres **est** cette
competence, pas un substitut. Il n'y a rien a discuter sur cette moitie.

"Lire un nombre entier", c'est produire sa designation orale a partir de son ecriture chiffree. Ce
produit n'a et n'aura aucune modalite orale ; l'ecriture en lettres est la trace ecrite de cette
designation orale, et c'est exactement ainsi que la lecture des grands nombres s'evalue par ecrit en
classe. **Ce que le proxy travaille vraiment, c'est la connaissance qui fait lire** : savoir que
305 421 se decoupe en classes, que la classe des mille se nomme et que sa position commande tout. Un
enfant qui choisit la bonne ecriture en lettres a lu le nombre ; il n'y a pas d'autre chemin vers la
bonne proposition.

Ce que le proxy ne travaille pas, et je le nomme pour que personne ne croie le contraire : **la
fluence orale**. Dire un grand nombre a voix haute, vite et sans hesiter, ne sera pas travaille par
ce jeu. Ce n'est pas un defaut du proxy, c'est la limite du produit, et elle vaut deja pour tout ce
qui est livre.

**Deux conditions, et elles ne sont pas cosmetiques.**

- **GN-1.** Dans le sens chiffres vers lettres, **chaque distracteur doit se distinguer de la bonne
  reponse par la valeur qu'il designe**, jamais par l'orthographe des mots-nombres. Un item dont les
  propositions sont "quatre-vingts" et "quatre-vingt", ou qui joue sur le trait d'union ou sur le
  "s" de "cent", ne teste pas la lecture d'un nombre : il teste l'orthographe des mots-nombres, qui
  est une competence de francais et qui n'est pas cette ligne. Les erreurs a mettre en distracteur
  sont les erreurs de classe et de position - "trois mille cinq cent vingt-et-un" contre "trois cent
  cinq mille quatre cent vingt-et-un", ou un zero intercalaire ignore.
- **GN-2.** Dans le sens lettres vers chiffres, **la designation litterale de l'enonce est ecrite
  sans faute et sans ambiguite**, avec les traits d'union de la reforme ou sans, mais de facon
  constante dans tout le paquet. Un enfant qui hesite sur la forme du mot avant de compter les
  classes paie deux fois.

**Consequence pour la matrice** : la ligne peut ouvrir **en entier** - lire, ecrire, decomposer,
comparer - le jour ou `camille-po` en fait une story. Ce n'est pas une couverture partielle. Je
n'ouvre pas cette story moi-meme, ce n'est pas mon travail.

### Proxy 2 - encadrement entre deux entiers, pour "notion de fraction simple et reperage sur une droite numerique" : **NON**

**Le motif n'est pas une opinion sur les droites numeriques, c'est une arithmetique.** La ligne du
referentiel dit, mot pour mot : *"Notion de fraction simple (unites fractionnaires, **fractions
inferieures a 1**) et reperage sur une droite numerique."* Si le paquet respecte ce perimetre, toutes
ses fractions sont inferieures a 1, donc **tout item d'encadrement a la meme reponse : entre 0 et
1**, pour le premier item comme pour le vingtieme. Ce n'est pas un exercice facile, c'est un exercice
vide : au deuxieme item l'enfant a compris qu'il peut repondre sans lire, et c'est precisement ce que
je refuse partout ailleurs sous le nom de devinette.

**L'exemple que `camille-po` me propose le montre lui-meme** : "entre quels deux entiers se trouve
7/4 ?" n'est un item qu'a la condition que 7/4 soit superieur a 1 - c'est-a-dire en sortant du
perimetre que la ligne nomme. Le proxy n'a de contenu que hors de sa propre ligne. Il ne peut donc
pas la servir.

**Ce que je dis sur le fond, en plus de l'arithmetique** : "reperer sur une droite numerique" est une
competence de representation. Ce que l'enfant doit apprendre, c'est qu'une fraction est un nombre qui
a une place, qu'on trouve cette place en partageant l'unite en parts egales, et qu'on lit une
graduation. Un encadrement textuel ne fait rien de tout cela, meme avec des fractions superieures a
1 : il donne le voisinage, pas le partage de l'unite, qui est le coeur de la notion. **Cette
competence exige une ligne reellement dessinee et graduee.** Tant qu'aucun mecanisme ne la dessine,
elle n'est pas couverte, et je prefere une case vide a une case fausse.

**Ce que je propose a la place, et c'est une reponse complete, pas un renvoi.** La ligne n'a pas
besoin de rester fermee. Sa premiere moitie, "notion de fraction simple", est entierement servable
**sans aucun proxy** : reconnaitre l'unite fractionnaire, associer une fraction a son ecriture, a sa
denomination ("un quart", "trois cinquiemes"), a une part d'un tout partage en parts egales.
`camille-po` l'avait deja constate. Donc :

- la ligne peut ouvrir en **couverture partielle nommee**, sur la moitie "notion de fraction simple
  (unites fractionnaires, fractions inferieures a 1)" ;
- la moitie "reperage sur une droite numerique" reste **non couverte**, et la matrice doit le dire
  avec ces mots, pas la laisser dans l'ombre d'une ligne verte.

**Une remarque sur le referentiel, que j'adresse a `camille-po` par `sofia-sm` et que je ne tranche
pas moi-meme.** La restriction "fractions inferieures a 1" est plus etroite que ce qu'un CM1 fait
reellement : les fractions superieures a 1 et leur encadrement entre deux entiers sont au programme
de CM1, et ce socle-la, qui se declare non exhaustif et non officiel, les laisse dehors. Si
l'utilisatrice et `camille-po` elargissent la ligne, l'encadrement redevient examinable - mais
**mon avis de fond ne changera pas** : il ne couvrira jamais que l'entree de la competence, jamais
le reperage, et ce serait une seconde couverture partielle nommee, pas la ligne entiere. Je le dis
maintenant pour qu'on ne me la represente pas dans trois sprints comme une porte deja franchie.

## 2. Conditions pour S12-1 - perimetre du carre et du rectangle

**Etat de la matiere premiere, verifie item par item.** Les sept calculs de perimetre de carre ou de
rectangle de `contenu-repris/quiz/cm1-perimetre.yml` sont **justes tous les sept**, et leurs
distracteurs sont **plausibles tous les quatorze** : ils valent l'aire ou la moitie du perimetre,
c'est-a-dire les deux confusions reelles. C'est de la bonne matiere, et je le dis parce que c'est
rare.

**Une correction de compte, avant de m'appuyer dessus.** `DEC-0029` ecrit "8 de ses 11 questions
calculent un perimetre de carre ou de rectangle a partir de cotes donnes en texte", et la demande
qui m'est faite reprend "8 items sur 11 transposables". **Ils sont sept, pas huit** : carre de 5 cm,
rectangle 6 x 4, rectangle 8 x 3, carre de 10 cm, rectangle 12 x 5, carre de 9 m, rectangle 7 x 2.
Le huitieme item compte sans doute "Comment calcule-t-on le perimetre d'un carre ?", qui enonce une
procedure et ne calcule rien. L'ecart ne change aucune decision - le paquet doit compter au moins
vingt items, donc l'essentiel est neuf de toute facon - mais un compte faux dans une decision de
perimetre devient une promesse fausse au sprint suivant.

**Mes conditions.**

1. **P-1 - Bornes.** Les cotes sont des **entiers de 1 a 50**, et le perimetre resultant est un
   entier. Aucun nombre decimal, aucune virgule, aucune fraction : les nombres decimaux
   n'apparaissent nulle part dans le referentiel CM1 de ce depot et ne sont couverts par aucune ligne
   livree. Un enfant ne doit pas avoir a apprendre une ecriture nouvelle pour repondre a une question
   de perimetre.
2. **P-2 - Unites.** **Le centimetre et le metre, et rien d'autre.** Pas de millimetre, pas de
   kilometre : un rectangle de 3 km n'est pas une figure qu'un enfant se represente. **La meme unite
   dans l'enonce et dans les trois propositions**, et **l'unite est ecrite dans chaque proposition**.
   Une proposition nue - "20" au lieu de "20 cm" - enseigne qu'une mesure est un nombre sans
   grandeur, ce qui est faux et ce que ce jeu ne dira pas. `contenu-repris/quiz/cm1-longueurs.yml`
   le fait ; ce n'est pas repris.
3. **P-3 - Jamais deux unites dans un item.** "Longueur 2 m et largeur 30 cm" est refuse d'avance :
   c'est une conversion suivie d'un calcul, donc deux etapes, donc une autre ligne du referentiel.
4. **P-4 - Formes d'items admises, et seulement celles-la.** (a) un carre dont le cote est donne ;
   (b) un rectangle dont la longueur et la largeur sont donnees. **L'item inverse est refuse** - "le
   perimetre d'un carre est 20 cm, combien mesure son cote ?" : la ligne dit "calculer un perimetre",
   et surtout, un paquet qui melange le sens direct et le sens inverse sans que l'enfant soit averti
   change d'un item a l'autre ce qu'il doit faire. C'est une bonne question pour une story a elle
   seule, pas un ajout silencieux a celle-ci.
5. **P-5 - Items definitionnels : deux au maximum, et hors quota.** Les deux items de la matiere
   premiere - "Le perimetre d'une figure, c'est..." et "Comment calcule-t-on le perimetre d'un
   carre ?" - sont **admis**, parce qu'un enfant qui ne sait pas ce que le mot designe ne tire rien
   de vingt calculs. Ils ne comptent pas dans les vingt items de calcul exiges par les criteres, et
   il n'y en a pas un troisieme.
6. **P-6 - Distracteurs : deux familles, nommees.** L'aire (cote x cote, ou longueur x largeur) et
   la moitie du perimetre (cote x 2, ou longueur + largeur). Ce sont les deux erreurs que fait
   reellement un CM1. Tout autre distracteur doit etre justifie par une erreur nommee dans le
   fichier ; un nombre choisi pour remplir est refuse.
7. **P-7 - Aucune proposition egale a une autre, dans aucun item.** Ce n'est pas une precaution de
   style, c'est un piege arithmetique que ce paquet rencontrera : **le carre de cote 4 a pour
   perimetre 16 et pour aire 16**, et **le rectangle 3 x 6 a pour perimetre 18 et pour aire 18**. Sur
   ces deux figures, le distracteur "aire" **est** la bonne reponse. Un item qui presente deux fois
   la meme valeur donne a l'enfant une question sans reponse unique, et il n'a rien fait de mal en
   choisissant le doublon. J'ai verifie : ni l'enveloppe `type: question`, ni le record `Question`,
   ni aucun garde-fou du depot ne compare un distracteur a la bonne reponse. **Le cote 4 et le couple
   3 x 6 sont donc exclus du paquet**, ou bien ils n'utilisent pas l'aire comme distracteur.
8. **P-8 - Enonce.** Une seule phrase interrogative, quinze mots au plus, qui nomme la figure et
   donne ses mesures avec leur unite. La forme de la matiere premiere - "Quel est le perimetre d'un
   rectangle de longueur 6 cm et de largeur 4 cm ?" - est la bonne et je la valide telle quelle.
9. **P-9 - Indice.** Il donne la **methode**, jamais le resultat, en une phrase de dix mots au plus.
   "Le carre a quatre cotes egaux." et "Fais le tour : longueur + largeur + longueur + largeur." sont
   des indices. "Calcule le perimetre." est une paraphrase de l'enonce et est refusee ; "C'est
   20 cm." est refusee. Un indice peut etre partage par tous les items d'une meme figure ; il ne peut
   pas etre le meme sur tout le paquet.
10. **P-10 - Mises en situation : un tiers du paquet au plus, et sans presuppose social.** Un enonce
    habille - une cloture, une bordure, un cadre - est bienvenu et donne du sens au mot, mais la
    majorite des items reste en enonce geometrique direct : la ligne dit "calculer un perimetre", pas
    "resoudre un probleme". Et aucune situation ne suppose que l'enfant a un jardin, une piscine ou
    une maison a lui. Une cour d'ecole, un terrain de sport, un cadre de photo, une table : tout le
    monde en a vu.

## 3. Conditions pour S12-2 - conversions de longueurs, de masses et de contenances

**Etat de la matiere premiere, verifie item par item.** Les trois fichiers repris comptent 27 items,
dont je compte **treize conversions reelles** ; les quatorze autres sont de l'estimation d'ordre de
grandeur, de l'identification d'instrument, ou de la **comparaison de grandeurs** - une troisieme
forme hors sujet que `DEC-0029` ne nomme pas et que j'ajoute ci-dessous. Les treize conversions sont
**arithmetiquement justes**. Quatre d'entre elles ont une reponse decimale, et c'est le point qui
decide de la plupart de mes conditions.

11. **C-1 - Aucun nombre decimal, ni dans la bonne reponse, ni dans un distracteur, ni dans
    l'enonce.** "500 g, c'est combien de kilogrammes ? - 0,5 kg" est refuse d'avance, ainsi que
    "50 cl, c'est combien de litre ? - 0,5 L", "250 ml - 2,5 cl" et "environ 1,5 L". Motif : le
    referentiel CM1 de ce depot ne nomme que les entiers jusqu'au million et les fractions
    inferieures a 1 ; l'ecriture decimale n'y figure pas et aucune ligne livree ne l'enseigne. Faire
    lire 0,5 kg a un enfant pour verifier qu'il sait convertir, c'est verifier deux choses et n'en
    enseigner aucune. Consequence pratique et suffisante : la conversion va de la grande unite vers
    la petite, ou de la petite vers la grande **quand le quotient est entier** (2000 g vers 2 kg :
    oui ; 500 g vers des kilogrammes : non).
12. **C-2 - Facteur simple, un seul palier de calcul.** Le facteur entre les deux unites d'un item
    vaut **10, 100 ou 1000**. "3 km, combien de centimetres ?" est refuse : le facteur 100 000 n'est
    pas du calcul mental de CM1, c'est un exercice de zeros.
13. **C-3 - Unites admises, et pas d'autres.** Longueur : mm, cm, m, km. Masse : g, kg, tonne.
    Contenance : ml, cl, L. Je confirme la liste de `camille-po` et je refuse qu'on l'etende au
    decimetre, decametre, hectometre, decigramme, centigramme, decagramme, hectogramme : ces unites
    existent dans le tableau de conversion et pratiquement pas dans la vie de l'enfant. Cette ligne
    est dans "Grandeurs et mesures", pas dans "reciter un tableau".
14. **C-4 - Formes exclues, les trois.** Ni estimation d'ordre de grandeur ("quelle est la masse
    d'une pomme ?"), ni identification d'instrument ("avec quoi mesure-t-on une masse ?") -
    `DEC-0029` les avait vues - **ni comparaison de grandeurs** ("qu'est-ce qui est le plus long ?",
    "qu'est-ce qui est le plus lourd ?", "qu'est-ce qui contient le plus de liquide ?"). Cette
    troisieme forme est presente dans les trois fichiers repris, elle n'est pas nommee par
    `DEC-0029`, et elle n'est pas le verbe "convertir". Je l'ajoute a la liste des exclusions pour
    qu'un developpeur ne la reprenne pas en croyant s'appuyer sur une decision qui l'autorise.
15. **C-5 - Forme composee exclue de ce paquet.** "150 cm, c'est 1 m 50 cm" et "1500 g, c'est
    1 kg 500 g" sont de bons items et de la vraie competence de CM1, et je ne les condamne pas sur le
    fond : je les refuse **dans ce paquet-ci**, parce qu'il porte deja trois grandeurs et que leur
    ajouter une seconde tache - decomposer en deux unites au lieu de convertir vers une - fait trop
    varier ce que l'enfant doit faire d'un item a l'autre. C'est un motif de story a part, et je le
    signale comme tel a `camille-po`.
16. **C-6 - L'unite est ecrite dans chaque proposition, et c'est l'unite demandee.** Meme condition
    que P-2 et meme motif. `cm1-longueurs.yml` repond "100" quand `cm1-masses.yml` repond "1000 g" :
    la forme retenue est celle des masses.
17. **C-7 - Distracteurs : erreurs de facteur, et rien d'autre.** Multiplier par 10 au lieu de 100,
    par 100 au lieu de 1000, ou aller dans le mauvais sens. Ce sont les erreurs reelles. Un
    distracteur qui n'est pas une erreur de facteur nommee est refuse, et **aucune proposition n'est
    egale a une autre dans un item** (meme motif qu'en P-7 : rien dans le code ne le verifie).
18. **C-8 - Enonce autosuffisant.** Chaque enonce nomme **la quantite de depart avec son unite et
    l'unite d'arrivee**, en une phrase. L'enfant ne doit jamais avoir a se souvenir de l'item
    precedent, ni a deviner vers quoi on convertit. C'est ma reponse a la question que `camille-po`
    me pose explicitement - **oui, les trois grandeurs peuvent tenir dans un seul jeu sans devenir
    confuses**, exactement a cette condition-la et a la suivante.
19. **C-9 - Le titre du jeu annonce les trois grandeurs.** Pas "Les longueurs" : un enfant qui a
    choisi "Les longueurs" et recoit une question sur les litres a le droit de se croire en faute.
    Un titre qui couvre les trois - du genre "Les mesures" - et qui reste dechiffrable.
20. **C-10 - Indice.** Il donne le rapport ou le sens, jamais le resultat : "1 kg, c'est 1000 g." ou
    "Vers une plus petite unite, le nombre grandit." Un indice par famille de conversion au moins ;
    "Convertis." est refuse comme paraphrase.

## 4. Conditions pour S12-3 - tables de multiplication jusqu'a x10

**Un constat de perimetre, a inscrire avant tout le reste.** La ligne dit "tables de multiplication
(jusqu'a x10), **rappel automatise**". L'automatisation est une affaire de **vitesse** : un enfant
automatise quand il repond sans calculer. **Ce produit ne mesure aucun temps de reponse et n'en
mesurera pas dans ce sprint.** Ce paquet verifiera donc que l'enfant **sait** ses tables, pas qu'il
les a automatisees. Ce n'est pas un refus - savoir ses tables est la condition d'y arriver, et
s'entrainer est exactement le bon usage de ce jeu - mais c'est une **couverture partielle, et la
matrice doit l'ecrire avec ces mots**. Je le pose maintenant pour que personne n'ait a me demander
plus tard pourquoi je ne passe pas la ligne en couvert-valide sans mention.

21. **T-1 - Espace couvert.** Les 55 couples (a, b) avec 1 <= a <= b <= 10 sont tous representes, au
    moins une fois. Je confirme le critere de `camille-po` : un paquet qui ne porte que les tables
    faciles ne travaille pas les tables.
22. **T-2 - Le facile ne prend pas le tiers du jeu.** Sur les 55 couples, **19 comportent un facteur
    1 ou un facteur 10** - c'est-a-dire ne demandent aucun rappel de table. A raison d'un item par
    couple, ils feraient 35 % des manches. C'est le meme defaut que les operandes nuls que j'avais
    mesures a 26 % au sprint 0 et fait ramener a 13 % : un jeu de calcul ou une manche sur trois ne
    fait pas calculer. **Condition : un facteur 1 ou 10 ne concerne pas plus d'une manche sur
    cinq.** Le moyen le plus simple, que je donne sans l'imposer : un seul item pour chacun de ces 19
    couples, au moins deux items pour chacun des 36 autres - le paquet fait alors une centaine
    d'items et double l'entrainement la ou un CM1 en a besoin, sur 6 x 7, 7 x 8, 8 x 9.
23. **T-3 - Les deux ordres.** Pour les 28 couples ou a et b sont differents et ou aucun ne vaut 1
    ni 10, le paquet presente **les deux ordres** (7 x 8 et 8 x 7). Un enfant qui ne voit jamais que
    le petit facteur en premier apprend sa table dans un seul sens.
24. **T-4 - Le signe de la multiplication est le signe de la multiplication.** Les enonces portent le
    **signe multiplie** (U+00D7), pas la lettre minuscule x du clavier. La lettre est ce que les
    criteres du backlog ecrivent, et ils ont raison de le faire puisque le backlog est en ASCII :
    **le contenu lu par l'enfant, lui, n'est pas en ASCII** et n'a pas a l'etre - c'est deja le cas
    du signe multiplie dans `contenu-repris/quiz/cm1-perimetre.yml`. Un enfant qui apprend la
    multiplication voit le bon signe. La meme remarque vaut pour les enonces que ce fichier-ci cite
    en ASCII par convention de redaction du depot : ils sont cites, pas prescrits sous cette forme.
25. **T-5 - Distracteurs plausibles, et un piege a eviter.** La famille sure est le **produit
    voisin** : un facteur lu a un pres (pour 7 x 8 = 56, les distracteurs 48 et 63), ou une entree
    voisine de la table. La somme des facteurs, que les criteres proposent, **n'est admise que sur les
    petits produits** : pour 7 x 8, proposer 15 a cote de 56 et 48 laisse l'enfant eliminer par
    l'ordre de grandeur sans savoir sa table, et l'item devient une question a deux choix. Et un cas
    a exclure nommement : **pour 2 x 2, la somme des facteurs vaut 4, c'est-a-dire le produit** -
    c'est le seul couple ou cela arrive, et un item qui propose deux fois 4 n'a pas de reponse
    unique. Meme exigence qu'en P-7 et C-7 : aucune proposition egale a une autre, dans aucun item.
26. **T-6 - Indice : une strategie, pas un slogan.** L'indice doit donner un chemin vers le produit -
    "7 x 8, c'est 7 x 4 double.", "Compte de 8 en 8.", "Prends 7 x 10 et enleve 7 x 2." - et jamais
    le produit. Il peut etre partage par les items d'une meme table ; **il ne peut pas etre le meme
    sur tout le paquet.** Je le pose explicitement parce que le critere automatique verifie que le
    champ est rempli, et qu'un "Recite ta table." copie cent fois le remplirait en n'apprenant rien
    a personne. C'est le genre de vide qu'une CI verte laisse passer.
27. **T-7 - Formes exclues.** Aucun facteur hors de 1 a 10. Aucune division. **Aucun facteur
    manquant** ("7 x ? = 56") : c'est le verbe inverse, et il ne se melange pas au rappel direct sans
    avertir l'enfant. **Aucune mise en situation** ("7 paquets de 8 gateaux...") : la ligne dit
    "calcul mental" et "rappel", pas "probleme" - et les problemes ont leur propre ligne, encore non
    couverte. Les propositions sont des nombres nus, sans unite : un produit de deux nombres n'a pas
    d'unite.

## 5. Ce que je refuse d'avance, en une liste

Pour qu'aucun lot ne revienne avec ceci dedans, et pour qu'aucun developpeur n'ait a me le
redemander :

- tout nombre decimal ou toute virgule dans un contenu de ces trois paquets (P-1, C-1) ;
- toute proposition sans son unite, dans les deux paquets de mesures (P-2, C-6) ;
- **toute proposition egale a une autre proposition du meme item** - et je designe les trois cas
  precis ou ce paquet-la le produirait : carre de cote 4, rectangle 3 x 6, et 2 x 2 avec la somme
  des facteurs (P-7, C-7, T-5) ;
- l'item inverse et l'item a facteur manquant (P-4, T-7) ;
- l'estimation d'ordre de grandeur, l'identification d'instrument et la comparaison de grandeurs
  dans le paquet de conversions (C-4) ;
- le triangle equilateral dans le paquet de perimetre, comme `DEC-0029` l'a deja ecrit ;
- l'indice qui paraphrase l'enonce, l'indice qui donne la reponse, et l'indice identique sur tout un
  paquet (P-9, C-10, T-6) ;
- la mise en situation qui suppose que l'enfant a un jardin, une piscine ou une maison a lui (P-10) ;
- un distracteur qui n'est pas une erreur nommee dans le fichier.

## 6. Les proprietes dont je reste la seule garde

L'equipe doit savoir ce qui n'est **pas** couvert par un automate, sinon elle croira couvert ce qui
ne l'est pas. Sur ces trois paquets, apres avoir lu l'enveloppe `type: question`, le record
`Question` et les garde-fous de paquet du depot :

1. **Qu'aucune proposition n'est egale a une autre dans un item.** Verifie : ni la fabrique, ni le
   record, ni aucun test du depot ne compare un distracteur a la bonne reponse. C'est la propriete la
   plus mecanique de cette liste et la plus facile a tenir par un garde-fou - je decris la propriete,
   le moyen appartient a `theo-archi` et a `yanis-qa`. Tant qu'il n'existe pas, c'est moi, en jouant.
2. **Que la bonne reponse est arithmetiquement la bonne.** Rien ne recalcule un perimetre, un facteur
   de conversion ni un produit. Un paquet entier peut etre faux avec une CI verte.
3. **Que le distracteur est une erreur reelle et non un nombre de remplissage.** Aucun automate ne
   sait ce qu'est une confusion plausible.
4. **Que l'indice aide au lieu de meubler.** Le critere existant verifie que le champ est rempli,
   ce qui est une condition de forme et non d'utilite.
5. **Que la part du facile reste sous le seuil (T-2), et que les deux ordres sont presents (T-3).**
   Ce sont des proprietes de distribution sur tout un paquet, et aucun test ne les porte aujourd'hui.
6. **Que le niveau est le bon** : la lisibilite de l'enonce, la charge de ce que l'enfant tient en
   tete, et la progression au fil d'une partie de dix manches. Cela ne se lit pas, cela se joue, et
   je le jugerai sur l'increment.

## 7. Ce que je corrige, avant de m'appuyer dessus

- **Un compte de `DEC-0029` que la demande m'a transmis** : sept items de perimetre transposables, et
  non huit. Detail en section 2. Je ne l'efface pas, je le corrige.
- **Mon propre decoupage des douze lignes CM1 etait faux sur deux lignes**, et c'est `camille-po` qui
  l'a vu : j'avais classe "lire, ecrire, decomposer, comparer" et "fraction et droite numerique"
  comme servables sans remarquer qu'elles empaquetaient chacune un verbe que ce mecanisme n'atteint
  pas. Sa correction est juste, elle est retenue, et c'est elle qui a rendu cet arbitrage necessaire.
- **Une exclusion que `DEC-0029` n'a pas vue** : la comparaison de grandeurs dans les trois fichiers
  de mesures (C-4). Je ne le compte pas contre elle - elle avait nomme deux formes sur trois - mais
  un developpeur qui se fie a la liste de la decision reprendrait ces items.

## 8. Suites hors de ma main

- `camille-po` : la ligne "lire, ecrire, decomposer, comparer les nombres entiers jusqu'au million"
  peut ouvrir **en entier** sur le proxy de transcription, sous GN-1 et GN-2. La ligne "notion de
  fraction simple et reperage sur une droite numerique" peut ouvrir en **couverture partielle
  nommee** sur sa premiere moitie seulement, sans proxy ; sa seconde moitie reste non couverte.
- `camille-po`, deux motifs de story que j'ai ecartes de ces paquets sans les condamner : l'item
  inverse de perimetre (P-4) et la conversion en unite composee (C-5).
- `camille-po` par `sofia-sm` : la remarque sur la restriction "fractions inferieures a 1" du
  referentiel, section 1.
- `camille-po` : la ligne des tables ne pourra pas passer en couvert-valide sans la mention
  "sans mesure d'automatisation" (section 4).
- `theo-archi` et `yanis-qa` : la propriete 1 de la section 6 est la plus mecanisable de la liste.
  Je decris, je ne prescris pas.
