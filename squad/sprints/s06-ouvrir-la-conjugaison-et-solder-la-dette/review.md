# [Sprint s06] Review - 2026-08-10

- **Horodatage de debut** : 2026-08-07 15:35 (repris de `planning.md`)
- **Horodatage de fin** : 2026-08-10 10:35
- **Duree effective** : 2 jours 19 heures
- **Anime par** : sofia-sm
- **Commit de tete de `round-two`** : `86ea06d`

## Increment livre

La ligne francais CM1 "homophones grammaticaux" passe `couvert-valide` (deuxieme ligne du produit,
premiere en francais) ; "Le bon son" (CP) sort de quarantaine et rejoint le catalogue, `VALIDE SOUS
RESERVE` ; S6-9 et S6-10 sont accordes sans reserve bloquante ; le rembourrage lateral sous 1200 px
et le rendu a 360 px sont confirmes tenir. A l'oppose, "Le bon verbe" (conjugaison presente CM1),
ouvert ce meme sprint, est **REFUSE** pour cinq indices qui enseignent une regle de conjugaison
fausse, et retire du catalogue par `DEC-0014` dans le meme geste que ce retrait technique. Deux
scripts d'outillage testes (`verifier-lignee.sh`, `verifier-jdk.sh`) et une commande de mesure de
rendu versionnee (`ADR-0025`) equipent desormais le sprint suivant.

## Rapport qualite - yanis-qa

- **Fichier** : `squad/sprints/s06-ouvrir-la-conjugaison-et-solder-la-dette/rapport-qualite.md`.
- **Resultat** : `mvn -B clean verify` vert sur son worktree isole (277 tests avant la derniere
  vague de fusions, coherent avec l'etat de `round-two` au moment de son rapport).
- **Couvert** : le plancher exact de `chrome --headless --window-size` (500 px, decoupage a gauche
  et non mise a l'echelle, borne nette a 516 px) ; le correctif de S6-8 reverifie par deux voies
  independantes de la mesure d'origine (lecture statique de la cascade CSS, rendu reel par iframe
  hors plancher) ; le debordement de menu a 360 px non reproduit par une methode qui evite le
  plancher, reproduit par la commande fautive sans qu'aucune propriete CSS ne deborde reellement ;
  preuve de morsure du garde-fou de S6-8 par mutation reelle.
- **Non couvert** : aucune assertion programmatique en pixels sans capture d'ecran (limite du
  protocole DevTools, nommee et non mise en oeuvre par lui, reprise par `theo-archi` dans
  `ADR-0025`).
