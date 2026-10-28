# Fiche de conformite - ce1-qlm-03

- **Mecanique** : `bandit-manchot`, `type: question`, sans texte support, comme les paquets d'EMC CE1 ; aucune modification de
  la mecanique, aucune illustration (`docs/backlog/backlog.md`, annotation du 2026-10-25, cinquieme, *"Ce qui est commun aux
  neuf stories"*).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-besoins-de-la-plante.yaml` (14 items, lus par
  `grep -c "bonneReponse:"`), ecrit par `marc-dev` (instance neuve, `S57-BR-QLM-CE1`).
- **Entree de catalogue** : aucune. `QC3` : un paquet n'entre au catalogue qu'apres un Done pedagogique ou un refus leve ;
  `grep -c "ce1-qlm" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 0 le 2026-10-27.
- **Ligne declaree** : `QLM-CE1-03`. **Story** : `S57-BR-QLM-CE1-03` (`docs/backlog/backlog.md`). **Decision** : `DEC-0120` A.227, A.250 a A.255,
  A.268 a A.280.
- **Competence programme visee** : `squad/programme/questionner-le-monde/competences-ce1.md`, `QLM-CE1-03`, *"Identifier ce dont une plante a besoin pour vivre et grandir (eau, lumiere, chaleur)."*
- **Conditions opposables** : celles de la story `S57-BR-QLM-CE1-03` et les reperes `QC1` a `QC9` du bloc commun, **non recopiees ici** :
  la story fait foi, ses mots sont ceux de `nadia-prof`. Plafond de la ligne : `couvert-valide`.
- **Statut global** : **en attente d'audit a l'ecran**. Fiche ouverte par `camille-po` le 2026-10-27 (`DEC-0120` A.280, reponse
  de l'utilisatrice a `Q2` : nom `FICHE-ce1-qlm-<lot>.md`). Aucun verdict. La ligne `QLM-CE1-03` reste `non couvert` dans la
  matrice, qui ne bouge qu'au verdict de `nadia-prof`.
- **Statut global, mis a jour le 2026-10-28 par `camille-po` (`DEC-0120` A.283), l'ouverture ci-dessus etant perimee et conservee** : **VALIDE SOUS RESERVE** par `nadia-prof` le 2026-10-27 (s57, palier 5, rang 3 sur 9), sur le paquet tel qu'il est a `90c421a7` (item 6 compris), fusionne dans `round-two` par `740bbc34`. Deux reserves non bloquantes : le mot *plus* de la tache privation, que l'utilisatrice a decide de ne pas corriger, et la liste des non-besoins de la tache besoin. Voir le bloc de validation.
- **Ligne de matrice, etat du 2026-10-28** : `QLM-CE1-03` reste `non couvert`. Motifs lus au depot : (1) `QC3`, aucune entree de catalogue (`grep -c "ce1-qlm" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 0), donc aucun jeu a nommer dans la cellule "Jeu(x) couvrant" ; (2) Done technique complet, les quatre jobs de `.github/workflows/ci.yml` (`QC8`, A.280 point 2), dont seul `build` a ete lance. Aucun des deux n'est une decision de `nadia-prof`. La ligne bouge quand l'entree est fusionnee et que la CI complete est verte (`S57-QLM-CE1-ENTREES-CATALOGUE`) ; fusion du paquet dans `round-two` : `740bbc34`.
- **Stories du backlog pour ce lot** (`docs/backlog/backlog.md`, annotation du 2026-10-28, douzieme, `DEC-0120` A.283) : `S57-BPL-CE1-1-REVISION` ; pas de story pour le mot *plus* (l'utilisatrice, 2026-10-28 : *"on ne corrige pas"*). Aucune n'est engagee.
- **Derniere mise a jour** : 2026-10-27 ; 2026-10-28 (`camille-po`, `DEC-0120` A.283 : statut, ligne `verify`, etat de la ligne de matrice, stories).

---

## Competence visee

| Matiere | Niveau | Competence | Reference au referentiel |
|---|---|---|---|
| questionner le monde | CE1 (cycle 2) | Identifier ce dont une plante a besoin pour vivre et grandir (eau, lumiere, chaleur). | `squad/programme/questionner-le-monde/competences-ce1.md`, `QLM-CE1-03` |

## Criteres attendus - camille-po

Les criteres d'acceptation de cette fiche sont ceux de la story `S57-BR-QLM-CE1-03` (`docs/backlog/backlog.md`, titre *"Identifier ce dont une plante a besoin"*) et
les reperes `QC1` a `QC9` du bloc commun. Je ne les recopie pas : une copie derive de la source. Je n'ecris aucune mesure ici.

- [ ] Criteres de la story `S57-BR-QLM-CE1-03`, numerotes dans le backlog.
- [ ] `QC1` a `QC9` du bloc commun (paquet, ligne declaree, branchement, test du paquet, densite, conditions negatives,
  lecture avant `verify`, Done technique, ce que `nadia-prof` garde seule).
- [ ] Texte servi a l'enfant integralement accentue, orthographe et ponctuation irreprochables (`DEC-0002`).

## Parcours du lot, faits lus au depot

Source : `planning.md` du sprint s57, amendement du 2026-10-27 15:40, section 1 ; chaque SHA est un commit de `round-two`.

| Geste | Auteur | Commit | Fichier |
|---|---|---|---|
| premier jet | `marc-dev` | `7fe939a6` | `ce1-qlm-besoins-de-la-plante.yaml` |
| lecture du premier jet | `nadia-prof` | `30ba34de` | `lecture-nadia-premier-jet-ce1-qlm-besoins-de-la-plante.md` |
| corrections | `marc-dev` | `37f49795` | `ce1-qlm-besoins-de-la-plante.yaml` |
| relecture des corrections (seconde lecture) | `nadia-prof` | `dddd54b3` | `relecture-nadia-corrections-ce1-qlm-besoins-de-la-plante.md` |

Les fichiers de `nadia-prof` sont dans `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/`. Cette fiche n'en reprend aucun
verdict : la lecture et la relecture sont des gestes avant `verify`, pas le Done pedagogique.

## Etat de la porte technique

**Ce qu'un fichier du depot prouve, et rien de plus.**

- **Aucun `mvn verify` complet n'a ete lance pour ce lot** (`planning.md`, amendement du 2026-10-27 15:40, section 1 : fait donne
  par l'agent lanceur, non verifie ; les relectures ecrivent *"aucun autre job de `ci.yml` lance"*).
- **Regle en vigueur** (`DEC-0120` A.280, reponse de l'utilisatrice a `Q1`, option `O1`) : un `mvn verify` complet **avant** l'audit
  a l'ecran de `nadia-prof` de ce lot ; passage CI complet (`prefixes`, `chemins`, `outils`, `build`) a la cloture du palier ; toute
  annonce de porte nomme les jobs lances.
- Premier `verify` complet du lot : **fait au niveau du reacteur, pas par lot** (mis a jour le 2026-10-28 par `camille-po`, `DEC-0120` A.283 ; texte d'origine *"a faire"* perime, conserve ici). `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, sur `90c421a7` : `BUILD SUCCESS`, 3618 tests unitaires et 6 d'integration, 0 echec, mesure par `sofia-sm` (`planning.md`, amendement du 2026-10-27 21:34, section 3), **job `build` seul**. `prefixes` et `chemins` n'ont ete lances qu'en commande de garde-fou, forme CI, `outils` n'a pas ete lance : la porte technique complete n'est pas annoncee (A.280 point 2). Sur `1a810b79`, base de cette mise a jour : un `verify` complet vert (3 624 tests, `build` seul) est **annonce par l'agent lanceur et n'est ecrit dans aucun fichier du depot que j'aie lu** ; `git diff --name-only 90c421a7 1a810b79` ne liste que des fichiers `.md`. Que ce `verify` unique tienne lieu de `verify` du lot n'est ecrit nulle part ; `nadia-prof` l'a lu ainsi dans son verdict. L'item 6 (`e02dcd86`) est ancetre de `90c421a7` (`planning.md`, section 2 de l'amendement du 2026-10-27 21:34), donc contenu dans ce `verify`.

## Ce dont `nadia-prof` reste la seule garde sur cette ligne

Ce qu'aucun test ne tient (`QC9`, `arbitrage-nadia-palier-5-qlm-9-lots.md`, section 6) : l'exactitude de chaque fait contre la source
ecrite en commentaire du paquet, `NRC-CE1-1` sur le sujet de l'item, `CYC-1` contre les paquets voisins, `TRV-CE1-6`, l'operation
mentale de `FER-3`, et les conditions propres a la ligne que la story nomme.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Verdict du 2026-10-27 (`nadia-prof`, s57, palier 5, `P2`, rang 3 sur 9) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce** sur `round-two` a `d6f2e007`, paquet `ce1-qlm-besoins-de-la-plante` tel qu'il est a `90c421a7`
(item 6 compris, `e02dcd86`). Les deux reserves ci-dessous ne bloquent pas. L'entree au catalogue reste une etape distincte
(`QC3`) ; `camille-po` passe `QLM-CE1-03` a `couvert-valide` selon cette etape, et fait une story de la reserve 2 si elle la
retient.

**Done technique, ce qui est acquis.** Regle `O1` d'A.280 : un `mvn -B clean verify` complet avant mon audit. Il a ete lance par
`sofia-sm` a `90c421a7`, reacteur complet, `BUILD SUCCESS`, 3624 tests (`planning.md`, amendement du 2026-10-27 21:34) ; job
`build` seul. Je ne l'ai pas relance. `git diff --stat 90c421a7 d6f2e007` sur `domain`, `application`, `adapters` et
`bootstrap` est vide. Les jobs `prefixes`, `chemins` et `outils` sont dus a la cloture du palier : si cette CI rougit sur ce
lot, ce verdict est rouvert.

**Comment j'ai joue.** Jar de `round-two`, construit a `90c421a7` d'apres l'agent lanceur. J'ai verifie a l'octet (`sha256`)
deux ressources, `contenu/ce1-qlm-besoins-de-la-plante.yaml` et `catalogue.yaml`, identiques a l'arbre de `d6f2e007`. Le
paquet est hors catalogue : j'ai joue une **copie du jar hors du depot**, dont seule la ressource `catalogue.yaml` porte une
entree ajoutee, comme pour `cm1-reperes` le 2026-08-27. Le titre (*"Les besoins de la plante"*) et la consigne (*"Lis bien, puis
choisis la bonne réponse."*, celle de `ce1-emc-biens-communs`) de cette entree sont les miens, pour jouer. **Ce verdict ne couvre
pas le titre ni la consigne de l'entree de `QC3`**, que je n'ai pas vus. Serveur lance en JDK 21 (`java -jar` sur la copie,
`--server.port`, forme de `JarRecreoIT`), arrete par son PID. `git status` du worktree vide avant et apres.

- **Fragments htmx** (`POST /parties/<id>` puis `/coups`, en-tete `HX-Request`) lus en texte, trois parties suivies a la main :
  tout juste, une erreur par manche, tout faux. Puis 60 parties de 10 manches par script : 30 au hasard, 30 avec deux signes
  de forme (detail en reserve 1).
- **Quatre captures** par Edge sans tete, page servie et fragment du serveur : items 6 et 12 apres une erreur, a 768 x 1024
  et a 744 x 551.
- **Non fait, dit** : aucun enfant, aucun toucher ni clic dans un navigateur, aucun echange htmx joue par le navigateur. Je
  n'ai capture que deux items sur quatorze. Une capture a 375 de large deborde des l'en-tete, hors de ce lot : je ne m'en sers
  pas.

**Verifie a l'ecran.**

1. **Le texte servi est celui que j'ai lu.** Les 14 enonces, les 42 propositions et les 14 indices s'affichent tels qu'ils sont
   dans le YAML. Le script n'a trouve aucun ecart, et les 14 items sont apparus en 60 parties. Accents rendus (*très*, *pâle*,
   *arrête*, *Écarte*), apostrophes droites. L'espace insecable tient le *?* a son mot : *"Que lui arrive-t-il ?"* ne se coupe
   pas sur les deux captures.
2. **Le parcours d'un enfant de CE1.** 10 manches par partie, aucun item deux fois dans une meme partie (60 parties). Le rang de
   la bonne reponse varie : 1, 2 ou 3, de 90 a 115 fois chacun sur 300 manches par serie. A l'erreur : *"Ce n'est pas ça. Essaie
   encore !"*, le bouton essaye barre et toujours en place, l'indice de l'item affiche a cote. Apres deux erreurs, il ne reste
   qu'un bouton non barre : l'enfant bloque avance toujours. Fin de partie : *"Tu as trouvé, tout seul : n"* sur *10*.
3. **Lisibilite et pli.** A 744 x 551, l'enonce de l'item 12 (75 caracteres, 14 mots) tient sur deux lignes. Le plus long,
   celui de l'item 8 (78 caracteres), n'est pas capture. Les trois boutons, le retour et
   l'indice sont visibles sans defiler, l'indice tout juste. La plus longue proposition, *"elle devient pâle et pousse mal"*,
   tient sur une ligne.
4. **L'indice est une methode, pas la reponse.** C'est mon jugement de lecture, et il tient a l'ecran : affiche apres l'erreur,
   l'indice ne designe aucun bouton.
5. **Exactitude, `BPL-CE1-1`, `BPL-CE1-2`, `NRC-CE1-1`, `CYC-1`** : non rejuges ici. Mes trois lectures les tiennent, et le
   texte servi est identique a celui que j'ai lu (point 1).

**Reserves, non bloquantes.**

1. **Le mot *plus* dans la tache privation**, ma reserve du 2026-10-27
   (`relecture-nadia-troisieme-lecture-ce1-qlm-03-et-08.md`, section 3). L'utilisatrice a decide qu'on ne la corrige pas : je
   la garde, je ne demande rien. Ce que l'ecran montre : chaque partie sert de 2 a 5 items de privation (60 parties), donc le
   signe *plus n'est jamais juste* peut s'apprendre d'une partie a l'autre. Un joueur qui ecarte le bouton qui porte *plus*,
   puis choisit au hasard, trouve du premier coup 54 fois sur 112 (48 %). Au hasard pur : 34 sur 101 (34 %). C'est le passage de
   un sur trois a un sur deux que j'avais prevu. **Ce n'est pas un motif de refus** : le choix qui reste oppose la consequence
   et son contraire, et ce choix-la demande la competence.
2. **Neuve, datee du 2026-10-27 : la liste des non-besoins se reconnait sans lire l'enonce.** Je l'avais annoncee dans
   `lecture-nadia-premier-jet-ce1-qlm-besoins-de-la-plante.md`, 1.1. Dans les 9 items de la tache besoin, les deux distracteurs
   sont toujours deux de *sucre*, *lait*, *bruit*. Un joueur qui les ecarte trouve du premier coup **188 fois sur 188**, sans
   lire l'enonce. Avec la reserve 1, il trouve du premier coup 242 manches sur 300 (81 %), contre 103 sur 300 (34 %) au
   hasard : le score *"trouvé, tout seul"* surestime ce que l'enfant sait de la privation. Ecarter *du sucre* et *du lait*
   reste la conception que la ligne veut deplacer : c'est pourquoi ce n'est pas un refus. Ce que le jeu ne fait jamais : mettre
   deux des trois besoins cote a cote dans un item ou l'enonce dit lequel manque (par exemple une plante arrosee, au chaud,
   dans un placard). **Le lot tient ma condition `BPL-CE1-1` telle que je l'ai ecrite** (*"Distracteurs : ce dont une plante n'a
   pas besoin"*). Ce que je vois de plus vise donc ma condition, pas le travail de `marc-dev`. Pour en faire une story, il faut
   d'abord que je revise `BPL-CE1-1` par ecrit, pour un sprint suivant.

**Hors de ce lot, constate et non juge ici** : l'ecran de fin affiche le retour de la derniere manche (*"Bravo, c'est juste !"*)
meme quand *"trouvé, tout seul"* vaut 0. C'est la mecanique commune, que ce lot ne modifie pas.

**Je reste la seule garde**, pour ce lot : de l'elimination par *plus* (le garde-fou de la clause 3 ne compte pas ce mot) ; de
la reconnaissance de la liste des non-besoins ; de l'exactitude de chaque fait contre sa source ; de `NRC-CE1-1` et de `CYC-1`
(aucun garde-fou ne compare deux paquets) ; du pli a 744 x 551 (aucun test ne le mesure ; je n'ai capture que les items 6 et 12).

**Duree de l'audit, a l'horloge** (`date`, UTC+01:00, donnee brute, aucun bareme, A.276) : debut 21:51:32 ; serveur joue de
21:57:09 a 22:06:52, puis de 22:09:13 a 22:11:18 pour les captures a 744 x 551 ; debut de la redaction a 22:12:35. La redaction
et les verifications avant commit ne sont pas dans ces fenetres.

**Signe** : `nadia-prof`, 2026-10-27.
