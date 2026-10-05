# s51 - Avis de faisabilite (`theo-archi`) - neuf stories de `DEC-0110` et la correction `HOM-CE1-R1`

- **Date** : 2026-10-05
- **Base** : `round-two` a `f68458de`. Les fichiers non commites de l'arbre principal (dont
  `ADR-0104`, `DEC-0110` et la section "Sprint 51" du backlog) ne sont pas dans l'arbre mesure ; ils
  sont lus, pas executes.
- **Emplacement** : dossier de sprint, a la demande de `sofia-sm`. Les avis de s44 a s50 sont sous
  `squad/rapports/`.
- **Ce que cet avis ne vaut pas** : il ne fixe aucun contenu, aucune affectation, et ne rejuge
  aucune condition. Le Done pedagogique reste a `nadia-prof`.
- **Fait nouveau lu avant d'ecrire** : `squad/conformite/ARBITRAGE-PREALABLE-s51-borne-a-trois-instances.md`
  (non commite a l'heure de cet avis) rend l'issue *"refus"* de `DEC-0110` section 8 : **l'instance C
  n'est pas engagee a s51**, report a s52. Les verdicts V8 a V10 servent donc s52 ; `DEC-0110`
  section 8 prevoyait que cet avis serve s52 dans ce cas.

---

## 0. L'etat mesure

`bash outils/mvn-jdk21.sh -B clean verify -Dmaven.test.failure.ignore=true`, reacteur complet, dans
un worktree detache a `f68458de`, cree pour cette mesure et supprime ensuite. Version : **JDK
21.0.10**, telle que la machine virtuelle des tests la rapporte (20 lignes *"using Java 21.0.10"*,
aucune autre version). Seul le job `build` est couvert : `chemins`, `prefixes` et `outils` n'ont pas
ete lances.

| Etat | Tests | Echecs | Methode |
|---|---|---|---|
| `f68458de` tel quel | **2004** (120 / 17 / 401 / 1167 / 293 / 6) | **0**, `BUILD SUCCESS` | somme des six blocs `Tests run:` de synthese |
| meme arbre plus quatre paquets-sondes (section 1) | 2004 | **21** (0 / 0 / 3 / 10 / 8 / 0) | idem ; messages lus dans les rapports `TEST-*.xml` |

## 1. Methode : quatre paquets-sondes passes par les garde-fous reels

Pour repondre a *"aucune clause ne bloque un paquet de plus"* sans le deduire du code, j'ai ecrit
dans le worktree quatre paquets de la forme de chaque instance, chacun avec son entree de catalogue,
puis lance le reacteur complet. Les sondes sont de ma main ; ce qu'elles prouvent tient aux
garde-fous, qui sont ceux du depot.

| Sonde | Forme | Ce qu'elle porte |
|---|---|---|
| `ce1-sonde-graphie` | instance A : `question`, francais CE1, deux propositions | 12 items `m`/`g`/`c` dont 4 en `ç` (*un garçon* / *un garcon*), blancs insecables devant `?` |
| `cm1-sonde-sciences` | instance B : `question`, sciences CM1, trois propositions textuelles | 12 items |
| `ce1-sonde-symetrie` | instance C : `designation`, deux figures a lignes candidates qui se croisent, deux quadrillages 6 x 6 a 36 zones | 12 items, 4 dessins carres de 352 px |
| `ce1-sonde-multiplication` | instance C : `question`, items illustres de reseaux de points, items de somme repetee | 12 items, 2 dessins de 176 px |

Les 21 echecs, ranges par cause :

| Cause | Garde-fou | Sonde | Nature |
|---|---|---|---|
| **un distracteur sans cedille** | `AucunMotServiNeSEcarteDeSonAccentTest` : *"le mot "garcon" n'est pas une forme du francais, alors que [garçon] en est une du meme squelette"* (4 defauts, un par item en `ç`) | graphie | **bloquant** (section 3) |
| paquet hors du domaine numerique non declare | `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (4 methodes) | graphie, sciences, symetrie | ecriture attendue : `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` |
| inventaire du port entrant | `TexteAccentueExposeParLePortEntrantTest` (3 methodes) | les quatre | ecriture attendue : identifiant, titre, consigne, domaine, competence |
| sous-partie de mathematiques CE1 non allouee | `ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` (2) | symetrie, multiplication | ecriture attendue : une entree de `SousPartieDeMathematiquesCe1` (code de test) |
| plafonds de designation | `AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest` (3), `...SansLaDetteDeLisibiliteDuLibelleTest` (1) | symetrie | ecriture attendue : 2 vers 3 dans les deux cliquets |
| dessins de designation non listes ; poids total | `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest` (3) | symetrie, multiplication | ecriture attendue : `DESSINS_DE_DESIGNATION`, `POIDS_TOTAL_MAXIMUM` (51 307 vers 57 615 sur les sondes) |
| compte d'items illustres | `LIllustrationDeContenuResoutEtAucuneNeDortTest` (64 vers 80) | symetrie, multiplication | ecriture attendue |
| deux items au meme ecran | `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` | symetrie | defaut de ma sonde, et enseignement (section 5.1) |
| item de paquet mixte ecarte | `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` | multiplication | ecriture attendue : `PAQUETS_A_ITEMS_ECARTES`, ou paquet homogene |
| rang de la bonne reponse | `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (2) | multiplication | defaut de mes distracteurs ; `MUL-CE1-6` rend les trois rangs atteignables |

**Contre-epreuve du seul bloquant.** Meme sonde, les quatre items en `ç` remplaces par des mots sans
cedille, `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test
-Dtest=AucunMotServiNeSEcarteDeSonAccentTest -Dsurefire.failIfNoSpecifiedTests=false` (l'enveloppe
rapporte 21.0.10) : 3 tests, 0 echec. La cedille est la cause, et la seule.

**Ce qui n'a rien bloque, et c'est la reponse a la question posee pour A et B** : l'accentuation
hors cedille, le compte de mots de CE1 (`AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest`, qui lit
aussi les enonces de designation), les signes doubles (`AucunSigneDoubleNEstDetachableDeSonMotTest`,
blancs insecables poses), le registre (`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, aucun
identifiant neuf), l'illustration qui ne dort pas (aucune illustration en A et B), les cliquets de
poids (aucun dessin en A et B), les lignes de referentiel, le nombre d'items par manche.

## 2. Verdicts

| Code | Story | Verdict | ADR requis |
|---|---|---|---|
| V1 | `S51-FRA-GRAPHIE` (`FRA-CE1-22`) | **infaisable en l'etat**, faisable moyennant `ADR` : `GRL-CE1-1` et `GRL-CE1-2` imposent le distracteur sans cedille, que le garde-fou d'accentuation refuse sans exemption possible (section 3) | amendement d'`ADR-0065` (A1) |
| V2 | `S51-FRA-TEMPS` (`FRA-CE1-20`) | **faisable en l'etat** | non |
| V3 | `S51-FRA-ALPHABETIQUE` (`FRA-CE1-23`) | **faisable en l'etat** | non |
| V4 | `HOM-CE1-R1` | **faisable en l'etat** : YAML seul ; aucun test ne nomme un prenom | non |
| V5 | `S51-SCI-CORPS-CM1` (`SCI-CM1-10`) | **faisable en l'etat** | non |
| V6 | `S51-SCI-TERRE-CM1` (`SCI-CM1-15`) | **faisable en l'etat** | non |
| V7 | `S51-SCI-ACTIVITE-HUMAINE-CM1` (`SCI-CM1-18`) | **faisable en l'etat** | non |
| V8 | `S51-MAT-SYMETRIE` (`MAT-CE1-17`), s52 | **faisable sous condition**, sans code de production : materiau (au moins dix dessins) et `ZON-13` encode avant le premier commit du paquet | amendement d'`ADR-0080` (A2) |
| V9 | `S51-MAT-MULTIPLICATION` (`MAT-CE1-06`), s52 | **faisable sous condition** du materiau (reseaux de points), sans code de production | non |
| V10 | `S51-MAT-COMPARER-RANGER` (`MAT-CE1-02`, moitie textuelle), s52 | **faisable en l'etat** | non |

**Aucune story ne touche `src/main/java`, aucune n'appelle de regle ArchUnit.** Les deux
amendements A1 et A2 portent sur des garde-fous de contenu (JUnit), comme les `ADR` qu'ils amendent ;
leur section "Regle executable" nommera ces tests, pas une `ArchRule`.

## 3. Instance A - francais CE1

### V1, le bloquant, mesure

`LexiqueFrancais.squelette` retire toute marque `NON_SPACING_MARK` apres decomposition NFD, la cedille
comprise. *garcon* n'est pas au lexique, *garçon* l'est sous le meme squelette : defaut. Le garde-fou
lit les distracteurs (*"champ distracteur 1"*), n'a ni table d'exemption ni cliquet. `GRL-CE1-1` met
`c`/`ç` dans au moins un quart des items ; `GRL-CE1-2` exige *un garçon* / *un garcon*. Le paquet que
la story decrit est donc rouge par construction. Les regles `m` (*janbe*) et `g` (*gitare*) ne
declenchent rien : aucune forme accentuee ne partage leur squelette.

**Ce que je propose d'ecrire (A1, amendement d'`ADR-0065`)**, la forme la plus etroite que j'ai
trouvee : *un mot sans sa marque est admis dans un distracteur si et seulement si la bonne reponse du
meme item porte la forme marquee a la meme position, et que les deux propositions ne different que
par cette marque.* C'est la forme exacte de `GRL-CE1-2`, derivee de l'item et non d'une liste de
mots. Ce qu'elle laisse rouge : le meme mot dans un enonce, un indice, une bonne reponse, ou un
distracteur dont la bonne reponse ne porte pas la soeur marquee. Mise a l'epreuve : la sonde de cette
mesure, plus un item ou la bonne reponse ne porte pas la cedille.

**L'autre voie** est une autre forme d'item (*gar_on*, propositions `c` / `ç`), qui contredit
`GRL-CE1-2` : elle est a `nadia-prof`, pas a moi (Q1).

### V2 a V4

- **Commun aux trois lignes neuves** : une entree de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` par paquet,
  avec son motif ; les cinq chaines de l'entree de catalogue dans l'inventaire de
  `TexteAccentueExposeParLePortEntrantTest` ; un blanc insecable devant `?`, `!`, `:`, `;` dans les
  titre et consigne de catalogue aussi (`AucunSigneDoubleNEstDetachableDeSonMotTest` compte le
  catalogue, et un paquet neuf ou une entree neuve n'herite d'aucune dette). Les pieges de l'avis de
  s50, section 2, valent tels quels (drapeau `UNICODE_CHARACTER_CLASS`, compte des mots).
- **V2** : *présent* est strictement la plus longue des trois propositions fixes. `TRV-CE1-2` le borne
  a la moitie des items, `TPS-CE1-1` a un quart au moins : fenetre compatible, a tenir a la redaction
  et a asserter dans le test de paquet. Les trois mots portent leur accent ; ecrits sans, le garde-fou
  d'accentuation les refuse, et c'est voulu.
- **V3** : *"la decision tombe a la premiere ou a la deuxieme lettre, jamais a la troisieme"* est
  calculable et s'asserte ; comparer les deux premieres lettres en caracteres bruts suffit, puisque
  `ALP-CE1-2` exclut accent, apostrophe, trait d'union et majuscule a ces positions.
- **V4** : YAML seul. `PaquetCe1HomophonesTest` ne nomme aucun prenom (lu). Un prenom accentue
  (*Inès*) s'ecrit avec son accent ; aucun garde-fou ne juge un prenom absent du lexique.

## 4. Instance B - sciences et technologie CM1 (V5 a V7)

Meme ecriture commune qu'en A, mesuree sur la sonde : `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` et
l'inventaire, rien d'autre.

- **Paquet mixte** : un item a trois propositions numeriques (une distance, une duree, `SCI-3`) entre
  dans le calcul de rang et rend le paquet partiellement juge. Il faut alors le declarer dans
  `PAQUETS_A_ITEMS_ECARTES` avec le compte exact, ou garder le paquet homogene.
- **`SCI-2`** (V7) est encodable dans le test de paquet : aucun jeton *tu*, *ta*, *ton*, *tes*, *toi*,
  *ta famille* en mot entier sous `UNICODE_CHARACTER_CLASS`, sur enonce, propositions, indice et
  explication. La liste exacte est a `nadia-prof` ; sans elle, `SCI-2` reste tenue par elle seule.

## 5. Instance C - mathematiques CE1 (V8 a V10, s52)

### 5.1 (d) Servables par les mecaniques existantes, sans code de production : oui, mesure

La sonde de symetrie est un paquet `designation` lu par `DesignationContentSourceFactory`, rendu par
l'ecran de manche reel (`LEcranDeMancheDuJeuDeDesignationReelEstServiTest` vert sur elle), avec des
items a 3 zones et des items a **36 zones** : `ZoneADesigner` ne borne pas le nombre de zones, et
aucun garde-fou n'a rougi sur ce nombre. La sonde de multiplication est un paquet `question` a
`illustration`, la forme de `ce1-solides`. `MAT-CE1-02` moitie textuelle est un paquet `question`.
Les seules ecritures hors YAML et SVG sont celles du tableau de la section 1, toutes en code de test.

**Un enseignement de la sonde qui fixe le nombre de dessins.** L'ecran d'un item de designation, pour
`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`, est *enonce + illustration + zones*, sans la
bonne zone. Deux items de completion sur le meme dessin sont le meme ecran ; et un item de completion
ne peut de toute facon pas changer de case attendue sans changer de dessin. **Un dessin par item**,
donc au moins dix pour `FER-2`, et `nombreDeManches` au plus egal au nombre d'items moins un
(`DEC-0064`) - decision de `camille-po` (Q4).

### 5.2 (a) Le materiau : cout et responsable

| Famille | Nombre | Rendu | Poids mesure sur sonde (une ligne de commentaire) | Estimation avec en-tete commente |
|---|---|---|---|---|
| figure a lignes candidates (`MAT-CE1-17`) | 5 a 6 | 352 px, carre | 505 octets | environ 0,9 Ko piece |
| quadrillage 6 x 6 avec axe et figure (`MAT-CE1-17`) | 5 a 6 | 352 px, carre | 1 638 a 1 720 octets | environ 2,1 Ko piece |
| reseau de points (`MAT-CE1-06`) | 6 a 8 | 176 px | 799 (3 x 5), 1 141 (4 x 6) | 1,2 a 2 Ko piece |

Methode de l'estimation : poids des sondes, plus l'ecart moyen d'en-tete des quinze solides de s39
(8 971 octets pour 15 dessins commentes). **Ordre de grandeur, pas une mesure** : 25 a 30 Ko, soit
`POIDS_TOTAL_MAXIMUM` de 51 307 vers environ 80 000, deplace dans le commit qui installe les
dessins, valeur mesuree et motif ecrits (`ADR-0036` clause 5).

Deux pieges de dessin, verifies dans le garde-fou :

- **Un reseau de 10 x 10** porte cent cercles, environ 3,8 Ko de trace seul : avec un en-tete
  commente, il depasse `POIDS_D_UN_DESSIN_MAXIMUM` (4 633). Soit le reseau reste plus petit, soit le
  cliquet par dessin bouge avec son motif.
- **Un reseau dessine par un motif SVG (`<pattern>`) remplissant un seul rectangle** est un dessin
  d'une seule forme remplie : `FIG-4` le refuse. Chaque point se trace.

Contraintes que le garde-fou impose deja et que le dessin doit respecter : `viewBox`, taille declaree
au plus au plafond (352 pour la designation, et le dessin inscrit dans `DESSINS_DE_DESIGNATION`, liste
ecrite a la main parce qu'`adapter-web` ne voit pas le contenu), une seule couleur de trait
(`#1c2733`), trait rendu d'au moins 2 px, aucun `<text>` ni `<tspan>`.

**Qui.** `ines-design` ecrit les contraintes et les mesure au rendu, comme a s39
(`contrainte-ines-solides-ce1.md`) ; elle ne dessine pas les dessins livres. Convention du depot
depuis s25 : les dessins sont commites par le developpeur qu'affecte `sofia-sm` (`lea-dev` pour les
figures planes et les solides, `sofia-sm` pour la monnaie). Les points a mesurer par elle :
lisibilite d'un reseau de dix colonnes a 176 px (pas d'environ 15,6 px entre centres sur un cadre de
vue de 100) ; trait de quadrillage contre figure remplie a 352 px ; une legende de 36 libelles sous
le dessin (`emplacementDuLibelleDeZone: hors-de-la-boite`, seule forme ou un libelle de case tient,
`ADR-0094`) et 36 arrets de tabulation.

**Convention qui rend les cadres exacts** : cadre de vue `0 0 100 100`, taille declaree 352 x 352,
comme `plan-classe.svg`. Un pourcentage de `CadreDeZone` est alors une unite du dessin. Un 6 x 6 tient
en cases de 16 unites a partir de 2 ; 100/6 n'etant pas entier, une grille pleine largeur ne
s'aligne pas sur des cadres entiers.

### 5.3 (b) `ZON-13` sur des lignes candidates qui se croisent

`nadia-prof` a tranche le pedagogique (arbitrage de s51, section 6.3) : pas de relachement, une zone
couvre une portion ou sa ligne est seule, le croisement n'appartient a aucune zone, et une ligne
prolongee hors de la figure est recevable. La forme technique qui en decoule, sans rien changer au
code :

- **chaque zone se pose sur la partie de sa ligne prolongee au-dela de la figure**, loin du croisement ;
- **les extremites de deux lignes voisines sont separees d'au moins 12,5 unites** (44 px sur 352) le
  long du bord ou les zones se posent, sans quoi deux zones de 44 px se chevauchent ;
- **toutes les zones d'un item ont la meme taille** : `LaBonneZoneNEstPasPredictibleSurUnPaquetTest`
  refuse une bonne zone strictement la plus grande sur la majorite des items, et une zone allongee
  sur une diagonale serait la plus grande ;
- **identifiants varies** : un identifiant `axe` sur chaque item de reconnaissance rougit le meme
  garde-fou des que ces items sont la majorite du paquet.

**Ce qui n'est encode nulle part aujourd'hui, mesure.** `ZON-13` (non-chevauchement, *"derivable de
quatre nombres par zone"*) et le rang de declaration de la bonne zone (*"ni toujours la premiere ni
toujours la derniere"*) m'ont ete routes le 2026-09-05 (`ARBITRAGE-PREALABLE-s29-le-palier-2...`,
section 7) et n'ont jamais ete ecrits : `git grep` sur `*.java` ne trouve aucun test de
chevauchement de cadres, et `LaBonneZoneNEstPasPredictibleSurUnPaquetTest` ne lit pas le rang. La
story `S51-MAT-SYMETRIE` les cite pourtant en criteres d'acceptation. Mesure sur les deux paquets
servis, script de poste sur les YAML : 0 paire de cadres chevauchante (`cp-frise-de-la-journee`, 11
items ; `cp-plan-de-la-classe`, 6 items), bonne zone premiere 3/11 et 1/6, derniere 1/11 et 1/6.
**Les deux clauses seraient vertes aujourd'hui et mordraient le premier defaut** : c'est A2.

**Ce qui reste a `nadia-prof` seule** : qu'aucune zone ne contienne un segment d'une autre ligne. Cela
demande la geometrie des lignes, qui vit dans le SVG et pas dans le YAML ; je ne l'encode pas.

### 5.4 (c) Le nombre de zones sur une ligne graduee

Derivation : des zones posees sur les graduations (arbitrage de s51, section 6.4), chacune d'au moins
44 px de large, deux voisines sans chevauchement, donc un pas de graduation d'au moins 44 px a l'ecran.
**Plafond = partie entiere de L / 44**, ou L est la largeur servie de la ligne. A la largeur cible de
744 px, la rangee fait 680 px (commentaire de `recreo.css`, `ADR-0054`) : 15 zones au plus, sans marge
entre elles ; 12 a 540 px, plafond de la feuille tierce sous 720 px. Le plancher est 3 (`ZON-8`), et la
regle des deux graduations voisines (section 6.4, point 3) en demande deja trois autour de la bonne.
L se mesure au rendu : a `ines-design`.

**Le garde-fou 3 d'`ADR-0080` ne sait pas juger une ligne graduee, mesure.** Il calcule
`min(largeur %, hauteur %) x 22rem`, c'est-a-dire qu'il suppose une image carree rendue a 352 px.
Sur les dessins servis, la frise (640 x 320) a pour plus petit cote de zone rendu 96 px quand le
garde-fou calcule 52,8 px ; le plan (352 x 352) donne 52,8 px des deux cotes (script de poste, tailles
lues dans les SVG). Sur une ligne de 680 x 136 :

- une zone de 8 % de large (54 px reels) est calculee a 28 px : **faux rouge**. Tel qu'il est, le
  garde-fou plafonne une ligne a 8 zones ;
- une zone de 15 % de haut (20 px reels) est calculee a 53 px : **faux vert**.

Et l'ecran de manche reel n'est juge que sur la premiere manche tiree de chaque jeu. **Avant le
paquet de designation de `MAT-CE1-02` (s52 au plus tot), le garde-fou doit calculer la taille rendue
a partir de la taille declaree du dessin et de la largeur servie** : A3. Sans effet sur V8 si ses
dessins sont carres a 352 px, ou le calcul est exact.

Autre cout du materiau de la ligne : `ORD-CE1-6` veut les nombres de reference ecrits sur la ligne,
et aucun dessin ne porte de `<text>`. Ils se tracent en chemins, comme les chiffres graves de la
monnaie, sous le plancher d'encre de 14 px d'`ines-design`.

### 5.5 V9 et V10, ce qui vaut pour l'ecriture

- **La lecture de valeur lit une somme repetee.** `lireValeur("5 + 5 + 5")` rend 15. Un item
  *produit vers somme* dont un distracteur est l'autre lecture de la meme disposition (*3 + 3 + 3 + 3
  + 3*, 15 aussi) rougit `aucuneValeurDeProductionNeCoincideAvecUneAutreDansLeMemeItem` - et porterait
  deux bonnes reponses. Un item *"3 fois 5"* sort du domaine (un chiffre reste apres la lecture) : le
  paquet devient mixte et se declare dans `PAQUETS_A_ITEMS_ECARTES`.
