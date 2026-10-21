# ADR-0054 - La largeur offerte a une rangee repliable est une fonction epinglee, jamais un effet de bord d'un seuil tiers

- **Statut** : Accepte, sa regle ecrite dans le meme commit
  (`LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest`, dix tests : une clause de production,
  sept morsures sur materiau reel, et un jeu fautif commite a noms renommes - un cas vert, un cas
  rouge).
- **Sprint** : s17, lot hors sprint. **Date** : 2026-08-22. **Auteur** : theo-archi.
- **Arbre mesure** : `round-two` a `db2bff6` (SHA releve par `git rev-parse round-two` sur le
  worktree neuf, pas repris d'un message de commit).
- **Route par** : `nadia-prof`, qui nomme la propriete en section 4 de
  `squad/conformite/mesure-hauteur-utile-nouvelle-cible.md` (branche `s17-nadia-hauteur-utile`, non
  fusionnee) : *"la propriete 3 de la section 4 est un trou de garde-fou, et sa cause est technique.
  Je decris le symptome, je ne propose pas de mecanisme."*
- **Complete** : `ADR-0014` (vendorisation et ordre de chargement), `ADR-0020` (le document
  assemble), `ADR-0025` et `ADR-0028` (ce qu'un rendu ne laisse pas garder), `ADR-0046`
  (amendement 3, dont une limite heritee est traitee ici pour cette propriete), `ADR-0053`
  (comparer l'issue plutot qu'une inegalite), `ADR-0013`.

## Contexte

`nadia-prof` mesure au navigateur reel, le 2026-08-22, sur 160 tirages, que **la largeur effective de
la rangee de propositions est une fonction en escalier du viewport** : 500 px sur toute la bande
576-767 px, 680 px des 768 px. Vingt-quatre pixels de viewport font passer les seize items de
`cm1-synonymes` de *"cinq items a deux rangees"* a *"tous sur une rangee"*, et le pire bas d'un ecran
de manche de 531,19 a 411,19 px. La marche est decidee par une declaration de la feuille tierce que
ce depot ne possede pas.

**Le fait technique, verifie sur la feuille servie.** `main#jeu` porte `container zone-jeu`.
`.container` ne declare aucun plafond au niveau superieur du fichier : ses cinq plafonds vivent
**exclusivement sous `@media (min-width: ...)`**. Aucune lecture aplatie des feuilles ne les voit -
et c'est la seule lecture que ce module avait.

**L'arithmetique se ferme, et c'est ce qui rend la propriete gardable.** Chaque terme entre le
plafond et la rangee est une declaration litterale : plafond 540, rembourrage lateral de `.container`
2 x 12 px, bordure de `.cabinet` 2 x 8 px, `box-sizing: border-box` qui prend le rembourrage
**dans** le plafond. 540 - 24 - 16 = **500**, et 720 - 24 - 16 = **680**. Ce sont, au pixel, les deux
chiffres que `nadia-prof` a mesures au navigateur. **Le modele declaratif n'est pas une supposition de
ma main : il est valide contre une mesure de rendu reel.**

### Ce que la CI voyait deja, et ce qu'elle ne voyait pas

`nadia-prof` nomme trois facons de perdre la propriete. Elles n'ont pas le meme statut, et je le
mesure au lieu de le supposer - le gabarit servi mute du geste exact, puis
`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test` sur l'arbre mute (JDK 21.0.10, mesure
partielle, `-am` porte) :

1. **Un retrait de `container` sur `#jeu` : deja rouge, depuis s06.**
   `EnTeteEtMenuTest.leContenuDeLAccueilEstCentreDansUneLargeurMaximale` echoue, avec le motif
   *"S6-2 : la zone de jeu porte .container, qui centre et borne sa largeur"*. Cette clause a ete
   ecrite pour le **centrage**, elle assertionne `main#jeu.container` par un nom litteral et ne dit
   rien de l'escalier - mais elle tient le fait. **Je ne compte donc pas ce trou-la comme ouvert, et
   je ne le reclame pas.** La mesure de `nadia-prof` est exacte sur le symptome et attribue a ce
   geste un risque que la CI portait deja.
2. **Un changement de version de la bibliotheque : rouge, puis vert, et muet sur ce qui compte.**
   `ADR-0014` et `RessourcesTiercesTest` epinglent le SHA-256 de `bootstrap.min.css` : une mise a
   jour **ne peut pas** etre muette. Mais celui qui la fait recalcule l'empreinte, la CI redevient
   verte, et **rien ne lui dit que la largeur offerte a la rangee a change**. Le garde-fou existant
   protege l'octet, pas la consequence.
3. **Une regle de feuille ajoutee ailleurs : entierement muet.** `max-width: 320px` sur `.zone-jeu`
   passe `ViewportEtLargeurTelephoneTest`, dont la population est celle des largeurs declarees
   **au-dela** de la cible telephone - 320 ne depasse rien, et `max-width` n'est pas `width`. Sous
   `@media`, elle est invisible en plus a toute lecture aplatie du module. Un rembourrage lateral
   **ajoute** sur `.cabinet` passe `LeConteneurTierGardeSonRembourrageLateralTest`, dont la faute est
   un rembourrage remis **a zero** sur un element portant une classe de conteneur tiers - `.cabinet`
   n'en porte aucune, et le geste est l'inverse de celui qu'il attrape.

**La cause d'un mode de defaillance nomme deux fois ce sprint** : un garde-fou vert qui n'a jamais
rencontre l'objet dont on croyait qu'il le tenait.

### Ce que je corrige de la mesure qui me route

Sur un point, et il commande la forme de la decision. `nadia-prof` ecrit qu'un retrait de `container`
*"ramenerait la rangee vers 320 px"*. **Ce geste ne retrecit pas la rangee, il l'elargit** : sans
plafond, `main` prend toute la largeur du viewport, et la rangee vaut 728 px a 744 px de viewport au
lieu de 500. Sa mesure n'est pas en cause - elle porte sur l'etat servi - c'est le sens du risque qui
est inverse. Et cela change tout pour le mecanisme : **un cliquet a plancher resterait vert sur le
geste meme qu'elle designe**, tout en rendant caducs les seize chiffres de son tableau 2.3.

## Decision

**Epingler la fonction, pas un point de cette fonction, et exiger l'egalite dans les deux sens.**

1. **Toute rangee repliable du document que l'enfant a sous les yeux a une et une seule largeur
   gouvernee sur sa chaine d'ancetres.** Zero est un echec (la rangee prend la largeur du viewport
   sans plafond) ; plusieurs est un echec (le garde-fou ne sait plus laquelle gouverne).
