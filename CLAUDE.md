# recreo

App web de révision scolaire pour les enfants de **CP (cycle 2)** et **CM1 (cycle 3)**, sous
forme de mini-jeux, visant à couvrir **toutes les matières** du programme de l'Éducation
nationale (le calcul mental — bandit manchot — n'est que la première tranche). Le principe
directeur du produit : découpler une **mécanique de jeu réutilisable** (`domain/mechanic`) d'un
**contenu pédagogique piloté par des règles** (`domain/content`, `DifficultyConfig`/`RuleSet`),
afin qu'une même mécanique serve n'importe quelle matière/niveau sans modification de code.
`squad/programme/MATRICE-couverture.md` pilote l'atteinte de la couverture complète du programme.

## Stack

- **Back** : Java 21 (LTS), Clean Architecture (`domain` → `application` → `adapters` →
  `bootstrap`), Spring Boot uniquement dans les adapters et `bootstrap`.
- **Build** : Maven multi-module (POM parent + `domain`, `application`, `adapters/adapter-web`,
  `adapters/adapter-persistence`, `bootstrap`).
- **Front** : htmx + Thymeleaf (rendu serveur, fragments HTML — jamais de JSON côté vues),
  packagé dans un **fat jar unique** (`spring-boot-maven-plugin`) → déploiement
  `java -jar bootstrap/target/recreo.jar`, H2 embarqué, zéro build Node, zéro serveur front
  séparé.
- **Persistence** : JPA + H2 (dev/test), PostgreSQL visé en prod.

## Règles clés

- **Clean Architecture stricte** : les dépendances vont toujours vers l'intérieur. `domain` n'a
  0 dépendance framework ; `application` définit les ports (in/out) sans Spring ; les adapters
  implémentent ces ports ; `bootstrap` est la seule couche qui voit Spring et tous les adapters
  (wiring `@Bean`).
- **Toute règle d'architecture doit être encodée en test ArchUnit** (`bootstrap/src/test`) —
  une règle non exécutable n'est pas une règle. Voir notamment le découplage
  mécanique/contenu (`domain.mechanic..`/`domain.mechanics..` ne dépendent d'aucun générateur de
  contenu concret) et l'isolation des mécaniques entre elles.
- **Commits préfixés par agent** : chaque agent de la squad préfixe tous ses commits par son tag
  — `[CAM]` Camille (PO), `[SOF]` Sofia (SM+dev), `[MARC]` Marc (dev), `[LEA]` Léa (dev), `[THEO]`
  Théo (archi), `[YAN]` Yanis (QA). Permet d'isoler la production d'un agent avec
  `git log --grep='^\[TAG\]'`.
- **Secrets dans `.env`, jamais commité** : le `GITHUB_TOKEN` et tout autre secret vivent dans
  `.env` (`.gitignore` l'exclut). `.env.example` documente les variables avec des placeholders.
  Aucun secret n'est écrit en dur dans le code, les prompts d'agents ou les fichiers commités.
- **Le dépôt Git est la seule source de vérité.** Ce qui n'est pas écrit dans le dépôt n'existe
  pas : une cérémonie sans fichier dans `squad/ceremonies/` n'a pas eu lieu, une décision non
  rédigée en `ADR`/`DEC` n'a pas été prise, un test non écrit ne couvre rien.
- **Double « Done »** : Done **technique** = CI verte (`mvn verify` : compile + tests unitaires +
  intégration H2 + ArchUnit), jugé objectivement par la CI. Done **pédagogique** = prononcé
  **uniquement par l'utilisatrice**, après démo — aucun agent ne le prononce.

## La squad

Six sous-agents Claude Code (`.claude/agents/`) forment la squad qui construit `recreo` :
`camille-po` (PO), `sofia-sm` (Scrum Master + dev), `theo-archi` (architecte), `yanis-qa` (QA),
`marc-dev` / `lea-dev` (devs seniors). Le process (cérémonies, décisions, matrice de couverture,
conformité, file de validation) est documenté et tenu à jour dans **[`squad/README.md`](squad/README.md)**
— s'y référer pour le déroulé d'un sprint, les conventions de nommage, la règle d'or de validation
pédagogique et les frontières entre agents.
