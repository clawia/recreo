# s50 - Avis de faisabilite (`theo-archi`) - cinq stories de `DEC-0108`

- **Date** : 2026-10-03
- **Base** : `round-two` a `03d152b0`, arbre propre. Aucun diff de code ni de contenu dans cet avis.
- **Pourquoi ce fichier est ici** : `DEC-0108` (section 8) route a `theo-archi` l'avis sur
  `FRA-CE1-02`, `FRA-CE1-16` et les trois corrections. Meme emplacement et meme nommage que les avis
  de s44 a s49.
- **Ce que cet avis ne vaut pas** : il ne fixe aucun contenu, aucune affectation, et ne rejuge
  aucune condition. Le Done pedagogique reste a `nadia-prof`.

---

## 0. L'etat sur lequel cet avis est fonde : deux echecs, ceux que `DEC-0108` assume, et rien d'autre

`bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, a `03d152b0`. Version : **JDK
21.0.10**, telle que la machine virtuelle des tests la rapporte (20 lignes *"using Java 21.0.10"*,
aucune autre version).

**Le lancement dans l'arbre principal n'a rien mesure de `bootstrap`** : `clean` y echoue, un jar
servi par un autre processus (port 8089) tenant `bootstrap/target/recreo.jar`. Je n'ai pas arrete un
processus que je n'ai pas lance. Mesure refaite dans un worktree detache a la meme tete, hors de
l'arbre, supprime ensuite. Premier lancement : `BUILD FAILURE` sur les tests de `bootstrap`, donc
sans les tests d'integration. Second lancement avec `-Dmaven.test.failure.ignore=true`, pour les
atteindre : memes chiffres, plus les 6 tests d'integration verts.

| Ce qui est compte | Resultat | Methode |
|---|---|---|
| Tests du reacteur | **1975, 2 echecs, 0 erreur, 0 ignore** (120 / 17 / 401 / 1138 / 293 / 6) | somme des six blocs `Tests run:` de synthese du second lancement |
| Les deux echecs | `LIllustrationDeContenuResoutEtAucuneNeDortTest`, methodes `chaqueIllustrationDeclareeParUnPaquetServiResoutEtAucunFichierNeDort` et `unIdentifiantDOeuvreResoutDesQueLeFichierDeReproductionExiste` | les deux messages, lus en entier, ne nomment que les 25 fichiers `oeuvre-*.jpg` (*"aucun item d'aucun paquet servi ne le declare"*) et aucun autre objet |
| Tests de `com.recreo.archi` | **214, 2 echecs** (les deux ci-dessus) | somme des attributs `tests` et `failures` des 30 rapports `TEST-com.recreo.archi.*.xml` |
| Regles ArchUnit | **36 `static final ArchRule`, 35 `@ArchTest`**, inchange depuis s49 | `git grep -c -F` sur les fichiers suivis de `domain`, `application`, `adapters`, `bootstrap`, pathspec `':!**/contreexemple/**'`, somme par fichier, lance par `rtk proxy` |

**Les deux echecs sont exactement la dette que `DEC-0108` (sections 1 et 7) assume jusqu'a T6.**
Rien de plus. Ecart avec l'avis de s49 (1954) : +21, tous livres a s49 apres `cafd9fe5` -
`adapter-content` +15 (13 de `PaquetCe1GraphemesComplexesTest`, une assertion chacun pour
`PaquetCpAnglaisCultureTest` et `PaquetCpEmcAlerteTest`), `adapter-web` +1 (`RessourcesTiercesTest`),
`bootstrap` +5 (T2 et T4 d'`ADR-0082`). Seul le job `build` est couvert : `chemins`, `prefixes` et
`outils` n'ont pas ete lances pour fonder cet avis.

## 1. Verdicts

| Code | Story | Verdict |
|---|---|---|
| V1 | `FRA-CE1-02` | **faisable en l'etat**, sans `ADR` ni `ArchRule`. La lecture d'une lettre est calculable : le test peut verifier l'exactitude de chaque item, ce que celui de `FRA-CE1-01` ne pouvait pas (section 3) |
| V2 | `FRA-CE1-16` | **faisable en l'etat**, sans `ADR` ni `ArchRule`. Le patron d'indice de `cm1-homophones` est a ne pas recopier (section 4) |
| V3 | `GRC-CE1-R1` | **faisable en l'etat.** YAML et test de paquet (section 5) |
| V4 | `SOU-CE1-R1` | **infaisable en l'etat.** La correction prescrite rougit `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` sur ses deux clauses, mesure sur le paquet corrige. Aucune exemption n'est ouvrable. A `nadia-prof` (section 6) |
| V5 | `DIV-CE1-R1` | **faisable en l'etat.** YAML seul, rangs inchanges, mesure sur le paquet corrige (section 7) |

**Aucune des cinq stories ne touche `src/main/java`.** Les corrections touchent un YAML de
`adapters/adapter-content/src/main/resources/contenu/` chacune, plus leur test de paquet quand il
existe. Les deux lignes neuves ajoutent, sur le modele de `224803e0` (`FRA-CE1-01`), six fichiers et
aucun de production Java : le YAML, une entree de `catalogue.yaml`, le test de paquet et son jeu
fautif sous `src/test`, une entree de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` dans
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, et l'inventaire de
`TexteAccentueExposeParLePortEntrantTest` (identifiant, titre, consigne, competence). `git grep` sur
`*/src/main/java/*` : aucun code ne nomme le francais, les homophones, les soustractions ni la
division ; les seules occurrences sont des Javadoc. Aucune mecanique, aucune cle de schema, aucune
persistance (`DEC-0028`).

