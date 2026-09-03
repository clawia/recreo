# Arbitrage s28 - ce que "non jouable sans voir l'image" signifie pour les six lignes du palier 2

- **Auteur** : `nadia-prof`. **Date** : 2026-09-03. **Sprint** : s28, avant planning.
- **Branche** : `round-two` a `c9a9e44`.
- **Route par** : `theo-archi`, `ADR-0080` (commit `f251b6c`), section 7.2 et section 8, qui nomme
  le fait et son destinataire sans le trancher. Je reponds sur la portee pedagogique, et sur elle
  seule.
- **Ce que ce document ne fait pas** : il ne juge pas la conception technique d'`ADR-0080`, qui
  n'est pas ma main ; **il ne prononce aucun Done** ; il ne touche ni la matrice ni le backlog
  (`camille-po`) ; il ne priorise pas (l'utilisatrice) ; il ne traite pas les droits sur les
  reproductions d'oeuvres (`camille-po`).
- **Audit degrade, et je le declare d'entree** : **je n'ai joue aucun increment**, parce qu'aucun
  n'existe - la mecanique de designation n'a pas une ligne de code a ce jour. Ce que j'ai lu :
  `ADR-0080` en entier, les trois referentiels concernes, les six cellules de
  `squad/programme/MATRICE-couverture.md`, et mes propres barres anterieures (`V1` a `V4`,
  `QUA-1` a `QUA-5`, `CON-1` a `CON-4`, les mentions de `SEM`, de `FRA-9` et de la frise). **Rien
  de ce qui suit n'est un verdict sur un increment**, et le jour ou un ecran de designation
  existera, je le jouerai avant de dire quoi que ce soit de sa qualite.

---

## 1. Deux corrections a mes propres constats, avant de m'en servir

C'est ma deuxieme habitude et elle passe avant l'arbitrage. Les deux constats ci-dessous sont de
moi, ils ont moins de trente-six heures, et l'un des deux est faux.

### 1.1 J'ai range la ligne des echelles par assimilation, sans l'examiner

**Ce que j'ai ecrit le 2026-09-02** (arbitrage prealable de s27, section 7.2, ligne 10, sur
*"situer son lieu de vie a differentes echelles"*) : *"non aujourd'hui - `non couvert`. **Meme
motif.** Et le lieu de vie de l'enfant n'est pas connu du produit."*

**"Meme motif" est une assimilation, pas un examen.** La ligne 9 (*se reperer sur une carte et sur
un planisphere*) **nomme son support graphique dans son propre enonce** ; la ligne 10 **n'en nomme
aucun**. Je les ai rangees ensemble parce qu'elles se suivent dans le referentiel et qu'elles
parlent toutes les deux d'espace. C'est le seul motif que je peux honnetement donner, et il n'en
est pas un.

**Consequence, et elle est desagreable pour moi** : la ligne 6 du recensement d'`ADR-0080` est
derriere le palier 2 **parce que je l'y ai mise**, pas parce que la competence l'exige. C'est le
cas de la section 5, et c'est celui qui remonte.

**Je ne reecris pas l'arbitrage de s27** : un constat efface n'a aucune valeur de preuve. Le texte
du 2026-09-02 reste tel quel, cette correction est datee d'aujourd'hui, et c'est elle qui vaut.

### 1.2 Mon refus du quadrillage n'a jamais dit que la competence etait visuelle - verifie

J'ai voulu verifier que rien, chez moi, n'autorisait a ecrire *"le quadrillage est une competence
visuelle"*. **Ce n'est pas ce que j'ai ecrit**, et le texte du 2026-08-31 (arbitrage prealable de
s25, section 2.4) dit autre chose : le substitut textuel *"tu es sur la case A3, avance de deux
cases"* demande a un enfant de six ans **de construire la grille dans sa tete et d'y tenir une
position pendant qu'il la deplace** - *"ce n'est plus du reperage, c'est de la memoire de
travail"*. **C'est une barre de charge cognitive au CP, pas une propriete de la competence.** La
nuance decide toute la section 3, et je la pose avant de m'en servir. Ce refus-la est reconduit
sans un mot de changement.

---

## 2. Verdict sur l'exhaustivite des deux branches : elles ne le sont pas, et la conclusion tient