2. **L'escalier declare sur cet element gouvernant est epingle marche par marche**, seuil et valeur.
3. **Tout terme lateral declare sur un element de la chaine est epingle** - `box-sizing`, `width`,
   `min-width`, `max-width`, rembourrage, marge, bordure - qu'il vienne de la feuille maison ou de la
   feuille tierce, **au niveau superieur du fichier comme sous une requete de media**.
4. **L'egalite, jamais un plancher.** Ce que la mesure de `nadia-prof` appuie n'est pas une inegalite
   sur la largeur, c'est un couple (fonction, mesure) : tout changement de la fonction, dans un sens
   comme dans l'autre, rend la mesure muette. C'est le meme choix qu'`ADR-0053`, pour la meme raison
   - une inegalite laisse passer la moitie des facons de perdre, et ici c'est precisement la moitie
   que la mesure designe.
5. **La table epinglee n'est pas une exigence sur la largeur.** Fixer une largeur de reference est une
   decision de `camille-po`, en cours au moment de ce commit ; cet `ADR` n'en fixe aucune et n'en
   suppose aucune. La table est le releve de ce que les declarations produisent. Un lot qui change la
   mise en page volontairement la met a jour **dans le meme commit**, et ce rouge-la est le seul canal
   par lequel une remesure de `nadia-prof` devient due.

**Ce qui n'est pas decide ici, et qui ne l'est pas par omission** : la largeur obtenue a un viewport
donne reste non mesurable sans navigateur. `ADR-0025` et `ADR-0028` ne sont pas amendes. Un vert de
cette regle ne dit pas que la rangee est large ; il dit que la fonction est celle contre laquelle
`nadia-prof` a mesure.

## Regle executable

`adapters/adapter-web/src/test/java/com/recreo/adapter/web/LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.java`

Une clause de production, sept morsures sur materiau reel, un jeu fautif commite a noms renommes
(deux cas). Elle vit dans `adapter-web` parce que son materiau est le document servi.

**Le materiau est le document assemble, produit par le serveur.** La chaine traverse une frontiere de
fichier - `container` est declare dans `accueil.html`, la rangee vit dans `fragments/manche.html` - et
aucune lecture de gabarit isole ne la voit. Lire les deux fichiers et deduire le raccord serait une
inference de ma main ; l'assemblage qu'`ADR-0020` encode (innerHTML de la zone echangee) est celui que
le navigateur fait. **Les sept morsures mutent ce meme materiau reel** : la sortie du serveur, ou la
feuille tierce publiee telle qu'elle est, une valeur changee. Jamais une fixture ecrite pour
ressembler a ce que j'imagine.

**ADR-0013, les trois obligations.**

- **Perimetre derive.** Une rangee est un element que designe une regle **maison** declarant
  `flex-wrap` autre que `nowrap` - la declaration dont j'ai ecrit en s15 qu'elle est le seul garde-fou
  possible du defaut de largeur, *"cette declaration-la et non son effet"*. L'element gouvernant est
  derive de la feuille **tierce** : la classe portee par un ancetre sur laquelle une regle tierce
  declare `max-width` sous une regle-`@`. Ni `container`, ni `propositions`, ni aucun seuil, ni aucune
  valeur de plafond n'est ecrit comme predicat dans ce fichier - les seules valeurs qui y figurent sont
  celles de la table relevee.
- **Derivation totale.** Aucune regle lue, aucune classe gouvernante derivee, aucune rangee dans le
  document, aucun ou plusieurs elements gouvernants sur la chaine d'une rangee, un selecteur que le
  moteur de selection ne sait pas evaluer : **des echecs**, jamais des cas ignores.
- **Mise a l'epreuve.** Sept mutations du materiau reel, plus un jeu fautif commite dont aucun nom
  ni aucun seuil ne coincide avec ceux de la production. Les deux sont dus, et la section suivante
  dit pourquoi.

**La table epinglee : 25 lignes**, une par (rangee, element de sa chaine, regle qui y declare un terme
lateral). Deux rangees reelles y figurent - la rangee de propositions et le rang de l'en-tete, qui
declare le meme repli et depend du meme escalier. **Les deux lignes qui portent 540px et 720px sont
les seules dont une mesure au navigateur etablit la consequence** (500 px et 680 px de rangee, 160
tirages, 2026-08-22) ; les trois marches au-dela (960, 1140, 1320) sont derivees de la meme
arithmetique et n'ont ete mesurees par personne. Leur presence garde l'escalier entier contre un
changement muet, elle n'affirme rien sur ce qu'un enfant voit a ces largeurs, et le fichier le dit a
l'endroit ou elles sont ecrites.

