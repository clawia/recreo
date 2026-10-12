# ADR-0105 - L'accueil en deux vues lit la matiere par le port entrant, et son choix de vue ne vit que dans la session HTTP

- **Statut** : Accepte
- **Date** : 2026-10-11
- **Auteur** : theo-archi
- **Sprint** : hors sprint, chantier de l'accueil en deux vues (avis :
  `squad/sprints/hors-sprint-accueil-vues/avis-faisabilite.md`)
- **Prolonge** : `ADR-0044` (le choix des jeux montres vit derriere le port entrant), `ADR-0019` (un
  seul type touche la session HTTP), `ADR-0047` (le marqueur de partie), `ADR-0008` (la liste de mots
  interdits du JavaScript maison), `ADR-0013` (garde-fou de balayage eprouve), `DEC-0028` (rien n'est
  conserve entre deux sessions)
- **Arbre mesure** : worktree `theo-accueil`, base `8ac33440` (tip de `round-two`). **JDK 21.0.10**,
  propriete `java.version` des rapports `TEST-*.xml` (`ADR-0071`), enveloppe `bash outils/mvn-jdk21.sh`.

## Contexte

Le chantier demande deux presentations de l'accueil : une vue a deux etages (les matieres d'un
niveau, puis les jeux d'une matiere) et une vue unique groupee par matiere ; vue par defaut la
seconde ; un bouton bascule ; **le choix vit dans la session HTTP et disparait avec elle** ; la
matiere vient de `descripteur.matiere` du paquet de chaque jeu (`ADR-0049`, `ADR-0006`).

Ce qui est deja garde, verifie dans le code et non suppose :

- `LeChoixDesJeuxResteDerriereLePortTest` interdit a `adapter-web` et `bootstrap` toute dependance
  vers `ContentDescriptor`, et `LeChoixDesJeuxNeVitPasDansLaVueTest` interdit a un gabarit de nommer
  `descripteur`. **Grouper par matiere dans le controleur ou dans le gabarit est donc deja rouge.**
- `AccesALEtatDeSessionTest` ne laisse qu'`EtatDePartieEnSession` toucher la session HTTP.

Ce qui ne l'est pas, trouve en lisant les quatre garde-fous que le chantier traverse :

1. **`TexteAccentueExposeParLePortEntrantTest` lit deux methodes du port entrant nommees a la main**,
   `jeuxDisponibles()` et `niveauxDuCatalogue()`. Une methode neuve qui rend les matieres d'un niveau
   - la premiere chose que le lot ecrira - exposerait des libelles lus par l'enfant sous `th:text`
   sans entrer dans l'inventaire. Perimetre enumere, echec par omission.
2. **Aucune regle n'empeche un choix d'affichage de survivre a la session.** Le chemin le moins cher
   d'une bascule est de recopier les quatre lignes de `PartieController.marquerPartieEnCours` avec une
   duree d'un an : rien ne rougit. Cote client, `localStorage` et `sessionStorage` sont interdits au
   JavaScript maison, mais ni `document.cookie`, ni `indexedDB`, ni l'API `caches`.
3. **Un lien `<a th:href>` sans `href` litteral sort en silence de cinq garde-fous de pressable**, qui
   derivent leur population de `"button, a[href], input"` lu sur le gabarit brut
   (`LHabillageNeRogneJamaisUnElementPressableTest.PRESSABLES`, lu par `TailleDesTextesEtBoutonsTest`,
   `LActionNeDeclarePasMoinsQueLaSortieTest`, `LHabillageResteImmobileTest`,
   `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` et lui-meme). Le commentaire de la puce de niveau
   d'`accueil.html` nomme ce piege en prose ; aucun test ne le tient. Une tuile de matiere est
   exactement ce lien. Methode : `grep -lF PRESSABLES` sur les tests d'`adapter-web`.

## Decision

1. **La matiere atteint la vue par le port entrant, deja groupee.** Les listes de matieres et les
   groupes de jeux par matiere sont calcules dans `application`, derriere `PartieUseCase`. La forme
   exacte des methodes appartient au lot, sous deux contraintes deja ecrites par `ADR-0044` : aucune
   valeur sentinelle pour dire "tous les niveaux", et aucun ordre de matiere ecrit en code.
   **Toute methode du port entrant qui parle a une vue est lue par l'inventaire des chaines
   exposees.**
