# Suite du relecteur - camille-po, s55

- **Auteur** : `camille-po`, le 2026-10-08.
- **Arbre** : worktree `s55-camille-relecture`, branche issue de `round-two` a `82e58343` (`git log -1 --format=%h` au depart). `bash outils/verifier-lignee.sh` : "descend de la lignee de `round-two`".
- **Source** : `relecture-cloture.md`, section 3. Points a moi : `R1`, `R3`, `R6`. `R4` : reserves ouvertes de `nadia-prof`, rien a faire. `R2` et `R5` : a `sofia-sm`.
- **Ecrit** : `DEC-0115` amendement A.18 a A.21, deux lignes de `docs/backlog/backlog.md`, une entree de `DEC-index.md`. `planning.md` non touche.

## Verdict par point

| Point | Reel ? | Mesure | Traite en |
|---|---|---|---|
| `R1` formule de la forme 3 absente | Oui | `grep -c 'recu de MASTER'` sur `DEC-0115` : 0 ; `grep -c 'forme 3'` : 12 lignes ; backlog "Sprint 55" : l'entree `S55-ART-CP-10-MECANIQUE` et le paragraphe "A.1 a A.8" disent "forme 3" sans la formule | A.18, backlog |
| `R3` 0,25 de A non alloues | Oui a `af8b326e`, a moitie corrige a `82e58343` | `git show af8b326e:<DEC>` : aucun titre `### A.14` a `A.17` ; A.16 (`a278a438`, non ancetre de `af8b326e`) dit deja "Q-U4 devenue un fait, A a 4,0". A.3, A.7, A.8, l'index et le backlog (paragraphe A.1 a A.8) disent encore l'inverse | A.19, backlog, index |
| `R6` point 5 de "Decisions prises" | Oui | point 5 : `ART-RES` "sans instance", premier report ; A.2 : une instance, pas de report ; A.7 ne liste pas ce point | A.20 |

## Ce que R1 implique pour le financement

Un enonce recu de MASTER ne fonde aucune `DEC` (`DEC-0062` section 3.2, confirmee par `Q6` le 2026-08-31). Le financement de la mecanique d'`ART-CP-10` n'a que cet enonce pour source. Il est donc consigne comme une execution, pas fonde comme une decision, jusqu'a ce que les mots de l'utilisatrice soient ecrits en forme 2 (`Q6`, ouverte). Statut de `DEC-0115` : `Proposee`, inchange. Les criteres de la story viennent d'`ADR-0104` (forme 1), aucune ligne de matrice ne bouge.

## Arithmetique corrigee (R3)

A : 1,0 + 1,0 + 0,25 + 0,5 + 0,25 + 0,25 + 0,25 = 3,5, plus 0,5 de marge = 4,0. A + B = 4,0 + 3,75 = 7,75 sur 8,0. L'ecart de lecture sur B (3,5 ou 4,0) est a `nadia-prof`, deja signale au backlog.

## Laisse volontairement

- Deux lignes du backlog de s54 (entrees citant `DEC-0114`) portent le meme manque de formule : hors du diff de s55, pas de balayage retroactif (`DEC-0062` section 7).
- `planning.md` lignes 27 et 395 : a `sofia-sm`.

## Mesure

`bash outils/mvn-jdk21.sh -B clean verify` (reacteur complet, tableaux Markdown gardes, `ADR-0087`), lance apres l'ajout de ce fichier au suivi : code 0, `BUILD SUCCESS`, adapter-content 1419 tests, bootstrap 333 tests et 6 de Failsafe, 0 echec ; l'enveloppe rapporte le JDK 21.0.10, la version de la machine virtuelle des tests n'est pas relue (`ADR-0071`). Jobs de `ci.yml` lances, un a un : `build` (ci-dessus, hors etapes de package), `chemins` (`verifier-chemins-test.sh` 0, `verifier-chemins.sh` sur `git ls-files` hors le test 0), `prefixes` (`verifier-prefixes-test.sh` 0, `verifier-prefixes.sh` sur le sujet du commit 0). `outils` et `commit-atteignable.sh` non lances : aucun outil modifie. Le workflow n'a pas tourne sur GitHub. Aucun Done prononce.
