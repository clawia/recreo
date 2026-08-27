# Avis de faisabilite - s21 (ouverture)

- **Auteur** : `theo-archi`. **Date** : 2026-08-27. **Sprint** : s21, avant `planning.md`.
- **Arbre mesure** : branche `round-two`, tete `9e2e31a` au debut de cet avis, arbre propre.
  **La tete a bouge pendant que je mesurais** : `nadia-prof` a pousse `749244a` a 14:23 (arbitrage
  du gel `S3-4`). **Aucun fichier de production n'y est touche** - verifie plutot que suppose,
  `git diff --name-only 9e2e31a..749244a -- domain application adapters bootstrap` ne rend aucun
  fichier - donc le jar mesure et tous les chiffres de cet avis valent identiquement a `749244a`.
  Les sections 1, 2, 3 et 5 sont reconciliees avec son arbitrage ; je dis ou il me corrige.
- **Ce que cet avis est** : un avis de faisabilite, six points demandes par `sofia-sm`. Je ne redige
  aucune story, je ne repriorise rien, je ne prononce aucun statut de couverture et aucun Done -
  pedagogique encore moins que technique.
- **Ce que cet avis n'est pas** : une decision. Aucun des `ADR` qu'il rend possibles n'est ecrit
  ici ; un avis dit si une decision est encodable et a quel prix, il ne la prend pas.
- **Convention de chiffres tenue dans tout le document** : chaque chiffre porte sa methode, son
  perimetre et sa date dans la meme phrase. Un chiffre repris d'un autre document est verifie
  contre sa source primaire avant d'etre cite, et je le dis quand la source primaire le contredit.

## 0. La porte technique, mesuree aujourd'hui - et un faux rouge d'environnement

**Commande** : `bash outils/mvn-jdk21.sh -B clean verify`, a la racine du reacteur (six modules,
aucun `-pl`), sur `9e2e31a`, le 2026-08-27.

**Premiere execution : `BUILD FAILURE`, et ce n'est pas le code.** Le `clean` de `recreo-bootstrap`
n'a pas pu supprimer `bootstrap/target/recreo.jar` : *"le processus ne peut pas acceder au fichier
car ce fichier est utilise par un autre processus"*. Cause identifiee, pas supposee : un
`java -jar bootstrap/target/recreo.jar --server.port=8123` tournait encore, reste de la campagne de
mesure du matin. Les cinq premiers modules etaient deja verts a ce moment-la
(`recreo-adapter-content` compris, `Results: Tests run: 198`). Processus arrete **par son
identifiant**, jamais par nom d'image, puis rejeu.

**Seconde execution : `BUILD SUCCESS`.** Blocs `Results:` releves module par module dans le journal
de cette commande, jamais deduits d'un total :

| Module | Bloc `Results:` |
|---|---|
| `recreo-domain` | `Tests run: 49, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-application` | `Tests run: 13, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-adapter-web` | `Tests run: 330, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-adapter-content` | `Tests run: 198, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-bootstrap` (surefire) | `Tests run: 98, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-bootstrap` (failsafe) | `Tests run: 6, Failures: 0, Errors: 0, Skipped: 0` |
| **total, somme des six blocs** | **694** |

`Total time: 02:37 min`. La porte technique est verte a `9e2e31a`.

