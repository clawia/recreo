# [Sprint s05] Confirmation ArchUnit - theo-archi

> Ce fichier a deux parties. La premiere date du 2026-08-06 et juge l'arbre sur lequel `ADR-0018` a
> ete ecrit. La seconde, en fin de fichier, date du 2026-08-07 et juge l'arbre **fusionne**, apres
> les quatre lots du sprint et le rhabillage du gabarit. **La seconde corrige un chiffre de la
> premiere** : l'inventaire de regles annonce ci-dessous etait faux.

- **Date** : 2026-08-06
- **Auteur** : theo-archi
- **Arbre juge** : `round-two`, commit `099e750`, **plus les fichiers du present commit `[THEO]`** -
  `ADR-0018`, ses deux regles, ses deux contre-exemples, son test de comportement, la separation des
  deux canaux dans `adapter-web`, les amendements d'`ADR-0011` et d'`ADR-0013`, et l'index.
- **Prerequis** : `JAVA_HOME` sur un JDK 21, positionne pour la duree de la commande et jamais ecrit
  dans un fichier commite.
- **Ce que je ne fais pas ici** : aucun Done pedagogique ni son anticipation - il appartient a
  `nadia-prof` seule. Aucun arbitrage produit.

## Le resultat de la commande

`mvn -B clean verify`

```
recreo ............................................. SUCCESS
recreo-domain ...................................... SUCCESS      38 tests
recreo-application ................................. SUCCESS       7 tests
recreo-adapter-web ................................. SUCCESS      82 tests
recreo-adapter-content ............................. SUCCESS      16 tests
recreo-bootstrap ................................... SUCCESS   61 + 2 tests
BUILD SUCCESS
```

**206 tests, 0 echec.** Inventaire des regles : **29 `ArchRule` declarees**, toutes passees a un
`check` - verifie par `LesGardeFousSontMisALEpreuveTest`, pas par moi. Plafond de dette de balayage
inchange a **3**.

> **Correction du 2026-08-07.** Ce "29" est faux, et je n'ai pas retrouve d'ou il sort. Le compte
> reel a ce commit etait **25**. Je n'avais donne aucune methode de comptage, ce qui est
> precisement la faute : un nombre sans sa methode ne se verifie pas et ne se compare pas. La
> methode est ecrite dans le complement, en fin de fichier.

Deux echecs rencontres en chemin, tous deux miens et tous deux resolus, notes parce qu'un vert qui
n'a jamais rougi n'apprend rien :

1. Premier `verify` : `BUILD FAILURE` sur le `clean` de `bootstrap`. Cause reelle, pas supposee -
   une instance de l'application que j'avais lancee pour reproduire le defaut tenait `recreo.jar`
   verrouille. C'est exactement ce que la Javadoc de `JarRecreoIT.arreter` documente. Processus
   arrete, `verify` relance, vert.
2. Aucun echec de test.

## Ce que j'ai mesure avant de conclure, plutot que sur parole

Le defaut signale a ete reproduit sur le jar de `099e750`, pas admis :

| Mesure | Resultat |
|---|---|
| `propositionChoisie=a` sur `bandit-manchot-homophones-cm1` | verdict normal, HTTP 200 |
| `propositionChoisie=à`, session neuve | ecran d'erreur, HTTP 200 |
| Journal complet apres declenchement | **21 lignes, toutes de demarrage. Aucune trace.** |
| Type reel de l'exception (journal en `DEBUG`) | `org.apache.tomcat.util.http.InvalidParameterException` |
| Sa hierarchie (`javap` sur `tomcat-embed-core-11.0.22.jar` extrait du jar) | `extends java.lang.IllegalStateException` |
| Un troisieme type, hors sous-arbre, sur la route du jeu | `{"timestamp":...,"status":400,...}` en clair |

Le point decisif est la ligne `javap` : le gestionnaire n'attrapait pas deux situations, il attrapait
deux **sous-arbres**, et une exception du conteneur Tomcat empruntait celui de la session perdue.
`ADR-0018` en tire la decision et les regles.

