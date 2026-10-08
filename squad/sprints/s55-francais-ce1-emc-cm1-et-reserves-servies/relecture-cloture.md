# Relecture de cloture s55 - relecteur-pedagogique

- **Auteur** : `relecteur-pedagogique`
- **Date** : 2026-10-08
- **Arbre** : worktree `s55-relecteur-cloture`, branche issue de `round-two` a `af8b326e` (`git log -1 --format=%h` au depart).
  `bash outils/verifier-lignee.sh` : "descend de la lignee de `round-two`".
- **Plage relue** : `git diff --stat b0406cfb..af8b326e` (78 fichiers : planning, avis, lecture de testabilite, trois audits de
  `nadia-prof`, huit rapports de dev (dont celui de `sofia-sm` en casquette developpeuse), `DEC-0115` et ses amendements A.1 a A.13, six fiches de conformite, backlog "Sprint 55",
  outils A7 et A8, garde-fous de provenance, tests et YAML de paquet). `b0406cfb` est le dernier commit avant le planning de s55.
- **Aucune logique de code editee.** Aucun texte lu par l'enfant modifie. Aucun push, rebase ni fusion. Aucun Done prononce.
- **Modele de ce fichier** : `squad/sprints/s53-relecture-paquets-session-contenu/relecture-cloture.md`. Il n'existe pas de
  relecture de cloture pour s54 dans le depot.

## 1. Defauts corriges (texte seul)

Trois commits `[REL]` : un pour le fichier de documentation (C1), deux pour les commentaires de fichiers de code ou de contenu (C2 a C6)
(`git log --grep='^\[REL\]' --format='%h %s' af8b326e..HEAD`).

| # | Fichier | Defaut | Correction | Preuve |
|---|---|---|---|---|
| C1 | `rapport-camille-criteres.md`, paragraphe d'ouverture | "raccourci d outillage" : apostrophe manquante | "raccourci d'outillage" | relecture de la ligne ; balayage des doublons et de la ponctuation sur les lignes ajoutees du sprint : aucun autre cas |
| C2 | `adapters/adapter-content/src/main/resources/contenu/cp-emc-exclusion.yaml`, en-tete | L'en-tete dit que la consigne du jeu est "Lis bien, puis dis si c'est une moquerie qui se repete, ..." (`EXC-R1`, `catalogue.yaml`) et que le paquet compte "5 reponses distinctes sur 11 items". Depuis `EXC-R15` et `EXC-R16` la consigne du catalogue est une autre, les items 1 et 3 nomment la mise a l'ecart et le paquet compte 6 bonnes reponses distinctes. Aucun paragraphe de l'en-tete ne datait ces changements | Paragraphe "S55" ajoute avant `items:`, en ASCII (aucune des chaines accentuees que les criteres de `ARBITRAGE-QC4-s55.md` section 6 comptent par `grep`) : quatre points (noms de categorie des items 1 et 3, consigne, indice de l'item 4, titre inchange) | Bonnes reponses distinctes recomptees sur le fichier : 6 (mise a l'ecart, moquerie, accident isole, surveillant de la cour, maitresse, adulte de la cantine) contre 5 sur `b0406cfb`. Consigne lue dans `catalogue.yaml` |
| C3 | `cm1-emc-respect-differences.yaml`, paragraphe `EMC-5` de l'en-tete (nouvel item 4, A9) | Voir la section 2 ci-dessous : le paragraphe est exact sur l'item 4 et sur l'item 8, mais comptait "quatre distracteurs partageaient l'amorce" sans dire que l'un des quatre a un mot d'ecart, et finissait sur "A rejuger" alors que `nadia-prof` a accepte l'ecart (`audit-nadia-b-emc.md`, P1) | "moule" au lieu de "amorce" pour les quatre, et mention du quatrieme ("Prenom, parce que c'est", item 5). "A rejuger" remplace par le renvoi a la fiche, section 3 | `rapport-lea-dev-emc.md` section 4 (E1) nomme les quatre ; relecture des distracteurs du paquet ; `FICHE-bandit-manchot-respect-differences-cm1.md` section 3 : "accepte" |
| C4 | `bootstrap/src/test/java/com/recreo/bootstrap/catalogue/TexteAccentueExposeParLePortEntrantTest.java`, commentaire de la consigne du jeu des familles d'oeuvres | "S54-BR-ART-CP-10 (s54) : consigne neuve" decrit un texte qui n'est plus celui de la ligne : `S54-ART-RES-R1` l'a remplace a s55. `rapport-lea-dev.md` ("Ecarts") l'avait releve et laisse | "... remplacee a s55 par celle-ci (S54-ART-RES-R1), deja inventoriee pour d'autres jeux" | `git diff b0406cfb..af8b326e` sur la ligne ; la chaine inventoriee n'est pas touchee |
| C5 | `outils/mvn-jdk21.sh`, en-tete (ligne 26) | "sa premiere ligne dit ce que ce JDK rapporte" : depuis `8541cffc` (A7) le script lit la premiere ligne qui contient `version "`, pas la premiere ligne | Phrase reecrite : la premiere ligne de la sortie qui contient `version "`, avec la mention de la ligne "Picked up JAVA_TOOL_OPTIONS" | Lecture de la ligne 80 du script (`grep -m1 'version "'`) |
| C6 | `outils/rapport-tokens.py`, texte d'aide de `--sprint` | `--sprint` est obligatoire depuis `69d9d6a2` (A8, code 2 sans lui) ; `--help` ne le disait pas | "identifiant du sprint, obligatoire, par exemple s03" | Lecture de `main` (refus ligne 463 environ) ; aucune autre ligne du fichier ne promet encore "tous les sprints" (`grep`) |

