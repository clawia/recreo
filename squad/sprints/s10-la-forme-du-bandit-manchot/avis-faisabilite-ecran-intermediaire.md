# s10 - Avis de faisabilite : la disparition de l'ecran intermediaire, et le levier sur le flanc

- **Auteur** : theo-archi
- **Date** : 2026-08-12
- **Objet** : la decision produit de l'utilisatrice (bonne reponse, la question suivante se charge ;
  mauvaise reponse, l'enfant reste sur l'ecran de la machine, message sous le meuble) et la place d'un
  levier a boule sur le flanc du meuble.
- **Ce document ne tranche rien de produit ni de pedagogique.** Il chiffre. Les arbitrages nommes plus
  bas appartiennent a `camille-po` et a `nadia-prof`.

## Prerequis et methode des chiffres

- **Arbre** : `b15bda1`, branche `s10-theo-domaine`. `JAVA_HOME` sur un JDK 21.0.10 le temps de chaque
  commande, jamais ecrit ici ; le `java` du `PATH` ne compile pas.
- **Reference** : `mvn -B verify` avant toute modification, **371 tests, 0 echec** (43 + 7 + 178 + 63 +
  77 + 3).
- **Ce qui est mesure** est annonce comme mesure, avec le compte d'echecs et la commande. **Ce qui est
  derive** de la geometrie declaree ou de la lecture du code est annonce comme derive. Les deux ne sont
  jamais melanges dans la meme phrase.
- Toute mutation de production citee ci-dessous a ete **retiree apres mesure** ; l'arbre livre par ce
  lot ne modifie aucun fichier de `src/main`.

## 0. La reouverture d'`ADR-0026`, dite pour ce qu'elle est

`ADR-0026` section 3 refusait le levier sur l'ecran de manche et nommait la condition de reouverture :
*"si `nadia-prof` constate en jouant que le geste manque de sa cause visible"*. **Cette condition-la
n'est pas celle qui se realise.** Ce qui rouvre le sujet est une **decision produit de
l'utilisatrice**, prise apres avoir vu le manche rendu, sur deux images de reference d'un meuble
d'arcade complet. Ce n'est pas le declencheur que j'avais prevu, et le noter n'est pas une formalite :
un refus rouvert par son declencheur prevu se reprend avec les memes pieces, un refus rouvert par
autre chose se rechiffre entierement. C'est le cas ici.

**Et le prix que j'avais ecrit n'est pas le prix de ce chantier-ci.** `ADR-0026` chiffrait *"un etat de
domaine de plus, une route de plus, un aller-retour de plus par manche"* pour un etat **avant** la
reponse (rouleaux au repos, puis tires). La decision de l'utilisatrice porte sur un etat **apres** la
reponse, que le `POST` existant transporte deja. Les trois termes de mon chiffrage tombent, et deux
tombent du bon cote : **zero route de plus, un aller-retour de moins par manche**. Ce que ce chantier
coute est ailleurs, et c'est la section 2 qui le nomme.

## 1. Le chemin de code : ce que je confirme, et les quatre corrections

| Declaration a eprouver | Verdict |
|---|---|
| `jouer()` juge, incremente le score et tire la manche suivante dans le meme appel ; la manche jouee n'existe plus dans l'etat rendu | **exact** |
| Il faut une branche "faux, la manche reste" qui ne passe pas par `tirerManche` | **exact** |
| Deux champs neufs au constructeur prive, dont **six** sites d'appel devront se prononcer | **inexact sur le compte, et sur la necessite** |
| Le web n'a aucun moyen de distinguer "faux, la manche a avance" de "faux, reessaie" sans une information neuve | **inexact** |
| `PartieController.jouer()` doit passer par `mancheModele` | **exact, et incomplet d'un cout** |
| Deux clauses d'`ADR-0026` interdisent de rendre une proposition inactive | **exact** |
| Le marqueur d'une proposition deja essayee ne peut pas nommer la reponse | **exact** |