Apres correction, sur le jar reconstruit et **au niveau de journalisation par defaut** : le meme coup
accentue produit une ligne `ERROR` avec la pile complete et le point d'entree ; un coup sans partie
en cours produit une ligne `INFO` ; une ressource statique absente reste sur le comportement par
defaut de Spring Boot, sans fragment de jeu. L'ecran rendu a l'enfant est inchange dans tous les cas.

## Ce qui reste ouvert, et a qui

- **La correction d'encodage elle-meme appartient a `lea-dev`** et n'est pas dans ce commit. Le
  defaut est toujours present ; ce qui change est qu'il ne peut plus etre silencieux.
- **Dette nommee, non fermee** : un parametre manquant sur la route du jeu rend encore du JSON brut
  en 400. Mesuree, ecrite dans `ADR-0018`, section "Ce que cette regle ne couvre pas". Aucune story
  ne la demande.
- **Meme forme ailleurs, non atteignable aujourd'hui** : un paquet de contenu epuise avant
  `nombreDeManches` rend l'ecran de fin de partie normal, sans trace. Les deux paquets du catalogue
  portent 22 items et une source infinie pour `nombreDeManches: 10` ; `CatalogueChargeableIT` ne
  verifie que la borne basse. Je n'ecris pas de regle sur un cas jamais constate. L'arbitrage de
  couverture revient a `yanis-qa` et `camille-po`.
- **Cinquieme occurrence du perimetre qui ne derive pas de la source**, portee dans `ADR-0013` par
  amendement plutot que laissee a une sixieme decouverte : les **donnees d'entree** d'un test font
  partie de son perimetre. `PartieHttpTest` joue une partie complete contre un catalogue ecrit a la
  main dont les propositions sont `"1"`, `"2"`, `"3"` ; `CatalogueChargeableIT` parcourt le vrai
  catalogue mais n'appelle jamais `jouer`. L'encodage de cette occurrence est le garde-fou de contenu
  de `lea-dev` ; la generalisation reste une doctrine de revue, et l'amendement le dit au lieu de la
  deguiser en regle.

---

# Complement du 2026-08-07 - l'arbre fusionne

- **Arbre juge** : `round-two`, commit `d53adfc`, **plus les fichiers du present commit `[THEO]`**.
  Il contient les quatre lots du sprint et le rhabillage : `d0ed8d3` (`lea-dev`, decodage tolerant
  du corps de formulaire), `d00b121` (`marc-dev`, contenu a trous), `cf7891c` (moi, `ADR-0018`),
  `a325af9` (`ines-design`, en-tete et menu), `d53adfc` (`relecteur-pedagogique`).
- **Sur quel JDK je mesure** : JDK **21.0.10** (LTS, HotSpot 21.0.10+8-LTS-217), positionne dans
  `JAVA_HOME` pour la duree des commandes. Il **existe sur ce poste**, contrairement a ce que quatre
  agents ont conclu : le fichier de toolchains du depot local Maven s'appelle `toochains.xml`, avec
  un `l` manquant, donc Maven ne le lit jamais et ne trouve aucun JDK 21 par ce chemin-la. Le `java`
  du `PATH` par defaut est un JDK 8, ce qui explique le diagnostic errone. Renommer ce fichier
  releve du poste de l'utilisatrice, pas du depot ; je le signale sans y toucher.
- **Deux instances arretees, dites plutot que tuees en silence** : l'instance de l'utilisatrice sur
  le port 8080 (elle tenait `recreo.jar` verrouille, `clean` echouait) et la mienne, lancee sur un
  port distinct pour les mesures ci-dessous. Les deux ports sont libres a l'ecriture de ces lignes ;
  **l'application n'est plus lancee sur 8080, il faut la relancer.**
- **Ce que je ne fais pas ici** : aucun Done pedagogique ni son anticipation. Aucun arbitrage
  produit.

## 1. Le resultat de la commande, et la methode de comptage

`mvn -B clean verify`

