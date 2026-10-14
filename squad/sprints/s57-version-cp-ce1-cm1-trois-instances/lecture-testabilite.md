# [Sprint s57] Lecture de testabilite des criteres - yanis-qa - 2026-10-14

Base : `544159d9` (tete de la branche `s57-yanis-lecture`, issue de `round-two` ; arbre propre au depart, `git status --short` vide). `round-two` a avance de
deux commits depuis : `20860e75` (`DEC-0120` A.53 a A.57) et `8396696e` (`arbitrage-nadia-palier-1.md`, `composition-palier-1.md`). Je les ai lus par
`git show round-two:<chemin>` **sans les fusionner** ; ce fichier cite donc deux documents absents de l'arbre sur lequel il est commite.
Sources lues : `duree-et-paliers.md`, `preparation.md`, `DEC-0120` en entier (amendements A.1 a A.57), `ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`
(sections 0 a 4), la section "Cloture de s56" du backlog (`S56-ART-CP-10-REPRISE`, `S56-RDO-CP-R1`, `-R2`, `S56-VSJ-CE1-R1`, `S57-ADR-0065-DEUX-INSTANCES`),
`ADR-0065` amendement A2, `squad/lessons.md`, et les lectures de s53 a s55 pour la forme. **Aucun fichier de code, de contenu ni d'outil du depot n'est
edite.** Les simulations (sections 2.1 a 2.5) ont tourne dans des worktrees jetables hors du depot, supprimes a la fin ; les textes appliques sont ceux de
`nadia-prof` quand elle les a ecrits, sinon un texte de substitution dit comme tel. Ce fichier ne prononce aucun Done.

**Etat de s57 a cette heure** : `planning.md` n'existe pas, s57 n'est pas ouvert, `DEC-0120` est `Proposee` (A.55). Aucune story des trois lots de francais CE1 du
palier 1 n'existe (`grep -c '^### S57-BR' docs/backlog/backlog.md` rend 0 sur `544159d9`) : **pour ces trois lots il n'y a aucun critere d'acceptation a lire** (section 2.5).

## 1. La base

**Commande** : `bash outils/mvn-jdk21.sh -B clean verify`, racine du worktree, reacteur complet, sans `-pl`.
**Resultat** : `BUILD SUCCESS`, code de sortie 0, 10 min 41 s. L'enveloppe a rendu "Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10" ; la version que la
machine virtuelle des tests rapporte est lue dans les rapports XML : propriete `java.version` = **21.0.10** (`ADR-0071`).

| Module | Surefire | Failsafe | Echecs, erreurs, ignores |
|---|---|---|---|
| `recreo-domain` | 120 | - | 0 |
| `recreo-application` | 31 | - | 0 |
| `recreo-adapter-web` | 486 | - | 0 |
| `recreo-adapter-content` | 1484 | - | 0 |
| `recreo-bootstrap` | 371 | 6 | 0 |
| **Reacteur** | **2492** | **6** | **0** |

**Total : 2498 tests.** Methode : somme des attributs `tests`, `failures`, `errors`, `skipped` de l'element racine de chaque `TEST-*.xml` sous
`target/surefire-reports` et `target/failsafe-reports` de chaque module (11, 2, 54, 174, 59 fichiers Surefire ; 4 fichiers Failsafe), recoupee avec les lignes
`Tests run:` de fin de module. Je ne cite pas ce nombre comme total de s57 : `sofia-sm` l'etablit sur l'arbre fusionne. Un seul passage, vert, aucun test instable observe ;
je n'ai pas relance la suite pour juger de la stabilite. Les lignes `ERROR` du journal Maven sont des erreurs que des tests de gestion d'erreur provoquent expres (`GestionnaireDesErreurs`) ;
aucune ne correspond a un echec.

**Jobs de `ci.yml`, etat mesure ici** : `build` etape `mvn -B verify` : lancee, via l'equivalent portable `clean verify` (ci-dessus) ; etape `mvn -B -pl bootstrap -am package` : **non lancee**.
`prefixes` et `chemins` : voir section 6. `outils` : non lance, aucun outil n'est modifie par ce commit. J'ai lance en plus le juge du controle du garde-fou de l'accent
(`bash .github/scripts/verifier-garde-fou-accent-test.sh`, step du job `prefixes`) : **21 cas passent**. CI distante : non lue, aucun verdict de CI n'est cite ici.

## 2. Lecture poste par poste

