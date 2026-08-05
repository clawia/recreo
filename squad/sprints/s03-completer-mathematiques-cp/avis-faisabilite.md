# [Sprint s03] Avis de faisabilite - theo-archi

- **Date** : 2026-08-05
- **Auteur** : theo-archi
- **Backlog lu** : `docs/backlog/backlog.md`, section "Sprint 3 - Completer la ligne mathematiques
  CP avant d'en ouvrir une nouvelle", stories S3-1 a S3-9. **Ce sont leurs criteres d'acceptation
  qui font foi ici**, pas le resume de `DEC-0007`, que je lis en second pour comprendre le motif.
- **Pieces de reference** : `DEC-0007-perimetre-s03-completer-avant-etendre.md`,
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md` (verdicts s01 et s02),
  `DEC-0004`, `DEC-0005`.
- **Code relu** : `AdditionsBorneesContentSource`, `BanditManchotSession`, `YamlContentPackLoader`,
  `AdditionsBorneesContentSourceFactory`, `ContentSourceFactory`, `DifficulteTest`,
  `TailleDesTextesEtBoutonsTest` dans sa version corrigee (`164b662`), `accueil.html`,
  `recreo.css`, `cp-additions-jusqu-a-10.yaml`, `catalogue.yaml`, `favicon.ico`.
- **Nom de ce dossier** : `s03-completer-mathematiques-cp`, choisi faute de slug existant au moment
  ou j'ecris. `sofia-sm` anime le planning apres moi et **peut le renommer** ; si elle le fait, ce
  fichier suit, il n'a aucune autorite sur le nom du sprint.

## Synthese

| Story | Avis | ADR requis | Testable par |
|---|---|---|---|
| S3-1 | **Faisable, mais deux de ses quatre criteres se contredisent tels qu'ecrits.** Sortie mesuree et chiffree ci-dessous, elle demande un arbitrage de `camille-po` sur un seuil de `DEC-0004` | aucun | automate (statistique), plus porte pedagogique sur le fond |
| S3-2 | Faisable en l'etat, avec deux pieges d'implementation nommes ci-dessous | aucun | automate, plus porte pedagogique sur l'effet de l'aide |
| S3-3 | Faisable en l'etat. **Le chargeur unique l'accepte sans nouvelle fabrique** - verifie en lisant le code, pas suppose | aucun | automate |
| S3-4 | Faisable en l'etat | aucun | automate (declaration CSS), plus porte pedagogique |
| S3-5 | Faisable ; le critere "ecart reel entre les boites" n'est pas mesurable sans moteur de rendu, seule la declaration l'est | aucun | automate sur la declaration ; le reste, porte pedagogique |
| S3-6 | Faisable ; contrainte d'implementation forte imposee par un garde-fou existant, voir ci-dessous | aucun | automate, plus porte pedagogique |
| S3-7 | Faisable, y compris le critere "dessinee pour 16 px" : mesurable aujourd'hui sans nouvelle dependance | aucun | automate (proxy mesurable), plus porte pedagogique sur le dessin |
| S3-8 | **Faisable en partie seulement. Le critere central n'est pas verifiable par automate dans ce depot** ; et le prealable qui manque n'est pas dans la story | aucun | automate pour une partie ; le reste, ni automate ni porte pedagogique aujourd'hui |
| S3-9 | Faisable en l'etat, avec un piege de balayage nomme ci-dessous | aucun | automate |

**Aucun ADR nouveau n'est requis pour ces neuf stories.** Elles s'executent a l'interieur des treize
deja acceptes. Je dis plus bas, story par story, ce qui en demanderait un si l'equipe partait dans
une autre direction que celle que je recommande.

**`ADR-0013` s'applique a tout ce que ce sprint va ecrire comme garde-fou.** Quatre des neuf stories
(S3-4, S3-5, S3-6, S3-9) portent naturellement vers un test qui enumere une liste de selecteurs ou
de gabarits. Je les signale maintenant, comme la regle l'exige, et non a la review.

## S3-1 - Des additions qui travaillent tout l'intervalle

**Avis : faisable, mais les criteres tels qu'ecrits ne peuvent pas etre satisfaits ensemble. Aucun
ADR.**

### La question d'architecture, tranchee

**C'est un defaut de cette source de contenu, a corriger chez elle. Ce n'est pas le signe qu'une
propriete de distribution doive devenir exprimable dans le paquet de contenu.**

Le tirage fautif est dans `AdditionsBorneesContentSource.tirer` :
`operandeA = hasard.nextInt(sommeMaximale + 1)` puis
`operandeB = hasard.nextInt(sommeMaximale - operandeA + 1)`. C'est une loi interne du generateur.
Aucun contrat n'est traverse : `ContentSource.next` ne change pas, la mecanique ne voit rien, le
descripteur ne bouge pas. Corriger la loi de tirage a l'interieur de cette classe est une
modification locale, et c'est pour cela qu'aucun ADR n'est requis.

Faire de la "distribution" une notion **du contrat de contenu** serait au contraire la dette n°2 de
`main`, a l'identique : une configuration taillee pour l'arithmetique que chaque contenu de francais
devrait remplir de valeurs factices. "Uniforme sur les couples valides" ne veut rigoureusement rien
dire pour un paquet d'orthographe. `ADR-0006` a deja tranche la bonne frontiere et le code la
respecte : `sommeMaximale` et `nombreDePropositions` vivent sous `parametres` dans **son** paquet
YAML et ne sont lus que par **sa** fabrique. Une eventuelle "loi de tirage" appartient exactement au
meme rayon : un parametre de ce generateur, jamais une capacite du contrat.

La regle qui garde cette frontiere est deja ecrite et mord :
`DifficulteTest.leContratDeContenuNePorteQueDuTexte` porte sur les champs des classes de
`com.recreo.domain.content` - le paquet exact, pas ses sous-paquets. `AdditionsBorneesContentSource`
vit dans `com.recreo.domain.content.source` et peut donc porter des entiers ; c'est deja le cas de
`sommeMaximale`. **Si quelqu'un remontait un reglage de distribution d'un cran, dans le contrat,
cette regle deviendrait rouge.** Le garde-fou existe, il n'y a rien a ajouter.

**Ce qui demanderait un ADR, et que je refuserais** : exposer la distribution a la mecanique, ou
l'ajouter au contrat de contenu pour que d'autres matieres la "remplissent". Je le nomme pour que
personne ne le tente en croyant appliquer `DEC-0007`.

**Une recommandation que je fais sans l'imposer**, parce qu'elle est le prolongement exact de
`DEC-0005` et de S3-3 : les deux seuils pedagogiques de cette classe
(`ACCEPTATION_OPERANDE_NUL_SUR`, et la loi de tirage si elle recoit un reglage) sont aujourd'hui des
constantes Java du domaine. `nadia-prof` a deja fait retoucher l'un d'eux une fois (26 % en s00,
12,9 % en s01). Un seuil qu'une porte pedagogique fait bouger et qui exige de recompiler le domaine
a le meme defaut que le texte d'indice, pour la meme raison. S3-3 ouvre justement le chemin
(`parametres` accueille une entree de plus, lue par la seule fabrique de ce type). Faire suivre le
seuil dans le meme geste ne coute presque rien. Je ne le demande pas comme condition de
faisabilite : c'est un choix de perimetre qui appartient a `camille-po`.

### Le defaut mesure dans les criteres, avec les chiffres

J'ai simule les deux algorithmes candidats, 200 000 tirages chacun, plutot que de raisonner sur le
texte.

**Premier constat : le tirage uniforme sur les 65 couples valides, seul, viole le quatrieme critere
de la story.** Les couples comportant au moins un zero sont 20 sur 65, soit **30,7 %** de tirages a
operande nul (mesure : 30,72 %). Le quatrieme critere de S3-1 exige que le test de `DEC-0004`
point 5 (5 % a 15 %) reste vert. Il ne le resterait pas - et 30,7 % est pire que les 26 % que
`nadia-prof` reprochait deja en s00.

**Deuxieme constat : garder le filtre actuel sur les operandes nuls (accepter un tirage a zero une
fois sur cinq) satisfait le quatrieme critere mais fait sortir deux sommes de leurs fourchettes.**
Mesure : operande nul 8,1 % (conforme), mais somme 1 a **0,82 %** pour une fourchette basse a 1,1 %,
et somme 10 a **19,2 %** pour une fourchette haute a 18,9 %. Ce n'est pas du bruit : c'est la
distribution theorique de l'algorithme compose, qui vaut 0,82 % et 19,18 % exactement.

**La table de la story a ete calculee sur le tirage nu, pas sur le tirage tel qu'il sera reellement
compose avec le filtre deja en place.** C'est la seule cause des deux desaccords.

**Une sortie existe, et elle satisfait les quatre criteres a la fois. Elle est mesuree.** Porter
`ACCEPTATION_OPERANDE_NUL_SUR` de 5 a **3** (accepter un tirage a operande nul une fois sur trois) :

| Reglage | Operande nul | Sommes hors fourchette |
|---|---|---|
| Filtre 1 sur 5 (actuel) | 8,09 % | **1 et 10** |
| **Filtre 1 sur 3** | **12,92 %** | **aucune** |
| Filtre 1 sur 2 | 18,15 % | aucune, mais **hors du 5-15 % de `DEC-0004`** |
| Pas de filtre | 30,72 % | aucune, mais tres loin du 5-15 % |

12,9 % est exactement la proportion que `nadia-prof` a mesuree en s01 et dont elle a ecrit "c'est le
bon reglage, et je le dis explicitement puisque la question m'est posee". La sortie ne demande donc
aucun arbitrage pedagogique nouveau : elle reconduit un reglage deja valide.

**Mais elle change une valeur fixee par `DEC-0004` point 5, et cela n'est pas a moi de le decider.**
Je route a `camille-po` : soit elle acte le passage a 1 sur 3, soit elle recalcule la table de
fourchettes sur la distribution reellement composee. Les deux marchent ; il faut en choisir une, et
la story ne peut pas etre livree telle quelle.

### Un troisieme defaut, de methode celui-la, et il est important

Meme avec le bon reglage, **la table de fourchettes de la story ne survit pas a un changement de
graine si elle reste calee sur la cible du tirage nu**. Mesure sur 6500 tirages, dix graines : **4
echecs sur 10**, sur les sommes 8 et 10. La marge de +/- 2 points est mangee par l'erreur
d'echantillonnage parce que la cible est fausse d'un demi-point aux deux extremites.

Recalculee sur l'algorithme reellement retenu (uniforme sur 65 couples, filtre 1 sur 3), la cible
theorique vaut :

| Somme | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |
|---|---|---|---|---|---|---|---|---|---|---|---|
| Cible | 0 % | 1,29 % | 3,23 % | 5,16 % | 7,10 % | 9,03 % | 10,97 % | 12,90 % | 14,84 % | 16,77 % | 18,71 % |

Avec cette table, +/- 2 points et 6500 tirages : **0 echec sur 20 graines**. Le test est alors
robuste, et pas seulement vert une fois.

**Et une mise en garde de methode, qui releve d'`ADR-0013`.** Un test statistique a **graine unique
fixee** est deterministe : il ne peut pas etre instable, mais il invite au pire des reglages, celui
qui consiste a chercher la graine qui passe. Un garde-fou qu'on accorde jusqu'a ce qu'il soit vert
ne garde rien. **Je recommande que le test tourne sur un jeu de graines declare (par exemple 0 a 9)
et exige que toutes passent** : c'est le meme cout d'ecriture, et une correction qui ne marche que
pour une graine devient rouge. Ce point est du ressort de `yanis-qa` pour la forme, mais je le
signale maintenant parce que le critere tel qu'ecrit ("une graine aleatoire fixee") ouvre la porte.

## S3-2 - Un indice atteignable dans la partie en cours

**Avis : faisable en l'etat. Aucun ADR.** Deux pieges d'implementation, et une reponse a la question
d'ou doit vivre la correction.

### Ou la correction doit vivre pour que le couplage cesse d'etre invisible

Le couplage que `nadia-prof` a mis au jour est reel et il tient a trois reglages loges a trois
endroits :

| Reglage | Ou il vit | A qui il appartient |
|---|---|---|
| `SEUIL_ECHECS_SUR_LE_MEME_ITEM = 3` | `BanditManchotSession`, domaine | mecanique |
| le retour d'un item deja vu (rejet des `dejaVus` sur 20 tentatives) | `AdditionsBorneesContentSource`, domaine | contenu |
| `nombreDeManches: 10` | `catalogue.yaml` | catalogue (`ADR-0012`) |

Aucun des trois n'est fautif isolement. L'aide etait inatteignable a cause de leur **produit**, que
personne ne calculait nulle part. C'est le defaut typique d'une propriete qui n'a pas de
proprietaire.

**La correction doit vivre dans la mecanique, et elle doit supprimer le couplage plutot que le
documenter.** Le declencheur retenu par `DEC-0007` point 3 - trois echecs consecutifs, quel que soit
l'item - ne depend que d'un etat que la mecanique possede deja (`echecsConsecutifs`). Il ne depend
plus ni de la loi de tirage du contenu, ni de la probabilite qu'un item revienne. Deux des trois
dependances disparaissent purement et simplement, ce qui est bien mieux que de les rendre visibles.

**La troisieme ne disparait pas, et c'est elle qu'il faut rendre visible.** Un jeu configure avec
`nombreDeManches: 3` rendrait l'aide de nouveau inatteignable, en silence, sans qu'une ligne de code
change. Ma recommandation, concrete :

1. la mecanique **publie** le nombre de manches en dessous duquel son aide ne peut pas s'afficher
   (aujourd'hui `SEUIL_ECHECS_CONSECUTIFS + 1`, soit 4), plutot que de le laisser prive ;
2. le test d'integration du catalogue (`CatalogueChargeableIT`, qui verifie deja `nombreDeManches`
   depuis l'amendement d'`ADR-0012`) asserte, **pour chaque jeu du catalogue reel et non pour une
   liste ecrite en dur**, que `nombreDeManches` atteint cette valeur publiee.

Le perimetre est alors derive du catalogue, la valeur est derivee de la mecanique, et le couplage
qui restait devient un rouge au lieu d'un silence. C'est une application directe d'`ADR-0012` et
d'`ADR-0013`, sans ADR nouveau. Ecrire le nombre 4 en dur dans le test du catalogue serait la
mauvaise version du meme geste : deux endroits a maintenir, et la divergence ne se verrait pas.

### Piege 1 - le compteur est remis a zero avant que l'indice soit calcule

Dans `BanditManchotSession.tirerManche`, quand le seuil d'echecs consecutifs est atteint **et**
qu'un item a deja ete reussi, la "manche plus simple" s'applique et `echecsConsecutifsApres` passe a
`0` (ligne 120). L'indice est calcule apres, ligne 131.

Une implementation naive qui ferait dependre l'indice de `echecsConsecutifsApres` satisferait le
deuxieme critere de la story (rater les trois premieres manches : rien n'a encore ete reussi, donc
pas de remise a zero) et **echouerait le quatrieme** (une reussite puis trois echecs : la manche
plus simple s'applique, le compteur est remis a zero, l'indice ne s'afficherait jamais) - c'est-a-dire
exactement le cas ou la story exige que les deux aides fonctionnent ensemble. L'indice doit etre
calcule a partir de la valeur **entrante** `echecsConsecutifs`.

### Piege 2 - ne pas laisser derriere soi l'ancien mecanisme

`echecsParItem` et `SEUIL_ECHECS_SUR_LE_MEME_ITEM` deviennent morts si le declencheur change. Ils
doivent partir dans le meme commit. `BanditManchotSession` porte quatorze parametres de
constructeur, et j'avais signale a la cloture de s01 que c'est la trajectoire de la dette n°6 de
`main` ; cette story est l'occasion d'en retirer un plutot que d'en ajouter.

### Une frontiere a ne pas franchir

Le texte de l'indice vient du contenu (`question.indice()`), la mecanique decide seulement **quand**
l'afficher. Un contenu sans indice n'affichera rien meme apres trois echecs consecutifs, et c'est le
bon comportement (`ADR-0004`). Les criteres de la story ne doivent pas etre lus comme "la mecanique
garantit un texte d'aide" : elle garantit un declenchement. Si quelqu'un mettait un texte d'aide par
defaut dans la mecanique, `uneMecaniqueNeVoitQueLesContrats` et le principe directeur du produit
seraient en cause - et S3-3 ferait le chemin inverse le meme sprint.

## S3-3 - L'indice vit dans le contenu

**Avis : faisable en l'etat. Aucun ADR. Verifie en lisant le code du chargeur, pas suppose.**

**Le chargeur unique l'accepte sans nouvelle famille de chargeur, et sans toucher au schema.**
`YamlContentPackLoader.chargerPaquet` lit l'enveloppe commune (schema, type, descripteur) puis
delegue le corps brut a la fabrique enregistree pour ce `type`.
`AdditionsBorneesContentSourceFactory.creer` lit deja `parametres` et en extrait deux entiers. Ajouter
le texte de l'indice, c'est une ligne dans cette fabrique et un parametre de constructeur de plus.
`FABRIQUES`, `games()`, `chargerPaquet`, l'enveloppe de schema et le numero de schema ne bougent pas.

La dette n°3 de `main` - neuf familles de chargeurs quasi identiques - n'est pas approchee : le
nombre de fabriques reste deux, et il ne bougerait que si un **type de paquet** nouveau apparaissait,
ce qui n'est pas le cas ici. La regle `ilNYAQuUnSeulChargeurDeContenu` reste verte sans etre
touchee, et `seulLAdaptateurDeContenuLitDesFichiers` aussi.

Aucune regle ArchUnit n'a besoin d'etre affaiblie, contrairement a ce qu'on pourrait craindre :
`leContratDeContenuNePorteQueDuTexte` vise `com.recreo.domain.content`, pas
`com.recreo.domain.content.source`, et de toute facon un `String` est exactement ce qu'elle autorise.
Le quatrieme critere de la story est donc tenable tel quel.

**Un point de testabilite, et c'est le seul reproche que je fais a cette story.** Son premier
critere - "il ne contient plus aucune constante de texte destine a l'enfant" - est verifiable par
lecture pour la constante `INDICE` nommement, mais pas en general : un automate ne sait pas
distinguer un message d'exception technique en francais (il y en a dans le domaine) d'un texte
destine a l'enfant, et une regle qui essaierait serait une machine a faux rouges. Ce qui **est**
testable, et qui prouve davantage : une fixture de paquet YAML portant un autre texte d'indice, et
un test qui verifie que le texte affiche suit la fixture. C'est une preuve positive que le texte
n'est plus code en dur, derivee du paquet et non de la source Java. Je recommande cette forme a
`yanis-qa` plutot que la lecture du fichier Java.

## S3-4 - Le nombre doit peser plus que le bouton

**Avis : faisable en l'etat. Aucun ADR.** Testable par lecture de `recreo.css`, comme les tests de
taille existants.

Un point d'`ADR-0013` : le critere dit "au moins 2 rem, soit au moins la taille du calcul affiche
(`.enonce`)". Deux valeurs pour une seule exigence. Le garde-fou doit **comparer `.proposition` a
`.enonce`** et non au litteral `2`, sinon le jour ou `.enonce` bouge, la relation que le critere
enonce cesse d'etre gardee sans que rien ne rougisse. C'est la meme forme que
`lIndiceEstAuMoinsAussiGrandQueLaConsigne`, qui est deja ecrite correctement dans le depot.

## S3-5 - Un ecart confortable entre deux boutons

**Avis : faisable, avec une reserve de formulation. Aucun ADR.**

Le critere dit "quand on mesure l'ecart reel entre leurs boites". **L'ecart reel entre deux boites
rendues n'est pas mesurable dans ce depot** : il faut un moteur de rendu (voir S3-8). Ce qui est
mesurable est la declaration `gap` du conteneur `.propositions` (aujourd'hui `0.75rem`), ce qui est
un tres bon proxy ici parce que les boutons sont dans un conteneur `flex` en colonne et qu'aucune
marge ne s'y ajoute - c'est justement ce que la correction de s02 a mis en place pour que les marges
ne se fondent plus.

Je recommande de reformuler le critere sur la declaration, ou d'assumer le proxy en l'ecrivant. Tel
quel, il promet une mesure que personne ne fera. C'est exactement le defaut qu'`ADR-0013` nomme.

## S3-6 - La sortie doit se voir sur l'ecran de fin et l'ecran d'erreur

**Avis : faisable. Aucun ADR.** Mais cette story se heurte a une contrainte reelle imposee par un
garde-fou existant, et il vaut mieux la connaitre avant d'ecrire le code que de la decouvrir en
rouge.

`TailleDesTextesEtBoutonsTest`, dans sa version corrigee par `yanis-qa` (`164b662`), exige pour
**chaque classe portee par un bouton** - pas pour l'union de ses classes - une declaration
`font-size` **et** une declaration `min-height`, toutes deux en rem. Une classe modificatrice du
genre `class="retour-accueil principal"` rendrait donc le build rouge tant que `.principal` ne
declare pas elle-meme ces deux proprietes, alors meme que le bouton est parfaitement style par
`.retour-accueil`.

Ce n'est pas un defaut de la correction de `yanis-qa`, qui ferme un vrai trou ; c'est une
consequence de la seule facon de lire du CSS sans moteur de rendu. Deux chemins possibles pour
S3-6 : donner une classe propre et complete au bouton de chaque ecran (par exemple `.sortie-finale`
declarant tout), ou faire evoluer le garde-fou pour raisonner sur l'union des regles applicables a
un element. **Le second est une question de test qui appartient a `yanis-qa`**, je ne la tranche pas ;
je signale que S3-4, S3-5 et S3-6 vont toutes trois toucher au style des boutons et rencontreront
cette contrainte.

Point d'`ADR-0013` : le garde-fou de S3-6 doit deriver des gabarits la liste des ecrans ou un bouton
de sortie apparait, et non l'enumerer. Sinon un ecran neuf sortira du controle en silence, ce qui
est precisement l'histoire de ce sprint.

## S3-7 - Une icone qui dessine un objet

**Avis : faisable en l'etat, y compris le critere qui parait le plus difficile. Aucun ADR.**

Les criteres techniques (200, type image) sont deja couverts par `FaviconTest`.

**Le critere "dessinee pour 16 px et non obtenue par simple reduction" est mesurable aujourd'hui,
sans nouvelle dependance, et j'ai verifie la structure du fichier plutot que de le supposer.**
`favicon.ico` contient trois entrees (16x16, 32x32, 48x48), et **la charge utile de l'entree 16x16
est un PNG** de 705 octets, dont l'en-tete IHDR declare bien 16x16. Consequence pratique : un test
peut lire l'annuaire ICO (quelques lignes : un en-tete de 6 octets, puis une entree de 16 octets par
image, donnant taille et decalage), extraire la plage d'octets de l'entree 16x16, et la decoder avec
`ImageIO.read` du JDK seul. Aucune bibliotheque nouvelle.

Ce qu'on mesure alors est le proxy que `nadia-prof` a elle-meme employe : le nombre de couleurs
distinctes de la vignette 16 px (122 aujourd'hui, sur 256 pixels, signe d'une reduction floue). Un
dessin pense pour 16 px en compte peu. **Le seuil exact est un choix de design et de pedagogie, pas
d'architecture** : je le route a `ines-design` et `nadia-prof`, et je recommande seulement d'ajouter
aux criteres de la story que l'entree 16x16 soit stockee en PNG - c'est deja le cas, et l'ecrire
evite que le test depende de la chance sur le prochain fichier livre.

Le fond - "un objet reconnaissable en un coup d'oeil pour un enfant qui ne lit pas" - n'appartient
qu'a `nadia-prof`, et la story le dit deja correctement.

## S3-8 - Jouable sur un ecran de largeur telephone

**Avis : faisable en partie seulement. Le critere central n'est pas verifiable par automate dans ce
depot aujourd'hui. Aucun ADR ; ce n'est pas une question de decision mais d'outillage.**

### Ce qui n'est pas verifiable, et ce que couterait de le rendre verifiable

"Aucun element ne depasse la largeur de la fenetre" et "chaque bouton reste entierement visible sans
chevaucher un autre element" sont des proprietes du **rendu**. Les etablir demande un moteur de mise
en page : il faut resoudre le flux, le `flex`, les retours a la ligne et les polices reelles. Aucune
lecture de CSS ne les decide, et le depot n'a pas de moteur de rendu.

Le cout de l'avoir, dit franchement : une dependance de navigateur sans tete (Playwright ou
Selenium), c'est-a-dire le telechargement d'un binaire de navigateur dans la CI a chaque
construction ou un cache a gerer, un temps de build sensiblement plus long, un mode de defaillance
nouveau et etranger au produit (un navigateur qui ne demarre pas), et la premiere dependance
d'execution du depot en dehors du JDK et de Spring. `CLAUDE.md` pose "zero build Node, zero serveur
front separe" ; ce n'est pas litteralement un build Node, mais c'est le meme genre de poids.
**Je ne le recommande pas pour ce sprint**, et je ne le tranche pas seul : c'est une question
d'outillage que `DEC-0007` route deja a `yanis-qa` et moi. Mon avis ecrit est : non, pas pour trois
criteres visuels, et oui le jour ou la porte pedagogique de `nadia-prof` en dependra pour plusieurs
jeux.

### Le prealable qui manque, et qui vaut plus que le reste de la story

**`accueil.html` ne contient aucune balise `<meta name="viewport">`.** Verifie, c'est le seul gabarit
qui porte un `<head>`. Sans elle, un telephone rend la page dans une fenetre virtuelle d'environ
980 px puis reduit l'ensemble : la mise en page a 375 px CSS n'est jamais exercee sur un vrai
telephone, et le texte arrive a l'ecran a environ 40 % de la taille voulue. **La premisse de la
story n'est donc pas tenue aujourd'hui**, et aucun de ses criteres ne le dit.

C'est une ligne de HTML, et c'est **testable par automate sans rien ajouter** : le `<head>` rendu
contient la balise, avec `width=device-width`. C'est, de tout ce que porte S3-8, ce qui change le
plus pour un enfant qui joue sur le telephone de la maison.

### Ce que je recommande pour que la story ne porte pas un critere que personne ne verifiera

Separer explicitement, dans les criteres :

1. **Verifiable maintenant** : la balise `viewport` existe et vaut `width=device-width` ; aucune
   regle de `recreo.css` ne fixe une largeur en pixels superieure a la largeur cible ; aucun
   conteneur ne force une largeur fixe. Le perimetre de ce garde-fou doit etre **derive** - toutes
   les regles du CSS, tous les gabarits - et non une liste de selecteurs (`ADR-0013`).
2. **Non verifiable, et nomme comme tel** : l'absence de depassement reel a 375 px. Ni automate, ni
   porte pedagogique aujourd'hui, puisque le harnais de `nadia-prof` s'arrete a environ 500 px. La
   story doit l'ecrire, sinon elle affirme une garantie que rien ne produit.

Sans cette separation, S3-8 est exactement le cas de figure d'`ADR-0013` : un critere ecrit, une
absence de garde-fou, et une CI verte qui n'en dit rien.

## S3-9 - Le nom du jeu s'ecrit comme il se lit

**Avis : faisable en l'etat. Aucun ADR.** Deux occurrences a corriger, mesurees :
`accueil.html` ligne 5 (`<title>Recreo</title>`) et ligne 17 (`<h1>Recreo</h1>`). Aucune autre dans
les gabarits ni dans `catalogue.yaml`.

**Piege de balayage, et il est serieux.** Le second critere ("tout autre gabarit qui affiche le nom
du produit") invite a chercher la sous-chaine "recreo" dans les gabarits. Elle y est partout et sans
rapport avec le texte lu par l'enfant : `@{/css/recreo.css}`, `@{/js/recreo.js}`, et la palette
`--recreo-accent` du CSS. Un garde-fou naif sur le source brut serait rouge en permanence, ou serait
affaibli jusqu'a ne plus rien garder. Le balayage doit porter sur les **noeuds de texte rendus**
(jsoup, deja utilise dans ce module, et jamais une expression reguliere - `AssertionsWebTest`), et
le titre du document doit etre traite explicitement puisqu'il n'est pas un noeud de texte du corps.

## La dette orpheline que j'ai trouvee, et ou je la range

`TexteAccentueDesGabaritsTest` enumere **trois gabarits sur les quatre existants** et omet
`fragments/erreur.html` - precisement l'ecran qui fondait le troisieme motif de refus de
`nadia-prof` en s00. Une faute d'accentuation y reviendrait sans qu'aucun test ne rougisse. Je l'ai
signalee dans `squad/sprints/s02-identite-visuelle/confirmation-archunit.md` et elle n'a pas de
proprietaire.

**Je recommande qu'elle entre dans s03, portee par S3-9, et je dis pourquoi plutot que de la mettre
en dette par confort.** S3-9 va de toute facon ecrire un balayage de tous les gabarits pour verifier
qu'aucune des deux graphies ne cohabite : c'est le meme fichier de test, le meme geste et la meme
forme de garde-fou. Faire deriver la liste des gabarits du repertoire `templates/` au lieu de
l'enumerer ferme le trou de `fragments/erreur.html` **par construction**, et retire une entree de la
dette d'`ADR-0013` au lieu d'en ajouter une. Le cout est de trois lignes.

Si le planning juge que cela deborde S3-9, alors la dette doit recevoir un proprietaire nomme -
`yanis-qa`, dont c'est le fichier - et une echeance, pas un renvoi a "plus tard". Ce que je refuse
est qu'elle reste sans proprietaire, ce qui est l'etat actuel.

## Ce que cet avis ne dit pas

- Il ne repriorise rien et ne recrit aucune story. Les trois desaccords de criteres que je signale
  (S3-1, S3-5, S3-8) appartiennent a `camille-po` ; je donne les mesures, pas la reecriture.
- Il ne prononce aucun Done, ni technique ni pedagogique. Le Done pedagogique appartient a
  `nadia-prof` seule, et rien ici ne l'anticipe.
- Il ne dit pas que les seuils retenus sont pedagogiquement justes. Que 12,9 % d'operandes nuls, un
  indice a la quatrieme manche ou 375 px soient les bonnes valeurs n'est pas de mon ressort ; je dis
  seulement lesquelles sont atteignables ensemble.
- Il ne garantit pas l'absence d'autres couplages du genre de celui de S3-2. J'en ai cherche un, je
  l'ai trouve et nomme (`nombreDeManches` et la longueur minimale d'une partie utile) ; je n'affirme
  pas qu'il n'y en a pas d'autre.
