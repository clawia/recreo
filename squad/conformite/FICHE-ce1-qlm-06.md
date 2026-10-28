# Fiche de conformite - ce1-qlm-06

- **Mecanique** : `bandit-manchot`, `type: question`, sans texte support, comme les paquets d'EMC CE1 ; aucune modification de
  la mecanique, aucune illustration (`docs/backlog/backlog.md`, annotation du 2026-10-25, cinquieme, *"Ce qui est commun aux
  neuf stories"*).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-aimant.yaml` (12 items, lus par
  `grep -c "bonneReponse:"`), ecrit par `marc-dev` (instance neuve, `S57-BR-QLM-CE1`).
- **Entree de catalogue** : aucune. `QC3` : un paquet n'entre au catalogue qu'apres un Done pedagogique ou un refus leve ;
  `grep -c "ce1-qlm" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 0 le 2026-10-27.
- **Ligne declaree** : `QLM-CE1-06`. **Story** : `S57-BR-QLM-CE1-06` (`docs/backlog/backlog.md`). **Decision** : `DEC-0120` A.227, A.250 a A.255,
  A.268 a A.280.
- **Competence programme visee** : `squad/programme/questionner-le-monde/competences-ce1.md`, `QLM-CE1-06`, *"Reconnaitre les objets qu'un aimant attire et ceux qu'il n'attire pas."*
- **Conditions opposables** : celles de la story `S57-BR-QLM-CE1-06` et les reperes `QC1` a `QC9` du bloc commun, **non recopiees ici** :
  la story fait foi, ses mots sont ceux de `nadia-prof`. Plafond de la ligne : `couvert-valide`.
- **Statut global** : **en attente d'audit a l'ecran**. Fiche ouverte par `camille-po` le 2026-10-27 (`DEC-0120` A.280, reponse
  de l'utilisatrice a `Q2` : nom `FICHE-ce1-qlm-<lot>.md`). Aucun verdict. La ligne `QLM-CE1-06` reste `non couvert` dans la
  matrice, qui ne bouge qu'au verdict de `nadia-prof`.
- **Statut global, mis a jour le 2026-10-28 par `camille-po` (`DEC-0120` A.283), l'ouverture ci-dessus etant perimee et conservee** : **VALIDE SOUS RESERVE** par `nadia-prof` le 2026-10-27 (s57, palier 5, rang 6 sur 9), fusionne dans `round-two` par `898b6b61`. Deux reserves non bloquantes : la liste d'objets de la tache B et l'entree de catalogue. Voir le bloc de validation.
- **Ligne de matrice, etat du 2026-10-28** : `QLM-CE1-06` reste `non couvert`. Motifs lus au depot : (1) `QC3`, aucune entree de catalogue (`grep -c "ce1-qlm" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 0), donc aucun jeu a nommer dans la cellule "Jeu(x) couvrant" ; (2) Done technique complet, les quatre jobs de `.github/workflows/ci.yml` (`QC8`, A.280 point 2), dont seul `build` a ete lance. Aucun des deux n'est une decision de `nadia-prof`. La ligne bouge quand l'entree est fusionnee et que la CI complete est verte (`S57-QLM-CE1-ENTREES-CATALOGUE`) ; fusion du paquet dans `round-two` : `898b6b61`.
- **Stories du backlog pour ce lot** (`docs/backlog/backlog.md`, annotation du 2026-10-28, douzieme, `DEC-0120` A.283) : `S57-QLM-CE1-06-LISTE-D-OBJETS` (reserve 1) ; sa reserve 2, l'entree de catalogue, est `S57-QLM-CE1-ENTREES-CATALOGUE`. Aucune n'est engagee.
- **Derniere mise a jour** : 2026-10-27 ; 2026-10-28 (`camille-po`, `DEC-0120` A.283 : statut, ligne `verify`, etat de la ligne de matrice, stories).

---

## Competence visee

| Matiere | Niveau | Competence | Reference au referentiel |
|---|---|---|---|
| questionner le monde | CE1 (cycle 2) | Reconnaitre les objets qu'un aimant attire et ceux qu'il n'attire pas. | `squad/programme/questionner-le-monde/competences-ce1.md`, `QLM-CE1-06` |

