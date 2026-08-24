# [Sprint s19] Avis de faisabilite - six stories, l'ordre de fusion, et deux routages a confirmer

- **Date** : 2026-08-24. **Auteur** : theo-archi.
- **Branche** : `s19-theo`, worktree dedie, lignee verifiee par `outils/verifier-lignee.sh`.
- **Arbre juge** : `round-two` a `c3d3d47` (SHA releve sur la branche, pas repris d'un message), plus
  les lots non fusionnes `s19-camille` (`dc80943`) et `s19-nadia` (`6aecee3`), lus depuis ce worktree.
- **Mesure de reference du lot** : `bash outils/mvn-jdk21.sh -B clean verify` a la racine du reacteur,
  JDK 21.0.10 de la toolchain declaree - **621 tests, 0 echec, BUILD SUCCESS** avant mes ecritures,
  **624, 0 echec** apres (la regle d'`ADR-0058` et ses deux morsures). Reacteur complet, donc pas de
  `-am` a citer ; les mesures partielles de cet avis le portent et le disent.
- **Ce que je ne fais pas** : je ne redige aucune story, je ne repriorise rien, je ne prononce aucun
  Done. Sur chaque story je dis une chose : faisable en l'etat, faisable moyennant un `ADR`, ou
  infaisable sans revoir le decoupage.

## Tableau de tete

| Story | Verdict | Ce qui coince, en une ligne |
|---|---|---|
| `S19-1` antonymes CM1 | **faisable en l'etat** | rien ; le raisonnement de `camille-po` sur `ADR-0052` est verifie exact |
| `S19-2` accord dans le groupe nominal CM1 | **faisable en l'etat, deux garde-fous mordent** | l'indice obligatoire sur chaque item n'est dans aucun critere, et un item a plusieurs trous n'a qu'une reponse |
| `S19-3` accord sujet-verbe CM1 | **faisable en l'etat, trois garde-fous mordent** | meme indice manquant, plus le garde-fou de l'indice qui se contredit - dont le cliquet est a zero |
| `S19-4` nom/verbe CP | **faisable en l'etat** | deux propositions par item est un format deja servi, mesure ; rien de neuf |
| `S19-5` migration `ADR-0049` | **faisable en l'etat, perimetre corrige** | mon chiffre de s17 comptait un fichier de trop et un de moins ; le vrai perimetre est 13 + 2 |
| `S19-6` `SYN-16`/`BPM-8` | **infaisable en l'etat sur la bande entiere** | l'arithmetique ferme la famille de correctifs qu'on croit avoir, et le chiffre qui deciderait n'existe pas |

---

## 1. `S19-1` - Les antonymes (francais CM1)

### Verdict : **faisable en l'etat.** Aucun `ADR`, aucune regle touchee, aucun code de domaine.

**Ce qui m'etait demande avant tout le reste : verifier le raisonnement de `camille-po` contre
`ADR-0052`. Il est exact, et je l'ai verifie sur la donnee, pas sur le texte de l'ADR.**

`adapters/adapter-content/src/main/resources/catalogue.yaml`, entree
`bandit-manchot-synonymes-cm1` : `consigne: "Quel mot peut remplacer le mot entre guillemets sans
changer le sens ?"`. La consigne est **une cle de l'entree de catalogue**, une par jeu, jamais une
cle d'item - c'est le point 1 d'`ADR-0052`, et le point 2 dit pourquoi seul le mot cible est reste
dans l'item. Un item d'antonyme servi sous cette entree recevrait donc cette consigne-la, mot pour
mot, et elle serait fausse pour lui. Sa conclusion tient.

**Je ferme aussi la porte de sortie qu'elle n'a pas examinee**, parce que c'est celle qu'un
developpeur essaiera : une **seconde entree de catalogue pointant le meme paquet**, avec une autre
consigne. Elle ne marche pas, pour deux raisons independantes. Le tirage d'un item est aleatoire dans
le paquet entier (`ListContentSource.next`) : les deux entrees serviraient les memes items melanges,
donc des antonymes sous la consigne des synonymes. Et `AucuneEntreeDuCatalogueNEnDoubleUneAutreTest`
refuse deux entrees qui partagent le couple (mecanique, paquet) : sa clause
`doesNotHaveDuplicates()` sur l'association est eprouvee par un catalogue fautif
(`unCatalogueQuiAssigneAUnJeuLePaquetDUnAutreFaitEchouerLeGardeFou`), et le meme paquet sous **deux
mecaniques differentes** reste vert, ce qui est bien la frontiere. Le message de ce garde-fou ouvre
une porte, et je la nomme pour qu'elle ne serve pas ici : si la duplication etait voulue, *"le critere
devient le triplet avec nombreDeManches, ou il se retire"* - c'est-a-dire un affaiblissement du
garde-fou, decidable par `camille-po` seule, et sans objet ici puisque le premier motif (le tirage
melange les deux operations) suffit a fermer le chemin. **Paquet neuf et entree neuve sont donc le
seul chemin vert**, et c'est bien celui qu'elle a retenu.

**Ce que la story coute en garde-fous, avec la methode du comptage.** Balayage des fichiers
`*Test.java` de `adapters/adapter-content/src/test/java/com/recreo/adapter/content`, hors les tests
nommes d'apres un paquet precis (`Paquet*Test`), retenant ceux dont le perimetre se derive du
repertoire de contenu : **9 garde-fous** entrent en jeu du jour ou le fichier est depose, **sans
qu'aucun ne soit modifie** - c'est `ADR-0013` obligation 1 qui rend cela vrai. Trois autres se
derivent du catalogue et entrent avec l'entree neuve
(`AucuneEntreeDuCatalogueNEnDoubleUneAutreTest`, `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest`,
`ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`). Deux points a connaitre d'avance :

- **le plancher de 16 items est en dessous de ce qu'exige `ChaqueJeuDuCatalogueOffrePlusDItemsQue-
  DeManchesTest`** ? Non : `nombreDeManches: 10` demande au moins 11 items distincts. 16 passe ;
- **`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` ne verra rien de ce paquet**, et il
  faut le savoir plutot que de croire le contraire : sa propriete ne s'applique qu'aux items dont les
  trois propositions sont **numeriques**, et `ADR-0051` a deja declare l'ordre des propositions sur
  du texte **non gardable**. La propriete "aucun distracteur n'est un antonyme acceptable" est donc
  tenue par la seule porte pedagogique. Ce n'est pas un defaut de la story, c'est un fait a ecrire
  pour qu'aucune fiche ne credite un automate d'un controle qu'il ne fait pas.

**Ce que la story ajoute d'obligatoire ailleurs** : 6 chaines dans l'inventaire de
`TexteAccentueExposeParLePortEntrantTest` (identifiant, titre, consigne, matiere, domaine,
competence), dans le meme commit. Ses deux clauses sont bidirectionnelles - sans cette edition, les
deux rougissent. Voir la section 7 pour ce que cela impose a l'ordre de fusion.

---

## 2. `S19-2` - Accord en genre et en nombre dans le groupe nominal (francais CM1)

### Verdict : **faisable en l'etat.** Aucun `ADR`. **Deux garde-fous mordent sur des points que les criteres d'acceptation ne nomment pas.**

**a. Chaque item doit porter un indice, et ce critere n'est pas dans la story.**
`ChaquePaquetPorteUnIndiceTest` charge chaque paquet du repertoire par le chargeur reel, tire ses
items par la meme chaine que l'enfant, et **un item sans indice fait echouer le test**. Il n'y a
aucune sortie : sa liste d'exemptions `DETTE_DE_CONTENU` est **vide** et son `PLAFOND_DE_DETTE` vaut
**0** depuis le 2026-08-07, precisement pour qu'un paquet neuf sans indice ne puisse plus y entrer en
silence. `S19-1` et `S19-4` portent ce critere dans leur story, `S19-2` et `S19-3` ne le portent
pas. Ce n'est pas un manque de contenu, c'est un rouge de CI que le developpeur decouvrira au premier
`verify` s'il ne le sait pas d'avance.

**b. Un item a plusieurs trous n'a qu'une seule reponse, et c'est une contrainte de mecanique.** La
story laisse le format ouvert - *"une phrase a trou par composante, ou un groupe nominal complet avec
plusieurs trous"* - et me le route s'il a un cout different. **Il en a un.** La mecanique
`bandit-manchot` fait presser **une** proposition par manche et compare **une** chaine
(`BanditManchotSession.jouer`). Un groupe nominal a plusieurs trous n'est donc jouable que si chaque
proposition remplit **tous** les trous a la fois ("les petites fleurs" contre "le petites fleur"),
c'est-a-dire si le trou multiple est en realite un choix unique. Toute autre forme - un verdict par
trou - est une mecanique neuve, et je la refuse comme cout de cette story.
`ChaquePaquetATrousPorteSonTrouSurChaqueItemTest` n'y voit rien : il derive le paquet a trous de la
presence du marqueur `___` sur au moins un item et exige alors sa presence sur **tous**, sans jamais
compter les marqueurs d'un item. Le garde-fou est donc muet sur ce point precis ; la contrainte est
tenue par cet avis et par la relecture, pas par la CI.

**c. Un piege de forme, a ne pas decouvrir par un rouge.** Le critere de la story sur les
distracteurs de l'adjectif reprend *"le patron deja valide sur `cm1-accords.yml` (les quatre formes
possibles de l'adjectif)"*, soit **4 propositions**. Quatre propositions est un format deja servi
(`cm1-conjugaison-present`, 25 items a 4 propositions, `couvert-valide`), donc rien de neuf du cote
du code : la feuille maison replie ces items en deux rangees de deux
(`.propositions:has(.proposition:nth-child(4)) .proposition { flex: 1 1 calc(50% - 0.5rem) }`) et
`TailleDesTextesEtBoutonsTest` leur applique un plancher de police distinct, 1,875 rem. **Ce qui
change avec un paquet mixte**, c'est que le meme paquet melangerait des items a 4 propositions et des
items a 2 ou 3 : la hauteur de l'ecran varierait d'un item a l'autre du meme jeu. C'est exactement la
famille `SYN-11`/`BPM-1`, et c'est `nadia-prof` qui la mesure. Je le nomme a l'ouverture plutot qu'a
l'audit.

**d. Sur la limite de couverture** que la story permet de nommer si les trois composantes ne tiennent
pas : ce n'est pas une question d'architecture, c'est une decision de `camille-po` et une porte de
`nadia-prof`. Aucun garde-fou ne compte les composantes d'un groupe nominal, et aucun ne le pourra
sans un dictionnaire, qu'`ADR-0039` et `ADR-0049` refusent tous deux.

---

## 3. `S19-3` - Accorder le verbe avec son sujet (francais CM1)

### Verdict : **faisable en l'etat.** Aucun `ADR`. **Trois garde-fous mordent, dont un dont le cliquet est deja a zero.**

**a. Le meme indice obligatoire qu'en 2.a**, meme motif, meme absence dans les criteres.

**b. Et surtout : `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` est ecrit contre exactement
cette story.** Il est ne du refus de `nadia-prof` sur `cm1-conjugaison-present` le 2026-08-10 :
*"a la manche 7, l'indice affirmait 'avec tu, le verbe se termine par -es', et a la manche 10 la
bonne reponse etait 'finis'"*. Sa propriete : **aucun indice enoncant une regle generale non temperee
ne doit etre dementi par la bonne reponse d'un autre item du meme paquet portant le meme sujet.** Un
paquet d'accord sujet-verbe couvrant present, futur et imparfait, avec des sujets composes et des
groupes nominaux, est la forme qui produit le plus naturellement une regle generale trop large. Le
garde-fou distingue deja trois formes d'indice - regle temperee ("presque toujours"), regle nommant
un verbe precis, regle generale non temperee - et ne mord que sur la troisieme. **La sortie n'est pas
d'exempter** : son cliquet vaut *"exactement la taille de la dette - zero"*, il n'y a aucune place
libre. La sortie est d'ecrire les indices temperes ou nommant leur sujet, et c'est une contrainte
d'ecriture a donner au developpeur des l'affectation.

**c. La distinction avec la ligne conjugaison deja couverte** que la story exige - *"jamais un sujet
pronominal isole colle au verbe"* - **n'est gardee par aucun automate**, et il faut le dire.
`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` compare des ecrans a l'interieur d'un paquet,
jamais entre deux paquets : deux jeux qui poseraient la meme question sous deux titres passeraient
verts. Cette propriete-la est entierement pedagogique.

**d. Rien d'autre.** 20 items sur `nombreDeManches: 10` passe `ADR-0023` largement, les propositions
sont textuelles donc hors du perimetre d'`ADR-0041` et d'`ADR-0051`, et le paquet portera son
marqueur `___` comme `cm1-conjugaison-present` le fait deja.

---

## 4. `S19-4` - Distinguer nom et verbe dans une phrase tres simple (francais CP)

### Verdict : **faisable en l'etat.** Aucun `ADR`, aucun format neuf.

**Le seul point qui meritait une mesure, et il tombe.** Deux propositions par item ("nom" et "verbe")
pouvait etre un format de colonne jamais servi, donc un rendu jamais juge. **Il est deja servi** :
comptage des propositions par item sur les treize paquets de
`adapters/adapter-content/src/main/resources/contenu/`, bonne reponse plus distracteurs -
`cm1-homophones` sert **24 items a 2 propositions**, et sa ligne est `couvert-valide` depuis s18. Les
comptes servis aujourd'hui sont 2, 3 et 4 ; `S19-4` n'en introduit aucun de neuf, et la feuille
maison replie deja *"a deux, trois ou quatre colonnes"* (`recreo.css`, `.propositions`).

**Une consequence de ce format que la story ne nomme pas, et qui n'est pas un defaut** : quand les
deux propositions sont les memes chaines sur tous les items,
`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` se reduit a une comparaison d'enonces -
l'identite qu'il compare est le couple **enonce + ensemble de propositions**, et l'ensemble est
constant ici. Deux items ne differant que par le mot cible marque dans la meme phrase seraient donc
rouges, ce qui est le bon comportement. Le paquet doit porter 16 phrases distinctes, pas 8 phrases
vues deux fois sous deux mots cibles.

**Le reste des criteres** - repartition equilibree nom/verbe, vocabulaire de CP, indice qui ne nomme
pas la reponse, marque du mot cible par une ponctuation francaise - est soit deja garde
(`ChaquePaquetPorteUnIndiceTest`), soit hors de portee d'un automate et donc pedagogique. Je ne
propose aucune regle neuve : compter les mots "de niveau CP" demanderait une liste ecrite a la main,
c'est-a-dire un critere enumere sous une promesse universelle, ce qu'`ADR-0039` sanctionne.

---

## 5. `S19-5` - Migration `ADR-0049` (accentuation des descripteurs)

### Verdict : **faisable en l'etat, en un seul commit.** Aucun `ADR`, aucune regle neuve. **Le perimetre que j'ai chiffre a s17 est faux sur deux points, et je le corrige plutot que de le recopier.**

**Methode de la reverification**, dans le meme paragraphe que les chiffres : balayage de
`adapters/adapter-content/src/main/resources/contenu/*.yaml` pour la cle `descripteur.matiere` ;
recherche de l'appel `matiere()` dans tout `.java` de l'arbre ; lecture des deux fichiers de test qui
comparent une valeur de matiere. Arbre `round-two` a `c3d3d47`, 2026-08-24.

**Le perimetre reel aujourd'hui : 13 fichiers YAML et 2 fichiers Java, un seul commit.**

1. **13 paquets servis**, tous en ASCII, aucune exception - **4** en `Francais`
   (`cm1-conjugaison-present`, `cm1-homophones`, `cm1-synonymes`, `cp-le-bon-son`) et **9** en
   `Mathematiques`. Le compte de `camille-po` a l'ouverture de s19 est exact.
2. **`TexteAccentueExposeParLePortEntrantTest`** : **5 chaines**, pas 2 - les deux matieres, plus les
   trois chaines `CONNU ET NON CORRIGE ICI` que le complement du 2026-08-19 a fait entrer au
   perimetre (un domaine, deux competences). Ses deux clauses sont bidirectionnelles : sans cette
   edition les deux rougissent.
3. **`PaquetCm1SynonymesTest.leDescripteurEstFrancaisCm1VocabulaireSynonymesEtAntonymes`**, ligne 232,
   `assertThat(descripteur.matiere()).isEqualTo("Francais")` **sur le paquet servi**. **Ce fichier
   n'est dans aucun perimetre ecrit a ce jour**, ni dans mon avis de s17, ni dans la story, ni dans
   son complement. Il n'y etait pas parce qu'il n'existait pas quand j'ai chiffre : il est arrive
   avec le lot `S17-3`, sur une branche non fusionnee ce jour-la. Il rougit a la bascule.
4. **`YamlContentPackLoaderTest`, ligne 33, ne fait pas partie du perimetre**, contrairement a ce
   qu'`ADR-0049` et la story affirment tous deux. Son assertion porte sur la fixture
   `src/test/resources/fixtures/valide/contenu/paquet-question.yaml`, **hors** du repertoire que
   `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` balaie (`src/main/resources/contenu`). La
   toucher est un choix de coherence, jamais une obligation, et la toucher **a moitie** - l'assertion
   sans la fixture - est le seul geste qui rougit. L'erreur est la mienne, elle date d'`ADR-0049`,
   elle est recopiee depuis dans deux documents, et elle est corrigee ici.
5. **Zero ligne de code de production.** `ContentDescriptor.matiere()` n'est appele nulle part sous
   `src/main` ; la seule lecture est `YamlContentPackLoader` ligne 82, qui lit la cle sans la
   comparer.

**Le mecanisme n'a pas change et je le reconduis** : un seul commit portant tous les fichiers, parce
que tout etat intermediaire est rouge par construction, et **aucun garde-fou neuf n'est du** - la
regle d'unicite d'`ADR-0049` bloque symetriquement dans les deux sens. Le piege du remplacement
global reste entier : `adapters/adapter-content/src/test/resources/contreexemple/UneMatiereNAQuUne-
SeuleGraphieDansLeContenuServiTest/deux-graphies-de-francais/contenu/paquet-servi-en-ascii.yaml`
**doit rester en ASCII deliberement**, sous peine de casser la morsure du garde-fou. La bascule ne
touche que `src/main/resources/contenu/` et les deux fichiers Java nommes ci-dessus.

---

## 6. `S19-6` - Le pli entre 576 et 719 px (`SYN-16` / `BPM-8`)

### Verdict : **infaisable en l'etat sur la bande entiere telle que les criteres l'ecrivent.** Pas "cher" : ferme par une arithmetique, sur la seule famille de correctifs que la story et l'analyse de `lea-dev` envisagent. Une seconde famille reste ouverte, et **le chiffre qui decide entre les deux n'existe dans aucun fichier**.

C'est la partie ou la retro de s18 me dit exactement quoi faire : trois defauts de garde-fou de ce
sprint n'ont ete trouves qu'en appliquant un candidat concret et en mesurant. Je ne peux pas rendre
une mesure de navigateur - ce depot n'en a pas les moyens (`ADR-0025`) - alors je rends ce qui se
derive sans navigateur, et je nomme la mesure qui manque au lieu de l'inventer.

### 6.1 L'arithmetique de la largeur offerte, et ce qu'elle ferme

La fonction est deja epinglee par `ADR-0054` et `ADR-0055`, et son arithmetique est celle qu'`ADR-0056`
a confirmee sur le rendu : **largeur de rangee = plafond effectif - 24 px (rembourrage de
`.container`) - 16 px (bordure de `.cabinet`, 0,5 rem de chaque cote)**.

Elle se verifie sur les deux seuls points que `nadia-prof` a mesures au navigateur :

| viewport | plafond effectif | rangee derivee | rangee mesuree |
|---|---|---|---|
| 576 a 719 px | 540 px (feuille tierce) | 540 - 40 = **500 px** | **500 px** |
| 744 px (reference) | 720 px (regle maison de s18) | 720 - 40 = **680 px** | **680,00 px** |

La formule reproduit les deux mesures. J'en tire la borne que personne n'a ecrite :

> **A un viewport `V`, aucune declaration de largeur ne peut offrir a la rangee plus de `V - 40` px**,
> puisque la rangee vit a l'interieur du viewport, du rembourrage du conteneur et de la bordure du
> meuble.

Sur la bande visee, cette borne vaut **536 px a 576 px** et **679 px a 719 px**. Or la seule largeur
dont on sache qu'elle suffit aux cinq items fautifs est **680 px**. Consequences, dans l'ordre :

- **au sommet de la bande, un correctif de largeur est a un pixel de la seule valeur connue
  suffisante** - donc plausible, et impossible a affirmer sans mesure : 680 est **suffisante**, la
  largeur **necessaire** n'a jamais ete mesuree et elle est inferieure ou egale a 680 ;
- **au pied de la bande, le plafond atteignable est 536 px**, soit **+36 px** sur les 500 px
  d'aujourd'hui, la ou il a fallu **+180 px** pour que le defaut disparaisse. Un correctif de largeur
  ne tiendra le critere *"sur la totalite des seize items, entre 576 et 719 px inclus"* que si la
  largeur necessaire est inferieure a 536 px, ce qui contredirait la mesure a 500 px ;
- **et il faudrait effondrer entierement les marges pour y arriver**, ce que `SYN-14` refuse - la
  meme condition de `nadia-prof` qui a fait scoper le correctif de s18 a 720-767,98 px.

**Le choix que `lea-dev` a nomme n'a donc pas deux issues, il en a une.** Sa formulation - *"corriger
le pli dans cette bande effondre les marges ; preserver les marges y laisse le verdict sous le
pli"* - suppose que l'effondrement des marges corrigerait. Sur la moitie basse de la bande, il ne
corrige pas non plus.

### 6.2 La famille que personne n'a regardee, et elle est arithmetiquement plus proche

`nadia-prof` mesure la position du haut du bloc de verdict : **594,19 px** dans la bande, **474,19 px**
a partir de 720 px, contre un pli a **551 px** (reference 744 x 551, `DEC-0040`). Deux soustractions,
sur ses chiffres et non sur les miens :

- la seconde rangee de propositions coute exactement **120,00 px** ;
- **il manque 43,19 px** pour que le verdict repasse au-dessus du pli **sans que la seconde rangee
  disparaisse**.

Recuperer 43 px de budget **vertical** dans la seule bande 576-719 px est un probleme entierement
different, et bien plus petit, que fabriquer 180 px de largeur qui n'existent pas. Les termes
verticaux declares au-dessus du verdict sont lisibles dans `recreo.css` - le rembourrage de
`.zone-jeu` vaut a lui seul 32 px en haut - et **je m'arrete la volontairement** : lesquels peuvent
bouger est une question de forme et de lisibilite, donc `ines-design` propose et `nadia-prof`
tranche. Ce que j'apporte est que **cette famille existe, qu'elle est chiffree a 43,19 px, et qu'elle
n'a jamais ete instruite** parce que tout le monde cherchait de la largeur.

Une troisieme famille existe et je la nomme sans la recommander : reduire la largeur **demandee**
plutot qu'augmenter celle offerte. Les termes sont epingles par `ADR-0055` -
`.proposition { padding: 0.75rem 1.25rem }`, `.propositions { gap: 1rem }`. Ils ne sont sous aucun
plancher pedagogique (les planchers portent sur la police, 2 rem, et sur la cible tactile, 2,75 rem
de `min-width`, qu'un mot de 2 rem depasse largement). C'est le levier que j'avais moi-meme designe
en ouvrant `ADR-0055` comme *"le moins cher du correctif attendu"*, et il est aujourd'hui epingle
donc visible. Il reste un choix de forme, pas d'architecture.

### 6.3 Ce que le mecanisme doit tenir, quel qu'il soit

1. **Etre scope a la bande, et a elle seule** : `@media (min-width: 576px) and (max-width: 719.98px)`.
   Le troisieme critere de la story - ne rien faire regresser au-dela de 720 px - se tient par la
   forme de la declaration, pas par une verification a posteriori.
2. **Rougir dans la bonne table, dans le meme commit.** Une largeur touche `ADR-0054` ou `ADR-0055` et
   leur table epinglee ; un terme vertical n'est dans aucune des deux, et **aucun garde-fou de ce
   depot n'epingle un terme vertical**. C'est un trou reel, symetrique de celui qu'`ADR-0054` a
   ferme, et il faudra decider de le combler ou de le declarer non gardable (`ADR-0025`, `ADR-0051`).
   Je ne le tranche pas dans un avis de faisabilite : cela merite un `ADR`, et **c'est cet `ADR`-la
   que la story appelle si la famille verticale est retenue**.
3. **Ne pas evader le seuil de largeur par le nichage** : `ADR-0056` clause 1 juge une largeur en
   pixels sous une regle-`@` au meme titre qu'au niveau superieur, donc `ViewportEtLargeurTelephone-
   Test` verra la declaration. Son plafond est 744 px depuis `DEC-0040` ; une declaration bornee a la
   bande passe sous ce plafond.

### 6.4 Ce que je demande avant d'affecter la story

**Une mesure, et une seule : a quelle largeur de rangee les cinq items fautifs cessent-ils de se
replier ?** Elle se fait par la meme methode que celles de s18 - une regle injectee, un balayage de
largeurs - et elle vaut la difference entre "un correctif de largeur suffit au-dessus de 640 px" et
"aucun correctif de largeur ne marche". Sans elle, un lot travaillerait a l'aveugle sur une famille
que l'arithmetique donne perdante au pied de la bande. Elle appartient a `nadia-prof`, seule a
mesurer au navigateur ; `sofia-sm` decide si elle la demande avant ou pendant.

**En attendant, mon verdict d'affectation** : la story est **infaisable telle que ses criteres
l'ecrivent** si l'on entend les tenir sur la bande entiere par une largeur. Elle devient faisable
soit sur une bande reduite - ce qui est une decision de `camille-po`, pas la mienne - soit par la
famille verticale, moyennant l'`ADR` du point 6.3.2. Je ne recommande ni l'un ni l'autre : je dis ce
que chacun coute.

### 6.5 Amendement du 2026-08-24, meme jour - la mesure est rendue, mon verdict tombe

**Fait neuf** : `nadia-prof` a mesure la largeur de rangee **necessaire** que je nommais comme le
seul chiffre manquant. Elle vaut **539 px** (`FICHE-bandit-manchot-synonymes-cm1.md`, "Mesure du
2026-08-24", lot `s19-nadia`). Les sections 6.1 a 6.4 restent ci-dessus telles qu'elles ont ete
ecrites : elles etaient exactes sous l'information disponible, et c'est de la qu'il faut partir pour
lire ce qui suit.

**Ce que la mesure confirme.** L'arithmetique de la largeur offerte - `rangee = plafond - 40` - est
reproduite une quatrieme fois par ses trois campagnes (plafond 579 injecte, rangee mesuree 539,00).
**Et une cinquieme, que personne n'avait faite** : ses huit mesures de bords lateraux a plafond 579
se derivent toutes de `bord gauche = (V - min(V, 579)) / 2`, soit 0 - 0 - 0 - 0,5 - 10,5 - 30,5 -
60,5 - 70,0 px a 576, 578, 579, 580, 600, 640, 700 et 719 px de viewport. **Ce sont ses huit
chiffres, au dixieme.**

**Ce qui tombe, et je le nomme precisement plutot que de me couvrir derriere ce qui tenait.**

- **La ligne de mon tableau de tete est fausse** : j'y ecris *"l'arithmetique ferme la famille de
  correctifs qu'on croit avoir"*. Elle n'en ferme pas la famille, elle en ferme **3 largeurs de
  viewport sur 144**. Un correctif de largeur est faisable de **579 a 719 px** et echoue sur 576, 577
  et 578 px, faute des 3, 2 et 1 px que le viewport lui-meme retient. **`S19-6` n'est pas infaisable :
  elle est faisable sur 141 largeurs sur 144**, et savoir si le produit s'en contente est un
  arbitrage de perimetre qui appartient a `camille-po`.
- **Ma phrase litterale, elle, tient - et c'est precisement ce qui la rend trompeuse.** J'ecrivais
  qu'un correctif *"ne tiendra le critere sur la totalite des seize items, entre 576 et 719 px inclus,
  que si la largeur necessaire est inferieure a 536 px"*. C'est vrai : 539 > 536, la bande entiere
  n'est pas tenable. Mais un "infaisable" en tete de tableau, quand il porte sur 2 % de la bande, dit
  au lecteur autre chose que ce que le calcul dit. **Le defaut n'est pas dans l'arithmetique, il est
  dans le poids que j'ai donne a sa conclusion en ne sachant pas encore sur combien de pixels elle
  portait.** C'est exactement ce que le chiffre manquant decidait, et c'est pour cela que je le
  demandais.
- **Mon "+180 px" de la section 6.1 est un chiffre juste au mauvais endroit.** J'ecris *"la ou il a
  fallu +180 px pour que le defaut disparaisse"* : 180 px est la taille du correctif de s18, pas le
  besoin. Le besoin etait de **+39 px** (500 -> 539), et personne ne le savait.
- **Ma troisieme puce de 6.1 est fausse sur le fond** : j'ecrivais qu'*"il faudrait effondrer
  entierement les marges, ce que `SYN-14` refuse"*. `nadia-prof` a mesure `SYN-14` a huit viewports
  avec un plafond de 579 px et la trouve **tenue** - en-tete aligne aux huit largeurs, et 12,0 px du
  bord au pire cas, c'est-a-dire exactement le rembourrage de la zone et exactement le chiffre
  qu'elle avait deja accepte le 2026-08-23. Je supposais son refus au lieu de lire ce qu'elle avait
  ecrit.

**Ce qui reste exact et n'est pas rouvert** : la borne `rangee <= V - 40` est une propriete du
viewport et non de la feuille. Les trois pixels 576-578 sont hors de portee de **toute** valeur de
plafond, aujourd'hui comme demain.

### 6.6 Le mecanisme, maintenant qu'une famille est ouverte - `ADR-0059`

Ma section 6.3 disait ce que le mecanisme doit tenir quel qu'il soit. Il est nomme, decide et garde
par **`ADR-0059`**, ecrit dans ce lot avec sa regle executable, ses quatre morsures sur la feuille
maison reelle et un jeu fautif commite joue en cas vert et en cas rouge
(`UnPlafondMaisonNAffamePasLaRangeeTest`, `adapter-web`, 7 tests, 0 echec). En trois points, pour que
cet avis se lise seul :

- **le mecanisme** est une declaration maison de plafond lateral qui l'emporte sur celui de la
  feuille tierce **a l'interieur de la bande**, sur les memes selecteurs qu'a s18 - donc le mecanisme
  de s18 etendu vers le bas, pas un mecanisme neuf ;
- **le garde-fou** est un **critere**, la ou `ADR-0054` n'est qu'un **detecteur de changement** : il
  refuse tout plafond maison qui laisse a la rangee moins que la largeur mesuree necessaire. Ce qu'il
  interdit reellement est nomme et reproduit par une morsure - recopier le `540px` de la feuille
  tierce, mettre la table d'`ADR-0054` a jour, redevenir vert, et livrer un correctif qui ne corrige
  rien ;
- **la valeur du plafond n'est pas la mienne et n'est ecrite nulle part dans la regle.** Elle vit
  dans `recreo.css` ; la regle porte trois constantes nommees d'une ligne chacune - le necessaire
  mesure, le retranchement de la chaine, la reserve de police - et une inegalite,
  **`P >= L + R + 40`**. La reserve vaut zero tant que personne ne l'a tranchee, et zero est ici la
  valeur qui n'affirme rien. Le jour ou `camille-po` fixe la tolerance, une ligne de constante change
  et rien d'autre.

**Le fait qui rend ce routage possible, et il est demontre dans l'`ADR`** : la couverture est
**binaire** en la valeur du plafond. Au-dessus de `L + 40`, toute valeur couvre les **memes** 141
largeurs ; en dessous, aucune n'en couvre une seule. Ce que la valeur gouverne n'est pas ce qui est
corrige, c'est **ou le centrage commence** - donc une question de forme, pour `ines-design` et
`nadia-prof`, jamais un arbitrage d'architecture deguise.

### 6.7 La famille verticale garde-t-elle un interet - oui, et pour une autre raison qu'avant

**Comme route rivale, elle perd presque tout son interet.** Elle ne vaut plus que les trois pixels
que la largeur ne peut pas atteindre - 576, 577 et 578 px. Les instruire coute une campagne de mesure
et un `ADR`, pour 2 % de la bande. Je ne le recommande pas ; le prioriser ou non appartient a
`camille-po`, et `nadia-prof` note la meme chose de son cote : les deux familles ne s'excluent pas et
ne couvrent pas la meme chose.

**Comme garde-fou, elle en gagne, et c'est une raison plus forte que celle que j'avais.** Le
correctif de largeur ne vaut que par un chiffre **vertical** : le verdict remonte a 504,19 px pour un
pli a 551 px, soit **+46,81 px** de marge. Cette marge est tenue par des declarations que **rien** ne
compare a rien. Le rembourrage de `.zone-jeu` vaut 32 px en haut ; un lot de forme qui le porte a
2,5 rem en consomme 8, et aucun test de ce depot ne le dit. **Mon constat de la section 6.3.2 tient
donc, mais il ne porte plus sur une route alternative : il porte sur ce qui garde le correctif qu'on
va livrer.**

**L'`ADR` est le mien, il est du, et je ne l'ecris pas dans ce lot - le motif est ecrit dans
`ADR-0059`, section "Ce qui reste ouvert".** En deux phrases : le perimetre honnete n'est pas la
chaine d'ancetres du bloc de verdict mais **tout le flux qui le precede**, freres compris - un
garde-fou limite aux ancetres resterait vert pendant qu'un rembourrage de `.enonce` pousse le verdict
sous le pli, et ce serait une regle decorative ; et une table epinglee sur ce flux serait epinglee
contre une mise en page que le correctif de `S19-6` est sur le point de changer, donc perimee le jour
de son premier usage. **Declencheur date et nomme** : le premier lot qui touche un terme vertical du
flux au-dessus du verdict - correctif de `S19-6` compris - porte cet `ADR` et sa table, avec la
remesure de `nadia-prof` du meme commit. C'est une dette explicite, elle est la mienne, elle est
datee du 2026-08-24.

### 6.8 Mon verdict d'affectation, revise

**`S19-6` est faisable, moyennant `ADR-0059`, sur 579-719 px de viewport - 141 largeurs sur 144.**
Elle reste infaisable sur 576-578 px par une borne du viewport que rien ne leve. Ce qui reste a
decider avant qu'un lot commence n'est plus une mesure : c'est **la valeur du plafond**
(`ines-design` propose, `nadia-prof` tranche, sous la tolerance de `camille-po`) et **si couvrir 141
pixels sur 144 est ce que le produit veut** (`camille-po` seule). Aucune des deux n'est de moi, et
aucune des deux n'attend un chiffre qui n'existe pas.

**Un point de methode que je me dois a moi-meme.** `nadia-prof` ecrit que le chiffre manquait parce
que sa mesure de s18 l'avait laisse manquer, pas parce que j'avais mal cherche. C'est genereux et ce
n'est pas tout a fait suffisant : j'avais **680 px** comme suffisante et **500 px** comme
insuffisante dans le meme fichier, donc l'encadrement etait sous mes yeux, et j'ai conclu sur la
borne haute de cet encadrement plutot que d'ecrire que le verdict dependait d'un intervalle de 180 px
que personne n'avait resserre. C'est ce que la section 6.4 demandait, et c'est ce que le tableau de
tete a efface.

---

## 7. Question routee par `camille-po` - l'ordre de fusion entre `S19-5` et le lot des quatre paquets

### Ce que je tranche, et ce que je ne tranche pas

**Je ne tranche pas l'ordre : c'est un arbitrage de capacite, et il appartient a `sofia-sm`.** Le seul
critere qui separe les deux ordres est le nombre de fichiers a reecrire selon que les paquets neufs
naissent avant ou apres la bascule, c'est-a-dire du temps de developpeur, pas de l'architecture. **Ce
que je tranche, en revanche, c'est la contrainte exacte sous laquelle elle choisira**, parce qu'elle
est plus dure que ce que `camille-po` a nomme.

**La dependance n'est pas une, elle est double**, et la seconde n'est nommee nulle part :

1. **`UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest`** (celle que `camille-po` nomme) : une
   seule graphie par matiere sur tout le contenu servi, a chaque instant. Un paquet accentue depose a
   cote de treize paquets ASCII est rouge ; un paquet ASCII depose apres la bascule l'est aussi.
2. **`TexteAccentueExposeParLePortEntrantTest`** (celle qui manque au constat) : un inventaire de
   chaines litterales, **bidirectionnel**, dans `bootstrap`. Chaque paquet neuf y ajoute 6 chaines,
   dont sa matiere ; la bascule y modifie 5 chaines. **Les deux lots editent donc la meme liste dans
   le meme fichier**, ce qui est un conflit de fusion textuel en plus d'un conflit de graphie, et il
   ne se resout pas en changeant seulement le YAML.

**La contrainte exacte, ecrite pour qu'elle puisse choisir sans revenir vers moi.**

- **Si `S19-5` fusionne en premier** : les quatre paquets neufs naissent **accentues**, et leurs
  entrees d'inventaire aussi. Aucune reprise. C'est l'ordre a **zero reecriture**, et le seul cout
  est que `S19-5` doit etre pret en premier.
- **Si les paquets neufs fusionnent en premier** : ils naissent en **ASCII**, deliberement, avec le
  motif ecrit dans le fichier - exactement ce que `lea-dev` a fait pour `cm1-synonymes` a s17, et ce
  que sa Javadoc de `PaquetCm1SynonymesTest` explique. `S19-5` reprend alors **4 fichiers de plus**
  (les quatre YAML), **4 chaines de plus** dans l'inventaire, **et autant d'assertions de paquet**
  que les lots en auront ecrites sur le modele de `PaquetCm1SynonymesTest` ligne 232 - c'est ce
  dernier point qui a echappe a mon chiffrage de s17 et qui echappera au sien si personne ne
  l'ecrit.
- **Dans les deux cas, et c'est la seule chose qui n'est pas negociable : la bascule est un commit
  unique.** Tout etat intermediaire est rouge par construction, ce n'est pas une preference
  d'ordonnancement mais une propriete de la regle.
- **Dans les deux cas, `paquet-servi-en-ascii.yaml` reste en ASCII.** Il vit sous
  `src/test/resources/contreexemple/`, il est le jeu fautif qui prouve que le garde-fou mord, et un
  remplacement global de `Francais` dans le depot le detruirait sans qu'aucun test ne le dise autrement
  que par la perte d'une morsure. C'est le piege que j'avais nomme a s17 ; il n'a pas bouge.

**Mon avis, puisqu'elle le demandera** : le premier ordre coute strictement moins cher et n'a aucun
inconvenient d'architecture. Mais si `S19-5` n'est pas prete, faire attendre quatre paquets de
contenu pour une bascule de graphie serait payer une dette de forme avec de la couverture de
programme, et ce n'est pas a moi d'en decider.

---

## 8. Question routee par `nadia-prof` - le routage des deux lignes refusees

**Confirme, sans correction : ni les figures planes CP ni le decodage syllabique CP n'appellent une
mecanique neuve.**

Le critere est celui que j'ai etabli a s13 et que je maintiens : **une mecanique est definie par le
geste de l'enfant et par ce que le serveur en fait** (`GameMechanic.apply(session, coup)`). Sur les
deux lignes qu'elle route, le geste reste *presser l'une des propositions affichees*.

- **Figures planes, famille visuelle** (`FIG-1` a `FIG-4`) : ce qui change est la facon de **dessiner**
  une proposition - une figure dont le contenu declare les proportions au lieu d'un glyphe de police.
  `BanditManchot` tire un item, melange ses propositions, compare la chaine pressee : rien de tout
  cela ne change. Le cout est une **forme d'item** de plus (`Item permits ...`), un `type:` de plus
  dans le chargeur unique, et du rendu.
- **Decodage syllabique, audio par emission seule** (`SYL-1` et suivantes) : ce qui change est la
  facon de **presenter l'enonce**. Meme conclusion, meme cout, plus la production des sons - qui
  n'est pas de l'architecture et qu'aucun agent de cette squad ne peut produire, comme je l'ai ecrit a
  s13.

**Ce que cette confirmation ne dit pas, et qu'il faut lire avec elle.** "Aucune mecanique neuve" ne
veut pas dire "pas cher". Le visuel bute sur un fait que j'ai deja ecrit : **ce depot n'a aucun
garde-fou de rendu**, et six lignes de contenu visuel sont six increments dont la moitie de la
validation ne peut pas etre automatisee. L'audio bute sur la production des enregistrements. Les deux
sont des couts reels, ils ne sont simplement pas du cout de mecanique - et c'est bien la seule chose
qu'on me demandait de confirmer, pour qu'un arbitrage de sequencement ne les compte pas comme telle.

**Et le corollaire que je reconduis, parce qu'il est du meme geste** : les deux lignes d'operation
posee - multiplication posee, division euclidienne - **sont** une mecanique neuve, une **troisieme**,
et elles ne doivent jamais etre comptees avec la saisie. `nadia-prof` le confirme de son cote pour un
motif pedagogique different du mien ; les deux motifs vont dans le meme sens et c'est ecrit dans
`ADR-0058`.

---

## 9. Ce que j'ai ecrit dans ce lot, et ce que ma propre production coute

- **`ADR-0058`** - la mecanique de saisie, ses cinq decisions de conception, sa regle executable et
  les deux qui sont dues avec le code plutot qu'aujourd'hui.
- **Une regle ArchUnit de plus**, verte, avec ses deux morsures et ses deux jeux fautifs. Compte des
  regles, methode comprise : balayage des `.java` de l'arbre du worktree, `target/` et `.git/` exclus,
  champs `ArchRule` - **32 apres ce lot, 31 avant**, dont **2** vivent dans le faux depot de
  contre-exemple de `LesGardeFousSontMisALEpreuveTest` et ne s'executent jamais comme regles. Soit
  **30 regles reelles apres ce lot, 29 avant** : la clause d'`ADR-0058` est la **30e**. Sur ces 30,
  **29** portent `@ArchTest` ; la trentieme (`ScanDeComposantsTest`) est appelee par un `@Test`. C'est
  ce qui rendait le "29" des `ADR-0044` a `ADR-0046` inverifiable : ce compte-la designait les champs
  `ArchRule` reels, un comptage des seuls `@ArchTest` en rendait 28 sur le meme arbre, et les deux
  etaient exacts sans compter la meme chose.
- **Un defaut de production corrige dans le meme commit** : `ContentSource.indicesNommentLaReponse()`
  etait une methode `default`, c'est-a-dire du comportement dans un contrat ferme, et la regle neuve
  l'a trouve immediatement. 6 fichiers, une ligne chacun. Motif complet dans `ADR-0058`, section 4.
- **Une mesure qui vaut d'etre gardee pour les prochains comptages** : `LesReglesMordentTest` est
  execute par **deux** moteurs JUnit - 1 test par le moteur ArchUnit, 28 par Jupiter - et le rapport
  texte de `surefire-reports` n'en garde **qu'un**. Un comptage lu dans les rapports texte sous-compte
  donc ; le journal Maven, lui, porte les deux lignes. C'est la troisieme des trois causes de
  divergence que j'avais identifiees apres coup en s14, observee ici en direct.

---

## 10. Amendement du 2026-08-24 (cloture s19) - la morsure que mes sections 1 a 4 n'ont pas vue, et ce qu'un avis de faisabilite peut promettre

*Amendement, pas reecriture : les sections 1 a 9 restent telles qu'elles ont ete rendues, y compris la
phrase de la section 1 que cet amendement contredit.*

### 10.1 Ce que j'ai ecrit de faux, deux fois

`marc-dev` et `lea-dev` ont chacun decouvert par un rouge, independamment, sur deux stories
differentes, que `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` **mord des qu'un paquet a
propositions textuelles est depose** : sa clause `verifierQuAucunPaquetNEstEcarteEnSilence`
(`ADR-0051`) exige que tout paquet dont il n'examine aucun item se declare dans
`PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` **avec un motif non vide**, faute de quoi il est repute ecarte en
silence et le test rougit.

**Le probleme n'est pas que je l'aie omis - je l'ai nomme, et j'ai dit le contraire de ce qu'il fait.**

- Section 1, sur `S19-1` : *"`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` ne verra rien
  de ce paquet, et il faut le savoir plutot que de croire le contraire"*. Il ne verra rien **de la
  propriete de rang**, c'est exact ; et c'est precisement pour cela qu'il exige une declaration.
  J'ai transforme la condition de la morsure en preuve de son absence.
- Section 3, sur `S19-3` : *"les propositions sont textuelles donc hors du perimetre d'`ADR-0041` et
  d'`ADR-0051`"*. Faux pour `ADR-0051`, qui est l'ADR qui **cree** cette obligation - et que j'ai
  ecrit moi-meme.
- Sections 2 et 4, sur `S19-2` et `S19-4` : rien du tout.

Quatre stories, quatre paquets a propositions textuelles, quatre entrees ajoutees a
`PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` pendant le sprint (`cm1-accords-groupe-nominal`,
`cm1-accord-sujet-verbe`, `cm1-antonymes`, `cp-nature-mot`) - **quatre morsures qu'aucun de mes avis
n'annoncait**. Et je me suis fait mordre le meme sprint par `LesGardeFousSontMisALEpreuveTest`, qui a
refuse ma premiere version de regle faute de jeu fautif commite.

### 10.2 Ce que le prochain paquet a propositions textuelles doit savoir, d'avance

Tout paquet **dont aucun item n'a exactement trois propositions numeriques** - ce qui est le cas de
tout paquet de francais, et de tout paquet a deux ou quatre propositions quelle que soit la matiere -
doit, **dans le meme commit que le fichier YAML** :

1. entrer dans `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`
   (`adapters/adapter-content/src/test/java/com/recreo/adapter/content/LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java`) ;
2. y porter un **motif ecrit et mesure** - le nombre d'items, le nombre de distracteurs par item, et
   la raison pour laquelle aucune proposition n'a de valeur numerique, avec la date de la mesure. Une
   declaration vide echoue sur une clause dediee ;
3. savoir que la declaration **ne peut pas etre posee d'avance** : un paquet declare qui contribuerait
   malgre tout au calcul de rang rougit sur `declarationsPerimees`, et un paquet declare qui
   n'existerait plus rougit sur `declarationsFantomes`. La declaration se pose avec le paquet, jamais
   avant, jamais apres.

Ce paragraphe est ce que ma section 1 aurait du contenir.

### 10.3 La vraie question : un avis peut-il, par construction, enumerer les garde-fous qui mordront ?

**Non. Pas a la lecture, et pas par plus de soin.** Je le dis franchement plutot que de promettre un
inventaire plus applique la prochaine fois, parce que la promesse serait tenue par ma vigilance,
c'est-a-dire par rien - et que c'est exactement ce que ce depot refuse d'un garde-fou.

Trois raisons, dont la troisieme est la seule qui compte.

1. **Un garde-fou de ce depot derive son perimetre de la source** (`ADR-0013`, obligation 1). Sa
   population n'est donc pas une liste que je peux lire, c'est le resultat d'un predicat evalue sur un
   artefact qui **n'existe pas encore** au moment ou j'ecris l'avis. Enumerer exactement ce qui mordra
   demande d'evaluer ces predicats, c'est-a-dire d'executer les garde-fous, pas de les lire.
2. **Une morsure vient souvent de la clause secondaire, pas de la propriete que le nom annonce.** Ici,
   le nom promet une propriete de rang ; ce qui mord est une obligation de declaration greffee par
   `ADR-0051`. C'est la meme forme que le defaut de s11 - un `@ExceptionHandler` attrape un type et
   tout son sous-arbre, et nommer deux types n'est pas nommer deux situations. Un perimetre enumere a
   la lecture echoue par inclusion autant que par omission.
3. **La preuve est ma propre production, et elle est accablante.** J'ai inventorie a la main pour
   quatre stories, avec le fichier ouvert sous les yeux, et je me suis trompe quatre fois - dont deux
   fois en affirmant l'inverse du comportement reel d'un garde-fou dont j'avais ecrit l'ADR trois
   sprints plus tot. Une methode qui echoue dans ces conditions-la n'echoue pas par manque de soin.

**Ce qu'un avis de faisabilite peut donc promettre, et ce qu'il ne peut pas.** Il peut nommer les
garde-fous dont la story **change le perimetre** : un fichier depose dans un repertoire derive, une
entree de catalogue neuve, une declaration a tenir. Il ne peut pas garantir que cette liste soit
complete, et **il doit le dire**, sans quoi il vaut moins que rien : un developpeur qui lit une liste
qu'il croit exhaustive cesse de chercher.

**Ce qui remplace l'enumeration exhaustive, et qui coute deux minutes.** Deriver au lieu d'enumerer -
la doctrine d'`ADR-0013`, appliquee a mon propre livrable. Avant d'ecrire une ligne de contenu, le
developpeur depose un **artefact minimal de la bonne forme** (un paquet de deux ou trois items, du
bon type, du bon nombre de propositions, contenu bidon) et lance la suite du module :

```
bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test
```

Le `-am` n'est pas decoratif : sans lui la mesure porte sur les modules amont **installes**, pas sur
l'arbre (`CLAUDE.md`, prerequis de mesure). Ce que rend cette commande n'est pas une liste que
quelqu'un a devinee : c'est la liste **exacte** des garde-fous que cette forme d'artefact met en jeu,
produite par la machine, au debut de la story plutot qu'a la fin. Le rouge arrive quand il ne coute
rien - avant le contenu, avant l'arbitrage pedagogique, avant la fusion.

**Ce que je change dans mes avis a partir de maintenant** : toute section qui nomme des garde-fous se
termine par la phrase *"cette liste n'est pas exhaustive et ne peut pas l'etre ; la liste exhaustive
s'obtient par un depot minimal et un `test` du module, avant d'ecrire le contenu"*, suivie de la
commande. C'est un engagement sur **mon** livrable, que je tiens seul.

**Ce que je ne decide pas** : faire de ce depot minimal une etape obligatoire du deroulement d'une
story de contenu est une decision de **process**, donc de `sofia-sm`. Je la lui route, avec mon avis :
elle coute deux minutes par story, elle a evite trois rouges tardifs ce sprint-ci a elle seule, et
elle est la seule des deux methodes qui ne repose sur la vigilance de personne.

**Et pourquoi je n'en fais pas un `ADR`** : la regle porterait sur la facon dont un humain conduit une
story, pas sur une propriete du code. Je ne sais pas l'encoder en test - un depot Git ne peut pas
distinguer un `mvn test` lance avant d'un `mvn test` lance apres - et une regle que je ne sais pas
encoder n'est pas une regle. `CLAUDE.md` demande dans ce cas d'ecrire quel autre mecanisme la garde :
ici, aucun. C'est une pratique, elle est ecrite comme telle, et elle n'a pas la force d'une regle.
