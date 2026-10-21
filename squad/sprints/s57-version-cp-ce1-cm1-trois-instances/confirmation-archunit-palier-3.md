# [Sprint s57] Palier 3 - confirmation ArchUnit a la cloture, gardes ecrits ou amendes, dettes d'architecture, ecarts de procedure

- **Date** : 2026-10-21, 16:38 (UTC+02:00), lue par `date`.
- **Auteur** : `theo-archi`.
- **Arbre mesure** : `round-two` a `b52c4ac5672118254614f54548576873dd475a5d` (`git rev-parse round-two`), `git status --short` vide avant ecriture. Mesures sur deux copies de cet arbre par `git archive b52c4ac5`, hors de tout worktree et hors du depot, l'une pour le reacteur complet, l'autre pour les mutations.
- **Version** : **JDK 21.0.10**, propriete `java.version` des 319 rapports `TEST-*.xml` du reacteur complet, une seule valeur (`ADR-0071`).
- **Modele** : `confirmation-archunit-palier-2.md` ; `cloture-palier-2.md` section 8.
- **Ce que ce fichier ne fait pas** : il ne modifie ni code, ni `ADR`, ni `DEC`, ni registre, ni story. Il ne prononce aucun Done, technique ou pedagogique. Il n'est pas commite par moi.

## 0. Verdict

| Code | Question | Verdict |
|---|---|---|
| `V1` | Les regles ArchUnit sont-elles vertes au sommet ? | **oui** : 15 classes porteuses de regles, 88 tests, 0 echec ; reacteur complet `BUILD SUCCESS`, 2855 tests, 0 echec (section 1) |
| `V2` | Le compte a-t-il bouge pendant le palier ? | **non** : 41 declarations, 38 `@ArchTest`, 16 fichiers a `bb322938` (cloture du palier 2) comme a `b52c4ac5` ; 39 et 38 dans 15 fichiers hors contre-exemple (section 2) |
| `V3` | Les gardes ecrits ou amendes au palier sont-ils executables et mordent-ils sur l'arbre fusionne ? | **oui, les sept** ; neuf mutations rejouees au sommet, chacune rouge ou verte comme son `ADR` l'ecrit (section 3) |
| `V4` | Des dettes d'architecture restent-elles ? | **oui, cinq**, aucune bloquante pour la cloture du palier, chacune avec son destinataire (section 4) |
| `V5` | Des ecarts de procedure ? | **sept**, dont deux de ma main (section 5) |

## 1. Commandes et resultats

| Mesure | Commande | Resultat |
|---|---|---|
| `R1`, reacteur complet | `bash outils/mvn-jdk21.sh -B clean verify` sur la racine d'une copie de `b52c4ac5`, sans `-pl`, ce fichier absent | `BUILD SUCCESS`, exit 0, 12 min 36 s. **2855 tests, 0 echec, 0 erreur, 0 ignore** : 120, 31, 523, 1794, 381, 6 d'integration (lignes `Tests run` de fin de module) ; recompte a part par l'attribut `tests` des 319 `TEST-*.xml` : 2855 |
| `R2`, perimetre ArchUnit | rapports `TEST-*.xml` de `R1`, classes des 15 fichiers de la section 2 hors contre-exemple | **15 classes, 88 tests, 0 echec, 0 erreur** |

Detail de `R2`, identique au palier 2 classe par classe (`confirmation-archunit-palier-2.md:28`) : `AssertionsWebTest` 2, `AbstractionsCiteesTest` 5, `AccesALEtatDeSessionTest` 2, `ArchitectureEnCouchesTest` 2, `ChargementDuContenuTest` 2, `DecouplageMecaniqueContenuTest` 13, `DifficulteTest` 3, `GestionDesErreursTest` 4, `LOrdreDesMatieresVientDeLaDonneeTest` 4, `LOrdreDesNiveauxResteUneDonneeTest` 5, `LeChoixDesJeuxResteDerriereLePortTest` 1, `LeSeulCookieEstLeMarqueurDePartieTest` 2, `LesReglesMordentTest` 39, `PureteDesCouchesTest` 2, `ScanDeComposantsTest` 2. Un rapport compte les `@ArchTest` et les `@Test` de la classe : 88 n'est pas un compte de regles.

