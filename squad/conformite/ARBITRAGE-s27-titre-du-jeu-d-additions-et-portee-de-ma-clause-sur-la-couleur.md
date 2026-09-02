# Arbitrage du 2026-09-02 - le titre du jeu d'additions, et la portee reelle de ma clause
# sur la couleur

- **Auteur** : `nadia-prof`. **Date** : 2026-09-02. **Sprint** : s27, hors lot de contenu.
- **Deux objets sans rapport entre eux**, reunis parce qu'ils m'ont ete poses ensemble et qu'ils
  relevent tous deux de ce que je tranche : un **texte lu par l'enfant**, et la **portee d'une
  clause que j'ai ecrite**.
- **Ce document ne prononce aucun Done** et n'ecrit ni backlog ni garde-fou.

---

## 1. Le titre du jeu d'additions - **"Les petites additions"**

### 1.1 Ce que j'ai regarde avant de choisir

**Les quarante titres du catalogue servi**, releves un par un dans
`adapters/adapter-content/src/main/resources/catalogue.yaml`, et non l'idee que j'en avais. Trois
registres coexistent :

| Registre | Compte | Exemples |
|---|---|---|
| **Nominal** (article + nom) | **environ 29 sur 40** | *Les petites soustractions*, *Les complements a 10*, *La monnaie*, *Le bon son*, *Les figures* |
| Imperatif | 7 | *Compare les nombres*, *Range les nombres*, *Ecris le complement a 10*, *Ecris le mot dicte*, *Lis et comprends* |
| Interrogatif | 3 | *Nom ou verbe ?*, *Un ou plusieurs ?*, *Majuscule ou point ?* |

### 1.2 Ce que ce releve m'a appris et que je n'avais pas prevu

**L'imperatif n'est pas libre dans ce catalogue : il fait deja un travail.** Le paquet de saisie des
complements s'appelle **"Ecris le complement a 10"** et son jumeau en choix **"Les complements a
10"**. Le second jeu de saisie s'appelle **"Ecris le mot dicte"**. **Le verbe a l'imperatif est ce
qui distingue le jumeau de saisie de son jumeau de choix**, et c'est la seule marque qui les
distingue a l'ecran d'accueil.

Ce n'est pas une regle absolue - *Compare les nombres* et *Range les nombres* sont des jeux de
choix - mais **le motif "Ecris ..." = saisie est net et il porte deja de l'information pour
l'enfant**. Poser un imperatif sur un jeu de choix affaiblit un signal qui fonctionne.

### 1.3 Mon verdict, et les motifs

> **"Les petites additions".** C'est la recommandation de `camille-po` et je la suis, avec deux
> motifs qu'elle n'avait pas et un qu'elle avait.

1. **Cela restitue une paire que le catalogue a cassee.** Deux jeux, meme domaine, meme mecanique,
   meme borne - *dans la limite de 10* dans les deux paquets -, et *"Les petites soustractions"*
   existe deja, servi et valide. **Un enfant de six ans qui parcourt une liste reconnait des paires
   avant de lire des mots.** *Calcul CP* et *Les petites soustractions* ne se ressemblent en rien,
   et rien a l'ecran ne dit qu'ils vont ensemble.
2. **C'est le registre de la maison** : 29 titres sur 40.
3. **L'imperatif est deja pris**, section 1.2.

**Et "Calcul CP" echoue sur deux points, pas un seul.** Le niveau, que l'utilisatrice a releve et
qu'aucun des trente-neuf autres titres ne porte - un enfant n'a pas besoin qu'on lui rappelle sa
classe pour jouer. **Mais aussi la sous-specification, que personne n'a nommee** : *calcul* couvre
l'addition **et** la soustraction, alors que le jeu ne fait que des additions et que le jeu frere
est nomme par son operation. Le titre promet plus large que le contenu. Retirer le *"CP"* sans
toucher au reste aurait laisse ce second defaut en place.

