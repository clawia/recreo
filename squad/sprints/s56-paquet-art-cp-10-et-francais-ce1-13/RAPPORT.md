# Rapport de sprint s56 - le premier paquet a reproductions d'`ART-CP-10` ecrit, branche et refuse a l'audit ; une ligne fermee (142/182), six reserves levees

- **Duree** : 2026-10-09 20:36 -> 2026-10-11 14:14 (UTC+02:00), 41 h 38 min d'horloge. Debut : l'heure de debut de `planning.md` ; fin : celle de `review.md`, lue par `date`.
- **Objectif gele** (`planning.md`) : ecrire puis auditer le premier paquet a reproductions d'`ART-CP-10`, brancher `ce1-verbe-sujet` (`FRA-CE1-13`), lever six reserves de jeux deja servis (`ACV-CE1-R1`, `ACV-CE1-R2`, `CLM-CE1-R1`, `EMC-CM1-R8`, `EMC-CM1-R9`, `EXC-R16`).

## L'increment livre, en une phrase

Un jeu de francais CE1 (`Le verbe et son sujet`) est servi a l'enfant avec le verdict `VALIDE SOUS RESERVE` de `nadia-prof`, ce qui porte la couverture de 141/182 (77,47 %) a **142/182 (78,02 %)** ; six reserves de jeux deja servis sont levees ; le premier jeu d'arts plastiques CP
fait de reproductions d'oeuvres (`Des œuvres faites de la même façon`) est ecrit, garde par des tests et servi, **mais refuse a l'audit** parce que la phrase qui dit a l'enfant d'appuyer sur la bonne oeuvre est entierement sous le pli de l'ecran de 744 x 551 (105 px manquent) : il est toujours au catalogue, `ART-CP-10` reste `non couvert`.

## Les decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| `DEC-0116` | Ouverture de s56 : option O3, un lot de francais CE1, six reserves levees, le paquet a reproductions d'`ART-CP-10` ecrit par `lea-dev` puis audite, ecart accorde pour s56 seulement, second report de `S54-OBS-SCI-CONSIGNE` | Produit, creee le 2026-10-09 (avant le planning) ; **amendee cinq fois** : `A.1`, `A.2` (2026-10-09), `A.4` a `A.8`, `A.9` a `A.14` (2026-10-09), `A.15` a `A.20` (2026-10-11, cloture) | **`Proposee`**, tenue : `Q4` (ordre des matieres) sans reponse de l'utilisatrice ; ses mots couvrent la composition, l'ecart et le proprietaire du paquet, pas l'ordre |
| `ADR-0026` | Une animation retarde ce que l'enfant voit, elle ne determine rien | Technique, **amendee** le 2026-10-09 (`1de72f0f`) : la clause 3 exclut un sujet pseudo-element decoratif ; mot corrige par le relecteur (`C3`) | `Accepte` |
| `ADR-0024` | Faire porter l'explication d'un item par le verdict de la manche jouee | Technique, **amendee** le 2026-10-09 (`6f8895a4`) : un paquet sans graphie porte `explication` sur tous ses items ou sur aucun | `Accepte` |
| `ADR-0104` | Rapprocher deux oeuvres se sert par des propositions qui sont des reproductions | Technique, **amendee** le 2026-10-11 (`a44a9d5b`) : deux garde-fous de rendu ecartent les reponses en image (ratification de `0076cbff`), oracle independant du test de non-exclusion | `Accepte` |