`R1` est egal au total de la derniere porte technique de `sofia-sm` (2855 sur `03de0f6d`, `rapport-sofia-dev-palier-3.md:381`) : les commits posterieurs a `03de0f6d` ne touchent que `squad/`.

## 2. Compte de regles

**Methode d'`ADR-0104`** : lignes des `*.java` suivis par Git au commit nomme (`git grep`, ce qui exclut les worktrees d'agents) qui correspondent a `^\s*(public |private |protected )?static final ArchRule ` d'une part, a `^\s*@ArchTest\s*$` d'autre part, comptees par `wc -l` ; fichiers par `git grep -l` sur l'un ou l'autre motif.

| Commit | `static final ArchRule` | `@ArchTest` | Fichiers | dont `RegleContreExemple.java` (declarations, `@ArchTest`, fichier) |
|---|---|---|---|---|
| `bb322938` (cloture du palier 2) | 41 | 38 | 16 | 2, 0, 1 |
| `31fe0126` (fusion de la confirmation du palier 2) | 41 | 38 | 16 | 2, 0, 1 |
| `b52c4ac5` (sommet) | **41** | **38** | **16** | 2, 0, 1 |
| ecart sur le palier | **0** | **0** | **0** | 0 |

Regles reelles : **39 declarations dans 15 fichiers**, dont 38 executees par `@ArchTest` et une par deux `.check(...)` explicites (`ScanDeComposantsTest`), meme lecture qu'au palier 2 (`confirmation-archunit-palier-2.md:40`).

**Aucune regle affaiblie.** `git diff --stat bb322938 b52c4ac5` restreint aux 16 fichiers ci-dessus et a `bootstrap/src/test/resources/contreexemple/LesGardeFousSontMisALEpreuveTest` rend 0 ligne. Sur tout le diff `*.java` du palier (`git diff --shortstat bb322938 b52c4ac5 -- '*.java'` : 16 fichiers, 4316 insertions, 45 suppressions), aucune ligne ajoutee ne porte `@Disabled`, `@Ignore`, `ArchIgnore`, `allowEmptyShould(true)` ni `assume` (`git diff ... | grep -E '^\+.*(...)'` : rien). Les 45 suppressions ne sont pas lues ligne a ligne ici : **non verifie** qu'aucune ne retire une assertion.

## 3. Gardes ecrits ou amendes pendant le palier

Aucun n'est une `ArchRule`, chacun pour un motif ecrit dans son `ADR` (une feuille de style et des gabarits, un attribut de gabarit, un fichier Markdown) : le compte de la section 2 ne bouge donc pas, ce qui est juste. Chaque `ADR` amende ecrit son propre compte inchange (`ADR-0031...md:422`, `ADR-0054...md:435` et `:510`).

### 3.1 Les gardes et leur morsure telle que l'auteur l'a ecrite

