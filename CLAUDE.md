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
  `adapters/adapter-content`, `bootstrap`).
- **Front** : htmx + Thymeleaf (rendu serveur, fragments HTML, jamais de JSON cote vues),
  package dans un **fat jar unique** (`spring-boot-maven-plugin`) et deploye avec
  `java -jar bootstrap/target/recreo.jar`. Zero build Node, zero serveur front separe.
- **Persistence** : aucune, par decision produit du 2026-08-14 (`DEC-0028`). Recreo ne conserve ni
  progression, ni score, ni partie en cours entre deux sessions : l'etat d'une partie vit dans la
  session HTTP et disparait avec elle. Le module `adapters/adapter-persistence` n'existe pas et ne
  sera pas cree pour ce motif ; JPA, H2 et PostgreSQL cessent d'etre une cible du produit (voir
  `ADR-0001`, `DEC-0003`). Un besoin d'ecrire quelque chose de durable pour un **autre** motif
  resterait a justifier le moment venu.

Le decoupage exact en modules est une decision d'architecture : il est acte en `ADR` par
`theo-archi`, pas presuppose par ce fichier.

## Prerequis de build

Deux exigences, declarees dans le POM parent et donc verifiees a chaque `mvn`, des la phase
`validate` :

- une **toolchain de type `jdk` et de version `21`** dans le fichier de toolchains de
  l'utilisateur (`~/.m2/toolchains.xml`), qui designe le JDK de compilation et d'execution des
  tests. Sans elle, `maven-toolchains-plugin` arrete le build en nommant le type, la version et le
  fichier a renseigner. En CI, `actions/setup-java` ecrit cette toolchain lui-meme ;
- **Maven lance sur un JDK 17 ou plus recent** : `spring-boot-maven-plugin` ne peut pas etre
  charge par une machine virtuelle plus ancienne, et une toolchain ne gouverne pas la machine
  virtuelle de Maven. Sans cela, `maven-enforcer-plugin` arrete le build avec la marche a suivre.