## 2. Paragraphe `EMC-5` de `cm1-emc-respect-differences` (nouvel item 4)

Relu contre l'item 4 du YAML, l'arbitrage de l'instance B (`ARBITRAGE-PREALABLE-s55-instance-b-emc-cm1.md`, sections 2.1 a 2.3), le
rapport de `lea-dev` et le test `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`.

- **Exact** : jeu de cartes et non relais ; motif de la personne seule ; aucune capacite attribuee a l'enfant en fauteuil (`EMC-4`) ;
  renvois "section 2.1", "section 2.3" et "sections 2.2, 2.3" (ces sections existent et portent ces items : 4, 8, puis 7 et 8) ;
  les indices des items 7 et 8 sont bien ceux de `nadia-prof` ; l'ecart "cette partie" au lieu de "la partie" est celui du diff ;
  `ADR-0086` clause 2 est bien la regle qui rougissait.
- **Exact, a l'unite pres** : le moule "Prenom, parce que la" est porte par `Malo` (item 1), `Tom` avec "la partie" et `Lea` (item 4)
  et rejoint par `Lea, parce que c'est` (item 5), a un mot d'ecart. C'est le compte de `rapport-lea-dev-emc.md` section 4 (E1).
  Corrige en C3.
- **Non verifie par moi** : que le garde-fou rougirait avec "la partie". Je n'ai pas rejoue la mutation ; le message cite par `lea-dev`
  (`4 distracteurs sur 22 ... "lea parce que la"`) est dans son rapport, pas relance.

## 3. Points a trancher ou a router (aucune correction appliquee)

