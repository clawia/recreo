# ADR-0034 - Un corpus d'ecrans exerce les deux verdicts : un perimetre derive par route ne dit rien des etats

- **Statut** : Accepte
- **Date** : 2026-08-12
- **Auteur** : theo-archi
- **Sprint** : s10 (avis de faisabilite sur la disparition de l'ecran intermediaire)
- **Ne remplace rien.** `ADR-0020` et `ADR-0027` restent **Accepte** : leurs clauses sont justes, c'est
  leur **corpus** qui etait incomplet, et le present ADR le corrige sans toucher a une seule de leurs
  decisions.

## Contexte

L'avis de faisabilite du sprint 10 demandait d'eprouver, par mutation reelle, ce que couterait la
disparition de l'ecran intermediaire. La mutation a d'abord montre autre chose, et ce quelque chose
existe **aujourd'hui**, sans attendre aucun arbitrage.

**Mesure, methode dans le meme paragraphe.** Arbre `b15bda1`, `JAVA_HOME` sur un JDK 21.0.10,
`mvn -B verify` : 371 tests verts avant toute modification. Mutation appliquee a la production, puis
retiree : branche "faux, la manche reste" dans `BanditManchotSession.jouer()`, routage du cas dans
`PartieController.jouer()`, et un `<p class="message-reessaie" th:if="...">` ajoute **a l'interieur**
de `<form class="propositions">` de `fragments/manche.html`, c'est-a-dire exactement la faute que
`LEcranDeJeuDeclareCommentIlEstArriveTest.aucunTexteAutreQueLesPropositionsNEntreDansLeConteneurDePropositions`
existe pour refuser - un texte que l'enfant lit dans le conteneur des colonnes, la ou `nadia-prof`
refuse nommement tout mot leurre.

**Resultat mesure : les six tests de cette classe sont restes verts.** Le seul rouge du module sur ce
point venait de `HabillageDesEcransTest` (classe orpheline, aucune regle de feuille), pour une autre
raison que celle-la.

**La cause n'est pas une clause trop faible, c'est un corpus aveugle.** Les deux classes qui jugent
des ecrans assembles derivent leur perimetre **par route** (`RequestMappingHandlerMapping`), et
`POST /parties/{jeuId}/coups` y figurait deja : la route etait exercee, l'obligation 1 d'`ADR-0013`
satisfaite, et le garde-fou vert. Mais cette route rend **deux etats** - retour juste, retour faux - et
le corpus n'en jouait qu'un. Verification a `b15bda1` : les deux corpus n'envoient que
`propositionChoisie=1` et `=2`, qui sont les **bonnes** reponses des deux items du jeu de test
(`PartieControllerTest.JeuDeTestConfiguration`). **Aucun ecran produit par une reponse fausse n'est
jamais passe devant les deux clauses d'`ADR-0020` ni devant les cinq d'`ADR-0027`.** La moitie du jeu
- celle ou l'enfant se trompe, donc celle ou il a le plus besoin que l'ecran soit juste - n'etait
gardee par aucun automate de ces deux classes.

C'est la forme de defaut que ce depot poursuit depuis `ADR-0013`, avec une variante que je n'avais pas
nommee : **un perimetre derive de la bonne source peut rester aveugle a une dimension entiere.** La
route est derivee ; l'etat ne l'etait pas, et rien ne le disait.

## Decision

### 1. Tout corpus d'ecrans exerce chaque couple de verdict et de suite, et le prouve

**Amendement du 2026-08-12, le meme jour : cette clause disait "les deux verdicts" et c'etait deja
insuffisant.** Je l'ai constate en chiffrant la disparition de l'ecran intermediaire, sur ma propre
clause ecrite le matin.

Le verdict n'avait un ecran a lui que parce que l'ecran de retour existait. Des que la bonne reponse
charge directement la question suivante, l'ecran d'une reponse fausse est un **ecran de manche** : il ne
porte **ni** l'un **ni** l'autre des deux marqueurs. La clause d'origine serait restee **verte**,
satisfaite par les seules fins de partie - donc sur une population qui n'a plus rien a voir avec ce
qu'elle pretend garder. C'est le defaut que cet `ADR` ferme, reproduit dans l'`ADR` qui le ferme, et
c'est la quatrieme fois de ce sprint que je le rencontre.

**La regle corrigee.** Toute classe de test qui construit un corpus d'ecrans **exerce chaque couple
(verdict, statut de session)**, et porte une clause qui echoue quand l'un manque. Le perimetre est le
**produit** de deux ensembles totaux par construction : le verdict est un booleen, `SessionStatus` est
une enumeration. Quatre couples, aucune exemption possible.

**Ce que le produit apporte et que le verdict seul n'apportait pas** : il distingue "faux, la partie
continue" de "faux, la partie est finie". Ce sont deux ecrans differents aujourd'hui, et ils resteront
deux ecrans differents apres le changement d'enchainement - c'est meme la seule paire qui survit
intacte. Une clause portee sur le verdict seul confond les deux ; portee sur le couple, elle ne peut
plus.

**Le nombre de statuts est epingle** a 2, avec son motif : ce garde-fou ne sait traduire que deux
statuts en ecran, et un troisieme se confondrait **en silence** avec la partie qui continue. Le message
demande de lui apprendre le nouvel ecran, jamais d'affaiblir la clause.

### 2. Les marqueurs sont lus dans le gabarit, jamais ecrits dans le test

Le couple de classes qui distingue un verdict juste d'un verdict faux est lu dans
`fragments/retour.html`, sur l'attribut qui choisit entre deux litteraux selon `${juste}` (`ADR-0015` :
deriver le perimetre de la source qui gouverne le rendu). Deux noms de classe recopies dans un test
auraient survecu a leur propre disparition du gabarit ; ici, ils n'y survivent pas.