| Code | Commit | Regle | Test | Mutation ecrite et mesure de l'auteur |
|---|---|---|---|---|
| `GA1` | `78b260e3` | `ADR-0031`, amendement du 2026-10-20, decision 1 : le lecteur partage coupe un en-tete aux seules virgules de premier niveau (`Z1`) (`ADR-0031...md:349`) | `UnEcranNeGouvernePasLApparenceDUnAutreTest`, lecteur dans `LAnimationRetardeMaisNeDetermineRienTest` (`reglesDe`, `selecteurs`) | `V1` : le lecteur recoupe sur toute virgule, 2 echecs sur 14 ; `V3`, `V4` : critere 1 et 2 de la story, 2 echecs chacun (`ADR-0031...md:397-400`) |
| `GA2` | `78b260e3` | meme amendement, decision 2 : le CSS imbrique est refuse par le lecteur (`Z2`) (`:355`) | meme fichier, `uneRegleImbriqueeQuiNommeLeMessageEstRefuseeParLeLecteur` | `V2` : refus desactive, 1 echec ; `V5`, `V7` : 9 echecs sur la classe (`:398`, `:401`, `:403`) ; 49 echecs dans 11 classes sur `adapter-web` entier (`rapport-qualite-palier-3-mutations-css.md:43`, `:73`) |
| `GA3` | `78b260e3` | meme amendement, decisions 3 et 4 : l'exemple de la decision 1 epingle (`P1`, `Z5`) ; cliquet exact de 3 regions d'annonce (`Z4`) (`:361-367`) | `lExempleDeLaDecisionUnEstRefuseParLaClauseDuSujetHorsDuMessage`, `leNombreDeRegionsDAnnonceEstEpingle`, `unMessageQuiPerdSaRegionDAnnonceFaitEchouerLeCliquet` | aucune mutation de gabarit reel ecrite dans l'`ADR` pour le cliquet : la table `V0` a `V7` ne la porte pas (`:394-403`). Remesuree ici (`T4`) |
| `GA4` | `558dd887` | `ADR-0054`, amendement du 2026-10-20 : la pile de polices servie est celle de la feuille tierce, perimetre de variables derive de la feuille tierce (`ADR-0054...md:368-381`) | `LaPileDePolicesServieNeChangePasEnSilenceTest` (8 tests a ce commit) | `W1` a `W3` sur le test, 1 echec chacun (`:408-413`) ; `MP1` a `MP6` et `ME1` a `ME13` sur `recreo.css` par `yanis-qa`, dont deux trous `ME8`, `ME9` et un faux rouge `ME10` (`rapport-qualite-palier-3-mutations-css.md:20-67`) |
| `GA5` | `19aa6c98` | `ADR-0054`, amendement du 2026-10-21 : echappements CSS decodes avant comparaison, `inherit` admis et lui seul (`:451-465`) | meme fichier, 12 tests | `ME8`, `ME9` : 3 echecs, verts avant ; `ME10` : 0 echec, rouge avant ; `W4` decodage desactive : 2 echecs ; `W5` `inherit` refuse : 2 echecs (`:482-492`) |
| `GA6` | `5a0b55f3` | `ADR-0058`, amendement du 2026-10-21 : `input.champ-reponse` porte `autocorrect="off"` (`ADR-0058...md:676`, `:691`) | `EcranDeSaisieTest.leChampDemandeAuClavierDeNePasCorrigerLaFrappe`, trois rendus, un champ exactement sur chacun (`:719`) | attribut retire : 15 tests, 1 echec (`:730`) |
| `GA7` | `21e994a4`, `ad9ef86d` | `ADR-0085`, clause 2 : allocation de `PL-9` a `PL-13` au registre, numeros suivants du prefixe deja alloue (`REGISTRE-identifiants.md:15-17`, `:27-29`, `:2074-2078`) ; ligne d'etat 999 et 179 (`:945`) | `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest.leDepotReelEstCoherent` (`bootstrap`) | avant allocation, le garde etait rouge sur `round-two` : 1 echec sur 10, les seuls ecarts etant ces identifiants (`rapport-sofia-dev-palier-3.md:178`, `:254`) ; vert apres (`:207`, `:285`) |

### 3.2 Remesure sur l'arbre fusionne

**Methode.** Copie de `b52c4ac5` par `git archive`, dans mon repertoire de travail, hors du depot. Un fichier mute a la fois : ligne ajoutee en fin de `recreo.css`, ou remplacement verifie present une fois exactement (`grep -cF` vaut 1) ; restauration par copie de reference apres chaque mutation, `cmp` identique (aucune ligne *"RESTAURATION ECHOUEE"* dans le resume). Commande pour `T0` a `T8` : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test -Dtest=UnEcranNeGouvernePasLApparenceDUnAutreTest,LaPileDePolicesServieNeChangePasEnSilenceTest,EcranDeSaisieTest -Dsurefire.failIfNoSpecifiedTests=false`, perimetre ces trois classes (14 + 12 + 15 = 41 tests), `-am` present ; pour `T9` : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest -Dsurefire.failIfNoSpecifiedTests=false`, 10 tests. Ces chiffres sont ceux des classes nommees, pas des modules.

