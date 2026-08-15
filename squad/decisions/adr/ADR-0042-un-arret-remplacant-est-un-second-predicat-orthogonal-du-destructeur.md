# ADR-0042 - Ajouter un second predicat orthogonal du destructeur, "remplacant", et en tirer la contrainte de forme de l'ecran de S13-6

- **Statut** : Accepte
- **Date** : 2026-08-15
- **Amende le** : 2026-08-15 - **un chiffre faux, aucune conclusion touchee**. Cet `ADR` disait
  "sept jeux" au catalogue reel a trois endroits ; il y en a **onze**. "Sept" etait la taille du
  catalogue en s10 et a ete reporte de sprint en sprint sans etre remesure. Ecart signale par
  `ines-design`. Aucune clause, aucune mutation, aucune alternative de cet `ADR` ne depend de ce
  nombre : les clauses portent sur des positions dans l'ordre du document, jamais sur un effectif,
  et les mesures M1 a M5 sont faites sur des catalogues de test a quatre boutons dont le nombre est
  ecrit dans leur propre tableau. Ce qui etait faux est donc la **taille du defaut rapporte** - onze
  boutons remplacants annonces comme sept - pas ce qui en est conclu. Corrige avec sa methode de
  comptage. Le troisieme emploi, "l'ordre visuel des sept jeux" dans la liste de ce que la contrainte
  laisse libre, perd son chiffre plutot que d'en recevoir un juste : il aurait pourri au jeu suivant.
- **Auteur** : theo-archi
- **Sprint** : s13 (avis de faisabilite S13-1 a S13-6, section S13-6 : "infaisable sans `ADR`")
- **Regle executable** : **due par le lot de S13-6**, dans le meme commit que l'ecran, sur le modele
  d'`ADR-0038`. L'enonce, le fichier, la population et les deux mesures de morsure sont ci-dessous ;
  il n'y a rien a inventer. Fichier :
  `adapters/adapter-web/src/test/java/com/recreo/adapter/web/AucunArretDeTabulationNEstDestructeurTest.java`.
  **Pourquoi je ne l'ecris pas ici** : l'ecran n'existe pas, le lot appartient a un developpeur, et
  `sofia-sm` a fixe ce partage. Ce que je dois au depot, et qui est ci-dessous, est un enonce assez
  precis pour que personne n'ait a le redecouvrir - plus les chiffres qui disent ce qu'il attrape et
  ce qu'il laisse passer.

## Contexte

S13-6 demande un chemin vers le catalogue **pendant** qu'une partie vit. Aucun ecran du depot ne fait
cela aujourd'hui : `GET /` avec une partie en cours rend la manche seule, zero bouton de demarrage.

Les quatre clauses d'`ADR-0038` et d'`ADR-0040` sont deja executables et deux d'entre elles mordent
toutes seules sur un ecran neuf (la clause 4 exige un point de focus declare,
`chaqueRouteDuControleurEstExerceeParCeGardeFou` exige que toute route neuve entre au corpus). La
moitie du travail de garde est faite.

**Ce qui manque est le predicat.** "Destructeur" est un effet mesure et non un attribut lu : la
requete de l'arret est rejouee sur une session fraiche reproduisant l'ecran, puis
`EtatDePartieEnSession` dit si la session **porte encore une partie en cours**. Or
`POST /parties/{jeuId}` ecrase les trois attributs de session et en installe trois autres : apres le
rejeu, la session porte bien une partie en cours - **une autre**. Le critere rend donc faux sur le
geste qui annihile la partie de l'enfant.

Mesure du 2026-08-15, sur la production mutee et non sur une fixture - `GET /` rend, en partie en
cours, la manche **et** la liste des jeux, c'est-a-dire la forme la plus simple que S13-6 puisse
prendre ; `mvn -B -pl adapters/adapter-web -am test`, JDK 21.0.10 :

