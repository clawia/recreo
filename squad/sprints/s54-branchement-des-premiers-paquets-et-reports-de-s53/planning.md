# [Sprint s54] Planning - 2026-10-07

- **Horodatage de debut** : 2026-10-07 20:27 (UTC+02:00), lu par `date` au moment de l'ecriture. Premiere lecture de `date` de
  la tache : 20:19.
- **Anime par** : sofia-sm (casquette Scrum Master ; aucune story de developpement portee par moi dans ce fichier. La story du
  journal de tokens m'est affectee sous ma casquette developpeuse, dans une tache separee qui annonce la casquette)
- **Participants** : aucun agent invoque pour ce planning, qui constate l'etat du depot, mesure, decoupe et affecte.
  **Sources lues, par auteur** : camille-po (`DEC-0112`, `DEC-0113` dont la section 9, backlog "Reports de s53", `MATRICE-couverture.md`) ;
  nadia-prof (`ARBITRAGE-PREALABLE-s53-points-de-relecture-p1-a-p11.md`, `ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`
  section 13 pour `PAR-1`, `PAR-2`, `PAR-4`, `PAR-7`, `PAR-8` ; `ARBITRAGE-PREALABLE-s30-...quinze-reserves.md` pour `PAR-9`, `PAR-10` ;
  `ARBITRAGE-PREALABLE-s32-...md` pour `PAR-13` ; `ARBITRAGE-PREALABLE-s33-...md` pour `PAR-14` ; `ARBITRAGE-PREALABLE-s52-instances-ce1.md`
  sections 4 a 6 ; `ARBITRAGE-PREALABLE-s52-fra-ce1-questions-de-l-avis.md` section 4.3 ; `ARBITRAGE-PREALABLE-s48-francais-ce1.md` section 12) ;
  theo-archi (`confirmation-archunit.md` de s53 section 5) ; relecteur-pedagogique (`relecture-cloture.md` de s53) ; sofia-sm (`planning.md`,
  `review.md`, `retro.md`, `RAPPORT.md` de s53 et `planning.md`, `review.md`, `RAPPORT.md` de s52). Les 45 paquets ont ete lus par moi en-tete
  par en-tete, par script de lecture seule, pas relus item par item.
- **Slug** : `s54-branchement-des-premiers-paquets-et-reports-de-s53`. Le dossier n'existait pas ; il est cree par ce planning.
- **Lignee verifiee avant ecriture** : branche `s54-sofia-planning`, `git log -1 --format=%h` rend `52e2b0cd` (tip de `round-two`),
  `bash outils/verifier-lignee.sh` sort 0, `git merge-base --is-ancestor round-two HEAD` reussit, `git status --short` est vide. Le
  travail est ecrit dans un worktree dedie.
- **Etat de la reference distante** : `git rev-parse --short origin/round-two` rend `52e2b0cd`, soit le tip local. La reference
  locale dit que s53 est pousse ; **aucun verdict de CI n'est lu** (`gh run list --branch round-two` rend `HTTP 404` depuis ce poste).
  Le verdict du tronc reste a lire par qui a acces a la CI (question Q9).

## Objectif du sprint

Gele : **brancher cinq paquets de la session de contenu du 2026-10-07 au catalogue et les presenter a `nadia-prof` (deux
francais CE1, deux sciences CM1, un EMC CM1), solder les quatre reports de s53 qui touchent un jeu servi ou la squad (`CRP-CM1-R1`
a `R3`, `S53-PASSE-TRANSVERSE`, `S51-OUTIL-JOURNAL-TOKENS`, `S53-REPORT-GRANDS-NOMBRES`), traiter les cinq corrections de redaction
de s53, et ecrire pour `ART-CP-10` le prix et le plafond qui permettent a l'utilisatrice de la financer ou non.** Les 40 autres paquets ne
sont pas engages : section "Ce qui est reporte". Au mieux cinq lignes de matrice ferment (`FRA-CE1-18`, `FRA-CE1-19`, `SCI-CM1-15`, `SCI-CM1-18`,
`EMC-CM1-02`), soit **132/182 (72,53 %) a 137/182 (75,27 %)**. Ce n'est pas une promesse : un Done pedagogique refuse reste un refus.

## Provenance, et ce qui manque a la cloture de ce planning

- **Le perimetre vient d'un message de l'agent qui m'a lance**, qui le donne comme arbitrage de l'utilisatrice du 2026-10-07. **Je n'ai pas
  ses mots** : ce message est de la forme 3 de `squad/README.md` section 2 bis (recu d'un agent, sans source dans le depot), il fonde l'ordre
  de planifier, pas un motif. Les mots de l'utilisatrice que le depot porte sont ceux de `DEC-0113` section 9 : *"remets les 3 lignes à s54
  si Aucune des trois lignes n'est une impossibilité"* (forme 2, citation figee par `camille-po`). Le reste du perimetre (branchement,
  reports de s53) n'a **aucune source ecrite de l'utilisatrice dans le depot**.
- **Aucune `DEC` d'ouverture de s54 n'existe** : `ls squad/decisions/produit` s'arrete a `DEC-0113` (`Refusee`). `DEC-0112` section 5.5 interdit de
  reporter en silence les trois postes non livres ; `DEC-0113` section 9.3 dit que la `DEC` d'ouverture de s54 tranche les trois lignes.
  Je planifie ce que le message demande et je ne fabrique pas la `DEC` : elle est a `camille-po` (Q1). **Lecon de la retro de s53** : un engagement
  ajoute par une `DEC` apres ce planning s'ecrit dans un amendement de ce fichier, avec affectation nominative, avant l'ouverture du
  premier worktree concerne.
- **Aucun avis de faisabilite de `theo-archi` n'existe pour s54**, et **aucun arbitrage prealable de `nadia-prof`** (`ls squad/conformite |
  grep -c s54` rend 0). La section "Avis de faisabilite" dit ce que chacun doit rendre. Conformement a l'amendement de s48, le planning
  affecte sans attendre l'avis ; `review.md` dira comment l'avis l'a rattrape.
- **`lessons.md`** : l'instruction d'organisation de ce poste parle d'un `lessons.md`. Il n'existe pas dans le depot (`find . -iname lessons.md`
  ne rend rien) ; la memoire des corrections est `retro.md` et `squad/README.md`. Rien n'est ecrit hors du depot.

## Le cout reel d'un branchement, mesure

**Reference : la livraison d'un paquet neuf en s52, commit `bd56d39b` (`[MARC]`, `ce1-categories`).** `git show --stat bd56d39b` : six
fichiers, 595 lignes ajoutees : `catalogue.yaml` +6, le paquet 175, `PaquetCe1CategoriesTest` 374, une fixture de test fautive 19,
`TexteAccentueExposeParLePortEntrantTest` +13, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` +8. Ensuite, par `nadia-prof` : un audit
joue, une fiche (`FICHE-bandit-manchot-categories-ce1.md`, 107 lignes ajoutees au premier passage), un verdict ; par `camille-po` : la matrice.

| Poste d'un branchement | Deja fait pour les 45 (mesure) | Reste a faire par paquet branche |
|---|---|---|
| Fichier de paquet | oui, 45 fichiers, 784 items (somme de `- enonce:`) | rien, sauf correction decidee par `nadia-prof` |
| Declaration dans les garde-fous d'`adapter-content` | oui, `70b1cdc1` (4 tests, 201 lignes) et s53 | rien |
| Entree de `catalogue.yaml` (id, titre, consigne, mecanique, paquet, manches) | **non** : 0 sur 45 (`grep -c "paquet: <nom>$"`) | 6 lignes ; titre et consigne avec espace insecable devant `? ! : ;` |
| Test du paquet (`Paquet<Nom>Test`) | **non** : le nom du paquet entre guillemets n'apparait que dans deux tests de garde-fou (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` pour 41, `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` pour 4), `grep -rlF` sur les quatre modules | un test par paquet : **mediane 341 lignes sur les 22 tests `PaquetCe1*`, 205 sur les 122 `Paquet*Test`** (`wc -l`). Obligatoire ou non : question T1 |
| Inventaire des chaines accentuees (`TexteAccentueExposeParLePortEntrantTest`, `bootstrap`) | non | environ 13 lignes (s52) |
| Audit joue, fiche, verdict | non, aucun des 45 n'a de verdict | `nadia-prof` : 1,0 par paquet (tarif du depot), 0,5 le rejeu, 0,25 la correction mot pour mot (`PAR-10`) ; **tarif propre a ces paquets non ecrit** (Q3) |
| Ligne de matrice, mention de couverture (`MNC-1`) | non | `camille-po`, `nadia-prof` |

**Ce que l'ecriture a deja paye et ce qu'elle ne paye pas.** `nadia-prof` l'a ecrit : *"Les corrections decidees ici le rendent moins long, pas
moins cher"* (arbitrage de s53 section 14). Le travail d'audit est son unite, il ne baisse pas parce que le paquet existe deja. Ce que le depot
n'a pas : **un tarif de developpement** (`DEC-0112` section 7). Je donne des tailles : un branchement coute environ 590 lignes en six fichiers, dont
les deux tiers pour le test du paquet.

**Cadence mesuree a s52** (`git log --format='%ad %h %s'`, 2026-10-06 : paquet `ce1-categories` a 21:45, verdicts de l'instance A a 22:12,
22:39) : les trois paquets de l'instance A sont livres et audites en environ une heure d'horloge. **Le plafond d'un sprint n'est pas le temps
d'un developpeur, c'est la regle de capacite de `nadia-prof`**, ce qui commande tout le decoupage ci-dessous.

**Taux de refus au premier passage, tels que le depot les porte** : s52, 1 refus sur 6 lots (`FRA-CE1-17`, rejoue et leve, `review.md` de s52 section 6) ;
s51, instance B, 2 refus sur 3 lots neufs (`ARBITRAGE-PREALABLE-s52-fra-ce1-questions-de-l-avis.md` section 4.3). Un refus coute 0,5 de rejeu :
une instance en absorbe un dans sa marge.

## Les 45 paquets, par matiere et par groupe

Source : `grep -m1 '^lignesDeReferentiel'` et `grep -c '^# DEGRADE'` sur chaque fichier de `contenu/` ajoute de `9d44d510` a `76a50e8b` (45 fichiers,
`git log --diff-filter=A --name-only`), auteurs lus par `git log --diff-filter=A --format=%s -- <fichier>` (24 `[LEA]`, 21 `[MARC]`).
**DEGRADE** = commentaire `# DEGRADE` en tete du YAML (le paquet ne sert qu'une partie du libelle de la ligne). **25 sont DEGRADE, 20 ne le sont pas.**
Le marqueur est un commentaire que rien ne lit (`grep -rn DEGRADE adapters/adapter-content/src/test/java` ne rend rien) : le compte est une lecture de fichier.

| Groupe | Paquets | Items | DEGRADE | Lignes (plafond atteignable) | Touches par s53 | Auteur |
|---|---|---|---|---|---|---|
| **G1** francais CE1, ligne fermable | 13 : `accord-groupe-nominal`, `accord-sujet-verbe`, `classes-de-mots`, `familles-de-mots`, `futur`, `inference`, `negation`, `passe-compose`, `phrases-types`, `pronoms`, `texte-documentaire`, `texte-narratif`, `verbe-sujet` | 296 | 0 | `FRA-CE1-04` a `-07`, `-10` a `-15`, `-18`, `-19`, `-24` : `couvert-valide` (arbitrage de s48 section 12) | `accord-groupe-nominal` (P1), `pronoms` (P2), `texte-documentaire` (P8) | 9 `[MARC]`, 4 `[LEA]` |
| **G2** francais CE1, DEGRADE, fermable | 2 : `ordre-des-mots` (`-08`), `relire-une-phrase` (`-09`) | 40 | 2 | `couvert-valide` (meme tableau) ; le DEGRADE dit que l'enfant choisit au lieu de remettre en ordre ou de corriger | non | `[LEA]` |
| **G3** francais CE1, plafonne `en cours` | 2 : `mots-irreguliers` (`-03`), `mots-invariables` (`-21`) | 44 | 2 | `en cours` au plus (s48 section 12) | `mots-invariables` (item 7) | 1 `[MARC]`, 1 `[LEA]` |
| **G4** mathematiques CE1 | 5 : `nombres-jusqu-a-1000`, `masses-contenances`, `calendrier-dates`, `figures-planes`, `deplacements-quadrillage` | 108 | 5 | **aucune ne ferme** : `MAT-CE1-01`, `-10`, `-11`, `-15` `en cours` au plus ; `MAT-CE1-14` hors capacite (A3 d'`ADR-0080`, `DEC-0112` 5.3) (`ARBITRAGE-PREALABLE-s52-instances-ce1.md` section 4.2) | `masses-contenances` (P6) | `[MARC]` |
| **G5** EMC, CM1 et CP | 4 : `cm1-emc-atteinte-autrui` (`EMC-CM1-02`), `cm1-emc-respect-differences` (`-03`), `cm1-emc-principes-republique` (`-06`), `cp-emc-personnes-differentes` (`EMC-CP-04`) | 44 | 0 | `couvert-valide` pour les trois CM1 sous `EMC-3`, `EMC-4`, `EMC-5`, `EMC-9`, `BIA-1`, `BIA-2`, `BIA-5` ; **`EMC-CP-04` : non retenue a s31 (`DEC-0081` section 7), `nadia-prof` ecrit que le paquet *"reste sous le refus"* (arbitrage de s53 12.1)** | `principes` (P3, P4, six indices), `personnes-differentes` (reecriture) | `[MARC]` |
| **G6** sciences CM1 | 2 : `terre-jour-nuit-saisons` (`SCI-CM1-15`), `environnement-activite-humaine` (`SCI-CM1-18`) | 24 | 0 | `couvert-valide` ; **reprises corrigees** des deux paquets refuses a s51 (en-tete de chaque fichier) | non | `[LEA]` |
| **G7** anglais CM1 | 2 : `lire-texte` (`LVE-CM1-05`), `ecrire-mots` (`LVE-CM1-06`, DEGRADE : choix, pas de saisie) | 24 | 1 | `LVE-CM1-05` : second paquet lexical exige par `nadia-prof` (matrice, cellule) ; `-06` : saisie sous `ANG-S1`, `ANG-S2` | non | `[MARC]` |
| **G8** histoire des arts CM1 et arts CM1 | 7 : `hda-decrire-lexique`, `-caracteristiques-techniques`, `-materiaux-mise-en-forme`, `-epoque-civilisation`, `-reperes-patrimoine`, `-musee-reperes-regles`, `arts-decrire-une-oeuvre-lexique` | 86 | 7 | **non ecrit pour un paquet texte seul.** La matrice dit *"palier 1 illustre, image d'oeuvre requise (`DEC-0075`)"* et *"un item textuel qui decrirait l'oeuvre dans son enonce serait une contrefacon (`ART-4`, `CON-1`)"* ; `HDA-CM1-09` : palier 2 | `epoque-civilisation` (P7), les trois fichiers de P11 | `[LEA]` |
| **G9** histoire-geographie CM1 | 2 : `carte-et-planisphere` (`HG-CM1-09`), `echelles-du-lieu-de-vie` (`-10`) | 28 | 2 | **non ecrit.** La matrice : *"un quiz de noms ne sert pas la competence"* ; le materiau (dessin de carte) manque | non | `[LEA]` |
| **G10** questionner le monde CP | 3 : `vocabulaire-de-position` (`QLM-CP-10`), `situer-la-france` (`-12`), `modes-de-vie` (`-16`) | 38 | 3 | **non ecrit.** `-10` exige le palier 1 illustre, 2,0 et non 1,5 ; `-12` le materiau de carte ; `-16` `BIA-1`, `BIA-2` | les trois (P5, P10, base rouge) | `[LEA]` |
| **G11** autres | 3 : `cp-assemblage-syllabique` (`FRA-CP-05`), `cm1-lire-et-restituer` (`FRA-CM1-01`), `cp-arts-oeuvres-familles` (`ART-CP-10`) | 52 | 3 | **non ecrit.** `cp-arts-oeuvres-familles` dit dans son en-tete : *"Ce paquet ne ferme pas la ligne"* | non | `[LEA]` |
| **Total** | **45** | **784** | **25** | | **11 fichiers touches** (`git diff --name-only 76a50e8b HEAD -- contenu`) | 24 `[LEA]`, 21 `[MARC]` |

Les comptes de la colonne "Paquets" somment a 45 et ceux d'"Items" a 784 (`python` de lecture seule sur les en-tetes). Les auteurs sont ceux de `git log --diff-filter=A` sur chaque fichier : 21 `[MARC]` (G1 9, G3 1, G4 5, G5 4, G7 2) et 24 `[LEA]` (G1 4, G2 2, G3 1, G6 2, G8 7, G9 2, G10 3, G11 3).

**Paquets que `nadia-prof` a deja corriges ou ecartes a s53.** *Corriges* (onze fichiers modifies par s53, arbitrage de `nadia-prof` et reecritures de la
base rouge) : `ce1-accord-groupe-nominal` (P1), `ce1-pronoms` (P2, indices des items 10 et 13), `cm1-emc-principes-republique` (P3, P4 : six indices sur onze),
`cp-qlm-vocabulaire-de-position` (P5, items 12 a 14), `ce1-masses-contenances` (P6, deux items), `cm1-hda-epoque-civilisation` (P7 confirme, une ligne),
`ce1-texte-documentaire` (P8, item 16), `cp-qlm-situer-la-france` (P10, item 12), `cp-emc-personnes-differentes`, `cp-qlm-modes-de-vie`, `ce1-mots-invariables`
(base rouge, section 12). *Acceptes sans changement* : P9 (`atteinte-autrui`, `respect-differences`, `principes` : aucune ligne `DEGRADE`), P11 (espace
avant `:` dans `competence`). ***Ecartes : aucun des 45 n'a ete retire par s53*** (l'issue "retirer" n'est employee par aucun point de l'arbitrage).
Les deux paquets *ecartes* sont ceux de s51, `cm1-sciences-terre` et `cm1-sciences-activite-humaine` (retires du catalogue par `DEC-0111` section 3) : la
session a ecrit **deux fichiers distincts** pour leurs lignes, ce sont les deux de G6. Aucun des 45 n'a de verdict : tous passent en premier passage complet
au branchement, et `nadia-prof` reste *"la seule garde"* de six points qu'elle ne juge qu'en jouant (arbitrage de s53 section 13).

## Capacite : ce que les plafonds de `nadia-prof` permettent en un sprint

Regles appliquees, chacune verifiee dans son texte (sections citees en tete de ce fichier) :

| Regle | Ce qu'elle dit | Effet ici |
|---|---|---|
| `PAR-1` | un lot n'est jamais partage entre deux instances | un paquet = un lot = une instance |
| `PAR-2` | une matiere et un niveau ne sont jamais partages (EMC CP et EMC CM1 comptent pour une matiere) | **les 17 paquets de francais CE1 tiennent sur une seule instance** ; `nadia-prof` a refuse d'amender `PAR-2` pour le francais CE1 le 2026-10-06 (s52 section 5), en nommant ce qui la ferait reexaminer |
| `PAR-7` | une instance porte l'accumulation et la passe transverse | l'instance A (francais CE1, matiere auditee a s52) porte `PAR-4` ; aucune continuite d'instance n'est supposee |
| `PAR-8` | plafond dur 5,0 par instance, **au plus trois lots par instance**, marge de rejeu 0,5 nommee | **au plus six lots par sprint a deux instances** |
| `PAR-9` | a partir de s53, une `DEC` qui ouvre deux instances compte **4,0 par instance, marge de rejeu comprise**, ou ecrit l'ecart et son motif | 8,0 au total ; plafond de planification 4,0 par instance, pas 5,0 |
| `PAR-10` | un rejeu se paie par paquet : 0,5 ; correction mot pour mot 0,25 ; une correction n'est pas un lot (`DEC-0107` section 4) | `CRP`, `SYC`, `MUL` a 0,5, hors du compte de trois lots |
| `PAR-13` | au plus une instance par sprint prend un terrain dont les conditions n'ont jamais rencontre de contenu | `nadia-prof` lit le francais CE1 comme un seul terrain, dont `RCT`, `PRN`, `INF`, `DOC`, `OMP`, `RLP`, `FAM` n'ont jamais rencontre de contenu (s52 section 5, motif 2). `FUT-CE1-*` et `PCO-CE1-*` n'y figurent pas et sont les cousines de `PRS-CE1-*`, validee a s52 : **a lire par elle (Q3)**. L'instance B porte du connu |
| `PAR-14` | une reprise dont les corrections touchent plus de la moitie des items se paie au tarif d'un lot | les deux reprises de sciences : lecture en Q4 |

**Consequence chiffree, et correction du chiffre de s53.** Le planning de s53 disait *"au moins six sprints a 8,0"* pour 45 paquets. **C'est trop bas** :
`PAR-8` plafonne a trois lots par instance, donc **six lots par sprint au plus**, et 45 paquets (44,5 avec une reprise a 0,5) demandent **au moins 8 sprints**
(45 / 6 = 7,5). Chaque sprint porte aussi des charges qui ne sont pas des lots (`PAR-4`, reserves, rejeux) : **9 sprints est la lecture realiste de la borne basse**,
sans refus au-dela de la marge et au tarif de 1,0 par paquet. **Le chemin critique est le francais CE1** : 17 paquets, une instance, trois lots au plus par sprint,
soit **6 sprints au moins** pour la seule matiere (5,67), dont 15 ferment une ligne. Aucun developpeur ne limite ce chemin ; `PAR-2` et `PAR-8` le font.

## Ce qui est engage

### Stories retenues

Competence visee entre parentheses ; "transverse" pour les corrections. Tarifs de `nadia-prof` au bareme du depot, **proposes et a confirmer par elle (Q3, Q4)**.

| Story | Contenu | Competence programme visee | Decision liee | Dev (affectee a) | Instance de `nadia-prof`, tarif |
|---|---|---|---|---|---|
| `S51-SCI-CORPS-CM1-RESERVES` (`CRP-CM1-R1` a `R3`) | **Premiere a fusionner.** Cinq items sur douze de `cm1-sciences-corps` (3, 5, 6, 7, 11) : l'indice de l'item 3 est faux et le jeu est servi (`grep -c "corps-cm1" catalogue.yaml` rend 1). 5/12 = 41,7 %, sous la moitie | sciences / CM1 / `SCI-CM1-10`, ligne `couvert-valide`, non rouverte | `DEC-0112` 5.2 ; arbitrage de s30 `PAR-10` | marc-dev (auteur, `d88268d5`) | B, rejeu 0,5 |
| `S54-BR-FRA-CE1-18` | Brancher `ce1-futur` (24 items) | francais / CE1 / `FRA-CE1-18` | a ecrire (Q1) | marc-dev (auteur) | A, lot 1,0 |
| `S54-BR-FRA-CE1-19` | Brancher `ce1-passe-compose` (24 items) | francais / CE1 / `FRA-CE1-19` | idem | marc-dev (auteur) | A, lot 1,0 |
| `S54-BR-SCI-CM1-15` | Brancher `cm1-sciences-terre-jour-nuit-saisons` (12 items), reprise du paquet refuse a s51 | sciences / CM1 / `SCI-CM1-15` | `DEC-0113` section 9 | lea-dev (auteure) | B, 1,0 (tarif de `S52-SCI-TERRE-CM1-REPRISE`, `DEC-0112` 5.1) |
| `S54-BR-SCI-CM1-18` | Brancher `cm1-sciences-environnement-activite-humaine` (12 items), reprise du paquet refuse deux fois a s51 | sciences / CM1 / `SCI-CM1-18` | idem | lea-dev (auteure) | B, 0,5 (tarif de `S52-SCI-ACTIVITE-HUMAINE-CM1-REPRISE`) |
| `S54-BR-EMC-CM1-02` | Brancher `cm1-emc-atteinte-autrui` (11 items). **Un seul paquet d'EMC : propose le moins touche** (accepte sans changement a s53, P9). `principes` reste apres, sous la garde de l'ecran | EMC / CM1 / `EMC-CM1-02` | a ecrire | lea-dev (non auteure ; voir justification 3) | B, lot 1,0 |
| `S52-SYC-CE1-R1` (reserve `SYC-CE1-R1`) et item 16 de `FRA-CE1-25` | **Proposition : engager**, 0,5 pour les deux (re-audit ensemble, `retro.md` de s52 A3). Deux items (19, 23) et un enonce | francais / CE1 / `FRA-CE1-25`, `couvert-valide`, non rouverte | `DEC-0111` A.13 ; **second report, un troisieme est interdit** | marc-dev (auteur, `46e69b13`) | A, 0,5 |
| `MUL-CE1-R1` | **Proposition : retirer** comme poste de sprint (voir "Seconds reports") | mathematiques / CE1 / `MAT-CE1-06` | `DEC-0111` A.13 | - | - |
| `S53-PASSE-TRANSVERSE` (`PAR-4`) | Un fichier `squad/conformite/PASSE-TRANSVERSE-s54-*.md`, une ligne par propriete de la portee **D** (arbitrage de s29 13.2). Passes ecrites : s29, s30, s35, aucune depuis (`find squad -iname 'PASSE-TRANSVERSE*'`) | transverse | `DEC-0112` 5.4 | aucun (jugement de `nadia-prof`) | A, 0,5 |
| `S51-OUTIL-JOURNAL-TOKENS` | `ecrire_journal` conserve une section chiffree quand rien n'est mesure ; **meme geste pour `ecrire_rapport_sprint`**, et `main` sans `--sprint` ne reecrit plus le `RAPPORT-tokens.md` des sprints dont les transcriptions manquent. Prix de `theo-archi` : une fonction de 20 a 30 lignes plus `outils/rapport-tokens-journal-test.py`, double au plus avec `ecrire_rapport_sprint` | transverse | `DEC-0112` 5.5, `DEC-0110` 17 | **sofia-sm, casquette developpeuse, tache separee** | aucune |
| `S53-REPORT-GRANDS-NOMBRES` | Les 14 `enonce` de `cm1-grands-nombres` (espace insecable avant `:`) **et** `PREFIXE_ECRIRE`, `PREFIXE_LIRE` de `PaquetCm1GrandsNombresTest` (lignes 60 et 61), **dans un seul commit** | CM1 / mathematiques / ligne deja `couvert-valide`, non rouverte | `DEC-0002` amendement du 2026-08-04 ; `ADR-0067` | lea-dev (voir justification 3) | **question Q5** : correction de typographie d'un jeu servi, `nadia-prof` dit si elle la voit (0,0 propose) |
| `S53-REPORT-REDACTION` : R1, R2, R5 et `S53-REL-10` | Amendement de `DEC-0112` (acter `S53-REL-10`, corriger "0 fichier" par 4, la provenance "Relaye de l'utilisatrice") ; erratum de "onze paquets plus anciens" au backlog (9 mesures) | transverse | `DEC-0112` ; `DEC-0062` | camille-po (aucun dev) | aucune |
| `S53-REPORT-REDACTION` : R3 | Corriger "six fichiers" de la section 11 de l'arbitrage de s53 (un seul fichier, `cm1-grands-nombres`) | transverse | - | nadia-prof, **non chiffre** (Q3) | A, 0,0 propose |
| `S54-ART-CP-10-PRIX` | **Pas de construction.** Le prix de la mecanique qui montre deux oeuvres ensemble (`theo-archi`, statut d'`ADR-0104`, `Propose`) et le plafond d'un paquet DEGRADE (`nadia-prof`, une ligne) | arts plastiques / CP / `ART-CP-10` | `DEC-0113` section 9 ; Q6 | aucun dev | A, 0,0 propose |

Deux actions de redaction de la retro de s53 (A9 : provenance de `relecture-p11.md` lignes 7 a 9 ; A10 : "0 sur 182" de la lecture de testabilite)
vont a `relecteur-pedagogique` et `yanis-qa`, sans prix, au meme titre que s53.

### Capacite par instance (`PAR-9` : 4,0 par instance, marge de rejeu comprise)

| | **Instance A** (francais CE1, porte l'accumulation, `PAR-7`) | **Instance B** (sciences CM1 et EMC CM1) |
|---|---|---|
| Lots | `FRA-CE1-18` 1,0 ; `FRA-CE1-19` 1,0 | `SCI-CM1-15` 1,0 ; `SCI-CM1-18` 0,5 ; `EMC-CM1-02` 1,0 |
| Corrections et passes | `SYC-CE1-R1` + item 16 : 0,5 ; `PAR-4` : 0,5 | `CRP-CM1-R1` a `R3` : 0,5 |
| Marge de rejeu nommee, allouee a aucun lot | 0,5 | 0,5 |
| **Total / 4,0** | **3,5** | **3,5** |
| Nombre de lots / 3 | 2 | 3 (si une reprise compte pour un lot : Q4) |
| Ecart a `PAR-9` | aucun | aucun |
| Terrain neuf (`PAR-13`) | **a lire par `nadia-prof`** : francais CE1 est un seul terrain pour elle ; `FUT` et `PCO` sont cousines de `PRS`, validee | non (sciences et EMC CM1 ont des lignes `couvert-valide`) |
| Reste non alloue | 0,5 | 0,5 |

**Engage : 7,0 sur 8,0.** Le 1,0 restant est deux fois 0,5 : aucun des deux ne paie un lot (1,0), il couvre un second rejeu. Les deux instances **ne partagent aucune
matiere** (`PAR-2` : francais CE1 ; sciences CM1 et EMC) et **aucun lot n'est partage** (`PAR-1`). Si `nadia-prof` chiffre son arbitrage prealable de s54 a 0,5 (precedent de s53,
section 14) au lieu de 0,0 (precedent de s52, section 4), l'instance A passe a **4,0 sur 4,0**, toujours dans `PAR-9`.

**Variante P3, non proposee, pour que le choix soit visible.** L'instance A prend un troisieme lot francais CE1 (`ce1-familles-de-mots`, `FRA-CE1-24`) : 3,0 + 0,5 + 0,5 + 0,5 = 4,5.
C'est 0,5 au-dessus de `PAR-9` (la `DEC` ecrit l'ecart et son motif ou retire `SYC-CE1-R1`) et sous le plafond dur de 5,0, comme A et C a s52 (4,5 chacune). Une fermeture de plus,
un second rejeu sans marge. **Elle fait aussi pencher `PAR-13` : trois paquets d'un terrain neuf sur une instance.** C'est un arbitrage de `camille-po` et de `nadia-prof`.

**Ce que la capacite achete, au mieux et avec les refus mesures.** Cinq fermetures : 137/182 = 75,27 %. Avec le refus au premier passage de s52 (1 sur 6) ou celui de s51 B (2 sur 3),
trois a cinq. Une marge de 0,5 par instance finance **un** rejeu : un second refus dans une instance serait un lot non ferme, dit comme tel a la review.

### Pourquoi ces cinq, et pas d'autres (criteres lus dans le depot, pas des opinions)

1. **Fermable** : la ligne atteint `couvert-valide` selon l'arbitrage cite (G1, G5 CM1, G6). G3 et G4 ne ferment rien. G8 a G11 n'ont aucun plafond ecrit pour un paquet texte seul.
2. **Non DEGRADE** : les cinq le sont tous (le DEGRADE ne ferme une ligne que par une mention que `nadia-prof` fixe).
3. **Non touche par s53** : un paquet corrige apres coup que `nadia-prof` n'a pas relu est une reserve de plus a lire (`confirmation-archunit.md` de s53 section 3.2) ; les cinq choisis n'en portent aucune.
4. **Terrain connu ou parent d'un terrain valide** : `ce1-futur` et `ce1-passe-compose` sont de la famille de `FRA-CE1-17` (conjugaison, `ce1-conjugaison-present`, refus au premier passage sur `TRV-CE1-5`
   puis VALIDE a s52) : la defaillance connue (un indice qui renvoie a l'oreille) est nommee dans la fiche, le developpeur la lit avant.
5. **Les deux lignes de sciences ont des motifs de refus ecrits que le paquet prend** (en-tete de chaque fichier : motifs 1 et 2 de `FICHE-bandit-manchot-terre-cm1.md`, motif de la fiche d'activite humaine) :
   c'est ce que l'utilisatrice a demande en remettant les lignes a s54.

## Ce qui est reporte, et pourquoi

| Poste | Reporte parce que | Ce qui le debloque |
|---|---|---|
| **13 autres paquets de G1 et G2** (15 paquets moins `futur`, `passe-compose`, soit 13 : `accord-groupe-nominal`, `accord-sujet-verbe`, `classes-de-mots`, `familles-de-mots`, `inference`, `negation`, `phrases-types`, `pronoms`, `texte-documentaire`, `texte-narratif`, `verbe-sujet`, `ordre-des-mots`, `relire-une-phrase`) | `PAR-2` et `PAR-8` : une instance, trois lots au plus. 13 lots restants : **5 sprints a trois lots, 7 a deux** | 5 sprints de l'instance A a trois lots (s55 a s59), 7 a deux lots ; ou le decoupage du francais CE1 que `nadia-prof` dit pouvoir reexaminer (s52 section 5, verdict) |
| G3 (2) et G4 (5) | ne ferment aucune ligne (`en cours` au plus) | tarif reel de `nadia-prof` ; un sprint qui veut `en cours` pour lui-meme |
| G5 : `respect-differences`, `principes`, `personnes-differentes` | `EMC-CP-04` sous refus ; `principes` demande de jouer six points a l'ecran ; un seul lot d'EMC tient a B | s55, instance B |
| G7 (2), G8 (7), G9 (2), G10 (3), G11 (3) : **17 paquets** | **aucun plafond ecrit** pour un paquet DEGRADE texte seul, et la matrice dit pour plusieurs lignes qu'une forme texte est une contrefacon ou un quiz | un arbitrage prealable de `nadia-prof` qui ecrit un plafond par groupe (Q7), avant qu'un lot soit achete |
| `MAT-CE1-02` moitie designation, `MAT-CE1-14` | `DEC-0112` 5.3, liees a A3 d'`ADR-0080` | inchange |
| `ART-CP-10` | non financee dans les 8,0 de s54 sans retirer un lot ; question Q6 | decision de l'utilisatrice |
| Reserves de `yanis-qa` et `theo-archi` non levees a s53 (motif de table, 53 items ecartes du rang, prose du motif) | a lire au branchement des paquets concernes, pas avant | le sprint qui branche chacun |

**Engage : 5 paquets sur 45 (11,1 %), 83 items sur 784 (10,6 %).** Reporte : 40, dont 13 sont de G1 et G2 (fermables, non engages faute de lot), 17 sans plafond ecrit,
7 qui ne ferment rien, 3 d'EMC.

## Seconds reports : une proposition par poste, a decider par `camille-po`

`DEC-0112` section 6 : *"Un troisieme report n'est plus permis"*. Chaque poste est engage ou retire a s54.

| Poste | Ce que le depot dit | Proposition | Motif de la proposition |
|---|---|---|---|
| `SYC-CE1-R1` (0,5) et item 16 de `FRA-CE1-25` | reserve non bloquante de `FRA-CE1-25`, deux activites servies du cote du stereotype (items 19 et 23) ; l'item 16 est une observation de `nadia-prof` *"a re-auditer avec `SYC-CE1-R1`"* | **Engager, A, 0,5, marc-dev** | meme matiere et meme instance que les lots ; l'enonce de l'item 16 *"peut faire douter"* un enfant sur une ligne `couvert-valide` ; le developpeur est l'auteur |
| `MUL-CE1-R1` (0,5) | reserve non bloquante de `MAT-CE1-06`, la premiere phrase de six enonces parle de *"deux facons"* avant de les nommer ; aucune erreur de fait | **Retirer comme poste de sprint**, la reserve reste ecrite dans la fiche | mathematiques CE1 ne porte aucun lot a s54 (G4 ne ferme rien) ; l'engager coute 0,5 et laisserait A a 4,0 sans marge supplementaire ; `PAR-2` n'interdit pas qu'A la porte, c'est la capacite qui la retire. Rouvrable par le sprint qui porte mathematiques CE1 |

Cout de l'alternative, pour que le choix soit visible : engager `MUL-CE1-R1` porte A a 4,0 sur 4,0 (3,5 + 0,5), sans reserve de capacite si `nadia-prof` chiffre son arbitrage prealable. Retirer `SYC-CE1-R1`
libererait 0,5 sans acheter un lot (1,0).

## Les trois lignes (`DEC-0113` section 9) et le reste de la perimetre

- **`SCI-CM1-15`, `SCI-CM1-18`** : engagees par leur branchement (stories `S54-BR-SCI-CM1-15`, `-18`). Elles ne se comptent qu'une fois dans le sprint : le point (3) et le point (1) de la consigne portent le meme travail.
  **Le branchement n'engage pas la fermeture** : un REFUSE reste un refus (les deux paquets ont ete refuses trois fois au total a s51).
  **Point a ecrire par `camille-po` (Q1)** : `DEC-0112` 5.1 retirait ces postes parce qu'une instance de sciences CM1 ne reunit pas la barre de 4,0 que `nadia-prof` a exigee (1,5 de reprises plus `CRP` 0,5 = 2,0).
  B porte 3,5 avec un lot d'EMC : la `DEC` d'ouverture dit si cela remplit la barre ou si `nadia-prof` l'amende (Q4).
- **`ART-CP-10`** : cinquieme sprint de report si elle n'est pas engagee, **ce que `DEC-0112` 6 et `DEC-0113` 9.3 interdisent de faire en silence**. Le fait : le paquet de la session
  (`cp-arts-oeuvres-familles`) est DEGRADE et son en-tete dit qu'il *"ne ferme pas la ligne"* ; `ADR-0104` est `Propose`, la mecanique n'existe pas ; `nadia-prof` a repondu le 2026-10-06 que `C5` clause 1 ne mord pas sur une
  reproduction qui constitue la proposition entiere (`DEC-0113` section 3.1, qui cite `DEC-0111` A.14). **Ce que s54 engage** : le prix de la mecanique (`theo-archi`) et le plafond du paquet (`nadia-prof`, une ligne). **Ce que s54 n'engage pas** : la construction,
  qui retirerait des lots a A ou B. L'utilisatrice choisit (Q6).

## Ordre propose des matieres : a arbitrer par l'utilisatrice

**Question U2 de la review de s53.** Proposition de `sofia-sm`, fondee sur les colonnes de la table des groupes, **non decidee** :

| Rang | Groupe | Pourquoi ici (mesure) | Risque nomme |
|---|---|---|---|
| 1 | **G1 puis G2** (francais CE1, 15 lignes fermables) | chemin critique : une instance, 6 sprints au moins ; 15 lignes sur 15 fermables ; conditions acceptees en s48 ; aucun dessin ; instance A deja auditee a s52 (3 lots, 3 fermes) | `PAR-13` : le francais CE1 est un terrain neuf pour `nadia-prof` ; un refus sur trois a s52 |
| 2 | **G6 puis G5** (sciences CM1, EMC CM1) | 2 + 3 lignes fermables ; les deux reprises ont des motifs de refus ecrits et pris ; c'est la demande de l'utilisatrice du 2026-10-07 pour les deux lignes de sciences | EMC : `principes` est *"la ligne la plus dangereuse de la matiere"* (matrice) ; `EMC-CP-04` sous refus |
| 3 | **Arbitrage prealable sur G7 a G11** (17 paquets) | aucun plafond ecrit ; acheter des lots avant de savoir si la ligne ferme, c'est reproduire le refus de s51 | un paquet DEGRADE branche puis refuse coute 1,0 plus 0,5 de rejeu |
| 4 | G3, G4 (7 paquets) | ne ferment rien | `en cours` seulement |

Ordre alternatif, que je ne propose pas mais que l'utilisatrice peut choisir : **G8 d'abord** (7 lignes d'histoire des arts et d'arts CM1, la plus grande matiere restante), au motif
d'un manque de couverture visible ; il fait entrer 7 lots sous un plafond non ecrit et la matrice dit que le paquet texte seul n'y repond pas. **Je ne le recommande pas avant l'arbitrage de `nadia-prof`.**

## Affectations nominatives (`PAR-16`)

| Agent | Taches | Couloir |
|---|---|---|
| **nadia-prof, instance A** | arbitrage prealable de s54 (Q3 a Q8), audits de `FRA-CE1-18`, `-19`, re-audit `SYC-CE1-R1` + item 16, passe `PAR-4`, correction de la section 11 de son arbitrage (R3) | instance A, scratchpad dedie |
| **nadia-prof, instance B** | rejeu `CRP-CM1-R1` a `R3`, audits de `SCI-CM1-15`, `-18`, `EMC-CM1-02` | instance B, scratchpad dedie (README section 9, amendement s30) |
| **camille-po** | `DEC` d'ouverture de s54 (Q1) ; amendement de `DEC-0112` (R1, R2, `S53-REL-10`) ; erratum R5 au backlog ; fiches et matrice au fil des verdicts ; reponses a Q2, Q6 | decision |
| **theo-archi** | avis de faisabilite (section ci-dessous) ; prix de l'outil de journal avec `ecrire_rapport_sprint` ; prix et statut pour `ART-CP-10` (`ADR-0104`) ; A8 de la retro de s53 (regle du push dans le README) | decision |
| **marc-dev** | dans cet ordre, un seul worktree ouvert a la fois : `S51-SCI-CORPS-CM1-RESERVES` (premiere a fusionner), puis `S54-BR-FRA-CE1-18`, `S54-BR-FRA-CE1-19`, puis `SYC-CE1-R1` + item 16 | un worktree |
| **lea-dev** | `S54-BR-SCI-CM1-15`, `S54-BR-SCI-CM1-18`, `S54-BR-EMC-CM1-02`, puis `S53-REPORT-GRANDS-NOMBRES` | un worktree |
| **sofia-sm (casquette developpeuse)** | `S51-OUTIL-JOURNAL-TOKENS`, apres l'avis de `theo-archi`, dans une tache qui annonce la casquette ; **je ne l'ouvre pas dans cette tache** | un worktree |
| **yanis-qa** | lecture de testabilite des criteres, rapport qualite de cloture sur l'arbre fusionne, quatre jobs nommes ; A10 de la retro de s53 | cloture |
| **relecteur-pedagogique** | relecture de fin de sprint, une ligne meme s'il ne trouve rien ; A9 de la retro de s53 | cloture |
| **ines-design** | aucune (aucun des cinq paquets n'est illustre) | - |

Justification :

1. **Un dev tient un seul worktree a la fois.** marc-dev porte quatre postes et lea-dev quatre ; aucun n'est sur deux worktrees en parallele (README section 9).
2. **Le premier fusionne est `CRP-CM1-R1` a `R3`** : c'est le seul poste qui touche un jeu servi a l'enfant aujourd'hui (une phrase fausse a l'ecran) ; il ne depend d'aucune decision.
3. **Affectation de capacite, mon role.** marc-dev est l'auteur de `ce1-futur`, `ce1-passe-compose`, `cm1-sciences-corps`, `cm1-emc-atteinte-autrui` et de `cm1-grands-nombres` et de son test ; lea-dev, des deux
   paquets de sciences. Donner a marc-dev ses quatre postes et en plus l'EMC et `grands-nombres` aurait fait six postes contre deux pour lea-dev : **`S54-BR-EMC-CM1-02` et `S53-REPORT-GRANDS-NOMBRES` vont a lea-dev,
   qui n'en est pas l'auteure**. Cout nomme : elle lit le paquet d'EMC et le test de `grands-nombres` avant d'editer ; le test de `grands-nombres` est un seul commit de 14 lignes et deux constantes. Si elle
   devient le chemin critique, je peux deplacer le second vers marc-dev sous les conditions de s52 (elle n'a pas commence, marc-dev n'a plus de worktree ouvert, entree au tableau d'obstacles le jour meme).
4. **`S51-OUTIL-JOURNAL-TOKENS` a un proprietaire** : `DEC-0112` 5.5 le reportait faute de proprietaire. Je me l'affecte sous ma casquette developpeuse, parce que marc-dev et lea-dev portent des paquets et que `theo-archi` n'ecrit pas de code.
   Les deux casquettes ne sont pas portees dans la meme tache.

### Isolation et fusion

Une branche, un worktree, un scratchpad par agent ; un scratchpad dedie par instance de `nadia-prof`. Aucun serveur de jeu n'est lance par les developpeurs ; si `nadia-prof` en lance un (audit joue), un port par instance.

| Agent | Branche | Worktree (dossier voisin du checkout) |
|---|---|---|
| marc-dev | `s54-marc-branchement` | `../recreo-s54-marc-branchement` |
| lea-dev | `s54-lea-branchement` | `../recreo-s54-lea-branchement` |
| nadia-prof A | `s54-nadia-instance-a` | `../recreo-s54-nadia-instance-a` |
| nadia-prof B | `s54-nadia-instance-b` | `../recreo-s54-nadia-instance-b` |
| camille-po | `s54-camille-dec` | `../recreo-s54-camille-dec` |
| theo-archi | `s54-theo-avis` | `../recreo-s54-theo-avis` |
| relecteur-pedagogique | `s54-rel-redaction` | `../recreo-s54-rel-redaction` |
| yanis-qa | `s54-yanis-qualite` | `../recreo-s54-yanis-qualite` |
| sofia-sm (dev) | `s54-sofia-tokens` | `../recreo-s54-sofia-tokens` |

**Longueur des chemins** : le chemin suivi le plus long fait 219 caracteres (`git ls-files | awk '{ print length($0) }' | sort -n | tail -1`, a `52e2b0cd`) ; les noms ci-dessus ont 20 a 27 caracteres, soit un total
de 249 a 256 avec le prefixe de poste de s52 (nom + 10) : marge de 4 a 11 caracteres sous 260, la plus courte pour les trois noms de 27. Non verifie sur un autre poste.

**Ouverture des worktrees** : par moi, dans le meme geste que le routage, **une fois ce planning fusionne sur `round-two`** (README section 9, amendement s40). **Cette tache ne fusionne pas et n'ouvre aucun worktree d'agent.**

**Ordre de fusion** : decisions d'abord (`DEC` d'ouverture, arbitrage prealable, avis, amendement de `DEC-0112`), puis `S51-SCI-CORPS-CM1-RESERVES`, puis les branchements de marc-dev, puis ceux de lea-dev. `catalogue.yaml`,
`TexteAccentueExposeParLePortEntrantTest` et `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` sont modifies par les deux couloirs : on garde les ajouts des deux cotes, jamais une assertion affaiblie
(README section 9). **`bash outils/mvn-jdk21.sh -B clean verify` sur chaque commit de fusion. Tout compte de tests est celui du reacteur complet sur l'arbre fusionne, jamais la somme de deux branches** ; le dernier total connu
est 2174 (s53, cloture), a remesurer. Lignee verifiee avant chaque fusion (`bash outils/verifier-lignee.sh`).

## Criteres d'acceptation

**Communs** (chacun est une commande ou une lecture) :

- **C1 - decision d'abord.** Un branchement n'est edite qu'apres que le fichier de decision qui engage son poste (`DEC` d'ouverture de s54, amendement de ce planning) existe sur `round-two` (`git show round-two:<fichier>`).
- **C2 - un branchement est un diff borne.** `git diff round-two --stat` d'une story de branchement ne montre que : l'entree de `catalogue.yaml`, le test du paquet (si T1 le confirme), son inventaire de chaines, et les declarations des garde-fous ; aucun YAML de paquet sauf correction decidee par `nadia-prof`.
- **C3 - `grep -c "paquet: <nom>$" catalogue.yaml` rend 1** apres livraison, **0 aujourd'hui** : rouge sur l'existant. Les 40 autres paquets : toujours 0 a la cloture (`git diff round-two -- catalogue.yaml` ne les nomme pas).
- **C4 - suite verte, perimetre nomme.** Mesure partielle : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,bootstrap -am test` (`-am` obligatoire). Porte : `mvn -B clean verify` de la racine. Une mesure partielle ne tient jamais lieu de la porte (retro de s53 section 3).
- **C5 - aucun garde-fou assoupli.**
- **C6 - jobs nommes** : chaque livraison nomme les jobs de `ci.yml` qu'elle a lances (`prefixes`, `chemins`, `outils`, `build`) et ne deduit pas les autres.
- **C7 - un branchement s'arrete au verdict.** Aucune ligne de matrice ne change de statut avant le verdict ecrit de `nadia-prof` dans la fiche, et la mention `MNC-1` est reproduite mot pour mot quand elle est due. **Le planning ne prononce aucun Done.**

**Specifiques** :

| Story | Critere | Rouge sur l'existant ? |
|---|---|---|
| `S51-SCI-CORPS-CM1-RESERVES` | criteres du backlog, repris : l'indice de l'item 3 ne dit plus que le corps *"ne fait rien d'autre"* ; item 5, item 6, item 11, item 7 comme ecrits au backlog ; aucun item ajoute ni retire (`grep -c '^ *- enonce:'` rend 12 avant et apres) | oui (item 3 faux aujourd'hui) |
| `S54-BR-SCI-CM1-15`, `-18` | C3 ; l'en-tete de chaque paquet nomme les motifs de la fiche de s51, et `nadia-prof` rejoue ces items en premier | oui (0 entree) |
| `S54-BR-FRA-CE1-18`, `-19` | C3 ; la regle `TRV-CE1-5` (aucun indice ne renvoie a l'oreille) est relue par le developpeur sur les 24 items, resultat ecrit dans son rapport | oui |
| `S54-BR-EMC-CM1-02` | C3 ; `EMC-9` (aucun item ne laisse croire a l'enfant qu'il vient d'alerter quelqu'un) relue par le developpeur | oui |
| `S53-REPORT-GRANDS-NOMBRES` | `grep -cE '^ *- enonce: ".* :' contenu/cm1-grands-nombres.yaml` rend 0 (14 aujourd'hui) ; **un seul commit** change les 14 lignes et les deux constantes ; `clean verify` vert, nombre de tests inchange | oui |
| `S51-OUTIL-JOURNAL-TOKENS` | les quatre cas de la story, plus : `ecrire_rapport_sprint` conserve un rapport chiffre quand rien n'est mesure ; `python outils/rapport-tokens.py` sans `--sprint` ne reecrit plus le `RAPPORT-tokens.md` de s22 a s27 (`git status --short` apres execution) ; un fichier `outils/rapport-tokens-journal-test.py` que le job `outils` lance | oui |
| `S53-PASSE-TRANSVERSE` | `find squad -iname 'PASSE-TRANSVERSE-s54*'` rend un fichier (rend rien aujourd'hui) ; une ligne par propriete de la portee **D** ; sha de l'arbre ; prix reel ecrit | oui |
| `SYC-CE1-R1` + item 16 | `nadia-prof` rejoue les items 19, 23 et 16 *"a la barre ecrite"* dans la fiche | non automatisable |
| `S53-REPORT-REDACTION` | `grep -n "0 fichier" DEC-0112-*.md` ne rend plus R1 ; `grep -n "six fichiers"` sur l'arbitrage de s53 ne rend plus R3 ; une note d'erratum de R5 au backlog | oui |

**Ce que `yanis-qa` doit dire**, avant la premiere livraison : lesquels de ces criteres un automate ne juge pas (mes lectures : `TRV-CE1-5` et `EMC-9` sur les paquets, le rejeu de `SYC-CE1-R1`, la passe `PAR-4`).

## Avis de faisabilite - theo-archi

**Aucun avis ecrit a la cloture de ce planning.** Je ne l'ai pas invoque et je n'ecris pas son avis pour lui. Le tableau dit ce qu'il doit rendre ; la colonne "ma lecture" n'est pas un avis.

| Story | Avis | ADR requis | Questions a lui, avec ma lecture (non un avis) |
|---|---|---|---|
| `S54-BR-*` (cinq) | a rendre | a determiner | **T1** : le test du paquet est-il obligatoire par un garde-fou ou par convention ? (mesure : aucun des 45 n'en a, aucun garde-fou connu ne l'exige ; ~340 lignes pour un `PaquetCe1*`). **T2** : quelles listes de garde-fous les cinq branchements modifient-ils encore (le rang et les sous-parties sont deja declares par `70b1cdc1`) ? **T3** : `type: question` pour les 45, mecanique `bandit-manchot` : un seul des cinq demande-t-il autre chose ? |
| `S51-SCI-CORPS-CM1-RESERVES` | a rendre | non attendu | contenu seul |
| `S51-OUTIL-JOURNAL-TOKENS` | **prix deja rendu** (`confirmation-archunit.md` s53 section 5) ; a confirmer avec `ecrire_rapport_sprint` et le defaut de `main` sans `--sprint` (lignes 462 a 464) | non | **T4** : le geste couvre-t-il aussi `main` sans `--sprint` ? |
| `S53-REPORT-GRANDS-NOMBRES` | a rendre | non | **T5** : les deux constantes en espace insecable suffisent-elles, ou le `startsWith` doit-il changer de forme ? |
| `S54-ART-CP-10-PRIX` | prix a rendre | **`ADR-0104`, statut** : `Propose` ; sa condition de passage a `Accepte` etait la reponse de `nadia-prof` a `C5` clause 1 (donnee le 2026-10-06 selon `DEC-0113` 3.1) | **T6** : statut d'`ADR-0104`, et prix d'une mecanique qui montre deux oeuvres ensemble, en tailles (le depot n'a pas de tarif de developpement) |
| README, section 9 | a rendre | amendement | **T7** : ecrire la regle du push (A8 de la retro de s53) |

## Verification poste/story (`PAR-16`, `squad/README.md` section 4)

Faite a la cloture de ce planning. Aucune `DEC` de composition n'existe pour s54 : la table de composition est la liste du message de lancement, point par point.

| Poste du message | Story | Affectation nominative | Issue |
|---|---|---|---|
| (1) branchement des 45 : cinq engages | `S54-BR-*` (cinq) | marc-dev (2), lea-dev (3) ; audits : instance A (2), B (3) | engage |
| (1) branchement des 45 : quarante restants | aucune story | - | **non engage, budget : aucun montant libere** (la capacite est consommee, la liste est en "Ce qui est reporte"), **decision de l'utilisatrice sur l'ordre** |
| (2) `CRP-CM1-R1` a `R3` | `S51-SCI-CORPS-CM1-RESERVES` | marc-dev ; instance B | engage |
| (2) `S53-PASSE-TRANSVERSE` | idem | instance A | engage |
| (2) journal de tokens | `S51-OUTIL-JOURNAL-TOKENS` | sofia-sm (dev) | engage |
| (3) `SCI-CM1-15`, `SCI-CM1-18` | `S54-BR-SCI-CM1-15`, `-18` | lea-dev ; instance B | engage (compte une fois avec (1)) |
| (3) `ART-CP-10` | `S54-ART-CP-10-PRIX` | theo-archi, nadia-prof | engage comme prix et plafond, **construction non engagee, Q6** |
| (4) `S53-REPORT-GRANDS-NOMBRES` | idem | lea-dev | engage |
| (5) R1, R2, R3, R5, `S53-REL-10` | `S53-REPORT-REDACTION` | camille-po (R1, R2, R5, `S53-REL-10`), nadia-prof (R3) | engage |
| `SYC-CE1-R1` + item 16 | `S52-SYC-CE1-R1` | marc-dev ; instance A | **proposition : engager**, a decider |
| `MUL-CE1-R1` | - | - | **proposition : retirer**, a decider ; en attendant, **non engage, 0,5 libere dans la marge de A** |

**Aucun poste engage sans story, aucun sans affectation nominative.** Ecart signale : les stories `S54-BR-*`, `S52-SYC-CE1-R1` et `S54-ART-CP-10-PRIX` n'existent pas au backlog (qui est a `camille-po`) ; ce planning les nomme et
les affecte, il ne les ecrit pas a sa place.

## Risques

| Code | Risque | Ce qui le contient |
|---|---|---|
| R1 | Le perimetre repose sur un message d'agent et aucune `DEC` d'ouverture : trois postes engages sans l'acte que `DEC-0112` exige, comme a s53 (retro, "trois engagements ajoutes apres le planning") | Q1 ; amendement de ce planning avant l'ouverture du premier worktree concerne |
| R2 | **Tarif des 45 inconnu** : 1,0 par paquet est le tarif du depot, pas un prix de `nadia-prof` pour ces paquets (aucun n'a de verdict) | Q3 ; le plan tient si le prix est 1,0 ; il se resserre sinon |
| R3 | Les reprises de sciences se lisent soit 1,0 et 0,5 (`DEC-0112`), soit au tarif de lot (`PAR-14`) : l'en-tete de `terre` cite des corrections sur environ 7 items de 12 (2, 3, 4, 7, 9, 10, 11), soit 58 % ; celui d'`activite-humaine` en cite 2 a 3 | Q4 ; B passe de 3,5 a 4,0 si les deux sont a 1,0 : `PAR-9` tient encore |
| R4 | B porte trois lots et un terrain EMC : un refus de `principes` ou d'`atteinte-autrui` consomme la marge et laisse sciences sans rejeu, comme `SCI-CM1-15` a s51 | une seule marge par instance ; un refus de plus est dit non ferme a la review |
| R5 | Un branchement fait entrer un paquet dans des garde-fous qui jugent tout `contenu/` (base rouge de s53) | C4 : reacteur complet a chaque fusion ; mesure partielle sans `-am` interdite |
| R6 | Les deux instances de `nadia-prof` ecrivent la meme ressource sans le savoir (forme (c), s50) | scratchpad et worktree dedies par instance ; `REGISTRE-identifiants.md` ecrit depuis le worktree de l'instance |
| R7 | Le journal de tokens regenere perd des sections chiffrees ; `ecrire_rapport_sprint` reecrit s22 a s27 sans `--sprint` | la story ; en attendant, la review de s54 lance `--sprint s54` explicite et restaure le journal |
| R8 | Le francais CE1 est le chemin critique et le seul qui ne se partage pas : 6 sprints au moins | question a l'utilisatrice (ordre) et a `nadia-prof` (reexamen de `PAR-2`) |

## Questions ouvertes

Ecrites comme ouvertes ; aucune n'est tranchee par ce planning.

| Code | Question | Route a | Artefact attendu |
|---|---|---|---|
| Q1 | `DEC` d'ouverture de s54 : engage-t-elle les stories ci-dessus, tranche-t-elle les trois lignes (`DEC-0113` 9.3), designe-t-elle les proprietaires de `CRP`, `PAR-4`, journal, et reconnait-elle le message de lancement comme source du perimetre ? | `camille-po`, utilisatrice | `DEC` |
| Q2 | `SYC-CE1-R1` + item 16 : engager ; `MUL-CE1-R1` : retirer ? | `camille-po` | `DEC` |
| Q3 | Quel est le prix reel d'un paquet de la session (1,0 ?), d'un arbitrage prealable de s54 (0,0 ou 0,5), de la correction de R3, des deux passes de `PAR-4` ? | `nadia-prof` | arbitrage prealable |
| Q4 | Les deux reprises de sciences : 1,0 et 0,5, ou tarif de lot (`PAR-14`) ? Comptent-elles pour des lots sous `PAR-8` ? B a 3,5 remplit-elle la barre de `DEC-0112` 5.1 ? | `nadia-prof`, `camille-po` | arbitrage, `DEC` |
| Q5 | `S53-REPORT-GRANDS-NOMBRES` : la typographie d'un jeu servi passe-t-elle par elle (0,0, 0,25) ou par `relecteur-pedagogique` seul ? | `nadia-prof` | une ligne |
| Q6 | `ART-CP-10` : financer la mecanique (de quoi ?), brancher le paquet DEGRADE sans fermer, ou une autre issue de `DEC-0111` section 9 ? | **utilisatrice**, `theo-archi` pour le prix | decision |
| Q7 | Plafond atteignable par groupe pour les 17 paquets de G7 a G11 (lecture : ceux dont la matrice dit *"image requise"* ou *"quiz de noms"*) | `nadia-prof` | arbitrage prealable |
| Q8 | Francais CE1 : un decoupage qui ne coupe aucune frontiere de la table de s48 section 7 est-il propose (le seul levier du chemin critique) ? | `camille-po` propose, `nadia-prof`, `theo-archi` (`ADR-0065`) | proposition ecrite |
| Q9 | Lire le verdict de la CI sur `round-two` a `52e2b0cd` (la reference locale dit que s53 est pousse, aucun verdict n'est lisible d'ici) | utilisatrice | verdict |
| Q10 | **Ordre des matieres a brancher** : mon ordre G1/G2, puis G6/G5, puis arbitrage sur G7 a G11, puis G3/G4 ; ou l'alternative G8 d'abord ? | **utilisatrice** | arbitrage |

## Porte technique

- **Juge : la CI, tous les jobs de `.github/workflows/ci.yml`** : `prefixes`, `chemins`, `outils`, `build`. `mvn verify` n'est que le contenu du job `build`. Chaque agent nomme les jobs qu'il a lances.
- **`yanis-qa`** : rapport qualite de cloture sur l'arbre fusionne, quatre jobs nommes, commande exacte, total mesure et origine de l'ecart avec la base. **Je n'annonce aucun total** : 2174 est le dernier connu (s53) et chaque branchement ajoute des tests.
- **`theo-archi`** : confirmation ArchUnit a la cloture ; une regle ArchUnit ecrite dans le meme geste si T6 ou T7 en appelle une.
- **Porte pedagogique** : **prononcee par `nadia-prof` seule**, apres avoir joue chaque paquet, avec un motif ecrit dans `squad/conformite/FICHE-<jeu>.md`. **Ce planning ne prononce aucun Done**, ni technique ni pedagogique.

## Ce que ce planning ne fait pas

- Il ne decide ni les tarifs, ni les plafonds, ni la lecture de `PAR-14`, ni les deux seconds reports, ni l'ordre des matieres, ni le sort d'`ART-CP-10` : il les propose ou les route.
- Il n'ecrit aucune `DEC`, aucun `ADR`, aucun avis a la place de `theo-archi` ni arbitrage a la place de `nadia-prof`, et n'amende ni `PAR-2`, ni `PAR-8`, ni `PAR-9`, ni `PAR-14`.
- Il n'ouvre aucun worktree d'agent, ne fusionne rien et ne pousse rien.
- **Il ne modifie ni le backlog, ni la matrice, ni une fiche, ni le registre, ni `catalogue.yaml`.** La matrice reste a 132/182 ; aucune ligne ne change de statut.

## Decisions prises

Aucune `ADR` ni `DEC` n'est creee ou amendee par ce planning. Six decisions de planning, qui relevent de mon role :

1. **Decoupage en stories** (une par paquet branche, une par report) et **cinq paquets branches**, dont l'ordre est une proposition (Q10).
2. **Affectations nominatives** : marc-dev et lea-dev, journal de tokens a sofia-sm (casquette developpeuse, tache separee) ; `S54-BR-EMC-CM1-02` et `S53-REPORT-GRANDS-NOMBRES` a lea-dev, qui n'en est pas l'auteure (justification 3).
3. **Deux instances de `nadia-prof`** (A : francais CE1 et accumulation ; B : sciences et EMC CM1), 3,5 sur 4,0 chacune ; ce chiffre applique `PAR-9` et ne l'ouvre pas : l'ouverture est a la `DEC`.
4. **Correction du chiffre de s53** : 45 paquets demandent au moins 8 sprints (9 en lecture realiste), pas six, parce que `PAR-8` plafonne a six lots par sprint.
5. **Isolation** : un worktree, une branche et un scratchpad par agent, dedies par instance de `nadia-prof` ; fusion decisions, `CRP`, marc-dev, lea-dev.
6. **`S54-ART-CP-10-PRIX`** : s54 engage le prix et le plafond de `ART-CP-10`, pas sa construction.

## Prochaines etapes

Ce planning est fusionne sur `round-two` par moi avant tout lancement (cette tache ne fusionne pas). Ensuite, par agent :

- `camille-po` : `DEC` d'ouverture (Q1, Q2), amendement de `DEC-0112`, erratum R5 ; stories au backlog.
- `nadia-prof` : arbitrage prealable (Q3 a Q5, Q7) ; passes et audits au fil des livraisons.
- `theo-archi` : avis de faisabilite (T1 a T7) ; prix de l'outil de journal et d'`ART-CP-10`.
- `marc-dev`, `lea-dev` : propositions et edits sur C1, dans l'ordre ci-dessus.
- `yanis-qa` : lecture de testabilite des criteres ; `relecteur-pedagogique` : relecture de cloture.
- `sofia-sm` : ouverture des worktrees des agents routes, verification de lignee a chaque fusion, `review.md` avec le tableau des obstacles, `RAPPORT.md`, `python outils/rapport-tokens.py --sprint s54`
  (**avec `--sprint` explicite**, tant que le defaut de l'outil n'est pas corrige), `retro.md`. **Le daily n'est pas journalise.**
- **Utilisatrice** : Q6, Q9 et Q10.
