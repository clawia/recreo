# [Sprint s03] Confirmation ArchUnit - theo-archi

- **Date** : 2026-08-06
- **Auteur** : theo-archi
- **Arbre juge** : `round-two`, commit `4c62f60` (dernier commit fusionne), **plus les fichiers du
  present commit `[THEO]`** - `ADR-0015`, la correction des deux garde-fous qu'il tranche, leurs
  deux jeux fautifs, l'amendement d'`ADR-0014` et l'index.
- **Perimetre** : s03 dans son entier, **et la part s04 qui releve de moi**. `s04 n'a aucun dossier
  de sprint` : je ne le fabrique pas. Voir la section "Ou va la part s04" en fin de document.
- **Ce que je ne fais pas ici** : aucun Done pedagogique, ni son anticipation. Il appartient a
  `nadia-prof` seule. Aucun arbitrage produit ni pedagogique : ce qui en releve est remonte a
  `sofia-sm` et nomme comme tel.

## 1. Sur quel JDK je mesure, et pourquoi c'est une section a part

**JDK 21.0.10** (`21.0.10+8-LTS-217`), le JDK 21 reel de ce poste. `JAVA_HOME` positionne dessus
pour la duree de la commande, jamais ecrit dans un fichier commite.

Trois agents ont affirme en s03 que ce JDK n'existait pas et ont valide sur un JDK 25. Il existe.
La cause trouvee par `lea-dev` est confirmee : **le fichier de toolchains de l'utilisateur porte un
nom fautif** - `toochains.xml` au lieu de `toolchains.xml` - donc Maven ne le lit jamais, et le
`java` par defaut du `PATH` est un JRE 8 qui ne compile pas. Le fichier declare pourtant bien un
JDK 21 et un JDK 25. Ce n'est pas un fichier du depot et je ne le corrige pas : c'est un poste de
travail, pas un livrable. Ce qui releve du depot, en revanche, est que **rien dans le depot n'oblige
a mesurer sur un JDK 21** : la CI le fait (`actions/setup-java`, `java-version: "21"`), un agent en
local ne le fait pas. C'est une dette de methode, pas d'architecture ; je la remonte a `sofia-sm`
pour la retro plutot que d'inventer un garde-fou pour un poste de travail.

## 2. La suite est verte - commande et resultat

```
mvn -B clean verify
```

**BUILD SUCCESS**, code de sortie 0, `Total time: 01:07 min`.

| Module | Tests unitaires | Tests d'integration | Echecs |
|---|---|---|---|
| `recreo-domain` | 38 | - | 0 |
| `recreo-application` | 7 | - | 0 |
| `recreo-adapter-web` | 68 | - | 0 |
| `recreo-adapter-content` | 10 | - | 0 |
| `recreo-bootstrap` | 57 | 2 | 0 |
| **Total** | **180** | **2** | **0** |

`sofia-sm` annonce 180 tests : le chiffre est exact pour l'arbre `4c62f60`, ou `adapter-web` en
comptait 66. Le present commit porte `adapter-web` a 68 - une methode supprimee, trois ajoutees
(`ADR-0015`) - donc **180 unitaires + 2 d'integration** sur l'arbre que je juge, contre 178 + 2
avant lui. Les deux chiffres 180 ne recouvrent pas le meme ensemble ; les ecrire tous deux evite
qu'on croie plus tard que rien n'a bouge.

## 3. Combien de regles ArchUnit sont reellement executees, et selon quel comptage

**23 regles.** C'est le comptage que j'emploie : **une regle = une `ArchRule` declaree et evaluee
contre les classes de production**. Il est verifiable sans me croire, parce qu'il est exactement
celui que `LesGardeFousSontMisALEpreuveTest.toutesLesRegles()` derive du balayage des sources de
test de tous les modules declares au pom agregateur.

