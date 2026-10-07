# Review - s53 - onze points de relecture decides et appliques, un point partiel, trois engagements de DEC-0112 non livres, aucun paquet branche

- **Horodatage de debut** (`planning.md`) : 2026-10-07 12:18 (UTC+02:00), lu par `date` a l'ecriture du planning.
- **Horodatage de fin** : 2026-10-07 15:23 (UTC+02:00), lu par `date` a la fin de l'ecriture de ce fichier.
- **Duree effective** : 3 h 05 min.
- **Casquette** : `sofia-sm`, Scrum Master. Aucune story de developpement portee par moi a s53.
- **Arbre revu** : branche `s53-sofia-cloture`, issue de `round-two` a `560b0fd2` (`git log -1 --format=%h` rend `560b0fd2`,
  `git status --short` vide, `bash outils/verifier-lignee.sh` sort 0). `origin/round-two` est `76a50e8b` : **37 commits
  (24 hors fusions, 13 fusions) ne sont pas pousses.**
- **Lignee des branches de s53, verifiee par moi** : `git merge-base --is-ancestor 52e98065 <branche>` (le planning) et
  `git merge-base --is-ancestor <branche> HEAD` reussissent pour les neuf branches de s53 qui existent encore
  (`s53-marc-relecture`, `s53-lea-relecture`, `s53-camille-p9`, `s53-theo-p10`, `s53-rel-p11`, `s53-yanis-testabilite`,
  `s53-yanis-qualite`, `s53-theo-archunit`, `s53-rel-cloture`). La branche de `nadia-prof` n'existe plus : son commit `f454f76f`
  est dans l'historique de `HEAD`. Les branches de `marc-dev` et `lea-dev` ont pour base `5c1353a9`, la fusion du planning
  (`git merge-base <branche> 5c1353a9` rend `5c1353a9`).

---

## 1. L'increment livre contre l'objectif

**Objectif gele** (`planning.md`, repris de la story `S53SPE-RELECTURE` et de `DEC-0112` section 1) : chacun des onze points
P1 a P11 a une decision ecrite et appliquee, aucun paquet de la session n'est branche, les paquets corriges repassent la suite
de `adapter-content` et de `bootstrap`. **Aucune ligne de matrice ne change de statut.**

**Livre** : l'objectif de la story est atteint. Les onze points ont une decision sur le disque ; neuf ont modifie un paquet, deux
(P9, P11) n'en modifient aucun. Aucun branchement : `git diff 9d44d510 HEAD --name-only | grep -c catalogue.yaml` rend **0**. Aucune
ligne de matrice ne bouge : meme commande avec `MATRICE` rend 0. Aucune fiche de conformite n'est ajoutee ni modifiee : meme
commande avec `squad/conformite/FICHE` rend 0.

