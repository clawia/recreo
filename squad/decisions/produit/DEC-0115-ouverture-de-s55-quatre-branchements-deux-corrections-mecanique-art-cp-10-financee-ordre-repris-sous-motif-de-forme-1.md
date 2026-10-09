# DEC-0115 - Ouverture de s55 : quatre branchements (deux de francais CE1, deux d'EMC CM1), deux corrections de jeux servis,
# la mecanique d'`ART-CP-10` financee (dispositif seul, sans paquet ni audit), ordre des matieres repris sous motif de forme 1

- **Type** : decision d'ouverture de sprint (perimetre, financement, ordre, reports, routage)
- **Date** : 2026-10-08
- **Auteur** : `camille-po`
- **Sprint** : s55
- **Competences programme visees** : `FRA-CE1-14`, `FRA-CE1-12` (francais CE1, paquets proposes par le planning, substituables par
  `nadia-prof`, section 2), `EMC-CM1-03`, `EMC-CM1-06` (EMC CM1) pour les quatre branchements ; `EMC-CP-03` (`couvert-valide`, non rouverte)
  pour `S54-EXC-CATEGORIE` ; `ART-CP-10` pour `S54-ART-RES-R1` a `R3` (la ligne reste `non couvert`) et pour la mecanique (section 5, la ligne ne
  ferme pas). Les autres postes sont transverses - pas de contenu pedagogique. **Aucune ligne de matrice ne change de statut par cette decision** :
  elle n'ouvre que des audits ; la matrice reste a 137 / 182.
- **Statut** : **Acceptee** le 2026-10-09 (A.22), sur les mots de l'utilisatrice en forme 2. Etait `Proposee` du 2026-10-08 au 2026-10-09 : le financement de la
  mecanique d'`ART-CP-10` (section 5, point 1), un choix de l'utilisatrice et non une mesure, n'avait que la forme 3 de `DEC-0062` pour source. Tout le reste porte un motif de forme 1.
- **Lien** : `DEC-0114` (sections 0.4, 6, 7 ; A.20, A.21 ; amendee par A.27, ce commit) ; `DEC-0113` (`Refusee`, section 9) ; `DEC-0112` section 6 ;
  `DEC-0111` A.14 ; `DEC-0110` 17.4 ; `DEC-0062` (regles 3.2 et 3.5) ; `ADR-0104` (`Accepte`) ;
  `squad/sprints/s55-francais-ce1-emc-cm1-et-reserves-servies/planning.md` ;
  `squad/sprints/s54-branchement-des-premiers-paquets-et-reports-de-s53/avis-faisabilite.md` (T6 et amendement de cloture), `proposition-s55.md`,
  `review.md` ; `squad/conformite/ARBITRAGE-PREALABLE-s54-instance-a-francais-ce1.md` (Q6, Q7) ; `docs/backlog/backlog.md` ("Sprint 55")

---

## 0. Provenance et methode

### 0.1 Ce que j'ai recu de l'agent qui m'a lance, cite tel que recu et fige a la reception (`DEC-0062` regle 3.5)

Un message de l'agent qui m'a lance, le 2026-10-08, **sans source dans le depot**. Il les donne comme des reponses de l'utilisatrice, "messages relayes".
Je n'ai pas le message d'origine. Seul le mot `ok` est donne entre guillemets ; le reste est le texte de l'agent, que je ne mets pas dans sa bouche.

1. **CI** : *"CI verte sur fbe07d79"*. Aucun job n'est nomme, aucun lien. Je ne l'ai pas verifiee (le planning de s55 note que `gh run list --branch round-two`
   rend `HTTP 404` depuis le poste de `sofia-sm`). Elle fonde le fait qu'elle a ete annoncee, pas que les quatre jobs de `ci.yml` (`prefixes`, `chemins`, `outils`, `build`) sont verts.
2. **Q1** : l'utilisatrice repond `"ok"` ; l'agent precise qu'elle valide l'ordre des matieres au-dela de s54 propose : `G1`/`G2` (francais CE1), puis `G6`/`G5`
   (sciences et EMC CM1), puis arbitrage de `nadia-prof` sur `G7` a `G11`, puis `G3`/`G4`. Ce que `ok` valide est ce que `proposition-s55.md` section 3 et la section 7 de `DEC-0114`
   ecrivent ; il ne dit aucun motif.
3. **Q2** : *"la mecanique d'ART-CP-10 est financee DANS CE SPRINT (s55)"*, texte de l'agent, majuscules comprises. Je ne sais pas lesquels de ces mots sont les siens.

**Borne de la forme 3 (`DEC-0062` section 3.2) : ces trois messages fondent l'ordre dans lequel le travail se fait, jamais sa raison.**

### 0.2 Sources de forme 1, par auteur

`sofia-sm` : `planning.md` de s55 (constats, mesures, tarifs proposes), `proposition-s55.md`, `review.md` et `retro.md` de s54 ; `camille-po` : `DEC-0114`,
`DEC-0113`, backlog "Sprint 54", matrice ; `nadia-prof` : `ARBITRAGE-PREALABLE-s54-instance-a-francais-ce1.md` (Q6, Q7), `PAR-8`, `PAR-9`, `PAR-11`, `PAR-13`
(arbitrages de s29, s30, s31, s32, lus en citation dans le planning) ; `theo-archi` : `avis-faisabilite.md` de s54 (T6, amendement de cloture), `ADR-0104`.
Je n'ai pas relu les 39 paquets restants ; je cite le planning pour ce que je n'ai pas mesure et je le dis.

### 0.3 Mesures lancees pour cette decision (arbre `round-two` a `98e4bbcd`, worktree `s55-camille-dec`, 2026-10-08)

| Mesure | Resultat |
|---|---|
| `grep -c "paquet: <nom>$"` sur `catalogue.yaml`, pour `ce1-accord-sujet-verbe`, `ce1-classes-de-mots`, `cm1-emc-respect-differences`, `cm1-emc-principes-republique` | **0, 0, 0, 0** (rouge : C3 du planning) |
| Les 13 paquets de `G1`/`G2` : fichier dans `contenu/` et entree de catalogue | **13 fichiers, 0 entree** |
| Items et ligne de referentiel des quatre | 24 (`FRA-CE1-14`), 24 (`FRA-CE1-12`), 11 (`EMC-CM1-03`), 11 (`EMC-CM1-06`) |
| `EXC-R15` : `grep -c 'bonneReponse: "une moquerie qui se répète"'` sur `cp-emc-exclusion.yaml` ; `grep -c "une mise à l'écart qui se répète"` ; `grep -c "dis si c'est une moquerie qui se répète"` sur `catalogue.yaml` | **3, 0, 1** (rouge) |
| `ART-RES` : `grep -c 'va avec la statue, le tableau ou la photographie'` sur `catalogue.yaml` ; `grep -cE 'une peintre\|une photographe\|une sculptrice'` sur le paquet | **1, 0** (rouge) |
| `manche.html` : `grep -c "oeuvres/' + \${proposition}"` ; `grep -c "oeuvres/' + \${illustration}"` ; `grep -c 'th:text="${proposition}"'` | **0, 1, 1** |
| `git grep -l propositionsEnImage` dans `adapters`, `bootstrap`, `domain`, `application` | **0 fichier** (le nom n'existe que dans `ADR-0104` et l'avis) |
| `RessourcesTiercesTest`, methode `troisReferencesDeLaProductionSontDesIllustrationsOuDesReproductionsDeContenu` : `hasSize(3)` | **1** |
| Compte d'`ArchRule` et d'`@ArchTest` (methode d'`ADR-0104`, `git ls-files '*.java'`) | **39 et 36** |
| `ls squad/conformite \| grep -ci s55` | **0** : aucun arbitrage prealable de `nadia-prof` pour s55 |
| Matrice, commande de la "Methode du compte" | 137 / 182, relue dans la matrice, non relancee |

Ces mesures ne sont pas des mesures de la CI. Jobs lances pour ce commit : voir section 8.

### 0.4 Ce que `DEC-0114` section 0.4 exige, et ce que `ok` en satisfait

La section 0.4 dit : `Acceptee` quand **(a)** les mots de l'utilisatrice sur l'ordre sont ecrits dans le depot en forme 2 avec leur date, ou **(b)** *"la `DEC` d'ouverture
de s55 reprend l'ordre sous son propre motif de forme 1"*.

- **(a) n'est pas satisfaite.** `ok` m'arrive par un relais, sous la forme 3 ; je n'ai ni le message d'origine ni une date de sa main. Je ne le cite pas en forme 2.
- **`ok` n'est pas un motif, et je ne l'utilise pas comme tel.** Il valide l'ordre ; il ne dit pas pourquoi. Lire (b) comme *"sous le motif de la reponse relayee"*
  reviendrait a promouvoir la forme 3 en motif, ce que la section 0.4 interdit en toutes lettres.
- **(b) est satisfaite si, et seulement si, chaque rang de l'ordre porte ci-dessous un motif de forme 1 que je peux verifier** : section 3. Le motif de chaque rang
  est mesure ou cite d'un auteur ; `ok` fonde que l'ordre est suivi, la section 3 fonde pourquoi il est defendable. **Ce que (b) ne couvre pas** : un rang dont le motif
  ne serait que mon assertion. Je nomme en section 3 le seul rang dont je n'ai pas remesure la source (rang 4).

### 0.5 Statut de cette decision

**`Acceptee` le 2026-10-09 (A.22).** Etait `Proposee`, parce que la section 5 point 1 (financement de la mecanique) n'a que la forme 3 pour source : un financement est un choix, aucune mesure ne le motive.
Elle passe a `Acceptee` quand les mots de l'utilisatrice sur ce financement sont ecrits dans le depot en forme 2 avec leur date, ou quand elle l'a confirme sur le prix de la section 5.
Le point ne bloque rien : la section 5 s'applique des maintenant (meme regime que `DEC-0114` pour l'ordre, s54). Si ses mots divergent, la section 5 est amendee et les sections 1 a 4 ne sont pas touchees.

---

## 1. Perimetre : ce que s55 engage

