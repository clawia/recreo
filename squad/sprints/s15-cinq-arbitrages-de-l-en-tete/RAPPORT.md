# [Sprint s15] Rapport de sprint - cinq arbitrages successifs referment l'en-tete de recreo

- **Debut** : 2026-08-17 20:33:26 +0200 (`planning.md`, premier commit du sprint, `7bf9692`)
- **Fin** : 2026-08-18 17:20 +0200 (`review.md`)

## Increment livre

**L'en-tete de recreo tient un seul rang au-dessus de 470 px (deux rangs en dessous, accepte
d'avance), porte "Les jeux" en permanence y compris pendant une manche en cours, et la puce de
niveau pressee reste pleine jusqu'au demarrage du jeu ou jusqu'a un retour au catalogue complet,
sur les trois routes qui y ramenent.** Ce sprint n'a suivi aucun de ses propres rites : il a ete
pilote de bout en bout par cinq arbitrages successifs de l'utilisatrice, rendus en jouant
l'application livree, sans objectif gele ni avis de faisabilite avant le premier commit - voir
`planning.md` et `review.md` pour le detail et pour ce que ce mode a coute.

## Decisions du sprint

Chaque `ADR` et chaque `DEC` cree ou modifie dans la fenetre du sprint (2026-08-17 20:33:26 a
2026-08-18 17:20), verifie sur `squad/decisions/adr/ADR-index.md` et
`squad/decisions/produit/DEC-index.md`, pas recopie d'une liste anterieure.

