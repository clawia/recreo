# [Sprint s06] Rapport de sprint - ouvrir la conjugaison et solder la dette

- **Debut** : 2026-08-07 15:35 (`planning.md`)
- **Fin** : 2026-08-10 10:35 (commit `0434c3b`)
- **Duree effective** : 2 jours 19 heures

## Increment livre

Recreo gagne une deuxieme competence francais validee (les homophones grammaticaux CM1, `couvert-valide`), "Le bon son" (CP) sort de quarantaine et revient au catalogue, et une troisieme competence ouverte ce meme sprint (la conjugaison au present, CM1) est refusee a son premier audit et retiree du catalogue le jour meme, pour avoir enseigne une regle de grammaire fausse dans l'indice servi apres trois echecs.

Un chiffre a ne pas embellir : le sprint a livre huit stories sur les douze planifiees (S6-3 a
S6-14), en a sorti une du perimetre (le debordement de menu a 360 px, obstacle non etabli - voir
le tableau des obstacles), en a vu une deja satisfaite sans code a ecrire (S6-9, mecanisme deja
livre par `ADR-0022`), et **a produit un refus pedagogique sur un increment qu'il a lui-meme
cree** ("Le bon verbe", ouvert et refuse dans la meme fenetre). Une ligne de matrice passe
`couvert-valide`, une autre s'ouvre puis est retiree du catalogue jouable le jour de son audit.

Deux garde-fous d'outillage testes (`verifier-lignee.sh`, `verifier-jdk.sh`) et une commande de
mesure de rendu versionnee (`outils/mesurer-rendu.sh`, `ADR-0025`) equipent desormais les sprints
suivants.

## Decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| DEC-0012 | Le rhabillage du gabarit ouvre le sprint 6 ; le menu reste a une seule entree | Produit | Acceptee, **amendee** le 2026-08-07 (constat de l'absorption de S6-1/S6-2 dans s05) |
| DEC-0013 | Perimetre du sprint 6 : trois chantiers actes, choix de `cm1-conjugaison.yml` | Produit | Acceptee |
| DEC-0014 | Retrait de "Le bon verbe" du catalogue jusqu'a sa correction | Produit | Acceptee, executee dans le meme commit que sa creation (`6060d32`) |
| ADR-0017 | Comparer deux boutons d'un meme ecran | Architecture | Accepte, **second amendement** le 2026-08-07 (par `ADR-0022`) |
| ADR-0020 | Juger un ecran sur le document assemble apres l'echange htmx | Architecture | Accepte |
| ADR-0021 | Interdire a un contre-exemple de garde-fou de porter un stereotype Spring | Architecture | Accepte |
| ADR-0022 | Rendre la visibilite d'un habillage permanent dependante d'un marqueur d'etat | Architecture | Accepte |
| ADR-0023 | Exiger de tout jeu du catalogue qu'il offre strictement plus d'items que sa partie n'a de manches | Architecture | Accepte |
| ADR-0024 | Faire porter l'explication d'un item par le verdict, laisser la graphie au paquet | Architecture | Accepte |
| ADR-0025 | Rendre toute mesure de rendu rejouable par une commande versionnee, declarer le debordement non gardable | Architecture | Accepte |

## Etat des deux Done

| Done | Juge par | Etat | Reference |
|---|---|---|---|
| Technique | CI (`mvn verify`) | **Vert.** `mvn -B clean verify` sur `round-two` (`0434c3b`) : BUILD SUCCESS, 5 modules, **279 tests**, **28 `ArchRule`** (27 executees par `@ArchTest`, 1 par `.check()` explicite). Les deux plafonds de dette (balayage : 2, contenu : 0) valent exactement la taille de leur dette. | `review.md`, sections "Rapport qualite" et "Confirmation ArchUnit" |
| Pedagogique | nadia-prof | **Quatre verdicts distincts.** Homophones CM1 : **VALIDE**, ligne `couvert-valide`. "Le bon son" CP : **VALIDE SOUS RESERVE**, refus du 2026-08-06 leve. S6-9 et S6-10 : **VALIDE**. Conjugaison presente CM1 : **REFUSE**, premier audit, cinq indices sur douze enoncent une regle fausse. | `squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`, `FICHE-bandit-manchot-le-bon-son-cp.md`, `FICHE-bandit-manchot-additions-cp.md`, `FICHE-bandit-manchot-conjugaison-present-cm1.md` |

## Obstacles