`ADR-0080`, section 7.2, tient sa demonstration en deux branches donnees pour exhaustives *"sur ce
qu'un nom accessible peut porter"* : le libelle **nomme la zone** (`CON-1` en echec), ou il **ne
nomme rien** (l'ecran n'a plus de sens). *"Il n'y a pas de troisieme branche, parce qu'un nom
accessible dit quelque chose ou ne dit rien."*

**La dichotomie finale est vraie et vide** - dire quelque chose ou ne rien dire n'exclut rien. Le
glissement est un cran plus haut : la premiere branche assimile *"dit quelque chose"* a *"nomme ce
que la zone est"*. Ce sont deux choses differentes, et l'ecart porte deux cas reels.

**Cas A - un libelle qui nomme ou est la zone, pas ce qu'elle est.** *"En haut a gauche"*,
*"case A3"*, *"l'intervalle entre les deux premiers reperes"*. Un tel nom **dit quelque chose** :
une lecture d'ecran annonce une liste ordonnee et navigable, et l'ecran n'est pas la bouillie de
la seconde branche. Et il **ne met pas `CON-1` en echec** : sur *"Montre le tableau"*, savoir
qu'une zone est en haut a gauche ne dit pas laquelle est le tableau. Ce cas ne tombe dans aucune
des deux branches.

**Cas B - un libelle qui nomme la zone, avec un enonce relationnel.** Libelles *"le tableau"*,
*"le coin lecture"*, *"la porte"*, enonce *"Montre ce qui est a cote de la porte"*. La premiere
branche conclurait `CON-1` en echec ; c'est faux ici. Lire les trois noms ne donne pas la reponse,
parce que **la donnee qui decide est le voisinage, et il est dans l'image**. L'inference *"le
libelle nomme la zone donc `CON-1` tombe"* n'est pas valide en general : elle depend de l'enonce,
pas du seul libelle.

**Ce que ces deux cas changent, et ce qu'ils ne changent pas.** Dans le cas A comme dans le cas B,
l'enfant qui ne voit pas l'image **ne peut toujours pas repondre**. La conclusion d'`ADR-0080`
tient donc entierement. **C'est la demonstration qui est incomplete, pas le resultat** - et je le
dis parce qu'une demonstration incomplete qu'on croit close est ce qui fait accepter demain une
conception qu'elle n'excluait pas.

**La demonstration reparee, en une seule branche au lieu de deux, et c'est la forme que je
demande qu'on cite desormais :**

> Le critere n'est pas ce que le libelle **nomme**, c'est s'il **porte ou non la relation que la
> question demande**. S'il la porte - *"la case a droite de la case bleue"*, ou *"le tableau"*
> quand la question demande le tableau - la reponse se trouve image effacee, et c'est `CON-1` en
> echec. S'il ne la porte pas, la relation reste dans l'image, et l'ecran n'est pas jouable sans
> la voir. **Il n'y a pas de sortie, parce qu'un libelle est attache a une zone et qu'une relation
> est entre deux zones.** Un nom ne peut pas porter ce qui n'est pas dans un nom.

C'est la formulation pedagogiquement juste, en plus d'etre exhaustive : **ce que ces six lignes
demandent est une relation** - entre un objet et sa place, entre une place et une autre, entre un
evenement et un autre. Une liste de noms n'est pas une relation.

**Consequence operationnelle, et je la nomme pour que personne ne se croie couvert.** Le
garde-fou 5 d'`ADR-0080` (*"l'enonce ne nomme pas le libelle de sa bonne zone"*, decoupage en mots
d'`ADR-0076`) attrape le recouvrement **lexical** entre l'enonce et le libelle. Il ne voit ni le
cas A, ni un libelle qui porterait la relation dans d'autres mots que ceux de l'enonce. **Il est
utile et il est partiel** ; la moitie qu'il ne voit pas reste dans ma main, elle est nommee `ZON-2`
en section 6, et elle se verifie item par item.

---

## 3. La portee pedagogique : la question binaire qu'on me pose n'a pas deux reponses, elle en a trois

On me demande de trancher entre *"la limite est celle de la competence"* et *"le produit ajoute une
barriere"*. **Je viens de refuser une dichotomie non exhaustive ; je ne vais pas en accepter une
autre dans le meme document.** Il y a un troisieme terme, il est le plus frequent des trois ici, et
le confondre avec le premier ferait ecrire au produit quelque chose de faux.

