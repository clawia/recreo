# Rapport qualite - sprint 11 (la tabulation ne detruit plus la partie)

- **Auteur** : yanis-qa
- **Date** : 2026-08-14
- **Worktree** : dedie, provisionne depuis `round-two` sur une branche neuve (`s11-yanis-qualite`),
  hors du depot principal et hors du worktree du `relecteur-pedagogique` qui travaille en parallele.
  Lignee verifiee : `bash outils/verifier-lignee.sh` -> "le worktree sur la branche
  's11-yanis-qualite' descend de la lignee de round-two (ancetre commun trouve dans son
  historique)".
- **Commit audite** : `1421d46`, tete de `round-two` au moment de la saisine.
- **Constat de JDK** (`bash outils/verifier-jdk.sh`) : `java du PATH : 1.8.0_202 | JDK de
  compilation : 21.0.10 | JAVA_HOME : vide | toolchains lu par Maven : non (fichier present sous un
  nom non lu)`. Meme anomalie de poste que les sprints precedents. `JAVA_HOME` a ete pointe sur ce
  JDK 21 pour la duree de chaque commande citee ci-dessous, jamais ecrit dans un fichier commite.

Ce rapport ne recopie aucune mesure sans l'avoir soit rejouee, soit refutee - y compris les
garde-fous cites comme fermant un trou, casses puis remis en etat, jamais relus seulement.

## 1. Porte technique

```
mvn -B clean verify
```

**Resultat : BUILD SUCCESS**, six modules en SUCCESS, aucun test ignore.

| Module | Tests (agregat Maven, "Results:") |
|---|---|
| domain | 46 |
| application | 7 |
| adapter-web | 216 |
| adapter-content | 64 |
| bootstrap (surefire) | 77 |
| bootstrap (failsafe) | 6 |
| **total** | **416** |

**416 confirme le chiffre de `sofia-sm`, et 28 confirme celui des regles ArchUnit** - methode
inchangee depuis s06 : recherche litterale de `ArchRule <nom> =` sous `src/test/java` des cinq
modules du pom agregateur, fixture de contre-exemple de `LesGardeFousSontMisALEpreuveTest` exclue
(elle vit sous `src/test/resources`, jamais compilee comme regle du depot). Resultat : 1 dans
`adapter-web` (`AssertionsWebTest`), 27 dans `bootstrap` (dix classes de `com.recreo.archi`) = **28**.

**Garde-fou de chemins, rejoue avant d'ecrire ce rapport** :
```
bash .github/scripts/verifier-chemins-test.sh    -> 22 cas, tous passes, EXIT=0
git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh
  -> "Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.", EXIT=0
```
Confirme sur mon propre lot, avant commit, comme demande.

### Tranche laquelle est autoritaire : 416, jamais le total des fichiers `.txt` de surefire/failsafe

Deux methodes divergent d'exactement 7 tests, et j'ai etabli pourquoi plutot que de choisir a
l'oreille.

- **Somme des lignes `Results:` du journal du reacteur Maven** (calculee ci-dessus, sur `mvn -B
  clean verify` complet apres `clean`) : **416**.
- **Somme de l'attribut `tests="..."` de chaque `TEST-*.xml` de `target/surefire-reports` et
  `target/failsafe-reports`** : **416**, identique.
- **Somme de la ligne `Tests run:` de chaque `*.txt` de ces memes repertoires** : **409**. C'est le
  chiffre que `sofia-sm` accuse de sous-compter, et elle a raison sur le fait, pas sur sa cause.

**Ce qui sous-compte, et ou** : quatre fichiers `.txt`, pas un seul, et la cause n'est pas la
parametrisation - c'est le melange, dans une meme classe, d'un champ statique `@ArchTest
ArchRule` (execute par le moteur JUnit5 propre a `archunit-junit5`, un `TestEngine` distinct de
Jupiter) et d'au moins une methode `@Test` ordinaire (executee par Jupiter). Le rapport XML de la
classe agrege correctement les deux moteurs (`tests="N"` juste) ; le rapport `.txt`, lui, n'ecrit
que le compte du moteur Jupiter pour cette classe :

| Classe | `.txt` (Jupiter seul) | `tests=` XML (les deux moteurs) | ecart |
|---|---|---|---|
| `AssertionsWebTest` (adapter-web) | 1 | 2 | 1 |
| `AbstractionsCiteesTest` (bootstrap) | 1 | 5 | 4 |
| `ChargementDuContenuTest` (bootstrap) | 1 | 2 | 1 |
| `LesReglesMordentTest` (bootstrap) | 25 | 26 | 1 |
| **total** | | | **7** |

