# ADR-0080 - Une designation sur image est un choix parmi des zones enumerees, le client n'envoie jamais une coordonnee, et les cinq cas d'usage tiennent dans une seule mecanique

- **Statut** : **Propose**. Aucune de ses regles n'est ecrite a la date de redaction, et cet `ADR`
  dit pour chacune **quand elle est due** et **si elle serait verte ou rouge aujourd'hui** plutot que
  de laisser le silence trancher. Une seule - la clause 11 - peut etre ecrite et verte des
  maintenant, sur une population non vide ; toutes les autres attendent le type ou le paquet
  qu'elles jugent, une regle a population vide etant ce qu'`ADR-0051` refuse.
- **Sprint** : s28. **Date** : 2026-09-03. **Auteur** : `theo-archi`.
- **Route par** : `DEC-0074`, arbitrage 2 de l'utilisatrice du 2026-09-03 - *"le palier 2 ouvre en
  s28 comme chantier de mecanique seul"*, et *"cette decision ne prejuge ni la faisabilite ni la
  forme du palier 2 : c'est le travail d'`ADR-0080`"*.
- **Realise un declencheur ecrit d'avance**, il n'en invente pas un. `ADR-0073`, decision 6 :
  *"Ce qui me ferait changer d'avis, ecrit maintenant pour que ce soit un declencheur et pas une
  revision : un palier ou l'enfant agit sur l'image [...]. La le geste de reponse change, le critere
  d'`ADR-0058` est rempli, et une forme d'item ou une mecanique de plus se justifie sur un usage
  constate."* Et, sur l'alternative de la troisieme forme d'item : *"Elle redeviendra la bonne
  reponse au palier 2."* Cet `ADR` n'a donc pas a rouvrir la question ; il a a l'executer.
- **Prolonge** : `ADR-0004` (deux contrats fermes, une mecanique par paquet), `ADR-0007` (un seul
  chargeur pilote par un schema), `ADR-0008` (le verdict est calcule cote serveur),
  `ADR-0013` (un garde-fou derive son perimetre), `ADR-0051` (pas de regle a population vide),
  `ADR-0057` (la focalisabilite est declaree par le gabarit), `ADR-0058` (une forme d'item de plus
  se justifie quand la facon dont l'enfant repond change ; un seul point de descente dans
  l'adaptateur), `ADR-0070` (aucune cle de schema n'est ignoree), `ADR-0073` (l'item declare son
  illustration par un identifiant), `ADR-0076` (designer n'est pas contenir).
- **Ce que cet `ADR` ne fait pas** : il ne prononce aucun Done, il n'ecrit aucune ligne de Java, il
  ne cree aucun paquet de contenu, il ne fixe aucune condition pedagogique et il ne ferme aucune
  ligne de matrice. Le gain de couverture arrive au plus tot a s29 (`DEC-0074`, section 4).
- **Arbre mesure** : arbre de travail de `round-two` a `c9a9e44`. **Toutes les mesures de cet `ADR`
  sont statiques** - lectures de fichiers, comptages par motif - et **aucun build n'a ete lance**.
  C'est dit ici une fois pour toutes : aucun chiffre de cet `ADR` n'est un compte de tests, et aucun
  ne suppose une machine virtuelle. La ou une mesure de compilation ou d'execution serait la bonne
  preuve, cet `ADR` la nomme comme due au lot plutot que de la remplacer par une estimation.

---

## Contexte

### 1. Les lignes bloquees, etablies par lecture de la matrice et non reprises d'un resume

**Methode** : recherche du motif `palier 2` dans `squad/programme/MATRICE-couverture.md` le
2026-09-03, puis lecture integrale des tables *Questionner le monde - CP* et
*Histoire-Geographie - CM1*, puis recherche du motif `quadrillage` dans le meme fichier. Rien n'est
recopie d'une autre source. **Six lignes**, dont il faut dire tout de suite qu'elles ne portent pas
toutes leur motif au meme endroit :

| # | Matiere, niveau | Competence | Ou le motif "palier 2" est ecrit |
|---|---|---|---|
| 1 | mathematiques CP | Se reperer et decrire des deplacements sur un quadrillage simple | **Pas dans la matrice.** La cellule dit `non couvert`, sans un mot de plus. Le motif vit dans l'arbitrage de `nadia-prof` (`V1`, `QUA-1` a `QUA-5`) et dans `DEC-0067` |
| 2 | questionner le monde CP | Situer un lieu ou un objet sur un plan simple de la classe, de l'ecole ou du quartier | Dans la cellule, avec la distinction explicite d'avec `non couvrable en l'etat` |
| 3 | questionner le monde CP | Situer la France et quelques espaces etudies sur une carte ou sur un globe | Dans la cellule, "meme motif que la ligne precedente" |
| 4 | questionner le monde CP | Situer des evenements les uns par rapport aux autres sur une frise chronologique simple | Dans la cellule. **Gain partiel** : la moitie anteriorite/posteriorite est jouable hors palier 2 |
| 5 | histoire-geographie CM1 | Se reperer sur une carte de France et sur un planisphere | Dans la cellule |
| 6 | histoire-geographie CM1 | Situer son lieu de vie a differentes echelles | Dans la cellule. **Second obstacle** : le lieu de vie de l'enfant n'est connu d'aucune session, faute de persistance (`DEC-0028`). Le palier 2 seul ne la ferme pas |

**Ce compte concorde avec celui de `DEC-0074` par un autre chemin** - `camille-po` enumere les
lignes, ce recensement cherche un motif dans le fichier puis lit les tables - et **la concordance
n'est pas circulaire** : la ligne 1 n'aurait pas ete trouvee par le motif seul, et c'est la lecture
de la table de mathematiques qui la rend. **Le fait a retenir de cet ecart** : une ligne de matrice
peut etre bloquee par une mecanique sans que sa cellule le dise. Corriger la matrice n'est pas de mon
ressort - elle est tenue par `camille-po` - et c'est signale.

**Ce que ces six lignes ne sont pas** : `non couvrable en l'etat`. `nadia-prof` a pose la
distinction elle-meme, et elle est citee dans la matrice : *"le palier 2 est constructible, il
n'existe pas"*. C'est exactement la difference entre une dette d'ingenierie et une limite de nature,
et c'est ce qui rend cet `ADR` possible.

### 2. La question d'architecture centrale : une mecanique ou plusieurs ?

Les cinq cas d'usage ont l'air heterogenes - un plan de classe, une carte, un globe, un planisphere,
une frise, un quadrillage. **Ils ne le sont pas, et le test qui le montre ne porte pas sur ce que
l'image represente mais sur ce que l'enfant fait.**

| Cas | Ce qu'il regarde | Ce qu'il fait | Ce que le serveur compare |
|---|---|---|---|
| Plan de la classe | un dessin fixe | il designe un endroit | un identifiant |
| France sur une carte ou un globe | un dessin fixe | il designe un endroit | un identifiant |
| Planisphere | un dessin fixe | il designe un endroit | un identifiant |
| Frise chronologique | un dessin fixe | il designe un intervalle | un identifiant |
| Quadrillage | un dessin fixe | il designe une case | un identifiant |

**Les cinq lignes de la colonne "ce qu'il fait" sont la meme phrase** : *l'enfant designe un element
parmi un ensemble fini d'elements, poses a des endroits que le contenu declare, sur une image fixe.*
Ce qui varie d'un cas a l'autre - le dessin, le nombre d'elements, leur decoupe, ce qu'ils
representent - est **entierement du contenu**. C'est le principe directeur de `CLAUDE.md` applique
sans exception : une meme mecanique sert n'importe quelle matiere et n'importe quel niveau sans
modification de code.

**Verdict : une seule mecanique.** Et cet `ADR` ne se contente pas de l'affirmer : il ecrit la regle
qui rend une deuxieme mecanique de designation impossible a justifier, parce que la seule chose qui
pourrait differencier un quadrillage d'un planisphere - la geometrie - est **interdite a toute
mecanique** (decision 6, clause 10).

### 3. Le piege que ce chantier tend, et qui est deja nomme dans ce depot

`ADR-0073` l'a ecrit en refusant une mecanique visuelle au palier 1 : *"Une mecanique visuelle qui
redistribuerait des propositions, tiendrait un score et rendrait un verdict serait une **copie** de
`BanditManchot`, et ce serait la dette n° 4 de `CLAUDE.md` par un autre chemin : huit classes qui ne
font que deleguer."* Ce risque est reel ici aussi, et il faut le peser avec un chiffre plutot qu'avec
une intuition.

**Mesure du prix d'une mecanique, sur les deux qui existent** (comptage de lignes des sources de
production de `domain/src/main/java/com/recreo/domain/mechanics`, 2026-09-03) : **bandit manchot 392
lignes** (`BanditManchot` 44, `BanditManchotMove` 9, `BanditManchotSession` 313, `Verdict` 26),
**saisie 473 lignes** (`Saisie` 51, `SaisieMove` 10, `ContinuerMove` 19, `SaisieSession` 295,
`NormalisationDeSaisie` 64, `SaisieVerdict` 34), **865 lignes au total**. Les deux tiennent une
boucle de partie de meme forme - tirer une manche sans repetition, compter les manches, compter le
score, compter les essais, montrer l'indice, reveler, terminer - et **elles ne la partagent pas**,
parce que la clause 3 d'`ADR-0004` interdit a une mecanique de dependre d'autre chose que du JDK et
des deux contrats.

