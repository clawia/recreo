# [Sprint s03] Rapport de sprint - completer mathematiques CP

- **Debut** : 2026-08-05 19:05
- **Fin** : 2026-08-06 12:02
- **Duree effective** : 16h57

## Increment livre

La ligne mathematiques CP de `squad/programme/MATRICE-couverture.md` passe a `couvert-valide` -
**la premiere ligne de toute la matrice a atteindre ce statut**, apres avoir ete reportee deux
fois depuis s01.

En detail : le tirage des additions de `bandit-manchot-additions-cp` couvre desormais tout
l'intervalle 0-10 au lieu de se concentrer sur son bord superieur (les sommes 9 et 10 sont passees
de 43,9 % a 34,2 % des manches, mesure sur 2 000 manches reelles jouees par `nadia-prof`), l'indice
de S1-5 devient reellement atteignable en partie (verifie en jeu par une sequence ecrite a
l'avance) et vit dans le contenu YAML plutot que dans une constante du domaine, et les six
reserves visuelles et redactionnelles restantes sur ce jeu sont levees.

**Le fait le plus important de ce sprint ne porte cependant pas sur les neuf stories S3-1 a S3-9,
mais sur un chantier hors sprint qui s'est termine dans la meme fenetre : voir la section
dediee.**

## Chantier hors sprint - la premiere preuve reelle du decouplage mecanique/contenu

Ce fait appartient a la cloture de s04 (`squad/sprints/s04-ouvrir-le-francais/RAPPORT.md`), qui le
porte integralement. Je ne le duplique pas ici, je le cite parce qu'il a ete verifie
independamment par `theo-archi` **et** par `yanis-qa` sur l'arbre issu de ce sprint : `git diff`
sur `domain/` entre la fin du lot `marc-dev` de s03 (`9af191c`) et la fusion du lot francais de s04
(`4c62f60`) est vide - pas seulement `domain/mechanic`, le module entier. Deux jeux de francais
sont entres au catalogue par trois fichiers YAML et un test.

## Chantier hors sprint - Bootstrap (`ADR-0014`, `ADR-0015`)

Declenche hors planning par une demande directe de l'utilisatrice sur le rendu de l'application,
pendant que s03 et s04 etaient en cours de fusion. Il n'appartient a aucun des deux sprints
(aucune story S3-x ni S4-x ne le decrit), mais je le liste ici parce que `theo-archi` a lui-meme
date ses deux `ADR` sur s03 et que c'est dans le dossier de ce sprint que vit l'historique de
l'habillage transverse depuis s02 (`DEC-0006`). `nadia-prof` a **refuse** ce chantier sur un seul
point (bouton de sortie 30 % plus grand que le bouton d'action, regression tracee au commit
`6c83c97`) et a **leve son refus le meme jour**, sur mesure au pixel et non sur annonce (rapport
action/sortie remonte de 0,77 a 1,14). Detail complet : `squad/conformite/
FICHE-bandit-manchot-additions-cp.md`, points 2 et 2 bis.

## Decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| DEC-0004 | Textes, forme du score et seuils chiffres pour lever le refus pedagogique du sprint 1 | Produit | Acceptee (amendee le 2026-08-05 : cible pedagogique d'operandes nuls precisee a 12,9 %) |
| DEC-0007 | Le sprint 3 complete la ligne mathematiques CP existante et solde la dette de gouvernance | Produit | Acceptee (amendee deux fois : table de repartition recalculee le 2026-08-05 ; point 1 remplace par `DEC-0008` le 2026-08-06) |
| ADR-0014 | Faire entrer une bibliotheque CSS tierce par vendorisation tracee, et faire deriver un garde-fou pedagogique du rendu plutot que du fichier | Architecture | Accepte - **hors sprint** (voir section dediee), date `s03` par son auteur |
| ADR-0015 | Deriver le perimetre d'un garde-fou de la source qui gouverne le rendu, et supprimer celui dont le nom promet plus que son perimetre | Architecture | Accepte - **hors sprint** (voir section dediee), date `s03 (redige hors ceremonie)` par son auteur |

## Etat des deux Done

| Done | Juge par | Etat | Reference |
|---|---|---|---|
| Technique | CI (`mvn verify`) | **Vert.** Dernier rapport nomme (`yanis-qa`, `theo-archi`) sur l'arbre portant `ADR-0015` : 183 tests, 0 echec. Reverifie par moi a `HEAD` (`399fa5f`) : `mvn -B clean verify` sur JDK 21.0.10, **BUILD SUCCESS, 183 tests, 0 echec**, chiffre inchange par les trois derniers commits. Aucun rapport nomme de `yanis-qa`/`theo-archi` n'existe litteralement sur `HEAD` : ma reverification est une observation de passage, pas la porte technique au sens plein. | `review.md`, section "Rapport qualite" |
| Pedagogique | nadia-prof | **VALIDE.** Condition posee en s01 sur la repartition des sommes, reportee deux fois, levee et mesuree sur 2 000 manches reelles. | `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section 1 |

## Obstacles

**Resolus** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| S3-1 : filtre d'operandes nuls et table de cibles se contredisaient tels qu'ecrits | camille-po | Amendements du 2026-08-05 a `DEC-0004` et `DEC-0007` | `57b2183` |
| S3-5 : "ecart reel entre les boites" non mesurable sans moteur de rendu | camille-po | Critere reecrit sur la declaration `gap`, proxy assume | `docs/backlog/backlog.md`, S3-5 |
| `TexteAccentueDesGabaritsTest` omettait `fragments/erreur.html` | yanis-qa | Perimetre derive du repertoire `templates/`, jeu fautif sur objet neuf | `5ee8ca7` |
| Comptage divergent 22 / 21 sur `LesReglesMordentTest` | yanis-qa, theo-archi | Reconciliation ecrite : deux moteurs JUnit, deux mesures legitimes | `rapport-qualite.md` / `confirmation-archunit.md` |
| Concordance des tags d'agent a quatre emplacements, pas trois | yanis-qa | `ConcordanceDesTagsDAgentTest`, perimetre derive du script de CI | `32b8be8` |
| Quatre stories a risque de garde-fou a perimetre enumere | yanis-qa, ines-design | Verifie a la review : aucune violation constatee | `review.md` |
| Angle mort `!important` sur les variables CSS Bootstrap | theo-archi | `ADR-0015` : recherche exigeant un debut de declaration | `3486356` |
| `.sortie-pleine` invisible au garde-fou de taille (classe rendue par `th:class`) | theo-archi | `ADR-0015` : perimetre derive de chaque etat rendu | `43bf2ca` |
| `HabillageDesEcransTest` (nom promettant plus que son perimetre) | theo-archi | `ADR-0015` : methode supprimee, propriete portee par `RessourcesTiercesTest` | `43bf2ca` |
| Bootstrap hors sprint : bouton de sortie 30 % plus grand que le bouton d'action | nadia-prof, ines-design | Refus puis correction puis levee, mesuree au pixel | `a810fc7` |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| S3-8 : reste de la story (absence de depassement/chevauchement reel a 375 px) non verifiable sans navigateur sans tete | camille-po (perimetre), theo-archi / yanis-qa (outillage) | s02 | une decision d'outillage, non urgente selon `theo-archi` |
| `HabillageDesEcransTest` reste sans jeu fautif, dans `DETTE_DE_BALAYAGE` (plafond a 4, ne baisse pas) | theo-archi | 2026-08-06 | un jeu fautif et un perimetre derive, nommes par `theo-archi` lui-meme plutot que de les laisser croire solde |
| Aucune regle ne compare deux selecteurs entre eux sur un meme ecran (saillance relative) - troisieme defaut visible traversant une CI verte | theo-archi | 2026-08-06 | un mecanisme de comparaison de rapports de surfaces sur un rendu, dont `theo-archi` ne sait pas encore s'il est faisable ; jusqu'a son existence, `nadia-prof` reste le seul garde-fou, en jouant |
| Trois worktrees provisionnes sur `main` au lieu de `round-two` | sofia-sm | 2026-08-05 | une verification en amont, avant le premier commit d'un worktree, pas seulement au moment de la fusion (amendement propose en `retro.md`) |
| Trois agents ont valide sur JDK 25 en croyant qu'aucun JDK 21 n'existait (fichier de toolchains mal nomme, hors depot) | sofia-sm | 2026-08-05 | qu'un rapport de qualite cite systematiquement la version obtenue, pas seulement "vert" (amendement propose en `retro.md`) |
| S5-5 : un paquet `type: question` ne peut porter d'indice | theo-archi | 2026-08-06 | une decision de modelisation ; deja route dans `docs/backlog/backlog.md` |
| S5-1 second critere : la reprise de session ne revalide pas l'identifiant contre le catalogue | camille-po | 2026-08-06 | volontairement laisse ouvert par `nadia-prof` jusqu'a l'existence d'`adapter-persistence` |

## Couverture programme

| Ligne de matrice | Avant | Apres |
|---|---|---|
| mathematiques / CP / Nombres et calculs / Calcul mental - additions dans la limite de 10 (puis 20) | `en cours` | **`couvert-valide`** - premiere ligne du produit a ce statut |

## Consommation de tokens

`python outils/rapport-tokens.py --sprint s03` a produit
`squad/sprints/s03-completer-mathematiques-cp/RAPPORT-tokens.md`, repris ci-dessous, et mis a jour
`squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 173 | 346 | 105 605 | 1 417 072 | 16 488 817 |
| `sofia-sm` | claude-sonnet-5 | 266 | 27 492 | 73 046 | 983 433 | 17 748 662 |
| `theo-archi` | claude-opus-5 | 386 | 29 464 | 173 943 | 1 102 007 | 47 652 179 |
| `marc-dev` | claude-sonnet-5 | 152 | 18 778 | 43 247 | 272 227 | 15 316 668 |
| `lea-dev` | claude-sonnet-5 | 506 | 79 639 | 111 945 | 1 126 719 | 61 235 151 |
| `yanis-qa` | claude-sonnet-5 | 368 | 30 580 | 114 282 | 1 084 696 | 46 730 910 |
| `relecteur-pedagogique` | claude-sonnet-5 | 103 | 206 | 12 742 | 793 838 | 13 886 019 |
| `nadia-prof` | claude-opus-5 | 200 | 9 898 | 81 635 | 999 101 | 20 655 748 |
| `ines-design` | claude-sonnet-5 | 483 | 5 563 | 140 068 | 1 461 491 | 67 541 365 |
| orchestrateur (fil principal) | claude-opus-5 | 71 | 2 580 | 215 973 | 1 155 788 | 12 160 107 |
| **Total** | | **2708** | **204 546** | **1 072 486** | **10 396 372** | **319 415 626** |

**Une precision de mesure, pas une erreur** : la fenetre de ce tableau (2026-08-05 19:05 a
2026-08-06 12:02) est celle de s03, mais `outils/rapport-tokens.py` decoupe par horodatage, pas par
sujet. s04 n'ayant aucune fenetre propre (aucun `planning.md`), et le chantier Bootstrap hors
sprint s'etant deroule dans ce meme intervalle, **ces chiffres incluent mecaniquement une partie
du travail de s04 et du chantier hors sprint**, sans qu'aucune ligne ne le distingue. Ce n'est pas
corrige ici : le corriger supposerait de retailler la fenetre par une hypothese non ecrite plutot
que par un horodatage reel.
