# ADR-0055 - La largeur demandee par une rangee est epinglee comme celle qui lui est offerte : un plancher ne garde qu'une moitie de comparaison

- **Statut** : Accepte, sa regle ecrite dans le meme commit
  (`LaLargeurDemandeeParUneRangeeEstEpingleeTest`, huit tests : une clause de production a 34 lignes
  epinglees, cinq morsures sur materiau reel, un jeu fautif commite a noms renommes - un cas vert,
  un cas rouge).
- **Sprint** : s18, avis de faisabilite (aucun commit de code de story ouvert). **Date** :
  2026-08-22. **Auteur** : theo-archi.
- **Arbre mesure** : `round-two` a `494da1aa7841fb8ccb752f7a42573aee8b09ff94`, SHA releve par
  `git rev-parse HEAD` sur ce worktree, jamais repris d'un message de commit.
- **Route par** : personne. Je l'ouvre en instruisant `SYN-11` pour `DEC-0041` : la question qui
  m'est posee est le mecanisme qui remonterait le bloc de retour juste/faux au-dessus du pli, et le
  levier que `nadia-prof` a mesure est un ecart entre deux largeurs dont **une seule** etait gardee
  par une egalite depuis la veille.
- **Complete** : `ADR-0054` (dont il est la moitie manquante), `ADR-0026` (le plancher d'ecart entre
  deux boutons de reponse), `ADR-0053` (comparer l'issue plutot qu'une inegalite), `ADR-0013`,
  `ADR-0025` et `ADR-0028` (ce qu'un rendu ne laisse pas garder).

## Contexte

`ADR-0054`, ecrit la veille, epingle par une egalite **la largeur qu'une chaine d'ancetres offre a
une rangee repliable** : 500 px sur la bande 576-767 px, 680 px des 768 px, chaque terme lateral de
la chaine releve dans une table de 25 lignes.

**Le repli d'une rangee n'est pas une propriete de cette largeur : c'est l'issue d'une comparaison
entre deux largeurs.** Le fait sur lequel `nadia-prof` appuie `SYN-11` est litteralement cette
comparaison : *"les cinq items debordent la rangee de 500 px de 14 a 38 px"* (2026-08-22,
`squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md`). Le membre gauche - 500 px - a desormais
une egalite qui le garde. Le membre droit - 514 a 538 px demandes par trois colonnes et leurs deux
ecarts - n'avait aucune egalite. **J'ai epingle une moitie de comparaison et j'ai ecrit dans
`ADR-0054` que la fonction etait gardee.**

### Ce que la CI voyait deja de ce membre droit, mesure et non suppose

Deux garde-fous existent sur le membre droit, tous deux acquis sur un refus pedagogique, tous deux
**des planchers**, et je les ai verifies sur l'arbre plutot que de me fier a leur nom :

1. **L'ecart minimal entre deux boutons de reponse**, 1 rem sur les deux axes
   (`TailleDesTextesEtBoutonsTest.lEcartEntreDeuxBoutonsDeReponseEstAuMoinsUnRemSurLesDeuxAxes`,
   `ADR-0026`, acquis d'un refus de `nadia-prof` en s03). Il interdit de **descendre** sous 1 rem.
2. **La taille de police d'une proposition**, plancher general de 2 rem, entaille unique de
   1,875 rem sous le repli a quatre colonnes, plus une clause qui refuse toute troisieme regle de
   taille de proposition (`lesSeulesTaillesDePropositionSontLesDeuxReglesNommees`). Il interdit lui
   aussi de **descendre**.

**Or ce qui defait la mesure de `nadia-prof` est de monter.** Un ecart porte de 1 a 2 rem coute
16 px de plus par intervalle ; une police portee de 2 a 2,5 rem elargit chaque etiquette. Les deux
gestes font passer des items d'une rangee a deux - c'est-a-dire exactement le mecanisme de 90 px que
`nadia-prof` a mesure - et **les deux planchers restent verts**. Ce sont des gestes vraisemblables :
augmenter un ecart ou une police est le reflexe d'un lot d'accessibilite visuelle, et c'est
`ines-design` qui propose la mise en page.

**Et une troisieme famille n'avait aucun garde-fou d'aucun cote** : le rembourrage lateral, la
bordure, la largeur minimale et la base flexible d'une colonne. Verifie sur l'arbre : `.proposition`
declare `min-width: 2.75rem` et `padding: 0.75rem 1.25rem` dans la feuille maison, `.btn` declare
`padding` et `border` dans la feuille tierce, et **aucune clause du depot ne compare aucune de ces
valeurs a quoi que ce soit**. Ramener le rembourrage lateral d'un bouton de 1,25 rem a 0,25 rem
rendrait 96 px sur trois colonnes - plus que le pire deficit de 38 px - **sans faire rougir un seul
test**, et rendrait muettes les seize lignes du tableau de `nadia-prof`. C'est, au moment ou j'ecris,
le correctif le moins cher de `SYN-11`.

### Pourquoi c'est la meme faute qu'`ADR-0054` a corrigee, sur l'autre membre

`ADR-0054` a ecarte un cliquet a plancher sur un fait mesure : le geste que `nadia-prof` designait
comme risque **elargissait** la rangee, donc un plancher serait reste vert dessus tout en rendant
caducs ses seize chiffres. J'ai ecrit ce raisonnement, je l'ai applique au membre gauche, et j'ai
laisse le membre droit garde par deux planchers - c'est-a-dire par la forme exacte que je venais
d'ecarter, dans le sens exact ou elle ne mord pas.

**Ce depot a deja sanctionne cette famille sous un autre nom** : *"un cliquet dont le plafond depasse
la dette n'est pas un cliquet"*. Un plancher qui ne borne que le cote ou personne ne va n'est pas un
garde-fou de la propriete qu'on croit garder ; c'est un garde-fou d'une autre propriete, qui se
trouve porter les memes mots.

## Decision

**Epingler la largeur demandee par une egalite, sur la meme population de rangees, avec la meme
mecanique de table et la meme obligation de mise a jour dans le commit qui change la mise en page.**

1. **Pour chaque rangee repliable du document que l'enfant a sous les yeux**, l'espace intercale
   qu'elle declare entre deux colonnes voisines est epingle (`gap`, `column-gap`).
2. **Pour chacune de ses colonnes**, tout terme declare qui decide de la largeur qu'elle demande est
   epingle : les termes de sa boite (`box-sizing`, `width`, `min-width`, `max-width`, rembourrage,
   marge, bordure lateraux), sa base flexible (`flex`, `flex-basis`, `flex-grow`, `flex-shrink`) et
   sa taille de police - qu'ils viennent de la feuille maison ou de la feuille tierce, au niveau
   superieur du fichier comme sous une requete de media.
3. **L'egalite, jamais un plancher, dans les deux sens.** C'est le point entier de cet `ADR` : les
   deux planchers existants ne sont ni retires ni affaiblis - ils gardent un acquis pedagogique que
   cette regle-ci ne garde pas - mais ils ne sont plus le seul canal par lequel un changement du
   membre droit devient visible.
4. **`row-gap` n'y est pas.** Il coute de la hauteur une fois que la rangee s'est deja repliee ; il
   n'entre pas dans la comparaison qui decide du repli. Le nommer aurait fait une table plus grosse
   sans fermer un mode de defaillance de plus.
5. **La table n'est pas une exigence sur la largeur**, exactement comme celle d'`ADR-0054`. Elle est
   le releve de ce que les declarations demandent aujourd'hui. Un lot qui change la mise en page
   volontairement la met a jour **dans le meme commit**, et ce rouge-la est le canal par lequel une
   remesure de `nadia-prof` devient due.

**Ce qui n'est pas decide ici** : la largeur qu'un mot occupe reellement. Elle depend d'une police
que ce depot ne contient pas, et c'est la question que `nadia-prof` laisse ouverte et que
`ADR-0054` a laissee entiere comme un **perimetre de mesure**. Cet `ADR` ne la tranche pas, ne pinne
pas la pile de polices declaree, et n'y repond pas : il garde les declarations dont la largeur
demandee est la somme, pas la largeur.

## Regle executable

`adapters/adapter-web/src/test/java/com/recreo/adapter/web/LaLargeurDemandeeParUneRangeeEstEpingleeTest.java`

Huit tests : la clause de production (34 lignes epinglees), cinq morsures sur materiau reel, et deux
cas sur un jeu fautif commite a noms renommes. Elle vit dans `adapter-web` parce que son materiau est
le document servi, et elle lit le **meme** document assemble qu'`ADR-0054` (`ADR-0020`, innerHTML de
la zone echangee).

**ADR-0013, les trois obligations.**

- **Perimetre derive, et partage plutot que recopie.** La population des rangees est celle
  d'`ADR-0054` : `rangeesRepliables` est extraite dans le fichier de la veille et appelee par les
  deux. **Deux tables qui decriraient deux ensembles de rangees differents ne s'additionneraient pas,
  elles se contrediraient** - et le jour ou l'une des deux derivations deriverait, la moitie de la
  comparaison qu'elle garde changerait de sujet sans que personne l'apprenne. Les colonnes sont les
  enfants de ces rangees dans le document assemble. Ni `propositions`, ni `proposition`, ni `btn`
  n'est ecrit comme predicat dans ce fichier.
- **Derivation totale.** Aucune rangee, une rangee sans colonne, aucune regle lue, un selecteur que
  le moteur de selection ne sait pas evaluer : **des echecs**, jamais des cas ignores.
- **Mise a l'epreuve.** Cinq mutations du materiau reel - la feuille servie et le document que le
  serveur rend - **plus** un jeu fautif commite dont aucun nom ne coincide avec ceux de la
  production. Les deux formes sont dues et ne prouvent pas la meme chose, pour le motif deja ecrit en
  `ADR-0054` : le materiau reel prouve que la regle mord sur ce qui arrive, le jeu renomme prouve
  qu'elle mord sur autre chose que ce que j'ai sous les yeux. Cette fois je n'ai pas attendu que
  `LesGardeFousSontMisALEpreuveTest` me le rappelle.

**La table epinglee : 34 lignes**, une par (rangee, ecart declare) et une par (rangee, nom de
colonne, regle qui declare un terme de colonne sur elle). Deux rangees reelles y figurent, les memes
qu'`ADR-0054` : la rangee de propositions et le rang de l'en-tete. **Les lignes de
`form.propositions` sont les seules dont une mesure au navigateur etablit la consequence** - trois
colonnes qui demandent 514 a 538 px la ou la rangee en offre 500, sur cinq des seize items de
`cm1-synonymes`, 160 tirages, 2026-08-22. Celles du rang de l'en-tete n'ont ete mesurees par
personne ; elles gardent la meme comparaison contre un changement muet et n'affirment rien sur ce
qu'un enfant y voit.

### Les cinq morsures, et contre quoi chacune est prouvee

| # | Mutation, sur materiau reel | Ce qu'elle prouve |
|---|---|---|
| 1 | `gap: 1rem` porte a `2rem` sur la rangee, dans la feuille maison reelle | **Le vert silencieux du premier plancher.** Le test appelle d'abord `TailleDesTextesEtBoutonsTest.verifierEcartEntreBoutonsDeReponse` sur la feuille mutee et **le constate vert** : un plancher a 1 rem n'a rien a dire de 2 rem. Le rouge de cette regle-ci est le seul |
| 2 | Rembourrage lateral d'une colonne remis a zero, feuille maison reelle | La famille qui n'avait **aucun** garde-fou, et le correctif le moins cher de `SYN-11` au moment ou j'ecris |
| 3 | Base flexible d'une colonne portee de `1 1 auto` a `1 1 100%` | Une colonne qui refuse desormais de partager une rangee : le repli devient certain quel que soit le contenu, et rien ne le disait |
| 4 | Rembourrage lateral de `.btn` change dans la feuille tierce reelle, empreinte supposee mise a jour | Le mode de defaillance qu'`ADR-0014` ne couvre pas, cote membre droit cette fois : la mise a jour de bibliotheque n'est pas muette, sa **consequence** l'etait |
| 5 | `font-size: 2.5rem` ajoute sur une colonne, feuille maison reelle | **Le vert silencieux du second plancher.** Un plancher a 2 rem n'a rien a dire de 2,5 rem, et la clause qui refuse une troisieme regle de taille de proposition ne juge pas la valeur d'une regle qui existe deja |

**Le jeu fautif commite** :
`src/test/resources/contreexemple/LaLargeurDemandeeParUneRangeeEstEpingleeTest/`. Une bibliotheque
fictive dont la classe de bouton s'appelle `.touche`, une rangee qui s'appelle `.colonnes`, des
colonnes qui s'appellent `.case`, et aucun seuil ni aucune valeur en commun avec la production. Deux
cas : un vert (la derivation retrouve la rangee, ses deux colonnes, leurs termes maison et tiers sous
ces autres noms) et un rouge (le rembourrage de colonne remis a zero, sous ces autres noms aussi).
Les cinq morsures travaillent toutes sur les noms de la production : elles prouvent que la regle mord
sur les gestes reels, **pas** que la derivation est derivee.

**Mesure de ce lot** (JDK 21.0.10 par la toolchain declaree,
`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test`, `-am` porte parce que la mesure est
partielle) : **8 tests, 0 echec** pour ce fichier, **10 tests, 0 echec** pour celui d'`ADR-0054`,
inchange dans son verdict apres l'extraction des trois methodes qu'il partage desormais.
**Porte technique** : `bash outils/mvn-jdk21.sh -B clean verify` sur la racine du worktree,
**BUILD SUCCESS**, six modules, **612 tests, 0 echec**.

**Methode de ce chiffre.** 612 est la **somme des six totaux par module** que Surefire et Failsafe
impriment sur la racine apres `clean` (48 + 13 + 312 + 144 + 89 + 6), tests d'integration compris -
la meme methode et le meme perimetre qu'`ADR-0054`, qui mesurait 604 sur le meme decoupage
(48 + 13 + **304** + 144 + 89 + 6). L'ecart de 8 est entierement le fichier ajoute par ce lot, et
c'est le seul module dont le total bouge. Le comptage par rapports texte de classe rend un chiffre
plus bas, pour la cause deja etablie en `ADR-0054` (quatre classes executees par deux moteurs JUnit,
un rapport texte par classe) ; le chiffre a citer est celui du reacteur.

## Ce que cette regle ne voit pas, nomme

**Les termes ne sont pas resolus numeriquement.** `padding: var(--bs-btn-padding-y)
var(--bs-btn-padding-x)` est epingle comme du texte, pas reduit a 12 px. Meme motif, meme
consequence et **meme dette avec le meme declencheur** qu'`ADR-0054` : un changement de
`--bs-btn-padding-x` qui laisserait la forme litterale intacte ne serait pas vu ici. Verifie sur
l'arbre plutot que suppose - `grep -n "\-\-bs-" static/css/recreo.css` ne rend **aucune** occurrence,
donc la feuille maison ne redefinit aujourd'hui aucune variable tierce, et le declencheur nomme en
`ADR-0054` (*le premier lot qui redefinit une variable tierce dans la feuille maison*) n'est
toujours pas realise. Il est desormais partage par **deux** garde-fous, ce qui augmente son cout sans
changer sa nature.

**La regle sur-collecte, volontairement, et pas exactement comme `ADR-0054`.** Les pseudo-classes
sont retirees des selecteurs, donc `.chip-niveau:hover` entre par sa forme inconditionnelle : sa
bordure figure dans la table alors qu'elle ne s'applique qu'au survol. **Mauvaise consequence
assumee** : un lot qui change une bordure d'etat rougit cette table pour une raison qui n'est pas la
sienne. Je le prefere a une largeur demandee qui change sous condition sans que personne l'apprenne,
et c'est le meme arbitrage qu'`ADR-0054`.

**Le nombre de colonnes n'est pas epingle, volontairement.** Les lignes sont dedupliquees par nom
d'element : trois propositions rendent trois boutons identiques et donc une ligne. Le nombre de
propositions d'un ecran est une propriete du **contenu** - deux, trois ou quatre selon le paquet -
pas de la mise en page, et l'epingler ici epinglerait le catalogue de test. **Ce que cela coute** :
si un ecran passait de trois a quatre colonnes sans qu'aucune declaration ne change, cette table
resterait verte. Ce cas-la est deja garde ailleurs, par la regle de taille de proposition dont
l'exception est nommee *sous le repli a quatre colonnes*, et par les regles de contenu qui comptent
les propositions d'un item.

**La dette d'ancetre d'`ADR-0046` et d'`ADR-0053` n'est pas partagee par cette regle, et je le dis
parce que la ressemblance invite a le croire.** Ces deux clauses comparent des specificites lues dans
un **texte** de selecteur, sans verifier qu'un prefixe d'ancetre s'applique. Cette regle-ci n'infere
rien : elle demande au moteur de selection quels elements du **document assemble** un selecteur
designe, ce qui evalue le prefixe d'ancetre par construction - `.entete-contenu .lien-accueil` est
retrouve sur le vrai lien, et non credite a tous les liens. La dette reste ouverte la ou elle est
nee, avec son declencheur inchange.

**Un vert de cette regle ne dit rien de trois proprietes que `nadia-prof` garde seule.** `SYN-5`,
`SYN-10` et `SYN-11` restent exactement ou elles etaient. Ce qui est garde est une moitie de
comparaison de plus, pas le resultat de la comparaison.

## Consequences

- **La comparaison que `nadia-prof` a mesuree est gardee de bout en bout.** Depuis ce commit, les
  deux membres de *"514 a 538 px demandes contre 500 px offerts"* ont chacun une egalite. Aucun des
  deux ne peut changer sans qu'un automate le dise, dans un sens comme dans l'autre.
- **Le correctif de `SYN-11` rougira, quel que soit le levier retenu**, et c'est le comportement
  voulu : le lot qui le livre met la table a jour dans le meme commit, et ce diff est la demande de
  remesure adressee a `nadia-prof`. Un correctif de mise en page qui ne rougirait ni `ADR-0054` ni
  `ADR-0055` serait desormais un correctif dont il faut se demander ce qu'il change reellement.
- **34 lignes de table de plus a maintenir**, apres les 25 d'`ADR-0054`. Le cout est reel et tombe
  sur tout lot qui touche la geometrie de la rangee ou de ses colonnes. C'est le prix de l'egalite, et
  c'est la seconde fois en deux jours que je l'assume plutot que de le baisser en passant a une
  inegalite qui ne mord qu'a moitie.
- **Un garde-fou de plus a la charge du meta-garde-fou**, sans exemption et sans plafond releve : il
  possede son jeu fautif commite et s'y execute en exigeant le rouge.
- **Trois methodes du fichier d'`ADR-0054` deviennent visibles dans le paquet de test**
  (`etiquette`, `designes`, `declareUnRepli`) et deux sont extraites (`rangeesRepliables`,
  `termesDeclares`). Ce n'est pas une abstraction anticipee : c'est le partage de la **population**
  entre deux tables qui doivent parler des memes rangees, et son motif est ecrit ci-dessus.
- **Mauvaise consequence** : un lecteur peut croire, en voyant les deux clauses vertes, que la
  rangee est assez large pour ses items. Elle ne l'est pas ; elle est **inchangee**, et `SYN-11`
  reste non tenue. Le nom du test dit *"largeur demandee epinglee"* et non *"les items tiennent"*,
  et c'est la seule protection contre cette lecture.
- **Aucun octet de ce que voit un enfant n'est change par cet `ADR`.** Il n'ajoute que du test. Ni
  `S17-3`, ni `SYN-11`, ni aucun refus pedagogique n'est touche.

## Alternatives considerees

**Ne rien ecrire et nommer la dette avec son declencheur.** C'etait le choix confortable : la dette
aurait ete *"le premier lot qui touche la largeur demandee par une colonne"*. **Ecartee, et c'est le
vrai arbitrage de cet `ADR`** : ce declencheur est realise **ce sprint**. Le correctif de `SYN-11` est
au perimetre propose par `DEC-0041`, son levier le moins cher est precisement un terme de colonne, et
un garde-fou qui arrive apres le lot qu'il devait rendre visible n'a jamais garde ce lot. Une regle
ecrite apres le geste qu'elle interdit documente une histoire ; elle n'empeche rien.

**Elargir la table d'`ADR-0054` plutot qu'en ouvrir une seconde.** Ecartee sur le nom : ce fichier
s'appelle *"la largeur offerte"*, et lui faire porter la largeur demandee lui ferait dire autre chose
que son nom - le defaut que ce depot a paye plusieurs fois, ou une clause ecrite pour une propriete se
voit creditee d'une autre. Les deux tables partagent leur population et leur mecanique, elles gardent
deux membres distincts d'une comparaison, et chacune se lit seule.

**Epingler la largeur demandee en pixels** (514 a 538 px). Ecartee pour les deux raisons d'`ADR-0054`,
dont une est plus forte ici : cela exigerait de resoudre les variables, les `calc` **et** la largeur
intrinseque d'un texte dans une police que le depot ne contient pas - c'est-a-dire un moteur de rendu.
Et cela fixerait implicitement une largeur de reference, decision de `camille-po`.

**Ajouter `letter-spacing`, `word-spacing`, `font-weight`, `font-family` et `white-space` a la
table.** Ecartee, et pas par oubli : aucune de ces proprietes n'est declaree aujourd'hui sur une
colonne d'une rangee du produit, aucun geste realiste du sprint ne les touche, et `font-family`
appartient a la question de police que je laisse entiere. **Dette explicite, avec son declencheur** :
la premiere declaration de l'une de ces cinq proprietes sur une colonne d'une rangee - elle passerait
aujourd'hui sans rougir cette table, alors qu'elle change la largeur demandee.

**Relever les deux planchers existants en egalites.** Ecartee : ils gardent un **acquis
pedagogique** - "au moins 1 rem d'ecart", "au moins 2 rem de police" - et une egalite leur ferait
dire *"exactement 1 rem"*, ce qui interdirait a `nadia-prof` d'obtenir plus d'air ou plus de police
sans qu'un `ADR` soit reecrit. Deux clauses qui disent deux choses differentes sur les memes
declarations valent mieux qu'une clause qui dit mal les deux.
