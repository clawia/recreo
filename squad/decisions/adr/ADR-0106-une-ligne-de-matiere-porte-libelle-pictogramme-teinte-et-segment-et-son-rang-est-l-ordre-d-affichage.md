# ADR-0106 - Une ligne de matiere porte le libelle court, le pictogramme, la teinte et le segment ; son rang est l'ordre d'affichage, et aucune de ces valeurs n'est ecrite ailleurs

- **Statut** : Accepte
- **Date** : 2026-10-11
- **Auteur** : theo-archi
- **Sprint** : hors sprint, chantier de l'accueil par matiere (`DEC-0117`). Avis :
  `squad/sprints/hors-sprint-accueil-vues/avis-faisabilite.md`, sections 5 et 10.
- **Repond a** : `Q-T1` et `Q-T2` de `DEC-0117` ; `APM-1` a `APM-6` de
  `squad/conformite/ARBITRAGE-PREALABLE-accueil-par-matiere.md` ; `E2` de
  `squad/sprints/hors-sprint-accueil-vues/rapport-marc-dev.md`
- **Amende** : `ADR-0049` (point 2, son declencheur est atteint ; point 4, le segment vit dans la ligne de
  matiere) ; `ADR-0043` (decision 1, le role `entree` ne demande plus un habillage permanent)
- **Prolonge** : `ADR-0105`, `ADR-0007`, `ADR-0006`, `ADR-0094` (decision 6), `ADR-0095`
- **Arbre mesure** : worktree `theo-accueil-matieres`, base `9f03ff77` (tip de `round-two`). JDK
  21.0.10, propriete `java.version` des rapports `TEST-*.xml` (`ADR-0071`), enveloppe
  `bash outils/mvn-jdk21.sh`.

## Contexte