Verifie testcase par testcase dans chaque `TEST-*.xml` : les entrees "manquantes" au compte `.txt`
portent un `classname` sans le paquet (`classname="AbstractionsCiteesTest"` au lieu de
`"com.recreo.archi.AbstractionsCiteesTest"`) et un nom de methode qui est en realite le nom du
champ `@ArchTest` (`aucunTypeNommeRuleSet`, `contentSourceExiste`...) - la signature exacte d'une
regle ArchUnit executee par son propre moteur, pas d'un test parametre JUnit5.

**416 est le chiffre autoritaire** : c'est celui que Maven lui-meme rend comme agregat du reacteur,
c'est celui que porte l'attribut `tests=` de chaque rapport XML, et c'est le seul des trois qui ne
perd aucune regle ArchUnit reellement executee. Les fichiers `.txt` de surefire/failsafe ne
doivent plus servir de source de compte dans ce depot des qu'une classe combine un champ
`@ArchTest` et une methode `@Test` - ce qui est le cas d'au moins quatre classes aujourd'hui, dans
deux modules differents.

## 2. Garde-fous casses puis remis en etat sur du materiau reel

Regle acceptee a la cloture de s10 (`squad/README.md`, section 4) : appliquee ici sur cinq
mutations, chacune sur un fichier de production reel, jamais une fixture, avec restauration
verifiee par `git status --porcelain` (vide avant et apres chaque mutation).

| Garde-fou | Mutation | Resultat | Restaure |
|---|---|---|---|
| `PartieControllerTest.leBoutonDeRetourDemandeConfirmationSansClorePartieEnCours` (ADR-0038, point 3) | `data-role="sortie"` -> `data-role="action"` sur le bouton reel qui detruit dans `fragments/confirmation.html` | **rouge**, 1 echec sur 25, "Expecting actual not to be empty" a la ligne `[data-role=sortie]` | oui |
| `AucunArretDeTabulationNEstDestructeurTest` (les deux clauses, non-vacuite) | `ecrans()` reduit a une liste vide dans le test reel | **rouge**, 5 echecs sur 6 (les deux non-vacuites de clause, le recensement de routes, le corpus non vide) ; seule `laFormeDeLaProductionAvantCeLotFaitEchouerLaClause1` (fixture figee) reste verte | oui |
| `TexteAccentueDesGabaritsTest.leTexteDeConfirmationEstCeluiArbitreParNadiaProf` | un mot change dans la phrase arbitree, dans `fragments/confirmation.html` reel | **rouge**, 1 echec sur 5 | oui |
| meme test, mutation B | la meme phrase, texte et classe inchanges, deplacee apres les deux boutons dans le meme fragment reel | **vert** (attendu : selecteur par classe, insensible a la position) | oui |
| `RessourcesTiercesTest.aucunJavaScriptDeBibliothequeDInterfaceNEntreDansLeDepot` | ajout reel d'un fichier `alpine.min.js` (bibliotheque de comportement d'interface, pas nomme "bootstrap") sous `static/vendor/`, avec une ligne de provenance correctement tracee et une empreinte SHA-256 exacte | **vert sur les 16 tests**, y compris celui cense refuser ce geste - voir section 3 | oui (fichier supprime, `provenance.txt` restaure par `git checkout --`) |