Sur un poste dont le `java` du `PATH` n'est pas un JDK 21, `bash outils/mvn-jdk21.sh -B clean
verify` lance Maven sur le JDK que designe la toolchain declaree, ou **refuse** : plus besoin de
positionner `JAVA_HOME` a la main a chaque commande.
Amendement du 2026-08-31 (`sofia-sm`, s25, lot F) : cette phrase se terminait par *"et la version
sur laquelle on a mesure est celle de la toolchain"*. C'est la formule qu'`ADR-0071` disqualifie
comme methode - la version d'une mesure est celle que la **machine virtuelle des tests rapporte**,
jamais celle qu'une entree de toolchain declare, et les deux se sont averees differentes sur ce
poste. L'enveloppe demande desormais sa version au JDK qu'elle a resolu, et **sort en erreur sans
lancer Maven** quand ce JDK dement l'entree qui le designe. Ce refus paie la dette ouverte par
`S21-6` le 2026-08-27 - *"il doit refuser au lieu d'imprimer"* - documentee quatre fois sans etre
payee, dont une ou l'avertissement imprime a ete filtre par son propre auteur le jour ou il
l'ecrivait. Il est mordu par `outils/mvn-jdk21-test.sh`, qui verifie que **Maven n'est pas lance**.

Une troisieme exigence s'ajoute aux deux precedentes, **d'une autre nature : aucun `mvn` ne la
verifie**, aucun plugin ne l'arrete, et c'est exactement pourquoi elle est ecrite ici.

- **Toute mesure partielle du reacteur porte `-am`.** `mvn -pl <module> test` sans `-am` ne
  construit pas les modules dont `<module>` depend : Maven les resout depuis le depot local de
  l'utilisateur (`~/.m2/repository`), ou dort la derniere version **installee**, qui peut etre plus
  ancienne que l'arbre qu'on croit mesurer. **Le mode de defaillance n'est pas l'echec, c'est
  l'ecart muet** : la commande reussit, le chiffre s'affiche, et il decrit un autre arbre que celui
  du worktree. Cela vaut pour n'importe quel `-pl`, par n'importe quel agent, sur un test comme sur
  un comptage.

Ce que cette exigence coute a omettre, et pourquoi la formule "juste cette fois" ne tient pas : un
tel ecart a ete mesure le 2026-08-17 sur un garde-fou de contenu, **avec et sans `-am` sur le meme
arbre et sans aucune mutation**, et les deux executions ne rendent pas le meme verdict.
`squad/sprints/s14-points-d-entree-par-niveau/avis-faisabilite.md`, section 12, porte le chiffre avec
sa methode ; il n'est pas recopie ici, pour qu'il ne puisse pas y pourrir a moitie. *Cette phrase a
renvoye du 2026-08-17 au 2026-08-19 vers "`ADR-0045`, section Prerequis de mesure", qui n'existe pas -
`ADR-0045` porte sur la coherence du catalogue. Ecart trouve par `camille-po` en verifiant la
reference, corrige par `theo-archi` qui l'avait ecrite : une reference qui pointe vers rien, dans le
fichier que tous les agents lisent, est de la meme famille qu'un chiffre sans methode.* Ce jour-la l'ecart etait un **faux rouge**, donc
visible et corrige dans l'heure. Rien ne garantit le sens du prochain : un **faux vert** a la meme
cause, et il ne se remarque pas - c'est la definition meme d'un garde-fou qui ne garde plus rien,
la famille de defaut que ce depot a deja sanctionnee plusieurs fois.

`mvn -B clean verify` sur la racine n'a jamais ce probleme : le reacteur y est complet par
construction, et c'est la commande de la porte technique. Le `-am` ne concerne que les mesures
partielles, celles qu'on lance pour aller plus vite - et toute mesure partielle citee dans un
fichier commite nomme son perimetre a cote de son chiffre, un module mesure n'etant pas le depot
(`squad/README.md`, section 4).

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
  pedagogique, `[NAD]` Nadia (professeure des ecoles), `[INES]` Ines (designer d'interface). La
  CI **rejette** un message de commit sans tag connu.
- **Secrets dans `.env`, jamais commite.** `.gitignore` l'exclut. Aucun secret n'est ecrit en dur
  dans le code, dans un prompt d'agent ou dans un fichier commite.
- **Le depot Git est la seule source de verite.** Ce qui n'est pas ecrit dans le depot n'existe
  pas : une ceremonie sans fichier dans `squad/sprints/` n'a pas eu lieu, une decision non
  redigee en `ADR` ou `DEC` n'a pas ete prise, un test non ecrit ne couvre rien.
- **Double Done.** **Technique** = CI verte (`mvn verify` : compilation, tests unitaires, tests
  d'integration Spring Boot, ArchUnit), juge objectivement par la CI. **Pedagogique** = prononce
  **uniquement par `nadia-prof`**, apres avoir joue l'increment, avec un motif ecrit dans
  `squad/conformite/FICHE-<jeu>.md`. Les deux sont independants : un increment techniquement
  irreprochable peut etre refuse pedagogiquement.

## Conventions de redaction

Elles s'appliquent a la redaction du depot - code, commentaires, Javadoc, messages d'erreur
techniques, README, `squad/`, `docs/backlog/`, ADR, DEC, fiches de conformite, messages de commit,
noms de fichiers - et sont verifiees par le `relecteur-pedagogique` :

- Tirets simples et guillemets droits. Pas de tiret cadratin, pas de guillemets typographiques.
- Pas d'emoji ni de symbole Unicode decoratif.
- Les caracteres accentues sont normaux dans le contenu redactionnel francais ; les messages de
  commit et les identifiants de code restent en ASCII.
- Pas de commentaire qui paraphrase le code.
- **Aucun chemin absolu de poste de travail, aucun nom d'utilisateur, aucune credential.** Le
  depot est pousse sur GitHub : `C:\...`, `/c/...`, `/home/...`, `/Users/...`, un chemin
  d'interpreteur ou de JDK, un nom de compte n'ont rien a y faire, y compris dans un rapport de
  ceremonie qui cite la commande qu'un agent a lancee. Ecrire la commande sous sa forme portable
  (`mvn -B verify`, `python outils/rapport-tokens.py`) et nommer le prerequis plutot que son
  emplacement (`JAVA_HOME` sur un JDK 21, et non le repertoire ou il est installe sur telle
  machine). Les secrets vivent dans `.env`, jamais dans un fichier commite.
  Le critere est **la portabilite, pas la presence d'un separateur** : un emplacement qui vaut
  identiquement sur toute machine reste ecrivable. `$HOME`, `$JAVA_HOME`, `%USERPROFILE%`,
  `~/.m2/settings.xml`, `~/.m2/toolchains.xml` sont des formes acceptees ; c'est la variable qui
  est bonne et sa valeur figee qui est mauvaise. C'est ce qu'`ADR-0010` decide et ce que
  `verifier-chemins.sh` execute depuis s03, avec un cas de test nomme pour chacune de ces formes.
  Amendement du 2026-08-17 (`theo-archi`, s14) : cette phrase enumerait `~/.m2/...` parmi les
  formes interdites, en contradiction avec `ADR-0010`, avec le garde-fou, avec le test du
  garde-fou et avec deux relectures (s03, s05) qui avaient deja tranche dans l'autre sens. La
  decision vit dans l'`ADR` ; c'est cette enumeration-ci qui avait derive, elle est corrigee.

Le contenu et l'interface destines a l'enfant (enonces, propositions de reponse, pictogrammes,
signaux d'interface comme le retour juste/faux) n'entrent pas dans ce registre : ils sont
gouvernes par la porte pedagogique de `nadia-prof`, pas par cette regle de style (voir
`DEC-0002-emoji-et-pictogrammes-contenu-enfant.md`). Une seule exigence de redaction leur reste
commune, ajoutee par l'amendement du 2026-08-04 a `DEC-0002` : ce texte est redige en francais
correct et integralement accentue, orthographe et ponctuation irreprochables, parce qu'il est lu
par un enfant en cours d'apprentissage de la lecture. Cette exigence-la est verifiee par
`relecteur-pedagogique` comme n'importe quelle autre regle de cette section.

## La squad

Neuf sous-agents Claude Code (`.claude/agents/`) construisent `recreo` : `camille-po` (PO),
`sofia-sm` (Scrum Master et troisieme developpeuse), `theo-archi` (architecte), `marc-dev` et
`lea-dev` (developpeurs seniors), `yanis-qa` (QA), `ines-design` (designer d'interface et
accessibilite visuelle, qui propose sans trancher), `relecteur-pedagogique` (relecture des
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
