# ADR-0077 - Un garde-fou execute la clause qu'il cite, et la population d'une clause de paquet se calcule sur le dessin

- **Statut** : Accepte. **Amende le 2026-09-03, meme sprint, meme auteur** : un des deux chiffres que
  cet `ADR` annoncait etait faux - `POIDS_D_UN_DESSIN_MAXIMUM` **bouge**, de 2 685 a 2 775 -, et le
  test de prediction qu'il installait est retire, son objet ayant cesse d'exister. Section
  *Amendement du 2026-09-03*, a lire avant la section sur le cliquet, qui est conservee telle
  qu'ecrite parce qu'elle etait exacte sur les autres points.
- **Statut d'origine** : Accepte. **Sa regle est ecrite, verte et mise a l'epreuve dans le meme commit** -
  `adapters/adapter-web/src/test/java/com/recreo/adapter/web/ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest.java`,
  **17 tests** (13 avant ce lot), dont **4 neufs** : une clause de FIG-4 mordue sur un dessin servi
  reel, une clause de COU-2 mordue sur deux teintes du meme dessin, une **preuve que le
  retrecissement laisse passer** ce qu'il doit laisser passer, et la revision d'`ines-design`
  reproduite sur les vingt-quatre fichiers servis.
- **Sprint** : s27 (planning). **Date** : 2026-09-02. **Auteur** : `theo-archi`.
- **Arbre mesure** : worktree `s27-theo`, branche partie de `round-two` a `9294632`. **JDK
  21.0.10**, celui que la machine virtuelle des tests rapporte d'elle-meme et non celui qu'une
  entree de toolchain declare (`ADR-0071`). Enveloppe : `bash outils/mvn-jdk21.sh`.
- **Encode un arbitrage pedagogique de `nadia-prof` ; il n'en propose pas une variante.**
  L'arbitrage est le sien, du 2026-09-02, `squad/conformite/ARBITRAGE-s27-titre-du-jeu-d-additions-et-portee-de-ma-clause-sur-la-couleur.md`,
  section 2, clauses `COU-1` a `COU-4`. Elle y ecrit que **retrecir le garde-fou, et comment, est
  une decision d'architecture** ; c'est celle-ci.
