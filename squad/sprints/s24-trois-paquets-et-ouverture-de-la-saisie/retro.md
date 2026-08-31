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

---

## 5. Amendement du 2026-08-31 - ce que la cloture a ajoute a la retrospective

*Bloc ajoute par `sofia-sm` apres les trois correctifs, les quatre `VALIDE` et la relecture. **Rien
des sections 1 a 4 n'est efface** : elles decrivent le sprint tel qu'il etait quand la retro a ete
tenue, porte rouge et saisie non jouable. Ce qui suit vient apres, et les faits les plus lourds du
sprint sont dans ce bloc-ci.*

### 5.1 Le fait qui domine le sprint - quatre proprietes promises et non tenues par leur code, en une journee

Ce n'est pas quatre accidents, c'est **un motif**, et il est de la meme famille que la dette 7 de
`CLAUDE.md` - *"un garde-fou annonce mais absent"* - sauf que le garde-fou existe et qu'il ment sur ce
qu'il garde.

| # | Ce qui etait promis | Ce que le code faisait | Trouve par |
|---|---|---|---|
| 1 | La traversee de saisie derive du **repertoire**, ecrit mot pour mot dans sa Javadoc | Elle lisait `games()` - le catalogue. L'entree de catalogue etant retenue, **elle serait restee sur une branche vide** pendant que le fil-piege voisin passait au vert | le fil-piege de `marc-dev` |
| 2 | Les onze items d'un paquet sont juges | Tirage a hasard constant zero : la mecanique rendait **toujours le premier item**, et les dix autres sortaient par un `return true` silencieux, **compteur en hausse**. *Compter une population n'est pas la juger* | `sofia-sm` (dev) |
| 3 | Les formes acceptees d'un item servi sont le bareme reel | Les formes attendues etaient lues **dans l'item charge des deux cotes de l'assertion**. Une fabrique qui perdait les formes les perdait **des deux cotes**, et le garde-fou restait vert **sur le defaut meme que sa premiere phrase annonce** | `sofia-sm` (dev), reproduit par mutation |
| 4 | **`CPL-3`** - l'ecriture a zero initial est acceptee. Condition **ecrite, datee, precise**, du 2026-08-30 | **Rien ne l'executait.** Un enfant de CP qui ecrit `03` pour `3` s'entend dire qu'il s'est trompe et perd un essai | **`nadia-prof`, en jouant** |

**Ce que les quatre ont en commun** : un enonce qui affirme plus large que ce que son code verifie, et
**une CI verte qui le certifie**. Les trois premiers sont des garde-fous ; le quatrieme est une
condition pedagogique que personne n'avait traduite. **La forme est identique : le texte promet, le
vert confirme, et personne ne compare les deux.**

**Ce que le sprint a mis en face, et qui a marche** : le fil-piege de `marc-dev` a saute et a montre
que **mettre son compte a jour n'aurait rien corrige** - ses deux clauses ne derivaient pas leur
perimetre de la meme source. Un fil-piege qui, en sautant, montre que la reparation evidente est la
mauvaise, vaut plus que le defaut qu'il signale.

**Ce que je propose d'en tirer, en section 6.**

### 5.2 Deux defauts d'integration sous la meme forme - et la phrase de `theo-archi` vaut pour quatre

**Vert sur une branche, vert sur l'autre, rouge une fois ensemble.** Deux fois dans la meme journee :

- le garde-fou d'`ADR-0072` contre le paquet de saisie du lot 5b (`review.md`, section 1.2) ;
- le fil-piege du lot 4 contre l'entree de catalogue retenue (section 5.1, ligne 1).

`theo-archi` l'ecrit **contre lui-meme**, et c'est la formulation la plus utile du sprint :

> *"J'ai decrit mon clone en croyant decrire le depot."*

Il l'ecrit d'abord de son recensement de branches - il avait mesure *"aucune branche connue de mon
clone"* et ecrit *"aucune branche poussee du depot"* - puis il en tire la regle generale : *"deux verts
sur deux branches ne font pas un vert, et le seul remede est de mesurer sur l'arbre fusionne."*

**Cette phrase vaut pour quatre, pas pour lui** :

- pour **`theo-archi`**, sur son recensement et sur son garde-fou ;
- pour **`marc-dev`** et pour **`sofia-sm` (dev)**, dont les 825 tests annonces etaient le meme nombre
  sur deux arbres differents ;
