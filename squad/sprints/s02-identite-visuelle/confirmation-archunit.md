# [Sprint s02] Confirmation ArchUnit - theo-archi

- **Date** : 2026-08-05
- **Auteur** : theo-archi
- **Statut de ce document** : rendu **a posteriori**, apres la review. `review.md` porte l'obstacle
  ouvert *"Aucun rapport qualite `yanis-qa` ni confirmation ArchUnit `theo-archi` pour s02 : la porte
  technique n'a pas ete rendue par ses garants"*. Ce fichier solde ma moitie de cet obstacle. Je ne
  pretends pas l'avoir produit avant la review : il ne l'a pas ete, et le dire est plus utile que de
  l'anteponer.
- **Arbre juge** : `round-two`, commit `44469d4` (dernier commit de `yanis-qa` pour s02), plus les
  fichiers du present commit `[THEO]` - `ADR-0013`, son recensement executable et son depot fictif.
- **Ce que je ne fais pas ici** : aucun Done pedagogique, ni son anticipation. Il appartient a
  `nadia-prof`, il est deja rendu pour ce chantier, et rien de ce que je constate ici ne le touche.

## Commande et resultat

```
mvn -B clean verify
```

**BUILD SUCCESS**, code de sortie 0, `Total time: 34.908 s`, sur un JDK 21.0.10 - la version que la
CI utilise. `JAVA_HOME` positionne pour la duree de la commande sur le JDK 21 de ce poste, jamais
ecrit dans un fichier commite ; le `java` par defaut du `PATH` est un JRE 8 qui ne compile pas.

| Module | Tests | Echecs |
|---|---|---|
| `recreo-domain` | 31 | 0 |
| `recreo-application` | 7 | 0 |
| `recreo-adapter-web` | 38 | 0 |
| `recreo-adapter-content` | 5 | 0 |
| `recreo-bootstrap` (unitaires, Surefire) | 56 | 0 |
| `recreo-bootstrap` (integration, Failsafe) | 2 | 0 |
| **Total** | **139** | **0** |

Ecart avec les 132 tests mesures par `sofia-sm` a la cloture : les 7 cas de
`LesGardeFousSontMisALEpreuveTest`, ajoutes par ce commit.

**Un echec de build rencontre en cours de route, et qui n'est pas un defaut du depot.** Le premier
`clean verify` de ce passage a echoue en `spring-boot-maven-plugin:repackage` sur
`Unable to rename 'recreo.jar' to 'recreo.jar.original'`, tous les tests etant verts. Cause etablie
et non supposee : une instance de l'application tournait sur le port 8080 et tenait le fichier
ouvert (`netstat` puis `tasklist` sur le PID). Instance arretee, build relance, vert. Je le consigne
parce qu'il ressemble a un echec transitoire et n'en est pas un : il echoue **apres** les tests, a
l'empaquetage, et son message le nomme.

## Les garde-fous hors Maven

Les trois sont executes, et chacun est teste avant d'etre applique (ADR-0009, ADR-0010).

| Garde-fou | Script juge | Script de test | Resultat |
|---|---|---|---|
| Prefixe de commit par agent | `.github/scripts/verifier-prefixes.sh` | `verifier-prefixes-test.sh` | tous les cas verts, sortie 0 |
| Plage de commits atteignable | `.github/scripts/commit-atteignable.sh` | `commit-atteignable-test.sh` | tous les cas verts, sortie 0 |
| Chemins de poste de travail | `.github/scripts/verifier-chemins.sh` | `verifier-chemins-test.sh` | tous les cas verts, sortie 0 |

Le juge de chemins a aussi ete passe sur le materiau reel, y compris les fichiers neufs de ce
commit : `git ls-files` moins son propre fichier de cas, rendu
`Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.`

## Le compte des regles - et il n'est plus tenu a la main

**23 regles ArchUnit declarees, 23 passees a un `check` sur un contre-exemple.** Ce compte n'est plus
le resultat d'un inventaire de ma part : depuis ce commit, il est **derive** par
`LesGardeFousSontMisALEpreuveTest.aucuneRegleArchUnitNEchappeAuContreExemple`, qui releve toute
declaration `ArchRule <nom> =` dans les sources de test de tous les modules declares au pom, et exige
que chacune apparaisse dans un `<nom>.check(`.

C'est la correction du defaut que je decris au paragraphe suivant, et c'est le motif d'`ADR-0013` :
`LesReglesMordentTest` **enumerait son propre perimetre**, vingt et une methodes ecrites une par une,
et rien n'obligeait la vingt-quatrieme regle a recevoir la sienne. Le garde-fou de nos garde-fous
avait exactement la forme du defaut que `nadia-prof` a mesure deux fois sur l'habillage. Verifie par
mutation : une declaration `ArchRule uneRegleNeuveSansContreExemple =` sans `check` rend ce
recensement rouge en nommant la regle. Mutation annulee.