**Cette duplication-la est deja ratifiee par ce depot**, en connaissance de cause, a `ADR-0058`.
Elle est le prix de l'isolation, et l'isolation est ce qui garantit qu'une mecanique ne peut pas
etre cassee par une autre. Une troisieme mecanique coute donc le meme ordre de grandeur : **environ
300 a 470 lignes**. Ce n'est pas la dette 4, et la difference est nette : la dette 4 etait *"huit
classes Java de 55 lignes qui ne faisaient que deleguer"* a **692 lignes de JavaScript qu'aucun test
ne couvrait**. Ici, les 300 a 470 lignes **sont** le comportement, elles vivent dans le domaine, et
elles sont testables sans navigateur.

---

## Decision

### 1. Une troisieme forme d'item, `ZoneADesigner`, et la couture scellee fait son travail

`Item permits Question, ReponseAEcrire, ZoneADesigner`.

Le critere est celui d'`ADR-0058`, et il est rempli sans discussion : **la facon dont l'enfant repond
change**. Il ne choisit pas un texte, il montre un endroit. C'est la difference que `nadia-prof`
avait ecrite en refusant le quadrillage au palier 1 : *"Un enfant qui repond "A3" en lisant trois
etiquettes n'a pas montre ou il est ; un enfant qui appuie sur une case l'a montre."*

**Composantes**, et aucune de plus :

```
ZoneADesigner(ItemId id,
              String enonce,
              String illustration,
              String illustrationTexteAlternatif,
              List<Zone> zones,
              String bonneZone,
              Optional<String> indice,
              Optional<String> explication,
              Optional<String> texteAVoixHaute)
```

Trois choix a motiver, parce qu'ils ne vont pas de soi :

- **`illustration` et `illustrationTexteAlternatif` sont obligatoires ici**, alors qu'ils sont
  facultatifs sur `Question` (`ADR-0073`). Ce n'est pas une inconsistance : sur `Question` un item
  peut ne rien montrer ; ici, un item **sans image ne veut rien dire**. L'invariant est pose dans le
  constructeur compact du record, donc a **tout** site de construction, y compris ceux qui n'ont
  jamais lu un fichier - c'est le geste exact de la clause 5 d'`ADR-0073`.
- **`bonneZone` porte l'identifiant d'une zone, jamais un libelle et jamais une coordonnee.**
  Invariants du constructeur compact : `bonneZone` est l'identifiant de l'une des `zones`, et deux
  zones d'un meme item n'ont jamais le meme identifiant. Ces deux invariants ne sont pas des
  precautions : sans le premier, un item est injouable et rien ne le dit ; sans le second, la
  designation est ambigue et le verdict depend de l'ordre de declaration.
- **Ni `texteSupport`, ni `partDesignee`.** Aucun paquet n'en demande, et les ajouter maintenant
  serait la dette 2 - une composante que chaque item devrait porter sans la concerner. Elles
  s'ajouteront le jour ou un paquet les demandera, comme les cinq composantes facultatives de
  `Question` se sont ajoutees une par une.

**Ce que la couture scellee coute, mesure et non estime.** Recherche du motif
`case Question|case ReponseAEcrire` sur les sources Java du depot, `/target/` exclu, 2026-09-03 :
**8 expressions `switch` exhaustives sur `Item`, dans 5 fichiers** - `BanditManchotSession` (1) et
`SaisieSession` (1) en production, `ClassementDesItems` (3),
`AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest` (2) et
`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` (1) dans les tests. **La troisieme permission
arrete donc le compilateur a 8 endroits exacts, et nulle part ailleurs.** C'est le nombre a inscrire
au planning, et c'est aussi ce que la couture est censee faire : `ADR-0058` l'a ecrit pour la
deuxieme permission, et le fait s'est verifie a s24.

