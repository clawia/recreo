# Fiche de conformite - ce1-qlm-02

- **Mecanique** : `bandit-manchot`, `type: question`, sans texte support, comme les paquets d'EMC CE1 ; aucune modification de
  la mecanique, aucune illustration (`docs/backlog/backlog.md`, annotation du 2026-10-25, cinquieme, *"Ce qui est commun aux
  neuf stories"*).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-ce-que-mangent-les-animaux.yaml` (12 items, lus par
  `grep -c "bonneReponse:"`), ecrit par `marc-dev` (instance neuve, `S57-BR-QLM-CE1`).
- **Entree de catalogue** : aucune. `QC3` : un paquet n'entre au catalogue qu'apres un Done pedagogique ou un refus leve ;
  `grep -c "ce1-qlm" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 0 le 2026-10-27.
- **Ligne declaree** : `QLM-CE1-02`. **Story** : `S57-BR-QLM-CE1-02` (`docs/backlog/backlog.md`). **Decision** : `DEC-0120` A.227, A.250 a A.255,
  A.268 a A.280.
- **Competence programme visee** : `squad/programme/questionner-le-monde/competences-ce1.md`, `QLM-CE1-02`, *"Relier quelques animaux familiers a ce qu'ils mangent (des vegetaux, d'autres animaux, ou les deux)."*
- **Conditions opposables** : celles de la story `S57-BR-QLM-CE1-02` et les reperes `QC1` a `QC9` du bloc commun, **non recopiees ici** :
  la story fait foi, ses mots sont ceux de `nadia-prof`. Plafond de la ligne : `couvert-valide`.
- **Statut global** : **VALIDE SOUS RESERVE**, prononce le 2026-10-27 par `nadia-prof` (s57, palier 5, audit `P2`), reserve
  `RAL-CE1-R1` ; voir le bloc de validation. Fiche ouverte par `camille-po` le 2026-10-27 (`DEC-0120` A.280, reponse de
  l'utilisatrice a `Q2` : nom `FICHE-ce1-qlm-<lot>.md`), statut d'origine *"en attente d'audit a l'ecran"*. La matrice n'est pas
  modifiee par ce verdict : son passage est a `camille-po`.
- **Ligne de matrice, etat du 2026-10-28** : `QLM-CE1-02` reste `non couvert`. Motifs lus au depot : (1) `QC3`, aucune entree de catalogue (`grep -c "ce1-qlm" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 0), donc aucun jeu a nommer dans la cellule "Jeu(x) couvrant" ; (2) Done technique complet, les quatre jobs de `.github/workflows/ci.yml` (`QC8`, A.280 point 2), dont seul `build` a ete lance. Aucun des deux n'est une decision de `nadia-prof`. La ligne bouge quand l'entree est fusionnee et que la CI complete est verte (`S57-QLM-CE1-ENTREES-CATALOGUE`) ; fusion du paquet dans `round-two` : `9a7ad21f`.
- **Stories du backlog pour ce lot** (`docs/backlog/backlog.md`, annotation du 2026-10-28, douzieme, `DEC-0120` A.283) : `S57-RAL-CE1-R1`. Aucune n'est engagee.
- **Derniere mise a jour** : 2026-10-27 ; 2026-10-28 (`camille-po`, `DEC-0120` A.283 : statut, ligne `verify`, etat de la ligne de matrice, stories).

---

## Competence visee

| Matiere | Niveau | Competence | Reference au referentiel |
|---|---|---|---|
| questionner le monde | CE1 (cycle 2) | Relier quelques animaux familiers a ce qu'ils mangent (des vegetaux, d'autres animaux, ou les deux). | `squad/programme/questionner-le-monde/competences-ce1.md`, `QLM-CE1-02` |

## Criteres attendus - camille-po

Les criteres d'acceptation de cette fiche sont ceux de la story `S57-BR-QLM-CE1-02` (`docs/backlog/backlog.md`, titre *"Relier un animal familier a ce qu'il mange"*) et
les reperes `QC1` a `QC9` du bloc commun. Je ne les recopie pas : une copie derive de la source. Je n'ecris aucune mesure ici.

- [ ] Criteres de la story `S57-BR-QLM-CE1-02`, numerotes dans le backlog.
- [ ] `QC1` a `QC9` du bloc commun (paquet, ligne declaree, branchement, test du paquet, densite, conditions negatives,
  lecture avant `verify`, Done technique, ce que `nadia-prof` garde seule).
- [ ] Texte servi a l'enfant integralement accentue, orthographe et ponctuation irreprochables (`DEC-0002`).

