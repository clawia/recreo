# Arbitrage prealable de s25 - les seize lignes instruites une par une, la famille visuelle,
# `SUB`, et ma borne

- **Auteure** : `nadia-prof`
- **Date d'ouverture** : 2026-08-31
- **Demande** : `sofia-sm`, a l'ouverture de s25, sur l'objectif de l'utilisatrice - *"planifie s25
  pour avoir la plus grande couverture"*.
- **Ce que ce document est** : un arbitrage **prealable**, rendu avant qu'une ligne de contenu
  n'existe, dans la discipline que `DEC-0057` a etablie. Il ne prononce **aucun Done pedagogique**,
  ne fait bouger **aucune ligne** de `squad/programme/MATRICE-couverture.md`, n'ecrit **aucune
  story** et ne priorise rien. Le perimetre est a `camille-po`, la technique a `theo-archi`.
- **Increment de reference** : `round-two` a `130ba64`, worktree dedie, branche `s25-nadia`.

---

## 0. Ma methode, et ou elle est degradee

**J'ai joue.** J'ai construit le jar depuis cet arbre et lance l'application. Ce que j'ai joue :

- `bandit-manchot-fractions-cm1` - une partie en me trompant expres a chaque manche jusqu'a la
  revelation, pour revoir ce que l'enfant recoit apres un, deux, trois echecs ; puis **70 ouvertures
  de partie** pour faire sortir l'item 18, que je devais juger (section 7) ;
- `saisie-complements-10-cp` - ouverture et premiere manche, pour verifier que le jeu que j'ai
  valide au second passage est bien celui qui est servi depuis `130ba64` ;
- le catalogue CP et le catalogue CM1, pour relire les deux entrees de complements a 10 cote a cote
  (`CPL-9a`).

**Ou ma methode est degradee, et je le dis parce que c'est un audit prealable et non un audit
d'increment** : sur les seize lignes que j'instruis ci-dessous, **il n'y a rien a jouer**. Aucun
jeu ne les sert. Je juge donc sur les referentiels, sur la matiere premiere de `contenu-repris/`
quand elle existe, et sur ce que l'application rend aujourd'hui pour les lignes voisines. C'est la
limite normale d'un arbitrage prealable ; elle ne s'applique pas a la section 7, ou j'ai joue.

**Une chose que je n'ai pas faite** : aucun `mvn verify`, aucune mesure de rendu, aucun pixel. Le
seul build que j'ai lance est un `package`, pour obtenir le jar a jouer.

---

## 1. L'inventaire qui m'a ete transmis - je l'ai verifie, il est exact, et je le complete

`sofia-sm` m'ecrit : *"`NAR`, `PLU` et `FRA` ont ete consommees a s24. Il reste `SUB` et `DEN`, et
`DEN` ne fait bouger aucune ligne de matrice."* Elle me demande de le verifier plutot que de le
reprendre. **Je l'ai fait, et il est exact.**

**Methode** : ma reserve de s23 est le tableau de la section 9.1 de
`ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` - sept paquets
ouvrables : `RAN`, `ETA`, `NAR`, `PLU`, `SUB`, `LIA`, plus la moitie *"notion"* de `FRA`. J'ai lu
`squad/sprints/s23-trois-paquets-parmi-sept/planning.md`, lignes 113 a 115 : s23 a pris `RAN`, `ETA`
et `LIA`. `DEC-0059` a pris `NAR`, `PLU` et `FRA` a s24. **Il reste `SUB`.** `DEN` vient d'ailleurs -
de mon arbitrage de s19 - et `DEN-2`, qui est de moi et que je maintiens, laisse la ligne `en cours`
meme si le paquet est un jour valide.

**Ce que je complete, et qui n'etait pas dans son inventaire** : ma reserve n'est pas *presque* vide,
**elle est vide en lignes de matrice apres `SUB`**. `SUB` est le dernier paquet arbitre qui fasse
bouger une ligne. `DEN` en fait zero. **Un sprint qui se contenterait de consommer la reserve
plafonne a une ligne, pas a deux.** Sa lecture etait la bonne, sa conclusion aussi ; je durcis le
chiffre d'une unite, dans son sens.

### Mon propre compte de la matrice, avec sa methode

**Methode** : les 47 lignes de tableau des quatre sections de matiere de
`squad/programme/MATRICE-couverture.md` a l'etat de `130ba64`, classees par leur colonne "Statut",
comptees mecaniquement et non a l'oeil. *Perimetre : ce seul fichier, ces quatre tableaux, aucun
autre.*

| Statut | Compte |
|---|---|
| `couvert-valide` | **27** |
| `non couvert` | 15 |
| `en cours` | 1 |
| `non couvrable en l'etat` | 4 |
| **Total** | **47** |

**27 sur 43 hors des quatre `non couvrable`, soit 62,8 pour cent.** Je reproduis le chiffre annonce
a la decimale, par une lecture independante. **Les seize lignes que `camille-po` instruit en
parallele sont exactement les 15 `non couvert` plus la seule `en cours`**, et ce sont celles que
j'instruis ci-dessous.

---

## 2. Les seize lignes, instruites une par une

**Ce que je reponds pour chacune** : est-elle servable par une des deux mecaniques existantes - le
choix, ou la saisie que je viens de valider - et si oui, sous quelles conditions ?

### 2.1 La table de reponse

| # | Ligne | Deja arbitree par moi ? | Servable par le choix ou la saisie ? | Fait bouger la ligne ? |
|---|---|---|---|---|
| 1 | Denombrer, lire, ecrire, decomposer jusqu'a 100 (maths CP) | oui, s19 (`DEN-1` a `DEN-8`) | **oui**, pour *lire, ecrire, decomposer* | **non** - `DEN-2` |
| 2 | Reconnaitre et utiliser la monnaie (maths CP) | **non** | **non** - section 2.2 | non |
| 3 | Se reperer dans la journee, la semaine ; lire l'heure pleine (maths CP) | **non** | **oui**, pour la moitie *"se reperer"* - `SEM-1` a `SEM-9` | **oui, +1** |
| 4 | Deplacements sur un quadrillage simple (maths CP) | **non** | **non** - famille visuelle, `QUA-1` a `QUA-5` | non |
| 5 | Reconnaitre et nommer des figures planes (maths CP) | oui, s19 - REFUSE | non - famille visuelle, `FIG-1` a `FIG-4` | non |
| 6 | Multiplication posee (maths CM1) | avis du 2026-08-15, jamais en conditions | **non** - `POS-1` a `POS-6` | non |
| 7 | Division euclidienne (maths CM1) | avis du 2026-08-15, jamais en conditions | **non** - `POS-1` a `POS-6` | non |
| 8 | Localiser une syllabe dans un mot (fr. CP) | famille audio, jamais motivee ligne a ligne | **non** - section 2.5 | non |
| 9 | Identifier un son (phoneme) dans un mot (fr. CP) | famille audio | **non** - section 2.5 | non |
| 10 | Associer les graphemes aux phonemes (fr. CP), `en cours` | oui - barre de la fiche, 2026-08-10 | **non**, et je corrige ce que j'ai ecrit a s24 - section 2.6 | non |
| 11 | Decoder un mot regulier inconnu (fr. CP) | oui, s19 - REFUSE, `SYL-1` a `SYL-4` | non - famille audio | non |
| 12 | Copier un mot, une phrase courte (fr. CP) | oui, s19 - `R1` | **non, et par aucune mecanique de ce produit** - section 2.7 | non |
| 13 | Ecrire de maniere autonome un mot simple (fr. CP) | oui, s19 - saisie **et** audio noues | non - il manque l'audio | non |
| 14 | Lire silencieusement, restituer l'essentiel (fr. CM1) | non - `R2` n'a ecarte que la saisie | **oui, sous reserve mesuree** - `LIS-1` a `LIS-10` | **oui, +1, sous condition** |
| 15 | Reperer les substituts d'un personnage (fr. CM1) | oui, s23 - `SUB-1` a `SUB-10` | **oui** | **oui, +1** |
| 16 | Rediger un texte narratif court (fr. CM1) | oui, s19 - `R3` | **non, et par aucune mecanique de ce produit** - section 2.7 | non |

**Le total qui decide l'ampleur de s25 : trois lignes.** `SEM`, `LIS`, `SUB`. Plus `DEN`, qui vaut
un paquet et zero ligne. **Ce n'est pas ma borne qui borne s25 : c'est l'offre.**

### 2.2 Monnaie (maths CP) - REFUSE en l'etat, et le motif n'est pas celui qu'on attend

**Verdict : REFUSE sur la ligne entiere. `MON-1` a `MON-5` disent ce qui la debloquerait.**

Le motif qui circule depuis `DEC-0019` est que *"reconnaitre"* est visuel. C'est vrai et
insuffisant. **Le vrai motif est que la moitie textuelle n'apprend rien que le catalogue n'apprenne
deja.**

J'ai relu `contenu-repris/quiz/cp-monnaie.yml`, ses huit items, un par un. *"Tu as une piece de 2
euros et une piece de 1 euro. Combien as-tu en tout ?"* est une addition dans la limite de 10,
couverte par `bandit-manchot-additions-cp`. *"Un pain coute 1 euro, tu paies avec 2 euros, combien
te rend-on ?"* est une soustraction, couverte. *"Quelle piece vaut le plus, 2 euros ou 1 euro ?"* est
une comparaison, couverte. **Sur les huit items, sept sont un jeu deja valide avec un symbole d'euro
colle au resultat.** Un enfant qui joue ce paquet ne travaille pas la monnaie ; il refait du calcul.

