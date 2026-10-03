# Rapport de sprint s48 - problemes a une ou deux etapes en mathematiques CE1, cinq corrections
differees de s47, reprise de SOP-CE1-R1 (instance A), instruction du referentiel francais CE1
(instance B)

- **Duree** : 2026-10-03 09:12:00 -> 2026-10-03 14:21:00 (UTC+02:00), 5 h 09 min.
- **Objectif gele** (`DEC-0106`, Acceptee, 2026-10-03) : instance A (Done pedagogique) -
  `MAT-CE1-08`, cinq corrections differees de s47 (`TAB-CE1-R1`, `AUT-CP-R1`,
  `ANG-CP-R1`/`ANG-CP-R2`, les deux reserves d'`EMC-CP-14`, les deux reserves d'`EMC-CM1-10`),
  `SOP-CE1-R1`. Budget de sprint opposable 5,0, total engage **4,0/5,0**, marge 1,0 non allouee.
  Instance B (instruction, hors chiffrage) - Jugement de faisabilite du referentiel de francais CE1,
  26 lignes.

## L'increment livre, en une phrase

Sept paquets sont audites par `nadia-prof` sans aucun refus - un paquet neuf
(`ce1-problemes-etapes`, `MAT-CE1-08`) et six corrections sur cinq paquets deja fermes en s47 et s46
- avec quatre Done pedagogiques sans reserve ouverte (`TAB-CE1-R1`, `AUT-CP-R1`, `EMC-CM1-10`,
`SOP-CE1-R1`) et trois sous reserve (`MAT-CE1-08`, `ANG-CP-R1`/`ANG-CP-R2`, `EMC-CP-14`), pendant
qu'une seconde instance de `nadia-prof` instruit pour la premiere fois les 26 lignes du referentiel
de francais CE1 (24 atteignables `couvert-valide`, 2 plafonnees `en cours`, zero non couvrable) sans
prononcer aucun Done. **La ligne `MAT-CE1-08` reste `non couvert` dans
`squad/programme/MATRICE-couverture.md` a ce jour** : son Done pedagogique est prononce, mais le
geste de cloture de la matrice, qui appartient a `camille-po`, n'a pas encore ete execute - la
couverture du programme reste donc **118/182 (64,8 %)**, inchangee depuis s47.

## Les decisions du sprint

| Decision | Numero | Statut | Objet |
|---|---|---|---|
| `DEC-0105` | Produit | Acceptee | Aucune candidate CM1 n'est ouverte a l'instance B d'instruction de s48 ; correction du statut perime de `DEC-0075` dans la matrice (tranchee depuis `DEC-0077`, le chantier de sourcing du corpus d'oeuvres reste a engager) |
| `DEC-0106` | Produit | Acceptee | Ouverture du perimetre de s48 : `MAT-CE1-08` (1,0), les cinq corrections differees de s47 (0,5 chacune), `SOP-CE1-R1` (0,5), tarif d'une correction differee fixe a 0,5 sous le precedent `PAR-10` ; instance B d'instruction du referentiel francais CE1, hors chiffrage |

`DEC-0106` section 3 corrige, dans son propre texte, une description perimee de l'arbitrage de
`SOP-CE1-R1` (signale par `theo-archi`, avis de faisabilite, Q3) : l'arbitrage pedagogique etait deja
rendu en s47, la decision le decrivait encore comme a venir. Aucune autre `DEC` ni aucun `ADR` n'est
cree ou amende par s48.

**Anomalie constatee, non tranchee par cette cloture** (`review.md`, section 4) : les sept
developpements sont commites avant `DEC-0106` et avant l'avis de faisabilite de `theo-archi` qui en
confirme la faisabilite technique. Aucune decision de capacite ne m'appartient en propre sur ce
sprint au-dela de ce constat, traite comme lecon de process dans `retro.md`.

## Etat des deux Done

### Technique - rapport de `yanis-qa`, les quatre jobs de la CI reproduits un par un

