# ADR-0024 - Faire porter l'explication d'un item par le verdict de la manche jouee, et laisser la graphie qui la justifie dans le paquet qui la declare

- **Statut** : Accepte
- **Date** : 2026-08-07
- **Auteur** : theo-archi
- **Sprint** : s06

## Contexte

Le lot "Le bon son" (S6-6 et S6-7, indivisible sur arbitrage de `nadia-prof`) demande deux choses qui
n'en font qu'une techniquement.

**S6-7 veut qu'un texte atteigne l'ecran de retour**, sur les seuls items dont la graphie differe de
la notation du son, systematiquement, que l'enfant ait repondu juste ou faux, et a chaque occurrence
de l'item - "Pas 'une seule fois' non plus" (avis de conception du 2026-08-06). Ce texte n'existe
nulle part : `Verdict` porte `(boolean juste, String bonneReponse)` et rien d'autre.

**S6-6 veut que chaque item declare la graphie qui porte le son**, pour qu'on puisse determiner
automatiquement si l'item est regulier "sans avoir a compter les lettres du mot". C'est le besoin que
`nadia-prof` m'a explicitement adresse. J'ai mesure en instruisant la story que ce champ serait
**ignore en silence** : `YamlContentPackLoader.chargerPaquet` valide l'enveloppe puis delegue le corps
a `QuestionContentSourceFactory`, qui lit `enonce`, `bonneReponse`, `distracteurs`, `indice` - et
**aucune cle inconnue n'est refusee**. Un auteur ecrirait quinze declarations de graphie, verrait sa CI
verte, et rien n'en sortirait. C'est le defaut d'`ADR-0016` dans l'autre sens : la, une abstraction
existait qu'un appelant sur deux remplissait ; ici, le contenu remplirait un champ que personne ne lit.

Les deux stories partagent donc un seul champ de contenu et une seule decision. J'avais refuse
d'ecrire cet ADR trois jours avant l'ouverture du lot, en disant pourquoi : la question "ou vit ce
texte" avait deja sa reponse (le contenu, `DEC-0005`, `ADR-0016`), mais "par quoi il transite" en
avait deux, et je penchais pour l'une sans l'avoir verifiee. **Je l'ai verifiee en ouvrant le lot, et
la lecture du code tranche plus nettement que mon inclination.**

### Ce que la lecture du code a change