**Objectif gele du planning, repris** : brancher quatre paquets au catalogue et les presenter a `nadia-prof` (deux de francais CE1 sur l'instance A, deux d'EMC CM1 sur l'instance B),
solder deux jeux servis dont un texte est faux, financer le dispositif d'`ART-CP-10`. Au mieux quatre lignes de matrice ferment : 137/182 (75,27 %) a 141/182 (77,47 %). Ce n'est pas une promesse :
un Done refuse reste un refus, et aucune ligne ne bouge avant le verdict de `nadia-prof` dans la fiche (C9 du planning). **Les tarifs ci-dessous sont proposes par le planning ; `nadia-prof` ecrit les siens dans son arbitrage
prealable de s55, qui n'existe pas encore.**

| Story | Competence | Fondement (forme) | Instance, tarif propose | Dev propose (planning) |
|---|---|---|---|---|
| `S54-EXC-CATEGORIE` (`EXC-R15`) | `EMC-CP-03`, `couvert-valide`, non rouverte | un texte servi est *faux*, mot pour mot de `nadia-prof` ; backlog ; criteres rouges (3, 0, 1) | B, 0,25 | `marc-dev`, premiere a fusionner |
| `S54-ART-RES-R1` a `R3` | `ART-CP-10`, reste `non couvert` | fiche de l'instance C ; consigne fausse pour les items 7 et 10 ; criteres rouges (1, 0) ; `PAR-14` (sous la moitie des items) | **sous Q5** : B, 0,5 ; sans instance, report numero 1 | `lea-dev`, avec `EXC-R15` en tete de file |
| `S55-BR-FRA-CE1-L1` | `FRA-CE1-14` (`ce1-accord-sujet-verbe`) | planning (forme 1) ; chemin critique, section 3 | A, 1,0 | `marc-dev` |
| `S55-BR-FRA-CE1-L2` | `FRA-CE1-12` (`ce1-classes-de-mots`) | idem | A, 1,0 | `marc-dev` |
| `S55-BR-EMC-CM1-03` | `EMC-CM1-03` (`cm1-emc-respect-differences`) | planning ; accepte sans changement a s53 (P9) | B, 1,0 | `lea-dev` |
| `S55-BR-EMC-CM1-06` | `EMC-CM1-06` (`cm1-emc-principes-republique`) | planning ; six indices corriges a s53 (P3, P4) | B, 1,0 | `lea-dev` |
| `S54-PONCT-GN-INDICES` | transverse - pas de contenu pedagogique (ligne `cm1-grands-nombres` non rouverte) | `DEC-0002`, amendement du 2026-08-04 : espaces insecables avant `? ! : ;` ; 12 indices servis ne la tiennent pas | aucun (0,0 pour `nadia-prof`) | `lea-dev`, derniere de sa file |
| `S55-ART-CP-10-MECANIQUE` | `ART-CP-10` (la ligne ne ferme pas) | section 5 | aucun budget `PAR` identifie, voir 5.3 | non affecte (5.4) |

**Deux choix du planning que je reprends et que `nadia-prof` peut defaire.** (1) Les deux paquets de francais CE1 sont ceux que le planning propose (`-14`, `-12`) ; elle peut les remplacer dans son arbitrage
prealable (`PAR-13` sur `RCT`, `PRN`, `INF`, `DOC`, `OMP`, `RLP`, `FAM` est a elle) : **la story change de nom, l'affectation et le tarif restent**, sans amender cette `DEC`. (2) La paire `-12`/`-13` : auditer `classes-de-mots` seul ou avec `verbe-sujet` (Q3) est a elle.

**`PONCT` est engagee, contrairement au planning qui la proposait** : elle a un proprietaire, des criteres rouges (`grep -cP '^ *indice: ".* [:;!?]'` rend 12, doit rendre 0), un prix de 0,0 pour `nadia-prof` et elle corrige un
texte servi a l'enfant contre une exigence ecrite (`DEC-0002`). Le planning ne la retenait pas pour une raison de file de `lea-dev` ; si cette file devient le chemin critique, `sofia-sm` la deplace et l'ecrit.

**Criteres d'acceptation** : C1 a C9 du planning et tableau "Specifiques", repris dans les stories du backlog, section "Sprint 55". **Chacun a ete verifie contre l'existant** (section 0.3) : aucun n'est vert sur ce qu'il corrige.
Pour `EXC-R15` et `ART-RES`, les criteres reprennent les mots de `nadia-prof`, pas une propriete plus commode a tester.

### Capacite, telle que le planning la compte (`PAR-9`, A.20 de `DEC-0114`)

| | A (francais CE1) | B (EMC CM1, EMC CP, arts plastiques CP si Q5) |
|---|---|---|
| Lots | 1,0 + 1,0 | 1,0 + 1,0 |
| Arbitrage prealable | 0,5 | 0,5 |
| Corrections et reserves | - | `EXC-R15` 0,25 + `ART-RES` 0,5 |
| Marge de rejeu nommee, allouee a aucun lot | 0,5 | 0,5 |
| **Total / 4,0** | **3,0** | **3,75** |
| Reserve non allouee | 1,0 | 0,25 |

**6,75 sur la borne de 8,0 de `PAR-8`, comptee selon `PAR-9` telle qu'ecrite, deux instances.** Aucune troisieme instance : A.20 vaut, le 9,5 de s54 n'est pas un chiffre de depart ; une troisieme instance
demanderait son propre ecart, son propre motif et sa propre autorisation. Je n'ai recompte aucun de ces tarifs : ce sont ceux du bareme, par le planning.

---

## 2. Ce que cette decision ne tranche pas : O1, O2, O3 et les questions du planning

**O1, O2, O3 ne sont pas tranchees ici.** Elles dependent de ce que `nadia-prof` ecrit (Q3, Q4) et, depuis la section 5, de ce que son arbitrage sur la forme de la mecanique prend dans la reserve de A.

| Question du planning | Etat apres cette decision | Route |
|---|---|---|
| Q3 : emploi de la reserve de 1,0 de A (O1 : un troisieme lot, O2 : rien, O3 : arbitrage sur `G7` a `G11`) ; lecture de `PAR-13` ; choix des paquets ; paire `-12`/`-13` | **ouverte.** Aucune story `L3` n'est engagee. La reserve de A reste non allouee jusqu'a un amendement de cette `DEC`, signe de `camille-po`, ecrit apres son arbitrage prealable et avant l'ouverture de tout worktree concerne (regle de `DEC-0114` section 4) | `nadia-prof`, puis `camille-po` |
| Q4 : prix et place de l'arbitrage sur `G7` a `G11` | **ouverte** : le seul repere du depot est 0,5 pour 51 items lus (A, s54) ; je n'extrapole pas a 216 items | `nadia-prof` |
| Q5 : instance qui relit `S54-ART-RES-R1` a `R3` | **ouverte** ; sans instance, report numero 1 de ces trois reserves (section 4) | `nadia-prof` |
| Q6 : deux instances de francais CE1 (`DEC-0114` 7.2) | **non engagee** : exige l'accord de `nadia-prof` sur `PAR-2` et l'avis de `theo-archi` sur `ADR-0065` | `nadia-prof`, `theo-archi` |
| Q7 : passe `PAR-4` due a s55 ; verification A10 | **ouverte** | `nadia-prof` |

**Un fait de composition que je ne decide pas** : l'ordre de l'utilisatrice (section 3) place l'arbitrage sur `G7` a `G11` au rang 3, derriere le francais CE1. Il n'en suit pas qu'O1 precede O3 : le planning le lit ainsi,
sous la forme 3, sans le trancher. Si `nadia-prof` ecrit pour l'arbitrage un prix qui ne tient pas dans la reserve de A, c'est son chiffre qui commande.

---

## 3. Ordre des matieres : repris sous un motif de forme 1 par rang (satisfait `DEC-0114` 0.4 (b))

Ordre valide par `ok` (section 0.1, point 2), repris ici **sous les motifs suivants, qui sont de forme 1**. Ce sont les motifs de la section 7.1 de `DEC-0114`, remesures au 2026-10-08.

| Rang | Groupe | Motif de forme 1 | Source, mesure |
|---|---|---|---|
| 1 | `G1`/`G2` francais CE1 | chemin critique : 13 paquets, 13 fichiers de contenu, 0 entree de catalogue ; une instance (`PAR-2`), trois lots au plus par instance (`PAR-8`) : **5 sprints a trois lots, s55 a s59** ; chaque paquet ferme une ligne selon `proposition-s55.md` section 1 (un Done refuse reste un refus) | mesure du 2026-10-08 (section 0.3) ; `proposition-s55.md` section 1 pour le nombre de sprints ; arithmetique relue : 13 lots a 3 par sprint = 5 sprints |
| 2 | `G6`/`G5` sciences et EMC CM1 | `G6` est fait (`SCI-CM1-15` et `-18` fermees a s54, 137/182) ; `G5` : deux paquets branchables (0 entree de catalogue chacun, 11 items), motifs de refus ecrits par paquet (P3, P4 pour `principes`) ; le troisieme (`cp-emc-personnes-differentes`, `EMC-CP-04`) est sous refus (`DEC-0081` section 7) et n'est **pas un lot** | mesure de la section 0.3 ; `proposition-s55.md` section 3, rang 2 |
| 3 | arbitrage de `nadia-prof` sur `G7` a `G11` (16 paquets, 216 items) | **aucun plafond ecrit** pour un paquet `DEGRADE` texte seul : *"un chiffre que je ne donne pas sans avoir lu les 17 en-tetes et les cellules de matrice"* (`nadia-prof`, Q7, arbitrage prealable de A, s54) ; acheter des lots avant de savoir si la ligne ferme reproduirait s51 (`DEC-0114` 7.1) | citation de `nadia-prof` ; 16 paquets : `proposition-s55.md`, non relus ici |
| 4 | `G3`/`G4` (7 paquets : francais CE1 plafonne `en cours` x 2, mathematiques CE1 x 5) | ne ferment aucune ligne ; `MUL-CE1-R1` se rouvre a la `DEC` du premier sprint qui porte une instance de mathematiques CE1 (`DEC-0114` section 3, inchange) | **planning de s54 et `proposition-s55.md`, non remesure par moi** : je le dis, c'est le seul rang dont le motif est cite sans mesure |

**Le point que `DEC-0114` 7.1 laissait a cette decision** : `G3` (francais CE1, deux paquets) est classe au dernier rang **avec `G4`**, comme le planning, parce qu'il ne ferme aucune ligne (motif ci-dessus, rang 4).
Les mots recus disent *"mathematiques CE1"* ; `G3` est du francais CE1 : **le motif est le seul fondement de ce placement, `ok` ne le dit pas.** Si `nadia-prof` lit autrement le plafond de `G3`, le rang est amende.

**Ce que cette section ne fait pas** : elle ne tranche pas O1/O2/O3 (section 2), n'engage rien au-dela de s55, et n'engage pas le decoupage du francais CE1 en deux instances.

---

## 4. Reports comptes

Un poste engage par une `DEC` d'ouverture et non livre a la cloture compte comme un report (`DEC-0114` section 4). Un second report n'est permis qu'avec un acte ecrit (`DEC-0112` section 6).

| Poste | Reports avant | Issue a s55 |
|---|---|---|
| `ART-CP-10` (la ligne) | s50, s51, s52, s53 : quatre ; engagee a s54 (prix, paquet `DEGRADE` branche) | **engagee a s55 : dispositif et reserves**, section 5 ; pas un report |
| `S54-ART-RES-R1` a `R3` | ouvertes a s54, aucun report | engagees ; **sans instance (Q5), premier report**, a ecrire a l'amendement de cette `DEC` |
| `S54-EXC-CATEGORIE` | ouverte a s54, aucun report | engagee |
| `S54-PONCT-GN-INDICES` | ouverte a s54, aucun report | engagee |
| `S54-ASCII-APOSTROPHES-TESTS` | ouverte a s54 : aucun report | **non engagee** : la forme est a `theo-archi` et aucun proprietaire n'est designe ; **premier report (s55)**. Engageable par amendement des que `theo-archi` a ecrit la forme et designe l'agent |
| `S55-OUTIL-TOKENS-SANS-SPRINT` (A8), `S55-OUTIL-JDK-JAVA-TOOL-OPTIONS` (A7) | proposees par le planning, jamais engagees : aucun report | **non engagees** : le prix de `theo-archi` manque ; **premier report (s55)**. `--sprint` explicite tant qu'A8 n'est pas fait |
| `S54-OBS-SCI-CONSIGNE` | ouverte a s54 : aucun report | **non engagee** : criteres non ecrits (*"a ecrire quand une instance l'engage"*) ; **premier report (s55)** |
| `S55-BR-FRA-CE1-L3` (O1) | - | non engagee (section 2) ; ce n'est pas un report, c'est une option ouverte |

**Rien n'est engage sans proprietaire ni prix** : c'est la lecon de `S51-OUTIL-JOURNAL-TOKENS` (engagee sans proprietaire a s53, non livree, trois reports). Les cinq postes non engages ci-dessus n'ont ni forme ni prix.

---

## 5. La mecanique d'`ART-CP-10` ("deux oeuvres ensemble", `ADR-0104`) : financee a s55, dispositif seul

**Fondement.** `DEC-0114` section 6, point 4 : *"la decision de financer la mecanique se prend apres lecture des deux prix, a l'ouverture de s55 (...) elle ne se reporte pas sans compte"*. Les deux prix sont lus :
le prix en tailles de `theo-archi` (`avis-faisabilite.md` de s54, amendement de cloture : production 2 fichiers d'`adapter-web`, 20 a 38 lignes ; tests 3 fichiers neufs, 5 modifies, 15 a 18 tests, 680 a 1 400 lignes ;
zero ligne dans `domain`, `application`, le chargeur, les POM et la CI ; zero regle ArchUnit neuve ; `ADR-0104` `Accepte`) et le plafond de `nadia-prof` (`cp-arts-oeuvres-familles` laisse la ligne a `non couvert`).

**Decision.**

1. **Financement** (forme 3 seule, section 0.5) : la mecanique est financee a s55, selon le message de l'agent (section 0.1, point 3). Je la porte comme story `S55-ART-CP-10-MECANIQUE`, criteres au backlog ("Sprint 55").
2. **Ce qui est finance** : les taches T2 (garde-fous 1 a 3 de la decision 7) et T3 (rendu, revelation, ecran de fin, garde-fou 4, cliquet 3 -> 4) d'`ADR-0104`. **Ce que je ne decide pas** : la conception. La forme du rendu, le nom des variables, la place des gardes sont a `theo-archi` (`ADR-0104`) ; la mise en page et la marque de revelation sont a `ines-design` ; les mots lus par l'enfant sont a `nadia-prof` (`CON-5`).
3. **Ce qui n'est pas finance par cette decision** : le **paquet** a reproductions (T5 : *"n'existe pas"* ; `cp-arts-oeuvres-familles` ne la sert pas, 0 `illustration`) et **l'audit** de ce paquet (2,0, ou 2,5 sous `PAR-11` ; tarif de `nadia-prof`, a elle). Sans paquet au catalogue, **l'enfant ne voit rien changer a la fin de s55**, et `ART-CP-10` reste `non couvert`.
4. **T4** (restauration de trois reproductions, constantes 8 -> 11 et 176 474 -> 250 915) : **question a `theo-archi`, non tranchee par moi**. Constat de lecture : `LIllustrationDeContenuResoutEtAucuneNeDortTest` porte une clause *"aucune ne dort"* (la clause vaut sur `static/oeuvres/`, test lu, non lance) ; trois reproductions restaurees qu'aucun item ne declare la feraient rougir. Si cette lecture est juste, T4 ne peut pas etre verte sans le paquet, et T4 voyage avec lui. L'avis de `theo-archi` dit si T4 est dans cette story ou dans celle du paquet.

### 5.1 Ce que ce financement change a la capacite du planning

La borne de 8,0 de `PAR-8` et le budget de 4,0 par instance de `PAR-9` comptent le travail de `nadia-prof` (arbitrage, lots, rejeux). Le developpement n'y entre pas.

| Poste | Charge | Source |
|---|---|---|
| Developpement T2 et T3 | **0,0 pour `nadia-prof`**. Charge de dev : environ 1,75 j pour T2 a T4 (estimation de `theo-archi`, pas un tarif du depot) | `ADR-0104`, "Cout et decoupage" ; avis s54, T6 |
| Arbitrage de `nadia-prof` sur la forme (nom accessible, phrase de revelation, ecran de fin, choix des paires, `C5`) | **prix inconnu** : `ADR-0104` ecrit qu'il *"n'est dans aucune ligne publiee de son tarif"* | `ADR-0104`, "Cout" |
| Arbitrage de `ines-design` (mise en page, marque de revelation) | hors `PAR` ; **le planning ne lui donne aucune tache** ("ines-design : aucune") : ecart a corriger | planning, "Affectations" ; `ADR-0104` T3 |

**Hypothese de calcul, que je marque comme telle** : si l'arbitrage de forme prend 0,5 (le prix de l'arbitrage prealable de A et de B a s54, qui n'est **pas** son tarif pour cette forme) :

| | Avant | Avec 0,5 | Lecture |
|---|---|---|---|
| A | 3,0 / 4,0, reserve 1,0 | **3,5 / 4,0**, reserve 0,5 | tient ; ferme O1 (+1,0 = 4,5) et O3 (+1,0) tels que le planning les chiffre |
| B | 3,75 / 4,0, reserve 0,25 | **4,25 / 4,0** | **depasse `PAR-9`** : B ne peut pas la porter |
| Somme | 6,75 / 8,0 | **7,25 / 8,0** | tient sous la borne |

**Conclusion sur le seul dispositif : aucun ecart a `PAR-8`, `PAR-9` ni a leurs plafonds n'est exige tant que le prix de l'arbitrage de forme est au plus 1,0 sur A**, et A porterait alors la forme (ce qui pose `PAR-13` : une forme jamais jugee et les sept conditions de francais CE1 non lues sur la meme instance ; **c'est a `nadia-prof`**). Au-dela de 1,0 sur A, ou sur B a quelque prix que ce soit au-dessus de 0,25, un ecart serait exige : **je l'ecris comme question a l'utilisatrice (Q-U2), pas comme fait.**

### 5.2 L'audit du paquet, s'il etait fait a s55 : ecart exige

Avec l'audit au prix de `theo-archi` (2,0) : **6,75 + 2,0 = 8,75 sur 8,0**, soit +0,75 ; a 2,5 (`PAR-11`) : 9,25, soit +1,25. Par instance : A 3,0 + 2,0 = **5,0**, au plafond dur de `PAR-8`, au-dessus de 4,0 (`PAR-9`) ; B 3,75 + 2,0 = 5,75, **au-dessus du plafond dur de 5,0**.
**Aucune des deux instances ne peut le porter sans ecart a `PAR-9`**, et B pas du tout ; une troisieme demande son propre ecart (`DEC-0114` A.20). **C'est donc une question a l'utilisatrice, pas un fait** (Q-U1, Q-U2).

**Correction a signaler a `sofia-sm`** : le planning (section "Stories proposees", apres le tableau) ecrit qu'un budget d'audit *"tient sous 8,0 uniquement si O1 et O3 ne sont pas pris"*. Avec les chiffres de `theo-archi` (2,0) c'est faux meme sans O1 ni O3 : 6,75 + 2,0 = 8,75. Le planning est a elle ; je ne le modifie pas.

### 5.3 Ce que ce financement ne fait pas

Il ne ferme pas la ligne, ne branche aucun paquet, n'ouvre aucune instance, n'ecrit aucun tarif ni plafond, ne prononce aucun Done, ne touche ni `catalogue.yaml`, ni un paquet, ni la matrice (`git diff` de ce commit : la matrice n'y est pas), ni le planning.

### 5.4 Ce qui reste a faire pour que le planning de `sofia-sm` la porte

Amendement du planning par `sofia-sm`, avant l'ouverture du worktree concerne (action A5) :

1. **Proprietaire du developpement** : non designe. Un dev tient un seul worktree ; `marc-dev` et `lea-dev` portent trois ou quatre postes chacun ; `sofia-sm` (casquette developpeuse) n'a aucun poste engage depuis que A7 et A8 ne le sont pas (section 4). **C'est a elle de designer** ; branche, worktree et scratchpad avec.
2. **`ines-design`** : ajouter la tache (mise en page de trois boutons-images et d'une reference ; marque visuelle de la revelation), **avant T3**.
3. **`nadia-prof`** : ajouter a son arbitrage prealable de s55 les points 2 a 5 de `ADR-0104` ("Ce que je route") **et leur prix**, et dire sur quelle instance (`PAR-2`, `PAR-13`).
4. **`theo-archi`** : T6 du planning reconfirme que son prix vaut pour l'arbre de s55 ; **ajouter la question T4 ci-dessus** (le corpus est-il vert sans paquet ?) ; la forme de l'avis fixe les commandes des criteres 1 et 7 de la story.
5. **Ordre de fusion** : T3 attend les mots de `nadia-prof` et la mise en page d'`ines-design` (dependance d'`ADR-0104`, T3) ; ecrire ce que fait le dev tant qu'ils manquent, ou ne pas ouvrir le worktree.
6. **Chiffres** : corriger la phrase citee en 5.2 ; porter 7,25 (hypothese 5.1) ou le prix de `nadia-prof` au tableau de capacite.
7. **Objectif de sprint** : il ne dit rien de la mecanique ; l'amendement du planning l'ajoute, avec la mention que l'enfant ne voit rien tant qu'aucun paquet n'est au catalogue.

### 5.5 Questions a l'utilisatrice

| Code | Question |
|---|---|
| Q-U1 | Le **paquet** a reproductions (T5) s'ecrit-il a s55, a s56 ou plus tard ? Son ecriture est une charge de developpement, pas un budget `PAR` ; son arbitrage et son audit sont a `nadia-prof`. Un dispositif sans paquet ne sert aucun enfant. |
| Q-U2 | L'**audit** du paquet (2,0 propose par `theo-archi`, tarif de `nadia-prof` a confirmer) s'il avait lieu a s55 depasserait la borne de 8,0 de +0,75 (ou +1,25 sous `PAR-11`) et exigerait un ecart ecrit (5.2). **Accorde-t-elle un ecart pour s55, ou l'audit va-t-il a s56 ?** Je ne l'ecris pas comme accorde. Si la reponse est s56, l'audit est une charge de s56 dans la borne de 8,0 de ce sprint-la. |
| Q-U3 | Confirmer, **par ses mots**, que le financement du dispositif seul est bien ce qu'elle a decide (la formule du message est celle de l'agent, section 0.1, point 3). Ses mots en forme 2 passent cette `DEC` a `Acceptee`. |

---

## 6. `DEC-0114` : statut

Voir l'amendement A.27 de `DEC-0114` (ce commit) : **`Acceptee`**, par la lecture (b) de la section 0.4, conditionnee a la section 3 de cette decision. Ce que cette lecture ne fait pas : elle ne remplace pas les mots de l'utilisatrice,
et elle ne transforme pas `ok` en motif (section 0.4).

---

## 7. Routage (changement de fichiers et de code)

| A qui | Quoi |
|---|---|
| `sofia-sm` | Fusionner cette decision et l'amendement A.27 avant tout worktree (C1) ; amender le planning selon 5.4 (proprietaire, `ines-design`, capacite, correction de la phrase 5.2, objectif) ; branche, worktree et scratchpad de toute nouvelle instance ou de tout nouveau dev avant son premier edit (A5) ; **aucune troisieme instance** |
| `nadia-prof` | Arbitrage prealable de s55 : tarifs de chaque story, Q3 (`PAR-13`, choix des paquets, paire `-12`/`-13`), Q4, Q5, Q7 ; les points 2 a 5 de `ADR-0104` et leur prix sur la forme ; lecture du plafond de `G3` (section 3) ; je n'ecris aucun de ces points |
| `theo-archi` | Avis de faisabilite de s55 (T1 a T7 du planning) ; T6 reconfirme et la question T4 de 5 ; forme de `S54-ASCII-APOSTROPHES-TESTS` (premier report) ; prix d'A7, A8 |
| `ines-design` | Mise en page et marque de revelation, avant T3 (5.4, point 2) ; propose, ne tranche pas |
| `marc-dev`, `lea-dev` | Aucun edit avant la fusion de cette decision sur `round-two` (C1) ; `S54-EXC-CATEGORIE` et `S54-ART-RES-R1` a `R3` d'abord ; rapports `rapport-marc-dev.md` et `rapport-lea-dev.md` (C7) |
| `yanis-qa` | Lecture de testabilite des criteres du backlog "Sprint 55", dont ceux de la mecanique, avant la premiere livraison |
| `camille-po` | Backlog, `DEC-index.md` (ce commit) ; fiches de conformite des quatre jeux a l'ouverture de leur branchement ; matrice au verdict seulement |
| Utilisatrice | Q-U1 a Q-U3 (section 5.5) |

---

## 8. Ce que cette decision ne fait pas

- Elle ne prononce aucun Done pedagogique, ne fixe aucun tarif ni aucun plafond, ne tranche ni O1/O2/O3, ni la lecture de `PAR-13`, ni la conception de la mecanique.
- Elle ne touche ni `catalogue.yaml`, ni un paquet, ni du code, ni la matrice, ni le planning de `sofia-sm`.
- Elle n'ouvre aucun worktree d'agent, ne fusionne rien, ne pousse rien.
- Elle ne dit pas que la CI est verte : *"CI verte sur fbe07d79"* est un message recu (0.1), non verifie.
- **Jobs de `ci.yml` lances pour ce commit** : `chemins` (`.github/scripts/verifier-chemins.sh` sur les fichiers suivis) et `prefixes` (`.github/scripts/verifier-prefixes.sh` sur le sujet du commit), resultats dans le message de livraison. **`outils` et `build` ne sont pas lances** : ce commit ne modifie ni code, ni contenu, ni outil ; je ne deduis pas leur resultat.

## Decisions prises

1. Quatre branchements (`S55-BR-FRA-CE1-L1`, `-L2`, `S55-BR-EMC-CM1-03`, `-06`), `S54-EXC-CATEGORIE`, `S54-ART-RES-R1` a `R3` (sous Q5) et `S54-PONCT-GN-INDICES` engages a s55 ; tarifs proposes, 6,75 sur 8,0 (`PAR-9`, deux instances, aucun ecart).
2. O1, O2, O3 et Q3 a Q7 du planning **non tranchees** : routees a `nadia-prof` ; aucune `L3` engagee.
3. Ordre des matieres repris sous un motif de forme 1 par rang (section 3) ; `G3` au dernier rang avec `G4` ; `DEC-0114` passe a `Acceptee` (A.27).
4. Mecanique d'`ART-CP-10` : dispositif (T2, T3) finance a s55, story `S55-ART-CP-10-MECANIQUE` ; paquet et audit non finances, deux questions a l'utilisatrice ; T4 sous avis de `theo-archi`.
5. Cinq postes non engages (`ASCII-APOSTROPHES`, A7, A8, `OBS-SCI-CONSIGNE`, et `ART-RES` sans instance) : premier report compte, motif ecrit.
6. Statut de cette decision : `Proposee` jusqu'aux mots de l'utilisatrice sur le financement (section 0.5) ; **`Acceptee` le 2026-10-09 (A.22)**.

---

## Amendement du 2026-10-08 (s55, `camille-po`) : arts plastiques CP entier sur l'instance A, reserve de A, forme et audit d'`ART-CP-10`, criteres corriges

Annotation datee. **Rien au-dessus n'est reecrit** : les lignes que cet amendement remplace sont nommees en A.7. Sources lues avant d'ecrire :
`ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md` (A, 15:44) et `ARBITRAGE-PREALABLE-s55-instance-b-emc-cm1.md` (B, 15:35) de `nadia-prof` ;
`squad/sprints/s55-francais-ce1-emc-cm1-et-reserves-servies/avis-faisabilite.md` de `theo-archi` (15:42) ; `planning.md`.

### A.1 Ce que j'ai recu, cite tel que recu (forme 3, `DEC-0062` regle 3.5)

Message de l'agent qui m'a lance, 2026-10-08, relayant l'utilisatrice, **sans source dans le depot** (je n'ai pas le message d'origine) :
"L'utilisatrice a tranche Q4 (message relaye, forme 3) : option (b) : TOUTES les arts plastiques CP vont a l'instance A de nadia-prof (PAR-2)."
Le texte est celui de l'agent. **Ce "Q4" n'est pas le Q4 du planning** (prix et place de l'arbitrage sur `G7` a `G11`, que `nadia-prof` a traite en section 6 de A) : il designe
le choix entre les issues (a) et (b) de A 2.3 et de B 4.1.

**Borne de la forme 3 : ce message fonde le choix de (b) contre (a), jamais sa raison.** La raison est de forme 1, et elle est celle des deux instances : `PAR-2` interdit de partager
une matiere et un niveau dans un sprint ; le critere de `ART-RES` (ni titre ni consigne du jeu des familles ne contiennent `reconna` ou `rapproch`) est la frontiere entre ce paquet et
`ART-CP-10` ; "tenue par deux instances, elle est tenue par moitie" (A 2.3, qui confirme la lecture de B 4.1). (b) met les deux cotes de la frontiere chez la meme instance.
B 4.1 posait comme condition que (b) soit un amendement de cette `DEC` accepte par l'instance A : A l'a chiffre dans son propre arbitrage (2.3, ligne (b), 3,75) ; cet amendement est le mien.

### A.2 Decision 1 : affectation et arithmetique

- `S54-ART-RES-R1` a `R3` : **instance A**, **0,25** (textes de B 4.2, que A rejoue mot pour mot ; 0,5 si reecrits). Developpement : `lea-dev`, inchange, en tete de sa file.
- La forme d'`ART-CP-10` (points 2 a 5 d'`ADR-0104`) : **instance A**, 0,25, deja comptee dans les 3,5 de A.
- `S54-EXC-CATEGORIE` (`EXC-R15`) : **reste a l'instance B**, 0,25 (B 5). `PAR-2` : l'EMC CP et l'EMC CM1 sont une seule matiere et B porte l'EMC CM1 ; le fait que l'instance A de s54 ait ecrit la reserve n'y change rien (A 2.1 : 0,0 sur A).

Arithmetique, avec les tarifs que `nadia-prof` a ecrits (A 2.1 et 2.3, B 6). Je ne recompte aucun tarif, je les additionne.

| Poste | A | B |
|---|---|---|
| Deux lots (A : `-14`, `-12` ; B : `EMC-CM1-03` 1,0 et `EMC-CM1-06` 1,5) | 2,0 | 2,5 |
| Arbitrage prealable | 0,5 | 0,5 |
| Plafonds de `G7` a `G11` | 0,25 | - |
| Forme d'`ART-CP-10` | 0,25 | - |
| `S54-ART-RES-R1` a `R3` | **0,25** (etait chez B) | **0** (etait 0,25) |
| `S54-EXC-CATEGORIE` | - | 0,25 |
| Marge de rejeu, allouee a aucun lot | 0,5 | 0,5 |
| **Total / 4,0** | **3,75** (3,5 avant) | **3,75** (4,0 avant) |
| Reserve non allouee | **0,25** | **0,25** |

A : 2,0 + 0,5 + 0,25 + 0,25 + 0,25 + 0,5 = 3,75. B : 2,5 + 0,5 + 0,25 + 0,5 = 3,75. **Somme 7,5 sur 8,0** (`PAR-8`), inchangee par le deplacement (0,25 passe de B a A).

- **`PAR-9` (4,0 par instance, marge comprise) : respectee** par les deux, 3,75 chacune. **`PAR-8`** : deux lots par instance (au plus trois), plafond dur de 5,0 non approche.
- **`PAR-11` : non appliquee, comme a s52** (`DEC-0111`), et toujours non tranchee. **Je donne les deux lectures** : dans sa lecture amendee (0,5 de marge par lot neuf, par instance),
  A et B portent chacune deux lots neufs, soit 1,0 de marge demandee pour 0,5 allouee : **4,25 chacune, 8,5 en somme, au-dessus de `PAR-9` et de la borne de 8,0**. Le risque est celui que B 6 ecrit pour B (un seul lot rejouable) ; il vaut aussi pour A.
- **Ecart a la `DEC` d'origine, a ne pas confondre avec le deplacement** : sa section 1 comptait 6,75 sur 8,0 avec les tarifs du planning. Les tarifs de `nadia-prof` donnent **7,5** : A +0,5 (plafonds 0,25, forme 0,25), B +0,25
  (`EMC-CM1-06` a 1,5 au lieu de 1,0, `ART-RES` a 0,25 au lieu de 0,5, avant deplacement). Aucun ecart a `PAR-9` ni a `PAR-8` n'est exige ; **la somme de 6,75 est caduque**.
- Si `EXC-R15` est reecrite au lieu d'etre appliquee mot pour mot (A.6) : B 3,75 - 0,25 + 0,5 = **4,0**, encore dans `PAR-9`, reserve 0.

**Reports** : `S54-ART-RES-R1` a `R3` ont une instance, donc **le "premier report sans instance (Q5)" de la section 4 ne se produit pas.** Engagees a s55 et non livrees a la cloture, elles compteraient pour un premier report (`DEC-0114` section 4).

### A.3 La reserve de A : O1 ne tient pas, O3 est fait, O2 reste a l'utilisatrice

La reserve de A etait de 1,0 au planning, de 0,5 avec les prix de `nadia-prof`, elle est de **0,25** apres A.2.

| Emploi | Etat | Preuve |
|---|---|---|
| **O1** : un troisieme lot (`-13`, 1,0) | **ne tient pas sous `PAR-9`** : 3,75 + 1,0 = **4,75 sur 4,0** (A 2.2 : 4,5 avant le deplacement) | arithmetique ci-dessus. Il exigerait un ecart ecrit de l'utilisatrice ; **personne ne le demande**, `S55-BR-FRA-CE1-L3` n'est pas engagee |
| **O3** : arbitrage sur `G7` a `G11` | **fait par `nadia-prof`, 0,25** (A section 6), et non 1,0 | 15 paquets sur 16 plafonnes `non couvert` ; seul `cm1-lire-et-restituer` (`FRA-CM1-01`) peut fermer, apres une mesure de pli d'`ines-design` qui n'est pas faite, puis un lot a 1,0. Le rang 3 de l'ordre (section 3) **achete au plus une ligne** ; l'ordre est celui de l'utilisatrice, je n'y touche pas |
| **O2** : ne rien ajouter | **ouvert : decision de l'utilisatrice** | voir ci-dessous |

**Ce qui reste a decider, sans que je le tranche (Q-U4)** : l'emploi des **0,25** restants de A. Deux usages nommes par `nadia-prof`, qui ne tiennent pas ensemble (0,25 + 0,25 = 0,5 > 0,25) :

1. **Le choix des oeuvres distractrices** du paquet illustre (A 3.5, regles 2 et 3), 0,25 : il precede l'ecriture du paquet. Il n'a de valeur a s55 que si le paquet s'ecrit a s55 (Q-U1, toujours ouverte).
2. **Un rejeu mot pour mot** (A 2.2), 0,25, en plus de la marge de 0,5 : la marge paie un rejeu ; un second refus a s55 (`-14`, `-12` ou une reserve d'`ART-RES`) n'est finance que par cette ligne.

Laisser les 0,25 non alloues est une troisieme issue : A ferme a 3,75, sans rien de plus. **Le choix est celui de l'utilisatrice ; cet amendement ne l'ecrit pas.** Tant qu'il n'est pas fait, les 0,25 restent non alloues.

### A.4 La forme d'`ART-CP-10` et l'audit du paquet illustre

- **La forme est a l'instance A.** Sa section 3 est ecrite et, (b) etant retenue, c'est elle qui vaut : ce que le dispositif montre (`M1` a `M6`), ce qu'il ne montre pas (`X1` a `X7`, dont l'ecran de fin sans oeuvre),
  le nom accessible et la phrase de revelation (3.2), les regles de paires (3.4). **Je ne recopie aucun mot lu par l'enfant** : ils sont a elle (`CON-5`) et vivent dans son fichier.
- **Ce que sa forme laisse expressement "au paquet, en jouant"** (3.1, 3.4, point 6) : ce que la forme vaut sur des paires reelles, la visibilite de chaque propriete a 176 px CSS, la regle "le sujet ne suffit jamais", un recadrage de la reference (`X5`). **Rien de cela ne se tranche a s55.**
- **L'audit du paquet illustre : 2,5 (tarif de `nadia-prof`, A 3.5 : lot d'arts plastiques CP dont elle n'est pas la source 1,5, illustre +0,5, forme sans contenu +0,5), a s56 au plus tot.** Le 2,0 de ma section 5.2 etait celui de `theo-archi` ; le tarif est celui de `nadia-prof` et il l'emporte.
  A s55 il ne tiendrait sur aucune instance : A 3,75 + 2,5 = 6,25 et B 3,75 + 2,5 = 6,25, tous deux au-dessus du plafond dur de 5,0 ; somme 10,0 sur 8,0. **Q-U2 (ecart a s55 ou audit a s56) n'a plus a etre posee : aucun ecart n'est demande, l'audit va a s56 au plus tot.**
- **Ce que l'audit impose a s56** (A 3.5), que la `DEC` d'ouverture de s56 comptera : l'instance qui l'audite garde 4,0 - 0,5 (arbitrage) - 0,5 (marge) - 2,5 = **0,5**, donc **aucun autre lot** ; la forme n'a aucune cousine, c'est le terrain neuf de s56 au sens de `PAR-13` ;
  un lot sans cousine (`-08`, `-09`) n'est jamais dans le sprint ou une autre instance audite ce paquet. **Consequence de produit, nommee sans etre tranchee** : `PAR-2` met tout le francais CE1 (rang 1 de l'ordre, section 3) chez A ; si A audite le paquet a s56, le francais CE1 n'avance pas a s56.
- **Ce que s55 construit de la mecanique** (avis de `theo-archi`, sections 3 et 4) : **T2** (garde-fous 1 a 3) ne depend d'aucun mot et se livre seul ; **T3** (rendu, revelation, ecran de fin, garde-fou 4, cliquet 3 vers 4) attend les mots (ecrits, A 3.2)
  et la mise en page d'`ines-design` (**non ecrite, toujours sans tache au planning**) ; **T4 sort de la story** et voyage avec le fichier du paquet a reproductions (T5) : sans item qui declare les trois reproductions, `LIllustrationDeContenuResoutEtAucuneNeDortTest` est rouge (simulation B de l'avis, 2 tests sur 18).
  Le paquet (T5) n'est ni finance ni date : **Q-U1 reste ouverte** (s55 ou s56 ; l'audit de s56 en depend).