**`ADR` ou `DEC` creees : aucune dans la fenetre du sprint** (`DEC-0116` existait avant le planning ; `git diff --stat dfcec37c..HEAD -- squad/decisions` : trois `ADR` modifiees, une `DEC` amendee, aucun fichier neuf). Index tenus : `ADR-index.md` (trois lignes ajoutees), `DEC-index.md` (trois amendements notes).
`REGISTRE-identifiants.md` : identifiants neufs `RDO-CP-R1`, `RDO-CP-R2`, `VSJ-CE1-R1` (`nadia-prof`), compte lu dans l'en-tete du registre, **non recompte par moi** ; le `build` vert porte son garde-fou.
Hors decision : `lessons.md` cree a la racine par la retro (il n'existait pas). **Aucune `ADR` ni `DEC` n'est creee ou amendee par `review.md`, `retro.md` ou ce rapport.**

## Etat des deux Done

### Technique

**Juge : la CI, tous les jobs de `.github/workflows/ci.yml`** : `build`, `prefixes`, `chemins`, `outils`. **La CI distante (GitHub Actions) n'est lue par personne** : `gh run list` rendait `HTTP 404` depuis ce poste, rien n'est pousse. Tout ce qui suit a tourne en local, sous Windows.
**Je ne declare pas le Done technique.**

| Qui | Arbre | Jobs lances, par leur nom | Resultat |
|---|---|---|---|
| `yanis-qa` | `80c06e64` | `build` (`verify` ; etape "Package du fat jar" non lancee telle quelle), `prefixes`, `chemins`, `outils` (10 juges) | quatre lances, verts ; 2403 tests ; son rapport, premiere version, a rendu `BUILD FAILURE` (codes de mutation lus comme identifiants), corrige avant commit |
| `theo-archi` | `80c06e64` | `build` (deux etapes), `chemins`, `prefixes` ; **`outils` non lance** | verts ; 248 tests `com.recreo.archi` ; 39 `ArchRule` et 36 `@ArchTest` inchanges |
| `relecteur-pedagogique` | `7130dec6` plus ses commits | `build` (`verify` ; etapes "presence du build" et "package" non lancees), `chemins`, `prefixes` ; **`outils` non lance** | verts ; 2404 tests |
| `marc-dev`, `lea-dev` | arbre de chaque commit | `build`, `prefixes`, `chemins`, `outils` | `BUILD SUCCESS` ; `lea-dev` : `verify` complet seulement au dernier commit des garde-fous |
| `sofia-sm` (cette tache) | `116354be` plus les fichiers de cloture | `build` (`clean verify`, puis `package` sans `clean`), `prefixes`, `chemins`, `outils` (10 juges) ; ni le workflow GitHub, ni la branche `BASE` de `prefixes` | les quatre verts ; 2404 tests, 0 echec ; `BUILD SUCCESS` deux fois (2 min 51 s et 2 min 01 s) |

**Total des tests, etabli par moi sur l'arbre fusionne : 2404** (`bash outils/mvn-jdk21.sh -B clean verify` sur `116354be`, reacteur complet : domain 120, application 18, adapter-web 442, adapter-content 1470, bootstrap 348, plus 6 d'integration ; 0 echec, 0 erreur, 0 ignore ;
somme des attributs `tests` des 295 rapports XML, recoupee par les lignes `Tests run:` de fin de module ; JDK 21.0.10 rapporte par la machine virtuelle des tests). **Delta : +97 sur 2307** (base `4cd44098`, mesure du planning) :
+96 mesures par `yanis-qa` sur `80c06e64` (adapter-content +47, adapter-web +35, bootstrap +14) et +1 de `7b315f36`. Les annonces des developpeurs mesurent chacune la base de leur worktree plus leur lot ; elles se recoupent (60 + 33 + 3 = 96) sans etre reprises.

**Ce qui n'est pas verifie** : la CI distante ; la branche `BASE` du job `prefixes` ; tout ce qui tourne sous Linux ; une seule configuration de fins de ligne (`core.autocrlf=true`).
**Le rouge de CI que `a3606b2f` corrige (A4)** : `theo-archi` reproduit le mecanisme (1 echec sur 17 avec `SOURCE_DATE_EPOCH`, 0 sans) ; **le declencheur sur GitHub n'est pas etabli**, le journal du job rouge n'est cite par aucun fichier. **Cause non etablie.**
**Ce que `yanis-qa` n'a pas pu mordre** : quinze mutations vertes sur 90, neuf constats `F1` a `F9`, dont `aspect-ratio` de la case et `max-height` sous `@media` non gardes (`F1`, `F2`), cinq proprietes de la marque non gardees (`F3`), et `F9` (le critere d'origine du plafond, insatisfiable, reste lisible dans le backlog).
`lecture-testabilite.md` n'a pas ete ecrite.

### Pedagogique - prononce par `nadia-prof` seule