| Classe | Regles | Moteur |
|---|---|---|
| `AbstractionsCiteesTest` | 4 | `archunit-junit5-engine` |
| `ArchitectureEnCouchesTest` | 2 | `archunit-junit5-engine` |
| `ChargementDuContenuTest` | 1 | `archunit-junit5-engine` |
| `DecouplageMecaniqueContenuTest` | 7 | `archunit-junit5-engine` |
| `DifficulteTest` | 3 | `archunit-junit5-engine` |
| `GestionDesErreursTest` | 2 | `archunit-junit5-engine` |
| `PureteDesCouchesTest` | 2 | `archunit-junit5-engine` |
| `LesReglesMordentTest` | 1 | `archunit-junit5-engine` |
| `AssertionsWebTest` (`adapter-web`) | 1 | `archunit-junit5-engine` |
| **Total** | **23** | |

Et **23 contre-exemples**, un par regle : 21 methodes `@Test` dans `LesReglesMordentTest` (dont
trois portent plusieurs regles - `uneAbstractionCiteeQuiDisparaitEstRefusee` en couvre trois,
`unTypeDeDifficulteDansLeDomaineEstRefuse` deux), plus la methode de `AssertionsWebTest`. Le fait
qu'aucune regle n'echappe a son contre-exemple n'est pas une affirmation de ma part : c'est
`LesGardeFousSontMisALEpreuveTest.aucuneRegleArchUnitNEchappeAuContreExemple` qui l'etablit, en
derivant la liste des regles au lieu de la tenir a la main.

**Sur le comptage `22` contre `21` releve par `yanis-qa` en s03 : sa conclusion est exacte et je la
confirme.** `LesReglesMordentTest` apparait deux fois dans la sortie surefire du module `bootstrap`,
`Tests run: 1` puis `Tests run: 21`, parce que **deux moteurs JUnit la parcourent** :
`archunit-junit5-engine` execute son unique champ `@ArchTest`
(`aucunContreExempleEnProduction`), `junit-jupiter` execute ses 21 methodes `@Test`. Total 22
executions pour 21 methodes ecrites. Ce n'est ni un doublon ni une regle fantome ; c'est la meme
classe vue par deux moteurs, ce qui est le comportement normal d'une classe qui porte les deux
sortes de tests. **Ce chiffre-la n'est pas un nombre de regles** : le nombre de regles est 23, et il
ne se lit dans aucune ligne `Tests run:`.

## 4. Clean Architecture apres les deux fusions

Les quatre proprietes exigees par le `CLAUDE.md` sont tenues, chacune par une regle qui a son
contre-exemple :

| Propriete | Regle | Contre-exemple qui la fait echouer |
|---|---|---|
| Les dependances vont vers l'interieur | `ArchitectureEnCouchesTest.lesDependancesVontVersLInterieur` | `DomaineDependantDeLApplicationContreExemple` |
| Deux adaptateurs ne se connaissent pas | `ArchitectureEnCouchesTest.lesAdaptateursNeSeConnaissentPas` | `AdaptateurWebDependantDuContenuContreExemple` |
| `domain` ne depend d'aucun framework | `PureteDesCouchesTest.leDomaineNeDependQueDuJdk` | `DomainePollueContreExemple` |
| `application` ne voit pas Spring | `PureteDesCouchesTest.lApplicationNeDependQueDuJdkEtDuDomaine` | `ApplicationPollueeContreExemple` |

### Les deux points precis demandes

**`GameMechanic.longueurMinimale()`, ajoute par `lea-dev` en s03.** La methode vit dans le contrat de
mecanique, dans `domain`. `DecouplageMecaniqueContenuTest.leContratDeMecaniqueEstFerme` et la purete
du domaine sont vertes sur elle. Elle ne fait entrer aucun type nouveau dans le contrat et ne porte
aucune borne arithmetique : `DifficulteTest.leContratDeContenuNePorteQueDuTexte` - la regle qui avait
du etre reecrite parce qu'elle laissait passer les primitifs - reste verte, et elle porte sur le
contrat de **contenu**, que cet ajout ne touche pas. `ADR-0012` est respecte : la longueur de partie
vit dans le catalogue, ce que `lea-dev` a ajoute est la borne minimale que la **mecanique** exige,
pas une longueur portee par un contenu.

**La bibliotheque tierce d'`ines-design` ne fuit nulle part.** Verifie de trois facons :