| # | Fichier et ligne | Constat | A qui |
|---|---|---|---|
| R1 | `DEC-0115` sections 0.1 (lignes 25 a 37), 0.5, 5 decision 1 (ligne 189) et A.1 (lignes 294 a 304) ; `planning.md` lignes 27 et 395 ; backlog, entree `S55-ART-CP-10-MECANIQUE` ("Financement : message de l'agent qui m'a lance") | Un message de l'agent lanceur est etiquete "forme 3" de `DEC-0062` alors qu'il n'emploie pas la formule fixe `recu de MASTER, sans source dans le depot`. Les textes sont donnes tels que recus ("CI verte sur fbe07d79", la phrase du financement en majuscules, la phrase de A.1) mais la `DEC` dit elle-meme ne pas savoir lesquels des mots sont ceux de l'utilisatrice. Ce n'est aucune des trois formes. Meme constat que `R2` de la relecture de s53 : deuxieme occurrence. En outre le financement de la mecanique (section 5, decision 1) n'a que ce message pour fondement : la borne de la forme 3 ("ne fonde aucune `DEC`") est en cause. La `DEC` le reconnait (statut `Proposee`, section 0.5, question `Q-U3`). Je ne choisis pas entre la requalifier ou la remplacer par les mots de l'utilisatrice | `camille-po` |
| R2 | `planning.md` lignes 154, 481, 483, 494, 496 | Le planning porte encore quatre criteres que `rapport-camille-criteres.md` (section 3) et le backlog declarent insatisfiables ou remplaces : `grep -c "mise . l.cart"` sur le test (rend 0, `rapport-marc-dev-exc.md` E1), `g.teau` (locale), "42 `@Test`" (41), "cliquet de 12 a 0" (la forme verte est une ligne retiree). Le backlog dit que ses lignes l'emportent ; le planning n'est pas aligne | `sofia-sm` (`review.md`) |
| R3 | `DEC-0115` A.3 (Q-U4) et A.2 ; backlog, tableau des tarifs de A ; `ARBITRAGE-COMPLEMENT-s55-instance-a.md` section 4 | A.3 laisse "non alloues" les 0,25 restants de A et pose Q-U4 a l'utilisatrice. Le complement de `nadia-prof` (section 4) les consomme (`A10`) : "A passe de 3,75 a 4,0, reserve 0". Aucun amendement de la `DEC` ne reprend ce fait ; le backlog garde "A 3,75 / 4,0" | `camille-po` |
| R4 | `EMC-CM1-R8` et `EMC-CM1-R9` (fiches de `cm1-emc-respect-differences` et `cm1-emc-principes-republique`) | Deux reserves ouvertes portent des textes de remplacement exacts (indices des items 10 et 11, indice de l'item 3, citation de 2018 dans l'en-tete de `cm1-emc-principes-republique`, qui omet "dans un but humanitaire" et "sur le territoire national"). Je n'ai pas applique la citation : c'est une levee de reserve, rejeu a 0,25 a planifier. L'en-tete de `cm1-emc-principes-republique.yaml` ligne 50 porte donc encore la forme incomplete | `camille-po` (planification), `lea-dev` |
| R5 | `CLAUDE.md` ligne 167 | L'exemple de commande portable `python outils/rapport-tokens.py` sort desormais en code 2 sans `--sprint`. L'exemple illustre la regle de portabilite et ne la contredit pas ; il decrit pourtant une commande qui refuse. `CLAUDE.md` est un fichier commun, je ne le modifie pas | `sofia-sm` |
| R6 | `DEC-0115` "Decisions prises", point 5 | Compte `ART-RES` parmi les cinq postes non engages et en premier report ; le tableau de la section 4 et A.2 le disent engage, sans report. A.7 liste les lignes remplacees mais pas ce point | `camille-po` |

## 4. Verifications faites, sans defaut

- **Caracteres** : balayage des lignes ajoutees de tout le diff (`git diff -U0 b0406cfb..af8b326e`) pour tiret demi-cadratin ou cadratin, guillemets
  typographiques simples ou doubles, plages d'emoji, fleches et symboles : 0 occurrence. Les guillemets francais `« »` ne figurent que dans les
  citations mot pour mot de texte d'enfant (fiches, arbitrages), conformes a leur YAML.
