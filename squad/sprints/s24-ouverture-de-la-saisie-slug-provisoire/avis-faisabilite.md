# Avis de faisabilite s24 - l'etat exact de la conception de la saisie, et ce qu'il reste a decider

- **Auteur** : theo-archi. **Date** : 2026-08-30. **Sprint** : s24 (ouverture).
- **Branche** : `s24-theo`, worktree dedie. **Arbre mesure** : `round-two` a `ea546cf`.
- **Repertoire provisoire.** Le slug du sprint n'est pas fixe : `camille-po` travaille le perimetre
  en parallele, `sofia-sm` animera le planning. Ce document vit sous
  `squad/sprints/s24-ouverture-de-la-saisie-slug-provisoire/`, **slug provisoire assume**, a renommer
  par `sofia-sm` a la fixation du perimetre.
- **JDK** : toutes les mesures de ce document sont faites sur le JDK **21.0.10** de la toolchain
  declaree, par `bash outils/mvn-jdk21.sh`. Toute mesure partielle citee ici porte `-am` et nomme son
  perimetre ; un module mesure n'est pas le depot.
- **Ce que ce document n'est pas** : ni un perimetre (`camille-po`), ni une decision pedagogique
  (`nadia-prof`), ni des stories. Il dit ce qui est decide, ce qui ne l'est pas, ce que coute chaque
  lot et si le sprint tient.

---

## 0. Ce que j'ai reconstruit, et une correction a la liste d'entree

La liste d'entree du sprint nommait `ADR-0058` et `ADR-0059`. **`ADR-0059` n'a rien a voir avec la
saisie** : c'est le plafond maison du correctif de pli (largeur de rangee, `SYN-16`), et sa seule
occurrence du mot *saisie* est une reference bibliographique a un fichier de s20 dont le nom contient
*"conception de la saisie"*. Verifie par recherche du mot dans le fichier : **une occurrence, ligne
428, dans un chemin de fichier cite**. `ADR-0059` reste par ailleurs *"intacte et sans emploi"*
depuis le retrait du correctif de largeur par `camille-po` (son second amendement du 2026-08-25) - ce
n'est ni un acquis ni un obstacle pour la saisie.

L'inventaire ci-dessous est reconstruit depuis le depot, pas depuis cette liste.

---

## 1. Ce qui est deja decide

### 1.1 La conception d'architecture - `ADR-0058`, quatre blocs dates

`ADR-0058` (643 lignes) porte le corps de la conception plus trois amendements, et j'y en ajoute un
quatrieme aujourd'hui (section 2.1 ci-dessous). **Ce qui y est acquis et n'est pas a rouvrir :**

| Clause | Ce qui est decide |
|---|---|
| §1 | Une **forme d'item de plus** dans le contrat de contenu, `Item permits Question, ReponseAEcrire`, portant enonce, forme de reference, formes acceptees, indice et explication facultatifs, plus le mode de forme. Jamais des champs de plus sur `Question` |
| §2 | Le **mode est declare par le contenu**, jamais devine du code. La commodite "paquet, sinon item" de `SAI-2a` est un fait de **schema**, lu par la fabrique, sur le patron deja en service pour `indicesNommentLaReponse` |
| §3 | La **normalisation** (`SAI-1`, `SAI-3`, `SAI-4`) vit dans `com.recreo.domain.mechanics.saisie`, ne depend que du JDK, emploie `Locale.ROOT` et compte l'insecable comme un blanc. Non partagee avec `bandit-manchot` |
| §4 | **Aucun contrat ne porte de comportement**, methode statique ou par defaut d'interface comprise. **C'est la seule regle d'`ADR-0058` deja ecrite et verte** |
| §5 | `Move` et `GameSession` **ne bougent pas**. La saisie definit `SaisieMove` et son propre type de session dans son paquet |
| §6 + am. 1 | La session de saisie porte **`List<String> tentatives`** dans l'ordre d'ecriture, `derniereTentative()` devient une propriete calculee. Motif mesure : `Set.copyOf` ne conserve pas l'ordre d'insertion |
| §7 | La tabulation est deja couverte ; **deux planchers de forme ne voient pas un `<input>`** et devront etre etendus dans le lot ; `ADR-0008` est mieux servi qu'aujourd'hui - la bonne reponse ne part plus dans la page |
| §8 | L'**operation posee** n'est pas de la saisie, c'est une troisieme mecanique. Aucun appariement approximatif. Aucun verdict partiel, aucun troisieme etat |
| am. 3 | **La chaine recue et le mode qui l'a jugee voyagent sur le verdict** de la mecanique de saisie, seul objet qui survit a la resolution d'une manche. Deux composantes et non une |