2. **Le choix de vue vit dans la session HTTP, par le seul porteur.** Il est un attribut de session
   ecrit et lu par `EtatDePartieEnSession` (`ADR-0019` inchange). La route qui l'ecrit **porte la vue
   visee** et ne calcule jamais "l'autre vue" a partir de l'etat courant : ecrire une valeur recue n'est
   pas un cycle lire-calculer-ecrire, et un double appui rend deux fois le meme etat. `abandonner()`
   ne l'efface pas : quitter une partie n'est pas changer d'affichage. Valeur absente = vue par
   defaut.
3. **Le seul cookie du produit reste le marqueur de partie, et le JavaScript maison n'ecrit dans
   aucun stockage du navigateur.**
4. **Tout lien d'un gabarit porte un `href` litteral**, que `th:href` ecrase au rendu.

**Ce que cet ADR ne decide pas** : ou vit la correspondance matiere vers libelle court et
pictogramme, la forme du pictogramme, la place du bouton, le comportement de "Les jeux" et de l'accueil
sans niveau. Ces points dependent de la proposition d'`ines-design` et de l'arbitrage de
`nadia-prof` ; l'avis de faisabilite les liste avec l'`ADR` et les regles qu'ils appelleront.

## Regle executable ArchUnit

### Regle 2 (ArchUnit) - le seul cookie est le marqueur de partie

Fichier : `bootstrap/src/test/java/com/recreo/archi/LeSeulCookieEstLeMarqueurDePartieTest.java`

```java
@ArchTest
static final ArchRule aucunCookieNeNaitHorsDuMarqueurDePartie = codeUnits()
        .that(HORS_DU_MARQUEUR_DE_PARTIE)
        .should(neFaireNaitreAucunCookie())
        .as("ADR-0105 : le seul cookie du produit est le marqueur de partie (ADR-0047) ; "
                + "un choix d'affichage vit dans la session HTTP (DEC-0028)");
```

`neFaireNaitreAucunCookie` refuse tout appel au constructeur de `jakarta.servlet.http.Cookie` et tout
acces a `org.springframework.http.ResponseCookie`. **L'exemption vaut exactement la dette** : les deux
methodes `marquerPartieEnCours` et `effacerMarqueurDePartie` de `PartieController`, par leur classe et
leur nom. Le test `lesDeuxPointsExemptesFontEncoreNaitreUnCookie` compare par egalite l'ensemble des
methodes qui font reellement naitre un cookie a ces deux noms : un nom qui ne designe plus rien, ou un
troisieme point, rougit.

**Contre-exemple** (`ADR-0003`) :
`bootstrap/src/test/java/com/recreo/adapter/web/contreexemple/PreferenceDeVueEnCookieContreExemple.java`,
la bascule ecrite en recopiant le marqueur avec une duree d'un an. Morsure :
`LesReglesMordentTest.unChoixDeVueRetenuParUnCookieEstRefuse`.

**Ce qu'elle ne voit pas** : un en-tete `Set-Cookie` ecrit en clair par `setHeader`. Le nom de
l'en-tete est une chaine, ArchUnit ne lit pas les chaines.

### Regles 1, 3 et 4 - pourquoi ce ne sont pas des regles ArchUnit

Elles portent sur un inventaire de chaines servies, sur un fichier JavaScript et sur des gabarits :
rien de cela n'est du bytecode. Le mecanisme automatique est un test JUnit execute par `mvn verify`.

- **Regle 1** - `TexteAccentueExposeParLePortEntrantTest.chaqueMethodeDuPortEntrantQuiParleAUneVueEstLueParCetInventaire` :
  l'ensemble des methodes de `PartieUseCase` qui ne rendent pas une `GameSession` est compare par
  egalite a l'ensemble que l'inventaire appelle. Le test du catalogue reel appelle desormais aussi
  `jeuxDuNiveau` pour chaque niveau. Morsure permanente :
  `uneMethodeNeuveDuPortQueLInventaireNeLitPasFaitEchouerLeGardeFou`, sur un port qui ajoute
  `matieresDuNiveau(String)`. La frontiere `GameSession` est celle que la classe trace deja entre un
  record de catalogue et un texte de paquet, juge dans `adapter-content`.
- **Regle 3** - `FrontieresDuJavaScriptTest.INTERDITS_JS` gagne `cookie`, `indexedDB` et `caches.`.
  Ces trois mots sont absents de `static/js/` a la date de cet `ADR` (recherche insensible a la casse).