| Ligne ou paquet | Verdict | Fiche |
|---|---|---|
| `ART-CP-10` (`cp-rapprocher-deux-oeuvres`) | **REFUSE** : a 744 x 551, a la revelation, la phrase de `M4` est entierement sous le pli (626 a 656, pli a 551, 105 px) ; reserves `RDO-CP-R1` (a l'echec, ni verdict ni indice au-dessus du pli), `RDO-CP-R2` (l'ecran de fin parle d'oeuvres qu'il ne montre pas) | `FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md` |
| `FRA-CE1-13` (`ce1-verbe-sujet`) | **VALIDE SOUS RESERVE** : `J1` a `J5` tenues, 24 items justes ; `VSJ-CE1-R1` (la place seule donne le sujet) | `FICHE-bandit-manchot-verbe-sujet-ce1.md` |
| `FRA-CE1-14` | `ACV-CE1-R1`, `ACV-CE1-R2` **levees** | `FICHE-bandit-manchot-accord-sujet-verbe-ce1.md` |
| `FRA-CE1-12` | `CLM-CE1-R1` **levee** | `FICHE-bandit-manchot-classes-de-mots-ce1.md` |
| `EMC-CM1-03`, `EMC-CM1-06` | `EMC-CM1-R8`, `EMC-CM1-R9` **levees** | `FICHE-bandit-manchot-respect-differences-cm1.md`, `FICHE-bandit-manchot-principes-republique-cm1.md` |
| `EMC-CP-03` | `EXC-R16` **levee**, en-tete de fiche complete (A11) | `FICHE-bandit-manchot-exclusion-cp.md` |

Audits joues sur un jar, Chrome sans tete pilote par son port de deverminage, captures a densite 1 : aucun enfant, aucun ecran de tablette, aucun doigt. Elle reste seule garde de la phrase de `M4`, de l'indice et de la croix au-dessus du pli, de `RDO-CP-R2`, de la regle 2, de `B6`, de l'exactitude des explications :
**une CI verte ne couvre aucune de ces proprietes.** Elle a corrige elle-meme son constat *"tenu avec O1 et le meuble"*, faux. **Ce rapport ne prononce aucun Done.**

## Couverture du programme

**142/182 (78,02 %)**, contre 141/182 (77,47 %) a la base. Commande de la "Methode du compte" de `MATRICE-couverture.md`, relancee par moi sur `dfcec37c`, `7130dec6` et `HEAD` : `couvert-valide` 141, 141, **142** ; `en cours` 4 ; `non couvert` 37, 37, **36** ; `non couvrable en l'etat` 76 ; total 258 ; denominateur 182.
Une ligne ferme, `FRA-CE1-13`. `ART-CP-10` reste `non couvert`, aucune mention de s50. Fichiers de contenu sans entree de catalogue : 36 sur 183.

## Capacite : `PAR-8` et `PAR-9`

Unites : bareme de `nadia-prof`, pas des tokens. Recomptees par moi (arithmetique) a partir de ses prix, non remesurees.

| Poste | Instance A | Instance B |
|---|---|---|
| **Engage** (planning amende ; `DEC-0116` A.13) | **3,5** | **4,75** |
| **Consomme, selon `cloture-nadia.md` section 6** (5,75) | 2,5 | 3,25 |
| **Consomme, mon recompte avec les arbitrages, la forme et le choix des oeuvres** (7,25) | 3,0 | 4,25 |
| Marges de rejeu, non consommees | 0,5 | 0,5 |