## Parcours du lot, faits lus au depot

Source : `planning.md` du sprint s57, amendement du 2026-10-27 15:40, section 1 ; chaque SHA est un commit de `round-two`.

| Geste | Auteur | Commit | Fichier |
|---|---|---|---|
| premier jet | `marc-dev` | `ebc274b1` | `ce1-qlm-ce-que-mangent-les-animaux.yaml` |
| lecture du premier jet | `nadia-prof` | `388cb638` | `lecture-nadia-premier-jet-ce1-qlm-ce-que-mangent-les-animaux.md` |
| corrections | `marc-dev` | `f7c6bc7d` | `ce1-qlm-ce-que-mangent-les-animaux.yaml` |
| relecture des corrections (seconde lecture) | `nadia-prof` | `e0d2ba08` | `relecture-nadia-corrections-ce1-qlm-ce-que-mangent-les-animaux.md` |

Les fichiers de `nadia-prof` sont dans `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/`. Cette fiche n'en reprend aucun
verdict : la lecture et la relecture sont des gestes avant `verify`, pas le Done pedagogique.

## Etat de la porte technique

**Ce qu'un fichier du depot prouve, et rien de plus.**

- **Aucun `mvn verify` complet n'a ete lance pour ce lot** (`planning.md`, amendement du 2026-10-27 15:40, section 1 : fait donne
  par l'agent lanceur, non verifie ; les relectures ecrivent *"aucun autre job de `ci.yml` lance"*).
