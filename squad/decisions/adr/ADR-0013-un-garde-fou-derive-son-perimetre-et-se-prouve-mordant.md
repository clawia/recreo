# ADR-0013 - Un garde-fou derive son perimetre de la source, et se prouve mordant sur un objet neuf

- **Statut** : Accepte
- **Date** : 2026-08-05
- **Auteur** : theo-archi
- **Sprint** : s02 (cloture)

## Contexte

`ADR-0003` a pose que toute regle ArchUnit qui garde une decision d'ADR doit etre accompagnee d'un
contre-exemple qui la fait echouer. Ce principe a tenu deux sprints et il a paye : il a trouve
qu'`ADR-0006` ne mordait pas et qu'`ADR-0012` nommait le mauvais paquet. Mais il ne vaut que pour
les `ArchRule`, et il ne dit rien du **perimetre** d'un garde-fou. Le depot a mesure deux fois, coup
sur coup, ce que ces deux limites laissent passer.

**Fait 1, s01.** Les criteres visuels de S1-6 et S1-9 sont livres sans aucun test automatise. Releve
nommement par `yanis-qa` dans `squad/sprints/s01-lever-refus-pedagogique/rapport-qualite.md` : *"S1-6
et S1-9 n'ont recu aucun test alors que `theo-archi` les avait explicitement qualifies testables"*.
Un critere sans automate ne regresse pas bruyamment : il regresse en silence.

**Fait 2, s02.** Le test est cette fois ecrit, dans le meme commit que le code, comme la regle
l'exige. Il est vert. `nadia-prof` le casse par mutation et montre que
`TailleDesTextesEtBoutonsTest.toutBoutonActionnableEstAuMoinsAUnRemVingtCinq`, dont le nom affirme
"tout bouton actionnable", itere en fait sur **quatre selecteurs ecrits en dur** d'ou `.proposition`
est absent - c'est-a-dire exactement le bouton en defaut, celui pour lequel la story existait. Le
garde-fou couvrait tout sauf le defaut qu'il devait empecher.

**Fait 2 bis, le plus instructif.** Apres correction, la liste n'est plus ecrite en dur : elle est
derivee des **classes** des boutons rendus. `nadia-prof` remesure au lieu de croire le vert. Elle
mute `.proposition` a 1,1 rem : rouge, la regle mord. Puis elle ajoute un
`<button type="button">Rejouer</button>` **sans classe** dans `fragments/retour.html` : les six
tests restent verts. Un bouton sans classe ne produit aucun nom de classe, donc aucune assertion.
C'est la forme exacte du defaut de s01, ou "Encore !" etait un `<button>` sans classe tombe au
defaut du navigateur. Le trou n'avait pas ete ferme, il avait ete deplace d'un cran.

**Fait 3, que je constate en ecrivant cet ADR et que personne n'avait releve.** Le mecanisme
d'`ADR-0003` a lui-meme cette forme. `LesReglesMordentTest` contient vingt et une methodes ecrites a
la main, une par regle. Rien n'oblige la vingt-deuxieme regle a en recevoir une. L'inventaire est
tenu a chaque cloture dans `confirmation-archunit.md`, c'est-a-dire par la discipline de l'agent qui
cloture - ce qui, selon l'axiome du depot, est un souhait. Le garde-fou de nos garde-fous enumere
lui aussi ce qu'il verifie.

Les trois faits partagent une seule propriete, et c'est elle qu'il faut nommer.

### La propriete generale

Tout garde-fou a deux moities : un **perimetre** (les objets qu'il juge) et un **critere** (ce qu'il
leur reproche). Les deux peuvent etre enumeres ou derives, et les consequences ne sont pas
symetriques.

