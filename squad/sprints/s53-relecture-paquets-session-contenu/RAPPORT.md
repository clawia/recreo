# Rapport de sprint s53 - onze points de relecture decides et appliques, 9 stories sur 10 tenues, trois engagements de DEC-0112 non livres, aucun paquet branche

- **Duree** : 2026-10-07 12:18 -> 2026-10-07 15:23 (UTC+02:00), 3 h 05 min. Debut : horodatage de `planning.md` ; fin : celle de
  `review.md`, lue par `date`.
- **Objectif gele** (`planning.md`, repris de la story `S53SPE-RELECTURE`) : chacun des onze points P1 a P11 a une decision ecrite
  et appliquee, aucun paquet de la session de contenu n'est branche au catalogue, les paquets corriges repassent la suite de
  `adapter-content` et de `bootstrap`. Aucune ligne de matrice ne change de statut.

## L'increment livre, en une phrase

Les onze points de relecture des 45 paquets de contenu ecrits hors sprint le 2026-10-07 sont decides et appliques (neuf paquets
corriges, deux points acceptes sans changement), la suite de tests, rouge a l'ouverture du sprint sur 5 tests, est verte sur l'arbre
fusionne (2174 tests), et aucun de ces paquets n'est branche ni joue : la couverture reste a **132/182 (72,5 %)**.

## Les decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| `DEC-0112` | Ouverture de s53 : onze points de relecture des paquets de la session de contenu, un seul passage de `nadia-prof`, aucun branchement, trois postes engages et deux retires au troisieme report ; decision de P9 | Produit | Acceptee. Creee le 2026-10-07 (`cefab111`, `[CAM]`). Une correction de graphie ("une espace") par le relecteur en cloture (`b35221a5`), aucun amendement |

**`ADR` : aucune creee ni amendee.** `theo-archi` decide P10 (un item a un seul distracteur est admis par le chargeur, par le
domaine et par les garde-fous) sans `ADR` ni regle ArchUnit (`avis-faisabilite.md` section 3).

Un arbitrage de `nadia-prof` (pas une `DEC`) tranche P1 a P11 : `squad/conformite/ARBITRAGE-PREALABLE-s53-points-de-relecture-p1-a-p11.md`.
Il ne prononce aucun Done, aucun verdict, aucun statut de matrice. Index tenu a jour : `DEC-index.md` (ligne de `DEC-0112`).
**Aucun identifiant alloue** dans `REGISTRE-identifiants.md` (`git diff --name-only 9d44d510 HEAD` ne le contient pas).

**Non decide, a ecrire** : un amendement de `DEC-0112` pour acter `S53-REL-10` et corriger deux mesures (`review.md` obstacles 3, 5, 6),
route a `camille-po`.

## Etat des deux Done

### Technique - quatre jobs verts en local, juge CI non rendu

