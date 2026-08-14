# ADR-0039 - Un critere se derive des que la promesse est universelle, et une enumeration passe du cote autorise

- **Statut** : Accepte
- **Date** : 2026-08-14
- **Auteur** : theo-archi
- **Sprint** : s12

## Contexte

`ADR-0013` a range les modes de defaillance d'un garde-fou dans un tableau a quatre cases et en a
tire une phrase : **le perimetre se derive, le critere peut s'enumerer.** Sa case "critere enumere"
dit *"un motif qu'on n'a pas nomme passe. Limite visible dans le texte du test"*, et son obligation 1
va plus loin : une liste ecrite a la main de fautes a reprocher *"reste permise : c'est un critere, et
son mode de defaillance est un rouge, pas un vert"*.

**Cette derniere phrase est fausse.** Elle a couvert un trou vivant pendant deux sprints, et le trou
n'etait pas theorique.

### Fait 1 - un script tiers reel est entre vert, et la classe affirmait le contraire

`yanis-qa` a mesure en s11, et j'ai rejoue sa mutation avant de corriger quoi que ce soit, sur la
tete de `round-two` a `ef801b4`, JDK 21.0.10 :

- **la mutation** : `alpinejs` 3.14.9, le vrai `dist/cdn.min.js` (43,7 ko) recupere depuis son
  origine, copie sous `static/vendor/alpine.min.js`, avec une ligne de `provenance.txt` complete et
  une empreinte SHA-256 exacte. C'est-a-dire exactement le geste "correct" que la doctrine attend
  d'un ajout legitime, avec une bibliotheque de comportement d'interface - menus, replis, modales -
  que la Javadoc de la classe dit vouloir refuser ;
- **le resultat, avant mon lot** : `Tests run: 16, Failures: 0, Errors: 0, Skipped: 0`, BUILD
  SUCCESS. Y compris `aucunJavaScriptDeBibliothequeDInterfaceNEntreDansLeDepot`, dont le nom promet
  le refus de ce geste ;
- **la cause** : `JAVASCRIPT_TIERS_REFUSE = List.of("bootstrap")`, avec ce commentaire dans le code -
  *"un nom absent de cette liste n'echappe a rien : il devra de toute facon etre vendorise et trace,
  donc apparaitre en diff"*. Un fichier trace apparait bien en diff ; **un diff n'est pas un
  garde-fou**, c'est une relecture, et l'axiome du depot range une relecture parmi les souhaits.

Le perimetre, lui, etait correctement derive : `chaqueRessourceTierceEstTraceeEtIntacte` rougit sur
tout fichier present et non trace. La doctrine d'`ADR-0013` a tenu sur la moitie qu'elle couvre.

### Fait 2 - une phrase lue par l'enfant a ete remplacee entierement sans qu'un test bouge

`TexteAccentueDesGabaritsTest` balaye le repertoire des gabarits - perimetre derive, la aussi - et
leur reproche cinq fautes nommees : `"ca."`, `"reponse etait"`, `"la partie est terminee"`,
`"d'avoir joue"`, `"Recreo"` accentue. `nadia-prof` a fait remplacer **integralement** le texte de
l'ecran de confirmation en s11 ; aucun de ces cinq motifs ne le traverse, donc rien n'a bouge.
`lea-dev` a ferme le cas precis par un test qui epingle la phrase arbitree - ce qui etait la bonne
correction du cas, et ne dit rien du texte neuf suivant.

### La propriete generale, et pourquoi la distinction proposee ne tient pas

Les deux cas partagent une forme que les six garde-fous verts diagnostiques en s10 et s11 n'avaient
pas : ceux-la etaient verts parce qu'ils **ne rencontraient pas** leur objet. Ceux-ci le rencontrent
et ne savent pas quoi lui demander.

`yanis-qa` propose de separer les criteres enumeres legitimes des autres par la nature de leurs
membres : une **categorie qui generalise** (`addition`, `syllabe`) contre le **nom d'un incident date
et clos** (`bootstrap`, les cinq fautes). Je l'ai verifiee et elle ne survit pas a la pousser :

