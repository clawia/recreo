# ADR-0030 - Un habillage entoure sans rogner, n'anime rien, et toute animation finit sur son etat de repos

- **Statut** : Accepte
- **Date** : 2026-08-11
- **Auteur** : theo-archi
- **Sprint** : s08 (planning)

## Contexte

Trois lots me sont routes, aucun fusionne : l'arbitrage prealable de `nadia-prof` (`s08-nadia`,
`cb4f40e`), qui contraint ; la proposition d'`ines-design` (`s08-ines-design`, `5d03af4`) ; `DEC-0017`
et les stories S8-1 a S8-4 (`s08-camille-po`, `e9ac1e6`). Je juge la faisabilite du cabinet **sur le
code**, et je tranche cinq questions que les deux premiers lots me laissent explicitement.

Je ne code pas le cabinet : `lea-dev` l'implementera. Ce que cet `ADR` livre, ce sont les contraintes
de mise en oeuvre, deux clauses executables neuves, la correction d'une valeur que `DEC-0017` acte
(S8-2), et la liste de ce qui reste a `nadia-prof`.

## Decision

### 1. Le cabinet est faisable en l'etat, sous quatre contraintes de mise en oeuvre

**Verdict : faisable, sans `ADR` supplementaire, moyennant le retrait d'une propriete** (point 2).
Les quatre points que `sofia-sm` me demande de verifier sur le code, verifies plutot qu'affirmes :

**a. `.cabinet` comme classe et comme element.**
`HabillageDesEcransTest.aucuneClassePoseeSurUnElementNEstOrpheline` exige que toute classe posee dans
un gabarit soit nommee par une regle **servie** - c'est le garde-fou qui m'a corrige en s07 et qui a
fait choisir un attribut plutot qu'une classe pour `data-arrivee`. La forme proposee le satisfait :
les trois classes du gabarit (`cabinet`, `cabinet-bandeau`, `cabinet-socle`) sont chacune nommees par
une regle de la feuille proposee. Contrainte pour `lea-dev` : **une bande decorative sans regle propre
est refusee**. Si le socle finit sans declaration, il ne doit pas exister comme classe.

**b. `ADR-0020`, classes et jamais d'identifiants.** La forme proposee n'introduit aucun `id`. Rien a
trancher, la contrainte est deja tenue.

**c. `ADR-0017` et le `data-role`.** Le cabinet n'est pas pressable : il ne porte **pas** de
`data-role`, et c'est correct - `LActionNeDeclarePasMoinsQueLaSortieTest` ne reproche l'absence de role
qu'aux elements qu'il collecte, et il collecte `button, a[href]`. **La derivation reste correcte avec
un conteneur de plus entre la zone et les boutons**, et voici les trois lectures qui l'etablissent
plutot qu'une impression : le selecteur `button, a[href]` est un selecteur de **descendance**, donc la
profondeur n'y change rien ; `estDans` remonte la chaine des parents jusqu'a la zone echangee, donc un
bouton reste reconnu comme etant dans la zone ; et `contraintesHeritees` ne collecte que `th:if` et
`th:unless` sur les ancetres, or le cabinet n'en porte aucun. **Contrainte pour `lea-dev` : aucun
`th:if` ni `th:unless` sur le cabinet.** Un habillage rendu conditionnellement ferait entrer sa
condition dans l'appariement de saillance de tous les boutons qu'il contient, et cette derivation-la
n'a pas ete pensee pour ca.