```
recreo ............................................. SUCCESS
recreo-domain ...................................... SUCCESS      38 tests
recreo-application ................................. SUCCESS       7 tests
recreo-adapter-web ................................. SUCCESS      98 tests
recreo-adapter-content ............................. SUCCESS      21 tests
recreo-bootstrap ................................... SUCCESS   61 + 3 tests
BUILD SUCCESS
```

**228 tests, 0 echec. 25 `ArchRule` reellement executees.**

`yanis-qa` compte de son cote dans un worktree isole. Nos deux chiffres doivent pouvoir etre
**compares, pas fusionnes** : voici exactement ce que je compte, et les trois pieges qui font
diverger deux comptages honnetes.

**Ce que je compte.** Les modules viennent du pom agregateur (`<module>`), jamais d'une liste. Dans
chacun, je parcours `src/test/java` et j'applique l'algorithme de `reglesDeclarees` de
`LesGardeFousSontMisALEpreuveTest`, caractere pour caractere : la chaine `ArchRule ` suivie d'un
identifiant Java puis, apres espaces eventuels, d'un `=`. Resultat : **25 declarations, 25 champs
`@ArchTest`, aucun nom en double**.

| Fichier | `ArchRule` | `@ArchTest` |
|---|---|---|
| `adapter-web/.../archi/AssertionsWebTest.java` | 1 | 1 |
| `bootstrap/.../archi/AbstractionsCiteesTest.java` | 4 | 4 |
| `bootstrap/.../archi/ArchitectureEnCouchesTest.java` | 2 | 2 |
| `bootstrap/.../archi/ChargementDuContenuTest.java` | 1 | 1 |
| `bootstrap/.../archi/DecouplageMecaniqueContenuTest.java` | 7 | 7 |
| `bootstrap/.../archi/DifficulteTest.java` | 3 | 3 |
| `bootstrap/.../archi/GestionDesErreursTest.java` | 4 | 4 |
| `bootstrap/.../archi/LesReglesMordentTest.java` | 1 | 1 |
| `bootstrap/.../archi/PureteDesCouchesTest.java` | 2 | 2 |
| **Total** | **25** | **25** |

**Piege 1 - le faux depot.** `bootstrap/src/test/resources/contreexemple/LesGardeFousSontMisALEpreuveTest/faux-module/src/test/java/`
contient 2 `ArchRule` de plus. Elles ne sont **jamais executees** : c'est le depot fictif sur lequel
le recensement se prouve mordant. Un `git grep` sur `*/src/test/java/*.java` les compte et donne
**27**. Mon parcours ne les voit pas, parce qu'il part des modules du pom.

**Piege 2 - les worktrees dans l'arbre.** Les worktrees d'agents vivent sous `.claude/worktrees/`,
donc **a l'interieur** du repertoire de travail. Un `grep -r` lance depuis la racine du depot les
parcourt et double tout. Meme protection : partir du pom.

**Piege 3 - deux moteurs JUnit, celui de s02, toujours actif.** Une regle `@ArchTest` est executee
par le moteur ArchUnit ; un `@Test` par le moteur Jupiter. Les deux ecrivent leurs resultats pour la
**meme classe**, et le rapport `.txt` par classe de Surefire **ne conserve que le dernier**. Mesure
sur ce build :

| Classe | XML (`tests=`) | `.txt` |
|---|---|---|
| `AbstractionsCiteesTest` | 5 | 1 |
| `LesReglesMordentTest` | 24 | 23 |
| `ChargementDuContenuTest` | 2 | 1 |
| `AssertionsWebTest` | 2 | 1 |

Un comptage fonde sur les `.txt` donne **221** au lieu de 228, et perd exactement les **7** regles
`@ArchTest` des quatre classes mixtes. **Ne pas compter sur les `.txt`.** L'agregat de la console
Maven et les `TEST-*.xml` sont d'accord entre eux, a 228.

Une derniere chose que je ne fais pas moi-meme : verifier que chaque regle est passee a un `check`.
C'est `LesGardeFousSontMisALEpreuveTest.aucuneRegleArchUnitNEchappeAuContreExemple` qui le fait, et
il est vert.