**Les six sites de test qui s'arretent ne sont pas une corvee** : trois d'entre eux sont des
garde-fous de contenu qui devront decider, item par item, ce qu'ils font d'un item de designation.
`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` en particulier : l'identite d'ecran d'un item
de designation comprend son illustration, son enonce **et l'ensemble de ses zones** - deux items qui
montrent le meme plan et posent la meme question sont le meme ecran pour l'enfant. Ce choix-la
appartient au lot, pas a cet `ADR` ; ce qui appartient a cet `ADR` est de dire que le compilateur
oblige a le faire, plutot que de laisser une branche vide passer inapercue.

### 2. Deux records de contenu, et la geometrie vit dans le sien

```
Zone(String id, String libelle, CadreDeZone cadre)
CadreDeZone(int x, int y, int largeur, int hauteur)
```

`CadreDeZone` est un rectangle **en pourcentage de la boite de l'image**, en entiers. Invariants du
constructeur compact : `x` et `y` valent au moins 0, `largeur` et `hauteur` valent au moins 1,
`x + largeur` et `y + hauteur` ne depassent pas 100.

**Pourquoi un pourcentage, et pas les unites du dessin.** Les dessins servis declarent un
`viewBox` et une taille en unites utilisateur (releve du 2026-09-03 sur
`adapters/adapter-web/src/main/resources/static/illustrations/`, 24 fichiers SVG, le plus lourd
2,7 ko). Le bouton d'une zone n'est pas un element du dessin : c'est un element HTML pose
**par-dessus la boite de l'image**, et la seule unite qui vaille pour lui est une fraction de cette
boite. Le pourcentage n'a besoin de connaitre ni le format du fichier, ni sa taille declaree, ni la
feuille de style. **Consequence assumee** : le contenu et le dessin doivent s'accorder, et aucun
automate ne le verifiera entierement - `nadia-prof` a deja ecrit pour elle-meme que *"le jour ou une
image existe, je suis le seul garde-fou de son exactitude"*. Ce que les automates peuvent tenir est
ecrit en decision 6, et c'est plus qu'on ne croit.

**Pourquoi `Zone` porte un `libelle`.** C'est le nom accessible du bouton, celui qu'une lecture
d'ecran annonce. Il n'est pas decoratif : sans lui, le bouton n'a aucun nom, et un bouton sans nom
est un defaut d'accessibilite dur, pas une finition. **Ce que ce libelle porte reellement est une
question pedagogique, routee et non tranchee ici** (decision 7).

**Pourquoi trois records et pas quatre champs sur `Zone`.** `CadreDeZone` est separe de `Zone` pour
une raison unique et entierement architecturale : c'est ce qui permet a la clause 10 d'exister. Une
mecanique peut transporter une `Zone` sans jamais toucher a un `CadreDeZone`, et ArchUnit sait dire
la difference. Fondues en un seul record, les deux notions seraient indissociables et la regle la
plus importante de cet `ADR` deviendrait inecrivable. **C'est une piece mobile de plus qui achete une
regle executable ; c'est le seul motif que j'accepte pour une piece mobile de plus.**

### 3. Une troisieme mecanique, `designation`, et le motif de ne pas etendre l'existante

`com.recreo.domain.mechanics.designation` : `Designation`, `DesignationMove(String zoneDesignee)`,
`DesignationSession`, `DesignationVerdict`.

**Pourquoi pas une branche dans `BanditManchot`.** Le bandit manchot **melange** ses propositions et
les distribue en colonnes ; c'est son comportement propre, celui qui lui donne son nom, et c'est
exactement ce qu'une designation ne doit **jamais** faire : la place d'une zone est une donnee du
contenu, pas un tirage. Une mecanique qui porterait une branche *"si cet item a des zones, ne pas
melanger"* serait une mecanique qui lit la forme du contenu pour changer son propre comportement -
la chose meme qu'`ADR-0004` ferme, dans les deux sens, depuis le sprint 0. Le `switch` exhaustif de
`BanditManchotSession.questionDe` refuse deja un `ReponseAEcrire` en nommant les deux ; il refusera
un `ZoneADesigner` de la meme facon, et c'est le bon comportement.

**Pourquoi pas un moteur partage entre les trois.** Interdit, et la clause 8 de
`DecouplageMecaniqueContenuTest` le mord depuis s24, y compris sous sa forme la plus tentante - une
interface de contrat portant une methode par defaut. Ce refus a un prix, il est chiffre au contexte
(865 lignes pour deux mecaniques), et il est deja paye deux fois.

**Ce que la mecanique de designation fait, et rien de plus** : elle tire une manche sans repetition,
elle expose l'enonce, l'illustration, son texte alternatif et **la liste des zones dans l'ordre ou le
contenu les declare**, elle recoit un identifiant de zone, elle le compare a `bonneZone`, elle compte
les essais, elle montre l'indice quand la boucle de partie le prevoit, elle revele apres trois essais
et elle termine. **Elle ne melange rien, elle ne calcule aucune position, elle ne connait aucune
unite.**

**Une zone inconnue est traitee comme n'importe quelle designation fausse**, sans chemin d'erreur
neuf. C'est ce que `BanditManchotSession.jouer` fait deja d'une proposition inconnue - elle n'est pas
la bonne reponse, elle consomme un essai - et un identifiant inconnu ne peut venir que d'une requete
forgee, jamais de l'ecran servi. Ecrire un traitement particulier serait ecrire du code pour un cas
que le produit ne rencontre pas.

### 4. Ou vit le verdict : le client envoie un identifiant, le serveur decide tout

C'est la contrainte non negociable de ce chantier, et elle se dit en une phrase :
**le navigateur ne fait aucun calcul de position, parce qu'il n'y a aucune position a calculer.**

**Ce que le client envoie, exactement.** Un champ de formulaire, encode comme n'importe quel autre
coup de ce produit, emis par un vrai bouton de soumission :

```
<button type="submit" name="zoneDesignee" value="tableau" class="zone">le tableau</button>
```

Rien d'autre. Pas de coordonnee, pas de `<map>`, pas de `<area>`, pas d'ecouteur de clic, pas de
`getBoundingClientRect`, pas de JSON. **Le navigateur n'a rien a decider parce que le serveur a deja
tout decide** : il a rendu autant de boutons qu'il y a de zones, chacun a sa place, chacun portant
son identifiant. Le geste de l'enfant selectionne l'un de ces boutons ; le navigateur transmet la
valeur que le serveur y a ecrite.

