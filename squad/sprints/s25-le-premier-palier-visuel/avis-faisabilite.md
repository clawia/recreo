# Avis de faisabilite - s25, le premier palier visuel

- **Auteur** : `theo-archi`. **Date** : 2026-08-31.
- **Slug provisoire, et je le signale** : `s25-le-premier-palier-visuel`. Le slug definitif est fixe
  par `sofia-sm` au planning ; le repertoire est a renommer sans etat d'ame, ce fichier n'est
  reference nulle part ailleurs a ce jour.
- **Arbre mesure** : worktree `s25-theo`, branche `s25-theo` partie de `round-two` a `130ba64`.
  **JDK 21.0.10**, celui que la machine virtuelle des tests rapporte d'elle-meme (`ADR-0071`).
  Enveloppe : `bash outils/mvn-jdk21.sh`.
- **Ce que ce document ne fait pas** : il ne fixe aucun perimetre (`camille-po`), ne juge aucune
  pedagogie (`nadia-prof`), ne prononce aucun Done, n'ecrit aucune story et n'en repriorise aucune.
- **Ce que ce document a produit dans le meme geste** : `ADR-0073` et son garde-fou,
  `LIllustrationDeContenuResoutEtAucuneNeDortTest`, 10 tests, vert et mordant. Un `ADR` sans sa
  regle n'est pas complet ; celui-la l'est.

---

## 0. Une correction a mon fait d'entree, avant tout le reste

`camille-po` ecrit : *"le visuel demande une architecture qui n'existe pas - aucun `ADR` ne dit
comment un item porte une donnee de dessin."* On me demande de verifier plutot que de reprendre.
**J'ai verifie, et la seconde moitie est fausse.**

Un `ADR` en dit quelque chose, et ce qu'il en dit est **non**. `ADR-0036` clause 1 : *"un attribut
`src` / `th:src` dont la valeur est un chemin ecrit en clair. Jamais un chemin construit par
concatenation ou par expression."*

**Mesure plutot que lecture.** Cet `<img>` pose dans le fragment de manche :

```html
<img class="illustration" th:if="${illustration != null}"
     th:src="@{'/illustrations/' + ${illustration} + '.svg'}" alt="">
```

`bash outils/mvn-jdk21.sh -B -o -pl adapters/adapter-web -am test` - **349 tests, 2 echecs**.
*Perimetre : le module `adapter-web` et ses amonts, `-am` porte, jamais le depot. Reference sans la
mutation, meme arbre, meme commande : 349 tests, 0 echec.*

| Classe rouge | Ce que le message dit |
|---|---|
| `RessourcesTiercesTest` | `ADR-0014` : reference par expression calculee, une ressource de page s'ecrit en chemin litteral |
| `HabillageDesEcransTest` | la classe `illustration` n'est nommee par aucune feuille servie |

**L'ecart n'est pas de detail.** *"Rien n'est decide"* invite un lot a decider dans son coin ;
*"une decision verte refuse la forme evidente"* dit que le chemin le plus court pour lever ce rouge
est **d'ajouter une exemption a la garantie hors ligne d'`ADR-0014`**, sous la pression d'une fin de
sprint, par quelqu'un qui n'a pas le temps de la borner. C'est pour cela que l'`ADR` est ecrit avant
le lot et non pendant.

La **premiere** moitie de sa phrase, elle, est exacte : rien ne dit comment un item porte cette
donnee. C'est ce qu'`ADR-0073` decide.

---

## 1. Le choix d'architecture, et le raisonnement qui y mene

### 1.1 Ce que la regle de chemin litteral achete, terme par terme

Elle n'est pas une preference. Elle achete trois choses ; il faut les separer pour savoir laquelle
on perd :

1. **la cible est enumerable** - on lit le gabarit, on liste ce que le navigateur ira chercher ;
2. **la cible existe comme fichier de `static/`** - la garantie hors ligne d'`ADR-0014` ;
3. **la cible ne varie pas avec l'item** - la condition C5 de `nadia-prof`, qu'`ADR-0036` fait porter
   par cette clause faute d'un autre canal.

Une illustration de contenu renonce a (3) **volontairement** : c'est son objet meme. Elle ne renonce
ni a (1) ni a (2) - **elle les deplace**. L'ensemble des cibles reste enumerable, non plus depuis le
gabarit, mais depuis **la donnee**.

**C'est le choix : la preuve ne disparait pas, elle change de source.** Et elle y gagne un second
sens que le decor n'a jamais eu - *aucun dessin ne dort sur disque sans qu'un item le montre*.

### 1.2 Ressource servie, donnee dans le paquet, ou reference resolue par un adapter ?

La question m'est posee dans ces trois termes. **La reponse est la troisieme, et les deux autres sont
ecartees pour des motifs differents.**

- **Une donnee dans le paquet** - le dessin encode dans le YAML - est ecartee : le paquet devient
  illisible a la relecture de `nadia-prof`, l'image passe sous le juge de chemins de poste de travail
  qu'`ADR-0036` fait 2 avait deja fait rougir sur quatre PNG sur vingt et un, et le navigateur ne peut
  plus rien mettre en cache.
