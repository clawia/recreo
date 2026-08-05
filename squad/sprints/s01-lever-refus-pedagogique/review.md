# [Sprint s01] Review - 2026-08-05

- **Horodatage de debut** : 2026-08-05 13:03 (`planning.md`)
- **Horodatage de fin** : 2026-08-05 15:45
- **Duree effective** : 2h42
- **Anime par** : sofia-sm

## Objectif du sprint, tel que gele au planning

> Lever le refus pedagogique prononce par `nadia-prof` sur `bandit-manchot-additions-cp` en
> corrigeant ses trois motifs de refus (S1-1, S1-2, S1-3 du backlog), pour que l'increment
> repasse devant `nadia-prof` en fin de sprint.

Objectif atteint : l'increment est repasse devant `nadia-prof`, qui a rendu son verdict le
2026-08-05 (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`) - **VALIDE SOUS RESERVE,
Done pedagogique ACCORDE**. Les trois motifs geles sont leves. Le verdict pedagogique fait foi ;
cette review le reprend, elle ne l'adoucit pas et ne l'interprete pas.

## Story par story, ce qui est livre et ce qui ne l'est pas

| Story | Livre | Ce qui reste |
|---|---|---|
| S1-1 - texte sans faute (motif de refus 1) | **LEVE.** Verifie par `nadia-prof` sur le HTML reellement servi : retour faux, fin de partie, bouton de sortie, message de session perdue et consigne, tous integralement accentues. | Reserve mineure, non bloquante : le titre `<h1>Recreo</h1>` n'est pas accentue (question de marque, pas de contenu pedagogique, a trancher hors de la main de `nadia-prof`). |
| S1-2 - score lisible au CP (motif de refus 2) | **LEVE.** `Score : N`, aucun symbole operatoire, aucune barre oblique, verifie en jeu et par `PartieControllerTest`. | Rien. |
| S1-3 - ecran d'erreur franchissable (motif de refus 3) | **LEVE.** HTTP 200, `text/html;charset=UTF-8`, "Oups ! Le jeu a un souci." et "Recommencer" verifies en jeu sur les deux chemins d'echec reproduits en s00. | Reserve non bloquante : "Recommencer" est un lien HTML sans regle CSS (texte souligne a la taille par defaut), pas un bouton visuel - route a s02 (reserve 7 de la fiche). |
| S1-4 - partie avec fin et sortie (reserve 1) | **LEVEE.** 10 manches exactement, ecran de fin atteint, bouton de sortie present sur les trois ecrans (manche, retour, fin), reprise silencieuse en session valide, message exact en session perdue. | Le libelle du bouton de sortie a change deux fois pendant cette review (voir section dediee ci-dessous) : "Retour à l'accueil" -> "Les jeux", a la demande de `nadia-prof`. |
| S1-5 - aide plutot qu'enfermement (reserve 3) | **PARTIELLEMENT LEVEE.** La "manche plus simple" (reprise d'un item deja reussi apres trois echecs consecutifs) fonctionne et est verifiee en jeu. | **L'indice, lui, n'est jamais atteint dans une partie reelle de 10 manches** (il faudrait la 14e manche au plus tot) : constat de fond de `nadia-prof`, route a `camille-po` ci-dessous, non traite dans ce sprint. Son texte est en revanche corrige ("Compte sur tes doigts.", voir ci-dessous) en attendant que le seuil de declenchement soit revu. |
| S1-6 - signal juste/faux independant de la couleur (reserve 4) | **LEVEE.** Jugee par lecture et par rasterisation du `clip-path` par `nadia-prof` : coche et croix reconnaissables sans couleur. Aucun test automatise (non bloquant, remonte par `yanis-qa`). | Ecrire un test de regression sur la CSS - route a s02, non bloquant. |
| S1-7 - additions qui font calculer (reserve 2) | **LEVEE** au sens du critere chiffre : 12,9 % de manches a operande nul sur 171 manches reelles, dans la fourchette de `DEC-0004` (5 a 15 %). | **Constat de fond nouveau de `nadia-prof` qui n'etait pas le critere de cette story** : le tirage n'est pas uniforme sur les sommes, il favorise fortement le haut de l'intervalle (voir section dediee). Route a `camille-po` et `theo-archi`. |
| S1-8 - vocabulaire de CP (reserve 6) | **LEVEE sur le fond**, avec deux libelles repris pendant cette review (fin de partie refusee, bouton de sortie reformule sous reserve) - voir sections dediees. Les trois autres libelles suggeres par `nadia-prof` en s00 et repris a l'identique par le developpeur ("Encore !", "Non, c'est X.", "Il n'y a pas de jeu pour le moment.", "Calcul CP") sont **acceptes tels quels**. | Test manquant sur le critere "ecran de catalogue vide" (non bloquant, remonte par `yanis-qa`, confirme satisfait a la main par `nadia-prof`). |
| S1-9 - taille du calcul et du signal (reserve 5) | **LEVEE** sur les deux criteres ecrits par `camille-po` (`.enonce` a 2 rem, `.icone-signal` a 3 rem), confirmes par `nadia-prof`. | Reserve non bloquante et hors du perimetre du critere ecrit : `.score`, `.indice`, `.fin-de-partie`, `.erreur`, `.pas-de-jeu`, `.message-reprise` et les boutons n'ont aucune regle de taille (16 px et taille par defaut du navigateur, soit le plus petit texte de l'ecran). Route a s02. |

## Rapport qualite - yanis-qa

`squad/sprints/s01-lever-refus-pedagogique/rapport-qualite.md`.

- **Commande et resultat sur l'arbre qu'elle a audite** (`82070bd`) : `mvn -B clean verify`, vert,
  **120 tests, 0 echec** (31 `domain`, 7 `application`, 26 `adapter-web`, 5 `adapter-content`,
  49 + 2 `bootstrap`). Aucun module sans test : la dette de `main` ("des modules entiers sans
  aucun test") n'est pas reproduite.
- **Preuve d'execution du decouplage** : `BanditManchotTest` fait jouer la meme mecanique sur un
  contenu combinatoire de mathematiques CP et un contenu enumere d'anglais CM1, sans sous-classe ni
  configuration.
- **Trois criteres sans test automatise, qualifies non bloquants par elle-meme** : S1-6 (forme et
  couleur du signal), S1-9 (tailles de police), S1-8 (ecran de catalogue vide). Les trois ont ete
  juges satisfaits a la main par `nadia-prof` (voir la fiche, section 4 et section 3).
- **Assertions fragiles** : aucune assertion HTML par expression reguliere en production ; aucun
  ordre de tirage non graine ; les deux nouvelles regles ArchUnit d'existence gardent
  `allowEmptyShould(false)`.

## Confirmation ArchUnit - theo-archi

`squad/sprints/s01-lever-refus-pedagogique/avis-architecture-cloture.md` et
`confirmation-archunit.md`.

- **Verdict** : **Porte architecture OUVERTE.**
- **23 regles ArchUnit**, contre 21 a la cloture de s00 (les deux nouvelles sont celles
  d'`ADR-0011`), toutes executees, toutes avec un contre-exemple qui les fait echouer. Aucune regle
  supprimee ni affaiblie.
- **ADR-0011** est complet des sa creation : deux mutations du code de production (retirer
  `@ControllerAdvice`, faire retourner un corps brut) ont ete appliquees puis annulees pour prouver
  que les regles refusent le vrai contournement, pas seulement un contre-exemple ecrit pour etre
  refuse.
- **ADR-0012 avait deux manques, corriges par `theo-archi` pendant ce sprint** : son contre-exemple
  etait range dans le mauvais paquet (invisible pour la regle qu'il devait faire echouer, corrige
  et mis a l'epreuve par mutation) et la derniere garde de comportement de son tableau restait sur
  le papier (`CatalogueChargeableIT` n'assertait rien sur `nombreDeManches` ; `isPositive()`
  ajoute). `ADR-0012` et `ADR-index.md` portent l'amendement.
- **Reserve non bloquante, non close** : un echec transitoire de `LesReglesMordentTest` (2 echecs
  sur 21), observe une fois, non reproduit sur huit tentatives ulterieures, rapport Surefire
  fautif deja ecrase avant d'avoir pu etre lu. `theo-archi` nomme une hypothese (ecriture
  concurrente dans le meme arbre par plusieurs agents pendant la fusion des trois worktrees) sans
  la faire passer pour une conclusion, et renvoie l'arbitrage a la CI (checkout neuf, sans
  ecrivain concurrent). **Cette review ne le clot pas** : aucun fichier ne le tranche, il reste
  ouvert, sous surveillance.

**Reverification apres les trois corrections de texte de cette review** (voir sections
dediees ci-dessous, casquette dev de `sofia-sm`, commit `caa1049`) : `mvn -B clean verify` relance
sur JDK 21.0.10, **BUILD SUCCESS, 120 tests, 0 echec**, meme repartition par module que le rapport
de `yanis-qa` et de `theo-archi`. Le Done technique tient sur l'etat final reellement revu par
cette ceremonie, pas seulement sur l'etat au moment du rapport qualite.

## Relecture - relecteur-pedagogique

`squad/sprints/s01-lever-refus-pedagogique/relecture.md`.

- **Correction appliquee** : `CLAUDE.md` ne portait que l'exemption de categorie B de `DEC-0002`,
  pas l'exigence positive (texte enfant en francais correct et integralement accentue) que
  `DEC-0002` demandait explicitement d'y reporter. Corrige (commit `[REL]`).
- **Cas nomme, pas corrige** : l'indice d'`AdditionsBorneesContentSource` est conforme
  typographiquement, mais vit dans une constante Java plutot que dans le YAML - obstacle deja
  route par `camille-po` via `DEC-0005`, confirme sans etre retranche.
- **Six libelles remontes a `camille-po` et `nadia-prof`** comme non fixes par une decision
  ecrite ("Retour à l'accueil", "Encore !", le libelle de fin de partie, "Non, c'est X.", "Il n'y a
  pas de jeu pour le moment.", "Calcul CP") : tous statues dans la fiche de conformite (section 3),
  voir tableau des obstacles.

## Les trois textes corriges pendant cette review - casquette dev de sofia-sm

`nadia-prof` a rendu trois verdicts sur des libelles introduits pendant s01 sans DEC dediee
(fiche, section 3). Sofia ne tranche pas a sa place : elle applique la reformulation ecrite,
casquette developpeuse, commit `caa1049`.

| Libelle | Verdict de nadia-prof | Applique |
|---|---|---|
| "La partie est finie. Bravo, tu as bien joué !" | **REFUSE** - felicite un enfant a 0/10, use le seul mot de renforcement du jeu, contredit visuellement le signal rouge au-dessus | "C'est fini !" |
| "Retour à l'accueil" | ACCEPTE SOUS RESERVE - grapheme "ueil" non lu au CP, mot abstrait | "Les jeux" |
| "Compte avec tes doigts pour t'aider." (indice) | ACCEPTE SOUS RESERVE - fond juste, forme trop longue pour le lecteur le plus fragile (6 mots, mots irreguliers) | "Compte sur tes doigts." |

Tests adaptes dans le meme commit (`PartieControllerTest`, `TexteAccentueDesGabaritsTest`) :
`mvn -B verify` vert avant de commiter. Le deplacement de l'indice vers le YAML (`DEC-0005`)
n'est pas fait ici : c'est plus qu'un remplacement de texte (cablage d'un nouveau champ de
parametre, fabrique, schema), explicitement route au backlog de s02 par `camille-po`, non force
dans ce commit.

## Les deux constats de fond de nadia-prof, routes et non tranches

### 1. La repartition des sommes est biaisee vers le haut - route a camille-po et theo-archi

Sur 171 manches reellement jouees, 43,9 % portent sur une somme de 9 ou 10 et 7,6 % seulement sur
une somme inferieure ou egale a 4 (detail par somme dans la fiche, section 5). Le tirage
uniforme du premier operande puis du second dans ce qui reste favorise mecaniquement les grandes
sommes. `nadia-prof` : "le jeu ne travaille pas les additions dans la limite de 10, il travaille
surtout leur bord superieur", et elle en fait **la condition** pour que
`squad/programme/MATRICE-couverture.md` passe cette ligne de `en cours` a `couvert-valide`.

Je ne tranche ni la cible de repartition ni l'algorithme : je route.

- **`camille-po`** : quelle repartition cible (uniforme sur les 66 couples, ou progression du bas
  vers le haut de l'intervalle comme le suggere `nadia-prof`) - c'est un jugement produit et
  pedagogique sur ce que "travailler la competence" doit vouloir dire, pas une question technique.
- **`theo-archi`** : si la reponse de `camille-po` demande de faire depasser au tirage la simple
  correction de proportion deja actee par `DEC-0004` (par exemple une notion de progression ou de
  poids par somme), verifier si cela reste a l'interieur du contrat de contenu actuel
  (`ADR-0006`) ou si cela appelle un nouvel `ADR`.

### 2. L'indice de S1-5 est inatteignable en jeu reel - route a camille-po

`nadia-prof` a verifie, sequence la plus favorable a l'appui : le troisieme echec sur un meme
item ne peut pas arriver avant la 11e manche, l'indice ne peut donc pas s'afficher avant la 14e -
et une partie dure 10 manches. Sur trois parties jouees selon cette sequence, le compteur n'a
jamais depasse deux. Le comportement est correctement implemente (`yanis-qa` le confirme par ses
tests) ; c'est le reglage produit qui rend l'aide morte pour l'enfant qui rate tout des la
premiere manche - celui pour qui S1-5 a ete ecrite.

Je ne tranche pas la correction. `nadia-prof` propose deux pistes sans les arbitrer (abaisser le
seuil d'echecs sur le meme item, ou declencher l'indice sur des echecs consecutifs plutot que sur
la repetition d'un item) : c'est `camille-po` qui choisit, parce que cela touche au comportement
du jeu que `DEC-0004` avait deja specifie.

## Tableau des obstacles du sprint

| Description | Categorie | Agent route | Decision / artefact | Statut |
|---|---|---|---|---|
| Trois motifs de refus de s00 (texte non accentue, score illisible, ecran d'erreur infranchissable) | pedagogie | nadia-prof | Verdict "LEVE" pour les trois, `FICHE-bandit-manchot-additions-cp.md` | clos |
| Cinq points produit routes par `theo-archi` en planning (forme du score, libelles d'erreur, nombre de manches, seuils de S1-5, seuil chiffre de S1-7) | produit | camille-po | `DEC-0004` | clos |
| Indice de S1-5 code en dur dans le domaine plutot que porte par le YAML | produit puis technique | camille-po | `DEC-0005`, correction technique non bloquante portee au backlog de s02 | clos comme obstacle de routage ; correction technique reste ouverte a s02 |
| `ADR-0012` incomplet (contre-exemple dans le mauvais paquet, derniere garde de comportement non honoree) | technique | theo-archi | `ADR-0012` amende, `ADR-index.md` mis a jour | clos |
| Echec transitoire de `LesReglesMordentTest` (2 echecs sur 21), non reproduit | qualite / technique | theo-archi | aucun fichier ne le tranche | ouvert, sous surveillance de la CI |
| Libelle de fin de partie REFUSE par `nadia-prof` | pedagogie | nadia-prof | reformulation "C'est fini !" ecrite dans la fiche, appliquee (commit `caa1049`) | clos |
| Bouton "Retour à l'accueil" illisible au CP, accepte sous reserve | pedagogie | nadia-prof | reformulation "Les jeux" ecrite dans la fiche, appliquee (commit `caa1049`) | clos |
| Indice trop long pour le lecteur le plus fragile, accepte sous reserve | pedagogie | nadia-prof | reformulation "Compte sur tes doigts." ecrite dans la fiche, appliquee (commit `caa1049`) | clos |
| Six libelles introduits sans DEC dediee, remontes par `relecteur-pedagogique` | redaction / pedagogie | nadia-prof | verdicts individuels dans la fiche, section 3 | clos |
| Repartition des sommes biaisee vers le haut (43,9 % sur 9-10, 7,6 % sous 5) | produit + technique | camille-po, theo-archi | aucune decision ecrite | ouvert, route au backlog de s02 |
| Indice de S1-5 inatteignable en jeu reel avant la 14e manche | produit | camille-po | aucune decision ecrite | ouvert, route au backlog de s02 |
| `squad/validation/FILE-ATTENTE.md` ne reflete pas encore le verdict "VALIDE SOUS RESERVE" (porte encore "REFUSE, depuis s00") | produit | camille-po | a mettre a jour au prochain planning | ouvert |
| Fusion de trois worktrees paralleles, un defaut de fusion reel | capacite / technique | sofia-sm | corrige dans `d534451` | clos |
| Arbre de travail partage entre agents pendant la porte technique (fichiers d'un agent emportes par le commit d'un autre, reinitialisation dure ayant detruit du travail non commite) | capacite / process | sofia-sm | voir `retro.md`, amendement propose | ouvert, amendement en discussion |
| `camille-po` et `relecteur-pedagogique` sans outil shell : leurs livrables ont ete commites par un tiers | capacite / process | sofia-sm | voir `retro.md`, amendement propose | ouvert, amendement en discussion |

Rappel : un obstacle n'est `clos` que si le fichier qui le tranche existe physiquement. Verifie
pour chaque ligne marquee `clos` ci-dessus : `FICHE-bandit-manchot-additions-cp.md`, `DEC-0004`,
`DEC-0005`, `ADR-0012` (et `ADR-index.md`), `d534451` et `caa1049` existent tous sur le disque et
ont ete lus avant d'ecrire ce tableau.

## Porte pedagogique - nadia-prof

- **Verdict** : **VALIDE SOUS RESERVE. Done pedagogique ACCORDE.**
- **Fiche** : `squad/conformite/FICHE-bandit-manchot-additions-cp.md`.
- **Audit joue** : oui, non degrade. Fat jar reconstruit depuis `round-two`, lance sur le port
  8099. 171 manches reelles relevees sur 24 parties, plus des parties dediees a forcer chacune
  des aides de S1-5, plus le catalogue vide reellement rendu.
- **Motif, en une phrase** : les trois motifs de refus de s00 sont leves et verifies en jeu, le
  contenu mathematique est exact sur 171 manches, les trois criteres visuels sans test automatise
  sont juges satisfaits a la main - mais un libelle introduit ce sprint est refuse (fin de partie
  inconditionnelle) et deux constats de fond restent a traiter (repartition des sommes, indice
  inatteignable), d'ou le "sous reserve" plutot qu'un "VALIDE" sans condition.
- **Ce que ce verdict ne change pas** : `squad/programme/MATRICE-couverture.md`, ligne
  mathematiques / CP / "Calcul mental - additions dans la limite de 10 (puis 20)", **reste `en
  cours`**. `nadia-prof` motive explicitement ce choix : le Done pedagogique porte sur l'increment,
  la matrice porte sur la couverture reelle de la competence, et un jeu qui passe 43,9 % de son
  temps sur deux des onze sommes possibles n'a pas fini de couvrir "les additions dans la limite de
  10". Elle pose une condition ecrite pour que la ligne passe a `couvert-valide` : la reserve sur
  la repartition des sommes traitee. Cette ligne appartient a `camille-po`, je ne l'ai pas
  editee.
- **Onze reserves nommees pour s02** (fiche, section 6), dont trois deja traitees pendant cette
  review (les trois textes ci-dessus) et deux routees explicitement plus haut (repartition des
  sommes, indice inatteignable). Les six autres (tailles de texte residuelles, "Recommencer" sans
  forme de bouton, "0 + 0" possible en premiere manche, "Calcul CP" trop large a terme, titre non
  accentue, ecran de catalogue vide sans action) restent au backlog de s02, non bloquantes.