**Amendement du 2026-08-12** : le marqueur de fin de partie se lit de la meme facon, sur la classe de
l'element que la negation de `${enSuite}` rend. Meme motif, meme mode de defaillance : si le gabarit
cesse d'exprimer la fin de partie ainsi, la lecture echoue bruyamment au lieu de chercher une classe
qui n'existe plus.

Corollaire, et c'est ce que le jeu fautif prouve : si le gabarit cesse d'exprimer le verdict par une
paire de litteraux - par exemple parce que les deux noms de classe demenagent dans le modele - la
lecture **echoue bruyamment** au lieu de rendre une liste vide. Une liste vide aurait rendu la clause
du point 1 verte sans rien verifier, ce qui est le defaut que cet ADR corrige, reproduit dans l'outil
qui le corrige.

### 3. Une lecture, deux lecteurs

`LEcranDeJeuDeclareCommentIlEstArriveTest` **appelle** la lecture de
`ToutEcranEchangeEstUnDocumentJouableTest` au lieu de la recopier. Meme raison qu'`ADR-0033` point 3 :
deux lectures libres de diverger laisseraient un verdict sortir du corpus d'ici et de celui de la-bas,
donc controle par ni l'un ni l'autre.

### 4. Ce que cet ADR ne decide pas

Il ne decide **rien** de l'ecran intermediaire, ni de la place de la revelation de la bonne reponse, ni
de celle de l'explication, ni du sort d'une proposition deja essayee. Ces quatre points sont des
arbitrages de produit et de pedagogie (`camille-po`, `nadia-prof`) ; l'avis de faisabilite les chiffre
sans les trancher. Cet ADR est **anterieur et independant** de leur arbitrage : le corpus etait aveugle
avant que la question ne se pose, et il le resterait si la question etait refermee.

## Regle executable

Trois clauses neuves, deux fichiers touches, aucune production modifiee.

**Fichiers** :

- `adapters/adapter-web/src/test/java/com/recreo/adapter/web/ToutEcranEchangeEstUnDocumentJouableTest.java`
  - `marqueursDeVerdict(Path)` : la lecture derivee du gabarit, qui exige exactement deux litteraux ;
  - `leCorpusExerceLesDeuxVerdicts` : chaque marqueur est porte par au moins un ecran du corpus ;
  - `unGabaritQuiNExprimePlusLeVerdictParUnePaireDeMarqueursFaitEchouerLaLecture` : la mise a
    l'epreuve de la lecture ;
  - le corpus gagne l'ecran "retour faux, la partie continue".
