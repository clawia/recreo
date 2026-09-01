# [Sprint s25] Rapport qualite - `yanis-qa`

- **Date** : 2026-09-01. **Auteur** : `yanis-qa`.
- **Arbre mesure** : worktree dedie, branche `s25-yanis`, branchee sur `s25-sofia-review`, tete
  `834c6bc` - l'arbre de cloture. Lignee verifiee avant toute mesure
  (`git merge-base --is-ancestor`, et les deux revisions sont la meme).
  **Aucune mesure de ce rapport ne vient d'une autre branche.**
- **Rang dans la cloture** : apres `nadia-prof` et apres le `relecteur-pedagogique`, comme
  `sofia-sm` l'a fixe. C'est la correction d'un ecart de s24 qui etait de moi, et elle a servi : la
  campagne de mutation de ce rapport porte sur l'arbre que la cloture presente, pas sur un arbre
  intermediaire.

> **Ce rapport ne prononce que le Done technique, et il ne le prononce pas non plus : la CI le
> juge, je le mesure et je le rapporte.** Le Done pedagogique appartient a `nadia-prof` seule. Rien
> de ce qui suit ne s'en approche, y compris les sections qui parlent de `CON-6` : j'y mesure ce
> qu'un garde-fou executerait, jamais ce qu'un contenu vaut.

---

## 1. La porte technique, mesuree par moi

### 1.1 La commande et son verdict

```
bash outils/mvn-jdk21.sh -B clean verify
```

a la racine du reacteur, **sans aucune modification de l'enveloppe ni du poste**. Reacteur complet,
aucun `-pl`, donc aucun `-am` a citer et aucune reserve de perimetre a poser.

> **BUILD SUCCESS.**

| Module | Tests | Echecs | Erreurs | Ignores |
|---|---:|---:|---:|---:|
| `recreo-domain` | 80 | 0 | 0 | 0 |
| `recreo-application` | 13 | 0 | 0 | 0 |
| `recreo-adapter-web` | 364 | 0 | 0 | 0 |
| `recreo-adapter-content` | 410 | 0 | 0 | 0 |
| `recreo-bootstrap` (surefire) | 145 | 0 | 0 | 0 |
| `recreo-bootstrap` (failsafe) | 6 | 0 | 0 | 0 |
| **Total** | **1 018** | **0** | **0** | **0** |

**Methode du comptage** : somme des attributs `tests`, `failures`, `errors` et `skipped` de
l'element `<testsuite>` de chaque `TEST-*.xml` de `target/surefire-reports/` et de
`target/failsafe-reports/`, sur les rapports produits par ce build apres un `clean`. **140
rapports** - 136 surefire, 4 failsafe. **Perimetre : le depot entier**, les cinq modules du pom
agregateur, sans exclusion.

**Mesure trois fois, sur le meme commit, dans le meme worktree, avec la meme commande** : memes
1 018 tests, memes 140 rapports, meme verdict. La deuxieme suit la campagne de mutation et verifie
du meme geste que l'arbre est revenu intact ; la troisieme porte l'arbre avec ce rapport dedans,
c'est-a-dire l'etat exact que je commite.

### 1.2 Sur quelle version j'ai mesure

L'enveloppe du depot a **accepte**, et elle l'ecrit elle-meme avant de lancer Maven : *"Maven lance
sur le JDK de la toolchain jdk 21 : 21.0.10"*, precede de sa propre reserve - *"verdict rendu sur le
fichier de toolchains que Maven lit, qui n'est pas celui de ce shell"*. **La machine virtuelle qui
execute les tests rapporte `21.0.10`** : c'est ce que Spring Boot inscrit dans son journal a chaque
demarrage de contexte, sur les trois executions, sans une seule exception.

**Je confirme donc le fait que `sofia-sm` a corrige dans le commit de cloture** : la commande du
depot rend un chiffre, et je n'ai eu besoin d'aucun fichier de toolchains hors depot pour l'obtenir.
Le refus de la veille etait bien un faux rouge.

### 1.3 Le delta que j'annonce, et il est nul

**Commit de base : `834c6bc`. Delta de tests apporte par ma branche : 0.** Mon commit n'ajoute aucun
test et n'en retire aucun ; il ajoute ce fichier. Les 1 018 ne sont pas un progres que j'annonce,
c'est une **reproduction** du chiffre que `sofia-sm` a etabli sur cet arbre et que le
`relecteur-pedagogique` a reproduit avant moi. **Trois mesures independantes, meme commit, meme
chiffre.**

Je ne compare pas 1 018 aux 1 001 de la section 3.2 de la review : ce sont deux arbres differents,
et je n'ai pas mesure le second.

### 1.4 Les autres criteres de sortie du Done technique