## Criteres attendus - camille-po

Les criteres d'acceptation de cette fiche sont ceux de la story `S57-BR-QLM-CE1-06` (`docs/backlog/backlog.md`, titre *"Reconnaitre ce qu'un aimant attire et n'attire pas"*) et
les reperes `QC1` a `QC9` du bloc commun. Je ne les recopie pas : une copie derive de la source. Je n'ecris aucune mesure ici.

- [ ] Criteres de la story `S57-BR-QLM-CE1-06`, numerotes dans le backlog.
- [ ] `QC1` a `QC9` du bloc commun (paquet, ligne declaree, branchement, test du paquet, densite, conditions negatives,
  lecture avant `verify`, Done technique, ce que `nadia-prof` garde seule).
- [ ] Texte servi a l'enfant integralement accentue, orthographe et ponctuation irreprochables (`DEC-0002`).

## Parcours du lot, faits lus au depot

Source : `planning.md` du sprint s57, amendement du 2026-10-27 15:40, section 1 ; chaque SHA est un commit de `round-two`.

| Geste | Auteur | Commit | Fichier |
|---|---|---|---|
| premier jet | `marc-dev` | `c86148d1` | `ce1-qlm-aimant.yaml` |
| lecture du premier jet | `nadia-prof` | `d32d2777` | `lecture-nadia-premier-jet-ce1-qlm-aimant.md` |
| corrections | `marc-dev` | `f3d7357c` | `ce1-qlm-aimant.yaml` |
| relecture des corrections (seconde lecture) | `nadia-prof` | `330de000` | `relecture-nadia-corrections-ce1-qlm-aimant.md` |

Les fichiers de `nadia-prof` sont dans `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/`. Cette fiche n'en reprend aucun
verdict : la lecture et la relecture sont des gestes avant `verify`, pas le Done pedagogique.

## Etat de la porte technique

**Ce qu'un fichier du depot prouve, et rien de plus.**

- **Aucun `mvn verify` complet n'a ete lance pour ce lot** (`planning.md`, amendement du 2026-10-27 15:40, section 1 : fait donne
  par l'agent lanceur, non verifie ; les relectures ecrivent *"aucun autre job de `ci.yml` lance"*).
