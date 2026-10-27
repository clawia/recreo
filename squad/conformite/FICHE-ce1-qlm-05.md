# Fiche de conformite - ce1-qlm-05

- **Mecanique** : `bandit-manchot`, `type: question`, sans texte support, comme les paquets d'EMC CE1 ; aucune modification de
  la mecanique, aucune illustration (`docs/backlog/backlog.md`, annotation du 2026-10-25, cinquieme, *"Ce qui est commun aux
  neuf stories"*).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-eau-trois-etats.yaml` (12 items, lus par
  `grep -c "bonneReponse:"`), ecrit par `marc-dev` (instance neuve, `S57-BR-QLM-CE1`).
- **Entree de catalogue** : aucune. `QC3` : un paquet n'entre au catalogue qu'apres un Done pedagogique ou un refus leve ;
  `grep -c "ce1-qlm" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 0 le 2026-10-27.
- **Ligne declaree** : `QLM-CE1-05`. **Story** : `S57-BR-QLM-CE1-05` (`docs/backlog/backlog.md`). **Decision** : `DEC-0120` A.227, A.250 a A.255,
  A.268 a A.280.
- **Competence programme visee** : `squad/programme/questionner-le-monde/competences-ce1.md`, `QLM-CE1-05`, *"Reconnaitre l'eau sous ses trois etats (glace, eau liquide, vapeur d'eau) et relier la fonte et le gel a la temperature."*
- **Conditions opposables** : celles de la story `S57-BR-QLM-CE1-05` et les reperes `QC1` a `QC9` du bloc commun, **non recopiees ici** :
  la story fait foi, ses mots sont ceux de `nadia-prof`. Plafond de la ligne : `couvert-valide`.
- **Statut global** : **en attente d'audit a l'ecran**. Fiche ouverte par `camille-po` le 2026-10-27 (`DEC-0120` A.280, reponse
  de l'utilisatrice a `Q2` : nom `FICHE-ce1-qlm-<lot>.md`). Aucun verdict. La ligne `QLM-CE1-05` reste `non couvert` dans la
  matrice, qui ne bouge qu'au verdict de `nadia-prof`.
- **Derniere mise a jour** : 2026-10-27.

---

## Competence visee

| Matiere | Niveau | Competence | Reference au referentiel |
|---|---|---|---|
| questionner le monde | CE1 (cycle 2) | Reconnaitre l'eau sous ses trois etats (glace, eau liquide, vapeur d'eau) et relier la fonte et le gel a la temperature. | `squad/programme/questionner-le-monde/competences-ce1.md`, `QLM-CE1-05` |

## Criteres attendus - camille-po

Les criteres d'acceptation de cette fiche sont ceux de la story `S57-BR-QLM-CE1-05` (`docs/backlog/backlog.md`, titre *"L'eau sous ses trois etats, la fonte et le gel relies a la temperature"*) et
les reperes `QC1` a `QC9` du bloc commun. Je ne les recopie pas : une copie derive de la source. Je n'ecris aucune mesure ici.

- [ ] Criteres de la story `S57-BR-QLM-CE1-05`, numerotes dans le backlog.
- [ ] `QC1` a `QC9` du bloc commun (paquet, ligne declaree, branchement, test du paquet, densite, conditions negatives,
  lecture avant `verify`, Done technique, ce que `nadia-prof` garde seule).
- [ ] Texte servi a l'enfant integralement accentue, orthographe et ponctuation irreprochables (`DEC-0002`).

## Parcours du lot, faits lus au depot

Source : `planning.md` du sprint s57, amendement du 2026-10-27 15:40, section 1 ; chaque SHA est un commit de `round-two`.

| Geste | Auteur | Commit | Fichier |
|---|---|---|---|
| premier jet | `marc-dev` | `e6694d47` | `ce1-qlm-eau-trois-etats.yaml` |
| lecture du premier jet | `nadia-prof` | `3131431f` | `lecture-nadia-premier-jet-ce1-qlm-eau-trois-etats.md` |
| corrections | `marc-dev` | `e0875968` | `ce1-qlm-eau-trois-etats.yaml` |
| relecture des corrections (seconde lecture) | `nadia-prof` | `a463e55e` | `relecture-nadia-corrections-ce1-qlm-eau-trois-etats.md` |

Les fichiers de `nadia-prof` sont dans `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/`. Cette fiche n'en reprend aucun
verdict : la lecture et la relecture sont des gestes avant `verify`, pas le Done pedagogique.

## Etat de la porte technique

**Ce qu'un fichier du depot prouve, et rien de plus.**

- **Aucun `mvn verify` complet n'a ete lance pour ce lot** (`planning.md`, amendement du 2026-10-27 15:40, section 1 : fait donne
  par l'agent lanceur, non verifie ; les relectures ecrivent *"aucun autre job de `ci.yml` lance"*).
- **Regle en vigueur** (`DEC-0120` A.280, reponse de l'utilisatrice a `Q1`, option `O1`) : un `mvn verify` complet **avant** l'audit
  a l'ecran de `nadia-prof` de ce lot ; passage CI complet (`prefixes`, `chemins`, `outils`, `build`) a la cloture du palier ; toute
  annonce de porte nomme les jobs lances.
- Premier `verify` complet du lot : **a faire**, resultat a inscrire ici par qui le lance, avec les jobs nommes.

## Ce dont `nadia-prof` reste la seule garde sur cette ligne

Ce qu'aucun test ne tient (`QC9`, `arbitrage-nadia-palier-5-qlm-9-lots.md`, section 6) : l'exactitude de chaque fait contre la source
ecrite en commentaire du paquet, `NRC-CE1-1` sur le sujet de l'item, `CYC-1` contre les paquets voisins, `TRV-CE1-6`, l'operation
mentale de `FER-3`, et les conditions propres a la ligne que la story nomme.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Verdict du 2026-10-27 (`nadia-prof`, s57, palier 5, `P2`, lot 5 sur 9) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce sur le paquet `ce1-qlm-eau-trois-etats`** tel que commite a `e0875968` (contenu inchange
depuis : `fe34a485` ne touche que le test). `camille-po` peut passer `QLM-CE1-05` a `couvert-valide`, sans mention de
couverture partielle, **une fois l'entree de catalogue fusionnee** (`QC3`, etape distincte que ce verdict n'ecrit pas) et
son titre lu par moi (section 5). Trois reserves neuves, non bloquantes, a faire en stories : `ETE-CE1-R1`, `ETE-CE1-R2`,
`ETE-CE1-R3` (section 6).

#### 1. Ce que j'ai fait

- **Arbre** : worktree de la branche `s57-p5-nadia-audit-05`, base `round-two` a `d6f2e007`. `git diff --name-only
  90c421a7 d6f2e007` ne rend que des `.md`.
- **Done technique, lu et non lance par moi** : `mvn -B clean verify` complet, reacteur entier, `BUILD SUCCESS` a
  `90c421a7`, 3624 tests dont 6 d'integration, 0 echec, mesure par `sofia-sm` (`planning.md`, amendement du 2026-10-27
  21:34, section 3). Ce `verify` contient le paquet tel que joue. Job `build` seul, etape `verify` seule ; `prefixes`,
  `chemins` et `outils` non lances en entier : la porte technique complete n'est pas annoncee, elle est a la cloture du
  palier (`DEC-0120` A.280 point 2).
- **Jar** : le jar du depot servi par l'agent lanceur, copie en lecture seule, SHA-256
  `920bb486520f05be747a362bb63bc964cf66a4b994da8625d43566d03510cdb9`. Paquet et `catalogue.yaml` qu'il embarque identiques
  octet pour octet a ceux de `d6f2e007` (SHA-256 compares). JDK 21.0.10 de la toolchain declaree. Port **18105**
  (`--server.port`, la forme de `JarRecreoIT`) : *"Tomcat started on port 18105"*, PID 21636, **arrete par ce PID seul**,
  port libre ensuite.
- **Comment le paquet a ete servi sans entree de catalogue.** Le paquet n'est pas au catalogue (`QC3`) et le jar ne sert
  que les jeux du catalogue. J'ai lance le jar par son `PropertiesLauncher` avec un `loader.path` vers un dossier de travail
  hors du depot, qui porte une **copie** de `catalogue.yaml` plus une entree locale (`bandit-manchot-eau-trois-etats-ce1-audit`,
  consigne *"Lis bien, puis choisis la bonne réponse."*, `nombreDeManches: 10`). **Rien n'est ecrit au catalogue du depot,
  rien dans le jar.** Le titre de cette entree est un libelle de travail, jamais lu par moi comme celui de l'enfant.
- **Par les routes servies** (`POST /parties/...`, puis `/coups`, en-tete htmx, session par cookie) : 6 parties de dix
  manches jouees en entier, transcrites ecran par ecran, **les 12 items servis** ; une partie ou je me trompe deux fois a
  chaque manche (20 erreurs), une partie mixte (18 erreurs), quatre parties justes au premier appui. Puis des parties de
  plus, une erreur par manche, jusqu'a obtenir l'ecran apres erreur de chacun des 12 items. **0 ecart** au fichier sur 98
  ecrans (propositions servies, indice servi), **aucun indice avant la premiere erreur**. Bonne reponse en position 1, 2,
  3 : 19, 19, 22 fois sur 60 premiers ecrans de manche.
- **Rendu a 744 x 551, apres une erreur**, les 12 items : methode de `P3`, s25 (page servie, fragment de la manche jouee
  insere dans `#jeu`, Chrome sans tete, `getBoundingClientRect()`), fenetre reglee pour que `innerWidth` x `innerHeight`
  vaille 744 x 551, verifie par le script ; captures a 744 x 551, regardees pour les items 2, 4, 5, 6, 7, 9, 11 et 12.
