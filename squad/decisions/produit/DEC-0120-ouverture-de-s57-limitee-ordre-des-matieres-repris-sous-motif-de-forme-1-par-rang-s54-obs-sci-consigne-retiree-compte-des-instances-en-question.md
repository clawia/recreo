# DEC-0120 - Ouverture de s57, limitee : l'ordre des matieres repris sous un motif de forme 1 par rang (table
# de `DEC-0115` section 3 remesuree), `S54-OBS-SCI-CONSIGNE` retiree, prerequis du francais CE1 a deux instances,
# postes connus ; le compte des instances est une question ; le sprint n'est pas ouvert

- **Type** : decision d'ouverture de sprint, limitee (ordre, prerequis, postes connus). N'ouvre pas le sprint.
- **Date** : 2026-10-13
- **Auteur** : `camille-po`
- **Sprint** : s57 (non ouvert : ni `planning.md`, ni composition, ni tarif, ni affectation)
- **Competences programme visees** : `FRA-CE1-04`, `-05`, `-06`, `-07`, `-08`, `-09`, `-10`, `-11`, `-15`, `-24` (francais CE1,
  les dix lots du rang 1) ; `ART-CP-10` (reprise, ligne `non couvert`) ; `FRA-CE1-13` et `SCI-CM1-20` (lignes deja `couvert-valide`,
  non rouvertes). Les autres postes sont transverses - pas de contenu pedagogique. **Aucune ligne de matrice ne change** : 142 / 182
  (78,02 %), commande de la "Methode du compte" relancee le 2026-10-13.
- **Statut** : **Proposee**. Ses decisions portent un motif de forme 1 ou 2 et s'appliquent des maintenant ; une question a
  l'utilisatrice reste ouverte (`Q-U1`, section 5) et gouverne la composition de s57, pas l'ordre. Elle passe a `Acceptee` quand `Q-U1`
  a une reponse et que la retraite de `S54-OBS-SCI-CONSIGNE` (4.1) n'est pas contredite. **Elle est la `DEC` qui reprend l'ordre sous des
  motifs de forme 1 : `DEC-0116` passe a `Acceptee` par son amendement A.37**, comme `DEC-0114` l'a ete par `DEC-0115` (A.27) quand
  `DEC-0115` etait elle-meme `Proposee`.
- **Lien** : `DEC-0114` (0.4, 7.1, 7.2, A.20) ; `DEC-0115` (0.4, 3, A.8) ; `DEC-0116` (0.5, A.2, A.16, A.18, A.19, A.21 a A.25, A.32 a A.36) ;
  `DEC-0112` section 6 ; `DEC-0062` (formes 1, 2, 3) ; `DEC-0009` ; `DEC-0117` section 11 ; `DEC-0119` ;
  `squad/sprints/s56-paquet-art-cp-10-et-francais-ce1-13/` (`retro.md`, `review.md`, `cloture-nadia.md`, `avis-faisabilite.md`) ;
  `squad/sprints/s55-francais-ce1-emc-cm1-et-reserves-servies/proposition-s56.md` ;
  `squad/conformite/ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md` (section 6), `ARBITRAGE-COMPLEMENT-s55-instance-a.md` (section 3),
  `ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md` (3.3), `ARBITRAGE-PREALABLE-s52-instances-ce1.md` (4.2, 5) ;
  `docs/backlog/backlog.md` (section "Ouverture limitee de s57") ; `squad/programme/MATRICE-couverture.md`

---

## 0. Provenance et methode

### 0.1 Les mots de l'utilisatrice, forme 2 de `DEC-0062`, et ce qu'ils fondent

Mots de l'utilisatrice, recus directement dans la session du 2026-10-13, tels que l'agent qui m'a lance me les transmet : *"reprend l'ordre"*.
**Je ne les ai pas lus de sa main** (meme reserve que `DEC-0116` 0.1, A.21, A.32) : si elle les dement, la section 1 est amendee et les autres
sections ne sont pas touchees. L'agent lanceur les rattache au choix (ii) de `DEC-0116` A.22 ; ses mots ne nomment ni (ii), ni `DEC-0116`, ni
s57 : ce rattachement est le sien. Le 2026-10-11, elle avait ecrit *"D2 ordre ok"* (`DEC-0116` A.21).