### 1.4 Pourquoi je n'ai pas pris "Additionne jusqu'a 10"

Le titre est juste, lisible, et le registre a des precedents : ce n'est pas un mauvais titre. Trois
raisons de ne pas le prendre, la troisieme etant la plus solide.

- Il pose un imperatif sur un jeu de choix, section 1.2.
- Il casse la paire avec *"Les petites soustractions"*, section 1.3.
- **Il fige le titre sur une borne que la competence annonce comme provisoire.** La ligne de
  referentiel dit *"additions dans la limite de 10 **(puis 20)**"*, et ma reserve du 2026-08-06 dit
  que la borne 20 reste hors du perimetre de ce jeu et demandera une autre story. **Le jour ou cette
  borne bouge, "Additionne jusqu'a 10" devient faux et doit etre renomme.** *"Les petites
  soustractions"* a evite ce piege en disant *petites* plutot qu'un nombre ; le jeu frere doit
  l'eviter pareil.

### 1.5 Ce que je verifie et ce que je ne verifie pas

**Verifie par moi** : le vocabulaire (*addition* est le mot du programme de CP et celui de la
maitresse), la longueur (trois mots, lisibles par un CP en une fixation), l'absence de niveau,
l'exactitude vis-a-vis du contenu (le paquet ne sert que des additions bornees a 10).

**Pas par moi** : l'orthographe, l'accentuation et la ponctuation de ce titre appartiennent au
`relecteur-pedagogique` (`DEC-0002` amendement du 2026-08-04, `DEC-0056`), y compris parce que
c'est un texte d'enfant.

**Ce que ce changement n'est pas** : ce n'est pas un changement de contenu. Aucun item ne bouge,
aucune illustration, aucune reponse. **Il ne me coute rien contre ma borne** et ne demande pas de
rejouer le paquet - mon Done du 2026-08-06 sur `bandit-manchot-additions-cp` porte sur les items et
il n'est pas touche. **Je verifie le titre a l'ecran d'accueil, ce qui est un regard, pas un
audit.**

---

## 2. Ma clause sur la couleur - **la lecture d'`ines-design` est la bonne**

### 2.1 Ce que ma clause dit, mot pour mot

Je suis allee la relire plutot que de me souvenir de ce que je voulais dire.

> **`FIG-3`** (2026-08-24, reconduite sans changement le 2026-08-31) - *"la couleur ne **distingue**
> jamais deux formes entre elles a l'interieur d'un meme item, et aucune consigne ne la mentionne."*
>
> **`HOR-3`**, qui en donne le test operatoire - *"Meme motif que `FIG-3` et `QUA-5` : **une image
> lue en niveaux de gris donne la meme reponse**."*

> **C'est une clause de discrimination, pas une clause de remplissage.** Elle interdit a la couleur
> de **decider**. Elle n'a jamais interdit a une surface d'etre remplie, et le mot *remplissage*
> n'y figure pas.

**`ines-design` lit ma clause correctement, et je le prononce sans reserve.**

### 2.2 Le garde-fou est plus large que ma clause, et son message le montre lui-meme

Le garde-fou refuse tout `fill` autre que `none` sous `static/illustrations/`, avec ce message :

> *"Une figure lue en niveaux de gris doit donner la meme reponse, et un cercle rempli n'est plus un
> cercle mais un disque (`FIG-3`, `FIG-4`)"*

**Il cite deux clauses et n'en applique correctement aucune des deux a cette population.**

- **`FIG-3`** est une clause de discrimination. Le garde-fou l'a transformee en interdiction de
  remplissage, ce qui est **plus large que ce qu'elle dit**.
- **`FIG-4`** - *"le paquet ne confond ni cercle et disque, ni figure plane et solide"* - est une
  clause **du paquet des figures planes**, ou *cercle* est une reponse attendue. Le garde-fou l'a
  appliquee a **toutes** les illustrations du produit, y compris a un paquet ou aucun item ne
  demande de nommer une figure. **C'est une clause de perimetre etendue hors de son perimetre.**

