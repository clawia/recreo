# Complement a l'avis de faisabilite s24 - la version de JDK declaree contre celle qui execute

- **Auteur** : theo-archi. **Date** : 2026-08-30. **Branche** : `s24-theo`.
- **Demande** : `sofia-sm`, apres un constat de `nadia-prof` - *"le fichier de toolchains declare une
  toolchain `jdk` de version `21` dont le `jdkHome` pointe un JDK 25"*.
- **Ce que ce document fait** : il constate plutot que de croire, corrige ce qui est faux, et tranche
  ce qu'un garde-fou peut arreter. Il **n'amende aucun rapport de sprint clos** : ce qui reste a
  reprendre est route en section 4.

## 1. Le fait, constate

### 1.1 Le mecanisme decrit par `nadia-prof` est exact, et je l'ai reproduit

`maven-toolchains-plugin` apparie une entree sur la **version qu'elle declare**, jamais sur celle du
binaire qu'elle designe. Reproduction du 2026-08-30, avec un fichier de toolchains **fabrique hors du
depot** declarant `21` et designant une racine de JDK 25, passe a Maven par son option de fichier de
toolchains :

```
[INFO] Required toolchain: jdk [ version='21' ]
[INFO] Found matching toolchain for type jdk: JDK[...jdk-25...]
[INFO] Toolchain in maven-surefire-plugin: JDK[...jdk-25...]
```

Le build ne bronche pas. **Le mecanisme est etabli.**

### 1.2 Par quel moyen on lit la version reellement executee

**Une seule source dit la verite : demander a l'execution ce qu'elle est.** Une sonde posee dans
`bootstrap`, executee par surefire dans le meme build que les autres tests, et retiree ensuite,
imprime `System.getProperty("java.version")`, `java.class.version` et `Runtime.version().feature()`.