| Reference | Titre | Statut ce sprint |
|---|---|---|
| DEC-0033 | BARRE-7 : le lien "Les jeux" disparait de l'accueil | **Remplacee** par `DEC-0034` (heritee de s14, close ce sprint) |
| DEC-0034 | L'utilisatrice retablit "Les jeux" dans l'en-tete et unifie l'apparence des puces de niveau | **Creee** (arbitrages A et B) ; **amendee (note)** le 2026-08-18 : l'hypothese de masquage inchange sur les ecrans de jeu est depassee par l'arbitrage C |
| DEC-0027 | Le retrait du bouton de sortie de la manche reouvre S6-9 / `ADR-0022` | Acceptee en s11 ; **amendee une troisieme fois** le 2026-08-18 (note renvoyant a l'arbitrage C de `DEC-0035`) |
| DEC-0035 | Trois arbitrages de s15 (sortie permanente, rang unique, couleur d'etat actif) n'avaient aucune trace ecrite | **Creee**, trace retroactivement les arbitrages C, D, E ; signale sans les combler l'absence de citation verbatim de l'utilisatrice et l'absence de story de backlog pour ces trois arbitrages |
| ADR-0022 | Un habillage permanent declare son masquage, l'ecran son marqueur | Acceptee en s06 ; **reforme verifiee par mutation** le 2026-08-17 (zero fichier de garde-fou bouge sur le retour de "Les jeux") ; **note** le 2026-08-18 (premisse renversee par l'utilisatrice, cinquieme bascule de population de masquage, cout zero clause) |
| ADR-0044 | Le choix des jeux montres vit derriere le port, le point d'entree est une route | Acceptee en s14 ; **amendee** le 2026-08-18 : la premiere phrase du point 3 tombe, sa branche conditionnelle (rendu complet par appui) devient l'etat de l'application |
| ADR-0046 | Un etat interactif tiers oblige la feuille maison a le declarer | **Creee**, ferme le defaut de survol qui effacait tous les boutons du depot ; renverse la garantie de specificite du point 5 d'`ADR-0014` |
| ADR-0014 | Vendoriser une bibliotheque CSS tierce et deriver les garde-fous du rendu | Acceptee en s03 ; **amendee une troisieme fois** (meme commit qu'`ADR-0046`) : le point 5 devient une condition partielle parmi trois |

**Huit fichiers de decision touches ce sprint** (deux `DEC` creees, une `DEC` remplacee, une `DEC`
amendee, une `ADR` creee, trois `ADR` amendees). Aucune section vide.

## Etat des deux Done

| Done | Juge par | Etat |
|---|---|---|
| Technique | CI (`mvn -B clean verify`) | **Vert, rejoue independamment par moi sur l'arbre fusionne final, `round-two` a `74e6e20`.** BUILD SUCCESS, six modules, **528 tests** (0 echec, deux methodes de comptage concordantes : somme des blocs `Results:` et somme des attributs `tests="..."` des 83 rapports XML), **29 regles ArchUnit** (deux methodes concordantes : 28 champs `@ArchTest` + 1 regle verifiee par `.check()` explicite dans `ScanDeComposantsTest`, contre 29 declarations de champ `ArchRule` comptees directement), garde-fou de chemins en code de sortie 0 (script et auto-test). **Aucun rapport qualite (`yanis-qa`) ni confirmation ArchUnit motivee (`theo-archi`) n'existent pour ce sprint** : c'est ma propre reverification, ci-dessus, qui atteste ce Done, voir `review.md`. |
| Pedagogique | nadia-prof | **Quatre verdicts distincts, aucun ne remplacant les autres.** Cloture (2026-08-18) : **VALIDE SOUS RESERVE** sur le correctif de regression (couleur eteinte au pixel sur les trois routes concernees) et sur l'arbitrage de `.proposition.deja-essayee` (variante neutre retenue, conditions S15-8/S15-9 posees), reserve S15-D neuve. Second lot (2026-08-18) : **VALIDE** sur le rang unique et l'etat actif, sept conditions S15-1 a S15-7 tenues au chiffre. Premier lot (2026-08-18) : **VALIDE SOUS RESERVE** sur S15-1, S15-2 et l'arbitrage C. Herite de s14, inchange : **VALIDE SOUS RESERVE**. |

## Obstacles

**Resolus** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| Le sixieme lot (`726e0c4`, Done VALIDE de `nadia-prof` sur le rang unique et l'etat actif) etait reste fusionne nulle part | sofia-sm | Fusionne (`6b3641e`), sans conflit, verifie contenu present et non ecrasant | 2026-08-18 |
| Trois arbitrages (C, D, E) sans aucun fichier `DEC` | camille-po | `DEC-0035` cree, amendements dates de `DEC-0027` et `DEC-0034` | 2026-08-18 |
| Aucun Done pedagogique sur le correctif de regression | nadia-prof | VALIDE SOUS RESERVE, cinq etats mesures au pixel sur le rendu | 2026-08-18 |
| Trou du selecteur compose (`.proposition.deja-essayee` sans etat interactif) - arbitrage de forme | nadia-prof, ines-design, theo-archi | Variante 1 (neutre) retenue par `nadia-prof` ; deux conditions S15-8/S15-9 posees pour l'implementation | 2026-08-18 |
| Noms de marqueurs `ecran-exclusif` et `ecran-porte-sa-sortie` a confirmer | ines-design | Les deux noms confirmes tels que proposes par `theo-archi` | 2026-08-18 |
| Defaut de survol effacant tous les boutons du depot | theo-archi | `ADR-0046`, garde-fou neuf | 2026-08-17 |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| Aucune story de backlog pour les arbitrages C, D, E | camille-po | 2026-08-18 (nomme par elle-meme dans `DEC-0035`) | stories retroactives, hors mandat de la regularisation de cloture |
| Trou du selecteur compose - implementation de la forme neutre | theo-archi | 2026-08-18 | derivation corrigee, S15-8 et S15-9 tenues sur le code livre |
| Aucun rapport qualite, aucune confirmation ArchUnit motivee, aucune relecture pour ce sprint | yanis-qa, theo-archi, relecteur-pedagogique | constate a la review | rapport qualite avec mutation de garde-fou recent, confirmation ArchUnit motivee, relecture complete des livrables textuels du sprint |
| Reserve S15-C : "Les jeux" reste visuellement identique a une puce inactive | camille-po (story candidate) | 2026-08-18, reduite d'un tiers | story candidate ou reserve durable |
| Reserve S15-D : aucune phrase n'annonce le filtre sur `/niveaux/{niveau}/confirmer` | camille-po (story candidate) | 2026-08-18, neuve | story candidate |
| R-BARRE-1 (grands nombres CM1) : +22 px depuis s14 | camille-po | 2026-08-18, chiffre definitif | story candidate |
| Dependance a une seule personne pour toute mesure de rendu | sofia-sm (capacite), camille-po | nomme quatre fois par `nadia-prof` en deux jours ce sprint | outillage de mesure de rendu qui ne pilote aucun navigateur reel, jamais de survol produit |
| R-BARRE-4 (F11, lecteur d'ecran) | hors de la portee de la squad | s13, aggrave ce sprint | ecoute reelle, hors squad |
| Question de nomenclature de la matiere accentuee | camille-po, nadia-prof | s14 | decision avant tout filtre par matiere |
| Garde-fou de provenance des classes amont (`-am`) | theo-archi | s14 | mecanisme, non affecte |
| Migration du marqueur `ecran-de-jeu` vers un attribut | theo-archi, sofia-sm | s06 | capacite, non affectee |
| Resolution de navigateur d'`outils/mesurer-rendu.sh` | sofia-sm | s14 | capacite, non affectee |
| Predicat `estRemplacant` d'`ADR-0042` toujours sans objet en code | theo-archi | s13 | declencheur non atteint ce sprint |
| Reserve F11 (ecoute reelle au lecteur d'ecran) | hors de la portee de la squad | s13 | ecoute reelle, hors squad |

## Correction du 2026-08-19 (trouvee par le relecteur-pedagogique en s16)

La ligne "R-BARRE-1 (grands nombres CM1) : +22 px depuis s14" dans le tableau des obstacles ouverts
ci-dessus **inverse le sens du mouvement**. Lu dans
`squad/conformite/FICHE-barre-entree-niveau.md`, section "S15-7" : avant s14 y = 397, s14 y = 410
(+13 px), lot 3 (`b109f98`) y = 419 (**+22 px** - c'est le lot 3 qui degrade), lot 4 (rang unique et
etat actif) y = 411 (**+14 px** - le lot 4 **recupere 8 px**). Le chiffre final de s15 est **+14 px**,
non +22. `DEC-0037` de s16 reprend le chiffre faux de ce document ; routee a `camille-po` - voir
`squad/sprints/s16-deux-defauts-et-dettes-de-len-tete/planning.md`.

## Couverture programme

**Aucune ligne de `squad/programme/MATRICE-couverture.md` n'est ouverte ni fermee par ce sprint, et
aucun contenu pedagogique neuf n'a ete livre.** Les cinq arbitrages portent exclusivement sur la
navigation et l'apparence de l'en-tete ; ce n'est pas une omission, c'est le perimetre reel du
sprint.

## Consommation de tokens

```
python outils/rapport-tokens.py --sprint s15
```

produit `squad/sprints/s15-cinq-arbitrages-de-l-en-tete/RAPPORT-tokens.md` et met a jour
`squad/rapports/JOURNAL-tokens.md`. Fenetre mesuree : 2026-08-17 18:33 a 2026-08-18 15:20 UTC
(2026-08-17 20:33 a 2026-08-18 17:20, heure locale +0200).

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 97 | 194 | 41 828 | 396 569 | 10 857 010 |
| `sofia-sm` | claude-sonnet-5 | 703 | 21 346 | 218 735 | 2 298 603 | 57 736 316 |
| `theo-archi` | claude-opus-5 | 160 | 320 | 147 058 | 5 184 978 | 49 482 013 |
| `marc-dev` | claude-sonnet-5 | 668 | 1 336 | 320 783 | 6 504 724 | 303 272 286 |
| `nadia-prof` | claude-opus-5 | 162 | 324 | 85 048 | 2 961 758 | 74 257 996 |
| `ines-design` | claude-sonnet-5 | 266 | 532 | 135 028 | 779 493 | 28 806 585 |
| orchestrateur (fil principal) | claude-opus-5 | 134 | 257 | 244 439 | 1 643 536 | 48 906 340 |
| **Total** | | **2190** | **24 309** | **1 192 919** | **19 769 661** | **573 318 546** |

**Aucune invocation mesuree sur cette fenetre pour** : `lea-dev`, `yanis-qa`,
`relecteur-pedagogique` - coherent avec l'absence de leurs rapports de cloture habituels, nommee
plus haut.

Detail complet, y compris la fenetre "hors sprint", dans `RAPPORT-tokens.md` et
`squad/rapports/JOURNAL-tokens.md`.
