Rapport qualite - s17 (francais CM1 et dette), yanis-qa

Mesure sur `s17-yanis`, fast-forward sur `round-two` a `150c428` (fusion lot nadia-prof - COMP-5
accepte sous conditions). Worktree dedie : `git worktree add ../recreo-s17-yanis -b s17-yanis
round-two`, lignee verifiee par `bash outils/verifier-lignee.sh`. Une fusion (`sofia-dette`, s17,
8.1 et 8.2) est arrivee sur `round-two` pendant l'instruction ; le worktree a ete rebase par
fast-forward (`git merge --ff-only round-two`) avant de mesurer le point 3, comme demande.

## Etat du build

`bash outils/mvn-jdk21.sh -B clean verify` sur `150c428`, JDK 21.0.10 (toolchain, `java` du PATH
non concerne) : **BUILD SUCCESS**, reacteur complet (recreo, domain, application, adapter-web,
adapter-content, bootstrap), 3 min 53 s. Les quatre tests d'integration de `bootstrap`
(`CatalogueChargeableIT`, `PropositionsAccentueesJouablesIT`, `JarRecreoIT`,
`LeLivrableNeSertQueLesRessourcesDeSesModulesIT`, 6 methodes) passent. Aucun test instable
rencontre pendant les runs cibles ci-dessous.

Ce rapport ne porte pas de Done pedagogique : `cm1-synonymes` (branche `s17-lea`, non fusionnee)
n'a pas ete rejoue par `nadia-prof`, et rien ici ne s'y substitue.

## 1. `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` sur `cm1-synonymes` : constat de
`lea-dev` confirme, garde-fou vide

**Verifie a la source**, pas pris sur parole : lecture de `git show
s17-lea:adapters/adapter-content/src/main/resources/contenu/cm1-synonymes.yaml` (20 items, 60
propositions - une bonne reponse et deux distracteurs par item, tous des mots ou groupes de mots :
"heureuse", "rapidement", "tranquille", etc.) et du code de
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (`adapters/adapter-content/src/test/java/
com/recreo/adapter/content/LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java`).

**Propriete que ce garde-fou peut structurellement voir, et celle qu'il ne peut pas** (amendement
s11) : il classe la bonne reponse d'un item en "la plus petite / intermediaire / la plus grande"
par **valeur numerique lue** (`valeurDe`, ADR-0041 : duree, somme de termes, ou nombre a
separateurs de milliers retires). Sur une proposition textuelle non numerique, `valeurDe` rend
`null` ; `rangDe` rend alors `null` des que `bonne == null` ; `repartitionDe` ignore tout item de
rang `null` (`if (rang == null) continue;`) ; et la methode principale ignore tout paquet dont le
total resultant est nul (`if (repartition.total() == 0) continue;`, ligne 519). Sur les 60
propositions de `cm1-synonymes`, aucune ne commence par un chiffre : `valeurDe` rend `null` pour
les 60, `rangDe` rend `null` pour les 20 items, `repartition.total()` vaut 0, et le paquet **ne
contribue aucun defaut, quel qu'il soit, a la liste `defauts`** de
`leRangDeLaBonneReponseNEstPasPredictibleSurLesPaquetsDeProduction`. Le test resterait vert si les
20 items donnaient systematiquement la meme reponse en tete de liste, dans le meme ordre, sur
chaque manche : rien dans ce garde-fou ne peut le voir sur ce contenu. Constat de `lea-dev`
confirme a l'identique.

**Combien de paquets deja servis ce garde-fou voit reellement, combien il exclut en silence**
(mesure a la source `150c428`, script temporaire non commite dans
`adapters/adapter-content/src/test/java/.../DiagnosticTemporaireYanisTest.java`, execute par
`bash outils/mvn-jdk21.sh -pl adapters/adapter-content -am test -Dtest=DiagnosticTemporaireYanisTest
-Dsurefire.failIfNoSpecifiedTests=false`, puis retire - perimetre : les 12 paquets de
`adapters/adapter-content/src/main/resources/contenu/`, comptage par item et par lecture de
valeur reelle) :

