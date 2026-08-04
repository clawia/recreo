# ADR-0007 - Charger tout le contenu avec un seul chargeur pilote par un schema

- **Statut** : Accepte
- **Date** : 2026-08-04
- **Auteur** : theo-archi
- **Sprint** : s00

## Contexte

La version precedente avait neuf familles de chargeurs YAML quasi identiques
(`AssocContentPack`, `QuizContentPack`, `MemoryContentPack`, `MotsContentPack`,
`PhraseContentPack`, `PuzzleContentPack`, `FriseContentPack`, `FractionsContentPack`,
`SyllabesContentPack`, plus leur `ContentPackLoader`), environ 1 200 lignes dupliquees, logees
dans `bootstrap` a cote d'un `BeansConfig` de 513 lignes.

Le mecanisme de la dette est simple a reconstituer : chaque nouveau type de jeu apportait sa forme
YAML, chaque forme YAML apportait sa classe de chargement, et chaque classe de chargement etait
une copie de la precedente avec trois champs changes. Personne n'a jamais decide "faisons neuf
chargeurs" ; il n'existait aucun endroit ou un dixieme aurait ete refuse.

Le second effet est visible dans `contenu-repris/` : les 61 paquets ont neuf structures
differentes (`puzzles`, `paires`, `questions`, `associations`, `mots`), ce qui rend le contenu
inexploitable sans son chargeur d'origine. Le fichier `contenu-repris/README.md` le dit :
la matiere est reutilisable, la structure ne l'est pas.

## Decision

### Un seul chargeur, dans son propre module

`com.recreo.adapter.content.YamlContentPackLoader`, module `adapters/adapter-content` (ADR-0001).
Il implemente le port sortant `com.recreo.application.port.out.ContentCatalogPort`, et il est la
**seule** classe du projet autorisee a lire des fichiers.

Le port tient en une methode, `List<GameDefinition> games()`, et le chargeur est instancie en
`@Bean` dans `bootstrap` : `adapter-content` ne declare aucune dependance Spring, ce qui lui
interdit de se decouvrir tout seul et laisse le cablage la ou ADR-0001 le met.

### Un schema unique pour tous les paquets

Toutes les matieres, tous les niveaux, toutes les mecaniques ecrivent la meme enveloppe :

```yaml
schema: 1
id: cp-formes-geometriques
type: question
descripteur:
  matiere: "Mathematiques"
  niveau: "CP"
  domaine: "Formes geometriques"
  competence: "reconnaitre et nommer les figures usuelles"
items:
  - enonce: "Combien de cotes a un triangle ?"
    bonneReponse: "3"
    distracteurs: ["4", "2"]
```

L'enveloppe (`schema`, `id`, `type`, `descripteur`) est invariante. Le champ `type` designe une
**fabrique de `ContentSource`**, jamais une mecanique et jamais une matiere. Un paquet ne nomme
jamais la mecanique qui le jouera : c'est le catalogue qui les associe.

Deux natures de paquet, une seule enveloppe. Le paquet ci-dessus est **enumere** : ses items sont
ecrits a la main. Un paquet **combinatoire** remplace `items` par `parametres`, lus par sa seule
fabrique :

```yaml
schema: 1
id: cp-additions-jusqu-a-10
type: additions-bornees
descripteur:
  matiere: "Mathematiques"
  niveau: "CP"
  domaine: "Nombres et calculs"
  competence: "calcul mental - additions dans la limite de 10"
parametres:
  sommeMaximale: 10
  nombreDePropositions: 3
```

Le sprint 0 a besoin des deux natures : la story S0-4 demande des additions variees d'une manche a
l'autre, donc un tirage combinatoire, pas une liste figee. Les bornes vivent dans `parametres` et
ne sont lues que par la fabrique `additions-bornees` (ADR-0006) ; aucune autre matiere ne les voit,
aucun paquet enumere n'a a les remplir.

### Un catalogue, qui associe mecanique et paquet

```yaml
schema: 1
jeux:
  - id: formes-cp
    titre: "Les formes geometriques"
    consigne: "Clique sur la bonne reponse"
    mecanique: <identifiant de la mecanique>
    paquet: cp-formes-geometriques
```

La `consigne` est portee par l'entree de catalogue, pas par le paquet ni par la mecanique : c'est
le seul endroit ou l'on sait a la fois quel contenu est joue et comment. Un meme paquet joue par
deux mecaniques n'a pas la meme consigne, et une mecanique ne peut pas porter un texte
pedagogique - elle ignore la matiere (ADR-0004). Le texte lui-meme releve de Camille et de Nadia.

