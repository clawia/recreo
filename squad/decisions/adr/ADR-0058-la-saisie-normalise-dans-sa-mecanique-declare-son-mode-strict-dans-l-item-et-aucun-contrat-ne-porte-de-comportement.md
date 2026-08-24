# ADR-0058 - La saisie normalise dans sa propre mecanique, declare son mode strict dans l'item, et aucun contrat ne porte de comportement

- **Statut** : Accepte pour ses clauses de conception ; **une seule de ses regles est ecrite et verte
  aujourd'hui** (clause 8 de `DecouplageMecaniqueContenuTest`, ses deux morsures dans
  `LesReglesMordentTest`), les deux autres sont **ecrites ici et dues avec le lot de code**, chacune
  avec la mesure qui dit qu'elle serait rouge aujourd'hui. La section "Regles executables" dit
  laquelle est laquelle, plutot que de laisser le silence trancher.
- **Sprint** : s19. **Date** : 2026-08-24. **Auteur** : theo-archi.
- **Arbre mesure** : worktree `s19-theo`, branche `round-two` a `c3d3d47` (SHA releve sur la branche).
  Toutes les mesures de cet ADR sont faites sur cet arbre, sur le JDK 21.0.10 de la toolchain
  declaree, par `bash outils/mvn-jdk21.sh` (racine du reacteur sauf mention contraire).
- **Route par** : `nadia-prof`,
  `squad/conformite/ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md`, section 8 - *"ou vivent SAI-1,
  SAI-3 et SAI-4"* et *"SAI-9 suppose que le serveur connaisse la chaine que l'enfant a envoyee"*.
- **Prolonge** : `ADR-0004` (deux contrats fermes), `ADR-0006` (le contrat de contenu ne porte que du
  texte), `ADR-0008` (le verdict est calcule cote serveur), `ADR-0039` (une propriete calculee plutot
  qu'une enumeration), `ADR-0049` (le repliage Unicode n'est pas une regle de francais),
  `ADR-0057` (la focalisabilite est declaree par le gabarit).
- **Ne prononce aucun Done, ne code aucune mecanique.** Ce sprint n'affecte aucune capacite de
  developpement a la saisie ; cet ADR existe pour qu'un sprint ulterieur ouvre des stories sans
  rouvrir la conception.

## Contexte

