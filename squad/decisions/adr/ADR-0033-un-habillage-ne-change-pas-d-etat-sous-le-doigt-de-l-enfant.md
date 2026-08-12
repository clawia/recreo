# ADR-0033 - Un habillage ne change pas d'etat sous le doigt de l'enfant

- **Statut** : Accepte
- **Date** : 2026-08-12
- **Auteur** : theo-archi
- **Sprint** : s10 (planning)
- **Rouvre** : le refus ecrit en "Alternatives considerees" d'`ADR-0030` ("Ecrire une clause de couleur
  pour couvrir tout C3"). `ADR-0030` reste **Accepte** : rien n'y est remplace, un seul de ses refus
  est renverse, et il l'est ici plutot que dans son propre fichier pour que l'index le montre.

## Contexte

Le sprint 10 dessine l'habillage du bandit manchot. Le perimetre est contraint : cet habillage sera
**dessine et immobile**, en `position: absolute` sur le bloc qui l'ancre, pour un cout de **zero
pixel** en largeur comme en hauteur. Ou il se pose et quelle forme il prend a change en cours de
sprint (section 7) ; la contrainte de cout, elle, n'a pas bouge, et c'est elle qui gouverne cet
`ADR`. Cette contrainte-la n'est pas esthetique, elle est mesuree :
le bas du cadre passe deja sous le pli sur **24 etats sur 33** de "Le bon verbe", avec **4 px de marge
sur le meilleur etat du pire jeu** (`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`,
section 7, releve de `nadia-prof` du 2026-08-11, pli a `y = 640`, bas de cadre a `540..636 px` sans
indice et `636..792 px` avec). Une bande soeur couterait 12 a 24 px et aggraverait nommement ce
constat.

**Ce qui manque n'est pas une regle de plus sur l'animation, c'est la moitie de C3 que j'ai laissee
dehors.** La condition C3 de `nadia-prof` (arbitrage prealable du 2026-08-11) dit : *"aucune animation,
aucun clignotement, aucun changement de couleur, aucune lumiere sur le cabinet lui-meme, a aucun
moment. Le mouvement appartient aux colonnes et a elles seules."* La clause 9 d'`ADR-0030` en a pris
ce qui **s'anime**. Ce que je lui avais laisse, en le nommant : *"un changement de couleur obtenu
autrement qu'en animant - un etat au survol, une transition, une lumiere peinte dans une image."*

Mesure sur la feuille servie, methode dans le meme paragraphe. Perimetre : les feuilles maison, soit
`static/` hors `static/vendor/` (`ADR-0014`), soit un seul fichier, `css/recreo.css`. **Zero
occurrence de `transition`.** Une seule occurrence litterale de `:focus`, ligne 216, et elle est
**dans un commentaire** - celui qui cite `.btn:focus-visible` de la bibliotheque ; comme la lecture du
garde-fou retire les commentaires avant de decouper les regles, ce qu'il voit vaut zero. Aucune
occurrence de `:hover`, `:active` ni `:target`. La feuille vendorisee en porte 47 (`:hover`) et 12
(`:focus-visible`), et aucune clause maison ne les lit.

**Consequence :** un `.cabinet-bandeau:hover::after { background-color: gold; transition:
background-color 0.3s }` ajoute dans six mois passe **toute** la CI verte. Mesure, pas hypothese : les
deux mutations reelles du tableau ci-dessous echouent sur cette classe seule, les 27 autres classes de
test du module restant vertes.

**Mon propre declencheur est atteint, et il faut etre precis sur lequel.** `ADR-0030` avait ecrit :
*"le jour ou une transition ou un etat au survol apparait sur un habillage, la question se rouvre avec
un objet reel a controler."* Ce declencheur-la n'est **pas** atteint : aucune transition n'existe. Ce
qui est atteint, c'est le motif du refus, et ce motif etait faux.

## Decision

### 1. La regle : aucun habillage ne change d'etat, et aucun etat interactif ne le conditionne

**Aucune regle maison dont le sujet nomme une classe d'habillage ne declare une propriete dont le nom
commence par `transition` ou par `animation`, et aucune n'est conditionnee par une pseudo-classe
interactive** (`:hover`, `:active`, `:focus` - donc aussi `:focus-visible` et `:focus-within` par
prefixe - et `:target`).