**Correction 1 - trois sites d'appel, pas six.** `grep` sur l'arbre : `new BanditManchotSession(` apparait
**3 fois**, toutes dans `BanditManchotSession.java` (lignes 104, 133, 141). La branche neuve en ferait
**4**.

**Correction 2 - zero champ neuf, si "faux" veut toujours dire "reste".** C'est la correction qui pese
le plus. Le web n'a besoin d'aucune information neuve : `!verdict.juste()` **est** le predicat complet.
Le commentaire d'`ADR-0024` dans `Verdict.java` argumente contre un champ parallele sur la session, et
il a raison - mais la question ne se pose pas, parce que l'information existe deja et qu'elle est
portee par le verdict lui-meme, croisee avec un `SessionStatus` que le controleur lit deja :

| verdict | statut | ecran |
|---|---|---|
| juste | `EN_COURS` | la manche suivante |
| juste | `TERMINEE` | fin de partie |
| faux | `EN_COURS` | **la meme manche, message sous le meuble** |
| faux | `TERMINEE` | fin de partie (l'enfant ne peut pas rester : il n'y a plus de manche) |

Un champ neuf ne devient necessaire **que si** le produit veut une exception : borner le nombre
d'essais, ou marquer une proposition deja essayee. Ces deux-la sont des arbitrages, pas des
consequences. **Tant que "faux" veut dire "reste" sans exception, l'etat de domaine neuf est nul.**

**Correction 3 - `ADR-0019` n'a rien de neuf a tenir.** Le cycle lire-calculer-ecrire d'
`EtatDePartieEnSession.jouer` ecrit deja l'etat que la mecanique rend, que la manche ait avance ou non :
la transition se produit **dans le meme appel**, sous le meme verrou. Ce que j'avais ecrit dans
`ADR-0026` valait pour une transition **entre deux requetes** ; il n'y en a pas ici.

**Correction 4 - le cout qui manquait dans la declaration, et il est reel.** `mancheModele` exige la
**consigne**, que `jouer()` n'a pas : `EtatDePartieEnSession.jouer` ne rend que la session. Deux issues,
et la premiere est mauvaise : un second `lire()` hors du premier verrou (ce que ma mutation a fait pour
mesurer, et qui passe), ou **elargir le retour de `jouer` a `EtatDePartie`** pour que la consigne sorte
du meme verrou que la session. La seconde est la bonne, et c'est le seul changement de signature que ce
chantier impose. Cout : 1 methode, 1 appelant.

## 2. Les trois arbitrages inevitables, chiffres

### 2.a `Score.tentatives()` : le seul compteur de longueur de partie

`Score` est un record de deux entiers ; `tentatives()` decide seul la fin de partie
(`nouveauScore.tentatives() >= nombreDeManches`). Deux lectures possibles, et **aucune des deux n'est
gratuite** :

| Lecture | Consequence chiffree |
|---|---|
| `tentatives` continue de compter **chaque essai** | Sur un jeu de 10 manches, un enfant qui se trompe trois fois sur la premiere question finit la partie apres **7 questions vues**, et la partie peut se terminer sur un essai faux, donc arracher l'enfant a une question qu'il etait en train de reprendre |
| la longueur compte les **manches distinctes** | La partie **n'a plus de preuve de terminaison** : un enfant qui ne trouve jamais ne termine jamais. `ADR-0012` promet nommement l'inverse ("la partie se termine des que `nombreDeManches` reponses ont ete donnees, independamment de ce que rend le contenu") |

**Ce n'est donc pas une preference, c'est une preuve a refaire.** La terminaison ne se retablit que par
l'une de ces trois choses : compter les essais (premiere ligne), **borner** les essais par manche, ou
retirer les propositions deja essayees (une manche a *n* propositions se termine alors en *n* essais au
pire). La troisieme a un cout de rendu chiffre en 2.c. **Le choix appartient a `camille-po` et
`nadia-prof` ; le fait qu'il faille choisir n'appartient a personne, il est structurel.**

