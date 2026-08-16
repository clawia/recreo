# ADR-0041 - Deriver la valeur comparable d'une proposition de ce qu'elle ecrit, et refuser qu'un garde-fou juge un paquet sur une partie de ses items

- **Statut** : Accepte
- **Date** : 2026-08-15
- **Amende le** : 2026-08-15, voir la section "Amendement du 2026-08-15" - deux points, la
  ratification de la lecture d'une somme et la correction de la clause 5, qui exigeait l'identite
  d'un texte la ou elle voulait dire l'identite d'une unite
- **Auteur** : theo-archi
- **Sprint** : s13 (annonce dans mon avis de faisabilite S13-1 et non ecrit ; `lea-dev` a corrige le
  classificateur et a refuse d'en decider la doctrine a ma place, ce qui etait sa frontiere)
- **Regle executable** :
  - la lecture elle-meme :
    `adapters/adapter-content/src/test/java/com/recreo/adapter/content/LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java`
    (methode `lireValeur`, qui rend la valeur **et** le reste, et son abrege `valeurDe` ; mises a
    l'epreuve `...Milliers...`, `...Durees...` et `...ValeursCoincident...`, lots `lea-dev`
    fusionnes en `31ca444` et `74cc9e7`) ;
  - ses deux angles morts, ecrits par cet ADR :
    `adapters/adapter-content/src/test/java/com/recreo/adapter/content/LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest.java`
    (methodes `aucunPaquetNEstJugeSurUnePartieDeSesItemsSansQueLeDepotLeDeclare`,
    `lesTroisPropositionsDUnItemDuDomaineLaissentLeMemeResteApresLecture` et sa mise a l'epreuve
    derivee `chaqueItemReelDontUnResteEstReecritFaitEchouerLaClause`).

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
2. sinon, une **somme de termes** ("40 000 + 700 + 5") vaut son **total**, chaque terme etant lu
   comme un nombre entier complet ; si un seul terme ne l'est pas, la somme n'a pas de valeur plutot
   qu'une valeur partielle (**ratifie par l'amendement du 2026-08-15**, voir la section
   "Amendement") ;
3. sinon, un **nombre entier complet** en tete de la proposition, ses separateurs de milliers retires
   - l'espace, l'espace insecable (U+00A0) et l'espace fine insecable (U+202F) ;
4. sinon, la proposition **n'a pas de valeur**.

L'ordre n'est pas cosmetique : lire le nombre en premier ferait valoir 1 la duree "1 h 30", ce qui
est le defaut du fait 2, et ferait valoir 40 000 la decomposition "40 000 + 700 + 5", ce qui est le
defaut mesure a l'amendement. Une regle qui se contenterait de dire "on lit le nombre entier"
laisserait donc les deux tiers du defaut ouverts.

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

