# `squad/` — mode d'emploi

`squad/` est le **journal opérationnel** de l'équipe (humaine + agents Claude Code) qui
construit `recreo`. Principe de gouvernance : **le dépôt Git est la seule mémoire partagée**.
Une cérémonie qui n'a pas de fichier dans `squad/ceremonies/` n'a pas eu lieu ; une décision non
écrite n'existe pas.

Ce dossier n'est pas de la documentation à côté du code : c'est un **livrable de première
classe**, au même titre que le code Java. Il est lu et écrit par les agents de la squad
(`camille-po`, `sofia-sm`, `theo-archi`, `yanis-qa`, `marc-dev`, `lea-dev`) et par
l'utilisatrice, seule autorité pédagogique.

---

## 1. Conventions de nommage

Le tri alphabétique d'un dossier doit toujours donner le tri chronologique/logique. D'où :

| Type de fichier | Motif | Exemple |
|---|---|---|
| Cérémonie | `squad/ceremonies/AAAA-MM-JJ-sNN-<ceremonie>.md` avec `<ceremonie>` ∈ {`planning`, `daily`, `review`, `retro`} | `squad/ceremonies/2026-07-17-s01-planning.md` |
| ADR technique | `squad/decisions/adr/ADR-XXXX-<slug>.md` (numéro à 4 chiffres, **jamais réutilisé**, même si un ADR est refusé/remplacé) | `squad/decisions/adr/ADR-0001-clean-architecture-maven.md` |
| Décision produit/pédago | `squad/decisions/produit/DEC-XXXX-<slug>.md` (même règle de numérotation) | `squad/decisions/produit/DEC-0001-bandit-manchot-calcul-mental.md` |
| Fiche de conformité | `squad/conformite/FICHE-<jeu>.md` (un jeu = une mécanique + son contenu pédagogique, une fiche par jeu) | `squad/conformite/FICHE-bandit-manchot-calcul-mental.md` |

