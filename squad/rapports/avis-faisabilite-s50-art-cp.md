# s50 - Avis de faisabilite (`theo-archi`) - `S50-ART` (`ART-CP-09`, `ART-CP-10`, `T6` d'`ADR-0082`)

- **Date** : 2026-10-04
- **Base** : `round-two` a `43ec4411`, arbre propre. Aucun diff de code ni de contenu dans cet avis.
- **Pourquoi un fichier a part** : `DEC-0109` (section 6) route cet avis apres la cloture de
  `avis-faisabilite-s50-cinq-stories.md`, sur une autre decision et une autre base (`03d152b0` la-bas).
  Le fusionner reecrirait la base d'un avis deja rendu.
- **Ce que cet avis ne vaut pas** : il ne choisit aucune oeuvre, aucun distracteur, et ne juge aucune
  condition. Le Done pedagogique reste a `nadia-prof`.

---

## 0. L'etat mesure : neuf echecs, pas deux, et sept sont de mon fait

`bash outils/mvn-jdk21.sh -B -q -pl bootstrap -am test
-Dtest=LIllustrationDeContenuResoutEtAucuneNeDortTest,ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest
-Dsurefire.failIfNoSpecifiedTests=false`, a `43ec4411`, le 2026-10-04. Version : **JDK 21.0.10**,
propriete `java.version` des deux rapports `TEST-*.xml`. Perimetre : ces deux classes seules, pas le
reacteur.

| Classe | Tests | Echecs | Cause |
|---|---|---|---|
| `ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest` | 20 | **7** | `oeuvre-chambre.jpg` declare 3000x2348 px (plafond 352) ; 28 reproductions pour un cliquet de 25 |
| `LIllustrationDeContenuResoutEtAucuneNeDortTest` | 18 | **2** | les 28 reproductions dorment (28 noms distincts dans le message) |

Les sept echecs : `leDepotReelTientLaRegle`, `unMaterielSainPasse`,
`uneReproductionSousLePlafondDePixelsPasseLaClause`, `leCliquetVautExactementLaTailleDeLaPopulationServie`,
et trois morsures (`unFichierSansEntreeEstRefuse`, `unNomDeBasePartageParDeuxPopulationsEstRefuse`,
`unDessinMaisonQuiPorteLePrefixeDesReproductionsEstRefuse`) dont le message attendu est masque par la
faute de pixels, levee avant lui.

**La cause est mon commit `56b6b77c`.** Il ajoute trois fichiers a la resolution de l'Art Institute
of Chicago sans le redimensionnement qu'`ADR-0082` decision 2 exige, et sans relever le cliquet.
Dimensions lues dans le segment SOFn de chaque fichier, par la meme regle que `dimensionsJpeg`
(script de poste, non commite) :

| Fichier | Dimensions | Octets |
|---|---|---|
| `oeuvre-chambre.jpg` | 3000x2348 | 2 915 017 |
| `oeuvre-rue-sous-la-pluie.jpg` | 3000x2329 | 2 047 582 |
| `oeuvre-grande-jatte.jpg` | 3000x2009 | 2 574 668 |

Les 25 autres fichiers pesent ensemble 620 639 octets, exactement `POIDS_TOTAL_MAXIMUM` ; les trois
ajoutent 7 537 267 octets. Le message de `56b6b77c` n'annonce que les deux echecs de
`LIllustrationDeContenuResoutEtAucuneNeDortTest` : je n'avais pas lance le garde-fou de la population
que je modifiais. `confirmation-archunit.md` (`ce72dc03`) est anterieure a `56b6b77c` et ne couvre
pas cet etat.

## 1. Verdicts

| Code | Objet | Verdict |
|---|---|---|
| V1 | `ART-CP-09` | **faisable en l'etat**, `bandit-manchot`, forme `question`, aucun `src/main/java`, aucun `ADR`. Prerequis : A1 (deux des trois fichiers fautifs sont dans son vivier) |
| V2 | `ART-CP-10` | **faisable en l'etat sous une seule lecture**, a faire confirmer par `nadia-prof` avant ecriture (Q1). Sous l'autre lecture, **infaisable dans `T6`** : forme d'item neuve, `ADR` prealable, `src/main/java` |
| V3 | Critere *"`BUILD SUCCESS`, zero echec"* (`DEC-0109` section 6, sixieme critere de la story) | **infaisable par le paquet seul.** Deux causes independantes, mesurees (sections 0 et 3). Levees par A1 et A2, pas par le contenu |