- **Rangs** : sous les formes de `MUL-CE1-6`, la bonne reponse est la plus grande avec `a + b` et
  `a x (b - 1)`, la plus petite avec `a x (b + 1)` et `(a + 1) x b`, intermediaire sinon. Les trois
  rangs sont atteignables, a la difference de `SOU-CE1-R1` a s50 ; la repartition se tient a la
  redaction.
- **V10** : les propositions de rangement (*"32, 33, 40"*), de comparaison (`<`, `>`, `=`) et
  d'encadrement (*"340 < 347 < 350"*) sortent toutes du domaine numerique (`ADR-0068`), comme
  `cp-ranger-nombres` deja declare : `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` si aucun item n'est
  numerique, `PAQUETS_A_ITEMS_ECARTES` sinon. `ORD-CE1-1` a `-4` sont toutes decidables sur l'item ;
  `RAN-3` a 16 caracteres se compte.

## 6. Ecritures d'architecture requises

| Code | Quoi | Pour | Quand | Regle executable |
|---|---|---|---|---|
| A1 | amendement d'`ADR-0065` : le mot sans marque admis dans un distracteur dont la bonne reponse porte la forme marquee, les deux propositions ne differant que par cette marque | V1 | avant ou dans le commit du paquet de `FRA-CE1-22`, s51 | `AucunMotServiNeSEcarteDeSonAccentTest`, clause neuve et sa mise a l'epreuve |
| A2 | amendement d'`ADR-0080` : `ZON-13` en garde-fou (aucune paire de cadres d'un item ne se chevauche) ; rang de declaration de la bonne zone en troisieme clause du garde-fou 4 | V8 | avant le premier paquet de designation de CE1, s52 | test de contenu sur la production, deux mutations du materiau reel |
| A3 | amendement d'`ADR-0080`, garde-fou 3 : taille rendue derivee de la taille declaree du dessin et de la largeur servie ; jugee sur chaque item, pas sur la premiere manche | moitie designation de `MAT-CE1-02`, `MAT-CE1-14` | avant leur premier paquet | les deux garde-fous 3 (`adapter-web` et `bootstrap`) |