**Resolus** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| `GET /parties/{jeuId}/manche` sur une partie terminee (`DEC-0013` point 3) | theo-archi | `ADR-0020` clause 3 | `a5ea324` |
| Identifiant `id="jeu"` en double apres un echange htmx | theo-archi | `ADR-0020` | `a5ea324` |
| Contre-exemple de garde-fou enregistre comme un vrai bean Spring | theo-archi | `ADR-0021` | `a5ea324` |
| Garde-fou generique inter-catalogue manquant pour S6-3 | theo-archi | `ADR-0023` | `b3c4a00` |
| Champ `graphie` non lu, porteur du texte de retour non modelise (lot S6-6/S6-7) | theo-archi | `ADR-0024` | `abe2f99` |
| Debordement du menu de l'en-tete a 360 px | ines-design, lea-dev, theo-archi, nadia-prof | non etabli : story sortie du sprint, confirme par l'audit de `nadia-prof` | `be60b10` / `9f7bba2` |
| Plancher de `chrome --headless --window-size` (500 px exact) faussant les mesures de rendu | yanis-qa, theo-archi | `ADR-0025`, `outils/mesurer-rendu.sh` | `b1aeaf9` |
| Deux mesures de rendu du sprint sans commande citable dans le depot | theo-archi | `ADR-0025` impose une commande versionnee pour toute mesure future | `b1aeaf9` |
| `verifier-lignee-test.sh`/`verifier-jdk-test.sh` testes mais jamais executes par un automate | theo-archi | etape CI `outils` ajoutee (`.github/workflows/ci.yml`) | `b1aeaf9` |
| `verifier-lignee.sh` refuse a tort un worktree simplement en retard sur `round-two` | sofia-sm (dev) | corrige, cas ajoute a la suite, morsure prouvee par mutation reelle | `2370519` |
| `verifier-lignee.sh` refuse a tort un worktree fourche d'une tete ancienne avec un commit propre | sofia-sm (dev) | corrige (test d'ancetre commun unique), morsure prouvee par mutation reelle | `8658aa8` |
| Deux mots exemples de `nadia-prof` (MAIN, PAIN) ecartes du paquet sans qu'elle le sache avant son audit | nadia-prof | information inscrite avant l'audit, non nommee comme bloquante dans son verdict | `9dee88b` |
| `bandit-manchot-conjugaison-present-cm1` REFUSE mais vivant dans `catalogue.yaml`, jouable | camille-po | `DEC-0014`, executee dans le meme commit que sa creation | `6060d32` |
| Citation dans `relecture.md` faisant rougir `verifier-chemins.sh` | sofia-sm (trouve a la porte technique) | corrigee directement | `86ea06d` |
| Six criteres de S6-3/S6-6/S6-7/S6-9/S6-14 ne verifiaient pas ce qu'ils croyaient verifier | camille-po | deja actionnees avant le planning | avant `bdd7ebf` |
| Camille-po a travaille sans worktree separe, sa consigne de fusion lue comme "ne pas commiter" | coordinateur | commite pour son compte sous son prefixe, en le disant (`f3b6d08`) ; rien perdu | `f3b6d08` |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| Correction des cinq indices fautifs de "Le bon verbe" | camille-po (story ecrite, prete), sofia-sm (affectation) | 2026-08-10 | affectation au planning s07 |
| Constat neuf : la 4e proposition d'un jeu a quatre choix passe sous le pli a 360x640 | nadia-prof, camille-po | 2026-08-10 | story a ecrire |
| Six reserves non bloquantes de `nadia-prof` sur "Le bon verbe" | nadia-prof, camille-po | 2026-08-10 | conversion en stories |
| Divergence de mesure de rendu entre trois agents sans qu'aucun n'ait menti, faute de methode reproductible sous 500 px avant `ADR-0025` | sofia-sm | 2026-08-07/10 | traite en retro (voir `retro.md`) |
| Ambiguite de la consigne "ne pousse rien, ne fusionne pas" pour un agent sans worktree dedie | sofia-sm | 2026-08-10 | traite en retro (voir `retro.md`) |

## Couverture programme