## 2. Mecanique

**`ART-CP-09` : `bandit-manchot`, forme `question`.** `Question` porte une `illustration` et des
propositions en texte (`domain/.../content/Question.java`, composantes `illustration` et
`illustrationTexteAlternatif`). La resolution `oeuvre-` vers `static/oeuvres/` existe (T2, T3). Rien
a ajouter.

**`ZoneADesigner` ne convient a aucune des deux lignes**, pour trois motifs lus dans le code et les
`ADR` :

1. elle designe une zone **dans une seule image** (`ZoneADesigner(..., String illustration, ...,
   List<Zone> zones, ...)`) ; rapprocher deux oeuvres demanderait un montage de plusieurs oeuvres
   dans un fichier, ce qu'`ADR-0082` decision 2 exclut (*"le fichier source redimensionne, une seule
   etape"*) et que le registre ne sait pas prouver (une entree, une oeuvre, un fichier) ;
2. elle est servie par la mecanique `designation`, et `BanditManchotSession.questionDe` refuse un
   `ZoneADesigner` : un paquet ne melange pas les deux, et `DEC-0109` en impose un seul ;
3. pour `ART-CP-09`, l'enfant decrit l'oeuvre entiere, il ne designe pas une partie.

**`ART-CP-10` sous `question`** : un item montre une oeuvre (par exemple `oeuvre-champ-de-ble`) et
demande laquelle, parmi des oeuvres deja vues, lui ressemble ; les propositions designent ces
oeuvres **en texte**. Techniquement faisable sans une ligne de Java. Mais `nadia-prof` a ecrit, sur
cette ligne meme (`competences-cp.md`, ligne 75, et ligne 74 pour `CON-1`), qu'un item qui decrit
une oeuvre en texte au lieu de la montrer est une contrefacon. Des propositions comme *"le tableau de
la chambre"* sont une description de l'oeuvre a rapprocher. **C'est Q1, et c'est le seul point
bloquant de cet avis cote contenu.**

**Si elle exige que l'enfant voie les deux oeuvres** (propositions en image, ou deux images par
item) : la facon dont l'enfant repond change, critere d'`ADR-0058` repris par `ADR-0082` decision 5.
Il faut alors une quatrieme forme d'item (`Item permits`), sa mecanique ou l'extension d'une
existante, son gabarit, ses garde-fous de rendu, donc `domain`, `adapter-web` et `src/main/java`.
C'est un `ADR` que j'ecris avant tout code, et un chantier qui ne tient pas dans `T6` a 2,0. Je ne
l'invente pas ici.

## 3. Registre et fichiers

- **Les 11 identifiants existent des deux cotes** : pour chacun, `grep -c -x "  - identifiant:
  oeuvre-<id>"` sur `squad/corpus/oeuvres-domaine-public.yaml` rend 1, et `test -f` sur
  `adapters/adapter-web/src/main/resources/static/oeuvres/oeuvre-<id>.jpg` reussit. 28 entrees au
  registre (`grep -c "identifiant: oeuvre-"`), 28 fichiers sous `static/oeuvres/` (liste du
  repertoire). Mesure du 2026-10-04 a `43ec4411`.
- **Ils ne suffisent pas a lever le rouge.** La clause *"aucune ne dort"* porte sur **chaque**
  reproduction (`LIllustrationDeContenuResoutEtAucuneNeDortTest.verifier`, boucle sur
  `reproductions`) : il ne suffit pas qu'un paquet declare *"au moins un identifiant `oeuvre-`"*,
  comme `DEC-0108` (section 1, note du 2026-10-03) l'ecrit. **Mesure, pas deduction** : dans un
  worktree detache a `43ec4411`, hors de l'arbre et supprime ensuite, un paquet temporaire declarant
  exactement les 11 identifiants, puis la meme commande restreinte a cette classe. Resultat : les deux
  memes methodes restent rouges et nomment **17** reproductions qui dorment - les 17 de `T7` et `T8`.
  Un troisieme echec, `lesDeuxPopulationsIllustreesValentExactementCeQueLeDepotSert` (56 attendu), est
  le cliquet que le lot releve dans son propre commit.