- **Aucune dependance Maven.** Aucun `pom.xml` du depot ne cite `bootstrap` ni `webjar`. Les
  dependances d'`adapter-web` sont `recreo-application`, `recreo-domain`,
  `spring-boot-starter-web`, `spring-boot-starter-thymeleaf`, `spring-boot-starter-test`,
  `spring-boot-webmvc-test`, `jsoup`, `archunit-junit5`. La bibliotheque est **un fichier statique
  vendorise**, pas une dependance : elle n'a donc aucun moyen d'atteindre `domain`, `application`,
  `adapter-content` ou `bootstrap` par le classpath. C'est exactement ce qu'`ADR-0014` cherchait en
  ecartant le webjar, et c'est ici verifie plutot que suppose.
- **Aucune citation hors `adapter-web`.** Le seul autre fichier du depot qui nomme `vendor/` est
  `.gitattributes`, pour y desactiver la conversion de fins de ligne (`ADR-0014`), et les ADR qui en
  parlent. Aucune source Java, aucun YAML de contenu, aucun gabarit hors `adapter-web`.
- **La classe `btn` ne sort pas des gabarits d'`adapter-web`** et de la feuille maison, ou elle
  n'apparait que dans des commentaires - donc jamais comme selecteur maison, ce que
  `TailleDesTextesEtBoutonsTest` verifie a son insu en exigeant qu'une regle **maison** s'applique a
  chaque bouton.

## 5. Le point le plus important de s04 : le decouplage est demontre

`DEC-0008` demandait que le francais **prouve** le principe directeur du `CLAUDE.md` - une meme
mecanique sert n'importe quelle matiere sans modification de code. `lea-dev` affirme n'avoir touche
aucune ligne de `domain/mechanic` ni `domain/mechanics`.

**Verifie au diff, et c'est vrai.** Le lot s04 (`a6c416c`, fusionne en `4c62f60`) contient
exactement quatre fichiers :

```
adapters/adapter-content/src/main/resources/catalogue.yaml              |  12 ++
adapters/adapter-content/src/main/resources/contenu/cm1-homophones.yaml |  39 ++
adapters/adapter-content/src/main/resources/contenu/cp-le-bon-son.yaml  |  81 ++
adapters/adapter-content/src/test/java/.../PaquetsFrancaisS04Test.java  | 145 ++
4 files changed, 277 insertions(+)
```

`git diff d5451b9..4c62f60 -- domain/` est **vide**. Pas seulement `domain/mechanic` et
`domain/mechanics` : le module `domain` entier n'a pas bouge d'une ligne. Pas une ligne de Java de
production n'a bouge dans aucun module. Deux jeux de francais - un CP, un CM1 - sont entres dans le
catalogue par **trois fichiers YAML et un test**.

**C'est la premiere demonstration du principe directeur en quatre sprints, et elle merite mieux
qu'un rapport de sprint.** Elle est actee de facon durable a trois endroits, parce qu'un rapport de
sprint ne se relit pas et qu'un test, lui, casse :

1. **Ici**, avec les commits et le diff, pour la trace.
2. **La ou la propriete est deja executable** : `DecouplageMecaniqueContenuTest` porte sept regles
   qui interdisent structurellement le couplage que ce lot vient de ne pas commettre. Ce que s04
   apporte n'est pas une regle de plus, c'est le **fait constate** qu'aucune n'a eu a etre
   contournee. Une regle qui interdit et un usage reel qui n'a pas eu besoin de la violer : c'est la
   difference entre une regle decorative et une regle qui tient.
3. **Ce qui manque encore, et que je nomme plutot que de l'inventer.** Aucune regle n'exige
   aujourd'hui qu'une matiere neuve n'entraine aucune modification de `domain`. Une telle regle est
   ecrivable - comparer, entre deux commits, l'ensemble des matieres du catalogue et l'ensemble des
   fichiers modifies sous `domain/` - mais elle jugerait un historique Git, pas un etat du depot, et
   `ADR-0009` a montre ce que couter une regle qui depend d'une plage de commits. **Je ne l'ecris
   pas maintenant** : une abstraction, et une regle en est une, se justifie sur un usage constate.
   Un seul lot de contenu constitue une observation, pas encore un usage. Ce qui me ferait changer
   d'avis : un second lot de matiere qui, lui, toucherait `domain`. Ce jour-la, la regle a un objet.

