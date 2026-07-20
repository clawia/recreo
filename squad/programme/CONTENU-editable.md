# Éditer le contenu des jeux sans développeur

Objectif : permettre à l'adulte (autorité pédagogique) d'**enrichir ou corriger le contenu**
directement, sans passer par un développeur (cf. DEC-0006). **Tout le contenu à liste finie est
éditable** (quiz s09, mots s10, textes à trous + syllabes s11). Convention de nommage des fichiers :
**`niveau-jeu.yml`** (ADR-0012) — ex. `cm1-sciences.yml`, `cp-le-bon-son.yml`.

## Quiz-aventure (fait — s09)

Chaque jeu quiz est un fichier **YAML** dans `bootstrap/src/main/resources/content/quiz/` :

| Fichier | Jeu |
|---|---|
| `cp-questionner-le-monde.yml` | Questionner le monde (CP) |
| `cm1-sciences.yml` | Sciences (CM1) |
| `cm1-histoire.yml` | Histoire (CM1) |
| `cm1-geographie.yml` | Géographie (CM1) |
| `cp-emc.yml` | Vivre ensemble — EMC (CP) |
| `cm1-emc.yml` | Citoyenneté & respect — EMC (CM1) |
| `cm1-vrai-faux-sciences.yml` | Vrai ou Faux — sciences (CM1) |

### Modifier / ajouter une question
Ouvre le fichier du jeu et ajoute une entrée sous `questions:` :
```yaml
  - question: "Quel est le plus grand océan ?"
    bonne: "L'océan Pacifique"
    distracteurs: ["L'océan Atlantique", "La mer Méditerranée"]
```
Règles (vérifiées au démarrage) :
- chaque question a **une** `bonne` réponse et **au moins un** `distracteur` ;
- les distracteurs sont **distincts** et **différents** de la bonne réponse ;
- pas de **question en double** dans un même fichier.

Pour un **Vrai/Faux** : `bonne` vaut `"Vrai"` ou `"Faux"`, et l'unique distracteur est l'autre.

### Ajouter un tout nouveau jeu quiz
Crée un nouveau fichier `content/quiz/mon-jeu.yml` (copie un existant), renseigne `entryId`
(unique), `title`, `gameKey`, `gameLabel`, `matiere`, `niveau`, `domaine` et les `questions`.
Il apparaît automatiquement dans le catalogue au redémarrage — **aucun code à toucher**.

### Prise en compte des changements
1. Enregistre le fichier. 2. Reconstruis/relance l'application (`mvn -pl bootstrap package` puis
`java -jar bootstrap/target/recreo.jar`). 3. Recharge la page. Si un fichier est **incohérent**,
l'application **refuse de démarrer** avec un message indiquant le fichier et le problème (garde-fou).

> Astuce : le YAML est sensible à l'**indentation** (2 espaces, pas de tabulation) et les textes
> avec apostrophes/accents se mettent entre guillemets doubles `"..."`. Chaque fichier commence par
> un rappel de ces règles.

## Mots → réponse — bandit manchot (fait — s10)

Fichiers dans `bootstrap/src/main/resources/content/mots/` :

| Fichier | Jeu | Mode distracteurs |
|---|---|---|
| `cp-le-bon-son.yml` | Le bon son (Français CP) | `explicit` |
| `cm1-homophones.yml` | Le bon petit mot (Français CM1) | `explicit` |
| `cp-premiere-lettre.yml` | La première lettre (Français CP) | `pool` |
| `cm1-nature-mot.yml` | La nature du mot (Français CM1) | `pool` |
| `cm1-anglais-lexique.yml` | Le bon mot en anglais (Anglais CM1) | `pool` |

Un item = un `prompt` (le mot ou la phrase affichés) + une `bonne` réponse. Deux façons de définir les
mauvaises réponses, selon `distractorMode` :
- **`explicit`** : chaque item liste ses `distracteurs` (distincts, différents de la bonne réponse).
  ```yaml
  - { prompt: "MAMAN", bonne: "[an]", distracteurs: ["[on]", "[in]"] }
  ```
- **`pool`** (avec `distractorCount:`) : les distracteurs sont tirés automatiquement parmi les **autres
  bonnes réponses** du fichier — pas besoin de les écrire.
  ```yaml
  - { prompt: "chien", bonne: "dog" }
  ```

Ajouter un tout nouveau jeu « mot → réponse » = créer un fichier `content/mots/mon-jeu.yml` (copie un
existant) ; il apparaît au redémarrage, sans code.

## Textes à trous (fait — s11)

Fichiers dans `content/textes-trous/` : `cm1-conjugaison.yml`, `cm1-accords.yml`, `cp-mot-manquant.yml`.
**Même schéma que les quiz** : `question` porte la phrase avec le trou `___`, `bonne` = le bon mot,
`distracteurs` = les autres propositions.
```yaml
  - question: "Nous ___ une histoire."
    bonne: "lisons"
    distracteurs: ["lit", "lisent", "lis"]
```
> « Complète l'égalité » (Maths CP) n'a pas de fichier : il est **généré** (a + ___ = c).

## Syllabes (fait — s11)

Fichier `content/syllabes/cp-syllabes.yml`. Chaque item = un `mot` + son nombre de `syllabes` ; les
options du réglage adulte sont dans `syllabesOptions`. Les mauvaises réponses (nombres voisins) sont
calculées automatiquement.
```yaml
items:
  - { mot: "bateau", syllabes: 2 }
  - { mot: "chocolat", syllabes: 3 }
```

## Reste en code (par conception — contenu génératif)
Le **calcul mental**, les **tables de multiplication** et « **complète l'égalité** » sont **générés à
la volée** (vivier infini) : il n'y a pas de liste à éditer. Leur difficulté est réglée dans
`content/cp-calcul-mental.yml`, `content/cm1-calcul-mental.yml`, `content/cm1-tables-multiplication.yml`.
