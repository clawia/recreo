# ADR-0081 - Une ligne de referentiel a un code alloue, chaque paquet servi declare les lignes qu'il sert, et un item n'en sert qu'une

- **Statut** : Accepte.
- **Sa regle est ecrite, verte et mise a l'epreuve dans le meme commit** -
  `bootstrap/src/test/java/com/recreo/archi/ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest.java`,
  **12 tests** : la clause sur le depot reel, le compte de `FER-2` prouve par mutation, six morsures
  mutees en memoire sur du materiau reel, une borne qui exige un vert, la non-vacuite des deux
  cotes, le cliquet de dette, et une morsure sur le jeu fautif commite.
- **Sprint** : s28. **Date** : 2026-09-03. **Auteur** : `theo-archi`.
- **Arbre mesure** : `round-two` a `c9a9e44` plus les cinq commits de pre-sprint (`ef4a636`), aucun
  `.java` modifie entre les deux. **JDK 21.0.10**, celui que la toolchain resout et que
  `maven-surefire-plugin` a lance pour les tests cites. Enveloppe : `bash outils/mvn-jdk21.sh`.
- **Encode `HIS-4`, condition de `nadia-prof` du 2026-09-03** (`ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md`,
  sections 2, 5.1 et 10). Elle ecrit la propriete - *"chaque item declare la ligne de referentiel
  qu'il sert"* - et laisse la forme a `theo-archi` : *"je dis la propriete ; la forme est a
  `theo-archi`"*. Cet `ADR` ne propose aucune variante de sa condition.
- **Applique un arbitrage de l'utilisatrice du 2026-09-03** : le marqueur est obligatoire sur
  **tous** les paquets, et la reprise des paquets existants se fait en s28. **Ce point n'a pas ete
  tranche ici** ; il est recu, chiffre et outille.
- **Prolonge** : `ADR-0007` (un seul chargeur pilote par un schema), `ADR-0013` (perimetre derive,
  garde-fou mis a l'epreuve), `ADR-0051` (un garde-fou qui n'examine aucun objet le declare),
  `ADR-0070` (aucune cle d'un paquet servi n'est ignoree de tout le depot), `ADR-0079` (une
  condition de lot se compte sur le lot, et le lot se derive des descripteurs).
- **Ne decide aucun contenu, aucune pedagogie.** Il ne dit pas si un item sert vraiment la ligne
  qu'il declare, et il ne prononce ni ouverture ni fermeture de ligne.

---

## Contexte

### Le fait, et il est de `nadia-prof`

Le 2026-09-03, avant le planning de s28, elle ecrit d'avance les trois conditions qui ferment une
ligne de referentiel - `FER-1` extension, `FER-2` densite, `FER-3` deux taches - et constate qu'elle
a *"prononce trente-cinq fermetures sans jamais ecrire ce qui fermait"*. Puis elle nomme ce qui
manque pour que ces conditions soient autre chose que son avis :

> *"`FER-1` et `FER-2` sont **calculables** - si, et seulement si, chaque item declare la ligne de
> referentiel qu'il sert. Aujourd'hui il ne la declare pas : `cm1-histoire-geographie.yaml` porte une
> seule chaine de competence qui enumere onze lignes, et l'attribution item par ligne que j'utilise
> est la mienne, faite a la main, verifiee seulement par le fait que sa somme retombe sur vingt.
> Personne d'autre que moi ne peut la refaire."*

Le defaut qu'elle a manque a s27 est le cout de cette absence : **vingt items pour onze lignes,
1,8 item par competence, six lignes servies par exactement un item**. Le chiffre etait dans le
fichier ; rien ne le calculait.

### Ce que la matrice ne peut pas porter, et pourquoi le rang non plus

Une ligne de referentiel est aujourd'hui designee de deux facons, et **aucune des deux ne fait une
identite** :