| Code | Garde | Mutation | Sortie |
|---|---|---|---|
| `T0` | tous | aucune | 41 tests, 0 echec, exit 0 |
| `T1` | `GA1` | `.ecran-de-jeu:has(.consigne, .est-faux) .propositions { row-gap: 0; }` | **2 echecs**, `aucuneRegleDontLeSujetEstHorsDuMessageNeNommeCeQueSeulLeMessagePose` et la clause verte `uneListeDeClassesDuMessageSousSonSommetResteVerte`, qui lit la meme feuille ; le message cite le selecteur entier. Vert a `bb322938` (`Z1`, `confirmation-archunit-palier-2.md:90`) |
| `T2` | `GA2` | `.ecran-de-jeu { &:has(.est-faux) .propositions { row-gap: 0; } }` | **15 echecs** : 9 dans la classe d'`ADR-0031`, 6 dans celle de la pile de polices, qui lit le meme lecteur ; message *"regle imbriquee, non lue par les garde-fous de la feuille"*. Vert a `bb322938` (`Z2`, `:91`) |
| `T3` | `GA3`, exemple | `body:has(.est-juste) .cabinet-bandeau { background-color: gold; }` | **2 echecs**, la clause du sujet hors du message, message *"cabinet-bandeau : le sujet de ce selecteur est hors du ..."*. C'est `Z5b` (`:78`), rouge pour son motif |
| `T4` | `GA3`, cliquet | `role="status"` retire de `fragments/manche.html` (une occurrence) | **2 echecs** : `leNombreDeRegionsDAnnonceEstEpingle` (*"le nombre de regions [role=status] des gabarits a change"*) et `lExempleDeLaDecisionUnEstRefuseParLaClauseDuSujetHorsDuMessage`. Mutation du gabarit reel, demandee en `confirmation-archunit-palier-2.md:132`, mesuree pour la premiere fois ici |
| `T5` | `GA4` | `body { font-family: Georgia, serif; }` | **3 echecs**, le test principal et deux clauses vertes qui lisent la feuille reelle ; une seule detection. `yanis-qa` en comptait 2 a `558dd887` (`rapport-qualite-palier-3-mutations-css.md:22`) : la troisieme lecture de la feuille est `uneDeclarationQuiHeriteLaPoliceDuParentResteVerteEtInitialResteRouge`, ajoutee par `19aa6c98` |
| `T6` | `GA5` | `body { \66ont-family: Georgia; }` | **3 echecs**, message *"declare \66ont-family: Georgia"*. Vert a `558dd887` (`ME8`) |
| `T7` | `GA5` | `button { font-family: inherit; }` | **0 echec**, exit 0. Rouge a `558dd887` (`ME10`) |
| `T8` | `GA6` | `autocorrect="off"` retire de la balise du champ de `fragments/saisie.html` | **1 echec**, `leChampDemandeAuClavierDeNePasCorrigerLaFrappe`, *expected "off" but was ""* |
| `T9` | `GA7` | base : aucune ; puis la ligne `PL-13` de la table du registre retiree (1 ligne) | base : 10 tests, 0 echec. Mutation : **1 echec**, `leDepotReelEstCoherent`, *"... ecrit l'identifiant PL-13, que squad/decisions/REGISTRE-identifiants.md n'alloue pas"* |

**Ce que ces mesures ne couvrent pas.** `ME9` (`@\66ont-face`) n'est pas rejouee : sa morsure est celle de l'`ADR` (`ADR-0054...md:486`). Aucune mutation n'a ete faite sur le test lui-meme au sommet (les `V1`, `V2`, `W1` a `W5` des `ADR` sont des mesures de branche, pas de l'arbre fusionne). Ce que fait le clavier d'un appareil n'est vu par aucun de ces tests (`ADR-0058...md:719`).

## 4. Dettes d'architecture qui restent