- **Une ressource servie designee dans le gabarit** est ecartee par la mesure de la section 0.
- **Une reference resolue par un adapter** est retenue : l'item declare un **identifiant** de
  l'alphabet `[a-z0-9-]+`, l'adapter web le resout en un fichier servi, en un seul endroit.

**Pourquoi un identifiant et pas un chemin.** Un chemin dans la donnee fait entrer trois choses dans
le contenu : l'agencement du serveur, le format du fichier, et la possibilite d'ecrire `../` ou
`https://`. La premiere est une fuite de framework dans un fichier que `nadia-prof` relit ; la
troisieme est une porte ouverte. **L'alphabet ferme les trois d'un coup, sans qu'aucune inspection de
chemin ne soit ecrite nulle part** : un identifiant qui ne peut contenir ni point ni barre oblique ne
peut designer que le repertoire prevu. C'est la question 2 de mon reflexe - ce que la regle interdit
reellement a quelqu'un qui essaierait de mal faire - et la reponse est trois choses nommees, pas rien.

### 1.3 Le palier 1 n'est ni une forme d'item de plus, ni une mecanique de plus

C'est la decision la plus structurante et celle qui economise le plus. **Elle n'est pas un avis, elle
suit un critere ecrit dans ce depot.**

`ADR-0058` justifie la seconde forme d'item ainsi : **la facon dont l'enfant repond a change** -
choisir contre ecrire. **Elle ne change pas au palier 1** : l'enfant choisit parmi des propositions en
texte, exactement comme au bandit manchot. Ce qui change est **ce qu'il regarde avant de choisir**.

Cette categorie-la a deja un patron et trois occurrences dans le depot : `explication` (`ADR-0024`),
`texteSupport` (`ADR-0050`), `partDesignee` (`ADR-0062`) - une composante **optionnelle** de
`Question`, que la mecanique transporte sans jamais la lire, qu'un paquet qui n'en porte pas ne
remplit d'aucune valeur factice.

**L'objection d'`ADR-0058` ne s'applique pas, et je la traite au lieu de l'esquiver.** Il refuse de
poser les formes acceptees sur `Question` parce que cela *"obligerait treize paquets de choix a porter
une cle qui ne les concerne pas - la dette 2 de la version precedente"*. **Une composante `Optional`
n'oblige personne**, et les 27 paquets servis le demontrent : aucun ne porte de `partDesignee`
factice.

**Et ce n'est pas une mecanique de plus.** Le chemin d'un champ de contenu jusqu'a l'ecran est
mesurable : `texteSupport` occupe **trois** endroits en production - une composante de `Question`, un
accesseur de trois lignes sur `BanditManchotSession`, un attribut de modele dans `PartieController` -
plus une branche de gabarit. Une mecanique visuelle qui redistribuerait des propositions, tiendrait un
score et rendrait un verdict serait une **copie** de `BanditManchot` : la dette n° 4 de `CLAUDE.md`
par un autre chemin, huit classes qui ne font que deleguer.

**Ce qui me ferait changer d'avis**, ecrit maintenant pour que ce soit un declencheur et non une
revision : un palier ou l'enfant **agit sur l'image**. Voir la section 5.

**Deux precisions de frontiere.** *Premiere* : ce que je dis ici est ce que le palier 1 **coute** en
pieces de code, ce qui est ma charge - `nadia-prof` ecrit elle-meme que *"le cout de chacun est a
`theo-archi`"*. Si `camille-po` juge qu'une entree de catalogue doit porter un identifiant de
mecanique distinct pour des motifs de produit, c'est sa decision et elle ne coute presque rien ; ce
qui couterait est d'ecrire un second moteur derriere. *Seconde* : `nadia-prof` distingue trois paliers
et refuse qu'on les confonde. Ma decision **respecte** cette distinction plutot qu'elle ne l'efface :
elle rend le palier 1 bon marche **precisement parce qu'il est le palier 1**, et elle ecrit ce qui
change au palier 2.

---

## 2. Ce que la troisieme forme coute aux garde-fous de contenu - mesure, pas estime

`sofia-sm` a etabli a s24 que le prix d'une **seconde** forme d'item etait de treize garde-fous, et
elle l'a fait en posant une sonde apres avoir refuse de croire mon chiffre. **Elle avait raison de le
faire, et son chiffre avait corrige le mien.** Je reprends sa methode.

### 2.1 Methode et perimetre

Deux sondes completes, **rien de commite, rien laisse sur disque**. Commande identique dans les deux
cas : `bash outils/mvn-jdk21.sh -B -o test` sur la **racine**, donc reacteur complet - la question du
`-am` ne se pose pas, il n'y a pas de mesure partielle. Phase `test` : les tests d'integration de
`verify` ne sont pas dans ce compte.