**La seule chose monnaie-specifique et textuelle que j'aie trouvee** est la composition d'une somme
avec les valeurs qui existent reellement - *"quelles pieces pour payer 7 euros"* -, parce que les
valeurs disponibles ne sont pas quelconques. **Cela ne fait pas une ligne**, et cela ne touche
toujours pas *"reconnaitre"*.

**J'applique ici la meme regle qu'a `DEN-2`, et je la nomme pour qu'on cesse de me la redemander cas
par cas** (section 2.8) : *reconnaitre* est le premier verbe de la ligne, et au CP il fonde
*utiliser* - un enfant qui ne sait pas qu'un disque jaune vaut deux euros ne compose rien. La ligne
resterait `en cours` meme avec un bon paquet textuel. **Je prefere une case vide a une case
fausse**, et ici la case fausse serait doublement fausse : vide sur la competence, et pleine d'une
competence deja verte ailleurs.

- **`MON-1`** - Les pieces et les billets sont **montres**, pas nommes. Un enonce qui ecrit *"une
  piece de 2 euros"* a deja fait la reconnaissance a la place de l'enfant.
- **`MON-2`** - Les valeurs servies sont celles qui existent : 1, 2, 5, 10, 20, 50 centimes puis 1 et
  2 euros en pieces ; 5, 10, 20 euros en billets. **Aucune valeur inventee**, jamais.
- **`MON-3`** - Sur au moins un tiers des items, la reponse demande de **composer une somme avec les
  valeurs montrees**, et non de faire une addition qui serait juste avec n'importe quels nombres.
  *Unite de compte : l'item.*
- **`MON-4`** - Les sommes restent dans la limite que le CP sait calculer : **au plus 20 euros, sans
  centimes**, tant que la ligne "additions jusqu'a 10 (puis 20)" porte sa propre borne a 10.
- **`MON-5`** - Aucune situation ne suppose de l'argent de poche, une course faite seul, ni un achat
  qu'un enfant ne pourrait pas faire. *(Meme famille que `NAR-8` et `SUB-8`.)*

`contenu-repris/quiz/cp-monnaie.yml` reste de la matiere premiere pour la part *utiliser*, et rien
de plus.

### 2.3 Se reperer dans la journee, la semaine ; lire l'heure pleine (maths CP)

**Verdict : ACCEPTE SOUS CONDITIONS `SEM-1` a `SEM-9` sur la moitie *"se reperer dans la journee,
la semaine"*. REFUSE sur la moitie *"lire l'heure pleine sur une horloge"*, qui rejoint la famille
visuelle. Couverture partielle nommee : la ligne peut passer `couvert-valide`.**

**C'est le seul candidat neuf de ce sprint, et je l'ai cherche.** La ligne joint deux objets
distincts par un point-virgule. *Se reperer dans la journee et la semaine* - les moments du jour,
les jours dans l'ordre, les mois, les saisons - est un apprentissage entier, celui du rituel de la
date, que le CP mene tous les matins. Il ne repose pas sur la lecture de l'horloge et il se mene a
son terme sans elle. *Lire l'heure pleine* est un objet instrumental : il faut un cadran.

**Pourquoi cette moitie-la va au vert alors que `DEN` reste `en cours`** : ce n'est pas une faveur,
c'est ma regle de la section 2.8. Dans `DEN`, la part manquante - *denombrer* - fonde la part
servie. Ici, aucune des deux moities ne fonde l'autre.

**La matiere premiere existe et elle porte le piege** : `contenu-repris/quiz/cp-durees.yml` compte
onze items, dont neuf entierement textuels et utilisables comme matiere. **Les deux autres sont
exactement la contrefacon que `sofia-sm` me demande de juger** - *"La grande aiguille est sur le 12
et la petite aiguille sur le 3. Quelle heure est-il ?"*. Cette phrase decrit le cadran a la place de
l'enfant. Or lire l'heure pleine, c'est precisement **trouver ou pointe la petite aiguille sur un
cadran** ; quand le texte l'a dit, il ne reste qu'a savoir laquelle des deux aiguilles donne
l'heure. **Ces deux items sont ecartes du paquet**, c'est `SEM-3`, et ils sont mon exemple pour la
section 3.

- **`SEM-1` - Perimetre.** Les items portent sur : les moments de la journee (matin, midi,
  apres-midi, soir, nuit), les jours de la semaine et leur ordre, les mois et leur ordre, les
  saisons. **Rien d'autre.**

  > **Amendement du 2026-09-24 (`nadia-prof`), sur `SEM-1` et elle seule. La redaction ci-dessus
  > est fautive ; elle reste ecrite, elle n'est plus opposable. C'est la redaction corrigee, plus
  > bas dans ce bloc, qui vaut a partir de cette date.**
  >
  > **La phrase fautive** est la fin de la clause : *"les jours de la semaine et leur ordre, **les
  > mois et leur ordre, les saisons**. Rien d'autre."*
  >
  > **Pourquoi elle est fautive.** Le referentiel de questionner le monde loge les mois et les
  > saisons sur une autre ligne : `QLM-CP-13`, *"Nommer et ordonner les mois de l'annee et les
  > saisons"*, et sa section *"Recouvrements a ne pas double-compter"* dit dans les memes termes que
  > les lignes de questionner le monde *"portent sur les mois, les saisons et l'anteriorite entre
  > evenements, pas sur la journee ni la semaine"*. En ouvrant `MAT-CP-09` aux mois et aux saisons le
  > 2026-08-31, **j'ai autorise dans un paquet de mathematiques ce que mon propre referentiel reserve
  > a questionner le monde**. L'ecart vient de moi, pas d'une developpeuse : il est reste invisible
  > tant que `QLM-CP-13` n'etait servie par aucun jeu, et il est apparu le 2026-09-24 quand
  > `cp-qlm-saisons` l'a servie. Je l'ai constate moi-meme dans
  > `squad/conformite/FICHE-bandit-manchot-saisons-cp.md`, section *"Correction d'un de mes propres
  > constats, avant de m'en servir"*, ou le detail du recouvrement est ecrit. L'utilisatrice a
  > tranche le retrait des items concernes de `cp-semaine` ; la `DEC` est a `camille-po`.
  >
  > **Redaction corrigee, opposable a partir du 2026-09-24 :**
  >
  > **`SEM-1` - Perimetre.** Les items portent sur : les moments de la journee (matin, midi,
  > apres-midi, soir, nuit), les jours de la semaine et leur ordre. **Rien d'autre.** Les mois,
  > leur ordre et les saisons **sortent de ce perimetre** : ils sont la competence de `QLM-CP-13`,
  > et un item de `cp-semaine` qui les travaille fait travailler a l'enfant une competence comptee
  > sur `MAT-CP-09` alors que le referentiel la loge ailleurs.
  >
  > **Correction d'un chiffre que j'avais moi-meme avance.** Ma fiche de s41 parle de *"quatre items
  > de `cp-semaine`"* concernes. **Ce chiffre est faux** : il comptait les items de `cp-qlm-saisons`
  > en recouvrement, pas ceux de `cp-semaine`. J'ai relu les vingt-trois enonces de
  > `adapters/adapter-content/src/main/resources/contenu/cp-semaine.yaml` un par un : **onze items
  > sortent du perimetre corrige** - sept sur les mois (rangs 5, 6, 7, 15, 16, 17, 18) et quatre sur
  > les saisons (rangs 8, 9, 19, 20). **Douze items restent dans le perimetre** : huit sur les jours,
  > quatre sur les moments de la journee.
  >
  > **Ce que ce chiffre implique pour les autres conditions, et que je ne durcis pas.** `SEM-9`
  > demande **au moins vingt items** et je la laisse telle quelle : les douze items restants ne la
  > tiennent pas, donc le paquet a completer devra regagner des items **dans le perimetre corrige**
  > (jours, moments) pour repasser ma porte. `SEM-6` (un tiers d'items d'ordre) reste tenue sur les
  > douze restants - sept demandent *"juste avant"*, *"juste apres"* ou *"entre"*, pour un plancher
  > de quatre. `SEM-4` ne bouge pas : son plafond de neuf lettres reste ce qu'il etait, meme si
  > `septembre`, le mot par lequel je l'illustrais, sort du perimetre.
  >
  > **Ce que cet amendement ne fait pas.** Il ne touche a aucun fichier de contenu. Il ne tranche pas
  > la couverture de `MAT-CP-09` dans la matrice - c'est `camille-po` qui l'instruit. Il ne revient
  > pas sur le Done pedagogique prononce le 2026-08-31 sur `bandit-manchot-semaine-cp` : ce verdict
  > reste ecrit, il a ete rendu contre la redaction d'alors, et je ne deplace pas un poteau apres
  > coup. C'est la suite qui est gouvernee par la redaction corrigee, pas le passe.

- **`SEM-2` - Aucun item ne demande de calculer une duree.** Le calcul de duree est une ligne de
  CM1, deja verte (`bandit-manchot-problemes-durees-cm1`) ; un item de CP qui l'exigerait
  empieterait sur elle.
