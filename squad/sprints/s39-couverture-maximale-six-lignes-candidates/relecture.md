# Relecture - s39 - couverture maximale sur six lignes candidates

- **Auteur** : `relecteur-pedagogique`.
- **Date** : 2026-09-22.
- **Sprint** : s39.
- **Arbre relu** : `round-two` a `237fb3b8` (cloture de s39), worktree dedie
  `../recreo-s39-relecteur` (branche `s39-relecteur`), base `542c1258` (cloture de s38).
- **Perimetre** : tous les livrables non executables du sprint (`ADR-0097` amende, `ADR-0098`,
  `DEC-0092`, `ADR-index.md`, `DEC-index.md`, `REGISTRE-identifiants.md`, six fiches de conformite,
  `ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md`, `MATRICE-couverture.md`,
  `docs/backlog/backlog.md` section "Sprint 39", et tous les fichiers de
  `squad/sprints/s39-couverture-maximale-six-lignes-candidates/`) et le contenu enfant des six
  paquets YAML neufs (`ce1-monnaie`, `cm1-sciences-energie`, `cp-arts-outils`, `ce1-solides`,
  `ce1-longueurs`, `cp-nombres-jusqu-a-100`) et de leurs deux fixtures de test associees.

## 1. Corrections de langue appliquees

- **`cp-nombres-jusqu-a-100.yaml`**, item "ecrire" du nombre 71 : *"soixante-et-onze"* ->
  *"soixante et onze"*. Le nombre 71 fait partie des dizaines liees par "et" (21/31/41/51/61/71),
  que la convention classique ecrit sans trait d'union - regle que le commentaire d'en-tete du
  fichier declare lui-meme ("traits d'union de la convention classique, 'et' sur
  21/31/41/51/61/71 seulement"). Seule occurrence de ce cas dans les six paquets neufs.
- **`DEC-0092-...md`**, section 3 : *"Ceci est **une** sourcage plus mince"* -> *"Ceci est **un**
  sourcage plus mince"* - accord de genre (`sourcage` est masculin, comme le confirme le pronom
  "celui" employe juste apres dans la meme phrase).
- **`planning.md`**, section 5 : *"Regle **geleee** pour s39"* -> *"Regle **gelee** pour s39"* -
  faute de frappe (lettre en trop), a cote de cinq autres occurrences correctes de "gele"/"gelee"
  dans le meme fichier.
- **`docs/backlog/backlog.md`**, quatre des six titres de story s39 (`S39-LONGUEUR`, `S39-MONNAIE`,
  `S39-SOLIDES`, `S39-ENERGIE`) portaient leur titre coupe sur deux lignes, chacune prefixee `###` :
  ceci cree deux titres Markdown distincts au lieu d'un seul (le second titre, tronque, n'a aucun
  sens hors contexte). Fusionnes en un seul titre par ligne, sur le modele des deux titres qui
  tenaient deja sur une seule ligne (`S39-DEN`, `S39-OUTILS`).
- **`adapters/adapter-content/.../contreexemple/.../paquet-solides-ce1.yaml`** (jeu fautif
  d'`ADR-0098`, jamais servi) : toutes les apostrophes du commentaire d'en-tete et de l'indice du
  premier item avaient disparu (*"d ADR-0098"*, *"elle l est"*, *"C est"*, *"l elargissement"*,
  *"l allocation"*, *"d un de a jouer"*), en rupture avec la convention du reste des fixtures
  `contreexemple/` du depot (apostrophes correctement placees). Restituees. Verifie que le texte
  corrige n'est lu par aucune assertion du test associe (`ChaquePaquetDeMathematiquesDeCe1DeclareSa
  SousPartieTest` ne compare ni l'`indice` ni le commentaire).

## 2. Verifications sans defaut trouve

- Les six paquets YAML neufs (`ce1-monnaie`, `cm1-sciences-energie`, `cp-arts-outils`,
  `ce1-solides`, `ce1-longueurs`, `cp-nombres-jusqu-a-100`) : francais correct et integralement
  accentue sur les enonces, bonnes reponses, distracteurs et indices, ponctuation conforme a
  `TYP-1` (espace insecable devant `?`), au-dela de la seule correction de la section 1.
- Les six consignes et titres neufs de `catalogue.yaml` : francais correct et accentue.
- `ADR-0097` (amendement), `ADR-0098`, `DEC-0092`, `ADR-index.md`, `DEC-index.md`,
  `REGISTRE-identifiants.md` : coherence terminologique tenue, aucune faute trouvee au-dela de la
  section 1.
- Les six fiches de conformite (`FICHE-bandit-manchot-{monnaie-ce1,energie-cm1,outils-arts-cp,
  solides-ce1,longueurs-ce1,nombres-jusqu-a-100-cp}.md`) et
  `ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md` : aucune faute trouvee.
- `planning.md`, `avis-faisabilite.md` (749 lignes), `rapport-qualite.md`,
  `contrainte-ines-solides-ce1.md` : aucune faute trouvee au-dela de la section 1.
- `MATRICE-couverture.md` (synthese s39) : arithmetique de la synthese verifiee (78 + 4 = 82,
  77 - 6 = 71, 82 / 155 = 52,9 % arrondi) - exacte.
- Verification de la convention MASTER (`DEC-0062`) sur `DEC-0092` et `planning.md` : les deux
  citations forme 3 sont mot pour mot identiques entre les deux fichiers (l'objectif recu et la
  confirmation "ok") ; aucun enonce recu de MASTER ne sert de motif a une decision ; la forme 2
  (mot cite de l'utilisatrice, *"ok"*) porte sa date. Aucun ecart trouve.
- Balayage programmatique de tous les fichiers `.md`/`.yaml` modifies dans le diff s39 : aucun
  tiret cadratin, aucune guillemet typographique, aucun emoji, aucun chemin absolu de poste de
  travail ni nom de compte au-dela du texte deja verifie par `verifier-chemins.sh`.
- Les SVG neufs (`solide-{cube,pave-droit,boule,cylindre,cone}-{a,b,c}.svg`) et leurs deux fixtures
  de test (`ce1-solides-fautif.yaml`, `boule-fautive.svg`) : commentaires en francais correct,
  aucun texte enfant dans les dessins.

## 3. Points a trancher

Aucun. Toutes les corrections trouvees sont des corrections de langue ou de redaction sans
ambiguite, appliquees directement.

## Verification avant commit

`bash outils/mvn-jdk21.sh -B clean verify` sur la racine du worktree `s39-relecteur`,
**BUILD SUCCESS**, JDK 21.0.10 rapporte par la machine virtuelle des tests. Les tests touchant les
deux fichiers YAML modifies (`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`,
`PaquetCpNombresJusquA100Test`) verifies isolement avant la mesure complete : 23 tests, 0 echec.
`bash .github/scripts/verifier-chemins.sh` sur l'ensemble du diff -> vert : *"Aucun chemin de poste
de travail ni nom de compte dans les fichiers verifies."* Les jobs `prefixes` et `outils` de
`.github/workflows/ci.yml` n'ont pas ete lances ici et ne sont donc pas annonces verts sur la foi
de cette relecture.

## Commits

Prefixes `[REL]`, sur la branche `s39-relecteur` (worktree dedie, base `round-two` a `237fb3b8`).
