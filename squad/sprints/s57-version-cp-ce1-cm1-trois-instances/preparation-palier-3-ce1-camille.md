# s57 - preparation du palier 3 : les lignes CE1 non `couvert-valide` de cinq matieres (`camille-po`)

- **Auteure** : `camille-po`, 2026-10-19 20:26 (UTC+02:00), lu par `date`. Arbre : `round-two` a `ad65827e`, checkout principal, `git status --short` ne montre que `.claude/settings.local.json` (non suivi).
- **Ce fichier n'est pas `planning.md` ni une composition.** Le palier 3 n'est pas ouvert : aucun planning, aucun arbitrage prealable de `nadia-prof`, aucune table d'isolation n'existe sur le disque (`ls squad/sprints/s57-*/ | grep -i palier-3` ne rend que ce fichier). **Je traite comme engages ce que l'utilisatrice m'indique engage** (`S57-GARDE-C1-VIRGULE-ET-IMBRIQUE`, `Z1` et `Z2`, `RL5` a `RL7`, `-06`, `-07`, `-11`) ; le depot ne les porte que comme proposition (`DEC-0120` A.115, *"sans engagement"*). Je ne les deplace pas.
- **Cadre donne par l'utilisatrice, 2026-10-19** : palier 3 de s57 (s57 n'est pas clos, le palier 2 l'est : `cloture-palier-2.md`, section 14) ; perimetre = toutes les lignes CE1 non `couvert-valide` de francais, mathematiques, questionner le monde, EMC et arts plastiques, y compris `en cours` et `non couvrable en l'etat`.
- **Ce que ce fichier ne fait pas** : ne touche ni la matrice, ni `DEC-0120`, ni un paquet, ni du code ; n'engage rien ; ne juge aucun contenu ; ne decide aucun moyen technique. Les prix et les affectations d'instance sont a `nadia-prof` (`DEC-0120` A.33, A.36), les dessins a `theo-archi`.
- **Une correction de cadre** : la borne de 8,0 est **par palier**, pas par sprint (`PAR-9` ; `ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`, 11.3). Un sprint porte 8,0 x n.

## 0. Ce que j'ai mesure

| Mesure | Commande, sur `ad65827e` | Rendu |
|---|---|---|
| Lignes CE1 non `couvert-valide` des cinq matieres | `awk` avec la barre verticale pour separateur, sur les lignes de donnees de la matrice dont la matiere est l'une des cinq et le niveau CE1, colonne `Statut` ne commencant pas par `couvert-valide` | **38** : mathematiques 7, francais 12, questionner le monde 12, arts plastiques 2, EMC 5. Le compte de l'utilisatrice tient |
| Dont `en cours` | meme commande | 2 (`MAT-CE1-02`, `MAT-CE1-09`) |
| Dont `non couvrable en l'etat` | meme commande | 6 (`FRA-CE1-27` a `FRA-CE1-31`, `EMC-CE1-05`) |
| Paquets CE1 presents dans `contenu/` et absents du catalogue | `grep -c "paquet: <nom>$"` sur `catalogue.yaml`, pour les 47 fichiers `ce1-*.yaml` | **12** a 0 : `nombres-jusqu-a-1000`, `masses-contenances`, `calendrier-dates`, `figures-planes`, `deplacements-quadrillage`, `mots-irreguliers`, `mots-invariables`, `inference`, `texte-documentaire`, `negation`, `ordre-des-mots`, `relire-une-phrase` |
| Paquets de questionner le monde CE1 et d'EMC CE1 | `grep -l` de chaque code de ligne sur `contenu/*.yaml`, puis `git grep -n` de chacun des deux prefixes sous `adapters` | **0 fichier** pour `QLM-CE1-01` a `-08`, `-10`, `-12` et `EMC-CE1-01` a `-04` (14 lignes) ; aucune occurrence dans `adapters` |
| Entrees de catalogue | `grep -c "^  - id:" catalogue.yaml` | 152 |
| Cliquet de poids des dessins | `POIDS_TOTAL_MAXIMUM` dans `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest` ; nombre de fichiers suivis sous `static/illustrations` | 69203 ; 56 fichiers (le chiffre de l'avis de `theo-archi` du 2026-10-13, `squad/sprints/inter-s56/avis-faisabilite-sept-dessins.md` : cliquet plein) |

## 1. Les 38 lignes

`Atteignable` se lit : **oui** = un lot peut la fermer en `couvert-valide` sous les regles ecrites ; **oui, `en cours`** = un lot peut la faire passer a `en cours`, jamais plus ; **non** = rien n'est engageable au palier 3 ; **a decider** = depend d'une question de la section 5. Cela ne dit rien de la capacite (section 2).

### 1.1 Mathematiques CE1 (7)

| Ligne | Intitule court | Statut matrice | Jeu ou paquet existant | Obstacle | Atteignable |
|---|---|---|---|---|---|
| `MAT-CE1-01` | nombres jusqu'a 1000 | non couvert | `ce1-nombres-jusqu-a-1000`, 22 items, non servi | *denombrer* exige une collection dessinee : plafond `en cours` (arbitrage s43, lot 1, section 2) | oui, `en cours` |
| `MAT-CE1-02` | comparer, ranger, encadrer ; ligne graduee | en cours | `bandit-manchot-comparer-ranger-ce1`, servi, moitie textuelle VALIDE | `couvert-valide` exige une ligne graduee dessinee servie et jugee (`ORD-CE1-6`) ; la moitie designation n'est pas engagee. Plafond deja atteint | non |
| `MAT-CE1-09` | longueurs, regle graduee | en cours | `bandit-manchot-longueurs-ce1`, servi, part servie VALIDE | *mesurer avec une regle graduee* ne se sert pas par du texte ; aucune image de regle. Plafond deja atteint | non |
| `MAT-CE1-10` | masses et contenances | non couvert | `ce1-masses-contenances`, 21 items, non servi | *mesurer* exige une balance ou un recipient grade rendu : plafond `en cours` (arbitrage s43, lot 2, section 3) | oui, `en cours` |
| `MAT-CE1-11` | heure et calendrier | non couvert | `ce1-calendrier-dates`, 25 items, non servi | cadran non rendu ; les mois sont servis par `QLM-CP-13` : plafond `en cours` (arbitrage s43, lot 2, section 4) | oui, `en cours` |
| `MAT-CE1-14` | quadrillage, deplacement | non couvert | `ce1-deplacements-quadrillage`, 20 items, non servi | paquet textuel : plafond `en cours` par ses propres conditions ; `couvert-valide` exige un quadrillage montre en designation et `A3` d'`ADR-0080`, non ecrit. `DEC-0120` A.35 la laisse hors du rang 4a, et elle n'est pas dans les six lignes plafonnees d'A.32 | a decider (Q-05) |
| `MAT-CE1-15` | figures planes | non couvert | `ce1-figures-planes`, 20 items, non servi | *tracer* : aucune mecanique ne recoit un trace : plafond `en cours` (arbitrage s43, lot 3, section 2) | oui, `en cours` |

### 1.2 Francais CE1 (12)

| Ligne | Intitule court | Statut matrice | Jeu ou paquet existant | Obstacle | Atteignable |
|---|---|---|---|---|---|
| `FRA-CE1-03` | mots frequents et irreguliers | non couvert | `ce1-mots-irreguliers`, 20 items, non servi | *reconnaitre sans dechiffrer* est une vitesse que le produit ne mesure pas : plafond `en cours`, aucune levee connue (`ARBITRAGE-COMPLEMENT-s55-instance-a.md`, section 3). Hors des groupes X et Y de `DEC-0120` section 1 | oui, `en cours` |
| `FRA-CE1-06` | inference simple | non couvert | `ce1-inference`, 20 items, non servi | aucun. Groupe X, **engage** (A.115 rang 3) | oui |
| `FRA-CE1-07` | texte documentaire | non couvert | `ce1-texte-documentaire`, 20 items, non servi | aucun. Groupe X, **engage** ; `nadia-prof` joue chaque item texte masque (arbitrage palier 2, 6.2) | oui |
| `FRA-CE1-08` | remettre les mots dans l'ordre | non couvert | `ce1-ordre-des-mots`, 20 items, non servi, en-tete `DEGRADE` (choix parmi trois phrases) | **condition 4 de `nadia-prof`** : *"`-08` et `-09` ne sont pas des lots de s57, quelle que soit sa duree"* (11.3). Groupe Y, sans cousine servie | non (Q-07) |
| `FRA-CE1-09` | relire une phrase, reperer l'erreur | non couvert | `ce1-relire-une-phrase`, 20 items, non servi, en-tete `DEGRADE` (nomme l'erreur, ne la corrige pas) | meme condition 4. La dependance a `-15` est levee (`FRA-CE1-15` `couvert-valide`) | non (Q-07) |
| `FRA-CE1-11` | phrase negative | non couvert | `ce1-negation`, 24 items, non servi | aucun. Groupe X, **engage** ; biais de longueur de `TRV-CE1-2` recompte par `nadia-prof` | oui |
| `FRA-CE1-21` | mots invariables | non couvert | `ce1-mots-invariables`, 24 items, non servi, en-tete `DEGRADE` | l'ecrire sans modele exige la dictee ou la saisie alphabetique au CE1, non arbitrees : plafond `en cours`. Hors des groupes X et Y | oui, `en cours` (Q-08, Q-09) |
| `FRA-CE1-27` | ecouter un texte lu, s'exprimer | non couvrable en l'etat | aucun | *ecouter* : nature reversible (la voix est celle de l'adulte, premisse arbitree pour le CP seul) ; *s'exprimer* : irreversible | non (Q-08) |
| `FRA-CE1-28` | lire a voix haute | non couvrable en l'etat | aucun | nature irreversible : aucun dispositif ne verifie ce que l'enfant prononce | non |
| `FRA-CE1-29` | dictee | non couvrable en l'etat | aucun | nature reversible : la voix de l'adulte ; l'etendre au CE1 est une decision de l'utilisatrice | non (Q-08) |
| `FRA-CE1-30` | copier un texte | non couvrable en l'etat | aucun | contrainte fondatrice : `DEC-0046` a ecarte tout geste de trace | non |
| `FRA-CE1-31` | produire un ecrit court | non couvrable en l'etat | aucun | nature irreversible : jugement humain sur une production libre (`ADR-0008`) | non |

### 1.3 Questionner le monde CE1 (12)

Instruction faite le 2026-10-13 (`ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`), hors des 8,0, 16 lignes acceptees sous conditions. **Aucune des 12 n'a de paquet.**

| Ligne | Intitule court | Statut matrice | Jeu ou paquet existant | Obstacle | Atteignable |
|---|---|---|---|---|---|
| `QLM-CE1-01` | etapes d'un developpement | non couvert | aucun | paquet a ecrire ; conditions `DVP-CE1-1`, `DVP-CE1-2` | oui |
| `QLM-CE1-02` | animal et ce qu'il mange | non couvert | aucun | paquet a ecrire ; `RAL-CE1-1`, `RAL-CE1-2` | oui |
| `QLM-CE1-03` | besoins d'une plante | non couvert | aucun | paquet a ecrire ; `BPL-CE1-1`, `BPL-CE1-2` | oui |
| `QLM-CE1-04` | familles d'aliments, repas varie | non couvert | aucun | paquet a ecrire ; `FAL-CE1-1`, `FAL-CE1-2`, `BIA-4` | oui |
| `QLM-CE1-05` | l'eau sous trois etats | non couvert | aucun | paquet a ecrire ; `ETE-CE1-1`, `ETE-CE1-2` | oui |
| `QLM-CE1-06` | ce qu'un aimant attire | non couvert | aucun | paquet a ecrire ; `AIM-CE1-1`, `AIM-CE1-2` | oui |
| `QLM-CE1-07` | circuit electrique simple | non couvert | aucun | moitie textuelle seule (`CIR-CE1-1`) : plafond `en cours` ; la moitie dessinee (`CIR-CE1-2`) exige un circuit dessine, absent | oui, `en cours` (dessin : Q-06) |
| `QLM-CE1-08` | dangers de l'electricite | non couvert | aucun | paquet a ecrire ; `DEL-CE1-1`, `DEL-CE1-2` | oui |
| `QLM-CE1-09` | plan et legende | non couvert | aucun | aucun plan dessine ; forme degradee (`PLG-CE1-2`) `en cours` au mieux, forme fidele demande un `ADR` ; terrain neuf (`PAR-13`) | non (Q-06) |
| `QLM-CE1-10` | paysages | non couvert | aucun | paquet a ecrire ; `PAY-CE1-1`, `PAY-CE1-2` | oui |
| `QLM-CE1-11` | continents et oceans | non couvert | aucun | aucun planisphere ; rien sans dessin ; terrain neuf (`PAR-13`) | non (Q-06) |
| `QLM-CE1-12` | generations et evenements d'une vie | non couvert | aucun | paquet a ecrire ; `GEN-CE1-1`, `GEN-CE1-2`, `BIA-4` | oui |

### 1.4 Arts plastiques CE1 (2)

| Ligne | Intitule court | Statut matrice | Jeu ou paquet existant | Obstacle | Atteignable |
|---|---|---|---|---|---|
| `ART-CE1-01` | reconnaitre comment une oeuvre est faite | non couvert | aucun | instruction non faite ; corpus : aucun dessin ni photographie parmi les 12 reproductions ; un corpus neuf pose la question des droits (`ART-3`, route a `theo-archi`) ; terrain neuf. Aucun plafond ecrit | non (Q-10) |
| `ART-CE1-02` | comparer deux oeuvres d'un meme sujet | non couvert | aucun | instruction non faite : elle attendait le verdict du rejeu d'`ART-CP-10`, prononce le 2026-10-16 (`VALIDE`) ; corpus sans deux oeuvres d'un meme sujet faites de deux facons ; terrain neuf. Aucun plafond ecrit | non (Q-10) |

### 1.5 EMC CE1 (5)

Meme instruction du 2026-10-13. **Aucun paquet.**

| Ligne | Intitule court | Statut matrice | Jeu ou paquet existant | Obstacle | Atteignable |
|---|---|---|---|---|---|
| `EMC-CE1-01` | biens de chacun, biens de tous | non couvert | aucun | paquet a ecrire ; `BCO-CE1-1`, `BCO-CE1-2` | oui |
| `EMC-CE1-02` | regle et sanction | non couvert | aucun | paquet a ecrire ; `RSA-CE1-1`, `RSA-CE1-2` | oui |
| `EMC-CE1-03` | droits de l'eleve et devoirs | non couvert | aucun | paquet a ecrire ; `DDE-CE1-1`, `DDE-CE1-2` | oui |
| `EMC-CE1-04` | interet d'un seul, interet de tous | non couvert | aucun | paquet a ecrire ; `INT-CE1-1`, `INT-CE1-2` | oui |
| `EMC-CE1-05` | cooperer dans un groupe | non couvrable en l'etat | aucun | nature irreversible : une conduite, pas une connaissance (`CON-1`) | non |

### 1.6 Bilan

| Atteignable | Nombre | Lignes |
|---|---|---|
| oui | 16 | `FRA-CE1-06`, `-07`, `-11` (engagees) ; `QLM-CE1-01`, `-02`, `-03`, `-04`, `-05`, `-06`, `-08`, `-10`, `-12` ; `EMC-CE1-01` a `-04` |
| oui, `en cours` | 7 | `MAT-CE1-01`, `-10`, `-11`, `-15` ; `FRA-CE1-03`, `-21` ; `QLM-CE1-07` |
| a decider | 1 | `MAT-CE1-14` |
| non | 14 | `MAT-CE1-02`, `-09` ; `FRA-CE1-08`, `-09`, `-27` a `-31` ; `QLM-CE1-09`, `-11` ; `ART-CE1-01`, `-02` ; `EMC-CE1-05` |

16 + 7 + 1 + 14 = 38. Les 6 lignes `non couvrable en l'etat` sont dans les 14. Les lignes `non couvrable` ne bougent que par une decision de l'utilisatrice (matrice, "Comment la lire") : seules `FRA-CE1-27` et `-29` sont de nature reversible (Q-08).

## 2. Capacite du palier 3 apres l'engage

Bornes de `nadia-prof`, reprises sans calcul propre : 8,0 par palier (`PAR-9`) ; 5,0 et trois lots au plus par instance (`PAR-8`) ; arbitrage prealable 0,5 par instance ouverte ; marge de rejeu 0,5 par instance qui porte un lot ou un rejeu ; lot neuf textuel 1,0 (bareme ; `+0,5` pour un lot illustre) ; aucun chiffre au-dela de 8,0. Les marges inutilisees du palier 2 (1,5) et son non affecte (0,25) sont tombes (`DEC-0120` A.114).

| Poste | Instance | Prix |
|---|---|---|
| arbitrage prealable | A | 0,5 |
| marge de rejeu | A | 0,5 |
| `-06`, `-07`, `-11` | A | 3,0 |
| **Total A** | | **4,0** sur 5,0 ; **trois lots : la borne de lots est atteinte** |
| `S57-GARDE-C1-VIRGULE-ET-IMBRIQUE` (`Z1`, `Z2`), `S57-GARDE-PILE-DE-POLICES`, `RL5` a `RL7`, `S57-GARDE-NOMBRE-DE-MANCHES` | hors `PAR` | 0 |
| **Reste pour A' et B** | | **4,0** |

**Ce que 4,0 permet**, sans le depasser :

| Cas | Instances ouvertes en plus de A | Cout fixe | Lots possibles | Total |
|---|---|---|---|---|
| (a) | B seule | 1,0 | **3** a 1,0 | 8,0, aucun non affecte |
| (b) | B et A' | 2,0 | **2** a 1,0 | 8,0 |
| (c) | B seule, deux lots | 1,0 | 2 | 7,0, 1,0 non affecte |

Un quatrieme lot dans une instance est refuse par `PAR-8`. **Au plus 3 lots neufs s'ajoutent aux 3 de A au palier 3**, soit 6 lots sur les 23 lots de la section 3 (24 avec M5). Aucune des 17 autres lignes atteignables n'entre au palier 3 ; c'est une arithmetique des bornes, pas un report choisi.

**Vers l'arriere** : a deux instances de 3 lots, un palier porte 6 lots (4,0 + 4,0). Apres le palier 3 il resterait 17 lots : au moins trois paliers de plus, si rien d'autre n'entre. Ordre de grandeur de bornes, pas un engagement.

**Reports** : `-06`, `-07`, `-11` en sont a leur deuxieme ecartement ; `-06` hors du palier 3 serait le troisieme, et une `DEC` le dirait (`composition-palier-2.md`, section 9). Tous les autres lots ci-dessous sont a leur **premiere inscription** dans une composition.

## 3. Les lots proposes

Un lot = un paquet de contenu YAML sur la mecanique `bandit-manchot`, `type: question`, sans texte support, sans modification de mecanique. Les lots hors X ci-dessous (20, ou 21 avec M5) sont **tous un paquet pour une ligne** : chaque paquet declare une seule ligne dans `lignesDeReferentiel`. Une story par lot, nommee `S57-BR-<ligne>` sur le modele de `S57-BR-FRA-CE1-24`. **Je n'ecris aucune story avant les reponses de la section 5** (`A.115` : elles s'ecrivent a l'ouverture du palier, avant l'arbitrage). `nadia-prof` n'arbitre pas un lot sans story.