Aucune regle n'a ete supprimee ni affaiblie pendant s02 : le `git diff` du sprint sur les fichiers de
regles ne touche aucune `ArchRule`.

## Inventaire demande : les garde-fous dont le perimetre est enumere

C'est un inventaire, pas une reecriture. Je ne corrige que ce qui releve de mon mandat ; le reste est
nomme et route a son proprietaire.

### A. Perimetre enumere - trou silencieux, a fermer

| Garde-fou | Ce qui est enumere | Ce qui echappe aujourd'hui |
|---|---|---|
| `TailleDesTextesEtBoutonsTest.boutonsActionnables()` | quatre gabarits, `resolve(...)` un par un | un cinquieme gabarit. `yanis-qa` a ferme la derivation par les classes des boutons (`164b662`, bonne correction) ; le cran au-dessus - **quels gabarits sont ouverts** - reste ecrit a la main |
| `TailleDesTextesEtBoutonsTest.aucunTexteNePasseSousLaTailleDeLaConsigne` | six selecteurs de texte | un septieme selecteur de texte lu par l'enfant |
| `TexteAccentueDesGabaritsTest` | trois gabarits sur les quatre existants | **`fragments/erreur.html` n'est pas dans la liste**, alors que l'ecran d'erreur est l'un des trois motifs du refus pedagogique de s00. Une faute d'accentuation y reviendrait sans un rouge. C'est le trou le plus concret de cet inventaire |
| `HabillageDesEcransTest.chaqueEcranNuPorteAuMoinsUneRegleDeCouleurDeFondOuDEspacement` | onze selecteurs d'ecran | un ecran neuf n'est pas habille-verifie |
| `ChargementDuContenuTest.ilNYAQuUnSeulChargeurDeContenu` | la population est filtree par suffixe de nom (`Loader`, `ContentPack`) | un second chargeur nomme autrement. Attenue, pas ferme, par `seulLAdaptateurDeContenuLitDesFichiers`, qui derive son perimetre et refuse a tout le monde hors `adapter-content` de toucher au disque |
| `LesReglesMordentTest` | vingt et une methodes, une par regle | **ferme par ce commit** : le recensement 2 d'ADR-0013 |

Les cinq premieres lignes appartiennent a `yanis-qa` et `ines-design`. Je ne touche pas a leurs
tests ; elles sont inscrites nommement dans la dette de `LesGardeFousSontMisALEpreuveTest`, ou elles
seront rouges des qu'elles en sortiront sans jeu fautif.

### B. Perimetre derive - la forme juste, et il y en a deja dans le depot

- `FrontieresDuJavaScriptTest` : `Files.walk` sur `static/js` et sur `templates`. C'est le meilleur
  exemple du depot ; un gabarit neuf est juge du jour ou il est ecrit, et c'est ce qui a fait que le
  lot `ines-design` de s01 etait couvert sans qu'aucune regle ne bouge.
- `AucunVocabulaireDeMatiereTest` : balayage des sources de `domain/mechanics`.
- `ToutesLesMecaniquesSontTesteesTest` : population = les classes assignables a `GameMechanic`, pas
  une liste de mecaniques.
- Les regles ArchUnit a predicat de paquet (`com.recreo.domain..`, `com.recreo.application..`) : une
  classe neuve dans un paquet garde est jugee sans qu'on l'inscrive nulle part.
- Le job CI `chemins` : `git ls-files`, c'est-a-dire tous les fichiers suivis. Un fichier neuf est
  juge sans qu'on y pense.

### C. Criteres enumeres - listes assumees, dont l'oubli fait un rouge et non un vert

Ceux-la ne sont **pas** vises par ADR-0013, et il faut le dire pour que personne ne parte les
reecrire.

- `verifier-prefixes.sh`, la liste `TAGS` des neuf tags d'agent : un agent neuf voit son commit
  **refuse bruyamment**, ce qui est exactement le bon sens de l'erreur - c'est ce qui s'est passe a
  l'ouverture du tag `[INES]` en s01. Un point de vigilance qui n'est pas le silence mais la
  divergence : cette liste est ecrite en **trois** endroits (le script, `CLAUDE.md`,
  `squad/README.md` section 8). Rien ne verifie qu'elles disent la meme chose. Ce n'est pas un trou
  de garde-fou, c'est un point de gouvernance documentaire : je le route a `sofia-sm` sans le
  trancher.
- `verifier-chemins.sh`, les quatre formes de chemin refusees : liste noire, une cinquieme forme
  passerait en silence. Inevitable - "toutes les formes de chemin de poste de travail" ne se derive
  pas - et son **perimetre**, lui, est bien derive.
- `PureteDesCouchesTest`, la liste blanche de paquets JDK autorises : une dependance externe neuve
  est refusee par defaut. Liste blanche, donc fail-closed.