- **`SEM-3` - Aucun item ne decrit une horloge, un cadran ni la position d'une aiguille**, sous
  quelque forme que ce soit. Cette moitie de la ligne n'est pas servie et le paquet ne fait pas
  semblant de la servir.
- **`SEM-4` - Longueur.** L'`enonce` compte **au plus 12 mots**, et **aucun mot ne depasse neuf
  lettres**. *Un mot est une suite de caracteres sans espace ; unite de compte : l'item. Reference,
  et je ne durcis rien : `NAR-3` pose deja le plafond de neuf lettres au CP, et le mois le plus long
  du francais - `septembre` - le tient exactement.*
- **`SEM-5` - Les distracteurs sont de la meme categorie que la bonne reponse.** A une question sur
  un jour, on ne repond pas par un mois. Un distracteur d'une autre categorie s'elimine sans savoir.
- **`SEM-6` - Sur au moins un tiers des items, la reponse demande un ordre** - ce qui vient juste
  avant, juste apres - et non une denomination isolee. *Unite de compte : l'item.* Motif : *"se
  reperer"* est un fait d'ordre ; reciter la liste des sept jours n'est pas s'y reperer.
- **`SEM-7` - Aucune situation ne suppose** une configuration familiale, un rythme scolaire
  particulier, un depart en vacances, une fete religieuse ou un pays. *(Le mercredi et le samedi ne
  portent aucune activite.)*
- **`SEM-8` - Indice.** Il dit **quoi se rappeler**, jamais la reponse. **Huit mots au plus**, comme
  `NAR-9`.
- **`SEM-9` - Au moins vingt items**, et deux items ne posent jamais la meme question.

**La mention exacte que j'exigerai sur la ligne le jour ou elle bougerait**, ecrite par moi et non
par `camille-po`, et qui est une condition et non une remarque : **"moitie 'se reperer dans la
journee, la semaine' seule ; la moitie 'lire l'heure pleine sur une horloge' reste non couverte"**.

### 2.4 Quadrillage (maths CP) - REFUSE, famille visuelle

**Verdict : REFUSE. `QUA-1` a `QUA-5` disent ce qui la debloquerait.**

*"Se reperer et decrire des deplacements sur un quadrillage simple."* Un quadrillage est un objet a
deux dimensions ; s'y reperer, c'est regarder. En texte, *"tu es sur la case A3, tu avances de deux
cases vers la droite, ou es-tu ?"* demande a un enfant de six ans de **construire la grille dans sa
tete et d'y tenir une position pendant qu'il la deplace**. Ce n'est plus du reperage, c'est de la
memoire de travail, et c'est la charge cognitive la plus lourde que j'aie eu a juger sur une ligne
de CP.

**Ce que cette ligne exige, et c'est plus qu'une image** : les propositions doivent etre **des
positions designees sur la grille**, pas des etiquettes de texte. Un enfant qui repond *"A3"* en
lisant trois etiquettes n'a pas montre ou il est ; un enfant qui appuie sur une case l'a montre.

- **`QUA-1`** - La grille est **dessinee**, avec des cases **reellement carrees et de meme taille**.
  Une case plus large qu'une autre est une erreur de contenu, pas un defaut de forme.
- **`QUA-2`** - Le point de depart est **visible sur la grille** pendant toute la manche.
- **`QUA-3`** - Les propositions sont **des cases de la grille**, designables. Aucune proposition
  n'est une coordonnee ecrite en toutes lettres.
- **`QUA-4`** - Le deplacement demande compte **au plus trois pas** au CP, et chaque pas est une
  direction simple (droite, gauche, haut, bas). Aucune diagonale.
- **`QUA-5`** - Aucune couleur ne porte d'information : une grille lue en niveaux de gris donne la
  meme reponse. *(Meme clause que `FIG-3`, et pour le motif que `cp-formes.yml` a demontre.)*

### 2.5 Localiser une syllabe, identifier un phoneme (fr. CP) - REFUSE, et je donne enfin le motif

**Verdict : REFUSE sur les deux. Famille audio. `SYL-1` a `SYL-4` de s19 valent pour elles.**

Ces deux lignes etaient classees "audio" depuis mon avis du 2026-08-15, **mais je ne les avais
jamais motivees ligne a ligne** - et depuis, j'ai accepte *"denombrer les syllabes orales"* **sans
audio**, par ma clause de transposition. Quelqu'un a le droit de me demander pourquoi la meme clause
ne les ouvre pas. **La voici, et c'est la premiere fois qu'elle est ecrite.**

Ma clause de transposition, telle que je l'ai posee dans `FICHE-bandit-manchot-syllabes-cp.md` :
*"l'objet de la tache est de compter, la reponse est un nombre, et montrer le mot est la donnee de
l'exercice, pas sa reponse"*. Le test est donc : **montrer le mot ecrit donne-t-il la reponse ?**

- **Denombrer les syllabes** : non. L'enfant voit `CHAPEAU` et doit encore segmenter pour repondre
  *2*. La fuite existe - il peut compter des groupes de lettres - et je l'ai nommee et assumee, mais
  le mot ecrit ne porte pas le chiffre.
- **Localiser une syllabe** : **oui, et immediatement.** *"Dans `CHAPEAU`, ou entends-tu `cha` ?"* -
  l'enfant voit `cha` au debut des lettres et repond *"au debut"* sans rien entendre. **Le mot ecrit
  porte la reponse.** C'est exactement le motif par lequel j'ai refuse *"decoder un mot regulier"*
  le 2026-08-24, et ma clause de transposition ne s'y applique pas.
- **Identifier un phoneme** : **oui, et c'est pire**. `eau`, `au`, `o` sont un son et trois graphies.
  Un exercice ecrit enseigne le contraire de ce que la ligne nomme. Reconduit sans changement du
  2026-08-15.

**Ces deux lignes, plus *decoder un mot regulier*, restent bloquees par un obstacle de production -
pas de corpus enregistre, pas de synthese embarquee - arbitre par l'utilisatrice a `DEC-0042`,
arbitrage 2, et non par un jugement pedagogique qui manquerait.** Je ne les reclame pas ce sprint.

### 2.6 Associer les graphemes aux phonemes (fr. CP) - je corrige ce que j'ai ecrit a s24

**Verdict : REFUSE de fermer cette ligne a s25. Ma barre du 2026-08-10 tient, et elle demande de
l'audio.**

**J'ai ecrit le 2026-08-30, en instruisant les cinq lignes que le perimetre de `camille-po` ne
couvrait pas** : *"Ce qui manque a cette ligne, ce sont les voyelles et consonnes simples et les
digraphes que `cp-le-bon-son` ne porte pas encore. C'est un trou de contenu sur la mecanique de
choix, pas un besoin de saisie."*

**C'est plus large que ma propre barre, et c'est moi qui l'ai ecrit trop vite.** La phrase repondait
a la question posee - *la saisie ouvre-t-elle cette ligne ?* - et sa reponse, non, reste juste. Mais
prise seule, elle laisse croire que du contenu suffirait. **Ma barre, ecrite le 2026-08-10 dans
`FICHE-bandit-manchot-le-bon-son-cp.md`, section 6, compte trois conditions, et la deuxieme est :**
*"les digraphes `ou`, `oi` et `ch` sont couverts par un jeu qui emet un son"*.

**Cette ligne ne peut donc pas passer `couvert-valide` a s25**, quel que soit le contenu ecrit. Un
paquet sur les voyelles et consonnes simples serait un vrai gain pour un enfant - et il ne bougerait
pas la ligne. **Je le dis avant qu'on l'ecrive, pas apres.**

*J'ai relu ma barre avant de m'appuyer dessus et je ne la deplace pas : ses quatre points sont ceux
du 2026-08-10, mot pour mot, y compris le quatrieme - "j'aurai joue l'increment".*

### 2.7 Copier un mot ; rediger un texte narratif court - aucune mecanique de ce produit ne les sert

**Verdict : REFUSE sur les deux, et pas seulement pour la saisie.**

`R1` et `R3` de s19 refusaient ces deux lignes **a la mecanique de saisie**. On ne m'a jamais
demande si le **choix** les servait. La reponse est non, et pour la meme raison que *"lire a voix
haute"* : **ce sont des lignes de production.**

- **Copier un mot, une phrase courte, en respectant l'espacement** : la competence est
  graphomotrice - tenir l'outil, former les lettres, espacer sur la ligne. Ni un clavier ni un choix
  ne produisent un geste d'ecriture. L'utilisatrice a arbitre a `DEC-0046` qu'aucun geste de trace
  n'est envisage pour ce produit ; **former des lettres est un geste de trace.**
- **Rediger un texte narratif court et coherent** : `R3` refusait par avance *"quelle que soit la
  qualite du lot"*. Je le reconduis et je l'etends au choix. Remettre des phrases dans l'ordre
  travaille la coherence, et c'est un bon exercice - **mais l'enfant ne redige pas**, et la ligne dit
  *rediger*.

**Ce que j'en tire, et ce que je refuse d'en tirer.** Ces deux lignes ont la meme forme que les
quatre deja classees `non couvrable en l'etat` : la competence exige une capacite qu'aucune mecanique
de ce produit ne fournira. **La classification est de `camille-po`, pas de moi, et je lui donne le
jugement qui la fonde.** Je note tout de suite ce que je ne fais pas : reclasser deux lignes ferait
passer le denominateur de 43 a 41 et le taux de 62,8 a 65,9 pour cent **sans qu'un enfant ait appris
quoi que ce soit**. Je le calcule pour qu'il ne soit pas presente comme un gain de couverture. Ce
n'en est pas un.

