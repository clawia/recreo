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

---

## Amendement du 2026-10-13 (`camille-po`, apres la reponse a `Q-U1`)

Annotation datee. **Rien au-dessus n'est reecrit** ; les phrases remplacees sont nommees (A.2). Aucun fichier de code, de contenu, de catalogue ni de matrice n'est touche. Le sprint n'est toujours pas ouvert : ni `planning.md`, ni tarif, ni affectation.

### A.1 Les mots de l'utilisatrice, forme 2 de `DEC-0062`

Mots de la session du 2026-10-13, tels que l'agent qui m'a lance me les transmet, en les donnant pour recus directement et citables (forme 2) ; **je ne les ai pas lus de sa main** (meme reserve que 0.1) : *"on plannifie A A' et B, on adapte les instances et la duree car il faut sortir une version"*. Ils repondent a `Q-U1`.

**Ce qu'ils fondent** : s57 compte **trois instances** de `nadia-prof`. A et A' portent le francais CE1 ; B porte les arts plastiques CP et les sciences CM1 (`PAR-2`). La borne et la duree du sprint sont a **adapter** a ce compte. **Ce qu'ils ne fondent pas** : la valeur de la borne, la duree, le contenu de la version, ni l'accord de `nadia-prof` sur `PAR-2` pour le francais CE1.

`Q-U1` est **fermee**. Sa reponse est une troisieme lecture que ni (A, A') ni (A, B) ne portaient : (A, A', B).

### A.2 Ce que cet amendement remplace dans le texte ci-dessus