**Ce que je ne dis pas** : que ces deux jeux sont pedagogiquement bons. Cela n'est pas de mon
ressort et `nadia-prof` ne s'est pas prononcee. Le decouplage est une propriete de structure ; il
peut etre parfait sur un contenu mauvais.

## 6. Ma propre production de ce cycle, jugee comme celle des autres

`ADR-0013` et `ADR-0014` sont de moi, `ADR-0014` a rearbitre deux garde-fous existants, et je les
avais mesures sur un arbre qui n'est plus celui-ci : `ines-design` a depuis pose `.btn` sur chaque
bouton et retire du CSS maison. **Les quatre mutations sont rejouees sur l'arbre fusionne**, dans un
arbre de travail Git isole pour ne pas perturber `yanis-qa` et le `relecteur-pedagogique` qui
travaillent dans le meme depot.

| Mutation de l'arbre reel `4c62f60` | Resultat mesure |
|---|---|
| `class="btn proposition"` remplace par `class="btn"` sur le bouton de reponse | **rouge** sur les deux planchers, `toutBoutonReellementRenduEstAuMoinsAUnRemVingtCinq` et `toutBoutonReellementRenduAUneCibleTactileDAuMoinsQuaranteQuatrePixels` : `aucune regle maison ne s'applique a ce bouton (selecteurs vus : [.btn])` |
| une ligne ajoutee a la fin de `vendor/bootstrap.min.css` | **rouge**, `chaqueRessourceTierceEstTraceeEtIntacte`, "ne correspond plus a l'empreinte declaree" |
| les deux `<link>` d'`accueil.html` intervertis | **rouge**, `laFeuilleMaisonEstChargeeApresLaFeuilleTierce`, "doit passer apres" |
| `bootstrap.min.css` copie dans `static/css/` au lieu de `static/vendor/` | **rouge**, `ViewportEtLargeurTelephoneTest.aucuneLargeurNestFixeeAuDelaDeLaCibleTelephone` |
| **(le trou ferme sur `!important`)** la classe utilitaire tierce `.fs-6` (`font-size:1rem!important`) posee sur un bouton reel | **rouge**, `aucuneClasseTierceNeDefaitUnPlancherSurUnBoutonRendu` |

Les cinq mordent toujours apres la mise en oeuvre d'`ines-design`. La premiere est celle qui compte :
c'est le geste qu'un developpeur ferait de bonne foi en adoptant Bootstrap, et `.btn` etant desormais
sur **tous** les boutons, elle avait toutes les chances de s'etre emoussee. Elle ne l'est pas.

### Le trou que ce rearbitrage a laisse, et il y en a un

La question posee - le garde-fou de style ne juge que hors `vendor/`, le garde-fou pedagogique juge
un element rendu, cela a-t-il fait cesser une verification d'une exigence de `nadia-prof` - a une
reponse **oui**, mais pas la ou on l'attendrait. Le rearbitrage d'`ADR-0014` n'a rien retire ; il a
laisse ouvert un trou **anterieur**, qu'il a nomme dans sa section "ce que cette regle ne couvre pas"
au lieu de le fermer. C'est la faute exacte qu'`ADR-0013`, que j'ai ecrit deux jours plus tot,
interdit. Je ne me la passe pas.

`fragments/retour.html` porte
`th:class="${enSuite} ? 'btn retour-accueil' : 'btn sortie-pleine'"`. Le garde-fou lisait l'attribut
`class` du gabarit source, donc ne voyait jamais `.sortie-pleine` - le bouton de sortie de fin de
partie, exige en aplat par `nadia-prof` (reserve 4 de
`FICHE-bandit-manchot-additions-cp.md`). **Mesure sur l'arbre fusionne** : retirer `.sortie-pleine`
des deux groupes de regles de `recreo.css`, ce qui fait retomber ce bouton au defaut du navigateur,
laissait `TailleDesTextesEtBoutonsTest` **vert**. C'est la seconde des deux dettes de la section
suivante ; elle est fermee dans le present commit.

## 7. Les deux dettes nommees et non corrigees : tranchees

