# ADR-0028 - Outiller la mesure du pli, et declarer non gardable le contenu au-dessus du pli

- **Statut** : Accepte
- **Date** : 2026-08-11
- **Auteur** : theo-archi
- **Sprint** : s08 (planning)

## Contexte

`DEC-0016` fixe un viewport de reference - **360 x 640** - et une propriete : sur un ecran de manche,
l'enonce, l'indice s'il est affiche et les propositions restent visibles **sans defilement**.
`camille-po` nomme la regle et me route explicitement son mecanisme de garde, sans le choisir
(`DEC-0016` decision 3). Aucun `ADR` n'existait ; l'obstacle est reste ouvert a la cloture de s07 et
`sofia-sm` le remet au planning de s08.

Le fait qui rend la decision urgente n'est pas une preference de forme, c'est une lacune d'outillage :
**personne ne peut verifier `DEC-0016` mecaniquement aujourd'hui.** `yanis-qa` a du mesurer le pli a
la main a la porte technique de s07, et `nadia-prof` avait mesure a la main les vingt-quatre items de
"Le bon petit mot" a s06. La hauteur est **la seule des deux dimensions du viewport de reference que
`outils/mesurer-rendu.sh` ne prend pas en parametre** : l'outil versionne du depot sait mesurer une
largeur de 360 px sous le plancher de Chrome (`ADR-0025`), et rend toujours une page entiere, donc une
image sur laquelle aucun pli n'existe.

J'ai chiffre le sujet en section 7 de mon `confirmation-archunit.md` de s07 et je m'y tiens :
outiller la hauteur est peu couteux, en faire un automate rouge ou vert est couteux et a refuser.

## Decision

**Deux gestes, et le second est un refus assume - dans cet ordre, parce que le refus ne se tient que
si le besoin qu'il laisse ouvert est outille.**

### 1. La hauteur devient un parametre de `outils/mesurer-rendu.sh`, et l'iframe porte le pli

`outils/mesurer-rendu.sh <largeur_css_px> <url> <fichier_png> [hauteur_css_px]`. Sans quatrieme
argument, la capture montre toute la page, exactement comme avant. Avec, **la hauteur demandee devient
la hauteur de l'iframe, donc le viewport de la page chargee dedans** : ce qui depasse n'est pas rendu,
le fond rouge du conteneur reprend a `y = hauteur`, et l'image montre exactement ce qu'un enfant voit
sans defiler.

C'est la meme mecanique qu'`ADR-0025` a etablie pour la largeur, appliquee a l'autre dimension : la
hauteur CSS d'une iframe n'est pas soumise au plancher de fenetre de Chrome, c'est elle qui sert de
viewport.

**Pourquoi la hauteur est le quatrieme argument et non le deuxieme.** Deux invocations sont deja
ecrites dans des fichiers commites (`ADR-0025` et `DEC-0016`, toutes deux
`outils/mesurer-rendu.sh 360 <url> <png>`), et trois fiches de conformite les citent. Un argument
positionnel insere avant elles aurait fait mentir ces fichiers sans les toucher. L'ordre est laid ; il
est juste.

**Ce que le script ne declare pas, et pourquoi.** Aucun plancher de hauteur. Le plancher de largeur de
516 px existe parce qu'il a ete **mesure** deux fois par deux methodes (`ADR-0025`) ; je n'ai mesure
aucun equivalent en hauteur, et je n'epingle pas un chiffre que personne n'a constate. La fenetre
parente garde donc sa hauteur de 1400 px quelle que soit la hauteur demandee - la seule facon
d'approcher un plancher inconnu serait de reduire la fenetre, ce que ce script ne fait jamais - et le
juge refuse toute hauteur d'iframe qui la depasserait.

### 2. "La reponse est au-dessus du pli" reste non gardable par un automate de ce depot

Cette propriete est de la meme famille que le debordement de mise en page qu'`ADR-0025` a deja
declaree non gardable, et pour la meme raison, qui n'a pas bouge : **c'est une propriete de pixels
rendus, pas de declarations.** La hauteur qu'occupe un enonce depend de la police installee, du repli
du texte, de la largeur de glyphes rendus - `DEC-0016` decision 2 l'ecrit elle-meme et interdit
nommement tout critere en nombre de caracteres, parce que `nadia-prof` a constate qu'un raisonnement
par lettres manque son pire cas reel.