**Deux sorties, et le choix entre elles est le mien sauf sur un point.**

| Code | Sortie | Regle changee | `ADR` |
|---|---|---|---|
| O1 | Le commit du paquet retire les 17 fichiers et leurs 17 entrees de registre ; `T7`/`T8` les restaurent a l'octet depuis l'historique (`git checkout <sha> -- ...`, meme blob, aucun poids Git de plus) | aucune : c'est la regle d'`ADR-0073` clause 2 telle qu'ecrite | non |
| O2 | Les 17 restent, sous une dette nommee exacte dans le garde-fou (chaque nom doit designer un fichier reel non declare ; plafond egal a la dette, qui ne remonte jamais) | amendement d'`ADR-0073` clause 2 | oui, avant le paquet |

**Je recommande O1** : zero piece mobile, et le motif de la clause (pas de poids mort dans le fat jar)
reste entier. Ce qui me ferait choisir O2 : que `T7` soit engage au sprint suivant, la sortie et le
retour des fichiers devenant alors du bruit d'historique. O1 retire de l'arbre une partie du livrable
de `T5` que `camille-po` a accepte en `DEC-0108` section 1 : elle doit le savoir avant (Q3). Le
registre reste juge sur les 11 restants ; `oeuvre-hippopotame` (auteur `anonyme`) garde aux morsures
leur entree anonyme.

## 4. Poids

- **Aucun plafond de poids du fat jar n'existe dans le depot** : `git grep` de `recreo.jar`, `fat jar`
  et `taille du jar` sur `*.java`, `*.xml`, `*.yml`, `*.sh` ne trouve que `JarRecreoIT` et
  `LeLivrableNeSertQueLesRessourcesDeSesModulesIT`, qui n'en portent pas. Le seul garde-fou de poids
  de cette population est le cliquet exact de
  `ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest` (25 fichiers, 620 639
  octets), et c'est lui que les trois fichiers fautifs franchissent.
- **Les 11 fichiers du vivier pesent 5 161 939 octets** a `43ec4411`, dont 4 962 599 pour
  `oeuvre-chambre` et `oeuvre-rue-sous-la-pluie` ; les neuf autres, 199 340. Somme des tailles lues par
  le meme script.
- **Apres A1**, l'ordre de grandeur attendu par fichier est celui des 25 deja servis, en moyenne
  24 826 octets (620 639 / 25, mesure). C'est une estimation pour les trois fichiers a refaire, pas une
  mesure : la valeur du cliquet est celle que A1 mesurera.
- Le fat jar n'a pas ete mesure pour cet avis : aucun `recreo.jar` n'etait construit dans l'arbre.

## 5. Texte dans une reproduction

- **La clause 4 d'`ADR-0073` ne s'applique pas a un JPEG, lu dans le code** :
  `LIllustrationDeContenuResoutEtAucuneNeDortTest.verifier` cherche `<text` et `<tspan` dans la seule
  boucle sur `illustrations` (dessins SVG, lus en texte) ; la population `reproductions` n'est indexee
  que par nom de fichier et jamais lue. `ADR-0082` (Consequences) l'ecrit : *"ne s'applique pas ici et
  ne peut pas s'appliquer"*. **Aucun garde-fou technique ne bloque donc une estampe qui porte du
  texte.**
- **Constat sur le fichier**, inspection visuelle de `oeuvre-grande-vague.jpg` (352x237) le
  2026-10-04 : un cartouche rectangulaire d'ecriture japonaise verticale en haut a gauche, et une
  colonne de signature a sa droite. Pas d'alphabet latin ; les caracteres ne se distinguent pas un a
  un a cette taille, le bloc d'ecriture se voit.