- pour **moi**, qui reponds de ce que je fusionne et qui n'ai mesure l'arbre fusionne qu'a la porte -
  c'est-a-dire au dernier moment ou le defaut coute le plus cher a corriger ;
- et pour **le pilotage du sprint**, qui a donne a `nadia-prof` un arbre de reference ne portant qu'un
  des trois correctifs. **Elle a construit le sien et l'a identifie par quatre empreintes**, ce qui est
  exactement le remede applique par la personne qui n'avait pas cause le probleme.

**Ce que la cloture doit a `nadia-prof` sur ce point** : sans ses quatre empreintes, l'integration de
cloture aurait porte un arbre dont **personne n'aurait pu prouver** qu'il etait celui des verdicts. Le
compte de tests ne l'aurait pas dit - le sien coincide avec celui d'un arbre a un seul correctif, les
deux autres ne touchant que du YAML. **Un compte de tests ne distingue pas deux arbres ; une empreinte
de fichier, oui.**

### 5.3 `TYP-1` ne voit que la moitie du texte que l'enfant lit

`relecteur-pedagogique` mesure : le cliquet d'`ADR-0067` derive son perimetre du **texte servi par les
paquets**, et les quatre paquets neufs y entrent a zero - c'est propre. **Mais l'enfant lit aussi ce
que les gabarits ecrivent**, et rien ne le regarde : **onze occurrences, dont trois neuves a s24**,
dans `fragments/saisie.html`, entrees sous une porte technique verte.

**Pourquoi c'est un fait de retro et pas une liste de onze corrections** : `ADR-0067` dit que le
cliquet existe *"pour arreter la croissance"*, apres *"quatre orphelins neufs apparus a s22"*. Trois
orphelins neufs viennent d'apparaitre **dans le perimetre exact ou le cliquet ne peut pas les voir**.
**Un cliquet qui ne couvre qu'une des deux sources du texte rendu arrete la croissance a un endroit et
l'autorise a l'autre** - et il donne, entre-temps, l'impression que la croissance est arretee.

Ce n'est pas un reproche a l'auteur du lot 4 : `fragments/saisie.html` reprend `'Score : '` de
`fragments/manche.html` a l'identique, ce qui est la bonne facon d'ecrire un second fragment.
**C'est le perimetre du cliquet qui est en cause, pas la main qui a copie** - meme structure exacte
que la section 5.1.

Le relecteur **refuse de corriger** les onze chaines, et il a raison : `ADR-0067` laisse ouvert le
choix entre l'insecable ordinaire, l'insecable etroite et le retrait de l'espace, et poser un
caractere dans onze chaines lues par un enfant **trancherait ce choix par un commit de relecture**,
sans que `nadia-prof` l'ait vu. **Obstacle 22.**

### 5.4 923 tests verts, et un enfant de CP qui ecrit `03` a tort

C'est `nadia-prof` qui l'ecrit, et c'est la phrase que ce sprint doit garder :

> *"`SAI-6` et `SAI-7` etaient nommees le 2026-08-30 comme le point le plus dangereux de la mecanique
> de saisie : un bareme faux passe une CI verte sans laisser de trace. **C'est arrive, et le seul
> garde-fou qui l'a vu, c'est d'avoir joue.**"*

**Ce que cela dit du Done technique, et il faut le dire exactement** : la CI n'a pas failli. Elle a
juge ce qu'on lui avait demande de juger, et personne ne lui avait demande `CPL-3`. **Le Done
technique n'est pas une approximation du Done pedagogique** - c'est un autre jugement, sur un autre
objet, et le sprint vient de montrer ce que coute de les confondre : un paquet a **onze items sur
onze** en defaut, sous 923 tests verts.

**Ce que cela dit de la porte pedagogique** : elle a paye son cout et elle l'a justifie dans le meme
geste. Sans le fait de jouer, `S24-SAI-2` serait entre au catalogue, aurait ete compte comme couvert,
et **le premier enfant a l'ouvrir aurait perdu un essai pour avoir eu raison**.

**Et le fait que `nadia-prof` verse pour la retro, qu'aucun compteur de couverture ne verra** :

> *"Le catalogue contient desormais **un jeu sur trente et un ou un enfant qui ne sait pas peut
> echouer**."*