- `adapters/adapter-web/src/test/java/com/recreo/adapter/web/LEcranDeJeuDeclareCommentIlEstArriveTest.java`
  - `leCorpusExerceLesDeuxVerdicts`, appuye sur la meme lecture ;
  - le corpus gagne la reponse "retour faux, la partie continue".
- Jeu fautif :
  `adapters/adapter-web/src/test/resources/contreexemple/ToutEcranEchangeEstUnDocumentJouableTest/verdict-sans-paire-de-marqueurs/templates/fragments/retour.html`

**Pourquoi l'ecran faux vit sur sa propre session.** Le jeu de test `un-jeu` a deux items : sa deuxieme
manche est aussi sa derniere, donc il ne peut pas porter a la fois un retour faux et un retour juste qui
laissent la partie en cours. Le corpus joue donc la reponse fausse sur `jeu-repete` (meme item redonne,
dix manches), seule entree du catalogue de test ou une reponse fausse laisse la partie en cours. Ce
n'est pas un detail d'ecriture : sans cette session separee, la clause aurait exige un ecran que le
catalogue de test ne sait pas produire.

**Pourquoi ce n'est pas une `ArchRule`.** Ce qui est juge est un corpus de sorties HTTP, pas une
dependance entre types. ArchUnit lit du bytecode ; il ne sait pas si un serveur a rendu un ecran. Le
compte de regles ArchUnit du depot reste donc **28**, methode inchangee (voir `ADR-0031`).

### Preuves de morsure, toutes mesurees sur la sortie reelle du serveur

`JAVA_HOME` sur un JDK 21.0.10. Chaque mutation de production a ete retiree apres mesure.

| Objet mis a l'epreuve | Attendu | Mesure |
|---|---|---|
| **Le defaut, avant** : `<p class="explication" th:unless="${juste}"></p>` ajoute a `fragments/retour.html`, soit un paragraphe vide sur la seule branche fausse - la forme exacte du defaut d'`ADR-0020` clause 2 | vert, faute de corpus | **13 tests, 0 echec.** Le defaut traverse la CI sans etre vu |
| **Le meme defaut, apres** : corpus etendu, production mutee a l'identique | rouge, sur l'ecran nomme | **1 echec sur 15**, `aucunEcranNeRendUnParagrapheVide`, message : "sur l'ecran \"retour faux, la partie continue\" (POST /parties/{jeuId}/coups), un paragraphe est rendu sans texte" |
| **Jeu fautif** : `retour.html` dont `th:classappend` lit deux variables du modele au lieu de deux litteraux | la lecture echoue, elle ne rend pas une liste vide | rouge, "les deux marqueurs du verdict se lisent dans..." |
| **Le corpus lui-meme** : `mvn -B verify` complet | vert | **374 tests, 0 echec** (371 + 3) |
| **Amendement, morsure de la clause elargie** : corpus prive de l'ecran "fin de partie sur une reponse fausse", clause portee sur le couple | rouge, en nommant le couple manquant | **1 echec sur 9**, "aucun ecran de ce corpus ne joint \"est-faux\" a un statut TERMINEE". La clause d'origine, elle, etait **verte** sur ce meme corpus : ce couple n'avait jamais ete exerce, meme apres la correction du matin |

Le premier et le deuxieme cas sont le meme octet de production juge par deux corpus : c'est la mesure
qui etablit que l'angle mort etait reel et qu'il est ferme, et non que la clause est exigeante en
general.

### Le garde-fou qui m'a corrige, et je l'ecris parce qu'il a eu raison

Premiere version de ce lot : `LesGardeFousSontMisALEpreuveTest.aucunGardeFouDeBalayageNEchappeALaMiseALEpreuve`
est passe **rouge** sur `ToutEcranEchangeEstUnDocumentJouableTest`. Motif exact : en lisant desormais un
fichier de `src/main/`, cette classe est devenue un **garde-fou de balayage** au sens d'`ADR-0013`, et
elle devait donc posseder un jeu fautif et s'y executer en exigeant le rouge. Elle n'en avait pas.

