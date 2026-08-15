# ADR-0041 - Deriver la valeur comparable d'une proposition de ce qu'elle ecrit, et refuser qu'un garde-fou juge un paquet sur une partie de ses items

- **Statut** : Accepte
- **Date** : 2026-08-15
- **Auteur** : theo-archi
- **Sprint** : s13 (annonce dans mon avis de faisabilite S13-1 et non ecrit ; `lea-dev` a corrige le
  classificateur et a refuse d'en decider la doctrine a ma place, ce qui etait sa frontiere)
- **Regle executable** :
  - la lecture elle-meme :
    `adapters/adapter-content/src/test/java/com/recreo/adapter/content/LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java`
    (methode `valeurDe`, et ses quatre mises a l'epreuve `...Milliers...` et `...Durees...`, lot
    `lea-dev` fusionne en `31ca444`) ;
  - ses deux angles morts, ecrits par cet ADR :
    `adapters/adapter-content/src/test/java/com/recreo/adapter/content/LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest.java`
    (methodes `aucunPaquetNEstJugeSurUnePartieDeSesItemsSansQueLeDepotLeDeclare` et
    `lesTroisPropositionsDUnItemDuDomainePortentLaMemeUnite`).

## Contexte

Depuis s12, un garde-fou de contenu compare les trois propositions d'un item pour dire si le rang de
la bonne reponse est predictible. Comparer suppose une **valeur**, et cette valeur etait lue par un
motif ecrit en passant : `^-?\d+`, le premier groupe de chiffres. Personne n'avait ecrit ce que
"la valeur d'une proposition" voulait dire ; il n'y avait donc rien a contredire, et la lecture a
tenu deux sprints en disant faux sans que rien ne rougisse.

Deux faits, tous deux trouves ce sprint, et tous deux du meme genre : **le garde-fou etait vert, et
son vert etait faux**.

### Fait 1 - vert et faux au-dessus de mille, pendant deux sprints

Aucun paquet de production ne franchissait le millier ; le separateur de milliers n'existait donc
nulle part, et le defaut n'avait aucun objet. S13-1 est le premier paquet du depot a franchir le
million. Mesure, sur du materiau reel depose dans le repertoire de contenu de production - douze
items dont la bonne reponse est chaque fois le plus grand des trois nombres, ecrits au separateur
d'espace :

| mesure | resultat |
|---|---|
| `mvn -B -pl adapters/adapter-content -am test` avec ce paquet | **98 tests, 0 echec** : le garde-fou de rang ne dit rien |
| le meme paquet, un seul `indice` retire | **rouge**, `ChaquePaquetPorteUnIndiceTest` nomme le paquet |

La seconde ligne est la moitie qui compte : le paquet **etait** dans le perimetre des garde-fous
derives du repertoire, et le silence du premier n'etait pas un artefact de portee. La cause est
arithmetique : "1 000 000" etait lu **1** et "999 999" lu **999**, donc le rang calcule etait
l'inverse du rang reel, et la repartition truquee se repartissait sur les trois rangs au lieu d'en
saturer un seul.

Remesure du 2026-08-15 sur le jeu fautif que `lea-dev` a conserve, ancienne lecture retablie a la
main dans `valeurDe` puis `mvn -B -pl adapters/adapter-content -am test`, verdict lu dans le message
d'echec :

```
paquet-neuf-milliers-toujours-la-plus-grande   total=12  petite=4  inter=4  grande=4  max=33,3 %
```

Douze items dont la bonne reponse est **toujours** la plus grande, lus comme une repartition
parfaitement uniforme. Le seuil de majorite est a 50 % : ce paquet passait avec une marge
confortable.

### Fait 2 - vert par coincidence exacte sur les durees, et la coincidence tenait a un item

`nadia-prof` a trouve le meme defaut sur une autre ecriture (arbitrage prealable s13, section 3.6) :
sur une duree, `^-?\d+` ne lit que l'heure et perd les minutes. Meme protocole, meme commande, sur
son jeu fautif de douze items dont la bonne reponse est toujours la plus grande une fois les durees
reellement lues :

```
paquet-neuf-durees-toujours-la-plus-grande     total=12  petite=6  inter=0  grande=6  max=50,0 %
```

Six items dont les trois propositions partagent l'heure de tete sont tous lus **"la plus petite"**,
a tort ; six items dont l'heure de tete suffit a ordonner sont lus **"la plus grande"**, par hasard.
La comparaison au seuil est stricte (`> 0,5`) : **50,0 % pile passe**. Un seul item deplace d'une
moitie vers l'autre aurait donne 7 sur 12, soit 58,3 %, et le garde-fou aurait rougi. Le vert ne
tenait pas a une marge, il tenait a une egalite.

C'est le point qui justifie d'ecrire cet ADR plutot que de laisser la correction parler seule : un
vert obtenu par compensation exacte entre deux erreurs opposees ne se distingue en rien, dans un
rapport de revue, d'un vert obtenu par une propriete tenue.

### Ce que `lea-dev` a livre, et ou s'arretait sa frontiere

Son correctif (`31ca444`, fusionne en `ae2d724`) fait deriver la valeur de ce que la proposition
**ecrit** : une duree ou un instant en heures et minutes converti en minutes totales, sinon un
nombre entier complet separateurs de milliers retires, sinon rien. Elle a couvert les durees plutot
que de les exclure, sur le motif qu'un ordre total existe et que les ecarter violerait l'obligation
de derivation totale d'`ADR-0013` ; elle n'ecarte **aucun item** de production, taux inchanges sur
les six paquets qui en ont un.

Ce qu'elle a refuse d'ecrire, et elle avait raison de le refuser, c'est **ce qu'est la valeur d'une
proposition** - une definition qui vaut pour tout garde-fou qui en lira une, pas seulement pour le
sien. C'est l'objet de cet ADR.

## Decision

**1. La valeur d'une proposition est derivee de ce que la proposition ecrit, jamais du type declare
par le paquet ni d'un attribut porte a cote.** C'est la meme doctrine que le caractere destructeur
d'`ADR-0038` point 3 : un effet mesure sur l'objet reel, pas un role declare. Une consequence
pratique en decoule tout de suite : un paquet ne peut pas desamorcer un garde-fou de comparaison en
se declarant d'un autre type.

**2. La lecture, dans cet ordre, et l'ordre fait partie de la decision** :

1. une **duree ou un instant en heures et minutes** ("1 h 30", "2 h", "45 min") vaut son nombre total
   de minutes ;
2. sinon, un **nombre entier complet** en tete de la proposition, ses separateurs de milliers retires
   - l'espace, l'espace insecable (U+00A0) et l'espace fine insecable (U+202F) ;
3. sinon, la proposition **n'a pas de valeur**.

L'ordre n'est pas cosmetique : lire le nombre en premier ferait valoir 1 la duree "1 h 30", ce qui
est le defaut du fait 2. Une regle qui se contenterait de dire "on lit le nombre entier" laisserait
donc la moitie du defaut ouverte.

**3. Une proposition sans valeur est hors du domaine des proprietes qui comparent des propositions.
Hors du domaine n'est pas un defaut** - le rang d'un symbole de comparaison ou d'un mot n'a pas de
sens - **mais hors du domaine n'est pas non plus gratuit.**

**4. Un garde-fou ne juge jamais un paquet sur une partie de ses items sans que le depot le
declare.** C'est la clause neuve de cet ADR, et c'est elle qui empeche le point 3 d'etre une porte.
Trois cas, et un seul est interdit :

| etat d'un paquet | ce que le garde-fou de rang en fait | verdict |
|---|---|---|
| **tous** ses items sont dans le domaine | il rend un taux qui porte sur le paquet | rien a declarer |
| **aucun** de ses items n'est dans le domaine | il ne rend aucun taux, donc n'affirme rien | rien a declarer |
| **une partie** de ses items est dans le domaine | il rend un taux et le presente comme celui du paquet | **declaration exacte obligatoire** |

Le cas interdit est celui ou un chiffre est publie pour un paquet alors qu'il porte sur un
sous-ensemble. Aujourd'hui il existe, et il est mesure : `cm1-perimetre` porte 22 items a trois
propositions, dont **20** dans le domaine ; les deux autres sont ses deux items conceptuels
("Le perimetre d'une figure, c'est...", "Comment calcule-t-on le perimetre d'un carre ?"). Le taux de
90,0 % que le garde-fou de rang cite pour ce paquet - et que sa Javadoc et son exemption de dette
citent a leur tour - porte donc sur 20 items, pas sur 22. La declaration ne corrige pas le paquet ;
elle rend l'ecart visible au lieu de le laisser deduire.

**5. Les trois propositions d'un item du domaine portent la meme unite.** La lecture ne lit que le
nombre : "20 cm" vaut 20 et "1 m" vaut 1. Trois propositions d'unites differentes recoivent donc un
ordre faux - non pas absent, **faux** - et le point 4 ne peut pas le voir, puisque les trois valeurs
existent. Le catalogue porte deja un paquet de conversions (`cm1-mesures`, competence *"convertir des
unites de longueur, masse, contenance"*), dont les 24 items ecrivent tous une unite : l'objet de
cette clause est a un item de distance.

## Regle executable

Fichier : `adapters/adapter-content/src/test/java/com/recreo/adapter/content/LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest.java`

```java
/** Les paquets juges sur une partie de leurs items, avec le nombre exact d'items ecartes. */
private static final Map<String, Integer> PAQUETS_A_ITEMS_ECARTES = Map.of("cm1-perimetre", 2);

@Test
void aucunPaquetNEstJugeSurUnePartieDeSesItemsSansQueLeDepotLeDeclare() {
    assertThat(itemsEcartesParPaquet(CONTENU_DE_PRODUCTION, ""))
            .containsExactlyInAnyOrderEntriesOf(PAQUETS_A_ITEMS_ECARTES);
}

@Test
void lesTroisPropositionsDUnItemDuDomainePortentLaMemeUnite() {
    assertThat(itemsAUnitesMelangees(CONTENU_DE_PRODUCTION, "")).isEmpty();
}
```

La lecture n'est pas recopiee : ce garde-fou appelle
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.valeurDe`, dont la visibilite passe de
privee a celle du paquet dans le meme commit. Deux lectures qui derivent l'une de l'autre finissent
par diverger, et c'est alors le garde-fou qui garde la mauvaise.

**Pourquoi ce n'est pas une regle ArchUnit** : ArchUnit juge des classes et leurs dependances ; la
propriete porte sur le contenu YAML charge par le chargeur reel. Le mecanisme automatique est un test
du module `adapter-content`, execute par `mvn verify` comme le reste. **Le compte des regles ArchUnit
reste a 28**, methode inchangee (declarations `ArchRule <nom> =` sous `src/test/java` des modules du
pom agregateur ; comptees sur l'arbre de ce commit).

**Les trois obligations d'`ADR-0013`, sans dispense :**

1. **perimetre derive** - les paquets viennent du balayage de `contenu/`, jamais d'une liste ; leurs
   items viennent du chargeur reel. `PAQUETS_A_ITEMS_ECARTES` n'est pas un perimetre mais un
   **constat exact** : la mesure et la declaration sont comparees par egalite, dans les deux sens ;
2. **derivation totale** - tout item a trois propositions est classe dans le domaine ou hors du
   domaine, sans troisieme sort. Un paquet introuvable, un repertoire vide sont des echecs ;
3. **mise a l'epreuve** - deux paquets neufs jamais livres, un par clause, chacun avec sa version
   corrigee qui doit redevenir verte.

**Morsure mesuree, methode comprise.** JDK 21.0.10, worktree dedie hors de l'arbre du depot,
`mvn -B -pl adapters/adapter-content -am test` - jamais un `-pl` seul, voir la section suivante -
depot restaure apres chaque mutation, verdict lu dans le message d'echec :

| # | Mutation, sur du materiau reel | Resultat |
|---|---|---|
| M1 | `PAQUETS_A_ITEMS_ECARTES` vide | **1 echec sur 7**, *"Expecting empty but was: {"cm1-perimetre"=2}"* |
| M2 | declaration a 3 items ecartes au lieu de 2 | **1 echec sur 7**, la meme clause, qui oppose la mesure a la declaration |
| M3 | un distracteur reel de `cm1-mesures` reecrit dans une autre unite de meme grandeur (`"4000 cm"` devient `"40 m"`) | **1 echec sur 20**, la clause d'unite, nommant l'item ; **les 12 tests du garde-fou de rang restent verts** |

M3 est la mesure qui compte, et elle est plus forte que je ne l'attendais : les deux ecritures
designent la meme longueur, mais le rang classe de cet item **bascule** d'intermediaire a "la plus
grande" (repartition de `cm1-mesures` mesuree a 13/4/7 avant, 13/3/8 apres). Une conversion
parfaitement correcte du point de vue de l'enfant deplace donc silencieusement un item d'une
categorie a l'autre, et rien d'autre que cette clause ne s'en apercoit.

**Une mesure ratee, et je l'ecris parce qu'elle a failli entrer dans cet ADR.** Mes deux premieres
executions de la sonde de repartition ont ete lancees avec `-pl adapters/adapter-content` **sans**
`-am`. Le module `domain` a alors ete resolu depuis un jar installe et perime, et la sonde a rendu
63,1 % pour `cp-additions-jusqu-a-10` - exactement l'ancien chiffre, d'avant le correctif de
`marc-dev` sur la fabrique - au lieu de 38,5 %. Le meme arbre rend donc deux chiffres selon la
commande, et le faux a l'air du vrai puisqu'il figure deja dans le depot. C'est le piege que
`sofia-sm` a constate ce sprint et que mon propre avis de faisabilite citait ; je l'ai reproduit
quand meme. Toutes les mesures ci-dessus sont refaites avec `-am`.

## Ce que cette definition ne couvre pas, nommement

1. **Un paquet entierement hors du domaine n'est juge par rien, et ce n'est pas declare.** Quatre
   paquets de production sont dans ce cas au 2026-08-15 : `cm1-conjugaison-present`,
   `cm1-homophones`, `cp-comparer-nombres` et `cp-le-bon-son`. Le point 4 ne les compte pas, a
   dessein : ils ne produisent aucun taux, donc aucune affirmation fausse. Ce qui reste ouvert est
   qu'un paquet **aujourd'hui numerique** pourrait le devenir - un paquet de grands nombres reecrit
   en toutes lettres - et cesser d'etre juge sans qu'aucun diff ne le montre. Je ne le ferme pas :
   la clause qui le fermerait obligerait a declarer les quatre paquets de francais et a en ajouter un
   a chaque ouverture de ligne, pour un risque qu'aucun objet du depot ne porte. **Ce qui me fera
   changer d'avis** : le premier paquet qui sort du domaine sans que ce soit voulu.
2. **L'unite n'est ni lue ni convertie, elle est seulement exigee identique.** "20 cm" et "20 m" ont
   la meme valeur pour ce depot. La clause 5 rend cette cecite inoffensive **a l'interieur d'un
   item** ; elle ne dit rien entre deux items du meme paquet, ou la comparaison n'a de toute facon
   pas lieu. Le jour ou un item pedagogiquement legitime demandera de comparer deux unites -
   *"coche la plus grande longueur : 1 m, 50 cm, 200 mm"*, ce qui est une question de CM1 tout a fait
   ordinaire - la clause 5 rougira dessus. Ce rouge sera **vrai** : la lecture ordonnerait 1 < 50 <
   200, c'est-a-dire l'inverse. Il faudra alors apprendre les unites a la lecture, ou sortir ces
   items du domaine par un critere ecrit ; ce sera un `ADR`, et le rouge est precisement ce qui
   forcera a le prendre au lieu de publier un classement faux.
3. **Une duree et un nombre nu se comparent sans que rien ne s'en plaigne.** "1 h 30" vaut 90 et
   "90" vaut 90 : les deux sont dans le domaine, leur "unite" residuelle est vide dans les deux cas,
   la clause 5 les laisse passer. C'est correct si le nombre nu signifie des minutes, faux sinon.
   Aucun item du depot ne melange les deux ecritures.
4. **Un instant et une duree portent la meme ecriture.** "8 h 30" est lu comme 510 minutes, qu'il
   designe une heure de la journee ou une duree. Cela suffit a les ordonner tant qu'aucun item ne
   franchit minuit (D-3 de l'arbitrage de `nadia-prof`) ; ce n'est plus vrai le jour ou un item
   comparera "23 h 30" et "0 h 30" comme deux instants successifs.
5. **La valeur ne dit rien de la justesse pedagogique d'une proposition.** Qu'un distracteur soit
   plausible, qu'un nombre soit lisible par un enfant de CM1, que "1 000 000" soit une meilleure
   ecriture que "1000000" : c'est `nadia-prof`, et rien ici n'y touche.
6. **Le seuil de majorite reste compare strictement, et deux paquets de production s'y tiennent a
   l'egalite exacte** : `cp-problemes-additifs` et `cp-soustractions`, tous deux mesures a 50,0 %
   pile. Ce n'est pas un defaut de cette definition, mais c'est la meme forme de fragilite que le
   fait 2 : un vert a l'egalite exacte n'a aucune marge, et un item ajoute peut le renverser. Je le
   signale sans le changer - le seuil et sa comparaison sont ceux de `nadia-prof`.
7. **Une seule lecture existe aujourd'hui, et rien n'oblige la seconde a l'appeler.** Le point 1 de
   la decision parle de tout garde-fou qui compare des propositions ; il n'y en a qu'un, plus celui
   de cet ADR qui l'appelle. Un garde-fou neuf qui reecrirait son propre motif serait **vert** devant
   tout ce que ce depot possede. C'est une dette, elle est nommee, et je refuse de l'encoder par un
   balayage qui chercherait un motif de chiffres dans les sources de test : la lecon de s05 est
   exactement celle-la - une detection qui devine la forme que le monde ecrira attrape autre chose
   que ce qu'elle vise. **Declencheur** : le deuxieme garde-fou qui a besoin de comparer deux
   propositions ; il appelle `valeurDe` ou il justifie dans un `ADR` pourquoi il ne peut pas.

## Consequences

- **Le prochain paquet melange coute un diff, et c'est le but.** S13-1 est le premier candidat : si
  ses verbes "lire" et "comparer" (propositions en lettres ou en symboles) cohabitent avec "ecrire"
  et "decomposer" (propositions numeriques) dans un seul paquet, la clause 4 rougit et l'auteur doit
  soit declarer le nombre exact d'items ecartes, soit separer le paquet. Il ne peut plus laisser le
  garde-fou de rang publier un taux qui porte sur la moitie du paquet.
- **Une exemption de dette existante devient lisible.** L'exemption de `cm1-perimetre` dans
  `DETTE_DE_RANG` s'appuie sur un taux de 90,0 % dont on sait maintenant qu'il porte sur 20 items sur
  22. L'exemption ne change pas ; ce qui change est qu'on ne peut plus lire ce chiffre sans sa base.
- **Mauvaise consequence assumee, et elle est reelle** : la clause 5 interdit aujourd'hui un item
  qu'un enseignant pourrait vouloir ecrire (non-preuve 2). Je prefere un rouge qui se discute a un
  classement faux qui ne se voit pas, mais c'est un arbitrage, pas une evidence. Si `nadia-prof`
  demande cet item, c'est la clause qui devra apprendre les unites - pas une exemption d'un nom.
- **Cout d'execution** : sept tests de plus, environ une seconde sur ce poste. Le module
  `adapter-content` passe de 102 a 109 tests, le depot de 468 a 475.
- **Aucune ligne de production ne change.** Ce lot ne touche que des tests, un contre-exemple et deux
  fichiers de decision.

## Alternatives considerees

- **Ne rien ecrire : la correction de `lea-dev` se suffit.** Ecarte. Le correctif est juste, mais il
  ne dit pas ce qu'est une valeur ; le prochain garde-fou qui compare deux propositions
  redecouvrirait le fait 1 sur son propre materiau, comme `nadia-prof` et moi l'avons redecouvert
  chacun de notre cote a trois jours d'intervalle.
- **Exiger qu'un paquet soit entierement dans le domaine ou entierement hors.** Ecarte, mesure a
  l'appui : la clause serait **rouge sur `cm1-perimetre`** des sa premiere execution, et le remede
  serait de retirer deux items conceptuels d'un paquet livre et valide - c'est-a-dire une decision de
  contenu, qui n'est pas la mienne. La declaration exacte obtient la visibilite sans prendre la
  decision de quelqu'un d'autre.
- **Plafonner le nombre de paquets melanges, comme les autres dettes du depot.** Ecarte : un plafond
  qui majore la dette est une place libre, et une place libre laisse entrer en silence le paquet
  suivant (`ADR-0013`, amendement du 2026-08-15). L'egalite entre la mesure et la declaration n'a
  aucune place libre par construction, et n'a donc pas besoin de plafond.
- **Ecarter les durees du domaine plutot que de les lire.** C'est ce qu'un lecteur presse aurait pu
  tirer de mon avis de faisabilite, qui ne parlait que des milliers. `lea-dev` a refuse, et elle a
  raison : un ordre total existe sur les durees, les ecarter violerait l'obligation de derivation
  totale d'`ADR-0013`, et surtout cela aurait rendu invisible tout un paquet de mesures - le fait 2
  est precisement ce qui arrive quand une famille d'ecritures n'est pas lue.
- **Encoder la doctrine par un balayage des sources de test** (aucun autre fichier ne declare de
  motif numerique). Ecarte : voir la non-preuve 7. La detection devinerait la forme du defaut, ce qui
  est la faute mesuree en s05 et repetee trois fois depuis.

## Ce qui me ferait changer d'avis

- **Si un item pedagogiquement legitime devait comparer deux unites**, la clause 5 passerait de
  l'interdiction a la conversion : la lecture apprendrait un facteur par unite, ce qui est une piece
  mobile de plus et une table a tenir. Je ne la prends pas avant d'avoir l'item.
- **Si un second garde-fou avait besoin de comparer des propositions**, la non-preuve 7 cesserait
  d'etre theorique et il faudrait un mecanisme, pas une doctrine. Le mecanisme le moins couteux que
  je voie aujourd'hui n'est pas un balayage mais un deplacement : sortir `valeurDe` dans une classe
  de test partagee du module, ce qui rend l'appel plus naturel que la reecriture.
- **Si `nadia-prof` changeait le seuil de majorite ou sa comparaison**, la non-preuve 6 changerait de
  sens ; le reste de cet ADR ne bouge pas.