Apres restauration : `git status --porcelain` vide sur l'ensemble du worktree, et `mvn -B -o -pl
domain,adapter-web test` rejoue en fin de sequence -> `Tests run: 46` (domain) et `Tests run: 216`
(adapter-web), 0 echec, identique a la mesure de la section 1.

## 3. La question sur le critere enumere - deux autres instances trouvees, une distinguee

`TexteAccentueDesGabaritsTest.aucunGabaritNeReintroduitUneFauteConnue` derive bien son perimetre
(balayage du repertoire de gabarits) mais son critere - une liste fermee de fautes passees,
nommees, datees - ne peut structurellement rien exiger d'un texte qui n'existait pas au moment ou
la liste a ete ecrite. C'est la propriete a chercher ailleurs : **une liste dont chaque membre est
le nom d'un incident historique precis, jamais une categorie qui generalise**, sur une population
elle-meme correctement derivee.

**Methode de recherche** : ADR-0013 exige que tout choix d'un "critere enumere" au lieu d'un
"perimetre enumere" soit ecrit en prose dans le test lui-meme (c'est la clause qui rend ce choix
legitime plutot que suspect). J'ai donc cherche cette auto-declaration dans le depot plutot que de
deviner : `grep` sur "critere enumere", "liste ecrite a la main", "jamais un perimetre". Quatre
fichiers la portent.

**Une deuxieme instance confirmee par mutation, pas seulement par lecture** :
`RessourcesTiercesTest.verifierAucunJavaScriptTiersRefuse` (ADR-0014/ADR-0008). Perimetre derive
(balayage reel de `static/vendor/`, confirme par `chaqueRessourceTierceEstTraceeEtIntacte` qui
exige que tout fichier present soit trace, sous peine de rougir). Critere : `JAVASCRIPT_TIERS_REFUSE
= List.of("bootstrap")`, avec ce commentaire exact dans le code : *"un nom absent de cette liste
n'echappe a rien : il devra de toute facon etre vendorise et trace, donc apparaitre en diff"*.
**J'ai teste cette affirmation et elle est fausse.** J'ai ajoute un fichier `alpine.min.js` reel
sous `static/vendor/`, avec une ligne de provenance complete et une empreinte SHA-256 exacte -
exactement le geste "correct" que la doctrine attend d'un ajout legitime. Les 16 tests de la classe
restent verts, y compris celui qui pretend refuser "le JavaScript de composants d'une bibliotheque
d'interface" (`aucunJavaScriptDeBibliothequeDInterfaceNEntreDansLeDepot`) : une bibliotheque de
comportement d'interface (menus, modales - exactement ce que la Javadoc de la classe dit vouloir
interdire) entre dans le depot, correctement tracee, sans qu'aucun test ne le remarque, pour la
seule raison qu'elle ne s'appelle pas "bootstrap". La tracabilite (obligation d'ADR-0014) est
intacte ; l'interdiction de principe (ADR-0008, le comportement de jeu reste sur le serveur) est
silencieusement vide sur tout nom nouveau.

**Une troisieme candidate cherchee et distinguee, pas confirmee comme la meme faute** :
`AucunVocabulaireDeMatiereTest` (domaine, ADR-0004/ADR-0024). Perimetre derive (balayage de
`domain/mechanics` et du paquet racine de `domain/content`) ; critere : une liste de vocabulaire de
matiere (`addition`, `syllabe`, `conjugaison`, `graphie`...). Son propre commentaire nomme
explicitement le risque - *"son mode de defaillance est un mot oublie, donc un vert incomplet -
jamais un objet non juge"* - et je ne l'ai pas trouve faux en le confrontant a la difference qui
compte : chaque mot de cette liste est une **categorie de vocabulaire** qui generalise a une
matiere entiere (n'importe quel fichier futur qui emploie "addition" serait attrape), alors que
"bootstrap" et les cinq fautes de `TexteAccentueDesGabaritsTest` (`"ca.", "reponse etait", "la
partie est terminee", "d'avoir joue", "Récréo"`) sont chacun le nom d'un **incident date et clos**,
incapables par construction de dire quoi que ce soit d'un contenu qui n'a pas encore commis
exactement la meme faute. Je n'ai pas rejoue de mutation dessus (elle sort du perimetre de ma
saisine et le risque qu'elle nomme elle-meme est deja le bon), mais je ne la classe pas dans la
meme famille sans l'avoir distinguee par cette raison ecrite, plutot que par ressemblance de forme.

**Reponse a la question posee** : oui, il existe une facon partiellement systematique de chercher
cette classe, et elle vient de trouver deux instances de plus en un seul geste. Le grep sur
l'auto-declaration exigee par ADR-0013 ("critere enumere, jamais un perimetre") **enumere lui-meme
tous les candidats du depot** - c'est un fait mecanique, verifiable par n'importe qui. Ce que ce
grep ne tranche pas mecaniquement, c'est **si les membres de la liste generalisent ou nomment
chacun un incident clos** : cette distinction reste une lecture, pas un motif syntaxique. Je
propose donc a `sofia-sm` d'ajouter au reflexe de cloture : a chaque `critere enumere`
auto-declare, verifier si un membre au moins de la liste est le nom d'un incident date plutot
qu'une categorie - et si oui, muter un objet neuf et absent de la liste pour voir si le garde-fou
peut seulement dire quelque chose de lui. Ce n'est pas un lint qui peut se fermer seul, mais c'est
un point de controle qui se cherche en une commande.