- **par son intitule en toutes lettres**, recopie dans `MATRICE-couverture.md` et dans le
  descripteur des paquets qui la servent. C'est une identite qui **casse au premier reformatage** -
  une virgule, une parenthese deplacee, et la designation ne resout plus ;
- **par son rang dans le referentiel** - *"ligne 11"*, *"ligne 6"* - forme employee par tout le
  monde, y compris par moi. Un rang **change de sens en silence a la premiere insertion** : la ligne
  qu'un paquet croyait servir n'est plus la meme, aucun fichier ne bouge, et rien ne devient rouge.
  C'est un faux vert, la famille de defaut que ce depot sanctionne depuis `ADR-0079`.

Il n'y a pas de troisieme facon **derivable** : une identite stable est ecrite, ou elle n'existe
pas. C'est la seule chose que cet `ADR` ajoute au depot, et il l'ajoute la ou la ligne est definie.

### Deux ecarts d'identite deja vivants dans la matrice, trouves en mesurant

Mesure du 2026-09-03 sur `squad/programme/MATRICE-couverture.md`, methode dans le meme paragraphe :
lecture des tables dont l'en-tete est exactement `| Matiere | Niveau | Domaine | Competence | Jeu(x)
couvrant | Mecanique | Statut |`, soit **209 lignes** (138 atteignables + 71 `non couvrable en
l'etat`), puis resolution de la colonne *Jeu(x) couvrant* contre les identifiants de
`catalogue.yaml`.

- La matrice cite **deux fois** `bandit-manchot-multiplications-divisions-cm1`, qui n'existe dans
  aucune entree du catalogue ; l'entree reelle est
  `bandit-manchot-multiplications-divisions-10-100-cm1`.
- Elle cite `cp-ranger-nombres`, qui est un **identifiant de paquet** et non de jeu ; le jeu est
  `bandit-manchot-ranger-nombres-cp`.

Aucun des deux n'est grave aujourd'hui, et **aucun n'est de mon ressort** : ils sont routes a
`camille-po`. Ils sont ecrits ici parce qu'ils sont la demonstration, sur materiau reel, de ce que
coute une designation par recopie - deux noms qui ne resolvent pas, dans le document qui pilote la
couverture, sans que rien ne le dise.

### L'arbitrage recu, et ce qu'il change

L'utilisatrice a arbitre le 2026-09-03 que **le marqueur est obligatoire sur tous les paquets** et
que **la reprise des paquets existants se fait en s28**. Cet arbitrage a une consequence directe sur
la forme, et elle a decide la conception : **une forme qui demanderait un jugement humain sur chacun
des paquets existants ne serait pas tenable a cette echelle.** Le chiffre est en section *Le cout de
reprise*.

---

## Decision

1. **Chaque ligne de referentiel porte un code**, ecrit entre crochets en tete de sa puce dans
   `squad/programme/<matiere>/competences-<niveau>.md` :

   ```markdown
   - [HG-CM1-11] Identifier les caracteristiques d'un lieu de vie et le distinguer d'un autre (espace urbain, espace periurbain, espace rural).
   ```

   Le code a trois segments : un prefixe de matiere, le niveau, un numero. **Il est alloue une fois
   et n'est jamais reutilise**, exactement comme un numero d'`ADR`. Reformuler un intitule ne change
   pas son code ; inserer une ligne prend le numero libre suivant, **sans renumeroter les autres**.
   Le code n'est donc pas un rang, meme si les premiers ont ete alloues dans l'ordre de lecture -
   ce qui fait que les references en prose deja ecrites (*"ligne 11"*) resolvent encore aujourd'hui.

2. **Chaque paquet servi declare, a sa racine, les lignes qu'il sert**, sous la cle
   `lignesDeReferentiel`, liste non vide de codes :

   ```yaml
   lignesDeReferentiel: ["QLM-CP-05"]
   ```

3. **Un paquet qui declare une seule ligne ne la repete pas sur ses items** : la declaration du
   paquet vaut pour tous. Deux sources pour une meme attribution divergent un jour, et c'est le
   cas de **quarante-deux paquets sur quarante-cinq**.

