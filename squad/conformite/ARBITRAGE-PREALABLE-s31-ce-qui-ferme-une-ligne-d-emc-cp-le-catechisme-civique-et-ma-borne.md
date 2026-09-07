# Arbitrage prealable de s31 - ce qui ferme une ligne d'EMC CP, ce qui ferme une ligne de sciences
# au second passage, le catechisme civique trouve en jouant, et le tri des dix-sept reserves

- **Auteur** : `nadia-prof`, professeure des ecoles, **seule autorite sur le Done pedagogique**.
- **Date** : 2026-09-07.
- **Arbre** : `round-two` a `2ef583ba`, s30 clos. Worktree dedie `s31-nadia-arbitrage`, scratchpad
  dedie a cette instance (amendement de `squad/README.md` ecrit a la retro de s30, section 9).
- **Ce document est un arbitrage prealable.** Il ne prononce aucun Done pedagogique, n'ecrit pas la
  matrice, n'ouvre aucun perimetre et n'affecte personne. Il dit ce qui fermerait, ce que cela
  coute, ce que je refuse d'avance, et ce dont je reste la seule garde.

---

## 0. Ce que j'ai joue aujourd'hui, et ce que je n'ai pas pu voir

**Aucun increment de s31 n'existe : le sprint n'est pas ouvert.** Ce que j'ai joue, c'est l'etat
servi aujourd'hui, sur les quatre paquets dont ce document tire des conclusions. Jar reconstruit
depuis mon propre worktree, application lancee sur le port 8083, **quatre parties completes de dix
manches, une erreur volontaire par manche, poussee jusqu'a trois erreurs partout ou la revelation
etait atteignable** :

| Paquet | Ce que j'ai joue | Ce que j'y ai verifie |
|---|---|---|
| `bandit-manchot-objet-technique-cm1` | partie complete, erreurs volontaires | `OBT-R1` et `OBT-R2` a l'ecran |
| `bandit-manchot-melanges-cm1` | partie complete, erreurs volontaires | `MEL-R1` et `MEL-R3` a l'ecran, plus un fait neuf (section 5, `BIN-1`) |
| `bandit-manchot-permis-interdit-cp` | partie complete, erreurs volontaires | `PER-R1` a l'ecran, plus `CAT-1` (section 2) |
| `bandit-manchot-regles-de-vie-cp` | partie complete, erreurs volontaires | `REG-R1` a l'ecran, plus `CAT-1` (section 2) |

**Audit degrade sur un point, et je le declare** : j'ai joue par requetes HTTP reelles, donc j'ai lu
le **HTML servi**, pas la page rendue par un navigateur. Cela couvre les textes, l'ordre des ecrans,
l'enchainement apres une erreur et apres trois, et le tirage. Cela **ne** couvre ni la couleur, ni
le contraste, ni la taille, ni la position a l'ecran. **Aucune conclusion de ce document ne repose
sur un jugement visuel.**

**Ce que je n'ai pas joue** : les cinquante-quatre autres jeux du catalogue. Ce document ne dit rien
d'eux, sauf la ou il nomme explicitement une passe transverse a faire.

---

## 1. Cinq corrections a mes propres constats, avant de m'en servir

### 1.1 Ma propre derivation de borne soustrait deux fois les memes 0,5, et je refuse le +1,0 qu'elle me devrait

**Ce que j'ai ecrit le 2026-09-05, reconduit le 2026-09-06** :

| Poste | Effet |
|---|---|
| Capacite brute : 2 x 5,0 | **10,0** |
| La passe transverse cesse d'etre un sous-produit gratuit (`PAR-0`, `PAR-4`) et devient une tache payee | **-0,5** |
| Les dettes de portee **D** deviennent payables et doivent l'etre | **-0,5** |
| Retenue de defiance sur la parallelisation | **-1,0** |
| **Borne totale** | **8,0** |

**Ce que la composition de s30 a fait de cette borne**, mot pour mot (`DEC-0079`) : *"une instance
prend les deux lots de sciences (3,0) plus le bloc D (0,5), l'autre prend les cinq autres blocs
(2,25), **la passe transverse (0,5)**, `CONS-1` (0,5) et `REV-3` (0,25) - 3,5 planifies de chaque
cote."* Et ma propre section 3.4 : *"Reste pour les lignes de sciences = 8,00 - 5,00 = 3,00"*, ou
les 5,00 sont les dettes de portee **D** plus la passe.

**Les deux postes que la derivation retranche de la capacite sont ensuite depenses a l'interieur du
resultat.** Ce sont des reservations, pas des reductions : les reserver en abaissant la borne
**et** les payer dans la borne, c'est les reserver deux fois. **Corrigee, ma derivation donne 9,0,
pas 8,0.**

**Je ne prends pas ce +1,0, et le motif n'est pas la prudence.**

1. **Il vient dans le sens qui m'arrange.** Une erreur d'arithmetique trouvee par son auteur, qui se
   trouve lui rendre exactement un lot de plus le jour ou le sprint en voudrait un, est la chose que
   je refuserais a n'importe qui d'autre dans ce depot. *"Une barre qu'on abaisse des qu'elle gene
   vaut aussi peu qu'une barre qu'on releve apres coup"* - je l'ai ecrit le 2026-09-06, contre
   moi-meme, et il s'applique ici sans amenagement.
2. **Il effacerait trois faits de gouvernance deja enregistres.** A 9,0, les trois depassements
   consecutifs (s28 8,25 ; s29 9,50 ; s30 8,50) deviennent deux non-depassements et un depassement
   de 0,50. **Un depassement qui disparait parce que son auteur a corrige sa propre regle apres coup
   n'a jamais eu lieu**, et ce depot a ecrit plusieurs fois qu'un refus efface n'a aucune valeur de
   preuve. La meme chose vaut pour un depassement.
3. **8,0 tient comme chiffre empirique meme si sa derivation ne tient pas.** C'est la valeur a
   laquelle trois sprints mesures ont produit des audits que je peux defendre - dont deux refus
   prononces en jouant, a s30, sur des paquets que la CI declarait verts.

> **Ma borne pour s31 est 8,0.** Elle n'est plus derivee : elle est **empirique et datee**. La
> derivation qui la produisait portait une double soustraction, elle est nommee ici, et je
> m'interdis de m'en servir pour monter. **`camille-po` et l'utilisatrice peuvent decider de prendre
> le +1,0 corrige ; moi, non.** Ce n'est pas ma decision de produit, c'est ma discipline
> d'auditeur.

### 1.2 `SCI-4` a ete tenue a la lettre deux fois et n'a rien protege - je pose une clause neuve, je ne durcis pas l'ancienne

**Ce que `SCI-4` demande**, ecrit le 2026-09-06 : *"au moins trois propositions"* des qu'un
distracteur porte une conception fausse. **Son motif ecrit** : *"ecarter la conception fausse laisse
alors deux candidates"*.

**Ce que les deux paquets ont livre** : trois propositions, a la lettre. Et la troisieme etait, dans
les deux cas, un bouton de remplissage - *"un signal qui se sent"* (`SIG-R2`), *"un melange qui
n'existe pas"* (`MEL-R3`, revu a l'ecran aujourd'hui, manche 6). **La barre a ete franchie et
l'objet de la barre n'a pas ete atteint.** Le defaut est dans ma clause : j'ai compte des
propositions au lieu d'exiger des candidats.

