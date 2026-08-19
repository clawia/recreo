# [Sprint s16] Rapport de sprint - deux defauts de l'utilisatrice et dettes de l'en-tete

- **Debut** : 2026-08-19 10:11 +0200 (`planning.md`)
- **Fin** : 2026-08-19 15:20 +0200 (`review.md`)

## Increment livre

**Le message de partie interrompue ne s'affiche plus que pour une interruption reelle, le retour
au catalogue complet affiche toujours la bonne adresse, la puce de niveau active et la proposition
deja essayee gardent leur apparence sous le pointeur et au clavier, et la phrase de filtre survit
desormais a un changement de niveau confirme en cours de partie.** Sept stories, avis de
faisabilite ecrit avant le premier commit (ordre normal, par opposition au mode par arbitrages
successifs de s15) : sept criteres sur vingt-et-un ont ete refuses ou reecrits, un renforce
gratuitement, sur des stories qui n'avaient encore aucune ligne de code.

## Decisions du sprint

Chaque `ADR` et chaque `DEC` cree ou modifie dans la fenetre du sprint, verifie sur
`squad/decisions/adr/ADR-index.md` et `squad/decisions/produit/DEC-index.md`, pas recopie d'une
liste anterieure.

| Reference | Titre | Statut ce sprint |
|---|---|---|
| DEC-0036 | Le message de partie interrompue est borne a une interruption plausible, jamais a un cookie qui a survecu a l'expiration de sa session | **Creee**, Acceptee |
| DEC-0037 | Perimetre du sprint 16 : fermeture des dettes de l'en-tete et des deux defauts trouves par l'utilisatrice, zero ouverture de contenu | **Creee**, Proposee (perimetre gele au planning par `sofia-sm`, comme le prevoit son propre statut) ; **note du 2026-08-19** : chiffre de `R-BARRE-1` corrige a +14 px au lieu de +22 (arbitrage inchange) ; **seconde note du 2026-08-19** : couplage entre `S15-8` et la composition du catalogue nomme, declencheur pose, aucune story ouverte |
| DEC-0004 | Textes, forme du score et seuils chiffres pour lever le refus pedagogique du sprint 1 | Acceptee en s01 ; **amendee (note)** le 2026-08-19 : le declencheur du message de reprise est desormais precise par `DEC-0036`, le texte reste inchange |
| ADR-0047 | Deriver la duree de vie du marqueur de partie de la duree d'inactivite de la session, lue et jamais recopiee | **Creee** `Propose` (avis de faisabilite), **Accepte** le meme jour dans le commit du lot `S16-1` qui porte sa regle |
| ADR-0048 | Faire gouverner l'adresse affichee par une en-tete de reponse, jamais par le declencheur | **Creee** `Propose`, **Accepte** le meme jour dans le commit du lot `S16-2` qui porte sa regle |
| ADR-0046 | Un etat interactif tiers oblige la feuille maison a le declarer | Accepte en s15 ; **amendee deux fois** le 2026-08-19 : (1) sa derivation ne voyait pas un selecteur compose, correction par le lot `S16-3`/`S16-4` ; (2) son exclusion de `:focus-visible` etait trop grossiere (un etat exclu la ou il fallait exclure une seule propriete de cet etat), corrigee contre son propre avis de faisabilite |
| `CLAUDE.md` | Reference perimee vers une section d'`ADR-0045` qui n'existe pas | **Corrigee** par `theo-archi`, ecart trouve par `camille-po`, corrige par celui qui l'avait ecrite |

**Six references touchees ce sprint** (deux `DEC` creees, une `DEC` amendee par note, deux `ADR`
creees et acceptees le jour meme, une `ADR` amendee deux fois, une correction de `CLAUDE.md`).
Aucune section vide.

**Hors `ADR`/`DEC` mais du meme ordre de gouvernance** : la table de correspondance des conditions
et reserves pedagogiques de `nadia-prof` (`squad/conformite/FICHE-barre-entree-niveau.md`, section
4) est ecrite pour la premiere fois ce sprint, avec ses trois regles de lecture. Elle ne renomme
rien et ne casse donc aucune des references portees par les onze fichiers de code qui citent ces
identifiants (`theo-archi`).