`bash outils/mvn-jdk21.sh -B clean verify` : **BUILD SUCCESS**, 6 modules sur 6, 9 min 01 s, JDK de
compilation 21.0.10. **1954 tests (1948 unitaires + 6 integration), 0 echec** - +16 depuis s47,
ecart explique sans reste par trois fichiers de `adapter-content` (paquet neuf `MAT-CE1-08` +12,
`SOP-CE1-R1` +3, `TAB-CE1-R1` +1). **Zero ligne de `src/main/java` touchee, ArchUnit inchange.** Les
quatre jobs de la CI (`build`, `prefixes`, `chemins`, `outils`) sont **verts, chacun reproduit
localement**, aucun deduit de `mvn verify` seul. Sept commits, sept prefixes d'agent conformes.
Detail complet : `review.md`, section 2.

### Pedagogique - instance A, sept Done, zero refus ; instance B, instruction seule

| Story | Verdict | Reserves fermees | Reserves ouvertes |
|---|---|---|---|
| `MAT-CE1-08` | VALIDE SOUS RESERVE | - | `PRO-CE1-R1`, `PRO-CE1-R2` |
| `TAB-CE1-R1` | VALIDE | `TAB-CE1-R1` | aucune |
| `AUT-CP-R1` | VALIDE | `AUT-CP-R1` | aucune |
| `ANG-CP-R1`/`ANG-CP-R2` | VALIDE SOUS RESERVE | `ANG-CP-R1` (item 10), `ANG-CP-R2` (item 1) | `ANG-CP-R1` (item 12), `ANG-CP-R2` (item 2), `ANG-CP-R3` (neuve) |
| `EMC-CP-14` | VALIDE SOUS RESERVE | deux reserves de s47 | une reserve neuve, sans identifiant, sur l'indice de l'item 7 |
| `EMC-CM1-10` | VALIDE | deux reserves de s47 | aucune |
| `SOP-CE1-R1` | VALIDE | `SOP-CE1-R1` | aucune |