Consequence mesuree du meme point : `laPartieSeTermineQuandLeVivierEstEpuise` est passe **rouge**
(`expected TERMINEE but was EN_COURS`) sous ma mutation. Motif : la seconde sortie de partie -
`contenu.next()` vide - vit **a l'interieur de `tirerManche`**, que la branche fausse ne traverse plus.
Elle n'a pas besoin d'y etre ajoutee **si** la premiere lecture est retenue ; elle doit l'etre dans les
deux autres cas.

### 2.b `echecsConsecutifs` : l'aide de S3-2 devient inatteignable telle qu'elle est ecrite

Les deux aides de `DEC-0004` point 4 et de S3-2 - l'indice, et la manche plus simple qui represente un
item deja reussi - se declenchent **dans `tirerManche`**, sur la manche **suivante**. Si une reponse
fausse ne tire plus de manche, **elles ne se declenchent plus jamais** : trois echecs consecutifs sur
des items differents deviennent impossibles (un echec ne change pas d'item), et une manche plus simple
ne peut plus etre presentee apres un echec.

**Mesure.** Mutation appliquee (branche "faux, la manche reste" + routage) puis retiree,
`mvn -B verify -Dmaven.test.failure.ignore=true` : **19 tests rouges sur 371**, dans trois classes.

| Classe | Rouges | Ce que c'est |
|---|---|---|
| `BanditManchotTest` | **11 sur 22** | 3 idiomatiques, 1 qui encode l'ancienne regle, 7 qui encodent une propriete devenue inatteignable |
| `PartieControllerTest` | **6 sur 21** | le rendu des deux retours, l'explication, l'indice, le bouton de continuation |
| `PartieHttpTest` (bootstrap) | **2 sur 4** | les deux memes chemins, de bout en bout en HTTP |

Detail des 11, parce que la difference decide du travail :

- **3 idiomatiques**, reecriture mecanique : `lEnchainementDesManchesConserveLeScoreCumule`,
  `laPartieSeTermineQuandLeVivierEstEpuise`, `leTirageExcluBienLesManchesDejaVues`. Ces trois avancent
  dans la partie **en repondant faux**, parce que c'etait le moyen le plus court ; il faut repondre
  juste.
- **1 qui encode l'ancienne regle** : `unChoixIncorrectEstJugeFauxDesigneLaBonneReponseEtNAvancePasLeScore`
  asserte `enonceManche()` contient "trois" apres un faux, c'est-a-dire exactement que la manche a
  avance. A retourner.
- **7 qui encodent une propriete inatteignable** : les quatre du seuil que vous nommez, plus
  `troisEchecsConsecutifsRepresententUnItemDejaReussiPuisLeTirageNormalReprend`,
  `troisEchecsConsecutifsSansAucunItemReussiSuiventLeTirageNormal` et
  `unItemRepresenteApresTroisEchecsPorteEncoreSonExplication`. **Ces sept-la ne se reecrivent pas : ils
  attendent une decision sur ce que devient l'aide.** Trois possibilites, aucune n'est mienne : l'aide
  s'affiche sur la manche ou l'enfant est reste (l'indice devient une propriete de la manche courante,
  la manche plus simple disparait) ; l'aide se declenche sur un essai borne ; l'aide est abandonnee avec
  son `ADR`.

**Un chiffre derive, non mesure, a ne pas oublier** : `LONGUEUR_MINIMALE_POUR_AIDE = 4` et
`BanditManchot.longueurMinimale()` disent qu'une partie de moins de 4 manches ne peut jamais montrer
l'aide. Si l'indice se rattache a la manche courante, ce nombre devient **1** et deux epinglages
mentent : `BanditManchotTest` ligne 94 (`isEqualTo(4)`) et `CatalogueChargeableIT`, qui compare cette
valeur au `nombreDeManches` de chaque jeu du catalogue. Ils sont restes **verts** sous ma mutation
parce que je n'ai pas touche la constante : c'est un mensonge qui ne rougit pas tout seul.

### 2.c Le sort de la proposition fautive : ce que chaque reponse coute

Trois formes, et leur cout en amendements est tres inegal.

| Forme | Amendements | Cout mesure ou derive |
|---|---|---|
| **Elle reste active et identique** | **aucun** | Aucune preuve de terminaison (2.a). L'enfant peut presser le meme mauvais bouton indefiniment |
| **Elle est rendue inactive** (`disabled`, `aria-disabled`, `pointer-events: none`, `opacity: 0`) | **2 clauses d'`ADR-0026`** (3 et 4) **+ 2 jeux fautifs a retailler** (`action-desactivee`, `proposition-cachee-en-permanence`, qui prouvent aujourd'hui la morsure de ces clauses) | L'amendement doit apporter un **critere discriminant neuf** (permanent contre lie a un etat), sinon les deux clauses cessent de mordre sur ce qu'elles gardaient. `ADR-0033` ne s'y oppose pas : il gouverne l'habillage, defini comme le complementaire des classes portees par `button, a[href]`, et `.proposition` est sur un `button` |
| **Elle n'est plus rendue du tout** | aucun sur `ADR-0026` (c'est meme ce que sa clause 4 prescrit : *"il ne rend pas ce bouton du tout"*) | **Reflux mesurable de la mise en page.** Sur "Le bon verbe" (4 propositions, au catalogue depuis s08), perdre une proposition fait passer les colonnes de **2 rangees de 2 a 152,00 px** a **1 rangee de 3 a 96,00 px**, et la police de **1,875 rem a 2 rem** - parce que `.propositions:has(.proposition:nth-child(4))` cesse de s'appliquer. La mise en page change **sous le doigt de l'enfant**, au moment ou il se trompe |