- **Sonde B** - une **troisieme forme d'item** : `Item permits Question, ReponseAEcrire,
  SondeIllustree`, sa fabrique, son enregistrement au chargeur, et un paquet de figures planes de onze
  items servi depuis `contenu/`.
- **Sonde A** - une **composante optionnelle de plus sur `Question`** : `Optional<String>
  illustration`, sa lecture dans `QuestionContentSourceFactory`, et le meme paquet de onze items, de
  type `question`, dont chaque item porte `enonce` en premier champ.

**Reference sans sonde, meme arbre, meme commande** : domain **78**, application **13**,
`adapter-web` **349**, `adapter-content` **355**, `bootstrap` **125** - soit **920 tests, 0 echec**.

### 2.2 Le resultat

| | Arrets du compilateur | Classes rouges | Echecs | Erreurs |
|---|---|---|---|---|
| Reference | 0 | 0 | 0 | 0 |
| **Sonde B** - troisieme forme | **6** | **12**, toutes dans `adapter-content` | 22 | 1 |
| **Sonde A** - composante optionnelle | **0** | **4**, toutes dans `adapter-content` | 6 | 0 |

`adapter-web` (349) et `bootstrap` (125) restent a **0 echec** sous les deux sondes. Le prix se paie
entierement dans `adapter-content` - ce qui confirme sur une troisieme forme ce que `sofia-sm` avait
mesure sur la deuxieme, et qui n'allait pas de soi.

### 2.3 Les six arrets du compilateur sont le dividende de s24, et ils sont tous bruyants

Deux en production - `BanditManchotSession.questionDe`, `SaisieSession.reponseDe` - et quatre dans les
tests, dont **trois dans le seul `ClassementDesItems`**. Le classement des formes ecrit une fois par
`sofia-sm` a concentre ce qui aurait ete disperse dans treize `switch`. **Sans le lot 5a de s24, une
troisieme forme aurait coute treize sites au lieu de quatre.**

### 2.4 Le fait qu'aucune lecture ne donnait

Trois des douze classes rouges de la sonde B le sont pour une cause qui n'est ecrite nulle part -
ni dans le tri de s24, ni dans un `ADR` : **`ChaquePaquetPorteUnIndiceTest.itemsDeclares`, le comptage
textuel partage, ne trouve aucun item** dans le paquet de sonde. Son critere est explicite : *"un item
enumere porte `enonce` en premier champ, quelle que soit sa forme (`ADR-0064`, lot 5a de s24)"*.

Une forme d'item dont le premier champ est `illustration` sort du comptage, et le message le dit
exactement, en nommant le paquet. **C'est une contrainte sur toute forme d'item future, bien au-dela
du visuel**, et elle ne se lit dans aucun document.

### 2.5 Ce que les quatre rouges de la sonde A sont vraiment - un par nature

C'est la partie utile pour le planning : **les quatre ne demandent pas le meme travail.**

| Classe rouge | Nature | Ce que cela coute au lot |
|---|---|---|
| `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` | **obligation de conception**, et elle est saine | Dire si l'ecran montre l'illustration, donc si elle entre dans l'identite d'ecran. Le garde-fou nomme la composante non classee et refuse d'avancer. **C'est la clause de s24 qui tient exactement sa promesse** |
| `AucunSigneDoubleNEstDetachableDeSonMotTest` | **hygiene de redaction du paquet** | Le cliquet de `TYP-1` compte 16 espaces ordinaires devant un signe double dans mon paquet de sonde. Un paquet neuf part a zero : c'est le cout normal, deja paye par les quatre paquets de s24 |
| `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | **cout de tout paquet non numerique** | Une declaration `ADR-0051` : le paquet est hors du domaine de la propriete, avec son motif. Deja paye par 15 paquets sur 27 |
| `ChaquePaquetExpliqueSesItemsAVariationTest` | **un defaut du depot, sans rapport avec le visuel** | Voir la section 3. C'est le seul des quatre qui n'est pas un cout normal |

### 2.6 La limite de cette mesure, que `sofia-sm` a etablie et que je reprends

*"Le nombre se reproduit, la liste ne se reproduit pas. Une sonde mesure ce qu'elle contient."* Mes
douze classes de la sonde B ne seront pas exactement les douze d'une autre sonde : `AucunSigneDoubleNEstDetachableDeSonMotTest`
depend de ma typographie, `ChaquePaquetExpliqueSesItemsAVariationTest` de ma decision d'ecrire des
explications, `LeRangDeLaBonneReponse...` de mes distracteurs non numeriques. **Ce qui se reproduit est
l'ordre de grandeur et le rapport entre les deux sondes** : une troisieme forme d'item coute environ
**trois fois** ce que coute une composante optionnelle, et elle ajoute six arrets de compilation que
l'autre n'a pas. **Aucune de mes sondes n'ajoutait ni ne retirait un seul test** : ce sont les memes
355 de `adapter-content` qui changent de verdict.

---

## 3. Un defaut du depot, trouve par la sonde et isole par un temoin

**`explication`, que `ADR-0024` a rendue universelle et qui vit sur le record traverse par toutes les
matieres, est aujourd'hui inutilisable par tout paquet qui ne declare pas aussi `graphie`** - une cle
de francais, ecrite pour un seul paquet.

