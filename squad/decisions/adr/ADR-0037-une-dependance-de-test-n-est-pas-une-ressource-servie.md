# ADR-0037 - Distinguer une dependance de test d'une ressource servie, et refuser tout octet servi qui n'est pas vendorise

- **Statut** : Accepte
- **Date** : 2026-08-14
- **Auteur** : theo-archi
- **Sprint** : s10 (point route par `sofia-sm` a la cloture)

## Contexte

Un lot du sprint 10 a ajoute `org.openjdk.nashorn:nashorn-core` en portee `test`, pour executer
reellement `recreo.js` dans un test JVM plutot que d'en lire le texte - c'est ce test qui a leve la
reserve L de `nadia-prof`. `yanis-qa` a instruit la question a la cloture (`rapport-qualite.md`,
section 3.2) et conclut : geste legitime, hors du perimetre d'`ADR-0014`, mais **arbitrage ecrit
nulle part** - ni `ADR`, ni `DEC`, ni message de commit. Il recommande une ligne de doctrine
generale plutot qu'un `ADR` dedie a ce cas.

### Ce que j'ai verifie moi-meme, et non repris de lui

- **Le pom.** `nashorn-core` est declare deux fois en portee `test` : dans le `dependencyManagement`
  du pom agregateur (avec `<nashorn.version>15.7</nashorn.version>`) et dans
  `adapters/adapter-web/pom.xml`. Sa portee effective est donc `test`, sans ambiguite de resolution.
- **Le livrable.** `bootstrap/target/recreo.jar`, reconstruit a la tete de ma branche, embarque
  **43 archives** sous `BOOT-INF/lib/` ; `nashorn-core` n'en fait pas partie. Ce n'est pas une
  deduction depuis la portee declaree, c'est la liste lue dans l'archive produite.
- **Le texte d'`ADR-0014`.** Ses huit points de decision portent tous sur un **fichier servi** :
  l'emplacement `static/vendor/`, la ligne de `provenance.txt`, l'empreinte SHA-256, le
  fonctionnement hors ligne, l'ordre de chargement des feuilles. Rien n'y gouverne un outil qui ne
  quitte jamais le classpath de test.

**La lecture de `yanis-qa` est exacte sur les deux points.** J'y ajoute la nuance qu'il ne nomme
pas, et qui explique pourquoi cet arbitrage etait difficile a ranger : le point 1 d'`ADR-0014` est
ecrit *"une bibliotheque front tierce entre par vendorisation, jamais par une dependance qui apporte
tout un paquet"*. Pris a la lettre, il parle de **dependances**, pas de fichiers servis - donc il se
lit comme s'il gouvernait toute dependance Maven. C'est cette ambiguite que le present ADR ferme, et
c'est pour cela qu'il ne suffit pas de recopier la conclusion de `yanis-qa` : il faut dire de quoi
`ADR-0014` est le perimetre.

### Le trou trouve en verifiant, et il change la nature de la reponse

`ADR-0014` a ecarte le webjar dans ses **alternatives considerees**, avec un argument ecrit et juste.
Il n'a encode aucun refus. Aujourd'hui, **rien dans ce depot ne refuse un webjar** :
`RessourcesTiercesTest` derive son perimetre de `adapters/adapter-web/src/main/resources/static/`,
donc un octet servi qui n'atterrit jamais dans cet arbre lui est invisible. Deux chemins d'entree
sont ouverts et verts :

1. **un artefact Maven qui porte `META-INF/resources/`** - la forme exacte d'un webjar, et la facon
   la plus courante d'installer une bibliotheque front dans un projet Spring Boot ;
2. **un repertoire `static/` dans un autre module de ce depot** - `bootstrap` ou `adapter-content`
   serviraient alors des octets qu'aucune ligne de provenance ne trace.

C'est le meme defaut de forme que celui que ce sprint a rencontre quatre fois : une propriete
affirmee dans un texte, sans automate qui la garde. Ecrire la ligne de doctrine sans fermer ces deux
chemins produirait un troisieme document qui dit vrai et ne prouve rien.