**Cette propriete appartient a `nadia-prof`**, comme les autres proprietes de rendu que ce depot lui a
confiees (`ADR-0014`, `ADR-0017`, `ADR-0022`, `ADR-0025`). Elle est la seule autorite qui la constate,
en jouant. Ce que cet `ADR` lui doit, et lui livre, c'est le moyen de la constater de facon
**rejouable et sans main** : la commande du geste 1, dont la sortie nomme le pli.

**Ce que je refuse d'ecrire, avec le motif.** Un test JUnit qui lancerait un navigateur et lirait la
position des boites. Les trois raisons d'`ADR-0025` tiennent toutes : il ajoute une dependance a un
binaire externe que `mvn verify` ne peut pas garantir, et un test qui se met en veille quand le
binaire manque est un vert qui ne prouve rien ; une assertion en pixels sur du texte rendu depend des
polices installees, donc exigerait une image figee. **Ce qui me ferait revenir sur ce refus** : le
jour ou le depot porte un conteneur de build avec ses polices epinglees, le calcul change et cet `ADR`
doit etre repris - meme declencheur qu'`ADR-0025`, et il n'est pas encore survenu.

**Ce que je refuse aussi, et c'est le refus le plus tentant.** Un garde-fou declaratif qui bornerait
la longueur des enonces ou la hauteur declaree des blocs. `DEC-0016` l'interdit explicitement, et il
serait de plus **rouge sur du contenu que `nadia-prof` a valide** : "Le bon petit mot" garde son Done
pedagogique et sa ligne `couvert-valide` avec ses trois items en defaut. Ecrire cet automate
obligerait a modifier un contenu valide pour verdir un test, c'est-a-dire l'inverse de l'ordre des
choses - `nadia-prof` constate, puis on garde.

## Regles executables

### 1. `outils/mesurer-rendu-test.sh` - le juge d'invocation, sept cas de plus

Le juge pur `juger_invocation` recoit deux arguments supplementaires (hauteur CSS, hauteur de fenetre)
et refuse trois choses de plus : une hauteur qui n'est pas un entier positif, une hauteur de fenetre
illisible, et **une hauteur d'iframe superieure a la fenetre qui la contient** - sinon l'image est
coupee par la fenetre et l'agent lirait un pli a 1400 px comme s'il etait a la hauteur demandee.

Le fichier passe de **16 a 23 cas** (22 appels au juge plus le test negatif global ; comptage :
`bash outils/mesurer-rendu-test.sh` rend une ligne `ok` par cas et la derniere ligne les compte).

**Preuve de morsure par trois mutations reelles du juge, chacune relancee :**

| Mutation | Resultat attendu | Resultat mesure |
|---|---|---|
| `-gt` remplace par `-ge` sur l'inegalite de hauteur (relecture adverse) | le bord haut est refuse a tort | **6 cas en echec**, et c'est le chiffre qui compte le plus : `1400 x 1400` est exactement ce que produit une invocation **sans** quatrieme argument, donc cette mutation refuse **toutes** les acceptations du fichier, y compris les trois cas de largeur et les deux cas de message sur le profil |
| controle de l'inegalite de hauteur supprime | un pli invente passe | **1 cas en echec** : "un pixel de plus que la fenetre parente est refuse" |
| controle de validite de la hauteur CSS supprime | une hauteur qui n'est pas une hauteur passe | **3 cas en echec** : hauteur nulle, negative, non numerique |

La premiere mutation dit une chose qu'une mutation racontee n'aurait pas dite : l'inegalite de hauteur
n'est pas un cas de bord, c'est le chemin par lequel passe **l'invocation par defaut**. Un `-ge` au
mauvais sens aurait rejete toutes les mesures deja citees dans le depot, et aucun cas nominal n'aurait
eu l'air de parler de hauteur.

Un cas existant a du changer d'attendu : "une fenetre parente egale au plancher est acceptee" cherchait
le motif `fenetre parente : 516 px`, que la ligne citable ecrit desormais `516 x 1400 px`. Le motif est
reduit a `fenetre parente : 516 x`, ce qui epingle toujours le bord bas du plancher de largeur - la
premiere mutation ci-dessus le confirme en le faisant rougir.

### 2. L'enveloppe, prouvee a la main sur l'application reelle