Je n'ai pas exempte la classe, j'ai ecrit le jeu fautif. La lecon vaut d'etre notee : le mecanisme
d'`ADR-0013` a attrape **son propre auteur** en train d'ajouter une lecture de production sans sa mise
a l'epreuve, dans le meme lot ou il reprochait a deux corpus d'etre aveugles.

## Consequences

**Bonnes.**

- Les deux clauses d'`ADR-0020` et les cinq d'`ADR-0027` s'appliquent desormais a la moitie du jeu
  qu'elles ne voyaient pas. C'est un elargissement de couverture a cout nul en production.
- Le lot qui implementera l'arbitrage de `nadia-prof` sur l'ecran intermediaire trouvera un corpus qui
  contient deja l'ecran d'une reponse fausse. Sans cela, ce lot aurait pu livrer un ecran neuf que rien
  ne juge - exactement le chemin par lequel le defaut mesure ci-dessus est arrive.
- Le marqueur du verdict ne peut plus disparaitre du gabarit en silence.

**Mauvaises, et je les nomme.**

- **Le corpus reste une liste ecrite a la main.** Cet ADR ferme la dimension du verdict, une dimension
  parmi d'autres. Un etat neuf sur une route deja exercee, s'il ne se distingue pas par le verdict,
  echapperait encore. Je n'ecris pas aujourd'hui la clause generale ("chaque branche conditionnelle
  d'un gabarit est exercee par le corpus") parce que le nombre d'etats de l'ecran de jeu est
  precisement ce que l'arbitrage a venir va changer : la deriver maintenant serait la deriver d'un flux
  imaginaire. **La condition de reouverture est ecrite** : le jour ou l'ecran intermediaire est
  arbitre, cette clause generale se derive du flux reel, et c'est la que je la demande.
- **Deux clauses de meme nom dans deux classes.** C'est une duplication assumee de six lignes ; la
  lecture, elle, n'est pas dupliquee. Trois lignes semblables valent mieux qu'une abstraction
  prematuree, et une classe de support pour deux appels n'en serait pas une bonne.
- Le corpus fait un demarrage de partie de plus (`jeu-repete`), soit deux requetes supplementaires par
  execution de chaque classe. Cout mesure : indiscernable dans le temps de la classe.

## Alternatives considerees

- **Ecrire les deux noms de classe dans le test** (`est-juste`, `est-faux`). Rejete : c'est un
  perimetre enumere, qui survit a la disparition de son objet. La lecon de s05 est explicite - une
  fixture ecrite par l'auteur d'une regle ressemble a ce qu'il imagine ; ici, deux litteraux recopies
  ressembleraient a l'etat du gabarit du jour ou ils ont ete recopies.
- **Deriver l'etat depuis le domaine** en demandant a `Verdict` de dire quelle classe le representer.
  Rejete : ce serait faire remonter un nom de classe CSS dans `domain`, ce qu'`ADR-0002` interdit et
  que rien n'exige - le gabarit est la source qui gouverne le rendu, et il est lisible.
- **Ecrire tout de suite la clause generale sur les branches conditionnelles des gabarits.** Rejete
  aujourd'hui, avec sa condition de reouverture ci-dessus. Deux motifs : elle se derive du flux, qui
  est en cours d'arbitrage ; et l'appariement d'une condition de gabarit avec l'element rendu
  correspondant n'a pas de cle evidente - `fragments/retour.html` porte deux paragraphes de classe
  `verdict` sur les deux branches de la meme condition, donc la presence-absence par classe ne suffit
  pas. Une clause qui se tromperait de cle serait pire que pas de clause : elle passerait pour une
  couverture.
- **Ne rien ecrire et laisser le constat dans l'avis de faisabilite.** Rejete par le premier principe
  de ce role : une regle qui n'est pas encodee en test executable n'est pas une regle, c'est un
  souhait. Le constat est mesure, sa correction ne depend d'aucun arbitrage, et rien ne justifiait
  d'attendre.

## Ce qui me ferait changer d'avis

- Si la clause generale sur les branches de gabarit est ecrite un jour et couvre reellement la
  dimension du verdict, le point 1 devient redondant et doit etre **supprime** plutot que garde par
  habitude, avec son motif ecrit.
- Si l'arbitrage supprime l'ecran de retour faux, les deux marqueurs cessent d'etre rendus par
  `fragments/retour.html`. La lecture rougira alors, et c'est le comportement voulu : elle demande a
  etre re-derivee sur la source qui exprime desormais le verdict, pas a etre contournee.

## Ce que l'amendement du 2026-08-12 laisse ouvert, et pour qui

La clause elargie reste juste apres la disparition de l'ecran intermediaire, mais **elle ne suffira pas
a couvrir les deux ecrans de manche**. Sur le nouvel enchainement, "juste, la partie continue" et "faux,
la partie continue" sont deux ecrans de manche que rien ne distingue par un marqueur de verdict : leur
seul discriminant sera **le message sous le meuble**, qui n'existe pas encore. La clause deviendra donc
**rouge** le jour du changement, sur le couple faux + `EN_COURS`, et c'est voulu : son message dit de
re-deriver le marqueur de cet etat, jamais d'affaiblir la clause. **C'est la charge du lot qui
implementera la mecanique**, et elle est nommee ici pour qu'elle ne se decouvre pas.

## Verification

`mvn -B verify` sur l'arbre de ce lot, `JAVA_HOME` sur un JDK 21.0.10 : **BUILD SUCCESS**, 382 tests,
0 echec. Baseline avant ce lot, meme commande, meme JDK : 371 tests, 0 echec. Les onze tests d'ecart
sont les clauses de cet `ADR` et d'`ADR-0035`, amendements compris.

> **Amendement du 2026-08-13 (s10, marc-dev) - la clause a rougi comme annonce, et voici sa
> re-derivation.**
>
> Confirme exactement : implementer l'arbitrage de mecanique de nadia-prof
> (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, conditions M1-M13) a rendu
> `leCorpusExerceChaqueCoupleDeVerdictEtDeSuite` rouge sur le couple faux + `EN_COURS`, dans les deux
> classes qui la portent. `fragments/retour.html` ne rend plus qu'un seul etat - la fin de partie -
> puisque le cycle de manche absorbe desormais la reponse juste comme la fausse tant que la partie
> continue ; sa branche "la partie continue" (`${enSuite}`, "Encore !") a disparu avec
> `GET /parties/{jeuId}/manche`, son seul appelant.
>
> **Ce que cela change pour la lecture du marqueur de fin de partie.** `marqueurDeFinDePartie()`
> lisait la classe portee par l'element que la negation de `${enSuite}` rend - il n'y a plus de
> condition `${enSuite}` a lire, `fragments/retour.html` n'ayant plus qu'un seul etat a exprimer. La
> lecture est re-derivee sur ce que ce gabarit garde d'unique et de stable : la classe de l'element
> dont le texte fixe est "C'est fini !" - un litteral que ce depot traite deja comme une source de
> verite ailleurs (`TexteAccentueDesGabaritsTest.leMessageDeFinDePartieRefuseParNadiaNeRevientPas`).
> Le corpus des deux classes est etendu d'un ecran neuf ("manche en revelation, la partie continue")
> pour que M5 soit lui aussi exerce, sans quoi rien ne distinguerait plus un echec ordinaire d'une
> revelation dans ce corpus.
>
> **Ce qui n'a pas bouge** : la clause elle-meme, `marqueursDeVerdict()` (toujours lus sur
> `${juste}`), et le principe que ce corpus derive ses marqueurs du gabarit plutot que de les
> recopier (ADR-0015). Aucune des deux classes n'a ete exemptee ni affaiblie.
>
> Fichiers : `adapters/adapter-web/src/test/java/com/recreo/adapter/web/ToutEcranEchangeEstUnDocumentJouableTest.java`,
> `adapters/adapter-web/src/test/java/com/recreo/adapter/web/LEcranDeJeuDeclareCommentIlEstArriveTest.java`,
> `adapters/adapter-web/src/main/resources/templates/fragments/retour.html`.