4. **Un paquet qui declare plusieurs lignes attribue chacun de ses items**, sous la cle d'item
   `ligneDeReferentiel`, **exactement une ligne par item**, prise parmi celles que le paquet
   declare. Une ligne declaree par le paquet et servie par aucun item est refusee.

5. **Un item ne sert qu'une ligne, et c'est un choix, pas une omission.** Un item peut toucher
   plusieurs competences ; il est compte sur **celle qu'il evalue**, celle ou sa bonne reponse se
   trouve - la lecture que `FER-1` donne deja : *"un item ou il est en position de bonne reponse"*.
   Motif : si un item pouvait compter sur trois lignes, **vingt items fermeraient six lignes a dix
   items chacune**, ce qui est exactement l'illusion que `FER-2` existe pour tuer. Le compte serait
   calculable et faux, ce qui est pire qu'incalculable. **Ce point est route a `nadia-prof`** : si
   elle veut qu'un item compte sur plusieurs lignes, la clause 4 change d'un mot et le compte avec.

6. **Le marqueur vit dans le schema, pas dans un commentaire**, contrairement aux marqueurs `HIS-1`
   et `COMP-3`. Il ne cree **aucune fabrique** et **aucune branche** dans le chargeur : les deux
   cles sont ignorees par `QuestionContentSourceFactory` comme toute cle qu'elle ne connait pas, et
   elles sont lues par un garde-fou - meme regime que `graphie`, `relationBonneReponse` et
   `relationsDistracteurs`, trois cles de declaration d'auteur deja servies et deja relues par des
   garde-fous (`ADR-0070`). La dette 3 de `CLAUDE.md` - un seul chargeur pilote par un schema - est
   donc intacte : **zero ligne de chargeur change par cet `ADR`**.

7. **Le garde-fou ne lit jamais la matrice**, et ne change jamais un statut de ligne. Le lien entre
   un code et la ligne de matrice qui le porte est ecrit par `camille-po` ; un garde-fou qui
   ouvrirait ou fermerait une ligne prononcerait un verdict pedagogique.

---

## Regle executable

`bootstrap/src/test/java/com/recreo/archi/ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest.java`,
**12 tests**.

**Ce n'est pas une regle ArchUnit**, et le compte de regles ArchUnit du depot est inchange : l'objet
juge est l'arbre de fichiers du depot - des referentiels en Markdown et des paquets en YAML -, pas
un graphe de types. Le mecanisme automatique qui la garde est la porte technique, `mvn -B verify`
sur le reacteur complet. Elle vit dans `bootstrap` parce que c'est le seul module qui voit a la fois
`squad/programme` et le contenu servi de tous les modules ; elle n'ecrit pas une ligne dans
`ArchitectureEnCouchesTest`.

**Population derivee des deux cotes** (`ADR-0013` obligation 1) : les paquets viennent des
repertoires de contenu des modules declares au pom agregateur, par
`AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.repertoiresDeContenu`, sans qu'une seconde
notion de *"ou vit le contenu servi"* soit ecrite ; les lignes viennent des fichiers
`competences-*.md` de `squad/programme`. **Aucune matiere, aucun niveau et aucun code n'est ecrit
dans le garde-fou** hors de son jeu fautif : le niveau d'un code est verifie contre le **nom du
fichier** qui le declare et contre le **descripteur** du paquet qui le cite.

**Ce que la regle interdit reellement** - la question que je me pose avant d'ecrire une regle :

1. un paquet servi qui ne declare aucune ligne, donc invisible a tout comptage ;
2. un code cite que **aucun referentiel ne declare** : c'est la faute la plus silencieuse de la
   famille, parce que le compte de la ligne reellement servie reste **nul** sans que rien ne le
   dise ;
3. un code **declare deux fois** : deux competences fondues dans un seul compte, donc `FER-2` tenue
   deux fois avec les items d'une seule ;