J'ai pose le probleme moi-meme a s13 (`squad/sprints/s13-grands-nombres-et-chemin-de-catalogue/
avis-ordre-de-grandeur-seconde-mecanique.md`) : des trois familles que `camille-po` classait par ce
dont l'enfant a besoin, **seule la saisie est une seconde mecanique** - le visuel et l'audio sont des
formes d'item que `BanditManchot` sert sans changement. J'y ecrivais que le verdict cesse d'etre une
egalite de chaines, que `uneMecaniqueIgnoreLaMatiere` interdit qu'une mecanique connaisse
l'orthographe francaise, et que le chemin etait *"le paquet porte l'ensemble des formes acceptees, la
mecanique se borne a une normalisation qu'elle possede"*.

**Je maintiens ce chemin, et je corrige un chiffre de cet avis-la.** Sa table disait, pour la saisie :
`adapter-web` = *"rendu + un `<input>`"*. C'est faux, et la mesure le dit : `adapter-web` **type son
etat de partie sur la mecanique existante**. Balayage des sources de production du module,
`adapters/adapter-web/src/main/java`, comptage des occurrences de la chaine `BanditManchot`, imports
et Javadoc compris - **20 occurrences sur 19 lignes, dans 3 fichiers** : `PartieController` 12,
`EtatDePartieEnSession` 6, `EtatDePartie` 2. Le port entrant
rend un `GameSession`, et l'adaptateur le **descend par transtypage** vers `BanditManchotSession` des
la ligne 236, puis stocke ce type concret dans l'attribut de session HTTP. Une seconde mecanique n'est
donc pas "un champ de plus" : c'est le retypage de l'etat de partie de l'adaptateur. C'est le vrai
cout, il est borne, et il est chiffre ici plutot que decouvert par un lot.

`nadia-prof` a rendu le 2026-08-24 l'arbitrage pedagogique qui manquait. Ses onze conditions
`SAI-1` a `SAI-11`, ses cinq refus `R1` a `R5` et ses cinq seuils `SEUIL-1` a `SEUIL-5` font foi et
vivent dans son fichier ; **cet ADR ne les recopie pas et ne les negocie pas**. Il decide par quel
chemin l'architecture les tient, et nomme celle qu'elle ne peut pas tenir telle qu'elle est ecrite
(section "Ce que je route").

## Decision

### 1. Une forme d'item de plus, dans le contrat de contenu, qui ne nomme aucune matiere

`Item` est une interface scellee `permits Question` depuis le sprint 0, avec en Javadoc *"une
deuxieme permission ne sera ajoutee que par la mecanique qui en aura besoin"* (`ADR-0004`). C'est
cette mecanique-la. `Item permits Question, ReponseAEcrire`.

`ReponseAEcrire` ne porte **que du texte** (`ADR-0006`), et ses composantes sont :

- l'enonce ;
- la **forme de reference**, celle que l'enfant doit revoir integralement accentuee et sans faute
  (`SAI-8`, `SAI-9`) ;
- les **formes acceptees**, une liste de chaines, ecrite ou relue par `nadia-prof` (`SAI-6`,
  `SAI-7`) ;
- l'indice et l'explication, optionnels, comme sur `Question` ;
- **un booleen `formeExacteExigee`**, qui est l'interrupteur de `SAI-2`.

**Pourquoi pas des champs de plus sur `Question`** : `Question` est traverse par toutes les matieres
et par tous les jeux du catalogue. Y poser des formes acceptees obligerait treize paquets de choix a
porter une cle qui ne les concerne pas - la dette 2 de la version precedente, mot pour mot, et
c'est ce qu'`ADR-0024` a deja tranche pour l'explication.

**Pourquoi un booleen et pas un enum a deux constantes** : `nadia-prof` nomme deux etats et deux
seulement, et un enum de deux constantes est une piece mobile de plus sans usage constate. *Ce qui me
ferait changer d'avis* : un troisieme mode reellement demande par un paquet, jamais imagine par moi.

### 2. Le mode strict est declare par le contenu, jamais devine par le code

`formeExacteExigee` vit **sur l'item**, et c'est la seule chose que le domaine connait. La commodite
que `SAI-2` demande - *"au niveau du paquet si le paquet entier est concerne, au niveau de l'item si
le paquet melange les deux cas"* - est **un fait de schema**, pas un fait de domaine : la fabrique de
`adapter-content` lit une cle facultative au niveau du paquet, une cle facultative au niveau de
l'item, et remplit le booleen de chaque item. C'est exactement le mecanisme deja en service pour
`indicesNommentLaReponse` (cle facultative du paquet, lue par `QuestionContentSourceFactory`), donc
un patron constate et non invente.

**Ce que cela interdit, et c'est le point de `SAI-2`** : le code ne deduit jamais le mode de la
matiere ni du niveau. Il ne le peut deja pas - `uneMecaniqueIgnoreLaMatiere` (clause 4 d'`ADR-0004`)
refuse a toute mecanique de dependre de `ContentDescriptor`, et sa morsure existe depuis s00. Un
`if (matiere.equals("Francais"))` dans une mecanique est rouge aujourd'hui.

**Ce que le nom du champ change.** `AucunVocabulaireDeMatiereTest` refuse un mot de matiere dans le
paquet racine `domain/content` ; sa liste porte deja **`graphie`**, c'est-a-dire le nom le plus
tentant pour ce champ. `graphieSignificative` est donc rouge aujourd'hui, sans qu'aucune regle neuve
ne soit ecrite. `accentSignificatif` passerait, et **je refuse d'ajouter `accent` a la liste** :
`accent` est la sous-chaine d'`accentue`, mot que n'importe quelle Javadoc de ce paquet peut employer
a bon droit, et une regle qui rougit sur un mot etranger a son objet est la faute mesuree en s05.
Declencheur ecrit d'avance, a la place : **le lot qui ouvre la saisie decide de l'entree d'`accent`
dans cette liste, en la mesurant sur ses propres sources**, jamais par avance sur des sources qui
n'existent pas.

### 3. La normalisation vit dans le paquet de la mecanique de saisie, et elle est une propriete calculee

`SAI-1` (accents et cedilles), `SAI-3` (casse) et `SAI-4` (blancs) sont tenus par **une normalisation
que la mecanique possede**, dans `com.recreo.domain.mechanics.saisie`, et par rien d'autre. Elle ne
depend que du JDK.

**Elle ne connait pas le francais, et ce n'est pas une affirmation de ma part : c'est deja juge dans
ce depot.** Le repliage de `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` fait exactement ces
trois gestes - decomposition NFD, retrait des `NON_SPACING_MARK`, comparaison sans casse, absorption
des blancs y compris l'insecable - et sa Javadoc, ratifiee par `ADR-0049`, ecrit : *"Ce n'est pas une
normalisation du francais, c'est la seule facon de dire que ces deux chaines nomment la meme matiere
sans tenir de table de correspondance."* `ADR-0049` le redit dans les termes d'`ADR-0039` : *"le
repliage ne connait aucune liste de mots [...] donc une matiere neuve entre dans le controle sans que
ce fichier change - une propriete calculee et non une enumeration."* La meme operation, sur la meme
pile, pour le meme motif.

Trois precisions qui font partie de la decision :

- **`Locale.ROOT`, jamais `Locale.FRENCH`.** Un repliage de casse dependant d'une langue est une regle
  de langue deguisee en appel de bibliotheque. Aucune regle ArchUnit ne distingue les deux
  aujourd'hui (les deux constantes sont dans `java..`, autorise par la clause 3 d'`ADR-0004`) ; c'est
  donc une clause de relecture, et je la nomme comme telle plutot que d'inventer une regle
  decorative.
- **Le blanc insecable compte comme un blanc.** `Character.isWhitespace` **ne** le compte pas, par
  definition ; c'est exactement le blanc qu'un copier-coller depose, et l'amendement d'`ADR-0049`
  l'a mesure sur du materiau reel. Le predicat est `isWhitespace || isSpaceChar`.
- **La normalisation n'est pas partagee avec `bandit-manchot`.** Si celle-ci en veut une un jour, elle
  ecrira la sienne. Trois lignes semblables valent mieux qu'une abstraction prematuree, et surtout :
  le lieu ou l'on serait tente de la partager est un contrat, ce que la clause 4 ci-dessous rend
  desormais rouge.

### 4. Aucun contrat ne porte de comportement, pas meme sous forme d'interface

C'est la clause de fond de cet ADR, et c'est la seule dont la regle est ecrite et verte aujourd'hui.

`ADR-0004` ferme les deux contrats (clauses 1 et 2) contre une **classe** porteuse de comportement -
c'etait la forme du `SessionEngine` de la version precedente, et c'est la forme des deux
contre-exemples de s00. **Une interface dont une methode est statique, par defaut ou privee loge
exactement le meme moteur partage et restait verte.**

**Mesure du 2026-08-24, pas lecture.** Deux sondes posees dans l'arbre : une normalisation de saisie
en methode **statique** d'interface dans `com.recreo.domain.mechanic`, un normalisateur d'enonce en
methode **par defaut** d'interface dans `com.recreo.domain.content`.
`bash outils/mvn-jdk21.sh -B -o -pl bootstrap -am test` - **615 tests, 0 echec, BUILD SUCCESS**. Les
deux contrats fermes ne voyaient rien. Le `-am` est porte parce que la mesure est partielle
(`CLAUDE.md`, prerequis de build) ; 615 et non 621 parce que la phase `test` n'execute pas les 6 tests
d'integration de `failsafe`.

**Pourquoi cette clause n'est pas decorative, et le test est celui que je m'impose** : sans elle, la
clause 7 d'`ADR-0004` - *"deux mecaniques ne dependent pas l'une de l'autre"* - ne prouve rien. Deux
mecaniques peuvent etre deux coquilles qui appellent le meme moteur hisse dans un contrat : elles ne
dependraient pas l'une de l'autre, et tout le comportement vivrait au meme endroit. Le geste est
concret et date d'aujourd'hui : la saisie a besoin d'une normalisation, la mecanique existante compare
des chaines, et un contrat commun est le lieu le moins cher pour la poser.

**Consequence de production, assumee dans le meme commit.** La regle a rougi immediatement sur un
defaut reel : `ContentSource.indicesNommentLaReponse()` etait `default` et renvoyait `false`. Elle
devient **abstraite**. Ce n'est pas un changement de la convention de `M21` - *"faux tant que le
paquet ne declare pas le contraire"* est implemente par `QuestionContentSourceFactory`, qui lit la
cle facultative du YAML, **jamais par ce defaut**. Le defaut ne dispensait qu'une implementation de
decider, ce qui est la forme exacte du constructeur de commodite par lequel le defaut d'`ADR-0016`
etait entre (Javadoc de `Question`). Cout : 6 fichiers, une ligne chacun - `ContentSource`,
`AdditionsBorneesContentSource`, et les quatre implementations anonymes des tests.

*Ce qui me ferait changer d'avis* : le jour ou une interface de contrat aurait un besoin reellement
constate d'une methode par defaut, la clause gagnerait une exemption **nommee, datee, plafonnee a la
taille exacte de la dette** et adossee a un test qui verifie qu'elle designe encore un comportement
reel - le patron de `DETTE_DE_BALAYAGE`. Je ne l'ouvre pas d'avance : un cliquet dont le plafond
depasse la dette n'est pas un cliquet.

### 5. `Move` et `GameSession` ne bougent pas

- `Move` reste un marqueur vide. La saisie definit `SaisieMove(String texteEcrit)` **dans son propre
  paquet**, comme `BanditManchotMove` dans le sien. La clause 6 d'`ADR-0004` l'y oblige deja.
- `GameSession` reste `status()` + `score()`, et **c'est une decision, pas un constat**. L'elargir
  pour servir le rendu - `enonce()`, `propositions()`, `derniereTentative()` - donnerait a chaque
  mecanique des methodes que l'autre remplirait de valeurs vides : la dette 2 de la version
  precedente sur le contrat de mecanique au lieu du contrat de contenu. Chaque mecanique expose ce
  qu'elle a sur **son** type de session, comme `BanditManchotSession` le fait aujourd'hui.

### 6. `SAI-9` ne coute aucun mecanisme neuf, et c'est mesure sur le code existant

`nadia-prof` route `SAI-9` comme sa condition la plus dure : *"au moment du verdict, l'enfant doit
revoir ce qu'il a ecrit"*, ce qui suppose que le serveur connaisse la chaine envoyee.

**Le serveur la connait deja, et il la garde deja.** `BanditManchotSession` porte
`Optional<String> derniereTentative`, expose par `derniereTentative()`, alimente a chaque echec et
remis a vide a chaque manche neuve. Il existe pour `M13` - placer le point de focus sur le bouton que
l'enfant vient de presser. La saisie garde le meme champ, avec la meme duree de vie, pour la meme
raison plus une : le rendre a l'ecran. C'est une composante de plus sur un type de session que la
mecanique possede, zero ligne dans un contrat, zero port touche.

**Et la propriete que `nadia-prof` dit detruite ne l'est qu'a moitie.** Ce que `M9` donne aujourd'hui,
c'est que l'enfant voit **toutes** ses tentatives, marquees, jusqu'a la resolution. Un champ de saisie
n'en garde qu'une. Architecturalement, garder les trois est le meme geste que garder une - la session
porte deja `Set<String> propositionsEssayees` a cote de `derniereTentative`. **Ce n'est pas une
decision d'architecture** : trois chaines a l'ecran plutot qu'une est un fait de rendu et de lecture,
donc `ines-design` propose et `nadia-prof` tranche. Je note seulement que le cout de l'une et de
l'autre est identique, pour que le choix ne se fasse pas sur un cout suppose.

### 7. Ce que la saisie fait a l'ecran, a la tabulation et a la sortie clavier

Ce depot a paye trois sprints de dette sur cette famille (`ADR-0057`, `S17-C`, `M13`), et un champ de
saisie est un arret de tabulation de plus. Trois faits mesures sur l'arbre, pas supposes :

1. **La tabulation est deja couverte, sans changer le garde-fou.**
   `AucunArretDeTabulationNEstDestructeurTest` derive ses candidats du document servi par le selecteur
   `a[href], button, input, select, textarea, [tabindex]` (ligne 359) et sa liste de balises
   naturellement focalisables porte `input` (ligne 102). Un `<input>` entre donc dans son perimetre
   par construction. `ADR-0057` clause 2 est satisfaite sans `tabindex` : un `<input>` est focalisable
   nativement dans le document servi.
2. **Les deux planchers de lisibilite et de cible tactile, eux, ne le voient pas.**
   `TailleDesTextesEtBoutonsTest` derive ses pressables par `document.select("button, a[href]")`
   (ligne 396). **Un `<input>` n'y est pas.** Sa taille de texte ne serait confrontee a aucun plancher
   de 1,25 rem, sa cible tactile a aucun plancher de 44 px, et la CI serait verte. C'est mot pour mot
   le mode de defaillance qu'un perimetre enumere par types produit - **par omission cette fois** -
   et c'est celui que ce depot a deja paye deux sprints. **Le lot qui ouvre la saisie etend ce
   selecteur dans le meme commit**, avec sa morsure : un `<input>` sous le plancher doit faire rougir.
3. **`ADR-0008` n'est pas sollicite, et il est mieux servi qu'aujourd'hui.** Un `<input>` dans un
   formulaire htmx est du HTML ; le serveur recoit une chaine et rend un verdict. La bonne reponse
   **ne part plus** dans la page, alors qu'aujourd'hui elle y est, melangee aux distracteurs.

Le rendu de `SAI-8` et `SAI-9` se pose dans le bloc `.message-jeu` de `fragments/manche.html`, qui
porte deja `.verdict`, `.explication` et `.indice` sous le meuble. Un texte de plus a cet endroit est
un objet a dessiner pour `ines-design`, sous le veto de `nadia-prof` ; ce n'est pas une decision
d'architecture et je ne la prends pas.

### 8. Ce que la saisie ne fera pas

- **L'operation posee - multiplication posee, division euclidienne - n'est pas de la saisie.** Je
  reconduis sans le modifier ce que j'ai ecrit a s13 : ce n'est pas un champ, c'est une grille de
  champs, des retenues, un ordre de remplissage et un verdict partiel case par case. C'est une
  **troisieme** mecanique. `nadia-prof` le confirme de son cote le 2026-08-24, pour un motif qui n'est
  pas le mien - le geste lui-meme est la competence, et il se fait sur du papier en colonnes. **Ces
  deux lignes ne doivent jamais etre comptees avec la saisie dans un arbitrage de sequencement.**
- **Aucun appariement approximatif, nulle part** (`SAI-5`). Voir la section suivante pour ce qui le
  garde et ce qui ne le garde pas.
- **Aucun verdict partiel, aucun troisieme etat.** `SAI-5` et le verdict binaire de `SAI-8`/`SAI-9`
  se rejoignent ici : le domaine ne portera jamais un type de verdict a trois valeurs ni un score
  fractionnaire. C'est ce qui rend `R2` et `R3` opposables autrement que par la vigilance : une
  reponse libre sur un texte demanderait exactement cela.

## Regles executables

### Ecrite et verte aujourd'hui, dans le meme commit que cet ADR

**Fichier** : `bootstrap/src/test/java/com/recreo/archi/DecouplageMecaniqueContenuTest.java`, clause
(8). **Morsures** : `bootstrap/src/test/java/com/recreo/archi/LesReglesMordentTest.java`, deux tests.
**Jeux fautifs** : `bootstrap/src/test/java/com/recreo/domain/mechanic/NormalisationPartageeContreExemple.java`
et `bootstrap/src/test/java/com/recreo/domain/content/EnonceNormaliseContreExemple.java`.

> **Une interface de contrat ne declare que des methodes abstraites.**

```java
@ArchTest
static final ArchRule unContratNeDeclareAucunComportement = methods()
        .that().areDeclaredInClassesThat().resideInAnyPackage(CONTRAT_CONTENU, CONTRAT_MECANIQUE)
        .and().areDeclaredInClassesThat().areInterfaces()
        .should().haveModifier(JavaModifier.ABSTRACT)
        .allowEmptyShould(false)
        .as("ADR-0058 : une interface de contrat ne declare que des methodes abstraites - ni"
                + " statique, ni par defaut, ni privee. Un contrat qui porte du comportement est"
                + " un moteur partage, quelle que soit la forme du type qui le porte");