**Criteres communs, repris de K1 a K10 du backlog (palier 2)** avec `<nom>` = le paquet du lot : catalogue (`grep -c "paquet: <nom>$"` : 0 aujourd'hui, 1 apres), test de paquet et fixtures, aucun garde-fou assoupli, jobs de `ci.yml` nommes, rapport du developpeur, matrice intacte au branchement, fiche ouverte apres le commit de branchement, garde de l'accent intacte. **Plus, pour les lots de paquet neuf** : `grep -l 'lignesDeReferentiel: \["<LIGNE>"\]' adapters/adapter-content/src/main/resources/contenu/*.yaml` rend **0 fichier aujourd'hui** (mesure du 0, pour les 14 lignes de la section 0) et 1 apres ; au moins 20 items (`grep -c 'bonneReponse:'`, plancher de `TRV-CE1-7`). **Plus, pour les 10 lots de questionner le monde** (`QMT-CE1-1`) : `grep -ciE 'herbivore|carnivore|omnivore|reproduction|vieillissement|fusion|solidification|vaporisation|évaporation|condensation|conducteur|isolant|larve|plantule|légumineuse'` sur les lignes `enonce:`, `distracteurs:`, `bonneReponse:` et `indice:` du paquet rend 0.

**Ce que les criteres ne gardent pas** et que `nadia-prof` garde seule (ses mots, section 7 de l'instruction) : `NRC-CE1-1` (aucune reprise d'un item de CP), l'exactitude des faits, `DDE-CE1-2`, `RSA-CE1-1`, `INT-CE1-1`, `GEN-CE1-2`, `CON-1`. Chaque critere ci-dessous reprend un mot ecrit de sa condition, et rien de plus ; si l'un lui parait mal formule, c'est elle qui le dit.

### 3.1 Les lots engages, non redefinis

| Lot | Ligne | Paquet | Prix | Instance |
|---|---|---|---|---|
| X1 | `FRA-CE1-06` | `ce1-inference` (20) | 1,0 | A |
| X2 | `FRA-CE1-07` | `ce1-texte-documentaire` (20) | 1,0 | A |
| X3 | `FRA-CE1-11` | `ce1-negation` (24) | 1,0 | A |

Leurs stories sont a ecrire a l'ouverture (`A.115`). Ordre de `nadia-prof` : `-06`, `-07`, `-11`.

### 3.2 EMC CE1 : quatre lots, `couvert-valide` (paquets a ecrire)

| Lot | Ligne | Critere propre, une ligne | Prealable |
|---|---|---|---|
| E1 | `EMC-CE1-01` | aucune ligne `bonneReponse:` ne contient `ranger` ni `remett` (`BCO-CE1-1`) : le `grep -ci` de ces deux mots sur ces lignes rend 0 | paquet a ecrire |
| E2 | `EMC-CE1-02` | `méchant` et `vilain` n'apparaissent dans aucun `enonce:`, `distracteurs:` ni `bonneReponse:` (`RSA-CE1-2`) : 0 | paquet a ecrire |
| E3 | `EMC-CE1-03` | le commentaire du paquet liste les couples fermes (`DDE-CE1-1`) et chaque couple est servi dans les deux sens : au moins un item donne le droit et demande le devoir, au moins un donne le devoir et demande le droit | paquet a ecrire |
| E4 | `EMC-CE1-04` | `intérêt général` et `bien commun` n'apparaissent dans aucun texte lu par l'enfant (`INT-CE1-2`) : 0 | paquet a ecrire |

### 3.3 Questionner le monde CE1 : dix lots (paquets a ecrire)

| Lot | Ligne | Plafond | Critere propre, une ligne | Prealable |
|---|---|---|---|---|
| Q1 | `QLM-CE1-01` | `couvert-valide` | aucun `enonce:` ne contient `première` ni `dernière` (`DVP-CE1-1`) : 0 ; la liste fermee des especes et des etapes est dans le commentaire du paquet avec sa source (`QMT-CE1-2`) | paquet a ecrire |
| Q2 | `QLM-CE1-02` | `couvert-valide` | `chat`, `chien`, `renard`, `hérisson` n'apparaissent dans aucun `enonce:` ni aucune proposition (`RAL-CE1-2`) : 0 | paquet a ecrire |
| Q3 | `QLM-CE1-03` | `couvert-valide` | `eau`, `lumière` et `chaleur` sont chacun `bonneReponse:` au moins une fois, et `air`, `terre`, `engrais`, `sels` ne sont jamais proposes (`BPL-CE1-1`) | paquet a ecrire |
| Q4 | `QLM-CE1-04` | `couvert-valide` | la pomme de terre a `féculents` pour `bonneReponse:` au moins une fois, et `lentilles`, `pois chiches`, `maïs`, `avocat`, `olives`, `pizza`, `sandwich`, `gratin` n'apparaissent nulle part (`FAL-CE1-1`) | paquet a ecrire |
| Q5 | `QLM-CE1-05` | `couvert-valide` | `vapeur d'eau` est `bonneReponse:` dans au moins un tiers des items (`ETE-CE1-1`), et `buée`, `nuage`, `brouillard` n'apparaissent ni en `bonneReponse:` ni en `indice:` (`ETE-CE1-2`) | paquet a ecrire |
| Q6 | `QLM-CE1-06` | `couvert-valide` | au moins un quart des items ont pour cible un objet metallique que l'aimant n'attire pas (`AIM-CE1-2`) ; `pièce`, `canette`, `clé`, `couvert`, `casserole`, `bijou` n'apparaissent nulle part (`AIM-CE1-1`) | paquet a ecrire |
| Q7 | `QLM-CE1-07` | **`en cours`** | les quatre elements (pile, ampoule, fils, interrupteur) sont chacun `bonneReponse:` au moins une fois (`CIR-CE1-1`) ; apres le verdict la cellule de matrice dit `en cours` et reprend mot pour mot : *"nommer les elements d'un circuit seuls ; dire si un circuit dessine fait briller l'ampoule n'est pas servi"* | paquet a ecrire ; `+1` au compteur de version seulement |
| Q8 | `QLM-CE1-08` | `couvert-valide` | `pile` n'apparait dans aucun texte du paquet (`DEL-CE1-1`) et aucun item ne contient `électrocut`, `brûl` ni `mort` (`DEL-CE1-2`) : 0 | paquet a ecrire |
| Q9 | `QLM-CE1-10` | `couvert-valide` | les quatre paysages (littoral, montagne, campagne, ville) sont chacun `bonneReponse:` au moins une fois (`PAY-CE1-1`) ; `plus beau`, `plus calme`, `plus dur`, `plus pauvre` n'apparaissent nulle part (`PAY-CE1-2`) | paquet a ecrire |
| Q10 | `QLM-CE1-12` | `couvert-valide` | les generations et les evenements d'une vie sont chacun `bonneReponse:` au moins une fois (`GEN-CE1-1`) | paquet a ecrire |

### 3.4 Mathematiques et francais CE1 plafonnes `en cours` : six lots et un a decider (paquets prets)

Chacun fait gagner **une ligne au compteur de version** (`DEC-0120` A.32 : `en cours` avec les cinq criteres de `nadia-prof`) et **zero au `couvert-valide`** de la matrice. Critere commun : apres le verdict, la cellule dit `en cours`, jamais `couvert-valide`, et porte mot pour mot la mention de couverture partielle de `nadia-prof` (arbitrage s43, lots 1 a 3 ; `ARBITRAGE-COMPLEMENT-s55-instance-a.md`, section 3).

| Lot | Ligne | Paquet, items | Critere propre, une ligne | Prealable |
|---|---|---|---|---|
| M1 | `MAT-CE1-01` | `ce1-nombres-jusqu-a-1000`, 22 | `grep -c "paquet: ce1-nombres-jusqu-a-1000$" catalogue.yaml` rend 0 aujourd'hui, 1 apres | paquet pret ; story a ecrire |
| M2 | `MAT-CE1-10` | `ce1-masses-contenances`, 21 | idem pour `ce1-masses-contenances` | paquet pret |
| M3 | `MAT-CE1-11` | `ce1-calendrier-dates`, 25 | idem pour `ce1-calendrier-dates` | paquet pret |
| M4 | `MAT-CE1-15` | `ce1-figures-planes`, 20 | idem pour `ce1-figures-planes` | paquet pret |
| M5 | `MAT-CE1-14` (Q-05) | `ce1-deplacements-quadrillage`, 20 | idem pour `ce1-deplacements-quadrillage` ; la ligne n'est pas dans les six lignes plafonnees d'A.32 : le compteur ne la compte qu'avec un plafond ecrit de `nadia-prof` qui l'y autorise | paquet pret ; **a decider** |
| F1 | `FRA-CE1-03` | `ce1-mots-irreguliers`, 20 | idem pour `ce1-mots-irreguliers` | paquet pret ; instance a dire (hors X et Y) |
| F2 | `FRA-CE1-21` | `ce1-mots-invariables`, 24 | idem pour `ce1-mots-invariables` | paquet pret ; instance a dire ; plafond releve seulement par Q-08 ou Q-09 |

Les mesures *0 aujourd'hui* sont celles de la section 0 (12 paquets a 0). Les sept paquets de ce tableau sont a 0 : rouges sur l'existant, comme voulu. Avec Q7 (section 3.3), les lots plafonnes `en cours` sont sept, hors M5.

## 4. Ordre de priorite

**Ce qui ne bouge pas** : `Z1` et `Z2` (rang 1), puis `RL5` a `RL7`, avant tout autre commit de code et avant le premier jar rejoue ; puis X1, X2, X3 (3,0 sur A).

**Pour les 3 places restantes (section 2), mon ordre, sous reserve de Q-01 et Q-02** :

1. **EMC CE1, E1 a E4.** Motifs : (1) quatre `couvert-valide` sur quatre lignes atteignables, ce qui ferme la matiere au CE1 ; (2) cousines servies au CP et au CM1 (instruction, section 5.1) ; (3) un seul terrain (`PAR-2`, section 5.3 : l'EMC CE1 va a l'instance qui porte l'EMC). Trois au palier 3 (E1, E2, E3), E4 au palier 4. L'ordre interne est a `nadia-prof` : aucun critere de produit ne departage quatre lots a 1,0.
2. **Questionner le monde, Q1 a Q6, Q8 a Q10.** Neuf `couvert-valide`. Ordre du referentiel, faute d'autre critere de produit.
3. **Les sept lots plafonnes `en cours` (M1 a M4, F1, F2, Q7), puis M5 si Q-05 le veut.** Un gain au compteur chacun, aucun au `couvert-valide`.
4. **Pas avant les reponses** : `QLM-CE1-09`, `-11`, `QLM-CE1-07` moitie dessinee, `MAT-CE1-02`, `-09` (dessins, Q-06) ; `FRA-CE1-08`, `-09` (Q-07) ; `ART-CE1-01`, `-02` (Q-10) ; `FRA-CE1-27` et `-29` (Q-08).

**Ecart avec `DEC-0120` A.35, a decider par l'utilisatrice (Q-02).** A.35 range les quatre lots de mathematiques (rang 4a) avant questionner le monde et l'EMC (rang 5), au motif que le rang 5 n'avait ni instruction ni prix. Les deux motifs sont leves : instruction faite le 2026-10-13, prix de 1,0 ecrit (`ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`, 6.2) ; A.35 renvoyait l'ordre du rang 5 a ce prix. Mon ordre ci-dessus met donc le rang 5 avant le rang 4a. **Je ne l'applique pas seule** : c'est une inversion d'un rang `Acceptee`, qui demande un amendement de `DEC-0120` apres la reponse. Le compromis de calendrier est en Q-01 : les paquets de mathematiques existent, ceux de questionner le monde et d'EMC non.

## 5. Questions a l'utilisatrice

Sans reponse supposee. Chacune dit ce qu'elle debloque.

| Code | Question | Ce que cela debloque |
|---|---|---|
| Q-01 | Les paquets de questionner le monde et d'EMC n'existent pas (0 fichier). Doivent-ils etre ecrits **avant** l'ouverture du palier 3, hors palier comme la session de contenu de s53, ou **dans** le palier ? Sans paquet ecrit avant l'arbitrage de `nadia-prof`, ces lignes ne sont pas un lot (`A.115` : *"une ligne sans paquet instruit n'est pas un lot"*) ; je lis cette phrase ainsi et `nadia-prof` la confirme | quels lots peuvent occuper les trois places du palier 3 : sans paquet ecrit avant, seuls M1 a M5, F1 et F2 le peuvent |
| Q-02 | `DEC-0120` A.35 place les lots de mathematiques (4a) avant questionner le monde et l'EMC (5). Maintenir cet ordre, l'inverser comme en section 4, ou panacher ? Fait a connaitre : un lot de mathematiques donne `en cours`, un lot d'EMC ou de questionner le monde donne `couvert-valide` et `en cours` au compteur pour le meme prix de 1,0 | l'ordre des trois places, et l'amendement de `DEC-0120` que j'ecris apres la reponse |
| Q-03 | Quelles instances le palier 3 ouvre-t-il en plus de A : B seule (3 lots), ou B et A' (2 lots) ? A' n'a plus de lot de francais CE1 a porter : le groupe Y n'a plus que `-08` et `-09`, exclus (Q-07). Un lot qui change de groupe est refuse (condition 1 de `nadia-prof`) | le cas (a) ou (b) de la section 2 ; l'affectation reste a `nadia-prof` |
| Q-04 | Les sept lots plafonnes `en cours` (7,0 au total, un gain au compteur chacun, zero au `couvert-valide`) valent-ils leur prix au palier 3, plus tard, ou pas avant qu'un plafond se leve ? | la place de M1 a M4, F1, F2, Q7 |
| Q-05 | `MAT-CE1-14` : prendre le lot textuel (`ce1-deplacements-quadrillage`, plafond `en cours`) maintenant, attendre `A3` d'`ADR-0080` et un quadrillage dessine pour viser `couvert-valide`, ou ne pas la prendre ? Elle n'est pas dans les six lignes plafonnees d'`A.32` : la prendre appelle un plafond ecrit de `nadia-prof` qui l'autorise a compter | M5 |
| Q-06 | Voulez-vous qu'un dessin soit produit : circuit (`QLM-CE1-07`, seconde moitie), plan a legende (`-09`), planisphere (`-11`), ligne graduee (`MAT-CE1-02`), regle graduee (`MAT-CE1-09`) ? Faits : `theo-archi` juge les trois premiers faisables en designation (avis du 2026-10-13) ; chacun est un terrain neuf (`PAR-13`) : une seule ligne a dessin par sprint, et pas tant qu'A' existe ; le cliquet de poids des dessins est plein (69203) ; aucun prix n'est ecrit ; la forme fidele de la legende demande un `ADR` | l'ouverture des quatre lignes de dessin, ou leur retrait de ce perimetre |
| Q-07 | `FRA-CE1-08` et `-09` : la condition 4 de `nadia-prof` les exclut de tout palier de s57, motif ecrit *"B rejoue `ART-CP-10`"*. Ce rejeu est fait (`VALIDE`, 2026-10-16). Demandez-vous a `nadia-prof` de relire sa condition pour le palier 3, ou la condition tient pour tout s57 ? Les deux paquets existent (20 items chacun) mais sont en forme `DEGRADE` | deux lignes, et le groupe Y |
| Q-08 | La premisse *"l'enfant de CP est toujours accompagne"* (`DEC-0046`, `DEC-0068`) vaut pour le CP seul. L'etendez-vous au CE1 pour la dictee (`FRA-CE1-29`) et l'ecoute d'un texte lu (`FRA-CE1-27`) ? C'est la seule voie qui rende ces deux lignes couvrables, et elle releverait aussi le plafond de `FRA-CE1-21` | `FRA-CE1-27`, `-29`, plafond de `-21` |
| Q-09 | Une saisie alphabetique au CE1 (`FRA-CE1-21`) : demandez-vous a `nadia-prof` l'arbitrage qu'elle dit ne pas avoir ecrit (`ARBITRAGE-COMPLEMENT-s55-instance-a.md`, section 3, *"je ne peux pas en fixer le prix aujourd'hui"*) ? | plafond de `FRA-CE1-21`, independamment de Q-08 |
| Q-10 | Arts plastiques CE1 : demandez-vous l'instruction d'`ART-CE1-02` (possible depuis le verdict d'`ART-CP-10`) et d'`ART-CE1-01` a `nadia-prof` ? Aucun corpus ne les sert et un corpus de dessins et de photographies pose la question des droits (`ART-3`, a `theo-archi`) ; tous deux sont un terrain neuf | l'entree des deux dernieres lignes dans le perimetre, ou leur retrait |

## 6. Routage, sans decision

| A qui | Quoi |
|---|---|
| `sofia-sm` | l'engagement et le planning du palier 3 ; la table d'isolation ; les developpeurs des paquets a ecrire (14 paquets, Q-01) |
| `nadia-prof` | la capacite et les prix du palier 3 ; l'affectation des lots aux instances (dont F1 et F2, hors X et Y) ; l'ordre interne de E1 a E4 et de Q1 a Q10 ; l'instruction des deux lignes d'arts plastiques si Q-10 |
| `theo-archi` | `A3` d'`ADR-0080` (Q-05) ; les dessins (Q-06) ; les droits d'un corpus (Q-10) |
| `camille-po` | les stories des lots retenus, a l'ouverture ; l'amendement de `DEC-0120` si Q-02 inverse A.35 ; la matrice, apres chaque verdict de `nadia-prof` |

## 7. Jobs de `ci.yml`

Ce fichier ne touche ni code ni contenu. Lance par moi, fichier present dans l'arbre, avant tout commit (il n'y en a pas) :

- **`chemins`** : `verifier-chemins.sh` sur ce fichier, sortie 0 (*"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*). `verifier-chemins-test.sh` non relance.
- **`build`, partiel** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test`, restreint a `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` et `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (perimetre : `bootstrap` et ses amonts) : `BUILD SUCCESS`, 15 tests (5 et 10), 0 echec. Ni `mvn -B clean verify` de la racine ni l'etape de package du fat jar ne sont lances.
- **`prefixes`** et **`outils`** : non lances. Aucun commit n'est fait.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.

## 8. Proposition de composition du palier 3 (CE1), 2026-10-19

Ajoutee apres `arbitrage-nadia-preparation-palier-3-ce1.md` (2026-10-19, 20:42) et les reponses de l'utilisatrice : `Q-01` paquets de questionner le monde et d'EMC ecrits dans le palier ; `Q-02` ordre de `DEC-0120` A.35 maintenu (mathematiques avant questionner le monde et EMC) ; `Q-03` B et A' ouvrent ; `Q-04` lots plafonnes `en cours` pris. **C'est une proposition pour la composition, pas la composition** : `sofia-sm` la fait ; `nadia-prof` affecte et prix (A.33, A.36). Les sections 3 et 4 ci-dessus sont perimees par ces reponses sur les points dits ici ; je ne les reecris pas.

### 8.1 Choix entre (i) et (ii) de `nadia-prof` (arbitrage, 2.2)

**Je retiens (i).** Motifs :

1. **`Q-03`** : B et A' ouvrent. (ii) laisse A' sans lot, donc sans arbitrage ecrit (*"je n'ecris pas d'arbitrage pour une instance sans lot ni rejeu"*) : une instance ouverte de nom, ce que l'utilisatrice n'a pas dit. (i) la fait travailler.
2. **`Q-02` et A.35** : le rang 4a (mathematiques) passe avant le rang 5 (EMC, questionner le monde). (i) l'applique sur la premiere place : un lot de mathematiques, sur B. La seconde place ne peut pas etre un second lot de mathematiques sur A' sans couper la matiere (`PAR-2`, A.36). Le seul autre emplacement d'un second lot de mathematiques est B, et c'est (ii).
3. **Ce que (i) coute au regard de `Q-02`** : `MAT-CE1-10` et `-11` (rang 4a) sont passees par un lot d'EMC (rang 5). Cette entorse a la lettre d'A.35 vient de `Q-03` lu avec `PAR-2`, pas d'une inversion d'ordre voulue : **A.35 n'est pas amende**, aucun amendement de `DEC-0120` n'est a ecrire sur ce point. Si l'utilisatrice lit `Q-02` a la lettre jusqu'a la derniere place, (ii) est la composition et A' ne s'ouvre pas : c'est a elle de dire laquelle de ses deux reponses cede.
4. **(i) affecte tout** : 8,0, rien de non affecte, cinq lots. (ii) laisse 1,0 qui tombe a la cloture.

### 8.2 Composition proposee

| Instance | Postes | Somme | Lots |
|---|---|---|---|
| A | arbitrage 0,5 ; marge 0,5 ; `FRA-CE1-06`, `-07`, `-11` (X1, X2, X3) 3,0 | 4,0 | 3 sur 3 |
| B | arbitrage 0,5 ; marge 0,5 ; **M1, `MAT-CE1-01`**, 1,0 | 2,0 | 1 sur 3 |
| A' | arbitrage 0,5 ; marge 0,5 ; **E1, `EMC-CE1-01`**, 1,0 | 2,0 | 1 sur 3 |
| **Palier** | | **8,0** sur 8,0 | 5 lots ; `PAR-8` et `PAR-9` tenus |

Affectation de la matiere : mathematiques CE1 a B, EMC CE1 a A', comme `nadia-prof` le pose (arbitrage, 2.2). La fusion de l'amendement d'`ADR-0065` precede le premier worktree d'A' (A.36) ; la question de `theo-archi` sur A' qui ne porterait que du francais reste ouverte (arbitrage, section 6).

**Ordre.** Hors lots : `Z1`, `Z2`, puis `RL5` a `RL7`, avant tout autre commit de code. Puis X1, X2, X3 sur A, **M1 sur B (rang 4a)**, **E1 sur A' (rang 5)**. Dans A, l'ordre de `nadia-prof` : `-06`, `-07`, `-11`.

### 8.3 Pourquoi M1, `MAT-CE1-01`

- **Seul lot de mathematiques recevable dont la mention est deja ecrite** : forme textuelle, plafond `en cours` (arbitrage, 1.2), mention de `NUM-CE1-2` mot pour mot (4.3). `MAT-CE1-10` et `-11` exigent une mention que `nadia-prof` vient d'ecrire et que je dois porter dans une cellule : possible, mais plus de mots a tenir pour un gain identique.
- **Premier de l'ordre d'A.35** (`-01`, `-10`, `-11`, `-15`), sans critere de produit qui departage.
- **Paquet pret** (`ce1-nombres-jusqu-a-1000`, 22 items, ecrit a s53 ; `theo-archi` : *"oui pour lire, ecrire, decomposer"*), sans dessin, sans code neuf.
- `MAT-CE1-15` est hors choix : `nadia-prof` refuse sa forme textuelle (arbitrage, 1.2) et sa forme illustree coute 1,5, d'ou 8,5 en (i). `MAT-CE1-14` aussi, pour `DEP-CE1-2`.
- Un lot `en cours` : `Q-04` l'admet. Gain : une ligne au compteur de version (A.32), zero au `couvert-valide`.

### 8.4 Pourquoi E1, `EMC-CE1-01`

- Premier de l'ordre interne de `nadia-prof` (arbitrage, 1.4) : son recouvrement avec le CP (*ranger*) dit des le premier paquet si `NRC-CE1-1` tient en EMC.
- `Q-01` : le paquet s'ecrit dans le palier. Plafond `couvert-valide` : une ligne de matrice, une ligne au compteur.
- Gain du palier 3 sous (i), si les Done tombent : `FRA-CE1-06`, `-07`, `-11` et `EMC-CE1-01` au `couvert-valide`, `MAT-CE1-01` `en cours`. Cinq lignes au compteur au plus.

### 8.5 Ecarte du palier 3, et pourquoi

| Lot | Motif |
|---|---|
| `MAT-CE1-10`, `-11` (M2, M3) | pas de place : B porte un seul lot ; un second lot de mathematiques sur B est (ii), non retenue (8.1). Candidates du palier 4 |
| `MAT-CE1-15` (M4) | forme textuelle non recevable ; forme illustree 1,5, soit 8,5 en (i) |
| `MAT-CE1-14` (M5) | non recevable sous forme textuelle (`DEP-CE1-2`) ; reste `Q-05` |
| `FRA-CE1-03`, `-21` (F1, F2) | A pleine en lots ; hors X et Y ; `nadia-prof` : non |
| `FRA-CE1-08`, `-09` | condition 4 tenue pour tout s57 (arbitrage, 3.2, `Q-07`) |
| `EMC-CE1-02`, `-03`, `-04` (E2 a E4) | un seul lot d'EMC dans la capacite ; candidates du palier 4. `PAR-2` : l'EMC reste sur A' |
| Questionner le monde CE1 (Q1 a Q10) | aucun audit a s57 (arbitrage, AR1). `Q-01` permet d'ecrire les paquets dans le palier, mais aucun des cinq lots de 8.2 n'est un lot de questionner le monde ; ce que coute d'ecrire sans auditer est la question de `sofia-sm` (arbitrage, section 6) et je n'y reponds pas. Premier audit possible : s58 au plus tot |
| `QLM-CE1-09`, `-11`, `ART-CE1-01`, `-02`, `MAT-CE1-02`, `-09`, `FRA-CE1-27` a `-31`, `EMC-CE1-05` | non recevables a s57 (arbitrage, 1.1 a 1.5) |

**Report compte.** Aucun lot ecarte ici n'etait inscrit a une composition avant : le palier 3 est leur premier ecartement, pas un report repete. `FRA-CE1-06`, `-07`, `-11`, ecartes aux paliers 1 et 2 (`composition-palier-2.md`, section 9), entrent ici.

### 8.6 Ce qui reste a l'utilisatrice

`Q-05` a `Q-10` n'ont pas de reponse et je n'en suppose aucune. Aucune ne change la composition (i) :

| Question | Effet sur cette proposition |
|---|---|
| `Q-05` (`MAT-CE1-14`) | aucun : M5 n'est pas recevable sous forme textuelle, et la place de B est prise |
| `Q-06` (dessins) | aucun : aucune des lignes a dessin n'entre ; terrains neufs exclus tant qu'A' existe |
| `Q-07` (`-08`, `-09`) | aucun : A est pleine, et la condition 4 tient pour tout s57 |
| `Q-08`, `Q-09` (premisse au CE1, saisie alphabetique) | aucun : `FRA-CE1-21` n'entre pas |
| `Q-10` (arts plastiques) | aucun : terrains neufs |

Seule une lecture de `Q-02` a la lettre (8.1, point 3) changerait la composition, vers (ii). Ce choix est a elle.

### 8.7 Routage

| A qui | Quoi |
|---|---|
| `sofia-sm` | composition et planning du palier 3 ; developpeurs de M1 et du paquet d'E1 ; ce que coute d'ecrire les paquets de questionner le monde dans le palier |
| `nadia-prof` | confirmer l'affectation (B mathematiques, A' EMC) et les prix ; l'arbitrage prealable par instance |
| `camille-po` | stories `S57-BR-MAT-CE1-01` et `S57-BR-EMC-CE1-01` a l'ouverture, pas avant ; la mention de `NUM-CE1-2` mot pour mot dans la cellule le jour du verdict |
| `theo-archi` | `ADR-0065` pour A' (arbitrage, section 6) |

Aucune story, aucune `DEC` n'est ecrite par ce geste. Non commite.