## 2. Ma propre production, revue sur l'arbre fusionne

La question qui m'est posee est la bonne : le filtre de `lea-dev` empeche l'exception qui a motive
`ADR-0018` d'etre levee. **Ma regle de journalisation garde-t-elle encore quelque chose ?**

**Ce qui est reellement mort.** Un fait de l'ADR, pas une regle : la section "Mesures, apres
correction, sur le jar reellement construit" montre une ligne `ERROR` obtenue en envoyant une
proposition accentuee. **Cette mesure n'est plus reproductible** sur l'arbre fusionne, et c'est
voulu - le filtre traite desormais la sequence d'octets invalide comme une reponse fausse ordinaire.
Verifie sur le jar de cet arbre, session neuve sur `bandit-manchot-homophones-cm1` :

| Requete | Ecran rendu | Ligne `ERROR` |
|---|---|---|
| `propositionChoisie=%C3%A0` (UTF-8 valide) | verdict normal | aucune |
| `propositionChoisie=%E0` (octet latin-1, invalide en UTF-8) | verdict normal | aucune |

Les deux corrections tiennent donc ensemble, et dans le bon sens : la correction de `lea-dev` **vide
le canal `ERROR` du bruit** au lieu de le remplir. C'etait l'inquietude symetrique.

**Ce qui n'est pas mort, et c'est le point.** Les deux regles ArchUnit d'`ADR-0018` sont statiques :
elles jugent les methodes `@ExceptionHandler`, pas les requetes. Le filtre ne les touche pas. Leurs
deux contre-exemples les font toujours echouer, un par mode de defaillance. Et le test de
comportement `UneErreurImprevueLaisseUneTraceTest` leve `DefautJamaisVu` depuis un double du port
entrant : ce chemin ne passe par aucun decodage de parametre.

Mais un garde-fou dont la seule preuve est un double de test merite la question du code mort. Je
l'ai donc traitee sur le jar, avec une requete **reellement atteignable par un enfant** : rejouer le
dernier coup d'une partie terminee, ce qu'un double appui produit. Dix coups joues, puis un
onzieme :

```
ERROR ... c.r.adapter.web.GestionnaireDesErreurs : defaut imprevu sur POST /parties/bandit-manchot-additions-cp/coups
java.lang.IllegalStateException: la partie est terminee, plus aucune manche a jouer
	at com.recreo.domain.mechanics.banditmanchot.BanditManchotSession.jouer(BanditManchotSession.java:80)
```

Au niveau de journalisation par defaut, avec la pile, et l'enfant voit `Oups ! Le jeu a un souci.`
comme avant. **C'est une `IllegalStateException`** - exactement le sous-arbre par lequel le defaut de
s05 s'etait deguise en session perdue. Avant `ADR-0018`, ce double appui rendait le meme ecran et
n'ecrivait rien. La regle garde donc un chemin de production vivant, pas seulement une hypothese.

Ce que je ne fais pas : ecrire une regle sur ce cas precis. Un double appui sur le dernier coup est
un defaut fonctionnel, pas architectural, et il appartient a `camille-po` et `yanis-qa` de decider
s'il vaut une story. Je le remonte, je ne le tranche pas.

## 3. Le trou de frontiere d'`ADR-0017` - ferme

`camille-po` et `ines-design` me routaient toutes deux le meme point, et c'etait bien l'objet de mon
travail partiel. Le lien de menu de S6-1 vit dans `accueil.html`, les boutons d'action dans
`fragments/manche.html` et `fragments/retour.html`, et l'en-tete est present sur tous les ecrans :
c'est exactement la limite 5 que j'avais nommee dans `ADR-0017` en ecrivant "rien ne l'empeche
demain". Demain, c'etait le sprint suivant.

Ferme. Un ecran n'est plus un fichier de gabarit : c'est la zone que htmx echange, plus ce qui lui
survit. La cible est **lue** dans les gabarits (`hx-target`), pas ecrite dans le test, et plusieurs
cibles distinctes sont un echec explicite. Detail dans l'amendement du 2026-08-07 a `ADR-0017`.