| Critere | Etat mesure par moi |
|---|---|
| `mvn -B clean verify` vert a la racine | **Tenu** - section 1.1, trois passages |
| Aucun module sans test | **Tenu** - les cinq modules du pom agregateur produisent des rapports, du plus petit (`application`, 13) au plus gros (`adapter-content`, 410) |
| Aucun test ignore ou desactive | **Tenu** - `skipped` vaut 0 sur les 140 rapports, sur les trois executions |
| Garde-fou de chemins de poste | **Tenu** - la commande exacte du workflow, avec son exclusion du fichier de cas de test, code de retour 0 : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies"*. Le juge du garde-fou est vert lui aussi |
| Tests instables | **Aucun.** Section 8, avec sa methode |

---

## 2. Tracabilite - chaque condition de s25 et le test qui la nomme

La tracabilite de ce depot est portee par les identifiants de condition cites dans la Javadoc et
dans les clauses `as(...)`, pas par les noms de classe. **Methode** : recherche litterale de chaque
identifiant dans les fichiers `*Test.java` des cinq arbres de modules, copies sous `target/`
exclues. **46 identifiants balayes** - `FIG-1` a `FIG-4`, `CON-1` a `CON-6`, `MON-1` a `MON-6`,
`SEM-1` a `SEM-9`, `SUB-1` a `SUB-12`, `DES-1` a `DES-7`, `DV-1`, `TYP-1`.

> **43 sur 46 sont cites par au moins un fichier de test. Trois ne le sont par aucun.**

| Identifiant | Ce que j'en dis, et ce que je n'en dis pas |
|---|---|
| **`MON-6`** | **Ce n'est pas un trou de couverture aujourd'hui** : `nadia-prof` l'ecrit non retroactive et note qu'elle *"vaut pour les paquets de composition suivants"*. Aucun paquet servi n'est sous elle. **Ce qui est vrai est que rien ne l'executera le jour ou le paquet suivant arrivera**, et c'est la forme de `CPL-3` prise a l'avance |
| **`SUB-8`** | **Sa moitie calculable est encodee ailleurs depuis s24, et pas ici.** `PaquetCpTexteNarratifTest` encode l'equilibre en genre des prenoms distincts pour `NAR-7` au moyen d'une table declaree par l'auteur, en disant explicitement ce que la table prouve et ce qu'elle ne prouve pas. `SUB-8` porte la meme moitie calculable, sur la meme famille, dans le meme module, et **aucun test ne la nomme**. `nadia-prof` la mesure TENUE a la main - 10 prenoms feminins distincts, 9 masculins. **Le precedent existe, l'ecart est un oubli d'encodage, et il appartient a son auteur** |
| **`DES-6`** | **Annoncee hors de portee, pour une chaine qui existe desormais.** La Javadoc du garde-fou de geometrie ecrit que `DES-6` est *"sans objet, aucun chiffre trace"* sur les figures planes et qu'elle *"mordra sur la chaine de la monnaie"*. Cette chaine est servie depuis ce sprint, avec des chiffres traces, et `DES-6` n'est executee par rien. **Le fichier dit lui-meme pourquoi** - aucune propriete du fichier ne distingue une commande de trace qui fait un glyphe - **et c'est exactement pourquoi je la compte ici plutot que de la laisser dans une phrase de Javadoc** |

**Les 43 autres sont cites.** Je ne dis pas qu'elles sont **couvertes** : citer n'est pas prouver, et
plusieurs des conditions citees le sont dans une Javadoc qui explique precisement ce qu'un automate
ne saura pas en dire - `CON-1`, `CON-4`, `CON-5`, `MON-5`, la moitie non calculable de `FIG-3`.
**Ce tableau repond a "peut-on retrouver la condition depuis le code ?", pas a "la condition
est-elle prouvee ?".**

---

## 3. `CON-6` - ce qui est encode, ce qui ne l'est pas, et ce que j'ai mesure

`nadia-prof` insiste sur un point et `sofia-sm` en fait la troisieme occurrence de `CPL-3` du
sprint : *"un garde-fou qui n'encoderait qu'une de ces clauses serait vert et vide sur
`cp-monnaie`"*. **J'ai mesure au lieu de relayer. Le fait principal est exact ; le mecanisme annonce
ne l'est pas.**

### 3.1 Methode

Sonde jetable, **non commitee**, ecrite dans le module `adapter-content` et lancee par
`-pl adapters/adapter-content -am` - **le `-am` est present, et ces sondes sont le seul endroit de
ce rapport ou une mesure est partielle**. Elle charge les paquets **par la fabrique reelle**,
epuise chaque source d'items comme les tests de paquet le font, et compte sur les items **servis**,
jamais sur les lignes du fichier YAML. Perimetre : les deux paquets illustres servis - **`CON-6` ne
porte que sur eux** - plus l'etat de `cp-figures-planes` **d'avant la redistribution des indices**,
rejoue en memoire par la meme reconstruction que le contre-exemple gele de
`PaquetCpFiguresPlanesTest` emploie.

### 3.2 Ce que rendent les quatre clauses de la seconde revision

