# ADR-0025 - Rendre toute mesure de rendu rejouable par une commande versionnee, et declarer non gardable le debordement de mise en page

- **Statut** : Accepte
- **Date** : 2026-08-10
- **Auteur** : theo-archi
- **Sprint** : s06 (cloture)

## Contexte

L'obstacle m'est route par `ines-design` puis `yanis-qa`, et `sofia-sm` l'a inscrit sans le trancher.
Il porte sur une phrase precise : **`ViewportEtLargeurTelephoneTest` derive sa population des largeurs
declarees en pixels, et un debordement de flex n'en est pas une.** C'est exact, et il faut dire de quoi
c'est le symptome.

**Les faits etablis par `yanis-qa`** (rapport de qualite s06, points 1 a 4), que j'ai remesures avant
d'ecrire plutot que repris :

- `chrome --headless --window-size=<L>` impose un plancher : pour tout `L` demande sous 516, la page
  est mise en page comme si le viewport valait **500 px**, et le PNG est un **decoupage a gauche** de
  cette mise en page. Ma remesure, par une methode differente de la sienne - `--dump-dom` sur une page
  qui ecrit `window.innerWidth`, la ou il mesurait la boite englobante d'un texte sur l'image - donne :
  320, 400, 500 et 515 demandes rendent tous `innerWidth = 500` ; 516 rend 500 ; 520 rend 504 ; 600
  rend 584. Les deux methodes concordent, et la valeur 516 est exactement le premier `L` pour lequel
  `L - 16` atteint 500, `16 px` etant la reserve de barre de defilement. Le plancher est donc reel et
  sa borne est nette.
- Second piege, distinct : sans `--user-data-dir` dedie, l'invocation s'attache a une session Chrome
  deja ouverte sur le poste et ne mesure plus un rendu headless.
- Consequence mesuree par `yanis-qa` : la commande fautive **reproduit le symptome signale** (l'entree
  de menu absente du bandeau a 360 px) sans qu'aucune propriete CSS ne deborde reellement, et deux
  mesures independantes par une methode saine ne trouvent aucun debordement a une largeur reelle de
  360 px sur ce code.

**La cause racine qu'il nomme, et que je retiens** : aucune des deux verifications de rendu du sprint
n'a laisse de commande citable dans le depot. Une mesure dont la commande n'est ecrite nulle part n'est
pas rejouable - donc ni refutable, ni comparable, et un symptome fabrique par l'outil ne se distingue
pas d'un defaut du produit. C'est le meme defaut de forme qu'`ADR-0009` a ferme pour les garde-fous de
CI, applique cette fois a la mesure.

**Ce que cet ADR ne fait pas, et pourquoi je l'ecris ici plutot que de le laisser deviner.** Il
n'ecrit aucun garde-fou du debordement a 360 px. Ce defaut **n'est pas etabli** : `sofia-sm` a sorti la story du
sprint pour ce motif exact, en refusant de fabriquer un motif de remplacement, et je ne vais pas en
fabriquer un par un automate. Un garde-fou ecrit pour un defaut non
etabli est un garde-fou dont personne ne peut dire s'il mord sur quoi que ce soit de reel.

## Decision

**Deux gestes, et le premier est un refus assume.**

### 1. Le debordement de mise en page est declare non gardable par un automate de ce depot

"Aucun element ne sort du cadre visible a une largeur donnee" est une propriete du **rendu**, pas de la
declaration. Les memes declarations debordent ou non selon la police installee, la longueur exacte du
texte et l'algorithme de mise en page du navigateur. **Aucune analyse statique ne l'atteint**, et le
perimetre de `ViewportEtLargeurTelephoneTest` n'est donc pas a elargir : il est complet pour ce qu'il
annonce, et sa Javadoc dit desormais ce qu'il ne peut pas voir.

**Cette propriete appartient a `nadia-prof`**, comme les autres proprietes de rendu que ce depot lui a
deja confiees (`ADR-0014`, `ADR-0017`, `ADR-0022`). Elle est la seule autorite qui la constate, en
jouant. Ce que cet ADR lui doit, et lui livre, c'est le moyen de la constater de facon **rejouable** :
toute mesure qu'elle demande ou qu'un agent lui presente passe par la commande versionnee du geste 2.
Un rapport qui affirme une largeur de rendu sans citer cette commande n'est pas une mesure ; c'est une
impression, et elle ne fonde ni un refus ni une acceptation.

