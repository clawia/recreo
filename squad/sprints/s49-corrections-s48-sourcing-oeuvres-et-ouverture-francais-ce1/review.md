# Review - s49 - trois corrections differees de s48, `FRA-CE1-01` (premiere ligne construite du
vivier francais CE1), chantier de sourcing du corpus d'oeuvres du domaine public inscrit

- **Horodatage de debut** (`planning.md`) : 2026-10-03 14:59:00 (UTC+02:00).
- **Horodatage de fin** : 2026-10-03 19:20:25 (UTC+02:00, horodatage du dernier commit du sprint,
  `a272c703`, cloture de `camille-po`).
- **Duree effective** : 4 h 21 min.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Pas de worktree dedie** : document ecrit directement sur `round-two`, meme pattern que
  `planning.md`. Verifie avant d'ecrire : `git status --short` rend vide, `git rev-parse
  --abbrev-ref HEAD` rend `round-two`, `git log -1 --format=%H` rend
  `a272c703f5b1ed10c5c01570e0d0f810ded90f89`.
- **Arbre revu** : `round-two` a `a272c703`.

---

## 1. L'increment livre, en une phrase

Une ligne du programme passe de `non couvert` a `couvert-valide` (`FRA-CE1-01`, lire des mots a
graphemes complexes et les associer a leur son - premier paquet de contenu de francais CE1
construit, Done pedagogique **VALIDE SOUS RESERVE** au second passage, apres un **REFUS** au premier
passage le meme jour), trois corrections differees de s48 sont levees sans toucher de statut
(`PRO-CE1-R1`/`PRO-CE1-R2` sur `MAT-CE1-08`, `ANG-CP-R3` et l'item non leve d'`ANG-CP-R1`/`ANG-CP-R2`
sur `LVE-CP-06`, la reserve sans identifiant de l'item 7 d'`EMC-CP-14`), et le chantier de sourcing du
corpus d'oeuvres du domaine public (`DEC-0077`, `ADR-0082`) avance d'un mecanisme technique sans corpus
reel (T2-T4), pendant qu'une erreur factuelle de `theo-archi` - l'`ADR` du chantier declare manquant
alors qu'il existait depuis le 2026-09-05 - traverse deux `DEC` avant d'etre corrigee le jour meme.
**Quatre Done pedagogiques prononces, zero refus final, un refus intermediaire leve dans le meme
passage.** La couverture passe de **119/182 (65,4 %)** a **120/182 (65,9 %)**.

## 2. Le rapport qualite de `yanis-qa` (resume)

`rapport-qualite.md` (arbre mesure `round-two` a `90c36ab0`, perimetre explicitement borne aux huit
commits `d7360cc5` a `90c36ab0`, les deux commits de ceremonie qui precedent - ouverture, planning -
et les quatre commits posterieurs - refus, corrections, Done pedagogique, cloture - en sont exclus et
le rapport le dit) : `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, lance **deux fois**
sur cet arbre, **BUILD SUCCESS** les deux fois, 6/6 modules, ~9 min. **1975 tests (1969 unitaires + 6
integration), +21 depuis s48**, ecart explique sans reste par six fichiers (`PaquetCe1GraphemesComplexesTest`
+13, `PaquetCpAnglaisCultureTest` +1, `PaquetCpEmcAlerteTest` +1, `RessourcesTiercesTest` +1,
`ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest` +2,
`LIllustrationDeContenuResoutEtAucuneNeDortTest` +3). **Les quatre jobs de la CI** (`build`, `prefixes`,
`chemins`, `outils`) sont verts, **reproduits localement un par un**, aucun deduit de `mvn verify`
seul. Huit commits, huit prefixes d'agent conformes.

**Le mecanisme T2-T4 mis a l'epreuve, pas seulement lu** (section 3 du rapport) : `yanis-qa` copie le
contre-exemple de test sous un repertoire reellement servi (`static/oeuvres/oeuvre-test-fuite.jpg`),
relance les trois garde-fous concernes - **rouge sur les trois angles a la fois**, cinq echecs, zero
faux vert -, puis retire le fichier et reconfirme le vert. **Conclusion de `yanis-qa` : "La porte
technique de s49 est verte."**

## 3. L'avis de faisabilite de `theo-archi` (resume), et sa propre erreur corrigee le jour meme