**Le premier fait, et il decide tout le reste : aucune de ces six competences n'est intrinsequement
visuelle.** L'ecole les enseigne aux eleves aveugles, et elle le fait depuis longtemps : plans en
relief, cartes en thermogonflage, grilles a picots, frises tactiles. Un enfant aveugle **se repere
sur une carte** ; il la lit avec les doigts. Ce qu'exigent ces competences n'est pas *l'oeil*,
c'est **l'acces a une representation spatiale percue comme un tout**. Ecrire *"cette competence est
visuelle"* serait une contre-verite, et une contre-verite d'un genre particulierement laid :
**elle met sur le dos du programme une limite qui est celle de notre support.**

**Le troisieme terme est donc celui-ci : la limite du medium.** `recreo` est une page web sur un
ecran. Il n'a **qu'un seul canal** pour une representation spatiale, et c'est la vue. Ce n'est ni
une propriete de la competence, ni une barriere de fabrication qu'une meilleure conception
leverait : `ADR-0080` a d'ailleurs elimine l'alternative par coordonnees **parce qu'**elle n'etait
pas jouable au clavier, ce qui est le contraire d'ajouter une barriere. **Une limite de medium se
declare ; elle ne se repare pas et elle ne s'excuse pas.**

**Le test que j'applique aux six lignes**, ecrit avant de les traiter pour qu'on puisse me
l'opposer :

1. **La competence nomme-t-elle son support graphique dans son propre enonce ?**
2. **Si oui, le support est-il l'objet de la competence, ou l'habitude de l'ecole ?** *Lire une
   carte* a la carte pour objet. *Ordonner des evenements* a l'ordre pour objet, et la frise est le
   support ou l'ecole le fait voir.

### Les six lignes classees

| # | Ligne | Support nomme dans la competence ? | Objet reel de la competence | Ou est la limite |
|---|---|---|---|---|
| 1 | maths CP - se reperer et decrire des deplacements **sur un quadrillage simple** | oui | se situer et se deplacer dans un espace quadrille | **medium** - et, au CP, ma barre de charge cognitive du 2026-08-31 en plus (1.2) |
| 2 | QLM CP - situer un lieu ou un objet **sur un plan simple** de la classe, de l'ecole ou du quartier | oui | mettre en relation l'espace reel et sa representation | **medium** |
| 3 | QLM CP - situer la France et quelques espaces etudies **sur une carte ou sur un globe** | oui | lire une representation du monde | **medium** |
| 4 | QLM CP - situer des evenements les uns par rapport aux autres **sur une frise chronologique simple** (avant, apres, en meme temps) | oui, mais | **la relation d'ordre**, que ma parenthese enonce ; la frise est le support de l'ecole | **medium sur la seule moitie "placement sur la frise"** ; l'autre moitie est deja ouverte hors palier 2 |
| 5 | HG CM1 - se reperer **sur une carte de France et sur un planisphere** | oui | lire une carte, y retrouver des reperes | **medium** |
| 6 | HG CM1 - situer son lieu de vie **a differentes echelles** (commune, departement, region, France, Europe, monde) | **non** | **l'emboitement des echelles**, une relation d'inclusion | **le produit** - et c'est moi qui l'ai posee (1.1) |

**Ce que cela signifie concretement, pour un enfant.** Sur les lignes 1, 2, 3 et 5, un enfant qui
ne voit pas l'ecran ne peut pas jouer, **et il ne pourrait pas davantage si nous concevions
autrement** : ce que la ligne demande, c'est de lire une carte, et nous n'avons rien a lui donner a
lire du bout des doigts. **Refuser de servir ces lignes pour ce motif ne rendrait service a
personne** : cela retirerait le jeu aux enfants qui voient sans rien donner a ceux qui ne voient
pas. Elles se servent, et le produit dit la verite sur ce qu'elles supposent - c'est la section 4.

Sur la ligne 4, la moitie qui compte pedagogiquement au CP - *avant, apres, en meme temps* - est
**deja jouable sans image**, la matrice le porte depuis le 2026-09-02 avec ma mention, et le palier
2 n'y ajoute que le geste de placement. C'est la ligne ou il serait le plus facile, et le plus
faux, de laisser croire que le palier 2 l'a ouverte a lui seul.

Sur la ligne 6, voir la section 5.

---

