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
- **Statut** : **Acceptee le 2026-10-14 (amendement A.77)**, `Proposee` du 2026-10-13 au 2026-10-14 ; le texte d'origine de cette ligne, qui decrit l'etat du 2026-10-13, n'est pas reecrit. Ses decisions portent un motif de forme 1 ou 2 et s'appliquent des maintenant ; une question a
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

---

## Amendement du 2026-10-14, neuvieme (`camille-po`, apres `planning.md` du palier 1 et les questions qui me sont routees)

Annotation datee. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace est nomme en A.66 et A.73. `planning.md` existe sur `round-two` (`3a3ea97e`) et s57 est ouvert sur la confirmation de l'utilisatrice que ce planning cite, pas sur l'acceptation de cette decision. **Aucun fichier de matrice n'est touche** (`git diff round-two --stat -- squad/programme/` : vide) : 142 / 200 inchange. Aucun catalogue, aucun paquet, aucun code, aucune fiche. Les criteres des stories sont dans l'annotation du meme jour du backlog ("neuvieme") ; cet amendement porte les decisions, pas les criteres.

### A.63 Mesures de cet amendement

Arbre `round-two` a `3a3ea97e`, worktree `s57-camille-q`, 2026-10-14.

| Commande | Sortie |
|---|---|
| `grep -c "^  - id:" adapters/adapter-content/src/main/resources/catalogue.yaml` | 146 |
| `grep -c "id: bandit-manchot-rapprocher-deux-oeuvres-cp$"` sur le meme fichier | 0 |
| `grep -c "REFUSE" squad/conformite/FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md` | 2 |
| `grep -c "puis réponds à la question"` sur `cm1-sciences-extraire-information.yaml` | 12 |
| `git merge-base --is-ancestor` de `729b0d8a`, `7a34366c`, `bac0a6d1` sur `HEAD` | 0, 0, 0 |
| comptage de `LON-CE1-3` et `-4` sous trois regles de mots et trois de phrases (script hors depot) | au plus 22, 23, 23 mots par texte ; 4, 4, 4 phrases ; 10 mots par phrase |
| commande de paires de `S54-OBS-SCI-CONSIGNE` sur trois copies du fichier hors depot | `12 0 0`, `11 1 0`, `0 0 0` |

**Ce que je n'ai pas fait** : lancer le reacteur (`mvn`), joue un jeu, lu la CI distante. Aucun test n'est cite comme vert par moi ; les chiffres de tests sont ceux de `yanis-qa` sur `544159d9`, nommes comme tels.

### A.64 `S57-ARB-PALIER-1` : le poste 1 a une story (`Q-S3`, premiere partie)

**Decision : je l'ecris.** `PAR-16` demande une story par poste ; le livrable de `nadia-prof` est sur le disque, la part restante est sa lecture des items avant son premier lot, qui a un observable (une ligne datee par paquet, avec le nombre d'items lus). Competence : transverse. Prix : aucun, pris sur le 0,5 de l'arbitrage de chaque instance. Criteres dans le backlog ; **la forme de la ligne est la sienne** et le critere s'amende si elle l'ecrit autrement. Rien n'est ecrit sur ce qu'elle lit ni sur ce qu'elle conclut.

### A.65 Identifiant de l'entree de B2 (`Q3` de `theo-archi`)

**Decision : l'entree neuve reprend l'identifiant `bandit-manchot-rapprocher-deux-oeuvres-cp`.** Motifs de forme 1 :