**Ce que le serveur decide.** `POST /parties/{jeuId}/designations`, parametre `zoneDesignee`.
`PartieController` - **seul point de descente**, clause 9 d'`ADR-0058` - construit un
`DesignationMove`, le passe par `PartieUseCase`, et `DesignationSession` compare l'identifiant recu a
`bonneZone`. Le score, les essais, l'indice, la revelation, la fin de partie : tout est calcule la,
dans le domaine, sans framework, verifiable par un test unitaire de `domain`.

**Une route distincte, et non un parametre de plus sur `/coups`**, pour le motif exact qu'`ADR-0058`
a retenu en ouvrant `/reponses` : ce n'est pas le meme geste. Et une route neuve **entre d'elle-meme**
dans le perimetre du garde-fou de tabulation, qui derive ses routes du registre de Spring plutot que
d'une liste ecrite a la main.

**Le positionnement du bouton.** Quatre declarations de style en pourcentage, ecrites par le gabarit
a partir du `CadreDeZone` que le contenu declare. **Aucune arithmetique** : le pourcentage **est** la
valeur declaree. C'est ce qui distingue cette conception de celle qui aurait fait porter au gabarit
une geometrie a calculer - l'alternative 2 d'`ADR-0073`, un moteur de dessin dans un gabarit
Thymeleaf, deja refusee une fois.

**La revelation et le focus n'inventent aucun mecanisme.** Une zone deja essayee prend la marque de
`M9`, la zone revelee prend `data-focus-cible`, et le script d'agrement deplace le focus comme il le
fait deja pour une proposition. Rien de neuf cote client, et c'est le but.

### 5. Le format de la zone dans le paquet, et le chargeur unique reste unique

Un quatrieme `type` de paquet, `designation`, dans le **meme** registre du **meme** chargeur.

```yaml
schema: 1
id: cp-plan-de-la-classe
type: designation
descripteur:
  matiere: mathematiques
  niveau: CP
  domaine: Espace et geometrie
  competence: ...
items:
  - enonce: "Montre le tableau."
    illustration: plan-classe-a
    illustrationTexteAlternatif: "Plan de la classe vue de dessus. ..."
    indice: "..."
    bonneZone: tableau
    zones:
      - id: tableau
        libelle: "le tableau"
        cadre: { x: 8, y: 4, largeur: 30, hauteur: 22 }
      - id: coin-lecture
        libelle: "le coin lecture"
        cadre: { x: 62, y: 8, largeur: 30, hauteur: 26 }
```

**La dette 3 n'est pas reproduite, et c'est mesure et non affirme.** Etat du chargement au
2026-09-03 : **un** chargeur (`YamlContentPackLoader`), une enveloppe de schema (`schema`, `id`,
`type`, `descripteur`, puis `items` ou `parametres`), un registre de fabriques, et **trois** fabriques
totalisant **286 lignes** (`QuestionContentSourceFactory` 116, `SaisieContentSourceFactory` 125,
`AdditionsBorneesContentSourceFactory` 45). La dette de `main` etait **neuf familles de chargeurs,
environ 1 200 lignes dupliquees dans `bootstrap`**. Une quatrieme fabrique d'une centaine de lignes,
enregistree dans le meme registre, est **le mecanisme qu'`ADR-0007` a concu**, pas son contournement.
`ChargementDuContenuTest.ilNYAQuUnSeulChargeurDeContenu` reste vert sans modification, et
`seulLAdaptateurDeContenuLitDesFichiers` aussi.

**Ce que je refuse de faire maintenant, et le declencheur ecrit.** Avec la quatrieme fabrique, trois
fabriques liront chacune, de leur cote, les memes cles facultatives d'item (`indice`, `explication`,
`texteAVoixHaute`). C'est la forme miniature de la dette 3, et je **n'extrais pas** de lecteur
partage aujourd'hui : trois lectures de dix lignes valent mieux qu'une abstraction ecrite pour un
usage que j'imagine. **Ce qui me ferait changer d'avis** : une cinquieme fabrique, ou une cle
facultative de plus - deux occurrences supplementaires, donc un usage constate et non anticipe.

**Mais je ferme le defaut que cette duplication rend probable**, parce que lui n'est pas hypothetique
et qu'il est **muet** : une cle facultative lue par deux fabriques et oubliee par la troisieme.
`ADR-0070` ne l'attrape pas, et il le dit lui-meme dans sa Javadoc : *"Ce que ce garde-fou ne prouve
pas [...] : qu'une cle citee quelque part soit lue au bon endroit. Un litteral pose dans un test qui
n'ouvre aucun paquet le satisferait."* Un paquet de designation qui declarerait `texteAVoixHaute`
serait donc vert partout et l'enfant ne recevrait rien. La regle est en decision 6, garde-fou 6.

**Un trou de perimetre que ce format ouvre chez le voisin, trouve en lisant son code et non en le
supposant.** `AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.clesDeSchema` collecte les cles de
la racine, les cles de chaque item, et les sous-cles de **deux** blocs nommes (`descripteur`,
`parametres`). Il ne descend **pas** dans une liste de correspondances portee par un item. Les cles
`id`, `libelle`, `cadre`, `x`, `y`, `largeur` et `hauteur` lui seraient donc **invisibles**, et un
`largeurr` mal orthographie passerait sans un mot - le defaut exact que ce garde-fou existe pour
attraper. **Sa descente doit etre etendue avec ce lot**, et **par derivation plutot que par un
troisieme litteral** (`ADR-0013`, obligation 1) : descendre dans toute valeur qui est une liste de
correspondances sous un item. La forme est le critere, pas le nom - `distracteurs` et
`formesAcceptees` sont des listes de chaines et restent hors, sans qu'aucune exception ne soit
ecrite.

### 6. Les regles executables

**Compte des `ArchRule` du depot avant ce lot : 31.** Methode, dans le meme paragraphe que le
chiffre : recherche du motif `static final ArchRule` sur les arbres de sources des modules du depot,
`/target/` exclu et le faux module de contre-exemple de `LesGardeFousSontMisALEpreuveTest` exclu - il
en declare 2, ce qui explique qu'un comptage naif rende 33. Mesure statique du 2026-09-03, aucun
build lance. **Ce chiffre concorde avec les 31 qu'`ADR-0073` a obtenus par un autre chemin** (le
recensement `LesGardeFousSontMisALEpreuveTest.toutesLesRegles`, sur 168 fichiers de test des cinq
modules declares). Deux methodes, un meme nombre. **Apres ce lot : 33.**

