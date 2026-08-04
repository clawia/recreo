# [Sprint s00] Rapport qualite - yanis-qa

- **Date** : 2026-08-04
- **Auteur** : yanis-qa
- **Perimetre** : stories S0-1 a S0-5 (`docs/backlog/backlog.md`, section Sprint 0)

## Commande et resultat

```
JAVA_HOME=<JDK 21> mvn -B verify
```

**Vert**, lancee par moi-meme trois fois (avant mes ajouts, puis deux fois apres, pour detecter une
instabilite). Resultat stable a chaque fois :

| Module | Tests | Echecs |
|---|---|---|
| `recreo-domain` | 26 | 0 |
| `recreo-application` | 6 | 0 |
| `recreo-adapter-web` | 9 | 0 |
| `recreo-adapter-content` | 4 | 0 |
| `recreo-bootstrap` (unitaires) | 33 | 0 |
| `recreo-bootstrap` (integration, Failsafe) | 2 | 0 |
| **Total** | **80** | **0** |

Avant mon passage, la suite comptait 76 tests verts (tel que constate par sofia-sm et confirme par
moi a l'identique). J'en ai ajoute 4 (detail plus bas). Aucun test instable observe sur les
executions repetees : ni un ordre different, ni un temps d'execution anormal, ni un tirage
aleatoire non seede en cause (tous les tests qui dependent du hasard le recoivent injecte a graine
fixe - `RandomSource` toujours construit avec `new Random(valeur)` ou un lambda constant dans les
tests ; seule la production, `HasardConfiguration`, utilise `new Random()` sans graine, ce qui est
correct : c'est le comportement reel attendu, pas un test).

## Ce que le vert prouve, et ce qu'il ne prouvait pas avant mon passage

`mvn verify` vert prouve que le code compile, que les regles ArchUnit ne detectent aucune violation
sur le code actuel, et que les parcours HTTP simules par `@SpringBootTest`/`@WebMvcTest`
fonctionnent. Il ne prouvait **pas**, avant les ajouts ci-dessous :

1. que l'artefact **reellement empaquete** (`bootstrap/target/recreo.jar`), lance en processus
   separe exactement comme un utilisateur le ferait, demarre et sert une page HTTP - les tests
   `@SpringBootTest` existants (`PartieHttpTest`, `CatalogueChargeableIT`) demarrent un contexte
   Spring **dans le processus de test lui-meme**, jamais le fat jar comme binaire autonome ;
2. que la regle ArchUnit de couches (`ArchitectureEnCouchesTest`, ADR-0001) echoue reellement sur
   une violation - elle n'avait aucun contre-exemple, contrairement a ce qu'ADR-0003 exige pour
   toute regle de garde ;
3. que la mecanique bandit manchot joue reellement deux contenus differents dans la meme execution,
   sans changement de code - preuve d'execution du principe directeur du produit, distincte de la
   preuve structurelle (ArchUnit) que porte theo-archi ;
4. deux tests de `FrontieresDuJavaScriptTest` (adapter-web, ADR-0008) auraient pu passer sans rien
   verifier si les fichiers qu'ils scrutent avaient disparu ou change d'emplacement (vacuite de
   type 1 au sens d'ADR-0003) ;
5. rien ne prouve, ni ne peut prouver en Java, que la CI echoue explicitement sur un commit sans
   prefixe d'agent connu : c'est un script shell dans `.github/workflows/ci.yml`. Reste non couvert,
   voir plus bas.

Les points 1 a 4 sont maintenant combles (tests ajoutes, detail plus bas). Le point 5 est remonte.

## Criteres d'acceptation, story par story

### S0-1 - Un socle applicatif qui se verifie tout seul

| Critere | Test qui le couvre |
|---|---|
| `mvn verify` se termine en code 0 (compilation, tests unitaires, integration Spring Boot, ArchUnit) | Preuve directe par execution de la commande (tableau ci-dessus), ce n'est pas un test unique. Le critere lui-meme a ete reformule en amont par `DEC-0003` ("integration Spring Boot" et non "H2"), et c'est bien ce que la suite livre. |
| L'artefact empaquete, lance via `java -jar bootstrap/target/recreo.jar`, sert une page accessible depuis un navigateur | **Etait non couvert.** Ajoute : `JarRecreoIT.leJarEmpaqueteSertUnePageHttpAccessibleDepuisUnNavigateur` (`bootstrap`, IT Failsafe). Lance le jar reellement produit par la phase `package`, en processus separe, avec le JDK de `java.home` (jamais le `java` du `PATH`, qui est un JRE 1.8 sur cette machine), interroge `GET /` et verifie via jsoup qu'un `button.demarrer` est present. |
| La CI echoue explicitement sur un commit sans prefixe d'agent connu, en citant le prefixe manquant | **Non couvert par un test automatise**, et ne peut pas l'etre en Java : c'est un script shell dans `.github/workflows/ci.yml`. Je l'ai lu et verifie manuellement (simulation du `grep -Eq "^\[(CAM\|SOF\|THEO\|MARC\|LEA\|YAN\|REL\|NAD)\] "` contre six sujets de commit types, prefixe connu/inconnu/absent/minuscule/sans espace/merge) : le comportement est correct dans les six cas. Le message d'erreur cite le sujet de commit fautif complet (pas un mot "prefixe" isole), ce qui satisfait l'esprit du critere mais reste un shell script non regressible automatiquement. **Remonte a sofia-sm** ci-dessous. |
| La suite ArchUnit echoue si une couche interne depend d'une couche exterieure ou d'un framework | `ArchitectureEnCouchesTest` (ADR-0001) porte la regle. **Le contre-exemple manquait entierement** (aucune classe ne prouvait que `lesDependancesVontVersLInterieur` ou `lesAdaptateursNeSeConnaissentPas` echouent reellement - vacuite de type 3 au sens d'ADR-0003 : la regle pouvait etre mal ecrite sans que rien ne le montre). Ajoute : deux contre-exemples (`DomaineDependantDeLApplicationContreExemple`, `AdaptateurWebDependantDuContenuContreExemple`) et deux tests dans `LesReglesMordentTest` (`leDomaineNeDoitPasDependreDeLApplication`, `deuxAdaptateursNeDoiventPasSeConnaitre`). Le premier essai a echoue pour une raison instructive (voir "Defauts reels trouves" plus bas) : `leDomaineNeDependQueDuJdk` (ADR-0002) est deja verifie separement. |

### S0-2 - Jouer une manche du bandit manchot du debut a la fin

| Critere | Test qui le couvre |
|---|---|
| Demarrer une partie sans compte ni information personnelle | `PartieControllerTest.laPageDAccueilProposeDeDemarrerSansCompteNiDonneePersonnelle` (adapter-web) + `PartieHttpTest.lAccueilPermetDeDemarrerUnePartieSansCompte` (bootstrap, contexte complet) |
| Choix correct -> retour "correct" + score +1 | `BanditManchotTest.unChoixCorrectEstJugeJusteEtLeScoreAvance` (domaine) + `PartieControllerTest.uneReponseCorrecteAfficheLeSignalJusteEtAvanceLeScore` + `PartieHttpTest.uneReponseCorrecteDonneUnRetourCorrectEtAvanceLeScore` |
| Choix incorrect -> retour "incorrect" qui designe la bonne reponse, score inchange | `BanditManchotTest.unChoixIncorrectEstJugeFauxDesigneLaBonneReponseEtNAvancePasLeScore` + `PartieControllerTest.uneReponseIncorrecteAfficheLeSignalFauxEtDesigneLaBonneReponse` + `PartieHttpTest.uneReponseIncorrecteDesigneLaBonneReponseEtNAvancePasLeScore` |
| Manche suivante -> nouvelle question, score cumule visible | `BanditManchotTest.lEnchainementDesManchesConserveLeScoreCumule` + `PartieControllerTest.laMancheSuivanteAfficheUneNouvelleQuestionEtConserveLeScoreCumule` + `PartieHttpTest.laMancheSuivanteAfficheUneNouvelleQuestionEtLeScoreCumule` |

Couvert trois fois a trois niveaux (domaine pur, `@WebMvcTest`, `@SpringBootTest` complet) : c'est
plus qu'exige, mais chaque niveau attrape une classe de regression differente (logique de session,
cablage du controleur, cablage Spring complet) ; je ne le juge pas redondant.

### S0-3 - Rendre impossible de meler mecanique et contenu

| Critere | Test qui le couvre |
|---|---|
| La regle echoue si la mecanique reference une valeur ou une classe propre au contenu additions CP (borne 10, mot "addition") | `DecouplageMecaniqueContenuTest.uneMecaniqueNeVoitQueLesContrats` (liste blanche bytecode) + `AucunVocabulaireDeMatiereTest.aucuneMecaniqueNeNommeUneMatiere` (lecture de source, attrape le litteral que le bytecode ne voit pas). Non-vacuite de la premiere regle verifiee par `LesReglesMordentTest.uneMecaniqueQuiUtiliseUnGenerateurConcretEstRefusee`. La seconde (lecture de source) n'a pas de contre-exemple binaire dedie, mais sa structure (assertion directe sur le contenu du fichier, garde `isNotEmpty()` sur la liste de sources) exclut deja la vacuite de type 1 ; je ne l'ai pas jugee a risque de vacuite de type 3 (une regex/`contains` mal ecrite s'auto-detecterait au premier paquet de vocabulaire francais). |
| Une fois le contenu injecte via l'abstraction generique, la regle passe | La suite ArchUnit verte le prouve structurellement (cote theo-archi). **La preuve d'execution manquait** : aucun test ne faisait tourner la meme instance de mecanique sur deux contenus reellement differents (tous les tests existants utilisaient un `ContentDescriptor` "Mathematiques"/"CP", meme quand le texte des questions etait arbitraire). Ajoute : `BanditManchotTest.laMemeMecaniqueJoueDeuxContenusDifferentsSansAucunChangementDeCode`, qui fait jouer `BanditManchot` sur un contenu combinatoire (mathematiques, additions bornees) puis sur un contenu enumere d'une autre matiere et d'un autre niveau (anglais CM1, vocabulaire), sans sous-classe ni configuration, et verifie que les deux sessions se comportent correctement. |

### S0-4 - S'entrainer aux petites additions en jouant

| Critere | Test qui le couvre |
|---|---|
| Addition de deux entiers entre 0 et 10, somme entre 0 et 10 | `AdditionsBorneesContentSourceTest.chaqueManchePresenteDeuxOperandesEtUneSommeEntre0Et10` (50 tirages, graine fixe) |
| Bonne reponse presente exactement une fois, au moins un distracteur plausible, jamais negatif ni non entier | `AdditionsBorneesContentSourceTest.laBonneReponseFigureExactementUneFoisParmiLesPropositions` + `.auMoinsUnDistracteurPlausibleJamaisNegatifNiNonEntier` |
| Variete sur 20 manches, pas de question figee repetee | `AdditionsBorneesContentSourceTest.lesAdditionsVarientSurVingtManchesConsecutives` (verifie `hasSizeGreaterThan(1)` distinctes sur 20 tirages a graine 42 - correspond au libelle litteral du critere, "pas toutes identiques") |
| La consigne reste en vocabulaire courant de CP ("combien ca fait ?", pas "somme") | **Non couvert par un test technique, et ce n'est pas un trou : c'est le Done pedagogique.** La formulation reelle livree est "Combien ça fait ?" (`adapter-content/src/main/resources/catalogue.yaml`). Juger si ce vocabulaire est adapte a un enfant de CP est exclu de mon mandat ("Tu ne juges pas ... la formulation de la consigne : c'est nadia-prof") ; je ne l'ai donc pas ecrit et je ne recommande pas de le faire, pour ne pas laisser croire qu'une regex de vocabulaire vaudrait la validation de Nadia. |

### S0-5 - Un retour comprehensible pour un enfant qui lit encore peu

| Critere | Test qui le couvre |
|---|---|
| Consigne en une phrase courte (moins de dix mots), vocabulaire de CP | **Non couvert par un test technique, meme partiellement.** Le sous-critere "moins de dix mots" serait mecaniquement testable, mais le critere est compose et sa partie utile (vocabulaire adapte) ne l'est pas : ecrire un test qui ne verifie que le compte de mots donnerait une fausse impression de couverture sur un critere fondamentalement pedagogique. Relève du Done pedagogique, comme le confirme l'avis de faisabilite de theo-archi lui-meme ("le fond est pedagogique"). |
| Retour juste/faux accompagne d'un signal visuel distinct du texte | `PartieControllerTest.uneReponseCorrecteAfficheLeSignalJusteEtAvanceLeScore` / `.uneReponseIncorrecteAfficheLeSignalFauxEtDesigneLaBonneReponse` + equivalents `PartieHttpTest` : verifient structurellement (jsoup, selecteur de classe) que le fragment de retour porte `.signal.est-juste` ou `.signal.est-faux` selon le verdict. **Ce qui n'est pas verifie par un test** : que `recreo.css` rend ces deux classes visuellement distinctes (forme et couleur) pour un enfant qui ne lit pas encore vite. Je l'ai relu a la main : `.est-juste` est un rond vert, `.est-faux` un triangle rouge (`clip-path`), donc forme **et** couleur distinctes, conforme a DEC-0002. Une assertion automatisee sur le contenu CSS serait fragile et de peu de valeur (elle testerait une valeur de couleur, pas une perception) ; je ne l'ai donc pas ecrite. Sans emoji dans le gabarit, verifie par ADR-0008/`FrontieresDuJavaScriptTest` (le pictogramme est en CSS pur, `::before`). |

## Tests ajoutes (4)

1. **`bootstrap/src/test/java/com/recreo/bootstrap/JarRecreoIT.java`** - lance le fat jar reellement
   produit par `package`, en processus separe, avec le JDK de `java.home` (jamais le `java` du
   `PATH`, qui est un JRE 1.8 sur cette machine) ; attend qu'il reponde sur un port libre trouve a
   l'avance ; verifie via jsoup qu'un `button.demarrer` est servi ; arrete le processus et attend sa
   sortie avant de rendre la main (sinon un `java -jar` reste actif verrouillerait `recreo.jar` sous
   Windows au prochain `mvn package`, l'ecueil deja signale dans le brief de ce sprint). Attrape :
   une regression de packaging (ressource manquante, `MANIFEST` sans classe principale, port qui ne
   se libere pas) invisible a tous les autres tests, qui restent dans le processus de test.

2. **`bootstrap/src/test/java/com/recreo/domain/contreexemple/DomaineDependantDeLApplicationContreExemple.java`**
   et **`bootstrap/src/test/java/com/recreo/adapter/web/contreexemple/AdaptateurWebDependantDuContenuContreExemple.java`**,
   plus deux tests dans `LesReglesMordentTest` (`leDomaineNeDoitPasDependreDeLApplication`,
   `deuxAdaptateursNeDoiventPasSeConnaitre`). Attrape : une regle de couches (`ArchitectureEnCouchesTest`,
   ADR-0001) mal ecrite qui resterait verte sans jamais rien interdire - exactement le mode de
   defaillance qu'ADR-0003 documente et que cette regle precise n'avait jamais eu a demontrer.

3. **`domain/src/test/java/com/recreo/domain/mechanics/banditmanchot/BanditManchotTest.laMemeMecaniqueJoueDeuxContenusDifferentsSansAucunChangementDeCode`**
   (methode ajoutee dans le fichier existant). Attrape : un decouplage mecanique/contenu qui ne
   tiendrait qu'en theorie (verifie par ArchUnit) mais casserait a l'execution - par exemple un
   `switch` cache sur la forme du contenu, ou une hypothese implicite sur la matiere qu'aucune regle
   de bytecode ne peut voir.

4. **Deux gardes de non-vacuite dans `adapters/adapter-web/src/test/java/com/recreo/adapter/web/FrontieresDuJavaScriptTest.java`**
   (`leJavaScriptDAgrementTientDansSonBudget`, `leJavaScriptNePrendAucuneDecisionDeJeu`) et une
   troisieme dans `aucunGabaritNeMarqueLaBonneReponse` : les trois methodes iteraient sur une liste
   de fichiers trouvee par `Files.walk` sans jamais verifier que cette liste n'etait pas vide.
   Si `recreo.js` disparaissait ou changeait de repertoire (renommage, refactor), ces tests
   passeraient sans rien avoir verifie - vacuite de type 1 au sens d'ADR-0003, dans un fichier qui
   cite pourtant cet ADR. Corrige par un `assertThat(...).isNotEmpty()` avant chaque boucle.

Verification faite comme l'exige ADR-0003 pour toute regle de garde nouvellement contre-exemplifiee :
le premier essai des deux tests du point 2 a echoue pour une raison differente de celle visee
(`layeredArchitecture()` echoue par defaut si une couche declaree est vide, avant meme de regarder
la dependance interdite ; importer uniquement le paquet du contre-exemple laissait "Application",
"Web", "Contenu" et "Bootstrap" vides). Corrige en important aussi les classes de production reelles
de chaque module, pour que les cinq couches soient peuplees et que la seule violation restante soit
celle du contre-exemple. Le message d'assertion a ete relu pour confirmer qu'il cite bien le nom de
la classe fautive et non un artefact du montage du test.

## Defauts reels trouves

- **`ArchitectureEnCouchesTest` (ADR-0001) n'avait aucun contre-exemple**, contrairement a ce que
  demande ADR-0003 pour toute regle de garde. C'est directement le quatrieme critere de S0-1. Corrige
  (voir ci-dessus), mais je le nomme comme defaut reel plutot que comme simple trou de couverture :
  la regle existait depuis le premier commit du sprint sans jamais avoir ete mise a l'epreuve.
- **`FrontieresDuJavaScriptTest` (ADR-0008) etait vacuite-fragile** sur ses trois methodes de test
  a base de scan de fichiers. Corrige (voir ci-dessus).
- Aucun autre defaut de comportement trouve : tous les contre-exemples existants (`DomainePollueContreExemple`,
  `MoteurPartageContreExemple`, `MecaniqueCoupleeContreExemple`, `ContenuQuiSaitJouerContreExemple`)
  ont ete relus, et j'ai confirme par lecture que chaque `hasMessageContaining` pointe bien vers le
  nom de la classe fautive et vers la dependance precise violee (pas seulement `AssertionError.class`),
  conformement a l'amendement du 2026-08-04 d'ADR-0003. Je n'ai pas retrouve de cas ou deux regles
  differentes produiraient un message ambigu.

## Trous assumes, nommes un par un

1. **Le script shell de garde-fou de commit (critere 3 de S0-1) n'a aucun test automatise.**
   Verifie manuellement (voir S0-1 ci-dessus) mais non regressible : si quelqu'un modifie
   `.github/workflows/ci.yml` demain et casse la regex, rien ne le detectera avant un vrai commit
   fautif en CI. Remonte a sofia-sm ci-dessous.
2. **Les rules de garde d'ADR-0005 (`AbstractionsCiteesTest.aucunTypeNommeRuleSet`), d'ADR-0006
   (`DifficulteTest`, ses trois regles) et d'ADR-0007 (`ChargementDuContenuTest.seulLAdaptateurDeContenuLitDesFichiers`)
   n'ont pas de contre-exemple.** Aucune de ces quatre regles ne selectionne un ensemble vide
   (verifie : leurs criteres de selection couvrent tout `com.recreo.domain..` ou tout `com.recreo..`,
   jamais un paquet etroit qui pourrait se vider silencieusement), donc le risque n'est pas la
   vacuite de type 1. Le risque est la vacuite de type 3 (une regle mal ecrite qui ne peut pas
   mordre) : je ne l'ai pas constate en lecture, mais je ne l'ai pas non plus prouve par l'echec
   d'un contre-exemple, contrairement a ce qu'exige ADR-0003 a la lettre. Je n'ai pas ajoute ces
   quatre contre-exemples moi-meme : ce sprint n'est pas centre sur ADR-0005/0006/0007 et la
   completude de l'application d'ADR-0003 rule par rule appartient a theo-archi, qui a ecrit la
   regle elle-meme. Remonte a sofia-sm/theo-archi ci-dessous.
3. **`AucunVocabulaireDeMatiereTest` et le sous-critere "vocabulaire de CP" de S0-4/S0-5 restent,
   par nature, des tests de texte et non de sens.** Un paquet qui emploierait un synonyme non liste
   ("plus" au lieu de "somme") passerait la regle actuelle sans que ce soit un defaut du test : ce
   n'est pas son role, c'est celui de Nadia.
4. **La lisibilite visuelle reelle du signal juste/faux (contraste, taille percue) n'est verifiee
   par aucun automate**, seulement par ma lecture du CSS. Un test de rendu visuel demanderait un
   navigateur pilote, hors de la stack (`CLAUDE.md` exclut tout build/outillage Node).

## Points remontes a sofia-sm

1. **Le garde-fou de prefixe de commit (critere 3 de S0-1) est un script shell non testable en
   Java et non couvert par aucun test.** Je l'ai verifie manuellement et il se comporte
   correctement aujourd'hui, mais rien n'empeche une regression silencieuse demain. Piste
   (a decider par theo-archi, pas par moi) : extraire la logique dans un script versionne
   (`.github/scripts/verifier-prefixes.sh`) invoque par le workflow, ce qui permettrait un test
   shell dedie (par exemple avec `bats` ou un simple script d'assertions) execute en CI au meme
   titre que `mvn verify`. Je ne l'ai pas fait moi-meme : modifier la structure du workflow est une
   decision d'outillage transverse, pas un ajout de test dans un module.
2. **Quatre regles ArchUnit de garde (ADR-0005, ADR-0006 x3, ADR-0007) n'ont pas de contre-exemple**,
   alors qu'ADR-0003 les vise explicitement ("toute regle qui garde une decision d'ADR"). Aucune
   n'est vide de selection aujourd'hui, mais aucune n'a non plus ete prouvee capable d'echouer.
   A trancher par theo-archi : soit il ecrit les quatre contre-exemples manquants (cout : quatre
   petites classes de plus dans `bootstrap/src/test`, meme schema que celles deja en place), soit il
   documente explicitement pourquoi ces quatre-la sont jugees a moindre risque et n'en ont pas besoin.
   Je n'ai pas tranche a sa place.

## Ce qui est couvert, en une phrase par story

- **S0-1** : le socle compile, se teste, s'empaquete, et l'artefact reellement lance sert une page -
  le seul point technique restant sans automatisation est le garde-fou de commit lui-meme.
- **S0-2** : le parcours de jeu complet est verifie a trois niveaux (domaine, controleur, bout en
  bout HTTP), y compris que la bonne reponse ne fuite jamais avant le coup de l'enfant.
- **S0-3** : le decouplage est verifie structurellement (ArchUnit) et desormais aussi a l'execution
  (une meme mecanique joue deux contenus reellement differents sans changement de code).
- **S0-4** : les quatre criteres numeriques du contenu additions CP sont verifies de facon
  deterministe (graine fixe) sur des dizaines de tirages ; le seul point non teste (le vocabulaire
  de la consigne) est intentionnellement laisse au Done pedagogique.
- **S0-5** : le mecanisme du signal visuel est verifie structurellement ; sa suffisance perceptive
  et la formulation de la consigne restent, a raison, hors de portee d'un test automatise.
