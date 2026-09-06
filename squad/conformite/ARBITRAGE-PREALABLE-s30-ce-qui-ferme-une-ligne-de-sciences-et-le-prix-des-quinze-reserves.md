# Arbitrage prealable de s30 - ce qui ferme une ligne de sciences et technologie CM1, le prix des
# quinze reserves, et le plafond a quatre lignes

**Auteur** : `nadia-prof`, professeure des ecoles, autorite sur le Done pedagogique.
**Date** : 2026-09-06.
**Statut** : premier document de la chaine de s30. Ecrit **avant** la `DEC` de strategie de
`camille-po` et **avant** le gel d'objectif de `sofia-sm`, comme a s28 et a s29.

**Ce document ne prononce aucun Done pedagogique** : aucun increment de sciences n'existe. Il pose
d'avance ce qui fermera une ligne, ce que chaque travail coute contre ma borne, et ce que je
refuse avant qu'un lot existe.

**Ce document ne priorise pas, n'ouvre aucun perimetre, n'ecrit aucune story, n'affecte personne et
ne touche pas a `squad/programme/MATRICE-couverture.md`.** Le perimetre est a `camille-po`,
l'objectif et l'affectation a `sofia-sm`, le compte de la matrice a `camille-po`.

---

## 0. Ce que j'ai joue aujourd'hui, et ce que je n'ai pas pu voir

Ma premiere regle est que je joue. Je l'ai fait, et je declare la forme exacte de ce que j'ai joue
pour qu'on ne me credite pas plus que ce que j'ai verifie.

**Joue le 2026-09-06, sur l'application qui tourne** :

- `bandit-manchot-le-bon-son-cp` - une manche complete, une reponse fausse puis la bonne ;
- `bandit-manchot-histoire-geographie-cm1` - une partie longue jouee volontairement mal, en pressant
  systematiquement la premiere proposition, jusqu'a declencher la revelation plusieurs dizaines de
  fois ;
- le catalogue des deux niveaux, et les trois etats de l'ecran de manche (question neuve, echec,
  revelation).

**Audit degrade sur un point, et je le nomme** : j'ai joue **par requetes HTTP**, donc j'ai lu le
**HTML servi**, pas la page rendue par un navigateur. Ce que cela couvre : les textes, l'ordre des
ecrans, la presence et l'absence de chaque bloc, l'enchainement apres une erreur. Ce que cela **ne**
couvre pas : la couleur, le contraste, la taille reelle, la position a l'ecran. **Rien de ce que
j'affirme plus bas ne repose sur le rendu visuel**, et je ne prononce ici aucun jugement visuel.

**Trois faits etablis en jouant, et ils changent une de mes propres conditions** - section 1.1.

---

## 1. Six corrections que je fais a mes propres constats avant de m'en servir

C'est ma deuxieme habitude et elle passe avant le reste. Cinq des six portent sur des textes que
j'ai ecrits il y a trois jours ou moins. **Aucune n'efface un verdict.**

### 1.1 `SCI-1` designe le mauvais champ - verifie a l'ecran, et la correction baisse son prix

**Ce que j'ai ecrit le 2026-09-05** (amendement de `SCI-1`, arbitrage prealable de s29, section
1.1) :

> *"Chaque item de sciences porte une `explication` non vide. Elle dit pourquoi la conception
> fausse placee en distracteur est fausse [...]"*

**Ce que le produit fait, joue et non suppose, le 2026-09-06** :

| Geste de l'enfant | Ce qui s'affiche |
|---|---|
| Question neuve, rien de presse | ni `indice` ni `explication` |
| **Premiere reponse fausse** | *"Ce n'est pas ça. Essaie encore !"* **puis l'`indice` de l'item** |
| Reponse juste | *"Bravo !"*, **ni `indice` ni `explication`** |
| Troisieme reponse fausse sur la manche (`ESSAIS_AVANT_REVELATION = 3`) | revelation, **et c'est la seule situation ou l'`explication` s'affiche sur l'ecran de manche** |
| Fin de partie | l'`explication` **du dernier item joue seulement** |

*Methode : trois etats successifs de `bandit-manchot-le-bon-son-cp` compares au balisage
(`fragments/manche.html`, le bloc `.message-jeu`) et a la branche du controleur qui le nourrit
(`PartieController.mancheModele`) - l'`explication` n'est posee au modele que sous
`session.revelee()`.*

**Consequence, et elle est decisive.** Sur un item a trois propositions, dont deux sont fausses,
**l'enfant ne peut pas atteindre la revelation par des propositions distinctes** : il n'a que deux
erreurs possibles, la troisieme proposition est la bonne. Il n'y accede qu'en pressant **deux fois
la meme proposition fausse**. **L'`explication` est donc invisible pour la quasi-totalite des
enfants qui se trompent.** Ecrire vingt explications de sciences aurait produit vingt textes que
presque personne ne lit - et j'aurais fait payer a l'equipe un champ inutilisable, sur la matiere
ou j'ai declare que le retour apres erreur etait la condition centrale.

**Ce qui atteint l'enfant qui se trompe est l'`indice`, et lui seul.** Il s'affiche des la premiere
erreur, il reste sous les yeux pendant que l'enfant choisit a nouveau, et les paquets le remplissent
deja systematiquement - onze items sur onze dans `cm1-emc-droit-devoir.yaml`.

> **`SCI-1`, precisee le 2026-09-06. Elle remplace la formulation du 2026-09-05, qui remplacait
> celle du 2026-09-03. Les trois restent lisibles ; c'est celle-ci qui vaut.**
>
> Sur **chaque item de sciences dont un distracteur porte une conception fausse courante**,
> l'`indice` dit **pourquoi cette conception est fausse**, en une phrase, au present de verite
> generale, **sans nommer la bonne reponse** (`M21`). Il ne commente jamais *"ta reponse"* : il est
> ecrit pour etre vrai quel que soit le bouton presse.
>
> L'`explication` reste **facultative**. La ou un paquet en porte une, elle tient la meme exigence
> de fond ; elle n'est vue que par l'enfant qui a revele la reponse ou qui vient de finir la partie,
> et elle n'est jamais le moyen par lequel `SCI-1` est tenue.

**Cette precision baisse deux barres et n'en releve aucune.** Elle passe de *"chaque item"* a
*"chaque item portant une conception fausse en distracteur"*, et elle passe d'un champ rempli par
**un paquet sur cinquante-quatre** a un champ que les paquets remplissent deja partout. Je la fais
**avant le planning**, contre aucune equipe, et sur un lot qui n'existe pas.

**Ce que je corrige aussi dans mon propre chiffre** : j'ai ecrit le 2026-09-05 *"sur les 48 paquets
du contenu servi, un seul porte le champ `explication`"*. Le depot en sert **54** aujourd'hui, et le
compte de porteurs est inchange : **un seul**, `cp-le-bon-son.yaml`, sur **6 de ses 15 items**.
*Methode : comptage des occurrences de la cle `explication` dans les 54 fichiers de
`adapters/adapter-content/src/main/resources/contenu`.*

**Et je corrige la premisse qui m'est transmise, parce qu'elle est juste sur le fond et fausse sur
le nom.** Il m'est ecrit que *"le champ `indice` du schema de contenu porte donc deja ce que `SCI-1`
exige"*. **Le mecanisme existe, c'est verifie, et c'est bien une contrainte de contenu et non de
code** - sur ce point la premisse est exacte et je la reprends. Mais le champ que `SCI-1` nommait
n'etait pas celui-la, et l'ecart n'est pas cosmetique : un lot ecrit contre la lettre de `SCI-1`
aurait rempli `explication` en croyant tenir la condition, et l'enfant qui se trompe n'aurait rien
lu.

### 1.2 `SCI-1` et `M21` ne peuvent pas tenir ensemble sur un item a deux propositions

**C'est le defaut de forme que je me connais** - `EMC-3` contre `FER-3` a s29 - pris cette fois
avant qu'il coute quelque chose a quelqu'un.

- **`SCI-1` (ci-dessus)** : l'indice dit pourquoi la conception fausse est fausse.
- **`M21`** : un indice qui nomme la reponse est une revelation deguisee.
- **`DRO-R2`, du 2026-09-06** : *"sur un item binaire, l'indice porte le critere et jamais la
  categorie"*, regle que j'ai etendue a tout item binaire du depot.

Sur un item a **deux** propositions, dire pourquoi l'une est fausse **nomme l'autre par
elimination**. Les deux conditions sont incompatibles, et c'est le format qui les departage.

> **`SCI-4` - nouvelle, datee du 2026-09-06.** Un item de sciences dont un distracteur porte une
> **conception fausse courante** offre **au moins trois propositions**. Ecarter la conception fausse
> laisse alors deux candidates, et l'indice fait son travail sans etre une revelation.
>
> Les items de sciences **sans** conception fausse en distracteur ne sont pas concernes : deux
> propositions y restent permises, et `DRO-R2` y suffit.