### 1.2 Les regles nommees par `ADR-0058` et **encore dues** avec le lot de code

Elles sont ecrites dans l'`ADR`, pas dans le depot, et leur declencheur est le premier commit de la
mecanique. Aucune n'a ete ecrite depuis :

- **`R-A`** - `adapter-web` ne nomme aucune mecanique hors de son point de descente. **Rouge
  aujourd'hui, remesure sur l'arbre du jour** : la chaine `BanditManchot` figure sur **20 lignes de
  3 fichiers de production** de `adapters/adapter-web/src/main/java` - `PartieController` 12,
  `EtatDePartieEnSession` 6, `EtatDePartie` 2 - pour **21 occurrences** (une ligne en porte deux).
  Methode : `grep` recursif sur les seules sources de production du module, imports et Javadoc
  compris. *`ADR-0058` annoncait 20 occurrences sur 19 lignes a s19 ; l'ecart d'une occurrence vient
  d'`ADR-0062`, qui a ajoute un parametre type a `segmentsEnonce`. Le chiffre bouge avec l'arbre,
  c'est pourquoi il porte sa date.*
- **`R-B`** - aucune distance entre chaines dans une mecanique (`SAI-5`). Non encodable en ArchUnit
  pour sa moitie "ecrite a la main" ; le mecanisme est un test unitaire portant la table de couples
  nommee par `nadia-prof`.
- **`R-C`** - l'ordre des tentatives rendues est l'ordre d'ecriture (`SAI-12`), avec la seconde
  moitie qui distingue "ordonne" de "ordonne par hasard comme je l'attendais".
- **`R-D`** - sur un item strict resolu par une reussite, le verdict porte la chaine recue **et** la
  manche a deja change ; la resolution par revelation la porte aussi.

### 1.3 Ce qui est decide aujourd'hui, dans ce meme lot

- **`ADR-0069`** - le mode de forme est un **type du contrat de contenu**, jamais un booleen. Voir
  section 2.1 : c'est une clause du corps d'`ADR-0058` qui tombe sur une mesure.
- **`ADR-0070`** - **aucune cle de schema d'un paquet servi n'est ignoree de tout le depot**. Voir
  section 3 : c'est la reponse au piege des cles YAML.

### 1.4 La porte pedagogique - `nadia-prof`, quatre blocs dates

`squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` (920 lignes) fait foi et **je ne le
recopie ni ne le negocie**. Ce que le lot doit savoir de son etat courant :

- **douze conditions `SAI-1` a `SAI-12`**, dont `SAI-2` scindee en `SAI-2a`/`SAI-2b`/`SAI-2c` et
  `SAI-9` en `SAI-9a`/`SAI-9b` ;
- **six refus de ligne `R1` a `R6`**, `R6` etant neuf du 2026-08-25 (ecriture autonome d'un mot au
  CP, fermee a la saisie tant que la correction automatique est acceptee) ;
- **cinq seuils `SEUIL-1` a `SEUIL-5`**, dont `SEUIL-4` a change de statut sans changer de texte : le
  clavier etant le seul geste, un champ qu'on n'atteint pas au clavier n'est pas une mecanique
  degradee, c'en est une inutilisable ;
- **`SAI-11` est levee** depuis le 2026-08-25 : la saisie alphabetique est permise au CP. La saisie
  **numerique courte** reste ce que `nadia-prof` recommande en premier, et ce n'est pas la meme
  chose qu'une equivalence ;
- **`SAI-6`, `SAI-7`, `SAI-2b`, la formulation des retours et `R1` a `R6` ne sont gardes par aucun
  automate**, et cela ne changera pas : il faudrait un dictionnaire, porte fermee par `ADR-0039` puis
  `ADR-0049`. La liste des formes acceptees **est** le bareme ; un bareme faux passe une CI verte.

### 1.5 Ce qui est deja vrai dans le code, et qui aide

- **La couture d'`Item` est scellee et elle mord au compilateur.** Mesure du 2026-08-30 : ajouter la
  seconde permission produit **exactement deux arrets de compilation**,
  `BanditManchotSession.java:165` (le `switch` de `questionDe`) et
  `PropositionsAccentueesJouablesIT.java:171`. Ni plus, ni moins - la mecanique existante ne peut pas
  ignorer en silence qu'une seconde forme d'item existe.
- **Le port entrant n'a pas besoin de bouger.** `PartieUseCase.jouer(String, GameSession, Move)`
  prend deja le contrat, pas un type concret ; `MechanicRegistryPort` resout par `MechanicId` ;
  `MecaniquesConfiguration` enregistre une mecanique par bean et construit son registre par
  collecte. **Une mecanique de plus est un `@Bean` de plus**, et rien dans `application`.