| Code | Dette | Etat au sommet | Destinataire |
|---|---|---|---|
| `DA1` | Garde generique de `LON-CE1-3` a `-5` et `TRV-CE1-1` (ecart `E3`) | **non ecrit**. Forme `F1` retenue (`decision-theo-garde-ce1-e3.md:11`), chemin du test fixe (`:61`) ; `git ls-files` ne connait aucun fichier `LesPropositionsEtLesTextesSupportsDeCe1TiennentLeursBornesTest` (rien rendu). La story n'est pas ecrite (`composition-palier-3.md:83`). Le perimetre par matiere qu'attendait le critere `CA4` (`decision-theo-garde-ce1-e3.md:87`) est fourni par `nadia-prof` (`reponses-nadia-questions-stories-palier-3.md:18`, question `Q-X1`) : plus rien n'attend de moi | `camille-po` (story et moment) ; puis le developpeur que `sofia-sm` engage ; l'`ADR` est ecrit dans le commit du test (`decision-theo-garde-ce1-e3.md:76`) |
| `DA2` | Les memes regles verifiees par paquet seulement | Au sommet, 24 fichiers `*Test.java` citent `LON-CE1-3`, `-4` ou `-5` (`git grep -lE "LON-CE1-[345]" b52c4ac5 -- '*Test.java'`), tous des `PaquetCe1*Test` ; 23 citent `TRV-CE1-1` ; 36 fichiers `PaquetCe1*Test.java` existent ; 48 YAML du repertoire de contenu portent la ligne `niveau: "CE1"` (`git grep -l`, motif sur la ligne, pas sur le descripteur parse). Un paquet CE1 sans test qui cite ces regles les passe en silence (forme `F3` ecartee pour ce motif, `decision-theo-garde-ce1-e3.md:17`). Se ferme avec `DA1` | meme route que `DA1` ; le retrait des assertions redondantes est un geste distinct (`:21`) |
| `DA3` | 6 items dont la reponse attendue passe sous le pli a 744 x 551 a la revelation (`-06` 14, 16, 19, 20 ; `EMC` 13 ; `-11` 13), 8 a 720 avec barre (`mesure-ines-palier-3-pli-744x551.md:1102-1103`) | Reserve non bloquante (`veto-nadia-gabarit-pli.md:200`). **Aucun garde du reacteur** ne mesure une cote de pli : elle ne se lit qu'au navigateur, hors CI (`lecture-testabilite.md:105-106`). Aucune story trouvee par `git grep -i "reponse attendue entiere"` sur `docs/backlog/backlog.md` (non verifie au-dela de ce motif). Si le levier choisi est le gabarit, il passe par `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest`, qui epingle la forme mesuree et rougit sur tout changement de declaration (`confirmation-archunit-palier-2.md:59`) | `camille-po` (stories et levier, `veto-nadia-gabarit-pli.md:214-217`) ; `nadia-prof` seule garde de la lecture (`:182-183`) |
| `DA4` | Les mots proposes au-dessus du clavier, question qui m'est routee (`arbitrage-nadia-q05-q10.md:419-421`) | **Non instruite.** `ADR-0058` amende ne pose que `autocorrect="off"` ; `spellcheck` et `autocapitalize` n'y sont pas poses faute de verification (`ADR-0058...md:699`) ; `autocomplete="off"` y est deja et vise une autre liste (`:739`). Je n'ai lu aucune source ce jour sur un attribut qui supprimerait ces propositions : **non verifie**. Ce n'est pas une condition de `nadia-prof` (`arbitrage-nadia-q05-q10.md:419-421`) | `theo-archi`, reponse ecrite a `nadia-prof` ; un attribut trouve s'ajoute par amendement d'`ADR-0058`, avec sa garde dans `EcranDeSaisieTest` |
| `DA5` | `MAT-CE1-14` conditionnee a `A3` d'`ADR-0080` (`DEC-0120...md:785`) | Routee a moi sans etre tranchee (`arbitrage-nadia-q05-q10.md:59-61`). **Ma lecture, non actee** : la section 6 d'`ADR-0080` dit qu'`A3` ne bloque pas un dessin carre a 352 px (`ADR-0080...md:1164-1166`) ; une grille de 4 x 4 a 6 x 6 dans un carre de 100 unites (forme de `symetrie-grille-a.svg:4`, `viewBox="0 0 100 100"`, 352 px) donne des cases de 25 a 16,7 unites, au-dessus de la barre de 15 (`ADR-0080...md:1183-1186`). **La marge est mince a 6 x 6** : 1,7 unite, et une zone plus petite que sa case passerait sous 15 sans rien d'automatique pour le voir (`:1197`). Reste aussi le faux vert du seul ecran de premiere manche juge (`:1167-1169`). Donc `A3` ne bloque pas la ligne, il garde ce que l'audit garde seul aujourd'hui | `theo-archi` : amendement d'`ADR-0080` (section 6) au moment ou la story est ecrite, avec la forme (a) ou (b) choisie ; `camille-po` : la condition ecrite dans `DEC-0120` |

## 5. Ecarts de procedure vus

