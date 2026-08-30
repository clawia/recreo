# [Sprint s22] Rapport de cloture - couverture maximale du catalogue

## L'increment livre, en une phrase

Trois paquets CM1 neufs entrent au catalogue apres un cycle complet de refus puis de correction
pedagogique - `bandit-manchot-multiplications-divisions-cm1` (calcul mental x10/x100, **VALIDE**),
`bandit-manchot-problemes-durees-cm1` (problemes de durees, **VALIDE SOUS RESERVE**) et
`bandit-manchot-comprehension-cm1` (comprehension de texte, **VALIDE**) - portes par un champ
`texteSupport` neuf sur `Question` ; la couverture programme passe de 18/43 (41,9 %) a 21/43
(48,8 %) et le catalogue de 21 a 24 jeux.

## Les decisions du sprint

| Numero | Titre | Statut |
|---|---|---|
| `ADR-0063` | Un item ne presente jamais plus de trois propositions, et les deux autres regles de forme restent des conseils | Accepte (cree s21) ; amende a s22 (2026-08-29) : la regle 4 (porte de rendu avant fusion sur le pli) perd son demandeur avec l'arbitrage 1 de `DEC-0053` et passe d'etape de revue a conseil de premier recours, datee plutot que supprimee |
| `ADR-0064` | Un garde-fou borne ses tirages par ce que le paquet declare, et une borne ecrite a la main ne descend jamais sous le catalogue | Accepte (cree s22, 2026-08-29). Ferme l'obstacle 16 : cinq garde-fous bornaient leurs tirages a 60 alors qu'un paquet en declare 99, 39 items par garde-fou non examines en silence |
| `DEC-0025` | Levee de la condition M4 de `nadia-prof` : le sprint 10 est livre avec un defaut de pli assume par ecrit | Acceptee (cree s10) ; amendee une seconde fois a s22 (2026-08-29), consequence de `DEC-0053` sur la portee du point 4, rien d'autre requalifie |
| `DEC-0045` | "Nom ou verbe ?" quitte le catalogue jouable ; regle generale sur l'exposition au catalogue face a un verdict `REFUSE` | Acceptee (creee avant s22) ; amendee a s22 par une note renvoyant a `DEC-0055` sous sa section 3, section elle-meme non reecrite |
| `DEC-0053` | Arbitrages de l'utilisatrice pour s22 : le pli sort de la porte pedagogique sur tout le catalogue, perimetre du sprint fixe (trois paquets CM1 prioritaires) | Acceptee (cree s22, 2026-08-28) |
| `DEC-0054` | Deux branches locales non fusionnees (`hs-nadia-arbitrages`, `s20-ines-2`) : contenu verifie recapture ou rendu sans objet, abandon des deux | Acceptee (cree s22, 2026-08-28) |
| `DEC-0055` | `DEC-0045` section 3 amendee : une fenetre de grace peut s'ouvrir sur un `REFUSE`, sous trois conditions cumulatives verifiables et une borne de fermeture obligatoire | Acceptee (cree s22, cloture, 2026-08-30). Troisieme cas instruit comme annonce a s21 (`DEC-0052` section 8) |
| `DEC-0056` | La porte de l'accentuation du texte lu par l'enfant reste celle de `relecteur-pedagogique`, telle que `DEC-0002` l'a deja tranchee le 2026-08-04 - clarification, aucun arbitrage neuf | Acceptee (cree s22, cloture, 2026-08-30). Deux fiches d'agent corrigees en consequence |

Aucune decision de ce sprint n'a ete refusee ni retiree. Le detail de chacune vit dans `review.md`
et dans les fichiers cites.

## Etat des deux Done

- **Technique : vert.** `bash outils/mvn-jdk21.sh -B clean verify` sur le reacteur complet
  (racine, aucun `-pl`), sur `HEAD` apres les onze fusions du sprint : **BUILD SUCCESS, 761 tests,
  0 echec, 0 erreur, 0 ignore** (methode XML : somme des attributs `tests`/`failures`/`errors`/
  `skipped` de l'element `<testsuite>` des 116 fichiers `TEST-*.xml` de `target/surefire-reports/`
  et `target/failsafe-reports/`, mesure par moi a la cloture). **30 regles ArchUnit, inchange par
  le sprint** - aucune des trois dernieres branches fusionnees ne touche un fichier `.java` au-dela
  d'un commentaire.

- **Pedagogique : trois Done prononces, aucun refus ouvert a la cloture.** Les trois paquets ont
  chacun ete refuses une premiere fois le 2026-08-29 (motifs distincts : accentuation manquante,
  distracteurs de duree non plausibles, item resoluble sans lire le texte), corriges sur la tete de
  leur branche d'origine, puis reaudites et valides le 2026-08-30 : `S13-2` **VALIDE**, `S13-3`
  **VALIDE SOUS RESERVE** (cinq reserves non bloquantes), `S17-2` **VALIDE** (deux reserves non
  bloquantes). Premier sprint ou la totalite des paquets livres passe par un cycle `REFUSE` ->
  correction -> reaudit et aboutit dans le meme sprint.

## Le mouvement de couverture et de catalogue

