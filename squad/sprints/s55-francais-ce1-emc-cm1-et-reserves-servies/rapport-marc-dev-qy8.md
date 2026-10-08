# Rapport de marc-dev, s55, Q-Y8 (apostrophe typographique non gardee)

Worktree `s55-marc-qy8`, base `e661879a` (`round-two`). `bash outils/verifier-lignee.sh` : lignee de `round-two` confirmee.
Rien rebase, fusionne ni pousse. Aucun YAML, aucun garde-fou affaibli, aucun motif modifie.

## Forme du cas

`avis-faisabilite.md` T4 donne la forme de l'echappement (`\u2019` dans le motif), pas celle d'un cas fabrique.
Forme choisie, la plus simple : un `@Test` par paquet, qui passe au motif de ce paquet une chaine fabriquee avec
l'apostrophe droite, puis une avec U+2019 ecrit `’` dans le source (aucun caractere U+2019 litteral).
Nom : `lElisionDeTuEstReconnueAvecLApostropheDroiteEtLApostropheTypographique`.

| Test de paquet | Motif | Test ajoute |
|---|---|---|
| `PaquetCm1EmcAtteinteAutruiTest` | `ADRESSE_A_L_ENFANT` | oui |
| `PaquetCm1EmcPrincipesRepubliqueTest` | `ADRESSE_A_L_ENFANT` | oui |
| `PaquetCm1EmcRespectDifferencesTest` | `ADRESSE_A_L_ENFANT` | oui |
| `PaquetCm1SciencesActiviteHumaineTest` | `JETONS_D_ADRESSE` | oui |

Recherche : `grep -n 'u2019' adapters/adapter-content/src/test/java/com/recreo/adapter/content/Paquet*.java` : 4 motifs, ces 4 fichiers.

## Mutation de yanis-qa, rejouee (ADR-0013)

Motif reduit a `\bt'` dans les 4 fichiers, avec les 4 tests neufs, `-pl adapters/adapter-content -am`, 4 classes :
50 tests, **4 echecs**, un par classe (les nouveaux). Sans les tests neufs, la meme mutation donne 46 tests, 0 echec (constat de yanis-qa).
Motif restaure ensuite : `git diff` ne montre que 24 lignes ajoutees (6 par fichier), 0 retiree.

## Mesures

- JDK rapporte par Surefire (`java.version`) : 21.0.10.
- Delta : +4 tests, base `e661879a`, perimetre `adapters/adapter-content` (1423 mesures dans ce worktree apres ajout, soit 1419 sur la base ; ce n'est pas un total fusionne).
- `bash outils/mvn-jdk21.sh -B clean verify` : BUILD SUCCESS (120, 18, 407, 1423, 333 tests, plus 6 de Failsafe).

## Jobs de `ci.yml`

- Lance : `build` (equivalent `mvn -B clean verify` via l'enveloppe), `chemins` (garde-fou et liste des fichiers suivis).
- Non lances : `prefixes` (juge le sujet du commit, apres commit), `outils` (aucun outil modifie).
