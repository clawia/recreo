# ADR-0073 - Un item declare l'illustration qu'il montre par un identifiant, et la preuve de la ressource change de source

- **Statut** : Accepte. **Son garde-fou est ecrit, vert et mis a l'epreuve dans le meme commit** -
  `bootstrap/src/test/java/com/recreo/archi/LIllustrationDeContenuResoutEtAucuneNeDortTest.java`,
  **10 tests** : trois cas verts dont deux sur le materiau reel, six morsures dont cinq par mutation
  en memoire du materiau reel et une sur un jeu fautif commite.
- **Sprint** : s25. **Date** : 2026-08-31. **Auteur** : `theo-archi`.
- **Arbre mesure** : worktree `s25-theo`, branche partie de `round-two` a `130ba64`. **JDK
  21.0.10**, celui que la machine virtuelle des tests rapporte d'elle-meme et non celui qu'une
  toolchain declare (`ADR-0071`). Enveloppe : `bash outils/mvn-jdk21.sh`.
- **Amende `ADR-0036`** (clause 1 : une image est designee par un chemin litteral) et **`ADR-0014`**
  (une reference de ressource d'un gabarit s'ecrit en chemin litteral). Ne remplace rien.
- **Prolonge** : `ADR-0004` (mecanique decouplee du contenu), `ADR-0006` (le contenu ne porte pas de
  reglage de matiere), `ADR-0013` (perimetre derive, garde-fou mordant), `ADR-0051` (un garde-fou
  qui n'examine aucun objet le declare), `ADR-0058` (le critere qui justifie une forme d'item de
  plus), `ADR-0070` (aucune cle de schema n'est ignoree).
- **Ne decide aucun perimetre de sprint** (`camille-po`), **aucune pedagogie** (`nadia-prof`),
  **aucune apparence** (`ines-design`). Ce qu'un dessin represente, ce qu'un texte alternatif dit,
  et le fait qu'une ligne de programme soit ouverte ne sont pas ici.

---

## Contexte

### Le fait qui ouvre le dossier, et sa correction

`camille-po` ecrit, en instruisant s25, que *"le visuel demande une architecture qui n'existe pas -
aucun `ADR` ne dit comment un item porte une donnee de dessin"*. **La premiere moitie est exacte, la
seconde est fausse, et l'ecart n'est pas de detail** : ce n'est pas que le depot soit muet, c'est
qu'il **refuse deja** la forme evidente. Il vaut mieux le savoir avant d'ouvrir un lot que pendant.

**Mesure, et non lecture.** Sur l'arbre a `130ba64`, un attribut pose dans le fragment de manche :

```html
<img class="illustration" th:if="${illustration != null}"
     th:src="@{'/illustrations/' + ${illustration} + '.svg'}" alt="">
```

`bash outils/mvn-jdk21.sh -B -o -pl adapters/adapter-web -am test` : **349 tests, 2 echecs**,
deux classes rouges, et chacune nomme sa decision.

| Classe | Message |
|---|---|
| `RessourcesTiercesTest` | *"ADR-0014 : `fragments/manche.html` reference une ressource par une expression calculee ; une ressource de page s'ecrit en chemin litteral, sinon rien ne prouve qu'elle est servie hors ligne"* |
| `HabillageDesEcransTest` | *"la classe 'illustration', posee sur `<img>`, n'est nommee par aucune feuille servie : l'element retombe au rendu par defaut du navigateur"* |

*Perimetre : le module `adapter-web` et ses amonts, `-am` porte, jamais le depot. Reference sans la
mutation sur le meme arbre et la meme commande : 349 tests, 0 echec.*

**Il y a donc bien une decision qui gouverne, et elle dit non.** `ADR-0036` clause 1 - *"un attribut
`src` / `th:src` dont la valeur est un chemin ecrit en clair. Jamais un chemin construit par
concatenation ou par expression"* - a ete ecrite pour le **decor**, et elle y porte la condition C5
de `nadia-prof` : le jeu d'images du cabinet ne depend ni de l'item tire, ni de la colonne, ni de la
reponse attendue. Une illustration de contenu depend de l'item **par definition**. Les deux besoins
sont contradictoires **sous une seule regle**, et c'est la vraie question de cet `ADR`.

### Ce que la regle de chemin litteral achete, exactement

Elle n'est pas une preference de style. Elle achete trois choses, et il faut les nommer une par une
pour savoir laquelle on perd :

1. **La cible est enumerable.** On peut lire le gabarit et lister ce que le navigateur ira chercher.
2. **La cible existe comme fichier de `static/`** - la garantie hors ligne d'`ADR-0014`.
3. **La cible ne varie pas avec l'item** - la condition C5, qu'`ADR-0036` fait porter par cette
   clause faute d'un autre canal.

Une illustration de contenu renonce a (3) **volontairement** : c'est son objet. Elle ne renonce ni a
(1) ni a (2) - **elle les deplace**. L'ensemble des cibles reste enumerable, non plus depuis le
gabarit, mais depuis **la donnee** : les paquets servis sont sur disque, leurs items aussi, et un
balayage les lit.

**C'est la decision de fond de cet `ADR` : la preuve ne disparait pas, elle change de source.**

### Le second fait, mesure parce qu'on me demandait de l'estimer

`sofia-sm` a etabli a s24 le prix d'une seconde forme d'item : **13 garde-fous de contenu**, une
sonde deposee, un tri ecrit garde-fou par garde-fou. La question posee ici est ce que coute une
**troisieme** forme. Je l'ai mesuree au lieu de la deduire, par deux sondes completes, non commitees.

**Methode commune aux deux sondes** : arbre `s25-theo` a `130ba64`, `bash outils/mvn-jdk21.sh -B -o
test` sur la **racine** (reacteur complet, donc pas de question de `-am`), phase `test` - les tests
d'integration de `verify` ne sont pas dans ce compte. Reference sans sonde, meme commande, meme
arbre : **domain 78, application 13, `adapter-web` 349, `adapter-content` 355, `bootstrap` 125**,
soit **920 tests, 0 echec**.

**Sonde B - une troisieme forme d'item** (`Item permits Question, ReponseAEcrire, SondeIllustree`),
sa fabrique, et un paquet de figures planes de onze items servi depuis `contenu/`.

**Sonde A - une composante optionnelle de plus sur `Question`** (`Optional<String> illustration`),
sa lecture dans `QuestionContentSourceFactory`, et le meme paquet, de type `question`.

| | Arrets du compilateur | Classes rouges | Echecs | Erreurs |
|---|---|---|---|---|
| Reference | 0 | 0 | 0 | 0 |
| **Sonde B** - troisieme forme | **6** | **12** (`adapter-content`) | 22 | 1 |
| **Sonde A** - composante optionnelle | **0** | **4** (`adapter-content`) | 6 | 0 |

`adapter-web` (349) et `bootstrap` (125) restent a **0 echec** sous les deux sondes. Le prix se paie
entierement dans `adapter-content`, ce qui confirme sur une troisieme forme ce que `sofia-sm` avait
mesure sur la deuxieme.

**Les six arrets du compilateur sont le dividende de s24, et ils sont tous bruyants** : deux dans la
production (`BanditManchotSession.questionDe`, `SaisieSession.reponseDe`) et quatre dans les tests,
dont **trois dans le seul `ClassementDesItems`**. Le classement des formes ecrit une fois par
`sofia-sm` a concentre ce qui aurait ete disperse ; sans lui, chaque garde-fou aurait porte son
propre `switch`.

**Ce que la sonde B casse et que la sonde A ne casse pas.** Trois garde-fous rougissent sur la meme
cause, et ce n'est pas celle qu'on attend : `ChaquePaquetPorteUnIndiceTest.itemsDeclares`, **le
comptage textuel partage**, ne trouve **aucun** item dans le paquet de sonde. Son critere est ecrit
noir sur blanc : *"un item enumere porte `enonce` en premier champ, quelle que soit sa forme
(`ADR-0064`, lot 5a de s24)"*. Une forme d'item dont le premier champ est `illustration` sort du
comptage, et le message le dit exactement. **C'est un fait qu'aucune lecture ne donne** : il ne
figure ni dans le tri de s24, ni dans aucun `ADR`, et il contraint toute forme d'item future bien
au-dela du visuel.

---

## Decision

### 1. Un item declare une illustration par un **identifiant**, pas par un chemin, pas par des octets, pas par une adresse

La cle est `illustration`, sa valeur est un identifiant de l'alphabet `[a-z0-9-]+` : pas de point,
pas de barre oblique, pas de deux-points, pas de majuscule.

**Pourquoi pas un chemin.** Un chemin dans la donnee fait entrer trois choses dans le contenu :
l'agencement du serveur (`/illustrations/`), le format du fichier (`.svg`), et la possibilite
d'ecrire `../` ou `https://`. La premiere est une fuite de framework dans du contenu que
`nadia-prof` relit ; la troisieme est une porte ouverte. **L'alphabet ferme les trois d'un coup et
sans qu'aucune inspection de chemin soit ecrite nulle part** : un identifiant qui ne peut contenir
ni point ni barre oblique ne peut designer que le repertoire prevu.

**Pourquoi pas les octets dans le paquet.** Un dessin encode dans le YAML rendrait le paquet
illisible a la relecture pedagogique, ferait passer une image par le juge de chemins de poste de
travail (`ADR-0036` fait 2), et empecherait le navigateur de la mettre en cache.

### 2. Une illustration de contenu vit sous `static/illustrations/`, le decor reste sous `static/images/`

**Deux repertoires, deux populations, et c'est la clause qui porte tout le reste.** Le decor garde
`ADR-0036` clause 1 sans une virgule de changement, donc garde la condition C5. Les illustrations de
contenu sont gouvernees par la clause 3 ci-dessous. Aucune des deux populations n'est une liste de
noms : chacune est **un repertoire**, donc derivable.

Ce que cela interdit reellement a quelqu'un qui essaierait de mal faire : glisser une illustration
de contenu dans `static/images/` pour echapper au controle. Elle y serait alors soumise au critere
de chemin litteral, qu'elle ne peut pas tenir, et le gabarit qui la designe serait rouge.

### 3. La preuve change de source : elle se lit sur la donnee, plus sur le gabarit

**Amendement a `ADR-0014` et a `ADR-0036`.** Le critere de chemin litteral s'applique a toute
reference de ressource **dont la cible ne depend pas de l'item**. Il ne s'applique pas a
l'illustration de contenu, qui est prouvee autrement, et par une propriete plus forte que celle
qu'elle remplace, dans les deux sens :

- **tout identifiant declare par un paquet servi resout** vers un fichier de
  `static/illustrations/` - sinon l'enfant recoit une image cassee et rien ne le dit ;
- **tout fichier de `static/illustrations/` est declare** par au moins un item d'un paquet servi -
  sinon c'est du poids mort dans le fat jar.

Le second sens n'existait pas pour le decor. Cet `ADR` ne perd donc pas une garantie, il en echange
une contre deux.

**La forme de l'exemption dans `RessourcesTiercesTest` est decidee ici, parce que c'est l'endroit le
plus dangereux du lot.** Le chemin le plus court pour lever le rouge mesure plus haut est d'ajouter
une exemption large a ce garde-fou - et une exemption large perce la garantie hors ligne d'`ADR-0014`
pour tout le depot, pas pour l'illustration. **Une seule forme d'expression est admise** : celle dont
le prefixe litteral est **exactement** le repertoire d'illustrations de contenu, suivi d'un unique
terme calcule, suivi de l'extension litterale. Toute autre expression calculee reste refusee, y
compris une expression qui construirait le repertoire lui-meme. **Une morsure est due avec le lot** :
une seconde forme calculee, posee dans le meme gabarit, doit rester rouge. Sans cette seconde moitie,
la morsure prouverait que l'exemption existe, pas qu'elle est etroite.

### 4. Aucun texte dans une illustration de contenu

Un dessin ne porte ni `<text` ni `<tspan`.

**Le motif n'est pas graphique, il est de perimetre.** Le texte que l'enfant lit est juge par des
garde-fous qui derivent leur population soit des **paquets** (`AucunMotServiNeSEcarteDeSonAccentTest`,
le cliquet de `TYP-1` d'`ADR-0067`, `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest`), soit
des **gabarits**. Du texte pose dans le dessin n'est ni l'un ni l'autre : ce serait un **troisieme
angle mort**, ouvert le sprint meme ou le depot en compte deja deux, dont un que le
`relecteur-pedagogique` vient de chiffrer a onze occurrences.

**Ce que cette clause ne decide pas** : si une figure legendee est pedagogiquement souhaitable. Le
jour ou `nadia-prof` en demande une, **cette clause cede** - et les garde-fous de texte apprennent a
lire un SVG dans le meme geste, sans quoi le texte lu par l'enfant sortirait du controle. C'est un
declencheur ecrit, pas un interdit definitif.

### 5. Une illustration declaree porte son texte alternatif, declare par l'item

Un item qui declare `illustration` declare `illustrationTexteAlternatif`, et reciproquement. Les
deux cles se gardent l'une l'autre.

**Pourquoi sur l'item et pas sur le gabarit** : un `alt` ecrit dans le gabarit serait le meme pour
les onze figures d'un paquet, donc ne decrirait aucune. **Pourquoi obligatoire** : une image qui
porte la question de la manche ne parvient pas a une lecture d'ecran sans lui, et un `alt=""` sur une
image de contenu est une declaration fausse - il annonce une image decorative.

**Ce que cette clause ne decide pas, et je le route explicitement** : **ce que ce texte dit.** Un
texte alternatif qui nomme la figure donne la reponse - c'est la *contrefacon* que `nadia-prof`
demontre pour la description en texte, transposee mot pour mot au lecteur d'ecran. Le libelle
appartient a `nadia-prof` ; la possibilite qu'elle conclue qu'un jeu visuel est injouable sans la vue
et que cela doive etre dit au joueur appartient a `camille-po`. **Je decide qu'il en existe un ; je
ne decide pas ce qu'il porte.**

### 6. Le palier 1 n'est **pas** une troisieme forme d'item, et **pas** une troisieme mecanique

C'est la decision la plus structurante, et c'est celle qui economise le plus.

**Le critere n'est pas invente ici, il vient d'`ADR-0058`** : une forme d'item de plus se justifie
quand **la facon dont l'enfant repond** change. Elle a change entre choisir et ecrire. **Elle ne
change pas au palier 1** : l'enfant choisit parmi des propositions en texte, exactement comme au
bandit manchot. Ce qui change est **ce qu'il regarde avant de choisir**.

Cette categorie a deja un patron dans ce depot, et trois occurrences : `explication` (`ADR-0024`),
`partDesignee` (`ADR-0062`), `texteSupport` (`ADR-0050`). Une composante **optionnelle** de
`Question`, que la mecanique transporte sans jamais la lire, qu'un paquet qui n'en porte pas ne
remplit d'aucune valeur factice. `illustration` et `illustrationTexteAlternatif` sont la quatrieme et
la cinquieme.

**L'objection d'`ADR-0058` ne s'applique pas ici, et il faut le dire precisement plutot que de
l'esquiver.** Cet `ADR` refuse de poser les formes acceptees sur `Question` parce que *"y poser des
formes acceptees obligerait treize paquets de choix a porter une cle qui ne les concerne pas - la
dette 2 de la version precedente"*. **Une composante `Optional` n'oblige personne** : c'est
exactement ce qu'`ADR-0024` a tranche pour l'explication, et ce que les 27 paquets servis
demontrent - aucun ne porte de `partDesignee` factice.

**Et ce n'est pas non plus une troisieme mecanique.** Le chemin d'un champ de contenu jusqu'a l'ecran
est mesurable : `texteSupport` occupe **trois** endroits en production - une composante de
`Question`, un accesseur de trois lignes sur `BanditManchotSession`, un attribut de modele dans
`PartieController` - plus une branche de gabarit. Une mecanique visuelle qui redistribuerait des
propositions, tiendrait un score et rendrait un verdict serait une **copie** de `BanditManchot`, et
ce serait la dette n° 4 de `CLAUDE.md` par un autre chemin : huit classes qui ne font que deleguer.

**Ce qui me ferait changer d'avis**, ecrit maintenant pour que ce soit un declencheur et pas une
revision : un palier ou l'enfant **agit sur l'image** - le palier 2 de `nadia-prof`, propositions
positionnees sur le dessin, et a plus forte raison le palier 3. La le geste de reponse change, le
critere d'`ADR-0058` est rempli, et une forme d'item ou une mecanique de plus se justifie sur un
usage constate.

### 7. Ce que cet `ADR` ne decide pas

- **Le poids.** `ADR-0036` clause 5 laisse une dette explicite : *"le commit qui livre la premiere
  image illustree pose le cliquet de poids, sur la valeur qu'il installe"*. Elle reste ouverte et
  elle est **due au lot qui livre le premier paquet illustre**, pas a celui-ci. Tant que ce cliquet
  n'existe pas, rien n'empeche une illustration de 4 Mo, et c'est ecrit pour que personne ne croie le
  contraire.
- **Le format.** Le seul dessin maison du depot est un SVG de 1 883 octets
  (`static/images/cabinet-fronton.svg`), et la clause 4 suppose un format textuel pour etre
  verifiable. Je n'interdis pas le binaire ; je constate qu'un dessin geometrique - cadran, figure
  plane, piece - se fait en SVG, que le SVG est diffable et relisible, et que la clause 4 ne saurait
  pas lire un PNG. Un jour ou une illustration devra etre une photographie, la clause 4 ne dira rien
  d'elle et il faudra le decider a ce moment-la.
- **Le pli.** `nadia-prof` ecrit qu'elle ne sait pas si le pli tient avec une image et que personne
  ne le sait. C'est une mesure d'`ines-design` (`ADR-0028`), pas une clause d'architecture, et je ne
  la deguise pas en clause.

---

## Regle executable

**Ce n'est pas une regle ArchUnit**, et je l'ecris plutot que de laisser le silence trancher :
ArchUnit modelise des types Java et leurs relations ; les objets juges ici sont **des identifiants
d'un fichier de ressource, des fichiers de dessin et des repertoires**, dont aucun n'est un type.
C'est un garde-fou de balayage, **septieme motif distinct** de cette famille apres la declaration de
feuille de style (`ADR-0059`), la chaine issue du chargement d'une ressource (`ADR-0060`), la valeur
d'une constante compilee (`ADR-0061`), le document HTML servi (`ADR-0062`), la cle de schema
(`ADR-0070`) et la version de la machine virtuelle (`ADR-0071`).

**Fichier** :
`bootstrap/src/test/java/com/recreo/archi/LIllustrationDeContenuResoutEtAucuneNeDortTest.java`.
**Jeu fautif** :
`bootstrap/src/test/resources/contreexemple/LIllustrationDeContenuResoutEtAucuneNeDortTest/paquet-illustre-fautif/`.

> **Tout identifiant d'illustration declare par un paquet servi resout vers un fichier de
> `static/illustrations/`, tout fichier de ce repertoire est declare par au moins un item, aucun
> n'est confondu avec le decor, aucun ne porte de texte, et aucune illustration n'est declaree sans
> son texte alternatif.**

**Perimetre derive, jamais enumere** (`ADR-0013` obligation 1) :

- les **modules** viennent du pom agregateur, par `LesGardeFousSontMisALEpreuveTest.modulesDeclares`
  - reutilisee et non reecrite ;
- les **paquets** viennent du repertoire de contenu de chaque module, par
  `AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu`, **jamais du
  catalogue** : un paquet retire du catalogue mais laisse sur disque reste juge (`ChaquePaquetPorteUnIndiceTest`, s05) ;
- les **identifiants** viennent de SnakeYAML, la bibliotheque avec laquelle le chargeur de production
  lit ces memes fichiers ;
- les **fichiers** viennent des deux repertoires d'images de chaque module, jamais d'une liste.

**Le juge est une fonction pure** - `verifier(declarees, illustrations, decor)` - qui ne lit ni
disque ni classpath. C'est ce qui permet de le mordre en mutant le **materiau reel en memoire**
plutot qu'en fabriquant un depot fautif qui ressemblerait a ce que j'imagine (lecon de s05, septieme
occurrence).

### Ce que ce garde-fou examine aujourd'hui, honnetement

**Aucun paquet servi ne declare d'illustration, et `static/illustrations/` n'existe dans aucun
module.** Quatre des cinq clauses portent donc sur une **population vide**. Je ne le cache pas dans
un vert : `ADR-0051` l'oblige, et le fichier le declare.

Ce qui empeche ces clauses d'etre decoratives est en trois points, et chacun est verifiable :

1. **Elles mordent aujourd'hui**, six fois, dont cinq sur du materiau reel mute en memoire.
2. **Le zero est epingle.** `aucunPaquetServiNeDeclareEncoreDIllustration` **rougit** le jour ou le
   premier paquet illustre entre. Le plafond vaut exactement la taille de la population - c'est le
   cliquet exact qu'`ADR-0067` et `ADR-0013` exigent. Le lot qui pose ce paquet lit cet `ADR` au lieu
   de le decouvrir six mois plus tard.
3. **Une clause a, elle, une population non vide sur le materiau reel** : `leDecorExisteEtAucunItemNeLeDesigne`
   verifie la separation des deux populations sur le seul dessin maison du depot,
   `cabinet-fronton.svg`, et affirme ce perimetre non vide.

### Mesure de l'execution

`bash outils/mvn-jdk21.sh -B -o -pl bootstrap -am test
-Dtest='LIllustrationDeContenuResoutEtAucuneNeDortTest,LesGardeFousSontMisALEpreuveTest'
-Dsurefire.failIfNoSpecifiedTests=false` : **20 tests, 0 echec, BUILD SUCCESS**. Le `-am` est porte,
le perimetre est `bootstrap` et ses amonts, jamais le depot.

**Preuve que le cliquet mord sur le chemin de production et pas seulement sur une fixture** : un
fichier `figure-sonde.svg` depose sous
`adapters/adapter-web/src/main/resources/static/illustrations/`, sans qu'aucun paquet ne le declare,
rend **2 tests sur 10 rouges** - la clause de resolution *("aucun item d'aucun paquet servi ne le
declare")* et l'epinglage du zero. Le fichier a ete retire ; rien n'en subsiste dans l'arbre.

**Ce que ce garde-fou ne prouve pas**, ecrit avant qu'on me le reproche :

- **que l'image atteigne l'ecran.** Il prouve que la cible existe et qu'elle est unique ; la
  traversee jusqu'au HTML servi reste due au lot qui la livre, sur le patron des trois tests de
  traversee par champ (`ADR-0050` clause 6 et ses voisins) ;
- **que le texte alternatif soit juste.** Il exige qu'il existe et qu'il ne soit pas vide. Ce qu'il
  dit reste a `nadia-prof`, et aucun automate ne le jugera.

### Un trou que je m'inflige, et que je nomme plutot que de le laisser trouver

Ce garde-fou ecrit `"illustration"` et `"illustrationTexteAlternatif"` en litteraux de chaine. Vu
d'`ADR-0070`, ces deux cles sont donc **deja nommees par une source du depot** : un paquet qui les
porterait avant que la fabrique les lise ne serait plus attrape par `ADR-0070`, dont c'etait
precisement l'objet - *"la cle ecrite avant que son champ existe"*.

**La propriete n'est pas perdue, elle est deplacee.** Un tel paquet est rouge ici, parce que son
identifiant ne resout aucun fichier, et rouge une seconde fois par l'epinglage du zero. Elle est
deplacee, pas affaiblie - **mais elle n'est plus au meme endroit**, et quiconque lit `ADR-0070` doit
le savoir. C'est le prix de tout garde-fou qui nomme une cle a l'avance, et il vaut mieux l'ecrire
une fois ici que le redecouvrir a la troisieme.

### Compte des regles ArchUnit du depot : inchange a 31, et la methode avec le chiffre

Ce lot n'ajoute aucun champ `ArchRule`. **31 est le resultat du recensement du depot lui-meme**,
`LesGardeFousSontMisALEpreuveTest.toutesLesRegles`, sur les 168 fichiers de source de test des cinq
modules declares, execute le 2026-08-31 sur l'arbre `s25-theo`.

**Deux chiffres circulent, et je tranche avec la methode plutot qu'avec l'autorite.** `ADR-0070` et
`ADR-0071` ecrivent *"inchange a 30"* ; l'ouverture de s25 annonce 31. **Le recensement rend 31**, et
la cause de l'ecart est datable : `ADR-0072` a ajoute une regle apres l'ecriture d'`ADR-0071`, et les
deux `ADR` ont recopie un compte au lieu de le redemander. **Un grep de chemin rend 33 sur le meme
arbre**, et les deux de trop viennent du faux module de contre-exemple de
`LesGardeFousSontMisALEpreuveTest`, dont le chemin interne contient `src/test/java`. **C'est
exactement la cause nommee dans la lecon de s21** - *"un faux depot de contre-exemple"* - et elle se
represente ici, sur le chiffre suivant, deux mois plus tard. Le recensement fait foi ; un compte
recopie a la main dans un `ADR` pourrit.

---

## Consequences

- **Le lot qui livre le premier paquet illustre entre par une porte rouge**, et c'est voulu :
  l'epinglage du zero le force a lire cet `ADR`. Mauvaise consequence assumee - un auteur de contenu
  qui prendrait de l'avance sur le code verrait un rouge qu'il ne sait pas lever seul, exactement
  comme `ADR-0070` l'avait deja accepte.
- **`ADR-0036` clause 1 cesse d'etre universelle**, et sa portee est desormais *"toute reference dont
  la cible ne depend pas de l'item"*. La condition C5 n'est pas affaiblie : le decor est le seul
  objet de cette clause, et il n'a jamais eu d'autre objet.
- **Cinq composantes optionnelles sur `Question`**, contre trois avant. Le patron a une limite que
  je nomme : chaque composante de plus est une ligne de plus dans l'identite d'ecran
  d'`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`, et c'est ce garde-fou qui dira quand le
  record est trop large - il rougit deja a la sixieme, comme la sonde A l'a montre.
- **Une illustration sans texte alternatif est impossible**, et un texte alternatif sans illustration
  aussi. Aucun automate ne dira jamais si ce texte est le bon.
- **Le second sens de la resolution empeche le poids mort** : un dessin retire du contenu mais laisse
  sur disque fait rougir la CI au lieu de voyager dans le fat jar.

---

## Alternatives considerees

1. **Une troisieme forme d'item** (`Item permits Question, ReponseAEcrire, QuestionIllustree`).
   Ecartee **sur mesure et non par gout** : 6 arrets du compilateur, 12 classes rouges, 22 echecs et
   1 erreur contre 4 classes et 6 echecs, sur le meme paquet et la meme commande. Et surtout ecartee
   sur le **critere d'`ADR-0058`**, qui n'est pas rempli : la facon dont l'enfant repond ne change
   pas. Elle redeviendra la bonne reponse au palier 2.
2. **Une image decrite dans le YAML sous forme de primitives** (une liste de segments, un type de
   figure, des coordonnees) rendue par le gabarit. Ecartee : c'est un moteur de dessin dans un
   gabarit Thymeleaf, une piece mobile de plus a chaque figure nouvelle, et cela ferait porter au
   contenu une geometrie que `nadia-prof` ne peut pas relire. Trois lignes de SVG lisible valent
   mieux qu'une abstraction de dessin.
3. **Une route de l'application qui sert l'image** (`/jeu/{id}/illustration`) plutot qu'un fichier
   statique. Ecartee : elle ajoute un point d'entree, un cache a gerer et un controleur, pour
   resoudre un probleme que personne n'a - le depot n'a aucune image dont l'acces doive etre
   controle. Elle redeviendrait la bonne reponse si une image devait un jour dependre de l'etat de
   la session, ce qu'aucune ligne du programme ne demande.
4. **Etendre le critere de chemin litteral en autorisant les expressions dont tous les termes sont
   litteraux sauf un.** Ecartee : c'est une regle qu'on comprend en lisant son implementation, et
   elle ne prouve toujours rien sur l'existence du fichier. La preuve par la donnee prouve
   davantage et se dit en une phrase.
5. **Ne rien decider et laisser le lot trancher.** Ecartee pour un motif mesurable : le lot aurait
   ecrit l'expression calculee, aurait vu `RessourcesTiercesTest` rouge, et le chemin le plus court
   pour lever ce rouge est **d'ajouter une exemption a `RessourcesTiercesTest`** - c'est-a-dire de
   percer la garantie hors ligne d'`ADR-0014` sous la pression d'une fin de sprint. Une decision
   ecrite avant le lot coute une demi-journee ; celle-la aurait coute une garantie.

---

## Amendement du 2026-08-31, meme jour - la clause 4 face au cadran et a la piece, et une clause 6 que je n'avais pas vue

`nadia-prof` a verifie ce commit avant de s'appuyer dessus, approuve la clause 4 sans reserve -
*"une figure legendee, non, jamais : c'est ma condition de contrefacon prise par l'autre bout,
l'image qui porte la reponse"* - et pose la seule question qui restait, en la posant exactement comme
il faut : **`HOR-2` exige les chiffres du cadran et `MON-1` des pieces reconnaissables ; si la
distinction est tenable dans un automate, ces deux lignes restent au palier 1 ; sinon elle ne l'est
pas, et elle prefere l'entendre dire.**

Elle verse un jugement, comme le sien :

> Un chiffre grave sur une piece n'est pas du texte que l'enfant lit - ce n'est pas un mot, il ne
> s'accentue pas, **c'est une partie de l'objet dessine, comme les aiguilles**.

### 1. Ce jugement est exact, et la clause 4 l'encode deja

**Sa phrase nomme le bon critere : dessine contre ecrit.** C'est mot pour mot ce que la clause 4
separe. Ce qui etait faux n'est pas son jugement, c'est une premisse que sa question et ma clause
partageaient sans l'avoir examinee : **qu'un chiffre visible exige un element `<text>`.** Il ne
l'exige pas. Un `2` trace en `<path>` est de la geometrie, exactement comme les aiguilles.

**Mesure, sur le chemin de production reel, et non sur une fixture.** Une piece de deux euros deposee
sous le repertoire d'illustrations de `adapter-web`, un paquet servi qui la declare, **la meme piece
deux fois, le meme chiffre visible, le meme nom de fichier** :

| Le chiffre est ecrit | Clause 4 |
|---|---|
| en `path`, avec les commandes de trace | **verte** |
| en element `text` | **rouge**, *"du texte pose dans le dessin echappe a tous les garde-fous qui jugent le texte lu par l'enfant"* |

*Perimetre : `bootstrap` et ses amonts, `-am` porte. Dans les deux etats le seul autre rouge est
l'epinglage du zero, qui est le signal voulu - le premier paquet illustre est arrive. La sonde a ete
retiree ; rien n'en subsiste dans l'arbre.*

**`HOR` et `MON` restent donc au palier 1, et je n'ai rien affaibli.** La clause 4 n'est pas touchee,
aucune exemption n'est ouverte, et le compte des morsures monte au lieu de baisser.

### 2. La distinction a l'interieur d'un element de texte, elle, n'est pas tenable - et je le mesure plutot que de l'affirmer

Il faut le dire pour que personne ne la reecrive dans six mois. **Tout critere qui admettrait le `2`
de la piece a l'interieur d'un element de texte admet aussi la reponse ecrite sur la figure.** Trois
familles essayees, mesurees sur le contenu servi - les **680 bonnes reponses** des 27 paquets du
repertoire de contenu de `adapter-content`, comptage a `130ba64` :

| Critere qu'on serait tente d'ecrire | Ce qu'il laisse passer |
|---|---|
| le contenu est purement numerique | **217 bonnes reponses sur 680 (31,9 %)** sont purement numeriques - un `8` pose sur un octogone donne la reponse a *"combien de cotes ?"* |
| le contenu fait au plus deux caracteres | **267 sur 680 (39,3 %)**, dont `Le`, `un`, `ce`, `sa`, `va` |
| le contenu ne porte aucune lettre | **308 sur 680 (45,3 %)** |

**Les trois echouent dans le meme sens et c'est le sens grave** : elles autorisent d'ecrire la
reponse sur le dessin, c'est-a-dire exactement la contrefacon que `nadia-prof` refuse. Aucune ne
distingue *"un chiffre qui fait partie de l'objet"* de *"un chiffre qui est la reponse"* : cette
difference n'est pas dans les caracteres, elle est dans le rapport entre le dessin et la question -
et ce rapport, aucune machine de ce depot ne le lit. **Une regle qui enumererait les cas admis - le
cadran, la piece - serait une liste d'exemples deguisee en regle**, la famille que ce depot a deja
sanctionnee.

C'est le meme refus que dans `ADR-0072`, et le meme motif : je ne peux pas formuler la propriete, je
le montre au lieu de l'affirmer, et je nomme ce qui la remplace.

### 3. Un fait qui renforce la reponse, et qui ne vient pas de moi

**Le depot ne vendorise aucune police et n'ecrit aucune regle de police telechargee** - verifie a
`130ba64` : zero fichier `.woff`, `.woff2`, `.ttf` ou `.otf` dans les modules, zero regle
`@font-face` dans la feuille maison comme dans la feuille tierce. Un element de texte dans un SVG est
donc rendu avec la police que l'appareil possede, a ses metriques : **la position et la taille des
chiffres d'un cadran ne seraient reproductibles sur aucun appareil.** Tracer les chiffres n'est pas
une concession arrachee par ma clause, c'est ce qu'un dessin independant de l'appareil demande de
toute facon.

### 4. La troisieme voie - la porte de `nadia-prof` porterait la distinction - est refusee, et pour un motif mesure

Elle est defendable en apparence : `nadia-prof` juge deja la contrefacon par description et jugera le
texte alternatif. **Elle est refusee ici, et le motif n'est pas la mefiance.**

L'objet meme de la clause 4 est que du texte pose dans un dessin **echappe aux garde-fous
automatiques** qui jugent le texte lu par l'enfant - les accents, le cliquet de `TYP-1`, la notation
d'`ADR-0060`. Router la distinction vers une porte humaine ne ferme pas ce trou : **elle le laisse
ouvert et lui ajoute un juge.** Et le depot a mesure ce que vaut la relecture humaine seule sur cette
matiere exacte : le `relecteur-pedagogique` a compte **onze** espaces ordinaires devant un signe
double dans le texte rendu, **trois neuves a s24**, sous une porte technique verte et apres relecture.
Ce n'est pas un reproche a la relecture, c'est la raison d'etre d'un cliquet.

Ecrire *"pas de texte, sauf si `nadia-prof` l'accepte"* serait une exemption sans perimetre derivable
- une regle qui ne garde rien, et qui aurait en plus l'apparence d'en garder une. **La reponse
honnete etait dans la question de depart : la distinction dessine contre ecrit est tenable, et c'est
la seule qui le soit.**

### 5. Clause 6 - un item illustre declare `enonce` comme premiere cle

Elle vient d'une lecture de `nadia-prof` que ma mesure ne donnait pas, et elle lui est due.

J'avais mesure qu'une **forme d'item** dont le premier champ n'est pas `enonce` sort du comptage
textuel partage (`ChaquePaquetPorteUnIndiceTest.itemsDeclares`, `ADR-0064`), et que le paquet entier
tombe alors a zero item - **bruyamment**. Elle en tire le cas **partiel**, qui est le dangereux : un
paquet dont **certains** items commencent par `illustration` est compte **en partie**. Les conditions
de longueur cessent d'etre executees sur les items manquants, et **deux des huit garde-fous qui
partagent ce comptage ne portent pas la clause de taille** qui le rattraperait (routage 2 du rapport
qualite de s24). Ses mots : *"c'est la forme de `CPL-3`"* - la condition ecrite que rien n'executait,
qui a coute un refus a s24.

**Decision** : un item qui declare une illustration declare `enonce` comme **premiere** cle.

**Sa morsure est la seule du fichier qui ne peut pas etre jouee en memoire** : elle depend de l'ordre
des cles tel que SnakeYAML le rend. Le jeu fautif commite porte donc une cinquieme faute, un item qui
commence par `illustration`, et le test qui le traverse l'exige nommement.

### 6. Ce que cet amendement change au compte

`LIllustrationDeContenuResoutEtAucuneNeDortTest` passe de **10 a 11 tests** : sept morsures au lieu
de six, et un jeu fautif commite qui porte cinq fautes au lieu de quatre. **Aucune clause n'est
retiree, aucune exemption n'est ouverte, aucune regle ArchUnit n'est ajoutee** - compte du depot
inchange a 31, par le meme recensement.

### 7. Ce que cet amendement ne decide pas

**Que les chiffres traces soient producibles.** Je l'ai demontre sur une piece, a la main. Un cadran
de douze chiffres est un autre volume de travail, et **c'est `ines-design` qui dit si c'est
producible**, pas moi. Si sa reponse est non, la contrainte mord pour de bon et la conclusion
redevient celle que `nadia-prof` avait envisagee. **Ma reponse est donc precise : la distinction est
tenable dans une regle executable, et la ligne tient si le trace tient.** Je ne fais pas passer pour
un verdict d'architecture ce qui depend d'une main que je n'ai pas.

**Une limite que je nomme plutot que de la laisser trouver** : rien n'empeche quelqu'un de tracer une
**phrase entiere** en `path`. La clause 4 ne la verrait pas. Je n'ecris pas de garde-fou contre ce
cas - il faudrait mesurer une longueur de trace ou compter des courbes, c'est-a-dire une liste
d'exemples sous forme de nombre - et **ce residu-la, lui, reste a la porte de `nadia-prof`**. La
difference avec la section 4 est de taille et non de nature : router *"un mot legende, ou pas"* a la
porte, c'est lui donner tout le probleme ; router *"quelqu'un a-t-il dessine une phrase a la main"*,
c'est lui donner ce qu'aucune machine ne peut prendre.

**Une consequence de planning que je nomme sans la decider** : `nadia-prof` releve sa capacite de
trois a quatre paquets au motif que le palier 1 n'ouvre pas de mecanique au sens de `SEUIL-1` a
`SEUIL-5`. C'est une decision a elle, sur un fait que cet `ADR` etablit. Le rang des lignes reste a
`camille-po`.
