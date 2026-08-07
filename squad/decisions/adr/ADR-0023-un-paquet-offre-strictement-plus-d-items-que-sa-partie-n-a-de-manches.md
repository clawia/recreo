# ADR-0023 - Exiger de tout jeu du catalogue qu'il offre strictement plus d'items que sa partie n'a de manches, et deriver ce controle du catalogue

- **Statut** : Accepte
- **Date** : 2026-08-07
- **Auteur** : theo-archi
- **Sprint** : s06

## Contexte

Deux defauts distincts naissent du meme rapport de tailles, et aucun des deux ne fait de bruit.

**Le premier est un arret avant l'heure.** `ListContentSource.next` rend `Optional.empty()` quand le
vivier est epuise. `BanditManchotSession.tirerManche` traite ce vide comme une fin de partie : la
session passe en `TERMINEE`, l'ecran de retour s'affiche, il n'y a **ni erreur, ni message, ni
trace**. Un paquet de six items dans une partie de dix manches se termine donc a la sixieme, et rien
ne le dit - c'est exactement ce que j'ai mesure sur `contenu-repris/textes-trous/cm1-conjugaison.yml`
en instruisant S6-3, et c'est la raison pour laquelle j'ai ecrit dans mon avis de faisabilite qu'il
n'y avait "aucun garde-fou" pour le voir.

**Le second est une absence de variete.** Un paquet aussi long que la partie presente exactement tous
ses items a chaque partie, dans un ordre different au mieux. C'est la reserve que `nadia-prof` a posee
en s04 sur les homophones - dix items pour dix manches - et le premier critere de S5-7 en est ne.

**Ce qui gardait la propriete, et ce que ce garde-fou ne pouvait pas voir.** La propriete etait
verifiee deux fois, par deux methodes de `PaquetsFrancaisS04Test` qui nommaient chacune leur paquet
dans leur titre : `homophonesCm1CompteDavantageDItemsQueDeManchesParPartie` et
`conjugaisonPresentCm1CompteDavantageDItemsQueDeManchesParPartie`. C'est un perimetre enumere, la
faute qu'`ADR-0013` interdit nommement, et le sprint en a fourni la demonstration : l'entree
`bandit-manchot-conjugaison-present-cm1` est arrivee au catalogue en s06, et `marc-dev` a du ecrire
lui-meme la seconde methode pour la couvrir - honnetement signale par lui comme satisfait "au niveau
de son paquet seulement". La troisieme entree qui arrivera n'aura personne pour l'y inscrire.

J'avais nomme ce trou dans mon avis de faisabilite sans le refermer, et j'avais dit pourquoi : le
garde-fou n'a de sens qu'attache au critere qui tranche entre "moins de manches" et "plus d'items",
qui est un choix de produit. Le choix est fait (douze items pour dix manches, `marc-dev`), le paquet
est livre, la fermeture est due.

## Decision

**Tout jeu du catalogue offre strictement plus d'items distincts que sa partie n'a de manches, et le
controle derive son perimetre du catalogue lui-meme.**

Trois precisions qui sont la decision, pas de la mise en oeuvre :

1. **L'inegalite est stricte.** L'egalite n'est pas un cas limite tolerable, c'est le second defaut
   ci-dessus. Une inegalite large refuserait l'arret avant l'heure et accepterait l'absence de
   variete.
2. **Le perimetre est le catalogue, pas le repertoire de contenu.** C'est la difference avec
   `ADR-0016`, et elle se justifie : `nombreDeManches` est un attribut de l'association mecanique +
   paquet (`ADR-0012`), il n'existe nulle part ailleurs. Un paquet sans entree de catalogue n'a aucun
   nombre auquel etre compare, et le juger serait inventer une valeur. Consequence assumee et
   nommee : `cp-le-bon-son`, retire du catalogue par `DEC-0009`, **n'est pas juge par cette regle**
   tant qu'il n'y revient pas. Le jour ou il y revient, il y entre sans qu'on l'inscrive.
3. **Le comptage s'arrete au minimum a prouver.** La propriete est un minimum, pas une taille, et un
   paquet combinatoire n'a pas de taille - `AdditionsBorneesContentSource.next` ne rend jamais
   `Optional.empty()`. Compter jusqu'a epuisement ferait donc boucler le controle sur le jeu
   d'additions au lieu de le faire rougir.

