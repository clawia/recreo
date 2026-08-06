# [Sprint s03] Relecture pedagogique et QA de contenu - relecteur-pedagogique

- **Date** : 2026-08-06
- **Auteur** : relecteur-pedagogique
- **Perimetre** : tout ce qui a ete produit depuis ma derniere relecture (fin s01), soit s02, s03,
  deux artefacts hors sprint (`ADR-0014`, `DEC-0008`) et le contenu de s04 (aucun dossier de sprint
  n'existe pour s04 - je n'en cree pas ; ce qui suit couvre neanmoins ses deux paquets de contenu et
  ses deux entrees de catalogue, sur demande explicite).
- **Livrables lus** :
  - s02 : `planning.md` (absent, constate), `review.md`, `retro.md`, `RAPPORT.md`,
    `rapport-qualite.md`, `confirmation-archunit.md`.
  - s03 : `planning.md`, `avis-faisabilite.md`, `rapport-qualite.md` (partiel).
  - `DEC-0006`, `DEC-0007` (et son amendement du 2026-08-05), `DEC-0004` (amendement du
    2026-08-05), `DEC-0008`, `ADR-0013`, `ADR-0014`, `ADR-index.md`, `DEC-index.md`.
  - `docs/backlog/backlog.md`, sections Sprint 2, Sprint 3, Sprint 4.
  - `squad/programme/MATRICE-couverture.md`, `squad/conformite/FICHE-bandit-manchot-additions-cp.md`
    (criteres coches et reserves).
  - Code et ressources : `adapters/adapter-web/src/main/resources/static/vendor/provenance.txt`,
    `bootstrap.min.css`, `htmx.min.js` ; `bootstrap/src/test/java/com/recreo/archi/
    LesGardeFousSontMisALEpreuveTest.java` ; `adapters/adapter-web/src/test/java/com/recreo/
    adapter/web/RessourcesTiercesTest.java` et ses fixtures ; les gabarits `accueil.html`,
    `fragments/manche.html`, `fragments/retour.html` ; `adapters/adapter-content/src/main/
    resources/catalogue.yaml`, `cp-additions-jusqu-a-10.yaml`, `cp-le-bon-son.yaml`,
    `cm1-homophones.yaml`.

## Methode

Pour chaque livrable non executable : verification des conventions de redaction de `CLAUDE.md`
(tirets simples, guillemets droits, absence d'emoji ou de symbole Unicode decoratif, absence de
commentaire qui paraphrase le code, absence de chemin absolu de poste de travail, de nom
d'utilisateur ou de credential) ; puis verification que la documentation decrit ce que le code fait
reellement. Pour les deux paquets de contenu francais neufs, relecture caractere par caractere de
chaque enonce, bonne reponse et distracteur (accents, apostrophes, majuscules accentuees,
ponctuation), sans juger le niveau scolaire ni le choix des mots.

## Ce qui a ete verifie et est conforme

### Conventions de redaction

- **Aucun tiret cadratin, aucun guillemet typographique, aucun emoji ni symbole Unicode decoratif**
  dans l'ensemble des livrables lus (recherche par expression sur `—`, `" " ' '`, et sur les plages
  Unicode des pictogrammes decoratifs : aucune correspondance dans les fichiers de ce cycle).
  `ADR-0014` documente explicitement, et a raison, que `bootstrap.min.css` contient un tiret
  cadratin tiers dans une regle qu'on ne sert pas : c'est un fichier vendorise jamais retouche
  (`provenance.txt`), donc hors du perimetre des conventions de redaction du depot - je confirme la
  lecture de `theo-archi` plutot que de la re-signaler comme une faute.