- **Une route neuve entre d'elle-meme dans le garde-fou de tabulation** :
  `AucunArretDeTabulationNEstDestructeurTest` derive ses routes de `RequestMappingHandlerMapping` et
  ses arrets du document servi par le selecteur `a[href], button, input, select, textarea,
  [tabindex]` (ligne 359) - **un `<input>` y est deja**.

---

## 2. Ce qui n'est pas decide

### 2.1 La structure de l'item et son chargement YAML face a l'existant

**Ce qui vient d'etre tranche, parce que la mesure l'imposait.** `ADR-0058` §1 decidait *"un booleen
`formeExacteExigee`"*. J'ai pose la sonde dans l'arbre - l'item exact de cette section - et
`DifficulteTest.leContratDeContenuNePorteQueDuTexte` (`ADR-0006`) **rougit en une violation nommant
le type `boolean`** ; le meme item avec un type du paquet de contrat est vert (3 tests, 0 echec). La
forme decidee a s19 etait rouge depuis s01 et personne ne l'avait mesure. **`ADR-0069` la corrige**,
avec la morsure qui manquait a la regle sur les formes non chiffrees.

**Ce qui reste ouvert et appartient au lot, pas a un `ADR` :**

1. **Le nom des composantes et du type de mode.** `ExigenceDeForme` / `ModeDeComparaison`,
   `formeDeReference` / `reponseAttendue` : aucune regle ne les tient, et je ne fige pas un nom pour
   le plaisir d'en figer un. **Une seule contrainte est encodee** :
   `AucunVocabulaireDeMatiereTest` refuse douze mots de matiere dans `domain/content` et
   `domain/mechanics`, dont **`graphie`** - donc `graphieSignificative` est rouge aujourd'hui sans
   qu'aucune regle neuve ne soit ecrite.
2. **L'entree de `accent` dans cette liste de vocabulaire.** `ADR-0058` §2 a ecrit le declencheur :
   *"le lot qui ouvre la saisie decide de l'entree d'`accent` dans cette liste, en la mesurant sur
   ses propres sources"*. Ce lot-ci est celui-la. **Le motif de prudence tient toujours** : `accent`
   est la sous-chaine d'`accentue`, mot qu'une Javadoc de ce paquet peut employer a bon droit, et
   une regle qui rougit sur un mot etranger a son objet est la faute mesuree en s05. La decision se
   prend **apres** que les sources de la mecanique existent, jamais avant.
3. **Le type de paquet YAML.** Un `type: saisie` neuf avec sa fabrique, ou une extension du type
   `question` ? Je recommande le type neuf, et le motif est structurel : `YamlContentPackLoader`
   **echoue avec un message nomme** sur un type inconnu (*"type de contenu inconnu"*), alors qu'une
   fabrique `question` etendue accepterait un paquet de saisie a moitie rempli. Ce n'est pas encore
   une decision : elle appartient au lot 1, et elle est peu couteuse - la fabrique de `saisie` est le
   frere de `QuestionContentSourceFactory`, enregistre dans la meme table.
