# ADR-0099 - Un document tableau est une composante structuree d'item, et une ligne y porte exactement les cellules de son en-tete

- **Statut** : Accepte
- **Date** : 2026-09-23
- **Sprint** : s40
- **Auteur** : theo-archi
- **Route par** : `camille-po`, `DEC-0094`, sur le signalement de mon propre avis de faisabilite de
  s40 (`squad/sprints/s40-couverture-maximale-emc-histoire-sciences/avis-faisabilite.md`, section 6)

## Contexte

`SCI-CM1-20` demande d'extraire une information d'un document. L'arbitrage prealable de
`nadia-prof` du 2026-09-03 ferme cette ligne sur la mention *"document textuel et tableau seuls ; le
schema et le graphique restent non couverts"*, et `DEC-0094` tranche que **les deux formes sont
exigees** : le texte seul ne ferme pas la ligne avec cette mention-la.

Ma section 6 a mesure trois faits sur l'arbre de `7c10add1`, et ce sont eux qui font qu'il y a une
decision a prendre plutot qu'un paquet a ecrire :

1. **aucune cle `tableau` n'existe** dans les paquets servis ni dans le schema, et `ADR-0070`
   interdit qu'une cle de paquet servi soit ignoree du chargeur ;
2. **`texteSupport` est rendu par un `<p th:text>`** : `th:text` echappe, HTML replie les blancs, et
   un texte multiligne y devient une seule ligne courante ;
3. **`Question` ne portait aucune composante structuree** : ses onze composantes etaient des `String`
   et des `Optional<String>`.

Le produit savait donc afficher un paragraphe, pas une grille. Et la voie qui semble la moins
chere - aligner des colonnes a l'espace dans `texteSupport` - est fermee deux fois : les blancs sont
replies, et meme s'ils ne l'etaient pas, `ADR-0092` a deja tranche qu'une mise en forme qui repose
sur la police servie n'est pas gardable, *"la police servie est celle de l'appareil"*.

**Ce que ce document ne tranche pas** : ce qu'un contenu d'extraction doit dire, combien d'items il
porte, et si la ligne ferme. C'est `nadia-prof` seule. Aucune ligne de contenu n'est ecrite ici
(`DEC-0093`, section 5).

## Decision

### 1. La grille est une composante d'item declaree, sous la cle `tableauSupport`

`Question` recoit une **septieme composante optionnelle**, `Optional<TableauSupport>`, sur le patron
exact des six precedentes (`ADR-0024`, `ADR-0050`, `ADR-0062`, `ADR-0073`, `ADR-0075`) : la mecanique
la transporte sans jamais la lire, un paquet qui ne montre aucune grille ne remplit aucun champ
factice, et la facon dont l'enfant repond ne change pas - critere d'`ADR-0058`, donc ce n'est ni une
forme d'item de plus ni une mecanique de plus.

Le type `TableauSupport` (`domain/src/main/java/com/recreo/domain/content/TableauSupport.java`) porte
deux champs : `enTete`, les intitules de colonnes, et `lignes`, les lignes de corps. La cle YAML a
exactement ces deux sous-cles.

```yaml
tableauSupport:
  enTete: ["Arbre", "Hauteur"]
  lignes:
    - ["chene", "25 metres"]
    - ["hetre", "30 metres"]
```

**Ce bloc est lu strictement, a la difference de tout le reste de la fabrique de questions**, et le
motif est un trou mesure et non un gout : le balayage d'`ADR-0070` ne descend dans un item que par
une *liste de correspondances*, jamais par une correspondance posee directement dessus - c'est ce qui
l'empeche de juger les cles d'auteur de `relationsDistracteurs` comme du schema. Une sous-cle de
`tableauSupport` mal orthographiee tombait donc dans ce trou : ni lue, ni signalee. Elle est
desormais **refusee au chargement, en nommant l'item et la sous-cle**. C'est la dette d'`ADR-0024`
fermee sur ce bloc et sur lui seul - la ou aucun autre garde-fou ne pouvait la rattraper.