## Decision

**1. Le perimetre d'`ADR-0014` est l'octet servi au navigateur, pas la dependance Maven.** Le point 1
d'`ADR-0014` se lit desormais ainsi : ce qui doit entrer par vendorisation, c'est le **fichier que
l'application sert**. Une dependance Maven relevant d'`ADR-0014` est une dependance qui apporte un
tel fichier - jamais une dependance en general.

**2. Une dependance de portee `test` n'est gouvernee par aucune regle d'entree de ressource.** Elle
n'entre pas dans le livrable, elle n'est servie a personne, aucun enfant ne la voit. Elle se justifie
dans le message du commit qui l'ajoute et dans la javadoc du test qui l'emploie ; elle n'exige ni
`ADR` ni `DEC`. `junit`, `assertj`, `archunit-junit5`, `jsoup` sont entres ainsi ; `nashorn-core`
releve de la meme categorie, et **le geste du sprint 10 est regulier**.

**3. Une dependance qui n'est pas de portee `test` entre dans le livrable.** Elle est jugee par les
regles de couches (`ADR-0001`, `ADR-0002`) ; et **si elle apporte un octet servi au navigateur, elle
est refusee**. Une bibliotheque front entre par vendorisation sous `static/vendor/` avec sa ligne de
provenance et son empreinte, jamais par une dependance.

**4. Un seul module de ce depot apporte des ressources servies, et c'est celui dont `ADR-0014` derive
la provenance.** Ce n'est pas une preference de rangement : la provenance d'`ADR-0014` est derivee de
l'arbre d'**un** module, donc un octet servi depuis un autre module serait servi sans empreinte. Le
jour ou un second module doit servir, c'est la derivation de la provenance qu'il faut etendre
d'abord.

**5. Ce qui n'a rien a interdire n'a pas de regle, et je le dis ici plutot que de le laisser
supposer.** Le point 2 est une **permission** : il n'interdit rien, il n'y a donc rien a encoder.
Ce qui est encode, c'est la frontiere - les points 3 et 4 - parce que c'est la seule moitie de cette
doctrine ou quelqu'un peut mal faire.

**Ce que cet ADR ne fait pas.** Il ne choisit aucune bibliotheque, ne juge aucun outil de test, et ne
dit pas si un test doit exister : c'est au developpeur et a `yanis-qa`. Il ne remplace pas
`ADR-0014`, il en dit le perimetre et encode le refus que ses alternatives avaient laisse au papier.

## Regle executable

**Cette regle n'est pas une regle ArchUnit, et je dis pourquoi.** ArchUnit juge des classes Java et
leurs dependances, lues dans du bytecode. La propriete a garder porte sur des **entrees d'archive** a
un emplacement que Spring sert : un webjar ne contient aucune classe que nous referencions, il ne
contient parfois aucune classe du tout. Aucune analyse de bytecode ne peut le voir. C'est exactement
l'observation d'`ADR-0014` point 7 - *"on peut interdire le `<script>` dans un gabarit ; on ne peut
pas empecher l'octet d'etre la et joignable"*. Le mecanisme automatique est donc un test
d'integration Failsafe, execute par `mvn verify` comme tout le reste, jamais par une etape a part.

`bootstrap/src/test/java/com/recreo/bootstrap/LeLivrableNeSertQueLesRessourcesDeSesModulesIT.java`

Il lit **le livrable lui-meme**, `target/recreo.jar`, celui que l'utilisateur lance par `java -jar`.
C'est la seule population exacte : le classpath de test porte les outils de test, qui ne sont servis
a personne, et un garde-fou qui les jugerait accuserait des artefacts qu'aucun navigateur n'atteint
jamais.