| Phrase ou ligne | Remplacee par |
|---|---|
| Section 2, titre et tableau (*"deux sous les deux lectures, la lecture n'est pas decidee"*) | trois instances, A, A', B (A.1) ; le calcul est en A.4 |
| Section 2 et decision 4 (*"s57 compte a 8,0"*) | la borne de s57 est **a adapter** (A.1) ; deux options chiffrees en A.4, valeur non choisie |
| Section 3.3 (*"Budget : 4,0 par instance, somme 8,0"*) | `PAR-9` s'applique a trois budgets (A.4) |
| Section 3.4 (*"`A10` ... due seulement pour une troisieme instance ... sans objet"*) | **`A10` est due** : la troisieme instance est proposee (A.5) |
| Section 3.2 et 4.4 (*"Engagee seulement dans la lecture (A, A')"*) | la lecture (A, A') est retenue : `S57-ADR-0065-DEUX-INSTANCES` est **engagee**, proprietaire `theo-archi`, hors `PAR`, 0,0 pour `nadia-prof` ; l'heure de fusion est a `sofia-sm` |
| Section 4.1 et decision 3 (*"retiree, sans suite engagee"*) | retiree **tant que** la condition de A.3 point 1 n'est pas remplie ; candidat de B **si** elle l'est |
| Section 4.2 (reprise d'`ART-CP-10`, instance non nommee) | portee par **B** (A.3 point 2), sous l'affectation de `nadia-prof` |
| Section 5, `Q-U1` | fermee (A.1) ; `Q-U8` ouverte (A.6) |

### A.3 Ce qui decoule de B

1. **`S54-OBS-SCI-CONSIGNE` : candidat de B, a la place de sa retraite, a une seule condition : `nadia-prof` ecrit ses criteres.** B porte les sciences CM1 (`PAR-2`) : l'instance qui l'auditerait existe. Mais le placement exige trois elements (`DEC-0114` section 4, `DEC-0115` section 4) : criteres de `nadia-prof`, proprietaire, prix. **Aucun n'existe** (4.1) et je n'ecris pas ses criteres a sa place. Sans ses criteres avant l'ouverture du planning, la retraite de 4.1 tient et le troisieme report reste interdit (`DEC-0112` section 6). Le compte reste : deux reports (s55, s56). `Q-U2` reste ouverte et devient sans objet si elle ecrit les criteres.
2. **Reprise d'`ART-CP-10`** (`S56-ART-CP-10-REPRISE`, `S56-RDO-CP-R1`, `S56-RDO-CP-R2`, 0,5) : **retenue pour B**, les arts plastiques CP etant sa matiere et B ayant audite ce paquet a s56. L'affectation et le prix sont a `nadia-prof`. Les conditions de 4.2 (developpeur et forme de la correction, rejeu d'un paquet sans entree de catalogue) sont inchangees.
3. **`VSJ-CE1-R1`** (0,5) : reste a l'instance qui porte le groupe X, donc A (`PAR-2` : une seule instance lit le francais CE1 pour une reserve de `FRA-CE1-13` ; 4.3 inchange). Elle consomme 0,5 de A.
4. **Ordre des matieres : inchange** (section 1). Les quatre rangs, leurs motifs et leurs mesures tiennent. B n'a aucun lot dans l'ordre : rang 2 epuise (EMC CM1 11 sur 11, sciences CM1 19 sur 19), rang 3 vide a s57, rang 4 sans ligne fermable. **Ce que B porte aujourd'hui, mesure : 0,5 chiffre** (la reprise d'`ART-CP-10`) **plus un candidat sans prix** (`S54-OBS-SCI-CONSIGNE`, point 1). Aucune commande nouvelle : section 1 et 0.3, meme jour, meme arbre.

### A.4 Le budget a trois instances : arithmetique, deux options, aucune valeur choisie

**Ce que je ne decide pas.** La borne est `PAR-8`, un arbitrage de `nadia-prof`. La duree du sprint releve de `sofia-sm`. La repartition entre instances est a `nadia-prof` (`PAR-9` dit seulement que la somme des budgets par instance egale la borne).

**Hypotheses communes aux deux options** (celles de la section 2) : zero refus au-dela de la marge de rejeu ; un lot de francais CE1 coute 1,0 ; chaque instance porte dans son budget un arbitrage prealable (0,5) et une marge de rejeu (0,5), soit 1,0 fixe par instance (`DEC-0116` A.23 point 3) ; au plus trois lots par instance (`PAR-8`) ; reprise d'`ART-CP-10` (0,5) chez B, `VSJ-CE1-R1` (0,5) chez A. Un amendement de `PAR-8` ou `PAR-9` par `nadia-prof` prime sur ces chiffres.

**Etat de reference, deux instances (A, A') a 8,0** (section 2) : 6,0 de travail, dont 1,0 pour les deux reprises, **cinq lots**, les dix lots du rang 1 finis a **s58**.

| | Option O1 : borne 8,0 maintenue | Option O2 : borne relevee |
|---|---|---|
| Formule | somme des trois budgets = 8,0 : 8,0 / 3 = **2,67** par instance (au quart pres, par exemple 2,75 + 2,75 + 2,50 : repartition a `nadia-prof`) | somme des trois budgets = borne ; borne = 3 x budget par instance, au plus 3 x 5,0 = 15,0 (plafond dur de `PAR-8`) |
| Fixe (arbitrage + rejeu) | 1,0 x 3 = 3,0 | 1,0 x 3 = 3,0 |
| Travail total | **5,0** (contre 6,0 a deux instances) | borne - 3,0 |
| Travail par instance | environ 1,67 | budget par instance - 1,0 |
| A (0,5 de `VSJ-CE1-R1`) et A' | A : 1,67 - 0,5 = 1,17 ; A' : 1,67. **Un lot chacun, deux lots** ; un deuxieme lot ne tient dans aucune des deux | exemple a 4,0 par instance (borne 12,0) : A 3,0 - 0,5 = 2,5, deux lots ; A' 3,0, trois lots ; **cinq lots** |
| B (0,5 de reprise d'`ART-CP-10`) | 1,67 - 0,5 = **1,17 sans poste nomme** | exemple a 4,0 : 3,0 - 0,5 = **2,5 sans poste nomme** |
| Les 10 lots du rang 1 | 2 lots a s57, 8 restants : **s59 au plus tot**, si s58 revient a deux instances (6 lots au plus par sprint a 8,0) | 5 lots a s57 : **s58**, comme l'etat de reference |
| Effet de la troisieme instance sur le calendrier du francais CE1 | **un sprint de retard** sur l'etat de reference (s59 contre s58) | **aucun** : meme calendrier qu'a deux instances, pour 4,0 de budget de plus (B), dont 2,5 sans poste nomme |

**Le chiffre 12,0 de l'exemple d'O2 n'est pas une proposition.** C'est ce que rend la formule de `nadia-prof` de s32 (3 x 5,0 - 3 paires x 1,0 = 12,0, soit 4,0 par instance, `ARBITRAGE-PREALABLE-s32...` section 4.2). Elle ecrit elle-meme (`ARBITRAGE-PREALABLE-s46...` section 5) que le chiffre d'une derivation future est *"recalcule sur les mesures de ce jour-la, sans reprendre 12,0 d'avance"* et que la fenetre est *"bornee au nombre de sprints que dure l'excedent du vivier"*.

**Ce que le tableau dit sans conclure.** A 8,0 (O1), trois budgets de 2,67 laissent 5,0 de travail contre 6,0 a deux budgets de 4,0, et 1,17 de ce travail est chez B, hors de portee des lots du francais CE1 : la troisieme instance retarde le francais CE1 d'un sprint. Relevee (O2), elle ne le retarde pas et ne l'accelere pas, et le surplus de B n'a pas de poste nomme aujourd'hui (A.3 point 4). **Ce que cet ecart vaut depend du contenu de la version** (`Q-U8`) : je ne le lis pas a sa place.

### A.5 `A10` : ce qu'elle exige maintenant, et qui l'ecrit

**Ce que dit le depot.** `DEC-0116` section 6, ligne `A10` : verification de s54 (trois instances) contre les formes (a), (b), (c), *"due avant toute `DEC` qui propose une troisieme instance"*, proprietaire `nadia-prof`. Routee a elle par la retro de s55 (`A10`, *"note dans son arbitrage de s56"*), non engagee a s56 (`retro.md` de s56, tableau des actions de s55, `A10`), jamais faite : `ARBITRAGE-COMPLEMENT-s55-instance-a.md` section 2.5 ecrit que cette verification *"n'est routee a personne aujourd'hui, et elle n'est pas faite"*. **Cet amendement est la `DEC` qui propose une troisieme instance** (A.1) : `A10` est due et **n'est pas ecrite**. Les mots de l'utilisatrice sont l'instruction de composer trois instances ; ils ne remplacent pas la verification (meme raisonnement que 0.1 pour `PAR-2`).

**Collision d'identifiant.** `retro.md` de s56 porte deux lignes `A10` : celle des actions de s55 (la verification) et celle des actions de s56 (*"Ecrire dans le depot les deux arrets d'agent et le watchdog"*, `sofia-sm` et utilisatrice). Ce sont deux actions. Ici, `A10` designe la verification des formes ; l'ecriture des arrets reste `DEC-0116` A.36 point 3.

**Ce qu'`A10` demande, dans les mots des documents de `nadia-prof`** (`ARBITRAGE-PREALABLE-s46...` section 5 ; `ARBITRAGE-PREALABLE-s52-instances-ce1.md` sections 2 et 6) :

1. **La verification du dernier sprint a trois instances (s54) contre les formes (a), (b), (c)**, transcriptions comprises, par elle : *"Une seule occurrence de l'une des trois formes suffit."* La condition (ii) se lit sur *"le dernier sprint parallele clos"* ; s55 et s56 sont des sprints paralleles clos apres s54, a deux instances. **Si la verification porte sur s54 seul ou sur s54 a s56 : a elle.** Je ne l'ecris pas.
2. **Les conditions (i) et (iii), pour toute derivation d'une borne a trois instances (O2).** (iii) : au plus une instance sur un terrain dont ses conditions n'ont jamais rencontre de contenu reel ; A et A' sont sur le francais CE1, B sur deux matieres deja servies : lecture a elle. (i) : *"une composition a deux instances qui remplit 8,0 et laisse dehors au moins 4,0 de lots instruits"* que la troisieme instance peut prendre sous `PAR-1` et `PAR-2`, **mesure que sa section 5 place chez `camille-po`**. Ma mesure, sans commande nouvelle (A.3 point 4) : ce que B peut prendre est **0,5 chiffre** (la reprise d'`ART-CP-10`) plus un candidat sans prix ; le francais CE1 n'est pas prenable par une troisieme instance (`PAR-2`) ; **0,5 contre 4,0**. Ce que cette mesure vaut pour sa derivation est a elle. Si O1 (8,0) est retenue, savoir si la derivation est requise est aussi a elle.
3. **Son accord sur `PAR-2` pour le francais CE1** (3.1), place avant le premier worktree d'un lot de A'. Il n'est pas `A10`, mais il est dans le meme arbitrage.

| Qui | Ce qu'il ecrit | Ou |
|---|---|---|
| `nadia-prof` | La verification (point 1) ; la lecture des conditions (i) et (iii) et, si elle derive, la borne (`PAR-8`) ; l'accord ou le refus sur `PAR-2` (3.1) ; l'affectation et le prix de B ; les criteres de `S54-OBS-SCI-CONSIGNE` si elle veut la placer | son arbitrage prealable de s57 |
| `sofia-sm` | La duree et le planning de s57 a trois instances ; l'heure de fusion de l'amendement d'`ADR-0065` ; les chiffres de sprint que la forme (b) peut lui demander (`review.md`, `RAPPORT.md` de s54 a s56) | `planning.md` de s57 |
| `theo-archi` | `S57-ADR-0065-DEUX-INSTANCES` ; pour la forme (c), l'isolation des worktrees, des ports et des fichiers partages de trois couloirs, **si `nadia-prof` le lui demande** (precedent : l'isolation de l'instance C de s54, `A6`, *"faisable, et tenu"*) | `ADR-0065` ; avis de faisabilite de s57 |
| `camille-po` | La mesure de la condition (i) (ci-dessus) ; l'ordre ; `Q-U8` | cet amendement |

**Ce que je ne peux pas faire tenir seule.** Le planning de s57 ne s'ecrit pas sur trois budgets avant `A10` et l'accord sur `PAR-2` ; la borne et la duree ne s'ecrivent pas avant la lecture de `nadia-prof` et la reponse a `Q-U8`.

### A.6 `Q-U8` : la version

Mots de l'utilisatrice : *"il faut sortir une version"*. Le depot ne dit pas ce qu'est cette version : `git tag` ne rend que `s54-avant-reecriture-trailers`, et aucun fichier de `squad/` ni de `docs/backlog/` consulte ne definit une version a sortir (recherche de "sortir une version", "release", "sortie de version" dans les `DEC-0114` a `DEC-0120`, `squad/README.md` et le backlog : rien). Je ne presume rien.

| Code | Question |
|---|---|
| `Q-U8` | **Quel contenu doit sortir dans cette version, et pour quelle date ?** (a) Le contenu : quelles competences ou quels jeux, ou l'etat du produit a la cloture de s57, quel qu'il soit ? (b) La date : un jour precis, ou la fin de s57 ? (c) Si le contenu est nomme : doit-il etre `couvert-valide` (Done de `nadia-prof`) a la date, ou sorti tel quel ? Sa reponse gouverne le choix entre O1 et O2 (A.4) et la duree |

### A.7 Statuts et routage

- **`DEC-0120` reste `Proposee`.** Elle devait passer a `Acceptee` quand `Q-U1` aurait une reponse et que la retraite de `S54-OBS-SCI-CONSIGNE` ne serait pas contredite. `Q-U1` a sa reponse, mais l'amendement ajoute une troisieme instance dont `A10` n'est pas ecrite, une condition sur la retraite (A.3 point 1), et laisse `Q-U2` et `Q-U8` ouvertes. Elle passe a `Acceptee` quand `A10` est ecrite, que `Q-U2` et `Q-U8` ont leur reponse, et que la borne et la duree sont ecrites.
- **`DEC-0116` : `Acceptee`, inchange.** Sa section 6, ligne `A10`, est la reference d'A.5.
- **Statuts de stories** : `S57-ADR-0065-DEUX-INSTANCES` passe de non engagee a **engagee** (A.2). `S54-OBS-SCI-CONSIGNE` passe de *retiree* a *retiree, candidat de B sous condition* (A.3 point 1). Aucune autre story ne change. **Aucune ligne de matrice ne change** : 142 / 182.
- **Routage** : `nadia-prof`, `sofia-sm`, `theo-archi` : tableau d'A.5. Utilisatrice : `Q-U2`, `Q-U8`. Les postes de la section 6 gardent leur story ou leur proprietaire ; aucun n'est cree ici.

### A.8 Ce que cet amendement ne fait pas

- Il n'ouvre pas s57, ne choisit ni O1 ni O2, ne donne aucune duree et ne fixe aucune borne.
- Il n'ecrit ni `A10`, ni l'accord sur `PAR-2`, ni les criteres de `S54-OBS-SCI-CONSIGNE`, ni l'avis de `theo-archi`.
- Il ne touche ni code, ni catalogue, ni paquet, ni matrice, ni fiche. Il ne dit pas que la CI distante est verte.
- **Jobs de `ci.yml` lances pour son commit** : `prefixes` et `chemins` (resultats dans le message de livraison). `outils` et `build` ne sont pas lances : le commit ne modifie ni code, ni contenu, ni outil.

---

## Amendement du 2026-10-13, second (`camille-po`, apres la reponse partielle a `Q-U8`)

Annotation datee. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace est nomme en A.10. Le sprint n'est toujours pas ouvert : ni `planning.md`, ni tarif, ni borne, ni duree, ni affectation. **Aucune ligne de matrice, aucun catalogue, aucun paquet, aucun code n'est touche** : 142 / 182 (78,02 %).

### A.9 Les mots de l'utilisatrice, forme 2 de `DEC-0062`

Mots de la session du 2026-10-13, tels que l'agent qui m'a lance me les transmet, en les donnant pour recus directement et citables (forme 2) ; **je ne les ai pas lus de sa main** (meme reserve que 0.1 et A.1) : *"idealement CP-CE1-CM1 avec un maximum de couverture"*. Ils repondent a `Q-U8`. Ils ne donnent ni date ni statut de ligne exige : je n'en invente aucun.

**Lecture, qui est la mienne.** La version couvre les trois niveaux CP, CE1 et CM1, avec le maximum de couverture de `MATRICE-couverture.md`. *"Idealement"* n'est pas une exigence ferme : ce que la version sort si un niveau n'atteint pas ce maximum a la date n'est pas dit ; je ne le decide pas et le laisse a `Q-U8` (b) plutot que de le presumer.

### A.10 Ce que cet amendement ferme et ce qu'il laisse ouvert

| Point de `Q-U8` (A.6) | Etat | Remplace |
|---|---|---|
| (a) le contenu | **fermee** : trois niveaux, couverture maximale de la matrice (A.9) | la phrase *"quelles competences ou quels jeux, ou l'etat du produit a la cloture de s57"* |
| (b) la date | **ouverte**, question precise ci-dessous | - |
| (c) le statut exige des lignes | **ouverte**, question precise ci-dessous | - |
| (d) le perimetre du CE1 | **nouvelle**, decoulee de la mesure A.11 | - |

| Code | Question precise pour l'utilisatrice |
|---|---|
| `Q-U8` (b) | **Quelle date, ou quel nombre de sprints ?** Un jour precis, la fin d'un sprint nomme, ou *"quand le maximum est atteint"* ? A.15 donne, pour chaque cible, le nombre de sprints sous O1 et sous O2 : de 2 a 5 sprints pour 153 lignes sur 182, de 3 a 8 pour 163 |
| `Q-U8` (c) | **La version sort-elle uniquement des lignes `couvert-valide` (Done de `nadia-prof`), ou aussi des lignes `en cours` et des jeux branches sans Done a la date ?** Sans reponse, seul `couvert-valide` compte (definition du statut dans la matrice) |
| `Q-U8` (d) | **Le maximum du CE1 se mesure-t-il sur les deux referentiels qui existent (francais et mathematiques, 49 lignes), ou faut-il des referentiels de CE1 pour les autres matieres ?** Le CE1 n'a que deux referentiels (`ls squad/programme/*/competences-ce1.md` rend 2 fichiers) contre 8 au CP et 10 au CM1 : un *"maximum de couverture"* du CE1 est aujourd'hui un maximum sur deux matieres. Ecrire un referentiel est un travail de `nadia-prof` (`DEC-0103`), pas le mien |

### A.11 Mesures lancees pour cet amendement (arbre `round-two` a `32cfb7ac`, worktree `version-camille`, 2026-10-13, perimetre : le worktree entier)

| Mesure | Commande ou fichier | Rendu |
|---|---|---|
| Matrice, total | commande de la "Methode du compte" de `MATRICE-couverture.md`, 258 lignes | `couvert-valide` 142, `en cours` 4, `non couvert` 36, `non couvrable en l'etat` 76 ; 142 / 182 = 78,02 % |
| Matrice, par niveau et par matiere | la meme commande, `$3` puis `$2"/"$3` ajoutes comme cle de comptage | tableau A.12 ; 20 couples matiere / niveau |
| Code de chaque ligne non fermee | script de poste, non commite : lit les 40 lignes `non couvert` ou `en cours` de la matrice et les rapproche du texte des referentiels `squad/programme/*/competences-*.md` | 40 lignes, un code chacune ; `ART-CM1-07` non rapprochee par le script, trouvee par `grep` sur le referentiel |
| Referentiels par niveau | `ls squad/programme/*/competences-<niveau>.md \| wc -l` | CP 8, CE1 2, CM1 10 |
| Fichiers de contenu sans entree de catalogue | `comm -23` des fichiers de `contenu/` et des `paquet:` de `catalogue.yaml` (commande de `proposition-s56.md` section 1) | **37** sur 183 fichiers ; 146 `paquet:` au catalogue : inchange depuis 0.3 |
| Plafonds du francais CE1 | `ARBITRAGE-PREALABLE-s48-francais-ce1.md` section 12 (recapitulatif des 26 verdicts) | 24 `couvert-valide`, 2 `en cours` (`FRA-CE1-03`, `-21`) |
| Plafonds de `G7` a `G11` | `ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md` section 6 | 15 paquets sur 16 plafonnes a `non couvert` ; `cm1-lire-et-restituer` : `couvert-valide`, apres une mesure de pli non faite |
| Plafonds de mathematiques CE1 | `ARBITRAGE-PREALABLE-s52-instances-ce1.md` section 4.2, tableau des huit lignes | `MAT-CE1-01`, `-10`, `-11`, `-15` : `en cours` au plus ; `-14` : `couvert-valide` sur deux moities dont une designation |
| `LVE-CM1-06` | `ARBITRAGE-PREALABLE-s45-cinq-lots.md`, `Q5` (section 5) | *"refusee a la saisie"* au motif de `R6`, depuis 2026-10-01 |
| Corpus d'oeuvres | `grep -c "identifiant: oeuvre-" squad/corpus/oeuvres-domaine-public.yaml` | 12 |
| `non couvrable en l'etat` : nature | `awk` sur la septieme cellule des 76 lignes, mots *"nature irreversible"* et *"reversible"* | irreversible 54, reversible 13, sans mention 9 |
| Capacite mesuree, francais CE1 | notes de cloture de `MATRICE-couverture.md` pour s54, s55, s56 ; `cloture-nadia.md` de s56 section 6 ; `proposition-s56.md` section 2 | lignes de francais CE1 fermees par l'instance A : **2** a s54 (`FRA-CE1-18`, `-19`), **2** a s55 (`-14`, `-12`), **1** a s56 (`-13`) ; moyenne 5 / 3 = **1,67** par sprint et par instance, contre 3 en arithmetique de `PAR-8` |

Ce que je n'ai pas lu ni lance : aucun avis de `theo-archi`, aucun arbitrage prealable de `nadia-prof` pour s57 (ils n'existent pas) ; aucun jar, aucun test de contenu ; aucun prix de `nadia-prof` pour les lignes du rang B autres que `QLM-CP-10`.

### A.12 Distance au maximum, par niveau

**Denominateur.** Les 258 lignes se partagent en 182 atteignables et 76 `non couvrable en l'etat` (definition du statut dans la matrice : retirees du denominateur, sans l'etre du referentiel). 142 / 182 = 78,02 % ; **142 / 258 = 55,04 %** si l'on compte les 76.

| Niveau | Lignes | `couvert-valide` | `en cours` | `non couvert` | `non couvrable` | Atteignables | Taux |
|---|---|---|---|---|---|---|---|
| CP | 92 | 51 | 2 | 6 | 33 | 59 | 51 / 59 = 86,44 % |
| CE1 | 49 | 25 | 2 | 17 | 5 | 44 | 25 / 44 = 56,82 % |
| CM1 | 117 | 66 | 0 | 13 | 38 | 79 | 66 / 79 = 83,54 % |
| **Total** | **258** | **142** | **4** | **36** | **76** | **182** | **78,02 %** |

Le CE1 est le niveau le plus loin du maximum : 19 lignes non fermees sur 44, dont les 10 du francais en rang 1.

### A.13 Les 40 lignes non fermees : fermable, bloquee, hors perimetre

Definitions. **Rang A (fermable, paquet ecrit)** : un paquet existe, son plafond ecrit permet `couvert-valide`, il reste un lot d'audit de `nadia-prof`. **Rang B (fermable par ecriture)** : le depot n'ecrit ni plafond, ni refus, ni decision, ni mecanique manquante qui l'interdise ; aucun paquet jugeable n'existe (absent, ou plafonne a `non couvert` faute d'image) ; **que l'ecriture reussisse est un jugement de `nadia-prof`, pas le mien**. **Rang C (bloquee)** : le depot ecrit ce qui manque, et ce n'est ni un lot ni une ecriture. **Hors perimetre produit** : les 76 `non couvrable en l'etat`, hors de ce tableau (A.12 les compte, A.16 les nomme par matiere).

| Rang | Lignes | Niveau | Ce que le depot dit | Source |
|---|---|---|---|---|
| A | `FRA-CE1-04`, `-05`, `-06`, `-07`, `-08`, `-09`, `-10`, `-11`, `-15`, `-24` (10) | CE1 | dix paquets ecrits, sans entree de catalogue : `ce1-texte-narratif`, `-pronoms`, `-inference`, `-texte-documentaire`, `-ordre-des-mots`, `-relire-une-phrase`, `-phrases-types`, `-negation`, `-accord-groupe-nominal`, `-familles-de-mots` ; plafond `couvert-valide` ; `-15` avant `-09` | `comm -23` ; s48 section 12 ; section 1 |
| A | `ART-CP-10` (1) | CP | paquet refuse (656 px pour un pli a 551), retire du catalogue ; correctif de 105 px et de deux textes, rejeu de 0,5 | `cloture-nadia.md` s56 ; 4.2 |
| B | `QLM-CP-10` | CP | palier 1 illustre existe (`ADR-0073`), cout de 2,0 ; le fichier `cp-qlm-vocabulaire-de-position` est plafonne a `non couvert` (sans image) | s55 A section 6 ; cellule de matrice (`DEC-0099` section 4 ter) |
| B | `QLM-CP-16` | CP | il manque un paquet ou *comparer* est la tache de la plupart des items, sous `BIA-1`, `BIA-2` ; le fichier ecrit ne compare que sur 1 item de 12 | s55 A section 6 |
| B | `ART-CM1-07` | CM1 | palier 1 illustre, image d'oeuvre requise ; droits tranches (`DEC-0077`) ; aucun paquet | cellule de matrice |
| B | `ART-CM1-08` | CM1 | paquet ecrit plafonne a `non couvert` : il faut une reproduction par item | s55 A section 6 |
| B | `HDA-CM1-01`, `-02`, `-03`, `-04` | CM1 | quatre paquets ecrits plafonnes a `non couvert` : il faut une reproduction par item | s55 A section 6 |
| B | `HDA-CM1-06` | CM1 | idem ; mention exigee : *"identification et ordre entre deux oeuvres seuls ; le placement sur une frise reste non couvert"* | s55 A section 6 ; cellule |
| B | `LVE-CM1-05` | CM1 | condition de `nadia-prof` : un second paquet lexical valide avant que la ligne s'ouvre (deux lots, pas un) | s55 A section 6 |
| C, plafond | `MAT-CE1-01`, `-10`, `-11`, `-15` ; `FRA-CE1-03`, `-21` (6) | CE1 | plafond ecrit `en cours` : un lot fait passer la ligne a `en cours`, jamais a `couvert-valide`. `-21` : releve seulement par une decision de l'utilisatrice (dictee ou saisie alphabetique au CE1) ; `-03` : rien de connu | s52 4.2 ; complement s55 section 3 |
| C, mecanique ou materiau | `MAT-CP-01` | CP | *denombrer* ne se sert pas par une mecanique textuelle (constat de `theo-archi`) | cellule ; `ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md` section 1 |
| C, mecanique ou materiau | `MAT-CE1-09` | CE1 | aucune image de regle, aucune lecture de graduation | cellule |
| C, mecanique ou materiau | `MAT-CE1-02` | CE1 | `couvert-valide` seulement avec une ligne graduee dessinee (`ORD-CE1-6`) ; moitie designation non engagee, deux reports | cellule ; `DEC-0111` section 9 |
| C, mecanique ou materiau | `MAT-CE1-14`, `MAT-CP-10` | CE1, CP | designation sur quadrillage : `A3` d'`ADR-0080` non ecrit. Le lien `A3` / `MAT-CE1-14` est ecrit (`DEC-0112` 5.3) ; **pour `MAT-CP-10` il ne l'est pas, c'est la meme moitie (quadrillage) : a confirmer par `theo-archi`** | `ADR-0080` ; cellule de `MAT-CP-10` |
| C, mecanique ou materiau | `QLM-CP-12`, `HG-CM1-09`, `HG-CM1-10` | CP, CM1 | aucun dessin de carte, de globe ni de planisphere (palier 2 existe, trois entrees au catalogue) ; `HG-CM1-10` : le lieu de vie de l'enfant n'est pas connu du produit (`DEC-0028`) | cellules (commandes `grep -c` et `find` de la cellule) |
| C, mecanique ou materiau | `HDA-CM1-09` | CM1 | un plan de musee en designation, absent | s55 A section 6 |
| C, decision | `FRA-CM1-01` | CM1 | paquet ecrit, plafond `couvert-valide`, apres une mesure de pli de `LIS-2` que l'utilisatrice a ecartee (*"D4 non"*) | `DEC-0116` A.21, A.24 |
| C, refus ecrit | `EMC-CP-04` | CP | refusee d'avance (`EMC-4` : une seule tache possible, l'interdit) | `DEC-0081` section 7 |
| C, refus ecrit | `LVE-CM1-06` | CM1 | refusee a la saisie (`R6`) depuis le 2026-10-01. **Ecart** : la cellule de matrice dit encore *"jouable par saisie, sous `ANG-S1` et `ANG-S2`"* ; non corrige ici (matrice) | `ARBITRAGE-PREALABLE-s45-cinq-lots.md` `Q5` ; cellule |
| C, aucune forme | `FRA-CP-05` | CP | *"une forme ou la proposition ne reproduit pas les lettres du stimulus ; je n'en connais pas sans image ni son"* | s55 A section 6 |

**Compte** : rang A 11, rang B 10, rang C 19 ; 11 + 10 + 19 = 40 = 4 `en cours` + 36 `non couvert`. Les quatre `en cours` (`MAT-CP-01`, `MAT-CP-10`, `MAT-CE1-02`, `MAT-CE1-09`) sont toutes en rang C.

**Les lignes les plus proches du rang B** : quatre lignes de rang C n'ont pour seul manque qu'un dessin (`QLM-CP-12`, `HG-CM1-09`, `HG-CM1-10`, `HDA-CM1-09`). L'avis de faisabilite de `theo-archi` est a obtenir avant tout chiffrage (`DEC-0099` section 4 ter) ; je ne le presume pas.

### A.14 Le maximum atteignable

**Definition.** Le maximum atteignable est le nombre de lignes `couvert-valide` si tous les lots du rang A et du rang B ferment, **sous les plafonds, refus et decisions tels que le depot les ecrit aujourd'hui**. Ce n'est pas une promesse : le rang B depend du jugement de `nadia-prof`, et chaque lot peut etre refuse.

| Cible | Lignes `couvert-valide` | Sur 182 | Sur 258 |
|---|---|---|---|
| Aujourd'hui | 142 | 78,02 % | 55,04 % |
| Rang A ferme (+ 11) | **153** | 84,07 % | 59,30 % |
| **Maximum : rangs A et B (+ 21)** | **163** | **89,56 %** | 63,18 % |
| Si l'utilisatrice leve `D4` (+ `FRA-CM1-01`) | 164 | 90,11 % | 63,57 % |

| Niveau | Aujourd'hui | Rang A ferme | Maximum (A + B) | Taux au maximum |
|---|---|---|---|---|
| CP | 51 | 52 | 54 | 54 / 59 = 91,53 % |
| CE1 | 25 | 35 | 35 | 35 / 44 = 79,55 % |
| CM1 | 66 | 66 | 74 | 74 / 79 = 93,67 % |

**Lecture.** Le CE1 plafonne a 79,55 % : ses 9 lignes restantes (`MAT-CE1-01`, `-02`, `-09`, `-10`, `-11`, `-14`, `-15`, `FRA-CE1-03`, `-21`) sont toutes en rang C. Le rang B profite au CM1 (8 lignes) et au CP (2). Un maximum de 163 laisse 19 lignes atteignables non fermees et 76 hors perimetre.

### A.15 Projection : ce que l'arithmetique permet, ce que la capacite mesuree a donne

**Hypotheses, ecrites pour pouvoir etre contestees.** Celles de A.4 (un lot de francais CE1 = 1,0 ; arbitrage 0,5 et rejeu 0,5 par instance ; au plus 3 lots et 5,0 par instance, `PAR-8` ; une seule instance par matiere, `PAR-2` ; reprise d'`ART-CP-10` 0,5 chez B ; `VSJ-CE1-R1` 0,5 chez A) et : **un lot du rang B coute 2,0**, seul prix ecrit (`QLM-CP-10`, `DEC-0099` section 4 ter) ; **2,5** si le prix est celui de l'audit du paquet d'`ART-CP-10` (`cloture-nadia.md` de s56, section 6). Aucun autre prix du rang B n'est ecrit. La borne est a `nadia-prof` (`PAR-8`), la duree a `sofia-sm` : ces chiffres sont des planchers de calcul, pas des propositions.

**Arithmetique, rang A (153).** Travail : 10 lots + 0,5 (reprise) + 0,5 (`VSJ-CE1-R1`) = 11,0.

| | O1 : 8,0, trois instances a s57 | O2 : borne relevee |
|---|---|---|
| s57 | 2 lots de francais CE1 (A.4) | 5 lots si A a 3,5 et A' a 4,0 de budget : **plancher de 9,0** avec B a 1,5 ; 12,0 (exemple de A.4) y suffit |
| Fin du rang A | **s59** si s58 revient a deux instances a 8,0 (6 lots) ; **s61** si les trois instances durent (2 lots par sprint) | **s58** : deux sprints est le minimum, car deux instances de francais CE1 prennent 6 lots au plus par sprint |
| Sprints | 3 a 5 | 2 |

**Mesure, rang A.** A.11 : 2, 2, 1 lots de francais CE1 par sprint pour une instance (s54, s55, s56), soit 1,67. Les reserves de lots anterieurs ont pris la place des lots neufs (s55 : 1,75 de rejeux sur 3,5 consommes ; s56 : `CLM-CE1-R1` 1,0 et `ACV-CE1-R1`, `-R2` 0,5 dans les 2,5 d'A). Deux instances de francais CE1 au rythme mesure : 3,33 lots par sprint.

| | O1 | O2 |
|---|---|---|
| s57 | 2 (l'arithmetique plafonne sous le rythme mesure) | 3,33 |
| s58 | 3,33 | 3,33 |
| s59 | 3,33, cumul 8,67 | 3,33, cumul **10,0** |
| Fin du rang A au rythme moyen | **s60** | **s59**, sans marge |
| Rythme maximal observe (2 par instance) | s59 | s59 |
| Rythme minimal observe (1 par instance) | s61 | s61 |

**Ce que la mesure dit de l'arithmetique** : l'arithmetique de s58 (O2) demande 5 lots a s57, soit 2,5 par instance de francais CE1 ; le rythme mesure n'a jamais depasse 2. **Aucune des deux options ne tient le francais CE1 en deux sprints sur la capacite mesuree.** O2 le tient en trois, sans marge ; O1 en quatre.

**Arithmetique, maximum (163).** Travail : 11,0 + 10 x 2,0 = **31,0** (36,0 a 2,5 le lot). Travail par sprint : O1 a trois instances 5,0 (1,67 chacune : **aucun lot de 2,0 ne tient dans une instance**, le rang B n'avance pas) ; O1 a deux instances 6,0 ; O2 a 15,0 (3 x 5,0, plafond dur de `PAR-8`) 12,0.

| | O1 (8,0) | O2 (15,0 au plus) |
|---|---|---|
| Travail 31,0 | 6 sprints (deux instances, 31,0 / 6,0) a 7 (trois instances, 31,0 / 5,0) | **3 sprints** (31,0 / 12,0) |
| Travail 36,0 | 6 a 8 sprints | **3 sprints** (36,0 / 12,0, sans marge) |
| Chaine de `HDA-CM1` | cinq lots d'une meme matiere, une seule instance (`PAR-2`) : 5 sprints a 4,0 de budget (un lot par sprint), 3 a 5,0 (deux lots par sprint) | idem |

3 sprints est un **plancher** : il suppose que A, A' et B ne laissent aucun budget inemploye, que les matieres du rang B (arts plastiques CM1, histoire des arts CM1, questionner le monde CP, anglais CM1) tiennent sur ces trois instances, et que la borne est au plafond dur. **Je n'ai aucune mesure du rythme des lots illustres** : le depot en donne une fermeture au troisieme passage (`ART-CP-09`, s50), et une ligne engagee trois fois sans fermeture (`ART-CP-10`, 4.2). Le nombre de sprints du maximum n'est donc pas projetable sur la capacite mesuree ; l'ecrire serait inventer un taux.

**Laquelle des deux options est necessaire.** (i) **Rang A (153) en deux sprints** : O2 est necessaire (O1 donne s59 au mieux), et la capacite mesuree ne le permet sous aucune option. (ii) **Rang A en trois sprints** : O2 suffit, O1 ne suffit pas (s60 au rythme moyen). (iii) **Maximum (163) en trois sprints** : O2 au plafond dur est necessaire ; O1 est exclue, car a trois instances a 8,0 le rang B n'avance pas du tout. **Je ne choisis ni la borne (`PAR-8`, `nadia-prof`) ni la duree (`sofia-sm`)** ; ces trois lignes disent ce que chaque cible exige.

### A.16 Ce que la version sortirait non couvert

**Les 76 `non couvrable en l'etat` sortent non couvertes, quelle que soit la cible.** Par niveau et matiere : CP 33 (musique 9, arts plastiques 7, EPS 7, anglais 5, EMC 2, francais 2, questionner le monde 1) ; CE1 5 (francais) ; CM1 38 (musique 10, arts plastiques 8, EPS 8, anglais 3, histoire des arts 3, mathematiques 2, francais 2, EMC 1, sciences 1). Motifs, lus dans les cellules : 54 de nature irreversible (production libre jugee par un humain, geste moteur, groupe absent), **13 de nature reversible** (aucun son fourni, voix de l'adulte, trace graphomotrice : seule une decision de l'utilisatrice les rouvre, `CLAUDE.md`, `DEC-0046`, `DEC-0068`), 9 sans mention de nature dans la cellule.

**Si la version sort au rang A ferme (153)**, 29 lignes atteignables sortent non couvertes (4 `en cours`, 25 `non couvert`) :

- CP (7) : `MAT-CP-01` (`en cours`), `MAT-CP-10` (`en cours`), `FRA-CP-05`, `QLM-CP-10`, `QLM-CP-12`, `QLM-CP-16`, `EMC-CP-04`.
- CE1 (9) : `MAT-CE1-01`, `-02` (`en cours`), `-09` (`en cours`), `-10`, `-11`, `-14`, `-15`, `FRA-CE1-03`, `-21`.
- CM1 (13) : `FRA-CM1-01`, `HG-CM1-09`, `HG-CM1-10`, `LVE-CM1-05`, `LVE-CM1-06`, `ART-CM1-07`, `-08`, `HDA-CM1-01`, `-02`, `-03`, `-04`, `-06`, `-09`.

Compte : 7 + 9 + 13 = 29.

**Si la version sort au maximum (163)**, 19 lignes atteignables sortent non couvertes, les 19 du rang C, dont quatre `en cours` :

- CP (5) : `MAT-CP-01` (`en cours`), `MAT-CP-10` (`en cours`), `FRA-CP-05`, `QLM-CP-12`, `EMC-CP-04`.
- CE1 (9) : `MAT-CE1-01`, `-02` (`en cours`), `-09` (`en cours`), `-10`, `-11`, `-14`, `-15`, `FRA-CE1-03`, `-21`.
- CM1 (5) : `FRA-CM1-01`, `HG-CM1-09`, `HG-CM1-10`, `LVE-CM1-06`, `HDA-CM1-09`.

Compte : 5 + 9 + 5 = 19.

### A.17 Statuts, routage, ce que cet amendement ne fait pas

- **`DEC-0120` reste `Proposee`.** Elle passe a `Acceptee` quand `A10` est ecrite, que `Q-U2` et `Q-U8` (b), (c), (d) ont leur reponse, et que la borne et la duree sont ecrites (A.7, complete).
- **Routage.** `nadia-prof` : jugement du rang B (A.13), prix de ses lots, lecture d'A.15 pour `PAR-8`, `A10`, referentiels de CE1 si l'utilisatrice repond oui a `Q-U8` (d). `sofia-sm` : la duree a la lumiere d'A.15, le planning. `theo-archi` : avis de faisabilite des quatre dessins (A.13) ; lien de `A3` avec `MAT-CP-10`. Utilisatrice : `Q-U2`, `Q-U8` (b), (c), (d).
- **Ecart signale, non corrige** : la cellule de matrice de `LVE-CM1-06` contredit le refus de saisie de `nadia-prof` (A.13). Le corriger est un geste de matrice, a faire avec le build complet ; il n'est pas fait ici.
- **Ne fait pas** : n'ouvre pas s57 ; ne choisit ni O1 ni O2, ne fixe ni borne ni duree ; ne prononce aucun Done et ne juge aucun contenu (le rang B est une lecture des plafonds ecrits, pas un avis pedagogique) ; ne touche ni code, ni catalogue, ni paquet, ni fiche, ni matrice ; ne dit pas que la CI distante est verte.
- **Jobs lances pour son commit** : `prefixes` (`.github/scripts/verifier-prefixes.sh`) et `chemins` (`.github/scripts/verifier-chemins.sh`), plus le test de registre des identifiants (`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, `-pl bootstrap -am`) parce que cet amendement cite des identifiants ; resultats dans le message de livraison. `outils` et `build` complet ne sont pas lances : le commit ne modifie ni code, ni contenu, ni outil, ni matrice.

---

## Amendement du 2026-10-13, troisieme (`camille-po`, apres les reponses de l'utilisatrice a `Q-U8` (b), (c) et (d))

Annotation datee. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace est nomme en A.19. Le sprint n'est toujours pas ouvert : ni `planning.md`, ni tarif, ni borne, ni duree, ni affectation. **Aucune ligne de matrice, aucun catalogue, aucun paquet, aucun code, aucune fiche n'est touche** : 142 / 182 (78,02 %).

### A.18 Les mots de l'utilisatrice, forme 2 de `DEC-0062`

Mots de la session du 2026-10-13, tels que l'agent qui m'a lance me les transmet, en les donnant pour recus directement et citables (forme 2) ; **je ne les ai pas lus de sa main** (meme reserve que 0.1, A.1 et A.9). Ils repondent aux points (b), (c) et (d) de `Q-U8` (A.10), cites tels quels :

- (b) *"pas de date"*
- (c) *"on compte toutes les lignes meme en cours si le jeux est jouable"*
- (d) *"pour le CE1 ouvre d'autres matieres"*

| Point | Ce que les mots fondent | Ce qu'ils ne fondent pas |
|---|---|---|
| (b) | Aucune date de sortie. La version sort quand le maximum est atteint selon (c) | La valeur du maximum, que (c) et (d) font dependre de documents qui n'existent pas encore ; ce qui se passe si une ligne ne ferme pas |
| (c) | Le compteur de la version inclut les lignes `en cours` dont le jeu est jouable | Qui juge *"jouable"* et sur quoi : ce sont les criteres de `nadia-prof`, ecrits en parallele, **en attente**. Je ne les invente pas |
| (d) | Le perimetre du CE1 s'ouvre a d'autres matieres | Lesquelles. Les referentiels sont de `nadia-prof` (`DEC-0103`), en parallele |

**Lecture, qui est la mienne.** *"Toutes les lignes"* s'entend dans le denominateur atteignable (182) : les 76 `non couvrable en l'etat` en sont retirees par definition (matrice, section "Comment la lire"), et les mots ne les nomment pas. Si l'utilisatrice les contredit, A.21 et A.23 sont amendes.

### A.19 Ce que cet amendement ferme, ouvre et remplace

| Point | Etat | Remplace |
|---|---|---|
| `Q-U8` (b) la date | **fermee** : pas de date | la question *"quelle date, ou quel nombre de sprints ?"* (A.10) |
| `Q-U8` (c) le statut exige | **fermee dans son principe** : `couvert-valide` et `en cours` jouables comptent. **Formule incomplete** : criteres de `nadia-prof`, en attente (A.21) | la phrase *"sans reponse, seul `couvert-valide` compte"* (A.10) |
| `Q-U8` (d) le perimetre du CE1 | **fermee dans son principe** : le CE1 s'ouvre. Matieres et referentiels : `nadia-prof`, en attente (A.24) | la question sur les deux referentiels (A.10) |
| `Q-U9` | **nouvelle** (ci-dessous) | - |
| `Q-U2` | **ouverte**, `nadia-prof` interrogee, en attente (A.25) | - |

Lectures d'A.15 qui ne tiennent plus : (iii) *"O1 est exclue"* lisait la cible *"163 en 3 sprints"* ; sans date, cette cible n'existe plus (A.23). Les nombres de sprints d'A.15 restent des previsions de sortie, plus des contraintes. Le maximum de 163 (A.14) n'est plus la cible de la version (A.23).

| Code | Question precise pour l'utilisatrice |
|---|---|
| `Q-U9` | **Le maximum de la version se mesure-t-il sur la matrice telle qu'elle sera quand les referentiels de CE1 seront ecrits, puis fige, ou sur la matrice vivante ?** Sans date (b) et avec un perimetre qui s'ouvre (d), chaque referentiel neuf ajoute des lignes au denominateur (A.24) : sur la matrice vivante, la cible bouge tant qu'un referentiel arrive. Je ne choisis pas |

### A.20 Mesures lancees pour cet amendement (arbre `round-two` a `59c4b56f`, worktree `decisions-camille`, 2026-10-13, perimetre : le worktree entier)

| Mesure | Commande ou fichier | Rendu |
|---|---|---|
| Matrice, total | commande de la "Methode du compte" de `MATRICE-couverture.md`, 258 lignes de donnees | `couvert-valide` 142, `en cours` 4, `non couvert` 36, `non couvrable en l'etat` 76 ; 142 / 182 = 78,02 % |
| Les lignes `en cours` | la meme commande, filtre `s ~ /^en cours/`, sorties : matiere, niveau, competence, jeu | 4 lignes : `MAT-CP-01` (`bandit-manchot-nombres-jusqu-a-100-cp`), `MAT-CP-10` (`bandit-manchot-deplacements-quadrillage-cp`), `MAT-CE1-02` (`bandit-manchot-comparer-ranger-ce1`), `MAT-CE1-09` (`bandit-manchot-longueurs-ce1`). Codes repris d'A.13 ; leurs cellules ecrivent un verdict `VALIDE` ou `VALIDE SOUS RESERVE` de `nadia-prof` sur la part servie, sans passer a `couvert-valide` |
| Jeux au catalogue | `grep -n` des quatre identifiants de jeu sur `catalogue.yaml` | 4 lignes `- id:` (495, 501, 591, 831) : chacun des quatre jeux a une entree. **C'est un fait de catalogue, pas un jugement de jouabilite** |
| Referentiels par niveau | `ls squad/programme/*/competences-<niveau>.md \| wc -l` | CP 8, CE1 2, CM1 10 : inchange depuis A.11 |
| Criteres de jouabilite de `nadia-prof` | sur `59c4b56f` : `git ls-tree -r --name-only HEAD \| grep -i jouabilit` ; `git grep -l -i jouabilit HEAD -- squad docs CLAUDE.md` | 0 nom de fichier ; 9 fichiers citent le mot (autres decisions et sprints, **non lus comme criteres de version : aucun n'est le document attendu**). Le document de `nadia-prof` n'est pas dans l'arbre `round-two` : je ne le cite pas et n'en reprends aucun terme |

Ce que je n'ai pas mesure : le nombre de lignes `en cours` dont le jeu est jouable, faute de criteres (A.22).

### A.21 Le compteur de la version : formule, incomplete

**Compteur de version = lignes `couvert-valide` + lignes `en cours` dont le jeu est jouable selon les criteres de `nadia-prof`, en attente.** Denominateur : les lignes atteignables (182 aujourd'hui), non change par (c).

- **Le statut de matrice ne change pas.** (c) definit un compteur de version, pas un statut : une ligne `en cours` reste `en cours`, et `couvert-valide` n'est toujours attribue que par le Done de `nadia-prof` (`CLAUDE.md`, double Done). La matrice garde son taux (142 / 182) ; le compteur de version est un second nombre, ecrit a cote, jamais a sa place.
- **Commande.** La commande de la "Methode du compte" donne les deux termes `cv` et `ec` ; la liste des lignes `ec` (A.20) est ce que `nadia-prof` croise avec ses criteres. Sans ses criteres, seul le premier terme est mesurable.
- **Texte existant que je signale sans en faire un critere** : `DEC-0045` section 3 ecrit que l'exposition d'un jeu au catalogue suit le Done pedagogique par jeu, pas le statut de la ligne. Savoir si elle fait partie de *"jouable"* est a `nadia-prof`.

### A.22 Distance au maximum : ce qui se mesure aujourd'hui

Le nombre `j` de lignes `en cours` jouables **n'est pas mesurable** : il depend de criteres que le depot n'a pas (A.20). Je ne l'estime pas. Le depot permet seulement l'encadrement par le nombre de lignes `en cours` (4), qui est une borne, pas une estimation :

| Niveau | Atteignables | Compteur si `j` = 0 | Compteur si `j` = 4 |
|---|---|---|---|
| CP | 59 | 51 (86,44 %) | 53 (89,83 %), `MAT-CP-01` et `-10` |
| CE1 | 44 | 25 (56,82 %) | 27 (61,36 %), `MAT-CE1-02` et `-09` |
| CM1 | 79 | 66 (83,54 %) | 66 (83,54 %), aucune ligne `en cours` |
| **Total** | **182** | **142 (78,02 %)** | **146 (80,22 %)** |

### A.23 Le maximum sous (c)

A.14 definissait le maximum par les seules lignes `couvert-valide` : 163 / 182. Sous (c), il compte aussi les lignes `en cours` jouables. Deux apports, tous deux **conditionnes aux criteres de `nadia-prof`** :

1. Les 4 lignes `en cours` d'aujourd'hui (A.20), sans lot.
2. Les 6 lignes de plafond `en cours` du rang C (`MAT-CE1-01`, `-10`, `-11`, `-15`, `FRA-CE1-03`, `-21`) : A.13 ecrit qu'un lot les fait passer a `en cours`, jamais a `couvert-valide`. Sous (c), ce lot suffit a les compter. **Ce reclassement est le mien**, tire de ce texte ; aucun de ces six lots n'a de prix ni de place ecrits.

| Niveau | Maximum A.14 (`couvert-valide`) | Maximum sous (c), borne haute | Taux |
|---|---|---|---|
| CP | 54 | 54 + 2 = 56 | 56 / 59 = 94,92 % |
| CE1 | 35 | 35 + 2 + 6 = 43 | 43 / 44 = 97,73 % |
| CM1 | 74 | 74 | 74 / 79 = 93,67 % |
| **Total** | **163** (89,56 %) | **173** | **173 / 182 = 95,05 %** |

**9 lignes atteignables restent hors du maximum, meme sous (c)** : `MAT-CE1-14`, `QLM-CP-12`, `HG-CM1-09`, `HG-CM1-10`, `HDA-CM1-09` (mecanique ou materiau), `FRA-CM1-01` (decision de l'utilisatrice, `D4 non`), `EMC-CP-04`, `LVE-CM1-06` (refus ecrits), `FRA-CP-05` (aucune forme connue). 173 + 9 = 182. Le rang C passe de 19 lignes a 9 ; rang A (11) et rang B (10) inchanges.

**Ce que (b) et (c) changent a la projection d'A.15.**

- Sans date, les sprints d'A.15 sont des previsions de sortie. O1 n'est plus exclue par un delai ; elle reste plus longue (6 a 8 sprints pour 31,0 a 36,0 de travail). Le choix de la borne reste a `nadia-prof` (`PAR-8`), la duree a `sofia-sm`.
- (c) n'allege pas le travail des 21 lignes de rangs A et B : aucune n'est `en cours` aujourd'hui. Il **ajoute six lots non chiffres** : le travail d'A.15 (31,0 a 36,0) est un plancher, pas un total, et je n'ecris pas de total faute de prix.
- **Question a `nadia-prof`, pas critere de ma main** : une ligne dont le paquet est branche avant son Done est `en cours` au planning (matrice, "Qui la met a jour"). Ses criteres disent-ils si un tel jeu est *"jouable"* ? C'est la situation que `DEC-0045` section 3 et `DEC-0009` ont voulu eviter.
- **Lecture, qui est la mienne** : A.14 definit le maximum *"sous les plafonds, refus et decisions tels que le depot les ecrit aujourd'hui"*. Une ligne dont `nadia-prof` ecrit un plafond ou un refus abaisse donc le maximum et ne bloque pas la sortie. Si l'utilisatrice la lit autrement, c'est `Q-U9`.

### A.24 (d) : ce que l'ouverture du CE1 change au denominateur, a faire quand les referentiels existeront

Aujourd'hui : 2 referentiels de CE1 (francais, mathematiques ; A.20), 44 lignes atteignables. **Je ne mesure pas l'effet maintenant et je n'estime pas le nombre de lignes** : il n'existe aucun referentiel neuf. A l'arrivee de chaque referentiel `squad/programme/<matiere>/competences-ce1.md` commite sur `round-two` :

1. Verifier qu'il existe : `ls squad/programme/*/competences-ce1.md | wc -l`, contre 2.
2. Ajouter ses lignes a la matrice, **dans un geste de matrice distinct, avec le build complet** (A.17), une section par matiere comme les existantes, statut `non couvert`, ou `non couvrable en l'etat` seulement sur la lecture de `nadia-prof` selon `DEC-0042`.
3. Relancer la commande de la "Methode du compte", par niveau, avant et apres, perimetre nomme. Ecrire : lignes ajoutees, `k` lignes atteignables ajoutees, denominateur 182 + `k`, CE1 44 + `k`. Le numerateur ne bouge pas : le taux devient 142 / (182 + `k`), **par le seul effet du denominateur, pas un mouvement de couverture** (meme formule que la note de la matrice du 2026-10-07).
4. Classer les lignes neuves en rangs A, B, C (A.13) et recalculer A.14, A.22 et A.23 sur le nouveau denominateur.
5. Placer la matiere dans l'ordre (`DEC-0115` section 3, `DEC-0120` section 1) : c'est mon geste.
6. Relire la condition (iii) d'`A10` (A.5 point 2) et `PAR-13` : une matiere de CE1 neuve est un terrain neuf, **lecture de `nadia-prof`**.
7. Avant le point 3, savoir si `Q-U9` fige le maximum a ce moment.

### A.25 `Q-U2` : en attente de `nadia-prof`

`nadia-prof` est interrogee sur `S54-OBS-SCI-CONSIGNE`, le 2026-10-13, par l'agent lanceur. **Statut : en attente, aucune reponse a cette heure.** Elle ecrit ses criteres (A.3 point 1) ou non. Sans criteres avant l'ouverture du planning, la retraite de 4.1 tient et le troisieme report reste interdit : deux reports comptes (s55, s56), inchange.

### A.26 Statuts, routage, ce que cet amendement ne fait pas

- **`DEC-0120` reste `Proposee`.** Elle passe a `Acceptee` quand : `A10` est ecrite ; `Q-U2` et `Q-U9` ont leur reponse ; les criteres de jouabilite et les referentiels de CE1 de `nadia-prof` sont ecrits (la formule d'A.21 et le denominateur d'A.24 en dependent) ; la borne et la duree sont ecrites (A.17, complete). `Q-U8` (b), (c), (d) ont leur reponse.
- **Routage.** `nadia-prof` : criteres de jouabilite (en parallele), referentiels de CE1, reponse a `Q-U2`, question d'A.23, lecture d'A.23 pour `PAR-8`. `sofia-sm` : la duree, sans date, a la lumiere d'A.23. Utilisatrice : `Q-U9`. Aucun poste neuf, aucune story neuve, aucun changement de code.
- **Ne fait pas** : n'ouvre pas s57 ; ne choisit ni O1 ni O2, ne fixe ni borne ni duree ; n'invente aucun critere de jouabilite ; ne prononce aucun Done ; ne touche ni code, ni catalogue, ni paquet, ni fiche, ni matrice ; ne dit pas que la CI distante est verte.
- **Jobs lances pour son commit** : `prefixes` (`.github/scripts/verifier-prefixes.sh`) et `chemins` (`.github/scripts/verifier-chemins.sh`), resultats dans le message de livraison. `outils` et `build` ne sont pas lances : le commit ne modifie ni code, ni contenu, ni outil, ni matrice.

---

## Amendement du 2026-10-13, quatrieme (`camille-po`, apres les reponses de l'utilisatrice sur les referentiels CE1 et l'arbitrage prealable de `nadia-prof`)

Annotation datee. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace est nomme en A.28. Le sprint n'est toujours pas ouvert : ni `planning.md`, ni duree, ni affectation. **La matrice change** : 17 lignes de CE1 y entrent (A.30), 142 / 182 devient 142 / 198. Aucun catalogue, aucun paquet, aucun code, aucune fiche n'est touche.

### A.27 Les mots de l'utilisatrice, forme 2 de `DEC-0062`

Mots de la session du 2026-10-13, tels que l'agent qui m'a lance me les transmet, en les donnant pour recus directement et citables (forme 2) ; **je ne les ai pas lus de sa main** (meme reserve que 0.1, A.1, A.9, A.18). Les deux premiers repondent aux questions de `nadia-prof` (`ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`, section 8, `Q1` et `Q2`), le troisieme a `Q-U9` ; les gloses sont celles de l'agent lanceur, pas des mots de l'utilisatrice.

| Question | Mots | Ce qu'ils fondent | Ce qu'ils ne fondent pas |
|---|---|---|---|
| `Q1` de `nadia-prof` (lignes de CE1 jumelles de celles du CP) | *"non nouveaux items"* | Aucune ligne de CE1 ne reprend une ligne de CP avec des items plus difficiles ; le critere de `nadia-prof` (une ligne de CE1 n'est ecrite que si un paquet de CE1 sert autre chose qu'un paquet de CP) tient | Le contenu d'une ligne de CE1 : il reste a elle |
| `Q2` de `nadia-prof` (rouvrir un son) | *"non pas de son"* | Aucun son, enregistre ou synthetique ; l'anglais et l'education musicale restent fermes au CE1 | Le CP et le CM1, dont les lignes `non couvrable en l'etat` restent comme A.16 les nomme |
| `Q-U9` (matrice figee ou vivante) | *"avec referentiels ecrits"* | Le maximum de la version se mesure une fois les referentiels de CE1 ecrits ; `Q-U9` est close sur cette reponse | **Le moment ou la matrice est figee** : `Q-U10` (A.28) |
| Ma lecture d'A.18 et de la derniere puce d'A.23 | *"oui"* | *"Toutes les lignes"* s'entend dans les lignes atteignables ; un plafond ou un refus ecrit de `nadia-prof` baisse le maximum et ne bloque pas la sortie | - |
| Travail demande dans cette session | *"ok"* | L'integration des deux referentiels a la matrice et le recompte (A.29 a A.32), le choix de repartition, le placement de `S54-OBS-SCI-CONSIGNE` et le rang de `G3` / `G4` (A.33 a A.35) | - |

### A.28 Ce que cet amendement ferme, ouvre et remplace

| Point | Etat | Remplace |
|---|---|---|
| `Q-U9` | **fermee** (A.27) | la question d'A.19 |
| `Q-U2` | **sans objet** : `nadia-prof` a ecrit ses criteres (`ARBITRAGE-PREALABLE-s57-...`, section 3), la condition d'A.3 point 1 est remplie ; `S54-OBS-SCI-CONSIGNE` est placee (A.34) | A.25 (*"en attente"*) et la retraite de 4.1 |
| `Q-U10` | **nouvelle**, ci-dessous | - |
| Criteres de jouabilite | **ecrits** : cinq criteres cumulatifs (`ARBITRAGE-PREALABLE-s57-...`, section 1.2, `D1`) | A.21 (*"formule incomplete"*), A.22 (*"non mesurable"*) |
| Referentiels de CE1 | **ecrits** : questionner le monde (12 lignes), EMC (5 lignes). Arts plastiques *reportee*, anglais *reportee* (rouvrable seulement par une decision sur le son, refusee en A.27), education musicale et EPS *refusees* (`ARBITRAGE-PREALABLE-s57-...`, section 2.2) | A.24 (*"a faire quand les referentiels existeront"*) |
| Gestes d'A.24 | points 1 a 5 faits ici (A.29 a A.35) ; point 6 (condition (iii) d'`A10` et `PAR-13` pour une matiere de CE1 neuve) **reste a `nadia-prof`** ; point 7 : `Q-U9` close | A.24 |
| `A10` | **ecrite par `nadia-prof`** (section 4.1) : condition (ii) non remplie pour s54, une occurrence de la forme (c), instance C en cause. Aucune derivation au-dela de 8,0 | A.5 (*"n'est pas ecrite"*) |
| Borne de s57 | **8,0 pour trois instances** (`nadia-prof`, `D7`, option O1). La duree reste a `sofia-sm` | A.4 (*"aucune valeur choisie"*) |
| Maximum de la version | **186 / 198** sous (c), borne haute (A.31) | A.23 (173 / 182) |
| Section 1, rang 4, *"ne ferment aucune ligne"* | remplace par A.35 | section 1, tableau, rang 4 ; 1.2 point 4 |

| Code | Question precise pour l'utilisatrice |
|---|---|
| `Q-U10` | **Le maximum de 198 lignes atteignables est-il fige maintenant, ou attend-il le referentiel d'arts plastiques CE1 ?** `nadia-prof` l'a *reporte* apres le verdict du rejeu d'`ART-CP-10` (section 2.2) ; s'il est ecrit, il ajoute des lignes. Lecture par defaut, la mienne : *"avec referentiels ecrits"* designe les referentiels qui existent le 2026-10-13 ; un referentiel qui arrive ensuite se traite par les gestes d'A.24, et le maximum bouge alors par le seul effet du denominateur, ecrit comme tel. Je ne fige rien sans sa reponse |

### A.29 Mesures lancees pour cet amendement (arbre `round-two` a `13e2588a`, worktree `matrice-camille`, 2026-10-13, perimetre : les 258 puis 275 lignes de donnees de la matrice, aucune mesure partielle)

Commande de la "Methode du compte" de `MATRICE-couverture.md`, reprise telle quelle :

```
awk -F'|' '/^\| [a-z]/ && ($3 ~ /^ (CP|CE1|CM1) $/) {s=$8; gsub(/^ +/,"",s); gsub(/^\*\*/,"",s);
  k="autre"; if (s ~ /^couvert-valide/) k="cv"; else if (s ~ /^non couvert( |$)/) k="nc";
  else if (s ~ /^en cours/) k="ec"; else if (s ~ /^non couvrable/) k="ncb"; c[k]++; t++}
  END {for (k in c) print k, c[k]; print "total", t}' squad/programme/MATRICE-couverture.md
```

| Sortie | Avant (`git show 13e2588a:squad/programme/MATRICE-couverture.md` passe a la commande) | Apres (fichier du worktree) |
|---|---|---|
| `cv` (`couvert-valide`) | 142 | 142 |
| `ec` (`en cours`) | 4 | 4 |
| `nc` (`non couvert`) | 36 | 52 |
| `ncb` (`non couvrable en l'etat`) | 76 | 77 |
| `total` | 258 | 275 |
| denominateur atteignable (`total` moins `ncb`) | 182 | **198** |
| `cv` / denominateur | 142 / 182 = 78,02 % | **142 / 198 = 71,72 %** |
| `cv` / `total` | 142 / 258 = 55,04 % | 142 / 275 = 51,64 % |

**Le numerateur ne bouge pas** : le taux baisse de 6,30 points par le seul effet du denominateur, pas par un mouvement de couverture (meme formule que la note de la matrice du 2026-10-07 ; A.24 point 3). Par niveau (`$3` comme cle) : CE1 `cv` 25, `ec` 2, `nc` 33, `ncb` 6, total 66 (49 avant) ; CP 92 et CM1 117, inchanges. Par section (`$2` et `$3` comme cle) : questionner le monde / CE1 `nc` 12, total 12 ; EMC / CE1 `nc` 4, `ncb` 1, total 5.

### A.30 La matrice : 17 lignes entrent, avec le statut de `nadia-prof`

Les 17 lignes sont copiees des deux referentiels (`squad/programme/questionner-le-monde/competences-ce1.md`, `squad/programme/enseignement-moral-et-civique/competences-ce1.md`), aucun mot change, sans jeu ni mecanique, chacune avec le statut que son jugement de faisabilite lui attribue. **Audit degrade, et elle le declare** : aucun paquet n'existe sur ces lignes, rien n'a ete joue. Ces statuts sont ceux d'*entree dans la matrice*, pas une instruction ligne par ligne, qui reste due avant le premier paquet.

| Section | Lignes | Statut a l'entree | Motif de `nadia-prof` |
|---|---|---|---|
| questionner le monde / CE1 | `QLM-CE1-01` a `-06`, `-08`, `-10`, `-12` (9) | `non couvert`, jouable au choix textuel | connaissances exactes et verifiables en trois propositions ; `-10` sans image |
| questionner le monde / CE1 | `QLM-CE1-07`, `-09`, `-11` (3) | `non couvert`, **en attente d'un materiau** | un circuit, un plan avec legende, un planisphere dessines : absents du depot (meme manque que `QLM-CP-12` et `HG-CM1-09`) |
| EMC / CE1 | `EMC-CE1-01` a `-04` (4) | `non couvert`, jouable au choix | `EMC-1` a `EMC-7` du CP valent telles qu'ecrites |
| EMC / CE1 | `EMC-CE1-05` (1) | **`non couvrable en l'etat`**, nature irreversible | cooperer est une conduite, pas une connaissance ; la faire passer pour un clic serait une contrefacon (`CON-1`) |

La forme est celle des sections voisines (sept cellules par ligne, une section par matiere et niveau, bilan sous le tableau). Le texte de tete de la matrice passe de vingt a vingt-deux referentiels. **Aucun test ni garde-fou n'a ete modifie** : la seule exigence de forme que j'ai trouvee qui lise la matrice est `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`, que le build complet execute ; son resultat est dans le message de livraison.

### A.31 Distance au maximum, recalculee sur 198 (remplace A.12, A.13, A.14, A.16 et A.23)

**A.12, par niveau.**

| Niveau | Lignes | `couvert-valide` | `en cours` | `non couvert` | `non couvrable` | Atteignables | Taux |
|---|---|---|---|---|---|---|---|
| CP | 92 | 51 | 2 | 6 | 33 | 59 | 51 / 59 = 86,44 % |
| CE1 | 66 | 25 | 2 | 33 | 6 | 60 | 25 / 60 = 41,67 % |
| CM1 | 117 | 66 | 0 | 13 | 38 | 79 | 66 / 79 = 83,54 % |
| **Total** | **275** | **142** | **4** | **52** | **77** | **198** | **71,72 %** |

Le CE1 passe de 56,82 % a 41,67 % par le denominateur seul (44 a 60 lignes atteignables). Lignes non fermees : 56 = 4 `en cours` + 52 `non couvert` (40 avant, plus 16).

**A.13, rangs** (definitions inchangees). Rang A : 11, inchange. Rang B : **23**, les 10 d'A.13 plus 13 lignes neuves (`QLM-CE1-01` a `-06`, `-08`, `-10`, `-12`, `EMC-CE1-01` a `-04`), fermables par ecriture, que l'ecriture reussisse etant un jugement de `nadia-prof`. Rang C : **22**, les 19 d'A.13 plus 3 lignes neuves dont le seul manque est un dessin (`QLM-CE1-07`, `-09`, `-11`). 11 + 23 + 22 = 56. Les lignes de rang C dont le seul manque est un dessin sont maintenant sept (`QLM-CP-12`, `HG-CM1-09`, `HG-CM1-10`, `HDA-CM1-09` et les trois neuves) ; l'avis de faisabilite de `theo-archi` reste a obtenir avant tout chiffrage (`DEC-0099` section 4 ter). `QLM-CE1-07` a une moitie textuelle jouable (*nommer les elements*) : aucun plafond `en cours` n'est ecrit pour elle, je ne le presume pas.

**A.14, maximum en `couvert-valide`** (sous les plafonds, refus et decisions que le depot ecrit) :

| Cible | Lignes `couvert-valide` | Sur 198 | Sur 275 |
|---|---|---|---|
| Aujourd'hui | 142 | 71,72 % | 51,64 % |
| Rang A ferme (+ 11) | 153 | 77,27 % | 55,64 % |
| **Maximum, rangs A et B (+ 34)** | **176** | **88,89 %** | 64,00 % |
| Si l'utilisatrice leve `D4` (+ `FRA-CM1-01`) | 177 | 89,39 % | 64,36 % |

| Niveau | Aujourd'hui | Rang A ferme | Maximum (A + B) | Taux au maximum |
|---|---|---|---|---|
| CP | 51 | 52 | 54 | 54 / 59 = 91,53 % |
| CE1 | 25 | 35 | 48 | 48 / 60 = 80,00 % |
| CM1 | 66 | 66 | 74 | 74 / 79 = 93,67 % |

**A.23, maximum sous (c), borne haute.** Les 176, plus les 4 lignes `en cours` d'aujourd'hui, plus les 6 lignes de plafond `en cours` (`MAT-CE1-01`, `-10`, `-11`, `-15`, `FRA-CE1-03`, `-21`) qu'un lot rend `en cours` : **186 / 198 = 93,94 %** (173 / 182 = 95,05 % avant). CP 56 / 59 = 94,92 % ; CE1 56 / 60 = 93,33 % ; CM1 74 / 79 = 93,67 % ; 56 + 56 + 74 = 186. Un plafond ou un refus ecrit de `nadia-prof` baisse ce nombre sans bloquer la sortie (A.27, *"oui"*).

**A.16, ce que la version sortirait non couvert.** Les 77 `non couvrable en l'etat` sortent non couvertes, quelle que soit la cible (CE1 : 5 de francais et `EMC-CE1-05`). **12 lignes atteignables restent hors du maximum, meme sous (c)** : les 9 d'A.23 (`MAT-CE1-14`, `QLM-CP-12`, `HG-CM1-09`, `HG-CM1-10`, `HDA-CM1-09`, `FRA-CM1-01`, `EMC-CP-04`, `LVE-CM1-06`, `FRA-CP-05`) et `QLM-CE1-07`, `-09`, `-11`. 186 + 12 = 198.

**Travail (A.15), plancher.** A.15 donnait 31,0 pour 163 (rang A 11,0 plus dix lots de rang B a 2,0). Ajouts : 13 lots textuels neufs au bareme de lot neuf de `nadia-prof` (1,0, *"application, pas mesure"*), soit 13,0 ; les quatre lots de `G4` a son prix ecrit de 1,0, soit 4,0. **Les deux lots de `G3` et l'instruction ligne par ligne des deux referentiels n'ont aucun prix ecrit** (section 2.3 de son arbitrage) et sont hors du total. Plancher : 31,0 + 13,0 + 4,0 = **48,0**. A 5,0 de travail par sprint a trois instances (8,0 moins 3 x 1,0 de fixe) : 10 sprints ; a 6,0 (deux instances) : 8. **Previsions de sortie, pas contraintes** (pas de date, A.19), sur des tarifs de bareme et le rythme mesure de 1,67 lot par sprint et par instance de francais CE1 (A.11). Je n'en tire aucune conclusion de planning.

### A.32 Le compteur de version, sous les criteres de `nadia-prof` (remplace A.21 et A.22)

**Compteur de version = lignes `couvert-valide` + lignes `en cours` dont le jeu remplit les cinq criteres de `nadia-prof`** : servi au catalogue ; CI verte, jobs nommes, sur l'arbre de la version ; verdict ecrit de sa main, non `REFUSE`, sur le jar qui sert l'entree ; plafond ecrit de sa main qui autorise `en cours`, avec la mention de couverture partielle reprise dans la cellule ; aucune reserve bloquante ouverte. Denominateur : les lignes atteignables, 198.

- **Ce qui est mesure.** `nadia-prof` ecrit que les quatre lignes `en cours` (`MAT-CP-01`, `MAT-CP-10`, `MAT-CE1-02`, `MAT-CE1-09`) remplissent les cinq criteres (sections 0 et 1.4 : un jeu au catalogue chacune, un verdict de sa main limite a la part servie). Ma part, lue sur la matrice : les quatre cellules portent la mention de couverture partielle (`awk` sur les lignes `en cours`, test `couverture partielle|mention` : oui sur les quatre). **Le critere 2 se lit sur l'arbre de la version, pas sur celui-ci** : le compteur est ecrit sous cette condition.
- **Ce qui ne compte pas aujourd'hui** : les six lignes dont `nadia-prof` plafonne le jeu a `en cours` (`MAT-CE1-01`, `-10`, `-11`, `-15`, `FRA-CE1-03`, `-21`) sont `non couvert` ; elles ne rempliront les criteres qu'apres un lot audite chacune (section 1.4).

| Niveau | Atteignables | `couvert-valide` | Compteur (+ 4 `en cours`) | Taux du compteur |
|---|---|---|---|---|
| CP | 59 | 51 | 53 | 89,83 % |
| CE1 | 60 | 25 | 27 | 45,00 % |
| CM1 | 79 | 66 | 66 | 83,54 % |
| **Total** | **198** | **142** | **146** | **73,74 %** |

La matrice garde son taux (142 / 198 = 71,72 %) ; le compteur est un second nombre, ecrit a cote, jamais a sa place (A.21). **Libelle devant un adulte** (`nadia-prof`, 1.4 : annoncer un total sans la distinction *"ferait passer une couverture partielle pour une couverture"* ; le libelle est a moi) : *"146 lignes sur 198 : 142 validees par la professeure, 4 en cours, dont le jeu est jouable pour la part de la competence que la ligne dit servir."* L'enfant ne voit pas la matrice. `nadia-prof` ne prononce aucun Done de version (`D2`) ; je n'en ecris pas.

### A.33 Repartition de s57 entre A, A' et B : choix de `camille-po` (`D7`)

`nadia-prof` fixe la borne (8,0, O1) et donne deux repartitions, en ecrivant que *"le choix est un ordre, donc a `camille-po`"* (section 4.3). **Retenue : la colonne "sans lot de `G4`".**

| Instance | Budget | Contenu |
|---|---|---|
| A (groupe X) | **2,5** | arbitrage 0,5, marge de rejeu 0,5, `VSJ-CE1-R1` 0,5, un lot de francais CE1 |
| A' (groupe Y) | **3,0** | arbitrage 0,5, marge de rejeu 0,5, deux lots de francais CE1 |
| B | **2,5** | arbitrage 0,5, marge de rejeu 0,5, rejeu d'`ART-CP-10` 0,5, `S54-OBS-SCI-CONSIGNE` 0,25 ; 0,75 non affectes |
| Somme | **8,0** (`PAR-9`) | trois lots de francais CE1 |

**Motifs, verifiables dans son arbitrage et dans cette decision :**

1. **L'ordre de la section 1 tient.** Le rang 1 (francais CE1, dix lots) passe devant le rang 4. L'autre colonne retire un lot de rang 1 a A', qui porte deux lots sous 3,0, pour le donner a un lot de rang 4 chez B.
2. **Meme gain au compteur, plus ferme.** Sous (c) et `D1`, les deux colonnes ajoutent trois lignes au compteur. La colonne retenue en ferme trois en `couvert-valide` ; l'autre en ferme deux et laisse la troisieme `en cours`, sous mention de couverture partielle (plafond de `G4`).
3. **Meme budget non affecte.** Les deux colonnes affectent 7,25 sur 8,0 (A 2,5, A' 3,0, B 1,75 d'un cote ; A 2,5, A' 2,0, B 2,75 de l'autre) : l'avantage de la seconde, B pleine, ne consomme pas moins de capacite de `nadia-prof`.
4. **Un ecart d'arithmetique dans son tableau, signale et non corrige** (c'est son document). La colonne "avec un lot de `G4`" ecrit pour A' *"2,75 : arbitrage, marge, 1 lot ; 0,25 non affectes"*. Sur ses tarifs, 0,5 + 0,5 + 1,0 = 2,0 et 2,75 moins 2,0 = **0,75**, pas 0,25. Sa somme de 8,0 est juste ; ce sont les non affectes qui sont de 0,75 dans les deux colonnes. Question `Q-N1`.

**Ce qui reste hors de ma main** : le contenu exact des lots, l'accord sur `PAR-2` (ecrit, six conditions, section 4.2, dont *"`-08` et `-09` ne sont pas des lots de s57"*), `PAR-13` sur le groupe Y et l'affectation des lots aux instances. Si elle juge a l'arbitrage de A' qu'un second lot de Y n'est pas prenable, le lot libere ne va pas de lui-meme a `G4` : ce serait un amendement de cette repartition.

| Code | Question a `nadia-prof` (routee par `sofia-sm`, pas une question a l'utilisatrice) |
|---|---|
| `Q-N1` | (a) Corriger ou confirmer le 0,25 de la colonne "avec un lot de `G4`" (0,75 sur ses tarifs). (b) Les 0,75 non affectes de B peuvent-ils porter l'instruction ligne par ligne de l'un des deux referentiels de CE1, due avant le premier paquet de ces lignes et dont elle n'a pas ecrit le prix (section 2.3) ? Si oui, elle fixe le prix ; je ne l'invente pas |

### A.34 `S54-OBS-SCI-CONSIGNE` : placee en B (`Q-U2` sans objet)

**Compte des reports.** Ouverte a s54 ; **premier report a s55** (`DEC-0115` A.10), **second report a s56** (`DEC-0116` section 5, A.2). `DEC-0112` section 6 : *"un troisieme report n'est plus permis"* ; l'utilisatrice, le 2026-10-09 : *"Reporter a s57, abandon ou placement ecrit alors"*. 4.1 l'a retiree faute de criteres, de proprietaire et de prix ; A.3 point 1 la rendait candidate de B a une seule condition, que `nadia-prof` ecrive ses criteres. **Elle les a ecrits** (section 3 de son arbitrage), avec un prix (0,25) et un avis : *"la placer en B"*, parce qu'elle est visible sur toutes les manches et ne coute presque rien.

**Decision : placee a s57, instance B, 0,25.** C'est un placement ecrit, pas un report : le compte reste a deux. **Si elle n'est pas livree et rejouee a la cloture de s57, ce serait un troisieme report, interdit** : elle est alors abandonnee par un acte ecrit a la cloture, pas reportee a s58 (`nadia-prof` ne demande pas de troisieme report, section 3).

**Criteres, ses mots** (section 3, la story complete est au backlog) : (1) les 12 enonces de `cm1-sciences-extraire-information.yaml` perdent leur premiere phrase, chacun commence par *"D'après ce tableau,"* ou *"D'après ce texte,"* ; mesure : `grep -c "puis réponds à la question"` sur le paquet rend 0 ; (2) rien d'autre ne change, `git diff` ne touche que les 12 lignes `enonce:` ; (3) la consigne du catalogue ne change pas ; (4) les tests qui lisent ce paquet restent verts, le developpeur nomme ceux qu'il a lances ; (5) rejeu par elle, instance B, une partie de dix manches, `SCI-CM1-20` reste `couvert-valide`.

**Verification contre l'existant, faite avant d'ecrire** (elle est a ma charge) : le critere 1 rend **12 aujourd'hui** (`grep -c` sur le paquet, 2026-10-13), donc rouge sur l'existant : c'est un critere. Les criteres 2 et 3 sont des clauses de non-regression, vertes par construction aujourd'hui ; je ne les presente pas comme des correctifs. Critere 4 : `PaquetCm1SciencesExtraireInformationTest` ne contient ni *"Lis"* ni *"puis r"* (`grep -n`), donc aucune de ses assertions ne lit la phrase retiree ; ce n'est pas un test que j'ai lance, le developpeur le reverifie.

**Proprietaire.** La story est la mienne ; le developpeur est **a designer par `sofia-sm`** (`nadia-prof` : *"Developpeur : a designer par `sofia-sm`"*) ; le rejeu est a `nadia-prof`, instance B. **Une decision qui implique un changement de code n'est pas prise tant que ce changement n'a pas de proprietaire** : tant que `sofia-sm` n'a pas designe le developpeur avant l'ouverture du planning, le placement n'est pas complet et la retraite de 4.1 tient. La story est routee a `sofia-sm` dans le meme geste (A.37).

### A.35 Rang de `G3` et `G4`, et des lignes neuves (remplace la section 1, rang 4, et 1.2 point 4)

**Ce qui change.** Le rang 4 etait ecrit *"ne ferment aucune ligne"*. Sous (c) et `D1`, un lot de `G3` ou de `G4` fait compter une ligne dans la version (`en cours`, un lot audite, plafond de `nadia-prof`) ; elle le nomme et renvoie le rang a `camille-po` (section 1.4). **Les rangs ne sont pas renumerotes.**

| Rang | Contenu | Place, et motif |
|---|---|---|
| 1 | dix lots de francais CE1 (`couvert-valide`) | **inchange, premier.** Ils ferment des lignes ; les cinq derniers lots n'ont eu aucun refus ecrit (0.3) |
| 2, 3 | epuise, vide | inchanges |
| **4a** | **`G4` : cinq paquets de mathematiques CE1.** Quatre lignes `en cours` au plus (`MAT-CE1-01`, `-10`, `-11`, `-15`), 1,0 par lot (`nadia-prof`) ; `MAT-CE1-14` reste conditionnee a `A3` d'`ADR-0080` | **juste apres le rang 1.** Matiere distincte du francais CE1 : prenable par B sous `PAR-2`, donc sans consommer la capacite de A et A' ; chaque lot fait compter une ligne. **Aucun lot de `G4` a s57** (A.33, motif 1) : B n'a que 0,75 non affectes, moins que le lot |
| **4b** | **`G3` : deux paquets de francais CE1** (`FRA-CE1-03`, `-21`), plafond `en cours` | **apres les dix lots du rang 1 et apres 4a.** Meme matiere que le rang 1 : ils prennent la capacite de A et A', qui est le goulot (`PAR-2`), et ne font compter une ligne qu'`en cours`. `-21` n'est releve que par une decision de l'utilisatrice (dictee ou saisie alphabetique au CE1) |
| **5** (neuf) | les 23 lignes de rang B : les 10 d'A.13 et les 13 de CE1 (A.31) | **derriere les rangs 1 et 4.** Aucune n'est engageable aujourd'hui : l'instruction des deux referentiels de CE1 est due avant le premier paquet et son prix n'est pas ecrit ; le rang B d'A.13 n'a pas de prix mesure. **Pas d'ordre interne : un ordre ne se derive pas de prix qui n'existent pas** (`DEC-0114` section 4 : rien n'est engage sans proprietaire ni prix). Il se fait quand `nadia-prof` ecrit les prix |
| hors rang | les 22 lignes de rang C (A.31) | bloquees par un materiau, une mecanique, une decision ou un refus ecrit : ni lot ni ecriture |

**Matieres neuves.** Questionner le monde CE1 et EMC CE1 sont des matieres de CE1 que le depot n'a jamais servies : leur place dans `PAR-13` (terrain neuf) est a `nadia-prof` (A.24 point 6). Je ne la presume pas.

### A.36 Ce que je ne tranche pas, et a qui c'est

| A qui | Quoi |
|---|---|
| `sofia-sm` | **La duree du sprint** (sans date, A.19) ; **l'heure de fusion de l'amendement d'`ADR-0065`** avant le premier worktree d'un lot de A' (condition 2 de `nadia-prof`, 4.2) ; **la seconde ligne `A10`** de `retro.md` de s56 (*"Ecrire dans le depot les deux arrets d'agent et le watchdog"*, collision d'identifiant signalee en A.5) ; **le developpeur de `S54-OBS-SCI-CONSIGNE`** ; les sous-repertoires de scratchpad et les ports par agent (condition 5) ; la question `Q-N1`. Je n'ecris aucun de ces points |
| `theo-archi` | `S57-ADR-0065-DEUX-INSTANCES` avant le premier worktree de A' (engagee, A.2) ; l'avis de faisabilite des sept dessins (A.31). Je ne tranche aucun moyen technique |
| `nadia-prof` | L'affectation des lots aux instances ; `PAR-13` pour les deux matieres neuves de CE1 ; l'instruction ligne par ligne des deux referentiels et son prix ; les prix du rang B ; `Q-N1`. Si une derivation au-dela de 8,0 est voulue pour s58, la condition (i) est a montrer par moi dans un document de planning (4.3) : **non faite ici** |
| Utilisatrice | `Q-U10` |

### A.37 Statuts, routage, ce que cet amendement ne fait pas

- **`DEC-0120` reste `Proposee`.** Elle passe a `Acceptee` quand la duree de s57 est ecrite par `sofia-sm` et que `Q-U10` a sa reponse. Cet amendement leve : `A10` (ecrite par `nadia-prof`), `Q-U2` (sans objet), `Q-U9`, les criteres de jouabilite, les referentiels de CE1, la borne.
- **Statuts de stories** : `S54-OBS-SCI-CONSIGNE` passe de *retiree, candidat de B sous condition* a **placee a s57, instance B, 0,25, developpeur a designer** (A.34). Aucune autre story ne change ; `S57-ADR-0065-DEUX-INSTANCES` reste engagee.
- **Routage** : le developpeur de `S54-OBS-SCI-CONSIGNE` et les autres points d'A.36 sont routes a `sofia-sm` par le backlog et le journal de gouvernance du meme commit.
- **Ne fait pas** : n'ouvre pas s57 ; ne fixe ni la duree ni l'heure de fusion d'`ADR-0065` ; ne prononce aucun Done et ne juge aucun contenu (les statuts des 17 lignes sont ceux de `nadia-prof`) ; ne compose aucun lot ; ne touche ni catalogue, ni paquet, ni code, ni fiche ; ne corrige pas l'ecart de `LVE-CM1-06` signale en A.17 (la cellule dit encore *"jouable par saisie"* alors que `nadia-prof` l'a refusee a la saisie : geste de matrice a part, non demande) ; ne dit pas que la CI distante est verte.
- **Jobs de `ci.yml` lances pour son commit** : nommes, avec leurs sorties, dans le message de livraison (`build` par `bash outils/mvn-jdk21.sh -B clean verify`, `chemins`, `prefixes`). `outils` n'est pas lance : le commit ne modifie aucun outil, et je n'en deduis pas le resultat.

---

## Amendement du 2026-10-13, cinquieme (`camille-po`, apres les reponses de l'utilisatrice a `Q1` et `Q2` de `sofia-sm` et a `Q-U10`, et les plafonds de `nadia-prof`)

Annotation datee. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace est nomme en A.39. Le sprint n'est toujours pas ouvert : ni `planning.md`, ni duree, ni borne, ni affectation. **La matrice change dans trois cellules de texte et dans aucun statut** (A.40) : 142 / 198 inchange. Aucun catalogue, aucun paquet, aucun code, aucune fiche.

### A.38 Les mots de l'utilisatrice, forme 2 de `DEC-0062`

Mots de la session du 2026-10-13, tels que l'agent qui m'a lance me les transmet, en les donnant pour recus directement et citables (forme 2) ; **je ne les ai pas lus de sa main** (meme reserve que 0.1, A.1, A.9, A.18, A.27). `Q1` et `Q2` sont les questions de `sofia-sm` (`squad/sprints/s57-version-cp-ce1-cm1-trois-instances/preparation.md`, sections 1.3 et 4). Les gloses de la colonne "Ce qu'ils fondent" sont celles de l'agent lanceur.

| Question | Mots | Ce qu'ils fondent | Ce qu'ils ne fondent pas |
|---|---|---|---|
| `Q1` de `sofia-sm` : *"on adapte les instances et la duree car il faut sortir une version"* (A.1) demandait-il un sprint plus long pour porter plus que 8,0 ? | *"oui plus que 8"* | Un sprint plus long qui porte plus de 8,0 | La valeur de la borne : **borne de `nadia-prof` (`PAR-8`), en attente**. La duree : `sofia-sm`. Je n'ecris aucun chiffre |
| `Q2` de `sofia-sm` : developpeur de `S54-OBS-SCI-CONSIGNE` | *"lea dev"* | `lea-dev` est le developpeur de `S54-OBS-SCI-CONSIGNE` | La ligne d'affectation au planning et la charge de `lea-dev` : `sofia-sm` |
| `Q-U10` (A.28) : maximum fige maintenant, ou apres le referentiel d'arts plastiques CE1 ? | *"avec ART plastique CE1"* | Le maximum de la version n'est fige qu'une fois le referentiel d'arts plastiques CE1 ecrit | Un chiffre de maximum : **en attente du referentiel d'arts plastiques CE1** (`nadia-prof` l'instruit en parallele, dit l'agent lanceur) |

### A.39 Ce que cet amendement ferme, ouvre et remplace

| Point | Etat | Remplace |
|---|---|---|
| `Q-U10` | **fermee** (A.38). Ma lecture par defaut d'A.28 (*"avec referentiels ecrits"* designait ceux qui existent le 2026-10-13) est **ecartee** | la question et la lecture d'A.28 |
| Borne de s57 | **plus que 8,0, valeur de `nadia-prof`, en attente** | A.28, ligne *"Borne de s57 : 8,0 pour trois instances"* ; la somme de 8,0 de la repartition d'A.33 (A 2,5, A' 3,0, B 2,5), qui se rouvre quand la borne est ecrite. L'ordre de la section 1 et d'A.35 vaut a toute borne |
| Maximum de la version | **en attente du referentiel d'arts plastiques CE1** | A.28, ligne *"186 / 198"*, et A.31 : 176 / 198 et 186 / 198 ne sont plus le maximum de la version, ce sont des calculs sur la matrice du 2026-10-13 (A.42) |
| Denominateur | **198 + `k`**, `k` = lignes atteignables du referentiel d'arts plastiques CE1, non ecrit | A.31 et A.32, *"198"* |
| Developpeur de `S54-OBS-SCI-CONSIGNE` | **`lea-dev`** (A.44) | A.34, *"a designer par `sofia-sm`"* |
| `HG-CM1-10` | **reclassee** : son manque n'est pas un dessin (A.41) | A.13 et A.31, listes des lignes dont le seul manque est un dessin |
| `LVE-CM1-06` | cellule de matrice **corrigee** (A.40) | A.17 et A.37, *"ecart signale, non corrige"* |
| `QLM-CE1-07`, `QLM-CE1-09` | plafonds `en cours` **reportes** dans la matrice (A.40) | A.31, *"aucun plafond `en cours` n'est ecrit pour elle, je ne le presume pas"* (`QLM-CE1-07`) |
| Priorite de la forme fidele de la legende de `QLM-CE1-09` | **statuee** (A.43) | - |

### A.40 La matrice : trois cellules de texte, aucun statut

Mesures lancees pour cet amendement (arbre `round-two` a `729b0d8a`, worktree `s57b-camille`, 2026-10-13, perimetre : les lignes de donnees de la matrice, aucune mesure partielle). Commande de la "Methode du compte" de `MATRICE-couverture.md`, reprise telle quelle :

```
awk -F'|' '/^\| [a-z]/ && ($3 ~ /^ (CP|CE1|CM1) $/) {s=$8; gsub(/^ +/,"",s); gsub(/^\*\*/,"",s);
  k="autre"; if (s ~ /^couvert-valide/) k="cv"; else if (s ~ /^non couvert( |$)/) k="nc";
  else if (s ~ /^en cours/) k="ec"; else if (s ~ /^non couvrable/) k="ncb"; c[k]++; t++}
  END {for (k in c) print k, c[k]; print "total", t}' squad/programme/MATRICE-couverture.md
```

| Sortie | Avant (`git show 729b0d8a:squad/programme/MATRICE-couverture.md` passe a la commande) | Apres (fichier du worktree) |
|---|---|---|
| `cv` | 142 | 142 |
| `ec` | 4 | 4 |
| `nc` | 52 | 52 |
| `ncb` | 77 | 77 |
| `total` | 275 | 275 |
| denominateur (`total` moins `ncb`) | 198 | 198 |
| `cv` / denominateur | 142 / 198 = 71,72 % | 142 / 198 = 71,72 % |

**Cette identite est attendue, elle ne prouve rien d'autre** : un plafond n'est pas un statut. Ce qui la tient est lu a part : les trois cellules touchees commencent encore par `non couvert` (`awk` sur les lignes dont la septieme cellule contient `QLM-CE1-07`, `QLM-CE1-09` ou `LVE-CM1-06` : trois lignes, chacune de neuf champs `awk`, statut lu `non couvert -`), et la repartition des champs `awk` par ligne de donnees est la meme avant et apres (272 lignes a neuf champs, 3 a huit).

Ce que chaque cellule dit maintenant, mots de `nadia-prof` :

| Ligne | Ce qui est reporte | Source |
|---|---|---|
| `QLM-CE1-07` | Plafond **`en cours` aujourd'hui**, sur la moitie textuelle (`CIR-CE1-1`), avec la mention de couverture partielle reprise mot pour mot : *"nommer les elements d'un circuit seuls ; dire si un circuit dessine fait briller l'ampoule n'est pas servi"* ; `couvert-valide` quand un circuit dessine existe et sert la seconde moitie (`CIR-CE1-2`) | `ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`, 3.7 |
| `QLM-CE1-09` | Plafond **`en cours` avec un plan dessine en forme degradee, rien sans plan** (`PLG-CE1-1`, `PLG-CE1-2`), mention reprise mot pour mot : *"situer un lieu par un symbole dont le sens est donne dans l'enonce ; lire la legende sur le plan et suivre un itineraire ne sont pas servis"* ; `couvert-valide` avec la legende lue dans le dessin et l'itineraire servi sous une forme ou l'enfant lit la legende pour le suivre ; aucun item d'itineraire dans la forme degradee | meme document, 3.9 et 11.2 a 11.3 |
| `LVE-CM1-06` | La valeur de `nadia-prof` : **refusee a la saisie depuis le 2026-10-01**, motif `R6` ; hors vivier ; `non couvert` et non `non couvrable en l'etat` (`R6` n'est ni une contrainte fondatrice ni un jugement humain sur une production libre) ; le denominateur ne change pas. La cellule disait *"jouable par saisie, sous conditions `ANG-S1` et `ANG-S2`"*, faux depuis le 2026-10-01 (mention corrigee, ancienne formule citee) | `ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`, 10.3 ; `ARBITRAGE-PREALABLE-s45-cinq-lots.md`, 5.1 |

Le texte de tete de la matrice porte une note datee du meme jour ; le bilan de la section questionner le monde / CE1 recoit une phrase datee.

### A.41 `HG-CM1-10` : reclassee, son manque n'est pas un dessin

**Le fait.** A.13 et A.31 rangent `HG-CM1-10` parmi les lignes de rang C dont le seul manque est un dessin. Trois textes du depot disent le contraire : la cellule de matrice (*"et le lieu de vie de l'enfant n'est pas connu du produit (`DEC-0028`, aucune persistance)"*), la colonne "Ce que le depot dit" d'A.13 elle-meme, et `nadia-prof` (`ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md`, ligne `HG-CM1-10`) : *"NON TOUCHEE, et le palier 2 n'est pas son obstacle"* ; la ligne demande a l'enfant ou **il** vit, `EMC-1`, `EMC-6` et `BIA-4` refusent tout item qui l'interroge sur son foyer ou sa commune, et un emboitement d'echelles generique serait une autre competence. `theo-archi` arrive au meme verdict (`squad/sprints/inter-s56/avis-faisabilite-sept-dessins.md`, 2.1, `F3`). **L'erreur etait la mienne** : mon classement confondait les deux obstacles que la cellule nomme.

**Ce qui change, sans reecrire A.13 ni A.31.**

| Endroit | Etait | Est |
|---|---|---|
| A.13, derniere phrase (*"quatre lignes de rang C n'ont pour seul manque qu'un dessin"*) | `QLM-CP-12`, `HG-CM1-09`, `HG-CM1-10`, `HDA-CM1-09` | **trois** : `QLM-CP-12`, `HG-CM1-09`, `HDA-CM1-09` |
| A.31, rang C (*"sont maintenant sept"*) | `QLM-CP-12`, `HG-CM1-09`, `HG-CM1-10`, `HDA-CM1-09`, `QLM-CE1-07`, `-09`, `-11` | **six** : `QLM-CP-12`, `HG-CM1-09`, `HDA-CM1-09`, `QLM-CE1-07`, `-09`, `-11` |
| Categorie de `HG-CM1-10` dans le tableau d'A.13 | C, mecanique ou materiau | **C, decision** : `DEC-0028`, a cote de `FRA-CM1-01` |

**Ce qui ne change pas.** Le rang C compte toujours 22 lignes (A.31) ; la ligne etait hors maximum et le reste ; la cellule de matrice n'est pas modifiee, elle nomme les deux obstacles et c'est exact. **Ce qui la leverait** : les deux sources ne disent pas la meme chose (`DEC-0028`, decision de l'utilisatrice ; `nadia-prof`, une autre competence) et je n'en choisis pas. Un dessin de carte, lui, ne la leve pas. L'avis de `theo-archi` a deja la bonne liste : six lignes dont le seul manque est un dessin, une de moins que celle d'A.31.

### A.42 Distance au maximum et compteur de version, avec les plafonds de `nadia-prof`

**Methode** (celle d'A.31 et A.32, reprise, rien d'autre). Matrice : `cv` / denominateur, commande de A.40. Compteur de version : `cv` plus les lignes `en cours` dont le jeu remplit les cinq criteres de `nadia-prof` (A.32). Borne haute sous (c) : compteur plus les lignes que les rangs A et B et les plafonds `en cours` ecrits permettent de fermer ou de rendre jouables, **sous les plafonds, refus et decisions que le depot ecrit aujourd'hui** (A.14, A.23). Distance : borne haute moins compteur, par niveau. Denominateur : `198 + k` ; **le maximum de la version est en attente du referentiel d'arts plastiques CE1** (A.38) : les chiffres ci-dessous sont calcules **sur la matrice du 2026-10-13, soit `k` = 0**, et ne sont pas ce maximum.

**Ce que les plafonds changent.**
- `QLM-CE1-07`, plafond `en cours` sans dessin, lot textuel de 1,0 (`nadia-prof`, 6.2) : la ligne entre dans la borne haute. **+ 1.**
- `QLM-CE1-09`, plafond `en cours` **avec un plan dessine** : aucun plan n'existe. Je la laisse hors de la borne haute, comme `QLM-CP-12`, `HG-CM1-09`, `HDA-CM1-09` et `QLM-CE1-11`, que A.31 a laisses hors maximum sur le meme motif (A.13 : une ligne dont le depot ecrit que ce qui manque n'est ni un lot ni une ecriture). **Lecture, qui est la mienne** : si le plan existait, `QLM-CE1-09` ajouterait 1 et la borne haute serait 188.
- `LVE-CM1-06` : hors maximum avant, hors maximum apres ; aucun nombre ne bouge.
- Compteur de version : **146, inchange**. `QLM-CE1-07` et `-09` n'ont ni paquet ni entree de catalogue : aucun des cinq criteres n'est rempli.

| Niveau | Atteignables | `cv` / atteignables | Compteur | Borne haute sous (c) | Distance compteur - borne haute |
|---|---|---|---|---|---|
| CP | 59 | 51 / 59 = 86,44 % | 53 / 59 = 89,83 % | 56 / 59 = 94,92 % | 3 |
| CE1 | 60 | 25 / 60 = 41,67 % | 27 / 60 = 45,00 % | **57** / 60 = **95,00 %** (56 avant) | **30** (29 avant) |
| CM1 | 79 | 66 / 79 = 83,54 % | 66 / 79 = 83,54 % | 74 / 79 = 93,67 % | 8 |
| **Total** | **198** | **142 / 198 = 71,72 %** | **146 / 198 = 73,74 %** | **187 / 198 = 94,44 %** (186 avant) | **41** (40 avant), soit 20,71 points |

**Verifications d'arithmetique.** 142 + 34 (rangs A et B) = 176 ; 176 + 4 (`en cours` d'aujourd'hui) = 180 ; 180 + 6 (plafonds `en cours` de `MAT-CE1-01`, `-10`, `-11`, `-15`, `FRA-CE1-03`, `-21`) = 186 ; 186 + 1 (`QLM-CE1-07`) = 187 ; 57 + 56 + 74 = 187 ; 41 = 3 + 30 + 8. **Onze lignes atteignables restent hors de la borne haute** (douze avant) : `MAT-CE1-14`, `QLM-CP-12`, `HG-CM1-09`, `HG-CM1-10`, `HDA-CM1-09`, `FRA-CM1-01`, `EMC-CP-04`, `LVE-CM1-06`, `FRA-CP-05`, `QLM-CE1-09`, `QLM-CE1-11` ; 187 + 11 = 198. Maximum en `couvert-valide` (A.14, A.31) : **176 / 198 = 88,89 %, inchange** : `QLM-CE1-07` n'y entre qu'avec un circuit dessine.

**Libelle devant un adulte** (A.32, inchange) : *"146 lignes sur 198 : 142 validees par la professeure, 4 en cours, dont le jeu est jouable pour la part de la competence que la ligne dit servir."* Il ne se publie pas tel quel : `k` n'est pas fixe.

**Travail, plancher d'A.31** : 48,0 devient **49,0** (+ 1,0, le lot de la moitie textuelle de `QLM-CE1-07`, au prix ecrit de `nadia-prof`). Les sprints d'A.31 (10 a trois instances, 8 a deux) etaient calcules sur 8,0 : **ils ne sont plus recalcules**, la borne est en attente. Le lot de `QLM-CE1-07` entre au rang 5 d'A.35, un lot de plus que les 23 lignes de rang B, avec les lots textuels de questionner le monde CE1 et sans ordre interne ; la ligne reste de rang C.

**Ce que la reponse a `Q-U10` implique, a lire par l'utilisatrice.** Le maximum attend un referentiel que `nadia-prof` ecrit *"apres le verdict du rejeu d'`ART-CP-10`"* (`ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`, 2.2) ; ce rejeu est un poste de B a s57. Son instruction en parallele (agent lanceur) leve-t-elle ce report ? C'est a elle. Tant que le referentiel n'est pas ecrit, `k` est inconnu et le maximum ne s'ecrit pas ; a son arrivee, les gestes d'A.24 s'appliquent (points 1 a 5 ; le point 6 est a elle ; le point 7 est clos par cette reponse).

### A.43 Priorite de la forme fidele de la legende de `QLM-CE1-09`

`nadia-prof` demande la forme fidele (la legende lue dans le dessin) : *"je la demande pour cette ligne, parce que c'est la seule voie vers `couvert-valide`"* ; la priorite est a moi, le moyen et l'`ADR` a `theo-archi` (11.3 et 11.5). `ADR-0073`, clause 4 : elle *"cede"* le jour ou `nadia-prof` demande une figure legendee, *"et les garde-fous de texte apprennent a lire un SVG dans le meme geste"*.

**Decision : non prioritaire, non engagee, aucune story de code ouverte.** Elle ne passe devant aucun lot de la forme degradee et n'entre dans aucun planning tant que les trois conditions suivantes ne sont pas toutes ecrites :

1. un lot de `QLM-CE1-09` en forme degradee a un verdict ecrit de `nadia-prof` (aujourd'hui rien n'est joue : aucun plan, aucun item, son instruction est faite sur description, 11.1) ;
2. `nadia-prof` ecrit la forme d'itineraire qui permettrait `couvert-valide` ; elle a refuse les deux ecritures possibles sous un geste par manche (11.2) ;
3. `theo-archi` dit par ecrit si l'`ADR` de la forme fidele porte aussi cet itineraire, ou s'il demande un autre moyen (question routee par `sofia-sm` ; je ne decide aucun moyen).

**Motifs, verifiables dans les documents cites :**
1. **Zero gain pour la version, un pour la matrice.** Sous (c) (*"on compte toutes les lignes meme en cours si le jeux est jouable"*, A.18), la ligne compte des qu'elle est `en cours` jouable : la forme degradee la fait entrer au compteur (A.32). La forme fidele ne change ni le compteur (146 + 0) ni la borne haute (187) ; elle fait passer la ligne de `en cours` a `couvert-valide` : 142 / 198 deviendrait 143 / 198 = 72,22 %, soit + 0,51 point, et le maximum en `couvert-valide` 176 deviendrait 177.
2. **Elle ne ferme pas la ligne seule.** `couvert-valide` demande les deux : la legende lue dans le dessin et l'itineraire servi (11.3). Le second n'a pas de forme (condition 2).
3. **Son cout n'est pas un lot** : un `ADR`, la fin d'une clause d'un `ADR` accepte, des garde-fous de texte a faire lire un SVG. `theo-archi` n'a ecrit aucun prix et je n'en invente pas.
4. **Le manque de la forme degradee n'est pas encore connu.** Dire ce que l'enfant perd sans la legende suppose que quelqu'un ait joue le plan.

**Compte.** La demande date du 2026-10-13 : aucun report anterieur, aucun engagement. Elle n'est pas refusee ; elle n'est pas ordonnee avant ses conditions. **Reversible** : si l'utilisatrice la veut devant, ou si les trois conditions sont remplies, je la place par un amendement, avec la story et son proprietaire.

### A.44 `S54-OBS-SCI-CONSIGNE` : developpeur `lea-dev`

Les trois elements du placement de `DEC-0114` section 4 et `DEC-0115` section 4 existent : criteres de `nadia-prof` (A.34), proprietaire **`lea-dev`** (*"lea dev"*, A.38), prix 0,25. **Le placement est complet.** Deux reports comptes (s55, s56), inchange ; un placement n'est pas un report. **Si elle n'est pas livree et rejouee a la cloture de s57, ce serait un troisieme report, interdit : elle est alors abandonnee par un acte ecrit.** La ligne d'affectation s'ecrit au planning (`sofia-sm`, `PAR-16`) ; `lea-dev` porte aussi la correction d'`ART-CP-10`, le poste le plus risque (`preparation.md`, section 4), et la charge de chaque developpeur est a `sofia-sm`, pas a moi. La story est mise a jour au backlog (champ developpeur).

### A.45 Statuts, routage, ce que cet amendement ne fait pas

- **`DEC-0120` reste `Proposee`.** Elle passe a `Acceptee` quand la duree et la borne de s57 sont ecrites (`sofia-sm`, `nadia-prof`). `Q-U10` a sa reponse (A.38) ; le maximum de la version reste en attente du referentiel d'arts plastiques CE1 et n'est pas ecrit par cette decision, donc pas une condition de son acceptation. Cela remplace la condition d'A.37 (*"la duree de s57 est ecrite par `sofia-sm` et `Q-U10` a sa reponse"*).
- **Statuts de stories** : `S54-OBS-SCI-CONSIGNE` : placee a s57, instance B, 0,25, **developpeur `lea-dev`**. Aucune autre story ne change ; la forme fidele de la legende n'a pas de story (A.43). **Matrice : 142 / 198, aucun statut.**
- **Routage.**

| A qui | Quoi |
|---|---|
| `nadia-prof` | La borne `PAR-8` au-dela de 8,0, **en parallele** ; ses conditions (i) a (iii) de derivation (criteres, 4.3) : (ii) *"non etablie"*, a elle ; (i) *"le montrer est a `camille-po`"* ; l'itineraire de `QLM-CE1-09` (A.43, condition 2) ; le referentiel d'arts plastiques CE1 (A.42) |
| `sofia-sm` | La duree, a la lumiere de la reponse `Q1` : `preparation.md` P2 (*"on ne l'allonge pas pour absorber plus de 8,0"*) est contredit par *"oui plus que 8"*, a elle de la relire ; la ligne d'affectation de `lea-dev` et sa charge (A.44) ; la question a `theo-archi` de A.43, condition 3 |
| `theo-archi` | Rien de neuf. Sa liste des dessins (six lignes) est deja la bonne (A.41) ; l'`ADR` de la forme fidele n'est pas demande avant les conditions d'A.43 |
| `lea-dev` | `S54-OBS-SCI-CONSIGNE` a l'ouverture du planning, aucun edit avant |
| `camille-po` | **La mesure de la condition (i) de la derivation au-dela de 8,0** (A.5 point 2) : due par la reponse *"oui plus que 8"*, **non faite ici** (hors du travail demande). Seconde fois que je la laisse : premiere en A.36 (quatrieme amendement), seconde ici. Elle se fait avant que `nadia-prof` derive la borne |
| Utilisatrice | Aucune question neuve |

- **Ne fait pas** : n'ouvre pas s57 ; ne choisit ni la borne, ni la duree ; ne fige aucun maximum ; ne prononce aucun Done et ne juge aucun contenu (les plafonds et la valeur de `LVE-CM1-06` sont ceux de `nadia-prof`) ; ne touche ni catalogue, ni paquet, ni code, ni fiche ; ne dit pas que la CI distante est verte.
- **Jobs de `ci.yml` lances pour son commit** : nommes, avec leurs sorties, dans le message de livraison (`build` par `bash outils/mvn-jdk21.sh -B clean verify`, `chemins`, `prefixes`). `outils` n'est pas lance : le commit ne modifie aucun outil, et je n'en deduis pas le resultat.

---

## Amendement du 2026-10-13, sixieme (`camille-po`, apres le referentiel d'arts plastiques CE1 de `nadia-prof`)

Annotation datee. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace est nomme en A.47. Le sprint n'est toujours pas ouvert : ni `planning.md`, ni duree, ni borne, ni affectation. **La matrice gagne deux lignes et aucun statut ne change** (A.48) : 142 / 200. **Le maximum de la version est fige** (A.49). **La condition (i) de la derivation au-dela de 8,0 est mesuree** (A.50), ce que A.36 et A.45 avaient laisse a deux reprises. Aucun catalogue, aucun paquet, aucun code, aucune fiche.

### A.46 Ce qui le declenche

`Q-U10` est fermee par la reponse *"avec ART plastique CE1"* (A.38) : le maximum n'etait fige qu'une fois le referentiel d'arts plastiques CE1 ecrit. **Il l'est** : `squad/programme/arts-plastiques/competences-ce1.md`, ecrit par `nadia-prof` le 2026-10-13 (`squad/conformite/ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`, section 11.5, qui ecrit que *"le maximum peut etre fige des que `camille-po` aura integre ces deux lignes ; le figer est a elle"*). Son report de 2.2 (*"apres le verdict du rejeu d'`ART-CP-10`"*) etait plus large que son motif : elle le corrige en 11.5, ce qui attend ce verdict est l'instruction de `ART-CE1-02`, pas l'existence de la ligne. Les gestes d'A.24 s'appliquent : points 1 a 3 en A.48, points 4 et 5 en A.49, point 6 deja ecrit par elle (11.5 : les deux lignes sont des terrains neufs pour `PAR-13`), point 7 clos par A.38.

### A.47 Ce que cet amendement ferme, ouvre et remplace

| Point | Etat | Remplace |
|---|---|---|
| `Q-U10` | **executee** : le maximum est fige (A.49) | A.39, ligne *"Maximum de la version : en attente du referentiel d'arts plastiques CE1"* |
| Denominateur | **200** (`198 + k`, `k` = 2) | A.39, ligne *"198 + `k`"*. A.31, A.32 et A.42 sont des calculs sur 198, avant ce geste : ils ne sont pas recopies ligne a ligne, les valeurs a 200 sont en A.49 |
| Referentiels de CE1 | **cinq ecrits** : francais, mathematiques, questionner le monde, EMC, arts plastiques ; anglais, education musicale et EPS refusees (`ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`, 2.2 et 10.4) | A.28, ligne *"Referentiels de CE1"* (arts plastiques *reportee*) |
| Condition (i) | **mesuree** (A.50) ; sa lecture est a `nadia-prof` | A.38 et A.45 : *"non faite ici"* |
| Rang C | **24 lignes** (22 plus `ART-CE1-01` et `-02`) ; rangs A et B inchanges (11, 23) ; 58 lignes non fermees = 4 `en cours` + 54 `non couvert` = 11 + 23 + 24 | A.31, *"Rang C : 22"* |
| Plancher de travail | **49,0, inchange** : les deux lignes neuves n'ont ni lot ni ecriture | - |

### A.48 La matrice : deux lignes entrent (A.24 points 1 a 3)

Mesures lancees pour cet amendement (arbre `round-two` a `3988e15a`, worktree `s57c-camille`, 2026-10-13, perimetre : les lignes de donnees de la matrice, aucune mesure partielle). `ls squad/programme/*/competences-ce1.md | wc -l` rend **5** (2 en A.24). Commande de la "Methode du compte" de `MATRICE-couverture.md`, reprise telle quelle, sur `git show 3988e15a:squad/programme/MATRICE-couverture.md` puis sur le fichier du worktree :

| Sortie | Avant | Apres |
|---|---|---|
| `cv` | 142 | 142 |
| `ec` | 4 | 4 |
| `nc` | 52 | 54 |
| `ncb` | 77 | 77 |
| `total` | 275 | 277 |
| denominateur (`total` moins `ncb`) | 198 | **200** |
| `cv` / denominateur | 142 / 198 = 71,72 % | **142 / 200 = 71,00 %** |

**Le numerateur ne bouge pas : le taux baisse de 0,72 point par le seul effet du denominateur, pas par un mouvement de couverture** (A.24 point 3, meme formule que la note de la matrice du 2026-10-07). Par niveau (`$3` comme cle) : CE1 `cv` 25, `ec` 2, `nc` 35, `ncb` 6, total 68 (66 avant) ; CP 92 et CM1 117 inchanges. Par section (`$2` et `$3`) : arts plastiques / CE1 `nc` 2, total 2. **Seconde lecture, ecrite autrement** : une analyse des cellules en Python, sans `awk`, rend 142 / 4 / 54 / 77, total 277, denominateur 200. Sa premiere version rendait 274 : elle exigeait une barre finale, et trois lignes de donnees n'en ont pas (A.40 : *"3 a huit"* champs) ; corrigee pour lire huit champs, elle rend 277.

Les deux lignes, statuts de `nadia-prof` (referentiel, section "Jugement de faisabilite") : `ART-CE1-01` `non couvert`, **en attente d'un materiau** (la forme existe, celle d'`ART-CP-09`) ; `ART-CE1-02` `non couvert`, **en attente de la forme a deux oeuvres et d'un materiau** (la forme d'`ART-CP-10`, refusee le 2026-10-11 et pas rejouee ; son instruction attend ce verdict). Aucun plafond `en cours` n'est ecrit pour l'une ni pour l'autre. La mention exigee d'avance si une ligne ferme (*"reception d'oeuvres seule ; aucune pratique plastique n'est servie par ce produit"*) est reprise dans les deux cellules. Corpus : `grep -c "identifiant: \"\?oeuvre-" squad/corpus/oeuvres-domaine-public.yaml` rend 12. **Le registre ne porte aucun champ de technique : la phrase "aucun dessin ni aucune photographie" est celle de `nadia-prof`, que je ne verifie pas par commande.**

### A.49 Le maximum de la version, fige

**Maximum : 200 lignes atteignables** (CP 59, CE1 62, CM1 79 ; 59 + 62 + 79 = 200), sur une matrice de 277 lignes dont 77 `non couvrable en l'etat`. **Il ne bouge plus que par un acte ecrit qui le dit** : un referentiel neuf, ou le classement d'une ligne en `non couvrable en l'etat` ; il bouge alors par le seul effet du denominateur, et la note de la matrice l'ecrit comme tel. Un changement de rang d'une ligne (par exemple `ART-CE1-01` en rang B le jour ou un corpus de dessins et de photographies existe) change les cibles ci-dessous, pas le denominateur. **Place dans l'ordre de la section 1 (A.24 point 5)** : hors rang, avec les lignes de rang C dont le manque n'est ni un lot ni une ecriture.

Cibles, **methode d'A.31 et A.32 reprise sans rien d'autre**, sous les plafonds, refus et decisions que le depot ecrit aujourd'hui (A.42) :

| Cible | Sur 198 (A.42) | Sur 200 |
|---|---|---|
| Matrice, `couvert-valide` | 142 / 198 = 71,72 % | **142 / 200 = 71,00 %** |
| Compteur de version (`cv` plus 4 `en cours` jouables) | 146 / 198 = 73,74 % | **146 / 200 = 73,00 %** |
| Maximum en `couvert-valide` (rangs A et B, + 34) | 176 / 198 = 88,89 % | **176 / 200 = 88,00 %** |
| Borne haute sous (c) | 187 / 198 = 94,44 % | **187 / 200 = 93,50 %** |
| Distance compteur - borne haute | 41 lignes, 20,71 points | **41 lignes, 20,50 points** |

| Niveau | Atteignables | `cv` | Compteur | Maximum `cv` | Borne haute sous (c) |
|---|---|---|---|---|---|
| CP | 59 | 51 / 59 = 86,44 % | 53 / 59 = 89,83 % | 54 / 59 = 91,53 % | 56 / 59 = 94,92 % |
| CE1 | 62 | 25 / 62 = 40,32 % | 27 / 62 = 43,55 % | 48 / 62 = 77,42 % | 57 / 62 = 91,94 % |
| CM1 | 79 | 66 / 79 = 83,54 % | 66 / 79 = 83,54 % | 74 / 79 = 93,67 % | 74 / 79 = 93,67 % |
| **Total** | **200** | **142 / 200 = 71,00 %** | **146 / 200 = 73,00 %** | **176 / 200 = 88,00 %** | **187 / 200 = 93,50 %** |

**Verifications d'arithmetique.** 57 + 56 + 74 = 187 ; 187 + 13 = 200. **Treize lignes atteignables restent hors de la borne haute** (onze en A.42, plus `ART-CE1-01` et `-02`) : `MAT-CE1-14`, `QLM-CP-12`, `HG-CM1-09`, `HG-CM1-10`, `HDA-CM1-09`, `FRA-CM1-01`, `EMC-CP-04`, `LVE-CM1-06`, `FRA-CP-05`, `QLM-CE1-09`, `QLM-CE1-11`, `ART-CE1-01`, `ART-CE1-02`. CE1 : 57 + 5 (`MAT-CE1-14`, `QLM-CE1-09`, `-11`, `ART-CE1-01`, `-02`) = 62. **Rang des deux lignes : C**, par la definition d'A.13 : le depot ecrit ce qui manque (un materiau, et pour `-02` une forme refusee), et ce n'est ni un lot ni une ecriture. **Ce que cela dit a l'utilisatrice** : sur 200 lignes atteignables, 13 ne se ferment par aucun lot ni aucune ecriture que le depot connaisse aujourd'hui ; le maximum de 187 est un plafond, pas une promesse.

**Libelle devant un adulte** (A.32, denominateur change) : *"146 lignes sur 200 : 142 validees par la professeure, 4 en cours, dont le jeu est jouable pour la part de la competence que la ligne dit servir."* Il reste non publie tel quel tant que la version n'est pas decidee (`nadia-prof`, 1.4 : pas de Done de version).

### A.50 La condition (i) de la derivation au-dela de 8,0, mesuree

**Ce que la condition dit** (`ARBITRAGE-PREALABLE-s46-trois-instances-refusees-le-vivier-borne-avant-la-capacite.md`, section 5, reprise en 4.3 du document de criteres du 2026-10-13) : *"une composition a deux instances qui remplit 8,0 (`PAR-8` respecte) et laisse dehors au moins 4,0 de lots instruits, soit la capacite d'une instance"*, prenables par la troisieme sous `PAR-1` et `PAR-2`. Mesure placee chez `camille-po` (A.5 point 2, A.36, A.45).

**Composition a deux instances, C2 (A et A', francais CE1, accord `PAR-2` de `nadia-prof` pour s57, six conditions).** Chacune : arbitrage 0,5, marge de rejeu 0,5, trois lots a 1,0 = **4,0** (`PAR-9` ; `PAR-8` : au plus 5,0 et trois lots). Somme **8,0**, six lots de francais CE1 sur les dix du rang 1 (section 1). Faisable sans deplacer de frontiere : A' dispose de trois lots de Y hors `-08` et `-09` (`FRA-CE1-10`, `-15`, `-24`, soit `ce1-phrases-types`, `ce1-accord-groupe-nominal`, `ce1-familles-de-mots`) ; A prend trois des cinq de X. **Hors de C2, non comptes** : `VSJ-CE1-R1` (0,5, reste a A, condition 3 de `nadia-prof`) et les quatre lots de francais CE1 restants (deux de X, `-08`, `-09`) : meme matiere que A et A', donc non prenables par une troisieme instance sous `PAR-2`.

**Lots instruits laisses dehors, prenables par la troisieme.** Mesure : `comm -23` des fichiers de `adapters/adapter-content/src/main/resources/contenu/` et des `paquet:` de `catalogue.yaml`, filtre `ce1-` : **17 paquets sans entree de catalogue**, dont, par `lignesDeReferentiel` de chacun, 12 de francais (`FRA-CE1-03`, `-04` a `-11`, `-15`, `-21`, `-24`) et 5 de mathematiques (`MAT-CE1-01`, `-10`, `-11`, `-14`, `-15`).

| Groupe (matiere et niveau, `PAR-2`) | Lots | Prix ecrit | Source du prix | Instruit par | `PAR-13` |
|---|---|---|---|---|---|
| Mathematiques CE1, `G4` : `ce1-nombres-jusqu-a-1000` (`MAT-CE1-01`), `ce1-masses-contenances` (`-10`), `ce1-calendrier-dates` (`-11`), `ce1-figures-planes` (`-15`), plafond `en cours` | 4 | 1,0 chacun, **4,0** | A.31 ; criteres de `nadia-prof`, 4.3 et 11.4 | plafonds de `nadia-prof` (section 1, rang 4 ; A.35) | terrain connu |
| Questionner le monde CE1 : `QLM-CE1-01` a `-06`, `-08`, `-10`, `-12` (9) et la moitie textuelle de `-07` (1) | 10 | 1,0 chacun, **10,0** | `ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`, 6.2 (bareme, application, pas mesure) | meme document, sections 3 et 9 | pas un terrain neuf (5.1) |
| EMC CE1 : `EMC-CE1-01` a `-04` | 4 | 1,0 chacun, **4,0** | meme document, 6.2 (bareme, application, pas mesure) | meme document, sections 4 et 9 | pas un terrain neuf (5.1) |
| **Total des lots** | **18** | **18,0** | | | |
| Reprise d'`ART-CP-10` (rejeu, pas un lot, `PAR-10`) | - | 0,5 | `cloture-nadia.md` de s56, section 3, reprise en 4.3 du document de criteres | | - |

Le compte des 14 lots de questionner le monde et d'EMC est relu au disque : un `awk` sur le recapitulatif des dix-sept verdicts (section 9 du document d'instruction) rend 14 lignes `ACCEPTEE` hors `QLM-CE1-09` et `-11` (*"rien sans dessin"*) : `QLM` 10, `EMC` 4. **Exclus du compte, avec leur motif** : `QLM-CE1-09` et `-11` (aucun dessin, aucun prix) et la moitie dessinee de `-07` ; `MAT-CE1-14` (conditionnee a `A3` d'`ADR-0080`, non ecrit) ; les deux lots de `G3` (francais CE1, `PAR-2`) ; les dix lignes de rang B d'A.13 (aucune n'a d'instruction ligne par ligne ecrite : *"ecriture possible"* ne dit pas *"instruit"*) ; `ART-CE1-01` et `-02` (aucune instruction) ; `S54-OBS-SCI-CONSIGNE` (0,25, pas un lot).

**Resultat, sous deux lectures que je ne departage pas.**

1. **Lecture de la lettre de la condition (`PAR-1` et `PAR-2` seuls) : tenue.** 18,0 de lots instruits, prenables hors du francais CE1, contre 4,0 requis ; 18,5 avec la reprise d'`ART-CP-10`. Chaque lot est entier (`PAR-1`) et chaque groupe de matiere et niveau tient sur une seule instance (`PAR-2`).
2. **Lecture avec le plafond de l'instance qui les porterait (`PAR-8` : trois lots, 5,0 ; `PAR-9` : 4,0 dont 1,0 fixe) : non tenue.** Une troisieme instance porte au plus **trois lots**, soit **3,0** (3,5 avec la reprise d'`ART-CP-10`, qui n'est pas un lot au sens de `PAR-10`), moins que les 4,0 requis. Les 18 lots instruits sont tous a 1,0 : aucune combinaison de trois d'entre eux n'atteint 4,0. Les quatre lots de `G4`, que `nadia-prof` donne en exemple de 4,0 prenable par B (4.3 et 11.4 de son document), ne tiennent pas non plus sur une seule instance : quatre lots depassent `PAR-8`. **Un cas change ce resultat, et je ne le tranche pas** : `QLM-CP-10`, rang B, prix ecrit 2,0 (`DEC-0099` 4 ter, repris dans le tableau de 5 du document de criteres), compte avec questionner le monde CE1 sous `PAR-2` ; si *"ecriture possible"* valait *"instruit"*, trois lots (2,0 + 1,0 + 1,0) feraient 4,0 sur une seule instance.

**Ce que je ne tranche pas** : laquelle des deux lectures est celle de sa condition (la section 5 de `ARBITRAGE-PREALABLE-s46...` dit *"soit la capacite d'une instance"* et nomme `PAR-1` et `PAR-2`, pas `PAR-8`) et si `QLM-CP-10` est *"instruit"* : **`nadia-prof`**. **Ce que la mesure ne change pas** : (i) est une des trois conditions. (ii) n'est pas etablie (s54 porte une occurrence de la forme (c) ; s56 n'est pas verifie, document de criteres, 11.4), donc **O2 reste non derivable meme si la lecture 1 est la bonne**. L'utilisatrice a repondu *"oui plus que 8"* (A.38) ; `nadia-prof` y repond par des paliers de 8,0, chacun clos avant le suivant (11.3), sans borne au-dela de 8,0 dans un meme palier. **Un palier est un moyen de porter plus de 8,0 sans (i)**, pas une derivation de O2 : c'est sa question `Q3` a l'utilisatrice, que je ne ferme pas.

**Compte du report.** Mesure due depuis A.5 point 2 : laissee a A.36 (premiere fois), a A.45 (seconde fois), faite ici. Aucun troisieme report.

### A.51 Ce que je ne tranche pas, et a qui c'est

| A qui | Quoi |
|---|---|
| `nadia-prof` | La lecture de la condition (i) et le statut de `QLM-CP-10` (A.50) ; la verification de s56 contre les trois formes (condition (ii), 0,25, *"quand `camille-po` me la demande"*) ; le rang des deux lignes d'arts plastiques CE1 s'il change (un corpus de dessins et de photographies) ; l'instruction de `ART-CE1-01` et de `-02` (celle de `-02` apres le rejeu d'`ART-CP-10`) |
| `theo-archi` | La question des droits d'un corpus de dessins et de photographies, que `nadia-prof` ne tranche pas (`ART-3`, 11.8 de son document) ; rien d'autre |
| `sofia-sm` | La duree et le planning de s57 a la lumiere de `Q1` et de la borne (A.45, inchange) ; `PAR-2` quand un lot de ces matieres est compose |
| Utilisatrice | `Q3` de `nadia-prof` : un sprint plus long comme suite de paliers de 8,0, chacun audite et clos avant le suivant. Aucune question neuve de ma part |

### A.52 Statuts, ce que cet amendement ne fait pas

- **`DEC-0120` reste `Proposee`.** Elle passe a `Acceptee` quand la duree et la borne de s57 sont ecrites (A.45, inchange). Le maximum fige n'en est pas une condition.
- **Statuts de stories** : aucun ne change ; aucune story neuve (les deux lignes n'ont ni lot ni ecriture).
- **Ne fait pas** : n'ouvre pas s57 ; ne choisit ni la borne, ni la duree, ni le nombre de paliers ; ne compose aucun lot de s57 ; ne prononce aucun Done et ne juge aucun contenu (les statuts des deux lignes et la nature de leur manque sont ceux de `nadia-prof`) ; ne touche ni catalogue, ni paquet, ni code, ni fiche ; ne dit pas que la CI distante est verte.
- **Jobs de `ci.yml` lances pour son commit** : nommes, avec leurs sorties, dans le message de livraison (`chemins` par `bash .github/scripts/verifier-chemins.sh` sur `git ls-files`, `prefixes`). `build` et `outils` ne sont pas lances : le commit ne modifie ni code, ni contenu, ni outil, et je n'en deduis pas le resultat.

---

## Amendement du 2026-10-14, septieme (`camille-po`, apres les reponses de l'utilisatrice a `Q3` de `nadia-prof` et `Q-S1` de `sofia-sm`, et la fusion de `duree-et-paliers.md`)

Annotation datee. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace est nomme en A.54 et A.55. Le sprint n'est toujours pas ouvert : ni `planning.md`, ni objectif gele, ni affectation engagee. **Aucun fichier de matrice n'est touche** (`git diff --stat` sur `squad/programme/` : vide) : 142 / 200 inchange. Aucun catalogue, aucun paquet, aucun code, aucune fiche.

### A.53 Les mots de l'utilisatrice, forme 2 de `DEC-0062`

Mots du 2026-10-14, tels que l'agent qui m'a lance me les transmet, en les donnant pour recus directement et citables (forme 2) ; **je ne les ai pas lus de sa main** (meme reserve que 0.1, A.1, A.9, A.18, A.27, A.38). Les parentheses qui accompagnent `Q-S1` dans la transmission sont une glose de l'agent lanceur, pas des mots de l'utilisatrice.

| Question | Mots | Ce qu'ils fondent | Ce qu'ils ne fondent pas |
|---|---|---|---|
| `Q3` de `nadia-prof` (`ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`, 11.9) : *"Un sprint plus long, je l'accepte comme une suite de paliers de 8,0, chacun audite et clos avant le suivant. Est-ce ce qu'elle veut ?"* | *"oui"* | s57 est un sprint long, suite de paliers de 8,0, chacun audite et clos avant le suivant (11.3). Il complete *"oui plus que 8"* (A.38) : la borne par fenetre reste 8,0 et le *"plus que 8"* se porte par la suite de paliers | Un chiffre au-dela de 8,0 dans un palier (O2 non derivable, (ii) non etablie, A.50) ; le nombre de paliers et la duree (`sofia-sm`, 11.3 condition 6) |
| `Q-S1` de `sofia-sm` (`duree-et-paliers.md`, section 4) : une seule `review.md` et une seule `retro.md` a la cloture du sprint, plus un fichier de cloture de palier de `sofia-sm` a chaque palier, avec amendement de `squad/README.md` section 4 ; alternative : un sprint par palier | *"ok"* | La proposition de `sofia-sm`, pas l'alternative (glose de l'agent lanceur : une review, une retro, un fichier de cloture de palier par `sofia-sm`) | Le texte de l'amendement de `squad/README.md` section 4 et le contenu du fichier de cloture de palier : `sofia-sm`, pas moi. Elle ne dit pas que la duree est ecrite |

### A.54 `S57-ADR-0065-DEUX-INSTANCES` : livree avant l'ouverture

**Mesures** (arbre `round-two` a `544159d9`, worktree `s57-camille-p1`, 2026-10-14) :

| Commande | Sortie |
|---|---|
| `git merge-base --is-ancestor 729b0d8a HEAD` | 0 |
| `git merge-base --is-ancestor 7a34366c HEAD` et `git merge-base --is-ancestor bac0a6d1 HEAD` | 0 et 0 |
| `git log -1 --format='%h %ci %s' 729b0d8a` | `729b0d8a 2026-10-13 17:42:21 +0200 [SOF] fusion de s57-theo` |
| `git log -1 --format='%h %ci %s' 7a34366c` | `7a34366c 2026-10-13 16:49:57 +0200 [THEO] s57: ADR-0065 amendement A2 - deux instances de francais CE1, aucune ne modifie le garde-fou` |
| `git log -1 --format='%h %ci %s' bac0a6d1` | `bac0a6d1 2026-10-13 17:40:48 +0200 [THEO] s57: ADR-0065 A2 point 1 - controle chemin et prefixe dans le job prefixes, dette payee` |
| `grep -c -i instance` sur le fichier d'`ADR-0065` | 15 (0 quand A.2 engageait la story, 3.2) |

**Statut : livree avant l'ouverture, fusion `729b0d8a`, 2026-10-13 17:42:21.** Proprietaire `theo-archi`, hors `PAR`, 0,0 pour `nadia-prof`, **aucune ligne engagee au planning** : il la cite comme livree avant l'ouverture (`duree-et-paliers.md` section 5). Remplace *"engagee"* en A.2, et *"`S57-ADR-0065-DEUX-INSTANCES` reste engagee"* en A.37. Le critere 1 de la story (`grep -c -i instance` au moins 1) est vert ; je n'ai relu ni son critere 3 (compte d'`ArchRule`) ni son critere 4 (`mvn verify`) : ils sont a `theo-archi` et a la CI, et **cette decision ne dit pas que la CI distante est verte**. Le contenu de l'amendement est de `theo-archi` : je ne le juge pas.

**Ce qui reste pour les lots de A'** : la condition 2 de `nadia-prof` (accord `PAR-2`) porte sur le premier worktree d'un lot de A', que le planning n'a pas ouvert : l'amendement est fusionne avant lui ; le premier worktree d'un lot de A' relance `git merge-base --is-ancestor 729b0d8a HEAD` et cite sa sortie (`duree-et-paliers.md` section 5).

### A.55 Ce qui reste pour que `DEC-0120` passe de `Proposee` a `Acceptee`

**Condition en vigueur (A.45)** : *"quand la duree et la borne de s57 sont ecrites (`sofia-sm`, `nadia-prof`)"*. Etat mesure :

| Element | Etat | Ce qui reste |
|---|---|---|
| Borne | **ecrite** : 8,0 par palier, 5,0 et trois lots par instance et par palier, aucun chiffre au-dela de 8,0 dans un palier (`nadia-prof`, 11.3, 2026-10-13 18:32) ; sa forme est confirmee par `Q3` (A.53) | rien |
| Duree | **proposee, pas ecrite** : `duree-et-paliers.md`, ligne *"Statut"* : *"proposition de `sofia-sm`"*, section *"Decisions prises"* : *"Aucune `ADR` ni `DEC` n'est creee ou amendee par ce fichier"*, `Q3` et `Q-S1` ouvertes. Les deux sont repondues (A.53), le fichier ne le dit pas | `sofia-sm` ecrit la duree comme decidee : une ligne datee, dans `duree-et-paliers.md` ou dans `planning.md`, qui reprend `P1'` a `P6'` ou les amende a la lumiere d'A.53, et l'amendement de `squad/README.md` section 4 (fichier de cloture de palier) que `Q-S1` appelle |

**Je ne passe pas `DEC-0120` a `Acceptee` dans ce geste.** La decision est la mienne, mais sa condition porte sur un ecrit de `sofia-sm` qui n'existe pas sous forme de decision : le passer sur la foi d'un *"oui"* et d'un *"ok"* relayes et d'une proposition fusionnee reviendrait a declarer ecrit ce qui est propose. Je la passe, dans un amendement date, quand la ligne de `sofia-sm` est dans le depot.

**Ce que le planning exige en plus, hors acceptation de cette decision** (etat : `ls squad/sprints/s57-version-cp-ce1-cm1-trois-instances/` : `composition-palier-1.md`, `duree-et-paliers.md`, `preparation.md`) : l'avis de faisabilite de `theo-archi` pour s57 (absent) ; l'arbitrage prealable de `nadia-prof` pour chaque instance du palier 1, qui tranche les affectations proposees (absent) ; `lecture-testabilite.md` de `yanis-qa` (absente) ; les stories des trois lots de francais CE1 du palier 1 (A.56).

### A.56 Palier 1 : composition ecrite, lots sans story, report compte

La composition est dans `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/composition-palier-1.md` : **A.33 reprise sans changement** (A 2,5, A' 3,0, B 2,5, somme 8,0, plafond de 5,0 et trois lots par instance tenus, marge de 0,5 par instance), trois lots de francais CE1 (`FRA-CE1-04` a A, `-10` et `-15` a A'). **Toutes les affectations d'instance y sont proposees** ; `nadia-prof` les arbitre.

**Report compte.** Les trois lots n'ont aucune story au backlog (`grep -c '^### S57-BR' docs/backlog/backlog.md` : 0). Je ne les ecris pas dans ce geste (hors du perimetre demande) : **premier report de ce poste, s57, 2026-10-14**. Elles s'ecrivent avant que `planning.md` engage les lots ; un lot sans story n'est pas engage.

### A.57 Statuts, routage, ce que cet amendement ne fait pas

- **`DEC-0120` reste `Proposee`** (A.55). **Statuts de stories** : `S57-ADR-0065-DEUX-INSTANCES` livree avant l'ouverture (A.54). Aucune autre ne change. **Matrice : 142 / 200, aucun statut.**
- **Routage.**

| A qui | Quoi |
|---|---|
| `sofia-sm` | La duree ecrite comme decidee (A.55) ; l'amendement de `squad/README.md` section 4 pour le fichier de cloture de palier (`Q-S1`) ; `planning.md` : regle d'ouverture d'un palier, sous-repertoires de scratchpad et ports par agent, par instance et par palier, `729b0d8a` cite comme livree avant l'ouverture ; la lecture du glissement de `S54-OBS-SCI-CONSIGNE` entre paliers (`composition-palier-1.md` section 4) |
| `nadia-prof` | Arbitrage prealable de A, A' et B pour le palier 1 : affectations proposees, `Q-P1` (marge de B) et `Q-P2` (quels lots de X et de Y) de `composition-palier-1.md` ; lecture de la condition (i) (A.51, inchange) |
| `camille-po` | Les trois stories de lots du palier 1 (A.56) ; la composition des paliers suivants, apres la cloture du palier 1 |
| `theo-archi` | Rien de neuf |
| Utilisatrice | Aucune question neuve |

- **Ne fait pas** : n'ouvre pas s57 ; ne choisit ni la duree, ni le nombre de paliers ; ne passe pas `DEC-0120` a `Acceptee` ; n'engage aucune affectation ; ne prononce aucun Done et ne juge aucun contenu ; ne touche ni catalogue, ni paquet, ni code, ni fiche ; ne dit pas que la CI distante est verte.
- **Jobs de `ci.yml` lances pour son commit** : nommes, avec leurs sorties, dans le message de livraison (`chemins`, `prefixes`). `build` et `outils` ne sont pas lances : le commit ne modifie ni code, ni contenu, ni outil, et je n'en deduis pas le resultat.

---

## Amendement du 2026-10-14, huitieme (`camille-po`, apres l'arbitrage prealable du palier 1 de `nadia-prof`)

Annotation datee. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace est nomme en A.59. Le sprint n'est toujours pas ouvert : ni `planning.md`, ni objectif gele, ni affectation engagee. **Aucun fichier de matrice n'est touche** (`git diff round-two --stat -- squad/programme/` : vide) : 142 / 200 inchange. Aucun catalogue, aucun paquet, aucun code, aucune fiche.

### A.58 Ce que `nadia-prof` a tranche : `Q-P1`, `Q-P2`, affectations

Source : `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/arbitrage-nadia-palier-1.md` (`8396696e`, fusionne `7180f666`, 2026-10-14). Ce sont ses decisions, pas des mots de l'utilisatrice ; je les consigne sans les juger (`D1` a `D6` de son document).

| Point | Decision de `nadia-prof` | Section |
|---|---|---|
| `Q-P1`, marge de rejeu de B | *"B garde sa marge de 0,5, nommee a l'ouverture du palier, allouee a aucun poste."* Elle couvre un second rejeu d'`ART-CP-10` (0,5) ou de `S54-OBS-SCI-CONSIGNE` (0,25). Elle corrige sa condition 1 de 11.3 : *"par instance qui porte un lot ou un rejeu"*. **Seule exception sur les 0,75 non affectes de B** : si les deux rejeux de B sont refuses dans ce palier, le second rejeu de `S54-OBS-SCI-CONSIGNE` (0,25) se prend sur eux ; aucun lot n'y entre. B reste a 2,5 | 2 |
| `Q-P2`, groupe X | `FRA-CE1-04`, `ce1-texte-narratif`, a A | 3.1 |
| `Q-P2`, groupe Y | `FRA-CE1-10`, `ce1-phrases-types`, et `FRA-CE1-15`, `ce1-accord-groupe-nominal`, a A'. `FRA-CE1-24` attend le palier 2 | 3.2 |
| Affectations et prix | **toutes confirmees** aux prix de `composition-palier-1.md` (A 2,5 ; A' 3,0 ; B 2,5 ; somme 8,0) ; aucun lot refuse ni reduit | 4 |
| Bornes | tenues (8,0 ; 5,0 par instance ; trois lots au plus ; 0,5 de marge par instance) | 5 |

**Mon propre choix, `-04`, `-10`, `-15`, coincide avec le sien ; son motif est neuf et est le sien** : je n'avais pris que l'ordre du referentiel (`composition-palier-1.md`, section 3, point 4). Le motif retenu est celui de son arbitrage, sections 3.1 et 3.2.

### A.59 Ce que cet amendement ferme, ouvre et remplace

| Point | Etat | Remplace |
|---|---|---|
| `Q-P1`, `Q-P2` de `composition-palier-1.md` | **fermees** (A.58) | section 5 de ce fichier |
| Affectations d'instance du palier 1 | **confirmees par `nadia-prof`** ; elles restent des lignes de composition, engagees seulement par `planning.md` | *"proposee"* dans `composition-palier-1.md`, section 2 (le fichier porte la mise a jour datee en section 7, rien n'y est reecrit) |
| Premier report de A.56 (lots sans story) | **solde** : trois stories ecrites, `S57-BR-FRA-CE1-04`, `-10`, `-15` (backlog, annotation du meme jour) | A.56, *"premier report de ce poste"* : un geste a l'autre, aucun sprint ecoule |
| Glissement de `S54-OBS-SCI-CONSIGNE` du palier 1 au palier 2 | `nadia-prof` : un glissement de palier n'est pas un report *"au sens de ma porte"* (arbitrage, section 4) ; le compte des reports est a `camille-po` et `sofia-sm`. **Ma lecture : le compte reste a deux.** Un glissement entre deux paliers de s57 n'est pas un report de sprint ; l'acte ecrit d'abandon se prend a la cloture de s57 si la story n'est pas livree et rejouee (A.34, A.44, inchange). **Elle vaut sauf avis contraire de `sofia-sm` au planning** | la question de `composition-palier-1.md`, section 4, derniere ligne |
| `ce1-familles-de-mots`, item *"livre"* | route a `camille-po` par `nadia-prof` (6.3, section 8) : story `S57-FRA-FAMILLES-LIVRE` ecrite, **non engagee**, sans prix, anterieure au branchement de `FRA-CE1-24` (palier 2) | - |

### A.60 Les stories du palier 1

Trois stories de branchement au backlog, criteres verifies contre l'existant le 2026-10-14 : `S57-BR-FRA-CE1-04` (A), `S57-BR-FRA-CE1-10` et `S57-BR-FRA-CE1-15` (A'). Elles portent les points d'audit de `nadia-prof` (section 6 de son arbitrage) **avec ses mots**, sans les traduire en une propriete plus commode : `ce1-texte-narratif` en porte trois (6.1), les deux paquets de A' un seul, a recompter (6.2). **Ce que la verification contre l'existant a donne** :

- Rouges aujourd'hui : l'entree de catalogue (0 pour les trois noms), le test de paquet (0 fichier), `planning.md` (absent), les rapports de developpeur (absents), la mesure de la majuscule (2 items), celle de l'indice (1 indice).
- **Vertes aujourd'hui, ecrites comme clauses de non-regression et non comme correctifs** : les bornes `LON-CE1-3` et `LON-CE1-4` sur les vingt textes (au plus 22 mots, 4 phrases, 10 mots par phrase) ; les proportions de `TPH-CE1-1` (15 items de type, 5 par type, 9 de reconnaissance) et de `GNA-CE1-1` (21 groupes a adjectif, 12 en -x, 6 en -e, 6 en -s) ; la clause du garde-fou de l'accent. Ce qui manque et que les stories demandent est le **garde-fou** qui les recompte, ou une ligne *"non garde"* au rapport.
- **Cibles non ecrites** : pour les points 6.1 (1) et (2), `nadia-prof` ecrit *"Je le jugerai en jouant"* ; je n'invente pas de cible. `Q-B1`.

### A.61 Ce qui reste pour que `DEC-0120` passe de `Proposee` a `Acceptee`

**Inchange (A.55).** Etat mesure : `grep -n "Statut" squad/sprints/s57-version-cp-ce1-cm1-trois-instances/duree-et-paliers.md` rend toujours *"proposition de `sofia-sm`"*. La borne est ecrite ; la duree ne l'est pas comme decidee. **`DEC-0120` reste `Proposee`.**

### A.62 Routage, statuts, ce que cet amendement ne fait pas

| A qui | Quoi |
|---|---|
| `sofia-sm` | La duree ecrite comme decidee et l'amendement de `squad/README.md` section 4 (A.55) ; `planning.md` du palier 1 ; **les developpeurs des trois stories de branchement** ; la marge de B nommee a l'ouverture ; sa lecture du glissement de `S54-OBS-SCI-CONSIGNE` (A.59) ; `Q-B1` et `Q-B2` a `nadia-prof` |
| `nadia-prof` | `Q-B1` : pour les points 6.1 (1) et (2) de `ce1-texte-narratif`, correction avant le branchement (cible 0 sur les deux mesures) ou valeur consignee et jugee au jeu ? `Q-B2` : l'indice qu'elle cite en 6.3 (*"Un mot de la même famille garde un morceau du mot."*) figure sur cinq items de `ce1-familles-de-mots` mais pas sur l'item *livre*, qui porte *"Cherche le mot qui parle de la même chose."* ; est-ce bien l'indice qu'elle visait ? |
| `theo-archi` | Rien de neuf |
| Utilisatrice | Aucune question neuve |

- **Statuts de stories** : `S57-BR-FRA-CE1-04`, `-10`, `-15` ecrites, **non engagees** (aucun `planning.md`) ; `S57-FRA-FAMILLES-LIVRE` ecrite, non engagee. Aucun autre statut ne change. **Matrice : 142 / 200, aucun statut.**
- **Ne fait pas** : n'ouvre pas s57 ; ne passe pas `DEC-0120` a `Acceptee` ; n'engage aucune ligne ; ne designe aucun developpeur ; ne prononce aucun Done et ne juge aucun contenu (les points d'audit sont ceux de `nadia-prof`) ; ne touche ni catalogue, ni paquet, ni code, ni fiche ; ne dit pas que la CI distante est verte.
- **Jobs de `ci.yml` lances pour son commit** : nommes, avec leurs sorties, dans le message de livraison (`chemins`, `prefixes`). `build` et `outils` ne sont pas lances : le commit ne modifie ni code, ni contenu, ni outil, et je n'en deduis pas le resultat.
