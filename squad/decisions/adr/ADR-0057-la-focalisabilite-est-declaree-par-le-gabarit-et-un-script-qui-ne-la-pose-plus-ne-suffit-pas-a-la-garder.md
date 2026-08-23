# ADR-0057 - La focalisabilite d'une cible de focus est declaree par le gabarit qui la rend, et un script qui ne la pose plus ne suffit pas a la garder

- **Statut** : Accepte, ses deux clauses ecrites dans le meme commit
  (`FrontieresDuJavaScriptTest.leScriptDeFocusNeRendJamaisUnElementFocalisable` et sa morsure ;
  `ToutEcranEchangeEstUnDocumentJouableTest.surChaqueEcranLaCibleDeFocusDuScriptEstFocalisableDansLeDocumentServi`
  et sa morsure).
- **Sprint** : s18. **Date** : 2026-08-23. **Auteur** : theo-archi.
- **Arbre mesure** : la branche `s18-theo` apres fusion du lot de `marc-dev` (`6b3fe2b`, lui-meme
  forke de `round-two` a `8046256`). SHA de la fusion releve par `git rev-parse HEAD` sur ce
  worktree, jamais repris d'un message de commit. `round-two` reste a
  `80462564229e3c3f6cba2b331ba54f2e4769aea9`, intact.
- **Route par** : `sofia-sm`, qui me demande l'`ADR` que j'avais moi-meme declare du dans l'avis de
  faisabilite de `S17-C` - *"sa regle ne peut pas etre verte avant que le code existe"*. Le code
  existe : `marc-dev` a livre le mecanisme B, la ligne qui posait `tabindex` est sortie de
  `recreo.js`, et l'attribut est declare statiquement dans `manche.html`,
  `confirmation.html` et `retour.html`. **L'enonce de la regle m'est transmis comme une proposition,
  et c'est comme telle que je le traite.**
