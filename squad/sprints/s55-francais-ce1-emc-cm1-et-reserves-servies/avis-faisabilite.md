# [Sprint s55] Avis de faisabilite - theo-archi

- **Date** : 2026-10-08 15:42 (UTC+02:00), lu par `date`.
- **Auteur** : theo-archi. Branche `s55-theo-faisabilite`, worktree dedie, base `0a795589` (tip de `round-two`), `git status --short` vide
  avant ecriture. Le planning nommait `s55-theo-avis` ; la branche a ete ouverte sous ce nom-ci par l'agent qui m'a lance.
- **Perimetre** : T1 a T7 du planning ; la story `S55-ART-CP-10-MECANIQUE` de `DEC-0115` section 5 et ses criteres (backlog, "Sprint 55") ; la question T4
  d'`ADR-0104` que `DEC-0115` 5, point 4 me route ; les points 2 a 5 d'`ADR-0104` ; l'ordre de fusion. Ce commit n'ajoute que ce fichier : aucun `ADR`, aucun Java, aucun YAML.
- **JDK des mesures** : **21.0.10**, propriete `java.version` des rapports `TEST-*.xml` de Surefire (`ADR-0071`). Enveloppe `bash outils/mvn-jdk21.sh`.
  Toute mesure partielle ci-dessous porte `-am` et nomme son perimetre.

## 0. Constat bloquant, hors de toute story : `round-two` est rouge

**`AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` echoue sur l'arbre propre**, 2 tests sur 5 (`leDepotReelNAffichePasMoinsQuIlNEcrit`,
`laDetteNommeExactementLesLignesEnSurplusQuiRestent`). Commande, sur ce worktree avant ecriture de ce fichier :
`bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest -Dsurefire.failIfNoSpecifiedTests=false`
(perimetre : cette seule classe ; `-am` construit les quatre modules amont), 15:33:52 a 15:34:54, `BUILD FAILURE`.

- **Cause** : `squad/sprints/s54-branchement-des-premiers-paquets-et-reports-de-s53/review.md` ligne 293, ligne `U4` du tableau de la section 14 : 4 cellules pour un en-tete de 3 colonnes (`ADR-0087`).
- **Origine** : `git log -L293,293` sur ce fichier, puis le compte des cellules de la ligne `U4` a chaque commit : 3 a `23b55b60^`, 4 a `23b55b60` (`[CAM] s54`, 2026-10-08 14:20), 4 a `fbe07d79` et a `0a795589`.
- **Ce que cela contredit** : `DEC-0115` 0.1 point 1 relaie *"CI verte sur fbe07d79"*. Le job `build` lance `mvn verify`, qui lance cette classe ; elle est rouge sur `fbe07d79` par mesure locale.
  Je n'ai pas lu la CI distante ; je dis que la mesure locale du meme arbre la contredit.