Cout pour l'equipe : nul si elle l'apprend maintenant, un refus si elle l'apprend a l'audit. C'est
la seule raison pour laquelle cette section existe.

### 1.3 `PAR-8` pose une borne totale que rien ne fait respecter, et s29 l'a mesure

**Ce que j'ai ecrit le 2026-09-05** : *"La borne passe de 5,0 a 8,0 pour s29. Le plafond individuel
reste 5,0 par instance."*

**Ce que s29 a coute** : **9,50 sur une borne de 8,0** - Instance A 4,25 / 5,0, Instance B 5,25 /
5,0 (recompte par `camille-po` sur les deux releves de verdicts, `RAPPORT.md` de s29).

**Le defaut est dans ma clause, pas dans le sprint.** Deux plafonds individuels de 5,0 somment a
**10,0**. Une equipe qui planifie contre les plafonds individuels - ce qui est la seule chose
qu'elle puisse faire, puisque c'est la seule contrainte qu'un agent voit sur son propre travail -
peut respecter les deux et depasser le total de 2,0. **Chaque instance est restee sous ou pres de
son plafond ; c'est le total qui a saute, et rien ne le tenait.** Une borne que rien ne fait
respecter n'est pas une borne, c'est un souhait - le depot sanctionne cette famille-la depuis s01.

> **`PAR-9` - nouvelle, datee du 2026-09-06.** **Le budget opposable est le budget par instance, et
> la somme des budgets par instance est egale a la borne du sprint.** A 8,0 et deux instances :
> **4,0 par instance, marge de rejeu comprise**. Le plafond de `PAR-8` (5,0) cesse d'etre le chiffre
> de planification : il reste un plafond dur qu'une instance ne franchit jamais, mais **ce n'est
> plus lui qu'on remplit**.
>
> **La borne totale ne bouge pas** : elle reste 8,0. `PAR-9` ne retire rien au sprint, elle rend
> exigible ce que `PAR-8` annoncait deja.

### 1.4 Ma condition de levee du -1,0 n'est pas remplie, et elle ne peut pas l'etre a l'ouverture de s30

La question m'est posee directement : *la decote de 1,0 a-t-elle encore lieu d'etre, maintenant que
la parallelisation a ete jouee une fois ?* **La question est legitime, et le motif que j'avais ecrit
en face de la ligne a effectivement expire** : la seconde instance n'en est plus a son premier
sprint.

**Mais j'avais ecrit la barre de levee, et c'est elle qui gouverne, pas la formulation du motif.**
Mot pour mot, le 2026-09-05 :

> *"Ce qui la ferait monter au-dela, et je le dis pour que ce ne soit pas a redemander : un second
> sprint parallele sans defaut de couture, **et** le registre d'identifiants de `PAR-5` ecrit. **Les
> deux, pas l'un des deux.**"*

**Aucune des deux n'est acquise, et la premiere ne peut pas l'etre aujourd'hui.**

1. **Un second sprint parallele sans defaut de couture.** s30 *serait* ce second sprint. La
   condition ne peut donc etre constatee qu'a sa **cloture**, jamais a son ouverture. Lever la
   decote maintenant reviendrait a payer d'avance un resultat non observe - exactement la forme de
   raisonnement que je refuse a une CI verte.