```
A[0] button.btn retour-accueil "Les jeux"  -> POST /retour                    destructeur=false
A[1] button.btn proposition "2"            -> POST /parties/un-jeu/coups      destructeur=false
A[2] button.btn proposition "1"            -> POST /parties/un-jeu/coups      destructeur=false
A[3] button.btn demarrer "Un jeu"          -> POST /parties/un-jeu            destructeur=false
A[4] button.btn demarrer "Jeu infini"      -> POST /parties/jeu-infini        destructeur=false
A[5] button.btn demarrer "Jeu repete"      -> POST /parties/jeu-repete        destructeur=false
A[6] button.btn demarrer "Jeu a quatre..." -> POST /parties/jeu-quatre-...    destructeur=false
```

Quatre arrets de l'ordre de tabulation - **onze** dans le catalogue reel - effacent la partie de
l'enfant et le predicat repond **non destructeur** sur les onze. *Chiffre corrige le 2026-08-15 : il
disait "sept", qui etait la taille du catalogue en s10 et qui a ete reporte de sprint en sprint.
Methode : entrees `- id:` sous la cle `jeux:` de
`adapters/adapter-content/src/main/resources/catalogue.yaml`, comptees sur l'arbre du commit cite ;
7 en `a6f2275` (s10), 8 en `4f0290d`, 9 en `d9e390c` (s12), 11 depuis `ab1d11e` (s13), donc 11 le
jour ou cet `ADR` a ete ecrit. Ecart signale par `ines-design`.*

## Decision

**1. Un second predicat, orthogonal, et non un critere de destruction elargi.**

> **Un arret est *remplacant* si, sa requete rejouee sur une session fraiche reproduisant l'etat de
> depart de l'ecran juge, la session porte une partie en cours qui n'est pas celle qu'elle portait :
> soit une partie d'un autre jeu, soit une partie du meme jeu dont le compte de manches resolues a
> recule.**

Deux etats de session lus par `EtatDePartieEnSession`, avant et apres, et deux comparaisons :
`jeuId`, puis `score().tentatives()`. Rien d'autre - ni URL, ni `data-role`, ni libelle. Les deux
predicats sont **mutuellement exclusifs par construction** : destructeur exige qu'aucune partie ne
soit en cours apres le rejeu, remplacant qu'il y en ait une.

**Les clauses 1, 2 et 3 exigent desormais que l'arret qu'elles jugent ne soit ni destructeur ni
remplacant.** Les trois, pas seulement les deux premieres : la clause 3 juge le point de focus
lui-meme - Entree sans aucune Tabulation - et il n'y a aucune raison qu'un remplacement y soit permis
alors qu'une destruction n'y l'est pas. La clause 4 (un point de focus existe) ne lit aucun predicat
et ne bouge pas.

**2. Pourquoi orthogonal, et non un critere elargi. Trois formes d'elargissement ont ete mesurees,
pas raisonnees.**

- **Elargissement naif - "la partie n'est plus la meme"**, c'est-a-dire l'objet de session remplace.
  Mesure sur l'arbre **non mute** : **2 tests en echec sur 11**, et les ecarts nomment **sept ecrans
  legitimes**. La clause 2 rougit sur "manche 1", "reprise, une partie en cours", "manche apres une
  reponse juste", "manche apres une reponse fausse" et "annulation de l'abandon" ; la clause 3 rougit
  sur "manche apres une reponse fausse" et "manche en revelation". **Le chemin legitime que cet
  elargissement interdit est le jeu lui-meme** : presser une proposition remplace l'objet de session,
  et M13 place justement le point de focus sur une proposition apres un echec. Un critere qui rougit
  quand l'enfant joue n'est pas un critere.
- **Elargissement par la progression seule** - "la partie n'a pas avance". Mesure : **faux positif
  sur le second appui de la meme mauvaise proposition** ("manche apres une reponse fausse", A[1], qui
  est de surcroit le point de focus declare). La mecanique compte alors un essai de plus, mais
  l'ensemble `propositionsEssayees` ne grandit pas, le seul compteur d'essais expose etant un
  ensemble. Le predicat retenu n'utilise donc **pas** les essais, seulement les manches resolues.