**`tableauSupport` ne remplace pas `texteSupport`** et ne s'y substitue pas : un item porte l'un,
l'autre, ou les deux. Ce ne sont pas deux ecritures du meme objet - un paragraphe n'a ni colonnes ni
intitules.

### 2. Une ligne porte exactement autant de cellules que son en-tete, et la largeur attendue est celle de cet en-tete

C'est `ADR-0087` transpose du markdown du depot au contenu servi : la largeur est **derivee de
l'en-tete du tableau juge**, jamais ecrite dans une regle ; la comparaison est interne au tableau,
comme celle d'`ADR-0045`.

**Une difference avec `ADR-0087`, et elle est de fait, pas de doctrine.** `ADR-0087` n'exige que
*"pas plus"* : le depot portait 9 lignes en deficit et un deficit markdown ne fait perdre aucun
texte, si bien qu'un cliquet aurait coute un diff a chaque correction pour garder une propriete sans
objet. Ici, **la population de grilles servies est nulle** au jour de cette decision : il n'y a aucune
dette a exempter, donc aucun cliquet a tenir, et l'egalite se decide au moment ou elle est gratuite.
Elle interdit les deux defauts a la fois - une cellule en trop n'est affichee par aucun rendu, une
cellule en moins laisse une valeur sans son intitule de colonne dans ce qu'une lecture d'ecran
annonce.

S'y ajoute le refus d'une **cellule vide**, en en-tete comme en corps : un `<th>` vide ne donne aucun
intitule a sa colonne, et rien ne distingue, dans un fichier, l'absence voulue de l'oubli.

**Ces invariants vivent dans le constructeur du record**, meme geste que la clause 5 d'`ADR-0073` :
ils valent pour tout site de construction, y compris ceux qui n'ont jamais lu un fichier. Une grille
mal formee ne se charge pas ; elle ne parvient donc jamais a un enfant.

### 3. Le rendu est un `<table>` dont les intitules sont des `<th scope="col">`

Le bloc vit dans `fragments/manche.html`, a l'emplacement du texte-support - *"la matiere sur
laquelle porte la question"* (`ADR-0050`) - avec le meme `th:if`, donc jamais une table vide
(`ADR-0020`).

Trois points qui ne sont pas de la mise en forme :

- **la mise en colonnes est faite par le moteur de rendu**, jamais par la police servie
  (`ADR-0092`) ;
- **`scope="col"` est la seule chose qui, dans un document servi, rattache une cellule a l'intitule
  de sa colonne** pour une lecture d'ecran. Un tableau dont l'en-tete est rendu en `<td>` porte
  exactement les memes caracteres et n'annonce plus rien de ce rattachement ;
- **aucun `<caption>`** : le gabarit ne peut en ecrire qu'un seul, identique pour toutes les grilles
  de tous les paquets, donc qui n'en decrirait aucune. C'est le motif exact du texte alternatif d'une
  illustration, qui vient du modele et jamais du gabarit (`ADR-0073`, clause 5). Si une grille doit un
  jour porter un titre propre, il sera declare par le contenu, et c'est `nadia-prof` qui dira s'il
  est du.

La feuille de style pose la taille du texte au plancher de lisibilite deja en vigueur (1,25rem, celui
de `.texte-support` et de `.consigne`), des bordures separatrices et un alignement a gauche des deux
cotes, intitules compris - un intitule centre au-dessus d'une colonne alignee a gauche cesse de
pointer sa colonne des que les largeurs different. Aucune valeur en pixels, aucune largeur de colonne
imposee : c'est le contenu qui decide combien il y a de colonnes.

### 4. Une rangee de tableau est un fragment de support, et `ADR-0072` la juge comme une phrase

**C'est la decision qui empeche cet ADR d'etre une porte ouverte.** `ADR-0072` tient qu'*aucune
strategie qui ne lit qu'une seule phrase du support ne gagne la majorite des items d'un paquet*. Son
domaine etait *"l'item porte un texte support"*. Sans cette decision-ci, un paquet dont les items ne
portent qu'une grille **sortait entierement de ce domaine** : la strategie "ne lire qu'une rangee et
presser la seule proposition qui s'y trouve" aurait pu gagner tous les items, sur une suite verte.

