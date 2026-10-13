# Arbitrage prealable de s57 - instruction ligne par ligne des referentiels de CE1 de questionner le monde
# (`QLM-CE1-01` a `-12`) et d'EMC (`EMC-CE1-01` a `-05`), `PAR-13` sur ces deux matieres, et leur prix

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-13, 16:33 (UTC+02:00), lu par `date`.
- **Arbre** : `round-two` a `9d101ea0`, branche `s57-nadia`, arbre propre avant ecriture.
- **Type** : arbitrage prealable, hors sprint (s57 n'est pas ouvert). Il execute ce que `DEC-0120` A.36 route a
  `nadia-prof` : `PAR-13` pour les deux matieres neuves de CE1, l'instruction ligne par ligne des deux referentiels et
  son prix. Le modele est `ARBITRAGE-PREALABLE-s48-francais-ce1.md` (conditions transverses, puis une section par
  ligne, verdict et plafond, identifiants, recapitulatif).
- **Ce document ne prononce aucun Done**, ne touche ni la matrice, ni un paquet, ni le catalogue, ni le code. Les
  plafonds qu'il ecrit sont a reporter dans la matrice par `camille-po`.
- **Je n'ai rien joue, et ce n'est pas un audit degrade : il n'y a rien a jouer.** Aucun paquet n'existe sur ces
  dix-sept lignes. Ce que j'ai fait a la place est en section 0.
- **Lien** : `DEC-0120` (A.27 a A.37) ; mes referentiels `squad/programme/questionner-le-monde/competences-ce1.md` et
  `squad/programme/enseignement-moral-et-civique/competences-ce1.md` ; `ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md` ;
  `ARBITRAGE-PREALABLE-s48-francais-ce1.md` (`TRV-CE1-1` a `-7`, `LON-CE1-5`) ; `ARBITRAGE-s36-longueur-d-enonce-au-ce1.md`
  (`LON-CE1-1`, `LON-CE1-2`) ; `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` (`BIA-1` a `BIA-5`) ;
  `ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md` (`FER-1` a `FER-3`) ;
  `squad/programme/enseignement-moral-et-civique/competences-cp.md` (`EMC-1` a `EMC-7`) ;
  `ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md` (`CYC-1`, `PAR-2`) ;
  `ARBITRAGE-PREALABLE-s25-palier-1-visuel-mon-ordre-du-15-aout-et-la-mesure-de-pli.md` (`CON-1`) ;
  `ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md` (`PAR-13`).

## Resume

| # | Point | Decision | Section |
|---|---|---|---|
| I1 | Conditions transverses | `TRV-CE1-1`, `-2`, `-5`, `-6`, `LON-CE1-1`, `-2`, `-5`, `FER-1` a `-3`, `CYC-1`, `CON-1` valent telles qu'ecrites ; trois neuves : `NRC-CE1-1` (aucune reprise du CP), `QMT-CE1-1` (mots que l'enfant lit), `QMT-CE1-2` (source dans le commentaire) | 2 |
| I2 | Questionner le monde CE1, 12 lignes | **9 acceptees sous conditions, plafond `couvert-valide`** ; `QLM-CE1-07` **acceptee, plafond `en cours`** sur sa moitie textuelle ; `-09` et `-11` **acceptees, en attente d'un dessin** | 3 |
| I3 | EMC CE1, 5 lignes | **4 acceptees sous conditions, plafond `couvert-valide`** ; `EMC-CE1-05` **confirmee `non couvrable en l'etat`** | 4 |
| I4 | `PAR-13` | **Ni l'EMC CE1 ni les neuf lignes textuelles de questionner le monde CE1 ne sont un terrain neuf** (cousinage) ; **chacune des trois lignes a dessin en est un** | 5 |
| I5 | `PAR-2` | L'EMC CE1 compte avec l'EMC CP et CM1 pour une seule matiere ; questionner le monde CE1 avec questionner le monde CP | 5.3 |
| I6 | Prix | **L'instruction n'a pas de prix mesurable** et n'en a jamais eu dans ce depot ; elle est faite ici, hors sprint, hors des 8,0 de s57. Lots : bareme, pas mesure | 6 |

---

## 0. Ce que j'ai lu et mesure

| Mesure | Commande ou fichier | Rendu |
|---|---|---|
| Statuts des matieres voisines | `awk` de la "Methode du compte" de la matrice, cle matiere et niveau, sur `9d101ea0` | questionner le monde CP : 12 `couvert-valide`, 3 `non couvert`, 1 `non couvrable` ; EMC CP 11 / 1 / 2 ; EMC CM1 11 / 0 / 1 ; sciences CM1 19 / 0 / 1 ; questionner le monde CE1 12 `non couvert` ; EMC CE1 4 `non couvert`, 1 `non couvrable` |
| Paquets de CP voisins, items lus | `grep -n "enonce:\|bonneReponse"` sur `cp-qlm-hygiene`, `cp-qlm-cycle-de-vie`, `cp-qlm-etats-eau`, `cp-qlm-matiere`, `cp-emc-regle-souhait-loi`, `cp-emc-droits-enfant`, `cp-emc-regles-de-vie` | quatre recouvrements d'items avec mes lignes de CE1, nommes en 1.2 |
| Sujets de CE1 deja presents dans le contenu | `grep -lic` de 17 mots (`aimant`, `vapeur`, `têtard`, `féculent`, `continent`, `océan`, `sanction`, `devoir`, `repas`, ...) sur `contenu/*.yaml` | `aimant` dans `cm1-sciences-melanges` (propositions) ; `têtard` dans `cm1-sciences-etapes-developpement` ; `continent` et `océan` dans `cp-qlm-situer-la-france` ; `sanction` dans `cm1-emc-regle-loi` ; `féculent` nulle part |
| Paquets cousins servis | `grep -c "paquet: <paquet>$"` sur `catalogue.yaml` ; `lignesDeReferentiel` de chacun | servis (1 chacun) : `cm1-sciences-etapes-developpement` (`SCI-CM1-08`), `-regimes-alimentaires` (`-07`), `-melanges` (`-02`), `-etats-matiere` (`-01`), `cm1-emc-regle-loi` (`EMC-CM1-08`), `-droit-devoir` (`-07`), `-regles-vie-collective` (`-04`), `cp-emc-regle-souhait-loi`, `cp-emc-regles-de-vie`, `cp-qlm-etats-eau`, `cp-qlm-hygiene`, `cp-qlm-matiere`, `cm1-histoire-geographie-nommer-en-geographie`. **Non servi** : `cp-qlm-situer-la-france` (0) |
| `CYC-1`, lecture des paquets de cycle 3 sur les memes objets | `grep` des enonces de `cm1-sciences-regimes-alimentaires` et `cm1-sciences-etats-matiere` | le CM1 nomme *fusion*, *solidification*, *vaporisation* ; le CP nomme *fonte*. Les deux sont justes ; le CE1 garde *fonte* et *gel*, les mots de sa ligne |
| Prefixes neufs libres | `git grep -E` de chaque prefixe sur l'arbre ; `grep -c` dans la section 4 du registre | 18 prefixes, 0 occurrence chacun avant ecriture. `DEV` etait pris (2 lignes) : j'ai pris `DVP` |