- **Prolonge** : `ADR-0013` (perimetre derive, garde-fou mis a l'epreuve), `ADR-0036` clause 5 (le
  cliquet de poids), `ADR-0051` (un garde-fou qui n'examine aucun objet le declare), `ADR-0073`
  (perimetre derive, jamais enumere, dans cette meme famille de tests).
- **Ne decide aucun contenu, aucune pedagogie, aucune apparence.** Il ne prononce aucun Done : le
  rejeu de `cp-monnaie` appartient a `nadia-prof`, qui l'a price a 0,5 contre sa borne.

---

## Contexte

### Le fait, et il n'est pas de moi

L'utilisatrice a joue le produit : *"pour la monnaie, pourquoi des images n'ont pas ete utilisees ?"*
Les images sont la et servies. Elles **ne se lisent pas comme de la monnaie** - des cercles au
trait, sans remplissage, avec le chiffre trace en geometrie.

`ines-design` propose `fill="#e8a33d"` sur le cercle exterieur des pieces et `fill="#cfe3ef"` sur le
rectangle exterieur des billets ; le lisere, le cadre et **tous les chiffres restent en contour
pur**, aucun element de texte ajoute. Douze fichiers revises, verifies par elle dans un navigateur,
**non commites** parce qu'appliques aux fichiers suivis ils rendent le garde-fou rouge :

```
Tests run: 13, Failures: 2
- 44x "la surface est remplie de '#e8a33d'/'#cfe3ef' (FIG-3, FIG-4)"
- leCliquetVautExactementCeQueCeLotInstalle: expected 30070, but was 30730
```

### Ce que le garde-fou refusait, et ce que la clause dit

Le garde-fou refusait **tout** `fill` autre que `none` sous `static/illustrations/`, avec ce
message : *"Une figure lue en niveaux de gris doit donner la meme reponse, et un cercle rempli n'est
plus un cercle mais un disque (FIG-3, FIG-4)"*.

`nadia-prof` est allee **relire ses clauses plutot que de se souvenir de ce qu'elle voulait dire** :

> **`FIG-3`** - *"la couleur ne **distingue** jamais deux formes entre elles a l'interieur d'un meme
> item, et aucune consigne ne la mentionne."*
> **`HOR-3`**, son test operatoire - *"une image lue en niveaux de gris donne la meme reponse."*
>
> *"C'est une clause de discrimination, pas une clause de remplissage. Elle interdit a la couleur de
> **decider**. Elle n'a jamais interdit a une surface d'etre remplie, et le mot remplissage n'y
> figure pas."*

Et sur les deux clauses citees ensemble :

> *"Il cite deux clauses et **n'en applique correctement aucune des deux a cette population**.
> `FIG-3` est une clause de discrimination. Le garde-fou l'a transformee en interdiction de
> remplissage, ce qui est plus large que ce qu'elle dit. `FIG-4` - le paquet ne confond ni cercle et
> disque, ni figure plane et solide - est une clause **du paquet des figures planes**, ou cercle est
> une reponse attendue. Le garde-fou l'a appliquee a toutes les illustrations du produit, y compris
> a un paquet ou aucun item ne demande de nommer une figure. C'est une clause de perimetre etendue
> hors de son perimetre."*

**L'attribution, telle qu'elle l'ecrit, et je la verse sans la lisser** :

> *"Ce sont mes sixieme et septieme clauses fausses en extension en trois jours, et c'est encore un
> tiers qui les a trouvees. Je le note plutot que de le passer."*

Ce qu'il faut ajouter et qui est de mon cote : **l'extension n'etait pas dans sa clause, elle etait
dans mon encodage.** Le fichier de garde-fou porte le nom de deux clauses dans un message d'erreur
et refuse quelque chose qu'aucune des deux ne nomme ; le commentaire du jeu fautif commite portait
la meme extension mot pour mot. Un garde-fou qui cite une clause a laquelle il ne se conforme pas
est pire qu'un garde-fou muet : il fait passer une interdiction inventee pour une decision arbitree,
et il a bloque pendant un sprint un correctif demande par l'utilisatrice.

### Le mode de defaillance a ne pas remplacer par son symetrique

Retrecir un garde-fou est la manoeuvre qui produit des verts silencieux. La regle qui suit doit donc
etre jugee sur ce qu'elle **interdit encore** a quelqu'un qui essaierait de mal faire, pas sur le
fait qu'elle laisse passer les douze dessins du jour.

---

## Decision

**Remplacer l'interdiction unique de remplissage par les deux clauses que `nadia-prof` nomme, chacune
sur la population que son texte designe, et calculer cette population sur le materiau.**

1. **`COU-2`, sur toutes les illustrations de contenu.** Deux dessins servis ne deviennent pas le
   meme dessin une fois la couleur retiree. Si cela arrive, ce qui les separe est une teinte seule :
   en niveaux de gris ils sont une seule image, et les deux items qui les montrent cessent d'avoir
   des reponses distinguables. **C'est le test operatoire de `HOR-3`, encode.**
2. **`FIG-4`, sur la population que sa lettre designe.** Un dessin qui ne trace **qu'une seule forme
   rendue** *est* cette forme ; la remplir en fait un autre objet geometrique, et un cercle rempli
   est un disque. Cette clause conserve son interdiction de remplissage, sur ces dessins-la et sur
   eux seuls.
3. **La population de la clause 2 est comptee dans le fichier, jamais nommee.** Formes du langage
   SVG hors `<defs>`, plus les instanciations. Aucun nom de paquet, aucun prefixe de nom de fichier.
4. **La couleur est retiree, jamais convertie en luminance.** Deux teintes de meme clarte sont deja
   indistinguables en gris ; aucune formule de luminance n'a a decider d'un verdict d'architecture.
5. **`COU-1` et `COU-3` ne sont pas encodees, et le motif de chaque abstention est ecrit dans le
   fichier de test** - section *Consequences* ci-dessous.
6. **Le cliquet de poids d'`ADR-0036` clause 5 ne bouge pas dans ce commit**, et la clause 5 est
   completee : **il se deplace dans le commit qui installe les octets**, sur la valeur mesuree sur
   l'arbre de ce commit, jamais recopiee d'une annonce.

### Ce que la clause 2 vaut, mesure et non suppose

Sur les vingt-quatre dessins servis : **les douze figures planes tracent exactement une forme, les
douze scenes de monnaie en tracent de cinq a dix-sept.** La separation est nette, et elle n'est pas
une coincidence de nommage : elle tient a `MON-1`, *"la valeur ne se lit que sur l'image"*. Un objet
de monnaie porte donc toujours son chiffre grave en plus de son contour, et **ne peut pas etre une
forme nue**. Une figure plane sans chiffre, elle, n'est rien d'autre que son contour.

*Methode du chiffre : `formesRendues` applique aux vingt-quatre fichiers de
`adapters/adapter-web/src/main/resources/static/illustrations/`, definitions et commentaires retires,
sur l'arbre `s27-theo` ; c'est le meme calcul que celui du garde-fou, et il est execute par
`premierDessinA` a chaque passage de la suite.*

---

## Regle executable ArchUnit

**Ce n'est pas une `ArchRule`** - la propriete porte sur des fichiers servis, pas sur des types - et
c'est le regime qu'`ADR-0013` prevoit : un garde-fou de balayage qui derive son perimetre et se
prouve mordant. Le fichier :

`adapters/adapter-web/src/test/java/com/recreo/adapter/web/ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest.java`

```java
if (formesRendues(source) == 1) {
    for (String remplissage : valeursDeLAttribut(source, REMPLISSAGE)) {
        if (!"none".equals(remplissage)) {
            fautes.add(nom + " : le dessin ne trace qu'une seule forme rendue, il est"
                    + " donc cette forme, et la remplir de '" + remplissage + "' en"
                    + " fait un autre objet geometrique - un cercle rempli n'est plus"
                    + " un cercle mais un disque (FIG-4). Population derivee du fichier"
                    + " lui-meme, jamais d'un nom de paquet ni d'un prefixe de nom de"
                    + " fichier (ADR-0077)");
        }
    }
}

String deja = squelettes.putIfAbsent(sansCouleur(source), nom);
if (deja != null) {
    fautes.add(nom + " et " + deja + " : les deux dessins sont le meme dessin une fois"
            + " la couleur retiree. Ce qui les distingue est alors une teinte seule, et"
            + " l'item qui montre l'un ou l'autre cesse de donner la meme reponse lu en"
            + " niveaux de gris (COU-2, HOR-3, FIG-3)");
}
```

### Les quatre morsures, toutes sur du materiau reel

| Morsure | Ce qu'elle mute | Verdict attendu |
|---|---|---|
| `unDessinDUneSeuleFormeQuOnRemplitFaitEchouerLeGardeFou` | le premier dessin servi **que le calcul de population designe** - jamais un nom de fichier - recoit un remplissage | rouge, `FIG-4`, *disque* |
| `uneSceneDePlusieursObjetsQuOnRemplitResteAcceptee` | une scene servie reelle recoit un remplissage | **vert** |
| `deuxDessinsQueSeuleLaCouleurDistingueFontEchouerLeGardeFou` | une scene servie remplie, plus la meme scene dans l'autre teinte | rouge, `COU-2`, *niveaux de gris* |
| `lesDouzeDessinsRevisesNeSontRefusesQueParLeCliquetDePoids` | la revision d'`ines-design` reproduite sur les vingt-quatre fichiers servis | **vert sur toutes les clauses**, rouge sur le seul cliquet de poids |

**La deuxieme morsure est celle qui compte pour un retrecissement**, et elle a une raison d'exister
qui n'est pas decorative : sans elle, les deux clauses se contenteraient de refuser moins souvent,
et rien dans la suite ne dirait qu'elles laissent passer le correctif demande.

**Une mutation a ete corrigee avant d'etre commise, et le motif reste ecrit dans le fichier.** La
morsure de `COU-2` ajoutait d'abord un remplissage a une scene qui n'en avait pas, et le garde-fou
restait **vert - a raison**. Une surface remplie et une surface vide ne se distinguent pas par une
teinte : elles se distinguent en gris aussi bien qu'en couleur. Ce qui decide vraiment est **deux
fois la meme image dans deux teintes**, et c'est la forme exacte du defaut de reprise que ce
garde-fou cite depuis s25 - `contenu-repris/associer/cp-formes.yml` associe le glyphe carre orange a
la cible *"rectangle"* et le glyphe carre bleu a la cible *"carre"*. **La regle etait juste, ma
premiere mutation etait fausse** ; c'est la mutation qui a change, pas la regle.

### Le point fixe qui prouve que la reproduction est bien celle d'`ines-design`

Elle a mesure **44 refus de remplissage** et un total de **30 730 octets** contre un cliquet a
**30 070**. La reproduction, ecrite d'apres sa regle et non d'apres ses fichiers, ajoute
**exactement 44 attributs de 15 octets** - la forme exterieure de chacun des 44 objets de monnaie,
le lisere se reconnaissant a son epaisseur de trait propre et le chiffre a ce qu'il est une
instanciation - soit **660 octets**, et l'assertion l'epingle. **Un chiffre annonce par un tiers et
retrouve par le calcul sur le materiau vaut mieux qu'un chiffre recopie.**

---

## Le cliquet de poids : pourquoi il ne bouge pas ici, et ce que cela decide

`ADR-0036` clause 5 pose le cliquet ; le critere de sortie 11 du planning de s25 dit qu'un lot qui
l'agrandit *"le respecte ou le deplace dans le meme commit, avec son motif ecrit et sa valeur
mesuree"*. **Les octets ne sont pas dans cet arbre** : les douze fichiers revises sont chez
`ines-design`, verifies par elle dans un navigateur et pas encore commis. Porter le cliquet a 30 730
sur un arbre qui pese 30 070 poserait **un plafond au-dessus de sa dette** - exactement ce que
`leCliquetVautExactementCeQueCeLotInstalle` refuse, et exactement ce que j'ai ecrit a s26 sous la
forme *"un cliquet dont le plafond depasse la dette n'est pas un cliquet"*. La reciproque de ma
phrase de s26 - *"un plafond qu'on baisse en changeant ce qu'il compte n'est pas un plafond
baisse"* - vaut aussi : un cliquet qu'on releve sans dire pourquoi est un cliquet mort. Ici le
pourquoi est ecrit et le combien est mesure ; c'est le **quand** qui est deplace d'un commit, et le
motif du deplacement est qu'un cliquet exact ne peut pas devancer ses octets.

**Ce que ce commit laisse donc a faire, nommement, plutot que de le laisser trouver** : le commit qui
installe les douze fichiers revises porte `POIDS_TOTAL_MAXIMUM` a la valeur **mesuree sur son propre
arbre fusionne**. La valeur annoncee est 30 730 ; elle n'est pas a recopier. La lecon est deja dans
ce fichier sous une autre forme - *"chaque lot avait mesure 12 et 12 sur sa propre branche, ce qui
etait exact chez lui et faux apres fusion"* - et elle vaut pour un poids comme pour un compte.
**`POIDS_D_UN_DESSIN_MAXIMUM` ne bouge pas** : le plus gros dessin gagne 90 octets au plus et reste
sous 2 685. *(Faux. Cette phrase est conservee parce qu'elle a ete ecrite, et corrigee dans
l'amendement du 2026-09-03 : le plus gros dessin etait **exactement** a la valeur du cliquet, donc
tout octet de plus le deborde. Il est a 2 775.)*

---

## Consequences

### Ce que la regle interdit reellement a quelqu'un qui essaierait de mal faire

- Remplir une figure plane servie : **rouge**, et le message nomme `FIG-4` et le mot *disque*.
- Servir deux dessins que seule une teinte separe - le defaut de `cp-formes.yml` : **rouge**, et le
  message nomme `COU-2` et le test des niveaux de gris.
- Copier un dessin et n'en changer que la couleur pour en faire un second item : **rouge**, meme
  clause. C'est la forme la plus probable du defaut, puisqu'elle est la moins chere a ecrire.

### Ce que la regle ne voit plus, et je le nomme plutot que de le laisser trouver

- **Un remplissage qui varie sans changer la geometrie a l'interieur d'un meme dessin** - deux pieces
  de meme taille, deux teintes - **passe**. La clause 1 compare des dessins entre eux, pas des objets
  a l'interieur d'un dessin. C'est `COU-1`, et `COU-1` n'est pas encodable : le **type** d'un objet
  n'est declare nulle part dans le materiau, et un substitut calculable serait un critere invente.
  Elle reste dans la main d'`ines-design` et de `nadia-prof`.
- **Ce qu'il ne faut surtout pas encoder a la place**, et `nadia-prof` le dit avant que quelqu'un
  l'essaie : la formulation d'`ines-design`, *"remplissage invariant selon la valeur"*, serait
  **fausse** dans un garde-fou. `MON-2` montre 1 et 2 euros en pieces, 5, 10 et 20 en billets : le
  remplissage est invariant **par type**, et le type est correle a l'etendue de valeur. Une regle
  d'invariance selon la valeur rougirait a tort. Et son motif de fond est plus important que la
  correction : *"la correlation qui reste **est la competence**, pas une fuite : qu'une piece porte
  une petite valeur et un billet une grande est ce que reconnaitre la monnaie demande d'apprendre."*
- **`COU-3` n'a aucun garde-fou et n'en aura pas.** *"Le chiffre grave doit rester nettement lisible
  sur la surface remplie"* : si le trait sombre du chiffre se rapproche en clarte du fond qui le
  porte, **l'item devient irrepondable**, et un enfant echouerait sur un defaut de rendu.
  `nadia-prof` verifie a l'oeil, item par item. **Rien dans ce commit ne pretend la couvrir, et la
  Javadoc du garde-fou le dit en toutes lettres** - un garde-fou qui laisserait croire qu'il la
  couvre serait pire que son absence.
- **Une figure plane dessinee en plusieurs traits** - un carre en quatre segments - sortirait de la
  population de la clause 2 et pourrait etre remplie sans rougir. Aucun dessin servi n'est dans ce
  cas et rien n'y pousse ; le jour ou un lot en ecrit un, le garde-fou ne le verra pas, et c'est
  `nadia-prof` qui le verra.
- **Un faux rouge reste possible et il est voulu dans ce sens** : un futur dessin d'une seule forme
  dont le remplissage serait legitime rougira. Un rouge se voit et appelle une decision ; un vert
  silencieux ne se voit pas. **Ce qui me ferait changer d'avis** : deux lots consecutifs bloques par
  ce faux rouge sur des paquets ou nommer la figure n'est pas la reponse. Alors la population se
  derivera du contenu - le paquet dont les reponses nomment l'objet dessine - et la regle changera de
  module pour voir les paquets, comme `LIllustrationDeContenuResoutEtAucuneNeDortTest`. Pas avant :
  ce serait une abstraction pour un usage imagine.

### Ce qui devient possible

Le correctif d'`ines-design` n'est plus bloque par un garde-fou. **Il n'est pas Done pour autant** :
`nadia-prof` rejoue les douze items sur `COU-2`, `COU-3` et le fait que les objets se lisent comme de
la monnaie, et elle a ecrit d'avance ce qu'elle refuse - *"que le correctif soit livre et repute Done
parce que la CI est verte et que le dessin est plus joli. Quatre defauts visibles par un enfant ont
deja traverse une CI entierement verte sur ce produit. Aucun garde-fou ne sait si une piece ressemble
a une piece."*

### Un cout accepte

Le juge prend deux parametres de plus - les deux cliquets - pour qu'une morsure puisse juger un
corpus plus lourd que le cliquet du jour sur **toutes les autres clauses**. Sans cela, la morsure de
la revision d'`ines-design` ne prouverait que le poids. Le cliquet ne perd rien a etre parametre :
`leCliquetVautExactementCeQueCeLotInstalle` continue de l'epingler a la taille exacte de ce qui est
commite, et aucun plafond de confort ne peut entrer.

---

## Alternatives considerees

**Deriver la population de `FIG-4` du nom de fichier** (`figure-*`) - ecartee. C'est une enumeration
deguisee en calcul : elle depend d'une convention que rien ne tient, et un dessin nomme autrement
sortirait de la clause en silence. C'est exactement ce qu'`ADR-0073` refuse dans cette famille de
tests.

**Deriver la population du paquet qui declare l'illustration** - ecartee **pour l'instant**, et c'est
la seule alternative serieuse. Elle serait plus fidele a la lettre de `FIG-4`, qui parle d'un paquet.
Deux couts : le garde-fou devrait changer de module pour voir le contenu, et il faudrait un critere
calculable pour *"le paquet dont les reponses nomment l'objet dessine"* - dont les seuls candidats
sont un vocabulaire de noms de figures ou un motif sur les enonces, c'est-a-dire deux enumerations.
Le calcul sur le dessin ne demande ni l'un ni l'autre et separe le materiau reel sans erreur. La
condition qui ferait basculer ce choix est ecrite ci-dessus.

**Convertir les teintes en luminance et comparer les gris** - ecartee. Elle ferait dependre un
verdict d'architecture d'une formule et d'un arrondi, et elle serait **plus permissive** : deux
teintes de meme clarte passeraient, alors qu'elles sont precisement le cas ou la couleur decide sans
que le gris le montre.

**Garder l'interdiction generale et exempter la monnaie** - ecartee. Une exemption nommee est une
liste, elle grandit, et surtout elle laisserait le garde-fou continuer de citer `FIG-3` pour une
chose que `FIG-3` ne dit pas. Le defaut n'etait pas qu'il manquait une exception : c'est que la
regle encodee n'etait pas la clause.

**Ne rien encoder du tout et retirer la clause de remplissage** - ecartee. `nadia-prof` confirme
`FIG-4` sur `cp-figures-planes` en `COU-4` : *"son refus d'etendre son remede est correct et je le
confirme."* La clause garde son perimetre ; c'est son perimetre qui devait etre calcule.

---

## Amendement du 2026-09-03 - un des deux chiffres que j'avais annonces etait faux, et c'etait le mien

**Ce que j'avais ecrit**, en clair, dans la section sur le cliquet :

> *"`POIDS_D_UN_DESSIN_MAXIMUM` ne bouge pas : le plus gros dessin gagne 90 octets au plus et reste
> sous 2 685."*

**Ce que `ines-design` a mesure sur les fichiers reels avant de les commiter** :

> *"`monnaie-meme-somme-e.svg` (6 objets, +90 octets) tombe a **2 775**, au-dessus de l'ancien
> plafond, **parce qu'il etait deja exactement a la valeur du cliquet avant l'ajout du
> remplissage**."*

Elle l'a porte de **2 685 a 2 775** et **documente l'ecart avec mon texte dans la Javadoc de la
constante**, plutot que de suivre en silence le nombre que j'avais annonce. C'est exactement la
conduite que je demandais, exercee contre moi. **Mon autre chiffre tient** : `POIDS_TOTAL_MAXIMUM`
est bien a **30 730**, mesure par elle sur son arbre et non recopie de moi.

### Pourquoi c'etait faux, et ce n'est pas une erreur de calcul

**J'ai compare l'increment au plafond au lieu de l'ajouter au dessin qui definissait ce plafond.**
Or un cliquet exact est, par construction, **colle a son plus gros objet** :
`leCliquetVautExactementCeQueCeLotInstalle` l'y epingle a l'octet pres, c'est sa raison d'etre. Il
s'ensuit que **toute croissance du plus gros dessin deborde le cliquet par dessin, quelle qu'en soit
la taille** - 90 octets, ou un seul. La phrase *"gagne 90 octets et reste sous 2 685"* ne pouvait pas
etre vraie ; elle aurait ete refutee par une soustraction que j'avais tout le materiau pour faire.

**Et le reproche exact que je me fais n'est pas de m'etre trompe, c'est de n'avoir pas applique a ma
propre phrase la regle que je venais d'ecrire deux lignes plus haut** : *"30 730 est annonce, pas a
recopier - la lecon des « 12 et 12 » exacts par branche et faux apres fusion vaut pour un poids."*
J'ai protege le nombre que je transmettais a autrui, et **affirme dans la phrase suivante une valeur
que je n'avais pas mesuree**, sous la forme la plus dangereuse qui soit : une **negation**. *Ce
cliquet ne bougera pas* se verifie moins spontanement que *ce cliquet vaudra tant*, et ne demande
pourtant pas moins de mesure.

### Ce qui a fonctionne, et qu'il faut noter avec l'erreur

**Le garde-fou, lui, a fait son travail.** `leCliquetVautExactementCeQueCeLotInstalle` a rougi sur le
dessin que ma prose declarait tranquille, et il a rougi avant que quiconque ait a relire ma prose.
**La regle a rattrape son auteur** ; c'est l'ordre normal des choses dans ce depot, et c'est le seul
motif pour lequel l'ecart s'arrete a une phrase fausse dans un `ADR` au lieu d'un cliquet mal pose.

### La clause 6 est corrigee, pas seulement l'anecdote

La clause 6 disait que le cliquet total se deplacerait dans le commit qui installe les octets. Elle
vaut, et elle s'etend : **les deux cliquets se deplacent ensemble, et aucun des deux ne se predit.**
Un cliquet exact touche son objet le plus gros ; il n'y a donc pas de cliquet *"qui ne bougera pas"*
tant que l'objet le plus gros grossit, et la seule chose qu'un commit anterieur puisse dire est
**quelle mesure faire**, jamais **quel resultat elle donnera**.

### Le test de prediction est retire, et son objet avec

`lesDouzeDessinsRevisesNeSontRefusesQueParLeCliquetDePoids` reproduisait la revision d'`ines-design`
pour prouver que le retrecissement laissait passer le correctif **avant que le correctif existe**.
**Sa prediction s'est realisee, donc son objet a disparu** : les douze dessins sont commites et le
corpus reel est juge directement. Le fait qu'il epinglait - 660 octets, 44 attributs de 15 - est
desormais porte par le cliquet lui-meme.

**Il fallait le retirer plutot que le laisser vert**, sur deux motifs qu'`ines-design` a trouves et
routes sans les corriger, ce fichier n'etant pas son lot. **Son recit etait devenu faux** - il disait
*"elle ne les a pas commis"* d'un lot commite. Et **son mecanisme ne mordait plus rien** : applique a
des dessins deja remplis, il reecrivait `fill=` **en double sur la meme balise, sans une erreur**.
Un test dont la narration ment et dont le mecanisme s'applique deux fois en silence **occupe la place
de la preuve qu'il ne fait plus**.

### Le mecanisme de mutation est corrige, et le remede n'est pas celui qui vient d'abord

La correction evidente etait de **detecter** la double application et d'echouer. **Je l'ai ecrite,
puis jetee** : elle produisait une branche d'erreur que rien ne pouvait atteindre - le mecanisme
sautant les balises deja remplies, il n'en produisait jamais deux -, c'est-a-dire **un garde-fou dont
la moitie ne s'execute jamais**, ce que cet `ADR` appelle decoratif une section plus haut.

`sceneRemplieAvec(source, teinte)` **pose** la valeur au lieu de l'ajouter : elle remplace le
remplissage present, ou l'ecrit s'il est absent. La double application devient **impossible plutot
que detectable**, et la fonction est totale - le meme appel rend le meme texte sur un dessin nu comme
sur un dessin deja rempli. **Les deux morsures qui s'en servent disent donc la meme chose avant et
apres l'arrivee des dessins remplis**, au lieu de devenir vraies pour une autre raison que celle
qu'elles annoncent.

**Et le mecanisme est mordu, ce qu'il n'etait pas** : `laMutationDeRemplissagePoseLaTeinteAuLieuDeLAjouter`
verifie sur un dessin servi reel que muter un dessin **deja mute** rend exactement le meme texte.
*Preuve par retrait, mesuree et non affirmee : le mecanisme d'origine remis en place, la suite rend
**2 echecs sur 17** dont celui-ci ; remis en ordre, **17 / 0**.* C'est la lecon qui manquait : les
trois morsures qui employaient ce mecanisme regardaient le verdict du juge et **jamais le texte
produit**, et c'est par la qu'un defaut de mutation a pu vivre sans temoin.