## 2. Ce qui vaut pour les deux lignes neuves

- **Deux propositions, pas trois** (`TRV-CE1-2`, choix binaire nomme pour `FRA-CE1-02` et
  `FRA-CE1-16`). Recopier `PaquetCe1GraphemesComplexesTest` recopie `hasSize(2)` sur les
  distracteurs et *"trois propositions distinctes"* : rouge sur chaque item. Un seul distracteur.
- **`PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`** : un paquet sans proposition numerique non declare rougit
  `aucunPaquetNEstEcarteEnSilenceDuCalculDeRang`. Une entree par paquet, avec son motif mesure.
  L'avis de s49 ne le disait pas ; `224803e0` l'a fait.
- **Les mots entiers sous `Pattern.UNICODE_CHARACTER_CLASS`**, pour tout lexique et pour l'indice
  qui ne doit pas contenir la bonne reponse. Mesure sur JDK 21.0.10 : `\bà\b` sans ce drapeau ne
  trouve pas `à` dans *"Je vais à l'ecole"* ; avec, il le trouve. Sans lui, la verification de
  l'indice est verte a vide sur tout item dont la reponse est `à`.
- **La casse accentuee** : `CASE_INSENSITIVE` seul ne rapproche pas `à` de `À` (mesure, meme JDK) ;
  `UNICODE_CHARACTER_CLASS` l'implique. `À`, `Et`, `Est` en debut de phrase sont les formes ou la
  fuite se cache : c'est ainsi que deux indices de `cm1-homophones` ont echappe au controle a s17
  (Javadoc de `PaquetsFrancaisS04Test`).