`nadia-prof` a arbitre, pour chacune des onze matieres servies, un libelle court (N1), un pictogramme
(N2, dix acceptes, Maths refuse), une teinte (N6, dix acceptees, celle de Histoire des arts refusee
parce qu'elle est grise) et un ordre unique pour tous les niveaux et les deux vues (N5). Ce sont quatre
valeurs par matiere qu'aucun paquet ne porte et qu'il faut ranger quelque part.

**Le declencheur qu'`ADR-0049` point 2 avait ecrit d'avance est atteint, et mesure.** Les valeurs de
`descripteur.matiere` font de 15 a 30 caracteres (`DEC-0117` section 4) ; avec elles, deux tuiles sur huit
au CM1 ont leur texte dans le rembourrage a 744 px (`proposition-ines-accueil-vues.md` section 3). Six
libelles different de la valeur pour une raison qui n'est pas l'accent : "Maths", "Sciences", "Vivre
ensemble", "Anglais", "Musique", "Sport".

**Les chemins les moins chers, et ce qu'ils couteraient.** La maquette d'`ines-design` range la teinte
dans onze regles de feuille par emplacement (`[data-teinte="1"]` a `"11"`) : une douzieme matiere serait
une modification de feuille (`ADR-0006`, risque `R9` de `DEC-0117`). Le service de `marc-dev` (P1) range les
groupes dans l'ordre d'apparition au catalogue, qui differe de N5 aux trois niveaux servis (son rapport,
section 2). Et `jeuxDeLaMatiere(niveau, matiere)` attend la valeur accentuee, alors que l'adresse d'un etage
2 ne porte qu'un segment sans accent (`ADR-0049` point 4) : l'adaptateur devrait traduire l'un en l'autre.

Quatre points etaient laisses ouverts par l'avis et par `ADR-0105` : le segment au port, le role
`data-role` de la tuile et du bouton de vue, la place du bouton, le comportement de la route de vue.

## Decision

1. **Une ligne par matiere servie, dans un seul fichier**,
   `adapters/adapter-content/src/main/resources/matieres.yaml` : `matiere` (la valeur exacte du descripteur,
   cle de jointure), `libelle`, `pictogramme` (un nom de fichier), `teinteFond`, `teinteContour`, `segment`.
   **Le rang de la ligne est l'ordre d'affichage**, le meme a tous les niveaux et dans les deux vues. Le
   chargeur unique la lit (`ADR-0007`) ; `ContentCatalogPort.matieres()` l'expose en
   `application.catalog.Matiere`. Une matiere neuve est une ligne, jamais un gabarit, une feuille ou une
   enumeration.
2. **L'ordre et l'absence de repli vivent dans `PartieService`.** `matieresDuNiveau` range les groupes
   par le rang de leur ligne. Une matiere servie sans ligne fait echouer le regroupement en se nommant :
   aucun libelle de repli n'est invente (`APM-1`). Le build rend ce chemin inatteignable en production
   (regle 2).
3. **La teinte n'atteint le HTML que par deux proprietes personnalisees**, `--teinte-fond` et
   `--teinte-contour`, posees en style en ligne sur une tuile ou un bandeau de l'accueil ; la feuille les
   lit par `var()`. Aucune valeur de teinte n'est ecrite dans une feuille, un gabarit, un script ou une
   source Java. Sur un ecran de jeu, un style en ligne ne declare que la geometrie d'une zone (`APM-5`).
   Aucune teinte n'est un gris (`APM-6`).
4. **Le pictogramme est un fichier SVG** sous `adapters/adapter-web/src/main/resources/static/images/matieres/`,
   identique octet pour octet au fichier arbitre (`APM-2`). Il est rendu en `<img alt="">` a cote de son
   libelle en texte, jamais seul, jamais dans une carte de jeu ni dans une puce de niveau (`APM-3`).
5. **Le segment entre au port (P4 le recoit de l'adresse, P3 le resout).** `jeuxDeLaMatiere(niveau,
   matiere)` est remplace par `groupeDeLaMatiere(niveau, segment)`, qui rend un `Optional<GroupeDeJeux>` ;
   `GroupeDeJeux` porte la `Matiere` entiere au lieu de sa seule chaine. L'adaptateur ne traduit jamais un
   segment en matiere : ce serait une selection dans la vue (`ADR-0044`) et une seconde table de
   correspondance.
6. **Roles** (amendement d'`ADR-0043`). La tuile de matiere declare `data-role="action"` : c'est le choix qui
   fait avancer l'etage 1, comme `.demarrer` sur la liste. Le bouton de vue declare `data-role="entree"`
   dans ses trois etats, chevron de l'etage 2 compris : il ne fait pas avancer l'ecran affiche, il n'en est
   pas la sortie, et la clause de libelle unique interdit `sortie` a tout ce qui ne dit pas "Les jeux".
   `ADR-0043` decision 1 est reecrite : *"Un bouton qui ne fait pas avancer l'ecran affiche et qui n'est pas
   la sortie de cet ecran declare `data-role="entree"`"* ; la mention *"d'un habillage permanent"* tombe, la
   liste reste fermee a trois valeurs et `entree` reste du cote domine.
7. **Le bouton vit dans `#jeu`**, jamais dans l'en-tete (`APM-7`). `hx-post`, `hx-target="#jeu"` : la
   coquille garde une seule cible (`ToutEcranEchangeEstUnDocumentJouableTest.zoneEchangee`).
8. **Les routes, toutes sous un niveau tant que `Q-U1` n'a pas de reponse.**
   - `POST /niveaux/{niveau}/vue/{vue}` ecrit la vue recue (`ADR-0105` decision 2), avec ou sans partie.
     Sans partie, elle rend `accueil :: zoneAccueil` du niveau dans la vue ecrite, pose `niveauActif` et
     l'en-tete `HX-Push-Url: /niveaux/{niveau}`. Avec une partie, elle n'abandonne rien et rend la
     confirmation de la puce de niveau, vers `/niveaux/{niveau}/confirmer`, sans en-tete d'adresse
     (`ADR-0048`). Une vue inconnue rend 404 sans ecrire la session.
   - `GET /niveaux/{niveau}` et `POST /niveaux/{niveau}/confirmer` rendent V2 ou l'etage 1 selon la vue
     de la session ; leurs adresses ne changent pas.
   - `GET /niveaux/{niveau}/matieres/{segment}` rend l'etage 2 quelle que soit la vue de la session
     (l'adresse gouverne, `APM-8`). Avec une partie, la confirmation directe, et
     `POST /niveaux/{niveau}/matieres/{segment}/confirmer` rend ensuite l'etage 2 demande (`APM-11`). Un
     segment que le port ne resout pas rend le niveau sans jeu, phrase `.pas-de-jeu` comprise, comme un
     niveau inconnu aujourd'hui.
   - Aucune route de vue sans niveau. `/`, `/retour` et `/retour/confirmer` ne changent pas avant `Q-U1` :
     ils ne rendent jamais un etage 2 (`APM-9`).

**Ce que cet ADR ne decide pas** : `Q-U1` et `Q-U2` (l'utilisatrice) ; la contradiction du critere "Tenu en
session" d'`HS-ACCUEIL-BASCULE` (`camille-po`, arbitrage de `nadia-prof` section 1) ; les valeurs en attente
(pictogramme de Maths, teinte de Histoire des arts : `ines-design` puis `nadia-prof`) ; le balisage et la
feuille des deux vues (P4).

## Regle executable ArchUnit

### La regle : aucune matiere n'est une constante d'enumeration

Fichier : `bootstrap/src/test/java/com/recreo/archi/LOrdreDesMatieresVientDeLaDonneeTest.java`

```java
@ArchTest
static final ArchRule aucuneMatiereNEstUneConstanteDEnumeration = classes()
        .that().resideInAPackage("com.recreo..")
        .should(neJamaisEnumererUneMatiere())
        .as("ADR-0106 : la matiere et son ordre restent une donnee, jamais une constante d'enumeration");
```

Les noms refuses sont **derives de la donnee** par le chargeur reel : matiere, libelle, segment et nom de
pictogramme de chaque ligne, replies en identifiant (accents retires, tout autre signe en souligne, sans
casse) - `FRANCAIS`, `MATHEMATIQUES`, `MATHS`, `EMC`, `EPS`, `QUESTIONNER_LE_MONDE`... Une matiere neuve est
gardee le jour ou sa ligne entre. **Ce qu'elle interdit reellement** : le tri en une ligne
`Comparator.comparing(Matiere::ordinal)` sur une enumeration des matieres, chemin le moins cher d'un ordre
ecrit en code. **Ce qu'elle ne voit pas** : une enumeration aux constantes neutres (`M1, M2`), un tableau de
chaines ; la clause 3 ci-dessous les attrape par leur effet.

Contre-exemple (`ADR-0003`) :
`bootstrap/src/test/java/com/recreo/adapter/web/contreexemple/MatiereEnumereeContreExemple.java`, morsure
`LOrdreDesMatieresVientDeLaDonneeTest.uneEnumerationDesMatieresEstRefusee`.

### Les autres regles, et pourquoi ce ne sont pas des regles ArchUnit

Elles jugent un fichier de donnees, des fichiers SVG, des gabarits, une feuille et des documents rendus :
rien de cela n'est du bytecode. Chacune est un test JUnit execute par `mvn verify`.

1. **`adapters/adapter-content/src/test/java/com/recreo/adapter/content/ChaqueMatiereServieASaLigneDeMatiereTest.java`**
   (`APM-1`, `APM-6`). Sur les matieres servies et les lignes lues par le chargeur reel : chaque matiere
   servie a exactement une ligne et chaque ligne nomme une matiere servie, par egalite exacte ; une ligne qui
   ne differe que par le repliage d'`ADR-0049` est nommee comme telle ; libelle, pictogramme et segment
   uniques ; segment `[a-z0-9]+(-[a-z0-9]+)*` ; pictogramme `<meme forme>.svg` ; teinte `#rrggbb` ; un
   libelle qui n'est qu'une matiere servie privee de son accent est refuse ; **aucune teinte de saturation
   TSL inferieure a 0,25**, sur le fond comme sur le contour. Seuil mesure : la paire refusee vaut 0,18 et
   0,18, la plus faible des dix paires acceptees 0,47 (fond) et 0,37 (contour). **Cliquet exact** :
   `TEINTES_GRISES_EN_ATTENTE = {"Histoire des arts"}`, qui doit designer une ligne encore grise.
2. **Repli** : `PartieServiceTest.uneMatiereServieSansLigneFaitEchouerLeRegroupementEnSeNommant` ;
   **ordre** : `PartieServiceTest.matieresDuNiveauSuitLOrdreDesLignesDeMatiere`, ou l'ordre des lignes
   differe a la fois de l'ordre d'apparition et de l'alphabet.
3. **`LOrdreDesMatieresVientDeLaDonneeTest.lesGroupesDeChaqueNiveauSuiventLOrdreDesLignes`** (`APM-4`), sur
   le catalogue reel a chaque niveau servi.
4. **`bootstrap/src/test/java/com/recreo/archi/LePictogrammeDeMatiereEstUnFichierArbitreTest.java`**
   (`APM-2`) : chaque pictogramme nomme est depose sous `static/images/matieres/` avec l'empreinte SHA-256
   du fichier arbitre, aucun fichier depose n'est orphelin, aucune empreinte ne survit a son nom. **Cliquet
   exact** : `EN_ATTENTE = {"mathematiques.svg"}`, nomme et non depose. `.gitattributes` gagne
   `**/static/images/matieres/** -text`, meme motif qu'`ADR-0014` : sans elle, un poste en
   `core.autocrlf=true` extrait d'autres octets.
5. **`AucunGabaritNiFeuilleMaisonNeNommeUnContenuParticulierTest`** (`APM-1` "nulle part ailleurs", `APM-5`)
   gagne le genre `DONNEE_DE_MATIERE` : matiere, libelle, pictogramme et les deux teintes de chaque ligne
   sont refuses en litteral dans tout gabarit, feuille maison, script maison et source Java de production.
   C'est ce qui refuse les onze regles par emplacement de la maquette. **Le segment n'y entre pas, mesure** :
   sur la production des cinq modules, "francais" figure dans 5 fichiers et "sport" dans 10 ("transport") ;
   le juger par sous-chaine serait la faute de s05.
6. **`ToutEcranEchangeEstUnDocumentJouableTest`** (`adapter-web`), sur les documents assembles :
   `aucunEcranDeJeuNePorteUneTeinteDeMatiere` (`APM-5` : sur un document qui porte `.ecran-de-jeu`, un style
   en ligne ne declare que `left`, `top`, `width`, `height`, liste permise mesuree sur l'unique style des
   gabarits) ; `lePictogrammeDeMatiereNEstJamaisSeulEtNeParlePas` (`APM-3` : aucune image dans `.demarrer`
   ni `.chip-niveau` ; tout `img` servi depuis `/images/matieres/` a un `alt` vide et un parent dont le texte
   n'est pas vide) ; **cliquet de vacuite** `aucunPictogrammeDeMatiereNEstEncoreServi`, qui rougit au premier
   pictogramme servi et que P4 remplace par l'exigence inverse.
7. **`bootstrap/src/test/java/com/recreo/bootstrap/config/ContenuConfigurationTest.java`** : le bean publie
   sert la donnee de matiere du chargeur (Consequences, point 2).

### Preuve de morsure sur la production, methode comprise

Treize mutations, une a la fois, sur l'arbre de ce commit, puis annulees (`git checkout` depuis l'index,
`git status --short` sans fichier modifie hors index apres chacune). Commande : `bash outils/mvn-jdk21.sh -B
-q -o -pl bootstrap -am test -Dtest=<les neuf classes qui portent ces regles>`. Perimetre : `bootstrap` et ses
quatre amonts, c'est-a-dire les cinq modules, mais neuf classes de test, pas la suite. Sans mutation : 94
tests, 0 echec. Chaque ligne a ete lue apres deux controles (`lessons.md`, s56) : le script a trouve son motif
une fois et une seule, et les rapports XML comptent plus de zero test. Le nombre de tests varie parce que le
reacteur s'arrete au premier module rouge ; deux mutations sont relancees avec
`-Dmaven.test.failure.ignore=true` pour atteindre `bootstrap`.

| | Mutation de la production | Tests rouges (rapports XML) |
|---|---|---|
| M1 | le tri par rang retire de `PartieService` : l'ordre d'apparition revient | `matieresDuNiveauSuitLOrdreDesLignesDeMatiere`, et sur le catalogue reel `lesGroupesDeChaqueNiveauSuiventLOrdreDesLignes` (94 tests, 2 echecs) |
| M2 | le controle de ligne neutralise | `uneMatiereServieSansLigneFaitEchouerLeRegroupementEnSeNommant` |
| M3 | la ligne de Francais deplacee en fin de fichier | `lOrdreDesLignesEstCeluiQueNadiaProfATrancheEnN5` |
| M4 | le fond de Sport remplace par le gris refuse `#d8dde4` | `laDonneeDeMatiereServieTientToutesSesClauses` |
| M5 | la ligne de Sport retiree | 7 echecs : la clause de donnee, l'epinglage N5, `eps.svg` depose sans ligne, `EPS` absent des noms derives, l'ordre reel et deux clauses de l'inventaire, que le service fait echouer en nommant la matiere |
| M6 | la cle `"Français"` ecrite `"Francais"` | la clause de donnee (message du repliage) et l'epinglage N5 |
| M7 | `recreo.css` recoit la regle par emplacement de la maquette, teinte reelle | `aucunGabaritNiFeuilleMaisonNeNommeUnContenuParticulier` |
| M8 | `style="--teinte-fond: #cfe3f6"` sur la racine de `fragments/manche.html` | `aucunEcranDeJeuNePorteUneTeinteDeMatiere` |
| M9 | une image a `src` calcule dans le bouton `.demarrer` d'`accueil.html` | `lePictogrammeDeMatiereNEstJamaisSeulEtNeParlePas` (16 occurrences) et le cliquet de vacuite |
| M10 | l'encre de `static/images/matieres/francais.svg` changee | `chaquePictogrammeNommeEstLeFichierArbitre` |
| M11 | `enum MatiereMutee { FRANCAIS, MATHS }` dans `application.catalog` | `aucuneMatiereNEstUneConstanteDEnumeration` |
| M12 | le bean de `ContenuConfiguration` sans `matieres()` | `ContenuConfigurationTest` et deux clauses de `TexteAccentueExposeParLePortEntrantTest` |
| M13 | une image `alt="Maths"` seule dans un `div` de l'accueil | `lePictogrammeDeMatiereNEstJamaisSeulEtNeParlePas` ("texte alternatif non vide", "sans texte a cote de lui") et le cliquet de vacuite |

**Deux mesures ecartees, et pourquoi.** La premiere passe des douze premieres mutations n'a lance aucun test :
le script de mutation ne s'analysait pas, et le controle du motif l'a arrete a chaque ligne. La premiere forme
de M9 posait l'image dans un bouton qui porte `th:text` : le rendu l'efface, seul le garde-fou des
litteraux a rougi (le nom `francais.svg`), et la clause `APM-3` est restee verte a raison. La forme retenue
calcule `src` et garde le titre dans un `span`.

Une derniere mesure, sur du materiau reel : la premiere version de `ChaqueMatiereServieASaLigneDeMatiereTest`
prenait la premiere ligne comme "le libelle accentue" ; M3 l'a fait rougir pour ce motif, etranger a la
regle. Elle cherche desormais le premier libelle accentue de la donnee.

**Compte des regles ArchUnit** : 39 declarations `ArchRule <nom> =` dans `src/test/java` des cinq modules
du `pom.xml` agregateur apres ce commit, 38 avant ; methode de `LesGardeFousSontMisALEpreuveTest.toutesLesRegles`,
reproduite par `grep -P 'ArchRule\s+\w+\s*='` sur l'arbre de travail puis sur `HEAD`.

### Ce qui n'est pas encodable et reste a l'audit de `nadia-prof`

Aucun automate de ce depot ne juge ce qui suit ; un vert ne l'anticipe pas.

1. Que les pictogrammes se reconnaissent a leur taille rendue, a 744 et a 390, et que le pictogramme de Maths
   refait se lise "maths" (`APM-2` : la regle 4 garde l'octet, pas le sens).
2. Que la teinte qui remplacera le gris ne se lise pas "desactive" : le seuil refuse un gris, il ne dit rien
   d'une teinte saturee terne.
3. Que deux teintes voisines se distinguent, y compris pour un enfant daltonien (aucune simulation faite,
   `ines-design` le dit) ; la regle 1 n'exige pas l'unicite des teintes, la couleur etant un doublon.
4. Que "Par matière" et "Tout voir" ne se confondent pas avec "Les jeux" ; qu'un bandeau colle ne recouvre
   pas la carte qui recoit le focus ; que le premier ecran de V2 dise la matiere ; qu'un etage 2 d'une carte
   ne paraisse pas casse ; le parcours complet (section 6 de son arbitrage, points 3 a 7).
5. L'orthographe des libelles : `relecteur-pedagogique` (`DEC-0002`), l'inventaire d'`ADR-0105` regle 1 les
   rendra executables quand P3 les exposera au port entrant.

`APM-7` a `APM-11` sont des comportements de route et de gabarit ; ils sont gardes par les tests du lot P4,
dont la liste est dans l'avis, section 10.

## Consequences

- Les quatre valeurs d'une matiere et son rang vivent dans une ligne. Ajouter une matiere coute une ligne,
  un fichier SVG arbitre et son empreinte : trois fichiers, aucun gabarit.
- **Mauvaise consequence, mesuree avant le commit** : `matieres()` a une valeur par defaut vide, pour que
  les treize doubles de test ecrits en lambda compilent encore (dix fichiers de trois modules, methode :
  `git grep 'ContentCatalogPort [a-zA-Z]* = () ->'` sur l'index). Elle a menti des sa premiere execution : le
  bean de `ContenuConfiguration`, une lambda qui met le catalogue en cache, servait l'accueil sans aucune
  ligne, et `TexteAccentueExposeParLePortEntrantTest` a rougi ("Mathematiques est servie sans ligne"). Le bean
  est corrige et `ContenuConfigurationTest` le garde. Ce qui me ferait passer la methode en abstraite : une
  deuxieme implementation de production du port.
- **La teinte grise refusee est dans la donnee servie**, sous un cliquet qui ne l'exempte que tant qu'elle est
  grise, et le fichier de Maths est nomme sans etre depose. Aucune vue ne les montre avant P4, et P4 attend
  `APM-2` (`nadia-prof`, routage de son arbitrage).
- `GroupeDeJeux` porte encore la seule chaine de matiere ; P3 la remplace par la `Matiere`, et l'inventaire
  d'`ADR-0105` regle 1 recevra alors les libelles, noms de pictogramme, teintes et segments, chacun avec son
  motif.
- Le segment n'est pas garde contre une ecriture en dur dans un gabarit (regle 5, mesure). Une route ecrite
  pour une seule matiere se lit dans le diff ; elle ne rougit pas.
- Le port entrant garde sept methodes : `groupeDeLaMatiere` remplace `jeuxDeLaMatiere`, il ne s'y ajoute pas.

## Alternatives considerees

1. **Onze regles de feuille par emplacement de teinte** (maquette d'`ines-design`). Ecartee : une matiere
   neuve deviendrait une modification de feuille, et le gris refuse n'aurait aucun juge. La regle 5 la refuse.
2. **Un champ de libelle, de pictogramme ou de segment par paquet.** Ecartee : 147 copies d'une valeur par
   matiere, la dette 2, et `ADR-0049` point 2 le refusait deja pour ce motif.
3. **Une enumeration ou une liste Java des matieres**, comme `OrdreDesNiveaux` pour les niveaux. Ecartee :
   quatre niveaux sont une sequence scolaire stable (`DEC-0090`) ; les matieres portent quatre valeurs
   chacune et changent avec le catalogue. La regle ArchUnit la refuse.
4. **Le port recoit la matiere, l'adaptateur traduit le segment.** Ecartee : une table de correspondance dans
   la vue et une selection hors du port (`ADR-0044`).
5. **Rendre `matieres()` abstraite.** Ecartee pour l'instant : treize doubles de test a reecrire dans trois
   modules pour un seul bean de production, qui est garde. Voir Consequences.
6. **Des champs facultatifs pour les deux valeurs en attente.** Ecartee : un type taille pour une dette de
   quelques jours, que chaque vue aurait a traiter. Les cliquets exacts nomment la dette et tombent avec elle.
7. **Un cliquet de poids sur les pictogrammes**, patron d'`ADR-0073` (clause 5 de mon avis). Ecartee : dix
   fichiers de 312 a 518 octets ; l'empreinte garde deja chaque octet. Ce qui me ferait changer d'avis : un
   pictogramme matriciel.
8. **Un emoji comme pictogramme.** Ecartee par `nadia-prof` (`Q7`) : il est dessine par le systeme.