- **Chemins de poste** : motifs `[A-Za-z]:\`, `/c/`, `/home/`, `/Users/`, `C:/` sur les memes lignes : 0 occurrence.
- **References de decision et de fichier** : script de lecture seule sur les 78 fichiers du diff. Tous les `DEC-NNNN` et `ADR-NNNN` cites existent
  (`git ls-files`). Tous les noms de fichier `.md`, `.yaml`, `.java`, `.sh`, `.py` cites existent, hors les cas suivants, qui sont des abreviations ou des
  noms coupes en fin de ligne dans un commentaire : `instance-B.md` (suite de `ARBITRAGE-PREALABLE-s55-instance-b-emc-cm1.md`),
  `verdicts-nadia-instance-B.md` (ligne 92 de `cp-emc-exclusion.yaml`, ligne d'avant s55 : nom nu, le fichier existe dans le dossier de s32 cite deux lignes plus haut), `lessons.md`
  (le planning et la lecture de testabilite disent eux-memes qu'il n'existe pas). Les chemins complets cites existent tous (`git ls-files`), hors
  `bootstrap/target/recreo.jar` (artefact de build) et les elisions `...` des avis. `ADR-0045` porte bien un paragraphe "Prerequis de mesure" (ligne 116), cite par la
  Javadoc de `ProvenanceDesClassesAmontEstLeDepotTest` (d'avant s55).
- **Sections citees** : `ARBITRAGE-PREALABLE-s55-instance-a` (2.1, 2.3, 3.2, 3.4, 3.5, 5, 6), `-instance-b` (1.2, 2.1 a 2.3, 3.2, 3.3, 3.4, 4.1, 4.2, 5, 6),
  `ARBITRAGE-QC4-s55` (1, 5, 6, 7), `ARBITRAGE-PREALABLE-s48-francais-ce1` (5.3, 5.5), `ADR-0104` (decisions 1 a 8) : toutes existent comme titres de section.
- **Hashes** : tous les hashes de 7 a 12 caracteres cites dans les `.md` du diff resolvent par `git cat-file -t`, sauf `c2c82ac` (`backlog.md` ligne
  12189) et `dcbcd65a` (`DEC-index.md` lignes 89 a 91) : lignes de s5 a s32, hors du diff de s55 (`git blame`), non traitees.
- **Chiffres des `DEC` et des rapports** : arithmetique de `DEC-0115` relue (3,75 + 3,75 = 7,5 ; 7,5 + 2,5 = 10,0 ; 4,25 x 2 = 8,5 ; 4,0 - 0,5 - 0,5 - 2,5 = 0,5 ;
  6,75 + 2,0 = 8,75 ; 13 lots a 3 par sprint = 5 sprints ; 137/182 = 75,27 %, 141/182 = 77,47 %, 142/182 = 78,02 %). Table de `AucunSigneDoubleNEstDetachableDeSonMotTest` :
  somme des 16 entrees = 490 = `DETTE_TOTALE`. Comptes d'`@Test` des classes neuves : `PartieControllerPropositionsEnImageTest` 5,
  `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest` 10, `UnItemAPropositionsEnImageTientSesTroisInvariantsTest` 7 (`grep -cE '^\s*@(Test|ParameterizedTest)\s*$'`),
  comme `rapport-lea-dev-mecanique.md`. `cm1-grands-nombres.yaml` : 28 `indice`, 0 avec espace ordinaire avant `: ; ! ?`, 12 avec U+00A0.
  `ce1-classes-de-mots` : 5 adjectifs (2 avant le nom, 2 apres, 1 attribut) comme l'en-tete.
- **Javadoc de `ProvenanceDesClassesAmontEstLeDepotTest`** (trois copies, `adapter-content`, `adapter-web`, `application`) : les versions citees sont celles du poste
  (`maven-archiver` 3.6.5 est la `mavenArchiverVersion` de `maven-jar-plugin` 3.5.0 ; `maven-jar-plugin.version` vaut 3.5.0 dans
  `spring-boot-dependencies` 4.1.0, lu dans `~/.m2`). La Javadoc de la methode des trois jars correspond aux trois appels de `jar(...)`. La Javadoc de classe et le message d'assertion decrivent ce que
  fait le code (repli sur l'horodatage du fichier jar quand toutes les entrees portent le meme horodatage).
- **Outils A7 et A8** : le commentaire du cas neuf de `mvn-jdk21-test.sh` et le docstring de `rapport-tokens-journal-test.py` (cas 6) correspondent au code ; le
  message de refus de `rapport-tokens.py` nomme `--sprint`. Aucun commentaire ne paraphrase le code. Seul le commentaire de `mvn-jdk21.sh` etait perime (C5).
- **Provenance (`DEC-0062`)** : une occurrence de `MASTER` dans l'arbre relu (`backlog.md` ligne 19568, du 2026-10-06, formule correcte). Les messages de l'agent lanceur de s55 :
  voir R1.
- **Commentaires qui paraphrasent le code** : balayage des Javadoc des classes neuves (`UnItemAPropositionsEnImageTientSesTroisInvariantsTest`, `PaquetCe1AccordSujetVerbeTest`,
  `PaquetCe1ClassesDeMotsTest`, `PaquetCm1Emc*Test`, `ProvenanceDesClassesAmontEstLeDepotTest`) : elles disent l'invariant garde et sa raison, pas l'instruction suivante. Aucun cas releve.

## 5. Ce que je n'ai pas verifie

- Le sens pedagogique, les textes lus par l'enfant (consignes, enonces, indices, titres) et les verdicts : `nadia-prof`. Un exemple vu en passant et laisse a elle : le titre du jeu d'exclusion
  du CP ("Une moquerie qui se repete") ne nomme plus que l'une des deux categories de classement ; `audit-nadia-b-exc.md` dit "pas de reserve".
- `planning.md`, `avis-faisabilite.md`, `lecture-testabilite.md` : balayages mecaniques (caracteres, chemins, references, hashes) et lecture des passages cites ci-dessus. Pas de relecture
  integrale de ces trois fichiers (environ 140 Ko).
- Les six fiches de conformite : balayages mecaniques et references. Pas de relecture de leur fond.
- `docs/backlog/backlog.md` : seules les lignes ajoutees depuis `b0406cfb` ont ete lues ; l'etat des stories de s55 a la cloture ("OUVERTE, engagee") n'est pas mis a jour dans ce que j'ai lu ;
  c'est a `camille-po` et `sofia-sm`.
- Les commandes citees par les autres agents (`grep`, `awk`, `git log`) : relues, pas relancees, sauf celles de la section 1 et le recomptage de `cm1-grands-nombres`.
- Que le garde-fou d'amorce rougirait avec "la partie" (section 2).
- Job `chemins`, `prefixes` et `outils` de `.github/workflows/ci.yml` : voir la section 6.

## 6. Mesure

Commande, lancee a la racine du worktree apres mes trois commits `[REL]` et apres l'ajout de ce fichier au suivi :
`bash outils/mvn-jdk21.sh -B clean verify` (reacteur complet, sans `-pl`). Code de sortie 0, `BUILD SUCCESS`. L'enveloppe rapporte le JDK
21.0.10 ; la version de la machine virtuelle des tests n'a pas ete relue dans les `TEST-*.xml` (`ADR-0071`). Une premiere execution identique avant le
troisieme commit `[REL]` (commentaire de `cm1-emc-respect-differences.yaml` seul) avait rendu les memes comptes.

```
Tests run: 120, Failures: 0, Errors: 0, Skipped: 0   (recreo-domain)
Tests run: 18, Failures: 0, Errors: 0, Skipped: 0    (recreo-application)
Tests run: 407, Failures: 0, Errors: 0, Skipped: 0   (recreo-adapter-web)
Tests run: 1419, Failures: 0, Errors: 0, Skipped: 0  (recreo-adapter-content)
Tests run: 333, Failures: 0, Errors: 0, Skipped: 0   (recreo-bootstrap)
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0     (Failsafe, bootstrap)
```

Le garde-fou de tableau Markdown (`ADR-0087`, `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`) a ete relance seul apres l'ajout de cette section, ce fichier suivi (`-pl bootstrap -am`, une classe) : 5 tests, 0 echec.

Jobs de `.github/workflows/ci.yml` lances par moi, sur l'arbre qui porte ce fichier :

- `build` : `mvn -B clean verify` ci-dessus. L'etape "Verifier la presence du build Maven" et l'etape "Package du fat jar" ne sont pas lancees par moi.
- `chemins` : `verifier-chemins-test.sh` (0) et `verifier-chemins.sh` sur `git ls-files` hors le test (0).
- `prefixes` : `verifier-prefixes-test.sh` (0), `commit-atteignable-test.sh` (0), `verifier-prefixes.sh` sur les sujets de `af8b326e..HEAD` et du sujet de ce fichier (0).
  `commit-atteignable.sh` sur une plage de PR n'est pas lance.
- `outils` : balayage des trois motifs de la CI : 10 juges, 10 a 0, 0 rouge.

Le workflow lui-meme n'a pas tourne sur GitHub. Aucun Done n'est prononce ici.