Règles complémentaires :
- **Un fichier par cérémonie** (jamais d'ajout dans un fichier existant) : diffs Git propres, pas
  de conflit d'écriture concurrente, tri = date. Les vues agrégées viennent des index et de la
  file d'attente, pas d'un gros fichier unique.
- `ADR-index.md` et `DEC-index.md` sont des **tableaux récapitulatifs** (un ADR/DEC = une ligne),
  tenus à jour par leur agent responsable (Théo pour les ADR, Camille pour les DEC) à chaque
  création/changement de statut.
- Les numéros ADR/DEC ne sont jamais réutilisés, même en cas de refus (`REFUSÉ`) : ça garde
  l'historique honnête.

## 2. Pilotage par incrément (pas de timebox)

`recreo` n'a **pas de sprint calé sur 48h**. Un « sprint » = un **incrément livrable** ; les
agents enchaînent par salves de travail réel (minutes ou heures), le rythme est dicté par la
capacité et surtout par le **frein de validation pédagogique humaine** (voir règle d'or ci-dessous).

- Le fichier `…-planning.md` d'un sprint porte l'**horodatage de début** du sprint.
- Le fichier `…-review.md` du même sprint porte l'**horodatage de fin** et la **durée effective**
  calculée (fin − début).
- `squad/ceremonies/JOURNAL-durees.md` agrège toutes ces durées dans un tableau
  `sprint → début → fin → durée → incrément livré`, pour garder une vue d'ensemble du rythme réel
  sans avoir à rouvrir chaque review.

Ce n'est pas du reporting pour le reporting : ça permet de répondre honnêtement à « combien de
temps ça a vraiment pris » sans se raccrocher à une convention de sprint artificielle.

## 3. Double « Done »

Deux notions de « fini », jamais confondues :

- **Done technique** — prononcé par la CI. Concrètement : `mvn verify` vert (compile + tests
  unitaires + tests d'intégration H2 + ArchUnit), commit(s) correctement préfixés par agent.
  Yanis et Théo en sont les garants côté squad, mais c'est la CI qui juge, pas un agent.
- **Done pédagogique** — prononcé **uniquement par l'utilisatrice**, après démo, jamais par un
  agent. Il déclenche la mise à jour de `squad/validation/FILE-ATTENTE.md`, du statut des `DEC`
  concernées, et de la fiche de conformité du jeu.

Un incrément peut être « Done technique » (CI verte) sans être « Done pédagogique » (pas encore
validé par l'utilisatrice) : c'est l'état normal d'un incrément qui attend en
`FILE-ATTENTE.md`.

## 4. Règle d'or

**Jamais plus de 2-3 incréments (sprints) non validés pédagogiquement en attente dans
`FILE-ATTENTE.md`.** Au-delà, le planning suivant déclenche un **GEL DU CONTENU** : priorité
absolue à la préparation de démo et à faire valider l'existant, pas de nouvelle story de contenu
tant que la file n'est pas redescendue sous le seuil. Cette règle est portée à la fois par
`camille-po` (qui alimente la file) et `sofia-sm` (qui anime le planning et la fait respecter).

## 5. Préfixes de commit par agent

Chaque agent préfixe **tous** ses commits par son tag, format `[TAG] <résumé>` (ex.
`[CAM] Story: additions CP jusqu'à 10`). Ça rend l'auteur reconnaissable d'un coup d'œil dans
`git log`, y compris via `git log --grep='^\[THEO\]'`.

| Agent | Rôle | Tag |
|---|---|---|
| Camille | Product Owner | `[CAM]` |
| Sofia | Scrum Master + dev senior | `[SOF]` |
| Marc | Dev senior Java | `[MARC]` |
| Léa | Dev senior Java | `[LEA]` |
| Théo | Architecte | `[THEO]` |
| Yanis | QA | `[YAN]` |

La CI peut refuser un commit dont le préfixe n'est pas dans cette liste (garde-fou léger,
optionnel — voir `.github/workflows/ci.yml`).

## 6. Checklist de déroulé d'un sprint

Ordre d'invocation des agents, à suivre à chaque incrément :

1. **Planning**
   - `camille-po` : écrit/actualise les stories du sprint avec critères d'acceptation testables
     et **compétence programme visée** ; met à jour `squad/programme/MATRICE-couverture.md`.
   - `theo-archi` : vérifie la faisabilité technique, écrit un ADR si une décision d'architecture
     est nécessaire.
   - `sofia-sm` : fige l'objectif du sprint, affecte le travail, écrit
     `squad/ceremonies/AAAA-MM-JJ-sNN-planning.md` avec l'**horodatage de début**.
2. **Dév**
   - `marc-dev` / `lea-dev` / `sofia-sm` (casquette dev) codent et écrivent les tests associés.
   - `yanis-qa` écrit les tests couvrant les critères d'acceptation et fait tourner la CI locale.
   - Tout obstacle est remonté à `sofia-sm`, qui **classe → route → attend la décision écrite →
     débloque** (jamais elle ne tranche produit ou technique elle-même) :
     - obstacle produit/pédago → `camille-po` (produit une `DEC`)
     - obstacle technique → `theo-archi` (produit un `ADR` + règle ArchUnit)
     - obstacle qualité → `yanis-qa`
     - obstacle de capacité/charge → `sofia-sm` répartit entre Marc/Léa
3. **Daily** (une salve peut n'avoir qu'un seul daily, selon la durée réelle du sprint)
   - `sofia-sm` collecte l'avancement et les obstacles, route ce qui doit l'être, écrit
     `squad/ceremonies/AAAA-MM-JJ-sNN-daily.md` avec le tableau d'obstacles (description /
     catégorie / agent routé / décision liée / statut), et surveille la taille de
     `FILE-ATTENTE.md`.
4. **Review**
   - `yanis-qa` rapporte l'état CI (compile / tests / ArchUnit).
   - `theo-archi` confirme que les règles ArchUnit sont vertes.
   - `camille-po` met à jour la fiche de conformité **et** la matrice de couverture, puis pousse
     l'incrément en `FILE-ATTENTE.md` avec le statut `À VALIDER`.
   - `sofia-sm` écrit `squad/ceremonies/AAAA-MM-JJ-sNN-review.md` avec l'**horodatage de fin** et
     la **durée effective**, reportée dans `squad/ceremonies/JOURNAL-durees.md`.
5. **PORTE HUMAINE**
   - L'utilisatrice joue la démo, prononce (ou non) le **Done pédagogique**, et — aux jalons
     (environ une session par jeu terminé) — fait un **playtest enfants**.
   - Elle met à jour `FILE-ATTENTE.md`, le statut de la/des `DEC` concernées, et la fiche de
     conformité.
6. **Rétro**
   - `sofia-sm` anime, écrit `squad/ceremonies/AAAA-MM-JJ-sNN-retro.md`, et ajuste ce fichier
     (`squad/README.md`) si le process a besoin d'évoluer.

Les 6 templates Markdown vierges utilisés à chaque étape sont regroupés dans
[`squad/TEMPLATES.md`](TEMPLATES.md).

## 7. Frontières entre agents (rappel)

- Camille ne décide pas la technique.
- Théo ne décide pas le pédago.
- Marc et Léa n'inventent pas de règle d'architecture : ils remontent à Sofia, qui route vers
  Théo si besoin.
- Aucun agent ne prononce le Done pédagogique — uniquement l'utilisatrice.

## 8. Arborescence de `squad/`

```
squad/
├─ README.md                     # ce fichier
├─ TEMPLATES.md                  # les 6 templates vierges
├─ ceremonies/                   # journal trié par date, + JOURNAL-durees.md
├─ decisions/
│  ├─ adr/      ADR-XXXX-<slug>.md + ADR-index.md      (Théo)
│  └─ produit/  DEC-XXXX-<slug>.md + DEC-index.md      (Camille)
├─ programme/
│  ├─ MATRICE-couverture.md      (Camille)
│  ├─ mathematiques/             # référentiels de compétences
│  └─ francais/
├─ conformite/  FICHE-<jeu>.md   (Camille)
└─ validation/  FILE-ATTENTE.md  (queue "à valider" par l'utilisatrice)
```