- **Complete** : `ADR-0038` et `ADR-0040` (les arrets de tabulation et le point de focus declare),
  `ADR-0020` (le document assemble), `ADR-0008` (la frontiere du JavaScript), `ADR-0013`
  (les trois obligations), `ADR-0055` (la moitie de comparaison qui ne garde pas), `ADR-0056`
  (l'unicite d'ancrage, dont le declencheur d'extraction est atteint ici), `ADR-0025` et `ADR-0028`
  (ce qu'un rendu ne laisse pas garder).

## Contexte

Le mecanisme est le bon et il est livre proprement : le serveur declare, le script se reduit a
`focus()`. **Ce que la CI en garde, en revanche, ne tenait ni d'un cote ni de l'autre**, et je ne
l'ai pas trouve en lisant le lot - je l'ai trouve en essayant de le defaire.

### Mesure 1 - la clause proposee est contournee par l'orthographe usuelle de la faute

La clause que `marc-dev` propose comme base est
`FrontieresDuJavaScriptTest.leScriptDeFocusNeMuteJamaisTabindex` : le fichier maison ne contient
jamais la sous-chaine `tabindex`. Elle attrape bien `setAttribute('tabindex', '-1')`, la ligne qui
vient d'etre retiree.

**Elle n'attrape pas la meme chose ecrite autrement.** `pointDeFocus.tabIndex = -1;` est la propriete
DOM - la facon la **plus** idiomatique d'ecrire ce geste en JavaScript - et `doesNotContain` est
sensible a la casse. Mesure du 2026-08-23, sur l'arbre fusionne, JDK 21.0.10, cette seule ligne
ajoutee a `recreo.js` :

| Ce que la CI dit | Ce qui est vrai |
|---|---|
| `bash outils/mvn-jdk21.sh -B clean verify` : **BUILD SUCCESS**, six modules, **618 tests, 0 echec** | le bouton nomme a la revelation est ressorti de l'ordre de tabulation : **le defaut exact que `nadia-prof` a mesure et que `S17-C` existe pour corriger, integralement restaure** |

Les vingt et un tests des trois classes concernees passent, la suite entiere passe. **Un garde-fou que
l'orthographe usuelle de la faute contourne n'est pas un garde-fou** : c'est la lecon de s05 - une
fixture, ou ici un predicat, qui ressemble a ce que son auteur imagine plutot qu'a ce que le monde
ecrit. Et c'est la deuxieme fois en deux jours que je la rencontre : `ADR-0056` fermait hier un seuil
qu'il suffisait d'indenter.

### Mesure 2 - la moitie que le lot a rendue decisive n'etait gardee par rien

Le mecanisme deplace la declaration du script vers trois gabarits. La clause proposee garde
**l'absence** dans le script. Rien ne gardait **la presence** dans les gabarits.

Mesure du meme jour, sur le meme arbre : les trois `tabindex="-1"` retires de `manche.html`,
`retour.html` et `confirmation.html` - c'est-a-dire **le mecanisme entier de `S17-C` defait, la
totalite de ce que le lot livre** :

| Ce que la CI dit | Ce qui est vrai |
|---|---|
| **BUILD SUCCESS**, **618 tests, 0 echec** | sur les trois ecrans, `focus()` ne fait plus rien : un `<p>` et un `<div>` sans `tabindex` ne sont pas focalisables. **L'enfant qui arrive sur l'ecran n'a plus de point de depart au clavier** |

C'est la faute qu'`ADR-0055` a nommee, sur un autre objet : **une moitie de propriete gardee n'est
pas la propriete gardee.** Le lot n'a pas cree ce trou - il l'a rendu decisif, en faisant du gabarit
le porteur unique de la propriete. Avant `S17-C`, le script posait l'attribut a l'execution, donc son
absence dans le gabarit ne cassait rien.

### Ce que je ne reproche pas au lot

Rien de ce qui precede n'est un defaut de ce que `marc-dev` a livre. Le mecanisme retenu est celui
que j'ai recommande, l'implementation le suit exactement, les deux pieges que j'avais nommes ont ete
rencontres et traites, la morsure ajoutee a `AucunArretDeTabulationNEstDestructeurTest` est la bonne
- elle reintroduit le defaut d'avant le lot sur le materiau reel. **La clause textuelle qu'il propose,
il la propose ; c'est a moi qu'il revenait de dire si elle garde ce qu'elle nomme, et la reponse est
non.** Elle a par ailleurs atterri dans le seul fichier de balayage JavaScript que
`DETTE_DE_BALAYAGE` exempte de l'obligation de morsure, ce qui explique que rien ne l'ait signalee.

## Decision

**La focalisabilite d'une cible de focus est une propriete du document servi, declaree par le gabarit
qui la rend. Elle est gardee des deux cotes, par une egalite de nature : le script ne la produit
jamais, et le document la porte toujours.**

1. **Le script d'agrement ne rend aucun element focalisable.** Il ne pose pas, ne retire pas et
   n'ecrit pas la focalisabilite d'une cible - ni par l'attribut (`setAttribute`,
   `removeAttribute`), ni par la propriete (`tabIndex`). La comparaison **ignore la casse**, parce
   que l'attribut et la propriete s'ecrivent avec les memes lettres et que la mesure 1 etablit que
   distinguer les deux est exactement le trou.
2. **Pour tout ecran du corpus, la cible que le script designera est focalisable dans le document
   que le serveur rend** : soit par sa balise, soit par un `tabindex` declare, quelle que soit sa
   valeur - `-1` retire de l'ordre de tabulation mais laisse `focus()` agir, et c'est precisement ce
   que le mecanisme pose sur les trois cibles non focalisables.
3. **Le sens de la regle proposee est retenu, sa formulation est etendue.** La proposition disait
   *"une cible non nativement focalisable porte `tabindex="-1"` dans son gabarit"*. Cet `ADR` ne
   nomme pas la valeur : ce qui compte est que `focus()` atteigne la cible, et epingler `-1` ferait
   rougir un lot qui voudrait, pour un motif pedagogique, faire de cette cible un arret de tabulation
   a part entiere. Ce choix-la appartient a `nadia-prof`, et `ADR-0038`/`ADR-0040` gardent deja ce
   qu'un arret de tabulation ne doit pas faire.
4. **La clause 2 nomme les trois branches de l'aiguillage du script et exige de les rencontrer
   toutes.** Une clause qui n'aurait croise que deux branches sur trois garderait moins qu'elle ne
   nomme : c'est le motif d'`ADR-0051`, applique par une assertion et non par un commentaire.

**Ce qui n'est pas decide ici** : que la tabulation atteigne reellement ce point au clavier. Cela
reste la mesure de `nadia-prof`, `ADR-0025` et `ADR-0028` inchanges. Aucun octet de ce que voit un
enfant n'est change par cet `ADR` ; il n'ajoute que du test.

## Regles executables

**Clause 1** -
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/FrontieresDuJavaScriptTest.java`,
`leScriptDeFocusNeRendJamaisUnElementFocalisable`, avec
`doesNotContainIgnoringCase` au lieu de `doesNotContain`.

**Sa morsure**, `uneFocalisabiliteRendueParLeScriptFaitEchouerLeGardeFou` : `recreo.js` **tel qu'il
est servi**, copie puis mute du geste exact que la mesure 1 a trouve invisible. Pas une fixture -
c'est l'orthographe imaginee qui a laisse passer la version precedente, et une fixture de ma main
aurait porte la mienne. Le perimetre du balayage devient parametrable par racine statique pour que
la morsure juge une copie mutee du materiau reel ; le repertoire tiers est derive de la racine
passee, jamais de la constante de production.

**Le plafond de `DETTE_DE_BALAYAGE` n'est pas baisse**, alors que ce fichier possede desormais une
morsure. Ses trois autres clauses de balayage n'en ont pas, et le predicat du meta-garde-fou verifie
l'existence d'un repertoire - le sortir de la dette parce qu'un repertoire existe serait satisfaire
le proxy que j'ai moi-meme critique dans `ADR-0054`. La morsure vit donc sur une copie temporaire,
pas sous `contreexemple/`, et la dette reste a deux noms.

**Clause 2** -
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/ToutEcranEchangeEstUnDocumentJouableTest.java`,
`surChaqueEcranLaCibleDeFocusDuScriptEstFocalisableDansLeDocumentServi`.

**Pourquoi ce fichier et pas un autre.** Son nom dit *"tout ecran echange est un document jouable"*,
et un ecran dont le point de focus ne peut pas recevoir le focus n'est pas jouable au clavier : la
clause dit ce que le fichier dit. Il porte deja le corpus dont j'ai besoin - de vrais ecrans produits
en jouant de vraies parties sur une session HTTP, un par route, la liste des routes derivee de
`RequestMappingHandlerMapping` et non ecrite a la main. **Je n'ai pas mis cette clause dans
`AucunArretDeTabulationNEstDestructeurTest`**, qui porte le meme corpus, pour la raison qu'`ADR-0055`
a deja opposee a l'elargissement de la table d'`ADR-0054` : ce fichier s'appelle *"aucun arret de
tabulation n'est destructeur"*, et la focalisabilite n'est pas cela.

**ADR-0013, les trois obligations.**

- **Perimetre derive.** Les ecrans viennent du corpus existant, pas d'une liste ecrite dans la
  clause. La cible de focus est calculee par le meme aiguillage que le script - le `[data-focus]` de
  l'ecran, puis `.enonce` ou `[data-focus-cible="true"]` selon sa valeur, et a defaut le repli sur
  `.signal`.
- **Derivation totale.** Aucun ecran ne designant de cible est un echec (`ADR-0003`). Une branche de
  l'aiguillage que le corpus n'exerce pas est un echec, avec la liste des branches rencontrees dans
  le message. Mesure : les trois branches sont exercees par le corpus actuel.
- **Mise a l'epreuve.** Sur la sortie reelle du serveur, mutee : le `tabindex` que le gabarit declare
  est retire du document que Thymeleaf vient de rendre, et la cible cesse d'etre focalisable. C'est
  le geste dont la mesure 2 etablit qu'il passait inapercu. **Verifie aussi dans l'autre sens, sur
  l'arbre** : les trois attributs retires des trois gabarits font desormais rougir cette clause en
  nommant l'ecran, la balise, la branche et la consequence pour l'enfant.

**Mesure de ce lot** (JDK 21.0.10 par la toolchain declaree). `bash outils/mvn-jdk21.sh -B clean
verify` sur la racine du worktree : **BUILD SUCCESS**, six modules, **620 tests, 0 echec**.
**Methode de ce chiffre** : somme des six totaux par module que Surefire et Failsafe impriment sur
la racine apres `clean` (48 + 13 + **320** + 144 + 89 + 6), tests d'integration compris - meme
methode et meme perimetre qu'`ADR-0054` (604), `ADR-0055` (612) et `ADR-0056` (615).
**Le delta est annonce contre la base reelle apres fusion, pas contre `round-two`** : la fusion du
lot de `marc-dev` porte l'arbre a **618** (ses +3 sur mes 615), et ce lot-ci ajoute **+2 net** -
trois clauses neuves (la morsure de la clause 1, la clause 2, sa morsure) moins une clause retiree
par l'extraction decrite ci-dessous, qui prouvait deux fois la meme implementation.
`adapter-web` est le seul module dont le total bouge.

## Le declencheur d'`ADR-0056` est atteint, et je tiens l'engagement plutot que de le plaider

`ADR-0056` a assume deux copies de `muter` en ecrivant son declencheur d'extraction : *"le jour ou un
troisieme fichier de ce module mute du materiau reel, les trois copies partent dans un seul endroit,
et ce sera le meme geste que l'extraction de `rangeesRepliables` en `ADR-0055`"*. La morsure de la
clause 1 est ce troisieme fichier, **le lendemain**.

J'aurais pu plaider que le tour d'appel differe - un fichier ici, une racine et un chemin relatif
la - et que ce n'est donc pas une troisieme copie. **Ce serait vrai de la signature et faux de ce qui
compte** : l'unicite d'ancrage decidee par `ADR-0056` est une decision, pas une commodite, et en trois
copies elle pouvait deriver en trois endroits. `muterUneSeuleFois(Path, String, String)` vit donc
desormais dans le fichier ou `muter` est ne, les deux autres l'appellent, et chacun garde le tour
d'appel qui lui va. **Consequence sur le compte** : la clause qui prouvait l'unicite d'ancrage
existait en deux exemplaires, un par copie ; il en reste une, la ou vit l'implementation. Un test de
moins qui ne prouve rien de moins.

## Ce que ces deux clauses ne gardent pas

**La tabulation reelle au clavier.** La clause 1 de `M13` - Tabulation puis Entree ne detruit jamais
la partie - reste la mesure de `nadia-prof`, dans un vrai navigateur. Ce que ce lot ajoute est que la
**cause declarative** de son echec precedent a disparu du materiau servi et ne peut plus revenir en
silence. `marc-dev` le dit exactement ainsi dans son lot, sans le masquer, et je le confirme.

**La focalisabilite native n'est pas modelisee par le faux DOM**, et `marc-dev` l'a verifie. Sa
consequence est plus large que ce qu'elle a l'air : `RecreoJsDeplaceLeFocusTest` execute le vrai
script contre un `FakeElement` dont `focus()` pose un drapeau, donc **il reussit toujours**. Un vert
de ce fichier-la ne dit jamais que la cible etait focalisable - c'est precisement pourquoi la clause 2
ne pouvait pas y vivre et doit lire le document servi.

**La propriete DOM n'est pas observable par le faux DOM non plus.** `pointDeFocus.tabIndex = -1` sur
un objet Java exporte a Nashorn est **silencieusement perdu** : il n'y a pas de `setTabIndex`, et
l'affectation ne laisse aucune trace que `getAttribute("tabindex")` puisse lire. Le faux DOM n'est
donc pas seulement non teste sur ce point, il est **structurellement aveugle** ; c'est la seconde
raison pour laquelle la clause 1 reste textuelle plutot que comportementale.

**Un nom d'attribut calcule echappe a la clause 1.** `setAttribute(nom, '-1')` ou
`'tab' + 'index'` ne contient pas les lettres cherchees. **Consequence assumee, avec ce qui la borne**
: `recreo.js` fait **15 lignes** et `ADR-0008` lui impose un plafond de 100 lignes et de 4 Kio, verifie
par le meme fichier de test. Une indirection de ce genre dans quinze lignes ne passe pas une relecture ;
la seule alternative serait d'interpreter le script, c'est-a-dire d'ecrire un navigateur dans un test -
la dependance que ce depot a refusee en `ADR-0025`, en `ADR-0046` et en `ADR-0054`. **Dette explicite,
avec son declencheur** : le premier lot qui fait calculer a `recreo.js` un nom d'attribut.

**L'aiguillage du script est reencode en Java par la clause 2.** Les deux peuvent divergier si
quelqu'un change une branche **existante** et ne met a jour qu'un des deux endroits. Ce qui borne ce
risque n'est pas cette clause : `RecreoJsDeplaceLeFocusTest` epingle les trois branches en executant
le vrai script, et son faux DOM leve `UnsupportedOperationException` sur tout selecteur qu'il ne
modelise pas - un **quatrieme** selecteur rougit donc la-bas avant d'etre oublie ici. Une branche
existante modifiee de travers dans les deux endroits reste possible. **Non garde, nomme.**

**L'enumeration des balises nativement focalisables est le point faible de la clause 2**, et c'est une
enumeration par types - la forme dont ce depot a ecrit qu'elle *"echoue par inclusion autant que par
omission"*. Le **sens de la panne est choisi** : une balise focalisable absente de la liste rend la
clause rouge a tort, ce qui est visible et se corrige en une ligne ; une balise non focalisable qu'on
y ajouterait creerait un trou muet. La liste reste donc l'ensemble interactif de la specification, et
rien n'y entre pour faire passer un cas.

## Verification que les deux tables de largeur ne sont pas concernees - mesuree, pas crue

`marc-dev` ecrit que son lot n'oblige a toucher ni les deux planchers pedagogiques ni les tables
d'`ADR-0054` et `ADR-0055`. **C'est exact, et je l'ai verifie plutot que de le croire** - je viens
d'etablir dans `ADR-0056` que deux morsures d'`ADR-0054` rougissaient pour une cause qui n'etait pas
la leur, ce qui est exactement le genre de chose qu'on ne voit pas en lisant.

Mesure sur l'arbre fusionne : `LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest` **11 tests,
0 echec** ; `LaLargeurDemandeeParUneRangeeEstEpingleeTest` **9 tests, 0 echec** (8 apres l'extraction
ci-dessus) ; `TailleDesTextesEtBoutonsTest`, qui porte les deux planchers, **28 tests, 0 echec**.

**Le motif, et il est structurel plutot que chanceux** : les deux tables epinglent des **declarations
de feuille de style** - les termes lateraux d'une chaine, les termes de colonne d'une rangee. Le lot
n'ajoute aucune declaration : il ajoute un **attribut HTML** sur trois elements, et `tabindex`
n'appartient a aucun des deux jeux de proprietes. Il n'ajoute pas non plus d'element, donc la
population des rangees repliables - derivee des elements que designe une regle maison declarant
`flex-wrap` - est inchangee. Aucune des deux tables ne pouvait bouger, et aucune n'a bouge.

## Le sort de la dette 4 de `CLAUDE.md`

La dette 4 est : *"le comportement des mecaniques loge dans le JavaScript client : 692 lignes
qu'aucun test ne couvrait, derriere huit classes Java de 55 lignes qui ne faisaient que deleguer"*.

**Elle recule, et c'est mesurable ; elle n'est pas fermee, et il faut le dire aussi.**

Ce qui recule : `recreo.js` fait **15 lignes** et, depuis ce lot, **ne mute plus rien**. Il lit le
document et appelle `focus()`. La seule decision qu'il portait et que la CI ne pouvait pas voir -
quel element devient focalisable - **a franchi la frontiere vers le serveur**, ou elle est
desormais une declaration dans un gabarit, lisible par un automate sur le document servi. La clause 2
est la contrepartie de ce deplacement : la propriete est verifiee **la ou elle vit maintenant**, sur
de vrais ecrans produits par de vraies parties. C'est exactement le mouvement que la dette 4
demande - *"ce qui peut etre teste cote serveur doit l'etre"* - et c'etait l'un de mes trois motifs
pour recommander ce mecanisme plutot que l'autre.

Ce qui ne recule pas : **l'aiguillage lui-meme reste dans le script.** Choisir entre `.enonce`,
`[data-focus-cible="true"]` et le repli sur `.signal` est un comportement qui vit cote client, et il
n'est eprouve que par un faux DOM - cinq clauses qui executent le vrai script contre un objet Java, ce
qui est beaucoup mieux que rien et n'est pas un navigateur. **La dette 4 passe donc de "une decision
invisible a la CI" a "une decision visible, dont l'aiguillage reste eprouve par procuration".** Je ne
la declare pas soldee, et je n'ouvre pas de lot pour la solder : deplacer l'aiguillage vers le serveur
demanderait que le fragment nomme lui-meme sa cible sans ambiguite, ce qui est une question de
conception de gabarit, pas un defaut a corriger. **Declencheur qui la rendrait due** : une quatrieme
branche d'aiguillage, ou une branche dont la cible depend de l'etat de la partie.

## Consequences

- **Le mecanisme de `S17-C` ne peut plus etre defait en silence, des deux cotes.** Retirer un
  `tabindex` d'un gabarit rougit la clause 2 en nommant l'ecran et l'enfant ; le reposer depuis le
  script rougit la clause 1, quelle que soit l'orthographe usuelle employee.
- **Une propriete pedagogique gagne sa moitie manquante.** C'est la deuxieme fois en deux jours qu'un
  `ADR` de ce depot existe pour cette raison-la ; la forme du defaut est desormais identifiee assez
  precisement pour etre cherchee au lieu d'etre rencontree.
- **Un garde-fou de plus lit le corpus d'ecrans reels**, dont le cout de construction est deja paye.
  La clause 2 n'ajoute aucune requete HTTP au-dela de ce que ce fichier jouait deja.
- **Mauvaise consequence** : la clause 2 rougira pour un lot qui ajouterait un ecran designant une
  cible de focus et oublierait sa focalisabilite - ce qui est le but - mais aussi pour un lot qui
  ajouterait une cible dont la balise est focalisable sans figurer dans l'enumeration. Le faux rouge
  est visible et se corrige en une ligne ; je le prefere au trou muet symetrique.
- **Seconde mauvaise consequence** : la clause 1 reste textuelle. Elle garde une propriete du
  **fichier**, pas du comportement, et un lecteur peut la croire plus forte qu'elle n'est. Le nom du
  test dit *"ne rend jamais un element focalisable"*, ce que le predicat approche sans l'atteindre ;
  la section "ce que ces deux clauses ne gardent pas" est la seule protection contre cette lecture, et
  le plafond de quinze lignes du script en est la borne pratique.
- **Aucun octet de ce que voit un enfant n'est change par cet `ADR`.** Ni `S17-C`, ni `M13`, ni aucun
  verdict pedagogique n'est touche. Le Done pedagogique de `S17-C` appartient a `nadia-prof` et
  n'est pas prononce ici.

## Alternatives considerees

**Reprendre la clause de `marc-dev` telle quelle.** Ecartee sur une mesure, pas sur un jugement de
style : la ligne la plus idiomatique du geste interdit la laisse verte, avec 618 tests verts autour.
Une clause qu'on contourne sans le savoir est pire qu'une clause absente, parce qu'elle occupe la
place.

**Garder la clause textuelle sensible a la casse et lui ajouter `tabIndex` comme second interdit.**
Ecartee : c'est une enumeration de deux orthographes la ou une comparaison insensible a la casse
couvre la classe entiere - et *"nommer deux types n'est pas nommer deux situations"* est precisement
la faute que ce depot a payee d'une session perdue pendant deux sprints.

**Garder la propriete par un comportement plutot que par un texte**, en faisant modeliser au faux DOM
l'attribut et la propriete, puis en assertant qu'aucun des deux ne bouge apres execution. Ecartee sur
un fait, pas par confort : une affectation de propriete sur un objet Java exporte a Nashorn est
silencieusement perdue, donc le faux DOM ne peut pas l'observer sans qu'on lui ecrive un modele de
proprietes DOM - un navigateur en miniature, la dependance refusee par `ADR-0025`.

**Epingler la valeur `-1`** comme la proposition le suggerait. Ecartee : ce qui compte est que
`focus()` atteigne la cible. Epingler `-1` interdirait a `nadia-prof` de faire de l'une de ces cibles
un arret de tabulation a part entiere sans qu'un `ADR` soit reecrit, et ce choix-la est le sien -
meme motif que le refus, en `ADR-0055`, de relever les deux planchers pedagogiques en egalites.

**Mettre la clause 2 dans `AucunArretDeTabulationNEstDestructeurTest`**, qui porte le meme corpus et
ou `marc-dev` a place sa propre morsure. Ecartee sur le nom, comme `ADR-0055` avait ecarte
l'elargissement de la table d'`ADR-0054` : ce fichier garde *"aucun arret de tabulation n'est
destructeur"*, et lui faire porter la focalisabilite lui ferait dire autre chose que son nom - le
defaut que ce depot a paye plusieurs fois, ou une clause ecrite pour une propriete se voit creditee
d'une autre.

**Sortir `FrontieresDuJavaScriptTest` de `DETTE_DE_BALAYAGE` et baisser le plafond de 2 a 1**,
puisque le fichier possede desormais une morsure. Ecartee, et c'est un refus que je m'applique :
le predicat du meta-garde-fou verifie l'existence d'un repertoire de jeu fautif, ce qui est un proxy ;
ses trois autres clauses de balayage resteraient sans morsure. Baisser le plafond sur la foi du proxy
aurait fait passer la dette de deux noms a un nom sans qu'un seul garde-fou de plus soit eprouve.
**Declencheur de la baisse** : le jour ou ses trois autres clauses de balayage recoivent leur morsure.