| Ligne de matrice | Avant s06 | Apres s06 |
|---|---|---|
| francais / CM1 / Orthographe et grammaire / Distinguer et utiliser les homophones grammaticaux | `en cours` | **`couvert-valide`** |
| francais / CM1 / Orthographe et grammaire / Conjuguer les verbes frequents au present, futur et imparfait | ouverte ce sprint, `en cours` | **`en cours`**, Done pedagogique refuse |
| francais / CP / Lecture et comprehension de l'ecrit / Associer les graphemes aux phonemes | `en cours` | **`en cours`**, "Le bon son" `VALIDE SOUS RESERVE`, barre ecrite par `nadia-prof` pour `couvert-valide` |

Synthese globale (`squad/programme/MATRICE-couverture.md`) : sur 46 lignes, **2 `couvert-valide`**
(contre 1 a l'ouverture du sprint), **2 `en cours`** (contre 3 a l'ouverture), **42 `non couvert`**
(inchange).

## Consommation de tokens

`python outils/rapport-tokens.py --sprint s06` a produit
`squad/sprints/s06-ouvrir-la-conjugaison-et-solder-la-dette/RAPPORT-tokens.md` et mis a jour
`squad/rapports/JOURNAL-tokens.md`. Fenetre bien bornee cette fois (debut et fin toutes deux
presentes), contrairement a s02 et s04.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 82 | 6 166 | 36 375 | 466 557 | 10 816 882 |
| `sofia-sm` | claude-sonnet-5 | 401 | 802 | 159 031 | 4 517 434 | 128 401 466 |
| `theo-archi` | claude-opus-5 | 289 | 3 120 | 146 688 | 2 070 551 | 57 166 066 |
| `marc-dev` | claude-sonnet-5 | 102 | 204 | 24 984 | 131 410 | 22 197 496 |
| `lea-dev` | claude-sonnet-5 | 378 | 13 364 | 145 313 | 1 659 569 | 84 038 948 |
| `yanis-qa` | claude-sonnet-5 | 175 | 23 639 | 42 596 | 280 134 | 14 078 491 |
| `relecteur-pedagogique` | claude-sonnet-5 | 158 | 4 834 | 32 528 | 1 066 174 | 22 478 138 |
| `nadia-prof` | claude-opus-5 | 266 | 44 522 | 132 142 | 698 990 | 29 078 647 |
| `ines-design` | claude-sonnet-5 | 252 | 504 | 111 746 | 779 430 | 61 925 526 |
| orchestrateur (fil principal) | claude-opus-5 | 87 | 161 | 266 003 | 776 653 | 20 340 892 |
| **Total** | | **2190** | **97 316** | **1 097 406** | **12 446 902** | **450 522 552** |

Les quatre compteurs restent separes (`squad/README.md`, section 6) : le cache lu depasse
couramment l'output de deux ordres de grandeur, les additionner donnerait un classement faux.

**Anomalie constatee, a ne pas taire.** Onze worktrees d'agents ont tourne ce sprint, et deux
agents (`relecteur-pedagogique`, `theo-archi`) ont repris leur tache apres une erreur
d'infrastructure. Verifie avant de rapporter ces chiffres tels quels :

- `relecteur-pedagogique` (158 tours, 22,5 M de cache lu en s06) est de meme ordre de grandeur que
  son propre volume en s05 (141 tours, 21,5 M) et ne porte **aucune** entree dans le bucket "hors
  sprint" du journal - sa reprise semble entierement captee dans la fenetre du sprint.
- `theo-archi` porte, dans le bucket **"hors sprint"** du journal cumule, le plus gros volume de
  tous les agents sur toute l'histoire du depot (1 427 tours, environ 200 M de cache lu, sur deux
  modeles). Cause la plus probable, verifiee et non supposee : `s02` et `s04` n'ont **aucune**
  fenetre temporelle connue (`planning.md` sans horodatage de debut, message explicite du script a
  chaque execution depuis leur cloture) - toute son activite architecturale de ces deux sprints
  tombe donc dans "hors sprint" par construction, un defaut deja documente et non nouveau ce
  sprint. Je ne peux **pas** exclure qu'une part de sa reprise du 2026-08-10 y soit egalement
  tombee si son horodatage de reprise a echappe a la fenetre `2026-08-07 13:35` - `2026-08-10
  08:35` UTC de ce sprint ; je ne l'ai pas trouve en cherchant, mais je ne peux pas non plus le
  prouver absent avec certitude sans acces aux transcriptions brutes au-dela de ce qu'un comptage
  agrege permet de voir. Je ne reaffecte aucun token entre fenetres : la regle du script
  ("jamais reparti arbitrairement") vaut aussi pour mes propres doutes.

Ce point est repris en retro plutot que traite ici par un amendement improvise.
