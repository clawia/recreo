# `S21-1` - correctif de l'outillage de mesure du pli

- **Auteur** : `theo-archi`. **Date** : 2026-08-27. **Branche** : `s21-theo`, worktree dedie hors
  de l'arbre du depot, forkee de `round-two` a `caad2f0`.
- **Ce que ce lot est** : le correctif des trois defauts que j'ai trouves le matin meme et decrits
  dans `avis-faisabilite.md`, section 2.4. C'est un lot d'outillage : il ne touche aucune ligne de
  production, aucun contenu, aucun gabarit, aucune feuille de style.
- **Ce que ce lot n'est pas** : un garde-fou du pli. `ADR-0028` declare le contenu au-dessus du pli
  non gardable, son declencheur de reouverture n'est pas survenu, et je ne le rouvre pas. Ce qui
  est corrige ici, c'est l'**instrument** qui constate le pli, pas la propriete.
- **Convention de chiffres tenue dans tout le document** : chaque chiffre porte sa methode, son
  perimetre et sa date dans la meme phrase.

## 1. Le defaut, et son mode de defaillance

`squad/outils/nadia-prof/balayage-largeur-rangee.py` est le module dont derivent, par import, tous
les outils de mesure du pli du depot : `auditer-paquet.py`, `budget-de-forme.py`,
`confirmer-largeur-dans-la-bande.py`, `lire-le-pli-a-l-ecran.py`,
`lire-le-recouvrement-a-l-ecran.py`, `mesurer-budget-vertical.py`, `mesurer-candidats-rep-8.py`,
`mesurer-cout-mot-designe.py` - **huit fichiers, comptes le 2026-08-27 par
`grep -rn "balayage-largeur-rangee.py" squad/outils/ --include=*.py` dans le worktree `s21-theo`**.

Trois defauts, une seule cause de fond : **ces outils decrivaient leurs conditions de mesure par
leurs parametres, jamais par une lecture de ce qui s'est reellement passe.**

1. **Le port de deverminage etait fixe (9319) et le script s'attachait a ce qui repondait.** Quand
   le port etait deja tenu, le Chrome fraichement lance n'arrivait pas a s'y attacher et le client
   se raccrochait, **sans le dire**, au navigateur d'un autre agent.
2. **La regle CSS injectee etait posee puis supposee encore la.** Une navigation complete recree le
   `<head>` et la jette ; un rapport pouvait donc melanger des items mesures avec la regle et des
   items mesures sans.
3. **Le champ `viewport` des rapports etait recopie de l'argument.** Un releve pouvait porter
   `744 x 551` alors que les pixels venaient d'une autre fenetre.

**Le mode de defaillance n'est pas l'echec, c'est l'ecart muet** - la famille que `CLAUDE.md`
decrit pour un `-pl` sans `-am`. Le 2026-08-27, il s'est manifeste sous sa forme la plus couteuse :
**8 jeux sur 17 perdus** dans ma premiere campagne, sans qu'aucun message ne les nomme.

## 2. Ce que le correctif change, defaut par defaut

### 2.1 Le port n'est plus fixe, il est attribue et relu

**Le geste principal n'est pas un juge, c'est la suppression de la ressource partagee.**
`PORT_DEVERMINAGE` passe de `9319` a **`0`** : le systeme attribue un port libre, et le script lit
lequel dans le fichier `DevToolsActivePort` de **son propre profil** - un repertoire temporaire cree
par cette execution et par elle seule. Deux campagnes simultanees sur le meme poste ne peuvent plus
se rencontrer, non parce qu'on le detecte, mais parce qu'elles n'ont plus rien en commun.

**Un fait mesure qui a corrige ma premiere version, et je l'ecris parce qu'il est exactement la
lecon de s05.** J'avais d'abord ecrit le juge sur une fixture de ma main : garder le port fixe et
exiger que le profil declare **ce** port. La regle passait tous ses cas fabriques - vingt-quatre a ce moment-la - et **echouait
sur du materiau reel des la premiere execution** : Chrome n'ecrit `DevToolsActivePort` que lorsque
le port lui est **attribue**, jamais lorsqu'un port lui est impose (verifie le 2026-08-27 sur
Chrome 151.0.7922.174, les deux modes essayes cote a cote sur le poste de mesure). Une fixture
ressemble a ce que son auteur imagine ; le monde ecrit autre chose. C'est ce constat qui a fait
passer le port de "fixe et verifie" a "attribue et relu", et c'est la meilleure des deux
conceptions - elle a moins de pieces mobiles et elle rend la collision impossible au lieu de la
detecter.

