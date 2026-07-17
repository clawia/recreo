# Definition of Done — `recreo`

Deux notions de « fini », jamais confondues (voir `squad/README.md` §3 pour le détail process).
Un incrément peut être Done technique sans être Done pédagogique : c'est l'état normal d'un
incrément en attente dans `squad/validation/FILE-ATTENTE.md`.

## Done technique

Prononcé par la CI, confirmé par `yanis-qa` et `theo-archi` en review. Un incrément est Done
technique quand **toutes** les conditions suivantes sont vraies :

- [ ] `mvn verify` est vert à la racine :
  - [ ] compilation de tous les modules (`domain`, `application`, `adapters/*`, `bootstrap`).
  - [ ] tests unitaires verts (`domain`, `application`).
  - [ ] tests d'intégration verts sur H2 in-memory (`bootstrap`, `@SpringBootTest` + MockMvc).
  - [ ] règles ArchUnit vertes (couches, pureté du domaine, découplage mécanique/contenu,
        isolation des mécaniques entre elles).
- [ ] Le pipeline GitHub Actions (`.github/workflows/ci.yml`) est vert sur la branche.
- [ ] Tous les commits de l'incrément sont **préfixés par agent** (`[CAM]`, `[SOF]`, `[MARC]`,
      `[LEA]`, `[THEO]`, `[YAN]` — voir `squad/README.md` §5). Un commit non préfixé n'est pas
      considéré comme faisant partie d'un incrément Done.
- [ ] Aucune dépendance Spring/JPA introduite dans `domain` ou `application` (vérifié par
      ArchUnit, pas seulement par revue).
- [ ] Si l'incrément introduit une décision d'architecture, l'ADR correspondant existe dans
      `squad/decisions/adr/` avec le statut `Accepté` et une règle ArchUnit associée.

## Done pédagogique

Prononcé **uniquement par l'utilisatrice**, après démo. Aucun agent ne peut prononcer le Done
pédagogique à sa place. Un incrément est Done pédagogique quand :

- [ ] La démo a été jouée par l'utilisatrice (CP et CM1 quand applicable).
- [ ] L'utilisatrice a explicitement validé l'incrément (pas de validation implicite / silence
      qui vaut acceptation).
- [ ] `squad/validation/FILE-ATTENTE.md` est mis à jour : la ligne de l'incrément passe de
      `À VALIDER` à `VALIDÉ` (ou `REFUSÉ` le cas échéant), avec la date.
- [ ] Le statut de validation de la/des `DEC` concernées dans `squad/decisions/produit/` est mis
      à jour (`VALIDÉ (user, AAAA-MM-JJ)` ou `REFUSÉ`).
- [ ] La ligne correspondante de `squad/programme/MATRICE-couverture.md` passe (si validée) au
      statut `couvert-validé`.
- [ ] La fiche de conformité du jeu (`squad/conformite/FICHE-<jeu>.md`) est renseignée : bloc
      « validation utilisateur » complété, et bloc « playtest enfants » complété si le jalon de
      playtest est atteint.

## Rappel — règle d'or

Jamais plus de 2-3 incréments non validés pédagogiquement en attente simultanément dans
`FILE-ATTENTE.md`, sous peine de GEL DU CONTENU au planning suivant (voir
`squad/README.md` §4).