#### Les deux regles ArchUnit

**Fichier** : `bootstrap/src/test/java/com/recreo/archi/DecouplageMecaniqueContenuTest.java`, en
clauses 10 et 11, a la suite des neuf existantes.
**Contre-exemples** : `bootstrap/src/test/java/com/recreo/domain/mechanics/contreexemple/` et
`bootstrap/src/test/java/com/recreo/domain/content/contreexemple/`, morsures declarees dans
`LesReglesMordentTest` (`ADR-0003`).

**Clause 10 - `uneMecaniqueIgnoreLaGeometrie`.** C'est la regle centrale de cet `ADR`.

```java
@ArchTest
static final ArchRule uneMecaniqueIgnoreLaGeometrie = noClasses()
        .that().resideInAnyPackage(CONTRAT_MECANIQUE + "..", MECANIQUES)
        .should().dependOnClassesThat()
        .haveFullyQualifiedName(CONTRAT_CONTENU + ".CadreDeZone")
        .allowEmptyShould(false)
        .as("ADR-0080 : une mecanique transporte une zone et ne lit jamais son cadre. Une mecanique"
                + " qui touche a la geometrie est une mecanique qui resout un point en zone, donc"
                + " un verdict qui depend d'une coordonnee envoyee par le navigateur");
```

**Ce qu'elle interdit reellement, et pourquoi elle n'est pas decorative.** La conception que cet
`ADR` refuse - le client envoie `(x, y)`, le serveur cherche quelle zone contient ce point - **n'est
pas absurde** : elle est meme la premiere qui vient a l'esprit, elle loge bien le verdict cote
serveur, et elle passerait toutes les regles existantes du depot sans en rougir une seule. Pour
l'ecrire, il faut lire un `CadreDeZone` dans la mecanique : comparer une abscisse a `cadre.x()`,
additionner `cadre.largeur()`. **Chacun de ces gestes produit un appel dont le proprietaire est
`CadreDeZone`, donc une dependance directe que cette clause voit.** C'est le seul point du systeme ou
cette conception se trahit sans ambiguite, et c'est pourquoi la regle est posee la et pas ailleurs.
**Elle est calquee sur la clause 4** (`uneMecaniqueIgnoreLaMatiere`), qui tient depuis s00 la
propriete de meme forme.

**Ce qu'elle ne dit pas, ecrit avant qu'on le lui reproche** : elle ne garde pas l'adaptateur web.
Un `@RequestParam int x` sur `PartieController` est un entier pour ArchUnit, et aucun modele de types
ne dira jamais qu'il porte une abscisse. Cette moitie-la est tenue par un garde-fou de balayage sur
le document servi (garde-fou 1) et par la liste d'interdits du JavaScript maison (garde-fou 2).
**Une propriete gardee a moitie qui se croit gardee en entier est la faute que ce depot a sanctionnee
plusieurs fois** ; elle est donc decoupee, et chaque moitie est nommee avec son garde.

**Due avec la tache T1**, celle qui cree `CadreDeZone`. L'ecrire avant serait une regle a population
vide, ce qu'`ADR-0051` refuse.

**Clause 11 - `aucunRecordDuContratDeContenuNeDeclareDeMethode`.** Elle ferme un trou de la clause 8,
et le trou est reel.

La clause 8 (`unContratNeDeclareAucunComportement`, `ADR-0058`) refuse a une **interface** des deux
paquets de contrat toute methode statique, par defaut ou privee. Son perimetre **exclut
explicitement les records**, et l'exclusion est motivee et juste : leurs accesseurs sont produits par
le compilateur, et les refuser ferait rougir `Score` et `SessionStatus` sans qu'aucun comportement
n'ait ete partage. **Mais un record peut declarer une methode ecrite a la main**, et
`CadreDeZone.contient(int x, int y)` est exactement cela : le predicat de resolution d'un point en
zone, pose dans le contrat de contenu, ou la clause 8 ne le regarde pas. La propriete de la clause 10
ne tiendrait plus alors que par un seul fil - le fait que la mecanique doive quand meme nommer le
type pour appeler la methode. **Un seul fil ne suffit pas pour la propriete centrale de cet `ADR`.**

Forme : une condition sur chaque record des deux paquets de contrat - toute methode declaree porte le
nom d'un de ses champs, ou est `equals`, `hashCode` ou `toString`. **Population et etat aujourd'hui,
verifies par lecture et non supposes** : `com.recreo.domain.content` porte quatre records -
`Question`, `ReponseAEcrire`, `ContentDescriptor`, `ItemId` - qui ne declarent **aucune** methode ;
`Question` et `ReponseAEcrire` declarent des constructeurs, qui ne sont pas des methodes. La clause
serait donc **verte aujourd'hui sur une population non vide**, et c'est la seule regle de cet `ADR`
qui peut etre ecrite avant le reste du lot. **Elle doit l'etre** : une regle ecrite le jour ou elle
gene est une regle qu'on negocie.

**Ce qui n'est pas une regle neuve, et je le dis plutot que de gonfler le compte** : la clause 7
(`lesMecaniquesNeSeConnaissentPas`) et la clause 9
(`adapterWebNeNommeAucuneMecaniqueHorsDeSonPointDeDescente`) couvrent la mecanique de designation
**sans une ligne de changement**, parce que l'une derive son perimetre des tranches de
`domain.mechanics` et l'autre de tout `adapter.web`. Ecrire une regle *"la designation ne depend pas
des deux autres"* serait la definition meme d'une regle decorative.

#### Les six garde-fous qui ne sont pas des `ArchRule`

Dit explicitement, comme `ADR-0073`, `ADR-0078` et `ADR-0079` l'ont fait : ArchUnit modelise des
types Java et leurs relations. Les objets juges ci-dessous sont **un document HTML servi, un fichier
de script, une feuille de style, des cles de YAML et des items charges** - aucun n'est un type.

