# Review - s48 - problemes a une ou deux etapes en mathematiques CE1, cinq corrections differees de
s47, reprise de SOP-CE1-R1 (instance A), instruction du referentiel francais CE1 (instance B)

- **Horodatage de debut** (`planning.md`) : 2026-10-03 09:12:00 (UTC+02:00).
- **Horodatage de fin** : 2026-10-03 14:21:00 (UTC+02:00, horodatage du dernier commit du sprint,
  `4263155b`, rapport qualite de `yanis-qa`).
- **Duree effective** : 5 h 09 min.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Pas de worktree dedie** : document ecrit directement sur `round-two`, meme pattern que
  `planning.md`. Verifie avant d'ecrire : `git status --short` rend vide, `git log -1 --format=%H`
  rend `4263155b1a37c392e41f3f39734a68fb8f4ac2f5`.
- **Arbre revu** : `round-two` a `4263155b`.

---

## 1. L'increment livre, en une phrase

Une ligne du programme passe de `non couvert` a `couvert-valide` pedagogiquement pretee (`MAT-CE1-08`,
problemes a une ou deux etapes en mathematiques CE1, Done pedagogique **VALIDE SOUS RESERVE**
prononce, mais la bascule de statut dans `squad/programme/MATRICE-couverture.md` n'est pas encore
executee par `camille-po` - section 6), six reserves ouvertes a la cloture de s47 sont reprises sur
cinq paquets deja fermes (`TAB-CE1-R1`, `AUT-CP-R1`, `ANG-CP-R1`/`ANG-CP-R2`, les deux reserves
d'`EMC-CP-14`, les deux reserves d'`EMC-CM1-10`) plus une sixieme reprise routee hors chiffrage de
s47 (`SOP-CE1-R1`), et le referentiel de francais CE1 (26 lignes) recoit son premier Jugement de
faisabilite complet, instruit par une seconde instance de `nadia-prof` qui ne prononce aucun Done.
Sept paquets audites, **zero refus pedagogique cette fois** (contraste avec s46 et s47, qui portaient
chacun trois refus sur cinq lots) ; les reserves ouvertes a la cloture de s47 sont en net **recul
partiel** : certaines fermees, d'autres rouvertes pour partie, deux reserves neuves apparaissent.

## 2. Le rapport qualite de `yanis-qa` (resume)

`rapport-qualite.md` (mesure directe sur le checkout principal, arbre `round-two` a `bc1e7835`, base
de sprint `bfdc7fee0`) : `bash outils/mvn-jdk21.sh -B clean verify` vert du premier coup (9 min 01 s),
**1954 tests (1948 unitaires + 6 integration)**, +16 depuis s47, ecart explique sans reste par trois
fichiers de `adapter-content` (`PaquetCe1ProblemesEtapesTest` +12, `PaquetCe1SoustractionsPoseesTest`
+3, `PaquetCe1TablesMultiplicationTest` +1 ; les quatre autres stories ne touchent que du YAML,
aucune methode de test neuve). **Zero ligne de `src/main/java` touchee, ArchUnit inchange** (zero
`static final ArchRule` ajoutee ou retiree). **Les quatre jobs de la CI** (`build`, `prefixes`,
`chemins`, `outils`) sont verts, **reproduits localement un par un**, aucun deduit de `mvn verify`
seul - conforme a l'amendement de `CLAUDE.md` du 2026-09-08. Sept commits, sept prefixes d'agent
conformes. **Conclusion de `yanis-qa` : "La porte technique de s48 est verte."**

Quatre fichiers Markdown (`docs/backlog/backlog.md`, `REGISTRE-identifiants.md`, `DEC-index.md`,
`MATRICE-couverture.md`) portaient des modifications non commitees au moment de la mesure de
`yanis-qa` ; ils ont ete commites dans des commits ulterieurs du sprint (`374fed64`, `9800adc9`,
`ac391c03`), hors du perimetre mesure par `yanis-qa`, qui le dit explicitement et n'en deduit rien.

