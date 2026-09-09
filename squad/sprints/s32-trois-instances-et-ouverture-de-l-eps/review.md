# [Sprint 32] Review - 2026-09-09

- **Horodatage de debut** : 2026-09-08 20:10 (repris du planning)
- **Horodatage de fin** : 2026-09-09 12:15
- **Duree effective** : 16 h 05
- **Anime par** : sofia-sm

## Increment livre

Trois instances de `nadia-prof` ont audite en parallele pour la premiere fois trois terrains
disjoints (histoire-geographie et anglais CM1, dette d'EMC CP, ouverture de l'education physique
et sportive CP) ; trois lignes ferment (`HG-CM1-01`, `HG-CM1-03`, `LVE-CM1-07`), quatre sont
refusees et le paiement d'une cinquieme dette est refuse, portant la couverture du programme de
63/138 (45,7 %) a 66/138 (47,8 %).

## Rapport qualite - yanis-qa

- **Commande** : `bash outils/mvn-jdk21.sh -B clean verify` (job `build`), plus `bash
  .github/scripts/verifier-chemins-test.sh` et `verifier-chemins.sh` (job `chemins`), la boucle du
  workflow sur `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py` (job `outils`), et
  `verifier-prefixes-test.sh` + `commit-atteignable-test.sh` + `verifier-prefixes.sh` (job
  `prefixes`) - les quatre jobs de `.github/workflows/ci.yml`, chacun relance et nomme, sur l'arbre
  `round-two` a `7c800261`.
- **Resultat** : vert sur les quatre jobs. `BUILD SUCCESS`, six modules `SUCCESS`, **1 439 tests, 0
  echec, 0 erreur** (193 fichiers `TEST-*.xml`, mesure directe sur `surefire-reports` et
  `failsafe-reports`, confirmee independamment par `sofia-sm` et `theo-archi` - trois mesures
  concordantes, aucune recopiee).
- **Couvert** : le contenu neuf par des tests qui derivent leur perimetre sur le paquet charge
  (`PaquetCm1HistoireGeographieTest`, `PaquetCm1AnglaisTest`, egalite stricte sur le nombre
  d'items) ; `LON-1`/`LON-2` par un garde-fou qui balaie tous les enonces de CP servis (36 paquets,
  528 enonces) ; l'exemption `DETTE_DE_MARQUEUR` mise a l'epreuve par mutation (etat refuse fige
  reinjecte, le garde-fou rougit, puis restauration verte).
- **Non couvert** : la lecture semantique des enonces (exactitude d'un fait d'histoire ou d'un
  terme, hors de portee de tout test structurel) ; le plancher `FER-3` (divergence entre la
  consigne donnee a `marc-dev`, "au moins 4 items", et la constante du depot `MINIMUM_PAR_TACHE =
  3`, question de source et non de test) ; aucune trace dans le depot d'une partie jouee de bout en
  bout sur `recreo.jar` pour les paquets d'EPS ou d'EMC corriges de ce sprint - trou de
  verification signale, pas confirme close.
- **Tests instables** : aucun.
- **Une regression de fragilite trouvee dans du code neuf** : `PaquetCm1HistoireGeographieTest`
  (lignes 300 et 320) lit la tache d'un item par `startsWith(...)` sur l'enonce, exactement le
  patron que ce meme sprint corrige ailleurs (`PaquetCpEmcExclusionTest`, `ADR-0089`) parce qu'il
  fait deriver le classement d'une tache d'un texte que `nadia-prof` peut reformuler sans le savoir.
  Les deux tests ne sont pas faux aujourd'hui (chaines verifiees caractere pour caractere) mais
  posent un plancher, pas une partition exhaustive verifiee - deux voisins du meme fichier montrent
  le patron correct. Route a `theo-archi`/`marc-dev` pour correction sur le meme patron.
- **Correction sur son propre rapport** : le compte de `ArchRule` reelles, trouve faux par le
  `relecteur-pedagogique` (35 annonce, 34 reel - `RegleContreExemple.java` declare deux `ArchRule`
  factices, pas une), confirme par `sofia-sm`, corrige par `yanis-qa` (commit `1dac01ef`). Ne
  rouvre pas le verdict de la porte technique.

## Confirmation ArchUnit - theo-archi

- **Commande** : `bash outils/mvn-jdk21.sh -B clean verify` sur le worktree `s32-theo2`, plus les
  trois autres jobs de `.github/workflows/ci.yml`, chacun nomme.