## 4. Les quatre verifications demandees

### 4.1 - Le retrait de la clause de concordance d'ADR-0038 ne laisse pas un trou de plus que celui nomme

**Le sens destructeur -> sortie reste garde**, et je l'ai casse pour le verifier : mutation 1 de la
section 2 (renommer `data-role="sortie"` en `"action"` sur le bouton reel de
`fragments/confirmation.html` qui appelle `/retour/confirmer`) fait rougir
`PartieControllerTest.leBoutonDeRetourDemandeConfirmationSansClorePartieEnCours`, exactement le
test que l'amendement du 2026-08-14 cite. Ce n'est pas une derivation generale (elle ne porte que
sur cet unique ecran), et c'est exactement ce que l'ADR dit d'elle-meme.

**La "dette nommee" (une deuxieme requete destructrice, declaree `action`, passerait verte)
tient**, verifiee par lecture du controleur et non supposee : `PartieController.java` ne contient
qu'un seul appel a `EtatDePartieEnSession.abandonner()` sur une partie reellement en cours - celui
de `retour/confirmer` (ligne 128). L'appel de la ligne 119, dans `retour()`, ne s'execute que
`sans` partie en cours (`if (etat.portePartieEnCours()) { return confirmation; }` avant d'atteindre
cette ligne), donc sur une session deja vide : rien a y detruire. Il n'existe aujourd'hui aucune
deuxieme requete destructrice a confondre le test avec.

**Le declencheur d'encodage (la deuxieme requete destructrice de l'application) est coherent**,
verifie par lecture de `LActionNeDeclarePasMoinsQueLaSortieTest` (ADR-0017) : sa clause de
non-vacuite est bien globale -
`if (couples.isEmpty()) { defauts.add("aucun couple action/sortie n'a ete confronte...") }` -
calculee sur la liste `couples` qui aplatit **tous** les ecrans, jamais par ecran. Un couple
confronte n'importe ou dans l'application suffit a fermer cette clause pour toute l'application,
confirmant exactement le mecanisme de desamorcage que l'ADR decrit ("un ecran qui n'en declare plus
aucune la laisse verte tant qu'un autre ecran en fournit un"). Le trou est donc reel, nomme
honnetement, et sa taille (une deuxieme requete destructrice mal etiquetee, sur n'importe quel
ecran) correspond a ce que le texte dit - ni plus etroit ni plus large.

### 4.2 - Les quatre non-preuves declarees "encore vraies" (1, 2, 6, 7) tiennent contre l'implementation reelle

- **1** (le focus va la ou le serveur le declare, jamais verifie par la clause 2) : confirme par
  lecture de `RecreoJsDeplaceLeFocusTest`, dont la Javadoc porte mot pour mot la meme phrase que
  l'ADR cite ("la clause 1 de M13 reste verifiee au clavier, dans un vrai navigateur, hors de la
  CI") et dont le DOM execute est un faux ecrit a la main (`FakeElement`, `FakeDocument`), jamais un
  navigateur.