| # | Fichier (a creer) | Module | Ce qu'il interdit reellement | Du avec |
|---|---|---|---|---|
| 1 | `ChaqueZoneDesignableEstUnBoutonDuFormulaireDeCoupTest` | `adapter-web` | Sur l'ecran de manche reellement servi d'un jeu de designation : chaque zone de l'item est **un** `<button type="submit" name="zoneDesignee">` a valeur non vide et a nom accessible non vide, dans le formulaire du coup ; le document ne porte ni `usemap`, ni `<map>`, ni `<area>`, ni attribut d'evenement en ligne. **Interdit l'image cliquable et le bouton simule par un `<div>`** - les deux formes par lesquelles la designation cesserait d'etre jouable au clavier | T4 |
| 2 | Amendement de `FrontieresDuJavaScriptTest.INTERDITS_JS` | `adapter-web` | Ajoute `zone`, `clientX`, `clientY`, `offsetX`, `offsetY`, `getBoundingClientRect`, `elementFromPoint`. **Interdit la resolution d'un point en zone cote client**, dans ses ecritures usuelles. Meme geste que l'amendement d'`ADR-0026`, qui avait ajoute `proposition` et `colonne` : ces mots nomment ce que le serveur doit avoir decide avant que le fragment ne le quitte | T4 |
| 3 | `AucuneZoneDesignableNeDescendSousLaCibleTactileTest` | `adapter-web` | La taille rendue d'une zone atteint le plancher de cible tactile. **Les deux termes sont derives, jamais ecrits a la main** : le plancher est lu la ou il est deja epingle, la taille rendue de l'image est lue dans la feuille de style. Patron d'`ADR-0054`, une fonction epinglee - et `ADR-0055` pour le motif d'epingler **les deux** membres de la comparaison | T4 |
| 4 | `LaBonneZoneNEstPasPredictibleSurUnPaquetTest` | `adapter-content` | Sur un paquet de designation : la bonne zone n'est pas le meme identifiant sur une majorite d'items, et n'est pas la plus grande zone de son item sur une majorite d'items. **Transposition d'un defaut mesure**, pas une precaution : `S12-3` a ete refuse parce qu'un enfant qui pressait toujours la plus grande proposition gagnait 64,6 % des manches sans lire un enonce | T5 |
| 5 | `AucunEnonceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest` | `adapter-content` | L'enonce ne nomme pas le libelle de la bonne zone. **Avec l'operateur d'`ADR-0076`** - decoupage en mots, jamais sous-chaine - et **en reutilisant son tokeniseur**, pas un second ecrit a cote. Interdit la contrefacon par enonce : *"Montre le tableau"* avec une zone nommee *"le tableau"* se repond par le nom accessible seul, image effacee, ce qui est `CON-1` en echec | T5 |
| 6 | `ChaqueCleFacultativeDItemEstLueParLaFabriqueDeSonTypeTest` | `adapter-content` | Pour chaque paquet servi, chaque cle facultative d'item declaree dans le YAML se retrouve dans l'item **charge par la chaine reelle**. **Ferme le trou qu'`ADR-0070` nomme dans sa propre Javadoc** : une cle lue par deux fabriques sur trois est aujourd'hui verte partout et perdue a l'execution | T3 |

**Sur le garde-fou 2 et la lecon de s05.** Une liste de mots interdits est exactement le genre de
regle qui passe sa propre epreuve et echoue dans le monde. J'ai donc verifie les sept mots **sur le
materiau reel et non sur une fixture** : `adapters/adapter-web/src/main/resources/static/js/recreo.js`,
543 octets, seul script maison du depot, lu le 2026-09-03 - **aucun des sept n'y figure**, ni comme
mot ni comme sous-chaine. Le mot `zone` est le plus fragile des sept et je le garde en le disant :
il est court, et un script maison qui l'emploierait a bon droit rougirait. Je l'accepte parce qu'un
script d'agrement qui nomme une zone est precisement ce que cette liste refuse, et parce que la liste
ne juge que le JavaScript maison, jamais la bibliotheque tierce - c'est la faute de s05 fermee par le
perimetre plutot que par la chance.

### 7. L'accessibilite : deux questions distinctes, et je refuse de les confondre

**C'est une contrainte de conception ici, pas une finition, et la suite montre pourquoi : la reponse
a la premiere question a decide la conception, elle n'en a pas decoule.**

#### 7.1 Jouable au clavier : **oui**, et c'est une consequence de la decision 4

Une zone est un `<button type="submit">`. Un bouton est nativement focalisable, il est atteint par la
tabulation dans l'ordre du document, il s'active par Entree et par Espace, et il soumet le formulaire
qui porte le `hx-post`. **Aucun `tabindex` n'est pose**, ni par le gabarit ni par le script -
`ADR-0057` clause 1 tient deja cette moitie et son garde-fou est ecrit.

**Ce que cela a coute a la conception, et c'est le point.** L'autre conception - une surface cliquable
et une resolution du point - **n'est pas jouable au clavier, et ne peut pas le devenir**. Il n'y a
rien a focaliser : une image n'a pas de sous-elements atteignables, et simuler un curseur au clavier
demanderait au JavaScript de tenir une position, donc de decider quelque chose de jeu, donc la
dette 4. **La jouabilite au clavier n'est donc pas une propriete ajoutee apres coup a la conception
retenue : c'est le critere qui a elimine l'autre.** C'est pourquoi cet `ADR` la traite comme la
contrainte de conception que l'utilisatrice a demande qu'elle soit, et non comme un point de
finition.

**Ce qui reste a decider et qui n'est pas de moi** : l'ordre de tabulation est l'ordre de declaration
des zones dans le paquet. C'est le seul ordre disponible - il n'y en a pas d'autre qu'un tri
geometrique, et un tri geometrique serait de la geometrie dans le rendu. L'auteur declare donc ses
zones dans un ordre de lecture, et **ce qu'est un ordre de lecture sur un planisphere** est une
question pedagogique routee a `nadia-prof`.

#### 7.2 Jouable sans voir l'image : **non**, et aucun libelle ne repare cela

Ce n'est pas la meme question, et les confondre serait exactement le defaut que ce depot m'a appris a
eviter - un perimetre qui echoue par inclusion autant que par omission.

La demonstration tient en deux branches, et elles sont exhaustives sur ce qu'un nom accessible peut
porter :

- **Si le libelle nomme ce que la zone est** (*"le tableau"*, *"l'ocean Atlantique"*), une lecture
  d'ecran annonce la liste des reponses possibles en clair. La question *"Montre le tableau"* se
  repond sans regarder l'image. C'est `CON-1` en echec, litteralement : *"en effacant l'image, la
  reponse reste-t-elle trouvable ?"* - oui.
- **Si le libelle ne nomme rien** (*"zone 1"*, *"zone 2"*), l'ecran n'a plus aucun sens pour qui ne
  voit pas.

