# recreo

App web de revision scolaire pour les enfants de **CP (cycle 2)** et de **CM1 (cycle 3)**, sous
forme de mini-jeux, visant a couvrir **toutes les matieres** du programme de l'Education
nationale.

Le principe directeur du produit : decoupler une **mecanique de jeu reutilisable**
(`domain/mechanic`) d'un **contenu pedagogique pilote par des regles** (`domain/content`), afin
qu'une meme mecanique serve n'importe quelle matiere et n'importe quel niveau sans modification
de code. `squad/programme/MATRICE-couverture.md` pilote l'atteinte de la couverture complete du
programme.

> Cette branche, `round-two`, est une reconstruction **from scratch**. La premiere version vit
> sur `main` et reste consultable, mais elle n'est pas une reference a suivre : ses dettes sont
> listees plus bas et sont a ne pas reproduire.

## Stack

- **Back** : Java 21 (LTS), Clean Architecture (`domain` -> `application` -> `adapters` ->
  `bootstrap`), Spring Boot uniquement dans les adapters et `bootstrap`.
- **Build** : Maven multi-module (POM parent + `domain`, `application`, `adapters/adapter-web`,
  `adapters/adapter-persistence`, `bootstrap`).
- **Front** : htmx + Thymeleaf (rendu serveur, fragments HTML, jamais de JSON cote vues),
  package dans un **fat jar unique** (`spring-boot-maven-plugin`) et deploye avec
  `java -jar bootstrap/target/recreo.jar`. H2 embarque, zero build Node, zero serveur front
  separe.
- **Persistence** : JPA + H2 (dev et test), PostgreSQL vise en production.

Le decoupage exact en modules est une decision d'architecture : il est acte en `ADR` par
`theo-archi`, pas presuppose par ce fichier.

## Regles cles

- **Clean Architecture stricte** : les dependances vont toujours vers l'interieur. `domain` n'a
  aucune dependance de framework ; `application` definit les ports (in et out) sans Spring ; les
  adapters implementent ces ports ; `bootstrap` est la seule couche qui voit Spring et tous les
  adapters.
- **Toute regle d'architecture doit etre encodee en test ArchUnit.** Une regle non executable
  n'est pas une regle, c'est un souhait. Un `ADR` sans sa regle ArchUnit ecrite dans le meme
  geste n'est pas complet.
- **Les tests sont ecrits dans le meme commit que le code**, dans **tous** les modules, des le
  premier commit de code.
- **Commits prefixes par agent** : `[CAM]` Camille (PO), `[SOF]` Sofia (SM et dev), `[THEO]`
  Theo (archi), `[MARC]` Marc (dev), `[LEA]` Lea (dev), `[YAN]` Yanis (QA), `[REL]` relecteur
  pedagogique, `[NAD]` Nadia (professeure des ecoles). La CI **rejette** un message de commit
  sans tag connu.
- **Secrets dans `.env`, jamais commite.** `.gitignore` l'exclut. Aucun secret n'est ecrit en dur
  dans le code, dans un prompt d'agent ou dans un fichier commite.
- **Le depot Git est la seule source de verite.** Ce qui n'est pas ecrit dans le depot n'existe
  pas : une ceremonie sans fichier dans `squad/sprints/` n'a pas eu lieu, une decision non
  redigee en `ADR` ou `DEC` n'a pas ete prise, un test non ecrit ne couvre rien.
- **Double Done.** **Technique** = CI verte (`mvn verify` : compilation, tests unitaires,
  integration H2, ArchUnit), juge objectivement par la CI. **Pedagogique** = prononce
  **uniquement par `nadia-prof`**, apres avoir joue l'increment, avec un motif ecrit dans
  `squad/conformite/FICHE-<jeu>.md`. Les deux sont independants : un increment techniquement
  irreprochable peut etre refuse pedagogiquement.

## Conventions de redaction

Elles s'appliquent a tout le depot, code et documentation, et sont verifiees par le
`relecteur-pedagogique` :

- Tirets simples et guillemets droits. Pas de tiret cadratin, pas de guillemets typographiques.
- Pas d'emoji ni de symbole Unicode decoratif.
- Les caracteres accentues sont normaux dans le contenu redactionnel francais ; les messages de
  commit et les identifiants de code restent en ASCII.
- Pas de commentaire qui paraphrase le code.

## La squad

Huit sous-agents Claude Code (`.claude/agents/`) construisent `recreo` : `camille-po` (PO),
`sofia-sm` (Scrum Master et troisieme developpeuse), `theo-archi` (architecte), `marc-dev` et
`lea-dev` (developpeurs seniors), `yanis-qa` (QA), `relecteur-pedagogique` (relecture des
livrables non executables) et `nadia-prof` (professeure des ecoles, **seule autorite sur le Done
pedagogique**).

Le process complet - ceremonies, decisions, matrice de couverture, conformite, rapports de
sprint, consommation de tokens - est documente dans **[`squad/README.md`](squad/README.md)**.
Le roster lisible est dans **[`squad/EQUIPE.md`](squad/EQUIPE.md)**.

## Dettes de la version precedente, a ne pas reproduire

Constatees sur `main` (13 226 lignes de Java, 25 sprints). Ce sont des contraintes de conception,
pas des decouvertes a faire plus tard :

1. **Une abstraction centrale documentee partout mais jamais implementee.** `RuleSet` etait cite
   dans `CLAUDE.md` et dans quatre prompts d'agents ; il n'existait dans aucun fichier Java. Soit
   une abstraction existe dans le code, soit un `ADR` acte explicitement son abandon.
2. **Une configuration de difficulte taillee pour l'arithmetique** (`minOperand`, `maxOperand`,
   `operations`), que chaque contenu de francais devait remplir de valeurs factices. La
   configuration doit etre generique ou typee par matiere.
3. **Neuf familles de chargeurs de contenu quasi identiques**, environ 1 200 lignes dupliquees
   dans `bootstrap`. Un seul chargeur, pilote par un schema.
4. **Le comportement des mecaniques loge dans le JavaScript client** : 692 lignes qu'aucun test
   ne couvrait, derriere huit classes Java de 55 lignes qui ne faisaient que deleguer. Ce qui
   peut etre teste cote serveur doit l'etre.
5. **Des modules entiers sans aucun test** : `application`, `adapter-web` et
   `adapter-persistence`. Et des tests qui assertionnaient le HTML par expression reguliere,
   donc cassants au moindre changement de balisage.
6. **Des records tres larges avec des methodes de copie ecrites a la main** (16 et 27 champs),
   ou ajouter un champ obligeait a editer une quinzaine d'appels.
7. **Un garde-fou annonce mais absent** : la documentation affirmait que la CI rejetait les
   commits sans prefixe d'agent ; le workflow ne contenait aucune etape de ce genre. Sur
   `round-two`, il existe.

## Contenu repris

`contenu-repris/` contient 61 paquets YAML issus de `main`. C'est une **reserve de matiere
premiere**, pas un cahier des charges et pas du contenu cable. Tout contenu repris doit repasser
devant `nadia-prof` comme un contenu neuf. Voir `contenu-repris/README.md`.