## 4. Ce que le produit doit ecrire pour ne pas mentir

**La mention exigee d'avance, dans la forme que j'emploie depuis s25** (`SEM`, `FRA-9`,
substituts CM1, frise) : une chaine que j'ecris moi-meme, que `camille-po` reprend **mot pour mot**
dans la cellule de la matrice le jour ou la ligne passe `couvert-valide`, et sans laquelle la
ligne dirait plus que ce que le jeu sert.

> **Mention `VUE`, a porter sur toute ligne qui verdit par un jeu de designation sur image :**
>
> **"couvert par un jeu de designation sur image : se joue en regardant l'ecran, ne se joue pas
> sans voir l'image. La competence, elle, n'est pas visuelle : l'ecole la sert aussi en relief, ce
> que ce produit ne sait pas faire."**

**Les deux phrases sont indissociables et je refuse qu'on ne reprenne que la premiere.** La
premiere dit ce que le jeu suppose ; la seconde empeche la deduction fausse que la premiere invite
a faire. Une mention qui dirait seulement *"competence visuelle"* serait **refusee a la porte**, au
meme titre qu'un contenu inexact : c'est une erreur de fait sur le programme, ecrite dans le
document qui pilote la couverture.

**Elle s'ajoute aux mentions de couverture partielle, elle ne les remplace pas.** Deux lignes en
portent deja une ou en porteront une :

- **Ligne 4, frise.** La mention du 2026-09-02 - *"moitie anteriorite/posteriorite entre
  evenements seule ; le placement sur une frise reste non couvert"* - **reste valable telle quelle
  tant que seule la moitie textuelle est servie**, et je ne la deplace pas. Si un paquet de
  designation sert le placement sur la frise, la ligne devient entiere et porte `VUE`.
- **Ligne 6, echelles.** Si elle est servie sans designation (section 5), elle porte :
  **"sur un lieu donne par l'item ; le produit ne connait pas le lieu de vie de l'enfant
  (`DEC-0028`), la moitie 'son lieu de vie' reste non couverte"**.
- **Ligne 1, quadrillage.** Aucune mention de couverture partielle n'est due : le plafond de
  4 x 4 cases qu'`ADR-0080` calcule (section 7.3) sert `QUA-4`, qui borne le deplacement du CP a
  trois pas. **La contrainte mord, elle ne coupe pas la ligne en deux.**

**Ce que je ne decide pas ici, et je le laisse entier** : ce que le **jeu** dit au **joueur**, et
s'il l'annonce. `ADR-0073` puis `ADR-0080` l'ont route a `camille-po`, c'est du produit, et la
mention ci-dessus ne prejuge pas de sa reponse : elle porte sur la matrice, pas sur l'ecran.

---

## 5. Le cas qui remonte a l'utilisatrice : la ligne 6, et elle seule

**Ligne 6 - histoire-geographie CM1, *"situer son lieu de vie a differentes echelles (commune,
departement, region, France, Europe, monde)"*.**

**Pourquoi elle n'est pas de la meme famille que les cinq autres.** Elle est la seule des six dont
l'enonce **ne nomme aucun support graphique**. Son objet est une **relation d'inclusion** : la
meme commune est dans un departement, qui est dans une region, qui est dans un pays, qui est dans
un continent. Cette relation se pose, se questionne et se verifie **sans une image** :
*"Le departement du Rhone se trouve dans quelle region ?"* est un item honnete, exact, de niveau
CM1, jouable au palier 1 des aujourd'hui avec la mecanique qui existe depuis le sprint 0.

**Ce que le produit ajouterait donc, si rien ne bouge** : une barriere de sa fabrication. La ligne
serait rangee derriere une mecanique qu'elle ne requiert pas, et **je suis celle qui l'y a rangee**
(1.1). La carte enrichit cette competence - voir l'emboitement plutot que le reciter - mais elle
n'en est pas la condition, et une ligne dont l'essentiel est servable ne doit pas attendre un
palier.