`ChaquePaquetExpliqueSesItemsAVariationTest` fait entrer un paquet dans son controle des que **l'une
des deux** cles apparait, et exige alors **les deux sur tous les items**. Le garde-fou est bon : les
deux cles se gardent l'une l'autre, et c'est ce qui le rend incontournable par une faute de frappe.
**L'effet de bord, lui, n'a jamais ete decide.**

**Temoin, pour que ce ne soit pas un artefact de mon paquet visuel.** Une seule ligne `explication:`
ajoutee au premier item de `cm1-mesures.yaml`, un paquet de mathematiques existant, sans rien d'autre :

```
bash outils/mvn-jdk21.sh -B -o -pl adapters/adapter-content -am test \
  -Dtest=ChaquePaquetExpliqueSesItemsAVariationTest -Dsurefire.failIfNoSpecifiedTests=false
```

**5 tests, 1 echec** : *"paquet cm1-mesures, item "7 cm, combien de mm ?" : ce paquet declare la
graphie de ses items, donc tous."* La ligne a ete retiree ; rien n'en subsiste.

**Mesure de l'etendue** : sur les 27 paquets servis, **un seul** porte `explication` -
`cp-le-bon-son`, 6 occurrences - et c'est **le seul** qui porte `graphie`, 15 occurrences. La clause
n'a donc jamais ete exercee que sur le paquet pour lequel elle a ete ecrite.

**C'est la dette n° 2 de `CLAUDE.md` sous sa forme exacte** : une cle taillee pour une matiere qu'une
autre devrait remplir de valeurs factices - ici, une *graphie* pour un dessin de figure geometrique.

**Je ne le corrige pas dans ce lot et je ne decide pas quand il l'est** : ce n'est pas le perimetre
qu'on m'a donne, et la correction touche un garde-fou vert qui protege un vrai paquet. **Je le
signale avec sa methode, et j'en tire une consequence de sequencement en section 6.**

---

## 4. Les lots

**Ce que j'ai deja livre sur cette branche**, et qui n'est pas un lot du sprint : `ADR-0073` et son
garde-fou. Ils sont sur `s25-theo`, verts, et ils changent la porte technique de 926 a **936 tests**.

### Lot 1 - le champ de contenu, du domaine jusqu'a l'ecran

**Modules** : `domain`, `adapter-content`, `adapter-web`. **Depend de** : rien.

Deux composantes optionnelles sur `Question` (`illustration`, `illustrationTexteAlternatif`), leur
lecture dans `QuestionContentSourceFactory`, deux accesseurs sur `BanditManchotSession`, deux
attributs de modele dans `PartieController`, une branche de gabarit et sa regle de feuille maison.

**Trois obligations mesurees, pas devinees** :

1. **Classer les deux composantes dans l'identite d'ecran.** Mesure : la sonde A rend
   `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` rouge en nommant `illustration` parmi les
   composantes non classees. Le lot ne peut pas l'ignorer, le compilateur ni le garde-fou ne le
   laisseront passer.
2. **Nommer la classe CSS dans la feuille maison.** Mesure : `HabillageDesEcransTest`, section 0.
3. **Borner l'exemption de `RessourcesTiercesTest`, et la mordre dans les deux sens.** C'est le point
   le plus dangereux du sprint, et `ADR-0073` clause 3 en decide la forme : une seule expression est
   admise, prefixe litteral egal au repertoire d'illustrations, un unique terme calcule, extension
   litterale. **Une seconde forme calculee, posee dans le meme gabarit, doit rester rouge** - sans
   cette moitie-la, la morsure prouve que l'exemption existe, pas qu'elle est etroite.

**Faisable dans un sprint** : oui, et c'est le plus petit des trois lots. La reference de cout est
`texteSupport` : trois endroits de production et une branche de gabarit.

### Lot 2 - les dessins

**Modules** : `adapter-web` (`static/illustrations/`). **Depend de** : rien techniquement, de
`nadia-prof` et d'`ines-design` pour tout le reste.

N fichiers SVG, un par item. **Deux obligations** :

1. **Aucun `<text>` ni `<tspan>`** (`ADR-0073` clause 4), sous peine de rouvrir un troisieme angle
   mort sur le texte lu par l'enfant.
2. **Le cliquet de poids.** `ADR-0036` clause 5 laisse une dette explicite depuis s10 : *"le commit
   qui livre la premiere image illustree pose le cliquet de poids, sur la valeur qu'il installe"*.
   **C'est ce lot-ci, et pas un autre.** La cible - ce qui est acceptable sur le telephone d'un enfant,
   sur le reseau d'une ecole - est une decision de `sofia-sm` et de `nadia-prof`, pas la mienne.
   Reference : le seul dessin maison du depot, `cabinet-fronton.svg`, pese 1 883 octets ; l'ecran de
   jeu chargeait 321 281 octets a s10.

**Faisable dans un sprint** : ce n'est pas ma competence de le dire. Je note seulement qu'un dessin
geometrique sans texte - cadran, figure plane, piece - se fait en SVG en quelques dizaines de lignes,
et que ce lot ne depend d'aucun autre.