**Ce que ces mots fondent** : que l'ordre de `DEC-0115` section 3 est suivi pour s57. **Ce qu'ils ne fondent pas** : pourquoi il est defendable.
Je n'en fais pas un motif (`DEC-0114` 0.4, `DEC-0115` 0.4) : chaque rang porte, section 1, un motif que le depot permet de verifier.

### 0.2 Sources de forme 1, par auteur

`nadia-prof` : arbitrage de s55 instance A (section 6 : plafonds de `G7` a `G11`), complement de s55 (section 3 : plafond de `G3`), arbitrage de
s56 instance A (3.3), `cloture-nadia.md` de s56 (prix de reprise), arbitrage de s52 (4.2, 5) ; `theo-archi` : `avis-faisabilite.md` de s56
(sections 8 et 9) ; `sofia-sm` : `retro.md`, `review.md` de s56 ; `camille-po` : `DEC-0112` a `DEC-0119`, backlog, matrice.

### 0.3 Mesures lancees pour cette decision (arbre `round-two` a `900db2dc`, worktree `ordre-camille`, 2026-10-13, perimetre : le worktree entier)

| Mesure | Commande ou fichier | Rendu |
|---|---|---|
| Matrice, total | commande de la "Methode du compte" de `MATRICE-couverture.md`, 258 lignes | `couvert-valide` 142, `en cours` 4, `non couvert` 36, `non couvrable en l'etat` 76 ; 142 / 182 |
| Matrice, par matiere et niveau | la meme commande, `$2` et `$3` ajoutes comme cle de comptage | table de la section 1 |
| Fichiers de contenu sans entree de catalogue | `comm -23` des fichiers de `contenu/` et des `paquet:` de `catalogue.yaml` (commande de `proposition-s56.md` section 1) | **37** sur 183 fichiers ; 146 paquets au catalogue |
| Les 37, par nom | lecture de la liste | 10 francais CE1 (rang 1) + 2 `G3` + 5 `G4` (rang 4) + 16 `G7` a `G11` (rang 3) + 2 sciences CM1 refuses a s51 + `cp-emc-personnes-differentes` + `cp-rapprocher-deux-oeuvres` (retire, `13d00bfe`) = 37 |
| Items des 16 paquets de `G7` a `G11` | `grep -c '^ *- enonce:'` sur chacun, somme | 216 |
| Rang 2 | meme `comm -23` filtre par `grep -E "cm1-emc\|cm1-sciences"` ; `grep -c "paquet: <nom>$"` sur `catalogue.yaml` pour `cm1-emc-respect-differences`, `cm1-emc-principes-republique`, `cm1-sciences-terre-jour-nuit-saisons`, `cm1-sciences-environnement-activite-humaine` | 2 fichiers (`cm1-sciences-terre`, `cm1-sciences-activite-humaine`, les refus de s51) ; 1, 1, 1, 1 |
| Cinq derniers lots de francais CE1 | `awk` sur les lignes `francais` / `CE1` de la matrice, occurrences de `REFUS` dans les lignes de `S54-BR-FRA-CE1-18`, `-19`, `S55-BR-FRA-CE1-L1`, `-L2`, `S56-BR-FRA-CE1-13` | 0 dans chacune |
| Mesure de pli de `LIS-2` | `ls squad/conformite` ; lecture de `mesure-ines-lis-pli-s42.md` section 4 et de l'arbitrage de s56 instance A, 3.3 | une mesure de `ines-design` du 2026-09-24 (plafond haut, 70 mots) rend *"Non"* ; l'arbitrage de s56 ecrit *"la mesure de pli de `LIS-2` n'est pas faite"* |
| Fichiers de s57 | `ls squad/conformite \| grep -ci s57` ; `ls squad/sprints \| grep -ci s57` | 0 ; 0 : aucun arbitrage prealable, aucun dossier |
| `ADR-0065` et deux instances | `grep -c -i instance` sur le fichier de l'`ADR` | 0 |
| `S54-OBS-SCI-CONSIGNE` | `grep -c "puis réponds à la question"` sur `contenu/cm1-sciences-extraire-information.yaml` ; sur la `consigne` de l'entree du catalogue | 12 ; 1 : inchange depuis le constat de s54 |
| `APM-R2` | `grep -n "porteur.choisirLaVue\|portePartieEnCours"` sur `PartieController.java` | l'ecriture de la vue (ligne 250) precede le test de la partie en cours (ligne 251) ; lecture, non rejouee par moi |
| `HS-ACCUEIL-MESURE-DEFAUT` | `git log` sur `outils/mesurer-accueil.py` ; `grep -n "vue par defaut"` | un seul commit (`ff86c571`, anterieur a `DEC-0119`) ; l'outil juge encore la page par defaut comme V2 (lignes 120 et 155) |
| `RP-ACCUEIL-147` | `grep -n 147` sur `ARBITRAGE-PREALABLE-accueil-par-matiere.md` | lignes 41, 236 (a corriger) et 398 (ecart constate, voulu) |

