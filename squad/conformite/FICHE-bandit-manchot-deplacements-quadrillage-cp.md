# Fiche de conformite - bandit-manchot-deplacements-quadrillage-cp

- **Mecanique** : bandit manchot.
- **Contenu** :
  `adapters/adapter-content/src/main/resources/contenu/cp-deplacements-quadrillage.yaml`
  (12 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-deplacements-quadrillage-cp`, titre servi *"Decrire un
  deplacement"*, consigne servie *"Lis bien, puis dis sur quelle case l'enfant se trouve."*
  (corrigee par `lea-dev` au second passage ; elle disait *"Lis bien, puis dis quel geste est
  decrit."* au premier), `nombreDeManches: 10`.
- **Ligne declaree** : `MAT-CP-10`, **couverture partielle des l'origine** (voir ci-dessous).
- **Statut global** : **DONE PEDAGOGIQUE PRONONCE - VALIDE SOUS RESERVE** le 2026-09-24 par
  `nadia-prof` (instance B), **au second passage**, apres avoir rejoue le jar reconstruit.
  **REFUSE au premier passage** le meme jour, sur deux motifs chacun suffisant ; **corrige par
  `lea-dev`** (commits `da1b0b61`, `7317b990`) ; **les deux motifs sont payes**, verifie en
  rejouant. **Les deux blocs de validation sont conserves ci-dessous, dans l'ordre** - un refus
  efface n'a aucune valeur de preuve. **`MAT-CP-10` passe a `en cours`**, jamais a
  `couvert-valide`, avec la mention de couverture partielle. **Trois reserves neuves et datees du
  second passage**, qui ne retiennent pas la ligne. Fiche ouverte par `camille-po` a l'ouverture du
  bloc de construction du sprint s42 (paquet et catalogue livres et fusionnes dans `round-two`,
  commit `00952c9a`, contenu au commit `051ba2bb`).
- **Story** : `S42-MAT-TEXTUEL` - Se reperer et decrire des deplacements sur un quadrillage
  simple, moitie textuelle seulement (`docs/backlog/backlog.md`).
- **Competence programme visee** : mathematiques, CP (cycle 2), domaine "Espace et geometrie" -
  `squad/programme/mathematiques/competences-cp.md`, `MAT-CP-10`. Ligne `non couvert` dans
  `squad/programme/MATRICE-couverture.md` (ligne 227) au moment d'ouverture de cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage
  neuf a router**
  (`ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-
  ligne-marginale.md`, section 17.7) : *"Oui, `MAT-CP-10` demande le palier 2, et pour une moitie
  seulement. Se reperer et se deplacer sur un quadrillage est une designation sur un dessin et
  rien d'autre ne la sert ; decrire un deplacement n'est pas une designation et se sert en choix
  textuel, dans un second paquet. La ligne ne ferme donc pas par le palier 2 seul, et la mention
  [...] est due."* Repris sans reouverture par `DEC-0097` (section 6) et `DEC-0098` (section 5) :
  cette story ne couvre que la moitie *decrire un deplacement*, en choix textuel ; la moitie *se
  reperer et se deplacer* (designation sur quadrillage, palier 2) reste `non couvert`.
- **Ecart de tenue signale par la story, non corrige par cette fiche** : `MATRICE-couverture.md`,
  ligne 227, porte a l'ouverture de cette fiche une mention de couverture partielle dont la
  formulation inverse les deux moities par rapport a la source citee ci-dessus (elle attribue la
  couverture a *"se reperer et se deplacer"* et laisse *"decrire un deplacement"* non couverte,
  alors que `ARBITRAGE-PREALABLE-s32`, section 17.7, dit l'inverse). La correction de cette
  cellule est hors du perimetre de cette fiche.
- **Lien decision** : `DEC-0098`.
- **Derniere mise a jour** : 2026-09-24 (second passage - Done pedagogique VALIDE SOUS RESERVE,
  `nadia-prof` instance B ; le REFUS du premier passage est conserve).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S42-MAT-TEXTUEL`)