| | Enumere | Derive |
|---|---|---|
| **Critere** | Un motif qu'on n'a pas nomme passe. Limite visible dans le texte du test, et l'ajout d'un motif est un ajout, pas une refonte. Souvent inevitable : une liste noire ne se derive pas. | Rare et souvent impossible. |
| **Perimetre** | **Un objet qu'on n'a pas inscrit n'est jamais juge, et rien ne le dit.** Le test est vert, son nom promet le contraire, et le rapport de CI ne mentionne pas ce qu'il n'a pas regarde. | Un objet neuf est juge du jour ou il existe. |

Un perimetre enumere echoue **en silence**. C'est la seule case dangereuse du tableau, et c'est
celle ou les trois faits se rangent. D'ou la regle, en une phrase : **le perimetre se derive, le
critere peut s'enumerer.**

> **Amendement du 2026-08-14 (s12, `ADR-0039`) - la case "critere enumere" de ce tableau est trop
> indulgente, et la phrase de l'obligation 1 qui en decoule est fausse.**
>
> Cette case dit "un motif qu'on n'a pas nomme passe. Limite visible dans le texte du test", et
> l'obligation 1 en tire qu'une liste de fautes a reprocher a pour "mode de defaillance un rouge,
> pas un vert". **Mesure : c'est faux des que la promesse du garde-fou est universelle.**
> `RessourcesTiercesTest` promettait qu'aucun JavaScript de bibliotheque d'interface n'entre dans le
> depot et refusait un seul nom ; un `alpine.min.js` reel, correctement trace, est entre en laissant
> les seize tests de la classe verts. Le mode de defaillance etait bien un **vert silencieux**, sur un
> objet que le garde-fou avait pourtant rencontre - la case que ce tableau reservait au perimetre.
>
> Ce qui distingue les deux n'est pas la nature des membres de la liste mais sa **polarite** : une
> liste de refus est verte par defaut, une liste d'autorisation est rouge par defaut. `ADR-0039`
> ajoute donc la clause qui manquait - un critere se derive lui aussi des que la promesse est
> universelle et que son complement contient un objet acquerable sans decision - et porte le
> recensement executable des criteres qui restent enumeres. Les trois obligations ci-dessous ne
> changent pas ; seule la permission accordee en passant par l'obligation 1 est resserree.

Le fait 2 bis ajoute une precision que je n'aurais pas trouvee seul, et qui vient d'une mesure :
**une derivation qui passe par une propriete facultative des membres n'est pas une derivation du
perimetre.** `document.select("button, a[href]")` derive la population des boutons ;
`element.classNames()` la projette sur une propriete que tous les boutons n'ont pas, et la
projection perd sans un mot ceux qui ne l'ont pas. Une derivation doit etre **totale** : chaque
membre de la population produit au moins une assertion, ou fait echouer le test.

> **Amendement du 2026-08-06 (s05, cinquieme occurrence) - les donnees d'entree d'un test font
> partie de son perimetre.**
>
> Le defaut d'encodage de s05 (ADR-0018) a traverse la suite entiere. Mesure sur `099e750`, en lisant
> les tests plutot qu'en croyant le vert :
>
> - `PartieHttpTest` joue une partie complete de bout en bout... contre `CatalogueDeTest`, un
>   catalogue ecrit a la main dont les trois propositions sont `"1"`, `"2"` et `"3"` ;
> - `CatalogueChargeableIT` parcourt le **vrai** catalogue, derive du chargeur, mais n'appelle que
>   `demarrer` - jamais `jouer`.
>
> Aucun test du depot n'envoie donc la reponse reelle d'un paquet reel a travers la frontiere HTTP.
> Le chemin etait couvert ; les **donnees** qui l'empruntent ne l'etaient pas.
>
> C'est la meme faute que les quatre precedentes, sur une dimension que ni cet ADR ni ADR-0015
> n'avaient nommee. Un test a deux populations : les **chemins** qu'il emprunte et les **valeurs**
> qu'il y fait passer. Le tableau ci-dessus ne parlait implicitement que de la premiere. Le vert de
> `PartieHttpTest` ne dit rien des caracteres qu'on n'a pas tapes, et rien dans son nom ni dans le
> rapport de CI ne le signale. **Obligation 1 etendue : quand un garde-fou fait circuler des donnees,
> ces donnees se derivent de la source qui les gouverne** - ici le repertoire de contenu, comme
> ADR-0016 l'a fait pour les indices - et non d'un echantillon saisi a la main.
>
> **Ce que cet amendement n'encode pas, et je le dis plutot que de le farder.** Il n'existe pas de
> regle generale executable pour "un test fait circuler des donnees representatives" : la propriete
> porte sur l'intention du test, qu'aucun automate ne lit. L'encodage de cette occurrence-ci est le
> garde-fou de contenu ecrit par `lea-dev` en s05, qui doit faire jouer les reponses reelles de
> chaque paquet a travers la frontiere HTTP, et dont le perimetre derive du repertoire de contenu.
> Ce que ce garde-fou-la couvre est reel et verifiable ; la generalisation, elle, reste une doctrine
> de revue. Ne pas la deguiser en regle est le seul moyen de ne pas la croire acquise.