Ce que je n'ai pas lu ni lance : aucun avis de `theo-archi` ni arbitrage prealable de `nadia-prof` pour s57 (ils n'existent pas) ; aucun jar, aucun
test Maven (cette decision ne change ni code ni contenu). Les tarifs sont ceux que `nadia-prof` a ecrits en s55 et s56, additionnes sans recompte.

### 0.4 Ce que `DEC-0114` 0.4 (b) et `DEC-0116` 0.5 exigent, et ce que cette decision fait

`DEC-0114` 0.4 (b), repris par `DEC-0115` 0.4 : l'ordre est repris *"sous son propre motif de forme 1"*, **si, et seulement si, chaque rang porte un
motif que je peux verifier**, et le rang dont la source n'etait pas remesuree est nomme. `DEC-0116` A.22 (ii) demande la meme chose a la `DEC`
d'ouverture de s57 : *"sous des motifs de forme 1 par rang"*, la table de `DEC-0115` section 3 **remesuree**. La section 1 le fait, rang par rang,
avec la commande ou le fichier lu. **Ce que cette decision n'est pas** : le planning de s57. Elle n'affecte personne, n'ecrit aucun tarif, ne compose
aucun sprint ; `sofia-sm` et `nadia-prof` ecrivent le planning et l'arbitrage prealable (section 6).

---

## 1. Ordre des matieres pour s57 : repris sous un motif de forme 1 par rang

Ordre de `DEC-0115` section 3, suivi (0.1). **Aucun rang n'est deplace.** Ce que la remesure change est en 1.2.

