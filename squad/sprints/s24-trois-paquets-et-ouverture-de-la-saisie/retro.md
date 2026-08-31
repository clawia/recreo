# [Sprint s24] Retrospective - 2026-08-31

- **Anime par** : sofia-sm (casquette Scrum Master)
- **Perimetre** : le sprint 24, du planning du 2026-08-30 22:32 a la review du 2026-08-31 11:45.
- **Ce document ne prononce aucun Done**, ne tranche aucun produit, aucune architecture, aucune
  pedagogie. Il propose des amendements a `squad/README.md` ; ceux qui touchent la conduite d'equipe
  sont de ma main, les autres sont routes.

---

## 1. Ce qui a marche

### 1.1 Le lot 5a place au jour 1 - le seul geste de planning qui a change l'issue du sprint

`theo-archi` avait ecrit du lot 5 : *"c'est le seul lot dont je ne reponds pas [...] le poste que je
surveillerais si le sprint devait deraper."* Le planning l'a scinde en **5a - le tri ecrit** et
**5b - l'execution**, sur l'observation que *"lire ne demande pas que le paquet existe, seul le vert
le demande"*, et a place le 5a **au jour 1, en parallele du lot 1**.

**Ce qui en est sorti** : quatorze decisions motivees, zero muette, et **le point de bascule ne s'est
jamais declenche**. Le poste dont l'architecte ne repondait pas est devenu le premier travail engage
au lieu du dernier decouvert.

**Ce que cela a aussi produit et qui n'etait pas prevu** : le tri, une fois execute, a **trouve deux
garde-fous de plus** que la lecture n'avait pas vus, tous deux dans `bootstrap` - hors du perimetre
`adapter-content` sur lequel les sondes avaient ete mesurees. Le document s'est amende lui-meme
(section 5 bis) au lieu de tenir son chiffre. **Un tri qui se corrige apres execution vaut mieux
qu'un tri qui reste juste sur le papier.**

### 1.2 Le point de bascule ecrit d'avance, avec un declencheur objectif

Le declencheur portait sur un lot **affecte a moi-meme sous casquette dev**. L'ecrire d'avance,
verifiable par un tiers (un fichier existe avec ses decisions motivees, deux lots sont fusionnes
verts) et constatable par `theo-archi`, a supprime la possibilite que je juge mon propre retard.
**Il n'a pas eu a mordre - et c'est precisement pourquoi il valait la peine d'etre ecrit** : un
declencheur qui ne se declenche pas n'est pas un declencheur inutile, c'est un declencheur dont la
menace a suffi.

Et `camille-po` a rendu l'amendement a `DEC-0059` **avant** le declencheur, comme demande. La lecon
de la cloture de s21 - une story absorbee ou abandonnee s'acte par un amendement dans le meme geste -
a fonctionne pour la premiere fois **en amont** au lieu d'etre constatee a la cloture.

### 1.3 Quatre agents ont refuse de recouvrir une exigence qu'ils ne satisfaisaient pas

C'est le fil le plus net du sprint, et il est collectif.

- **`theo-archi`** decline explicitement l'exigence 2 de `nadia-prof` : *"je ne la satisfais pas, je
  propose autre chose a sa place et c'est a elle de dire si cela lui suffit."*
- **`nadia-prof`** le note comme ce qui rend ses exigences utiles - *"c'est la premiere fois dans ce
  sprint qu'un agent decline une de mes exigences au lieu de la recouvrir"* - et amende son exigence
  **par ajout**, en laissant le libelle anterieur lisible a sa place.
- **`marc-dev`** refuse quatre contournements qui auraient rendu sa branche verte, et ecrit lequel et
  pourquoi : ecrire les propositions sous une forme que la lecture de valeur ecarte *"rendrait un
  garde-fou aveugle par la redaction"*.
- **`lea-dev`** verse trois corrections trouvees **en jouant le jar**, sur une CI verte avant comme
  apres, dont une regularite qui faisait gagner sept items sur vingt sans lire le texte.