2. **Et le premier sprint parallele a eu un defaut de couture.** Review de s29, obstacle 10 :
   *"Ecart de comptage sur les lignes fermees : les deux instances annoncaient huit, il y en a neuf
   (`EMC-CP-06` omise du total de l'Instance A)"*, trouve par `camille-po` en recomptant, **par
   aucune des deux instances**. Le defaut est benin dans sa consequence - une ligne oubliee dans un
   total, en faveur du sprint - et il est **exactement du type que je redoutais** : silencieux,
   sur la couture, invisible de l'interieur de chaque moitie.
3. **Le registre de `PAR-5` n'est pas ecrit.** Review de s29, obstacle 17 : *"Ouvert, aucune story
   de s29 ne le construit, verifie seulement qu'aucune collision n'a ete creee."* Et il a grandi :
   s30 y ajoute au moins `SCI-4`, `PAR-9` et `PAR-10`, poses par ce document.

**La retenue de 1,0 reste. Ma borne pour s30 est 8,0.** Ce n'est pas de la prudence, et je ne
l'invoque pas comme une precaution : **c'est la barre que j'ai ecrite moi-meme, et je ne la deplace
pas dans le sens qui m'arrange trois jours plus tard.** Une barre qu'on abaisse des qu'elle gene
vaut aussi peu qu'une barre qu'on releve apres coup.

### 1.5 Un chiffre de moi ecrit sans sa population, a deux endroits, avec deux valeurs

Le referentiel de sciences dit : *"C'est la matiere la plus immediatement servable **des six**"*. La
matrice, qui recopie la meme phrase, dit *"**des dix**"*. **Les deux sont de moi et aucune des deux
n'ecrit sa population.**

**La population est** : les **dix referentiels** ecrits le meme jour, le 2026-09-03, dans le meme
commit (`1cf80f4`) - arts plastiques CP et CM1, education musicale CP et CM1, EPS CP et CM1, EMC CP
et CM1, histoire des arts CM1, sciences et technologie CM1. **C'est *des dix* qui est juste**, et
c'est la formulation de la matrice, celle qui m'est citee dans ma commande.

La conclusion ne bouge pas d'un mot : **19 lignes sur 20 sont jouables par une mecanique existante,
et aucune autre des dix matieres n'approche ce chiffre.** Mais un comparatif sans sa population est
precisement ce que je sanctionne ailleurs, et il valait mieux que ce soit moi qui le releve.

### 1.6 La matrice porte encore les deux libelles faux que j'ai corriges, et `FER-1` les rend contractuels

**Verifie dans le fichier aujourd'hui, ligne par ligne.**

| Ou | Ligne 7 (regimes alimentaires) | Ligne 9 (grandes fonctions) |
|---|---|---|
| `squad/programme/sciences-et-technologie/competences-cm1.md` | **corrige** : *(herbivore, carnivore, omnivore)* | **corrige** : *"les grandes fonctions de nutrition [...] (digestion, respiration, circulation)"* |
| `squad/programme/MATRICE-couverture.md`, lignes 1744 et 1746 | **non corrige** : *(végétarien, carnivore, omnivore)* | **non corrige** : *(nutrition, digestion, respiration, circulation)* |

`DEC-0077`, section 8.7, trace la correction et precise qu'elle **n'attend pas l'ouverture d'un lot
de sciences**. Elle a ete portee dans le referentiel et pas dans la matrice.

**Pourquoi cela ne peut pas attendre l'audit.** `FER-1` fait de la parenthese d'un libelle une
**liste contractuelle** : chaque element enumere doit etre servi par au moins un item **en position
de bonne reponse**. Un developpeur qui compose son lot a partir de la matrice - le document de
pilotage, celui qu'on lit pour savoir ce qui reste a couvrir - ecrira donc des items dont la bonne
reponse est *"vegetarien"* pour designer le regime d'un animal, et un item demandant *"quel est
l'organe principal de la nutrition ?"*, question a plusieurs reponses defendables que `HIS-2`
refuse. **Le lot serait faux par construction, et il serait faux en obeissant scrupuleusement au
document.**

**Route a `camille-po` via `sofia-sm`. Je ne touche pas la matrice** - son compte lui appartient,
je l'ai ecrit moi-meme. Ce que je peux faire, je le fais en section 6 : je refuse ces deux items
d'avance, par ecrit, avant qu'ils existent.

---

## 2. Ce qui ferme une ligne de sciences et technologie CM1

### 2.1 La regle de lecture d'une parenthese, appliquee aux dix-neuf lignes

`FER-1` porte sur les enumerations **conjonctives** du libelle ; sur une enumeration
**disjonctive** - reperable au mot *ou*, ou a une liste d'exemples introduite par *quelques* - **un
seul terme suffit**, et servir les autres est un enrichissement (precision du 2026-09-05). En cas de
doute, c'est moi qui tranche ligne par ligne. **Je les tranche toutes ici, d'avance**, pour que
personne n'ait a me le redemander.

**Ce que la colonne *elements* signifie** : le nombre d'elements du libelle qui doivent chacun
occuper la position de **bonne reponse** dans au moins un item. C'est le plancher de `FER-1`, et il
est independant de `FER-2`.

### 2.2 Les dix-neuf lignes, une par une

Numerotation dans l'ordre de lecture du referentiel. **Les codes `SCI-CM1-xx` n'existent pas
encore** - voir section 2.6.

| # | Ligne | Nature de la parenthese | Elements exiges par `FER-1` | Ce que `FER-3` demande (deux taches, la moins servie a 3 items) | Conditions particulieres |
|---|---|---|---|---|---|
| **1** | Etats de la matiere et changements d'etat | **conjonctive, deux sous-listes** | **7** : solide, liquide, gaz ; fusion, solidification, vaporisation, condensation | nommer l'etat d'une matiere decrite / nommer le changement qui fait passer d'un etat a l'autre | **`CYC-1`** - section 2.5. Ligne la plus large du lot : 7 elements dans 11 items laisse 4 items de jeu |
| **2** | Melange homogene / heterogene, moyen de separation | **conjonctive** ; *"identifier **un** moyen"* est au singulier | **3** : homogene, heterogene, un moyen de separation | classer un melange / choisir le moyen de separer un melange donne | Les deux mots sont ceux du programme et se definissent dans l'item |
| **3** | Mouvement : trajectoire, vitesse, point de repere | **conjonctive** | **3** : trajectoire, vitesse, point de repere | decrire une trajectoire / comparer deux mouvements par leur vitesse | **Recouvrement** : tout item qui **calcule** une vitesse est compte sur mathematiques CM1, pas ici (`ADR-0081` clause 5). Refuse en section 6 |
| **4** | Sources et formes d'energie, conversion | **conjonctive** | **3** : une source, une forme, une conversion | identifier la source ou la forme / dire en quoi une forme se convertit | Piege de fond, section 6 : *source* contre *forme* n'est pas tranchable sur tous les exemples |
| **5** | Signal et information dans les objets du quotidien | *"quelques objets"* -> parenthese **exemplative** ; le couple *signal et information* est **conjonctif** | **2** : le signal, l'information | reconnaitre le signal emis par un objet / dire quelle information il porte | Aucune condition de biais. **La ligne la moins chere du lot** |
| **6** | Classer d'apres un caractere commun, diversite d'un milieu | **conjonctive** | **2** : le classement sur un caractere, la diversite des especes d'un milieu | ecarter l'intrus sur un caractere / nommer le caractere commun a un groupe donne | **Mention de couverture partielle exigee d'avance** - section 2.4 |
| **7** | Besoins pour se nourrir, regimes alimentaires | **conjonctive** | **4** : les besoins ; herbivore, carnivore, omnivore | attribuer un regime a un animal / dire ce dont un etre vivant a besoin pour se nourrir | **Le libelle corrige seul fait foi** (section 1.6). *Vegetarien* refuse en section 6 |
| **8** | Ordonner les etapes du developpement | **conjonctive** | **5** : naissance, croissance, reproduction, vieillissement, mort | dire ce qui vient apres une etape / situer une situation decrite a son etape | Le verbe est *ordonner* : un item qui se contente de nommer une etape ne sert pas la ligne |
| **9** | Grandes fonctions de nutrition et organe principal | **conjonctive, deux sous-listes** | **6** : digestion, respiration, circulation ; leurs trois organes | nommer la fonction d'un organe / nommer l'organe d'une fonction | **Le libelle corrige seul fait foi.** Piege `HIS-2` sur l'organe de la digestion, section 6 |
| **10** | Alimentation, activite physique, sommeil, hygiene | **conjonctive** | **4** | dire ce qui est bon pour le corps / dire a quoi sert un de ces quatre | **`BIA-4`** : aucun item n'interroge l'enfant sur sa propre vie |
| **11** | Familles de materiaux, choix par une propriete | **conjonctive** | **5 au moins** : au moins deux familles nommees ; resistance, isolation, souplesse, impermeabilite | nommer la famille d'un materiau / choisir le materiau qui convient a un usage | **Une propriete par item, jamais deux** (pose le 2026-09-03) |
| **12** | Objet technique : fonction, composants, fonctionnement | **conjonctive** | **3** : la fonction, un composant, le fonctionnement | dire a quoi sert un objet / nommer la piece qui fait telle chose | Aucune condition de biais. **Attention a `OBJ-R2`**, section 2.5 |
| **13** | Evolution d'un objet technique | *"dans sa fabrication **ou** dans son usage"* -> **disjonctive** | **2** : l'evolution, **l'un** des deux (fabrication ou usage) | dire ce qui a change / dire ce qui n'a pas change (la fonction demeure) | **`BIA-3`** : aucun item ne dit que l'objet ancien etait moins bien |
| **14** | Regles d'usage d'un environnement numerique | *"quelques regles"* + parenthese **exemplative** | **2 des 4** - *quelques* est un pluriel, il en faut plus d'une ; la liste reste illustrative | reconnaitre la bonne conduite / dire pourquoi une conduite est risquee | **Mention de couverture partielle exigee d'avance** - section 2.4 |
| **15** | Terre, systeme solaire, jour, nuit, saisons | **conjonctive** | **4** : situer la Terre dans le systeme solaire ; le jour ; la nuit ; les saisons | expliquer le jour et la nuit / expliquer les saisons | **`SCI-1` mord ici plus qu'ailleurs.** La cause des saisons est **l'inclinaison de l'axe**, pas la distance au Soleil. **`SCI-4`** s'applique : trois propositions au moins |
| **16** | Phenomenes geologiques et meteorologiques, risques | *"quelques phenomenes"* -> parenthese **exemplative** ; *"geologiques **et** meteorologiques"* est **conjonctif** | **3** : un phenomene geologique, un phenomene meteorologique, un risque associe | reconnaitre le phenomene decrit / dire quel geste protege | **`BIA-2`** : un paquet qui situe tous les seismes, toutes les eruptions et toutes les inondations ailleurs enseigne une geographie du malheur |
| **17** | Milieu proche et chaine alimentaire | **conjonctive** | **2** : les etres vivants du milieu, la relation alimentaire | dire qui mange qui / dire ce qui arrive a l'un si l'autre disparait | **Construire** une chaine entiere est refuse par `V3`. Deux maillons se posent en texte |
| **18** | Effet de l'activite humaine, geste de preservation | **conjonctive** | **2** : un effet, un geste | reconnaitre l'effet d'une activite / choisir le geste qui preserve | **`SCI-2`. La ligne la plus dangereuse du lot** - section 2.4 |
| **19** | Mener une demarche d'investigation | - | - | - | **`non couvrable en l'etat`, nature irreversible.** Confirme sans changement. La decoupe qui la sauverait est refusee en section 6 |
| **20** | Extraire une information d'un document scientifique | **disjonctive** - la parenthese enumere des **formes possibles** d'un document | **1 support suffit** | trouver l'information demandee / dire ce que le document ne dit pas | **Mention de couverture partielle exigee d'avance** - section 2.4. Forme deja servie et validee deux fois |

### 2.3 `FER-2` et `FER-3` transposees, et l'arithmetique qu'elles imposent

**`FER-2` - au moins dix items servent la ligne**, tous paquets confondus. Inchangee. Barre fixee
**sous mon propre plancher mesure** (le plus petit paquet ayant ferme une ligne en porte onze), pour
qu'on ne puisse pas me reprocher de l'avoir relevee.

**`FER-3` - deux taches distinctes, la moins servie portant au moins trois items.** Elle porte sur
**l'operation mentale demandee**, jamais sur la forme de la phrase : changer le nom de l'animal dans
la meme question n'est **pas** une seconde tache. La colonne du tableau ci-dessus donne, pour chaque
ligne, un couple de taches que je tiens pour distinctes - **c'est un exemple qui ferme, pas une
prescription** : l'equipe peut en proposer un autre et je le jugerai.

**Ce que cela impose, et c'est de l'arithmetique, pas un avis** :

- `ADR-0081` clause 5 : **un item n'est compte que sur la ligne qu'il evalue**, celle ou sa bonne
  reponse se trouve. Confirmee sans changement, pour le motif de `theo-archi` qui est le mien.
- `DEC-0064` : un paquet porte **strictement plus** d'items que de manches. A `nombreDeManches: 10`,
  un paquet de fermeture porte **onze items au moins**.
- Donc **un lot de 22 items ferme deux lignes au plus**, et pas une de plus.

### 2.4 Les trois mentions de couverture partielle, exigees d'avance

Elles sont deja ecrites dans la matrice et dans le referentiel. **Je les redonne mot pour mot** pour
que le jour ou la ligne bouge, `camille-po` recopie et ne reformule pas. Le regime est celui deja
applique aux fractions (`FRA-9`).

> **Ligne 6** - *"raisonnement sur un caractere, item par item ; le tri d'une collection entiere
> reste non couvert"*.
>
> **Ligne 14** - *"regles et connaissances d'usage seules ; la pratique d'un environnement numerique
> reste non couverte"*.
>
> **Ligne 20** - *"document textuel et tableau seuls ; le schema et le graphique restent non
> couverts"*.

**Une ligne qui ferme avec une mention ferme reellement** : elle passe a `couvert-valide`, et la
mention dit ce qu'elle ne couvre pas. Ce n'est pas un demi-statut, c'est une fermeture bornee et
ecrite.

### 2.5 Ce que `SCI-1`, `SCI-2`, `SCI-3` exigent concretement d'un paquet

**`SCI-1`** (precisee en 1.1) - **sur les items a conception fausse en distracteur** :

- le champ `indice` est non vide ;
- il dit **pourquoi la conception fausse est fausse**, pas ce que l'enfant a fait ;
- il ne nomme pas la bonne reponse (`M21`) ;
- il est vrai qu'on l'ait lu apres une erreur ou pas du tout : **il ne dit jamais *"ta reponse"***
  et ne se lit pas comme un commentaire du geste ;
- l'item porte **trois propositions au moins** (`SCI-4`).

*Exemple de ce que j'attends, sur la ligne 15* : bonne reponse *l'inclinaison de l'axe de la Terre*,
distracteur *la Terre est plus près du Soleil en été* - qui est la conception fausse a peu pres
universelle et donc le bon distracteur - et indice : *"La distance de la Terre au Soleil change très
peu au cours de l'année."* Cet indice tue la conception fausse, ne nomme pas la reponse, et se lit
aussi bien par l'enfant qui a repondu juste.

**`SCI-2`** - sur les lignes 16 et 18 :

- aucun item ne fait porter une responsabilite a l'enfant **ni a son foyer** - chauffage, voiture,
  voyages, dechets, courses ;
- aucun item ne demande a l'enfant ce qu'il fait, ce que sa famille fait, ni ce qu'il faudrait
  qu'ils fassent ;
- l'item decrit **un mecanisme** et **un geste possible**, a la troisieme personne ou a
  l'impersonnel.

Meme famille que `BIA-4` : tous les enfants n'ont pas le meme foyer, et un item qui le met en cause
exclut avant d'enseigner.

**`SCI-3`** - au niveau du **lot** (portee L de la table 13.2 de s29) :

- **au plus un item sur six** demande une valeur numerique a restituer - distance d'une planete,
  temperature d'un changement d'etat, duree d'une revolution, magnitude ;
- sur un lot de 22 items, cela fait **3 items au maximum**.

C'est `HIS-1` transpose : le `bandit-manchot` produit spontanement du quiz de chiffres, et les
sciences de cycle 3 ne sont pas cela. **`SCI-3` ne se verifie pas paquet par paquet** : deux paquets
tenant chacun sa moitie peuvent violer le lot. C'est ce qui rend `PAR-1` non negociable ici.

**`HIS-3`** et **`CON-1`** s'appliquent a l'identique : chaque fait porte sa source dans le
commentaire du paquet ; et aucun item ne se repond sans lire ce qu'il faut lire.

### 2.6 `CYC-1` mord ici, et je nomme les trois recouvrements et les paquets a lire

Ma propre passe transverse de s29 a route ce point : *"`CYC-1` est vacante partout, pas tenue ; elle
mordra sur les etats de la matiere - a rejouer le jour ou un paquet de sciences CM1 est servi."*
**Ce jour est s30.**

`CYC-1` : *un item de cycle 3 n'en contredit aucun de cycle 2 deja servi sur le meme objet.
L'ajout est permis et attendu ; la contradiction ne l'est pas.*

| Ligne de sciences CM1 | Paquet de cycle 2 servi a lire | Ce qui est un **ajout permis** | Ce qui serait une **contradiction refusee** |
|---|---|---|---|
| **1** - etats de la matiere | `cp-etats-eau.yaml` (`QLM-CP-05`, fermee **au mot pres sur deux etats**, gaz et vapeur refuses d'avance par moi au CP) | **le gaz, la vaporisation, la condensation** : `FER-1` les **exige** en CM1 | dire ou laisser croire que l'eau n'a que deux etats, ou qu'un etat enseigne au CP etait faux |
| **9** - grandes fonctions et organes | `cp-corps-sens.yaml` (`QLM-CP-03`, dont la reserve `COR-R2` a corrige *"on goute avec la bouche"* en *"la langue"*) | les trois fonctions et leurs organes, absents du CP | reintroduire *la bouche* comme organe du gout, ou nommer un organe autrement qu'au CP |
| **6** et **17** - classer le vivant, chaine alimentaire | `cp-qlm-vivant-non-vivant.yaml` (`QLM-CP-01`, dont trois items de matiere inerte ont deja ete refuses une fois pour une classification fausse) | le caractere observable, la relation alimentaire | ranger comme *vivant* ou *non vivant* quelque chose que le paquet CP range dans l'autre sens |

**Je nomme explicitement le piege de la ligne 1, parce qu'il se retourne dans les deux sens.** J'ai
refuse d'avance, au CP, *"tout item qui introduirait l'etat gazeux, la vapeur ou l'evaporation"*.
**Ce refus vaut au CP et nulle part ailleurs.** En CM1, le gaz est dans le libelle, `FER-1` le rend
obligatoire, et un lot qui l'eviterait par prudence echouerait a fermer la ligne. **Progression,
pas contradiction** - c'est exactement le cas que `CYC-1` a ete ecrite pour trancher.

**Cout de `CYC-1` : nul en supplement.** C'est une obligation de **lecture** de trois fichiers, et
elle tient dans le prix du lot. Le depot est la memoire partagee ; c'est ce qui la rend tenable a
deux instances.

### 2.7 Les codes de ligne n'existent pas, et sans eux `FER-1` et `FER-2` ne sont pas verifiables

`ADR-0081` a alloue **84 codes dans 7 referentiels**, et exclut explicitement les onze qu'aucun
paquet servi ne touche - dont **sciences et technologie CM1** - au motif qu'*"un code y serait vert
sur une population vide"*, et que le jour ou un paquet voudra en citer un, *"il n'y aura aucun code
a citer, donc un rouge bruyant et immediat"*. **Ce jour est s30, et le rouge est attendu.**

L'allocation `SCI-CM1-01` a `SCI-CM1-20` se fait **dans le meme geste que le lot**, comme
`lea-dev` l'a fait pour l'EMC a s29. Sans elle, `lignesDeReferentiel` n'a rien a declarer, `FER-2`
n'est plus derivable, et `FER-1` redevient une attribution que je fais a la main - c'est-a-dire
verifiable par moi seule, ce qu'`ADR-0081` existe pour supprimer.

**Je dis la propriete, je ne prescris ni la forme ni le moment** : c'est a `theo-archi` et a
`camille-po`. Ce qui m'appartient est la consequence : **je ne prononcerai pas de Done pedagogique
sur une ligne de sciences dont le code n'est pas alloue et declare par le paquet qui la sert** - ce
n'est pas une barre neuve, c'est `ADR-0081` applique.

### 2.8 Les lignes que je juge les plus surement fermables - jugement de faisabilite, pas de priorite

**Je ne priorise pas.** Ce que je peux dire, et que j'ai dit a s28 comme a s29, c'est ou une
fermeture est chere et ou elle est sure. Le classement suit la charge de `FER-1` et le nombre de
conditions qui pesent sur la ligne.

| Rang | Lignes | Motif |
|---|---|---|
| **Les quatre plus sures** | **5** (signal et information), **17** (chaine alimentaire), **2** (melanges), **12** (objet technique) | 2, 2, 3 et 3 elements ; **aucune condition de biais** ; aucun fait qui demande une source exterieure ; distracteurs naturels |
| Les trois suivantes | **1** (etats de la matiere), **7** (regimes), **11** (materiaux) | Faits certains, mais 7, 4 et 5 elements. La ligne 1 est *"le terrain le plus sur"* sur l'exactitude et **la plus large** sur `FER-1` : 7 elements dans 11 items ne laisse que 4 items de manoeuvre |
| A ne pas prendre au premier lot | **18** (impact humain), **16** (risques), **15** (Terre et saisons), **9** (fonctions et organes) | `SCI-2`, `BIA-2`, `SCI-1` a son point le plus exigeant, et le piege `HIS-2` de l'organe de la digestion. **Aucune de ces conditions n'a jamais rencontre de contenu reel** |

**Une composition que je tiens pour tenable, si elle est demandee** : lot 1 = lignes **5 et 17**,
lot 2 = lignes **2 et 12**. Quatre lignes, 44 items, aucune condition de biais, `SCI-1` mobilisee
sur peu d'items. **Le choix appartient a `camille-po`.**

---

## 3. Le prix contre ma borne, avec l'arithmetique

### 3.1 Le tarif d'un lot de sciences reste 1,5, et je refuse de le decouper

Le tarif etait deja ecrit, par moi, le 2026-09-03 dans le referentiel et confirme le 2026-09-05 :
*"Je ne suis pas la source en sciences comme je le suis en calcul : l'exactitude de chaque fait se
verifie contre une source, et c'est le cout principal de cette matiere pour moi."* -> **1,5 par
lot**.

**On pourrait me demander de faire pour les sciences ce que j'ai fait pour l'EMC CP - un tarif mixte
qui rendrait certaines lignes moins cheres, et donc un levier de 0,5 a `camille-po`. Je refuse, et
le motif est une mesure, pas une preference.**

`COR-R2` : le produit a dit a des enfants de CP, pendant deux sprints, **que l'on goute avec la
bouche**. L'organe du gout est la langue. Cette erreur est sur `QLM-CP-03`, c'est-a-dire sur une
ligne de la matiere que j'ai tarifee **1,0** au motif que *"le vivant, le corps, les objets, je les
enseigne"* - donc sur la ligne meme ou je me suis declaree la source. **Ma confiance en moi comme
source sur le corps humain est exactement ce qui a laisse passer cette erreur.** Decouper le tarif
des sciences pour rendre moins cheres les lignes du vivant et du corps reviendrait a rejouer ce
mecanisme sur la matiere ou les faits sont plus nombreux et plus techniques.

**1,5 partout sur sciences et technologie CM1, sans exception de ligne.** Cela coute a l'equipe,
c'est derive et non decrete, et je l'ecris avant de connaitre la composition du sprint.

### 3.2 Le prix des quinze reserves - 3,75, et la regle de groupement qui evite 7,50

**Sans regle de groupement, quinze reserves au regime du rejeu borne couteraient 15 x 0,5 = 7,50,
soit la quasi-totalite de la borne du sprint.** Ce chiffre serait absurde, et il serait faux : je ne
relis pas quinze fois un paquet parce qu'il porte trois reserves.

> **`PAR-10` - nouvelle, datee du 2026-09-06.** **Un rejeu borne se paie par paquet rejoue, pas par
> reserve.** Plusieurs reserves portant sur le meme paquet forment **un** rejeu, au prix du regime
> le plus cher qu'elles appellent. Un rejeu borne dont la propriete a tenir est **nommee par ecrit
> dans ma reserve** est transferable a une instance qui n'a pas l'accumulation de ce paquet : **le
> texte de la reserve porte l'image**, c'est exactement ce qu'il est fait pour porter.

**Bareme reconduit sans changement** (s29, section 8.1) : correction reprise **mot pour mot** d'un
texte que j'ai fourni, puis reverification = **0,25** ; **rejeu borne** a des proprietes nommees,
l'equipe reecrit et je rejuge = **0,5**.

| Bloc | Reserves | Matiere et niveau | Regime | Prix |
|---|---|---|---|---|
| **A** - verbatim disperse | `CYV-R1`, `VIV-R2` | questionner le monde CP | mot pour mot, un passage pour les deux | **0,25** |
| **B** - `objet-technique-cp` | `OBJ-R1` (verbatim), `OBJ-R2` (rejeu) | questionner le monde CP | rejeu borne ; le verbatim voyage avec | **0,5** |
| **C** - `histoire-geographie-cm1` | `HG-R6` (rejeu), `HG-R7` (verbatim, **erreur de fait**) | histoire-geographie CM1 | rejeu borne ; le verbatim voyage avec | **0,5** |
| **D** - `division-posee-cm1` | `POS-R2` | mathematiques CM1 | rejeu borne, **serie de onze items** | **0,5** |
| **E** - `fait-opinion-cm1` | `FOP-R1`, `FOP-R2`, `FOP-R3` | EMC CM1 | rejeu borne, `FOP-R2` porte sur dix distracteurs | **0,5** |
| **F** - `droit-devoir-cm1` | `DRO-R1`, `DRO-R2`, `DRO-R3` | EMC CM1 | rejeu borne ; `DRO-R3` est a cout nul et voyage avec | **0,5** |
| **G** - `regles-de-vie-cp` | `REG-R1` | EMC CP | rejeu borne | **0,5** |
| **H** - `permis-interdit-cp` | `PER-R1` | EMC CP | rejeu borne | **0,5** |
| | | | **Total** | **3,75** |

**Je n'abaisse aucun des prix que j'ai deja ecrits.** Le backlog porte *"regime : rejeu borne
(0,5)"* pour `OBJ-R2`, `HG-R6` et `POS-R2`, sur la foi de mes propres fiches. Les blocs B, C et D
restent donc a 0,5 chacun **meme quand la reserve ne touche que deux items** - j'ai sous-estime
`POS-R1` une fois, section 1.4 de s29 l'a montre, et je ne recommence pas dans le sens qui
m'arrange.

**Le fait que l'utilisatrice demande a voir chiffre honnetement, et le voici sans adoucissement** :

> **Les quinze reserves valent 3,75 de ma borne et ne ferment pas une seule ligne neuve.** Les huit
> paquets concernes sont **tous** sur des lignes deja `couvert-valide`. Aucune de ces quinze
> corrections ne fait bouger un statut de la matrice - le backlog l'ecrit lui-meme pour chacune.

### 3.3 `CONS-1`, `REV-3`, la passe transverse

| Travail | Objet | Prix | Ferme une ligne ? |
|---|---|---|---|
| **`CONS-1`** | dix consignes reecrites, chacune rejouee **contre le paquet qu'elle introduit** - une consigne ne se juge pas seule | **0,5** | **non** |
| **`REV-3`** | rejeu du correctif sur la population deja couverte par ma reverification de `REV-1` : six paquets, CP et CM1, cinq formes de bonne reponse | **0,25** | **non** |
| **Passe transverse (`PAR-4`)** | proprietes de portee **depot**, objet nomme en 3.4 | **0,5** | **non** |
| | | **1,25** | |

**Le prix de `REV-3` etait laisse a chiffrer par moi au moment du rejeu ; je le chiffre maintenant,
et je nomme la condition qui le double.** 0,25 couvre le rejeu de la **meme population** que
`REV-1`, par la **meme methode**. **Si le correctif de `theo-archi` fait apparaitre une liste de
plusieurs reponses revelees en fin de partie** - ce que le second critere d'acceptation autorise et
que l'ecran ne fait pas aujourd'hui, puisqu'il ne nomme que la reponse du **dernier** item - **alors
ce n'est plus un rejeu mais un ecran neuf, et le prix est 0,5.** Dit d'avance pour que le
depassement, s'il arrive, soit un choix et non une surprise.

**L'objet de la passe transverse de s30, nomme d'avance pour qu'elle ne soit pas une repetition de
celle de s29.** Ma passe de s29 a ecrit ses bords elle-meme : *"`REV-1` est le seul defaut d'ecran
que cette passe a cherche ; il en existe peut-etre d'autres du meme genre, produits par la rencontre
d'un champ de contenu et d'un gabarit, et je ne les ai pas cherches."* C'est cela, l'objet de s30 :

1. **les autres endroits ou un gabarit compose une phrase autour d'un champ de contenu** - la
   famille de `REV-1`, que j'ai ajoutee moi-meme a la portee **D** ;
2. **l'homogeneite de registre au-dela de la consigne** - titres, messages de reussite, ecrans de
   fin - explicitement hors du perimetre de `CONS-1`, et ou un titre m'avait deja saute aux yeux en
   passant (`OBJ-R1`) ;
3. **les collisions d'identifiants mintes par s30**, faute de registre `PAR-5`.

### 3.4 Ce que cela laisse pour les lignes de sciences

| Poste | Prix | Lignes fermees |
|---|---|---|
| Quinze reserves (blocs A a H) | **3,75** | **0** |
| `CONS-1` | **0,50** | **0** |
| `REV-3` | **0,25** | **0** |
| Passe transverse (`PAR-4`) | **0,50** | **0** |
| **Sous-total : ce qui ne ferme aucune ligne** | **5,00** | **0** |
| **Borne du sprint** | **8,00** | |
| **Reste pour les lignes de sciences** | **3,00** | |

**Trois lots de reponse a la question posee :**

1. **Les quinze reserves plus `CONS-1` consomment 4,25 de ma borne sans fermer une ligne.** Avec
   `REV-3` et la passe transverse, qui n'en ferment pas davantage, le total des travaux sterils en
   couverture est de **5,00 sur 8,00 - soit 62,5 % de ma borne**.
2. **Il reste 3,00, c'est-a-dire exactement deux lots de sciences a 1,5, avec zero marge de
   rejeu.**
3. **Zero marge est la composition que j'ai declinee par ecrit a s29** (composition 3, section 8.2)
   et c'est la situation qui a fait partir s28 en depassement. **Je la decline a nouveau ici**, ce
   qui veut dire que 3,00 de lots **ne tiennent pas** : voir la composition de la section 5.

### 3.5 Le plafond arithmetique de lignes fermables

**Trois appuis, tous verifies, et le calcul n'a aucun degre de liberte.**

- `FER-2` : dix items au moins par ligne.
- `ADR-0081` clause 5 : un item n'est compte que sur **une** ligne.
- `DEC-0064` : onze items au moins dans un paquet de fermeture a dix manches.
- Prix : **1,5 par lot**, un lot valant jusqu'a **22 items** quand il est compose de deux paquets
  fermant chacun une ligne (precision du 2026-09-05).

Donc : **un lot ferme 2 lignes au plus**, et **1,5 de ma borne achete 2 lignes**, soit **0,75 par
ligne**.

> **Plafond arithmetique de s30 : 4 lignes.**
>
> 3,00 disponibles / 1,5 = **2 lots** ; 2 lots x 2 lignes = **4 lignes**.
> **52 + 4 = 56 / 138 = 40,6 %.**
>
> Et ce plafond suppose **zero marge de rejeu**, ce que je decline. **Avec la marge que je tiens
> pour necessaire (1,0), le plafond descend a la composition de la section 5.**

**Mon attente raisonnable : 3 a 4 lignes, soit 55 a 56 / 138, c'est-a-dire 39,9 % a 40,6 %.**

**Et je corrige d'avance la lecture de mon attente, parce que celle de s29 s'est trompee dans un
sens qu'il faut dire.** A s29 j'avais ecrit *"mon attente raisonnable : 7 a 8"* sur un plafond de 9,
et **le resultat a ete 9** : j'ai sous-estime l'equipe d'une a deux lignes. Cela ne me fait pas
remonter mon attente ici, et voici pourquoi : a s29, **une** matiere du perimetre portait des
conditions inedites (l'EMC, neuf conditions) ; a s30, la seule matiere du perimetre en porte
**quatre qui n'ont jamais rencontre de contenu reel** - `SCI-1` dans sa forme d'aujourd'hui,
`SCI-2`, `SCI-3` et `SCI-4` - **plus `CYC-1`, declaree vacante partout par ma propre passe
transverse.** Une condition inedite ne fait pas monter mon prix, elle fait baisser mon plafond :
c'est la regle que j'applique depuis s28 et elle joue ici a plein.

---

## 4. La borne du sprint sous deux instances, et le plafond individuel

**Derivation, reprise de la table du 2026-09-05 et mise a jour poste par poste.**

| Poste | Effet | Etat au 2026-09-06 |
|---|---|---|
| Capacite brute : 2 x 5,0 | **10,0** | inchange |
| La passe transverse est une tache payee et non un sous-produit gratuit (`PAR-0`, `PAR-4`) | **-0,5** | **confirme par la mesure** : la passe de s29 a coute exactement 0,5 et a trouve `REV-1` et `CONS-1`, que rien d'autre n'aurait trouves |
| Les dettes de portee **D** deviennent payables et doivent l'etre | **-0,5** | **confirme** : s30 en paie 5,00 |
| **La retenue de defiance sur la parallelisation** | **-1,0** | **maintenue**, section 1.4 : la barre de levee que j'ai ecrite exige deux conditions, aucune n'est acquise, et la premiere ne peut pas l'etre a l'ouverture |
| **Borne totale de s30** | **8,0** | |

> **La borne de s30 est 8,0.**
>
> **Budget opposable par instance : 4,0, marge de rejeu comprise** (`PAR-9`, section 1.3). Le
> plafond dur de 5,0 par instance de `PAR-8` demeure, mais **ce n'est plus le chiffre de
> planification** : la somme des budgets par instance est desormais egale a la borne.
>
> **Au plus trois lots par instance** (`PAR-8`, reconduit).
>
> **Marge de rejeu : 0,5 par instance, nommee a l'ouverture, allouee a aucun lot.** Soit **1,0 de
> marge pour le sprint**, ce qui est ce que s29 avait nomme.

**Le motif de la marge, chiffre et non estime.** A s29, **quatre paquets sur cinq evalues au premier
passage ont ete refuses**, tous corriges et revalides dans le sprint. Si s30 connait le meme taux
sur deux lots de sciences, cela fait **deux reaudits** : 0,25 chacun s'ils sont verbatim, 0,5
chacun s'ils sont des rejeux bornes. **La marge de 1,0 couvre exactement ce cas et rien de plus.**
Elle n'est pas arrondie ; elle est dimensionnee sur ce que le sprint precedent a reellement coute.

**Ce que je ne recommande toujours pas** : abaisser ma borne pour se menager de la marge. *Une borne
avec de la marge cesse d'etre une borne et le sprint suivant gonflerait pour la remplir.* La borne
reste 8,0 ; ce que je propose est de n'en **planifier que 7,0** et de nommer les 1,0 restants
d'avance. Une reserve annoncee n'est pas une borne elargie.

---

## 5. La repartition entre les deux instances

### 5.1 Ce que mes propres clauses imposent, avant toute preference

- **`PAR-1`** - un lot n'est jamais partage. Les deux lots de sciences ne se coupent pas en deux.
- **`PAR-2`** - une matiere et un niveau ne sont jamais partages. **Consequence directe et non
  negociable : les deux lots de sciences vont a la meme instance.** `SCI-3` se mesure sur le lot de
  matiere ; deux instances tenant chacune sa moitie la rendraient **verte a tort**, et c'est
  exactement le mode de defaillance silencieux que `PAR-1` et `PAR-2` existent pour tuer.
- **`PAR-7`** - une instance porte l'accumulation, l'autre non. L'instance d'accumulation prend les
  passes transverses, les dettes, les lignes `en cours` et les matieres deja auditees ; l'autre
  prend le terrain ou **personne** n'a d'accumulation. **Sciences et technologie CM1 n'a jamais ete
  servie par un seul item : c'est, dans tout le depot, le terrain ou l'accumulation de personne ne
  manque.**
- **`PAR-3`** - l'instance qui audite une ligne `en cours` lit les items deja servis avant de juger
  `FER-1`. Le bloc **C** touche l'histoire-geographie CM1, qui porte des lignes `en cours` : il va a
  l'instance d'accumulation.

> **Precision de `PAR-2`, datee du 2026-09-06, et elle ne durcit rien.** `PAR-2` gouverne
> l'attribution des **lots de contenu**. Elle ne s'applique pas aux travaux de **portee D**, dont
> l'objet est le depot entier par definition - la passe transverse, `CONS-1` (dix jeux de cinq
> matieres), `REV-3` (six paquets, deux niveaux). Ces travaux vont **tous a l'instance
> d'accumulation**, ce qui est la seule affectation compatible avec `PAR-7`, et leur traversee des
> matieres n'est pas un partage.

### 5.2 La composition qui tient - chiffree, verifiee contre chaque clause

**Rappel de ma propre clause de s29, que je m'applique ici** : ma borne protege un jugement qui ne
se divise pas, pas un debit de lecture. La repartition ci-dessous n'est donc pas un partage de
charge : c'est un decoupage par **objet de jugement**.

| | **Instance A** - porte l'accumulation (`PAR-7`) | **Instance B** - le terrain sans accumulation |
|---|---|---|
| **Lots de contenu** | aucun | **sciences et technologie CM1, lot 1 : 1,5** ; **lot 2 : 1,5** |
| **Reprises** | bloc **A** verbatim 0,25 ; bloc **B** `objet-technique` 0,5 ; bloc **C** `histoire-geographie` 0,5 ; bloc **E** `fait-opinion` 0,5 ; bloc **F** `droit-devoir` 0,5 | bloc **D** `division-posee` (`POS-R2`) **0,5** |
| **Portee depot** | passe transverse 0,5 ; `CONS-1` 0,5 ; `REV-3` 0,25 | - |
| **Planifie** | **3,50** | **3,50** |
| **Marge de rejeu** | **0,5** | **0,5** |
| **Total** | **4,0 / 4,0** | **4,0 / 4,0** |
| **Differe a s31** | blocs **G** (`REG-R1`) et **H** (`PER-R1`), **1,0** | - |

**Total planifie du sprint : 7,00. Marge : 1,00. Borne : 8,00.**

**Plafond de cette composition : 4 lignes** - deux lots de sciences a deux lignes chacun.
**52 + 4 = 56 / 138 = 40,6 %.** **Mon attente raisonnable : 3 a 4 lignes**, soit 39,9 % a 40,6 %.

**Verification clause par clause, faite et non supposee :**

- **`PAR-1`** - aucun lot partage. Les deux lots de sciences sont entiers chez B.
- **`PAR-2`** - sciences CM1 : B seule. Mathematiques CM1 : B seule (aucun travail de A n'y touche ;
  les dix consignes de `CONS-1` portent sur questionner le monde CP, histoire-geographie CM1,
  anglais CM1, EMC CP et EMC CM1, pas sur les mathematiques). Questionner le monde CP,
  histoire-geographie CM1, EMC (**CP et CM1 comptes comme une seule matiere**) : A seule.
- **`PAR-3`** - le bloc C touche une matiere a lignes `en cours` : il est chez A, l'instance
  d'accumulation.
- **`PAR-7`** - B prend le seul terrain du sprint ou personne n'a d'accumulation, plus **un** rejeu
  dont la propriete a tenir est ecrite mot pour mot dans ma reserve (`PAR-10`). A prend tout le
  reste : les passes de portee depot, les dettes, les matieres deja auditees.
- **`CYC-1`** - les paquets de cycle 2 a lire (section 2.6) sont chez A, les lignes de cycle 3 chez
  B. **Ce n'est pas un probleme et je le dis explicitement pour qu'on ne me pose pas la question** :
  `CYC-1` est une obligation de **lecture du depot**, pas une contrainte d'affectation - je l'ai
  ecrite ainsi le 2026-09-05, et c'est ce qui la rend tenable a deux instances.

**Pourquoi B prend les deux lots de sciences et A n'en prend aucun.** Ce n'est pas un desequilibre :
les deux instances sont a 4,0. **C'est que les deux natures de travail ne se melangent pas bien.**
L'instance qui audite un contenu neuf sur une matiere jamais servie doit tenir en tete une image qui
se construit au fil des vingt-deux items - `SCI-3` sur le lot, `BIA-2` sur le lot, `FER-1` sur la
ligne entiere. L'instance qui paie huit dettes sur huit paquets differents change d'objet a chaque
fois et n'a aucune image a accumuler. **Melanger les deux, c'est demander a la premiere d'interrompre
son accumulation huit fois** - et l'accumulation est, d'apres `PAR-0`, ce qui produit tout ce que ma
porte apporte au-dela de ce que la CI voit.

### 5.3 Les deux blocs que je nomme comme differables, et le motif

**Ce n'est pas une priorisation** : c'est la reponse a la question *"si deux blocs doivent sortir,
lesquels font le moins de mal a un enfant ?"*, et cette question-la est de mon ressort. `camille-po`
tranche ; si elle veut ces deux blocs, elle sait ce qu'ils deplacent.

| Bloc differe | Reserve | Motif du differe |
|---|---|---|
| **H** | `PER-R1` (`permis-interdit-cp`) | La question et la bonne reponse ne s'enchainent pas toujours en francais correct (*"elle fait un service"*). **Aucun apprentissage faux** : l'enfant apprend la bonne qualification, il lit une phrase maladroite. C'est le seul de la liste ou rien de faux n'est enseigne et ou aucun item ne se gagne sans la competence |
| **G** | `REG-R1` (`regles-de-vie-cp`) | Trois distracteurs hors registre reduisent trois items a un choix entre deux. **Aucun apprentissage faux** : l'item est plus facile qu'il ne devrait, il n'est pas trompeur |

**Et je dis ce que je ne differerais pas, avec le motif, pour que la substitution soit possible sans
me redemander** : le bloc **C** porte `HG-R7`, **une erreur de fait** - le produit enseigne
aujourd'hui que le 14 juillet devient fete nationale des 1790, quand il l'est depuis 1880. **Une
erreur factuelle dans un contenu scolaire ne se differe pas.** Les blocs **B**, **D**, **E** et
**F** portent tous des items **gagnables sans la competence visee** - eliminer sans connaitre
l'objet, repondre sans poser la division, reconnaitre un distracteur a sa seule deraison, resoudre
un classement par un autre item du meme paquet. C'est la contrefacon que `CON-1` decrit, et elle
rend vert un apprentissage qui n'a pas eu lieu.

### 5.4 Les compositions que je decline par ecrit

| # | Composition | Prix planifie | Marge | Pourquoi je la decline |
|---|---|---|---|---|
| **X** | Deux lots de sciences **et** les quinze reserves **et** `CONS-1`, `REV-3`, la passe | **8,00** | **0** | **Zero marge.** C'est la composition 3 de s29, declinee pour le meme motif, et c'est la situation de s28. Et elle ne s'affecte pas : `PAR-2` mettant les deux lots de sciences chez la meme instance, l'autre porterait **4,75**, au-dessus du budget opposable de `PAR-9` |
| **Y** | Trois lots de sciences (6 lignes, 58 / 138 = 42,0 %) plus les dettes | **9,50 et plus** | negative | Depasse la borne de 1,5, exactement le depassement de s29. **Une borne qu'on depasse deux sprints de suite n'est plus une borne** |
| **Z** | Un seul lot de sciences (2 lignes), toutes les quinze reserves | **6,50** | 1,5 | **Je ne la decline pas**, je la nomme : elle paie les quinze dettes et ferme **2 lignes, 54 / 138 = 39,1 %**. **Elle coute deux lignes de couverture** contre la composition de la section 5.2. Le choix appartient a `camille-po` ; l'objectif nomme par l'utilisatrice est la couverture, et je le dis parce que c'est un fait du dossier, pas parce que je priorise |

---

## 6. Ce que je refuse d'avance

**Ecrit avant qu'un lot existe.** Un refus ecrit apres coup est un caprice ; ecrit avant, c'est une
specification. Aucun de ces refus n'est negociable a l'audit, et chacun est actionnable sans me
redemander ce que je voulais dire.

**Sur le fond scientifique :**

1. **Tout item dont la bonne reponse est *"végétarien"* pour designer le regime alimentaire d'un
   animal** (ligne 7). *Vegetarien* est un choix alimentaire humain ; l'animal qui mange des
   vegetaux est **herbivore**. Le libelle de la matrice est faux sur ce point (section 1.6) ; c'est
   le referentiel corrige qui fait foi.
2. **Tout item demandant l'organe principal de *"la nutrition"*** (ligne 9). La nutrition est la
   **famille** qui contient la digestion, la respiration et la circulation, pas une quatrieme
   fonction a leur cote. La question a plusieurs reponses defendables : `HIS-2` la refuse.
3. **Tout item demandant si l'electricite est une *source* ou une *forme* d'energie** (ligne 4). La
   distinction n'est pas tranchable sur cet exemple au cycle 3, et un item sans reponse unique
   defendable est refuse quelle que soit sa formulation.
4. **Tout distracteur presente comme une conception fausse et qui est en realite vrai ou
   defendable.** `SCI-1` fait de la conception fausse le bon distracteur ; un item qui traite comme
   fausse une proposition exacte enseigne l'inverse de son intention, et l'`indice` grave l'erreur.
5. **Tout item qui contredit un item de cycle 2 deja servi sur le meme objet** (`CYC-1`, section
   2.6). **Et symetriquement, tout lot qui eviterait le gaz, la vaporisation et la condensation sur
   la ligne 1** au motif que je les ai refuses au CP : mon refus du CP ne vaut qu'au CP, et
   `FER-1` les exige ici.

**Sur ce que l'item met en jeu :**

6. **Tout item, sur les lignes 16 et 18, qui met en cause l'enfant ou son foyer** - chauffage,
   voiture, voyages, dechets, courses, *"que fais-tu pour la planete ?"*. `SCI-2`, et le motif est
   celui de `BIA-4` : tous les enfants n'ont pas le meme foyer, et un item qui le met en cause
   exclut avant d'enseigner.
7. **Tout paquet de la ligne 16 qui situe ailleurs tous les seismes, toutes les eruptions et toutes
   les inondations.** `BIA-2`. Ce n'est pas de la geologie, c'est une geographie du malheur.
8. **Tout item de la ligne 13 qui dit ou laisse entendre que l'objet ancien etait moins bien.**
   `BIA-3`. On decrit un changement de materiau, de fonction ou d'usage.

**Sur la forme :**

9. **Tout item de sciences a deux propositions dont un distracteur porte une conception fausse
   courante** (`SCI-4`, section 1.2). Sur un item binaire, l'indice de `SCI-1` est une revelation
   deguisee, ce que `M21` refuse.
10. **Plus d'un item sur six demandant une valeur numerique a restituer**, soit plus de 3 items sur
    un lot de 22 (`SCI-3`). Se mesure **sur le lot**, jamais paquet par paquet.
11. **Tout item de la ligne 3 qui calcule une vitesse, une duree ou une longueur.** Un tel item est
    compte sur mathematiques CM1 (`ADR-0081` clause 5) : le porter ici gonflerait le compte de
    sciences sans servir la description du mouvement, qui est ce que la ligne demande.
12. **Toute entree neuve de catalogue reprenant la consigne generique** *"Lis bien la question, puis
    choisis la bonne réponse."* `DEC-0078` en fait une regle produit a partir de s30, et il serait
    absurde que le sprint qui paie `CONS-1` la fasse repousser au meme endroit.

**Sur la ligne 19, et je le repete parce que c'est le refus le plus facile a contourner :**

13. **La decoupe de la ligne 19 en *"choisir l'experience qui teste cette hypothese"*, comptee sur
    la ligne 19.** L'item est honnete et jouable ; c'est **une autre competence, strictement plus
    etroite**. La compter sur cette ligne serait une contrefacon par reduction, de la famille de
    `CON-1`. Si l'utilisatrice veut cette competence, elle s'ecrit comme une **ligne neuve et
    datee**, avec son propre code, pas comme un morceau de celle-ci.

**Sur la gouvernance de mon propre travail :**

14. **Je ne prononce aucun Done pedagogique sur une ligne de sciences dont le code `SCI-CM1-xx`
    n'est pas alloue et declare par le paquet** (section 2.7). Ce n'est pas une barre neuve, c'est
    `ADR-0081` applique.
15. **Je ne valide aucune des quinze corrections de reserve sans rejouer le paquet corrige.** Une
    correction reprise mot pour mot se verifie a l'ecran, pas au diff : c'est en rejouant le
    correctif de `REV-1` que j'ai trouve `REV-2`, et en rejouant `REV-2` que j'ai trouve `REV-3`.
16. **Je refuse d'avance tout dispositif qui verifierait ce qu'un adulte a dit a voix haute.** Refus
    deja ecrit, reconduit sans changement, au cas ou une ligne de sciences ferait naitre l'idee.

---

## 7. Ou cela cassera d'abord

Dit avant, et pas apres. L'ordre est celui de ma confiance decroissante, pas celui de la gravite.

1. **`SCI-1` et son indice, sur les lignes a conception fausse.** C'est une condition que j'ai
   ecrite trois fois en trois jours et qui n'a **jamais rencontre de contenu reel**. Ecrire un
   indice qui dit pourquoi une conception fausse est fausse **sans nommer la reponse** et **sans
   commenter le geste de l'enfant** est un exercice d'ecriture difficile, et le depot n'en contient
   aucun exemple : les indices existants aident a trouver, ils ne corrigent pas une croyance.
   **Mon attente : c'est ici que le premier refus tombera.**
2. **`FER-1` sur les lignes a parenthese longue.** Un lot de 22 items partage en deux paquets de 11
   donne onze items par ligne. La ligne 1 exige **7 elements** en position de bonne reponse, la
   ligne 9 en exige **6**, la ligne 8 **5**. Avec `FER-3` qui reclame une seconde tache d'au moins
   trois items, **un lot qui apparie deux lignes a longue parenthese echouera sur l'une des deux
   conditions.** La parade est de composition, elle est gratuite, et elle doit etre connue
   maintenant : **apparier une ligne large avec une ligne etroite**, jamais deux larges.
3. **La couture entre les deux instances, encore.** C'est le premier mode de defaillance a s29 et il
   s'est realise - l'ecart de comptage des lignes fermees, trouve par ni l'une ni l'autre. Rien n'a
   change depuis : **le registre de `PAR-5` n'existe toujours pas**, et s30 minte au moins trois
   identifiants de plus (`SCI-4`, `PAR-9`, `PAR-10`). Rappel de ma propre regle, qui est le seul
   garde-fou disponible : **les conditions se posent dans l'arbitrage prealable - un document, une
   instance, avant le sprint - et aucune instance n'en cree, n'en amende ni n'en retire en cours de
   sprint.**
4. **Le depassement de borne.** s29 a planifie 7,5 et coute 9,50. Le mecanisme etait le taux de
   refus au premier passage - quatre paquets sur cinq. **Si les deux lots de sciences sont refuses
   au premier passage, la marge de 1,0 est consommee exactement**, et il ne reste rien pour
   `REV-3` s'il devient un ecran neuf (section 3.3). Le calendrier est le vrai remede, et je
   reconduis l'engagement de s29 : **j'audite chaque lot le jour de sa livraison**, jamais en bloc a
   la cloture.
5. **La matrice non corrigee.** Si les libelles des lignes 7 et 9 ne sont pas corriges avant que le
   contenu soit ecrit, le lot sera faux **en obeissant au document de pilotage** - et le refus qui
   suivra sera merite par le lot mais cause par le referentiel. Ce serait le pire des enchainements,
   parce qu'il donne raison a une equipe qui a bien travaille.

---

## 8. Ce dont je reste la seule garde sur ce perimetre

Dit explicitement, parce que sinon l'equipe croira couvert ce qui ne l'est pas. **Quatre defauts
visibles par un enfant ont deja traverse une CI entierement verte, et tant qu'un garde-fou n'existe
pas pour une propriete, c'est moi le garde-fou, et je le suis en jouant.**

| Propriete | Garde executable ? |
|---|---|
| **L'exactitude d'un fait scientifique** | **aucun.** Aucun test du depot ne sait qu'un enonce scolaire est vrai. Et c'est le poste principal du tarif a 1,5 |
| **`SCI-1`** - l'indice corrige la conception fausse sans nommer la reponse | **aucun.** Un garde-fou pourrait verifier que l'indice est **non vide** ; il ne peut pas verifier qu'il corrige quelque chose |
| **`SCI-2`**, **`BIA-2`**, **`BIA-3`**, **`BIA-4`** | **aucun.** Ce sont des jugements sur ce qu'un item met en cause |
| **`SCI-3`** | **mordable si le contenu declare le type de chaque item** - meme remarque qu'a `HIS-1`, adressee a `theo-archi` sans prescrire la forme. En attendant : moi seule, sur le lot |
| **`SCI-4`** | **mordable des aujourd'hui** : le nombre de propositions d'un item est une propriete structurelle du YAML. Si `theo-archi` veut le garder, il lui faut savoir quels items portent une conception fausse - donc une declaration de contenu, comme pour `SCI-3` |
| **`FER-1`, `FER-2`, `FER-3`** | `FER-2` est **derivable** depuis `ADR-0081` une fois les codes alloues. **`FER-1` et `FER-3` restent des jugements** : ce qu'est *"un element du libelle"* et ce qu'est *"une autre tache mentale"* ne se calculent pas |
| **La plausibilite d'un distracteur** | **aucun**, et c'est la propriete qui a produit le plus de reserves de s27 a s29 |
| **`CYC-1`** | **aucun.** Elle compare deux fichiers que rien ne relie |
| **`VOI-3`, `VOI-5`, `VOI-8`** | **aucun, pas meme moi** : je ne suis pas dans la piece quand l'adulte lit |

**Et ce que la parallelisation ne change pas** : deux paires d'yeux sur des lots disjoints, ce sont
deux paires d'yeux, pas une verification croisee. **Aucune des deux instances ne relit l'autre, et
rien dans ce dispositif ne fabrique une seconde lecture d'un meme item.** Personne ne doit lire
*"deux auditrices"* comme *"double controle"*.

---

## 9. Ce que je route

| Fait | A qui | Ce qu'il en fait |
|---|---|---|
| **La matrice porte encore *"végétarien"* (ligne 1744) et *"nutrition"* parmi les fonctions (ligne 1746)**, corriges dans le referentiel depuis le 2026-09-05 et traces par `DEC-0077` section 8.7 | `camille-po`, via `sofia-sm` | **Avant qu'un item de sciences soit ecrit.** `FER-1` rend ces parentheses contractuelles |
| **La matrice porte `SCI-1` dans sa formulation du 2026-09-03**, deux fois amendee depuis | `camille-po` | Recopier la formulation du 2026-09-06, section 1.1 |
| **Les codes `SCI-CM1-01` a `SCI-CM1-20` n'existent pas** (`ADR-0081` les exclut explicitement tant qu'aucun paquet ne les touche) | `theo-archi`, `camille-po` | A allouer dans le meme geste que le lot, comme `lea-dev` l'a fait pour l'EMC |
| **`SCI-3` et `SCI-4` deviennent mordables si le contenu declare le type de chaque item** | `theo-archi` | Je dis la propriete, jamais la forme |
| **Le registre d'identifiants de `PAR-5` reste entier a construire**, et il grandit de trois ce sprint | `theo-archi` | Deuxieme sprint de suite ou je le route. C'est aussi la condition qui bloque la levee de ma retenue de 1,0 |
| **`REV-3` : si le correctif fait apparaitre une liste de plusieurs reponses revelees**, ce n'est plus un rejeu mais un ecran neuf, et mon prix double | `theo-archi`, `sofia-sm` | Dit d'avance pour que ce soit un choix |
| **`PAR-9` et `PAR-10`**, nouvelles clauses de gouvernance de mon audit | `sofia-sm`, `camille-po` | A porter partout ou `PAR-8` est cite |

---

## 10. Ce que ce document ne fait pas

- **Il ne prononce aucun Done pedagogique.** Aucun increment de sciences n'existe.
- **Il ne priorise pas et n'ouvre aucun perimetre.** Les compositions de la section 5 sont chiffrees,
  pas choisies : `camille-po` tranche.
- **Il ne gele aucun objectif et n'affecte personne.** La repartition de la section 5.2 est une
  contrainte de mon jugement, derivee de mes propres clauses ; **c'est `sofia-sm` qui affecte.**
- **Il n'ecrit aucune story et ne modifie pas `docs/backlog/backlog.md`.**
- **Il ne touche pas a `squad/programme/MATRICE-couverture.md`** - deux corrections y sont dues, je
  les route, je ne les fais pas.
- **Il ne modifie aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/`.** Je les
  ai lus pour verifier ce que le produit fait apres une erreur ; je n'y ecris rien.
- **Il ne corrige ni l'orthographe, ni l'accentuation, ni la ponctuation** de quoi que ce soit : ce
  controle appartient au `relecteur-pedagogique` (`DEC-0002` amende le 2026-08-04, `DEC-0056`), y
  compris sur le texte destine a l'enfant.

**Inventaire complet des barres neuves de ce document, arrete au 2026-09-06** : `SCI-4` (portee
item), `PAR-9` et `PAR-10` (gouvernance de l'audit, pas du contenu). **`SCI-1` est precisee dans le
sens qui baisse son cout, `FER-1` est appliquee ligne par ligne sans changer sa definition, et
`PAR-2` recoit une precision qui ne durcit rien.** Aucune autre condition n'est creee, amendee ni
retiree.

**Aucun verdict anterieur n'est efface par ce document.** Les trois formulations successives de
`SCI-1` restent lisibles la ou elles ont ete ecrites ; c'est celle du 2026-09-06 qui vaut.
