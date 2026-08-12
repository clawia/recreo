# [Sprint s08] Review - 2026-08-11

- **Horodatage de debut** : 2026-08-11 11:44 (repris de `planning.md`)
- **Horodatage de fin** : 2026-08-11 20:15
- **Duree effective** : 8 heures 31 minutes
- **Anime par** : sofia-sm
- **Commit de tete de `round-two`** : `3fca08a1f8cd9ee31484058e75898f0e34bf980e`

## Increment livre

L'ecran de manche montre desormais une machine reconnaissable - un cabinet a coins arrondis entoure
les colonnes, le decor de levier a quitte les boutons de choix de jeu de l'accueil, la fin du
mouvement des colonnes ne disparait plus d'un coup (S8-1 a S8-3) - et l'explication d'une variation
graphemique se distingue visuellement de la consigne. "Le bon verbe" rejoint le catalogue jouable,
son paquet s'etend au futur et a l'imparfait de l'indicatif (S8-4), et la ligne de matrice francais
CM1 conjugaison passe a `couvert-valide` - troisieme ligne sur 46, il n'en reste plus qu'une seule
`en cours`. Le chemin n'a pas ete direct : l'activation du catalogue a d'abord ete refusee
pedagogiquement, corrigee dans le meme sprint (S8-5), puis validee sous reserve au quatrieme audit
(section "Porte pedagogique" ci-dessous).

## Fusion du dernier lot (depuis le planning)

`round-two` etait a `bd9a8f0` a l'ouverture de cette cloture (planning, developpement, portes
technique et pedagogique deja fusionnes lors des ceremonies precedentes du sprint). Un seul lot
restait a fusionner :