Ce que le juge ne couvre pas - que les options Chrome soient les bonnes - est prouve par mesure, et
cette mesure a servi a repondre a la question ouverte plutot qu'a se rassurer. Methode, en entier :
`recreo.jar` reconstruit depuis ce worktree et servi sur un port local ; une partie de
`bandit-manchot-homophones-cm1` jouee par requetes HTTP reelles avec porte-cookies, trois reponses
fausses pour declencher l'indice (le seul cas ou `DEC-0016` mord, `nadia-prof` l'a etabli), puis
`GET /` qui rend le **document assemble** de l'ecran de manche (`ADR-0020` ; c'est la reprise
silencieuse de S1-4, donc `data-arrivee=sans-geste`). Ce document est servi tel quel sur un second
port local avec une seule ligne ajoutee, une `base` qui fait resoudre les feuilles et les scripts sur
l'application elle-meme. Capture par la commande du depot, navigateur designe par la variable
`CHROME`. Positions relevees au pixel sur l'image (boite englobante Pillow, aplat d'accent
`#2e6f9e` des boutons de reponse, fond de page `#f4f7fb`, fond du conteneur `#ff0000`).

| Mesure | Attendu | Mesure relevee |
|---|---|---|
| capture sans quatrieme argument | aucun pli sur l'image | **aucune ligne entierement rouge**, bord droit du contenu a `x = 360` |
| capture avec `640` | premiere ligne entierement rouge a `y = 640` | **`y = 640` exactement** ; `px(10, 639)` est le fond de page, `px(10, 640)` est le fond du conteneur |
| largeur du contenu avec `640` | inchangee a 360 px | **`x = 360`** : la barre de defilement verticale que la reduction de hauteur fait apparaitre ne retranche rien, `--hide-scrollbars` tient. C'est le point qui pouvait etre faux et c'est pourquoi il est mesure et non suppose |

**Ce que l'outil a immediatement rendu, et que personne ne pouvait obtenir avant.** Sur l'item le plus
long de `cm1-homophones` avec son indice affiche - `"Apres cette longue randonnee en montagne, le
vieux chien ne ___ plus vraiment courir comme avant."`, propositions `peu` et `peut`, tire en 58
sessions - a un viewport reel de 360 x 640 : **les deux boutons de reponse occupent `y = 717` a
`y = 790`, entierement sous le pli**, et ils sont absents de la capture a 640. Sur un autre item du
meme paquet (`"___ vas-tu ?"`, indice affiche), ils occupent `y = 447` a `y = 520`, entierement
au-dessus.

Le defaut que `nadia-prof` avait mesure a la main est donc desormais **reproductible par une commande
versionnee**. Je n'en tire aucun verdict : le Done pedagogique de "Le bon petit mot" est accorde, sa
ligne est `couvert-valide`, et c'est elle seule qui juge ce que ce chiffre vaut. Je livre la mesure et
je nomme sa destinataire.

Limite deja nommee dans le script et inchangee : il sait qu'une capture a ete ecrite, pas qu'elle
montre quelque chose. C'est l'etape de mesure qui le voit.

Seconde limite, neuve et propre a l'ecran de manche : **un ecran de manche s'obtient par un POST**, et
un outil de capture ne fait que des GET. Le detour ci-dessus (jouer par HTTP, prendre le document
assemble, le servir avec une `base`) est la facon dont je l'ai contourne ; il n'est pas dans le script,
parce qu'un outil de capture n'a pas a savoir jouer a un jeu. `nadia-prof` obtient le meme resultat par
sa propre methode - les feuilles reellement servies et la structure reelle du gabarit - et le parametre
de hauteur la sert a l'identique.

### 3. Pourquoi rien de tout cela n'est une `ArchRule`

ArchUnit lit du bytecode. Ni la hauteur d'un rendu ni le verdict d'un script shell n'y sont visibles.
Le compte de regles ArchUnit du depot est inchange a **28**, et cet `ADR` n'en revendique aucune.

## Consequences

- Le depot sait mesurer un pli. La verification de `DEC-0016` cesse d'etre une lecture a la main et
  devient une commande citable, avec la meme forme d'invocation que les mesures de largeur.
- **Mauvaise consequence assumee, et c'est la meme qu'`ADR-0025`** : la propriete reste hors de toute
  CI. Une regression de pli passera la CI verte et sera trouvee par `nadia-prof` en jouant, ou pas
  trouvee du tout. C'est le prix du refus, il est reel, il est ecrit, et il est **plus faible
  qu'avant** cet `ADR` seulement d'un cran : mesurer est desormais a la portee de tout agent, sans
  main.