### 2.8 La regle de couverture partielle que j'applique, ecrite une fois pour toutes

Je viens de m'en servir trois fois en trois sens differents (`SEM` au vert, `MON` et `DEN` a
`en cours`). Tant qu'elle n'est pas ecrite, ma porte a l'air d'un caprice. **La voici.**

> **Une couverture partielle nommee fait passer la ligne a `couvert-valide`** quand la part servie
> est un apprentissage entier que l'enfant peut mener a son terme sans la part manquante, **et** que
> la part manquante est un **objet distinct**.
>
> **Elle laisse la ligne `en cours`** quand la part manquante **fonde** la part servie, ou quand la
> part servie n'est qu'un **degre plus facile du meme objet**.
>
> Dans les deux cas, la mention exacte de ce qui n'est pas servi est **exigee sur la ligne**, et
> c'est moi qui l'ecris.

**Verifiee contre ce que j'ai deja prononce, avant de m'en servir** :

| Ligne | Part manquante | La regle donne | Ce que j'ai reellement prononce |
|---|---|---|---|
| Additions *"jusqu'a 10 (puis 20)"* | degre superieur du meme objet | `en cours` | reserve attachee, borne a 10 nommee - **coherent** |
| `FRA` notion / droite numerique | objet distinct (une representation) | vert + mention | **VALIDE** + `FRA-9` - **coherent** |
| `DEN` lire-ecrire-decomposer / denombrer | *denombrer* fonde le reste | `en cours` | `DEN-2` - **coherent** |
| Comparer / ranger | *ranger* est *comparer* repete | `en cours` | reste `en cours` jusqu'a un second jeu - **coherent** |
| `LIA` | objet distinct | vert + mention | **VALIDE** + `LIA-10` - **coherent** |

**Aucun de mes verdicts passes ne contredit cette regle.** Je ne l'invente pas pour s25, je la
constate et je la nomme. Si un tiers y trouve une contradiction avec un verdict que j'ai rendu,
**c'est ma regle qui cede, pas le verdict** : un verdict prononce ne se reecrit pas.

---

## 3. La famille visuelle - la question qui decide l'ampleur de s25

`sofia-sm` me donne sa lecture comme la sienne : la famille visuelle serait *"le plus gros paquet de
lignes qui ne se heurte a aucune contrainte fondatrice du produit"*. **Je la corrige sur le compte,
je la confirme sur le fond, et j'ajoute la distinction qui compte pour un planning.**

### 3.1 Le compte, avec sa methode

**Methode** : je pars des seize lignes de la section 1, je retire celles que la famille visuelle ne
touche pas, et je compte **ce que la famille visuelle ferait passer au vert qui ne le serait pas
sans elle**. *Perimetre : les quatre tableaux de matiere, a `130ba64`, apres les arbitrages du
present document.*

| Ligne | Ce que le visuel apporte | Ligne neuve ? |
|---|---|---|
| Quadrillage CP | la ligne entiere | **oui, +1** |
| Figures planes CP | la ligne entiere | **oui, +1** |
| Monnaie CP | la moitie *reconnaitre*, sans laquelle la ligne ne peut pas passer | **oui, +1** |
| `DEN` - la moitie *denombrer* | debloque `DEN-2`, avec le paquet textuel | **oui, +1** |
| Heure CP - moitie *lire l'heure* | **rien de neuf** si `SEM` est deja verte : il leve la mention | non |
| `FRA` - droite numerique | **rien de neuf** : la ligne est deja verte | non |
| Figures planes CM1, symetrie CM1 | **rien** : ces deux lignes exigent le **trace**, que l'utilisatrice a ecarte (`DEC-0046`) | non, et hors du denominateur |

**Quatre lignes, pas six.** Et deux d'entre elles - monnaie et `DEN` - **demandent le visuel *et* un
paquet textuel**, donc deux paquets chacune.

**Ce qui reste vrai dans sa lecture, et c'est l'essentiel** : quatre lignes, c'est **plus** que ce
que la reserve de contenu seul contiendra apres s25, qui est zero. La famille visuelle est bien le
plus gros gisement restant. Elle est simplement plus petite que six, et plus chere que "un paquet
par ligne".

**Un point sur lequel je ne la suis pas** : *"ne se heurte a aucune contrainte fondatrice du
produit"*. La contrainte du **pli** - `DEC-0051`, ferme sur tout le catalogue - s'y heurte
frontalement. Un cadran, une grille, une collection prennent de la hauteur au-dessus du pli, la ou
un enonce prend deux lignes. **Je ne sais pas si le pli tient avec une image ; personne ne le sait,
parce que personne ne l'a mesure.** Ce n'est pas ma mesure - c'est celle d'`ines-design` - mais c'est
ma porte qui refusera un jeu dont l'image tombe sous le pli.

### 3.2 Une image suffit-elle, ou faut-il une interaction ? - ligne par ligne

**C'est la question la plus utile qu'on m'ait posee ce sprint, et la reponse n'est pas la meme pour
les cinq lignes.** Les traiter comme un seul besoin ferait payer a tout le monde le prix de la plus
chere.

| Ligne | Ce que l'enfant doit faire | Ce que cela exige |
|---|---|---|
| **Figures planes CP** | regarder une figure, choisir son nom | **une image fixe** + propositions en texte |
| **Monnaie CP** | regarder des pieces, dire combien il y a | **une image fixe** + propositions en texte |
| **Heure pleine CP** | regarder un cadran, dire l'heure | **une image fixe** + propositions en texte |
| **Quadrillage CP** | montrer ou il arrive apres un deplacement | **une image + des propositions qui sont des positions sur cette image** |
| **`DEN` - denombrer** | grouper une collection pour la compter | **une manipulation reelle** - deplacer, marquer, grouper |

**Trois des cinq n'ont besoin que d'une image fixe et des propositions que la mecanique de choix rend
deja.** Une seule - `denombrer` - demande une manipulation, et c'est celle que j'ai refusee le
2026-08-24 en ecrivant qu'elle *"n'ouvrira qu'avec une interaction reelle sur la collection
(deplacer, marquer, grouper), pas un simple dessin statique"*. **Je ne deplace pas ce poteau et je ne
l'assouplis pas** : le plafond de comptage a l'ecran est autour de dix objets, et une ligne qui va
jusqu'a cent demande un groupement par dix que l'enfant fait, pas qu'on lui montre.

**Consequence de planning que je nomme sans la decider** : le palier "image fixe + propositions en
texte" ouvre **trois** lignes (quadrillage exclu, il lui faut des propositions positionnees ;
`denombrer` exclu). Le palier "propositions positionnees sur l'image" en ouvre **une** de plus. Le
palier "manipulation" en ouvre **une** de plus. **Ce sont trois paliers, pas une mecanique.** Le cout
de chacun est a `theo-archi`.

### 3.3 Une image decrite en texte est-elle recevable au CP ?

**Non. C'est une contrefacon, et je peux le montrer sur le materiau de ce depot plutot que le
soutenir.**

**Le test que j'applique est celui de la section 2.5, et il est le meme partout** : *la description
donne-t-elle la reponse ?*

- **`contenu-repris/quiz/cp-durees.yml`, items 8 et 9** : *"La grande aiguille est sur le 12 et la
  petite aiguille sur le 3. Quelle heure est-il ?"* La competence est de **trouver ou pointent les
  aiguilles**. Le texte l'a fait. Il reste une regle a appliquer - la petite donne l'heure - et
  l'enfant qui l'applique n'a jamais regarde un cadran. **Contrefacon.**
- **`contenu-repris/associer/cp-formes.yml`** : relu ce jour, il associe le glyphe carre orange a la
  cible *"rectangle"* et le glyphe carre bleu a la cible *"carre"*. **La meme forme, deux reponses,
  departagees par la couleur.** C'est ce que j'avais ecrit le 2026-08-24 et je le reverifie plutot
  que de le citer de memoire : c'est exact, et c'est le seul materiau du depot sur cette competence.
- **`contenu-repris/quiz/cp-geometrie.yml`** : *"Combien de cotes a un triangle ?"* Ce n'est pas
  *reconnaitre*, c'est reciter une propriete. Un enfant qui n'a jamais distingue un carre d'un
  rectangle repond juste.

**Un cas ou la description en texte n'est pas une contrefacon, pour que la regle ne soit pas plus
large qu'elle ne doit** : quand ce que le texte decrit **n'est pas ce qu'on evalue**. *"Une bande de
papier est partagee en 8 parts egales, on en colorie 3"* decrit une image, et c'est recevable -
`FRA` est verte - parce qu'on evalue l'ecriture de la fraction, pas le fait de voir les huit parts.
**La ligne de partage est : si le geste que le texte remplace est le geste que la ligne nomme, c'est
une contrefacon.**

### 3.4 Ma reponse a la question posee - clairement

**Aucune des cinq lignes de la famille visuelle n'est servable par le choix ni par la saisie, et
aucune ne l'est par une image decrite en texte.** Il faut un rendu reel. C'est une information de
planning et je la donne maintenant, comme `sofia-sm` me le demande, plutot qu'apres un lot.