**Ce qui me ferait revenir sur ce refus** : le pilotage de Chrome par le protocole DevTools
(`Emulation.setDeviceMetricsOverride` puis `Runtime.evaluate` sur `getBoundingClientRect`) rendrait la
propriete assertable en pixels, sans capture ni retouche de gabarit - c'est la piste que `yanis-qa`
nomme pour moi, et elle est juste techniquement. Je l'ecarte **aujourd'hui** pour trois raisons
chiffrables, pas par gout : elle ajoute un client WebSocket aux dependances du depot ; elle rend la
suite dependante d'un binaire externe (un navigateur) que `mvn verify` ne peut pas garantir, or un test
qui se met en veille quand le binaire manque est un vert qui ne prouve rien - le mode de defaillance
que ce depot combat depuis `ADR-0013` ; et une assertion en pixels sur du texte rendu depend des
polices installees, donc exigerait une image figee (conteneur, polices epinglees) pour ne pas etre
instable d'une machine a l'autre. Le jour ou le depot porte deja un conteneur de build, le calcul
change et cet ADR doit etre repris.

### 2. Toute mesure de rendu passe par une commande versionnee, qui refuse les invocations trompeuses

`outils/mesurer-rendu.sh` porte la seule invocation citable. Il applique la methode iframe de
`yanis-qa` - la largeur CSS d'une iframe n'est pas soumise au plancher de fenetre, c'est elle qui sert
de viewport a la page chargee dedans - et il **refuse** structurellement les deux pieges etablis :

- une fenetre parente sous le plancher de 516 px ;
- une invocation sans profil isole.

Il n'est **pas** un garde-fou de CI et ne s'execute pas dans `mvn verify` : il a besoin d'un
navigateur, que rien dans le depot ne garantit. C'est un outil de mesure pour un agent. Ce qui est
teste, c'est son **verdict d'invocation**, par la meme coupure qu'`ADR-0009` impose et que
`verifier-lignee.sh` applique : un juge pur (`juger_invocation`), teste par des entrees fabriquees ; une
enveloppe qui touche le poste, prouvee une fois a la main.

## Regles executables

### 1. `outils/mesurer-rendu-test.sh` - le juge d'invocation

Seize cas, dont le couple qui fixe l'inegalite du plancher. **Preuve de morsure par trois mutations
reelles du juge**, chacune relancee :