Le domaine devient donc *"l'item porte un support"* - un texte, une grille, ou les deux - et le
fragment qu'une strategie aveugle lit est **une phrase de texte ou une rangee de tableau**, en-tete
compris. Le seuil de s12 n'est pas renegocie, le juge et sa comparaison stricte ne changent pas.

**Verification demandee par `DEC-0094` : ma conception ne contredit pas `ADR-0072`, elle l'etend.**
Et je corrige au passage ce que mon propre avis de faisabilite disait de trop en section 6.4. J'y
ecrivais que *"la forme tableau est plus sure au regard d'`ADR-0072` que la forme prose"*, au motif
qu'une grille porte toutes ses valeurs sur la meme grille. **C'est vrai de la grille entiere et faux
de la rangee**, qui est l'unite qu'un enfant isole reellement : une rangee porte le plus souvent la
valeur cherchee et aucune autre proposition, donc elle est decisive au sens exact du juge. La forme
tableau n'est pas plus sure ; elle est **soumise a la meme contrainte**, qui se paie en faisant varier
la rangee interrogee d'un item a l'autre.

### 5. La grille entre dans l'identite d'ecran d'`ADR-0032`

Meme motif que `texteSupport` et qu'`illustration` : un paquet d'extraction pose la meme question sur
des grilles differentes - meme enonce, memes propositions, une grille par item. La classer hors de
l'identite d'ecran refuserait tous les items sauf le premier comme des copies, alors que l'enfant lit
une grille differente a chaque fois. L'egalite de `TableauSupport` est celle d'un record, donc
profonde : deux grilles qui ne different que par une cellule sont deux ecrans differents.

### 6. Le perimetre declare d'un garde-fou voisin devient son perimetre execute

`ChaqueCleFacultativeDItemEstLueParLaFabriqueDeSonTypeTest` (`ADR-0080`, garde-fou 6) compare la
valeur ecrite dans le YAML a la valeur chargee, pour toute composante d'item de type `Optional`. Sa
Javadoc declare depuis toujours que les composantes *"d'un autre type que `Optional<String>`"* sont
hors de son perimetre - **mais rien ne l'executait** : le critere s'arretait a `Optional`, ce qui
etait exact tant que toutes l'etaient de `String`.

`tableauSupport` est le premier `Optional` d'une structure. Compare comme du texte, il aurait produit
un **faux rouge le jour du premier paquet a grille**, sa valeur chargee et sa valeur ecrite n'ayant
aucune raison de partager une ecriture. Le critere lit desormais le type porte. C'est un defaut paye
maintenant plutot que decouvert par le developpeur du premier paquet, sur un message qui ne l'aurait
pas aide.

## Regle executable

**Aucune regle ArchUnit neuve, et le compte du depot est inchange : 36 `static final ArchRule`,
35 `@ArchTest`.** Methode, celle d'`ADR-0095` et d'`ADR-0098` : occurrences litterales de ces deux
chaines sous `src/test/java` des cinq modules declares au pom agregateur, `target/` exclu ; l'ecart
d'une unite a la cause qu'`ADR-0094` a nommee, une `ArchRule` du depot n'etant pas annotee
`@ArchTest`. Les objets juges ici sont un YAML, un record du domaine et un document HTML servi -
qu'ArchUnit ne modelise pas. C'est la meme famille qu'`ADR-0080`, `ADR-0087` et `ADR-0094`.