- **Aucun chemin absolu de poste de travail, nom d'utilisateur ou empreinte de machine.** Verifie en
  particulier avec vigilance sur `ADR-0014` (coordonnees et empreintes de la bibliotheque
  vendorisee : toutes portables - `groupId:artifactId:version`, `npm:nom:version`, SHA-256 - aucune
  ne nomme un poste), sur les rapports qui citent des commandes lancees par des agents
  (`squad/sprints/s02-identite-visuelle/rapport-qualite.md`,
  `confirmation-archunit.md` ; `squad/sprints/s03-completer-mathematiques-cp/rapport-qualite.md`) -
  toutes les occurrences de `JAVA_HOME` sont ecrites sous forme portable
  (`JAVA_HOME=<JDK compatible avec java.version=21>`, ou explicitement qualifiees "jamais ecrit dans
  un fichier commite"), et sur les mentions de `~/.m2/settings.xml` - uniquement dans `ADR-0010`
  (pre-existant, hors perimetre de ce cycle) comme exemple de forme portable acceptee, jamais un
  chemin reel. `theo-archi` a lui-meme fait tourner `verifier-chemins.sh` sur le materiau
  d'`ADR-0014` et rapporte un resultat propre ; je le confirme par lecture directe plutot que de me
  fier au seul script.
- **Aucun commentaire qui paraphrase le code** dans les fichiers neufs ou modifies (`RessourcesTiercesTest`,
  `LesGardeFousSontMisALEpreuveTest`, les gabarits touches par S3-6/ADR-0014) : chaque commentaire
  ajoute une reference (ADR, story, motif de refus) que la signature seule ne porte pas.
- **Coherence terminologique** : "manche", "partie", "jeu", "mecanique", "contenu", "paquet",
  "descripteur", "distracteur" restent employes de facon stable entre le code, les YAML, les ADR/DEC
  et la fiche de conformite.

### Exactitude de la documentation contre le code

- **`ADR-0014` contre `static/vendor/`** : l'ADR affirme deux fichiers vendorises
  (`bootstrap.min.css` de `org.webjars:bootstrap:5.3.8`, `htmx.min.js` de `npm:htmx.org:2.0.10`),
  chacun trace par une ligne a quatre champs dans `provenance.txt` avec une empreinte SHA-256
  precise. Verifie : `provenance.txt` contient exactement ces deux lignes, avec les empreintes
  `d85327d9...dede` et `71ea6718...c0de` citees par l'ADR. Conforme.
- **`ADR-0013` contre `LesGardeFousSontMisALEpreuveTest.java`** : le fichier reel porte
  `DETTE_DE_BALAYAGE` a quatre entrees (`AucunVocabulaireDeMatiereTest`, `FaviconTest`,
  `FrontieresDuJavaScriptTest`, `HabillageDesEcransTest`) et `PLAFOND_DE_DETTE = 4`, avec un
  commentaire qui retrace la descente 6 -> 5 (s02, `TexteAccentueDesGabaritsTest`) puis 5 -> 4 (s03,
  `TailleDesTextesEtBoutonsTest`, via `ADR-0014`). Conforme au recit fait par `ADR-0013`,
  `ADR-0014` et `rapport-qualite.md` de s03.
- **`DEC-0008` contre `catalogue.yaml` et `MATRICE-couverture.md`** : les deux jeux
  (`bandit-manchot-le-bon-son-cp`, `bandit-manchot-homophones-cm1`) et leurs paquets
  (`cp-le-bon-son`, `cm1-homophones`) existent tels que decrits ; les deux lignes de matrice
  concernees (francais CP - graphemes/phonemes, francais CM1 - homophones grammaticaux) sont bien
  passees de `non couvert` a `en cours`, avec jeu et mecanique renseignes. Conforme.
- **`FICHE-bandit-manchot-additions-cp.md` contre `DEC-0007`** : les quatre criteres que
  `nadia-prof` avait confirmes satisfaits en s01 sont coches avec la mention explicite du retard de
  tenue a jour ; les deux criteres nouveaux (indice atteignable, repartition des sommes) sont
  ajoutes non coches, en attente d'audit. Le texte d'indice cite ("Compte sur tes doigts.") est
  identique caractere pour caractere entre la fiche, la reserve 4 du verdict s01 et le paquet
  `cp-additions-jusqu-a-10.yaml` ligne 12. Conforme.
- **Fixtures de contre-exemple contre les tableaux d'`ADR-0014`** : les sept jeux fautifs annonces
  pour `RessourcesTiercesTest` et les deux pour `TailleDesTextesEtBoutonsTest` existent tous sur le
  disque, sous les noms exacts cites par l'ADR. Conforme.

Aucune divergence trouvee entre ce que ces documents affirment et ce que le depot contient
reellement.

## Corrections appliquees

Aucune. Je n'ai trouve, dans le perimetre de ce cycle, ni faute de redaction (tiret cadratin,
guillemet typographique, emoji, chemin de poste, paraphrase), ni ecart entre une documentation et le
code qu'elle decrit, qui appellerait une correction sans ambiguite.

## Relecture caractere par caractere des deux paquets de contenu francais neufs

### `adapters/adapter-content/src/main/resources/contenu/cp-le-bon-son.yaml`