- Le pli de "Le bon petit mot" reste une intention non detaillee, propriete de `camille-po`
  (`DEC-0016` impact). Cet `ADR` ne choisit pas entre raccourcir les enonces et traiter la hauteur par
  la mise en page : ce choix n'est pas technique, `DEC-0016` le route a `nadia-prof` et a
  `ines-design`, et je ne le prends pas a leur place.
- Le script ne modifie aucun fichier de production. **Zero octet de ce que voit un enfant ne change
  par cet `ADR`.**
- Une invocation avec un quatrieme argument superieur a 1400 est refusee. C'est une contrainte reelle
  sur qui voudrait mesurer un viewport tres haut ; elle se leve en changeant une constante, une fois
  qu'un plancher de hauteur aura ete mesure.

## Alternatives considerees

**Faire de la hauteur le deuxieme argument, par symetrie avec la largeur.** Ecartee : elle aurait fait
mentir les deux invocations deja ecrites dans `ADR-0025` et `DEC-0016` et les trois fiches de
conformite qui les citent, sans toucher ces fichiers. Le depot est la memoire partagee ; on ne change
pas le sens d'une commande deja citee.

**Un plancher de hauteur symetrique du plancher de largeur de 516 px.** Ecartee faute de mesure. Le
plancher de largeur est un fait etabli deux fois ; un plancher de hauteur serait un chiffre invente, et
ce depot a deja paye pour un chiffre cite sans sa methode.

**Un automate de pli pilote par le protocole DevTools.** Ecartee pour aujourd'hui, avec le declencheur
de reprise ecrit plus haut. Meme analyse qu'`ADR-0025`, dont aucune des trois raisons n'a change en un
sprint.

**Ne rien ecrire et laisser la clause "non gardable" seule.** Ecartee, et c'etait la sortie la plus
economique - elle etait meme deja a moitie ecrite dans mon avis de s07. Elle aurait laisse ouvert le
fait qui motive l'obstacle : que personne ne peut mesurer. Une clause "non gardable" est legitime pour
la propriete ; elle ne dispense pas de fermer ce qui, autour, est fermable. C'est exactement le
raisonnement d'`ADR-0025`, et le sprint 7 vient de montrer ce que coute de ne pas l'appliquer : une
mesure a la main a la porte technique.

**Un garde-fou declaratif sur la longueur des enonces.** Ecartee, motif chiffre ci-dessus : il serait
rouge sur un paquet dont `nadia-prof` a accorde le Done, et `DEC-0016` l'interdit nommement.

## Verification

`mvn -B clean verify`, sur un JDK **21.0.10** designe par `JAVA_HOME` le temps de la commande (le
`java` du `PATH` de ce poste est un JRE 8 qui ne compile pas ; constat rendu par
`bash outils/verifier-jdk.sh`) : **BUILD SUCCESS**, les six modules du pom agregateur en SUCCESS.

**Chiffres, et la methode dans le meme paragraphe.** Comptage valide **apres un `clean`** et lu sur les
lignes `Results:` du journal de la commande, module par module : `domain` 43, `application` 7,
`adapter-web` 153, `adapter-content` 36, `bootstrap` 77 en surefire et 3 en failsafe. Total
**319 tests**, contre 315 a `f29a2b0` : les 4 de plus sont exactement les quatre cas du garde-fou
d'`ADR-0029`, ce qui est le seul test Java ajoute par ce commit. Les 23 cas de
`outils/mesurer-rendu-test.sh` n'entrent pas dans ce total - c'est un script shell, lance par l'etape
`outils` de la CI et non par surefire.

**Regles ArchUnit : 28, inchange.** Methode : `grep` de la forme `ArchRule <nom> =` dans les
`src/test/java` des six modules declares au pom agregateur, execute **dans ce worktree seul**. Trois
causes de divergence connues et neutralisees ici : le faux depot de contre-exemple de
`LesGardeFousSontMisALEpreuveTest` vit sous `src/test/resources` et n'entre donc pas dans ce perimetre ;
les worktrees des autres agents vivent hors de cet arbre ; et le compte n'est pas lu sur un rapport
texte, dont un des deux moteurs JUnit n'apparait pas. Le meme balayage rend **27** annotations
`@ArchTest` - la 28e regle est executee par un `.check()` explicite, ecart deja etabli en s07.

`bash outils/mesurer-rendu-test.sh` : **23 cas, tous verts** (22 appels au juge plus le test negatif
global), et les trois mutations du tableau ci-dessus rejouees puis annulees. `git status` apres coup :
aucun fichier de production modifie.
