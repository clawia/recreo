# Relecture pedagogique et QA de contenu - Sprint 6

- **Auteure** : relecteur-pedagogique
- **Date** : 2026-08-10
- **Provisionnement** : worktree dedie `relecture-s06`, provisionne hors de l'arbre du depot depuis
  `round-two` a `be60b10`. Lignee verifiee : `outils/verifier-lignee.sh` rend
  "le worktree sur la branche 'relecture-s06' descend de round-two." Rien a signaler sur le
  comportement du script, il rend le bon verdict sur ce cas.
- **Perimetre lu** : `DEC-0013`, l'amendement de `DEC-0012`, `ADR-0020` a `ADR-0024` et
  `ADR-index.md`, `DEC-index.md`, `ADR-0017` (second amendement), `docs/backlog/backlog.md` section
  Sprint 6 (S6-1 a S6-14), `squad/README.md` (diff complet), `squad/conformite/
  FICHE-bandit-manchot-additions-cp.md` (blocs neufs S6-9/S6-10) et `FICHE-bandit-manchot-
  conjugaison-present-cm1.md`, `squad/programme/MATRICE-couverture.md`,
  `squad/sprints/s06-.../planning.md`, `avis-faisabilite.md`, `rapport-qualite.md`,
  `proposition-ines-s6-8-s6-10.md`, `proposition-ines-menu-360px.md`, `outils/verifier-lignee.sh`,
  `verifier-lignee-test.sh`, `verifier-jdk.sh`, `verifier-jdk-test.sh`, les 22 messages de commit du
  sprint, les trois paquets de contenu (`cm1-conjugaison-present.yaml`, `cm1-homophones.yaml`,
  `cp-le-bon-son.yaml`), les quatre gabarits de `templates/` et `recreo.css`, `PartieController.java`,
  `Question.java`, `Verdict.java`, et un echantillon des tests neufs les plus longs
  (`ToutEcranEchangeEstUnDocumentJouableTest`, `PaquetsFrancaisS04Test`).
- **Non touche** : `confirmation-archunit.md` (n'existe pas encore pour s06) et tout `ADR` en cours
  d'ecriture par `theo-archi`.

## Corrections appliquees (3)

1. `squad/sprints/s06-ouvrir-la-conjugaison-et-solder-la-dette/proposition-ines-menu-360px.md`,
   ligne 86 : mot anglais "exactly" laisse par erreur dans une phrase francaise, corrige en
   "exactement".
2. `squad/sprints/s06-ouvrir-la-conjugaison-et-solder-la-dette/proposition-ines-menu-360px.md`,
   ligne 128 : coquille "de na pas ecrire" corrigee en "de ne pas ecrire".
3. `adapters/adapter-web/src/main/resources/static/css/recreo.css`, commentaire au-dessus de
   `.zone-jeu` : accord fautif "les deux axes laterales" corrige en "les deux axes lateraux"
   (accord masculin pluriel de "axe").

Les trois sont des coquilles sans ambiguite, aucune n'affecte le sens ni le code.

## Signale sans corriger

Rien n'a ete signale ce sprint : aucun defaut ambigu, aucun chemin de poste, aucun point de
terminologie ou de clarte n'a exige un arbitrage d'un autre agent. Un seul point mineur, releve pour
memoire plutot que comme un defaut a trancher :

- `outils/verifier-lignee-test.sh`, ligne 68, porte le mot "figé" (seul mot accentue de tout le
  corpus de commentaires shell du sprint, qui s'ecrit sans accent partout ailleurs - "developpeur",
  "reference", "priorisation", etc.). Ce n'est pas une faute : `CLAUDE.md` autorise explicitement les
  accents dans le contenu redactionnel francais, et le mot est correctement orthographie. C'est une
  incoherence de style isolee, pas un defaut de langue. Je ne corrige pas (retirer l'accent n'est pas
  plus juste que le garder) et je ne bloque rien dessus. A `sofia-sm`, pour information seulement, au
  cas ou l'equipe voudrait un jour trancher la convention d'accentuation des commentaires de code.

## Verifie sans rien trouver

- **Messages de commit du sprint** (22 commits, `7bbc1a8..be60b10`) : tous prefixes par un tag
  d'agent connu, tous en ASCII pur (aucun caractere accentue, aucun tiret cadratin, aucun guillemet
  typographique).
- **Tirets cadratin, guillemets typographiques, emoji, symboles Unicode decoratifs** : balayage de
  tous les fichiers `.md/.java/.sh/.yaml/.html/.css` modifies ce sprint (89 fichiers). Aucune
  occurrence.