```

- **Ce qu'elle interdit reellement** : le partage de la normalisation de saisie entre deux mecaniques
  par un contrat, c'est-a-dire le seul chemin qui rendrait la clause 7 d'`ADR-0004` verte a vide.
- **Perimetre** : les methodes declarees par une **interface** des deux paquets de contrat. Les
  records et les enums en sont hors, et ce n'est pas une exemption concedee : leurs accesseurs et
  leurs `values()`/`valueOf()` sont produits par le compilateur, jamais ecrits, et les refuser ferait
  rougir `Score` et `SessionStatus` sans qu'aucun comportement n'ait ete partage.
- **Non-vacuite** (`ADR-0003`) : `allowEmptyShould(false)` - si les deux paquets ne declaraient plus
  aucune methode d'interface, la clause echouerait au lieu de passer verte a vide.
- **Deux morsures, une par forme et une par contrat** (`ADR-0013` obligation 3) : la methode
  **statique** dans le contrat de mecanique, la methode **par defaut** dans le contrat de contenu. Un
  predicat qui n'aurait refuse que `static` laisserait passer la seconde, et reciproquement - c'est la
  moitie de comparaison gardee qu'`ADR-0055` a sanctionnee. Chaque morsure verifie en outre, dans le
  meme test, que son jeu fautif **passe** la clause d'`ADR-0004` qu'il contourne : c'est cette
  assertion-la qui prouve que le trou existait.
- **Le jeu fautif n'est pas une fixture imaginee** : `NormalisationPartageeContreExemple.replier` est
  la normalisation que la section 3 de cet ADR decrit, ecrite comme le chemin le moins cher l'ecrirait.

**Mesure, methode comprise** : `bash outils/mvn-jdk21.sh -B clean verify` a la racine du reacteur -
**624 tests, 0 echec, BUILD SUCCESS** (48 `domain` + 13 `application` + 321 `adapter-web` +
144 `adapter-content` + 92 `bootstrap` surefire + 6 `failsafe`). Reference avant ce lot, meme
commande, meme arbre : **621, 0 echec**. *Correction du 2026-08-24, meme jour : cette parenthese
donnait "321 `adapter-content` + 144 `adapter-web`", les deux etiquettes echangees. Ecart trouve par
`sofia-sm` a la relecture de ce lot, verse au dossier de `yanis-qa` plutot que corrige dans un
fichier qui n'est pas le sien, et corrige ici par `theo-archi` qui l'avait ecrit. Verifie par le
comptage des `<testsuite tests="...">` des rapports XML de surefire et de failsafe : `adapter-web`
porte 321 tests sur 42 classes, `adapter-content` 144 sur 23. Le total de 624 etait juste - c'est un
defaut d'etiquette, pas de mesure, et c'est exactement le genre de chiffre qu'un lecteur reprendrait
sans le recompter.* Les trois tests de plus sont la clause et ses deux morsures. Pas de
`-am` a citer : le reacteur est complet par construction.

**Compte des regles ArchUnit, avec sa methode.** Balayage des sources `.java` de l'arbre du worktree,
`target/` et `.git/` exclus, comptage des champs `ArchRule` : **32 apres ce lot, 31 avant**, dont **2**
vivent dans le faux depot de contre-exemple de `LesGardeFousSontMisALEpreuveTest` et ne s'executent
jamais comme regles. Soit **30 regles reelles apres ce lot, 29 avant** - la clause de cet ADR est donc
la **30e**. Sur ces 30, **29** portent `@ArchTest` ; la trentieme (`ScanDeComposantsTest`) est
verifiee par un `@Test` qui appelle `check()` sur son champ. C'est ce qui rendait "29" inverifiable
dans `ADR-0044` a `ADR-0046` : ce 29-la designait les champs `ArchRule` reels, tandis qu'un comptage
des seuls `@ArchTest` sur le meme arbre en rendait 28. Les deux etaient exacts, ils ne comptaient pas
la meme chose, et aucun des deux ne se lit sans sa methode. **Troisieme piege, observe dans le
journal de ce lot** : `LesReglesMordentTest` est execute par **deux** moteurs JUnit (1 test par le
moteur ArchUnit, 28 par Jupiter) et le rapport texte de surefire n'en garde qu'un - un comptage lu
dans `surefire-reports` sous-compte donc, la ou le journal Maven porte les deux lignes.

### Ecrites ici, dues avec le lot de code, et rouges aujourd'hui

Ces deux-la ne sont pas ecrites dans le depot, et je dis pourquoi plutot que de laisser le silence
trancher : elles jugent des objets qui n'existent pas encore, ou elles sont rouges sur l'arbre
d'aujourd'hui pour un defaut que ce sprint n'a pas la capacite de corriger.

**R-A - `adapter-web` ne nomme aucune mecanique hors de son point de descente.**

> Aucun type de `com.recreo.adapter.web` ne cite un type de `com.recreo.domain.mechanics..`, hormis
> le point de descente nomme de chaque mecanique.

Elle est **rouge aujourd'hui** : 20 occurrences dans 3 fichiers (mesure de la section Contexte).
Elle est due dans le lot qui ouvre la seconde mecanique, parce que c'est ce lot qui doit retyper
`EtatDePartie` et `EtatDePartieEnSession` sur le contrat `GameSession`. L'ecrire aujourd'hui, verte,
demanderait ce retypage - donc du code de production que ce sprint n'affecte pas. **Ce n'est pas une
regle differee sans declencheur** : son declencheur est le premier commit de la seconde mecanique, et
sans elle la clause 7 d'`ADR-0004` serait respectee dans le domaine et defaite dans l'adaptateur.

**R-B - aucune distance entre chaines dans une mecanique** (`SAI-5`).

La moitie "bibliotheque tierce" est **deja gardee** : la clause 3 d'`ADR-0004`
(`uneMecaniqueNeVoitQueLesContrats`) interdit a une mecanique toute dependance hors du JDK et des deux
contrats, donc aucune bibliotheque de similarite n'y entrera jamais. **La moitie "distance ecrite a la
main avec `java.lang.String` seul" n'est pas encodable en ArchUnit** : ArchUnit voit les types, et une
boucle de comptage de caracteres n'en cite aucun de particulier. Le mecanisme automatique qui la garde
est donc **un test unitaire de la mecanique**, du avec son code, portant une table de couples que
`nadia-prof` a nommes - `300`/`200`, `47`/`42`, `chevaux`/`cheveux`, `pere`/`perte`, `sept`/`cept` -
et exigeant le verdict **faux** sur chacun. Je le nomme ici pour qu'il soit un livrable de la story et
non une bonne intention, et parce que `SAI-5` est la condition sur laquelle elle a ecrit qu'elle
refuserait un increment sans discuter.

### Ce qu'aucun automate ne gardera, et je le dis avant le reste

`SAI-6` et `SAI-7` - le **contenu** des listes de formes acceptees - ne sont gardes par rien. Aucun
test ne peut dire qu'une forme manque a la liste d'un mot, ni qu'une forme n'a rien a y faire ; il
faudrait un dictionnaire, et aucun n'entre dans ce depot (`ADR-0039`, `ADR-0049`). `nadia-prof` ecrit
que c'est le point le plus dangereux de cette mecanique, et je le confirme du cote technique : **un
bareme faux passe une CI verte sans laisser de trace.** La consequence architecturale, elle, est
ecrivable et je l'ecris : puisque la liste est le bareme, elle vit dans la **donnee** (le paquet) et
jamais dans le code, ou aucune relecture pedagogique ne l'atteindrait.

## Consequences

- **Un sprint ulterieur peut ouvrir des stories de saisie sans rouvrir la conception.** Ce qui reste
  a decider par un lot est de l'implementation, pas de l'architecture.
- **La clause 7 d'`ADR-0004` cesse d'etre potentiellement decorative.** Elle etait vraie et
  contournable ; elle est desormais vraie et gardee des deux cotes.
- **Le contrat de contenu perd une commodite.** Toute implementation de `ContentSource` doit
  desormais declarer `indicesNommentLaReponse()`. C'est une ligne de plus par implementation, et
  c'est le prix a payer pour qu'un contrat ne porte pas de comportement. Mauvaise consequence assumee.
- **Le cout de la seconde mecanique est chiffre et il n'est pas celui que s13 annoncait** : le
  retypage de l'etat de partie de `adapter-web` (3 fichiers) s'ajoute a la forme d'item, a la
  mecanique et au rendu.
- **Deux garde-fous de forme devront etre etendus** dans le lot : le selecteur de
  `TailleDesTextesEtBoutonsTest` (mesure section 7) et, s'il en apparait d'autres, tout perimetre
  derive par enumeration de balises.
- **Rien de tout cela ne fait avancer la couverture du programme d'une ligne.** La saisie ouvre des
  lignes que le choix parmi quatre ferme mal ; elle en ferme cinq par avance (`R1` a `R5`). Le bilan
  de couverture appartient a `camille-po`.

## Ce que je route plutot que de le contourner

**`SAI-9` lue au pied de la lettre contredit `M5` et `M9` clause 5, deux conditions de `nadia-prof`
elle-meme.** `SAI-9` ecrit : *"Quand une reponse est refusee, le retour montre ce que l'enfant a ecrit
a cote de la reponse attendue"*, avec l'exemple *"Tu as écrit : elleve. On écrit : élève."* Applique a
**chaque** refus, cela nomme la bonne reponse des le premier essai. Or `M5` donne trois essais avant
la revelation, et `M9` clause 5 est encodee dans le code : `BanditManchotSession.reponseRevelee()`
n'expose la bonne reponse qu'une fois la manche revelee, *"avant ce moment, aucune methode de cette
classe ne la designe"*. La saisie heriterait de cette invariance ou la romprait.

Deux lectures, et je ne choisis pas a sa place :

- **lecture (a), litterale** : les deux chaines a chaque refus. La manche n'a plus qu'un essai utile,
  `M5` devient sans objet sur cette mecanique, et `M9` clause 5 doit etre amendee ;
- **lecture (b)** : la chaine ecrite par l'enfant a chaque refus, les **deux** chaines cote a cote a
  la revelation. C'est mot pour mot l'acquis qu'elle decrit elle-meme en section 0 de son arbitrage -
  *"au moment de la revelation il voit son erreur a cote de la bonne reponse"* - et cela tient `M5`,
  `M9` clause 5 et `SEUIL-1` ensemble.

**Cet ADR est ecrit sur la lecture (b)**, parce que c'est la seule qui ne contredit aucune de ses
autres conditions. **`sofia-sm` la lui route** ; si elle tranche pour (a), la section 6 de cet ADR ne
change pas d'un mot - le serveur garde la chaine dans les deux cas - mais `M9` clause 5 et son
encodage dans le domaine deviennent a rouvrir, et je le ferai par un amendement date.

**Ce que je route et qui ne bloque rien.** Sa question a l'utilisatrice sur l'acces a une lettre
accentuee sur tablette *"conditionne la portee de tout ce document"*, ecrit-elle. Elle ne conditionne
pas cet ADR : `SAI-1` est le **defaut** de cette conception, donc que la reponse soit "touche directe"
ou "appui long", l'architecture est la meme. La reponse change la valeur pedagogique de la saisie
alphabetique, pas son mecanisme. Ce n'est pas une raison de ne pas la poser, c'en est une pour ne pas
attendre.

## Alternatives considerees

1. **Le paquet porte les formes acceptees et rien d'autre, sans normalisation dans la mecanique.**
   Ecartee : `SAI-1`, `SAI-3` et `SAI-4` demanderaient d'enumerer, pour chaque mot, ses variantes
   d'accent, de casse et d'espacement - une explosion combinatoire dans la donnee, ecrite a la main
   par la personne qui tient le bareme. `SEUIL-2` tomberait a la premiere variante oubliee.
2. **La mecanique porte les regles d'orthographe acceptables.** Ecartee, et elle est deja rouge :
   c'est ce que `uneMecaniqueIgnoreLaMatiere` et `AucunVocabulaireDeMatiereTest` refusent depuis s00.
3. **Un mode strict deduit de la matiere ou du niveau.** Ecartee par `SAI-2` (*"une regle devinee est
   une regle qui se trompera un jour"*) **et** par la clause 4 d'`ADR-0004`, qui la rend impossible.
4. **Une normalisation partagee, hissee dans un contrat, appelee par les deux mecaniques.** Ecartee,
   et c'est l'alternative dont cet ADR fait une regle : c'est la dette centrale de la version
   precedente sous une forme que les regles de s00 ne voyaient pas.
5. **Un enum `Comparaison { FORME_LIBRE, FORME_EXACTE }` plutot qu'un booleen.** Ecartee : deux etats
   nommes par `nadia-prof`, aucun troisieme demande. Declencheur de reouverture ecrit en section 1.
6. **Elargir `GameSession` pour que l'adaptateur n'ait plus a descendre vers un type concret.**
   Ecartee en section 5 : ce serait la dette 2 de la version precedente portee par le contrat de
   mecanique. Le retypage de l'adaptateur (`R-A`) resout le meme probleme sans charger le contrat.

---

## Amendement du 2026-08-24, meme jour - `SAI-9` est tranchee, `SAI-12` est chiffree, et une phrase de ma section 6 etait fausse

### 1. La question que je routais est fermee, et rien ne m'est du

`nadia-prof` tranche `SAI-9` en retenant la **lecture (b)** - celle sur laquelle cet ADR est ecrit -
et elle precise que ce n'est pas par commodite d'architecture : la lecture litterale aurait vide les
trois essais de `M5` de leur sens, et elle aurait refuse un increment qui l'aurait implementee. Elle
scinde sa condition en `SAI-9a` (a chaque refus, la chaine de l'enfant seule) et `SAI-9b` (a la
revelation, les deux chaines).

**Consequence sur cet ADR : aucune ligne de son corps ne change.** `M9` clause 5 n'est pas rouverte,
son encodage dans le domaine ne bouge pas, et **l'amendement date que je m'engageais a ecrire si elle
tranchait pour (a) n'est pas du**. Je le note ici pour que la section "Ce que je route" ne reste pas
lue comme une question ouverte dans un fichier que d'autres reprennent.

### 2. `SAI-12`, chiffree a part comme elle le demande

**Sa question** : j'ai chiffre une chaine, elle en demande trois. Est-ce que cela change la
conception, l'etat de partie en session, le retypage d'`adapter-web`, ou seulement le rendu ?

**La reponse, poste par poste, contre cet ADR tel qu'il est ecrit :**

| Ce que `SAI-12` touche | Verdict |
|---|---|
| Conception, sections 1 a 4 (forme d'item, mode strict, normalisation, contrats) | **rien** |
| Section 5, `Move` et `GameSession` | **rien** - la liste vit sur le type de session de la saisie, comme le reste |
| Retypage d'`adapter-web` (20 occurrences, 3 fichiers) | **rien** - ce chiffre compte les endroits qui **nomment** la mecanique, pas ce que la session porte |
| Etat de partie de la saisie | **un champ change de type, et un champ disparait** |
| Rendu | **oui**, et c'est la que le travail est |

**Et ma section 6 disait faux sur un point, que je corrige plutot que de le laisser passer.** J'y ai
ecrit : *"garder les trois est le meme geste que garder une - la session porte deja
`Set<String> propositionsEssayees` a cote de `derniereTentative`"*. Le geste, oui. **Le type, non**,
et la difference est exactement celle que `SAI-12` demande.

`BanditManchotSession` normalise son champ a la construction :
`this.propositionsEssayees = Set.copyOf(propositionsEssayees)`. `Set.copyOf` rend un ensemble
immuable **dont l'ordre d'iteration n'est pas specifie et varie d'une machine virtuelle a l'autre**.
**Mesure** : cinq executions de `jshell` sur le JDK 21.0.10 de la toolchain declaree, meme ordre
d'insertion a chaque fois (`elleve, eleve, elevve, ellevve`), **deux ordres d'iteration distincts
observes, dont aucun n'est l'ordre d'insertion**. Ce n'est pas une lecture de documentation, c'est un
constat.

**Pourquoi cela ne coute rien aux boutons et tout a la saisie.** Sur les boutons, ce champ n'est
jamais itere pour l'affichage : le gabarit ne l'interroge que par appartenance
(`th:classappend="${propositionsEssayees.contains(proposition)} ? 'deja-essayee' : ''"`) et
`PartieController` ne s'en sert que par `contains` et `isEmpty`. **L'ordre affiche est celui de
`propositions`, une `List` melangee une fois par manche.** Dans la saisie, cette liste n'existe pas :
l'ordre de ce que l'enfant a ecrit ne vit nulle part ailleurs que dans la collection elle-meme.
Reprendre le meme type rendrait a l'enfant, sur la meme manche, une liste dont l'ordre peut changer
d'un demarrage a l'autre.

**Ce que cela decide, et cela simplifie.** La session de la saisie porte
**`List<String> tentatives`**, dans l'ordre ou l'enfant les a ecrites, et **`derniereTentative()`
devient une propriete calculee** - la derniere de la liste - au lieu d'un second champ. C'est le
geste d'`ADR-0039`, et il fait **un champ au lieu de deux** : `SAI-12` en retire un, elle n'en ajoute
pas.

**Ce qu'elle coute, borne.** Au plus **deux** chaines anterieures a l'ecran :
`ESSAIS_AVANT_REVELATION` vaut 3, donc le deuxieme essai en montre une et le troisieme en montre
deux. Rien a stocker qui merite d'etre nomme, et le texte de l'enfant traverse deja le rendu pour
`SAI-9a` - trois chaines echappees plutot qu'une n'est pas une difference de nature.

**Un fait d'architecture qui evite une decision inutile a `nadia-prof`** : avec les boutons,
represser une proposition deja marquee **consomme bel et bien un essai** - `jouer` incremente
`essaisSurLaManche` avant que `essayeesSuivantes.add(choix)` ne fasse rien sur un doublon. Donc une
chaine retapee a l'identique qui consomme un essai dans la saisie est **symetrique de la mecanique
existante**, pas une decision neuve. Restent deux points qui ne sont pas de moi : si une chaine
retapee a l'identique apparait **deux fois** dans la liste, et si la liste se compare avant ou apres
normalisation. Les deux sont des questions de forme et de pedagogie - une repetition vue ou non vue
est precisement ce que `SAI-12` existe pour montrer - et je les laisse a `nadia-prof`.

**`R-C`, due avec le lot de code, et je la nomme pour qu'elle ne soit pas une bonne intention.**
Aucune regle ArchUnit ne garde ce que `SAI-12` demande, et je ne prends pas ce silence pour un vert :
ArchUnit voit des types, pas l'ordre d'une collection rendue par un gabarit. Le mecanisme automatique
est un **test unitaire de la mecanique de saisie** : trois tentatives ecrites dans un ordre connu
reviennent dans cet ordre-la, et le meme test sur un autre ordre d'insertion rend cet autre ordre -
c'est cette seconde moitie qui distingue "ordonne" de "ordonne par hasard comme je l'attendais", et
c'est elle qu'un `Set.copyOf` fait rougir.

### 3. Ce que cet amendement ne change pas

Les sections 1 a 5, 7 et 8, les regles executables, `R-A`, `R-B` et toutes les mesures de cet ADR
sont inchangees. La conception tient telle qu'elle est ecrite ; c'est une phrase de justification de
la section 6 qui etait fausse, et le type d'un champ qui s'en deduit.
