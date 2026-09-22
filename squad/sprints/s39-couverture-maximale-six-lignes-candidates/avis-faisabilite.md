# s39 - Avis de faisabilite (`theo-archi`) - `S39-ENERGIE`, `S39-OUTILS`, `S39-SOLIDES`

- **Date** : 2026-09-22
- **Base** : `round-two` a `5a7a2312`, stories de `camille-po` dans `docs/backlog/backlog.md`,
  section "Sprint 39" (non commitees a l'heure de cet avis). **Sections 8 et suivantes** :
  `round-two` a `502dcca6`, qui porte l'arbitrage prealable de `nadia-prof`.
- **Perimetre de cet avis** : **deux** lots sur les six a sa premiere redaction, ceux que `DEC-0092`
  dispense d'arbitrage pedagogique prealable - `S39-ENERGIE` (`SCI-CM1-04`) et `S39-OUTILS`
  (`ART-CP-11`). **`S39-SOLIDES` (`MAT-CE1-16`) s'y ajoute en section 8**, l'arbitrage prealable de
  `nadia-prof` ayant ete rendu depuis
  (`squad/conformite/ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md`, section 4).
  `S39-DEN`, `S39-LONGUEUR` et `S39-MONNAIE` **ne sont pas instruits ici**.
- **Ce que cet avis ne vaut pas** : il ne vaut pas livraison. L'affectation nominative du
  developpement revient a `sofia-sm` (`DEC-0092`, section 5, reconduisant `DEC-0091`). Aucun code
  n'est ecrit avec cet avis.

---

## 1. Verdicts

**`S39-ENERGIE` : faisable en l'etat, sans `ADR` neuf et sans regle neuve.** C'est le cas le plus
banal du depot : un paquet statique de type `question` de plus dans un lot de matiere deja servi
(`Sciences et technologie` / `CM1`, 8 paquets aujourd'hui). Complexite **basse**.

**`S39-OUTILS` : faisable en l'etat, sans `ADR` neuf et sans regle neuve, mais ce n'est pas le
meme cas.** Le mecanisme est identique ; ce qui change est que **`Arts plastiques` serait la
treizieme matiere du corpus servi et la premiere de cette matiere**. Complexite **basse cote
architecture**, le cout reel etant editorial et pedagogique. Section 4.

## 2. Le mecanisme existe deja, et aucun `ADR` neuf n'est du

La question posee - *existe-t-il un mecanisme du produit qui couvre le choix textuel pour du
contenu de connaissance fixe, sans code neuf ?* - a deja trois reponses ecrites, et elles se
composent :

- **`ADR-0004`** decouple la mecanique du contenu : `bandit-manchot` ne connait pas la matiere
  qu'elle sert.
- **`ADR-0007`** impose **un seul chargeur pilote par un schema** : un paquet de type `question`
  entre par sa donnee, pas par une classe.
- **`ADR-0096`** a acte que **le paquet statique est la norme du depot**, le generatif
  l'exception, et que le choix du moyen est reversible sans toucher aux garde-fous.

**Preuve, sur du materiau reel plutot que sur mon estimation.** Commit `712c293b` (`[LEA]`, s30),
qui a livre deux paquets de sciences CM1 servant `SCI-CM1-05` et `SCI-CM1-17` - exactement le
regime que la story de `S39-ENERGIE` invoque : **7 fichiers, 512 lignes ajoutees, zero ligne de
Java de production**.

Je n'ecris donc **aucun `ADR`** pour ces deux lots. Un `ADR` qui redirait ce que `ADR-0004`,
`ADR-0007` et `ADR-0096` decident deja serait un `ADR` decoratif, et ecrire un `ADR` apres coup
pour documenter une pratique deja reglee est precisement ce que mon mandat m'interdit.

**Ce qui me ferait changer d'avis, ecrit maintenant** : si `nadia-prof` refusait la liste finie au
motif de la repetition sur une partie de dix manches, la question du generatif se rouvrirait - et
elle se paierait alors en production seule, les garde-fous portant la propriete et jamais le moyen
(`ADR-0096`, decision 3).

## 3. `S39-ENERGIE` (`SCI-CM1-04`) - fichiers concernes

Derives du commit `712c293b`, un paquet au lieu de deux :

| Fichier | Nature |
|---|---|
| `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-<nom>.yaml` | paquet neuf, `type: question`, `lignesDeReferentiel: ["SCI-CM1-04"]` |
| `adapters/adapter-content/src/main/resources/catalogue.yaml` | une entree, titre et consigne propres |
| `adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCm1Sciences<Nom>Test.java` | garde-fou du paquet |
| `adapters/adapter-content/src/test/java/com/recreo/adapter/content/LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java` | inventaire d'exemption du domaine numerique, une entree avec son motif **mesure** |
| `bootstrap/src/test/java/com/recreo/bootstrap/catalogue/TexteAccentueExposeParLePortEntrantTest.java` | inventaire des chaines exposees par le port entrant |

**Zero fichier de production Java, zero schema, zero contrat.**

## 4. `S39-OUTILS` (`ART-CP-11`) - ce qui differe, et ce qui ne differe pas

**Ce qui ne differe pas** : la meme liste de fichiers qu'en section 3, avec
`adapters/adapter-content/src/main/resources/contenu/cp-arts-<nom>.yaml` et
`lignesDeReferentiel: ["ART-CP-11"]`.

**Ce qui differe : `Arts plastiques` serait une matiere neuve du corpus servi.** Comptage du
2026-09-22 sur `adapters/adapter-content/src/main/resources/contenu/` (78 fichiers lus, descripteur
par descripteur) : **12 couples (matiere, niveau) servis, aucun en arts plastiques**. J'ai verifie
ce que cela coute **structurellement**, et la reponse est : rien.

1. **Aucune enumeration de matieres en production.** `matiere` est un champ de
   `domain/.../ContentDescriptor` ; aucune classe de production, aucun gabarit Thymeleaf, aucune
   feuille de style ne nomme une matiere. Recherche sur `"Questionner le monde"` - la derniere
   matiere neuve entree (s27) - hors du repertoire de contenu : **que des tests**.
2. **Aucun garde-fou a perimetre enumere par matiere.**
   `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` (`ADR-0049`) ne porte aucune liste de
   matieres : il exige une seule graphie par matiere, ce qu'une matiere neuve satisfait par
   construction. `UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest` (`ADR-0079`)
   derive ses lots des descripteurs ; ses assertions de rapprochement ne portent que sur le lot
   `Questionner le monde / CP` et un lot neuf ne les touche pas.
3. **Le code de ligne est deja alloue.**
   `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest` (`ADR-0081`) lit les codes dans
   `squad/programme`, jamais dans une liste. `ART-CP-11` y est
   (`squad/programme/arts-plastiques/competences-cp.md`, ligne 47) et `SCI-CM1-04` aussi
   (`squad/programme/sciences-et-technologie/competences-cm1.md`, ligne 50). Rien a allouer.

**Le cout reel de ce lot est donc editorial, pas architectural**, et il vit sous une contrainte que
le lot sciences n'a pas : **c'est du CP**.

## 5. Garde-fous qui mordent sur ces deux lots

Aucun n'est a ecrire ; tous sont a tenir. Ceux qui decident la forme du paquet, donc a lire
**avant** d'ecrire un item et non apres un rouge :

| Regle | Ce qu'elle impose ici |
|---|---|
| `ADR-0023` | le paquet offre **strictement plus** d'items que la partie n'a de manches : 11 items pour `nombreDeManches: 10`, ce que font les paquets de sciences deja fermes |
| `ADR-0063` | au plus **trois** propositions par item |
| `ADR-0032` / `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` | deux items d'un meme paquet ne presentent jamais le meme ecran |
| `ADR-0086` / `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` | la longueur ni la forme des propositions ne designent la bonne reponse |
| `ADR-0067` (`TYP-1`) | blanc insecable avant `?` et `:` dans tout enonce francais neuf |
| `ADR-0065` / `AucunMotServiNeSEcarteDeSonAccentTest` | chaque mot servi est juge sur le lexique exterieur - **point de vigilance sur le vocabulaire plastique** (`esquisse`, `argile`, `fusain`) |
| `ADR-0049` | `Arts plastiques` s'ecrit d'une seule graphie dans toute la donnee |
| **`ADR-0089`** | **`S39-OUTILS` seulement** : au CP, **au plus quinze mots et deux phrases par enonce** (`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`). Contrainte reelle sur une definition de vocabulaire - *"Avec quel outil peint-on ?"* passe, une glose ne passe pas |

Conditions pedagogiques opposables, rappelees pour memoire et **non arbitrees par moi** : `ART-1`
(aucune reponse portee par une couleur percue) et `ART-2` (aucune question sur le ressenti) sur
`S39-OUTILS` ; `SCI-1` a `SCI-4` et `FER-1` a `FER-3` sur `S39-ENERGIE`. `nadia-prof` a deja tarife
les arts plastiques CP a *"non au-dela d'`ART-1`"* (`squad/conformite/ARBITRAGE-PREALABLE-s32-trois-
instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md`, ligne 524) :
c'est ce qui rend l'absence d'arbitrage prealable coherente, et non une omission.

## 6. Risques

**R1 - l'inventaire d'exemption du domaine numerique pourrit en silence (les deux lots).**
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` demande un **motif mesure** par paquet
exempte. Ce fichier porte deja la trace de sa propre derive : les motifs de trois paquets
`cp-qlm-*` dataient du 2026-09-02 et n'avaient pas suivi le lot de s28 qui les a portes de huit a
quatorze items, **deux d'entre eux etant faux sur la forme** ; recomptes le 2026-09-10. Un motif
recopie d'un paquet voisin au lieu d'etre compte sur le paquet livre reproduit ce defaut.
**Parade** : compter sur le paquet servi, ecrire le compte et sa date.

**R2 - `ART-CP-11` est du vocabulaire, et le vocabulaire se heurte a `ADR-0089` (S39-OUTILS).**
C'est le seul endroit ou un rouge est previsible. Il est mecanique et se voit au premier `verify`.

**R3 - `SCI-CM1-04` touche a l'energie, ou la simplification fausse est facile (S39-ENERGIE).**
*"Le soleil produit de l'electricite"* est le genre d'item qu'un garde-fou ne verra jamais et que
`nadia-prof` refusera. Hors de ma main, nomme ici pour qu'il ne soit pas decouvert au Done.

**R4 - `-am` sur toute mesure partielle** (`CLAUDE.md`). `mvn -pl adapters/adapter-content test`
sans `-am` mesure le depot local, pas le worktree. La porte reste `mvn -B clean verify` sur la
racine, et la porte technique est **l'ensemble des jobs de `.github/workflows/ci.yml`**, pas
`mvn verify` seul.

## 7. Ce que cet avis ne dit pas

Il ne dit pas quels faits, quels outils ni quels exemples de conversion retenir - cela reste a
l'auteur du paquet puis a `nadia-prof`. Il ne prononce aucun Done pedagogique. Il ne dit pas si
ces deux lignes passeront `couvert-valide` : `FER-2` et l'audit d'items sont dans la seule main de
`nadia-prof`.

---

# `S39-SOLIDES` (`MAT-CE1-16`) - avis de faisabilite

- **Date** : 2026-09-22, apres l'arbitrage prealable de `nadia-prof` du meme jour.
- **Question posee** : le mecanisme de rendu visuel construit pour les figures planes CP
  (`FIG-1` a `FIG-4`, s25) est-il reutilisable tel quel pour cinq solides usuels, adaptable, ou
  faut-il un chantier neuf ?
- **Methode** : lecture du code de production et des garde-fous sur `502dcca6`, plus deux commits
  d'historique cites avec leur empreinte. **Aucun `mvn` n'a ete lance pour cet avis** : il n'ecrit
  aucune ligne de code, et tout ce qu'il affirme se lit dans l'arbre. Les comptes ci-dessous sont
  des lectures de fichiers, pas des mesures de build ; quand j'estime au lieu de mesurer, je
  l'ecris.

## 8. Verdict

**Le mecanisme est reutilisable tel quel. Aucun chantier technique neuf, aucun `ADR` neuf, une
seule clause de garde-fou a ecrire. Complexite moyenne - au-dessus de `S39-ENERGIE` et
`S39-OUTILS`, tres en dessous de `S39-DEN`.**

`nadia-prof` a nomme la difference qui compte, et je la confirme sur le code : *"`item illustre`
existe deja comme mecanique, seul le materiau (les cinq dessins) manque, contrairement a `S39-DEN`
ou c'est la mecanique elle-meme qui manque"*. C'est exact. **La chaine est complete de bout en
bout et ne sait rien de la dimension de ce qu'elle montre.**

**`S39-SOLIDES` peut viser `couvert-valide` a s39**, au meme titre que `S39-MONNAIE` et
contrairement a `S39-DEN` et `S39-LONGUEUR`. Ce qui reste devant est du materiau et un Done
pedagogique, pas une architecture.

## 9. Ce que le mecanisme est reellement, verifie fichier par fichier

Pas une supposition : les cinq maillons, chacun avec son fichier.

| Maillon | Fichier | Ce qu'il fait |
|---|---|---|
| 1. Declaration | `adapters/adapter-content/src/main/resources/contenu/cp-figures-planes.yaml` | l'item porte `illustration: figure-carre-incline` et `illustrationTexteAlternatif` - un **identifiant**, jamais un chemin (`ADR-0073`) |
| 2. Chargement | chargeur unique pilote par schema (`ADR-0007`) | aucune classe par paquet |
| 3. Session | `illustrationManche()` / `illustrationTexteAlternatifManche()` | `Optional`, portes par la session de mecanique |
| 4. Controleur | `adapters/adapter-web/.../PartieController.java`, lignes 431-433 | pose les deux attributs au modele |
| 5. Gabarit | `adapters/adapter-web/src/main/resources/templates/fragments/manche.html`, lignes 84-86 | `<img class="image-item" th:src="@{'/illustrations/' + ${illustration} + '.svg'}" th:alt="...">` |
| 6. Fichier | `adapters/adapter-web/src/main/resources/static/illustrations/*.svg` | **27 SVG ecrits a la main**, trait seul, couleur unique |

**Le rendu est du SVG statique ecrit a la main, servi comme image.** Ni police, ni glyphe, ni
image matricielle, ni bibliotheque de dessin, ni une ligne de JavaScript.

## 10. Pourquoi rien de cette chaine n'est specifique a la 2D

Trois constats, tous verifiables dans l'arbre :

1. **Aucun maillon ne connait la geometrie de ce qu'il transporte.** Du YAML au `<img>`, ce qui
   circule est un identifiant `[a-z0-9-]+` et un texte alternatif. Une projection isometrique d'un
   cube est un trace plan comme un autre : le format SVG n'a pas de notion de troisieme dimension,
   et il n'en a pas besoin - **une projection est, par definition, un dessin a plat**.
2. **Le corpus servi contient deja des dessins qui ne sont pas des figures geometriques.** Les 27
   fichiers portent 12 figures planes, 12 scenes de monnaie (piece, billet, chiffre grave), 2
   frises d'evenements et 1 plan de classe. La chaine n'a jamais ete une chaine a figures planes ;
   elle a ete **inauguree** par des figures planes.
3. **Les garde-fous de geometrie jugent le fichier, pas le sujet.** `viewBox` present, taille
   declaree en unites utilisateur dans le rapport du cadre de vue, hauteur sous le plafond de
   rendu, trait le plus fin au-dessus de 2 px rendus, une seule couleur de trait, pas deux dessins
   identiques en niveaux de gris. Un cube en projection tient ces six clauses exactement comme un
   carre.

**`SOL-CE1-1` tombe donc pile dans ce que le mecanisme garantit deja.** *"Des proportions
reellement declarees par le contenu, jamais un glyphe"* est ce que le couple `viewBox` +
`width`/`height` encode, et ce que `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLe
CliquetDePoidsTest` execute depuis s25 au titre de `FIG-1`. Aucune regle neuve n'est due pour
`SOL-CE1-1`.

## 11. Ce que le lot coute exactement

### 11.1 Le materiau : 15 dessins et 15 items, et c'est `SOL-CE1-2` qui fixe le plancher

`SOL-CE1-2` demande au moins trois exemples par solide : 5 x 3 = **15 dessins**. La clause
*"aucune illustration ne dort"* (`ADR-0073`, executee par `LIllustrationDeContenuResoutEtAucuneNe
DortTest`) impose que chaque fichier soit declare par un item servi, et le precedent des figures
planes n'en reemploie aucun : **15 items au moins**.

C'est `SOL-CE1-2` qui plafonne, pas `ADR-0023` (qui n'exigerait que 11 items pour 10 manches). A
lire avant d'ecrire le paquet, sinon le rouge arrive apres l'ecriture.

### 11.2 Le precedent chiffre, mesure et non estime

Commit `28150cd4` (`[LEA]`, s25, lots B et C - les 12 figures planes) : **22 fichiers, 1 014
insertions**, dont **397 lignes de garde-fou de geometrie ecrit ce jour-la** et 314 lignes de
garde-fou de paquet. Les 12 dessins eux-memes pesent **5 a 15 lignes chacun**. Le meme sprint a
livre 12 dessins de plus (`115ac285`, monnaie), soit **24 dessins servis dans un seul sprint**.

**Ce qui ne se repaiera pas** : les 397 lignes du garde-fou de geometrie. Il existe, il porte les
six clauses de la section 10 et il s'applique au repertoire entier, donc aux 15 dessins neufs sans
une ligne de plus. Le cout de `S39-SOLIDES` est **la moitie basse** de ce precedent.

### 11.3 Trois compteurs exacts a deplacer dans le commit qui livre

Ce depot epingle ses populations a la valeur exacte, jamais a un plafond de confort. Un lot qui
les agrandit les releve **dans son propre commit, avec sa valeur mesuree et son motif ecrit** -
c'est le geste, deja fait sept fois, et il ne s'improvise pas au moment du rouge :

| Constante | Fichier | Aujourd'hui | Apres |
|---|---|---|---|
| `FICHIERS_D_ILLUSTRATION` | `bootstrap/.../LIllustrationDeContenuResoutEtAucuneNeDortTest.java` | 27 | 42 |
| `ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION` | meme fichier | 41 | 56 |
| `POIDS_TOTAL_MAXIMUM` | `adapters/adapter-web/.../ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest.java` | 42 336 octets | **a mesurer** |
| `POIDS_D_UN_DESSIN_MAXIMUM` | meme fichier | 4 633 octets | **probablement inchange** |

Les deux premieres valeurs se derivent du lot (27 + 15, 41 + 15) et je les ecris ici ; les deux
autres **se mesurent sur l'arbre du lot, jamais recopiees d'une annonce** - c'est exactement la
faute qu'`ADR-0077` a commise sur ce meme cliquet et que le commit suivant a rattrapee. Mon
attente sur la quatrieme, ecrite comme une attente et pas comme un chiffre : le plus gros dessin
du depot est `frise-journee-ecole.svg` (4 633 octets, 6 pictogrammes et 57 balises) ; un solide en
projection trace 3 a 8 formes, donc reste largement dessous. Si un dessin de solide depasse 4 633,
c'est le signe qu'il est trop charge pour une image de 176 px, et la bonne reponse est de le
simplifier, pas de relever le cliquet.

## 12. La seule clause neuve qui est due, et ce qu'elle interdit reellement

**Le trou, trouve sur le code et non suppose.** Une boule livree comme **cercle nu** et un cube
livre comme **carre nu** passeraient **toutes** les clauses existantes sans qu'aucune ne s'en
apercoive : geometrie declaree, trait, couleur, poids, resolution de l'identifiant, tout est vert.
`FIG-4` - *"le paquet ne confond ni figure plane et solide"* - n'existe aujourd'hui que dans
`PaquetCpFiguresPlanesTest`, qui **ne lit que le YAML** (il cherche les mots interdits dans les
enonces) et **ne regarde aucun dessin**. Sur un paquet de solides, la confusion ne serait pas dans
le mot, elle serait dans le trace.

**La clause, et elle est derivable du fichier :**

> Un dessin declare par un paquet qui sert `MAT-CE1-16` trace **au moins deux formes rendues**.

Motif : la derivation d'`ADR-0077` dit qu'*"un dessin qui ne trace qu'une seule forme rendue **est**
cette forme"*. C'est ce qui interdit deja de remplir un cercle. Retournee, c'est exactement ce qui
manque ici : **aucun solide usuel ne se represente en projection par une forme unique** - un cube
et un pave droit demandent un contour plus trois aretes, un cylindre deux ellipses et deux
generatrices, un cone une ellipse et deux generatrices, une boule un cercle **plus** son equateur,
sans quoi c'est un cercle.

**Ce qu'elle interdit reellement** : la livraison d'une figure plane sous un nom de solide - le
defaut exact que `nadia-prof` a sanctionne sous `FIG-4` et que `SOL-CE1-4` reconduit.
**Ce qu'elle ne prouve pas**, ecrit avant qu'on me le reproche : que la projection se lise comme un
solide, ni qu'un cube ne soit pas un pave droit mal dessine. Deux formes ne font pas un cube.
Cette part-la est l'oeil de `nadia-prof`, item par item, et aucun automate ne la fera a sa place.

**Ou elle vit, et pourquoi pas ailleurs.** Dans
`bootstrap/src/test/java/com/recreo/archi/LIllustrationDeContenuResoutEtAucuneNeDortTest.java`.
C'est le **seul** module qui voie a la fois les paquets de contenu et les fichiers de dessin, et
`ADR-0073` y a deja loge le jugement du lien paquet - dessin : rien de neuf a decider, donc pas
d'`ADR`. Les deux autres logements ont ete examines et ecartes sur un motif, pas par gout :

- **`adapter-content`** (ou vivrait un `PaquetCe1SolidesTest`) ne voit pas les SVG, qui sont des
  ressources d'`adapter-web` - c'est ecrit noir sur blanc dans `PaquetCpFiguresPlanesTest` : les
  declarations d'illustration *"sont lues dans le YAML"*, jamais les dessins ;
- **`adapter-web`** voit les dessins mais **ne depend pas d'`adapter-content`** (sens des
  dependances), d'ou la liste `DESSINS_DE_DESIGNATION` ecrite a la main dans son garde-fou de
  geometrie. Son propre commentaire route a `theo-archi` *"si cette liste devait grandir au point
  de justifier un mecanisme partage"*. **`S39-SOLIDES` fait echoir cette question et je la
  tranche ici** : une seconde liste manuelle de 15 noms serait la croissance annoncee, et la
  reponse n'est pas un mecanisme partage - c'est de poser la clause la ou le lien est **deja
  derive** de la donnee. La liste existante de 3 noms reste ou elle est, elle ne grandit pas.

**Le prix a payer, nomme et non cache** : le compteur de formes (`formesRendues`, environ 20
lignes) vit dans les sources de test d'`adapter-web`, qu'aucun test de `bootstrap` ne voit - il n'y
a pas de `test-jar` entre les deux modules, verifie dans le `pom`. La clause en duplique donc le
comptage dans `bootstrap`. **20 lignes dupliquees valent mieux qu'un `test-jar` entre modules pour
un seul appelant** ; ce qui me ferait changer d'avis est un troisieme appelant.

**La forme du code**, pour que la clause ne soit pas un souhait :

```java
/**
 * SOL-CE1-1 et SOL-CE1-4 (nadia-prof, 2026-09-22) : un solide n'est pas une figure plane. La
 * derivation d'ADR-0077 - un dessin qui ne trace qu'une seule forme rendue EST cette forme - dit
 * qu'une boule livree en cercle nu est un cercle. Aucune autre clause du depot ne le voit.
 */
@Test
void chaqueDessinQuiSertUnSolideTraceUneProjectionEtNonUneFigurePlane() {
    Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
    List<String> fautes = new ArrayList<>();
    Map<String, String> dessins = fichiersIndexesParNomDeBase(
            repertoires(racine, ILLUSTRATIONS_DE_CONTENU));
    for (IllustrationDeclaree declaree
            : illustrationsDeclareesParLesPaquetsQuiServent(LIGNE_DES_SOLIDES)) {
        int formes = formesRendues(dessins.get(declaree.identifiant()));
        if (formes < 2) {
            fautes.add(declaree.identifiant() + " (" + declaree.paquet() + ") : " + formes
                    + " forme rendue. Un dessin d'une seule forme EST cette forme (ADR-0077) :"
                    + " une boule livree en cercle nu est un cercle, et la ligne demande de"
                    + " reconnaitre un solide (SOL-CE1-1, SOL-CE1-4)");
        }
    }
    assertThat(fautes).isEmpty();
}
```

**Le perimetre est derive, jamais enumere** (`ADR-0013` obligation 1) : `LIGNE_DES_SOLIDES` est
`MAT-CE1-16`, **un code de ligne du referentiel** - pas un nom de paquet, pas un prefixe de nom de
fichier, les deux que `ADR-0077` interdit. La cle `lignesDeReferentiel` est deja portee par chaque
paquet servi et deja lue par `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest`
(`ADR-0081`). Nommer la ligne est ici **nommer la situation** que la clause juge : `SOL-CE1-1` est
une clause **de cette ligne**, et d'aucune autre.

**Le contre-exemple qui la mord** (`ADR-0013` obligation 3) : un dessin d'une seule forme sous
`bootstrap/src/test/resources/contreexemple/LIllustrationDeContenuResoutEtAucuneNeDortTest/`, ou
le repertoire porte deja le jeu fautif `paquet-illustre-fautif/` - un second jeu, ou un dessin de
plus dans celui-la. La clause n'a pas de valeur sans lui.

## 13. Garde-fous qui mordent sur ce lot

Aucun a ecrire hors celui de la section 12 ; tous a tenir. Ceux qui decident la forme du paquet et
des dessins, donc a lire **avant** d'ecrire :

| Regle | Ce qu'elle impose ici |
|---|---|
| `FIG-1` / geometrie (`ADR-0036`, `DES-1` a `DES-3`) | chaque dessin declare `viewBox` **et** sa taille en unites utilisateur, dans le meme rapport, hauteur **au plus 176 px** (`max-height: 11rem` de `.image-item`) |
| `DES-3` / plancher de trait | le trait le plus fin rend **au moins 2 px** a la hauteur declaree. Sur une projection, c'est la contrainte reelle : les aretes internes ne peuvent pas etre plus fines que le contour |
| `DES-4` | `vector-effect: non-scaling-stroke` interdit |
| `COU-2` / `FIG-3` | **une seule couleur de trait**, celle du texte de la page. Une arete cachee ne peut donc pas etre grisee - elle se distingue par le tirete (`stroke-dasharray`, que rien n'interdit) ou pas du tout |
| `COU-2`, seconde moitie | deux dessins qui deviennent le meme dessin une fois la couleur retiree sont refuses : les 3 exemples d'un meme solide doivent differer par leur **geometrie**, pas par une teinte |
| `ADR-0073` / `LIllustrationDeContenuResoutEtAucuneNeDort` | aucun dessin qui dort, aucun identifiant qui ne resout pas, **aucun `<text>` ni `<tspan>` dans un dessin** - un solide n'est jamais legende |
| `CON-5` (texte alternatif) | l'`alt` decrit ce qu'il y a a regarder sans jamais nommer le solide, donc **identique sur les 15 items**, comme sur les figures planes |
| `CON-1` a `CON-3` | l'enonce ne decrit ni la forme ni la position de ce que l'image montre : image effacee, la reponse n'est plus trouvable |
| `CON-6` | l'indice est choisi d'apres **le jeu de propositions**, jamais d'apres la bonne reponse |
| `ADR-0063` | au plus **trois** propositions par item |
| `ADR-0032` | deux items d'un meme paquet ne presentent jamais le meme ecran |
| `ADR-0023` | strictement plus d'items que de manches - non contraignant ici, voir 11.1 |
| `ADR-0081` | `lignesDeReferentiel: ["MAT-CE1-16"]`, code deja alloue au referentiel |

**Un point ou ce lot est plus confortable que son precedent, et c'est mesurable.** `CON-6` avait
coince les figures planes **exactement a la borne** (l'indice le plus repandu couvrant 6 items sur
12) parce que 4 formes n'offrent que 4 jeux de 3 propositions. Avec **5** solides, les jeux de 3
propositions possibles passent a 10 pour 15 items : la contrainte se tient avec de la marge au
lieu de se tenir a zero.

## 14. Risques

**R5 - le plafond de 176 px est le seul point qui pourrait obliger a autre chose que du materiau.**
Une projection porte 3 a 8 traits la ou un carre en porte 4 fermes, et `.image-item` plafonne a
11 rem. La mecanique de designation a obtenu 22 rem, mais par `ADR-0080` et **pour un motif qui ne
vaut pas ici** (le contenu y pose des zones tactiles sur le dessin). Si `ines-design` juge 176 px
insuffisant pour lire un cube, c'est un arbitrage de design sur la feuille, pas un chantier - mais
il se paie en aller-retour au milieu du sprint. **Parade** : le faire trancher par `ines-design`
**avant** de dessiner les quinze.
*Au passage, une fragilite que je nomme sans la corriger ici* : `PLAFOND_DE_RENDU_EN_PIXELS = 176`
est un **litteral** dans le garde-fou de geometrie, alors que le plafond de la designation, lui,
est **derive** de la feuille. Si `.image-item` montait dans la feuille sans que ce litteral suive,
le garde-fou deviendrait plus strict que la feuille - faux rouge, donc visible, et c'est pourquoi
je ne le traite pas dans cet avis.

**R6 - la boule n'a pas d'orientation, et `SOL-CE1-2` en demande une variation.** Le depot a deja
rencontre ce cas exact : le cercle, tranche par `nadia-prof` le 2026-08-31 (*"la taille est la
seule transformation qui laisse un cercle invariant"*), avec une borne explicite - **ce n'est pas
un precedent pour les autres formes**. La boule est le meme cas geometrique. **Ce n'est pas a moi
de trancher** : a router a `nadia-prof` **des le debut du lot**, pas au Done.

**R7 - cube et pave droit en projection isometrique.** `SOL-CE1-4` exige un rapport d'aretes
visiblement distinct. En isometrique, le cube se projette en hexagone regulier et le pave droit en
hexagone allonge : c'est geometriquement net et le `viewBox` le declare. Le risque n'est pas
technique, il est de main - un pave droit trop proche du cube est **une reponse fausse, pas un
defaut de forme**, et aucun automate ne le voit. C'est le meme constat que le garde-fou de
geometrie ecrit deja de lui-meme.

**R8 - les trois compteurs exacts de la section 11.3 oublies jusqu'au rouge.** Ils ne cassent
rien, ils font perdre une passe. Leur motif doit etre **mesure sur l'arbre du lot**, jamais
recopie du present avis.

**R9 - `-am` sur toute mesure partielle** (`CLAUDE.md`). `mvn -pl adapters/adapter-web test` sans
`-am` mesure le depot local, pas le worktree. La porte reste `mvn -B clean verify` sur la racine,
et la porte technique est **l'ensemble des jobs de `.github/workflows/ci.yml`**, pas `mvn verify`
seul.

## 15. Ce que cet avis ne dit pas

Il ne dessine rien et ne choisit ni la projection (isometrique ou cavaliere), ni les trois
variations de chaque solide, ni les enonces, ni les distracteurs : materiau, pas architecture. Il
n'affecte personne - `sofia-sm` le fait (`DEC-0092`, section 5). Il ne prononce aucun Done, ni
technique ni pedagogique, et il ne garantit pas `couvert-valide` : il dit que **rien dans
l'architecture ne l'empeche**, ce qui n'est pas la meme chose. `SOL-CE1-1` a `SOL-CE1-4` sont
tenues par `nadia-prof`, qui juge sur le jar servi.

---

# s39 - Avis de faisabilite (`theo-archi`), seconde partie - `S39-DEN`, `S39-LONGUEUR`, `S39-MONNAIE`

- **Date** : 2026-09-22. **Base** : `round-two` a `502dcca6`.
- **Entree** : `squad/conformite/ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md`
  (`nadia-prof`, 2026-09-22), sections 1 a 3.
- **Perimetre** : **trois** des quatre lots arbitres. `S39-SOLIDES` est instruit a part et n'est
  pas traite ici.
- **Version de mesure** : toute commande citee ci-dessous a ete lancee par
  `bash outils/mvn-jdk21.sh`, qui a rapporte **`Maven lance sur le JDK de la toolchain jdk 21 :
  21.0.10`**. C'est la version que la machine virtuelle des tests rapporte, pas celle qu'une entree
  de toolchain declare (`ADR-0071`).
- **Ce que cet avis ne vaut pas** : ni livraison, ni affectation (`DEC-0092`, section 5). Aucune
  ligne de code n'est ecrite avec lui. Il ne prononce aucun Done pedagogique et ne rouvre aucune
  borne de `nadia-prof`.

## 16. Verdicts

| Lot | Verdict |
|---|---|
| `S39-DEN` | **Faisable en l'etat pour la part servie** (`DEN-1` a `DEN-8`, ligne plafonnee `en cours`), sans `ADR` neuf. **Infaisable dans ce sprint pour `DEN-2`** : je confirme `nadia-prof` sur le code, et je chiffre le chantier - section 17 |
| `S39-LONGUEUR` | **Faisable moyennant un `ADR`**, pour la part textuelle seule. Deux obstacles verifies qu'aucune story ne nomme : l'allocation de sous-partie d'`ADR-0097`, et une **collision directe entre `MES-CE1-3` et un garde-fou en vigueur** - section 18 |
| `S39-MONNAIE` | **Faisable moyennant le meme `ADR`**, `couvert-valide` techniquement atteignable. Un obstacle propre, verifie : **les centimes sortent le paquet du domaine numerique** - section 19 |

**Obstacle commun aux trois, et il est deja rouge.** `mvn -B clean verify` sur la racine de ce
worktree **echoue** a `502dcca6`, avant toute story de s39 :
`ToutesLesReferencesDeDecisionResolventTest.leDepotReelEstCoherent` (`ADR-0066`, clause 1) nomme
deux fichiers commites qui citent `DEC-0092` - l'arbitrage prealable de `nadia-prof` et la premiere
partie de cet avis - alors qu'**aucun fichier de `squad/decisions/` ne porte `DEC-0092`**. Mesure :
`Tests run: 265, Failures: 1` sur `recreo-bootstrap`, les cinq autres modules verts. La cause n'est
pas une faute de redaction : `DEC-0092` existe, il n'est simplement pas commite. **Ce document
ajoute une troisieme citation et n'ameliore pas ce rouge** - je le nomme plutot que de l'ignorer.
Il se paie en commitant `DEC-0092`, ce qui n'est pas de ma main.

## 17. `S39-DEN` - je confirme `DEN-2` sur le code, et je chiffre ce qu'il coute

**Ce que j'ai lu moi-meme**, sans recopier : `Designation.java`, `DesignationSession.java` (268
lignes), `DesignationMove.java` (11 lignes), `ZoneADesigner.java`, `Zone.java`, `Item.java`,
`ReponseAEcrire.java`, `Question.java`.

**Le constat de `nadia-prof` est exact.** `DesignationMove` porte **un seul** identifiant de zone
par coup ; `DesignationSession.jouer` resout la manche des que ce choix est la bonne zone, et une
zone fausse n'est que **marquee** dans `zonesEssayees` - jamais deplacee, jamais regroupee, jamais
porteuse d'un etat propre. Aucune boucle de la session ne permet une sequence d'actions sur les
elements d'une meme collection.

**Deux choses qu'elle n'avait pas a verifier, et qui ferment la question au lieu de la laisser
ouverte.** Je les ai cherchees precisement parce qu'elle juge le pedagogique et pas la faisabilite :

1. **Le contrat de contenu n'a que trois formes d'item**, et c'est une couture **scellee** :
   `public sealed interface Item permits Question, ReponseAEcrire, ZoneADesigner`. Il n'existe donc
   aucune quatrieme forme dormante qu'une story pourrait reveiller.
2. **`ReponseAEcrire` ne porte aucune illustration** - verifie sur ses composantes, la ou
   `Question` en porte une facultative depuis `ADR-0073`. La voie a laquelle on penserait
   spontanement - *une collection dessinee, et l'enfant ecrit combien il en a compte* - n'est donc
   **pas constructible aujourd'hui**, meme en laissant de cote qu'elle ne satisferait pas `DEN-2`
   faute d'action sur la collection. Je le dis pour qu'elle ne soit pas proposee comme un raccourci
   au sprint suivant.

**Ce qu'un `DEN-2` satisfait couterait, chiffre sur du materiau reel plutot que sur mon
estimation.** Le depot a deja introduit une mecanique une fois, et c'est la mesure la plus proche :

- **six `ADR` sur neuf sprints d'ecart** pour la designation - `ADR-0080` (s28, le plus long du
  depot), `ADR-0088` (s32), `ADR-0091` et `ADR-0092` (s35), `ADR-0093` et `ADR-0094` (s36) ;
- **13 fichiers de test dont le nom porte `Designation` ou `Zone`** (methode : `find` sur les
  quatre racines de test du reacteur, correspondance sur le nom de fichier, comptage du
  2026-09-22 ; 12 tests et un contre-exemple). Ce chiffre ne compte pas les garde-fous existants
  qu'il a fallu etendre, il est donc un **plancher** ;
- et une quatrieme permission a la couture scellee, dont `ADR-0080` a mesure qu'elle arrete le
  compilateur a huit endroits exacts pour la troisieme.

**Et ce serait plus cher, pas moins.** La designation a reutilise la boucle de partie de
`BanditManchotSession` mot pour mot (`ADR-0080`, decision 3) : trois essais, revelation, une manche
resolue par revelation ne compte pas. Une mecanique de denombrement **ne peut pas** la reutiliser -
`DEN-2` demande une sequence d'actions sur une meme collection, donc un etat par element au sein
d'une manche, donc une boucle de partie neuve. Le poste de cout qui a ete **evite** a s28 est
precisement celui qu'il faudrait payer ici.

**Verdict.** La part textuelle (`DEN-1` a `DEN-8`) est un paquet statique ordinaire, du meme regime
que la section 2 de cet avis, sans `ADR` neuf. `DEN-2` est **un chantier de mecanique, pas une
story** : infaisable dans s39 sans revoir le decoupage. Je ne l'ouvre pas de moi-meme - c'est a
`sofia-sm` et `camille-po` de decider s'il entre un jour au backlog, et a `nadia-prof` seule de
dire si la ligne le vaut.

## 18. `S39-LONGUEUR` - deux obstacles verifies, dont un que la borne pedagogique heurte de front

### 18.1 La part textuelle a un precedent servi, et il dit aussi comment ne pas s'y prendre

`cm1-mesures` sert `MAT-CM1-08` par 24 items de type `question`, conversions `cm`/`m`/`mm`/`km`,
`g`/`kg`/`tonne`, `ml`/`cl`/`L`, **sans aucune image**. Le mecanisme demande par `MES-CE1-1` a
`MES-CE1-3` existe donc, servi, et ne coute aucune ligne de Java de production.

### 18.2 `MES-CE1-3`, tel qu'il est ecrit, est rouge sur un garde-fou en vigueur

C'est le point central de cet avis, et il se lit dans le code, pas dans une estimation.
`LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest`, clause
`lesTroisPropositionsDUnItemDuDomaineLaissentLeMemeResteApresLecture` (`ADR-0041`), exige que les
trois propositions d'un item **laissent le meme reste** apres lecture de leur valeur - le reste
etant l'unite ecrite. Son propre message d'erreur nomme le cas :

> *"Une unite ecrite ('cm' face a 'm') est le cas le plus courant de reste different, ce n'est pas
> le seul"*

Or `MES-CE1-3` demande un distracteur qui soit **une confusion d'unite** - *"repondre en cm la
valeur attendue en m, ou l'inverse"*. Un item dont la bonne reponse est `3 m` et un distracteur
`300 cm` laisse les restes `{m, cm}` : **rouge**, avec un message qui decrit exactement cet item.

**Ce n'est pas un garde-fou a assouplir.** Sa raison est ecrite et elle est juste : deux
propositions d'unites differentes ne mesurent pas la meme chose, l'ordre qu'on en tire est faux, et
*"une valeur existe, elle est seulement fausse"* - un faux vert. Le precedent le montre en creux :
`cm1-mesures` exprime la faute d'unite comme une **erreur de facteur dans une seule unite**
(`7 cm` -> `70 mm`, distracteurs `700 mm` et `7000 mm`), jamais comme deux unites dans un meme
item. La seconde moitie de `MES-CE1-3` - *"une erreur de conversion par un facteur autre que 100"* -
passe telle quelle ; c'est la premiere moitie qui ne se construit pas sous cette forme.

**Ce que je fais, et ce que je ne fais pas.** Je ne reformule pas `MES-CE1-3` : c'est une borne
pedagogique, elle est a `nadia-prof`. Je nomme l'obstacle avec sa preuve pour qu'il soit arbitre
avant developpement, et non decouvert comme un rouge mecanique au premier `verify`.

### 18.3 `ADR-0097` bloque tout paquet de mathematiques de CE1 neuf

`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` recense **tout** paquet du repertoire de
contenu declarant niveau `CE1` et matiere `Mathematiques`, et rend rouge celui dont la `competence`
declaree n'est pas allouee dans l'enumeration `SousPartieDeMathematiquesCe1`. Polarite `ADR-0039` :
un paquet que le tableau ne nomme pas est **rouge, pas vert**. Le tableau porte aujourd'hui quatre
entrees, **toutes des sous-parties de `MAT-CE1-03`** (additions, soustractions, complements a la
dizaine, moities). Mesure : `mvn -B -pl adapters/adapter-content -am test` sur ce seul test,
**`Tests run: 10, Failures: 0`** - la regle est verte et elle mord.

**Le defaut n'est pas le garde-fou, c'est l'ecart entre son perimetre et sa decision.** Sa
population est *tout paquet de mathematiques de CE1* ; son allocation s'intitule *sous-partie de
`MAT-CE1-03`*. Tant que `MAT-CE1-03` etait la seule ligne CE1 servie, les deux coincidaient. Un
paquet de longueur ou de monnaie fait apparaitre l'ecart : il doit etre alloue dans un tableau dont
le nom et la Javadoc affirment qu'il n'enumere que des sous-parties d'une autre ligne. C'est la
famille de defaut que mon propre mandat me designe - **un perimetre qui echoue par inclusion**.

**Consequence : un `ADR` est du**, et il tient en une decision - *l'allocation declare la
sous-partie de la ligne de referentiel qu'un paquet de mathematiques de CE1 sert, quelle que soit
cette ligne*. Sa regle executable est le meme fichier, dont l'enumeration devient indexee par ligne
plutot que supposee sur une seule. Cet `ADR` n'est **pas** propre a `S39-LONGUEUR` : il est du pour
`S39-MONNAIE` a l'identique, et pour toute ligne CE1 de mathematiques apres elles. **Un seul `ADR`
pour les deux lots** - c'est une question posee deux fois, pas deux questions, exactement comme
`ADR-0097` l'avait etabli pour les trois paquets de s38.

### 18.4 Le rendu de regle graduee - et une nature de manque que l'arbitrage n'a pas tranchee

Je confirme l'absence : **les 27 illustrations servies** (methode : contenu de
`adapters/adapter-web/src/main/resources/static/illustrations/`, comptage du 2026-09-22) sont des
figures planes, deux frises, douze images de monnaie et un plan de classe. Aucune regle graduee.

**Mais la nature du manque n'est pas celle de `S39-DEN`, et c'est ce qu'il faut ne pas confondre.**
`nadia-prof` a fait cette distinction pour `S39-SOLIDES` ; elle vaut ici aussi et personne ne l'a
posee. La mecanique de designation sert deja *"montre l'endroit X sur l'image Y"* : un dessin de
regle graduee dont les graduations sont des `Zone` declarees permet techniquement *"montre le trait
qui marque 7 cm"*. **Ce qui manque est le materiau - un dessin - pas la mecanique.** Il ne s'agit
donc pas d'un chantier de la taille de `DEN-2`, mais d'un lot de contenu illustre, du meme ordre
que `S39-SOLIDES`.

**Ce que je ne dis pas** : qu'une designation de graduation *serve* le verbe "mesurer avec une
regle graduee". C'est un jugement pedagogique et il appartient a `nadia-prof` seule - elle a
explicitement laisse la faisabilite technique de cote, je lui rends donc le constat technique sans
le convertir en verdict. Je note seulement que son classement de cette ligne *"de la meme famille
que l'horloge"* est exact sur le rendu et **inexact sur le cout** : l'horloge exigerait une lecture
d'aiguilles, une regle graduee tient dans une image a zones.

### 18.5 Verdict

Faisable pour la part textuelle, moyennant l'`ADR` de 18.3, et **a la condition que `MES-CE1-3`
soit arbitre avant developpement** (18.2). Ligne plafonnee `en cours` par `nadia-prof`, ce que je
n'ai ni a confirmer ni a contester. Complexite **basse une fois ces deux points leves**, le paquet
lui-meme etant un `cm1-mesures` restreint a `cm`/`m`.

## 19. `S39-MONNAIE` - les centimes sortent le paquet du domaine numerique

### 19.1 Le mecanisme

Paquet statique de type `question`, bandit manchot, aucune image obligatoire - `nadia-prof` ecarte
`MON-1` avec son motif. Aucune ligne de Java de production. Les douze images `monnaie-*.svg` du CP
restent utilisables mais ne sont pas requises ; elles ne sont pas non plus a reutiliser sans
arbitrage, `LIllustrationDeContenuResoutEtAucuneNeDortTest` gardant leur emploi.

### 19.2 `MON-CE1-1` introduit la premiere virgule decimale du corpus servi, et elle a un effet

Mesure : **aucune ecriture decimale a la virgule n'existe dans les 78 paquets servis** (methode :
recherche d'un chiffre suivi d'une virgule puis d'un chiffre dans
`adapters/adapter-content/src/main/resources/contenu/`, 2026-09-22, aucun fichier retourne). Un
paquet portant `3,45 euros` serait donc le premier, et j'ai verifie ce que cela declenche plutot
que de le supposer.

`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.lireValeur` lit le premier nombre d'une
proposition, puis **rend `null` si le reste porte encore un chiffre** (`ADR-0068`). La Javadoc de
cette ligne nomme le cas explicitement : *"une liste, une fraction, un intervalle ou **une ecriture
decimale a la virgule** sont attrapees par la meme ligne"*. Une proposition `3,45 euros` est donc
**hors du domaine numerique**, et le garde-fou de predictibilite du rang ne la juge pas.

**Deux consequences, et la premiere est un rouge.**

1. **Un paquet qui melange des items avec centimes et des items sans est rouge**, par
   `aucunPaquetNEstJugeSurUnePartieDeSesItemsSansQueLeDepotLeDeclare` (`ADR-0041`) : un paquet juge
   sur une partie seulement de ses items doit etre declare dans `PAQUETS_A_ITEMS_ECARTES` avec son
   **nombre exact** d'items ecartes - trois paquets y figurent aujourd'hui. Comme `MON-CE1-1`
   impose des centimes en multiples de 5 et que `MON-CE1-3` impose au moins un tiers d'items de
   rendu, le melange est le cas nominal : **la declaration sera due**, avec son compte mesure sur
   le paquet livre et non recopie. C'est exactement le risque `R1` de la section 6, ici certain
   plutot que possible.
2. **Les items a centimes ne sont gardes par aucun automate de rang.** Leur predictibilite repose
   entierement sur la relecture de `nadia-prof`, comme les 14 items de `cm1-grands-nombres` et les
   18 de `cm1-fractions`. Je l'ecris pour que le taux qui sera cite sur ce paquet porte sa methode,
   et non pour l'excuser.

### 19.3 Un second point, plus discret, sur `MON-CE1-3`

La clause de reste identique (18.2) mord ici aussi, sur les seuls items **sans** centimes : un item
dont la bonne reponse est `2 euros` et un distracteur `50 centimes` laisse les restes
`{euros, centimes}` - rouge. Les trois propositions d'un meme item se tiennent dans une seule
unite. C'est une contrainte de redaction du paquet, sans effet sur les bornes de `nadia-prof`.

### 19.4 Verdict

Faisable, `couvert-valide` techniquement atteignable, moyennant **l'`ADR` de 18.3** - le meme, pas
un second - et sous les trois contraintes de forme ci-dessus. Complexite **basse cote architecture,
moyenne cote redaction du paquet** : c'est le premier paquet du depot a ecrire des decimales, et
trois garde-fous en vigueur reagissent a ce seul fait.

## 20. Ce que cette seconde partie ne dit pas

Elle ne fixe aucune borne, aucun montant, aucun distracteur : `MES-CE1-1` a `3` et `MON-CE1-1` a
`4` sont a `nadia-prof`, y compris la reponse a l'obstacle de 18.2. Elle n'affecte personne et ne
priorise rien (`DEC-0092`, section 5). Elle ne prononce aucun Done, et elle ne dit pas si ces trois
lignes bougeront dans la matrice - un statut suit un Done pedagogique, jamais un avis de
faisabilite.


---

# s39 - Ajout date du 2026-09-22 (`theo-archi`) - l'ecriture des montants de `S39-MONNAIE`

- **Ce que cet ajout est.** La reponse a la question laissee ouverte en section 19.2 - *comment
  `S39-MONNAIE` existe sans violer `ADR-0068`*. Aucun mot des sections 1 a 20 n'est modifie ni
  retire. La ceremonie de s39 n'est pas close.
- **Base** : `round-two` a `77e441e2`, plus le worktree `s39-theo-adr` qui porte `ADR-0098`.
- **Version de mesure** : `bash outils/mvn-jdk21.sh`, qui a rapporte **`Maven lance sur le JDK de
  la toolchain jdk 21 : 21.0.10`** - la version que la machine virtuelle des tests rapporte
  (`ADR-0071`).

## 21. `S39-MONNAIE` - la contrainte de conception, et pourquoi elle n'est pas un `ADR`

### 21.1 Ce que j'ai mesure plutot que suppose

Trois lectures, sur la lecture reelle du depot et non sur une copie :

| Ecriture | Ce que `lireValeur` rend | Consequence |
|---|---|---|
| `3,50 euros` | `null` | hors du domaine numerique |
| `3 euros 50` | `null` | hors du domaine numerique |
| `350 centimes` | valeur `350`, reste `centimes` | dans le domaine |

Le critere est le chiffre laisse derriere la lecture, jamais la forme qui le porte (`ADR-0068`,
`ADR-0039`) : une virgule decimale, une liste, une fraction et un intervalle sont attrapees par la
meme ligne de code.

**Ces trois lectures etaient affirmees par une Javadoc et prouvees par rien.** Aucune assertion du
depot n'exercait la forme decimale, et le corpus servi n'en ecrit aucune - mesure du 2026-09-22,
aucun fichier de `adapters/adapter-content/src/main/resources/contenu/` ne porte un chiffre suivi
d'une virgule puis d'un chiffre. Une affirmation de Javadoc sur laquelle un lot entier s'appuie est
un souhait tant qu'une assertion ne la tient pas : elle est desormais tenue par
`uneEcritureDecimaleALaVirguleSortDuDomaineEtUnMontantEntierYReste`, dans
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest.java`.
Mesure : `Tests run: 10, Failures: 0` sur ce fichier, `-pl adapters/adapter-content -am`.

### 21.2 La contrainte, en trois clauses

Elles sont techniques et elles sont de ma main. Elles ne choisissent pas le libelle des montants.

**C1 - les trois propositions d'un item se tiennent dans une seule unite.** Deja ecrite en 19.3, elle
vient de la clause de reste identique d'`ADR-0041` et elle vaut quelle que soit l'ecriture retenue.

**C2 - tout item dont une proposition ecrit deux nombres sort du domaine numerique, et sa sortie se
declare.** Les deux mecanismes existent, leurs noms sont verifies dans le code, et le paquet entre
dans **l'un des deux, jamais les deux** :

| Cas | Mecanisme | Fichier | Ce qui est du |
|---|---|---|---|
| Une partie des items seulement | `PAQUETS_A_ITEMS_ECARTES` | `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest.java` | le **nombre exact** d'items ecartes, mesure sur le paquet livre |
| Tous les items | `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` | `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java` | un **motif ecrit**, dont chaque compte cite est mesure |

Ni l'un ni l'autre n'a de plafond, et ce n'est pas un oubli : un contenu textuel de plus n'est pas
une degradation, c'est le programme scolaire. Les deux refusent en revanche de pourrir dans les deux
sens - un paquet non declare est rouge, une declaration qui ne correspond plus est rouge, un compte
faux est rouge.

**C3 - `ADR-0068` n'est pas rouvert.** Personne n'apprend a `lireValeur` a lire une virgule. Le
motif n'est pas la prudence : une lecture qui rendrait 350 sur `3,50 euros` la comparerait a 3 sur
`3 euros`, soit deux echelles dans une meme comparaison - exactement le faux vert qu'`ADR-0068` a
ferme. Une lecture decimale correcte demanderait de connaitre l'unite de chaque proposition, donc de
faire entrer le sens de l'unite dans un garde-fou qui ne lit aujourd'hui que des chiffres.

### 21.3 Pourquoi ce n'est pas un `ADR`

Parce qu'aucune de ces trois clauses n'est une decision neuve. `ADR-0068` decide la lecture,
`ADR-0041` decide la declaration partielle et la clause de reste, `ADR-0051` decide la declaration
totale. Les trois sont en vigueur, gardees, et elles mordent sur `S39-MONNAIE` sans qu'une ligne
bouge. **Un `ADR` qui redirait ce que trois `ADR` decident deja serait decoratif**, et ecrire un
`ADR` apres coup pour documenter une pratique deja reglee est ce que mon mandat m'interdit.

**Ce qui me ferait changer d'avis, ecrit maintenant** : un second besoin d'ecriture decimale, hors de
la monnaie - les nombres decimaux de CM2 en sont le candidat evident. Deux paquets sur lesquels
aucun garde-fou de rang ne porte, pour la meme cause, cesseraient d'etre un cas et deviendraient une
famille ; une lecture decimale qui declare son echelle deviendrait alors un usage constate, et elle
se deciderait en `ADR`. Aujourd'hui c'est un paquet.

### 21.4 Ce que cet ajout ne dit pas

Il ne choisit pas l'ecriture des montants. `MON-CE1-1` impose des centimes en multiples de 5 et
`MON-CE1-3` impose des items de rendu : ecrire `3,50 euros`, `3 euros 50` ou `350 centimes` est un
arbitrage pedagogique de `nadia-prof`, et les trois sont **techniquement servables** - les deux
premieres au prix d'une declaration mesuree, la troisieme sans declaration mais sous une graphie
qu'elle seule peut juger acceptable pour un enfant de CE1. Il ne prononce aucun Done, n'affecte
personne et ne priorise rien (`DEC-0092`, section 5).