Il n'y a pas de troisieme branche, parce qu'un nom accessible dit quelque chose ou ne dit rien.
**La mecanique de designation n'est donc pas jouable par un enfant qui ne voit pas l'image, et ce
n'est pas un defaut d'implementation a corriger plus tard : c'est une propriete de la competence
visee.** *Se reperer sur une carte* est une competence visuelle ; le programme la formule ainsi.

**Ce que j'en fais, et ce que je n'en fais pas.** C'est un **fait d'architecture**, et il remonte -
a `nadia-prof` pour la portee pedagogique, a `camille-po` pour ce que le produit doit en dire au
joueur, a l'utilisatrice pour l'arbitrage s'il en faut un. `ADR-0073` avait deja nomme cette
frontiere en des termes que je reprends sans les changer : *"la possibilite qu'elle conclue qu'un jeu
visuel est injouable sans la vue et que cela doive etre dit au joueur appartient a `camille-po`"*.
**Je ne tranche rien de produit ni de pedagogique ; je nomme le fait et son destinataire.**

#### 7.3 Le plancher de taille d'une zone, et le plafond qu'il impose au quadrillage

**Deux valeurs deja epinglees dans le depot, lues le 2026-09-03, aucun build lance :**

- la cible tactile minimale est de **44 px**, epinglee par `TailleDesTextesEtBoutonsTest` sur la
  hauteur minimale d'un pressable - *"une cible tactile de 46 x 41 px avec 8 px d'espacement est sous
  le minimum de 44 px"* ;
- la hauteur rendue d'une illustration d'item est plafonnee a **11 rem, soit 176 px**, par la regle
  `.image-item { max-height: 11rem }` de
  `adapters/adapter-web/src/main/resources/static/css/recreo.css`.

**Consequence arithmetique, et elle est contraignante** : 44 / 176 = 25 %. **A la taille d'image
d'aujourd'hui, une zone carree occupe au moins un quart du cote, et un quadrillage plus fin que
4 x 4 est impossible.** Une grille 5 x 5 rendrait des cases de 35,2 px, sous le plancher.

**Ce que ce plafond ne ferme pas** : `QUA-4` borne un deplacement de CP a trois pas, ce qu'une grille
4 x 4 sert. La contrainte mord, elle ne bloque pas la ligne.

**Ce qui est route et non tranche ici** : agrandir l'image servie au palier 2 est une decision
d'`ines-design`, mesuree par elle contre le pli, et elle n'appartient pas a cet `ADR`. Ce que cet
`ADR` fixe est que **le garde-fou 3 lira ces deux valeurs la ou elles vivent** et rougira le jour ou
l'une d'elles bougera sans que l'autre soit revue - la moitie de comparaison qu'`ADR-0055` reproche a
`ADR-0054` de n'avoir pas epinglee.

### 8. Ce que cet `ADR` ne decide pas

- **Le libelle d'une zone**, sa formulation, et si un jeu de designation doit annoncer au joueur
  qu'il suppose la vue. `nadia-prof` pour la premiere moitie, `camille-po` pour la seconde.
- **La taille d'image du palier 2** et le pli. `ines-design`.
- **Les conditions pedagogiques d'un paquet de designation** - la transposition de `CON-1` a `CON-4`,
  l'exactitude d'un plan ou d'une carte, ce qu'un quadrillage de CP a le droit de demander.
  `nadia-prof`, par un arbitrage prealable, comme pour toute famille neuve.
- **L'ordre de declaration des zones** comme ordre de lecture (voir 7.1).
- **Le format des dessins de plan et de carte.** `ADR-0073` a laisse ce point ouvert en le nommant :
  sa clause 4 sait lire un SVG et ne saurait pas lire une photographie. Un plan de classe et un
  planisphere sont des dessins geometriques ; rien n'oblige a rouvrir la question, et si un lot doit
  la rouvrir, il la rouvrira avec sa mesure.
- **Le cliquet de poids des illustrations**, deja pose par le lot du palier 1, qui s'appliquera aux
  dessins du palier 2 sans qu'aucune decision neuve soit necessaire.

---

## Consequences

**Bonnes :**

- Les cinq cas d'usage heterogenes tiennent dans **un** contrat, **une** mecanique, **un** type de
  paquet. Un planisphere de CM1 et un quadrillage de CP sont deux fichiers de contenu, pas deux
  familles de code.
- Le verdict est dans le domaine, calculable par un test unitaire sans navigateur et sans Spring.
- La jouabilite au clavier est acquise par construction et non par vigilance.
- La clause 10 rend la conception par coordonnees **impossible a introduire en silence**, y compris
  par quelqu'un qui n'aurait jamais lu cet `ADR`.
- Un trou de perimetre du garde-fou d'`ADR-0070` est trouve et ferme avant qu'un paquet ne tombe
  dedans, plutot qu'apres.

**Mauvaises, et elles sont reelles :**

- **Une troisieme mecanique duplique une troisieme fois la boucle de partie**, pour un cout de
  l'ordre de 300 a 470 lignes mesure sur les deux existantes. C'est le prix de la clause 3
  d'`ADR-0004`, deja paye deux fois et paye les yeux ouverts. **Ce qui me ferait rouvrir la
  question** : une quatrieme mecanique. A trois occurrences, une boucle de partie partagee cesse
  d'etre une abstraction imaginee - mais elle se heurterait alors frontalement a `ADR-0004`, et ce
  serait un `ADR` qui l'amende, pas un refactoring.
- **Une troisieme fabrique de contenu duplique la lecture des cles facultatives.** Assume, avec son
  declencheur ecrit, et son defaut muet ferme par le garde-fou 6.
- **Huit `switch` exhaustifs s'arretent a la compilation.** C'est voulu, c'est mesure, et six d'entre
  eux sont dans des garde-fous de contenu qui devront **decider** quelque chose plutot que renvoyer
  une branche vide.
- **`CadreDeZone` est une piece mobile de plus** qu'une conception minimale n'aurait pas. Elle
  n'existe que pour rendre la clause 10 ecrivable ; si la clause 10 disparaissait, ce record devrait
  fondre dans `Zone`.
- **Un contenu de designation ne se relit pas sans jouer.** Quatre nombres par zone ne disent a
  personne si la zone couvre le bon endroit du dessin. Aucun automate ne le dira, et c'est une charge
  de relecture de plus pour `nadia-prof`, sur une propriete dont elle est deja seule garde.
- **La mecanique n'est pas jouable sans la vue** (7.2). Ce n'est pas une consequence de la
  conception, c'est une propriete de la competence - mais c'est une consequence du fait de servir ces
  lignes-la, et elle est ecrite ici pour ne pas etre decouverte a la review.