Non lance : aucun jar, aucun jeu. Il n'y a rien a jouer.

---

## 1. Ce que l'instruction ajoute au jugement de faisabilite, et la reponse de l'utilisatrice

### 1.1 La reponse du 2026-10-13

`DEC-0120` A.27 transmet, forme 2 de `DEC-0062`, deux reponses a mes questions : `Q1` *"non nouveaux items"* (pas de
ligne de CE1 jumelle d'une ligne de CP), `Q2` *"non pas de son"*. **Je ne les ai pas lues de sa main** ; l'agent qui me
lance les resume ainsi : pas de nouveaux items au CE1 par reprise du CP, pas de son.

**Ce que j'en tire pour l'instruction** : la frontiere avec le CP ne suffit pas au niveau de la ligne ; elle doit tenir
**au niveau de l'item**. Une ligne de CE1 qui a un contenu propre peut quand meme recevoir des items de CP. C'est
`NRC-CE1-1` (section 2).

### 1.2 Quatre recouvrements d'items, trouves en lisant les paquets de CP

Mon referentiel ecrit la frontiere ligne contre ligne. Lu contre les items servis, il en manquait quatre :

| Ligne de CE1 | Item de CP qui la sert deja | Ce que la condition ferme |
|---|---|---|
| `QLM-CE1-04`, *reconnaitre un repas varie* | `cp-qlm-hygiene` : *"Que doit-on faire pour bien nourrir son corps chaque jour ?"* - *"manger des aliments variés"* | `FAL-CE1-2` : au CE1, le repas varie se reconnait par les familles de ses aliments, jamais par la regle d'hygiene |
| `QLM-CE1-05`, glace et eau liquide | `cp-qlm-etats-eau` : douze items *"solide ou liquide ?"*, et le nom de la fonte | `ETE-CE1-1` : chaque item porte la vapeur d'eau ou le lien avec la temperature |
| `QLM-CE1-11`, continents et oceans | `cp-qlm-situer-la-france` (non servi) : *"La France est dans quel continent ?"*, *"Quel océan est à l'ouest de la France ?"* | `COC-CE1-1` : aucun item ne situe la France |
| `EMC-CE1-01`, *le geste qui protege* un bien de tous | `cp-emc-regles-de-vie` : ranger le materiel, *"les remettre à leur place"* (crayons de la classe) | `BCO-CE1-1` : ranger et remettre a sa place ne sont jamais la bonne reponse |

Et une demi-ligne a borner : `QLM-CE1-12`, *"quelques evenements de la vie d'un personnage"*, touche `QLM-CP-14`
(avant, apres, sur une frise de la journee). `GEN-CE1-1` la tient a l'echelle d'une vie.

**Aucun intitule ne change.** Les cinq lignes ont un contenu propre au CE1 ; c'est au niveau de l'item que le CP
pouvait revenir. Les deux referentiels recoivent une note datee qui renvoie ici.

### 1.3 Pas de son

Aucune des dix-sept lignes ne demande une voix, et aucun item n'en portera : `TRV-CE1-1` (aucun champ a voix haute au
CE1) vaut pour ces deux matieres. La premisse de l'adulte qui lit (`DEC-0046`, `DEC-0068`) reste arbitree pour le CP
seul.

---

## 2. Conditions transverses

