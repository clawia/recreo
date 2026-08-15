# Rapport qualite - sprint 12 (mathematiques CM1)

- **Auteur** : yanis-qa
- **Date** : 2026-08-15
- **Worktree** : dedie, provisionne depuis `round-two` sur une branche neuve (`s12-yanis-qualite`,
  repertoire `recreo-s12-yanis-qualite`), hors du depot principal ou `relecteur-pedagogique`
  travaille en parallele. Aucun push.
- **Commit audite** : `7f6218a`, tete de `round-two` au moment de la saisine (fusion du lot
  `camille-po`, matrice apres les seconds verdicts).
- **Constat JDK** : aucun JDK 21 trouve sur ce poste, dans les emplacements standards comme
  ailleurs (recherche etendue a `Program Files`, aux installations utilisateur et aux outils
  internes). Mesure faite sur un JDK 25 (distribution Temurin) via `JAVA_HOME` pointe dessus pour
  chaque commande citee ci-dessous, jamais ecrit dans un fichier commite. Le pom fixe
  `java.version=21` (compilation en mode `--release 21`) et la compilation comme les tests
  reussissent sur ce JDK 25 sans aucune adaptation.

Ce rapport ne recopie aucun chiffre sans l'avoir rejoue sur un build complet, et ne recopie aucun
garde-fou cite comme fermant un trou sans l'avoir casse puis remis en etat sur du materiau reel.

## 1. Porte technique

```
mvn -B clean verify
```

**Resultat : BUILD SUCCESS**, six modules en SUCCESS, aucun test ignore, execute deux fois
(baseline avant mutations, verification finale apres restauration complete) avec un total
identique les deux fois.

| Module | Tests (agregat Maven, "Results:") |
|---|---|
| domain | 47 |
| application | 7 |
| adapter-web | 226 |
| adapter-content | 98 |
| bootstrap (surefire) | 80 |
| bootstrap (failsafe) | 6 |
| **total** | **464** |

**464 confirme le chiffre annonce par `sofia-sm`**, etabli sur `mvn -B clean verify` complet -
jamais une mesure incrementale (`-pl` seul, sans `-am`), pour les raisons developpees section 4.

**28 regles ArchUnit, confirme egalement** : recherche litterale de `static final ArchRule` sous
`src/test/java` des cinq modules du pom agregateur. 27 sont cablees par `@ArchTest` +
`@AnalyzeClasses` (execution automatique par le moteur ArchUnit) : 1 dans `adapter-web`
(`AssertionsWebTest`), 26 dans `bootstrap` (dix classes de `com.recreo.archi`, dont les 7 regles
neuves de `DecouplageMecaniqueContenuTest`). La 28e, `ScanDeComposantsTest.aucuneClasseDeTest...`,
est une regle ArchUnit reelle mais invoquee a la main par `.check(...)` dans une methode `@Test`
ordinaire, sur un perimetre different (le classpath de test du module bootstrap, ADR-0021) - meme
population de regles, cablage different, a ne pas confondre avec un decompte incomplet.

**Garde-fou de chemins, rejoue avant d'ecrire ce rapport** :
```
git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh
  -> "Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.", EXIT=0
```
Confirme sur mon propre lot, avant tout commit, comme pour chaque sprint precedent.

**Tests instables** : aucun constate sur les deux executions completes de la suite.

## 2. Cinq mutations sur du materiau de production reel, toutes restaurees

Regle acceptee a la cloture de s10 (`squad/README.md`, section 4), appliquee ici aux cinq
garde-fous neufs nommes par la saisine. Chaque mutation est faite sur le fichier de production ou
de test reel, jamais une fixture ecrite pour l'occasion ; `git status --porcelain` est verifie vide
avant la premiere mutation et apres la derniere restauration.