**Une regle ArchUnit existante a mordu cette conception a l'ecriture, et elle avait raison.** La
clause 11 de `DecouplageMecaniqueContenuTest` (`ADR-0080`, decision 6) refuse a un record des deux
paquets de contrat toute methode ecrite a la main. Ma premiere ecriture de `TableauSupport` portait
une methode de lecture qui reunissait les cellules d'une rangee, et une methode d'appoint de
validation : **deux violations, nommees par la regle avant tout commit**. Les quatre verifications
vivent desormais dans le constructeur, et la reunion des cellules est passee la ou elle est utilisee,
dans le juge d'`ADR-0072`. Un comportement pose sur un record du contrat est du comportement loge la
ou aucune clause de decouplage ne le regarde ; c'est exactement ce que cette regle-la existe pour
empecher, et le noter ici vaut mieux que de l'avoir oublie.

Quatre garde-fous portent cette decision.

| Garde-fou | Fichier | Ce qu'il tient |
|---|---|---|
| L'invariant de largeur et de cellule | `domain/src/test/java/com/recreo/domain/content/TableauSupportTest.java` | une ligne en surplus, une ligne en deficit, une cellule vide, une cellule absente, une grille a une colonne, une grille sans corps ; et que la largeur attendue soit **celle de l'en-tete juge**, verifiee sur deux largeurs differentes |
| La lecture stricte du bloc | `adapters/adapter-content/src/test/java/com/recreo/adapter/content/LaGrilleDUnItemEstLueOuLeChargementEchoueTest.java` | la grille declaree est chargee ; une sous-cle inconnue, une sous-cle manquante ou une forme inattendue font **echouer** le chargement en nommant l'item ; un item sans grille n'en porte aucune |
| La strategie aveugle sur une rangee | `adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest.java` | decision 4 : le domaine et le fragment etendus, avec un jeu fautif (60 % sur une rangee fixe) et son jumeau vert (40 %, meme materiau, seule la rangee interrogee varie) |
| L'arrivee a l'ecran | `bootstrap/src/test/java/com/recreo/bootstrap/rendu/LeTableauSupportDUnItemAtteintLEcranDeMancheEnGrilleTest.java` | decision 3 : une grille est rendue **si et seulement si** l'item en declare une, en `<table>`, ses intitules en `<th scope="col">` exactement egaux a l'en-tete declare, ses lignes exactement egales aux lignes declarees |

`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` porte la decision 5, et sa clause de classement
l'a exigee : **elle a rougi des l'ajout de la composante**, en refusant qu'une composante d'item ne
soit ni classee dans l'identite d'ecran ni classee hors d'elle avec son motif. C'est un garde-fou
ecrit en s17 qui a impose une decision en s40 sans que personne ait a y penser.

### Ce que ces garde-fous interdisent reellement

- **Qu'une grille declaree n'atteigne jamais l'ecran.** Une cle lue en silence et jamais rendue est
  le defaut nomme par la Javadoc de `QuestionContentSourceFactory` elle-meme ; il est ici rouge.
- **Qu'une grille soit rendue en perdant son rattachement de colonne.** La mutation qui compte le
  plus du dernier garde-fou transforme les `<th scope="col">` en `<td>` : le document porte alors
  exactement les memes caracteres, un oeil n'y voit qu'un tableau un peu moins gras, et plus rien
  n'annonce sous quel intitule se lit une valeur. **Une assertion sur le texte rendu resterait verte
  ici.**
- **Qu'un paquet de grilles se gagne sans lire la question.** Six items sur dix interrogeant la meme
  rangee suffisent a le faire rougir.
- **Qu'une sous-cle mal orthographiee ampute un item en silence.**
- **Qu'une ligne ne corresponde pas a son en-tete**, dans les deux sens.

### Ce qu'ils ne voient pas, et il faut le lire avant de s'y fier

- **Le materiau de la moitie *tableau* est de ma main, et c'est la faiblesse de cet ADR.** La lecon
  de s05 veut qu'une fixture ecrite par l'auteur d'une regle ressemble a ce qu'il imagine, pas a ce
  que le monde ecrit - et aucun paquet ne declarait de grille le 2026-09-23, donc il n'existait aucun
  materiau reel a prendre. Ce qui compense, et ce n'est pas rien : **le juge est le meme** que celui
  qui s'execute sur les paquets a texte-support servis et sur le paquet de `lea-dev` avant et apres
  sa correction. C'est le juge qui est eprouve sur du reel ; seule l'entree tableau ne l'est pas.
  **Declencheur ecrit** : le premier paquet a grille qui passe la porte pedagogique rend ce materiau
  disponible, et la morsure devra etre reprise sur lui si elle y trouve un cas que ma fixture ne
  porte pas.
