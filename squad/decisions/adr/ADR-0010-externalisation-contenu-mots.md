# ADR-0010 — Externalisation du contenu « mot → réponse » (bandit manchot)

- **Statut** : Accepté
- **Date** : 2026-07-20
- **Auteur** : theo-archi
- **Sprint** : s10

## Contexte

Suite d'ADR-0009 (quiz externalisés), s10 externalise la **2e famille de contenu à liste finie** :
les jeux « mot → réponse » du bandit manchot — **le bon son**, **le bon petit mot** (homophones),
**la première lettre**, **la nature du mot**, **le bon mot en anglais**. Chacun était une sous-classe
Java portant sa liste en dur, avec des **stratégies de distracteurs hétérogènes** :

- **distracteurs explicites** par item (le bon son : sons absents du mot ; homophones : l'homophone piège) ;
- **distracteurs tirés d'un vivier** de réponses (anglais : autres traductions ; nature : autres natures ;
  première lettre : autres lettres) — comportement **dynamique** (distracteurs variables à chaque tirage).

Objectif : rendre ce contenu **éditable sans dev** (DEC-0006) tout en préservant la Clean Architecture
(domaine pur, pas d'IO), les 5 règles ArchUnit, et le comportement de jeu.

## Décision

### 1. Un générateur GÉNÉRIQUE dans le domaine : `MotChoixGenerator`
`domain.content.impl.MotChoixGenerator` remplace les 5 sous-classes. Il reçoit de l'extérieur : une
**consigne** fixe, une **liste d'`Entree(prompt, bonne, distracteurs)`**, et un **mode de distracteurs** :
- `explicit(descriptor, question, entrees)` : distracteurs = ceux de chaque entrée ;
- `fromPool(descriptor, question, entrees, distractorCount)` : distracteurs tirés **aléatoirement**
  parmi l'ensemble des autres bonnes réponses du jeu (comportement dynamique conservé).

Le prompt s'affiche sur le rouleau (`reelSymbols = [prompt]`), la clé d'item reste
`Item.keyOf([prompt], question)` (anti-doublon de session inchangé). Le domaine ne lit **aucun fichier**.

### 2. Le contenu vit dans `content/mots/*.yml`, chargé + validé en `bootstrap`
`MotsPackLoader` (comme `QuizPackLoader`/`ContentPackLoader`) scanne `classpath*:content/mots/*.yml`
(tri par nom de fichier), parse (SnakeYAML) et **valide au démarrage** : champs requis, ≥1 item, prompts
uniques ; mode `explicit` → distracteurs présents, distincts, ≠ bonne ; mode `pool` → `distractorCount ≥ 1`
et **assez de réponses distinctes** dans le vivier pour tirer les distracteurs. Un fichier incohérent fait
échouer le boot avec un message explicite. `BeansConfig` boucle sur les paquets et construit une entrée
bandit manchot (`simpleGame`) alimentée par un `MotChoixGenerator` du bon mode.

### 3. Schéma YAML
```yaml
entryId: le-bon-son
title: "Le bon son"
gameKey: le-bon-son
gameLabel: "Le bon son"
matiere: "Français"
niveau: "CP"
domaine: "Décodage — association graphème/phonème"
question: "Quel son entends-tu ?"
distractorMode: explicit         # ou "pool" (+ distractorCount)
items:
  - { prompt: "MAMAN", bonne: "[an]", distracteurs: ["[on]", "[in]"] }
```

## Règle exécutable ArchUnit

**Aucune nouvelle règle requise.** Chargement/parse/validation vivent en `bootstrap` (SnakeYAML,
`PathMatchingResourcePatternResolver`), jamais dans `domain`/`application` : layering (règle 1) + pureté
du domaine (règle 2) tiennent. `MotChoixGenerator` est un `ContentGenerator` de `domain.content.impl`,
dont aucune mécanique ne dépend (règles 4/5 vertes). **5 règles ArchUnit vertes** (vérifié). Couverture :
`MotChoixGeneratorTest` (domaine, 2 modes) + `MotsPackLoaderTest` (bootstrap, 5 fichiers réels + rejets).

## Conséquences

- **Positif** : 5 sous-classes supprimées ; l'utilisatrice édite/ajoute un jeu « mot → réponse » en
  modifiant un YAML ; un **nouveau** fichier devient un jeu au redémarrage (scan classpath).
- **Positif** : un seul générateur générique + un schéma pour toute la famille ; validation stricte au boot.
- **Changement de comportement mineur et assumé** — *première lettre* : les distracteurs (mode `pool`)
  sont désormais tirés parmi les **lettres initiales présentes dans la liste** (≈10 lettres) au lieu de
  n'importe quelle lettre A–Z. Toujours des lettres majuscules plausibles, distinctes de la réponse ;
  jugé neutre-à-positif pédagogiquement. *Anglais* et *nature du mot* : comportement **identique**
  (le vivier = exactement l'ensemble des réponses, comme avant).
- **Limite** : *syllabes* (options **numériques** + réglage `maxSyllabes`) et les *textes à trous* restent
  en dur — schémas propres à traiter dans une prochaine tranche.

## Alternatives considérées

- **Un fichier listant explicitement tous les distracteurs partout** : imposerait d'écrire ~100 distracteurs
  pour l'anglais et figerait le tirage dynamique. Écarté au profit du **mode `pool`** (vivier) qui reproduit
  le comportement historique sans énumération.
- **Un schéma/loader par jeu** : dupliquerait la tuyauterie. Écarté : un schéma commun + un mode de
  distracteurs déclaratif couvre toute la famille.
- **Garder les distracteurs A–Z pour la première lettre** (mode dédié) : complexité pour un gain nul.
  Écarté ; le mode `pool` suffit, changement documenté.
