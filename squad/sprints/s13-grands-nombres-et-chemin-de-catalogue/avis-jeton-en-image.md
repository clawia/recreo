# [Sprint s13] Avis de faisabilite - le jeton qui ne tient pas dans sa colonne

- **Date** : 2026-08-16
- **Auteur** : theo-archi
- **Question posee par l'utilisatrice** : *"transformer le nombre en image est-il possible, on
  pourrait les integrer au sources ?"*
- **Objet** : quatre voies chiffrees pour que le nombre entre dans sa colonne, apres le second refus
  de `nadia-prof` sur `cm1-grands-nombres` (`FICHE-bandit-manchot-grands-nombres-cm1.md`, verdict du
  2026-08-16). Je ne tranche pas et je n'implemente rien : l'utilisatrice choisit, et tout ce qui
  touche a ce que l'enfant recoit revient a `nadia-prof`.
- **Arbre juge** : `round-two` a `34abbb7`, arbre propre. Worktree dedie hors de l'arbre du depot,
  branche `s13-theo-jeton`.
- **Prerequis** : `JAVA_HOME` sur un JDK 21, positionne le temps de chaque commande et jamais ecrit
  dans un fichier commite. Constat rendu par `outils/verifier-jdk.sh` sur ce poste : `java du PATH :
  1.8.0_202 | JDK de compilation : 21.0.10 | JAVA_HOME : vide | toolchains lu par Maven : non
  (fichier present sous un nom non lu)`. **Toutes les mesures Java de ce document sont prises sur le
  JDK 21.0.10.**

## Methode des chiffres cites

Tout chiffre de ce document vient de l'une de ces trois mesures, et aucune autre.

**1. Etat de reference du depot.** `mvn -B clean verify` complet, jamais incrementiel, sur JDK
21.0.10 depuis le worktree `s13-theo-jeton` a `34abbb7`. **BUILD SUCCESS, 487 tests, 0 echec, 0
erreur.** Le compte de 487 est obtenu en sommant l'attribut `tests` des **76 rapports XML** de
`surefire-reports` et `failsafe-reports` des cinq modules, et non les lignes `Tests run` du journal :
les rapports texte n'en gardent qu'un par moteur JUnit. Le chiffre concorde avec celui de
`nadia-prof`.

**2. Geometrie du rendu.** Sonde de mesure non commitee, hors de l'arbre du depot. Methode : la
**feuille maison reelle** (`static/css/recreo.css`) et la **bibliotheque vendorisee reelle**
(`static/vendor/bootstrap.min.css`) servies sur HTTP depuis un port dedie, le **balisage reel** de
`fragments/manche.html` avec ses attributs Thymeleaf resolus, dans une **iframe de 360 px exactement**
placee dans une fenetre Chrome de 800 px (technique et plancher de fenetre d'`ADR-0025` et de
`outils/mesurer-rendu.sh`), geometrie lue au dixieme de pixel sur le document rendu.

Perimetre : **319 items**, c'est-a-dire **la totalite des items des onze paquets YAML de
production** qui portent au moins deux propositions, plus **un item fabrique pour
`cp-additions-jusqu-a-10`**, dont les propositions sont generees a l'execution et n'existent dans
aucun fichier (`AdditionsBorneesContentSource`, `String.valueOf(somme)`). Exclusion unique et
nommee : rien. Les propositions sont celles des YAML, lues telles quelles, separateur U+00A0
compris.

*Concordance avec `nadia-prof`, par une methode differente de la sienne* : sur les 28 items de
`cm1-grands-nombres`, ma sonde trouve **20 items debordants et un pire debord de 33,1 px par cote**,
la ou son releve donne 20 et 33,0. Les deux mesures se rejoignent, la sienne sur le jar reel joue,
la mienne sur la feuille reelle rendue. Je ne rejoue pas son verdict, je verifie que je mesure la
meme chose qu'elle.

**3. Ce qu'un garde-fou voit d'un changement de mise en page.** `mvn -B clean verify` complet, sur
JDK 21.0.10, avec la voie 4 ci-dessous **reellement appliquee a `recreo.css`** dans le worktree, puis
`git checkout` du fichier. Le worktree est propre a la remise de cet avis.