**Ce que la regle interdit reellement**, verifie et pas raisonne. En revenant a l'ancienne
semantique, un ecran = un fichier, et en relancant la seule classe :

```
LActionNeDeclarePasMoinsQueLaSortieTest.uneSortieDHabillageQuiDepasseLActionDUnAutreFichierFaitEchouerLeGardeFou
LActionNeDeclarePasMoinsQueLaSortieTest.leLienDeLEnTeteEstConfronteAuxActionsDesEcransDeJeu
Tests run: 10, Failures: 2
```

Le jeu fautif est construit pour cela : sa sortie d'habillage est **plus petite** que l'action ecrite
dans le meme fichier - donc la comparaison au sein d'un fichier reste verte - et **plus grosse** que
l'action du fragment. Seule la traversee de la frontiere la rend rouge. Sans la correction, ce
contre-exemple serait decoratif.

Le lien de `ines-design` passe sans qu'un octet de sa feuille change : elle l'avait deja aligne sur
`.retour-accueil`. Ce qui change est que cette propriete tenait a son attention et tient maintenant a
un test. Les deux commentaires du depot qui affirmaient que rien ne confrontait ces deux fichiers -
dans `recreo.css` et dans `EnTeteEtMenuTest` - disaient vrai a leur ecriture et disent faux
maintenant ; ils sont corriges dans le meme commit.

Ce qui reste ouvert, et je le nomme plutot que de le laisser decouvrir : un habillage qui ne serait
pas un frere de la zone echangee **dans le meme fichier** echappe encore. Ce cas n'existe pas dans le
depot ; je n'ecris pas de regle sur un cas jamais constate.

## 4. Clean Architecture apres quatre fusions

| Propriete | Regle qui la garde | Etat |
|---|---|---|
| Dependances vers l'interieur | `lesDependancesVontVersLInterieur` | verte |
| Deux adaptateurs ne se voient pas | `lesAdaptateursNeSeConnaissentPas` | verte |
| `domain` : JDK et lui-meme, rien d'autre | `leDomaineNeDependQueDuJdk` | verte |
| `application` : JDK et `domain`, rien d'autre | `lApplicationNeDependQueDuJdkEtDuDomaine` | verte |

Les deux regles de purete sont ecrites en **liste blanche** (`resideOutsideOfPackages`), pas en
liste noire de frameworks : un `jakarta.servlet` ou un `org.springframework` dans `domain` ou
`application` est refuse sans que personne ait a l'avoir prevu. C'est ce qui rend la reponse
suivante solide.

**Le filtre servlet de `lea-dev`, verifie nommement.** `CorpsFormulaireEnUtf8ToleranteFilter` vit
dans `com.recreo.adapter.web`. C'est le bon endroit : un filtre servlet est une piece de l'adaptateur
entrant HTTP, et rien d'autre dans le depot n'a de raison de savoir comment un corps de formulaire
se decode. Il ne fuit nulle part :

- aucun fichier hors d'`adapters/adapter-web/src` ne nomme `CorpsFormulaireEnUtf8ToleranteFilter` ni
  `RequeteFormulaireDecodeeEnUtf8` (verifie par balayage du depot, worktrees exclus) ;
- `RequeteFormulaireDecodeeEnUtf8` est `final` et de **portee paquet** : elle n'est pas
  instanciable depuis `bootstrap`, meme par accident ;
- son cablage est un `@Component` ramasse par le scan de `RecreoApplication`, donc `bootstrap` reste
  la seule couche qui compose - il ne l'importe pas.

Une consequence a dire, qui n'est pas un defaut mais un rayon d'action : ce filtre change le
decodage des parametres de **toute** requete `POST application/x-www-form-urlencoded` de
l'application, pas seulement des routes de jeu. C'est coherent tant qu'`adapter-web` est le seul
adaptateur entrant HTTP. Le jour ou une route servirait un autre encodage, c'est la premiere piece
a relire.

## 5. Les plafonds de dette