- **Elargissement par la fusion des deux predicats en un seul** - "la partie de l'enfant n'a pas
  survecu telle quelle". Il a **exactement la meme extension** que la reunion des deux, et je le dis
  parce que **c'est ce que mon avis de faisabilite affirmait de faux** : j'y ecrivais qu'un predicat
  elargi "compterait la fin naturelle d'une partie parmi les destructions et fabriquerait des faux
  rouges sur les ecrans de manche existants". **C'est deja le cas du predicat actuel**, et c'est
  mesure : sur "manche apres une reponse juste", l'arret A[2] mene la partie a `TERMINEE` et
  `estDestructeur` rend **vrai** sur lui - une reponse juste sur la derniere manche. Le garde-fou
  n'est vert que parce que cet arret n'occupe ni la position A[0] ni celle qui suit le point de focus.
  L'argument que j'avais avance ne tenait donc pas ; celui qui reste tient, et il est le meme
  qu'`ADR-0040` : **deux clauses separees rendent un message qui dit lequel des deux gestes a eu
  lieu**, "detruit" et "remplace" n'appelant pas le meme remede de conception.

**3. Ce que ce predicat attrape, ce qu'il laisse passer, et les deux chiffres qui le disent.**

Mesure sur deux formes possibles de l'ecran de S13-6, predicat cable dans les clauses 1 a 3, meme
commande et meme JDK :

| forme mesuree | resultat |
|---|---|
| **catalogue apres la manche** (`A[0]` est le retour vers la partie) | **11 tests, 0 echec** |
| **catalogue avant la manche** (`A[0]` est un bouton de demarrage) | **1 echec sur 11**, clause 1 : *"sur \"reprise, une manche deja resolue\" (GET /), le premier arret de tabulation `<button class="btn demarrer">` \"Un jeu\" detruit ou remplace la partie en cours"* |

Deux enseignements, et le second est un trou.

- Le predicat **ne rend pas la premiere forme sure par magie** : les clauses jugent deux positions,
  `A[0]` et l'arret qui suit le point de focus, jamais l'ecran entier. Sur la premiere forme, les
  quatre boutons de demarrage sont bien remplacants et restent atteignables en quatre tabulations -
  **et c'est voulu** : `ADR-0038` garde contre l'accident a zero ou une tabulation du point de
  depart, pas contre un choix delibere. Le troisieme critere d'acceptation de S13-6 dit exactement
  cela : choisir un autre jeu depuis le catalogue est un choix de l'enfant. Mon avis de faisabilite
  melangeait les deux ; ici ils sont separes.
- Le predicat **manque le redemarrage du jeu en cours tant qu'aucune manche n'est resolue**. Sur la
  seconde forme, l'ecran rouge est celui ou l'enfant a deja resolu une manche ; l'ecran ou il n'en a
  resolu aucune reste **vert**, alors que `A[0]` y est le bouton qui redemarre sa propre partie. La
  cause n'est pas un oubli : a progression nulle, l'etat de session apres redemarrage est
  indiscernable de l'etat avant, aux deux accesseurs pres. Ce que l'enfant perd dans ce cas precis
  est la question affichee et rien d'autre.

**4. Le predicat que mon avis de faisabilite annoncait etait insuffisant, et c'est mesure.** J'y
ecrivais *"il sera en outre remplacant si elle en porte une d'un autre jeu"* - la seule comparaison
de `jeuId`. Mesure sur la seconde forme ci-dessus, ce predicat-la : **11 tests, 0 echec**. Il est
**entierement vert sur la forme la plus dangereuse**, y compris sur l'ecran ou l'enfant avait deja
resolu une manche, parce que le premier bouton d'un catalogue est celui du jeu en cours. Le
catalogue de test place `un-jeu` en tete et le scenario joue `un-jeu` ; un catalogue reel ordonne de
la meme facon pour tous ses ecrans fera exactement pareil. J'aurais livre une clause decorative sur
la seule position qui compte.