## Le fait qui domine les quatre voies, et qu'il faut lire avant elles

**La mise en page des colonnes n'est gardee par rien.** Ce n'est pas une inquietude, c'est une
mesure : j'ai applique a `recreo.css` le changement qui **ferme les 29 debordements des douze
paquets**, et `mvn -B clean verify` rend **BUILD SUCCESS, 487 tests, 0 echec**. Pas un test n'a
bouge, ni dans un sens ni dans l'autre.

Cela veut dire deux choses, et la seconde est la plus derangeante :

- aucune des quatre voies ne sera **freinee** par un garde-fou existant sur la propriete qui compte.
  `ADR-0025` l'avait deja ecrit : *"le debordement reste non gardable"* ;
- et aucune des quatre voies ne sera **prouvee** par un garde-fou existant non plus. L'etat que
  `nadia-prof` a refuse deux fois est vert de bout en bout, et l'etat qui le corrige l'est autant.

Un cas precis le montre. `TailleDesTextesEtBoutonsTest.leRepliEnDeuxRangeesEstScopeALaQuatriemePropositionSeulement`
affirme dans son nom que le repli en deux rangees ne s'active qu'a partir de la quatrieme
proposition. Avec la voie 4 appliquee, `.propositions` se replie **toujours**, et ce test **reste
vert** : il verifie que la regle `:has(.proposition:nth-child(4))` existe et contient
`flex-wrap: wrap`, jamais qu'elle est la seule a le declarer. Il ne devient pas faux, il devient
**decoratif**. Le lot qui touchera a cette mise en page devra le reprendre, quelle que soit la voie
retenue.

**Consequence pour l'ADR a venir** : quelle que soit la voie choisie, elle doit arriver avec une
regle que les 487 tests actuels n'ont pas. Le critere que j'applique a cette regle est celui-ci :
**rougit-elle sur l'etat que `nadia-prof` a refuse le 2026-08-16 ?** Si non, elle est decorative.
Une seule des quatre voies passe ce critere (voie 4, section correspondante).

## Ce que la mesure ajoute au constat de `nadia-prof`

Son releve porte sur `cm1-grands-nombres`. Sur les 319 items des douze paquets, **29 debordent
aujourd'hui, dans trois paquets** :

| paquet | items debordants | pire debord par cote | cause du pire cas |
|---|---|---|---|
| `cm1-grands-nombres` | 20 sur 28 | 33,1 px | `cinquante-` (mot francais long) |
| `cm1-mesures` | 8 sur 24 | 16,4 px | `6000000` dans `6000000 km` (7 chiffres colles, sans separateur) |
| `cm1-perimetre` | 1 sur 22 | 25,3 px | `interieure` dans `sa surface interieure` (mot francais long) |
| les neuf autres paquets | 0 | - | - |

Les **neuf items hors `cm1-grands-nombres`** debordent **en production, aujourd'hui**, sur deux jeux
que personne n'a mesures sous cet angle. Ils ne relevent pas du separateur de milliers, et
`cm1-mesures` n'en porte meme aucun. **Ce n'est donc pas un defaut du paquet refuse, c'est un defaut
de la mise en page, et il est deja livre.** Je le remonte comme un fait ; qu'il vaille un refus, une
reserve ou rien du tout n'est pas a moi de le dire, c'est a `nadia-prof`, et le perimetre de ce qu'on
en fait est a `camille-po`.

