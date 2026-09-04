# Review de s28 - second passage et chantier palier 2

- **Horodatage de fin** : 2026-09-04 21:35
- **Duree effective** : depuis l'ouverture (2026-09-03 22:18), environ 23h17.
- **Increment mesure** : `round-two` a `b37405d`.

---

## Porte technique - confirmee, avec un ecart de comptage explique

**Rapport qualite de `yanis-qa`** (`squad/sprints/s28-second-passage-et-chantier-palier-2/rapport-qualite.md`,
mesure sur `21f3d1d`, dernier commit de contenu avant les portes) : `bash outils/mvn-jdk21.sh -B
clean verify`, reacteur complet, **BUILD SUCCESS**, **1208 tests, 0 echec, 0 erreur** (165 rapports
XML lus, methode de l'amendement s11). Il confirme par sa propre execution, sur son propre `clean`,
le chiffre annonce par `marc-dev` dans son dernier commit de lot.

**Trois mesures posterieures, chacune signee par son auteur** : `lea-dev` (`ce3059b`, sur les
trois trouvailles de `yanis-qa`) annonce 1215 tests. `sofia-sm` (`aac5d88`, cliquet des garde-fous
de designation) annonce 1221 tests, 167 rapports XML. `lea-dev` a nouveau (`0c383b9`, correction du
paquet refuse) annonce **1222 tests, 0 echec, 166 rapports XML**, et confirme elle-meme le 1221 de
`sofia-sm` a `aac5d88` en reconciliant un ecart de 7 tests que sa comparaison precedente avait
signale, du a une base de mesure incomplete (les tests de `ce3059b` absents de cette base), le
delta de +1 restant inchange.

**Ma propre execution sur l'arbre final** (`b37405d`, apres la fusion de la matrice et de `DEC-0076`,
qui ne touchent aucun fichier Java) : `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet,
sans `-pl`. **BUILD SUCCESS**, les six modules (`recreo`, `recreo-domain`, `recreo-application`,
`recreo-adapter-web`, `recreo-adapter-content`, `recreo-bootstrap`) en `SUCCESS`.

**Compte de tests, methode de l'amendement s11** : somme de l'attribut `tests="..."` de chaque
rapport XML sous `target/surefire-reports/` et `target/failsafe-reports/`, apres `clean` (**167**
fichiers XML lus - domain 8, application 2, adapter-web 46, adapter-content 75, bootstrap 36 dont
5 failsafe). **Total : 1222 tests, 0 echec, 0 erreur.** Je confirme le chiffre de tests annonce par
`lea-dev` (1222), par ma propre execution, sur mon propre `clean`, sur l'arbre final du sprint.

**Un ecart que je ne tais pas et ne resous pas par une supposition** : je compte **167** rapports
XML, pas 166 comme l'ecrivait `lea-dev` a `0c383b9` (elle-meme etait passee de 167 a `aac5d88` a
166 a `0c383b9`, sans qu'aucun fichier de test n'ait ete retire dans son propre diff). Le nombre de
rapports peut varier d'une execution a l'autre sans faire varier le total de tests (une classe peut
se scinder en plusieurs fichiers de rapport selon le fork de Surefire) : **le total de tests, seul
chiffre que la methode de l'amendement s11 engage, est confirme a l'identique (1222, 0 echec)**. Je
signale l'ecart de comptage de rapports plutot que de le relayer sans verification.

**Verdict : porte technique verte**, confirmee independamment par trois agents (`yanis-qa`,
`lea-dev`, `sofia-sm`) et par moi-meme sur l'arbre de cloture.

## Confirmation ArchUnit

Aucun `confirmation-archunit.md` distinct n'a ete ouvert ce sprint. Les regles d'architecture
vivent dans les suites `com.recreo.archi.*` (domain) et leurs equivalents de `bootstrap`, toutes
vertes dans le decompte ci-dessus, y compris les regles neuves du sprint :

- **`DecouplageMecaniqueContenuTest`, ArchRule 31 a 33** (`13963eb`, `S28-5`) : la mecanique de
  designation ne connait aucun contenu, sur le modele des mecaniques existantes.
- **`ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest`** (`ADR-0081`, `c6acf3b` puis
  `1619d06`) : le cliquet `PLAFOND_DE_DETTE` est descendu de 42 a 0 dans le meme diff que la
  transcription des 42 paquets, comme l'`ADR` l'exige - aucune dette de declaration ne reste
  ouverte a la cloture.
- **Le garde-fou du cliquet de designation** (`aac5d88`) : plafond a zero, gratuit tant qu'aucun
  paquet de designation n'existe sous `src/main/resources`, verifie par mutation dans les deux sens.
- **L'exemption d'`ADR-0006`** (amendee dans `13963eb`) : `CadreDeZone(int, int, int, int)` est
  nomme, date et plafonne au seul rectangle en pourcentage - `theo-archi` a trouve et repare ce
  conflit avec sa propre regle de la veille avant que `sofia-sm` ne le decouvre en l'implementant.

---

## Corrections du `relecteur-pedagogique`

Commit `53400df`, deux categories de correction :

1. **Trois phrases cassees dans `squad/programme/MATRICE-couverture.md`** : deux lignes d'EPS CP
   portaient une phrase brisee par la fusion de deux gabarits de motif (l'une disait "aucun capteur
   ne mesure une mecanique de ce produit n'observe un corps"), restaurees sur le texte source du
   referentiel ; une faute de frappe corrigee dans la section d'integration.
2. **Toutes les references verifiees resolvent** : identifiants de condition des trois arbitrages,
   `ADR` et `DEC` cites, codes de ligne des paquets neufs vers leur puce de referentiel.

**Texte servi a l'enfant relu integralement** : les quelque 90 items neufs des sept paquets, plus
les consignes et titres du catalogue - zero faute.

**Un ecart signale et non corrige, hors du perimetre donne** : l'amendement d'`ADR-0006` vit dans
`13963eb` et non dans `c6acf3b` comme la consigne de relecture l'indiquait - verifie par
`git show` plutot que repris. Je ne le corrige pas ici, ce n'etait pas son mandat de le faire :
je le note pour que la trace ne se perde pas.

---

## Tableau des obstacles du sprint

| # | Obstacle | Categorie | Route vers | Resolution |
|---|---|---|---|---|
| 1 | `HIS-4` : forme technique du marqueur de ligne de referentiel a trancher avant `S28-2` | Technique/architecture | `theo-archi` (route au planning) | Ferme : `ADR-0081` (`c6acf3b`) - code par ligne, jamais un rang, cle de schema |
| 2 | Reprise du marqueur `HIS-4` sur les 42 paquets deja servis, arbitree en cours de sprint par l'utilisatrice | Produit (tache non planifiee) | `marc-dev` (affectation de `sofia-sm`) | Ferme : `1619d06`, cliquet de dette descendu de 42 a 0 dans le meme diff |
| 3 | Conflit entre `ADR-0080` (`CadreDeZone` a champs numeriques) et `ADR-0006` (contrat de contenu integralement textuel), non anticipe par `ADR-0080` | Technique/architecture | `theo-archi` (trouve et route par lui-meme) | Ferme : exemption nommee, datee et plafonnee, amendement d'`ADR-0006` dans `13963eb` |
| 4 | `PaquetCpQlmCorpsSensTest` non etendu alors que le paquet passe de 8 a 14 items - `FER-1` a `FER-3` affirmees fermees, gardees par rien | Qualite, trouve par `yanis-qa` apres coup | `sofia-sm` pour `lea-dev` | Ferme : `ce3059b`, le fichier passe de 3 a 8 tests |
| 5 | `ANG-5` ecrite en commentaire du paquet d'anglais sans etre ni testee ni declaree non gardee | Qualite, trouve par `yanis-qa` apres coup | `sofia-sm` pour `lea-dev` | Ferme : `ce3059b`, ligne de lecture desormais mordue |
| 6 | Test nomme `bia2...` ne verifiait pas `BIA-2` (distinction des reponses, pas l'absence de pays reduit a un seul trait) | Qualite, trouve par `yanis-qa` apres coup | `sofia-sm` pour `marc-dev`/`theo-archi` | Ferme : `ce3059b`, test renomme pour ce qu'il fait, `BIA-2` recoit un test qui la mord |
| 7 | Dette des garde-fous 4/5/6 de designation (`ADR-0080`) sans cliquet executable - rien n'empeche mecaniquement un paquet de designation d'entrer en production sans etendre les trois balayages | Qualite/technique, trouve par `yanis-qa` apres coup | `sofia-sm` pour `theo-archi` | Ferme : paye immediatement par `sofia-sm` elle-meme (`aac5d88`), amendement d'`ADR-0080` signe, `T6` inchange |
| 8 | Prescription de `nadia-prof` sur le calcul pose ("dix items, partie epuisant exactement le vivier") incompatible avec `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` | Pedagogie, trouve par `marc-dev` en executant `S28-4` | `nadia-prof`/`camille-po` (route par `marc-dev`) | Ferme : `nadia-prof` retire l'exigence d'exhaustion dans son verdict (`537a0e1`), reconnait sa prescription fausse |
| 9 | Refus `cp-qlm-vivant-non-vivant` - trois items de matiere inerte repondent "un objet" a une question d'identification, faux au regard du libelle de la ligne | Pedagogie | `lea-dev` (correction), cause reconnue par `nadia-prof` elle-meme | Ferme : correction mot pour mot (`0c383b9`), reaudit **VALIDE SOUS RESERVE** (`e06de4b`), `QLM-CP-01` ferme |
| 10 | Depassement de la borne d'audit de `nadia-prof` (5,25 sur 5,0) pour corriger et reauditer le paquet refuse dans le sprint plutot qu'a s29 | Capacite/gestion | Utilisatrice (arbitrage) | Ferme : `DEC-0076`, executee sans etre rediscutee |
| 11 | Classement de la ligne "situer son lieu de vie a differentes echelles" derriere le palier 2, pose par assimilation et non par examen | Pedagogie, `nadia-prof` se corrige elle-meme | `camille-po` (transmission a l'utilisatrice pour la question de fond) | Partiellement ferme : la ligne sort du palier 2 et est tracee pour s29 (`9c48b9c`) ; la question de fond (lieu de vie fictif vs reel, liee a `DEC-0028`) **reste ouverte**, hors perimetre de s28 |
| 12 | `DEC-0075` (corpus de reproductions d'oeuvres, droits d'usage non instruits) - neuf lignes atteignables bloquees | Produit | `camille-po`/utilisatrice | **Ouvert**, statut `Proposee` inchange, aucune story dans s28 |
| 13 | Deux corrections orthographiques apparues dans le worktree principal pendant la revue de `yanis-qa`, sans qu'il les ait faites | Gouvernance (traçabilite) | Signale par `yanis-qa` lui-meme dans son rapport | Ferme : mesure de `yanis-qa` explicitement datee avant cette modification, aucune ambiguite sur le perimetre mesure |

---

## Avis de `nadia-prof` et Done pedagogique

**Sept verdicts au premier passage, un refus, puis un reaudit sur le lot refuse** (increment joue a
`aac5d88` pour le premier passage, jar reconstruit, sept parties completes de dix manches par
appels HTTP reels ; increment rejoue a `0c383b9` pour le reaudit) :

| Paquet | Verdict | Mouvement de ligne |
|---|---|---|
| `bandit-manchot-vivant-non-vivant-cp` | **REFUSE** (2026-09-04) puis **VALIDE SOUS RESERVE** au reaudit (`VIV-R2`) | `en cours` -> **`couvert-valide`** (`QLM-CP-01`) |
| `bandit-manchot-corps-sens-cp` | **VALIDE SOUS RESERVE** (`COR-R4`, payee et levee le meme jour) | `en cours` -> **`couvert-valide`** (`QLM-CP-03`) |
| `bandit-manchot-etats-eau-cp` | **VALIDE** | `en cours` -> **`couvert-valide`** (`QLM-CP-05`) |
| `bandit-manchot-histoire-geographie-cm1` (composition B) | **VALIDE SOUS RESERVE** (`HG-R4`, `HG-R5`) | deux lignes `en cours` -> **`couvert-valide`** (`HG-CM1-11`, `HG-CM1-13`) ; neuf lignes restent `en cours` |
| `bandit-manchot-anglais-cm1` | **VALIDE SOUS RESERVE** (`ANG-R1`, mot transparent `mountain`) | `LVE-CM1-04` : `non couvert` -> **`couvert-valide`** ; `LVE-CM1-07` : `non couvert` -> `en cours` (s'ouvre, ne ferme pas) |
| `bandit-manchot-multiplication-posee-cm1` | **VALIDE** | `non couvert` -> **`couvert-valide`** (`MAT-CM1-04`) |
| `bandit-manchot-division-posee-cm1` | **VALIDE SOUS RESERVE** (`POS-R1`) | `non couvert` -> **`couvert-valide`** (`MAT-CM1-05`) |

**Huit lignes ferment sur un plafond de huit, une s'ouvre sans fermer** (`LVE-CM1-07`) :
`QLM-CP-01`, `QLM-CP-03`, `QLM-CP-05`, `HG-CM1-11`, `HG-CM1-13`, `LVE-CM1-04`, `MAT-CM1-04`,
`MAT-CM1-05`.

**Le refus et sa cause, ecrite contre elle-meme** : "La cause est chez moi et je l'ecris : mon
arbitrage du 2026-09-03 a autorise l'opposition binaire sans verifier le libelle du pole non-
vivant, que je connaissais. `lea-dev` a applique ma consigne a la lettre. Ma barre d'exactitude n'a
pas bouge, c'est ma prescription qui etait incomplete." (`537a0e1`). Trois items faux -
identification d'un caillou, de sable et de neige comme "un objet" - ont ete **servis en
production** jusqu'a leur correction dans le meme sprint.

**Deux autres corrections contre elle-meme dans le meme verdict** : elle retire l'exigence
d'exhaustion de sa prescription du calcul pose ("Je retire l'exigence d'exhaustion, `marc-dev`
avait pris la seule issue compatible") et confirme que les limites declarees par `lea-dev`
(`REC-5`) lui conviennent ("une garde etroite et dite vaut mieux qu'une garde large et fausse").
Au reaudit (`e06de4b`), elle corrige un chiffre a elle sur `COR-R4` (12 mots et non 11).

**Prix reel du sprint : 5,25 sur une borne de 5**, par l'arbitrage de cloture de l'utilisatrice
(`DEC-0076`) : corriger et reauditer le paquet refuse dans le sprint plutot que laisser trois items
faux servis en production jusqu'a s29. `nadia-prof` avait decline cette composition par ecrit avant
le sprint ; elle a execute l'arbitrage sans le rediscuter.

**Conditions restant sans garde executable**, sur les huit lignes fermees ce sprint : `HIS-2` et
l'exactitude factuelle (`HG-CM1-11`, `HG-CM1-13`), `REC-5`/`REC-6` au sens fin (`LVE-CM1-04`),
`POS-3` (`MAT-CM1-04`, `MAT-CM1-05`), et la verite d'un enonce de matiere inerte (`QLM-CP-01`) - "le
test qui garde la classification du paquet garde des chaines de caracteres, pas un fait", ce que le
refus puis le reaudit demontrent directement (`squad/programme/MATRICE-couverture.md`, section
"Ce que le vert ne prouve pas" apres les verdicts de s28).

**Sept reserves ouvertes pour s29** (aucune perdue) : `HG-R4`, `HG-R5`, `ANG-R1`, `POS-R1`,
`COR-R2`, `EAU-R1`, `VIV-R2`. `POS-R1` signalee en priorite : neuf items sur onze du paquet de
division portent un distracteur au meme quotient que la bonne reponse, et deux se repondent sans
diviser.

### Done pedagogique

**Prononce.** Huit lignes ferment (`en cours` ou `non couvert` -> `couvert-valide`), une s'ouvre
sans fermer. Un refus a ete rendu puis leve dans le sprint, sur arbitrage explicite de
l'utilisatrice de le corriger et le reauditer plutot que de laisser trois items faux en production
jusqu'a s29 - le motif du refus reste ecrit dans la fiche de conformite, non efface.

### Done technique

**Prononce.** `mvn -B clean verify` vert sur le reacteur complet, `b37405d`, **1222 tests, 0 echec**
- confirme independamment par `yanis-qa` (1208 sur `21f3d1d`, avant les portes), par `lea-dev`
(1222 sur `0c383b9`) et par ma propre execution sur l'arbre de cloture.