## 3. L'avis de faisabilite de `theo-archi` (resume)

`avis-faisabilite-s48-sept-stories.md`, fonde sur `round-two` a `bfdc7fee` plus les modifications non
commitees de `squad/` : `mvn verify` vert (1938 tests, base d'avant-sprint), ArchUnit a 36/35, aucune
des sept stories ne touche `src/main/java`, aucun `ADR` du. Sept verdicts "faisable en l'etat" ou
"faisable, test de paquet a adapter" (`SOP-CE1-R1` seule exige une adaptation du test de paquet pour
encoder la cinquieme forme de distracteur). Quatre questions routees (Q1, Q2 a `nadia-prof`,
confirmees sans ecart par les fiches de conformite ; Q3 a `camille-po`, **corrige dans `DEC-0106`
section 3 au meme diff** : la decision decrivait encore l'arbitrage de `SOP-CE1-R1` comme "a venir"
alors qu'il etait deja rendu depuis s47, et la correction a pour consequence que la reprise ne
chiffrait qu'une seule sortie possible, pas deux ; Q4, non bloquante, sur le volume reel
d'`EMC-CM1-10`, cinq items sur douze et non trois, tarif inchange). **Theo-archi refuse explicitement
d'engager a s48 le chantier de sourcing du corpus d'oeuvres du domaine public** (`DEC-0077`, en
souffrance depuis s29, hors perimetre gele par `DEC-0106`), et demande son inscription a s49.

## 4. Anomalie de chronologie constatee entre les artefacts du sprint

Je la rapporte parce qu'elle touche directement la gouvernance par le depot, pas pour la trancher.
L'ordre chronologique reel des commits (`git log --reverse`) est :

| Heure (UTC+02:00) | Commit | Contenu |
|---|---|---|
| 11:28 - 12:18 | `d08d6bbe`, `b29c900b`, `358b3d24`, `f99a47a9`, `4b6a6294`, `b77cabeb`, `bc1e7835` | les sept stories de developpement |
| 14:18 | `374fed64` | `DEC-0105` |
| 14:19 | `9800adc9` | `DEC-0106`, ouverture formelle du perimetre de s48 |
| 14:20 | `16320c8b` | avis de faisabilite de `theo-archi` |
| 14:20 | `ac391c03` | instruction francais CE1 et Done pedagogique des sept stories, par `nadia-prof` |
| 14:21 | `b6150dfb` | `planning.md` (ce document, dans son etat final commite) |
| 14:21 | `4263155b` | rapport qualite de `yanis-qa` |

**Les sept developpements sont commites avant `DEC-0106`, qui gele formellement le perimetre de s48,
et avant l'avis de faisabilite de `theo-archi` qui en confirme la faisabilite technique.** Je ne
peux pas, depuis le seul depot, distinguer un risque reel (du code ecrit avant que son perimetre ne
soit arbitre) d'un artefact d'assemblage de ce sprint (un travail conduit sur des criteres deja
ecrits avec precision dans le backlog - voir planning.md section 3.4 : *"chaque story cite deja, mot
pour mot, la forme que nadia-prof attend en remplacement"* - et sur une condition pedagogique
(`MAT-CE1-08`) deja arbitree depuis s43). Je le signale explicitement plutot que de le laisser
silencieux : **traite en detail dans `retro.md`, section 1.**

**Second ecart, plus net.** `planning.md`, dans son texte tel que commite a 14:21 (donc apres l'avis
de `theo-archi` et apres le Done pedagogique de `nadia-prof`), se cloture encore sur *"Je reviens
affecter nominativement les developpeurs et les deux instances de `nadia-prof` des que l'avis de
faisabilite de `theo-archi` existe dans le depot"* - alors que cet avis existait deja une minute
avant son propre commit, et que les sept stories etaient deja developpees et auditees. **Aucun
document du depot ne porte l'affectation nominative finale** des sept stories : elle n'est lisible
qu'en deduction des prefixes de commit (`marc-dev` sur `MAT-CE1-08`, `lea-dev` sur `TAB-CE1-R1`,
`AUT-CP-R1`, `ANG-CP-R1`/`ANG-CP-R2`, `sofia-sm` casquette dev sur `EMC-CP-14`, `EMC-CM1-10`,
`SOP-CE1-R1`), jamais d'une decision ecrite. Conforme au principe de gouvernance ("ce qui n'est pas
ecrit dans le depot n'existe pas"), je ne la fabrique pas ici pour combler ce manque : je le
constate.

## 5. Le Done pedagogique - instance A (sept stories), rapporte par cette review, pas prononce par
elle

| Ligne / story | Verdict final s48 | Reserves fermees | Reserves ouvertes a la cloture |
|---|---|---|---|
| `MAT-CE1-08` (paquet neuf `ce1-problemes-etapes`) | **VALIDE SOUS RESERVE**, premier passage | - | `PRO-CE1-R1` (famille (c) non plausible, items 9-11), `PRO-CE1-R2` (item 8 porte une vente) |
| `TAB-CE1-R1` (paquet `ce1-tables-multiplication`, `MAT-CE1-07`) | **VALIDE**, reserve levee | `TAB-CE1-R1` | aucune |
| `AUT-CP-R1` (paquet `cp-qlm-autrefois-aujourdhui`, `QLM-CP-15`) | **VALIDE**, reserve levee | `AUT-CP-R1` | aucune |
| `ANG-CP-R1`/`ANG-CP-R2` (paquet `cp-anglais-culture`, `LVE-CP-06`) | **VALIDE SOUS RESERVE** | `ANG-CP-R1` sur l'item 10, `ANG-CP-R2` sur l'item 1 | `ANG-CP-R1` reste ouverte sur l'item 12, `ANG-CP-R2` reste ouverte sur l'item 2, reserve neuve `ANG-CP-R3` (item 10, le mot *statue* ne figure que dans la bonne reponse) |
| `EMC-CP-14` (paquet `cp-emc-alerte`) | **VALIDE SOUS RESERVE** | les deux reserves du second passage de s47 (lieu de l'item 7, vocabulaire de l'item 11) | reserve neuve sans identifiant dedie, ouverte par `nadia-prof` elle-meme sur sa propre forme (l'indice de l'item 7 nomme la bonne reponse) |
| `EMC-CM1-10` (paquet `cm1-emc-vote-election`) | **VALIDE**, deux reserves levees | les deux reserves reconduites de s47 (distracteur hors categorie items 3-6, question mal formee item 11) | aucune |
| `SOP-CE1-R1` (paquet `ce1-soustractions-posees`, `MAT-CE1-05`) | **VALIDE**, reserve levee | `SOP-CE1-R1` | aucune |

**Bilan du Done pedagogique de l'instance A** : sept Done pedagogiques prononces, zero refus,
**quatre VALIDE sans reserve ouverte** (`TAB-CE1-R1`, `AUT-CP-R1`, `EMC-CM1-10`, `SOP-CE1-R1`) et
**trois VALIDE SOUS RESERVE** (`MAT-CE1-08`, `ANG-CP-R1`/`ANG-CP-R2`, `EMC-CP-14`). Six reserves
ouvertes a la cloture de s47 sont fermees, quatre restent ou s'ouvrent : `PRO-CE1-R1`, `PRO-CE1-R2`
(neuves, `MAT-CE1-08`), `ANG-CP-R3` (neuve, `LVE-CP-06`), plus l'item non leve de `ANG-CP-R1` et de
`ANG-CP-R2` chacune (le meme identifiant, pas un identifiant neuf), plus la reserve sans identifiant
sur l'indice de l'item 7 d'`EMC-CP-14` - **matiere a router vers le backlog, sur le meme modele que
les corrections differees de s47, sans qu'aucune story ne soit encore ecrite a ce jour** (section 6).

## 6. L'instance B - instruction du referentiel de francais CE1, aucun Done pedagogique

`ARBITRAGE-PREALABLE-s48-francais-ce1.md` : `nadia-prof` n'a joue aucun jar - *"il n'y a rien a
jouer"*, aucun paquet de francais CE1 n'existe - et instruit les 26 lignes (`FRA-CE1-01` a
`FRA-CE1-26`) par lecture du referentiel, des arbitrages de longueur deja poses (`LON-CE1-1` a
`LON-CE1-4`) et des paquets voisins deja servis. **Verdict : 26/26 ACCEPTE SOUS CONDITIONS, zero NON
COUVRABLE** - 24 lignes au plafond `couvert-valide` atteignable, 2 plafonnees `en cours`
(`FRA-CE1-03` mots frequents, reconnaissance immediate non servie ; `FRA-CE1-21` mots invariables,
ecrire sans modele non servi). Trois corrections prealables ecrites par `nadia-prof` elle-meme avant
de s'appuyer sur le dossier : la borne de longueur du texte support, que `DEC-0106` et le planning
citaient encore comme "non posee", l'avait deja ete la veille (`LON-CE1-3`, `LON-CE1-4`,
`ARBITRAGE-s47-longueur-du-texte-support-au-ce1.md`) - `nadia-prof` ne la repose pas, et route la
correction de cette reference perimee a `camille-po` ; les cinq attendus `FRA-CE1-27` a `FRA-CE1-31`,
deja sortis du referentiel instruit le 2026-10-02 (`non couvrable en l'etat`), ne sont pas
reinstruits ; deux nuances du garde-fou `LON-CE1-2`, verifiees dans le code plutot que supposees,
sont documentees pour les developpeurs futurs. 86 identifiants neufs alloues (`LON-CE1-5`,
`TRV-CE1-1` a `TRV-CE1-7`, 26 x 3 conditions par ligne), 27 prefixes neufs, registre porte de 825/131
a 911/158.