4. un code d'un **autre niveau** que le paquet qui le cite ;
5. un paquet qui declare **plusieurs** lignes sans dire, item par item, laquelle chacun sert -
   c'est-a-dire l'etat exact du paquet d'histoire-geographie de s27 ;
6. une ligne **declaree et servie par aucun item** : une couverture revendiquee que rien ne porte ;
7. la repetition, sur un item, de la ligne unique de son paquet - la seconde source qui divergera.

**Ce que la regle ne juge pas, et qui reste a `nadia-prof`** : si un item sert vraiment la ligne
qu'il declare. Une declaration menteuse passe, comme une marque `HIS-1` menteuse passe. Ce qui est
tenu est que chaque item soit attribue a une ligne et une seule, et que cette ligne resolve.

**Ce qu'elle ne verifie pas non plus, et c'est delibere : le seuil de dix items de `FER-2`.** Une
ligne vit legitimement sous dix items tant qu'elle est `en cours` ; le seuil est une **condition de
fermeture**, prononcee par `nadia-prof`. Un garde-fou qui l'imposerait rendrait un verdict
pedagogique en CI. Ce que la CI garantit est que **le compte sur lequel elle s'appuie est calculable
et fiable**, et le test le prouve par mutation : la somme des comptes vaut exactement le nombre
d'items des paquets declarants, et un item de plus vaut un point de plus sur sa ligne et sur aucune
autre. **Aucun nombre n'est fige dans le test** - un compte fige serait a remonter a chaque lot de
contenu, ce qui en ferait une corvee plutot qu'une preuve.

**Une precision que je fais contre l'`ADR` que j'ecris** : `nadia-prof` annonce que `FER-1` **et**
`FER-2` deviennent calculables. C'est vrai de `FER-2`, qui est un comptage. Ce ne l'est pas de
`FER-1`, qui demande que **chaque element enumere par le libelle** soit servi par un item ou il est
en position de bonne reponse : le marqueur donne la **population** de la ligne, il ne dit pas ce que
la parenthese du libelle enumere ni ou tombe la bonne reponse. `FER-1` reste un jugement, sur une
population desormais derivee au lieu d'etre reconstituee a la main. Le dire evite de croire couvert
ce qui ne l'est pas.

### Ce que le materiau porte aujourd'hui

**84 codes alloues dans 7 referentiels** - questionner le monde CP 16, histoire-geographie CM1 14,
francais CP 12, francais CM1 12, mathematiques CP 11, mathematiques CM1 12, langues vivantes CM1 7.
Methode : une puce de premier niveau par competence dans les `competences-*.md` des six lots de
matiere reellement servis, plus langues vivantes CM1 dont `S28-3` a besoin ce sprint ; les deux
puces de la section *Recouvrements a ne pas double-compter* de questionner le monde ne sont pas des
competences et ne portent pas de code. Les **onze** referentiels restants, ceux qu'aucun paquet
servi ne touche - arts plastiques, education musicale, EPS, EMC (CP et CM1), histoire des arts et
sciences et technologie (CM1), langues vivantes CP - **ne sont pas codes** : un code y serait vert
sur une population vide (`ADR-0051`), et le jour ou un
paquet voudra citer une de leurs lignes, il n'y aura **aucun code a citer** - donc un rouge bruyant
et immediat, jamais un compte fausse.

**3 paquets declarent, 42 sont en dette.** Les trois de questionner le monde CP ont ete repris ici
parce que leur reprise ne demande **aucun jugement** : leur descripteur recopie deja l'intitule du
referentiel a la lettre - *"un mot change, la ligne n'est plus la mienne"* -, chacun sert une seule
ligne, et `nadia-prof` a publie l'attribution (lignes 1, 3 et 5 de son referentiel). Ils font la
population non vide sans laquelle la regle ne prouverait rien.