### Lot 3 - le paquet de contenu et son entree de catalogue

**Modules** : `adapter-content` (`contenu/`, `catalogue.yaml`). **Depend de** : lot 1 **et** lot 2, et
la dependance est **bidirectionnelle et dure** - voir la section 6, c'est ce qui protege s25.

**Le cout normal d'un paquet neuf**, deja paye 27 fois : la declaration `ADR-0051` du garde-fou de
rang, la typographie de `TYP-1` a zero, l'indice sur chaque item, l'unicite des ecrans.

**Faisable dans un sprint** : oui, si `nadia-prof` a arbitre la ligne. Le nombre de paquets, lui, est
une decision de `camille-po`.

### Ce qui n'est pas un lot et que je nomme quand meme

`DEC-0059` section 5.3 refuse de cloturer sur une mecanique non jouable, et s24 y est arrive **par un
autre chemin que celui contre lequel le garde-fou etait pose** : une entree de catalogue qui dependait
de deux lots. **Le meme chemin existe ici** : lot 3 depend des deux autres. La difference est que je
l'ecris avant le planning au lieu de le decouvrir au dernier jour.

---

## 5. Les paliers 2 et 3 - un ordre de grandeur, sans les concevoir

La question qui m'est posee est precise : **le palier 1 ferme-t-il des portes, ou les laisse-t-il
ouvertes ?** Je reponds a celle-la et pas a une autre.

**Il n'en ferme aucune, et il en ouvre une.** Ce que le palier 1 installe - un identifiant declare par
l'item, un repertoire d'illustrations, une resolution en un seul endroit, un garde-fou bidirectionnel -
est **exactement** ce dont les paliers suivants ont besoin, et rien de ce qu'ils devraient defaire.

**Palier 2 - propositions positionnees sur l'image.** Le geste de reponse change : l'enfant ne presse
plus un bouton portant un mot, il presse un endroit. C'est le moment ou **le critere d'`ADR-0058` est
rempli** et ou une forme d'item ou une mecanique de plus se justifie sur un usage constate, non
imagine. Ordre de grandeur, en pieces et non en jours : une forme d'item (les six arrets de
compilation mesures en section 2, plus le tri des garde-fous de contenu que `sofia-sm` a chiffre a
quatorze pour la saisie), une mecanique, un ecran, et - le point que je ne sais pas chiffrer - **des
coordonnees dans la donnee**, ce que ce depot n'a jamais eu. Le palier 1 ne rend rien de cela plus
cher.

**Palier 3 - manipulation.** `nadia-prof` le decrit comme *"deplacer, marquer, grouper"*. C'est le
seul des trois qui met en cause une contrainte fondatrice : le comportement de jeu vit sur le serveur
(`ADR-0008`), et un glisser-deposer sur cent objets qui fait un aller-retour serveur par geste est un
autre produit. **Je ne dis pas qu'il est infaisable ; je dis qu'il rouvre `ADR-0008`, et qu'aucun des
deux autres paliers ne le fait.** Le palier 1 ne l'aggrave ni ne le facilite.

**Une porte que le palier 1 laisse ouverte, et qu'il faut savoir ouverte** : la clause 4 de
`ADR-0073` - aucun texte dans un dessin - n'est pas un interdit definitif. Le jour ou une figure
legendee est demandee, la clause cede **et les garde-fous de texte apprennent a lire un SVG dans le
meme geste**. C'est ecrit comme declencheur dans l'`ADR`, pas comme un obstacle.

---

## 6. Les defauts d'integration de s24 - est-ce que mon decoupage y expose s25 ?

Le mode de defaillance de s24 : **vert sur une branche, vert sur l'autre, rouge une fois ensemble**.
J'en ai tire *"j'ai decrit mon clone en croyant decrire le depot"*. La question est de savoir si mes
trois lots refont la meme chose.

### 6.1 Ce qui est structurellement immunise, et c'est mesure

**Un lot 2 seul est rouge. Un lot 3 seul est rouge.** Ce n'est pas une esperance de sequencement,
c'est la propriete bidirectionnelle du garde-fou d'`ADR-0073`, et je l'ai verifiee dans les deux
sens :

- **un fichier sans paquet qui le declare** : `figure-sonde.svg` depose sous
  `adapters/adapter-web/src/main/resources/static/illustrations/`, aucun paquet touche - **2 tests sur
  10 rouges**, dont *"aucun item d'aucun paquet servi ne le declare"*. Le fichier a ete retire ;
- **un paquet qui declare sans fichier** : la morsure 1 du garde-fou, sur materiau reel mute en
  memoire - rouge en nommant l'identifiant et le paquet.

**Les deux moities ne peuvent donc pas etre vertes separement et rouges ensemble** : chacune est rouge
separement. C'est le contraire exact du defaut de s24, et c'est pour cela que le garde-fou a ete ecrit
dans les deux sens plutot que dans un seul.

### 6.2 La seule exposition reelle qui reste, et elle est nommee