Un critere est **testable** s'il se juge par une commande et un resultat attendu ; **tenable** si un developpeur qui applique le texte de `nadia-prof` le rend vert.
"Aujourd'hui" = la commande lancee par moi sur `544159d9`. "Apres" = la meme commande sur un worktree jetable ou le texte est applique par script.
Une mutation ne se lit qu'apres deux controles (`squad/lessons.md`) : le motif a ete trouve, et le nombre de tests lances est superieur a zero ; chacune des mutations
ci-dessous a ete controlee ainsi (ligne imprimee ou `git diff --stat` non vide, et compte de tests lu dans la sortie).

| Poste | Story au backlog | Criteres ecrits par | Developpeur | Instance |
|---|---|---|---|---|
| `VSJ-CE1-R1` | `S56-VSJ-CE1-R1` | `nadia-prof` (ses mots) | `marc-dev` (backlog) | A |
| `S54-OBS-SCI-CONSIGNE` | oui, entree de s54 ; criteres : arbitrage de s57, section 3 | `nadia-prof` | `lea-dev` (`DEC-0120` A.44) | B |
| Correction d'`ART-CP-10` | `S56-ART-CP-10-REPRISE`, `S56-RDO-CP-R1`, `S56-RDO-CP-R2` | `nadia-prof` (ses mots) | a designer ; forme a `theo-archi`, `ines-design`, `lea-dev` | B (rejeu) |
| `S57-ADR-0065-DEUX-INSTANCES` | oui | `camille-po` | `theo-archi` | hors `PAR`, livree |
| Francais CE1, palier 1 : `FRA-CE1-04`, `-10`, `-15` | **aucune** | **aucun** (conditions de s48 seulement) | a designer | A (`-04`), A' (`-10`, `-15`) |

### 2.1 `VSJ-CE1-R1` (`ce1-verbe-sujet`, `FRA-CE1-13`, reserve non bloquante)

