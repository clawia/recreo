# [Sprint s01] Rapport de sprint - lever le refus pedagogique

- **Debut** : 2026-08-05 13:03
- **Fin** : 2026-08-05 15:45
- **Duree effective** : 2h42

## Increment livre

Le jeu `bandit-manchot-additions-cp`, refuse pedagogiquement au sprint 0, est maintenant jouable
par un enfant de CP de bout en bout - texte accentue, score lisible, ecran d'erreur franchissable,
partie de 10 manches avec fin et sortie, aide apres une serie d'echecs - et `nadia-prof` a prononce
**VALIDE SOUS RESERVE, Done pedagogique ACCORDE**, apres l'avoir reellement joue (171 manches sur
24 parties).

En detail :

- Les trois motifs de refus de s00 sont leves et verifies dans le HTML reellement servi : plus
  aucune faute d'accentuation, un score `Score : N` sans aucun symbole operatoire, un ecran
  d'erreur en francais avec un moyen de repartir (`ADR-0011`).
- La partie a desormais une fin (10 manches, `ADR-0012`) et une sortie visible sur tous les
  ecrans ; un enfant en echec recoit une "manche plus simple" apres trois echecs consecutifs.
- Un libelle introduit ce sprint a ete refuse par `nadia-prof` en cours de review ("La partie est
  finie. Bravo, tu as bien joué !" felicite un enfant a 0/10) et corrige dans le meme geste
  ("C'est fini !"), avec deux autres reformulations acceptees sous reserve appliquees en meme
  temps ("Retour à l'accueil" -> "Les jeux", l'indice -> "Compte sur tes doigts.").
- Deux constats de fond restent ouverts et routes, non tranches par cette review : la repartition
  des sommes tirees favorise fortement le haut de l'intervalle (43,9 % sur les sommes 9 et 10),
  et l'indice de S1-5 ne peut pas s'afficher dans une partie reelle de 10 manches.
- 23 regles ArchUnit (2 nouvelles, `ADR-0011`), toutes avec un contre-exemple qui les fait
  echouer ; **120 tests, 0 echec**, aucun module sans test.

## Decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| ADR-0011 | Rendre toute erreur imprevue en HTML francais, jamais un corps technique brut | Architecture | Accepte, **nouveau ce sprint** |
| ADR-0012 | Porter la longueur d'une partie par l'entree de catalogue, jamais par le contenu | Architecture | Accepte, **nouveau ce sprint**, amende deux fois pendant le sprint (paquet du contre-exemple corrige, derniere garde de comportement honoree) |
| ADR-0008 | Loger tout le comportement de jeu sur le serveur et brider le JavaScript par un budget | Architecture | Accepte, amende (renvoi vers `ADR-0011` sur le chemin d'exception, aucune garde existante modifiee) |
| DEC-0004 | Textes, forme du score et seuils chiffres pour lever le refus pedagogique du sprint 1 | Produit | Acceptee, **nouvelle ce sprint** |
| DEC-0005 | L'indice de S1-5 est un contenu YAML, jamais une constante du domaine | Produit | Acceptee, **nouvelle ce sprint**, correction technique non bloquante portee au backlog de s02 |

Deux `ADR` nouveaux, un `ADR` amende, deux `DEC` nouvelles. Aucune decision n'a ete prise pendant
les ceremonies de cloture elles-memes (review, retro) : les trois textes corriges pendant la
review (voir `review.md`) appliquent des reformulations deja ecrites par `nadia-prof` dans la
fiche de conformite, ce ne sont pas de nouvelles decisions.

## Etat des deux Done

| Done | Juge par | Etat | Reference |
|---|---|---|---|
| Technique | CI (`mvn verify`) | vert - **120 tests, 0 echec**, 23 regles ArchUnit toutes contre-exemplifiees, trois garde-fous de CI verts. Reverifie apres les trois corrections de texte de la review (commit `caa1049`). | `rapport-qualite.md`, `avis-architecture-cloture.md`, `confirmation-archunit.md` |
| Pedagogique | nadia-prof | **VALIDE SOUS RESERVE, ACCORDE** | `squad/conformite/FICHE-bandit-manchot-additions-cp.md` |

Reserve non close sur le Done technique : un echec transitoire de `LesReglesMordentTest` (2 echecs
sur 21), observe une fois pendant la confirmation de `theo-archi`, non reproduit sur huit
tentatives ulterieures ni sur la reverification finale de cette review. Aucun fichier ne le
tranche : il reste sous surveillance de la CI, pas classe comme clos.

## Obstacles

**Resolus** :

| Description | Agent route | Decision | Clos par |
|---|---|---|---|
| Trois motifs de refus de s00 | nadia-prof | verdict "LEVE" x3 | `FICHE-bandit-manchot-additions-cp.md` |
| Cinq points produit routes par theo-archi en planning | camille-po | `DEC-0004` | `05647a1` |
| Indice code en dur dans le domaine plutot que dans le YAML (obstacle de routage) | camille-po | `DEC-0005` | `7b83754` |
| `ADR-0012` incomplet (contre-exemple mal range, derniere garde non honoree) | theo-archi | `ADR-0012` amende | `82070bd` |
| Libelle de fin de partie REFUSE par nadia-prof | nadia-prof | reformulation "C'est fini !" ecrite dans la fiche | `caa1049` |
| Bouton "Retour à l'accueil" illisible au CP (sous reserve) | nadia-prof | reformulation "Les jeux" ecrite dans la fiche | `caa1049` |
| Indice trop long pour le lecteur fragile (sous reserve) | nadia-prof | reformulation "Compte sur tes doigts." ecrite dans la fiche | `caa1049` |
| Six libelles introduits sans DEC dediee, remontes par relecteur-pedagogique | nadia-prof | verdicts individuels dans la fiche, section 3 | `FICHE-bandit-manchot-additions-cp.md` |
| Trois worktrees verts separement, rouges ensemble | sofia-sm | correction directe | `d534451` |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| Repartition des sommes biaisee vers le haut (43,9 % sur 9-10, 7,6 % sous 5) | camille-po, theo-archi | 2026-08-05 (constat de nadia-prof en review) | une cible de repartition (produit) et, si elle depasse le contrat actuel, un ADR (architecture) |
| Indice de S1-5 inatteignable en jeu reel avant la 14e manche d'une partie de 10 | camille-po | 2026-08-05 (constat de nadia-prof en review) | un choix entre abaisser le seuil d'echecs sur le meme item ou declencher l'indice sur des echecs consecutifs |
| Echec transitoire de `LesReglesMordentTest`, non reproduit | theo-archi | 2026-08-05 | reproduction en CI, ou preuve qu'il n'y a pas de defaut reel |
| `squad/validation/FILE-ATTENTE.md` ne reflete pas encore le verdict "VALIDE SOUS RESERVE" | camille-po | 2026-08-05 | mise a jour au prochain planning |
| Arbre de travail partage entre agents pendant la porte technique, deux pertes de travail non commite evitees de justesse | sofia-sm | 2026-08-05 | amendement discute au prochain planning (voir `retro.md`) |
| Deux agents sans outil shell, livrable commite par un tiers sans que la regle ne soit ecrite | sofia-sm | 2026-08-05 | amendement discute au prochain planning (voir `retro.md`) |

## Couverture programme

`squad/programme/MATRICE-couverture.md`, ligne mathematiques / CP / "Calcul mental - additions
dans la limite de 10 (puis 20)" : **reste `en cours`**, malgre le Done pedagogique accorde.
`nadia-prof` motive explicitement l'ecart : le Done pedagogique porte sur l'increment tel qu'il
est aujourd'hui, la matrice porte sur la couverture reelle de la competence, et un jeu dont 43,9 %
des manches portent sur deux des onze sommes possibles ne couvre pas encore "les additions dans
la limite de 10" au sens du referentiel. Elle pose une condition ecrite pour que
`camille-po` fasse passer cette ligne a `couvert-valide` : la reserve sur la repartition des
sommes traitee.

## Consommation de tokens

Produit par `python outils/rapport-tokens.py --sprint s01`, ecrit dans
`squad/sprints/s01-lever-refus-pedagogique/RAPPORT-tokens.md` et cumule dans
`squad/rapports/JOURNAL-tokens.md`. Fenetre mesuree : 2026-08-05 11:03 a 2026-08-05 13:45 UTC
(horodatage de debut du planning et de fin de cette review, convertis en UTC).

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 68 | 406 | 48 862 | 549 529 | 3 489 263 |
| `sofia-sm` | claude-sonnet-5 | 138 | 612 | 62 048 | 743 269 | 13 625 287 |
| `theo-archi` | claude-opus-5 | 175 | 4 402 | 64 243 | 385 915 | 18 892 435 |
| `marc-dev` | claude-sonnet-5 | 149 | 700 | 58 077 | 500 086 | 17 743 907 |
| `lea-dev` | claude-sonnet-5 | 249 | 674 | 116 870 | 836 005 | 48 304 064 |
| `yanis-qa` | claude-sonnet-5 | 258 | 16 347 | 74 193 | 524 241 | 32 816 129 |
| `relecteur-pedagogique` | claude-sonnet-5 | 128 | 2 332 | 22 211 | 505 962 | 10 495 277 |
| `nadia-prof` | claude-opus-5 | 106 | 11 234 | 60 362 | 452 545 | 9 184 135 |
| `ines-design` | claude-sonnet-5 | 66 | 132 | 21 645 | 243 765 | 3 750 764 |
| orchestrateur (fil principal) | claude-opus-5 | 107 | 619 | 138 601 | 244 036 | 36 000 994 |
| **Total** | | **1444** | **37 458** | **667 112** | **4 985 353** | **194 302 255** |

Les quatre compteurs sont volontairement separes (les additionner classerait faux : le cache lu
depasse couramment l'output de deux ordres de grandeur), et ce ne sont pas des euros. Cette
fenetre inclut une partie de l'ecriture de cette ceremonie de cloture elle-meme (`sofia-sm`),
puisque `review.md` et `retro.md` sont ecrits avant l'horodatage de fin qu'ils portent.