**Si le premier paquet illustre porte des explications, il est rouge sur le defaut de la section 3** -
la coupure `explication` / `graphie` - **et il le sera au dernier lot, sur la branche qui fusionne en
dernier.** Vert sur le lot 1, vert sur le lot 2, rouge quand le paquet arrive : c'est la forme exacte
de s24.

Trois choses ferment ce risque, et le choix entre elles n'est pas le mien :

1. **le mesurer maintenant** - c'est fait, section 3, et c'est ce qui empeche la surprise ;
2. **placer la correction du defaut avant le lot 3**, ce qui en fait un lot du sprint et releve de
   `camille-po` ;
3. **ecrire le premier paquet illustre sans explication**, ce qui est une contrainte pedagogique et
   releve de `nadia-prof`.

### 6.3 La cause de fond de s24, et ce qui la rejoue

La retro l'ecrit : *"le planning avait prevu l'ordre de fusion des lots entre eux, il n'avait rien
prevu pour un garde-fou neuf ecrit pendant le sprint par un agent qui n'etait pas dans la chaine de
fusion."* `ADR-0072` etait le quinzieme garde-fou de contenu, ne apres le tri.

**Ce sprint en produit un seizieme, et c'est moi qui l'ecris** :
`LIllustrationDeContenuResoutEtAucuneNeDortTest`. Il est sur `s25-theo`, avant le planning, et non au
milieu du sprint - mais **la classe de defaut est la meme**, et l'amendement propose a
`squad/README.md` par la retro de s24 (section 3.2 : *le tri des garde-fous est rouvert par tout
garde-fou ne pendant le sprint*) s'applique a lui. **Je le declare de moi-meme plutot que d'attendre
qu'on me le trouve.** Deux differences le rendent moins dangereux que le cas de s24 : il vit dans
`bootstrap` et ne transtype rien, donc il ne peut pas casser sur une forme d'item ; et son perimetre
est vide aujourd'hui, donc il ne peut rougir que sur ce que le sprint ajoute deliberement.

---

## 7. Verdict de faisabilite, lot par lot

| Lot | Verdict |
|---|---|
| **Lot 1** - le champ jusqu'a l'ecran | **Faisable en l'etat**, sous `ADR-0073`, qui est ecrit. Trois obligations mesurees et nommees, dont une - la borne de l'exemption de `RessourcesTiercesTest` - a surveiller en review comme le point le plus dangereux du sprint |
| **Lot 2** - les dessins | **Faisable en l'etat** cote architecture. **Porte la dette de cliquet de poids d'`ADR-0036` clause 5**, ouverte depuis s10, et c'est le lot qui la ferme. Le reste n'est pas de ma competence |
| **Lot 3** - le paquet et le catalogue | **Faisable moyennant une decision qui n'est pas la mienne** : soit la coupure `explication` / `graphie` est corrigee avant, soit le premier paquet n'ecrit pas d'explication. Sans l'une des deux, le lot est rouge au dernier jour, sur la forme exacte du defaut de s24 |
| **La famille visuelle entiere en un sprint** | **Infaisable sans revoir le decoupage**, et le motif n'est pas technique : `nadia-prof` etablit que le palier 1 ouvre trois lignes, dont deux demandent **deux paquets chacune** - le visuel *et* un paquet textuel. Le nombre de lignes tenables est un arbitrage de `camille-po`, pas un verdict d'architecture |

**Sur la troisieme mecanique elle-meme** : l'ouverture arbitree est **faisable, et moins chere que le
mot ne le laisse croire** - zero arret de compilation, quatre classes de garde-fou a traiter dont une
seule est une vraie decision de conception, et aucune classe de mecanique neuve. C'est le resultat le
plus utile de cet avis, et c'est le resultat d'une mesure, pas d'une opinion.

---

## 8. Les trois dettes echues de s24 - lisibles, non engagees, et ma position sur chacune

Elles ne sont pas dans le perimetre que `camille-po` fixera. Elles restent ecrites ici parce qu'elles
sont miennes et qu'un sprint visuel est exactement celui ou deux d'entre elles se representent.

### 8.1 Le gestionnaire d'evenement inline - **severite haute, et elle monte avec ce sprint**

`yanis-qa` a mesure : un `onclick` pose sur un bouton de proposition laisse **926 tests verts**, parce
que le budget d'`ADR-0008` derive sa population de `static/**/*.js` et que son seul balayage de
gabarits cherche quatre marqueurs de correction. **Un attribut n'est ni un fichier `.js` ni un
marqueur de correction.**

**Ma position** : le trou est reel, il est a moi, et le correctif est simple - le perimetre doit etre
**l'attribut**, pas le fichier, exactement comme `ADR-0036` avait deja fait passer les references de
ressource d'une liste de balises a un attribut. Un attribut dont le nom commence par `on`, ou par
`hx-on:`, dans un gabarit servi, est du comportement de jeu ecrit en JavaScript.