Module des tests : `adapters/adapter-content`, classe `PaquetCe1VerbeSujetTest` (22 tests aujourd'hui, 23 avec le mien).

| Critere (ses mots) | Commande | Aujourd'hui | Apres | Verdict |
|---|---|---|---|---|
| Au moins 3 items de sujet ont une proposition fausse **avant** le verbe cite | pas de commande ecrite (le script du backlog est hors depot, le backlog demande de l'ecrire avec moi). **La mienne** : un test dans `PaquetCe1VerbeSujetTest` qui reprend ses assistants (`estUnItemDeSujet`, `motsDeLaPhraseCitee`, `rangDansLaPhrase`) et compte les items dont un distracteur est avant le verbe, attendu au moins 3 | **0 sur 15 items de sujet : rouge, mesure** (`-pl adapters/adapter-content -am test -Dtest=PaquetCe1VerbeSujetTest` : 23 tests, 1 echec, le mien, `Expecting actual: 0L to be greater than or equal to: 3L`) | non simule en entier (il faut trois items de `nadia-prof`) | testable, tenable |
| La bonne reponse reste le sujet, `LON-CE1-1` et `J1` tenues | tests existants de la classe | verts | **voir ci-dessous** | testable ; **tenue incertaine** |
| Propriete 8 amendee par elle, "le test qui la porte change avec elle" | `vsjCe12LeSujetEstAvantLeVerbeCiteEtLesDeuxDistracteursApres` | vert, garde **le contraire** du critere | rouge sur tout item conforme | il change dans le meme commit que les textes |
| Rejeu, 3 items au moins, instance A, 0,5 | - | - | - | ce que la machine ne voit pas : qu'un enfant doive se poser "Qui est-ce qui ?" |

**Simulation avec l'exemple de `nadia-prof`.** Premier item du paquet remplace par son exemple (`Ce matin, Léa lit un livre.`, bonne reponse `Léa`, propositions fausses `ce matin`, `un livre`),
espaces insecables conservees comme dans le fichier ; une seule modification. Resultat mesure (`-pl adapters/adapter-content -am test -Dtest=PaquetCe1VerbeSujetTest`, 23 tests, **5 echecs**) :
la propriete 8 (attendu, elle l'amende), mon test de comptage (1 sur 3, attendu), **et trois autres que le critere ne nomme pas** :

1. `propriete2J1LaMajusculeNeDesigneJamaisLaBonneReponse` : `Léa` est la seule proposition a majuscule (`ce matin` et `un livre` n'en portent pas : c'est ainsi que je les ai ecrites) ;
2. `propriete4LesPrenomsFemininsEtMasculinsSontALUnPres` : `Ce` est "une majuscule hors des listes de prenoms et d'ouvertures" ;
3. `vsjCe11EtCe12QuinzeItemsDeSujetAuMoinsEtCinqPourChaqueFormeDeSujet` : l'item perdait un pronom (`elle`), la repartition pronom, nom seul, groupe nominal tombe sous le plancher de cinq.

**Ce que cela dit** : appliquer son exemple mot pour mot oblige a amender trois tests dont elle ne parle pas (une liste d'ouvertures, un plancher, une regle de majuscule), ou a choisir d'autres exemples. `J1` est citee comme tenue
dans son critere et elle est rouge sur son propre exemple. Un developpeur qui garde `J1` verte doit ecrire ses trois items sans que la bonne reponse soit la seule proposition a majuscule. Question `Q-Y2`.
**Defaut possible du nouveau test** : un distracteur absent de la phrase donne un rang -1 et ne compte pas ; la propriete 3 (chaque proposition vient de la phrase) le ferme, a condition de rester verte.

### 2.2 `S54-OBS-SCI-CONSIGNE` (`cm1-sciences-extraire-information`, `SCI-CM1-20`, 0,25)

Modules : `adapters/adapter-content` (`PaquetCm1SciencesExtraireInformationTest`) et `bootstrap` (`TexteAccentueExposeParLePortEntrantTest`). Simulation : les 12 enonces perdent leur premiere phrase par script (`Lis ce tableau,` ou `Lis ce texte, puis réponds à la question.`), aucune autre ligne touchee.

| Critere | Commande | Aujourd'hui | Apres | Verdict |
|---|---|---|---|---|
| 1. les 12 enonces commencent par `D'après ce tableau,` ou `D'après ce texte,` | `grep -c "puis réponds à la question"` sur le paquet | **12, rouge** | **0** | testable, tenable. **Gardee par aucun test** (voir ci-dessous) |
| 2. `git diff` ne touche que les 12 lignes `enonce:` | `git diff -U0 -- <paquet> \| grep -E '^[+-]' \| grep -vE '^(\+\+\+\|---)' \| grep -vc '^[+-]  - enonce:'` | 0 (arbre propre) | **0** ; `git diff --stat` : 12 insertions, 12 suppressions | testable, **plus faible que le critere** (voir ci-dessous) |
| 3. la consigne du catalogue ne change pas | `TexteAccentueExposeParLePortEntrantTest` | vert | vert | **gardee, mutation mesuree** (voir ci-dessous) |
| 4. les tests qui lisent le paquet restent verts, le developpeur nomme ceux qu'il a lances | reacteur `test` complet sur l'arbre simule | vert | **vert : 2492 tests, 0 echec** (`-pl adapters/adapter-content,bootstrap -am test`, qui construit les cinq modules ; Failsafe non lance) | tenable. Les classes qui nomment le paquet : `PaquetCm1SciencesExtraireInformationTest`, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, `TexteAccentueExposeParLePortEntrantTest` |
| 5. rejeu par elle, une partie de dix manches | - | - | - | la machine ne voit pas ce que l'enfant lit a l'ecran |

**Critere 1 : aucun garde-fou une fois livre.** Aucun test ne lit la phrase retiree (`grep -c "puis r"` sur `PaquetCm1SciencesExtraireInformationTest` : 0, comme `DEC-0120` A.34 le dit) : remettre la phrase dans un item laisse la CI verte.
Test de substitution ecrit dans la classe, dans le worktree jetable : chaque enonce ne contient pas `puis réponds à la question` et satisfait `^D'après ce (tableau|texte),.*`. **Rouge sur l'arbre de base** (6 tests, 1 echec, premier item), **vert sur l'arbre simule** (6 tests, 0 echec). Ce test n'est pas commite ; il est la forme que je demande au developpeur, `lea-dev`.

**Critere 2 : la commande ne protege pas ce que le critere dit.** Trois mutations sur l'arbre simule, la meme commande : strip seul **0** ; un distracteur modifie **2** (elle mord) ; une question reecrite sur sa ligne `enonce:` (`quel est le régime alimentaire de la vache` devenu `quel est le repas de la vache`) **0** : **elle ne mord pas**.
Le critere dit "rien d'autre ne change" ; la commande ne voit pas un enonce reecrit au-dela de sa premiere phrase. La forme qui mord : pour chacune des 12 paires, l'ancienne ligne est egale a la phrase retiree suivie de la nouvelle. Question `Q-Y3`.

**Critere 3 : mutation.** Consigne de l'entree du catalogue (`catalogue.yaml`, ligne 551) changee en `Lis le texte ou le tableau, puis réponds.` : `TexteAccentueExposeParLePortEntrantTest` rend **4 echecs sur 10** (`touteChaineExposeeParLeCatalogueReelEstInventoriee`, `chaqueChaineDeLInventaireEstEncoreExposeeParLeCatalogueReel`, `lesNiveauxSansLettreAccentuableRestentVertsSansFigurerDansLInventaire`, `unNiveauNeufHorsDeLInventaireEtHorsExemptionFaitEchouerLeGardeFou`), avec le message
nommant `bandit-manchot-sciences-extraire-information-cm1 (chemin "consigne")`. Le critere 3 est garde. Il garde aussi toute correction legitime de cette consigne, qui devra amender l'inventaire dans le meme commit.

### 2.3 Correction d'`ART-CP-10` (`S56-ART-CP-10-REPRISE`, `S56-RDO-CP-R1`, `S56-RDO-CP-R2`)

**Rien n'est mesurable aujourd'hui sur le jar de `round-two`** : le jeu n'est plus servi (`grep -c "id: bandit-manchot-rapprocher-deux-oeuvres-cp$" adapters/adapter-content/src/main/resources/catalogue.yaml` rend **0**, retrait `13d00bfe`). Les cotes "aujourd'hui" du backlog (656, 676, 568) datent du jar de s56 et sont celles de `nadia-prof` et d'`ines-design` ; **je ne les ai pas rejouees**.

| Critere (ses mots) | Testable ? | Par quoi | Module | Ce qui manque |
|---|---|---|---|---|
| `REPRISE` : a 744 x 551, a la revelation, `.reponse-revelee` finit au-dessus de y = 551 sur les six items ; reference, cases, croix, anneau au-dessus ; `N1` a `N6` tenues | oui, **hors CI** : une cote lue dans un navigateur | `pilote-q7.mjs` (dossier `squad/conformite/releves-s56/`) : Node, Chrome, jar, port 19222 fixe, rend un JSON de cotes **sans verdict** | aucun test du reacteur | un juge pur qui lit le JSON et rend le verdict (forme `ADR-0009`) ; un jeu servi pour mesurer (voir ci-dessous) |
| `RDO-CP-R1` : apres une erreur, `.indice` finit au-dessus de 551 a 744 x 551, la pastille au-dessus de 551 a 992 x 551, sur les six items | idem | idem | idem | idem |
| `RDO-CP-R2` : sur l'ecran de fin d'un jeu a reponses en image, aucun texte ne designe une oeuvre absente (`celle-ci`, `ces deux œuvres`) | oui, **par le contenu ou par la vue** selon la forme de la correction | `grep -c 'celle-ci'` et `grep -c 'ces deux'` sur `cp-rapprocher-deux-oeuvres.yaml` : **6 et 6** ; aucun test ne les lit (`grep -c "celle-ci\|ces deux" PaquetCpRapprocherDeuxOeuvresTest.java` : 0) | `adapter-content` si la forme change le texte ; `adapter-web` ou `bootstrap/rendu` (selecteurs `jsoup`, comme `PartieControllerPropositionsEnImageTest`) si la forme change la vue | le critere liste deux expressions, pas la propriete "designer une oeuvre absente" : `cette`, `celle-là`, `ces œuvres` passeraient. `Q-Y5` |

**Ce qu'aucun test du reacteur ne fait ici.** Aucun navigateur n'est dans le reacteur : `grep -rli "playwright\|selenium\|htmlunit" --include=*.java --include=pom.xml .` ne rend aucun fichier, et `FrontieresDuJavaScriptTest` interdit `getBoundingClientRect` et `elementFromPoint` dans `recreo.js`. Le critere de pli est donc un critere de rejeu, mesure par un outil de poste, pas une porte de la CI. C'est une propriete du depot, pas un defaut du critere ; je la dis pour qu'on n'ecrive pas "tenu" sur la foi d'un `mvn verify` vert.

**Ce que le retour du jeu au catalogue coute, mesure.** Worktree jetable sur `544159d9`, hunk de `catalogue.yaml` de `13d00bfe` rejoue a l'envers (l'entree revient, rien d'autre ne change) ; six classes lancees (`-pl adapters/adapter-content,bootstrap -am test -Dtest=...`, 46 tests : 13 dans `adapter-content`, 33 dans `bootstrap`) :
**4 echecs dans 2 classes** : `TexteAccentueExposeParLePortEntrantTest` (3 echecs sur 10 : l'identifiant du jeu n'est pas dans l'inventaire) et `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest` (1 echec sur 14, `chaqueEcranDeChaqueItemAPropositionsEnImageEstSainEtLePerimetreReelEstVide` : le perimetre reel "vide depuis le retrait" ne l'est plus, `ADR-0051`).
Les quatre autres classes restent vertes (`AucuneEntreeDuCatalogueNEnDoubleUneAutreTest`, `PaquetCpRapprocherDeuxOeuvresTest`, `LEnonceDuPaquetCpRapprocherDeuxOeuvresEstUniqueEtCourtTest`, `LeFormatDUneOeuvreNeDesignePasLaBonneReponseTest`). Donc : **rejouer le paquet demande une entree** (`DEC-0116` A.17 : un retour passe par une entree neuve apres un Done qui n'est pas `REFUSE`), et cette entree ne passe pas la CI sans que l'inventaire et la liste d'`ADR-0051` soient etendus. La question de `DEC-0120` 4.2, "comment `nadia-prof` rejoue un paquet sans entree", reste ouverte : `Q-Y4`.

**Gabarit commun.** `composition-palier-1.md` et le backlog disent que la correction peut toucher le gabarit commun. Le perimetre reel des jeux a propositions en image est vide (message de l'assertion ci-dessus) : un changement de gabarit n'a aucun jeu reel a faire regresser, seulement le jeu de preuve de `LesPropositionsEnImageAtteignent...` (items reels mutes) et `PartieControllerPropositionsEnImageTest`. Je n'ai pas mesure ce que ces tests voient d'un changement de balisage de `.reponse-revelee` (`manche.html` lignes 212 et 213, `retour.html` ligne 97).

### 2.4 `S57-ADR-0065-DEUX-INSTANCES` (livree avant l'ouverture, fusion `729b0d8a`, 2026-10-13 17:42)

| Critere (backlog) | Commande | Rendu sur `544159d9` | Verdict |
|---|---|---|---|
| `ADR-0065` porte un amendement date qui dit comment deux instances ajoutent leurs mots | `grep -c -i instance <ADR-0065>` au moins 1 | **15** | vert ; le backlog le dit "indice de presence, pas un jugement" |
| L'amendement et le test sont modifies dans **un seul commit**, avant le premier commit d'un lot de A' ; `git merge-base --is-ancestor <amendement> <premier commit du lot de A'>` sort 0 | `git merge-base --is-ancestor 729b0d8a HEAD`, puis `7a34366c` | 0 et 0 (HEAD est ici le worktree de cette lecture, pas celui d'un lot de A') | **non tenu a la lettre** : `7a34366c` ne touche que l'ADR et l'index (`git show --stat`) ; le test est volontairement inchange, la dette du point 1 est payee dans `bac0a6d1`, deux commits. `ADR-0065` A2 le dit lui-meme. `Q-Y1` |
| Aucune regle assouplie : le compte d'`ArchRule` et d'`@ArchTest` ne baisse pas | `git grep -c "static final ArchRule"` et `git grep -c "@ArchTest"` sur les `*.java` suivis, sommes | **41 et 38** (s56 : 39 et 36) | vert, perimetre : tout `*.java` suivi a `544159d9` |
| `mvn verify` vert | section 1 | vert | vert ; CI distante non lue |

**Le garde-fou que cet amendement cite comme fermant sa dette, mis a l'epreuve.** `verifier-garde-fou-accent.sh` (job `prefixes`, `ci.yml`) : le test (`verifier-garde-fou-accent-test.sh`, 21 cas) passe sur `544159d9`. Mutations du script sur un worktree jetable, test relance :

1. verdict `fautifs` neutralise (`fautifs=""`) : **10 cas en echec sur 21** ;
2. `adapters/adapter-content/pom.xml` retire de la liste des chemins gardes : **1 cas en echec sur 21**.

Le test mord les deux. Le script lance sur la plage reelle `7a34366c^..HEAD` : 2 commits touchent les chemins gardes, tous `[THEO]`, code 0.
**Ce que ni le script ni son test ne ferment, et que l'ADR nomme** : un autre agent qui ecrit `[THEO]` passe ; `02ba9e80` (un amendement de `theo-archi` recommite sur la branche d'une instance) passe ; le point 4 (recevoir l'amendement en fusionnant `round-two`) n'a aucun automate ; le point 3 (un amendement a la fois) n'a que le conflit de fusion. Les conditions 1 (aucun lot ne change de groupe) et 6 (chaque instance "ecrit qu'elle sait l'autre") de `nadia-prof` sont des regles de lecture du planning, sans test possible.
La condition 6 renvoie aussi a `ADR-0085` clause 5 : `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (module `bootstrap`) est le juge ; je ne l'ai pas mute.

### 2.5 Francais CE1, palier 1 : `FRA-CE1-04` (A), `-10` et `-15` (A')

**Il n'y a aucun critere a lire.** Les trois lots n'ont pas de story (`grep -c '^### S57-BR' docs/backlog/backlog.md` : 0), et `composition-palier-1.md` section 4 compte ce geste comme premier report. Ce qui existe : les conditions de `nadia-prof` dans `ARBITRAGE-PREALABLE-s48-francais-ce1.md` (`TRV-CE1-1` a `-7`, `RCT-CE1-1` a `-3`, `TPH-CE1-1` et `-2`, `GNA-CE1-1` et `-2`) et ses points d'audit annonces (`arbitrage-nadia-palier-1.md` 6.1, 6.2). Ce qui suit lit **l'etat des trois paquets et des garde-fous**, pas un critere.

**Etat des paquets** (script de lecture seule hors depot, sans bibliotheque YAML : lecture ligne a ligne des cles `enonce`, `texteSupport`, `bonneReponse`, `distracteurs`, `indice`). Regles de comptage, dites parce que trois regles ont rendu trois resultats a s55 : un mot est un jeton de `split()` qui porte une lettre ou un chiffre ; une phrase se coupe apres `.`, `!` ou `?` suivi d'un blanc ; une proposition est comparee au texte mot a mot **sans la majuscule initiale de chaque mot** (sans cette normalisation, 10 propositions de `ce1-texte-narratif` sortent du texte a tort : `Le soir` contre `le soir`).

| Mesure | `ce1-texte-narratif` (`-04`) | `ce1-phrases-types` (`-10`) | `ce1-accord-groupe-nominal` (`-15`) |
|---|---|---|---|
| Items (`TRV-CE1-7`, au moins 20) | 20 | 24 | 24 |
| Entree au catalogue (`grep -c "paquet: <nom>$"`) | 0 | 0 | 0 |
| Test de paquet (`PaquetCe1<Nom>Test`) | aucun | aucun | aucun |
| Textes supports, distincts | 20, 20 | sans texte | sans texte |
| `LON-CE1-3` et `-4` : mots du texte, phrases, mots par phrase | max 22, max 4, max 10 : **0 hors borne** (30, 4, 12) | - | - |
| `RCT-CE1-2` : propositions hors du texte | 0 | - | - |
| `TRV-CE1-5` : indice de plus de 12 mots ; indices distincts ; indice contenant une proposition (mot entier) | 0 ; 18 ; 0 | 0 ; 5 ; 0 | 0 ; 4 ; 0 |
| Majuscule seule sur la bonne reponse (6.1 point 1 de `nadia-prof`) | **2 items** (`Que fait Sara avant de couper la pomme ?`, `Que fait Nour en premier ?`) ; 4 autres items ont une majuscule seule sur un distracteur | 0 | 0 |

Le comptage de la majuscule retrouve les deux items que `nadia-prof` annonce : sa lecture de fichier et ma mesure concordent.

**Garde-fous qui voient deja ces paquets, sans entree de catalogue.** `AucunMotServiNeSEcarteDeSonAccentTest` lit "les paquets du repertoire" (son en-tete) et `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` derive son perimetre du balayage de `contenu/` : les trois fichiers y sont deja, verts dans la base.

**Garde-fous qui ne les voient pas.** `LON-CE1-3` et `-4` : `git grep -l "LON-CE1-3" -- '*.java'` rend **0 fichier** ; `nadia-prof` l'ecrit ("moi seule : aucun test ne la lit", section 7). `TRV-CE1-5`, `-6`, `-7`, `RCT-CE1-2`, `TPH`, `GNA` : aucun test n'existe pour ces trois paquets (le modele est `PaquetCe1VerbeSujetTest` ou `PaquetCe1ConjugaisonPresentTest`). Les nombres du tableau ci-dessus sont a l'heure d'aujourd'hui, **non gardes**.

**Ce que le branchement coute en tests, mesure.** Worktree jetable sur `544159d9`, trois entrees ajoutees a `catalogue.yaml` (une par paquet, titre et consigne **copies d'une entree existante** pour isoler le reste ; les textes de `nadia-prof` ne sont pas simules), `-pl adapters/adapter-content,bootstrap -am test` en entier :
`adapter-content` 1484 tests, **0 echec** ; `bootstrap` 371 tests, **3 echecs, tous dans `TexteAccentueExposeParLePortEntrantTest`** (`touteChaineExposeeParLeCatalogueReelEstInventoriee`, `lesNiveauxSansLettreAccentuableRestentVertsSansFigurerDansLInventaire`, `unNiveauNeufHorsDeLInventaireEtHorsExemptionFaitEchouerLeGardeFou`), message : `bandit-manchot-ce1-texte-narratif (chemin "id")`. Deux constats :

1. **Rien n'exige un test de paquet.** Trois paquets branches sans le moindre `Paquet*Test` laissent `adapter-content` entierement vert. Le test de paquet est un critere de story a ecrire (c'etait `T1` a s55), pas un automate.
2. **Les deux instances modifient les memes fichiers.** `catalogue.yaml` et l'inventaire de `TexteAccentueExposeParLePortEntrantTest` sont communs a A (un lot) et a A' (deux lots). Mesure sur `catalogue.yaml` : deux commits issus de `544159d9`, chacun ajoutant une entree en fin de fichier, puis `git merge-tree --write-tree` : **code 1, conflit** sur `adapters/adapter-content/src/main/resources/catalogue.yaml`. Je n'ai pas mesure l'inventaire de la classe de test. `ADR-0065` A2 traite le garde-fou de l'accent ; la condition 6 de `nadia-prof` traite le registre d'identifiants ; aucun texte ne traite ces deux fichiers-la. `Q-Y7`.

**Ce que la machine ne voit pas** (a `nadia-prof`, signale pour qu'on ne cherche pas un test) : qu'un indice "fait deduire au lieu de relire" (6.1 point 2, `Pense à ce qu'on fait avant de couper un fruit.`), l'exactitude des marques d'accord (`GNA-CE1-2`), qu'une famille de mots ne soit pas savante (`FAM-CE1-2`, palier 2).

## 3. Questions

Codes propres a ce fichier. Je ne corrige aucun critere ; chaque ligne est une question dont la reponse reecrit un critere ou le laisse tel quel.

| Code | A qui | Question |
|---|---|---|
| `Q-Y1` | `camille-po` | **`S57-ADR-0065-DEUX-INSTANCES`, critere 2** : "l'amendement et le test sont modifies dans un seul commit" n'est pas tenu a la lettre (2.4) : le test est inchange, deux commits. Gardez-vous la phrase, ou la reecrivez-vous en "le controle et l'amendement sont fusionnes avant le premier worktree d'un lot de A'" ? Les trois autres criteres sont verts |
| `Q-Y2` | `camille-po`, `nadia-prof` | **`VSJ-CE1-R1`** : son exemple (`Ce matin, Léa lit un livre.`), applique a un item, rend rouges `J1`, la liste d'ouvertures de `TRV-CE1-6` et le plancher de formes de sujet, en plus de la propriete 8 (2.1). L'exemple est-il une illustration, ou doit-il etre servi tel quel ? Dans le second cas, ces trois tests sont a amender dans le meme commit et le critere doit les nommer |
| `Q-Y3` | `camille-po` | **`S54-OBS-SCI-CONSIGNE`, critere 2** : `git diff` ne touche que les 12 lignes `enonce:` laisse passer une question reecrite sur sa ligne (2.2). Voulez-vous "chaque ancien enonce est egal a la phrase retiree suivie du nouveau" ? Et le critere 1 doit-il etre garde par un test (forme de substitution, 2.2) ? |
| `Q-Y4` | `sofia-sm`, `theo-archi` | **Rejeu d'`ART-CP-10`** : le jeu n'est pas servi, et le servir fait rougir 4 tests (2.3). Comment `nadia-prof` rejoue-t-elle, et quel commit etend l'inventaire et la liste d'`ADR-0051` ? Le critere de pli n'a aucun juge executable dans le reacteur : veut-on un juge pur sur le JSON de `pilote-q7.mjs`, ou le laisse-t-on a l'outil de poste ? |
| `Q-Y5` | `nadia-prof` | **`RDO-CP-R2`** : la propriete est "aucun texte ne designe une oeuvre absente", le critere cite deux expressions. Quelle liste ferme la propriete (`cette`, `celle-là`, `ces œuvres`) ? Sans elle, le test que j'ecrirai ne garde que les deux expressions nommees |
| `Q-Y6` | `camille-po`, `nadia-prof` | **Les trois lots de francais CE1** : sans story, rien n'est lisible. A l'ecriture, chaque story nomme (a) le test de paquet et son module, (b) la regle de comptage des mots et des phrases pour `LON-CE1-3` et `-4` : la mienne donne 22, 4 et 10 sur `-04`, une autre regle peut en donner d'autres, (c) la propriete de la majuscule de 6.1 comme commande (2 items aujourd'hui). Quelle regle de comptage fait foi ? |
| `Q-Y7` | `sofia-sm`, `theo-archi` | **Fichiers partages entre A et A'** : `catalogue.yaml` (conflit mesure, 2.5) et l'inventaire de `TexteAccentueExposeParLePortEntrantTest`. Ordre de fusion, auteur de la resolution, et qui verifie que la resolution n'a pas perdu une entree ? Aucun texte du depot ne le dit |

## 4. Ce que cette lecture n'a pas fait

- **Aucune lecture pour les cinq lots du palier 2** (`FRA-CE1-05`, `-06`, `-07`, `-11`, `-24`) ni pour `G4` : ni story ni arbitrage. Cette lecture se complete par annotation datee a l'ouverture de chaque palier ; je n'en ecris pas d'avance.
- **Aucune mutation de livraison** : rien n'est livre. Mutations nommees pour la livraison : les trois items de `VSJ-CE1-R1` remis dans leur forme actuelle (le test de comptage doit rougir) ; la phrase retiree remise dans un item (le test de substitution doit rougir) ; la correction d'`ART-CP-10` retiree (le juge de pli doit rougir) ; `celle-ci` remis dans un enonce (le test de `RDO-CP-R2`) ; un texte de `ce1-texte-narratif` porte a 31 mots puis a 5 phrases (le garde-fou de `LON-CE1-3` et `-4` doit rougir, s'il est ecrit).
- **Non rejoues** : les cotes de pli (656, 676, 568) ; `pilote-q7.mjs` (Node et Chrome existent sur le poste, mais le jeu n'est pas servi) ; `N1` a `N6` de la fiche d'`ART-CP-10`, que je n'ai pas lue.
- **Non simule** : les textes de titre et de consigne des trois lots (copies d'une entree existante) ; l'inventaire de la classe de test dans le conflit de fusion ; les entrees de catalogue de `nadia-prof`.
- Les simulations de la section 2 ne couvrent que les fichiers et tests nommes. Le reacteur `test` complet n'a tourne que sur l'arbre du critere 4 de `S54-OBS-SCI-CONSIGNE` et sur celui des trois entrees ; les autres ont lance les classes nommees.
- `-am` sur toute mesure partielle de ce fichier ; chaque total cite porte son perimetre. CI distante : non lue. Etape `mvn -B -pl bootstrap -am package` : non lancee.
- Aucun Done n'est prononce. Le Done technique est celui de la CI, tous les jobs ; le Done pedagogique est a `nadia-prof`.

## 5. Obstacles de qualite

Aucun test instable observe, aucune CI cassee sur la base. Les trous de testabilite sont les questions `Q-Y1` a `Q-Y7` ; aucun n'est une cause architecturale que je tranche, ceux qui le sont (`Q-Y4`, `Q-Y7`) sont routes a `sofia-sm` et `theo-archi`.

## 6. Jobs lances pour ce commit

Le commit ne modifie que ce fichier. Resultats lus avant le commit, avec le sujet et la liste de fichiers prevus :

| Job de `ci.yml` | Lance | Sortie |
|---|---|---|
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-prefixes.sh` avec le sujet du commit en entree | trois sorties 0, "Tous les commits portent un prefixe d'agent connu." Etape du garde-fou de l'accent : voir le compte rendu de la tache (lancee apres le commit, sur `HEAD^!`) |
| `chemins` | `verifier-chemins-test.sh` ; `verifier-chemins.sh` avec la liste des fichiers suivis plus ce fichier | deux sorties 0, "Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies." |
| `outils` | non lance | aucun outil modifie ; je ne deduis pas son resultat |
| `build` | `clean verify` sur la base `544159d9` (section 1) ; etape de package non lancee | `BUILD SUCCESS` sur la base ; **non relance apres ce commit**, qui ne change ni code ni contenu |

Mesure en plus : `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (`-pl bootstrap -am`, 10 tests, 0 echec), parce que ce fichier cite des identifiants de condition.