Les quatre autres sources disponibles ne disent que ce qui est **declare**, et sont donc
disqualifiees pour cet usage : la ligne `Found matching toolchain` du journal (elle nomme une racine
de repertoire), le nom du repertoire de JDK (une chaine que personne ne verifie), le champ d'un
fichier de toolchains (l'objet meme du defaut), et la ligne de `outils/mvn-jdk21.sh` (voir 1.4).

### 1.3 Ce que cette lecture vaut ici

**Mesure du 2026-08-30, worktree `s24-theo`** :

| Ce qui est mesure | Valeur | Source |
|---|---|---|
| Version de la machine virtuelle qui execute les tests | **21.0.10** | `java.version`, sonde surefire |
| Version majeure de classe de cette machine | **65.0** | `java.class.version`, meme sonde |
| Version de fonctionnalite | **21** | `Runtime.version().feature()`, meme sonde |
| Toolchain retenue par Maven pour surefire et le compilateur | racine d'un JDK **21.0.10** | lignes `Toolchain in maven-*-plugin`, une par module |

**Les tests de ce depot ont bien tourne sur un JDK 21, et non sur un 25.** L'inquietude etait fondee
sur le mecanisme et ne se verifie pas sur cette execution.

### 1.4 Ce qui a produit le constat, et c'est un vrai defaut - simplement pas celui-la

**Le poste de mesure porte deux fichiers de toolchains, et ils ne declarent pas le meme JDK.**
`outils/mvn-jdk21.sh` lit celui du `HOME` du shell POSIX ; Maven resout le sien dans le profil
utilisateur de sa machine virtuelle Java. Ce sont deux emplacements distincts sur ce poste.

- Le fichier **du shell** declare une seule entree : version `21`, designant une racine de JDK 25.
  **C'est celui que `nadia-prof` a ouvert, et son constat y est exact.**
- Le fichier **que Maven lit** declare trois entrees - 17, 21, 25 - dont celle de version `21`
  designe une racine de JDK 21.0.10. **Aucun ecart dans celui-la.**

Consequence reelle : l'enveloppe pose `JAVA_HOME` sur le JDK 25, ce qui gouverne **la machine
virtuelle de Maven lui-meme** ; Maven compile et execute les tests sur le 21.0.10 de **sa** toolchain.
**Maven sur 25, les tests sur 21 : c'est conforme**, `CLAUDE.md` n'exigeant de la machine virtuelle
de Maven qu'un 17 ou plus recent, et une toolchain ne la gouvernant pas.

**Rien de tout cela n'est neuf.** `S21-6` (2026-08-27) a etabli les deux fichiers, la divergence et
le remede a apporter a l'outillage - *"il doit refuser au lieu d'imprimer"*
(`correctif-outillage-de-mesure-s21-1.md`, section 6). **Trois sprints plus tard, l'enveloppe
imprime toujours** : sa ligne du jour dit mot pour mot *"ce JDK rapporte 25.0.2 : la declaration de
version n'a pas ete confirmee"*, sur la sortie d'erreur, a chaque lancement.

**Et je l'ai filtree moi-meme, le jour meme.** Mes commandes de mesure du 2026-08-30 passaient la
sortie par un motif du genre `Tests run:|BUILD|ERROR`, qui ne retient aucune des deux lignes de
l'enveloppe. **Un avertissement qu'un filtre de lecture ordinaire fait disparaitre n'est pas un
garde-fou.** C'est ma faute de methode, elle est datee, et c'est elle qui motive `ADR-0071`.

## 2. Ma synthese : ce qui etait faux et ce qui ne l'etait pas

**Le chiffre etait juste, la methode ne l'etait pas.** J'ai ecrit *"JDK 21.0.10 de la toolchain
declaree"*. La valeur est confirmee par la lecture d'execution ci-dessus - mais je la tenais de ce que
le plugin **declare**, pas de ce que la machine virtuelle **rapporte**. Il se trouve qu'ici les deux
coincident ; rien ne le garantissait, et une phrase juste par chance reste une phrase sans methode.

**Corrige dans les trois documents que j'ai ecrits ce sprint** - `ADR-0069`, `ADR-0070`, l'avis de
faisabilite : la version y est desormais donnee comme *"celle que la machine virtuelle des tests
rapporte d'elle-meme, jamais celle qu'une toolchain declare"*, avec renvoi a `ADR-0071`. **Zero
occurrence de l'ancienne formule** dans ces trois fichiers apres correction, verifie par recherche.

**Aucun chiffre n'est remplace**, dans aucun document : il n'y avait rien a remplacer.

## 3. Le perimetre historique, avec sa methode - et pourquoi je ne balaie pas

**Methode** : recherche sur l'index des fichiers suivis du worktree `s24-theo`, le 2026-08-30.

| Ce qui est compte | Fichiers | Occurrences |
|---|---|---|
| La chaine `toolchain declaree` | 40 | 51 |
| La chaine `21.0.10` | 147 | 316 |
| Fichiers citant `21.0.10` **et** portant deja un amendement date de `S21-6` | 22 | - |

**Ces trois nombres ne sont pas le nombre de phrases fausses, et il serait malhonnete de les
presenter ainsi.** Le chiffre `21.0.10` est **exact** partout ou il est ecrit - `S21-6` l'a etabli a
trois sources independantes le 2026-08-27, et la sonde d'aujourd'hui le reconfirme. Ce qui peut etre
inexact est le **raccordement** cite a cote : une phrase qui donne comme source *"la toolchain que
designe `outils/mvn-jdk21.sh`"* nomme le mauvais fichier. `S21-6` a deja trie et amende sur ce
critere les **douze** `confirmation-archunit.md`, en trois groupes, et a laisse non corriges les
quatre qui citaient un `JAVA_HOME` pose a la main - raccordement vrai.

**Je ne balaie pas les 147 fichiers, et le motif n'est pas la charge.** Trier demande de lire ce que
**chaque** phrase donne comme source ; ecrire le meme amendement partout affirmerait un defaut la ou
il n'y en a pas, ce qui est exactement la faute que `S21-6` a evitee. Et ces fichiers sont des
rapports de sprints clos, dont plusieurs appartiennent a d'autres agents : **je ne reecris pas leurs
documents**, je route.

## 4. Ce qu'un garde-fou peut arreter - tranche

### 4.1 Sur l'affirmation d'un document : non, et j'ecris pourquoi plutot qu'une consigne

Un balayage des documents commites cherchant *"JDK 21.0.10 de la toolchain declaree"* ne pourrait
constater que sa **presence**, jamais sa verite : la mesure qu'elle rapporte a eu lieu un autre jour,
sur un autre arbre, dans une session qui n'existe plus. Il ne saurait pas davantage separer une
phrase juste d'une phrase fausse - **le chiffre est le meme dans les deux cas, c'est tout le
probleme**. Ce garde-fou serait decoratif, et je ne l'ecris pas.

### 4.2 Sur la coherence declare / execute : oui, et c'est fait - `ADR-0071`

> La machine virtuelle qui execute les tests porte exactement la version de toolchain que le pom
> agregateur exige.

`bootstrap/src/test/java/com/recreo/archi/LaMachineVirtuelleDesTestsPorteLaVersionQueLeBuildExigeTest.java`,
**4 tests, 0 echec**.

- L'exigence est **lue dans le pom agregateur**, dans le bloc que `maven-toolchains-plugin` lit
  lui-meme pour apparier ; jamais recopiee en constante.
- L'objet juge est `Runtime.version().feature()` dans la machine virtuelle forkee par surefire, celle
  qui a execute tous les autres tests du meme build.
- **Egalite, jamais plancher** : un `>=` laisserait passer une execution sur 25, c'est-a-dire le
  defaut meme. Les deux moities sont mordues separement (`ADR-0055`).
- **Morsure sur le mecanisme reel** : le fichier de toolchains fabrique de la section 1.1 fait rougir
  ce test avec son message complet - 4 tests, 1 echec, `BUILD FAILURE`.
- Le fichier de toolchains reste **hors du depot** et la regle n'en a pas besoin : elle raccorde
  l'exigence commitee a l'execution. Le message nomme le prerequis par sa fonction, jamais son
  emplacement (`ADR-0010`).

**Ce que cela change pour les phrases de documents** : elles cessent de pouvoir devenir fausses pour
ce motif. C'est la seule facon honnete de garder une affirmation - rendre vrai ce qu'elle affirme,
plutot que policer sa formulation.

### 4.3 Ce que je ne fais pas dans ce lot, et que je route a `sofia-sm`

**La dette d'outillage de `S21-6`, quatrieme occurrence documentee** : le message de
`outils/mvn-jdk21.sh`, qui affirme un raccordement qu'il ne verifie pas, et le champ
`toolchains lu par Maven` de `outils/verifier-jdk.sh`, qui repond sur un autre fichier que celui dont
il parle. Les deux sont decrits depuis le 2026-08-27, section 6 du correctif de s21, avec leur cout
- *"une fonction, quelques cas"*. **C'est une story d'outillage**, avec son propre harnais de test et
sa propre etape de CI ; l'embarquer dans un lot de conception de la saisie serait un elargissement de
perimetre que je refuse. Elle a desormais un cout mesure a citer au planning : elle a induit en
erreur une lectrice competente le 2026-08-30, et elle m'avait echappe le meme jour.

**Le tri du perimetre historique** (section 3), si `sofia-sm` le veut fait : c'est un lot de
relecture, pas une correction mecanique, et il suit le patron de `S21-6` - amender, jamais reecrire.

## 5. Ce que ce complement ne touche pas

Les sections 1 a 7 de l'avis de faisabilite sont inchangees, hormis la phrase de methode corrigee en
section 2 ci-dessus. **Le verdict de faisabilite du sprint ne bouge pas** : rien de ce constat ne
touche la conception de la saisie, ses lots ni leur cout. La mesure de cloture de l'avis - 807 tests,
0 echec - tenait sur une execution en JDK 21, et c'est etabli plutot que suppose.

**Mesure de cloture**, `bash outils/mvn-jdk21.sh -B clean verify` a la racine : **BUILD SUCCESS,
811 tests, 0 echec** (49 `domain` + 13 `application` + 332 `adapter-web` + 288 `adapter-content` +
123 `bootstrap` surefire + 6 `failsafe`), sur un JDK **21.0.10** - version rapportee par la machine
virtuelle des tests elle-meme, desormais verifiee a chaque build par `ADR-0071`. Pas de `-am` a
citer : le reacteur est complet par construction.