Un second fait, sur le seul paquet a quatre propositions : `cm1-conjugaison-present` ne deborde pas,
mais sa proposition la plus large (`mangeons`, 146,3 px sur l'item *"Vous ___ du chocolat."*) tient
dans sa colonne de 152 px avec **2,9 px d'air par cote**. C'est le mot meme qui avait declenche
`DEC-0018` a -2,01 px avant l'exception a 1,875 rem : il tient desormais, de 2,9 px. Un mot plus long
dans ce paquet le fait deborder, et il n'existe aucun garde-fou pour le dire.

## Voie 1 - le jeton en image, integre aux sources

C'est la voie que l'utilisatrice nomme. Je la chiffre sans la privilegier ni l'ecarter.

### Ce qui est vrai et qu'il faut arreter de repeter

Le depot **autorise deja les images maison**, et j'ai verifie plutot que de me croire : `ADR-0036`
l'acte, `static/images/cabinet-fronton.svg` est un SVG maison servi et affiche par
`background-image: url('/images/cabinet-fronton.svg')`, et `favicon.ico` est une image binaire servie
depuis S6-1. Il n'y a **aucune autorisation a demander** pour poser un fichier image dans les
sources.

### Le mur, et il est declaratif

`ADR-0036` section 1 exige qu'une image soit designee par **un chemin ecrit en clair, jamais construit
par concatenation ou par expression**. Ce n'est pas une preference de style, c'est encode :
`RessourcesTiercesTest.cheminDeGabarit` refuse toute cible dont un caractere n'est pas un caractere
d'URI, et les accolades d'une expression Thymeleaf n'en sont pas.

Un jeton en image demande **exactement la forme interdite** :
`th:src="@{'/images/jetons/' + ${proposition} + '.svg'}"`. **Rouge.**

Et ce refus ne tient pas par accident : `ADR-0036` dit noir sur blanc que ce garde-fou porte la
**condition C5 de `nadia-prof`**, *"le jeu d'images du cabinet ne depend ni de l'item tire, ni du
numero de la colonne, ni de la reponse attendue"*. Un jeton en image depend des trois. **La voie 1
n'est pas bloquee par une regle technique qu'un ADR de ma main pourrait lever : elle est bloquee par
une condition pedagogique de `nadia-prof`, et c'est a elle de dire si elle la leve.** Je remonte, je
ne tranche pas.

### Ce que la voie coute en pixels : rien, et c'est le point

Une image ne cree pas de place. Pour qu'une image de `1 000 000` tienne dans une colonne de 96 px,
il faut qu'elle soit reduite dans le rapport `96 / 146,5`. A la police de production (32 px), cela
donne des glyphes de **21,0 px apparents**. Mesure sur les 29 items qui debordent, en prenant pour
chacun son plus large jeton insecable :

| | taille de glyphe apparente | equivalent en rem |
|---|---|---|
| minimum (`cinquante-`, `528 006`) | **18,95 px** | 1,184 rem |
| mediane des 29 | 25,75 px | 1,609 rem |
| maximum | 30,45 px | 1,903 rem |

**L'enfant voit exactement ce qu'il verrait avec la voie 2, aux memes pixels pres, et sur un item il
voit moins que le plancher de lisibilite de 1,25 rem.** L'image n'achete pas un pixel de largeur :
elle **retire le chiffre du champ de `TailleDesTextesEtBoutonsTest`**, qui lit une declaration
`font-size` dans le CSS et ne saura jamais lire la hauteur d'un glyphe dessine dans un PNG. Le
plancher de 1,25 rem, gagne sur un refus de `nadia-prof` en S2-1, resterait declare et cesserait
d'etre vrai.

C'est le cout central de cette voie et je le nomme sans detour : **elle ne corrige pas le defaut,
elle le rend invisible au garde-fou.**

### Combien de garde-fous cessent de voir ce contenu

- **103 tests, dans 14 classes de `adapter-content`**, derivent leur sujet du **texte** d'une
  proposition : `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` (ADR-0041, la valeur derivee
  du texte), `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`,
  `AucunePropositionNEstEgaleAUneAutreDansUnItemTest` (unicite par comparaison de chaines),
  `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`, `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest`,
  `ChaquePaquetExpliqueSesItemsAVariationTest`, et les huit garde-fous de paquet. **Aucun ne cesse
  d'etre vert.** Ils continuent tous de juger une chaine YAML qui n'atteint plus l'oeil de l'enfant.
  Le lien entre la chaine jugee et l'image vue n'est garde par rien.
- **Ce lien est-il encodable ?** Cela depend du format, et la reponse est nette dans les deux sens.
  - En **SVG contenant un noeud `<text>`**, oui : un garde-fou peut ouvrir le fichier, lire le
    `<text>` et exiger l'egalite avec la proposition du YAML. Mais cela **impose** que l'image soit
    du texte dessine par le navigateur, donc interdit toute vraie illustration - ce qui vide la voie
    de son interet, puisqu'on serait revenu a du texte, avec un fichier de plus par nombre.
  - En **PNG ou en SVG a glyphes traces**, non, et `ADR-0036` section 4 l'a deja ecrit :
    *"un garde-fou de contenu redactionnel ne lit pas un binaire"*. **Rien ne prouverait jamais que
    l'image de `1 000 000` montre `1 000 000`.** Une image fausse serait un defaut de contenu
    scolaire - le motif de refus que `nadia-prof` traite comme suffisant a lui seul - et il serait
    vert en CI.
- **Le nom accessible du bouton.** Aujourd'hui il vient du noeud de texte pose par `th:text`. Avec
  une image il viendrait de `alt`, pose par `th:alt`. Or `th:alt` n'est ni dans
  `POSE_SON_TEXTE_AU_RENDU` de `TexteAccentueDesGabaritsTest`, ni dans `ATTRIBUTS_DE_TEXTE` de
  `AucunTexteNeVitDansUnSousArbreMasqueTest` : **un `alt` absent, vide ou faux serait vert partout**.
  La reserve F11 de `nadia-prof` est entiere et l'utilisatrice fera l'ecoute elle-meme ; cette voie
  est la seule des quatre qui **ajoute** un objet muet sur le trajet du lecteur d'ecran, et la seule
  dont le resultat de cette ecoute peut tout invalider apres coup.

### Qui produit ces images, et combien

- **`cm1-grands-nombres` seul** : 28 items x 3 propositions = **84 occurrences**, pour **66 chaines
  distinctes** (42 portent un U+00A0).
- **Les onze paquets YAML** : **354 chaines distinctes**. La mise en page etant celle du bandit
  manchot, une voie appliquee a un seul paquet laisse les dix autres avec deux rendus differents
  pour la meme mecanique.
- **`cp-additions-jusqu-a-10` n'a aucune chaine dans aucun fichier** : ses propositions sont
  produites a l'execution. Aucune image ne peut etre pre-produite pour lui. Soit ce paquet garde le
  rendu texte - et la mecanique unique a deux rendus, ce qui est la dette 4 de `main` sous une autre
  forme -, soit il faut un generateur d'image a l'execution, qu'aucun garde-fou du depot ne saurait
  verifier.
- **Toute correction de contenu redemande une image.** Le paquet refuse a deja ete corrige deux fois
  en trois jours ; chaque correction d'un chiffre aurait redemande un fichier.
- **Poids** : `ADR-0036` section 5 refuse tout plafond de poids et ecrit la dette en clair - *"le
  commit qui livre la premiere image illustree pose le cliquet de poids"*. Ce cliquet **n'existe
  pas**. La dette d'images vaut aujourd'hui 1 014 octets (`favicon.ico`). 354 images entreraient
  sans aucun plafond : de l'ordre de 90 ko en SVG de texte minimal, de l'ordre de 1,5 Mo en PNG a
  deux densites. Le lot qui les livre doit poser ce cliquet a la valeur qu'il installe.

### Ce que la voie rend impossible ensuite

- **Un contenu pilote par des regles.** Le principe directeur du produit est qu'une meme mecanique
  serve n'importe quelle matiere sans modification de code. Une proposition qui n'est jouable
  qu'accompagnee d'un fichier binaire fabrique a la main **cesse d'etre du contenu** : ajouter un
  item devient un geste de developpeur, plus un geste de contenu.
- **La derivation de valeur d'`ADR-0041`.** Elle lit ce que la proposition **ecrit**. Si l'ecriture
  est une image, la derivation ne juge plus l'objet rendu mais son etiquette, et l'ADR le plus recent
  du depot perd son sujet sans qu'aucune ligne ne change.
- **La correction par un non-developpeur.** `nadia-prof` corrige aujourd'hui un contenu en editant
  un YAML. Elle ne pourra plus.

## Voie 2 - la police reduite sur les propositions longues

### Les planchers, et ce qu'ils autorisent exactement

Trois sont ecrits, et les trois sont des acquis de refus pedagogiques, pas des variables de mise en
page :

1. **1,25 rem, plancher general de lisibilite** de tout bouton rendu
   (`TailleDesTextesEtBoutonsTest.toutBoutonReellementRenduEstAuMoinsAUnRemVingtCinq`), acquis du
   refus de S2-1.
2. **2 rem exactement pour `.proposition`**, et au moins autant que `.enonce`
   (`leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul` : `isGreaterThanOrEqualTo(2.0)` ;
   `laPoliceDuRepliAQuatreColonnesEstUneExceptionAuPlancherGeneralDeDeuxRem` : `isEqualTo(2.0)`),
   acquis de la reserve 1 de `nadia-prof` sur S2-1.
3. **Une seule exception nommee, 1,875 rem, sous le repli a quatre colonnes**, et
   `lesSeulesTaillesDePropositionSontLesDeuxReglesNommees` **enumere** : la feuille maison porte
   exactement ces deux regles, a leur valeur litterale, et **une troisieme regle qui fixe la taille
   d'une proposition est rouge, requete de media comprise**. `ADR-0017` porte la comparaison de
   saillance entre deux boutons d'un meme ecran ; c'est la condition 7 de l'arbitrage du 2026-08-11
   qui a impose cette enumeration, apres que `nadia-prof` a accorde l'exception **une fois, sur ce
   seul cas**, et ecrit que la deuxieme se lui demande.

**Une taille qui s'adapte au jeton est donc exclue deux fois** : la condition 3 de `nadia-prof`
refuse *"ni variable, ni calc"*, et le garde-fou l'encode - `proprieteEnRemSiExiste` ne rend une
valeur que si elle est litterale en `rem`. Un `clamp()`, un `calc()`, une variable ou une taille
calcule cote serveur rougissent.

### Ce que la voie coute en pixels

Mesure sur les 319 items, par abaissement uniforme de la police des propositions :

| police | items debordants sur 319 | pire debord par cote |
|---|---|---|
| 2 rem (production) | 29 | 33,1 px |
| 1,875 rem (exception deja accordee) | 17 | 28,0 px |
| 1,5 rem | 5 | 12,8 px |
| **1,25 rem (plancher de lisibilite)** | **1** | **2,7 px** |
| 1,1875 rem | 1 | 0,1 px |
| 1,125 rem | **0** | -2,4 px |

**Au plancher de lisibilite, le defaut n'est pas ferme.** Il reste un item, `Lis ce nombre :
528 006.`, a **2,7 px de debord par cote**. Ce n'est pas une marge d'arrondi : `DEC-0018` et une
reprise entiere au sprint 8 ont ete declenchees par **2,01 px** sur `mangeons`, et `nadia-prof` a
rappele cette barre dans son verdict du 2026-08-16. **2,7 px est au-dessus de la barre qu'elle a
elle-meme posee.** La premiere taille qui ferme le defaut est **1,125 rem, soit 0,125 rem sous le
plancher**.

Cout vertical, au passage et non nul dans le bon sens : a 1,25 rem, la hauteur maximale du bloc de
propositions passe de 362 px a 236 px sur `cm1-grands-nombres`, parce que le texte se coupe sur moins
de lignes.

### Ce que la voie rend aveugle

- Rien, mecaniquement : elle est la seule des quatre a **rougir** avant d'etre livree, sur trois
  tests a la fois (`leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul`,
  `laPoliceDuRepliAQuatreColonnesEstUneExceptionAuPlancherGeneralDeDeuxRem`,
  `lesSeulesTaillesDePropositionSontLesDeuxReglesNommees`). C'est un point en sa faveur, pas contre
  elle : les garde-fous font ici leur travail.
- Mais la seule facon de les faire redevenir verts est **d'abaisser les valeurs qu'ils epinglent**,
  c'est-a-dire de dissoudre trois acquis pedagogiques dans une correction de mise en page. Le
  garde-fou survit ; ce qu'il garde, non.

### Ce que la voie rend impossible ensuite

Elle **consomme le plancher**. Une fois `.proposition` a 1,125 rem, il n'y a plus de reserve : le
prochain mot francais long n'aura plus rien a ceder, et la phrase *"aucun texte lu par l'enfant ne
passe sous la taille de la consigne"* n'aura plus de sens, puisque le texte le plus important de
l'ecran sera le plus petit.

## Voie 3 - les propositions empilees, une par ligne

Mesure sur les 319 items, `.propositions` en colonne :

| | valeur |
|---|---|
| items debordants | **0 sur 319** |
| cout vertical moyen | **+163,6 px** |
| cout vertical median | +180,0 px |
| cout vertical maximum | +180,0 px |
| items au cout nul | **0 sur 319** |
| paquets dont au moins un item passe sous le pli a 640 px | **12 sur 12** (contre 6 sur 12 aujourd'hui) |
| items passant sous le pli | **319 sur 319** (contre 129 aujourd'hui) |

*Note de methode sur le pli* : ma sonde affiche toujours le message et l'indice, la ou `nadia-prof`
joue un premier echec reel. Mes valeurs absolues ne sont donc pas comparables aux siennes ni au
compte de `DEC-0025` ("trois jeux sur sept"). **La comparaison entre v0, voie 3 et voie 4 est faite
au sein de ma seule sonde et elle est valide ; le compte absolu ne l'est pas.**

**La voie 3 fait payer les onze jeux pour trois.** Elle ajoute de 90 a 180 px aux **neuf paquets,
245 items, qui ne debordent pas d'un pixel aujourd'hui** : `cm1-tables-multiplication` (99 items,
+180), `cm1-conjugaison-present` (25, +174), `cp-comparer-nombres` (24, +180), `cp-soustractions`
(24, +180), `cm1-homophones` (24, +90), `cp-problemes-additifs` (22, +180), `cp-le-bon-son` (15,
+180), `cp-complements-10` (11, +180), `cp-additions-jusqu-a-10` (+180). Sur un produit qui porte
deja une dette de pli assumee par ecrit
(`DEC-0025`) et trois jeux hors du pli, elle transforme une dette de six paquets en dette de douze.

**Ce qu'elle rend impossible ensuite** : le retour. `DEC-0025` a ete signee sur un etat mesure ; la
rouvrir vers le pire sur la totalite du catalogue rend tout arbitrage de pli ulterieur plus cher, et
elle prive `ines-design` de l'argument de la colonne, qui est la forme meme du bandit manchot
(`ADR-0026`). Une machine a sous dont les rouleaux sont empiles verticalement n'est plus une machine
a sous.

## Voie 4 - la colonne recule devant son contenu (celle que j'ajoute)

Deux lignes de CSS, sans nouvelle classe, sans nouvelle regle de taille, sans image :

```css
.propositions { flex-wrap: wrap; }   /* ajoute au bloc existant */
.proposition  { flex: 1 1 auto; }    /* remplace flex: 1 1 0 */
```

La cause du defaut, en une phrase : `flex: 1 1 0` donne a chaque colonne une base de zero, donc trois
colonnes se partagent la largeur **quoi qu'elles contiennent**, et un `<button>` laisse son texte
sortir de sa boite plutot que d'exiger sa place. Avec `flex-basis: auto`, la base d'une colonne est ce
qu'elle contient ; avec `flex-wrap: wrap`, ce qui ne rentre pas passe a la ligne au lieu de deborder.
**Les colonnes ne sont plus rognees par une base fixee d'avance : elles restent cote a cote quand
elles tiennent et se replient quand elles ne tiennent pas.**

Mesure sur les 319 items, avec la modification **reellement appliquee a `recreo.css`** :

| | valeur |
|---|---|
| items debordants | **0 sur 319** (contre 29) |
| pire debord par cote | -2,9 px (air positif partout) |
| cout vertical moyen | **+16,8 px** |
| cout vertical median | **+0,0 px** |
| cout vertical maximum | +180,0 px |
| items au cout nul | **252 sur 319 (79 %)** |
| paquets dont au moins un item passe sous le pli a 640 px | **6 sur 12, inchange** |
| items passant sous le pli | 138 (contre 129, soit +9, tous dans `cm1-mesures`) |

Detail par paquet du cout vertical maximal : `cm1-tables-multiplication` **+0**,
`cp-comparer-nombres` **+0**, `cp-soustractions` **+0**, `cp-complements-10` **+0**, `cp-le-bon-son`
**+0**, `cp-problemes-additifs` **+0**, `cm1-homophones` **+0**, `cm1-conjugaison-present` **+0**
(le repli a quatre colonnes le protege, sa specificite l'emporte), `cm1-grands-nombres` +132,
`cm1-mesures` +132, `cm1-perimetre` +180.

**Elle ne coute que la ou le contenu est large, et rien ailleurs.** C'est la difference exacte avec
la voie 3, qui coute partout.

Deux variantes que j'ai mesurees et qui sont **dominees**, donc que je ne propose pas : deux colonnes
par rangee a 2 rem (1 item deborde encore, +186 px) et deux colonnes par rangee a 1,875 rem (0
debordement mais +156 px, et une troisieme regle de taille donc rouge). Les deux sont pires que la
voie 4 sur les deux axes a la fois.

### La regle executable qui l'accompagne, et pourquoi elle n'est pas decorative

C'est la seule des quatre voies dont la correction s'ecrit en propriete declarative verifiable, et
qui **rougit sur l'etat que `nadia-prof` a refuse**. Formulation :

> Le conteneur des propositions declare `flex-wrap: wrap`, et la regle de la proposition declare une
> base de flexibilite qui n'est pas une longueur nulle. Une base nulle sur un conteneur qui ne se
> replie pas est la forme exacte du defaut du 2026-08-16.

Fichier : `adapters/adapter-web/src/test/java/com/recreo/adapter/web/TailleDesTextesEtBoutonsTest.java`
ou un fichier voisin du meme module, avec la machinerie de lecture de CSS deja existante
(`blocsMaison`, `debutDeDeclaration`, `valeurDeclaree`) - aucune expression reguliere, `ADR-0008` les
interdit dans ce module.

**Epreuve de morsure, obligation 3 d'`ADR-0013`** : le jeu fautif n'est pas une fixture de ma main,
c'est **l'etat reel du depot a `34abbb7`**, celui que `nadia-prof` a refuse. Une regle dont le
contre-exemple est un etat de l'historique se prouve sur du materiau que personne n'a arrange.

**Ce qu'elle n'interdit pas, et qu'il faut ecrire** : elle ne verifie pas qu'un texte est visible.
`ADR-0025` tient toujours, le debordement reste non gardable, et la seule autorite sur ce que
l'enfant lit reste `nadia-prof`. Elle interdit une chose et une seule : **qu'une regle maison
reimpose une base de colonne fixee d'avance**, c'est-a-dire qu'on refasse exactement ce qui a ete
fait.

**Ce qu'elle oblige a reprendre** : `leRepliEnDeuxRangeesEstScopeALaQuatriemePropositionSeulement`
devient decoratif sur sa moitie `flex-wrap` (voir plus haut). Le meme lot doit le reformuler ou en
retirer la clause devenue vide, sans quoi le depot porte un test qui affirme un scope qui n'existe
plus.

### Ce que la voie rend impossible ensuite

- **Elle rend le nombre de colonnes imprevisible pour `ines-design`.** Un ecran ne montre plus
  toujours trois colonnes : il en montre trois, deux ou une selon le contenu tire. Tout habillage qui
  supposerait trois colonnes cote a cote - un rouleau par colonne, une animation qui compte les
  colonnes - devient faux. `ADR-0029` (chaque colonne porte son decalage d'animation) reste tenu
  puisqu'il derive du contenu, mais l'aspect visuel du meuble varie d'une manche a l'autre. **C'est
  un effet visible par l'enfant : il releve de `nadia-prof` et de son arbitrage prealable, pas de
  moi.**
- **Elle ne ferme pas la question des 2,9 px d'air de `cm1-conjugaison-present`** : le repli a
  quatre colonnes garde `calc(50% - 0.5rem)`, donc une base fixee. La voie 4 corrige les replis a
  deux et trois colonnes, pas celui a quatre.

## Recommandation, et ce qui la contredit

**Je recommande la voie 4**, et je donne d'abord les trois raisons, puis les trois choses qui la
contredisent.

**Pour** : c'est la seule qui ferme les 29 debordements des trois paquets sans toucher a un plancher
pedagogique, sans ajouter un objet muet sur le trajet du lecteur d'ecran, et sans faire payer les
sept paquets qui vont bien (79 % des items a cout nul, contre 0 % pour la voie 3). C'est la seule
dont la correction s'ecrit en regle executable qui rougit sur l'etat refuse. Et c'est celle qui a le
moins de pieces mobiles : deux lignes de CSS, aucun fichier neuf, aucun ADR a amender.

**Contre, et ce sont de vraies objections :**

1. **Elle change ce que l'enfant voit d'une manche a l'autre**, et ce n'est pas un detail cosmetique
   sur un jeu dont la forme est un meuble a trois rouleaux. Un enfant de CM1 qui voit trois colonnes
   puis une colonne peut lire ce changement comme un signal. **Je n'ai aucune competence pour dire si
   c'est benin, et `nadia-prof` peut refuser cette voie sur ce seul motif** - auquel cas la voie 3,
   qui est stable, redevient la seule qui ferme le defaut sans toucher aux planchers, a son prix de
   +163,6 px partout.
2. **Mon chiffre de +16,8 px de moyenne masque un maximum de +180 px**, et c'est la moyenne qui
   flatte la voie, pas le maximum. Sur `cm1-perimetre`, l'item qui deborde paye le plein tarif de
   l'empilement. Si le critere de decision est le pire cas et non la moyenne, **la voie 4 et la voie
   3 sont a egalite sur les trois paquets qui debordent**, et l'ecart n'existe que sur les neuf
   autres.
3. **Elle ne prouve rien de ce qui a ete refuse.** La regle que je propose garde une cause, pas un
   effet : elle empeche de refaire `flex: 1 1 0`, elle ne dit pas qu'un nombre est lisible. Si
   quelqu'un reintroduit le meme symptome par un autre chemin - une largeur en pixels, un
   `white-space`, une police plus large - elle sera verte. `ADR-0025` reste vrai et je ne pretends
   pas l'avoir contourne.

**Sur la voie 1, la reponse a la question de l'utilisatrice, en une phrase** : oui, techniquement,
des images peuvent entrer dans les sources - elles y sont deja -, mais un jeton en image **ne donne
pas un pixel de plus a l'enfant** (18,95 a 30,45 px de glyphe apparent, soit exactement la voie 2),
il **retire 103 tests du contenu qu'ils croient juger**, il rend **impossible a garder** l'egalite
entre l'image et le nombre des que l'image n'est plus du texte, et il **bute sur la condition C5 de
`nadia-prof`**, que je ne peux pas lever. Si l'utilisatrice veut malgre tout l'explorer, la seule
forme que je saurais garder est un SVG portant un noeud `<text>` egal a la proposition - et cette
forme-la est du texte deguise en fichier, donc elle ajoute 354 fichiers pour ne rien changer a
l'ecran.

## Ce que je remonte, et a qui

- **A `nadia-prof`** : la condition C5 (une image de jeu ne depend ni de l'item, ni de la colonne, ni
  de la reponse) bloque la voie 1 et n'est pas a moi de lever. La variation du nombre de colonnes
  d'une manche a l'autre (voie 4) est un arbitrage prealable qui lui revient. Les neuf items de
  `cm1-mesures` et `cm1-perimetre` qui debordent en production aujourd'hui sont un fait neuf soumis a
  son jugement.
- **A `camille-po` et `sofia-sm`** : le perimetre de la correction - le seul paquet refuse, ou les
  trois paquets qui debordent, ou la mise en page des onze jeux - est une decision de perimetre, pas
  d'architecture. Ma mesure dit seulement que la cause est unique et que la corriger une fois les
  corrige tous.
- **Au lot qui portera la correction, quelle qu'elle soit** : `leRepliEnDeuxRangeesEstScopeALaQuatriemePropositionSeulement`
  est a reprendre, et le cliquet de poids d'images d'`ADR-0036` section 5 est a poser si un fichier
  image entre.

## Ce que je n'ai pas fait

Aucun ADR n'est ecrit et `ADR-index.md` est inchange : cet avis ne decide rien, et je n'ecris pas un
ADR pour une decision qui n'est pas prise. L'ADR viendra avec la voie retenue, et il portera sa regle
dans le meme commit. Rien n'est implemente : le seul changement de code de ce travail a ete une sonde
de mesure appliquee puis retiree par `git checkout`, et le worktree est propre.