| Point | Decision sur le disque | Application | Commit |
|---|---|---|---|
| P1 | `ARBITRAGE-PREALABLE-s53-points-de-relecture-p1-a-p11.md` section 1 : "les beaux chapeaux" | item 1 de `ce1-accord-groupe-nominal` | `6fe850dd` [MARC] |
| P2 | `propositions-lea-dev.md` (decision d'auteure), confirmee et completee par l'arbitrage section 2 (indice des items 10 et 13) | textes des items 10, 13, 14 ; puis indices des items 10 et 13 de `ce1-pronoms` | `9bd0ad21`, `46490d0d` [LEA] |
| P3 | arbitrage section 3 : "la democratie" remplace "la liberte" | item 4 de `cm1-emc-principes-republique` | `7f94d7d5` [MARC] |
| P4 | arbitrage section 4 : indices 6 a 11 reecrits, 1 a 5 gardes | indices 6 a 11 du meme paquet | `752182f1` [MARC] |
| P5 | arbitrage section 5 : item reecrit par negation | item 7 de `cp-qlm-vocabulaire-de-position`, en-tete `FER-3` | `cc8b9810` [LEA] |
| P6 | arbitrage section 6 : les deux items | deux indices de `ce1-masses-contenances` | `2c9d34cd` [MARC] |
| P7 | `propositions-lea-dev.md` (decision d'auteure), confirmee par l'arbitrage section 7 | item 4 de `cm1-hda-epoque-civilisation` | `f43e5f96` [LEA] |
| P8 | arbitrage section 8 : item 16 corrige, items 15 et 3 acceptes | item 16 de `ce1-texte-documentaire` | `a7b353b3` [LEA] |
| P9 | `DEC-0112` section 4 : la mention exigee d'avance n'est pas une ligne `DEGRADE` ; arbitrage section 9 | aucun YAML | - |
| P10 | `avis-faisabilite.md` section 3 (`theo-archi` : un distracteur admis, aucun `ADR`) ; arbitrage sections 5 et 10 (item 7 : un distracteur admis ; item 12 : deux exiges) | item 12 de `cp-qlm-situer-la-france` ; l'item 7 de `vocabulaire-de-position` garde un distracteur | `9811dfd1` [LEA] |
| P11 | arbitrage section 11 : espace ordinaire acceptee dans `competence`, treize fichiers | aucun YAML | - |

**Ordre decision puis edit (critere C1), mesure sur les heures de commit** (`git log --no-merges --format='%h %ad' --date=format:%H:%M:%S`) :
l'arbitrage de `nadia-prof` est commite a 14:20:46 ; les edits de P1, P3, P4, P5, P6, P8 et P10 le sont entre 14:27:13 et 14:29:11.
**Exception constatee** : P2 (`9bd0ad21`, 13:50:39) et P7 (`f43e5f96`, 13:50:40) sont commites avant le fichier de decision d'auteure
qui les tranche (`7daadbfb`, 13:55:42), qui n'est sur `round-two` qu'a la fusion de 14:04. La lettre de C1 demande la decision
sur `round-two` avant l'edit ; elle n'y etait pas. Sans effet constate : `nadia-prof` confirme le texte des deux edits (arbitrage
sections 2 et 7), et demande une reprise pour P2 (`46490d0d`).

## 2. Les stories, une par une

"Tenue" dit que le critere automatisable de la story est tenu sur l'arbre fusionne `54953faa`, tel que `yanis-qa` l'a rejoue (rouge
sur la base `5c1353a9`, vert ensuite). Ce qu'aucun automate ne juge reste a `nadia-prof`, a l'audit de chaque paquet. **Preuves :
`rapport-qualite.md`, section 4 pour `S53-REL-1` a `-9`, section 3 pour `S53-REL-10`.**

| Story | Points | Verdict | Ce que la preuve dit |
|---|---|---|---|
| `S53-REL-1` | P1 | **tenue** | `grep -c` de l'ancienne forme : 1 a la base, 0 ensuite (rapport section 4, `S53-REL-1`) |
| `S53-REL-2` | P2 | **partielle** | critere non automatisable. Verifie par machine : le diff ne touche que les items 10, 13, 14 ; indices de 10 mots ; numeros d'items de l'en-tete entre 1 et 20. **Non verifie** : que la classification de l'en-tete soit celle qu'on obtient en relisant item par item (rapport section 4, `S53-REL-2`) |
| `S53-REL-3` | P3, P4 | **tenue pour ce qui se mesure** | P3 : 1 a la base, 0 ensuite. P4 : indices 1 a 5 inchanges, 6 a 11 egaux a l'arbitrage. Non juge : que les indices reecrits ne reformulent pas la reponse (rapport section 4, `S53-REL-3`) |
| `S53-REL-4` | P5, P10 | **tenue** | P5 : 1 puis 0. P10 : 2 lignes puis 1 (l'item admis a un distracteur). Reserve de `yanis-qa` : le motif de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` n'est pas eprouve par mutation (rapport section 4, `S53-REL-4`) |
| `S53-REL-5` | P6 | **tenue** | 2 occurrences a la base, 0 ensuite, aucun indice ne contient sa bonne reponse (rapport section 4, `S53-REL-5`) |
| `S53-REL-6` | P7 | **tenue** | 1 occurrence dans un `enonce`, 0 ensuite ; la ligne d'en-tete reste, acceptee par l'arbitrage (rapport section 4, `S53-REL-6`) |
| `S53-REL-7` | P8 | **tenue** | balayage : 4 lignes a la base, 1 ensuite, celle que l'arbitrage accepte (rapport section 4, `S53-REL-7`) |
| `S53-REL-8` | P9 | **tenue (non-regression)** | `grep -c '^# DEGRADE'` rend 0 sur les trois paquets a la base comme apres ; aucun YAML ne change. Aucun test ne lit ce marqueur (rapport section 4, `S53-REL-8`) |
| `S53-REL-9` | P11 | **tenue, un point non verifie** | 13 fichiers a la base et apres, meme liste ; la decision accepte, aucun diff attendu. Non verifie : que les neuf plus anciens soient ceux que la decision a en tete, elle ne les nomme pas (rapport section 4, `S53-REL-9`) |
| `S53-REL-10` | base rouge | **tenue** | 5 echecs sur `5c1353a9`, 0 sur `54953faa`, meme commande, aucun garde-fou assoupli (rapport section 3). **Story absente du planning, du backlog et de `DEC-0112`** : obstacle 3 |

**Taux d'engagement, mesure.** Engagements ecrits au depart : 9 stories du planning plus 3 postes ajoutes par `DEC-0112` section 5
(`CRP-CM1-R1` a `R3`, `PAR-4`, journal de tokens) = **12**. Tenus : **8** (`S53-REL-1`, `-3`, `-4`, `-5`, `-6`, `-7`, `-8`, `-9`).
Partiel : **1** (`S53-REL-2`). Non livres : **3** (les postes de `DEC-0112`). **8 sur 12 tenus, soit 66,7 % ; 9 sur 12 en comptant le
partiel, soit 75,0 %.** `S53-REL-10` (tenue) n'etait engagee nulle part et n'entre pas dans ce taux ; avec elle, 9 stories tenues
sur 10. Sur le perimetre que l'utilisatrice a nomme (P1 a P11) : 11 points sur 11 decides et appliques ou soldes, un point (P2)
dont la verification s'arrete a la lecture de l'auteur.

## 3. Etat des engagements de `DEC-0112`

`DEC-0112` (`camille-po`, `cefab111`, 2026-10-07 14:03, Acceptee) porte, hors du perimetre P1 a P11 qui etait demande, trois postes
engages a s53 en section 5. **Aucun n'a ete execute. Leur statut a la cloture : non livre, arbitrage de l'utilisatrice en attente.**

| Engagement | Section | Preuve de l'etat | Statut |
|---|---|---|---|
| `CRP-CM1-R1` a `R3` (0,5, rejeu de `nadia-prof`, cinq items de `cm1-sciences-corps`) | 5.2 | `git diff 9d44d510 HEAD -- adapters/adapter-content/src/main/resources/contenu/cm1-sciences-corps.yaml` est vide ; `grep -c "corps-cm1" adapters/adapter-content/src/main/resources/catalogue.yaml` rend **1** : le jeu est toujours servi, avec l'indice que `DEC-0112` dit faux (item 3) | **non livre, arbitrage de l'utilisatrice en attente** |
| `PAR-4`, passe transverse (`S53-PASSE-TRANSVERSE`, 0,5) | 5.4 | `find squad -iname 'PASSE-TRANSVERSE-s53*'` rend 0 fichier ; les trois passes du depot sont celles de s29, s30, s35 | **non livre, arbitrage de l'utilisatrice en attente** |
| `S51-OUTIL-JOURNAL-TOKENS` | 5.5 | `git diff --stat 9d44d510 HEAD -- outils` est vide. `theo-archi` a rendu le prix (`confirmation-archunit.md` section 5 : une fonction de 20 a 30 lignes et un fichier de test, "pas trop lourd") apres coup : son avis de faisabilite ne le contenait pas | **non livre, arbitrage de l'utilisatrice en attente** ; poste sans proprietaire (obstacle 10) |

Ce que le planning dit de ces trois postes : rien. Le planning date de 12:18, `DEC-0112` de 14:03 ; `git log --format=%h -- planning.md`
rend deux commits, le planning lui-meme et une correction de graphie du relecteur (`b35221a5`). `DEC-0112` section 9 me demandait de
reintegrer ces postes au planning et de les affecter ; **ni l'amendement ni l'affectation n'existent**. Je n'ecris pas cet
amendement apres coup : ce serait un faux. Le prix de `nadia-prof` est de 0,5 pour son passage (arbitrage section 14), 1,5 sur 5,0 avec
les 1,0 de `DEC-0112` ; **ces 1,0 n'ont pas ete depenses**, aucun fichier ne les consomme.

Les autres engagements et decisions de `DEC-0112`, rapportes sans jugement :

| Section | Contenu | Etat |
|---|---|---|
| 1, 2, 3 (Q2, Q4, Q8), 4 | perimetre, 45 fichiers, `nadia-prof` decide P11, l'auteur propose et `nadia-prof` decide P1, P3, P5, P9 non equivalent a `DEGRADE` | **appliques** (section 1 de cette review) |
| 5.1 | reprises de sciences CM1 retirees comme postes de sprint | decision ecrite ; les lignes `SCI-CM1-15` et `SCI-CM1-18` restent `non couvert` ; la question a l'utilisatrice est a la section 9 |
| 5.3 | `MAT-CE1-02` moitie designation retiree comme poste | decision ecrite ; ligne inchangee |
| 6 | `SYC-CE1-R1`, `MUL-CE1-R1` et item 16 de `FRA-CE1-25` : second report ; `ART-CP-10` : quatrieme sprint hors perimetre | reportes, aucun fichier de s53 ne les traite ; `DEC-0112` dit qu'un troisieme report n'est plus permis pour les trois premiers |
| 7 | une instance de `nadia-prof`, `PAR-9` non declenchee | tenu : une seule instance, un seul passage (`ARBITRAGE-PREALABLE-s53-...`) |
| 8 | branchement des 45 paquets : non engage | tenu (C3 : 0) ; proposition reportee au backlog, section 9 |

## 4. Done technique

**Juge : la CI, tous les jobs de `.github/workflows/ci.yml`** : `prefixes`, `chemins`, `outils`, `build`. **Rien n'est pousse : la CI
n'a rendu aucun verdict sur ces commits.** `gh run list` rendait 404 depuis ce poste a la lecture de testabilite de `yanis-qa` ; je ne
l'ai pas retente.

### 4.1 Ce que le rapport qualite de `yanis-qa` prouve

`rapport-qualite.md`, arbre `54953faa` (fusion des corrections de s53), les quatre jobs lances par `yanis-qa` : `prefixes` vert,
`chemins` vert, `outils` vert (9 juges), `build` vert (`bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, `BUILD SUCCESS`,
**2174 tests**, 0 echec, 0 erreur, 0 ignore). Reserves ecrites par lui : le pas `package` n'est pas rejoue, le job `prefixes` n'exerce
pas la branche `BASE`, aucune mesure sous Linux, aucun test d'acceptation ne nomme une story de s53.

### 4.2 Ce que le rapport ne couvre pas, et ce que j'ai relance

Le rapport porte sur `54953faa`. Le tip revu est `560b0fd2` : `git diff --stat 54953faa HEAD` donne 7 fichiers, dont **un seul hors
`squad/`** : `cp-qlm-situer-la-france.yaml`, deux lignes de **commentaire** d'en-tete (`git diff` : les lignes ajoutees et retirees
commencent par `#`). Les six autres sont des fichiers de `squad/` : trois documents neufs (`rapport-qualite.md`, `confirmation-archunit.md`,
`relecture-cloture.md`) et trois corrections de texte (l'arbitrage, `DEC-0112`, `planning.md`).

J'ai relance les quatre jobs sur `560b0fd2`, avant d'ecrire les fichiers de cloture :

| Job | Commande | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify`, racine, reacteur complet, de 15:08 a 15:12 | `BUILD SUCCESS`, 4 min 49 s, six modules `SUCCESS`, code 0 ; **2174 tests** |
| `prefixes` | les deux scripts de test, `commit-atteignable.sh origin/round-two`, puis `git log --no-merges --format=%s origin/round-two..HEAD \| bash .github/scripts/verifier-prefixes.sh` | vert, codes 0, 24 sujets |
| `chemins` | `verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | vert, codes 0 |
| `outils` | boucle du workflow, trois motifs ; `python3` repond sur ce poste, la boucle l'a choisi | vert, **9 juges, 9 codes 0** |

Le resultat des jobs relances **apres** l'ecriture des fichiers de cloture est dans `RAPPORT.md`, qui est ecrit en dernier. Le pas
`package` du job `build` n'est pas rejoue.

**Total des tests, etabli sur l'arbre fusionne** : somme de l'attribut `tests` des rapports XML de surefire et de failsafe apres le
`clean verify` ci-dessus (script de lecture seule, les fichiers `TEST-*.xml` de chaque `target/`) :

| Module | Surefire | Failsafe | Echecs | Erreurs | Ignores |
|---|---|---|---|---|---|
| `domain` | 120 | - | 0 | 0 | 0 |
| `application` | 17 | - | 0 | 0 | 0 |
| `adapter-web` | 401 | - | 0 | 0 | 0 |
| `adapter-content` | 1318 | - | 0 | 0 | 0 |
| `bootstrap` | 312 | 6 | 0 | 0 | 0 |

**2174 tests (2168 surefire, 6 failsafe), 0 echec.** JDK : 20 occurrences de `using Java 21.0.10` dans le journal, une seule valeur ;
la propriete `java.version` des rapports XML est `21.0.10` dans tous.

**Origine de l'ecart avec les annonces** :

| Annonce | Valeur | Arbre | Origine de l'ecart avec 2174 |
|---|---|---|---|
| Total de cloture de s52 | 2174 | `509552f0` | 0 : s53 ajoute 45 paquets de contenu qui entrent dans des tests existants et n'ajoute aucun test (`git diff --name-status 9d44d510 HEAD` : 4 fichiers de test **modifies**, aucun ajoute) |
| Lecture de testabilite de `yanis-qa` | 2168 dont 5 echecs | `5c1353a9` | +6 : c'est un total surefire seul, construction arretee a `bootstrap`, failsafe n'avait pas tourne ; a perimetre egal 2168 contre 2168 (rapport qualite section 2) |
| Rapport qualite de `yanis-qa` | 2174 | `54953faa` | 0 |
| Confirmation de `theo-archi` | 2174 (229 dans `com.recreo.archi`) | `54953faa` | 0 ; mes mesures sur `560b0fd2` donnent les memes comptes |

## 5. Confirmation ArchUnit - `theo-archi`

`confirmation-archunit.md`, arbre `54953faa`, reacteur complet `clean verify` : **229 tests dans `com.recreo.archi`, 0 echec**
(32 classes, 229 retrouves par moi sur `560b0fd2` dans les rapports XML). Compte de regles inchange depuis `9d44d510` : **39 / 36
contre-exemples compris, 37 / 36 exclus** (methode d'`ADR-0104`). **Aucune regle ajoutee, retiree ni modifiee.** 0 ligne de
`src/main/java` dans le diff du sprint, aucun fichier de `domain` ni d'`application`. Les quatre tests modifies sont des declarations
de paquets et des recomptages, chacun juge a egalite dans les deux sens, **aucune assertion affaiblie** (section 3.2 de son fichier).
**Aucun `ADR` n'est cree ni amende.** Les cinq echecs de la base sont leves par correction de contenu, jamais par exemption : les
plafonds restent a 0.

Limites qu'il nomme : 53 items de quatre paquets neufs sont hors du calcul de rang automatique (`PAQUETS_A_ITEMS_ECARTES`), sans
paragraphe de motif date a la difference des six entrees anterieures ; la prose "certains items portent un seul distracteur" reste
dans le motif de `cp-qlm-vocabulaire-de-position`. Statut d'`ADR-0104` : `Propose` sur le disque (`grep` de la ligne `Statut`),
non tranche par s53.

## 6. Relecture - `relecteur-pedagogique`

`relecture-cloture.md`, plage `9d44d510..HEAD` (59 fichiers). **Corrections appliquees (texte seul), quatre** : bloc de P2 de
l'arbitrage (espaces insecables en tete, diverge du paquet), en-tete `FER-3` de `cp-qlm-situer-la-france` (items 6 a 10 pour le point
cardinal, items 5, 11 et 12 pour l'emboitement ; commentaire seulement), "une espace" dans `DEC-0112` et dans `planning.md`. Aucun
Java edite, aucune bonne reponse ni aucun nombre d'items modifie. **Cinq points routes sans correction, R1 a R5** : obstacles 5 a 9 (R4 est le defaut des 14 `enonce` de `cm1-grands-nombres`).
Limites qu'il nomme : jobs `chemins`, `prefixes`, `outils` non lances par lui ; sens pedagogique des items non relu.

## 7. Tableau des obstacles du sprint

Daily non journalise : les obstacles ci-dessous sont ceux que j'ai classes et routes pendant le sprint et a la cloture.

| # | Obstacle | Categorie | Route a | Decision liee | Statut a la cloture |
|---|---|---|---|---|---|
| 1 | **Base de s53 rouge** : 5 echecs de `bootstrap` dans 3 classes (`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` 2, `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest` 2, `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` 1), sur `cp-emc-personnes-differentes`, `cp-qlm-modes-de-vie`, `cp-qlm-vocabulaire-de-position`, `ce1-mots-invariables` (`lecture-testabilite.md` section 1, `avis-faisabilite.md` section 1). **Cause lue dans les fichiers** : ces garde-fous derivent leur population du repertoire de contenu et jugent donc un paquet non branche ; `70b1cdc1` a declare les 45 paquets dans quatre tests d'`adapter-content` et dans aucun de `bootstrap`. **Cause rapportee par l'agent lanceur de cette tache, aucun journal du depot ne la prouve** : la session de contenu du 2026-10-07 a ete poussee apres un `mvn` limite a `adapter-content`. Aucun des quatre paquets n'est dans P1 a P11, sauf l'item 7 de `vocabulaire-de-position` (P5) | Qualite / process de push | `yanis-qa` (mesure), `theo-archi` (avis), `lea-dev`, `marc-dev` (correction) | aucune ; `ADR-0013` obligation 1 (comportement voulu, `confirmation-archunit.md` section 4) | **Resolu en contenu, constate.** `S53-REL-10` : rouge sur `5c1353a9` (5 echecs), vert sur `54953faa` (0), meme commande (`rapport-qualite.md` section 3), commits `32262d3b`, `c2531df6`, `90d3532c`. Aucun garde-fou assoupli. Lecon dans `retro.md`. **`nadia-prof` n'a rien ecrit apres ces reecritures** : `git diff --name-status 9d44d510 HEAD -- squad/conformite` ne rend que son arbitrage, commite a 14:20 ; les reecritures le sont de 14:31 a 14:34. Sa section 12 avait fixe ce qui devait rester vrai |
| 2 | **Le tronc deja pousse est rouge cote `bootstrap`** : `origin/round-two` est `76a50e8b`. `git diff --name-only 76a50e8b 5c1353a9` rend un seul fichier, `planning.md` ; la base mesuree rouge (`5c1353a9`) et `76a50e8b` ont donc les memes sources de test et de contenu. **Je ne l'ai pas mesure directement sur `76a50e8b`** | Qualite / etat du tronc | utilisatrice (le push) | aucune | **Ouvert.** Seul le push de s53 corrige le tronc distant ; je n'ai rien pousse (consigne : pas de push). `gh run list` rendait 404, le verdict de la CI sur `76a50e8b` n'est pas connu de moi |
| 3 | **`S53-REL-10` sans acte d'ouverture** : la story est nommee dans trois commits et dans les rapports de cloture ; `grep -rl "S53-REL-10" --include=*.md .` ne la rend que dans `rapport-qualite.md`, `confirmation-archunit.md` et `relecture-cloture.md`. Ni le planning, ni le backlog, ni `DEC-0112` ne la portent. Les corrections de contenu qu'elle recouvre sont hors de P1 a P11 | Process / perimetre | `camille-po` | `DEC-0112` (amendement a ecrire) | **Ouvert.** Une story absorbee en cours de sprint s'acte par un amendement a la decision d'origine, dans le meme geste que sa fusion : il n'existe pas. Je ne l'ecris pas (la decision est a `camille-po`) et je n'ecris pas un amendement de planning retrospectif |
| 4 | **Trois postes de `DEC-0112` section 5 non livres** (`CRP-CM1-R1` a `R3`, `PAR-4`, journal de tokens) : section 3 de cette review. Portes a s51 et s52 sans livraison (`DEC-0112` section 5), engages a s53, non livres | Produit / capacite | `camille-po`, utilisatrice | `DEC-0112` sections 5.2, 5.4, 5.5 | **Ouvert, arbitrage de l'utilisatrice en attente.** `DEC-0112` 5.5 interdit de les reporter en silence : ils sont ecrits ici comme non livres. `CRP-CM1-R1` touche un jeu servi |
| 5 | **R1 de la relecture** : `DEC-0112` section 0 mesure 3 et section 3 Q4 ecrivent que l'espace insecable n'est pas la cause ("0 fichier") ; `lecture-testabilite.md` ecrit 0 sur 182. `relecture-p11.md` section 2, `avis-faisabilite.md` section 2 et `propositions-lea-dev.md` rendent **4 fichiers** (`ce1-accord-groupe-nominal`, `ce1-classes-de-mots`, `ce1-inference`, `ce1-texte-narratif`) ; le relecteur l'a refait. Cause du faux zero : non etablie (piege de `grep -P` hors mode UTF-8, decrit dans `lecture-testabilite.md`) | Redaction / chiffre | `camille-po` (amendement de `DEC-0112`), `yanis-qa` (sa lecture) | `DEC-0112` | **Ouvert.** Sans effet sur la decision de P11, que `nadia-prof` fonde sur le champ non affiche, et dont l'arbitrage section 11 ecrit les quatre fichiers |
| 6 | **R2 : provenance** : `DEC-0112` section 3, Q4 et Q8, ecrit "Relaye de l'utilisatrice" ; la section 0 de la meme `DEC` dit "Je n'ai pas ses mots". Ce n'est pas l'une des trois formes de `DEC-0062` (la forme 2 exige ses mots cites). Meme remarque pour `relecture-p11.md` lignes 7 a 9 | Redaction / provenance | `camille-po` (`DEC-0112`), `relecteur-pedagogique` (`relecture-p11.md`) | `DEC-0062` | **Ouvert.** Le planning a evite la forme : il cite le message de l'orchestrateur comme provenance, pas comme motif |
| 7 | **R3 : "six fichiers"** : l'arbitrage section 11 ecrit que six fichiers portent une espace ordinaire avant ":" dans un `enonce` ; la mesure du relecteur en trouve **un** (`cm1-grands-nombres`, 14 `enonce`), les cinq autres noms viennent de commentaires. `propositions-lea-dev.md` ligne 264 porte le meme chiffre | Redaction / chiffre | `nadia-prof` (son document) | arbitrage s53 section 11 | **Ouvert.** Le chiffre est aussi dans la consigne de la relecture ; je ne le relaie pas |
| 8 | **R4 : defaut de ponctuation de texte d'enfant** : 14 lignes `enonce` de `cm1-grands-nombres.yaml` (7 "Ecris en chiffres : ...", 7 "Lis ce nombre : ...") portent une espace ordinaire avant ":". Les remplacer par U+00A0 fait echouer `chacunDesQuatreVerbesEstRepresenteParAuMoinsUnQuartDuPaquet` (28 attendus, 14 obtenus), parce que `PaquetCm1GrandsNombresTest` classe les items par `startsWith` sur deux constantes a espace ordinaire. Le relecteur a annule son essai | Technique / contenu | `theo-archi` ou le developpeur du test | `DEC-0002` (amendement du 2026-08-04), `ADR-0067` (cliquet `TYP-1`) | **Ouvert, reporte au backlog de s54** (section "Reports de s53" de `docs/backlog/backlog.md`) : les 14 lignes et les deux constantes `PREFIXE_ECRIRE` et `PREFIXE_LIRE` se corrigent dans **un meme commit** |
| 9 | **R5 : "onze paquets plus anciens"** : la story `S53SPE-RELECTURE` du backlog garde ce chiffre ; la mesure donne 9 (`relecture-p11.md` section 3), `DEC-0112` et l'arbitrage ne l'expliquent pas | Redaction / chiffre | `camille-po` | - | **Ouvert.** Erratum a poser comme pour "51 paquets" |
| 10 | **Journal de tokens sans proprietaire** : `S51-OUTIL-JOURNAL-TOKENS`, engage par `DEC-0112` 5.5, n'est affecte a personne. Prix rendu par `theo-archi` (`confirmation-archunit.md` section 5) : une fonction de 20 a 30 lignes, un fichier de test, aucun changement de CI. **Fait de perimetre** : `ecrire_rapport_sprint` a le meme defaut, et `outils/rapport-tokens.py` sans `--sprint` reecrit le `RAPPORT-tokens.md` de chaque sprint (la lecture de `theo-archi` : lignes 462 a 464) ; les criteres de la story ne visent que le journal | Technique / capacite | `camille-po` (`DEC` de s54 : engager avec un proprietaire, ou retirer avec motif), `theo-archi` | `DEC-0112` 5.5 | **Ouvert.** `theo-archi` : ce qui manque n'est pas la capacite, c'est un proprietaire, et le sprint est a sa cloture |
| 11 | **Ecarts de criteres du planning, releves par `yanis-qa`** (`lecture-testabilite.md` section 5, sept) : C4 inatteignable a la base (cf. obstacle 1), le `grep -c` de P3 qui ne peut pas viser un item, "ne contient pas la bonne reponse" sans garde-fou, la ligne 15 de `cm1-hda-epoque-civilisation`, la ligne 114 de `ce1-texte-documentaire`, le motif d'espace insecable, `python3` dans le job `outils` | Process / redaction du planning | `sofia-sm` | - | **Constate, planning non amende.** Les commandes de rechange sont dans `rapport-qualite.md` et dans `lecture-testabilite.md`. `python3` repond sur ce poste (`yanis-qa` ne sait pas pourquoi ; mon execution du job a choisi `python3`) |
| 12 | **Reserves de `yanis-qa` et `theo-archi` non levees** : motif de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` non eprouve par mutation ; 53 items ecartes du rang sans paragraphe de motif date ; prose "certains items portent un seul distracteur" dans le motif de `vocabulaire-de-position` ; item "un kilogramme, c'est mille grammes" des items 1 et 2 de `ce1-masses-contenances` laisse tel quel (`nadia-prof` ne l'exige pas) | Qualite | `yanis-qa`, `theo-archi`, `lea-dev` | - | **Ouvert, non engage.** Aucune n'est une condition de verdict ; chacune est un fait a lire au branchement |
| 13 | **Ecart de toolchains du poste** (obstacle 8 de s52, action A10) : mon `clean verify` imprime encore *"Verdict rendu sur le fichier de toolchains que Maven lit, qui n'est pas celui de ce shell."* | Technique / poste | utilisatrice | - | **Ouvert, hors depot.** Sans effet sur le verdict : JDK 21.0.10 rapporte par la machine virtuelle des tests |
| 14 | **Reports et questions que `DEC-0112` pose a l'utilisatrice** : sciences CM1 (5.1), `ART-CP-10` (6), branchement des 45 paquets (8) | Produit | utilisatrice, via `camille-po` | `DEC-0112` sections 5.1, 6, 8, 9 | **Ouvert.** Portees a la section 9 de cette review et dans `RAPPORT.md`. `ART-CP-10` : quatrieme sprint hors perimetre |
| 15 | **Actions de la retro de s52 non traitees par s53** : A1 (verification de la condition (ii)), A2 (prix du rejeu de la symetrie), A4 (`Statut global` des fiches), A6 (niveau `ERROR` epingle), A7 (fins de ligne), A8 (statut d'`ADR-0104`) | Divers | leurs proprietaires (retro s52) | - | **Ouvert.** Preuve : s53 ajoute un seul arbitrage et une seule `DEC`, aucune fiche, aucun `ADR`, et `git diff --name-only 9d44d510 HEAD` ne contient ni `DEC-0111`, ni une fiche, ni `UnePartieOuverteSurUnJeuRetireNeServeAucunResultatTest`. A5 (fichier `c3`) et A10 : hors depot, non verifies par moi |
| 16 | **Questions du planning, Q1 a Q8** | Divers | divers | voir ligne | **Closes sur fichier existant.** Q1 : `DEC-0112`. Q2 : `DEC-0112` section 3 (45 fichiers ; le sujet du commit `76a50e8b`, pousse, dit 51 a tort et n'est pas reecrit). Q3 : arbitrage section 14 (0,5). Q4 : `DEC-0112` section 3 Q4, arbitrage section 11. Q5 : `avis-faisabilite.md` section 3, arbitrage sections 5 et 10. Q6 : `DEC-0112` section 4. Q7 : arbitrage section 4.4. Q8 : `DEC-0112` section 3 Q8 |

Rappel : un obstacle n'est clos que si le fichier de decision existe physiquement. Clos sur fichier existant : la ligne 16 et la
partie "resolu en contenu" de la ligne 1 (`rapport-qualite.md` section 3). Les lignes 2 a 15 sont ouvertes ou constatees.

## 8. Done pedagogique - `nadia-prof`

**Aucun Done pedagogique n'est prononce a s53.** Aucun paquet n'est joue ni branche. `nadia-prof` a rendu un arbitrage prealable
(`ARBITRAGE-PREALABLE-s53-points-de-relecture-p1-a-p11.md`) qui ecrit en tete : *"Ce document ne prononce aucun Done pedagogique,
aucun verdict, aucun statut de matrice."* Chaque paquet passera devant elle en premier passage complet, au tarif d'un lot, le jour
ou il sera branche. Six points restent ouverts jusqu'au premier passage de chaque paquet (section 13 de son arbitrage), dont la
fuite d'indice d'un item vers un autre dans `cm1-emc-principes-republique` et `ce1-masses-contenances`. Le tableau de couverture ne
bouge pas : la matrice n'est pas editee.

## 9. Questions a porter a l'utilisatrice

| Code | Question | Source |
|---|---|---|
| U1 | Les trois postes de `DEC-0112` section 5 non livres (`CRP-CM1-R1` a `R3`, `PAR-4`, journal de tokens) : les engager a s54 avec un proprietaire, ou les retirer avec motif. `CRP-CM1-R1` concerne un jeu servi a l'enfant | section 3, obstacles 4 et 10 |
| U2 | Brancher les 45 paquets, et dans quel ordre de matieres. Ordre de grandeur du planning, borne basse non verifiee : 45,0 au tarif de 1,0 par paquet, au moins 6 sprints a 8,0 | `DEC-0112` section 8 ; backlog, "Reports de s53" |
| U3 | Veut-elle que les deux lignes de sciences CM1 (`SCI-CM1-15`, `SCI-CM1-18`) soient couvertes, et accepte-t-elle un sprint sous la barre de `nadia-prof` | `DEC-0112` section 5.1 |
| U4 | `ART-CP-10` : un sprint, ou une declaration `non couvrable en l'etat` par `nadia-prof` | `DEC-0112` section 6 |
| U5 | Pousser s53 : le tronc distant est rouge cote `bootstrap`, ces 37 commits le remettent au vert | obstacle 2 |

## 10. Ce que cette review ne fait pas

- Elle ne prononce aucun Done pedagogique et ne change aucun statut de la matrice.
- Elle n'annonce pas la porte technique verte pour la CI : les jobs ont tourne en local, la CI n'a rien rendu.
- Elle n'edite ni `DEC-0112`, ni la matrice, ni une fiche, ni un `ADR`, ni le registre d'identifiants.
- Elle ne decide aucun des points qu'elle route.

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee par cette review.** Decisions du sprint, rapportees et non prises ici (liste dans
`RAPPORT.md`) : `DEC-0112` (creee le 2026-10-07, correction de graphie par le relecteur en cloture, aucun amendement) et un arbitrage
de `nadia-prof`. Aucune `ADR`.