**5. Ce que ce lot rouvre, et que je ne demande pas de payer ici.** `ADR-0040` inscrivait comme
declencheur de deux dettes *"si un deuxieme ecran de confirmation apparaissait - une seconde requete
destructrice dans l'application"*. S13-6 n'ajoute pas de requete destructrice mais fait apparaitre
des arrets **remplacants** sur un ecran a partie en cours, ce qui est le meme evenement du point de
vue de ces deux dettes : la concordance entre le role declare (`data-role`) et l'effet mesure, et la
clause 3 de M13 de `nadia-prof` (*"jamais la sortie"*), qui n'est encodee par rien. Elles doivent
etre nommees au rapport de sprint, pas redecouvertes au sprint 15.

## La contrainte de forme, ecrite pour `ines-design`

C'est une **derivation**, pas une preference : elle dit ce qui reste possible, jamais ce qu'il faut
faire. Le choix a l'interieur de ce qui reste possible est entierement le sien, avec `nadia-prof`
pour ce que l'enfant doit comprendre.

Soit un ecran qui montre le catalogue pendant qu'une partie vit. Il porte donc, dans l'ordre du
document, un ensemble d'arrets de tabulation dont **autant sont remplacants que le catalogue porte de
boutons de demarrage** - onze au 2026-08-15, voir la methode de comptage plus haut - et dont au moins
un est sur, le retour vers la partie en cours. Aucune clause ci-dessous ne depend de ce nombre : elles
portent sur des positions dans l'ordre du document, pas sur un effectif. Alors :

1. **Le premier arret de tabulation du document ne peut etre aucun bouton de demarrage.** Clause 1.
   Ce qui peut l'occuper : le retour vers la partie en cours, ou tout autre arret dont la requete
   laisse la partie intacte.
2. **L'arret qui suit le point de focus declare ne peut etre aucun bouton de demarrage** - rebouclage
   compris, c'est-a-dire y compris quand le point de focus est apres le dernier arret. Clause 2.
3. **Le point de focus declare ne peut pas etre un bouton de demarrage** (clause 3) **et l'ecran doit
   en declarer un** (clause 4).
4. **Le point de focus ne peut pas etre le bouton de retour lui-meme.** Ce n'est pas une clause de
   plus, c'est la clause 2 appliquee : `recreo.js` pose `tabindex="-1"` sur le point de focus avant
   de le focaliser, donc focaliser le bouton de retour le retire de l'ordre vivant et fait du premier
   bouton de demarrage l'arret suivant. C'est la mecanique exacte qu'`ADR-0040` a mesuree sur l'ecran
   de confirmation.

**Ce qui reste possible, et il n'y a pas d'autre famille** : un point de focus qui **ne declare
aucune requete** - un titre, une phrase - place avant le retour vers la partie en cours dans l'ordre
du document, et **le retour vers la partie en cours en premier arret**, les boutons de demarrage
apres lui. Le mecanisme existe deja et ne demande aucune ligne de JavaScript : `data-focus` sur la
racine du fragment et `data-focus-cible="true"` sur l'element vise, comme `fragments/manche.html` le
fait avec son enonce.

**Ce que la contrainte ne dit pas, et qui reste libre** : l'apparence, la place a l'ecran, la taille,
la couleur, le libelle, l'ordre visuel des jeux du catalogue, la presence ou non d'une illustration.
**L'ordre du document et l'ordre visuel ne sont pas le meme ordre** - une mise en page peut placer le
catalogue au-dessus et le retour au-dessous tout en gardant le retour premier dans le document. La
contrainte porte sur l'ordre du document, jamais sur ce que l'oeil parcourt.

**Un point ouvert qui n'est pas de moi** : le catalogue montre-t-il le jeu en cours ? Si oui, un
enfant peut redemarrer sa propre partie, et le point 3 ci-dessus dit que le garde-fou ne l'attrapera
pas tant qu'aucune manche n'est resolue. La question est produit et pedagogique - `camille-po` et
`nadia-prof`.

## Regle executable - obligations du lot de S13-6

```java
private boolean estRemplacant(Element arret, Scenario scenario) throws Exception {
    // meme rejeu que estDestructeur, mais l'etat de session est lu AVANT et APRES :
    // remplacant si les deux portent une partie en cours et que celle d'apres n'est pas
    // celle d'avant - autre jeuId, ou score().tentatives() en recul.
}
```