**Et j'ajoute ce qu'elle ne m'a pas demande, parce que cela change la decision** : ce rendu reel
n'est pas une seule mecanique, mais **trois paliers de capacite** dont le premier ouvre deja trois
lignes. Une decision "on fait le visuel ou on ne le fait pas" perdrait cette distinction.

**Une reserve honnete sur ma propre competence** : je ne sais pas ce que coute une image dans ce
produit, et je n'ai aucun avis sur la forme qu'elle prendrait. Je decris ce que l'enfant doit
faire ; `theo-archi` dit ce que cela coute et `ines-design` ce a quoi cela ressemble.

### 3.5 Une ligne que je n'avais pas jugee, et qu'on ne m'a pas redemandee

`camille-po` a ecrit le 2026-08-25, a propos de **la symetrie axiale CM1**, que *"cette ligne peut
rouvrir `en cours` sur le seul verbe 'reconnaitre' le jour ou `nadia-prof` juge qu'une telle
couverture partielle est honnete ; ce jugement n'existe pas aujourd'hui et je ne le presume pas."*
Elle a eu raison de ne pas le presumer, et je le rends maintenant plutot que de le laisser dormir un
septieme sprint.

**Verdict : NON. La couverture partielle du seul verbe *reconnaitre* n'est pas honnete sur cette
ligne**, et ma regle de la section 2.8 donne le motif sans que j'aie besoin d'en inventer un :
*reconnaitre une symetrie* et *l'utiliser pour completer une figure* ne sont pas deux objets
distincts, c'est **le meme objet a deux degres**, et le degre servi serait le plus facile. La ligne
reste `non couvrable en l'etat`.

---

## 4. `SUB` - je confirme la conclusion, je corrige un motif, et je precise ce qu'elle coute

### 4.1 Le quota `COMP-2` - conclusion confirmee, motif corrige

`camille-po` ecrit que le motif du premier report de `SUB` - mon quota d'au plus un paquet de type
`COMP-2` par sprint - *"est consomme, puisque `NAR` est verte"*.

**Sa conclusion est juste : `SUB` est eligible a s25.** Son motif ne l'est pas tout a fait, et la
difference compte.

**Ma condition, telle qu'elle est ecrite** : *"au plus un paquet du type `COMP-2` par sprint"*.
**C'est un quota par sprint, pas une file qu'une ligne verte libere.** s25 ouvre une place neuve quel
qu'ait ete le sort de `NAR`. Ce que le vert de `NAR` change est autre chose, et c'est ce qui rend sa
conclusion juste : **il ne laisse aucun reaudit de type `COMP-2` du a s25.** Si `NAR` avait ete
refusee et que ses items devaient etre relus texte masque a s25, ce reaudit aurait mange la place, et
`SUB` aurait ete reportee une seconde fois avec une ligne verte au tableau.

**Pourquoi je corrige plutot que de laisser courir** : sa formule donnerait la mauvaise reponse le
jour ou un autre paquet `COMP-2` serait en attente de reaudit. La mienne la donne dans les deux cas.

### 4.2 Ce que `SUB` coute a auditer

`sofia-sm` me cite : *"`SUB-2` autorise 45 mots de texte par item contre 18 pour `NAR-2`, soit
environ deux fois et demie le volume narratif, et sur l'axe de l'accentuation `SUB` est le paquet
cher"*.

**J'ai verifie que c'est bien de moi avant de repondre, et ce l'est** - mot pour mot, section 1.4 de
mon arbitrage de s24. Je l'aurais conteste de memoire ; je ne le conteste pas apres l'avoir relu.
**Mais elle me le presente comme un cout pour moi, et ce n'en est pas un.**

**L'axe de l'accentuation n'est pas ma porte depuis `DEC-0056`, le 2026-08-30** : l'orthographe,
l'accentuation et la ponctuation du texte lu par l'enfant appartiennent au `relecteur-pedagogique`.
**Les deux fois et demie de volume sont donc son cout, pas le mien**, et c'est a lui qu'il faut le
dire au planning - avec le fait, qui est de moi, que le garde-fou d'`ADR-0065` ne voit pas la faute
d'accent sur un mot qui existe aussi sans accent.

**Mon cout a moi, sur `SUB`, est ailleurs et il est nomme depuis s23** :

| Condition | Ce que je fais | Automatisable ? |
|---|---|---|
| `SUB-4` - deux propositions sur trois s'accordent en genre et en nombre | **chaque item**, a la main | non : il faudrait un lexique morphologique, ce depot n'en a pas |
| `SUB-5` - le referent n'est pas le plus proche a gauche sur au moins `N/3` items | **chaque item**, a la main | **partiellement** : la distance en mots se compte, l'accord ne se decide pas |
| `SUB-6` - texte masque, la question ne se repond pas | **chaque item**, texte masque, sur la totalite | **non, jamais.** C'est `COMP-2` |

**Je maintiens mon classement de s23 sans le durcir ni l'alleger** : `SUB` est le paquet dont l'audit
me coute le plus cher apres `NAR`. Il compte **pour un paquet** contre ma borne, et **pour la place
`COMP-2` de s25**.

---

## 5. Ma borne pour s25 - et ce n'est pas elle qui borne le sprint

### 5.1 Ce que je reconfirme, sans le relever

**Quatre a cinq paquets de contenu par sprint, sur une mecanique existante.** Inchange depuis s22,
reconfirme a s24, et je le reconfirme ici. **Plus un audit de mecanique neuve, qui n'est pas price
dans ce chiffre** - c'est le cout que j'ai nomme le 2026-08-30 et que je n'avais jamais nomme avant.

**Les trois conditions qui tiennent ma borne, verifiees par moi sur cet arbre** :

| Condition | Etat |
|---|---|
| 1 - l'accentuation est gardee par un automate | **remplie** - `AucunMotServiNeSEcarteDeSonAccentTest` existe, et sa morsure est prouvee sur le materiau reel du defaut de s22 |
| 2 - chaque paquet arrive avec ses comptes declares et leur methode | **remplie** - pratique acquise depuis s22, tenue a s23 et s24, et le relecteur les a recomptes a s24 sans ecart |
| 3 - au plus un paquet du type `COMP-2` par sprint | **tenable a s25** si `SUB` est le seul du lot - section 4.1 et `LIS-6` |

### 5.2 Ce que je peux porter a s25, et ce que je ne releverai pas pour un objectif

**Le fait qui commande, et il n'est pas de moi** : apres cet arbitrage, il n'existe que **trois**
paquets qui bougent une ligne (`SEM`, `LIS`, `SUB`), plus `DEN` qui n'en bouge aucune. **Ma borne
n'est pas atteinte par l'offre.** C'est l'inverse de s24, et c'est le fait de planning le plus
important de ce document.

| Scenario de s25 | Ce que je porte | Lignes neuves |
|---|---|---|
| **A - contenu seul** | `SEM`, `LIS`, `SUB`, `DEN` = **4 paquets**. Je suis a ma borne basse, pas en dessous. `LIS` et `SUB` ne tiennent pas ensemble (`LIS-6`) : le lot est donc `SEM`, `DEN`, plus **un** des deux | **+2** avec `SEM` et l'un des deux ; **+3** exige de lever `LIS-6` ou d'etaler sur deux sprints |
| **B - contenu seul + un palier visuel qui livre un paquet** | **3 paquets** de contenu + l'audit de la mecanique visuelle + son premier paquet | **+2 ou +3** |
| **C - contenu seul + une mecanique visuelle qui ne livre aucun paquet** | 4 paquets, rien a auditer sur la mecanique | **+2** |

*Correction que je fais a mon propre tableau avant de le rendre : j'avais d'abord ecrit "+3" au
scenario A. **C'est faux, et c'est ma propre condition `LIS-6` qui l'interdit** - `LIS` et `SUB`
portent toutes deux la propriete de type `COMP-2`, et ma condition 3 n'en autorise qu'une par sprint.
Le maximum reel de s25 sans mecanique neuve est **+2 lignes**, soit **29 sur 43, 67,4 pour cent**.
Je prefere rendre ce chiffre-la que le chiffre flatteur que j'avais ecrit une ligne plus haut.*

**Pourquoi je descends a trois paquets dans le scenario B, et pourquoi ce n'est pas un durcissement
de ma borne.** J'ai ecrit le 2026-08-30 que l'audit d'une mecanique neuve n'etait **pas price** par
mon chiffre, et que je le nommerais plutot que de le decouvrir. **Je le price ici, pour la mecanique
visuelle, et il est plus lourd que celui de la saisie** :

- la saisie, je l'ai auditee sur `SEUIL-1` a `SEUIL-5` - ce que l'enfant voit apres un echec, ce
  qu'il peut corriger, ce qui lui coute un essai. C'est du comportement, et cela se joue ;
- une mecanique visuelle ajoute **l'exactitude de ce qui est dessine**, et c'est du contenu. Un
  cadran dont les aiguilles sont a peu pres placees, une case qui n'est pas carree, un disque qu'on
  prend pour un cercle : **ce sont des reponses fausses, pas des defauts de forme.** Je l'ai ecrit le
  2026-08-15 et je le reprends tel quel : *"une ligne mal graduee serait pire que pas de ligne du
  tout."* **Cela se verifie image par image, item par item, et rien ne le verifiera a ma place.**