**Ce que je ne fais pas, et ce que je demande.** Je n'ouvre pas la ligne moi-meme : je ne l'ai pas
instruite comme j'instruis un candidat de sprint, je n'ai pose aucune condition de contenu sur
elle, et le second obstacle est reel et n'est pas de moi - **le produit ne connait pas le lieu de
vie de l'enfant** (`DEC-0028`, aucune persistance), de sorte qu'un paquet la servirait **sur un
lieu donne par l'item**, donc partiellement, avec la mention de la section 4. **La question a
poser a l'utilisatrice tient en une phrase** : accepte-t-elle qu'une competence formulee sur *son*
lieu de vie soit servie sur *un* lieu de vie, avec sa mention, ou prefere-t-elle la laisser fermee
jusqu'a ce que la carte existe ? **Ce n'est pas moi qui tranche cela** : c'est un arbitrage sur ce
que le produit a le droit de compter comme couvert, et il lui appartient. Si elle l'ouvre, la
ligne redevient un candidat ordinaire et je l'instruirai comme les autres, avec ses conditions.

**Les cinq autres lignes ne remontent pas.** Leur limite est celle du medium, aucune decision
produit ne la leve, et faire arbitrer une limite qu'on ne peut pas lever ferait perdre du temps a
tout le monde.

---

## 6. Ce que je refuse d'avance

Meme regime que les refus de contrefacon que j'ai poses le 2026-09-03 sur les referentiels du
cycle 3 - education musicale ligne 6, EPS lignes 3 et 7, histoire des arts ligne 9, sciences et
technologie ligne 19 : ecrits **avant** que le contenu existe, pour qu'aucun lot ne soit jete
apres coup.

- **`ZON-1` - Aucun texte ne remplace l'image.** Le test est celui de `CON-1`, transpose au
  palier 2 et elargi a **tout le texte servi** : en effacant l'image et en gardant l'enonce, le
  texte alternatif, l'indice, l'explication, les libelles et tout support textuel a venir, **la
  reponse reste-t-elle trouvable ?** Si oui, l'item est une contrefacon et le lot est **refuse a
  la porte**, quelle que soit la qualite du dessin.
- **`ZON-2` - Aucun libelle ne porte la relation que la question demande.** C'est le critere de la
  section 2, et il vise ce que le garde-fou 5 d'`ADR-0080` ne voit pas : un libelle qui donne la
  reponse **dans d'autres mots que ceux de l'enonce** (*"la case a droite de la case bleue"* sur
  une question de deplacement). Verification item par item, exhaustive, de moi.
- **`ZON-3` - Le texte alternatif dit ce que l'image est, jamais ou est la reponse.** `ADR-0080`
  rend `illustrationTexteAlternatif` **obligatoire** sur un item de designation : le risque est
  donc fabrique par la conception, et il faut le nommer. *"Plan de la classe vue de dessus"* est
  bon. *"Plan de la classe vue de dessus, avec le tableau en haut a gauche"* est une contrefacon,
  ecrite avec les meilleures intentions du monde. **Un texte alternatif honnete decrit le cadre,
  pas la solution.**
- **`ZON-4` - Aucune ligne ne verdit par un quiz de noms ni par des coordonnees ecrites.**
  Reconduction sans changement de `QUA-3` (2026-08-31) et de mon refus du 2026-09-02 sur les
  lignes carte et planisphere : *"dans quel pays est la tour Eiffel ?"* et *"quel est le plus
  grand ocean ?"* ne servent pas *se reperer sur une carte*, et une proposition *"A3"* lue en
  etiquette ne sert pas *se reperer sur un quadrillage*. **Ce refus est ancien ; je ne le durcis
  pas, je le rappelle parce que le palier 2 est exactement ce qui devait le lever.**
- **`ZON-5` - Aucune ligne ne verdit par reduction du geste.** Designer quelque chose n'est pas
  designer **ce que la ligne demande**. Un bouton *"avant"* et un bouton *"apres"* poses **a cote**
  d'une frise sont un choix textuel deguise en designation : la ligne 4 ne se ferme que si l'enfant
  montre **un endroit de la frise**. Idem pour une carte dont les zones designables seraient les
  entrees d'une legende plutot que des lieux. Meme famille que la contrefacon par reduction que
  j'ai refusee le 2026-09-03 en sciences et technologie ligne 19.
- **`ZON-6` - Un libelle de position ne rend pas le jeu accessible, et ne sera pas presente comme
  tel.** Le cas A de la section 2 rend l'ecran **navigable**, ce qui est bien et vaut mieux que
  *"zone 1"* ; il ne le rend pas **jouable** sans voir. Si un jour quelqu'un ecrit qu'un choix de
  libelles a rendu ces jeux accessibles aux enfants qui ne voient pas, ce sera faux, et la mention
  `VUE` existe contre cette phrase-la.

