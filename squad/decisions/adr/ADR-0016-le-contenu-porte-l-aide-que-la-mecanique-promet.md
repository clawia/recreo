# ADR-0016 - Faire porter par le contenu l'aide que la mecanique promet, et deriver le perimetre du garde-fou du repertoire de contenu

- **Statut** : Accepte
- **Date** : 2026-08-06
- **Auteur** : theo-archi
- **Sprint** : s05 (ouverture, avant planning)

## Contexte

Depuis S3-2, la mecanique bandit manchot **offre** une aide : apres trois manches ratees
consecutives, la manche suivante affiche l'indice que son contenu lui associe
(`BanditManchotSession.tirerManche`, `indiceAffiche = declencheAide ? manche.indice() :
Optional.empty()`). Depuis S3-3 et `DEC-0005`, cet indice vient du paquet et non d'une constante
Java : `cp-additions-jusqu-a-10.yaml` le declare sous `parametres.indice`, et
`AdditionsBorneesContentSource` le pose sur chaque item qu'il tire.

`nadia-prof` a joue une partie complete de dix manches de `bandit-manchot-le-bon-son-cp` en
repondant faux a chaque manche. **Aucun indice n'est jamais apparu**
(`squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`, motif 5). Elle a trouve la cause seule,
et elle est exacte : `QuestionContentSourceFactory` construisait chaque item avec le constructeur a
quatre arguments de `Question`, celui qui laisse l'indice a `Optional.empty()`. Un paquet
`type: question` ne pouvait porter aucune aide, quoi qu'un auteur ecrive dans son YAML. La meme
limite est portee en reserve 6 de `FICHE-bandit-manchot-homophones-cm1.md` : elle touche les deux
paquets `question` du depot, donc tout le francais livre a ce jour.

Ce qui rend ce defaut interessant n'est pas la ligne fautive, qui se corrige en trois lignes. C'est
sa forme :

- **l'abstraction ne manquait pas.** `Question` porte un champ `indice` depuis S1-5, documente
  "l'indice est optionnel et vient du contenu, jamais de la mecanique". Ce n'est donc pas la dette
  numero 1 de `main` (une abstraction citee partout et jamais implementee) : l'abstraction existe,
  elle est correcte, et **un seul de ses deux appelants la remplit** ;