- **Tests instables** : aucun.
- **Reverifie a la cloture, sur l'arbre fusionne complet** (`86ea06d`, apres l'execution de
  `DEC-0014`) : `mvn -B clean verify` **BUILD SUCCESS**, 5 modules. **279 tests**, methode : somme
  de l'attribut `tests` de la balise racine `<testsuite>` des **53 rapports XML** (50 sous
  `target/surefire-reports/`, 3 sous `target/failsafe-reports/`) des cinq modules du pom
  agregateur, apres un `clean`. Repartition : `adapter-web` 121, `bootstrap` 76 (73 unitaires + 3
  d'integration), `domain` 43, `adapter-content` 32, `application` 7. Chiffre inchange par
  l'execution de `DEC-0014` : les trois methodes de `PaquetsFrancaisS04Test` qui lisaient
  "Le bon verbe" via son entree de catalogue basculent sur le paquet charge directement, meme
  nombre de tests.

## Confirmation ArchUnit - theo-archi

- **Fichier** : `squad/sprints/s06-ouvrir-la-conjugaison-et-solder-la-dette/confirmation-archunit.md`.
- **Commande** : `mvn -B clean verify`, JDK 21 designe par `JAVA_HOME` le temps de la commande.
- **Resultat** : vert, arbre juge `be60b10` plus son propre commit (`ADR-0025`,
  `outils/mesurer-rendu.sh`, etape CI `outils`, limite nommee dans la Javadoc de
  `ViewportEtLargeurTelephoneTest`) - **279 tests**, methode identique a celle de `sofia-sm`,
  chiffres comparables et confirmes.
- **28 `ArchRule` declarees, 28 executees** (27 par le moteur `@ArchTest`, 1 par un `.check()`
  explicite depuis deux methodes - `ScanDeComposantsTest`, `ADR-0021`). Fait a retenir pour tout
  comptage futur : **un comptage par `@ArchTest` annoncerait 27, pas 28**, parce que la 28e regle
  ne se declare pas par ce mecanisme. Le sprint a ajoute **une seule `ArchRule`** mais **onze
  garde-fous** (neuf classes de test Java sans `ArchRule`, plus les trois scripts a juge pur
  `verifier-lignee.sh`, `verifier-jdk.sh`, `mesurer-rendu.sh`) : le compte de regles ArchUnit
  n'est pas une mesure de la couverture de garde-fous du sprint, et confirmation-archunit.md le dit
  explicitement pour qu'un futur lecteur ne s'y trompe pas.
- **Plafonds de dette, verifies a la taille exacte de leur dette** : `DETTE_DE_BALAYAGE` (balayage,
  `LesGardeFousSontMisALEpreuveTest`) compte 2 noms (`FaviconTest`, `FrontieresDuJavaScriptTest`),
  `PLAFOND_DE_DETTE` vaut 2. `DETTE_DE_CONTENU` (`ChaquePaquetPorteUnIndiceTest`, `ADR-0016`) est
  vide, son plafond vaut 0. Aucune place libre dans les deux cas.
- **`ADR-0025`** tranche l'obstacle route par `ines-design` puis `yanis-qa` sur la mesure de rendu :
  le debordement de mise en page a une largeur donnee est declare **non gardable** par un automate
  de ce depot (propriete du rendu, jamais de la declaration, confiee a `nadia-prof`) ; toute mesure
  de rendu passe desormais par `outils/mesurer-rendu.sh`, une commande versionnee qui refuse une
  fenetre parente sous le plancher de 516 px et une invocation sans profil isole, testee par seize
  cas et prouvee mordante par trois mutations reelles.
- **Avis de porte technique** : **ouverte**. Ce n'est pas le Done du sprint - le Done pedagogique
  reste entierement a `nadia-prof`.

## Relecture - relecteur-pedagogique

- **Fichier** : `squad/sprints/s06-ouvrir-la-conjugaison-et-solder-la-dette/relecture.md`.
- **Corrections appliquees (3)** : deux coquilles dans `proposition-ines-menu-360px.md` ("exactly"
  -> "exactement", "de na pas" -> "de ne pas") ; un accord fautif dans `recreo.css`
  ("axes laterales" -> "axes lateraux").
- **Signale sans corriger** : un unique mot accentue ("figé") dans un commentaire shell par
  ailleurs non accentue, pour information, sans arbitrage demande.
- **Verifie sans rien trouver** : messages de commit (22, tous prefixes, ASCII pur), tirets
  cadratin et guillemets typographiques, emoji, chemins de poste sur 89 fichiers modifies dont les
  rapports les plus exposes, coherence du renommage "Accueil" -> "Les jeux", le point sur la
  reserve 5 perimee de `nadia-prof`, exactitude des 29 items de contenu neufs et de leurs
  indices/explications, coherence arithmetique de la matrice, exactitude technique des `ADR` contre
  le code livre.
- **Correction supplementaire, trouvee a la porte technique et non par elle** : sa propre citation
  d'exemple dans `relecture.md` (une forme Git Bash de chemin de poste) faisait rougir
  `verifier-chemins.sh` qu'elle discute au meme endroit - un segment reel suit la forme citee,
  contrairement a la convention tronquee de `CLAUDE.md`. Corrigee par `sofia-sm` lors de la
  verification finale (commit `86ea06d`) plutot que renvoyee pour un second cycle de relecture,
  parce que le changement est mecanique et sans ambiguite (meme regle que celle qui autorise le
  relecteur lui-meme a corriger sans arbitrage).

## Obstacles du sprint

| Description | Categorie | Agent route | Decision liee | Statut |
|---|---|---|---|---|
| Arbitrage de capacite entre les trois chantiers du sprint | capacite | sofia-sm | `planning.md`, sequencement acte | clos |
| Six criteres de S6-3/S6-6/S6-7/S6-9/S6-14 ne verifiaient pas ce qu'ils croyaient verifier | produit | camille-po | deja actionnees dans le backlog lu au planning | clos avant planning |
| `GET /parties/{jeuId}/manche` sur une partie terminee (`DEC-0013` point 3) | technique | theo-archi | `ADR-0020` clause 3 | clos |
| Garde-fou generique inter-catalogue manquant pour S6-3 | technique | theo-archi | `ADR-0023`, `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` | clos |
| Champ `graphie` non lu par le chargeur, porteur du texte de retour non modelise (lot S6-6/S6-7) | technique | theo-archi | `ADR-0024` | clos |
| Debordement du menu de l'en-tete a 360 px | technique + pedagogie | ines-design, lea-dev, theo-archi, nadia-prof | non etabli (voir ci-dessous) - story sortie du sprint, confirmee par l'audit de `nadia-prof` ("aucun debordement") | clos |
| Plancher de `chrome --headless --window-size` (~500 px) faussant les mesures de rendu sous ce seuil | qualite + technique | yanis-qa, theo-archi | `ADR-0025`, `outils/mesurer-rendu.sh` | clos |
| Deux mesures de rendu du sprint sans commande citable dans le depot | qualite | theo-archi | `ADR-0025` impose une commande versionnee pour toute mesure future | clos |
| `verifier-lignee-test.sh` et `verifier-jdk-test.sh` testes mais jamais executes par aucun automate | technique | theo-archi | etape CI `outils` ajoutee (`.github/workflows/ci.yml`, `ADR-0025`) | clos |
| `verifier-lignee.sh` refuse a tort un worktree simplement en retard sur `round-two` | technique (mon propre lot, sous casquette dev) | sofia-sm | corrige, cas ajoute a la suite, morsure prouvee par mutation reelle sur le worktree de `theo-archi` | clos |
| `verifier-lignee.sh` refuse a tort un worktree fourche d'une tete ancienne portant son propre commit (branche jetable) | technique (mon propre lot, sous casquette dev) | sofia-sm | corrige (test d'ancetre commun unique), cas ajoute a la suite, morsure prouvee par mutation reelle sur le worktree de la branche jetable | clos |
| Deux mots exemples de `nadia-prof` (MAIN, PAIN) ecartes du paquet "Le bon son" sans qu'elle le sache avant son audit | pedagogie (information, pas un arbitrage) | nadia-prof | inscrit avant son audit (commit `9dee88b`) ; son verdict `VALIDE SOUS RESERVE` ne le nomme pas comme un point bloquant | clos |
| `bandit-manchot-conjugaison-present-cm1` REFUSE mais vivant dans `catalogue.yaml`, jouable par un enfant | produit (retrait/correction) | camille-po | `DEC-0014`, executee dans le meme geste que sa creation (retrait de la ligne, commit `6060d32`) | clos |
| Citation dans `relecture.md` faisant rougir `verifier-chemins.sh` | redaction | relecteur-pedagogique (trouve par sofia-sm) | corrigee directement, commit `86ea06d` | clos |
| Correction des cinq indices fautifs de "Le bon verbe" | produit + technique | camille-po (story ecrite, prete), sofia-sm (affectation) | story en tete de "Backlog - sprints suivants", `DEC-0014` | **ouvert** - a affecter au planning s07 |
| Constat neuf : la 4e proposition d'un jeu a quatre choix passe sous le pli a 360 x 640 | pedagogie -> produit | nadia-prof -> camille-po | nomme, non transforme en critere, `nadia-prof` precise que ce n'est pas un motif de refus | **ouvert** - a instruire par camille-po |
| Six reserves non bloquantes de `nadia-prof` sur "Le bon verbe" (sujet toujours pronom, indice qui donne la reponse, ratio items/manches, decor repetitif, niveau non annonce a l'accueil) | pedagogie -> produit | nadia-prof -> camille-po | nommees, non bloquantes | **ouvert** - a convertir en stories |
| Divergence de mesure de rendu entre trois agents (lea-dev, ines-design, yanis-qa), sans qu'aucun n'ait menti, faute de methode reproductible sous 500 px avant `ADR-0025` | process | sofia-sm | note pour la retro (non tenue ce tour, voir consigne) | **ouvert** - a instruire en retro |

Rappel du gabarit : un obstacle n'est `clos` que si le fichier de decision existe physiquement.
Les deux derniers restent `ouvert` parce que leur artefact (story affectee, amendement de process)
n'existe pas encore.

## Porte pedagogique - nadia-prof

Quatre fiches auditees le 2026-08-10, branche `s06-nadia-porte-pedagogique`, commit `9f7bba2`.

| Increment | Verdict | Ligne de matrice | Motif / reference |
|---|---|---|---|
| `bandit-manchot-conjugaison-present-cm1` | **REFUSE** (premier audit) | reste `en cours` | Cinq indices sur douze enoncent une regle de conjugaison fausse, trois dementies par un autre item du meme paquet ; l'indice n'est servi qu'apres trois echecs, a l'enfant le moins en etat de la detecter. `FICHE-bandit-manchot-conjugaison-present-cm1.md`, section 2. |
| `bandit-manchot-homophones-cm1` | **VALIDE** | passe **`couvert-valide`** | Sa condition en six points est levee sur le paquet a 24 items. `FICHE-bandit-manchot-homophones-cm1.md`, verdict du 2026-08-10. |
| `bandit-manchot-le-bon-son-cp` | **VALIDE SOUS RESERVE** | reste `en cours`, barre ecrite | Le REFUSE du 2026-08-06 est leve sur ses cinq motifs. `FICHE-bandit-manchot-le-bon-son-cp.md`, verdict du 2026-08-10. |
| S6-9 (une seule sortie par ecran de jeu) | **VALIDE** | transverse, aucun mouvement de matrice | Verifie ecran par ecran sur le HTML servi et sur le rendu reel. |
| S6-10 (bandeau + icone) | **VALIDE** | transverse, aucun mouvement de matrice | Audit degrade du 2026-08-07 leve apres recomptage des 1855 pixels sur l'icone telechargee depuis l'application reelle. |
| Debordement du menu a 360 px | **aucun debordement constate** | sans objet | Confirme la decision de `sofia-sm` de sortir la story du sprint (commit `be60b10`), en creditant explicitement le refus de fabriquer un motif de remplacement. |

`nadia-prof` ajoute, hors verdict : un constat neuf sur la 4e proposition sous le pli a 360x640
(non bloquant, route a `camille-po`) ; six reserves non bloquantes sur "Le bon verbe" ; la barre
qu'elle pose pour que la ligne conjugaison CM1 passe `couvert-valide` une fois le futur et
l'imparfait ouverts.

## Decisions prises

1. **Execution de `DEC-0009` dans le meme commit que le verdict qui la leve** (amendement de la
   retro de s04, deja applique en s05) : fusion en une seule commande `octopus` de
   `s06-nadia-porte-pedagogique` et de la branche jetable `s06-lea-son-cp-jouable-jetable`, commit
   `3f08bf5`, plutot qu'une fusion sequentielle qui aurait separe la levee de son execution dans
   deux commits distincts.
2. **Mecanisme d'execution de `DEC-0014`** : retrait pur et simple de la ligne
   `bandit-manchot-conjugaison-present-cm1` de `catalogue.yaml`, identique au precedent `DEC-0009`
   - la decision laissait le choix technique ouvert (suppression, drapeau de desactivation, autre
   mecanisme). Execute sous ma casquette dev, dans le meme geste que les fusions de ce tour
   (commit `6060d32`), pour ne pas clore cette review avec une `DEC` de retrait non executee -
   exactement le defaut de gouvernance constate sur `DEC-0012` a la cloture de s05.
3. **Correction directe de la citation fautive de `relecture.md`** plutot qu'un renvoi au
   relecteur-pedagogique pour un second cycle : changement mecanique et sans ambiguite (commit
   `86ea06d`).
4. **La story de correction de "Le bon verbe"** (prete, ecrite par `camille-po`) n'est pas
   affectee a un developpeur dans cette review : son entree au planning est une decision de
   capacite qui appartient au prochain planning (s07), pas a cette cloture.
5. **Aucun `RAPPORT.md` ni retro ce tour** : demande explicite, a produire dans un tour distinct.

## verifier-lignee.sh - deux defauts corriges ce sprint, recapitulatif pour la review

- **Premier defaut (2026-08-07, trouve par `theo-archi`)** : le script ne testait qu'un sens
  ("round-two est un ancetre de HEAD"), vrai seulement a l'instant du provisionnement. Corrige en
  testant aussi le sens inverse.
- **Second defaut (2026-08-10, trouve par `nadia-prof`)** : le correctif du premier ne couvrait pas
  un troisieme cas - un worktree sain fourche d'une tete ancienne de `round-two`, portant ensuite
  son propre commit (exactement la forme de la branche jetable `s06-lea-son-cp-jouable-jetable`).
  Ni l'un ni l'autre sens d'ancetralite n'y est vrai.
- **Propriete retenue, finale** : un seul test, l'existence d'un ancetre commun entre `HEAD` et
  `round-two` ("git merge-base round-two HEAD"), qui couvre les trois formes saines (a jour, en
  avance, en retard ou fourche avec un commit propre) sans avoir besoin de les distinguer, et
  refuse exactement quand aucun ancetre commun n'existe - fait discriminant verifie sur ce depot
  reel : `git merge-base main round-two` echoue sans resultat (deux racines orphelines), la ou
  toute branche saine en trouve toujours un.
- **Preuve de morsure** : sur le worktree reel de la branche jetable
  (`s06-lea-son-cp-jouable-jetable`), le script d'avant le second correctif le refuse ("ne descend
  pas de round-two", rouge) ; le script corrige l'accepte (vert) ; worktree restaure identique
  ensuite. Le refus d'une lignee reellement etrangere reste tenu (cas fabrique "main" de la suite,
  confirme par la commande reelle sur ce depot).