Etat par matiere et niveau, lignes **non fermees** (la commande compte 20 matieres et niveaux ; les absents n'ont ni `non couvert` ni `en cours`) :

| Matiere / niveau | Atteignables | `couvert-valide` | `en cours` | `non couvert` | Rang |
|---|---|---|---|---|---|
| francais CE1 | 26 | 14 | 0 | 12 | 1 (10) et 4 (2, `G3`) |
| mathematiques CE1 | 18 | 11 | 2 | 5 | 4 (`G4`) |
| enseignement moral et civique CM1 | 11 | 11 | 0 | 0 | 2 : epuise |
| sciences et technologie CM1 | 19 | 19 | 0 | 0 | 2 : epuise |
| enseignement moral et civique CP | 12 | 11 | 0 | 1 | hors rang : sous refus (`DEC-0081` section 7) |
| arts plastiques CP | 4 | 3 | 0 | 1 | hors rang : `ART-CP-10`, reprise (4.2) |
| `G7` a `G11` (francais CP et CM1, questionner le monde CP, histoire-geographie CM1, anglais CM1, arts plastiques CM1, histoire des arts CM1) | 17 lignes `non couvert` pour 16 paquets (une ligne d'arts plastiques CM1 n'a aucun paquet) | | | 17 | 3 |

| Rang | Groupe | Atteignable a s57 (mesure) | Motif de forme 1 | Source, mesure |
|---|---|---|---|---|
| 1 | `G1`/`G2` francais CE1 | **10 paquets, 10 lignes** `non couvert` qui peuvent fermer. Groupe X (5) : `texte-narratif`, `pronoms`, `inference`, `texte-documentaire`, `negation`. Groupe Y (5) : `ordre-des-mots`, `relire-une-phrase`, `phrases-types`, `accord-groupe-nominal`, `familles-de-mots` | **Chemin critique** : 10 lots, trois au plus par instance et par sprint (`PAR-8`) : une instance finit a **s60** (3, 3, 3, 1), deux instances a **s58** (6 puis 4) si la seconde est active a s57. Chaque lot ferme une ligne (`proposition-s56.md` section 1). Les cinq derniers lots de la matiere ont ferme sans refus ecrit dans les lignes de la matrice (0.3) ; l'hypothese de zero refus reste une hypothese pour les dix | `comm -23` (37, 10 en francais CE1) ; matrice : 12 `non couvert`, dont 2 de `G3` ; arithmetique de `DEC-0116` A.23 |
| 2 | `G6`/`G5` sciences et EMC CM1 | **0**. EMC CM1 : 11 sur 11 atteignables `couvert-valide` ; sciences CM1 : 19 sur 19. Les deux paquets de sciences refuses a s51 n'ont plus de ligne a fermer (`SCI-CM1-15` et `-18` ont ferme a s54 par d'autres paquets, entres au catalogue). `cp-emc-personnes-differentes` (`EMC-CP-04`) reste sous refus : pas un lot | Rien a brancher ni a fermer. Le rang est **epuise**, pas deplace | matrice (restriction `enseignement moral` / `CM1` et `sciences` / `CM1`) ; `comm -23` filtre ; `grep -c` des quatre paquets (1, 1, 1, 1) |
| 3 | `G7` a `G11` (16 paquets, 216 items) | **0 pour s57.** Plafonds ecrits par `nadia-prof` : 15 paquets sur 16 plafonnes a `non couvert` (brancher l'un d'eux est la situation de `DEC-0009`, elle ne recommande aucun lot) ; le seizieme, `cm1-lire-et-restituer` (`FRA-CM1-01`), peut fermer apres une mesure de pli de `LIS-2` et un lot de 1,0. L'utilisatrice a repondu `D4 non` (`DEC-0116` A.21, A.24) : pas de mesure | Le motif de `DEC-0115` ("aucun plafond ecrit") **n'est plus vrai** : l'arbitrage que ce rang attendait est ecrit. Le rang reste dans l'ordre, **vide a s57** ; un lot de `cm1-lire-et-restituer` demande d'abord la mesure, que ni `ines-design` ni elle n'a faite (0.3) | arbitrage de s55 instance A, section 6 ; arbitrage de s56 3.3 ; `mesure-ines-lis-pli-s42.md` ; `DEC-0116` A.24 |
| 4 | `G3`/`G4` (7 paquets) | **0 ligne fermable aujourd'hui.** `G3` (2, `FRA-CE1-03` et `-21`) : plafond `en cours`, reconfirme le 2026-10-08. `G4` (5, mathematiques CE1) : `en cours` au plus pour quatre lignes ; `deplacements-quadrillage` fermerait `MAT-CE1-14`, mais cette ligne depend de `A3` d'`ADR-0080`, non ecrit | Aucun lot de ce rang ne change `couvert-valide` a s57 ; il n'est atteint, au plus tot, qu'apres les 10 lots du rang 1 | arbitrage complement s55, section 3 (*"le rang 4 de `DEC-0115` section 3 tient sans amendement"*) ; arbitrage de s52, 4.2 ; `DEC-0112` 5.3 ; `ADR-0080` : dernier amendement du 2026-10-06 |

**Un rang qui n'est pas remesure par moi** : aucun. Le rang 4 etait le seul cite sans mesure en `DEC-0115` ; il est remesure ici (commande de matrice, `comm -23`, plafonds lus).

### 1.2 Ce que la remesure change a `DEC-0115` section 3

1. **Rang 1** : 13 paquets (s55) devenus 10 (`-14`, `-12` branches a s55, `-13` a s56).
2. **Rang 2** : de deux paquets branchables a zero. Epuise.
3. **Rang 3** : le motif change (plafonds ecrits). Le rang est vide a s57.
4. **Rang 4** : "ne ferment aucune ligne" est exact aujourd'hui, avec une precision que `DEC-0115` ne portait pas : `MAT-CE1-14` fermerait par `deplacements-quadrillage` si `A3` etait ecrit (`DEC-0112` 5.3 : *"liee a A3"*).