Engage 8,25 sur la borne de 8,0 : **0,25 au-dessus de la borne**, sous l'ecart de 0,5 accorde pour s56 seulement (*"Ecart de 0,5"*, de l'utilisatrice, 2026-10-09, `DEC-0116` A.1, relaye) ; **0,25 n'est alloue a aucun poste**. Plafond dur de 5,0 : B a 4,75, non atteint. `PAR-2` : le choix des oeuvres est sur B (*"Oui, a 4,75"*, `DEC-0116` A.4).
**Garde-fous `B2`, `B3`, `B5`, `B7`, `B8`** : 0,0 pour `nadia-prof`, environ 0,5 jour de developpement (estimation de `theo-archi`), sans ligne au planning ; **depassement de la borne accepte par l'utilisatrice** le 2026-10-11 : *"Lance Q2 aussi meme si on depasse la borne"*, mots cites, forme 2, **recus de l'agent lanceur, ecrits nulle part ailleurs dans le depot** (`review.md` section 4).
La borne visee et le montant ne figurent dans aucun fichier : `DEC-0116` A.18 point 5. **Ce n'est pas une jurisprudence** : un ecart accorde pour s56 seulement (`DEC-0114` A.20) ; la `DEC` d'ouverture de s57 compte selon `PAR-9` telle qu'ecrite.
`PAR-8` et `PAR-9` : plafond dur non atteint, deux lots pour A, un pour B. Cause du refus : une **specification prealable fausse** (une barre dite tenue sans l'etre), pas un contenu faux. Rejeu : 0,5.

## Obstacles resolus et encore ouverts

Vingt-trois lignes au tableau de `review.md` section 12.

**Resolus sur fichier existant** :

| Description | Agent route | Decision ou fichier | Clos le |
|---|---|---|---|
| Planning sans avis ni arbitrage | `theo-archi`, `nadia-prof` | `avis-faisabilite.md` (`026b2e17`), `ARBITRAGE-PREALABLE-s56-*` (`c92ad8f7`, `1ae74a43`) | 2026-10-09 |
| Capacite de B (4,75), `PAR-2`, 0,25 sans poste | `camille-po`, utilisatrice | `DEC-0116` A.4, A.5, A.13 | 2026-10-09 |
| Deux postes d'`ADR` sans story ; critere de la marque et du plafond non ecrits ou insatisfiable | `camille-po`, `theo-archi` | `DEC-0116` A.9, A.10, A.11 ; `6f8895a4`, `1de72f0f` | 2026-10-09 |
| `lea-dev` : un critere dit non ecrit alors qu'il est dans sa base ; cote lue a l'oeil | `sofia-sm` | `lessons.md` (retro de s56) | 2026-10-11 |
| `P3` de `relecture-cloture.md` (total 2307 sous deux perimetres) | `sofia-sm` | `retro.md` section 4 | 2026-10-11 |
| Reaffectation des postes de l'instance arretee | `sofia-sm` | `cloture-nadia.md` | 2026-10-11 |

**Constates** : entree de catalogue 15 h 07 apres son echeance, 23 h 49 sans livraison (les deux arrets d'agent et le watchdog sont des enonces du message de lancement, non ecrits dans le depot) ; porte `Q7` insatisfiable (de ma main) ; garde-fous sans ligne de planning et depassement ecrit ici pour la premiere fois.

**Encore ouverts** (numero du tableau de la review) :

| # | Description | Agent route | Ce qui manque |
|---|---|---|---|
| 9 | **`ART-CP-10` REFUSE** : phrase de `M4` sous le pli, `RDO-CP-R1`, `RDO-CP-R2` ; reprise 0,5 | `camille-po`, `lea-dev`, `ines-design`, `theo-archi`, `nadia-prof` | la `DEC` d'ouverture de s57 |
| 10 | **`Q-U3` : le jeu refuse est jouable au catalogue** (retrait D1 ou maintien D2) | utilisatrice | sa reponse ; sans elle l'entree reste |
| 11, 12 | `DEC-0116` `Proposee` (`Q4`) ; `Q3`, `Q6` | utilisatrice | ses mots en forme 2 |
| 13, 14 | Cause du rouge de CI (A4) ; CI distante non lue (A3, second report) | `theo-archi`, utilisatrice | le journal du job rouge ; un push et un verdict nomme |
| 15, 16 | `F1` a `F9` de `rapport-qualite.md` ; `lecture-testabilite.md` absente | `theo-archi`, `nadia-prof`, `ines-design`, `lea-dev`, `relecteur-pedagogique`, `camille-po`, `yanis-qa` | stories ou decisions de ne pas faire |
| 17, 22 | `P1`, `P2`, `P4`, `P5` de la relecture ; forme 3 de `DEC-0114` ligne 31 | `camille-po`, `nadia-prof`, `ines-design`, `lea-dev`, utilisatrice | une ligne chacun ; la lecture de `DEC-0062` |
| 18, 19 | `A10` non engagee ; `S54-OBS-SCI-CONSIGNE`, second report | `nadia-prof`, `camille-po` | placement ou abandon a s57, troisieme report interdit |
| 20, 21 | Collision de port de `JarRecreoIT`, non expliquee ; `B6` tenu par `nadia-prof` seule | `yanis-qa`, `nadia-prof` | une reproduction ; un champ de support si un second objet en volume est restaure |

## Questions ouvertes de l'utilisatrice

Reprises de `review.md` section 15 :

- **U1, `Q-U3`** : retirer l'entree du paquet refuse (D1, recommandation de `camille-po`) ou la maintenir (D2, qui exige son arbitrage ecrit, un amendement de `DEC-0055`, un proprietaire nomme) ?
- **U2, U3** : `Q4` (ordre des matieres), `Q3` (deux instances de francais CE1), `Q6` (mesure de pli de `LIS-2`).
- **U4** : pousser et lire la CI sur un commit nomme, job par job ; lire le journal du job `build` rouge d'avant `a3606b2f`.
- **U5** : le depassement des garde-fous : quelle borne, de combien, l'ecrire dans `DEC-0116` ?
- **U6** : les deux agents arretes et le watchdog sur `lea-dev` : motifs et durees, pour les ecrire.
- **U7** : la formule de la forme 3 tolere-t-elle l'accord au pluriel (`DEC-0114` ligne 31) ?

## Consommation de tokens

Genere par `python outils/rapport-tokens.py --sprint s56` (fichier complet : `RAPPORT-tokens.md`). Fenetre fermee par l'heure de fin de `review.md` : les lancements de verification posterieurs ne sont pas dans la fenetre.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5-5 | 193 | 392 | 129 304 | 2 739 663 | 47 023 471 |
| `sofia-sm` | claude-sonnet-5-5 | 192 | 388 | 149 145 | 1 449 211 | 38 696 087 |
| `theo-archi` | claude-opus-5-5 | 381 | 772 | 185 821 | 2 332 430 | 109 522 265 |
| `marc-dev` | claude-sonnet-5-5 | 183 | 370 | 139 458 | 1 433 170 | 29 163 927 |
| `lea-dev` | claude-sonnet-5-5 | 529 | 1 072 | 280 293 | 11 720 419 | 166 529 536 |
| `yanis-qa` | claude-sonnet-5-5 | 333 | 666 | 173 756 | 981 391 | 110 030 826 |
| `relecteur-pedagogique` | claude-sonnet-5-5 | 153 | 306 | 53 249 | 699 781 | 30 796 029 |
| `nadia-prof` | claude-opus-5-5 | 480 | 966 | 281 252 | 3 310 484 | 105 452 865 |
| `ines-design` | claude-sonnet-5-5 | 170 | 340 | 100 696 | 663 876 | 15 685 512 |
| orchestrateur (fil principal) | claude-sonnet-5-5 | 142 | 376 | 101 447 | 1 199 858 | 17 159 807 |
| **Total** | | **2756** | **5 648** | **1 594 421** | **26 530 283** | **670 060 325** |

Fenetre : 2026-10-09 18:36 a 2026-10-11 12:14 UTC (soit 20:36 a 14:14 UTC+02:00). Transcriptions presentes pour les neuf agents invoques et le fil principal. `lea-dev` porte 529 tours sur 2756 (19,2 %) et 11 720 419 tokens de cache cree, le plus haut de la fenetre (le suivant : `nadia-prof`, 3 310 484) ; `nadia-prof` 480 tours (17,4 %) sur deux instances et une cloture ; `theo-archi` 381 (13,8 %) ; `yanis-qa` 333 (12,1 %). `nadia-prof` et `theo-archi` sont sur `claude-opus-5-5`, les autres sur `claude-sonnet-5-5` : c'est la source des onze trailers `Claude Opus 5.5` (six `[THEO]`, cinq `[NAD]`) de `review.md` S1 (`DEC-0110` 17.4). Les quatre compteurs ne s'additionnent pas (voir `RAPPORT-tokens.md`). **Comparaison avec s55** (journal) : 2756 tours contre 3706 (-25,6 %), 1 594 421 tokens d'output contre 2 107 822 (-24,4 %) ; `nadia-prof` 480 contre 868. Ce que les compteurs ne disent pas : pourquoi `nadia-prof` consomme moins qu'a s55 ; je ne l'ai pas mesure.

## Ce que ce rapport ne couvre pas

Les comptes de tokens ne disent pas ce que chaque agent a produit. Les chiffres de capacite sont ceux de `nadia-prof`, recomptes en arithmetique et non remesures. La CI distante n'a pas ete lue. Aucun Done n'est prononce ici. Les arrets d'agent et le watchdog ne sont pas verifies dans le depot.