### 2. Pourquoi le refus d'`ADR-0030` etait faux, et c'est le coeur de cet `ADR`

`ADR-0030` refusait cette clause en ecrivant : *"la partie non animee de C3 n'a aujourd'hui aucune
population dans la feuille maison, et une clause sans objet est decorative."*

**La population d'une interdiction n'est pas l'ensemble de ses violations, c'est l'ensemble des sujets
qu'elle gouverne.** Cet ensemble n'est pas vide et ne l'etait deja pas : **33 regles maison ont pour
sujet une classe d'habillage**, sur **29** classes d'habillage derivees de **38** classes posees moins
**9** classes pressables (methode : sonde jetable non commitee sur `src/main/resources`, comptage des
couples selecteur-corps rendus par `reglesMaison` dont le sujet nomme une classe d'habillage ; une
regle ecrite avec deux selecteurs compte pour deux).

Et le pire : **`ADR-0030` a accepte sa propre clause de rognage sur exactement ce critere-la**, en
l'ecrivant noir sur blanc - *"aucune feuille maison ne declare de propriete de rognage, donc sur la
feuille livree elle ne mord sur rien. Ce n'est pas pour autant un vert a vide de la mauvaise espece
[...] sa population d'ancetres est reelle et derivee."* Deux clauses, la meme situation, deux verdicts
opposes dans le meme fichier. Ce n'est pas un arbitrage, c'est une incoherence, et je la corrige dans
le sens qui garde le plus.

### 3. Le perimetre est derive par **complementaire**, jamais enumere

`ADR-0013` obligation 1. L'habillage n'est pas une liste de noms : c'est **toute classe posee dans un
gabarit, moins toute classe portee par un element `button, a[href]`**.

Le pressable est derive par **le meme litteral** qu'`ADR-0017` et qu'`ADR-0030`, et par **la meme
lecture de classes**. Ce n'est pas de la coquetterie : `LHabillageResteImmobileTest` definit l'habillage
comme le complementaire de ce que `LHabillageNeRogneJamaisUnElementPressableTest` appelle pressable.
Deux lectures libres de diverger auraient laisse une classe sortir de la population des ancetres ici et
de celle de l'habillage la-bas, donc gardee **par ni l'un ni l'autre**. Le litteral et la lecture sont
donc exposes dans le paquet et lus par les deux.

Un manche, un fronton, un socle, une bande future entrent dans le controle **le jour ou le gabarit les
pose**, sans que le fichier de test change.

### 4. La liste des proprietes interdites n'est pas ecrite, elle est **prefixee**

`ADR-0013` obligation 2. La clause ne cherche pas quatre noms devines comme le fait
`DECLARATIONS_D_ANIMATION` d'`ADR-0026` : elle lit les noms **reellement declares** par le corps de la
regle et refuse tout nom qui commence par `transition` ou par `animation`. Elle attrape donc aussi
`transition-behavior`, `animation-timeline`, et ce qu'un navigateur ajoutera apres cet `ADR`. C'est
pour cela que `proprietesDeclarees` devient visible dans son paquet.

### 5. `transform` n'entre pas dans la liste, et l'exclusion est mesuree et cliquetee

**Verifie plutot que suppose.** Les deux barres de la croix du signal faux declarent
`transform: rotate(45deg)` sur `.signal.est-faux .icone-signal::before` et `::after`, et
`icone-signal` **est** une classe d'habillage au sens derive - aucun bouton ne la porte. Mesure : en
ajoutant `transform` a la liste des prefixes, la clause rougit sur ces deux regles exactement, avec le
message d'immobilite. Un decor statique legitime serait donc refuse.

**La clause interdit le changement d'etat, pas la geometrie** : une rotation declaree une fois pour
toutes ne change jamais, elle est. Et l'exclusion n'est pas laissee a la bonne foi d'un lecteur futur :
`laCroixDuSignalFauxDeclareTransformSurUnHabillageEtResteVerte` epingle les deux faits dont elle
depend - qu'il existe reellement un habillage declarant `transform`, et que la clause est verte sur la
production. Le jour ou quelqu'un ajoute `transform` "par symetrie", c'est ce test qui le lui dit avec
le motif. Le jour ou la croix disparait, c'est lui qui dit que l'exclusion n'est plus une exception
constatee mais une question ouverte.