- **Regle en vigueur** (`DEC-0120` A.280, reponse de l'utilisatrice a `Q1`, option `O1`) : un `mvn verify` complet **avant** l'audit
  a l'ecran de `nadia-prof` de ce lot ; passage CI complet (`prefixes`, `chemins`, `outils`, `build`) a la cloture du palier ; toute
  annonce de porte nomme les jobs lances.
- Premier `verify` complet du lot : **fait au niveau du reacteur, pas par lot** (mis a jour le 2026-10-28 par `camille-po`, `DEC-0120` A.283 ; texte d'origine *"a faire"* perime, conserve ici). `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, sur `90c421a7` : `BUILD SUCCESS`, 3618 tests unitaires et 6 d'integration, 0 echec, mesure par `sofia-sm` (`planning.md`, amendement du 2026-10-27 21:34, section 3), **job `build` seul**. `prefixes` et `chemins` n'ont ete lances qu'en commande de garde-fou, forme CI, `outils` n'a pas ete lance : la porte technique complete n'est pas annoncee (A.280 point 2). Sur `1a810b79`, base de cette mise a jour : un `verify` complet vert (3 624 tests, `build` seul) est **annonce par l'agent lanceur et n'est ecrit dans aucun fichier du depot que j'aie lu** ; `git diff --name-only 90c421a7 1a810b79` ne liste que des fichiers `.md`. Que ce `verify` unique tienne lieu de `verify` du lot n'est ecrit nulle part ; `nadia-prof` l'a lu ainsi dans son verdict.

## Ce dont `nadia-prof` reste la seule garde sur cette ligne

Ce qu'aucun test ne tient (`QC9`, `arbitrage-nadia-palier-5-qlm-9-lots.md`, section 6) : l'exactitude de chaque fait contre la source
ecrite en commentaire du paquet, `NRC-CE1-1` sur le sujet de l'item, `CYC-1` contre les paquets voisins, `TRV-CE1-6`, l'operation
mentale de `FER-3`, et les conditions propres a la ligne que la story nomme.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Verdict du 2026-10-27 (`nadia-prof`, s57, palier 5, audit `P2`, rang 2 sur 9) : **VALIDE SOUS RESERVE**

**Ce qui a ete joue.** Le jar du depot fourni par l'agent lanceur (SHA-256 commencant par `920bb486520f05be`), annonce au
contenu de `round-two`. Verifie par moi : le `catalogue.yaml` et le paquet `ce1-qlm-ce-que-mangent-les-animaux.yaml` extraits du
jar sont identiques a l'octet a ceux de l'arbre `round-two` a `d6f2e007` ; `git diff --stat 90c421a7 d6f2e007` hors fichiers
`.md` est vide ; le paquet et son test n'ont pas change depuis `f7c6bc7d`, le commit que ma seconde lecture a lu.

**Comment le paquet a ete servi, alors qu'il n'est pas au catalogue (`QC3`).** Un `catalogue.yaml` de travail, hors du depot et
non commite : copie de celui du jar plus une entree (`id: audit-nadia-ce1-qlm-02`, `mecanique: bandit-manchot`, ce paquet,
`nombreDeManches: 10`, titre *"Ce que mangent les animaux"*, consigne *"Lis bien, puis choisis la bonne réponse."* recopiee des
entrees d'EMC CE1). Place avant le jar sur le classpath, lance par le lanceur de Spring Boot :
`java -cp <repertoire du catalogue de travail><separateur>recreo.jar org.springframework.boot.loader.launch.JarLauncher --server.port=18102`,
avec le JDK 21 que designe la toolchain. Lu dans le journal : *"Starting RecreoApplication v0.1.0-SNAPSHOT using Java 21.0.10"*,
*"Tomcat started on port 18102 (http)"*, PID lu, aucune ligne `ERROR` ni `Exception` ; arrete par son PID. **Le catalogue du depot
n'est pas touche. Le titre, la consigne et le nombre de manches que j'ai joues sont les miens : ils ne sont pas audites ici.**
L'entree de catalogue de `QC3` passera devant moi a son etape.

**Audit degrade, dit.** Joue par requetes HTTP sur les routes de l'enfant (accueil, puce CE1, tuile de questionner le monde, puis
`POST /parties/...` et `/coups` avec l'en-tete htmx), fragments lus en texte. **Aucun navigateur** : ni rendu visuel, ni pli, ni
focus, ni clavier. Ce sont des proprietes de `ines-design` et de la mecanique, inchangees par ce lot.

**Parties jouees : 18 parties de 10 manches, 180 manches.** 3 parties sans erreur ; 3 parties en me trompant une fois a chaque
manche ; 1 partie a trois erreurs par manche ; 8 parties jouees comme un enfant qui cherche l'intrus sans lire l'animal ; 3 parties
en choisissant toujours la proposition la plus longue. Les 12 items sont servis, aucun enonce inconnu du paquet, aucun item servi
deux fois dans une partie, 5 a 6 ordres des propositions par item. Chaque partie porte 4 a 6 manches de la tache categorie.

**Ce que l'ecran montre (constate).**

- Bonne reponse : *"Bravo !"*, score plus un, manche suivante.
- Premiere et deuxieme erreur : *"Ce n'est pas ça. Essaie encore !"* suivi de l'indice de l'item ; la proposition essayee reste a sa
  place et reste cliquable. Les 12 indices ont ete lus a l'ecran, chacun sur son item, a l'identique du paquet.
- Troisieme erreur : *"Ce n'est pas ça. Appuie sur la bonne réponse : des feuilles de salade"* (escargot), indice maintenu ; la
  manche n'est pas comptee comme trouvee. Un enfant bloque avance.
- Fin de partie sans erreur : *"Tu as trouvé, tout seul : 10"*, *"Tu as joué toutes les questions : 10"*, *"Bravo, c'est juste !
  C'est fini !"*. A 0 : chaque item manque est redonne avec *"La bonne réponse : ..."* (par exemple le sanglier, *les deux*).
- Enonces de 6 a 10 mots, une phrase, une question, espace insecable avant `?` ; propositions de 2 a 4 mots ; indices de 8 a 11
  mots. Un enfant de CE1 les lit seul. Je n'ai vu aucun accent manquant ; ce controle reste au `relecteur-pedagogique` (`DEC-0002`).

**Ce qui tient, verifie a l'ecran et contre ma lecture (a ne pas defaire).**

- **Exactitude** : les 12 bonnes reponses et les 24 distracteurs sont ceux que ma lecture (`388cb638`) et ma seconde lecture
  (`e0d2ba08`) ont tenus ; aucun distracteur n'est mange par l'animal de l'enonce. Aucune erreur factuelle.
- **Tache categorie (6 items)** : la competence y est travaillee. Les trois propositions sont toujours les trois categories ; en
  cherchant l'intrus sans lire l'animal, 13 bonnes reponses du premier coup sur 39 manches, le hasard. L'enfant doit savoir ce que
  la vache, la chouette ou le sanglier mangent.
- **`TRV-CE1-2`** : la plus longue proposition donne 11 bonnes reponses du premier coup sur 30 manches (3 parties), le hasard.
- **`TRV-CE1-5`** : aucun des 12 indices ne nomme une categorie ni un aliment ; ils renvoient au lieu, au geste ou au corps.
- **`RAL-CE1-2`**, critere 4 : aucun animal exclu, aucun aliment du commerce, chaque enonce dit ou l'animal trouve sa nourriture.
- **`TRV-CE1-6`** : aucun prenom, aucun foyer, aucun objet possede. Aucun biais ni ton condescendant.

**Reserve `RAL-CE1-R1`, neuve, datee du 2026-10-27, sur ma regle et non sur ce paquet.** C'est le constat de l'intrus
(`lecture-nadia-premier-jet-ce1-qlm-ce-que-mangent-les-animaux.md`, section 4 ; `DEC-0120` A.278 point 5, ligne 4 ; `P4` de
`planning.md`), que j'avais dit regarder a l'audit. **Constate a l'ecran** : en choisissant, dans chaque item de la tache aliment,
la proposition qui n'est pas de la meme categorie que les deux autres, **sans lire l'animal**, 41 bonnes reponses du premier coup
sur 41 manches, en 8 parties. Dans cette tache, l'enfant classe trois aliments (une partie de la ligne) ; il n'a pas a relier
l'animal a ce qu'il mange (le verbe de la ligne). La cause est la regle que j'ai ecrite dans `avis-nadia-seconde-tache-ce1-qlm-02.md`
et qui est passee dans le critere 6 de la story : *"Les deux distracteurs sont des aliments de l'autre categorie"*. Le paquet la
tient exactement ; je ne refuse pas un paquet pour avoir tenu ma regle, et je ne la durcis pas apres coup.
**Ce qu'il faut, pour une story de `camille-po`** : dans chaque item de la tache aliment, un des deux distracteurs est un aliment
**de la meme categorie que la bonne reponse**, que l'animal ne mange pas a ce lieu, avec sa source ecrite dans le commentaire du
paquet (`QMT-CE1-2`) ; l'autre distracteur reste de l'autre categorie. Mesure de levee : la recherche de l'intrus, jouee comme
ci-dessus, ne donne plus que le hasard sur la tache aliment. La regle du critere 6 de la story est a reecrire dans ce sens ; le
choix des aliments et de leur source repasse par ma lecture avant `verify`. Symptome pour `theo-archi`, sans en decider la forme :
la propriete se calcule sur les items si chaque aliment du paquet porte sa categorie dans le commentaire.

**Pourquoi VALIDE SOUS RESERVE et non REFUSE.** Aucune erreur factuelle, aucun item a deux reponses defendables. La tache
categorie travaille la competence annoncee sur 4 a 6 manches de chaque partie, et aucune strategie de surface que j'ai jouee ne
la resout. La tache aliment reste utilisable : elle demande de classer les aliments, et la reserve la rend a la ligne entiere.

**Porte technique.** Je m'appuie sur le `mvn -B clean verify` complet vert a `90c421a7` (`planning.md`, amendement du 2026-10-27
21:34, section 3 : `BUILD SUCCESS`, 3 624 tests, job `build`, etape `verify` seule), sur un arbre qui porte ce paquet a l'identique
de celui que j'ai joue. Je le lis comme le `verify` complet avant audit qu'exige A.280 point 2. `prefixes`, `chemins` et `outils`
n'ont pas ete lances en entier : c'est la condition de cloture du palier, pas celle du lot. La ligne *"Premier `verify` complet du
lot"* de cette fiche reste a inscrire par qui `sofia-sm` designe ; je ne l'ecris pas.

**Je reste la seule garde, sur cette ligne** : l'exactitude de chaque categorie et de chaque aliment contre sa source ; qu'aucun
distracteur de la tache aliment ne soit mange par l'animal ; que la tache aliment ne se resolve pas par l'intrus (`RAL-CE1-R1`,
aucun test) ; qu'un indice soit une methode et pas une periphrase ; *"meme type d'article"* (`I1`) tant que la garde demandee
n'est pas ecrite ; *"un animal classe les deux n'entre pas en B"* ; le lieu de l'enonce ; `NRC-CE1-1` et `CYC-1`, aucun
garde-fou ne compare deux paquets. **Aucune de ces proprietes n'est couverte par un test.**

**Ce qui suit.** `camille-po` peut passer `QLM-CE1-02` a `couvert-valide` dans la matrice et faire une story de `RAL-CE1-R1`.
L'entree de catalogue est une etape distincte (`QC3`), que ce verdict ouvre et ne fait pas.

**Duree de l'audit, a l'horloge** (`date`) : debut **2026-10-27 21:51:25**, fin **2026-10-27 22:17:33**, avant le commit, soit
**26 min 08 s**, lancement du serveur, 18 parties, ecriture et verifications comprises. Donnee brute pour la remesure
d'A.276 ; je n'en convertis aucune en bareme.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