**5. Les trois propositions d'un item du domaine laissent le meme reste apres lecture de leur
valeur** (*amende le 2026-08-15 ; la clause disait "portent la meme unite" et comparait un texte, ce
qui n'est pas la meme chose - voir la section "Amendement", point 2*). Le **reste** est ce que la
lecture n'a pas consomme, rendu par elle : vide sur un nombre, une somme ou une duree, qu'elle lit en
entier ; "cm" sur "20 cm", qu'elle n'entame que jusqu'au nombre. Deux restes differents disent que
les trois valeurs ne mesurent pas la meme chose : "20 cm" vaut 20 et "1 m" vaut 1, et l'ordre est
alors faux - non pas absent, **faux** - ce que le point 4 ne peut pas voir, puisque les trois valeurs
existent. Le catalogue porte deja un paquet de conversions (`cm1-mesures`, competence *"convertir des
unites de longueur, masse, contenance"*), dont les 24 items ecrivent tous une unite : l'objet de
cette clause est a un item de distance. Le reste n'est pas une unite et la clause ne dit pas
"unite" : une unite ecrite est le cas le plus courant de reste non vide, ce n'est pas le seul.

## Regle executable

Fichier : `adapters/adapter-content/src/test/java/com/recreo/adapter/content/LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest.java`

```java
@Test
void aucunPaquetNEstJugeSurUnePartieDeSesItemsSansQueLeDepotLeDeclare() {
    assertThat(itemsEcartesParPaquet(CONTENU_DE_PRODUCTION, ""))
            .containsExactlyInAnyOrderEntriesOf(PAQUETS_A_ITEMS_ECARTES);
}

@Test
void lesTroisPropositionsDUnItemDuDomaineLaissentLeMemeResteApresLecture() {
    assertThat(itemsAResteDeLectureDifferent(CONTENU_DE_PRODUCTION, "")).isEmpty();
}
```

`PAQUETS_A_ITEMS_ECARTES` declare `cm1-perimetre` (2 items ecartes) et, depuis le lot de `marc-dev`,
`cm1-grands-nombres` (14). Le reste compare par la seconde clause est rendu par la lecture elle-meme :

```java
record Lue(long valeur, String reste) { }

static Lue lireValeur(String proposition) { ... }   // reste vide sur une duree et sur une somme,
                                                    // suffixe non lu sur un nombre de tete
```

La lecture n'est **ni recopiee ni doublee** : ce garde-fou appelle
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.lireValeur`, dont la visibilite est celle du
paquet. Deux lectures qui derivent l'une de l'autre finissent par diverger, et c'est alors le
garde-fou qui garde la mauvaise - **c'est exactement ce qui est arrive** entre le 2026-08-15 et
l'amendement du meme jour, sur cette phrase-la, dans ce fichier-la.

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
   corrigee qui doit redevenir verte, **plus** - amendement du 2026-08-15 - une mutation derivee de
   **chaque** item du domaine de la production pour la clause 5, decrite a la fin de la section
   "Amendement". Un paquet neuf prouve que la clause mord sur ce que son auteur imagine ; la mutation
   derivee prouve qu'elle mord sur ce que le depot ecrit.

**Morsure mesuree, methode comprise.** JDK 21.0.10, worktree dedie hors de l'arbre du depot,
`mvn -B -pl adapters/adapter-content -am test` - jamais un `-pl` seul, voir la section suivante -
depot restaure apres chaque mutation, verdict lu dans le message d'echec :

| # | Mutation, sur du materiau reel | Resultat |
|---|---|---|
| M1 | `PAQUETS_A_ITEMS_ECARTES` vide | **1 echec sur 7**, *"Expecting empty but was: {"cm1-perimetre"=2}"* |
| M2 | declaration a 3 items ecartes au lieu de 2 | **1 echec sur 7**, la meme clause, qui oppose la mesure a la declaration |
| M3 | un distracteur reel de `cm1-mesures` reecrit dans une autre unite de meme grandeur (`"4000 cm"` devient `"40 m"`) | **1 echec sur 20**, la clause d'unite, nommant l'item ; **les 12 tests du garde-fou de rang restent verts** |

*M3 est rejouee apres l'amendement du 2026-08-15 (morsure A1) : la clause du reste rougit sur la meme
mutation, et le garde-fou de rang ne reste plus vert - le garde-fou d'egalite, neuf depuis, rougit
aussi, `"40 cm"` et `"40 m"` se lisant tous deux 40.*

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

## Amendement du 2026-08-15

Deux points, arrives ensemble parce que le meme contenu les a reveles : la reprise de
`cm1-grands-nombres` par `marc-dev` apres le REFUS de `nadia-prof`, dont les distracteurs de
decomposition gardent desormais le premier terme et deplacent l'erreur sur un terme suivant
(`600 000 + 40` contre `600 000 + 4` et `600 000 + 400`).

### 1. La lecture d'une somme est ratifiee, et devient le cas 2 de la decision

`lea-dev` l'a implementee en fermant un autre trou - un item dont deux propositions se lisent a la
meme valeur rougit desormais au lieu d'etre ecarte - et a explicitement refuse d'en decider la
doctrine : elle a eu raison, et sa lecture est **ratifiee telle quelle**.

**Le motif n'est pas qu'elle est correcte, c'est qu'il n'y a pas d'autre issue.** Mesure, JDK
21.0.10, worktree dedie hors de l'arbre du depot, contenu de `marc-dev` (`066f857`) depose dans le
repertoire de contenu de production, `mvn -B -pl adapters/adapter-content -am test` :

| lecture de la somme | ce que devient `cm1-grands-nombres` |
|---|---|
| active (etat du depot) | **14 items juges** : petite 5, intermediaire 4, grande 5, max 35,7 % |
| desactivee a la main dans `valeurDe` | **7 items juges**, les 7 "decomposer" lus a la meme valeur tous les trois ; le garde-fou d'egalite rougit sur les sept |

> **Correction du 2026-08-16 - la premiere ligne de ce tableau ne decrit pas le contenu qu'elle
> nomme.** L'original est laisse au-dessus, comme les six chiffres deja corriges dans ce depot.
>
> **Le chiffre qui tient est celui de `marc-dev` : 14 items juges, petite 3, intermediaire 6,
> grande 5, max 42,9 %.** Mesure sur l'arbre fusionne `4000bab`, JDK 21.0.10, worktree dedie hors
> de l'arbre du depot, sonde jetable non commitee placee dans le paquet de test d'`adapter-content`,
> qui appelle le chargeur reel et la lecture reelle (`paquets`, `itemsTires`, `valeurDe` de
> `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`), jamais une transcription :
> `mvn -B -pl adapters/adapter-content -am test -Dtest=<sonde>`. La meme execution rend
> `cm1-mesures` 13/4/7 et `cp-additions-jusqu-a-10` 38,5 %, les deux chiffres que cet ADR cite par
> ailleurs : la sonde lit bien ce que cet ADR croit lire.
>
> **D'ou venait l'ecart : d'un etat de contenu, pas d'une sonde qui lit faux.** Le 5/4/5 est la
> repartition du contenu **d'avant la reprise de `marc-dev`** - celui que `nadia-prof` a REFUSE, ou
> les trois propositions de decomposition ne faisaient varier que leur premier terme. Reproduit :
> le fichier `cm1-grands-nombres.yaml` de `c6b7745` remis dans le repertoire de production rend
> exactement **14 items juges, 5/4/5, max 35,7 %**. Le contenu de `066f857` n'a donc jamais ete
> depose avant cette mesure-la, alors que la ligne le nomme.
>
> **Les deux lignes du tableau n'ont pas ete mesurees sur le meme contenu**, et c'est ce qui a rendu
> l'erreur invisible : sur le contenu refuse, desactiver la lecture de somme ne change **rien**
> (14 items juges, 5/4/5, dans les deux sens - les trois premiers termes different, donc les trois
> valeurs restent distinctes sans lire la somme). La ligne 2 ne peut donc venir que du contenu de
> `066f857`, et elle est juste. Sur l'arbre fusionne, le contraste que ce tableau veut montrer est
> **14 items juges (3/6/5) contre 7 (2/3/2)** : la conclusion de la ratification est inchangee et
> le fait qu'elle etablit est intact.
>
> **Consequence de garde-fou : aucune.** 42,9 % reste sous le seuil de majorite de 50 %,
> `cm1-grands-nombres` n'entre pas dans `DETTE_DE_RANG`, et le plafond de dette ne bouge pas.
>
> **La lecon, et elle est neuve.** Les trois familles de cet ADR - la sonde qui reimplemente, le
> `-pl` sans `-am` qui lit un jar perime - portaient toutes sur le **code** mesure. Celle-ci porte
> sur le **contenu** mesure, et aucune de mes precautions ne la voyait : deposer un fichier dans un
> repertoire est un geste sans trace, que rien dans le rapport ne distingue d'un geste omis. Une
> mesure ne cite donc pas seulement sa commande et sa version de JDK, elle cite **l'etat de l'arbre
> qu'elle a lu** - un commit, jamais un fichier recopie a la main depuis une autre branche. Quand ce
> n'est pas possible, la mesure attend la fusion.

Refuser la lecture de la somme ne laisserait donc pas le depot en l'etat : elle retirerait du
domaine **la moitie des items numeriques** d'un paquet livre, et ferait de la forme de distracteur
que `nadia-prof` **exige** la seule forme qu'aucun garde-fou de rang ne peut voir. C'est le fait 1
de cet ADR, une troisieme fois, sur une troisieme ecriture. La derivation totale d'`ADR-0013`
interdit de s'en tirer en declarant les sept items hors du domaine, exactement comme elle
l'interdisait pour les durees.

**Ce que j'ai verifie de la mesure de `lea-dev`, plutot que de la croire.** Elle annonce que les
douze paquets de production ne bougent d'aucun taux avec ou sans cette lecture. Sonde jetable
imprimant la repartition des rangs des douze paquets, executee deux fois sur le meme arbre, la
seconde avec `sommeDeTermes` neutralisee : **les douze lignes sont identiques au chiffre pres**. Sa
mesure tient. La sonde n'est pas commitee ; ses chiffres sont ici.

**Ce que la ratification ne couvre pas** est ecrit en non-preuves 8, 9 et 10 ci-dessous.

### 2. La clause 5 exigeait l'identite d'un texte la ou elle voulait dire l'identite d'une unite

`lea-dev` a trouve le defaut en verifiant, et ne l'a pas touche - hors de son perimetre, et c'est ma
clause. Elle a raison sur les deux points, et sur le diagnostic.

**Ce que la clause faisait.** Elle comparait, entre les trois propositions, **le texte qui suit le
premier nombre**, lu par un motif ecrit dans le garde-fou - les chiffres, le signe et les trois
ecritures du separateur de milliers, retires en tete. Ce
motif etait une **seconde lecture**, ecrite a cote de la lecture de valeur, et la Javadoc de cet
ADR interdisait deja explicitement ce montage a propos de `valeurDe` : *"deux lectures qui derivent
l'une de l'autre finissent par diverger, et c'est alors le garde-fou qui garde la mauvaise."* Je
l'avais ecrit, et je l'avais fait quand meme, dans le meme fichier.

**Quand elles ont diverge.** Des que la lecture de valeur a su lire une somme. Sur
`600 000 + 40`, la lecture de valeur consomme tout et rend 600 040 ; le motif d'a cote s'arretait au
premier nombre et rendait `+ 40`. Tant que les items "decomposer" ne faisaient varier que leur
premier terme, ce residu etait identique entre les trois **par coincidence** - la clause etait verte
sans rien prouver, exactement la forme de vert que cet ADR a ete ecrit pour refuser. Le contenu
corrige de `marc-dev` fait varier un terme suivant : les trois residus different, et la clause
declenche un **faux positif d'unite sur un paquet qui n'ecrit aucune unite**.

Mesure de ce faux positif, contenu de `marc-dev` (`066f857`) depose en production, clause d'avant
l'amendement : **1 echec sur 7**, `lesTroisPropositionsDUnItemDuDomainePortentLaMemeUnite`, nommant
les **sept** items "decomposer" du paquet, dont

```
paquet cm1-grands-nombres : les trois propositions [600 000 + 40, 600 000 + 4, 600 000 + 400]
ne portent pas la meme unite ([+ 40, + 4, + 400])
```

C'etait le seul rouge restant du lot de `marc-dev` une fois celui de `lea-dev` fusionne.

**Ce que la clause devient.** La lecture rend desormais, avec la valeur, **ce qu'elle n'a pas
consomme** - le `reste`. La clause exige que les trois propositions d'un item du domaine laissent le
**meme reste**.

> **Clause 5 (amendee).** Les trois propositions d'un item du domaine laissent le meme reste apres
> lecture de leur valeur. Le reste est le complement exact de la lecture, rendu par elle : vide sur
> un nombre, une somme ou une duree, qu'elle consomme en entier ; `"cm"` sur `"20 cm"`, qu'elle
> n'entame que jusqu'au nombre.

Trois proprietes, et ce sont les trois demandees :

1. **Ce que la clause gardait, elle le garde.** Une proposition en `kg` face a une en `g` laisse des
   restes differents et rougit, comme avant.
2. **Elle ne lit ni ne convertit les unites**, ce que la non-preuve 2 disait deja et continue de
   dire : elle ne sait pas que `1 m` vaut `100 cm`, elle sait seulement que le texte laisse derriere
   n'est pas le meme.
3. **Rien n'est enumere** (`ADR-0039`). Il n'y a aucune liste d'unites connues : le reste est
   **derive** de la lecture, et l'ensemble des restes que la production ecrit est **mesure** sur le
   corpus par la mise a l'epreuve ci-dessous, jamais ecrit a la main.

**Ce que la clause corrigee dit de plus, et que je n'avais pas cherche.** Un reste non vide ne
signifie pas "une unite" : il signifie "la lecture n'a pas tout lu". Sur `600 000 - 40`, elle rend
600 000 et laisse `- 40` : la valeur est fausse, et deux ecritures soustractives qui different
laissent des restes differents, donc rougissent. La clause attrape ainsi une famille plus large que
celle qu'elle visait, sans qu'aucune ligne la nomme. Le mot "unite" quitte le nom de la clause pour
cette raison : il decrivait le cas le plus courant, pas la propriete.

### Morsures de la clause amendee, sur du materiau reel, dans les deux sens

JDK 21.0.10, worktree dedie hors de l'arbre du depot, depot restaure apres chaque mutation.

| # | Sens | Materiau et mutation | Resultat |
|---|---|---|---|
| A1 | **rouge** | `cm1-mesures`, distracteur reel `"4000 cm"` reecrit `"40 m"` dans le fichier de production - la mutation M3 rejouee | **1 echec**, la clause nomme l'item : *"les trois propositions [400 cm, 40 cm, 40 m] ne laissent pas le meme reste apres lecture de leur valeur ([cm, m])"*. Le garde-fou d'egalite rougit **en plus** : *"[400 cm, 40 cm, 40 m] se lisent a la meme valeur (400, 40, 40)"* |
| A2 | **rouge** | la lecture mutee pour rendre toujours un reste vide, production intacte | **2 echecs sur 8** : la mise a l'epreuve derivee et le paquet neuf a unites melangees. Une clause qui ne compare plus rien ne peut plus passer |
| B1 | **vert** | contenu de `marc-dev` (`066f857`) depose en production, clause amendee | **0 echec** sur les deux classes concernees, **23 tests**. Les sept items "decomposer" laissent tous un reste vide - une somme est consommee en entier |
| B2 | **vert** | le meme contenu, clause d'avant l'amendement | **1 echec sur 7**, les sept items nommes : la preuve que B1 corrige un faux positif et n'endort pas la clause |

**La mise a l'epreuve derivee, et pourquoi elle remplace ma fixture.** Le paquet neuf
`paquet-neuf-unites-melangees` est ecrit de ma main : il prouve que la clause mord sur ce que
j'imagine, ce qui est la faute de s05 exactement. La mise a l'epreuve neuve part de **chaque** item
du domaine de la production, et remplace le reste d'un seul de ses distracteurs par un autre reste
**que la production ecrit ailleurs** - `"4000 cm"` devient `"4000 m"`, M3 appliquee a tous les items
a la fois. Deux faits sont exiges ensemble : la production telle qu'elle est ecrite ne laisse aucun
item a restes differents, et **chacun** de ses items mutes en laisse un. L'ensemble des restes est
mesure sur le corpus, aucune unite n'est nommee.

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
2. **L'unite n'est ni lue ni convertie ; ce n'est meme pas l'unite qui est exigee identique, c'est le
   reste** (*reecrit par l'amendement du 2026-08-15 - la formulation d'origine parlait d'unite parce
   que la clause croyait comparer des unites*). "20 cm" et "20 m" ont la meme valeur pour ce depot.
   La clause 5 rend cette cecite inoffensive **a l'interieur d'un
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

   > **Note du 2026-08-16 - cette non-preuve etait fausse le jour ou je l'ai ecrite, et
   > l'amendement l'a rendue vraie.** `lea-dev` m'avait laisse une non-preuve qui s'est verifiee
   > (la clause d'unite rougissait a tort sur le contenu corrige) ; j'ai cherche le symetrique -
   > une clause qui **ne rougit plus** sur un cas qu'elle attrapait avant l'amendement - et il
   > existe : c'est celui-ci. Sur `["1 h 30", "90", "45 min"]`, le motif d'avant l'amendement - les
   > chiffres, le signe et les trois ecritures du separateur de milliers retires en tete, repris tel
   > quel de `c6b7745` - rendait les residus `[h 30, , min]` et **rougissait** ; la clause du reste
   > rend `[]` et passe. La phrase ci-dessus
   > figurait deja dans la version initiale de cet ADR (`780e638`), ou elle etait donc **fausse** :
   > la clause de l'epoque ne les laissait pas passer.
   >
   > **Mesure du symetrique sur du materiau reel, pas sur une fixture.** Sonde jetable non commitee,
   > les deux clauses evaluees cote a cote sur tout le contenu chargeable du module - les douze
   > paquets de production et les paquets de contre-exemple - JDK 21.0.10, arbre `4000bab` :
   > **32 divergences, toutes dans le sens "ancienne rouge, nouvelle verte", aucune dans l'autre
   > sens.** Sept sont les items "decomposer" de `cm1-grands-nombres`, le faux positif que
   > l'amendement corrige. Vingt-quatre sont les douze items de
   > `paquet-neuf-durees-toujours-la-plus-grande` et de sa version corrigee, comptes une fois
   > chacun par racine : l'ancienne clause y opposait `h 50` a `h 10` comme deux unites
   > differentes, ce qui etait faux aussi - et invisible, ce paquet appartenant au garde-fou de rang
   > et non au perimetre de la clause. La derniere est l'item unique de
   > `paquet-neuf-valeurs-egales`, deux decompositions dont l'ancienne clause opposait les queues de
   > somme. **Aucune vraie prise n'est donc perdue sur ce que le depot
   > ecrit** ; la seule perte est le cas de cette non-preuve, qu'aucun paquet n'ecrit - aucun paquet
   > de production ne porte de duree au 2026-08-16.
   >
   > **La lecon, distincte de celle de la ligne 1.** Une non-preuve est une affirmation sur le code,
   > au meme titre qu'un chiffre, et celle-ci n'avait jamais ete executee : je l'avais deduite de ce
   > que la clause **voulait** faire, pas de ce qu'elle faisait. Une non-preuve qui decrit un cas
   > concret se mesure comme une morsure, dans le sens vert, avant d'etre publiee.
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
8. **La somme ne connait qu'un operateur, et c'est `+`.** "600 000 - 40" n'est pas une somme : la
   lecture retombe sur le nombre de tete, rend 600 000, et laisse "- 40" derriere elle. La valeur est
   alors fausse. La clause 5 amendee en attrape une partie - deux ecritures soustractives dont la
   partie retranchee differe laissent des restes differents et rougissent - **et je n'irai pas plus
   loin que ca** : quand la partie retranchee est identique entre les trois propositions, le reste
   l'est aussi, la clause est muette, et l'ordre se trouve conserve parce que la meme quantite est
   retranchee partout. Ce dernier point est une **observation, pas une preuve** : rien ici ne
   demontre qu'un suffixe identique preserve l'ordre pour toute ecriture qu'un enseignant pourrait
   choisir. **Ce qui me fera changer d'avis** : le premier item de production qui ecrit autre chose
   qu'une somme ou un nombre dans une proposition. Aucun ne le fait au 2026-08-15.
9. **Une somme mal formee n'a pas de valeur, et rien ne distingue ce silence d'une proposition
   litterale.** "40 000 + sept cents" tombe hors du domaine par le meme chemin que "soixante-dix
   mille dix-huit". La clause 4 le compte comme un item ecarte a declarer, ce qui le rend visible ;
   elle ne dit pas **pourquoi** il est ecarte.
10. **Rien ne verifie qu'une decomposition est canonique.** "500 000 + 100 040" vaut 600 040 comme
    "600 000 + 40" : la lecture les tient pour egales, ce qui est arithmetiquement vrai et
    pedagogiquement faux - une seule des deux decompose en valeurs de position. Le garde-fou
    d'egalite rougirait si les deux ecritures se trouvaient dans un meme item ; entre deux items, il
    ne dit rien. **Ce controle-la est pedagogique et appartient a `nadia-prof`**, qui l'exerce
    distracteur par distracteur ; je ne l'encode pas, et l'ecrire ici est la seule facon de ne pas
    laisser croire que la lecture s'en charge.

### Ce que l'amendement du 2026-08-15 ne prouve pas

11. **Plus la lecture avale, moins la clause 5 peut parler, et c'est structurel.** Le reste est le
    complement de la lecture : chaque cas neuf ajoute a `lireValeur` rend vide un reste qui ne
    l'etait pas, et retire donc de la vue de la clause toute une famille d'ecritures. C'est
    exactement ce que l'amendement vient de faire aux sommes, a bon droit ici puisque la lecture les
    lit **correctement**. Mais si un cas futur consommait une ecriture en la lisant **mal** - une
    multiplication prise pour une somme - la clause deviendrait muette sur precisement l'ecriture mal
    lue, sans qu'aucun diff ne le montre. C'est le prix de n'avoir qu'une seule lecture, et je le
    paie sciemment : deux lectures divergent, une lecture unique se tait. **Obligation qui en
    decoule** : tout cas ajoute a `lireValeur` s'accompagne de la mesure de ce que la clause 5 cesse
    de voir. Cet amendement la fournit pour la somme (morsures B1 et B2).
12. **Le reste vide est desormais atteint par trois ecritures, et la clause ne les distingue pas.**
    "1 h 30" (90), "90" et "40 000 + 50" laissent tous les trois un reste vide. La non-preuve 3
    disait deja cela de la duree et du nombre nu ; la somme s'y ajoute. Aucun item du depot ne
    melange ces ecritures au 2026-08-15, et le garde-fou d'egalite rougirait si deux d'entre elles se
    lisaient a la meme valeur dans un meme item - mais si elles se lisent a des valeurs differentes,
    rien ne verifie que la comparaison ait un sens.
13. **La mutation derivee prouve que la clause voit un reste change, pas qu'elle voit un ordre
    faux.** Elle mute un distracteur reel et exige un rouge ; elle ne verifie pas que le rang de
    l'item bascule. Un paquet dont **les trois** propositions porteraient la meme unite fausse -
    tous les items en "cm" alors que la question porte sur des metres - laisse un reste identique,
    reste vert, et rien ici ne peut le voir. C'est `nadia-prof`, comme la non-preuve 5.
14. **Le mot "unite" a disparu de la clause, la cecite aux unites n'a pas disparu du depot.**
    L'amendement change ce que la clause compare, pas ce que la lecture comprend : la non-preuve 2
    reste vraie mot pour mot, et le jour ou un item legitime demandera de comparer 1 m, 50 cm et
    200 mm, la clause rougira toujours, et il faudra toujours un `ADR` pour trancher.

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
- **Amendement du 2026-08-15** : un test de plus, la mise a l'epreuve derivee. Le depot passe de 486
  a **487 tests**, le compte des regles ArchUnit reste a **28** avec sa methode. Aucune ligne de
  production ne change non plus, et le lot de `marc-dev` cesse d'etre bloque par un rouge qui n'etait
  pas le sien.

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