Deux cliquets distincts, tous deux verifies.

**`LesGardeFousSontMisALEpreuveTest`, dette de balayage : coherent, inchange a 3.** Le depot compte
**11** garde-fous de balayage hors ce test lui-meme. Trois sont exemptes
(`AucunVocabulaireDeMatiereTest`, `FaviconTest`, `FrontieresDuJavaScriptTest`) ; j'ai verifie qu'ils
sont encore **reellement** non eprouves - aucun des trois ne contient `assertThatThrownBy` - donc
aucun nom n'exempte a vide, et le plafond ne peut pas etre baisse sans travail reel. Les deux
garde-fous de balayage arrives ce sprint, `ChaquePaquetATrousPorteSonTrouSurChaqueItemTest`
(`marc-dev`) et le mien, sont **hors dette** et portent chacun leur jeu fautif. `EnTeteEtMenuTest`
n'est pas un balayage : il rend par le vrai moteur Thymeleaf et ne lit aucun fichier de production
depuis le disque ; sa Javadoc le dit et le recensement le confirme.

**`ChaquePaquetPorteUnIndiceTest`, dette de contenu : incoherent, corrige.** Voir le point 6.

## 6. La fermeture de la dette d'`ADR-0016` - l'ecart signale par le relecteur

Le `relecteur-pedagogique` a raison, et l'ecart etait plus qu'un oubli de redaction.

`DETTE_DE_CONTENU` est `Set.of()` : `lea-dev` a ecrit les 24 indices de `cp-le-bon-son`, `marc-dev`
ceux de `cm1-homophones`, et le garde-fou passe sur les deux paquets, item par item, en tirant par le
chargeur reel. **La dette de contenu d'`ADR-0016` est fermee.** Je le confirme, ce que j'aurais du
faire dans la premiere version de ce fichier.

Mais en allant le verifier, j'ai trouve le vrai defaut. Le planning de s05, point 3 de sa consigne de
fusion, me demandait nommement de confirmer que `PLAFOND_DE_DETTE` **tombe a 0** si les deux lots
fermaient leur part. L'ensemble etait vide ; **le plafond etait reste a 2**. Un cliquet dont le
plafond depasse la dette n'est pas un cliquet : deux paquets neufs sans indice pouvaient encore etre
exemptes sans qu'aucun diff de plafond le montre. Le commentaire du champ appelait meme ce plafond
"la trace de cette fermeture", ce qui etait faux.

Plafond porte a **0** dans ce commit, avec le motif ecrit sur le champ, et `ADR-0016` amende.

## 7. Avis : porte architecture **ouverte**

Motif, en trois points et sans complaisance.

1. **La suite est verte et le vert est verifiable** : 228 tests, 25 regles, methode de comptage
   ecrite et reproductible, trois causes de divergence nommees pour que le chiffre de `yanis-qa`
   puisse etre compare au mien.
2. **Les deux defauts que j'ai trouves sur ma propre production sont corriges dans ce commit**, pas
   reportes : le trou de frontiere d'`ADR-0017`, ferme et prouve mordant par retour a l'ancienne
   semantique ; le plafond de dette d'`ADR-0016`, ramene a 0. Le troisieme, le "29" du 2026-08-06,
   est corrige a l'endroit ou il a ete ecrit.
3. **La quatrieme occurrence d'un defaut de forme visible par un enfant n'aurait pas ete attrapee
   par la CI avant ce commit ; elle le serait maintenant.** C'est la seule raison pour laquelle
   j'ouvre : `ines-design` avait fait le bon choix de style, mais un bon choix que rien ne garde est
   un souhait.

Ce que cette porte **ne dit pas**, et qu'il ne faut pas lui faire dire : rien ici ne se prononce sur
le rendu vu par un enfant. La saillance rendue depend de la couleur, de la position et de la largeur
des libelles, qu'aucune de mes regles ne mesure (`ADR-0017`, points 1 a 3 de sa section de limites).
Le Done pedagogique appartient a `nadia-prof`, en jouant, et a elle seule.