Source : `rapport-qualite.md` de `yanis-qa`, arbre `54953faa`, les quatre jobs de `.github/workflows/ci.yml` lances : `prefixes` vert,
`chemins` vert, `outils` vert (9 juges), `build` vert (`bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, `BUILD SUCCESS`,
**2174 tests**, 0 echec). Reserve : le pas `package` n'est pas rejoue.

**Ce que ce rapport ne couvre pas** : les commits posterieurs a `54953faa` (`git diff --stat 54953faa HEAD` : 7 fichiers, dont deux
lignes de commentaire dans `cp-qlm-situer-la-france.yaml`, le reste dans `squad/`). J'ai relance les quatre jobs sur `560b0fd2`, puis
sur l'arbre avec les fichiers de cloture :

| Job | Commande | Arbre | Resultat |
|---|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify`, racine, reacteur complet | `560b0fd2` | `BUILD SUCCESS`, 4 min 49 s, **2174 tests**, 0 echec, 0 erreur, 0 ignore |
| `build` | meme commande | `560b0fd2` + `review.md`, `retro.md`, `RAPPORT-tokens.md`, `backlog.md` | `BUILD SUCCESS`, 4 min 34 s, six modules `SUCCESS`, **2174 tests**, domain 120, application 17, adapter-web 401, adapter-content 1318, bootstrap 312 + 6 d'integration, 229 dans `com.recreo.archi` ; JDK 21.0.10 rapporte par la machine virtuelle des tests |
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `commit-atteignable.sh origin/round-two`, puis `git log --no-merges --format=%s origin/round-two..HEAD \| bash .github/scripts/verifier-prefixes.sh` | `560b0fd2` | vert, codes 0, 24 sujets hors fusions. Le sujet de mon commit de cloture est verifie par `verifier-prefixes.sh` avant d'etre cree |
| `chemins` | `verifier-chemins-test.sh`, puis `git ls-files -co --exclude-standard -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | arbre avec mes fichiers, y compris ce fichier | vert, codes 0 |
| `outils` | boucle du workflow, trois motifs, `python3` | `560b0fd2` | vert, **9 juges, 9 codes 0** |

**Total des tests, etabli sur l'arbre fusionne : 2174**, somme des attributs `tests` des rapports XML de surefire et de failsafe
apres `clean verify`. Meme total que celui de `yanis-qa` et de `theo-archi` (`54953faa`) et que la cloture de s52 (`509552f0`) : s53
n'ajoute aucun test. Les 2168 de la lecture de testabilite sont un total surefire seul d'une construction arretee a `bootstrap`
(`review.md` section 4.2).

**Apres l'ecriture de ce fichier** (15:32 a 15:34) : `bash outils/mvn-jdk21.sh -B clean test -pl bootstrap -am '-Dtest=com/recreo/archi/**/*' -Dsurefire.failIfNoSpecifiedTests=false`
(perimetre : le seul paquetage `com.recreo.archi`, qui lit `squad/` ; `-am` construit les modules dont `bootstrap` depend) : `BUILD SUCCESS`,
229 tests, 0 echec ; `chemins` relance avec ce fichier dans la liste : code 0. Le `clean verify` complet n'est pas relance apres ce fichier.

`theo-archi` confirme (`confirmation-archunit.md`, `54953faa`) : **229 tests ArchUnit verts**, **39** `static final ArchRule` et **36**
`@ArchTest` (methode d'`ADR-0104`), aucune regle ajoutee, retiree ni modifiee, 0 ligne de `src/main/java`, aucune assertion
affaiblie (les quatre tests modifies sont des declarations de paquets et des recomptages).

**Rien n'est pousse : la CI n'a rendu aucun verdict sur ces commits, et le tronc distant (`origin/round-two`, `76a50e8b`) est rouge
cote `bootstrap`** : ses sources sont celles de la base que `yanis-qa` a mesuree rouge (`git diff --name-only 76a50e8b 5c1353a9` ne
rend que `planning.md`), et je ne l'ai pas mesure directement. Seul le push de s53 le corrige.

### Pedagogique - aucun Done prononce

Aucun paquet n'est joue ni branche ; `nadia-prof` n'a rendu aucun verdict et aucune fiche de conformite n'est ajoutee ni modifiee
(`git diff 9d44d510 HEAD --name-only | grep -c 'squad/conformite/FICHE'` rend 0). Chaque paquet de la session passera devant elle en
premier passage complet, au tarif d'un lot, le jour ou il sera branche.

## Les stories

| Story | Points | Verdict de `yanis-qa` (rouge puis vert) |
|---|---|---|
| `S53-REL-1` | P1 | tenue |
| `S53-REL-2` | P2 | **partielle** : critere non automatisable |
| `S53-REL-3` | P3, P4 | tenue pour ce qui se mesure |
| `S53-REL-4` | P5, P10 | tenue |
| `S53-REL-5` | P6 | tenue |
| `S53-REL-6` | P7 | tenue |
| `S53-REL-7` | P8 | tenue |
| `S53-REL-8` | P9 | tenue (non-regression, aucun YAML) |
| `S53-REL-9` | P11 | tenue (aucun YAML) |
| `S53-REL-10` | base rouge | tenue, **absente du planning, du backlog et de `DEC-0112`** |

**Taux d'engagement mesure** : 12 engagements ecrits (9 stories du planning, 3 postes de `DEC-0112` section 5) ; 8 tenus, 1 partiel,
3 non livres : **66,7 % tenus, 75,0 % avec le partiel.** `S53-REL-10`, tenue, n'etait engagee nulle part : 9 stories tenues sur 10.

## Couverture du programme

**132/182 (72,5 %), inchange.** Commande ecrite dans `squad/programme/MATRICE-couverture.md` (section "Methode du compte"), lancee
sur la matrice a `9d44d510` et a `HEAD` : `couvert-valide` 132, `en cours` 4, `non couvert` 46, `non couvrable en l'etat` 76, total 258,
denominateur 182, dans les deux cas. La matrice n'est pas editee par s53.

## Obstacles resolus et encore ouverts

Seize lignes au tableau de `review.md` section 7.

**Resolus** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| Base de s53 rouge, 5 echecs de `bootstrap` (`S53-REL-10`) | `lea-dev`, `marc-dev`, `yanis-qa` | `rapport-qualite.md` section 3 : 5 echecs sur `5c1353a9`, 0 sur `54953faa`, aucun garde-fou assoupli | 2026-10-07 |
| Questions Q1 a Q8 du planning | `camille-po`, `nadia-prof`, `theo-archi` | `DEC-0112` ; arbitrage de `nadia-prof` ; `avis-faisabilite.md` | 2026-10-07 (fichiers existants) |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| Tronc distant rouge cote `bootstrap` | utilisatrice | s53 | le push de s53 |
| `CRP-CM1-R1` a `R3`, `PAR-4`, journal de tokens : **non livres, arbitrage de l'utilisatrice en attente** | utilisatrice, `camille-po` | s51 (report), s53 (engages) | engager avec proprietaire ou retirer avec motif |
| `S53-REL-10` sans acte d'ouverture | `camille-po` | s53 | amendement de `DEC-0112` |
| R1 : "0 fichier" a espace insecable dans `DEC-0112`, contre 4 fichiers | `camille-po`, `yanis-qa` | s53 | amendement, lecture corrigee |
| R2 : "Relaye de l'utilisatrice" n'est pas une forme de `DEC-0062` | `camille-po`, `relecteur-pedagogique` | s53 | forme corrigee |
| R3 : "six fichiers" dans l'arbitrage, un seul mesure | `nadia-prof` | s53 | arbitrage corrige |
| R4 : 14 `enonce` de `cm1-grands-nombres` et deux constantes de test, un meme commit | `theo-archi` ou developpeur du test | s53 | story `S53-REPORT-GRANDS-NOMBRES` (backlog, "Reports de s53") |
| R5 : "onze paquets plus anciens" au backlog, neuf mesures | `camille-po` | s53 | erratum |
| Journal de tokens sans proprietaire | `camille-po`, `theo-archi` | s51 | `DEC` de s54 |
| Reserves non levees de `yanis-qa` et `theo-archi` (motif de table, 53 items ecartes du rang, prose du motif) | `yanis-qa`, `theo-archi`, `lea-dev` | s53 | a lire au branchement |
| Ecart de toolchains du shell (hors depot) | utilisatrice | s52 | correction sur le poste |
| Actions de la retro de s52 : A1, A2, A4, A6, A7, A8 | leurs proprietaires | s52 | voir `review.md` obstacle 15 |
| `SYC-CE1-R1`, `MUL-CE1-R1`, item 16 de `FRA-CE1-25` : second report ; `ART-CP-10` : quatrieme sprint hors perimetre ; sciences CM1 | `camille-po`, utilisatrice | s51 a s53 | `DEC` de s54 ; questions U3 et U4 |

## Prochain perimetre

**Non propose par cette cloture** : role de `camille-po`. La section "Reports de s53" de `docs/backlog/backlog.md` rassemble ce que
s53 laisse : une story proposee (`S53-REPORT-GRANDS-NOMBRES`), les corrections de redaction routees, l'etat des trois postes non livres,
et le branchement des 45 paquets comme **proposition non engagee** (ordre de grandeur, borne basse non verifiee : 45,0 au tarif de 1,0
par paquet, au moins six sprints a 8,0 ; le tarif reel est a `nadia-prof`).

**Questions a l'utilisatrice**, ecrites a `review.md` section 9 : U1 (sort des trois postes non livres), U2 (brancher les 45 paquets,
dans quel ordre de matieres), U3 (sciences CM1), U4 (`ART-CP-10`), U5 (pousser s53).

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s53`, fenetre 2026-10-07 10:18 -> 13:23 UTC (debut du planning, fin de `review.md`).
Detail dans `RAPPORT-tokens.md` (ce dossier). `git status --short` apres l'execution : seul `squad/rapports/JOURNAL-tokens.md` est modifie
en plus du nouveau `RAPPORT-tokens.md` ; **aucun `RAPPORT-tokens.md` d'un autre sprint n'a bouge.** **Cumul dans
`squad/rapports/JOURNAL-tokens.md` : non mis a jour.** La regeneration du journal remplace par *"Aucune consommation mesuree"* les
sections de s23 a s27 ; je l'ai restaure par `git checkout -- squad/rapports/JOURNAL-tokens.md` (decision de l'utilisatrice du 2026-10-06 :
ne pas commiter le journal regenere) et j'ai conserve la regeneration hors depot. **La section de s53 est donc absente du journal
commite**, comme celles de s51 et de s52. Le tableau ci-dessous et `RAPPORT-tokens.md` sont les seules traces commitees de s53.

Le travail avant le planning (la session de contenu hors sprint, `DEC-0111` A.14) tombe dans `hors sprint`. Les tokens consommes apres
13:23 UTC pour finir les fichiers de cloture, relancer les jobs et ecrire ce rapport sont hors de la fenetre.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5-5 | 117 | 234 | 73 949 | 397 743 | 14 198 075 |
| `sofia-sm` | claude-sonnet-5-5 | 96 | 192 | 55 343 | 788 288 | 15 875 867 |
| `theo-archi` | claude-opus-5-5 | 179 | 358 | 73 406 | 645 493 | 20 953 059 |
| `marc-dev` | claude-sonnet-5-5 | 156 | 312 | 112 116 | 506 622 | 16 158 857 |
| `lea-dev` | claude-sonnet-5-5 | 114 | 228 | 66 671 | 440 352 | 8 624 315 |
| `yanis-qa` | claude-sonnet-5-5 | 151 | 302 | 67 849 | 461 865 | 10 925 349 |
| `relecteur-pedagogique` | claude-sonnet-5-5 | 208 | 416 | 76 207 | 513 109 | 18 992 567 |
| `nadia-prof` | claude-opus-5-5 | 88 | 176 | 71 822 | 529 824 | 14 272 612 |
| `ines-design` | - | aucune invocation mesuree | - | - | - | - |
| orchestrateur (fil principal) | claude-sonnet-5-5 | 58 | 158 | 100 169 | 487 195 | 12 140 466 |
| **Total** | | **1167** | **2 376** | **697 532** | **4 770 491** | **132 141 167** |

Les quatre compteurs sont separes (voir `RAPPORT-tokens.md`) : les additionner donnerait un classement faux.