```java
    static void verifierQueSeulUnModuleDeCeDepotSert(Path livrable) throws IOException {
        Inventaire inventaire = inventaire(livrable);

        assertThat(inventaire.porteurs().stream().map(Porteur::nom).toList())
                .as("ADR-0037 : %s, et c'est le module de ce depot dont ADR-0014 lit l'arbre. Un "
                        + "artefact tiers qui en apporte sert des octets sans ligne de provenance ni "
                        + "empreinte : une bibliotheque front entre par vendorisation sous "
                        + "static/vendor/, jamais par une dependance Maven. [...]", MOTIF_DE_REFUS)
                .hasSize(1);

        Porteur porteur = inventaire.porteurs().get(0);
        assertThat(porteur.groupId())
                .as("ADR-0037 : %s sert %s ; un artefact qui ne declare pas le groupId de ce depot "
                        + "est tiers [...]", porteur.nom(), porteur.ressourcesServies())
                .isEqualTo(inventaire.groupIdDuDepot());
    }
```

**Perimetre derive de bout en bout** (`ADR-0013` obligation 1) :

- **les emplacements servis viennent de Spring**, pas d'une liste ecrite dans le test :
  `new WebProperties.Resources().getStaticLocations()`. Mesure faite, quatre valeurs :
  `classpath:/META-INF/resources/`, `classpath:/resources/`, `classpath:/static/`,
  `classpath:/public/`. Le jour ou le framework en ajoute un, le garde-fou le controle sans etre
  modifie ;
- **ce qui distingue un module de ce depot d'un artefact tiers est le `groupId` que chaque artefact
  declare dans son propre descripteur Maven** (`META-INF/maven/.../pom.properties`), compare a celui
  du livrable, lu dans le descripteur de son module de tete. Aucun nom de module, aucun nom de
  fichier, aucun `groupId` n'est ecrit dans le test ;
- **derivation totale** (obligation 2) : un emplacement servi que le garde-fou ne sait pas traduire
  en prefixe d'entree, aucun emplacement du tout, aucun descripteur sur le module de tete ou
  plusieurs, un descripteur sans `groupId`, sont des **echecs** et jamais des cas ignores. Zero
  porteur echoue au meme titre que deux : une regle qui ne trouve plus rien a juger ne prouve rien.

**Mise a l'epreuve, sur le livrable reel et non sur une fixture** (`ADR-0013` obligation 3). Deux
clauses, deux epreuves, toutes deux obtenues en **mutant l'archive produite par le build** :

| Mutation du livrable reel | Clause eprouvee | Rouge obtenu |
|---|---|---|
| un webjar ajoute sous `BOOT-INF/lib/`, portant **exactement** le nom d'entree d'une ressource reellement servie aujourd'hui - lu dans le livrable, jamais invente | un porteur de plus | "un seul artefact du livrable apporte des ressources servies au navigateur" |
| le meme webjar ajoute **et** le module qui sert retire de l'archive | le porteur unique est tiers | "un artefact qui ne declare pas le groupId de ce depot" |

La seconde mutation est le geste reel qu'on ferait en adoptant le webjar au lieu de vendoriser :
elle est la raison d'etre de la clause du `groupId`, qui sans elle serait ecrite sans preuve qu'elle
mord. La lecon de s05 est respectee : le materiau des deux epreuves est l'archive que le build vient
de produire, plus le seul geste refuse ; rien n'y est ecrit de ma main sauf ce geste.

**Etat mesure.** `mvn -B clean verify`, `JAVA_HOME` sur un JDK 21.0.10 (le `java` du `PATH` de ce
poste est un JRE 8, qui ne compile pas) : BUILD SUCCESS. **406 tests** - 403 avant ce lot, plus les
trois de ce fichier - repartis en 46 `domain`, 7 `application`, 206 `adapter-web`, 64
`adapter-content`, 77 `bootstrap` (surefire) et 6 `bootstrap` (failsafe). Methode : somme de
l'attribut `Tests run` du bloc `Results:` de chaque module dans la sortie du reacteur, apres un
`clean`, jamais une addition de fichiers. **28 regles ArchUnit, inchange** : cette regle n'en est pas
une, et le compte suit la meme methode que `rapport-qualite.md` du sprint 10 - recherche de la forme
litterale `ArchRule <nom> =` dans `src/test/java` des cinq modules declares au pom agregateur.