4. **Ce qu'un paquet de saisie fait aux garde-fous de contenu, et c'est le vrai cout.** **Mesure du
   2026-08-30, sur un paquet de sonde depose dans `contenu/` puis retire** :
   `bash outils/mvn-jdk21.sh -B -o -pl adapters/adapter-content -am test` rend **288 tests, 8 echecs
   et 28 erreurs**, repartis sur **13 classes** :
   `AuPlusTroisPropositionsParItemTest`, `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest`,
   `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`, `AucunMotServiNeSEcarteDeSonAccentTest`,
   `AucunSigneDoubleNEstDetachableDeSonMotTest`, `AucunePropositionNEstEgaleAUneAutreDansUnItemTest`,
   `ChaquePaquetATrousPorteSonTrouSurChaqueItemTest`, `ChaquePaquetPorteUnIndiceTest`,
   `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest`,
   `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`,
   `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest`,
   `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest`,
   `UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest`.
   **La cause est saine et le rouge est voulu** : ces garde-fous derivent leur perimetre du
   **repertoire** de contenu (`ADR-0013` obligation 1, decision de s05), donc ils voient le paquet de
   saisie des qu'il est pose, et **36 classes de test transtypent `(Question) item`** sans garde -
   35 dans `adapter-content`, une dans `domain` (`AdditionsBorneesContentSourceTest`). **Ce qui reste a decider est ce que chacun de ces
   treize garde-fous doit faire d'un item de saisie** : l'ignorer parce que son critere parle de
   propositions (le rang de la bonne reponse, l'egalite de deux propositions), ou l'inclure parce que
   son critere parle de texte servi (l'accent, le signe double, la notation d'un caractere). **Ce
   tri-la est le poste de travail le plus lourd du sprint, et il n'est pas mecanisable** : il se
   decide garde-fou par garde-fou, en lisant ce que chacun promet.
5. **Le decompte d'items d'un paquet de saisie.** `ChaquePaquetPorteUnIndiceTest.itemsDeclares`
   compte les lignes commencant par `- enonce:` pour borner ses tirages (`ADR-0064`). Un item de
   saisie qui porterait un autre premier champ ferait rendre **zero** a ce comptage, et un
   garde-fou qui borne a zero ne tire rien. C'est un vert silencieux, pas un rouge, **et c'est la
   seule defaillance muette que j'aie trouvee dans ce tri**. Elle est evitable a peu de frais - que
   l'item de saisie porte `enonce` en premier champ, comme les autres - mais elle doit etre **decidee
   et non subie**.

### 2.2 Le contrat du port et sa place dans les couches

**Rien n'est a decider sur les ports, et c'est verifie et non suppose** : `PartieUseCase`,
`ContentCatalogPort`, `MechanicRegistryPort` et `GameDefinition` prennent tous des types de contrat.
La mecanique de saisie s'y branche sans qu'une ligne de `application` bouge.

**Ce qui reste ouvert vit dans `adapter-web`, et c'est `R-A` :**

1. **Le retypage de l'etat de partie.** `EtatDePartie` et `EtatDePartieEnSession` sont types sur
   `BanditManchotSession`, et `PartieController` descend par transtypage des la construction. Il faut
   les retyper sur `GameSession`. **Ce n'est pas une decision d'architecture, c'est le travail que
   `R-A` designe** ; `AccesALEtatDeSessionTest` n'est pas touche - il garde *quel type* touche la
   session HTTP, pas *quel type de session* y est range.
2. **La forme du point de descente, et je ne la decide pas d'avance.** Deux formes tiennent `R-A` :
   deux branches nommees dans `PartieController` (`instanceof BanditManchotSession` /
   `instanceof SaisieSession`), ou un type par mecanique dans `adapter-web` qui rend le modele et le
   nom de fragment. **La premiere a moins de pieces mobiles et c'est celle que je recommande pour
   deux mecaniques.** *Ce qui me ferait changer d'avis* : une troisieme mecanique - l'operation
   posee est deja nommee par `ADR-0058` §8 - ou un second controleur. Une abstraction de dispatch
   ecrite pour deux cas est une abstraction ecrite pour un usage imagine.
3. **Le parametre du coup.** `POST /parties/{jeuId}/coups` porte `@RequestParam String
   propositionChoisie`. La saisie envoie autre chose. Route distincte ou parametre distinct : c'est
   un choix de lot, et il est sans risque - une route neuve entre d'elle-meme dans le perimetre du
   garde-fou de tabulation (section 1.5).

### 2.3 Le rendu htmx/Thymeleaf et le retour juste/faux

**Rien n'est decide, et c'est le poste ou le travail est** (`ADR-0058`, amendement 1, tableau de
`SAI-12`). Les faits qui contraignent :

1. **`fragments/manche.html` n'est pas reutilisable tel quel.** Il porte le cabinet, le bandeau, le
   levier, le socle et une `<form class="propositions">` qui boucle sur les propositions. Un ecran de
   saisie est un **second fragment**, pas une branche du premier. Ce qui s'y reprend a l'identique
   est le bloc `.message-jeu` sous le meuble - `.signal`, `.icone-signal`, `.verdict`,
   `.explication`, `.indice` - qui porte deja `M11` clause 1 (jamais la couleur seule) et sert
   `SAI-10` sans changement.
2. **Deux planchers de forme ne verront pas un `<input>`, et c'est mesure sur l'arbre du jour.**
   Quatre garde-fous derivent leurs pressables du selecteur `button, a[href]` :
   `TailleDesTextesEtBoutonsTest:489`, `LActionNeDeclarePasMoinsQueLaSortieTest:348`,
   `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest:356`,
   `LHabillageNeRogneJamaisUnElementPressableTest:88` (constante `PRESSABLES`). **Un seul garde-fou
   du module inclut `input`** : celui de la tabulation. *`ADR-0058` §7 citait la ligne 396 pour
   `TailleDesTextesEtBoutonsTest` ; elle est aujourd'hui a 489. Le fichier a bouge, le fait ne bouge
   pas.* **Consequence** : sans extension de ces selecteurs dans le meme commit, un champ de saisie
   sous le plancher de 1,25 rem ou sous la cible tactile de 44 px passerait une CI verte. C'est
   l'omission par enumeration de types, la lecon que ce depot a payee deux sprints.
3. **Ce qui n'est pas a moi.** Ce que l'ecran montre - la chaine ecrite seule a chaque refus
   (`SAI-9a`), les deux chaines a la revelation (`SAI-9b`), la chaine jugee a la reussite stricte
   (`SAI-2c`), une ou trois tentatives (`SAI-12`), une repetition vue ou non vue - est de la
   pedagogie et du dessin. **`ines-design` propose, `nadia-prof` tranche.** Le domaine transporte,
   il ne decide pas d'afficher.

### 2.4 La session

**Rien de neuf n'est a decider, et `DEC-0028` n'est pas menace.** L'etat d'une partie de saisie vit
dans la session HTTP comme celui d'une partie de bandit manchot, disparait avec elle, et rien n'est
ecrit. Trois faits verifies :

- `EtatDePartieEnSession` reste **le seul type du depot qui touche `HttpSession`**, et
  `AccesALEtatDeSessionTest` continue de le garder sans modification ;
- le verrou de session et le cycle lire-calculer-ecrire ne changent pas de forme : `jouer` prend un
  `UnaryOperator` sur le type de session, il devient un `UnaryOperator<GameSession>` ;
- **l'alternative de ranger la chaine recue dans la session HTTP est deja ecartee**, par
  l'amendement 3 d'`ADR-0058` : elle recree un champ parallele a tenir en phase avec chaque
  construction de session, forme exacte du defaut d'`ADR-0016`. La chaine voyage sur le verdict.

**Un point que personne n'a instruit et que je nomme plutot que de le supposer** : rien dans ce
depot ne rend une session de jeu serialisable, et rien ne l'exige - le conteneur est embarque, la
session n'est ni repliquee ni persistee (`DEC-0028`). La mecanique de saisie n'a donc **aucune
contrainte de serialisation**, et ce n'est vrai que tant que `DEC-0028` tient.

### 2.5 Les regles ArchUnit qui devront exister dans le meme geste que le code

**Etat exact aujourd'hui : 30 regles ArchUnit reelles.** Methode : balayage des fichiers `.java` des
quatre arbres de modules du worktree, arbre de contre-exemple `faux-module` exclu, comptage des
lignes portant `static final ArchRule` - **30**. Les deux autres occurrences de la chaine `ArchRule `
sont une constante et une phrase de Javadoc de `LesGardeFousSontMisALEpreuveTest`. **Les deux `ADR`
de ce lot n'en ajoutent aucune** : `ADR-0069` s'appuie sur une regle existante et lui ajoute une
morsure, `ADR-0070` est un garde-fou de balayage.

**Ce qui est du avec le code de la saisie**, et je le nomme lot par lot plutot qu'en vrac :

| Regle | Nature | Due avec |
|---|---|---|
| `R-A` - `adapter-web` ne nomme aucune mecanique hors de son point de descente | ArchUnit, rouge aujourd'hui (20 lignes, 3 fichiers) | Lot 3 |
| `R-B` - aucune distance entre chaines dans une mecanique (`SAI-5`) | **Pas ArchUnit** - test unitaire portant la table de couples de `nadia-prof` | Lot 2 |
| `R-C` - l'ordre des tentatives est l'ordre d'ecriture (`SAI-12`) | **Pas ArchUnit** - test unitaire, avec sa seconde moitie | Lot 2 |
| `R-D` - le verdict porte la chaine recue apres resolution, revelation comprise | **Pas ArchUnit** - test unitaire, avec sa seconde moitie | Lot 2 |
| Extension du perimetre des pressables a `input` | Garde-fou de balayage existant, a etendre **avec sa morsure** | Lot 4 |
| Le tri des treize garde-fous de contenu face a un item de saisie | Garde-fous existants, a etendre ou a borner explicitement | Lot 5 |

**Une regle que je n'ecris pas, et le motif.** Aucune regle ne peut dire qu'une mecanique de saisie
ne compte pas les caracteres. ArchUnit voit des types ; une boucle sur `java.lang.String` n'en cite
aucun de particulier. **La moitie "bibliotheque tierce" de `SAI-5` est deja gardee** par la clause 3
d'`ADR-0004` - une mecanique ne depend que du JDK et des deux contrats, donc aucune bibliotheque de
similarite n'y entrera jamais. La moitie restante est un test unitaire, et il est un livrable de
story, pas une bonne intention.

---

## 3. Le piege des cles YAML ignorees - il s'applique, et voici ce qui l'arrete

**Oui, il s'applique, et il s'applique plus fort ici qu'aux trois fois precedentes.**

`QuestionContentSourceFactory` ignore sans un mot toute cle qu'elle ne connait pas ; sa propre
Javadoc l'ecrit depuis `ADR-0024` : *"c'est la dette que l'ADR nomme et n'a pas fermee."* **Le depot
a paye trois fois le meme remede et jamais la propriete** : `ADR-0050` clause 6 pour `texteSupport`,
l'avis de mecanisme de s17, l'avis de faisabilite de s22. Chacun a produit un test de traversee pour
**un champ nomme** ; aucun ne garde le champ suivant.

**Pourquoi c'est pire pour la saisie.** Une forme d'item neuve apporte au moins trois cles d'un coup
- forme de reference, formes acceptees, mode de forme - et elles sont ecrites par `nadia-prof` dans
des paquets qu'aucune fabrique ne lit le jour ou ils sont ecrits (`SAI-7` : elle ecrit ou relit la
liste **avant** tout Done). **Et le pire cas n'est pas un ecran vide** : une liste de formes
acceptees perdue en route ne vide rien, elle **refuse une reponse juste**, ce qui est exactement
`SEUIL-2` - *"si une reponse juste refusee pour une question de forme coute un essai, la saisie ne
doit pas exister"*. La CI serait verte et personne ne verrait rien.