- **2** (qu'un vrai navigateur reboucle comme la regle le calcule) : confirme structurellement -
  aucune dependance Selenium/Playwright/WebDriver dans aucun `pom.xml` du depot, aucun outillage de
  navigateur pilote dans la CI (`ADR-0025` : la mesure de rendu vit hors CI, dans une commande
  versionnee). Rien dans ce depot ne peut affirmer ce que fait un navigateur reel.
- **6** (que l'enfant s'en sort) : trivialement vraie - aucun test de ce depot ne pretend juger la
  lisibilite ou l'acceptabilite pedagogique d'un ecran ; c'est `nadia-prof` et `ines-design`.
- **7** (que M13 clause 1 est tenue) : confirme par lecture de `DEC-0026` - le declencheur de
  reouverture exige toujours "la reverification... au protocole exact de `nadia-prof` : au clavier
  reel", sur les deux ecrans depuis l'amendement du 2026-08-14. Rien dans `ADR-0038` ni dans le
  code livre ne pretend remplacer cette reverification ; le vert de la CI est une condition
  necessaire de plus, jamais suffisante.

Aucune des quatre n'est tombee a la lecture ni a la mesure.

### 4.3 - Le rejeu de `AucunArretDeTabulationNEstDestructeurTest` ne laisse pas d'etat et ne devient pas vert a vide

**Pas d'etat partage entre rejeux** : verifie par lecture d'`EtatDePartieEnSession` (le seul type
du depot qui touche la session HTTP, ADR-0019) - tout l'etat de partie vit dans des attributs de
`HttpSession`, jamais dans un champ statique ni un registre partage. `estDestructeur()` cree une
`new MockHttpSession()` a chaque arret juge, `PartieService` (application) est un service sans
etat mutable (il ne fait que calculer une session suivante et la rendre a l'appelant), et
`ecrans()` est une methode, jamais un champ mis en cache : chaque `@Test` de la classe l'appelle
independamment. Aucune session, aucun etat ne survit d'un rejeu au suivant ni d'un test au suivant.

**Ne devient pas vert si la population s'evide** : verifie par mutation reelle (section 2, ligne
2). Vider `ecrans()` fait rougir 5 des 6 tests de la classe, dont les deux non-vacuites de clause
qui existent precisement pour cela ("cette clause ne prouve rien si aucun ecran de ce corpus ne
porte une partie en cours" / "... ne declare de point de focus..."). Ce garde-fou n'est pas un
cliquet decoratif : je l'ai casse pour le voir rougir, pas seulement lu son intention.

### 4.4 - Le test de texte de lea-dev garde la phrase entiere, pas une position

`TexteAccentueDesGabaritsTest.leTexteDeConfirmationEstCeluiArbitreParNadiaProf` compare
`fragment.select(".question-confirmation").text()` a la phrase arbitree **entiere** par
`isEqualTo`, pas une sous-chaine ni un mot : la mutation 3 de la section 2 (un seul mot change,
"s'arrête" -> "continue") suffit a la faire rougir, ce qui montre qu'elle garde bien le texte
complet et non un fragment de lui.

**Elle ne garde pas la position.** Mutation 4 de la section 2 : la meme phrase, texte et classe
inchanges, deplacee du debut du fragment vers la fin (apres les deux boutons) - le test reste
**vert**. Le selecteur est un selecteur de classe CSS (`.question-confirmation`), independant de
l'ordre du document ; rien dans ce test n'affirme quoi que ce soit sur la position de la phrase.
Ce n'est pas un defaut au regard de ce que le test annonce (garder le texte arbitre, pas sa place),
mais c'est une limite reelle qui merite d'etre dite plutot que supposee : si un lot deplacait cette
phrase apres les boutons de confirmation - une regression d'ordre de lecture, potentiellement
pertinente pour `nadia-prof` ou `ines-design` - ce test-la ne le verrait pas.

## 5. Ce que ce rapport ne couvre pas

- Aucun Done pedagogique : il n'est prononce que par `nadia-prof`. Ce rapport ne prononce, ne
  simule et ne remplace aucun de ses verdicts.
- Aucune tracabilite critere par critere des stories du sprint : les fiches de conformite et les
  `DEC`/`ADR` du sprint portent deja cette tracabilite cote produit.
- Aucun jugement sur la forme de l'ecran de confirmation, son texte au sens pedagogique, ou
  l'accessibilite au lecteur d'ecran : `ines-design` et `nadia-prof`.

## Conclusion

**Vert, constate par moi-meme, sur `round-two` a `1421d46`** : `mvn -B clean verify`, BUILD
SUCCESS, **416 tests** (chiffre autoritaire, etabli par l'agregat Maven et par la somme des
attributs XML - les fichiers `.txt` de surefire/failsafe sous-comptent de 7 sur quatre classes qui
melangent un champ `@ArchTest` et une methode `@Test`, jamais une classe parametree), **28 regles
ArchUnit**, garde-fou de chemins en code 0. Cinq mutations sur du materiau de production reel,
toutes restaurees, arbre propre verifie a chaque etape. Le retrait de la clause de concordance
d'`ADR-0038` ne laisse pas de trou au-dela de celui que `theo-archi` nomme lui-meme, et je l'ai
verifie par mutation plutot que par lecture. Deux instances supplementaires de la classe de defaut
"perimetre derive, critere enumere sur des incidents clos" trouvees et l'une confirmee par
mutation (`RessourcesTiercesTest`), une troisieme candidate cherchee et distinguee par une raison
ecrite plutot que rejetee par ressemblance. Rien de tout cela ne change le Done pedagogique, qui
reste la seule autorite de `nadia-prof`.
