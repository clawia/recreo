# Fiche de conformite - bandit-manchot-deplacements-quadrillage-cp

- **Mecanique** : bandit manchot.
- **Contenu** :
  `adapters/adapter-content/src/main/resources/contenu/cp-deplacements-quadrillage.yaml`
  (12 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-deplacements-quadrillage-cp`, titre servi *"Decrire un
  deplacement"*, consigne servie *"Lis bien, puis dis quel geste est decrit."*,
  `nombreDeManches: 10`.
- **Ligne declaree** : `MAT-CP-10`, **couverture partielle des l'origine** (voir ci-dessous).
- **Statut global** : **DONE PEDAGOGIQUE REFUSE** le 2026-09-24 par `nadia-prof` (instance B),
  apres avoir joue le jar servi. Deux motifs, chacun suffisant (voir le bloc de validation en bas
  de fiche). **`MAT-CP-10` reste `non couvert`** et la mention actuellement portee ligne 227 de
  `MATRICE-couverture.md` est a **retirer**, pas a inverser. Fiche ouverte par `camille-po` a
  l'ouverture du bloc de construction du sprint s42 (paquet et catalogue livres et fusionnes dans
  `round-two`, commit `00952c9a`, contenu au commit `051ba2bb`).
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
- **Derniere mise a jour** : 2026-09-24 (Done pedagogique REFUSE, `nadia-prof` instance B).

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

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

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
