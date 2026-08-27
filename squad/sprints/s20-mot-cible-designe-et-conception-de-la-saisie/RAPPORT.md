# [Sprint s20] Rapport de cloture - mot cible designe et conception de la saisie

## L'increment livre, en une phrase

Le mot cible de "Nom ou verbe ?" est desormais declare dans le contenu et marque a l'ecran par un
contrat explicite plutot que devine des guillemets, une competence neuve du referentiel CM1
(reperer et corriger la majuscule et la ponctuation) entre au catalogue apres un refus pedagogique
corrige et leve dans le meme sprint, et la bande de viewport 576-719 px sort du perimetre produit -
la couverture passe de 13/43 (30,2 %) a 15/43 (34,9 %).

## Les decisions du sprint

| Numero | Titre | Statut |
|---|---|---|
| `ADR-0058` | La saisie normalise dans sa mecanique, declare son mode strict dans l'item, aucun contrat ne porte de comportement | Accepte pour ses clauses de conception ; amende le 2026-08-25 (`SAI-2c` : la chaine jugee et le mode qui l'a jugee voyagent sur le verdict, pas sur `derniereTentative`) |
| `ADR-0059` | Un plafond maison qui lie dans la bande est le mecanisme du correctif de largeur, sa valeur est un parametre | Accepte ; amende deux fois le 2026-08-25 (remesure de `L` a 647 px, deux affirmations chiffrees corrigees ; puis la dette verticale mesuree a 308,19 px sur 134 items, le declencheur devient une mesure et non une table) |
| `ADR-0062` | La part designee d'un enonce est declaree par l'item, jamais deduite du texte ; l'enonce est rendu en segments | Accepte, cree le 2026-08-25 (regle rouge a dessein) ; amende le 2026-08-27 a la cloture (`S20-1` fusionne, les deux clauses vertes sur `9ccbb1d`, 694 tests) |
| `DEC-0040` | La cible de deploiement est ordinateur et tablette, le telephone hors perimetre | Acceptee, amendee une troisieme fois le 2026-08-25 (s20) : la bande 576-719 px sort de la cible du produit, arbitrage verbatim de l'utilisatrice, reversant `DEC-0041` |
| `DEC-0041` | Perimetre du sprint 18 | Proposee, gelee par `sofia-sm` en s18 ; note de renvoi vers `DEC-0040` ajoutee le 2026-08-25 (s20), section d'origine non reecrite - son arbitrage sur la bande est reverse |
| `DEC-0043` | `SYN-16` : residu de largeur assume ; `S19-6` scindee en `S19-6a`/`S19-6b` | Acceptee ; note versee le 2026-08-25 (s20) : la scission en deux mecanismes est etablie fausse (meme ecran, meme cause, dix jeux) |
| `DEC-0045` | "Nom ou verbe ?" quitte le catalogue jouable faute d'audit ; regle generale sur l'exposition au catalogue | Acceptee (hors sprint, 2026-08-24) ; notes ajoutees le 2026-08-25 par `DEC-0048` et `DEC-0049` (s20) : le meme trou de gouvernance s'est reproduit sur `cm1-reperes` |
| `DEC-0047` | Perimetre du sprint 20 : `NAT-5` en trois gestes, `cm1-reperes` ouverte, `S19-6a`/`S19-6b` retirees | Acceptee, amendee trois fois le 2026-08-25 (arbitrages de l'utilisatrice sur la saisie ; avis de faisabilite et suspension de `S19-6a` ; sortie de la bande) |
| `DEC-0048` | `cp-nature-mot` retourne au catalogue (`NAT-5` `VALIDE`) ; `cm1-reperes` en sort faute d'audit | Acceptee (2026-08-25) |
| `DEC-0049` | `cm1-reperes` `REFUSE`, reste hors catalogue (pli, `REP-4` non tenue) | Acceptee (2026-08-25) ; note ajoutee le 2026-08-27 (s20, cloture) : le motif est leve sur reaudition, trace en `DEC-0050` |
| `DEC-0050` | `cm1-reperes` retourne au catalogue (`VALIDE SOUS RESERVE`) ; ligne matrice `couvert-valide`, 15/43 ; `REP-8` routee comme defaut de catalogue | Acceptee (2026-08-27, cloture) |

Aucune decision de ce sprint n'a ete refusee ni retiree. Le detail de chacune vit dans `review.md`
et dans les fichiers cites.

## Etat des deux Done

- **Technique** : vert. `bash outils/mvn-jdk21.sh -B clean verify` sur l'arbre de cloture
  (`round-two` a `1c9c313`, le 2026-08-27) : **BUILD SUCCESS, 694 tests, 0 echec** (49 `domain` +
  13 `application` + 330 `adapter-web` + 198 `adapter-content` + 98 `bootstrap` surefire + 6
  `bootstrap` failsafe), mesure par moi et concordante avec `yanis-qa` (`77dd467`) et `theo-archi`
  (`9ccbb1d`). **30 regles ArchUnit**, inchange par le sprint.

  **Note sur la poussee.** `origin/round-two` reste a `a7ca85b` ; `HEAD` la depasse de 22 commits.
  Comme etabli a la cloture de s19, ce Done technique est une mesure locale concordante sur trois
  executions independantes, pas un verdict de la CI sur cet arbre - `squad/README.md` section 7
  designe la CI comme seul juge. Je ne pousse rien : la poussee reste la main de l'utilisatrice.

- **Pedagogique** : deux verdicts, aucun refus ouvert a la cloture. `bandit-manchot-nature-mot-cp`
  `VALIDE` confirme sur l'artefact reellement servi. `bandit-manchot-reperes-cm1` `VALIDE SOUS
  RESERVE` apres un `REFUSE` corrige dans le meme sprint (quatre reserves non bloquantes ouvertes,
  aucune ne bloque la ligne de matrice).

## Le mouvement de couverture

**13/43 (30,2 %) en debut de sprint devient 15/43 (34,9 %) a la cloture**, deux lignes gagnees et
auditees dans le meme sprint : "Distinguer les principaux types de mots" (francais CP,
`bandit-manchot-nature-mot-cp`) et "Reperer et corriger la majuscule et la ponctuation dans une
phrase donnee" (francais CM1, `bandit-manchot-reperes-cm1`). Le denominateur atteignable (43/47)
n'a pas bouge ce sprint.

## Obstacles resolus et obstacles encore ouverts

Tableau complet (17 lignes, avec destinataire et etat) dans `review.md`. En resume : **13
obstacles sur 17 sont clos** (dont un, ligne 7, ferme pour ce planning mais dont la consequence
complete reste ouverte - compte donc aussi dans les quatre ci-dessous), dont un faux vert de
mesure ArchUnit instruit independamment par deux agents et une collision de labels desambiguisee a
cette review. **Quatre restent ouverts, en tout ou en partie** :

- Les 95 items sur 296 deja sous le pli a la reference du produit (dix jeux, dont deux
  `couvert-valide`) - route a `nadia-prof` puis `camille-po`, aucun statut de couverture prononce.
- La consequence complete de `SAI-2` (issue du clavier correcteur) sur la conception de la saisie -
  reste au chantier documentaire.
- Quatre reserves non bloquantes issues de la reaudition de `cm1-reperes` (`REP-7`, `REP-8`,
  `REP-9`, `REP-10`) - en backlog non affecte a un sprint.
- Le commentaire de `.mot-designe` dans `recreo.css` ("trois canaux non colores", en realite un
  seul mesure) - route a `marc-dev` et `ines-design`, suspendu a la reserve `NAT-12`.

## Ce que `nadia-prof` releve, et qui merite d'etre porte

Elle a verifie sa propre barre de refus plutot que de s'appuyer dessus, et l'a trouvee fausse pour
une de ses deux composantes : la regle "verdict et indice entierement au-dessus du pli" n'etait en
realite tenue par aucun autre jeu du catalogue sur l'indice (8 jeux sur 17 la contredisent). Elle
leve cette partie de son propre refus plutot que de deplacer le poteau, et en tire une story de
backlog de catalogue (`REP-8`) au lieu de la retenir contre le seul paquet qui l'a fait chercher.

## Consommation de tokens

Genere par `python outils/rapport-tokens.py --sprint s20` ; le detail complet vit dans
`RAPPORT-tokens.md` et dans `squad/rapports/JOURNAL-tokens.md`. Fenetre : 2026-08-25 15:00 a
2026-08-27 09:15 UTC (horodatages de `planning.md` et de `review.md`).

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 338 | 676 | 160 708 | 2 177 012 | 112 401 228 |
| `sofia-sm` | claude-sonnet-5 | 450 | 900 | 155 532 | 3 152 404 | 99 064 209 |
| `theo-archi` | claude-opus-5 | 144 | 288 | 75 558 | 1 876 882 | 19 014 605 |
| `marc-dev` | claude-sonnet-5 | 329 | 658 | 146 693 | 754 583 | 72 806 699 |
| `lea-dev` | claude-sonnet-5 | 332 | 664 | 203 994 | 2 760 726 | 89 346 905 |
| `yanis-qa` | claude-sonnet-5 | 109 | 218 | 36 481 | 298 065 | 10 031 897 |
| `relecteur-pedagogique` | claude-sonnet-5 | 137 | 274 | 40 569 | 579 554 | 22 086 709 |
| `nadia-prof` | claude-opus-5 | 372 | 744 | 227 411 | 3 371 070 | 68 479 442 |
| `ines-design` | claude-sonnet-5 | 392 | 784 | 172 802 | 6 378 907 | 147 975 583 |
| orchestrateur (fil principal) | claude-opus-5 | 174 | 348 | 245 503 | 7 998 022 | 65 590 622 |
| **Total** | | **2 777** | **5 554** | **1 465 251** | **29 347 225** | **706 797 899** |

Tous les agents apparaissent sur cette fenetre : aucune invocation absente a signaler ce sprint.