- **Chemins absolus de poste de travail, noms de compte, credentials** : balayage cible (lettres de
  lecteur Windows, `/home/`, `/Users/`, `.m2`, `AppData`, `JAVA_HOME=<chemin>`) sur les memes 89
  fichiers, en particulier `rapport-qualite.md`, `avis-faisabilite.md`, `planning.md`, les deux
  `proposition-ines-*.md` et les quatre fiches de conformite touchees - les rapports les plus exposes
  puisqu'ils citent des commandes reellement lancees dans des worktrees distincts. Aucune occurrence
  reelle. Les seuls "chemins" presents sont soit des chemins relatifs au depot (legitimes), soit des
  valeurs deliberement simulees a l'execution dans `verifier-jdk-test.sh` et
  `verifier-lignee-test.sh` (construites par concatenation en shell, jamais ecrites en clair dans le
  fichier source, precisement pour ne pas faire rougir `verifier-chemins.sh` sur leur propre fixture
  de test) - conformes a l'intention documentee de ces deux scripts.
- **`.github/scripts/verifier-chemins.sh` outrepasse** : verifie a la main au-dela de ce que ce
  script couvre (formes `C:\`, `/c/Users/`, noms de compte litteraux) plutot que de me fier a son
  vert.
- **Coherence terminologique du renommage "Accueil" -> "Les jeux"** : le libelle est identique partout
  ou il apparait apres l'arbitrage de `nadia-prof` du 2026-08-07 - `ADR-0022`, backlog S6-9, la fiche
  de conformite, `accueil.html`, `fragments/manche.html`, `fragments/retour.html`. Aucune trace
  residuelle de "Accueil" comme libelle de ce lien.
- **Le point specifique signale par le coordinateur sur la reserve 5 perimee de `nadia-prof`
  ("Récréo" accentue)** : verifie dans `squad/conformite/FICHE-bandit-manchot-additions-cp.md`,
  section "Un constat de moi-meme que je corrige" - le texte est deja correctement ecrit par
  `nadia-prof` elle-meme : elle dit explicitement que sa reserve 5 de s02 "est aujourd'hui perimee"
  et que la ligne ne doit "plus etre lue comme un acquis a preserver", sans rouvrir ni effacer la
  ligne d'origine. Aucune autre occurrence du titre trouvee ailleurs dans les livrables du sprint,
  presentee comme un acquis courant.
- **Les 29 items de contenu neufs et leurs indices/explications** (12 `cm1-conjugaison-present.yaml`,
  2 `cm1-homophones.yaml`, 15 `cp-le-bon-son.yaml` recomposes) : francais correct, integralement
  accentue, orthographe et ponctuation verifiees phrase par phrase. Aucune faute trouvee. Comptes de
  items verifies par grep (`12`, `24` dont les `2` neufs, `15`) contre les chiffres annonces par
  `DEC-0013`/le backlog.
- **L'indice corrige de `cm1-homophones.yaml`** (item "mes") : verifie contre le motif de la story
  S6-5 - l'ancienne phrase de substitution ("J'ai range les miennes affaires") n'existe plus, la
  nouvelle ("J'ai range tes affaires") est du francais correct et suit le modele de l'item "son" cite
  au mot pres par `nadia-prof`.
- **Exactitude technique des ADR contre le code reellement livre** : `ADR-0020` verifie contre
  `PartieController.java` (`mancheModele`, `retourModele`) et `accueil.html` (`th:block`,
  `th:fragment="zoneAccueil"`) ; `ADR-0022` verifie contre `recreo.css`
  (`body:has(.ecran-de-jeu) .lien-accueil`) et les quatre gabarits (classe `ecran-de-jeu`). Les deux
  correspondent exactement a ce que l'ADR decrit.
- **Coherence arithmetique de `squad/programme/MATRICE-couverture.md`** : la table de synthese
  (46 = 42 + 3 + 1, par matiere/niveau) s'additionne correctement.
- **Clarte pour un developpeur junior** : lecture complete de `DEC-0013`, des cinq `ADR` du sprint,
  du backlog S6-1 a S6-14, et de `squad/README.md` - aucune phrase qui ne se comprenne qu'en
  connaissant l'historique tacite du sprint ; chaque renvoi a un fait anterieur (verdict, mesure,
  commit) est cite avec sa reference exacte.
- **Absence de commentaire qui paraphrase le code** dans les fichiers Java lus (`Question.java`,
  `Verdict.java`, `PartieController.java`, `ToutEcranEchangeEstUnDocumentJouableTest.java`,
  `PaquetsFrancaisS04Test.java`) : chaque Javadoc ajoute un motif ou une reference (ADR, story,
  mesure) que le code seul ne porte pas.