**Je ne durcis pas `SCI-4`.** `SIG-R2` et `MEL-R3` restent des reserves, au prix que j'ai deja
ecrit, sur des lignes que j'ai fermees et que je ne rouvre pas. **Je plante un poteau neuf, a cote
d'une barre deja franchie** (`PLA-1`, section 5), et il vaut pour les paquets ecrits a partir
d'aujourd'hui.

### 1.3 Ma propre table de s30 propose, sur trois lignes, une tache a bonne reponse invariante

Ma table du 2026-09-06 donne, pour chaque ligne de sciences, un couple de taches *"qui ferme, pas
une prescription"*. **Trois de ces couples portent une tache dont la bonne reponse ne varie pas d'un
item a l'autre**, c'est-a-dire exactement la forme que j'ai refusee sur `SCI-CM1-17` :

| Ligne | La tache que j'ai proposee | Le defaut |
|---|---|---|
| **13** - evolution d'un objet technique | *"dire ce qui n'a pas change (la fonction demeure)"* | **La bonne reponse est *la fonction* a tous les items.** Des le deuxieme, l'enfant la presse sans lire |
| **10** - alimentation, activite, sommeil, hygiene | *"dire ce qui est bon pour le corps"* | La bonne reponse est l'option raisonnable a tous les items - voir `CAT-1`, section 2 |
| **14** - regles d'usage d'un environnement numerique | *"reconnaitre la bonne conduite"* | Meme forme |

**Une equipe qui implementerait mon propre exemple mot pour mot se ferait refuser par ma propre
clause.** Je le corrige avant le sprint et non apres : sur ces trois lignes, les couples de taches
sont a reconstruire, et je donne un remplacant en section 4.3.

### 1.4 La position d'une proposition n'est pas une propriete d'un paquet - verifie en jouant, et cela m'interdit une clause que j'allais ecrire

J'allais ecrire, dans la clause de fabrication de la section 5, que *"la bonne reponse ne se
reconnait pas a sa position dans le triplet"*. **En jouant trois fois le meme paquet aujourd'hui,
j'ai vu l'ordre des propositions changer d'une partie a l'autre** : premiere partie
`l'ampoule / l'interrupteur / la pile`, troisieme partie `la pile / l'interrupteur / l'ampoule`, sur
le meme item. **L'ordre des propositions est tire, l'ordre des items aussi.**

Une propriete de position ne peut donc pas etre portee par un paquet ni verifiee sur un paquet : la
clause aurait ete inecrivable et non mordable. **Elle est retiree avant d'exister.** Ce qui reste
verifiable, c'est la **forme** du texte - amorce, longueur, registre - et c'est cela seul que ma
clause retient.

**Lecture qui ne bouge pas** : `POS-R3` parle de *"la bonne au milieu du triplet de quotients"*, ce
qui designe le rang **numerique** du quotient, pas sa place a l'ecran. `POS-R3` est intacte.

### 1.5 Les deux libelles faux de la matrice sont corriges - je leve mon constat de s30

J'avais releve le 2026-09-06 que la matrice portait encore *"vegetarien"* et *"nutrition"* rangee
parmi ses propres composantes, la ou le referentiel etait deja corrige. **Verifie aujourd'hui dans
`squad/programme/MATRICE-couverture.md` : les deux libelles portent desormais la forme juste**
(*herbivore, carnivore, omnivore* ; *les grandes fonctions de nutrition (digestion, respiration,
circulation)*). Le routage a ete execute hors story par `camille-po` avant l'ouverture de s30
(`DEC-0079`). **Mon constat est leve, et je l'ecris parce qu'un constat qu'on laisse ouvert apres
sa correction est aussi trompeur qu'un constat inexact.**

---

## 2. `CAT-1` - ce que j'ai trouve en jouant aujourd'hui, et que mon audit de s29 n'a pas vu

**C'est le fait le plus important de ce document, et il est a mon debit.**

Le 2026-09-03, avant qu'un seul item d'EMC existe, j'ai ecrit dans le referentiel d'EMC CP un
avertissement pour la matiere entiere : *"une mecanique de choix appliquee a l'EMC produit
spontanement du **catechisme civique** - des items ou l'enfant coche la reponse gentille sans que
rien n'ait ete travaille."*

**J'ai audite cinq paquets d'EMC a s29, j'en ai refuse quatre au premier passage, et je n'ai pas vu
que les deux qui ont ferme portaient exactement ce defaut sur leur tache principale.** Je le vois
aujourd'hui en jouant, ce que la relecture des fichiers ne m'avait pas donne.

**`bandit-manchot-regles-de-vie-cp`** (ligne `EMC-CP-06`, `couvert-valide`), tache *"que doit-on
faire"*, releve a l'ecran :

| Manche | Bonne reponse | Distracteurs |
|---|---|---|
| 2 | *en marchant* | *en courant*, *en criant* |
| 4 | *attendre son tour* | *parler plus fort que lui*, *le couper aussitot* |
| 6 | *faire la queue* | *se bousculer*, *se servir tous ensemble* |
| 7 | *les remettre a leur place* | *les laisser tomber par terre*, *les garder pour elle* |

**`bandit-manchot-permis-interdit-cp`** (ligne `EMC-CP-02`, `couvert-valide`), tache *"pourquoi
c'est interdit"* :

| Manche | Bonne reponse | Distracteurs |
|---|---|---|
| 4 | *pour ne pas lui faire mal* | *pour finir la recreation plus vite*, *pour etre le plus fort de la classe* |
| 6 | *pour ne pas le faire souffrir* | *pour parler plus fort que lui*, *pour gagner une recompense* |

**Sur ces six items, la bonne reponse est la seule proposition qui sonne comme l'ecole, et les deux
distracteurs sont les seules qui sonnent comme la faute.** Un enfant qui n'a jamais entendu la regle
gagne en reconnaissant le registre attendu. **C'est `CON-1` : l'item se gagne sans la competence
visee** - et il se gagne d'autant plus surement que l'enfant est docile, ce qui est le contraire de
ce que l'EMC cherche a construire.

**Ce que je fais de ce constat, et ce que je n'en fais pas.**

- **Je ne rouvre ni `EMC-CP-02` ni `EMC-CP-06`.** Elles restent `couvert-valide`. Le poteau est neuf
  et date d'aujourd'hui ; il ne se retourne pas contre une barre franchie il y a deux jours.
- **Je ne l'ajoute pas aux dix-sept reserves.** Il n'en fait pas partie, il est de ce matin, et le
  glisser dans un total que l'utilisatrice m'a demande de trier fausserait son tri.
- **Je le dis en toutes lettres** : **deux des quatorze lignes d'EMC CP sont declarees couvertes par
  des paquets dont la tache principale se gagne largement sans la competence.** L'utilisatrice a le
  droit de le savoir avant de decider ou va l'argent de s31.
- **Son prix est nul si les deux blocs deja dus sont payes.** `REG-R1` et `PER-R1` sont ouvertes sur
  ces deux paquets exactement, differees de s29 a s30 puis de s30 a s31, chiffrees 0,5 chacune.
  **Par `PAR-10`, `CAT-1` voyage avec elles dans le meme rejeu, sans un centime de plus.**