**Ce qui l'arrete : un garde-fou, ecrit, vert et mordant dans ce lot - `ADR-0070`.**

> Toute cle de schema d'un paquet servi est un litteral de chaine d'au moins une source Java du
> depot, commentaires exclus.

`bootstrap/src/test/java/com/recreo/archi/AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.java`,
**4 tests, 0 echec**. Ce qu'il refuse concretement :

- une cle **mal orthographiee** par rapport a celle que le code lit ;
- une cle **ecrite avant que le champ existe** - c'est le cas de ce sprint, nomme ;
- une cle **nommee seulement dans un commentaire**, une Javadoc ou un `ADR` recopie : la conception
  ecrite d'avance est le vecteur du defaut, pas une circonstance attenuante ;
- une cle dont le nom est la **sous-chaine** d'une cle saine : le critere compare des chaines
  entieres, `graphi` n'est pas `graphie`, et la faute de s05 est fermee par construction.

**Mesure du cas vert, avec sa methode** : 27 paquets de
`adapters/adapter-content/src/main/resources/contenu`, **23 cles de schema distinctes**, 193 fichiers
`.java` des arbres `src/main/java` et `src/test/java` des cinq modules declares, 4 355 litteraux de
chaine distincts - **zero cle orpheline, zero exemption, aucun plafond de dette a ouvrir**. Il ne
s'agit donc pas d'un cliquet sur une dette existante : la propriete est deja vraie, ce lot la rend
opposable.