**Zero refus pedagogique sur les sept stories**, a la difference de s46 et s47 qui en portaient
chacun trois. Quatre reserves restent ouvertes a la cloture (`PRO-CE1-R1`, `PRO-CE1-R2`, `ANG-CP-R3`,
plus un item non leve de `ANG-CP-R1` et un de `ANG-CP-R2`, plus la reserve sans identifiant
d'`EMC-CP-14`) : **aucune n'a encore de story dans `docs/backlog/backlog.md` a ce jour**, a la
difference des corrections differees de s47 qui etaient deja ecrites au moment de la cloture.
`camille-po` reste seule a en faire des stories.

**Instance B** (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`) : 26 lignes instruites, **26 ACCEPTE SOUS
CONDITIONS** (24 `couvert-valide` atteignable, 2 plafonnees `en cours`), zero `non couvrable`. Aucun
Done pedagogique prononce - ce n'est pas son objet. Cette instruction leve le risque signale par le
planning et `DEC-0105` (vivier instruit hors mathematiques CE1 epuise) : le francais CE1 devient une
candidate disponible pour un sprint ulterieur.

## Couverture du programme

**118/182 (64,8 %), inchangee depuis la cloture de s47.** `MAT-CE1-08` porte un Done pedagogique
VALIDE SOUS RESERVE mais reste `non couvert` dans la matrice : le geste de bascule de statut,
qui appartient a `camille-po`, n'est pas execute a ce jour, a la difference du patron de s47 (cloture
de la matrice au commit `506c5137`). Si ce geste est fait, la couverture passerait a 119/182
(65,4 %) - je ne l'anticipe pas comme un fait acquis. Verifie par moi sur
`squad/programme/MATRICE-couverture.md`, qui ne porte aucune note de sprint s48.

**Amendement du 2026-10-03 (`camille-po`)** : le geste signale ci-dessus comme non execute l'a ete
dans la foulee de cette review, avant la poussee des commits de s48 (`dcae5ce0`). La couverture est
donc **119/182 (65,4 %)** a la cloture effective de s48, pas 118/182.

## Obstacles resolus et encore ouverts

**Resolu dans le meme document** : la description perimee de l'arbitrage de `SOP-CE1-R1` dans
`DEC-0106` section 3, corrigee par `camille-po` au signalement de `theo-archi` avant la cloture de
cette section de la decision elle-meme.

**Encore ouverts, aucun bloquant pour le Done technique ou pedagogique prononce** :

- La bascule de statut de `MAT-CE1-08` et la note de cloture de s48 dans
  `squad/programme/MATRICE-couverture.md`, geste de `camille-po` non execute a ce jour.
- Quatre reserves de Done pedagogique sans story au backlog (`PRO-CE1-R1`, `PRO-CE1-R2`,
  `ANG-CP-R3`, les items non leves de `ANG-CP-R1` et `ANG-CP-R2`, la reserve sans identifiant
  d'`EMC-CP-14`).
- La reference perimee a la borne de longueur du texte support dans `DEC-0106` et le planning,
  signalee par `nadia-prof` (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section 0.1), non corrigee
  par cette cloture.
- Le chantier de sourcing du corpus d'oeuvres du domaine public (`DEC-0077`), refuse explicitement
  pour s48 par `theo-archi`, qui demande son inscription a s49.
- L'anomalie de chronologie entre les sept developpements et `DEC-0106`/l'avis de faisabilite
  (`review.md`, section 4), et l'absence d'affectation nominative ecrite dans `planning.md` malgre
  un sprint entierement livre : deux constats de process, traites dans `retro.md`, sans correction
  retroactive d'un fichier deja commite.

Tableau complet, avec routage par categorie : `review.md`, section 7.

## Prochain perimetre

**Non propose par cette cloture** : proposer ou arbitrer un perimetre n'est pas le role de cette
cloture, il appartient a `camille-po`. Deux candidates disponibles, non engagees : le referentiel de
francais CE1 (24 lignes `couvert-valide` atteignable, instruit par cette instance B) et les quatre
reserves de Done pedagogique encore sans story (ci-dessus). Le chantier de sourcing du corpus
d'oeuvres du domaine public est explicitement demande par `theo-archi` pour le perimetre de s49.

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s48`, fenetre 2026-10-03 07:12 -> 2026-10-03 12:21 UTC
(horodatage de debut du planning jusqu'au dernier commit du sprint). Detail complet dans
`RAPPORT-tokens.md` (ce dossier) et cumule dans `squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 102 | 204 | 45 044 | 841 310 | 6 927 173 |
| `sofia-sm` | claude-sonnet-5 | 191 | 382 | 84 738 | 2 325 005 | 30 698 309 |
| `theo-archi` | claude-opus-5-5 | 143 | 286 | 47 912 | 467 001 | 21 202 529 |
| `marc-dev` | claude-sonnet-5 | 240 | 480 | 126 698 | 1 814 765 | 60 988 082 |
| `lea-dev` | claude-sonnet-5 | 95 | 190 | 20 957 | 400 033 | 9 243 957 |
| `yanis-qa` | claude-sonnet-5 | 83 | 166 | 21 378 | 302 241 | 4 995 729 |
| `relecteur-pedagogique` | claude-sonnet-5 | 72 | 144 | 14 848 | 387 660 | 6 945 458 |
| `nadia-prof` | claude-opus-5-5 | 305 | 610 | 196 480 | 1 228 072 | 47 930 458 |
| orchestrateur (fil principal) | claude-sonnet-5 | 119 | 238 | 140 476 | 248 513 | 17 368 271 |
| **Total** | | **1350** | **2 700** | **698 531** | **8 014 600** | **206 299 966** |

Aucune invocation mesuree sur cette fenetre pour `ines-design` : aucun travail de design n'etait du
sur ce sprint (perimetre entierement textuel). Les quatre compteurs sont volontairement separes (voir
`RAPPORT-tokens.md`) : les additionner donnerait un classement faux, le cache lu depassant couramment
l'output de deux ordres de grandeur.