| # | Garde-fou | Mutation | Resultat | Restaure |
|---|---|---|---|---|
| 1 | `AdditionsBorneesContentSource.distracteurs` (fabrique corrigee de `marc-dev`) | clamp `Math.min(nombreDeDistracteurs, bonneReponse)` retire | **vert a tort** - voir le constat ci-dessous | oui |
| 2 | `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.DETTE_DE_RANG` (plafond 2, `lea-dev`) | ajout d'un troisieme nom, taille 3 | **rouge**, `laDetteNePeutNiGrandirNiPourrir` : "size ... to be less than or equal to 2 but was 3" | oui |
| 3 | meme dette | un des deux noms remplace par un paquet qui ne depasse plus le seuil | **rouge** sur 2 tests : la dette perimee ET le garde-fou de production (parce que le nom retire, `cm1-perimetre`, depasse reellement encore le seuil) | oui |
| 4 | `DETTE_DE_SEUIL_MINIMUM` (plafond 3, `lea-dev`) | ajout d'un quatrieme nom, taille 4 | **rouge**, meme message de plafond | oui |
| 5 | meme dette | un des trois noms remplace par un paquet qui ne tombe plus sous le seuil | **rouge** sur 2 tests : la dette perimee ET le garde-fou de production (le nom retire, `cp-complements-10`, tombe reellement encore sous le seuil) | oui |

`git diff --stat` vide et `mvn -B -pl adapters/adapter-content -am test
-Dtest=LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` de nouveau a 8/8 apres chaque
restauration.

### Le constat de la mutation 1, qui ne clot pas la question posee

La question etait double : aucune proposition negative n'est-elle atteignable, et le taux
depend-il de la graine ? J'ai verifie les deux, pas seulement relu la javadoc.

- **Reachabilite reelle du defaut retire** : sur la fabrique mutee (clamp retire), 1417 valeurs
  negatives sur 325 000 tirages (20 graines x 6500 x 2 distracteurs, la meme population que le test
  de rang voisin) - le defaut est bien reel et bien reachable des que `bonneReponse` vaut 1 (le
  seul cas ou `enDessous = 2` produit `1 - 2 = -1`).
- **Le vert etait a tort** : `mvn -pl domain test -Dtest=AdditionsBorneesContentSourceTest` reste
  a 9/9 sur la fabrique mutee. Le test cense l'interdire
  (`auMoinsUnDistracteurPlausibleJamaisNegatifNiNonEntier`) tire sur une seule graine (42) et 50
  manches ; comme la somme 1 (donc "0 + 1"/"1 + 0") est un tirage rare (les deux couples ont un
  operande nul, donc filtres par `ACCEPTATION_OPERANDE_NUL_SUR`), 50 tirages a une seule graine
  n'ont pas suffi a l'atteindre. Ce n'est pas un defaut de la fabrique corrigee (0 negatif sur les
  325 000 memes tirages, une fois le clamp remis) : **c'est une faiblesse du pouvoir de detection
  du test existant sur cette classe de defaut precise**, a nommer plutot qu'a taire.
- **Le taux ne depend pas de la graine, verifie par graine et non par agregat.** Le seul test du
  depot qui couvre cette propriete (`leRangDeLaBonneReponseNEstJamaisMajoritaireEtAucunRangNEstPresqueVide`)
  agrege 20 graines x 6500 tirages en un seul total ; un biais isole sur une graine s'y noierait.
  J'ai recalcule les trois proportions **graine par graine** sur la fabrique corrigee (non mutee) :
  etendue `[32,46 % ; 34,15 %]` (la plus petite), `[32,60 % ; 35,06 %]` (intermediaire),
  `[31,54 % ; 34,02 %]` (la plus grande) sur les 20 graines - toutes dans la bande 30-38 % de
  `nadia-prof`, aucune graine hors bande. La propriete tient graine par graine, pas seulement en
  moyenne.

Remonte a `sofia-sm` comme faiblesse de couverture (pas comme defaut de production) :
`auMoinsUnDistracteurPlausibleJamaisNegatifNiNonEntier` devrait balayer plusieurs graines ou un
volume plus large, sur le modele du test de rang voisin, pour que "jamais negatif" tienne sa
promesse sur les sommes rares plutot que sur un echantillon qui les rate le plus souvent.

## 3. Reponse a la question sur les releves qui mesurent autre chose que ce qu'ils annoncent

**Methode.** Contrairement au critere enumere de s11 (ADR-0013 impose une prose obligatoire des
qu'un critere choisit une liste plutot qu'un perimetre, donc un grep sur cette auto-declaration
enumere mecaniquement tous les candidats), cette classe de defaut n'a **aucun marqueur obligatoire
equivalent**. Rien n'oblige un agent a ecrire "cette mesure ne peut pas voir X" au moment ou il
l'ecrit - `nadia-prof` ne l'a decouvert qu'en relisant son propre releve douze sprints plus tard.
Je ne peux donc pas l'enumerer mecaniquement ; j'ai cherche par lecture cible, pas par un grep qui
ferait autorite :