**Pourquoi ce sprint et pas un autre** : une mecanique visuelle est le lot ou la tentation revient -
mettre en evidence une zone, animer une figure, marquer un choix - et le depot n'a aujourd'hui **aucun**
`onclick`, `oninput`, `onsubmit` ni `hx-on:`. **Un cliquet pose sur zero est exact ; pose apres le
premier, il ne l'est plus.** Un fait qui pese dans l'autre sens et que je ne cache pas :
`FrontieresDuJavaScriptTest` est l'un des **deux** noms de `DETTE_DE_BALAYAGE`, plafond a 2, garde-fou
sans jeu fautif - le corriger demande de lui en ecrire un et de baisser le plafond dans le meme diff.
**C'est un lot, pas une retouche.** La decision de l'ouvrir est a `camille-po`.

### 8.2 Le cliquet typographique qui ne voit que les paquets

Le `relecteur-pedagogique` a compte **11 occurrences** d'espace ordinaire devant un signe double dans
le texte rendu par les gabarits et les constantes de message, dont **3 neuves a s24**, sous une porte
technique verte - parce que le cliquet de `TYP-1` derive son perimetre du texte servi par les
**paquets**.

**Ma position** : le perimetre est faux, pas le cliquet. `ADR-0067` existe pour *"arreter la
croissance"*, et il l'arrete d'un cote en l'autorisant de l'autre. **Ce n'est pas a moi de trancher
seul** : `ADR-0067` laisse ouvert le choix entre l'insecable ordinaire, l'insecable etroite et le
retrait de l'espace, et ce choix porte sur du texte lu par un enfant - il passe par `nadia-prof`. Ce
qui est a moi est le **perimetre** : le jour ou le caractere est choisi, le cliquet doit deriver sa
population des deux sources, gabarits compris. **Troisieme source a ne pas oublier** : `ADR-0073`
clause 4 interdit le texte dans un dessin **precisement pour ne pas en ouvrir une troisieme**.

### 8.3 Un concept, trois noms - **j'arbitre ici, la mise en oeuvre reste un lot**

Le `relecteur-pedagogique` route la decision et note qu'un des trois noms est deja ecarte par une
Javadoc du meme sprint.

| Nom | Ou il vit |
|---|---|
| **exigence de forme** | `ExigenceDeForme`, la cle YAML, la fabrique, les fixtures, le paquet servi |
| **mode de forme** | titre et section 1 d'`ADR-0069`, `ADR-0070`, `ADR-index`, l'avis de s24, `review.md`, `RAPPORT.md` |
| **mode de saisie** / **mode de comparaison** | `ModeDeSaisieEnBooleenContreExemple`, et le nom de la regle mordue |

**Le terme canonique est `exigence de forme`**, et le motif n'est pas une preference : la Javadoc
d'`ExigenceDeForme` le **motive** - *"un nom qui parlerait de comparaison ferait dire au contenu ce que
la mecanique fait, la ou `ADR-0004` tient les deux separes"*. Le troisieme nom est exactement celui que
ce paragraphe ecarte, **et il vit dans la section « Regle executable » d'un `ADR` accepte**,
c'est-a-dire a l'endroit du depot qui fait foi. Le raisonnement etait bon ; c'est le nommage qui ne
l'a pas suivi.

**Un arbitrage ecrit ne coute rien ; le renommage en coute.** Il touche une chaine assertee dans
`LesReglesMordentTest`, le corps d'`ADR-0069`, `ADR-0070` et `ADR-index`. C'est un lot, il n'est pas
engage a s25, et la decision de l'ouvrir est a `camille-po`. **Ce qui est ferme des maintenant est la
question** : le terme est tranche, plus personne n'a a le rouvrir.

---

## 9. Ce que je ne decide pas, et ce que je route

| Question | A qui |
|---|---|
| Combien de lignes visuelles s25 ouvre, et lesquelles | `camille-po` |
| Ce que le texte alternatif d'une illustration **dit** - et le fait qu'un texte alternatif qui nomme la figure est la contrefacon de `nadia-prof` transposee au lecteur d'ecran | `nadia-prof` |
| Ce qu'un dessin represente, et s'il tient au-dessus du pli | `ines-design`, puis la porte de `nadia-prof` |
| La cible de poids d'une illustration - sur le telephone d'un enfant, sur le reseau d'une ecole | `sofia-sm` et `nadia-prof`. `ADR-0036` clause 5 refuse deja qu'un architecte invente ce nombre |
| Le caractere retenu pour `TYP-1` | `nadia-prof` ; le perimetre du cliquet reste a moi |
| Quand la coupure `explication` / `graphie` est corrigee | `camille-po` pour le rang, moi pour le moyen |

---

## 10. Confirmation d'execution - la porte technique, avec sa commande

`squad/README.md` demande le resultat de la commande, pas une impression.

```
bash outils/mvn-jdk21.sh -B -o clean verify
```

**BUILD SUCCESS**, cinq modules, **936 tests, 0 echec, 0 erreur**. *Methode du comptage : somme des
lignes de resultat de module (`surefire` et `failsafe`), jamais des lignes par classe ; perimetre le
reacteur complet, donc pas de `-am` a porter. Reference a `130ba64` avant ce lot : 926. Les 10 tests
d'ecart sont ceux de `LIllustrationDeContenuResoutEtAucuneNeDortTest`, et rien d'autre n'a ete
touche.*