- **Resultat** : vert sur les quatre jobs. 1 439 tests (raccord exact avec la mesure de `sofia-sm`
  et de `yanis-qa`).
- **Regles ajoutees ce sprint** : la clause 12 (`ArchRule`) de `DecouplageMecaniqueContenuTest`
  (`ADR-0088`, avis de faisabilite du palier 2, fusionnee avant l'ouverture) ; `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`
  (`ADR-0089`, 7 tests dont 3 morsures, rend executable `LON-1`/`LON-2`). Le compte reel du depot
  passe de 33 (cloture de s31) a 34.
- **Rouge herite paye, pas contourne** : l'exemption `DETTE_DE_MARQUEUR` de
  `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` est retiree (clause 3, 1 111 items a
  deux distracteurs, 0 fautif, cliquet a plafond zero) apres que `S32-EMCCP07` a paye le defaut
  qu'elle exemptait. Une dette de meme forme reste ouverte sur la clause 2, declencheur ecrit dans
  la Javadoc du test.
- **Dette generale ouverte avec son chiffre** : 25 fichiers de test de paquet sur 95 lisent l'enonce
  par une operation de chaine plutot que par un champ de tache dedie ; majorant assume, declencheur
  ecrit dans `ADR-0089`, non pris dans ce commit.

## Relecture - relecteur-pedagogique

- **Corrections appliquees** : aucune faute d'orthographe, de ponctuation ou d'accentuation trouvee
  sur le contenu enfant des paquets touches ce sprint ; aucune forme interdite (tiret cadratin,
  guillemet typographique, emoji, chemin de poste de travail) trouvee dans le perimetre relu.
- **Points a trancher** :
  - `cp-emc-regle-souhait-loi.yaml`, item 11 - le partitif "de" a ete retire pour tenir `LON-1`
    ("vendre l'alcool" au lieu de "vendre de l'alcool"), francais incorrect pour un enfant.
    Reinserer le mot porte l'enonce a 16 mots, un de plus que `LON-1` n'autorise : question de
    redaction pedagogique, pas de correction sans ambiguite. Route a `nadia-prof` et `lea-dev`.
  - Le compte de `ArchRule` de `rapport-qualite.md` (35 au lieu de 34) - route a `yanis-qa`, corrige
    depuis (commit `1dac01ef`).
  - La citation du commit `dcbcd65a` dans `DEC-0083` (sections "Lien" et 13) pointe vers un commit
    orphelin, non atteignable depuis aucune branche ni tag, qui peut disparaitre au prochain
    `git gc` ; le meme contenu est verifiable sous `96c8efbe`/`043b8503`. Route a `theo-archi`
    (auteur des deux commits et de la citation), corrige depuis par `camille-po` dans `DEC-0084`
    section 6.4 et directement dans `DEC-0083`.

## Obstacles du sprint

| Description | Categorie | Agent route | Decision liee | Statut |
|---|---|---|---|---|
| Exemption `DETTE_DE_MARQUEUR` a retirer une fois le defaut qu'elle exemptait paye | Technique, garde-fou | theo-archi | ADR-0086 (amendement 2026-09-08) | clos |
| `LON-1`/`LON-2` sans regle executable | Technique, garde-fou absent | theo-archi | ADR-0089 | clos |
| `EXC-R1` - la consigne du catalogue nomme encore l'ancienne tache | Redaction, technique a tort | camille-po (via theo-archi et sofia-sm) | - | route, story `S33-EXC-R1` |
| `EXC-R5` - test qui classait la tache par sous-chaine de l'enonce | Technique | theo-archi | ADR-0089 (decision E) | clos, contenu du a nadia-prof (`S33-EXC-R5`) |
| Plancher `FER-3` : consigne "au moins 4" contre `MINIMUM_PAR_TACHE = 3` du depot | Pedagogie, source de la regle | nadia-prof | - | ouvert |
| Terme "les Sarrasins", `HG-CM1-04` | Pedagogie, formulation | nadia-prof | HG-R12 (bloquante) | ouvert, story `S33-HG-04` |
| Fait faux "sacre de Clovis en 481", `HG-CM1-02` | Pedagogie, exactitude | nadia-prof | HG-R11 (bloquante) | ouvert, story `S33-HG-02` |
| Item de "chat perche" declarant une faute le fait de toucher un joueur perche | Pedagogie, exactitude de regle de jeu | nadia-prof | JEU-R1 | ouvert, story `S33-EPS-JEU` |
| Repere de registre remplace par un repere lexical ("maitresse"), `CAT-1` | Pedagogie, meme classe de defaut que celle payee | nadia-prof | - | ouvert, story `S33-CAT1` |
| Regression de l'item 4 de `regle-souhait-loi`, due a une contradiction entre deux textes de nadia-prof | Pedagogie, defaut de methode | nadia-prof (a son propre debit) | - | ouvert, story `S33-EMCCP07` |
| Compte de `ArchRule` faux d'une unite (35 au lieu de 34) dans `rapport-qualite.md` | Redaction, qualite | yanis-qa | - | clos (commit `1dac01ef`) |
| Reference orpheline `dcbcd65a` dans `DEC-0083` | Redaction, technique | theo-archi / camille-po | DEC-0084 section 6.4 | clos |
| Couture (c) sur `REGISTRE-identifiants.md` a la fusion des trois instances | Technique, capacite | sofia-sm | DEC-0084 section 3 | clos, retenue de 3,0 maintenue |
| Deux tests neufs (`PaquetCm1HistoireGeographieTest`) reproduisant la lecture de tache par sous-chaine que ce sprint corrige ailleurs | Technique, garde-fou fragile | theo-archi / marc-dev | - | ouvert |
| Dette generale : 25 fichiers de test sur 95 lisant l'enonce par operation de chaine | Technique, garde-fou absent | theo-archi | ADR-0089 (declencheur ecrit) | ouvert |
| Absence de trace d'une partie jouee de bout en bout sur `recreo.jar` pour les paquets neufs | Qualite, verification manquante | yanis-qa (signale) | - | ouvert |
| Incoherence de referentiel : `LVE-CM1-07` sans la parenthese que porte `LVE-CP-06` | Pedagogie, referentiel | nadia-prof | - | ouvert |

Rappel : un obstacle n'est `clos` que si le fichier de decision existe physiquement.

## Porte pedagogique - nadia-prof

- **Verdict** : mixte, ligne par ligne. `HG-CM1-01` VALIDE, `HG-CM1-03` VALIDE, `LVE-CM1-07` VALIDE
  SOUS RESERVE (4 reserves non bloquantes) ; `HG-CM1-02` REFUSE (`HG-R11`, bloquante) ; `HG-CM1-04`
  REFUSE (`HG-R12`, bloquante) ; `EMC-CP-07` REFUSE, troisieme passage ; `EPS-CP-04` REFUSE,
  premier contact ; `EPS-CP-08` REFUSE, premier contact ; paiement de `CAT-1` refuse sur
  `EMC-CP-02` et `EMC-CP-06` sans rouvrir ni l'une ni l'autre.
- **Fiches** : dix fiches de conformite citees par les trois verdicts (`squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/verdicts-nadia-instance-A.md`,
  `-B.md`, `-C.md`).
- **Motif** : detaille ligne par ligne dans les trois verdicts et repris dans `DEC-0084` section 1.
  En resume - deux faits faux ou termes non revendiques en histoire-geographie (le sacre de Clovis
  en 481, "les Sarrasins") ; une regression de fond sur `EMC-CP-07` due a une contradiction entre
  deux textes de `nadia-prof` elle-meme ; un correctif d'`EMC-CP-02`/`-06` qui remplace un repere de
  registre par un repere lexical ; deux paquets d'EPS CP refuses au premier contact sur des
  conditions anterieures au lot (`FAB-1c`, `EPS-1`) et sur l'exactitude de regles de jeu (toucher un
  joueur perche au chat perche n'est pas une faute).
- **Reserves** : `LVE-CM1-07` porte quatre reserves non bloquantes (`ANG-R3` a `ANG-R6`), non
  encore routees en story distincte - a affecter au planning de s33. Le registre complet des
  reserves neuves et de leur sort est dans `DEC-0084`, section 4.

## Decisions prises

Aucune decision produit, pedagogique ou d'architecture n'est prise par cette review : elle
enregistre les verdicts des trois instances de `nadia-prof`, deja arbitres et deja rediges, et le
rapport qualite et la confirmation ArchUnit deja rendus. Les decisions du sprint sont celles
listees dans `RAPPORT.md`.