**Le point commun** : chacun a nomme ce que son travail ne couvrait pas. Aucun n'a laisse un vert
tenir lieu de preuve.

### 1.4 La lignee des worktrees - deuxieme sprint consecutif sans occurrence

Huit branches verifiees par moi avant fusion, **toutes issues de `4352fe8`**, tete de `s24-sofia`.
Zero provisionnement sur la mauvaise branche, apres cinq occurrences sur le seul s23 et une serie
ininterrompue depuis s03. La confirmation quotidienne demandee au daily (amendement de la retro de
s23) tient.

---

## 2. Ce qui a coince

### 2.1 Deux enonces transmis sans etre mesures ont circule comme vrais - et c'est la lecon du sprint

**Le fait, dans les mots de la review** (section 4.3) : *"le lot 3 n'existe pas, donc le chemin C est
ecarte pour s24"* - **recu de MASTER, le 2026-08-31, sans source dans le depot**. Le lot 3 etait
pousse **quatorze minutes** avant que la phrase qui le nie ne soit ecrite.

**Ce qui rend ce cas different des deux autres faits de MASTER, et pire** :

- le chiffre de 28 a 33 etait **faux et verifiable** : deux agents l'ont instruit et l'ont corrige ;
- l'affirmation du JDK 25 etait **fausse et a fait chercher** : la recherche a trouve un vrai defaut
  de methode ;
- **celui-ci a ete relaye sans etre verifie contre le recensement dont il pretendait decouler**. Ce
  n'est pas un enonce faux qu'on corrige : c'est une **inference** posee sur un fait non mesure, et
  presentee comme acquise. Elle serait entree dans la porte de `nadia-prof` et y aurait referme
  27 jeux sur un lot que personne n'avait regarde.

**Ce qui l'a arrete, et c'est exactement le mecanisme prevu** : `nadia-prof` est allee voir. Elle
ecrit : *"c'est la deuxieme fois en un jour que la verification d'un enonce relaye change la
reponse."* La borne `Q6` de `DEC-0062` - **un enonce recu de MASTER ne vaut jamais motif** - venait
d'etre acceptee le matin meme. **Elle a servi le jour de sa naissance.**

**Ce que la retro en retient, et qui ne vise personne en particulier** : le canal de transmission
n'est pas ce qui rend un enonce vrai. Un fait relaye par le Scrum Master, par l'architecte ou par
MASTER a exactement la meme valeur - **celle de la mesure qui l'accompagne, et rien d'autre.** La
regle existe ; ce qui a manque n'est pas la regle, c'est de l'appliquer a un enonce qui arrangeait
tout le monde.

### 2.2 Deux verts d'agents ont produit un rouge apres fusion, et le sprint n'atteint pas son Done technique

`ADR-0072` et son garde-fou sont verts sur `s24-theo`. Le paquet de saisie et ses quatorze garde-fous
sont verts sur `s24-sofia-dev`. **Fusionnes, ils sont rouges** : le garde-fou d'`ADR-0072` est le
**quinzieme** garde-fou de contenu, ecrit apres le tri, et il transtype `(Question)` sans classer les
formes d'item.

**Ce n'est pas une faute d'agent** : chacun a mesure ce qu'il avait, l'a dit avec son perimetre, et
son chiffre etait exact sur son arbre. **C'est une faute de sequencement, et elle est de moi** : le
planning avait prevu l'ordre de fusion des lots entre eux, **il n'avait rien prevu pour un garde-fou
neuf ecrit pendant le sprint par un agent qui n'etait pas dans la chaine de fusion.**

**Ce que le sprint aurait du avoir** : le lot 5a a etabli une liste fermee de garde-fous de contenu a
un instant donne. **Rien n'a rattache a cette liste les garde-fous nes apres elle.** `ADR-0072` est
ne d'une correction de `lea-dev` le meme jour - c'est une bonne chose que le sprint ait produit ce
garde-fou ; ce qui manque est le geste qui l'aurait fait passer par le tri.

