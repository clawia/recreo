# Relecture - s46 (`relecteur-pedagogique`)

- **Date** : 2026-10-02.
- **Arbre** : `round-two` a `cd8af48f` (cloture de s46, deja fusionnee), worktree dedie
  (`git worktree add ../recreo-s46-relecture -b s46-relecture round-two`), lignee verifiee
  (`bash outils/verifier-lignee.sh round-two`, vert avant la premiere ecriture).
- **Perimetre relu** : `planning.md`, `DEC-0102`, `DEC-0103`,
  `squad/programme/francais/competences-ce1.md`,
  `ARBITRAGE-PREALABLE-s46-trois-instances-refusees-...md`,
  `FICHE-bandit-manchot-soustractions-posees-ce1.md` et l'amendement dans
  `ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`, `avis-faisabilite-s46-cinq-lots.md`,
  `rapport-qualite.md`, `confirmation-archunit.md`, `docs/backlog/backlog.md` section "Sprint 46",
  et les commentaires (hors contenu enfant) des cinq paquets neufs et de leurs tests, Java et YAML.

## Corrections appliquees

1. **Nom de methode de test incoherent avec la grammaire francaise**,
   `adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCe1SoustractionsPoseesTest.java` :
   `aucuneLettreNiVirguleEtTouteLePropositionEstNumerique` porte un article en trop
   (`Toute Le Proposition`). Renomme `aucuneLettreNiVirguleEtToutePropositionEstNumerique`. Aucune
   reference externe (methode `@Test`, decouverte par reflexion), compilation et les 13 tests du
   fichier revalides.
2. **Incoherence de redaction signalee par `yanis-qa`** (`rapport-qualite.md`, section 4) :
   les deux fichiers de sciences (`PaquetCm1SciencesMouvementTest.java`,
   `PaquetCm1SciencesPhenomenesTest.java`) precisent sur leur methode
   `chaqueItemDeclareUniquement<Ligne>` que l'exclusivite de la ligne de referentiel est "deja
   tenu par ADR-0081, non repete ici" ; les deux fichiers d'EMC
   (`PaquetCm1EmcEmotionRessentirAgirTest.java`, `PaquetCm1EmcInstitutionsTest.java`) ne le
   disaient pas, laissant croire a tort que le test verifie l'exclusivite. Complete les deux
   commentaires a l'identique des fichiers de sciences.

**Verification apres correction** : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am
test -Dtest=PaquetCe1SoustractionsPoseesTest,PaquetCm1EmcEmotionRessentirAgirTest,PaquetCm1EmcInstitutionsTest
-Dsurefire.failIfNoSpecifiedTests=false`, 27 tests, 0 echec (mesure partielle sur
`adapter-content`, perimetre nomme conformement a `CLAUDE.md` racine). Garde-fou de chemins
(`git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash
.github/scripts/verifier-chemins.sh`) vert sur l'arbre corrige.

## Verifie, sans defaut trouve

- **Attribution MASTER / DEC-0062** : aucun enonce de fait ou chiffre du perimetre ne s'appuie sur
  une forme hors des trois prevues ; `DEC-0102` cite sa commande en forme 3 (instruction directe,
  meme canal que `DEC-0098`/`DEC-0101`) correctement formee.
- **References croisees** : tous les fichiers cites existent et disent ce que la citation leur
  pretend (`FICHE-bandit-manchot-soustractions-posees-ce1.md` section 2,
  `ARBITRAGE-PREALABLE-s43-...md` section 5.2 et son amendement du 2026-10-01,
  `ARBITRAGE-PREALABLE-s30-...md` section 2.2 lignes 3 et 16,
  `squad/programme/enseignement-moral-et-civique/competences-cm1.md` section "Conditions
  opposables" et sa precision du 2026-10-01, `squad/rapports/avis-faisabilite-s46-cinq-lots.md`).
- **Chemins absolus, tirets, guillemets, emoji** : aucune occurrence dans les livrables neufs ou
  modifies de s46 (code et squad confondus), verifie par recherche automatisee.
- **Coherence terminologique** : `MAT-CE1-05`, `EMC-CM1-01`, `EMC-CM1-09`, `SCI-CM1-03`,
  `SCI-CM1-16` nommees de facon identique dans `DEC-0102`, l'avis de faisabilite, le backlog, les
  fiches de conformite et le code.

## Signale, non tranche

Aucun point a trancher par `camille-po` ou `theo-archi` sur ce perimetre.