**Reprises telles qu'ecrites**, sur les dix-sept lignes : `TRV-CE1-1` (declaration `niveau: "CE1"`, une ligne par item,
`LON-CE1-1`, `-2`, `-5`, aucun champ a voix haute ; aucun `texteSupport` sur ces lignes) ; `TRV-CE1-2` (trois
propositions distinctes, une seule acceptable, familles de distracteurs nommees a cote de l'item, la bonne reponse
strictement la plus longue dans la moitie des items au plus ; la clause des lignes binaires du francais ne s'applique
pas) ; `TRV-CE1-5` (indice : une methode, jamais la reponse, douze mots au plus, pas le meme sur tout le paquet, jamais
une regle fausse) ; `TRV-CE1-6` (prenoms equilibres et varies, aucun role distribue par genre, rien qui suppose un foyer,
un logement ou un objet possede) ; `FER-1` a `FER-3` ; `CYC-1` (aucun item de CE1 ne contredit un item servi au CP ou au
CM1 sur le meme objet ; l'instance qui audite lit ces paquets) ; `CON-1` sur les lignes a dessin. **Sur l'EMC CE1,
`EMC-1` a `EMC-7` s'ajoutent**, comme le referentiel le dit. **Sur questionner le monde CE1, `BIA-4` s'ajoute** sur
`-04` et `-12`.

> **`NRC-CE1-1` - aucune reprise du CP, item par item.** Aucun item d'un paquet de CE1 de questionner le monde ou d'EMC
> ne reprend un item d'un paquet de CP (meme question, ou meme bonne reponse a la meme tache), et **chaque bonne reponse
> mobilise le contenu propre a la ligne de CE1** que sa frontiere nomme (tableau "Frontieres" du referentiel, et 1.2
> ci-dessus). Un item qu'un enfant de CP resoudrait avec ce que le paquet de CP voisin lui a appris, sans rien de plus,
> sert la ligne de CP et ne compte pas pour la ligne de CE1. **Motif** : la reponse de l'utilisatrice du 2026-10-13
> (1.1). Je lis les paquets de CP voisins a chaque audit ; aucun garde-fou ne compare deux paquets.

> **`QMT-CE1-1` - les mots que l'enfant de CE1 lit en questionner le monde.** Sont exclus de l'enonce, de l'indice et
> des propositions : *herbivore*, *carnivore*, *omnivore*, *reproduction*, *vieillissement* (mots du CM1, frontiere de
> `QLM-CE1-01` et `-02`) ; *fusion*, *solidification*, *vaporisation*, *evaporation*, *condensation* (CM1, `SCI-CM1-01`) ;
> *conducteur*, *isolant*, *larve*, *plantule*, *legumineuse*.
> Sont admis, parce que la ligne les nomme : *metamorphose*, *germination*, *graine*, *pousse*, *tetard*, *chenille*,
> *chrysalide*, *vegetaux*, *feculents*, *produits laitiers*, *glace*, *vapeur d'eau*, *fonte*, *gel*, *temperature*,
> *aimant*, *pile*, *ampoule*, *fil*, *interrupteur*, *prise*, *legende*, *itineraire*, *littoral*, *continent*, *ocean*,
> *planisphere*, *globe*, *generation*, *frise*. Un mot admis difficile (*feculents*, *littoral*) est explique par
> l'indice avec des exemples, jamais par une definition.

> **`QMT-CE1-2` - la source dans le commentaire.** Tout fait qui peut se discuter - le regime d'un animal, la famille
> d'un aliment, ce qu'un aimant attire, le nom et le nombre des continents et des oceans, une etape d'un developpement -
> porte sa source dans le commentaire du paquet, a cote de l'item, comme `HIS-3` et `EMC-7` l'exigent. Un fait sans
> source n'entre pas.

---

## 3. Questionner le monde CE1

### 3.1 `QLM-CE1-01` - ordonner les etapes d'un developpement, metamorphose et germination comprises

**Verdict : ACCEPTEE SOUS CONDITIONS `DVP-CE1-1`, `DVP-CE1-2`.** Plafond : `couvert-valide`.

Un developpement est un cycle : *"quelle est la premiere etape de la vie de la grenouille ?"* a deux reponses
defendables (l'oeuf, ou la grenouille qui pond). La question qui n'en a qu'une est l'etape voisine.

> **`DVP-CE1-1` - perimetre et forme.** Chaque item nomme une espece familiere et une etape, et demande l'etape qui vient
> **juste avant** ou **juste apres**. Jamais *"la premiere"* ni *"la derniere"*. Especes et etapes d'une liste fermee,
> ecrite dans le commentaire du paquet avec sa source (`QMT-CE1-2`) : grenouille (oeuf, tetard, jeune grenouille,
> grenouille), papillon (oeuf, chenille, chrysalide, papillon), poule (oeuf, poussin, poule), une plante a graine
> (graine, jeune pousse, plante, fleur, fruit). Une metamorphose et une germination sont chacune en bonne reponse
> (`FER-1`). La seconde tache (`FER-3`) range trois etapes dans l'ordre, propositions en suites de trois mots.

> **`DVP-CE1-2` - distracteurs et exactitude.** Distracteurs : une etape de la meme espece hors de sa place, ou une etape
> d'une autre espece (*tetard* pour le papillon). Jamais deux etapes defendables (*jeune grenouille* et *grenouille*
> ensemble apres le tetard). Aucun item de cette ligne ne dit qu'une graine a besoin de lumiere pour germer (`BPL-CE1-2`).
> Aucun item ne contredit `cm1-sciences-etapes-developpement` (`CYC-1`).

### 3.2 `QLM-CE1-02` - relier un animal familier a ce qu'il mange

**Verdict : ACCEPTEE SOUS CONDITIONS `RAL-CE1-1`, `RAL-CE1-2`.** Plafond : `couvert-valide`.

> **`RAL-CE1-1` - trois categories, sans les mots du CM1.** La bonne reponse est *des vegetaux*, *d'autres animaux* ou
> *les deux*, ou un aliment nomme. Chacune des trois est en bonne reponse (`FER-1`). Le regime de chaque animal porte sa
> source (`QMT-CE1-2`) et ne contredit pas `cm1-sciences-regimes-alimentaires` (`CYC-1`).

> **`RAL-CE1-2` - pas d'animal au regime discute, pas de nourriture du commerce.** Exclus : le chat et le chien
> (croquettes et patee rendent une seconde reponse defendable), le renard et le herisson (enseignes souvent comme
> carnivores, ils mangent aussi des fruits). Aucune proposition n'est un aliment du commerce pour animaux. L'animal mange
> dans la nature ou a la ferme, et l'enonce le dit.

### 3.3 `QLM-CE1-03` - ce dont une plante a besoin

**Verdict : ACCEPTEE SOUS CONDITIONS `BPL-CE1-1`, `BPL-CE1-2`.** Plafond : `couvert-valide`.

Le piege d'exactitude est dans les distracteurs : une plante a aussi besoin d'air et de sels mineraux. Un distracteur
*"de l'air"* serait juste.

> **`BPL-CE1-1` - perimetre et distracteurs.** Les bonnes reponses sont l'eau, la lumiere et la chaleur, chacune au moins
> une fois (`FER-1`). **Ni *l'air*, ni *la terre*, ni *l'engrais*, ni *les sels mineraux* ne sont jamais proposes**, ni
> en bonne reponse ni en distracteur : ce sont de vrais besoins hors de la parenthese, ou un besoin discute (la terre).
> Distracteurs : ce dont une plante n'a pas besoin (*du sucre*, *du lait*, *du bruit*). Seconde tache (`FER-3`) : ce qui
> arrive a une plante privee de l'un des trois.

> **`BPL-CE1-2` - germer n'est pas grandir.** Aucun item ne dit qu'une graine a besoin de lumiere pour germer. Un item sur
> la graine porte sur l'eau ou la chaleur ; la lumiere porte sur la plante qui a deja des feuilles.

### 3.4 `QLM-CE1-04` - classer un aliment dans sa famille, reconnaitre un repas varie

**Verdict : ACCEPTEE SOUS CONDITIONS `FAL-CE1-1`, `FAL-CE1-2`, et `BIA-4`.** Plafond : `couvert-valide`.

> **`FAL-CE1-1` - une classification sourcee, les aliments ambigus exclus.** Les six familles de la ligne, chacune en
> bonne reponse (`FER-1`), classees selon une source nommee dans le commentaire (`QMT-CE1-2`). La pomme de terre est
> rangee avec les feculents, au moins une fois : c'est le piege de la ligne. **Exclus**, parce que deux familles sont
> defendables selon la source : les legumes secs (lentilles, pois chiches), le mais, les petits pois, l'avocat, les
> olives, les fruits a coque, le beurre et la creme, le jus de fruits, tout plat compose (pizza, sandwich, gratin).

> **`FAL-CE1-2` - le repas varie se reconnait par ses familles.** L'item montre trois menus nommes, d'un personnage ou
> de la cantine, et la bonne reponse est celui qui a le plus de familles. Jamais *"pourquoi manger varie ?"*, qui est
> l'item de `cp-qlm-hygiene` (1.2). Aucun menu n'est qualifie de mauvais, aucun aliment de defendu ; aucun item ne
> demande a l'enfant ce qu'il mange (`BIA-4`).

### 3.5 `QLM-CE1-05` - l'eau sous ses trois etats, la fonte et le gel relies a la temperature

**Verdict : ACCEPTEE SOUS CONDITIONS `ETE-CE1-1`, `ETE-CE1-2`.** Plafond : `couvert-valide`.

> **`ETE-CE1-1` - ce que le CP n'a pas servi.** Chaque item porte la vapeur d'eau ou le lien entre un changement d'etat
> et la temperature (*l'eau gele quand il fait tres froid*, *la glace fond quand on la chauffe*). Aucun item ne demande
> seulement *"solide ou liquide ?"* ni seulement le nom de la fonte : ce sont les items de `cp-qlm-etats-eau`
> (`NRC-CE1-1`). La vapeur d'eau est en bonne reponse dans au moins un tiers des items.

> **`ETE-CE1-2` - exactitude.** La vapeur d'eau ne se voit pas. **La buee, le nuage, le brouillard et la "fumee" blanche
> d'une casserole ne sont jamais presentes comme de la vapeur d'eau**, ni dans une bonne reponse ni dans un indice : ce
> sont de toutes petites gouttes d'eau liquide. Aucun item ne dit que l'eau ne devient vapeur qu'en bouillant (le linge
> seche sans bouillir). Si un nombre est cite, c'est 0 degre pour la fonte et le gel, et aucun autre.

### 3.6 `QLM-CE1-06` - ce qu'un aimant attire et n'attire pas

**Verdict : ACCEPTEE SOUS CONDITIONS `AIM-CE1-1`, `AIM-CE1-2`.** Plafond : `couvert-valide`.

> **`AIM-CE1-1` - des objets au metal sans doute.** Attires : objets en fer ou en acier ordinaire (clou, trombone,
> epingle, aiguille). Non attires : objets non metalliques, et objets en aluminium (papier d'aluminium), en cuivre
> ou en or. **Exclus**, parce que leur metal varie : les pieces de monnaie, les canettes, les cles, les couverts, les
> casseroles, les bijoux. La source est dans le commentaire (`QMT-CE1-2`).

> **`AIM-CE1-2` - le piege est servi, la regle fausse jamais.** Au moins un quart des items ont pour cible un objet
> metallique que l'aimant n'attire pas. Aucun enonce, aucun indice, aucune explication ne dit que *l'aimant attire les
> metaux*.

### 3.7 `QLM-CE1-07` - le circuit electrique simple

**Verdict : ACCEPTEE SOUS CONDITIONS `CIR-CE1-1`, `CIR-CE1-2`. Plafond : `en cours` aujourd'hui**, sur la moitie
textuelle, avec la mention : *"nommer les elements d'un circuit seuls ; dire si un circuit dessine fait briller
l'ampoule n'est pas servi"*. `couvert-valide` quand un circuit dessine existe et sert la seconde moitie.

`DEC-0120` A.31 notait qu'aucun plafond `en cours` n'etait ecrit pour cette ligne. Il l'est ici.

> **`CIR-CE1-1` - la moitie textuelle.** Les quatre elements de la parenthese, chacun en bonne reponse (`FER-1`), par leur
> role (*ce qui allume ou eteint*, *ce qui fait briller*, *ce qui donne l'electricite*, *ce qui relie*). La pile de
> poche est le seul generateur ; aucun item ne parle de la prise ni du courant de la maison (frontiere avec `-08`).

> **`CIR-CE1-2` - la moitie dessinee.** Dire si l'ampoule brille se joue sur un circuit dessine, en designation, et
> jamais sur un circuit decrit par des mots (`CON-1` : en effacant le dessin, la reponse ne doit plus se trouver).
> Circuits ouverts et fermes a parts voisines ; une coupure visible a la taille rendue. Avis de faisabilite du dessin a
> `theo-archi` (`DEC-0099` section 4 ter).

### 3.8 `QLM-CE1-08` - les dangers de l'electricite de la maison

**Verdict : ACCEPTEE SOUS CONDITIONS `DEL-CE1-1`, `DEL-CE1-2`.** Plafond : `couvert-valide`.

> **`DEL-CE1-1` - le bon geste est un geste d'enfant.** La bonne reponse est ne pas toucher, ne pas approcher de l'eau,
> ne pas brancher avec les mains mouillees, ou prevenir un adulte. **Jamais un geste de reparation ou de manipulation
> par l'enfant** (reparer un fil, debrancher un appareil qui fume). Les trois situations de la parenthese en bonne
> reponse (`FER-1`). **Aucune pile** dans cette ligne : la pile de poche est sans danger electrique et sert `-07`, et la
> pile bouton a un autre danger, l'avaler, que cette ligne ne sert pas.

> **`DEL-CE1-2` - aucune blessure decrite.** Aucun item ne decrit une electrocution, une brulure ou une mort, et aucun
> personnage nomme n'est blesse : comme `EMC-3`, l'item enseigne le geste sans mettre en scene l'accident.

### 3.9 `QLM-CE1-09` - lire un plan a l'aide de sa legende

**Verdict : ACCEPTEE SOUS CONDITION `PLG-CE1-1`. En attente d'un dessin**, plafond `couvert-valide` quand il existe. Aucune
moitie textuelle : lire une legende sans plan est une contrefacon (`CON-1`).

> **`PLG-CE1-1` - la legende est le chemin.** Chaque item demande de lire un symbole de la legende pour trouver un lieu,
> ou de suivre un itineraire decrit par des lieux de la legende. Un item qui se resout sans la legende sert `QLM-CP-11`
> et ne compte pas ici (`NRC-CE1-1`). Plan d'un lieu fictif nomme ; aucun item ne suppose le quartier de l'enfant
> (`EMC-6`).

*Ajout du 2026-10-13, 17:01 : la forme degradee proposee par `theo-archi` est jugee en section 11 (recevable sous
`PLG-CE1-2`, sur la moitie "situer un lieu" seule, plafond `en cours`). Le verdict ci-dessus n'est pas reecrit.*

### 3.10 `QLM-CE1-10` - identifier un paysage par ses elements

**Verdict : ACCEPTEE SOUS CONDITIONS `PAY-CE1-1`, `PAY-CE1-2`.** Plafond : `couvert-valide`.

> **`PAY-CE1-1` - au moins un element qui tranche.** L'enonce nomme au moins deux elements, dont au moins un
> n'appartient qu'au paysage attendu (*plage*, *falaise* ; *sommet* ; *champ*, *ferme* ; *immeuble*, *rue*). Un element
> partage (*foret*, *riviere*, *neige*, *port*, *village*) n'est jamais le seul indice. Les quatre paysages en bonne
> reponse (`FER-1`). Seconde tache (`FER-3`) : l'element qui n'appartient pas a un paysage nomme.

> **`PAY-CE1-2` - aucune hierarchie.** Aucun paysage n'est qualifie de plus beau, plus calme, plus dur ou plus pauvre
> (`BIA-3` transposee aux lieux) ; aucun item ne demande ou vit l'enfant (`EMC-6`).

### 3.11 `QLM-CE1-11` - situer continents et oceans

**Verdict : ACCEPTEE SOUS CONDITION `COC-CE1-1`. En attente d'un dessin** (planisphere ou globe), plafond
`couvert-valide` quand il existe. Aucune moitie textuelle (`CON-1`).

> **`COC-CE1-1` - les conventions ne sont jamais la question.** Aucun item ne demande le nombre de continents ou
> d'oceans (cinq, six ou sept selon l'usage) ; la convention retenue (une ou deux Ameriques, l'Antarctique, l'ocean
> Austral) est ecrite dans le commentaire avec sa source (`QMT-CE1-2`), et aucun distracteur n'est un ocean ou un
> continent que l'autre convention reconnait. Aucun item ne situe la France (`QLM-CP-12`, `NRC-CE1-1`).

### 3.12 `QLM-CE1-12` - les generations d'une famille, les evenements d'une vie

**Verdict : ACCEPTEE SOUS CONDITIONS `GEN-CE1-1`, `GEN-CE1-2`, et `BIA-4`.** Plafond : `couvert-valide`.

> **`GEN-CE1-1` - l'echelle d'une vie.** Les items placent des generations (enfants, parents, grands-parents,
> arriere-grands-parents) ou des evenements d'une vie (naissance, entree a l'ecole, premier emploi, naissance d'un
> enfant) dans l'ordre, a l'echelle des annees. Jamais un evenement d'une journee : c'est `QLM-CP-14` (`NRC-CE1-1`).
> Les deux moities en bonne reponse (`FER-1`).

> **`GEN-CE1-2` - la famille d'un personnage, aucune norme.** La famille est celle d'un personnage nomme, decrite dans
> l'enonce. Les items portent sur qui est de la generation d'avant ou d'apres, jamais sur qui vit avec qui. Aucune
> configuration familiale n'est presentee comme la norme ou comme incomplete. Aucun item ne demande a l'enfant sa
> famille (`BIA-4`, `EMC-1`).

---

## 4. Enseignement moral et civique CE1

### 4.1 `EMC-CE1-01` - les biens de chacun et les biens de tous

**Verdict : ACCEPTEE SOUS CONDITIONS `BCO-CE1-1`, `BCO-CE1-2`.** Plafond : `couvert-valide`.

> **`BCO-CE1-1` - deux taches, ni l'une ni l'autre du CP.** Tache 1 : a qui est cet objet, a tous ou a une personne ?
> Tache 2 : quel geste protege un bien de tous (*ne pas ecrire dans un livre de la bibliotheque*, *prevenir un adulte
> quand un jeu de la cour est casse*). **Ranger et remettre a sa place ne sont jamais la bonne reponse** : c'est
> `cp-emc-regles-de-vie` (1.2, `NRC-CE1-1`). Les trois exemples de la parenthese en bonne reponse (`FER-1`).

> **`BCO-CE1-2` - les biens personnels sont ceux d'un personnage, a l'ecole.** Un bien personnel est celui d'un
> personnage nomme, a l'ecole (*la trousse de Lina*). Aucun item ne porte sur ce que l'enfant possede ou sur ce qu'il y a
> chez lui (`EMC-1`, `EMC-6`, `TRV-CE1-6`), aucun ne compare des biens. Preter n'est jamais un devoir ; prendre le bien
> d'un autre demande de lui demander.

### 4.2 `EMC-CE1-02` - la regle et la sanction

**Verdict : ACCEPTEE SOUS CONDITIONS `RSA-CE1-1`, `RSA-CE1-2`.** Plafond : `couvert-valide`.

> **`RSA-CE1-1` - a quoi sert une sanction.** Tache 1 : dans une situation, qu'est-ce qui est la regle et qu'est-ce qui
> est la sanction. Tache 2 : a quoi sert la sanction - rappeler la regle ou reparer le tort, les deux en bonne reponse
> (`FER-1`). Les sanctions proposees en bonne reponse sont des reparations (*recoller la page*, *nettoyer*, *s'excuser*,
> *refaire*). **Aucune proposition, meme en distracteur, n'est une humiliation, une punition physique, une sanction
> collective ou une privation de repas** : un distracteur se lit (`EMC-4`, motif).

> **`RSA-CE1-2` - un acte, un personnage, jamais l'enfant.** La regle est enfreinte par un personnage nomme, jamais par
> l'enfant (`EMC-1`). La sanction porte sur l'acte, jamais sur la personne : aucune proposition ne qualifie le
> personnage (*mechant*, *vilain*).

### 4.3 `EMC-CE1-03` - des droits de l'eleve et les devoirs qui les accompagnent

**Verdict : ACCEPTEE SOUS CONDITIONS `DDE-CE1-1`, `DDE-CE1-2`.** Plafond : `couvert-valide`.

> **`DDE-CE1-1` - des couples fermes, a l'ecole.** Une liste fermee de couples, ecrite dans le commentaire : apprendre et
> laisser les autres apprendre ; etre respecte et respecter les autres ; parler et ecouter quand un autre parle ; etre en
> securite et ne mettre personne en danger. L'item donne l'un et demande l'autre, dans les deux sens (`FER-3`). Aucun
> item ne reprend un droit de l'enfant de `cp-emc-droits-enfant` sans son devoir (`NRC-CE1-1`).

> **`DDE-CE1-2` - un droit ne se merite pas.** **Aucun item ne presente un droit comme gagne en faisant son devoir, ni
> comme perdu en y manquant.** Les droits de l'enfant ne dependent d'aucune conduite ; un item qui dirait le contraire
> enseignerait une erreur.

### 4.4 `EMC-CE1-04` - l'interet d'une seule personne, l'interet de tous

**Verdict : ACCEPTEE SOUS CONDITIONS `INT-CE1-1`, `INT-CE1-2`.** Plafond : `couvert-valide`.

> **`INT-CE1-1` - une seule lecture, aucun blame.** La situation n'a qu'une lecture defendable (`EMC-5`). Vouloir quelque
> chose pour soi n'est jamais presente comme mal : l'item distingue, il ne juge pas. Aucune situation ne met en balance
> un personnage et le groupe pour faire honte au personnage.

> **`INT-CE1-2` - les mots.** Les propositions disent *pour une seule personne* et *pour toute la classe* (ou *pour tout
> le monde*). *Interet general* et *bien commun* ne sont pas lus par l'enfant.

### 4.5 `EMC-CE1-05` - cooperer dans un travail de groupe

**Verdict : NON COUVRABLE PAR CE PRODUIT, confirme.** Le motif du referentiel tient : une conduite, pas une connaissance
(`CON-1`). Aucune condition.

---

## 5. `PAR-13` sur les deux matieres neuves de CE1

`PAR-13` : *"Au plus une instance par sprint prend un terrain dont mes conditions n'ont jamais rencontre de contenu
reel."* Je la juge au niveau de la matiere, par le cousinage, comme a s51, s52, s54, s55 et s56
(`ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md`, 1.1) : une condition neuve n'est pas neuve si une condition
cousine a deja mordu un paquet servi.

### 5.1 Lecture

| Terrain | Terrain neuf ? | Preuve |
|---|---|---|
| EMC CE1, `EMC-CE1-01` a `-04` | **non** | `EMC-1` a `EMC-7` ont mordu 22 lignes `couvert-valide` (11 au CP, 11 au CM1, section 0). Cousines servies : `BCO` de `cp-emc-regles-de-vie` et `cm1-emc-regles-vie-collective` ; `RSA` de `cp-emc-regle-souhait-loi` et `cm1-emc-regle-loi` (qui porte deja *sanction*) ; `DDE` de `cm1-emc-droit-devoir` ; `INT` de `cp-emc-regle-souhait-loi` (classer une situation en deux categories) |
| Questionner le monde CE1, les neuf lignes textuelles et la moitie textuelle de `-07` | **non** | `BIA-4`, `FER-1` a `-3` ont mordu 12 lignes au CP ; `LON-CE1-*` et `TRV-CE1-*` ont mordu le CE1 en francais et en mathematiques. Cousines servies : `DVP` de `cm1-sciences-etapes-developpement` ; `RAL` de `cm1-sciences-regimes-alimentaires` ; `ETE` de `cp-qlm-etats-eau` et `cm1-sciences-etats-matiere` ; `AIM` de `cp-qlm-matiere` (trier selon une propriete) et `cm1-sciences-melanges` (l'aimant, en proposition) ; `FAL` de `cp-qlm-matiere` (classer) et `cp-qlm-hygiene` ; `BPL` de `cp-qlm-hygiene` (ce dont le corps a besoin) ; `DEL` de `cp-eps-regles-de-securite` ; `PAY` de `cm1-histoire-geographie-nommer-en-geographie` ; `GEN` de `cp-qlm-autrefois-aujourdhui` et de la frise d'`HG-CM1-01` |
| `QLM-CE1-07` (moitie dessinee), `-09`, `-11` | **oui, chacune** | un circuit dessine, un plan a legende, un planisphere : trois supports qu'aucun paquet n'a servis, et la lecture d'une legende n'a jamais rencontre de contenu. `-09` a une cousine de forme (`designation-plan-de-la-classe`) ; la legende, non |

### 5.2 Ce que j'en tire

- **Une ligne a dessin au plus par sprint**, et jamais dans le sprint ou une autre instance porte un autre terrain neuf
  (`PAR-13` tel qu'ecrit). Aucune n'est engageable aujourd'hui : aucun dessin n'existe.
- **Pour s57, `PAR-13` ne change rien** : aucune ligne de ces deux matieres n'est un lot de s57 (`DEC-0120` A.33, A.35).
  s57 porte deja un terrain sans cousine : le rejeu d'`ART-CP-10` sur B (mes criteres, 4.2, condition 4).
- **Un premier lot de questionner le monde CE1** releve de ma regle de s55 sans l'amender : il ne prend pas le sprint
  ou une autre instance audite un paquet sans cousine.

### 5.3 `PAR-2`

`PAR-2` (*"une matiere et un niveau ne sont jamais partages"*) compte deja l'EMC CP et l'EMC CM1 pour une seule
matiere, parce que `EMC-1` a `EMC-7` valent pour les deux. **Elles valent aussi au CE1 : l'EMC CE1 va a l'instance qui
porte l'EMC**, jamais a une autre dans le meme sprint. **Questionner le monde CE1 compte avec questionner le monde CP**
(`BIA-4`, `NRC-CE1-1` : l'audit lit les paquets de CP voisins).

---

## 6. Prix

### 6.1 L'instruction : aucun prix mesurable, et je ne l'invente pas

**Methode** : chercher une mesure ecrite du travail d'une instruction dans le depot. `DEC-0106` section 4 l'ecrit
pour celle du francais CE1 (26 lignes, s48) : *"aucune unite de ce type n'a jamais mesure un travail d'instruction dans
ce depot"*, et celle des mathematiques CE1 (s36-s37) *"n'est pas chiffree en points non plus"*. L'instruction de s48 a
ete placee **hors chiffrage** de la borne d'audit. Je n'ai pas d'autre source.

**Ce qui se compte, et qui n'est pas un prix** : 17 lignes instruites, 33 conditions neuves, 18 prefixes neufs. Je
n'en tire aucun chiffre de capacite.

**Effet** : l'instruction est faite ici, hors sprint, **hors des 8,0 de s57** (comme mes criteres de la version, qui
ne sont pas un lot). Elle n'a pas a entrer dans la repartition de `DEC-0120` A.33.

### 6.2 Les lots, une fois instruits

| Ligne | Prix ecrit | Source | Prix mesure |
|---|---|---|---|
| `QLM-CE1-01` a `-06`, `-08`, `-10`, `-12` ; `EMC-CE1-01` a `-04` (13 lignes) | **1,0** par lot neuf textuel | mon bareme (`ARBITRAGE-PREALABLE-s28...`, 4.3), application, pas mesure | aucun |
| `QLM-CE1-07`, moitie textuelle | **1,0** | idem | aucun |
| `QLM-CE1-07` moitie dessinee, `-09`, `-11` | aucun | l'avis de faisabilite des dessins par `theo-archi` manque (`DEC-0120` A.31) | aucun |

Aucune de ces lignes n'a jamais ete auditee. **Le premier prix mesure sera celui du premier lot**, ecrit a sa cloture.

---

## 7. Ce dont je reste la seule garde

| Propriete | Qui la tient |
|---|---|
| `NRC-CE1-1` : aucun item de CE1 ne reprend un item de CP | **moi seule** ; aucun garde-fou ne compare deux paquets |
| L'exactitude des faits (`AIM`, `ETE`, `FAL`, `RAL`, `BPL`, `COC`, `DVP`) | moi, contre la source du commentaire |
| `DDE-CE1-2`, `RSA-CE1-1` (aucune sanction humiliante, meme en distracteur), `INT-CE1-1`, `GEN-CE1-2` | moi seule |
| `CON-1` sur les trois lignes a dessin | moi seule, en jouant |
| `QMT-CE1-1` : les mots exclus | encodable (liste fermee), non encode ; moi d'ici la |

---

## 8. Les identifiants

**La forme se copie** : trois segments, `PREFIXE-CE1-<n>`, celle de `TRV-CE1-6` et `LON-CE1-5`, deja allouees en section
4 du registre. Aucun marqueur de reserve : ce sont des conditions.

| Portee | Identifiants |
|---|---|
| transverse, les deux matieres | `NRC-CE1-1` |
| transverse, questionner le monde | `QMT-CE1-1`, `QMT-CE1-2` |
| `QLM-CE1-01` | `DVP-CE1-1`, `DVP-CE1-2` |
| `QLM-CE1-02` | `RAL-CE1-1`, `RAL-CE1-2` |
| `QLM-CE1-03` | `BPL-CE1-1`, `BPL-CE1-2` |
| `QLM-CE1-04` | `FAL-CE1-1`, `FAL-CE1-2` |
| `QLM-CE1-05` | `ETE-CE1-1`, `ETE-CE1-2` |
| `QLM-CE1-06` | `AIM-CE1-1`, `AIM-CE1-2` |
| `QLM-CE1-07` | `CIR-CE1-1`, `CIR-CE1-2` |
| `QLM-CE1-08` | `DEL-CE1-1`, `DEL-CE1-2` |
| `QLM-CE1-09` | `PLG-CE1-1` |
| `QLM-CE1-10` | `PAY-CE1-1`, `PAY-CE1-2` |
| `QLM-CE1-11` | `COC-CE1-1` |
| `QLM-CE1-12` | `GEN-CE1-1`, `GEN-CE1-2` |
| `EMC-CE1-01` | `BCO-CE1-1`, `BCO-CE1-2` |
| `EMC-CE1-02` | `RSA-CE1-1`, `RSA-CE1-2` |
| `EMC-CE1-03` | `DDE-CE1-1`, `DDE-CE1-2` |
| `EMC-CE1-04` | `INT-CE1-1`, `INT-CE1-2` |

Les lignes de la section 4 du registre sont dans le meme diff. **Le compte est celui que le garde-fou affiche**, recopie
dans la note datee du registre ; je n'en ecris pas un ici.

## 9. Recapitulatif des dix-sept verdicts

| Ligne | Verdict | Plafond |
|---|---|---|
| `QLM-CE1-01` | ACCEPTEE SOUS CONDITIONS `DVP-CE1-1`, `-2` | `couvert-valide` |
| `QLM-CE1-02` | ACCEPTEE SOUS CONDITIONS `RAL-CE1-1`, `-2` | `couvert-valide` |
| `QLM-CE1-03` | ACCEPTEE SOUS CONDITIONS `BPL-CE1-1`, `-2` | `couvert-valide` |
| `QLM-CE1-04` | ACCEPTEE SOUS CONDITIONS `FAL-CE1-1`, `-2`, `BIA-4` | `couvert-valide` |
| `QLM-CE1-05` | ACCEPTEE SOUS CONDITIONS `ETE-CE1-1`, `-2` | `couvert-valide` |
| `QLM-CE1-06` | ACCEPTEE SOUS CONDITIONS `AIM-CE1-1`, `-2` | `couvert-valide` |
| `QLM-CE1-07` | ACCEPTEE SOUS CONDITIONS `CIR-CE1-1`, `-2` | **`en cours`** sur la moitie textuelle ; `couvert-valide` avec un circuit dessine |
| `QLM-CE1-08` | ACCEPTEE SOUS CONDITIONS `DEL-CE1-1`, `-2` | `couvert-valide` |
| `QLM-CE1-09` | ACCEPTEE SOUS CONDITION `PLG-CE1-1`, en attente d'un dessin | `couvert-valide` avec le dessin ; rien sans |
| `QLM-CE1-10` | ACCEPTEE SOUS CONDITIONS `PAY-CE1-1`, `-2` | `couvert-valide` |
| `QLM-CE1-11` | ACCEPTEE SOUS CONDITION `COC-CE1-1`, en attente d'un dessin | `couvert-valide` avec le dessin ; rien sans |
| `QLM-CE1-12` | ACCEPTEE SOUS CONDITIONS `GEN-CE1-1`, `-2`, `BIA-4` | `couvert-valide` |
| `EMC-CE1-01` | ACCEPTEE SOUS CONDITIONS `BCO-CE1-1`, `-2` | `couvert-valide` |
| `EMC-CE1-02` | ACCEPTEE SOUS CONDITIONS `RSA-CE1-1`, `-2` | `couvert-valide` |
| `EMC-CE1-03` | ACCEPTEE SOUS CONDITIONS `DDE-CE1-1`, `-2` | `couvert-valide` |
| `EMC-CE1-04` | ACCEPTEE SOUS CONDITIONS `INT-CE1-1`, `-2` | `couvert-valide` |
| `EMC-CE1-05` | NON COUVRABLE PAR CE PRODUIT, confirme | - |

**Compte** : 16 acceptees sous conditions, dont 13 a `couvert-valide` atteignable sans dessin, 1 plafonnee `en cours`
sans dessin (`QLM-CE1-07`), 2 qui n'ont rien sans dessin (`-09`, `-11`) ; 1 non couvrable.

## 10. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | Reporter les plafonds dans la matrice (`QLM-CE1-07` : plafond `en cours` et sa mention, 3.7) ; l'effet sur le maximum de `DEC-0120` A.31 (`QLM-CE1-07` entre au maximum sous (c) en `en cours`) ; le prix des lots (6.2) pour ordonner le rang 5 d'A.35 |
| `theo-archi` | L'avis de faisabilite des trois dessins (circuit, plan a legende, planisphere), deja demande par A.31 |
| `sofia-sm` | Aucun geste ; `PAR-2` (5.3) le jour ou un lot de ces matieres est compose |

**Signe** : `nadia-prof`, hors sprint, 2026-10-13.

---

## 11. `QLM-CE1-09` : la forme degradee de `theo-archi` (Q4), jugee le 2026-10-13, 17:01 (UTC+02:00)

### 11.1 Ce qui m'est soumis, et ce que j'ai lu

- **La forme** (Q4 de l'avis de faisabilite des sept dessins, `theo-archi`) : le plan se sert en designation ; la legende
  n'a aucune place a l'ecran, parce qu'un dessin ne porte aucun texte (`ADR-0073` clause 4) et que `ZoneADesigner` n'a ni
  `texteSupport` ni `tableauSupport`. Le sens du symbole est donne dans l'enonce, l'enfant designe le symbole sur le plan.
  Pour l'itineraire, un geste par manche : seule l'arrivee se designe. La forme fidele, la legende lue dans le dessin,
  demanderait un `ADR`.
- **L'avis de `theo-archi` n'est pas dans le depot** a `9e55f87f` : `git grep -l "sept dessins"` rend `DEC-0120`, le
  backlog, la matrice et le referentiel, aucun avis. Je juge sur le resume que l'agent qui me lance m'a transmis, et j'ai
  verifie moi-meme les deux faits techniques dont il depend : la Javadoc de `ZoneADesigner` ecrit *"ni texteSupport, ni
  partDesignee"* et le record n'a aucun champ de texte support ; `ADR-0073` clause 4 interdit `<text` et `<tspan` dans
  un dessin, et ecrit que la clause *"cede"* le jour ou je demande une figure legendee.
- **La reponse de l'utilisatrice** : *"oui"* a Q4, le 2026-10-13, transmis par un agent ; je ne l'ai pas lue de sa main.
  C'est son avis sur la forme, pas un Done ni une recevabilite : celles-ci sont jugees ici.
- **Je n'ai rien joue.** Aucun plan a legende n'existe, aucun paquet, aucun item. Ce jugement est une instruction sur
  description, pas un audit.

### 11.2 Verdict : RECEVABLE SOUS CONDITION `PLG-CE1-2`, sur la moitie "situer un lieu" seule. Plafond : `en cours`

**Ce que la forme degradee sert.** Un enfant qui lit *"La croix verte, c'est la pharmacie. Montre la pharmacie."* et
appuie sur la croix verte parmi d'autres symboles a fait le geste central de la legende : passer du lieu cherche a son
symbole, puis du symbole a sa place sur le plan. Le test de `CON-1` tient : en effacant le plan, la reponse ne se trouve
plus, puisque c'est un endroit.

**Ce qu'elle ne sert pas.** La legende n'est plus une table a cote du plan, ou l'enfant cherche une entree parmi
plusieurs : l'enonce lui tend l'entree utile. C'est la moitie de la competence que l'enfant de CE1 rencontre sur une
fiche de classe, et elle manque. D'ou `en cours`, jamais `couvert-valide`, dans cette forme.

**Pourquoi l'itineraire n'entre pas.** Avec un seul geste, l'arrivee seule, un item d'itineraire n'a que deux ecritures
possibles, et je les refuse toutes les deux :

1. l'itineraire est decrit par les symboles eux-memes (*"Pars de la croix verte, passe devant le rond bleu"*) : l'enfant
   suit des formes, il ne lit plus aucune legende. L'item se resout sans elle et sert `QLM-CP-11`, pas cette ligne
   (`PLG-CE1-1`, `NRC-CE1-1`) ;
2. l'itineraire est decrit par des lieux (*"Pars de la pharmacie, passe devant la piscine"*) : chaque lieu demande son
   entree de legende dans l'enonce. Deux entrees, le depart, l'etape et la question depassent les 20 mots et les trois
   phrases de `LON-CE1-1` et `LON-CE1-2`.

Un itineraire trace sur le dessin ne vaut pas mieux : son arrivee est l'autre bout du trait, visible sans le suivre.
**Aucun item d'itineraire n'entre dans un paquet de cette ligne sous la forme degradee.**

> **`PLG-CE1-2` - la forme degradee, bornee.** Valable tant que la legende ne peut pas etre lue dans le dessin.
> 1. **L'enonce donne une ou deux entrees de legende** (*"la croix verte, c'est la pharmacie"*), puis demande un lieu.
>    Quand il en donne deux, le lieu demande n'est pas toujours le premier nomme. `LON-CE1-1` et `LON-CE1-2` valent sans
>    exception.
> 2. **Derogation a `CON-2`, limitee et ecrite** : l'enonce peut nommer la forme et la couleur d'un symbole, parce que
>    c'est le contenu d'une legende. Il ne dit jamais **sa position** (ni *en haut*, ni *a cote de*, ni *pres de*), qui
>    reste la donnee du plan.
> 3. **Chaque symbole n'apparait qu'une fois sur le plan**, et parmi les zones designables, au moins un autre symbole
>    partage avec la bonne zone sa forme ou sa couleur : l'enfant lit le symbole entier, pas un seul trait.
> 4. **Aucun texte a l'ecran ne contourne la legende.** `ZON-16` (aucun libelle de zone ne nomme un objet du dessin) et
>    `ZON-3` (le texte alternatif dit ce qu'est l'image, jamais ou est la reponse) valent sur ce plan : un libelle ou un
>    texte alternatif qui dit *"la pharmacie"* ou *"la croix verte en haut"* donne la reponse. `ZON-14` (un item, un
>    geste) et `DES-7` (rien n'est ecrit sur le dessin) valent aussi.
> 5. **Aucun item d'itineraire** (11.2, ci-dessus).

`PLG-CE1-1` n'est pas modifie : une barre que j'ai ecrite le matin ne se releve pas l'apres-midi. Les items de la forme
degradee la tiennent par sa premiere branche (*"lire un symbole de la legende pour trouver un lieu"*).

### 11.3 Ce que cela change pour `QLM-CE1-09`

| | Avant (3.9, 9) | Apres ce jugement |
|---|---|---|
| Verdict | ACCEPTEE SOUS CONDITION `PLG-CE1-1`, en attente d'un dessin | ACCEPTEE SOUS CONDITIONS `PLG-CE1-1`, `PLG-CE1-2`, **toujours en attente d'un dessin** : la forme degradee demande elle aussi un plan avec ses symboles, et il n'existe pas |
| Plafond avec le plan, forme degradee | - | **`en cours`**, mention : *"situer un lieu par un symbole dont le sens est donne dans l'enonce ; lire la legende sur le plan et suivre un itineraire ne sont pas servis"* |
| `couvert-valide` | avec le dessin | avec **les deux** : la legende lue dans le dessin (forme fidele), et l'itineraire servi sous une forme ou l'enfant lit la legende pour le suivre |

**Couverture partielle, et elle compte.** Sous la forme degradee, `QLM-CE1-09` rejoint `QLM-CE1-07` : une ligne qui
peut atteindre `en cours`, pas `couvert-valide`. Le recapitulatif de la section 9 se lit desormais : 13 lignes a
`couvert-valide` atteignable sans dessin, 2 plafonnees `en cours` (`-07` sans dessin, `-09` avec un plan en forme
degradee), 1 qui n'a rien sans dessin (`-11`), 1 non couvrable.

**`PAR-13` ne change pas** (5.1) : un plan a symboles reste un support qu'aucun paquet n'a servi, et la lecture d'une
entree de legende n'a jamais rencontre de contenu. La ligne reste un terrain neuf.

**La forme fidele, je la demande.** `ADR-0073` clause 4 ecrit qu'elle cede le jour ou je demande une figure legendee :
je la demande pour cette ligne, parce que c'est la seule voie vers `couvert-valide`. Je ne dis ni quand, ni par quel
moyen : la priorite est a `camille-po`, le moyen et l'`ADR` a `theo-archi`.

### 11.4 Ce dont je reste la seule garde sur cette ligne

| Propriete | Qui la tient |
|---|---|
| `PLG-CE1-2` points 1, 2 et 3 (entrees dans l'enonce, aucune position, symboles qui partagent un trait) | moi seule, item par item |
| `PLG-CE1-2` point 4 (libelle et texte alternatif qui ne nomment pas le lieu) | moi, en lisant les libelles et en jouant ; `git grep -ln "ZON-16\|ZON-3\b" -- '*.java'` ne rend aucun fichier |
| `CON-1` sur le plan | moi seule, en jouant, image effacee |

### 11.5 Routage

| A qui | Quoi |
|---|---|
| `camille-po` | Reporter dans la matrice le plafond `en cours` de `QLM-CE1-09` sous forme degradee, et sa mention (11.3) ; l'effet sur le maximum de `DEC-0120` A.31 ; la demande de forme fidele, a prioriser |
| `theo-archi` | L'`ADR` de la forme fidele, que la clause 4 d'`ADR-0073` attendait de ma demande (11.3) ; rien a faire pour la forme degradee, qui ne change aucun code |

Identifiant ecrit : `PLG-CE1-2`, forme copiee de `PLG-CE1-1` (trois segments, sans marqueur de reserve), allouee dans la
section 4 du registre dans le meme diff.

**Signe** : `nadia-prof`, hors sprint, 2026-10-13, 17:01.

---

## 12. Amendement du 2026-10-13, 18:32 (UTC+02:00) (`nadia-prof`) : `NRC-CE1-1` etendue aux arts plastiques CE1

Branche `s57b-nadia` a `729b0d8a`. **Rien au-dessus n'est reecrit.**

J'ecris le referentiel d'arts plastiques CE1 (`squad/programme/arts-plastiques/competences-ce1.md`,
deux lignes ; motif et methode dans `ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`, section 11.5).
Le 2026-10-13 (meme arbitrage, 10.4), j'ecrivais : *"quand je l'ecrirai, `NRC-CE1-1` vaudra pour elle"*. C'est fait :

> **`NRC-CE1-1` vaut, telle qu'ecrite en section 2, pour les paquets de CE1 d'arts plastiques**, en plus de questionner
> le monde et de l'EMC. Les paquets de CP voisins a lire a chaque audit sont `cp-decrire-une-oeuvre`, `cp-arts-couleurs`,
> `cp-arts-outils`, `cp-arts-oeuvres-familles` et `cp-rapprocher-deux-oeuvres` ; la frontiere est le tableau
> "Frontieres" du referentiel.

Meme condition, un perimetre de plus : **aucun identifiant neuf**, la ligne du registre de `NRC-CE1-1` ne change pas.
L'instruction des deux lignes d'arts plastiques CE1 n'est pas faite ici : elle est due avant le premier paquet, et celle
d'`ART-CE1-02` attend le verdict du rejeu d'`ART-CP-10`.

**Signe** : `nadia-prof`, hors sprint, 2026-10-13, 18:32.