Le marqueur d'une proposition deja essayee, quelle que soit la forme retenue, ne peut pas nommer la
reponse : `data-correct`, `data-bonne`, `data-solution`, `data-reponse` sont refuses par trois tests
distincts (`FrontieresDuJavaScriptTest`, `PartieControllerTest`, `PartieHttpTest`). **Confirme.** Un
marqueur qui dit "deja essayee" et rien d'autre ne tombe sous aucune de ces interdictions.

## 3. Le compte, puisque vous le demandez

| Grandeur | Aujourd'hui | Apres |
|---|---|---|
| Routes du controleur | 5 | **5**, dont **1 sans plus aucun appelant** (`GET /parties/{jeuId}/manche`, appelee par le seul bouton "Encore !") |
| Allers-retours par manche | 2 | **1** |
| Champs de `BanditManchotSession` | 12 | **12** si "faux" veut toujours dire "reste" ; 13 ou 14 si le produit borne les essais |
| Sites d'appel du constructeur prive | 3 | **4** |
| Signatures a elargir | - | **1** (`EtatDePartieEnSession.jouer`, pour la consigne) |
| Tests rouges mesures | - | **19 sur 371** |
| Tests verts qui mentent, derives | - | **2** (`BanditManchotTest:94`, `CatalogueChargeableIT`) |

**Le sort de "Encore !" est un cout a lui seul, et il est mesure.** Mutation : suppression du formulaire
`.encore` de `fragments/retour.html`, puis restauration. `mvn -B test` sur `adapter-web` : **3 rouges**.

- `LActionNeDeclarePasMoinsQueLaSortieTest.surLEcranDeRetourLaSortieEstConfronteeAEncoreMaisPasDansSonEtatDeFinDePartie`
  - l'epinglage du seul couple ou l'appariement de saillance fait un choix ;