| Paquet | Items a 2 distracteurs | Items a 3 valeurs numeriques | Vu par le garde-fou de rang ? |
|---|---|---|---|
| cm1-conjugaison-present | 0/25 | 0 | non - 0/25 a exactement 2 distracteurs |
| cm1-grands-nombres | 28/28 | 14 | oui, partiellement (14 items) |
| cm1-homophones | 0/24 | 0 | non - 0/24 a exactement 2 distracteurs |
| cm1-mesures | 24/24 | 24 | oui (dette DETTE_DE_RANG existante) |
| cm1-perimetre | 22/22 | 20 | oui (dette DETTE_DE_RANG existante) |
| cm1-tables-multiplication | 99/99 | 99 | oui |
| cp-additions-jusqu-a-10 | 65/65 | 65 | oui |
| cp-comparer-nombres | 24/24 | 0 | non - propositions "<", ">", "=" |
| cp-complements-10 | 11/11 | 11 | oui |
| cp-le-bon-son | 15/15 | 0 | non - propositions phonemes textuels |
| cp-problemes-additifs | 22/22 | 22 | oui |
| cp-soustractions | 24/24 | 24 | oui |

**4 des 12 paquets de production actuels (33 %) contribuent deja zero item au calcul**, sans
qu'aucune ligne du depot ne le declare : `cm1-conjugaison-present`, `cm1-homophones` (aucun item a
exactement deux distracteurs), et `cp-comparer-nombres`, `cp-le-bon-son` (propositions non
numeriques). Pour `cp-comparer-nombres` et `cp-le-bon-son` l'exclusion est legitime au sens
d'ADR-0041 (une proposition non numerique est hors du domaine de cette propriete precise, pas un
defaut) - mais rien dans le depot ne distingue aujourd'hui "hors du domaine, legitimement" de "vide
et personne ne le sait". `cm1-synonymes` (branche non fusionnee) serait un cinquieme cas, avec un
total de 20 items a exactement deux distracteurs et une exclusion totale malgre tout, contrairement
aux quatre paquets ci-dessus dont une partie des items est deja hors-jeu pour une autre raison
(nombre de distracteurs different de deux).

**Existe-t-il un autre garde-fou du depot qui garde cette propriete sur du contenu textuel ?**
Non. Recherche `grep -r "predictible|Predictible"` sur tout le depot : seuls deux tests portent
cette famille de propriete -
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (rang par valeur, numerique seulement,
etudie ci-dessus) et `LaPositionDeLaBonneReponseALEcranNEstPasPredictibleSurUnGrandNombreDeTiragesTest`
(`domain/src/test/java/com/recreo/domain/mechanics/banditmanchot/`). Ce second test est d'une
nature differente : il mesure le melange Fisher-Yates de `BanditManchotSession#melanger` sur un
**item synthetique unique et fixe** ("enonce", "bonne", "distracteur1", "distracteur2"), jamais un
paquet reel, et sur un axe disjoint (la position a l'ecran, pas l'ordre de valeur des
propositions). Il ne lit aucun contenu de production et ne peut donc rien dire de
`cm1-synonymes` ou d'aucun autre paquet textuel. **Sur du contenu textuel reel, la propriete "une
strategie qui ignore l'enonce ne doit pas gagner la majorite des manches" n'est gardee par rien.**