Les clauses 1, 2 et 3 appellent `estDestructeur(...) || estRemplacant(...)`, et leur message dit
lequel des deux.

**Ce n'est pas une regle ArchUnit** - ArchUnit juge des classes et leurs dependances ; la propriete
porte sur un document rendu et sur l'effet d'une requete sur une session. Le mecanisme automatique
est un test du module `adapter-web`, execute par `mvn verify`. **Le compte des regles ArchUnit reste
a 28**, methode inchangee (declarations `ArchRule <nom> =` sous `src/test/java` des modules du pom
agregateur).

**Obligations d'`ADR-0013`, les trois, et la troisieme est une obligation du futur lot :**

1. **perimetre derive** - inchange : le corpus vient de vraies requetes sur de vraies sessions, les
   routes de `RequestMappingHandlerMapping`, les arrets du document assemble et du masquage servi. Le
   caractere remplacant vient de la requete rejouee, jamais d'un attribut ni d'une liste ;
2. **derivation totale** - inchangee : une requete declaree illisible, un `tabindex` illisible, zero
   arret sur un ecran a partie en cours restent des echecs. Le predicat neuf n'ajoute aucun cas
   ignore : tout arret juge recoit l'un des trois verdicts (sur, destructeur, remplacant) ;
3. **mise a l'epreuve** - `LesGardeFousSontMisALEpreuveTest` n'exigera **rien de neuf** du lot : la
   classe possede deja un repertoire de contre-exemple non vide et un `assertThatThrownBy`, donc le
   recensement restera vert meme si la clause neuve n'est jamais eprouvee. **C'est un vert facile, et
   il est nomme ici pour que la revue le refuse** : le lot doit une preuve de morsure a la clause
   neuve, sur du materiau reel et non sur une fixture de sa main (lecon de s05). La forme la moins
   couteuse est celle que j'ai mesuree - l'ecran livre, ses boutons de demarrage remontes avant le
   retour dans l'ordre du document - parce qu'elle mute un objet existant plutot que d'en inventer
   un. **Le corpus devra en outre porter un ecran a partie en cours ou au moins une manche est
   resolue** ; sans lui, le trou du point 3 rend la clause verte sur le redemarrage du jeu en cours,
   et la preuve de morsure elle-meme ne tomberait pas.

**Morsure mesuree d'avance, methode comprise.** JDK 21.0.10, worktree dedie hors de l'arbre du depot,
`mvn -B -pl adapters/adapter-web -am test`, arbre restaure apres chaque mutation et verifie propre :

| # | Arbre | Predicat | Resultat |
|---|---|---|---|
| M0 | non mute | destructeur seul (etat du jour) | **10 tests, 0 echec**, 3,2 s |
| M1 | non mute | destructeur + remplacant | **11 tests, 0 echec** - aucun verdict ne change, la clause peut donc s'ecrire avant l'ecran |
| M2 | catalogue apres la manche | destructeur + remplacant | 11 tests, 0 echec |
| M3 | catalogue avant la manche | destructeur + remplacant | **1 echec sur 11**, clause 1, nommant l'ecran et le bouton |
| M4 | catalogue avant la manche | destructeur + remplacant par le seul `jeuId` | **11 tests, 0 echec** - le predicat annonce dans mon avis ne mord pas |
| M5 | non mute | destructeur + "la partie n'est plus le meme objet" | **2 echecs sur 11**, sept ecrans legitimes nommes |

M1 a M5 comptent 11 tests et non 10 : les mesures ont ete prises avec une sonde de predicats
ajoutee a la classe, et avec un ecran de plus au corpus - "reprise, une manche deja resolue" - tous
deux retires ensuite. C'est ce qui explique aussi
pourquoi M3 nomme cet ecran-la et pas l'autre.

## Ce que ce predicat ne prouvera pas, nommement

`ADR-0038` en portait huit ; l'une d'elles s'est revelee **franchement fausse** au sprint suivant. Une
liste de non-preuves est un engagement a reverifier, pas une precaution rhetorique. Celles-ci seront
relues au lot de S13-6, et chacune porte de quoi la refuter.