## Etat des deux Done

| Done | Juge par | Etat |
|---|---|---|
| Technique | CI (`mvn -B clean verify`) | **Vert, rejoue independamment par moi sur l'arbre fusionne final, `round-two` a `750ef9d`.** BUILD SUCCESS, six modules, **545 tests** (0 echec, deux methodes de comptage concordantes : somme des blocs `Results:` et somme des attributs `tests="..."` des 97 rapports XML), **29 regles ArchUnit** (deux methodes concordantes : 28 champs `@ArchTest` contre 31 declarations de champ `ArchRule` moins 2 dans le faux module de contre-exemple), garde-fou de chemins en code de sortie 0 (script et auto-test). **Aucune confirmation ArchUnit dediee de `theo-archi` n'existe pour s16** : je le nomme plutot que de la fabriquer ou de recycler celle de s15. Le rapport qualite de `yanis-qa` existe et confirme mes deux chiffres. |
| Pedagogique | nadia-prof | **Quatre constats.** `S16-1` (message de partie interrompue) : **VALIDE**. `S16-2` (adresse affichee) : **VALIDE**. `S16-6` (phrase de filtre apres changement de niveau) : **VALIDE**, reserve `S15-D` **FERMEE**. `S16-3`+`S16-4` (etat sous le pointeur et au clavier) : **VALIDE SOUS RESERVE**, conditions `S15-8`/`S15-9` tenues aux quatre positions y compris au focus clavier, **reserve neuve S16-A** (aucun indicateur de focus clavier visible sur les sept pressables d'une manche). `S16-5` et `S16-7` n'appelaient aucun constat par construction. |

## Obstacles

**Resolus ce sprint** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| Rapport qualite de s15 perime, sa duty de mutation-morsure sur `ADR-0046` jamais executee | sofia-sm (decide), yanis-qa (execute) | Rapport declare perime, duty reportee sur le lot `S16-3`/`S16-4` et executee | 2026-08-19 |
| Relecture de s15 encore due | relecteur-pedagogique | `relecture-du-retard-de-s15.md`, zero correction directe, quatre points signales | 2026-08-19 |
| Chiffre `R-BARRE-1` errone (+22 px au lieu de +14) dans trois documents de `sofia-sm` et un de `camille-po` | sofia-sm, camille-po | Notes datees dans chaque document, pas de reecriture silencieuse | 2026-08-19 |
| Commentaire perime d'`accueil.html`, affirmation contredite d'`avis-faisabilite.md`, `ADR-index.md` incomplet | theo-archi | Trois corrections dans le meme commit | 2026-08-19 |
| Contradiction entre `ADR-0046` et l'avis de faisabilite de s16 sur `:focus-visible` | theo-archi | Tranchee contre lui-meme : l'exclusion etait trop grossiere, corrigee | 2026-08-19 |
| `S16-3` tenue explicitement partiellement ouverte (regle d'epinglage a reformuler) | lea-dev | Regle reformulee, jamais supprimee | 2026-08-19 |
| Nomenclature des conditions pedagogiques (quatre conventions non reliees), signalee par le relecteur | nadia-prof | Table de correspondance unique ecrite, avec trois regles de lecture | 2026-08-19 |

**Ouverts a l'entree de s17** :

| Description | Agent route | Ce qui manque pour clore |
|---|---|---|
| `ADR-0042` : `theo-archi` s'etait engage par ecrit a changer son statut si le declencheur n'avait pas joue a la cloture de s16. Il n'a pas joue ; le fichier porte toujours `Accepte` a cette review | theo-archi | Ecrire le changement de statut engage - obstacle non tranche par `sofia-sm`, qui n'a pas mandat sur l'architecture |
| `dureeInactiviteMaximale()` protegee seulement par la divergence fortuite de deux fixtures (1800 vs 900), jamais par une propriete de la regle | theo-archi (conception), camille-po (priorite) | Un test dedie qui varie explicitement la duree, ou une decision explicite de ne pas le faire |
| Garde-fou de provenance `S16-7` : verifie un emplacement, jamais une fraicheur (mutation adversariale, `target/` perime accepte comme a jour) | theo-archi | Choix d'architecture sur l'opportunite de comparer contenu ou horodatage du fichier compile a sa source |
| Migration du marqueur `ecran-de-jeu` vers un attribut, motif renforce | theo-archi (mecanisme), sofia-sm (capacite) | Candidate de backlog pour s17, jamais devant une story qui corrige ce qu'un enfant voit |
| `DEC-0032` point 2 (nomenclature de matiere accentuee), sans reponse ecrite depuis le 2026-08-17 | theo-archi | Reponse ecrite, prealable au filtre par matiere |
| Aucune proposition de forme pour un second axe de filtre combine | ines-design | Proposition, puis arbitrage prealable de `nadia-prof` |
| Dependance a une seule personne pour toute mesure de rendu | sofia-sm (capacite), camille-po | Aucune decision de capacite prise ce sprint, inchange |
| Couplage `S15-8` / composition du catalogue ("Le bon verbe" seul a servir quatre positions) | camille-po | Ferme par une note ecrite (declencheur pose pour le jour d'un retrait de contenu), pas par un traitement immediat |
| R-BARRE-4 / reserve F11 (ecoute reelle au lecteur d'ecran) | hors de la portee de la squad | Inchange depuis s13 |
| Javadoc du trait de M9 formulee comme une mesure figee, alors qu'elle est reverifiee a chaque execution | sofia-sm | Reformulation cosmetique, non bloquante, candidate a un futur lot |

## Couverture programme

**Troisieme sprint consecutif sans mouvement de `squad/programme/MATRICE-couverture.md`** : 23
lignes `couvert-valide`, 5 `en cours`, 46 `non couvert`, sur 74 lignes de competence au total,
inchange depuis s13. Aucune des sept stories de ce sprint ne cible une competence, decision
explicite de `camille-po` dans `DEC-0037`. **Limite posee pour s17, reprise ici telle quelle** :
si s17 ne rouvre aucune ligne de la matrice, ce sera a `sofia-sm` et `camille-po` de l'ecrire comme
un changement de direction du produit, pas comme une continuation silencieuse.

## Consommation de tokens

Genere par `python outils/rapport-tokens.py --sprint s16`, fenetre 2026-08-19 08:11 a 2026-08-19
13:20 UTC (10:11 a 15:20 +0200) - voir `RAPPORT-tokens.md` pour le detail par tour et
`squad/rapports/JOURNAL-tokens.md` pour la serie complete.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 75 | 150 | 25 887 | 232 783 | 5 546 401 |
| `sofia-sm` | claude-sonnet-5 | 537 | 1 074 | 247 629 | 3 501 719 | 110 084 499 |
| `theo-archi` | claude-opus-5 | 34 | 68 | 28 753 | 1 434 883 | 22 480 511 |
| `marc-dev` | claude-sonnet-5 | 133 | 266 | 54 437 | 440 595 | 16 794 285 |
| `lea-dev` | claude-sonnet-5 | 396 | 792 | 221 503 | 1 834 904 | 129 566 766 |
| `yanis-qa` | claude-sonnet-5 | 312 | 624 | 90 650 | 834 858 | 47 596 359 |
| `relecteur-pedagogique` | claude-sonnet-5 | 219 | 438 | 71 806 | 646 084 | 38 627 529 |
| `nadia-prof` | claude-opus-5 | 115 | 230 | 76 036 | 2 517 510 | 67 975 671 |
| orchestrateur (fil principal) | claude-opus-5 | 61 | 122 | 170 287 | 177 372 | 33 461 752 |
| **Total** | | **1 882** | **3 764** | **986 988** | **11 620 708** | **472 133 773** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`. Les quatre compteurs restent
separes (voir `squad/README.md`, section 6) : les additionner donnerait un classement faux, le
cache lu depassant couramment l'output de deux ordres de grandeur.