**Le garde-fou signale-t-il son silence ?** Non. Contrairement a
`LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` (ADR-0041), qui declare explicitement dans
`PAQUETS_A_ITEMS_ECARTES` les paquets juges sur une **partie** de leurs items et fait rougir toute
declaration perimee ou manquante, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` n'a
aucun mecanisme equivalent pour les paquets juges sur **aucun** de leurs items. Le commentaire de
cette clause ADR-0041 le dit lui-meme : "sur un paquet entierement hors du domaine, aucun taux
n'est calcule et rien n'est affirme" - une position deliberee et documentee de ce garde-fou-la, qui
n'a pas d'equivalent du cote du garde-fou de rang. **Un garde-fou qui exclut son objet en silence
est pire qu'un garde-fou absent : celui-ci occupe la place d'une preuve sur les 20 items de
`cm1-synonymes` sans qu'aucune ligne verte ou rouge ne dise qu'il ne les a jamais vus.**

**Ce qu'un garde-fou capable de voir la propriete sur du texte devrait mesurer** - route a
`sofia-sm` pour `theo-archi`, decision de mecanisme hors de mon mandat : la propriete generique
("une strategie qui ignore l'enonce ne doit pas gagner la majorite des manches d'un paquet") est
independante du type de contenu, mais sa mesure actuelle (`valeurDe`) est un mecanisme purement
numerique. Deux options existent sans que je tranche laquelle : (a) une seconde lecture de
"comparabilite" pour du texte (par exemple, sur `cm1-synonymes`, verifier qu'aucun rang de
distracteur ordonne alphabetiquement, ou par longueur, ne domine), ou (b) un garde-fou minimal qui
se contente de signaler, comme `PAQUETS_A_ITEMS_ECARTES` le fait deja pour son propre garde-fou,
tout paquet dont `repartition.total() == 0` sur `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`
- rendre le silence visible sans pretendre le combler. Les deux sont des decisions de mecanisme,
pas de simples corrections de bug.

## 2. `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` : mord dans le sens attendu, muet sur un
autre

Lecture du test (`adapters/adapter-content/src/test/java/com/recreo/adapter/content/
UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest.java`) : la fonction `replie()` normalise une
matiere par retrait des diacritiques (`Normalizer.normalize(..., NFD)` puis filtrage des
`NON_SPACING_MARK`) et mise en minuscules. Deux graphies sont en conflit si et seulement si elles
"replient" sur la meme chaine. C'est exactement l'axe accent/casse, et rien d'autre.

**Morsure rejouee sur du materiau reel** (pas la fixture `contreexemple/.../deux-graphies-de-
francais`) : copie de deux fichiers reels de production
(`cm1-conjugaison-present.yaml`, `cm1-homophones.yaml`, tous deux `matiere: "Francais"`) sous
`src/test/resources/contreexemple/UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest/mutation-
blanc-yanis-temp/contenu/`, mutation d'un seul caractere sur un seul fichier
(`matiere: "Francais "`, espace de fin ajoute sur `cm1-homophones.yaml` uniquement - ni accent, ni
casse, une simple divergence de blancs), execution du test reel
`verifierQuUneMatiereNAQuUneGraphie` sur ce jeu via un test temporaire, retire apres mesure :
`bash outils/mvn-jdk21.sh -pl adapters/adapter-content -am test
-Dtest=DiagnosticMutationMatiereYanisTest -Dsurefire.failIfNoSpecifiedTests=false` - **BUILD
SUCCESS, 1 test, 0 echec**. Le garde-fou reste vert alors que le contenu servi porterait reellement
deux chaines de matiere distinctes ("Francais" et "Francais ") - `replie("Francais")` et
`replie("Francais ")` different par l'espace non retire, donc les deux graphies tombent dans deux
groupes separes de la map `graphiesParMatiere` (taille 1 chacun) au lieu d'un seul groupe de taille
2, et aucun ecart n'est remonte.

**Reponse a la question posee** : la regle mord dans le sens ou son auteur l'a pensee (accent
contre ASCII, la fixture existante le prouve deja) mais ne mord pas dans un autre sens tout aussi
reel - une divergence de blancs sur le meme mot, sans accent ni casse en jeu. Le repliage retire
deliberement les diacritiques et la casse (Javadoc : "compare sans casse") mais ne touche a aucun
espace : deux graphies identiques au caractere pres sauf un blanc de bord restent invisibles au
critere d'unicite qu'il pretend garder. Fichier et etat mutes retires apres la mesure, `git status
--short` confirme l'arbre propre.

Route a `sofia-sm` pour `theo-archi` : etendre `replie()` pour absorber au moins les blancs de bord
(et, plus generalement, decider quelle classe d'ecarts hors accent/casse est dans le perimetre
d'ADR-0049) est une decision de mecanisme sur un test qui n'est pas le mien ; je ne l'ai pas
ecrite.

## 3. Les deux dettes de `sofia-sm` (casquette dev), fusionnees sur `round-two` a `150c428`

Les deux commits cites (`e6173ae`, `f35a218`) sont deja fusionnes sur `round-two` (commit de
fusion `971acaa`, "s17: fusion lot sofia-dette") au moment de la mesure ; mesure faite sur l'arbre
fusionne comme demande, apres fast-forward du worktree.

### 8.1 - duree du marqueur verifiee sur deux durees dans la meme epreuve

Lecture : `PartieControllerTest.laDureeDeVieDuMarqueurEstLueSurLaSessionEtJamaisRecopiee`
(`adapters/adapter-web/src/test/java/com/recreo/adapter/web/PartieControllerTest.java:601-615`)
pose une premiere session a 900 s, lit le cookie, pose une seconde session **distincte** a 1800 s,
relit - deux assertions d'egalite dans la meme methode, sur deux valeurs differentes.

**Rejoue, pas recopie, dans les deux sens** sur `PartieController.marquerPartieEnCours`
(`adapters/adapter-web/src/main/java/com/recreo/adapter/web/PartieController.java:426`) :

- mutation `cookie.setMaxAge(1800)` (constante cablee, valeur du **second** probe) :
  `bash outils/mvn-jdk21.sh -pl adapters/adapter-web -am test
  -Dtest=PartieControllerTest#laDureeDeVieDuMarqueurEstLueSurLaSessionEtJamaisRecopiee
  -Dsurefire.failIfNoSpecifiedTests=false` -> **rouge**,
  `expected: 900 but was: 1800` (premiere assertion) ;
- mutation `cookie.setMaxAge(900)` (constante cablee, valeur du **premier** probe, non essayee par
  `sofia-sm`) : meme commande -> **rouge**,
  `expected: 1800 but was: 900` (seconde assertion) ;
- production restauree a l'identique (`git status --short` propre) : meme commande -> vert (1
  test, 0 echec).

Les deux mutations rougissent, chacune sur une assertion differente de la meme methode. **La
clause tient par une propriete de la regle** ("l'egalite est verifiee sur au moins deux durees
differentes dans la meme epreuve") et non par une coincidence attachee a 1800 : n'importe quelle
constante cablee ne peut satisfaire qu'au plus une des deux valeurs distinctes posees (900 et
1800), donc au moins une assertion echoue quelle que soit la constante. Le defaut d'origine (deux
tests distincts, dans deux classes distinctes, dont la divergence fortuite des fixtures faisait la
preuve) ne reapparait pas : la preuve tient desormais a l'interieur d'une seule methode, sur une
propriete generale plutot que sur la valeur precise d'un voisin.

### 8.2 - fraicheur des classes amont : ce qui est nomme manquant est reel, mais incomplet

Lecture des trois copies identiques de `ProvenanceDesClassesAmontEstLeDepotTest`
(`application`, `adapter-web`, `adapter-content` - meme logique dans les trois modules). La
comparaison ajoutee (`verifierFraicheur`) exige que l'horodatage du `.class` compile ne soit jamais
anterieur a celui du `.java` source. Le commit et la Javadoc nomment une seule limite : "un `touch`
sur le `.class` suffirait a la tromper sans recompiler quoi que ce soit" - un faux vert.

**Verification demandee : "ce que la comparaison ne prouve pas" est-il bien nomme, et rien de
plus ?** Non - le nomme est reel mais incomplet. La comparaison ne repose que sur deux horodatages,
jamais sur un contenu ou un hash ; elle est donc symetriquement aveugle dans l'**autre** sens, non
nomme : un `.java` dont l'horodatage avance sans que son contenu change (un `touch`, un
enregistrement sans modification, un `git checkout` qui reinitialise les dates de fichiers - le
depot le sait deja et le dit ailleurs : "Git ne conserve aucun horodatage de fichier au clonage",
meme Javadoc, quelques lignes plus haut) fait rougir le garde-fou sur un `.class` pourtant
parfaitement a jour.

Rejoue sur l'artefact reel, hors fixture, symetriquement a la preuve de `sofia-sm` :
- `bash outils/mvn-jdk21.sh -pl domain -am install -DskipTests` : `GameMechanic.class` compile
  a `19:56:49`, `GameMechanic.java` modifie a `19:51` (le `.class` est reellement plus recent, cas
  sain) ;
- baseline verifiee verte : `bash outils/mvn-jdk21.sh -pl application -am test
  -Dtest=ProvenanceDesClassesAmontEstLeDepotTest#laClasseDeDomainVientDuTargetDuDepotEtNonDuDepotLocal
  -Dsurefire.failIfNoSpecifiedTests=false` -> 1 test, 0 echec ;
- `touch domain/src/main/java/com/recreo/domain/mechanic/GameMechanic.java` (horodatage avance a
  `20:14:10`, **contenu strictement inchange** - pas un octet modifie) ;
- meme commande de test, avec `-Dmaven.main.skip=true` (comme `sofia-sm` pour eviter qu'une
  recompilation efface la simulation) -> **rouge** :
  `le .class compile (..., horodatage 2026-08-19T17:56:49Z) est plus ancien que son .java source
  (..., horodatage 2026-08-19T18:14:10Z)`.

Le `.class` n'a pourtant jamais eu besoin d'etre recompile : rien dans `GameMechanic.java` n'a
change. `bash outils/mvn-jdk21.sh -q clean` ensuite ; `git status --short` confirme qu'aucun
contenu n'a bouge (seul un `touch`, jamais suivi de git puisque `target/` est ignore et le contenu
du `.java` est identique). **Ce que la comparaison ne prouve pas est donc plus large que ce qui est
nomme** : elle ne peut distinguer "modifie et non recompile" de "horodatage avance sans contenu
modifie", dans les deux sens, parce qu'elle ne compare que des metadonnees de date, jamais un
contenu. Le sens nomme (faux vert par `touch` sur le `.class`) est reel et bien decrit ; le sens
non nomme (faux rouge par avance d'horodatage sur le `.java`, dont un `git checkout` sans `mvn
clean` prealable est une cause plausible dans ce depot) ne l'est pas.

Ce faux rouge est moins dangereux qu'un faux vert - il force une investigation au lieu de la
dispenser - mais reste un defaut au sens de ce role : un test qui peut rougir sans raison reelle
est un test instable en puissance, meme s'il ne s'est pas encore manifeste en CI (la commande
officielle, `clean verify` sur reacteur complet, reconstruit tout apres checkout et n'expose donc
pas ce cas ; le risque est dans les invocations partielles sur un checkout existant, exactement la
famille de geste que ce depot documente par ailleurs comme fragile). Route a `sofia-sm` pour
`theo-archi` : completer la Javadoc des trois copies avec ce second angle mort est une simple
correction documentaire que je nomme mais n'ecris pas, puisque le fichier appartient au lot qu'on
m'a demande de ne pas modifier.

## Devoir permanent - listes enumerees auto-declarees rencontrees

En investiguant le point 1, j'ai croise trois listes enumerees d'exemption dans
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` et
`LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` (`DETTE_DE_RANG`,
`DETTE_DE_SEUIL_MINIMUM`, `PAQUETS_A_ITEMS_ECARTES`) - anterieures a ce sprint (s12-s13), pas
neuves dans les trois lots demandes. Chacune porte deja son propre garde-fou de peremption
(`laDetteNePeutNiGrandirNiPourrir`, `laDetteDuSeuilMinimalNePeutNiGrandirNiPourrir`, l'egalite
stricte de `PAQUETS_A_ITEMS_ECARTES`), verifie vert dans le `clean verify` de ce rapport : aucun
membre de ces trois listes n'y epingle un incident clos, chacun est une categorie mesuree encore
active (les pourcentages cites dans leur Javadoc, 90,0 %, 54,2 %, 0,0 %, 8,3 %, 9,1 %, sont
recalcules a chaque run et font rougir le test si perimes). Aucune liste enumeree neuve n'a ete
introduite dans les points 1 a 3 de ce sprint : les deux dettes de `sofia-sm` (8.1, 8.2) ne
reposent sur aucune enumeration, et la regle de `theo-archi` (ADR-0049) est un calcul, jamais une
liste.

## Ce que je route, recapitulatif

A `sofia-sm`, pour `theo-archi` (decisions de mecanisme, pas de bug simple) :
1. rendre visible - ou combler - le silence de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`
   sur les paquets entierement hors de son domaine numerique (au moins 4 paquets de production
   aujourd'hui, `cm1-synonymes` en ferait un cinquieme) ;
2. etendre le repliage d'`UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` au-dela de
   l'accent/casse, au moins aux blancs de bord ;
3. completer la Javadoc de `ProvenanceDesClassesAmontEstLeDepotTest` (les trois copies) avec le
   second angle mort de la comparaison de fraicheur (faux rouge par avance d'horodatage sans
   modification de contenu), symetrique de celui deja nomme.

Aucun Done pedagogique n'est prononce dans ce rapport. Aucun fichier des autres lots n'a ete
modifie ; toute mutation de production a ete restauree a l'identique et verifiee par `git status
--short` avant la fin de chaque mesure.