Mon avis de faisabilite posait deux sorties equivalentes en cout - `Verdict` gagne une composante, ou
`BanditManchotSession` expose un accesseur separe sur le modele exact d'`indice()` - et je les
separais par un argument de semantique (deux moments d'affichage distincts). Cet argument tient, mais
il n'est pas le bon : il y a une raison **structurelle**, et elle exclut l'accesseur.

`BanditManchotSession.jouer` construit le verdict de la manche jouee, puis appelle `tirerManche` : la
`mancheCourante` de la session rendue est **la suivante**. L'item que l'enfant vient de lire n'est plus
atteignable depuis la session - sauf par le verdict, qui en est la seule trace. Un accesseur
`explication()` serait donc un **champ parallele** au verdict, a remplir en phase avec lui a chacune
des quatre constructions de la session. C'est exactement la forme du defaut d'`ADR-0016` : deux choses
a remplir ensemble, dont une se remplit toute seule quand on y pense. `indice()` peut etre un champ de
session parce qu'il porte sur la manche **courante**, que la session tient ; l'explication porte sur la
manche **passee**, que la session a laissee partir.

## Decision

**Trois gestes, et le troisieme est une frontiere plus qu'un ajout.**

1. **`Question` gagne une composante optionnelle, `explication`, sans nom de matiere.** Un texte que
   seul le contenu ecrit, que la mecanique transporte sans le lire, et qui s'affiche sur l'ecran de
   retour apres chaque reponse a cet item. La mecanique ne decide jamais qu'un item merite une
   explication : le contenu en porte une ou n'en porte pas. C'est la seule ecriture qui preserve
   `ADR-0004` - ecrite autrement, le critere "aucun texte sur les items reguliers" serait une
   condition dans la mecanique, qui saurait alors quelque chose du francais.

2. **`Verdict` gagne une troisieme composante obligatoire, `Optional<String> explication`**, remplie
   par `jouer` a partir de l'item joue. Obligatoire veut dire : **aucun constructeur de commodite**.
   Un site ne peut pas construire un verdict sans decider de l'explication, et le compilateur le
   refuse. C'est le contraire du choix fait pour `Question`, et l'asymetrie est deliberee - voir plus
   bas.

3. **La graphie n'entre ni dans le domaine, ni dans une fabrique partagee.** Elle reste une
   **declaration d'auteur dans le paquet** qui en a besoin, relue par le garde-fou, jamais par le code
   de production. La faire entrer dans `Question` obligerait chaque paquet de mathematiques a porter un
   champ de francais, et c'est mot pour mot la dette 2 de la version precedente dans l'autre sens : une
   configuration taillee pour l'arithmetique que chaque contenu de francais devait remplir de valeurs
   factices. La faire lire par `QuestionContentSourceFactory`, partagee par toutes les matieres, a le
   meme effet un cran plus bas.

**Ce que le troisieme geste implique et qu'il faut dire franchement** : "ce champ existe et permet de
determiner automatiquement si l'item est regulier" (troisieme critere de S6-6) est tenu par un
**garde-fou**, pas par le chemin d'execution. C'est la lecture litterale du critere - la determination
est automatique - et c'est la seule qui n'oblige pas le produit a transporter une donnee dont il n'a
aucun usage. Le runtime n'a jamais besoin de savoir qu'un item est a variation : il affiche
l'explication que le contenu porte, un point.

### Pourquoi `Verdict` n'a pas de constructeur de commodite et `Question` en garde un

Parce que les deux populations d'appelants n'ont pas la meme forme, et que l'une est gardee et
l'autre non.

`new Verdict(` existe a **cinq** sites (mesure : `git grep -o "new Verdict(" -- "*/src/main/*"
"*/src/test/*"` sur les cinq modules du pom agregateur, a `cf48cae` : un en production dans
`BanditManchotSession.jouer`, quatre dans `BanditManchotTest`). Cinq sites qui decident tous
explicitement : le cout est nul et la garantie est celle du compilateur.

`new Question(` existe a **cinquante-six** sites (meme methode, meme commit : 1 en production dans
`QuestionContentSourceFactory`, 1 dans `AdditionsBorneesContentSource`, 54 dans les tests des cinq
modules). Mon avis de faisabilite en annoncait **42**, mesures a `095547c` : l'ecart de quatorze est
entierement du au lot de `marc-dev` fusionne depuis (douze items de conjugaison et deux items
d'homophones epingles dans `PaquetsFrancaisS04Test`), et je l'ai reverifie en comptant aux deux
commits. Cinquante-six sites obligeraient a un constructeur de commodite a cinq arguments sous peine
de rejouer la dette 6 de la version precedente - un record large ou ajouter un champ oblige a editer
quinze appels. Il est donc ajoute dans le meme geste que la composante.

Ce constructeur laisse l'explication vide en silence, et c'est **precisement par la** que le defaut
d'`ADR-0016` etait entre. Il n'est acceptable que pour une raison verifiable : un paquet qui devait
porter une explication et n'en porte pas fait rougir
`ChaquePaquetExpliqueSesItemsAVariationTest`, qui tire ses items par la chaine reelle. Le silence du
constructeur est rattrape par un automate, pas par la vigilance.

## Regles executables

Trois fichiers, dans le meme commit que la decision.

### 1. `adapters/adapter-content/src/test/java/com/recreo/adapter/content/ChaquePaquetExpliqueSesItemsAVariationTest.java`

Le garde-fou central. Perimetre derive du **repertoire de contenu** (`ADR-0016`, obligation 1
d'`ADR-0013`) : un paquet neuf entre dans le controle du jour ou il est deposé. Pour chaque paquet
dont au moins un item declare `graphie` **ou** `explication`, et pour chacun de ses items :

- la graphie est declaree et non vide ;
- l'item est retrouve parmi ceux que le **chargeur reel** rend, et non relu dans le YAML : c'est en
  chemin que l'explication pourrait se perdre, comme l'indice se perdait avant `ADR-0016` ;
- si la graphie differe de la reponse attendue, l'explication est presente, non vide, et **nomme le mot
  de son propre item** ;
- sinon, l'explication est **absente** ;
- les explications d'un meme paquet sont distinctes entre elles.

**Les deux cles se gardent l'une l'autre, et c'est ce qui rend ce garde-fou non contournable par une
faute de frappe** - le point le plus important de sa conception, puisque le chargeur, lui, continue
d'ignorer les cles inconnues. Un paquet entre dans le controle des que **l'une** des deux cles apparait,
et le controle exige alors les deux : `grafie` partout rend tous les items reguliers, donc leurs
explications interdites, donc rouge ; `explicaton` partout laisse les items a variation sans
explication, donc rouge.

**Ce que ce garde-fou ne verifie deliberement pas.** Il ne verifie pas que le texte contient la graphie
ni le son. Ce sont des suites de deux lettres : "an" est dans "Dans", "en" est dans "DENT". Un tel
controle passerait pour une raison etrangere a son objet - la faute que j'ai etablie en s05 sur la
detection d'un `!important`, qui cherchait une sous-chaine qu'un nom de variable de la bibliotheque
contenait. Le meme piege m'a d'ailleurs guette sur ma propre condition de sortie de perimetre : chercher
la sous-chaine "graphie" dans le corps du fichier rougirait sur le mot francais "orthographie", qu'une
competence de programme peut parfaitement porter. Le controle porte donc sur les **cles** de l'arbre
YAML, pas sur son texte. Le texte lui-meme reste juge par `nadia-prof`, comme S6-7 le dit.

### 2. `domain/src/test/java/com/recreo/domain/archi/AucunVocabulaireDeMatiereTest.java` (etendu)

Le troisieme geste de la decision - la graphie n'entre pas dans le domaine - n'etait garde par rien.
Ce test balayait les seules **mecaniques** ; son perimetre s'etend aux **types partages de
`domain/content`** (le paquet racine : `Item`, `Question`, `ContentDescriptor`, `ContentSource`,
`ItemId`, `RandomSource`), et son vocabulaire recoit "graphie", "phoneme", "nasale".

Le sous-paquet `content/source` est **volontairement** hors perimetre, et ce n'est pas une exception
concedee : `ADR-0006` y loge les bornes d'une matiere dans la source qui en a besoin, et
`AdditionsBorneesContentSource` nomme donc legitimement une somme et un operande. La frontiere que
trace ce perimetre est exactement celle de la decision - partage, donc neutre ; propre a une matiere,
donc libre.

Le vocabulaire reste une liste ecrite a la main, ce qu'`ADR-0013` permet pour un **critere**. Un mot n'y
entre qu'a deux conditions verifiees a la main : il est specifique a une matiere, et il n'est la
sous-chaine d'aucun mot courant. C'est pourquoi "son" n'y entre pas - il est dans "raison",
"comparaison", "saison".

Ce test **sort de la `DETTE_DE_BALAYAGE`** de `LesGardeFousSontMisALEpreuveTest` dans le meme geste :
il recoit deux arbres de sources fautifs, un par perimetre, plus un corrige. Le plafond passe de 3 a
**2**, la taille exacte de la dette restante.

### 3. Les tests de transport : `BanditManchotTest`, `PartieControllerTest`

Deux methodes dans `BanditManchotTest` (domain) : l'explication de l'item joue voyage dans le verdict
sur reponse juste comme sur reponse fausse, un item sans explication n'en fait pas apparaitre une, et
un item **represente** apres trois echecs (aide de S3-2) porte encore la sienne - l'exigence "pas une
seule fois" tenue par construction, sans aucun etat "deja montre". Le texte de ces tests est
volontairement absurde ("xyzzy 42") : un texte plausible ne distinguerait pas le transport d'une
fabrication.

Une methode dans `PartieControllerTest` (adapter-web) : l'ecran de retour recoit l'explication sous
l'attribut `explication`, et l'attribut est present et vide quand l'item n'en porte pas.

### Pourquoi aucune de ces regles n'est une `ArchRule`

ArchUnit lit du bytecode. Aucune des trois proprietes n'y est visible : la premiere porte sur le
contenu de fichiers YAML, la deuxieme sur des **litteraux** dans des sources (ArchUnit voit les types,
pas les mots - c'est le motif d'existence de ce test depuis `ADR-0004`), la troisieme sur des valeurs
qui circulent a l'execution. Le compte de regles ArchUnit du depot est donc inchange a **28**, et cet
ADR ne pretend pas le contraire.

### La preuve que ces garde-fous mordent

Toutes les mesures sur un JDK 21.0.10 designe par `JAVA_HOME` le temps de la commande.

**Mutation 1 - sur materiau reel, entree automatique dans le perimetre.** Chacun des 21 items du paquet servi
`cp-le-bon-son.yaml` recoit une `graphie` - egale a sa reponse attendue, sauf les items du son `an` qui
recoivent `en`, de quoi produire des items a variation - et aucun ne recoit d'explication. Aucune inscription
nulle part. Resultat : `BUILD FAILURE`,

```
paquet cp-le-bon-son, item "MAMAN" : sa graphie "en" differe de la reponse attendue "an", c'est donc
un item a variation et l'enfant doit voir les lettres qui font ce son sur l'ecran de retour
```

C'est la preuve du perimetre (obligation 3 d'`ADR-0013`) faite sur le paquet reel plutot que sur une
fixture de ma main, et c'est aussi la demonstration que le lot de `lea-dev` sera juge des son premier
item, sans qu'elle ait a inscrire son paquet quelque part.

**Mutation 2 - la faute de frappe, celle que le chargeur ne verra jamais.** Sur le meme paquet reel, un
seul item recoit `grafie` (mal orthographie) **et** son explication. Resultat : `BUILD FAILURE`,

```
paquet cp-le-bon-son, item "MAMAN" : ce paquet declare la graphie de ses items, donc tous. Sans elle,
rien ne dit si cet item demande une explication, et une cle mal orthographiee passerait inapercue
```

C'est la mesure qui justifie le croisement des deux cles. Sans lui, cette mutation serait **verte** :
le chargeur ignore `grafie` sans un mot, et l'auteur croirait avoir declare quinze graphies.

**Trois jeux fautifs, un par bord du critere conditionnel**, sous
`src/test/resources/contreexemple/ChaquePaquetExpliqueSesItemsAVariationTest/` : un item a variation
sans son texte ; un item **regulier avec** un texte, que tout critere ecrit sur la seule presence
laisserait passer ; un texte present mais qui ne nomme aucun mot, celui que produit un copier-coller.
Plus un corrige qui doit passer, et qui epingle l'autre borne - un garde-fou qui exigerait un texte
partout, ou qui refuserait le mot en majuscules, le refuserait.

**Deux arbres fautifs pour le perimetre etendu** d'`AucunVocabulaireDeMatiereTest` : une mecanique qui
compte des syllabes, et un `QuestionAvecGraphieContreExemple` qui pose la graphie sur le type partage -
la tentation exacte que cette decision refuse. Plus un corrige.

**Etat de la suite apres ces ajouts** : `mvn -B clean verify`, `BUILD SUCCESS`, **271 tests**, 0 echec.
Methode du comptage plus bas, dans la section de verification.

## Consequences

- `lea-dev` recoit une decision executable : le schema d'un item de type `question` accepte
  `explication` (lue) et `graphie` (declaration d'auteur), le verdict porte le texte jusqu'au modele
  sous l'attribut `explication`, et le garde-fou dira ce qui manque avant `nadia-prof`. Ce qui reste a
  son lot est ecrit dans la section suivante.
- **Le chargeur continue d'ignorer en silence toute cle inconnue.** C'est la dette de cet ADR, et elle
  est reelle : ce que le croisement des deux cles ferme, c'est la cle **de ce paquet-ci**, pas la
  classe entiere du defaut. Un `distracteus` mal orthographie sur un item echouerait bruyamment
  (`distracteurs` est obligatoire), mais un `indce` passerait - rattrape par `ChaquePaquetPorteUnIndiceTest`,
  qui est un autre garde-fou et non le chargeur. **Ce qui me ferait fermer la dette** : un second
  paquet ayant besoin d'une declaration d'auteur. A deux usages constates, un mecanisme de declaration
  explicite dans l'enveloppe du paquet (le chargeur refuse toute cle qui n'est ni connue de la fabrique
  ni declaree) se justifie ; a un seul, c'est de la generalisation speculative, et je m'y refuse par la
  meme regle qui a tue `RuleSet`.
- `Question` porte desormais six composantes et deux constructeurs de commodite. C'est le debut de la
  pente de la dette 6, et je le note pour que la prochaine composante soit refusee ou traitee
  autrement : a sept, il faudra un constructeur nomme ou un objet de valeur intermediaire, pas un
  troisieme raccourci.
- **Mauvaise consequence assumee** : le critere "l'explication nomme le mot de son item" suppose que
  l'enonce **est** un mot. Il l'est dans tout paquet qui declare une graphie, par definition de la
  convention. Applique un jour a un paquet dont l'enonce est une phrase entiere, ce critere serait
  absurde ; il ne s'appliquera pas, puisque le perimetre est "declare une graphie", mais la limite est
  ecrite ici plutot que decouverte.
- Le champ `graphie` n'est visible d'aucun code de production. Un outil futur qui voudrait s'en servir
  (statistiques de couverture par graphie, par exemple) devra relire le YAML. C'est le prix du refus de
  polluer les types partages, et il est plus faible que ce que la pollution couterait.

## Ce qui reste au lot de `lea-dev`, et qui n'est pas dans ce commit

Je m'arrete au modele. Ne sont **pas** dans ce commit, et ne sont pas des decisions d'architecture :

1. Les quinze items de `cp-le-bon-son.yaml` avec leur `graphie`, leur `explication` sur les six items a
   variation, et leurs quinze indices reecrits (le plafond de dette d'`ADR-0016` vaut 0 : le paquet ne
   peut pas etre recompose en deux commits dont le premier laisserait des items sans indice).
2. L'affichage dans `fragments/retour.html`, conditionne a la presence de l'attribut `explication`.
3. Le resserrement de `SONS_ATTENDUS` a trois sons dans `PaquetsFrancaisS04Test` - la liste reste, son
   contenu se reduit ; la supprimer rendrait le garde-fou incapable de refuser un quatrieme son.
4. **Un test de rendu** qui prouve que le texte atteint l'ecran de retour d'un item reel. Le mien
   s'arrete au modele, et je le dis dans sa Javadoc ; celui-la doit faire jouer la reponse reelle d'un
   item reel a travers la frontiere HTTP, comme l'amendement du 2026-08-06 a `ADR-0013` l'exige des
   donnees d'un garde-fou.

## Alternatives considerees

**Un accesseur `explication()` sur `BanditManchotSession`, sur le modele d'`indice()`.** Ecartee, et
c'etait mon inclination avant lecture : elle impose un champ parallele au verdict, a tenir en phase a
chacune des quatre constructions de la session, parce que la session ne tient plus l'item joue apres
`jouer`. C'est la forme du defaut d'`ADR-0016`, et le precedent d'`indice()` ne s'applique pas -
l'indice porte sur la manche courante, que la session tient.

**`graphie` dans `Question`, lue par `QuestionContentSourceFactory`.** Ecartee : dette 2 de la version
precedente, retournee. Chaque paquet de mathematiques porterait un champ de francais, et la fabrique
partagee par toutes les matieres connaitrait un mot de phonetique. C'est desormais **interdit par un
automate** et non par une recommandation (regle executable 2).

**Rendre le chargeur strict sur les cles d'item** (refuser toute cle inconnue). Ecartee **pour
aujourd'hui**, avec regret, parce qu'elle attaque la cause : elle exigerait que la fabrique partagee
connaisse `graphie`, ce que l'alternative precedente vient d'interdire, ou qu'un mecanisme de
declaration soit invente pour un seul usage constate. Le declencheur qui me ferait changer d'avis est
ecrit dans les consequences.

**Deriver "item a variation" en comptant les lettres du mot** plutot qu'en declarant la graphie.
Ecartee, et ce n'est pas mon avis mais celui de `nadia-prof`, cite dans le critere : "sans avoir a
compter les lettres du mot". Techniquement, ce serait en plus fragile - un automate qui decide seul que
"DENT" fait le son "an" par ses lettres est un moteur de phonetique francaise, la derniere chose que ce
depot doive contenir.

**Un texte porte par la mecanique, choisi selon que l'item est regulier.** Ecartee : la mecanique
saurait alors ce qu'est une graphie reguliere, et `ADR-0004` tombe. Le principe directeur du produit
tient ici a un detail d'ecriture, et c'est ce detail que la decision fixe.

## Verification

`mvn -B clean verify` sur un JDK 21.0.10 designe par `JAVA_HOME` le temps de la commande (le `java` du
`PATH` de ce poste est un JRE 8 qui ne compile pas ; `outils/verifier-jdk.sh` rend le constat citable) :
**BUILD SUCCESS**, les cinq modules du pom agregateur en SUCCESS.

**271 tests, 0 echec, 0 erreur, 0 ignore.** Methode : somme de l'attribut `tests` de la balise racine
`<testsuite>` des **49 rapports XML** produits par la commande - 46 sous `target/surefire-reports/` et 3
sous `target/failsafe-reports/`. Les rapports texte ne sont pas comptes : deux moteurs JUnit tournent et
le rapport texte n'en garde qu'un, source de la divergence 29 / 25 de s05. Le comptage est fait **apres
le `clean`** de cette commande, et non sur des rapports laisses par les executions mutees ci-dessus.
Repartition : `adapter-web` 115, `bootstrap` 71 + 3, `domain` 43, `adapter-content` 32, `application` 7.
Progression : **259 a la fusion de la premiere vague -> 260 apres ADR-0023 (trois methodes neuves, deux
methodes enumerees supprimees) -> 271 apres ADR-0024**.

**28 `ArchRule` declarees, inchange.** Methode : expression `ArchRule <identifiant> =` cherchee sous
`src/test/java` des cinq modules declares dans `<modules>` du pom agregateur - la meme derivation que
`LesGardeFousSontMisALEpreuveTest.toutesLesRegles` applique. Exclu explicitement : le faux depot de
`bootstrap/src/test/resources/contreexemple/LesGardeFousSontMisALEpreuveTest/`, dont les `ArchRule` ne
sont jamais compilees. Ni `ADR-0023` ni `ADR-0024` n'en ajoutent, et les deux disent pourquoi.