**Regles ArchUnit : 30, et voici la methode, parce qu'un compte sans methode m'a deja fait mentir de
quatre unites.** `grep -rn -E "ArchRule [A-Za-z_]+ *=" <module>/src/test` sur les cinq modules du
reacteur, dans le seul worktree principal (`git worktree list` en compte sept autres, tous des lots
d'agents de s20, tous exclus). Le comptage brut rend 32 declarations ; **deux appartiennent au faux
depot de contre-exemple** de `LesGardeFousSontMisALEpreuveTest`
(`bootstrap/src/test/resources/contreexemple/.../faux-module/`), qui n'est pas du code de garde-fou
mais le materiau qu'un garde-fou juge. Reste **30**, dont 29 dans `bootstrap/src/test/java/com/recreo/archi/`
et 1 dans `adapters/adapter-web/src/test/java/com/recreo/adapter/web/archi/`.

## 1. Faisabilite de fermer `REP-8` - les deux issues du gel `S3-4`, dont une tranchee pendant l'ecriture

### 1.1 Le fait qui commande les deux issues, et que le dossier ne posait pas

Le pire des jeux du catalogue n'est pas un probleme de nombre de boutons. **`cm1-grands-nombres`
n'a jamais plus de trois propositions, et ses libelles vont jusqu'a 40 caracteres** - un nombre
ecrit en lettres, *"quatre-vingt-seize mille deux cent trois"* (methode et table complete en
section 3.0, 18 paquets lus le 2026-08-27). Ses items a trois rangees viennent de la longueur du
libelle, et cette longueur **est la competence visee**, pas une maladresse de redaction. Meme
famille sur `cm1-perimetre` (26 caracteres) et `cm1-reperes` (21), ou `nadia-prof` a deja ecrit que
*"la matiere est saine"*.

**Consequence, et elle vaut pour les deux issues du gel** : aucun contrat de forme sur les libelles
ne s'applique retroactivement a ces jeux-la, et aucun budget vertical ne rattrape un terme
`.propositions` qui vaut 74 px a une rangee, 164 px a deux et **398 px a trois** (ma mesure de s20,
section 4, 2026-08-25). **Le pli de ces items n'est pas un probleme de taille : c'est un probleme
de dependance.** Tant que la position du bloc de message est une fonction de la hauteur de ce qui
le precede, elle heritera de tout ce que le contenu peut produire.

### 1.2 L'issue est tranchee pendant que je mesurais : le gel tient

**`nadia-prof` a rendu son arbitrage a 14:23 ce jour, commit `749244a` : le gel de `S3-4` est
MAINTENU.** J'avais commence cet avis en ecrivant les deux issues comme demande ; je garde ici ce
que l'issue "gel leve" aurait valu, parce que sa mesure a elle le tranche mieux que ma
speculation, et parce que le motif compte plus que le verdict.

**Ce que j'allais ecrire, et qui etait faux sur un point** : que personne n'avait mesure ce que la
police achete. Elle l'a mesure le 2026-08-27, valeur par valeur, sur `cp-problemes-additifs` a
744 x 551, releve commite `squad/conformite/releves-s21/s21-nadia-police-de-proposition-744x551.json`.
Sa loi : **la hauteur d'un bouton vaut 24 x police(rem) + 26 px**, verifiee contre une projection
independante a 567,19 px sur trois tirages.

**Ce que sa mesure etablit, et qui vaut mieux que mon intuition** : projete sur la population du
releve d'`ines-design`, **1,875 rem ferme 0 item de plus, 1,75 rem non plus**, et **1 rem - 16 px,
la taille d'un texte courant - laisse encore 4 items sous le pli**. Elle en tire, mot pour mot :
*"la police n'est pas le verrou de `REP-8` ; elle n'en a jamais ete la cle."*

**C'est exactement la conclusion de ma section 1.1, atteinte par une autre route**, et le fait que
deux chemins independants y arrivent est ce qui me fait la tenir : le pli de ces items n'est pas un
probleme de taille, c'est un probleme de dependance. Sa preuve chiffree du meme point : le pire
item, `grands-nombres-cm1` a trois rangees, manque **164,19 px**, quand trois rangees a 1 rem n'en
rendent que 72.

### 1.3 Le gel tenant, il reste un levier d'architecture, et je l'ai mesure

Les deux leviers de forme d'`ines-design` ferment **26 des 56 items** ; **30 restent**, dont deux a
102,19 et 132,19 px de deficit residuel (sa section 2.2, chiffres verifies a son releve commite,
puis recalcules depuis ses fichiers bruts par `nadia-prof` le meme jour). **La forme seule ne ferme
pas `REP-8`.** `nadia-prof` le prononce comme un fait de mesure et non comme un avis : *"`REP-8` ne
peut pas etre entierement fermee a s21. Ni par la police, ni par les deux leviers de forme, ni par
les deux ensemble."*

**Ce que sa phrase couvre, et ce qu'elle ne couvre pas.** Elle enumere trois leviers, et les trois
sont des **leviers de forme** - des valeurs de taille. Ce qui suit n'en est pas un : il ne change
aucune taille, il change **ce qui gouverne une position**. Je ne le presente pas comme une
objection a son verdict - son verdict porte sur ce qu'elle a mesure, et il tient sur ce
perimetre-la. Je le lui route comme un materiau qu'elle n'avait pas quand elle a ecrit, et dont
**elle seule** peut dire s'il est acceptable pour un enfant.

**Le levier qui n'est pas de son ressort**, parce qu'il ne touche aucune taille : **ce qui gouverne
la position du bloc de message.** Aujourd'hui, `.message-jeu` est place par le flux - donc par la
hauteur cumulee de l'en-tete, du score, de la consigne, de l'enonce et du cabinet. Une **declaration
de position** le rattache au bas de la fenetre au lieu du bas de ce qui le precede.

**Ou cette declaration irait, et pourquoi c'est le bon endroit** : `recreo.css` porte deja le
selecteur `.message-jeu` **avec un corps de regle vide**, et son commentaire dit exactement pourquoi
il est vide - *"Aucune propriete ici : sa forme revient au lot qui porte le meuble"* (ligne 1246 et
suivantes). C'est le seul bloc de la pile de manche qui n'est engage par aucun arbitrage de valeur.

**Ma mesure, avec sa methode, son perimetre et sa date.** Outil `squad/outils/ines-design/mesurer-candidats-rep-8.py`,
**non modifie** - la regle candidate est injectee dans le document rendu, **aucune ligne de
production n'est touchee, aucun fichier de style n'est modifie**. Jeu mesure :
`bandit-manchot-grands-nombres-cm1`, le plus enfonce du catalogue. Viewport 744 x 551, 10 items vus
par candidat, un echec par item, le 2026-08-27, sur le jar de `9e2e31a`.

| Candidat | Regle injectee | Items dont l'indice passe sous le pli | Pire bas de l'indice |
|---|---|---|---|
| **temoin** | aucune | **5 sur 10** | **715,19 px** (164,19 sous le pli) |
| **C - position collante** | `.message-jeu{position:sticky;bottom:0}` | **0 sur 10** | **551,00 px** |
| **D - position fixee** | `.message-jeu{position:fixed;left:0;right:0;bottom:0;margin:0;background:#ffffff}` | **0 sur 10** | **551,00 px** |

**Trois lectures de cette table, et la troisieme est la plus importante :**

1. **551,00 px pile n'est pas une coincidence, c'est la signature d'un bloc epingle.** Le temoin rend
   des valeurs continues (505,19 ; 583,19 ; 715,19 selon le nombre de rangees) ; les deux candidats
   rendent **exactement** la valeur du pli. Le bas du bloc a cesse d'etre une fonction de ce qui le
   precede.
2. **Les trois nombres de rangees sont presents dans les trois tirages** (1, 2 et 3 rangees) : le
   candidat n'est pas verifie seulement sur les cas faciles. Les items a trois rangees - ceux
   qu'aucune valeur du budget vertical ne touchait, ligne decisive de ma mesure de s20 - passent
   au-dessus du pli.
3. **Les deux candidats ne different pas sur le pli, ils different sur les items qui allaient
   deja bien.** Valeurs distinctes rendues par chaque candidat, telles quelles : le temoin donne
   505,19 / 535,19 / 553,19 / 583,19 a une rangee, 595,19 et 625,19 a deux, 715,19 a trois ; le
   candidat C garde **505,19 et 535,19 inchanges** et epingle tout le reste a 551,00 ; le candidat D
   rend **551,00 partout, y compris sur les items qui n'avaient aucun probleme**. Autrement dit :
   **`sticky` ne lie que quand il le faut, `fixed` lie toujours.** C'est un ecart de conception
   reel, pas un detail de valeur - et c'est `sticky` qui a le moins de pieces mobiles, parce qu'il
   ne change rien a ce qui fonctionne deja.

**Ce que ce levier ne rouvre pas, verifie plutot que suppose** : `fragments/manche.html` place le
bloc de message **en dernier enfant, sous le cabinet**, et c'est un arbitrage de `nadia-prof`
(`M1`, `M4` : *"le seul emplacement ou le jeu parle de la reponse de l'enfant, sous le meuble, hors
du cadre du cabinet - jamais au-dessus"*). **Une declaration de position ne change ni le gabarit ni
l'ordre du document** : le bloc reste le dernier enfant, dans l'ordre de lecture et pour un lecteur
d'ecran. **Je n'ai donc pas propose de reordonner les blocs, et c'est delibere** : ce levier-la est
ferme par un arbitrage, et le rouvrir serait une decision pedagogique qui ne m'appartient pas.

**Ce que ce levier coute, et que je ne tranche pas.** Quand la declaration lie, le bloc **recouvre
visuellement le bas du meuble**. Trois questions en decoulent, aucune n'est de mon ressort :

- **`ines-design`** : le fond, la separation, l'ombre - la regle mesuree ci-dessus porte un
  `background` uniquement pour que la mesure ait un sens, ce n'est pas une valeur proposee ;
- **`nadia-prof`** : un message qui recouvre le bas du meuble est-il lu par un enfant comme "sous
  le meuble" ou comme "par-dessus" ? C'est litteralement la frontiere que `M1` et `M4` tracent, et
  **elle seule** peut dire de quel cote tombe une superposition visuelle. **Je ne prejuge de rien
  et je ne prononce aucun Done** ;
- **`camille-po`** et **`sofia-sm`** : si `nadia-prof` refuse la superposition, ce levier tombe et
  il faut revenir a la forme, donc a une fermeture **partielle** de `REP-8` avec une reserve
  chiffree.

**Validite de ma mesure contre le defaut d'outil que `nadia-prof` signale le meme jour** : elle
etablit que `mesurer-candidats-rep-8.py` injecte la regle **une fois**, avant la premiere partie,
et que la sortie de partie est une navigation complete qui **jette la regle injectee** - donc
qu'une execution a plusieurs parties melange, sans le dire, des items mesures avec et sans la
regle. **Mes trois executions portent `parties: 1`**, verifie dans les trois fichiers avant
d'ecrire cette section : elles ne sont pas touchees. Meme verification qu'elle a faite sur les
trois releves d'`ines-design`, et pour le meme motif.

**Les limites de ma propre mesure, avant que quelqu'un les trouve a ma place** : un seul jeu, dix
items par candidat, **un tirage different par candidat** (un seul item commun aux trois). Ce qui est
etabli, c'est une **statistique de population sur le pire jeu** et une valeur epinglee a 551,00 px,
pas une comparaison item par item. **Une campagne sur les 17 jeux reste due avant tout statut**, et
c'est exactement la campagne de la section 4, rejouee avec la declaration en place.

### 1.4 Avis de faisabilite, apres l'arbitrage du gel

| Question | Avis |
|---|---|
| `REP-8` fermee sur tout le catalogue a s21 **par la forme** | **Infaisable.** Mesure de `nadia-prof` du 2026-08-27 : aucune valeur de police ne la ferme, les deux leviers de forme laissent 30 items sur 56. Le point n'est pas discutable, il est chiffre avant le sprint. |
| `REP-8` fermee sur tout le catalogue a s21 **par une declaration de position** | **Faisable moyennant un `ADR`, et suspendu a un arbitrage de `nadia-prof`.** 5 items sur 5 fermes sur le pire jeu, y compris a trois rangees, mesure ci-dessus. Ce n'est **pas** un levier de forme, donc son verdict du jour ne le couvre pas. |
| Ce qui reste vrai dans les deux cas | L'objectif de sprint ecrit par `sofia-sm` **ne doit pas** dire "fermer `REP-8` sur tout le catalogue" tant que l'arbitrage sur la superposition n'est pas rendu. L'objectif atteignable sans lui est celui que `nadia-prof` chiffre en section 4 de son arbitrage. |

**Ma recommandation d'ordre, et elle tient une seule ligne** : demander a `nadia-prof`, **des
l'ouverture**, l'arbitrage sur la superposition visuelle du bloc de message. C'est lui, et lui
seul, qui decide si `REP-8` est une story de contenu etalee sur deux sprints ou une story
d'architecture fermable dans celui-ci. Tout le reste du planning en depend, et rien d'autre ne le
debloque.

**Ce que je ne fais pas** : je ne rouvre pas son verdict, je n'ecris aucun objectif de sprint, et
je ne prononce aucun Done. Je livre une mesure et une declaration candidate ; l'acceptabilite pour
l'enfant ne m'appartient a aucun moment.

## 2. Le pli est-il encodable en garde-fou executable ?

**Reponse courte : non, pas en l'etat, et ce n'est pas une decouverte - c'est deja decide, par moi,
et le declencheur qui rouvrirait la decision n'est pas survenu.** Ce qui est neuf aujourd'hui, ce
n'est pas la reponse, c'est ce qui **est** gardable a cote, et un defaut de l'outil de mesure qui
rend la question plus urgente qu'elle n'en a l'air.

### 2.1 Ce qui est deja decide, et que je ne reouvre pas sans motif

`ADR-0028` (**Accepte**, 2026-08-11, `theo-archi`) porte dans son titre meme :
*"declarer non gardable le contenu au-dessus du pli"*. Sa decision 2 en donne trois motifs, et
aucun n'a bouge :

1. **C'est une propriete de pixels rendus, pas de declarations.** La hauteur qu'occupe un enonce
   depend de la police installee, du repli du texte, de la largeur des glyphes rendus.
2. **Un test JUnit qui lancerait un navigateur ajoute une dependance a un binaire externe que
   `mvn verify` ne peut pas garantir** - et un test qui se met en veille quand le binaire manque
   est un vert qui ne prouve rien. C'est la famille de faux vert que ce depot a deja sanctionnee
   plusieurs fois.
3. **Un garde-fou declaratif qui bornerait la longueur des enonces est interdit nommement** par
   `DEC-0016` decision 2, parce que `nadia-prof` a constate qu'un raisonnement par lettres manque
   son pire cas reel ; et il serait **rouge sur du contenu qu'elle a valide**.

`ADR-0028` ecrit aussi son declencheur de reouverture : *"le jour ou le depot porte un conteneur de
build avec ses polices epinglees"*. **Verifie ce jour** : aucun `Dockerfile`, aucun
`devcontainer`, aucune image epinglee a la racine ni au premier niveau du depot (`find` sur
`Dockerfile*`, `*.dockerfile`, `devcontainer*`, aucun resultat, 2026-08-27) ; la CI tourne sur
`ubuntu-latest` avec les polices que le runner porte ce jour-la. **Le declencheur n'est pas
survenu.** Je maintiens le refus.

**Et je precise pourquoi je le maintiens plutot que de ceder a la pression du defaut.** Le defaut
s'est installe sur 9 jeux, c'est vrai, et c'est un argument pour garder - mais un test qui
mesurerait des pixels de police non epinglee serait **rouge ou vert selon la machine qui le lance**.
Un garde-fou dont le verdict depend de la machine ne garde rien : il apprend a l'equipe a le
desactiver. Une reponse "non gardable, voici pourquoi" vaut mieux, et c'est celle que je donne.

### 2.2 Ce qui, en revanche, est gardable - et c'est la ou l'effort doit aller

**Le principe, et il est deja etabli sur l'autre axe.** `ADR-0059` (s19) n'a pas garde la largeur
rendue : il a decide que **le correctif serait une declaration**, puis a garde la declaration -
qu'elle existe, qu'elle soit litterale, qu'elle porte sur le bon selecteur, et qu'elle ne descende
pas sous la largeur mesuree necessaire. Le pixel reste non gardable ; le **mecanisme** qui le
gouverne devient gardable, parce qu'il est devenu une declaration.

**La transposition verticale est exactement la meme, et elle est la seule voie que je vois.** Si le
correctif de `REP-8` est une valeur de forme (une marge, un rembourrage), alors il n'y a rien a
garder : la valeur nouvelle sera aussi peu prouvee que l'ancienne, et le pli restera une propriete
de pixels. Si le correctif est **une declaration qui rend la position du bloc de message
independante de la hauteur du contenu qui le precede** (section 1), alors trois choses deviennent
gardables d'un coup, toutes sur le modele deja ecrit d'`ADR-0054`, `ADR-0055`, `ADR-0056` et
`ADR-0059` :

1. **que la declaration existe et soit litterale** dans `recreo.css`, sur le selecteur nomme ;
2. **qu'aucune declaration tierce ne la defasse** - la feuille vendorisee est deja lue par les
   garde-fous de cette famille, la population existe ;
3. **qu'elle morde** - un jeu fautif commite qui la retire ou la niche, sur le modele des jeux
   fautifs deja commites de cette famille (31 repertoires sous
   `adapters/adapter-web/src/test/resources/contreexemple/`, comptage du 2026-08-27).

**Ce que ces trois regles interdiraient reellement a quelqu'un qui essaierait de mal faire**, parce
que la question doit etre posee : elles interdiraient de **supprimer le correctif**, de le noyer
dans un `@media` qui ne mord pas, ou de le recopier sous une forme qui n'a pas d'effet. Elles
n'interdiraient **pas** qu'un enonce de quinze lignes passe sous le pli. C'est un plancher, pas une
preuve du pli - et je prefere l'ecrire ici que de laisser croire l'inverse.

### 2.3 Le prix de l'option que je n'ai pas retenue, chiffre honnetement

L'autre voie serait une **etape de CI** - pas un test JUnit - qui sert le jar, joue le catalogue au
navigateur et compare a un releve de reference commite. Elle a un precedent dans le depot : le job
`outils` de `.github/workflows/ci.yml` execute deja des juges de scripts que `mvn verify` ne voit
pas (`ADR-0009`, `ADR-0025`). Son prix, que je nomme pour qu'il soit compare et non suppose :

- **le releve devient un cliquet, donc une dette a plafond**. Or ma propre regle sur les cliquets
  est que **le plafond doit valoir exactement la taille de la dette** : 56 items aujourd'hui, sur
  une population de 167 elle-meme **echantillonnee** (tirage aleatoire, `ines-design` section 1,
  2026-08-27). Un cliquet dont la base bouge a chaque tirage n'est pas un cliquet, c'est un
  generateur de rouges aleatoires ;
- **il faut d'abord epingler la population**. Rendre le tirage deterministe pour la mesure est un
  chantier a part entiere, et il touche `RandomSource`, donc le produit ;
- **il faut epingler les polices** - c'est le declencheur d'`ADR-0028`, et il coute un conteneur de
  build.

**Mon avis : cette voie n'est pas faisable a s21**, et elle ne le devient que si l'ordre est tenu -
polices epinglees, puis population epinglee, puis cliquet. La proposer autrement produirait
exactement le garde-fou decoratif que ce depot sanctionne.

### 2.4 Le defaut que j'ai trouve aujourd'hui dans l'outil de mesure, et pourquoi il change la donne

Si le pli n'est pas garde par un test mais **constate par une mesure**, alors **l'outil de mesure
est le garde-fou** - et il faut le tenir comme tel. Or il a un defaut de la famille exacte que ce
depot sanctionne : **il ne signale pas quand il mesure autre chose que ce qu'on croit.**

**Le fait, mesure et non suppose, le 2026-08-27.** `squad/outils/nadia-prof/balayage-largeur-rangee.py`
(module dont derivent, par import, `auditer-paquet.py`, `mesurer-budget-vertical.py` et
`mesurer-candidats-rep-8.py` - donc **tous** les releves de pli du depot) lance Chrome sur un port
de deverminage **fixe**, puis interroge ce port et **s'attache a la premiere page qu'il y trouve**.
Il ne verifie jamais que le navigateur qui repond est celui qu'il vient de lancer. Quand le port est
deja pris, le Chrome fraichement lance n'arrive pas a s'y attacher et le client se raccroche
silencieusement au **navigateur de quelqu'un d'autre**.

**Comment je l'ai constate** : mes trois premieres tentatives de campagne de ce jour ont lu une page
en cours de partie, dont le score montait tout seul entre deux lectures (0, puis 1, puis 2, a trois
secondes d'intervalle, sans qu'aucune de mes commandes ne presse quoi que ce soit). Cause trouvee :
un Chrome de la campagne du matin d'`ines-design` tournait encore, pilote par un processus python
survivant, et tenait le port. Processus arretes **par identifiant**, jamais par nom d'image ; la
campagne de la section 4 a ensuite tourne proprement.

**Pourquoi c'est grave et pas anecdotique.** Le mode de defaillance n'est pas l'echec, **c'est
l'ecart muet** - exactement ce que `CLAUDE.md` decrit pour un `-pl` sans `-am`. Aujourd'hui il s'est
manifeste en erreur bruyante parce que la page etrangere n'avait pas le bouton attendu. Rien ne
garantit le sens de la prochaine fois : deux campagnes du meme jeu, l'une avec une regle injectee et
l'autre sans, se raccordant au meme navigateur, rendraient **le meme chiffre** - et ce chiffre
s'appellerait "delta mesure". De plus, le champ `viewport` que ces rapports ecrivent vient du
**parametre**, jamais d'une lecture du navigateur : un rapport peut donc porter `744 x 551` alors
que les pixels viennent d'une fenetre d'un autre agent.

**Ce n'est pas le seul defaut de cette famille, et le second est trouve le meme jour par quelqu'un
d'autre.** `nadia-prof` etablit dans `749244a` que `mesurer-candidats-rep-8.py` injecte la regle
candidate **une seule fois**, avant la premiere partie, et que la sortie de partie est un
`<a href="/">` ordinaire, donc une navigation complete qui recree le `<head>` et **jette la regle**.
Une execution a deux parties melange donc, dans un meme fichier, des items mesures avec la regle et
des items mesures sans - **sans le dire**. Elle l'a constate sur une de ses propres executions
(`parties: 2`, trois bas de verdict coexistant dont un a la valeur "sans regle"), a verifie que les
trois releves d'`ines-design` portent `parties: 1` et ne sont donc pas touches, et me le route.
**J'ai fait la meme verification sur mes trois releves de la section 1.3 : `parties: 1` partout.**

**Deux defauts, une seule cause de fond** : ces outils **decrivent leurs conditions de mesure par
leurs parametres, jamais par une lecture de ce qui s'est reellement passe**. Le port suppose que le
navigateur est le sien ; le `viewport` du rapport recopie l'argument ; la regle injectee est
supposee encore la. Aucun des trois n'est verifie a la lecture. C'est **un chiffre sans methode
grave dans un outil**, et il produira tot ou tard un ecart muet.

**Et c'est encodable, sans aucune dependance de poste.** Trois juges purs, testables par entrees
fabriquees et refusant bruyamment : le port de deverminage repond-il **avant** qu'on lance quoi que
ce soit ; la regle candidate est-elle **encore presente** dans le document a chaque lecture ; le
`viewport` ecrit dans le rapport est-il **lu** du navigateur et non recopie de l'argument. Le job
`outils` de la CI execute deja des juges de cette forme (`ADR-0009`, `ADR-0025`). **Cout : trois
fonctions, quelques cas de test, aucune dependance neuve.** C'est, de tout ce que cet avis propose,
le geste avec le meilleur rapport entre ce qu'il coute et ce qu'il empeche - et c'est le seul de la
section 2 que je qualifie de **faisable en l'etat, ce sprint**.

**Une precision qui evite un faux debat** : `nadia-prof` a **leve** sa limite `--hide-scrollbars`
le 2026-08-27, au seul viewport 744 x 551, sur mesure (une barre peinte prend 15 px a la mise en
page, et a ce viewport `.container` est deja plafonne sous 729 px, donc la rangee, le nombre de
rangees et le bas de l'indice sont identiques au centieme). **Cela ne concerne pas les defauts
ci-dessus** : ils ne portent pas sur les options du navigateur mais sur ce que le script croit
savoir de lui.

## 3. Le contrat de forme des paquets neufs : encodable ou non, regle par regle

`ines-design` en propose quatre. **Une seule doit devenir un test**, et ce n'est pas seulement mon
avis d'architecte : `nadia-prof` a tranche le meme jour (`749244a`, section 6 de son arbitrage) en
autorisant les regles 1 et 4, et en refusant les regles 2 et 3 **comme veto**, chiffres a l'appui.
Nos deux analyses convergent regle par regle, par deux routes differentes - la sienne
pedagogique et mesuree en pixels de police rendue, la mienne architecturale. Je donne les deux,
parce que quand elles concordent, la regle qui en sort tiendra.

### 3.0 La mesure de la dette d'abord, parce qu'un contrat qu'on ne peut pas appliquer au catalogue existant n'est pas un contrat

**Methode, perimetre et date** : les 18 fichiers de
`adapters/adapter-content/src/main/resources/contenu/` lus par un analyseur YAML, le 2026-08-27,
sur `9e2e31a`. Pour chaque item, le nombre de propositions est la taille de l'ensemble
`{bonneReponse} union {distracteurs}`, ou le pool du paquet quand l'item n'a pas de distracteurs
propres. Le paquet combinatoire `cp-additions-jusqu-a-10.yaml` n'a pas d'items a lire et est
declare tel.

| Paquet servi | items | max propositions | items a plus de 3 propositions | libelle le plus long |
|---|---|---|---|---|
| `cm1-accords-groupe-nominal` | 24 | **4** | **8** | 9 |
| `cm1-conjugaison-present` | 25 | **4** | **25** | 9 |
| `cm1-grands-nombres` | 28 | 3 | 0 | **40** |
| `cm1-perimetre` | 22 | 3 | 0 | **26** |
| `cm1-reperes` | 16 | 3 | 0 | **21** |
| `cm1-mesures` | 24 | 3 | 0 | **14** |
| `cm1-accord-sujet-verbe` | 21 | 3 | 0 | **11** |
| `cm1-antonymes` | 17 | 3 | 0 | **11** |
| `cm1-synonymes` | 16 | 3 | 0 | 10 |
| `cm1-homophones` | 24 | 2 | 0 | 4 |
| `cp-nature-mot` | 16 | 2 | 0 | 5 |
| `cm1-tables-multiplication` | 99 | 3 | 0 | 3 |
| `cp-comparer-nombres` / `cp-complements-10` / `cp-le-bon-son` / `cp-problemes-additifs` / `cp-soustractions` | 24 / 11 / 15 / 22 / 24 | 3 | 0 | 1 a 2 |
| `cp-additions-jusqu-a-10` | combinatoire | - | - | - |

**Trois faits que cette table etablit et que rien du dossier ne disait :**

1. La dette de la regle 1 vaut **exactement deux paquets, 33 items** : `cm1-accords-groupe-nominal`
   et `cm1-conjugaison-present`. C'est petit, et c'est chiffrable au paquet pres - donc un cliquet
   est possible dont **le plafond vaut exactement la taille de la dette**, ce qui est ma propre
   condition pour appeler ca un cliquet.
2. La dette de la regle 2 vaut **six paquets sur dix-huit**, dont un a **quatre fois la limite**
   (`cm1-grands-nombres`, 40 caracteres). Un cliquet a six exemptions sur dix-huit exempte un tiers
   du catalogue : c'est une regle qui, en pratique, ne s'appliquerait qu'aux paquets neufs, et il
   faut le dire ainsi plutot que de le deguiser.
3. **Les items a trois rangees de propositions ne viennent pas d'un quatrieme bouton, ils viennent
   d'un libelle long.** `cm1-grands-nombres` - le pire jeu du releve de `REP-8`, deficit 164,19 px -
   n'a **jamais plus de trois propositions**, et des libelles jusqu'a 40 caracteres. C'est un fait
   qui deplace la lecture du probleme, et j'y reviens en section 1.

### 3.1 Regle 1 - au plus 3 propositions par item : **encodable**, et elle mordrait

Perimetre derive du **repertoire de contenu**, jamais d'une liste - `ADR-0013`, obligation 1, et le
motif nomme par `ChaquePaquetPorteUnIndiceTest` : un paquet retire du catalogue mais laisse sur
disque doit rester vu. Chargement par le **chargeur reel**, pas par une relecture du YAML, pour
qu'un type de contenu dont la fabrique perdrait une proposition en route rougisse aussi.

**Ce qu'elle interdirait reellement** : elle interdirait qu'un paquet neuf entre au catalogue avec
quatre propositions, donc qu'il tombe dans le repli a quatre colonnes (police 1,875rem, deux
rangees) qui, a lui seul, a pousse le bas du cabinet a 492 px sur 551 sur `cm1-reperes`
(`FICHE-bandit-manchot-reperes-cm1.md`, correction du 2026-08-26). Ce n'est pas une regle
decorative : elle a un cas rouge reel et deja constate.

**Ce qu'elle n'interdirait pas** : que l'item passe sous le pli pour une autre cause. Fait 3
ci-dessus : les pires items du catalogue la respectent deja.

**Cout** : une classe de test dans `adapter-content`, un jeu fautif commite, une liste d'exemption
de **deux entrees** qui ne peut pas grandir. Le patron existe et est copiable tel quel.

### 3.2 Regle 2 - libelles de 10 caracteres au plus : **encodable, et il ne faut pas l'encoder**

**J'allais recommander de l'ecrire, avec un cliquet de six paquets. J'avais tort, et le chiffre qui
me corrige n'est pas le mien.**

Mon objection d'architecte etait la bonne famille mais la mauvaise force : c'est **un critere en
nombre de caracteres pour une propriete de pixels**, et `DEC-0016` decision 2 l'interdit nommement
pour le pli - dix `M` et dix `i` ne font pas la meme largeur. J'en concluais "encodable avec des
precautions". **`nadia-prof` etablit le 2026-08-27 que c'est faux, et pour une raison qu'aucune
precaution ne repare** : passee sur les 428 items des 17 paquets servis, cette regle ecarterait
**35 items**, dont `cm1-reperes` **16 sur 16** et `cm1-grands-nombres` 13 sur 28 - et elle
supprimerait **deux competences des referentiels**. Sa mesure, en pixels de police reellement
rendue : `quatre-vingt-seize mille deux cent trois` mesure **590,05 px**, soit 3,35 fois la
colonne ; `Point d'interrogation` mesure **317,69 px**, 1,8 fois la colonne, **et c'est le mot du
referentiel**. Son verdict, au mot : *"un contrat de 10 caracteres ne raccourcit pas ces libelles :
il supprime les competences."*

**Ce que cela dit de mon cliquet a six exemptions, et je l'ecris contre moi** : un cliquet dont
l'exemption doit contenir un paquet **entier** deja valide (`cm1-reperes`, 16 sur 16) n'exempte pas
une dette, il exempte la competence elle-meme. C'est **precisement le motif de refus d'`ADR-0028`** -
un garde-fou declaratif *"serait de plus rouge sur du contenu que `nadia-prof` a valide"* -, et je
ne l'avais pas reconnu sous cette forme-ci. **Regle 2 : ne pas encoder.** Elle vit comme conseil de
premier recours, ce que `nadia-prof` autorise explicitement.

**Une concordance qui vaut d'etre notee, parce qu'elle valide les deux mesures** : son perimetre de
**428 items sur 17 paquets** et le mien (table 3.0, somme des items des 18 fichiers moins le paquet
combinatoire) donnent **exactement le meme nombre**, par deux lectures independantes du meme
repertoire. Et sa regle 1 ecarterait **33 items sur 428** - `cm1-conjugaison-present` 25,
`cm1-accords-groupe-nominal` 8 - ce qui est **item pour item** ce que ma table 3.0 rend. Deux
methodes, deux auteurs, deux chiffres identiques : c'est ce qui me fait tenir la table 3.0 sans
reserve.

### 3.3 Regle 3 - l'enonce tient sur une seule ligne a 744 px : **non encodable**, et refusee aussi

C'est **la meme propriete que le pli**, un cran plus haut dans la pile : une largeur de texte rendu.
Les trois motifs de la section 2.1 s'appliquent mot pour mot, et la tentation a nommer pour qu'elle
soit refusee explicitement est d'encoder "une seule ligne" par un nombre maximal de caracteres
d'enonce - vert sur un enonce court en glyphes larges qui passe sur deux lignes, rouge sur un
enonce long en glyphes etroits qui tient sur une.

**Et la porte pedagogique la refuse aussi, en veto, avec son propre chiffre** : 47 caracteres tiennent
sur une ligne a 744 px (mesure de `nadia-prof` du 2026-08-27, `budget-de-forme.py`), et la regle
ecarterait **80 items sur 428**, dont `cp-problemes-additifs` **22 sur 22** - c'est-a-dire *"la
resolution de problemes en CP, en entier"*, une situation ne tenant pas en 47 caracteres. **Regle
3 : ne pas encoder, et ne pas ecrire en critere bloquant.**

### 3.3 bis Regle 4 - la porte de rendu avant fusion : la seule que `nadia-prof` erige en porte

*"Un paquet neuf entre au catalogue si, joue a 744 x 551, aucun item ne laisse l'indice ni le
verdict sous le pli. Comment l'auteur y arrive ne regarde ni `ines-design` ni moi."*

**Du point de vue de l'architecture, c'est la bonne forme** : elle parle du **resultat** au lieu de
prescrire le moyen, donc elle ne peut pas fermer une competence. Ce n'est pas un test et ne le
deviendra pas (section 2). **Deux exigences pour qu'elle vaille quelque chose :**

1. elle est nommee dans les criteres d'acceptation **comme etape de revue**, jamais comme quelque
   chose que la CI tient - sans quoi elle devient une promesse non tenue, la septieme dette de
   `CLAUDE.md` ;
2. **le defaut d'outil de la section 2.4 est ferme d'abord.** Une porte dont l'instrument peut
   rendre le verdict d'un autre navigateur n'est pas une porte. C'est le prealable technique du
   contrat, et il est petit.

### 3.4 Verdict de la section 3

**Faisable moyennant un `ADR`, et il ne porte qu'une regle sur quatre.** L'`ADR` encode la **regle
1** - au plus trois propositions par item - avec son cliquet chiffre de **deux paquets, 33 items**,
population derivee du repertoire de contenu et chargement par le chargeur reel. Il porte aussi, dans
sa section "Regle executable", les **trois decisions de ne pas encoder** : la regle 2 et la regle 3
avec leur motif chiffre par `nadia-prof` et le rappel d'`ADR-0028`, et la regle 4 avec le mecanisme
qui la garde a la place - une etape de revue nommee, et le correctif d'outil de la section 2.4 comme
prealable. C'est la variante "voici pourquoi ce n'est pas encodable et voici ce qui la garde" ; elle
n'est jamais vide, et ici elle est plus longue que la regle qui l'est.

**Ce que je retiens de ce point pour moi** : j'etais pret a ecrire une regle que la porte
pedagogique aurait rendue rouge sur un paquet deja valide. Ce n'est pas la regle qui m'a arrete,
c'est sa mesure a elle. **Un contrat de forme ecrit sans passer par le referentiel n'est pas un
garde-fou, c'est une amputation qui a l'air d'un garde-fou.**

## 4. L'obstacle des 95 items sur 296 : remesure faite ce jour, et il s'aggrave

`camille-po` etablit que `REP-8` ne le ferme pas par construction et qu'une remesure dediee m'est
due avant tout statut (`DEC-0051`, section 3). **Je l'ai faite.**

### 4.1 D'abord, la citation de mon propre chiffre, verifiee a sa source primaire

`DEC-0051` section 3 decrit mon perimetre de s20 comme *"10 jeux, tous `couvert-valide`"*. **Ma
source primaire dit autre chose**, et je le signale parce que la regle vaut aussi quand c'est mon
chiffre qu'un autre reprend
(`squad/sprints/s20-mot-cible-designe-et-conception-de-la-saisie/mesure-budget-vertical.md`,
sections 0 et 6, mesure du 2026-08-25) :

- le perimetre etait **15 jeux mesures sur 16 servis**, 296 items - pas 10 jeux. **10 est le nombre
  de jeux qui portaient au moins un item sous le pli**, pas le nombre de jeux regardes. Les cinq
  autres sont dans la table, ligne "cinq autres jeux, 95 items, 0 sous le pli" ;
- "tous `couvert-valide`" ne vient pas de ma section 6 non plus : j'y ecris *"dont **deux**
  (`cm1-synonymes`, `cm1-antonymes`) sont `couvert-valide`"*, formulation qui exclut le "tous".

**Ce que cela ne change pas** : la conclusion de `camille-po` tient entierement, et pour les motifs
qu'elle donne - deux mesures de populations, d'etats et de metriques differents ne se somment pas.
**Ce que cela change** : l'ecart entre les deux perimetres est plus grand qu'annonce, donc l'idee
que `REP-8` puisse fermer celui-ci par ricochet est encore moins soutenable.

### 4.2 La remesure, avec sa methode, son perimetre et sa date

**Methode, identique a celle de s20 pour que les deux chiffres soient comparables** : outil
`squad/outils/theo-archi/mesurer-budget-vertical.py`, non modifie ; metrique = **bas du bloc
`.message-jeu` entier** (signal, verdict, explication, indice) contre un pli a 551 px ; etat le plus
charge (`ECHECS=3`, la revelation) ; `PARTIES_MAX=12`, `PLAFOND_ITEMS=24` ; viewport 744 x 551
(`DEC-0040`) ; perimetre derive de `catalogue.yaml`, jamais d'une liste. Application servie depuis
le fat jar reconstruit par le `clean verify` de la section 0. **Date : 2026-08-27, tete `9e2e31a`.**

**17 jeux mesures sur 18 servis.** `bandit-manchot-additions-cp` est **declare saute par l'outil** :
paquet combinatoire, aucun item a lire - meme exclusion qu'a s20 et que chez `ines-design`.
**`scrollY = 0` sur toutes les lectures des 17 jeux**, verifie et non suppose.

| Jeu, 744 x 551, 2026-08-27 | items vus | sous le pli | pire deficit (px) |
|---|---|---|---|
| `bandit-manchot-problemes-additifs-cp` | 22 | **22** | 2,19 |
| `bandit-manchot-accord-sujet-verbe-cm1` | 21 | **20** | 32,19 |
| `bandit-manchot-perimetre-cm1` | 22 | **20** | 162,19 |
| `bandit-manchot-reperes-cm1` | 16 | **16** | 92,19 |
| `bandit-manchot-grands-nombres-cm1` | 24 | **15** | **192,19** |
| `bandit-manchot-accords-groupe-nominal-cm1` | 24 | 8 | 68,19 |
| `bandit-manchot-synonymes-cm1` | 16 | 7 | 32,19 |
| `bandit-manchot-le-bon-son-cp` | 15 | 6 | 8,19 |
| `bandit-manchot-conjugaison-present-cm1` | 24 | 3 | 32,19 |
| `bandit-manchot-conversions-unites-cm1` | 24 | 2 | 44,19 |
| `bandit-manchot-homophones-cm1` | 24 | 2 | 2,19 |
| `bandit-manchot-antonymes-cm1` | 17 | 1 | 2,19 |
| `bandit-manchot-comparer-nombres-cp` | 24 | 0 | - |
| `bandit-manchot-complements-10-cp` | 11 | 0 | - |
| `bandit-manchot-nature-mot-cp` | 16 | 0 | - |
| `bandit-manchot-soustractions-cp` | 23 | 0 | - |
| `bandit-manchot-tables-multiplication-cm1` | 25 | 0 | - |
| **Total** | **348** | **122 (35 %)** | **192,19** |

**122 items sur 348, sur 12 jeux touches sur 17 mesures**, contre **95 sur 296, sur 10 jeux touches
sur 15 mesures** le 2026-08-25. Releve commite :
`squad/conformite/releves-s21/s21-budget-vertical-catalogue-744x551.json` (17 jeux, deficit et
nombre de rangees item par item).

### 4.3 Ce que la comparaison des deux chiffres autorise, et ce qu'elle n'autorise pas

**Ce n'est pas une aggravation du produit de 27 items.** Trois causes de l'ecart, chacune verifiee :

1. **Le catalogue a change** entre les deux dates. `cp-nature-mot` est revenu (`DEC-0048`),
   `cm1-reperes` est entre puis sorti puis revenu (`DEC-0049`, `DEC-0050`) : deux jeux de plus dans
   le perimetre, dont un (`reperes`) a **16 items sur 16** sous le pli.
2. **La population est un plancher echantillonne, jamais un inventaire.** Un tirage voit plus ou
   moins d'items d'un paquet plus grand que la manche - `perimetre-cm1` passe de 20 a 22 items vus,
   `grands-nombres-cm1` de 22 a 24, et son pire deficit de 164,19 a **192,19 px** parce que ce
   tirage-ci a pris un item que l'autre n'avait pas pris. **Aucune des deux mesures n'est fausse.**
3. **Une divergence que je ne sais pas expliquer, et je la nomme plutot que de l'habiller.**
   `bandit-manchot-le-bon-son-cp` rend **6 items sous le pli** aujourd'hui contre **0** le
   2026-08-25. C'est le seul paquet servi qui porte des explications (6 items sur 15) : la
   revelation y ajoute une boite, ce que ma propre section 0 de s20 ecrivait deja. Deux causes sont
   possibles - un changement de rendu de l'enonce entre les deux dates (`S20-1` a modifie
   `fragments/manche.html` et `recreo.css`), ou un rapport de s20 fausse par le defaut d'outil de la
   section 2.4 - **et je ne peux pas trancher, parce que les rapports par jeu de s20 n'ont pas ete
   commites.** C'est une faute de methode qui est la mienne : une mesure dont le detail n'est pas
   au depot n'est pas rejouable, elle est seulement citable. **Corrige a partir d'aujourd'hui** :
   le releve de la section 4.2 est commite avec le detail item par item.

**Ce que je prononce, et ce que je ne prononce pas.** Je ne prononce **aucun statut de couverture**,
ni sur ces 122 items ni sur les 95 de s20 : que l'indice doive ou non etre au-dessus du pli est la
question de `nadia-prof`, et une part de ces items releve de reserves qu'elle a deja explicitement
acceptees (`SYN-16`, `BPM-8`). Ce que j'apporte est un chiffre a jour et un releve rejouable.

**Ce que la remesure etablit pour le planning, et c'est le point utile** : l'obstacle **n'est pas
ferme**, il porte aujourd'hui sur **douze jeux sur dix-sept**, et il **recouvre largement `REP-8`
sans lui etre identique** - les 9 jeux d'`ines-design` sont tous dans mes 12, les 3 de plus
(`le-bon-son-cp`, `conjugaison-present-cm1` sur la revelation, `antonymes-cm1`) ne sortent que
parce que ma metrique regarde le bloc entier au troisieme echec. **Corriger `REP-8` sur l'indice
apres un echec laisse ouverte la question du bloc entier apres la revelation**, et c'est
exactement ce que `camille-po` avait pose sans pouvoir le chiffrer.

## 5. L'`ADR` mal cite : l'erreur n'est ni dans un `ADR` ni dans l'index - elle est dans un test Java commite

`ines-design` a raison sur le fond, et j'ai verifie sa verification plutot que de la reprendre.
**La fiche est deja corrigee** : `nadia-prof` a retire la reference dans `749244a` le 2026-08-27,
en la datant et en nommant la source reelle. Ce qui suit porte donc sur ce qui reste, et ce qui
reste est de mon ressort.

**Ce qu'`ADR-0029` decide** (lu a la source, `ADR-0029-toute-colonne-que-le-contenu-peut-produire-porte-son-decalage.md`,
2026-08-11) : *"faire porter un decalage declare a toute colonne que le contenu peut produire"* -
`animation-delay`, borne de 250 ms de `nadia-prof`, et le fait qu'aucun paquet du depot ne depasse
quatre propositions. **Il ne dit pas un mot de la police ni du rembourrage de `.proposition`.**