**Ce sont mes sixieme et septieme clauses fausses en extension en trois jours**, et c'est encore un
tiers qui les a trouvees. Je le note plutot que de le passer.

### 2.3 Je corrige la formulation d'`ines-design` tout en retenant sa conclusion

Sa conclusion est juste. **Sa formulation ne l'est pas tout a fait, et si elle etait recopiee dans
un garde-fou elle y serait fausse** - c'est exactement la famille de defaut que ce depot a deja
payee.

Elle ecrit que le remplissage est *"uniforme et invariant selon la valeur"*. **Verifie sur le
paquet** : `MON-2` declare que les valeurs montrees sont **1 et 2 euros en pieces, 5, 10 et 20 euros
en billets**. Le remplissage propose est **par type d'objet**, et **le type est correle a l'etendue
de valeur**. Il n'est donc pas invariant selon la valeur ; il est invariant **selon le type**.

**Cela ne change pas le verdict, et voici pourquoi - c'est le point qui compte.** Ce que ma clause
exige n'est pas que la couleur ne porte aucune information : c'est que **la reponse soit inchangee
en niveaux de gris**. Or les douze reponses de ce paquet sont des **sommes en euros**, jamais
*"une piece"* ou *"un billet"* : pour repondre, il faut lire les chiffres graves, un par un, et les
ajouter. Aucune couleur ne dispense de ce geste.

**Et la correlation qui reste n'est pas une fuite, c'est la competence.** Qu'une piece porte une
petite valeur et un billet une grande **est ce que la ligne demande d'apprendre** - *"reconnaitre et
utiliser la monnaie"*. Une image qui l'enseigne fait son travail.

### 2.4 Mes conditions

> **`COU-1`** - Le remplissage est **uniforme par type d'objet** et **identique pour tous les objets
> du meme type dans tout le paquet**. Deux pieces de valeurs differentes ont le meme remplissage.
> *C'est la formulation exacte, celle qui peut etre recopiee dans un garde-fou sans y devenir
> fausse.*
>
> **`COU-2`** - **Le test reste le mien et il ne bouge pas : rendu en niveaux de gris, chaque item
> donne la meme reponse.** C'est le critere que `FIG-3` et `HOR-3` portent depuis s25, il vaut pour
> **tout** paquet illustre, present et futur, et c'est lui - et non l'absence de remplissage - qu'un
> garde-fou doit executer s'il se retrecit.
>
> **`COU-3` - Le chiffre grave doit rester nettement lisible sur la surface remplie.** *Motif, et il
> est a moi seule* : `MON-1` pose que **la valeur ne se lit que sur l'image**. Si le trait sombre du
> chiffre se rapproche en clarte du fond qui le porte, le chiffre s'efface - et **l'item devient
> irrepondable, pas seulement moins joli**. Un enfant echouerait alors sur un defaut de rendu. Je ne
> prescris ni teinte ni rapport de contraste : c'est `ines-design` et `theo-archi`. **Je verifie a
> l'oeil, item par item, et je refuse si le chiffre se lit mal.**
>
> **`COU-4` - `cp-figures-planes` n'est pas touche, et le motif d'`ines-design` est le bon.**
> Remplir un cercle en fait un disque : c'est `FIG-4` mot pour mot, sur le paquet pour lequel je
> l'ai ecrite, et elle l'a retrouvee seule en faisant rendre un carre et un cercle. **Son refus
> d'etendre son remede est correct et je le confirme.**

**Ce que je ne tranche pas** : retrecir le garde-fou, et comment, est une decision d'architecture
(`theo-archi`). Je dis seulement que **ma clause ne s'oppose pas au correctif** et que si le
garde-fou se retrecit, c'est `COU-2` qu'il doit executer, pas l'absence de `fill`.