- Etant donne un item, quand il s'affiche, alors il demande de decrire en mots un deplacement sur
  un quadrillage (par exemple avancer, reculer, tourner a gauche, tourner a droite), en choix
  textuel, jamais une designation d'une case ou d'un point sur une image (palier 2, hors de portee
  ici).
- Etant donne le paquet livre, quand on le relit dans son entier, alors plusieurs formes de
  deplacement distinctes (avancer, reculer, tourner a gauche, tourner a droite) sont chacune en
  bonne reponse au moins une fois.
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres deplacements plausibles sur un
  quadrillage, jamais des elements sans rapport.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `MAT-CP-10` et uniquement elle, avec la mention de couverture partielle exacte portee au Done
  pedagogique : *"moitie 'decrire un deplacement' seule, en choix textuel ; la moitie 'se reperer
  et se deplacer' (designation sur quadrillage, palier 2) reste non couverte"* - direction
  verifiee contre `ARBITRAGE-PREALABLE-s32`, section 17.7.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` (instance B) le joue, alors
  elle confirme qu'aucun item ne demande une designation sur l'image et que la ligne passe de
  `non couvert` a `en cours`, pas a `couvert-valide`.

## Ce que cette story ne fait pas

Elle ne construit aucune interaction de designation sur un quadrillage (palier 2, hors de portee
au palier 1) ; elle ne fait pas passer `MAT-CP-10` a `couvert-valide` - seule la moitie textuelle
est servie ; elle ne corrige pas la mention actuellement ecrite dans `MATRICE-couverture.md`,
ligne 227, dont la formulation est inversee par rapport a la source citee - signale ci-dessus,
pas corrige ici ; elle ne fixe aucun contenu factuel precis - reste a l'auteur du paquet puis a
l'audit de `nadia-prof`.

---

## Bloc de validation pedagogique - PREMIER PASSAGE - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.
>
> **Conserve tel quel apres la correction et la levee du refus.** Un refus efface n'a aucune valeur
> de preuve : il se lit avec son motif, sa date et ce qui l'a paye. Le verdict qui fait foi
> aujourd'hui est celui du **second passage**, plus bas dans cette fiche.

- **Verdict** : **REFUSE**
- **Date** : 2026-09-24
- **Auditrice** : `nadia-prof`, instance B (`DEC-0098`).
- **Audit joue** : **oui**

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `9817b4f0`, fat jar reconstruit depuis cet arbre et lance sur le
JDK de la toolchain (21.0.10 rapporte par la machine virtuelle), sur un port dedie. **Une partie
complete de dix manches par appels HTTP reels**, avec erreurs volontaires sur sept manches sur dix
et trois erreurs de suite sur quatre d'entre elles, ecran de fin atteint - *"Tu as trouve, tout
seul : 4 / Tu as joue toutes les questions : 10"*. **Et j'ai lu les douze items du paquet.**

**Ce n'est pas un audit degrade, et les deux motifs du refus sont des faits de partie jouee : les
items en cause m'ont ete servis, dans la meme partie, aux manches 2 et 8.**

### Ce qui tient, et qu'il faut garder

Un refus n'est utile que si l'equipe sait ce qu'elle garde.

- **La forme est bonne** : trois propositions, une bonne reponse une seule fois, distracteurs
  toujours pris parmi les quatre formes de deplacement, jamais un element hors registre.
- **Les quatre formes sont chacune en bonne reponse trois fois**, le critere d'acceptation est
  tenu a la lettre.
- **Aucun item ne demande une designation sur une image** - `palier 2` hors de portee, respecte
  sans ambiguite. Je le confirme comme la story me le demandait : **aucune illustration, aucun
  texte-support, rien a designer.**
- **Les prenoms sont bien equilibres** (Zoe, Malo, Nina, Iris, Hugo, Lea, Tom, Nour, Ali, Elsa,
  Ivan, Maya), varies d'origine, sans repartition genree des roles. Rien a signaler cote biais.
- **La longueur est tenue** : deux phrases courtes, la seconde etant la question, jamais plus de
  onze mots. `LON-1` et `LON-2` sont respectees.

**Le refus ne porte sur rien de cela. Il porte sur ce que l'enfant apprend.**

### Premier motif - le paquet ne met jamais l'enfant sur un quadrillage, et travaille une competence voisine plus facile

`MAT-CP-10` dit *"se reperer et **decrire des deplacements** sur un quadrillage simple"*. La moitie
que ce lot revendique est *decrire un deplacement*.

**Douze items, zero quadrillage.** Aucun item ne pose une case de depart, aucun n'en pose une
d'arrivee, aucun ne decrit un chemin, aucun ne demande a l'enfant de tenir une position en tete.
Six items sur douze (7 a 12) ne prononcent meme pas le mot *case*.

**Ce que la tache demande reellement est une substitution de mots :**

| ce que l'enonce donne | ce que l'enfant doit rendre |
|---|---|
| *"vers la case de devant"*, *"la case juste devant lui"* | avancer |
| *"vers la case de derriere"*, *"la case juste derriere lui"* | reculer |
| *"regarde maintenant vers la gauche"* | tourner a gauche |
| *"regarde maintenant vers la droite"* | tourner a droite |

L'enonce contient deja le mot de direction ; l'enfant choisit le verbe qui le reprend. **Il n'y a
aucun raisonnement spatial a faire, a aucun moment, sur aucun des douze items.**

**La consigne servie le dit elle-meme** : *"Lis bien, puis dis quel geste est decrit."* Le geste
est decrit, l'enfant le nomme. **Decrire un deplacement, c'est produire la description ; ici
l'enfant recoit la description et rend une etiquette.** C'est l'inverse de la competence annoncee,
et c'est une competence voisine plus facile : du vocabulaire de deplacement, qui ne figure comme
telle sur aucune ligne du referentiel de CP (`squad/programme/mathematiques/competences-cp.md`,
section *Espace et geometrie*, ne porte que `MAT-CP-10` et `MAT-CP-11`).

**Sur mes poteaux, parce que la question va se poser.** Mon arbitrage du 2026-09-05
(`ARBITRAGE-PREALABLE-s32`, section 17.7) dit : *"decrire un deplacement n'est pas une designation
et se sert en choix textuel, dans un second paquet"*. **Cette phrase autorise la forme, elle ne
dispense pas du fond**, et la story le dit explicitement de son cote (*"elle ne fixe aucun contenu
factuel precis - reste a l'auteur du paquet puis a l'audit de `nadia-prof`"*). **Je n'avais ecrit
aucune barre de substance sur cette ligne avant aujourd'hui** - je l'ecris donc ici, datee, comme
une exigence neuve et non comme un durcissement retroactif : un paquet textuel de `MAT-CP-10` doit
faire representer a l'enfant au moins une position ou une orientation qui n'est pas donnee dans la
phrase qu'il lit.

**Correctif actionnable.** Poser dans l'enonce ce que l'enfant doit tenir en tete, et demander le
resultat plutot que l'etiquette. Deux formes qui restent en texte seul, sous quinze mots et deux
phrases :

> *"Zoe est sur la case 3 et avance d'une case. Ou est-elle ?"*
> (propositions : *la case 4*, *la case 2*, *la case 3*)

> *"Malo regarde la porte, puis il tourne a droite. Que regarde-t-il ?"*
> (propositions : *la fenetre*, *le tableau*, *la porte* - a condition que la place des trois soit
> posee une fois pour toutes dans la consigne du jeu)

**La premiere forme est la plus sure et suffit a elle seule** : elle demande un reperage sur une
suite de cases, ce qu'aucun item actuel ne demande, et elle reste entierement textuelle.

### Second motif, suffisant a lui seul - deux items n'ont pas de reponse deductible de leur enonce

> *"Nour fait face au mur de gauche. Quel geste fait-elle ?"* (item 8, servi a ma manche 8)
> *"Ivan fait face au mur de droite. Quel geste fait-il ?"* (item 11, servi a ma manche 2)

**Ces deux enonces decrivent un etat final, jamais un mouvement, et ne posent aucun etat initial.**
De *"Nour fait face au mur de gauche"*, on ne peut pas deduire *"elle a tourne a gauche"* : si elle
faisait deja face a ce mur, elle n'a fait aucun geste ; si elle faisait face au mur oppose, elle a
fait un demi-tour. **La reponse attendue ne suit pas de l'enonce.**

S'y ajoute une confusion de repere : *"le mur de gauche"* se lit comme la gauche de la piece, alors
que *"tourner a gauche"* est la gauche de Nour. **La distinction entre gauche relative et gauche
absolue est precisement la difficulte que le CP travaille sur cette competence.** L'item la melange
dans une seule phrase, et **c'est l'enfant qui raisonne correctement que le jeu declare en erreur.**

L'item 3 (*"Nina quitte sa case pour celle d'en face"* -> avancer) a le meme flou : *la case d'en
face* n'est pas *la case devant*. Il n'est sauve que par elimination, ses deux distracteurs etant
des rotations. **Je le signale comme symptome, il n'est pas un motif a lui seul.**

**Correctif actionnable** : poser l'etat initial, ou supprimer la formulation en etat final.
*"Nour regardait le tableau. Maintenant elle regarde le mur de gauche. Que fait-elle ?"* redevient
deductible - mais cela fait trois phrases, donc `LON-2` impose plutot de renoncer a cette forme et
de basculer sur celle du premier motif.

### Troisieme constat - aggravant, pas un motif par lui-meme

- **Les indices n'apportent rien.** Sur *"Iris va vers la case de derriere"*, l'indice repond
  *"Elle va vers l'arriere, pas vers l'avant"*. C'est la meme phrase avec d'autres mots. Sur les
  six items de rotation, l'indice est *"Il change de cote, vers la gauche"* pour un enonce qui dit
  deja *vers la gauche*. **L'enfant bloque relit sa propre question.** C'est le point sur lequel je
  refuse le plus souvent, et il tombe ici mecaniquement : quand la tache est une substitution de
  mots, l'indice ne peut rien apporter d'autre qu'une troisieme formulation.
- **Aucune progression.** Douze items = quatre reponses, chacune reformulee trois fois avec un
  prenom different. En dix manches j'ai repondu *tourner a gauche* trois fois a trois phrases qui
  ne different que par le prenom et par la tournure. Difficulte constante et tres basse.

**Ces deux constats disparaitront d'eux-memes si le premier motif est paye** : une tache qui
demande un reperage rend l'indice utile et la progression possible.

### Ce dont je reste la seule garde sur cette ligne

`PaquetCpDeplacementsQuadrillageTest` est vert, et il est correct sur ce qu'il verifie : plafond
d'items, quatre formes en bonne reponse, deux distracteurs, propositions prises dans les quatre
formes, absence d'illustration, declaration de `MAT-CP-10`. **Il compte des etiquettes. Aucun test
ne peut voir ce qui suit, et l'equipe ne doit pas lire son vert comme une couverture :**

1. **Qu'un item soit reellement pose sur un quadrillage.** Les douze items passent tous les
   garde-fous en n'en contenant aucun.
2. **Qu'un enonce determine sa reponse.** Rien d'automatique ne voit que *"fait face au mur de
   gauche"* n'implique pas *"tourner a gauche"*.
3. **Qu'un indice apporte une information que l'enonce ne portait pas.**

**Ces trois proprietes ont traverse une CI entierement verte sur ce lot.**

### Consequence sur `MATRICE-couverture.md`, ligne 227

**Sur ce refus, `MAT-CP-10` reste `non couvert`**, colonne jeu vide, et **aucune mention de
couverture partielle n'est due aujourd'hui** : rien n'est servi. La mention actuellement portee
ligne 227 - *"moitie 'se reperer et se deplacer sur un quadrillage' seule ; la moitie 'decrire un
deplacement' reste non couverte"* - **est a retirer, pas a inverser** : elle attribue une
couverture a une moitie alors que la ligne ne sert aucun jeu, et elle est de surcroit inversee par
rapport a `ARBITRAGE-PREALABLE-s32`, section 17.7, comme `camille-po` l'a signale en ouvrant cette
fiche. La tenue de la cellule reste a `camille-po`.

**Mention qui deviendra due**, et que je fixe des maintenant pour qu'elle ne soit pas a me
redemander, **le jour ou les deux motifs seront payes et ou je prononcerai le Done sur la moitie
textuelle** - reprise mot pour mot :

> **"moitie 'decrire un deplacement' seule, en choix textuel ; la moitie 'se reperer et se
> deplacer' (designation sur quadrillage, palier 2) reste non couverte"**

La ligne passera alors a **`en cours`**, **jamais a `couvert-valide`** sur cette seule moitie.

---

## Bloc de validation pedagogique - SECOND PASSAGE - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. **C'est le verdict qui fait foi.**

- **Verdict** : **VALIDE SOUS RESERVE**
- **Date** : 2026-09-24 (second passage, le meme jour que le refus)
- **Auditrice** : `nadia-prof`, instance B (`DEC-0098`).
- **Audit joue** : **oui**
- **Objet** : correction de `lea-dev` (commits `da1b0b61` et `7317b990`), reaudit **des deux motifs
  du refus** et de ce que la correction a deplace.

### Conditions de l'audit

**J'ai rejoue.** Arbre `round-two` a `d88fb20c`, **fat jar reconstruit depuis cet arbre** - pas
celui du premier passage -, lance sur le JDK de la toolchain (21.0.10 rapporte par la machine
virtuelle), sur un port dedie. **Une partie complete de dix manches par appels HTTP reels**, avec
erreurs volontaires sur six manches sur dix et deux erreurs de suite sur trois d'entre elles, ecran
de fin atteint - *"Tu as trouve, tout seul : 4 / Tu as joue toutes les questions : 10"*. **Et j'ai
relu les douze items reecrits, la consigne du catalogue et les garde-fous neufs.**

**Les deux items que j'avais nommes au refus m'ont ete servis dans cette partie** : l'ancien item 8
(Nour) a la manche 6, l'ancien item 11 (Ivan) a la manche 4. Le reaudit porte sur des items joues,
pas sur un diff lu.

### Premier motif - PAYE

Chaque enonce pose maintenant une case de depart et demande la case d'arrivee. **La bonne reponse
n'est plus jamais contenue dans la phrase que l'enfant lit** : il doit tenir une position en tete
et lui appliquer un pas.

> *"Zoe est sur la case 3 et avance d'une case. Ou est-elle ?"* - propositions *la case 4*, *la
> case 2*, *la case 3*.

**La barre que j'avais ecrite au refus est atteinte, et je la cite pour qu'on verifie que je ne la
deplace pas** : *"un paquet textuel de `MAT-CP-10` doit faire representer a l'enfant au moins une
position ou une orientation qui n'est pas donnee dans la phrase qu'il lit"*. Elle est tenue sur les
douze items. **Je leve le motif.**

**Et les distracteurs sont meilleurs que ce que j'avais demande.** Sur chaque item, l'un est la
case de depart - il attrape l'enfant qui n'a pas bouge - et l'autre est la case du mauvais cote -
il attrape celui qui s'est trompe de sens. **Les deux erreurs possibles sont chacune diagnostiquees
par une proposition.** Verifie sur les douze items, joue sur dix manches. Ce point ne m'avait pas
ete promis ; il est acquis.

### Second motif - PAYE

Les deux enonces en etat final sans etat initial ont disparu. *"Nour fait face au mur de gauche"*
est devenu *"Nour est sur la case 3 et va vers la gauche d'une case. Ou est-elle ?"*, et *"Ivan
fait face au mur de droite"* de meme. **La reponse se deduit maintenant entierement de l'enonce**,
et la confusion entre gauche relative et gauche absolue n'a plus d'objet : il n'y a plus de mur,
plus d'orientation d'un personnage, seulement une position et un pas. L'item 3 (*"celle d'en
face"*), que j'avais signale comme symptome, a disparu dans le meme geste. **Je leve le motif.**

### Troisieme constat du refus - paye pour moitie, et je corrige ma propre prevision

J'avais ecrit que les deux constats aggravants *"disparaitront d'eux-memes si le premier motif est
paye"*. **C'etait vrai pour l'un, faux pour l'autre, et je le dis plutot que de laisser passer.**

- **Les indices sont payes.** *"Reculer, c'est retirer une case. Compte bien."* donne une regle de
  comptage et non une troisieme formulation de la question. L'enfant qui echoue recoit de quoi
  travailler et **doit encore compter** : je me suis trompee deux fois de suite aux manches 5, 7
  et 8, et l'indice ne m'a jamais donne la reponse.
- **La progression n'est pas payee**, et ne pouvait pas l'etre par ce correctif. Voir la reserve 2.

### Ce que la CI garde desormais, et ce qu'elle ne garde toujours pas

`lea-dev` n'a pas seulement corrige le contenu : **elle a rendu mes deux motifs executables**, ce
qui est la bonne reponse a un refus. `PaquetCpDeplacementsQuadrillageTest` porte maintenant
`laBonneReponseNEstJamaisLEtiquetteDuGeste`, `chaqueEnonceDeclareSonEtatInitialAvantLeDeplacement`
et `lesPropositionsSontLesTroisCasesAdjacentesALaCaseDeDepartEtLaBonneReponseSuitLeSensAnnonce`.

**Le troisieme est le plus fort, et je l'ai lu avant de m'y fier** : il ne compare pas des chaines,
il **recalcule** la case d'arrivee depuis le depart et le sens annonces dans l'enonce, puis exige
que la bonne reponse soit celle-la et que les distracteurs soient les deux autres cases adjacentes.
**Un item dont la reponse ne suivrait pas de son enonce ne peut plus passer.** Mon second motif
n'est plus ma garde : il est celle de la CI.

**Ce que ces tests ne gardent pas, et l'equipe ne doit pas croire le contraire :**

1. **La longueur des enonces.** Aucun test ne mesure `LON-1`/`LON-2` sur ce paquet ni sur aucun
   autre - le seul endroit ou la conformite est affirmee est un commentaire. Voir la reserve 3.
2. **Le fait que la suite de cases soit une ligne et non un quadrillage** (reserve 1). Le garde-fou
   recalcule `depart +/- 1` : il presuppose la ligne, donc il ne pourra jamais la signaler.
3. **La progression** (reserve 2). Rien d'automatique ne voit qu'un paquet est trop facile.
4. **Que la tache soit du cote production ou reception de la competence** (reserve 1 bis).

`laBonneReponseNEstJamaisLEtiquetteDuGeste`, pris seul, est plus etroit que mon premier motif : il
verifie que la bonne reponse a la forme `la case N`, pas que la tache demande un raisonnement. **Je
l'ecris parce que le nom du test promet plus que son corps.** Le troisieme test couvre l'essentiel
de l'ecart, mais c'est lui qui garde la propriete, pas celui qui en porte le nom.

### Reserves - NEUVES, datees du 2026-09-24, second passage

**Ce ne sont pas des durcissements du refus.** Les deux motifs du refus sont leves sans reserve
attachee. Ce qui suit est ce que je vois **depuis** la correction, et que je n'avais pas ecrit
avant : le dire comme une reserve datee plutot que comme une condition retroactive est la seule
facon de ne pas faire de ma porte un caprice.

**`R-MAT-CP-10-1`. La suite de cases numerotees est une ligne, pas un quadrillage, et les indices
enoncent comme une regle generale ce qui n'est vrai que sur une ligne.** Sur une bande numerotee,
*avancer* et *aller a droite* sont la meme operation (+1), *reculer* et *aller a gauche* aussi
(-1). Les indices le disent tels quels : *"Avancer, c'est ajouter une case"* et *"Aller a droite,
c'est ajouter une case"*. **Sur un vrai quadrillage, c'est faux** : avant/arriere et gauche/droite
y sont deux axes distincts, et c'est precisement ce que le paquet de `palier 2` enseignera sur
cette meme ligne de referentiel. Un enfant qui joue les deux apprendra deux choses incompatibles.
**Correctif : borner la regle a la situation (*"Ici, aller a gauche, c'est retirer une case"*), ou
passer a deux axes des que le support le permet.**
**Je dois preciser d'ou vient ce defaut : la forme d'item est celle que j'ai moi-meme ecrite dans
mon refus, mot pour mot, y compris ses trois propositions.** `lea-dev` a implemente ce que j'avais
specifie. **Cette reserve est a ma charge, pas a la sienne**, et elle ne pouvait pas etre trouvee
autrement qu'en jouant le resultat.

**`R-MAT-CP-10-1 bis`, du meme geste.** La question posee est *"Ou est-elle ?"* : l'enfant
**interprete** un deplacement decrit et localise son resultat ; il ne **produit** jamais la
description d'un deplacement. La moitie *decrire un deplacement* est donc servie du cote reception,
pas du cote production. C'est defendable - on ne decrit pas ce qu'on ne sait pas lire - et c'est
encore une consequence de la forme que j'ai specifiee. **Je l'ecris pour que celui qui fermera
cette ligne plus tard ne croie pas la production couverte.**

**`R-MAT-CP-10-2`. Aucune progression.** Douze items, cases 1 a 5, toujours un seul pas, toujours
+1 ou -1. L'enfant qui reussit le premier item reussit les douze ; j'ai joue dix manches sans
jamais rencontrer une difficulte superieure a la premiere. Le plancher est legitime pour un premier
paquet, il ne l'est pas pour la ligne. **Correctif : faire varier le nombre de cases du
deplacement, et elargir la bande au-dela de cinq cases.**

**`R-MAT-CP-10-3`. `LON-1` est atteinte au mot pres sur six items, et rien ne la mesure.** *"Tom
est sur la case 4 et va vers la gauche d'une case. Ou est-il ?"* est au plafond de quinze mots. Le
paquet est conforme ; il n'a plus un mot de marge, et la conformite ne repose que sur un
commentaire. **Je ne demande pas de raccourcir ces items** - ils sont legaux et je ne deplace pas
la barre d'`ADR-0089`. Je signale qu'un ajout d'un seul mot au prochain sprint la franchira sans
que rien ne morde.

### Mention de couverture, a porter par `camille-po` - ligne 227

**Je confirme que le libelle que j'avais prepare au refus tient sans changement**, et je l'ai
verifie contre le paquet livre et non contre mon souvenir : aucun item ne porte d'illustration,
aucun ne demande de designer une case sur un dessin (verifie en jouant - aucun texte-support,
aucune image servie - et garde par `aucunItemNePorteDIllustration`). La moitie *palier 2* reste
entierement a faire.

`MAT-CP-10` passe de **`non couvert`** a **`en cours`**, **jamais a `couvert-valide`**, avec la
mention de couverture partielle reprise mot pour mot :

> **"moitie 'decrire un deplacement' seule, en choix textuel ; la moitie 'se reperer et se
> deplacer' (designation sur quadrillage, palier 2) reste non couverte"**

**La mention ecrite ligne 227 avant ce sprint reste a remplacer par celle-ci**, et non a conserver
a cote : elle attribuait la couverture a l'autre moitie, en sens inverse de
`ARBITRAGE-PREALABLE-s32` section 17.7, comme `camille-po` l'avait signale en ouvrant cette fiche.
La tenue de la cellule reste a `camille-po`.

**A lire avec la reserve `R-MAT-CP-10-1 bis`** : la moitie nommee dans cette mention est servie du
cote reception. La mention n'est pas amendee pour autant - elle est deja citee a l'identique par la
story, par l'en-tete du paquet et par le critere d'acceptation, et en creer une quatrieme variante
ferait a cette cellule ce qu'une mention inversee lui a deja fait.
