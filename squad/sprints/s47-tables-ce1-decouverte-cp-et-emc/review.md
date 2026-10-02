# Review - s47 - tables de multiplication CE1, modes de vie autrefois et culture anglophone au CP
(instance A), gestes d'alerte et sens du vote en EMC CP/CM1 (instance B)

- **Horodatage de debut** (`planning.md`) : 2026-10-02 16:17:00 (UTC+02:00).
- **Horodatage de fin** : 2026-10-02 23:03:35 (UTC+02:00, horodatage du commit de fusion final
  `25860e82`, cloture de la matrice par `camille-po`, commit `506c5137`).
- **Duree effective** : 6 h 46 min 35 s.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Worktree** : `../recreo-s47-sofia-cloture`, branche `s47-sofia-cloture`, fondee sur
  `round-two` a `25860e82` (dernier commit sur `round-two` au moment de l'ouverture de cette
  ceremonie). Lignee verifiee avant toute ecriture : `bash outils/verifier-lignee.sh round-two`
  -> *"le worktree sur la branche 's47-sofia-cloture' descend de la lignee de round-two (ancetre
  commun trouve dans son historique)"*.
- **Arbre revu** : `round-two` a `25860e82`.

---

## 1. L'increment livre, en une phrase

Cinq lignes du programme passent de `non couvert` a `couvert-valide` (`MAT-CE1-07` tables de
multiplication CE1, `QLM-CP-15` modes de vie autrefois et aujourd'hui CP, `LVE-CP-06` decouverte
culturelle anglophone CP, `EMC-CP-14` donner l'alerte CP, `EMC-CM1-10` sens du vote et de la
representation CM1), apres un episode de correction d'orthographe sur un distracteur ("oeufs") qui
a mene a corriger un harnais de test plutot que le contenu, et apres trois refus pedagogiques
reels sur trois des cinq lots, chacun leve par une ou deux corrections de contenu ; la couverture
du programme passe de 113/182 (62,1 %) a **118/182 (64,8 %)**.

## 2. Le rapport qualite de `yanis-qa` (resume)

`rapport-qualite.md` (worktree dedie `s47-yanis-qualite`, arbre mesure `06449adc`, lignee
verifiee) : `bash outils/mvn-jdk21.sh -B clean verify` vert du premier coup, **1938 tests (1932
unitaires + 6 integration)**, methode autoritaire des attributs `tests="..."` des rapports XML.
Delta de tests explique sans reste : seul `adapter-content` bouge (1067 -> 1107, +40, exactement
la somme des `@Test` des cinq paquets neufs, comptee fichier par fichier) ; zero ligne de
`src/main/java` touchee sur l'ensemble du sprint. ArchUnit inchange (36 `static final ArchRule`,
35 `@ArchTest`, `contreexemple` exclu). Catalogue : 120 entrees (+5), aucun doublon.

**Deux preuves de morsure reelles**, mutation du materiau de production servi (pas une fixture) :
`PropositionsAccentueesJouablesIT` (la ligature "oe"/"oeufs" que le correctif `5015d96c` venait de
corriger - rouge attendu, rouge obtenu, restauration verte) et `TAB-CE1-6` famille (e) sur
`PaquetCe1TablesMultiplicationTest` (rouge attendu, rouge obtenu, restauration verte 8/8). Un
balayage independant des cinq paquets neufs confirme qu'aucun autre caractere hors Latin-1 ne s'y
est glisse, y compris dans des champs (`enonce`, `indice`) que le garde-fou existant ne regarde
pas. Trois garde-fous dont le compte devait bouger (amendement s17) ont tous bouge, verifies un
par un : l'inventaire du port entrant (+21 chaines), le garde-fou de rang (deux maps distinctes
selon que le paquet est homogene ou melange), le registre des sous-parties de mathematiques CE1
(+1 entree). **Conclusion de `yanis-qa` : "La porte technique de s47 est verte."**

## 3. La confirmation ArchUnit de `theo-archi` (resume)