| | `cp-figures-planes` **servi** | `cp-monnaie` **servi** | `cp-figures-planes` **avant la reecriture** |
|---|---|---|---|
| Items | 12 | 12 | 12 |
| Jeux de propositions distincts | **4** | **11** | 4 |
| Jeux partages par plus d'un item | **4** | **1** | 4 |
| **(a)** indice n'allant qu'avec une reponse | 0 - **verte** | 0 - **verte** | **2 - rouge** |
| **(b)**, moitie *"l'indice est fonction du jeu"* | 0 faute sur 4 jeux - **verte** | 0 faute sur 1 jeu - **verte** | **4 fautes sur 4 - rouge** |
| **(b)**, moitie *"le jeu est servi par toutes ses reponses"* | 0 faute sur 4 jeux - **verte** | **1 faute sur 1 jeu - rouge** | 0 faute - verte |
| **(b')** telle qu'elle est ecrite | 0 faute sur 4 reponses - **verte** | **10 fautes sur 11 reponses - rouge** | **4 fautes sur 4 - rouge** |
| **(b')** restreinte aux reponses servies au moins deux fois | domaine 4, 0 faute - **verte** | **domaine 1, 0 faute - verte** | domaine 4, **4 fautes - rouge** |
| **(c)** indice le plus repandu | 6 / 12 - **verte**, marge nulle | 5 / 12 - **verte** | 6 / 12 - verte |

### 3.3 Les trois faits que cette mesure etablit

**1. Les deux encodages livres portent bien `(a)` et `(c)`, et ils mordent sur le materiau reel.**
Ce n'est pas une lecture. Mutation `M8` - un indice de `cp-figures-planes` remplace par un texte qui
ne va qu'avec une reponse - rend `PaquetCpFiguresPlanesTest` rouge sur le message de `(a)`.
Mutation `M9` - l'indice minoritaire de `cp-monnaie` remplace par le majoritaire, qui passe ainsi a
7 items sur 12 - rend `PaquetCpMonnaieTest` rouge sur le message de `(c)`. **Les deux bornes de la
premiere version sont vivantes.**

**2. `(b)` et `(b')` ne sont executees par rien.** Recherche litterale sur les cinq arbres de
modules : aucune assertion ne porte l'une ou l'autre. **Le fait est exact, et il est bien de la
famille de `CPL-3`.**

**3. Mais `(b)` n'est pas vide sur `cp-monnaie` : elle y est rouge - et `(b')` aussi.** C'est le
point ou ma mesure s'ecarte de ce que la fiche et la review annoncent toutes les deux.

- La fiche ecrit que *"(b) et (b') sont vides sur un paquet dont les items ont tous des jeux de
  propositions distincts"* et nomme `cp-monnaie` comme un tel paquet. **Ses items n'ont pas tous des
  jeux distincts** : 11 jeux pour 12 items, donc **un jeu est partage par deux items**, et `(b)` a
  quelque chose a examiner. Sa moitie *"l'indice est fonction du jeu"* y est verte ; sa moitie *"ce
  jeu est servi par toutes ses reponses"* y est **rouge** - le jeu `{3 euros, 4 euros, 5 euros}` est
  servi par `3 euros` et par `5 euros`, jamais par `4 euros`.
- **La fiche portait deja ce fait dans son propre tableau de la section 14.1**, sous le libelle
  *"chaque jeu de propositions servi par ses trois reponses : non"* pour la monnaie. C'est la refonte
  des libelles entre 14.1 et 14.4 - cette moitie-la rentrant dans `(b)` - qui a fait disparaitre un
  *"non"* mesure derriere un *"vide"*.
- `(b')` telle qu'elle est ecrite - *chaque reponse est accompagnee d'au moins deux indices
  differents* - est **insatisfaisable** sur `cp-monnaie`, et pas seulement fausse : dix de ses onze
  reponses ne sont servies **qu'une seule fois**, et une reponse servie une fois ne peut porter
  qu'un seul indice. Aucune reecriture d'indices ne la rendrait verte ; il faudrait changer les
  reponses.

**Le risque du "vert et vide" est reel, mais il porte sur un paquet a venir, pas sur celui-ci.** Un
paquet dont les douze items auraient douze jeux distincts ferait examiner **zero** jeu a `(b)`, et
la clause serait alors muette sans rien dire. `cp-monnaie` en est a un jeu partage sur onze :
**silencieux aujourd'hui, vide demain.**

### 3.4 Ce qui doit etre encode, et ce qui ne peut pas l'etre

Je reponds a la question posee, et je m'arrete a la frontiere.

| Clause | Encodable ? | Ce que la mesure dit |
|---|---|---|
| **(a)** | **Deja encodee, deux fois** | Verte sur les deux paquets servis, rouge sur l'etat qui a motive la condition, mordue par `M8` |
| **(b)**, moitie *"fonction du jeu"* | **Oui, et elle mord fort** | 4 fautes sur 4 jeux sur l'etat d'avant la reecriture, quand `(a)` n'en releve que 2. C'est la borne principale, et rien ne s'oppose a son encodage |
| **(b)**, moitie *"servi par toutes ses reponses"* | **Pas en l'etat** | Elle est **rouge sur un paquet deja valide**, et `CON-6` est ecrite non retroactive. L'encoder telle quelle rendrait la porte technique rouge sur un increment que la porte pedagogique a passe. **Ce n'est pas a moi de dire si c'est le paquet ou la clause qui doit bouger** |
| **(b')** | **Non telle qu'elle est ecrite ; oui restreinte** | Insatisfaisable sur `cp-monnaie` - 10 reponses sur 11. Restreinte aux reponses servies au moins deux fois - ce que `nadia-prof` reserve elle-meme par *"la ou il est satisfaisable"*, sans le definir - elle est **verte sur les deux paquets servis, non vide sur les deux (domaine 1 et 4), et rouge sur les quatre reponses de l'etat fautif**. Definir ce domaine est un choix, donc pas le mien |
| **(c)** | **Deja encodee, deux fois** | Verte sur les deux, mordue par `M9`. Marge nulle sur `cp-figures-planes`, 6 sur 12, deja remontee par `lea-dev` |
| **(d)** | **Non, et son autrice le dit** | Rien a encoder, et rien a compenser par un chiffre |

**Une exigence de forme, qui vaut quelle que soit la clause retenue** : tout encodage de `(b)` ou de
`(b')` doit **declarer la taille du domaine qu'il examine et echouer si elle est nulle**, comme
`ADR-0051` l'impose deja ailleurs et comme `marc-dev` le fait dans son juge de rang. Sans cette
clause, `(b)` deviendra verte et vide sans que rien ne le signale, le jour du premier paquet a jeux
tous distincts. **C'est la seule chose que je demande ; le reste est un jugement de `nadia-prof` et
un perimetre de `theo-archi`.**

---

## 4. Le garde-fou "un rang suffit" - sa mesure verifiee, pas reprise

`marc-dev` a grave domaine, seuil et chiffres dans la Javadoc de son test plutot que dans un rapport,
et refuse d'ecrire le garde-fou parce que *"c'est un `ADR`, donc pas mon lot"*. **Je devais verifier
sa mesure. Je l'ai refaite avec son instrument, sur le materiau reel.**

### 4.1 Methode

Sonde jetable, non commitee, meme forme qu'en section 3.1. Elle reimplemente **la strategie de son
juge** - trier les propositions d'un item par la position de leur premiere occurrence dans le
`texteSupport` replie en minuscules, puis regarder le rang de la bonne reponse - et l'applique aux
items **servis**. La population des paquets est **derivee de `catalogue.yaml`**, jamais d'une liste
ecrite a la main. Un item dont une proposition n'apparait pas dans son support est hors du domaine,
comme chez lui. Pour l'etat refuse, le fichier de `cm1-substituts` **d'avant le correctif** est
restaure dans l'arbre servi, mesure, puis retire : la mesure porte sur le paquet reellement refuse,
et non sur une reconstruction de ce que j'imagine qu'il etait.

### 4.2 Ce que je trouve

| Etat mesure | Items | Domaine juge | Premiere | Milieu | Derniere |
|---|---:|---:|---:|---:|---:|
| `cm1-substituts` servi | 20 | 20 | **7 (35,0 %)** | 6 (30,0 %) | 7 (35,0 %) |
| `cp-texte-narratif` servi | 20 | 20 | **9 (45,0 %)** | 4 (20,0 %) | 7 (35,0 %) |
| `cm1-substituts` **refuse** | 20 | 20 | **16 (80,0 %)** | 3 (15,0 %) | 1 (5,0 %) |

**Deux de ses trois chiffres se reproduisent exactement. Le troisieme non, et je dis pourquoi.**

**35,0 % et 45,0 % sont exacts au dixieme.** Sa conclusion porteuse l'est aussi : au seuil existant -
aucune position au-dessus de la moitie - le garde-fou serait **vert sur les deux paquets servis et
rouge sur l'etat refuse**, donc *"il aurait attrape ce defaut avant les 210 manches"*. **C'est vrai,
et c'est le fait qui compte.**

**95,0 % n'est pas le chiffre de sa strategie, c'est celui de `nadia-prof`.** Les 19 items sur 20 de
la fiche sont mesures par *"le premier prenom du texte"*, un instrument **lexical**. La strategie
gravee dans sa Javadoc est *"la premiere proposition qui apparait dans le texte"*, un instrument
**positionnel**. **Sur l'etat refuse, l'instrument positionnel rend 16 sur 20, soit 80,0 %.** Sa
Javadoc est correcte quand elle dit que sa strategie est *"la meme famille, prise par un autre
bout"* ; c'est la note de perimetre de la review - *"la meme mesure, sur trois paquets"* - qui reunit
deux instruments sous un seul nom. **80,0 % reste tres au-dessus du seuil : la conclusion ne bouge
pas, seul le chiffre bouge.** Je le corrige plutot que de le recopier, parce qu'un chiffre qui
circule sous le nom d'un autre instrument est ce que ce depot a deja sanctionne plusieurs fois.

### 4.3 Ce que sa mesure ne dit pas, et que je mesure - le domaine derive se vide

Il etablit que **le domaine doit etre derive et jamais universel**. **C'est exact, et il y a une
seconde moitie.** Mesure sur le catalogue entier - 35 entrees, 35 paquets distincts, dont **34
traverses par ma sonde**, le trente-cinquieme (`cp-complements-10-saisie`) ne servant pas d'items de
type question et sortant du domaine de la propriete par construction :

- **31 paquets ne portent aucun `texteSupport`** : domaine vide par construction, la propriete n'y a
  pas de sens ;
- **3 paquets en portent sur tous leurs items** : `cm1-substituts`, `cp-texte-narratif` et
  `cm1-comprehension` ;
- sur ces trois, le domaine est plein - 20 items sur 20 - pour les deux premiers, et **vide, 0 item
  sur 20, pour `cm1-comprehension`**, dont les propositions ne sont pas des extraits litteraux de
  leur support.

> **Un garde-fou de depot derive de cette facon serait vert sur `cm1-comprehension` sans avoir
> examine un seul item.** C'est la forme de `CPL-3`, une quatrieme fois, et elle serait creee par le
> garde-fou meme qui existe pour fermer la troisieme.

**Le remede est deja ecrit dans le test de `marc-dev`** : il assertionne que son domaine vaut
exactement le nombre d'items attendu avant de juger quoi que ce soit. **Une generalisation doit
porter cette clause par paquet** - et elle sera alors **rouge sur `cm1-comprehension`**, ce qui est
une decision de perimetre et non un defaut. `theo-archi` pour l'`ADR`, rang a `camille-po`, comme la
review le route deja.

---

## 5. Le trou de s24 est toujours ouvert, et il est plus large que ce que j'avais mesure

### 5.1 Les deux mutations

| Mutation | Ce qu'elle pose dans le fragment de manche | Classes rouges |
|---|---|---|
| **`M1`** | `onclick="this.classList.add('deja-essayee')"` sur le bouton de proposition, celui que l'enfant presse | **0 sur 1 018 tests** |
| **`M7`** | Un element `<script>` de dix lignes qui ecoute le clic sur les boutons de proposition, lit `sessionStorage`, tient un compteur de `score` et compare la valeur choisie a un attribut de donnee | **0 sur 1 018 tests** |

**`M1` est le rejeu exact de la mutation `C7` de mon rapport de s24, sur un arbre qui a ajoute une
branche de gabarit et deux chaines de rendu. Le verdict est le meme.**

**`M7` est neuve, et elle elargit le constat.** Ce que j'avais decrit a s24 comme *"un budget
contournable par un attribut"* est contournable par **un bloc entier**. Le script de `M7` contient
`sessionStorage` et `score`, **deux mots de la liste de vocabulaire interdit d'`ADR-0008`** : la
liste existe, les mots sont presents dans un fichier servi a l'enfant, et rien ne rougit.

### 5.2 Pourquoi, et ce que le critere de sortie humain couvre

`FrontieresDuJavaScriptTest` derive sa population des fichiers `.js` de l'arborescence statique, hors
du repertoire tiers. Son seul balayage de gabarits cherche quatre marqueurs de correction.
**Ni un attribut d'evenement, ni un element `<script>`, ni le vocabulaire interdit ecrit dans un
gabarit ne sont l'un de ces quatre marqueurs.**

`sofia-sm` a pose un critere de sortie humain - *zero attribut inline* - et sa commande est juste :
elle rend bien zero sur cet arbre, je l'ai relancee. **Mais un critere de sortie n'est pas un
garde-fou** : il est lance par une personne a la cloture d'un sprint, il n'est dans aucun workflow,
et **il ne voit pas `M7`** - un element `<script>` ne porte aucun attribut de la forme `on...=`.

**Etat de fait, sans dramatisation** : le depot ne contient aujourd'hui **aucun** attribut
d'evenement inline ni aucun `<script>` maison dans un gabarit. **Rien n'est casse. La voie est libre,
et elle est plus large qu'a s24.** C'est la porte de la **dette n. 4 de `CLAUDE.md`** - *"le
comportement des mecaniques loge dans le JavaScript client : 692 lignes qu'aucun test ne couvrait"*.

**Je ne corrige pas.** `ADR-0008` et ce garde-fou sont a `theo-archi`. Route en section 9, deuxieme
sprint consecutif.

---

## 6. Le motif, apres quatre occurrences de plus

### 6.1 Ce que la campagne de ce sprint rend

**11 mutations du materiau reel, reacteur complet a chaque ligne, aucun `-pl`.** Methode identique a
celle de s24 : arbre remis a `834c6bc`, mutation appliquee, **tous** les `TEST-*.xml` du depot
effaces avant l'execution, `verify` lance avec les deux drapeaux d'ignorance - qui ne relachent rien
et servent seulement a ce que tous les modules s'executent meme quand un module amont est rouge -
verdict lu dans les rapports XML et jamais dans le code de retour, puis arbre restaure. **Un controle
prealable verifie que chaque mutation change reellement son fichier et abandonne sinon** : une
mutation sans effet produit un faux survivant, et c'est la premiere facon dont une campagne peut
mentir.

> **9 mutations sur 11 font rougir la classe visee. 2 survivent, et les deux sont le meme trou** -
> l'aveuglement d'`ADR-0008` aux gabarits.

Le tableau complet est en annexe.

### 6.2 Le verdict de s24 tient-il ?

Ma conclusion de s24 etait double : **ne pas ecrire d'outil de mutation generique** - une mutation
sur cinq etait fausse avant correction, et ses faux survivants couteraient plus cher que les vrais
trous - et **ne pas ajouter de consigne generale**, la forme qui marche etant la question posee a la
revue d'un garde-fou neuf.

**Les quatre occurrences de ce sprint confirment la seconde moitie et ne testent pas la premiere.**

- **Trois des quatre ont ete trouvees par leurs propres auteurs**, et par le meme geste a chaque
  fois : mesurer la clause qu'on vient d'ecrire **sur l'objet qu'elle a ete ecrite pour attraper**.
  `nadia-prof` mesure `CON-6` sur `cp-figures-planes` et trouve 50 pour cent pile. `lea-dev` mesure
  sa borne de taille sur ses douze dessins et trouve qu'elle est a l'envers. `sofia-sm` mesure sa
  propre conformite avant d'ecrire. **Aucun outil n'aurait fait cela** : le geste demande de savoir
  quel objet la clause visait, et cela n'est ecrit nulle part dans le code.
- **La quatrieme - l'enveloppe - a ete trouvee en s'en servant**, et son symetrique, le faux vert, a
  ete trouve par quelqu'un qui a cherche le cas oppose apres avoir corrige le premier. **C'est encore
  la meme forme : chercher le symetrique de ce qu'on vient de reparer.**
- **Sur le taux de fausses mutations, ma campagne de ce sprint ne prouve rien.** Aucune de mes 11
  mutations n'a ete rejetee par le controle prealable ni trouvee hors du perimetre de sa cible.
  **Mais 11 n'est pas 55, et les 11 ont toutes ete ecrites apres lecture de la Javadoc du garde-fou
  vise.** Cela confirme le mecanisme que j'avancais - lire la promesse avant de fabriquer la
  violation - et ne mesure pas le taux d'un outil qui ne la lirait pas. **Je ne convertis pas 0 sur
  11 en une revision du 1 sur 5.**

**Ce que j'ajoute au verdict de s24, et c'est une observation, pas une regle** : les quatre defauts
de ce sprint et les trois du mien partagent une propriete de plus que *"un garde-fou est muet sur la
population qu'il ne voit pas"*. **Ils sont tous nes du meme intervalle : entre le moment ou une
clause est ecrite et celui ou elle est mesuree sur l'objet qui l'a motivee.** `CON-6` premiere
version, `CPL-3`, la borne de taille a l'envers, le verdict de l'enveloppe : dans les quatre cas la
clause etait juste **en intention** et fausse **en extension**, et la mesure sur l'objet motivant l'a
rendu visible en une passe. **Faut-il en faire une obligation ? Ce n'est pas a moi de le decider** -
`ADR-0013` obligation 3 occupe deja ce terrain avec le jeu fautif, et ajouter une obligation est un
geste d'architecture. Je le route en section 9 comme un fait mesure, avec ses quatre occurrences.

---

## 7. L'obstacle 12 - sa cause, mesuree

La review porte l'obstacle 12 comme ouvert et aggrave : *"la somme par classe sous-compte de 7"*, un
ecart *"le meme aux deux bouts"*. **J'ai trouve la cause, elle tombe juste au test pres, et ce n'est
pas un defaut du build.**

Sur mon execution de la section 1.1 :

- le journal porte **144 lignes** de resultat par classe, dont la somme brute vaut **1 018** -
  exactement le total par module ;
- le depot produit **140 rapports XML**, dont la somme vaut **1 018** elle aussi ;
- **quatre noms de classe pleinement qualifies apparaissent deux fois** dans le journal, dans le
  meme module a chaque fois. Une agregation par nom de classe en garde une ligne et jette l'autre :
  **1 018 moins 1, 4, 1 et 1 font 1 011, soit exactement les 7 manquants.**

**Le mecanisme** : ces quatre classes portent **a la fois des regles ArchUnit (`@ArchTest`) et des
tests JUnit (`@Test`)**, donc deux moteurs, donc deux jeux de tests, donc deux lignes de journal. Le
compte se verifie un par un et il tombe juste a chaque fois : `LesReglesMordentTest` 1 et 30,
`AbstractionsCiteesTest` 4 et 1, `AssertionsWebTest` 1 et 1, `ChargementDuContenuTest` 1 et 1. **Le
rapport XML, lui, fusionne les deux moteurs** : celui de `LesReglesMordentTest` porte `tests="31"` et
contient 31 cas.

**Methode du recensement** : recherche de `@ArchTest` sur tous les `.java` des cinq arbres de
modules, `target/` exclu, puis comptage de `@Test` dans chacun des fichiers trouves. **Quatre
fichiers portent les deux annotations, et ce sont les quatre du journal.** La population est close.

**Consequence, et c'est tout ce que j'en dis** : la somme par module et la somme des rapports XML
sont justes et concordent ; la somme par classe n'est juste que si l'on additionne **les deux
lignes** d'une classe a double moteur. **Ce n'est pas un defaut du build, c'est un artefact de la
methode d'agregation.** Je ne recalcule pas les deltas de la review a partir de la : je n'ai pas
mesure ses deux journaux, et un chiffre sans son arbre ne se compare a rien. `theo-archi`, a qui
l'obstacle est route, a desormais la cause.

---

## 8. Tests instables

**Aucun.**

**Methode, parce qu'une absence sans methode ne vaut rien** : **14 executions completes du reacteur**
sur cet arbre - **3 sur l'arbre intact**, aux deux bouts de la campagne puis sur l'etat que je
commite, et 11 avec une mutation chacune. Les trois executions sur l'arbre intact rendent le meme
verdict, les memes 1 018 tests, les memes 140 rapports et les memes 144 lignes de resultat par
classe. Sur les 11 autres, **aucune classe
rouge n'est apparue en dehors de celles que la mutation explique**, et les deux mutations qui visent
le meme garde-fou de geometrie par deux proprietes differentes rendent la meme unique classe rouge.

S'y ajoutent **5 executions partielles** pour les sondes de mesure des sections 3 et 4, toutes avec
`-am`, et dont aucun chiffre n'est presente ici comme un chiffre de depot.

**Je n'ai relance aucune execution pour obtenir un vert.** Aucun test n'est desactive, ignore ni
exclu : `skipped` vaut 0 sur les 140 rapports, sur les trois executions intactes.

---

## 9. Ce que je route, et a qui

Je ne corrige aucun garde-fou qui ne m'appartient pas. **Ce depot a une regle de propriete des
garde-fous, elle a servi deux fois ce sprint, et je m'y tiens.**

| # | Constat mesure | Proprietaire | Severite |
|---|---|---|---|
| 1 | `ADR-0008` et `FrontieresDuJavaScriptTest` ne voient ni un attribut d'evenement ni un element `<script>` dans un gabarit servi. `M1` et `M7` : 0 classe rouge sur 1 018 tests, le script de `M7` contenant deux mots de la liste interdite | `theo-archi` (`ADR-0008`) | **Haute** - porte de la dette n. 4, **deuxieme sprint** |
| 2 | `CON-6` `(b)`, moitie *"servi par toutes ses reponses"*, est **rouge sur `cp-monnaie`**, pas vide : 11 jeux pour 12 items, donc un jeu partage, servi par 2 de ses 3 reponses. La fiche portait deja le fait dans son tableau 14.1 et l'annonce comme vide en 14.4 | `nadia-prof` (la clause), `theo-archi` (le perimetre) | **Haute** - une clause non retroactive rendue opposable a un increment deja valide |
| 3 | `CON-6` `(b')` telle qu'elle est ecrite est **insatisfaisable** sur `cp-monnaie` - 10 reponses sur 11 servies une seule fois. Restreinte aux reponses servies au moins deux fois, elle est verte sur les deux paquets servis, non vide sur les deux, et rouge sur les quatre reponses de l'etat fautif | `nadia-prof` (le domaine), `theo-archi` | **Haute** |
| 4 | Tout encodage de `(b)` ou de `(b')` doit declarer la taille du domaine examine et echouer si elle est nulle : `(b)` n'examine qu'un jeu sur onze sur `cp-monnaie` | `theo-archi` | **Moyenne** - previent la vacuite au lieu de la constater |
| 5 | Le garde-fou *"un rang suffit"* : domaine derive **vide sur `cm1-comprehension`** - 20 items a support, 0 juge. Une generalisation sans clause de non-vacuite par paquet y serait verte sans rien examiner | `theo-archi` (`ADR`), rang a `camille-po` | **Moyenne** |
| 6 | Les trois chiffres du raccourci de rang viennent de **deux instruments** : 35,0 % et 45,0 % de la strategie positionnelle, 95,0 % de la strategie lexicale de `nadia-prof`. Avec l'instrument positionnel, l'etat refuse rend **80,0 %**. La conclusion de `marc-dev` ne bouge pas | `marc-dev` (sa Javadoc est deja juste), `sofia-sm` (la note de perimetre de la review) | **Basse** - factuel |
| 7 | `SUB-8` n'est nommee par aucun test, alors que sa jumelle `NAR-7` est encodee depuis s24 dans le meme module par une table declaree | `marc-dev` | **Basse** |
| 8 | `DES-6` etait annoncee comme devant mordre sur la chaine de la monnaie ; cette chaine est servie et `DES-6` n'est executee par rien | `ines-design` et `nadia-prof` pour la propriete, `theo-archi` pour le perimetre | **Basse** |
| 9 | `MON-6` est non retroactive et n'est executee par rien : le paquet de composition suivant entrera sans qu'aucun test la porte | `nadia-prof` puis `theo-archi` | **Basse** - a l'echeance, pas maintenant |
| 10 | **Obstacle 12, cause etablie** : quatre classes portent `@ArchTest` et `@Test`, produisent deux lignes de journal chacune, et une agregation par nom de classe perd exactement 7 | `theo-archi` | **Information** - ferme le diagnostic, aucune action de ma part |

**Deux points d'information, sans demande.**

- **La correction de sens de la borne de taille de `lea-dev` est portante, et je l'ai eprouvee sur le
  materiau reel plutot que sur son contre-exemple gele.** Mutation `M2` : les dessins reviennent a la
  taille declaree qu'ils portaient avant sa correction, le garde-fou rougit immediatement. **Le
  correctif n'est pas decoratif.**
- **Les deux encodages de `CON-6` livres ce sprint mordent sur le materiau reel** (`M8`, `M9`), et la
  borne haute mord a 7 items sur 12 - la valeur exacte que `sofia-sm` avait mesuree sur `cp-monnaie`
  avant sa reecriture.

---

## 10. Ce que ce rapport ne dit pas

- **Rien sur le Done pedagogique.** Il appartient a `nadia-prof`, il ne decoule pas du vert de la
  section 1, et un increment techniquement irreprochable peut etre refuse.
- **Rien sur ce qu'un contenu vaut.** Les mesures de la section 3 disent ce qu'un automate
  executerait sur une distribution d'indices. Elles ne disent pas si un indice aide un enfant :
  c'est la clause `(d)`, et son autrice ecrit qu'elle n'est pas calculable.
- **Rien sur les garde-fous que je n'ai pas eprouves.** Cette campagne en vise 9 par au moins une
  mutation qui mord, plus les 2 survivants du meme trou. La population derivee de mon rapport de s24
  en comptait 54. **Je ne conclus rien sur les autres.**
- **Rien sur ce qu'un enfant voit.** Toutes mes mesures sont des mesures de source, de contenu servi
  et de test. `M1` et `M7` sont des trous de garde-fou, pas des defauts vus a l'ecran.
- **Rien sur les deltas de tests de la review.** Section 7 : je donne la cause d'un artefact de
  comptage, je ne recalcule pas des chiffres dont je n'ai pas mesure les arbres.

---

## Annexe - les 11 mutations et leur verdict

Colonne *vise* : le garde-fou dont la mutation viole la promesse. Colonne *rouges* : toutes les
classes rouges de l'execution. `verify` a la racine du reacteur complet, aucun `-pl`, **1 018 tests
executes et 140 rapports produits a chaque ligne**.

| Mutation | Ce qu'elle fait au materiau reel | Vise | Vise rouge | Rouges |
|---|---|---|:-:|---|
| `M1` | Un attribut `onclick` sur le bouton de proposition du fragment de manche | `FrontieresDuJavaScriptTest` | **non** | **0 - trou, section 5** |
| `M2` | Un dessin servi declare a nouveau la taille qu'il portait avant la correction de sens | `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest` | oui | 1 |
| `M3` | La taille declaree d'un dessin sort du rapport de son cadre de vue | idem | oui | 1 |
| `M4` | Un fichier d'illustration servi est supprime | `LIllustrationDeContenuResoutEtAucuneNeDortTest` | oui | 2 |
| `M5` | La cle `illustration` est retiree d'un item de `cp-monnaie` | idem | oui | 33 |
| `M6` | Un dessin servi remplit une surface au lieu de la tracer | `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest` (`FIG-3`) | oui | 1 |
| `M7` | Un `<script>` de dix lignes dans le fragment de manche, avec `sessionStorage` et `score` | `FrontieresDuJavaScriptTest` | **non** | **0 - trou, section 5** |
| `M8` | Un indice de `cp-figures-planes` ne va plus qu'avec une seule reponse | `PaquetCpFiguresPlanesTest`, `CON-6` borne basse | oui | 1 |
| `M9` | L'indice majoritaire de `cp-monnaie` passe a 7 items sur 12 | `PaquetCpMonnaieTest`, `CON-6` borne haute | oui | 1 |
| `M10` | Le texte alternatif est retire d'un item illustre | `LIllustrationDUnItemServiAtteintLEcranDeMancheTest` et voisins | oui | 28 |
| `M11` | Un dessin servi porte un element de texte | `LIllustrationDeContenuResoutEtAucuneNeDortTest`, `ADR-0073` clause 4 et `DES-7` | oui | 2 |

*`M5` et `M10` rougissent largement parce que la chaine de chargement du contenu refuse le paquet
mute et que tous les tests qui traversent le catalogue en heritent. **Le mode de defaillance est
bruyant, ce qui est la bonne propriete** - c'est la variante silencieuse qui coute un sprint.*