**Ce que ce garde-fou ne prouve pas**, ecrit avant qu'on me le reproche : qu'une cle citee quelque
part soit lue **au bon endroit**. Un litteral pose dans un test qui n'ouvre aucun paquet le
satisferait. Il tient la propriete faible parce que c'est celle qu'une machine peut tenir ; les tests
de traversee par champ restent utiles et ne sont remplaces par rien.

---

## 4. Les lots

Sequencement, modules, dependances. **Les lots 1 a 4 forment le chemin minimal d'une saisie jouable
de bout en bout ; le lot 5 est celui qu'on sous-estime.**

### Lot 0 - deja livre par ce document

`ADR-0069`, `ADR-0070`, la morsure sur la forme non chiffree, le garde-fou des cles de schema,
l'amendement 4 d'`ADR-0058`, l'index. **Modules** : `bootstrap` (tests). **Fait.**

### Lot 1 - la forme d'item et son chargement

- **Modules** : `domain` (le type d'item, le type de mode, la couture scellee), `adapter-content` (la
  fabrique `saisie` et son enregistrement), `bootstrap` (les deux arrets de compilation).
- **Depend de** : rien.
- **Contenu** : `ReponseAEcrire`, le type de mode (`ADR-0069`), `Item permits ...`, la fabrique, la
  lecture de la cle facultative au niveau du paquet **et** de l'item (`SAI-2a`), et un paquet de
  sonde **sous `src/test/resources`, jamais sous `src/main/resources/contenu`** - un paquet pose la,
  meme hors catalogue, declenche a lui seul le lot 5 (les garde-fous derivent du repertoire, pas du
  catalogue).
- **Ce qui rougit si on l'oublie** : les deux arrets de compilation, `ADR-0070` sur toute cle
  premature, `DifficulteTest` sur un booleen.
- **Tient dans un sprint** : oui, largement. C'est le lot le mieux instruit du sprint.

### Lot 2 - la mecanique