**Le cliquet, et son plafond vaut exactement la dette** : `PLAFOND_DE_DETTE = 42`, la liste ne peut
pas grandir - un paquet neuf sans declaration est rouge -, elle ne peut pas pourrir - un nom qui ne
designe plus un paquet servi non declarant est rouge -, et reprendre un paquet oblige a le sortir de
la liste **et** a baisser le plafond dans le meme diff. C'est ma propre lecon : un cliquet dont le
plafond depasse la dette n'est pas un cliquet.

---

## Le cout de reprise des 42 paquets, et qui le paie

**C'est du cout de developpement, pas du cout d'audit.** La borne de `nadia-prof` est pleine a 5,0
sur 5 et **n'est pas touchee** par cette reprise : elle ne relit aucun item de plus. Ce qui est
engage est la capacite de `marc-dev`, `lea-dev` et `sofia-sm`, dans un sprint qui porte deja quatre
lots de contenu et le chantier du palier 2.

**Chiffre, avec sa methode.** 45 paquets servis, unique repertoire de contenu servi des cinq modules
declares au pom (`adapters/adapter-content/src/main/resources/contenu`), moins les 3 repris ici :

| Geste | Volume | Nature |
|---|---|---|
| Une ligne `lignesDeReferentiel:` a la racine de chaque paquet en dette | **42 lignes** | transcription |
| Un marqueur `ligneDeReferentiel:` par item, sur les seuls paquets multi-lignes | **20 lignes** pour le seul paquet multi-lignes connu, `cm1-histoire-geographie.yaml` | attribution |
| Un code par ligne des referentiels des matieres encore non codees | **0** pour les six lots servis, deja codes ici | - |

**La transcription n'est pas un jugement, et c'est ce qui rend la reprise tenable** : la matrice
porte deja, ligne par ligne, la colonne *Jeu(x) couvrant*, et le catalogue resout un jeu vers son
paquet. La reprise d'un paquet mono-ligne consiste donc a lire la ligne de matrice qui le cite et a
recopier son code. **L'attribution item par item ne reste due que la ou un paquet sert plusieurs
lignes**, et elle appartient a l'auteur du paquet, sous l'oeil de `nadia-prof`.

**Ce que je ne sais pas et que je n'affirme pas** : que les 41 autres paquets en dette servent tous
une seule ligne. La mesure ci-dessous le rend probable, elle ne le prouve pas - **la reprise est
precisement ce qui le decouvrira**, et c'est une bonne raison de la faire.

---

## Ce qui se passe si une ligne deja fermee ne tient pas `FER-2`

L'arbitrage de l'utilisatrice l'a accepte d'avance : une fois les 45 paquets marques, le compte
existe **retroactivement**, y compris sur les 35 lignes deja `couvert-valide`. Il devient donc
possible qu'une ligne fermee compte moins de dix items.

**Le mecanisme, ecrit avant d'etre declenche :**

1. **Le constat est mecanique et n'appartient a personne** : `itemsParLigne` rend le compte de
   chaque ligne, sur le contenu servi, sans jugement.
2. **La CI ne fait rien de ce constat.** Elle ne rougit pas, elle ne rouvre rien : `FER-2` est une
   condition de fermeture, et un garde-fou qui rouvrirait une ligne prononcerait un verdict
   pedagogique. C'est la clause 7 de la decision.
3. **Le verdict appartient a `nadia-prof`, seule.** Elle a d'ailleurs deja ecrit le regime qui
   s'applique : *"elles s'appliquent a partir de s28"*, et *"il ne revoque aucun Done"*. Une ligne
   fermee sous dix items **n'est donc pas revoquee par le seul effet du compte** ; elle devient
   visible, ce qu'elle n'etait pas.
4. **L'ecriture appartient a `camille-po`** : si `nadia-prof` prononce quelque chose, c'est la
   matrice qui le porte, avec sa date et son motif.

**La taille reelle de l'exposition, mesuree plutot que redoutee.** Methode : les 35 lignes
`couvert-valide` des tables de la matrice (en-tete exact, 209 lignes lues), colonne *Jeu(x)
couvrant* resolue vers les paquets par `catalogue.yaml`, items comptes sur les occurrences de
`- enonce:` dans le paquet.