**Ce que cette instruction change pour la suite** : le risque signale par le planning (section 6,
point 1 ; `DEC-0105`) - le vivier instruit hors mathematiques CE1 etait epuise - est leve : 24 lignes
de francais CE1 sont desormais atteignables `couvert-valide` sans arbitrage prealable supplementaire.
Aucune de ces 26 lignes ne bouge dans `MATRICE-couverture.md` par ce seul document, qui le dit
lui-meme.

## 7. Tableau des obstacles du sprint

| Obstacle | Categorie | Route a | Statut a la cloture |
|---|---|---|---|
| `DEC-0106` section 3 decrivait l'arbitrage de `SOP-CE1-R1` comme "a venir" alors qu'il etait deja rendu en s47 (signale par `theo-archi`, Q3 de l'avis de faisabilite) | Produit / exactitude d'une decision | `camille-po` | **Resolu dans le meme document** - `DEC-0106` section 3 porte sa propre correction, datee du 2026-10-03, avant sa cloture |
| Reference perimee a la borne de longueur du texte support, citee "non posee" par `DEC-0106` et le planning alors qu'elle l'etait depuis la veille | Produit / exactitude d'une reference | `camille-po` | **Signale par `nadia-prof`**, non corrige par cette review (section 6) - **ouvert** |
| Chronologie des commits : les sept developpements precedent `DEC-0106` et l'avis de faisabilite de `theo-archi` (section 4) | Process / sequencement du sprint | moi-meme, `camille-po` | **Signale, non tranche** - traite en lecon de process dans `retro.md`, section 1 |
| Aucune affectation nominative finale ecrite dans `planning.md` malgre un sprint entierement livre et audite (section 4) | Process / tenue de la ceremonie de planning | moi-meme | **Ouvert** - ne peut pas etre corrige par un ajout a `planning.md` (regle : jamais a la suite d'un fichier existant) ; a traiter comme lecon de process, `retro.md` section 1 |
| `squad/programme/MATRICE-couverture.md` ne reflete pas encore le Done pedagogique de `MAT-CE1-08` (reste `non couvert`), et ne porte aucune note de cloture de s48 | Produit / tenue de la matrice | `camille-po` | **Ouvert** - geste de cloture de `camille-po` non execute a ce jour, a la difference du patron de s47 (`506c5137`) |
| Six reserves fermees, quatre restent ouvertes : `PRO-CE1-R1`, `PRO-CE1-R2`, `ANG-CP-R3` (neuves), l'item non leve de `ANG-CP-R1` (item 12) et de `ANG-CP-R2` (item 2), la reserve sans identifiant sur l'indice de l'item 7 d'`EMC-CP-14` | Pedagogie / correction differee de contenu, non bloquante | `camille-po` (stories a ecrire), developpeur a affecter par `sofia-sm` | **Ferme (amendement du 2026-10-03, `camille-po`)** - trois stories ecrites dans `docs/backlog/backlog.md` (`dcae5ce0`), groupees par paquet ; developpeur encore a affecter |
| `EMC-CM1-10` touche en realite cinq items sur douze et non les trois que `DEC-0106` section 2 decrivait (Q4 de l'avis de faisabilite) | Produit / precision d'un chiffrage | `camille-po` | **Signale, non bloquant** - tarif confirme inchange par l'avis lui-meme |
| Le chantier de sourcing du corpus d'oeuvres du domaine public (`DEC-0077`), en souffrance depuis s29, refuse explicitement pour s48 par `theo-archi` | Technique / architecture, charge | `theo-archi` (refus ecrit), `camille-po` (inscription a s49) | **Ouvert, hors perimetre de s48** - `theo-archi` demande son inscription explicite au perimetre de s49 |

## 8. La couverture du programme

**118/182 (64,8 %), inchangee depuis la cloture de s47**, verifiee par moi sur
`squad/programme/MATRICE-couverture.md` : la ligne `MAT-CE1-08` y est toujours `non couvert` malgre
son Done pedagogique **VALIDE SOUS RESERVE** prononce par `nadia-prof` (section 5), et aucune note de
sprint s48 n'y a ete ajoutee. Cette review ne deplace aucun statut - ce n'est pas son role, c'est
celui de `camille-po`, qui ne l'a pas encore execute a ce jour (section 7). Si et quand ce geste est
fait, la couverture passerait a 119/182 (65,4 %) : je ne l'anticipe pas comme un fait acquis.

**Amendement du 2026-10-03 (`camille-po`)** : le geste est execute dans la foulee de cette review,
avant la poussee des commits de s48 (`dcae5ce0`). La couverture a la cloture effective de s48 est
**119/182 (65,4 %)**.

## 9. Ce que cette review ne fait pas

- Elle ne prononce aucun Done pedagogique : les sept verdicts de `nadia-prof` (section 5) et
  l'instruction de l'instance B (section 6) sont deja ecrits ; cette review les rapporte, elle ne les
  rend pas.
- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md`, y compris celui de
  `MAT-CE1-08` : ce geste appartient a `camille-po` et n'est pas execute a ce jour (section 7, 8).
- Elle n'ecrit aucune story de correction pour les quatre reserves encore ouvertes (section 5, 7) :
  elles sont a router a `camille-po`, sur le meme modele que les corrections differees de s47.
- Elle ne tranche pas l'anomalie de chronologie (section 4) ni l'absence d'affectation nominative
  ecrite : elle les constate et les route en lecon de process a `retro.md`.
- Elle n'engage ni ne refuse le chantier de sourcing du corpus d'oeuvres du domaine public : ce
  travail est deja fait par `theo-archi` (section 3, section 7).

## Decisions prises

**Aucune `DEC` ni `ADR` n'est creee ou amendee par cette review.** La seule decision du perimetre de
s48 est `DEC-0106`, deja ecrite avant l'ouverture des travaux de developpement (section 1, section
4) ; elle n'est pas reprise une seconde fois ici. Aucune decision ne m'appartient en propre au-dela
de celles deja ecrites dans `planning.md` - dont l'affectation nominative finale, qui n'y a jamais
ete ecrite (section 4, section 7), un manque que je constate sans le combler par cette review.