**Un port force reste possible** (`PORT_DEV` non nul, encore lu par trois outils) et il est garde
par la sonde d'avant lancement : le port est interroge **avant** que le script lance quoi que ce
soit, et une reponse est un refus. Cette garde-la n'est pas une preuve de provenance, seulement une
forte presomption, et le module le dit dans sa documentation plutot que de laisser croire l'inverse.

### 2.2 La regle injectee est relue a chaque lecture

`LIRE_ETAT` rend desormais, a cote de la mesure, le texte du noeud `#mesure-nadia` tel qu'il se
trouve dans le document a cet instant - `null` s'il a disparu. `Navigateur.poser_regle` retient ce
qui a ete pose, `Navigateur.verifier_regle` compare, et un ecart est un refus.

**Ce que ce juge ne rend pas rouge, et c'est delibere** : un outil qui n'injecte rien
(`mesurer-budget-vertical.py`, dont le rapport porte `plafondInjecte: null`) n'est pas concerne. Une
regle qui rougirait sur un outil sain serait le motif de refus d'`ADR-0028`.

`lire-le-pli-a-l-ecran.py` et `lire-le-recouvrement-a-l-ecran.py` posaient leur regle sous un noeud
anonyme ou sous un identifiant a eux : les deux passent a `poser_regle` et sont donc verifies.
`confirmer-largeur-dans-la-bande.py` **repose sa regle avant chaque lecture** et n'a pas besoin de
ce juge - je le note plutot que de lui ajouter un controle qui ne garderait rien.

### 2.3 Le viewport est lu du navigateur

`Navigateur.regler_viewport` pose la metrique puis **lit** `[window.innerWidth, window.innerHeight]`
et refuse si les deux couples different. Six rapports changent : le champ `viewport` porte desormais
la **lecture**, et un champ `viewportDemande` neuf porte l'argument. Les deux sont ecrits, pour
qu'un ecart futur soit lisible dans le releve et pas seulement dans un refus.

`lire-le-recouvrement-a-l-ecran.py` lisait deja son viewport (`viewportLuDuNavigateur`, depuis s20)
mais par `documentElement.clientWidth`, qui n'est pas la meme grandeur : ce champ garde son nom et
son sens, la lecture par `innerWidth` s'y ajoute, et l'ancienne valeur passe sous
`largeurDeMiseEnPageLue`. **Aucun releve deja commite ne devient faux** : au viewport 744 x 551 avec
`--hide-scrollbars`, les deux grandeurs sont egales.

### 2.4 Le quatrieme geste, qui n'est pas dans l'enonce de la story et sans lequel les trois autres
ne ferment rien

`squad/outils/theo-archi/campagne-budget-vertical.sh` **ignorait le code de sortie de chaque mesure**
et ecrivait `campagne terminee` quoi qu'il arrive. C'est cette ligne-la, et non les trois juges, qui
a fait disparaitre les 8 jeux sur 17 : un refus bruyant dans le script python que la campagne avale
reste un ecart muet a l'echelle de la campagne.

La campagne compte desormais ses mesures, considere un jeu comme **perdu** si sa mesure sort en
erreur **ou** si elle n'ecrit pas de rapport lisible, **nomme les jeux perdus un par un** et **sort
en erreur**. Le nombre d'items vus, lui, reste **declare et non juge** : un tirage voit
legitimement moins d'items que le paquet n'en porte - 23 sur 24 sur `cp-soustractions` le
2026-08-27 - et en faire un echec serait un rouge sur une mesure saine.

## 3. Ce que ces regles interdiraient reellement a quelqu'un qui essaierait de mal faire

La question doit etre posee, sans quoi une regle est decorative.

| Regle | Ce qu'elle interdit reellement | Ce qu'elle n'interdit pas |
|---|---|---|
| Port attribue + relu du profil | Rendre un chiffre issu d'un navigateur qu'on n'a pas lance | Qu'une mesure soit fausse pour une autre cause |
| Sonde d'avant lancement (port force) | S'attacher a un port deja tenu par un autre agent | La course entre deux forcages simultanes du meme port |
| Regle injectee relue | Rendre un rapport dont une partie des items a ete mesuree sans la regle | Qu'une regle injectee soit la mauvaise regle |
| Viewport lu | Ecrire dans un releve un viewport qu'on n'a pas constate | Qu'un viewport constate soit le bon pour la question posee |
| Campagne comptable | Se declarer terminee en ayant perdu des jeux | Qu'une campagne complete mesure la bonne chose |

