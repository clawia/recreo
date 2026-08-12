# [Sprint s09] Rapport de sprint - quatre jeux neufs en mathematiques CP, et ce que 371 tests verts n'ont pas vu

- **Debut** : 2026-08-12 09:37 (`planning.md`)
- **Fin** : 2026-08-12 14:20 (`review.md`)
- **Duree effective** : 4 heures 43 minutes

## Increment livre

Le catalogue passe de quatre a huit jeux jouables : soustractions, complements a 10, comparaison
de deux nombres par les signes `<`/`>`/`=` et problemes additifs en situation rejoignent les
mathematiques CP, en pur contenu YAML sur la mecanique bandit manchot existante, sans aucune ligne
de rendu neuve ; trois des quatre ont ete refuses puis corriges par `nadia-prof` avant d'etre
valides, sur des fautes de francais et de sens qu'aucun des 371 tests de la CI ne pouvait detecter.

## Decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| ADR-0032 | Deux items d'un meme paquet ne presentent jamais le meme ecran | Architecture | Accepte, corrige le meme jour (compte exact des garde-fous) |
| DEC-0019 | Perimetre du sprint 9 : quatre lignes textuelles de mathematiques CP ouvertes, denombrement et monnaie differes sous le gel de rendu | Produit | Acceptee, amendee le 2026-08-12 (motifs des lignes 1 et 6 corriges sur avis de `theo-archi`) |
| DEC-0020 | S9-2 debloquee : `nombreDeManches` abaisse a 7 pour les complements a 10, plutot qu'un elargissement des formes d'items | Produit | Acceptee |
| DEC-0021 | La regularite de magnitude des additifs de "resoudre un probleme" est reportee hors sprint | Produit | Acceptee |

Aucun `ADR` ni `DEC` n'a ete cree par cette cloture elle-meme : les quatre references ci-dessus
sont toutes anterieures au fusionnage final, actees pendant le sprint.

## Etat des deux Done

| Done | Juge par | Etat | Reference |
|---|---|---|---|
| Technique | CI (`mvn verify`) | **Vert.** `mvn -B clean verify` sur `round-two` (`f8ba436`), rejoue independamment apres `clean` : BUILD SUCCESS, six modules, **371 tests**, **28 `ArchRule`** inchange (27 dans `bootstrap`, 1 dans `adapter-web`). Aucun plafond de dette approche. | `review.md`, sections "`mvn -B clean verify`" et "Rapport qualite" |
| Pedagogique | nadia-prof | **Quatre increments jugees, trois refusees puis corrigees et validees, une validee directe.** Complements a 10 : `VALIDE` direct. Soustractions et problemes additifs : `REFUSE` sur un motif borne (fautes de francais ; "en" partitif contredisant la reponse attendue) puis `VALIDE SOUS RESERVE` apres correction exacte de la barre ecrite par `nadia-prof`. Comparaison de nombres : `REFUSE` sur une faussete d'indice puis `VALIDE SOUS RESERVE` apres retrait du mot fautif, **mais la ligne de matrice reste `en cours`** - perimetre partiel pose avant tout audit, "ranger" differe par `DEC-0019`. | `squad/conformite/FICHE-bandit-manchot-{soustractions,complements-10,problemes-additifs,comparer-nombres}-cp.md` |

## Obstacles