**Le porteur unique constate aujourd'hui** : `BOOT-INF/lib/recreo-adapter-web-0.1.0-SNAPSHOT.jar`,
`groupId` `com.recreo`, apportant `static/` - c'est-a-dire exactement l'arbre dont `ADR-0014` derive
`provenance.txt`. Aucun des 42 autres artefacts embarques n'apporte quoi que ce soit sous un
emplacement servi, et `BOOT-INF/classes/` (le module `bootstrap`) non plus.

## Ce que cette regle ne couvre pas, dit avant qu'on le decouvre

- **Elle n'existe qu'apres `package`.** Un `mvn test` seul ne la voit pas ; seul `mvn verify` la
  passe. C'est le meme regime que `JarRecreoIT` et que les deux IT de catalogue, et le `CLAUDE.md`
  fait de `mvn verify` la porte du Done technique - mais un agent qui ne lance que les tests
  unitaires croira a tort avoir tout passe.
- **Elle lit les emplacements servis par defaut, pas les emplacements configures.** Une propriete
  `spring.web.resources.static-locations` ajouterait un emplacement que la regle ne lirait pas.
  Constat du jour, mesure et non suppose : **le depot ne contient aucun fichier
  `application.properties` ni `application.yml` de production**, donc aucune valeur configuree.
- **Elle ne voit pas un octet servi par un `ResourceHandler` enregistre a la main** ni par un
  controleur qui renverrait le contenu d'un jar. Constat du jour : aucun `WebMvcConfigurer`, aucun
  `addResourceHandlers` dans le code de production. Le jour ou il y en aura un, c'est des mappings
  enregistres qu'il faudra deriver le perimetre, pas des emplacements par defaut.
- **Elle ne juge pas les octets d'une ressource servie**, seulement d'ou ils viennent. La provenance
  et l'empreinte restent a `RessourcesTiercesTest` (`ADR-0014`).
- **Elle ne dit rien d'une dependance qui entre dans le livrable sans rien servir.** Un outil de test
  passe par erreur en portee de compilation alourdirait le jar sans etre refuse ici : c'est une
  question de poids et de proprete, pas de frontiere servie, et je ne fabrique pas une regle pour
  elle avant qu'un cas reel existe.
- **Elle suppose que les modules de ce depot continuent d'emporter leur descripteur Maven.** Un
  build configure avec `addMavenDescriptor=false` la ferait passer au rouge - failli du bon cote,
  jamais un vert silencieux, mais un rouge dont le motif ne serait pas celui qu'on cherche.
- **La moitie permissive de cette doctrine n'a aucun automate, par construction** : rien n'interdit
  d'ajouter une dependance de test, donc rien ne le verifie. Ce qui reste vrai est qu'une dependance
  de test qui cesserait de l'etre et servirait quelque chose serait attrapee par la regle ci-dessus.

## Consequences

- **Le refus du webjar est enfin executable.** C'etait une alternative ecartee en prose depuis
  `ADR-0014` ; c'est maintenant un rouge. Le cout de mise a jour manuelle d'une bibliotheque
  vendorisee, assume par `ADR-0014`, devient un cout **impose** et non plus seulement recommande.
- **Ajouter une ressource servie dans un autre module coute d'abord une extension de la provenance.**
  Friction voulue : c'est le prix de l'empreinte. Elle tombera sur le premier lot qui en aura besoin,
  et il faut qu'il sache pourquoi - d'ou le point 4.
- **Le livrable est ouvert trois fois par build** par ce fichier. Mesure : 6,1 s pour les trois
  tests, sur un build de 56 s. Negligeable, mais ce n'est pas gratuit.
- **Un `ADR` de plus a lire.** Il est court et il porte une frontiere que tout ajout de dependance
  rencontre ; c'est le genre de texte qui evite une instruction de question par sprint.