- **Modules** : `domain` (`com.recreo.domain.mechanics.saisie`), `bootstrap` (le `@Bean`).
- **Depend de** : lot 1.
- **Contenu** : la mecanique, sa session (`List<String> tentatives`, `derniereTentative()`
  calculee), son `Move`, son verdict a deux composantes de plus, la normalisation `SAI-1`/`SAI-3`/
  `SAI-4`, et **`R-B`, `R-C`, `R-D` dans le meme commit** - ce sont les trois seuls mecanismes qui
  gardent `SAI-5`, `SAI-12` et `SAI-2c`.
- **Point de vigilance nomme** : la decision sur `accent` dans `AucunVocabulaireDeMatiereTest` se
  prend **ici**, mesuree sur les sources de la mecanique, jamais avant.
- **Tient dans un sprint** : oui. Le domaine est pur, sans framework, et la conception ne laisse rien
  d'ouvert.

### Lot 3 - le retypage d'`adapter-web` et `R-A`

- **Modules** : `adapter-web` seulement.
- **Depend de** : lot 2 (il faut un second type de session a ranger).
- **Contenu** : `EtatDePartie` et `EtatDePartieEnSession` retypes sur `GameSession`, le point de
  descente nomme, **`R-A` ecrite et verte dans le meme commit**.
- **Cout mesure** : 20 lignes dans 3 fichiers de production, plus les tests du module qui nomment ces
  types.
- **Tient dans un sprint** : oui, et c'est le lot le plus mecanique des cinq. **Il ne doit pas etre
  fusionne avec le lot 4** : `R-A` est verte ou elle ne l'est pas, et un lot qui melange le retypage
  et le rendu rend cette frontiere illisible.

### Lot 4 - l'ecran de saisie

- **Modules** : `adapter-web` (fragment, feuille maison, controleur), `bootstrap` (le test de
  traversee).
- **Depend de** : lot 3.
- **Contenu** : le second fragment, la route ou le parametre du coup, **l'extension a `input` du
  selecteur des quatre garde-fous de pressables, avec sa morsure**, et le rendu de `SAI-8`,
  `SAI-9a`, `SAI-9b`, `SAI-2c`, `SAI-12`.
- **Dependance hors architecture, et elle est bloquante** : ce que l'ecran montre est une decision
  d'`ines-design` sous le veto de `nadia-prof`. **Ce lot ne peut pas commencer avant cette
  proposition.**
- **Tient dans un sprint** : oui **si** la proposition de rendu existe a l'ouverture. Sinon, non.

### Lot 5 - le tri des treize garde-fous de contenu

- **Modules** : `adapter-content` (13 classes), et `domain` si son unique transtypage est touche.
- **Depend de** : lot 1, et il est declenche par le **premier paquet de saisie pose dans
  `contenu/`**, pas par le catalogue.
- **Contenu** : pour chacun des treize, decider si son critere s'applique a un item de saisie, et
  ecrire ce que la decision implique - l'etendre, ou le borner explicitement avec son motif.