1. **Que la partie survit a quatre tabulations.** Les clauses jugent deux positions, jamais l'ecran
   entier ; les boutons de demarrage restent atteignables et remplacants, par construction et par
   decision produit. Un vert ici ne dit rien de ce que coute un parcours long. **Ce qui la
   refuterait** : `nadia-prof` mesurant au clavier qu'un enfant atteint le catalogue sans l'avoir
   voulu.
2. **Que le redemarrage du jeu en cours est vu.** Il ne l'est pas tant qu'aucune manche n'est
   resolue - point 3 de la decision, mesure. **Ce qui la refuterait** : un accesseur de progression
   plus fin sur `BanditManchotSession`, que je refuse d'ajouter a la production pour un test tant
   qu'aucun autre usage ne le demande.
3. **Que "remplacant" epuise les facons de perdre sa partie.** Le predicat compare deux etats de
   session. Une requete qui laisserait la meme partie en cours tout en la vidant de son sens - meme
   jeu, meme compte de manches, mais tirage remis a zero - serait **verte**. Aucune route
   d'aujourd'hui ne fait cela.
4. **Que l'egalite d'etat vaut identite de partie.** Le predicat lit `jeuId` et le compte de manches
   resolues, pas l'identite de l'objet de session. C'est ce qui evite le faux positif du second appui
   sur la meme mauvaise proposition (point 2 de la decision), et c'est le prix de la non-preuve 2 :
   les deux vont ensemble, on ne peut pas fermer l'une sans rouvrir l'autre avec les accesseurs
   d'aujourd'hui.
5. **Que la fin naturelle d'une partie est distinguee d'une destruction.** Elle ne l'est pas, et ce
   n'est pas neuf : `estDestructeur` rend deja **vrai** sur la proposition juste qui termine la
   partie (mesure ci-dessus, ecran "manche apres une reponse juste"). Le garde-fou n'est vert que
   parce que cet arret n'occupe aucune des deux positions jugees. **Dette nommee, avec son
   declencheur** : le jour ou une proposition occupera `A[0]` ou suivra le point de focus sur une
   derniere manche, la clause 1 ou la clause 2 rougira sur un geste parfaitement legitime, et il
   faudra alors distinguer "terminee parce que jouee" de "detruite". Je ne le fais pas maintenant :
   aucun ecran n'est dans ce cas, et la distinction demande de lire le statut de session, donc une
   piece mobile de plus.
6. **Que le focus va la ou le serveur le declare.** Inchange depuis `ADR-0038` non-preuve 1 et
   `ADR-0040` non-preuve 1 : le deplacement est fait par `recreo.js`, teste sur un faux DOM.
7. **Que l'ordre de tabulation calcule est celui d'un vrai navigateur.** Inchange depuis `ADR-0038`
   non-preuve 2. La contrainte de forme ci-dessus est derivee d'un document, pas pressee au clavier.
8. **Que l'identite d'objet en session se comporte ainsi en production.** Les mesures utilisent
   `MockHttpSession`, ou l'objet stocke est rendu tel quel. Le predicat retenu ne s'appuie pas sur
   l'identite d'objet - c'est une raison de plus de l'avoir ecarte - mais la mesure M5, qui la
   nomme, n'est valable que sous cette reserve.
9. **Que l'enfant comprend ce qui se passe.** Qu'un catalogue affiche pendant une partie ne soit pas
   deroutant, qu'un retour soit trouvable, qu'un redemarrage accidentel soit grave ou benin :
   `nadia-prof`, et aucun garde-fou de ce depot n'y touche.

## Consequences

- **Le lot de S13-6 recoit sa contrainte de forme avant de dessiner**, ce qui est l'ordre que ce
  projet s'est donne apres avoir jete un lot. `ines-design` n'a pas a deviner, et n'a pas a decouvrir
  en revue qu'un agencement est refuse par la CI.
- **Une famille d'agencements est desormais interdite par la CI** : catalogue en tete du document, sur
  un ecran a partie en cours. Le message nomme l'ecran et le bouton.