| Mutation | Resultat attendu | Resultat mesure |
|---|---|---|
| `PLANCHER_FENETRE_PX` porte de 516 a 0 | les refus sous le plancher tombent | **4 cas en echec** : "un pixel sous le plancher", "la commande fautive du sprint (360 px)", "une fenetre de 200 px", et - non prevu par moi - "un profil Windows n'est jamais recopie dans un refus", qui obtient son refus par le plancher et devient donc une acceptation |
| `-lt 516` remplace par `-le 516` (relecture adverse de l'inegalite) | le bord bas du plancher est refuse a tort | **2 cas en echec** : "une fenetre parente egale au plancher est acceptee", "une largeur CSS egale a la fenetre parente" |
| controle du profil isole supprime | l'absence de profil passe | **1 cas en echec** : "l'absence de profil isole est refusee" |

Le quatrieme echec de la premiere mutation est note parce qu'il m'a corrige : j'annoncais trois cas et
j'en ai mesure quatre. Un cas de test qui verifie une propriete de **message** doit obtenir son refus par
un motif, et le mien l'obtenait par le plancher - la dependance n'etait pas visible en le lisant. Elle
est sans consequence sur le verdict du juge, mais elle montre qu'une mutation mesuree dit des choses
qu'une mutation racontee ne dit pas.

La seconde mutation est celle qui compte le plus, et c'est la lecon du plafond de dette d'`ADR-0016`
appliquee a moi-meme : **516 doit etre accepte et 515 refuse**. Une inegalite large ou stricte au
mauvais sens rejetterait une mesure honnete a la borne exacte, et aucun cas nominal ne le dirait. Les
deux bords sont epingles par deux cas distincts.

Le dernier cas du fichier est un test negatif global : aucune des quinze sorties collectees ne contient
de chemin de poste de travail, y compris quand le profil qu'on lui passe **est** un chemin de poste
(`ADR-0010` applique a la sortie d'un outil, comme pour `verifier-jdk.sh`).

### 2. L'enveloppe, prouvee une fois a la main sur l'application reelle

Ce que le juge ne couvre pas - que les options Chrome soient les bonnes - est prouve par mesure, et
cette preuve a **trouve un defaut reel de l'enveloppe** que j'aurais autrement livre :

- **Premier essai** : capture produite, non vide, code de sortie du navigateur 0... et image
  uniformement `(32, 33, 36)`. La page n'avait jamais ete chargee : le navigateur est un binaire natif
  et ne resout pas un chemin de shell POSIX de la forme `/tmp/...`. Corrige par une conversion de
  chemin quand `cygpath` est disponible. **Un faux positif silencieux, exactement la forme de defaut
  que ce depot poursuit depuis `ADR-0018`, et qu'aucune fonction pure ne pouvait attraper.**
- **Apres correction, mesure en pixels** sur une page qui dessine une barre noire de la largeur exacte
  de `window.innerWidth`, capturee sur fond rouge :

  | Largeur CSS demandee | Fenetre parente | Barre noire mesuree | Fond rouge a partir de |
  |---|---|---|---|
  | 360 px | 600 px | **360 px** | x = 360 |
  | 320 px | 600 px | **320 px** | x = 320 |

  Le viewport reel vaut donc la largeur demandee, **sous le plancher de 500 px**, ce qui est
  precisement ce que la commande fautive du sprint ne savait pas faire.
- **Sur l'application reelle** (`recreo.jar` reconstruit a `be60b10`, servi sur un port local), a une
  largeur CSS de 360 px : le bandeau d'en-tete occupe `x = 0` a `x = 359` a l'interieur de l'iframe, et
  le fond du conteneur reprend exactement a `x = 360`. **Je n'en tire aucune conclusion sur le
  debordement** : ce n'est pas mon sujet ici, l'obstacle est sorti du sprint, et la seule chose que
  cette mesure etablit est que l'outil fonctionne sur l'application.

Limite nommee dans le script lui-meme : il sait qu'une capture a ete ecrite, pas qu'elle montre quelque
chose - une page qui ne se charge pas rend une image unie et un code 0. C'est l'etape de mesure (boite
englobante, ou l'oeil) qui le voit, et c'est pourquoi la methode mesure l'image plutot que de croire la
commande.

### 3. Les juges purs de `outils/` entrent dans la CI

En ecrivant ce test, j'ai constate que **ni `outils/verifier-lignee-test.sh` ni
`outils/verifier-jdk-test.sh` n'etaient lances par aucun automate** : `.github/workflows/ci.yml` ne les
appelait pas. Deux juges purs, environnement-independants, testes et jamais executes. Un troisieme
serait venu s'y ajouter. Une etape `outils` est donc ajoutee au workflow, qui balaie `outils/*-test.sh`
- perimetre derive du repertoire, pas une liste de trois noms. Les enveloppes, qui touchent le poste,
n'y sont pas executees et le commentaire de l'etape le dit.

### Pourquoi rien de tout cela n'est une `ArchRule`

ArchUnit lit du bytecode. Ni la largeur d'un rendu, ni le verdict d'un script shell n'y sont visibles.
Le compte de regles ArchUnit du depot est inchange a **28**, et cet ADR n'en revendique aucune.

## Ce que j'ai refuse d'ecrire, avec la mesure qui motive le refus

**Une regle "toute rangee flex declaree dans une feuille maison declare son repli".** C'est la seule
regle declarative qui aurait un rapport avec un debordement de flex, et elle est tentante parce qu'une
rangee sans repli ne peut pas descendre sous la somme des largeurs minimales de ses elements. Je la
refuse, et voici le chiffre qui tranche : `recreo.css` declare `display: flex` dans **5** blocs, dont
**2** en colonne, soit **3 rangees**, et **aucun** `flex-wrap` nulle part (mesure :
`grep -c "flex-wrap"` rend 0, `grep -n "display: flex"` rend 5 lignes, `flex-direction: column` en rend
2, sur `recreo.css` a `be60b10`). Cette regle serait donc **rouge sur trois blocs de production le jour
de son ecriture**, et la seule facon de la verdir serait de modifier `recreo.css` - c'est-a-dire de
changer la mise en page servie a un enfant pour satisfaire un automate ecrit contre un defaut que le
depot n'a pas etabli. C'est l'inverse de l'ordre des choses : `nadia-prof` constate, puis on garde.

Elle aurait de plus surpromis. Une rangee avec repli deborde encore sur un mot insecable, et une rangee
sans repli ne deborde jamais si son contenu est court. La regle n'aurait pas garde la propriete que son
nom suggere - le defaut mesure d'`ADR-0015`, et je ne vais pas le refaire dans le fichier qui le cite.

## Consequences

- Le depot sait mesurer un rendu sous 500 px de facon rejouable, et l'invocation qui a fabrique un
  symptome pendant ce sprint est desormais **refusee par l'outil**, pas seulement deconseillee dans un
  rapport.
- Toute affirmation de largeur de rendu dans un fichier commite doit citer cette commande. **Mauvaise
  consequence assumee** : c'est une contrainte de forme sur `ines-design`, `lea-dev`, `yanis-qa` et
  `nadia-prof`, dont l'oubli ne produit aucun rouge - aucun automate ne lit un rapport de ceremonie
  pour y chercher une commande. Ce point-la reste une regle de revue, portee par le
  `relecteur-pedagogique` et par la review. Je le dis plutot que de laisser croire que la doctrine est
  gardee de bout en bout : ce qui est garde, c'est que la commande **existe et refuse le piege** ; que
  chacun s'en serve reste une discipline.
- Le debordement de mise en page reste hors de toute CI. Une regression de ce genre passera la CI verte
  et sera trouvee par `nadia-prof` en jouant, ou pas trouvee du tout. C'est le prix du refus, il est
  reel, et il est ecrit.
- `mesurer-rendu.sh` depend d'un navigateur present sur le poste, designe par `CHROME` ou trouve sur le
  `PATH` (le prerequis est nomme, jamais son emplacement, `ADR-0010`). Sur un poste sans navigateur, il
  s'arrete avec un code 2 et un message qui nomme le prerequis - il ne se tait pas.
- La CI gagne une etape et environ deux secondes ; trois juges purs qui n'etaient lances par personne
  le sont a chaque push.

**Amendement du 2026-08-12 (s10) : l'outil que tout le monde utilise avait un mode de defaillance
silencieux, sur le seul de ses trois chemins que personne n'avait converti.** Invoque avec un chemin
de PNG relatif, `mesurer-rendu.sh` rendait "aucune capture (code 0)" et n'ecrivait rien. Trouve par
`nadia-prof`. Le chemin de sortie est desormais rendu absolu par une fonction pure (`resoudre_png`),
puis converti par `cygpath` comme le conteneur et le profil l'etaient deja ; un dossier de sortie
absent est refuse explicitement avant tout lancement, au lieu de produire le meme silence. Aucune
mesure deja ecrite dans le depot ne change : les deux formes de chemin absolu passent inchangees, et
la geometrie rendue est identique (verifiee a la main apres correction, sur un chemin relatif :
capture de 600 x 1400 px, contenu jusqu'a x = 359 et y = 639, fond rouge a x = 361 et y = 641). La
lecon de doctrine - une enveloppe dont le collaborateur de poste est designe par une variable
d'environnement a un cablage testable - est portee par l'amendement du meme jour a `ADR-0009`, avec
ses cas et sa mesure de morsure.

## Alternatives considerees

**Elargir le perimetre de `ViewportEtLargeurTelephoneTest` aux debordements.** Impossible, et pas par
manque d'effort : sa population est faite de declarations, la propriete est faite de pixels rendus. Un
elargissement aurait produit un nom plus large que le perimetre, ce qu'`ADR-0015` a deja du corriger
une fois dans ce depot.

**Automatiser la methode iframe en test JUnit** (Java lance Chrome, Java lit l'image). Ecartee : elle
transforme un outil en garde-fou, donc rend la suite dependante d'un navigateur, avec les deux issues
possibles connues - rouge sur un poste sans Chrome, ou ignore quand Chrome manque, c'est-a-dire un vert
decoratif. `mvn verify` doit rester vrai sur une machine nue.

**Piloter Chrome par le protocole DevTools maintenant.** Ecartee pour aujourd'hui, avec le declencheur
de reprise ecrit plus haut. C'est la bonne reponse technique a la mauvaise question de calendrier : le
depot n'a pas de defaut de rendu etabli a garder, il a un besoin de mesure rejouable, et le second se
satisfait pour deux fichiers de shell.

**Ne rien ecrire et laisser la clause "non gardable" seule.** Ecartee, et c'etait la sortie la plus
economique. Elle aurait laisse ouverte la cause racine que `yanis-qa` nomme - une commande ecrite
nulle part - et le prochain sprint aurait rediscute le meme symptome avec les memes moyens. Une clause
"non gardable" est legitime pour la propriete ; elle ne dispense pas de fermer ce qui, autour, est
fermable.

## Verification

`mvn -B clean verify` sur un JDK 21.0.10 designe par `JAVA_HOME` le temps de la commande : **BUILD
SUCCESS**, les cinq modules du pom agregateur en SUCCESS. Chiffres et methode dans
`squad/sprints/s06-ouvrir-la-conjugaison-et-solder-la-dette/confirmation-archunit.md`, qui est le
document de reference de cette cloture.

`bash outils/mesurer-rendu-test.sh` : **16 cas, tous verts**. `bash outils/verifier-lignee-test.sh` et
`bash outils/verifier-jdk-test.sh` : verts, desormais lances par la CI.