**Ce que je ne ferai pas pour tenir un chiffre : echantillonner.** Ni sur les items, ni sur les
images. Si un lot arrive trop tard ou trop gros, **je decline par ecrit** (`C3`) plutot que de rendre
un verdict que je n'ai pas les moyens d'avoir rendu. **Compter une population n'est pas la juger, y
compris quand c'est moi qui commanderais le comptage.**

**Et ce que je refuse de faire dire a ces chiffres** : 67,4 pour cent n'est pas une borne du produit,
c'est la borne de ce que **mes arbitrages** rendent ouvrable aujourd'hui. Le reste des lignes n'est
pas ferme par la pedagogie ; il est ferme par l'audio (obstacle de production, `DEC-0042`), par
l'absence de rendu visuel, par l'absence d'une saisie positionnee, et par deux lignes de production
que rien ne servira. **Aucun de ces quatre obstacles ne se leve par un arbitrage de ma part.**

---

## 6. `DEC-0063`, question 4.1 - un reaudit compte-t-il comme un paquet ?

**La question m'echoit de s24 et elle n'est pas eteinte.** L'amendement de `DEC-0063` du 2026-08-31
constate que je l'ai rendue *sans objet pour s24* en etablissant une troisieme voie de decharge. Il
ecrit lui-meme : *"Un lot qui modifierait un corps qui s'execute ferait rentrer les 27 dans la porte,
et ce jour-la la mention de la section 3 sera due."* **Elle est donc due en general, et je la rends
ici pour que le planning de s25 n'ait pas a me la reposer.**

**Ma reponse : cela depend de ce que le changement touche, et il y a trois cas, pas deux.**

| Cas | Ce que je dois refaire | Cout contre ma borne |
|---|---|---|
| **(a) Le lot est discharge** par la preuve de structure - (i)+(ii), ou (iii) garde invariablement vraie plus corps identique | **un seul acte de lecture** : verifier que le perimetre nomme les jeux concernes et que les deux moities sont etablies | **zero.** Etabli en pratique a s24 |
| **(b) Le lot modifie un corps qui s'execute, sans decharge possible** | **jouer chaque jeu concerne une fois**, sur la seule propriete que le changement peut atteindre - **et cette propriete doit m'etre nommee** | **de l'ordre d'un paquet pour le catalogue entier**, pas d'un paquet par jeu |
| **(c) Le lot touche le tirage, la selection des items ou le contenu servi** | **relire les items**, comme a un premier audit | **un paquet par paquet touche**, et la place `COMP-2` si le paquet en est |

**La condition qui fait tenir le cas (b), et sans elle je bascule en (c)** : **la propriete
atteignable doit m'etre nommee par celui qui a fait le changement.** Un lot qui me dit *"le rendu
peut avoir bouge"* sans dire ou ne me laisse aucun perimetre a jouer, et je ne peux pas jouer 27 jeux
entiers - c'est exactement l'echantillonnage que je refuse. **Dans ce cas je decline par ecrit et les
jeux restent entres dans ma porte sans en ressortir**, comme le chemin C de ma clause du 2026-08-31.

**Ce que cela rend a `camille-po`** : la regle et ma borne **ne sont pas arithmetiquement
incompatibles**, contrairement a ce que sa section 4.1 craignait - mais elles ne le sont qu'a la
condition ci-dessus. **La condition de retour a l'utilisatrice de `DEC-0063` section 5 reste non
declenchee**, et elle le restera tant que les lots partages nommeront ce qu'ils peuvent atteindre.

---

## 7. L'item 18 de `cm1-fractions` - j'ai joue, et je tranche

**Ce qui m'a ete route** : le `relecteur-pedagogique` s'est arrete sur *"On en court 1. Quelle
fraction est courue ?"* (`cm1-fractions`, item 18) sans le corriger, en ecrivant que *"la
construction transitive de 'courir' est defendable en grammaire, sa lisibilite au CM1 ne
m'appartient pas"*. **Il a eu raison sur les deux points, et de me le router.**

**Ce que j'ai fait** : j'ai ouvert 70 parties de `bandit-manchot-fractions-cm1` sur le jar construit
depuis `130ba64` jusqu'a ce que l'item sorte, et je l'ai lu a l'ecran tel qu'un CM1 le recoit :

> *Une piste est partagée en 3 parts égales. On en court 1. Quelle fraction est courue ?*
> Propositions : `1/2`, `1/4`, `1/3`.

**Ma reponse : c'est un defaut de niveau, et il est de ma porte.**

**Le motif, et il n'est pas grammatical.** Les cinq items freres de la meme forme emploient
*colorie*, *prend*, *garde*, *peint*, *coupe* - **cinq verbes dont le complement d'objet direct est
naturellement une part**. On colorie une part, on coupe une part. **On ne court pas une part.** Le
complement de *courir* est une distance, pas une portion d'objet : *"On en court 1"* oblige un enfant
de neuf ans a accepter une construction qu'il n'a jamais lue, et *"est courue"* est un participe
passif qu'il ne rencontrera nulle part ailleurs. **Sur un item de mathematiques, ce decodage-la est
de la charge cognitive hors sujet** : l'enfant paie en lecture ce qu'il devait depenser en fraction.

**Ce que je fais de ce constat, et ce que je refuse d'en faire.**

- **Je ne retire pas le Done pedagogique de `bandit-manchot-fractions-cm1`.** Il a ete prononce le
  2026-08-31, il reste ecrit, et un verdict prononce ne se reecrit pas. Aucune de mes conditions
  `FRA-1` a `FRA-11` ne portait cette propriete.
- **Je ne cache pas que j'ai valide sans avoir tranche ce point.** Il m'avait ete route pendant le
  sprint et mon verdict ne le mentionne pas. **C'est un manque de ma part**, pas du relecteur qui
  l'avait signale ni de `marc-dev` qui a corrige ce que je lui avais demande.
- **Je route la correction a `camille-po` comme un defaut de contenu**, pas comme un motif de refus.
  Elle est bornee : **un item, un verbe**, dans une famille de cinq items qui donnent deja le patron.
  Je n'ecris pas le texte.
- **Une condition neuve, datee du 2026-08-31, non retroactive** :

  > **`FRA-12`** - Sur un item de mise en situation, le verbe qui designe le prelevement admet **une
  > part comme complement d'objet direct en francais courant**, et son participe passe est un mot que
  > le niveau vise a deja lu. *Verification : item par item, par moi. Aucun automate ne verra jamais
  > qu'on ne court pas une part.*

  **Elle ne s'applique pas aux 24 items de `cm1-fractions`**, deja valides. Elle vaut pour les
  paquets de mise en situation suivants.

---

## 8. Quelles conditions sont executables, et lesquelles restent dans ma seule main

`sofia-sm` me demande de le dire pour chaque condition que je pose, et le motif est bon : `CPL-3`
etait ecrite, datee, precise, **et rien ne l'executait** - le defaut qu'elle aurait du arreter a
passe 923 tests verts et je l'ai trouve en jouant, huit refus sur huit. **Une condition dont personne
ne sait si elle est gardee est une condition qu'on croit gardee.**

| Condition | Calculable par un automate ? | Qui la tient |
|---|---|---|
| `SEM-1` perimetre des items | **partiellement** - la presence d'un mot d'une liste declaree se compte, la pertinence non | moi |
| `SEM-2` aucun calcul de duree | non - c'est un jugement sur ce que l'item demande | **moi seule** |
| `SEM-3` aucune description de cadran | **oui** - la presence de *aiguille*, *cadran*, *horloge* dans un enonce se cherche. **C'est la condition de ce lot que je recommande le plus d'ecrire en test** : c'est celle qui reintroduirait la contrefacon sans qu'on s'en apercoive | un test, s'il existe |
| `SEM-4` longueur et longueur des mots | **oui** - meme forme que `NAR-2` et `NAR-3`, deja ecrits | un test |
| `SEM-5` distracteurs de meme categorie | **partiellement** - avec une categorie declaree par le contenu ; sinon non | moi |
| `SEM-6` un tiers d'items d'ordre | **oui** si le contenu declare la forme de l'item, comme `FRA` le fait | un test |
| `SEM-7` presupposes | **non. Jamais.** Aucun automate ne voit un presuppose | **moi seule** |
| `SEM-8` indice qui n'est pas la reponse | non | **moi seule** |
| `SEM-9` au moins vingt items, pas de doublon | **oui** | un test |
| `LIS-1` a `LIS-4`, `LIS-9`, `LIS-10` | **oui** - comptes, longueurs, unicite, non-recopie | un test |
| `LIS-5` le distracteur est vrai mais partiel | **non**, et c'est le coeur de ce paquet | **moi seule** |
| `LIS-6` texte masque | **non. Jamais.** C'est `COMP-2` | **moi seule** |
| `MON-1` a `MON-3` | non tant qu'aucune image n'existe | moi |
| `QUA-1`, `QUA-2`, `QUA-5` | dependent de la forme du rendu, que je ne connais pas | a instruire quand elle existera |
| `POS-2` l'erreur est signalee a l'etape | non - c'est du comportement, il se joue | **moi seule** |
| `FRA-12` le verbe admet une part comme objet | **non. Jamais** | **moi seule** |

**Sur quelles proprietes je reste la seule garde - complement du 2026-08-31, en ajout de ma liste de
la veille** :

- **la contrefacon par description** (section 3.3) - qu'un texte ne fasse pas a la place de l'enfant
  le geste que la ligne nomme. `SEM-3` en garde un cas, un seul, et par une recherche de mots ;