### A.5 Criteres de `S55-ART-CP-10-MECANIQUE` corriges (backlog, "Sprint 55")

Corrections de `theo-archi` (QC1 a QC3 et critere 9). Verifie par moi contre l'arbre : le chemin du critere 8 (le contre-exemple vit bien sous `bootstrap/src/test/resources/contreexemple/`, des repertoires `contenu` y existent) et le critere 9 (ci-dessous). Les autres sont ses mesures, que je ne reconteste pas et dont je n'ai pas relance les commandes.
Le backlog les porte sous chaque critere concerne.

1. **Critere 6.** Les mots et la mise en page existent avant le rendu, **pour les points 3 et 4** d'`ADR-0104` (nom accessible, phrase de revelation, ecran de fin) : les mots sont ecrits (A 3.2, 3.3), la mise en page manque. **Les points 2 et 5 se tranchent "sur un paquet, en jouant"** (`nadia-prof`, 2026-10-06,
   `ARBITRAGE-PREALABLE-s52-fra-ce1-questions-de-l-avis.md`, fin de la section 4.2) : le critere ne les demande plus, et **la story ne les ferme pas**. T3 ne peut donc pas etre close a s55 au sens de la forme : la valeur de la forme est jugee a l'audit de s56 au plus tot.
   Reponse a **QC1** : la story se decoupe en T2 (livrable seul) et T3 (code possible a s55 si `ines-design` livre avant l'ouverture du worktree, jugement a s56) ; T3 non livree a la cloture de s55 est un **premier report**, compte.
2. **Critere 8.** Le chemin vise est `adapters/adapter-content/src/main/resources/contenu/`, et non tout `contenu/` : un `grep contenu/` compte le contre-exemple du garde-fou 1 (QC2).
3. **Critere 9.** F0 de l'avis (ligne `U4` de `review.md` de s54, 4 cellules pour 3 colonnes) est corrige par `8f740977`. Mesure du 2026-10-08 sur `ca425c17` : la ligne 293 a 3 cellules ;
   `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest -Dsurefire.failIfNoSpecifiedTests=false` (perimetre : cette classe, `-am`) : 5 tests, 0 echec.
   Le critere 9 redevient tenable ; **je n'ai pas lance `mvn -B clean verify` sur la racine**.
4. **Garde-fou 2 (critere 4, point 2).** La population reelle de reproductions vaut **exactement 0** a s55, forme d'`ADR-0051` deja pratiquee par `LIllustrationDeContenuResoutEtAucuneNeDortTest` (QC3) ;
   le commit du premier paquet a reproductions releve ce nombre. "Population non vide" serait rouge.

### A.6 Critere de `S54-EXC-CATEGORIE` : non tenable tel qu'ecrit

Mesure de `theo-archi` (avis, section 7, point 1, simulation C), que je ne conteste pas : **les quatre lignes de `nadia-prof` ne tiennent pas avec "`PaquetCpEmcExclusionTest` reste vert".**
Avec elles seules, la categorie "une moquerie qui se répète" tombe a 1 bonne reponse, sous le minimum de 3 de `FAB-1a` ; `CLASSEMENTS_VALIDES` ne connait pas la categorie neuve et les items 1 et 3 basculent en silence dans la tache B (**faux vert**) ; l'inventaire porte l'ancienne consigne.

**Je ne reecris pas le critere** : il reprend les mots de `nadia-prof` (regle de la story). **QC4 est routee a `nadia-prof`** : le seuil de `FAB-1a` se lit-il sur deux categories (moquerie ou mise a l'ecart, contre accident), auquel cas il tient a 3, ou sur trois, auquel cas la moquerie n'a qu'un item ?
Le backlog marque le critere **"non tenable tel qu'ecrit, a reecrire apres QC4"**. La story reste engagee ; sa livraison attend la reponse.

### A.7 Ce que cet amendement remplace dans la `DEC`

| Ligne | Remplacee par |
|---|---|
| Section 1, tableau : `ART-RES` "sous Q5 : B, 0,5" | A.2 : instance A, 0,25 |
| Section 1, tableau : `EMC-CM1-06` 1,0 | 1,5 (B 6) |
| Section 1, "Capacite" (3,0 / 3,75, 6,75 sur 8,0) | A.2 : 3,75 / 3,75, 7,5 sur 8,0 |
| Section 2, Q3, Q4, Q5 "ouvertes" | Q3 : O1 ne tient pas, O3 fait, O2 en A.3 ; Q4 (`G7` a `G11`) : fait, 0,25 ; Q5 : instance A |
| Section 4, `ART-RES` "sans instance, premier report" | A.2 : une instance, pas de report |
| Sections 5.1 (hypothese 0,5, B a 4,25) et 5.2 (2,0) | A.4 : forme 0,25 sur A, audit 2,5 a s56 au plus tot |
| Section 5.5, Q-U2 | A.4 : caduque ; Q-U1 ouverte ; Q-U3 inchangee |

**Nouvelle question a l'utilisatrice** : Q-U4 (emploi des 0,25 de A, A.3).

### A.8 Statut, routage, ce que cet amendement ne fait pas

- **Statut inchange : `Proposee`** (section 0.5). Le message de A.1 est de forme 3 et ne remplace pas les mots de l'utilisatrice.
- **Routage** : `sofia-sm` (planning : `ART-RES` et forme chez A, capacite 3,75 / 3,75, proprietaire du dev de la mecanique, tache d'`ines-design` avant T3, composition de s56 de A.4 ; aucune troisieme instance) ;
  `nadia-prof` (QC4 sur `FAB-1a`, A.6 ; confirmer par une ligne que sa section 3 vaut sous (b)) ; `theo-archi` (rien de neuf : QC1 a QC3 sont repris en A.5, QC4 est a `nadia-prof`) ; `ines-design` (mise en page, avant T3) ; utilisatrice (Q-U1, Q-U3, Q-U4).
- **Ne fait pas** : ne touche ni `catalogue.yaml`, ni un paquet, ni du code, ni la matrice (137 / 182, aucun statut ne bouge), ni le planning de `sofia-sm` ; ne prononce aucun Done ; ne fixe aucun tarif (ils sont ceux de `nadia-prof`) ; n'engage ni `S54-ASCII-APOSTROPHES-TESTS`
  (forme et agent ecrits par `theo-archi`, T4 de l'avis : engagement non decide ici), ni `S55-BR-FRA-CE1-L3`.
- **Jobs de `ci.yml` lances pour ce commit** : voir le message de livraison. `build` et `outils` ne sont pas lances (aucun code, contenu ni outil modifie), hors la classe nommee en A.5.

---

## Amendement du 2026-10-08 (s55, `camille-po`, second) : engagements de `S54-ASCII-APOSTROPHES-TESTS`, A7, A8, report de `S54-OBS-SCI-CONSIGNE`, fiches des quatre branchements

Annotation datee. **Rien au-dessus n'est reecrit.** Cet amendement remplace trois lignes de la section 4 (A.9) et ferme l'attente que `sofia-sm` a ecrite a son amendement de planning
(*"l'engagement revient a camille-po, `DEC-0115` section 4 ; sans amendement de sa part, ils comptent comme premier report"*). Sources lues : `DEC-0115` section 4 et A.8 ;
`avis-faisabilite.md` de `theo-archi`, T4 et T7 ; `planning.md` de s55 (amendement de `sofia-sm`, sections D et E) ; backlog (`S54-OBS-SCI-CONSIGNE`) ; `DEC-0112` section 6 ; `DEC-0111` A.13.

### A.9 Engages : `S54-ASCII-APOSTROPHES-TESTS`, A7, A8

La condition que la section 4 posait etait *"engageable par amendement des que `theo-archi` a ecrit la forme et designe l'agent"* (ASCII) et *"le prix de `theo-archi` manque"* (A7, A8).
`theo-archi` a ecrit les deux : T4 (forme, agent) et T7 (formes, prix) de l'avis. Les trois sont **engages a s55**. Le "premier report (s55)" de la section 4 pour ces trois lignes est **remplace** :
engages et non livres a la cloture, ils compteraient comme un premier report (`DEC-0114` section 4) ; livres, aucun.

| Poste | Proprietaire | Taille (avis de `theo-archi`) | Tarif `PAR` | Rang | Critere, commande et valeurs |
|---|---|---|---|---|---|
| `S54-ASCII-APOSTROPHES-TESTS` | `marc-dev`, un commit a part, apres `L1`, `EXC-R15`, `L2` (planning, section E) | 2 fichiers, 1 ligne chacun (T4) | 0,0 | 5 | `grep -cP '[\x{2018}\x{2019}]'` sur `PaquetCm1EmcAtteinteAutruiTest.java` et `PaquetCm1SciencesActiviteHumaineTest.java` : **1 et 1 aujourd'hui, 0 et 0 attendus** ; les deux tests restent verts ; forme : echappement `\u2019` (T4) |
| `S55-OUTIL-JDK-JAVA-TOOL-OPTIONS` (A7) | `sofia-sm`, casquette developpeuse, tache separee qui annonce la casquette | 1 ligne de production, 15 a 25 lignes de test (T7) | 0,0 | 6 | `JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8 bash outils/mvn-jdk21.sh -v` : **sort 1 aujourd'hui (relance par moi), doit sortir 0** ; un cas neuf dans `outils/mvn-jdk21-test.sh` ; `bash outils/mvn-jdk21-test.sh` sort 0 |
| `S55-OUTIL-TOKENS-SANS-SPRINT` (A8) | `sofia-sm`, meme casquette | 5 a 8 lignes de production, 15 a 30 lignes de test (T7) | 0,0 | 6 | `python outils/rapport-tokens.py` sans `--sprint` : **modifie des fichiers suivis aujourd'hui (mesure de `theo-archi`, non relancee par moi : la commande ecrit), doit sortir 2 et laisser `git status --short` vide** ; `test_6` de `outils/rapport-tokens-journal-test.py` reecrit en "refuse et n'ecrit rien" |

- Les criteres "ASCII" et "A7" sont **rouges sur l'arbre actuel** : je les ai mesures sur `2d0d10e7` avant de les ecrire (regle du backlog sur les criteres correctifs). Celui d'A8 repose sur la mesure de `theo-archi`.
- Un tarif de 0,0 ne veut pas dire sans proprietaire : chacun en a un, nomme ci-dessus. C'est la lecon de `S51-OUTIL-JOURNAL-TOKENS` (section 4).
- Aucune `ArchRule` neuve (T7) ; le job `outils` de la CI lance deja `outils/*-test.sh` (T7). Ces trois postes ne touchent ni `PAR-8` ni `PAR-9`.
- **Routage** : `sofia-sm` ouvre `s55-sofia-outils` (planning, section E) pour A7 et A8, et ajoute `ASCII` a la file de `marc-dev`. Le backlog porte l'etat (annotation datee a "Sprint 55").

### A.10 `S54-OBS-SCI-CONSIGNE` : non engagee, premier report (s55)

Non engagee. Elle n'a ni criteres ni prix : le backlog dit *"a ecrire quand une instance l'engage, avec les mots de `nadia-prof`"*, et les criteres reprennent les mots de la passe transverse, que je n'ecris pas moi-meme.
Sciences CM1 est a l'instance B (`PAR-2`), qui n'a aucun lot de sciences a s55 et est a 3,75 sur 4,0 : un audit de plus ne lui est pas demande. Rien n'est faux pour l'enfant : la consigne est dite deux fois, elle n'est pas fausse.

**Report compte : premier report (s55).** Elle avait ete ouverte a s54 sans report.

**Regle appliquee** (verifiee dans les textes, et non recopiee de memoire) : `DEC-0111` A.13 est l'endroit ou le premier report de `SYC-CE1-R1` et `MUL-CE1-R1` est compte ; la regle elle-meme est a `DEC-0112` section 6, citee par
`DEC-0114` section 4 : *"Un troisieme report n'est plus permis"*, et un second report n'est permis qu'avec un acte ecrit. Consequences pour ce poste :
1. **s56** : second report possible seulement avec un acte ecrit dans la `DEC` d'ouverture de s56, qui cite ce premier report (s55) et son motif.
2. **s57** : un troisieme report est interdit. La `DEC` de s57 l'engage (criteres de `nadia-prof`, proprietaire, prix) ou le retire, par une issue ecrite que `DEC-0112` section 6 nomme.
3. Pour sortir de l'attente avant : une instance de sciences CM1 qui l'engage et ecrit ses criteres avec ses mots.

### A.11 Fiches de conformite des quatre branchements : non ouvertes, identifiant non fixe

Les quatre paquets (`ce1-accord-sujet-verbe`, `ce1-classes-de-mots`, `cm1-emc-respect-differences`, `cm1-emc-principes-republique`) **n'ont pas d'entree de catalogue** : `grep -c "paquet: ce1-accord-sujet-verbe$"` et les trois autres rendent 0 sur `catalogue.yaml`
(l'arbitrage de l'instance A le dit pour les deux premiers). Les arbitrages de `nadia-prof` (A, B) et l'avis de `theo-archi` ne nomment aucun identifiant de jeu pour eux (ils nomment les ids de `bandit-manchot-homophones-ce1`,
`-passe-compose-ce1`, `-atteinte-autrui-cm1`, qui sont d'autres paquets). La convention est `FICHE-<id du jeu au catalogue>` : **l'id est choisi par le developpeur au branchement** (`marc-dev` pour les deux premiers, `lea-dev` pour les deux autres) ;
je ne l'invente pas. **Les fiches s'ouvrent apres le commit de branchement de chacun**, avec les valeurs avant/apres du planning (section D) et les conditions `K1` a `K5` de A et les sections 2 et 3 de B comme criteres.
Aucune fiche n'est ecrite par cet amendement. Le critere d'`EXC-R15` n'est pas ecrit non plus : il attend la reponse de `nadia-prof` a QC4.

### A.12 Ce que cet amendement ne fait pas

Statut de la `DEC` inchange (`Proposee`). Ne touche ni catalogue, ni code, ni paquet, ni matrice (aucun statut ne bouge). Ne prononce aucun Done. Ne fixe aucun tarif.

---

## Amendement du 2026-10-08 (s55, `camille-po`, troisieme) : criteres corriges apres la lecture de testabilite de `yanis-qa`

Annotation datee. **Rien au-dessus n'est reecrit.** Sources lues : `lecture-testabilite.md` (`yanis-qa`, base `2d0d10e7`), `ARBITRAGE-QC4-s55.md` (`nadia-prof`), `avis-faisabilite.md` section 7 (`theo-archi`). Chaque defaut a ete remesure par moi sur `fa4ddc7e` ; mesures, refus et commandes : `rapport-camille-criteres.md` du dossier de sprint. Les textes de criteres sont dans le backlog, section "Sprint 55" et sous les entrees de "Sprint 54" concernees.

### A.13 Ce que cet amendement remplace ou retire

| Ligne de la `DEC` | Devient |
|---|---|
| A.6, "critere non tenable tel qu'ecrit, a reecrire apres QC4" | **Reecrit** (backlog, `S54-EXC-CATEGORIE`). `nadia-prof` a repondu a QC4 : seuil de `FAB-1a` sur deux categories, quatre lignes inchangees, prix 0,25. La clause "`PaquetCpEmcExclusionTest` reste vert" est remplacee par le tableau de commandes de sa section 6 |
| A.6, "`CLASSEMENTS_VALIDES` ne connait pas la categorie neuve et les items 1 et 3 basculent en silence dans la tache B (faux vert)" | **Retire, inexact.** `PaquetCpEmcExclusionTest` identifie la tache de classement par son enonce (`estDeTacheClassement`), pas par ses bonnes reponses ; `nadia-prof` l'a corrige (QC4, section 1). Ce que A.6 reprenait de l'avis de `theo-archi` 7.1 etait sa phrase. Le garde-fou aveugle est celui de la tache B (mutation M2) |
| A.5, point 1 (critere 6 de la mecanique) | Les commandes `ls squad/conformite \| grep -ci s55` et `ls squad/design \| grep -c s55` sont remplacees par des commandes qui nomment les mots et le fichier ; `QC4` et `G-NADIA-A` sont fermees ; l'ordre se juge par `git merge-base --is-ancestor`. Ma phrase "0 aujourd'hui" n'etait vraie qu'a `98e4bbcd` |
| A.9, ligne `S54-ASCII-APOSTROPHES-TESTS` | Ajout d'un cas fabrique (`t` suivi de U+2019 puis `as` rejete, sans U+2019 litteral dans le test) : "les deux tests restent verts" est vrai que la reconnaissance soit gardee ou perdue |
| A.9, ligne A8 | "sort 2" est rendu avant tout controle du dossier des transcriptions, trois cas (dossier vide, inexistant, reel) |
| A.11, "le critere d'`EXC-R15` n'est pas ecrit non plus : il attend la reponse de `nadia-prof` a QC4" | Ecrit, voir la premiere ligne |

**Questions de `yanis-qa` non tranchees ici, routees** : Q-Y5(b) ("jamais la reponse", item 6, `va`) a `nadia-prof` par `sofia-sm` (ecart `E1` du rapport de `marc-dev`) ; Q-Y14 (`alt` d'une image de proposition) a `nadia-prof` par `sofia-sm`, avec la proposition d'`ines-design` (`alt=""`) ; la forme du cas fabrique de Q-Y8 a `theo-archi`.

**Ne fait pas** : ne touche ni catalogue, ni code, ni paquet, ni matrice, ni le planning (ses lignes de la section D que ce texte remplace restent ecrites la-bas ; le backlog fait foi pour le produit, et `sofia-sm` aligne le planning si elle le veut). Ne prononce aucun Done. Statut de la `DEC` inchange (`Proposee`). Aucun tarif change : `EXC-R15` reste a 0,25, `nadia-prof` n'ayant fourni aucun texte de remplacement.


---

## Amendement du 2026-10-08 (s55, cloture, `camille-po`, quatrieme) : etat a la cloture, reports comptes, statut inchange

Annotation datee. **Rien au-dessus n'est reecrit.** Sources lues : les blocs de verdict des six fiches, `audit-nadia-a.md`, `audit-nadia-b-emc.md`, `audit-nadia-b-exc.md`, `ARBITRAGE-COMPLEMENT-s55-instance-a.md`, `rapport-lea-dev-mecanique.md`, `git log` de `round-two` a `af8b326e`. Le detail, story par story, est dans le backlog ("Cloture de s55") ; la matrice porte sa note de s55.

### A.14 Ce que la cloture constate

- **Quatre lignes ferment**, sur les verdicts ecrits de `nadia-prof` (tous **VALIDE SOUS RESERVE**, reserves non bloquantes) : `FRA-CE1-14`, `FRA-CE1-12`, `EMC-CM1-03`, `EMC-CM1-06`. **137 / 182 (75,27 %) a 141 / 182 (77,47 %)**, par la commande de la "Methode du compte" de la matrice sur ses 258 lignes de donnees : `couvert-valide` 141, `en cours` 4, `non couvert` 37, `non couvrable en l'etat` 76. L'objectif gele de la section 1 ("au mieux quatre lignes") est atteint a quatre sur quatre.
- **Les deux jeux servis corriges sont rejoues** : `S54-ART-RES-R1` a `R3` **VALIDE** (trois reserves levees), `S54-EXC-CATEGORIE` **VALIDE SOUS RESERVE** (`EXC-R15` levee, `EXC-R16` neuve). Aucun mouvement de matrice sur `ART-CP-10` (reste `non couvert`) ni sur `EMC-CP-03` (reste `couvert-valide`).
- **Six reserves restent ouvertes** (`ACV-CE1-R1`, `ACV-CE1-R2`, `CLM-CE1-R1`, `EMC-CM1-R8`, `EMC-CM1-R9`, `EXC-R16`), **2,5 de rejeu** selon les prix ecrits dans les fiches (A 1,75, B 0,75). `ACV-CE1-R1` et `EXC-R16` ont leur texte applique mais ne sont pas levees tant que `nadia-prof` n'a pas rejoue. Stories : backlog, "Cloture de s55".
- **Mecanique d'`ART-CP-10`** : T2 et T3 livrees (`dfd2f107`, `363793e3`). **Pas un report** (la section 4 de cette `DEC` et A.5 point 1 ne comptaient comme report qu'une T3 non livree). Rien n'a ete joue sur le jar, aucun paquet n'est au catalogue, l'enfant ne voit rien (section 5.3, inchangee). Le paquet (T5), T4 et le choix des oeuvres distractrices ne sont pas engages : Q-U1 reste ouverte.

### A.15 Reports comptes a la cloture (section 4, mise a jour)

| Poste | Reports avant | Issue a la cloture de s55 |
|---|---|---|
| `S54-OBS-SCI-CONSIGNE` | aucun | **non engagee : premier report (s55)**, motif de A.10 inchange. Second report a s56 seulement par acte ecrit dans la `DEC` d'ouverture de s56, qui cite ce premier report ; troisieme interdit (`DEC-0112` section 6) |
| `S54-ASCII-APOSTROPHES-TESTS`, A7, A8 | aucun (engages par A.9) | **livres** (`51c81763`, `8541cffc`, `69d9d6a2`) : pas de report |
| `S54-PONCT-GN-INDICES`, `S54-EXC-CATEGORIE`, `S54-ART-RES-R1` a `R3` | aucun | **livrees** : pas de report. La relecture de ponctuation de `PONCT` par le `relecteur-pedagogique` n'est pas faite a cette date |
| `S55-ART-CP-10-MECANIQUE` | financee a s55 | **T2 et T3 livrees** : pas de report ; story non close |
| Paquet a reproductions (T5), T4, `S55-BR-FRA-CE1-L3` | non engages | pas un report a s55 (option ouverte, A.3, A.4) ; **la `DEC` d'ouverture de s56 les engage ou les compte** |
| `ART-CP-10` (la ligne) | s50 a s53 : quatre ; engagee a s54 et a s55 | engagee ; ni fermee ni reportee |

### A.16 Questions a l'utilisatrice, etat

- **Q6 (financement du dispositif seul d'`ART-CP-10`, Q-U3 de la section 5.5) : toujours ouverte.** Le financement de s55 reste de forme 3 (message relaye) ; **cette `DEC` reste `Proposee` jusqu'a ses propres mots, ecrits dans le depot en forme 2 avec leur date** (section 0.5). Ce n'est pas ma decision de la passer a `Acceptee`.
- **Q-U1 (moment du paquet a reproductions) : ouverte**, defaut propose par le planning : s56.
- **Q-U4 (emploi des 0,25 de A) : devenue un fait.** `nadia-prof` les a consommes en `A10` (complement, section 4) ; A est a 4,0 sur 4,0. L'utilisatrice n'a rien a trancher sur ce point ; je ne l'ecris pas comme sa decision.

### A.17 Routage et limites

- **Aucun changement de code ni de contenu n'est decide par cet amendement** : pas de routage a `sofia-sm` au sens de la regle des decisions qui impliquent du code. La composition de s56 est une proposition (`squad/sprints/s55-francais-ce1-emc-cm1-et-reserves-servies/proposition-s56.md`), pas une `DEC` d'ouverture.
- Ne touche ni catalogue, ni paquet, ni fiche, ni code. Ne prononce aucun Done : les quatre fermetures de la matrice reprennent les verdicts de `nadia-prof`. Statut de la `DEC` inchange : **`Proposee`**.

---

## Amendement du 2026-10-08 (s55, `camille-po`, cinquieme) : suite de la relecture de cloture (R1, R3, R6)

Annotation datee. **Rien au-dessus n'est reecrit.** Source : `squad/sprints/s55-francais-ce1-emc-cm1-et-reserves-servies/relecture-cloture.md` (`relecteur-pedagogique`, section 3), lu sur l'arbre `82e58343`. Chaque point a ete remesure avant d'etre amende ; le detail est dans `rapport-camille-relecture.md` du meme dossier. `R4` (reserves `EMC-CM1-R8` et `EMC-CM1-R9`, textes de `nadia-prof`) : rien a ecrire ici, leur rejeu est dans `proposition-s56.md`.

### A.18 R1 : la formule de la forme 3 manquait, et ce qu'elle interdit au financement

**Mesure** : `grep -c 'recu de MASTER'` sur cette `DEC` rend **0** ; `grep -c 'forme 3'` rend **12 lignes**. Les sections 0.1, 0.5, 5 (decision 1), A.1 et A.16 etiquettent "forme 3" sans la formule que `DEC-0062` (amendement A.2) et `squad/README.md` section 2 bis exigent. Le relecteur a raison : une etiquette sans la formule n'est aucune des trois formes.

**Formule, rappelee** : **`recu de MASTER, sans source dans le depot`**, avec l'enonce cite mot pour mot et la date de reception. Appliquee ici, date de reception du 2026-10-08 :

| Enonce | Lieu dans cette `DEC` | Ce que la formule ajoute |
|---|---|---|
| "CI verte sur fbe07d79" | 0.1 point 1 | recu de MASTER, sans source dans le depot ; non verifie, donc un fait a verifier et non un appui |
| `ok` (Q1, ordre des matieres) | 0.1 point 2 | recu de MASTER, sans source dans le depot ; fonde que l'ordre est suivi, pas pourquoi (la section 3 porte le motif en forme 1) |
| "la mecanique d'ART-CP-10 est financee DANS CE SPRINT (s55)" | 0.1 point 3, section 5 point 1 | recu de MASTER, sans source dans le depot ; texte de l'agent lanceur, majuscules comprises |
| "L'utilisatrice a tranche Q4 (message relaye, forme 3) : option (b) : TOUTES les arts plastiques CP vont a l'instance A de nadia-prof (PAR-2)." | A.1 | recu de MASTER, sans source dans le depot ; fonde le choix de (b), pas sa raison (forme 1, A.1) |

La mention "le texte est celui de l'agent, je ne sais pas lesquels de ces mots sont ceux de l'utilisatrice" (0.1, A.1) reste vraie : la formule range ces textes en forme 3, elle n'en fait pas des mots de l'utilisatrice, et aucun n'est cite en forme 2.

**Ce que la formule implique pour le financement de la mecanique.** `DEC-0062` section 3.2, confirmee par `Q6` de l'utilisatrice le 2026-08-31 (amendement A.2) : un enonce recu de MASTER "ne fonde aucune `DEC`, aucun `ADR`, aucun critere d'acceptation, aucun verdict" ; "une consigne recue par ce canal et qui doit fonder quelque chose devra d'abord etre ecrite quelque part".

1. **La section 5, decision 1 (financement), n'est pas une decision fondee.** Son unique appui est un enonce recu de MASTER. La lire comme "la `DEC` decide de financer" contredit la borne. La lire comme "la squad execute un ordre recu, que la `DEC` consigne et ne fonde pas" est la lecture de `DEC-0062` section 3.4 (un agent execute ce qu'on lui demande). **Je retiens la seconde.** La phrase de la section 0.5 "la section 5 s'applique des maintenant" se lit ainsi : elle consigne une execution, elle ne la decide pas.
2. **Rien d'autre ne repose sur ce message.** Les criteres de `S55-ART-CP-10-MECANIQUE` viennent d'`ADR-0104` et de `DEC-0114` section 6 point 4 (forme 1). Aucune ligne de matrice ne bouge (141 / 182 a la cloture, A.14, sur les verdicts de `nadia-prof`). Aucun tarif ne s'en deduit. L'enfant ne voit rien (section 5.3).
3. **Le financement devient une decision quand les mots de l'utilisatrice sont ecrits dans le depot en forme 2, avec leur date** (`Q6`, Q-U3 de la section 5.5, toujours ouverte). Si ses mots divergent, la section 5 est amendee (section 0.5) et le sort des deux commits livres (`dfd2f107`, `363793e3`) passe par une story routee a `sofia-sm` : je ne le decide pas avant ses mots.
4. **Le statut reste `Proposee`.** Cet amendement ne le change pas et ne remplace pas `Q6`.

**Corrections de fichier faites avec cet amendement** : `docs/backlog/backlog.md`, entree `S55-ART-CP-10-MECANIQUE` (ligne "Financement") et paragraphe "`DEC-0115` A.1 a A.8" des tarifs de A : la formule est ajoutee. Deux lignes du backlog de s54 (entrees citant `DEC-0114`) portent le meme manque ; elles sont hors du diff de s55 et hors de la demande du relecteur, je les laisse (`DEC-0062` section 7 : pas de balayage retroactif). `planning.md` lignes 27 et 395 : a `sofia-sm`, je n'y touche pas.

### A.19 R3 : les 0,25 de A sont consommes, A.3 et A.7 ne le disent pas

**Mesure.** Sur l'arbre lu par le relecteur (`af8b326e`), la `DEC` ne contenait ni A.14 ni A.16 : le fait n'etait ecrit que par `nadia-prof`, `ARBITRAGE-COMPLEMENT-s55-instance-a.md` section 4 ("A passe de 3,75 a 4,0 : `PAR-9` tenue, reserve 0", par `A10`). **Le point etait reel a `af8b326e`.** Depuis `a278a438` (cloture, ancetre de `82e58343`, non ancetre de `af8b326e`), A.16 l'ecrit ("Q-U4 : devenue un fait", "A est a 4,0 sur 4,0") et le backlog "Cloture de s55" le porte ("Fait de capacite"). **Ce qui reste faux a `82e58343`** : A.3 (les 0,25 "restent non alloues"), A.7 ("Nouvelle question a l'utilisatrice : Q-U4"), A.8 (routage de Q-U4 a l'utilisatrice), l'entree du `DEC-index.md` pour A.1 a A.8 et le paragraphe "`DEC-0115` A.1 a A.8" du backlog (A "3,75 / 4,0", "reserve non allouee : 0,25 sur A") disent l'inverse sans renvoyer a A.16.

**Amendement.** Remplace, sans reecrire :

| Ligne | Remplacee par |
|---|---|
| A.2, tableau, colonne A : total 3,75, reserve non allouee 0,25 | A : **4,0 sur 4,0, reserve 0** (`nadia-prof`, complement section 4 : `A10`, 0,25). B inchangee ici : 3,75 |
| A.3 : "Laisser les 0,25 non alloues est une troisieme issue" et "Tant qu'il n'est pas fait, les 0,25 restent non alloues" | Caduque : les 0,25 sont consommes par `A10`. Les deux emplois nommes en A.3 (choix des oeuvres distractrices, rejeu supplementaire) ne sont pas finances ; tout autre emploi porterait A a 4,25, au-dessus de `PAR-9`, sans ecart ecrit |
| A.7 et A.8 : question Q-U4 a l'utilisatrice | Retiree, comme A.16 le dit : un fait, pas une question |

Somme de A : lots `-14` 1,0 + `-12` 1,0 + `ART-RES` 0,25 + arbitrage 0,5 + plafonds `G7` a `G11` 0,25 + forme 0,25 + `A10` 0,25 = 3,5, plus la marge de rejeu de 0,5 = **4,0**. La marge n'a pas ete utilisee (backlog, "Fait de capacite"). Somme des deux instances dans cette `DEC` : 4,0 + 3,75 = **7,75** sur 8,0 (`PAR-8`), au lieu de 7,5. L'ecart de lecture sur B (3,5 ou 4,0 selon que la marge non allouee entre dans l'addition) est deja signale au backlog et laisse a `nadia-prof`.

### A.20 R6 : le point 5 de "Decisions prises" contredit A.2 sur `ART-RES`

**Mesure.** Point 5 : "Cinq postes non engages (`ASCII-APOSTROPHES`, A7, A8, `OBS-SCI-CONSIGNE`, et `ART-RES` sans instance) : premier report compte". A.2 : `S54-ART-RES-R1` a `R3` ont une instance (A, 0,25) et "le premier report sans instance (Q5) de la section 4 ne se produit pas". A.7 remplace le tableau de la section 4, pas le point 5. **Le point etait reel.** Le point 5 est en outre deja depasse par A.9 (`ASCII-APOSTROPHES`, A7, A8 engages) et A.10 (`OBS-SCI-CONSIGNE`, seul poste non engage).

**Amendement.** Le point 5 est remplace par : **un seul poste non engage, `S54-OBS-SCI-CONSIGNE`, premier report (s55), motif de A.10** ; `S54-ART-RES-R1` a `R3` sont engagees sur l'instance A, sans report (A.2) ; `S54-ASCII-APOSTROPHES-TESTS`, A7 et A8 sont engages (A.9) et livres (A.15). Le point 1, qui repete "sous Q5" et "6,75 sur 8,0", se lit avec A.2, A.7 et A.19 : `ART-RES` a l'instance A, 7,75 sur 8,0.

### A.21 Routage, limites, statut

- **Statut inchange : `Proposee`** (section 0.5, A.18 point 4). Aucune ligne de matrice ne change.
- Aucun changement de code ni de contenu : pas de routage a `sofia-sm` au sens de la regle des decisions qui impliquent du code. Les lignes de `planning.md` (27, 395, et les criteres que le relecteur range en `R2`) restent a `sofia-sm`.
- Ne prononce aucun Done, ne fixe aucun tarif, ne cite aucun mot de l'utilisatrice.

---

## Amendement du 2026-10-09 (s55, `camille-po`, sixieme) : Q6 repondue, statut `Acceptee`

Annotation datee. Rien au-dessus n'est reecrit, sauf le statut, en tete de fichier, en 0.5 et au point 6 de "Decisions prises" : chacun renvoie ici.

### A.22 Les mots de l'utilisatrice sur le financement de la mecanique

**Forme 2 de `DEC-0062` (section 3.1) : `de l'utilisatrice`, le 2026-10-09**, mot pour mot :

> "J'accepte que la mécanique d'ART-CP-10 soit financée dans s55."

**Reception.** Le texte m'est parvenu dans la consigne de l'agent qui m'a lance, qui l'atteste reponse directe de l'utilisatrice dans la conversation. Je ne l'ai pas lu de sa main et je ne peux pas le verifier depuis le depot : le statut ci-dessous repose sur cette attestation. Si l'utilisatrice la dement, il redevient `Proposee`.

**Ce que la `DEC` fonde desormais.**

1. Le financement de la mecanique d'`ART-CP-10` tel que la section 5 (decision 1) le decrit : T2 et T3, story `S55-ART-CP-10-MECANIQUE`, deja livrees (`dfd2f107`, `363793e3`). Ce financement etait une execution consignee (A.18 points 1 et 2) ; c'est maintenant une decision.
2. A.18 : la lecture "execution consignee, non decision fondee" valait tant que ces mots manquaient ; elle ne vaut plus pour la section 5 decision 1. Le point 3 (mots divergents) ne joue pas, le point 4 (statut `Proposee`) est caduc.
3. `Q6` (Q-U3 de la section 5.5) est close. La condition de la section 0.5 et du point 6 de "Decisions prises" est remplie.

**Ce que ses mots ne disent pas, et que cet amendement n'ecrit donc pas.** Le paquet a reproductions (T5), T4, l'audit, un ecart a `PAR-9` ou a la borne de 8,0, un tarif, le proprietaire du paquet. Je lis "la mécanique" comme ce que la section 5 nomme ainsi (le dispositif, T2 et T3) et rien de plus : la section 5 reste telle qu'ecrite, paquet et audit non finances par cette `DEC`. `Q-U1` et `Q-U2` ne sont pas closes ici ; les reponses du 2026-10-09 a la proposition de s56 sont dans `squad/sprints/s55-francais-ce1-emc-cm1-et-reserves-servies/proposition-s56.md` (section 9) et ne sont pas la `DEC` d'ouverture de s56.

### A.23 Routage, limites, statut

- **Statut : `Acceptee`** (2026-10-09). Sections 1 a 4 non touchees.
- Aucun changement de code ni de contenu : le dispositif est livre et sa story existe, il n'y a rien a router a `sofia-sm`.
- Aucune ligne de matrice ne change. Ne prononce aucun Done, ne fixe aucun tarif.