Elles ne sont pas renvoyees. `ADR-0015` les tranche toutes deux, et il est ecrit dans le meme geste
que les regles qui les rendent verifiables.

**Dette 1 - `HabillageDesEcransTest.aucuneRessourceExterneNiPoliceTelechargeeALExecution` ne balayait
que `recreo.css`.** Mesure d'abord, tranchee ensuite. Une feuille maison neuve `static/css/theme.css`
declarant un `@font-face` vers `fonts.gstatic.com` laisse ce garde-fou **vert** et fait echouer
`RessourcesTiercesTest.aucuneFeuilleNiAucunGabaritNeVaChercherQuoiQueCeSoitSurLeReseau`, dont le
perimetre derive de tout `static/` et des gabarits, et qui possede deux jeux fautifs. **La propriete
etait donc deja entierement gardee ailleurs.** Ce qui restait n'etait pas un trou de couverture,
c'etait une **illusion** de couverture : une methode dont le nom promet une propriete generale et
dont le vert ne prouve rien. Elle est **supprimee**, pas retrecie - un doublon aveugle vaut moins que
rien, parce qu'on le compte. Le reste de `HabillageDesEcransTest` continue de viser `recreo.css` par
son nom et c'est legitime : il juge des choix de style propres a ce depot, ce qui est une designation
d'objet et non une enumeration de perimetre.

**Dette 2 - `TailleDesTextesEtBoutonsTest` lisait la source brute des gabarits.** C'est bien la forme
exacte du defaut qu'`ADR-0013` nomme, et le diagnostic de la question est le bon : le perimetre
derivait de la mauvaise source. Le garde-fou derive desormais **chaque etat que le bouton peut porter
au rendu** - l'attribut `class`, chaque branche litterale de `th:class`, chaque ajout litteral de
`th:classappend` - et refuse une classe entierement calculee au lieu de l'ignorer, comme
`RessourcesTiercesTest` refuse deja une ressource referencee par une expression calculee.