`confirmation-archunit.md` (worktree dedie `s47-theo-archunit`, perimetre mesure `round-two` a
`06449adc`, bases de comparaison `57b8d68c` et `d441027c`, JDK 21.0.10) rend six verdicts : le
compte de regles n'a pas regresse (36/35, identique a l'avis de faisabilite) ; aucune regle
n'etait due (zero fichier de `domain`/`application`, zero `pom.xml`, zero mecanique neuve, un seul
domaine neuf - "Decouverte culturelle" - qui n'appelle aucune classe de production) ; `mvn -B
clean verify` vert (1938 tests, 0 echec, dont 209 dans `com.recreo.archi`) ; la Clean Architecture
tient sur les cinq lignes ; les six garde-fous censes couvrir le materiau neuf ont tous vu leur
population bouger exactement de la taille du materiau neuf ; le correctif `5015d96c` est tenu.

**Ce que `theo-archi` a trouve au-dela du perimetre attendu** : le refus initial de
`PropositionsAccentueesJouablesIT` venait du **harnais** du test (encodage Windows-1252 qui ne
sait pas reproduire "oe"/U+0153), pas d'un defaut du produit - la proposition "oeufs" n'avait donc
jamais ete prouvee comme faisant planter une manche reelle. Un texte destine a l'enfant avait ete
degrade pour une limite de test. `theo-archi` route deux points (Q1, Q2) a `nadia-prof` et
`relecteur-pedagogique`/`yanis-qa` plutot que de trancher lui-meme - traite en section 4 ci-dessous.
Les trois autres jobs de la CI (`chemins`, `prefixes`, `outils`), lances sur son propre diff, sont
verts.

## 4. L'episode "oeufs" - diagnostic corrige, harnais repare, contenu reetabli

**Chronologie.** `bc0fc46c` (18:12:04, `sofia-sm`) introduit le distracteur "oeufs" avec sa
ligature "œ" dans `cp-anglais-culture.yaml`. La fusion des cinq lots fait rougir
`PropositionsAccentueesJouablesIT` (bootstrap). Premier diagnostic, celui de l'orchestrateur :
le produit refuserait un caractere hors Latin-1 soumis par formulaire. Ce diagnostic est corrige
dans la suite de l'episode, pas confirme :

1. **`5015d96c`** (19:04:44, `[SOF]`) corrige en urgence le contenu : "oeufs" sans ligature,
   pour faire passer la CI. C'est le geste que la retro (section 1) qualifie d'erreur de methode.
2. **`47314ce9`** (19:37:18, `[THEO]`), confirmation ArchUnit, etablit le vrai diagnostic en
   lisant le code du test : ce n'est pas le produit qui refuse "œ", c'est le harnais de l'IT, qui
   encode le corps de la requete en `ISO_8859_1` avant de l'envoyer et leve avant meme d'atteindre
   le serveur. Le chemin produit reel decode en UTF-8 et ne leve pas sur cette sequence. `theo-archi`
   route Q1 (l'orthographe sans ligature tient-elle `DEC-0002` ?) et Q2 (le harnais peut-il
   reproduire "œ" sans changer ce qu'il prouve ?) plutot que de trancher.
3. **`9d9d27b1`** (19:48:52, `[NAD]`), arbitrage prealable : **la substitution degrade le texte lu
   par l'enfant**, la ligature doit revenir, dans le meme commit que la correction du harnais,
   jamais avant pour ne pas rendre la CI rouge. `nadia-prof` corrige au passage un fait qu'elle
   avait enregistre a tort ("oeufs" n'est pas le premier exemple de ce defaut dans le depot : le
   premier est "coeur", s34, jamais tranche sur le fond) et signale deux paquets ou une forme sans
   ligature echappe au harnais (`enonce`/`indice`, hors de son perimetre). Elle route trois actions
   (A1 correction du harnais, A2 retablir la ligature dans le meme commit, A3 statuer sur les
   graphies non vues par le harnais) a `theo-archi`/`yanis-qa` et `relecteur-pedagogique`.
4. **`24599866`** (20:06:06, `[THEO]`) corrige le harnais (poste en Windows-1252 au lieu de
   Latin-1, ce qui lui permet de reproduire "œ") et retablit la ligature dans le meme commit
   (A1, A2).
5. **`85a21c94`** (19:58:12, `[REL]`) - chronologiquement avant `24599866` sur l'horodatage de
   commit mais route par le meme arbitrage - retablit la ligature "oe"/"oeuf" dans les deux
   paquets concernes, CP et CM1 (A3, la dette de s34 sur "coeur").

**Verdict de l'episode** : le test avait raison d'exister, tort dans sa methode de preuve ; le
contenu avait raison depuis le debut. La correction qui a tenu est celle du harnais, pas celle du
contenu. Traite comme lecon de process en `retro.md`, section 1.

## 5. Le Done pedagogique - deux instances paralleles, rapporte par cette review, pas prononce par
elle

Dispositif a deux instances de `nadia-prof` (`DEC-0104`, section 4 ; `PAR-2` verifie en planning,
section 3, reverifie ici sans objection).

**Instance A** - `S47-MAT-TABLES`, `S47-QLM-AUTREFOIS`, `S47-LVE-CULTURE-CP`. Budget individuel
5,0, cout retenu 4,0 (1,0 + 1,5 + 1,5), marge 1,0.

| Ligne | Chronologie | Verdict final | Reserves | Ferme |
|---|---|---|---|---|
| Tables de multiplication (`MAT-CE1-07`) | **VALIDE SOUS RESERVE** des le 1er passage (`8f6f438a`, 20:54:38), aucun refus | **VALIDE SOUS RESERVE** | `TAB-CE1-R1` (les cinq items a facteur 1 portent l'indice de leur table, pas un indice sur le facteur 1) | `couvert-valide`, sans mention de couverture partielle |
| Autrefois et aujourd'hui (`QLM-CP-15`) | **REFUSE** 1er passage (`8f6f438a`, 20:54:38 - la tache de rattachement se repondait par le temps du verbe, item 14 faux sur le fond) -> corrige par `lea-dev` (`bc18e0ad`, 21:17:13) -> **REFUSE** 2e passage (`8f41cf74`, 22:01:48 - item 9, distracteur "un telephone fixe" juste aujourd'hui) -> corrige par `lea-dev` (`27858d86`, 22:18:00) -> **VALIDE SOUS RESERVE** au 3e passage (`93c13838`, 22:43:51) | **VALIDE SOUS RESERVE** | `AUT-CP-R1` (deux indices, items 7 et 13, redonnent mot pour mot la reponse l'un de l'autre - defaut introduit par la correction du 2e passage elle-meme) | `couvert-valide`, sans mention de couverture partielle |
| Le monde anglophone (`LVE-CP-06`) | **VALIDE SOUS RESERVE** des le 1er passage (`8f6f438a`, 20:54:38), aucun refus | **VALIDE SOUS RESERVE** | `ANG-CP-R1` (deux items se repondent par la lecture de l'enonce seul), `ANG-CP-R2` (deux indices au-dessus du CP) | `couvert-valide`, avec la mention de couverture partielle exigee d'avance : "decouverte culturelle seule, en francais ; aucune competence linguistique n'est servie" |

**Instance B** - `S47-EMC-ALERTE-CP`, `S47-EMC-VOTE-CM1`. Budget individuel 5,0, cout retenu 3,0
(1,5 + 1,5), marge 2,0.

| Ligne | Chronologie | Verdict final | Reserves | Ferme |
|---|---|---|---|---|
| Donner l'alerte (`EMC-CP-14`) | **REFUSE** 1er passage (`20844037`, 20:39:15 - item 5, situation sans danger, indice enoncant une regle fausse) -> corrige par `lea-dev` (`2e22ca0a`, 21:07:35) -> **VALIDE SOUS RESERVE** au 2e passage (`5b352b7c`, 21:41:30) | **VALIDE SOUS RESERVE** | deux reserves sans identifiant dedie (indice ambigu de l'item 7 - defaut introduit par la simplification demandee au 1er passage -, vocabulaire au-dessus du CP de l'item 11) | `couvert-valide`, sans mention de couverture partielle |
| Le vote et la representation (`EMC-CM1-10`) | **REFUSE** 1er passage (`20844037`, 20:39:15 - items 5 et 6 faux sur le fond, droit de vote et date du vote des femmes) -> corrige par `sofia-sm` casquette dev (`923bf998`, 20:53:45) -> **REFUSE** 2e passage (`5b352b7c`, 21:41:30 - correction de l'item 5 introduisant une exclusivite fausse, defaut neuf) -> corrige par `sofia-sm` (`5aa88a4e`, 21:47:02) -> **VALIDE SOUS RESERVE** au 3e passage (`35e990c2`, 21:59:13) | **VALIDE SOUS RESERVE** | deux reserves sans identifiant dedie, reconduites du 2e passage (un distracteur hors categorie sur les items 3 a 6, question mal formee de l'item 11) | `couvert-valide`, avec la mention de couverture partielle exigee d'avance : "connaissance du vote et de la representation seule ; participer a une decision collective reste non couvrable" |

**Bilan du Done pedagogique du sprint** : trois refus pedagogiques reels sur trois lots distincts
(`QLM-CP-15` deux fois, `EMC-CP-14` une fois, `EMC-CM1-10` deux fois), tous leves en cours de
sprint par des corrections de contenu testees et revalidees ; deux lots fermes sans aucun refus
(`MAT-CE1-07`, `LVE-CP-06`). Zero refus restant a la cloture, cinq Done pedagogiques prononces,
tous VALIDE SOUS RESERVE (huit reserves au total, aucune bloquante).

## 6. Tableau des obstacles du sprint

| Obstacle | Categorie | Route a | Statut a la cloture |
|---|---|---|---|
| Blocage initial `10 × 1` / `TAB-CE1-6` sur `MAT-CE1-07` avant engagement - le fait `10 × 1` n'admettait qu'un seul distracteur dans les quatre familles existantes, qui en exigeait deux, rendant la bonne reponse predictible (Q1 de l'avis de faisabilite de `theo-archi`) | Pedagogie / formulation d'un contenu | `nadia-prof` | **Resolu avant l'ouverture du planning** - amendement de `TAB-CE1-6` (cinquieme famille (e), confusion de `× 1` avec `+ 1`), commit `a29c85b4` (16:08:35), repris dans `planning.md` section 4 |
| "oeufs" sans ligature dans `LVE-CP-06` - correction d'urgence du contenu pour faire passer `PropositionsAccentueesJouablesIT`, diagnostic initial errone (le produit refuserait le caractere) | Qualite / methode de test, puis pedagogie / orthographe d'un contenu | `theo-archi` (diagnostic du harnais), `nadia-prof` (arbitrage sur le fond), `relecteur-pedagogique` (graphies hors du perimetre du harnais) | **Resolu** - diagnostic corrige par `theo-archi` (`47314ce9`, 19:37:18), arbitrage de `nadia-prof` (`9d9d27b1`, 19:48:52 : la ligature revient, jamais avant la correction du harnais), harnais corrige et ligature retablie par `theo-archi` (`24599866`, 20:06:06) et par `relecteur-pedagogique` sur un second paquet (`85a21c94`, 19:58:12). Traite comme lecon de process en `retro.md`, section 1 |
| `EMC-CP-14` et `EMC-CM1-10`, 1er passage (meme audit, `20844037`, 20:39:15) : item 5 de "Donner l'alerte" sans danger decrit, indice enoncant une regle fausse ; items 5 et 6 de "Le vote et la representation" faux sur le fond (droit de vote, date) | Pedagogie / exactitude d'un contenu | `nadia-prof` (verdict), `lea-dev` et `sofia-sm` casquette dev (corrections) | **Resolu** - `EMC-CP-14` corrige par `lea-dev` (`2e22ca0a`, 21:07:35), **VALIDE SOUS RESERVE** au 2e passage (`5b352b7c`, 21:41:30) ; `EMC-CM1-10` corrige par `sofia-sm` (`923bf998`, 20:53:45) |
| `EMC-CM1-10`, 2e passage (`5b352b7c`, 21:41:30) : la correction de l'item 5 introduit une exclusivite fausse ("reserve aux ... "), defaut neuf cree par la correction precedente | Pedagogie / exactitude d'un contenu | `nadia-prof` (verdict), `sofia-sm` (correction) | **Resolu** - corrige par `sofia-sm` (`5aa88a4e`, 21:47:02), **VALIDE SOUS RESERVE** au 3e passage (`35e990c2`, 21:59:13) |
| `QLM-CP-15`, 1er passage (`8f6f438a`, 20:54:38) : la tache de rattachement (items 1 a 7) se repondait par le temps du verbe, sans rien savoir de l'objet ; item 14 faux sur le fond (livre d'images range dans "autrefois") | Pedagogie / exactitude et conception d'un contenu | `nadia-prof` (verdict), `lea-dev` (correction) | **Resolu** - corrige par `lea-dev` (`bc18e0ad`, 21:17:13) |
| `QLM-CP-15`, 2e passage (`8f41cf74`, 22:01:48) : item 9, le distracteur "un telephone fixe" est un moyen juste aujourd'hui d'envoyer une nouvelle, defaut neuf introduit par la correction du 1er passage | Pedagogie / exactitude d'un contenu | `nadia-prof` (verdict), `lea-dev` (correction) | **Resolu** - corrige par `lea-dev` (`27858d86`, 22:18:00), **VALIDE SOUS RESERVE** au 3e passage (`93c13838`, 22:43:51), reserve `AUT-CP-R1` (deux indices se redonnent reciproquement leur reponse, elle-meme rouverte par la correction du 2e passage) |
| Reserves de correction differee - `TAB-CE1-R1` seule, `AUT-CP-R1` seule, `ANG-CP-R1`/`ANG-CP-R2` groupees, deux reserves de `EMC-CP-14` groupees, deux reserves de `EMC-CM1-10` groupees | Pedagogie / correction differee de contenu, non bloquante | `camille-po` (stories ecrites), developpeur a affecter par `sofia-sm` | **Ouvert** - cinq stories (huit reserves) ecrites dans `docs/backlog/backlog.md`, section "Backlog - sprints suivants", non engagees dans un planning a ce jour |
| `SOP-CE1-R1` (reserve de s46, arbitrage sur sa propre condition `SOP-CE1-5`) | Pedagogie / arbitrage sur sa propre condition | `nadia-prof` (elle-meme) | **Instruit hors du chiffrage de s47, le 2026-10-02** - `nadia-prof` admet une cinquieme forme de distracteur (le decalage des colonnes) et la rend obligatoire sur les items sans emprunt, garde la reserve **ouverte** jusqu'a ce qu'elle rejoue un paquet repris (`FICHE-bandit-manchot-soustractions-posees-ce1.md`). La reprise du paquet lui-meme n'est pas engagee dans s47 (`planning.md`, section 6) |

## 7. La couverture du programme

**118/182 (64,8 %)**, verifie par moi sur `squad/programme/MATRICE-couverture.md`, note de sprint
en tete et les cinq lignes elles-memes, toutes `couvert-valide` avec leur chronologie de
refus/correction et leurs reserves - en hausse de 2,7 points sur l'etat a l'ouverture de s47
(113/182, 62,1 %).

## 8. Ce que cette review ne fait pas

- Elle ne prononce aucun Done pedagogique : les cinq verdicts de `nadia-prof` (section 5) sont
  deja prononces ; cette review les rapporte, elle ne les rend pas.
- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` au-dela de ce que
  `camille-po` a deja execute (commit `506c5137`).