### 6. Ce que la clause laisse passer, et c'est voulu

**Un changement d'etat decide par le serveur.** `.signal.est-juste` et `.signal.est-faux` changent
l'aplat du bandeau de verdict selon une classe que le serveur pose (`ADR-0008`). Ce que la clause
refuse est le changement decide par le **client**, sans que le serveur en sache rien - la meme
frontiere qu'`ADR-0008`, lue du cote du CSS.

**Une lumiere peinte dans une image ou dans un degrade.** Elle ne declare ni transition ni etat
interactif ; elle reste a `nadia-prof` (`ADR-0025`). Ce que je prends de C3 s'arrete la, et ce que je
lui laisse ne s'allonge pas.

### 7. Contrainte de mise en oeuvre : entrer un selecteur dans le perimetre epingle

Cette section etait ecrite sur `.cabinet-bandeau::after`, le manche du fronton. Ce selecteur n'est
probablement pas celui qui existera : `nadia-prof` a refuse le manche **apres l'avoir vu rendu** - a
20 x 24 px il se lit comme un petit rectangle blanc - et demande un levier a boule sur le flanc du
meuble, plus un visage souriant et une enseigne nommee sur le fronton, redessines en CSS pur d'apres
deux images de reference. Cet arbitrage est produit et pedagogique, il ne m'appartient pas et je ne
fais que l'enregistrer ; ce qui m'appartient est sa consequence d'architecture : **les selecteurs a
venir ne sont ni connus ni uniques, donc la contrainte ne peut pas etre ecrite sur un nom de
selecteur.** Elle est ecrite sur le geste.

**a. Tout selecteur qui entre dans `SELECTEURS_DU_CABINET` recoit son bloc fautif dans le jeu
`contreexemple/CabinetDeLaMachineTest/dimension-en-pixels/`.** Trois obligations, et aucune ne depend
de la position du selecteur dans la liste :

1. le selecteur existe dans la feuille maison de production, et il n'y declare aucune dimension en
   pixels (rem ou pourcent) ;
2. le **meme** selecteur existe dans le jeu fautif, ou il porte une dimension en pixels ;
3. rien n'est a ecrire dans le test : l'attente du contre-exemple est **derivee** de la constante.
   Un selecteur ajoute a la liste sans son bloc fautif fait rougir le contre-exemple, en le nommant.

**Ce que j'avais ecrit ici etait un piege nomme et laisse ouvert, et je l'ai mesure sur moi-meme.**
La version precedente de cette section demandait que le jeu fautif recoive le bloc du selecteur neuf
avec sa dimension en pixels, et s'arretait la. Applique a la lettre, selecteur ajoute **apres**
`.cabinet-bandeau::before` : `Tests run: 8, Failures: 0`. Vert. Parce que la boucle echouait au
**premier** fautif rencontre - `.cabinet-bandeau::before`, qui portait deja `px` dans ce jeu - et que
`hasMessageContaining` epinglait ce nom-la en clair. Le selecteur neuf n'etait jamais eprouve, et
aucun rouge ne le disait. Deplacee **avant**, la meme contrainte rougissait sur l'assertion de
message, c'est-a-dire pour la mauvaise raison. Une contrainte dont le resultat depend d'un ordre est
une contrainte qu'un developpeur applique de travers sans le savoir.

**La correction n'est donc pas une consigne mieux ecrite, c'est une forme de garde-fou qui rend
l'ordre sans effet.** `verifierAucunePixelSurLeCabinet` rassemble desormais **tous** les selecteurs
fautifs avant d'echouer, au lieu d'echouer au premier, et
`chaqueSelecteurEpingleEstEprouveParLeJeuFautif` derive son attente de `SELECTEURS_DU_CABINET` en
exigeant que le message nomme **chacun** d'eux. Le jeu fautif porte en consequence une dimension en
pixels sur chaque selecteur epingle, et non sur un seul.

