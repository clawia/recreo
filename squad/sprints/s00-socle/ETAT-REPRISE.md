# Sprint 0 - etat et point de reprise

> Note de passation, pas un artefact de ceremonie. A supprimer a la cloture du sprint 0.

## Pourquoi ce fichier existe

Le sprint 0 a ete ouvert dans une session ou les 8 agents de la squad n'etaient plus
enregistrables. La creation de la branche orpheline `round-two` a vide l'arbre de travail, donc
`.claude/agents/`, et l'outil a desenregistre les agents sans reprendre les 8 definitions
ecrites juste apres. Les fichiers sont commites et seront enregistres au prochain demarrage.

Consequence : la phase de planification a bien ete produite par `camille-po` et `theo-archi`,
mais la suite du sprint attend une session ou la squad est de nouveau invocable. C'est necessaire
pour que `outils/rapport-tokens.py` attribue la consommation aux vrais noms d'agents, et pour que
`theo-archi` et `nadia-prof` tournent bien sur opus.

## Ce qui est fait et commite

| Commit | Contenu |
|---|---|
| `09949d2` | Branche orpheline, referentiels de competences et 61 paquets YAML repris dans `contenu-repris/` |
| `3d3ef22` | Les 8 definitions d'agents et `squad/EQUIPE.md` |
| `275c94c` | `squad/README.md`, `squad/TEMPLATES.md`, `CLAUDE.md` |
| `41c0d39` | `outils/rapport-tokens.py` et la CI avec le garde-fou des prefixes |
| `ef3fda6` | Les 8 ADR fondateurs et l'avis de faisabilite de `theo-archi` |
| `9060b0e` | Matrice de couverture, backlog, `DEC-0001` a `DEC-0003`, fiche de conformite ouverte |

## Ce qui reste a faire, dans l'ordre

1. **`sofia-sm`** ecrit `squad/sprints/s00-socle/planning.md` au gabarit de `squad/TEMPLATES.md`,
   avec l'horodatage de debut. Les stories sont deja ecrites dans `docs/backlog/backlog.md`, les
   avis de faisabilite dans `avis-faisabilite.md`. Elle gele l'objectif et affecte.
2. **`marc-dev`, `lea-dev`, `sofia-sm` (casquette dev)** montent le socle et la mecanique :
   squelette Maven a cinq modules selon `ADR-0001`, regles ArchUnit des huit ADR **avec leurs
   contre-exemples** exiges par `ADR-0003`, mecanique du bandit manchot, chargeur YAML unique,
   contenu additions CP jusqu'a 10. Tests dans le meme commit que le code, dans tous les modules.
3. **`yanis-qa`** traduit les criteres d'acceptation en tests, lance `mvn verify`, produit le
   rapport qualite. **`theo-archi`** confirme ArchUnit avec le resultat de la commande.
4. **`relecteur-pedagogique`** passe sur les livrables non executables. Deux points l'attendent,
   voir plus bas.
5. **`nadia-prof`** joue l'increment et prononce le Done pedagogique dans
   `squad/conformite/FICHE-bandit-manchot-additions-cp.md`.
6. **`sofia-sm`** ecrit `review.md`, `retro.md`, lance
   `python outils/rapport-tokens.py --sprint s00`, puis ecrit `RAPPORT.md`.

## Points ouverts, deja identifies

**Pour `relecteur-pedagogique`.** `CLAUDE.md` decrit une stack a cinq modules dont un
`adapters/adapter-persistence`, et fait figurer "integration H2" dans la definition du Done
technique. `ADR-0001` ne cree pas ce module et lui substitue `adapter-content` ; `DEC-0003` acte
que le sprint 0 n'a besoin d'aucune durabilite. La documentation ne decrit donc pas ce que le
code fera : c'est exactement le critere d'exactitude du relecteur. `DEC-0003` trace l'ecart sans
le corriger, volontairement.

**Pour `nadia-prof`, plus tard.** `camille-po` signale que
`contenu-repris/associer/cp-formes.yml` contient des pictogrammes ambigus (deux triangles
differents pour une meme cible, un carre bleu). A auditer le jour ou une mecanique d'association
reprendra ce paquet. Sans objet pour le sprint 0.

## Verification de la reprise

```
git branch --show-current          # round-two
git log --oneline | head -6        # les six commits ci-dessus
git log main --oneline -1          # aa2b356, main intact
ls .claude/agents/                 # 8 fichiers
```
