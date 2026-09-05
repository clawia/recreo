# Arbitrage prealable s29 - le palier 2 : ce qu'un paquet de designation doit apporter, ce qu'il coute, et ce que je refuse d'avance

- **Auteur** : `nadia-prof`. **Date** : 2026-09-05. **Sprint** : s29, story `S29-9`.
- **Branche** : `round-two` a `cc00ada`.
- **Route par** : `DEC-0074` (section 4), `ADR-0080` (section 8, quatrieme puce : *"Les conditions
  pedagogiques d'un paquet de designation [...] `nadia-prof`, par un arbitrage prealable, comme
  pour toute famille neuve"*), `DEC-0077` (section 8.6), et par moi-meme - mon arbitrage prealable
  de s29, section 7.5 : *"le palier 2 est bloque sur un arbitrage prealable de moi qui n'est pas
  ecrit, il bloque six lignes, et il ne coute rien contre ma borne"*.
- **Ce que ce document ne fait pas** : il ne juge pas la conception technique d'`ADR-0080`, qui
  n'est pas ma main ; **il ne prononce aucun Done pedagogique**, aucun paquet de designation
  n'existant ; il ne touche ni la matrice, ni le backlog, ni aucune fiche de conformite ; il ne
  priorise rien. **Aucune ligne ne bouge par cet arbitrage** : il ouvre la possibilite d'un lot, il
  n'en livre aucun.

## Le registre dans lequel ce document est ecrit, dit avant de commencer

J'ai trouve neuf defauts chez moi en trois jours et je leur ai nomme une cause commune : *"j'ecris
dans le registre du commentaire et on me lit dans le registre de la specification. `FER-1` a
transforme mes parentheses en contrats ; elles avaient ete ecrites la veille comme des exemples."*

**Ce document-ci sera lu comme une specification par un developpeur qui ecrira un paquet contre
lui.** Il est donc ecrit comme telle : des clauses numerotees, une obligation par clause, un
perimetre par clause, et un prix. **Tout ce qui est un exemple et non un contrat porte la mention
`exemple` en clair, a l'endroit meme ou il est ecrit**, et la section 11 les rassemble pour qu'on
puisse verifier la liste sans me relire en entier.

## Audit degrade, et je le declare d'entree

**Je n'ai joue aucun increment, et cette fois ce n'est pas seulement parce qu'aucun paquet
n'existe : l'ecran non plus n'existe pas.** Verification par lecture de l'arbre a `cc00ada`, ce
jour :

- le domaine porte `ZoneADesigner`, `Zone`, `CadreDeZone` et les quatre classes de
  `domain.mechanics.designation` ; `adapter-content` porte `DesignationContentSourceFactory` et
  deux des garde-fous de contenu ;
- **`adapter-web` ne porte rien** : aucune occurrence de `designation` dans ses sources, aucun
  gabarit de manche de designation, aucune route. La tache `T4` d'`ADR-0080` n'est pas livree, et
  `DEC-0074` l'avait ecrit ;
- `static/illustrations/` contient **24 fichiers**, tous des figures planes ou des pieces de
  monnaie. **Aucun plan, aucune carte, aucun planisphere, aucune frise, aucun quadrillage.**

Ce que j'ai lu, a la place : `ADR-0080` en entier, `ADR-0082` sections 2 et *"Ce que je route"*,
mon avis du 2026-09-04 (`ARBITRAGE-s28-le-palier-2-la-vue-et-les-six-lignes.md`), mes conditions
`CON-1` a `CON-4`, `QUA-1` a `QUA-5`, `REC-1` a `REC-6`, `FER-1` a `FER-3`, les sept cellules de
matrice concernees, les quatre referentiels concernes, `DES-1` a `DES-7` d'`ines-design`, et la
regle `.image-item` de la feuille de style.

---

## 1. Quatre corrections a mes propres constats, avant de m'en servir

C'est ma deuxieme habitude et elle passe avant l'arbitrage. Trois des quatre sont de moi ; la
quatrieme est un fait du depot qui a bouge depuis que je l'ai ecrit.

### 1.1 Je confirme ma correction du 2026-09-04, et j'en tire la consequence sur le compte de six

Le 2026-09-04 j'ai corrige un classement de moi : la ligne *"situer son lieu de vie a differentes
echelles"* (`HG-CM1-10`) etait derriere le palier 2 **parce que je l'y avais mise par
assimilation**, pas parce que la competence l'exige. Cette correction tient, elle est reconduite
sans un mot de changement, et **la question de fond qu'elle a ouverte reste ouverte** : la review
de s28 la porte comme *"partiellement ferme"*, et l'utilisatrice n'a pas tranche si une competence
formulee sur *son* lieu de vie peut etre servie sur *un* lieu de vie.

**Consequence, et elle evite une confusion qui aurait couru tout le sprint** : les six lignes de ce
document ne sont pas les six lignes d'`ADR-0080`. Une sort, une entre.

| | `ADR-0080`, 2026-09-03 | Ce document, 2026-09-05 |
|---|---|---|
| 1 | quadrillage, maths CP (`MAT-CP-10`) | idem |
| 2 | plan simple, QLM CP (`QLM-CP-11`) | idem |
| 3 | carte ou globe, QLM CP (`QLM-CP-12`) | idem |
| 4 | frise, QLM CP (`QLM-CP-14`) | idem |
| 5 | carte et planisphere, HG CM1 (`HG-CM1-09`) | idem |
| 6 | **echelles, HG CM1 (`HG-CM1-10`)** | **sort** - n'a jamais eu besoin du palier 2 |
| 6 | - | **entre** : *se reperer dans un musee*, histoire des arts CM1 |

**Le compte de six est conserve par substitution, pas par chance.** Aucune ligne n'est perdue :
celle des echelles redevient un candidat ordinaire des que l'utilisatrice a repondu, et je
l'instruirai comme les autres.

### 1.2 Ce que j'ai ecrit le 2026-09-04 sur la ligne du musee n'est plus vrai

**Ce que j'ai ecrit** (arbitrage de s28, section 8) : *"Une ligne hors matrice attend le meme
palier. [...] Ce referentiel n'est pas encore dans la matrice, donc la ligne n'a pas pu etre
comptee."*

**C'est faux aujourd'hui.** Verification de ce jour : `squad/programme/MATRICE-couverture.md` porte
le referentiel d'histoire des arts CM1 et sa ligne 9 - *"Se reperer dans un musee, un lieu d'art ou
un site patrimonial, et en respecter les regles"* - avec le motif `palier 2` ecrit dans la cellule.
Elle y est entree avec la matrice a 43/138 de la cloture de s28 (`camille-po`, commit `b37405d`).
**La ligne est comptee**, elle n'est pas hors matrice, et c'est elle qui prend la place de la ligne
des echelles ci-dessus.

Je ne reecris pas le texte du 2026-09-04 : un constat efface n'a aucune valeur de preuve. Cette
correction est datee d'aujourd'hui, et c'est elle qui vaut.

### 1.3 `FER-1` ne distinguait pas une enumeration conjonctive d'une enumeration disjonctive

**C'est le defaut de forme que je me connais**, pris cette fois avant qu'il coute quelque chose a
quelqu'un. `FER-1` dit : *"Chaque element enumere par le libelle de la competence, et notamment par
sa parenthese, est servi par au moins un item ou il est en position de bonne reponse."*

Applique a la lettre aux six lignes de ce document, cela exigerait qu'un paquet de plan serve **la
classe, l'ecole et le quartier**, et qu'un paquet de carte serve **une carte et un globe**. Or ces
libelles ecrivent *"ou"*, pas *"et"* : le programme y offre des supports au choix de l'enseignant,
il n'en impose pas trois. **Les lignes de s28 sur lesquelles `FER-1` a ete appliquee etaient toutes
conjonctives** - *les etres vivants, les objets et la matiere inerte* ; *les principales parties du
corps humain et les cinq sens* ; *solide, liquide* - de sorte que la question ne s'etait jamais
posee et que je ne l'avais pas vue.

> **Precision de `FER-1`, datee du 2026-09-05, et elle ne durcit rien.** `FER-1` porte sur les
> enumerations **conjonctives** du libelle. Sur une enumeration **disjonctive** - reperable au mot
> *ou* - **un seul terme suffit**, et servir les autres est un enrichissement, jamais une condition
> de fermeture. En cas de doute sur la nature de l'enumeration, c'est moi qui trancherai, ligne par
> ligne, et les six lignes de ce document sont deja tranchees en section 4.

**Cette precision baisse une barre, elle n'en releve aucune, et aucun lot livre n'a ete juge contre
la lecture stricte.** Je la fais avant le planning et non apres un refus, ce qui est le seul moment
ou une precision de cette nature est honnete.

### 1.4 Mon prix ne peut pas rester indexe sur la matiere seule, et je le corrige contre moi

**Ce que j'ai ecrit a s28** : *"Questionner le monde CP descend a 1,0. J'en suis la source - le
vivant, le corps, les objets, je les enseigne."*

**Je suis la source du vivant et du corps au CP. Je ne suis la source ni du trace d'une carte de
France, ni de la place du Massif central, ni de la limite d'un departement** - et ces trois choses
se serviraient dans questionner le monde CP, la matiere que j'ai tarifee 1,0. Le critere que j'ai
pose le 2026-09-03 est *"suis-je la source ?"* ; la matiere n'en etait qu'un raccourci commode, et
il casse sur ces lignes-ci.

> **Precision du tarif, datee du 2026-09-05.** La cle du tarif est **le contenu que le lot sert**,
> pas la matiere dans laquelle il est range. Un lot est a **1,5** des qu'une de ses lignes porte un
> fait qui se verifie contre une source exterieure - un trace de carte, un decoupage administratif,
> une date historique, une regle d'institution - meme si la matiere est par ailleurs a 1,0. **Le
> tarif d'un lot est celui de la plus chere des lignes qu'il sert.**

Elle monte un prix, et je la fais **avant le planning**, contre aucune equipe : personne ne s'est
jamais vu annoncer 1,0 sur un lot de carte.

---

## 2. Le fait qui gouverne tout le reste : rien n'est auditable avant que l'ecran existe

`ADR-0080` decrit l'ecran de designation ; il ne le livre pas, et `T4` n'est pas faite. **Un paquet
de designation ecrit aujourd'hui ne pourrait etre ni joue, ni vu, ni presse.**

Ce que cela m'interdit, precisement, et ce n'est pas une preference de methode :

- **je ne peux pas verifier ou tombe un cadre.** Quatre nombres en pourcentage ne disent a personne
  si la zone couvre le tableau ou le mur d'a cote. `ADR-0080` l'ecrit lui-meme : *"Un contenu de
  designation ne se relit pas sans jouer"* ;
- **je ne peux pas verifier qu'une zone est atteignable** - au doigt, a la tabulation, sans
  chevaucher sa voisine ;
- **je ne peux pas faire le test qui decide de toute cette famille** : regarder le dessin a la
  taille servie et chercher la cible.

> **`ZON-7` - Condition de recevabilite. Un paquet de designation n'entre pas dans mon audit tant
> que l'ecran de manche de designation n'est pas servi.** Ce n'est pas un refus pedagogique : c'est
> le constat qu'un jeu qu'on ne peut pas jouer n'est pas evaluable, et le Done technique d'`ADR-0080`
> n'est pas acquis sur cette moitie. **Un lot qui livrerait un paquet sans son ecran ne recevrait
> de moi ni VALIDE, ni VALIDE SOUS RESERVE, ni REFUSE : il recevrait "non recevable", et son prix
> ne serait pas consomme.**

Cette clause est ecrite dans l'interet de l'equipe autant que dans le mien : elle empeche qu'un lot
de contenu soit planifie dans un sprint ou l'ecran ne l'est pas, et que ma porte serve d'alibi a un
increment que personne ne peut voir.

---

## 3. `FER-1`, `FER-2`, `FER-3` sur une designation

Question posee : s'appliquent-elles telles quelles, ou l'une d'elles n'a-t-elle pas de sens sur
cette mecanique ?

**Reponse : les trois s'appliquent. Aucune ne tombe.** Deux s'appliquent sans un mot de changement,
la troisieme s'applique mais demande d'etre lue, parce que *"deux taches"* n'a pas de sens evident
quand la reponse est toujours le meme geste.

### 3.1 `FER-1` - s'applique telle quelle, avec la precision de 1.3

*"En position de bonne reponse"* se lit sans ambiguite sur une designation : **l'element est le
`bonneZone` d'au moins un item**. Une zone qui n'existe que comme zone fausse ne sert pas l'element
qu'elle porte, exactement comme un distracteur ne sert pas le mot qu'il porte. C'est la meme
clause, sur le meme critere.

`FER-1` reste **calculable** sur une designation, et pour la meme raison qu'ailleurs : chaque item
declare la ligne de referentiel qu'il sert (`ADR-0081`), et un item de designation la declare comme
les autres. Je n'ai pas a refaire d'attribution a la main.

### 3.2 `FER-2` - s'applique telle quelle, et le compte de manches la porte a onze

Dix items par ligne, tous paquets confondus. Rien ne change : un item de designation est un item.

**Deux consequences que je tire ici pour qu'on ne les decouvre pas au moment d'ecrire le YAML :**

- le garde-fou de manches (`ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`, `DEC-0064`) fait
  que **onze items sont le plancher reel** d'un paquet qui sert une ligne, et c'est ce que j'ai
  deja tranche pour s29 ;
- **`FER-2` compte des items, pas des dessins.** Un plan de classe unique peut porter onze items -
  onze questions differentes sur le meme dessin - et `FER-2` est tenue. C'est ce qui rend le palier
  2 abordable en volume de dessin, et c'est aussi ce qui rend necessaire la clause `ZON-8` : onze
  items sur un dessin de trois zones ne sont pas onze exercices, ce sont trois exercices repetes.

### 3.3 `FER-3` - la reponse a la question que personne n'avait posee

**Ce que `FER-3` ne demande pas** : que le geste change. Le geste ne change pas et n'a pas a
changer - c'est la mecanique, et une mecanique qui changerait de geste selon l'item serait une
mecanique qui lit la forme du contenu. **Ce que `FER-3` demande, et je l'ai ecrit a s28 en toutes
lettres, c'est que l'operation mentale change** : *"Reconnaitre et transformer sont deux taches ;
changer de nom dans la meme phrase n'en est qu'une."*

Sur une designation, l'operation mentale est **ce que l'enfant doit faire entre l'enonce et l'image
avant de savoir ou appuyer**. Elle a trois formes, et elles sont exhaustives sur les six lignes de
ce document :

> **Les trois taches d'une designation.**
>
> - **`T-A`, designation directe.** L'enonce nomme la cible ; l'enfant la retrouve sur l'image.
>   L'operation est une **mise en relation d'un nom et d'une representation**.
> - **`T-B`, designation relationnelle.** L'enonce ne nomme pas la cible : il la designe par sa
>   relation a un autre element de l'image - a cote de, entre, au-dessus, avant, apres, en meme
>   temps que. L'operation est une **lecture de relation**, et elle demande de trouver d'abord le
>   repere, ensuite la cible.
> - **`T-C`, designation apres transformation.** La cible n'est pas sur l'image au moment ou
>   l'enfant lit : elle se calcule par un deplacement, un parcours, un pas de plus. L'operation
>   ajoute une **transformation** a la lecture.
>
> **`FER-3` est tenue sur une ligne de designation si au moins deux de ces trois taches y sont
> servies, la moins servie portant au moins trois items** - c'est le chiffre de `FER-3`, inchange.

**Ce qui ne compte pas pour une seconde tache, dit explicitement parce que c'est la contrefacon
naturelle de cette clause :**

- **changer de cible** en gardant la meme phrase (*"Montre le tableau"*, *"Montre la porte"*) :
  c'est `T-A` onze fois ;
- **changer de dessin** en gardant la meme question : c'est `T-A` sur onze images ;
- **changer le nombre de zones** : ce n'est pas une operation mentale differente.

Cette lecture est **une transposition, pas un durcissement** : elle rend `FER-3` applicable la ou
elle serait restee muette, et la moins servie des deux taches porte le meme plancher de trois items
qu'ailleurs.

---

## 4. Ce qu'un paquet de designation doit apporter pour que je ferme, ligne par ligne

**C'est la forme que j'emploie depuis s28, et c'est ce qui a rendu s28 tenable : les developpeurs
savaient d'avance ce qui fermait.** L'etat de depart est le meme sur les six : **aucun item, aucun
dessin**.

### Vue d'ensemble

| # | Ligne | Ce que `FER-1` exige | Taches exigees par `FER-3` | Dessins minimum | Base tarifaire | Ferme entierement par une designation ? |
|---|---|---|---|---|---|---|
| 1 | `MAT-CP-10` quadrillage | *se reperer* **et** *decrire des deplacements* | `T-A` + `T-C` | **2** | 1,0 | **non** - mention exigee |
| 2 | `QLM-CP-11` plan simple | *un lieu* **ou** *un objet*, sur *classe* **ou** *ecole* **ou** *quartier* | `T-A` + `T-B` | **1** | 1,0 | **oui** |
| 3 | `QLM-CP-12` carte ou globe | *la France* **et** *quelques espaces etudies* | `T-A` + `T-B` | **1** | **1,5** | **oui** |
| 4 | `QLM-CP-14` frise | *avant* **et** *apres* **et** *en meme temps* | `T-B` + (`T-A` ou `T-C`) | **2** | 1,0 ou 1,5 | **oui**, avec la moitie textuelle |
| 5 | `HG-CM1-09` carte et planisphere | *oceans* **et** *continents* **et** *reperes physiques* **et** *grandes villes* ; *carte de France* **et** *planisphere* | `T-A` + `T-B` | **2** | **1,5** | **sous condition de taille d'image** |
| 6 | Histoire des arts CM1, se reperer dans un musee | *se reperer* **et** *en respecter les regles* | `T-A` + `T-B` | **1** | **1,5** | **non** - demande un second paquet textuel |

### 4.1 Ligne 1 - `MAT-CP-10`, *"Se reperer et decrire des deplacements sur un quadrillage simple"*

**Le point qui decide cette ligne, et il n'avait jamais ete ecrit : une designation ne peut pas
servir *decrire*.** Decrire un deplacement, c'est le produire - *"j'avance de deux cases vers la
droite"* - et l'enfant qui appuie sur une case n'a rien decrit. Le libelle est **conjonctif** : il
enumere deux gestes, et le palier 2 n'en sert qu'un.

- **`FER-1`** : la moitie *se reperer et se deplacer* est servie par la designation. **La moitie
  *decrire* ne l'est pas, et aucun item de designation ne la servira jamais.** Elle est servable
  par ailleurs - un item ou l'enfant choisit, parmi des propositions textuelles, celle qui decrit le
  deplacement dessine - mais c'est **une autre mecanique, donc un autre paquet**, et `FER-2` compte
  tous paquets confondus.
- **`FER-2`** : onze items au moins sur la ligne.
- **`FER-3`** : `T-A` - *se reperer*, designer une case decrite par sa position, sans deplacement -
  et `T-C` - designer la case d'arrivee d'un deplacement. **Trois items au moins sur la moins
  servie.** Les deux taches recouvrent exactement les deux moities du libelle, ce qui n'est pas un
  hasard : c'est la ligne qui est faite ainsi.
- **`QUA-1` a `QUA-5` sont reconduites sans un mot de changement**, et le palier 2 est precisement
  ce qui les rend enfin lisibles :
  - `QUA-1` la grille est dessinee, cases reellement carrees et de meme taille ;
  - `QUA-2` le point de depart est visible sur la grille pendant toute la manche. **Il est dans le
    dessin, jamais dans le texte alternatif, jamais dans l'enonce** - l'ecrire dans le texte serait
    `CON-2` ;
  - `QUA-3` les propositions sont des cases de la grille : tenue par construction, c'est ce que la
    mecanique fait ;
  - `QUA-4` au plus trois pas au CP, chaque pas dans une direction simple, aucune diagonale ;
  - `QUA-5` aucune couleur ne porte d'information.
- **Deux dessins au minimum**, et c'est un contrat : une grille avec un point de depart unique
  ferait apprendre une trajectoire par coeur. **Au moins deux positions de depart distinctes dans
  le paquet.**
- **Plafond de grille** : 4 x 4, et il n'est pas de moi - il tombe de l'arithmetique de la taille
  d'image (section 8.2). Il sert `QUA-4` et ne coupe pas la ligne.
- **Mention exigee d'avance, si seule la designation est servie** : **"moitie 'se reperer et se
  deplacer sur un quadrillage' seule ; la moitie 'decrire un deplacement' reste non couverte"**.
  Elle tombe le jour ou un paquet sert *decrire*.
- **Base tarifaire 1,0** : une grille ne se verifie contre aucune source, et j'en suis la source.

### 4.2 Ligne 2 - `QLM-CP-11`, *"Situer un lieu ou un objet sur un plan simple de la classe, de l'ecole ou du quartier"*

**Les deux enumerations sont disjonctives** (1.3) : un objet **ou** un lieu suffit, un plan de
classe suffit. **Un seul dessin ferme cette ligne**, et c'est la moins chere des six.

- **`FER-1`** : un support, un type de cible. Servir les trois supports est un enrichissement.
- **`FER-2`** : onze items sur le meme plan, c'est tenable et c'est la bonne forme.
- **`FER-3`** : `T-A` (*exemple, pas un contrat* : *"Montre le tableau."*) et `T-B` (*exemple* :
  *"Montre ce qui est a cote de la porte."*), trois items au moins sur la moins servie.
- **Ce que l'objet de la ligne impose au dessin, et c'est une condition, pas un conseil.** L'objet
  de cette competence est **la mise en relation de l'espace reel et de sa representation**. Un
  dessin qui montrerait la classe **en perspective**, vue depuis la porte, ne serait pas un plan :
  ce serait une image de la classe, et l'enfant y reconnaitrait des objets sans jamais lire une
  representation. **Le plan est une vue de dessus, et les objets y sont traces dans leur forme de
  plan** - une table est un rectangle vue de dessus, pas une table dessinee de trois quarts.
- **Base tarifaire 1,0** : un plan de classe est mon objet, il ne se verifie contre aucune source,
  et il n'a pas a representer une classe reelle.

### 4.3 Ligne 3 - `QLM-CP-12`, *"Situer la France et quelques espaces etudies sur une carte ou sur un globe"*

- **`FER-1`** : le support est disjonctif - **une carte suffit**. Le reste est conjonctif : *la
  France* **et** *quelques espaces etudies*. **Au moins un item ou la France est la bonne zone, et
  au moins trois items sur trois autres espaces distincts** - *quelques* est un pluriel, et trois
  est le plancher que j'emploie partout ailleurs pour un pluriel non chiffre.
- **`FER-2`** : onze items.
- **`FER-3`** : `T-A` (*exemple* : *"Montre la France."*) et `T-B` (*exemple* : *"Montre le pays qui
  touche la France au sud."*).
- **Le point dur de cette ligne, et il vient d'un garde-fou du depot, pas de moi.**
  `AucunEnonceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest` interdit qu'un enonce nomme le libelle
  de sa bonne zone. Sur une carte, la forme la plus naturelle - enonce *"Montre la France"*, zone
  libellee *"la France"* - **est donc refusee**, et elle a raison de l'etre : ce libelle est le nom
  accessible du bouton, une lecture d'ecran l'annonce, et la reponse se donne image effacee. C'est
  `ZON-1` en echec. **La forme du libelle est donc positionnelle sur cette ligne** (`ZON-10`).
- **`ZON-6` s'applique en plein ici** : un libelle positionnel rend l'ecran navigable, il ne le rend
  pas jouable sans voir. La mention `VUE` est due.
- **Base tarifaire 1,5** : un trace de carte est un fait, il se verifie contre une source, et je
  n'en suis pas la source (1.4).

### 4.4 Ligne 4 - `QLM-CP-14`, *"Situer des evenements les uns par rapport aux autres sur une frise chronologique simple (avant, apres, en meme temps)"*

- **`FER-1`** : la parenthese est conjonctive, elle enumere trois relations. **Chacune est la
  relation demandee par au moins un item.** *En meme temps* est la plus facile a oublier et la plus
  difficile a dessiner : elle suppose deux evenements a la meme place sur la frise, l'un au-dessus
  de l'autre.
- **`FER-2`** : onze items sur la ligne, **toutes moities confondues** - la moitie textuelle
  (anteriorite et posteriorite entre evenements, deja jouable au palier 1) et la moitie de placement
  comptent ensemble.
- **`FER-3`** : `T-B` est le coeur de la ligne ; la seconde tache est `T-A` (*exemple* : *"Montre le
  gouter."*) ou `T-C` (*exemple* : *"Montre ce qui vient juste apres l'ecole."*).
- **Ce que la frise doit etre au CP, et c'est une condition de contenu.** Une frise de CP porte des
  evenements que l'enfant a vecus - la journee, la semaine, les saisons - et **ses evenements sont
  dessines, jamais ecrits**. Ce n'est pas une preference : `DES-7` interdit tout element de texte
  dans un dessin servi, et une frise de CP couverte de mots serait de toute facon illisible pour un
  enfant qui lit encore peu. **Une frise de dates chiffrees n'est pas une frise de CP.**
- **Deux dessins au minimum** : une frise unique servirait onze items sur le meme ordre, et l'enfant
  retiendrait l'image plutot que la relation.
- **Mention** : la mention partielle du 2026-09-02 - *"moitie anteriorite/posteriorite entre
  evenements seule ; le placement sur une frise reste non couvert"* - **reste valable telle quelle
  tant que seule la moitie textuelle est servie**, et je ne la deplace pas. Si un paquet de
  designation sert le placement, **la ligne devient entiere et porte `VUE`**.
- **Base tarifaire 1,0** si les evenements sont ceux de la vie de l'enfant ; **1,5** si la frise
  porte des evenements historiques, qui se verifient contre une source.

### 4.5 Ligne 5 - `HG-CM1-09`, *"Se reperer sur une carte de France et sur un planisphere (oceans, continents, quelques reperes physiques, quelques grandes villes)"*

**C'est la ligne la plus chere des six, et la seule dont la fermeture ne depend pas que du
contenu.**

- **`FER-1`** : deux enumerations conjonctives. **Les deux supports** - une carte de France **et**
  un planisphere - et **les quatre categories de la parenthese**, chacune en position de bonne zone :
  oceans, continents, reperes physiques, grandes villes. **Deux dessins au minimum**, et ce n'est
  pas negociable : les oceans et les continents ne sont pas sur une carte de France, les grandes
  villes de France ne sont pas sur un planisphere.
- **`FER-2`** : onze items.
- **`FER-3`** : `T-A` et `T-B` (*exemple* : *"Montre l'ocean qui est a l'ouest de la France."*).
- **La condition qui n'est pas de contenu, et je la nomme parce qu'elle peut faire tomber la moitie
  de la ligne.** Une zone doit atteindre le plancher de cible tactile de 44 px. La hauteur rendue
  d'un dessin d'item est plafonnee a 176 px. **Une zone occupe donc au moins un quart de la hauteur
  de l'image** (section 8.2). Sur un planisphere, l'Europe n'atteint pas le quart de la hauteur du
  monde ; une grande ville, encore moins. **A la taille d'image d'aujourd'hui, la moitie planisphere
  de cette ligne ne tient pas, sauf a ne servir que les oceans et les plus grands continents.**
  Trois issues, et aucune n'est la mienne : l'image du palier 2 grandit (`ines-design`, deja route
  par `ADR-0080`) ; ou la ligne ferme partiellement, avec une mention que j'ecrirai le moment venu ;
  ou elle attend. **Je ne tranche pas une taille d'image.**
- **Base tarifaire 1,5.**

### 4.6 Ligne 6 - histoire des arts CM1, *"Se reperer dans un musee, un lieu d'art ou un site patrimonial, et en respecter les regles"*

- **`FER-1`** : le support est disjonctif - **un plan de musee suffit**. Le libelle, lui, est
  conjonctif : *se reperer* **et** *en respecter les regles*. **La moitie des regles n'est pas une
  designation** : elle se sert en choix textuel, dans un second paquet. Je le dis clairement parce
  que j'ai refuse d'avance, le 2026-09-03, le quiz *"que fait-on dans un musee ?"* **comme substitut
  du reperage** - ce refus tient sans un mot de changement, et il n'a jamais dit que ce quiz etait
  illegitime **sur la moitie qui est la sienne**.
- **`FER-2`** : onze items sur la ligne, les deux paquets confondus.
- **`FER-3`** : `T-A` (*exemple* : *"Montre ou on laisse les manteaux."*) et `T-B` (*exemple* :
  *"Montre la salle qui vient apres l'entree."*).
- **Le musee est fictif, ou il est reel et il a une source.** Un plan de musee invente est
  parfaitement legitime : la competence porte sur le reperage, pas sur la connaissance d'un
  batiment. **Mais un plan qui nomme une institution reelle devient un fait**, et il se verifie.
- **Base tarifaire 1,5**, portee par la moitie des regles : les regles d'un lieu patrimonial sont
  une donnee d'institution, elles varient d'un lieu a l'autre, et je n'en suis pas la source.

---

## 5. Le prix d'un lot de designation contre ma borne

### 5.1 Une designation n'est pas un lot illustre, et `REC-1` ne s'y applique pas

`REC-1` price un geste precis : *propositions masquees, je nomme le dessin ; si le premier nom qui
me vient n'est pas la reponse attendue, l'item est refuse*. **Ce geste n'existe pas sur une
designation** : le dessin n'est pas la reponse, il est le terrain sur lequel la reponse se trouve.
Nommer un plan de classe ne verifie rien.

**Le supplement de `REC-1` (+0,5) ne s'applique donc pas a un lot de designation, et il ne s'y
ajoute pas non plus.** Une designation est **une quatrieme famille de mon tarif**, pas une variante
de la troisieme.

### 5.2 Ce que je facture, et l'arithmetique qui le justifie

Ma borne mesure des **gestes d'audit**, pas du volume. Un lot de designation en demande **deux de
plus** qu'un lot textuel, et ils ne sont pas du meme genre :

| Geste | Ce qu'il coute | Precedent tarifaire |
|---|---|---|
| **Passe image effacee** (`ZON-1`, `ZON-2`, `ZON-3`) - tout le texte servi sous les yeux, la reponse doit rester introuvable | une demi-passe par paquet | **+0,5** - c'est le tarif de `CON-4` et de `REC-1`, deja etabli |
| **Passe jouee, zone par zone** (`ZON-9`) - je presse chaque zone de chaque item pour voir ou tombe chaque cadre | une passe complete, **et elle ne se lit pas, elle se joue** | **+0,5** - aucun precedent : c'est la premiere fois qu'un geste d'audit exige de jouer plutot que de lire |

> **Tarif d'un lot de designation : base + 1,0.**
>
> | Ce que le lot sert | Prix |
> |---|---|
> | Lignes dont je suis la source (quadrillage, plan de classe, frise de la vie de l'enfant) | **2,0** |
> | Lignes portant un fait a verifier contre une source (carte, planisphere, frise historique, musee) | **2,5** |
>
> **Unite** : jusqu'a **20 items de contenu**, plus **un item par paquet** au titre de la marge du
> garde-fou de manches - deux paquets, 22 items ; trois paquets, 23 - et jusqu'a **6 dessins**.
> Au-dela de l'un ou de l'autre, prorata.

**Pourquoi les dessins entrent dans l'unite alors que les items seuls y entraient jusqu'ici** :
parce que sur cette famille, deux de mes gestes se comptent par dessin et non par item -
l'exactitude d'un plan ou d'une carte, et l'accord des cadres avec le dessin. Onze items sur un plan
me coutent moins que onze items sur onze plans, et le tarif doit le dire, sinon il ment dans un sens
ou dans l'autre.

### 5.3 Ce que je refuse d'ajouter, et pourquoi

**Aucun supplement de premiere application.** J'ai pose la regle a s28 et elle vaut contre moi ici :
*"Une condition inedite ne fait pas monter mon prix : elle fait baisser mon plafond de fermeture."*
`ZON-7` a `ZON-14` sont huit conditions inedites, et je ne les facture pas.

**Ce qui baisse, a la place** : **je ne prends pas plus d'un lot de designation dans le sprint ou la
mecanique est servie pour la premiere fois.** Le premier lot est celui ou mes conditions rencontrent
un contenu reel, ou je decouvrirai ce que j'ai mal ecrit, et ou une correction de moi coute plus
qu'un rejeu. C'est une contrainte que je m'impose, pas un prix que je facture.

### 5.4 Ce que couterait la fermeture des six lignes, puisque la question sera posee

Un paquet de designation sert une ligne (`ADR-0081`, clause 5, que j'ai confirmee). Six lignes,
c'est donc six paquets, soit **trois lots** de deux paquets :

| Lot | Lignes | Prix |
|---|---|---|
| A | quadrillage CP + plan de classe CP | **2,0** |
| B | carte ou globe CP + frise CP | **2,5** |
| C | carte et planisphere CM1 + musee CM1 | **2,5** |
| **Total** | | **7,0** |

Plus **deux paquets textuels** hors designation, pour la moitie *decrire* du quadrillage et la
moitie *regles* du musee.

**Ce que ce chiffre dit, et je le dis sans le commenter** : ma borne est de cinq lots par sprint et
par instance d'audit. **Les six lignes du palier 2 ne tiennent pas dans un sprint** ; deux lots en
tiennent, et ils ne laissent presque rien pour le reste. **Je ne priorise pas** - je dis le prix et
je le dis avant qu'on le decouvre.

---

## 6. Ce que devient l'image

### 6.1 `REC-1` a `REC-4` - ce qui transpose et ce qui ne transpose pas

| Clause | Objet d'origine | Transpose ? |
|---|---|---|
| **`REC-1`** - propositions masquees, je nomme le dessin | l'item de vocabulaire illustre, ou le dessin **est** la reponse | **non, pas dans sa lettre.** Remplacee par `ZON-9`, qui teste la meme propriete par l'autre bout : **zones masquees, je cherche la cible sur le dessin** |
| **`REC-2`** - aucun champ lexical sans silhouette stable | le referent doit etre reconnaissable | **oui, transformee.** Sur une designation, ce qui doit etre reconnaissable n'est pas un objet isole mais **le type de representation** : un plan doit se lire comme un plan, une carte comme une carte. C'est `ZON-12` |
| **`REC-3`** - la couleur ne porte pas la reponse | l'exclusion d'un enfant daltonien de chaque item | **oui, telle quelle.** Elle rejoint `QUA-5`, `FIG-3` et `HOR-3`, que ce depot applique depuis s25, et elle s'etend a tout dessin de designation sans qu'aucune barre ancienne ne soit relevee : aucun lot de designation n'existe |
| **`REC-4`** - les nombres ne se servent pas par l'image | le denombrement d'une collection dessinee, refuse par `V2` | **non.** Son objet etait le denombrement, qu'aucune de ces six lignes ne demande. **Mais elle laisse la place a une contrainte plus dure** : `DES-7` interdit tout element de texte dans un dessin servi, donc une carte ne porte aucun nom de pays et une frise aucune date ecrite. C'est une chance sur les cartes - un nom donnerait la reponse - et une contrainte reelle sur la frise, traitee en 4.4 |

### 6.2 `ZON-3` a une moitie executable, et je la nomme

`ZON-3`, du 2026-09-04 : *"Le texte alternatif dit ce que l'image est, jamais ou est la reponse."*
Le risque est fabrique par la conception - `ADR-0080` rend `illustrationTexteAlternatif`
obligatoire - et une propriete dont je suis seule garde doit au moins etre enoncee de facon qu'un
tiers puisse me l'opposer.

> **La moitie executable, ecrite comme une propriete et non comme une forme** : *le texte alternatif
> d'un item de designation ne contient pas le libelle de sa bonne zone*. C'est exactement la
> propriete que le garde-fou 5 d'`ADR-0080` tient deja entre l'**enonce** et le libelle ; elle se
> transpose au texte alternatif **avec le meme operateur** - decoupage en mots d'`ADR-0076`, jamais
> sous-chaine, **et en reutilisant son tokeniseur**, pas un second ecrit a cote. La forme du
> garde-fou appartient a `theo-archi` ; je dis la propriete.

**Ce qu'elle attrape** : *"Plan de la classe vue de dessus, avec le tableau en haut a gauche"* quand
la bonne zone est *le tableau*. C'est la contrefacon ecrite avec les meilleures intentions du monde,
et c'est la plus probable de toutes.

**Ce qu'elle n'attrape pas, et je reste sa seule garde** : la paraphrase. *"Plan de la classe vue de
dessus ; le grand rectangle noir est en haut a gauche"* ne contient aucun mot du libelle et donne la
reponse entiere. **Aucun automate ne verra cela**, et je le verifie item par item.

---

## 7. Les huit conditions neuves, et ce que je refuse d'avance

`ZON-1` a `ZON-6` du 2026-09-04 sont **reconduites sans un mot de changement**. Les huit ci-dessous
sont **nouvelles, datees du 2026-09-05**, et elles s'appliquent au premier paquet de designation.

*Note d'allocation, parce que j'ai mesure a s29 cent quarante-sept identifiants de conditions sans
registre : le prefixe `DES-` est deja pris - `DES-1` a `DES-7` sont les conditions d'`ines-design`
sur ce qu'un dessin servi doit declarer. Je continue donc la numerotation `ZON-`, qui est la mienne
et qui va deja jusqu'a 6. Verifie par recherche sur le depot ce jour.*

> **`ZON-7` - Recevabilite.** Un paquet de designation n'entre pas dans mon audit tant que l'ecran
> de manche de designation n'est pas servi. Verdict rendu dans ce cas : **non recevable**, et le
> prix du lot n'est pas consomme. (Section 2.)
>
> **`ZON-8` - Nombre de zones.** **Plancher : trois zones par item.** Motif, et il n'est pas rond
> pour rien : le bandit manchot sert trois propositions, donc une chance sur trois au hasard ; une
> designation a deux zones serait **plus facile a deviner que la mecanique qui existe deja**, et
> aucune ligne ne doit fermer sur un jeu de pile ou face.
> **Plafond, quand les zones sont heterogenes** - un plan, une carte, une frise, un musee, ou chaque
> zone est une chose differente que l'enfant doit lire : **six au CP, douze au CM1**. Motifs :
> chaque zone est atteinte par une tabulation et annoncee par son nom, donc chaque zone de plus est
> un pas de plus pour un enfant au clavier ; et douze est ce que la plus fournie de ces six lignes
> demande - les cinq oceans et les six continents d'un planisphere - jamais un nombre rond choisi
> pour lui-meme.
> **Les zones homogenes n'ont pas ce plafond** : sur un quadrillage, l'enfant n'enumere pas les
> cases, il applique un deplacement. Elles restent bornees par le plancher de cible tactile, qui
> donne 4 x 4 aujourd'hui.
>
> **`ZON-9` - Zones masquees, la cible se trouve sur le dessin seul.** Item par item, sur la
> totalite du paquet et jamais sur un echantillon : je regarde le dessin **a la taille servie**,
> zones masquees, et je cherche ce que l'enonce demande. **Si je ne le trouve pas, aucun enfant ne
> le trouvera, et l'item est refuse.** Puis je revele les zones : **si la zone declaree bonne ne
> couvre pas l'endroit que j'ai trouve, l'item est refuse.** C'est le seul test qui attrape a la
> fois un dessin illisible et un cadre mal pose, et c'est celui qui ne peut se faire qu'en jouant.
>
> **`ZON-10` - La forme du libelle.** Le libelle d'une zone est son nom accessible ; il est annonce
> a haute voix par une lecture d'ecran. **Il ne nomme jamais la cible que l'enonce demande.** Deux
> formes sont admises, **et une seule par paquet** :
> - **nommante** - le libelle dit ce que la zone est (*exemple* : *"le tableau"*) - **admise
>   seulement si aucun enonce du paquet ne demande une zone par son nom**, donc sur un paquet
>   entierement relationnel ;
> - **positionnelle** - le libelle dit ou la zone est dans l'image (*exemple* : *"en haut a
>   gauche"*) - **obligatoire des qu'un enonce nomme sa cible**, ce qui est le cas de toute ligne de
>   carte.
>
> Melanger les deux formes dans un meme paquet est refuse : l'enfant qui navigue au clavier
> entendrait une liste dont la moitie decrit et l'autre situe, et il ne saurait plus ce qu'on lui
> annonce. **Et un libelle positionnel ne rend pas le jeu accessible** : `ZON-6` tient, sans
> changement.
>
> **`ZON-11` - L'ordre de declaration des zones est l'ordre de lecture de l'image.** `ADR-0080` m'a
> route la question - *"ce qu'est un ordre de lecture sur un planisphere"* - et voici la reponse :
> **de haut en bas, puis de gauche a droite**, sur la position des cadres. C'est l'ordre dans lequel
> l'enfant balaie une page, c'est celui dans lequel la tabulation traversera les boutons, et c'est
> le seul qui ne contredise pas un libelle positionnel. **Cette propriete est derivable d'un
> paquet** - les cadres portent leur position - et je la route comme telle a `theo-archi`, sans en
> decider la forme.
>
> **`ZON-12` - Le dessin est un schema, et il declare ce qu'il represente.** Un plan est une vue de
> dessus, avec les conventions du plan ; une carte est un trace geographique ; une frise est un axe
> oriente. **Un dessin en perspective n'est pas un plan** (4.2). Et **tout dessin qui represente une
> chose reelle - un pays, un fleuve, une ville, un evenement date, un lieu nomme - est un fait qui
> se verifie contre une source** : cette source existe, elle est ecrite dans le depot, elle est
> datee, et je la lis avant de juger le dessin (section 8).
>
> **`ZON-13` - Deux zones d'un item ne se chevauchent pas, et aucune zone ne deborde de ce que le
> dessin montre.** Deux boutons superposes rendent la designation ambigue pour l'enfant - il croit
> montrer l'un et presse l'autre - alors meme que le verdict, lui, reste parfaitement determine cote
> serveur. **Le defaut est invisible du code et visible du doigt.** Le non-chevauchement est
> **derivable** de quatre nombres par zone, et je le route a `theo-archi` ; le debordement, lui,
> demande de regarder le dessin, et il reste dans ma main.
>
> **`ZON-14` - Un item, un geste.** Une manche de designation demande **une** designation. Ni deux
> zones a designer l'une apres l'autre, ni une designation suivie d'une confirmation, ni un enonce
> qui demande de montrer *"les deux villes"*. Motif : la mecanique compte un essai par coup, et un
> enfant qui a bon au premier geste et faux au second ne comprendrait pas ce qu'on lui compte.

**Ce que je refuse d'avance, en plus** :

- **un lot qui fermerait une ligne conjonctive avec un seul dessin** quand elle en nomme deux -
  c'est la ligne 5, et le refus est ecrit avant que le lot existe ;
- **un plan ou une carte ou la couleur porte la reponse** (`REC-3`, `QUA-5`) ;
- **un item ou la zone bonne est toujours la premiere ou la derniere declaree.** Le garde-fou 4
  d'`ADR-0080` interdit deja le meme identifiant sur une majorite d'items et la plus grande zone sur
  une majorite d'items ; **le rang de declaration est la troisieme forme de la meme contrefacon**,
  et sa derivation est du meme genre. Je route l'extension a `theo-archi` ; d'ici la je la verifie a
  la main ;
- **une frise de CP portant des dates chiffrees** (4.4) ;
- **un plan nommant une institution reelle sans source** (4.6).

---

## 8. Le corpus d'images - ce dont j'ai besoin pour juger, et ce que je route

### 8.1 Ces images ne sont pas des oeuvres, et le corpus d'`ADR-0082` ne les couvre pas

`theo-archi` vient d'instruire un corpus de reproductions d'oeuvres du domaine public (`ADR-0082`) :
une troisieme population d'images, en JPEG, dans son propre repertoire, avec une preuve double et un
pivot de 1900. **Rien de cela ne s'applique ici, et il faut le dire avant que quelqu'un croie le
probleme resolu.**

| | Une reproduction d'oeuvre | Un plan, une carte, une frise, un quadrillage |
|---|---|---|
| Ce que c'est | une photographie d'un objet qui existe | **un schema**, qui ne represente rien de photographiable |
| Ce qui la rend recevable | des droits, une renonciation enregistree | **son exactitude**, et rien d'autre |
| Ce qui la rend juste | l'identification de l'oeuvre | **l'accord entre le trace et le reel** |
| Qui peut la fabriquer | personne : elle se trouve | **quelqu'un du projet la dessine** |
| Population du depot | `static/oeuvres/`, JPEG | vraisemblablement les dessins maison, SVG, `DES-1` a `DES-7` |

**Les cinq dessins dont ces six lignes ont besoin - plan de classe, plan de musee, carte de France,
planisphere, frise, quadrillage - n'existent nulle part dans le depot**, et le stock actuel est de
24 fichiers, tous des figures planes ou des pieces de monnaie.

### 8.2 L'arithmetique de taille, reprise et non recalculee

Deux valeurs epinglees dans le depot, que j'ai relues ce jour a leur source :

- **44 px**, cible tactile minimale, epinglee par `TailleDesTextesEtBoutonsTest` ;
- **176 px** (`11rem`), hauteur maximale rendue d'un dessin d'item, dans la regle `.image-item` de
  la feuille de style. La largeur, elle, n'est pas plafonnee de la meme facon : elle suit le rapport
  du fichier.

**Le plancher mord donc d'abord verticalement : une zone occupe au moins un quart de la hauteur de
l'image.** C'est ce qui donne le plafond de 4 x 4 sur un quadrillage, et c'est ce qui met en cause
la moitie planisphere de la ligne 5 (4.5). **La taille de l'image du palier 2 n'est pas de moi**,
elle est a `ines-design`, et `ADR-0080` la lui a deja routee.

### 8.3 Ce dont j'ai besoin pour juger un plan ou une carte

**Je dis la propriete ; je ne dis pas la forme.** La forme est a `theo-archi`.

1. **Une source, ecrite dans le depot, datee, pour tout dessin qui represente une chose reelle.** Un
   fleuve mal place, une region mal decoupee, une ville a cinquante kilometres de sa place sont des
   **erreurs de fait**, et une erreur de fait est un motif de refus a elle seule. Je ne peux pas
   verifier un trace contre rien. Ce que je demande n'est pas un dispositif : c'est que le dessin
   dise **d'apres quoi il a ete trace**, de la meme facon qu'`ADR-0082` fait porter au registre
   l'identification d'une oeuvre plutot qu'a un commentaire.
2. **Une declaration explicite quand le dessin ne represente rien de reel** - un plan de classe
   invente, un musee fictif. Sans elle, je ne sais pas si je dois verifier ou non, et je
   verifierais dans le vide.
3. **Le moyen de voir ou tombe chaque cadre.** C'est mon besoin le plus couteux : `ZON-9` me demande
   de presser chaque zone de chaque item, et un lot de 22 items a six zones represente plus de cent
   pressions. **Je ne demande aucun outil et je n'en prescris aucun** - si rien n'existe, je le fais
   en jouant, et c'est exactement ce que le supplement de 1,0 paie. Je signale simplement que la
   propriete *"les cadres tombent au bon endroit"* est celle dont le cout d'audit est le plus eleve
   de toute cette famille, et que si quelque chose peut la rendre visible d'un coup d'oeil, c'est la
   que ce quelque chose vaudrait le plus. **A `theo-archi` d'en juger la pertinence et le cout ; ce
   n'est pas une exigence.**
4. **La contrainte de `DES-7` traitee avant qu'on dessine, pas apres.** Aucun element de texte dans
   un dessin servi. Sur une carte, c'est une chance. Sur une frise, cela oblige a dessiner les
   evenements, ce qui est de toute facon la bonne forme au CP. **Sur un quadrillage, cela interdit
   les etiquettes de lignes et de colonnes** - et `QUA-3` les interdisait deja pour un autre motif.
   Les trois se rejoignent, et je le note pour que personne ne perde du temps a chercher comment
   contourner l'une des trois.
5. **Le rapport largeur/hauteur des dessins.** Un planisphere est large et bas ; un plan de classe
   est presque carre. `DES-2` exige que le fichier declare le rapport qu'il rend. **Je ne fixe aucun
   rapport** : je constate que c'est lui qui decide combien de zones tiennent au-dessus du plancher
   de 44 px, et que la question se pose donc **avant** qu'on dessine, pas apres.

**Ce que je route a `theo-archi`, en une phrase** : *ces cinq dessins sont-ils une quatrieme
population d'images, ou des dessins maison ordinaires soumis a `DES-1` a `DES-7` ; et ou vit la
preuve de l'exactitude d'un trace geographique ?* **Je ne tranche ni l'une ni l'autre.**

---

## 9. La mention `VUE`, et les lignes exactes qui la portent

Je reprends la mention du 2026-09-04 **sans un mot de changement**.

> **Mention `VUE`, a porter sur toute ligne qui verdit par un jeu de designation sur image :**
>
> **"couvert par un jeu de designation sur image : se joue en regardant l'ecran, ne se joue pas sans
> voir l'image. La competence, elle, n'est pas visuelle : l'ecole la sert aussi en relief, ce que ce
> produit ne sait pas faire."**

**Les deux phrases sont indissociables et je refuse qu'on ne reprenne que la premiere.** Une mention
qui dirait seulement *"competence visuelle"* serait **refusee a la porte**, au meme titre qu'un
contenu inexact : c'est une erreur de fait sur le programme, ecrite dans le document qui pilote la
couverture. Aucune de ces competences n'est visuelle par nature - l'ecole les sert en relief, en
thermogonflage, a picots - et ce qui nous manque est un canal, pas une competence.

**Ou elle se porte, exactement :**

| Ligne | `VUE` due ? | Condition |
|---|---|---|
| 1 - quadrillage CP | **oui** | des que la moitie designation verdit, en plus de la mention de couverture partielle sur *decrire* |
| 2 - plan simple CP | **oui** | sans condition |
| 3 - carte ou globe CP | **oui** | sans condition |
| 4 - frise CP | **oui, mais seulement si le placement est servi** | tant que seule la moitie textuelle est servie, la ligne ne porte que sa mention partielle du 2026-09-02, **et pas `VUE`** - elle se joue alors sans image |
| 5 - carte et planisphere CM1 | **oui** | sans condition |
| 6 - musee CM1 | **oui** | portee par la moitie *se reperer* ; la moitie *regles* se joue sans image, ce qui ne dispense de rien : la ligne entiere suppose la vue des qu'une de ses moities l'exige |
| `HG-CM1-10` echelles | **non** | elle sort du palier 2 (1.1) ; si elle est servie en texte, elle porte la mention du lieu de vie, pas `VUE` |

**Ce que je ne decide pas, et je le laisse entier** : ce que le **jeu** dit au **joueur**, et s'il
l'annonce. C'est du produit, `ADR-0073` puis `ADR-0080` l'ont route a `camille-po`, et la mention
ci-dessus ne prejuge pas de sa reponse : elle porte sur la matrice, pas sur l'ecran.

---

## 10. Ce dont je reste la seule garde

Dit pour que personne ne se croie couvert par une CI verte. `ADR-0080` livre trois garde-fous de
contenu utiles ; ils ne voient rien de ce qui suit.

1. **`ZON-1`, `ZON-2`** - la contrefacon par description, sous toutes ses formes. Le garde-fou 5 ne
   voit que le recouvrement lexical entre l'enonce et le libelle de la bonne zone.
2. **`ZON-3`, moitie paraphrase** - un texte alternatif qui situe la reponse dans d'autres mots que
   ceux du libelle (6.2). La moitie lexicale est routee et devient executable ; celle-ci ne l'est
   pas.
3. **`ZON-5`** - le geste reduit : designer, mais pas ce que la ligne demande.
4. **`ZON-9`, entierement** - l'accord des cadres avec le dessin, et la lisibilite du dessin a la
   taille servie. **Aucun automate ne verra jamais qu'une zone couvre le mur au lieu du tableau.**
   Je la trouverai en jouant, item par item, ou personne ne la trouvera.
5. **`ZON-12`** - l'exactitude de chaque plan, de chaque carte, de chaque frise, verifiee contre sa
   source.
6. **`ZON-13`, moitie debordement** - une zone qui sort de ce que le dessin montre.
7. **La verite de la mention `VUE`** sur les lignes qui verdiront.
8. **`FER-3` sur une designation** - qu'un paquet serve reellement deux operations mentales et non
   la meme onze fois. Le compte d'items est derivable ; **la nature de la tache ne l'est pas**.

**Ce qui devient executable par ce document, et que je route** : la moitie lexicale de `ZON-3`,
l'ordre de declaration (`ZON-11`), le non-chevauchement (`ZON-13`), et le rang de declaration de la
bonne zone (section 7). **Quatre proprietes qui sortent de ma main**, et c'est le meilleur usage que
je puisse faire de cet arbitrage.

---

## 11. Ce qui, dans ce document, est un exemple et non un contrat

**C'est la section que je dois a mon propre defaut.** Tout ce qui suit est un exemple, il n'oblige
personne, et un lot qui ne le reprend pas ne sera pas refuse pour cela :

- **tous les enonces cites** : *"Montre le tableau."*, *"Montre ce qui est a cote de la porte."*,
  *"Montre la France."*, *"Montre le pays qui touche la France au sud."*, *"Montre l'ocean qui est a
  l'ouest de la France."*, *"Montre le gouter."*, *"Montre ce qui vient juste apres l'ecole."*,
  *"Montre ou on laisse les manteaux."*, *"Montre la salle qui vient apres l'entree."* ;
- **tous les libelles de zone cites** : *"le tableau"*, *"en haut a gauche"* ;
- **le choix de la classe plutot que l'ecole ou le quartier** sur la ligne 2 ;
- **les objets et les lieux cites** - le tableau, la porte, le coin lecture, le vestiaire ;
- **le fait qu'une frise de CP porte la journee plutot que la semaine ou les saisons** ;
- **la composition des trois lots de la section 5.4** : c'est une arithmetique de prix, pas un
  perimetre de sprint, et je ne priorise pas.

**Et voici ce qui est un contrat, sans ambiguite** : les huit clauses `ZON-7` a `ZON-14` ; la
lecture de `FER-1`, `FER-2`, `FER-3` des sections 3 et 4 ; **tous les nombres** - trois zones au
plancher, six et douze au plafond, onze items par paquet, trois items sur la tache la moins servie,
deux dessins sur les lignes 1, 4 et 5, un quart de la hauteur d'image par zone ; les mentions de la
section 9 ; le tarif de la section 5 ; et les refus de la section 7.

---

## 12. Ce que je route, et a qui

**A `theo-archi`** :

1. **La forme des cinq dessins** : quatrieme population, ou dessins maison sous `DES-1` a `DES-7` ?
   Et **ou vit la preuve de l'exactitude d'un trace** (8.3) ? Je dis le besoin, pas la forme.
2. **Quatre proprietes derivables** que je prefere voir sortir de ma main : le texte alternatif ne
   contient pas le libelle de la bonne zone (6.2) ; l'ordre de declaration suit l'ordre de lecture
   des cadres (`ZON-11`) ; deux zones ne se chevauchent pas (`ZON-13`) ; la bonne zone n'est pas
   toujours la premiere ni la derniere declaree (section 7).
3. **Une remarque, pas une exigence** : `ZON-9` est la propriete la plus couteuse a auditer de toute
   cette famille, et elle ne s'audite qu'en jouant.

**A `camille-po`** :

1. **Le compte de six lignes a change de composition** (1.1) : la ligne des echelles sort, la ligne
   du musee entre. La matrice devra le refleter le jour ou l'une bougera.
2. **La ligne du musee est dans la matrice depuis s28** ; mon constat contraire du 2026-09-04 est
   corrige (1.2).
3. **Deux lignes ne fermeront pas par une designation seule** : le quadrillage a besoin d'un paquet
   textuel pour *decrire*, le musee d'un paquet textuel pour *les regles*.
4. **Trois lignes de la matrice, hors des six, attendent aussi le palier 2 sur une moitie**, et je
   les signale parce que le compte de six n'est toujours pas ferme : *notion de fraction simple et
   reperage sur une droite numerique* (maths CM1, `couvert-valide` avec la mention `FRA-9` - **si
   une designation sert le reperage, la mention doit tomber**) ; *identifier quelques oeuvres et les
   situer sur une frise* (histoire des arts CM1) ; *extraire une information d'un document (schema,
   graphique)* (sciences CM1). **Je ne les instruis pas ici** : elles n'etaient pas dans le
   perimetre de cette story, et je les nomme pour qu'elles ne se perdent pas.
5. **Le prix** : 2,0 ou 2,5 par lot de designation, 7,0 pour les six lignes. **Je ne priorise pas.**

**A l'utilisatrice, via `camille-po`** : la question de la ligne des echelles, ouverte depuis le
2026-09-04 et toujours sans reponse - une competence formulee sur *son* lieu de vie peut-elle etre
servie sur *un* lieu de vie ? Elle ne bloque aucune des six lignes de ce document.

**A `ines-design`, via `sofia-sm`** : la taille de l'image du palier 2 decide si la moitie
planisphere de la ligne 5 est servable (4.5, 8.2). Ce n'est pas ma decision et je ne la prends pas.

---

## 13. Ce que je conclus

**Les six lignes du palier 2 sont fermables, et je viens d'ecrire ce qui les ferme** : huit clauses
neuves, une lecture de `FER-1` a `FER-3` sur une mecanique qui ne les avait jamais rencontrees, un
tarif, et une mention. Un developpeur peut ecrire un paquet contre ce document sans me demander ce
que je voulais dire.

**Deux d'entre elles ne fermeront pas par une designation seule** - le quadrillage garde sa moitie
*decrire*, le musee sa moitie *regles* - et **une troisieme ne fermera pas du tout a la taille
d'image d'aujourd'hui**, la moitie planisphere ne tenant pas au-dessus du plancher de cible tactile.
Ces trois faits sont ecrits maintenant, pas a la review.

**Aucun Done pedagogique n'est prononce ici, et aucun ne le sera sur cette mecanique avant que j'aie
joue un ecran de designation** - image affichee, zones masquees, puis image effacee, item par item.
Aujourd'hui cet ecran n'existe pas, et c'est `ZON-7`.