- **Ce que je n'ai pas joue** : un autre navigateur, une autre largeur, une autre police, un appareil reel, un lecteur
  d'ecran ; l'entree de menu du jeu (aucune entree reelle n'existe).

#### 2. Ancrage au programme

Le paquet travaille **cette** ligne et pas une voisine plus facile. Tache vapeur (6 items) : l'enfant dit ce que devient
l'eau qu'il ne voit plus (le linge, la flaque, le sol de la cour, la casserole), ou de quoi sont faits la buee et le
brouillard. Tache temperature (6 items) : il prevoit ce que le froid ou la chaleur fait a de la glace et de l'eau liquide
ensemble, ou a de la glace seule sous 0 degre. Les cinq elements du libelle sont en bonne reponse (*vapeur d'eau*, *eau
liquide*, *glace*, *la fonte*, *le gel*). Aucun item ne demande *solide ou liquide* ni le nom de la fonte : la frontiere
avec `cp-qlm-etats-eau` tient (`NRC-CE1-1`), et aucun mot du CM1 n'est ecrit.

#### 3. Contenu, joue

| Point | Constat a l'ecran |
|---|---|
| Exactitude, contre l'instruction 3.5 et `cm1-sciences-etats-matiere` (`QMT-CE1-2`) | les 12 bonnes reponses sont justes, relues une a une a l'ecran. La buee et le brouillard sont de l'eau liquide ; l'eau du linge, de la flaque de la cour, du sol de la cour et de la casserole devient de la vapeur d'eau, trois fois sans bouillir ; sous 0 degre l'eau gele et la glace reste de la glace, au soleil la glace fond. **Tenu** |
| `ETE-CE1-2`, la buee n'est pas de la vapeur | aucun enonce, aucun indice, aucune bonne reponse ne presente la buee, le brouillard, le nuage ou la fumee comme de la vapeur ; *de la vapeur d'eau* est le distracteur des items 3 et 10. Seul nombre ecrit : *0 degré*, dans les items 5, 7 et 11. **Tenu** |
| Une seule reponse acceptable | sur chaque item, joue en me trompant sur les deux distracteurs : aucun n'est vrai dans la langue de l'enfant. Les trois items corriges a la lecture (4, 8, 12) tiennent a l'ecran : plus de brouillard sous le soleil, plus de nuage au-dessus de la casserole, plus de glacons qui disparaissent. **Tenu** |
| *Rien ne change* | bonne reponse de l'item 11, distracteur des items 2, 5 et 12. L'enfant qui l'ecarte toujours ne se trompe qu'a l'item 11, et l'ecarter ne resout aucun des trois autres : il reste deux propositions que la temperature departage. Je l'avais dit a regarder a l'ecran : **ce n'est pas un raccourci** |
| *De la glace et de l'eau* (items 7, 9) | joue : l'ecarter laisse *seulement de la glace* contre *seulement de l'eau liquide*, que la temperature decide. **Tenu** |
| Longueur comme indice | la bonne reponse est la plus longue dans 6 items sur 12 ; aux items 3 et 10, *la plus longue* (*de la vapeur d'eau*) est fausse. Choisir la plus longue ne gagne pas la partie. **Tenu** |
| Retour apres erreur | *"Ce n'est pas ça. Essaie encore !"*, l'indice de l'item, la proposition essayee barree ; apres deux erreurs il ne reste qu'une proposition, l'enfant bloque avance toujours. Les indices de la tache temperature (*"Compare un verre au soleil et un verre au congélateur."*) menent a la reponse sans la dire. **Ceux de la tache vapeur ne l'aident pas** : `ETE-CE1-R3` |
| `TRV-CE1-6`, ton et contexte | aucun prenom, aucun possessif, rien qui suppose un foyer ; *la cour*, *la mare*, *la route*, *le congélateur*, *la douche*. Rien a relever |
| Lisibilite CE1 | enonces de deux ou trois phrases courtes, la question a la fin ; propositions de 2 a 6 mots. Lisibles par un enfant de CE1. Deux defauts de mise en page, a l'ecran seulement : `ETE-CE1-R1`, `ETE-CE1-R2` |
| Accents | servis a l'ecran (*glaçon*, *congélateur*, *gèle*, *buée*, *réchauffe*, *degré*, *disparaît*), espace insecable avant *?* lue dans le HTML servi. L'orthographe et la ponctuation sont au `relecteur-pedagogique` (`DEC-0002`, `DEC-0056`) ; je n'ai rien vu qui manque |

#### 4. Correction de mes propres constats

Aucune correction de mes faits. Ma lecture du premier jet (section 9) et ma relecture (section 6) ecrivent
`verifier-chemins.sh` lance avec l'entree standard fermee : c'est la forme qu'`A.280` point 2 dit **faux vert**. Ces deux
verdicts de garde-fou ne valaient rien ; je ne les cite pas ici, et ceux de ce commit sont lances en forme CI.

#### 5. La consigne et le titre

- **Consigne *"Lis bien, puis choisis la bonne réponse."*** : jouee, **tenue** : l'enonce porte la question a chaque manche,
  la consigne ne la contredit pas. C'est celle des paquets d'EMC CE1, la mecanique de la story.
- **Titre** : **non audite**, aucune entree n'existe. Il est lu par moi dans le commit de l'entree de catalogue, avant que
  la ligne bouge. Une autre consigne que celle-ci, ou un autre `nombreDeManches` que 10, serait aussi a relire.

#### 6. Reserves neuves, datees du 2026-10-27, non bloquantes

**`ETE-CE1-R1` - a 744 x 551, apres une erreur, l'indice de l'item 9 passe sous le pli.** Bas du bloc `.indice` : item 9
(*"Un verre contient de l'eau et de la glace. Il reste au soleil toute la journée. ..."*) **580** : sa seconde ligne,
*"congélateur."*, n'est pas visible sans defiler (capture a l'appui). Items 4 et 7 : **550**, le cadre touche le bas ; item 6
: 540 ; les huit autres de 444 a 514. Sur les items 4, 7 et 9, le bloc du verdict descend a 568 : *"Ce n'est pas ça."* et la
croix restent visibles, *"Essaie encore !"* est coupe. Cause commune aux trois : enonce sur trois lignes et propositions
sur trois rangees. Meme famille que `GNA-CE1-R1` et `TPH-CE1-R1`, regime de la reponse de l'utilisatrice a `Q-N4` (non
bloquant). **Ce qui la leve** (le critere de `GNA-CE1-R1`, repris tel quel) : a 744 x 551, apres une erreur, le bas du bloc
`.indice` est au-dessus de y = 551 sur les 12 items. La cause (contenu ou mise en page) est a `theo-archi` et `ines-design`.