Et le fait 2 bis donne aussi le protocole qui distingue les deux, parce que `nadia-prof` l'a
applique sans le nommer : **muter un objet existant prouve que le critere mord ; ajouter un objet
neuf prouve que le perimetre derive.** Sa premiere mesure (`.proposition` a 1,1 rem) etait la
premiere ; sa seconde (un bouton que personne n'avait inscrit nulle part) etait la seconde, et c'est
la seconde qui a trouve le trou.

## Decision

**Le principe d'`ADR-0003` s'etend a tout garde-fou du depot, quelle que soit sa technologie, sous
trois obligations.**

1. **Le perimetre d'un garde-fou est derive de la source, jamais enumere.** Les gabarits balayes
   viennent du repertoire des gabarits, les boutons controles viennent du balisage, les modules
   parcourus viennent du pom agregateur, les fichiers juges par la CI viennent de `git ls-files`.
   Une liste ecrite a la main d'objets **a controler** est interdite. Une liste ecrite a la main de
   **fautes a reprocher** (vocabulaire interdit, motif refuse, tag d'agent connu) reste permise :
   c'est un critere, et son mode de defaillance est un rouge, pas un vert.

2. **La derivation est totale.** Tout membre de la population derivee produit au moins une
   assertion. Un membre qui ne porte pas ce dont la derivation a besoin - un bouton sans classe -
   est un echec du garde-fou, jamais un membre ignore.

3. **Un garde-fou se prouve mordant sur un objet neuf.** Il possede un jeu d'artefacts fautif qui
   contient au moins un objet **absent de la production**, et un test qui l'execute sur ce jeu en
   exigeant le rouge. Une mutation d'un objet existant ne suffit pas : elle prouve le critere, pas
   le perimetre.

Modalites d'application :

- Le jeu fautif d'un garde-fou nomme `XxxTest` vit dans
  `<module>/src/test/resources/contreexemple/XxxTest/`. C'est le pendant, pour les artefacts, de ce
  que les classes `...ContreExemple` sont pour les regles ArchUnit (`ADR-0003`).
- Le garde-fou est donc ecrit comme une fonction de sa racine d'artefacts, et non cable sur
  `src/main/resources`. C'est la seule contrainte d'ecriture que cet ADR impose.
- Pour une `ArchRule`, l'obligation 3 est deja celle d'`ADR-0003` et ne change pas : contre-exemple
  compile, `check` sur lui, `hasMessageContaining` sur le motif. Ce que cet ADR ajoute est que le
  recensement des regles concernees n'est plus tenu a la main.

Ce que cet ADR ne fait pas : il ne dit pas quels criteres visuels ou pedagogiques meritent un
garde-fou. Ce qui doit etre garde est une question de produit et de qualite - `camille-po`,
`yanis-qa`, `nadia-prof`. Cet ADR dit seulement ce qu'un garde-fou doit valoir une fois qu'on a
decide d'en ecrire un.

## Regle executable

Fichier : `bootstrap/src/test/java/com/recreo/archi/LesGardeFousSontMisALEpreuveTest.java`

Ce n'est pas une `ArchRule`, et j'explique pourquoi plus bas plutot que de forcer la forme. C'est un
test JUnit qui echoue dans `mvn verify` comme n'importe quel autre.

Il vit dans `bootstrap` parce qu'il doit voir **tous** les modules : un recensement place dans
`adapter-web` aurait pour perimetre implicite `adapter-web`, et un garde-fou ecrit dans un autre
module lui echapperait en silence - la faute meme que cet ADR interdit, commise dans la regle qui
l'interdit. Sa liste de modules vient du pom agregateur, seul endroit du depot qui dise quels
modules existent.

Il porte deux recensements.

**Recensement 1 - les garde-fous de balayage.** Population derivee : tout fichier `.java` sous
`<module>/src/test/java` dont la source lit un artefact de production (elle contient `src/main/`).
Pour chacun, hors dette : sa source doit contenir sa propre mise a l'epreuve, et le repertoire
`<module>/src/test/resources/contreexemple/<NomDeClasse>/` doit exister et contenir au moins un
fichier.

```java
    static List<String> balayagesSansMiseALEpreuve(Path racine, Set<String> dette) {
        List<String> manquants = new ArrayList<>();
        for (SourceDeTest balayage : balayagesDeProduction(racine)) {
            if (dette.contains(balayage.nom())) {
                continue;
            }
            Path jeuFautif = balayage.module().resolve(CONTRE_EXEMPLES).resolve(balayage.nom());
            boolean eprouve = lire(balayage.fichier()).contains(MISE_A_L_EPREUVE)
                    && Files.isDirectory(jeuFautif)
                    && !estVide(jeuFautif);
            if (!eprouve) {
                manquants.add(balayage.nom());
            }
        }
        return manquants;
    }
```

**Recensement 2 - les regles ArchUnit.** Population derivee : toute declaration `ArchRule <nom> =`
trouvee dans les sources de test de tous les modules. Chacune doit apparaitre quelque part dans un
`<nom>.check(`, espaces et retours a la ligne ignores. C'est le trou du fait 3 : une regle neuve
sans contre-exemple echoue desormais au lieu d'attendre qu'un humain la remarque.

```java
    static List<String> reglesSansMiseALEpreuve(Path racine) {
        StringBuilder toutLeCodeDeTest = new StringBuilder();
        for (SourceDeTest source : sourcesDeTest(racine)) {
            toutLeCodeDeTest.append(sansEspaces(lire(source.fichier())));
        }
        String code = toutLeCodeDeTest.toString();
        return toutesLesRegles(racine).stream()
                .filter(regle -> !code.contains(regle + ".check("))
                .toList();
    }
```

**Le test ne s'exempte pas par une liste.** Il se retire du recensement 1 par son propre nom de
classe (`LesGardeFousSontMisALEpreuveTest.class.getSimpleName()`), parce qu'il nomme la marque de
balayage pour la chercher chez les autres et ne lit lui-meme aucun artefact de production. Il reste
soumis au recensement 2.

### Ce qui est prouve, mesure et non suppose

Le recensement se prouve mordant sur un **depot fictif complet**, avec son propre pom agregateur, lu
comme donnee et jamais construit :
`bootstrap/src/test/resources/contreexemple/LesGardeFousSontMisALEpreuveTest/`.

| Fichier du depot fictif | Ce qu'il doit provoquer |
|---|---|
| `faux-module/.../BalayageSansEpreuveContreExemple.java` | balaie la production, aucune mise a l'epreuve : **doit etre nomme** |
| `faux-module/.../BalayageEprouveContreExemple.java` | balaie la production, mise a l'epreuve et jeu fautif presents : **ne doit pas etre nomme** |
| `faux-module/.../RegleContreExemple.java` | declare `uneRegleEprouvee` (passee a un `check`) et `uneRegleJamaisEprouvee` (jamais) : **seule la seconde doit etre nommee** |
| `faux-module/src/test/resources/contreexemple/BalayageEprouveContreExemple/gabarit-fautif.html` | l'objet neuf : un bouton sans classe que personne n'a inscrit nulle part |

Les deux directions sont assertees par `containsExactly`, pas par `isNotEmpty` : un recensement qui
nommerait tout le monde aurait l'air de mordre sans rien distinguer, et c'est le mode de defaillance
que la lecon d'`ADR-0003` impose de fermer.

Trois mutations du depot **reel**, mesurees puis annulees, toutes sur mon propre fichier :

| Mutation | Resultat |
|---|---|
| Retirer `TexteAccentueDesGabaritsTest` de la dette | rouge, `aucunGardeFouDeBalayageNEchappeALaMiseALEpreuve`, sur le bon motif |
| Inscrire dans la dette un nom qui ne designe aucun garde-fou | rouge, `laDetteNePeutNiGrandirNiPourrir` |
| Ecrire une declaration `ArchRule uneRegleNeuveSansContreExemple =` sans aucun `check` | rouge, `aucuneRegleArchUnitNEchappeAuContreExemple`, en nommant la regle |

Etat mesure du depot a l'ecriture de cet ADR : **23 regles ArchUnit declarees, 23 passees a un
`check`** ; **6 garde-fous de balayage, 0 mis a l'epreuve**. `mvn -B clean verify` sur JDK 21.0.10 :
BUILD SUCCESS, **139 tests, 0 echec** (31 `domain`, 7 `application`, 38 `adapter-web`,
5 `adapter-content`, 56 + 2 `bootstrap`).

### La dette, et pourquoi elle est dans le test et pas dans un document

Les six garde-fous de balayage existants sont anterieurs a cet ADR et aucun n'a de jeu fautif. Les
mettre en conformite dans ce commit reviendrait a reecrire les tests de `yanis-qa` et
d'`ines-design` pendant qu'ils travaillent ; ce n'est ni mon mandat ni prudent dans un arbre
partage. Ils sont donc nommes dans une liste d'exemptions **dans le test lui-meme** :

```java
    private static final Set<String> DETTE_DE_BALAYAGE = Set.of(
            "AucunVocabulaireDeMatiereTest",
            "FaviconTest",
            "FrontieresDuJavaScriptTest",
            "HabillageDesEcransTest",
            "TailleDesTextesEtBoutonsTest",
            "TexteAccentueDesGabaritsTest");

    /** Etat au 2026-08-05. Ce nombre ne remonte jamais ; le baisser demande un diff visible. */
    private static final int PLAFOND_DE_DETTE = 6;
```

Cette liste exempte, elle n'absout pas, et elle n'est pas un perimetre enumere deguise :

- elle **ne peut pas grandir** : le plafond est asserte, un septieme garde-fou non eprouve est
  rouge ;
- elle **ne peut pas pourrir** : chaque nom doit designer un garde-fou qui existe reellement, sinon
  rouge ;
- un oubli y produit un **rouge**, jamais un vert silencieux - c'est un critere d'exemption, pas un
  perimetre.

Ce qu'elle ne garantit pas, et je le dis franchement : rien n'oblige mecaniquement le plafond a
**descendre**. Il descendra parce que chaque cloture republie l'inventaire dans
`confirmation-archunit.md`, et parce qu'une dette nommee dans un test que tout le monde execute est
plus difficile a oublier qu'une phrase dans un ADR. Si le plafond est encore a 6 a la cloture de
s04, cet ADR aura echoue sur ce point et il faudra le dire.

> **Amendement du 2026-08-15 (s13) - ce paragraphe adossait une clause en vigueur a un document qui
> n'existait pas, et il faut le dire au lieu de le republier une cinquieme fois.**
>
> Le fait, avec sa methode : `confirmation-archunit.md` existe sous `squad/sprints/<sprint>/` pour
> **huit** sprints clos (s00, s01, s02, s03, s05, s06, s07, s08) et manque pour **cinq** (s04, puis
> s09 a s12, quatre d'affilee). Compte etabli par `git ls-files 'squad/sprints/*/confirmation-archunit.md'`
> sur `9430cfd`, croise avec la liste des repertoires de `squad/sprints/` ; l'absence de s04 est
> anterieure et documentee - ce sprint n'avait aucun dossier de ceremonie, et je l'avais ecrit a
> l'epoque dans le fichier de s03 plutot que d'inventer un dossier.
>
> **La phrase ci-dessus etait donc un souhait, exactement au sens de l'axiome du depot** : elle
> nommait comme mecanisme de descente un geste humain, qui a manque cinq fois sur treize. Je ne la
> repare pas en promettant mieux. Je lui substitue ce qui fait le travail, et c'est une seule ligne
> d'assertion : **le plafond ne se contente plus de majorer la dette, il vaut exactement sa taille.**
>
> ```java
> assertThat(DETTE_DE_BALAYAGE).hasSize(PLAFOND_DE_DETTE);   // etait hasSizeLessThanOrEqualTo
> ```
>
> Ce que ce changement ferme, et pourquoi il n'est pas cosmetique : un plafond superieur a la dette
> est une **place libre**, et une place libre laisse entrer en silence le garde-fou non eprouve
> suivant - le plafond ne rougit pas, personne ne voit rien, et l'exemption devient un perimetre
> enumere deguise, ce que les trois puces ci-dessus affirment qu'elle n'est pas. Avec l'egalite, la
> troisieme puce devient vraie dans les deux sens : entrer dans la dette **et** en sortir demandent
> tous deux un diff sur le plafond, donc une relecture.
>
> Ce que ce changement ne fait toujours pas, et je ne le maquille pas : **il n'oblige toujours pas le
> plafond a descendre.** Aucune assertion ne peut exiger qu'une dette soit payee. Ce qu'il obtient est
> plus modeste et verifiable : la dette ne peut plus grandir en silence, et le plafond ne peut plus
> mentir sur sa taille. La descente reste un choix, elle n'est simplement plus adossee a un document.
>
> **Morsure mesuree, sur le depot reel et non sur une fixture** : `PLAFOND_DE_DETTE` porte de 2 a 3,
> la dette laissee a 2 - c'est-a-dire exactement l'etat qu'autorisait l'assertion precedente.
> `mvn -B -pl bootstrap -am test -Dtest=LesGardeFousSontMisALEpreuveTest`, JDK 21.0.10 : **1 echec sur
> 10**, `laDetteNePeutNiGrandirNiPourrir`, message *"le plafond vaut exactement la taille de la dette.
> Un plafond superieur a la dette est une place libre, donc pas un cliquet"*. Plafond restaure a 2,
> suite verte.
>
> Le meme geste est applique a `PLAFOND_DE_CRITERES_ENUMERES` (`ADR-0039`), qui portait la meme
> assertion permissive pour la meme raison.
>
> **Etat des cinq cliquets du depot au 2026-08-15**, verifie et non suppose - chaque exemption nomme
> encore quelque chose de reel, ce que les tests eux-memes assertent :
>
> | cliquet | dette | plafond | jeu |
> |---|---|---|---|
> | `LesGardeFousSontMisALEpreuveTest.DETTE_DE_BALAYAGE` | 2 | 2 | **verrouille** par cet amendement |
> | `LesGardeFousSontMisALEpreuveTest.CRITERES_ENUMERES_DECLARES` | 1 | 1 | **verrouille** par cet amendement |
> | `LeRangDeLaBonneReponse...Test.DETTE_DE_RANG` | 2 | 2 | nul aujourd'hui, assertion encore permissive |
> | `LeRangDeLaBonneReponse...Test.DETTE_DE_SEUIL_MINIMUM` | 3 | 3 | nul aujourd'hui, assertion encore permissive |
> | `ChaquePaquetPorteUnIndiceTest.DETTE_DE_CONTENU` | 0 | 0 | nul par construction |
>
> Les deux cliquets d'`adapter-content` n'ont **aucun jeu aujourd'hui** et ne sont donc pas un defaut
> vivant. Je ne les modifie pas dans ce commit pour une raison de lot et non de doctrine : le lot de
> S13-1 doit deja corriger la lecture de valeur de ce meme fichier, et faire converger deux agents sur
> une ligne dont le changement est un non-evenement aujourd'hui coute un conflit pour rien. **Le
> resserrement des deux y est du, dans le meme commit** ; s'il n'y est pas, c'est une dette a inscrire
> au rapport de s13.

### Ce que la correction de `yanis-qa` doit a cette regle, et ce qu'elle lui doit encore

Le commit `164b662` fait deriver les boutons controles des elements `<button>` et `<a href>`
reellement rendus, et fait retomber un bouton sans classe sur sa balise nue au lieu de le laisser
sortir du controle. C'est l'obligation 2 tenue sur les boutons, et c'est la bonne correction : je ne
la reecris pas et je ne la reprends pas.

Deux choses restent, et elles se rangent sous les obligations 1 et 3 :

- la liste des **gabarits** balayes est toujours ecrite en dur (quatre `resolve(...)`), comme l'est
  la liste des six selecteurs de texte de `aucunTexteNePasseSousLaTailleDeLaConsigne`. Un cinquieme
  gabarit, ou un septieme selecteur de texte, echappe encore ;
- le garde-fou n'a pas de jeu fautif : la preuve de morsure a ete faite a la main par `nadia-prof`
  puis par `yanis-qa`, sur l'arbre reel, et n'a pas survecu au commit.

C'est pour cela que `TailleDesTextesEtBoutonsTest` est dans la dette et non hors sujet. La regle est
au-dessus de sa correction, elle ne la remplace pas.

## Ce que cette regle ne couvre pas, dit avant qu'on le decouvre

- **Elle ne cree aucun garde-fou.** Le fait 1 - un critere livre sans aucun test - reste hors de sa
  portee : on ne peut pas exiger la mise a l'epreuve d'un test qui n'existe pas. C'est la revue de
  couverture de `yanis-qa` qui attrape ce cas, et elle l'a attrape.
- **Elle verifie la presence de la mise a l'epreuve, pas son execution.** `bootstrap` ne voit pas le
  classpath de test des autres modules ; il ne peut donc pas executer un garde-fou d'`adapter-web`.
  L'execution reelle a lieu dans la suite du module proprietaire. Le recensement garantit qu'un
  garde-fou possede un jeu fautif et s'y confronte ; il ne garantit pas que le jeu fautif soit
  pertinent. C'est exactement le niveau de garantie d'`ADR-0003`, transpose : un contre-exemple
  existe et fait echouer la regle, sans qu'aucun automate ne prouve qu'il couvre toutes les
  violations imaginables.
- **Son critere d'appartenance est textuel et deliberement large.** Est reconnu garde-fou de
  balayage tout test dont la source contient `src/main/`. `FaviconTest`, qui juge un artefact nomme
  et n'a aucun perimetre a deriver, est donc inclus a tort. J'assume ce sens de l'erreur : une
  inclusion de trop coute un jeu fautif inutile et **se voit** ; une inclusion de moins ne se voit
  pas. C'est l'application a moi-meme du tableau du contexte.
- **Un garde-fou qui lirait la production par le classpath echapperait au recensement.** Aucun ne le
  fait aujourd'hui ; le jour ou il y en aura un, c'est le critere d'appartenance qu'il faudra
  elargir, pas la regle.
- **Une declaration `ArchRule` citee dans un commentaire compte comme une declaration.** Faux rouge,
  du bon cote.

## Consequences

- Un garde-fou coute desormais un repertoire de plus, comme une regle ArchUnit coute une classe de
  plus depuis `ADR-0003`. Le cout est reel et il est tres inferieur a celui d'un garde-fou vert qui
  couvre tout sauf le defaut vise.
- Ecrire un garde-fou impose de le rendre parametrable par sa racine d'artefacts. C'est une
  contrainte d'ecriture reelle, et c'est la seule facon d'executer un juge sur autre chose que la
  production.
- Le depot gagne un deuxieme mecanisme de recensement a cote de `LesReglesMordentTest`. Ils ne font
  pas la meme chose : `LesReglesMordentTest` **execute** les regles sur leurs contre-exemples,
  `LesGardeFousSontMisALEpreuveTest` **verifie que rien n'y manque**. Le second garde le premier.
- Un renommage de classe de test peut rendre rouge un test qui n'a pas bouge (dette qui pourrit,
  jeu fautif orphelin). C'est voulu, et le message le dit.
- Les listes de fautes existantes (vocabulaire interdit de `AucunVocabulaireDeMatiereTest`, motifs de
  `FrontieresDuJavaScriptTest`, tags de `verifier-prefixes.sh`, formes de chemin de
  `verifier-chemins.sh`) ne sont pas touchees : ce sont des criteres, la regle ne les vise pas.

## Alternatives considerees

- **Tests de mutation automatiques (PIT).** Ecartes, et pas pour la raison d'`ADR-0003`. PIT mute du
  bytecode Java ; les deux defauts mesures vivaient dans du CSS et du HTML, que PIT ne mute pas. Il
  ne repond donc pas a la question posee, meme partiellement. Un jeu d'artefacts fautif commite est
  une mutation figee, revue en diff, et applicable aux ressources.
- **Interdire syntaxiquement la liste litterale dans un test** (refuser un litteral qui nomme un
  gabarit de production). Ecarte : l'analyse syntaxique ne distingue pas un objet **balaye** d'un
  objet **vise**. `TexteAccentueDesGabaritsTest` nomme legitimement `fragments/retour.html` pour
  asserter une propriete de ce fichier-la. La regle produirait des faux rouges sur du code correct
  et se ferait desactiver.
- **Une annotation `@GardeFouDeBalayage` a poser sur les classes concernees.** Ecartee : une
  annotation est declarative, celui qui oublie de la poser echappe au recensement. C'est le trou du
  perimetre enumere, deplace dans le code source.
- **Un recensement par module.** Ecarte pour la raison donnee plus haut : son perimetre serait le
  module, et un module neuf lui echapperait en silence.
- **Executer les garde-fous par reflexion depuis `bootstrap`, via un contrat
  `static void verifier(Path)`.** Ecarte aujourd'hui : `bootstrap` n'a pas les classes de test des
  autres modules a son classpath, et les y mettre demanderait de publier des jars de test - trop de
  machinerie pour six garde-fous. C'est ce qui rend la garantie plus faible que celle
  d'`ADR-0003`, et c'est dit dans la section des limites.
- **Ecrire la regle et compter sur la revue.** Ecarte par l'axiome du depot. C'est aussi ce qui a
  laisse `LesReglesMordentTest` enumerer son perimetre pendant deux sprints sans que personne ne le
  remarque, moi le premier.

## Ce qui me ferait changer d'avis

- Si les jars de test des modules deviennent disponibles au classpath de `bootstrap` pour une autre
  raison, je remplace le recensement textuel du garde-fou de balayage par une execution reelle sur
  le jeu fautif, et la garantie monte au niveau d'`ADR-0003`.
- Si le plafond de dette n'a pas baisse a la cloture de s04, c'est que la liste d'exemptions sert de
  refuge et non de dette : je la supprime et je rends les six garde-fous rouges jusqu'a leur mise en
  conformite, ou j'acte que l'obligation 3 est hors de portee et je la retire de cet ADR.
- Si un jeu fautif devient plus complexe que le garde-fou qu'il eprouve, c'est le garde-fou qui est
  mal ecrit, et c'est lui que je reecris.