**Ce que l'ordre donne a s57** : le rang 1 seul porte des lots. Il n'y a donc, a s57, aucun arbitrage d'ordre entre matieres a faire pour les lots ; l'ordre dit ce qui se passe quand le rang 1 sera epuise et que le rang 4 sera le prochain. `PAR-13` reste jugee au niveau de la matiere par `nadia-prof` (le francais CE1 n'est pas un terrain neuf) ; `ordre-des-mots` et `relire-une-phrase` n'ont aucune cousine servie : au plus un par sprint, et `relire-une-phrase` apres `accord-groupe-nominal` (`RLP-CE1-1`).

---

## 2. Le compte des instances : deux sous les deux lectures, la lecture n'est pas decidee

**Les mots** : *"D3 deux instances de nadia"* (`DEC-0116` A.21, relayes). L'agent lanceur les lit comme *deux instances de francais CE1* ; ils ne nomment ni le sprint ni le francais CE1 (A.21).

**Ce qui decoule des faits** : `PAR-9` (somme des budgets par instance egale a la borne de 8,0 ; `ARBITRAGE-PREALABLE-s52-fra-ce1-questions-de-l-avis.md` 4.3) donne 4,0 par instance a deux ; chaque instance porte un arbitrage prealable (0,5) et une marge de rejeu (0,5) : **3,0 de travail par instance, 6,0 au total** (`DEC-0116` A.23, point 3). Une troisieme instance demande son propre ecart et la verification `A10` (`DEC-0114` A.20 ; `DEC-0116` section 6, ligne `A10`, et A.23 point 4) : personne ne les demande. **L'ecart de s56 n'est pas une jurisprudence** : s57 compte a 8,0.

| Lecture | Francais CE1 | Reprise d'`ART-CP-10` | Lots de francais CE1 par sprint | Les 10 lots finis a | Ce que l'autre instance porte |
|---|---|---|---|---|---|
| (A, A') | deux instances (X et Y) | A ou A' (`PAR-2` : une seule instance lit la matiere ; a s55, A lisait francais CE1 et arts plastiques CP) | 6 si l'amendement d'`ADR-0065` est fusionne avant le premier lot de A' ; 3 sinon | **s58** ; s59 si A' attend l'amendement | tout est en francais CE1 |
| (A, B) | une instance (A) | B | 3 | **s60** | B : 0,5 de reprise, et rien d'autre dans le depot : rang 2 epuise, rang 3 vide, `S54-OBS-SCI-CONSIGNE` retiree (4.1) ; 3,0 de travail de B sans poste nomme |

Arithmetique, pas mesure : elle suppose zero refus au-dela de la marge (tenue sur les cinq derniers lots, 0.3) et le tarif de reprise de s56 (0,5 chacun pour la reprise d'`ART-CP-10` et `VSJ-CE1-R1`). Si les deux reprises sont prises, il reste 5,0 pour les lots : **cinq lots, pas six**, dans la lecture (A, A') ; dans (A, B), A porte `VSJ-CE1-R1` (groupe X) et deux lots.

**Cette decision ne tranche pas entre les deux lectures** : le depot n'a pas les mots qui le feraient (A.21), et `nadia-prof` n'a pas ecrit son accord sur `PAR-2` (3.1). **`Q-U1`.**

---

## 3. Prerequis du francais CE1 a deux instances (`DEC-0116` A.23), etat mesure

Ils ne s'appliquent que dans la lecture (A, A'). Aucun n'est place par un autre que moi : cette section les place, avec leur etat.

| # | Prerequis | Etat au 2026-10-13 | Place |
|---|---|---|---|
| 3.1 | **Accord de `nadia-prof` sur `PAR-2`** pour le francais CE1. Elle l'a refuse le 2026-10-06 et ecrit ce qui la ferait reexaminer : un decoupage qui ne coupe aucune frontiere de la table de s48, sur un sprint ou aucune autre instance ne porte de terrain neuf, avec un avis de `theo-archi` sur `ADR-0065` (`DEC-0114` 7.2). La reponse de l'utilisatrice ne vaut pas accord de `nadia-prof` | non ecrit (aucun arbitrage prealable de s57). Le decoupage X / Y de `DEC-0114` 7.2, applique a l'etat d'aujourd'hui, donne X 5 paquets, Y 5 (section 1). Dans (A, A'), savoir si la reprise d'`ART-CP-10` est un terrain neuf au sens de sa condition est a elle. La condition *"un sprint ou A prend X seule"* est remplie par `-13` a s56 | dans son arbitrage prealable de s57, **avant le premier worktree d'un lot de A'** |
| 3.2 | **Amendement d'`ADR-0065`** (`S57-ADR-0065-DEUX-INSTANCES`), fusionne avant tout lot de la seconde instance (`avis-faisabilite.md` de s56, section 9) | `grep -c -i instance` sur l'`ADR` : 0 ; aucune branche | proprietaire : **`theo-archi`**, qui ecrit que c'est *"une story de `theo-archi`"* ; heure de fusion a inscrire au planning par `sofia-sm`. **Engagee seulement dans la lecture (A, A')** |
| 3.3 | **Budget** : 4,0 par instance, somme 8,0 | `PAR-9` ; ecart de s56 non reconduit | 2 |
| 3.4 | **`A10`** | due seulement pour une troisieme instance | sans objet |