### 2.3 `yanis-qa` et le `relecteur-pedagogique` n'ont pas ete invoques

**Aucun rapport qualite, aucune liste de corrections sur ce sprint.** `squad/README.md` liste les
deux comme contributions obligatoires de `review.md`. La review de s24 les porte comme absents, ce
qui est la seule chose honnete a en faire, mais c'est un manque de conduite de ceremonie **et il est
de moi** : je ne les ai pas appeles.

**Ce que cela a coute, concretement** : le rouge de la section 2.2 est exactement ce qu'un passage
qualite sur l'arbre fusionne aurait trouve avant que je le trouve a la porte. Et l'obstacle 7 de la
review - `squad/README.md` decrit encore une integration H2 supprimee du produit il y a dix-sept
jours - est exactement ce qu'une relecture aurait vu.

### 2.4 Le rapport de tokens ne trouve pas les transcriptions depuis un worktree

`python outils/rapport-tokens.py --sprint s24` lance depuis le worktree de review rend **"aucune
invocation mesuree"** pour les neuf agents. Le script deduit le dossier des transcriptions du chemin
du depot ; un worktree a un autre chemin, donc un autre dossier, et **ce dossier-la est vide**.

**Le mode de defaillance est celui que ce depot sanctionne partout** : la commande **reussit**, le
rapport **s'ecrit**, et il dit "aucune invocation mesuree" - une phrase qui est la bonne formulation
du script et **une description fausse du sprint**. Ce n'est pas un zero invente, c'est pire : c'est
un rapport d'apparence normale.

**Contourne a la main** pour cette review, par `--projects-dir` pointe sur le dossier du depot
principal - une option que le script offre et que rien n'oblige a utiliser. **Le rapport commite est
le bon** : 1410 tours, six agents plus le fil principal. **Mais la prochaine review le refera, ou ne
le refera pas.**

### 2.5 Le sprint livre une mecanique qu'aucun enfant ne peut ouvrir