- **Ce que je refuse d'avance** : un transtypage defensif silencieux (`if (item instanceof
  Question)` sans motif ecrit) dans les treize. Ce serait treize exemptions muettes posees en une
  fois, et c'est la famille de defaut que ce depot sanctionne depuis s05.
- **Tient dans un sprint** : **c'est le seul lot dont je ne reponds pas.** Treize decisions de
  perimetre, chacune a lire, aucune mecanisable. C'est le poste que je surveillerais si le sprint
  devait deraper.

---

## 5. Verdict de faisabilite

**Faisable dans un sprint : l'ouverture de la saisie jusqu'a un jeu jouable de bout en bout, lots 1 a
4, a trois conditions nommees.**

1. **La proposition de rendu d'`ines-design` existe a l'ouverture**, ou le lot 4 glisse et la saisie
   n'est pas jouable a la cloture. C'est la seule dependance externe du chemin critique.
2. **Le lot 5 est compte dans le sprint et non decouvert dedans.** Treize garde-fous a instruire est
   un poste de travail, pas une finition. Si le perimetre le laisse dehors, alors le premier paquet
   de saisie ne peut pas entrer dans `contenu/` ce sprint - et sans paquet, la saisie n'est pas
   jouable. **Les deux ne peuvent pas etre vrais en meme temps** : c'est le point que je remonte le
   plus fort a `sofia-sm` et `camille-po`.
3. **Les regles dues sont dans le commit de leur code, pas dans un lot de rattrapage.** `R-A`, `R-B`,
   `R-C`, `R-D` et l'extension des pressables. Une regle repoussee d'un lot ne s'ecrit jamais.

**Sur les trois paquets de contenu neufs** : c'est du perimetre, donc `camille-po`. Mon seul avis
technique est un avis de sequencement : **un paquet de saisie ne peut pas etre ecrit avant le lot
1**, sans quoi `ADR-0070` le refuse - c'est precisement ce pour quoi il est ecrit. Un paquet de choix
neuf, lui, ne depend d'aucun lot de ce document.

**Ce qui serait infaisable, et je le dis maintenant plutot qu'a la cloture** : ouvrir la saisie
**et** livrer trois paquets de saisie dans le meme sprint. Le lot 5 tombe entre les deux, et le
premier paquet le declenche. Trois paquets de **choix** a cote de l'ouverture de la saisie, c'est un
autre calcul, et il est de `camille-po`.

---

## 6. Ce que je ne decide pas, et ce que je route

- **A `camille-po`** : le perimetre, l'arbitrage entre volume de contenu et ouverture de mecanique,
  et le compte de couverture. Ce document ne classe aucune ligne du referentiel.
- **A `nadia-prof`** : tout ce que l'enfant voit et lit. En particulier `SAI-12` (une tentative ou
  trois a l'ecran, une repetition vue ou non vue, la comparaison avant ou apres normalisation), la
  formulation des retours de `SAI-8`, `SAI-9` et `SAI-2c`, et le contenu des listes de formes
  acceptees. **Aucun automate ne les garde et cela ne changera pas.**
- **A `ines-design`, via `sofia-sm`** : l'ecran de saisie. C'est la dependance bloquante du lot 4, et
  elle doit etre demandee a l'ouverture du sprint, pas au milieu.
- **A `sofia-sm`** : le renommage de ce repertoire, et l'arbitrage nomme au point 2 de la section 5 - le lot 5 et
  le premier paquet de saisie sont indissociables.
- **Une question qui reste ouverte a l'utilisatrice et qui ne bloque rien** : `nadia-prof` demande
  depuis le 2026-08-24 *"sur la tablette visee, que coute un accent ?"*. La correction automatique a
  ete tranchee le 2026-08-25 ; le cout d'un accent ne l'a pas ete. **Cela ne change aucune ligne de
  ce document** - `SAI-1` est le defaut de la conception, donc l'architecture est la meme dans les
  deux cas - mais cela change la valeur pedagogique de la saisie alphabetique, et c'est a elle.

---

## 7. Confirmation d'execution

`bash outils/mvn-jdk21.sh -B clean verify` a la racine du reacteur, arbre du worktree `s24-theo`
avec les livrables de ce document : **BUILD SUCCESS, 807 tests, 0 echec** (49 `domain` +
13 `application` + 332 `adapter-web` + 288 `adapter-content` + 119 `bootstrap` surefire +
6 `failsafe`), JDK **21.0.10** de la toolchain - version rapportee par la machine virtuelle des tests elle-meme, jamais lue sur une
declaration (`ADR-0071`). Pas de `-am` a citer : le reacteur est
complet par construction, c'est la commande de la porte technique.

Ce lot ajoute **cinq tests** : les quatre d'`AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest` et
la morsure d'`ADR-0069`. **Je n'ai pas rejoue le build de reference avant le lot** ; le delta se lit
dans le diff plutot que dans un chiffre que je n'aurais pas mesure.

Le garde-fou de chemins de la CI est passe sur l'index complet des fichiers suivis du worktree :
`git ls-files ... | bash .github/scripts/verifier-chemins.sh` - **aucun chemin de poste de travail ni
nom de compte**.

---

## 8. Complement du 2026-08-30 - la version de JDK declaree contre celle qui execute

`sofia-sm` route un constat de `nadia-prof` : une entree de toolchain declarant `21` et designant un
JDK 25. **Le mecanisme est exact et je l'ai reproduit ; la conclusion sur ce build ne l'est pas.** La
machine virtuelle qui execute les tests rapporte d'elle-meme `java.version = 21.0.10`. Ce qui etait
faux dans mes documents est la **methode** citee, pas le chiffre - il etait lu sur une declaration,
il l'est desormais sur l'execution. `ADR-0071` rend la coherence verifiable a chaque build.

Le detail - le fait constate, la correction, le perimetre historique avec sa methode, et ce qu'un
garde-fou peut ou ne peut pas arreter - vit dans
`complement-jdk-declare-contre-execute.md`, dans ce meme repertoire. **Le verdict de faisabilite de
la section 5 est inchange.**

**Mesure apres ce complement**, meme commande a la racine : **BUILD SUCCESS, 811 tests, 0 echec**
(49 `domain` + 13 `application` + 332 `adapter-web` + 288 `adapter-content` + 123 `bootstrap`
surefire + 6 `failsafe`). Les quatre tests de plus que les 807 de la section 7 sont ceux du garde-fou
d'`ADR-0071` ; le chiffre de la section 7 n'est pas corrige, il datait de l'etat de l'arbre a ce
moment-la.