**18/43 (41,9 %) devient 21/43 (48,8 %)**, verifie ligne par ligne sur la matrice fusionnee (47
lignes de competence au total, 4 `non couvrable en l'etat` retirees du denominateur atteignable) :
20 `non couvert`, 2 `en cours`, 21 `couvert-valide`. Trois lignes passent `couvert-valide` -
"Calcul mental - multiplications et divisions simples (multiples de 10, 100)" et "Resoudre des
problemes de durees" (mathematiques CM1), "Comprendre un texte narratif ou documentaire"
(francais CM1). Le catalogue passe de **21 a 24 entrees** (fichiers YAML de contenu), verifie par
comptage direct de l'arbre.

## Obstacles resolus et obstacles encore ouverts

Tableau complet (15 lignes) dans `review.md`. **Onze obstacles sur quinze sont clos** : un cliquet
de garde-fou borne a la main (obstacle 16 de s21, ferme par `ADR-0064`), une enveloppe de version
de JDK non verifiee, une regle d'`ADR` ayant perdu son demandeur, une contradiction entre deux
chartes d'agent (`marc-dev`/`lea-dev`) et la pratique reelle, un conflit de fusion a trois resolu,
un angle mort de garde-fou sur l'espace insecable, un ecart de methode de comptage de tests, un
recensement de bornes de tirage incomplet, un ecart de comptage de fiches, une reference de
`nadia-prof` a une valeur deja abandonnee par le produit, et le regime de retrait du catalogue sur
un `REFUSE` (obstacle 14, ferme par `DEC-0055`). **Un obstacle est partiellement clos** :
l'obstacle 15 (a qui appartient la verification de l'accentuation) voit son volet perimetre ferme
par `DEC-0056` (la porte reste celle de `relecteur-pedagogique`, deux fiches corrigees), mais sa
faisabilite technique reste ouverte. **Trois obstacles restent ouverts, visiblement** :

- **Faisabilite d'un garde-fou executable sur l'accentuation** du texte servi par un paquet de
  contenu (population entiere, pas un echantillon) - route a `theo-archi` par `DEC-0056`.
- **`TYP-1` (le moyen)** : le choix du caractere d'espace insecable et son application a
  `catalogue.yaml` restent a trancher - `catalogue.yaml` porte aujourd'hui zero insecable sur les
  signes de ponctuation double qu'il sert. Route a `theo-archi` et `sofia-sm`.
- **Sept reserves non bloquantes** (cinq sur `S13-3`, deux sur `S17-2`) versees en stories dans
  `docs/backlog/backlog.md`, dont l'obstacle 25 residuel (cinq scripts de `nadia-prof` executes a
  l'import, non testes par un juge pur, route a `theo-archi`) et l'obstacle 21 (4 items sans levier
  connu, route a `camille-po`) restent hors perimetre de ce sprint.

Aucun de ces trois n'est ferme par ce document.

## Ce que la retro instruit

Le fil rouge d'un chiffre ou d'un vert qui s'affiche sans que ce qui compte ait ete regarde tient
une seconde fois (sept defauts a s21, une famille comparable a s22), et deux chartes d'agent ont
contredit une decision du depot en deux sprints consecutifs sans qu'aucun garde-fou ne le
detecte avant qu'un agent n'y bute en situation reelle. `nadia-prof` s'est trouvee en tort deux
fois sur son propre reaudit et le nomme elle-meme, sans deplacer ses poteaux. Detail complet,
ce qui est tranche et ce qui est propose avec son destinataire : `retro.md`.

## Consommation de tokens

Genere par `python outils/rapport-tokens.py --sprint s22` ; le detail complet vit dans
`RAPPORT-tokens.md` et dans `squad/rapports/JOURNAL-tokens.md`. Fenetre : 2026-08-28 15:07 a
2026-08-30 08:40 UTC (horodatages de `planning.md` et du dernier ajout date de `review.md` - voir
la note sur la correction de `outils/rapport-tokens.py` dans ce meme fichier).

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 453 | 906 | 209 306 | 1 925 462 | 58 626 823 |
| `sofia-sm` | claude-sonnet-5 | 1036 | 2 072 | 488 565 | 2 991 026 | 136 904 157 |
| `theo-archi` | claude-opus-5 | 274 | 548 | 154 250 | 1 284 590 | 48 580 577 |
| `marc-dev` | claude-sonnet-5 | 444 | 888 | 213 473 | 1 985 004 | 99 934 630 |
| `lea-dev` | claude-sonnet-5 | 334 | 668 | 131 736 | 855 996 | 47 954 057 |
| `yanis-qa` | claude-sonnet-5 | 143 | 286 | 50 056 | 477 484 | 19 354 221 |
| `relecteur-pedagogique` | claude-sonnet-5 | 130 | 260 | 41 060 | 588 197 | 23 868 910 |
| `nadia-prof` | claude-opus-5 | 302 | 604 | 204 279 | 1 934 619 | 37 484 685 |
| orchestrateur (fil principal) | claude-opus-5 | 199 | 398 | 396 010 | 3 447 473 | 47 975 278 |
| **Total** | | **3315** | **6 630** | **1 888 735** | **15 489 851** | **520 683 338** |

Aucune invocation mesuree sur cette fenetre pour `ines-design` : pas de travail de design ce
sprint, ce n'est pas une invocation a zero token.