| Lot | Branche | Tete | Perimetre verifie |
|---|---|---|---|
| relecteur-pedagogique | `s08-relecture` | `cae256d` | 9 fichiers, 12 insertions / 11 suppressions - coquilles de nommage, accents manquants sur des identifiants de contenu (couples d'homophones), un raccord Markdown dans `ADR-index.md`, un accent isole incoherent dans `FILE-ATTENTE.md`. Deux fichiers de test Java touches, un commentaire chacun. |

**Lignee verifiee avant fusion** (`git merge-base round-two s08-relecture` = tete exacte de
`round-two` avant fusion, un seul commit d'ecart, aucune derive). **Perimetre verifie par
`git diff --stat` et par lecture du diff complet**, pas sur la seule parole de l'agent : conforme a
l'annonce, aucun fichier hors perimetre. **Renommage de methode de test relu specifiquement**
(`leReplniEnDeuxRangeesEstScopeALaQuatriemePropositionSeulement` ->
`leRepliEnDeuxRangeesEstScopeALaQuatriemePropositionSeulement`, coquille signalee par `theo-archi`
en s07 et jamais corrigee) : le diff ne touche que le nom de la methode et sa citation dans
`proposition-ines-largeur-du-cabinet.md`, aucune assertion modifiee. Fusionne par
`git merge --no-ff`, **aucun conflit**.

**Worktree nettoye apres fusion** : `s08-relecture` etait le dernier worktree ouvert du sprint.
`git worktree list` ne rend plus que le depot principal ; la branche `s08-relecture` est supprimee.

## `mvn -B clean verify`

Rejoue independamment sur l'arbre fusionne final (`3fca08a`), JDK 21 designe par `JAVA_HOME` le
temps de la commande : **BUILD SUCCESS**, six modules en SUCCESS.

| Module | Tests |
|---|---|
| domain | 43 |
| application | 7 |
| adapter-web | 178 |
| adapter-content | 39 |
| bootstrap (surefire) | 77 |
| bootstrap (failsafe) | 3 |
| **Total** | **347** |

**28 `ArchRule` declarees, 28 executees**, inchange depuis l'ouverture du sprint : le lot de
`relecteur-pedagogique` ne touche ni code ni test de production, aucun chiffre ne bouge par rapport
a la mesure de `theo-archi` (`confirmation-archunit.md`, section 5.3) et de `yanis-qa`
(`rapport-qualite.md`, section 1), toutes deux reprises independamment ici plutot que recopiees.

## Rapport qualite - yanis-qa

- **Fichier** : `squad/sprints/s08-cabinet-de-la-machine/rapport-qualite.md`.
- **Commit audite** : `bd9a8f0` (avant le lot de relecture, qui ne touche aucun test ni code de
  production - le rapport reste valable sur l'arbre final).
- **Compte de tests confirme independamment** : 344 sur `bd9a8f0`, deux methodes qui se recoupent
  (bloc `Results:` du reacteur et somme des `tests=` XML). 28 regles ArchUnit confirmees
  independamment de `theo-archi`.
- **Quatre garde-fous cites comme fermant un refus ou une dette, mis a l'epreuve par mutation
  reelle puis restauration** : le canal de feuille de la condition C4 (`ADR-0031`, mutation 1
  rouge, mutation 2 - la coquille permanente - verte donc limite confirmee non supposee) ; la fin
  du mouvement des colonnes (S8-2, valeur fautive de s07 remise, rouge, restauree, verte) ; le
  plancher de police du repli a quatre colonnes (S8-5, trois mutations dont `calc()` refuse par sa
  forme et non seulement par son resultat) ; `AGENTS_SQUAD` (retrait d'`ines-design`, rouge,
  restaure, vert).
- **Ecart de comptage documente pour eviter qu'il ne soit re-decouvert a tort** : le resume `.txt`
  de Surefire sous-compte de 7 tests sur 4 classes (et non 2 comme initialement suppose), toujours
  a cause du meme motif - deux moteurs JUnit dans une classe, dont le `.txt` ne garde qu'un. Le
  bloc `Results:` du reacteur et le XML restent la seule source stable.
- **Position tranchee sur la dette C4** : **reduite, non fermee** - voir tableau des obstacles.
- **Avis chiffre sur le scan du lexique de jeu d'argent** : faisabilite technique confirmee sur le
  patron existant (`AucunVocabulaireDeMatiereTest`, `TexteAccentueDesGabaritsTest`), mais deux
  conditions non negociables si la clause s'ecrit un jour (perimetre couvrant les paquets de
  contenu et pas seulement les gabarits ; liste eprouvee sur les 61 paquets de `contenu-repris/` et
  non sur une fixture d'auteur). Decision non prise par `yanis-qa` : routee, voir tableau des
  obstacles.
- **Aucun test instable constate** sur quatre executions completes de la suite pendant la review.

## Confirmation ArchUnit - theo-archi

- **Fichier** : `squad/sprints/s08-cabinet-de-la-machine/confirmation-archunit.md` (amende le meme
  jour, section 5 faisant foi sur l'arbre final).
- **Arbre juge** : `bd9a8f0` plus son propre commit d'amendement (clause d'unicite sur la taille de
  proposition, cf S8-5). **347 tests, 0 echec, 28 regles ArchUnit** - reconcilie exactement avec le
  repere mesure independamment (344 + 3 tests neufs de la clause d'unicite = 347).
- **Clause ajoutee** : `TailleDesTextesEtBoutonsTest.lesSeulesTaillesDePropositionSontLesDeuxReglesNommees`,
  qui garantit qu'aucune troisieme regle de taille de proposition ne s'ajoute a la feuille en
  dehors des deux valeurs nommement arbitrees. **Trouvee mordante avant meme la review** : une
  mutation reelle (`.cabinet .proposition { font-size: 1.5rem; }`) laissait les 178 tests
  d'`adapter-web` verts avant l'ajout de cette clause. Aucun `ADR` associe : la clause ferme une
  exception deja tranchee par `nadia-prof` et `DEC-0018`, elle n'etablit aucune decision neuve.
- **Position sur la dette C4** (co-occurrence d'ecrans, `ADR-0031`) : **fermee** pour le canal de
  feuille qui reunirait deux ecrans exclusifs (morsure verifiee par deux mutations reelles).
  **Ouverts, nommes et mesures** : le verdict peut encore colorer la coquille permanente (verifie
  vert sur mutation reelle, limite confirmee) et peut encore passer par le gabarit via
  `th:classappend` (non testable aujourd'hui, faute de toute variable de modele portant un verdict
  sur l'ecran de manche).

## Relecture - relecteur-pedagogique

- **Fichier** : lot fusionne ci-dessus (`s08-relecture`, `cae256d`), pas de fichier `relecture.md`
  distinct ce sprint - les corrections sont portees directement dans les fichiers concernes.
- **Corrections appliquees (9 fichiers)** : "dette n. 1" -> "dette n°1" (typo deja corrigee en s07,
  reapparue dans `ADR-0031`, `planning.md` et un commentaire Java) ; renommage de la coquille
  `leReplniEnDeuxRangees...` -> `leRepliEnDeuxRangees...` (signalee en s07, jamais corrigee,
  renommage pur verifie sans impact sur les assertions) ; accents ajoutes sur des identifiants de
  couples d'homophones dans `backlog.md` et `MATRICE-couverture.md` (a/à, ou/où, la/là) ;
  ligne blanche manquante avant un intertitre dans `ADR-index.md` ; accent isole incoherent sur
  "leve" dans `FILE-ATTENTE.md`.
- **`mvn -B clean verify` annonce vert a 347 tests, 28 regles ArchUnit avant le commit** - confirme
  independamment ci-dessus.

## Porte pedagogique - nadia-prof

Trois verdicts prononces le 2026-08-11 sur l'increment fusionne, deux increments distincts audites
separement :

| Increment | Verdict | Ligne de matrice | Motif / reference |
|---|---|---|---|
| Chantier cabinet (S8-1, S8-2, S8-3) | **VALIDE SOUS RESERVE**. Done pedagogique accorde. Les deux reserves anterieures (fin du mouvement, distinction de l'explication) sont **levees**. | aucune - ce chantier ne porte aucune competence du programme | Quatorze points de garde audites sur les quatre jeux du catalogue, 203 fragments mesures, 24 parties reelles jouees. Deux reserves neuves et datees : le quatrieme cote du cabinet passe sous le pli sur deux jeux (cause partagee entre le defaut de pli preexistant de `DEC-0016` et un budget vertical repose sur le mauvais jeu de reference, faute reconnue par `nadia-prof` elle-meme) ; le contour du cabinet ne se distingue des colonnes qu'a un contraste de 1,71:1 (forme, non bloquant). `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, verdict du 2026-08-11. |
| Conjugaison etendue - troisieme audit (S8-4 + activation du catalogue) | **REFUSE** | reste `en cours` | Motif unique et chiffre : a 360 px reels, deux propositions du paquet (`mangeons`, `cherchons`) sont plus larges que leur colonne de 152,00 px sous le repli a quatre colonnes du cabinet. La barre en six points de S8-4 elle-meme est **levee** dans le meme verdict - le refus ne porte pas sur le contenu grammatical. `FICHE-bandit-manchot-conjugaison-present-cm1.md`, troisieme audit. |
| Conjugaison etendue - quatrieme audit, increment corrige (S8-5) | **VALIDE SOUS RESERVE**. Done pedagogique accorde. **Le REFUSE du troisieme audit est LEVE.** | passe de `en cours` a `couvert-valide` | Motif du refus ferme, mesure sur 880 propositions dans 220 etats et 22 parties reelles : plus aucune proposition ne sort de sa colonne. **VALIDE SOUS RESERVE et non VALIDE sec** : la reserve est le defaut de pli (24 items sur 25 en defaut avec indice affiche), que `nadia-prof` refuse elle-meme d'utiliser comme motif de refus - deja exclu par `DEC-0016` amende, `DEC-0017` et `DEC-0018`. `FICHE-bandit-manchot-conjugaison-present-cm1.md`, quatrieme audit. |

**Denouement complet du second refus, tel que `DEC-0018` l'a pose** : `nadia-prof` a refuse le Done
pedagogique de l'activation, l'utilisatrice a tranche de corriger dans le sprint plutot que de
retirer le jeu, `camille-po` a ecrit `DEC-0018` avec les deux issues possibles decidees a l'avance,
`ines-design` a chiffre une correction (police scopee au repli), `nadia-prof` a arbitre en sept
conditions avant tout developpement, `lea-dev` a code (S8-5), `nadia-prof` a rejoue l'increment et
leve son refus. La condition de `DEC-0018` pour faire passer la ligne de matrice ("au premier
verdict qui n'est pas un REFUSE") est remplie par le quatrieme audit ci-dessus.

**Homophones CM1 et mathematiques CP restent `couvert-valide`** (inchanges, aucune story de ce
sprint ne les touchait).

## Tableau des obstacles du sprint

| Description | Categorie | Destinataire | Statut |
|---|---|---|---|
| Ecart de lignee du planning : `nadia-prof` a ecrit son arbitrage prealable directement sur `round-two`, sans worktree ni branche dediee - constate et accuse par elle-meme dans sa propre fiche | gouvernance / methode | sofia-sm | **verifie conforme sur le contenu** (quatre fiches de `squad/conformite/` uniquement, rien d'autre), non corrige retroactivement. Troisieme occurrence de la meme faute en deux sprints d'affilee - traite en retro. |
| Dette C4 (co-occurrence d'ecrans, `ADR-0031`) : le canal de feuille reunissant deux ecrans exclusifs est ferme et mordant ; restent ouverts le canal de la coquille permanente et celui du gabarit via `th:classappend` | technique, dette de test | theo-archi | **reduite, non fermee**, confirmee par `yanis-qa` sur sa propre mutation (mutation 2, verte, limite reelle et non supposee). |
| Trois points gardes par `nadia-prof` seule, avec trois reponses distinctes de `yanis-qa` : la taille de police effective du repli (**gardable et deja gardee**, S8-5) ; l'air de chaque cote des glyphes dans la boite (**non gardable a cout raisonnable** - rendu de police reelle, meme famille qu'`ADR-0025` - et il ne reste que 2,86 px de marge, donc le defaut mordra des qu'une forme sera ajoutee au paquet) ; le rapport proposition/enonce (**gardable a faible cout**, deux valeurs deja lues ailleurs par les tests existants) | pedagogie / technique | sofia-sm (arbitrage de capacite) | **le rapport proposition/enonce est ouvert** au backlog des sprints suivants comme prochain geste le moins cher pour reduire ce qui reste sur la seule main de `nadia-prof` (voir `RAPPORT.md`). Les deux autres restent en l'etat, motifs ecrits. |
| Scan du lexique de jeu d'argent sur les textes et attributs de contenu (C6) : faisabilite technique confirmee, mais deux conditions non negociables (perimetre incluant les 61 paquets de `contenu-repris/`, liste eprouvee sur du materiau reel) en font un geste de l'ordre d'une revue de contenu complete, pas d'un simple ajout de test | technique / capacite | sofia-sm | **route a un sprint futur** au cadrage, avec la mise en garde de `theo-archi` a reprendre integralement. |
| Defaut documentaire de `DEC-0017` : le texte annonce "quatre chantiers" et ne numerote que les chantiers 1, 2 et 4 (aucun chantier 3) ; le chantier 1 annonce "quatre stories" et n'en liste que trois (S8-1, S8-2, S8-3) | redaction / produit | camille-po | **verifie par moi-meme : aucun livrable n'a ete perdu.** Le backlog du sprint 8 porte exactement S8-1 a S8-5, toutes livrees et jugees. C'est un defaut de numerotation dans `DEC-0017`, pas un perimetre manquant - inscrit ici avec cette verification pour que personne ne le re-instruise. |
| Les trois indices qui qualifient d'irregulier ce qui l'est au niveau du verbe mais pas de la terminaison a l'imparfait | pedagogie / redaction | nadia-prof (deja verse au backlog) | **confirme par le `relecteur-pedagogique`**, qui refuse de reformuler ce qu'un enfant lit sans decision pedagogique prealable - reste au backlog. |

Rappel du gabarit : un obstacle n'est `clos` que si le fichier qui le tranche existe physiquement.
Aucun des six obstacles ci-dessus n'a d'artefact `ADR`/`DEC` neuf a cette review ; ils restent
`ouvert` ou `route`, pas `clos`.

## Decisions prises

Aucun `ADR` ni aucune `DEC` n'est cree par cette cloture elle-meme. Les references citees ci-dessus
(`ADR-0031`, `DEC-0018`, l'amendement de `DEC-0016`) sont toutes anterieures a cette review, actees
pendant le sprint et deja fusionnees. Les decisions prises ici m'appartiennent en tant que Scrum
Master :

1. **Le lot de `relecteur-pedagogique` est fusionne apres verification de lignee et de perimetre**,
   avec relecture specifique du renommage de methode de test (aucune assertion touchee).
2. **Le worktree `s08-relecture` est le dernier nettoye du sprint** : `git worktree list` ne rend
   plus que le depot principal.
3. **Le rapport proposition/enonce (point le plus rentable des trois nommes par `yanis-qa`) est
   ouvert au backlog** ; les deux autres (air des glyphes, pli) restent geres a la main par
   `nadia-prof`, motifs deja ecrits et non rouverts.
4. **Le scan du lexique de jeu d'argent est route a un sprint futur**, faute de capacite ce sprint
   et au vu du cout reel chiffre par `theo-archi` et `yanis-qa` (ordre de grandeur d'une revue de
   contenu complete sur `contenu-repris/`).
5. **Le defaut de numerotation de `DEC-0017` est inscrit avec sa verification** plutot que
   re-instruit : aucune story perdue, defaut de forme uniquement, route a `camille-po`.
6. **L'ecart de lignee de `nadia-prof` est porte a la retro**, troisieme occurrence de la meme
   faute de gouvernance en deux sprints d'affilee.