- `AbstractionsCiteesTest`, les trois types nommes explicitement : ce sont des regles d'existence
  (ADR-0005), la faute qu'elles gardent est une **absence**, il n'y a rien a deriver.
- `FrontieresDuJavaScriptTest`, les listes `INTERDITS_JS` et `MARQUEURS_DE_CORRECTION` : listes
  noires de motifs, avec le meme trou assume que ci-dessus, sur un perimetre derive.

### D. Un fait de terrain, sans consequence sur le build

`.claude/worktrees/` contient des copies non suivies d'arbres de modules d'anciens lots paralleles,
avec leurs `src/test/java`. Un recensement qui balaierait le disque a partir de la racine les
compterait comme du code du depot. Celui d'ADR-0013 ne les voit pas : il derive sa liste de modules
du pom agregateur, donc du build reel. Je le note parce que c'est precisement le genre de fausse
population qu'un balayage naif ramasse.

## La dette de l'echec transitoire de s01 - soldee ici, et non expliquee

Rappel du fait, tel que je l'ai ecrit a la cloture de s01 (`confirmation-archunit.md` et
`avis-architecture-cloture.md` de s01) : un `clean verify` a rendu `Tests run: 21, Failures: 2` sur
`LesReglesMordentTest`, puis vert a l'execution suivante sans qu'une ligne change ; huit tentatives
de reproduction vertes ; **rapport Surefire deja ecrase**, donc les deux cas fautifs inconnus.

**Protocole de ce passage, pour qu'il soit comparable et pas seulement affirme.** Un
`mvn -B clean verify` complet, puis **12 executions ciblees** de `LesReglesMordentTest` a la suite,
en archivant le rapport Surefire **apres chaque execution** au lieu de le laisser s'ecraser - c'est
la lacune qui m'avait empeche de conclure en s01, et elle est corrigee par le protocole, pas par la
chance.

**Resultat : `Tests run: 21, Failures: 0` sur les 12 rapports archives, plus le passage complet.**
Soit 273 executions de cas de cette classe, aucun echec.

**Verdict, sans broder.** L'echec **n'a pas ete reproduit**. Il **reste inexplique** : je ne sais
toujours pas quels deux cas ont echoue, et je n'invente pas de cause. L'hypothese que j'avais nommee
en s01 - une seconde construction Maven concurrente dans le meme `target/`, dans un arbre partage ou
d'autres agents ecrivaient - reste **une hypothese**, que ce passage n'a ni confirmee ni infirmee :
je n'ai pas cherche a la reproduire en lancant deux constructions concurrentes, parce que le faire
dans un arbre ou `yanis-qa` et `camille-po` travaillent aurait detruit leur travail.

Ce qui a change depuis s01 et qui vaut d'etre note : ce sprint a vu **quatre** `mvn -B verify`
independants rapportes verts par quatre agents differents (`ines-design`, `nadia-prof`, `sofia-sm`,
et le mien), plus la CI. L'echec n'a jamais reapparu. Je maintiens la ligne de s01 : si la CI, qui
construit un checkout neuf sans ecrivain concurrent, le fait reapparaitre un jour, c'est un defaut
reel de ces tests et il faudra le traiter comme tel. Tant qu'il ne reapparait pas, c'est une
observation isolee, datee, et desormais instrumentee.

**`yanis-qa` regarde le meme point de son cote. Nos deux constats sont faits pour etre compares, pas
fondus** : le mien porte sur `LesReglesMordentTest` seul, avec le protocole ci-dessus, sur
`44469d4`. Si le sien porte sur un autre perimetre ou un autre protocole et conclut autrement, c'est
une information, pas une contradiction a arbitrer. Nos deux conclusions se rejoignent - pas de
reapparition, cause non identifiee, aucune cause inventee - et nos deux protocoles different : il
rapporte quatre executions, j'en rapporte treize avec archivage systematique du rapport. Un ecart de
chiffre a comparer et non a trancher ici : son rapport (section 2) compte **22** cas dans
`LesReglesMordentTest`, mes treize rapports archives en comptent **21**. Je donne le mien tel que je
l'ai mesure, avec les rapports a l'appui ; qui refera la mesure verra lequel tient.

## Ce que ce vert ne prouve pas

- Il ne prouve pas qu'un critere visuel non teste est tenu. Six garde-fous de balayage n'ont pas
  encore de jeu fautif ; ils sont nommes et plafonnes, pas absous.
- Il ne prouve pas que les jeux fautifs a venir seront pertinents. ADR-0013 garantit qu'un garde-fou
  se confronte a un jeu fautif, pas que ce jeu couvre toutes les fautes imaginables. Meme niveau de
  garantie qu'ADR-0003, et meme limite.
- Il ne dit rien de la qualite fonctionnelle de l'increment : c'est le rapport de `yanis-qa`.
- Il ne prononce aucun Done pedagogique.
