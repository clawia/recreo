# s57 - relecture de l'orthographe des titres et consignes des neuf entrées de catalogue (`S57-QLM-CE1-ENTREES-CATALOGUE`, critère 4)

- **Auteur** : `relecteur-pedagogique`.
- **Date** : 2026-10-28.
- **Arbre** : branche `s57-p5-releco-catalogue`, base `round-two` à `d6933bda`.
- **Texte relu** : les neuf titres et les deux consignes de la section 1 de
  `lecture-nadia-entrees-de-catalogue-ce1-qlm-et-horloge.md` (fait foi, écrit par `nadia-prof`).
- **Norme du catalogue lue** : `adapters/adapter-content/src/main/resources/catalogue.yaml` tel que `83bccb3e` l'écrit
  (`git show`, sans checkout), ses entrées existantes comprises.
- **Périmètre** : orthographe, accents, majuscules, apostrophes, espaces, accords, ponctuation finale (`DEC-0002`, amendement
  du 2026-08-04). Le vocabulaire, le ton, le fond et le niveau scolaire restent à `nadia-prof` et ne sont pas jugés ici.
- **Aucun fichier du catalogue, aucun test, aucune lecture de `nadia-prof` n'est modifié.**

## 1. Normes relevées dans le catalogue

1. **Apostrophe : droite (`'`).** `catalogue.yaml` de `83bccb3e` : 95 lignes portent une apostrophe droite, 0 une apostrophe typographique (U+2019), comptées
   par `grep -c`. Exemples existants : "L'accord du verbe", "Les sources et les formes d'énergie".
   Les neuf textes la respectent.
2. **Espace avant `?`, `!`, `:`, `;`** : espace ordinaire (U+0020), jamais insécable (0 ligne avec espace insécable, `grep -c`). Exemples
   existants : "Nom ou verbe ?", "Qui mange qui ?", "Lis bien, puis trouve la réponse !". Aucun des neuf titres n'en porte ;
   les consignes n'en portent pas non plus.
3. **Ponctuation finale d'un titre** : aucune, sauf `?` pour un titre en question. Les neuf titres n'en portent pas.
4. **Ponctuation finale d'une consigne** : `.` pour une consigne à l'impératif sans exclamation (exemples : "Lis le texte, puis
   réponds à la question.", "Trouve le mot qui complète la phrase."), `?` pour une question, `!` pour quelques consignes
   existantes. Les deux consignes des neuf entrées finissent par `.`, comme leurs voisines.
5. **Guillemets du YAML** : droits (`"`), comme tout le fichier.

## 2. Verdict par ligne

| Ligne | Titre | Verdict titre | Consigne | Verdict consigne |
|---|---|---|---|---|
| `QLM-CE1-01` | "Grandir chez les animaux et les plantes" | juste | "Lis bien, puis choisis la bonne réponse." | juste |
| `QLM-CE1-02` | "Ce que mangent les animaux" | juste | "Lis bien, puis choisis la bonne réponse." | juste |
| `QLM-CE1-03` | "Ce qu'il faut à une plante" | juste | "Lis bien, puis choisis la bonne réponse." | juste |
| `QLM-CE1-04` | "Les familles d'aliments" | juste | "Lis bien, puis choisis la bonne réponse." | juste |
| `QLM-CE1-05` | "Les trois états de l'eau" | juste | "Lis bien, puis choisis la bonne réponse." | juste |
| `QLM-CE1-06` | "Ce que l'aimant attire ou non" | juste | "Lis bien, puis choisis la bonne réponse." | juste |
| `QLM-CE1-08` | "Les dangers de l'électricité" | juste | "Lis bien, puis choisis la bonne réponse." | juste |
| `QLM-CE1-10` | "Les paysages" | juste | "Lis bien, puis choisis la bonne réponse." | juste |
| `MAT-CE1-11` | "L'heure sur l'horloge" | juste | "Regarde les deux aiguilles, puis choisis la bonne heure." | juste |

**Correction proposée : aucune.** Rien n'est à valider par `nadia-prof`, dont l'écrit fait foi sur ces textes.

## 3. Règles vérifiées, point par point

1. **Accents** : "à" de "Ce qu'il faut à une plante" (préposition, distincte de "a", verbe) ; "états" ; "électricité" ; "réponse".
   Tous présents. Aucun titre ne commence par une voyelle accentuée.
2. **Majuscules** : initiale de chaque titre et de chaque consigne seulement ; aucun nom commun capitalisé en cours de titre.
3. **Élisions** : "qu'il", "d'aliments", "l'eau", "l'aimant", "l'électricité", "L'heure", "l'horloge" : apostrophe droite, pas
   d'espace.
4. **Accord** : "Ce que mangent les animaux" - le verbe s'accorde avec son sujet inversé "les animaux" (pluriel, `-ent`). "Les
   trois états" - pluriel de "état" après "trois". "Lis" et "choisis" - impératif 2e personne du singulier des verbes en `-ir` et
   de "lire" : `-s` final. "Regarde" - premier groupe, impératif sans `-s` (le `-s` ne reparaît que devant "en" ou "y").
5. **Virgule** : "Lis bien, puis choisis" et "Regarde les deux aiguilles, puis choisis" - virgule avant "puis", comme "Lis le
   texte, puis réponds à la question." dans le catalogue.
6. **Mots composés et traits d'union** : aucun à écrire ("ou non" sans trait).
7. **Signes interdits** (conventions de rédaction du dépôt) : aucun tiret cadratin, aucun guillemet typographique, aucun
   emoji dans les neuf textes.

## 4. Ce que je n'ai pas pu vérifier

1. **Le texte servi, tel que l'entrée le porte.** Les neuf textes sont lus dans la section 1 de `nadia-prof` et dans
   `83bccb3e`. Les entrées ne sont pas encore dans `catalogue.yaml` de ce worktree (`marc-dev` refait son commit après
   cet écrit) et `83bccb3e` porte encore deux titres que `nadia-prof` remplace : `QLM-CE1-01` ("Les étapes pour grandir")
   et `QLM-CE1-06` ("Ce que l'aimant attire"). Les deux titres de remplacement ne sont donc relus que dans l'écrit de
   `nadia-prof`. **L'orthographe de la chaîne effectivement écrite dans `catalogue.yaml` et dans la liste de
   `TexteAccentueExposeParLePortEntrantTest` n'est pas relue : elle doit l'être après le commit refait**, et la vérification
   qu'elle égale la section 1 mot pour mot revient à `nadia-prof` (section 5 de son écrit).
2. **Le rendu à l'écran** (apostrophes, espaces, retour à la ligne dans une tuile) : non vu, hors porte d'un relecteur de texte.
3. **Les titres existants du catalogue** : lus pour la norme (section 1), pas relus un par un.