- **Aucun jeu ne sert deux lignes fermees** : 35 lignes, 42 noms de jeux cites, aucun nom sur deux
  lignes. Cela confirme par un autre chemin ce que `nadia-prof` avait mesure chez elle - *"aucune
  n'a jamais partage son paquet avec une autre ligne"*.
- **Deux lignes tombent sous dix items dans cette mesure, et aucune des deux n'est un cas reel** :
  l'une est servie par `cp-additions-jusqu-a-10.yaml`, paquet **genere**, qui n'enumere aucun item -
  c'est l'exception que `FER-2` nomme elle-meme ; l'autre porte l'un des deux noms qui ne resolvent
  pas dans le catalogue (section *Deux ecarts d'identite*), et le paquet reel derriere ce nom
  compte 24 items.

**Donc : aucune instance connue aujourd'hui.** Ce n'est pas une garantie - c'est une mesure, avec
sa methode, sur un materiau ou l'attribution reste celle de la matrice et non celle des paquets. La
reprise est ce qui remplacera cette mesure par un compte.

---

## Ce que je route, sans le decider

| Fait | A qui | Ce qu'il en fait |
|---|---|---|
| **Le code de ligne doit apparaitre dans la matrice** pour qu'une fermeture soit tracable jusqu'a un compte. Le garde-fou ne lit pas la matrice et ne l'exige pas : sans le code, la matrice et les paquets restent relies par un intitule recopie | `camille-po` | Une colonne ou un prefixe de cellule, a sa main. **Non bloquant pour s28** |
| **La matrice cite deux noms de jeu qui ne resolvent pas** : `bandit-manchot-multiplications-divisions-cm1` (2 occurrences, l'entree reelle est `...-10-100-cm1`) et `cp-ranger-nombres` (identifiant de paquet, le jeu est `bandit-manchot-ranger-nombres-cp`) | `camille-po` | Deux corrections d'ecriture. Elles bornent aussi la fiabilite de la mesure ci-dessus |
| **Un item ne compte que sur une ligne** (clause 5). Si `FER-2` doit compter un item sur plusieurs lignes, la clause 4 change et le compte avec | `nadia-prof` | Le seul point de cet `ADR` qui interprete une de ses conditions plutot que de l'encoder |
| **`FER-1` n'est pas rendue calculable par `HIS-4`**, contrairement a ce que son arbitrage annonce : le marqueur donne la population d'une ligne, pas ce que la parenthese de son libelle enumere | `nadia-prof` | Ce que la CI tiendra, et ce qu'elle ne tiendra pas |
| **Les cinq referentiels sans paquet ne sont pas codes** ; le premier paquet qui voudra en servir une ligne sera rouge faute de code a citer | `camille-po`, developpeurs | Un rouge d'une minute, jamais un compte fausse |

---

## Consequences

**Bonnes.**

- `FER-2` cesse d'etre verifiable par une seule personne. Le compte est derive du contenu servi, par
  la CI, sur une identite qui resout.
- Le defaut de s27 - onze lignes, vingt items - devient **impossible a reproduire en silence** : un
  paquet qui declare plusieurs lignes doit attribuer chacun de ses items, et une ligne declaree sans
  item est refusee.
- L'identite d'une ligne cesse de dependre d'un intitule recopie ou d'un rang. Reformuler un libelle
  n'a plus aucun effet technique.
- Le chargeur ne change pas d'une ligne. Aucune fabrique de plus, aucune branche de plus.

**Mauvaises, et elles sont a payer.**

- **42 paquets a reprendre en s28**, dans un sprint deja plein. C'est du temps de developpement pris
  sur des lots de contenu, et l'utilisatrice l'a arbitre en le sachant.
- **Deux endroits a tenir a jour au lieu d'un** : le referentiel et le paquet. Une ligne neuve ne
  peut plus etre servie sans avoir d'abord recu un code.
- **Une declaration menteuse passe.** Le garde-fou tient la forme et l'identite, jamais le sens. Il
  peut donc **donner l'illusion** que l'attribution est verifiee alors que seule sa presence l'est -
  c'est le risque principal de cet `ADR`, et il est ecrit ici pour qu'il ne se decouvre pas plus
  tard.
- **Une seconde dette plafonnee entre au depot.** Elle est bornee, cliquetee et destinee a mourir en
  s28, mais tant qu'elle vit, 42 paquets sont hors compte.
- **Le compte retroactif peut exhumer une ligne fermee sous dix items.** Le mecanisme est ecrit
  ci-dessus ; le sort d'une telle ligne ne l'est pas, et ne m'appartient pas.

---

## Alternatives considerees

**A - Le marqueur recopie l'intitule de la ligne.** Ecartee : c'est l'identite d'aujourd'hui, et
elle casse au premier reformatage. Elle a pour elle d'echouer bruyamment plutot qu'en silence, mais
elle demanderait de recopier une phrase entiere et accentuee dans chaque paquet, et un seul
reformatage rendrait des dizaines d'items rouges d'un coup pour une raison qui n'a rien de reel.

**B - Le marqueur porte le rang de la ligne dans son referentiel** (*"ligne 11"*). Ecartee, et c'est
l'alternative dangereuse : elle ne coute rien a ecrire, elle est deja employee en prose partout, et
elle **change de sens en silence** a la premiere insertion. Elle fabrique le faux vert exact que
`FER-2` doit empecher.

**C - Le marqueur vit dans un commentaire YAML**, sur le patron de `HIS-1` et `COMP-3`. C'est ce que
`nadia-prof` suggerait - *"meme forme que le marqueur `HIS-1`"* - et c'est ce que j'aurais fait sans
l'arbitrage du 2026-09-03. **Ecartee par l'echelle** : un commentaire ne peut vivre que **sur
l'item**, donc la reprise des 42 paquets exigerait environ **900 marqueurs** ecrits a la main au lieu
de 42 lignes, sur des paquets qui, pour la plupart, servent une ligne unique et n'ont rien a repeter
huit ou vingt fois. Une cle de schema, elle, se declare **au niveau du paquet**, ce qu'un commentaire
d'item ne sait pas faire. Le prix paye est une cle de plus dans le schema ; il est nul en pieces
mobiles, `ADR-0070` garantissant deja qu'une cle mal orthographiee ne dort pas.

**D - Le garde-fou lit la matrice et en derive l'attribution.** Ecartee : la matrice est un document
de `camille-po`, en prose, de 357 ko ; faire dependre la porte technique de son formatage
transformerait une reecriture editoriale en rouge de CI. Et l'attribution y est portee par des noms
de jeux dont deux ne resolvent deja pas.

**E - Interdire purement les paquets multi-lignes.** Tentant, et **plus simple** : un paquet, une
ligne, et le compte devient trivial. Ecartee parce que ce serait une decision de contenu prise par
un architecte - `nadia-prof` et `camille-po` decoupent les paquets, pas moi - et parce qu'elle
rendrait illegal, retroactivement, un paquet deja valide.

---

## Ce qui me ferait changer d'avis

- **Si un item devait legitimement compter sur plusieurs lignes** (`nadia-prof`), la clause 4 passe
  d'un code a une liste et `itemsParLigne` compte l'item sur chacune. La forme est prete ; c'est le
  compte de `FER-2` qui change de sens, et ce n'est pas a moi de le decider.
- **Si la reprise revelait un grand nombre de paquets multi-lignes**, le geste d'attribution par item
  cesserait d'etre une exception et il faudrait se demander si le decoupage des paquets, et non le
  marqueur, est ce qui doit changer.
- **Si un referentiel devait un jour vivre ailleurs que dans `squad/programme`**, la derivation de la
  population change d'un chemin, et rien d'autre.