- **Regle en vigueur** (`DEC-0120` A.280, reponse de l'utilisatrice a `Q1`, option `O1`) : un `mvn verify` complet **avant** l'audit
  a l'ecran de `nadia-prof` de ce lot ; passage CI complet (`prefixes`, `chemins`, `outils`, `build`) a la cloture du palier ; toute
  annonce de porte nomme les jobs lances.
- Premier `verify` complet du lot : **fait au niveau du reacteur, pas par lot** (mis a jour le 2026-10-28 par `camille-po`, `DEC-0120` A.283 ; texte d'origine *"a faire"* perime, conserve ici). `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, sur `90c421a7` : `BUILD SUCCESS`, 3618 tests unitaires et 6 d'integration, 0 echec, mesure par `sofia-sm` (`planning.md`, amendement du 2026-10-27 21:34, section 3), **job `build` seul**. `prefixes` et `chemins` n'ont ete lances qu'en commande de garde-fou, forme CI, `outils` n'a pas ete lance : la porte technique complete n'est pas annoncee (A.280 point 2). Sur `1a810b79`, base de cette mise a jour : un `verify` complet vert (3 624 tests, `build` seul) est **annonce par l'agent lanceur et n'est ecrit dans aucun fichier du depot que j'aie lu** ; `git diff --name-only 90c421a7 1a810b79` ne liste que des fichiers `.md`. Que ce `verify` unique tienne lieu de `verify` du lot n'est ecrit nulle part ; `nadia-prof` l'a lu ainsi dans son verdict.

## Ce dont `nadia-prof` reste la seule garde sur cette ligne

Ce qu'aucun test ne tient (`QC9`, `arbitrage-nadia-palier-5-qlm-9-lots.md`, section 6) : l'exactitude de chaque fait contre la source
ecrite en commentaire du paquet, `NRC-CE1-1` sur le sujet de l'item, `CYC-1` contre les paquets voisins, `TRV-CE1-6`, l'operation
mentale de `FER-3`, et les conditions propres a la ligne que la story nomme.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Verdict du 2026-10-27 (`nadia-prof`, s57, palier 5, passe `P2`, rang 6 sur 9) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce** sur le paquet `ce1-qlm-aimant` tel qu'il est dans `round-two` a `d6f2e007` (contenu identique a
`90c421a7` : `git diff --name-only 90c421a7 d6f2e007` ne rend que deux `.md` ; dernier commit du paquet `f3d7357c`, ancetre de
`90c421a7`). `camille-po` peut brancher le paquet (`QC3`, etape distincte que ce commit ne fait pas) puis passer `QLM-CE1-06` a
`couvert-valide`, et faire une story de la reserve 1, non bloquante. Le Done porte sur les 12 items, leurs indices et leur
revelation ; il ne porte ni sur le titre ni sur la consigne de l'entree de catalogue, qui n'existent pas encore (reserve 2).

**Done technique du lot, au sens d'`DEC-0120` A.280 point 2** : `mvn -B clean verify` complet, reacteur entier, vert a `90c421a7`
(3 624 tests, 0 echec), lance par `sofia-sm` (`planning.md`, amendement du 2026-10-27 21:34, section 3). Lu, non relance par
moi. Job `build` seul, etape `verify` seule ; `prefixes`, `chemins`, `outils` non passes en entier : c'est le passage complet de la
cloture du palier, pas une condition de ce verdict.

**Comment j'ai joue, dit.** Le paquet n'est pas au catalogue (`QC3`) : le jar servi ne le propose a aucun enfant. Je l'ai lance
(JDK 21.0.10, port propre au lot) avec un catalogue de jeu ecrit hors du depot, copie exacte du catalogue servi plus une seule
entree pour ce paquet (`bandit-manchot`, 10 manches, consigne *"Lis bien, puis choisis la bonne réponse."*, celle des paquets
d'EMC CE1, titre provisoire de ma main), place devant le jar par le lanceur de Spring Boot (`loader.path`). Le paquet et le
catalogue du jar sont identiques, octet pour octet, a ceux du depot (`diff`). Aucune entree n'est ajoutee au depot. Parcours d'un
enfant : accueil, CE1, tuile *Questionner le monde*, tuile du jeu, partie. Cinq parties en fragments htmx (requetes `HX-Request`),
chacune avec un plan d'erreurs different (aucune erreur ; une ; deux ; trois, donc revelation ; en commencant par l'un ou l'autre
distracteur), puis quatre parties dans un navigateur sans tete : trois a 744 x 551, une a 375 x 667, captures et cotes des
boutons, en appuyant d'abord sur la proposition la plus courte a l'ecran. **Les 12 items ont ete servis et joues**, chacun
plusieurs fois. Serveur et navigateurs arretes par leur PID.

**Constate a l'ecran** :

1. Les 12 enonces, 36 propositions et 12 indices servis sont ceux du paquet, mot pour mot (accents et ponctuation : controle du
   `relecteur-pedagogique`, je n'en juge pas). Le retour faux (*"Ce n'est pas ça. Essaie encore !"*) affiche l'indice de l'item ; les 12 indices sont
   distincts, chacun une methode, aucun ne nomme une matiere ni ne designe une proposition. La proposition deja essayee est
   barree. Au troisieme echec, *"Appuie sur la bonne réponse :"* et la bonne reponse nommee ; l'enfant bloque avance toujours.
   Fin de partie : nombre trouve seul sur nombre joue.
2. A 744 x 551, tout l'item et le bloc du retour faux tiennent a l'ecran (depassement de 21 px au plus, sous l'indice) ; les
   enonces a premisse tiennent sur deux lignes, sans mot coupe en fin de ligne, sur les captures regardees.
3. Tache A : les trois propositions sont de largeur differente ; tache B : trois boutons de meme largeur, empiles.
4. **Le plus court gagne.** En appuyant d'abord sur le plus court, l'enfant trouve du premier coup dans 7 items sur 12 : les 4
   items de B (bon groupe le plus court en caracteres : 24 contre 39 et 28 ; 30 contre 37 et 32 ; 25 contre 37 et 29 ; 25 contre
   33 et 36) et les items 1, 7, 10 de A. Recompte ce jour sur le paquet : identique a ma lecture et a ma relecture, rien a
   corriger dans mes propres chiffres. A 375 x 667, a l'item 12, le bon groupe est le seul bouton sur deux lignes, les deux
   autres en prennent trois.
5. Hors du perimetre de reference (744 x 551), constate sans reserve : a 375 x 667, dans les items de B, la troisieme proposition
   est sous le bord de l'ecran (bas du bouton a 975 a 1 023 px pour 667). C'est le gabarit commun, pas ce paquet.

**Verifie, et tenu** : competence `QLM-CE1-06` travaillee par les deux taches, et pas une competence voisine (aucun item ne
demande une matiere, tous demandent ce que l'aimant attire) ; exactitude des 16 objets contre `AIM-CE1-1` (fer attire, aluminium
et cuivre non attires, aucun objet exclu, ni or, ni *acier*, ni *doré*) ; `AIM-CE1-2` : 8 items sur 12 comptent pour le quart, et
aucun enonce, indice ni revelation ne dit que l'aimant attire les metaux ; la croyance fausse ne trouve la reponse dans aucun des
huit, et la premisse *"Tous ces objets sont en métal."* la contredit a l'ecran ; `FER-1` (objet attire et objet non attire en
bonne reponse) ; `FER-3`, operation mentale : en B, deux jugements au moins pour garder le bon groupe ; les items 5, 6, 9, 12
corriges tiennent a l'ecran comme sur le papier ; lisibilite CE1 (enonces d'une ou deux phrases, propositions de six mots au
plus) ; `TRV-CE1-6`, `CYC-1`, `NRC-CE1-1` sur le sujet. La revelation nomme la reponse sans dire pourquoi : aucun paquet de CE1
ne porte d'explication, je ne l'ai demandee nulle part, ce n'est pas une reserve.

**Reserves, non bloquantes** :

1. **Reserve de ma lecture du 2026-10-27 (section 1, dernier point), confirmee a l'ecran, inchangee** : en tache B, le bon groupe
   est le plus court en caracteres dans les 4 items, et le plus court gagne au premier coup dans 7 items sur 12 (constat 4).
   Exigence : dans la tache B, le bon groupe n'est strictement le plus court en caracteres que dans la moitie des items de B au
   plus. Levier : la liste d'objets (des noms d'objets attires aussi longs que *fil de cuivre* ou *papier d'aluminium*), pas
   un item retouche ; le choix est a l'equipe, chaque objet ajoute porte sa source (`QMT-CE1-2`). Ma barre en mots de
   `TRV-CE1-2` et de ma regle de B reste celle qui est ecrite et tenue : je ne la releve pas.
2. **Neuve, datee du 2026-10-27** : l'entree de catalogue de ce paquet n'existe pas, et je n'ai joue que la consigne
   *"Lis bien, puis choisis la bonne réponse."* sur 10 manches. Exigence : l'entree de `QC3` porte cette consigne et 10 manches,
   ou bien son titre et toute autre consigne me sont montres avant la fusion. Le titre n'a pas ete audite.

**Je reste la seule garde**, aucun test ne les tient : l'exactitude de chaque objet contre sa classe ; la longueur en caracteres
du bon groupe (reserve 1) ; la forme, la taille et l'usage partages par deux distracteurs de A ; toute lecture fausse d'un nom
autre que *papier* (que `g12` encode) ; les paraphrases d'`AIM-CE1-2` hors de la garde ; l'operation mentale de `FER-3` ;
`CYC-1`, `NRC-CE1-1` sur le sujet, `TRV-CE1-6` ; le titre et la consigne de l'entree a venir.

**Duree de l'audit, a l'horloge** (`date`) : debut 21:52:08, fin 22:21:28, soit 29 min 20 s, verifications avant commit comprises (le `verify` complet n'a pas ete relance par moi). Remesure A.276 : aucune seconde lecture de plus
sur ce lot depuis la relecture du 2026-10-27 (une seule seconde lecture au total).

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