### Les sept morsures, et contre quoi chacune est prouvee

| # | Mutation, sur materiau reel | Ce qu'elle prouve |
|---|---|---|
| 1 | La classe gouvernante retiree de la zone echangee, par les classes **derivees** de la feuille tierce | La chaine sans plafond est rouge. Deja tenu par `EnTeteEtMenuTest` depuis s06 : ce n'est pas un trou que je ferme, c'est un second porteur, derive au lieu d'etre nomme |
| 2 | La classe gouvernante remplacee par une classe tierce **sans escalier**, elle aussi derivee | Le predicat porte sur l'escalier, pas sur *"porte une classe de la bibliotheque"*. C'est la faute vraisemblable : la bibliotheque publie une variante pleine largeur, et l'echanger se lit comme un geste anodin |
| 3 | `max-width:540px` change en `320px` dans la feuille tierce reelle, empreinte supposee mise a jour | **La morsure qui compte le plus.** Le seul mode de defaillance qu'`ADR-0014` ne couvre pas : la mise a jour n'est pas muette, sa **consequence** l'etait |
| 4 | `max-width: 320px` ajoute a `.zone-jeu` dans la feuille maison reelle | Le test appelle d'abord `ViewportEtLargeurTelephoneTest` sur la feuille mutee et **le constate vert**. Le rouge de cette regle-ci est le seul |
| 5 | La meme borne, nichee sous `@media (min-width: 576px)` | Le test **constate** d'abord que `blocsMaison` ne voit aucun `max-width` sur `.zone-jeu` : la limite d'`ADR-0046` est verifiee dans l'assertion, pas affirmee dans un commentaire |
| 6 | `padding: 0 3rem` **ajoute** sur `.cabinet` | Le test appelle `LeConteneurTierGardeSonRembourrageLateralTest` sur la feuille mutee et **le constate vert** : sa faute est un rembourrage remis a zero, sur un element portant une classe tierce - deux fois pas ce geste-ci |
| 7 | `flex-wrap: wrap` devenu `nowrap` sur la rangee | La rangee quitte la population, donc la table perd ses lignes, donc c'est rouge. La declaration qui empeche le debordement ne peut pas disparaitre en silence |

**Un defaut de ma propre regle, trouve par le materiau et non par une fixture.** Le retrait des
pseudo-classes rendait `.table>:not(caption)>*>*` sous la forme `.table>>*>*`, que le moteur de
selection refuse : ma regle echouait sur la feuille tierce reelle, a la premiere execution. Corrige en
retirant les pseudo-classes **par compose** et en rendant le selecteur universel quand un compose n'est
plus que sa condition - `:not(caption)` designe *"n'importe quel element"*, et c'est cela qu'il faut
evaluer. C'est la lecon de s05 une fois de plus, et cette fois elle m'a coute une execution au lieu
d'un sprint.

### Le jeu fautif commite, et le garde-fou de garde-fous qui a eu raison contre moi

**J'ai d'abord livre cette regle avec ses sept mutations et sans jeu fautif commite**, en me disant
que muter le materiau reel est mieux qu'ecrire une fixture - ce que je continue de penser.
`LesGardeFousSontMisALEpreuveTest` a rougi : *"un test qui balaie des artefacts de production doit
posseder un jeu fautif sous `src/test/resources/contreexemple/<NomDeLaClasse>` et s'y executer en
exigeant le rouge"*.

**Il avait raison, et pas pour la raison que son message donne.** Son predicat verifie l'existence
d'un repertoire, ce qui est un **proxy** de la mise a l'epreuve - le defaut que j'ai moi-meme
diagnostique dans l'amendement 3 d'`ADR-0046`. J'ai donc regarde ce que mes sept mutations
prouvaient reellement, et il manquait quelque chose : **elles travaillent toutes sur les noms de la
production**. Elles prouvent que la regle mord sur les gestes reels ; elles ne prouvent pas que la
derivation est **derivee**. Une regle qui reconnaitrait litteralement `container` et `.propositions`
aurait passe les sept.