- **Le cas n'est pas seul** : `oeuvre-averse-sur-le-pont.jpg` (242x352), l'autre moitie de la paire
  de `ART-CP-10`, porte un cartouche de titre en haut a droite et une signature avec cachet en bas a
  gauche. `DEC-0109` ne nomme que `oeuvre-grande-vague`. Les cartes murales de `oeuvre-femme-au-luth`
  et `oeuvre-jeune-femme-aiguiere` ne montrent aucun texte lisible a leur taille servie.
- **Verdict technique** : ne bloque pas la faisabilite. La recevabilite reste a `nadia-prof` (Q2), et
  elle porte sur les deux estampes.

## 6. `ADR`

**Aucun `ADR` sous V1, V2 premiere lecture et O1.** La story reste sous `ADR-0073`, `ADR-0082` et
`ADR-0058`. `ADR-0080` (`ZoneADesigner`) n'est pas retenu (section 2). Un `ADR` devient prealable dans
deux cas seulement : O2 (amendement d'`ADR-0073` clause 2), ou Q1 tranchee vers une forme d'item
neuve.

## 7. Pieges previsibles

1. **Les couleurs de `ART-CP-09` contre `ART-1`.** La ligne enumere *"sujet, couleurs, formes,
   materiaux"* ; `ART-1` interdit *"de quelle couleur est... ? sur une image"*. Le quart *couleurs*
   de la ligne n'est pas servable sous `ART-1`. Ferme-t-elle sans lui ? Q4.
2. **Le texte alternatif donne la reponse.** Pour une ligne dont la competence est de dire ce qu'on
   voit, un texte alternatif descriptif (*"un hippopotame bleu"*) est la reponse lue par le lecteur
   d'ecran. Le precedent est `ce1-solides` : texte identique sur tous les items, qui ne nomme rien
   (`CON-5`). Le garde-fou n'exige que la presence (clause 5), pas le contenu.
3. **`ART-4` et les propositions de `ART-CP-10`.** Designer une oeuvre en texte frole son titre
   (*"La Chambre"*). Aucun garde-fou ne compare une proposition au champ `titre` du registre.
4. **"Deja rencontree" n'est pas garanti en partie.** `ListContentSource.next` tire uniformement parmi
   les items non vus, et une partie compte `nombreDeManches` manches (10 pour `cp-arts-outils`) sur
   jusqu'a 20 items. L'item de `ART-CP-10` peut sortir avant son partenaire de `ART-CP-09`, ou sans
   lui ; et rien ne persiste d'une session a l'autre (`DEC-0028`). Le quatrieme critere de la story
   tient au niveau du paquet, pas de l'experience de l'enfant. Une garantie d'ordre toucherait
   `ContentSource` dans `domain` : `ADR`. Q5.
5. **Lisibilite a 176 px.** Le rendu plafonne a 176 px (`ADR-0082`, table du poids). Les materiaux
   (faience de `oeuvre-hippopotame` contre peinture) et les petits personnages de `oeuvre-moissonneurs`
   et `oeuvre-marche-aux-chevaux` sont a juger a cette taille, pas a 352.
6. **Fidelite** : `oeuvre-marche-aux-chevaux.jpg` montre le cadre dore du tableau. A `nadia-prof`,
   gardienne de la fidelite de la reproduction.
7. **Le prefixe `oeuvre-` est un identifiant, le mot servi est *oeuvre* avec sa ligature.** Le contenu
   destine a l'enfant s'ecrit *œuvre* (`DEC-0002`, amendement du 2026-08-04). La ligature est deja
   servie par plusieurs paquets ; je n'ai pas verifie que `AucunMotServiNeSEcarteDeSonAccentTest`
   attrape un *oeuvre* sans ligature dans un enonce.
8. **Ce que le commit du paquet touche, sur le precedent de `a3655b62` (`cp-arts-outils`) et
   `5b72c213` (`ce1-solides`)** : le YAML, son entree de `catalogue.yaml`, son test de paquet et son
   jeu fautif, une entree de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`
   (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`), l'inventaire de
   `TexteAccentueExposeParLePortEntrantTest`, et `ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION` (56 plus le
   nombre d'items illustres). Sous O1, en plus : `REPRODUCTIONS_SERVIES` et `POIDS_TOTAL_MAXIMUM`
   ramenes aux 11 fichiers, mesures. Premiere cle `enonce` et texte alternatif non vide sur chaque item
   (clauses 5 et 6, deja mordues).

## 8. Questions, routages et actions

| Code | Point | A qui | Bloquant ? |
|---|---|---|---|
| Q1 | `ART-CP-10` : une oeuvre montree et des propositions qui designent en texte des oeuvres deja vues, est-ce rapprocher, ou la contrefacon par description de `competences-cp.md` ligne 75 ? | `nadia-prof` | **oui, pour `ART-CP-10`, avant ecriture** |
| Q2 | Cartouches et signatures en ecriture japonaise visibles sur `oeuvre-grande-vague` **et** `oeuvre-averse-sur-le-pont` : recevables au CP ? (`ADR-0082` point 4) | `nadia-prof` | non pour l'ecriture ; oui pour son Done |
| Q3 | O1 retire de l'arbre 17 reproductions et leurs entrees, livrees par `T5` et acceptees en `DEC-0108`, jusqu'a `T7`/`T8`. Acceptable, ou O2 ? | `camille-po` | **oui, pour V3** |
| Q4 | `ART-CP-09` ferme-t-elle sans le quart *couleurs*, que `ART-1` exclut ? | `nadia-prof` | non pour l'ecriture |
| Q5 | "Deja rencontree" au niveau du paquet suffit-il, sans garantie d'ordre en partie ? | `nadia-prof` | non, sauf si la reponse est non (alors `ADR`) |
| Q6 | `DEC-0109` section 6 et la story attendent du paquet seul la levee du rouge du sprint. C'est faux deux fois (sections 0 et 3) : la levee passe par A1 et A2 | `camille-po` | information |

| Code | Action | Qui |
|---|---|---|
| A1 | Redimensionner `oeuvre-chambre`, `oeuvre-rue-sous-la-pluie`, `oeuvre-grande-jatte` a 352 px de plus grand cote, en une etape ; relever le cliquet a 28 fichiers et au poids mesure ; lancer les deux garde-fous de la section 0 avant de commiter. Les 7,5 Mo deja entres restent dans l'historique : les en retirer est une reecriture, qui demande l'accord de l'utilisatrice | `theo-archi`, sur feu vert de `sofia-sm` |
| A2 | Appliquer O1 ou O2 selon Q3, dans le commit du paquet (O1) ou par un `ADR` prealable (O2, `theo-archi`) | developpeur affecte ; `theo-archi` si O2 |
| A3 | Ecrire le paquet apres Q1, Q2 signalee d'avance (`DEC-0109` section 4) | developpeur affecte par `sofia-sm` |
| A4 | Porte technique : `mvn -B clean verify` sur le reacteur complet, plus les jobs `prefixes`, `chemins` et `outils`, nommes dans le rapport | developpeur, puis `theo-archi` en confirmation |

## 9. Mesure

- **Deux garde-fous a `43ec4411`** : section 0. Journal conserve hors du depot. Lancement dans
  l'arbre principal sans `clean` (le blocage du jar servi, nomme a la section 8 de l'avis precedent, porte sur `clean`).
- **Simulation du paquet** : worktree detache a `43ec4411`, hors de l'arbre, supprime ensuite. Un
  YAML temporaire de 11 items (`enonce` en premiere cle, `illustration`, texte alternatif non vide),
  puis la meme commande restreinte a `LIllustrationDeContenuResoutEtAucuneNeDortTest` : 18 tests,
  3 echecs, 17 noms distincts de reproductions dormantes dans le rapport. JDK 21.0.10.
- **Dimensions et poids** : script Python de poste, non commite, lecture du segment SOFn par la regle
  de `dimensionsJpeg`, sur les 28 fichiers.
- **Images** : inspection visuelle des fichiers servis, a leur taille servie, le 2026-10-04.
- **Non lance** : le reacteur complet et les jobs `prefixes`, `chemins`, `outils`. Le compte de regles
  ArchUnit n'est pas remesure : cet avis ne touche aucun code.