- **Le cote "une grille arrive a l'ecran" juge aujourd'hui un item synthetique et zero item servi.**
  La propriete jugee est celle du **gabarit**, qui est le meme pour tout contenu ; un paquet servi y
  entre de lui-meme le jour ou il existe, sans qu'une ligne soit ecrite - les paquets porteurs d'une
  grille sont decouverts par balayage du repertoire de contenu, jamais nommes.
- **Rien ne juge ce qu'une cellule contient.** Une grille bien formee peut mentir, et la justesse
  d'un tableau de sciences est a `nadia-prof`.
- ~~**`AucunTexteServiNEnchasseLaReponseDansUnePhraseTest` (`ADR-0083`) ne lit pas les cellules.** Son
  objet est l'ecran de revelation et l'ecran de fin de partie, ou aucune grille n'est rendue.~~
  **Faux, corrige le jour meme - voir l'amendement du 2026-09-23 au bas de cet ADR.**
- **La mention de couverture partielle de `SCI-CM1-20`** - *"document textuel et tableau seuls"* - reste
  gardee par aucun automate. C'est la dette que ma section 4.3 a nommee et qui n'est pas payee ici :
  elle porte sur au moins trois lignes deja servies, et la trancher dans cet ADR-ci serait la trancher
  a la sauvette.

## Consequences

### Bonnes

- `SCI-CM1-20` cesse d'etre infaisable sur sa moitie tableau, sans qu'aucune mecanique soit touchee
  ni qu'aucun contenu deja servi change d'un caractere.
- `ADR-0072` ne perd pas de terrain en gagnant une forme de support : la forme neuve entre dans son
  domaine dans le meme geste qu'elle est creee, au lieu d'ouvrir un angle mort a decouvrir plus tard.
- Un defaut latent d'un garde-fou voisin (decision 6) est paye avant d'avoir produit un faux rouge.
- La grille est annoncee a une lecture d'ecran comme une grille, ce qu'aucun alignement a l'espace ne
  permettait.

### Mauvaises

- **`Question` porte douze composantes.** Le patron des champs optionnels arrive a un point ou il
  faudra se demander si un item ne porte pas plutot *un support*, dont le texte et la grille seraient
  deux formes. **Je ne le fais pas ici** : deux formes ne justifient pas une abstraction, et je
  l'ecrirais sur un usage imagine. **Ce qui me ferait changer d'avis** : une troisieme forme de
  support.
- **La premiere composante non textuelle du contrat de contenu.** Tout garde-fou qui supposait
  implicitement *"toute composante optionnelle est du texte"* est a verifier ; un l'etait, il est
  corrige (decision 6), et je ne peux pas promettre qu'il etait le seul - je peux seulement dire que
  la suite complete est verte apres le changement.
- **Un cout de rendu s'ajoute a l'ecran de manche** : un tableau prend de la hauteur, et le budget de
  pli est hors de ma main (`DEC-0051`, `DEC-0053`). Si une grille de sciences chasse les propositions
  sous le pli, c'est `ines-design` et `nadia-prof` qui trancheront, pas cet ADR.
- **Le seuil d'`ADR-0072` s'applique maintenant a une forme ou il est plus serre qu'il n'y parait**
  (section 4, correction de mon avis). Un auteur de contenu qui l'ignore ecrira un paquet rouge ; le
  message le nomme, mais le cout est reel.

### Ce qui me ferait changer d'avis plus tard

