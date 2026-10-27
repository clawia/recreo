# Fiche de conformite - ce1-qlm-01

- **Mecanique** : `bandit-manchot`, `type: question`, sans texte support, comme les paquets d'EMC CE1 ; aucune modification de
  la mecanique, aucune illustration (`docs/backlog/backlog.md`, annotation du 2026-10-25, cinquieme, *"Ce qui est commun aux
  neuf stories"*).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-etapes-de-developpement.yaml` (15 items, lus par
  `grep -c "bonneReponse:"`), ecrit par `marc-dev` (instance neuve, `S57-BR-QLM-CE1`).
- **Entree de catalogue** : aucune. `QC3` : un paquet n'entre au catalogue qu'apres un Done pedagogique ou un refus leve ;
  `grep -c "ce1-qlm" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 0 le 2026-10-27.
- **Ligne declaree** : `QLM-CE1-01`. **Story** : `S57-BR-QLM-CE1-01` (`docs/backlog/backlog.md`). **Decision** : `DEC-0120` A.227, A.250 a A.255,
  A.268 a A.280.
- **Competence programme visee** : `squad/programme/questionner-le-monde/competences-ce1.md`, `QLM-CE1-01`, *"Ordonner les etapes du developpement d'un animal ou d'une plante familiers, y compris une metamorphose (oeuf, tetard, grenouille) ou une germination (graine, jeune pousse, plante)."*
- **Conditions opposables** : celles de la story `S57-BR-QLM-CE1-01` et les reperes `QC1` a `QC9` du bloc commun, **non recopiees ici** :
  la story fait foi, ses mots sont ceux de `nadia-prof`. Plafond de la ligne : `couvert-valide`.
- **Statut global** : **VALIDE**, prononce le 2026-10-27 par `nadia-prof` (s57, palier 5, `P2`, lot 1 sur 9), audit joue a
  l'ecran ; verdict et perimetre dans le bloc de validation. **Etat precedent, conserve** : **en attente d'audit a l'ecran**. Fiche ouverte par `camille-po` le 2026-10-27 (`DEC-0120` A.280, reponse
  de l'utilisatrice a `Q2` : nom `FICHE-ce1-qlm-<lot>.md`). Aucun verdict. La ligne `QLM-CE1-01` reste `non couvert` dans la
  matrice, qui ne bouge qu'au verdict de `nadia-prof`.
- **Derniere mise a jour** : 2026-10-27 (ouverture par `camille-po`, puis verdict de `nadia-prof`).

---

## Competence visee

| Matiere | Niveau | Competence | Reference au referentiel |
|---|---|---|---|
| questionner le monde | CE1 (cycle 2) | Ordonner les etapes du developpement d'un animal ou d'une plante familiers, y compris une metamorphose (oeuf, tetard, grenouille) ou une germination (graine, jeune pousse, plante). | `squad/programme/questionner-le-monde/competences-ce1.md`, `QLM-CE1-01` |

## Criteres attendus - camille-po

Les criteres d'acceptation de cette fiche sont ceux de la story `S57-BR-QLM-CE1-01` (`docs/backlog/backlog.md`, titre *"Ordonner les etapes du developpement d'un animal ou d'une plante"*) et
les reperes `QC1` a `QC9` du bloc commun. Je ne les recopie pas : une copie derive de la source. Je n'ecris aucune mesure ici.

- [ ] Criteres de la story `S57-BR-QLM-CE1-01`, numerotes dans le backlog.
- [ ] `QC1` a `QC9` du bloc commun (paquet, ligne declaree, branchement, test du paquet, densite, conditions negatives,
  lecture avant `verify`, Done technique, ce que `nadia-prof` garde seule).
- [ ] Texte servi a l'enfant integralement accentue, orthographe et ponctuation irreprochables (`DEC-0002`).

## Parcours du lot, faits lus au depot

Source : `planning.md` du sprint s57, amendement du 2026-10-27 15:40, section 1 ; chaque SHA est un commit de `round-two`.

| Geste | Auteur | Commit | Fichier |
|---|---|---|---|
| premier jet | `marc-dev` | `7c922786` | `ce1-qlm-etapes-de-developpement.yaml` |
| lecture du premier jet | `nadia-prof` | `02add592` | `lecture-nadia-premier-jet-ce1-qlm-etapes-de-developpement.md` |
| corrections | `marc-dev` | `47279e05` | `ce1-qlm-etapes-de-developpement.yaml` |
| relecture des corrections (seconde lecture) | `nadia-prof` | `408fec39` | `relecture-nadia-corrections-ce1-qlm-etapes-de-developpement.md` |

Les fichiers de `nadia-prof` sont dans `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/`. Cette fiche n'en reprend aucun
verdict : la lecture et la relecture sont des gestes avant `verify`, pas le Done pedagogique.

## Etat de la porte technique

**Ce qu'un fichier du depot prouve, et rien de plus.**

- **Aucun `mvn verify` complet n'a ete lance pour ce lot** (`planning.md`, amendement du 2026-10-27 15:40, section 1 : fait donne
  par l'agent lanceur, non verifie ; les relectures ecrivent *"aucun autre job de `ci.yml` lance"*).
- **Regle en vigueur** (`DEC-0120` A.280, reponse de l'utilisatrice a `Q1`, option `O1`) : un `mvn verify` complet **avant** l'audit
  a l'ecran de `nadia-prof` de ce lot ; passage CI complet (`prefixes`, `chemins`, `outils`, `build`) a la cloture du palier ; toute
  annonce de porte nomme les jobs lances.
- Premier `verify` complet du lot : **a faire**, resultat a inscrire ici par qui le lance, avec les jobs nommes.

## Ce dont `nadia-prof` reste la seule garde sur cette ligne

Ce qu'aucun test ne tient (`QC9`, `arbitrage-nadia-palier-5-qlm-9-lots.md`, section 6) : l'exactitude de chaque fait contre la source
ecrite en commentaire du paquet, `NRC-CE1-1` sur le sujet de l'item, `CYC-1` contre les paquets voisins, `TRV-CE1-6`, l'operation
mentale de `FER-3`, et les conditions propres a la ligne que la story nomme.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Verdict du 2026-10-27 (`nadia-prof`, s57, palier 5, `P2`, lot 1 sur 9) : **VALIDE**

**Done pedagogique prononce** pour `QLM-CE1-01`, paquet `ce1-qlm-etapes-de-developpement`, sans reserve. `camille-po` peut
passer la ligne a `couvert-valide` quand l'entree de catalogue sera ecrite (`QC3`), sous la limite de perimetre de la section 5.
Branche `s57-p5-nadia-audit-01`, base `round-two` a `d6f2e007`.

#### 1. Ce que j'ai fait

- **Objet joue** : le jar construit depuis `round-two` a `90c421a7`, fourni par l'agent lanceur, copie hors du depot, non
  reconstruit par moi. `git diff --stat 90c421a7 d6f2e007` : deux fichiers `.md`, aucun code ni contenu. Ressources de
  `adapter-content` et `adapter-web` extraites du jar : egales a celles du worktree a `d6f2e007` (`diff -rq --strip-trailing-cr`,
  0 difference) ; `catalogue.yaml` et le paquet egaux octet pour octet (`cmp`).
- **Hors catalogue (`QC3`)** : `grep -c ce1-qlm` sur le `catalogue.yaml` du jar rend 0. Pour jouer, un `catalogue.yaml`
  d'audit, hors du depot, egal au catalogue courant plus une entree (identifiant `audit-ce1-qlm-01`, titre *"AUDIT ce1-qlm-01"*,
  consigne *"Lis bien, puis choisis la bonne réponse."* reprise des quatre jeux d'EMC CE1, `bandit-manchot`, 10 manches), place
  devant le jar par `loader.path` du lanceur `PropertiesLauncher` de Spring Boot. Le jar n'est pas modifie, **aucune entree n'est
  ecrite dans le depot**, `git status` vide avant ecriture de cette fiche. La page CE1 de questionner le monde ne montre que ce jeu.
- **Serveur** : JDK 21.0.10 (entree `jdk` 21 de `~/.m2/toolchains.xml`), `--server.port=18101`, port libre avant (`netstat -ano`) ;
  journal *"Tomcat started on port 18101"*, PID 43996, arrete par ce PID seul, port libre apres.
- **Joue comme un enfant, en me trompant volontairement** : client HTTP sur les fragments htmx (en-tete `HX-Request`), 5 parties
  de 10 manches, 50 manches : une partie toute en erreur (deux erreurs, puis le seul bouton restant), deux parties justes du
  premier coup, deux parties avec une erreur puis la bonne reponse. 40 erreurs. **15 items sur 15 vus**, de 1 a 5 fois.
- **Ecarts contre le fichier** : 0 sur les 40 erreurs (propositions servies egales a la bonne reponse et aux deux distracteurs,
  indice servi egal a celui du fichier) ; aucun indice avant la premiere erreur.
- **Captures** (Chrome sans tete, profil isole hors du depot) : a 768 x 1024, et a 360 de large dans un cadre de 360 px (la fenetre
  sans tete ne descend pas sous 500 px, `innerWidth` mesure a 500). Trois manches a 360, les deux premieres aussi a 768 : l'ordre du papillon (propositions les plus
  longues, 30 caracteres), *"juste avant la jeune grenouille"* apres une erreur (enonce le plus long, 82 caracteres), *"juste
  avant la jeune pousse"* apres une erreur (indice le plus long, 13 mots).
- **Ce que je n'ai pas fait** : aucun enfant reel n'a joue ; ce que j'ecris d'un enfant est ce que j'ai joue a sa place.
- **Done technique** : lu, non lance par moi. `sofia-sm`, `bash outils/mvn-jdk21.sh -B clean verify` sur `90c421a7` :
  `BUILD SUCCESS`, 3618 tests unitaires et 6 d'integration, 0 echec (`planning.md`, amendement du 2026-10-27 21:34, section 3) ;
  **job `build` seul**. `prefixes`, `chemins`, `outils` ne sont pas lances en entier : passage CI complet a la cloture du palier
  (A.280, `O1`). La ligne *"Premier `verify` complet du lot : a faire"* de la section "Etat de la porte technique" est perimee ;
  je ne la corrige pas, elle est a qui a lance le `verify` (A.281 point 7, ligne 6).
- **Duree de l'audit, a l'horloge** (UTC+01:00, bornes lues par `date`, donnee brute pour la remesure d'A.276) : de 21:51:13
  (lecture de la procedure) a 22:22:37 (fin de redaction, avant les controles et le commit), soit **31 min 24 s** ; dont serveur
  en marche de 21:59:56 (journal) a 22:14:01 (arret), 14 min 05 s. Aucune conversion en bareme.

#### 2. Criteres tenus, a l'ecran

| Critere | Constat |
|---|---|
| Ancrage, `QLM-CE1-01` | 11 items *voisine* (juste avant, juste apres), 4 items *ordre* (trois etapes). L'enfant ordonne des etapes : c'est la competence, pas une reconnaissance d'animal |
| `DVP-CE1-1` | quatre especes de la liste fermee ; aucun item ne demande la premiere ni la derniere etape |
| `FER-1` | *l'œuf* (item 5), *le têtard* (13), *la grenouille* (9), *la graine* (11), *la jeune pousse* (3), *la plante* (7) sont des bonnes reponses servies : une metamorphose et une germination |
| `FER-3` | deux taches, *ordre* la moins servie a 4 items |
| Exactitude | 15 items sur 15 justes, contre ma liste ; 30 distracteurs, aucun defendable ; sur les 4 items *ordre*, aucun distracteur n'est une rotation du bon ordre |
| Lisibilite | enonces de 12 a 15 mots, une phrase, un seul moule (*"Dans la vie de..., quelle étape vient juste après / avant... ?"*) ; indices de 6 a 13 mots ; propositions de 1 a 3 mots |
| Typographie servie | les 21 points d'interrogation du paquet sont precedes de U+00A0 dans le fichier (21 sur 21, script) ; dans le HTML servi, lu brut sur les 15 enonces et sur 3 des 6 indices interrogatifs, les 3 autres n'ayant ete lus qu'apres une normalisation des espaces par mon script, qui ne les departage pas ; *œ* lie ; apostrophe droite. L'orthographe reste au `relecteur-pedagogique` |
| Traitement de l'erreur | *"Ce n'est pas ça. Essaie encore !"* et l'indice des la premiere erreur, bouton deja essaye barre ; apres deux erreurs, il reste un bouton et l'enfant avance. Chaque indice donne une methode (suivre, remonter, raconter la vie de l'espece) |
| Pli, 768 x 1024 | les deux manches capturees a cette taille (ordre du papillon, *"juste avant la jeune grenouille"* apres une erreur) tiennent dans l'ecran, enonce sur 2 lignes, propositions sur une ligne, indice compris |
| Pli, 360 de large | enonce jusqu'a 5 lignes ; sur les items *ordre*, chaque proposition passe sur 3 lignes, et a 360 x 640 (capture) la premiere est coupee par le bas de l'ecran, la deuxieme et la troisieme sont sous le pli : il faut faire defiler. Le paquet ne depasse pas ce que le catalogue CE1 sert deja (30 caracteres ici, 35 dans `ce1-emc-biens-communs`) et la mecanique n'est pas modifiee : mesure, pas reserve de lot |
| Biais et ton | aucune personne, aucun prenom, aucun lieu de vie ; ton neutre |

#### 3. Ce que j'avais annonce regarder en jouant

- **Items 6 et 9, la bonne reponse est le nom de l'espece** (`relecture-nadia-corrections-ce1-qlm-etapes-de-developpement.md`,
  2.1 : *"si un enfant les reussit en recopiant le mot de la question, ce sera une reserve, ecrite a ce moment-la, et cette forme
  en sera la correction proposee"*). Joue : le geste *"je presse la proposition ecrite dans la question"* gagne sur 6 (*la
  poule*) et 9 (*la grenouille*). Il perd sur 5 et 13, ou *la grenouille*, ecrite dans la question, est un distracteur, et sur 10,
  ou *le papillon* l'est. Recompte par script sur le fichier : une proposition ecrite mot pour mot dans l'enonce sur 4 items, la
  bonne reponse sur 2 (6, 9), un distracteur sur 2 (5, 13). **Le geste ne fait pas traverser une partie : il est puni aussi
  souvent qu'il paie.**
  **Correction de mon propre ecrit.** La forme que j'annoncais comme correction, *"juste avant la poule"* -> *le poussin*, ne tient
  pas ma propre regle : *l'œuf* est voisin du poussin, la famille a (`:39`, *"jamais voisine de la bonne reponse"*) n'a plus
  d'etape de la poule a offrir, les deux distracteurs seraient d'autres especes, et l'item se resoudrait par *"le poussin est le
  petit de la poule"*, savoir de CP (`cp-qlm-cycle-de-vie`, `:45`). Elle degraderait l'item. L'item 9 ne se corrige pas non plus
  sans rompre `FER-1` : c'est le seul qui sert *la grenouille* en bonne reponse, et `DVP-CE1-1` veut que l'enonce nomme l'espece.
  Le recouvrement entre nom d'espece et nom d'etape vient de ma liste fermee, pas du travail de `marc-dev`.
  **Je n'ouvre donc pas la reserve annoncee**, et je le dis comme un ecart a mon propre ecrit : la seule correction que j'avais
  nommee est fausse, et le geste qu'elle visait ne paie pas sur le paquet. Je reste la seule garde de cette propriete.
- **Le verbe *Remonte*, ligne 144** (`arbitrage-nadia-indices-ce1-qlm-etapes-de-developpement.md`, ligne 69). Une hesitation
  d'enfant ne s'observe pas sans enfant. A l'ecran, l'enonce *"... juste avant la jeune grenouille ?"* reste au-dessus de
  l'indice, a 360 comme a 768 (captures) : le sens est porte par l'enonce. Pas de reserve ; je reste la seule garde.
- **Items 3 et 11, meme paire de distracteurs** (*la fleur*, *le fruit*). Vus dans la meme partie (partie 4, manches 1 et 10).
  *La fleur* et *le fruit* ne sont jamais bonne reponse d'un item *voisine* ; les ecarter, c'est savoir qu'elles viennent plus tard,
  donc la competence elle-meme. Pas de reserve.

#### 4. Hors du lot, vu et non juge

L'ecran de fin dit *"Bravo, c'est juste !"* au-dessus de *"Tu as trouvé, tout seul : 0"* apres une partie toute en erreur. C'est
l'ecran commun de `bandit-manchot`, que cette story ne modifie pas ; je ne le juge pas ici et ne le rouvre pas.

#### 5. Perimetre du Done, et ce dont je reste la seule garde

- **Le Done couvre le paquet, pas l'entree de catalogue.** Le titre et la consigne que l'enfant lira n'existent pas (`QC3`) ; ceux
  que j'ai joues sont les miens. Le commit qui ecrit l'entree me les soumet avant la fusion ; jusque-la, aucun Done ne les couvre.
- **Seule garde, aucun test ne les tient** : l'exactitude de chaque ordre contre ma liste ; `NRC-CE1-1` sur le sujet ; `CYC-1` ;
  la plausibilite des distracteurs, rotation comprise ; la methode de chaque indice, *Remonte* compris ; l'operation mentale de la
  tache *ordre* ; les items 6 et 9 (section 3) ; le pli a 360 de large des items *ordre*.
- **Rien n'est rouvert** : aucune lecture ni correction anterieure de `-01`. Les reserves connues de `-02` (intrus) et de `-10`
  (items B) sont d'autres lots et ne figurent pas dans ce paquet.
- **Non fait ici, par perimetre** : aucune entree de catalogue, aucun YAML ni test modifie, matrice et `review.md` non touches
  (la matrice a `camille-po`, la review a la cloture).

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