## 4. La preuve : les juges d'abord, le monde ensuite

### 4.1 Les cas fabriques - `squad/outils/nadia-prof/balayage-largeur-rangee-test.sh`

**29 cas, tous verts**, le 2026-08-27, sur la branche `s21-theo` : `bash
squad/outils/nadia-prof/balayage-largeur-rangee-test.sh`, sortie `Tous les cas passent`, code 0.
Comme pour un contre-exemple ArchUnit, chaque cas attendu en refus verifie **un fragment du
message**, pas seulement l'existence d'un refus.

### 4.2 La morsure, prouvee par cinq mutations reelles, chacune relancee

Methode : une mutation a la fois, arbre restaure et verifie propre apres chaque essai, le
2026-08-27, dans le worktree `s21-theo`.

| Mutation du juge | Cas qui deviennent rouges |
|---|---|
| `juger_port_libre_avant_lancement` rend toujours `None` | **3** : le port cite, la reponse citee, la reponse vide |
| la comparaison de port de `juger_port_du_profil` devient `if False` | **1** : le `DevToolsActivePort` qui declare le port 0 |
| `juger_regle_injectee` ne voit plus la disparition (`if False`) | **2** : la regle disparue, et le motif qui nomme la navigation |
| `juger_viewport` ne compare que la largeur | **1** : l'ecart de hauteur seule |
| la campagne ignore de nouveau le code de sortie (`if false`) | **2** : la sortie en erreur avec rapport lisible, et le code cite |

**Ce que la cinquieme mutation m'a appris contre moi.** Dans sa premiere forme, elle ne faisait
rougir **aucun** cas : le seul cas de campagne que j'avais ecrit etait attrape **par les deux
conditions a la fois** - la mesure echouait *et* n'ecrivait aucun rapport. Une condition attrapee
par une autre n'est prouvee par personne. Le cas ajoute n'exerce que le code de sortie :
l'interpreteur fabrique ecrit un rapport parfaitement lisible, puis sort en erreur.

### 4.3 Le materiau reel, parce qu'une fixture n'est pas le monde

Trois executions sur l'application servie depuis le fat jar de cet arbre, le 2026-08-27, viewport
744 x 551, Chrome 151.0.7922.174.

| Execution | Attendu | Mesure |
|---|---|---|
| Nominal, port attribue, `bandit-manchot-synonymes-cm1` | un rapport, viewport lu | **code 0**, `itemsVus: 10`, `parties: 1`, `viewport [744, 551]` **lu du navigateur**, `viewportDemande [744, 551]` |
| Un navigateur etranger tient le port 9319, `PORT_DEV=9319` | refus bruyant | **code 1**, `MesureNonFiable: port de deverminage 9319 deja pris : il repond avant que ce script ait lance un navigateur. Mesure refusee` - la reponse recue est citee dans le message |
| Deux mesures **simultanees** en mode par defaut, pendant que le port 9319 reste tenu par l'etranger | deux rapports distincts | **code 0 et code 0**, deux enonces differents (`Ce desert de sable est tres "grand".` / `Cette piece est "claire" le matin.`), `viewport [744, 551]` dans les deux |

La troisieme ligne est celle qui compte : c'est le scenario que `sofia-sm` a explicitement ecarte au
planning (decision 2, parallelisation des audits) parce que l'outil ne le supportait pas. Il le
supporte maintenant. **Je ne rouvre pas sa decision** - elle porte aussi sur la capacite humaine de
`nadia-prof`, qui reste seule a jouer chaque item, et cette part-la n'est pas de mon ressort.

### 4.4 Les juges entrent en CI, et l'etape qui les balaie devient comptable

`ADR-0025` section 3 avait ouvert une etape `outils` qui balaie `outils/*-test.sh`, **perimetre
derive du repertoire**. Le meme constat s'est reproduit un cran plus bas : les outils de mesure
vivent sous `squad/outils/<agent>/`, et le seul test qui s'y trouvait,
`squad/outils/sofia-sm/verifier-provenance-lots-test.sh`, **n'etait lance par aucun automate depuis
sa creation** (verifie le 2026-08-27 par lecture de `.github/workflows/ci.yml`, seul workflow du
depot). L'etape balaie desormais les deux repertoires - **6 juges purs**, comptes par un essai a
blanc du meme motif dans le worktree `s21-theo` le 2026-08-27 - et **echoue si un motif ne rend
aucun fichier**, parce qu'un balayage vide est vert et ne prouve rien. `ADR-0025` porte
l'amendement date, son entree d'index aussi.