- `LActionNeDeclarePasMoinsQueLaSortieTest.leRejeuDuLotBootstrapFaitEchouerLeGardeFou` - **la preuve de
  morsure d'`ADR-0017`**, dont le message attendu nomme `.btn.encore`. Supprimer ce bouton **detruit la
  mise a l'epreuve d'un garde-fou accepte** ; elle doit etre retaillee sur un autre couple reellement
  confronte, et ce couple n'est pas libre ;
- `PartieControllerTest.leBoutonPourContinuerNEmploiePlusLeMotManche`.

Et un fait que je n'avais pas prevu, mesure a l'inverse de ma prediction : `CabinetDeLaMachineTest` reste
**vert**, parce qu'il epingle la regle `.encore` de `recreo.css` et non le bouton. La regle survivrait
donc au bouton, **protegee par un test vert** : du CSS mort qu'un garde-fou defend. Aucun automate du
depot ne signale une regle maison dont plus aucun gabarit ne pose la classe -
`HabillageDesEcransTest.aucuneClassePoseeSurUnElementNEstOrpheline` verifie l'implication inverse.

## 4. Les garde-fous a amender, et un que j'ai deja ferme

**Un seul point de conception est deja tranche et livre dans ce lot, parce qu'il ne depend d'aucun
arbitrage** : voir `ADR-0034`. Mesure : les deux corpus d'ecrans (`ADR-0020` et `ADR-0027`) ne jouaient
**que des bonnes reponses** - leurs deux coups, `propositionChoisie=1` et `=2`, sont les bonnes reponses
des deux items du jeu de test. Un `<p>` de texte insere **dans** `<form class="propositions">` sur le
chemin faux a laisse les **six** tests de `LEcranDeJeuDeclareCommentIlEstArriveTest` verts, dont celui
qui existe pour refuser un mot leurre dans le conteneur des colonnes. Corpus etendus, une clause par
classe, un jeu fautif, `mvn -B verify` : **374 tests, 0 echec**.

Les autres, a amender **apres** l'arbitrage, avec leur cout :

| Garde-fou | Ce qu'il faut faire | Pourquoi il ne peut pas etre fait maintenant |
|---|---|---|
| `ADR-0026` clauses 3 et 4 (`LAnimationRetardeMaisNeDetermineRienTest`) | Amendement + critere discriminant + 2 jeux fautifs retailles | Ne s'amende que si `nadia-prof` rend la proposition fautive inactive (2.c) |
| `ADR-0027` clause 2 (`toutEcranDeJeuEchangeParHtmxAutoriseLeMouvement`) | Choisir : accepter que les colonnes **rejouent leur animation a chaque erreur**, ou creer une troisieme valeur d'arrivee | La clause **force** aujourd'hui `data-arrivee="par-un-geste"` sur tout ecran a propositions echange par htmx. Mesure : sous ma mutation, l'ecran de reessai l'a declare et les 6 tests sont restes verts. **La CI accepte donc deja la reanimation d'une question qui n'a pas change** ; si `nadia-prof` la refuse, c'est un amendement d'`ADR-0027` + un selecteur de `recreo.css` + la clause. Cout de l'autre branche : **zero** |
| `ADR-0017` (`leRejeuDuLotBootstrapFaitEchouerLeGardeFou`) | Retailler la preuve de morsure sur un autre couple | Depend de la disparition de "Encore !", qui depend du flux |
| `ADR-0012` | Amendement, si la longueur de partie cesse de compter les essais | Depend de 2.a |
| `ADR-0034` (celui de ce lot) | Ecrire la clause generale : **chaque branche conditionnelle d'un gabarit est exercee par le corpus** | Le nombre d'etats de l'ecran de jeu est precisement ce que l'arbitrage va changer. La deriver maintenant serait la deriver d'un flux imaginaire. **C'est la que je la demande** |