Le message de `AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest` demande
encore d'*"etendre le balayage de production"* (T6) : les trois balayages lisent la production
depuis s34 (lu). Seul le plafond bouge ; le message est a corriger dans le meme diff que A2.

## 7. Questions et routages, via `sofia-sm`

| Code | Point | A qui | Bloquant ? |
|---|---|---|---|
| Q1 | V1 : A1 tient-il `GRL-CE1-2` tel qu'elle l'a ecrite, ou prefere-t-elle une autre forme d'item pour `c`/`ç` | `nadia-prof` | **oui, pour V1 seule**, jusqu'a A1 ou a sa reponse |
| Q2 | V7 : la liste des jetons d'adresse a l'enfant pour un test de `SCI-2` | `nadia-prof` | non |
| Q3 | Contraintes de dessin des trois familles de 5.2, legende a 36 libelles, mesure de L pour la ligne graduee | `ines-design` | oui pour V8 et V9, s52 |
| Q4 | `nombreDeManches` de la symetrie (un dessin par item) ; libelle de sous-partie de `MAT-CE1-02`, le referentiel nommant un quatrieme verbe que le paquet textuel ne sert pas | `camille-po` | non |
| Q5 | Affecter le dessinateur des dessins de C (convention s25 et s39 : un developpeur) | `sofia-sm` | oui pour V8 et V9, s52 |
| Q6 | A1, A2, A3 a mon compte ; A2 est une dette de trente jours (routee le 2026-09-05) | `theo-archi` | A1 pour s51 |

## 8. Mesure

- **Reacteur complet**, deux fois, section 0 ; worktree detache a `f68458de`, cree et supprime pour
  cette mesure ; journaux hors depot.
- **Sondes** : quatre YAML, six SVG, quatre entrees de catalogue, ecrits par un script de poste non
  commite ; aucun fichier du depot n'a ete modifie hors du worktree.
- **Chevauchement et rang de declaration** : script de poste sur les YAML de `contenu/`, test
  d'intersection stricte des rectangles `CadreDeZone`.
- **Taille rendue d'une zone** : largeur et hauteur declarees lues dans la balise racine de chaque
  SVG, hauteur bornee a 352 px et largeur a 720 px, comparees a la formule du garde-fou.
- **Garde-fous cites sans execution** : leurs verdicts sur des paquets reels de s51 et s52 sont
  deduits de leur code et de la sonde, pas d'un paquet livre.