**D'ou vient reellement la forme du repli a quatre colonnes** : `recreo.css` (ligne 332 et
suivantes) attribue lui-meme le plancher de 1,875rem a l'arbitrage prealable de `nadia-prof`
(`FICHE-bandit-manchot-conjugaison-present-cm1.md`, section C, `S8-5`), et
`TailleDesTextesEtBoutonsTest` (`adapter-web`) le garde sous ce motif-la, en le nommant dans ses
messages d'assertion. `ADR-0050` cite la meme filiation. La chaine est coherente **partout sauf
aux deux endroits ci-dessous**.

**Ce que j'ai verifie de mon cote, et le resultat :**

| Endroit verifie | Methode | Resultat |
|---|---|---|
| Les 62 `ADR` de `squad/decisions/adr/` | `grep -rn "ADR-0029"` sur le repertoire, chaque occurrence lue en contexte | **aucune erreur** : `ADR-0028`, `ADR-0031`, `ADR-0032` le citent tous les trois pour le decalage d'animation ou pour le plafond de quatre colonnes qui en decoule |
| `ADR-index.md` | meme `grep`, deux occurrences (ligne 35, ligne 372) | **aucune erreur** : le libelle de l'index est le titre exact de l'`ADR`, et la relecture de la ligne 372 porte sur la forme de la solution (une pince de deux clauses), pas sur `.proposition` |
| Le code et les gabarits | `grep -rn "ADR-0029"` sur `*.java`, `*.css`, `*.html` | **une erreur, dans un fichier commite du reacteur** - ci-dessous |