**d. Ou vit le cabinet.** Il est du **balisage de `fragments/manche.html`**, jamais un pseudo-element
pose sur un marqueur partage (`ecran-de-jeu`, ou la racine d'un fragment). C'est la contrainte qui
tient la condition C4 et l'acquis 6 de `nadia-prof` par la structure, comme elle le demande
nommement : le cabinet n'atteint pas l'ecran de retour ni l'ecran de fin de partie parce que son
balisage n'y est pas.

### 2. `overflow: hidden` sur le cabinet est refuse, et la propriete devient gardable

`ines-design` remonte la question de l'anneau de focus clavier plutot que de la trancher. **Je la
tranche par le refus, et sur un fait mesure dans le depot, pas sur un principe.**

La bibliotheque vendorisee declare, pour tout `.btn` :
`.btn:focus-visible { color: ...; outline: 0; box-shadow: var(--bs-btn-focus-box-shadow) }`.
L'anneau de focus de ce depot **n'est donc pas un `outline`** - il est explicitement supprime - mais
une **ombre portee**, peinte a l'exterieur de la boite du bouton. Une ombre portee est rognee par le
rognage d'un ancetre. Et il n'y a nulle part ou la peindre : `nadia-prof` a mesure que l'axe
horizontal est epuise a 360 px et a refuse que le cadre se paye sur le `gap` ou sur le rembourrage,
donc les colonnes occupent **toute** la largeur interieure du cabinet. Consequence : avec
`overflow: hidden`, **l'anneau de focus disparait sur la premiere et la derniere colonne** - a deux
colonnes, sur les deux ; a trois, sur deux des trois.

Second effet, distinct et pire : `overflow: hidden` fait du cabinet un **conteneur de defilement**.
Le navigateur qui donne le focus a un element rogne fait defiler ce conteneur pour le ramener dans le
champ, et les colonnes bougeraient a l'interieur de leur cadre sans qu'aucun geste de l'enfant ne
l'ait demande - ce que `ADR-0027` interdit cote serveur et que `nadia-prof` place en tete de C3. C'est
**mon propre argument de s07**, celui qui m'avait fait preferer le pseudo-element a une fenetre en
`overflow: hidden` ; le cabinet le reintroduit, et c'est pourquoi je le refuse ici au lieu de le
redecouvrir sur le rendu.

**La situation a change depuis s07, et c'est ce qui autorise la regle.** J'avais ecrit que le focus
visible n'est garde par aucun automate et que je n'ecrivais pas de regle contre un defaut non etabli.
Le defaut est desormais etabli : la declaration de la bibliotheque est un fait du depot, la largeur
epuisee est une mesure de `nadia-prof`, et la propriete rognante est **ecrite dans la proposition**.
J'ecris donc la regle - et **seulement la moitie declarative** : que l'anneau soit **visible** reste
une propriete de rendu, donc a `nadia-prof` (`ADR-0025`). Ce qui est garde est plus etroit et
verifiable : aucune regle maison ne pose de propriete de rognage sur un ancetre d'un element
pressable.

### 3. L'image : rien a vendoriser, et la forme dessinee est exigee

**Cout : nul.** `ines-design` ne propose aucun fichier : l'etoile est dessinee par la feuille, en
`clip-path: polygon(...)` sur un pseudo-element avec `content: ""` - la meme technique que la coche et
la croix du signal juste/faux. Aucune ressource tierce, aucun octet a vendoriser, aucune trace ni
empreinte a tenir, rien qui aille sur le reseau. `RessourcesTiercesTest` n'a rien de plus a juger.

**Et cette forme n'est pas seulement peu couteuse, elle est la seule que j'autorise**, parce que les
deux autres sont deja refusees ou dangereuses :

- **une image chargee par la feuille** (`content: url(...)`, `background-image: url(...)`) : la clause
  7 d'`ADR-0027` refuse toute valeur de `content` autre que la chaine vide - donc `content: url(...)`
  est **deja rouge**, par un garde-fou non vide qui porte sur deux regles reelles ;
- **une image posee par le gabarit** (`<img>`), a plus forte raison avec une source calculee
  (`th:src`) : c'est la forme qui ouvre le canal exact que la clause C5 point 2 de `nadia-prof`
  interdit - "le jeu d'images du cabinet ne depend ni de l'item tire, ni du numero de la colonne, ni
  de la reponse attendue", son point de garde le plus important du chantier. Une forme **dessinee par
  une regle statique** ne peut pas varier avec l'item : il n'existe aucun chemin par lequel le serveur
  l'atteindrait, puisqu'il ne rend aucun attribut sur cette bande et qu'aucun JavaScript ne vit ici
  (`ADR-0008`). L'invariance devient **structurelle** au lieu d'etre surveillee.

**Contrainte pour `lea-dev` et pour tout futur habillage** : une image du cabinet est dessinee par une
regle maison, jamais chargee, jamais designee par un attribut de gabarit. Si un jour une vraie image
est voulue, elle entre par la vendorisation tracee d'`ADR-0014` et **cet `ADR` doit etre repris** -
c'est le declencheur ecrit.

### 4. Ce que je prends de C3 et C4, et ce que je laisse

**C3 - je prends la moitie qui est dans la feuille.** Clause 9 : **toute animation maison s'applique a
un element pressable ou a son pseudo-element decoratif, jamais a un habillage.** C'est
verbatim l'exigence de `nadia-prof` - "le mouvement appartient aux colonnes et a elles seules" - dans
sa forme declarative. Elle ecrit elle-meme que ce point est le sien parce qu'"un `@keyframes` sur une
regle d'habillage passerait une CI verte" : ce n'est plus vrai.

**Ce que je lui laisse de C3, nommement** : un changement de couleur obtenu autrement qu'en animant -
un etat au survol, une transition, une lumiere peinte dans une image, un degrade qui evoque une
diode. Sa condition est plus large que sa moitie declarative, et je ne pretends pas la couvrir.

**C4 - je la laisse entiere a `nadia-prof`**, et je dis pourquoi plutot que de fabriquer un automate
qui en aurait l'air. Ce qu'elle verifie est l'identite **des pixels** de deux ecrans rendus : hors
d'atteinte d'une analyse declarative (`ADR-0025`). Ce qui est structurellement garanti l'est par la
contrainte 1.d - le cabinet est du balisage de `fragments/manche.html`, l'ecran de retour n'en porte
pas - et **cette garantie-la n'est gardee par aucun test** : je le nomme comme la dette qu'elle est,
plutot que d'ecrire une clause qui nommerait `.cabinet` dans un test, ce qui serait un perimetre
enumere (`ADR-0013`) et, pire, un nom cite dans un fichier alors que rien ne le porte encore dans le
code - la dette n°1 de la version precedente.

### 5. L'etat final d'une animation : gardable, mais l'egalite de chaines n'est pas la propriete

**Oui, la propriete est gardable**, et elle attrape la famille entiere plutot que le cas. Clause 10 :
**la derniere image d'une animation est l'etat de repos de ce qu'elle anime.** L'etat final est
declaratif - c'est le bloc `to` ou `100%` du `@keyframes` - et l'etat de repos est la declaration de
la meme propriete sur le meme sujet, ailleurs dans la feuille.

**Mais la propriete n'est pas une egalite de chaines, et c'est le coeur de cette section.**
`inset(100% 0 0 0)` (la valeur d'arrivee corrigee) et `inset(0 0 100% 0)` (l'etat de repos ecrit
depuis S7-1) sont **deux chaines differentes qui rendent la meme image** : rien de visible. Une
comparaison textuelle aurait donc rougi sur la **correction juste**, et la seule facon de la verdir
aurait ete de reecrire l'etat de repos pour qu'il ressemble a la derniere image - c'est-a-dire de
changer ce que voit un enfant pour satisfaire un automate, ce qu'`ADR-0025` a deja refuse. Ce n'est
pas une hypothese : la mesure est au tableau des mutations ci-dessous, et elle rend **deux** echecs.

`clip-path: inset()` est donc compare par la **geometrie qu'il decrit**, calculee sur les nombres
declares : deux decoupes sont equivalentes si elles decrivent le meme rectangle, ou si toutes deux
sont vides. Pour toute autre propriete, la comparaison reste textuelle apres normalisation des blancs
- **limite nommee** : deux valeurs equivalentes ecrites differemment produiraient un rouge, et le
remede est de les ecrire a l'identique, ce qui ne coute rien. `clip-path` recoit un traitement reel
parce que ses deux formes de "rien de visible" sont toutes deux naturelles et **toutes deux presentes
dans ce depot**.

**Et la valeur est corrigee dans le meme commit** (S8-2, acte par `DEC-0017`) : `recreo.css` termine
desormais sur `inset(100% 0 0 0)`. La valeur et sa verification numerique sont d'`ines-design`
(section 4 de sa proposition) : hauteur visible de 20 % x (1 - t), 0 px sur la derniere image, chute
maximale de 0,60 px sur les 150 dernieres millisecondes contre un plafond de 3 px. Je n'ai ni choisi
la forme du mouvement ni remesure le rendu : le releve image par image reste a `nadia-prof`, dont
c'est la reserve.

**Ce que cette clause dit du sprint 7, et qu'il faut ecrire.** Le defaut a traverse `lea-dev`, moi et
`yanis-qa`. `nadia-prof` en avait ecrit la cause exacte : *aucun test ne regarde la derniere image
d'une animation*. Trois de mes propres clauses passaient dessus - l'animation finit, elle a son repli,
elle tient dans la barre de duree - et aucune ne regardait **ou** elle finit. C'est la quatrieme
occurrence de ma famille de faute : une propriete structurelle prise pour la propriete qui compte.

## Regles executables

### 1. `LHabillageNeRogneJamaisUnElementPressableTest` (neuf) - point 2

`adapters/adapter-web/src/test/java/com/recreo/adapter/web/LHabillageNeRogneJamaisUnElementPressableTest.java`

Aucune regle maison ne declare `overflow`, `overflow-x`, `overflow-y` (autre que `visible`) ni
`clip-path` sur une classe portee par un **ancetre** d'un element pressable.

**Perimetre derive** : la population des classes d'ancetres vient du balayage des gabarits, en
remontant la chaine des parents de chaque element pressable, le pressable etant derive par **le meme
selecteur** qu'`ADR-0017` (`button, a[href]`) pour que les deux garde-fous s'accordent sur ce qu'est
un bouton. **Mesure de cette population aujourd'hui : neuf classes** - `container`, `ecran-de-jeu`,
`entete`, `entete-contenu`, `jeux`, `list-unstyled`, `menu`, `propositions`, `zone-jeu` (methode :
sortie de `classesDesAncetresDePressables` sur `src/main/resources`, relevee par une sonde jetable non
commitee). Aucun nom d'habillage n'est ecrit dans le test : **`cabinet` entrera dans cette population
le jour ou le gabarit le posera**, sans que ce fichier change.

L'element pressable lui-meme est exclu : une decoupe sur le bouton ou sur son pseudo-element
decoratif est le voile du rouleau de S7-1, legitime et deja garde par la clause 8 d'`ADR-0027`.

**Ce que cette clause vaut aujourd'hui, dit franchement** : aucune feuille maison ne declare de
propriete de rognage, donc **sur la feuille livree elle ne mord sur rien**. Ce n'est pas pour autant
un vert a vide de la mauvaise espece, et la mutation 4 ci-dessous le montre : sa population d'ancetres
est reelle, et une propriete de rognage posee sur `.propositions` - un ancetre reel, aujourd'hui - la
fait rougir immediatement. **Mesure annexe** : la feuille tierce vendorisee ne declare ni `overflow`
ni `clip-path` sur aucune des neuf classes (methode : balayage des blocs de `bootstrap.min.css`
contenant l'une des deux proprietes, intersection de leurs classes avec les neuf). Le garde-fou porte
sur la moitie maison, celle que ce depot ecrit et peut faire regresser.

### 2. `LAnimationRetardeMaisNeDetermineRienTest`, clauses 9 et 10 - points 4 et 5

Clause 9 : le **sujet** du selecteur anime doit nommer une classe portee par un element
`[data-role=action]` dans un gabarit. Clause 10 : l'image finale egale l'etat de repos, avec la
comparaison geometrique decrite plus haut.

La clause 10 ferme deux autres verts silencieux que sa lecture rend visibles : **une animation qui
nomme des images cles inexistantes** (elle ne joue rien du tout, et les huit clauses precedentes la
laissaient passer), et **un `@keyframes` sans image finale**. Le nom des images cles n'est pas devine
par sa position dans le raccourci - l'ordre y est libre - mais derive : c'est celui des mots de la
declaration qui nomme des images cles reellement declarees.

Le decoupage de blocs CSS de ce fichier devient visible dans son paquet plutot que d'etre recopie dans
le garde-fou neuf : un second decoupage dans le meme module aurait ete une duplication sans motif.

### 3. Preuve de morsure - quatre mutations reelles

Chacune rejouee sur la version livree puis annulee ; `git status` confirme qu'aucune ne subsiste.

| Mutation | Attendu | Mesure |
|---|---|---|
| la valeur d'arrivee revient a `inset(80% 0 0 0)`, **l'etat livre en s07** | rouge sur la clause 10 | **rouge** : "l'image finale de "rouleau-glisse" laisse clip-path: inset(80% 0 0 0) alors que l'etat de repos de ".proposition::after" est clip-path: inset(0 0 100% 0)". Le defaut que seul le releve image par image de `nadia-prof` avait vu est desormais attrape par `mvn verify` |
| la comparaison geometrique remplacee par une **egalite de chaines** | la correction juste serait refusee | **2 cas en echec** : `derniereImageDeChaqueAnimationEgaleLEtatDeRepos` (sur la **production corrigee**) et `laMemeAnimationCorrigeeEtEcriteAutrementResteVerte`. C'est la mesure qui etablit que l'egalite de chaines n'est pas la propriete, plutot que de l'argumenter |
| l'animation posee sur `.propositions` au lieu de `.proposition::after` - le conteneur au lieu des colonnes | rouge sur la clause 9 | **rouge** : "le sujet de ce selecteur ne porte aucune classe d'un element pressable [...] le mouvement appartient aux colonnes et a elles seules". La clause mord sur une faute de production plausible, pas seulement sur un jeu fautif |
| `overflow: hidden` pose sur `.propositions`, **ancetre reel** des colonnes | rouge sur le garde-fou neuf | **rouge**, message nommant l'ombre portee de la bibliotheque et le conteneur de defilement. C'est ce qui distingue cette clause d'un vert decoratif : sa population d'ancetres est reelle et derivee |

**Mise a l'epreuve sur objets neufs (`ADR-0013` obligation 3)**, six jeux d'artefacts :

- `habillage-qui-s-anime` : un cabinet qui **pulse en attendant** - nommement le cas que `nadia-prof`
  declare fatal a son propre avis. L'animation y est par ailleurs irreprochable (elle finit, elle a
  son repli, elle exige le marqueur de geste, elle tient dans la barre) : les huit clauses
  precedentes la laissaient passer ;
- `derniere-image-hors-du-repos` : **la feuille telle que S7-1 l'a livree**, valeurs comprises ;
- `derniere-image-ecrite-autrement` : la meme corrigee, et **ecrite autrement que l'etat de repos**.
  C'est le cas qui empeche la clause 10 d'etre une egalite de chaines deguisee en propriete de rendu ;
- `images-cles-inexistantes` : une animation dont le `@keyframes` a ete renomme ;
- `habillage-qui-rogne` : **la feuille et le gabarit proposes pour le cabinet, recopies tels
  qu'`ines-design` les ecrit**, `overflow: hidden` compris ;
- `habillage-sans-rognage` : les memes, corriges par le seul retrait du rognage - le cadre, le fond,
  les coins arrondis, le bandeau et le socle restent. Sans ce cas, la clause serait une interdiction du
  cabinet deguisee en clause de focus.

## Ce que j'ai refuse d'ecrire, avec la mesure qui motive le refus

**Une clause interdisant a un ancetre de pressable de declarer une propriete de texte heritee**
(`font-size`, `color`, `line-height`...). Elle etait tentante : si le cabinet declarait `font-size`,
les colonnes rapetisseraient et `TailleDesTextesEtBoutonsTest` continuerait de lire `2rem` sur
`.proposition`. Je la refuse, et voici le chiffre qui tranche : **les quinze blocs de la feuille maison qui declarent
une taille la declarent tous en `rem`** - dix a `1.25rem`, trois a `2rem`, deux a `1.5rem`, zero dans
une autre unite (methode : balayage des blocs de `recreo.css` contenant `font-size`, commentaires
retires, comptage par bloc et non par selecteur - une regle a quatre selecteurs compte pour un).
Une unite `rem` est relative a la racine du document, **pas a l'ancetre** : un
`font-size` sur le cabinet ne changerait donc rien a la taille des colonnes. La clause aurait interdit
quelque chose d'inoffensif, et l'aurait fait au nom d'une protection qu'elle n'apporte pas. Elle
redeviendra pertinente le jour ou une taille sera declaree en `em` - c'est le declencheur.

**Une clause qui verifie que le cabinet n'existe pas sur l'ecran de retour** (C4). Refusee : elle
n'aurait pu s'ecrire qu'en nommant `.cabinet`, c'est-a-dire en enumerant un perimetre et en citant
dans un test un nom que le code ne porte pas encore. La contrainte 1.d la remplace, et la dette est
nommee au point 4 plutot que couverte en apparence.

## Consequences

- La reserve unique de `nadia-prof` sur s07 est **corrigee et gardee** : la valeur est changee, et la
  famille entiere du defaut - une animation qui rend la main sur un autre etat que celui qui la suit -
  fait desormais echouer `mvn verify`.
- `lea-dev` implemente le cabinet **sans `overflow: hidden`**. Consequence de forme a assumer : les
  coins arrondis du cabinet ne rognent plus ce qui depasse a l'interieur. Si un debordement visible
  apparait aux angles, la reponse est une reponse de mise en page, qui appartient a `ines-design` sous
  `nadia-prof` - jamais un rognage sur un ancetre de bouton.
- **Mauvaise consequence assumee** : trois clauses de plus sur la feuille maison, donc trois facons de
  plus de rendre un lot rouge pour une raison qui n'est pas celle du lot. La clause 10 en particulier
  exigera, de toute animation future, que l'etat de repos de chaque propriete animee soit **declare** -
  sinon rouge. C'est une discipline d'une ligne, mais c'en est une, et elle n'etait pas demandee.
- Le cabinet entrera **automatiquement** dans la population du garde-fou de rognage. Personne n'aura a
  y penser, et c'est la seule raison pour laquelle cette regle est ecrite avant l'implementation.
- Ce qui reste a `nadia-prof` **s'allonge de rien** : les six points dont elle se declare seule garde
  restent les siens, moins les deux moities que je prends (l'animation sur le cabinet, et la fin du
  mouvement). Je ne prononce aucun Done pedagogique, et le cabinet n'a pas encore ete joue.
- Un octet de plus est change dans ce que voit un enfant, et un seul : la valeur d'arrivee du
  `@keyframes`. C'est S8-2, acte par `DEC-0017`, et la valeur est celle qu'`ines-design` a calculee.

## Alternatives considerees

**`overflow: clip` au lieu de `overflow: hidden`.** Techniquement meilleure - elle ne cree pas de
conteneur de defilement, donc supprime le second effet. Ecartee : elle rogne toujours l'ombre portee,
donc le premier effet demeure, qui est celui qui touche l'enfant au clavier. Le garde-fou refuse les
deux valeurs pour cette raison, et `overflow-clip-margin` n'est pas une reponse que je veux voir
dependre d'un reglage numerique face a une ombre dont l'epaisseur vient de la bibliotheque.

**Garder l'anneau de focus par un test de rendu.** Ecartee, meme analyse qu'`ADR-0025` et `ADR-0028` :
un test qui lance un navigateur rend la suite dependante d'un binaire que `mvn verify` ne peut pas
garantir. La moitie declarative est prise, la moitie de rendu reste a `nadia-prof`.

**Ecrire une clause de couleur pour couvrir tout C3.** Ecartee : la partie non animee de C3 (une
lumiere, un degrade, un etat au survol) n'a aujourd'hui **aucune population** dans la feuille maison,
et une clause sans objet est decorative. Le jour ou une transition ou un etat au survol apparait sur un
habillage, la question se rouvre avec un objet reel a controler.

**Attendre l'implementation pour ecrire les regles.** Ecartee, et c'est le choix de calendrier qui
compte : le defaut de rognage est **ecrit dans la proposition**, et une regle qui arrive apres le lot
arrive apres la revue qui l'aurait attrapee - ou ne l'aurait pas attrapee, comme la derniere image
d'une animation pendant tout le sprint 7.

## Verification

`mvn -B clean verify`, sur un JDK **21.0.10** designe par `JAVA_HOME` le temps de la commande (le
`java` du `PATH` de ce poste est un JRE 8 ; constat rendu par `bash outils/verifier-jdk.sh`) :
**BUILD SUCCESS**, les six modules du pom agregateur en SUCCESS.

**Chiffres et methode dans le meme paragraphe.** Comptage valide **apres un `clean`**, lu sur les
lignes `Results:` du journal, module par module : `domain` 43, `application` 7, `adapter-web` 162,
`adapter-content` 36, `bootstrap` 77 en surefire et 3 en failsafe. Total **328 tests**, contre 319 sur
cette branche avant ce commit : les 9 de plus sont les 6 cas neufs de
`LAnimationRetardeMaisNeDetermineRienTest` (21 puis 27) et les 3 de
`LHabillageNeRogneJamaisUnElementPressableTest`.

**Regles ArchUnit : 28, inchange.** Aucune des deux clauses de cet `ADR` n'est une `ArchRule` -
ArchUnit lit du bytecode, ni une feuille de style ni un gabarit n'y sont visibles. Methode identique a
celle d'`ADR-0028` : `grep` de la forme `ArchRule <nom> =` dans les `src/test/java` des six modules
declares au pom agregateur, execute dans ce worktree seul ; le meme balayage rend 27 annotations
`@ArchTest`, la 28e regle etant executee par un `.check()` explicite.
