# Entre s56 et s57 - Avis de faisabilite (`theo-archi`) - les sept lignes de rang C qui attendent un dessin

- **Date** : 2026-10-13.
- **Base** : `round-two` a `9d101ea0`. Toutes les lectures et commandes ci-dessous ont tourne sur cet
  arbre (branche `s57-theo`, qui n'y ajoute que l'amendement A2 d'`ADR-0065` et le controle de son
  point 1).
- **Demande** : `DEC-0120` A.36, ligne `theo-archi` : *"l'avis de faisabilite des sept dessins (A.31)"*,
  du avant tout chiffrage (`DEC-0099` section 4 ter).
- **Emplacement** : `squad/sprints/inter-s56/`, cree pour cet avis sur reponse de l'utilisatrice du
  2026-10-13 (le dossier de s57 n'existe pas sur `round-two`). Section 3 : ce que ce nom engage.
- **Ce que cet avis ne vaut pas** : il ne produit aucun dessin, ne mesure aucun rendu, n'estime aucun
  poids et ne juge aucune forme pedagogique. Une forme degradee est nommee comme telle et routee a
  `nadia-prof`. Il ne modifie pas `DEC-0120`.

---

## 1. Methode

1. **La liste** est celle de `DEC-0120` A.31 (A.13) : `QLM-CP-12`, `HG-CM1-09`, `HG-CM1-10`,
   `HDA-CM1-09`, `QLM-CE1-07`, `QLM-CE1-09`, `QLM-CE1-11`. L'objet de chaque ligne est lu dans sa
   cellule de `squad/programme/MATRICE-couverture.md`.
2. **La mecanique** est la seule qui designe un lieu sur une image : `designation`
   (`domain/src/main/java/com/recreo/domain/mechanics/designation/Designation.java`, `ADR-0080`).
   `grep -c 'mecanique: designation' adapters/adapter-content/src/main/resources/catalogue.yaml` rend
   **3**.
3. **Les contraintes qu'un dessin de designation doit tenir**, chacune lue a sa source :
   - zone = rectangle en pourcentages entiers du dessin : `CadreDeZone(int x, int y, int largeur, int hauteur)`
     (`domain/src/main/java/com/recreo/domain/content/CadreDeZone.java`) ;
   - trois zones au moins par item : `ZON-8`
     (`squad/conformite/ARBITRAGE-PREALABLE-s29-le-palier-2-ce-qu-un-paquet-de-designation-doit-apporter.md`) ;
   - deux zones d'un item ne se chevauchent pas : `DeuxZonesDUnItemNeSeChevauchentJamaisTest` ;
   - plancher de 16 unites par cote de zone sur un dessin carre, derive du rendu a 296 px
     (`ADR-0080`, amendement A2bis). Le garde-fou 3 juge a 352 px ; le deriver de la largeur servie est
     `A3` d'`ADR-0080`, non ecrit (`DEC-0120`, tableau du rang C) ;
   - aucun `<text` ni `<tspan` dans un dessin : `LIllustrationDeContenuResoutEtAucuneNeDortTest` ;
   - un item de designation porte `enonce`, `illustration`, `illustrationTexteAlternatif`, `zones`,
     `bonneZone`, `indice`, `explication`, `texteAVoixHaute`, `emplacementDuLibelleDeZone`
     (`domain/src/main/java/com/recreo/domain/content/ZoneADesigner.java`) : **aucun champ de texte
     ni de tableau d'appui**.
4. **Le materiau** : `git ls-files adapters/adapter-web/src/main/resources/static/illustrations | wc -l`
   rend **56**. Aucun nom ne contient `carte`, `globe`, `planisph`, `france`, `monde`, `circuit`,
   `musee` ni `legende` ; seul `plan-classe.svg` contient `plan`.
5. **Le cout commun a tout lot illustre** :
   `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest` porte
   `POIDS_TOTAL_MAXIMUM = 69203` et `POIDS_D_UN_DESSIN_MAXIMUM = 4633`. La somme des 56 fichiers lus
   par `git show HEAD:<fichier> | wc -c` (fins de ligne LF, celles du depot) vaut **69 203** : le
   cliquet est exactement plein. Tout dessin neuf le deplace dans son propre lot, et un dessin de
   designation s'inscrit dans `DESSINS_DE_DESIGNATION` du meme test. `plan-classe.svg` pese 3 875
   octets par la meme methode (3 933 dans un arbre extrait en CRLF : c'est la valeur LF qui compte).

## 2. Verdicts

| Code | Ligne | Faisable avec les mecaniques existantes | Ecart nomme |
|---|---|---|---|
| F1 | `QLM-CP-12` | **oui**, `designation` | Seul le dessin manque. La France sur un planisphere entier passerait sous le plancher de 16 unites : le dessin est a l'echelle de l'Europe ou de la France. |
| F2 | `HG-CM1-09` | **oui**, `designation`, deux dessins (carte de France, planisphere) | Dessins manquants. Un planisphere en 2:1 demande environ 30 unites de hauteur par zone : 296 px de large donnent 148 px de haut, et 44 px valent 29,7 % de 148. C'est de l'arithmetique, pas une mesure ; precedent : la frise (`viewBox` 640 x 320), dont la plus petite zone fait 33 de haut (`grep -o 'hauteur: *[0-9]*'` sur `cp-frise-de-la-journee.yaml`). Le garde-fou 3 ne verrait pas une zone trop basse tant qu'`A3` n'est pas ecrit. Villes et reperes physiques sont petits : une couverture partielle de la ligne est probable, jugement de `nadia-prof`. |
| F3 | `HG-CM1-10` | **non, et pas pour un motif de dessin** | Correction de la liste, section 2.1. |
| F4 | `HDA-CM1-09` | **oui**, `designation` sur un plan de musee | Seul le dessin manque. Precedent : `plan-classe.svg` (`QLM-CP-11`), 3 875 octets, sous le maximum de 4 633. |
| F5 | `QLM-CE1-07` | **oui**, `designation` | Seul le dessin manque. Les elements du circuit sont des zones ; *"l'ampoule brille-t-elle"* se joue avec trois circuits sur un meme dessin, le circuit ferme etant la bonne zone. Condition : ouvert ou ferme se lit a la forme, jamais a la seule couleur (`COU-2`). |
| F6 | `QLM-CE1-09` | **en partie** | Le plan est faisable (precedent F4). **La legende n'a pas de place** : pas de texte dans le dessin, et `ZoneADesigner` n'a aucun champ d'appui. La seule forme possible en l'etat donne le sens du symbole dans l'enonce, ce qui retire la lecture de la legende : forme degradee, recevabilite a `nadia-prof`. La forme fidele demande un `ADR` (un support de legende pour la designation). L'itineraire ne se suit pas : un seul geste par manche, seule l'arrivee se designe. |
| F7 | `QLM-CE1-11` | **oui**, `designation` | Seul le dessin manque. Meme geometrie que F2, ou deux globes sur un dessin carre, ou le plancher de 16 unites tient. |

**Bilan** : sur les sept lignes, 5 sont faisables des que leur dessin existe (F1, F2, F4, F5, F7),
1 en partie (F6), 1 n'a pas pour manque un dessin (F3). Comptes faits sur le tableau ci-dessus.

### 2.1 Correction : `HG-CM1-10` n'est pas une ligne dont le seul manque est un dessin

`DEC-0120` A.31 la range parmi *"les lignes de rang C dont le seul manque est un dessin"*. Deux
fichiers du depot disent le contraire :

- sa cellule de la matrice : *"et le lieu de vie de l'enfant n'est pas connu du produit (`DEC-0028`,
  aucune persistance)"* ;
- `DEC-0120` lui-meme, tableau du rang C : *"`HG-CM1-10` : le lieu de vie de l'enfant n'est pas connu
  du produit (`DEC-0028`)"*.

Un dessin de carte ne leve pas ce second obstacle : la ligne demande a l'enfant de situer **son**
lieu de vie, que le produit ne connait pas et ne conservera pas. Un emboitement d'echelles generique
serait une autre competence (`ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md`,
ligne `HG-CM1-10`). **Les lignes dont le seul manque est un dessin sont donc six**, pas sept.
Reclasser `HG-CM1-10` dans A.31 appartient a `camille-po` ; cet avis ne modifie pas `DEC-0120`.

## 3. Le nom du dossier `inter-s56`

Verifie avant de l'ecrire, sur la base de la section 1 :

- **Aucun automate ne l'interdit.** `outils/rapport-tokens.py` lit `squad/sprints/` par
  `RE_SPRINT_DIR = re.compile(r"^(s\d+)(?:-(.*))?$")` et passe un dossier qui ne correspond pas sans
  message ; aucun test Java ne liste `squad/sprints/` (`git grep -n "squad/sprints" -- '*.java'` ne
  rend que des references en commentaire) ; `outils/rapport-tokens-journal-test.py` fabrique son
  propre `s22-exemple`. La preuve executee est dans le message du commit qui cree ce fichier.
- **Consequence** : le temps passe sur cet avis n'entre dans aucune fenetre de sprint, et
  `rapport-tokens.py` le reporte sur la ligne `hors sprint`, comme il le fait deja pour
  `squad/sprints/hors-sprint-accueil-vues/`.
- **Un ecart a une convention non executable** : `squad/README.md` section 2 demande que le tri
  alphabetique d'un dossier donne le tri chronologique, et nomme `sNN-<slug>` comme forme d'un
  dossier de sprint. `inter-s56` se trie avant `s01-...`. Le precedent `hors-sprint-accueil-vues`
  porte le meme ecart. Je ne le corrige pas : le nom a ete donne par l'utilisatrice, et la
  convention appartient a `sofia-sm`.

## 4. Questions ouvertes

- **Q1** (`camille-po`) : reclasser `HG-CM1-10` dans `DEC-0120` A.31 (section 2.1). Le compte des
  lignes de rang C n'en change pas ; c'est la sous-liste *"seul manque : un dessin"* qui passe de sept
  a six.
- **Q2** (`nadia-prof`) : la forme degradee de la legende de `QLM-CE1-09` (F6) est-elle recevable ?
  Si non, la forme fidele demande un `ADR` avant tout lot.