- `ADR-0014` recoit un amendement de renvoi : son point 1 et sa section "alternatives" disent
  desormais ou est la regle qui les rend executables.

## Alternatives considerees

- **Amender `ADR-0014` au lieu d'ecrire un `ADR` neuf.** C'etait la forme la plus econome, et je l'ai
  ecartee pour deux raisons. La distinction porte sur **toute** dependance de ce depot, presente et
  future, alors qu'`ADR-0014` a pour objet une bibliotheque CSS et son mecanisme d'entree ; et cette
  decision apporte une regle executable neuve, avec son perimetre, ses deux clauses et ses deux
  epreuves - c'est un `ADR`, pas une phrase ajoutee dans un autre. `ADR-0014` garde son objet et
  recoit un renvoi.
- **Un `ADR` dedie a `nashorn-core`.** Ecarte, et c'est aussi la recommandation de `yanis-qa` : un cas
  isole ne merite pas un `ADR`. Une frontiere, oui. Le cas est cite ici comme declencheur et comme
  exemple range dans sa categorie, ce qui est ce qui manquait au depot.
- **Une regle sur le texte des poms** ("toute dependance tierce non `test` est refusee sauf..."),
  ecartee sur une mesure, pas sur une impression : `adapters/adapter-web/pom.xml` declare
  `archunit-junit5` **sans aucune balise `<scope>`**, sa portee `test` venant du
  `dependencyManagement` du pom agregateur. Un garde-fou qui chercherait `<scope>test</scope>` dans
  le pom du module le classerait comme dependance de compilation et produirait un faux rouge. La
  portee effective n'est pas dans le pom du module ; elle est dans ce que Maven resout, donc dans ce
  qu'il empaquete.
- **Une regle sur le classpath de test** (`classpath*:/META-INF/resources/**` via le resolveur
  Spring). Ecartee : sa population est le classpath de **test**, qui contient precisement les outils
  que cet ADR declare libres. Elle accuserait un artefact servi a personne - l'inverse exact de la
  doctrine qu'elle serait censee garder.
- **Interdire le `groupId` `org.webjars`.** Ecartee par `ADR-0013` : un perimetre enumere par nom
  echoue par omission, et une bibliotheque front publiee sous un autre `groupId` passerait sans
  bruit. Ce qui est refuse ici est **de servir un octet**, pas de porter un nom.
- **Ne rien encoder et ecrire la seule ligne de doctrine.** Ce que la demande autorisait, et ce que
  j'ai failli faire. Ecartee : ce sprint a produit quatre garde-fous verts qui n'avaient jamais
  rencontre leur objet, et un cinquieme document sans automate aurait ete la meme faute sous une
  autre forme. La ligne de doctrine sans la regle aurait laisse un webjar entrer le lendemain.

## Ce qui me ferait changer d'avis

- **Si un second module de ce depot doit legitimement servir des ressources** - un module de theme,
  un module d'images pedagogiques - la clause du porteur unique devient de la friction pure. Le
  remede n'est pas de l'affaiblir : c'est d'etendre la derivation de `provenance.txt` a l'ensemble
  des arbres de modules, puis de compter les porteurs contre cet ensemble.
- **Si un `ResourceHandler` enregistre a la main devient necessaire**, la derivation depuis les
  emplacements par defaut de Spring cesse d'etre totale, et il faut lire les mappings reellement
  enregistres dans le contexte assemble. C'est plus cher et je ne le paie pas avant qu'il existe.
- **Si un webjar devient la seule facon raisonnable de faire entrer une bibliotheque** (le cas qu'
  `ADR-0014` nomme deja : plusieurs fichiers d'une meme bibliotheque), alors c'est `ADR-0014` qu'il
  faut renverser, et cette regle qu'il faut reecrire pour exiger une provenance des octets **du
  jar** au lieu de les refuser. Ce que je refuserais dans tous les cas, c'est de la desactiver sans
  remplacer la garantie.