`avis-faisabilite-s49-quatre-stories.md`, fonde sur `round-two` a `cafd9fe5` : `mvn verify` vert (1954
tests, base d'avant-sprint), ArchUnit a 36/35, aucune des quatre stories ne touche `src/main/java`.
Quatre verdicts "faisable en l'etat". Six pieges nommes pour `FRA-CE1-01` (lexiques accentues, indice
par sous-chaine, terminaison `-ient` non separable par un suffixe, famille (a) propre aux graphies
nasales, guillemets et blanc insecable, titre). Deux questions non bloquantes routees a `nadia-prof`
(Q1, Q2), confirmees sans ecart dans la fiche de conformite.

**L'erreur, et sa trace complete dans le depot.** L'avis de faisabilite de s48 (section 11,
`theo-archi`) affirmait qu'aucun `ADR` ne couvrait le chantier de sourcing du corpus d'oeuvres du
domaine public et en demandait un. **Cette affirmation etait fausse : `ADR-0082` existe depuis le
2026-09-05** (commit `4f594d64`), repond deja aux quatre questions de `DEC-0077` section 4, et sa
regle (`ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest`, 18 tests verts)
tournait deja. L'erreur a traverse **deux `DEC`** avant d'etre trouvee : `DEC-0105` (section 2,
`camille-po`, ecrite a s48 sur la foi de l'avis de `theo-archi`) et `DEC-0107` (section 5, ouverture de
s49, meme source). C'est `theo-archi` lui-meme qui la trouve et la corrige, dans son propre avis de
s49 (section 7) : *"`DEC-0105` et `DEC-0107` section 5 reprennent mon erreur."* `camille-po` amende les
deux `DEC` le jour meme (`fcd7c3ee`, section 2 bis de `DEC-0105`, section 6 de `DEC-0107`) : la
consequence est que le travail restant sur le chantier n'est plus architectural mais concret (T2 a T4,
T5), et ce travail entre au perimetre de s49 hors chiffrage en lots, sur le precedent du palier 2
(`DEC-0074` section 1.2). Traite en lecon de process dans `retro.md`, section 2 : l'erreur est de
`theo-archi`, pas la mienne, et je ne la fabrique pas en obstacle qui m'appartiendrait.

## 4. L'episode `FRA-CE1-01` : un refus, un second passage le meme jour, une reserve neuve