Fichiers :
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/CabinetDeLaMachineTest.java`,
`adapters/adapter-web/src/test/resources/contreexemple/CabinetDeLaMachineTest/dimension-en-pixels/static/css/recreo.css`.

Morsure de la forme neuve, mesuree sur `mvn -pl adapters/adapter-web test -Dtest=CabinetDeLaMachineTest`,
JDK 21, chaque mutation annulee ensuite (`git diff` confirme qu'aucune ne subsiste). Le selecteur de
mesure est un nom jetable, sans rapport avec ceux que le lot livrera :

| Mutation | Attendu | Mesure |
|---|---|---|
| selecteur ajoute a la liste et a la production (en rem), jeu fautif non touche, **en fin de liste** | rouge nommant le selecteur neuf | **rouge, 1 echec sur 8**, sur `chaqueSelecteurEpingleEstEprouveParLeJeuFautif` : attendu `"<s> ne doit declarer aucune dimension en pixels"`, trouve `"le selecteur <s> doit exister dans le CSS maison"`. La clause de production reste verte. C'est le cas qui passait **vert** avant cette refonte |
| le meme, **en tete de liste** | le meme rouge, le meme motif | **identique** : l'ordre n'a aucun effet, ce qui est la propriete visee |
| jeu fautif complete par le bloc du selecteur neuf, en pixels | vert | **vert, 8 cas** : le selecteur neuf est desormais eprouve |
| une dimension en pixels dans un bloc de production | rouge sur la clause de production | **rouge** sur `lesDimensionsDuCabinetSontExprimeesEnRemOuEnPourcentJamaisEnPixels`, message nommant `.cabinet-socle`. La refonte n'a pas emousse la clause qu'elle reecrit |

**Consequence contre-intuitive, a dire plutot qu'a laisser decouvrir.** Tant que la boucle echouait au
premier fautif, le contre-exemple ne prouvait **qu'un seul** selecteur a la fois, celui que l'ordre
faisait rencontrer d'abord ; les quatre autres etaient dans le perimetre sans etre eprouves, et
`ADR-0013` obligation 3 etait honoree pour un cinquieme de ce qu'elle avait l'air de couvrir. Ce
n'etait pas absurde - le perimetre etant derive de la constante, la clause de production couvre tout
selecteur des qu'il est epingle - mais ce n'etait pas ce que le nom du test laissait croire. Depuis
cette refonte, les deux sont vrais en meme temps.

**b. L'ancrage est `position: relative` sur le bloc qui porte le pseudo-element**, et il ne change
aucune dimension. Rien d'autre ne doit apparaitre : ni `overflow` (`ADR-0030` point 2), ni
`transition`, ni pseudo-classe interactive (cet `ADR`), ni `content` autre que la chaine vide
(`ADR-0027` clause 7), ni image chargee (`ADR-0030` point 3) - y compris pour un decor tire d'une
image de reference, qui est **redessine** en regles statiques et jamais charge.

Une consequence de ces regles-la, qui vaut d'etre nommee avant le lot puisqu'une enseigne nommee est
demandee : **un mot que l'enfant lit ne peut pas etre un decor.** `content` est limite a la chaine
vide (`ADR-0027` clause 7), donc le texte d'une enseigne est du texte rendu par le serveur dans le
gabarit, soumis comme tout texte d'interface a l'exigence de francais integralement accentue de
`DEC-0002`. Ce que ce mot dit reste a `nadia-prof` ; ou il vit est deja decide par les regles
existantes.

## Regle executable

`adapters/adapter-web/src/test/java/com/recreo/adapter/web/LHabillageResteImmobileTest.java`

Cinq cas. Le fichier porte la propriete, sa mesure et ses limites ; ce qui suit est la preuve qu'il
mord.

### Preuve de morsure - mutations reelles sur la feuille livree

Chacune ajoutee a `recreo.css`, mesuree sur `mvn -pl adapters/adapter-web test`, puis annulee ;
`git diff` confirme qu'aucune ne subsiste.

| Mutation | Attendu | Mesure |
|---|---|---|
| `.cabinet { transition: background-color 0.3s }` | rouge sur cette clause, **verte partout ailleurs** | **rouge sur `LHabillageResteImmobileTest` seul** (2 cas en echec) ; les **26** autres classes de test du module restent vertes (methode : `grep -rl "@Test\|@ArchTest"` dans `adapters/adapter-web/src/test/java`, 27 classes dont la mienne). C'est ce "vertes ailleurs" qui etablit qu'aucune clause anterieure ne voyait le defaut |
| `.cabinet-bandeau:hover { background-color: #ffd54f }` - un changement de couleur **sans** transition, donc instantane | rouge sur cette clause, verte ailleurs | **rouge sur `LHabillageResteImmobileTest` seul** (2 cas), les 26 autres vertes. La clause 9 d'`ADR-0030` ne regarde que ce qui declare une animation, et celle-ci n'en declare aucune |
| `transform` ajoute a la liste des prefixes interdits | la croix du signal faux serait refusee | **rouge, 2 cas**, message nommant `.signal.est-faux .icone-signal::before` et `::after`. C'est la mesure qui etablit l'exclusion, plutot que de l'argumenter |
| `.cabinet-bandeau::after { clip-path: polygon(...) }` - un decor decoupe porte par un pseudo-element du fronton | **vert** sur le garde-fou de rognage d'`ADR-0030` | **vert** : `classesDuSelecteur(".cabinet-bandeau::after")` rend `{cabinet-bandeau}`, absent de la population des ancetres de pressables. Un decor dessine est faisable sur un pseudo-element de ce bloc |
| `.cabinet .cabinet-bandeau::after { clip-path: polygon(...) }` - le meme, avec l'ancetre nomme dans le selecteur | rouge | **rouge** sur `LHabillageNeRogneJamaisUnElementPressableTest`. Le garde-fou lit les classes du **selecteur entier**, pas de son sujet : nommer `.cabinet` devant suffit a le declencher. Contrainte pour le lot : ecrire le selecteur court |
| `.cabinet::after { clip-path: polygon(...) }` - le meme decor accroche au cabinet plutot qu'au fronton | rouge | **rouge** sur le meme garde-fou. `cabinet` est un ancetre reel des colonnes depuis S8-1. Contrainte pour le lot, et elle porte sur le levier de flanc autant que sur le fronton : un decor decoupe ne pend pas de `.cabinet`, il pend d'un bloc qui n'est ancetre d'aucun pressable |

### Mise a l'epreuve sur objets neufs (`ADR-0013` obligation 3)

Trois jeux d'artefacts sous
`adapters/adapter-web/src/test/resources/contreexemple/LHabillageResteImmobileTest/` :

- **`manche-qui-s-allume`** : le manche du sprint 10 tel qu'il est decide - dessine, en position
  absolue, a cout nul - plus une seule regle de trop, `.cabinet-bandeau:hover::after` avec sa
  transition. C'est le cas le plus tentant de tous : un manche qui reagit au survol a l'air d'un manche
  qui fonctionne. Le rouge nomme les **deux** membres de la clause ;
- **`manche-immobile`** : le meme, corrige **par le seul retrait de cette regle**. Le manche garde son
  `content`, sa position absolue, sa decoupe, sa couleur et ses dimensions, et il est vert. Sans ce
  jeu, la clause serait une interdiction du manche deguisee en clause d'immobilite et aucun jeu fautif
  ne le dirait - la lecon de la seconde mutation d'`ADR-0025` ;
- **`animation-sous-mouvement-reduit`** : **un trou de la clause 9, mesure plutot qu'affirme.** La
  population des animations d'`ADR-0030` ecarte les regles ecrites sous `prefers-reduced-motion` - il
  fallait bien qu'elle les ecarte, c'est la qu'un repli declare `animation: none` (`ADR-0026` clause 2).
  Consequence non voulue : un habillage qui clignote **uniquement** pour l'enfant qui a demande un ecran
  immobile echappe entierement aux clauses 1, 2, 5, 6 et 9, qui partagent cette population. Le test
  l'etablit dans les deux sens sur le meme jeu : **la clause 9 reste verte, celle-ci rougit.** C'est la
  reponse a "qu'est-ce que cette clause interdirait reellement qu'une autre n'interdit pas".

### Visibilites ouvertes dans le paquet, et pourquoi chacune

Trois membres passent de `private` a visible dans `com.recreo.adapter.web`, chacun avec la Javadoc du
motif :

| Membre | Fichier | Motif |
|---|---|---|
| `sujet(String)` | `LAnimationRetardeMaisNeDetermineRienTest` | la clause d'immobilite doit decouper le sujet d'un selecteur **exactement** comme la clause 9 le decoupe, sinon les deux garde-fous cessent de s'accorder sur ce qu'une regle habille |
| `proprietesDeclarees(String)` | `LAnimationRetardeMaisNeDetermineRienTest` | la derivation par prefixe (point 4) exige de lire les noms declares, pas de tester des noms devines |
| `PRESSABLES`, `motsDeClasse(Element)` | `LHabillageNeRogneJamaisUnElementPressableTest` | l'habillage est le complementaire du pressable (point 3) : un seul litteral et une seule lecture, pour les deux clauses |

## Limite nommee, et elle joue dans le mauvais sens

La surcollecte des expressions Thymeleaf est inoffensive quand elle grossit une population interdite -
c'est ce qu'ecrivent `ADR-0026` et `ADR-0030`. **Ici elle grossit le pressable, donc elle retranche de
l'habillage**, et son mode de defaillance est un vert, non un rouge.

Mesure : sur les gabarits livres, elle ne retranche **qu'un jeton**, `enSuite`, tokenise depuis
`th:class="${enSuite} ? 'btn retour-accueil' : 'btn sortie-pleine'"` dans `fragments/retour.html`. Ce
n'est qu'un nom de variable de modele, la classe de rien, et aucune regle maison ne le nomme. Le jour ou
une variable de modele portera le nom d'une classe d'habillage reelle, cette classe sortira
silencieusement du controle. Le remede n'est pas de deviner les expressions : c'est qu'un nom de classe
d'habillage ne soit pas aussi un nom de variable, ce que `HabillageDesEcransTest` rend deja visible en
exigeant qu'une classe posee soit nommee par une regle servie. **C'est le declencheur ecrit : le jour
ou ces deux vocabulaires se recouvrent, cette clause doit etre reprise.**

## Consequences

- La moitie non animee de C3 cesse d'etre gardee par la seule vigilance de `nadia-prof`. Ce qui lui
  reste de C3 s'abrege d'autant, et je ne prononce aucun `Done` pedagogique.
- Un habillage dessine et immobile est **faisable en l'etat**, sans `ADR` supplementaire, sous les deux
  contraintes du point 7 et sur un bloc qui n'est ancetre d'aucun pressable. La mesure a ete faite sur
  un pseudo-element du fronton, seul emplacement connu quand cet `ADR` a ete ecrit ; le levier de flanc
  et l'enseigne demandes depuis n'ont pas besoin d'un autre `ADR`, mais leurs selecteurs passent par le
  point 7.a et par la troisieme ligne du tableau de morsure ci-dessus.
- **Mauvaise consequence assumee, et elle est reelle** : `:focus-within` sur un habillage devient rouge.
  C'est un motif d'accessibilite legitime dans d'autres contextes - cadrer un conteneur quand un de ses
  enfants a le focus. Ici, l'anneau de focus vit sur le bouton (`ADR-0030` point 2) et C3 refuse toute
  lumiere sur le cabinet : je refuse donc le motif, en le nommant. Le jour ou `nadia-prof` demande un
  signal de focus sur un habillage, cet `ADR` doit etre repris - c'est le second declencheur ecrit.
- **Seconde mauvaise consequence** : une clause de plus sur la feuille maison, donc une facon de plus
  de rendre un lot rouge pour une raison qui n'est pas celle du lot. Elle porte sur 33 regles, soit la
  quasi-totalite de la feuille hors boutons.
- **Un trou est nomme et non ferme** : les clauses 1, 2, 5, 6 et 9 d'`ADR-0026`/`ADR-0027`/`ADR-0030`
  ne voient rien de ce qui est ecrit sous `prefers-reduced-motion`. Cet `ADR` le ferme pour l'habillage
  seulement. **Sur un element pressable, une animation ecrite uniquement sous `prefers-reduced-motion`
  echappe toujours a tout** - elle n'aurait ni fin garantie, ni marqueur de geste, ni borne de duree,
  ni image finale comparee. C'est une dette ouverte ici plutot qu'une decouverte future ; le remede
  serait de rendre la population de ces clauses independante du contexte de regles-@, et il n'est pas
  dans le perimetre du sprint 10.
- **Aucun octet de ce que voit un enfant n'est change par cet `ADR`.** Il n'ajoute que du test.

## Alternatives considerees

**Amender `ADR-0030` d'une clause 11 plutot qu'ecrire un `ADR`.** C'etait la forme la plus economique,
et je l'ecarte pour une raison de gouvernance. Cet `ADR` **renverse un refus explicitement ecrit dans
`ADR-0030`** et etablit que son motif etait faux. Un renversement loge dans le fichier qui portait le
refus n'apparait pas dans `ADR-index.md` : un lecteur de l'index verrait `ADR-0030` inchange et
n'aurait aucun moyen de savoir qu'une de ses positions ne tient plus. Le precedent de l'amendement
d'`ADR-0027` (clause 8, 2026-08-10) ne couvre pas ce cas : il ajoutait une **troisieme forme
realisable** d'une propriete deja decidee, il ne renversait aucun refus. `ADR-0030` reste donc
`Accepte`, et cet `ADR` porte le renversement sous son propre numero.

**Enumerer les proprietes interdites comme le fait `DECLARATIONS_D_ANIMATION`.** Ecartee au profit du
prefixe (point 4) : une enumeration echoue par omission le jour ou une propriete nouvelle arrive, et
`ADR-0026` en porte deja la trace - son plafond de duree ne voyait pas `animation-delay` ecrit hors du
raccourci, et 149 cas sur 149 passaient verts.

**Inclure `transform`, par symetrie avec `animation`.** Ecartee sur mesure et non sur principe : elle
rougit la croix du signal faux, un decor statique legitime (point 5). La symetrie aurait ete jolie et
fausse.

**Ecrire une clause qui verifie qu'aucune couleur du cabinet ne change au rendu.** Ecartee, meme
analyse qu'`ADR-0025` et qu'`ADR-0028` : c'est une propriete du rendu, et un test qui lance un
navigateur rend la suite dependante d'un binaire que `mvn verify` ne garantit pas. La moitie
declarative est prise, la moitie de rendu reste a `nadia-prof`.

**Attendre le lot du manche pour ecrire la clause.** Ecartee, et c'est le meme choix de calendrier
qu'`ADR-0030` : une regle qui arrive apres le lot arrive apres la revue qui l'aurait attrapee. Le
manche est precisement l'objet sur lequel un survol est tentant.

## Verification

`mvn -B clean verify`, sur un JDK **21.0.10** designe par `JAVA_HOME` le temps de la commande (le
`java` du `PATH` de ce poste est un JRE 8, et le fichier de toolchains y est enregistre sous un nom que
Maven ne lit pas ; constat rendu par `bash outils/verifier-jdk.sh`) : **BUILD SUCCESS**.

**Chiffres et methode dans le meme paragraphe.** Comptage valide **apres un `clean`**, lu sur les
lignes `Results:` du journal, module par module : `domain` 43, `application` 7, `adapter-web` 183,
`adapter-content` 63, `bootstrap` 77 en surefire et 3 en failsafe. Total **376 tests**, contre 371 sur
`round-two` a `b15bda1` : les 5 de plus sont les 5 cas de `LHabillageResteImmobileTest`, et rien
d'autre. Le compte de `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` est inchange a 4 - un test
supprime (le second parcours qui relisait le motif a cote) et un test ajoute (le troisieme jeu
d'artefacts) - ce qui est exactement pourquoi `adapter-content` n'a pas bouge.

**Regles ArchUnit : 28, inchange.** La clause de cet `ADR` n'est pas une `ArchRule` - ArchUnit lit du
bytecode, ni une feuille de style ni un gabarit n'y sont visibles. Methode identique a celle
d'`ADR-0030`, et rejouee plutot que relayee : recherche de la forme `ArchRule <nom> =` dans les
`src/test/java` des cinq modules porteurs de tests declares au pom agregateur, executee dans ce
worktree seul - **28**. Le meme balayage rend **27** annotations `@ArchTest` : la 28e regle est celle
d'`AssertionsWebTest`, executee par un `.check()` explicite dans son propre contre-exemple.