24 items relus un a un (enonce en majuscules, bonne reponse et distracteurs entre crochets). Aucune
faute d'accentuation, d'apostrophe ou de ponctuation trouvee. En particulier, les trois mots que
`lea-dev` signale avoir vus perdre puis retrouver leurs accents portent bien tous leurs signes
diacritiques : `ÉTOILE` (ligne 58, E accent aigu majuscule), `CHÂTEAU` (ligne 64, A accent
circonflexe), `BALANÇOIRE` (ligne 73, C cedille). Aucun autre mot du paquet n'a d'accent manquant ou
en trop (`MAMAN`, `DENT`, `PONT`, `BALLON`, `LAPIN`, `JARDIN`, `LOUP`, `GENOU`, `ROI`, `BOIS`,
`CHAT`, `VACHE`, `SOURIS`, `POULE`, `SAPIN`, `MONTRE`, `NICHE`, `MOUCHE`, `MANTEAU`, `MOUTON`,
`PINCEAU` : aucun n'en requiert). Rien a corriger.

### `adapters/adapter-content/src/main/resources/contenu/cm1-homophones.yaml`

10 items relus un a un. Accentuation correcte sur `à`, `où`, apostrophes droites correctes
(`l'école`). Ponctuation verifiee contre la convention deja en usage dans les gabarits (espace avant
`!` et `?`, deja utilisee dans `fragments/retour.html` : "Bravo, c'est juste !", "C'est fini !",
"Encore !") : les deux phrases interrogatives du paquet ("___ vas-tu ?", "Tu veux du thé ___ du
café ?") suivent la meme convention. Grammaire des couples verifiee un par un contre la phrase qui
les porte (a/à, et/est, son/sont, on/ont, ou/où) : chaque bonne reponse et son distracteur forment
bien une paire d'homophones grammaticaux, et la phrase impose une seule lecture grammaticalement
correcte. Rien a corriger.

### `adapters/adapter-content/src/main/resources/catalogue.yaml` - les deux nouvelles entrees

Titres et consignes des deux jeux neufs relus : "Le bon son" / "Quel son entends-tu ?" (lignes
10-11) et "Le bon petit mot" / "Quel mot complète la phrase ?" (lignes 16-17). Accentuation et
orthographe correctes (`entends-tu` : inversion sujet-verbe avec trait d'union correctement place ;
`complète` : accent grave correct sur la troisieme personne du present de l'indicatif de
"completer"). Rien a corriger.

## Libelles remontes a camille-po et nadia-prof

Ce n'est pas mon perimetre : ce registre est gouverne par la porte pedagogique de `nadia-prof`
(`DEC-0002` et son amendement du 2026-08-04), pas par les conventions de redaction du depot. Je
liste ici, sans les corriger ni en juger le fond, tous les libelles destines a l'enfant introduits
ou modifies depuis s01, pour qu'ils passent devant l'audit de `nadia-prof`.

| Texte | Emplacement | Origine / statut |
|---|---|---|
| "Récréo" (titre de la page et `<h1>`, remplace "Recreo") | `adapters/adapter-web/src/main/resources/templates/accueil.html:6` et `:22` | S3-9, `DEC-0007` point 7. Typographie verifiee correcte ; contenu non juge par moi |
| "Compte sur tes doigts." (indice, remplace "Compte avec tes doigts pour t'aider.", deplace du domaine vers le YAML) | `adapters/adapter-content/src/main/resources/contenu/cp-additions-jusqu-a-10.yaml:12` | S3-3 (execution de `DEC-0005`). Reformulation deja suggeree mot pour mot par `nadia-prof` en s01 (reserve 4) ; reste a auditer sur son atteignabilite en jeu (S3-2), pas sur son texte |
| "Le bon son" (titre du jeu) | `adapters/adapter-content/src/main/resources/catalogue.yaml:10` | Nouveau ce cycle, `DEC-0008`, story S4-2 |
| "Quel son entends-tu ?" (consigne) | `adapters/adapter-content/src/main/resources/catalogue.yaml:11` | Idem |
| "Le bon petit mot" (titre du jeu) | `adapters/adapter-content/src/main/resources/catalogue.yaml:16` | Nouveau ce cycle, `DEC-0008`, story S4-1 |
| "Quel mot complète la phrase ?" (consigne) | `adapters/adapter-content/src/main/resources/catalogue.yaml:17` | Idem |
| 24 enonces (mots), bonnes reponses et distracteurs (notations phonemiques entre crochets) | `adapters/adapter-content/src/main/resources/contenu/cp-le-bon-son.yaml:10-81` | Nouveau ce cycle, `DEC-0008`, story S4-2. Notation entre crochets et choix des mots deja signales par `DEC-0008` elle-meme comme un point pour `nadia-prof` |
| 10 phrases a trou, bonnes reponses et distracteurs | `adapters/adapter-content/src/main/resources/contenu/cm1-homophones.yaml:10-39` | Nouveau ce cycle, `DEC-0008`, story S4-1 |

Aucune faute typographique ou orthographique ne motive cette liste : je les remonte parce qu'ils
sont nouveaux ou modifies, pas parce que j'y ai trouve une erreur. Le jugement pedagogique reste
entierement a `nadia-prof` et le jugement produit (choix des titres, des consignes, des competences
visees) reste a `camille-po`.

## Points a trancher hors de mon mandat

Rien d'autre a signaler. Les obstacles de gouvernance ouverts par s02 et s03 (rapport qualite et
confirmation ArchUnit rendus a posteriori pour s02, comptage divergent de `LesReglesMordentTest`,
concordance des tags a quatre emplacements plutot que trois, trou d'audit sous ~500 px) sont deja
routes vers `yanis-qa`, `theo-archi` et `sofia-sm` par leurs propres documents ; ce sont des
questions de process et de qualite, pas de redaction, et je ne les tranche pas.