**`ETE-CE1-R2` - a 744 x 551, un mot de la question est coupe a son trait d'union.** Item 5 : *"Que se passe-"* en fin de
ligne, *"t-il ?"* a la suivante ; items 7 et 9 : *"Que trouve-t-"*, puis *"on dedans ?"*. Un enfant de CE1 qui dechiffre
lit *passe* puis un *t-il* isole, ou un *on* qu'il ne rattache pas au verbe. Les autres items en *-t-il* (2, 11, 12) tiennent
sur leur ligne a cette largeur. **Ce qui la leve** : a 744 x 551, aucun mot qui porte un trait d'union n'est coupe entre
deux lignes dans les 12 enonces. Le moyen (formulation, caractere ou feuille de style) n'est pas a moi : symptome decrit,
cause a `theo-archi` et `ines-design`, et le texte servi, s'il change, repasse par le `relecteur-pedagogique`. Je n'ai pas
regarde si d'autres jeux ont le meme defaut.

**`ETE-CE1-R3` - les indices des quatre items dont la bonne reponse est *elle devient de la vapeur d'eau* n'aident pas un
enfant bloque.** Items 1, 4, 6, 8 : *"Compare le linge mouillé et le linge sec."*, *"Compare la flaque du matin et celle du
soir."*, *"Compare le sol juste après la pluie et le lendemain."*, *"Compare le niveau de l'eau avant et après."* Joue : apres
*elle devient de la buée*, il reste la conception et la bonne reponse ; l'indice fait comparer l'avant et l'apres de la meme
situation, c'est-a-dire constater que l'eau n'est plus la, ce qui pousse vers *elle disparaît*, *elle n'existe plus*, *elle
a disparu*. L'enfant ne trouve qu'en ecartant tout le reste et n'apprend pas pourquoi l'eau ne disparait pas. **Ce n'est
pas un durcissement de ma regle 5**, que ces indices tiennent a la lettre et que je ne change pas : je l'avais dit a
regarder a l'ecran (lecture du premier jet, section 1), et c'est une reserve neuve. **Ce qui la leve** : sur ces quatre
items, l'indice compare la situation de l'enonce a une situation ou l'eau ne peut pas s'en aller dans l'air, en restant
dans la regle 5 (il ne nomme pas la vapeur, ne dit pas qu'elle ne se voit pas, compare deux situations). Exemple de la
forme, non impose : *"Compare un linge au soleil et un linge dans un sac fermé."* (12 mots) Le texte est a
`camille-po` et au developpeur, puis a ma lecture.

#### 7. Ce dont je reste la seule garde sur cette ligne

`PaquetCe1QlmEauTroisEtatsTest` et le test transverse comptent les taches, le tiers, les familles de distracteurs, les
nombres, les gardes `T2` et `T3`. **Ils ne gardent pas** : l'exactitude de chaque fait contre 3.5 et le CM1 ; qu'un
distracteur de vapeur ne soit pas ce que la situation produit hors d'une ebullition, ni ce que la meteo de l'enonce ecarte ;
qu'une disparition ne soit pas vraie dans la langue de l'enfant hors d'un item de fonte ; l'eau liquide decrite sans le mot
*eau* ; les paraphrases de *rien ne change* ; qu'un indice aide un enfant bloque (`ETE-CE1-R3`) ; `FER-3` item par item ;
`NRC-CE1-1` sur le sujet et `CYC-1` ; `TRV-CE1-6` ; la position de l'indice et la coupure des mots a l'ecran (`ETE-CE1-R1`,
`ETE-CE1-R2`). **Je les tiens en jouant.**

#### 8. Identifiants, duree, verifications

- **Identifiants** : trois neufs, `ETE-CE1-R1`, `ETE-CE1-R2`, `ETE-CE1-R3`. Forme copiee de `GNA-CE1-R1` : trois segments,
  niveau au milieu, marqueur `R` dans le dernier (`ADR-0100`) ; prefixe `ETE-CE1` deja alloue (`ETE-CE1-1`, `-2`) ;
  `git grep` sur `ETE-CE1-R` ne rendait rien avant d'ecrire. Les trois lignes de la section 4 de
  `squad/decisions/REGISTRE-identifiants.md` sont dans le meme diff que cette fiche.
- **Duree a l'horloge**, lue par `date` : debut **21:52:01**, fin **22:28:41**, avant le commit, soit **36 min 40 s**, lectures,
  jeu, rendu, ecriture et verifications compris. Donnee brute pour la remesure d'A.276 ; je n'en tire aucun bareme.
  Aucune seconde lecture : l'audit est dans le 1,0 du lot.
- **Verifications avant commit**, sur l'arbre qui porte ce fichier :
  - `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest
    -Dsurefire.failIfNoSpecifiedTests=false` : avant la mise a jour du compte, *"declare 999 identifiants alloues et 179
    prefixes distincts, sa table en porte 1002 et 179"*, seul echec des 10 tests ; compte recopie, puis *"Tests run: 10,
    Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS`.
  - `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest
    -Dsurefire.failIfNoSpecifiedTests=false` : *"Tests run: 5, Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS`.
  - `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh`, 3264
    chemins : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0.
  - `verifier-prefixes.sh`, le sujet de ce commit sur son entree standard : *"Tous les commits portent un prefixe d'agent
    connu."*, sortie 0.
  - Perimetre : ces deux tests, module `bootstrap` et ses dependances, et deux garde-fous ; ni un `verify`, ni la porte
    technique. Aucun autre job de `.github/workflows/ci.yml` lance. Seules ces lignes et celle de la duree ont ete ecrites
    apres ces mesures ; elles sont relancees avant le commit.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