C'est exactement le contenu de l'obligation 3 d'`ADR-0013` (*"sur un jeu fautif neuf, avec des noms
de classes differents de ceux de la production"*), et c'est ce qu'ajoute
`contreexemple/LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest/` : une bibliotheque fictive dont
la classe gouvernante s'appelle `.enveloppe`, une rangee qui s'appelle `.colonnes`, un cadre qui
s'appelle `.cadre`, et un escalier a **deux** marches a 640 et 900 px au lieu de cinq a partir de 576.
Deux cas : un vert (la derivation retrouve la chaine, l'escalier et les termes sous ces autres noms)
et un rouge (la classe gouvernante retiree, sous ces autres noms aussi).

**Je n'ai pas amende le meta-garde-fou et je n'ai pas inscrit ma regle dans sa dette.** Elargir son
predicat pour accepter la mutation en memoire aurait remplace un proxy par un proxy plus faible ;
l'inscrire dans `DETTE_DE_BALAYAGE` aurait fait monter un plafond dont `ADR-0013` exige qu'il vaille
exactement la taille de la dette, pour un garde-fou qui est mis a l'epreuve sept fois. **Les deux
formes de preuve sont dues parce qu'elles ne prouvent pas la meme chose** : le materiau reel prouve
que la regle mord sur ce qui arrive, le jeu renomme prouve qu'elle mord sur autre chose que ce que
j'ai sous les yeux.

**Mesure de ce lot** (JDK 21.0.10, `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test`,
`-am` porte parce que la mesure est partielle) : **10 tests, 0 echec** pour ce fichier.
**Porte technique** : `bash outils/mvn-jdk21.sh -B clean verify` sur la racine du worktree,
**BUILD SUCCESS**, six modules, **604 tests, 0 echec**.

**Methode de ce chiffre, et un ecart de 7 que je nomme parce que je l'ai rencontre.** 604 est la
**somme des six totaux par module** que Surefire et Failsafe impriment sur la racine apres `clean`
(48 + 13 + 304 + 144 + 89 + 6), tests d'integration compris. Le comptage par **rapports texte de
classe** rend 597 sur 96 fichiers, soit 7 de moins - cause identifiee et non supposee : **quatre
classes sont executees par deux moteurs JUnit** (`AssertionsWebTest`, `AbstractionsCiteesTest`,
`ChargementDuContenuTest`, `LesReglesMordentTest`), et le rapport texte, un par classe, n'en garde
qu'une execution. Les executions perdues valent 1 + 4 + 1 + 1 = 7. C'est la troisieme des causes de
divergence que j'avais nommees apres avoir annonce 29 regles pour 25 ; elle se represente, et le
chiffre a citer est celui du reacteur.

## Ce que cette regle ne voit pas, nomme

**Ou la chaine s'arrete.** A l'element gouvernant, `body` et `html` exclus : un plafond rend sans
effet ce qui est au-dessus de lui, aussi longtemps que ce qui est au-dessus est au moins aussi large.
Cela cesse d'etre vrai **sous le premier seuil de l'escalier** (576 px), ou il n'y a plus de plafond du
tout et ou la largeur remonte jusqu'au document. Cette bande est exactement celle du telephone, hors du
perimetre depuis `DEC-0040`. **Consequence a nommer si le perimetre revient en arriere** : une
reference produit sous 576 px sortirait de ce que cette regle garde. A redecider, pas a supposer.

**Les termes ne sont pas resolus numeriquement.** La regle epingle des formes litterales -
`calc(var(--bs-gutter-x) * .5)` est comparee comme du texte, pas reduite a 12 px. Resoudre les
variables et les `calc` demanderait un moteur de cascade, c'est-a-dire un navigateur ecrit dans un test
- l'alternative que j'ai deja ecartee en `ADR-0046` et que j'ecarte encore. **Consequence assumee** :
un changement de `--bs-gutter-x` qui laisserait la forme litterale intacte ne serait pas vu ici. Il
serait vu par l'empreinte de la ressource tierce s'il vient d'elle, et par la table s'il vient d'une
declaration maison sur la chaine ; il ne serait vu par personne s'il venait d'une redefinition de
variable ailleurs dans la feuille maison. **Dette explicite, avec son declencheur** : le premier lot
qui redefinit une variable tierce dans la feuille maison.

**La regle sur-collecte, volontairement.** Elle ne resout pas la cascade : elle collecte **toute** regle
qui declare un terme lateral sur un element de la chaine, sans dire laquelle gagne. Une regle
conditionnelle entre dans le perimetre par sa forme inconditionnelle, ce qui la rend rouge plutot
qu'invisible. Le motif est celui d'`ADR-0046` : une clause qui doit prouver qu'**aucune** regle
n'intervient gagne a sur-collecter, la ou une clause qui compare deux regles gagne a etre precise.
**Mauvaise consequence, assumee** : ajouter une bordure decorative sur un element de la chaine rougit
un lot pour une raison qui n'est pas la sienne. Je le prefere a une rangee qui retrecit sans que
personne l'apprenne.

**Un vert de cette regle ne dit rien de trois proprietes que `nadia-prof` garde seule.** `SYN-5` (la
hauteur rendue de la derniere proposition) et `SYN-10` (l'invariance au tirage) restent exactement ou
elles etaient. La troisieme propriete qu'elle nomme le 2026-08-22 est celle-ci, et **elle cesse d'etre
sans garde-fou sans devenir pour autant garantie** : ce qui est garde est la fonction, pas son
resultat.

## Le sort de la limite `@media` que l'amendement 3 d'`ADR-0046` avait nommee

Cette limite est ecrite ainsi : *"`blocsTiers` aplatit la feuille et rend la condition d'une regle-`@`
comme une cle de selecteur ; les regles tierces nichees sous `@media` sont invisibles a cette
derivation, avant comme apres cet amendement."*

**Son declencheur est tombe pour la cause, pas pour la clause.** Je le mesure au lieu de le trancher au
jugement, sur `bootstrap.min.css` 5.3.8 tel qu'il est servi (109 blocs `@media` de niveau superieur,
balayes avec leur contenu) :

- **La cause est realisee** : une declaration tierce nichee sous `@media` porte une propriete
  pedagogiquement decisive, et aucune lecture du module ne la voyait. C'est ce que ce lot corrige,
  **pour cette propriete-ci**, par un balayage qui garde le contexte de regle-`@`, et la morsure 5
  verifie dans son assertion que la lecture aplatie reste aveugle.
- **La clause d'`ADR-0046` n'a aucune instance realisee** : sur ces 109 blocs, **zero** contient une
  pseudo-classe d'etat (`:hover`, `:active`, `:focus`). La derivation de specificite de cet amendement
  ne rate donc aujourd'hui **aucun** etat interactif tiers.

**Verdict** : la limite reste **ouverte pour la clause d'`ADR-0046`**, et son declencheur est reformule
avec ce qui la rendrait due - *le premier etat interactif tiers declare sous une regle-`@`, ou la
premiere mise a jour de bibliotheque qui en introduit un*. Elle n'est pas ouverte pour la propriete de
largeur, qui a desormais son propre balayage. **Je ne generalise pas ce balayage a la clause
d'`ADR-0046` dans ce lot** : elle compare des specificites, et une regle sous `@media` n'entre pas dans
une comparaison de specificite sans qu'on decide d'abord si la condition de media s'applique - ce qui
est une autre decision que celle-ci, et qui n'a aujourd'hui aucun objet a trancher.

**Ce que je ne fais pas non plus, et le motif** : je ne fusionne pas les deux lectures de feuilles du
module en une seule. Il y en a maintenant deux - l'aplatie (`blocsMaison`, `blocsTiers`, qui lit un
plancher declare a un selecteur qu'on nomme) et celle qui garde le contexte de regle-`@`
(`reglesMaison`, `reglesTierces`). La seconde est generalisee ici aux feuilles tierces, ce qui evite un
troisieme decoupage de blocs CSS - c'est ce que le commentaire de `Regle` exige depuis `ADR-0030`.
Retirer la premiere obligerait a reecrire une quinzaine de clauses qui n'ont pas de defaut, pour un
gain nul le jour du changement.

## Ce que je nomme sans le resoudre

**La police n'est pas la meme sur l'appareil cible que sur le poste de mesure.** `nadia-prof` releve
que ses largeurs intrinseques sont mesurees en Segoe UI, que la pile du produit commence par
`system-ui`, qu'un iPad rendra en San Francisco, et qu'un item qui tient a **1,51 px** peut basculer
sans qu'elle puisse dire de quel cote.

**Mon avis de mecanisme, en une phrase, et je m'arrete la** : ce n'est pas une question de garde-fou,
c'est une question de **perimetre de mesure**, donc elle appartient a `camille-po` et a `nadia-prof`,
pas a moi. Ce qui serait gardable est la **pile de polices declaree** - qu'elle ne change pas en
silence, exactement comme l'escalier ci-dessus - et cela ne repondrait pas a la question posee : la
largeur intrinseque d'un mot dans une police que ce depot ne contient pas n'est pas une propriete du
depot. Le seul mecanisme qui repondrait est une mesure sur l'appareil, et elle n'est pas de mon
ressort. **Je ne l'ecris donc pas comme une regle, et je ne l'ecris pas comme une dette technique** :
c'est une question ouverte, adressee, et je la laisse entiere.

**Une seconde chose vue en passant, non traitee** : `nadia-prof` note qu'a 744 x 585 les cinq items a
deux rangees produisent un document de 587 px, donc une barre de defilement, et que le bloc de retour
juste/faux tombe sous le pli. C'est du rendu, `ADR-0025` s'applique, et rien de ce lot ne le change. Je
le repete ici seulement pour qu'il ne se perde pas entre deux fichiers.

## Consequences

- **Un mode de defaillance qui n'avait aucun canal en a un.** Une mise a jour de bibliotheque qui
  change la mise en page ne peut plus passer la CI en silence : l'empreinte rougit, on la met a jour,
  et **la table rougit ensuite** en nommant la marche qui a bouge.
- **Une remesure pedagogique devient exigible par un automate.** Jusqu'ici, seule `nadia-prof` pouvait
  savoir qu'une remesure etait due, en jouant. Le rouge de cette table est le premier signal qu'un
  developpeur recoit sans avoir a y penser.
- **Un garde-fou de plus a la charge du meta-garde-fou**, sans exemption ni plafond releve : le
  recensement d'`ADR-0013` compte desormais un balayage de production supplementaire, eprouve.
- **Vingt-cinq lignes de table a maintenir.** Le cout est reel et il tombe sur tout lot qui touche la
  geometrie de la chaine. C'est le prix de l'egalite, et je l'assume plutot que de le baisser en
  passant a une inegalite qui ne mordrait qu'a moitie.
- **Mauvaise consequence** : un lecteur peut croire, en voyant cette clause verte, que la rangee est
  assez large. Elle ne l'est pas ; elle est **inchangee**. Le nom du test dit *"fonction epinglee"* et
  non *"largeur suffisante"*, et c'est la seule protection contre cette lecture.
- **Seconde mauvaise consequence** : deux garde-fous portent desormais le meme fait sur `#jeu` -
  `EnTeteEtMenuTest` par un nom litteral, celui-ci par derivation. Une redondance qu'on peut trouver
  inelegante ; je la garde, parce que la clause de s06 protege le **centrage** et que la faire porter
  l'escalier serait lui faire dire ce qu'elle ne dit pas.
- **Aucun octet de ce que voit un enfant n'est change par cet `ADR`.** Il n'ajoute que du test. Ni
  `S17-3`, ni `SYN-5`, ni aucun refus pedagogique n'est touche : le refus du 2026-08-21 reste en
  vigueur et ne se levera que par une mesure de `nadia-prof` contre la reference que `camille-po`
  fixera.

## Alternatives considerees

**Declarer la propriete non gardable et la confier a `nadia-prof`**, sur le patron d'`ADR-0025`,
d'`ADR-0028` et d'`ADR-0051`. **Ecartee, et c'est le vrai choix de cet `ADR`.** Le motif de ces trois
decisions est que la propriete visee est un **resultat de rendu** que les memes declarations produisent
ou non selon la police, la longueur du texte et l'algorithme de mise en page. Ici, non : chaque terme
est une declaration litterale, l'arithmetique se ferme, et elle rend au pixel les deux chiffres qu'un
navigateur reel a mesures. Declarer non gardable ce qui se derive et se verifie contre une mesure
aurait ete un garde-fou de moins pour rien.

**Un cliquet a plancher** - *"la largeur offerte ne descend jamais sous ce qu'elle vaut aujourd'hui"*.
Ecartee sur un fait mesure : le geste que `nadia-prof` designe comme risque **elargit** la rangee, donc
un plancher resterait vert dessus tout en rendant sa mesure caduque. C'est le meme raisonnement
qu'`ADR-0053`, ou un registre d'egalites aurait declare la protection qui tenait et laisse passer celle
qui a cede.

**Epingler la largeur derivee en pixels** (500 px, 680 px) plutot que les termes. Ecartee pour deux
raisons independantes. D'abord, elle exige de resoudre variables et `calc` : un moteur de cascade. Et
surtout, elle **fixerait une largeur de reference**, ce qui est une decision de `camille-po` en cours au
moment de ce commit. La table des termes dit *"rien n'a change"* ; une table de pixels dirait *"voici
la largeur que le produit vise"*, ce que je n'ai pas a ecrire.

**Assertionner un seuil precis** - *"l'escalier a une marche a 768 px"*. Ecartee : ce serait choisir un
viewport de reference, la meme decision de perimetre, et cela ne garderait qu'une marche sur cinq.

**Mesurer la largeur au navigateur dans un test.** Ecartee : aucun moteur de rendu ne vit dans ce
depot, et en faire entrer un pour cette propriete serait une dependance de test lourde et une porte
d'entree vers l'assertion de rendu que `ADR-0025` refuse. La mesure au navigateur reste le geste de
`nadia-prof`, avec sa methode ; ce que ce lot ajoute est que sa mesure ne peut plus etre invalidee sans
que quelqu'un l'apprenne.

## Amendement du 2026-10-20 (s57, `theo-archi`) - la pile de polices servie ne change pas en silence

**Contexte.** La section *"Ce que je nomme sans le resoudre"* laissait entiere la question de la police de l'appareil cible, et
nommait *"la pile de polices declaree - qu'elle ne change pas en silence"* comme ce qui serait gardable, sans l'ecrire comme regle.
`DEC-0122` 3.5 (2026-10-16) a tranche le perimetre : une cote vaut pour la pile servie, rendue sur le poste de mesure. La question de
l'appareil reste ouverte ; la pile servie devient une propriete du depot. Mesure de `camille-po` sur `78270b4c`
(`S57-GARDE-PILE-DE-POLICES`) : `body { font-family: Georgia, serif; }` en fin de `recreo.css`, et les trois autres formes ensemble,
passaient `adapter-web` (501 tests, 0 echec). La feuille tierce est deja epinglee par empreinte (`ADR-0014`), une police chargee depuis
le reseau deja refusee : manquait la police declaree par la feuille maison ou par un gabarit.

**Decision.** La pile de polices servie est celle de la feuille tierce. Refuser :

1. dans une feuille maison, une declaration `font-family` ou `font`, une regle `@font-face`, ou la definition d'une propriete
   personnalisee que la feuille tierce lit dans une declaration de police ;
2. dans un gabarit, les memes declarations dans un attribut `style` ou `th:style` (la valeur et chacun de ses litteraux entre
   apostrophes), ou dans une balise `<style>`. Le critere 4 de la story laissait le choix : **refuse**, pas nomme comme limite. Cout
   nul : les gabarits portent 4 attributs de style (3 `th:style` de teinte dans `accueil.html`, 1 `style` de position dans
   `fragments/designation.html`) et 0 balise `<style>`, aucun ne declare une police.

**Perimetre derive.** Les variables de police ne sont pas ecrites dans le test : ce sont celles qu'une declaration `font` ou
`font-family` de la feuille tierce lit par `var(...)`. Mesure sur `5ee20db7` (lecture hors depot de `vendor/bootstrap.min.css`,
commentaires retires) : **4**, `--bs-body-font-family`, `--bs-btn-font-family`, `--bs-font-monospace`, `--bs-font-sans-serif` ; la
cloture transitive (une variable lue par une variable de police) n'en ajoute **aucune**, elle n'est donc pas ecrite. Son declencheur :
une mise a jour de la feuille tierce, que l'empreinte d'`ADR-0014` rend deja visible. Une population vide est un echec.

**Regle executable.** Pas une `ArchRule` : l'objet est une feuille de style et des gabarits.
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/LaPileDePolicesServieNeChangePasEnSilenceTest.java`, huit tests :

- `niLaFeuilleMaisonNiUnGabaritNeChangentLaPileDePolices` : la production, verte sans exemption ;
- `chaqueFormeDeDeclarationDePoliceFaitEchouerLeGardeFou` : les quatre formes de la story, une a une, plus
  `:root { --bs-font-sans-serif: Georgia, serif; }`, chacune ajoutee en fin du texte reel de la feuille maison et lue par le lecteur
  partage (`reglesDe`) ; chacune rougit et le message cite son jeton (`font-family`, `font:`, `--bs-body-font-family`, `@font-face`,
  `--bs-font-sans-serif`) ;
- `uneTailleUneGraisseEtUneVariableMaisonRestentVertes` : clause de non-sur-detection, `font-size`, `font-weight` et une variable maison
  `--teinte-font` ;
- `unePoliceDansUnStyleDeGabaritFaitEchouerLeGardeFou` : un `th:style` reel de `accueil.html`, precede du litteral
  `'font-family: Georgia; '` ;
- `unePoliceDansUneBaliseStyleDeGabaritFaitEchouerLeGardeFou` ;
- `sansVariableDePoliceDeriveeLaClauseEchoueAuLieuDePasserAVide` ;
- `unDepotQuiRedefinitLaPileFaitEchouerLeGardeFou` et `leMemeDepotSansPoliceDeclareeResteVert` (`ADR-0013` obligation 3) : un depot
  fictif sous `contreexemple/LaPileDePolicesServieNeChangePasEnSilenceTest/`, dont la feuille tierce lit `--tiers-police`, que la
  feuille maison redefinit et dont un gabarit declare une police, et son jumeau sans ces deux declarations. Aucun nom du depot reel :
  la variable fautive n'est connue que par la feuille tierce du jeu. Ce jeu a ete ajoute apres que `LesGardeFousSontMisALEpreuveTest`
  a rougi au premier `verify` (un balayage de `src/main/` sans jeu fautif sous `contreexemple/`).

**Preuve de morsure.** Commande : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test
-Dtest=LaPileDePolicesServieNeChangePasEnSilenceTest -Dsurefire.failIfNoSpecifiedTests=false`, perimetre `adapter-web` et ses amonts,
une classe, 6 tests a ce moment-la (avant le jeu fautif), JDK 21.0.10 (`java.version` du rapport Surefire). Le fichier de test mute apres verification que le motif apparait
une fois, restaure par copie de reference (`cmp` identique) ; chaque mutation a compile.

| Code | Mutation du test | Mesure |
|---|---|---|
| `W0` | aucune | 6 tests, 0 echec |
| `W1` | variables nommees a la main (`--bs-body-font-family` seule) au lieu d'etre derivees | **1 echec**, `chaqueFormeDeDeclarationDePoliceFaitEchouerLeGardeFou` (la forme `--bs-font-sans-serif`) |
| `W2` | la detection de `@font-face` desactivee | **1 echec**, le meme test |
| `W3` | les litteraux d'un attribut de style ignores, la valeur brute seule lue | **1 echec**, `unePoliceDansUnStyleDeGabaritFaitEchouerLeGardeFou` |

Les mutations de `recreo.css` elle-meme, criteres 1 et 2 de la story, sont a `yanis-qa` (`CR39`).

**Ce que cette regle ne garde pas, nomme.** Une police posee par le JavaScript apres le rendu ; `letter-spacing`, `word-spacing`,
`font-stretch` et toute propriete qui change la largeur d'un texte sans changer la pile (hors de `DEC-0122`, qui parle de la pile) ;
la feuille tierce elle-meme, epinglee par empreinte (`ADR-0014`) ; une variable de police que la feuille tierce ne lirait qu'a travers
une autre variable (aucune aujourd'hui) ; les polices installees sur une tablette (`DEC-0122` 3.3), qui restent la question ouverte
de la section ci-dessus.

**Consequences.** Bonne : une cote mesuree par `nadia-prof` ne peut plus etre invalidee par une police declaree en silence. Mauvaise :
changer la police du produit, meme pour une raison de lisibilite, demande d'amender cette regle et de remesurer les cotes ; c'est
voulu, et c'est un geste de `nadia-prof` et d'`ines-design` avant d'etre un commit. Le lecteur des regles-`@` de ce test est un petit
balayage propre, parce que le lecteur partage ne rend aucune regle pour un bloc `@font-face`.

**Alternatives considerees.** **Epingler la pile par egalite** (la valeur de `--bs-body-font-family` de la feuille tierce) : ecartee,
l'empreinte d'`ADR-0014` epingle deja toute la feuille tierce. **Nommer la limite des gabarits au lieu de la refuser** : ecartee, le
refus coute zero sur les gabarits actuels et son mode de defaillance est un rouge.

**Ce qui me ferait changer d'avis** : une decision de produit de servir une police propre, qui ferait de la feuille maison le
porteur de la pile ; la regle s'inverserait alors en egalite sur cette declaration.

**Compte ArchUnit inchange** : 41 `static final ArchRule`, 38 `@ArchTest`, 16 fichiers (methode d'`ADR-0104`), sur `5ee20db7` et sur ce
commit ; le fichier de test ne porte aucun des deux motifs.

## Amendement du 2026-10-21 (s57, `theo-archi`) - un echappement CSS ne cache pas une police, `inherit` n'en pose aucune (`R5`, `R6`)

**Contexte.** `rapport-qualite-palier-3-mutations-css.md` de s57 (`yanis-qa`, section 4, constats 1 et 2), mesure sur `558dd887`, la
classe du garde seule (8 tests) :

- **`R5`, un trou.** `body { \66ont-family: Georgia; }` (`ME8`) et `@\66ont-face { ... }` (`ME9`) passaient : 0 echec. Le garde
  comparait le texte ecrit de la propriete et de l'en-tete, pas leur forme decodee. `\66` est `f` dans la grammaire des identifiants
  CSS ; l'equivalence est lue dans cette grammaire, **non rendue dans un navigateur**, ni par `yanis-qa` ni par moi.
- **`R6`, un faux rouge.** `button { font-family: inherit; }` (`ME10`) etait refuse, et le message ne le distinguait pas d'une
  police declaree. Aucune regle de la feuille actuelle ne l'ecrit.

**Decision.**

1. **`R5` : le garde decode les echappements CSS avant de comparer** la propriete, la valeur et l'en-tete d'une regle-`@` :
   une barre oblique inverse suivie de 1 a 6 chiffres hexadecimaux et d'un blanc facultatif vaut le caractere de ce point de code ;
   suivie de tout autre caractere, elle vaut ce caractere. Le message cite la declaration telle qu'elle est ecrite
   (`declare \66ont-family: Georgia`), pour qu'on la retrouve dans la feuille. Le decodage vit dans le garde
   (`LaPileDePolicesServieNeChangePasEnSilenceTest.sansEchappements`), pas dans le lecteur partage : aucun autre garde-fou de la
   feuille ne compare un nom de propriete a une liste, et la question ne s'y pose pas aujourd'hui.
2. **`R6` : `inherit` est admis** comme valeur de `font-family` et de `font`, et elle seule. **Motif** : `inherit` reprend la police
   du parent et n'en pose aucune ; la pile servie reste celle que la feuille tierce donne a `body`, que `DEC-0122` 3.5 garde. La
   feuille tierce l'ecrit elle-meme (`button,input,optgroup,select,textarea{...font-family:inherit...}`, lu dans
   `vendor/bootstrap.min.css` sur `a0a69859`) : refuser l'idiome dans la feuille maison serait refuser ce que la pile mesuree contient
   deja. **Ce qui reste refuse** : toute autre valeur, dont `initial`, `revert` et `unset` ecrits sur `font` ou `font-family`.
   `initial` rend la police par defaut du navigateur et `revert` la feuille de l'agent utilisateur, qui pour un bouton n'est pas la
   pile du texte : les deux changent la pile. `unset` vaut `inherit` pour une propriete heritee, mais je ne l'admets pas sans usage
   constate : il rougit, et le jour ou une regle en a besoin, on l'ajoute ici. Une variable de police reste refusee quelle que soit
   sa valeur.

**Regle executable.** Meme fichier, douze tests (huit avant cet amendement) :

- `uneProprieteDePoliceEcriteParEchappementFaitEchouerLeGardeFou` et `uneRegleDePoliceEcriteParEchappementFaitEchouerLeGardeFou` :
  deux depots fictifs neufs sous `contreexemple/LaPileDePolicesServieNeChangePasEnSilenceTest/`
  (`propriete-de-police-ecrite-par-echappement`, `regle-de-police-ecrite-par-echappement`) ;
- `desEchappementsQuiNeNommentAucunePoliceRestentVerts` : leur jumeau vert (`echappement-sans-police`), des echappements qui decodent
  en `color` et en `font-size`, et `button { font-family: inherit; }` ;
- `uneDeclarationQuiHeriteLaPoliceDuParentResteVerteEtInitialResteRouge` : sur la feuille maison reelle, `font-family: inherit` et
  `font: INHERIT` restent verts ; `font-family: initial` rougit.

**Preuve de morsure.** Commande : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test
-Dtest=LaPileDePolicesServieNeChangePasEnSilenceTest -Dsurefire.failIfNoSpecifiedTests=false`, perimetre `adapter-web` et ses amonts,
une classe, 12 tests, JDK 21.0.10. Chaque mutation : motif present une fois verifie, fichier restaure par copie de reference
(`cmp` identique), aucune erreur de compilation.

| Code | Mutation | Mesure |
|---|---|---|
| `W0` | aucune | 12 tests, 0 echec |
| `ME8` | `body { \66ont-family: Georgia; }` en fin de `recreo.css` | **3 echecs**, le message cite `declare \66ont-family: Georgia` ; vert avant cet amendement |
| `ME9` | `@\66ont-face { font-family: Zz; src: local(Arial); }` en fin de `recreo.css` | **3 echecs**, le message cite `declare une regle @font-face` ; vert avant |
| `ME10` | `button { font-family: inherit; }` en fin de `recreo.css` | **0 echec** ; rouge avant |
| `W1` | variables nommees a la main au lieu d'etre derivees | **1 echec**, `chaqueFormeDeDeclarationDePoliceFaitEchouerLeGardeFou` |
| `W2` | detection de `@font-face` desactivee | **2 echecs**, le meme et `uneRegleDePoliceEcriteParEchappementFaitEchouerLeGardeFou` |
| `W3` | litteraux d'un attribut de style ignores | **2 echecs**, `unePoliceDansUnStyleDeGabaritFaitEchouerLeGardeFou` et `unDepotQuiRedefinitLaPileFaitEchouerLeGardeFou` |
| `W4` | decodage desactive (`sansEchappements` rend son entree) | **2 echecs**, les deux tests d'echappement |
| `W5` | `inherit` de nouveau refuse | **2 echecs**, `desEchappementsQuiNeNommentAucunePoliceRestentVerts` et `uneDeclarationQuiHeriteLaPoliceDuParentResteVerteEtInitialResteRouge` |

**Lecture des "3 echecs" de `ME8` et `ME9`** : trois tests lisent la feuille reelle (le test principal et deux clauses vertes) ; ils
rougissent ensemble pour une seule detection, comme le dit la methode de `yanis-qa`.

**Ce que cette regle ne garde pas, nomme** (s'ajoute a la liste du 2026-10-20). Un echappement dans le nom d'une variable de police
est decode comme le reste ; la casse d'une propriete personnalisee, que CSS distingue, est ramenee en minuscules, ce qui ne peut que
sur-detecter. Les echappements ne sont pas decodes dans les autres garde-fous de la feuille (decision 1).

**Consequences.** Bonne : les deux formes de `R5` rougissent, et l'idiome de la feuille tierce passe. Mauvaise : un `inherit` sur un
element dont le parent porterait une autre police heriterait de cette police ; ce cas ne peut pas exister tant que la regle refuse
toute autre declaration de police, ce qui est la condition de cette exception.

**Alternatives considerees.** **Refuser `inherit` et le dire dans le message** : ecartee, elle refuse ce que la pile mesuree contient
deja et oblige la premiere regle legitime a demander une exemption. **Nommer `R5` comme limite** : ecartee, le decodage coute une
fonction et son mode de defaillance est un rouge. **Decoder dans le lecteur partage** : ecartee pour l'instant (decision 1) ; ce qui me
ferait changer d'avis est un second garde-fou qui compare un nom de propriete ou un en-tete a une liste.

**Compte ArchUnit inchange** : 41 `static final ArchRule`, 38 `@ArchTest`, 16 fichiers (methode d'`ADR-0104`), sur `a0a69859` et sur ce
commit.