## Regle executable

`adapters/adapter-content/src/test/java/com/recreo/adapter/content/ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest.java`

### Pourquoi ce n'est pas une regle ArchUnit

ArchUnit lit du bytecode : des types, des paquets, des dependances. La propriete porte sur le
**nombre d'items qu'un fichier YAML enumere** compare a un entier ecrit dans un autre fichier YAML.
Aucune analyse statique de types ne l'atteint. Le mecanisme automatique est un test de balayage du
module `adapter-content`, execute par `mvn -B verify` comme n'importe quel autre test, et recense par
`LesGardeFousSontMisALEpreuveTest` au meme titre - avec une reserve que je nomme plus bas.

### Ce que la regle fait

Le perimetre vient de `new YamlContentPackLoader(racine).games()`, c'est-a-dire du chargeur reel lisant
`catalogue.yaml` : jamais une liste de noms de paquets ni de jeux. Pour chaque entree, les items sont
tires par `ContentSource.next` avec le tirage deja-vu qui grossit - la chaine complete qu'un enfant
traverse - jusqu'a en obtenir `nombreDeManches + 1` distincts ou epuiser un budget de vingt tirages par
item vise. Le compte obtenu est confronte a `nombreDeManches` par un `isGreaterThan`.

La derivation est totale (`ADR-0013` obligation 2) : chaque entree du catalogue produit une assertion,
y compris une entree dont le paquet ne rend aucun item.

### La preuve que la regle mord

Trois mesures, toutes faites sur un JDK 21.0.10 designe par `JAVA_HOME` le temps de la commande, et
toutes apres un `clean` quand elles portent un comptage.

**Mesure 1 - mutation sur materiau reel, au cas limite exact.** `nombreDeManches` de l'entree
`bandit-manchot-conjugaison-present-cm1` porte de 10 a **12**, c'est-a-dire exactement le nombre
d'items de `cm1-conjugaison-present.yaml` (12, comptes par `grep -c "^  - enonce:"`). Resultat :
`BUILD FAILURE`, une assertion en echec, message rendu :

```
jeu bandit-manchot-conjugaison-present-cm1 : sa partie compte 12 manches, son paquet doit donc
savoir rendre au moins 13 items distincts. [...] Le comptage s'arrete a 13 : il mesure ce minimum,
pas la taille du paquet
```

Ce n'est pas une fixture de ma main : c'est le catalogue servi, a la valeur ou le defaut commence.

**Mesure 2 - la meme mutation, le garde-fou retire de l'arbre.** `mvn -B clean verify` sur le depot
mute **sans** le fichier du garde-fou : `BUILD SUCCESS`, les cinq modules verts, 259 tests. C'est la
mesure de l'angle mort ferme : un catalogue qui epuise son paquet a la douzieme manche passait la
suite entiere, y compris `CatalogueChargeableIT` qui parcourt pourtant le vrai catalogue - il ne
compare `nombreDeManches` qu'a la longueur minimale de la mecanique.

**Mesure 3 - relecture adverse de l'inegalite, executee et non argumentee.** `isGreaterThan` remplace
par `isGreaterThanOrEqualTo`, tout le reste identique. Resultat :
`uneEntreeDeCatalogueAussiCourteQueSaPartieFaitEchouerLeGardeFou` **echoue** - le jeu fautif n'est plus
refuse. C'est le point que j'avais moi-meme manque dans le plafond de dette d'`ADR-0016`, ou une
inegalite large laissait deux places libres sans qu'aucun diff ne le montre. Ici, le jeu fautif porte
**trois items pour trois manches**, l'egalite exacte : c'est la seule valeur qui distingue `>` de
`>=`, et son corrige (quatre items pour trois manches, `laMemeEntreeAvecUnItemDePlusPasseLeGardeFou`)
epingle l'autre borne - un garde-fou ecrit avec une marge le refuserait.

### Une limite du recensement, que ce garde-fou met en lumiere