Preuve de morsure, `ADR-0013` obligation 3, sur un **objet neuf** et non sur la mutation d'un objet
connu : deux jeux fautifs, `branche-conditionnelle-sans-plancher` (un bouton dont l'attribut `class`
est irreprochable et dont la seconde branche pose une classe qu'aucune regle maison ne declare) et
`classe-calculee`. Et la mutation de l'arbre reel, avant et apres :

| Mutation | Avant `ADR-0015` | Apres |
|---|---|---|
| `.sortie-pleine` retiree des deux groupes de regles de `recreo.css` | **vert** | **rouge** : `.btn.sortie-pleine "Les jeux" (fragments/retour.html) : aucune regle maison ne s'applique a ce bouton` |

Un point note dans l'ADR parce que c'est le genre d'erreur qu'on refait : ma premiere version prenait
l'**union** des classes de toutes les branches. Elle ne mordait pas - il suffit qu'une branche porte
une classe maison correcte pour que la branche fautive passe. Le jeu fautif reste vert sous l'union ;
c'est lui qui a fait la difference, pas mon raisonnement.

## 8. Garde-fous de CI et plafond de dette

**Les trois jobs du workflow sont executables et leurs verdicts sont eux-memes mis a l'epreuve avant
d'etre appliques (`ADR-0009`).** Les quatre scripts passent sur cet arbre :

| Verification | Resultat |
|---|---|
| `verifier-prefixes-test.sh` (le garde-fou de prefixes est teste avant de juger) | OK |
| `commit-atteignable-test.sh` (le repli sur base reecrite, amendement `ADR-0009`) | OK |
| `verifier-chemins-test.sh` (le garde-fou de chemins est teste avant de juger) | OK |
| `verifier-chemins.sh` sur les fichiers suivis **plus les fichiers neufs de ce commit** | "Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies" |
| `verifier-prefixes.sh` sur `b09366e..HEAD` | "Tous les commits portent un prefixe d'agent connu" |
| `ConcordanceDesTagsDAgentTest` (les tags cites par les trois documents derivent du script que la CI execute) | vert |

**`LesGardeFousSontMisALEpreuveTest`, qui a bouge deux fois pendant ce cycle.** Ses sept methodes sont
vertes. Le plafond a baisse de 6 a 5 le 2026-08-05 (`TexteAccentueDesGabaritsTest`), puis de 5 a 4 le
2026-08-06 (`TailleDesTextesEtBoutonsTest`, `ADR-0014`). Il est aujourd'hui a **4**, avec exactement
quatre noms dans `DETTE_DE_BALAYAGE` : `AucunVocabulaireDeMatiereTest`, `FaviconTest`,
`FrontieresDuJavaScriptTest`, `HabillageDesEcransTest`.

Trois constats, dont un que je porte contre moi :

- Le plafond est coherent : quatre noms pour un plafond de quatre, et
  `laDetteNePeutNiGrandirNiPourrir` verifie de surcroit que chacun designe un garde-fou qui existe
  encore. Un garde-fou neuf non eprouve echoue au lieu d'entrer dans la liste.
- **`PLAFOND_DE_DETTE` ne baisse pas dans ce commit, et il aurait pu.** `HabillageDesEcransTest`
  reste exempte alors que je viens de le modifier : il lit toujours `recreo.css` et n'a toujours pas
  de jeu fautif. Lui en donner un est un travail distinct de celui d'`ADR-0015`, et je prefere le
  dire que le laisser croire solde. Dette ouverte, nommee ici.
- Les deux baisses de ce cycle ont ete faites dans le geste qui les meritait, jamais par
  complaisance : chacune a demande un perimetre derive **et** un jeu fautif ecrits dans le meme
  commit.

## 9. Verdict

**Porte architecture : OUVERTE pour la review de s03.**

Motif, sans adoucissement :

- `mvn -B clean verify` est vert sur JDK 21.0.10, 180 tests unitaires et 2 d'integration, 0 echec.
- Les 23 regles ArchUnit s'executent et aucune n'echappe a son contre-exemple, ce qui est etabli par
  un recensement derive et non par ma parole.
- Les quatre proprietes de Clean Architecture du `CLAUDE.md` sont tenues apres les deux fusions ; la
  bibliotheque tierce entree en s03 ne fuit hors d'`adapter-web` par aucun des trois chemins
  verifies.
- Les cinq mutations d'`ADR-0014` mordent toujours sur l'arbre fusionne, y compris apres que `.btn`
  a ete pose sur tous les boutons.
- Les deux dettes nommees sont tranchees dans ce commit, pas reportees, et la seconde l'est apres
  avoir ete **mesuree** verte sur une mutation qui aurait du la faire rougir.

Ce que cette porte ne dit pas, et qu'il faut lire avec elle :

- **Elle ne dit rien du Done pedagogique.** Les deux jeux de francais de s04 n'ont pas ete joues par
  `nadia-prof`. Un increment techniquement irreprochable peut etre refuse pedagogiquement, et
  `bandit-manchot-additions-cp` l'a deja ete.
- **Un garde-fou qui juge un rendu ne mesure aucun pixel.** Toutes les garanties de cette section
  sont des garanties sur des declarations et sur un arbre DOM statique.
- **Deux dettes restent ouvertes et nommees** : `HabillageDesEcransTest` sans jeu fautif (section 8),
  et l'absence de regle exigeant qu'une matiere neuve n'entraine aucune modification de `domain`
  (section 5), volontairement non ecrite faute d'usage constate.

## Ou va la part s04

**`s04` n'a aucun dossier de sprint.** C'est la deuxieme fois apres `s02` qu'un sprint se deroule
sans ceremonie. Je le constate, je ne le repare pas : fabriquer
`squad/sprints/s04-.../confirmation-archunit.md` reviendrait a produire la trace d'une ceremonie qui
n'a pas eu lieu, ce qui est un faux au meme titre qu'une ceremonie sans fichier.

Ma part s04 - la verification du decouplage demande par `DEC-0008`, section 5 - est donc **ecrite
ici, dans le dossier s03, et signalee comme telle** plutot que rangee dans un dossier invente. Quand
`sofia-sm` creera le dossier de cloture de s04, la section 5 de ce document s'y deplace ou s'y cite ;
c'est son geste, pas le mien. Ce constat de methode est remonte a `sofia-sm` pour la retro : deux
sprints sur quatre sans ceremonie n'est pas un incident isole.