`bash .github/scripts/verifier-chemins.sh` sur l'index : *"Aucun chemin de poste de travail ni nom de
compte dans les fichiers verifies."*

**Compte des regles ArchUnit : 31, inchange par ce lot** - et le chiffre porte sa methode, parce que
trois nombres circulent. **31** est ce que rend le recensement du depot lui-meme,
`LesGardeFousSontMisALEpreuveTest.toutesLesRegles`, sur les 168 fichiers de source de test des cinq
modules declares. `ADR-0070` et `ADR-0071` ecrivent *"inchange a 30"* : c'etait vrai avant
`ADR-0072`, qui a ajoute une regle sans que le compte soit redemande - **deux `ADR` ont recopie un
nombre au lieu de le mesurer**. Un grep de chemin rend **33** sur le meme arbre, et les deux de trop
viennent du **faux module de contre-exemple** de `LesGardeFousSontMisALEpreuveTest`, dont le chemin
interne contient `src/test/java`. **C'est la cause exacte que ma propre lecon de s21 nomme**, et elle
se represente ici, sur le chiffre suivant. Le recensement fait foi ; un compte recopie a la main dans
un `ADR` pourrit.

---

## 11. Complement du 2026-08-31 - la reponse a `nadia-prof` sur les chiffres du cadran et de la piece

Elle a verifie mon commit avant de s'appuyer dessus, approuve la clause 4 sans reserve, et pose la
seule question qui restait : **`HOR-2` exige les chiffres du cadran, `MON-1` des pieces
reconnaissables - la distinction est-elle tenable dans un automate ?** Le detail est dans
l'amendement d'`ADR-0073` ; voici ce qui interesse le planning.

**Reponse : oui, et sans qu'une clause soit retiree ni une exemption ouverte.** Son jugement -
*"c'est une partie de l'objet dessine, comme les aiguilles"* - nomme le bon critere, et la clause 4
l'encodait deja. Ce qui etait faux est une premisse que sa question et ma clause partageaient sans
l'avoir examinee : **qu'un chiffre visible exige un element de texte.**

**Mesure, sur le chemin de production reel, la meme piece de deux euros deux fois** : chiffre trace
en `path`, clause 4 **verte** ; le meme chiffre en element de texte, clause 4 **rouge**. La sonde a
ete retiree.

**Et la distinction que j'aurais du ecrire a l'interieur d'un element de texte n'est, elle, pas
tenable** - demontree plutot qu'affirmee, sur les **680 bonnes reponses des 27 paquets servis** :
tout critere admettant le `2` de la piece admet aussi la reponse ecrite sur la figure (purement
numerique : 217 reponses, 31,9 % ; deux caracteres au plus : 267, 39,3 % ; aucune lettre : 308,
45,3 %). Les trois echouent du meme cote, le grave.

**Trois consequences pour le planning, dont deux ne sont pas les miennes :**

1. **`HOR` et `MON` restent au palier 1** - sous une reserve qui n'est pas d'architecture : **c'est
   `ines-design` qui dit si douze chiffres traces sont producibles.** J'ai demontre le trace sur une
   piece, a la main. Si sa reponse est non, la contrainte mord et la ligne tombe. Je ne fais pas
   passer pour un verdict d'architecture ce qui depend d'une main que je n'ai pas.
2. **La contrainte `explication` / `graphie` de ma section 3 ne mord pas a s25**, `nadia-prof` l'ayant
   levee d'elle-meme : ses conditions de palier 1 demandent un indice, pas une explication. **La
   section 6.2 de cet avis perd donc son objet pour ce sprint** ; le defaut reste signale et entier.
3. **Une clause 6 est ajoutee a `ADR-0073`, et elle lui est due** : un item illustre declare `enonce`
   comme premiere cle. J'avais mesure le cas total - un paquet entier qui tombe a zero item,
   bruyamment. Elle en tire le cas **partiel**, qui est le dangereux, et **elle a raison** : un
   paquet dont certains items commencent par `illustration` est compte en partie, et deux des huit
   garde-fous qui partagent ce comptage ne portent pas la clause de taille qui le rattraperait.
   *"C'est la forme de `CPL-3`"* - la condition ecrite que rien n'executait.

**Ce que je n'ai pas fait, et c'est le point** : je n'ai pas elargi la clause pour faire tenir une
ligne. Le compte des morsures monte de six a sept, le jeu fautif passe de quatre a cinq fautes, et
aucune exemption n'existe. Si la reponse avait ete non, le sprint aurait pris trois lignes.

**Porte technique apres ce complement** : `bash outils/mvn-jdk21.sh -B -o clean verify` - **BUILD
SUCCESS, 937 tests, 0 echec**, un de plus que les 936 du commit precedent, et c'est la morsure de la
clause 6. Meme methode de comptage qu'en section 10.