**Ce qui n'a besoin de rien**, verifie et non suppose : `ADR-0019` (section 1, correction 3) ;
`ADR-0033`, qui gouverne l'habillage et non les pressables, donc ni `.proposition` ni son etat ;
`ADR-0020` clause 1 (le message est un `<p>`, pas un identifiant) ; et
`LEcranDeJeuDeclareCommentIlEstArriveTest.aucunTexteAutreQueLesPropositionsNEntreDansLeConteneurDePropositions`,
qui **garde deja gratuitement** la place du message : sous le meuble, il est hors de `.propositions` et
la clause est muette ; dedans, elle rougit - depuis `ADR-0034`, y compris sur le chemin faux.

Deux exigences d'ecriture s'appliqueront au message sans amendement d'aucune sorte : sa classe devra
porter une regle de `recreo.css` (`HabillageDesEcransTest`, mesure sous ma mutation : **rouge** sur la
classe orpheline) et son texte devra etre en francais integralement accentue
(`TexteAccentueDesGabaritsTest`, `DEC-0002` amende).

## 5. Les deux consequences pedagogiques : l'inventaire des places, sans le choix

L'ecran de retour porte aujourd'hui deux choses que rien d'autre ne porte. Si l'ecran disparait sur le
chemin juste **et** sur le chemin faux, il ne subsiste que comme ecran de fin de partie, et ces deux
choses doivent demenager ou disparaitre. Le choix est a `camille-po` et `nadia-prof` ; voici les places
techniquement disponibles et leur prix.

### 5.a La revelation de la bonne reponse (critere d'origine S0-2)

| Place possible | Prix technique | Ce qui s'y oppose ou s'y prete |
|---|---|---|
| **Nulle part sur le chemin faux** : l'enfant reessaie jusqu'a trouver, et voit la bonne reponse parce qu'il finit par la presser | zero | Techniquement la plus simple. Elle **supprime** le critere S0-2 tel qu'il est ecrit : la reponse n'est plus *revelee*, elle est *trouvee*. Depend entierement de 2.a : sans borne, l'enfant peut ne jamais trouver |
| **Sur la manche, apres un nombre borne d'essais** | 1 champ de session (essais sur la manche courante), 1 seuil, 4 sites d'appel du constructeur a faire se prononcer | C'est la seule place qui garde le critere **et** la terminaison. Elle cree un troisieme etat d'ecran, donc une clause d'`ADR-0034` a etendre |
| **Sur l'ecran de fin de partie**, en recapitulatif | 1 liste dans la session (les items rates et leur reponse), soit un etat qui grossit avec la partie | Deplace la revelation loin de l'erreur - c'est un choix pedagogique, pas un cout technique. Cout technique reel : le premier etat de session de ce depot dont la taille depend du deroulement |
| **Sur la manche, immediatement**, dans le message sous le meuble | zero (le verdict porte deja `bonneReponse`) | **Se contredit avec la decision produit** : donner la reponse et demander de reessayer en meme temps. Je le nomme pour qu'il ne soit pas redecouvert, pas pour qu'il soit choisi |

### 5.b L'explication, affichee systematiquement juste ou faux

Rappel de la contrainte reelle, qui n'est pas une preference : `Verdict` porte l'explication **parce que
c'est le seul instant ou la session tient encore l'item joue** (`ADR-0024`). Sur le chemin faux, cette
contrainte **disparait** - l'item est toujours la manche courante. Sur le chemin juste, elle reste
entiere : la session a deja tire la manche suivante.

| Place possible | Prix technique |
|---|---|
| **Chemin faux : sur la manche**, sous le meuble, avec le message | zero. L'item est la manche courante, l'explication est atteignable directement |
| **Chemin juste : nulle part** | zero, et c'est une **perte** : l'exigence de `nadia-prof` du 2026-08-06 est "systematiquement, juste ou faux". La bonne reponse n'aurait plus jamais son explication |
| **Chemin juste : sur la manche suivante**, en tete | zero en etat (le verdict la porte deja et voyage), mais l'explication de la question **precedente** s'afficherait au-dessus de la question **suivante**. Techniquement gratuit, lisiblement douteux : c'est exactement le genre de chose que `nadia-prof` doit voir rendue avant de dire oui |
| **Chemin juste : sur l'ecran de fin de partie** | meme cout que 5.a, meme etat qui grossit |