1. **Le refus reste attache au jeu.** La fiche porte ce nom (`FICHE-bandit-manchot-<id>.md`) et son verdict `REFUSE` du 2026-10-11 (2 occurrences). Un identifiant neuf ouvrirait une fiche sans refus : le contenu refuse reviendrait au catalogue sous un nom qui n'a jamais ete refuse. C'est la famille du defaut de `DEC-0009` : un contenu refuse que rien n'identifie plus comme refuse.
2. **B2 est l'inverse de `13d00bfe`** sur trois fichiers (avis de `theo-archi`, 3.2) ; avec le meme identifiant, `R1` compare six lignes identiques a celles retirees, et l'inventaire et le garde-fou 4 suivent un nom qu'ils connaissent.
3. **`DEC-0116` A.17 dit "entree neuve", pas "identifiant neuf"** : la regle porte sur le moment (apres un Done qui n'est pas `REFUSE`), pas sur le nom.

**Ce que ce choix coute** : l'identifiant d'un jeu retire puis rejoue est le meme ; la matrice et `REGISTRE-identifiants.md` (a `nadia-prof`) n'en changent pas. Je n'ouvre pas de fiche : elle existe. **R3 de l'avis est repondue.** `theo-archi` n'a rien a amender (l'avis dit que les deux tiennent techniquement).

### A.66 Ce qu'est un report, et quand il se compte (`Q-S2`, premiere partie)

**Remplace** la condition de `DEC-0120` 4.2 : *"si le correctif n'est pas fusionne quand le rejeu devrait commencer, il glisse : premier report de ce poste"*, et la position de `planning.md` (section "Compte des reports") qui n'en compte aucun. **Motif** : *"quand le rejeu devrait commencer"* n'a pas de date. Sans date, la condition ne se declenche jamais, et le compte disparait : c'est ce que `sofia-sm` ecrit du glissement entre paliers, et c'est le mecanisme de `DEC-0007` (trois sprints, un motif defendable chaque fois).

**Regle.** Un poste **retenu par la composition d'un palier** et **non engage a l'ouverture de ce palier** est un report, compte a l'ouverture, **quelle qu'en soit la cause**. Precedent : `DEC-0112` section 6 (les postes de s52 non engages a s53 : "second report") et `DEC-0115` A.10. Un poste engage plus tard dans le palier par amendement date de `planning.md` reste compte : l'engagement tardif n'efface pas le report, il evite le suivant. Le compte n'est pas un reproche : il a pour seul effet la limite de A.67.

### A.67 Postes 7 et 8 : reports comptes

| Poste | Compte | Ce qui debloque |
|---|---|---|
| 7, lot `ce1-texte-narratif` (`FRA-CE1-04`) | **premier report de ce poste, s57 palier 1, 2026-10-14** | `Q-B1`, a `nadia-prof`, **seule** (`Q-Y6` est repondue par A.71) |
| 8, correction d'`ART-CP-10` | **premier report de la reprise elle-meme, s57 palier 1, 2026-10-14** | `Q1`, `Q2`, `Q-Y5` (`nadia-prof`, `ines-design`) |

**Compte de la ligne `ART-CP-10`** : quatre reports (s50 a s53), trois engagements sans fermeture (s54, s55, s56) et ce report : **huit evenements, aucune fermeture**. `DEC-0120` 4.2 la retenait *"avant tout lot neuf"* ; le planning engage deux lots neufs et pas elle. **Je ne le conteste pas** : ces lots ne prennent rien au 0,5 du poste 8 (il reste dans B, `planning.md` "Capacite du palier 1") et ce qui la bloque est une reponse de `nadia-prof` ou d'`ines-design`, pas une capacite. Le poste 9 (B2) n'est pas un report : il suit le poste 8 et le verdict, il n'est jamais retenu seul.

**Limite.** Un deuxieme report du meme poste (non engage a l'ouverture du palier suivant) s'ecrit de la meme facon. **Un troisieme n'est pas permis** : `DEC-0112` section 6 interdit le troisieme report des postes de s53 ; je l'etends ici aux postes 7 et 8. Au troisieme, j'ecris le jour de l'ouverture du palier un acte, abandon du poste ou question a l'utilisatrice, et ce poste n'entre pas dans la composition du palier sans lui.

### A.68 `S57-ADR-0065-DEUX-INSTANCES`, critere 2 (`Q-Y1`)

**Decision : critere remplace, pas conserve.** *"Un seul commit"* n'est pas tenu (deux commits, test inchange) et je l'avais ecrit avant que la forme soit connue. Nouveau critere : `git merge-base --is-ancestor 729b0d8a HEAD` sort 0 dans le worktree de chaque lot de A'. **Contre l'existant** : vert sur `round-two`, ne mord que dans un worktree de lot de A' qui n'existe pas : une clause de porte, dite comme telle. La story reste livree.

### A.69 `VSJ-CE1-R1`, l'exemple de `nadia-prof` (`Q-Y2`, ma part)

**Decision.** Le critere garde ses mots. Je lis *"par exemple"* comme une illustration ; servi tel quel, l'exemple rend rouge `J1`, qu'elle exige tenue : le critere se contredirait. **Regle du poste** : `J1` et les deux tests nommes par `yanis-qa` restent verts sans modification ; si `marc-dev` ne peut pas, il s'arrete et route a `nadia-prof`, qui amende ou non par un amendement date avant le commit. Je ne decide pas quelle condition s'assouplit : `Q-N1` lui demande si ma lecture est la sienne. **Le poste 3 reste engage.**

### A.70 `S54-OBS-SCI-CONSIGNE`, criteres 1 et 2 (`Q-Y3`)

**Decision : les deux sont remplaces dans leur commande, pas dans ses mots.** Le critere 2 laissait passer une question reecrite sur sa ligne (mesure de `yanis-qa`, refaite par moi sur des copies hors depot : `11 1 0`) ; la commande de paires rend `12 0 0` sur le resultat conforme. Le critere 1 est garde par un test : sans lui, remettre la phrase laissait la CI verte. **Je ne remets pas ma commande a `nadia-prof` comme sa definition** : sa phrase est testable, c'est ma commande qui etait faible ; la seule reserve ecrite est de savoir si *"rien d'autre ne change"* couvre le texte des questions (je le lis ainsi). Les amendements precedent le premier commit de `lea-dev` (porte `P-4`).

### A.71 Trois lots de francais CE1 (`Q-Y6`, ma part)

**Decision.** Regle de comptage de `LON-CE1-3` et `-4` pour le test de paquet : celle de `yanis-qa`. **Je ne la tranche pas par preference : elle ne change aucun verdict** sur les vingt textes de `ce1-texte-narratif` sous trois regles de mots et trois de phrases (A.63) ; les phrases sont a 4, a la borne, jamais au-dessus. Si un texte approche de la borne un jour, la question revient a `nadia-prof`. La propriete de la majuscule s'ecrit en commande sur tous les items (2 aujourd'hui, identique a la mesure limitee a `# ordre`) ; la cible est `Q-B1`. Le module et les classes de test sont nommes. **Les postes 4 et 5 n'etaient pas bloques** ; le poste 7 l'est par `Q-B1` seule.

### A.72 Glissements de palier : une limite par poste (`sofia-sm`, `planning.md`, section "Compte des reports")

**Decision.** Un poste **engage** au palier N et non audite a sa cloture glisse au palier N+1 (`planning.md` D2 ; condition 2 de 11.3 de `nadia-prof`). C'est un glissement, pas un report, comme A.59 le dit. **Un poste glisse au plus une fois.** S'il n'est pas audite a la cloture du palier N+1, le second glissement est un **report compte** au sens de A.66, ecrit par moi le jour de la cloture, et A.67 s'applique : le troisieme est interdit. **Aucune limite n'est posee au nombre de paliers** : l'utilisatrice a repondu *"oui"* a une suite de paliers de 8,0 (A.53) et le sprint s'arrete quand aucun lot instruit ne reste ou quand elle l'arrete (`planning.md` D1, D3). Ce que la limite borne est la duree de vie d'un poste, pas celle du sprint : c'est la reponse a *"la cloture n'a pas de date, l'interdiction du troisieme report ne mord que tard"*.

### A.73 Application a `S54-OBS-SCI-CONSIGNE`

Deux reports comptes (s55, s56) ; le placement n'en est pas un (A.34, A.44). **Un glissement du palier 1 au palier 2 est permis une fois (A.72). A la cloture du palier 2, si la story n'est pas livree et rejouee par `nadia-prof`, c'est le troisieme report, interdit par `DEC-0112` section 6, et l'acte d'abandon s'ecrit ce jour-la.** **Remplace** *"a la cloture de s57"* dans A.34, A.44 et A.59 (la phrase *"l'acte ecrit d'abandon se prend a la cloture de s57 si la story n'est ni livree ni rejouee"*). Si s57 se cloture avant qu'un palier 2 existe, l'acte se prend a cette cloture. La ligne `SCI-CM1-20` reste `couvert-valide` et n'est pas rouverte. Elle est le premier poste de `lea-dev` : la correction d'`ART-CP-10` ne passe pas devant elle (`planning.md`, "Charge des developpeurs").

### A.74 `RP-ACCUEIL-147` et `HS-ACCUEIL-APM-R2` (`Q-S3`, suite)

**`RP-ACCUEIL-147`** : l'instance et le moment sont a `nadia-prof` ; l'instance n'a pas d'effet sur le produit (fichier d'accueil, ni jar ni port, hors du jeu de conflits des instances). **Je fixe l'echeance** : faite avant la cloture du palier 1 ; sinon un glissement, ecrit dans `cloture-palier-1.md` (A.72). Demandee par l'utilisatrice le 2026-10-12 (`DEC-0120` 4.5).

**`HS-ACCUEIL-APM-R2`** : **reste non engagee**. Ce qui m'en empeche : le prix de l'audit est a `nadia-prof` et n'est pas ecrit. **Je fixe** que cet audit ne se prend ni sur les marges de rejeu ni sur les 0,75 non affectes de B, et qu'il entre dans les 8,0 du palier qui engage la story. Aucun developpeur n'est designe avant le prix. Ce n'est pas un report : la story n'a pas ete retenue par la composition du palier 1.

### A.75 Routage

| A qui | Quoi |
|---|---|
| `sofia-sm` | Fusionner l'annotation du backlog et cet amendement **avant** le premier commit de `lea-dev` (`P-4`) ; amender la section "Compte des reports" de `planning.md` (postes 7 et 8 : report, A.67) ; ecrire dans `cloture-palier-1.md` les glissements, les reports et la limite d'A.72 ; `S57-ARB-PALIER-1` comme ligne du poste 1 ; verifier l'ordre du critere 3 de `S57-ARB-PALIER-1` a la cloture |
| `nadia-prof` | `Q-B1` ; `Q-N1` (l'exemple de `VSJ-CE1-R1`) ; `Q-N2` (prix de l'audit d'`APM-R2`) ; `Q-N3` : `Q1`, `Q2`, `Q-Y5`, a repondre dans le palier 1 ; `RP-ACCUEIL-147` ; la ligne de lecture complete de chaque paquet |
| `theo-archi` | Rien de neuf : `R3` est repondue ; `Q-Y4` (juge pur) reste a lui et a `yanis-qa` |
| `yanis-qa` | Ecrire, avec `lea-dev`, le test du critere 1 de `S54-OBS-SCI-CONSIGNE` et, avec `marc-dev`, le comptage de `VSJ-CE1-R1` |
| Utilisatrice | Aucune question neuve. A lire : le compte d'`ART-CP-10`, huit evenements sans fermeture (A.67) |

### A.76 Statuts, ce que cet amendement ne fait pas

- **`DEC-0120` reste `Proposee` dans cet amendement** ; son passage est le sujet du suivant, ecrit a part.
- **Statuts de stories** : `S57-ARB-PALIER-1` ecrite, engagee (poste 1) ; `S57-ADR-0065-DEUX-INSTANCES` livree, critere 2 remplace ; les postes 7 et 8 non engages, reports comptes ; `HS-ACCUEIL-APM-R2` non engagee. **Matrice : 142 / 200, aucun statut.**
- **Ne fait pas** : ne prononce aucun Done, ne juge aucun contenu, ne touche ni `domain/`, `application/`, `adapters/`, `bootstrap/`, ni catalogue, ni paquet, ni fiche ; ne dit pas que la CI distante est verte ; ne decide rien de technique (le moyen de B2, le juge de pli, la forme de la correction restent a `theo-archi` et `ines-design`).
- **Jobs de `ci.yml` lances pour son commit** : nommes dans le message de livraison (`chemins`, `prefixes`). `build` et `outils` ne sont pas lances : le commit ne modifie ni code, ni contenu, ni outil, et je n'en deduis pas le resultat.

---

## Amendement du 2026-10-14, dixieme (`camille-po`, passage de `Proposee` a `Acceptee`)

Annotation datee. **Rien au-dessus n'est reecrit**, a une exception nommee : la ligne `Statut` de l'en-tete porte desormais le nouveau statut, avec l'ancien. Meme geste que `DEC-0116` A.37. **Aucun fichier de matrice, de catalogue, de paquet, de code ni de fiche n'est touche.**

### A.77 Motif du passage a `Acceptee`

**Condition que j'avais ecrite** (A.45, A.55) : *"quand la duree et la borne de s57 sont ecrites (`sofia-sm`, `nadia-prof`)"*, et A.55 : *"je la passe, dans un amendement date, quand la ligne de `sofia-sm` est dans le depot"*. Etat mesure le 2026-10-14 sur `round-two` a `829db5eb` (cet amendement s'ecrit sur le commit de A.63 a A.76) :

| Element | Commande | Sortie |
|---|---|---|
| Borne : 8,0 par palier, 5,0 et trois lots par instance | `grep -n "^### 11.3" squad/conformite/ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md` | ligne 516, *"La borne d'un sprint plus long : 8,0 par palier, un palier clos avant le suivant"* (A.55 : ecrite) |
| Duree ecrite comme decidee | `grep -n "^- \*\*D[1-5] " squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md` | cinq lignes, D1 a D5, lignes 45 a 49 ; la ligne 43 dit : *"Ce paragraphe est la ligne datee de duree que `DEC-0120` A.55 demande"* |
| Amendement de `squad/README.md` section 4 (`Q-S1`) | `grep -n "Un sprint a paliers a une seule" squad/README.md` | ligne 166 |
| `Q-U1` | amendement A.1 | trois instances A, A' et B, mots de l'utilisatrice forme 2 |

**La condition est remplie sur ses termes ecrits.** La condition d'origine de l'en-tete (*"`Q-U1` a une reponse et la retraite de `S54-OBS-SCI-CONSIGNE` n'est pas contredite"*) a ete remplacee par A.37 puis A.45 ; la retraite a ete remplacee par un placement (A.34) sur la reponse de l'utilisatrice.

**Ce sur quoi l'acceptation repose, et ce sur quoi elle ne repose pas.**

- Elle repose sur A.55 (forme 1 : un fichier du depot, une commande) et sur les mots de l'utilisatrice en forme 2 deja consignes (*"oui plus que 8"*, *"oui"*, *"ok"*, *"on plannifie A A' et B"*, A.1, A.38, A.53), **que je n'ai pas lus de sa main** (meme reserve que partout dans cette decision).
- Elle ne repose **pas** sur A.63 a A.76 de ce jour (report compte a l'ouverture, limite d'un glissement par poste, identifiant de B2) : ces points sont de ma main, motif de forme 1, sans mots de l'utilisatrice. Si elle les conteste, un amendement les remplace sans toucher au statut.
- Elle ne dit pas que la CI distante est verte, ni qu'un lot est valide : aucun Done n'est prononce.

### A.78 Routage, ce que cet amendement ne fait pas

`DEC-0120` etait deja appliquee dans ses decisions (en-tete : *"s'appliquent des maintenant"*) ; le statut ne change aucune affectation. **Statut : `Acceptee` le 2026-10-14.** `DEC-index.md` suit. Ne fait pas : ne touche aucun poste, aucune story, aucune ligne de matrice ; ne prononce aucun Done.

---

## Amendement du 2026-10-14, onzieme (`camille-po`, apres les decisions `D1` a `D7` de `nadia-prof` sur les postes 7 et 8)

Annotation datee, apres 18:24 (UTC+02:00). **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace est nomme en A.80. **Le statut ne change pas : `Acceptee` (A.77).** Source : `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/lecture-nadia-palier-1.md` (`7161ab69`, fusionne `6ca84317`). Les criteres sont dans l'annotation du meme jour du backlog ("onzieme") ; cet amendement porte la consignation, pas les criteres. Ce sont des decisions de `nadia-prof`, consignees sans etre jugees ; aucun mot de l'utilisatrice n'est en jeu.

### A.79 Mesures de cet amendement

Arbre `round-two` a `6ca84317`, worktree `s57-camille-amend`, 2026-10-14. Scripts hors depot ; le premier est repris dans le backlog.

| Commande | Sortie |
|---|---|
| `git rev-parse --short HEAD` (worktree, avant ecriture) | `6ca84317` |
| propriete de la majuscule (script du backlog, `S2`) sur `ce1-texte-narratif.yaml` | 2 |
| meme script sur une copie sans majuscule pour Sara et Nour | 0 |
| meme script sur cette copie avec une bonne reponse `Élise pèle une pomme` ; ancien `awk` de `S2` | 1 ; 0 |
| `grep '^ *indice:' ... \| grep -vcE 'indice: "(Relis\|Cherche)'` (`S3`) | 1 |
| `grep -c "Pense à ce qu'on fait avant de couper un fruit"` (meme fichier) | 1 |
| comptage de `LON-CE1-3` et `-4`, regle de s47 : mots, phrases, mots par phrase, maximum sur 20 textes | `22 4 10` |
| meme comptage, texte a 5 phrases ; texte a 13 mots dans une phrase (copies) | `22 5 10` ; `22 4 13` |
| propositions absentes de leur texte, sur le paquet ; sur la copie corrigee | `0 60` ; `0 60` |
| `grep -c 'celle-ci'`, `grep -c 'ces deux'`, `grep -c 'explication:'` sur `cp-rapprocher-deux-oeuvres.yaml` | 6, 6, 6 |
| `grep -c "id: bandit-manchot-rapprocher-deux-oeuvres-cp$"` sur `catalogue.yaml` | 0 |

**Ce que je n'ai pas fait** : lancer `mvn`, jouer un jeu, mesurer une cote dans un navigateur (le jeu n'est pas servi), lire la CI distante. Les cotes 676, 568 et 656 sont celles du jar de s56, reprises de `nadia-prof` et d'`ines-design`. Le rouge de `S56-RDO-CP-R2` est etabli par lecture de `PartieController.retourModele`, pas par un test lance.

### A.80 Poste 7 : `S57-BR-FRA-CE1-04` amende (`D1`, `D2`, `D3`)

- **`D1`, la majuscule : correction avant le branchement, cible 0 sur les 20 items** (aujourd'hui 2), majuscule Unicode. **`D2`, l'indice de Sara : correction avant le branchement, cible 0** sur la mesure de `S3` (aujourd'hui 1). Ses mots, qui remplacent *"je le jugerai en jouant"* : la propriete se decide sur le fichier.
- **`D3`, la regle de comptage de `LON-CE1-3` et `-4` est celle de s47** (`ARBITRAGE-s47-longueur-du-texte-support-au-ce1.md`, section 2). **Remplace A.71**, qui donnait celle de `yanis-qa`. Le motif d'A.71 (*"elle ne change aucun verdict"*) reste exact, il ne suffisait pas : `nadia-prof` a une regle ecrite, c'est elle qui fait foi. Les deux se separent sur une phrase qui s'ouvre par un chiffre ou une minuscule.
- Le YAML du paquet entre dans la liste de `K2` (la clause *"seulement si `nadia-prof` decide une correction"* joue) ; `S6` ajoute une clause de non-regression (aucune proposition hors du texte).
- **Le poste n'est pas engage par cet amendement.** Reste : l'amendement de `planning.md` par `sofia-sm` (`P-5`) et le developpeur a designer. Il ne manque rien de la part de `nadia-prof` ni de la mienne (sa section 4.1).

### A.81 Poste 8 : `S56-RDO-CP-R1` (`D5`)

`RDO-CP-R1` se leve dans le meme rejeu que le refus. **La forme vise 125 px a 744 x 551** (`.indice`, aujourd'hui a 676) **et 17 px a 992 x 551** (pastille, aujourd'hui a 568). **La barre du refus reste 105 px** (`.reponse-revelee`, 656), sur son seul critere : 125 est la cible de la forme, pas une barre relevee du verdict ; si le rejeu montre la phrase de `M4` au-dessus du pli et l'indice dessous, le refus est leve et `RDO-CP-R1` reste une reserve non bloquante. **Je ne decide pas la forme visuelle des 125 px** : elle revient a `ines-design` (dans `C1` a `C4` de `theo-archi`), jugee par `nadia-prof` sur `M1`, `N1` a `N6`, la regle 6 et `C4` de s55, sans cote de vehicule. Prix du rejeu inchange : 0,5.

### A.82 Poste 8 : `S56-RDO-CP-R2` (`D6`, `D7`)

**Voie (b)** : sur l'ecran de fin d'un jeu dont la bonne reponse est une reproduction, ni la question rappelee ni l'explication ne sont posees, que la derniere manche soit juste ou revelee. (a) et (c) sont ecartees par `nadia-prof`, motifs dans sa section 1.5. **Critere : liste fermee des textes permis** (`D7`), pas une liste de mots interdits : `Les jeux` ; `Tu as trouvé, tout seul :` ; `Tu as joué toutes les questions :` ; les deux nombres du score ; `Bravo, c'est juste !` ; `Tu n'as pas trouvé.` ; `C'est fini !` ; aucun texte de l'item. Le contenu du paquet ne change pas (`celle-ci` : 6, doit rester 6). Amendements d'`ADR-0104` (decision 4, point 5) et d'`ADR-0084` (`REV-3`) : `theo-archi`, dans le commit du code. **Ce que `nadia-prof` garde seule** : ce que ces textes disent a un enfant de CP.

### A.83 Ce que cela change aux comptes de A.67 : rien

A.66 compte un report **a l'ouverture** du palier et dit : *"l'engagement tardif n'efface pas le report, il evite le suivant."* Les postes 7 et 8 ne sont pas engages a cette ouverture : le premier report de chacun (s57, palier 1, 2026-10-14) **reste ecrit**, y compris si `sofia-sm` les engage plus tard dans le palier. **La ligne `ART-CP-10` garde ses huit evenements sans fermeture** (A.67) tant qu'un rejeu ne l'a pas fermee. Une fois engage, un poste non audite a la cloture glisse selon A.72.

### A.84 Ce qui bloque encore l'engagement

| Poste | Reste a faire | A qui |
|---|---|---|
| 7 | amendement date de `planning.md` (`P-5`) ; developpeur designe | `sofia-sm` |
| 8 | proposition de forme des pixels de `RDO-CP-R1` ; seconde moitie de `Q-Y4` (juge pur sur le releve) ; amendement date de `planning.md` (`P-6`) ; `C1` a `C4` au rapport du developpeur | `ines-design` ; `theo-archi`, `yanis-qa` ; `sofia-sm` ; developpeur |
| 9 (B2) | poste 8 fusionne ; `Q3` repondue (A.65) | `sofia-sm` |

### A.85 Routage

| A qui | Quoi |
|---|---|
| `sofia-sm` | Fusionner l'annotation du backlog et cet amendement **avant** le worktree du poste 7 (`P-5`) et celui du poste 8 (`P-6`) ; amender `planning.md` pour engager chaque poste quand ses portes sont remplies, en nommant le developpeur ; l'entree `S56-VSJ-CE1-R1` du backlog dit encore `Q-N1` ouverte alors que `D4` y repond |
| `nadia-prof` | `Q-C1` : `D7` couvre-t-elle les attributs (`alt`, `title`, `aria-label`) du fragment `retour` en plus des noeuds de texte ? |
| `ines-design` | Proposer d'ou viennent les 125 px et les 17 px, sur les quatre points de `nadia-prof` (backlog, `S56-RDO-CP-R1`, point 3) |
| `theo-archi` | Amendements d'`ADR-0104` et d'`ADR-0084` dans le commit du code ; seconde moitie de `Q-Y4` |
| `yanis-qa` | Le test de `D1` (majuscule Unicode), la regle de s47 pour `LON-CE1-3` et `-4`, la liste fermee de `D7` ; juge pur du releve avec `theo-archi` |
| Utilisatrice | Aucune question neuve. A lire : la ligne `ART-CP-10` compte toujours huit evenements (A.83) |

### A.86 Statuts, ce que cet amendement ne fait pas

- **`DEC-0120` reste `Acceptee`.** Statuts de stories : `S57-BR-FRA-CE1-04` amendee, non engagee ; `S56-RDO-CP-R1` et `S56-RDO-CP-R2` amendees, non engagees ; la correction d'`ART-CP-10` reste `non couvert`. **Matrice : 142 / 200, aucun statut** (`git diff round-two --stat -- squad/programme/` : vide).
- **Ne fait pas** : ne prononce aucun Done, ne juge aucun contenu, ne choisit aucune forme visuelle, ne touche ni `domain/`, `application/`, `adapters/`, `bootstrap/`, ni catalogue, ni paquet, ni fiche ; ne dit pas que la CI distante est verte.
- **Jobs de `ci.yml` lances pour son commit** : nommes dans le message de livraison. Ceux qui ne sont pas lances ne sont pas deduits.

---

## Amendement du 2026-10-14, douzieme (`camille-po`, apres le rejeu de `nadia-prof` au palier 1)

Annotation datee, 21:53 (UTC+02:00). **Rien au-dessus n'est reecrit.** **Le statut ne change pas : `Acceptee` (A.77).** Sources : `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/rejeu-nadia-palier-1.md` et ses amendements du 2026-10-14, les fiches de `FRA-CE1-10`, `-13` et `-15`. Les criteres sont dans l'annotation du meme jour de `docs/backlog/backlog.md` (*"Reserves du palier 1 de s57 a solder"*) ; cet amendement porte la consignation, pas les criteres.

### A.87 Ce que cet amendement consigne

1. **`Q-N4` = non** (`rejeu-nadia-palier-1.md`, amendement du 2026-10-14). Reponse de l'utilisatrice en forme 2, relayee par l'agent principal, **que je n'ai pas lue de sa main**. Le pli a 744 x 551 ne redevient pas bloquant pour un jeu neuf ; la recommandation *"oui pour la suite"* de `nadia-prof` n'est pas retenue.
   - **Ecart de premisse, a dire.** La section 8 du rejeu pose la question sur `DEC-0051` (*"les jeux neufs naissent sans le defaut"*). `DEC-0053`, arbitrage 1 (utilisatrice, 2026-08-28, `Acceptee`), **remplace** `DEC-0051` sur ce point : *"oui, pour tout le catalogue, paquets neufs de s22 compris"*, le pli devient une reserve non bloquante et la dette est *"explicitement croissante et assumee"* (`grep -n "Arbitrage 1" DEC-0053-*.md` : ligne 32). La reponse *non* redit le regime que `DEC-0053` pose depuis le 2026-08-28 et que `nadia-prof` applique depuis (`RDO-CP-R1`, s56), et l'etend sans ambiguite aux lots de s57 ; **aucune direction ne change**. Je ne conteste rien sur le fond pedagogique : c'est une premisse d'intention produit, routee a `sofia-sm` (point 5).
2. **Trois stories de reserve ecrites, non engagees** : `S57-TPH-CE1-R1`, `S57-GNA-CE1-R1`, `S57-VSJ-CE1-R2`. **Ce qui est decide ici** : leur ecriture et leur ordre (TPH, puis GNA, puis VSJ ; motif produit au backlog). **Ce qui n'est pas decide ici** : le changement de code. Il n'a pas de proprietaire tant que `sofia-sm` ne l'a pas engage au planning d'un palier avec un developpeur ; **jusqu'a cet engagement, rien n'est applique et le regime est celui de `DEC-0053`**. Palier cible propose : 2, prix proposes a `nadia-prof` (`Q-R1`). Aucun report : premiere inscription des trois.
3. **`RP-ACCUEIL-147`, etat verifie sur `round-two` a `f95736b5`** (echeance : cloture du palier 1, A.74).

| Commande | Sortie |
|---|---|
| `git rev-parse --short HEAD` (worktree, avant ecriture) | `f95736b5` |
| `grep -n 147 squad/conformite/ARBITRAGE-PREALABLE-accueil-par-matiere.md` | lignes 41, 236 et 398 |
| `git log --oneline -1 -- squad/conformite/ARBITRAGE-PREALABLE-accueil-par-matiere.md` | `90904751` (aucun commit depuis la story) |
| `git diff --quiet round-two...<branche> -- <le fichier>` sur les 168 branches locales | 167 sans difference ; `main` : *"no merge base"*, branche orpheline, sans objet |
| `grep -c "^  - id:" adapters/adapter-content/src/main/resources/catalogue.yaml` | 148 |
| `grep -c 147 .../rejeu-nadia-palier-1.md` | 0 (l'instance et le moment demandes par `Q-S3` ne sont pas ecrits) |

   **Constat** : l'echeance n'est pas tenue a ce jour et n'est pas encore depassee (le palier 1 n'est pas clos : `cloture-palier-1.md` est un squelette). **Le critere 1 de la story etait faux tel qu'ecrit** : il demandait de remplacer 147 par 146, et le catalogue en compte 148 depuis les fusions `c935d764` et `d67204de`. Je l'amende au backlog (date et compte du jour de la correction). **Reste a faire** : `nadia-prof` corrige ou annote les lignes 41 et 236 de son fichier (commit `[NAD]`, ni jar ni port) ; `sofia-sm` ecrit, dans la section 2 de `cloture-palier-1.md`, *fait* avec le SHA ou *glissement* avec date et cause (un seul permis, A.72). Ce n'est pas un report : aucun glissement n'est ecrit.
4. **Matrice, ecart constate, non corrige ici** : `FRA-CE1-10` (ligne 1432) et `FRA-CE1-15` (ligne 1437) sont `non couvert`, alors que `nadia-prof` a prononce le Done pedagogique le 2026-10-14 et que `1f6642ab` et `72931c94` sont ancetres de `round-two` (`git merge-base --is-ancestor` sort 0 pour les deux). Son routage (section 9 et amendement de 20:40) m'en donne la charge. Cet amendement ne les passe pas : la tache qui l'a produit ne le demandait pas ; **question posee a l'agent principal**. Matrice : 142 / 200, inchangee (`git diff round-two --stat -- squad/programme/` : vide).
5. **Routage** :

| A qui | Quoi |
|---|---|
| `sofia-sm` | Les trois stories pour le planning d'un palier 2 (D2) ; la ligne `RP-ACCUEIL-147` de `cloture-palier-1.md` ; la premisse de `Q-N4` (point 1) a `nadia-prof`, si elle veut l'ecrire dans sa section 8 |
| `nadia-prof` | `Q-R1` (prix, instances, palier), `Q-R2` (la revelation fait-elle partie de la levee), `Q-R4` (jeux de non-regression) ; `RP-ACCUEIL-147` |
| `theo-archi`, `ines-design` | `Q-R3` : une forme commune leve-t-elle les trois reserves, avant la composition du palier 2 |
| `yanis-qa` | Rien a tirer en test du reacteur : la position de l'indice n'a pas d'automate ; il relit les criteres 3 et 4 de `S57-TPH-CE1-R1` |
| Utilisatrice | Aucune question neuve |

### A.88 Ce que cet amendement ne fait pas

- **`DEC-0120` reste `Acceptee`.** Ne fait pas : ne prononce aucun Done, ne juge aucun contenu, ne choisit aucune forme, ne touche ni `domain/`, `application/`, `adapters/`, `bootstrap/`, ni catalogue, ni paquet, ni fiche, ni matrice, ni `planning.md`, ni `cloture-palier-1.md` ; ne dit pas que la CI distante est verte.
- **Statuts de stories** : `S57-TPH-CE1-R1`, `S57-GNA-CE1-R1`, `S57-VSJ-CE1-R2` ecrites, non engagees ; `RP-ACCUEIL-147` ouverte, critere 1 amende. Aucun autre statut ne change.
- **Jobs de `ci.yml` lances pour son commit** : nommes dans le message de livraison ; ceux qui ne sont pas lances ne sont pas deduits.

---

## Amendement du 2026-10-14, treizieme (`camille-po`, matrice apres les verdicts du palier 1)

Annotation datee. **Rien au-dessus n'est reecrit.** **Le statut ne change pas : `Acceptee` (A.77).** Reponse a `Q-A1` de l'agent principal (A.87, point 4) : mettre la matrice a jour, en ne tranchant que ce que ses regles tranchent.

### A.89 Ce que les regles de la matrice tranchent, et l'etat des fiches

La section "Qui la met a jour" fait passer une ligne a `couvert-valide` quand `nadia-prof` a rendu un verdict `VALIDE` ; la phrase qui renvoie une ligne a `en cours` vise un statut remis en cause par une reserve non levee, pas une fermeture sous reserve non bloquante (precedent ecrit dans la note du 2026-10-08, s54 : `FRA-CE1-19`, `FRA-CE1-25`, puis `FRA-CE1-13`, `FRA-CE1-14`). **Ce qui decide de la fermeture est la phrase de la fiche** : *"`camille-po` peut passer `FRA-CE1-NN` a `couvert-valide`, sans mention de couverture partielle"*. Je n'ai fait passer que les lignes dont la fiche l'ecrit.

| Verification | Commande | Sortie |
|---|---|---|
| Verdict de `FRA-CE1-10` | `grep -n "^### Verdict du 2026-10-14" FICHE-bandit-manchot-phrases-types-ce1.md` | ligne 22, **VALIDE SOUS RESERVE** ; ligne 24 : *"`camille-po` peut passer `FRA-CE1-10` a `couvert-valide`, sans mention de couverture partielle, apres la fusion de `1f6642ab`"* |
| Verdict de `FRA-CE1-15` | idem, `FICHE-bandit-manchot-accord-groupe-nominal-ce1.md` | ligne 22, **VALIDE SOUS RESERVE** ; ligne 24 : meme phrase, *"apres la fusion de `72931c94`"* |
| Condition de fusion | `git merge-base --is-ancestor 1f6642ab round-two` ; idem `72931c94`, `7dc40fb0` | sort 0 pour les trois |
| `FRA-CE1-13` | `grep -n "^### Rejeu du 2026-10-14" FICHE-bandit-manchot-verbe-sujet-ce1.md` | ligne 117 : `VSJ-CE1-R1` **levee**, statut **VALIDE SOUS RESERVE**, reserve neuve `VSJ-CE1-R2` ; la ligne etait deja `couvert-valide`, seule sa cellule change |
| `SCI-CM1-20` | idem, `FICHE-bandit-manchot-sciences-extraire-information-cm1.md` | ligne 112 : `S54-OBS-SCI-CONSIGNE` **VALIDE** ; la ligne n'est pas rouverte, rien ne change |

Autres lignes dont la matrice compte un `VALIDE SOUS RESERVE` comme couvert : aucune n'a de verdict au palier 1. `ART-CP-10` reste `non couvert` (correction non engagee, A.84). Les lignes du palier 1 sans verdict (`FRA-CE1-04`, poste 7) ne bougent pas.

| | Avant (`98362908`, `round-two` a `8d106f4d`) | Apres |
|---|---|---|
| `couvert-valide` | 142 | **144** |
| `en cours` | 4 | 4 |
| `non couvert` | 54 | **52** |
| `non couvrable en l'etat` | 77 | 77 |
| Total | 277 | 277 |
| Denominateur atteignable | 200 | **200** |
| Taux | 142 / 200 = 71,00 % | **144 / 200 = 72,00 %** |

Commande : celle de la "Methode du compte" de la matrice (`awk -F'|'`, lignes de donnees, aucune mesure partielle), lancee avant et apres l'edition sur le meme fichier. `git diff 8d106f4d --stat` : un seul fichier, `MATRICE-couverture.md`, 3 lignes de tableau modifiees (`FRA-CE1-10`, `FRA-CE1-13`, `FRA-CE1-15`) plus la note.

**Le maximum de la version, fige a 200 (A.38, A.46 a A.52), est inchange** : ni referentiel neuf, ni ligne classee `non couvrable en l'etat` (77 des deux cotes). Compteur de version (A.32) : 148 / 200 = 74,00 % par la formule (144 + 4), son critere 2 (CI verte sur l'arbre de la version) restant a lire sur cet arbre.

### A.90 Ce que cet amendement ne fait pas

- **`DEC-0120` reste `Acceptee`.** Ne fait pas : ne prononce aucun Done, ne juge aucun contenu, ne touche ni `domain/`, `application/`, `adapters/`, `bootstrap/`, ni catalogue, paquet, fiche, `planning.md`, `cloture-palier-1.md`. Ne dit pas que la CI distante est verte.
- **Ecart de la fiche de `FRA-CE1-15` a nommer** : elle autorise la fermeture apres la fusion de `72931c94`. La fusion de `sofia-sm` (`d67204de`) porte le sommet `3459ddb8`, qui n'ajoute que le rapport de la developpeuse (`rejeu-nadia-palier-1.md`, amendement de 20:40) ; `cloture-palier-1.md` section 4 dit que le contenu fusionne est celui qui a ete joue. Verifie : `git diff 72931c94 d67204de -- <paquet>` rend 0 ligne, et `git diff 1f6642ab c935d764 -- <paquet>` rend 0 ligne pour `ce1-phrases-types.yaml`.
- **Statuts de stories** : aucun ne change. **Matrice : 144 / 200.**

---

## Amendement du 2026-10-15, quatorzieme (`camille-po`, matrice apres la cloture du palier 1)

Annotation datee, 18:06 (UTC+02:00). **Rien au-dessus n'est reecrit.** **Le statut ne change pas : `Acceptee` (A.77).** Sources : `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/cloture-palier-1.md` (section 0 `R1`, section 2, section 9 `P11`), `rejeu-nadia-b2-palier-1.md`, `FICHE-bandit-manchot-texte-narratif-ce1.md` et `FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md`.

### A.91 Ce que cet amendement consigne : deux fermetures

La regle est celle d'A.89 : ce qui decide de la fermeture est la phrase de la fiche, et la condition de fusion est lue par `git`.

| Ligne | Verdict et phrase de la fiche | Condition de fusion | Mention de couverture partielle |
|---|---|---|---|
| `FRA-CE1-04` | **VALIDE SOUS RESERVE**, 2026-10-15, instance A ; fiche, ligne 26 : *"`camille-po` peut passer `FRA-CE1-04` a `couvert-valide`, sans mention de couverture partielle, apres la fusion de `4dd6f298`"* ; reserve neuve `RCT-CE1-R1`, non bloquante | `git merge-base --is-ancestor 4dd6f298 HEAD` et `13affd6f` : sort 0 ; `git diff 4dd6f298 HEAD -- .../ce1-texte-narratif.yaml` : 0 ligne ; `grep -c "paquet: ce1-texte-narratif$"` du catalogue : 1 | aucune (fiche) |
| `ART-CP-10` | **VALIDE SOUS RESERVE**, rejeu du 2026-10-15, instance B, sur `ac9ae080` ; refus du 2026-10-11 leve, `RDO-CP-R1` et `RDO-CP-R2` levees ; routage de la fiche, section 9 : *"`ART-CP-10` a `couvert-valide` apres la fusion de B2"* ; reserve neuve `RDO-CP-R3`, non bloquante | `git merge-base --is-ancestor ac9ae080 HEAD` et `e7497db7` : sort 0 ; `git diff ac9ae080 HEAD -- adapters domain application bootstrap` : 0 ligne ; catalogue a 150 entrees | la fiche n'en ecrit aucune (`grep -c "couverture partielle"` : 0) ; **la fiche ne porte pas la formule *"sans mention de couverture partielle"* de `FRA-CE1-04`** : j'ai lu son silence comme une absence de mention, pas comme une mention exigee a deviner. `nadia-prof` la corrige si elle l'entend autrement |

| | Avant (`65bc1a9b`, `round-two` a `6dbb855d`) | Apres |
|---|---|---|
| `couvert-valide` | 144 | **146** |
| `en cours` | 4 | 4 |
| `non couvert` | 52 | **50** |
| `non couvrable en l'etat` | 77 | 77 |
| Total | 277 | 277 |
| Denominateur atteignable | 200 | **200** |
| Taux | 144 / 200 = 72,00 % | **146 / 200 = 73,00 %** |

Commande : celle de la "Methode du compte" de la matrice (`awk -F'|'`, lignes de donnees, aucune mesure partielle), lancee avant et apres l'edition sur le meme fichier. Compteur de version (A.32) : 146 + 4 = 150 / 200 = 75,00 % par la formule, son critere 2 (CI verte sur l'arbre de la version) restant a lire sur cet arbre. Par niveau : CP 59 / 52, CE1 62 / 28, CM1 79 / 66. Le maximum de la version, fige a 200, est inchange.

### A.92 Reports, tels qu'A.67 et A.83 les comptent

- **Poste 7 (`FRA-CE1-04`)** : premier report (s57, palier 1, 2026-10-14), audite dans le palier, **solde** par la fermeture. Aucun second report.
- **Poste 8 (`ART-CP-10`)** : A.83 gardait *"huit evenements sans fermeture"* tant qu'un rejeu ne l'avait pas fermee. **Le neuvieme evenement est la fermeture** : rejeu du 2026-10-15, `couvert-valide`. Le compte des reports de la ligne s'arrete ici ; il n'y a pas de troisieme report a interdire.
- **Poste 9 (B2)** : pas un report (A.67). Fusionne `e7497db7`.

### A.93 Ce que cet amendement ne fait pas

- **`DEC-0120` reste `Acceptee`.** Ne fait pas : ne prononce aucun Done, ne juge aucun contenu, ne touche ni `domain/`, `application/`, `adapters/`, `bootstrap/`, ni catalogue, paquet, fiche, `planning.md`, `cloture-palier-1.md`. Ne dit pas que la CI distante est verte.
- **Aucun changement de code decide ici.** Les deux reserves `RCT-CE1-R1` et `RDO-CP-R3` ont chacune une story non engagee au backlog (annotation du 2026-10-15, 18:06) ; **jusqu'a l'engagement de `sofia-sm` au planning d'un palier avec un developpeur, rien n'est applique et le regime est celui de `DEC-0053`** (le pli reste une reserve non bloquante).
- **Composition du palier 2** : non faite, elle suit l'arbitrage prealable de `nadia-prof` (`cloture-palier-1.md` section 9, condition (b)).
- **Statuts de stories** : aucun ne change. **Matrice : 146 / 200.**

---

## Amendement du 2026-10-16, quinzieme (`camille-po`, arbitrages du palier 2 : compte des reports, worktrees de `-05` et `-24`, story du script de `PA1`)

Annotation datee. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace est nomme. **Le statut ne change pas : `Acceptee` (A.77).** Arbre : `round-two` a `f6134910`, branche `s57-camille-p2-arbitrages`. Sources : `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md` (amendement du 2026-10-15 20:56, sections 1 a 13), `composition-palier-2.md`, `reponse-nadia-q8.md`, `avis-theo-poste-8-art-cp-10.md` (annexe D). **Aucune ligne de matrice, aucun catalogue, aucun paquet, aucun code n'est touche** : 146 / 200 inchange. **Aucun prix, aucun lot n'est modifie.**

### A.94 Provenance : trois decisions de l'utilisatrice, forme 2 de `DEC-0062`

Donnees a l'agent principal le 2026-10-16, **mots non cites** parce que ce sont des reponses *"oui"* a des propositions ; je ne les ai pas lues de sa main (meme reserve que 0.1, A.1, A.77).

- **(a) `E3` couvre tout le palier 2** (`Q-S7` du planning, section 10). **Remplace** la ligne de ce tableau *"porte des postes p2-7 et p2-12 seulement"* et la phrase de la section 1 du planning *"je ne l'applique pas comme porte du palier"*. `E3` est la lecture du diff de B1 par `theo-archi` ; `cloture-palier-1.md` (ligne 7) la nomme condition de l'ouverture du palier 2. **Ce que j'en tire pour les stories** : `K1` de `S57-BR-FRA-CE1-05`, `S57-BR-FRA-CE1-24`, `S57-OBS-SCI-N1-N2` et de la story de A.97 gagne la trace de `E3` comme condition. **Je n'en ecris pas la commande** : la forme de la trace est celle de `theo-archi`, et le `grep` que le planning a lance sur *"lecture du diff"* rend quatre fichiers qui la posent ou la constatent absente, donc une commande ecrite sur ces mots serait deja verte sans trace. Elle s'ecrit quand il a ecrit sa ligne. **A `sofia-sm`** : la section 4 du planning declare `p2-3` a `p2-6` partables sans prealable hors `K1` ; elle ne tient plus.
- **(b) Affectations confirmees** : `marc-dev` sur trois postes (script de `PA1`, `S57-OBS-SCI-N1-N2`, `S57-BR-FRA-CE1-05`, dans cet ordre) ; `lea-dev` sur la forme commune puis `S57-RDO-CP-R3`. Ce sont celles de la section 5 du planning ; rien ne change a mes stories, qui nomment ces developpeurs.
- **(c) `Q-S6`** : *"oui"* a la proposition que `camille-po` tranche le compte des reports par amendement de cette decision. C'est A.95.

### A.95 `Q-S6` : le compte des reports a l'ouverture est celui d'A.66, a la lettre

**Decision.** Un poste retenu par la composition d'un palier et non engage a l'ouverture de ce palier est un report, compte a l'ouverture, quelle qu'en soit la cause (A.66). **Remplace** la ligne de `composition-palier-2.md`, section 9, qui ecrivait *"premiere inscription ; un glissement du palier 2 est le premier report"* pour la forme commune, `RCT-CE1-R1`, `VSJ-CE1-R2`, `TPH-CE1-R1`, `GNA-CE1-R1`, `RDO-CP-R3`. `sofia-sm` a applique A.66 (planning, section 8) et l'a dit ; **l'ecart etait le mien** : ma composition (2026-10-15 20:43) est posterieure a A.66 (2026-10-14) et ne l'a pas appliquee.

**Motifs de forme 1.**

1. A.66 est ma regle, ecrite pour ce cas (*"quelle qu'en soit la cause"*), et A.67 l'a appliquee le jour de l'ouverture du palier 1 aux postes 7 et 8, que la composition du palier 1 avait retenus. Deux lectures pour la meme situation a un palier d'ecart, c'est deux regles.
2. *"Un glissement du palier 2 est le premier report"* fait dependre le premier report d'un evenement a venir. C'est la condition sans date qu'A.66 remplace : sans date elle ne se declenche jamais et le compte disparait (mecanisme de `DEC-0007`).
3. `sofia-sm` ecrit le compte a l'ouverture (A.75) ; la lecture qui lui laisse une date lisible est celle d'A.66.

**Compte.** Premier report, ouverture du palier 2, 2026-10-15 20:56 : `p2-7` (forme commune), `p2-8` (`TPH-CE1-R1`), `p2-9` (`GNA-CE1-R1`), `p2-10` (`RCT-CE1-R1`), `p2-11` (`VSJ-CE1-R2`), `p2-12` (`RDO-CP-R3`). `p2-1` a `p2-6` sont engages : aucun report. Les stories a prix 0 que la composition ne retient pas (`S57-MESSAGE-PARTIE-PERDUE`, `S57-TYP1-TEXTES-DE-GABARIT`, `S57-GARDE-NOMBRE-DE-MANCHES`) n'ont pas de report : elles n'etaient pas retenues. `-06`, `-07`, `-11` ne sont pas des reports mais des ecartements, comptes a part (composition, section 9) ; cette decision ne les change pas.

**Effet sur le palier 3.**

- Un de ces six postes encore retenu et non engage a l'ouverture du palier 3 est un **second report**, compte ce jour-la, avec la date du premier. Aucun troisieme n'est permis (A.67) : au palier suivant, un poste non engage a nouveau n'entre pas dans la composition sans un acte ecrit, abandon ou question a l'utilisatrice.
- Un de ces postes engage par amendement date pendant le palier 2 **garde son premier report** (A.83). Non audite a la cloture du palier 2, il glisse (glissement, pas report, A.72). Non audite a la cloture du palier 3, le second glissement est un report compte (A.72) : le second, donc le dernier permis.
- `p2-8` et `p2-9` dependent de la forme (`p2-7`) selon la section 4 du planning, `p2-10` et `p2-11` aussi quand leur voie est la forme. Si `p2-7` n'est pas engagee a l'ouverture du palier 3, ces cinq postes prennent leur second report le meme jour. Engager `p2-7` pendant le palier 2 est le seul geste qui evite cela pour la chaine.

### A.96 `Q-S4` : la lettre de `PN8` et `FM5` interdit d'ouvrir le worktree avant la lecture complete

**Decision : oui.** `PN8` (`S57-BR-FRA-CE1-05`) et `FM5` (`S57-BR-FRA-CE1-24`) disent que la correction demandee par `nadia-prof` apres sa lecture complete est ajoutee par amendement a l'entree **avant l'ouverture du worktree**. Si le worktree est ouvert avant la lecture, une correction demandee apres elle arrive necessairement apres l'ouverture : la clause ne peut pas etre tenue. Je n'en vois pas d'autre lecture, et je ne l'assouplis pas.

**Motifs de forme 1.**

1. La phrase. Une lecture qui laisserait ouvrir le worktree rendrait `PN8` fausse dans le cas qu'elle prevoit.
2. Le cas n'est pas theorique. Pour `-05` : l'item 15 de `ce1-pronoms` est rouge sur l'existant (`Q-P6`, bonne reponse *"Elias"*). Pour `-24` : `grep -c 'bonneReponse: "librairie"'` sur `ce1-familles-de-mots.yaml` rend 1 (`S57-FRA-FAMILLES-LIVRE`). Les fixtures et le test du paquet (`K3`, `K4`) s'ecrivent dans le worktree, sur un contenu que `nadia-prof` peut encore corriger.
3. Le cout est faible : `marc-dev` porte le script et `S57-OBS-SCI-N1-N2` avant `-05` (planning, section 5) ; `-24` est le premier poste de `sofia-sm` developpeuse. La lecture est le poste `p2-1`, a l'ordre 1, et n'attend pas la fusion du planning.

**Ce que cette decision n'est pas.** Ce n'est pas une exigence de `nadia-prof` : la sienne est *"avant le premier verdict"* (arbitrage du palier 2, 6.1 point 2). C'est un critere de mon fait, que je peux amender ; je ne le fais pas parce que le cout est faible. Elle remplace la ligne `Q-S4` du planning (sections 4 et 10 : *"worktrees ouverts, aucun YAML de paquet touche"*) pour `-05` et `-24` ; **l'amendement du planning est a `sofia-sm`**. `K2` reste.

**Commande, ecrite au backlog.** Le worktree d'un lot part d'une base qui contient la ligne datee *"lecture complete : <paquet>, N items lus"* de son paquet. Rouge aujourd'hui : 0 ligne pour les deux paquets sur `round-two`, donc non vide ; la branche est lue avant sa fusion. **Cette commande n'est pas celle de `S57-ARB-PALIER-2`, critere 2, qui etait deja verte (A.98).**

### A.97 `Q-S5` : la story du script de `PA1` est ecrite

**Decision : `S57-SCRIPT-ADDITIONS-CP`, au backlog (annotation du 2026-10-16), prix 0, hors `PAR`, competence transverse.**

`PAR-16` (`ARBITRAGE-PREALABLE-s34...`, section 6.1) dit : *"Je n'alloue contre ma borne aucun poste qui n'a pas de story a l'ouverture du sprint."* Le poste est a prix 0 et hors `PAR` : rien n'est alloue contre sa borne, `PAR-16` ne la demande pas, et la lecture du planning (section 10, `Q-S5`) est plus large que la clause. **Ce n'est pas le motif.** Motifs de forme 1 :

1. Un poste engage, avec un developpeur et un mesureur nommes, doit avoir un observable : `CR14` est une ligne de routage, elle ne dit pas ce que *"livre"* veut dire (regle du depot : une story qui n'est pas au backlog n'existe pas).
2. Le critere existe deja dans les mots de `nadia-prof` (`reponse-nadia-q8.md`, `N1` : *"Ni echantillon, ni partie d'essai qui en tiendrait lieu"* ; section 3 : 65 enonces distincts, egaux a l'ensemble derive) et dans `S57-FORME-COMMUNE-PROPOSITIONS-LONGUES`, critere 3 (22 enonces sur 65 mesures). Aucun fichier ne les transforme en entree et en sortie.
3. Les ports codes en dur de `pilote-q7.mjs` et de `pilote-accueil.mjs` (`C1`, `C2` du planning) ont deja cause des collisions : le critere qui les interdit s'ecrit avant le script, pas apres.

Le script mesure ; ce que la cote vaut est a `nadia-prof`.

### A.98 Ecart trouve en verifiant `S57-ARB-PALIER-2`, critere 2 : deja vert

Le critere 2 annoncait *"Aujourd'hui : 0 ligne pour chacun des deux, rouge"* avec `grep -rE "lecture complete.*ce1-pronoms.*20"` sur `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/` et `squad/conformite/`. Sur `round-two` a `f6134910`, `git grep -l -E 'lecture complete.*ce1-pronoms.*20'` rend `planning.md` (ligne 452, la ligne du poste `p2-1`), de meme pour `ce1-familles-de-mots` et 24. **Le critere etait vert sur ce qu'il devait exiger**, par un fichier de `sofia-sm` ecrit apres lui, dans le dossier qu'il cherche. **Remplace** la commande, pas les mots de `nadia-prof` : *"lecture complete : <paquet>, N items lus"*, avec ou sans accents graves autour du nom du paquet. Nouvelle commande, rouge (0 ligne pour chacun) sur le meme arbre : au backlog.

### A.99 Routage, ce que cet amendement ne fait pas

| A qui | Quoi |
|---|---|
| `sofia-sm` | Amender le planning : `Q-S4` (A.96), `Q-S6` (A.95, section 8 deja alignee), `Q-S7` et section 4 (A.94 a), titre de `p2-6` (A.97) ; la ligne obsolete nommee ci-dessous ; lire la branche d'un lot avant fusion contre la commande de A.96 |
| `nadia-prof` | Ecrire les lignes *"lecture complete"* de `ce1-pronoms` (20) et `ce1-familles-de-mots` (24) : elles ouvrent les worktrees de `-05` et `-24` |
| `theo-archi` | La trace de `E3`, dans la forme qu'il choisit |
| `marc-dev`, `ines-design`, `yanis-qa` | `S57-SCRIPT-ADDITIONS-CP` : ecriture, mesure, test des criteres |

**Ligne obsolete du planning, nommee et non corrigee.** Section 3 (ligne 465) : *"`D5` ne s'engage pas avant la fusion de la forme commune"*, et la liste *"Hors `PAR`, non engages [...] `D5`"*, reprise par *"les quatre stories a prix 0 et `D5`"* de la section 4. `D5` est fait et fusionne : `29d02bc6` (`[MARC]`, 2026-10-15 18:10) est ancetre de `round-two` (`git merge-base --is-ancestor` sort 0), fusionne par `dbd4a612`, et `grep -rln "brouillon soumis"` sur `contenu/` rend 0. Meme ecart dans `composition-palier-2.md` (section 4, ligne `D5` ; resume `CO5`). Je ne corrige ni l'un ni l'autre.

- **Ne fait pas** : ne prononce aucun Done, ne juge aucun contenu, ne touche ni `domain/`, `application/`, `adapters/`, `bootstrap/`, ni catalogue, paquet, fiche, matrice, `planning.md`, `composition-palier-2.md`. Ne dit pas que la CI distante est verte. N'ecrit pas l'amendement de cette decision qui porte la composition du palier 2 (planning, section 13) : il n'etait pas demande.
- **Jobs de `ci.yml` lances pour son commit** : nommes dans le message de livraison ; ceux qui ne sont pas lances ne sont pas deduits.


---

## Amendement du 2026-10-16, seizieme (`camille-po`, palier 2 : garde de `C1'`, `D5` et `Q-B2` corriges, recommandation sur les indices de `-24`)

Annotation datee, 2026-10-16 09:25 (UTC+02:00), lue par `date`. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace est nomme. **Le statut ne change pas : `Acceptee` (A.77).** Arbre : `round-two` a `aa9d703c`, branche `s57-camille-p2-garde`. Sources : `planning.md` (amendement du 2026-10-16 09:03, sections 1 a 13) ; `avis-theo-palier-2.md` (`TP4`, section 2.4, `Q-T14`, `Q-T15`) ; `reponse-nadia-q-palier-2.md` (3.6, 3.7) ; `ARBITRAGE-PREALABLE-s34...` section 6.1 (`PAR-16`) ; `A.94` a `A.99`. **Aucune ligne de matrice, aucun catalogue, aucun paquet, aucun code n'est touche** : 146 / 200 inchange. **Aucun prix, aucun lot n'est modifie.**

### A.100 `Q-T14` : la garde de `C1'` est une story hors `PAR`, a prix 0, et ne conditionne pas la fusion de p2-7

**Decision.** La story `S57-GARDE-C1-SUJET-HORS-MESSAGE` est ecrite au backlog (annotation du 2026-10-16), prix 0, hors `PAR`, competence transverse. **Elle n'est pas une condition de la fusion de `S57-FORME-COMMUNE-PROPOSITIONS-LONGUES`.** La seule condition de cette fusion reste la trace de lecture de `theo-archi` (`Q-T15`, `TP6`). La forme de la garde est a `theo-archi` ; je n'en decide aucun element.

**Prix 0, motifs de forme 1.** `PAR` est le budget d'audit de `nadia-prof` ; la story ne lui demande ni lecture, ni rejeu, ni verdict, et aucun texte lu par l'enfant ne change. Rien n'est donc alloue contre sa borne, et `PAR-16` (*"je n'alloue contre ma borne aucun poste qui n'a pas de story"*) est tenue dans l'autre sens : le poste a sa story. Si la forme choisie lui demande de lire ou rejouer, c'est elle qui chiffre, avant le commit.

**Pas une condition de p2-7, motifs de forme 1.**

1. **Elle ne rendrait pas un verdict que la lecture ne rend deja.** `theo-archi` a lu le diff de la forme (`TP1`, `TP2` : `C1'` tenue dans sa lettre sur les cinq regles) et `Q-T15` demande que le delta entre le commit lu et le sommet de la branche soit relu et dit. Sur ce diff, la garde repondrait la meme chose que lui.
2. **Une seule personne porterait les deux conditions.** `theo-archi` est le seul lecteur du diff de p2-7 (`TP6`) et le seul auteur de la garde. Deux conditions sur un meme agent avant une fusion dont dependent le rejeu de `TPH-CE1-R1` et de `GNA-CE1-R1` (premier report compte a l'ouverture, A.95 ; un poste non audite a la cloture du palier 2 glisse, A.72) ajoutent du delai a la chaine sans ajouter de verification.
3. **La forme ne cree pas la dette.** `TP4` : *"la forme ne viole pas `C1'`"* ; la dette existe depuis `C1'` (2026-10-14). La fusion de p2-7 n'est pas le moment ou elle s'aggrave.

**Ce que je ne dis pas** : que la garde est secondaire. `C1'` point 1 sans garde executable est la condition `C4` de `nadia-prof` tenue par une seule lecture, et cette lecture a manque une fois (`E3`). Ce que je choisis est l'ordre, pas l'importance (A.101).

**Ce que l'ordre de fusion devient** : il reste a `sofia-sm`. Si la garde est fusionnee avant p2-7, la fusion de p2-7 la subit et le build de `sofia-sm` la rejoue sur la feuille de la forme (critere 4 de la story) ; si p2-7 passe la premiere, la garde s'ecrit contre une feuille qui porte la forme. Aucun des deux ordres n'est a decider ici.

### A.101 Compte : la dette de `C1'` et son horizon

`C1'` a ete ecrite le 2026-10-14 avec, pour seule garde, la lecture du diff par `theo-archi` (`avis-theo-poste-8-art-cp-10.md` 2.2). Dette nommee le 2026-10-16 (`TP4`). Premiere inscription comme story : 2026-10-16. **Report : aucun**, la story n'est pas retenue par une composition (hors `PAR`, A.95). **Mais la dette se compte** : si `S57-GARDE-C1-SUJET-HORS-MESSAGE` n'est pas fusionnee a la cloture du palier 2, elle est inscrite en tete du palier 3, **avant tout poste qui modifie `recreo.css`**, et la composition du palier 3 le dit. Un poste qui touche la feuille avant elle cumule alors une dette de deux paliers sur la propriete que `nadia-prof` a posee en `C4`.

### A.102 Deux ecarts de mes fichiers, corriges

- **`D5` est fait.** Ma composition (2026-10-15 20:43) le tenait pour non engage. Il est fusionne depuis le 2026-10-15 18:10 (`29d02bc6`, fusion `dbd4a612`), apres la cloture du palier 1 (17:44) et avant l'ouverture du palier 2 (20:56). `composition-palier-2.md` est corrige en quatre endroits (`CO5`, section 4, `EC5`, section 9) et sa section 13 porte les preuves. **Remplace** la partie de A.99 qui disait *"Je ne corrige ni l'un ni l'autre"* : elle ne decrit plus l'etat de ce fichier. Trois stories a prix 0 restent non engagees, pas quatre.
- **`Q-B2` est repondue depuis le 2026-10-14** (`lecture-nadia-palier-1.md` 1.7, `D8`, `7161ab69`). Je l'avais ecrite *"non repondue"* dans `composition-palier-2.md` (section 3) et dans l'amendement du backlog de `S57-FRA-FAMILLES-LIVRE`. Corrige dans les deux (backlog : annotation du 2026-10-16). **Remplace** la ligne du tableau de la section 3 de la composition et la phrase *"`Q-B2` n'est pas repondue"* du backlog.

### A.103 La recommandation de `nadia-prof` sur les indices des items de famille de `-24` est prise

**Decision : prise.** Ses mots (`reponse-nadia-q-palier-2.md` 3.7) : *"Recommandation non bloquante, a `camille-po` de la prendre ou non : l'indice de 3.6 sur les 14 items de famille."* Critere FM6 de `S57-BR-FRA-CE1-24` (backlog, annotation du 2026-10-16) : les 14 items de famille portent l'indice de 3.6, recopie sans reformulation ; trois mesures, rouges aujourd'hui.

**Motifs de forme 1.**

1. **Le texte est le sien, la recommandation aussi.** Je ne juge pas qu'il convient aux 13 autres items ; je transforme son mot en critere. Elle peut retirer la recommandation par une ligne avant l'ouverture du worktree de `-24` : FM6 s'amende, et le lot se branche comme avant.
2. **Le choix se fait avant son verdict ou apres.** Elle ecrit que, si je ne la prends pas, elle juge au jeu en commettant les deux erreurs sur chaque item et que ce qu'elle y trouvera sera *"au plus une reserve non bloquante"*. Une reserve a un traitement qui a un prix de rejeu (0,25 ou 0,5 au registre de la composition, section 2). Le prendre maintenant coute quatorze lignes recopiees dans un commit deja prevu (p2-4, `sofia-sm`) et trois `grep`.
3. **Le prix du lot ne bouge pas, et il n'est pas a moi.** Si elle estime que FM6 change son audit, c'est elle qui chiffre.

**Ce que cette decision n'est pas.** Elle ne fait pas passer `FRA-CE1-24` ; le Done est a `nadia-prof`. Elle n'ecrit pas les amendements de `PN8` et `FM5` pour 1.1, 3.5 et 3.6 (items 10 et 13 de `ce1-pronoms`, item 15, item *livre*), que sa section 5 me route : ils restent a faire.

### A.104 Routage, ce que cet amendement ne fait pas

| A qui | Quoi |
|---|---|
| `sofia-sm` | Engager `S57-GARDE-C1-SUJET-HORS-MESSAGE` au planning du palier 2 (agent `theo-archi`, aucun developpeur, aucune condition sur la fusion de p2-7) ; recopier FM6 dans le commit de `-24` ; verifier au planning que l'amendement du 2026-10-16 09:03 (section 8, `Q-T14`) renvoie bien a cette story |
| `theo-archi` | Forme de la garde et amendement d'`ADR-0031` ; residu `data-oeuvre-revelee` (critere 3) ; perimetre de la commande de mesure si elle quitte `adapter-web` |
| `yanis-qa` | Test des criteres 1 a 5 de la story de garde par mutation de la feuille |
| `relecteur-pedagogique` | Orthotypographie de la phrase de FM6, avec les textes de 3.6 |
| `nadia-prof` | Retirer ou confirmer la recommandation avant l'ouverture du worktree de `-24` ; lire FM6 a son audit |

- **Ne fait pas** : ne prononce aucun Done, ne juge aucun contenu, ne touche ni `domain/`, `application/`, `adapters/`, `bootstrap/`, ni catalogue, paquet, fiche, matrice, `planning.md`. Ne dit pas que la CI distante est verte. N'engage aucun poste. Ne decide aucun element de forme de la garde.
- **Jobs de `ci.yml` lances pour son commit** : nommes dans le message de livraison ; ceux qui ne sont pas lances ne sont pas deduits.


---

## Amendement du 2026-10-16, dix-septieme (`camille-po`, palier 2 : prix recomptes et financement du rejeu de `-05`, paquet refuse servi, matrice apres les verdicts, voies de `PA7` et `PA8`, `FM5-6` et `FM3`)

Annotation datee, 2026-10-16 17:40 (UTC+02:00), lue par `date`. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace est nomme. **Le statut ne change pas : `Acceptee` (A.77).** Arbre : `round-two` a `78270b4c`, branche `s57-camille-p2-cloture`. Sources : `planning.md` (amendement du 2026-10-16 16:59, sections 2 a 10), `composition-palier-2.md`, `arbitrage-nadia-palier-2.md`, les cinq fichiers de rejeu et de reponse de `nadia-prof` du 2026-10-16, `avis-theo-pa7-pa8.md`, `DEC-0045` section 3, `DEC-0055`. **Aucun code, catalogue, paquet ni test n'est touche** ; deux stories neuves sont ecrites au backlog (annotation du 2026-10-16 17:40) avec leur proprietaire a designer.

### A.105 Prix : `GNA-CE1-R1` a 0,25 est confirme, et le rejeu de `-05` est finance sans toucher une marge

**Decision 1 : `GNA-CE1-R1` vaut 0,25.** Motifs de forme 1. (a) Le prix est a `nadia-prof` : son bareme ecrit 0,25 quand la forme du paquet ne differe pas de celle de `TPH` (`arbitrage-nadia-palier-2.md` 5) et son rejeu constate la condition (`rejeu-nadia-palier-2-ap.md` 4 : memes regles, meme jar, paquet servi inchange). (b) Mes 0,5 etaient le **maximum du registre**, inscrit par ma regle de composition (*"j'inscris le plus haut"*, `composition-palier-2.md` 2), pas un prix de moi ; je ne conteste pas un constat que le rejeu fait. (c) Retenir 0,5 ferait payer a l'instance A' un audit qu'elle n'a pas fait.

**Decision 2 : le rejeu de `-05` (0,5, `nadia-prof`, instance A) entre dans la composition de A, finance par deux quarts qui n'appartiennent a aucun poste.** Il n'avait aucune ligne (`planning.md` 16:59, section 5, point 2). Sources du prix : la fiche, section 5 (*"0,5 (des textes changent)"*) et `rejeu-nadia-palier-2-a-lot05.md` (*"Rejeu a 0,5"*). Financement :

1. **0,25 de A** : `VSJ-CE1-R2` etait inscrit a 0,5 dans ma composition, il a ete facture 0,25 (`rejeu-nadia-palier-2-a.md`, resume) ; `planning.md` du 2026-10-16 09:03 le porte *"non affecte"* dans A. Il reste dans A.
2. **0,25 hors instance** : la difference de 7,75 a 8,0 de ma composition (`composition-palier-2.md` 2, *"n'entre dans aucun poste"*), qui n'etait dans le budget d'aucune instance. **Je l'attribue a A.**

A passe de 3,0 a **3,25**. **A' garde son budget de 3,0** : les 0,25 rendus par `GNA` y restent non affectes (2,75 de postes et 0,25 non affecte). B ne bouge pas. Hors instance : 0,00.

**Ce que cette lecture change par rapport a celle de `sofia-sm`** (`planning.md` 16:59, section 5, point 2 : les 0,25 de A plus les 0,25 rendus par `GNA`, soit A a 3,25, A' a 2,75, hors instance 0,25). Meme total (8,0), meme non affecte (0,25), repartition differente. Je prends l'autre quart pour trois motifs. (i) **Aucun budget d'instance ne perd rien** : la repartition entre instances est a `nadia-prof` (A.4, dernier paragraphe : *"La repartition entre instances est a `nadia-prof`"*) et deplacer 0,25 d'A' vers A est ce geste ; attribuer un reste que personne n'avait attribue n'en retire a aucune. (ii) **La somme des budgets d'instance vaut la borne** : 3,25 + 3,0 + 1,75 = 8,0, ce que `PAR-9` ecrit (*"somme des budgets par instance egale a la borne de 8,0"*) et que ma composition ne tenait pas (7,75 plus 0,25 hors instance). (iii) **Le financement ne depend plus du prix de `GNA`** : avec `GNA` a 0,5, la meme composition vaut encore 8,0 (sortie ci-dessous) ; avec la lecture de `sofia-sm`, `GNA` a 0,5 donne 8,25 et depasse `PAR-9` (`planning.md` 16:59, section 5, point 3).

**Ma regle de composition est amendee, pas abandonnee.** *"L'ecart reste non affecte, il ne se reaffecte pas"* (`composition-palier-2.md` 2) etait ecrite pour qu'**un troisieme lot** ne soit pas finance par un ecart de prix connu apres coup (8,75 au maximum). Elle vaut toujours pour un lot. Elle ne vaut pas pour le **rejeu d'un refus**, dont le prix est connu et ecrit par `nadia-prof` : le payer sur un ecart de son instance est ce qu'une marge de rejeu n'a pas a payer quand un ecart existe. **Aucun prix n'est pris sur une marge** (arbitrage 8 point 7), les trois marges de 0,5 restent intactes.

**Recompte, par `awk`**, sur le registre ci-dessous (instance, ligne, prix, etat ; `Ap` = A', `H` = hors instance) :

```
A|arbitrage prealable|0.50|engage
A|FRA-CE1-05 lot|1.00|consomme
A|FRA-CE1-05 rejeu|0.50|engage
A|VSJ-CE1-R2|0.25|consomme
A|RCT-CE1-R1|0.50|engage
A|marge de rejeu|0.50|marge
Ap|arbitrage prealable|0.50|engage
Ap|FRA-CE1-24 lot|1.00|consomme
Ap|TPH-CE1-R1|0.50|consomme
Ap|GNA-CE1-R1|0.25|consomme
Ap|marge de rejeu|0.50|marge
Ap|non affecte (GNA)|0.25|non affecte
B|arbitrage prealable|0.50|engage
B|OBS-SCI-N1-N2|0.25|consomme
B|RDO-CP-R3|0.50|engage
B|marge de rejeu|0.50|marge
H|non affecte|0.00|non affecte
```

`awk -F'|' '{p[$1]+=$3; t+=$3} END{for(k in p) printf "%s %.2f\n",k,p[k]; printf "total %.2f\n",t}' registre.txt | sort` rend `A 3.25`, `Ap 3.00`, `B 1.75`, `H 0.00`, **`total 8.00`**. Par etat (`awk -F'|' '{e[$4]+=$3} END{for(k in e) printf "%s %.2f\n",k,e[k]}'`) : consomme **3.25**, engage **3.00**, marge **1.50**, non affecte **0.25**. Meme registre avec `GNA` a 0,50 et la ligne *non affecte (GNA)* a 0,00 : **`total 8.00`**. Ma composition du 2026-10-15 (prix au maximum, sans rejeu de `-05`), recomptee de la meme facon : `A 3.00`, `Ap 3.00`, `B 1.75`, `H 0.25`, `total 8.00`. Dix-sept lignes (`wc -l`).

| Borne | Valeur lue |
|---|---|
| 8,0 par palier (`PAR-9`) | 8,00 |
| 5,0 par instance (`PAR-8`) | l'instance la plus chargee est A : 3,25 |
| Trois lots au plus par instance | A 1 (`-05` ; le rejeu n'est pas un lot, `PAR-10`), A' 1, B 0 |
| Marge de 0,5 par instance qui porte un lot ou un rejeu | trois marges, somme 1,50, **intactes** |
| Aucun prix pris sur une marge | tenu |

**Second refus, dit maintenant.** Le rejeu de `-05` et celui de `RCT-CE1-R1` sont tous deux dans A, dont la marge est de 0,5 : **un seul des deux peut echouer sans acte ecrit**. Un second echec au meme palier se paie sur la marge de A (son usage) puis, au-dela, par un acte ecrit de `nadia-prof` ou de moi, jamais sur la marge d'une autre instance. Le non affecte restant (0,25, dans A') n'est pas un moyen de payer un rejeu : c'est un reste.

**Ce que je ne decide pas.** La repartition entre instances est a `nadia-prof` : elle dit, avant le rejeu, si l'attribution du quart hors instance a A lui convient ; sinon la lecture de `sofia-sm` s'applique et la somme reste 8,0 tant que `GNA` vaut 0,25. `planning.md` porte la composition de A a 3,25 : amendement a `sofia-sm`.

### A.106 Un paquet refuse reste servi : `bandit-manchot-pronoms-ce1`

**Fait** (`git grep -c 'id: bandit-manchot-pronoms-ce1$' 78270b4c -- adapters/adapter-content/src/main/resources/catalogue.yaml` rend 1) : `nadia-prof` a refuse le lot `-05` le 2026-10-16 (16:18, `rejeu-nadia-palier-2-a-lot05.md`) ; l'entree est au catalogue et le jar la sert. Aucun fichier du depot ne traite la sortie : ni l'amendement de `PN8` (16:40) ni `planning.md` (16:59) n'en parlent. Les entrees de lots entrent au catalogue avant l'audit par construction (`K3`) : le premier refus de s57 sur un lot est celui-ci.

**Decision : le regime par defaut s'applique, le paquet sort du catalogue servi.** `DEC-0045` section 3 : un `REFUSE` fait sortir l'entree *"au meme geste que la decision"*. `DEC-0055` 2 n'ouvre une fenetre que sous trois conditions cumulatives, verifiables *"au moment du refus"* : (1) arbitrage ecrit : aucun ; (2) correction engagee, proprietaire et travail identifiables **au moment du refus** (16:18) : la story de correction est de 16:40, le developpeur de 16:59, **non remplie** ; (3) motif borne : remplie. Je ne plie pas ma propre regle : elle ecrit *"verifiables sans jugement d'opportunite"* et *"je n'en autorise pas un aujourd'hui sur la seule foi du raisonnement"*. Meme analyse qu'en A.17 de `DEC-0116`, dont l'utilisatrice a repondu *"oui pour le retrait"* (A.26).

**Execution, dans le meme temps (la decision implique du code)** : story `S57-RETRAIT-PRONOMS-CE1` ecrite au backlog (annotation du 2026-10-16 17:40), `sofia-sm` l'affecte, `theo-archi` mesure le cout technique, comme `S56-RETRAIT-RAPPROCHER-OEUVRES`. Elle ne touche ni le paquet, ni son test, ni sa fiche : la correction de `marc-dev` et le retrait ne se disputent aucun fichier.

**Ce que cela coute, nomme.** Le rejeu de `-05` (0,5, A) a besoin d'un jar ou l'entree existe : la forme est celle de la branche de rejeu de `ART-CP-10` (`planning.md`, section *"Branche de rejeu B2"*), a `sofia-sm` et `theo-archi` ; un rejeu non fait a la cloture du palier 2 glisse (A.72), **et ce retrait augmente ce risque**. Je le dis plutot que de le taire.

**Ce que l'utilisatrice peut changer** : un arbitrage ecrit, **avant** que `sofia-sm` engage la story, qui ouvre une fenetre avec son echeance (`DEC-0055` 2 et 5) : exception nommee, datee, avec son motif ; je n'en ecris aucune, la condition 2 n'etant pas remplie. **`Q-U11`**, a l'utilisatrice, par `sofia-sm` : retrait (regime par defaut), ou fenetre bornee ? **La story n'attend pas la reponse.**

### A.107 Matrice : une ligne passe, trois cellules sont mises a jour, `FRA-CE1-05` reste `non couvert`

Sources, les verdicts de `nadia-prof` lus dans les fichiers de rejeu du 2026-10-16 :

| Ligne | Verdict, ses mots | Fichier | Effet |
|---|---|---|---|
| `FRA-CE1-24` | **VALIDE**, *"sans reserve"* ; *"`camille-po` peut passer la ligne a `couvert-valide`"* | `rejeu-nadia-palier-2-ap-lot24.md`, verdict | `non couvert` -> **`couvert-valide`** |
| `FRA-CE1-10` | `TPH-CE1-R1` **levee** ; `FRA-CE1-10` **VALIDE** | `rejeu-nadia-palier-2-ap.md`, resume | deja `couvert-valide` (2026-10-14) : cellule mise a jour |
| `FRA-CE1-15` | `GNA-CE1-R1` **levee** ; `FRA-CE1-15` **VALIDE** | idem | deja `couvert-valide` (2026-10-14) : cellule mise a jour |
| `FRA-CE1-13` | `VSJ-CE1-R2` **levee** ; `FRA-CE1-13` **VALIDE**, *"aucune reserve ouverte"* | `rejeu-nadia-palier-2-a.md`, resume | deja `couvert-valide` (2026-10-11) : cellule mise a jour |
| `SCI-CM1-20` | `N1` et `N2` prises en compte ; *"reste `couvert-valide`, aucune ligne ne bouge"* | `rejeu-nadia-palier-2-b.md`, verdict | inchangee |
| `FRA-CE1-05` | **REFUSE**, motif unique `TRV-CE1-6` | `rejeu-nadia-palier-2-a-lot05.md`, resume | reste `non couvert`, cellule mise a jour |

**Ecart de `planning.md` (16:59, section 2) : *"les passer a `couvert-valide` est le geste de `camille-po`"* est vrai d'une ligne sur quatre.** `FRA-CE1-10`, `-13` et `-15` etaient `couvert-valide` avant ce geste (lignes 1440, 1443 et 1445 de la matrice a `78270b4c`, statut lu), au titre d'un `VALIDE SOUS RESERVE` ; leur verdict d'aujourd'hui leve la reserve et ne change pas le statut. **Compte, par la commande de la "Methode du compte"** (`awk -F'|'` de la matrice, lignes de donnees, aucune mesure partielle) : avant, `couvert-valide` 146, `en cours` 4, `non couvert` 50, `non couvrable en l'etat` 77, total 277 (**146 / 200 = 73,00 %**) ; apres, 147, 4, 49, 77, total 277 (**147 / 200 = 73,50 %**) ; par niveau, CP 59 / 52, CE1 62 / 29, CM1 79 / 66. Compteur de version (A.32) : 147 + 4 = 151 / 200 = 75,50 % par la formule, critere 2 (CI verte sur l'arbre de la version) a lire sur cet arbre. **Le statut de tete de `FICHE-bandit-manchot-pronoms-ce1.md`** passe de *"en attente d'audit"* a *"refuse"* (motif `TRV-CE1-6`, correction engagee, entree encore servie) ; le bloc de validation de `nadia-prof` n'est pas touche.

### A.108 `PA7`, `PA8` et la police : les voies sont ecrites en criteres, la question de la police est tranchee (`DEC-0122`)

**Stories amendees** (backlog, annotation du 2026-10-16 17:40) : `S57-RDO-CP-R3` (critere 4 remplace par la phrase *"Voici la bonne œuvre. Appuie dessus."*, mots de `nadia-prof` ; critere 6 precise, parce que sa commande ne peut pas etre tenue avec le critere 4 : elle rend 6 lignes sur la copie corrigee ; critere 7 neuf) ; `S57-RCT-CE1-R1` (critere 3 remplace par les trois jeux de propositions de `nadia-prof`). **Voies de `nadia-prof`, non retouchees** (`NP1` a `NP6`). **Le poste de `lea-dev` et celui de `marc-dev` ne s'ouvrent qu'apres cet amendement** : verifiable, `git reflog show --date=iso <branche> | tail -1` porte une heure posterieure a `git log -1 --format=%cI <commit de cet amendement>` ; sinon un ecart de calendrier, dit dans le rapport avec l'heure.

**`DEC-0122`** (neuve, `Acceptee`) ferme la question que `ADR-0054` adresse a `camille-po` et `nadia-prof` depuis le 2026-08-22 (55 jours) : le perimetre de garantie d'une cote est la pile servie rendue sur le poste de mesure ; les polices imposees sont une sensibilite, pas un critere de levee ; les polices de tablette sont un risque nomme, couvert par la marge que `nadia-prof` juge. **Elle implique un code** (le point 3.5 : rien ne garde aujourd'hui la pile servie, mesure a l'appui) : story `S57-GARDE-PILE-DE-POLICES`, prix 0 hors `PAR`, `theo-archi` pour la forme, `sofia-sm` pour l'affectation, palier 3 au plus tot et avant tout poste qui modifie `recreo.css` (meme regle que A.101).

### A.109 `FM5-6` a base ecrite en SHA (`CR24`) et `FM3b` (`E4`)

**`FM5-6`** rendait 0 sur `round-two` (lance : `78270b4c`) pour la cause de `PN8-6` ; elle est reecrite avec la base `4004fab7...` (premier parent du commit livre `1febd011`), 34 sur le commit livre, la fusion et `round-two`. **`FM3`** gagne `FM3b` : le libelle `descripteur.competence` n'est lu par aucun code de production ni ecrit par aucun gabarit, **par deux commandes que j'ai lancees**, et perd la phrase qui attendait la confirmation de `theo-archi`. **Pourquoi je l'ecris moi-meme** : `E4` demandait un amendement *"attendu de `camille-po` et de `theo-archi`"* sans dire de quoi ; le fait qui le fonde est une lecture de code que `git grep` repond, pas un jugement d'architecture. `theo-archi` peut l'amender s'il lit le port entrant autrement ; `FM3b` est une clause, verte aujourd'hui.

### A.110 Routage, ce que cet amendement ne fait pas

| A qui | Quoi |
|---|---|
| `sofia-sm` | Amender le planning : A a 3,25 (A.105), rejeu de `-05` finance ; affecter `S57-RETRAIT-PRONOMS-CE1` et nommer la forme de la branche de rejeu de `-05` ; affecter `S57-GARDE-PILE-DE-POLICES` ; porter `Q-U11` ; ecart de la section 2 du 16:59 (A.107) |
| `nadia-prof` | Dire si l'attribution du quart hors instance a A lui convient (A.105) ; `Q-N5` (l'indice des items d'affixe de `-24`, texte ou retrait de la recommandation) ; lire la formulation de `DEC-0122` 3.2 |
| `theo-archi` | Cout technique du retrait ; forme du garde-fou de la pile ; `FM3b` |
| `lea-dev`, `marc-dev` | Lire les criteres amendes avant leur commit (backlog, `CR32` a `CR35`) |
| `yanis-qa` | Test des criteres : `RD3-*`, `RC1-*`, `FM5-6` amendee, `FM3b`, les quatre formes de la pile une a une |
| `relecteur-pedagogique` | Orthotypographie de la phrase de la voie (iii) et des trois jeux de propositions (`CR38`) |

- **Ne fait pas** : ne prononce aucun Done, ne juge aucun contenu, ne touche ni `domain/`, `application/`, `adapters/`, `bootstrap/`, ni catalogue, paquet, test, `planning.md`, `composition-palier-2.md`. Ne dit pas que la CI distante est verte. N'engage aucun poste. Ne change aucun prix de `nadia-prof`.
- **Jobs de `ci.yml` lances pour son commit** : nommes dans le message de livraison ; ceux qui ne sont pas lances ne sont pas deduits.

---

## Amendement du 2026-10-16, dix-huitieme (`camille-po`, cloture du palier 2 : `Q-U11` inscrite, matrice a 148 / 200, `Q2` de `marc-dev`, `Q-N5`, non affecte, mots de `S2`, proposition de palier 3)

Annotation datee, 2026-10-16 20:40 (UTC+02:00), lue par `date`. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace est nomme. **Le statut ne change pas : `Acceptee` (A.77).** Arbre : `round-two` a `bb322938`, branche `s57-camille-p2-matrice`. Sources : `planning.md` (amendements du 2026-10-16 18:14, 19:20 et 20:15), les fichiers de rejeu de `nadia-prof` du palier 2, `FICHE-bandit-manchot-pronoms-ce1.md`, `DEC-0045` section 3, `DEC-0055`, `DEC-0062` 3.1, `A.100` a `A.110`. **Aucun code, catalogue, paquet, test ni `planning.md` n'est touche.** Aucun Done n'est prononce par moi.

### A.111 `Q-U11` : la reponse est inscrite, la fenetre est close par le verdict, aucun retrait n'a eu lieu

**Provenance, forme 2 de `DEC-0062` 3.1.** L'utilisatrice a choisi, le 2026-10-16, entre les deux options que `Q-U11` lui proposait (retrait, regime par defaut de `DEC-0045` section 3 ; ou fenetre bornee, `DEC-0055`) : ***"fenetre bornee"***. Ce libelle est celui de l'option choisie, cite entre guillemets ; **ses mots ne sont rien de plus dans ce texte**. Il m'a ete transmis par l'agent principal, par le formulaire de celui-ci ; **je ne l'ai pas lu de sa main**, l'heure de la reponse n'est pas fournie, et **aucun motif ni aucune echeance ne m'ont ete transmis : je n'en ecris aucun**.

**Ce que cet acte est.** L'arbitrage ecrit de `DEC-0055` 2, condition 1, pour `bandit-manchot-pronoms-ce1` seul. La condition 2 (correction engagee au moment du refus) n'etait pas remplie a 16:18, ce que `A.106` constatait et qui reste vrai tel qu'ecrit : l'arbitrage de l'utilisatrice est donc l'exception nommee que `DEC-0055` 5 prevoit, datee, dont le motif n'est pas connu de moi. **Remplace** la decision de `A.106` (*"le paquet sort du catalogue servi"*) et son execution (`S57-RETRAIT-PRONOMS-CE1`) ; ne remplace pas le constat de fait de `A.106`.

**Faits, lus.**

- **Aucun retrait n'a eu lieu.** `git grep -c 'id: bandit-manchot-pronoms-ce1$' bb322938 -- adapters/adapter-content/src/main/resources/catalogue.yaml` rend **1** ; aucun commit de retrait sur `round-two`. `planning.md` 19:20, section 5 : *"pas de retrait"*, la story *"n'est pas engagee"*.
- **Le rejeu de `-05` a eu lieu avant la cloture du palier 2** : `rejeu-nadia-palier-2-a-lot05-rejeu.md`, 2026-10-16 20:02, verdict **VALIDE**, sans reserve.
- **La fenetre est close par ce verdict**, premier des deux evenements de `DEC-0055` 3 (*"VALIDE ... la fenetre se referme d'elle-meme, le jeu reste expose sur ce nouveau fondement"*). Le jeu a ete servi du refus (16:18) au verdict (20:02) : **3 h 44 min** ; il l'est ensuite sur un Done non refuse.
- **La lecture de `planning.md` 19:20** (la question du retrait revient au palier 3 si le rejeu n'a pas lieu avant la cloture) **ne joue pas** : sa condition est fausse. Je ne la ratifie ni ne l'infirme : elle est de `sofia-sm`, ce n'est pas un mot de l'utilisatrice.

**Ce qui a manque, nomme.** `DEC-0055` 3 exige une echeance ecrite, fixee a l'ouverture de la fenetre. Aucune n'a ete ecrite : la borne a ete l'evenement (le verdict), pas une date. Par ailleurs `A.106` decidait un retrait route avec une story, et il n'a pas ete execute entre le 17:40 et l'arbitrage de l'utilisatrice que le planning de 19:20 relaie : une decision ecrite et non executee, sans consequence ici parce que l'utilisatrice a repondu dans l'autre sens. Je l'ecris pour que le compte soit honnete.

**Fermetures.** `Q-U11` : **close**. `S57-RETRAIT-PRONOMS-CE1` : **non engagee, sans objet** (backlog, annotation du meme jour). Son critere 1 (*"le jeu n'est plus servi"*) reste rouge par decision de l'utilisatrice, et le paquet est `VALIDE` : je ne la garde pas.

### A.112 Matrice : une ligne passe, 148 / 200

Lignes lues une a une dans la matrice (par `grep` sur le texte de la competence), contre le rejeu et la fiche :

| Ligne | Ligne de la matrice | Verdict, ses mots | Etat avant | Effet |
|---|---|---|---|---|
| `FRA-CE1-05` | 1439 (`ce1-pronoms`) | **VALIDE**, *"sans reserve"* ; *"`camille-po` peut passer `FRA-CE1-05` a `couvert-valide`"* (fiche, rejeu du 2026-10-16) | `non couvert` (REFUSE) | **`couvert-valide`**, jeu `bandit-manchot-pronoms-ce1` ; historique du refus conserve dans la cellule |
| `FRA-CE1-04` | 1438 (`ce1-texte-narratif`) | `RCT-CE1-R1` **levee** ; **VALIDE**, *"aucune reserve ouverte"* (`rejeu-nadia-palier-2-a-rct.md`) | `couvert-valide` (2026-10-15) | cellule mise a jour, statut inchange |
| `ART-CP-10` | 2283 (`rapprocher-deux-oeuvres-cp`) | `RDO-CP-R3` **levee** ; jeu **VALIDE**, *"aucune reserve ouverte"* (`rejeu-nadia-palier-2-b-rdo-cp-r3.md`) | `couvert-valide` (2026-10-15) | cellule mise a jour, statut inchange |
| `FRA-CE1-24` | 1458 (`familles-de-mots`) | **VALIDE**, sans reserve | `couvert-valide` (2026-10-16, note precedente) | verifiee, non touchee |

**Ecart de la demande de cloture** : elle attendait trois passages ; `FRA-CE1-04` et `ART-CP-10` etaient `couvert-valide` depuis le 2026-10-15 au titre de `VALIDE SOUS RESERVE` : leur verdict d'aujourd'hui leve la reserve et ne change pas le statut. Meme ecart que `A.107` pour `-10`, `-13` et `-15`. `FRA-CE1-05` est fermee sans mention de couverture partielle (`grep -c -i "couverture partielle"` sur la fiche : 0).

**Compte**, par la commande de la "Methode du compte" de la matrice (`awk -F'|'`, lignes de donnees, aucune mesure partielle), sortie lue avant puis apres le geste :

| | `couvert-valide` | `en cours` | `non couvert` | `non couvrable en l'etat` | total | Taux |
|---|---|---|---|---|---|---|
| Avant (`bb322938`) | 147 | 4 | 49 | 77 | 277 | 147 / 200 = 73,50 % |
| **Apres** | **148** | 4 | **48** | 77 | 277 | **148 / 200 = 74,00 %** |

Par niveau, lignes atteignables / `couvert-valide` : CP 59 / 52, CE1 62 / 30, CM1 79 / 66. Compteur de version (`A.32`) : 148 + 4 = 152 / 200 = 76,00 % par la formule, critere 2 (CI verte sur l'arbre de la version) a lire sur cet arbre. **Le 147 / 200 des amendements de `planning.md` du 2026-10-16 (non remesure) est remplace par ce compte.**

### A.113 `Q2` de `marc-dev` : la copie fautive de `TRV-CE1-6` ne suit pas le paquet, pas de story

**Decision : non.** Le jeu fautif `ce1-pronoms-fautif.yaml` garde les anciens textes des items 3, 9, 13 et 18 ; seul *Hamza* devient *Omar* (item 16, `42071c23`).

**Motifs de forme 1.**

1. **Aucun enfant ne le lit.** Il est dans `adapters/adapter-content/src/test/resources/contreexemple/PaquetCe1PronomsTest/contenu/` (`ADR-0013`, obligation 3) et le catalogue servi ne le nomme pas (`git grep -n "ce1-pronoms-fautif" -- adapters/adapter-content/src/main` ne rend rien). Il n'entre dans aucun perimetre de `nadia-prof`.
2. **Ce qu'il atteste n'a pas change.** Son test sur `TRV-CE1-6` est `leJeuFautifCommiteEstRefuseSurSesPrenomsDesequilibres` (`PaquetCe1PronomsTest`, lignes 742 a 751) : `verifierLEquilibreDesPrenoms` refuse l'excedent de prenoms feminins distincts et cite *"TRV-CE1-6"* et *"féminins distincts"*. Il ne lit le texte d'aucun item. Les anciens textes sont donc l'entree du jeu fautif sans effet sur le verdict du test, qui reste vert (2669 tests, `planning.md` 20:15).
3. **La propriete que `nadia-prof` a refusee n'a pas de garde automatique, et je n'en demande pas.** *"La distribution des roles par genre"* est dans ce dont elle reste seule garde (fiche des pronoms, rejeu du 2026-10-16, section 4). Une copie fautive de cette propriete n'aurait aucun test a mordre.
4. **Le perimetre produit est ferme** ; la forme d'une fixture de test est a `theo-archi` et a `marc-dev`. S'ils jugent que les anciens textes trompent un lecteur futur, c'est un commentaire de test, hors backlog produit : je ne le demande pas, je ne l'interdis pas.

### A.114 `Q-N5`, non affecte de A', mots de `S2`, `Q-N4`

**`Q-N5` (indice des dix items d'affixe de `-24`) : la recommandation reste declinee.** Elle l'etait par l'annotation du backlog du 2026-10-16 17:40 ; je la confirme apres le verdict. Motifs : (1) `nadia-prof` ne donne aucun texte (*"il dit ou regarder, ce que mon critere demande"*) ; (2) *"Observation sans reserve, et je ne releve pas ma barre"* (`rejeu-nadia-palier-2-ap-lot24.md` 2.3), et la ligne est `couvert-valide` sans reserve : rien ne manque a la competence ; (3) changer le paquet d'une ligne **VALIDE** appelle un rejeu (0,25 ou 0,5 a son registre) pris sur les 8,0 du palier 3, contre des lots qui n'ont encore aucun Done. **A ne pas confondre** avec `A.103`, qui a pris la recommandation sur les **14** indices de famille (`FM6`, tenue au rejeu) : ce sont deux recommandations. **Reprise** : si `nadia-prof` ecrit l'indice, je le recopie en critere sans reformulation (precedent `FM6`) et `sofia-sm` le place. `Q-N5` reste **ouverte pour elle seule**, sans echeance ; aucune reponse n'est au depot (`git grep -n "Q-N5"` : six occurrences, toutes la question). Premiere inscription le 2026-10-16, premier non-engagement ; aucun report a compter, aucune composition ne la retient.

**Le non affecte de A' (0,25) tombe.** Registre du palier 2, seize lignes (`wc -l`), `awk -F'|' '{p[$1]+=$3; t+=$3} END{for(k in p) printf "%s %.2f\n",k,p[k]; printf "total %.2f\n",t}'` : `A 3.25`, `Ap 3.00`, `B 1.75`, **`total 8.00`** ; par etat, `facture 6.25`, `marge 1.50`, `non affecte 0.25`. Motifs : (a) `A.105` l'a ecrit : c'est *"un reste"*, pas un moyen de payer un rejeu ; (b) la borne est **par palier** (`PAR-9`, 8,0) et le precedent est ecrit : le palier 1 s'est clos avec 0,75 non affectes et 1,50 de marges intactes (`arbitrage-nadia-palier-2.md` 1), et le palier 2 a ete compose sur 8,0 sans les reprendre (`composition-palier-2.md` 2) ; (c) la capacite du palier 3 est celle que `nadia-prof` ecrira dans son arbitrage prealable (11.3, condition 3) : **je n'y ajoute rien**. Les marges inutilisees (1,50) tombent de la meme facon. Je ne l'attribue a aucun poste.

**Mots de `S2` (utilisatrice, 2026-10-12, `RP-ACCUEIL-147`) : ils sont au depot**, contrairement a ce que `cloture-palier-1.md` (`P5`) et `arbitrage-nadia-palier-2.md` (section 9) ecrivent. Deux fichiers les portent, que `git grep -n "ping nadia dessus"` rend : `DEC-0120` section 4.5 (tableau, ligne `RP-ACCUEIL-147`) et `squad/rapports/JOURNAL-gouvernance-hors-sprint.md`, entree *"2026-10-12 - Audit avant fusion pour les chantiers hors sprint ; note a reprendre au planning (`camille-po`)"* (commit `1b683ef6`, point 3 : *"Demande de l'utilisatrice"*). Les mots cites : ***"ajoute le au repo et ping nadia dessus sur le sprint prochain"***, date de l'entree 2026-10-12. **Je les recopie, je n'y ajoute rien.** Ce qui manque est autre : aucune des deux entrees ne dit par quel chemin les mots me sont parvenus (de sa main, ou transmis), donc la forme 2 de `DEC-0062` 3.1 est satisfaite sur le texte (mots entre guillemets, date) sans que la transmission soit nommee. `RP-ACCUEIL-147` est fait (`cloture-palier-1.md`, section 2) : rien n'en depend.

**`Q-N4` est au depot.** `nadia-prof` l'a inscrite le 2026-10-16 : `rejeu-nadia-palier-1.md`, section *"Annotation du 2026-10-16 (`nadia-prof`, s57, palier 2) : les mots de l'utilisatrice pour `Q-N4`"*, mots ***"le pli n'est pas bloquant"***, forme 2 ; et `FICHE-bandit-manchot-phrases-types-ce1.md`, *"Annotation du 2026-10-16"*, memes mots. `git grep -l "le pli n'est pas bloquant" bb322938 -- squad` rend six fichiers (ces deux-la, `FICHE-bandit-manchot-pronoms-ce1.md`, `FICHE-bandit-manchot-familles-de-mots-ce1.md`, `reponse-nadia-q-palier-2.md`, `planning.md`). **Ce qui manque, dit par elle** : la date de l'echange d'origine (*"n'a pas ete fournie"* ; *"le depot date la reponse relayee au 2026-10-14"*). Je ne la comble pas.

### A.115 Proposition pour le palier 3 : ordre des restes, sans engagement

**L'engagement est a `sofia-sm` au planning du palier 3 ; la capacite et les prix sont a `nadia-prof` (arbitrage prealable, condition 3).** Ce qui suit est mon ordre de priorite produit et ce que je ferai avant l'ouverture, rien d'autre. Etat de depart : 148 / 200 ; 48 `non couvert` (francais CE1 7, questionner le monde CE1 12 et CP 3, histoire des arts CM1 6, mathematiques CE1 5, enseignement moral et civique CE1 4 et CP 1, arts plastiques CE1 2 et CM1 2, histoire-geographie CM1 2, langues vivantes CM1 2, francais CM1 1 et CP 1) et 4 `en cours`.

| Rang | Quoi | Prix | Motif | Compte |
|---|---|---|---|---|
| 1 | `S57-GARDE-C1-VIRGULE-ET-IMBRIQUE`, puis `S57-GARDE-PILE-DE-POLICES` | 0, hors `PAR` (`theo-archi`) | Dette de garde sur `recreo.css` : `CR25` et `A.108` les placent en tete et **avant tout poste qui modifie la feuille**. `A.101` est payee pour la story d'origine (`S57-GARDE-C1-SUJET-HORS-MESSAGE`, fusionnee : `e138ec97`) ; la dette restante est sa suite. Si aucun poste du palier 3 ne modifie la feuille, elles n'ont pas de prealable a tenir et restent en tete | premiere inscription le 2026-10-16, aucun report |
| 2 | Commit de `RL5`, `RL6`, `RL7` (trois commentaires devenus faux) | hors `PAR`, aucun prix | Engage par `sofia-sm` le 2026-10-16 (`planning.md` 19:40, section 4), **non livre a la cloture** (`git log bb322938 --oneline` n'en nomme aucun). A livrer avant tout autre commit de code du palier 3 ; le commentaire de `manche.html` peut changer l'octet servi, a mesurer par le developpeur | premier glissement d'un poste engage, ecrit ici (le commit n'entre dans aucun lot) |
| 3 | `-06` (`ce1-inference`), puis `-07` (`ce1-texte-documentaire`), puis `-11` (`ce1-negation`), groupe X, dans l'ordre de `nadia-prof` (`arbitrage-nadia-palier-2.md` 7 : *"prelever (`-04`, servi), puis suivre une reprise, puis deduire"* ; `-07` porte le risque de culture) | 1,0 chacun (bareme de `nadia-prof`) | Une ligne instruite par lot. **`-06` est le troisieme ecartement s'il n'entre pas au palier 3** (`composition-palier-2.md` 9) | voir ci-dessous |
| 4 | `S57-GARDE-NOMBRE-DE-MANCHES` | 0, hors `PAR` | *"Aucun ecran ne change"*, aucun rejeu (backlog) : rien a attendre de `nadia-prof` | premiere inscription au palier 2, non retenue : pas un report |
| 5 | `S57-TYP1-TEXTES-DE-GABARIT`, puis `S57-MESSAGE-PARTIE-PERDUE` | 0, hors `PAR` (`Q-T7` repondue : aucun rejeu, `reponse-nadia-q-palier-1.md`) | Rangs 7 et 8 de `nadia-prof` : *"Score :"* est lu a chaque manche ; la seconde attend d'abord la mesure du nombre de lignes a 744 et a 1200 | premiere inscription au palier 2, non retenues : pas un report |

**Compte des ecartements de `-06`, `-07`, `-11` (groupe X), ecrit ici.** Premier ecartement : composition du palier 1, 2026-10-14 (*"restent pour un palier suivant"*, `composition-palier-1.md` 3). **Deuxieme : composition du palier 2, 2026-10-15** (`EC1`, un troisieme lot de X ne tient pas a 7,75 + 1,0). A ce rythme (un lot de X par palier), `-06` entre au 3, `-07` au 4, `-11` au 5. **Je ne tiens pas ce rythme pour acquis** : le palier 3 n'a plus de reserve a rejouer ni de rejeu de refus a financer, la contrainte de `EC1` ne s'y pose pas dans les memes termes, et trois lots dans A restent a la borne de `PAR-8` (trois lots, 5,0 par instance). **Si `-06` n'est pas retenu au palier 3, une `DEC` le dit avec son motif (troisieme ecartement)** ; de meme pour `-07` et `-11` au palier ou ils atteindraient leur troisieme. Aucune de ces trois lignes n'a de story (`grep -c "^### S57-BR-FRA-CE1-06"` rend 0, de meme `-07` et `-11`) et `nadia-prof` n'arbitre pas un lot sans story (arbitrage, section 8, point 9) : **j'ecrirai la story de `-06`, puis celles de `-07` et `-11`, a l'ouverture du palier 3 et avant son arbitrage prealable**, pas avant : `EC1` dit qu'elles s'ecrivent quand un palier les compose.

**Lots d'A' et de B : je n'en propose aucun.** Une ligne sans paquet instruit n'est pas un lot (arbitrage, section 8, point 6), et le terrain neuf reste ferme tant qu'A' existe (`PAR-13`, condition 5). `nadia-prof` dit au palier 3 quelles lignes sont instruites ; ce que le tableau ne contient pas n'est ni refuse ni decide.

### A.116 Routage, ce que cet amendement ne fait pas

| A qui | Quoi |
|---|---|
| `sofia-sm` | Amender le planning : `Q-U11` close (A.111), `Q2` decidee (A.113), non affecte de A' tombe (A.114), matrice a 148 / 200 (A.112), `Q-N5` declinee ; porter la proposition de A.115 a la composition du palier 3 ; `retro.md` (la lecon du sommet de rejeu est a elle) |
| `nadia-prof` | `Q-N5`, sans echeance ; arbitrage prealable du palier 3 ; les deux fichiers qui portent les mots de `S2` (A.114), si elle veut corriger `D3` et la section 9 de son arbitrage |
| `theo-archi` | Rien de neuf : forme des deux gardes du rang 1 (`CR26`, `CR27`, `CR39`) |
| `relecteur-pedagogique` | Orthotypographie de cet amendement et des cellules de matrice mises a jour |

- **Ne fait pas** : ne prononce aucun Done, ne juge aucun contenu, ne touche ni `domain/`, `application/`, `adapters/`, `bootstrap/`, ni catalogue, paquet, test, `planning.md`. N'engage aucun poste. Ne fixe aucun prix. Ne dit pas que la CI distante est verte.
- **Jobs de `ci.yml` lances pour son commit** : nommes dans le message de livraison ; ceux qui ne sont pas lances ne sont pas deduits.

## Amendement du 2026-10-21, dix-neuvieme (`camille-po`, palier 3 : ordre tenu et ecart au rapport, reports comptes, prix, reponses de l'utilisatrice `Q-05` a `Q-10`, matrice, ce que le palier n'a pas ecrit comme regle)

Annotation datee. **Rien au-dessus n'est reecrit** ; ce que cet amendement corrige est nomme (A.117 : la phrase de `A.115` rang 2). Etat de l'arbre : `round-two` a `b52c4ac5` (`git status` vide a la lecture). **Ce que je n'ai pas fait** : lancer `mvn`, jouer un jar. Les faits de git sont lus par `git merge-base --is-ancestor` et `git log` ; les verdicts sont ceux de `nadia-prof` (`verdict-nadia-palier-3-ce1.md`, `arbitrage-nadia-q05-q10.md`), relayes sans etre juges. Dossier : `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/`.

### A.117 L'ordre du palier 3 : propose, confirme, non decide, et tenu

**Statut de l'ordre, dans l'ordre des fichiers.** `A.115` le donne *"sans engagement"* (rang 1 `Z1`/`Z2`, rang 2 `RL5` a `RL7`, rang 3 les lots) ; `theo-archi` confirme la lecture d'architecture (`reponses-theo-questions-stories-palier-3.md`, ligne `ORD` : `RL5` a `RL7` precede chaque lot, `Z1`/`Z2` ne lie les lots que par le rang) ; `sofia-sm` l'engage *"comme ordre de planning, pas comme decision de produit ou d'architecture"* (`planning.md`, amendement du 2026-10-19 23:09, section 3). **Aucune `ADR` ni `DEC` ne le decide, et cet amendement non plus : il constate ce qui a ete fait.**

**Correction de `A.115` rang 2** (ma reponse a `AD5` du 2026-10-19 23:44, `preparation-palier-3-ce1-camille.md`, jamais portee ici) : *"avant tout autre commit de code"* se lit *"avant tout commit de lot"*. Le rang 1 est a moi dans la meme decision, il ne peut etre l'*"autre"* du rang 2. `Z1`/`Z2` et `RL5` a `RL7` ne se portent pas l'un l'autre ; seule `RL5` a `RL7` est une porte des lots.

**Ce que git montre** (2026-10-20, heures de `git log`) :

| Poste | Commit | Fusion | Heure du commit |
|---|---|---|---|
| `RL5` a `RL7` | `4bf3c359` | `20470501` | 00:07 |
| commentaires d'en-tete de paquet (`R6` du releve) | `e6c371ad` | `91ecf180` | 00:14 |
| lots (cinq tetes : `b4ab00d6` `-06`, `fba84a0d` `-07`, `9921ddee` `-11`, `bd99abcf` `MAT-CE1-01`, `43cd625d` `EMC-CE1-01`) | -- | `59d6f245` a `5d3d7a96` | 08:05 a 08:21 |
| `Z1`/`Z2` | `78b260e3` | `55328470` | 08:07 |
| pile de polices | `558dd887` | `4650aeab` | 09:43 |

`git merge-base --is-ancestor 4bf3c359 <tete>` sort **0 pour les cinq tetes de lots** : la porte de `RL5` a `RL7` est tenue. Les commentaires d'en-tete (`e6c371ad`) ne sont ancetres que de la tete d'`EMC-CE1-01` : ce n'est pas une porte des lots (`AD7` : *"pas avant les commits de lot"*), c'est une porte du premier jar rejoue, et `91ecf180` est ancetre de la base de ce jar. `Z1`/`Z2` n'est pas premiere dans le temps (commit a 08:07, apres `RL5` a `RL7` et apres les commentaires d'en-tete) : sans consequence, elle n'a aucune porte.

**Ecart trouve en verifiant, a `sofia-sm`** : `rapport-sofia-dev-palier-3.md`, section 6, ecrit que cinq branches sont *"coupees sur `b9c6cb69`, avant `RL5` a `RL7` et `R6`"*. `b9c6cb69` est **posterieur** a la fusion de `RL5` a `RL7` (`20470501`) et anterieur a celle des commentaires d'en-tete (`91ecf180`) : vrai pour `R6`, **faux pour `RL5` a `RL7`**. L'ordre n'en est pas touche ; la phrase du rapport l'est. Je ne modifie pas son fichier.

### A.118 Reports et glissements du palier 3, comptes

Regle d'`A.66` a `A.73` et `A.83`, appliquee comme aux paliers 1 et 2.

| Poste | Etat a l'ouverture (2026-10-19) | Sort au palier 3 | Compte |
|---|---|---|---|
| `-06`, `-07`, `-11` | engages, deuxieme ecartement (paliers 1 et 2) | `-06` **VALIDE SOUS RESERVE**, `-11` **VALIDE SOUS RESERVE** (`9d892663`) ; `-07` **REFUSE** le meme jour (`9d892663`) puis **VALIDE**, aucune reserve (`c17c3f9e`) | deuxieme ecartement **solde par l'entree** ; pas de troisieme |
| `MAT-CE1-01`, `EMC-CE1-01` | non engages a l'ouverture de l'amendement de 23:09, engages a 23:44 (`AD1`) | `MAT-CE1-01` **VALIDE**, plafond `en cours` ; `EMC-CE1-01` **VALIDE SOUS RESERVE** (`9d892663`), tous deux dans le palier | **premier report chacun, ecrit et solde** (`A.66`, `A.83` : l'engagement tardif n'efface pas le report, il evite le suivant) ; aucun second |
| `RL5` a `RL7` | engages (`AD2`), premier glissement (`A.115` rang 2, `planning.md` 21:46 section 9) | livres, `4bf3c359`, fusion `20470501` | **pas de second glissement** |
| `Z1`/`Z2`, `S57-GARDE-PILE-DE-POLICES`, `S57-R6-COMMENTAIRES-DE-PAQUET` | premiere inscription (`AD6`, `AD7`) | livres (`55328470`, `4650aeab`, `91ecf180`) | aucun report |
| `S57-GARDE-NOMBRE-DE-MANCHES`, `S57-TYP1-TEXTES-DE-GABARIT`, `S57-MESSAGE-PARTIE-PERDUE` | hors composition (`composition-palier-3.md` section 3) | non engagees | **deuxieme ecartement.** Premier : s57, palier 2 (composition du 2026-10-15, ecrit en `A.115` le 2026-10-16 : *"premiere inscription au palier 2, non retenue : pas un report"*). **Un troisieme, au palier 4, serait une direction que personne n'a choisie** : `preparation-palier-4-camille.md` les propose |

**Lignes ecartees du palier 3, premier ecartement** (`preparation-palier-3-ce1-camille.md` 8.5 : *"le palier 3 est leur premier ecartement, pas un report repete"*) : `MAT-CE1-10`, `-11`, `-14`, `-15`, `FRA-CE1-03`, `-21`, `EMC-CE1-02`, `-03`, `-04`. Un second ecartement au palier 4 s'ecrit dans `preparation-palier-4-camille.md`, ligne par ligne. Les lignes a dessin ne sont plus un report : l'utilisatrice les a sorties du perimetre (`DEC-0123` 3.3).

### A.119 Prix du palier 3 : registre, consomme, marges, ecarts

Registre commite : `registre-prix-palier-3.txt` (onze lignes, `wc -l`). Commandes lues ce jour : `awk -F'|' '{p[$1]+=$3; t+=$3} END{for(k in p) printf "%s %.2f\n",k,p[k]; printf "total %.2f\n",t}' registre-prix-palier-3.txt | sort` rend `A 4.00`, `Ap 2.00`, `B 2.00`, **`total 8.00`** ; par etat (`awk -F'|' '{e[$4]+=$3} END{for(k in e) printf "%s %.2f\n",k,e[k]}' registre-prix-palier-3.txt | sort`) : `facture 6.50`, `marge 1.50`. Lots : `awk -F'|' '$2 ~ / lot$/{t++} END{print t}' registre-prix-palier-3.txt` rend 5.

| Instance | Composition | Facture | Detail | Marge |
|---|---|---|---|---|
| A (groupe X) | 4,00 | **3,50** | arbitrage 0,5 ; `-06`, `-07`, `-11` 1,0 chacun | 0,50 |
| A' | 2,00 | **1,50** | arbitrage 0,5 ; `EMC-CE1-01` 1,0 | 0,50 |
| B | 2,00 | **1,50** | arbitrage 0,5 ; `MAT-CE1-01` 1,0 | 0,50 |
| **total** | **8,00** | **6,50** | | **1,50** |

**Verification de la demande** : *"6,50 engage et 1,50 de marge"* (`composition-palier-3.md`, section 12.2) est juste : 3 x 0,5 + 5 x 1,0 = 6,5 ; 3 x 0,5 = 1,5 ; 6,5 + 1,5 = 8,0. Aucun ecart de somme avec le planning (`planning.md` 23:09, section 2 : *"engage 4,50, non engage 2,0"* avant `AD1` ; 23:44 : *"engage 6,50"* apres), avec `preparation-palier-3-ce1-camille.md` 8.2 (A 4,0, B 2,0, A' 2,0) ni avec `reponses-nadia-questions-stories-palier-3.md` 7.2. Bornes : somme 8,0 (`PAR-9`) ; plafond de 5,0 par instance, A est a 4,0 (`PAR-8`) ; trois lots au plus, A en porte trois.

**Ce que le registre n'ecrit pas, et pourquoi.** `nadia-prof` n'a ecrit **aucun prix consomme** pour le palier 3 : ni champ *"Tarif consomme"* ni ligne de routage de prix dans ses rapports de palier (`grep` sur `verdict-nadia-palier-3-ce1.md`, `lecture-nadia-palier-3.md`, `veto-nadia-gabarit-pli.md`, `arbitrage-nadia-q05-q10.md`, les quatre fiches ouvertes). Les cinq lots sont inscrits **`facture` a leur prix de composition** (arbitrage 0,5 par instance livre, lot a 1,0 avec verdict ecrit). **Le rejeu de `-07`** (refus a 13:46, correction `ac986aca`, rejeu et releve a l'ecran, verdict `c17c3f9e`) **n'a aucun prix ecrit** : au palier 2, un rejeu apres refus valait 0,5 (`A.105`, `-05`), et `nadia-prof` route ici *"les rejeux sur la marge de chaque instance"* (`lecture-nadia-palier-3.md`, section 8). Si elle l'ecrit a 0,5, la marge de A (0,5) est consommee en entier : A passe a 4,00 facture et 0 de marge, le palier a 7,00 facture et 1,00 de marge ; **le total par instance ne change pas** (A 4,00) et le fichier se corrige d'une ligne. **Je ne l'ecris pas a sa place.** Les marges de B et d'A' sont intactes jusqu'a preuve du contraire : aucun rejeu ne les touche. Le non affecte du palier est 0, rien ne tombe.

### A.120 Les reponses de l'utilisatrice `Q-05` a `Q-10` et ce que le palier en a fait

Toutes relayees par l'agent lanceur le 2026-10-21 (forme 2 de `DEC-0062`) : **je ne les ai pas lues de sa main**, et je ne les date pas plus precisement que le jour. Consignees en entier par `DEC-0123` (`Q-05` a `Q-10`, `Q-09` suspendue) et `DEC-0124` (`Q-09`). Ce qui suit dit ce que `nadia-prof` et la squad en ont fait.

| Question | Ses mots, relayes | Ce qui en est fait | Ou |
|---|---|---|---|
| `Q-05`, `MAT-CE1-14` | *"ajouter une grille pour infos a l'enfant"* | le refus d'avance du paquet textuel tombe des que la grille est montree ; `DEP-CE1-2` n'est leve en entier que par une designation sur la grille ; mon choix de forme (b), repli (a) ; prealables ouverts (`theo-archi`, `QP3`) | `arbitrage-nadia-q05-q10.md` 1 ; backlog, amendement de `S57-BR-MAT-CE1-14-GRILLE` |
| `Q-06`, dessins | *"mettre ca hors contexte pour le moment avec ta justification"* | `QLM-CE1-09`, `-11`, moitie dessinee de `QLM-CE1-07`, `MAT-CE1-02`, `-09` hors perimetre ; direction choisie, plus un report ; condition de retour : une nouvelle reponse | `DEC-0123` 3 |
| `Q-07`, `-08` et `-09` | *"demander a nadia prof"* | **la condition 4 tient pour tout s57** ; a s58 au plus un des deux par sprint, `-09` d'abord ; stories ecrites, non engagees | `arbitrage-nadia-q05-q10.md` 2 |
| `Q-08`, premisse de l'adulte | *"oui, premisse etendue"* | etendue a `FRA-CE1-27` et `FRA-CE1-29` **seules** ; `TRV-CE1-1` a annoter par `nadia-prof` (non fait) ; amendement de `CLAUDE.md` a `sofia-sm` (non fait) | `DEC-0123` 5 ; `arbitrage-nadia-q05-q10.md` 3 |
| `Q-09`, saisie | *"la saisie clavier ne corrige pas le mot donc plus de refus"* (deux lectures), puis *"Q9 il n'y aura pas de correction automatique du clavier"*, puis, apres sa verification sur tablette, *"a toujours a oui et b a non sur tablette uniquement, pour moi c'est bon R6 peut etre leve"* | `autocorrect="off"` pose (`5a0b55f3`, fusion `03de0f6d`) ; **`R6` leve par son arbitrage**, avec une reserve non bloquante que `nadia-prof` ecrit (*"sur la tablette, des mots sont proposes au-dessus du clavier ; un enfant qui en touche un n'a pas ecrit le mot"*), portee par les cellules de `FRA-CP-09`, `FRA-CE1-21`, `FRA-CE1-29`, `LVE-CM1-06` ; l'ordinateur n'est pas mesure | `DEC-0124` et ses deux amendements ; `arbitrage-nadia-q05-q10.md` 7 et 8 |
| `Q-10`, arts plastiques | *"ouverture instruction"* | instruction ecrite (forme, corpus acceptable, droits) ; **le corpus manque** ; terrains neufs, pas a s57 | `arbitrage-nadia-q05-q10.md` 5 ; backlog, `S57-ART-CE1-01-ET-02` |

**Lecture de `Q-09`, et ce qu'elle a coute.** La premiere phrase de l'utilisatrice avait deux lectures (`DEC-0123` 7.3) ; la seconde les a departagees ; la troisieme a leve `R6` sur un fait de tablette. `nadia-prof` n'a pas rouvert la question que sa propre section 7.1 avait renvoyee a l'utilisatrice (8.2 : *"je ne reprends pas sur mon autorite de Done une question que j'ai renvoyee a la sienne"*).

### A.121 Ecarts trouves en lisant le palier, avec leur destinataire

| Ecart | Constat | A qui |
|---|---|---|
| Rapport de developpeuse, section 6 | *"avant `RL5` a `RL7`"* contredit par `git merge-base` (A.117) | `sofia-sm` |
| `S57-SAISIE-CLAVIER-SANS-CORRECTION` | livree par `theo-archi` avec trois ecarts a mes criteres : `spellcheck` non pose (accepte, c'est son jugement sur le moyen), l'avis mesure sur les deux appareils jamais ecrit (remplace apres coup par la tablette de l'utilisatrice, l'ordinateur non mesure), `ADR-0058` et son index dans le commit | amendement de la story, backlog ; `QP4` a `theo-archi` |
| `DEC-0124` et `DEC-index.md` | disaient *"non appliquee"* apres l'application | corrige par amendement de `DEC-0124` et nouvelle ligne de statut de l'index |
| `FRA-CE1-27`, `-29` | restent `non couvrable en l'etat` alors que le produit les a rendues atteignables (A.123) | moi, apres l'annotation de `nadia-prof` |
| Reserves du palier 3 sans story | `nadia-prof` demande que les reserves de `-06`, `-11` et d'`EMC-CE1-01` deviennent des stories (`verdict-nadia-palier-3-ce1.md`, section 7) : la reponse attendue sous le pli a la revelation (`-06` 14, 16, 19, 20 ; `-11` 13 ; EMC 13), le verdict et l'indice coupes de `-06` 14 apres une erreur, la coupure d'un mot a trait d'union (`-06` 14, EMC 13), l'item 17 d'EMC. **Non ecrites ici** : `preparation-palier-4-camille.md` les compte et demande si elles entrent | moi |

### A.122 Regle de procedure : aucune neuve, et `E5` reste ouverte

**Le palier 3 n'a ecrit aucune regle de procedure neuve** (je n'en trouve aucune dans `planning.md`, `composition-palier-3.md`, les arbitrages et verdicts de `nadia-prof`, ni le releve du `relecteur-pedagogique`), **et je n'en invente pas.** Ce que les fichiers montrent sans l'ecrire comme regle : `DEC-0123` et `DEC-0124` citent chaque reponse de l'utilisatrice entre guillemets, la disent relayee et non lue de sa main, et la lecture que j'en fais est ecrite a cote ; et `nadia-prof` a rectifie le 2026-10-21, sur mon constat que l'attribution ne se retrouvait nulle part, ce qu'elle attribuait a l'utilisatrice (*"le pli et les coupures"*, `verdict-nadia-palier-3-ce1.md`, rectification) : l'utilisatrice avait dit le pli, pas les coupures, et la coupure est une decision de `nadia-prof` sur sa seule autorite.

**`E5` (la regle pour une reponse relayee) est ouverte depuis `cloture-palier-1.md` (2026-10-15), a moi, et l'est encore a la fin du palier 3 : troisieme palier.** C'est un report que je compte ici. Je ne l'ecris pas en regle sur ces cas : trois DEC suivent une meme pratique, c'est un constat, pas un texte a ecrire sans l'utilisatrice.

### A.123 Matrice : rien ne bouge aujourd'hui, un reclassement est annonce

**Etat** : `couvert-valide` 152, `en cours` 5, `non couvert` 43, `non couvrable en l'etat` 77, total 277, denominateur atteignable 200, **152 / 200 = 76,00 %** (commande de la "Methode du compte" de `MATRICE-couverture.md`, lancee ce jour, sortie lue). Compteur de version (`A.32`) : 152 + 5 = 157 / 200 = 78,50 % par la formule. Aucune cellule ne change dans cet amendement.

**Reclassement annonce, decide, non applique.** `FRA-CE1-27` (moitie *ecouter*) et `FRA-CE1-29` (*mots seuls*) sont `non couvrable en l'etat` dans la matrice ; la reponse de l'utilisatrice a `Q-08` (la premisse de l'adulte etendue a ces deux lignes, **une decision de l'utilisatrice**, seul acte que la section "Comment la lire" admet pour une ligne dans cet etat) et l'arbitrage de `nadia-prof` du 2026-10-21 (3.1 et 3.2 : servables, plafond `en cours`) les rendent atteignables. **Je les passe a `non couvert`, une a une, quand `nadia-prof` a annote `TRV-CE1-1` pour elles** (`grep -c "2026-10-21" squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` rend 0 : l'annotation n'existe pas). Chaque passage ajoute 1 au denominateur atteignable, **par le seul effet du denominateur** (precedent : `A.46` a `A.52`) : 201 apres la premiere, 202 apres la seconde, soit 152 / 202 = 75,25 % a numerateur egal, et un maximum de version qui passe de 200 a 202. **L'utilisatrice l'a fixe a 200** (`Q-U10`, `A.38`) : elle doit le savoir avant que je le change, et `preparation-palier-4-camille.md` le lui pose (`QP5`).

### A.124 Palier 4 : renvoi

La proposition de palier 4 (lots candidats, composition a 8,0, ce qui est pret et ce qui exige une reponse de l'utilisatrice, ce qu'il ne faut pas engager) est dans `preparation-palier-4-camille.md`. Les stories manquantes sont a la fin de `docs/backlog/backlog.md` (annotation *"Palier 4 de s57"*), **non engagees, prix de `nadia-prof`**. **Aucun poste n'est engage par cet amendement** : l'engagement est a `sofia-sm`, la capacite et les prix a `nadia-prof`.

### A.125 Routage, ce que cet amendement ne fait pas

| A qui | Quoi |
|---|---|
| `sofia-sm` | Amender `planning.md` : consommation et reports du palier 3 (A.118, A.119), section 6 du rapport (A.117), `cloture-palier-3.md` ; ouvrir le palier 4 par amendement date avec la composition de `camille-po` et l'arbitrage de `nadia-prof` ; amender `CLAUDE.md` si elle juge que le CE1 doit y figurer (`DEC-0123` 5.4) |
| `nadia-prof` | Prix de rejeu de `-07` (A.119) ; annotation de `TRV-CE1-1` (A.123) ; arbitrage prealable du palier 4 par instance ; instance de `FRA-CE1-21` ; avis sur la section 3 de la decision de garde CE1 (`CA4`) ; jugement de cousinage de `FRA-CE1-03` si je le demande |
| `theo-archi` | `QP1`, `QP2`, `QP4` ; avis sur `MAT-CE1-14` (`A3`, un ou deux paquets, cliquet) ; double preuve d'une photographie |
| `relecteur-pedagogique` | Orthotypographie de cet amendement, des stories du backlog et du fichier de preparation |
| Utilisatrice | `QP3`, `QP5`, `QP6` et `QP7` de `preparation-palier-4-camille.md` |

- **Ne fait pas** : ne prononce aucun Done, ne juge aucun contenu, ne touche ni `domain/`, `application/`, `adapters/`, `bootstrap/`, ni catalogue, paquet, test, `composition-palier-3.md`, `cloture-palier-3.md`. N'engage aucun poste, ne fixe aucun prix. **Decisions de produit prises ici** : (1) `A.115` rang 2 se lit *"avant tout commit de lot"* ; (2) pour `MAT-CE1-14`, la forme visee est (b), le repli (a) ; (3) l'ecart de `S57-SAISIE-CLAVIER-SANS-CORRECTION` au critere 1 (`spellcheck`) est accepte ; (4) `FRA-CE1-27` et `-29` passent a `non couvert` apres l'annotation de `TRV-CE1-1`. `DEC-0120` reste `Acceptee`.
- **Jobs de `ci.yml` lances pour son commit** : nommes dans le message de livraison ; ceux qui ne sont pas lances ne sont pas deduits.

## Amendement du 2026-10-21, vingtieme (`camille-po`, consigne de l'utilisatrice sur le seuil du palier 4 : plus de 8,0, jusqu'a trois instances en parallele)

Annotation datee. **Rien au-dessus n'est reecrit.** Arbre : `round-two` a `b52c4ac5`. Ce que je n'ai pas fait : lancer `mvn`, jouer un jar. La borne est une borne de `nadia-prof` : **je ne la change pas, je la lui demande.**

### A.126 La consigne, ses mots, ma lecture

Mots de l'utilisatrice, 2026-10-21, relayes par l'agent lanceur, **non lus de sa main** : *"pour le palier 4 augmente le seuil avec des instances en // jusqu'a 3 si besoin"*. Ma lecture, **a confirmer par elle** :

| Mot | Lecture | Source |
|---|---|---|
| *"le seuil"* | la borne de 8,0 d'audit par palier | `PAR-9`, `ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md` 4.3 (`D7`, `O1`), `DEC-0120` A.28 (*"Borne de s57 : 8,0 pour trois instances"*) |
| *"des instances en //"* | les instances de `nadia-prof`, A, A' et B, qui lisent en parallele | `DEC-0120` A.36 |
| *"jusqu'a 3 si besoin"* | les trois instances ouvertes, ce qui est deja le cas depuis le palier 1 ; **ou** un plafond de trois instances qu'elle ne veut pas depasser. Les deux lectures donnent la meme composition | A.33 |

### A.127 Ce que disent `PAR-8` et `PAR-9`, et ce que la consigne change

Les mots, sans les reecrire (`ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`, 11.3 et 4.3) :

- `PAR-8` : *"8,0 a deux ou trois instances, 5,0 a une ; au plus 5,0 et trois lots par instance"*. Par palier : *"Par instance : 5,0 et trois lots par palier, inchanges."*
- `PAR-9` : *"la somme des budgets egale la borne"*.
- La borne : *"Decision : O1. La borne de s57 est 8,0, pour trois instances. O2 n'est pas derivable [...] Je ne pose aucun chiffre au-dela de 8,0, ni 12,0 ni un autre."* Un chiffre au-dela de 8,0 *"ne se derive que si trois conditions cumulatives tiennent"* : (i) deux instances remplissent 8,0 et laissent dehors au moins 4,0 de lots instruits prenables par la troisieme ; (ii) le dernier sprint parallele clos est sans occurrence des formes (a), (b), (c) ; (iii) `PAR-13`.

**Ce que la consigne change.** Le plafond **par instance** (5,0 et trois lots) ne bouge pas : sur trois instances, le maximum theorique de `PAR-8` est 15,0 et neuf lots. **La borne `PAR-9` est a `nadia-prof` et vaut 8,0** : relever le seuil, c'est qu'elle ecrive une borne neuve (`O2`) et la capacite de chaque instance dans son arbitrage prealable du palier 4. **Je ne peux pas ecrire cette borne, et je ne la presume pas.**

**Mesure des trois conditions, la seule part qui m'est demandee** (4.3 : *"Le montrer est a `camille-po`"*) :

| Condition | Etat |
|---|---|
| (i) | **non montree.** Les lots prets sont six (`LP1` a `LP6`). B et A' en portent cinq (`LP2` a `LP6`) : `LP1` (1,0) reste dehors ; avec `LP7` dans B, `LP1` et `LP8` (2,0). **Moins de 4,0 dans les deux cas.** Les lots qui feraient 4,0 (`MAT-CE1-14` forme (b)) ne sont pas prets |
| (ii) | **non verifiee par moi** : c'est la lecture de `nadia-prof` des transcriptions du dernier sprint parallele ; `rapport-qualite-palier-3.md` et la section "Obstacles" de la cloture du palier 3 ne sont pas ecrits |
| (iii) | tenue : aucun terrain neuf dans la composition |

Une consigne de l'utilisatrice n'est pas une des trois conditions, et je ne dis pas qu'elle la remplace : **c'est a `nadia-prof`, sur cette consigne, de dire si elle ecrit `O2`, avec quel chiffre, ou si elle maintient 8,0 et le dit.** Je lui pose la question avec la mesure ci-dessus.

### A.128 Composition maximale defendable, par instance

Prix de `nadia-prof` (arbitrage de preparation du palier 3, section 2 ; arbitrage du 2026-10-21) : arbitrage 0,5 et marge de rejeu 0,5 par instance, lot neuf textuel 1,0, lot illustre 1,5. Trois instances ouvertes : 3,0 fixes. Detail : `preparation-palier-4-camille.md`, sections 5 et 5bis.

| Composition | Total | Lots | A | B | A' | Condition |
|---|---|---|---|---|---|---|
| **8,0** (repli, si `nadia-prof` maintient la borne) | 8,0 | 5 | `FRA-CE1-21` | `MAT-CE1-10`, `-11` | `EMC-CE1-02`, `-03` | aucune |
| **9,0, pret** | **9,0** | **6** | `FRA-CE1-21` (2,0) | `MAT-CE1-10`, `-11` (3,0) | `EMC-CE1-02`, `-03`, `-04` (4,0) | une borne de 9,0 ecrite par `nadia-prof` |
| **11,5, conditionnel** | 11,5 | 8 | `FRA-CE1-21`, `FRA-CE1-03` (3,0) | `MAT-CE1-10`, `-11`, `MAT-CE1-15` illustre (4,5) | idem (4,0) | `O2` a 11,5 ; cousinage de `FRA-CE1-03` juge par `nadia-prof` ; developpeur et paquet reecrit de `MAT-CE1-15` |

**Le neuvieme lot n'existe pas.** A n'a que deux lignes de francais CE1 recevables (`-21` et, sous reserve, `-03`) : `-08`, `-09` et `-29` sont a s58 (condition 4 et levee de `R6`), `-27` est un terrain neuf. Je n'invente pas un lot pour atteindre 15,0. Le maximum que je defends est **9,0 pour six lots prets**, **11,5 pour huit** si deux conditions se levent ; un `MAT-CE1-14` en forme (b) ferait trois lots dans B au lieu de `MAT-CE1-15`, a 3,0, et attend `theo-archi` et `QP3`. Gain du 9,0, si les verdicts tombent : `couvert-valide` 152 a 156 (78,00 %), `en cours` 5 a 7, compteur 163 / 200 = 81,50 %. Gain du 11,5 : `en cours` 9, compteur 165 / 200 = 82,50 %.

### A.129 Ce qui doit etre amende, par qui

| Qui | Quoi |
|---|---|
| `nadia-prof` | **La borne** : `ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md` 4.3 et 11.3 (`D7`, `PAR-9`), par un document date qui ecrit `O2` et son chiffre pour le palier 4, ou qui maintient 8,0 ; la **capacite de chaque instance** dans l'arbitrage prealable du palier 4 ; instance et prix de `FRA-CE1-21` ; cousinage de `FRA-CE1-03` ; confirmation que `PAR-13` tient pour `FRA-CE1-21` |
| `sofia-sm` | La composition du palier 4 et son amendement de `planning.md` ; les regles d'isolation et de charge (A.130) ; l'ordre de fusion ; `cloture-palier-3.md` |
| `camille-po` | Cette proposition, les stories (fin de `backlog.md`), la matrice apres chaque verdict, `DEC-0120` |
| Utilisatrice | Confirmer la lecture de A.126 ; `QP5`, `QP6`, `QP7` de `preparation-palier-4-camille.md` |

**Je n'amende ni `PAR-8`, ni `PAR-9`, ni 4.3 : ils sont a `nadia-prof`.** Cet amendement ne dit pas que la borne est relevee.

### A.130 Le parallele : ce que le palier 3 a mesure, et ce qui est a `sofia-sm`

Fait lus : `rapport-sofia-dev-palier-3.md`, sections 6 a 16. **Chiffres relayes par l'agent lanceur, que je n'ai pas lus dans un fichier** : un `verify` complet a dure 7 h 26 au mur quand quatre agents compilaient ; des arrets du chien de garde de `marc-dev` et de `lea-dev`. Le depot porte, lui, des `verify` de 9 min 30 s a 12 min 40 s sur un worktree propre (sections 8 a 16 du meme rapport).

| Risque | Mesure du palier 3 | Ce qui change avec six ou huit lots |
|---|---|---|
| Contention de build | l'ecart de 12 min a 7 h 26 est l'effet de la concurrence, pas du code | trois instances compilent au meme moment ; **`RG4` (un worktree vivant a la fois par developpeur) reste la seule limite**, donc un developpeur de B ou d'A' porte trois lots l'un apres l'autre. Le parallele est **entre** instances, pas dans une instance : la regle de `sofia-sm`, pas la mienne |
| `catalogue.yaml` et inventaire de `TexteAccentueExposeParLePortEntrantTest` | quatre fusions de lots sur cinq ont eu un conflit (section 6), chacune resolue par ajout des deux cotes, aucune assertion modifiee | sept a huit fusions, autant de conflits attendus, chacune suivie d'un `mvn -B clean verify` entier a la racine : huit fusions a 12 min = 96 min, sans contention |
| Un seul registre d'identifiants | le `verify` est rouge des qu'un fichier non suivi d'un autre agent ecrit un identifiant non alloue (`PL-1` a `PL-13`, sections 7, 9 et 12 du rapport) | **mes stories n'allouent aucun identifiant** ; chaque developpeur n'en ecrit aucun ; un identifiant neuf est route a `theo-archi` avant d'etre ecrit |
| Matrice, `DEC-0120`, `DEC-index.md`, backlog | edites par moi seule | huit verdicts arrivent dans le desordre : une seule edition de la matrice par lot de verdicts, la commande de la "Methode du compte" relancee apres chacune, jamais deux branches de `camille-po` en meme temps |
| Audit | `nadia-prof` lit par instance | trois instances, trois lectures completes avant chaque premier verdict (`LC`) : sa borne est une fenetre d'attention, c'est ce que sa decision porte |

**Ce qui est a `sofia-sm`, que je ne tranche pas** : l'arret et la reprise apres un arret du chien de garde (une branche dont le developpeur est arrete reprend-t-elle sur le meme worktree ?), le nombre de `verify` simultanes, l'ordre de fusion, les lignes de la table d'isolation de trois instances sur plus de lots, la lignee de chaque branche (`verifier-lignee.sh`).

### A.131 Routage, ce que cet amendement ne fait pas

Ne prononce aucun Done, n'engage aucun poste, ne fixe aucune borne ni aucun prix, ne touche ni `PAR-8`, ni `PAR-9`, ni `composition-palier-3.md`, ni `cloture-palier-3.md`, ni la matrice. Aucun identifiant de condition neuf. `DEC-0120` reste `Acceptee`.

## Amendement du 2026-10-21, vingt et unieme (`camille-po`, confirmation par l'utilisatrice des mots relayes dans les decisions des paliers 3 et 4)

### A.132 Les mots relayes sont confirmes, a la lettre : forme 2 de `DEC-0062`, pour la liste seulement

**La question**, telle que l'agent lanceur l'a posee a l'utilisatrice le 2026-10-21 : *"Confirmez-vous, tels quels, les mots que je vous ai relayes dans les decisions du palier 3 et du palier 4 ? Ils sont : "ok" a ma recommandation sur AD1, AD2 et AD4 ; vos reponses a Q-05, Q-06, Q-07, Q-08, Q-09 et Q-10 ; "il n'y aura pas de correction automatique du clavier" ; "pour moi c'est bon R6 peut etre leve" ; "pour le palier 4 augmente le seuil avec des instances en // jusqu'a 3 si besoin"."*

**Sa reponse**, 2026-10-21, relayee par l'agent lanceur a `camille-po` (l'utilisatrice l'a donnee d'un seul mot) : *"confirme"*, en toutes lettres *"confirmé"*. **Je ne l'ai pas lue de sa main** : elle est relayee, et je le dis. Ce qui est cite ici est donc la question et la reponse **telles que l'agent lanceur me les a transmises**.

**Ce que cela fait a `DEC-0062`** (3.1 et 3.2 : la forme 2, *"de l'utilisatrice"*, porte ses mots cites entre guillemets et la date, et vaut motif ; la forme 3, sans source dans le depot, ne vaut jamais motif). **A la date du 2026-10-21, les enonces de la liste ci-dessus passent de la forme 3 a la forme 2** : leurs mots sont cites, ils sont confirmes mot pour mot, et la date est celle-ci. L'ecart 9 de `releve-relecteur-cloture-palier-3.md` (*"relayee"* n'est pas l'une des trois formes) est traite **pour la liste, et pour elle seule**.

**Ce qui est couvert** : exactement les enonces de la liste, quels que soient les fichiers qui les citent : le *"ok"* sur `AD1`, `AD2`, `AD4` ; les reponses a `Q-05`, `Q-06`, `Q-07`, `Q-08`, `Q-09`, `Q-10` ; *"il n'y aura pas de correction automatique du clavier"* ; *"pour moi c'est bon R6 peut etre leve"* ; *"pour le palier 4 augmente le seuil avec des instances en // jusqu'a 3 si besoin"*. **Les mots seulement.**

**Ce qui n'est pas couvert** :
- **Les lectures que les agents en ont faites.** Ma lecture de la consigne sur le seuil (la borne de 8,0 de `PAR-9`, les instances A, A' et B) reste **a confirmer** ; `QP3`, `QP5`, `QP6`, `QP7` de `preparation-palier-4-camille.md` restent **sans reponse**. Les lectures de `DEC-0123` section 0 (ma colonne *"Ma lecture"*) et de `DEC-0124` section 0 ne sont pas confirmees.
- **Ce que l'utilisatrice a verifie ou non** : la confirmation ne dit rien du jar joue sur la tablette ni de l'ordinateur pour `R6` : la limite *"l'ordinateur n'est pas mesure"* reste ecrite.
- **Tout autre enonce relaye**, dans ces fichiers ou ailleurs (`DEC-0120` A.114 et ses mots de `S2` et de `Q-N4`, `Q-U11`, les paliers 1 et 2, tout enonce hors de la liste) : **pas de balayage retroactif au-dela de la liste.** `E5` (la regle pour une reponse relayee) **reste ouvert** pour le reste.
- **La provenance du relais** : la confirmation ne dit pas par quel canal les mots sont arrives a l'agent lanceur la premiere fois.

**Statut** : aucune decision ne change, aucun texte d'origine n'est reecrit, aucune ligne de matrice ne bouge. Aucun identifiant de condition neuf.

**Dans cette decision** la liste couvre les mots de `AD1`, `AD2` et `AD4` (A.117 a A.131 les citent), de `Q-05` a `Q-10` (A.120) et la consigne sur le seuil (A.126). `DEC-0120` reste `Acceptee`.

## Amendement du 2026-10-21, vingt-deuxieme (`camille-po`, reponses de l'utilisatrice a `E5`, `QP3`, `QP5`, `QP6`, `QP7` et au seuil du palier 4)

Annotation datee. **Rien au-dessus n'est reecrit.** Ce que je n'ai pas fait : lancer `mvn`, jouer un jar, composer le palier 4, amender `planning.md`, toucher `squad/README.md` (a `sofia-sm`). Arbre : `round-two` a `50c74519`.

### A.133 Les mots de l'utilisatrice, forme 2 de `DEC-0062`

Cites tels que l'agent lanceur me les transmet, 2026-10-21, valant ses mots depuis sa reponse a `E5` (`DEC-0062`, amendement G) :

- *"E5 oui ça vaut pour mes mots, QP6 lea dev pour B et sofia pour A', QP7 après un palier 5 voir 6 je ne sais pas encore, QP5 oui, QP3 un tableau d'informations"* ;
- sur le seuil, a la question *"voulez-vous lever le plafond par instance a 6,67, ou passer a quatre instances, ou fixer 15,0 (trois instances a 5,0) ?"* dont la voie 1 etait *"Quatre instances a 5,0 (4 x 5,0 = 20,0)"* : *"1 quatre instances"* ; elle avait dit avant *"on monte a 20"*.

**Ce que je lis et ce que je ne lis pas.** Je lis chaque mot comme une reponse a la question de `preparation-palier-4-camille.md` qui porte son code. **Ces lectures sont les miennes** : celle de `QP3` est ecrite en A.137 avec ce qu'elle laisse ouvert. Je n'ai pas lu *"on monte a 20"* dans un fichier avant ce jour : c'est une citation de l'agent lanceur, datee du 2026-10-21, forme 2 a cette date.

### A.134 `QP6` : `lea-dev` sur B, `sofia-sm` sur A' - `RG1`, `RG2` et `RG4`, lues dans `composition-palier-3.md` section 4.1

| Regle | Texte | Etat avec A : `marc-dev`, B : `lea-dev`, A' : `sofia-sm` |
|---|---|---|
| `RG1` | `lea-dev` ne recoit aucun lot de francais CE1 | **tenue.** `lea-dev` porte B (mathematiques CE1) ; le seul lot de francais CE1 du repli (`LP1`, `FRA-CE1-21`) est a `marc-dev` |
| `RG2` | des developpeurs distincts sur des instances distinctes (motif : `catalogue.yaml` et l'inventaire de `TexteAccentueExposeParLePortEntrantTest` sont partages) | **tenue, sans ecart.** Trois instances, trois developpeurs. Au palier 3, `AD1` avait mis B et A' chez `lea-dev` et l'ecart etait dit (`composition-palier-3.md` 12.3) ; il n'existe plus |
| `RG4` | un developpeur n'entrelace pas ses worktrees : un a la fois, et il le dit | **tenue si** `lea-dev` livre `LP2` puis `LP3` (puis un troisieme lot de B) l'un apres l'autre, et `sofia-sm` `LP4`, `LP5`, `LP6` de meme. La regle est de `sofia-sm` ; je ne la declare tenue que sur ce que la reponse dit, pas sur le deroule |

Les autres regles du meme tableau : `RG3` (`marc-dev` pour un lot a texte support) ne vise aucun lot du repli ; `RG6` (l'agent est celui que la story nomme) : les stories de `LP2` a `LP6` ne nomment aucun developpeur (*"a designer par `sofia-sm`"*), la designation est donc celle de l'utilisatrice et non plus une designation de `sofia-sm` par elle-meme.

**`sofia-sm` porte deux casquettes, jamais simultanees (`RG5`).** `RG5` : casquette declaree au debut de la tache, aucun arbitrage de son propre obstacle (elle le note, change de casquette, le route), commits `[SOF]`, fusion de sa propre branche par `outils/verifier-lignee.sh` et liste de fichiers contre `K2`, relue par `yanis-qa` : *"je n'ai pas d'autre juge, et je le dis"*. **Ce que cela implique, en faits et sans trancher la procedure** (elle est a elle) :

1. **Les fusions** : l'ordre de fusion et la file sont de sa casquette de Scrum Master (`avis-theo-palier-4.md` 3.3 : *"La mecanique exacte est a `sofia-sm`"*). Pendant qu'elle developpe, aucune fusion de `marc-dev` ou de `lea-dev` ne se decide par elle ; elle les fait avant ou apres, ou elle designe qui les fait. **Je n'ecris pas laquelle.**
2. **La chaine d'A' est la plus longue** : trois lots l'un apres l'autre, chacun coupe apres la fusion du precedent (3.3, point 4). Chaque fusion de sa propre branche tient sur `yanis-qa` seul comme second regard.
3. **Les verifications** : un seul `verify` complet a la fois sur le poste (3.3, point 1). Le sien comme developpeuse et ceux de la file qu'elle conduit comme Scrum Master se partagent le meme jeton ; la file ne peut pas avancer pendant qu'elle verifie son propre lot.
4. **Les obstacles** : un obstacle sur A' n'est pas arbitre par elle. Qui le recoit quand c'est elle qui le porte est un point de procedure que `RG5` ne ferme pas.
5. **Le double Done** n'a pas de variante pour ses lots : CI sur les jobs nommes, Done pedagogique de `nadia-prof`.

**`theo-archi` recommandait deux developpeurs** (`avis-theo-palier-4.md` 3.4 : *"trois instances de lecture, deux developpeurs, une file de fusion et un jeton de `verify` complet"*) et laissait l'option de capacite a l'utilisatrice et a `sofia-sm`. **Elle a choisi trois developpeurs** : c'est son choix, ecrit ici avec la recommandation qu'il ne suit pas.

**Ce que cela fait au report.** `preparation-palier-4-camille.md` 7.2 disait `QP6` *"bloquante a l'ouverture"* : sans reponse, premier report pour chacun des quatre lots non engages. **Elle a repondu ; les developpeurs de `LP2` a `LP5` sont designes. Il n'y a donc pas de report a l'ouverture du palier 4 pour ce motif**, si le reste est tenu : l'arbitrage de `nadia-prof` (A.138), la composition et l'amendement de `planning.md` de `sofia-sm`, la table d'isolation. Je ne compte aucun report tant qu'aucun de ces trois points n'a manque.

### A.135 `QP5` : le maximum de la version passe de 200 a 202

*"QP5 oui"* : l'utilisatrice sait que le maximum de la version, qu'elle avait fixe a 200 (`Q-U10`, A.38), passe a **202**, le taux baissant de 0,75 point sans qu'aucune ligne ne bouge. **Appliquee dans `MATRICE-couverture.md`**, selon A.123 :

| Ligne | Statut avant | Statut apres | Plafond |
|---|---|---|---|
| `FRA-CE1-27` (moitie *ecouter*) | `non couvrable en l'etat` | `non couvert` | `en cours` ; la moitie *s'exprimer* reste irreversible |
| `FRA-CE1-29` (mots sous la dictee) | `non couvrable en l'etat` | `non couvert` | `en cours` |

**Compteurs**, commande de la "Methode du compte" de la matrice lancee ce jour, sortie lue, avant et apres : `couvert-valide` 152 et 152, `en cours` 5 et 5, `non couvert` 43 et **45**, `non couvrable en l'etat` 77 et **75**, total 277 et 277, denominateur atteignable 200 et **202**. **152 / 202 = 75,25 %** (76,00 % avant). Compteur de version (A.32) : 152 + 5 = 157 / 202 = 77,72 %. CE1 : 64 lignes atteignables, 34 `couvert-valide` (53,13 %), contre 62 avant. **Le recul n'est pas une regression du produit** : le numerateur n'a pas bouge.

**Ecart a A.123, dit.** A.123 annoncait le passage *apres* l'annotation de `TRV-CE1-1` par `nadia-prof`, et A.125 la lui routait. **Cette annotation n'existe toujours pas** (`grep -c "2026-10-21" squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` rend 0 ; `arbitrage-nadia-palier-4.md` section 1 la porte parmi ses ouverts). Le passage est fait **avant** elle, sur le *"oui"* de l'utilisatrice et sur la consigne de l'agent lanceur de l'appliquer. Ce que cela ne change pas : l'annotation reste a `nadia-prof`, aucun jeu n'est branche, aucun verdict n'est rendu, le plafond est celui qu'elle a ecrit (`arbitrage-nadia-q05-q10.md` 3.1 et 3.2). Les cellules des deux lignes portent l'ecart.

### A.136 `QP7` : s57 se prolonge au-dela du palier 4

*"QP7 après un palier 5 voir 6 je ne sais pas encore"* : s57 **ne se clot pas** apres le palier 4. Un palier 5 est voulu, un palier 6 est possible, la suite n'est pas decidee. **Rien ne se clot** : aucune cloture de sprint, aucun rapport de sprint, aucune retro n'est declenche par cette reponse (`duree-et-paliers.md` : le sprint s'arrete quand l'utilisatrice l'arrete).

**Ce que cela implique, en faits :**

- **La condition 4 de `nadia-prof` tient pour tout s57, quelle que soit sa duree** (`arbitrage-nadia-q05-q10.md` 2). `FRA-CE1-08`, `-09`, `-29` ne s'ouvrent donc dans aucun palier de s57. Les paliers 5 et 6 n'ont pour stock que ce qui reste de la section 3 de `preparation-palier-4-camille.md` : `LP6` si elle n'entre pas au palier 4, `LP7`, `LP8`, `LP9`, **si leurs prealables tombent** (paquet reecrit, story de `LP8`, `theo-archi` et la reponse de `nadia-prof` a la question de A.137). Les seize lignes que `QP7` comptait sur 43 `non couvert` attendent s58 ; `FRA-CE1-27` et `FRA-CE1-29` y entrent, soit un compte a refaire apres ce geste, non fait ici.
- **Un palier 5 n'est pas un palier plein par defaut.** Le seuil est un plafond (A.139) ; ce qui n'est pas pret n'est pas engage. Un palier 5 de deux lots est un palier de deux lots.
- **Le risque nomme par `DEC-0007`** (la couverture tenue fermee par des reports successifs) **est maintenant un choix**, pas une derive : l'utilisatrice a repondu en connaissant cette consequence (*"je ne sais pas encore"* apres le palier 5). Je ne le compte pas comme un report. **Je repose la question a la cloture du palier 5**, en citant cette reponse ; l'ecrire dans le fichier de cloture est a `sofia-sm`.

### A.137 `QP3` : *"un tableau d'informations"* - ce que cette lecture ne leve pas

**Ses mots** : *"QP3 un tableau d'informations"*, a la question *"voulez-vous un quadrillage montre sur lequel l'enfant se repere, se deplace et appuie sur la case, ou un tableau d'informations sans cases designables ?"* **Elle choisit la seconde lecture.** `DEC-0123` 2.1 lisait *"une grille pour infos a l'enfant"* comme un quadrillage montre ; **cette lecture est ecartee par elle**. La reserve de 2.1 (*"si l'utilisatrice voulait un tableau d'informations, `DEP-CE1-2` n'est pas levee"*) **se realise**, et mon propre texte le dit : **cette lecture ne leve pas `DEP-CE1-2`**.

**Ce que `DEP-CE1-2` demande, ses mots** (`ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md` section 1, cites par la story) : *"Le quadrillage est montre des qu'un item demande de se reperer ou de se deplacer. Designation sur dessin, grille d'au moins 4 x 4 et d'au plus 6 x 6 cases, toutes de meme taille et toutes designables."* **Un tableau sans cases designables ne satisfait pas la seconde phrase.** Ce que `nadia-prof` a ecrit (`arbitrage-nadia-q05-q10.md` 1.2) : `DEP-CE1-2` n'est leve en entier que par une designation sur la grille ; la forme (a) (grille montree, choix parmi trois textes) laisse la ligne `en cours`, la forme (b) (l'enfant appuie sur la case) la ferme.

**Ce que cela implique pour `MAT-CE1-14` et `S57-BR-MAT-CE1-14-GRILLE`, sans decider a la place de `nadia-prof` :**

| Point | Avant (story amendee) | Avec *"un tableau d'informations"* |
|---|---|---|
| Forme (b) | visee, parce qu'elle seule ferme la ligne en `couvert-valide` | **plus fondee par ses mots** : (b) exige des cases designables, qu'elle ecarte. Mon choix de (b) reposait sur ma lecture de `Q-05`, que sa reponse ecarte. **Je ne la retire pas de la story** : c'est a `nadia-prof` de dire si (b) reste recevable comme produit que l'utilisatrice n'a pas demande |
| Forme (a) | repli | **la seule forme compatible avec ses mots**, plafond `en cours`, 1,5 (indicatif), **a condition** que le tableau montre bien un quadrillage. S'il montre autre chose (une legende, une table de codes), le paquet textuel reste refuse d'avance (`DEC-0123` 2.4) |
| Plafond | `couvert-valide` possible par (b) | **`en cours`** tant que (b) n'est pas reouverte. La ligne ne ferme pas dans s57 sur cette lecture |
| `A3` d'`ADR-0080` | requis ou non pour une grille de designation de 4 x 4 a 6 x 6 | **tombe peut-etre** : `A3` est une clause de designation ; sans zones designables, la question change de nature. **C'est `theo-archi` qui le dit**, et il dit aussi si le tableau est un dessin (le cliquet de poids, `POIDS_TOTAL_MAXIMUM` en egalite, s'applique alors) ou du texte (sans octet de dessin, mais **un fragment de page** qu'aucune mecanique du catalogue ne porte aujourd'hui : *"sans modification de mecanique"* est a lui) |
| Criteres 1 (lecture sur `zones`) et 11 de la story | zones designables | **ne tiennent plus** sous cette lecture ; ils ne sont pas retires, la story est a reecrire apres la reponse de `nadia-prof` |
| Prix, instance | (a) 1,5 ; (b) 3,0 ou 1,5 ; B | (a) 1,5, B ; **pas pret** : la question ci-dessous et l'avis de `theo-archi` precedent |

**La question, une seule, routee a `nadia-prof`** : *sous la lecture de l'utilisatrice (un tableau d'informations sans cases designables), la forme (a) satisfait-elle `DEP-CE1-2` pour le plafond `en cours`, ou la clause refuse-t-elle d'avance tout item de deplacement sans designation ?* Sans sa reponse `LP9` reste hors palier. **La story n'est pas engagee, la matrice ne bouge pas.**

### A.138 Le seuil : *"1 quatre instances"*, soit 20,0

**Ce qui est decide, par elle** : le seuil du palier 4 monte a **20,0 = quatre instances de `nadia-prof` a 5,0 chacune**. Elle ecarte les deux autres voies (un plafond par instance releve a 6,67 ; trois instances a 5,0 pour 15,0). **L'arithmetique du conflit etait la suivante, et elle l'a levee** : `PAR-8` plafonne a 5,0 et a trois lots par instance, donc trois instances donnent au plus 15,0 ; 20,0 est quatre instances a 5,0 ou un plafond a 6,67. **Elle choisit les instances.** Ma lecture de A.126 (*"jusqu'a 3"* = l'etat des paliers 1 a 3, ou un plafond de trois) est **ecartee par ses mots** : le nombre passe de trois a quatre.

**Ce qui change, ce qui ne change pas.**

| Regle | Texte lu | Sort |
|---|---|---|
| `PAR-8`, plafond par instance | *"au plus 5,0 et trois lots par instance"* | **inchange**, a sa demande : quatre instances portent au plus 12 lots |
| `PAR-8`, borne du palier | *"8,0 a deux ou trois instances, 5,0 a une"* | **ne dit rien de quatre instances.** Le texte nomme deux ou trois : une borne a quatre est une ligne a ecrire, par `nadia-prof` |
| `PAR-9` | *"la somme des budgets egale la borne"* | **a lire par `nadia-prof`** : a 20,0 la somme des budgets engages (A.139) est 9,0. Le precedent des *"non affectes"* (0,75 dans `ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md` 10.2 : *"La somme de 8,0 etait juste"*) existe ; qu'un reste de 11,0 s'ecrive de meme ou que la borne soit un plafond est son jugement |
| D7 / 4.3 de `ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md` | *"O1. La borne de s57 est 8,0, pour trois instances"* ; *"Je ne pose aucun chiffre au-dela de 8,0"* | a amender par `nadia-prof`, par un document date : `O2` a 20,0 pour quatre instances. Elle a ecrit qu'au-dela de 8,0 c'est *"sa decision de produit"* (celle de l'utilisatrice) et qu'elle l'ecrit comme la sienne (`arbitrage-nadia-palier-4.md` 2.3) : **les mots sont maintenant ecrits (A.133)** |
| `DEC-0120` A.28, A.126 | *"Borne de s57 : 8,0 pour trois instances"* | **non reecrits** ; cet amendement les remplace pour le palier 4, a la date |
| A.33 et A.36 | instances A, A' et B | une **quatrieme instance, C**, est une affectation de `nadia-prof` (arbitrage prealable 0,5, marge 0,5) ; je ne la nomme par aucune matiere |
| `PAR-2`, `PAR-13`, condition 4, `Q-06` | une matiere et un niveau jamais partages ; aucun terrain neuf tant qu'A' existe ; `FRA-CE1-08`, `-09`, `-29` hors s57 ; dessins hors perimetre | **inchanges** : ce sont ces regles, et non le nombre d'instances, qui limitent les lots (A.139) |

**Ce qui est amende, et par qui.** `nadia-prof` : `O2` et son chiffre pour le palier 4, la capacite de chaque instance dont C, la lecture de `PAR-9` (egalite ou plafond), une ligne de `PAR-8` pour quatre instances. `sofia-sm` : l'amendement de `planning.md`, la composition, la table d'isolation de quatre instances sur plus de lots, la file de fusion. **Ce que je n'ai pas lu et que je ne dis donc pas amende** : `planning.md`, `composition-palier-3.md` et la table d'isolation, ou le nombre d'instances est ecrit (a `sofia-sm`). Ce qui n'est pas amende : le plafond par instance de `PAR-8`, `PAR-2`, `PAR-13`, la condition 4.

**Arbitrage prealable du palier 4 de `nadia-prof`** (18:32, anterieur a la reponse) : borne **8,0** maintenue, repli de 8,0 recevable (cinq lots), `EMC-CE1-04` seul ajout a 9,0. Il reste valide tant qu'elle ne l'a pas amende ; **elle ecrit sa capacite a quatre instances apres cette reponse, ou maintient le repli et le dit.**

### A.139 Ce que quatre instances ouvrent pour le palier 4

**Le seuil est un plafond, pas un objectif : ce qui n'est pas pret n'est pas engage.** Lots prets et prix de `nadia-prof` (`preparation-palier-4-camille.md` 3, prix indicatifs confirmes a son arbitrage du palier 4) :

| Lot | Ligne | Prix | Instance | Developpeur (A.134) |
|---|---|---|---|---|
| `LP1` | `FRA-CE1-21` | 1,0 | A | `marc-dev` |
| `LP2`, `LP3` | `MAT-CE1-10`, `-11` | 1,0 + 1,0 | B | `lea-dev` |
| `LP4`, `LP5`, `LP6` | `EMC-CE1-02`, `-03`, `-04` | 1,0 x 3 | A' | `sofia-sm` |

**Composition maximale en lots prets : 9,0**, six lots, trois instances qui portent un lot (3,0 de couts fixes plus 6,0 de lots). **11,5 conditionnel**, huit lots, repose sur `LP7` (paquet a reecrire) et `LP8` (cousinage juge recevable par `nadia-prof`, mais sans story) : ils ne sont pas prets. **A 20,0, 9,0 font 45 % du seuil ; le reste, 11,0, n'a pas de lot.**

**La quatrieme instance C n'a aucun lot pret, et la raison est celle de plusieurs regles, pas du seuil.** Francais CE1 est a A, mathematiques CE1 a B, EMC a A' (`PAR-2`, jamais partages). Les lots hors de ces trois matieres (`FRA-CE1-27`, arts plastiques CE1, questionner le monde CE1) sont des terrains neufs que `PAR-13` ferme tant qu'A' existe, et `nadia-prof` a ecrit qu'un paquet sans cousine ne s'audite pas dans le sprint ou une autre instance en audite un. `FRA-CE1-08`, `-09`, `-29` : condition 4. Les dessins : `Q-06`. **Ouvrir C sans lot ajoute 1,0 de couts fixes et aucune ligne de couverture : je ne le propose pas.** Une instance C n'a de lot que si `nadia-prof` leve `PAR-13` ou la condition 4, ou si un palier ulterieur y fait entrer un terrain neuf : c'est son arbitrage et non le mien.

**`QP8`, une question a l'utilisatrice, a poser seulement si C doit porter un lot** : `RG2` demande des developpeurs distincts, et `marc-dev` (A), `lea-dev` (B) et `sofia-sm` (A') sont pris, `RG4` interdisant d'entrelacer. `theo-archi` est architecte, `yanis-qa` QA, `ines-design` designer : **je ne designe personne.** Une quatrieme instance avec un lot demande soit un ecart a `RG2` (qu'elle a deja accepte au palier 3 par son *"ok"* a `AD1`, pour un cas different), soit un developpeur qu'elle nomme. Sans lot dans C, `QP8` n'est pas posee.

**La file de fusion, recalculee pour les lots prets d'apres les prix** (`avis-theo-palier-4.md` 3.3 : un seul `verify` complet a la fois, une fusion a la fois, `verify` de 11 min 39 s mesure sur une copie propre de `50dbd73f` ; les durees par fusion de 11 min 17 s a 12 min 39 s sont **calculees, non mesurees**) :

| Composition | Lots | `verify` seul | Job `build` entier (20 min 44 s a 23 min 01 s par fusion) |
|---|---|---|---|
| repli de `nadia-prof`, 8,0 | 5 | 56 min a 1 h 03 | 1 h 44 a 1 h 55 |
| 9,0, prets | **6** | **1 h 08 a 1 h 16** | **2 h 04 a 2 h 18** |
| 11,5, conditionnel | 8 | 1 h 30 a 1 h 41 | 2 h 46 a 3 h 04 |
| plafond de `PAR-8` a quatre instances, aucun lot pret | 12 | 2 h 15 a 2 h 32 | 4 h 09 a 4 h 36 |

**La quatrieme instance n'allonge pas la file** tant qu'elle ne porte pas de lot : la file compte les fusions, pas les instances. Les lignes de 6 et 8 lots sont celles de `theo-archi`, les deux autres sont calculees de la meme formule ; la ligne de 12 est le plafond du reglement, **pas une composition**.

### A.140 Routage, ce que cet amendement ne fait pas

| A qui | Quoi |
|---|---|
| `nadia-prof` | `O2` a 20,0 et la capacite de quatre instances, ou le maintien du repli ; `PAR-9` (egalite ou plafond) ; une ligne de `PAR-8` pour quatre instances ; l'annotation de `TRV-CE1-1` (A.135) ; la question de A.137 |
| `sofia-sm` | La composition du palier 4 et l'amendement de `planning.md` (quatre instances, trois developpeurs, ordre de fusion) ; la procedure de ses deux casquettes (A.134) ; `squad/README.md` pour la regle de `DEC-0062` G ; le fichier de cloture pour `QP7` |
| `theo-archi` | `A3` et la nature du tableau d'informations (A.137) |
| Utilisatrice | `QP8`, seulement si C doit porter un lot ; `QP7` a la cloture du palier 5 |

**Ne fait pas** : ne prononce aucun Done, ne juge aucun contenu, ne compose pas le palier 4, n'amende ni `planning.md`, ni `PAR-8`, ni `PAR-9`, ni le document de criteres, ni `composition-palier-3.md`. N'engage aucun poste, ne fixe aucun prix, n'affecte aucune instance. **Decisions de produit prises ici** : (1) le passage de `FRA-CE1-27` et `-29` a `non couvert` et le maximum de 202 (A.135) ; (2) rien d'autre : le seuil de 20,0 et le nombre de quatre instances sont ses mots, pas ma decision. Aucun identifiant de condition neuf. `DEC-0120` reste `Acceptee`.


## Amendement du 2026-10-21, vingt-troisieme (`camille-po`, reponse de l'utilisatrice a `QP3` : *"une grille dessinee"*)

Annotation datee. **Rien au-dessus n'est reecrit.** A.137 et la reponse a `QP3` de l'amendement precedent (*"un tableau d'informations"*) sont **precisees** par ce qui suit, pas effacees. **Statut de `DEC-0120` : `Acceptee`, inchange.** Ce que je n'ai pas fait : lancer `mvn`, jouer un jar, composer le palier 4, amender `planning.md`, ecrire un prix, un identifiant de condition neuf. Arbre : `round-two` a `01c3563e`.

### A.141 Les mots, et ce que nadia-prof avait dit qu'ils feraient

Cites tels que l'agent lanceur me les transmet, 2026-10-21 (forme 2 de `DEC-0062` G) : *"QP3 c'est une grille déssinée"*. Ils repondent a la question que `nadia-prof` posait en 8.1 de `arbitrage-nadia-palier-4.md` : *"si 'tableau d'informations' veut dire la grille dessinee sans case a toucher, c'est la forme (a) ; si c'est un tableau rempli de donnees, il n'est recevable pour aucune ligne"*. **La premiere branche est choisie. Lecture fixee : une grille dessinee, sans case designable, forme (a) de `nadia-prof`.** Le tableau de donnees n'est pas voulu.

Consequences sur A.137, ligne par ligne :

| Point de A.137 | Maintenant |
|---|---|
| *"Forme (b) [...] plus fondee par ses mots"* | **retiree** de la story `S57-BR-MAT-CE1-14-GRILLE` ; elle ne se rouvre que par une nouvelle reponse de l'utilisatrice |
| Forme (a), *"a condition que le tableau montre bien un quadrillage"* | **la condition est remplie** : c'est un quadrillage dessine |
| Plafond `en cours` | tient ; `DEP-CE1-2` est lue *"montre, de 4 x 4 a 6 x 6, cases de meme taille"*, pas *"toutes designables"* (`nadia-prof` 8.1) |
| Question a `nadia-prof` (la forme (a) satisfait-elle `DEP-CE1-2` pour `en cours` ?) | **repondue par elle** en 8.1 : oui, conditions 1, 2, 4, 5, 6 et 8 de `arbitrage-nadia-q05-q10.md` 1.3, point 3 sans objet, point 7 remplace par sa mention (*"se reperer et se deplacer servis sur un quadrillage montre, reponse au choix sans designation ; 'coder un deplacement' servi du cote reception [...] la ligne reste en cours"*) |
| Avis de `theo-archi` : `A3` tombe-t-il ? | repondu avant la reponse, `avis-theo-palier-4.md` 2.1 et 2.4 : **`A3` n'est pas un prealable**. La contrainte est la bande d'etiquettes d'une grille carree (6 unites sur 100 a 6 x 6, 17,8 px a 296, lisibilite non mesuree). Son avis visait la forme (b) ; **son complement pour la forme (a) n'est pas ecrit** |

**Ce que l'utilisatrice n'a pas dit** : la seconde moitie de la question de 8.1 (*"la ligne reste 'en cours' : elle ne passe pas a 'couverte' [...] L'acceptez-vous ?"*) n'a pas de mot de sa part. `nadia-prof` ecrit *"Un oui sur le quadrillage : forme (a)"* ; je suis cette lecture, et je l'ecris comme une lecture : le plafond `en cours` n'est ni accepte ni refuse par ses mots.

### A.142 `LP9` : prix, instance, developpeur, etat

| Point | Valeur |
|---|---|
| Prix | **1,5**, indicatif, lot illustre (`nadia-prof` 8.1), a confirmer a son arbitrage prealable ; je n'en fixe aucun |
| Instance | **B** (`PAR-2` : mathematiques CE1) |
| Developpeur | `lea-dev` est celui de B (`QP6`) ; **pas designe par lot**, a confirmer par `sofia-sm` dans la composition. B porterait trois lots en sequence (`RG4`) |
| Pret | la story reecrite (forme (a) seule, criteres 1, 3, 6, 8, 14 reecrits ou neufs, mesures contre le paquet `ce1-deplacements-quadrillage` : 0 illustration, 20 enonces sur 20 qui nomment une coordonnee, 3 indices *"Dessine le quadrillage"*) ; les conditions de `nadia-prof` ecrites ; `A3` non prealable ; la mesure de 36 zones (`avis-theo-palier-4.md` 2.3) **sans objet** |
| Manque | (1) la reponse de l'utilisatrice a la question de A.143 ; (2) le complement de `theo-archi` pour la forme (a) : un ou deux paquets (tous les items sont `type: question`, je ne tranche pas), octets mesures d'un dessin 6 x 6 a etiquettes tracees, paragraphe `A3 pour MAT-CE1-14` d'`ADR-0080` ; (3) la mesure d'`ines-design` des etiquettes a 296 px et a 744 x 551 avant l'engagement ; (4) la reponse de `nadia-prof` a *"par sa coordonnee seule"* (critere 3) ; (5) son arbitrage prealable (prix, `PAR-13` pour la forme (a), voir A.143) ; (6) la composition et l'amendement de `planning.md` (`sofia-sm`) ; (7) le paquet reecrit avec ses dessins, ce qui est le lot lui-meme |
| Report | premier ecartement au palier 3 ; **deuxieme s'il n'est pas engage au palier 4** ; un troisieme demande une `DEC` avec son motif |

**Composition maximale** (arithmetique, `PAR-8` : 5,0 et trois lots par instance). Si `LP9` devient pret : **9,0 + 1,5 = 10,5**, sept lots (A 2,0 ; B 3,0 + 1,5 = **4,5, trois lots** ; A' 4,0). **`LP7` et `LP9` sont toutes deux de B** : ensemble, B ferait 6,0 et quatre lots, au-dela de `PAR-8`. Le conditionnel de 11,5 (`LP7` et `LP8`) devient donc : `LP8` plus `LP9`, ou `LP8` plus `LP7`, **11,5 dans les deux cas, jamais 13,0**. Ce qui empeche encore `LP9` d'etre pret : les sept points de la ligne *"Manque"*, dont deux ne sont pas a l'equipe (l'utilisatrice, `nadia-prof`).

**File de fusion, sept lots** (meme formule que A.139 : un `verify` complet a la fois, 11 min 17 s a 12 min 39 s par fusion, 20 min 44 s a 23 min 01 s pour le job `build` ; **calcule, non mesure**) : `verify` **1 h 19 a 1 h 29**, job `build` **2 h 25 a 2 h 41** (six lots : 1 h 08 a 1 h 16 et 2 h 04 a 2 h 18). Le commit de `LP9` deplace `POIDS_TOTAL_MAXIMUM` (`ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest`) : **un autre lot qui installe des dessins dans le meme palier (`LP7`) entre en conflit sur la meme constante**, ce qui plaide pour ne pas les mettre dans le meme palier ; l'ordre de fusion est a `sofia-sm`. Les autres lots de 9,0 n'ajoutent aucun dessin (je ne l'ai pas verifie lot par lot : a lire par `sofia-sm` a la composition).

### A.143 La grille dessinee est-elle un dessin au sens de `Q-06` ? La tension, et la question qui en resulte

**Au sens de `Q-06`, la liste, non** : `DEC-0123` 3.1 sort cinq lignes nommees (`QLM-CE1-09`, `QLM-CE1-11`, la moitie dessinee de `QLM-CE1-07`, `MAT-CE1-02`, `MAT-CE1-09`) ; `MAT-CE1-14` n'en est pas, et 3.2 point 4 la dit *"couverte separement par `Q-05`"*. **Au sens du cliquet de poids, oui** : c'est un fichier SVG sous `static/illustrations`, et 3.2 (revers) l'ecrivait deja : *"la grille de `Q-05` est elle-meme un dessin ; le cliquet plein et `PAR-13` lui sont opposables comme aux autres"*. **Le cliquet n'interdit rien** : `POIDS_TOTAL_MAXIMUM` se deplace dans le commit qui installe les octets, sur la valeur mesuree (`ADR-0077` decision 6), et `theo-archi` ne trouve aucun plafond absolu (`avis-theo-palier-4.md` 2.4). Rien ne se contredit dans les textes.

**La tension est dans les motifs, pas dans la regle.** `DEC-0123` 3.2 point 1 sort les cinq dessins en invoquant *"terrain neuf, poids plein, aucun prix"* ; l'utilisatrice veut cette grille-ci. Le meme poids plein s'applique : `POIDS_TOTAL_MAXIMUM = 69203` octets, 56 fichiers, **mesure ce jour** (`git ls-files` rend 56 sous `static/illustrations`). Un dessin par item a vingt items, au plafond par dessin de 4633 octets : **92 660 octets au plus** (calcul), 30 a 90 ko selon l'ordre de grandeur de `theo-archi` (non mesure), soit **une hausse d'environ 43 % a 134 % du total actuel** (30 ko et 92 660 octets sur 69 203, calcul). Si le poids a pese pour sortir cinq lignes, il pese pour celle-ci ; si elle passe, le motif n'etait pas le poids pour les cinq autres, ou il l'etait et il a change. **Je ne tranche pas a sa place.**

**`PAR-13`** : `nadia-prof` ecrit *"Pas un terrain neuf au sens de `PAR-13`"* parce que *"la designation sur grille a rencontre du contenu reel (`designation-symetrie-ce1`)"* (`arbitrage-nadia-q05-q10.md` 1.4). **En forme (a) il n'y a plus de designation** ; son motif est celui de la forme (b). Sa lecture de `PAR-13` est a la matiere (mathematiques CE1 est servie), mais **elle ne l'a pas reecrite pour la forme (a)**. Question a elle, a son arbitrage prealable, pas a l'utilisatrice.

**La question a l'utilisatrice, une seule** (formulee pour une personne qui n'a pas lu les `DEC`) : *"La grille de `MAT-CE1-14` est un dessin, un par exercice, vingt au plus. Les cinq autres lignes a dessin sont sorties du produit en partie parce que le poids total des dessins est au plafond (69 203 octets aujourd'hui). Ces vingt grilles feraient monter ce plafond d'environ 40 % a plus de 130 % (estimation de `theo-archi`, non mesuree). Acceptez-vous cette hausse pour cette ligne seule ? Un oui n'ouvre pas les cinq autres lignes : leur retour demande une reponse a elles."* Sans reponse, `LP9` n'est pas engageable : la regle le permet, mais `DEC-0123` 3.3 a fait du report des dessins une direction qu'elle a choisie, et la contourner par un commit de cliquet serait la defaire sans lui avoir demande. **Cette question n'a pas d'identifiant** : je n'en cree pas ; elle est posee a l'utilisatrice par `sofia-sm` ou l'agent lanceur avec le prochain lot de questions.

### A.144 Routage, ce que cet amendement ne fait pas

| A qui | Quoi |
|---|---|
| l'utilisatrice | la question de A.143 |
| `theo-archi` | le complement de la forme (a) (A.142, point 2) ; son avis ecrit dans un fichier neuf du dossier du sprint |
| `ines-design` | la mesure des etiquettes d'une grille de 6 x 6 (A.142, point 3) ; elle propose, ne tranche pas |
| `nadia-prof` | *"par sa coordonnee seule"* ; `PAR-13` pour la forme (a) ; prix et instance a son arbitrage prealable ; le signalement du referentiel de mathematiques CE1 (aucune ligne sur la lecture de donnees en tableau, `arbitrage-nadia-palier-4.md` 8.1) : **je ne l'ai pas traite ici** (hors de la demande), il reste a verifier contre le texte officiel, et une ligne neuve est a elle |
| `sofia-sm` | la composition (10,5 ou le repli), l'amendement de `planning.md`, la file de fusion et l'ordre de `LP7`/`LP9` si les deux sont retenus |
| `lea-dev` | rien avant l'engagement |

**Ne fait pas** : ne prononce aucun Done, ne juge aucun contenu, ne compose pas le palier 4, n'amende ni `planning.md`, ni la matrice (`MAT-CE1-14` reste `non couvert`, **aucune cellule ne change** : la cellule ne dit rien de la forme), ni le compteur de version (je n'ai pas lu si `nadia-prof` a ecrit pour cette ligne le plafond que le compteur d'A.32 exige : non calcule). **Decisions de produit prises ici** : aucune. La forme (a) est le choix de l'utilisatrice, la mention est a `nadia-prof`, le prix aussi. Aucun identifiant de condition neuf. `DEC-0120` reste `Acceptee`. Non commite.


## Amendement du 2026-10-21, vingt-quatrieme (`camille-po`, reponses de l'utilisatrice : le poids des dessins pour `MAT-CE1-14`, le plafond `en cours`, la composition du palier 4)

Annotation datee. **Rien au-dessus n'est reecrit.** A.141 et A.143 sont **completes** par ce qui suit, pas effaces. **Statut de `DEC-0120` : `Acceptee`, inchange.** Arbre : `round-two` a `fedaf20f`. Ce que je n'ai pas fait : lancer `mvn`, commiter, pousser, ecrire `composition-palier-4.md` ou un amendement de `planning.md` (`sofia-sm`), fixer un prix (`nadia-prof`), toucher la matrice.

### A.145 Les mots, et la question a laquelle chacun repond

Cites tels que l'agent lanceur me les transmet, 2026-10-21 (forme 2 de `DEC-0062` G) : *"LP9 oui on peut augmenter le poids des dessins / QP3 plafond accepté / composition de palier 4 OK / pousse après tout ça"*.

| Mots | Question posee | Ce qu'ils valent |
|---|---|---|
| *"LP9 oui on peut augmenter le poids des dessins"* | A.143 : *"Acceptez-vous que le poids total des dessins monte pour les grilles de cette seule ligne ? Un oui n'ouvre pas les cinq autres lignes."* | **Oui, pour `MAT-CE1-14` seule** (A.146) |
| *"QP3 plafond accepté"* | `arbitrage-nadia-palier-4.md` 8.1 : la ligne reste `en cours`, *"L'acceptez-vous ?"* | **Oui.** A.141 ecrivait que le plafond n'etait *"ni accepte ni refuse par ses mots"* : il est maintenant accepte (A.147) |
| *"composition de palier 4 OK"* | la composition proposee dans mon dernier message | **Approuvee** telle que ce message la lui soumettait ; A.148 la consigne en etat definitif. Le calcul de `PAR-8` par instance de A.148 est de moi, pas d'elle |
| *"pousse après tout ça"* | instruction a l'agent lanceur | **Pas une decision de produit** : un geste de depot, apres la composition et l'amendement de `planning.md` de `sofia-sm`. Je ne l'ai pas fait (consigne : ni commit ni push) |

**Ce qu'elle n'a pas repondu** : la taille de la grille (4 x 4, 5 x 5 ou 6 x 6). Je la consigne **ouverte, a `nadia-prof` et a moi, pas a elle** (A.151).

### A.146 Le poids des dessins : ce que le oui permet, ce qu'il n'ouvre pas

**Permet.** Pour `MAT-CE1-14` seule, le poids total des dessins peut monter. `POIDS_TOTAL_MAXIMUM` se deplace dans le commit qui installe les octets, sur la valeur mesuree (`ADR-0077`, decision 6). La tension d'A.143 est **levee pour cette ligne** : le poids n'est plus un motif contre elle.

**Mesures.** Etat, relu a `fedaf20f` : 56 fichiers sous `adapters/adapter-web/src/main/resources/static/illustrations`, **69 203 octets** en fins de ligne LF (somme des `git show HEAD:<fichier>` passes par `tr -d '\r'` et `wc -c`), egal a la constante. La fourchette soumise a l'utilisatrice etait *"environ 40 % a plus de 130 %"*, bornee par calcul a 92 660 octets ajoutes (A.143). `avis-theo-palier-4.md` 7.4, prototype de `theo-archi`, glyphes provisoires, non remesure par moi : 4 x 4, total 114 743 (+66 %) ; 5 x 5, 122 443 (+77 %) ; 6 x 6, 130 403 (+88 %). **Les trois tailles sont dans la fourchette qu'elle a acceptee.**

**Borne du oui.** Ce qu'elle a accepte est une hausse comprise dans ce qu'on lui a demande. **Au-dela de 92 660 octets ajoutes, soit un total superieur a 161 863 octets (69 203 + 92 660), la question se repose** : le oui ne couvre pas ce qu'on ne lui a pas demande. La valeur qui compte est celle du commit (glyphes reels d'`ines-design`, commentaires d'en-tete reels), pas celle du prototype.

**N'ouvre pas :**
- les cinq lignes de `Q-06` (`QLM-CE1-09`, `QLM-CE1-11`, la moitie dessinee de `QLM-CE1-07`, `MAT-CE1-02`, `MAT-CE1-09`) : leur retour demande une reponse a elles, comme `DEC-0123` 3.3 l'ecrit. **Le motif du poids n'est ni leve ni reaffirme pour elles** : il n'a pas ete repose ;
- questionner le monde CE1, les arts plastiques CE1, l'instance C : `PAR-13` et la condition 4 sont inchangees ;
- **tout autre dessin, dont celui de `MAT-CE1-15`.** La condition de `nadia-prof` (3.2 de son arbitrage : le triangle rectangle montre dans au moins deux dessins) demande un dessin neuf, donc des octets de plus. `MAT-CE1-15` n'est pas dans la liste de `Q-06`, mais ce n'est pas *"cette seule ligne"* : si `LP7` entre un jour, **la question du poids se pose pour elle**, avec sa mesure.

`DEC-0123` reste `Acceptee` ; `Q-06` n'est pas modifiee ; `LP9` n'est pas un precedent pour les cinq lignes.

### A.147 Le plafond `en cours` de `MAT-CE1-14`

Accepte (A.145). `DEP-CE1-2` est lue *"montre, de 4 x 4 a 6 x 6, cases de meme taille"*, pas *"toutes designables"* (8.1). **La ligne ne passe pas a `couvert-valide` sur cette forme.** La forme (b) reste retiree ; elle ne se rouvre que par une nouvelle reponse. **Compteur de version** (A.32) : +1 si `en cours` avec les cinq criteres de `nadia-prof` ; je n'ai pas lu qu'elle les ait ecrits pour cette ligne, je ne le calcule pas (comme A.144). `MAT-CE1-14` reste `non couvert` dans la matrice : aucune cellule ne change.

### A.148 La composition du palier 4, etat definitif

Prix : ceux de `nadia-prof` (`arbitrage-nadia-palier-4.md` sections 1 a 8). Developpeurs : `QP6` (A.134) pour B et A', `marc-dev` pour A (`RG1`, `RG3`). Total = somme des lignes, **sa decision de produit** au-dessus de 8,0 (8.4 : *"j'ecris le depassement de 8,0 comme le sien, pas comme `O2`"*).

| Instance | Developpeur | Postes | Total | Lots |
|---|---|---|---|---|
| A, francais CE1 | `marc-dev` | arbitrage 0,5 ; marge 0,5 ; `LP1` `FRA-CE1-21` 1,0 | **2,0** | 1 |
| B, mathematiques CE1 | `lea-dev` | arbitrage 0,5 ; marge 0,5 ; `LP2` `MAT-CE1-10` 1,0 ; `LP3` `MAT-CE1-11` 1,0 ; `LP9` `MAT-CE1-14` 1,5 | **4,5** | 3 |
| A', EMC | `sofia-sm` | arbitrage 0,5 ; marge 0,5 ; `LP4` `EMC-CE1-02` 1,0 ; `LP5` `EMC-CE1-03` 1,0 ; `LP6` `EMC-CE1-04` 1,0 | **4,0** | 3 |
| C | aucun | aucun lot recevable (8.4 point 3) | 0 | n'ouvre pas |
| **Palier** | | | **10,5** | **7** |

Arithmetique, treize lignes `instance|poste|prix|etat`, `awk -F'|'` sur le modele de `preparation-palier-4-camille.md` section 5, sortie lue : `A 2.00` (1 lot), `Ap 4.00` (3 lots), `B 4.50` (3 lots), `total 10.50`, `engage 9.00`, `marge 1.50`, 7 lots. **Engage 9,0** = 1,5 d'arbitrages + 7,5 de lots ; **marge 1,5**, 0,5 par instance, allouee a aucun poste. 9,0 plus `LP9` 1,5 = 10,5.

**`PAR-8` par instance** : A 2,0 sur 5,0, 1 lot sur 3 ; B 4,5 sur 5,0, 3 lots sur 3 ; A' 4,0 sur 5,0, 3 lots sur 3. **Tenu, B et A' sont au plafond de lots.** `LP7` et `LP9` ne se cumulent pas dans B (6,0 et quatre lots) : un seul des deux illustres (8.4 point 3), c'est `LP9`.

**Ce qui est conditionnel**, du plus sur au moins sur :

| Poste | Condition | Si elle ne tient pas |
|---|---|---|
| `LP6` | `nadia-prof` 3.3 : pas de troisieme lot d'EMC *"tant que A' n'a pas montre un lot ecrit dans le palier sans refus"* : `LP6` ne part qu'apres un verdict de `LP4` ou `LP5` sans refus | le total tombe a 9,5 ; `LP6` est un report (A.150) |
| `LP1` | amendement de `S57-BR-FRA-CE1-21` par moi : **non fait** (A.151, point 1) ; sans lui, *"je n'arbitre pas le lot de A"* (5.2) ; `QP1` choisie | l'instance A ne s'ouvre pas ; le total perd 2,0 |
| `LP9` | les conditions de A.149 | le total tombe a 9,0 ; `LP9` est un report (A.150) |

**Plancher** : 8,0 = trois arbitrages, trois marges, cinq lots (`LP1` a `LP5`) ; c'est le repli de `nadia-prof` (`arbitrage-nadia-palier-4.md` section 3).

**Ordre**, produit (l'ordre de fusion est a `sofia-sm`, `avis-theo-palier-4.md` 3.3 : premier pret, premier fusionne) : `DEC-0120` A.35 tient (mathematiques 4a, francais 4b, puis EMC) ; `LP6` est le dernier de A' et `LP9` le dernier de B, parce que ses prealables sont les plus longs et qu'aucun autre lot ne doit l'attendre. **`LP9` ne bloque aucun lot.**

**Ce que la composition donne si les verdicts tombent** (`MATRICE-couverture.md`, 152 sur 202 aujourd'hui) : `couvert-valide` pour `LP1`, `LP4`, `LP5`, `LP6` : **156 / 202 = 77,23 %** ; `en cours` pour `LP2`, `LP3`, `LP9` : 5 a 8 ; compteur de version (A.32) de 157 a 164 : **164 / 202 = 81,19 %**, sous reserve des cinq criteres de `nadia-prof` pour chaque `en cours`. Un lot refuse ne ferme rien. **File de fusion, sept lots** (A.142, **calculee, non mesuree**) : `verify` 1 h 19 a 1 h 29, job `build` 2 h 25 a 2 h 41.

### A.149 `LP9` : engageable, a quelles conditions, et la regle d'ordre sur le poids

**Engageable** signifie : dans la composition, au prix indicatif de 1,5. Le lot ne s'ouvre (worktree, ecriture du paquet) que lorsque ces conditions sont ecrites, chacune a son proprietaire :

| Proprietaire | Condition | Source |
|---|---|---|
| `nadia-prof` | arbitrage prealable : prix et instance confirmes ; **`PAR-13` pour la forme (a) ecrit** (son motif de 1.4 etait la designation, qui n'existe plus) ; **reponse sur *"ne la nomme pas par sa coordonnee seule"*** (les 20 enonces du paquet actuel nomment une coordonnee) ; taille de grille et nombre d'items (A.151) | `arbitrage-nadia-palier-4.md` 8.1 ; A.143 ; `avis-theo-palier-4.md` 7.5 |
| `ines-design` | **avant l'ecriture du paquet** : les glyphes manquants (A a F, 3, 4, 6), les trois marques (objet, depart, arrivee), puis la mesure d'un candidat rendu a 176 px, a 744 x 551 et a 320 px ; elle propose, ne tranche pas | `avis-theo-palier-4.md` 7.3, 7.7 |
| `theo-archi` | **20 SVG** (`quadrillage-a` a `quadrillage-t`, `viewBox` 100, 176 px, trait de 1,2 unite, etiquettes tracees, aucun `<text`) ; **un test de geometrie neuf dans `bootstrap`, taille M**, qui croise chaque dessin avec son item, avec ses morsures sur les 20 dessins reels ; `ADR-0080` **non amende**, aucune regle ArchUnit touchee ; les 20 dessins hors de `DESSINS_DE_DESIGNATION` | `avis-theo-palier-4.md` 7.3, 7.5, 7.6 |
| `lea-dev` | **la constante de poids est mise a jour dans le commit qui ajoute les dessins**, sur la valeur mesuree du commit, avec une ligne de motif dans la Javadoc ; elle ne devance jamais les octets | `avis-theo-palier-4.md` 7.4 ; `ADR-0077` decision 6 |
| `sofia-sm` | composition, `planning.md`, file de fusion | A.152 |

**Regle d'ordre sur `POIDS_TOTAL_MAXIMUM`.** La constante est en egalite avec la somme des octets : deux commits qui ajoutent des dessins la deplacent chacun, et le second entre en conflit avec le premier.
1. **Dans le palier 4, `LP9` est le seul lot qui installe des dessins.** `LP1`, `LP2` et `LP3` portent 0 illustration (`grep -c 'illustration:'` rend 0 sur `ce1-mots-invariables`, `ce1-masses-contenances`, `ce1-calendrier-dates`) ; `LP4` a `LP6` sont des paquets textuels a ecrire. Aucun conflit n'est attendu dans cette composition. Pour les paquets a ecrire, **je ne l'ai pas verifie sur des fichiers qui n'existent pas** : `sofia-sm` le relit lot par lot a la composition.
2. **Si un autre lot installe des dessins dans le meme palier, `LP9` fusionne en premier** : sa hausse est la seule que l'utilisatrice ait acceptee (A.146). Le second lot ne fusionne qu'apres avoir fusionne le sommet de `round-two` (fusion, jamais rebase, `avis-theo-palier-4.md` 3.3), **recalcule la constante sur la somme mesuree de l'arbre fusionne** et la met a jour dans son propre commit ; une valeur calculee sur sa seule branche n'est jamais commitee.
3. **Un lot qui n'est pas `LP9` et qui ajoute des octets n'est pas couvert par le oui** (A.146) : la question se repose pour sa ligne avant son commit.

### A.150 Reports, comptes

| Poste | Compte | Ce qui s'ecrit |
|---|---|---|
| `LP1` a `LP5` | premier ecartement au palier 3 | **soldes par l'engagement** |
| `LP6` | premier ecartement au palier 3 ; solde par l'engagement | s'il ne part pas (condition de A.148) : **deuxieme**, au palier 4 ; un troisieme demande une `DEC` avec son motif |
| `LP9` | premier ecartement au palier 3 ; solde par l'engagement | si ses conditions (A.149) ne tiennent pas a la cloture du palier 4 : **deuxieme** ; un troisieme demande une `DEC` |
| `LP7` `MAT-CE1-15` | premier ecartement au palier 3 | **deuxieme ecartement, ecrit ici : non engage au palier 4.** Motif : B est au plafond de trois lots avec `LP9` (`PAR-8`) ; un seul illustre par palier (8.4) ; la condition du second triangle rectangle est un besoin de dessin non route a `theo-archi` ; le paquet est a reecrire ; son dessin neuf demanderait une nouvelle reponse sur le poids (A.146). **Un troisieme au palier 5 demande une `DEC` avec son motif** |
| `LP8` `FRA-CE1-03` | premier ecartement au palier 3 | **deuxieme ecartement, ecrit ici : non engage au palier 4.** Motif : cousinage juge recevable (3.1), mais **aucune story n'est ecrite**, `marc-dev` porte `LP1` (`RG4`) et `LP1` demande deja la reecriture de 24 enonces. C'est le seul lot de francais CE1 recevable et non engage : **premier de la file du palier 5**. Un troisieme demande une `DEC` |
| `S57-GARDE-NOMBRE-DE-MANCHES`, `S57-TYP1-TEXTES-DE-GABARIT`, `S57-MESSAGE-PARTIE-PERDUE` | deuxieme ecartement (`preparation-palier-4-camille.md` section 6) | **une troisieme non-entree au palier 4 est la direction que personne n'a choisie.** La premiere est prete (liste fermee, developpeur a designer par `sofia-sm`) : proposee hors `PAR`, prix 0. Les deux autres attendent `Q-T12` (`theo-archi`) et une mesure (`sofia-sm`). **Si elles n'entrent pas a l'ouverture, une `DEC` avec le motif s'ecrit a ce moment-la** ; je ne l'ecris pas ici, le motif n'est pas connu |
| `FRA-CE1-08`, `-09`, `-29`, `FRA-CE1-27`, arts plastiques CE1, questionner le monde CE1 | aucun report de mon fait | condition 4, `PAR-13`, `Q-06` : s58 ou fin d'A' |

### A.151 Ce qui reste ouvert

1. **Mon amendement de `S57-BR-FRA-CE1-21`** (24 enonces a reecrire, `QP1`) : demande par `nadia-prof` 5.2 avant son arbitrage du lot de A, **non fait**, hors de cette demande. Sans lui `LP1` n'est pas engageable ; si A n'a pas de lot, l'instance n'ouvre pas.
2. **Taille de la grille** (4 x 4, 5 x 5, 6 x 6) et **nombre d'items** : `nadia-prof` (son refus d'avance est *"une grille plus grande que 6 x 6"*, et `DEP-CE1-2` borne 4 x 4 a 6 x 6) et moi, **pas l'utilisatrice**. Elements : poids +66 %, +77 %, +88 % (A.146) ; a 176 px, 6 x 6 donne des cases de 25,2 px et des etiquettes de 14,1 px, **au minimum d'`ines-design`**, non verifie par un garde ; 5 x 5, 30,3 px de case ; 4 x 4, 37,8 px (`avis-theo-palier-4.md` 7.3, calcul non mesure). La mesure d'`ines-design` (A.149) departage. **Je ne tranche pas la taille a la place de `nadia-prof`** : elle depend de ce que l'enfant lit.
3. **`PAR-9`** : egalite ou plafond a 10,5 sur 20,0 (A.138). `nadia-prof` ecrit que le total est la somme des lignes (8.4 point 4) ; une ligne de `PAR-8` pour quatre instances reste a elle.
4. `QP7` : a la cloture du palier 5 (A.136). `QP8` : non posee, C n'a pas de lot.
5. Le signalement du referentiel de mathematiques CE1 (aucune ligne sur les donnees en tableau, 8.1) : a verifier contre le texte officiel, ligne neuve a `nadia-prof`. **Non traite.**

### A.152 Routage, ce que cet amendement ne fait pas

| A qui | Quoi |
|---|---|
| `sofia-sm` | `composition-palier-4.md`, l'amendement de `planning.md`, la table d'isolation (agents, ports, sous-repertoires), la file de fusion (l'ordre de A.148 est celui du produit), le developpeur du hors `PAR` ; **la poussee** que l'utilisatrice demande, apres ces ecritures |
| `nadia-prof` | l'arbitrage prealable de `LP9` et de `LP6` (A.149, A.148), la taille de grille, `PAR-9` |
| `theo-archi` | le test croise et les 20 SVG avec `lea-dev` ; `QP1` pour `LP1` |
| `ines-design` | glyphes, marques, mesure (A.149) |
| `lea-dev` | rien avant l'engagement de `LP9` |
| `camille-po` | l'amendement de `S57-BR-FRA-CE1-21` (A.151, point 1) ; la story de `LP8` si elle entre au palier 5 |

**Ne fait pas** : ne prononce aucun Done, ne juge aucun contenu, ne compose pas le palier (`sofia-sm`), n'amende ni `planning.md` ni la matrice (`MAT-CE1-14` reste `non couvert`), ne fixe aucun prix, n'affecte aucune instance. **Decisions de produit prises ici** : (1) la composition est consignee a 10,5 avec ses conditionnels, sur les mots de l'utilisatrice ; (2) la regle d'ordre sur `POIDS_TOTAL_MAXIMUM` (A.149) ; (3) deuxieme ecartement de `LP7` et de `LP8`, ecrit. **Changement de code decide** : les 20 dessins, le test croise et la constante de poids de `LP9`, **proprietaire `lea-dev` (B)**, forme du test par `theo-archi`, engagement par la composition de `sofia-sm` : la decision a un proprietaire, **elle n'est appliquee que par l'engagement de la story**. Aucun identifiant de condition neuf. Non commite.


## Amendement du 2026-10-22, vingt-cinquieme (`camille-po`, palier 4 : la mention de matrice de `MAT-CE1-10`, le troisieme rejeu de `LP1` et de `LP4`, `S57-ARB-PALIER-4`)

Annotation datee. **Rien au-dessus n'est reecrit.** **Statut de `DEC-0120` : `Acceptee`, inchange.** Arbre : `round-two` a `eb267ba7`, ecrit dans un worktree dedie, 14:12 (UTC+02:00), lu par `date`. Ce que je n'ai pas fait : lancer un jar, jouer un contenu, fixer un prix, engager un poste, toucher `planning.md`, `LP9` ou un lot.

### A.153 Les mots, et ce que chacun vaut

Cites tels que l'agent lanceur me les transmet, 2026-10-22 (forme 2 de `DEC-0062` G) : *"LP2 lancer camille"*, *"LP4 M1 a"*, *"LP1 corrige"*.

| Mots | Question a laquelle ils repondent | Ce qu'ils valent |
|---|---|---|
| *"LP2 lancer camille"* | le verdict **VALIDE** de `nadia-prof` sur `MAT-CE1-10` (rejeu, `c29d1d26`) demande une mention dans la cellule de la matrice avant ou avec le mouvement de la ligne | la tache est lancee : A.154 |
| *"LP4 M1 a"* | `nadia-prof`, rejeu de `EMC-CE1-02` : *"c'est a `camille-po` de lui poser la question avec ces deux formes"* (regle citee dans les 12 enonces, forme (a) ; ou sanction aussi entre chevrons, forme (b)) | **forme (a)** pour la tache 1. Critere 7 de `S57-BR-EMC-CE1-02` (backlog). Ce que ces mots laissent ouvert : A.157 |
| *"LP1 corrige"* | refus maintenu de `nadia-prof` sur l'item 12 de `ce1-mots-invariables` | lu comme un fait : la branche `s57-marc-fra-ce1-21` porte `8c76b1c7` (*"item 12 refait sur pour"*), constate par `git log`, **non joue, non lu par moi**. Si ces mots sont une instruction autre, l'utilisatrice le dit |

Les mots ne disent rien du prix du rejeu ni du palier ou il se place : A.155 les place sur la regle de `nadia-prof` (*"a `camille-po` et `sofia-sm` de le placer"*).

### A.154 `MAT-CE1-10` : la mention est ecrite, la ligne ne bouge pas

**Ecrit.** La cellule de `MAT-CE1-10` (`squad/programme/MATRICE-couverture.md`) porte, mot pour mot, la mention de `nadia-prof` (`arbitrage-nadia-preparation-palier-3-ce1.md` 4.3, reprise dans sa fiche, section *Rejeu du 2026-10-22*) : *"comparer des masses et des contenances et utiliser le gramme, le kilogramme et le litre, par du texte ; 'mesurer' reste non couvert, aucune balance ni aucun recipient gradue n'est rendu"*. `grep -c` de cette phrase dans la matrice : **0 avant, 1 apres** (critere 5 de `S57-BR-MAT-CE1-10`). Une note datee en tete de la matrice porte le compte.

**La ligne ne bouge pas, pour trois motifs lus sur les fichiers.**
1. **Le jeu n'est pas servi.** Le paquet `ce1-masses-contenances.yaml` existe sur `round-two`, mais `grep -c "paquet: ce1-masses-contenances$"` sur `catalogue.yaml` rend **0**. L'entree est sur la branche `s57-lea-mat-ce1-10`, non fusionnee.
2. **La fiche et son verdict sont sur `s57-nadia-p4-b`**, non fusionnee : la matrice ne cite pas un Done qui n'est pas sur `round-two`.
3. **La fusion n'a pas eu lieu** : ni sa CI (tous les jobs de `ci.yml`), ni son ordre dans la file de `composition-palier-4.md`.

**Ecart de mots, dit.** `nadia-prof` ecrit *"La ligne `MAT-CE1-10` reste `en cours`"* ; la matrice dit **`non couvert`** (jeu `-`). La ligne n'a jamais ete `en cours` : *"reste"* designe le plafond. Je garde `non couvert` jusqu'a la fusion ; **dans le commit de fusion du lot**, la ligne passe a `en cours` et le jeu `bandit-manchot-masses-contenances-ce1` est inscrit. Le statut commence par `non couvert` suivi d'une espace : la commande de la "Methode du compte" le compte `nc`.

**Compte.** Commande de la "Methode du compte", lancee avant et apres ce geste, sortie identique : `couvert-valide` 152, `en cours` 5, `non couvert` 45, `non couvrable en l'etat` 75, total 277, **152 / 202 = 75,25 %**. **Le compteur de 152 / 202 ne bouge pas.** Apres la fusion, `en cours` passe a 6 et `non couvert` a 44, `couvert-valide` reste a 152 : `MAT-CE1-10` ne ferme jamais (plafond `en cours`). Compteur de version (A.32) : non calcule, le lot n'est pas fusionne.

### A.155 Le troisieme rejeu de `LP1` et de `LP4` : ou, a quel prix, sous quel seuil

**Les faits.** Les trois marges de rejeu de 0,5 sont consommees, chacune par un seul rejeu, ecrit par `nadia-prof` dans la fiche du lot : A par `FRA-CE1-21` (*"ce rejeu consomme la marge de A (0,5)"*), A' par `EMC-CE1-02` (*"ce rejeu consomme la marge de A' (0,5)"*), B par `MAT-CE1-10` (*"consomme la marge de B (0,5)"*). Ses mots sur la suite : *"Un troisieme passage n'a plus de marge dans la composition : il est a `camille-po` et `sofia-sm` de le placer (palier 4 sur un depassement ecrit, ou palier suivant)"* (A) ; *"Un troisieme passage n'a plus de marge : a `camille-po` et `sofia-sm`. Je le fais sur la tache 1 seule si elle est seule changee"* (A'). Etat : `LP1` refuse maintenu sur l'item 12 seul ; `LP4` refuse maintenu sur `M1` seul.

**Decision : les deux rejeux se placent au palier 4, par un depassement ecrit ici, sur l'instance de leur lot.**

| Poste | Instance | Qui rejoue / qui corrige | Portee, ses mots | Prix | Etat |
|---|---|---|---|---|---|
| rejeu 3 de `LP1` `FRA-CE1-21` | A | `nadia-prof` (A : `s57-p4-nadia-prof-a`, port 18511) / `marc-dev` | *"Je rejoue l'item 12 seul s'il est le seul change"* | **0,5 au plus** | **propose** |
| rejeu 3 de `LP4` `EMC-CE1-02` | A' | `nadia-prof` (A' : `s57-p4-nadia-prof-ap`, port 18512) / `sofia-sm`, casquette developpeuse | *"Je le fais sur la tache 1 seule si elle est seule changee"* | **0,5 au plus** | **propose** |

**Prix.** Le prix est a `nadia-prof` (A.33, A.36) : **0,5 est un plafond, pas un prix**. Motif du plafond : un rejeu vaut 0,5 (`arbitrage-nadia-palier-4.md` section 1, *"c'est un rejeu"* ; A.105). Elle peut ecrire moins (`GNA-CE1-R1` a 0,25 pour 0,5 inscrit, A.105) : la ligne du registre se corrige sur sa valeur. **Je ne le fixe pas.**

**Pourquoi le palier 4 et pas le suivant.** (1) Le seuil le porte : 10,5 compose plus 1,0 = **11,5 sur 20,0**, il reste 8,5 (A.138). (2) `LP6` n'ouvre aucun worktree sans *"un verdict de `LP4` ou `LP5` sans refus"* (A.148) ; `LP4` est refuse deux fois, et un rejeu repousse a un palier 5 laisserait `LP6` bloque. (3) Un lot engage et non audite a la cloture glisse (A.72) : deux glissements de plus, sans motif de capacite.

| Instance | Compose | Rejeu 3 | Nouveau | `PAR-8` (5,0 et trois lots) | Reste sous 5,0 |
|---|---|---|---|---|---|
| A | 2,0 | 0,5 | **2,5** | tenu, 1 lot | 2,5 |
| A' | 4,0 | 0,5 | **4,5** | tenu, 3 lots (plafond de lots) | **0,5** |
| B | 4,5 | 0 | 4,5 | tenu, 3 lots (plafond de lots) | **0,5** |
| **Palier** | **10,5** | **1,0** | **11,5** | | 20,0 : reste 8,5 |

Un rejeu n'est pas un lot : le compte de lots de `PAR-8` ne bouge pas. **Registre** : `registre-prix-palier-4.txt` (A.156), `awk -F'|'` lance ce jour, sortie lue : `A 2.50`, `Ap 4.50`, `B 4.50`, `total 11.50` ; par etat `engage 7.50`, `consomme 1.50`, `non-engage 1.50`, `propose 1.00` ; sans les deux lignes `propose`, **10.50**, la composition de `sofia-sm`.

**Ce qui n'est pas decide ici.** L'**engagement** des deux postes est a `sofia-sm` (amendement date de `planning.md`, `RF11` de `composition-palier-4.md`) : tant qu'il n'est pas ecrit, ils sont `propose`. Aucun worktree, aucun agent n'est lance. **Un quatrieme passage n'est pas place** : s'il est refuse au rejeu 3, ni marge ni poste ne l'attendent, et je pose la question a l'utilisatrice (abandon du lot, ou nouveau placement) au lieu de le financer d'office : trois passages refuses sont la forme exacte d'une direction que personne n'a choisie. Aucune ligne neuve de la table d'isolation : les lignes de `nadia-prof` pour A et A' existent.

**Ce qui mord, et qui n'est pas 20,0.** B et A' n'ont chacune que **0,5 sous le plafond de 5,0 par instance** (`PAR-8`, que l'utilisatrice a dit inchange). Les lots sans marge de rejeu, `LP3` (B), `LP5` et `LP6` (A'), et `LP9` (B, non engage), ne peuvent porter qu'**un** rejeu de 0,5 au total dans B et un dans A' : un second rejeu de A' ou de B depasse 5,0. Question a l'utilisatrice : A.157, `QU1`.

### A.156 `S57-ARB-PALIER-4` et `registre-prix-palier-4.txt` : je les ecris

**Decision : je ferme `OV4` de `composition-palier-4.md`.** `PAR-16` demande une story par poste engage ; `p4-1` n'en avait pas (0 titre). **Story** : `S57-ARB-PALIER-4` (backlog, fin de fichier, annotation datee), sur le modele de `S57-ARB-PALIER-3` : transverse, aucune competence, prix aucun (pris sur le 0,5 d'arbitrage de chaque instance), perimetre `LP1` a `LP6`. Criteres : l'arbitrage sur `round-two` (clause, **verte a `eb267ba7`, dite**) ; une ligne datee `lecture complete` par paquet, N egal au compte (24, 21, 25 pour les trois paquets existants ; le compte du paquet livre pour les trois d'EMC) : **0 sur 6 a `eb267ba7`, rouge** ; l'ordre ligne avant verdict ; une ligne avant chaque rejeu. **Registre** : `registre-prix-palier-4.txt` dans le dossier du sprint, quinze lignes `instance|poste|prix|etat`, composition de `sofia-sm` reprise sans changer un prix, un lot ni une instance ; les trois marges sont inscrites `consomme` sur la parole de `nadia-prof` (A.155) ; `LP9` reste **`non-engage`** comme `composition-palier-4.md` le compte. **Je ne tranche pas `AP2`** (premier report ou solde par l'engagement) : la ligne de registre dit l'etat, pas le compte.

**Non fait, hors de la demande** : `OV3` (la ligne de rejeu de `-07` dans `registre-prix-palier-3.txt`, `arbitrage-nadia-palier-4.md` 5.5) reste ouverte ; elle est a moi.

### A.157 Ce que "LP4 M1 a" laisse ouvert, et ce qui reste a decider

| Code | Question | A qui | Sans reponse |
|---|---|---|---|
| `Q1` | **La forme (a) tient-elle dans `LON-CE1-1` et `-2` ?** Ses mots : *"`LON-CE1-1` et `-2` a tenir avec la phrase de plus"*. Le garde `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest` pose 20 mots (cliquet : une dette de 4 enonces de `ce1-longueurs`, qui *"ne remonte jamais sans un diff visible"*) et 3 phrases dont la derniere est la question ; a une lecture grossiere, les 8 items sans chevrons ont deja trois phrases. La citation de la regle ne tient donc que fondue dans une phrase existante, et chaque mot de plus approche les 20. Si elle ne tient pas, **qui cede : la borne ou la forme ?** Les mots de l'utilisatrice (*"M1 a"*) ne le disent pas. Je n'ai pas lance le garde sur un enonce reecrit | `nadia-prof` | `sofia-sm` s'arrete au premier enonce qui ne tient pas (critere 7) ; le rejeu 3 peut etre consomme pour une forme que ses propres bornes refusent |
| `Q2` | **Le rejeu 3 prend-il les reserves non bloquantes du rejeu** (item 24, indice de l'item 6, pli) ? Elle rejoue *"sur la tache 1 seule si elle est seule changee"* ; l'indice de l'item 6 est dans la tache 1, l'item 24 dans la tache 2 | `nadia-prof` | `sofia-sm` ne sait pas si les corriger dans le meme commit elargit le rejeu |
| `Q3` | **La forme (a) vaut-elle pour `EMC-CE1-03` et `-04`**, que `sofia-sm` ecrit ensuite ? Les mots de l'utilisatrice portent sur `LP4` | `nadia-prof` | un refus de `LP5` pour la meme forme n'a plus de marge (A.155) |
| `Q4` | Prix des deux rejeux, 0,5 au plus | `nadia-prof` | le registre garde 0,50 |
| `QU1` | **Rejeux suivants, `PAR-8` a 5,0 par instance.** Apres ce depassement, B et A' ont 0,5 sous 5,0. Voulez-vous (1) que chaque rejeu se place comme ici, un par un, par amendement date, `PAR-8` inchange (donc au plus un rejeu de plus dans B, un dans A') ; ou (2) relever le plafond de 5,0 pour les rejeux seuls ? Je ne propose pas (2) : elle n'est pas montree necessaire | l'utilisatrice | (1), par defaut |

**Critere 7 de `S57-BR-EMC-CE1-02`** ecrit (backlog, annotation du 2026-10-22), avec les mots de `nadia-prof` et la valeur d'aujourd'hui : **4 enonces sur 12** portent des chevrons sur `1152fdc9` (rouge). `LP1` : le critere 9 de `S57-BR-FRA-CE1-21` porte deja ses mots ; **je n'y ajoute rien** : `nadia-prof` a ecrit ce qu'elle applique (*"un autre mot compte s'il est juste, vrai dans la phrase, et qu'un enfant de CE1 peut l'ecrire"*) et c'est a elle de dire si cela amende le critere.

### A.158 Routage, ce que cet amendement ne fait pas

| A qui | Quoi |
|---|---|
| `sofia-sm` | l'engagement des deux rejeux (`planning.md`) ; le changement de contenu de la tache 1 de `ce1-emc-regle-sanction` : **proprietaire `sofia-sm` (A'), story `S57-BR-EMC-CE1-02` engagee, critere 7** : la decision a un proprietaire et une story ; la fusion de `LP2` (la ligne de `MAT-CE1-10` passe a `en cours` dans le commit de fusion, A.154) |
| `nadia-prof` | `Q1` a `Q4` ; son prix des rejeux ; son jeu du commit corrige |
| l'utilisatrice | `QU1`, et la suite d'un quatrieme passage s'il vient |
| `marc-dev` | rien de neuf : `LP1` est corrige (`8c76b1c7`), le rejeu est a `nadia-prof` |

**Ne fait pas** : ne prononce aucun Done, ne juge aucun contenu, ne fixe aucun prix, n'engage aucun poste, n'amende ni `planning.md` ni `LP9`, ne touche ni `domain/`, `application/`, `adapters/`, `bootstrap/`. **Decisions de produit prises ici** : (1) la mention de `MAT-CE1-10` est ecrite et la ligne ne bouge pas avant la fusion ; (2) les deux rejeux se placent au palier 4, depassement de 1,0 sous 20,0, prix a confirmer par `nadia-prof` ; (3) `S57-ARB-PALIER-4` et le registre du palier 4 sont ecrits. **Changement de code decide** : le contenu de la tache 1 de `ce1-emc-regle-sanction` (forme (a)), proprietaire `sofia-sm`, story engagee. `DEC-0120` reste `Acceptee`. Aucun identifiant de condition neuf : `Q1` a `Q4` et `QU1` n'ont pas de tiret.


## Amendement du 2026-10-22, vingt-sixieme (`camille-po`, palier 4 : verdicts du rejeu 3, quatrieme passage de `LP4`, plafond de `PAR-8`, `FRA-CE1-21`)

Annotation datee. **Rien au-dessus n'est reecrit** ; A.155 est **corrige** par A.160 (prix de `nadia-prof` a la place de mon plafond de 0,5). **Statut de `DEC-0120` : `Acceptee`, inchange.** Arbre : `round-two` a `eb267ba7`, commit `4a49c3f3` de `s57-camille-p4` ; les fiches de `nadia-prof` sont lues sur `s57-nadia-p4-a` et `s57-nadia-p4-ap`, non fusionnees. Ce que je n'ai pas fait : lancer un jar, jouer un contenu, fixer un prix, engager un poste, toucher `planning.md` ou `LP9`.

### A.159 Les mots

Cites tels que l'agent lanceur me les transmet, 2026-10-22 (forme 2 de `DEC-0062` G) : *"1 oui relance et ca reste au palier 4"*, *"2 a traiter dans le meme passage"*, *"on augmente le plafond pour que ca passe"*. La numerotation *"1"* et *"2"* renvoie a des questions de l'agent lanceur que je n'ai pas lues ; **je les lis** comme : *"1"*, le quatrieme passage de `LP4` se place au palier 4 ; *"2"*, la cellule de `FRA-CE1-21` s'ecrit dans le meme passage que celle de `MAT-CE1-10`. Si l'une des deux lectures est fausse, l'utilisatrice le dit.

### A.160 Rejeu 3 : verdicts et prix de `nadia-prof` ; quatrieme passage de `LP4`

| Lot | Verdict du rejeu 3 (fiche) | Prix de `nadia-prof` |
|---|---|---|
| `LP1` `FRA-CE1-21` (A) | **VALIDE SOUS RESERVE** sur `8c76b1c7` | **0,25** (*"Un item reecrit et un indice"*) |
| `LP4` `EMC-CE1-02` (A') | **REFUSE sur l'item 9 seul**, `M1` leve, sur `41203188` | **0,5** (*"12 items reecrits sur 24, l'item 24 et un indice"*) |

**Ses reponses** : `Q1` oui (18 a 20 mots, 3 phrases) ; `Q2` item 24 et indice de l'item 6 leves, pli en reserve ; `Q3` la forme (a) ne vaut pas pour `EMC-CE1-03` et `-04` (y valent `DDE-CE1-1`, `-2`, `INT-CE1-1`, `-2` et le point d'audit commun) ; `Q4` prix ci-dessus. A.157 est donc repondue, sauf `QU1` (A.161).

**Quatrieme passage de `LP4` : place au palier 4, `propose`.** Motif de l'item 9 (ses mots) : *"effacer un calcul n'est pas une faute"*. Elle rejoue *"que l'item 9 s'il est le seul change"*, **0,25**. Prix : le sien, **a corriger a la hausse seulement sur sa parole** si le commit change plus que l'item 9 (ses reserves 1 a 5 sont non bloquantes ; les y mettre elargit le rejeu). Instance A', agent `nadia-prof` (`s57-p4-nadia-prof-ap`, port 18512), correction `sofia-sm`. **C'est le troisieme refus de `LP4`** : un cinquieme passage n'est pas place ; s'il est refuse, la question va a l'utilisatrice avant tout financement.

**Registre** `registre-prix-palier-4.txt` (16 lignes), `awk -F'|'` lance ce jour : `A 2.25`, `Ap 4.75`, `B 4.50`, **`total 11.50`** ; par etat `engage 7.50`, `consomme 1.50`, `non-engage 1.50`, `facture 0.75`, `propose 0.25`. Sans les trois lignes de rejeu : 10.50, la composition de `sofia-sm`. Le total est le meme qu'en A.155 (1,0 de rejeux), repartis autrement : 0,25 + 0,5 + 0,25. Seuil de 20,0 : reste **8,5**.

### A.161 `QU1` : le plafond de 5,0 par instance

Ses mots : *"on augmente le plafond pour que ca passe"* (A.159). **Elle n'a donne aucune valeur.** Condition posee : *"pour que ca passe"*.

| Instance | Compose | Rejeux places | Total | Sous 5,0 |
|---|---|---|---|---|
| A | 2,0 | 0,25 | **2,25** | oui, reste 2,75 |
| A' | 4,0 | 0,5 + 0,25 | **4,75** | oui, reste **0,25** |
| B | 4,5 | 0 | 4,5 | oui, reste **0,5** |

**Avec les prix de `nadia-prof` (0,25, 0,5, 0,25), les rejeux deja places passent sous 5,0 : le relevement qu'elle demande n'est pas necessaire pour eux.** **Ma proposition de valeur, a confirmer, pas son chiffre : le plafond de `PAR-8` reste 5,0 par instance, aucun relevement n'est ecrit.** La plus petite valeur qui fait passer les rejeux places est 4,75 pour A' (2,25 pour A, 4,5 pour B) ; 5,0 les contient deja, et un plafond ecrit plus bas que 5,0 serait un abaissement. Je n'ecris donc pas *"relevee"* : sa condition est remplie sans le geste. **Portee, ma lecture a confirmer** : si elle veut quand meme relever, *"pour que ca passe"* vise les rejeux places, pas les postes de l'instance : un relevement pour tous les postes (lots compris) n'est pas dit par ses mots et je ne l'ecris pas.

**Ce que cela change pour les lots sans marge de rejeu** (marges de A, A' et B consommees) : `LP3` (B) et `LP9` (B, non engage) se partagent 0,5 : un rejeu a 0,5, ou deux a 0,25. `LP5` et `LP6` (A') se partagent **0,25** : un rejeu a 0,25, aucun a 0,5. Au-dela, le plafond de 5,0 mord ; un relevement serait alors a ecrire pour ce rejeu, sur sa parole d'alors (*"pour que ca passe"*, applique au cas), valeur proposee par moi et a confirmer. Un seul rejeu de 0,5 en A' a partir de maintenant **depasse** 5,0 (5,25). `QU1` est tranchee dans son principe ; la valeur, non.

### A.162 `FRA-CE1-21` : la cellule est ecrite, la ligne ne bouge pas

Verdict du rejeu 3 : **VALIDE SOUS RESERVE** ; `nadia-prof` ecrit que la ligne peut passer a `couvert-valide` avec, dans la cellule, **ecrites le jour du verdict** : *"mots sans accent seuls tant que l'accent est tolere"* (critere 7) et *"sur la tablette, des mots sont proposes au-dessus du clavier ; un enfant qui en touche un n'a pas ecrit le mot"* (critere 12). **Ecrites, mot pour mot**, dans la cellule de `FRA-CE1-21` (`grep -c` de la premiere phrase : 0 avant, 1 apres), avec la reserve du pli. **La ligne reste `non couvert` jusqu'a la fusion du lot** : meme raisons qu'A.154 (le jeu `saisie-mots-invariables-ce1` et la fiche sont sur des branches non fusionnees). Dans le commit de fusion : `couvert-valide` 153, `non couvert` 44. **152 / 202 ne bouge pas** : meme commande, meme sortie avant et apres (152 / 5 / 45 / 75 / 277).

### A.163 Routage, ce que cet amendement ne fait pas

| A qui | Quoi |
|---|---|
| `sofia-sm` | l'engagement du quatrieme passage de `LP4` (`planning.md`) ; le changement de l'item 9 de `ce1-emc-regle-sanction` : **proprietaire `sofia-sm` (A'), story `S57-BR-EMC-CE1-02` engagee, critere 8** ; les fusions de `LP1` et `LP2`, ou les cellules passent (A.154, A.162) |
| `nadia-prof` | son jeu du commit corrige ; la valeur d'un relevement de `PAR-8` si elle veut en confirmer un |
| l'utilisatrice | confirmer ou corriger la valeur de A.161 (5,0 inchange) |

**Ne fait pas** : ne prononce aucun Done, ne juge aucun contenu, ne fixe aucun prix, n'engage aucun poste, ne touche ni `planning.md`, ni `LP9`, ni `domain/`, `application/`, `adapters/`, `bootstrap/`. **Decisions de produit prises ici** : (1) le quatrieme passage de `LP4` se place au palier 4, 0,25, `propose` ; (2) les prix de rejeu sont ceux de `nadia-prof` ; (3) le plafond de `PAR-8` reste 5,0, proposition a confirmer ; (4) la cellule de `FRA-CE1-21` est ecrite, la ligne ne bouge pas. **Changement de code decide** : l'item 9 de `ce1-emc-regle-sanction`, proprietaire `sofia-sm`. `OV3` et `AP2` restent ouverts. Aucun identifiant de condition neuf.


## Amendement du 2026-10-22, vingt-septieme (`camille-po`, palier 4 : rejeux de `LP3` et de `LP5`, plafond de `PAR-8`, matrice apres fusion, reserves, `LP6`)

Annotation datee. **Rien au-dessus n'est reecrit.** **Statut de `DEC-0120` : `Acceptee`, inchange.** Arbre : `round-two` a `51a9d957` (`LP1`, `LP2`, `LP4` et `s57-camille-p4` fusionnes). Les fiches de `LP3` et `LP5` sont lues sur `s57-nadia-p4-lp3` et `s57-nadia-p4-lp5`, non fusionnees. Ce que je n'ai pas fait : lancer un jar, jouer un contenu, fixer un prix, engager un poste, toucher `planning.md`, `LP9`.

### A.164 Les mots

Cites tels que l'agent lanceur me les transmet, 2026-10-22 (forme 2 de `DEC-0062` G) : *"lance LP3, LP5 et p4-10 et si besoin releve le plafond"*. Le relevement est **conditionnel** (*"si besoin"*) ; la ligne de `p4-10` n'est pas de mon ressort ici (aucun prix, hors `PAR`). Ses mots ne nomment pas `LP6`.

### A.165 Les rejeux et leurs prix, au registre

| Lot | Verdict de `nadia-prof` | Prix | Etat au registre |
|---|---|---|---|
| `LP4` `EMC-CE1-02` (A'), rejeu 4 | **VALIDE SOUS RESERVE** sur `f36d83bc` | 0,25 (A.160 : le prix etait le sien) | `propose` devient **`facture`** |
| `LP3` `MAT-CE1-11` (B) | **REFUSE** sur l'item 18 : *"l'item ne demande que l'ordre de trois mois"* | **0,25** : *"Prix du rejeu : 0,25 si seuls l'item 18 et des reserves changent"* | rejeu **`propose`** |
| `LP5` `EMC-CE1-03` (A') | **REFUSE** sur les items 14 et 24 | **0,25** : *"0,25 si seuls les items 14 et 24 changent"* | rejeu **`propose`** |

Les prix sont ceux de `nadia-prof` ; un prix ne monte que sur sa parole si le commit change plus que ces items. `propose` : l'engagement est a `sofia-sm` (`planning.md`, `RF11`). **Registre** `registre-prix-palier-4.txt` (18 lignes), `awk -F'|'` lance ce jour : `A 2.25`, `Ap 5.00`, `B 4.75`, **`total 12.00`** ; par etat `engage 7.50`, `consomme 1.50`, `non-engage 1.50`, `facture 1.00`, `propose 0.50`. Sans les rejeux : 10.50. **Seuil de 20,0 : reste 8,0.** Je n'ai pas fait passer les lots fusionnes d'`engage` a `facture` : c'est un geste de cloture, hors de la demande.

### A.166 Le plafond de 5,0 par instance (`PAR-8`) : le besoin n'existe pas encore

Calcule sur le registre fusionne, marges consommees **comprises** (elles sont dans le compose) :

| Instance | Compose | Rejeux | Total | Plafond 5,0 |
|---|---|---|---|---|
| A | 2,0 (arbitrage 0,5 ; marge 0,5 ; `LP1` 1,0) | 0,25 | **2,25** | reste 2,75 |
| A' | 4,0 (arbitrage 0,5 ; marge 0,5 ; trois lots 3,0) | 0,5 (`LP4` rejeu 3) + 0,25 (`LP4` rejeu 4) + 0,25 (`LP5`) | **5,00** | **reste 0,00** : egal a 5,0, `PAR-8` dit *"au plus 5,0"* |
| B | 4,5 (arbitrage 0,5 ; marge 0,5 ; `LP2` 1,0 ; `LP3` 1,0 ; `LP9` 1,5) | 0,25 (`LP3`) | **4,75** | reste 0,25 |

**La plus petite valeur qui fait passer les rejeux places, par instance : A 2,25, A' 5,00, B 4,75.** Le plafond actuel (5,0) les contient tous, donc **aucun relevement n'est ecrit** : la condition posee par l'utilisatrice (*"si besoin"*) n'est pas remplie par ce calcul. Je le dis parce que l'agent lanceur ecrit *"il est maintenant besoin"* : l'egalite de A' (5,00) est sous le plafond, pas au-dessus. **Valeur a confirmer par l'utilisatrice, pas son chiffre : 5,0 inchange.**

**Ce qui est vrai quand meme** : A' n'a plus aucune marge sous 5,0. Un rejeu de plus dans A' (deuxieme rejeu de `LP5`, ou rejeu de `LP6`) depasse. Le relevement serait alors necessaire ; ma proposition de valeur, a confirmer, **sans l'ecrire comme relevee** : A' 5,25 pour un passage de 0,25 de plus (5,50 pour deux). B : 0,25 de marge, un passage de 0,25 de plus (`LP3` ou `LP9`) passe a 5,0 ; deux depassent (B 5,25). Portee : *"si besoin"* vise les rejeux, pas les lots, et je ne releve rien pour les lots. Question a l'utilisatrice : `QU2` (A.170).

### A.167 La matrice apres fusion : 152 / 202 devient 154 / 202

`LP1`, `LP2` et `LP4` sont fusionnes ; leurs commits joues sont ancetres de `round-two` (`git merge-base --is-ancestor` sort 0 pour `8c76b1c7`, `c29d1d26` et `f36d83bc`) et leurs paquets sont inchanges depuis (`git diff --stat <commit> HEAD -- <paquet>` : 0 ligne). Les trois entrees sont au catalogue (`saisie-mots-invariables-ce1`, `bandit-manchot-regle-sanction-ce1`, `bandit-manchot-masses-contenances-ce1`). **Mouvements** :

| Ligne | Avant | Apres | Cellule |
|---|---|---|---|
| `MAT-CE1-10` | `non couvert` | **`en cours`** | jeu inscrit ; mention exacte de `nadia-prof` conservee (A.154) ; plafond `en cours` ; *"mesurer"* non couvert |
| `FRA-CE1-21` | `non couvert` | **`couvert-valide`** | mention *"mots sans accent seuls tant que l'accent est tolere"* et reserve du clavier conservees (A.162), jeu inscrit |
| `EMC-CE1-02` | `non couvert` | **`couvert-valide`** | **VALIDE SOUS RESERVE** au rejeu 4 ; historique des refus ; trois reserves non bloquantes recopiees |

**Commande de la "Methode du compte"** (celle de `MATRICE-couverture.md`, reprise telle quelle, lancee sur `git show HEAD:squad/programme/MATRICE-couverture.md` puis sur le fichier modifie, les deux fois sur une seule commande, sortie lue) : **avant** `cv 152`, `ec 5`, `nc 45`, `ncb 75`, total 277, 152 / 202 = 75,25 % ; **apres** `cv 154`, `ec 6`, `nc 42`, `ncb 75`, total 277, denominateur 202, **154 / 202 = 76,24 %**. Par niveau, atteignables / `couvert-valide` : CE1 64 / 36 (avant 34), CP 59 / 52 et CM1 79 / 66 inchanges. Compteur de version (A.32), second nombre : 154 + 6 = 160 / 202 = 79,21 % **par la formule** ; son critere 2 (CI verte sur l'arbre) n'est pas lu par moi, et le plafond `en cours` de `MAT-CE1-10` exige les cinq criteres de `nadia-prof` que je ne calcule pas. La ligne passe parce que la fusion a eu lieu et que le verdict est sur `round-two`, **pas sur la foi d'une CI** : le Done est a `nadia-prof`.

### A.168 Reserves non bloquantes : stories, et criteres de correction de `LP3` et de `LP5`

Ecrits au backlog (annotation du 2026-10-22, apres `S57-BR-FRA-CE1-21`) :
- **Criteres de correction avec ses mots et la valeur d'aujourd'hui** : `S57-BR-MAT-CE1-11` critere 6 (item 18, `grep -c` rend 1, rouge) ; `S57-BR-EMC-CE1-03` critere 6 (items 14 et 24, 1 et 1, rouges). Proprietaires : `lea-dev` (B) et `sofia-sm` (A'), stories engagees.
- **Stories des reserves, non engagees, sans palier, prix a `nadia-prof`** : `S57-EMC-CE1-02-R1` (items 6 et 10 de `ce1-emc-regle-sanction`) ; `S57-PLI-MOTS-INVARIABLES-REVELATION` (`LP1` : 17 items sur 24 sous le pli a la revelation, de 13 px, mesure d'`ines-design` sur `f8d5d875`) ; `S57-SAISIE-MOTS-PROPOSES-PAR-LE-CLAVIER` (`LP1`, reserve du clavier ; `QP4` sans reponse de `theo-archi`).
- **Pas de story** pour la reserve de pli de `LP2` et de `LP4` : les fiches disent *"non remesure"*, mais `mesure-ines-palier-4-pli-744x551.md` porte la mesure, **sur `round-two`** (`LP2` 0 sur 21, section R4 ; `LP4` 0 sur 24 sur `f36d83bc`, section T2). Elle est a lire par `nadia-prof`.

### A.169 `LP6` : la recommandation de 3.3 n'est pas tenue, la condition de composition l'est

`nadia-prof` (fiche de `LP5`) : la condition de la composition (*"un verdict de `LP4` ou `LP5` sans refus"*) est **remplie par `LP4`** ; sa recommandation de 3.3 (*"tant que A' n'a pas montre un lot ecrit dans le palier sans refus"*) **n'est pas tenue** (`LP4` refuse trois fois, `LP5` une) ; *"c'est une recommandation, pas une condition : la decision est a `camille-po`, et l'ouverture suit la fusion de `LP5` (`RF5`)"*. **Ma decision de produit** : `LP6` reste engage dans la composition (A.148, inchange) ; **je ne le lance pas** : les mots de l'utilisatrice nomment `LP3`, `LP5` et `p4-10`, pas `LP6`. Il suit la fusion de `LP5` (`RF5`, `composition-palier-4.md`, ordre de `sofia-sm`) et son verdict, ce qui est la recommandation de 3.3 appliquee a `LP5` plutot qu'a un lot sans refus. **Si le verdict de `LP5` est un refus de plus, ou si `LP6` ne part pas au palier 4 : deuxieme ecartement** (A.148, A.150), un troisieme demande une `DEC`. **A l'utilisatrice, si elle veut ouvrir `LP6` avant la fusion de `LP5`** : c'est une exception a `RF5` (ordre de `sofia-sm`), non demandee par ses mots. Aucune decision d'ordre technique n'est prise ici.

### A.170 Routage, ce que cet amendement ne fait pas

| A qui | Quoi |
|---|---|
| `sofia-sm` | l'engagement des deux rejeux (`LP3`, `LP5`) ; les corrections : `lea-dev` (item 18 de `ce1-calendrier-dates`, critere 6) et `sofia-sm` (items 14 et 24 de `ce1-emc-droit-devoir`, critere 6) ; `LP6` et `RF5` |
| `nadia-prof` | les rejeux ; lire les releves de pli de `LP2` et `LP4` ; le seuil de `S57-PLI-MOTS-INVARIABLES-REVELATION` |
| l'utilisatrice | `QU2` : A' est a 5,00 sur 5,0 ; voulez-vous relever le plafond des maintenant pour que le prochain passage de `LP5` ou de `LP6` ne bloque pas, ou le faire cas par cas (defaut) ? Valeur proposee, a confirmer : A' 5,25 |
| `theo-archi` | `QP4` (attribut contre les mots proposes par le clavier) |

**Ne fait pas** : ne prononce aucun Done, ne juge aucun contenu, ne fixe aucun prix, n'engage aucun poste, n'amende ni `planning.md`, ni `LP9`, ni `domain/`, `application/`, `adapters/`, `bootstrap/`. **Decisions de produit prises ici** : (1) rejeux de `LP3` et `LP5` places, 0,25 chacun, `propose` ; (2) `PAR-8` inchange a 5,0, aucun relevement ecrit, valeurs de repli proposees ; (3) matrice 154 / 202 ; (4) `LP6` non lance. **Changement de code decide** : les items de `LP3` (18) et de `LP5` (14, 24), proprietaires `lea-dev` et `sofia-sm`, stories engagees avec critere. `OV3` et `AP2` restent ouverts. Aucun identifiant de condition neuf : `QU2` n'a pas de tiret.


## Amendement du 2026-10-23, vingt-huitieme (`camille-po`, palier 4 : cloture, ce qui reste ouvert apres la fusion de `LP1` a `LP6` et de `p4-10`)

Annotation datee. **Rien au-dessus n'est reecrit.** **Statut de `DEC-0120` : `Acceptee`, inchange.** Arbre : `round-two` a `c08e49ab`, tout fusionne. Le detail ligne par ligne est dans `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/arbitrage-camille-cloture-palier-4.md`. Ce que je n'ai pas fait : lancer un jar, jouer un contenu, lever une reserve, fixer un prix, engager un poste, pousser.

### A.171 Les mots, et ce qu'ils m'autorisent

Cites tels que l'agent lanceur me les transmet, 2026-10-23 (forme 2 de `DEC-0062` G, non lus de sa main) : *"clos ou reporte ce qui reste ouvert apres la fusion, ecris la cloture et push round-two"*. Ils m'autorisent a dire, pour chaque point ouvert, **CLOS** (avec la raison) ou **REPORTE** (ou, a qui, quand, quel declencheur). Ils ne m'autorisent pas a lever une reserve (`nadia-prof` seule), a trancher une forme (`theo-archi`), ni a pousser (la poussee est a `sofia-sm`, `A.152`).

### A.172 La matrice apres fusion : 154 / 202 devient 156 / 202

Les trois paquets sont ceux que `nadia-prof` a joues : `git diff <commit joue> HEAD -- <paquet>` rend 0 ligne pour `f863fa7a` (`ce1-calendrier-dates`), `5b9bb648` (`ce1-emc-droit-devoir`) et `c975a7b8` (`ce1-emc-interet-commun`) ; les trois entrees sont au catalogue (`grep -c "paquet: <nom>$"` rend 1 chacune).

| Ligne | Avant | Apres | Cellule |
|---|---|---|---|
| `MAT-CE1-11` | `non couvert` | **`en cours`** | VALIDE SOUS RESERVE, plafond `en cours` ; mention de couverture de `nadia-prof` recopiee mot pour mot ; *"lire l'heure sur une horloge"* non couvert |
| `EMC-CE1-03` | `non couvert` | **`couvert-valide`** | VALIDE SOUS RESERVE au rejeu ; deux reserves non bloquantes recopiees |
| `EMC-CE1-04` | `non couvert` | **`couvert-valide`** | VALIDE SOUS RESERVE au second rejeu ; historique des deux refus ; reserves 3, 6, 7 recopiees telles que la fiche les formule |

**Commande de la "Methode du compte"** (celle de `MATRICE-couverture.md`, reprise telle quelle, lancee avant puis apres, une commande par etat, sortie lue) : avant `cv 154`, `ec 6`, `nc 42`, `ncb 75`, total 277, 154 / 202 = 76,24 % ; **apres `cv 156`, `ec 7`, `nc 39`, `ncb 75`, total 277, denominateur 202, 156 / 202 = 77,23 %**. Par niveau, atteignables / `couvert-valide` : CP 59 / 52, CE1 64 / 38, CM1 79 / 66. Compteur de version (A.32), second nombre : 156 + 7 = 163 / 202 = 80,69 % par la formule, son critere 2 (CI verte sur l'arbre) non lu par moi ; **A.148 annoncait 164, l'ecart est `LP9`, non engage**. Les lignes passent parce que les lots sont fusionnes et que les verdicts sont sur `round-two`, pas sur la foi d'une CI.

### A.173 `LP6` : les reserves 3, 6 et 7, et la condition de composition

| Reserve | Decision | Raison | Declencheur |
|---|---|---|---|
| 3, pli | **CLOS**, aucune story | `ines-design` a mesure 0 element sous le pli a `c975a7b8`, 41 px de marge, sur `round-two`. **`nadia-prof` l'a levee** le 2026-10-23, 11:07 (`5d038278`, branche `s57-nadia-p4-lp6b`, lu par `git log round-two..s57-nadia-p4-lp6b` : un commit, **non fusionne** au moment de cet ecrit). Je ne la leve pas moi-meme : je constate sa levee | `sofia-sm` : fusionner la branche (RF11), la fusion |
| 6, item 10 et 18 | **REPORTE** : `S57-EMC-CE1-04-R1`, palier 5 | modifier le paquet change ce qu'elle a joue, c'est un lot neuf ; ses mots : *"La regle tombe a 15 sur 24"* ; elle ecrit de 6 et 7 : *"Elles peuvent etre reportees. Un lot qui les corrige [...] je relis alors les items touches [...] sans rejeu complet"* ; la barre de la fiche est tenue (17 sur 24), reserve non bloquante | composition du palier 5 ; apres `S57-GARDE-PAQUET-INTERET-COMMUN` |
| 7, item 13 | **REPORTE** : meme story | idem ; ses mots : *"'pour toute la classe' dit moins que la scene"* | idem |

**Condition de composition de `LP6` (point 9), ce que je retiens.** Elle etait **tenue a la lettre** : *"aucun worktree avant un verdict de `LP4` ou `LP5` sans refus"* ; les verdicts VALIDE SOUS RESERVE de `LP4` (rejeu 4, `70470aae`, 2026-10-22 15:18) et de `LP5` (`d5298c19`, 20:26) precedent le premier commit de `LP6` (`e85a93fe`, 23:08). **Elle n'a pas protege ce qu'elle visait** : `nadia-prof` 3.3 recommandait de ne pas prendre `LP6` tant qu'A' n'avait pas montre un lot ecrit dans le palier sans refus ; A.169 l'avait dite non tenue. Les faits : trois paquets ecrits dans le lot en A', trois refus au premier passage (`LP4` trois refus avant son verdict, `LP5` un, `LP6` deux). Sa recommandation s'est verifiee ; la condition que j'avais retenue ne la reproduisait pas. Je n'en tire aucune regle pour le palier 5 sans l'avis de `nadia-prof` et l'utilisatrice : le constat est la ligne suivante.

### A.174 Le defaut `T5` et les survivants de `yanis-qa` : une story, hors `PAR`, prix 0

**REPORTE** : `S57-GARDE-PAQUET-INTERET-COMMUN` (backlog, annotation du 2026-10-23). Elle porte `T5` (mutation M3 de `theo-archi`, 74 tests verts), `G5` et `LG5` (seuil de 3 a 4), `G11` et `LG11` (filtre par portee), et la remarque sur `.as(marqueur)` des sondes 46 a 49. **Aucun de ces points ne touche un paquet** : le Done de `LP6` n'est pas rouvert. Criteres verifies contre l'existant : les cinq mutations sont **vertes aujourd'hui** (74 sur 74), donc les criteres sont rouges ; le critere sur le message est rouge (*"Expecting code to raise a throwable."*). **Proprietaire du code** : `sofia-sm` (auteure du test) ; forme `theo-archi` ; morsures `yanis-qa`. **Je ne decide ni la forme ni le moment dans le palier** (`theo-archi` : *"Le moment [...] est a `sofia-sm`"*). Declencheur : avant `S57-EMC-CE1-04-R1`, parce que cette story retouche des scenes que le garde protege avec une marge nulle en portee *tous* (5 pour 5).

### A.175 Les reserves ouvertes de `LP1`, `LP3`, `LP4`, `LP5` (`LP2` : aucune hors pli)

| Lot | Reserve (mots de la fiche) | Decision | Story | Declencheur |
|---|---|---|---|---|
| `LP1` | pli a la revelation, 17 items sur 24 a 13 px sous le pli (mesure `ines-design`, `f8d5d875`) | **REPORTE** | `S57-PLI-MOTS-INVARIABLES-REVELATION` (A.168), report 1 | `nadia-prof` lit la mesure et maintient ou leve ; leve : story fermee sans suite |
| `LP1` | clavier : un mot propose au-dessus du clavier n'est pas un mot ecrit | **REPORTE** | `S57-SAISIE-MOTS-PROPOSES-PAR-LE-CLAVIER` (A.168), report 1 | reponse de `theo-archi` a `QP4` ; effet verifie par l'utilisatrice sur l'appareil |
| `LP3` | pli jamais mesure | **REPORTE** | `S57-PLI-LP3-LP5-MESURE` (neuve), report 1 | une mesure d'`ines-design`, puis lecture de `nadia-prof` |
| `LP4` | items 6 et 10 (`"Jade a taché exprès un dessin"`, `"Laisser jouer"`) | **REPORTE** | `S57-EMC-CE1-02-R1` (A.168), report 1 | composition du palier 5 ; prix a `nadia-prof` |
| `LP4` | pli non remesure | **CLOS cote produit** (A.168 le disait) | aucune | `ines-design` a mesure 0 sur 24 a `f36d83bc` (section T2) ; la lecture est a `nadia-prof` |
| `LP5` | indice de l'item 19 vers *lui donner un cadeau* | **REPORTE** | `S57-EMC-CE1-03-R1` (neuve), report 1 | composition du palier 5 ; prix a `nadia-prof` |
| `LP5` | pli jamais mesure | **REPORTE** | `S57-PLI-LP3-LP5-MESURE` | idem `LP3` |
| `LP2` | pli non remesure | **CLOS cote produit** | aucune | 0 sur 21 (section R4) ; lecture de `nadia-prof` |

Les criteres correctifs des deux stories neuves ont la valeur d'aujourd'hui a cote (`grep -c "font pour Hugo"` rend 1, `grep -l` des releves de `LP3` et `LP5` rend 0 fichier).

### A.176 Le prix des rejeux de `LP6`, et `PAR-8`

**Registre** `registre-prix-palier-4.txt` (20 lignes) : les lots fusionnes avec un Done passent d'`engage` a `facture` (geste de cloture, A.165 le laissait) ; les rejeux de `LP3` et `LP5` de `propose` a `facture`, au prix de `nadia-prof` (0,25 chacun). **Rejeu 1 de `LP6`, 0,50, `facture`** : son estimation du 2026-10-22 (*"je l'estime a 0,5, ce n'est pas une mesure"*), reprise telle quelle. **Rejeu 2 de `LP6` : `non-chiffre`, 0,00** : elle ecrit *"Je ne l'estime pas en prix : une phrase"* et le commit joue change dix scenes et cinq indices (fiche, second rejeu) ; **je ne fixe pas le prix a sa place**. `awk -F'|'` lance ce jour : `A 2.25`, `Ap 5.50`, `B 4.75`, **`total 12.50`** ; par etat `facture 9.50`, `consomme 1.50`, `non-engage 1.50`, `non-chiffre 0.00`.

**`PAR-8` est depasse en A' : 5,50 sur 5,0**, avant le prix du rejeu 2. A.166 l'annoncait : *"Un rejeu de plus dans A' [...] rejeu de `LP6`, depasse"*. **Aucun relevement n'est ecrit** : les mots *"si besoin releve le plafond"* (A.164) nomment `LP3`, `LP5` et `p4-10`, pas `LP6`. A' = 5,50 plus le prix du rejeu 2 (5,75 a 0,25 ; 6,00 a 0,50). **`QU2` reste ouverte, avec ces valeurs** (A.170 l'avait posee a 5,25 ; le besoin existe maintenant). Le palier est clos : le depassement est un fait comptable, il ne bloque rien, mais il est ecrit comme depassement et non comme relevement. Total du palier 12,50 sur 20,0.

### A.177 Reports a la cloture : `LP9`, `LP7`, `LP8`, stories hors `PAR`

Regle d'A.66 a A.73 et d'A.150. **`AP2` de `composition-palier-4.md` est tranchee** : `LP9` compte pour son **deuxieme ecartement** (premier : palier 3, A.118 ; palier 4 : non engage, les conditions d'A.149 non ecrites a la cloture, `OV5` non tenu). `composition-palier-4.md` l'ecrivait *"premier report"* a l'ouverture ; les deux comptes disent le meme fait, A.150 est le mien et le plus complet.

| Poste | Decision | Raison | Destinataire | Declencheur |
|---|---|---|---|---|
| `LP9` `MAT-CE1-14` grille | **REPORTE**, deuxieme ecartement ; **un troisieme demande une `DEC`** (A.150) | aucune des trois conditions d'A.149 n'a ete ecrite pendant le palier ; aucun worktree ouvert, aucun octet de dessin | `nadia-prof` (arbitrage, taille de grille), `ines-design` (glyphes, mesure), `theo-archi` (20 SVG, test croise), `lea-dev` (poids) | **composition du palier 5** : soit les conditions sont ecrites **avant** la composition et le lot est engage, soit une `DEC` avec le motif s'ecrit ; pas de troisieme inscription non engagee sans `DEC` |
| `LP7` `MAT-CE1-15` | **REPORTE**, deuxieme ecartement (ecrit en A.150) ; un troisieme demande une `DEC` | motif d'A.150 inchange ; paquet a reecrire | `nadia-prof`, `camille-po` | idem |
| `LP8` `FRA-CE1-03` | **REPORTE**, deuxieme ecartement ; un troisieme demande une `DEC` | **aucune story n'est ecrite** (`grep -c "^### .*FRA-CE1-03" docs/backlog/backlog.md` rend 0) ; je ne l'ecris pas ici, hors de la demande | `camille-po` : la story avant la composition | idem ; premier de la file du palier 5 |
| `S57-GARDE-NOMBRE-DE-MANCHES` | **CLOS, solde par l'entree** | `p4-10` fusionne (`f9f7f0fc`) | - | - |
| `S57-TYP1-TEXTES-DE-GABARIT`, `S57-MESSAGE-PARTIE-PERDUE` | **CLOS, retirees** (`DEC-0125`) | troisieme non-entree, A.150 | utilisatrice, pour confirmer | une phrase d'elle, ou une mesure |
| `S57-GARDE-CE1-PROPOSITIONS-ET-SUPPORTS` | **REPORTE**, report 1 | **ecart entre la story (`CA4` absent) et `theo-archi` (`CA4` fourni)** non tranche par moi ; `nadia-prof` dit si `Q-X1` vaut `CA4` | `nadia-prof` | sa ligne ; puis composition du palier 5 |

Non traites ici, et dits : la story des commentaires de `src/` (ecarts 33, 40 a 44, part code des 37 et 38 : **0 titre au backlog**, a moi) et la story de `LP8`. Les deux sont a ecrire avant la composition du palier 5 ; **je ne les ai pas lues pour les ecrire**.

### A.178 `DEC-0125` et le registre d'identifiants

`DEC-0125` est nee de ce geste (retrait de deux stories, section A.177). **Aucun identifiant de condition ou de reserve n'est alloue** : les stories et les rangs de ce texte (`S57-...`, `A.171`) ne sont pas des identifiants de condition ; `REGISTRE-identifiants.md` n'est pas modifie. `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` est lance sur ce diff (perimetre `-pl bootstrap -am`, nomme dans `arbitrage-camille-cloture-palier-4.md`).

### A.179 Routage

| A qui | Quoi |
|---|---|
| `nadia-prof` | lire les releves de pli de `LP2` et `LP4` ; chiffrer le rejeu 2 de `LP6` ; dire si `Q-X1` vaut `CA4` ; dire si une marge de rejeu de 0,50 par instance suffit pour un paquet ecrit dans le lot (A' : 2,00 de rejeux sur trois lots, hors le rejeu 2 de `LP6`) |
| `sofia-sm` | fusionner `s57-nadia-p4-lp6b` (`5d038278`) ; `S57-GARDE-PAQUET-INTERET-COMMUN` (code, moment), `S57-EMC-CE1-04-R1` et `S57-EMC-CE1-03-R1` (code, apres la garde) ; la composition du palier 5 ; `cloture-palier-4.md` ; la poussee |
| `theo-archi` | la forme de `T5` ; `QP4` |
| `ines-design` | `S57-PLI-LP3-LP5-MESURE` |
| l'utilisatrice | `QU2` (A' a 5,50 sur 5,0 plus le rejeu 2) ; confirmer ou refuser `DEC-0125` ; si le palier 5 s'ouvre (`QP7`) |

**Ne fait pas** : ne prononce aucun Done, ne juge aucun contenu, ne fixe aucun prix (le rejeu 1 est l'estimation de `nadia-prof`, le rejeu 2 n'est pas chiffre), n'engage aucun poste, n'amende ni `planning.md`, ni `composition-palier-4.md`, ni `domain/`, `application/`, `adapters/`, `bootstrap/`, ni aucun paquet, ni le catalogue. **Decisions de produit prises ici** : (1) matrice **156 / 202** ; (2) reserves 6 et 7 de `LP6`, `T5` et les survivants : stories ecrites, non engagees, ordre garde puis correction ; (3) reserve 3 de `LP6` : close, levee par `nadia-prof` (`5d038278`, a fusionner) ; reserves de pli de `LP2` et `LP4` : closes cote produit, a lever par `nadia-prof` seule ; (4) `LP9`, `LP7`, `LP8` : deuxieme ecartement, un troisieme demande une `DEC` ; (5) deux stories hors `PAR` retirees (`DEC-0125`) ; (6) `AP2` tranchee ; (7) `PAR-8` : depassement ecrit, aucun relevement. **Changements de code decides, avec proprietaire** : `S57-GARDE-PAQUET-INTERET-COMMUN` (`sofia-sm`), `S57-EMC-CE1-04-R1` et `S57-EMC-CE1-03-R1` (`sofia-sm`), tous non engages. **Ouvert** : `QU2`, `OV3`, le prix du rejeu 2 de `LP6`, la story de `LP8`, la story des commentaires de `src/`.

## Amendement du 2026-10-23, vingt-neuvieme (`camille-po`, palier 5 : les reponses de l'utilisatrice a `D1` a `D5` et aux autres points de la proposition)

Annotation datee. **Rien au-dessus n'est reecrit.** **Statut de `DEC-0120` : `Acceptee`, inchange.** Arbre : `round-two` a `f3b06989`, branche `s57-camille-p5-proposition`. Le detail et les calculs sont dans `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/proposition-camille-palier-5-ce1.md`. Ce que je n'ai pas fait : prononcer un Done, fixer un prix, engager un poste, ecrire une composition, engager un lot de dessins, chiffrer a la place de `nadia-prof`, creer `registre-prix-palier-5` (il n'existe pas), pousser.

### A.180 Les mots, et ce qu'ils m'autorisent

Cites tels que l'agent lanceur me les transmet, 2026-10-23 (forme 2 de `DEC-0062` G, non lus de sa main). Ils m'autorisent a consigner chaque reponse et a en ecrire les consequences de produit ; ils ne m'autorisent ni a chiffrer, ni a lever un plafond, ni a engager un lot.

| Point | Ses mots | Consigne en |
|---|---|---|
| `QU2` | "consigner le depassement" | A.186 |
| `DEC-0125` | "on retire" | A.187 |
| `QP7` | "on les compose au palier 5" | A.187 |
| Regle de `LP6` | "on ajoute", puis "Lecture avant verify" | A.184 |
| Confirmation d'`ArchUnit` du palier 4 | "theo-archi l'ecrit" | A.187 |
| s57 | "pas encore ferme" | A.187 |
| `QP4` | "j'ai teste de mon cote c'est ok" | A.187 |
| `T5` | "ok" | A.187 |
| `D1` | "on met la barre a 20" | A.181 |
| `D2` | "ok pour faire une rotation" | A.182 |
| `D3` | "on reste dans s57" | A.183 |
| `D4` | "on prend l'autre option" | A.184 |
| `D5` | "option 3 et 5 lignes" | A.185 |

### A.181 `D1` : la borne du palier 5 est 20,0

**Decision** : borne de `PAR-9` au palier 5 = **20,0**, comme au palier 4 (*"on monte a 20"*, A.126 a A.131). **Ce que cela change** : le total du palier (7,75 a 8,50 au bareme, 8,05 a 10,24 au surcout mesure de 0,10 a 0,58 par lot, proposition 4.3) ne peut plus depasser la borne ; il en reste 9,76 a 12,25. **Ce que cela ne change pas** : la composition (A : `LP8` ; B : `LP7`, `LP9` ; A' : trois stories de reserve ; C n'ouvre pas, `PAR-13`), le prix d'un lot (bareme et surcout de `nadia-prof`), et `PAR-8` par instance : a trois instances, 3 x 5,0 = 15,0 est le plafond d'instance, sous la borne. B passe 5,0 au-dela de 0,50 de surcout par lot (5,16 a 0,58). **Aucun lot n'est ajoute pour remplir la borne : c'est le stock de lots qui limite** (`nadia-prof`, `arbitrage-nadia-palier-4.md` : *"ce qui limite le palier, c'est le stock de lots, pas la borne"*). Un depassement de `PAR-8` en B s'ecrirait comme depassement, pas comme relevement, sauf mot contraire (`arbitrage-nadia-palier-4.md` 8.4) ; **`D1` ne parle pas de `PAR-8` et je ne l'etends pas**. La capacite de `nadia-prof` n'est pas la borne : elle dit dans son arbitrage prealable du palier 5 si elle tient la composition.

### A.182 `D2` : le second triangle rectangle de `LP7` se tente par rotation, sans octet

**Decision** : on tente la **rotation du dessin existant** (`figure-triangle-rectangle`, 1 fichier aujourd'hui), **sans octet neuf**. `theo-archi` est saisi en parallele (l'agent lanceur) pour dire si elle suffit a la condition de `nadia-prof` (*"au moins deux dessins qui different par l'orientation ou les proportions"*, `arbitrage-nadia-palier-4.md` 3.2). **Ce que la reponse ne couvre pas** : un fichier neuf. Si `theo-archi` juge la rotation insuffisante, le *oui* de l'utilisatrice sur le poids (A.146) ne couvre toujours que `LP9` et la question se repose (proposition, section 9, premiere question). **Changement de code decide, avec proprietaire** : la rotation est faite dans `LP7` par `lea-dev` (B), apres l'avis de `theo-archi` sur sa forme ; la story `S57-BR-MAT-CE1-15` porte le critere (annotation de backlog du 2026-10-23, seconde).

### A.183 `D3` : le palier 6 est dans s57, la consequence est ecrite

**Decision** : s57 reste ouvert apres le palier 5 ; **il n'y a pas de s58 a la cloture du palier 5** ; un palier 6 existe dans s57. **La consequence, dite a l'utilisatrice avant sa reponse et maintenue** : aucune ligne `couvert-valide` n'est possible dans s57 sans la levee des regles de `nadia-prof` (`FRA-CE1-08` et `-09`, questionner le monde, arts plastiques ; elle a refuse d'avance de les lever, et l'utilisatrice ne le lui demande pas) ou sans le materiau de `D5`. **Le contenu du palier 6 est donc a composer a partir de `D5` et des corrections ; je ne l'invente pas.** Gain au plus : s57 clos a 43 / 64 = 67,2 % `couvert-valide` pour le CE1 (CM1 83,5 %, CP 88,1 %), compteur de version 46 / 64 apres le palier 5. **Report compte** : les lignes de questionner le monde sont absentes des compositions des paliers 2, 3 et 4 (`grep -c "QLM-CE1"` rend 0 sur `composition-palier-2.md`, `-3.md`, `-4.md`) et du palier 5 ; leur lot est dit *"non auditable a s57"* par `nadia-prof` (`arbitrage-nadia-preparation-palier-3-ce1.md` 1.3, 2026-10-19). Le palier 6 est le cinquieme palier de s57 sans une seule de ces lignes. **C'est une direction choisie, avec ses mots, et non un report silencieux.** **Ce que `D3` ne dit pas** : combien de paliers apres le 5 ni la borne du palier 6. Je n'ecris ni *"un seul"* ni *"autant que necessaire"* : la question se pose a la composition du palier 6, quand l'avis de `theo-archi` et les prix de `nadia-prof` sont ecrits. **Changement de planning** : `planning.md` n'est pas amende ici ; proprietaire `sofia-sm`, avec la composition du palier 5.

### A.184 `D4` : la regle "Lecture avant verify" vaut pour tout paquet ecrit ou reecrit dans un lot

**Decision** : la regle de `LP6` (A.173, ses mots *"Lecture avant verify"*), qui visait **un lot d'A' sur un paquet neuf**, vaut pour **tout paquet ecrit ou reecrit dans un lot**, toutes instances. **`LP7` (`ce1-figures-planes`, reecrit) et `LP9` (`ce1-deplacements-quadrillage`, neuf avec ses dessins) en relevent** : deux lectures de `nadia-prof` au palier 5, en B. Le contenu de la regle : `nadia-prof` lit le premier jet du paquet **avant** le premier `verify` complet ; elle ecrit les items qu'elle refuserait et leur motif, **sans verdict** ; le developpeur les corrige avant son `verify` ; le Done reste prononce sur le commit joue. **Le prix** : la lecture et la correction qui la suit sont **comprises dans le prix du lot** (1,5 pour `LP7` et `LP9`), pas sur la marge de rejeu ; **le prix est a `nadia-prof`**, elle dit si 1,5 tient. Un refus apres le `verify` se facture comme avant. **Ma lecture, a confirmer par `nadia-prof` dans son arbitrage prealable** : `LP8` (paquet ecrit hors sprint, seulement branche par le lot) et les trois stories de reserve d'A' (corrections d'items de paquets du catalogue, relues *"sans rejeu complet"*, A.173) n'en relevent pas ; le premier jet de `LP7` et de `LP9` est le paquet **avec** ses dessins. **A.173 ecrivait** *"Je n'en tire aucune regle pour le palier 5 sans l'avis de `nadia-prof` et l'utilisatrice"* : l'utilisatrice a repondu, **l'avis de `nadia-prof` sur le prix et la portee fine n'est pas ecrit** ; l'ecart est dit, il se ferme dans son arbitrage prealable. **Changement de code** : aucun. **Changement de processus a proprietaire** : `lea-dev` (declencheur : commit du premier jet et ligne de `planning.md`) ; `sofia-sm` (`planning.md`) ; `nadia-prof` (ecrit `lecture-nadia-premier-jet-<paquet>.md`). Criteres de story : annotation de backlog du 2026-10-23, seconde.

### A.185 `D5` : les dessins sont rouverts pour cinq lignes de mathematiques CE1, sous reserve de l'avis de cout de `theo-archi`

**Decision** : `DEC-0123` 3 (`Q-06`) est **rouverte pour cinq lignes** : `MAT-CE1-01` (denombrer), `-10` (balance, recipient), `-11` (horloge), `-02` (ligne graduee), `-09` (regle graduee). **Sous reserve** de l'avis de cout de `theo-archi`, saisi en parallele : **aucun lot de dessins n'est engage avant lui**, aucune story n'est ecrite pour leur moitie image (leurs criteres dependent de la forme de chaque dessin), aucun prix n'est fixe. **Ce que `D5` ne fait pas** : lever un plafond `en cours` (`nadia-prof` seule, ligne par ligne) ; couvrir `QLM-CE1-09`, `QLM-CE1-11` et la moitie dessinee de `QLM-CE1-07`, qui restent hors perimetre ; poser le poids. **Trois faits que la composition du palier 6 devra regler**, aucun n'est decide ici : (1) `PAR-8` et `PAR-2` : les cinq lignes sont de B seule, trois lots au plus et 5,0 ; deux lots illustres coutent 4,00, trois 5,50 au bareme ; (2) **`PAR-13`**, cite par `DEC-0123` 3.2 point 1 (*"une seule ligne a dessin par sprint, et pas tant qu'A' existe"*) : que les cinq lignes y entrent est un jugement de `nadia-prof` ; si elles y entrent, `D3` et `D5` ne tiennent pas ensemble dans s57 au-dela de la limite qu'elle ecrit ; (3) le poids : la borne du *oui* de `DEC-0123` (septieme amendement) est 161 863 ; si l'avis chiffre au-dela, la question se repose, et sa reponse a `D5` ne cite pas le poids. **Changement de code** : **non decide aujourd'hui**. Il n'a pas de proprietaire tant que l'avis n'est pas rendu, et je l'ecris comme tel : pas une decision appliquee. Au retour de l'avis, j'ecris les stories et la route a `sofia-sm` dans le meme geste ; proprietaire attendu : `lea-dev` (B). Aucun dessin de ces lignes n'est au catalogue : aucun enfant ne peut jouer un contenu non valide dans l'intervalle.

### A.186 `QU2` : le depassement de `PAR-8` en A' est consigne comme relevement, valeur non fermee

**Decision** : le depassement de `PAR-8` en A' au palier 4 est **consigne comme relevement du plafond de A' au palier 4**. **Valeur** : 5,50 plus le prix du rejeu 2 de `LP6`, soit 5,75 a 0,25 ou 6,00 a 0,50 (A.176) ; **la valeur finale s'ecrit quand `nadia-prof` a chiffre le rejeu 2** (la ligne est `non-chiffre`, 0,00, dans `registre-prix-palier-4.txt`) : je ne chiffre pas a sa place et je ne modifie pas le registre ici. **Portee** : A', palier 4, ce depassement. Ni B, ni le palier 5, ni le texte de `PAR-8` (5,0) ne sont touches. Au geste suivant (le chiffre de `nadia-prof`) : la ligne du registre passe a `facture`, et un amendement ecrit la valeur. `QU2` est fermee dans son principe, ouverte dans son chiffre.

### A.187 Les autres reponses

| Point | Consignation |
|---|---|
| `QP7` | `LP9`, `LP7`, `LP8` sont **composees au palier 5, sans `DEC` d'ecartement**. C'est la premiere branche de l'alternative d'A.177 (*"soit les conditions sont ecrites avant la composition et le lot est engage, soit une `DEC`"*). **La regle d'A.150 tient** : composer n'est pas engager ; un lot sans ses conditions est un troisieme ecartement de fait. Les conditions sont ecrites en section 6 de la proposition, avec leur proprietaire. La story de `LP8` est ecrite (`S57-BR-FRA-CE1-03`, `e18b13e8`) |
| `DEC-0125` | **Confirmee** : `S57-TYP1-TEXTES-DE-GABARIT` et `S57-MESSAGE-PARTIE-PERDUE` restent retirees. Aucune regle de `DEC-0125` ne change ; sa section 6 (*"A confirmer par l'utilisatrice"*) est remplie. Annotation ajoutee a la `DEC` |
| `QP4` | Reponse de l'utilisatrice consignee : `S57-SAISIE-MOTS-PROPOSES-PAR-LE-CLAVIER` est **fermee** (annotation de backlog de `e18b13e8`). Appareil et constat precis non connus de moi ; a redemander a l'entree de `FRA-CE1-29`. A `theo-archi`, facultatif : une ligne qui dit `QP4` sans objet |
| `T5` | `S57-GARDE-PAQUET-INTERET-COMMUN` est **engagee**, hors `PAR`, prix 0, avant `S57-EMC-CE1-04-R1` : A.174 la disait reportee, elle ne l'est plus. Proprietaire du code : `sofia-sm` ; forme : `theo-archi` ; morsures : `yanis-qa` |
| Confirmation d'`ArchUnit` du palier 4 | `confirmation-archunit-palier-4.md` n'existe pas (`ls` du dossier du sprint rend `-2` et `-3` seulement) ; elle est a `theo-archi`, au plus tard avant la cloture du palier 5 |
| s57 | *"pas encore ferme"* : s57 reste ouvert, voir A.183 |

### A.188 Les regles de `DEC-0123`, de `DEC-0125` et de ce texte que ces reponses changent

1. **`DEC-0123` 3.1 et 3.3 (`Q-06`)** : **`MAT-CE1-02` et `MAT-CE1-09` sortent de la liste des lignes hors perimetre**. Leur condition de retour (*"une nouvelle reponse de l'utilisatrice"*) est remplie par `D5`. `MAT-CE1-01`, `-10`, `-11` n'etaient pas sur cette liste ; `D5` les nomme aussi. `QLM-CE1-09`, `QLM-CE1-11` et la moitie dessinee de `QLM-CE1-07` **restent hors perimetre**. Amendement (huitieme) ajoute a `DEC-0123`. Les motifs de 3.2 (poids, `PAR-13`) ne sont ni leves ni reaffirmes (A.185).
2. **`DEC-0123` septieme amendement, dernier alinea de la deuxieme puce** (*"le second triangle rectangle de `MAT-CE1-15` demande sa propre reponse"*) : repondu par `D2`, sans octet.
3. **`DEC-0125`** : aucune regle ne change ; confirmation seule. Annotation ajoutee.
4. **`DEC-0120` A.150 et A.177** (`QP7`) : une branche choisie, la regle du troisieme ecartement inchangee. **A.173, derniere phrase** (aucune regle pour le palier 5 sans l'avis de `nadia-prof` et de l'utilisatrice) : une regle est consignee (A.184), l'avis de `nadia-prof` reste a ecrire. **A.174** (`T5` reportee) : engagee. **A.176** (`QU2` ouverte) : fermee dans son principe (A.186).

### A.189 La matrice

**Aucun statut ne change** : aucun Done pedagogique n'est prononce depuis A.172. `squad/programme/MATRICE-couverture.md` n'est pas modifiee. Les cellules de `MAT-CE1-01`, `-02`, `-09`, `-10`, `-11` ne citent ni `DEC-0123` ni `Q-06` (`grep` sur ces lignes rend 0) : aucune reference de cellule n'est perimee par `D5`. Les chiffres cites (38 `couvert-valide` sur 64, CE1) sont ceux de la proposition, calcules par la commande de la "Methode du compte" a `f3b06989`, non recomptes dans ce geste.

### A.190 Routage

| A qui | Quoi |
|---|---|
| `theo-archi` | avis sur la rotation du triangle rectangle (`D2`) ; avis de cout des dessins de `D5` (collection a denombrer, balance et recipient, horloge, ligne graduee, regle graduee) et leur poids ; `confirmation-archunit-palier-4.md` ; la forme de `T5` ; facultatif : `QP4` sans objet |
| `nadia-prof` | arbitrage prealable du palier 5 : prix, instance B, deux lots illustres dans un palier (`LP7`, `LP9`) ; prix des deux lectures de `D4` et sa portee fine ; chiffre du rejeu 2 de `LP6` (relevement de `QU2`) ; `PAR-13` et les cinq lignes de `D5` ; levee de plafond ligne par ligne, quand le moment vient |
| `sofia-sm` | composition du palier 5 ; `planning.md` : palier 6 dans s57 (`D3`), ligne de premier jet (`D4`), garde `T5` ; file de fusion |
| `lea-dev` | `LP7` (rotation, paquet), `LP9` (20 dessins, paquet), declencheur de la lecture avant `verify` |
| `camille-po` | stories des cinq lignes de `D5` apres l'avis ; relevement chiffre de `QU2` apres `nadia-prof` ; story des commentaires de `src/` (0 titre au backlog, report 1 a ma charge, A.177) |
| l'utilisatrice | **aucune question aujourd'hui.** Trois questions a declencheur ecrit : un fichier neuf pour `LP7` (si la rotation ne suffit pas) ; le poids des dessins de `D5` (si l'avis depasse 161 863) ; le nombre de paliers de s57 apres le 5 et la borne du palier 6 (a la composition du palier 6) |

**Ne fait pas** : ne prononce aucun Done, ne juge aucun contenu, ne fixe aucun prix, n'engage aucun poste ni lot, n'amende ni `planning.md`, ni `domain/`, `application/`, `adapters/`, `bootstrap/`, ni paquet, ni catalogue, ni matrice, ni registre de prix. **Decisions de produit prises ici** : (1) borne du palier 5 a 20,0 ; (2) rotation sans octet pour `LP7` ; (3) palier 6 dans s57, pas de s58 a la cloture du palier 5, contenu non invente ; (4) regle "Lecture avant verify" etendue a tout paquet ecrit ou reecrit dans un lot ; (5) dessins rouverts pour cinq lignes, sous reserve de l'avis de `theo-archi`, sans engagement ; (6) `QU2` consigne comme relevement de `PAR-8` en A', valeur a fermer ; (7) `QP7`, `DEC-0125`, `QP4`, `T5` consignes. **Changements de code decides, avec proprietaire** : la rotation de `LP7` (`lea-dev`) ; la garde de `T5` (`sofia-sm`). **Changement de code non decide, dit** : les dessins de `D5`. **Aucun identifiant de condition ou de reserve n'est alloue** : `REGISTRE-identifiants.md` n'est pas modifie.

## Amendement du 2026-10-23, trentieme (`camille-po`, palier 5 : second fichier SVG de `LP7`, cadence de `D5`, fusion sans poussee, avis de `theo-archi` sur les dessins)

Annotation datee. **Rien au-dessus n'est reecrit** ; ce qui corrige A.182, A.185 et A.190 est dit ci-dessous. **Statut de `DEC-0120` : `Acceptee`, inchange.** Arbre : `round-two` a `f3b06989`. Source technique : `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/avis-theo-palier-5-dessins.md` (branche `s57-theo-p5-dessins`, commit `1c7eb423`, **non fusionne** dans la branche de cet amendement). Ce que je n'ai pas fait : fusionner (a `sofia-sm`), pousser, ecrire un nouveau texte de `PAR-13` (a `nadia-prof`), fixer un prix, engager un lot, ecrire une story d'image, modifier la matrice ou le registre.

### A.191 Les mots, et ce qu'ils m'autorisent

Cites tels que l'agent lanceur me les transmet, 2026-10-23, forme 2 de `DEC-0062` G (non lus de sa main).

| Point | Ses mots | Consigne en |
|---|---|---|
| `LP7`, second fichier (question 1 de l'avis) | "Oui, second fichier SVG" | A.192 |
| `D5`, cadence | "Deux lots par palier" | A.193, A.194 |
| Fusion | "Fusionner sans pousser" | A.196 |

L'avis de `theo-archi` est lu sur la branche `s57-theo-p5-dessins`, dans un autre worktree : je le cite, je ne l'ai pas fusionne. Ses octets par dessin et N = 10 sont ses suppositions ; ses mesures (56 fichiers, 69 203 octets, triangle de 513 octets) sont a `f3b06989`.

### A.192 `LP7` : un second fichier SVG, qui corrige A.182

**A.182 supposait que la rotation se fait sans octet. L'avis dit que non** : une rotation sans fichier neuf exige du code (une cle d'item lue par la fabrique, un champ de `domain`, un attribut de modele, une classe de gabarit, une regle de feuille de style, un amendement d'`ADR-0073` : taille M a L). La forme sans code est **un second fichier dont la rotation est dans le dessin** (precedent : `figure-carre-incline.svg`), environ 500 octets. **Reponse** : *"Oui, second fichier SVG"*. **Ce qu'elle couvre** : le poids de ce fichier, `POIDS_TOTAL_MAXIMUM` de 69 203 a environ 69 800 ; **elle ne couvre que lui** (A.146 point 3 tient pour tout autre fichier). La valeur commitee est celle que le commit de `LP7` mesure, pas l'estimation. **Ce qui reste a `nadia-prof`** : (i) une copie tournee, aux memes proportions, tient-elle sa condition (*"au moins deux dessins qui different par l'orientation ou les proportions"*) ; (ii) l'angle : 90, 180 ou 270 degres gardent les sommets dans le cadre, 45 degres en sort, et aucun garde ne verifie le debordement ; (iii) d'autres proportions au meme cout, si elle les prefere ; (iv) le test facultatif qui compare les deux dessins (angle droit par produit scalaire nul apres le `transform`, taille M, petite). **Le fichier ne s'ecrit pas avant sa reponse sur (i) a (iii)** ; je suppose que son cout ne change pas selon l'angle ou les proportions (avis 1.3). **Changement de code decide, avec proprietaire** : un fichier sous `static/illustrations`, dans le lot `LP7`, `lea-dev` ; constantes qui bougent dans le commit : `POIDS_TOTAL_MAXIMUM`, `FICHIERS_D_ILLUSTRATION` de 56 a 57 avant `LP9`, `ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION`. Ordre : `LP9` fusionne d'abord, `LP7` recalcule le cliquet sur le sommet fusionne (A.149). **Fin de la premiere question de l'utilisatrice d'A.190** (un fichier neuf pour `LP7`).

### A.193 `D5` : deux lots illustres par palier en B, trois paliers

**Reponse** : *"Deux lots par palier"*. **Consequence ecrite** : les cinq lignes (`MAT-CE1-01`, `-11`, `-09`, `-10`, `-02`, dans l'ordre technique de `theo-archi` : `LP9` et `LP7` au palier 5, puis ces cinq) vont a **deux lots illustres par palier en B** (`PAR-8` : trois lots au plus et 5,0 par instance ; `PAR-2` : B seule), soit **2 + 2 + 1, trois paliers**. **Prix** : 10,5 au bareme (7,5 de lots plus 3,0 de fixe) ; 12,4 au surcout mesure de B (0,375 par lot) ; par palier 4,00 / 4,00 / 2,50 au bareme et 4,75 / 4,75 / 2,88 avec surcout, chacun sous 5,0. Chiffres de `theo-archi` (avis 2.3), le bareme est a `nadia-prof`. **Ma lecture, a confirmer** : (i) *"trois paliers"* et *"on reste dans s57"* (`D3`) font aller s57 jusqu'au palier 8 ; elle ne l'a pas ecrit ainsi, et je ne le tiens pas pour sa phrase ; (ii) l'appariement des lignes aux paliers (01 et 11 ; 09 et 10 ; 02) suit l'ordre technique ; **l'ordre de produit est a `camille-po` et `nadia-prof`, l'ordre de fusion a `sofia-sm`**. **Gain** : au plus 40, 42, 43 sur 64 apres les paliers 6, 7, 8 (62,5 %, 65,6 %, 67,2 %) ; s57 clos au plus a 43 / 64, comme A.183, si `nadia-prof` leve chaque plafond. **Tout cela est hypothetique tant que le poids n'est pas reaccorde** (A.195) : aucun palier n'est compose, aucun lot engage, aucune story d'image ecrite. **Corrige A.185**, fait (1) : *"cinq lignes ne tiennent pas dans un palier"* est repondu par trois paliers, sans depassement de `PAR-8`. **Corrige A.190** : la troisieme question de l'utilisatrice (nombre de paliers) est repondue par la cadence, sous ma lecture (i) ; **la borne des paliers 6 a 8 reste non posee**.

### A.194 `PAR-13` : ce que la reponse change, et ce qu'elle ne dit pas

`DEC-0123` 3.2 point 1 cite `PAR-13` ainsi : *"une seule ligne a dessin par sprint, et pas tant qu'A' existe"*. **Deux lots par palier en B pour cinq lignes ne tient pas avec cette lecture** (cinq lignes en cinq sprints, ou une par palier). **Consequence de sa reponse** : elle choisit la cadence de deux lots par palier, donc **le sens de `PAR-13` change pour ces cinq lignes** : il n'est plus *"une ligne a dessin par sprint"*. **Ce qui n'est pas clair, a confirmer par `nadia-prof` (la regle est la sienne ; sa decision pour l'utilisatrice est la cadence, pas le texte de la regle)** : (i) le texte neuf de `PAR-13` : *"deux lots illustres par palier en B"* est ma formule de consequence, **pas un texte que j'ecris dans la regle** ; (ii) la clause *"pas tant qu'A' existe"* : la reponse n'en parle pas ; A' existe au palier 5, je ne sais pas aux paliers 6 a 8 ; (iii) le *"un seul"* lot illustre par palier (`arbitrage-nadia-palier-4.md` 8.4, ecrit pour le palier 4) : il ne tient plus en B, `LP9` et `LP7` etant deux lots illustres au palier 5. **`DEC-0123` est amendee** (neuvieme) : la phrase est citee, non reecrite.

### A.195 Le poids des cinq lignes reste hors de son oui

**Reponse (b), dans les mots relayes** : le poids total des cinq lignes, **87 000 a 150 000 octets estimes (N = 10 dessins par ligne, suppose par `theo-archi`)**, **reste hors de son oui borne a 161 863** (`DEC-0123`, septieme amendement). Repertoire estime avec `LP9` et `LP7` : 202 000 a 281 000 octets. **La question du poids se repose a l'ouverture de chaque lot de `D5`**, avec la valeur que mesure le lot et la somme deja installee. **Declencheur ecrit** : l'ouverture d'un lot de `D5`. **A.185, fait (3)** : precise, jamais leve. `LP9` garde son oui (A.146), `LP7` le sien (A.192).

### A.196 Fusion sans poussee

*"Fusionner sans pousser"* : la fusion des branches du palier est autorisee, **la poussee ne l'est pas**. Je ne fusionne rien : la fusion est a `sofia-sm` (`RF11`, A.152 pour la poussee, qui reste hors de portee tant qu'elle n'est pas autorisee). Les branches concernees a ma connaissance : `s57-camille-p5-proposition` et `s57-theo-p5-dessins`. Aucune ne contient l'autre ; l'avis de `theo-archi` est cite dans cet amendement sans exister sur ma branche, et une reference a un fichier absent de l'arbre tient jusqu'a la fusion.

### A.197 Matrice, routage, ce qui reste a confirmer

**Matrice** : aucun statut ne change, `MATRICE-couverture.md` n'est pas modifiee.

| A qui | Quoi |
|---|---|
| `nadia-prof` | angle ou proportions du second triangle, test facultatif (A.192) ; texte et portee de `PAR-13`, clause *"pas tant qu'A' existe"*, *"un seul"* lot illustre par palier (A.194) ; par ligne de `D5` : nombre d'items illustres, forme de `MAT-CE1-10` (balance ou recipient), acceptation de *"placer"* par designation pour `MAT-CE1-02`, condition qui affirme ce que chaque dessin montre ; bareme de 1,5 par lot de `D5` ; levee de plafond ligne par ligne |
| `sofia-sm` | fusion sans poussee (A.196) ; `planning.md` : paliers 6 a 8 hypothetiques, cadence, ligne de premier jet |
| `lea-dev` | `LP7` : le fichier, apres la reponse de `nadia-prof` ; recalcul du cliquet apres `LP9` |
| `ines-design` | glyphes 7, 8 et 9, marque de zone, mesure d'un cadran et d'une regle a 176 px (question 7 de l'avis) |
| `camille-po` | stories des lignes de `D5` a l'ouverture de chaque lot |
| l'utilisatrice | **aucune question aujourd'hui.** Deux declencheurs : le poids, a l'ouverture de chaque lot de `D5` ; la borne des paliers 6 a 8, a la composition du palier 6, et le nombre de paliers si elle l'entendait autrement (A.193 i) |

**Ne fait pas** : ne prononce aucun Done, ne fixe aucun prix, n'engage aucun poste ni lot, n'amende ni `planning.md`, ni `domain/`, `application/`, `adapters/`, `bootstrap/`, ni paquet, ni catalogue, ni matrice, ni registre. **Decisions de produit prises ici** : (1) `LP7` ajoute un second fichier SVG, poids couvert pour lui seul ; (2) `D5` : deux lots illustres par palier en B, trois paliers hypothetiques ; (3) le sens de `PAR-13` change pour ces lignes, son texte est a `nadia-prof` ; (4) le poids des cinq lignes reste a reaccorder lot par lot ; (5) fusion autorisee, poussee non. **Changement de code decide, avec proprietaire** : le fichier de `LP7` (`lea-dev`). **Non decide** : les dessins de `D5`. Aucun identifiant de condition alloue.