- **Regle 4** - `adapters/adapter-web/src/test/java/com/recreo/adapter/web/ToutLienDeGabaritPorteUnHrefLitteralTest.java` :
  tout `<a>` des gabarits de production porte un attribut `href`. Jeu fautif commite :
  `contreexemple/ToutLienDeGabaritPorteUnHrefLitteralTest/tuile-sans-href`, la tuile de matiere en
  `th:href` seul ; morsure sur le materiau reel : la puce de niveau d'`accueil.html` privee de son
  espace reserve, mutee en memoire.

### Preuve de morsure sur la production, methode comprise

Trois mutations appliquees ensemble a l'arbre de ce commit, puis annulees (`git status --short` ne
montre plus que les fichiers de ce commit) : une methode `retenirLaVue` qui fait naitre un cookie d'un
an dans `PartieController` ; `document.cookie = "recreo-vue=V1; max-age=31536000";` ajoute a
`recreo.js` ; une methode par defaut `matieresDuNiveau(String)` ajoutee a `PartieUseCase`. Commande :
`bash outils/mvn-jdk21.sh -B -q -o -pl adapters/adapter-web,bootstrap -am test
-Dtest='FrontieresDuJavaScriptTest,LeSeulCookieEstLeMarqueurDePartieTest,TexteAccentueExposeParLePortEntrantTest'`.
Resultat lu dans les rapports XML : `FrontieresDuJavaScriptTest` 13 tests, 1 echec (*"Expecting empty
but was: ["cookie"]"*) ; `LeSeulCookieEstLeMarqueurDePartieTest` 2 tests, 2 echecs (la regle nomme
`PartieController.retenirLaVue` et `Cookie.<init>`, l'egalite trouve trois points au lieu de deux) ;
`TexteAccentueExposeParLePortEntrantTest` 7 tests, 1 echec (l'ensemble declare porte
`matieresDuNiveau`). Perimetre de cette mesure : deux modules et leurs amonts, pas le depot.

**Compte des regles ArchUnit** : 38 declarations `ArchRule <nom> =` dans `src/test/java` des cinq
modules du `pom.xml` agregateur apres ce commit, 37 avant - methode de
`LesGardeFousSontMisALEpreuveTest.toutesLesRegles`, reproduite par `grep -P 'ArchRule\s+\w+\s*='`.

## Consequences

- Le lot de l'accueil recoit des rouges nommes avant d'ecrire : une methode de port non inventoriee,
  un cookie de preference, un stockage client, une tuile sans `href`.
- **Mauvaise consequence assumee** : `EtatDePartieEnSession` porte desormais un etat qui n'est pas un
  etat de partie, et son nom le dit mal. Le renommer toucherait la regle d'`ADR-0019` et son
  contre-exemple pour un gain de lecture ; je ne le fais pas tant qu'un seul attribut non lie a la
  partie y vit. Ce qui me ferait changer d'avis : un deuxieme.
- La regle 4 interdit un `<a>` sans destination, par exemple une ancre nommee `<a id="...">`. Aucun
  gabarit n'en porte ; si un usage reel apparait, la regle se reecrit sur `a:not([id])`, pas par une
  exemption.
- La liste `INTERDITS_JS` reste une enumeration : un quatrieme stockage du navigateur passerait. Elle
  couvre ceux qu'on ecrit sans y penser.

## Alternatives considerees

1. **Ne rien ecrire et laisser le lot porter ses tests.** Ecartee : les trois trous sont des chemins
   de moindre cout du lot lui-meme, et `ADR-0043` rappelle qu'une regle confiee au lot qui en a besoin
   n'a pas ete ecrite.
2. **Basculer en calculant la vue opposee a partir de la session.** Ecartee : c'est un cycle
   lire-calculer-ecrire, que `ADR-0019` a mesure perdant sous double appui.
3. **Un second porteur de session dedie a l'affichage.** Ecartee : la regle d'`ADR-0019` deviendrait
   une liste de porteurs, et deux classes qui verrouillent la meme session sur le meme mutex n'ajoutent
   rien qu'une piece mobile.
4. **Etendre `PRESSABLES` a `a[th:href]`.** Ecartee : la forme `th:attr="href=..."` passerait, et
   changer ce litteral deplace la population de cinq garde-fous a la fois.
5. **Faire lire l'inventaire par reflexion sur toutes les methodes du port.** Ecartee : une methode a
   parametre (`jeuxDuNiveau`) demande une valeur que la reflexion ne derive pas ; l'egalite entre
   methodes declarees et methodes appelees tient la propriete sans deviner d'arguments.

## Amendement du 2026-10-12 (hors sprint, cloture de l'accueil par matiere, `theo-archi`) - le cookie de session, et l'identifiant de session dans les adresses

**Ce que la decision 3 disait faux.** *"Le seul cookie du produit reste le marqueur de partie"* : chaque reponse qui cree une
session pose aussi `JSESSIONID`. La regle 2 ne porte que sur les cookies que le code du produit fait naitre (`Cookie`,
`ResponseCookie`) et elle est juste ; c'est la phrase qui est trop large. **Elle se lit desormais** : *le seul cookie que le code
du produit fait naitre est le marqueur de partie ; le cookie de session du conteneur est le transport de la session HTTP que la
decision 2 et `DEC-0028` supposent, et il meurt avec elle.* Comportement accepte : c'est la question R8 (b) de `lea-dev`.

**Ce qui est un defaut : l'identifiant de session voyage aussi dans les adresses.** Mesure, jar de `75290b26`, port libre, JDK
21.0.10, `curl` sans cookie : `GET /` rend 9 occurrences de `jsessionid` dans le corps, dont la puce
`href="/niveaux/CP;jsessionid=..."` ; `GET /niveaux/CP` en rend 76 (feuilles, icone, puces, `hx-post` de chaque carte) ; la meme
requete avec le cookie en rend 0. `yanis-qa` a mesure la meme chose, et la presence sur l'arbre d'avant ce chantier
(`rapport-qualite.md`, section 3). **Cause** : aucun fichier du depot ne regle les modes de suivi de session (lu : `bootstrap/src/main/resources`
ne porte que `META-INF`), le conteneur garde donc le cookie **et** la reecriture d'URL, et les adresses que le gabarit compose
sont reecrites tant que la requete n'a pas rapporte de cookie. Prouvee par l'effet : restreindre le suivi au cookie (point 1
ci-dessous) fait tomber les occurrences a 0, sans autre changement. Un clic sur la premiere puce met l'identifiant dans la barre d'adresse, ou il reste pour toute la
partie : une adresse copiee ou mise en signet emporte la session de l'enfant. Le produit n'a rien a gagner de ce repli : un
navigateur qui refuse les cookies ne garde deja pas le marqueur de partie (`ADR-0047`).

**Correctif, a porter par le developpeur que `sofia-sm` designe** (le code de production n'est pas touche par cet amendement) :

1. Creer `bootstrap/src/main/resources/application.properties`, une ligne :
   `server.servlet.session.tracking-modes=cookie`. Sa place est `bootstrap`, seule couche qui configure le conteneur.
   **Mesure de ce correctif sans toucher le code** : le meme jar lance avec l'argument
   `--server.servlet.session.tracking-modes=cookie` (Spring Boot 4.1.0) rend 0 occurrence sur `/`, `/niveaux/CP` et `/niveaux/CM1`
   sans cookie, un seul `Set-Cookie` (`JSESSIONID`), et la session tient : une partie demarree, `POST /retour` rend la
   confirmation.
2. Dans le meme commit, `JarRecreoIT` garde la propriete sur le seul conteneur reel du depot (le `MockMvc` des autres tests ne
   reecrit jamais une adresse, donc n'y verrait rien), apres la lecture de la premiere reponse :

```java
assertThat(reponse.body())
        .as("ADR-0105, amendement du 2026-10-12 : l'identifiant de session ne voyage que par son cookie, jamais dans une adresse")
        .doesNotContainIgnoringCase("jsessionid");
```

   Elle est rouge sur l'arbre d'aujourd'hui (9 occurrences dans la premiere reponse), ce qui en fait la moitie de l'epreuve ;
   retirer la ligne du point 1 la refait rougir.

**Ce que ce garde-fou ne voit pas** : une adresse ecrite en dur dans un gabarit avec un identifiant, ou un autre parametre de
session ajoute plus tard. Le premier se lit dans le diff ; le second passe par le meme fichier de proprietes.

**Mauvaise consequence** : le depot gagne son premier fichier de proprietes de production. `ADR-0037` a ecrit d'avance la
propriete qu'il ne doit pas porter sans revoir son garde-fou (`spring.web.resources.static-locations`).

**Ce n'est pas une regle ArchUnit** : l'objet est une configuration du conteneur et une reponse HTTP, pas du bytecode. Compte
ArchUnit inchange. **Statut** : la phrase corrigee de la decision 3 est acceptee ; la propriete "l'identifiant de session ne
voyage que par son cookie" est due par le commit du correctif, qui porte sa garde (precedent : `ADR-0048`).