- **Le compte des mots** de `TRV-CE1-5` et `LON-CE1-5` : la convention de `nadia-prof` est celle de
  `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.mots` (separateurs : blanc et U+00A0, un jeton ne
  compte que s'il porte une lettre ou un chiffre). Le `split("\\s+")` du test de `FRA-CE1-01`
  compte `:` comme un mot : il pese vers le rouge, sans faux vert. A reprendre sur la
  convention, `bootstrap` n'etant pas visible depuis `adapter-content`.
- **Guillemets et `TYP-1`** : `«` et `»` portent un blanc insecable a l'interieur, `?` en porte un
  devant lui ; le caractere, jamais sa notation (`ADR-0060`). Le marqueur de l'item sur la ligne
  `- enonce:`.

## 3. `FRA-CE1-02` (V1)

Patron : `PaquetCe1GraphemesComplexesTest` (marqueur relu dans le fichier source, jeu fautif
commite, `ADR-0013` obligation 3), avec un marqueur par item de la forme
`# VLE-CE1: lettre=c|g|s` et les mots-reperes de `VLE-CE1-2` declares en tete de fichier sous une
forme que le test relit (par exemple `# VLE-CE1-2: c=<dure>,<douce>; ...`).

| Condition | Assertion encodable | Ce qui reste a `nadia-prof` |
|---|---|---|
| `TRV-CE1-1` | `niveau: "CE1"`, `lignesDeReferentiel: ["FRA-CE1-02"]`, aucun `texteAVoixHaute`, `consigneAdulte` ni `texteSupport` | rien |
| `TRV-CE1-2` | un distracteur ; deux propositions distinctes ; bonne reponse strictement la plus longue sur la moitie des items au plus (piege 3) | qu'une seule soit acceptable |
| `TRV-CE1-3` | un seul mot entre `«` et `»`, qui contient la lettre du marqueur | aucune prononciation regionale ; nommer la lettre (*"le g"*) est la forme de son propre exemple (arbitrage, section 3.2) |
| `TRV-CE1-4`, `-5`, `-7`, `LON-CE1-5` | comme pour `FRA-CE1-01`, avec les drapeaux et le compte de la section 2 | la methode de l'indice |
| `VLE-CE1-1` | `c`/`ç` ensemble, `g`, `s`, chacun au moins un quart ; chaque valeur, **calculee** (piege 1), bonne reponse d'au moins un tiers des items de sa lettre ; `g` suivi d'un `e` puis de `a`, `o`, `u`, ou d'un `u` puis de `e`, `i`, `y`, sur au moins un tiers des items de `g` | rien |
| `VLE-CE1-2` | les propositions d'un item sont exactement les deux reperes de sa lettre, sur tout le paquet ; le mot de l'enonce n'est pas un repere ; dans chaque repere, la lettre calcule une seule valeur | que le repere soit celui de la classe |
| `VLE-CE1-3` | lettre cible jamais dans `sc`, `cc`, `ch`, `gn` ; `s` jamais final ni apres une consonne ; `ç` jamais devant `e` ou `i` dans aucune proposition ; lexique des exceptions nommees (`second`, `gageure` et flexions) refuse | les exceptions qu'elle n'a pas nommees |
| exactitude | la bonne reponse est le repere de la valeur calculee | la lisibilite du mot |

**Piege 1, la regle de lecture est calculable, et c'est elle qui rend l'exactitude testable.** Valeur
douce de `c` et `g` devant `e`, `é`, `è`, `ê`, `ë`, `i`, `î`, `ï`, `y` ; dure sinon (donc `gu`
dur, `ge` devant `a`, `o` doux) ; `ç` toujours doux ; `ss` lu comme une seule unite, `[s]` ; `s`
seul entre deux voyelles `[z]`, `[s]` sinon. Les exclusions de `VLE-CE1-3` rendent cette regle
totale sur les mots admis. **L'ensemble des voyelles est accentue** : avec `[eiy]` ASCII,
*cédille* et *musée* se calculent faux, et le test rougit un item juste. Meme classe que
`contientLaGraphieCibleSuivieDUneVoyelle` de `FRA-CE1-01`. Ce que la regle ne voit pas : une
exception de lecture qui donne une troisieme valeur (*second*), d'ou le lexique.

**Piege 2, la lettre presente deux fois.** *garage*, *saison* portent deux valeurs de la meme
lettre : *"le g se lit comme dans"* a alors deux reponses, ce que `TRV-CE1-2` interdit. Assertion :
toutes les occurrences de la lettre cible calculent la meme valeur (`ss` compte une fois). *cerceau*
passe, *garage* rougit.

**Piege 3, des reperes de longueurs differentes fixent le biais de longueur.** Avec les exemples de
`VLE-CE1-2` (*car*/*cerise*, *gare*/*girafe*, *sel*/*rose*), le repere de la valeur douce ou `[z]`
est toujours le plus long. A deux propositions, la bonne reponse est strictement la plus longue
exactement quand elle est ce repere-la. Ces valeurs sont donc bonnes reponses sur la moitie du paquet
au plus, quand `VLE-CE1-1` en exige au moins un tiers par lettre : fenetre compatible, a tenir a la
redaction. Des reperes de meme longueur la feraient disparaitre ; leur choix n'est pas le mien.

**Piege 4, `partDesignee`.** Si la lettre est designee par cette cle, `ADR-0062` clause 2 exige
qu'elle figure une seule fois dans l'enonce. *"Dans « pigeon », le g se lit..."* porte `g` deux
fois : rouge par `UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest`.

**Accentuation** : un `ç` oublie (*garcon*) est vu par `AucunMotServiNeSEcarteDeSonAccentTest`,
la cedille etant une marque retiree par `LexiqueFrancais.squelette`. Rien a ajouter.

## 4. `FRA-CE1-16` (V2)

Patron : le meme, plus `cm1-homophones` pour la forme (un trou `___`, un distracteur), et
`PaquetsFrancaisS04Test.homophonesCm1NePresenteQueLesCouplesAttendusAvecUnUniqueDistracteurApparie`
pour la verification des paires.

| Condition | Assertion encodable | Ce qui reste a `nadia-prof` |
|---|---|---|
| `HOM-CE1-1` | propositions `{a, à}` ou `{et, est}`, casse repliee sous `UNICODE_CHARACTER_CLASS` ; chaque paire au moins un tiers des items ; chaque forme bonne reponse d'au moins un tiers des items de sa paire | rien |
| `HOM-CE1-2` | exactement un `___` par enonce (`ChaquePaquetATrousPorteSonTrouSurChaqueItemTest` exige deja la coherence du trou, perimetre derive) | que la phrase n'admette qu'une forme |
| `HOM-CE1-3` | l'indice contient `avait` (paire `a`/`à`) ou `était` (paire `et`/`est`) en mot entier, et pas la bonne reponse en mot entier | que la substitution soit juste |
| `TRV-CE1-2` | tenue par construction sous `HOM-CE1-1` : `a` et `à` ont une lettre chacun ; `est` n'est plus long que sur les items dont il est la reponse, soit 2/3 x 2/3 = 44 % du paquet au plus. L'assertion reste, elle ne coute rien | une seule acceptable |
| `TRV-CE1-6` | non encodable sans liste de prenoms | prenoms, roles, *"tu"* |
| `LON-CE1-1`, `-2` | deja tenues par `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest` (`ADR-0101`) ; `___` n'y compte pas pour un mot | rien |

**Piege 1, l'indice de `cm1-homophones` ne tient pas `TRV-CE1-5`.** Il recopie la phrase entiere
dans l'indice (*"Remplace par 'était' : 'Papa était maman font la cuisine' ne fonctionne pas."*).
Mesure sur les 24 indices servis, separation par blancs : de 9 a 22 mots, 6 au-dessus de douze.
Recopie, ce patron depasse la borne des que la phrase passe six mots environ.

**Piege 2, le mot-test qui contient la reponse.** *"et puis"* pour tester `et` donne la reponse :
c'est la reserve que `nadia-prof` a fermee sur `cm1-homophones` le 2026-08-19 (Javadoc de
`PaquetsFrancaisS04Test` : tester la substitution du distracteur). La verification en mot entier ne
l'attrape que si les drapeaux de la section 2 sont poses.

**Piege 3, ce qu'aucun garde-fou ne voit.** `AucunMotServiNeSEcarteDeSonAccentTest` ne juge pas
`a` contre `à` ni `ou` contre `où` (sa propre Javadoc) : une confusion ailleurs dans la phrase
passe. Et `TRV-CE1-3` cite `est` parmi les lectures regionales : un indice qui ferait entendre la
difference entre `et` et `est` passerait tous les tests. Les deux a `nadia-prof`.

## 5. `GRC-CE1-R1` (V3)

Items 17 a 20, *colline* au second rang, marqueur `fam=b,c`. Le marqueur nomme la famille par
position : si le mot qui remplace *colline* est de famille (b), le marqueur change avec lui. Le test
ne verifie la famille que pour (a).

- **Longueur** : les quatre items `ill` comptent aujourd'hui parmi les 10 sur 24 ou la bonne reponse
  est strictement la plus longue (*papillon*, *chenille*, *coquille* 8 lettres, *brindille* 9, contre
  *colline* 7). Un remplacement ne peut que baisser ce compte.
- **Le lexique de *ville* n'est verifie que sur la bonne reponse**
  (`grc2AucuneBonneReponseNAppartientAuLexiqueDExclusion`) ; le critere de la story porte sur le
  distracteur qui remplace *colline*. Etendre la verification aux distracteurs : zero proposition du
  paquet dans le lexique a `03d152b0` (72 propositions), vert.
- **Assertion recommandee, mesuree sur le materiau reel** : *aucun distracteur ne figure sur plus de
  deux items du paquet*. A `03d152b0`, sur 29 distracteurs distincts, un seul depasse : *colline*,
  4 ; tous les autres figurent deux fois au plus. Elle porte la propriete du second passage de `nadia-prof` (section 3 :
  *"aucune paire de distracteurs ne revient sur plus de deux items"*), au-dela du seul mot de la
  reserve. Q2.
- **En-tete du YAML** : *"douze items sur vingt-quatre"* (10), *"six formes distinctes"* (5), Q1
  *"non repondues a ce jour"* sont faux (fiche, second passage, section 6). Commentaires, pas
  contenu servi : a corriger dans le meme commit, comme a s49. L'unification des familles de
  *cuisine*/*farine* et *canine*/*racine* touche les marqueurs des items 9 a 12, hors du critere de
  la story : Q3.

## 6. `SOU-CE1-R1` (V4) : infaisable en l'etat

**Le fait, mesure.** Rangs de la bonne reponse sur les 32 items, par la regle de `rangDe` :

| Etat | Plus petite | Intermediaire | Plus grande | Verdict de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` |
|---|---|---|---|---|
| `03d152b0` | 15 (46,9 %) | 13 (40,6 %) | 4 (12,5 %) | vert |
| corrige comme la fiche le demande (`48`, `61`, `73`, `85`) | 15 (46,9 %) | 17 (53,1 %) | 0 (0,0 %) | **rouge deux fois** : majorite (53,1 % > 50 %) et seuil minimal (0 % < 10 %) |

Les deux rouges ont ete obtenus en lancant le garde-fou sur le YAML corrige, pas deduits (section 9).

**La cause : sous la clause 1.4 de `nadia-prof`, la bonne reponse ne peut jamais etre la plus
grande.** `a + b` est toujours superieur a `a - b`, l'emprunt oublie aussi. Une bonne reponse n'est
la plus grande que si ses deux distracteurs sont plus petits, ce qu'aucune des trois formes
autorisees ne donne. Les quatre distracteurs que la reserve vise (`20`, `25`, `30`, `35`) sont les
quatre seuls items du paquet ou elle l'est, et ils portaient le seuil de 10 % a 12,5 % : le message
de `3b734fd5`, qui a livre ce paquet, ecrit *"12,5% plus grande"*. Le defaut nomme par la reserve
tenait le garde-fou.

**Pourquoi je n'ouvre pas d'exemption.** `DETTE_DE_RANG` est a 2 sur `PLAFOND_DE_DETTE` 2 et
`DETTE_DE_SEUIL_MINIMUM` a 3 sur 3. Les deux plafonds *"ne remontent jamais"*. Relever un cliquet
pour faire passer une correction, c'est un cliquet dont le plafond depasse la dette.

**Ce qui debloque, et c'est une decision de `nadia-prof`, pas la mienne** (Q1). La propriete a
tenir sur le paquet corrige : au moins 4 items sur 32 ou les deux distracteurs sont inferieurs a la
bonne reponse, et au plus 16 items intermediaires. Toute forme de distracteur qui y mene sort de la
clause 1.4 telle qu'ecrite ; il existe un precedent d'ajout de forme sur ce vivier (`SOP-CE1-R1`,
cinquieme forme admise a s47). Le developpeur ne peut pas l'inventer.

## 7. `DIV-CE1-R1` (V5)

Huit items, `a x b` remplace par `b` : `["10", "5"]`, `["18", "9"]`, `["24", "6"]`, `["32", "8"]`,
`["36", "9"]`, `["40", "8"]`, `["45", "9"]`, `["50", "10"]` (items 8, 10, 11, 13, 14, 19, 20, 21).
Sur chacun, `b`, `a - b` et le quotient sont distincts et positifs.

- **Rangs inchanges** : 8 / 7 / 6 (38,1 / 33,3 / 28,6 %) avant et apres. Sur ces huit items la bonne
  reponse reste la plus petite, `b` lui etant superieur.
- **`PaquetCe1DivisionTest` vert apres correction** : `b` appartient aux erreurs identifiees. Il est
  aussi vert avant : son assertion (`anyMatch` sur `a x b`, `a - b`, `b`) ne mord pas sur la
  reserve.
- **Assertion recommandee** : *chaque item porte le diviseur recopie `b` parmi ses distracteurs*.
  Mesuree a `03d152b0` : rouge sur exactement les items 8, 10, 11, 13, 14, 19, 20, 21 ; verte apres.
  Elle exige plus que `DIV-CE1-5`, dont `nadia-prof` ecrit que la reserve *"n'est pas un
  durcissement"* : je ne l'ecris dans le test qu'avec son accord (Q2).

## 8. Questions et routages, via `sofia-sm`

| Code | Point | A qui | Bloquant ? |
|---|---|---|---|
| Q1 | `SOU-CE1-R1` : la correction prescrite rougit le garde-fou du rang sur ses deux clauses (section 6). Quelle forme de distracteur, inferieure a la bonne reponse, admettre sur au moins 4 items ; ou lever la reserve autrement | `nadia-prof` | **oui, pour `SOU-CE1-R1` seule** |
| Q2 | Deux assertions de paquet qui portent plus que la reserve : *aucun distracteur sur plus de deux items* (`GRC-CE1-R1`), *`b` sur chaque item* (`DIV-CE1-R1`). A ecrire ? | `nadia-prof` | non ; sans reponse, ces deux corrections restent gardees par elle seule |
| Q3 | Unifier les familles des items 9 a 12 de `ce1-graphemes-complexes` dans le commit de `GRC-CE1-R1`, hors de son critere | `camille-po` | non |
| Q4 | `FRA-CE1-16` : une phrase de `cm1-homophones` peut-elle etre reprise mot pour mot au CE1 ? Aucun garde-fou ne compare deux paquets | `nadia-prof` | non |
| Q5 | Le jar servi sur le port 8089 tient `bootstrap/target/recreo.jar` : tout `clean verify` dans l'arbre principal echoue a `clean` tant qu'il tourne. La CI n'est pas touchee | `sofia-sm` | non |

## 9. Mesure

- **`mvn verify`** : section 0. Journaux conserves hors du depot. Worktree detache a `03d152b0`, cree
  et supprime pour cette mesure.
- **Rangs de V4 et V5** : script Python de poste, non commite, par la regle de `rangDe` sur les deux
  YAML a `03d152b0`, puis sur les memes corriges en memoire. Puis, dans le worktree, les deux YAML
  corriges par remplacement des seules listes de distracteurs (12 lignes) et
  `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test
  -Dtest=LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest,PaquetCe1DivisionTest,ChaquePaquetDeSoustractionsDeCe1TientSaRepartitionParFormeTest` :
  44 tests, 2 echecs, tous deux sur `ce1-soustractions-jusqu-a-100` (*"petite=15,
  intermediaire=17, grande=0"*) ; `PaquetCe1DivisionTest` 7 sur 7 vert, aucun echec ne nomme
  `ce1-division`.
- **Assertions recommandees de V3 et V5** : meme script, sur les YAML a `03d152b0`. V3 : decompte
  des distracteurs sur les 24 items. V5 : presence de `b`, lu comme le plus petit des deux nombres
  de l'enonce, comme le fait `PaquetCe1DivisionTest`.
- **Comportement des expressions regulieres** (section 2) : programme Java de poste, non commite,
  lance sur JDK 21.0.10.
- **Indices de `cm1-homophones`** : 24 indices, separation par blancs.
- **Garde-fous cites** : lus dans le code a `03d152b0`. Hors V4 et V5, aucun n'a ete execute sur un
  paquet corrige ou neuf ; leurs verdicts sur les lignes neuves et sur `GRC-CE1-R1` sont deduits de
  leur code.