**Le point dur, dit clairement** : la decision produit supprime le seul ecran ou "systematiquement,
juste ou faux" avait un endroit unique et symetrique. Toute solution rend l'explication **asymetrique**
entre le juste et le faux, ou la reporte a la fin de partie. Il n'existe pas de troisieme voie
technique.

## 6. Le levier a boule sur le flanc : ou cette forme peut vivre

### 6.a Il n'y a aucun pixel libre a 360 px, et c'est demontrable

**Methode.** Derivation de la geometrie declaree (`recreo.css`, `bootstrap.min.css` charge avant,
`box-sizing: border-box` global), **validee** sur trois captures independantes anterieures : les
101,33 px d'`ADR-0026` avant le cabinet, et les 96,00 px (3 colonnes) et 152,00 px (2 par rangee) que
`nadia-prof` et `ines-design` ont mesures a 360 px en s08. La derivation reproduit les trois exactement.
Ce n'est donc pas une capture de plus, c'est l'arithmetique de celles qui existent.

Les 360 px se referment sans reste :

```
360 = 24 (rembourrage lateral de .container, 2 x 12)
    + 16 (cadre du cabinet, 2 x 8)
    + 32 (deux ecarts de 1rem)
    + 288 (trois colonnes de 96,00 px)
```

Et dans le cas a quatre propositions, qui est celui de "Le bon verbe", au catalogue :
`360 = 24 + 16 + 16 (un ecart) + 304 (deux colonnes de 152,00 px)`.

**Consequence** : tout flanc dessine a un payeur, et il n'y en a que trois. Le rembourrage du
`.container` (12 px, un flanc), les colonnes (au prorata), ou la hauteur.

### 6.b Les cinq pistes, chiffrees