`LesGardeFousSontMisALEpreuveTest` reconnait un garde-fou de balayage a la presence de la chaine
`src/main/` dans sa source. Ce garde-fou-ci lit le contenu de production **par le classpath**, sans
jamais ecrire ce chemin : le recensement ne le compte donc pas parmi les balayages et ne lui reclame
pas de jeu fautif. Il en a deux, mais c'est ma discipline qui les a mis la, pas un automate.
Le critere du recensement est enumere (une chaine), ce qu'`ADR-0013` permet pour un critere ; il faut
seulement savoir que son mode de defaillance est ici un **oubli tolere**, pas un rouge. Je le nomme
plutot que de le laisser decouvrir, et je ne l'elargis pas dans le meme geste : "toute source de test
qui lit un artefact de production" n'a pas de definition executable que je sache ecrire aujourd'hui
sans arbitraire.

## Consequences

- Une entree de catalogue neuve entre dans le controle le jour ou elle est ecrite, sans etre inscrite
  nulle part. C'est le seul point qui compte.
- Les deux methodes enumerees de `PaquetsFrancaisS04Test` sont **supprimees** plutot que laissees en
  double. Laisser coexister un perimetre enumere avec un perimetre derive n'ajoute aucune couverture
  et laisse croire que la propriete se verifie paquet par paquet. Les criteres qu'elles portaient
  (S5-7 premier critere, S6-3 quatrieme critere) sont tenus, et pour toutes les entrees.
- **Mauvaise consequence, assumee** : le controle ne juge aucun paquet hors catalogue. `cp-le-bon-son`
  est aujourd'hui hors de portee, alors que c'est precisement le paquet que le sprint recompose. La
  propriete n'a pourtant pas de sens sans un `nombreDeManches`, et l'inventer serait pire ; c'est le
  jour de son retour au catalogue que le controle l'attrape, et il l'attrapera avant `nadia-prof`.
- **Seconde mauvaise consequence** : le comptage est borne, donc le message d'echec dit "au moins n
  items" et non "ce paquet en compte k". Un auteur qui cherche a savoir combien il en manque doit
  compter lui-meme. J'accepte cette pauvrete de diagnostic pour ne pas avoir a distinguer, dans le
  code du garde-fou, un vivier fini d'un vivier combinatoire.
- Le budget de tirages (vingt par item vise) est une borne arbitraire, et je le dis : elle protege
  d'une boucle infinie sur un paquet combinatoire trop pauvre. Un paquet qui l'atteindrait sans avoir
  trouve ses items rougirait, ce qui est le bon sens de la defaillance.

## Alternatives considerees

**Deriver du repertoire de contenu, comme `ADR-0016`.** Ecartee : un paquet sans entree de catalogue
n'a pas de nombre de manches. Il aurait fallu inventer une valeur par defaut, c'est-a-dire fabriquer
un chiffre pedagogique dans un test - ce qui n'est pas mon mandat.

**Compter les items en relisant le YAML.** Ecartee pour la raison d'`ADR-0016` : c'est en chemin,
entre le fichier et la mecanique, que la chaine perd des choses. Un paquet dont la fabrique
dedoublonnerait les items serait vert sur son YAML et court en jeu.

**Enumerer les items jusqu'a epuisement.** Ecartee : ne termine pas sur un paquet combinatoire. Le
garde-fou aurait boucle sur `bandit-manchot-additions-cp`, ce qui est un mode de defaillance pire
qu'un faux vert - il n'y a plus de rapport du tout.

**Faire echouer le chargement au demarrage** (`YamlContentPackLoader` refuse une entree trop courte).
Ecartee, et c'est l'alternative la plus tentante : elle rendrait le defaut structurellement
impossible. Deux raisons de ne pas la retenir aujourd'hui. Elle deplace un controle de coherence
pedagogique dans le chemin de demarrage de l'application, ou son mode de defaillance est un ecran
d'erreur pour un enfant au lieu d'un rouge pour un developpeur. Et elle obligerait le chargeur a
enumerer les items de tout paquet, y compris combinatoire, donc a distinguer deux formes de contenu
qu'`ADR-0007` a justement unifiees. **Ce qui me ferait changer d'avis** : une entree de catalogue
ecrite ailleurs que dans le depot - un catalogue fourni a l'execution. Le test ne verrait plus rien,
et le controle devrait alors vivre dans le chargeur.