- **rien ne comparait les deux cotes.** La mecanique promet ; le contenu fournit. Aucun test ne
  confrontait la promesse a ce que le contenu sait reellement donner. Le contrat est verifie de
  chaque cote separement (`BanditManchotTest` prouve que la mecanique affiche l'indice quand l'item
  en porte un ; `YamlContentPackLoaderTest` prouve que le paquet d'additions en porte un), et le
  trou est exactement entre les deux ;
- **le mode de defaillance est le silence.** L'enfant ne voit pas une erreur, il voit une aide qui
  ne vient pas. La CI est verte, la fiche de conformite est le seul endroit du depot ou le defaut
  apparait, et il a fallu qu'une adulte perde volontairement dix manches pour le trouver.

C'est la forme de defaut que ce depot combat depuis `ADR-0013` : une promesse sans garde-fou. La
question a trancher n'est donc pas "comment brancher l'indice sur `type: question`" - c'est **ou
doit vivre l'indice pour qu'un type de contenu ne puisse pas l'oublier en silence**, aujourd'hui et
pour le troisieme type de contenu qui arrivera.

## Decision

1. **L'aide est un texte du contenu, attache a l'item, et la mecanique ne decide que du moment ou
   elle s'affiche.** C'est `DEC-0005` etendu au type `question` : la cle est `indice`, ecrite sur
   l'item pour un paquet enumere, sous `parametres` pour un paquet combinatoire dont les items sont
   engendres. Aucune autre forme n'est ajoutee.

2. **La fabrique de chaque type de contenu lit ce champ.** `QuestionContentSourceFactory` le lit
   desormais ; le constructeur a quatre arguments de `Question` reste pour les tests qui ne
   s'interessent pas a l'aide, il n'est plus utilise par une fabrique.

3. **Tout paquet du depot porte un indice sur chacun de ses items.** Pas "au moins un item" : la
   promesse de la mecanique se tient manche par manche, et l'enfant qui echoue trois fois recoit la
   manche suivante, pas une manche choisie. Un item sans indice fait echouer la construction.

4. **Le perimetre de ce garde-fou derive du repertoire de contenu, jamais du catalogue.** C'est une
   consequence directe de `DEC-0009`, qui vient de retirer un jeu du catalogue en laissant son
   paquet en place : un garde-fou derive du catalogue aurait perdu de vue `cp-le-bon-son` le jour
   meme ou son defaut a ete trouve. Le repertoire est la seule source qui dise quels paquets
   existent.

5. **Les paquets ecrits avant cet ADR sont nommes dans une dette plafonnee**, parce qu'ecrire un
   indice est du contenu pedagogique et que ce n'est pas mon mandat. La dette ne peut pas grandir,
   ne peut pas pourrir, et son mode de defaillance est un rouge.

Ce que cet ADR ne decide pas : **ce que dit un indice**. Le texte est du contenu pedagogique, il
appartient a l'auteur du paquet et passe la porte de `nadia-prof` comme tout texte lu par un
enfant. Un garde-fou peut exiger qu'il existe ; il ne peut pas juger qu'il aide.

## Regle executable

Fichier :
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/ChaquePaquetPorteUnIndiceTest.java`

Ce n'est pas une `ArchRule`, et je dis pourquoi plutot que de forcer la forme : ArchUnit juge des
types, des paquetages et des dependances. Ici la propriete a garder n'est pas structurelle, c'est
une propriete de **donnee** - ce que la chaine schema/fabrique/source de contenu rend a la
mecanique. Aucune regle sur le graphe des classes ne l'atteint : la version fautive et la version
corrigee de `QuestionContentSourceFactory` ont exactement les memes dependances. Le mecanisme
automatique est donc un test JUnit, qui echoue dans `mvn verify` comme n'importe quelle regle
(`ADR-0013` a deja pose que l'exigence de morsure vaut pour tout garde-fou, quelle que soit sa
technologie).

Le perimetre derive du repertoire (obligation 1) :

```java
    static List<String> paquets(Path racine) {
        Path repertoire = racine.resolve("contenu");
        ...
        return arborescence.filter(Files::isRegularFile)
                .map(fichier -> fichier.getFileName().toString())
                .filter(nom -> nom.endsWith(".yaml"))
                .map(nom -> nom.substring(0, nom.length() - ".yaml".length()))
                .sorted()
                .toList();
    }
```

La derivation est totale (obligation 2) : chaque paquet trouve est charge **par le chargeur reel**,
ses items sont tires par la meme chaine qu'un enfant traverse, et chacun doit porter un indice.

```java
            List<Question> items = itemsTires(new YamlContentPackLoader(racineClasspath).chargerPaquet(paquet));
            for (Question item : items) {
                assertThat(item.indice())
                        .as("paquet %s, item \"%s\" : la mecanique promet une aide apres trois echecs"
                                + " consecutifs (S3-2) et ne peut afficher que ce que le contenu lui"
                                + " donne. Un item sans indice rend cette promesse muette pour"
                                + " l'enfant qui echoue", paquet, item.enonce())
                        .isPresent();
```

Le tirage passe par `ContentSource.next` et non par une relecture du YAML : c'est **au milieu de
la chaine** que l'indice se perdait, et un garde-fou qui relirait le fichier aurait ete vert sur le
paquet le jour du refus.

### Ce qui est prouve, mesure et non suppose

Jeu fautif :
`adapters/adapter-content/src/test/resources/contreexemple/ChaquePaquetPorteUnIndiceTest/`.

| Objet du jeu fautif | Ce qu'il doit provoquer |
|---|---|
| `contenu/paquet-neuf-sans-indice.yaml` | un paquet neuf, jamais livre, qu'aucune liste ne nomme : **rouge**, en le nommant |
| `corrige/contenu/paquet-neuf-sans-indice.yaml` | le meme paquet, indices ecrits : **vert** |

Le second n'est pas une politesse : sans lui, un garde-fou rouge sur tout aurait l'air de mordre
sans rien distinguer, mode de defaillance que la lecon d'`ADR-0003` impose de fermer. Il porte en
plus la seule preuve qui compte pour la correction de code : il est de `type: question`.

Trois mutations mesurees, puis annulees :

| Mutation | Resultat |
|---|---|
| Vider la dette (`Set.of()`) | rouge : `paquet cm1-homophones, item "Ils ___ un chien."` |
| Ne laisser que `cm1-homophones` dans la dette | rouge : `paquet cp-le-bon-son, item "LAPIN"` |
| **Rendre a `QuestionContentSourceFactory` son constructeur a quatre arguments** | rouge : `leMemePaquetMuniDUnIndicePasseLeGardeFou`, `paquet paquet-neuf-sans-indice` |

La troisieme est la mesure qui compte, et elle repond a l'objection qu'on peut faire a cette regle :
les deux seuls paquets `type: question` du depot sont dans la dette, donc **la regle ne prouverait
rien sur le type de contenu qu'elle vient de corriger** si elle ne tenait qu'aux paquets de
production. Le paquet fautif corrige comble exactement ce trou : il est de `type: question`, il est
hors dette par construction, et il redevient rouge le jour ou quelqu'un defait la correction. La
regle mord donc sur le code aujourd'hui, et elle mordra sur le contenu quand la dette sera fermee.

Etat mesure a l'ecriture de cet ADR, `mvn -B clean verify` sur JDK 21.0.10 : **BUILD SUCCESS,
189 tests, 0 echec** (38 `domain`, 7 `application`, 71 `adapter-web`, 14 `adapter-content`,
57 + 2 `bootstrap`).

### La dette, et ce qu'elle attend

```java
    private static final Set<String> DETTE_DE_CONTENU = Set.of("cm1-homophones", "cp-le-bon-son");

    /** Etat au 2026-08-06, ouverture de s05. Ce nombre ne remonte jamais. */
    private static final int PLAFOND_DE_DETTE = 2;
```

Deux paquets, les deux seuls `type: question` du depot. Ecrire leurs indices est du contenu : cela
appartient aux stories S5-2 a S5-6 et repasse devant `nadia-prof`. La dette est fermee le jour ou
ces textes existent, pas par un geste d'architecte. Elle ne peut pas grandir : un paquet neuf sans
indice est rouge et n'y entre pas. Elle ne peut pas pourrir : chaque nom doit designer un paquet qui
existe.

Ce que je dis franchement, comme pour la dette d'`ADR-0013` : rien n'oblige mecaniquement ce
plafond a descendre. Ce qui l'y pousse est que la reserve 6 de la fiche CM1 et le motif 5 de la
fiche CP le demandent tous les deux nommement, et qu'ils sont convertis en stories.

> **Amendement du 2026-08-07 (porte architecture de s05) - la dette est fermee, et le plafond ne
> l'etait pas.** `lea-dev` a ecrit les 24 indices de `cp-le-bon-son`, `marc-dev` ceux de
> `cm1-homophones` ; `DETTE_DE_CONTENU` est `Set.of()` et les deux seuls paquets `type: question` du
> depot passent la regle sur tous leurs items. Le paragraphe ci-dessus avait raison : rien n'a
> oblige le plafond a descendre, et il **n'est pas descendu tout seul**. Il est reste a 2 alors que
> l'ensemble etait deja vide, ce qui laissait deux places libres pour exempter en silence un paquet
> neuf sans indice - exactement le vert silencieux que le cliquet existe pour fermer. Le commentaire
> du champ, qui presentait ce plafond comme "la trace de cette fermeture", disait donc faux. Plafond
> porte a **0** dans le meme geste que cette confirmation. C'est la seule chose que le planning de
> s05 me demandait de confirmer ici et que ma premiere `confirmation-archunit.md` avait omise ;
> l'ecart m'a ete signale par le `relecteur-pedagogique`, pas trouve par moi.

## Ce que cette regle ne couvre pas, dit avant qu'on le decouvre

- **Elle ne juge pas la qualite de l'indice.** `"aaa"` la satisfait. Un indice qui donne la reponse,
  ou qui n'apprend rien, est un defaut pedagogique et se traite a la porte de `nadia-prof`. C'est
  aussi le risque principal de cet ADR : rendre l'indice obligatoire peut produire des indices de
  remplissage. La contre-mesure n'est pas un automate, c'est que le texte est lu par un enfant et
  donc audite.
- **Elle ne prouve pas que la mecanique l'affiche.** C'est le travail de `BanditManchotTest` depuis
  S3-2, et de `CatalogueChargeableIT` qui verifie qu'une partie compte assez de manches pour que
  l'aide reste atteignable (`ADR-0012`, `longueurMinimale`). Les trois se completent et aucun ne
  remplace les autres.
- **Elle ne voit qu'un paquet range sous `contenu/`.** Un contenu charge autrement - depuis une
  base, un jour ou `adapter-persistence` existera - lui echapperait. Le jour ou cela arrive, c'est
  le point de derivation qu'il faut deplacer, pas la regle.
- **Un paquet combinatoire ne s'epuise jamais** : le tirage est borne a 60 items. Un paquet dont
  seul le 61e item serait depourvu d'indice passerait. Aucun paquet enumere du depot n'approche
  cette taille, et un paquet combinatoire pose son indice une fois pour tous ses items.
- **Elle ne couvre pas les autres textes d'accompagnement.** Le motif 3 du refus de `nadia-prof`
  demande un retour qui explique une variation graphemique ; `Verdict` ne porte aujourd'hui que
  `juste` et `bonneReponse`, et aucun champ d'explication n'existe. C'est le meme trou, sur un autre
  texte. **Je ne l'ouvre pas ici** : on ne cree pas un champ pour un usage qu'aucune story n'a
  encore decrit (`ADR-0005`), et le moment ou un tel texte s'afficherait est une decision
  pedagogique, pas une decision d'architecture. Quand la story existera, elle prendra la meme forme
  que celle-ci et pourra reutiliser ce garde-fou en changeant son critere.

## Consequences

- Tout paquet neuf coute un indice par item. Pour un paquet enumere de 24 items, c'est 24 textes a
  ecrire et a faire relire. C'est cher, et c'est le prix de la promesse : la mecanique annonce une
  aide, un contenu qui n'en a pas ne devrait pas etre jouable.
- Le depot gagne un troisieme garde-fou a perimetre derive (apres les gabarits et les ressources
  tierces), et le premier dont la population est du **contenu**. Le contenu cesse d'etre une zone ou
  seule la relecture humaine juge.
- La correction de code est minuscule et le garde-fou est plus gros qu'elle. C'est assume : ce qui
  est cher a produire ici n'est pas la correction, c'est la garantie qu'elle ne se defait pas et
  qu'un troisieme type de contenu ne rouvrira pas le meme trou.
- `PaquetsFrancaisS04Test` et `YamlContentPackLoaderTest` ne sont pas touches. Le nouveau garde-fou
  ne remplace aucun test existant, il en comble l'intervalle.

## Alternatives considerees

- **Rendre `indice` obligatoire dans le chargeur** (`IllegalStateException` comme pour `enonce`).
  C'est la solution la plus forte en apparence - le contenu fautif ne se charge plus du tout - et je
  l'ecarte sur un fait mesure : le chargeur n'est appele au demarrage que pour les paquets
  **references par le catalogue**, et `DEC-0009` vient de sortir du catalogue precisement le paquet
  concerne. Un paquet retire serait donc reste hors de portee de sa propre regle. Le garde-fou
  derive du repertoire les voit tous, et il echoue a la construction, plus tot qu'au demarrage. Cela
  reste l'option a reprendre le jour ou la dette sera fermee : elle deviendra alors gratuite.
- **Un indice unique au niveau du paquet pour les paquets `question`**, comme pour les additions.
  Ecarte : les items d'un paquet enumere sont ecrits un par un, et une aide utile pour "MAMAN" ne
  l'est pas pour "DENT" - la ou une aide de calcul ("Compte sur tes doigts.") vaut pour toutes les
  additions. Ce qui me ferait changer d'avis : un paquet ou le meme texte est recopie sur chaque
  item ; ce serait visible en diff, et j'ajouterais alors un defaut de paquet, pas avant.
- **Un indice par defaut porte par la mecanique.** Ecarte par `ADR-0004` et `DEC-0005` : la
  mecanique ne connait ni matiere ni niveau, et un texte generique ("Reflechis bien.") serait une
  aide qui n'aide pas, avec en prime un vert permanent sur ce garde-fou.
- **Une regle ArchUnit qui interdirait a une fabrique d'appeler le constructeur a quatre
  arguments.** Tentant, et decoratif : elle se contourne en passant `Optional.empty()` au
  constructeur a cinq arguments, et elle ne dit rien du contenu, qui est l'objet reel de la
  promesse. Elle interdirait une ecriture, pas un defaut.
- **Laisser `nadia-prof` le trouver en jouant.** C'est ce qui s'est passe, et cela a coute un refus,
  un retrait de catalogue et un sprint. L'axiome du depot ferme cette option.

## Ce qui me ferait changer d'avis

- **Si la dette n'est pas fermee a la cloture de s05**, c'est que l'obligation d'indice n'est pas
  tenable au rythme ou le contenu est ecrit. Je le dirai, et je retomberai sur une exigence plus
  faible mais reelle : un indice par paquet, avec la consequence pedagogique assumee et ecrite.
- **Si un paquet arrive avec un indice de remplissage** que `nadia-prof` refuse, la regle aura
  produit du bruit plutot que de l'aide, et il faudra deplacer l'exigence au moment ou l'increment
  entre en audit plutot qu'a la construction.
- **Si un troisieme type de contenu ne peut structurellement pas porter d'aide** (une mecanique ou
  l'aide n'a pas de sens), alors l'exigence n'appartient pas au contenu mais au couple
  mecanique/contenu, et c'est `GameMechanic` qui devra declarer s'il promet une aide - la regle
  deviendrait conditionnelle a cette declaration.