- `"bootstrap"` generalise lui aussi - tout fichier futur nomme ainsi serait attrape. Il n'est pas
  moins categoriel que `"addition"` ;
- `"addition"` echoue lui aussi sur l'objet suivant - une mecanique qui nommerait `"verbe"`,
  `"division"` ou `"triangle"` passe verte.

Ce qui separe reellement les cas est **la polarite de la liste**, pas la nature de ses membres :

| Polarite | Etat par defaut d'un objet neuf | Mode de defaillance |
|---|---|---|
| Liste de **refus** (liste noire) | **vert** | un vert silencieux, sur un objet que le garde-fou a pourtant rencontre |
| Liste d'**autorisation** (liste blanche) | **rouge** | un rouge sur un ajout legitime, qui se voit et se traite |

Une promesse universelle - *"aucun JavaScript tiers d'interface n'entre dans ce depot"*, *"aucun
gabarit ne dit une faute a l'enfant"* - ne peut pas etre tenue par un critere vert par defaut. C'est
la seule case reellement dangereuse du cote critere, et c'est la case ou les deux faits se rangent.

### Ou je trace la limite, pour que la clause ne soit pas invraisemblable

Tout critere est enumere a un niveau ou a un autre : `referencesDeRessource` nomme les attributs qui
designent une ressource, `POSE_SON_TEXTE_AU_RENDU` nomme les deux attributs de Thymeleaf. Exiger la
derivation partout rendrait la doctrine inapplicable, donc ignoree.

La limite est **l'accessibilite du complement** : l'obligation s'applique quand le complement du
critere contient un objet que le depot peut acquerir **sans decision qui rouvre le garde-fou**.

- `alpine.min.js` : un geste de copie, aucune decision, personne a prevenir. Mesure a l'appui - il
  est entre vert. L'obligation s'applique.
- un mot de matiere neuf dans `AucunVocabulaireDeMatiereTest` : il n'entre que le jour ou une matiere
  neuve entre au catalogue, ce qui est une decision de `camille-po` et de `nadia-prof` qui rouvre
  `ADR-0004` de toute facon. Meme famille, portee differente : la liste reste enumeree, declaree et
  comptee (clause 3).
- un attribut `srcset` ou `poster` : il faudrait ecrire un balisage d'image adaptative qui n'existe
  pas, ce qui passe par `ines-design` et un arbitrage. Meme traitement.

## Decision

**Trois clauses.**

1. **Un garde-fou dont la promesse est universelle rend son critere total sur son complement.** Deux
   formes seulement : une **propriete calculee** de l'objet, ou une liste d'**autorisation**. Une
   liste de refus ne tient pas une promesse universelle et ne doit plus etre ecrite pour cela.
2. **Une liste d'autorisation porte ses deux clauses de plafond.** Chaque entree doit designer un
   objet reellement present - une autorisation qui ne garde plus rien est une place libre - et doit
   identifier son objet assez precisement pour qu'un autre ne s'y glisse pas. Pour un fichier :
   son origine declaree et son empreinte, **jamais son nom**.
3. **Un critere qui reste du cote refuse se declare et se compte.** Il ecrit dans sa source la
   formule exacte `critere enumere assume`, l'`ADR` qui l'accepte nomme l'objet reel qui passerait
   vert devant lui et le declencheur qui l'etend, et le recensement 3 de
   `LesGardeFousSontMisALEpreuveTest` le plafonne.