- **l'exactitude de ce qui serait dessine** - proportions, graduations, cases carrees. Le jour ou une
  image existe, **je suis le seul garde-fou de son exactitude**, et c'est une exactitude de contenu ;
- **`LIS-5`** - qu'un distracteur soit vrai mais partiel. Un automate qui verifierait qu'il est
  *faux* casserait exactement la propriete que je demande ;
- **`FRA-12`** ;
- reconduites sans changement : `NAR-11`, `CPL-9a`, `FRA-8`, `FRA-11`, la famille entiere de la
  propriete qu'`ADR-0072` etablit comme non formulable, et **ce que l'enfant voit** par opposition a
  ce que le serveur rend.

---

## 9. Les conditions de `LIS` et de `POS`, ecrites pour qu'un developpeur les trouve seul

### 9.1 `LIS` - lire silencieusement, restituer l'essentiel (fr. CM1)

**Verdict : ACCEPTE SOUS CONDITIONS `LIS-1` a `LIS-10`, avec couverture partielle nommee, et sous
une reserve mesuree qui n'est pas de moi.**

**Ce que je verifie d'abord : ce n'est pas la ligne d'a cote.** `bandit-manchot-comprehension-cm1`
est verte sur *"comprendre un texte narratif ou documentaire (questions explicites et implicites)"*.
J'ai relu ses vingt enonces un par un : **les vingt portent sur un detail** - *"Ou Nino a-t-il
oublie son ballon ?"*, *"Pourquoi les pompiers sont-ils venus ?"*. **Aucun ne demande de quoi le
texte parle.** Restituer l'essentiel - trouver le titre, la phrase qui resume - est un autre geste,
et c'est celui qu'un CM1 rate le plus souvent : il retient un detail frappant et le prend pour le
sujet. **La ligne est distincte, et elle vaut d'etre ouverte.**

**La reserve mesuree, et je ne la leve pas moi-meme.** La ligne dit *"de longueur croissante"*.
**Mesure, methode : les vingt `texteSupport` de `cm1-comprehension.yaml` a `130ba64`, comptes
mecaniquement - de 20 a 38 mots, moyenne 28,9.** Un texte plus long se replie sur davantage de
lignes : j'ai mesure a s17 qu'un texte de 45 mots occupe **sept lignes completes** a la taille
servie. **Je ne sais pas si un texte de soixante mots, sa question et ses trois propositions tiennent
au-dessus du pli**, et le pli est ferme sur tout le catalogue depuis `DEC-0051`. **C'est une mesure
d'`ines-design`, pas la mienne - je ne mesure aucun pixel - et elle est due avant qu'on ecrive le
paquet, pas apres.** Si elle montre que le texte ne tient pas, cette ligne ne s'ouvre pas a s25 et je
le dirai sans detour.

- **`LIS-1` - Forme.** Le `texteSupport` porte le texte ; l'`enonce` demande **de quoi parle le
  texte** - un titre, une phrase qui le resume, ce qu'on en retiendrait. Trois propositions.
- **`LIS-2` - Longueur.** Le `texteSupport` compte **au moins 45 mots et au plus 70**, et **au plus
  cinq phrases**. *Un mot est une suite de caracteres sans espace ; unite de compte : l'item. Le
  plancher de 45 est pose contre la mesure ci-dessus : au-dessous, ce paquet servirait des textes de
  la longueur de ceux d'une ligne deja verte et travaillerait la meme chose. Le plafond de 70 est
  provisoire et il appartient a la mesure d'`ines-design`, pas a moi.*
