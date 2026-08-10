# [Sprint s07] Rapport de sprint - une vraie machine a sous, pas un questionnaire empile

- **Debut** : 2026-08-10 14:05 (`planning.md`)
- **Fin** : 2026-08-10 17:27 (`review.md`)
- **Duree effective** : 3 heures 22 minutes

## Increment livre

La mecanique "bandit manchot" rend desormais une vraie machine a sous - colonnes qui defilent puis
s'arretent chacune sur une proposition, un seul geste qui reutilise le bouton d'ouverture de manche
existant - a la place du questionnaire a boutons empiles constate par l'utilisatrice, fermant la
dette n°1 de `CLAUDE.md`. Dans le meme sprint, les cinq indices fautifs de "Le bon verbe" qui
enseignaient une regle de conjugaison fausse sont corriges et son refus pedagogique du 2026-08-10
est leve. Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge : le chantier mecanique
ne porte aucune competence programme.

## Decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| DEC-0015 | Le sprint 7 construit la vraie machine a sous et ferme la dette n°1 de CLAUDE.md | Produit | Acceptee, **amendee quatre fois** le 2026-08-10 (contradiction S7-2 / reintegration de "Le bon verbe" routee a `theo-archi` ; arbitrage prealable de `nadia-prof`, reduction a trois propositions acquise puis **eteinte** par elle-meme ; rendu en clair de la reprise silencieuse laisse en l'etat face a `ADR-0027` ; repli de mise en page confirme sur maquette mais non livre) |
| DEC-0016 | Une hauteur de viewport de reference (360 x 640), et ce qui doit rester au-dessus du pli sur un ecran de manche | Produit | Acceptee |
| ADR-0026 | Une animation retarde ce que l'enfant voit, elle ne determine rien ; le levier n'est pas un bouton de plus | Architecture | Accepte, corrige deux fois le meme jour (chiffres de rendu relus a mi-hauteur plutot que dans les angles arrondis ; sa section 2 corrigee par `ADR-0027`) |
| ADR-0027 | Faire declarer par le serveur comment un ecran de jeu est arrive, et n'autoriser un mouvement que sur un geste de l'enfant | Architecture | Accepte, **amende le meme jour** par un commit direct de `theo-archi` (clause 8 sur la decoupe geometrique `clip-path`, clause 6 etendue aux retards ecrits hors du raccourci `animation`) |
| ADR-0008 | Le comportement de jeu vit sur le serveur (liste de vocabulaire interdit au client) | Architecture | Accepte, **amende** le 2026-08-10 : `proposition` et `colonne` rejoignent la liste (`ADR-0026` regle 2) |

Aucun `ADR` ni `DEC` n'a ete cree par cette cloture elle-meme : les cinq references ci-dessus sont
toutes anterieures au fusionnage final, actees pendant le sprint.

## Etat des deux Done