---

## Alternatives considerees

1. **Le client envoie une coordonnee `(x, y)`, le serveur cherche la zone qui contient ce point.**
   **Ecartee, et c'est le refus principal de cet `ADR`.** Elle loge pourtant le verdict cote serveur,
   donc elle passe la contrainte telle qu'elle se formule d'ordinaire. Trois motifs :
   *(a)* **elle n'est pas jouable au clavier et ne peut pas le devenir** (7.1) ; *(b)* elle oblige le
   navigateur a calculer une position dans le referentiel de l'image - facteur d'echelle, marges,
   defilement - donc a decider quelque chose de jeu en JavaScript, la dette 4 par un autre chemin ;
   *(c)* elle fait entrer la geometrie dans la mecanique, qui devient une mecanique qui sait ce
   qu'est un rectangle. **La clause 10 existe contre cette alternative-la, et contre elle seule.**
2. **Une image cliquable HTML (`<map>` / `<area>`).** Ecartee : c'est la variante native de
   l'alternative 1. Les zones y sont declarees, ce qui est bien, mais un `<area>` n'est pas un
   controle de formulaire, sa focalisabilite au clavier est inegale d'un navigateur a l'autre, et il
   ne soumet rien. Le garde-fou 1 l'interdit nommement plutot que par implication.
3. **Garder `Question` et poser la geometrie sur les propositions.** Ecartee pour deux motifs
   cumules : il faudrait soit des listes paralleles - une geometrie par proposition, indexee par
   position - qui est la dette 6 de `CLAUDE.md`, soit une composante de plus sur un record dont
   `ADR-0073` a deja ecrit qu'il atteint sa limite (*"chaque composante de plus est une ligne de plus
   dans l'identite d'ecran [...] il rougit deja a la sixieme"*). Et il faudrait apprendre a
   `BanditManchot` a ne pas melanger pour certains items, c'est-a-dire a lire la forme du contenu
   pour changer son comportement - ce qu'`ADR-0004` ferme depuis le sprint 0.
4. **Une mecanique par cas d'usage** - une pour le quadrillage, une pour la carte, une pour la frise.
   Ecartee : c'est la dette 3 transposee du chargement vers la mecanique. Les cinq cas ne different
   que par le dessin et par la liste des zones, c'est-a-dire par le contenu, et la clause 10 rend
   cette difference invisible du code. **Une deuxieme mecanique de designation n'aurait litteralement
   rien de plus a dire que la premiere.**
5. **Une mecanique de designation qui delegue sa boucle de partie a un moteur partage avec les deux
   autres.** Ecartee : interdite par la clause 3 d'`ADR-0004` et par la clause 8 d'`ADR-0058`, qui
   ferme jusqu'a la forme deguisee - une interface de contrat portant une methode par defaut. Le
   refus est ancien, motive, il a un prix chiffre ; le rouvrir demanderait un `ADR` qui amende
   `ADR-0004`.
6. **Attendre s29 et n'ecrire cet `ADR` qu'avec le lot.** Ecartee : `DEC-0074` ouvre le chantier en
   s28 precisement pour que la conception ne soit pas faite dans le meme geste que le code. Et une
   regle ecrite apres le geste qu'elle interdit documente une histoire au lieu d'empecher quelque
   chose.

---

## Cout et decoupage en taches

**Pour `sofia-sm`.** Cet avis porte sur la faisabilite et sur le decoupage ; il ne repriorise rien,
n'ecrit aucune story et ne fixe aucun rang.

| Tache | Perimetre | Modules | Depend de |
|---|---|---|---|
| **T1** | `ZoneADesigner`, `Zone`, `CadreDeZone`, troisieme permission d'`Item`, invariants des constructeurs compacts, les 8 `switch` exhaustifs a rouvrir, clauses ArchUnit 10 et 11 et leurs contre-exemples | `domain`, `bootstrap` | rien |
| **T2** | Mecanique `domain.mechanics.designation` : `Designation`, `DesignationMove`, `DesignationSession`, `DesignationVerdict`, tests unitaires de la boucle de partie | `domain` | T1 |
| **T3** | `DesignationContentSourceFactory`, enregistrement du type `designation`, extension de la descente de `AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest` aux listes de correspondances, garde-fou 6 | `adapter-content`, `bootstrap` | T1 |
| **T4** | Route `POST /parties/{jeuId}/designations`, branche du point de descente, gabarit de manche, style des zones positionnees, garde-fous 1, 2 et 3 | `adapter-web` | T2, **et l'arbitrage d'`ines-design` sur la taille d'image du palier 2** |
| **T5** | Garde-fous 4 et 5 sur les paquets de designation | `adapter-content` | T3, et un paquet a juger |
| **T6** | Un paquet de contenu, ses dessins, la fiche de conformite | `adapter-content`, `adapter-web` | T4, T5, **et l'arbitrage prealable de `nadia-prof`** |

**Verdict de faisabilite : le chantier ne tient pas en un sprint, et il n'a pas a y tenir.**

- **T1, T2, T3 et T5 sont faisables en l'etat en s28**, par un developpeur, sans aucune dependance
  externe a la squad. C'est ce que `DEC-0074` a affecte, et le precedent le soutient : la mecanique
  de saisie a ete concue a s19 (`ADR-0058`) et livree a s24 dans un sprint qui portait par ailleurs
  trois paquets de contenu.
- **T4 est faisable moyennant un arbitrage qui n'existe pas** : le garde-fou 3 epingle la taille
  rendue de l'image, et cette taille est celle du palier 1 (176 px) tant qu'`ines-design` n'en decide
  pas une autre. On peut livrer T4 sur la valeur du palier 1 et laisser le garde-fou rougir le jour
  ou elle change - c'est meme le comportement voulu - mais il faut le savoir avant, pas le decouvrir
  a la review.
- **T6 est infaisable en s28**, et `DEC-0074` l'a deja acte : aucune ligne ne se ferme dans s28, le
  gain arrive au plus tot a s29. Un paquet de designation exige un arbitrage prealable de
  `nadia-prof` du meme genre que celui du palier 1, et cet arbitrage n'est pas ecrit.

**Le risque qui merite d'etre nomme au planning** : T4 est la seule tache qui touche a la fois au
rendu, au style et a une valeur epinglee. C'est la que les surprises sont, et c'est aussi la tache
dont le resultat se juge en jouant plutot qu'en lisant. Si un seul point de ce chantier doit etre
demontre avant d'etre declare fini, c'est celui-la.