**Resolus** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| S9-2 bloquee : `nombreDeManches: 10` exige onze items distincts, la forme canonique des complements a 10 n'en offre que onze (neuf hors cas degeneres) | theo-archi (chiffrage), camille-po (arbitrage) | `DEC-0020` : `nombreDeManches` abaisse a 7, formes d'items non elargies | 2026-08-12 |
| Trois refus pedagogiques (soustractions, problemes additifs, comparaison de nombres), chacun sur un motif borne | marc-dev, sofia-sm (casquette dev), lea-dev (correction), nadia-prof (arbitrage et second audit) | Correction bornee exacte a la formulation ecrite par `nadia-prof` dans chaque refus | 2026-08-12, second audit de chacune des trois fiches |
| Trois fiches de conformite affirmaient a tort qu'aucun test ne verifiait l'exactitude arithmetique | yanis-qa (mutation), nadia-prof (correction) | Les trois fiches corrigees apres lecture des fichiers de test, pas sur declaration | 2026-08-12 |
| Reserve sur la magnitude des additifs de "resoudre un probleme" (regularite bonne reponse/distracteurs) | camille-po | `DEC-0021` : reportee, story versee au backlog | 2026-08-12 |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` : la clause de production ne juge aucun indice reel (population de match epinglee a zero, clause de non-vacuite verifiant les paquets et non les indices examines) | theo-archi | 2026-08-12 | reponse ecrite de l'utilisatrice sur le calendrier de correction (avant s10 ou au backlog) |
| Test dedie par paquet quand une operation est verifiable par calcul : tranche par `yanis-qa` en recommandation, pas encore un critere ecrit du depot | theo-archi, sofia-sm | 2026-08-12 | decision d'en faire ou non une regle nommee |
| Trois pieges de comptage documentes en deux sprints, aucun outil autoritatif | theo-archi | 2026-08-12 | outil de comptage selon `ADR-0009` (fonction pure sur rapports XML + enveloppe non testee) |
| Atteignabilite de tous les items et conformite des propositions au fichier - propriete chiffree comme la moins chere a garder (une demi-journee, sans code de production) | theo-archi | 2026-08-12 | avis de faisabilite puis affectation |
| Citation devenue perimee dans `FICHE-bandit-manchot-comparer-nombres-cp.md` (l'ancien texte d'un indice, cite dans un verdict deja rendu et date) | nadia-prof | 2026-08-12 | decision d'ajouter ou non une note de coherence |
| Reserves de contenu des quatre fiches (magnitude, vocabulaire, stereotypes, dizaines egales rares, chiffres inverses absents, egalites triviales, une locution mal segmentee, difficulte constante dans une partie) | camille-po | 2026-08-12 | affectation a un sprint futur, capacite disponible |
| Anomalie de poste : fichier de toolchains Maven mal orthographie, jamais lu par Maven | (hors depot) | s07 | correction sur le poste de travail, hors perimetre du depot |

## Couverture programme

| Ligne de matrice | Avant s09 | Apres s09 |
|---|---|---|
| mathematiques CP, Nombres et calculs, Calcul mental - soustractions dans la limite de 10 | `non couvert` | **`couvert-valide`** |
| mathematiques CP, Nombres et calculs, Complements a 10 | `non couvert` | **`couvert-valide`** |
| mathematiques CP, Nombres et calculs, Resoudre des problemes simples d'addition/soustraction en situation (1 etape) | `non couvert` | **`couvert-valide`** (reserve `DEC-0021` ouverte) |
| mathematiques CP, Nombres et calculs, Comparer et ranger des nombres jusqu'a 100 (`<`, `>`, `=`) | `non couvert` | `en cours` - "comparer" valide, "ranger" differe |

Synthese globale (`squad/programme/MATRICE-couverture.md`, 46 lignes) : **6 `couvert-valide`**
(contre 3 a la cloture de s08), **2 `en cours`**, **38 `non couvert`**, chiffre inchange. Quatre
lignes ont bouge ce sprint ; aucune ligne `non couvert` supplementaire ne s'est ouverte.

## Consommation de tokens

`python outils/rapport-tokens.py --sprint s09`, lance apres l'ecriture de `review.md` et de
`retro.md` pour que la fenetre se ferme sur l'horodatage de fin de `review.md` (2026-08-12 12:20
UTC) plutot que sur "jusqu'a maintenant". A produit
`squad/sprints/s09-mathematiques-cp-nombres-et-calculs/RAPPORT-tokens.md` et mis a jour
`squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 308 | 39 812 | 120 186 | 1 327 667 | 29 962 070 |
| `sofia-sm` | claude-sonnet-5 | 619 | 8 734 | 165 889 | 2 094 166 | 38 545 366 |
| `marc-dev` | claude-sonnet-5 | 312 | 17 028 | 100 856 | 1 054 725 | 26 343 055 |
| `lea-dev` | claude-sonnet-5 | 235 | 6 734 | 71 470 | 597 039 | 21 260 720 |
| `yanis-qa` | claude-sonnet-5 | 202 | 11 552 | 57 121 | 555 378 | 27 731 573 |
| `relecteur-pedagogique` | claude-sonnet-5 | 91 | 182 | 24 075 | 501 865 | 9 300 158 |
| `nadia-prof` | claude-opus-5 | 305 | 610 | 133 988 | 800 709 | 19 409 766 |
| orchestrateur (fil principal) | claude-opus-5 | 108 | 206 | 293 756 | 313 408 | 38 715 877 |
| **Total** | | **2 180** | **84 858** | **967 341** | **7 244 957** | **211 268 585** |

**Aucune invocation mesuree sur cette fenetre pour `theo-archi` et `ines-design` - a lire, pas a
prendre pour un zero.** `ines-design` n'a effectue aucun geste ce sprint, conformement a la
consigne de l'utilisatrice (aucun travail de rendu) : l'absence est donc conforme au fait.
`theo-archi`, lui, a bien travaille ce sprint (`avis-faisabilite.md`, `ADR-0032`), mais ses deux
commits sont horodates 09:25 et 09:26, soit avant l'horodatage de debut de `planning.md` (09:37) :
son activite tombe donc dans la fenetre `hors sprint` du journal global
(`squad/rapports/JOURNAL-tokens.md`), ou elle est bien mesuree (2 148 tours cumules toutes fenetres
`hors sprint` confondues), plutot que dans le tableau ci-dessus. Ce n'est pas une invocation
manquante, c'est une invocation anterieure a l'ouverture formelle de la fenetre de ce sprint - le
meme mecanisme qui a deja ete documente comme fonctionnant correctement aux sprints precedents.

Les quatre compteurs restent separes (`squad/README.md` section 6) : le cache lu depasse
couramment l'output de deux ordres de grandeur, les additionner donnerait un classement faux.

Dix-huit worktrees et dix-huit branches ont ete ouverts ce sprint (huit agents, plusieurs ayant
ouvert plusieurs lots) ; tous verifies ancetres de `HEAD` puis nettoyes a cette cloture, aucune
anomalie de lignee constatee.