## 5. Ce que je n'ai pas fait, et pourquoi

- **Je n'ai pas touche `squad/outils/ines-design/mesurer-candidats-rep-8.py`.** Son defaut propre -
  la regle injectee une seule fois avant la premiere partie - est la story `S21-7`, affectee a
  `ines-design`. Il herite du port attribue et du viewport lu par la classe mere ; le juge de regle
  injectee est a sa disposition, c'est a elle de le brancher.
- **Je n'ai pas touche `outils/mvn-jdk21.sh`** : hors perimetre de s21 par la decision 4 du
  planning. Ce qu'il faudrait y faire est en section 6.
- **Je n'ai pas ecrit d'`ADR`.** Ce lot n'introduit aucune decision d'architecture neuve : il
  applique `ADR-0009` (un juge pur teste, une enveloppe prouvee a la main) et `ADR-0025` (perimetre
  derive du repertoire), et il amende ce dernier la ou son perimetre etait trop etroit. Un `ADR`
  ecrit apres coup pour documenter un correctif deja garde serait de la paperasse, pas une
  decision.
- **Je ne prononce aucun statut de couverture et aucun Done**, ni technique ni pedagogique.

## 6. Ce qu'il faudrait faire a `outils/mvn-jdk21.sh` - route, non fait

La decision 4 du planning garde ce correctif hors de s21, et je m'y tiens. Voici ce qu'il faudra y
faire, pour que la story soit ecrite sans re-instruction :

1. **Le script doit cesser de pretendre nommer la toolchain de Maven.** Sa ligne
   `Maven lance sur le JDK de la toolchain jdk 21 : <version>` affirme un raccordement qu'il ne
   verifie pas : il lit `${HOME}/.m2/toolchains.xml` du shell POSIX, Maven resout sa toolchain sur
   `${user.home}` de la machine virtuelle Java, et sur le poste de mesure ce sont deux fichiers
   declarant deux JDK differents (verifie le 2026-08-27 : le fichier du shell declare un JDK 25.0.2,
   celui que Maven lit un JDK 21.0.10, et le journal du build montre bien ce dernier retenu). Son
   seul role defendable est de poser une machine virtuelle Maven acceptable.
2. **Il doit refuser au lieu d'imprimer.** Il a la version en main, il l'affiche, et il ne la compare
   a rien. Le juge manquant - "cette version convient-elle a la version exigee" - est pur et se teste
   par entrees fabriquees ; `extraire_version_java` existe deja dans `outils/verifier-jdk.sh`, deja
   couverte par `outils/verifier-jdk-test.sh`, deja executee par la CI. Cout : une fonction, quelques
   cas.
3. **`outils/verifier-jdk.sh` porte le meme defaut, un cran plus bas, et il n'a pas ete nomme
   jusqu'ici** : son champ `toolchains lu par Maven` est renseigne en regardant
   `${HOME}/.m2/toolchains.xml`, donc il repond sur un autre fichier que celui dont il parle. C'est
   ce champ que quatre `confirmation-archunit.md` citent comme preuve (`S21-6`). La correction est de
   la meme famille et devrait tenir dans la meme story.
4. **Ce qui me ferait changer d'avis sur le point 1** : une machine ou `$HOME` et `user.home` seraient
   garantis egaux rendrait le raccordement vrai et la simplification inutile. Rien ne le garantit
   aujourd'hui sur ce poste, et c'est precisement ce qui a rendu douze rapports inexacts.

## 7. Ce que je route

- **`sofia-sm`** : `S21-1` est fait et prouve ; les stories `S21-9` a `S21-11` ne sont plus retenues
  par lui. Le correctif de `outils/mvn-jdk21.sh` reste ouvert et route (section 6), avec un
  quatrieme element qu'elle n'avait pas quand elle a tranche : `outils/verifier-jdk.sh` porte le
  meme defaut.
- **`ines-design`** : le juge `juger_regle_injectee` est ecrit et teste ; `S21-7` n'a plus qu'a
  l'appeler. Le port attribue et le viewport lu, elle les a deja par heritage.
- **`nadia-prof`** : ses outils refusent desormais au lieu de se taire. Un refus qu'elle rencontrerait
  en pleine campagne n'est pas une panne d'outil, c'est l'outil qui fait son travail - et le message
  nomme la cause et la marche a suivre.