- **Un mauvais remede evident l'est aussi** : declarer le point de focus sur le bouton de retour.
  Meme mecanique qu'`ADR-0040`, meme cause dans `recreo.js`.
- **Cout d'execution** : un rejeu de requete de plus par arret juge, sur les trois clauses. Sur le
  corpus d'aujourd'hui, le garde-fou passe d'environ 3,8 s a environ 3,9 s. C'est le prix du critere
  par effet mesure, deja assume par `ADR-0038`.
- **Mauvaise consequence assumee** : le predicat a un trou nomme (non-preuve 2) et il le gardera tant
  que la progression exposee par la mecanique restera ce qu'elle est. Une clause qui a un trou nomme
  vaut mieux qu'une clause qui n'en a pas parce qu'elle n'a pas ete mesuree - c'est exactement
  l'ecart entre le predicat de mon avis et celui-ci.
- **Aucune ligne de production ne change dans ce lot-ci** : cet `ADR` ne livre que du texte.

## Alternatives considerees

- **Elargir `estDestructeur` au lieu d'ajouter un predicat.** Ecarte sur mesures, trois formes
  essayees, point 2 de la decision. La forme naive rougit sur le jeu lui-meme ; la forme par
  progression rougit sur le second appui de la meme mauvaise reponse ; la forme fusionnee a la meme
  extension que les deux predicats separes mais rend un message qui ne dit pas quel geste a eu lieu.
- **Lire l'intention plutot que l'effet** - reconnaitre `POST /parties/{jeuId}` comme un demarrage.
  Ecarte, et c'est la faute qu'`ADR-0038` a deja payee : un critere qu'on desamorce en changeant une
  URL, un attribut ou un libelle n'est pas un critere. L'effet mesure survit au renommage.
- **Interdire tout arret remplacant sur un ecran a partie en cours.** Ecarte : cela interdirait
  l'ecran que la story demande. Le troisieme critere d'acceptation de S13-6 dit que choisir un autre
  jeu depuis le catalogue reste un choix explicite de l'enfant ; c'est une decision produit, et elle
  n'est pas la mienne a renverser.
- **Cacher le jeu en cours du catalogue pour supprimer le cas du redemarrage.** Ecarte : c'est une
  decision de produit, remontee a `camille-po` et `nadia-prof`, pas un remede d'architecture.
- **Ajouter un accesseur de progression a `BanditManchotSession`** pour fermer le trou de la
  non-preuve 2. Ecarte pour ce lot : modifier la production pour un seul test, sur un usage qu'aucun
  autre appelant ne demande, est l'abstraction anticipee que ce depot s'interdit. Si un second usage
  apparait, la question se rouvre.
- **Ecrire la clause tout de suite, avant l'ecran.** Techniquement possible - M1 mesure qu'elle
  resterait verte - mais ecarte : le lot appartient a un developpeur, `sofia-sm` a fixe ce partage, et
  une clause verte a vide livree sans son objet ni sa preuve de morsure est exactement ce que
  l'obligation 3 d'`ADR-0013` interdit d'appeler un garde-fou.

## Ce qui me ferait changer d'avis

- **Si le catalogue de S13-6 ne montrait pas le jeu en cours**, la non-preuve 2 perdrait son objet et
  le predicat pourrait se reduire a la comparaison de `jeuId` - celle de mon avis. Je ne parie pas
  la-dessus : c'est une decision produit qui peut changer apres coup, et un predicat qui depend d'un
  choix de contenu est fragile.
- **Si `recreo.js` cessait de poser `tabindex="-1"` sur le point de focus**, le point 4 de la
  contrainte de forme tomberait et le bouton de retour pourrait porter le focus. Le reste tient.
- **Si un deuxieme predicat d'effet apparaissait** - une troisieme facon pour une requete de nuire a
  la partie - il faudrait cesser d'empiler les predicats dans les trois clauses et nommer une
  propriete unique, "cet arret laisse la partie de l'enfant telle qu'elle etait", dont destructeur et
  remplacant seraient deux cas. Deux, cela ne le justifie pas encore.