**L'erreur qui est de mon ressort, et elle est plus durable que celle de la fiche.**
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCm1ReperesTest.java`,
Javadoc de la constante `PROPOSITIONS_PAR_ITEM`, ligne 73 :

> `ADR-0029` et le pli (verdict du 2026-08-26) : trois propositions par item, jamais quatre.

`ADR-0029` **n'interdit pas quatre propositions** : il borne a quatre ce que la feuille couvre, et
c'est exactement l'inverse - quatre est la valeur qu'il autorise, cinq celle qu'il fait rougir. La
regle "trois, jamais quatre" vient du pli (verdict de `nadia-prof` du 2026-08-26), pas de lui. Meme
famille a la ligne 162, ou la Javadoc de
`chaqueItemProposeExactementTroisPropositionsDontToujoursLaPhraseCorrecte` annonce *"`ADR-0029` :
jamais plus de quatre propositions sur un item"* au-dessus d'un test qui en exige exactement trois.

**Pourquoi je le signale plutot que de le corriger dans cet avis** : un avis de faisabilite ne
touche pas au reacteur, et le corriger ferait rougir la porte technique le temps d'un commit hors
lot pour rien. Mais je nomme la hierarchie, parce qu'elle n'est pas celle qu'on croit :
une fiche de conformite est lue par qui la cherche ; **une Javadoc de test est lue par le prochain
developpeur qui modifiera ce test**, et elle lui dira que trois propositions sont une exigence
d'architecture alors que c'est une exigence de pli. Un correctif de pli qui rendrait quatre
propositions a nouveau tenables se heurterait a un test qui invoque, pour refuser, un `ADR` qui ne
refuse pas. **Je le porte comme une correction a faire dans le lot `REP-8`** : deux lignes de Javadoc, aucune
assertion touchee. Et je note l'ordre dans lequel cette erreur a ete trouvee, parce qu'il est
instructif - `ines-design` l'a vue en verifiant une reference qu'on lui donnait, `nadia-prof` l'a
corrigee dans son propre fichier dans l'heure, et **c'est moi, auteur de l'`ADR` cite, qui ne
l'avais jamais vue**, alors que le fichier fautif est un test.

## 6. Sur quelle machine virtuelle les tests tournent reellement - et ce que mes propres ecrits disent de faux

C'est le point de cet avis qui me concerne personnellement, et je le traite comme tel : le chiffre
que j'ai ecrit douze fois est **juste**, la phrase qui l'explique est **fausse**, et c'est la phrase
qui compte, parce qu'elle serait restee vraie a l'ecrit meme si le chiffre etait devenu faux.

### 6.1 Ce que j'ai mesure aujourd'hui, et par quoi

Quatre sources, sur le `clean verify` de la section 0, le 2026-08-27 :

| Ce qui est mesure | Source primaire | Resultat |
|---|---|---|
| La machine virtuelle **de Maven** | premiere ligne du journal, ecrite par `outils/mvn-jdk21.sh` lui-meme | `Maven lance sur le JDK de la toolchain jdk 21 : 25.0.2` |
| La toolchain **retenue par Maven** | `[INFO] Found matching toolchain for type jdk`, puis `Toolchain in maven-compiler-plugin` et `Toolchain in maven-surefire-plugin`, une fois par module | une racine de JDK **21.0.10** |
| La machine virtuelle **des tests** | proprietes du rapport surefire d'un test execute ce jour (`adapter-content`) | `java.version = 21.0.10`, `java.vm.version = 21.0.10+8-LTS-217` |
| La cible du bytecode livre | octets 7 et 8 de l'en-tete d'une classe de `bootstrap/target/recreo.jar` | version majeure **65**, c'est-a-dire **Java 21** |

**Reponse a la question posee : les tests s'executent bien sur un JDK 21.0.10, et le produit est
bien compile en 21.** Ce n'est pas "compiler en cible 21 sur une machine virtuelle 25" : `javac` et
surefire tournent tous deux sur le 21.0.10 que la toolchain designe. **Seule la machine virtuelle de
Maven lui-meme** est un 25.0.2.

### 6.2 D'ou vient le 25.0.2, et pourquoi personne ne l'a vu

Il y a **deux fichiers de toolchains** sur ce poste, et ils ne disent pas la meme chose.

- Maven lit `${user.home}/.m2/toolchains.xml`, ou `user.home` est la propriete de la machine
  virtuelle Java, donc le **profil utilisateur Windows**. Ce fichier-la declare trois toolchains, et
  celle de version 21 designe un **JDK 21.0.10**. C'est celle que le journal montre retenue.
- `outils/mvn-jdk21.sh` lit `"${HOME}/.m2/toolchains.xml"`, ou `HOME` est la variable du shell
  POSIX. **Sur ce poste, `HOME` ne designe pas le profil utilisateur Windows mais un lecteur reseau
  monte.** Le fichier qui s'y trouve declare une seule toolchain, de version 21, dont la racine est
  un **JDK 25.0.2** (date de derniere modification de ce fichier : 2026-08-25).

Le script pose donc `JAVA_HOME` sur le JDK 25.0.2 - ce qui gouverne la machine virtuelle de Maven -
puis Maven, lui, resout sa toolchain dans **l'autre** fichier et compile en 21.0.10. Les deux
mecanismes fonctionnent chacun comme il est ecrit ; **c'est leur raccordement qui est imaginaire.**

**Le defaut du script, precisement.** Il n'y en a pas un, il y en a deux, et le second est le pire :

1. **Il lit un fichier que Maven ne lit pas necessairement.** `~/.m2/...` est une forme portable a
   l'ecrit (`ADR-0010`), et cela ne change pas ; mais `$HOME` dans un shell POSIX sous Windows et
   `user.home` dans une machine virtuelle Java ne sont pas garantis egaux, et ici ils ne le sont
   pas.
2. **Il annonce une version sans jamais la verifier.** Sa derniere ligne de diagnostic ecrit
   `Maven lance sur le JDK de la toolchain jdk 21 : 25.0.2` - il a la valeur en main, il l'imprime,
   et il ne la compare a rien. Une phrase qui contient sa propre refutation, affichee a chaque
   build, et lue par personne. C'est **la meme famille de defaut que ce depot sanctionne partout
   ailleurs** : une valeur affichee sans le critere qui la juge n'est pas une preuve, c'est une
   decoration.

### 6.3 Ce que cela vaut pour la porte technique, et ce que `CLAUDE.md` promet vraiment

**Les deux garde-fous du POM font exactement ce qu'ils promettent, et rien de plus :**

- `maven-enforcer-plugin` exige que **Maven** tourne sur un JDK 17 ou plus recent. Un 25.0.2 le
  satisfait. Ce n'est pas un trou : la regle nomme un plancher, pas une version exacte, et son motif
  ecrit dans le POM est de pouvoir charger `spring-boot-maven-plugin`. Elle tient.
- `maven-toolchains-plugin` exige une toolchain `jdk` de version 21 **dans le fichier que Maven
  lit**, et fait compiler et tester par celle-la. Elle tient aussi, et c'est elle qui produit le
  21.0.10 mesure.

**Ce que ni l'un ni l'autre ne garde, et personne ne le gardait** : que le JDK choisi par
l'enveloppe du depot soit celui que la toolchain designe. Aucun `mvn` ne peut le garder - c'est le
script qui pose `JAVA_HOME`, avant que Maven existe.

**Consequence pratique pour s21 : nulle sur la CI, reelle sur le poste.** En CI,
`actions/setup-java@v4` avec `java-version: "21"` ecrit la toolchain lui-meme et `mvn -B verify`
est lance sans l'enveloppe (`.github/workflows/ci.yml`, job `build`) : le defaut est **local, pas
partage**. Sur le poste, le risque est aussi plus modeste qu'il en a l'air : **une divergence de
machine virtuelle de Maven ne change pas le bytecode livre**, verifie ci-dessus. Ce qu'elle change,
c'est la confiance qu'on peut accorder a la phrase d'un rapport.

### 6.4 Ce que je corrige, et ce que je ne corrige pas

**Ce qui doit etre corrige, et je le porte comme dette explicite plutot que de le glisser dans cet
avis :**

1. `outils/mvn-jdk21.sh` doit **refuser** quand le JDK qu'il a trouve ne rend pas une version 21, au
   lieu de l'imprimer. C'est encodable, et a bas cout : `extraire_version_java` est deja une
   fonction pure de `outils/verifier-jdk.sh`, deja couverte par `outils/verifier-jdk-test.sh`, deja
   executee par le job `outils` de la CI (`ADR-0009`, `ADR-0025`). Un juge pur de plus - "cette
   version convient-elle a la version exigee" - se teste par entrees fabriquees, sans aucun poste.
   **Cout : une fonction, quelques cas de test, aucune dependance neuve.**
2. La ligne de diagnostic doit cesser d'affirmer un raccordement qu'elle ne verifie pas. Le moins de
   pieces mobiles : **le script cesse de pretendre nommer la toolchain de Maven.** Son seul role
   defendable est de poser une machine virtuelle Maven acceptable ; c'est Maven qui choisit ensuite
   le JDK de compilation, et c'est le journal du build qui le dit - c'est deja la, a chaque module,
   et c'est cette ligne-la qu'un rapport doit citer. Ce qui me ferait changer d'avis, et je
   l'ecrirai dans l'`ADR` : une machine ou `$HOME` et `user.home` seraient garantis egaux rendrait
   le raccordement vrai, et la simplification inutile.
3. **Mes douze `confirmation-archunit.md` portent une phrase a corriger, pas un chiffre.** Douze
   fichiers `squad/sprints/*/confirmation-archunit.md` contiennent `21.0.10` (methode : `grep -rln`
   sur ce motif, dans le worktree principal, le 2026-08-27). Celui de s20 ecrit : *"JDK 21.0.10,
   celui de la toolchain declaree, lu dans le journal du build [...] l'enveloppe du depot, qui lit
   la racine du JDK dans `~/.m2/toolchains.xml`"*. Le **21.0.10 est exact**, verifie aujourd'hui a
   trois sources. La **seconde moitie de la phrase est fausse** : l'enveloppe lit un autre fichier
   que Maven, et le JDK qu'elle en tire n'est pas celui qui a execute les tests.

**Ce que je ne fais pas** : je n'efface aucune de ces douze phrases dans cet avis. Les corriger est
un geste de redaction sur douze fichiers de sprints clos, dont plusieurs servent de base a des
deltas cites ailleurs ; c'est un lot, pas une note de bas de page. **Je demande a `sofia-sm` de le
planifier**, et je propose la forme la moins destructrice, celle que j'ai deja utilisee a s20 pour
ma propre mesure perimee : on ne remplace pas la phrase, on l'amende et on la date.

**La lecon que je retiens contre moi**, parce qu'elle est plus generale que ce defaut-ci : j'ai
ecrit douze fois *"celui de la toolchain declaree"* sans jamais verifier **quelle** toolchain, sur
un poste ou il y en a deux. C'est exactement l'erreur que je reproche ailleurs - un chiffre juste
dont la methode citee n'est pas celle qui l'a produit. Le chiffre survit a l'audit ; la methode,
non.

## 7. Synthese des six avis, en une table

| Point | Avis |
|---|---|
| **1. Fermer `REP-8`** | **Infaisable par la forme** (mesure de `nadia-prof`, 2026-08-27). **Faisable moyennant un `ADR`** par une declaration de position sur `.message-jeu` : 5 items sur 5 fermes sur le pire jeu, trois rangees comprises. **Suspendu a son arbitrage sur la superposition visuelle**, a demander des l'ouverture. |
| **2. Garder le pli** | **Non gardable en l'etat**, decide par `ADR-0028` et son declencheur non survenu (aucun conteneur de build au depot, verifie ce jour). Gardables a la place : la **declaration** du correctif, sur le modele d'`ADR-0059`. **Faisable ce sprint** : trois juges purs qui ferment les defauts d'outil des sections 2.4 - le seul geste de la section 2 que je qualifie de faisable en l'etat. |
| **3. Contrat de forme** | **Une regle sur quatre devient un test** : la regle 1 (au plus trois propositions), cliquet de deux paquets et 33 items. Regles 2 et 3 : **ne pas encoder**, motif chiffre par `nadia-prof` et motif d'`ADR-0028`. Regle 4 : etape de revue nommee, jamais un test, et conditionnee au correctif d'outil. |
| **4. Les 95 sur 296** | **Remesure faite** le 2026-08-27 : **122 items sur 348, 12 jeux sur 17**, releve commite avec le detail item par item. L'obstacle **n'est pas ferme** et recouvre `REP-8` sans lui etre identique. **Aucun statut de couverture prononce.** |
| **5. `ADR-0029` mal cite** | **Ni dans un `ADR` ni dans l'index** - les 62 `ADR` et les deux entrees d'index sont justes. La fiche est corrigee par `nadia-prof` le meme jour. **Il reste l'erreur dans un test commite**, `PaquetCm1ReperesTest`, lignes 73 et 162, a corriger dans le lot `REP-8`. |
| **6. La machine virtuelle** | **Les tests tournent bien sur JDK 21.0.10 et le bytecode livre est en 21** (trois sources + la version majeure de classe 65). **Seule la machine virtuelle de Maven** est un 25.0.2, parce que `outils/mvn-jdk21.sh` lit un autre fichier de toolchains que Maven. `enforcer` et la toolchain tiennent leurs promesses ; c'est l'enveloppe qui affirme sans verifier. Correctif encodable en juge pur ; douze `confirmation-archunit.md` portent une **phrase** a amender, pas un chiffre. |

## Ce que je route

- **`sofia-sm`** : les six avis ci-dessus. Deux choses conditionnent son `planning.md` et rien
  d'autre : **l'arbitrage de `nadia-prof` sur la superposition visuelle** (section 1.3), qui decide
  si `REP-8` est fermable ce sprint ; et le fait que **l'objectif ne doit pas etre ecrit "fermer
  `REP-8` sur tout le catalogue"** tant que cet arbitrage n'est pas rendu. Le correctif d'outil de
  la section 2.4 est petit, faisable en l'etat, et **prealable** a la porte de forme du contrat de
  paquet neuf - il est a placer avant tout paquet neuf, pas apres. Enfin, la correction de mes douze
  `confirmation-archunit.md` (section 6.4) est un lot a planifier, pas une note de bas de page.
- **`nadia-prof`** : **une seule question, et elle est sur le chemin critique.** La declaration de
  position de la section 1.3 fait que le bloc de message, quand il lie, **recouvre visuellement le
  bas du meuble**. `M1` et `M4` disent *"sous le meuble, hors du cadre du cabinet - jamais
  au-dessus"* : l'ordre du document et l'ordre de lecture ne changent pas, la superposition
  visuelle, si. **De quel cote de sa frontiere tombe-t-elle ?** Releves commites
  (`s21-levier-position-message-*`), et son verdict sur les leviers de forme n'est pas en cause :
  celui-ci n'en est pas un.
- **`ines-design`** : le levier de la section 1.3 n'entre pas dans son mandat de forme, mais **tout
  ce qui l'habille en sort** si `nadia-prof` l'accepte - fond, separation, ombre. Le `background`
  de ma regle candidate est un artefact de mesure, pas une valeur proposee. Et le defaut de son
  outil (section 2.4, regle injectee jetee a la navigation) m'est route par `nadia-prof` ; je le
  porte avec les deux autres.
- **`camille-po`** : la citation de mon perimetre de s20 dans `DEC-0051` section 3 (section 4.1 de
  cet avis : 15 jeux mesures et non 10, et "tous `couvert-valide`" ne vient pas de ma source). Sa
  conclusion tient entierement ; c'est l'ecart des perimetres qui est plus grand qu'annonce. Et le
  chiffre a jour de l'obstacle - **122 sur 348** - pour l'instruction qu'elle voudra en faire.
- **`marc-dev`**, **`lea-dev`** (via `sofia-sm`) : la correction des deux Javadoc de
  `PaquetCm1ReperesTest` (section 5), dans le lot `REP-8`.

## Ce que je ne fais pas

- Je ne redige aucune story, je n'en repriorise aucune, je n'ecris ni `planning.md` ni le
  repertoire de sprint.
- Je n'ecris aucun des trois `ADR` que cet avis rend possibles : un avis dit si une decision est
  ecrivable, il ne la prend pas.
- Je ne prononce **aucun** statut de couverture, ni sur `REP-8`, ni sur les 122 items de la
  section 4, et **aucun Done**, technique excepte pour ce qu'il vaut - la CI est verte, et une CI
  verte ne dit rien de ce qu'un enfant voit.
- Je ne touche a aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/` : aucune
  ligne de production, aucun gabarit, aucune feuille de style n'est modifiee par cet avis. Les
  regles candidates de la section 1.3 sont **injectees dans le document rendu**, jamais ecrites.

## Impact

- `squad/conformite/avis-faisabilite-s21.md` : cree (ce fichier). `sofia-sm` le deplacera dans le
  repertoire de sprint qu'elle creera.
- `squad/conformite/releves-s21/s21-budget-vertical-catalogue-744x551.json` : cree - remesure de la
  section 4, 17 jeux, 348 items, deficit et nombre de rangees item par item.
- `squad/conformite/releves-s21/s21-levier-position-message-baseline-grands-nombres.json`,
  `...-sticky-...json`, `...-fixe-...json` : crees - les trois releves de la section 1.3.
- Aucun autre fichier n'est modifie. Aucun `ADR`, aucune `DEC`, aucun index n'est touche par cet
  avis : `ADR-index.md` reste exact, verifie en section 5.
