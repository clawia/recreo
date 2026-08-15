# [Sprint s13] Avis de faisabilite - theo-archi

- **Date** : 2026-08-15
- **Auteur** : theo-archi
- **Objet** : les cinq stories affectees provisoirement par `planning.md` ("Ce qui demarre
  maintenant"). Aucune affectation ne devient ferme avant cet avis ; c'est `DEC-0030` qui l'exige et
  `sofia-sm` qui a refuse de l'ecrire a ma place.
- **Arbre juge** : `round-two` a `9430cfd`, arbre propre. Worktree dedie hors de l'arbre du depot,
  branche `s13-theo-faisabilite`.
- **Prerequis** : `JAVA_HOME` sur un JDK 21, positionne le temps de chaque commande et jamais ecrit
  dans un fichier commite. Constat rendu par `outils/verifier-jdk.sh` sur ce poste : `java du PATH :
  1.8.0_202 | JDK de compilation : 21.0.10 | JAVA_HOME : vide | toolchains lu par Maven : non
  (fichier present sous un nom non lu)`. **Toutes les mesures de ce document sont prises sur le JDK
  21.0.10** ; le `java` du `PATH` ne compile pas.
- **Ce que je ne fais pas ici** : aucun Done pedagogique ni son anticipation, aucun arbitrage
  produit, aucune repriorisation, aucune redaction de story. Trois points ci-dessous sont
  explicitement rendus a `nadia-prof` ou a `camille-po` plutot que tranches.

## Resume

| Story | Avis | Ce qui manque |
|---|---|---|
| S13-1 | **faisable moyennant un `ADR`** | le garde-fou de rang est **vert et faux** au-dessus de mille. Mesure ci-dessous. |
| S13-4 | **faisable, criteres a durcir** | le critere ecrit n'est pas encodable ; sa forme encodable est plus forte et gratuite. |
| S13-5 | **faisable, un cout cache** | le critere derive rougit sur **trois** distracteurs, pas deux. Le troisieme est pour `nadia-prof`. |
| S12-5 | **faisable en l'etat** | aucun garde-fou n'exige de libelle non vide : la reformulation de `nadia-prof` est libre. Mais le canal qui a laisse passer ce texte huit sprints reste ouvert. |
| S13-6 | **infaisable sans `ADR`** | le critere de "destructeur" du depot **ne voit pas** le geste que le troisieme critere d'acceptation nomme. Mesure ci-dessous. |

Deux stories sur cinq portent un cout que les criteres ne montrent pas, et dans les deux cas ce
cout est un **garde-fou qui reste vert sur le defaut que la story ouvre**. C'est la meme forme de
defaut a chaque fois, et c'est celle que ce depot a deja payee cinq fois.

## S13-1 - Grands nombres jusqu'au million : faisable moyennant un `ADR`

**Le geste lui-meme est le moins couteux du sprint** : un paquet YAML en `type: question`, zero
ligne de Java, une entree de catalogue. Les conditions GN-1 et GN-2 sont des proprietes du texte,
jugees par `nadia-prof`, et rien dans l'architecture ne s'y oppose. Les garde-fous de contenu qui
s'appliqueront a ce paquet le jour ou il existe sont deja derives du repertoire, donc il n'y a
aucune inscription a faire nulle part : `ChaquePaquetPorteUnIndiceTest` (plafond de dette **0**,
aucune exemption possible), `AucunePropositionNEstEgaleAUneAutreDansUnItemTest`,
`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`,
`AucunIndiceNeContreditUnAutreItemDuMemePaquetTest`,
`ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`.

**Le cout cache est ailleurs, et il est mesure.**
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` lit la valeur d'une proposition par
`Pattern.compile("^-?\\d+")`, c'est-a-dire **le premier groupe de chiffres**. Aucun paquet de
production ne porte aujourd'hui un nombre superieur ou egal a mille (verifie sur les onze paquets de
`adapters/adapter-content/src/main/resources/contenu`). S13-1 est le premier paquet du depot a
franchir le millier, et c'est exactement la que le separateur de milliers apparait.

Mesure, sur du materiau reel et non sur une fixture de ma main : j'ai depose dans le repertoire de
contenu de production un paquet de **12 items dont la bonne reponse est, chaque fois, le plus grand
des trois nombres** - un enfant qui presse toujours le plus grand nombre gagne 100 % des manches -
avec les nombres ecrits au separateur d'espace ("1 000 000" contre "999 999" et "998 000").

| mesure | resultat |
|---|---|
| `mvn -B -pl adapters/adapter-content -am test` avec ce paquet | **98 tests, 0 echec** - le garde-fou de rang ne dit rien |
| le meme paquet, un seul `indice` retire | **rouge**, `ChaquePaquetPorteUnIndiceTest` nomme le paquet et l'item |

La seconde ligne est la moitie qui compte : elle prouve que le paquet **etait bien dans le perimetre**
des garde-fous derives du repertoire, et que le silence du premier n'est pas un artefact de portee.
La cause est arithmetique : "1 000 000" est lu **1**, "999 999" est lu **999**, donc le rang calcule
est l'inverse du rang reel, et la repartition truquee se repartit sur les trois rangs au lieu d'en
saturer un seul.

**Ce que j'en conclus, et ce que je ne conclus pas.** Je ne dis pas que le paquet de S13-1 sera
truque - il ne le sera pas. Je dis que sur ce paquet-la, ce garde-fou n'a plus de valeur probante :
il rendra vert quoi qu'il arrive, et son vert sera cite en review comme une preuve. C'est la
definition d'une regle decorative.

**Ce qui est du, dans le meme commit que le paquet** : le critere doit lire le nombre entier et non
son premier groupe - retrait des separateurs d'espace, y compris l'espace insecable, avant lecture -
et sa morsure doit etre prouvee sur un paquet fautif porteur de nombres au-dessus de mille. La
population et le critere restent derives ; ce qui change est une lecture de valeur, pas un perimetre.
Un `ADR` est du parce que la decision porte sur **ce qu'est la valeur d'une proposition** pour tous
les garde-fous qui la lisent, pas seulement pour celui-ci ; l'enonce est ci-dessus, il n'y a rien a
inventer, et je le redige si `sofia-sm` prefere que le lot n'en porte pas la charge.

**Ce que je ne tranche pas** : l'ecriture des nombres pour un enfant de CM1 - "1 000 000",
"1000000", ou la forme litterale - est un jugement de lisibilite qui appartient a `nadia-prof`. Le
correctif ci-dessus vaut quelle que soit sa reponse, et c'est pour cela que je ne l'attends pas pour
le demander.

**Une contrainte de conception a donner a l'auteur du paquet, que les criteres ne portent pas.** Le
plafond de dette de ce garde-fou vaut **exactement** la taille de sa dette (2 noms pour 2, et 3 pour
3 sur son second seuil) : il n'y a **aucune place libre**, et un paquet neuf qui echouerait ne peut
pas y etre exempte. Les items a propositions numeriques du paquet - c'est-a-dire les verbes "ecrire"
et "decomposer", pas "lire" ni "comparer" dont les propositions ne commencent pas par un chiffre -
doivent donc repartir le rang de la bonne reponse des la premiere ecriture, pas apres coup.

## S13-4 - L'enonce qui revient : faisable, et le critere gagne a etre durci

**Etat mesure du paquet**, sur `adapters/adapter-content/src/main/resources/contenu/cm1-tables-multiplication.yaml` :
**99 items, 83 enonces distincts**, et les seize items en trop sont exactement les huit carres de
`2 x 2` a `9 x 9`, portes chacun par trois items au meme enonce qui ne different que par leurs
distracteurs. Le chiffre de `nadia-prof` est exact au fichier pres.

**Rien ne s'oppose a la correction.** Retirer ou reecrire seize items laisse le paquet a 83 items,
tres au-dessus de la contrainte d'`ADR-0023` (strictement plus d'items que de manches, et une partie
en compte dix).

**Le premier critere d'acceptation n'est pas encodable tel qu'il est ecrit**, et c'est le seul point
que je releve : *"le nombre d'enonces distincts se rapproche des 99 items du paquet plutot que de
s'arreter a 83"*. "Se rapproche" n'a pas de mode de defaillance. Un test ne peut pas en sortir.

**Sa forme encodable est plus forte que ce qui est demande, et elle est gratuite** : *aucun item d'un
paquet ne porte le meme enonce qu'un autre item du meme paquet*. Mesure sur les onze paquets de
production : **rouge sur un seul**, `cm1-tables-multiplication` avec ses 16 doublons ; **vert sur les
dix autres**, y compris le paquet combinatoire `cp-additions-jusqu-a-10`. Le garde-fou peut donc
s'ecrire **universel, sans une seule exemption et sans plafond de dette**, dans le commit qui corrige
le paquet - il derive sa population du repertoire et charge les items par le chargeur reel, comme
`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` le fait deja trois metres plus loin.

Ce n'est pas la meme propriete qu'`ADR-0032` : `ADR-0032` interdit deux **ecrans** identiques
(enonce plus propositions), ce qui laisse passer trois enonces identiques a distracteurs differents -
et c'est precisement l'etat du jour. La clause neuve ferme ce cran-la.

**Le second critere - 500 parties, "sans qu'un seuil exact soit fixe ici" - n'est pas encodable non
plus, et il ne doit pas l'etre.** Un protocole de mesure sans seuil est un rapport, pas un test. Il
appartient a `nadia-prof`, qui juge en rejouant, et la clause ci-dessus lui donne mecaniquement ce
qu'elle mesure : 99 enonces distincts sur 99 items est la borne basse du taux de repetition.

## S13-5 - Distracteurs hors de l'espace des tables : faisable, et un cout que personne n'a vu

**Faisable, et le critere est derivable proprement** : l'espace des produits de deux facteurs entre
1 et 10 est une propriete calculable, jamais une liste. Ecrire "aucun distracteur ne vaut 11 ni 110"
serait un critere enumere sous une promesse universelle, c'est-a-dire exactement ce qu'`ADR-0039`
vient d'interdire ; ecrire "tout distracteur appartient a l'espace des produits" est derive et total.

**Le cout que les criteres ne montrent pas.** J'ai passe le paquet reel au critere derive :

| distracteur hors de l'espace des produits 1 a 10 | item |
|---|---|
| `11` | `1 x 10 = ?` |
| `110` | `10 x 10 = ?` |
| `0` | `1 x 1 = ?` |

**Trois, pas deux.** `nadia-prof` en a nomme deux ; le critere derive en trouve un troisieme,
`0` sur `1 x 1 = ?`, qu'elle n'a pas releve et qui n'est pas moins hors de l'espace que les deux
autres. C'est la difference entre un perimetre enumere et un perimetre derive, sur un cas reel et
dans le sens utile.

**Ce que je ne tranche pas, et ce qui bloque le lot tant que ce n'est pas tranche** : `0` est-il un
distracteur legitime pour `1 x 1` ? La question est pedagogique - un enfant qui confond la table de
1 et la table de 0 - et elle est pour `nadia-prof`. Trois issues, et le lot ne peut pas commencer
sans savoir laquelle : elle corrige le troisieme comme les deux autres ; ou elle le declare
legitime, et le critere derive doit alors admettre `0` comme un produit d'un facteur nul, ce qui
elargit l'espace de facon nommee ; ou elle le declare legitime **sans** elargir le critere, et il
faut alors une exemption d'un nom - que je refuserais, parce qu'une exemption d'un nom sur un critere
de trois lignes coute plus cher que la clause qu'elle contourne.

**Remonte a `sofia-sm` pour routage a `nadia-prof`, en meme temps que cet avis** : la question tient
en une ligne et ne demande pas un arbitrage prealable complet.

## S12-5 - L'annonce "Menu principal" : faisable en l'etat

**La verification que `nadia-prof` demande, faite : il n'existe aucun garde-fou qui exige un libelle
non vide.** Methode, dans le meme paragraphe : recherche de `aria-` sur tous les `.java`, `.js`,
`.sh`, `.css` et `.html` suivis par Git, hors `target/` et hors bibliotheque tierce vendorisee. Le
seul `aria-label` du depot est celui de `templates/accueil.html`. `EnTeteEtMenuTest` juge le nombre
d'entrees du menu et le libelle **visible** du lien ("Les jeux") ;
`LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` juge le masquage par la classe `.menu`, jamais
l'attribut. Aucun des deux ne bouge si l'attribut disparait.

**Les deux issues qu'elle nomme sont donc libres, et le choix est entierement le sien.** Un point de
fait pour l'eclairer, et un seul : sans l'attribut, le nom du reperage de navigation vient du
navigateur ou du lecteur d'ecran, pas d'un texte que le serveur envoie - il sort donc du perimetre
que sa propre formulation dessine. Ce que l'enfant entendrait alors, et s'il entend quelque chose,
reste sa reserve F11 et n'a jamais ete mesure sur ce produit.

**Ce que le geste ne ferme pas, et que je nomme comme dette plutot que de le laisser decouvrir.**
`TexteAccentueDesGabaritsTest` porte un inventaire, `TEXTES_LUS_PAR_L_ENFANT`, dont la Javadoc promet
*"tout texte qu'un gabarit ecrit en clair pour l'enfant doit figurer dans l'inventaire"* - une
promesse universelle, avec le mode de defaillance rouge par defaut qu'`ADR-0039` exige. Mais la meme
Javadoc dit deux lignes plus bas ce qu'elle balaie : *"l'inventaire ne juge que les noeuds de
texte"*. Un attribut n'est pas un noeud de texte. **C'est par la que "Menu principal" a traverse huit
sprints sans qu'aucun test ne le voie, et c'est `nadia-prof` qui l'a trouve en lisant.** Le critere
qu'elle pose - *tout texte que le serveur envoie et qu'un enfant peut recevoir, affiche ou annonce* -
et le perimetre de ce garde-fou ne coincident pas.

Supprimer l'attribut fait disparaitre le symptome sans fermer le canal : le prochain `aria-label`,
le prochain `title`, le prochain `alt` non vide rentrera de la meme facon, en silence.

**Ce que couterait de le fermer, mesure** : la production ne porte aujourd'hui que trois porteurs de
texte annonce, et le compte est fait sur les cinq gabarits - `<title>Recreo</title>` (un noeud de
texte, deja dans l'inventaire), `alt=""` (vide) et l'`aria-label` en cause. Etendre le perimetre de
l'inventaire aux attributs porteurs de texte annonce rougirait donc aujourd'hui sur **exactement une
occurrence : celle que `nadia-prof` a refusee**, et sur aucune autre. L'ensemble de ces attributs est
ferme par la specification HTML et ARIA, jamais par nous - le meme raisonnement que ce fichier tient
deja pour `POSE_SON_TEXTE_AU_RENDU`, *"ensemble ferme par Thymeleaf, jamais par nous"*. Le cout est
une demi-journee et un jeu fautif.

**Avis** : le geste de S12-5 est faisable en l'etat, sans `ADR` et sans risque. L'extension du
perimetre est une seconde chose ; elle ne bloque pas la story, elle merite son `ADR` et je le
redige si `sofia-sm` lui donne une capacite. **Si elle ne lui en donne pas, la dette doit etre
inscrite au rapport de sprint avec son declencheur** - le prochain attribut porteur de texte annonce
ajoute a un gabarit - plutot que d'etre reputee reglee par la suppression.

**Attention a une lecture trop rapide du critere de sortie**, qui n'est pas de moi mais qui me
concerne parce qu'il sera verifie : *"aucune occurrence de 'menu' ni 'principal' sur les deux
canaux"*. La classe CSS `.menu` porte le mot et la feuille maison s'en sert pour masquer l'entree sur
un ecran de jeu ; la retirer casse `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest`. Un nom de classe
n'est pas un texte que l'enfant recoit, et le critere de `nadia-prof` dit bien "texte", pas
"occurrence". Je le signale pour que personne ne renomme la classe en croyant satisfaire la porte.

## S13-6 - Un chemin vers le catalogue : infaisable sans `ADR`, et c'est la story la plus chere du sprint

C'est la seule des cinq qui touche le comportement, et son cout n'est pas ou on le cherche.

### L'etat du jour, verifie

`GET /` avec une partie en cours rend la branche `reprise` de `templates/accueil.html`, c'est-a-dire
la manche seule : **zero bouton de demarrage**. Sans partie en cours, la meme route rend la liste des
jeux. Le seul chemin vers le catalogue est `POST /retour`, qui rend l'ecran de confirmation, dont
`/retour/confirmer` detruit. Il n'existe donc aucun ecran du depot qui montre le catalogue **pendant**
qu'une partie vit - et c'est ce que la story demande de creer.

### Ce que les clauses 1 a 4 imposent a un tel ecran

Elles sont toutes les quatre executables aujourd'hui, clause 4 comprise -
`AucunArretDeTabulationNEstDestructeurTest`, huit tests. Deux consequences immediates et gratuites :

- **la clause 4 mord toute seule** : l'ecran neuf devra declarer un point de focus, faute de quoi
  elle rougit en nommant l'ecran. Rien a ecrire ;
- **`chaqueRouteDuControleurEstExerceeParCeGardeFou` mord toute seule aussi** : une route neuve rend
  le garde-fou rouge tant que son corpus ne l'exerce pas. Le lot ne peut pas oublier d'ajouter son
  ecran au corpus.

C'est la bonne nouvelle, et elle est reelle : la moitie du travail de garde est deja faite.

### Le defaut, mesure

Les trois clauses reposent sur une definition de "destructeur" qui est un **effet mesure** et non un
attribut lu, ce qui est la bonne conception : `estDestructeur` rejoue la requete de l'arret sur une
session fraiche reproduisant l'ecran, puis demande a `EtatDePartieEnSession` si la session **porte
encore une partie en cours**.

Or `POST /parties/{jeuId}` ecrase les trois attributs de session et en installe trois autres :
apres le rejeu, la session porte bien une partie en cours - **une autre**. Le critere rend donc
**faux** sur le geste qui annihile la partie de l'enfant.

Mesure, sur la production mutee et non sur une fixture : j'ai fait rendre a `GET /`, en partie en
cours, la manche **et** la liste des jeux - c'est-a-dire la forme la plus simple que S13-6 puisse
prendre - puis lance le module.

```
SONDE  (masque, hors ordre) btn lien-accueil "Les jeux"
SONDE  A[0] button.btn retour-accueil "Les jeux" -> /retour : NON DESTRUCTEUR
SONDE  A[1] button.btn proposition "2"
SONDE  A[2] button.btn proposition "1"
SONDE  A[3] button.btn demarrer "Un jeu" -> /parties/un-jeu : NON DESTRUCTEUR
SONDE  A[4] button.btn demarrer "Jeu infini" -> /parties/jeu-infini : NON DESTRUCTEUR
SONDE  A[5] button.btn demarrer "Jeu repete" -> /parties/jeu-repete : NON DESTRUCTEUR
SONDE  A[6] button.btn demarrer "Jeu a quatre propositions" -> /parties/jeu-quatre-propositions : NON DESTRUCTEUR
SONDE ---- point de focus declare : enonce / cibles=0
```

`mvn -B -pl adapters/adapter-web -am test` sur cet arbre mute : **226 tests, 0 echec**. La suite
entiere du module reste verte sur un ecran ou quatre tabulations et une Entree effacent la partie de
l'enfant - sept dans le catalogue reel au lieu de quatre dans celui des tests.

**Le troisieme critere d'acceptation de la story est donc exactement le point aveugle du garde-fou.**
*"Quand l'enfant choisit depuis le catalogue de lancer un autre jeu, alors la partie precedente est
abandonnee comme aujourd'hui : ce cas reste un choix explicite de l'enfant, pas un accident."* Le
garde-fou existe pour distinguer le choix de l'accident au clavier ; sur ce geste-la, il ne sait pas
que quelque chose a ete abandonne.

### Ce que ca coute, et ce que ca risque

**Ce que ca coute** : un `ADR` et une clause, dans le meme commit que l'ecran. La forme la moins
couteuse que j'aie trouvee n'est pas de corriger le critere existant mais d'en **ajouter un second,
orthogonal** : aujourd'hui un arret est *destructeur* si la session ne porte plus de partie ; il sera
en outre *remplacant* si elle en porte une **d'un autre jeu**. Deux predicats separes plutot qu'un
predicat elargi, pour la meme raison qu'`ADR-0040` a separe sa clause 3 de la clause 2 : un message
d'echec doit dire lequel des deux gestes a eu lieu. Et surtout, un predicat elargi en un seul
compterait la fin naturelle d'une partie parmi les destructions - le meme jeu, termine par sa
derniere manche - et fabriquerait des faux rouges sur les ecrans de manche existants.

Verifie avant de le proposer : sur l'arbre non mute, ce second predicat ne change **aucun** verdict.
Les arrets des ecrans a partie en cours sont les propositions (`POST /parties/{jeuId}/coups`, meme
jeu), la sortie (`POST /retour`, session intacte) et les deux boutons de la confirmation. Aucun
n'installe une partie d'un autre jeu. La clause peut donc s'ecrire avant l'ecran et rester verte
jusqu'a ce qu'il existe.

**Ce que ca risque, et c'est le vrai prix.** Une fois le second predicat en place, les sept boutons
de demarrage deviennent des arrets remplacants sur cet ecran, et les clauses 1 et 2 cessent d'etre
gratuites : le **premier** arret de l'ordre de tabulation ne doit etre ni destructeur ni remplacant,
et l'arret **qui suit le point de focus** non plus - rebouclage compris. Sur un ecran qui porte sept
arrets remplacants et un seul arret sur, cela laisse une seule famille de formes : le retour vers la
partie en cours doit etre le premier arret du document, le point de focus doit le preceder sans
declarer de requete, et il n'y a pas d'autre agencement. C'est une **derivation, pas une preference**,
et elle contraint la proposition d'`ines-design` avant qu'elle ne soit dessinee. Il faut le lui dire
maintenant plutot qu'apres.

Second risque, plus discret : `ADR-0040` inscrivait comme declencheur de deux dettes nommees
*"si un deuxieme ecran de confirmation apparaissait - une seconde requete destructrice dans
l'application"*. S13-6 n'ajoute pas de requete destructrice mais fait apparaitre sept arrets
remplacants sur un ecran a partie en cours, ce qui est le meme evenement du point de vue de ces deux
dettes. Elles se rouvrent avec cette story : la concordance entre le role declare (`data-role`) et
l'effet mesure, et la clause 3 de M13 de `nadia-prof` (*"jamais la sortie"*), qui n'est encodee par
rien. Je ne demande pas de les payer dans ce lot ; je demande qu'elles soient nommees dans le
rapport plutot que redecouvertes au sprint 15.

**Ce qui n'est pas dans le chiffrage et qui devrait l'etre** : le second critere d'acceptation
demande que le retour rende *"exactement la manche qui etait en cours"*. C'est deja tenu par
`POST /retour/annuler`, qui rend la manche sans faire progresser la session ; le chemin de retour du
catalogue peut reutiliser ce point d'appel tel quel, a condition qu'il ne compte pas d'essai et ne
renouvelle pas le tirage. C'est la seule partie de cette story qui ne coute rien.

**Avis** : infaisable en l'etat. Faisable avec un `ADR` que je redige - l'enonce du second predicat
est ci-dessus - a condition que la contrainte de forme soit transmise a `ines-design` **avant**
qu'elle ne propose, et non apres.

## Reproduction des mesures

Toutes sur JDK 21.0.10, dans un worktree dedie hors de l'arbre du depot, arbre restaure apres chaque
mutation et verifie propre par `git status`.

- **suite complete** : `mvn -B clean verify`, jamais un `-pl` seul - un jar de module perime rend un
  faux rouge, constat de `sofia-sm` ce sprint. Les mutations ci-dessus sont mesurees module par
  module avec `-am`, qui reconstruit les modules amont dans le meme reacteur ;
- **S13-1** : paquet de sonde depose sous le repertoire de contenu de production, `mvn -B -pl
  adapters/adapter-content -am test`, puis meme commande avec un `indice` retire ;
- **S13-6** : `templates/accueil.html` et `PartieController.accueil` mutes pour rendre la liste des
  jeux en partie en cours, `mvn -B -pl adapters/adapter-web -am test` ;
- **S13-4, S13-5** : lecture des onze paquets de production, sans execution.