**Ce que cette decision ne change pas** : les trois obligations d'`ADR-0013` restent entieres. Elle
en corrige une phrase (voir l'amendement du meme jour a `ADR-0013`) et ajoute la moitie qui manquait
- le perimetre se derive, et le critere aussi des que la promesse est universelle.

## Regle executable

Trois garde-fous, chacun mesure. Aucun n'est une `ArchRule`, pour la raison d'`ADR-0013` : ArchUnit
voit des types et des membres, pas la polarite d'une liste de litteraux ni le texte d'un gabarit. Ce
sont des tests JUnit, qui echouent dans `mvn verify` comme n'importe quel autre.

### 1. `adapters/adapter-web/.../RessourcesTiercesTest.java` - clause 1 par liste d'autorisation

```java
    private record JavaScriptAutorise(String origineDeclaree, String empreinte) {
    }

    private static final List<JavaScriptAutorise> JAVASCRIPT_TIERS_AUTORISE = List.of(
            new JavaScriptAutorise("npm:htmx.org:2.0.10",
                    "71ea67185bfa8c98c39d31717c6fce5d852370fcdfd129db4543774d3145c0de"));
```

Le critere devient : tout fichier `.js` sous `static/vendor/` doit correspondre a une entree par son
origine declaree **et** par son empreinte. Trois tests neufs :
`aucunJavaScriptTiersNonAutoriseNEntreDansLeDepot`,
`chaqueJavaScriptTiersAutoriseDesigneUnFichierReellementServi` (clause 2, le plafond),
`unJavaScriptTiersCorrectementTraceMaisNonAutoriseFaitEchouerLeGardeFou` (`ADR-0013` obligation 3,
sur un jeu fautif dont la provenance est **complete et juste**).

**La mutation de `yanis-qa`, rejouee apres le lot.** Meme fichier reel, meme ligne de provenance,
meme empreinte :

```
[ERROR] RessourcesTiercesTest.aucunJavaScriptTiersNonAutoriseNEntreDansLeDepot
[ADR-0008, ADR-0014 et ADR-0039 : le JavaScript tiers vendor/alpine.min.js n'entre pas dans ce
depot. Son origine declaree est "npm:alpinejs:3.14.9" et son empreinte
3ed1eed252488921df65e363d6715deb04d7f92aaedb9e52199fdf73cb1e0ad3, qu'aucune entree autorisee ne
designe. Le comportement de jeu vit sur le serveur : d'une bibliotheque d'interface, seule sa
feuille de style se vendorise. Faire entrer un script tiers de plus demande un ADR et une entree de
plus ici, jamais un fichier de plus dans ce repertoire]
```

`Tests run: 19, Failures: 1`. Fichier supprime, `provenance.txt` restaure, `git status --porcelain`
vide, `Tests run: 19, Failures: 0`.

**Deuxieme critere du meme fichier, corrige dans le meme geste.** Le refus d'une ressource designee
par une expression calculee enumerait trois caracteres (`$`, `+`, `'`) : une expression de message
`#{...}` n'en emploie aucun et designe une ressource differente selon la locale. Le critere exige
desormais la propriete positive - une cible litterale n'est faite que de caracteres d'URI (RFC 3986),
et toute expression Thymeleaf porte des accolades ou une barre verticale, qui n'en sont pas.

### 2. `adapters/adapter-web/.../TexteAccentueDesGabaritsTest.java` - clause 1 par inventaire

Le perimetre descend au **noeud de texte** : chaque noeud de chaque gabarit produit une assertion,
rien n'est retire par une propriete facultative de l'element qui le porte. Le critere est un
inventaire de dix textes, compares par egalite, avec la reference que le depot trace de chaque
formulation. Trois tests neufs :
`aucunGabaritDeProductionNEcritUnTexteHorsDeLInventaire`,
`chaqueTexteDeLInventaireEstEncoreEcritParUnGabarit` (clause 2),
`unTexteNeufHorsDeLInventaireFaitEchouerLeGardeFou`.

Trois mutations sur les gabarits **reels**, restaurees, arbre verifie vide apres chacune :

| Mutation | Resultat |
|---|---|
| une phrase neuve et irreprochable ajoutee a `fragments/retour.html` : `<p class="encouragement">Tu peux rejouer quand tu veux.</p>` | **rouge**, 1 echec sur 8, `retour.html ecrit "Tu peux rejouer quand tu veux.", un texte que l'inventaire de ce garde-fou ne porte pas`. C'est exactement le cas que l'ancien critere laissait passer |
| un mot change dans la phrase arbitree de `fragments/confirmation.html` (`pars` -> `sors`) | **rouge**, 3 echecs sur 8 : le test nomme de `lea-dev`, l'inventaire, et le plafond |
| `fragments/erreur.html` : la phrase d'erreur passee au modele par `th:text` | **rouge**, 1 echec sur 8, sur le plafond - un texte retire de l'inventaire est un acte delibere, pas une disparition |

### 3. `bootstrap/.../LesGardeFousSontMisALEpreuveTest.java` - clause 3, recensement 3

```java
    private static final String AVEU_DE_CRITERE_ENUMERE = "critere enumere assume";

    private static final Set<String> CRITERES_ENUMERES_DECLARES = Set.of(
            "AucunVocabulaireDeMatiereTest");

    private static final int PLAFOND_DE_CRITERES_ENUMERES = 1;
```

Population derivee des sources de test de tous les modules, comme les deux recensements existants ;
critere : porter l'aveu. L'ensemble des declarants doit valoir **exactement** le recense, ce qui
ferme les deux directions d'un coup - un declarant de plus est rouge, un nom recense que plus aucune
source ne declare est rouge. Trois tests neufs, dont la mise a l'epreuve sur le depot fictif
(`CritereEnumereContreExemple`).

**Il se retire par son propre nom de classe**, comme le recensement 1, et cette necessite est une
mesure et non une precaution : a la premiere execution, le recensement s'est nomme lui-meme
(`["AucunVocabulaireDeMatiereTest", "LesGardeFousSontMisALEpreuveTest"]`), parce qu'il ecrit l'aveu
pour le chercher chez les autres.

Deux mutations sur du materiau reel, restaurees :

| Mutation | Resultat |
|---|---|
| l'aveu retire d'`AucunVocabulaireDeMatiereTest` | **rouge**, `but could not find the following elements: ["AucunVocabulaireDeMatiereTest"]` - le recense ne peut pas pourrir |
| l'aveu ajoute a `FaviconTest`, qui n'est pas recense | **rouge**, `the following elements were unexpected: ["FaviconTest"]` - un declarant de plus ne passe pas en silence |

### Le verdict sur `AucunVocabulaireDeMatiereTest`, demande et rendu

`yanis-qa` l'avait distingue au lieu de l'amalgamer. **Sa conclusion est juste, son critere ne l'est
pas.** Le garde-fou est de la **meme famille** : sa liste est du cote refuse, et une mecanique qui
nommerait `"verbe"` ou `"triangle"` passe verte - c'est un cinquieme cas au sens de la forme. Il
n'est pas de la meme **portee** : son complement ne s'atteint que par une decision produit qui
rouvre `ADR-0004`. Il reste donc enumere, il porte desormais l'aveu, il est recense et plafonne, et
son declencheur d'extension est ecrit dans sa Javadoc - *toute matiere neuve au catalogue etend cette
liste dans le meme lot*.

## Ce que ces criteres derives ne prouvent pas

Une liste de non-preuves est un engagement a reverifier. Une des huit d'`ADR-0038` s'est revelee
franchement fausse au sprint suivant ; celles-ci sont a relire au meme titre.

1. **Le recensement 3 ne trouve pas un critere enumere qui ne se declare pas.** C'est mot pour mot la
   faiblesse qu'`ADR-0013` reprochait a l'alternative de l'annotation - *"celui qui oublie de la
   poser echappe au recensement"* - et je ne peux pas pretendre l'avoir evitee : elle est la,
   entiere. Ce que le recensement ajoute a rien du tout : la formule est portee par une source, le
   plafond vaut un, et le prochain declarant est rouge tant que quelqu'un n'a pas ecrit ce qui
   passerait vert devant lui.
2. **Je ne peux pas affirmer que le depot ne compte qu'un critere enumere.** Ce que j'ai mesure : le
   grep de l'auto-declaration en prose donne quatre fichiers, dont un antidote qui dit le contraire
   (`CabinetDeLaMachineTest`, *"sans qu'une liste ecrite a la main ait..."*) ; deux sondes
   syntaxiques mesurees et rejetees (voir les alternatives). L'ensemble des criteres enumeres **non
   declares** reste inconnu, et c'est le premier chiffre a battre.
3. **L'inventaire de textes ne dit rien du francais du texte.** Il refuse qu'un texte change en
   silence ; il ne juge ni l'orthographe, ni l'accentuation, ni la comprehensibilite pour un enfant
   de CP. C'est `nadia-prof`, et rien de ce lot ne s'en approche.
4. **Il ne force pas un arbitrage, il force un diff.** Un agent presse met l'inventaire a jour sans
   rien demander a `nadia-prof` ; le test est alors vert et la porte pedagogique n'a pas eu lieu. La
   porte n'est pas encodable, elle est a elle.
5. **L'inventaire ne couvre ni le texte pose par le modele, ni le texte d'attribut.** Le premier vient
   du contenu et ses garde-fous vivent dans `adapter-content`. Le second est un trou mesure : la
   production porte aujourd'hui un `aria-label="Menu principal"`, lu par un lecteur d'ecran, qu'aucun
   garde-fou de texte ne juge. Je n'ai pas enumere trois attributs pour le fermer - ce serait un
   perimetre enumere, la faute exacte que ce lot corrige.
6. **L'autorisation de JavaScript prouve l'integrite, pas l'authenticite.** L'empreinte garantit que
   les octets servis sont ceux qu'on a relus ; elle ne prouve pas qu'ils viennent de
   `npm:htmx.org`. Limite deja nommee par `ADR-0014`, inchangee.
7. **Le critere de cible litterale repose sur une lecture de la syntaxe de Thymeleaf**, non sur un
   test : j'affirme que toute forme dynamique porte des accolades ou une barre verticale. Si une
   forme sans l'un ni l'autre existe, elle passe verte.
8. **Aucun de ces trois garde-fous ne devient vert quand sa source se vide**, et c'est verifie et non
   suppose : l'inventaire de textes rougit sur un corpus vide (`isNotEmpty`, message ecrit) et sur
   une entree que plus aucun gabarit n'ecrit (mutation 3 mesuree) ; l'autorisation de JavaScript
   rougit si `static/vendor/` n'en sert plus (clause 2, jeu fautif `tiers-non-trace` mesure) ; le
   recensement 3 rougit si l'aveu recense disparait (mutation 1 mesuree). Ce que je ne peux pas
   ecarter : les trois se videraient sans rougir si le module de test entier disparaissait, ce que
   rien dans ce depot ne surveille.

## Consequences

- **Une mise a jour d'htmx touche ce test**, dans le meme commit que `provenance.txt`. C'est un cout
  reel et il est voulu : la question "est-ce encore la meme bibliotheque" recoit une reponse humaine.
- **Toute modification d'un texte de gabarit lu par l'enfant rend la CI rouge** jusqu'a mise a jour
  de l'inventaire. Dix entrees aujourd'hui. C'est le prix de la propriete, et c'est exactement le
  moment ou `nadia-prof` doit etre saisie.
- **Une liste d'autorisation deplace la friction** : elle rougit sur les ajouts legitimes, la ou une
  liste de refus rougissait sur rien. `marc-dev` et `lea-dev` la rencontreront, et ce sera un rouge
  qui se lit, pas un vert qui se croit.
- `ADR-0014` perd une de ses limites nommees, qui affirmait ce que ce lot a mesure faux ; amendee le
  meme jour.
- Le depot compte un troisieme recensement dans `LesGardeFousSontMisALEpreuveTest`. Les trois ne font
  pas la meme chose et aucun ne garde les deux autres.
- **Deux textes lus par l'enfant n'ont aucun arbitrage nomme dans le depot** (`Il n'y a pas de jeu
  pour le moment.`, `Oups ! Le jeu a un souci.`). L'inventaire le dit en clair a cote de chaque
  entree. Ce n'est pas une question d'architecture : elle est routee a `sofia-sm` pour `nadia-prof`.

## Alternatives considerees

- **N'amender qu'`ADR-0013`, sans nouvel `ADR`.** Ecartee, mais a moitie retenue : la phrase fausse
  est corrigee la ou elle vit (amendement du 2026-08-14 a `ADR-0013`), parce qu'une case de tableau
  qui dit faux se corrige sur place. Le reste - la polarite comme propriete operatoire, la limite de
  l'accessibilite du complement, le recensement executable - est une decision et pas une correction,
  et deux clauses de plus dans un `ADR` que personne n'applique valent moins qu'une clause
  executable : celle-ci en a une.
- **Detecter le defaut par une sonde syntaxique sur `doesNotContain` a deux litteraux ou plus**, dans
  les seuls garde-fous qui balaient la production. **Ecartee par mesure : elle ne trouve pas le cas
  vivant.** Le litteral de `JAVASCRIPT_TIERS_REFUSE` etait passe par une variable
  (`doesNotContain(refuse)` dans une boucle), donc invisible a cette sonde. Une detection qui rate le
  cas qui motive l'ADR n'est pas une detection.
- **Detecter par une sonde syntaxique sur `List.of("...")` et `Set.of("...")`** dans ces memes
  garde-fous. **Ecartee par mesure : 21 occurrences dans 11 fichiers, dont une seule est le defaut** -
  vingt faux rouges, sur des listes de proprietes CSS, d'attributs et de balises parfaitement
  legitimes. C'est l'alternative qu'`ADR-0013` avait ecartee par raisonnement (*"l'analyse syntaxique
  ne distingue pas un objet balaye d'un objet vise"*) ; elle l'est desormais par un chiffre, et le
  chiffre est celui a battre si quelqu'un veut la reprendre.
- **Interdire tout critere enumere.** Ecartee : pour certaines proprietes aucun critere calculable
  n'existe (le vocabulaire de matiere en est une), et une regle qu'on desactive vaut moins qu'une
  dette comptee.
- **Deriver l'inventaire de textes d'une source exterieure** - les fiches de conformite de
  `nadia-prof`. Ecartee : ce sont des fichiers de prose sans format stable, et ce serait exactement le
  piege d'un critere derive d'une source qui peut se vider - une fiche renommee et le critere passe
  vert sans que rien ne le dise.
- **Refuser tout JavaScript tiers, sans exception.** Ecartee : le transport hypermedia est la moitie
  du parti de rendu de ce depot (`CLAUDE.md`, `ADR-0008`). Le refuser demanderait de changer la pile,
  pas le garde-fou.
- **Pinner seulement l'origine declaree, sans l'empreinte.** Ecartee pour un geste plausible et non
  pour une malveillance : recopier la ligne de provenance d'htmx en changeant le chemin et en
  recalculant l'empreinte est ce qu'un agent presse fait ; l'empreinte epinglee le rend rouge, et
  elle coute une ligne.

## Ce qui me ferait changer d'avis

- Si `PLAFOND_DE_CRITERES_ENUMERES` monte a trois sans qu'un `ADR` nomme, pour chacun, l'objet reel
  qui passerait vert devant lui, la clause 3 sert de refuge et non de dette : je la remplace par une
  interdiction seche et je rends les declarants rouges.
- Si un critere enumere **non declare** est trouve par quelqu'un d'autre que le recensement, la
  declaration ne suffit pas et il faut une detection qui ne depende pas d'un aveu. Les deux sondes
  syntaxiques mesurees ici disent quels chiffres battre.
- Si l'inventaire de textes est mis a jour deux fois sans reference d'arbitrage, il n'est qu'un
  cliquet et la question remonte a `sofia-sm` : ce n'est plus un probleme d'architecture.
- Si un jeu fautif devient plus complexe que le garde-fou qu'il eprouve, c'est le garde-fou qui est
  mal ecrit - regle inchangee depuis `ADR-0013`.