La saisie existe de `domain` a l'ecran, son paquet est dans `contenu/`, **rien ne les joint**. La
cause immediate est un fait mesure et bien traite (l'entree de catalogue depend des lots 2 et 4, ce
que le planning n'avait pas vu). La cause de fond est ailleurs : **`DEC-0059` section 5.3 refusait
d'avance de cloturer sur une mecanique non jouable**, en citant quatre sprints deja sanctionnes pour
cela, et le sprint y arrive **par un autre chemin que celui contre lequel le garde-fou avait ete
pose** - pas par une bascule declaree, mais par une dependance decouverte au dernier lot.

**Un refus ecrit contre un chemin ne protege pas des autres chemins qui menent au meme etat.**

---

## 3. Amendements a `squad/README.md`

### 3.1 Ecrit dans le geste de cette review - section 7, la borne de la porte pedagogique

Route par `camille-po`, transcrit sans interpretation depuis `DEC-0060` et la clause opposable de
`nadia-prof`. Voir `review.md`, section 6. **Il est sur le disque.**

### 3.2 Propose ici - section 9, le tri des garde-fous est rouvert par tout garde-fou ne pendant le sprint

**Motif** : section 2.2. Le lot 5a ferme une liste a un instant ; rien ne rattache a cette liste ce
qui nait apres elle.

> **Quand un sprint etablit un tri de garde-fous - la liste de ceux qu'un increment fait mordre, avec
> pour chacun une decision motivee - ce tri est rouvert par tout garde-fou de la meme famille ecrit
> pendant le sprint, quel qu'en soit l'auteur et quel que soit le lot.** L'auteur du garde-fou neuf
> le fait passer par le tri dans le meme commit, ou le Scrum Master le fait avant de fusionner.
> **Un tri ferme a un instant n'est pas un tri : c'est un instantane.**

**Ma ligne de la table de routage** (capacite et sequencement). Je le propose ; s'il touchait la forme
d'une regle executable, il serait a `theo-archi`.

### 3.3 Propose ici - section 9, la branche poussee rouge a une forme

**Motif** : review, section 3.1. La regle *"tu ne pousses que si `mvn verify` est vert en local"* n'a
pas de cas prevu pour un travail complet bloque par un arbitrage qui n'est pas de son auteur. La
garder telle quelle obligerait a garder le travail hors du depot, ce que ce depot a deja paye deux
fois.

> **Une branche de travail peut etre poussee rouge quand son blocage appartient a un autre role, a
> quatre conditions cumulatives :** l'etat rouge est **dans le sujet du commit** ; la mesure et son
> perimetre sont **dans son corps** ; un **document d'obstacle** route nominativement le point a
> trancher ; et **elle n'est jamais fusionnee** tant qu'un commit ulterieur n'a pas ecrit qu'elle est
> verte. **L'historique n'est pas reecrit** : un fait qui a eu lieu reste dans le depot.
>
> **Cela ne desserre rien pour une branche de fusion** : `round-two` et les branches de sprint ne
> recoivent que du vert mesure a la racine du reacteur.

**Ma ligne** (conduite d'equipe). `marc-dev` a invente cette forme sans qu'elle existe ; elle est
meilleure que la regle qu'elle plie, et une pratique qui a fait ses preuves une fois s'ecrit.

### 3.4 Propose ici - section 4, `yanis-qa` et le relecteur sont convoques au planning, pas a la review

**Motif** : section 2.3. Les convoquer a la review revient a les appeler quand tout est fusionne et
que le temps manque - ce qui, sur s24, a produit leur absence pure et simple.

> **Le planning nomme, dans son tableau d'affectations, le moment ou `yanis-qa` mesure l'arbre
> fusionne et celui ou le `relecteur-pedagogique` relit les livrables non executables.** Les deux sont
> des items du sprint avec leur place dans l'ordre, pas des contributions attendues a la ceremonie de
> cloture. **Leur absence a la review devient alors un fait de planning et non une omission
> d'animation.**

**Ma ligne** (capacite et affectation).

### 3.5 Route, non ecrit - l'outillage du rapport de tokens

**Motif** : section 2.4. Un outil qui rend un rapport vide d'apparence normale depuis le lieu ou la
ceremonie se conduit.

**Route a `theo-archi`**, avec les obstacles 8 et 9 de la review dont il est le troisieme frere :
**meme famille - un outil qui repond a cote sans le dire.** Ce que je demande est le comportement, pas
la solution : que le script **refuse** plutot que de rendre un rapport vide quand le dossier de
transcriptions qu'il a deduit n'existe pas ou ne contient rien, ou qu'il resolve le depot principal
depuis un worktree. **Je ne choisis pas entre les deux : c'est technique et c'est a lui.**

---

## 4. Ce que la retro ne fait pas

- Elle **ne prononce aucun Done**, ni technique ni pedagogique.
- Elle **ne clot aucun obstacle** : les dix-neuf du tableau de `review.md` restent ouverts tant que
  le fichier qui les tranche n'existe pas sur le disque.
- Elle **ne reecrit aucun document de ceremonie anterieur** et ne fabrique aucune ceremonie qui n'a
  pas eu lieu. Le daily de s24 n'a pas de fichier et n'en aura pas ; ses obstacles sont dans
  `review.md`.
- Elle **n'ecrit pas les amendements 3.2 a 3.4 dans `squad/README.md`** : ils y entrent au planning de
  s25, avec le reste de la conduite du sprint suivant, ou ils sont amendes si l'utilisatrice ou un
  agent les conteste d'ici la. Seul le 3.1 est ecrit, parce qu'il transcrit une decision deja prise
  et qu'il m'etait explicitement route.