Le chargeur produit une liste de `com.recreo.application.catalog.GameDefinition`
`(String id, String titre, String consigne, MechanicId mecanique, ContentDescriptor descripteur, ContentSource contenu)`.
La `ContentSource` remise est un `ListContentSource` (domaine, pur, sans entree/sortie) construit
sur les items lus.

### Comment on ajoute quelque chose

| Ce qu'on ajoute | Ce qu'il faut ecrire |
|---|---|
| Un paquet de contenu d'un `type` deja connu, quelle que soit la matiere ou le niveau | un fichier YAML dans `adapter-content/src/main/resources/contenu/`, plus une ligne de catalogue. **Zero ligne de Java.** |
| Un `type` de contenu (nouvelle forme d'item, ou nouveau mode de tirage) | une fabrique d'une vingtaine de lignes, une entree dans le registre, et - si la forme d'item est neuve - une permission de plus sur l'interface scellee `Item`. **Toujours un seul chargeur.** |
| Une mecanique | un paquet `com.recreo.domain.mechanics.<mecanique>` (ADR-0004). Le chargement n'est pas touche. |

Le registre est un `Map<String, ContentSourceFactory>` construit dans `adapter-content`.
`ContentSourceFactory` est une interface a une methode :
`ContentSource creer(ContentDescriptor descripteur, Map<String, Object> corps)`. Au sprint 0 il y a
deux entrees : `question` (paquet enumere, construit un `ListContentSource`) et
`additions-bornees` (paquet combinatoire, construit le generateur d'additions). Deux fabriques, un
seul chargeur : c'est la difference exacte avec les neuf familles de la version precedente, ou
chaque forme apportait aussi sa lecture de fichier, sa gestion d'erreur et son parcours de
classpath.

### Echec au demarrage, jamais de valeur par defaut silencieuse

Un paquet dont le `schema` est inconnu, dont le `type` n'a pas de parseur, dont un champ
obligatoire manque, ou une entree de catalogue qui reference un paquet ou une mecanique inexistants
font **echouer le demarrage de l'application**. Aucun repli, aucune valeur par defaut, aucun paquet
ignore en silence. Un contenu invalide est un bug de contenu ; il doit se voir au premier
`java -jar`, et donc en CI.

## Regle executable ArchUnit

Fichier : `bootstrap/src/test/java/com/recreo/archi/ChargementDuContenuTest.java`

```java
package com.recreo.archi;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static org.assertj.core.api.Assertions.assertThat;

@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class ChargementDuContenuTest {

    private static final String ADAPTATEUR_CONTENU = "com.recreo.adapter.content..";

    /** Une seule porte d'entree vers les fichiers et vers YAML. */
    @ArchTest
    static final ArchRule seulLAdaptateurDeContenuLitDesFichiers = noClasses()
            .that().resideInAPackage("com.recreo..")
            .and().resideOutsideOfPackage(ADAPTATEUR_CONTENU)
            .should().dependOnClassesThat()
            .resideInAnyPackage("org.yaml.snakeyaml..", "java.nio.file..")
            .as("ADR-0007 : un seul adaptateur lit le contenu sur disque");

    /**
     * La dette n'etait pas "un chargeur mal ecrit", c'etait "neuf chargeurs".
     * Le dixieme, comme le deuxieme, casse le build.
     */
    @Test
    void ilNYAQuUnSeulChargeurDeContenu() {
        JavaClasses production = new ClassFileImporter()
                .withImportOption(new ImportOption.DoNotIncludeTests())
                .importPackages("com.recreo");

        List<String> chargeurs = production.stream()
                .map(JavaClass::getName)
                .filter(nom -> nom.endsWith("Loader") || nom.endsWith("ContentPack"))
                .sorted()
                .toList();

        assertThat(chargeurs)
                .as("ADR-0007 : un seul chargeur, pilote par le schema de paquet")
                .containsExactly("com.recreo.adapter.content.YamlContentPackLoader");
    }
}
```

`JavaClasses` etend `ForwardingCollection<JavaClass>` : `stream()` est disponible. Le filtre porte
sur les deux suffixes qui avaient prolifere sur la version precedente (`...Loader`,
`...ContentPack`) ; c'est une regle de cardinalite, qu'aucune API fluide d'ArchUnit n'exprime,
d'ou le test JUnit ordinaire.

**Amendement du 2026-08-04, application d'ADR-0003.** La regle ArchUnit de ce fichier n'avait pas de
contre-exemple. Elle en a un :
`bootstrap/src/test/java/com/recreo/bootstrap/contreexemple/ChargeurSauvageContreExemple.java`, le
dixieme chargeur - une classe de `bootstrap` qui manipule un `java.nio.file.Path` au lieu de passer
par l'adaptateur de contenu. `LesReglesMordentTest.unChargeurHorsDeLAdaptateurDeContenuEstRefuse`
verifie que la regle echoue en citant cette classe **et** `java.nio.file`, pour que l'echec ne
puisse pas venir d'un artefact du montage.

Le test `ilNYAQuUnSeulChargeurDeContenu` n'en recoit pas, et c'est motive dans l'amendement
d'ADR-0003 : ce n'est pas une `ArchRule` mais une assertion directe de cardinalite
(`containsExactly`), qui devient rouge des qu'un second chargeur apparait **et** des que sa
selection cesse de trouver le premier. Elle est son propre temoin.

Deux gardes complementaires, qui ne sont pas des regles de bytecode :

1. **Test d'integration de chargement**, `bootstrap/src/test/java/.../CatalogueChargeableIT.java` :
   demarre le contexte Spring et verifie que tous les paquets du classpath se chargent, que chaque
   entree de catalogue reference un paquet et une mecanique existants, et que **chaque entree
   demarre effectivement une partie**. C'est ce dernier point qui garde la compatibilite de forme
   entre un paquet et sa mecanique, propriete des donnees qu'ArchUnit ne peut pas voir (renvoi
   d'ADR-0004).
2. **Test de schema**, dans `adapter-content` : un paquet auquel il manque un champ obligatoire,
   ou dont le `type` est inconnu, doit produire une erreur qui **nomme le fichier et le champ**.
   Un message d'erreur inexploitable sur un contenu invalide est un defaut, pas un detail : c'est
   Camille et Nadia qui le liront.

## Consequences

- Reprendre un paquet de `contenu-repris/` demande de le reecrire dans l'enveloppe commune. C'est
  du travail de contenu, sans surprise technique, et le `README` de ce dossier l'annonce deja.
- Les paquets qui n'entrent pas dans la forme `question` attendront la forme d'item correspondante,
  donc la mecanique qui la consomme. C'est coherent avec "le sprint 0 livre une mecanique".
- Tout le contenu est embarque dans le fat jar en tant que ressource de `adapter-content`. Modifier
  un paquet demande de reconstruire. Acceptable tant que le contenu est ecrit par la squad ; le
  jour ou il serait edite en ligne, ce sera un ADR et probablement `adapter-persistence`.
- Mauvaise consequence assumee : le test de cardinalite se contourne en nommant une classe
  `ContenuReader`. Il arrete la proliferation par copier-coller, qui est le mode de defaillance
  reel ; il n'arrete pas la mauvaise foi.
- L'echec au demarrage sur contenu invalide rendra la CI rouge pour une faute de frappe dans un
  YAML. C'est exactement ce qu'on veut : un paquet a moitie charge est un jeu qui ment a l'enfant.

## Alternatives considerees

- **Une classe de chargement par forme d'item**, plus honnete a court terme. C'est la pente exacte
  qui a produit neuf familles : chaque forme est arrivee une par une. Ecarte.
- **Charger depuis `bootstrap`, comme avant.** `bootstrap` est le module ou tout est permis ; c'est
  la qu'un chargeur echappe a toute regle. Ecarte (ADR-0001).
- **Deserialisation directe vers des records par Jackson YAML.** Elegant sur le cas nominal, mais
  les messages d'erreur en cas de contenu invalide sont ceux de la bibliotheque, illisibles pour
  qui ecrit du contenu, et cela ajoute une dependance a un module qui n'en avait pas besoin.
  Ecarte au profit d'un chargement explicite avec des messages nommant le fichier et le champ.
- **JSON Schema pour valider les paquets.** Une dependance et un langage de plus pour un schema qui
  tient en quatre champs invariants. Ecarte tant que l'enveloppe reste aussi simple.
- **Un identifiant de mecanique dans le paquet de contenu**, comme sur la version precedente
  (`mechanic: bandit-manchot`). Ca reintroduit la dependance contenu vers mecanique qu'ADR-0004
  interdit, et ca empeche de rejouer un meme contenu avec une autre mecanique. Ecarte.

## Ce qui me ferait changer d'avis

- Un besoin d'edition du contenu hors build (par un enseignant, par exemple) : le chargeur
  deviendrait un adaptateur de lecture parmi deux, derriere le meme port, et
  `adapter-persistence` apparaitrait.
- Un catalogue devenu illisible a la main (plusieurs centaines d'entrees) : je decouperais le
  catalogue par matiere, sans toucher au chargeur ni au schema.
- Trois formes d'item ou plus dont les parseurs se ressembleraient a plus de 80 % : ce serait le
  signal d'une abstraction reelle a extraire dans `adapter-content`, cette fois justifiee par
  l'usage.