- vocabulaire de correction ("je credite une propriete pour une autre", "par construction ne peut
  pas voir", "porte sur X, pas sur Y") dans les quatre fiches CM1/additions et dans
  `MATRICE-couverture.md` ;
- comparaison ligne a ligne de chaque `releve-*.json` de `squad/conformite/releves-s12/` avec le
  texte de la fiche qui le cite, pour verifier que chaque champ mesure litteralement ce que la
  fiche lui fait dire (positions de pli en pixels, cible du focus apres Tabulation, rang par valeur
  calcule sur le contenu reellement tire) - aucun ecart trouve : les cinq releves JSON mesurent
  exactement ce que leur paragraphe annonce.

**Une seconde instance trouvee, deja auto-declaree par `nadia-prof` elle-meme dans `review.md`**,
et de la meme forme que celle du sprint 0 : la clause 4 d'`ADR-0040` (`toutEcranAPartieEnCoursDeclareUnPointDeFocus`)
prouve qu'un point de focus est **declare** dans le document - un fait de balisage, verifie par
mutation reelle (retirer `data-focus`/`data-focus-cible` du document effectivement rendu fait
rougir la clause avec le meme message qu'avant le lot, confirme par ma propre lecture du test).
Ce que cette preuve ne touche pas, et que `nadia-prof` nomme elle-meme : **ou la premiere
Tabulation atterrit reellement**, et si ce point recoit un anneau visible pour l'enfant. Ce sont
deux proprietes differentes credibles a tort l'une pour l'autre si on lit "clause 4 verte" comme
"navigation clavier sure" : la premiere est un fait de markup que jsoup peut lire, la seconde est
un comportement de navigateur reel (ordre de tabulation, rendu visuel du focus) qu'aucun outil de
ce depot ne simule (ADR-0025 : pas de WebDriver, pas de moteur de rendu). Seule la mesure au
clavier de `nadia-prof` (`releve-reserve-n-clavier.json`, 69 arrivees) etablit la seconde
propriete, et seulement sur les deux ecrans qu'elle a personnellement rejoues.

**Reponse a la question posee.** J'en ai trouve deux dans ce depot au sens strict demande : celle
de s0 (position a l'ecran credite pour rang par valeur, deja nommee dans la saisine) et celle-ci
(focus declare credite pour focus atteint et visible), toutes deux dans le meme sprint 12 et
toutes deux auto-diagnostiquees par leur autrice plutot que trouvees par moi en premier - je n'ai
fait que les verifier et les qualifier. Je n'ai pas trouve de troisieme instance non declaree
malgre une recherche ciblee sur les cinq fiches et les cinq releves de ce sprint, mais je ne
peux pas dire que la recherche est exhaustive : sans marqueur obligatoire, une lecture cible
borne ce que j'ai regarde, elle ne prouve pas l'absence sur ce que je n'ai pas relu. Je propose
a `sofia-sm`, sans trancher, le meme reflexe que celui que j'avais propose en s11 pour le critere
enumere : a chaque verdict qui s'appuie sur un releve automatique, nommer explicitement la
propriete que ce releve peut structurellement voir et celle qu'il ne peut pas, avant de le citer
comme preuve.

## 4. Les cinq verifications demandees

### 4.1 - Fabrique corrigee de `marc-dev` : aucune proposition negative atteignable, taux independant de la graine

Voir section 2, mutation 1 et son constat. **Sur la production (clamp en place) : 0 valeur
negative sur 325 000 tirages** (20 graines x 6500 x 2 distracteurs). **Le taux ne depend pas de la
graine** : verifie graine par graine (pas seulement en agregat, ce que le test existant du depot ne
fait pas), toutes les 20 graines tombent dans `[31,5 % ; 35,1 %]`, a l'interieur de la bande
30-38 % que `nadia-prof` fixe. Une faiblesse de detection du test voisin est remontee (section 2),
pas un defaut de production.

### 4.2 - Les deux dettes plafonnees ne peuvent ni grandir ni pourrir, et les exemptions portent le motif de `nadia-prof`

**Croissance et putrefaction verifiees par mutation, sur les deux dettes** (section 2,
mutations 2 a 5) : chaque tentative de faire grandir la liste au-dela du plafond rougit sur le
message de plafond exact ; chaque tentative de laisser un nom perime (qui ne depasse plus le
seuil qu'il est cense justifier) rougit sur le message de peremption, et fait au passage
rerougir le garde-fou de production sur le nom qui aurait du rester dans la dette. Les deux
mecanismes mordent dans les deux sens.

**Motif des exemptions, verifie contre `FICHE-bandit-manchot-additions-cp.md`, section 6, et
`MATRICE-couverture.md`** : aucune des cinq n'est une exemption de commodite.

- `DETTE_DE_RANG` (`cm1-perimetre`, `cm1-mesures`) : les deux sont **hors du perimetre de S12-3**
  (l'histoire ne corrige pas un contenu livre par un autre agent sur une autre story) - motif
  ecrit dans la javadoc du test et confirme par les verdicts respectifs de `nadia-prof`
  (`cm1-perimetre` VALIDE SOUS RESERVE, sa propre condition P-6 en cause ; `cm1-mesures` VALIDE
  SOUS RESERVE, hors mandat de la story de rang).
  `cp-additions-jusqu-a-10` est sorti de cette meme dette dans ce sprint, au meme commit que la
  correction de sa fabrique (mutation 3 le confirme : le retirer de l'exemption avant que la
  fabrique soit corrigee aurait rougi, apres, ne rougit plus).
- `DETTE_DE_SEUIL_MINIMUM` (`cm1-perimetre`, `cp-soustractions`, `cp-complements-10`) :
  `cm1-perimetre` parce que `nadia-prof` choisit explicitement de ne pas rouvrir le lot livre (sa
  correction de P-6 ne vaut que pour les paquets a venir) ; `cp-soustractions` et
  `cp-complements-10` parce qu'ils sont **hors du mandat de la story de rang** de ce sprint, avec
  le verdict pedagogique cite pour chacun comme seul motif, jamais "ca arrangeait le plafond".

### 4.3 - Rien d'autre dans ce sprint ne repose sur une mesure incrementale

Recherche de toute commande `mvn` citee dans les quatre fiches de conformite du sprint
(`additions-cp`, `perimetre-cm1`, `conversions-unites-cm1`, `tables-multiplication-cm1`), dans
`ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md` et dans `review.md` : toutes les commandes citees
sont des builds complets depuis un arbre propre (`mvn -B clean package`, `mvn -B package`,
"jar reconstruit ... avec un JDK 21", jamais un `-pl` isole sur un module aval sans `-am`). Aucune
autre mention de reconstruction partielle ou de reutilisation d'artefact du depot Maven local
trouvee. Ma propre methode l'a evite : le chiffre de 464 tests et les cinq mutations ci-dessus
viennent d'un `mvn -B clean verify` complet (deux fois) ou d'invocations `-pl ... -am` qui
reconstruisent systematiquement les modules amont depuis la source, jamais depuis un jar
preexistant du depot local.

### 4.4 - Les deux paquets a exactement 50,0 % : fait a inscrire, pas un risque a traiter aujourd'hui

Rejoue depuis le code de production (pas repris de la table manuelle de `nadia-prof`), meme
logique de rang, meme graine et budget de tirage que le garde-fou : `cp-soustractions` 12/24
intermediaire = **50,0000 %**, `cp-problemes-additifs` 11/22 la-plus-grande = **50,0000 %**. Les
deux chiffres sont exacts a la decimale, confirmes independamment du releve manuel de
`nadia-prof`.

**Fait a inscrire, pas un risque a corriger d'urgence** : `nadia-prof` a ecrit elle-meme "je ne
demande pas de la deplacer, elle est juste" - la barre de 50 % est un **plancher de refus**, pas
une cible de qualite (la cible est 33-37 %), et les deux paquets portent deja une reserve
pedagogique separee sur ce point (leur rang le plus rare, 8,3 % et 18,2 %, est couvert par le
second garde-fou, pas par le premier). Ce que je releve comme fragilite reelle, distincte du
verdict pedagogique : la comparaison stricte (`>`, pas `>=`) fait tenir ces deux paquets sur un
seul item d'ecart (1 item sur 24, 1 item sur 22) - un futur retrait ou ajout d'un seul item sur
l'un des deux, sans lien avec cette propriete, ferait basculer silencieusement le verdict du
garde-fou sans que personne ne rouvre la fiche. Je le nomme pour que l'equipe ne le decouvre pas
sans attribution, comme `nadia-prof` le demande deja dans sa propre fiche - pas comme une nouvelle
condition.

### 4.5 - Ce que la clause 4 ne couvre pas, et ce qui garde l'ecart

Voir section 3. La clause 4 juge qu'un point de focus **est declare** dans le document ; elle ne
juge ni ou la premiere Tabulation **atterrit** reellement, ni si ce point est **visible** (un
anneau peint) pour l'enfant. Verifie par lecture du test
(`AucunArretDeTabulationNEstDestructeurTest.toutEcranAPartieEnCoursDeclareUnPointDeFocus` et
`unEcranDontLePointDeFocusEstRetireFaitEchouerLaClause4`) : la premiere ne verifie que
`pointDeFocusDeclare(...).isEmpty()`, jamais le comportement de tabulation qui suit.

**Ce qui garde l'ecart aujourd'hui : uniquement la mesure au clavier de `nadia-prof`**
(`releve-reserve-n-clavier.json`), et seulement sur les ecrans qu'elle a personnellement rejoues
(69 arrivees, deux etats de partie, l'ecran de confirmation). Rien d'automatise dans ce depot ne le
couvre : pas de WebDriver, pas de moteur de rendu (ADR-0025), et `RecreoJsDeplaceLeFocusTest` ne
simule que le comportement du script apres un echange htmx, sur un DOM ecrit a la main
(`FakeElement`/`FakeDocument`), jamais un ordre de tabulation reel. Chaque ecran neuf qui declare
un point de focus devra donc etre rejoue au clavier par `nadia-prof` pour que cet ecart reste
garde - ce n'est pas un trou qui se referme une fois pour toutes.

## 5. Ce que ce rapport ne couvre pas

- Aucun Done pedagogique : il n'est prononce que par `nadia-prof`. Ce rapport ne prononce, ne
  simule et ne remplace aucun de ses verdicts (`VALIDE SOUS RESERVE` sur le perimetre et les
  conversions, `REFUSE` non rejoue sur les tables, `VALIDE` sur les additions apres correction -
  tous prononces par elle, lus ici, jamais reproduits par moi).
- Aucun jugement sur le niveau scolaire, la plausibilite pedagogique d'un distracteur au-dela de sa
  formule mathematique, ou l'accessibilite au lecteur d'ecran : `nadia-prof` (reserve F11 non
  levee, rappelee dans sa propre fiche) et `ines-design`.
- Aucune tracabilite critere par critere des 27 conditions de l'arbitrage prealable : elle vit dans
  `squad/conformite/ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md` et dans les fiches, pas ici.

## Conclusion

**Vert, constate par moi-meme, sur `round-two` a `7f6218a`** : `mvn -B clean verify`, BUILD
SUCCESS, **464 tests** (agregat Maven sur build complet, rejoue deux fois), **28 regles ArchUnit**
(27 par `@ArchTest`, 1 invoquee a la main sur un perimetre distinct), garde-fou de chemins en
code 0. Cinq mutations sur les cinq garde-fous neufs du sprint, toutes sur du materiau de
production reel, toutes restaurees, arbre propre verifie a chaque etape. Une faiblesse de
detection (pas un defaut de production) remontee sur le test de non-negativite de la fabrique
d'additions. Les deux dettes plafonnees mordent dans les deux sens et leurs cinq exemptions
portent chacune le motif pedagogique de `nadia-prof`. Aucune autre mesure incrementale trouvee
dans les artefacts de ce sprint. Les deux paquets a 50,0 % pile sont un fait a inscrire, pas un
risque a traiter aujourd'hui. La clause 4 reste couverte, pour ce qu'elle ne prouve pas, par la
seule main de `nadia-prof` au clavier - et j'ai trouve une seconde instance, deja auto-declaree,
de la propriete que je cherchais : une mesure creditee pour une autre. Rien de tout cela ne change
le Done pedagogique, qui reste la seule autorite de `nadia-prof`.