**Ces six conditions ne sont pas l'arbitrage prealable d'un paquet de designation.** Elles ne
portent que sur la contrefacon, qui est ce qu'on me demande ici. La transposition complete au
palier 2 - `CON-1` a `CON-4`, `QUA-1` a `QUA-5`, l'exactitude d'un plan et d'une carte, ce qu'un
quadrillage de CP a le droit de demander, la formulation des libelles et **ce qu'est un ordre de
lecture sur un planisphere** (`ADR-0080`, sections 7.1 et 8) - **reste due, dans un arbitrage
prealable, avant qu'un seul paquet ne soit ecrit**. Elle ne se fait pas dans le meme geste que
celui-ci.

---

## 7. Ce dont je reste la seule garde, dit pour que personne ne se croie couvert

`ADR-0080` ecrit deux garde-fous de contenu utiles - la bonne zone n'est pas predictible
(garde-fou 4), l'enonce ne nomme pas le libelle de sa bonne zone (garde-fou 5). **Aucun des deux ne
voit ce qui suit, et aucune CI verte ne le verra :**

1. **`ZON-1`, `ZON-2`, `ZON-3`** - la contrefacon par description sous toutes ses formes, y compris
   par le texte alternatif que la conception rend obligatoire.
2. **`ZON-5`** - le geste reduit : designer, mais pas ce que la ligne demande.
3. **L'accord entre les quatre nombres d'un cadre et le dessin.** `ADR-0080` le dit lui-meme et
   c'est exact : *"un contenu de designation ne se relit pas sans jouer"*. Une zone qui couvre le
   mauvais endroit du plan est une erreur de contenu invisible de toute la CI. **Je la trouverai en
   jouant, item par item, ou personne ne la trouvera.**
4. **L'exactitude de chaque plan, de chaque carte et de chaque frise.** Un fleuve mal place, une
   region mal decoupee, un siecle mal borne sont des erreurs de fait, et une erreur de fait est un
   motif de refus a elle seule.
5. **La verite de la mention `VUE`** sur les lignes qui verdiront.

---

## 8. Deux faits que je signale, parce que le compte de six n'est pas ferme

Ni l'un ni l'autre n'est un reproche a `ADR-0080` : son recensement porte sur les lignes
**bloquees** de la matrice, et il est juste sur ce perimetre. Ils vont a `camille-po`, qui tient la
matrice.

- **Une septieme ligne est concernee sans etre bloquee.** *Mathematiques CM1 - notion de fraction
  simple et reperage sur une droite numerique* est deja `couvert-valide` **avec la mention
  `FRA-9`** : *"la moitie 'reperage sur une droite numerique' reste non couverte"*. C'est
  precisement ce que `V3` a renvoye au palier 2 le 2026-08-31. Si le palier 2 sert cette moitie,
  **la ligne devient entiere et la mention `FRA-9` doit tomber** - une mention qu'on oublie de
  retirer ment autant qu'une mention qui manque.
- **Une ligne hors matrice attend le meme palier.** *Histoire des arts CM1 - se reperer dans un
  musee ou un site patrimonial*, que j'ai classee le 2026-09-03 : *"se reperer suppose de designer
  un lieu sur un plan : c'est exactement le palier 2"*. Ce referentiel n'est pas encore dans la
  matrice, donc la ligne n'a pas pu etre comptee.

---

## 9. Ce que je conclus, en trois phrases

**Le fait d'architecture est etabli et je le confirme : un jeu de designation n'est pas jouable
sans voir l'image.** Pour cinq des six lignes, cette limite est celle de notre support et non celle
de la competence - aucune de ces six competences n'est visuelle par nature, l'ecole les sert aussi
en relief - et le produit doit donc les servir en **disant la verite** sur ce qu'elles supposent,
mention `VUE` a l'appui, plutot que renoncer ou se justifier par une contre-verite sur le
programme. **Pour la sixieme, la ligne des echelles, la barriere est de notre fabrication et je
suis celle qui l'a posee** : elle remonte a l'utilisatrice, et elle seule.

**Aucun Done pedagogique n'est prononce ici**, et aucun ne le sera sur cette mecanique avant que
j'aie joue un ecran de designation, image affichee puis masquee, item par item.
