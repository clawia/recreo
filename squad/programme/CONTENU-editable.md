# Éditer le contenu des jeux sans développeur

Objectif : permettre à l'adulte (autorité pédagogique) d'**enrichir ou corriger le contenu**
directement, sans passer par un développeur (cf. DEC-0006). Réalisé pour la **famille quiz** en s09
(ADR-0009). Les autres familles suivront.

## Quiz-aventure (fait — s09)

Chaque jeu quiz est un fichier **YAML** dans `bootstrap/src/main/resources/content/quiz/` :

| Fichier | Jeu |
|---|---|
| `questionner-le-monde-cp.yml` | Questionner le monde (CP) |
| `sciences-cm1.yml` | Sciences (CM1) |
| `histoire-cm1.yml` | Histoire (CM1) |
| `geographie-cm1.yml` | Géographie (CM1) |
| `emc-cp.yml` | Vivre ensemble — EMC (CP) |
| `emc-cm1.yml` | Citoyenneté & respect — EMC (CM1) |
| `vrai-faux-sciences-cm1.yml` | Vrai ou Faux — sciences (CM1) |

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

## À venir
- **Mots → réponse** du bandit manchot (le bon son, anglais, syllabes, première lettre, homophones,
  nature du mot) : schéma dédié (mot + bonne réponse + distracteurs).
- **Textes à trous** (conjugaison, accords, mot manquant, phrase à trou, égalité) : schéma dédié
  (phrase avec un trou + mots à choisir).
- Le **calcul mental** (maths) est déjà piloté par YAML (`content/calcul-mental-*.yml`,
  `content/tables-multiplication-cm1.yml`) mais reste **génératif** (pas de liste à éditer).
