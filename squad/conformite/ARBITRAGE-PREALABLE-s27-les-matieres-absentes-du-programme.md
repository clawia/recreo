# Arbitrage prealable de s27 - les matieres absentes du programme, ce qu'elles contiennent,
# ce qui en est jouable, et ce qu'elles me coutent

- **Auteur** : `nadia-prof`. **Date** : 2026-09-02. **Sprint** : s27, avant planning.
- **Branche** : `matieres-absentes`, partie de `round-two` a `bf18ee0` (cloture de s26).
- **Demande d'origine** : l'utilisatrice, 2026-09-02 - *"il n'y a pas de jeux d'anglais
  (vocabulaire), histoire / geographie, questionner le monde ajoute les au prochain sprint"*, puis,
  le meme jour, sur la forme de l'anglais - *"pour l'anglais une image ou le mot en francais et il
  faut trouver son equivalent en anglais (francais) pas de son"*.
- **Ce que ce document est** : l'enonce de ce que le programme contient, au titre de la seule
  autorite que je detiens sur ce point, plus mon prix. **Il ne prononce aucun Done** : aucun
  increment n'existe. **Il n'ecrit ni la matrice ni le backlog** (`camille-po`) et **il ne
  priorise pas** (l'utilisatrice).
- **Audit degrade, et je le declare** : je n'ai joue aucun increment ici, parce qu'il n'y en a
  aucun. Ce que j'ai regarde, ce sont les quatre referentiels existants, les quatre tableaux de la
  matrice, les vingt-cinq illustrations servies et les deux paquets illustres. C'est une
  comparaison a ce qui existe, pas une lecture de description.

---

## 1. Ce qui a ete verifie, et un ecart de compte que je corrige d'entree

**Le constat qui m'est transmis est exact** : `squad/programme/MATRICE-couverture.md` ne porte que
quatre tableaux de matiere - mathematiques CP, mathematiques CM1, francais CP, francais CM1 -, soit
47 lignes, 41 atteignables, 34 vertes, 82,9 %. Verifie par comptage des lignes de tableau du
fichier, quatre valeurs distinctes de colonne `Matiere`/`Niveau` et pas une cinquieme.

**L'ecart que je corrige, et qui n'est pas de detail** : le message qui m'ouvre cette tache parle de
*"deux matieres sur huit"*. Il n'y a pas huit matieres, et le compte n'est pas le meme aux deux
niveaux. Section 2. Je le corrige maintenant plutot que de laisser un chiffre rond circuler : c'est
exactement la famille de defaut que ce depot a deja sanctionnee plusieurs fois, un nombre qui a
l'air d'un comptage et qui n'en est pas un.

---

## 2. Les matieres du programme, par cycle

Le programme n'est pas ecrit par niveau mais par **cycle**. Le CP est la premiere annee du **cycle
2** (CP, CE1, CE2). Le CM1 est la premiere annee du **cycle 3** (CM1, CM2, sixieme). La matrice dit
"CP" et "CM1" et c'est sa convention ; le referentiel derriere est un texte de cycle.

### 2.1 Cycle 2 - huit enseignements

| # | Enseignement | Dans la matrice ? |
|---|---|---|
| 1 | Francais | oui, 12 lignes |
| 2 | Mathematiques | oui, 11 lignes |
| 3 | **Langues vivantes (etrangeres ou regionales)** | **non** |
| 4 | **Questionner le monde** | **non** |
| 5 | **Arts plastiques** | **non** |
| 6 | **Education musicale** | **non** |
| 7 | **Education physique et sportive** | **non** |
| 8 | **Enseignement moral et civique** | **non** |

### 2.2 Cycle 3 - dix enseignements

| # | Enseignement | Dans la matrice ? |
|---|---|---|
| 1 | Francais | oui, 12 lignes |
| 2 | Mathematiques | oui, 12 lignes |
| 3 | **Langues vivantes (etrangeres ou regionales)** | **non** |
| 4 | **Histoire et geographie** (un seul enseignement, horaire et programme communs) | **non** |
| 5 | **Sciences et technologie** | **non** |
| 6 | **Arts plastiques** | **non** |
| 7 | **Education musicale** | **non** |
| 8 | **Histoire des arts** | **non** |
| 9 | **Education physique et sportive** | **non** |
| 10 | **Enseignement moral et civique** | **non** |

**Histoire et geographie comptent pour un seul enseignement** - un seul chapitre de programme, un
seul horaire - meme si l'usage les nomme separement. Le compte de dix le prend deja en compte.

### 2.2 bis Amendement du 2026-09-02 - mes deux titres etaient faux, et le second est de moi seule

**Ecart trouve par `camille-po`, et il est reel.** Cette section titrait *"Cycle 3 - neuf
enseignements"* et en listait **dix**. Ma phrase de reconciliation - *"dix lignes de tableau : c'est
que l'histoire et la geographie comptent pour un seul enseignement"* - **n'expliquait rien** : la
fusion etait deja faite dans le tableau, au-dessus de la phrase. Une phrase qui a la forme d'une
reconciliation sans en etre une est pire qu'un ecart nu, parce qu'elle arrete la verification de
celui qui la lit. C'est ce qui s'est passe : l'orchestrateur a repris mon *"neuf"* sans compter ma
liste.

**Et en verifiant le sien, j'ai trouve le mien.** La section 2.1 portait exactement le meme defaut,
que personne n'avait vu : elle titrait *"sept enseignements"* et sa cinquieme ligne declarait
elle-meme *"arts plastiques **et** education musicale (deux enseignements distincts)"*. **Sept
lignes de tableau, huit enseignements.** Le defaut est identique dans les deux sections - **j'ai
compte des lignes de mon tableau au lieu de compter des enseignements** - et il n'a ete visible au
cycle 3 que parce que j'y avais fusionne en le disant. Au cycle 2, je l'ai fusionne en silence, et
le silence l'a cache.

**Ce que je tranche : dix au cycle 3, huit au cycle 2.** Les deux titres sont corriges ci-dessus,
les tableaux sont deplies, et cette note reste pour qu'on lise ce qui s'y trouvait.

**Le critere de comptage, que je dois enoncer parce que le nombre en depend.** Je compte un
enseignement **par chapitre de programme portant ses propres attendus de fin de cycle**. C'est le
seul critere qui produise des competences, donc des lignes de matrice - et la matrice compte des
competences, pas des heures.

L'autre critere possible donne un autre nombre, et je le nomme pour qu'il ne soit pas decouvert
plus tard comme une contradiction : **l'arrete des horaires groupe autrement**. Il reunit les arts
plastiques et l'education musicale sous *"enseignements artistiques"*, il adosse l'EMC a
*questionner le monde* au cycle 2 et a l'histoire-geographie au cycle 3, et **il ne donne aucun
volume propre a l'histoire des arts**. Un compte fait par les horaires rend donc **six** au cycle 2
et **sept** au cycle 3. Aucun des deux comptes n'est faux ; ils ne comptent pas la meme chose. **La
matrice doit ecrire lequel elle emploie**, faute de quoi le denominateur du produit sera un nombre
dont personne ne saura de quoi il est le compte.

**Histoire des arts reste dans mon compte, et voici ce qui me le fait dire** - c'est le candidat
que `camille-po` designe, et je ne l'ecarte pas par confort : au cycle 3, l'histoire des arts est un
**chapitre de programme distinct, avec ses propres attendus de fin de cycle**, separe des arts
plastiques et de l'education musicale. Il produira donc des lignes de competence, ce qui est le seul
critere qui compte ici. **Ce qu'il n'a pas, c'est un horaire propre** : il se prend sur les heures
d'arts, d'histoire-geographie et de francais. C'est cette absence d'horaire qui le fait paraitre
douteux, et c'est precisement pourquoi le critere devait etre enonce avant de trancher.

**Ce que cet ecart n'a pas contamine, verifie et non suppose** : la section 3 enumere deja *"arts
plastiques, education musicale, histoire des arts"* separement dans son estimation d'ordre de
grandeur. **L'estimation de cent lignes et d'un taux vers un tiers n'est pas affectee.** Le defaut
etait confine aux deux titres et a la fausse phrase de reconciliation.

**Pour mon dossier de methode** : ce sont mes cinquieme et sixieme clauses fausses en extension en
trois jours. Les quatre premieres, je les avais trouvees par la mesure. **Celle-ci, c'est un tiers
qui l'a trouvee en comptant l'objet que ma phrase decrivait** - et j'ai trouve la sixieme en portant
sa methode sur la section qu'elle n'avait pas lue. La lecon vaut dans les deux sens : je ne me
verifie pas assez, et on me croit trop.

### 2.3 Le piege est reel, et il y en a un second

**Le piege signale est exact et je le confirme.** *Questionner le monde* est une matiere de
**cycle 2** ; *histoire-geographie* et *sciences et technologie* sont deux matieres de **cycle 3**.
Ce ne sont pas deux noms de la meme chose : *questionner le monde* est **l'ancetre commun des
deux**, et il se scinde au passage au cycle 3. Consequence directe pour la matrice : il n'y aura
**jamais** de ligne "questionner le monde / CM1", ni de ligne "histoire-geographie / CP". Une
matrice qui en porterait une serait fausse.

**Le second piege, que personne n'a nomme et qui coute autant** : *histoire des arts* est un
enseignement **a part entiere du cycle 3**, distinct des arts plastiques et de l'education
musicale, et il n'existe pas au cycle 2. Le mot "arts" employe au singulier recouvre en realite
**deux enseignements au cycle 2 et trois au cycle 3**. Si la matrice ouvre une seule section
"arts", elle sous-comptera le programme sans que rien ne le signale.

### 2.4 Ce qui n'est pas une matiere et ne doit pas prendre de ligne

Les **parcours** - parcours d'education artistique et culturelle, parcours citoyen, parcours
educatif de sante - sont des dispositifs transversaux, pas des enseignements. Ils n'ont pas de
competences propres et **ne doivent recevoir aucune ligne de matrice**. Je l'ecris parce que c'est
le genre d'entree qu'on ajoute de bonne foi et qui gonfle un denominateur sans rien decrire.

### 2.5 Le compte juste

**Deux matieres couvertes sur huit au cycle 2, deux sur dix au cycle 3.** Pas "deux sur huit" matieres au total.

*Chiffres corriges le 2026-09-02 ; ils disaient sept et neuf. Motif en 2.2 bis.*

---

## 3. Ce que l'entree de ces matieres fait au taux - le fait le plus important de ce document

Je le mets avant les competences parce que c'est ce que l'utilisatrice verra en premier au prochain
sprint, et qu'il vaut mieux qu'elle le voie ici.

**Le 82,9 % d'hier est vrai sur son perimetre et faux comme mesure du produit.** Il dit : *34 des 41
lignes atteignables des deux matieres decrites*. Il ne dit rien des cinq autres matieres du cycle 2
ni des sept autres du cycle 3, parce qu'aucune ligne ne les represente. Un denominateur qui ne
contient que ce qu'on a deja fait mesure l'effort, pas la couverture.

**Arithmetique des trois matieres demandees** (methode : mes lignes des sections 4, 5 et 6, moins
celles que je classe `non couvrable en l'etat` en section 7 ; le comptage ferme est de
`camille-po`, celui-ci est le mien et je le donne comme estimation).

| | Lignes | dont `non couvrable en l'etat` | Entrent au denominateur |
|---|---|---|---|
| Questionner le monde - CP | 16 | 1 | 15 |
| Histoire-geographie - CM1 | 14 | 0 | 14 |
| Langues vivantes (anglais) - CP | 6 | 5 | 1 |
| Langues vivantes (anglais) - CM1 | 7 | 3 | 4 |
| **Total** | **43** | **9** | **34** |

> **Denominateur : 41 -> 75. Numerateur : 34, inchange. Taux : 82,9 % -> 45,3 %.**
> Avec le paquet de voyelles simples qui ferme la ligne des graphemes : **35 / 75 = 46,7 %**.

**Aucune regression du produit n'est en cause.** Pas un jeu ne se casse, pas une ligne verte ne
retombe. Le chiffre baisse parce qu'il devient vrai. C'est le meme mouvement qu'a la cloture de s25,
ou un gain de seul denominateur avait fait baisser un taux sans qu'un paquet ait echoue - sauf qu'ici
il est vingt fois plus gros.

**Et ce n'est pas le fond.** Si les autres matieres entrent un jour - sciences et technologie CM1,
EMC aux deux niveaux, arts plastiques, education musicale, histoire des arts, EPS -, le
denominateur atteignable ira vers **cent lignes environ** et le taux vers **un tiers**. Estimation,
pas comptage : je ne l'appuie sur aucune ligne ecrite, et je la donne uniquement pour que personne
ne decouvre l'ordre de grandeur dans deux sprints. L'EPS et une bonne part de l'education musicale
partiront `non couvrable en l'etat` et n'entreront donc pas au denominateur, ce qui amortit une
partie de la chute.

**Ce que je ne dis pas** : je ne dis pas qu'il faut ouvrir toutes les matieres, ni dans quel ordre.
C'est un arbitrage de l'utilisatrice. Je dis ce que le programme contient et ce que le compte
devient.

---

## 4. Questionner le monde - CP (cycle 2)

**Grain** : celui des lignes existantes - une competence par ligne, un verbe en tete, bornee par sa
parenthese. J'ai regarde comment `francais/competences-cp.md` et `mathematiques/competences-cp.md`
sont ecrits et j'ai tenu le meme grain, sans quoi les taux ne se comparent pas.

**Copie conforme** : les seize lignes ci-dessous sont copiables telles quelles dans
`squad/programme/questionner-le-monde/competences-cp.md` et dans la matrice. **Si un mot change,
la ligne modifiee n'est plus la mienne** et je le dirai a ma porte plutot qu'apres. C'est la
precaution qui manquait aux cinq lignes restees six sprints derriere `DEC-0042`.

### Le vivant, la matière et les objets

1. Distinguer les êtres vivants des objets et de la matière inerte sur des exemples du quotidien.
2. Identifier ce qui caractérise un être vivant : il naît, se nourrit, grandit, a des petits, meurt.
3. Nommer les principales parties du corps humain et les cinq sens.
4. Connaître quelques règles d'hygiène de vie (sommeil, alimentation, hygiène du corps) et leur effet sur la santé.
5. Reconnaître les états de l'eau (solide, liquide) et les changements d'état observables.
6. Identifier de quelle matière un objet est fait et trier des matériaux selon une propriété simple.
7. Identifier la fonction d'un objet technique du quotidien et nommer ses parties.
8. Reconnaître les objets numériques du quotidien et quelques règles simples de leur usage.
9. Mener une démarche d'investigation simple : observer, questionner, expérimenter, dire ce qu'on a trouvé.

### Se situer dans l'espace

10. Utiliser le vocabulaire de position pour décrire l'espace proche (devant/derrière, gauche/droite, au-dessus/au-dessous, près/loin).
11. Situer un lieu ou un objet sur un plan simple de la classe, de l'école ou du quartier.
12. Situer la France et quelques espaces étudiés sur une carte ou sur un globe.

### Se situer dans le temps

13. Nommer et ordonner les mois de l'année et les saisons.
14. Situer des événements les uns par rapport aux autres sur une frise chronologique simple (avant, après, en même temps).
15. Comparer des modes de vie d'autrefois et d'aujourd'hui à partir d'objets et d'images du quotidien.

### Explorer les organisations du monde

16. Comparer des modes de vie ici et ailleurs (habitat, alimentation, école, déplacements).

### 4.1 Deux recouvrements avec des lignes deja ecrites - a ne pas double-compter

**Je les ai cherches en relisant les lignes existantes, et il y en a deux.** Un denominateur qui
compte deux fois la meme competence sous deux matieres est faux dans le sens qui arrange, et il
serait invisible.

- **Le temps.** La matrice porte deja, en **mathematiques CP / Grandeurs et mesures** : *"Se
  reperer dans la journee, la semaine ; lire l'heure pleine sur une horloge"*, verte sur sa moitie
  *se reperer*. Le programme place aussi le reperage dans le temps dans *questionner le monde*.
  **J'ai ecrit ma ligne 13 sur les mois et les saisons, pas sur la journee et la semaine**, pour
  qu'il n'y ait aucun recouvrement. La ligne 14 (anteriorite entre evenements) n'est pas non plus
  dans la ligne de mathematiques.
- **L'espace.** La matrice porte deja, en **mathematiques CP / Espace et geometrie** : *"Se reperer
  et decrire des deplacements sur un quadrillage simple"*, `non couvert`. Ma ligne 10 est le
  **vocabulaire de position**, ma ligne 11 la **lecture d'un plan** : ce sont trois choses
  differentes - un reperage par coordonnees, un langage spatial, une representation. Elles peuvent
  coexister. **Elles ne doivent pas etre fusionnees, et elles ne doivent pas non plus etre comptees
  comme trois voies vers la meme competence.**

---

## 5. Histoire-geographie - CM1 (cycle 3)

Meme grain, meme regle de copie conforme. Destination :
`squad/programme/histoire-geographie/competences-cm1.md`.

**Une precision de fait que je dois donner** : le programme d'histoire-geographie de cycle 3 est
ecrit en **themes annuels** - trois d'histoire et trois de geographie par annee - doubles de
**competences transversales** communes au cycle (se reperer dans le temps, se reperer dans l'espace,
raisonner, comprendre un document, pratiquer differents langages, cooperer). Les lignes ci-dessous
sont les competences **du CM1**, c'est-a-dire les competences transversales appliquees aux themes de
la premiere annee du cycle. Ce sont bien les themes de CM1 et non ceux de CM2.

### Se repérer dans le temps : construire des repères historiques

1. Situer et ordonner les grandes périodes historiques sur une frise (Préhistoire, Antiquité, Moyen Âge, Temps modernes, époque contemporaine).
2. Situer un fait dans un siècle ou un millénaire et ordonner des faits les uns par rapport aux autres.

### Et avant la France ?

3. Identifier les traces d'une occupation ancienne du territoire et ce que les Gaulois, les Grecs et les Romains y ont laissé.
4. Identifier les grands mouvements et déplacements de populations du IVe au Xe siècle et leurs effets sur le territoire.

### Le temps des rois

5. Nommer et situer dans le temps quelques rois qui ont marqué la construction du royaume (Louis IX, François Ier, Henri IV, Louis XIV).
6. Associer chacun de ces rois à un fait ou à une réalisation qui le caractérise (la croisade, la Renaissance, l'édit de Nantes, Versailles).

### Le temps de la Révolution et de l'Empire

7. Situer les événements majeurs de la Révolution française (1789, l'abolition des privilèges, la Déclaration des droits de l'homme et du citoyen, la fin de la monarchie).
8. Identifier le passage de la Révolution à l'Empire et ce que Napoléon Bonaparte y change.

### Se repérer dans l'espace : construire des repères géographiques

9. Se repérer sur une carte de France et sur un planisphère (océans, continents, quelques repères physiques, quelques grandes villes).
10. Situer son lieu de vie à différentes échelles (commune, département, région, France, Europe, monde).

### Découvrir le(s) lieu(x) où j'habite, se loger, travailler, se cultiver

11. Identifier les caractéristiques d'un lieu de vie et le distinguer d'un autre (espace urbain, espace périurbain, espace rural).
12. Décrire comment on se loge, on travaille, on se cultive et on se déplace dans un espace urbain et dans un espace touristique.

### Consommer en France

13. Identifier d'où viennent l'eau, l'énergie et les aliments consommés, et les étapes de leur acheminement.

### Comprendre un document

14. Comprendre un document (texte, carte, image) : identifier sa nature, sa date et son auteur, et en extraire une information.

---

## 6. Langues vivantes (anglais) - CP et CM1

Le programme de langue vivante s'organise en **cinq activites langagieres** - ecouter et comprendre,
parler en continu, prendre part a une conversation, lire et comprendre, ecrire - plus la
**decouverte culturelle**. Cette structure n'est pas un habillage : c'est elle qui rend visible ce
qu'un produit sans son ne peut pas atteindre. **Une matrice qui n'ecrirait que les lignes jouables
mentirait par omission** et ferait passer le vocabulaire ecrit pour l'anglais tout entier. Je les
ecris toutes, et je dis ensuite lesquelles tombent.

Un point de nommage qui n'est pas le mien : la matiere du programme est **"langues vivantes"**, et
l'anglais est la langue que le produit choisit. Si la matrice ecrit "anglais" en colonne `Matiere`,
elle acte ce choix en silence. C'est une decision de produit, a l'utilisatrice, pas a moi.

### Anglais - CP (cycle 2)

1. Comprendre à l'oral les consignes de classe et des expressions très courantes.
2. Reproduire un modèle oral : un mot, une formule de politesse, une comptine.
3. Se présenter oralement et répondre à une question simple sur soi.
4. Reconnaître à l'écrit des mots familiers déjà rencontrés à l'oral, dans les champs lexicaux du cycle 2 (nombres, couleurs, animaux, corps, école, famille).
5. Copier des mots isolés et des expressions courtes.
6. Découvrir quelques aspects culturels des pays de la langue étudiée (fêtes, lieux, vie quotidienne).

### Anglais - CM1 (cycle 3)

1. Écouter et comprendre un message oral simple ou un court récit.
2. Prendre part à une conversation simple : saluer, se présenter, demander et donner une information.
3. Parler en continu : décrire, raconter ou présenter en quelques phrases.
4. Lire et comprendre des mots et des expressions familiers dans les champs lexicaux du cycle 3.
5. Lire et comprendre un texte court et simple (consigne, message, courte description).
6. Écrire des mots et des expressions mémorisés (légender une image, renseigner une fiche).
7. Découvrir quelques aspects culturels des pays de la langue étudiée.

---

## 7. Ce qui est jouable, et ce qui ne l'est pas

**Les moyens du produit, tels qu'ils existent aujourd'hui et pas tels qu'on les imagine** : la
mecanique de **choix** (`bandit-manchot`), la mecanique de **saisie**, le **palier 1 visuel** - une
image fixe, des propositions en texte, `ADR-0073`. **Aucun son.** Aucune persistance. Le palier 2
(proposition positionnee sur l'image, quadrillage) et le palier 3 (manipulation d'une collection)
**n'existent pas** : `V1`, `V2` et `V3` de mon arbitrage du 2026-08-31 les refusent au palier 1, et
rien n'a ete construit depuis.

**Un fait que j'ai verifie et qui borne tout ce qui suit** : le depot sert **deux paquets
illustres** et **vingt-cinq illustrations**, toutes des **traces geometriques** - polygones,
cercles, chemins - en un seul trait sombre, sans aucun texte. Un carre, un cadran, deux pieces. Le
produit n'a jamais dessine un objet du monde reel.

### 7.1 Questionner le monde - CP

| Ligne | Jouable ? | Motif |
|---|---|---|
| 1 vivant / non vivant | **oui**, choix | Question de trois a cinq mots, reponse par le nom. Terrain le plus simple du lot |
| 2 caracteristiques du vivant | **oui**, choix | J'ai deja ecrit *"a des petits"* et non *"se reproduit"* dans la ligne 2 : le second n'est pas un mot de CP |
| 3 parties du corps, cinq sens | **oui**, choix | Designer une partie **sur** un dessin serait du palier 2. En choix textuel : *"Avec quoi entend-on ?"* |
| 4 regles d'hygiene | **oui**, choix | **Piege de biais pose d'avance** : *"que manges-tu au petit dejeuner ?"* presuppose qu'il y en a un. `BIA-4` |
| 5 etats de l'eau | **oui**, choix | Exact et court |
| 6 matiere d'un objet | **oui**, choix ; **plus juste en illustre** | Le nom de l'objet dans l'enonce donne souvent la matiere (*"une cuillere en bois"*). Distracteurs a surveiller |
| 7 objet technique | **oui**, choix | *"A quoi sert un parapluie ?"* est du CP. Nommer les parties est plus dur, a borner |
| 8 objets numeriques | **oui**, choix | Le vocabulaire (*ecran*, *clavier*, *souris*) est accessible |
| 9 demarche d'investigation | **non - `non couvrable en l'etat`** | **Geste pratique et compte rendu libre.** Observer, experimenter et *dire ce qu'on a trouve* ne se produisent ni par un choix ni par une saisie. Meme famille que *"rediger"* (`ADR-0008`) et que *"copier"* (`DEC-0066`) : **nature irreversible**, aucune mecanique de ce produit ne l'ouvrira |
| 10 vocabulaire de position | **oui**, choix ; **bien meilleur en illustre** | Une image a deux objets et *"ou est le chat ?"* est du palier 1 : l'image porte la donnee, les propositions sont du texte. **`CON-2` interdit que l'enonce decrive la position** - c'est precisement le piege ici |
| 11 plan de la classe | **non aujourd'hui - `non couvert`** | Il faut designer un lieu **sur** le plan : c'est le **palier 2**, refuse au palier 1 par `V1`. **Ce n'est pas `non couvrable en l'etat`** : le palier 2 est constructible, il n'existe pas. La distinction change le denominateur |
| 12 carte, globe | **non aujourd'hui - `non couvert`** | Meme motif que 11. Un quiz textuel *"dans quel pays est la tour Eiffel ?"* ne sert pas *situer sur une carte* : c'est une contrefacon par description, `CON-1` |
| 13 mois, saisons | **oui**, choix | Terrain deja eprouve par `cp-semaine` |
| 14 anteriorite, frise | **oui**, choix, **sur la moitie textuelle** | *"Que fait-on avant de se coucher ?"* est textuel et bon. *Placer sur une frise* est du palier 2. **Mention exigee d'avance si la ligne bouge** : *"moitie anteriorite/posteriorite entre evenements seule ; le placement sur une frise reste non couvert"* |
| 15 autrefois / aujourd'hui | **oui**, choix ; **plus juste en illustre** | **Piege de biais** : *"autrefois"* ne veut pas dire *"moins bien"*. `BIA-3` |
| 16 ici / ailleurs | **oui**, choix, **et c'est la ligne la plus dangereuse du lot** | Une maison, une ecole, un repas *"d'ailleurs"* reduits a un item de trois propositions produisent un stereotype en une phrase. `BIA-1`, `BIA-2` |

**Bilan** : sur seize lignes, **treize jouables au choix textuel des aujourd'hui**, une `non
couvrable en l'etat`, deux `non couvert` en attente d'un palier 2. **C'est la matiere la plus
immediatement servable des trois**, et de loin.

### 7.2 Histoire-geographie - CM1

| Ligne | Jouable ? | Motif |
|---|---|---|
| 1 grandes periodes | **oui**, choix | Ordonner cinq periodes se pose en texte |
| 2 siecle, millenaire | **oui**, choix | Terrain arithmetique, exact, verifiable |
| 3 Gaulois, Grecs, Romains | **oui**, choix | |
| 4 mouvements de populations | **oui**, choix | Le vocabulaire est dur pour un CM1, a surveiller mot a mot |
| 5 les rois | **oui**, choix | |
| 6 roi / realisation | **oui**, choix | **La meilleure forme d'item de toute la matiere** : l'association a des distracteurs naturels et plausibles, ce qui est rare ici |
| 7 Revolution | **oui**, choix | |
| 8 Revolution -> Empire | **oui**, choix | |
| 9 carte, planisphere | **non aujourd'hui - `non couvert`** | Se reperer **sur** une carte est du palier 2. Un quiz de noms (*"quel est le plus grand ocean ?"*) n'est pas *se reperer* |
| 10 echelles du lieu de vie | **non aujourd'hui - `non couvert`** | Meme motif. Et le lieu de vie de l'enfant n'est pas connu du produit, qui ne conserve rien (`DEC-0028`) : la ligne se jouerait sur un lieu donne par l'item, ce qui la ramene a la ligne 11 |
| 11 types d'espaces | **oui**, choix | Un trace de paysage est hors de ce que le produit sait dessiner (7.3 bis) |
| 12 se loger, travailler | **oui**, choix | |
| 13 consommer, acheminement | **oui**, choix | Distracteurs faciles a rendre plausibles |
| 14 comprendre un document | **oui**, choix, **sur le document textuel seulement** | Un extrait court avec question est exactement la forme de `bandit-manchot-comprehension-cm1` et de `bandit-manchot-texte-narratif-cp`, deux fois validee. **Le document image ou carte reste hors de portee.** Mention exigee d'avance : *"document textuel seul ; carte et image restent non couvertes"* |

**Bilan** : **douze lignes sur quatorze jouables au choix textuel**, deux en attente d'un palier 2,
aucune `non couvrable en l'etat`. Sur le papier, c'est excellent. **Sur le fond, c'est la matiere
qui me coute le plus cher, et je dis pourquoi en 7.5.**

### 7.3 Anglais - la reponse a la question de niveau, et elle est tranchee

**Ce que l'utilisatrice a tranche, et que je ne rouvre pas** : pas de son. Stimulus = une image ou
le mot francais. Reponse = l'equivalent dans l'autre langue, en choix. C'est bien la forme du
palier 1 pour la variante illustree, et un `bandit-manchot` ordinaire pour la variante mot a mot :
**ni mecanique neuve, ni forme d'item neuve.** Confirme.

#### Au CM1 : jouable. Quatre lignes sur sept.

| Ligne | Jouable ? | Motif |
|---|---|---|
| 1 ecouter et comprendre | **non - `non couvrable en l'etat`** | Pas de son. **Contrainte fondatrice, nature reversible** au sens de `DEC-0066` : seule une decision de l'utilisatrice sur le son la rouvrirait, et elle vient de la refermer |
| 2 prendre part a une conversation | **non - `non couvrable en l'etat`** | Pas de son **et** production libre jugee par un humain (`ADR-0008`). Deux motifs, chacun suffisant |
| 3 parler en continu | **non - `non couvrable en l'etat`** | Idem |
| 4 lire et comprendre des mots familiers | **oui** - c'est exactement la ligne de l'utilisatrice | Choix, image ou mot francais en stimulus |
| 5 lire et comprendre un texte court | **oui**, choix | Meme forme que la comprehension CM1. Plus tard : il faut que le lexique de la ligne 4 existe d'abord |
| 6 ecrire des mots memorises | **oui**, saisie, **sous condition** | 7.6 |
| 7 aspects culturels | **oui**, choix, **sous conditions de biais** | 7.4 |

#### Au CP : je refuse d'avance, et voici le motif

**Refuse. Aucun paquet d'anglais ecrit ne se joue au CP sans son, et je le dis maintenant plutot
qu'a ma porte** - c'est exactement ce qui m'est demande.

Deux motifs, et chacun tient seul.

**Motif 1 - cela heurte de front la competence que nous sommes en train de construire, et j'ai la
ligne sous les yeux.** La ligne *"Associer les graphemes aux phonemes"* du francais CP est
**`en cours` a cette heure** : c'est la seule ligne ouverte de la matrice, et le sprint 27 s'ouvre
sur un paquet de voyelles simples pour la fermer. Un enfant de CP est, a cette minute precise de
sa scolarite, en train d'apprendre **une seule chose** : que les lettres qu'il voit se prononcent
selon des correspondances stables. Lui montrer *blue*, *three*, *mouse*, *eight* pendant cette
periode, c'est lui presenter des chaines dont le decodage francais est faux, et lui demander
d'inhiber la regle qu'on lui enseigne par ailleurs. Ce n'est pas un inconfort, c'est une
interference, et elle porte sur la competence la plus fondatrice du niveau.

**Motif 2 - sans son, le produit fait passer un examen qu'il n'a pas enseigne.** Le programme de
cycle 2 en langue vivante est **oral d'abord** : l'enfant entend, repete, et ne rencontre la forme
ecrite qu'ensuite, et seulement pour des mots deja connus a l'oral. La ligne du programme le dit
elle-meme - *"reconnaitre a l'ecrit des mots familiers **deja rencontres a l'oral**"*. Sans son, le
produit **ne peut pas fournir cette rencontre** et **ne peut pas savoir si elle a eu lieu**. Un
enfant de six ans a qui l'on montre trois mots anglais ecrits qu'il n'a jamais entendus ne fait
pas un exercice : il devine parmi trois. C'est le defaut que je refuse partout ailleurs.

**La premisse de l'adulte accompagnant ne sauve pas ce cas, et il faut le dire explicitement avant
que quelqu'un ne le propose.** Elle ne vaut, comme moyen de jeu, que sur les lignes ou la voix est
la competence visee - arbitrage du 2026-09-01 - et l'utilisatrice a explicitement ecarte son
extension. **Mais il y a ici un motif qui lui est propre et qui vaut au-dela de cette borne** : a
s26, l'adulte lisait du **francais**, sa langue, et le produit pouvait raisonnablement supposer une
prononciation juste. En anglais, il ne le peut pas. Un adulte qui prononce *th* comme *z*, ou qui
lit *bear* comme *beer*, **enseigne le mauvais phoneme**, et rien dans le produit ne le sait ni ne
le corrige. **Je refuse d'avance tout dispositif ou l'adulte prononcerait de l'anglais**, quelle que
soit la decision qui serait prise par ailleurs sur l'extension de la premisse. C'est une reserve
**nouvelle et datee du 2026-09-02**, pas un durcissement d'une barre ancienne.

**Ce qui reste jouable au CP en langue vivante : une ligne sur six**, la ligne 6, *decouverte
culturelle*, jouable en francais - et je demande sa mention d'avance : *"decouverte culturelle
seule, en francais ; aucune competence linguistique n'est servie"*. Une ligne verte sur cette seule
base ne doit pas laisser croire que le produit enseigne l'anglais au CP.

**Consequence de matrice** : anglais CP = 6 lignes, dont **5 `non couvrable en l'etat`** - les trois
orales, la copie au titre de `DEC-0066`, et la ligne 4 au titre du present refus, **nature
reversible : le son la rouvre**. Une seule entre au denominateur.

#### Les deux sens de la parenthese - ma reponse, et je ne choisis pas en silence

La parenthese de l'utilisatrice - *"son equivalent en anglais (francais)"* - est ambigue et je la
traite comme telle. Les trois formes possibles, toutes instruites :

| Sens | Recevable ? | Ce qu'il evalue reellement |
|---|---|---|
| **Image -> mot anglais** | **oui, et c'est celui que je recommande** | Il evalue le lien **sens -> mot anglais**, sans passer par le francais. C'est la seule des trois formes qui traite l'anglais comme une langue et non comme un code du francais. **Cout d'illustration reel**, 7.3 bis |
| **Mot francais -> mot anglais** | **oui**, second choix | Direction de production. Plus exigeante que l'inverse, aucun cout d'image, forme d'item deja servie vingt fois |
| **Mot anglais -> mot francais** | **oui, mais jamais un paquet entier** | **La plus facile des trois** : les propositions sont en francais, l'enfant lit peu d'anglais, et la reconnaissance passive surestime ce qu'il sait. **Je l'accepte comme minorite melangee dans un paquet** - au plus un item sur quatre - et **je refuse un paquet compose de ce seul sens**. Un tel paquet dirait plus que ce que l'enfant sait |

Recommandation en une phrase : **image -> anglais en priorite, mot francais -> anglais en second,
anglais -> francais jamais seul.**

### 7.3 bis - Le cout d'illustration, et pourquoi l'allegement annonce n'est que partiel

**La moitie de l'allegement est exacte** : ni mecanique neuve, ni forme d'item neuve, `ADR-0073`
suffit, `VOI-12` ne s'applique pas, aucun ecran d'adulte a juger. Cela retire du prix, et je le
retire.

**L'autre moitie est fausse, et je la corrige avant qu'elle ne serve a chiffrer un sprint.** Pour
`FIG` et `MON`, le dessin representait un **objet geometrique dont la definition est ecrite** : un
carre a quatre cotes egaux, et je verifiais les sommets declares. Pour un paquet de vocabulaire,
**le dessin est le contenu**, et sa propriete essentielle n'est pas d'etre exact mais d'etre
**reconnaissable** : si le trace peut passer pour une tasse ou pour un bol, l'enfant qui repond
*bowl* a raison et le produit le compte faux. **Une illustration ambigue est une reponse fausse**,
exactement comme un enonce faux, et c'est un motif de refus a lui seul.

**Cela m'oblige a un geste d'audit que je n'ai jamais fait.** Pour `FIG` et `MON`, `CON-1` me
demandait de **masquer l'image** et de verifier que l'item ne se repond pas sans elle. Ici il faut
**le test inverse** : masquer les propositions, regarder le dessin seul, et **nommer ce qu'il
montre** - sans connaitre la reponse attendue. Je le pose comme condition opposable, sur le modele
de `CON-1` a `CON-4`.

> **`REC-1`** - Item par item, sur la totalite du paquet et jamais sur un echantillon : propositions
> masquees, je nomme le dessin. **Si le premier nom qui me vient n'est pas la reponse attendue,
> l'item est refuse.** Pas amende : refuse.
>
> **`REC-2`** - Aucun champ lexical dont le referent n'a pas de silhouette stable. Concret et
> silhouette nette : animaux, objets d'ecole, vetements, aliments, mobilier. **Interdits a l'image**
> : les actions, les sentiments, les qualites, les liens de famille - *aunt* et *mother* n'ont pas
> de silhouette. Ceux-la se servent en **mot francais -> mot anglais**.
>
> **`REC-3` - Les couleurs ne se servent pas par l'image, et je le refuse d'avance.** Un paquet ou
> la couleur porte la reponse exclut un enfant daltonien de **chaque item**, sans recours. C'est le
> contraire exact de `FIG-3` et de `HOR-3`, que ce depot applique depuis s25. Les couleurs se
> servent en **mot francais -> mot anglais**.
>
> **`REC-4` - Les nombres ne se servent pas par l'image.** Il faudrait denombrer une collection
> dessinee, que `V2` refuse depuis le 2026-08-24. *Trois -> three*, jamais trois pommes -> *three*.
>
> **`REC-5` - Aucun mot transparent en bonne reponse.** *Table*, *orange*, *train*, *animal* se
> repondent par ressemblance avec le francais sans connaitre un mot d'anglais. C'est la contrefacon
> de `CON-1` transposee a la langue : **en effacant la connaissance de l'anglais, la reponse
> reste-t-elle trouvable ?** Si oui, l'item ne teste rien. Les transparents sont recevables **en
> distracteur**, jamais en bonne reponse.
>
> **`REC-6` - Les trois propositions sont du meme champ lexical et de longueur voisine.** Un
> distracteur pris ailleurs s'elimine sans savoir, et un mot deux fois plus long se repere sans
> lire.

**Ce que `REC-1` coute, dit franchement** : c'est une propriete du type de `CON-4` et de `COMP-2`.
Aucun automate ne la voit. Mais contrairement a `CON-1`, que j'avais price *"une demi-passe de plus
sur un paquet"* parce que c'etait un geste tres court, **celui-ci demande de regarder chaque dessin
sans savoir la reponse**, ce qui suppose de ne pas avoir lu le fichier avant. **C'est une contrainte
de procedure sur moi-meme**, et non seulement une passe de plus. C'est la premiere fois que j'en
pose une. Je la price en section 8.

**Une consequence de planning que je nomme pour qu'elle ne surprenne pas** : chaque item illustre
demande **un fichier SVG dessine a la main**. Vingt items, vingt dessins. Le depot en compte
vingt-cinq au total apres deux sprints de visuel. **Un paquet d'anglais illustre de vingt items
double a lui seul le stock d'illustrations du produit.** Ce n'est pas mon cout, c'est celui du
developpement, et c'est a `theo-archi` et a `camille-po` de le chiffrer - mais il serait malhonnete
de dire *"aucune mecanique neuve"* sans poser celui-la a cote.

### 7.4 Le biais - cinq conditions posees d'avance

Trois des lignes que j'ouvre portent un risque de stereotype **superieur a tout ce que le produit a
servi jusqu'ici**. Je pose les conditions maintenant, opposables, pour qu'aucun lot ne soit ecrit
puis jete.

> **`BIA-1`** - Sur *"ici et ailleurs"* (QLM 16) et sur *"aspects culturels"* (anglais CP 6,
> CM1 7) : **aucun item ne compare un ailleurs a un ici en position de norme.** La forme *"chez nous
> on fait X, chez eux on fait Y"* est refusee. On decrit, on ne compare pas a un centre.
>
> **`BIA-2`** - **Un pays n'est jamais represente par un seul trait.** Si un item dit qu'on mange
> telle chose dans tel pays, un autre item du meme paquet montre autre chose du meme pays. Un paquet
> qui donne un fait par pays fabrique un stereotype par pays.
>
> **`BIA-3`** - Sur *"autrefois et aujourd'hui"* (QLM 15) : **la chronologie n'est pas un progres.**
> Aucun item ne qualifie l'ancien de *difficile*, *dur*, *pauvre* ou *pas pratique*.
>
> **`BIA-4`** - Sur *"regles d'hygiene"* (QLM 4) : **aucun item n'interroge l'enfant sur sa propre
> vie** - ce qu'il mange, ou il dort, qui l'accompagne. Les questions portent sur ce qui est bon
> pour le corps. Motif : tous les enfants n'ont pas le meme foyer, et la question qui le presuppose
> exclut avant d'enseigner.
>
> **`BIA-5`** - Sur les rois et la Revolution (HG 5 a 8) : **aucun item ne fait porter un jugement
> de valeur sur un personnage.** *"Louis XIV etait-il un bon roi ?"* n'a pas de bonne reponse, et une
> mecanique de choix en fabriquerait une.

### 7.5 Ce qui me coute vraiment en histoire-geographie, et ce n'est pas la jouabilite

**Douze lignes sur quatorze sont techniquement jouables. C'est precisement ce qui m'inquiete.**

Le `bandit-manchot` sur l'histoire produit spontanement du **quiz de dates**. Techniquement
irreprochable, contenu exact, CI verte - et **l'histoire de cycle 3 n'est pas cela**. Les
competences que j'ai ecrites en 1, 2 et 14 disent *se reperer*, *ordonner*, *comprendre un
document*. Un paquet qui demanderait quinze fois *"en quelle annee ?"* servirait la memorisation de
dates et rien d'autre, et il passerait ma porte si je ne pose pas la barre maintenant.

> **`HIS-1`** - Dans un paquet d'histoire, **au plus un tiers des items** demande une date, un nom
> ou un chiffre a restituer. **Les deux autres tiers** demandent d'ordonner deux faits, d'associer
> un fait a un personnage, ou d'extraire une information d'un document. Comptage a l'item, sur la
> totalite du paquet.
>
> **`HIS-2`** - **Aucun item n'a deux reponses defendables.** C'est le risque propre a cette
> matiere : *"pourquoi la Revolution a-t-elle eclate ?"* a plusieurs reponses justes, et une
> mecanique de choix en couronnerait une. Une question d'histoire dont la reponse est discutable
> n'entre pas dans un paquet a trois propositions.
>
> **`HIS-3`** - **Chaque fait porte sa source dans le commentaire du paquet.** Motif en section 8 :
> je ne suis pas la source en histoire comme je le suis en calcul, et une date fausse est un motif
> de refus a elle seule.

### 7.6 La saisie en anglais - possible au CM1, et sa condition

La mecanique de saisie replie la casse, les signes poses sur les lettres et les blancs. **Elle ne
tolere aucune erreur de lettre** : *catt* est refuse, *hous* est refuse.

**C'est defendable au CM1 sur un lexique ferme et deja joue en choix, et pas avant.** Demander a un
enfant d'ecrire un mot anglais qu'il n'a jamais vu ecrit, dans une langue dont il ignore les
correspondances, produit un echec qui n'enseigne rien.

> **`ANG-S1`** - Un paquet de saisie en anglais ne porte que des mots **deja servis en choix dans un
> paquet valide**. Le lexique est ferme et declare.
>
> **`ANG-S2`** - Apres une tentative fausse, l'indice montre **la longueur du mot et sa premiere
> lettre**, jamais le mot. Un enfant bloque doit pouvoir avancer, et sur une langue etrangere il
> sera bloque plus souvent qu'en francais.

---

## 8. Mon cout pour s27

### 8.1 Ma borne ne bouge pas

**Quatre a cinq paquets de contenu par sprint sur une mecanique existante.** Inchangee depuis s22,
reconfirmee a s24, s25 et s26, **reconfirmee ici**. Je ne la releve pas parce que trois matieres
arrivent d'un coup.

**Une correction que je dois faire a mon propre chiffre avant de m'en servir.** A s26 j'avais oppose
un plafond de **115 items** ; le sprint en a livre **75**, et ma borne n'a pas ete le facteur
limitant - je l'ai ecrit moi-meme a la cloture. Il serait tentant d'en conclure que je peux prendre
plus. **Je ne le conclus pas** : ce qui borne mon audit n'est pas le nombre d'items mais le nombre
de **gestes distincts** qu'un paquet me demande, et un paquet de matiere neuve en demande plus, pas
moins. Le plafond d'items reste a **20 par paquet**.

### 8.2 Une matiere neuve n'est pas un paquet de plus - reponse a la question posee

**Oui, et je le dis d'avance comme demande.** Un paquet dans une matiere neuve **ne compte pas un**
contre ma borne. Trois couts s'ajoutent, et un seul se retire.

**Ce qui s'ajoute :**

1. **Je ne suis pas la source.** C'est le cout principal et il est nouveau. Sur vingt additions, je
   verifie l'arithmetique de tete en deux minutes : je **suis** le referentiel. Sur vingt items
   d'histoire, chaque date, chaque nom, chaque attribution doit etre verifie **contre une source**,
   parce que ma memoire d'un fait de CM1 n'est pas une preuve et qu'une erreur factuelle dans un
   contenu scolaire est un motif de refus a elle seule - c'est ma propre regle. Idem en anglais :
   que le mot soit le bon mot, et que les trois distracteurs soient de vrais mots anglais avec le
   sens qu'on leur prete. **Ce cout n'a jamais existe sur ce produit.**
2. **`REC-1`, quand le paquet est illustre.** Nommer chaque dessin propositions masquees, sans avoir
   lu le fichier. Geste neuf, et contrainte de procedure sur moi-meme.
3. **La plausibilite des distracteurs, dans un espace qui n'en fournit pas naturellement.** Une
   somme fausse est un distracteur naturel. En histoire, un distracteur est soit absurde - et l'item
   devient une devinette -, soit reellement defendable - et l'item a deux reponses (`HIS-2`). Il n'y
   a pas de milieu gratuit : chaque triplet se juge.

**Ce qui se retire, et je le retire honnetement** : **le referentiel, je viens de le payer.** Ce
document etablit les competences des trois matieres. Les paquets de s27 n'auront pas a le refaire,
et je ne le facturerai pas deux fois. C'est ce qui empeche le prix ci-dessous d'etre double.

**Mon tarif, ecrit avant qu'on me demande de le tenir :**

| Famille de paquet | Prix contre ma borne de 5 |
|---|---|
| Paquet dans une matiere **connue**, mecanique existante | **1** |
| Paquet dans une matiere **neuve**, textuel (choix ou saisie) | **1,5** |
| Paquet dans une matiere **neuve**, **illustre** (`REC-1`) | **2** |

Le demi-paquet n'est pas une invention de circonstance : c'est l'unite que j'ai deja employee a s25
en pricant `CON-4` a *"une demi-passe de plus sur un paquet"*.

**Une nuance que je dois a l'honnetete de la mesure** : des trois matieres, **questionner le monde
CP est celle ou mon cout de verification factuelle est le plus faible** - le vivant, le corps, les
objets, je les enseigne, j'en suis la source comme en francais. **L'histoire-geographie CM1 est la
plus chere** : la densite de dates et de noms y est maximale et je ne suis la source d'aucun. Je ne
change pas mon tarif pour autant - trois prix valent mieux qu'une echelle a six crans qu'on
rediscutera a chaque sprint - mais si un choix devait se faire sur un critere de cout, il est la.

### 8.3 Ce que s27 peut porter, en nombres

Rappel de l'existant, que je ne priorise pas et que je ne conteste pas : `camille-po` a pose comme
rang 1 le **paquet de voyelles simples**, qui ferme la ligne des graphemes. Matiere connue,
mecanique connue, motif de fermeture ecrit d'avance : **1**.

| Composition | Compte | Tient dans 5 ? |
|---|---|---|
| voyelles (1) + **deux** matieres neuves textuelles (1,5 + 1,5) | **4,0** | **oui**, marge de 1 |
| voyelles (1) + deux matieres neuves textuelles + un paquet de matiere connue (1) | **5,0** | **oui**, au plafond, zero marge |
| voyelles (1) + une matiere neuve textuelle (1,5) + **anglais illustre** (2) | **4,5** | **oui**, marge de 0,5 |
| voyelles (1) + **trois** matieres neuves textuelles (4,5) | **5,5** | **non. Je decline par ecrit** |
| voyelles (1) + histoire-geo (1,5) + QLM (1,5) + anglais **illustre** (2) | **6,0** | **non. Je decline par ecrit** |

> **Le fait d'arithmetique que je dois a `camille-po`, et il est net : s27 porte le paquet de
> voyelles simples et DEUX des trois matieres neuves. Pas trois.**

**Laquelle des trois sort n'est pas ma decision** : c'est un arbitrage de priorisation, il est a
l'utilisatrice. Ce que je dois dire, et que je dis : si le choix se fait sur le cout d'audit,
questionner le monde CP est le moins cher et l'histoire-geographie CM1 le plus cher ; si l'anglais
est pris **par l'image**, il coute a lui seul autant que deux paquets textuels, et cela doit etre su
avant de composer le lot, pas apres.

**Et si un sixieme paquet apparait - une reprise de dette, un correctif, un paquet qu'on croit
"petit" - je decline par ecrit plutot que d'echantillonner.** C'est la meme phrase qu'a s25 et a
s26, et elle vaut pareil ici.

### 8.4 Ce que s27 fermera comme lignes, dit d'avance : une seule

**C'est la lecon de s26, appliquee avant plutot qu'apres.** A l'ouverture de s26 j'avais laisse
annoncer un plafond de 35/41 ; il a fini a 34/41, parce qu'une barre pedagogique n'etait pas dans le
compte. Je ne referai pas la meme erreur dans l'autre sens.

- **Le paquet de voyelles simples ferme la ligne des graphemes** - clause 1 de ma barre du
  2026-08-10, clauses 2, 3 et 4 deja remplies. **Une ligne.**
- **Les paquets de matiere neuve n'en fermeront aucune.** Non par defiance : un premier paquet dans
  une matiere ouvre une ligne a `en cours`. **Il rencontre une barre que je n'ai jamais appliquee,
  sur une matiere ou je ne suis pas la source, avec des conditions - `REC-*`, `BIA-*`, `HIS-*` -
  qu'aucun paquet n'a encore eu a tenir.** La probabilite qu'un premier paquet les tienne toutes du
  premier coup est faible, et l'historique de ce depot le dit : le premier paquet de saisie, le
  premier paquet illustre et le premier paquet a voix ont tous demande un second passage.

> **Plafond honnete de s27 : 35 lignes vertes.** Sur un denominateur qui vaudra 41 si la matrice
> n'a pas encore integre les matieres neuves, **et 75 si elle les a integrees**. Soit **85,4 %** ou
> **46,7 %** - **le meme produit, le meme sprint, le meme travail.**
>
> **Ces deux nombres decrivent la meme realite et l'un des deux ment.** C'est le 41 qui ment, parce
> qu'il declare que le programme tient en deux matieres. **Je recommande que la matrice integre les
> lignes des matieres neuves AVANT que s27 ne livre**, pour que la baisse soit lue comme ce qu'elle
> est - une correction de mesure - et non comme la consequence d'un sprint.

---

## 9. Les proprietes dont je reste la seule garde

Dit explicitement, comme a chaque fois, pour que l'equipe ne croie pas couvert ce qui ne l'est pas.

| Propriete | Qui la tient | Automatisable ? |
|---|---|---|
| `REC-1` - le dessin se nomme sans les propositions | **moi seule** | non |
| `REC-5` - aucun mot transparent en bonne reponse | **moi**, mais une liste declaree de transparents se cherche | **partiellement** - meme forme que `CON-2` |
| `BIA-1` a `BIA-5` - stereotype, jugement de valeur, presupposition de foyer | **moi seule** | non |
| `HIS-1` - au plus un tiers d'items de restitution | **moi**, mais **le comptage devient automatisable** si le paquet declare le type de chaque item | **oui, si le contenu le declare** |
| `HIS-2` - aucun item a deux reponses defendables | **moi seule** | non |
| **L'exactitude factuelle en histoire, en geographie et en anglais** | **moi seule** | non - et c'est la propriete la plus lourde de ce document |

**`HIS-1` est la seule des six qu'un garde-fou peut mordre**, et il ne le peut que si le contenu
declare le type de chaque item. Je le signale a `theo-archi` sans prescrire la forme : c'est une
decision d'architecture, pas la mienne.

---

## 10. Ce que je route

| Fait | A qui | Ce qu'il en fait |
|---|---|---|
| **Huit** enseignements au cycle 2, **dix** au cycle 3 (compte par chapitre de programme ; l'arrete des horaires en rend six et sept - 2.2 bis). *Questionner le monde* est cycle 2, *histoire-geographie* et *sciences et technologie* cycle 3, sans equivalence entre eux (2.3) | `camille-po` | Aucune ligne "QLM / CM1", aucune ligne "HG / CP". *Histoire des arts* est un enseignement de plus, cycle 3 seul |
| Les 43 competences des trois matieres, copiables telles quelles (4, 5, 6) | `camille-po` | Referentiels et matrice. **Un mot change, la ligne n'est plus la mienne** |
| Deux recouvrements a ne pas double-compter (4.1) | `camille-po` | Le temps et l'espace existent deja en mathematiques CP |
| **Le taux passe de 82,9 % a environ 45 % sans aucune regression** (3) | `sofia-sm`, `camille-po`, l'utilisatrice | A dire avant le sprint, pas apres |
| **Anglais au CP : refuse, deux motifs ecrits** (7.3) | `camille-po`, l'utilisatrice | 5 lignes sur 6 en `non couvrable en l'etat`, dont une **reversible par le son** |
| **Aucun dispositif ou l'adulte prononcerait de l'anglais** (7.3) | `camille-po`, `sofia-sm` | Reserve **nouvelle et datee du 2026-09-02**, motif propre a la langue etrangere |
| Les trois sens de l'anglais, ma recommandation, et mon refus du paquet mono-directionnel anglais -> francais (7.3) | l'utilisatrice, via `sofia-sm` | Reponse a son ambiguite, sans que je tranche a sa place |
| `REC-1` a `REC-6`, `BIA-1` a `BIA-5`, `HIS-1` a `HIS-3`, `ANG-S1`, `ANG-S2` | `camille-po`, developpeurs | Conditions d'ecriture, opposables, posees **avant** le lot |
| **Un paquet illustre de vingt items double le stock d'illustrations du produit** (7.3 bis) | `theo-archi`, `camille-po` | Cout de developpement, pas le mien - mais il ne doit pas etre efface par *"aucune mecanique neuve"* |
| `HIS-1` est mordable si le contenu declare le type d'item (9) | `theo-archi` | Je signale, je ne prescris pas |
| **Mon tarif : 1 / 1,5 / 2. s27 porte les voyelles et DEUX matieres neuves, pas trois** (8) | `camille-po`, `sofia-sm` | Fait d'arithmetique. Le choix des deux est a l'utilisatrice |

---

## 11. Ce que ce document ne fait pas

- Il **ne prononce aucun Done pedagogique** : rien n'est construit.
- Il **n'ecrit ni la matrice ni les referentiels** : je donne les lignes, `camille-po` les porte.
- Il **ne priorise pas** : je dis que deux matieres tiennent dans s27 et pas trois, je ne dis pas
  lesquelles.
- Il **ne tranche aucun choix technique** : ni la forme du champ d'illustration, ni la declaration
  du type d'item, ni le cout de dessin. `theo-archi`.
- Il **ne rouvre pas la premisse de l'adulte accompagnant** et ne demande pas son extension. Il
  ajoute un motif, propre a la langue etrangere, pour qu'elle ne soit pas etendue **de ce cote-la**.
- Il **ne revise aucune de mes barres anterieures**. `V1`, `V2`, `V3`, `CON-1` a `CON-4`, la barre
  des graphemes du 2026-08-10 et le refus de la reconnaissance vocale sont reconduits sans une
  virgule de changement. Ce qu'il ajoute est ajoute, date, et lisible comme nouveau.