Si 3.2 n'est pas fusionne quand le premier lot de A' devrait commencer, **ce lot glisse** : `sofia-sm` le dit a l'utilisatrice le jour meme, et le calendrier du rang 1 passe de s58 a s59.

---

## 4. Postes connus

"Retenu" veut dire : candidat de l'ouverture, sous les conditions ecrites. Rien n'est engage sans proprietaire ni prix (`DEC-0114` section 4, `DEC-0115` section 4) : les prix sont ceux de `nadia-prof` ; son arbitrage prealable de s57 les confirme ou les change.

### 4.1 `S54-OBS-SCI-CONSIGNE` : retiree par un acte ecrit (troisieme report interdit)

**Etat et compte.** Ouverte a s54 ; premier report a s55 (`DEC-0115` A.10) ; second a s56 (`DEC-0116` section 5, A.2). **Deux reports.** `DEC-0112` section 6 : *"un troisieme report n'est plus permis"* ; l'utilisatrice, le 2026-10-09 : *"Reporter a s57, abandon ou placement ecrit alors"* (`DEC-0116` A.2, forme 2). **La placer** exige, selon `DEC-0114` section 4 et `DEC-0115` section 4 : criteres de `nadia-prof`, proprietaire, prix. **Aucun des trois n'existe** (0.3 : pas d'arbitrage de s57 ; backlog s54 : *"a ecrire quand une instance l'engage, avec les mots de `nadia-prof`"*), et `retro.md` de s56 ne les a pas produits.

**Decision : retiree du backlog actif, sans suite engagee.** Motifs de forme 1 : (1) l'issue que `DEC-0112` section 6 permet quand le placement est impossible ; (2) les trois elements du placement manquent depuis s54 ; (3) `nadia-prof` l'ecrivait *"pour ne pas les avoir vues sans les dire"* : ce n'etait pas une reserve (backlog, s54) ; (4) l'enfant lit deux fois *"puis réponds à la question"* (12 enonces et la consigne, 0.3) : une redite, pas un texte faux ; la ligne `SCI-CM1-20` reste `couvert-valide`. **Reversible** : une story neuve, avec criteres ecrits par `nadia-prof`, proprietaire et prix, la rouvre. `Q-U2` si l'utilisatrice prefere la placer.

### 4.2 Reprise d'`ART-CP-10` (`S56-ART-CP-10-REPRISE`, `S56-RDO-CP-R1`, `S56-RDO-CP-R2`) : retenue, en tete des postes d'arts plastiques CP

**Etat.** Le paquet est refuse (`FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md`, 2026-10-11) et retire du catalogue (`13d00bfe`, mots de l'utilisatrice du 2026-10-12, `DEC-0116` A.26) ; le fichier, les JPEG, les garde-fous et la fiche sont conserves. Les criteres du backlog sont rouges (`.reponse-revelee` finit a 656 pour un pli a 551 ; cotes de `nadia-prof` et d'`ines-design`, non rejouees par moi). **Compte** : la ligne porte quatre reports (s50 a s53) et trois engagements sans fermeture (s54, s55, s56) ; la reprise elle-meme n'a jamais ete engagee : aucun report.

**Decision.** Retenue, **avant tout lot neuf** : motifs de forme 1 du backlog ("Cloture de s56") : (1) seule ligne que s56 a engagee sans la fermer ; (2) la ligne compte sept evenements sans fermeture (quatre reports, trois engagements) et un refus classe sans suite en ajouterait un ; (3) le contenu est juste, le paquet n'a besoin que de 105 px et de deux textes (fiche) ; **et, calcule ici a partir des prix de `nadia-prof`** (4) rendement : un rejeu de 0,5 (`cloture-nadia.md` section 3) ferme au mieux une ligne, un lot en coute 1,0. **Conditions avant l'ouverture du worktree** : un developpeur et la forme de la correction (`theo-archi`, `ines-design`, `lea-dev` : pas a moi) ; le prix et la place du rejeu confirmes par `nadia-prof`. **Comment `nadia-prof` rejoue un paquet sans entree de catalogue** : les fichiers lus ne l'ecrivent pas pour ce cas (`DEC-0116` A.17 dit seulement qu'un retour passe par une entree neuve apres un Done non refuse) : question a `sofia-sm` et `theo-archi`, pas a l'utilisatrice. **Si le correctif n'est pas fusionne quand le rejeu devrait commencer, il glisse : premier report de ce poste, signale le jour meme.**