| Piste | Largeur obtenue | Cout sur les colonnes | Cout ailleurs |
|---|---|---|---|
| **A. Pseudo-element debordant dans le rembourrage du `.container`** | **12,00 px** sur un flanc | **zero** | La respiration laterale du contenu tombe a 0 px sur la hauteur du levier. Rien ne le signale : `LeConteneurTierGardeSonRembourrageLateralTest` lit la **declaration**, pas le rendu |
| **B. Le cabinet cede N px de largeur** (marge, rembourrage, ou cadre epaissi d'un cote - identique sous `border-box`) | N px | **-N/3 par colonne a 3 colonnes, -N/2 a 2 par rangee** | Voir le tableau ci-dessous |
| **C. Le levier passe par-dessus les colonnes** (superposition absolue) | libre | zero | Il **intercepte l'appui** sur la premiere ou la derniere colonne. Aucun automate ne le voit : la clause 3 d'`ADR-0026` ne lit que les regles nommant une classe portee par un `[data-role=action]`, et `.cabinet::after` n'en est pas une |
| **D. Le levier couche sous le socle** | **336 px** | zero | Cout en **hauteur**, et la hauteur est deja pleine : releve de `nadia-prof` du 2026-08-11, pli a `y = 640`, bas du cadre a **540..636 px** sur les 8 etats sans indice. **4 px** separent le plus serre de ces etats du pli. Une bande de 12 a 24 px l'y fait passer |
| **E. Le levier dans le cadre de 8 px du cabinet** | **8,00 px** | **zero** | Reste entierement dans la boite du cabinet, ne deborde rien. Les dimensions entrent dans les cinq selecteurs a zero `px` de `CabinetDeLaMachineTest`, avec le piege d'ordre que j'ai deja documente (un selecteur ajoute avant `.cabinet-bandeau::before` rend le jeu fautif rouge pour la mauvaise raison) |

Cout de la piste B, chiffre sur le cas le plus serre reellement servi - `mangeons` de "Le bon verbe",
**156,02 px de glyphes dans une boite de 152,00 px**, soit **-2,01 px** de chaque cote, qui est la
**seule reserve** que `nadia-prof` ait maintenue sur ce chantier :

| N cede au flanc | 3 colonnes | 2 par rangee | Marge sur `mangeons` |
|---|---|---|---|
| 0 (etat livre) | 96,00 px | 152,00 px | **-2,01 px** |
| 12 px | 92,00 px | 146,00 px | **-8,01 px** |
| 20 px (largeur de la forme deja refusee comme illisible) | 89,33 px | 142,00 px | **-12,01 px** |
| 32 px (2 rem, la hauteur du bandeau) | 85,33 px | 136,00 px | **-20,01 px** |

Le plancher de cible tactile (44 px) survit a tous ces cas ; ce n'est pas lui qui borne. **Ce qui borne
est un debordement de glyphes deja negatif, que la piste B multiplie par 4 a 10.**

### 6.c Ce que je recommande d'examiner, et pourquoi ce n'est pas la meme forme

**A + E, cumulees, donnent 20 px de flanc dessine pour zero pixel de colonne** : les 8 px du cadre plus
les 12 px du rembourrage. Et sur le flanc, **l'axe contraint change** : la hauteur du cabinet est de
**138 px** (mesure `ines-design`, s08 : 8 + 32 + 74 + 16 + 8). La forme refusee sur le fronton disposait
de **20 x 24 px** ; sur le flanc, a cout nul, elle disposerait de **20 x 138 px**, soit **5,75 fois la
surface**.

C'est tout ce que je peux dire : **qu'une silhouette de 20 x 138 px se lise comme un levier a boule
n'est pas une question d'architecture**, et `ADR-0025` a deja declare cette famille non gardable par un
automate de ce depot. Elle est a `ines-design` pour la forme et a `nadia-prof` pour le verdict. Ce que
j'apporte est le budget, et le fait que **c'est le seul budget disponible sans payeur**.

**Deux refus fermes, sur des chiffres et non sur un gout** : la piste B, parce qu'elle aggrave de 4 a
10 fois le seul debordement que `nadia-prof` a nomme ; la piste D, parce que 4 px separent le pli de
l'etat le plus serre qui le respecte encore. La piste C est refusee aussi, et son motif est different :
elle vole l'appui d'une colonne **et aucun garde-fou ne le dirait**. Si elle etait retenue malgre tout,
elle demanderait une clause neuve avant la premiere ligne de CSS.

## 7. Verdict de faisabilite

- **La disparition de l'ecran intermediaire est faisable, et moins couteuse en structure que ce que
  j'avais chiffre dans `ADR-0026`** : zero route neuve, un aller-retour de moins, zero champ de session
  neuf tant que "faux" veut dire "reste" sans exception. **Faisable moyennant deux `ADR`** : un sur la
  longueur de partie (`ADR-0012` amende ou remplace), un sur le sort de l'aide de S3-2.
- **Elle n'est pas faisable en l'etat sans trois arbitrages qui ne sont pas les miens** : ce que compte
  la longueur d'une partie, ce que devient l'aide de S3-2, et ou vont la revelation et l'explication.
  Les trois sont remontes a `camille-po` et `nadia-prof`. Le premier n'est pas negociable au sens ou il
  faut **une** reponse : sans elle, la partie n'a plus de preuve de terminaison.
- **Le levier sur le flanc est faisable a cout nul en pixels**, dans 20 px de largeur et 138 px de
  hauteur, si et seulement si la forme se lit dans ce budget - ce que je ne peux pas juger et ce
  qu'aucun automate de ce depot ne jugera.
- **Ne pas coder la mecanique avant l'arbitrage.** Ce lot ne modifie aucun fichier de `src/main`.

`mvn -B verify` sur l'arbre de ce lot, JDK 21.0.10 : **BUILD SUCCESS, 374 tests, 0 echec.**