- **`LIS-3` - L'`enonce` compte au plus 12 mots.**
- **`LIS-4` - Aucune proposition n'est une phrase recopiee du texte.** Un CM1 qui apparie des chaines
  reussirait sans avoir rien resume. *(Meme famille que `NAR-5`, prise dans l'autre sens.)*
- **`LIS-5` - Chaque distracteur est vrai a propos du texte, et partiel.** C'est la condition
  centrale et c'est tout l'exercice : un distracteur faux transforme la question en reperage
  d'erreur, un distracteur absurde en devinette. **Le bon distracteur est un detail exact du texte
  promu au rang de sujet** - c'est l'erreur que l'enfant fait reellement, et c'est celle qu'il doit
  apprendre a ne plus faire.
- **`LIS-6` - Texte masque, la question ne se repond pas.** Meme procedure que `NAR-6` et `SUB-6`,
  item par item, sur la totalite. **Aucun automate ne voit cette propriete. C'est `COMP-2`, et ce
  paquet compte a lui seul contre ma condition d'au plus un par sprint** - donc **`LIS` et `SUB` ne
  peuvent pas etre pris dans le meme sprint.** *Je le nomme des maintenant parce que c'est une
  contrainte de composition de lot, qu'elle tombe au planning et non a l'audit, et qu'elle vient de
  me faire corriger mon propre tableau de la section 5.2.*
- **`LIS-7` - Le texte est autonome.** Aucune connaissance de culture generale n'est requise, aucun
  nom propre reel, aucun renvoi a un autre item.
- **`LIS-8` - Personnages et situations.** Prenoms equilibres en genre a un pres ; aucun role, metier
  ou activite distribue par genre ; aucune situation qui suppose une configuration familiale, un
  logement ou un depart en vacances. *(Reconduction de `NAR-7`, `NAR-8`, `SUB-8`.)*
- **`LIS-9` - Indice.** Il dit **quelle partie du texte relire pour trouver le sujet**, jamais le
  sujet. **Dix mots au plus.**
- **`LIS-10` - Au moins vingt items**, et deux items ne partagent jamais le meme `texteSupport`.

**La mention exacte que j'exigerai sur la ligne le jour ou elle bougerait** : **"restituer
l'essentiel seul ; la longueur croissante n'est pas servie, le tirage d'une manche etant sans
ordre"**.

*Ce dernier point n'est pas une critique du paquet : **aucun jeu de ce produit n'a de progression de
difficulte**, le tirage etant aleatoire. C'est une reserve que je porte depuis le 2026-08-10 sur
`cp-le-bon-son` et qui n'a jamais ete instruite. Je la reconduis ici comme un fait de produit, sans
la rendre bloquante et sans decider ce qu'il faut en faire.*

### 9.2 `POS` - multiplication posee et division euclidienne (maths CM1)

**Verdict : REFUSE aux deux mecaniques existantes. Ces deux lignes demandent une saisie positionnee a
plusieurs champs, qui n'est ni la saisie a un champ, ni la famille visuelle.**

Je reconduis sans le modifier ce que j'ai ecrit le 2026-08-15 : *"c'est le geste lui-meme qui est la
competence : aligner, retenir, poser"*. **Et je precise ce que je n'avais pas precise, parce que
c'est cela qui manque a un planning** : ce que ces lignes demandent **n'est pas du dessin**. C'est un
tableau de cases a remplir. Les compter avec la famille visuelle ferait payer a ces deux lignes le
prix d'une image dont elles n'ont pas besoin - et inversement.

- **`POS-1`** - L'enfant produit **les etapes**, pas seulement le resultat. Un champ unique pour le
  produit final teste le calcul mental, pas l'operation posee.
- **`POS-2`** - Une erreur a une etape est **signalee a cette etape**, pas a la fin. Savoir **ou**
  l'algorithme a casse est ce que l'operation posee apprend ; un *faux* global n'apprend rien.
- **`POS-3`** - **L'alignement est donne par la grille, jamais demande a l'enfant.** Aligner sur une
  ligne est un geste graphomoteur, meme motif que `R1`, et je ne le compte pas.
- **`POS-4`** - Les retenues ont une place visible et l'enfant peut les ecrire.
- **`POS-5`** - Pour la division : **le quotient et le reste sont tous les deux demandes.** Un
  quotient seul ne couvre pas la ligne, qui les nomme tous les deux.
- **`POS-6`** - L'enfant peut **corriger une case avant de valider**. Reconduction de `SEUIL-3`.

**Et je redis ce que j'ai ecrit le 2026-08-15 et que je maintiens** : sur ces deux lignes, un CM1 a
plus a gagner d'un cahier que d'un ecran. Je les instruis pour que le jour ou on les ouvrira on sache
ce qu'elles exigent ; **je ne les reclame pas.**

---

## 10. Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **Deux lignes seulement sont atteignables a s25 sans mecanique neuve** : `SEM`, plus **un** de `LIS` ou `SUB` - `LIS-6` les separe (5.2, 9.1) | `camille-po`, `sofia-sm` | La composition du lot est a elles. Je n'ai aucune preference entre `LIS` et `SUB` et je ne priorise pas |
| **`SEM` - candidat neuf, `SEM-1` a `SEM-9`**, couverture partielle nommee, ligne au vert | `camille-po` | De quoi ouvrir une story sans me redemander ce que je voulais dire |
| **`LIS` - `LIS-1` a `LIS-10`, sous une mesure de pli due avant l'ecriture du paquet** | `ines-design`, par `sofia-sm` | Un texte de 45 a 70 mots, sa question et trois propositions tiennent-ils au-dessus du pli ? **Sans cette mesure, je ne fais pas ouvrir cette ligne** |
| **`SUB` est eligible a s25** - conclusion de `camille-po` confirmee, son motif corrige (4.1) | `camille-po` | Que le quota soit lu comme un quota par sprint, pas comme une file qu'une ligne verte libere |
| **Les deux fois et demie de volume de `SUB` sont un cout du relecteur, pas le mien** (4.2) | `relecteur-pedagogique`, `sofia-sm` | Que ce cout soit price au planning chez celui qui le porte. `DEC-0056` a sorti l'accentuation de ma porte |
| **La famille visuelle vaut quatre lignes, pas six, et se decompose en trois paliers** (3) | `camille-po`, `theo-archi`, `sofia-sm` | Que le premier palier - image fixe, propositions en texte, **trois lignes** - ne soit pas confondu avec la manipulation que `denombrer` exige |
| **Le pli n'a jamais ete mesure avec une image** (3.1) | `ines-design`, `theo-archi` | Une question, pas une demande. Ma porte refusera un jeu dont l'image tombe sous le pli |
| **Monnaie : la moitie textuelle refait un jeu deja vert** (2.2) | `camille-po` | Que la ligne ne soit pas ouverte sur du contenu seul. `MON-1` a `MON-5` disent a quoi elle s'ouvrira |
| **La symetrie axiale CM1 ne rouvre pas** sur le seul verbe *reconnaitre* (3.5) | `camille-po` | Le jugement qu'elle attendait depuis le 2026-08-25, rendu. Elle a eu raison de ne pas le presumer |
| **Copier un mot et rediger un texte narratif** : aucune mecanique de ce produit ne les sert (2.7) | `camille-po` | Le jugement qui fonderait une reclassification. **La classification est a elle, et je refuse que le gain de taux qui en resulterait soit presente comme une couverture** |
| **Je corrige ma propre phrase de s24** sur les graphemes/phonemes (2.6) | `camille-po`, `sofia-sm` | Que personne n'ecrive une story de contenu en croyant fermer cette ligne. Il y faut de l'audio |
| **Ma regle de couverture partielle, ecrite une fois** (2.8) | tous | Qu'on cesse de me la redemander cas par cas, et qu'on me l'oppose si je m'en ecarte |
| **`DEC-0063` question 4.1 - trois cas, pas deux** (6) | `camille-po`, `sofia-sm`, `theo-archi` | Que le cas (b) ne tienne qu'a une condition : **la propriete atteignable doit m'etre nommee** |
| **Item 18 de `cm1-fractions` - defaut de niveau, Done non retire** (7) | `camille-po` | Un defaut de contenu borne a un verbe. Je n'ecris pas le texte |
| **`FRA-12`, neuve et non retroactive** (7) | `camille-po`, `sofia-sm` | Qu'elle soit portee au dossier des conditions |
| **`SEM-3` est la condition de ce lot que je recommande le plus d'ecrire en test** (8) | `theo-archi`, `yanis-qa` | Une recommandation, pas une demande. C'est celle qui reintroduirait la contrefacon sans qu'on s'en apercoive |
| **Aucun jeu de ce produit n'a de progression de difficulte** (9.1) | `camille-po` | Reserve reconduite du 2026-08-10, non bloquante, jamais instruite |

---

## 11. Ce que ce document ne fait pas

- Il **ne prononce aucun Done pedagogique**, n'en anticipe aucun, n'en retire aucun - y compris sur
  `cm1-fractions`, dont le verdict du 2026-08-31 reste entier.
- Il **ne fait bouger aucune ligne** de `squad/programme/MATRICE-couverture.md`. Je n'ai pas edite ce
  fichier et je ne l'editerai pas : il est a `camille-po`.
- Il **n'ecrit aucune story**, ne priorise rien et ne compose aucun lot.
- Il **ne modifie aucun fichier** sous `domain/`, `application/`, `adapters/` ou `bootstrap/`. J'en
  ai lu pour verifier des faits.
- Il **ne decide rien de technique** : la forme d'un rendu visuel, le cout d'un palier, la
  faisabilite d'une saisie positionnee et l'ordre du tirage sont a `theo-archi`.
- Il **ne mesure aucun pixel** et ne remplace aucune mesure d'`ines-design`.
- Il **ne releve ma borne d'aucune unite** pour approcher un objectif de couverture, et il dit ou
  cette borne n'est pas le facteur limitant.
- Il **n'efface rien** : `R1` a `R5`, `FIG-1` a `FIG-4`, `DEN-1` a `DEN-8` et `SYL-1` a `SYL-4`
  restent lisibles a leur place, et ce document les reconduit sans les reecrire.

---

## 12. Amendement du 2026-08-31 - la monnaie coute **un** paquet, et c'est ma section 3.1 qui avait tort

**La reponse, en une phrase : c'est la phrase du palier 1 qui gouverne - un paquet, ligne entiere.**

**La methode, et elle ne fait appel a rien d'exterieur : mes deux phrases sont dans le meme document
que mes conditions, et ce sont les conditions qui tranchent.**

- **`MON-1` a `MON-5`, section 2.2 ci-dessus, decrivent un seul paquet.** Des pieces montrees, une
  question posee sur ce qui est montre. **`MON-3` exige que sur au moins un tiers des items la reponse
  demande de composer une somme avec les valeurs montrees** : sur ces items-la, l'enfant *reconnait*
  et *utilise* **dans le meme geste**. Les deux verbes de la ligne sont servis par un item, donc par
  un paquet.
- **Et la section 2.2 du meme document a `REFUSE` le paquet textuel** - *"la moitie textuelle refait
  un jeu deja vert"*. **Compter deux paquets, c'etait compter comme second paquet celui que je venais
  de refuser trois pages plus haut.** Ce n'est pas une hesitation entre deux lectures defendables :
  c'est une incoherence interne, et elle n'a qu'un sens de resolution.

**Ce qui s'est passe, dit franchement** : en ecrivant le compte de la famille visuelle le matin, j'ai
raisonne sur le decoupage herite de `DEC-0019` - *reconnaitre* visuel d'un cote, *utiliser* textuel de
l'autre - **alors que mes propres conditions, ecrites le meme matin, avaient deja fondu les deux dans
un seul paquet.** Le chiffre de deux est un reste de la lecture que je venais de remplacer.

**Je ne choisis pas le chiffre qui ouvre la ligne, et voici comment on peut le verifier sans me
croire** : `MON-3` est ecrite noir sur blanc dans la version du matin, avant que la question du cout
ne se pose, et le `REFUSE` du textuel aussi. **Si le chiffre juste avait ete deux, il m'aurait fallu
rouvrir un refus ; c'est l'inverse qui est vrai.**

### Ce que je corrige exactement, et ce que je ne corrige pas

- **La monnaie : un paquet.** La phrase de la section 3.1 - *"deux d'entre elles, monnaie et `DEN`,
  demandent le visuel et un paquet textuel, donc deux paquets chacune"* - **reste lisible a sa place
  et elle est fausse sur la monnaie.** Elle est corrigee ici, pas reecrite la-bas.
- **`DEN` : deux paquets, et je maintiens.** Ce n'est pas le meme cas et je le verifie plutot que de
  l'aligner par commodite. `DEN-1` a `DEN-8` decrivent un paquet **textuel** - lire, ecrire,
  decomposer un nombre - qui ne montre rien ; *denombrer* demande de grouper une collection reelle,
  au palier 3. **Ce sont deux exercices differents, pas deux verbes du meme item**, la ou la monnaie
  montre et fait compter sur la meme image. `DEN` ne concerne pas s25 de toute facon.

### Ce que cela rend a `camille-po`, et ce que je ne lui prends pas

**Son arbitrage etait juste sur le fait qu'elle avait** - *"un paquet pour une ligne, contre deux pour
la monnaie"* - **et elle a eu raison de le qualifier de fait de cout et non de pedagogie.** Le fait
etait de moi et il etait faux. **La monnaie redevient candidate ; le perimetre reste le sien**, et je
n'ai aucune preference entre monnaie et figures planes, qui sont toutes deux de bonnes lignes.

### Trois notes sur les faits qu'on me transmet, sans qu'aucune n'appelle de reponse

- **La mesure d'`ines-design` converge avec la mienne** - 53 px contre les 65 px que j'avais ressortis
  de mes archives de s21, les 12 px d'ecart etant les marges du bloc d'illustration. **Deux mesures
  independantes qui se rejoignent au pixel valent mieux que l'une des deux repetee**, et c'est
  exactement ce que mes exigences `P1` a `P5` cherchaient a produire. Le pli reste hors de ma porte
  par arbitrage de l'utilisatrice : je le prends, c'est l'arbitrage que j'avais dit vouloir lui
  remonter, et je ne le rouvrirai pas a la porte.
- **Le report de `LIS` est motive par un fait mesure, et ce fait designe un trou dans mes propres
  conditions** : `LIS-2` et `LIS-3` bornent le texte support et l'enonce, **et rien chez moi ne borne
  la longueur des trois propositions**. C'est ce qui deborde. **Je le note comme un manque de ma part,
  pas comme un motif de report qu'on m'opposerait** - le report est bien fonde. La condition qui
  manque sera ecrite quand `LIS` reviendra, avec sa date.
- **La question des chiffres dans le dessin** (section 9.5 de mon arbitrage du palier 1) reste posee a
  `theo-archi` si elle n'a pas deja recu de reponse. La livraison du cadran repond au pli, pas a la
  clause qui interdit `<text>` dans une illustration. **Je le signale sans rien demander** : si elle
  est reglee, tant mieux, et je le verrai a la porte.