C'est ce que la mecanique de saisie apporte reellement. **C'est la phrase qui a remplace le chiffre
faux de 33 lignes sur 43**, et elle est mesurable, datee, et de sa main.

### 5.5 Le paquet narratif reste a 50,0 %, et `nadia-prof` refuse la reparation evidente

`lea-dev` remonte que le maximum de strategie sur `cp-texte-narratif` **reste a 50,0 %**, porte par la
seconde phrase, apres la correction qui avait ramene la premiere phrase sous le seuil.

**`nadia-prof` refuse de le faire baisser en ajoutant des items** :

> *"Ajouter des items ne retire aucun raccourci, cela dilue un denominateur."*

**C'est le refus le plus instructif du sprint**, parce que la reparation refusee aurait rendu le
chiffre vert sans rien changer pour l'enfant - exactement la famille de defaut que `marc-dev` avait
refusee sur `FRA` (*"rendre un garde-fou aveugle par la redaction"*) et que `theo-archi` avait refusee
sur `ADR-0072` (une exemption qui n'ecarte personne). **Trois refus de la meme forme, par trois
agents, dans le meme sprint.** C'est ce que la squad a fait de mieux ce sprint-ci.

**A verser au backlog, pas a s24.** Obstacle 23, a `camille-po`.

### 5.6 Un ecart de conduite de ceremonie qui n'est pas de ma main

La section 2.3 ecrit *"c'est un manque de conduite de ceremonie et il est de moi : je ne les ai pas
appeles"*. **Cette attribution est fausse sur un point, et je la corrige ici sans reecrire la
section** :

> *"`yanis-qa` et `relecteur-pedagogique` ont ete lances apres ta review au lieu d'avant"*
> - **recu de MASTER, le 2026-08-31, sans source dans le depot.**

**Le moment de l'invocation d'un agent ne m'appartient pas.** Ce qui reste de moi et que je ne deplace
pas : **ma review a ete rendue sans les avoir demandes**, et l'amendement 3.4 - les convoquer au
planning et non a la review - vaut exactement contre cela. C'est aussi la raison pour laquelle je le
maintiens : un amendement qui place la demande **au planning** la rend inratable, quel que soit
l'ordre dans lequel les agents sont lances.

**`yanis-qa` n'a pas rendu du tout** : sa branche n'est pas poussee a la cloture. Second sprint
consecutif sans rapport qualite. **Le defaut de la section 5.1 est exactement ce qu'un passage qualite
sur l'arbre fusionne aurait trouve** - et c'est le troisieme argument, apres les deux de la section
2.3, en faveur de l'amendement 3.4.

---

## 6. Amendement supplementaire propose - section 9, la promesse d'un garde-fou est mesuree, pas relue

**Motif** : section 5.1, quatre occurrences en une journee ; section 5.3, une cinquieme d'une autre
forme. **Un garde-fou vert ne prouve pas que ce qu'il promet est vrai** - il prouve que ce qu'il
regarde l'est, et ces deux ensembles ont diverge cinq fois dans ce sprint.

> **Un garde-fou dont la Javadoc ou le message d'erreur promet un perimetre porte, dans le meme
> fichier, une clause qui mesure ce perimetre** - le nombre d'elements reellement juges, et non le
> nombre parcouru. **Une population comptee n'est pas une population jugee**, et une clause de
> non-vacuite qui ne compte que les elements traverses ne mord pas sur un `return true` silencieux.
>
> **Quand un garde-fou est ecrit ou corrige, sa morsure est etablie par mutation** - on casse ce qu'il
> promet de voir et on verifie qu'il rougit **en nommant ce qui a change**. Une morsure non mesuree
> est une promesse, et ce sprint en a paye cinq.

**Ma ligne** (conduite d'equipe et qualite de ce que la squad produit). **Si sa forme executable devait
etre tranchee** - une regle ArchUnit, un patron impose - **elle serait a `theo-archi`, et je ne
l'ecris pas a sa place.** Ce que je propose ici est la discipline, pas le mecanisme.

**Ce qui existe deja et qui va dans ce sens, plutot que d'inventer** : `ADR-0013` obligation 3 impose
un jeu fautif a tout garde-fou qui balaie un repertoire, et le lot 5b a etabli le patron du domaine
declare (`ClassementDesItems`). **L'amendement ne cree rien : il generalise deux pratiques que ce
sprint a validees separement.**
