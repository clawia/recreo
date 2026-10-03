# DEC-0107 - Ouverture de s49 : trois corrections differees de s48, `FRA-CE1-01` (premiere ligne
# construite du vivier francais CE1), chantier de sourcing du corpus d'oeuvres du domaine public
# inscrit

- **Type** : decision d'ouverture de sprint (perimetre, chiffrage, routage).
- **Date** : 2026-10-03
- **Auteur** : `camille-po`
- **Sprint** : s49
- **Competence programme visee** : transverse - ce document gele un perimetre de sprint, il ne
  tranche aucune question pedagogique de contenu.
- **Statut** : Acceptee
- **Lien** : `squad/sprints/s49-corrections-s48-sourcing-oeuvres-et-ouverture-francais-ce1/planning.md`
  (sections 2 a 6) ; `DEC-0106` (precedent de tarif `PAR-10` pour une correction differee, section 2
  et 4 corrigee ci-dessous) ; `DEC-0085` (clause `PAR-10`, `PAR-14`) ; `DEC-0077`/`DEC-0105`
  (chantier de sourcing du corpus d'oeuvres) ; `squad/rapports/avis-faisabilite-s48-sept-stories.md`
  (section 11, demande d'inscription du chantier a s49) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` (section 3.1, verdict sur `FRA-CE1-01`) ;
  `docs/backlog/backlog.md`, sections "Backlog - sprints suivants" (les trois corrections) et
  "Sprint 49" (`S49-FRA-GRAPHEMES`, engagee dans le meme mouvement que ce document).

---

## 1. Perimetre gele

Instance unique de `nadia-prof` (Done pedagogique) :

| Story | Tarif | Motif |
|---|---|---|
| `PRO-CE1-R1` + `PRO-CE1-R2`, groupees (`bandit-manchot-problemes-etapes-ce1`) | 0,5 | correction, un seul paquet, quatre items sur vingt-quatre |
| `ANG-CP-R3` + l'item non leve d'`ANG-CP-R1`/`ANG-CP-R2`, groupees (`bandit-manchot-culture-anglophone-cp`) | 0,5 | correction, un seul paquet, trois items sur douze |
| Reserve sans identifiant d'`EMC-CP-14`, item 7 (`bandit-manchot-alerte-cp`) | 0,5 | correction, un seul paquet, un item sur onze |
| `FRA-CE1-01` (lire des mots a graphemes complexes et les associer a leur son) | 1,0 | ligne neuve, `nadia-prof` source du referentiel, verdict deja ecrit |
| **Total** | **2,5 / 5,0** | marge 2,5 |

Hors chiffrage de cette borne : le chantier de sourcing du corpus d'oeuvres du domaine public
(section 5, perimetre complete en section 9 - T2 a T5 inclus) - aucun Done pedagogique n'est
attendu dessus a s49, et aucune unite en lots ne le mesure.

## 2. Tarif des trois corrections differees : 0,5 chacune, meme precedent `PAR-10` que `DEC-0106`

Les trois stories sont deja ecrites, completes, avec criteres d'acceptation testables
(`docs/backlog/backlog.md`, section "Backlog - sprints suivants") : reserves ouvertes par
`nadia-prof` a l'audit de s48 sur trois paquets distincts, groupees par paquet par mes soins quand
plusieurs reserves portent sur le meme audit.

**Verification que `PAR-14` ne s'applique a aucune des trois**, d'apres le volume que chaque story
ecrit elle-meme :

- `PRO-CE1-R1`/`PRO-CE1-R2` : quatre items sur vingt-quatre (items 8, 9, 10, 11).
- `ANG-CP-R3` + l'item non leve d'`ANG-CP-R1`/`ANG-CP-R2` : trois items sur douze (items 2, 10, 12).
- La reserve sans identifiant d'`EMC-CP-14` : un item sur onze (item 7).

Aucune des trois ne touche la majorite des items de son paquet : `PAR-14` ne s'applique pas,
`PAR-10` s'applique (`DEC-0085`, section 8, repris sans changement par `DEC-0106`, section 2 - une
reserve se paie 0,5 par paquet touche, qu'elle groupe une ou plusieurs reserves du meme audit).
Total des trois corrections : **1,5 / 5,0**.

**Risque assume, nomme comme dans `DEC-0106`** : si l'une de ces corrections s'avere a l'audit
toucher en realite plus de la moitie de son paquet une fois le diff vu, `PAR-14` reprend la main et
la story vaut le tarif d'un lot, pas 0,5.

## 3. `FRA-CE1-01` entre au perimetre de s49 comme premiere ligne construite du vivier francais CE1

`ARBITRAGE-PREALABLE-s48-francais-ce1.md` instruit les 26 lignes du referentiel francais CE1 :
**26/26 ACCEPTE SOUS CONDITIONS**, 24 plafonnees `couvert-valide` atteignable. Le planning de s49
(section 3.2) nomme `FRA-CE1-01` et `FRA-CE1-02` comme les deux lignes qui portent le moins de
conditions transverses nouvelles a verifier (pas de `texteSupport`), sans les trancher. Je retiens
**`FRA-CE1-01` seule**, pour deux motifs :

- **Le modele etabli depuis s37 est une ligne neuve par sprint** pour ce type d'ouverture de vivier
  (planning, section 4, point 3), reconduit ici plutot que redecouvert au prix d'ouvrir deux lignes
  d'un coup sur un vivier qui n'a encore produit aucun paquet.
- **C'est la ligne qui valide en premier l'ensemble de l'appareil transverse** (`TRV-CE1-1` a
  `TRV-CE1-7`, `LON-CE1-1`, `LON-CE1-2`, `LON-CE1-5`) sur un terrain ou `nadia-prof` signale
  elle-meme des risques reels pour les lignes suivantes (ambiguite d'une question sur texte support,
  section 1.2 de son arbitrage ; cas limites d'elision et de coupe de phrase, sections 0.3 et 1.1) :
  auditer un premier paquet avant d'en engager un second reduit le risque qu'un defaut transverse se
  reproduise sur deux paquets a la fois plutot que sur un seul.

**Tarif : 1,0.** `nadia-prof` est la source du referentiel de francais CE1, qu'elle a ecrit
elle-meme (`DEC-0103`) : meme regle que pour les mathematiques CE1 depuis `DEC-0074`
("suis-je la source de cette matiere ?" - 1,0 quand elle l'est). Le verdict et les trois conditions
`GRC-CE1-1` a `GRC-CE1-3` sont deja ecrits (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section 3.1) ;
aucun arbitrage prealable supplementaire n'est du. Story ecrite dans `docs/backlog/backlog.md`,
section "Sprint 49" (`S49-FRA-GRAPHEMES`), dans le meme mouvement que ce document.

**`FRA-CE1-02` n'est pas ecartee de fond** : elle reste au vivier ouvert, candidate naturelle d'un
prochain sprint une fois `FRA-CE1-01` auditee.

## 4. Instance unique, pas deux instances paralleles

Le volume total de s49 (2,5/5,0) tient confortablement dans une seule instance de `nadia-prof` :
ouvrir une seconde instance paralleliserait un volume qui ne le demande pas, au prix reel que
`DEC-0077` (section 8.5) a deja nomme - deux instances sur des lots disjoints sont deux paires
d'yeux, pas un double controle, et la parallelisation ne remplace aucune des proprietes dont
`nadia-prof` reste seule la garde. Avec une seule instance, `PAR-1` (aucun lot partage entre deux
instances) et `PAR-2` (une matiere et un niveau jamais partages entre deux instances) sont
satisfaites par construction : il n'existe qu'une instance, donc aucun partage possible. La marge de
sprint (2,5) est plus large que les marges de 1,0 retenues depuis s42 sur des sprints pleins a
8,0 : elle n'est pas reduite artificiellement par l'ajout d'un lot qui n'a pas de motif pedagogique
ou de couverture propre, faute d'un autre candidat atteignable ce sprint (le vivier de mathematiques
CE1 est instruit epuise, planning section 3.1 ; aucun autre vivier hors mathematiques n'est signale
disponible pour s49 dans le planning).

## 5. Le chantier de sourcing du corpus d'oeuvres du domaine public entre au perimetre de s49, sans
## tarif en lots

`DEC-0077` (section 4) a tranche l'option (un corpus de reproductions du domaine public) le
2026-09-05. Rien ne reste a trancher sur le fond : seule son execution manque, en souffrance depuis
quatre sprints (`DEC-0105`). `theo-archi` a refuse de l'engager a s48 faute d'inscription au
perimetre (`squad/rapports/avis-faisabilite-s48-sept-stories.md`, section 11) et demande
explicitement son inscription a s49, en s'engageant a rendre l'`ADR` et sa regle ArchUnit a
l'ouverture du sprint qui l'inscrit. **Je l'inscris.**

**Motif de ne pas le reporter une sixieme fois.** Le chantier a deja attendu s29 a s48 sans etre
engage (quatre sprints nommes comme dette d'execution par `DEC-0105`, sans compter les sprints
anterieurs ou il n'etait meme pas signale). Un report de plus, sur un motif qui resterait le meme
("hors perimetre gele"), deviendrait la direction que personne n'a choisie plutot qu'un arbitrage -
exactement le risque que `CLAUDE.md` nomme pour `DEC-0007`. `theo-archi` a deja fait le geste qui
manquait la fois precedente (demander l'inscription plutot que refuser silencieusement) ; le geste
qui me revient est de ne pas le laisser sans reponse une seconde fois.

**Aucun tarif en lots ne lui est applique, et je ne l'invente pas.** `DEC-0077` (section 4) l'a deja
qualifie de "chantier de sourcing et de verification de droits", distinct d'un lot de contenu
pilotable par `nadia-prof`. Aucune unite de ce type n'a jamais mesure ce genre de chantier dans ce
depot : le seul precedent comparable, le palier 2 de designation d'un lieu sur une image
(`DEC-0074`, section 1.2), est entre au perimetre de s28 comme "chantier de mecanique seul (`ADR` de
`theo-archi`, plus un developpeur)", explicitement hors de la borne d'audit de `nadia-prof`, sans
jamais recevoir de chiffre en points ou en lots - son cout a ete porte en developpeur affecte, pas
en unite comptee contre une borne. Je traite le chantier de sourcing de la meme maniere : **hors
chiffrage, borne de forme plutot que de volume**. Sa seule borne est que `theo-archi` rende l'`ADR`
et sa regle avant la cloture de s49, exactement comme il s'y est engage ; la recherche de sources et
la verification de chaque preuve de domaine public (`ART-3`) qui suivront restent a affecter a un
developpeur par `sofia-sm` une fois l'`ADR` ecrit, sur le meme modele que le palier 2.

**Ce que je ne fais pas.** Je n'ecris pas ici une story en voix d'enfant pour ce chantier : c'est un
chantier de sourcing technique, transverse, sans contenu pedagogique a formuler du point de vue de
l'eleve, exactement comme le palier 2 n'en a jamais porte une. La story de construction du contenu
(quelles oeuvres, sur quelles lignes) restera a ecrire une fois le corpus sourcee, au moment ou une
ligne d'histoire des arts ou d'arts plastiques sera effectivement engagee.

## 6. Amendement du 2026-10-03 (meme jour) : l'`ADR` existait deja, T2 a T5 entrent au perimetre

La section 5 engage `theo-archi` a "rendre l'`ADR` et sa regle ArchUnit avant la cloture de s49",
sur la foi de son propre avis de s48 (section 11) qui affirmait l'`ADR` manquant. **Cette
affirmation est fausse.** `ADR-0082` (Accepte, 2026-09-05, commit `4f594d64`) existe depuis la
decision meme de `DEC-0077` et repond deja aux quatre questions de sa section 4 ; sa regle
(`ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest`, 18 tests verts)
tourne. `theo-archi` corrige son propre avis dans celui de s49
(`squad/rapports/avis-faisabilite-s49-quatre-stories.md`, section 7 ; `DEC-0105`, section 2 bis).
La borne de forme que la section 5 lui fixait est donc deja tenue, depuis le 2026-09-05.

**Consequence sur le perimetre : le travail restant n'est plus architectural, il est concret.**
`theo-archi` le decrit en deux lots (`ADR-0082`, decision 7 et taches ; avis de s49, section 7) :

- **T2, T3, T4** : brancher la resolution d'un identifiant `oeuvre-` dans les garde-fous et le
  gabarit de rendu, poser un plafond de pixels. Environ deux jours de developpeur, aucune decision
  d'architecture neuve (`DecouplageMecaniqueContenuTest` et les regles de couches n'ont rien de
  neuf a interdire).
- **T5** : constituer le corpus commence par le choix des oeuvres par `nadia-prof` (`DEC-0077`
  section 4). Un developpeur seul ne peut pas l'engager.

**Je decide : T2 a T4 et T5 entrent au perimetre de s49, hors chiffrage en lots**, sur le meme
precedent deja applique en section 5 a l'`ADR` lui-meme (palier 2, `DEC-0074` section 1.2 :
chantier porte en developpeur affecte, jamais en unite comptee contre la borne de 5,0 de
`nadia-prof`). Le perimetre chiffre de la section 1 (2,5/5,0, marge 2,5) ne bouge pas : T2 a T4 ne
produisent aucun contenu pedagogique a auditer, et T5 est un choix d'oeuvres par `nadia-prof`, pas
un Jugement de faisabilite ni un Done pedagogique - ni l'un ni l'autre ne consomme la borne de
lots.

**Ce que je nomme sans le chiffrer** : T5 consomme du temps reel de `nadia-prof`, hors de sa borne
d'audit en lots - point que `theo-archi` releve explicitement (avis de s49, section 7). Ne pas le
chiffrer en lots n'est pas le declarer gratuit : `sofia-sm` en tient compte au planning comme
d'une charge de `nadia-prof` distincte des 2,5/5,0 d'instance.

**Ce que je ne fais pas.** Je n'affecte aucun developpeur nominativement ni aucun ordre entre T5
et T2-T4 : `sofia-sm` et `theo-archi` le font au planning (section 7, point 6 de ce document,
inchange), y compris la question de savoir si T2-T4 peuvent commencer sur un materiau provisoire
avant que T5 ne livre le materiau reel.

## 7. Correction factuelle de `DEC-0106`, section 4

`DEC-0106` (section 4) cite "la borne de longueur du texte support que `DEC-0104` section 1.1
signale non posee". C'etait exact le 2026-10-02 au matin ; `nadia-prof` l'a posee le jour meme
(`LON-CE1-3`, `LON-CE1-4`, `ARBITRAGE-s47-longueur-du-texte-support-au-ce1.md`), et elle l'a
signale elle-meme comme une reference perimee d'un jour
(`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section 0.1, routee a `camille-po`). Correction
apportee directement dans `DEC-0106` (note ajoutee en fin de section 4, datee de ce jour) : ce n'est
pas une nouvelle decision, c'est une correction factuelle d'un etat qui a change le jour meme de
l'ecriture du document qu'elle corrige.

## 8. Ce que je decide

1. Le perimetre de s49 est gele : `PRO-CE1-R1`/`PRO-CE1-R2`, `ANG-CP-R3` + l'item non leve
   d'`ANG-CP-R1`/`ANG-CP-R2`, la reserve sans identifiant d'`EMC-CP-14` (item 7), `FRA-CE1-01` -
   total 2,5/5,0 pour l'instance unique de `nadia-prof` (Done pedagogique), marge 2,5.
2. Le tarif des trois corrections differees est de 0,5 chacune, sur le precedent `PAR-10` deja
   applique par `DEC-0106` (section 2).
3. `FRA-CE1-01` est la premiere ligne construite du vivier francais CE1, tarifee 1,0 (`nadia-prof`
   source du referentiel), sur le verdict deja ecrit (`GRC-CE1-1` a `GRC-CE1-3`). `FRA-CE1-02` reste
   au vivier ouvert, non engagee ce sprint.
4. Le chantier de sourcing du corpus d'oeuvres du domaine public entre au perimetre de s49, hors
   chiffrage de la borne d'audit. `ADR-0082` existe deja (section 6) : ce qui reste, T2 a T4
   (branchement technique, environ deux jours de developpeur) et T5 (choix des oeuvres par
   `nadia-prof`), entre lui aussi au perimetre de s49, hors chiffrage en lots, sur le precedent du
   palier 2 (section 6). Aucun Done pedagogique, aucun tarif en lots ; T5 consomme du temps de
   `nadia-prof` hors de sa borne d'audit, nomme sans etre chiffre.
5. La reference perimee de `DEC-0106`, section 4, est corrigee directement dans ce document (section
   7 ci-dessus).
6. Les developpeurs restent a affecter nominativement par `sofia-sm` au planning, une fois cette
   decision lue - y compris celui de T2 a T4.

## 9. Ce que cette decision ne fait pas

- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` et ne ferme aucune ligne :
  les statuts ne bougent qu'au verdict de `nadia-prof`.
- Elle ne prononce aucun Done, technique ou pedagogique.
- Elle ne rend pas l'avis de faisabilite technique dedie a s49 que `theo-archi` a deja rendu sur les
  trois corrections, sur `FRA-CE1-01` et sur le chantier de sourcing
  (`squad/rapports/avis-faisabilite-s49-quatre-stories.md`) : cette decision gele le perimetre
  produit, elle ne rejuge pas la faisabilite technique.
- Elle n'ecrit aucun `ADR` ni aucune regle ArchUnit : `ADR-0082` est deja ecrit et n'est pas a
  reecrire (section 6).
- Elle ne fixe l'affectation nominative d'aucun developpeur ni d'aucune instance, ni l'ordre entre
  T5 et T2-T4 : travail du planning de s49, par `sofia-sm` et `theo-archi`.