### 2.5 Le rejeu m'appartient, et je le price

**`ines-design` a raison de le signaler d'elle-meme, et elle a raison sur le fond** : `cp-monnaie`
porte mon Done du 2026-09-01, prononce sur **les dessins que j'ai vus**. Changer les dessins change
ce sur quoi j'ai prononce. **Le paquet n'est pas Done tant que je ne l'ai pas rejoue.**

**Ce que je rejoue, borne d'avance pour que personne n'ait a me le redemander** : les **douze
items** et les illustrations qu'ils referencent, sur trois proprietes et trois seulement -
`COU-3` (le chiffre se lit), `COU-2` (rendu en niveaux de gris, meme reponse), et le fait que les
objets se lisent **comme de la monnaie**, qui est le defaut d'origine releve par l'utilisatrice.

**Ce que je ne rejoue pas** : l'arithmetique des sommes, les distracteurs, les enonces, `MON-1` a
`MON-5`, `CON-1` a `CON-4`. **Rien de tout cela ne change**, et rejouer ce qui n'a pas bouge serait
depenser ma borne pour rien - c'est le regime que `DEC-0060` a arbitre.

> **Prix : 0,5 contre ma borne de 5.** Meme unite que la demi-passe de `CON-4` a s25.
>
> **Consequence sur s27, dite maintenant** : le sprint etait a **4,0** (voyelles 1 + questionner le
> monde 1,5 + histoire-geographie 1,5). Avec ce rejeu, **il est a 4,5**. **Il tient, et c'est la
> derniere chose qui tient.** Tout ajout supplementaire me fait declarer par ecrit, comme a s25 et
> a s26.

**Une chose que je refuse d'avance, pour qu'elle ne soit pas tentee** : que le correctif soit livre
et repute Done parce que la CI est verte et que le dessin est plus joli. **Quatre defauts visibles
par un enfant ont deja traverse une CI entierement verte sur ce produit.** Aucun garde-fou ne sait
si une piece ressemble a une piece.

---

## 3. Ce que je route

| Fait | A qui | Ce qu'il en fait |
|---|---|---|
| **Titre retenu : "Les petites additions"** (1.3) | `camille-po` | Une story de catalogue. Aucun item ne bouge, aucun rejeu du paquet |
| *Calcul CP* echouait sur **deux** points - le niveau **et** la sous-specification (1.3) | `camille-po` | Retirer le seul *"CP"* n'aurait pas suffi |
| L'imperatif marque deja la saisie dans ce catalogue (1.2) | `camille-po`, `ines-design` | Fait de catalogue mesure, utile aux prochains titres |
| **Ma clause n'interdit pas le remplissage. La lecture d'`ines-design` est la bonne** (2.1) | `ines-design`, `theo-archi` | Le correctif n'est pas bloque par la porte pedagogique |
| Le garde-fou etend `FIG-3` et applique `FIG-4` hors de son paquet (2.2) | `theo-archi` | S'il se retrecit, c'est `COU-2` qu'il execute, pas l'absence de `fill` |
| **Correction de formulation** : invariant **par type**, pas *"selon la valeur"* (2.3) | `ines-design`, `theo-archi` | A ne pas recopier sous sa forme approchee dans un garde-fou |
| `COU-1` a `COU-4` | `ines-design`, `camille-po` | Conditions d'ecriture du correctif |
| **`COU-3` est de moi seule** : le chiffre efface rend l'item irrepondable | `ines-design` | Aucun automate ne le voit |
| `cp-figures-planes` non touche, motif d'`ines-design` confirme (`COU-4`) | `ines-design` | Son refus d'etendre est correct |
| **Rejeu de `cp-monnaie` : 0,5. s27 passe de 4,0 a 4,5, et c'est plein** (2.5) | `camille-po`, `sofia-sm` | Fait d'arithmetique, pas un avis |