- Elle n'ouvre ni ne priorise les cinq stories de correction differee (section 6) : `camille-po`
  les a deja ecrites dans `docs/backlog/backlog.md` ; les affecter a un planning futur
  n'appartient pas a cette ceremonie.
- Elle ne tranche ni l'orthographe de "coeur"/"oeufs" au-dela de ce que `nadia-prof` et
  `relecteur-pedagogique` ont deja arbitre, ni l'arbitrage de `SOP-CE1-R1` : elle les rapporte.

## Decisions prises

**Aucune `DEC` ni `ADR` n'est creee ou amendee apres l'ouverture du planning (2026-10-02 16:17:00).**
`DEC-0104` est creee avant l'horodatage de debut de ce planning, le meme jour - prealable
necessaire a son ouverture. `git diff --name-status bac3b58e 25860e82 -- squad/decisions/produit
squad/decisions/adr` (`bac3b58e` : base citee par `planning.md`, section 1, dernier commit avant
son ecriture) est **vide** : aucune creation ni amendement, en dehors de
`squad/decisions/REGISTRE-identifiants.md` (allocation d'identifiants par `nadia-prof`, pas une
decision).

Aucune decision ne m'appartient en propre au-dela de celles deja ecrites dans `planning.md`,
section 9 (affectation nominative) ; elle n'est pas reprise une seconde fois ici.