| Done | Juge par | Etat | Reference |
|---|---|---|---|
| Technique | CI (`mvn verify`) | **Vert.** `mvn -B clean verify` sur `round-two` (`b612378`), rejoue deux fois independamment : BUILD SUCCESS, six modules, **315 tests**, **28 `ArchRule`** inchange (27 executees par `@ArchTest`, 1 par `.check()` explicite). Aucun plafond de dette approche. | `review.md`, sections "Rapport qualite" et "Confirmation ArchUnit" |
| Pedagogique | nadia-prof | **Deux verdicts distincts, tous deux `VALIDE SOUS RESERVE`.** Chantier machine a sous (S7-1 a S7-4) : quatre conditions non negociables tenues, une reserve chiffree (fin du mouvement discontinue, 13 px visibles a la derniere image). "Le bon verbe" : **le REFUSE du 2026-08-10 est LEVE**, douze indices verifies vrais en jeu, deux reserves neuves (`mangeons` a 2,0 px de marge, seconde rangee sous le pli avec l'indice sur cinq items sur douze). Homophones CM1 reste `couvert-valide`, inchange. | `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, `FICHE-bandit-manchot-conjugaison-present-cm1.md` |

## Obstacles

**Resolus** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| Reserve chiffree d'`ines-design` sur son pire cas choisi au nombre de lettres plutot qu'a la largeur de glyphes | ines-design | Remesuree par `nadia-prof` sur le code livre : `mangeons` tient a 2,0 px, pas 3 | `10dd74d` (verdict de `nadia-prof`) |
| Correction des cinq indices fautifs de "Le bon verbe" | marc-dev, sofia-sm (affectation) | Livree, Done pedagogique accorde, `REFUSE` du 2026-08-10 leve | `6913827` |
| Le rapport qualite de `yanis-qa` etait retrouve non fusionne au moment de la cloture, sans avoir ete annonce comme lot restant | sofia-sm | Verifie (perimetre disjoint, un seul fichier) et fusionne dans le meme geste que les deux derniers lots | `b6123787` |
| `theo-archi` a commite directement sur `round-two`, sans worktree dedie, en infraction a la regle actee en retro s06 | theo-archi (regle), sofia-sm (verification) | Verifie apres coup : 227 insertions et 0 suppression sur le test de `lea-dev`, perimetre exactement conforme a l'annonce, aucun chevauchement. Rien a defaire. | `942280669` (verification), porte a la retro |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| Mecanisme de garde de `DEC-0016` (viewport 360x640, "au-dessus du pli") | theo-archi | 2026-08-10 | avis de faisabilite chiffre (donne, section 7 de `confirmation-archunit.md`), pas encore de story ni d'`ADR` |
| Decalage inter-colonnes cumulatif de 80 ms : une cinquieme colonne le porterait a 314 ms, au-dela de la borne de 250 ms de `nadia-prof`, sans qu'aucun test ne le voie | theo-archi | 2026-08-10 | arbitrage de capacite au planning suivant, aucun paquet du catalogue n'a cinq colonnes aujourd'hui |
| Fin du mouvement discontinue (reserve unique de `nadia-prof`, 13 px visibles a la derniere image) | camille-po (story), ines-design (forme, sous veto de `nadia-prof`) | 2026-08-10 | conversion en story |
| Les deux conditions de remise au catalogue de "Le bon verbe" (`DEC-0015`, quatrieme amendement) sont remplies, mais `catalogue.yaml` n'a pas ete modifie | camille-po | trouve a cette cloture | decision de remise au catalogue, qui lui appartient |
| Defaut de pli de "Le bon petit mot" (5 items sur 24, pas 3 - corrige par `nadia-prof` a son propre desavantage) confirme persistant sur le rendu livre par `yanis-qa` | camille-po | 2026-08-10 (s06), reconfirme s07 | Done et ligne `couvert-valide` maintenus par `nadia-prof` ; reste une intention non detaillee, gouvernee par `DEC-0016` |
| `camille-po` sans donnees mesurees dans la fenetre du sprint (son travail de decision precede l'horodatage de debut du planning) | sofia-sm | cette cloture | constat methodologique, a porter en retro |
| `AGENTS_SQUAD` de `outils/rapport-tokens.py` liste huit agents et omet `ines-design` (l'equipe en compte neuf) | sofia-sm (dev, auteure du script) | cette cloture | correctif dans un sprint futur |

## Couverture programme

| Ligne de matrice | Avant s07 | Apres s07 |
|---|---|---|
| francais / CM1 / Orthographe et grammaire / Conjuguer les verbes frequents au present, futur et imparfait | `en cours`, Done pedagogique **refuse** | **`en cours`**, inchangee - Done pedagogique **accorde sous reserve**, `REFUSE` leve. La barre pour `couvert-valide` reste celle ecrite par `nadia-prof` en s06 (ouvrir le futur et l'imparfait), pas ses reserves. |
| francais / CM1 / Orthographe et grammaire / Distinguer et utiliser les homophones grammaticaux | `couvert-valide` | **`couvert-valide`**, inchangee. Aucune story de ce sprint ne la touchait. |

Synthese globale (`squad/programme/MATRICE-couverture.md`, 46 lignes) : **2 `couvert-valide`**, **2
`en cours`**, **42 `non couvert`** - identique a la cloture de s06. Ce sprint ne fait bouger aucune
ligne : il reconstruit une mecanique deja jouee, il n'ouvre aucun contenu nouveau (`DEC-0015`).

## Consommation de tokens

`python outils/rapport-tokens.py --sprint s07`, lance apres l'ecriture de `review.md` pour que la
fenetre se ferme sur son horodatage de fin (`squad/README.md` section 6) plutot que sur "jusqu'a
maintenant". A produit `squad/sprints/s07-machine-a-sous/RAPPORT-tokens.md` et mis a jour
`squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `sofia-sm` | claude-sonnet-5 | 198 | 40 008 | 48 723 | 2 548 755 | 41 815 319 |
| `theo-archi` | claude-opus-5 | 139 | 22 214 | 69 696 | 399 410 | 17 321 635 |
| `marc-dev` | claude-sonnet-5 | 229 | 38 068 | 58 731 | 747 034 | 32 814 903 |
| `lea-dev` | claude-sonnet-5 | 270 | 540 | 96 607 | 835 209 | 72 167 691 |
| `yanis-qa` | claude-sonnet-5 | 196 | 53 731 | 62 249 | 681 213 | 36 688 613 |
| `relecteur-pedagogique` | claude-sonnet-5 | 168 | 336 | 34 353 | 641 567 | 27 874 856 |
| `nadia-prof` | claude-opus-5 | 180 | 360 | 82 979 | 740 117 | 21 585 090 |
| orchestrateur (fil principal) | claude-opus-5 | 48 | 87 | 145 080 | 136 685 | 24 348 487 |
| **Total** | | **1 428** | **155 344** | **598 418** | **6 729 990** | **274 616 594** |

Les quatre compteurs restent separes (`squad/README.md` section 6) : le cache lu depasse
couramment l'output de deux ordres de grandeur, les additionner donnerait un classement faux.

**Anomalie constatee, a ne pas taire - deux causes distinctes, verifiees et non rapportees telles
quelles.**

- **`camille-po` n'apparait pas avec une donnee dans ce tableau, alors qu'elle a livre `DEC-0015`
  (quatre amendements) et `DEC-0016` ce sprint.** Toutes ses sessions mesurees le 2026-08-10 se
  terminent avant 12:05 UTC, avant l'horodatage de debut de ce planning (14:05 local). Son travail
  de decision a ete fait **avant l'ouverture formelle du planning** - c'est l'etat dans lequel ce
  sprint m'a ete confie. La fenetre du script, bornee par `planning.md`, exclut mecaniquement ce
  travail et le verse dans la ligne cumulative `hors sprint` de `JOURNAL-tokens.md`. Ce n'est pas un
  defaut du script : c'est la sequence reelle de ce sprint sur un outil qui mesure par fenetre de
  ceremonies.
- **`ines-design` n'apparait meme pas comme "aucune invocation mesuree" : elle est absente du
  tableau, pour une seconde raison, distincte et plus nette.** `outils/rapport-tokens.py`, constante
  `AGENTS_SQUAD`, liste **huit** agents et omet `ines-design`, alors que l'equipe en compte neuf
  (`squad/README.md` section 1). Un agent absent de cette constante ne recoit jamais la mention
  explicite d'absence : il disparait silencieusement. C'est un vrai defaut du script, distinct de la
  premiere cause, non corrige dans ce sprint (hors mandat de cette cloture) et route pour un sprint
  futur.

Huit worktrees ont tourne ce sprint et quatre interruptions d'infrastructure avec reprise d'agent
ont ete signalees en amont ; hors les deux causes ci-dessus, aucune autre anomalie trouvee - les
tours par agent restent dans l'ordre de grandeur des sprints precedents.

Ces deux points sont repris en retro plutot que traites ici par un amendement improvise.