- Si un contenu demandait des **en-tetes de ligne** (premiere colonne servant d'intitule), la
  structure s'etendrait d'un champ et le gabarit d'un `scope="row"` - et la decision 2 vaudrait
  telle quelle.
- Si une grille devait porter un titre propre, il serait **declare par le contenu**, jamais ecrit
  dans le gabarit (decision 3).
- Si une seconde mecanique devait lire une grille plutot que la transporter, le champ cesserait
  d'etre une simple composante d'item et `ADR-0058` serait a rouvrir.

## Alternatives considerees

Les trois voies sont celles que mon avis de faisabilite a nommees sans choisir (section 6.3).

1. **V1 - livrer la moitie texte seule, et laisser le tableau a un lot ulterieur.** Ecartee par
   `DEC-0094`, et c'etait a `camille-po` de l'ecarter : reduire le perimetre revenait a modifier une
   mention de couverture fixee par `nadia-prof`, ce qui n'est ni de sa main ni de la mienne.
2. **V3 - un tableau rendu en phrases dans `texteSupport`** (*"le chene mesure 25 metres, le hetre
   30 metres"*). Ecartee : ce n'est pas un tableau. Le critere d'acceptation demande des lignes et
   des colonnes lisibles, et cette voie les supprime. Elle a l'air gratuite, et c'est ce qui la rend
   dangereuse.
3. **Un tableau aligne a l'espace dans `texteSupport`, avec une regle CSS qui preserve les blancs.**
   Ecartee sur un fait deja tranche : `ADR-0092`. La largeur d'une colonne dependrait de la police
   servie, qui est celle de l'appareil, et rien ne serait gardable. Une lecture d'ecran y annoncerait
   une suite de mots, pas une grille.
4. **Un tableau ecrit en markdown dans `texteSupport`, rendu par un convertisseur.** Ecartee deux
   fois : elle fait deviner la structure par le gabarit, la ou `ADR-0062` a pose que c'est le contenu
   qui declare ; et elle ferait entrer une bibliotheque de rendu markdown dans `adapter-web` pour un
   besoin que deux champs couvrent.
5. **Une liste de lignes dont la premiere serait l'en-tete** (`tableauSupport: [[...], [...]]`), sans
   sous-cles. Ecartee : un auteur qui oublie la rangee d'en-tete voit sa premiere ligne de donnees
   rendue en `<th>` **sans qu'aucun message ne le dise**. Deux sous-cles nommees rendent l'oubli
   bruyant, et l'en-tete est precisement ce dont la decision 2 et le rendu accessible ont besoin par
   son nom.
6. **Ecrire la regle de largeur dans un garde-fou de contenu plutot que dans le record.** Ecartee sur
   le motif de la clause 5 d'`ADR-0073` : un invariant pose dans le seul chargeur ne vaut que pour ce
   qui vient d'un fichier, et le depot construit des items en memoire dans plus de cinquante endroits.

## Impact

- `domain/src/main/java/com/recreo/domain/content/TableauSupport.java` : cree.
- `domain/src/main/java/com/recreo/domain/content/Question.java` : douzieme composante et son
  constructeur de commodite.
- `domain/src/main/java/com/recreo/domain/mechanics/banditmanchot/BanditManchotSession.java` : la
  grille de la manche courante, transportee et jamais lue.
- `adapters/adapter-content/src/main/java/com/recreo/adapter/content/QuestionContentSourceFactory.java` :
  la cle et sa lecture stricte.
- `adapters/adapter-web/src/main/java/com/recreo/adapter/web/PartieController.java` et
  `adapters/adapter-web/src/main/resources/templates/fragments/manche.html` : l'attribut de modele et
  le bloc de rendu.
- `adapters/adapter-web/src/main/resources/static/css/recreo.css` : `.tableau-support` et ses
  cellules.
- `domain/src/test/java/com/recreo/domain/content/TableauSupportTest.java`,
  `adapters/adapter-content/src/test/java/com/recreo/adapter/content/LaGrilleDUnItemEstLueOuLeChargementEchoueTest.java`,
  `bootstrap/src/test/java/com/recreo/bootstrap/rendu/LeTableauSupportDUnItemAtteintLEcranDeMancheEnGrilleTest.java` :
  crees.
- `adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest.java` :
  domaine et fragment etendus, deux morsures neuves, deux jeux de contre-exemple crees. **Le nom de
  cette classe n'a pas change** : le renommer deplacerait les repertoires qui portent le materiau reel
  de s24, et ce materiau vaut plus que l'exactitude du nom. Declencheur ecrit : un troisieme genre de
  support.
- `adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest.java` :
  la grille dans l'identite d'ecran, avec son motif.
- `adapters/adapter-content/src/test/java/com/recreo/adapter/content/ChaqueCleFacultativeDItemEstLueParLaFabriqueDeSonTypeTest.java` :
  decision 6.
- `squad/decisions/adr/ADR-index.md` : ligne `ADR-0099` ajoutee.
- `squad/sprints/s40-couverture-maximale-emc-histoire-sciences/avis-faisabilite.md` : correction
  datee, section 4.1 - trois formes fautives epelees, dont deux que le registre d'identifiants de
  `ADR-0085` comptait comme des identifiants non alloues. Elles sont desormais designees, pas epelees,
  comme `ADR-0087` l'avait deja ecrit pour lui-meme.
- **Aucun fichier de contenu, aucune entree de catalogue, aucune affectation** (`DEC-0093`,
  section 5).

## Porte technique

`bash outils/mvn-jdk21.sh -B clean verify` sur la racine du worktree `s40-theo-tableau`, reacteur
complet - donc sans la question du `-am`, qui ne concerne que les mesures partielles. JDK que la
machine virtuelle des tests rapporte : **21.0.10** (`ADR-0071`).

**Les cinq modules passent leurs tests, et une seule clause du depot reste rouge - elle ne vient pas
de ce lot.** `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (`ADR-0085`, `PAR-5`) nomme
quatre identifiants ecrits sans allocation dans
`squad/conformite/ARBITRAGE-PREALABLE-s40-relier-une-oeuvre-a-son-contexte-hda-cm1-05.md`, fichier
ajoute par `b2bae164`. **Mesure et non deduction** : la meme clause a ete executee sur la branche
`round-two` a `1d0fee3e`, sans aucun de mes changements, et elle y est rouge sur les memes quatre
identifiants. La correction appartient a `nadia-prof`, qui les a ecrits - le registre demande la
ligne d'allocation *dans le meme commit que le document qui la definit*, et l'ecrire a sa place
effacerait la trace d'un commit passe sans sa porte. **Un cinquieme cas de la meme mesure etait
mien** - deux formes fautives epelees dans mon avis de faisabilite de s40 - et il est corrige ici.

Les autres jobs de `.github/workflows/ci.yml` - prefixes de commit, chemins de poste de travail,
juges purs des outils - ne sont pas lances par `mvn` et ne sont pas annonces ici.

## Amendement du 2026-09-23 - le premier paquet a grille arrive, et deux de mes garde-fous supposaient qu'il n'arriverait pas

`lea-dev` a ecrit `cm1-sciences-extraire-information` (12 items, six a texte, six a grille) en
suivant cet ADR a la lettre. Le paquet est conforme ; **deux garde-fous ont rougi, et les deux
avaient tort**. Ce n'est pas un alea : c'est le declencheur que cet ADR annoncait lui-meme, en
section *"ce qu'ils ne voient pas"*, sans le payer. Une population vide rend indistinguables deux
ecritures d'une meme clause, et on ne decouvre laquelle a ete ecrite que le jour ou la population
cesse d'etre vide - c'est-a-dire le jour ou le garde-fou sert enfin a quelque chose.

### 1. La moitie *"seulement si"* etait ecrite comme un *"jamais"*

`LeTableauSupportDUnItemAtteintLEcranDeMancheEnGrilleTest.aucunJeuDuCatalogueReelNAfficheDeGrilleSansDeclaration`
comparait l'ecran d'un jeu du catalogue reel a **rien** : toute table trouvee etait un defaut. La
promesse de la decision 3 est *si et seulement si* ; cette clause disait *jamais*. Les deux
coincident exactement tant qu'aucun jeu servi ne declare de grille, et divergent au premier qui en
declare une - qu'elle refusait alors qu'il est conforme.

**Corrige** : la clause compare desormais l'ecran a **l'item que le jeu sert**, par le meme
`manquements` que l'autre cote. Une grille sans declaration reste rouge, une grille conforme est
verte, et la grille servie est reellement **jugee** - intitules et cellules compris - au lieu d'etre
refusee en bloc. **Morsure verifiee sur du materiau reel plutot qu'affirmee** : `scope="col"` retire
du gabarit de production, cette clause rougit sur le paquet servi ; le gabarit restaure, elle est
verte. C'est la premiere fois que ce cote juge autre chose qu'un item de ma main.

### 2. L'ecran de revelation **est** l'ecran de manche, et il porte la grille

J'avais ecrit qu'il n'y avait rien a ajouter aux valeurs de contenu lues par
`AucunTexteServiNEnchasseLaReponseDansUnePhraseTest` (`ADR-0083`), *"l'ecran de revelation et l'ecran
de fin de partie, ou aucune grille n'est rendue"*. **C'est faux, et le fait etait verifiable le jour
ou je l'ai ecrit** : la revelation n'est pas un ecran distinct, c'est l'ecran de manche avec son bloc
de message - donc il porte la grille, comme il porte le texte-support.

Consequence mesuree : chaque `<th>` et chaque `<td>` comptait comme un **mot de la mecanique**, et
comme leur texte varie d'un item a l'autre - c'est leur raison d'etre -, `ADR-0083` les prenait pour
une phrase composee autour de la reponse. **17 griefs sur les six items a grille du paquet de
`lea-dev`.**

**Corrige** : les cellules de la grille rejoignent les valeurs de contenu de ce garde-fou, sur la
ligne exacte ou le texte-support et le texte a voix haute y sont deja. La propriete d'`ADR-0083` est
inchangee et sa morsure, qui porte sur du materiau reel mute, est verte apres correction.

### Ce que je retiens, et qui vaut au-dela de ces deux cas

**Un garde-fou dont la population reelle est vide ne dit pas ce qu'on croit qu'il dit.** Les deux
defauts sont la meme faute sous deux formes : j'ai ecrit ce qui etait vrai de l'etat du depot au lieu
d'ecrire la propriete, parce que rien ne distinguait les deux au moment ou j'ecrivais. La contrepartie
a exiger n'est pas de renoncer a ecrire la regle avant le contenu - `DEC-0094` demandait l'inverse et
elle a bien fait - c'est de **relire chaque clause en se demandant ce qu'elle dira le jour ou sa
population cessera d'etre vide**, et de le dire dans l'ADR comme une clause a verifier, pas comme une
limite a subir.

**Le second defaut n'avait meme pas besoin d'un paquet pour etre trouve** : *"aucune grille n'est
rendue sur l'ecran de revelation"* est un fait sur le gabarit, lisible dans `manche.html`, et je l'ai
affirme sans le lire.

### Impact de cet amendement

- `bootstrap/src/test/java/com/recreo/bootstrap/rendu/LeTableauSupportDUnItemAtteintLEcranDeMancheEnGrilleTest.java` :
  la clause *"seulement si"*, et la mise a l'epreuve qui posait une grille sur un item qui n'en
  declare aucun - elle prenait le premier jeu reel venu, elle prend desormais le premier dont l'item
  servi ne declare pas de grille, decouvert et non suppose.
- `bootstrap/src/test/java/com/recreo/bootstrap/rendu/AucunTexteServiNEnchasseLaReponseDansUnePhraseTest.java` :
  les cellules parmi les valeurs de contenu.
- Aucun changement de production : ni le domaine, ni la fabrique, ni le gabarit, ni la feuille de
  style. **Le contenu de `lea-dev` n'est pas touche d'un caractere** - il etait conforme, ce sont les
  deux clauses qui ne l'etaient pas.