### 4.3 `VSJ-CE1-R1` (`S56-VSJ-CE1-R1`, 0,5) : retenue

Reserve non bloquante de `FRA-CE1-13` (`couvert-valide`, non rouverte) ; jamais engagee : aucun report. Ses mots : *"un enfant qui choisit la proposition la plus a gauche dans la phrase reussit les 15 sans poser la question"* ; aujourd'hui 0 item sur 15 de sujet a une proposition fausse avant le verbe (backlog, script de poste, non commite). **Decision** : retenue, apres la reprise d'`ART-CP-10`, sur l'instance qui porte le groupe X (celle de `-13`) ; developpeur `marc-dev` (backlog) ; precedent : les six reserves de s55 ont toutes ete levees a s56 (`DEC-0116` A.15). Sous (A, A') elle consomme 0,5 de la capacite de A ; elle n'est pas un lot.

### 4.4 `S57-ADR-0065-DEUX-INSTANCES`

Section 3.2. Engagee dans la lecture (A, A') seulement, proprietaire `theo-archi`, hors `PAR`, 0,0 pour `nadia-prof`.

### 4.5 Postes du chantier hors sprint "accueil par matiere" : listes, non composes

`RP-ACCUEIL-147`, `APM-R2`, `HS-ACCUEIL-MESURE-DEFAUT` ne sont pas des postes de `PAR`. Je les liste parce que le planning de s57 les trouvera au backlog et qu'ils touchent le temps de `nadia-prof`.

| Poste | Etat mesure (0.3) | Proprietaire | Ce que cette decision dit |
|---|---|---|---|
| `RP-ACCUEIL-147` | 147 aux lignes 41 et 236 du fichier de `nadia-prof` ; son compte juste est 146 (retrait `13d00bfe`) | `nadia-prof` (le fichier est le sien) | **A reprendre au planning de s57**, comme l'utilisatrice l'a demande le 2026-10-12 (*"ajoute le au repo et ping nadia dessus sur le sprint prochain"*, journal du 2026-10-12). Prix : non ecrit |
| `APM-R2` | reserve neuve et non bloquante de `FICHE-accueil-par-matiere.md` ; **story ecrite ici** (backlog), non engagee | developpeur a designer par `sofia-sm` ; moyen a `theo-archi` ; test de route a `yanis-qa` | Tout changement du chantier passe par l'audit de `nadia-prof` **avant fusion** (`DEC-0117` section 11) : prix non ecrit, a elle |
| `HS-ACCUEIL-MESURE-DEFAUT` | ouverte : l'outil juge encore la page par defaut comme V2 | `yanis-qa` | Aucun prix de `nadia-prof` |

---

## 5. Questions pour l'utilisatrice

| Code | Question |
|---|---|
| `Q-U1` | **Les deux instances de `nadia-prof` de s57 sont-elles A et A' (deux instances de francais CE1, la reprise d'`ART-CP-10` portee par l'une d'elles), ou A et B (une instance de francais CE1, B pour les arts plastiques CP) ?** Ses mots du 2026-10-11 ne le disent pas (A.21). Les faits de la section 2 : (A, A') finit le francais CE1 a s58 (s59 si `ADR-0065` n'est pas amende a temps) et exige l'accord de `nadia-prof` sur `PAR-2` ; (A, B) finit a s60 et laisse 3,0 de B sans poste nomme. Je ne choisis pas a sa place |
| `Q-U2` | **`S54-OBS-SCI-CONSIGNE` : la retraite de 4.1 lui convient-elle, ou veut-elle la placer ?** La placer demande des criteres ecrits par `nadia-prof`, un proprietaire et un prix, que le depot n'a pas. Reponse attendue avant l'ouverture du planning |

---

## 6. Routage (changement de fichiers et de code)

Cette decision implique du code (`S57-ADR-0065-DEUX-INSTANCES`, la correction d'`ART-CP-10`, `VSJ-CE1-R1`, `APM-R2`, l'outil de mesure). **Chaque poste a une story au backlog et un proprietaire nomme par sa source, ou la mention "a designer par `sofia-sm`"** ; un poste sans proprietaire reste non engage.