> **`CAT-1` - nouvelle, datee du 2026-09-07, posee avant que les lots d'EMC de s31 soient ecrits.**
> **Sur toute ligne d'EMC, la bonne reponse ne se reconnait pas a son ton.** Un item dont la bonne
> reponse est la seule proposition polie, prudente ou bienveillante, et dont les distracteurs sont
> les seules propositions grossieres, dangereuses ou egoistes, se gagne sans la competence. **Les
> trois propositions sont du meme registre ; ce qui les distingue est ce qu'elles disent, jamais
> comment elles sonnent.**
>
> *Forme qui tient, donnee parce que la clause serait sinon impraticable* : sur *"pourquoi fait-on
> la queue a la cantine ?"*, les trois propositions sont trois raisons serieuses et polies, dont
> deux sont fausses (*"pour que le repas soit plus chaud"*, *"pour que la maitresse compte les
> eleves"*). L'enfant doit alors savoir a quoi la regle sert, ce qui est la moitie du libelle de la
> ligne 6.

---

## 3. Ce qui ferme une ligne d'EMC CP - les dix restantes

**Etat verifie** : 14 lignes, `EMC-CP-02` et `EMC-CP-06` `couvert-valide`, `EMC-CP-11` et
`EMC-CP-12` `non couvrable en l'etat` de nature irreversible (le vote sans collectif, le debat
regle), **dix restantes**. Toutes textuelles, toutes jouables par une mecanique existante, aucune en
attente du palier 2.

**Mon tarif du 2026-09-05, reconduit sans changement** : un lot restreint aux lignes **1 a 7** vaut
**1,0** (j'en suis la source) ; un lot qui touche l'une des lignes **8, 9, 10, 13, 14** vaut **1,5**
(une seule source a verifier suffit a ouvrir la verification). Je ne le renegocie dans aucun des
deux sens.

### 3.1 Les dix lignes, une par une

| Ligne | Peut-elle fermer a s31 ? | Ce qu'il faut, et les deux taches de `FER-3` |
|---|---|---|
| **`EMC-CP-01`** - emotions d'autrui | **oui, et c'est nouveau** | J'ai ecrit le 2026-09-05 qu'elle ne fermerait pas *"a s29"*, faute de seconde tache. **Je nomme aujourd'hui celle que je ne voyais pas** : inferer l'emotion d'un evenement decrit (*"Lea a perdu son doudou"*) et **lire un signe corporel** (*"Tom serre les poings et crie"*) sont deux operations mentales distinctes, et la seconde est au programme du CP. `EMC-2` mord fort : chaque situation n'admet **qu'une** emotion evidente. `FER-1` : les quatre emotions de la parenthese, chacune en bonne reponse. **La plus risquee des quatre lignes a 1,0** |
| **`EMC-CP-03`** - exclusion, en parler a un adulte | **oui** | `EMC-3` amendee donne les deux taches : reconnaitre la situation (*"est-ce une moquerie ou un accident ?"*) et identifier l'adulte selon le lieu. **La bonne reponse n'est jamais une solution que l'enfant applique seul** ; aucun item ne met l'enfant en position de victime ou d'auteur ; aucune violence detaillee. Ligne delicate, a ecrire en dernier dans son lot |
| **`EMC-CP-04`** - differences sans hierarchiser | **non. Aucun lot n'y depense d'items** | Troisieme fois que je l'ecris. `EMC-4` interdit la description des groupes de personnes, distracteurs compris ; il ne reste que la forme de l'interdit, donc **une seule tache**, et `FER-3` ne peut pas etre tenue. `FER-1` echoue aussi : la premiere moitie du libelle (*"les personnes sont differentes"*) ne se sert pas sans enumerer des differences. **Ce qui ouvrirait la porte, et rien d'autre** : une seconde tache portant sur des differences **individuelles nommees dans l'enonce** (deux enfants qui n'aiment pas le meme jeu), jamais sur une categorie de personnes. Je jugerais une telle proposition item par item, et je ne la commande pas |
| **`EMC-CP-05`** - formules de politesse | **oui, en demi-ligne** | `FER-1` : les quatre elements (saluer, remercier, demander, ecouter sans couper) en bonne reponse. Deux taches : choisir la formule qui convient a une situation ; dire quelle regle d'echange un dialogue decrit ne respecte pas. **Mention de couverture partielle exigee d'avance et non negociable** : *"connaissance des formules seule ; leur emploi en situation n'est pas servi"* - le verbe du libelle est *employer*, et ce produit ne verifie jamais ce qu'un enfant fait |
| **`EMC-CP-07`** - regle, souhait, loi | **oui, et c'est la plus sure des cinq a 1,0** | `FER-1` : les trois termes. Deux taches : classer (est-ce une regle pour tous, ou un souhait ?) et reconnaitre qu'une loi s'impose a tous, y compris aux adultes. **Le mot *loi* n'est jamais defini dans l'enonce** : une definition juridique en CP est illisible avant d'etre fausse. Il s'emploie sur des exemples concrets |
| **`EMC-CP-08`** - symboles de la Republique | **oui** (lot a 1,5) | Connaissance exacte. `FER-1` : drapeau, Marianne, La Marseillaise, fete nationale, chacun en bonne reponse. Deux taches : nommer le symbole decrit ; dire ce qu'il represente. **`ART-1` : aucune reponse portee par une couleur percue** |
| **`EMC-CP-09`** - devise et exemple | **oui, mais je la classe derniere** (lot a 1,5) | La devise est une restitution exacte et sure. **La moitie *exemple* est ou `EMC-5` mord le plus fort** : un exemple de fraternite a choisir parmi trois exige que les deux autres soient **nettement** hors sujet, sinon l'item a deux reponses defendables. La ligne ferme, mais elle coute cher a verifier |
| **`EMC-CP-10`** - droits de l'enfant | **oui, et c'est le terrain le plus sur des cinq a 1,5** | `FER-1` : etre protege, etre soigne, aller a l'ecole, jouer. Deux taches : reconnaitre le droit dont parle une situation ; dire ce que le droit garantit. **`BIA-4` et `EMC-1` : aucun item n'interroge l'enfant sur sa propre situation.** *"As-tu le droit d'aller a l'ecole ?"* est refuse d'avance |
| **`EMC-CP-13`** - roles et services de l'ecole et de la commune | **oui** (lot a 1,5) | Connaissance verifiable. Deux taches : a qui s'adresse-t-on pour telle chose ; que fait telle personne ou tel service. **`EMC-6` : aucun item ne suppose la commune, l'ecole ou le foyer de l'enfant.** Le produit ne les connait pas et ne peut pas les connaitre (`DEC-0028`) |
| **`EMC-CP-14`** - gestes d'alerte | **oui, sous `EMC-7` strictement** (lot a 1,5) | Deux taches : que fait-on d'abord ; qui previent-on. **Piege pose d'avance et non negociable** : *"quel numero appelle-t-on en cas d'incendie ?"* admet **18 et 112**, toutes deux justes. Un item qui n'en couronne qu'une est **faux** et refuse sans discussion. Chaque numero cite porte sa source dans le commentaire du paquet, comme `HIS-3` l'exige en histoire |

### 3.2 Ce que cela donne comme reserve de lignes

| Groupe | Lignes | Tarif du lot | Fermables |
|---|---|---|---|
| **Je suis la source** (1 a 7) | `01`, `03`, `05`, `07` (+ `04` **exclue**) | **1,0** | **4**, dont `05` en demi-ligne |
| **Source a verifier** (8 a 14) | `08`, `09`, `10`, `13`, `14` | **1,5** | **5**, toutes pleines |

**Le point que `camille-po` doit voir avant de composer** : le groupe a 1,0 offre **exactement**
quatre lignes pour deux lots de deux, dont une demi-ligne et la plus risquee de la matiere
(`EMC-CP-01`). **Il n'y a aucun remplacant.** Si une seule de ces quatre echoue au premier passage,
le second lot perd une ligne et rien ne peut la remplacer a ce tarif.

---

## 4. Ce qui ferme une ligne de sciences CM1 au second passage

**Etat verifie** : 20 lignes, 4 `couvert-valide` (`SCI-CM1-02`, `-05`, `-12`, `-17`), 1 `non
couvrable en l'etat` irreversible (la demarche d'investigation, ligne 19), **15 restantes**. Tarif
**1,5 par lot, sans exception de ligne** - je l'ai derive le 2026-09-06 contre l'interet de
l'equipe, sur la mesure de `COR-R2`, et je ne le redecoupe pas.

### 4.1 Ce que le premier passage a appris, et ce qui change au second

| Condition | Etat apres s30 | Effet sur s31 |
|---|---|---|
| `SCI-1` (le retour dit pourquoi la reponse est fausse) | **a rencontre du contenu reel et a tenu** | Ne fait plus baisser mon plafond |
| `SCI-2` (aucune responsabilite portee sur l'enfant) | **jamais rencontree** : aucune des quatre lignes fermees ne la mobilise | Fait toujours baisser mon plafond sur les lignes 16 et 18 |
| `SCI-3` (aucune valeur numerique a restituer) | **a tenu, mesuree sur le lot** (zero item numerique sur 22) | Ne fait plus baisser mon plafond |
| `SCI-4` (trois propositions des qu'un distracteur porte une conception fausse) | **tenue a la lettre deux fois, sans rien proteger** | Completee par `PLA-1`, section 5 |
| `CYC-1` (recouvrement CP / CM1) | **rencontree et tenue** sur `objet-technique-cm1`, verifiee contre le paquet de CP | Ne fait plus baisser mon plafond |

**Le solde est favorable** : trois conditions sur cinq ont rencontre du contenu et tenu. C'est ce
qui autorise, pour la premiere fois sur cette matiere, une attente au niveau du plafond plutot que
sous lui.

### 4.2 La famille de defaut qui a produit mes deux refus, et elle est plus large que les sciences

**Mes deux refus de s30 ont la meme cause, et ce n'est pas une erreur de fait.**

- **`SCI-CM1-17`** : les cinq items de la seconde tache sont **un** item, servi cinq fois avec un
  autre nom d'animal - meme question, **meme bonne reponse**, memes deux distracteurs.
- **`SCI-CM1-12`** : les quatre items de la tache de fonction ont tous leurs distracteurs pris dans
  **les fonctions des autres objets du paquet** - un enfant qui ignore l'objet nomme elimine quand
  meme les deux.

**Dans les deux cas, chaque item pris seul est correct.** Le defaut est une **regle de fabrication
appliquee uniformement a tous les items d'une meme tache**, et il ne se voit qu'en lisant la tache
entiere. C'est pour cela qu'une CI verte ne l'a pas vu, que la relecture item par item ne l'a pas
vu, et que je ne l'ai vu qu'en jouant - sur `chaine-alimentaire`, le tirage m'a servi **cinq des
cinq items clones sur dix manches**.

**La meme famille, comptee sur tout s30 et sur les deux instances** : `SCI-CM1-17` (bonne reponse
invariante), `SCI-CM1-12` (distracteurs pris dans le meme vivier), `FOP-R4` (huit distracteurs sur
dix partagent leur amorce, aucune bonne reponse ne la partage), `POS-R3` (la bonne reponse porte le
reste unique une fois sur deux), `DRO-R4` (l'indice nomme la categorie sur trois items sur trois),
`CAT-1` de ce matin (la bonne reponse est la seule du registre poli). **Six occurrences, quatre
matieres, deux niveaux, deux instances.** Ce n'est plus une reserve de paquet.

### 4.3 Les quinze lignes restantes, classees par surete

| Rang | Lignes | Motif |
|---|---|---|
| **Les plus sures** | **1** (etats de la matiere), **7** (regimes alimentaires), **11** (familles de materiaux) | Faits certains, taches naturellement distinctes, distracteurs plausibles sans effort. **Ce sont celles que je prendrais.** Charge `FER-1` : 7, 4 et 5 elements - la ligne 1 ne laisse que 4 items de manoeuvre sur 11 |
| Les suivantes | **8** (etapes du developpement), **3** (mouvement), **4** (energie), **6** (classement, mention partielle due), **20** (document, mention partielle due) | Faisables. La 3 et la 4 portent chacune un piege nomme : le calcul de vitesse compte sur les mathematiques ; *source* contre *forme* n'est pas tranchable sur tous les exemples |
| **A ne pas prendre en l'etat** | **13**, **10**, **14** | **Non pour la matiere, mais pour le couple de taches que j'avais moi-meme propose** (section 1.3) : sa seconde tache a une bonne reponse invariante. Elles redeviennent prenables des qu'un autre couple est propose, et j'en donne un ci-dessous |
| **A ne pas prendre non plus** | **18** (impact humain), **16** (risques), **15** (Terre et saisons), **9** (fonctions et organes) | `SCI-2`, `BIA-2`, `SCI-1` a son point le plus exigeant, et le piege `HIS-2` de l'organe de la digestion. Aucune de ces conditions n'a rencontre de contenu reel |

**Les trois couples de taches que je remplace, puisque les miens etaient defectueux** :

- **Ligne 13** : *dire ce qui a change entre l'objet ancien et l'objet actuel* (la reponse varie
  d'un objet a l'autre) et *nommer la fonction que les deux objets remplissent* (la reponse varie
  aussi). **Jamais *"qu'est-ce qui n'a pas change ?"***, dont la reponse est *la fonction* a chaque
  fois.
- **Ligne 10** : *dire a quoi sert un de ces quatre besoins* (dormir, bouger, se laver, manger
  varie) et *dire ce qui arrive au corps quand l'un manque*. **Jamais *"qu'est-ce qui est bon pour
  le corps ?"***, dont la reponse est l'option raisonnable a chaque fois.
- **Ligne 14** : *dire ce qu'une donnee personnelle permet a un inconnu de faire* et *dire a qui on
  s'adresse quand quelque chose se passe mal en ligne*. **Jamais *"quelle est la bonne
  conduite ?"***.

**Une composition que je tiens pour tenable, si elle est demandee** : lot 1 = lignes **1 et 7**,
lot 2 = lignes **11 et 8**. Quatre lignes, 44 items, `SCI-2` et `BIA-2` non mobilisees, `SCI-1`
mobilisee sur peu d'items. **Le choix appartient a `camille-po`.**

---

## 5. Les clauses neuves, datees du 2026-09-07

Elles sont posees **avant** que les lots soient ecrits, comme toutes les miennes. Elles ne
s'appliquent a aucun paquet existant et ne rouvrent aucune ligne fermee.

> **`FAB-1` - la regle de fabrication ne se repete pas a l'interieur d'une tache.** Sur les items
> qui servent une meme tache :
> **(a)** deux items ne portent pas la **meme bonne reponse** ; une tache dont tous les items ont la
> meme bonne reponse compte pour **un** item au titre de `FER-2` et de `FER-3`, quel que soit le
> nom, le lieu ou le nombre qui change dans l'enonce ;
> **(b)** les distracteurs ne sont pas pris systematiquement dans le vivier des bonnes reponses des
> **autres** items de la meme tache ;
> **(c)** la bonne reponse ne se reconnait pas a la **forme** de son texte - son amorce, sa
> longueur, son registre - sur plus d'un tiers des items de la tache.
> **Le controle se fait sur la tache entiere, jamais item par item** : c'est le seul niveau ou le
> defaut existe, et c'est ce qui a produit mes deux refus de s30.
>
> *Ce que `FAB-1` n'interdit pas, et je le dis pour qu'on ne la lise pas trop large* : un classement
> a categories fermees (homogene / heterogene, herbivore / carnivore / omnivore) sert
> necessairement les memes propositions a chaque item. **C'est licite** des lors que la bonne
> reponse **varie**, ce que `FER-1` exige de toute facon.

> **`PLA-1` - une proposition est un candidat, jamais un remplissage.** Chaque distracteur d'un item
> est une reponse qu'un enfant du niveau vise peut serieusement envisager : une categorie qu'on lui
> a enseignee, ou une reponse qu'il pourrait vraiment donner. **Un item dont l'une des propositions
> n'appartient a rien redevient binaire de fait**, meme quand le compte de propositions est tenu.
> Transverse a toutes les matieres, et non propre aux sciences : `REG-R1` porte le meme motif sur
> l'EMC CP depuis le 2026-09-05. **Complete `SCI-4` sans l'abroger** : `SCI-4` compte, `PLA-1`
> qualifie.

> **`BIN-1` - une partie ne se joue pas majoritairement a pile ou face.** Sur une ligne dont la
> tache principale est un classement a **deux** categories, **au plus la moitie des items d'un
> paquet porte deux propositions**.
> *Motif, mesure en jouant aujourd'hui* : sur `bandit-manchot-melanges-cm1`, dix manches, **sept
> relevaient du classement et six portaient deux propositions**. L'enfant a joue une partie dont la
> majorite se gagne au hasard. **Ce n'est pas un grief contre `SCI-CM1-02`, que j'ai validee et que
> je ne rouvre pas** : c'est un poteau neuf, plante a cote d'une barre franchie.
> *Pourquoi la clause est verifiable sur le paquet* : un paquet de fermeture porte onze items pour
> dix manches (`DEC-0064`), donc le tirage est presque exhaustif et une propriete comptee sur le
> paquet vaut pour la partie.

> **`CAT-1`** - enoncee en section 2.

---

## 6. Le tri des dix-sept reserves - lesquelles enseignent quelque chose de faux

**Le critere que j'applique est celui que j'ai ecrit le 2026-09-06** pour differer `PER-R1` et
`REG-R1` : *"les deux seules des quinze ou **rien de faux n'est enseigne** et ou **aucun item ne se
gagne sans la competence**"*. Il a deux branches. **L'utilisatrice n'en demande qu'une** ; je reponds
a celle-la, et je chiffre l'autre a cote, parce qu'ecarter une branche sans dire ce qu'elle laisse
impaye serait un tri tronque.

### 6.1 Groupe 1 - quelque chose de faux est enseigne : **deux des dix-sept, 1,0**

**Verifiees a l'ecran aujourd'hui, en jouant, pas dans mes notes de la veille.**

| Reserve | Paquet | Le faux, cite | Prix |
|---|---|---|---|
| **`MEL-R1`** | `melanges-cm1` | L'indice de l'item du sel, servi deux fois sur mes deux erreurs : *"Le sel dissous ne peut pas etre retenu par un filtre. **Il faut faire disparaitre l'eau.**"* **L'eau ne disparait pas, elle se vaporise**, et c'est exactement la conception fausse que `SCI-CM1-01` (etats de la matiere) devra defaire. **Voyage avec** la correction a cout nul de l'item 1, ou l'enonce dit *"Dans un bol"* et l'indice repond *"Toute la carafe"* - vu a l'ecran, manche 1 | **0,5** |
| **`OBT-R1`** | `objet-technique-cm1` | Manche 9. Bonne reponse : *"a se deplacer plus vite et **avec moins d'effort**"*. Indice servi apres l'erreur : *"En pedalant, on avance plus vite qu'a pied, **pour une fatigue proche**."* **L'indice dement la bonne reponse de son propre item, et il est faux au fond** : a distance egale, le velo coute moins d'effort que la marche, et c'est le sens meme de la machine | **0,5** |

> **Payer le groupe 1 coute 1,0** et, par `PAR-10` (un rejeu se paie par paquet, pas par reserve),
> **solde cinq des dix-sept** : `MEL-R1`, `MEL-R2`, `MEL-R3`, `OBT-R1`, `OBT-R2`. Les trois
> compagnes voyagent gratuitement.

### 6.2 Groupe 2 - peut attendre : **quinze des dix-sept, 4,0**, plus `CONS-3` non chiffree

**Mais elles ne peuvent pas toutes attendre pour la meme raison, et ce detail est le tri utile.**

| Sous-groupe | Reserves | Ce qui est en jeu | Prix |
|---|---|---|---|
| **2a - l'item se gagne sans la competence** (`CON-1`), **sur des lignes deja `couvert-valide`** | `SIG-R1`, `SIG-R2`, `MEL-R2`, `MEL-R3`, `POS-R3`, `CHA-R1`, `OBT-R2`, `HG-R8`, `FOP-R4`, `DRO-R4` - **dix** | Rien de faux n'est dit. **Mais la matrice declare couvertes des lignes que ces paquets ne servent pas sur ces items-la.** C'est la branche que le filtre *"faux seulement"* laisse impayee, et je la chiffre pour qu'elle soit ecartee en connaissance de cause | **3,0**, dont **1,5** solde gratuitement par le groupe 1 |
| **2b - lisibilite, consigne, gabarit** | `CONS-1` reliquat, `CONS-2`, `CONS-3`, `SAI-R1`, `ERR-R1` - **cinq** | Aucun apprentissage faux, aucun item gagne sans la competence. **Ce sont les seules des dix-sept qui peuvent attendre sans rien couter au fond** | **1,0** + `CONS-3` non chiffree |

**2 + 10 + 5 = 17.** Aucune n'est perdue.

### 6.3 Le cas de bord, nomme plutot que range en silence

**`CONS-1`, reliquat sur `histoire-geographie-cm1`.** La consigne servie nomme deux taches qui
couvrent **26 des 57 items** ; pour les 31 autres, le produit dit a l'enfant de faire quelque chose
qu'il ne lui demande pas. **Ce n'est pas un fait scolaire faux, c'est un enonce faux sur le jeu
lui-meme** - la seule des dix-sept dans ce cas.

**Je la laisse en 2b**, parce que le critere que l'utilisatrice me donne porte sur ce que l'enfant
apprend, et qu'une consigne inexacte ne lui met aucune idee fausse en tete sur le monde. **Si le
critere retenu est *"le produit affirme quelque chose de faux"* plutot que *"l'enfant apprend
quelque chose de faux"*, elle passe au groupe 1, qui devient trois reserves pour 1,5.** Le choix est
a `camille-po` ; je nomme les deux lectures plutot que d'en imposer une.

### 6.4 Ce que le total des dix-sept ne dit pas

**La dette d'EMC CP n'est pas dans les dix-sept.** `REG-R1` et `PER-R1`, differees de s29 vers s30
puis de s30 vers s31 sur mon propre motif, sont ouvertes, chiffrees **0,5 chacune, 1,0 au total**,
et ne sont comptees nulle part dans le 5,0. **Avec `CAT-1` qui voyage avec elles gratuitement, 1,0
repare les deux seules lignes que la matiere revendique aujourd'hui.**

> **Dette reelle a l'ouverture de s31 : 5,0 (les dix-sept) + 1,0 (les deux differees) + `CONS-3`
> non chiffree = 6,0 au moins, sur une borne de 8,0.**

---

## 7. Ma borne, le prix des lots, et ma repartition

### 7.1 La borne

**8,0**, section 1.1, empirique et non derivee. **Budget opposable par instance : 4,0, marge de
rejeu comprise** (`PAR-9`). Plafond dur individuel de 5,0 (`PAR-8`) inchange, et ce n'est toujours
pas le chiffre de planification.

**Marge de rejeu, `PAR-11` appliquee** : 0,5 par lot neuf audite, par instance, la marge du sprint
etant leur **somme**.

| Instance | Lots neufs | Marge due |
|---|---|---|
| A (EMC CP, 2 lots) | 2 | **1,0** |
| B (sciences CM1, 2 lots) | 2 | **1,0** |
| **Marge du sprint** | | **2,0** |

**Ce que la marge couvre exactement, et ce qu'elle ne couvre pas.** Un rejeu se paie par paquet
(`PAR-10`), un lot porte deux paquets : **0,5 par lot couvre le reaudit d'un seul paquet sur les
deux.** A s29, **quatre paquets d'EMC sur cinq ont ete refuses au premier passage**. Si l'EMC CP
reproduit ce taux sur les quatre paquets de l'Instance A, il faut **1,5 a 2,0 de reaudit** contre
**1,0** de marge.

**Je n'amende pas `PAR-11` pour autant.** Je l'ai ecrite hier, je l'applique telle quelle, et je
nomme l'exposition qu'elle ne couvre pas plutot que de deplacer ma propre regle un jour apres
l'avoir plantee. **Cet ecart de 0,5 a 1,0 est la cause la plus probable d'un quatrieme depassement
consecutif**, il est chiffre ici avant le sprint, et les deux facons de l'eviter appartiennent a
`camille-po` : un lot d'EMC de moins, ou une ligne de moins par lot.

### 7.2 Le prix des lots

| Lot | Tarif | Motif |
|---|---|---|
| Sciences CM1, chacun des deux | **1,5** | Je ne suis pas la source ; chaque fait se verifie contre une source. Aucune exception de ligne (derive le 2026-09-06 sur la mesure de `COR-R2`) |
| EMC CP restreint aux lignes 1 a 7 | **1,0** | J'en suis la source |
| EMC CP touchant une ligne 8, 9, 10, 13 ou 14 | **1,5** | Une seule source a verifier ouvre la verification |

### 7.3 Ma repartition - deux compositions qui tiennent dans 8,0, et celle que je recommande

**Composition 1 - celle que l'utilisatrice a retenue, quatre lots.**

| Instance | Postes | Total |
|---|---|---|
| **A** | 2 lots EMC CP **tous deux restreints aux lignes 1 a 7** (1,0 + 1,0) + marge `PAR-11` (1,0) + bloc `melanges` (0,5) + bloc `objet-technique` (0,5) | **4,0** |
| **B** | 2 lots sciences CM1 (1,5 + 1,5) + marge `PAR-11` (1,0) | **4,0** |
| | | **8,0** |

**Ce qu'elle achete** : le plafond maximal, **8 lignes**, et le groupe 1 du tri - les deux reserves
qui enseignent du faux - integralement paye.
**Ce qu'elle ne finance pas** : **la passe transverse** (`PAR-4` en fait une tache payee ; il n'y a
pas 0,5 pour elle), **`REG-R1` et `PER-R1`** differees une troisieme fois, et donc **`CAT-1`**, qui
voyageait gratuitement avec elles.
**Ce qu'elle exige** : les quatre lignes d'EMC viennent **obligatoirement** de `01`, `03`, `05`,
`07` - la totalite du groupe a 1,0, sans un remplacant, avec `05` en demi-ligne et `01` la plus
risquee de la matiere.

**Composition 2 - celle que je recommande, trois lots.**

| Instance | Postes | Total |
|---|---|---|
| **A** | 1 lot EMC CP restreint aux lignes 1 a 7 (1,0) + marge `PAR-11` (0,5) + blocs `regles-de-vie` et `permis-interdit` (1,0, `CAT-1` voyage avec) + bloc `melanges` (0,5) + bloc `objet-technique` (0,5) + passe transverse (0,5) | **4,0** |
| **B** | 2 lots sciences CM1 (1,5 + 1,5) + marge `PAR-11` (1,0) | **4,0** |
| | | **8,0** |

**Ce qu'elle achete** : **6 lignes** au plafond, le groupe 1 paye, **les deux lignes d'EMC deja
revendiquees reparees**, et la passe transverse financee.
**Ce qu'elle coute** : **deux lignes de couverture.**

**Mon avis, et il est couteux au sprint** : je recommande la composition 2. Motif en un fait, mesure
ce matin : **les deux seules lignes d'EMC CP que le produit revendique sont servies par des paquets
dont la tache principale se gagne au ton.** Ajouter quatre lignes a une matiere qui en revendique
deux qu'elle ne tient pas, sans reparer les deux, c'est augmenter le numerateur en degradant ce
qu'il signifie. **Et il y a un effet de contagion previsible** : l'Instance A ecrira ses paquets
neufs en lisant les paquets existants de la matiere, qui sont aujourd'hui le modele du defaut.

**Je ne priorise pas et je n'ouvre aucun perimetre.** Les deux compositions sont chiffrees, elles
tiennent toutes deux exactement dans 8,0, `camille-po` tranche.

**L'objet de la passe transverse, si elle est financee** : la propriete `FAB-1c` sur les 58 entrees
du catalogue - une bonne reponse reconnaissable a l'amorce ou a la longueur de son texte. C'est la
seule des quatre proprietes neuves qui se compte plutot qu'elle ne se lit, donc la seule tenable en
0,5 sur tout le catalogue. **Elle ne couvre ni `PLA-1`, ni `CAT-1`, ni `FAB-1b`**, et je le dis pour
qu'on ne les croie pas balayees par elle.

### 7.4 Le plafond arithmetique de lignes fermables

**Trois appuis, aucun degre de liberte.** `FER-2` : au moins dix items par ligne. `ADR-0081` clause
5 : un item n'est compte que sur une ligne. `DEC-0064` : onze items au moins dans un paquet de
fermeture a dix manches. Donc **un lot de 22 items ferme deux lignes au plus**, et 1,5 de ma borne
achete au mieux 2 lignes en sciences, 1,0 au mieux 2 lignes en EMC restreint.

> **Plafond arithmetique de s31, composition 1 (quatre lots) : 8 lignes.**
> **56 + 8 = 64 / 138 = 46,4 %.**
>
> **Plafond arithmetique de s31, composition 2 (trois lots) : 6 lignes.**
> **56 + 6 = 62 / 138 = 44,9 %.**

**Mon attente raisonnable, composition 1 : 5 a 7 lignes, soit 61 a 63 / 138, c'est-a-dire 44,2 % a
45,7 %.**

**Et je corrige d'avance la lecture de mon attente, parce que je me suis trompee dans le meme sens
deux fois.** A s29 j'attendais 7 a 8 sur un plafond de 9 et le resultat a ete 9 ; a s30 j'attendais
3 a 4 sur un plafond de 4 et le resultat a ete 4. **J'ai sous-estime l'equipe deux fois de suite.**
Je ne monte pourtant pas a 8, et le motif est arithmetique et non prudentiel : **les quatre lignes
d'EMC de la composition 1 doivent toutes venir d'un groupe de quatre sans remplacant, dans une
matiere dont le taux de refus au premier passage mesure est de quatre paquets sur cinq, avec une
marge qui couvre un paquet par lot.** Les 8 lignes exigent que ce taux tombe de 80 % a 50 % ou
moins. Sur les sciences, ou trois conditions ont deja rencontre du contenu et tenu, j'attends **3 a
4 lignes sur 4** ; c'est sur l'EMC que mon attente descend, et c'est mesure, pas suppose.

---

## 8. La retenue de 1,0 - ce qu'il en advient si `PAR-5` est ecrit avant la fin de s31

**La barre que j'ai ecrite le 2026-09-05, mot pour mot** : *"un second sprint parallele sans defaut
de couture, **et** le registre d'identifiants de `PAR-5` ecrit. **Les deux, pas l'un des deux.**"*

**Etat des deux conditions au 2026-09-07 :**

| Condition | Etat | Peut-elle etre acquise a s31 ? |
|---|---|---|
| Un sprint parallele **sans defaut de couture** | **echouee a s30, deux fois** : `VIV-R2` contre `CONS-1` sur `vivant-non-vivant-cp`, puis la consigne d'`histoire-geographie-cm1`, trois reecritures | **Seulement a la cloture de s31**, jamais a son ouverture |
| Le registre `PAR-5` ecrit | **non ecrit**, troisieme routage ; `theo-archi` l'ecrit en parallele de moi ce sprint | **Oui, dans le sprint** |

**Reponse a la question posee, sans detour :**

1. **Pour s31, la retenue reste. Ma borne est 8,0.** Une condition ne peut pas etre payee d'avance
   sur un resultat non observe - c'est exactement le raisonnement que je refuse a une CI verte.
2. **Si `PAR-5` est ecrit avant la fin de s31, la seconde condition est acquise, et elle seule.**
   Elle ne leve rien par elle-meme : *"les deux, pas l'un des deux"*. **Ecrire le registre ne
   rachete pas la couture rompue a s30.**
3. **Si `PAR-5` est ecrit dans s31 ET que s31 clot sans defaut de couture, les deux conditions sont
   remplies a la cloture de s31, et la retenue tombe pour s32 : borne 9,0.** C'est la seule facon
   dont elle tombe, et je m'y tiendrai sans qu'on ait a me la redemander.
4. **Si `PAR-5` est ecrit et qu'un defaut de couture survient, la retenue reste**, et je l'ecrirai
   avec le defaut qui la maintient.

**Ce qu'est un defaut de couture, nomme ici pour que la condition se constate a la cloture sans me
redemander ce que je voulais dire.** Un defaut de couture est un defaut **produit a la jointure de
deux travaux ou de deux instances, invisible de l'interieur de chacune des deux moities**. Trois
formes, toutes constatees dans ce depot :

- **(a)** une story qui modifie un paquet et une story qui modifie sa consigne dans le meme sprint,
  laissant la consigne fausse sur le paquet livre (s30, deux fois) ;
- **(b)** un chiffre de sprint que ni l'une ni l'autre instance ne peut verifier depuis sa moitie,
  et qu'un tiers trouve faux en recomptant (s29, la neuvieme ligne omise du total) ;
- **(c)** deux instances ecrivant dans une meme ressource partagee sans le savoir - identifiants,
  fichier, scratchpad (s29 et s30 ; l'amendement de `squad/README.md` ferme le scratchpad, pas les
  identifiants, tant que `PAR-5` n'existe pas).

**Une seule occurrence de l'une des trois formes suffit a maintenir la retenue.** C'est la barre,
elle est ecrite avant le sprint, et elle ne bouge dans aucun sens.

---

## 9. Mon avis sur EMC CP contre une autre matiere

**La question m'est posee ouvertement, et je reponds sur les deux plans.**

**Sur le choix de la matiere : EMC CP est le bon choix, et le motif de l'utilisatrice tient.** Les
trois lignes de questionner le monde CP qui dependent du palier 2 rendent cette matiere
partiellement inaccessible ce sprint ; l'EMC CP porte dix lignes toutes textuelles, toutes jouables
avec ce qui existe, sur une matiere ou deux lignes ont deja ferme, ou les sept conditions ont deja
rencontre du contenu reel, et **dont les codes de ligne sont alloues** - sans quoi `FER-1` et
`FER-2` ne seraient pas calculables. **Aucune des huit matieres sans codes alloues n'est prenable**,
et aucune autre matiere ne presente ce profil. Je ne propose pas de remplacante.

**Sur le volume : deux lots d'EMC CP est un lot de trop, et c'est la seule reserve de mon avis.**
Trois faits, tous mesures :

1. **Quatre paquets d'EMC sur cinq refuses au premier passage a s29** - le pire taux de premier
   passage de ce depot, sur la seule matiere qui l'ait connu.
2. **Les deux lignes que la matiere revendique sont servies par des paquets qui portent `CAT-1`**,
   trouve ce matin en jouant, dans une matiere ou j'avais annonce ce risque quatre jours avant le
   premier item.
3. **La marge de `PAR-11`, 1,0 pour deux lots, couvre un paquet reaudite par lot.** Au taux mesure,
   il en faudrait deux a trois.

**Ma recommandation est la composition 2 de la section 7.3** : un lot d'EMC CP, deux lignes, et le
1,0 libere paye les deux blocs differes qui reparent les deux lignes deja revendiquees. **Cela coute
deux lignes de couverture au sprint, et je le dis en sachant que la couverture est l'objectif.** Une
matiere qui revendique quatre lignes de plus en en tenant mal deux ne progresse pas de quatre.

---

## 10. Ce que je refuse d'avance, et ou cela cassera d'abord

**Chaque refus porte l'endroit ou il se realisera en premier**, pour qu'il soit evitable plutot que
constate.

| # | Ce que je refuse | Ou cela cassera d'abord |
|---|---|---|
| 1 | **Tout item sur `EMC-CP-04`.** Aucun lot n'y depense d'items | Un developpeur composant depuis la matrice, ou la ligne est `non couvert` **sans annotation**. Route a `camille-po` : l'annoter, ou vingt items partiront dedans |
| 2 | **Tout item d'EMC dont la bonne reponse se reconnait a son ton** (`CAT-1`) | Sur `EMC-CP-03` (*"en parler a un adulte"* comme unique reponse bienveillante), `EMC-CP-05` (la formule polie contre deux grossieretes) et `EMC-CP-10`. C'est la pente naturelle de la matiere, elle a deja emporte deux paquets |
| 3 | **Tout item de sciences dont une tache a une bonne reponse invariante** (`FAB-1a`) | Sur la ligne 13, avec **mon propre couple de taches** si personne ne lit la section 1.3 ; puis sur les lignes 10 et 14 |
| 4 | **Tout item dont les distracteurs sont pris dans les bonnes reponses des autres items de la meme tache** (`FAB-1b`) | Sur la ligne 7 (regimes) et la ligne 11 (materiaux), ou le vivier est ferme et la tentation maximale. **Precision** : servir les trois memes regimes a chaque item est licite ; ce qui ne l'est pas, c'est que le regime juste soit toujours le meme |
| 5 | **Toute troisieme proposition qui n'est un candidat pour personne** (`PLA-1`) | Sur la ligne 1 (etats de la matiere), des que `SCI-1` demandera trois propositions pour loger une conception fausse. C'est la ou `SIG-R2` et `MEL-R3` sont nees |
| 6 | **Tout paquet de classement binaire dont plus de la moitie des items portent deux propositions** (`BIN-1`) | Sur les lignes 6, 13, 14, 18 des sciences et sur `EMC-CP-07` (regle contre souhait) |
| 7 | **Tout item disant que l'eau disparait, se perd ou s'en va**, sur la ligne 1 | Sur la ligne 1 elle-meme. **Et si la ligne 1 est prise a s31 sans que `MEL-R1` soit paye, le produit se contredira sur deux paquets de la meme matiere et du meme niveau, dans le meme sprint** |
| 8 | **Tout item de `EMC-CP-08` dont la reponse est portee par une couleur percue** (`ART-1`) | Sur *"de quelle couleur est le drapeau ?"*, qui est l'item le plus evident de la ligne et sera donc le premier ecrit. Il exclut un enfant daltonien et ne teste pas le symbole. On demande le **nom** |
| 9 | **Tout item de `EMC-CP-14` dont deux propositions sont toutes deux correctes** (`EMC-7`) | Sur *"quel numero pour un incendie ?"* : **18 et 112** sont justes toutes les deux. Item faux, refus sans discussion. Chaque numero porte sa source dans le commentaire du paquet |
| 10 | **Tout item interrogeant l'enfant sur lui-meme, son foyer, sa commune ou sa famille** (`EMC-1`, `EMC-6`, `BIA-4`) | Sur `EMC-CP-10` (*"as-tu le droit de..."*) et `EMC-CP-13` (*"ta mairie"*). Ce sont les formulations naturelles des deux lignes |
| 11 | **Tout item de `EMC-CP-09` dont l'exemple admet deux reponses defendables** (`EMC-5`) | Sur la moitie *"exemple de fraternite"*, ce qui est la raison pour laquelle je classe cette ligne derniere |
| 12 | **Tout item calculant une vitesse** compte sur la ligne 3 des sciences | Il compte sur les mathematiques CM1 (`ADR-0081` clause 5), et le lot croira avoir dix items quand il en aura sept |
| 13 | **Toute ligne d'EMC presentee comme attestant une conduite** | Dans le libelle de matrice a la fermeture. Ce produit verifie ce qu'un enfant **sait**, jamais ce qu'il **fait** |
| 14 | **Toute fermeture de `EMC-CP-05` sans sa mention de couverture partielle** | A la cloture. La mention *"connaissance des formules seule ; leur emploi en situation n'est pas servi"* est due, et je refuse la ligne sans elle |
| 15 | **Tout Done pedagogique sur un lot que je n'ai pas joue**, et tout reaudit compte comme gratuit hors de la marge | A la cloture, si le sprint deborde. Un reaudit se paie ; s'il n'y a plus de budget, la ligne ne ferme pas - elle ne ferme pas *quand meme* |

---

## 11. Ce dont je reste la seule garde, et ce qui devient mordable

**Dit explicitement pour que l'equipe ne croie pas couvert ce qui ne l'est pas.** Aucun garde-fou
n'a ete ajoute a s30 sur `OBJ-R2`, `OBT-R1` ni `OBT-R2` : elles restent ou elles etaient.

| Propriete | Qui la tient | Automatisable ? |
|---|---|---|
| **`CAT-1`** - la bonne reponse ne se reconnait pas a son ton | **moi seule** | **non.** C'est un jugement de registre, et c'est le plus dangereux de la matiere |
| **`PLA-1`** - chaque proposition est un candidat serieux | **moi seule** | **non.** La plausibilite pour un enfant de neuf ans ne se calcule pas |
| **`FAB-1a`** - pas de bonne reponse invariante dans une tache | `theo-archi` peut la mordre | **oui, si le paquet declare la tache de chaque item.** Aujourd'hui il ne la declare pas ; c'est le meme manque qu'`ADR-0081` a comble pour les lignes de referentiel |
| **`FAB-1b`** - distracteurs pris hors du vivier des bonnes reponses de la tache | `theo-archi` peut la mordre | **oui**, comparaison d'ensembles, sans declaration supplementaire |
| **`FAB-1c`** - la bonne reponse ne se reconnait pas a sa forme | **moi**, partiellement | **partiellement** : l'amorce commune et la longueur se comptent ; le registre, non |
| **`BIN-1`** - au plus la moitie des items binaires sur une ligne a deux categories | `theo-archi` peut la mordre | **oui**, comptage d'items a deux propositions rapporte au nombre de manches |
| `EMC-2`, `EMC-5` - aucune situation a deux reponses defendables | **moi seule** | non |
| `EMC-3` - la posture des items sur l'exclusion | **moi seule** | non |
| `EMC-4` - aucun trait attribue a une categorie de personnes, distracteurs compris | **moi seule** | non |
| `EMC-7` - exactitude des numeros d'urgence et des symboles | **moi**, contre source | non |
| `SCI-2` - aucune responsabilite portee sur l'enfant | **moi seule** | non |

**Quatre des onze proprietes de cette table sont mordables** (`FAB-1a`, `FAB-1b`, `FAB-1c` en
partie, `BIN-1`) et **elles couvrent la famille qui a produit mes deux refus de s30 et six des
defauts du sprint**. Je dis la propriete, jamais la forme du test : la forme appartient a
`theo-archi`. **C'est le routage le plus rentable que je puisse faire ce sprint**, et il decharge
reellement une part de ce que je garde seule aujourd'hui.

---

## 12. Ce que ce document ne fait pas

- **Il ne prononce aucun Done pedagogique.** Aucun increment de s31 n'existe.
- **Il ne touche pas a `squad/programme/MATRICE-couverture.md`.** Son compte appartient a
  `camille-po`.
- **Il ne priorise pas et n'ouvre aucun perimetre** (`camille-po`), **ne gele rien et n'affecte
  personne** (`sofia-sm`).
- **Il n'ecrit aucun contenu a la place d'un developpeur** : les couples de taches sont des exemples
  qui ferment, jamais des prescriptions, et je le repete parce que trois de mes exemples de s30
  etaient defectueux.
- **Il ne modifie aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/`.** J'ai
  lu deux paquets YAML et joue quatre parties ; je n'y ai rien ecrit.
- **Il ne rouvre aucune ligne `couvert-valide`**, y compris les deux lignes d'EMC CP sur lesquelles
  je viens de trouver `CAT-1`.
- **Il n'efface aucun verdict et ne durcit aucune clause anterieure.** `SCI-4` est intacte ;
  `PLA-1`, `FAB-1`, `BIN-1` et `CAT-1` sont neuves, datees du 2026-09-07, et ne valent que pour les
  paquets ecrits a partir d'aujourd'hui.