| Code | Ecart | Preuve | A qui |
|---|---|---|---|
| `EP1` | `round-two` porte un garde rouge deux fois apres une fusion, fusion faite en le sachant : de `46313f13` a `21e994a4`, puis de `edb2ed8a` a `ad9ef86d`, `leDepotReelEstCoherent` echoue (*"etat attendu"*) | `rapport-sofia-dev-palier-3.md:178`, `:254` | `sofia-sm` |
| `EP2` | `ines-design` ecrit trois fois des identifiants sans allocation (`PL-1` a `PL-8`, `PL-9` et `PL-10`, `PL-11` a `PL-13`), alloues apres coup par moi ; c'est la cause d'`EP1` | `mesure-ines-palier-3-pli-744x551.md:272`, `:631` ; `rapport-sofia-dev-palier-3.md:105` | `ines-design` ; allouer avant d'ecrire est la regle d'`ADR-0085` |
| `EP3` | Le controle de perimetre avant fusion de `s57-ines-p3` et `s57-yanis-mutations-css` etait mal forme et n'a rien liste ; refait apres la fusion | `rapport-sofia-dev-palier-3.md:176` (ecrit par son auteure) | `sofia-sm`, deja nomme |
| `EP4` | `releve-ecran-ines-palier-3.md` cite ses captures par 96 lignes de chemin `%TEMP%\claude\...\scratchpad\...`, propres a une session d'agent et hors depot : la forme passe `verifier-chemins.sh` (variable), mais aucun autre lecteur ne peut ouvrir ces fichiers | `grep -c` sur l'identifiant de session dans ce fichier : 96 ; job `chemins` vert sur `cb18ebfa` (`rapport-sofia-dev-palier-3.md:338`) | `ines-design` et `sofia-sm` |
| `EP5` | Le sujet de la fusion `4650aeab` nomme `PileDePolicesServieNeChangePasEnSilenceTest` ; la classe s'appelle `LaPileDePolicesServieNeChangePasEnSilenceTest` | `git show -s --format=%s 4650aeab` | sans suite : un sujet de commit ne se corrige pas |
| `EP6` | **De ma main.** `5a0b55f3` est commite sur une mesure partielle, sans `verify` complet ni jobs `chemins`, `prefixes`, `outils` nommes ; la porte complete est faite par `sofia-sm` apres la fusion (2855, 0 echec). Pour `78b260e3` et `19aa6c98`, leurs messages ne nomment aucun job : **non verifie** que je les ai lances | `git show -s 5a0b55f3` (*"Pas de verify complet."*) ; `rapport-sofia-dev-palier-3.md:364-381` | `theo-archi` |
| `EP7` | **De ma main.** L'amendement du 2026-10-21 d'`ADR-0054` code ses constats `R5` et `R6` ; `R6` designe deja dans ce sprint le refus de saisie de `nadia-prof` et un ecart de la cloture du palier 2. Un meme code, trois sens, le meme jour pour deux d'entre eux | `ADR-0054...md:438` ; `arbitrage-nadia-q05-q10.md:290` ; `cloture-palier-2.md:167` | `theo-archi` : le prochain amendement de ce fichier dit *"constats 1 et 2 de `yanis-qa`"* a cote des deux codes |

## 6. Ce qui n'est pas lance pour ce fichier

`R1` est le job `build` (partie `verify`) sur l'arbre du sommet **sans** ce fichier ; l'etape *"Package du fat jar"* n'est pas relancee a part. Pour ce fichier lui-meme, copie dans la copie de mutation du sommet : `bash .github/scripts/verifier-chemins.sh` sur ce seul fichier, *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, exit 0 ; `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest,LesGardeFousSontMisALEpreuveTest -Dsurefire.failIfNoSpecifiedTests=false`, 20 tests, 0 echec, exit 0 (perimetre : ces deux classes). `prefixes` et `outils` ne s'appliquent pas a un fichier non commite. **Le Done technique n'est pas prononce par moi.**

## Decisions prises

**Aucune `ADR` ni `DEC` creee ni amendee par ce fichier**, `ADR-index.md` inchange. Il confirme le compte (ecart nul sur le palier), remesure au sommet les sept gardes du palier par neuf mutations, et nomme cinq dettes avec leur destinataire. Une lecture d'architecture y est ecrite sans etre actee (`DA5`) : elle le sera par amendement d'`ADR-0080`.