| A qui | Quoi |
|---|---|
| `sofia-sm` | Planning de s57, ecrit apres `Q-U1` : compte des instances, prix de `nadia-prof`, heure de fusion de l'amendement d'`ADR-0065` (3.2), developpeur d'`APM-R2` et de la correction d'`ART-CP-10`, ligne de `RP-ACCUEIL-147` ; consignation des depassements de garde-fous et ecriture des arrets d'agent (`DEC-0116` A.36, points 2 et 3, non repris ici) ; question 4.2 sur le rejeu d'un paquet sans entree |
| `nadia-prof` | Arbitrage prealable de s57 : accord sur `PAR-2` (3.1), tarifs de la reprise, de `VSJ-CE1-R1`, de `RP-ACCUEIL-147` et de l'audit d'`APM-R2` ; lecture de `PAR-13` sur le groupe Y. Je n'ecris aucun de ces points |
| `theo-archi` | `S57-ADR-0065-DEUX-INSTANCES` (3.2) ; le moyen d'`APM-R2` ; la forme de la correction d'`ART-CP-10` avec `ines-design` et `lea-dev` |
| `marc-dev`, `lea-dev` | `VSJ-CE1-R1` (`marc-dev`) ; la correction d'`ART-CP-10` (forme a eux) : aucun edit avant l'ouverture et l'avis de `theo-archi` |
| `yanis-qa` | `HS-ACCUEIL-MESURE-DEFAUT` ; le test de route d'`APM-R2` ; lecture de testabilite des criteres neufs avant la premiere livraison de s57 |
| `camille-po` | Fiches de conformite aux branchements ; matrice au verdict seulement ; amendement de cette decision apres `Q-U1` |
| Utilisatrice | `Q-U1`, `Q-U2` |

---

## 7. Ce que cette decision ne fait pas

- Elle n'ouvre pas s57 : pas de `planning.md`, pas de composition, pas de tarif, pas d'affectation, pas de worktree.
- Elle ne prononce aucun Done, ne touche ni `catalogue.yaml`, ni un paquet, ni du code, ni la matrice (142 / 182).
- Elle ne tranche pas `Q-U1`, ni l'accord de `nadia-prof` sur `PAR-2`, ni la forme de la correction d'`ART-CP-10`, ni le moyen d'`APM-R2`.
- Elle ne reprend ni la consignation des depassements de garde-fous, ni l'ecriture des arrets d'agent (`DEC-0116` A.36), ni `A3` de la retro de s56 (lire la CI sur un commit nomme).
- Elle ne dit pas que la CI distante est verte : personne ne l'a lue.
- **Jobs de `ci.yml` lances pour ce commit** : `prefixes` (`.github/scripts/verifier-prefixes.sh` sur le sujet du commit) et `chemins` (`.github/scripts/verifier-chemins.sh` sur les fichiers suivis), resultats dans le message de livraison. `outils` et `build` ne sont pas lances : ce commit ne modifie ni code, ni contenu, ni outil ; je ne deduis pas leur resultat.

## Decisions prises

1. L'ordre des matieres de `DEC-0115` section 3 est repris pour s57 sous un motif de forme 1 par rang, remesure (section 1). Rang 1 : dix lots de francais CE1 ; rang 2 : epuise ; rang 3 : vide a s57, motif remplace ; rang 4 : aucune ligne fermable aujourd'hui, `MAT-CE1-14` conditionnee a `A3`.
2. `DEC-0116` passe a `Acceptee` (A.37) : `DEC-0114` 0.4 (b) est satisfaite par la section 1.
3. `S54-OBS-SCI-CONSIGNE` est retiree, sans suite engagee, reversible (4.1) ; deux reports comptes, pas de troisieme. `Q-U2`.
4. Deux instances sous les deux lectures ; la lecture (A, A') ou (A, B) n'est pas decidee (`Q-U1`). s57 compte a 8,0.
5. Prerequis de la lecture (A, A') places avec leur etat (3) ; `S57-ADR-0065-DEUX-INSTANCES` a `theo-archi`, engagee dans cette seule lecture.
6. Reprise d'`ART-CP-10` retenue en tete des postes d'arts plastiques CP, `VSJ-CE1-R1` retenue apres elle ; `RP-ACCUEIL-147`, `APM-R2`, `HS-ACCUEIL-MESURE-DEFAUT` listes, non composes ; story `APM-R2` ecrite.
7. Statut de cette decision : `Proposee` (`Q-U1`, `Q-U2`).