- **Effet sur s55** : le critere C4 (porte) et le critere 9 de `S55-ART-CP-10-MECANIQUE` sont rouges pour toute story tant que la ligne n'est pas corrigee. Mesure : les trois simulations de la section 1 portent ces 2 echecs, sans lien avec leur diff.
- **Correction** : une ligne (fusionner la quatrieme cellule dans la troisieme, ou ajouter une colonne a l'en-tete). **Poste absent du planning : demande d'amendement A-1 a `sofia-sm`** (section 9).

## 1. Methode : trois simulations sur l'arbre, pas une lecture

Deux worktrees jetables ouverts en `--detach` sur `0a795589`, voisins du checkout, supprimes apres mesure (`git worktree list` ne les rend plus). Les mots que j'y ai ecrits
(identifiants, titres, consignes, textes de correction) sont de ma main et ne sont pas des propositions de contenu.

| Simulation | Diff | Commande | Resultat |
|---|---|---|---|
| A, passage 1 | 4 entrees de `catalogue.yaml` (24 lignes) ; pour `ce1-classes-de-mots`, la consigne existante de `cp-nature-mot` (espace ordinaire avant `?`) | `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,bootstrap -am test -fae`, 15:28:39 a 15:30:52 | `adapter-content` 1360 tests, 1 echec : `AucunSigneDoubleNEstDetachableDeSonMotTest.leCliquetDeLaDetteDeTyp1EstExact` (*"catalogue.yaml porte 24 occurrences, la table en declare 23"*) ; `bootstrap` non lance |
| A, passage 2 | idem, consigne neuve pour `ce1-classes-de-mots`, U+00A0 avant `?` | meme commande, 15:32:12 a la fin | domain 120, application 17, adapter-web 401, adapter-content 1360 : 0 echec. `bootstrap` 312 tests, 5 echecs : `TexteAccentueExposeParLePortEntrantTest` 3 sur 5, et les 2 de la section 0 |
| B | `ADR-0104` T4 seule : 3 JPEG restaures de `1db6348f^`, 30 lignes de registre (une ligne vide, puis les lignes 107 a 135 de `1db6348f^`), constantes 8 vers 11 et 176 474 vers 250 915 | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -fae`, 15:28:41 a 15:32:45 | quatre modules amont verts (1360 pour adapter-content) ; `bootstrap` 312, 4 echecs : `LIllustrationDeContenuResoutEtAucuneNeDortTest` 2 sur 18, et les 2 de la section 0 |
| C | `S54-EXC-CATEGORIE` (les quatre lignes de `nadia-prof`) et `S54-ART-RES-R1` a `R3` (consigne, indice de l'item 11, `"un peintre"` vers `"une peintre"` sur 3 lignes) | `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,bootstrap -am test -fae -Dmaven.test.failure.ignore=true`, a partir de 15:37:29 | `adapter-content` 1360, 1 echec : `PaquetCpEmcExclusionTest.fab1aLaTacheDeClassementSertSesDeuxCategoriesSansReponseInvariante` ; `bootstrap` 312, 6 echecs : `TexteAccentueExposeParLePortEntrantTest` 4 sur 5, et les 2 de la section 0 |

Comptes : lignes `Tests run` de synthese de chaque module dans la sortie Maven. Phase `test` seule : Failsafe n'est pas lance. Total d'une simulation : 2210 en phase `test`
(120 + 17 + 401 + 1360 + 312) ; le 2216 de s54 est un `verify`, Failsafe compris (6 tests dans l'avis de s54) ; 2210 + 6 = 2216 est coherent, Failsafe n'est pas mesure ici. Je n'annonce aucun total de cloture.

## 2. Reponses T1 a T7 du planning

**T1 - le test du paquet reste-t-il du ?** **Oui, pour les quatre**, par le motif de s54 : chaque paquet porte des conditions comptables qu'aucun garde-fou generique ne compte.
`ce1-accord-sujet-verbe.yaml` lignes 4 a 6 (`ACV-CE1-1` : sujet nominal dans 17 items sur 24, 12 pluriels, 12 singuliers) ; `ce1-classes-de-mots.yaml` lignes 4 a 6 (`CLM-CE1-1` : 5, 4, 5, 5, 5 par classe) ;
`cm1-emc-respect-differences.yaml` ligne 28 et `cm1-emc-principes-republique.yaml` ligne 30 (`FER-1` : chaque terme en bonne reponse). Preuve qu'aucun test ne les compte : `git grep -l '"<paquet>"' -- '*.java'`
rend pour chacun des quatre le seul `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`. Pas de regle "un test par paquet" (decorative, T1 de s54).

**T2 - un test de contenu sur un motif precis de refus est-il du (A3, C8) ?** **Oui quand le motif est une propriete decidable d'un item nomme ; non quand il est un jugement.**
Ce que le test interdit reellement : le retour du texte refuse lors d'une reecriture, exactement la mutation de `yanis-qa` a s54 (*"le printemps"* remis a l'item 11, verte sur 1360 + 312 tests,
`rapport-qualite.md` de s54 section 5). Une assertion negative sur l'item nomme la rend rouge ; ce n'est pas decoratif. Ce qu'il ne garde pas : un synonyme du texte refuse (*"au printemps"*), qui reste au jugement de `nadia-prof`.
Pour s55 :

1. `cm1-emc-principes-republique`, motif P3 (`ARBITRAGE-PREALABLE-s53-points-de-relecture-p1-a-p11.md` section 3 : *"la liberte" sort* des distracteurs de l'item 4, ligne 83 du paquet) : **test du**, dans `PaquetCm1EmcPrincipesRepubliqueTest`, 10 a 20 lignes, a l'interieur du poste `S55-BR-EMC-CM1-06` (aucun poste neuf).
2. Meme paquet, motif P4 (un indice ne redit pas le contenu de la bonne reponse, section 4) : **jugement, non garde** ; ligne "non garde : P4" du rapport de `lea-dev` (C8).
3. `ce1-accord-sujet-verbe`, `ce1-classes-de-mots`, `cm1-emc-respect-differences` : aucun motif de refus ecrit (jamais audites ; P9 accepte sans changement) ; rien a garder.
4. Les motifs de sciences d'A3 (`cm1-sciences-terre-jour-nuit-saisons` items 4 et 11, `cm1-sciences-environnement-activite-humaine` item 11) : aucun paquet de sciences n'est touche a s55. La regle ci-dessus s'applique au prochain poste qui touche ces paquets ; je n'ouvre pas de poste pour eux.

Aucune regle generique ni `ArchRule` : chaque motif est son propre texte, et un YAML n'est pas un type Java.

**T3 - listes que les branchements modifient encore.** **Une seule, l'inventaire de `TexteAccentueExposeParLePortEntrantTest`** : 3 chaines par entree (identifiant, titre, competence),
4 si la consigne est neuve (simulation A, passage 2). `LeRang...` declare deja les quatre paquets (T1). Deux pieges mesures :

- **`ce1-classes-de-mots` demande une consigne neuve qui porte une question** (en-tete du paquet, lignes 11 et 12 : la question est portee par la consigne). Une espace ordinaire avant `?` fait monter la dette
  `TYP-1` de `catalogue.yaml` de 23 a 24 et rougit le cliquet exact (simulation A, passage 1). Le caractere est U+00A0 (`ADR-0067`).
- `partDesignee` (`ce1-classes-de-mots`, 24 items) est deja servie par `cp-nature-mot` et `ce1-valeur-lettre` (`catalogue.yaml` lignes 103 et 745) : les tests de rendu de `bootstrap` passent avec l'entree (passage 2, aucun echec hors inventaire et section 0).

L'inventaire n'a pas ete complete dans la simulation : je deduis du precedent de s54 (passage 2 de son avis, vert) qu'il suffit, je ne l'ai pas rejoue.

**T4 - forme de `S54-ASCII-APOSTROPHES-TESTS`, et agent.** Forme : l'echappement d'expression reguliere, `"\\bt['\\u2019]"` au lieu du caractere litteral, sur
`PaquetCm1EmcAtteinteAutruiTest.java:57` et `PaquetCm1SciencesActiviteHumaineTest.java:65`. Precedent du depot, meme forme : `BLANC = "[\\s\\u00A0\\u202F]"` (`ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest.java:91`, et trois autres fichiers).
Taille : 2 fichiers, 1 ligne chacun ; `grep -cP '[\x{2018}\x{2019}]'` doit rendre 0 sur les deux. **Non mesure** : le passage des deux tests apres l'edit. **Agent : `marc-dev`**, auteur de la ligne 65 (`9afa50e4`), dont la file (3 postes) est plus courte que celle de `lea-dev` (4) ;
un commit separe dans son worktree, apres ses branchements. L'engagement est a `camille-po` (`DEC-0115` section 4 : *"engageable par amendement des que `theo-archi` a ecrit la forme et designe l'agent"*). Pas de garde-fou propose : deux occurrences en deux sprints, trouvees par la relecture.

**T5 - deux instances de francais CE1 et `ADR-0065`.** **Sans objet a s55** : Q6 n'est pas engagee (`DEC-0115` section 2). Si elle l'est un jour : un amendement d'`ADR-0065` et de
`AucunMotServiNeSEcarteDeSonAccentTest` est une story de `theo-archi`, une a la fois, fusionnee avant tout lot qui en depend ; le lot de la seconde instance attend. Ce n'est pas encodable en `ArchRule` (l'objet est un `ADR` et une liste de test) ;
le seul automate est le conflit de fusion sur ce fichier de test, qui signale la collision sans l'empecher.

**T6 - prix de la mecanique d'`ART-CP-10` sur l'arbre de s55.** **Reconfirme, avec une correction** : section 3.

**T7 - A7 et A8.**

- **A7** (`outils/mvn-jdk21.sh`, `JAVA_TOOL_OPTIONS`). **Reproduit** : `JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8 bash outils/mvn-jdk21.sh -v` rend le code 1 et *"ce JDK rapporte inconnue"* ; sans la variable, Maven se lance sur 21.0.10.
  Cause : ligne 80, `java -version 2>&1 | head -n1` lit la ligne *"Picked up JAVA_TOOL_OPTIONS"*. **Forme** : remplacer `head -n1` par `grep -m1 'version "'`. Mesure sur l'arbre jetable B : avec la variable, code 0 et `Java version: 21.0.10` ;
  `bash outils/mvn-jdk21-test.sh` code 0. **Prix** : production 1 ligne ; test 1 cas neuf dans `outils/mvn-jdk21-test.sh` (un `fabriquer_jdk` qui imprime la ligne *"Picked up"* avant la version), 15 a 25 lignes. CI inchangee : le job `outils` lance deja `outils/*-test.sh`.
- **A8** (`outils/rapport-tokens.py` sans `--sprint`). **Forme : refuser.** Sans `--sprint`, `main` (lignes 495 a 504) recalcule chaque fenetre ; avec `--sprint`, mesure sur l'arbre jetable B : `python outils/rapport-tokens.py --sprint s54` modifie
  `JOURNAL-tokens.md` (sections s54 et s55 seulement) et le `RAPPORT-tokens.md` de s54, rien d'autre (`git status --short`). Le refus suffit donc. **Prix** : `main`, 5 a 8 lignes (code 2 et un message qui nomme `--sprint`) ;
  `test_6_un_lancement_sans_sprint_ne_modifie_aucun_rapport_chiffre` (`outils/rapport-tokens-journal-test.py:131`) se reecrit en "un lancement sans `--sprint` est refuse et n'ecrit rien", 15 a 30 lignes. Ecartee : "ne plus recalculer les anciens", plus de lignes, et le cas que `test_6` couvrait deja a ete pris en defaut par le rejeu de `yanis-qa` a s54.
  Ni A7 ni A8 n'appelle une `ArchRule` (shell et Python, hors bytecode).

## 3. La mecanique d'`ART-CP-10` : prix reconfirme, criteres de la story

**Le prix tient sur `0a795589`.** `git diff --stat db69bbc6 0a795589` sur `adapters/adapter-web/src/main`, `PartieControllerTest`, `RessourcesTiercesTest`, `LIllustrationDeContenuResoutEtAucuneNeDortTest`,
`LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest`, `ChaqueReproductionServie...Test` et le registre : **vide**. Les longueurs servant de base (834, 205, 485, 891, 1 046 lignes) sont inchangees ; `static/oeuvres/` pese toujours 176 474 octets, 8 fichiers.

**Une correction a mon prix de s54, garde-fou 2.** J'ecrivais *"4 tests (3 clauses, population non vide)"*. Sans paquet a reproductions, la population reelle est **vide** : une clause "population non vide" serait rouge a s55.
La forme due est celle d'`ADR-0051` deja pratiquee par `LIllustrationDeContenuResoutEtAucuneNeDortTest` (lignes 586 a 592) : **taille exacte, 0 a s55**, qui rougit le jour ou le premier paquet entre et oblige ce commit a relever le chiffre. Meme nombre de tests, memes tailles.
Le garde-fou 4 est constructible sans paquet : precedent `LIllustrationDUnItemServiAtteintLEcranDeMancheTest.java:90-91` (`@WebMvcTest` sur `PartieController` et un catalogue de test construit depuis un YAML reel du depot).

**Prix, inchange** : production 2 fichiers d'`adapter-web`, 20 a 38 lignes ; tests 3 fichiers neufs (dont un YAML de contre-exemple), 5 modifies, 15 a 18 tests, 680 a 1 400 lignes ; zero ligne dans `domain`, `application`,
le chargeur, `bootstrap` main, les POM et la CI. **Sans T4** (section 4) : ni images, ni registre, ni les deux constantes.

### Les criteres 1 a 9, testables et tenables tels qu'ecrits ?

| Critere | Testable | Tenable a s55 | Lecture |
|---|---|---|---|
| 1 image, pas texte | oui | oui | `fragments/manche.html` ligne 166 porte `th:text="${proposition}"`, 1 occurrence ; la forme `'/oeuvres/' + ${proposition}` est celle d'`ADR-0104` decision 4 |
| 2 aucun identifiant en clair | oui | **non sans les mots** | les lignes citees sont justes (`PartieController.java` 686 et 746, `retour.html` 98). "Chaque bouton a un nom accessible non vide" exige un texte, que seule `nadia-prof` ecrit (critere 6) |
| 3 jeux en texte inchanges | oui (borne) | oui | 42 `@Test` dans `PartieControllerTest`, mesure |
| 4 garde-fous 1 a 4 mordent | oui | oui, avec la correction ci-dessus | morsure 3 : `oeuvre-champ-de-ble` n'est pas parmi les 8 oeuvres servies. Sur les 8, aucun identifiant ne rougit le garde-fou d'accent (`ADR-0104` decision 6 : seuls champ-de-ble et jeune-femme-aiguiere rougissent, tous deux retires). La morsure se fait en memoire sur cette chaine, sans fichier ; c'est la seule preuve que l'exemption a un effet |
| 5 cliquet 3 vers 4 exact | oui | oui | `RessourcesTiercesTest.java` lignes 709 et 725, `hasSize(3)`, mesure |
| 6 mots et mise en page avant le rendu | oui | **douteux** | voir ci-dessous |
| 7 perimetre de code | oui | oui | 39 `static final ArchRule` et 36 `@ArchTest`, methode d'`ADR-0104` remesuree sur `0a795589` ; inchange si aucune regle n'est ajoutee (section 6) |
| 8 ni catalogue, ni matrice, ni paquet | oui, **apres precision** | oui | le contre-exemple du garde-fou 1 vit sous `bootstrap/src/test/resources/contreexemple/.../contenu/` : un `grep contenu/` naif le compte. Le chemin vise est `adapters/adapter-content/src/main/resources/contenu/` |
| 9 porte | oui | **non aujourd'hui** | section 0 |

**Critere 6, le seul qui pose une question de fond.** `nadia-prof` a ecrit le 2026-10-06 que les points 2 a 5 d'`ADR-0104` (*"valeur de la forme pour ART-CP-10, nom accessible, ecran de fin, paires et distracteurs"*)
*"se tranchent sur un paquet, en jouant"* (`ARBITRAGE-PREALABLE-s52-fra-ce1-questions-de-l-avis.md`, fin de la section 4.2). `DEC-0115` finance le dispositif **sans paquet**. Les deux textes ensemble disent que T3 ne peut pas se terminer a s55,
sauf si elle accepte d'ecrire les points 3 et 4 sans paquet. **T2 (garde-fous 1 a 3) ne depend d'aucun mot** et se livre seul.

**Questions a `camille-po`, proprietaire de la story** (je ne corrige pas ses criteres) :

- **QC1** : la story se decoupe-t-elle en T2 a s55 et T3 au sprint ou `nadia-prof` ecrit les points 3 et 4 (avec ou sans paquet, a elle) ? Sinon, le critere 6 reste rouge a la cloture et la story compte comme un report.
- **QC2** : critere 8, remplacer "un fichier de `contenu/`" par "un fichier de `adapters/adapter-content/src/main/resources/contenu/`".
- **QC3** : critere 4 (2), ajouter "la population reelle vaut exactement 0, et le commit du premier paquet a reproductions releve ce nombre" ; sans cette phrase, un dev qui suit mon prix de s54 ecrit "non vide" et rougit.

## 4. T4 d'`ADR-0104` sans paquet : rouge, et elle voyage avec le fichier du paquet

**Mesure (simulation B)** : les trois reproductions restaurees, registre et constantes releves, **sans aucun item qui les declare**. Resultat :

- `ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest` : **vert** a 11 fichiers et 250 915 octets (le registre et les fichiers se repondent) ;
- `LIllustrationDeContenuResoutEtAucuneNeDortTest` : **rouge**, 2 tests (`chaqueIllustrationDeclareeParUnPaquetServiResoutEtAucunFichierNeDort`, `unIdentifiantDOeuvreResoutDesQueLeFichierDeReproductionExiste`), message *"oeuvre-averse-sur-le-pont.jpg : aucun item d'aucun paquet servi ne le declare"*, et de meme pour les deux autres ;
- rien d'autre ne bouge (`RessourcesTiercesTest`, `adapter-web` 401 tests verts).

**La lecture de `DEC-0115` 5 point 4 est juste**, a une precision pres : la clause lit les declarations dans **les fichiers de `contenu/`, jamais dans le catalogue**
(`LIllustrationDeContenuResoutEtAucuneNeDortTest.java` lignes 37 a 41 et 534 a 537). Ce qui la rend verte est donc **le fichier YAML du paquet**, pas son entree de catalogue. Et le garde-fou 1 de T2 n'y change rien :
il fait compter une proposition comme declaration, mais sans item, il n'y a ni illustration ni proposition.

**Avis** : **T4 sort de `S55-ART-CP-10-MECANIQUE` et va dans le commit qui ajoute le fichier du paquet a reproductions (T5).** Aucun critere de la story n'en depend : ses criteres 1 et 4 utilisent les 8 oeuvres servies ou des chaines en memoire.
Les paires de `nadia-prof` (point 5) decident de toute facon des fichiers a restaurer.

## 5. Points 2 a 5 d'`ADR-0104` : ce que chaque reponse coute, en tailles

Methode : lignes des fichiers concernes par `wc -l` sur `0a795589`, tailles d'ajout sur le precedent d'`ADR-0082` (`90c36ab0`) ; ce sont des estimations sur precedent, pas des mesures. Le prix d'arbitrage est a `nadia-prof`.

| Point | Reponse | Cout de developpement | Cout si la reponse vient apres le code |
|---|---|---|---|
| 2, la forme sert-elle `ART-CP-10` | oui | 0 | - |
| 2 | non | le dispositif n'a plus de client : `ADR-0104` passe `Remplace par`, et T2 et T3 livres sont du code mort a retirer | tout le prix de la section 3, en retrait |
| 3, nom accessible et phrase de revelation | des mots | 2 textes de gabarit dans `manche.html`, 2 a 6 lignes ; le garde-fou 4 exige deja un nom non vide | une ligne de gabarit par mot change |
| 4, l'ecran de fin revoit-il l'oeuvre | non | 0 : `reponseAttendueVisible` faux, deja dans T3 | - |
| 4 | oui | une image dans `retour.html` (3 a 6 lignes), le controleur pose l'identifiant (1 a 3), cliquet de `RessourcesTiercesTest` 4 vers 5, garde-fou 4 etendu a cette image (1 test, 30 a 60 lignes) | idem, plus la reecriture du test de fin |
| 5, deux ou trois propositions | l'un ou l'autre | 0 (`AuPlusTroisPropositionsParItemTest` borne deja) | 0 |
| 5, les paires | les trois historiques | T4 tel que mesure : 3 JPEG (74 441 octets), 30 lignes de registre, 2 constantes | - |
| 5 | parmi les 8 servies | T4 vaut 0 fichier | - |
| 5 | oeuvres neuves | par oeuvre : 1 JPEG d'au plus 352 px, environ 10 lignes de registre, la double preuve d'`ADR-0082`, les 2 constantes remesurees | - |
| 5, la propriete se voit-elle a 176 px CSS | non | non chiffre : amendement d'`ADR-0082` (plafond de 352 px), fichiers plus lourds, cliquet de poids (`ADR-0104`, "Ce qui me ferait changer d'avis", point 4) | - |

**Ce que le tableau dit sur l'ordre** : le point 2 conditionne tout le prix ; les points 3 et 4 conditionnent T3 ; le point 5 conditionne T4 seul. Coder T3 avant les points 2 a 4 expose a la colonne de droite.

## 6. Regle ArchUnit : aucune neuve, et pourquoi

**Aucune `ArchRule` n'est ajoutee par ce geste.** Ce que s55 construit vit dans des YAML, des images, du HTML rendu et une branche de controleur ; le seul acte Java que ce chantier tente est la lecture du prefixe d'une
proposition dans une mecanique, et la regle (13) d'`ADR-0104` (`DecouplageMecaniqueContenuTest`) l'interdit deja. Mesure sur ce worktree, sans aucun diff de code :
`bash outils/mvn-jdk21.sh -B -pl bootstrap -am test '-Dtest=com/recreo/archi/**/*' -Dsurefire.failIfNoSpecifiedTests=false` (perimetre : paquetage `com.recreo.archi`, `-am` construisant les quatre modules amont),
15:45:56 a 15:46:40, avec ce fichier dans l'arbre : `DecouplageMecaniqueContenuTest` 13 tests, `LesReglesMordentTest` 38 (1 + 37), 0 echec ; paquetage 229 tests, 2 echecs, ceux de la section 0 et eux seuls
(le seul fichier nomme par l'echec est `review.md:293` de s54 ; `ToutesLesReferencesDeDecisionResolventTest` vert, 3 tests). JDK 21.0.10.

**Ce qui n'est pas encodable en ArchUnit, nomme** : la decision 3 d'`ADR-0104` (*"c'est le seul endroit de l'adaptateur ou le prefixe d'une proposition est lu"*). Le prefixe est un litteral de chaine, qu'ArchUnit ne voit pas.
Le cote gabarit se garde par une clause du garde-fou 4, deja chiffre : `manche.html` ne contient aucun `#strings.startsWith(proposition` (5 a 10 lignes, aucun poste neuf). C'est la tentation exacte qu'`ADR-0104` decision 3 decrit.

## 7. Les deux corrections de jeux servis : leurs criteres ne tiennent pas tels qu'ecrits

Mesure : simulation C. **Ce sont des constats de forme, que `nadia-prof` a elle-meme routes vers moi** (backlog, `S54-EXC-CATEGORIE` : *"Symptome technique nomme par `nadia-prof`, forme a `theo-archi`"*).

1. **`S54-EXC-CATEGORIE`.** Le critere dit *"`git diff` : trois lignes du paquet, une du catalogue. `PaquetCpEmcExclusionTest` reste vert"*. **Les deux ne tiennent pas ensemble.** Avec les quatre lignes seules :
   - `PaquetCpEmcExclusionTest.fab1a...` est rouge : la categorie *"une moquerie qui se répète"* tombe a 1 bonne reponse, sous le minimum de 3 (`MINIMUM_PAR_TACHE`, ligne 77 du test) ;
   - `CLASSEMENTS_VALIDES` (ligne 82) ne connait pas *"une mise à l'écart qui se répète"* : les items 1 et 3 basculent en silence dans la tache B, qui est le complement. **Aucun test ne rougit pour cela** : c'est un faux vert ;
   - l'inventaire de `TexteAccentueExposeParLePortEntrantTest` porte l'ancienne consigne (ligne 870) : rouge dans les deux sens (chaine neuve non inventoriee, chaine inventoriee qui n'est plus exposee).

   Forme : 1 ligne de `CLASSEMENTS_VALIDES`, 1 ligne d'inventaire. **Le seuil de `FAB-1a` est pedagogique** : lu sur deux categories (moquerie ou mise a l'ecart, contre accident) il tient a 3 ; lu sur trois, la moquerie a 1 item. **Question a `nadia-prof`, par `camille-po`** (QC4). Le diff reel est donc au moins 6 lignes en 4 fichiers.
2. **`S54-ART-RES-R1` a `R3`.** La consigne changee est inventoriee (ligne 896) : 1 ligne d'inventaire de plus, ou une ligne retiree si la consigne reprend une chaine deja inventoriee. Le reste tient :
   `PaquetCpArtsOeuvresFamillesTest` et les garde-fous de population restent verts avec l'indice et *"une peintre"* (adapter-content : le seul echec de la simulation C est celui du point 1). 3 fichiers, 6 a 9 lignes : tenu, inventaire compris.

## 8. Ordre de fusion et isolation

**Fichiers touches, par poste** (simulations A et C, et le prix de la section 3) :

| Poste | Fichiers | En commun avec |
|---|---|---|
| A-1 (section 0) | `review.md` de s54 | aucun |
| `S54-EXC-CATEGORIE` (marc-dev) | `cp-emc-exclusion.yaml`, `catalogue.yaml` l. 395, inventaire l. 870, `PaquetCpEmcExclusionTest` | catalogue, inventaire |
| `S55-BR-FRA-CE1-L1`, `-L2` (marc-dev) | `catalogue.yaml` (12 lignes), inventaire (7 chaines), 2 tests de paquet neufs | catalogue, inventaire |
| `S54-ART-RES-R1` a `R3` (lea-dev) | `catalogue.yaml` l. 852, `cp-arts-oeuvres-familles.yaml`, inventaire l. 896 | catalogue, inventaire |
| `S55-BR-EMC-CM1-03`, `-06` (lea-dev) | `catalogue.yaml` (12 lignes), inventaire (6 chaines), 2 tests de paquet neufs | catalogue, inventaire |
| `S54-PONCT-GN-INDICES` (lea-dev) | `cm1-grands-nombres.yaml`, `AucunSigneDoubleNEstDetachableDeSonMotTest` | aucun |
| `S55-ART-CP-10-MECANIQUE` (non affecte) | `PartieController.java`, `manche.html`, `PartieControllerTest`, `RessourcesTiercesTest`, `LIllustrationDeContenuResoutEtAucuneNeDortTest` et son contre-exemple, `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest`, 2 tests neufs | **aucun** |

**La mecanique ne partage aucun fichier avec les branchements et les reserves**, ni le catalogue, ni le registre, ni l'inventaire, des que T4 en sort (section 4). Son ordre de fusion est donc libre ; elle se fusionne apres les deux corrections de jeux servis,
par la priorite pedagogique du planning et non par une contrainte technique. Sa seule dependance est d'amont : T3 attend les mots de `nadia-prof` et la mise en page d'`ines-design` (critere 6). Son garde-fou 1 lit tout `contenu/` : aucun branchement de s55 n'ajoute d'illustration, la simulation A le confirme.

**Ordre recommande** : A-1 d'abord (sans elle, chaque `clean verify` de fusion est rouge pour une raison etrangere) ; puis les decisions ; puis `S54-EXC-CATEGORIE` et `S54-ART-RES-R1` a `R3` ; puis marc-dev, puis lea-dev ; la mecanique quand elle est prete.
`bash outils/mvn-jdk21.sh -B clean verify` sur chaque commit de fusion.

**Ancres, pour que les deux couloirs n'ecrivent jamais au meme endroit** (lignes a `0a795589`) :

- `catalogue.yaml` : marc-dev ajoute apres l'entree `paquet: ce1-passe-compose` (793-794) et corrige la ligne 395 ; lea-dev ajoute apres `paquet: cm1-emc-atteinte-autrui` (847-848) et corrige la ligne 852, dans le meme couloir. Ecart entre couloirs : 54 lignes.
- Inventaire : marc-dev apres `"bandit-manchot-passe-compose-ce1"` (423), `"Les verbes au passé composé"` (708), la competence de `ce1-passe-compose` (1628), et sa consigne neuve pres de la ligne 870 qu'il corrige ;
  lea-dev apres `"bandit-manchot-atteinte-autrui-cm1"` (360), `"Ce qui blesse les autres"` (665), la competence de `cm1-emc-atteinte-autrui` (1540), et la ligne 896. Ecarts : 63, 43, 88 et 26 lignes.

Le conflit textuel de deux ajouts en fin de `catalogue.yaml` est mesure a s54 (section 3 de son avis) ; celui de l'inventaire est deduit, non rejoue.

## 9. Demandes d'amendement a `sofia-sm` (postes absents du planning)

- **A-1** : correction de la ligne 293 du `review.md` de s54 (section 0). Proprietaire a designer par elle ; une ligne ; premiere fusion de s55.
- **A-2** : `S55-ART-CP-10-MECANIQUE` n'a ni proprietaire, ni branche, ni worktree (`DEC-0115` 5.4). Si QC1 decoupe la story, le planning porte T2 seul a s55.
- **A-3** : `S54-ASCII-APOSTROPHES-TESTS` dans la file de `marc-dev`, si `camille-po` l'engage (T4).

Questions routees a `camille-po` : QC1 a QC3 (section 3), QC4 (section 7, seuil de `FAB-1a`, a `nadia-prof`), et la correction des criteres de `S54-EXC-CATEGORIE` (section 7, point 1).

## 10. Jobs de CI lances sur ce commit

| Job | Commande | Resultat |
|---|---|---|
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh'` et ce fichier en entree de `bash .github/scripts/verifier-chemins.sh` | code 0 et code 0 |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh` et `bash .github/scripts/commit-atteignable-test.sh`, puis le sujet de ce commit, ecrit avant le commit, en entree de `bash .github/scripts/verifier-prefixes.sh` | codes 0, 0 et 0 ; le sujet est relu apres le commit dans le compte rendu de la tache |
| `build` | **non lance en entier sur cet arbre** : rouge connu (section 0). Lance : la commande ArchUnit de la section 6, et `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test '-Dtest=com/recreo/archi/**/*' -Dsurefire.failIfNoSpecifiedTests=false` (paquetage `com.recreo.archi`, qui lit `squad/`) | 229 tests, 2 echecs, ceux de la section 0 ; rien d'autre |
| `outils` | non lance : aucun outil touche (les essais d'A7 et A8 ont eu lieu sur l'arbre jetable B) | - |

## Decisions prises

1. Aucun `ADR` neuf, aucune `ArchRule` neuve (section 6) ; regle (13) d'`ADR-0104` inchangee.
2. T4 d'`ADR-0104` sort de `S55-ART-CP-10-MECANIQUE` et voyage avec le fichier du paquet a reproductions (section 4).
3. Prix de la mecanique reconfirme sur `0a795589`, garde-fou 2 corrige en population exacte a 0 (section 3).
4. Formes ecrites : `S54-ASCII-APOSTROPHES-TESTS` (echappement, `marc-dev`), A7 (une ligne), A8 (refus sans `--sprint`).