**Premier passage (`nadia-prof`, instance A, 2026-10-03) : REFUSE**, deux motifs. Dix-sept parties
completes jouees, les 24 items tires. **Motif 1** : cinq items sur 24 portaient deux reponses justes
(*pain*/*chien*, *combien*/*copain*... riment reellement, l'exemple fautif de `GRC-CE1-3` ecrit par
`nadia-prof` elle-meme avait ete recopie par le paquet - elle le corrige dans l'arbitrage le jour meme,
sans deplacer sa barre). **Motif 2** : huit indices sur 24 ne donnaient ni methode ni lieu a regarder,
dont quatre enoncaient un conseil faux (*"ne choisis pas le mot qui te semble familier"* - la bonne
reponse est souvent le mot familier). `nadia-prof` ecrit, dans le meme document, **la barre exacte qui
leve chaque motif** : sur les items `ain`/`ein`/`ien`, aucun distracteur ne porte le son de *« main »* ;
les huit indices sont remplaces par une methode ou un lieu a regarder, autorise par sa propre reponse a
la question Q1 de `theo-archi` (nommer des lettres pour dire ou regarder est permis, nommer une
graphie comme un son ne l'est pas).

**Second passage (`nadia-prof`, instance A, meme jour) : VALIDE SOUS RESERVE.** `marc-dev` corrige
(`bcac0b17`) en reprenant la forme que `nadia-prof` avait ecrite au mot pres (cinq distracteurs
remplaces, huit indices reformules en *"Dis les trois mots tout bas."*). Vingt-sept parties completes
jouees, les 24 items tires chacun avec une erreur au moins. Les deux motifs sont leves. Une reserve
neuve et non bloquante s'ouvre, **`GRC-CE1-R1`** : le distracteur *colline* revient sur les quatre
items de la famille `ill`, annonce par `nadia-prof` elle-meme au premier passage (*"si ce point est
encore la au prochain passage, j'ouvrirai une reserve neuve et datee"*) et tenue comme promis.

**Ce que cet episode confirme, sur le meme modele que l'episode "oeufs" de s47** : quand la fiche de
`nadia-prof` ecrit une forme exacte plutot qu'un seul critere, la reprendre mot pour mot ferme la ligne
sans reserve neuve sur ce point precis - c'est exactement ce que la retro de s48 avait signale comme
manquant sur `ANG-CP-R1`/`EMC-CM1-10`, et c'est ce que ce premier refus, suivi d'un second passage le
jour meme avec une forme exacte, demontre a nouveau. **A la difference de l'episode "oeufs"** (un test
qui refusait a tort un contenu juste), celui-ci est un refus pedagogique reel sur un contenu reellement
fautif, leve par une correction reelle : les deux episodes partagent la forme (un aller-retour documente
dans la meme fiche, le meme jour) mais pas la nature de l'ecart initial.

## 5. Le mecanisme T2-T4 du chantier de sourcing : aucune ligne fermee, ce qui reste nomme

`ADR-0082` (section "Le cout, et le decoupage en taches") decoupe le chantier en huit taches. A la
cloture de s49 :

- **T1** (registre, garde-fou, `ADR`) : fait depuis le 2026-09-05, par `theo-archi`.
- **T2, T3, T4** (branchement technique : resolution d'un identifiant `oeuvre-` dans les deux
  repertoires, seconde branche de gabarit et seconde exemption etroite, plafond de pixels) : **livres a
  s49** par `sofia-sm` (`90c36ab0`), sur fixtures synthetiques uniquement - **aucun acces web
  disponible**, aucune preuve de domaine public fabriquee. Mis a l'epreuve par `yanis-qa` (section 2).
- **T5** (choix des oeuvres par `nadia-prof`, premiere etape de la constitution du corpus) : **fait en
  partie**. `nadia-prof` choisit 28 oeuvres candidates pour les neuf lignes bloquees
  (`squad/corpus/CHOIX-oeuvres-candidates-T5.md`, `98346560`), **aucune verifiee** - dates,
  institutions, inventaires et renonciations CC0 ecrits de memoire, sans acces web, explicitement
  qualifies de *"proposition a verifier, pas preuve de domaine public"*. Cinq questions routees (annee
  avant notre ere, photographie de monument, recadrage, nom de convention, provenance coloniale).
- **T6, T7, T8** (paquets arts plastiques CP, arts plastiques CM1, histoire des arts CM1) : **non
  engages**. Aucun ne peut l'etre tant que T5 n'a pas produit une seule entree verifiee de registre :
  `squad/corpus/oeuvres-domaine-public.yaml` declare toujours `oeuvres: []` a la cloture de s49, verifie
  par `yanis-qa` (section 2 du rapport qualite). Les neuf lignes de la matrice (arts plastiques CP/CM1,
  histoire des arts CM1) restent `non couvert`, inchangees.

**Zero ligne fermee par ce chantier a s49, et ce n'etait pas l'objectif chiffre du sprint** (`DEC-0107`
section 5 : *"aucun Done pedagogique n'est attendu dessus a s49"*). Ce que cette cloture retient : le
chantier a produit, pour la premiere fois depuis s29, un mecanisme technique mis a l'epreuve et un
premier geste de choix de matiere - mais aucune preuve verifiee, et la limite structurelle qui l'arrete
(l'absence d'acces web de la squad) est nommee en section 7 et traitee en lecon de process dans
`retro.md`.

## 6. La reserve `GRC-CE1-R1` : deja routee au backlog

`camille-po` a deja ecrit la story de correction differee de `GRC-CE1-R1` dans `docs/backlog/backlog.md`
(section "Backlog - sprints suivants", meme commit que la cloture, `a272c703`) : besoin, competence
visee, criteres d'acceptation testables et ce qui reste a la seule porte de `nadia-prof`, sur le meme
modele que les trois corrections differees de s48. Aucun developpeur n'y est encore affecte ; a
reprendre au planning d'un sprint futur.

## 7. Tableau des obstacles du sprint

| Obstacle | Categorie | Route a | Statut a la cloture |
|---|---|---|---|
| L'avis de faisabilite de `theo-archi` (s48, section 11) affirmait a tort qu'aucun `ADR` ne couvrait le chantier de sourcing, alors qu'`ADR-0082` existait depuis le 2026-09-05 ; l'erreur a traverse `DEC-0105` et `DEC-0107` | Technique / exactitude d'un avis d'architecture | `theo-archi` (origine), `camille-po` (deux `DEC` a corriger) | **Resolu le jour meme** - `theo-archi` la trouve et la corrige dans son propre avis de s49 (section 7) ; `camille-po` amende `DEC-0105` (section 2 bis) et `DEC-0107` (section 6) dans le meme commit (`fcd7c3ee`) |
| `FRA-CE1-01` refuse au premier passage de `nadia-prof` (deux motifs : cinq items a deux reponses justes, huit indices sans methode) | Pedagogie / exactitude d'un contenu neuf | `nadia-prof` (barre ecrite), `marc-dev` (correction) | **Resolu le jour meme** - second passage, forme exacte reprise au mot pres, Done pedagogique VALIDE SOUS RESERVE prononce |
| Reserve neuve `GRC-CE1-R1` (le distracteur *colline* revient sur les quatre items `ill`), annoncee d'avance par `nadia-prof` au premier passage si le point restait | Pedagogie / correction differee de contenu, non bloquante | `camille-po` (story a ecrire) | **Ferme** - story deja ecrite dans `docs/backlog/backlog.md` (`a272c703`), developpeur encore a affecter |
| Le chantier de sourcing (T5) ne produit a ce jour aucune preuve verifiee, faute d'acces web de la squad | Technique / limite structurelle, pas un defaut de process | aucune route possible depuis l'interieur du depot | **Ouvert, structurel** - nomme en section 5 et traite en lecon de process dans `retro.md`, non resoluble par un sprint de plus |
| T6 a T8 du chantier de sourcing restent non engages, neuf lignes toujours `non couvert` | Produit / sequencement du chantier | `camille-po` | **Ouvert, hors perimetre chiffre de s49** |
| `DEC-0106` section 4 portait une reference perimee d'un jour sur la borne de longueur du texte support | Produit / exactitude d'une reference | `camille-po` | **Resolu dans `DEC-0107` section 7** (devenue section 7 apres l'amendement de `fcd7c3ee`), corrige directement dans `DEC-0106` |

## 8. La couverture du programme

**120/182 (65,9 %)**, verifiee par moi sur `squad/programme/MATRICE-couverture.md` (note de cloture de
`camille-po`, commit `a272c703`) : une seule ligne fermee ce sprint, `FRA-CE1-01`. Les trois autres
stories (corrections differees) touchent des lignes deja `couvert-valide` (`MAT-CE1-08`, `LVE-CP-06`,
`EMC-CP-14`) et ne changent aucun statut. Le chantier de sourcing (T2-T4, T5) ne ferme aucune des neuf
lignes bloquees. 119/182 (65,4 %) a l'ouverture, 120/182 (65,9 %) a la cloture.

## 9. Ce que cette review ne fait pas

- Elle ne prononce aucun Done pedagogique : les verdicts de `nadia-prof` (sections 2, 4) sont deja
  ecrits dans les fiches de conformite ; cette review les rapporte, elle ne les rend pas.
- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` : le geste est deja execute
  par `camille-po` dans le meme commit de cloture (section 8).
- Elle n'ecrit aucune story de correction pour `GRC-CE1-R1` : deja faite par `camille-po` (section 6).
- Elle ne tranche pas la question de l'acces web de la squad ni l'ordre de T6 a T8 : elle les constate
  et les route en lecon de process a `retro.md`.
- Elle n'impute pas a `theo-archi` une faute qu'elle n'a pas a juger : elle rapporte le fait, deja
  constate et corrige par `theo-archi` et `camille-po` eux-memes dans le depot (section 3).

## Decisions prises

**Aucune `DEC` ni `ADR` n'est creee ou amendee par cette review.** Les decisions du sprint sont
`DEC-0107` (ouverture, amendee le jour meme) et les amendements de `DEC-0105`, deja ecrites avant cette
cloture (section 3). Aucune decision ne m'appartient en propre au-dela du constat des obstacles
(section 7).
