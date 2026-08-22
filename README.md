# recreo

Application web de revision scolaire pour les enfants de **CP** (cycle 2) et de **CM1** (cycle 3),
sous forme de mini-jeux, visant a couvrir toutes les matieres du programme de l'Education
nationale.

Le principe directeur du produit : decoupler une **mecanique de jeu reutilisable** d'un **contenu
pedagogique pilote par des regles**, afin qu'une meme mecanique serve n'importe quelle matiere et
n'importe quel niveau sans modification de code. Une seule mecanique existe aujourd'hui, le bandit
manchot (une question, trois propositions, un score) ; elle sert des additions de CP comme des
synonymes de CM1.

Cette branche, `round-two`, est une reconstruction from scratch. La premiere version vit sur `main`
et reste consultable, mais elle n'est pas une reference a suivre.

## Demarrer

### Deux prerequis, verifies par le build lui-meme

- une **toolchain de type `jdk` et de version `21`** declaree dans le fichier de toolchains de
  l'utilisateur (`~/.m2/toolchains.xml`). Elle designe le JDK qui compile et qui execute les
  tests. Sans elle, `maven-toolchains-plugin` arrete le build des la phase `validate` en nommant
  le type, la version et le fichier a renseigner ;
- **Maven lance sur un JDK 17 ou plus recent** : `spring-boot-maven-plugin` ne peut pas etre charge
  par une machine virtuelle plus ancienne, et une toolchain ne gouverne pas la machine virtuelle de
  Maven. Sans cela, `maven-enforcer-plugin` arrete le build avec la marche a suivre.

Ces deux exigences sont declarees dans le POM parent : elles echouent avec un message explicite,
jamais par une erreur de compilation obscure.

### Construire et lancer

```bash
mvn -B clean verify                       # compilation, tests, ArchUnit
mvn -B -pl bootstrap -am package          # fat jar
java -jar bootstrap/target/recreo.jar     # http://localhost:8080
```

Sur un poste dont le `java` du `PATH` n'est pas un JDK 21, `outils/mvn-jdk21.sh` lance Maven sur le
JDK de la toolchain declaree, sans avoir a positionner `JAVA_HOME` a la main :

```bash
bash outils/mvn-jdk21.sh -B clean verify
```

Le jar doit lui aussi etre execute par un JDK 21. Aucune configuration, aucune base de donnees,
aucune variable d'environnement n'est requise pour demarrer : le serveur ecoute sur le port 8080 et
sert l'accueil a la racine.

**Arreter l'application avant de relancer le build.** Tant qu'un processus tourne sur
`bootstrap/target/recreo.jar`, ce fichier est tenu ouvert et `mvn verify` echoue a l'etape de
repackaging (`Unable to rename 'recreo.jar' to 'recreo.jar.original'`), **tous les tests etant
pourtant verts**. Le message ne nomme pas la cause reelle : ce n'est pas le build qui est casse,
c'est le jar qui est verrouille. Arreter le processus **par son identifiant**, jamais par nom
d'image - `java` en tient souvent d'autres sur un poste de developpement.

### Mesure partielle : toujours avec `-am`

`mvn -pl <module> test` **sans `-am`** ne construit pas les modules dont `<module>` depend : Maven
les resout depuis le depot local de l'utilisateur, ou dort la derniere version *installee*, qui
peut etre plus ancienne que l'arbre qu'on croit mesurer. Le mode de defaillance n'est pas l'echec,
c'est l'ecart muet : la commande reussit, le chiffre s'affiche, et il decrit un autre arbre que
celui du worktree. Un tel ecart a ete mesure sur ce depot, avec et sans `-am` sur le meme arbre et
sans aucune mutation.

`mvn -B clean verify` sur la racine n'a jamais ce probleme : le reacteur y est complet par
construction.

## Stack

- **Java 21**, Clean Architecture stricte : les dependances vont toujours vers l'interieur.
- **Spring Boot 4.1**, cantonne aux adapters et a `bootstrap`. Le domaine n'a aucune dependance de
  framework.
- **Front en htmx et Thymeleaf** : rendu serveur, fragments HTML, jamais de JSON cote vues. Zero
  build Node, zero serveur front separe.
- **Aucune persistence**, par decision produit (`ADR-0001`, `DEC-0028`). L'etat d'une partie vit
  dans la session HTTP et disparait avec elle : ni progression, ni score, ni partie en cours ne
  survivent a une session. Il n'existe pas de module de persistence et il n'en est pas prevu.
- **Packaging en fat jar unique** (`spring-boot-maven-plugin`).

## Modules

| Module | Role | Voit |
|---|---|---|
| `domain` | mecaniques de jeu, contenu, regles | rien |
| `application` | ports entrants et sortants, cas d'usage | `domain` |
| `adapters/adapter-web` | controleurs, gabarits Thymeleaf, feuille de style | `application`, `domain` |
| `adapters/adapter-content` | chargement du catalogue et des paquets de contenu | `application`, `domain` |
| `bootstrap` | assemblage Spring, tests d'integration, fat jar | tout |

## Routes

| Methode | Chemin | Role |
|---|---|---|
| `GET` | `/` | accueil, catalogue complet |
| `GET` | `/niveaux/{niveau}` | catalogue filtre par niveau (`CP`, `CM1`) |
| `POST` | `/parties/{jeuId}` | demarrer une partie |
| `POST` | `/parties/{jeuId}/coups` | jouer une proposition |
| `POST` | `/retour`, `/retour/confirmer`, `/retour/annuler` | quitter une partie en cours |
| `POST` | `/niveaux/{niveau}/confirmer` | changer de niveau pendant une partie |

