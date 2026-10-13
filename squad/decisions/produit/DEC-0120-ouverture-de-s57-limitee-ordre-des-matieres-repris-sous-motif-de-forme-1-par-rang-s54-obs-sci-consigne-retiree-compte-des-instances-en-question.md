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