Les routes de jeu repondent un fragment HTML destine a etre echange par htmx dans la zone `#jeu`.

## Contenu

Le contenu pedagogique n'est pas dans le code. Il vit en YAML dans
`adapters/adapter-content/src/main/resources` :

- `catalogue.yaml` declare les jeux jouables : identifiant, titre, consigne, mecanique, paquet
  d'items et nombre de manches. **Un paquet present dans `contenu/` mais absent du catalogue n'est
  pas jouable** : c'est le mecanisme par lequel un contenu qui n'a pas passe la porte pedagogique
  reste hors de portee d'un enfant ;
- `contenu/*.yaml` porte les items : enonce, propositions, bonne reponse, indice, explication,
  descripteur (matiere, domaine, competence, niveau).

`contenu-repris/` contient des paquets issus de la version precedente. C'est une reserve de matiere
premiere, pas un cahier des charges : tout contenu repris repasse la porte pedagogique comme un
contenu neuf.

## Tests et garde-fous

`mvn -B clean verify` execute la totalite : compilation, tests unitaires, tests d'integration
Spring Boot, et les regles d'architecture ArchUnit. La CI n'ajoute rien a cette commande, elle
l'entoure.

Reacteur complet a la cloture du sprint 17 : **594 tests**, 0 echec - 48 pour `domain`, 13 pour
`application`, 294 pour `adapter-web`, 144 pour `adapter-content`, 89 plus 6 tests d'integration
pour `bootstrap` - et **29 regles ArchUnit**. Le compte autoritaire est la somme des blocs
`Results:` du reacteur apres un `clean`, ou de facon equivalente la somme des attributs
`tests="..."` des rapports XML de surefire et failsafe ; **jamais la somme des lignes `Tests run:`
des rapports `.txt`**, qui sous-comptent : quatre classes de ce depot melangent un champ
`@ArchTest` execute par le moteur d'ArchUnit et une methode `@Test` ordinaire executee par Jupiter,
et le rapport texte n'ecrit que le second.

Deux regles gouvernent l'ecriture des tests dans ce depot :

- **toute regle d'architecture est encodee en test ArchUnit.** Une regle non executable n'est pas
  une regle, c'est un souhait ;
- **les tests sont ecrits dans le meme commit que le code**, dans tous les modules.

Une troisieme regle gouverne leur credit : un garde-fou n'est declare complet qu'une fois sa
**preuve de morsure** decrite - mutation reelle du code, relecture adverse de l'inegalite qu'il
verifie, ou tout autre moyen nomme. "Il est vert" ne suffit jamais, y compris quand son auteur le
croit. Ce depot a mesure plusieurs garde-fous verts qui n'avaient jamais rencontre l'objet qu'ils
pretendaient garder.

Trois garde-fous s'executent hors de Maven, et la CI les fait echouer independamment du build :

| Script | Ce qu'il refuse |
|---|---|
| `.github/scripts/verifier-prefixes.sh` | un message de commit sans prefixe d'agent connu |
| `.github/scripts/verifier-chemins.sh` | un chemin de poste de travail, un nom de compte ou une credential dans un fichier commite |
| `.github/scripts/commit-atteignable.sh` | une plage de commits que la CI ne peut pas atteindre |

Chacun est livre avec sa propre suite de tests (`*-test.sh`), executee par la CI : un script teste
dont aucun automate ne lance les tests n'est pas un script teste.

## Ce que la CI ne peut pas voir

Le rendu. Aucun test de ce depot ne mesure une hauteur rendue, un contraste percu, un etat de
survol, d'appui ou de focus tel qu'un enfant le voit. Un increment peut etre vert de bout en bout
et rester injouable - c'est arrive, mesure, plusieurs fois. Cette limite est nommee explicitement
plutot que comblee a moitie : elle est couverte par une porte humaine, pas par un automate.

## Le double Done

Un increment n'est livre qu'une fois deux portes independantes franchies :

- **Done technique** : CI verte, juge objectivement par la CI ;
- **Done pedagogique** : prononce apres avoir joue l'increment, avec un motif ecrit dans
  `squad/conformite/FICHE-<jeu>.md`.

Les deux sont independants. Un increment techniquement irreprochable peut etre refuse
pedagogiquement, et cela s'est produit.

## Documentation

| Fichier | Contenu |
|---|---|
| [`CLAUDE.md`](CLAUDE.md) | regles de contribution, conventions de redaction, dettes a ne pas reproduire |
| [`squad/README.md`](squad/README.md) | le process complet : ceremonies, decisions, conformite, rapports |
| [`squad/EQUIPE.md`](squad/EQUIPE.md) | qui fait quoi, et qui tranche quoi |
| `squad/decisions/adr/` | decisions d'architecture, une par fichier, numerotation jamais reutilisee |
| `squad/decisions/produit/` | decisions produit, meme regle |
| `squad/programme/MATRICE-couverture.md` | etat de la couverture du programme, ligne par ligne |
| `squad/sprints/sNN-<slug>/` | planning, review, rapport et retro de chaque sprint |

Le depot Git est la seule source de verite du projet : une decision non redigee n'a pas ete prise,
une ceremonie sans fichier n'a pas eu lieu, un test non ecrit ne couvre rien.
