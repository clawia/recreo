# Arbitrage prealable - s43, mathematiques CE1, lot 3 (`MAT-CE1-14`, `-15`, `-17`, `-18`)

- **Auteur** : `nadia-prof`. **Date** : 2026-09-25. **Sprint** : s43 (arbitrage prealable, hors lot
  de contenu).
- **Ce qui m'est pose** : arbitrage pedagogique prealable sur quatre des quatorze lignes de
  mathematiques CE1 encore `non couvert`, demande par l'utilisatrice a la lecture de `DEC-0099`,
  section 4, qui constate que ces quatorze lignes n'ont **aucun arbitrage prealable ecrit** et que
  les engager suppose de me le demander avant tout avis de faisabilite de `theo-archi`. Le travail
  est divise en trois lots traites en parallele ; **celui-ci porte sur `MAT-CE1-14`, `MAT-CE1-15`,
  `MAT-CE1-17` et `MAT-CE1-18`, et sur rien d'autre.** Les dix autres lignes (`MAT-CE1-01`, `-02`,
  `-04`, `-05`, `-06`, `-07`, `-08`, `-10`, `-11`, `-12`) sont traitees ailleurs : je signale les
  collisions que je vois avec elles (section 6) **sans les juger**.
- **Ce document ne prononce aucun Done**, pedagogique ou technique. Il n'ecrit ni backlog, ni
  garde-fou, ne cree ni ne deplace aucune ligne de `squad/programme/MATRICE-couverture.md`, ne
  choisit aucun mecanisme technique, **ne fixe aucun tarif et ne rend aucun avis de faisabilite**.
- **Ce que j'ai joue, et ce que je n'ai pas pu jouer.** Aucun paquet n'existe pour aucune des quatre
  lignes : il n'y a rien a jouer sur elles, et je le dis plutot que de le laisser croire. Ce que
  j'ai fait a la place, et qui n'est pas une lecture de prose : relu le referentiel CE1 **et** le
  referentiel CP dont il prolonge trois lignes ; relu mes quatre arbitrages CE1 anterieurs ; relu
  les fiches des paquets deja servis qui touchent mes quatre lignes
  (`figures-planes-cp`, `deplacements-quadrillage-cp`, `solides-ce1`, `moities-ce1`,
  `plan-de-la-classe`) ; et **lu le code et la feuille de style de production** pour verifier
  moi-meme, plutot que de recopier, deux faits dont trois de mes quatre verdicts dependent
  (section 0).
- **Lien** : `DEC-0099` (section 4, qui route ces quatorze lignes) ; `DEC-0089` ;
  `squad/programme/mathematiques/competences-ce1.md` ; `competences-cp.md` ;
  `ARBITRAGE-s36-longueur-d-enonce-au-ce1.md` (`LON-CE1-1`, `LON-CE1-2`) ;
  `ARBITRAGE-s37-operande-nul-et-forme-du-tirage-a-l-echelle-100.md` ;
  `ARBITRAGE-s38-soustractions-complements-dizaine-et-moities-ce1.md` ;
  `ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md` (sections 2 et 4) ;
  `ARBITRAGE-PREALABLE-s29-le-palier-2-ce-qu-un-paquet-de-designation-doit-apporter.md` (`ZON-7` a
  `ZON-16`) ; `ARBITRAGE-PREALABLE-s32...` section 17.7 ;
  `FICHE-bandit-manchot-deplacements-quadrillage-cp.md` (`DEP-CP-R1` a `DEP-CP-R4`) ;
  `FICHE-bandit-manchot-figures-planes-cp.md` (`FIG-1` a `FIG-4`) ;
  `FICHE-designation-plan-de-la-classe.md`.

---

## 0. Deux corrections d'etat, faites avant d'arbitrer et non apres

Trois de mes quatre verdicts dependent de ce qu'une mecanique visuelle peut faire aujourd'hui. Je
verifie ce qui est ecrit sur ce point avant de m'appuyer dessus, parce que **deux des affirmations
en circulation sont perimees, dont une qui est de moi**.

### 0.1 La mecanique de designation - le "palier 2" - existe, elle est servie, et deux lignes ont ferme dessus

`DEC-0099`, section 4 bis, decrit le palier 2 comme *"une mecanique de `designation` entierement
absente du produit a ce jour"* et cite, a l'appui, ma propre phrase du 2026-09-05
(`ARBITRAGE-PREALABLE-s32`, section 9) : *"le palier 2 n'est jouable par personne"*. **La citation
est exacte ; le constat ne l'est plus, et il a vingt jours de retard.**

Ce que j'ai verifie, dans cet ordre, sur l'arbre `round-two` a `5f6ba3d7` :

- `domain/src/main/java/com/recreo/domain/mechanics/designation/` porte quatre classes de
  production - `Designation`, `DesignationSession`, `DesignationMove`, `DesignationVerdict` ;
- `adapters/adapter-content/src/main/resources/catalogue.yaml` sert **deux entrees** de mecanique
  `designation` : `designation-frise-de-la-journee` et `designation-plan-de-la-classe` ;
- j'ai moi-meme prononce le Done pedagogique sur la seconde le 2026-09-21
  (`FICHE-designation-plan-de-la-classe.md` : *"Etat de la ligne apres l'audit de s36 :
  `couvert-valide`"*), au second passage, apres un refus a s35 que la fiche conserve.

**Consequence pour ce lot** : la question *"peut-on montrer un quadrillage et faire designer une
case ?"* n'a pas la meme reponse qu'au 2026-09-05. Elle est **oui**, et deux de mes quatre verdicts
en dependent (`MAT-CE1-14`, `MAT-CE1-17`). Ce n'est pas un reproche a `DEC-0099`, qui cite sa source
et la date ; c'est la raison pour laquelle je ne juge pas sur la citation.

**Ce que ce constat ne dit pas** : il ne dit pas que la moitie visuelle de `MAT-CP-10` devient
gratuite, il ne rouvre aucune des quatre lignes de `DEC-0099`, section 4 ter, et il ne chiffre rien -
ce qu'un paquet de designation coute reste hors de ma main.

### 0.2 Le plafond de 4 x 4 sur un quadrillage est de moi, et ce n'est plus l'arithmetique d'aujourd'hui

`ZON-8` (`ARBITRAGE-PREALABLE-s29`, section 4.4) dit, pour les zones homogenes : *"sur un
quadrillage, l'enfant n'enumere pas les cases, il applique un deplacement. Elles restent bornees par
le plancher de cible tactile, **qui donne 4 x 4 aujourd'hui**."* Le mot *aujourd'hui* est dans la
clause : elle borne par une **derivation**, jamais par un nombre fige. Je refais donc la derivation
plutot que de recopier le nombre.

| Terme | Valeur a s29 (2026-09-04) | Valeur verifiee le 2026-09-25 | Source lue |
|---|---|---|---|
| Plancher de cible tactile | 44 px (2,75 rem) | **inchange, 2,75 rem** | `TailleDesTextesEtBoutonsTest.CIBLE_TACTILE_REM` |
| Plafond de hauteur rendue de l'image | 176 px (11 rem), `.image-item` | **352 px (22 rem), `.image-designation`** | `recreo.css`, regle `.image-designation` |
| Cote maximal d'une grille a cases carrees | 176 / 44 = **4** | 352 / 44 = **8** | `AucuneZoneDesignableNeDescendSousLaCibleTactileTest` |

La regle `.image-designation` **double le plafond pour la designation seule**, et le garde-fou qui
mord lit ce plafond-la, jamais celui de `.image-item` - sa Javadoc le dit explicitement. Mon 4 x 4
etait juste sur l'arithmetique de son jour et ne l'est plus.

**Je ne deplace pas un poteau, je re-derive la clause qui demandait a l'etre** : `ZON-8` borne par le
plancher tactile courant, et ce plancher autorise aujourd'hui 8 x 8. **Ce que je borne plus bas, en
section 1, est une borne pedagogique de CE1, et je la donne comme telle** - elle est plus serree que
la borne technique, et c'est a moi de l'assumer, pas au garde-fou.

---

## 1. `MAT-CE1-14` - se reperer, se deplacer et coder un deplacement sur un quadrillage

**Libelle exact, verifie mot pour mot** : *"Se repérer, se déplacer et coder un déplacement sur un
quadrillage."*

### Verdict : **ACCEPTE SOUS CONDITIONS**, `DEP-CE1-1` a `DEP-CE1-4`

Les trois verbes sont servables aujourd'hui, mais **pas par le meme support et pas au meme titre** :

| Verbe | Servable ? | Par quoi |
|---|---|---|
| se reperer | oui | designation sur un quadrillage dessine |
| se deplacer | oui | designation sur un quadrillage dessine |
| coder un deplacement | **oui, du cote reception seulement** | choix textuel illustre, trois codes candidats |

**Un paquet purement textuel, sans quadrillage montre, ne ferme pas cette ligne.** Il plafonne a
`en cours`, sur le meme principe que `MAT-CE1-09` a s39 : donner la position de depart, l'arrivee et
le chemin dans la phrase, c'est s'etre repere a la place de l'enfant. `couvert-valide` suppose la
moitie designation.

### Pourquoi "coder" reste du cote reception, et ce que j'ai verifie avant de l'ecrire

Produire un code, c'est l'ecrire. La mecanique qui fait ecrire l'enfant existe (`saisie`), et j'ai
lu le contrat de contenu avant de conclure : **`ReponseAEcrire` ne porte aucun champ
`illustration`** - seul `Question` en porte un. Un item de saisie ne peut donc pas montrer le
quadrillage sur lequel l'enfant coderait son deplacement, et coder un chemin qu'on ne voit pas
reproduit exactement le defaut que j'ai refuse au CP le 2026-09-24. **La production d'un code n'est
donc pas servable aujourd'hui, et ce n'est pas un choix de contenu : c'est la forme du contrat.** Je
le nomme pour que `theo-archi` sache que le manque est la, et non dans le materiau.

### Les conditions

> **`DEP-CE1-1` - Deux axes, jamais une bande.** Le support d'un item de `MAT-CE1-14` distingue un
> deplacement horizontal d'un deplacement vertical. Une suite de cases numerotees, ou *avancer* et
> *aller a droite* sont la meme operation, ne sert pas cette ligne. **Au moins un tiers des items
> portent un chemin qui change d'axe au moins une fois.**

**Motif, et il est a ma charge.** `DEP-CP-R1`, ouverte par moi le 2026-09-24 sur le paquet de CP,
constate que la forme d'item que j'avais moi-meme specifiee produit une bande numerotee ou les deux
axes se confondent, et que ses indices enoncent comme une regle generale (*"Avancer, c'est ajouter
une case"*) ce qui n'est vrai que sur une ligne. **Au CE1, ce n'est plus une reserve, c'est la
competence** : *se deplacer* et *coder* n'ont aucun sens sur un seul axe. Cette clause est neuve et
datee du 2026-09-25 ; elle ne durcit pas retroactivement le paquet de CP, qui reste ce que j'ai
valide.

> **`DEP-CE1-2` - Le quadrillage est montre des qu'un item demande de se reperer ou de se deplacer.**
> Designation sur dessin, grille **d'au moins 4 x 4 et d'au plus 6 x 6 cases**, toutes de meme
> taille et toutes designables.

**Plancher a 4 x 4** : en dessous, un chemin de deux pas sort de la grille ou n'a qu'une forme
possible. **Plafond a 6 x 6, et c'est une borne pedagogique, pas technique** : la derivation de la
section 0.2 en autorise 8 x 8, et je m'arrete plus bas parce qu'un enfant de CE1 qui doit retrouver
une case parmi 64 passe son temps a chercher, pas a se deplacer. Si quelqu'un veut 7 x 7 ou 8 x 8,
c'est une reserve neuve a m'ouvrir, pas une lecture de cette clause.

> **`DEP-CE1-3` - L'enonce ne contient jamais sa reponse.** L'enonce ne nomme ni le libelle de la
> case attendue ni aucune formule qui la designe directement : l'enfant part d'une case marquee sur
> le dessin et applique un deplacement qu'il doit tenir en tete.

**Motif, repris de la barre que j'ai posee au CP** le 2026-09-24 et atteinte depuis : *"un paquet
textuel de `MAT-CP-10` doit faire representer a l'enfant au moins une position ou une orientation qui
n'est pas donnee dans la phrase qu'il lit"*. Sur un support dessine, le piege change de forme mais
pas de nature : si les cases portent leur coordonnee ecrite (`B3`) et que l'enonce dit *"Montre la
case B3"*, la tache est une lecture de libelle, pas un reperage. `ZON-10` interdit deja qu'un libelle
nomme la cible ; cette clause-ci ferme le cote de l'enonce.

> **`DEP-CE1-4` - Coder : trois codes complets, comparables.** Les trois propositions d'un item de
> codage sont trois codes **de meme longueur**, formes des memes symboles, differant par l'ordre des
> pas ou par le sens d'au moins un pas, et **chacun menant a une case reelle de la grille**. Jamais
> un code plus court ou plus long que les autres, jamais un code qui sort du quadrillage.

**Motif** : un code plus court se repere sans le lire, et un code qui sort de la grille est un
distracteur absurde - il transforme l'exercice en devinette, ce que je refuse partout ailleurs.

### Mention de couverture, fixee d'avance pour ne pas etre a me redemander

Le jour ou les deux moities seront servies et ou je prononcerai le Done, la mention due est :

> **"se reperer et se deplacer servis par designation sur quadrillage ; 'coder un deplacement'
> servi du cote reception (choix d'un code parmi trois), la production d'un code reste non
> couverte"**

Si seule la moitie textuelle est servie, la ligne reste **`en cours`** et la mention porte en plus
l'absence du quadrillage montre.

---

## 2. `MAT-CE1-15` - reconnaitre, decrire et tracer des figures planes

**Libelle exact, verifie mot pour mot** : *"Reconnaître, décrire et tracer des figures planes
(carré, rectangle, triangle, triangle rectangle, cercle) à la règle et sur quadrillage."*

### Verdict : **COUVERTURE PARTIELLE nommee**, `FIG-CE1-1` a `FIG-CE1-3`

**Cette ligne ne peut pas atteindre `couvert-valide`, et le motif n'est pas de contenu.** Trois
verbes y sont attaches ; le troisieme, *tracer a la regle et sur quadrillage*, **n'est servable par
aucune mecanique du produit**, et je l'ai verifie plutot que suppose. Les trois mecaniques existantes
sont : `bandit-manchot` (l'enfant choisit parmi trois propositions), `designation` (l'enfant designe
une zone parmi celles que le contenu declare), `saisie` (l'enfant ecrit du texte). **Aucune ne
produit un trace.** Tracer un carre a la regle, c'est poser quatre segments ; rien dans le produit ne
recoit ce geste, et ce n'est pas un materiau qui manque - c'est l'interaction.

C'est le meme partage qu'a s39 sur `MAT-CE1-09` : la ligne ouvre sur ses verbes servables et plafonne
a **`en cours`** tant que le verbe instrumente n'est pas servi. Je ne prejuge pas s'il vaut la peine
d'etre construit un jour - question d'architecture, a `theo-archi`.

### Ce qui reste servable, et pourquoi ce n'est presque pas ce qu'on croit

> **`FIG-CE1-1` - "Reconnaitre" seul ne sert pas cette ligne au CE1.** Quatre des cinq figures
> nommees - carre, rectangle, triangle, cercle - sont **deja servies et validees au CP** par
> `bandit-manchot-figures-planes-cp` (`MAT-CP-11`, `couvert-valide`), sur la tache exacte *"Quelle
> est cette figure ?"* / *"Comment s'appelle cette figure ?"*. Un paquet de CE1 qui reprendrait cette
> tache travaillerait une competence de CP sous une ligne de CE1. **Tout item de `MAT-CE1-15` porte
> donc soit le triangle rectangle, soit le verbe *decrire*** - c'est-a-dire une propriete de la
> figure, et non son seul nom.

J'ai verifie le paquet de CP item par item avant d'ecrire cette clause : les douze items y sont tous
de la forme "un dessin, trois noms de figures", et l'un d'eux sert deja le dessin
`figure-triangle-rectangle` sous la bonne reponse *"un triangle"*.

> **`FIG-CE1-2` - "Un triangle" n'est jamais une proposition fausse devant un triangle rectangle.**
> Un triangle rectangle **est** un triangle : l'enfant qui repond *"un triangle"* a raison, et un
> item qui le compte faux lui apprend une erreur. Quand la bonne reponse est *"un triangle
> rectangle"*, les deux autres propositions excluent reellement la figure montree (un carre, un
> rectangle, un cercle) ; *"un triangle"* n'y figure jamais.

**Ce que cette clause autorise, et je le dis pour qu'on ne l'interdise pas par exces de prudence** :
le dessin `figure-triangle-rectangle`, deja servi au CP, **peut etre repris tel quel**. Il n'y a
aucune contradiction entre les deux paquets - les deux reponses sont vraies, a deux niveaux de
precision differents. C'est la cohabitation des deux **dans un meme item** qui est fautive.

> **`FIG-CE1-3` - Decrire : la propriete est celle qui distingue, et l'angle droit se voit.** Un item
> de description porte sur le nombre de cotes, l'egalite des cotes ou la presence d'angles droits.
> Quand l'angle droit est en jeu, il est **visiblement droit** sur le dessin, proportions declarees
> par le contenu (`FIG-1`), et aucune autre figure du meme item ne porte un angle proche de 90 degres
> sans l'etre : a la taille servie, un enfant ne discrimine pas 85 degres de 90.

### Ce que cette section ne contredit pas, et je le nomme plutot que de le laisser trouver

A s39, section 4, **j'ai refuse une description verbale comme substitut au verbe "reconnaitre"** pour
les solides (`MAT-CE1-16`) : *"reconnaitre un solide, c'est le voir et l'identifier ; une description
verbale qui enumere deja les proprietes definissantes fait a la place de l'enfant le travail
perceptif"*. **Ce refus tient, et `FIG-CE1-3` ne le contredit pas** : `MAT-CE1-16` ne porte **aucun**
verbe de description, `MAT-CE1-15` en porte un explicitement. Ce que j'avais refuse comme substitut
d'un verbe absent est ici le verbe lui-meme. La frontiere reste la meme des deux cotes : **une
description ne sert jamais "reconnaitre"**, ni au CE1 ni ailleurs.

### Mention de couverture, fixee d'avance

> **"reconnaitre le triangle rectangle et decrire des figures planes dessinees, seuls ; 'tracer a la
> regle et sur quadrillage' reste non couvert, aucune mecanique du produit ne recoit un trace"**

---

## 3. `MAT-CE1-17` - axe de symetrie et completement par symetrie sur quadrillage

**Libelle exact, verifie mot pour mot** : *"Reconnaître un axe de symétrie et compléter une figure
par symétrie sur quadrillage."*

### Verdict : **ACCEPTE SOUS CONDITIONS**, `SYM-CE1-1` a `SYM-CE1-3`

Les deux verbes sont servables par designation, et `couvert-valide` est atteignable - avec une
mention, parce que le second verbe est servi dans une forme retrecie que je nomme plutot que de la
laisser decouvrir.

> **`SYM-CE1-1` - L'axe se reconnait sur un dessin, jamais sur un texte.** Un item de reconnaissance
> d'axe montre une figure et des lignes candidates tracees dessus ; l'enfant designe celle qui est un
> axe de symetrie. Aucune formulation verbale ne remplace ce dessin.

**Motif, et c'est exactement celui de s39 sur les solides** : un axe de symetrie est une propriete
visuelle. Un enonce qui dirait *"la ligne qui partage la figure en deux parties superposables"*
donnerait la definition a la place du regard, et un enfant qui reussirait resterait incapable de voir
un axe sur une figure reelle.

> **`SYM-CE1-2` - Au moins une ligne candidate coupe la figure en deux parties de meme aire sans etre
> un axe de symetrie** - la diagonale d'un rectangle non carre est le cas type. Cette ligne est
> presente sur au moins la moitie des items de reconnaissance d'axe.

**Motif** : *"couper en deux"* n'est pas *"etre symetrique"*, et c'est **la** confusion que cette
competence existe pour corriger. Sans cette ligne candidate, l'item se reussit en comparant deux
moities a l'oeil, sans jamais penser au pliage. C'est la difference entre un exercice de symetrie et
un exercice de partage.

> **`SYM-CE1-3` - Completer par symetrie : une seule case manque par item, et elle ne se trouve pas
> par voisinage.** L'axe est trace sur le quadrillage, la figure est dessinee d'un cote et incomplete
> de l'autre, et **il manque exactement une case**. La case attendue n'est jamais la seule case vide
> touchant la figure : sinon l'enfant la trouve sans reflechir a la symetrie. Bornes de grille : celles
> de `DEP-CE1-2`, que je ne recopie pas ici.

**D'ou vient la restriction a une case, et pourquoi elle n'est pas une preference.** Je l'ai lue dans
le code avant de l'ecrire : `DesignationSession.jouer` resout la manche **sur une seule designation**,
`ZoneADesigner` declare **une** `bonneZone`, et `ZON-14` (*un item, un geste*) dit deja la meme chose
du cote pedagogique. Completer une figure a plusieurs cases demanderait une suite de gestes dans une
meme manche, ce qu'aucune mecanique ne fait - le meme constat que celui qui m'a fait refuser `S39-DEN`
a s39, et je le reverifie ici plutot que de le reciter.

**Ce que la restriction ne coute pas** : le raisonnement demande a l'enfant est entier. Reflechir une
case a travers un axe est exactement l'operation que la competence vise ; c'est la quantite qui est
retrecie, pas la nature. C'est pourquoi j'ouvre `couvert-valide` malgre elle, avec la mention
ci-dessous.

### Mention de couverture, fixee d'avance

> **"reconnaitre un axe de symetrie, et completer une figure par symetrie a raison d'une case par
> item ; completer une figure a plusieurs cases reste non couvert, aucune mecanique ne recoit une
> suite de gestes dans une meme manche"**

---

## 4. `MAT-CE1-18` - le sens de la division, partage et groupement

**Libelle exact, verifie mot pour mot** dans `squad/programme/mathematiques/competences-ce1.md`,
ligne ajoutee le 2026-09-21 : *"Comprendre le sens de la division en résolvant des problèmes de
partage et de groupement (sans technique opératoire posée)."* La parenthese n'est pas une decoration,
pas plus que celle de `MAT-CE1-03` : **elle exclut l'algorithme et designe le sens**, et deux de mes
cinq conditions en decoulent directement.

### Verdict : **ACCEPTE SOUS CONDITIONS**, `DIV-CE1-1` a `DIV-CE1-5`

**C'est la seule des quatre lignes de ce lot qui ne demande aucune image et aucune mecanique neuve.**
Des problemes en mots, trois propositions numeriques : la forme deja servie sept fois au CE1. Elle
peut atteindre `couvert-valide` sur un seul paquet, sans mention de couverture partielle.

> **`DIV-CE1-1` - Bornes, et division exacte.** Le dividende est un entier de 4 a 60, le diviseur un
> entier de 2 a 10, et **la division tombe juste : aucun reste**.

**Motif du zero reste, et ce qu'il n'interdit pas pour toujours.** Le quotient avec reste est
`MAT-CM1-05` (*"effectuer une division euclidienne simple, quotient et reste"*), deja servie par
`bandit-manchot-division-posee-cm1`, et elle suppose la technique que ce libelle-ci exclut. Un
probleme a reste au CE1 (*"14 billes par paquets de 4"*) est pedagogiquement legitime, mais il demande
de **decider ce qu'on fait du reste**, c'est-a-dire une seconde competence par-dessus le sens. Je
l'ecarte du premier paquet ; si quelqu'un le veut plus tard, c'est un arbitrage neuf et date a me
demander, pas une lecture large de cette clause.

**Motif des bornes.** 60 et 10 tiennent le calcul dans le repertoire que `MAT-CE1-07` construit
(tables de 2, 5, 10, puis 3 et 4) : l'enfant doit pouvoir retrouver le resultat, sinon il devine.

> **`DIV-CE1-2` - Les deux sens sont servis, chacun sur au moins un tiers des items.** **Partage** :
> le nombre de parts est donne, on cherche la valeur d'une part (*"12 billes partagées entre 3
> enfants, combien chacun ?"*). **Groupement** : la valeur d'une part est donnee, on cherche le
> nombre de parts (*"12 billes par paquets de 3, combien de paquets ?"*).

**Motif** : le libelle nomme les deux, et ce sont **deux taches differentes pour l'enfant** meme si
c'est le meme calcul - la premiere distribue, la seconde compte des paquets. Un paquet qui n'en sert
qu'une ne ferme pas la ligne. Meme principe exactement que `MON-CE1-3`, qui impose un tiers d'items de
rendu de monnaie pour que le second verbe de `MAT-CE1-13` soit reellement servi.

> **`DIV-CE1-3` - Aucune ecriture operatoire, nulle part.** Chaque item est une situation en mots. Ni
> `:` ni le signe de division dans l'enonce, dans les propositions ou dans l'indice ; jamais un item
> de la forme `12 : 3 = ?`.

**Motif** : *"sans technique operatoire posee"* est dans le libelle. Une ecriture symbolique ferait
glisser l'item vers un fait de table (`MAT-CE1-07`) ou vers la division posee du CM1, et l'enfant
appliquerait un signe sans avoir compris ce qu'on partage.

> **`DIV-CE1-4` - Diviseur 2 : au plus un tiers des items, et jamais le meme travail que les
> moities.** Aucun de ces items n'emploie le mot *moitie* ni ne se reduit au fait de repertoire deja
> servi par `bandit-manchot-moities-ce1` (`MAT-CE1-03`, forme *"Moitié de n = ?"*).

**Motif, et c'est une collision que j'ai verifiee sur le paquet servi et non supposee.**
`ce1-moities.yaml` sert des items de la forme litterale *"Moitié de 18 = ?"* - un fait, sans
situation. Partager en deux est une division parfaitement legitime ; ce que cette clause empeche,
c'est qu'un item de `MAT-CE1-18` soit le meme travail que celui deja servi sous `MAT-CE1-03`, ce
qu'`ADR-0081` interdit par ailleurs - un item sert une ligne et une seule.

> **`DIV-CE1-5` - Distracteurs.** Au moins un distracteur par item correspond a une erreur
> identifiable : le **produit** au lieu du quotient (`a x b`), la **difference** au lieu du quotient
> (`a - b`), ou **le diviseur recopié** (`b`, le nombre de l'énoncé qui n'est pas le dividende :
> le nombre de parts dans un partage, la valeur d'une part dans un groupement). Tous les
> distracteurs sont des entiers strictement positifs, distincts entre eux et de la bonne reponse ; jamais un decimal, jamais un negatif ; si une formule
> coincide avec la bonne reponse ou avec un autre distracteur, un distracteur de remplacement est
> pris parmi les deux autres formes.

*Amendement du 2026-09-25 (section 11.7, Q2 de `theo-archi`).* Le troisième cas s'écrivait *"le
**quotient de l'autre sens** (repondre le nombre de parts quand on demande la valeur d'une part, ou
l'inverse)"*. Le nom était faux : lire la situation dans l'autre sens redonne `a / b`, la bonne
réponse. Ce que je voulais, c'est la parenthèse, et elle désigne `b`. Seul le troisième cas change
de mots, pas de valeur.

**Motif du troisieme cas** : c'est l'erreur specifique de cette competence. Un enfant qui confond
partage et groupement repond *"4 paquets"* quand on lui demande *"combien par paquet"* - la confusion
que `DIV-CE1-2` fait travailler, retournee en distracteur. *Amendement du 2026-09-25 : cet exemple
ne donnait pas sa situation, donc pas sa valeur. Écrit en entier : "12 billes partagées entre 3
enfants, combien chacun ?" attend 4, et l'enfant qui répond le nombre de parts répond 3 ;
"12 billes par paquets de 3, combien de paquets ?" attend 4, et l'enfant qui répond la valeur d'une
part répond 3. Dans les deux sens, le distracteur vaut `b`.*

---

## 5. Collisions avec les sept jeux CE1 deja `couvert-valide` - verifiees une par une

Je les passe toutes, y compris celles dont la reponse est evidemment non, parce qu'une collision non
cherchee est une collision non trouvee.

| Jeu servi | Ligne | Collision avec mon lot | Verdict |
|---|---|---|---|
| `bandit-manchot-additions-ce1` | `MAT-CE1-03` | aucune : addition contre geometrie ou division | **non** |
| `bandit-manchot-soustractions-ce1` | `MAT-CE1-03` | aucune ; la soustraction reiteree n'est servie nulle part comme forme de division | **non** |
| `bandit-manchot-complements-dizaine-ce1` | `MAT-CE1-03` | aucune | **non** |
| `bandit-manchot-moities-ce1` | `MAT-CE1-03` | **oui**, avec `MAT-CE1-18` : la moitie est une division par 2 | **traitee par `DIV-CE1-4`** |
| `bandit-manchot-monnaie-ce1` | `MAT-CE1-13` | risque theorique d'un partage de somme ; verifie sur le paquet servi : les 12 items portent composition et rendu, aucun partage | **non** |
| `bandit-manchot-longueurs-ce1` | `MAT-CE1-09` | aucune avec mon lot ; `MES-CE1-1` a `-3` ne touchent ni la geometrie ni la division | **non** |
| `bandit-manchot-solides-ce1` | `MAT-CE1-16` | **oui**, avec `MAT-CE1-15` : figure plane contre solide | **voir ci-dessous** |

**La collision `MAT-CE1-15` / `MAT-CE1-16`, et elle est deja a moitie fermee.** `FIG-4`, opposable
depuis le 2026-08-24 et verifiee tenue sur le paquet de CP, interdit deja de confondre une figure
plane et un solide. Elle se lit maintenant dans les deux sens : le paquet de solides du CE1 est deja
audite sur ce point (`SOL-CE1-1`, l'ellipse d'equateur de la boule, justement posee pour qu'un cercle
ne passe pas pour une boule) ; un futur paquet de figures planes CE1 **ne reprend aucun dessin de
`ce1-solides.yaml` et ne propose jamais un nom de solide en distracteur**. Je le note sans en faire
une condition neuve : `FIG-4` le dit deja, et un second exemplaire d'une clause rancit.

**Et une collision hors CE1, avec un jeu `en cours` que je dois nommer** :
`bandit-manchot-deplacements-quadrillage-cp` (`MAT-CP-10`, `en cours` depuis le 2026-09-24) sert la
moitie *decrire un deplacement*. `MAT-CP-10` et `MAT-CE1-14` portent **toutes deux** le verbe *se
reperer* : la frontiere n'est pas dans le verbe, elle est dans le support. Je la fixe ici, decidable
sur l'item seul : **un item de `MAT-CE1-14` est pose sur une grille d'au moins 4 x 4 montree a
l'ecran, avec un chemin d'au moins deux pas** (`DEP-CE1-1`, `DEP-CE1-2`). Aucun item du paquet de CP
ne remplit cette condition - je l'ai verifie, ils sont tous sur une bande de cinq cases numerotees,
un seul pas - donc aucun risque de double comptage.

---

## 6. Collisions possibles avec les dix lignes des autres lots - signalees, non jugees

Je ne juge aucune de ces lignes : une autre instance les traite. Je signale ce que je vois depuis mon
lot, pour que la frontiere soit posee par qui de droit plutot que decouverte a l'audit.

| Ligne signalee | Avec quelle ligne de mon lot | Ce que je signale |
|---|---|---|
| `MAT-CE1-06` (sens de la multiplication, addition reiteree, lignes et colonnes) | `MAT-CE1-18` | **La plus proche des dix.** Le groupement (*"combien de paquets de 3 dans 12 ?"*) est la multiplication a trou (*"3 x ? = 12"*) vue de l'autre cote. Un meme item peut se declarer des deux cotes ; `ADR-0081` n'en verifie que la resolution, jamais la verite. Une frontiere explicite est due entre les deux lots |
| `MAT-CE1-07` (tables de 2, 5, 10, puis 3 et 4) | `MAT-CE1-18` | Un probleme de division dont le calcul est un fait de table peut se lire comme un item de table habille. `DIV-CE1-3` ferme le cote de l'ecriture ; le cote de la declaration de ligne reste a arbitrer entre les deux lots |
| `MAT-CE1-08` (problemes a une ou deux etapes) | `MAT-CE1-18` | Le libelle de `-08` nomme addition, soustraction et multiplication, **jamais la division** : la frontiere est propre par construction. Elle se rouvre sur un probleme a deux etapes dont l'une serait une division - cas a trancher par le lot qui porte `-08` |
| `MAT-CE1-02` (comparer, ranger, encadrer jusqu'a 1000, **et les placer sur une ligne graduee**) | aucune de mon lot | Signale pour une autre raison : *"placer sur une ligne graduee"* est de la meme famille instrumentee que *"mesurer avec une regle graduee"*, que j'ai plafonnee a `en cours` a s39 faute de rendu. Le lot qui porte `-02` aura la meme question, et ma reponse de s39 est le precedent |
| `MAT-CE1-11` (lire l'heure sur une horloge) et `MAT-CE1-12` (calculer une duree) | aucune de mon lot | Meme famille instrumentee que ci-dessus. `MATRICE-couverture.md` note deja `-11` comme non servable *"faute de ce rendu"* ; la section 0.1 de ce document change la donne, une horloge etant un dessin a zones designables. Je le signale, je ne le juge pas |
| `MAT-CE1-01`, `-04`, `-05`, `-10` | aucune de mon lot | Verifie : aucune intersection avec la geometrie ni avec le sens de la division |

---

## 7. Ce dont je reste la seule garde, et qu'aucun automate ne verra

**Toutes les conditions de ce document sont decidables sur l'item seul ou sur la liste des items**,
donc automatisables sur le patron d'`ADD-CE1-1`/`ADD-CE1-2`. **Tant qu'aucun garde-fou ne les
execute, c'est moi qui les tiens, en comptant et en jouant.** S'y ajoutent, et je les nomme pour que
l'equipe ne croie pas couvert ce qui ne l'est pas :

1. **Qu'un dessin de quadrillage soit lisible a la taille servie** (`ZON-9`) : que la case de depart
   marquee se voie, que les traits de la grille ne se confondent pas avec la figure posee dessus.
   Aucun automate ne le verra ; le garde-fou de cible tactile compte des pixels, pas des dessins.
2. **Qu'un axe de symetrie trace soit reellement un axe**, et qu'une ligne candidate refusee soit
   reellement refusable. C'est une verification mathematique dessin par dessin, exactement comme le
   rapport d'aretes cube / pave droit a s39.
3. **Que l'angle droit d'un triangle rectangle se voie a 176 px**, et qu'aucun angle voisin ne soit
   proche de 90 degres sans l'etre (`FIG-CE1-3`).
4. **Que le contexte d'un probleme de partage n'exclue personne.** Partager un gouter, des billes,
   des cartes : les situations de partage sont le terrain classique des presupposes de fratrie,
   d'argent de poche et de configuration familiale. Rien ne le verifie, et je le regarderai item par
   item.
5. **Que l'item travaille la ligne qu'il declare**, et pas une voisine plus facile - la garde de
   toujours, et sur ce lot elle porte surtout sur `MAT-CE1-15` (ne pas rejouer `MAT-CP-11`) et sur
   `MAT-CE1-18` (ne pas rejouer les moities).
6. **`LON-CE1-1` et `LON-CE1-2`** restent dues par les quatre lignes, y compris sur le champ `enonce`
   d'un item de designation. Aucun garde-fou ne les execute a ce jour.
7. **L'indice**, sur les quatre lignes. Un indice qui reformule la question n'apprend rien : c'est le
   point sur lequel j'ai refuse le plus souvent, et un paquet de designation n'y echappe pas.

---

## 8. Ce que je route, et a qui

| Fait | A qui | Pourquoi ce n'est pas a moi |
|---|---|---|
| Les quinze conditions de ce document sont decidables par item ou sur la liste, donc automatisables | `theo-archi`, via `sofia-sm` | Je dis la propriete ; la forme du test lui appartient |
| `DEC-0099`, section 4 bis, decrit le palier 2 comme absent du produit ; il est servi depuis s35 et deux lignes ont ferme dessus (section 0.1) | `camille-po`, via `sofia-sm` | C'est sa decision, et une correction s'ecrit chez son auteur |
| Le plafond de 4 x 4 que `ZON-8` derive pour un quadrillage vaut 8 x 8 sur la feuille de style d'aujourd'hui (section 0.2) | `theo-archi`, via `sofia-sm` | Le chiffre est derive d'une regle CSS et d'un garde-fou qui sont a lui ; ma borne pedagogique de 6 x 6 reste la mienne |
| `MAT-CE1-15` ne peut pas atteindre `couvert-valide` faute d'interaction de trace ; construire ou non cette interaction est un investissement | `theo-archi` | Decision d'architecture, explicitement hors de ma main |
| La frontiere `MAT-CE1-06` / `MAT-CE1-18` (section 6) | `camille-po`, via `sofia-sm` | Elle enjambe deux lots d'arbitrage ; je produis le motif, elle arbitre le perimetre |
| Les quinze identifiants sont alloues dans `squad/decisions/REGISTRE-identifiants.md`, section 4, dans le meme commit que ce document | moi-meme, fait | `PAR-5`, `ADR-0085` clause 2, `ADR-0100` |

---

## 9. Ce que ce document ne fait pas

- Il ne prononce aucun Done. Les quatre lignes restent `non couvert` jusqu'a un Done pedagogique que
  je prononcerai **apres avoir joue le jar servi**, jamais avant.
- Il ne fixe aucun tarif et ne rend aucun avis de faisabilite : `DEC-0099`, section 2, note qu'aucun
  tarif ligne a ligne publie par moi n'existe pour ces lignes, et ce document n'en publie pas.
- Il ne choisit aucun mecanisme technique, aucun format de dessin, aucune capacite de sprint.
- Il ne juge aucune des dix lignes des autres lots : la section 6 signale, elle n'arbitre pas.
- Il ne rouvre ni `ZON-8` dans son fond (je la re-derive, je ne l'amende pas), ni mon refus du texte
  seul pour les solides (`ARBITRAGE-PREALABLE-s39`, section 4, confirme en section 2), ni `DEP-CP-R1`
  a `DEP-CP-R4`, qui restent des reserves du paquet de CP et ne deviennent pas des conditions de CE1
  par ricochet.
- Il ne garantit `couvert-valide` sur aucune ligne : `MAT-CE1-15` est plafonnee `en cours` par son
  verbe *tracer* ; `MAT-CE1-14` l'est tant que le quadrillage n'est pas montre ; `MAT-CE1-17` et
  `MAT-CE1-18` peuvent l'atteindre, sous reserve d'un paquet qui tient les conditions ci-dessus.

## 10. Allocation des identifiants, et le perimetre de mon compte

Les quinze identifiants - `DEP-CE1-1` a `-4`, `FIG-CE1-1` a `-3`, `SYM-CE1-1` a `-3`, `DIV-CE1-1` a
`-5` - sont alloues dans `squad/decisions/REGISTRE-identifiants.md`, section 4, **dans le meme diff
que ce document**. Forme reprise a l'identique de `ADD-CE1-1` et `SOU-CE1-2`, deja allouees : trois
segments, prefixe, niveau, rang, **aucun marqueur de reserve** - ce ne sont pas des reserves, ce sont
des conditions.

**Deux prefixes sont neufs, `SYM` et `DIV`**, verifies libres sur le depot entier avant d'ecrire, et
pas seulement dans la table de la section 4. `DEP` et `FIG` existaient deja, sur les paquets de CP des
memes familles.

**Perimetre de mon compte, et ce n'est pas une precaution abstraite : je l'ai constate.** Une autre
instance a ecrit ses propres allocations dans le meme fichier pendant que j'ecrivais les miennes -
trente et une lignes pointant vers
`squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`, vues sur le worktree entre ma
mesure et mon commit. **Je commite donc mon seul diff** : les quinze allocations de ce document et
rien d'autre, sur la base du dernier commit de `round-two` (`5f6ba3d7`), sans toucher au travail en
vol de ma voisine. Mon compte - **712 / 115** - est juste sur ce diff-la et sur lui seul ; a
l'assemblage des trois lots, la reparation est **arithmetique** - aucune allocation ajoutee, aucune
retiree, aucun sens touche, exactement le cas que `ADR-0085` clause 5 prevoit et que ce registre a
deja paye trois fois. **Le chiffre que je porte sur la ligne d'etat courant est celui que le
garde-fou a affiche sur mon arbre** (`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, dix
tests, vert apres la mise a jour de la ligne), **jamais un chiffre que je recalcule de tete.**

---

## 11. Addendum du 2026-09-25, avant s44 - la frontière `MAT-CE1-06` / `MAT-CE1-18`, `DIV-CE1-6`

**Ce qui m'est posé.** La frontière que j'ai signalée en section 6, et que le lot de `MAT-CE1-06`
signale de son côté (`ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`, section 8, ligne
`MAT-CE1-18`), a été routée par `DEC-0099`, section 9.8, à `sofia-sm`, puis à `theo-archi`. Celui-ci
décline de la trancher, au motif que dire quelle ligne un item sert est une décision pédagogique.
Son analyse m'a été transmise par `sofia-sm` ; elle n'est écrite nulle part dans le dépôt, je
reprends donc ici les trois formes qu'elle laisse ouvertes :

- **G1** : un réseau dessiné en lignes et colonnes, le total donné, un facteur demandé ;
- **G2** : une addition réitérée écrite dont le nombre de termes est inconnu (*"3 + 3 + 3 + 3 = 12,
  combien de fois 3 ?"*) ;
- **le cas nu** : *"Combien de fois 3 dans 12 ?"*, ni signe ni objets.

Je reprends aussi, sans la rejuger, sa lecture des deux cas déjà fermés : `3 x ? = 12` n'est
admissible nulle part (`DIV-CE1-3`, `MUL-CE1-2`, `TAB-CE1-5`), et le groupement en mots n'est
admissible que sous `MAT-CE1-18` (`MUL-CE1-2`). Je l'ai vérifiée sur les trois clauses avant de la
reprendre.

### 11.1 Deux corrections de mes propres textes, faites avant d'arbitrer

**Ma section 8 routait cette frontière au mauvais destinataire.** Elle l'envoyait à `camille-po`
(*"je produis le motif, elle arbitre le périmètre"*). Le périmètre d'un sprint est à elle ; la ligne
qu'un item sert ne l'est pas, c'est une question de compétence, donc la mienne. `theo-archi` a
raison de me la renvoyer. La ligne de la section 8 reste telle quelle ; c'est cette section qui la
corrige.

**`DIV-CE1-3` dit deux choses de portée différente, et les deux sont de moi.** Son titre dit
*"Aucune écriture opératoire, nulle part"* ; la liste qui suit ne nomme que `:` et le signe de
division. Le titre et le motif (*"une écriture symbolique ferait glisser l'item vers un fait de
table"*) sont ce que je voulais dire : le motif vaut pour `+` autant que pour `:`. La liste était
plus étroite que le titre parce que je n'avais en vue que les signes de division. `DIV-CE1-6`
l'écrit en entier. **Ce n'est pas un poteau déplacé** : aucun paquet de `MAT-CE1-18` n'existe, aucune
équipe n'a atteint la barre de `DIV-CE1-3`, et rien de ce qui était admis ne devient refusé
rétroactivement.

### 11.2 La clause

> **`DIV-CE1-6` - Frontière avec `MAT-CE1-06` : un item de `MAT-CE1-18` est une situation en mots,
> sans dessin et sans signe.** Trois propriétés, chacune décidable sur l'item seul :
>
> 1. **Aucun support visuel.** L'item ne porte ni champ `illustration` ni champ `tableauSupport` :
>    ni réseau en lignes et colonnes, ni paquets dessinés, ni collection d'objets en vrac, ni
>    grille, quel que soit le champ qui la porte.
> 2. **Aucune écriture opératoire, dans aucun texte de l'item** : l'énoncé, la bonne réponse, les
>    distracteurs, l'indice, l'explication, le texte-support et le texte à voix haute. Aucun des
>    signes `+`, `-`, `x`, `×`, `:`, `÷` employé comme opération entre deux nombres, et aucun signe
>    `=`. Une suite de nombres qui compte de 3 en 3 (*"3, 6, 9, 12"*) n'est pas une écriture
>    opératoire et reste permise dans l'indice et dans l'explication.
> 3. **Le dividende est une quantité d'objets nommés** (*"12 billes"*, *"20 cartes"*), jamais un
>    nombre nu.

*Amendement du 2026-09-25 (section 11.7, Q3 de `theo-archi`).* Points 1 et 2 élargis. Leur texte
d'origine : point 1, *"**Aucune illustration.** L'item ne porte aucun champ `illustration` : ni
réseau en lignes et colonnes, ni paquets dessinés, ni collection d'objets en vrac."* ; point 2,
*"**Aucune écriture opératoire, dans l'énoncé, les propositions ou l'indice.**"*, le reste
inchangé sauf *"reste permise dans l'indice"*, étendu à l'explication. Le point 3 ne change pas.

Ce que la clause range, forme par forme :

| Forme d'item | Sous `MAT-CE1-06` | Sous `MAT-CE1-18` |
|---|---|---|
| `3 x ? = 12` | exclue (`MUL-CE1-2`) | exclue (`DIV-CE1-3`, `DIV-CE1-6` point 2) |
| **G1** - réseau dessiné, total donné, facteur demandé | admise, sous `MUL-CE1-2` et `MUL-CE1-5` | **exclue** (point 1) |
| **G2** - *"3 + 3 + 3 + 3 = 12, combien de fois 3 ?"* | admise, sous `MUL-CE1-2` et `MUL-CE1-3` | **exclue** (point 2) |
| **Cas nu** - *"Combien de fois 3 dans 12 ?"* | exclue (`MUL-CE1-2` : ni addition écrite ni dessin) | **exclue** (point 3) |
| Partage ou groupement en mots - *"12 billes par paquets de 3, combien de paquets ?"* | exclue (`MUL-CE1-2`) | admise |

### 11.3 Motifs

**Point 1, G1.** Dans un réseau dessiné complet, le facteur demandé **se compte sur le dessin** :
l'enfant lit une disposition, il ne forme aucun paquet et ne distribue rien. C'est le verbe de
`MAT-CE1-06` (*"disposition en lignes et en colonnes"*), pas celui de `MAT-CE1-18`. Des paquets déjà
dessinés ont le même défaut : la réponse se compte. **Une collection en vrac, je ne l'ai pas
examinée** : la mécanique servie ne permet pas d'entourer des objets, et je n'admets pas une forme
que je n'ai pas regardée. Un item illustré de `MAT-CE1-18` est donc un arbitrage neuf à me demander,
pas une lecture de cette clause.

**Ce que devient l'exemple du lot 1** (section 8, ligne `MAT-CE1-18` : *"36 gommettes en 6 rangées,
combien par rangée ?"*). Sur un dessin, il n'est pas de `MAT-CE1-18` (point 1), et sous
`MAT-CE1-06` il enfreint `MUL-CE1-5`, parce que l'énoncé dit le nombre de rangées. Sans dessin,
c'est un partage en mots, admis sous `MAT-CE1-18` (36 et 6 tiennent `DIV-CE1-1`). La collision que
ce lot nommait se ferme donc sur la présence du dessin.

**Point 2, G2.** Compter les termes d'une addition écrite, c'est lire la réitération : la première
représentation que nomme `MAT-CE1-06`. Sous `MAT-CE1-18`, l'enfant recevrait le paquet déjà fait et
n'aurait plus qu'à compter des signes, exactement le glissement que le motif de `DIV-CE1-3` nomme.

**Point 3, le cas nu.** *"Combien de fois 3 dans 12 ?"* est la lecture à voix haute de `12 : 3` :
un fait numérique mis en mots, pas une situation. `DIV-CE1-3` demande une situation ; celle-ci n'en
est pas une. Elle n'est admissible sous aucune des deux lignes aujourd'hui. Relier ce sens au signe
est un travail légitime plus tard ; qui voudrait le servir m'ouvre un arbitrage sur la ligne qui le
porterait, pas une lecture de cette clause.

### 11.4 Les deux côtés sont disjoints par construction

`MUL-CE1-2` impose à tout item de `MAT-CE1-06` de montrer une addition réitérée écrite ou une
disposition dessinée ; `DIV-CE1-6` interdit l'une et l'autre à tout item de `MAT-CE1-18`. **Aucun
item ne peut tenir les deux lignes à la fois**, et le discriminant se lit sur l'item sans juger
l'intention de son auteur : il y a un dessin ou un signe, ou il n'y en a pas. **`DIV-CE1-6`
n'ajoute aucune contrainte à `MAT-CE1-06`** : un paquet qui tient `MUL-CE1-1` à `MUL-CE1-7` ne s'y
heurte pas.

### 11.5 Effet sur le paquet de s44, et ce que je route

Un paquet de `MAT-CE1-18` entièrement textuel tient les points 1 et 2 par construction. **Le point 3,
lui, contraint réellement l'écriture** : *"Combien de paquets de 3 peut-on faire avec 12 ?"* est
exclu tant que 12 ne compte rien de nommé ; *"Avec 12 billes, combien de paquets de 3 ?"* est admis.
Je le dis pour que le développeur affecté ne le découvre pas à l'audit.

| Fait | À qui | Pourquoi ce n'est pas à moi |
|---|---|---|
| Les points 1 et 2 sont décidables mécaniquement (absence d'un champ, absence de signes entre nombres) ; le point 3 l'est à la lecture, sa forme automatisable reste à trouver | `theo-archi`, via `sofia-sm`, dans le même diff que le premier paquet de `MAT-CE1-18`, comme il l'a proposé | Je dis la propriété ; la forme du test lui appartient |

**Tant que ce garde-fou n'existe pas, c'est moi qui tiens les trois points, en jouant.** Pour le
point 3, je le resterai tant que `theo-archi` n'aura pas dit s'il sait l'encoder.

### 11.6 Ce que cet addendum ne fait pas

Il ne prononce aucun Done et ne touche ni aux sections 0 à 10 de ce document, ni à
`ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`, dont aucune condition ne change. Il n'écrit
aucun garde-fou et aucune story.

**Allocation.** `DIV-CE1-6` est alloué dans `squad/decisions/REGISTRE-identifiants.md`, section 4,
dans le même diff que cet addendum. Forme copiée de `DIV-CE1-5` : trois segments, aucun marqueur
de réserve, parce que c'est une condition. Le rang 6 a été vérifié libre dans la table de la
section 4, sur l'arbre entier et sur les trois branches `hs44-*` en vol avant d'écrire.

### 11.7 Addendum du 2026-09-25, suite - `DIV-CE1-5` troisième cas et périmètre de `DIV-CE1-6`

**Ce qui m'est posé.** `theo-archi`, dans son avis de faisabilité de `S44-MAT-DIVISION`
(`squad/rapports/avis-faisabilite-s44-mat-division.md`, section 7), me route deux questions, Q2 et
Q3. J'y réponds ici et j'applique la réponse aux deux clauses, en section 4 et en section 11.2.
Aucun paquet de `MAT-CE1-18` n'existe : aucune barre atteinte ne bouge, et rien de ce qui a été
admis ne devient refusé.

**Une correction de mon propre constat d'abord.** La section 11.5 dit qu'*"un paquet de
`MAT-CE1-18` entièrement textuel tient les points 1 et 2 par construction"*. C'est vrai du point 1,
faux du point 2 : *"3 + 3 + 3 + 3 = 12"* est du texte. Un paquet sans image tient le point 1 par
construction ; le point 2 se vérifie texte par texte. La phrase de la section 11.5 reste écrite ;
c'est celle-ci qui la corrige.

#### Q2 - le troisième distracteur de `DIV-CE1-5` vaut `b`

**Réponse : le diviseur recopié tel quel**, c'est-à-dire le nombre de l'énoncé qui n'est pas le
dividende. Dans un partage, c'est le nombre de parts ; dans un groupement, la valeur d'une part.
Jamais une autre valeur calculée. La lecture de `theo-archi` est la bonne, et l'ensemble de son
test, `{a x b, a - b, b}` privé de la bonne réponse, est exactement la condition.

**Pourquoi mon nom était faux.** Dans `a = q x b`, l'enfant manipule deux nombres qui jouent l'un
contre l'autre : le quotient cherché `q` et le nombre donné `b`. Confondre partage et groupement,
c'est répondre l'un à la place de l'autre, donc donner `b`. Je l'avais appelé *"quotient"* parce
que, dans l'item symétrique, `b` serait la réponse ; mais dans cet item-ci, recalculer dans l'autre
sens redonne `a / b`. La parenthèse de la clause disait la bonne chose, le nom non.

**Deux coïncidences, que la règle de remplacement de la clause couvre déjà** :

- `a = b x b` (4 et 2, 9 et 3, 16 et 4, 25 et 5, 36 et 6, 49 et 7) : `b` est la bonne réponse et ne
  peut pas être un distracteur. Sur ces items, la confusion des deux sens ne se voit pas ; je ne les
  interdis pas, faute de les avoir examinés comme items, et je les regarderai à l'audit.
- `a = 2 x b` : `a - b` vaut `b`, les deux formules ne donnent qu'un distracteur.

**La condition est toujours tenable** : `a x b` est au moins `2 x a`, donc différent de `a / b` et de
`b` pour tout item qui tient `DIV-CE1-1`. Un item a toujours au moins un distracteur identifiable.

#### Q3 - `DIV-CE1-6` s'étend, l'omission n'était pas voulue

**Réponse : j'étends les points 1 et 2** (texte en section 11.2). Ce n'est pas un choix de prudence :
c'est la deuxième fois, sur la même ligne, qu'une de mes listes est plus étroite que son titre. Le
titre de `DIV-CE1-6` dit *"sans dessin et sans signe"*, et j'avais nommé des champs au lieu de nommer
la situation. `theo-archi` a raison : nommer un champ n'est pas nommer une situation.

**Point 1, `tableauSupport`.** `ADR-0099` en fait une grille en lignes et colonnes. Elle peut
dessiner le réseau G1, et le motif de la section 11.3 s'y applique tel quel : la réponse s'y compte.
Une grille de données (une liste d'enfants et de billes, par exemple), je ne l'ai pas examinée pour
cette ligne, et je n'admets pas une forme que je n'ai pas regardée. Le point 1 exclut donc le champ
entier ; un item de `MAT-CE1-18` avec un support visuel, quel qu'il soit, est un arbitrage neuf à me
demander. `illustrationTexteAlternatif` n'a pas besoin d'être nommé : `ADR-0073` clause 5 l'interdit
sans illustration.

**Point 2, `explication`.** Elle s'affiche après chaque réponse, juste ou fausse (`ADR-0024`) :
l'enfant la lit. *"12 : 3 = 4"* à cet endroit relie le sens au signe, ce que la section 11.3 réserve à
un arbitrage futur ; *"4 + 4 + 4 = 12"* y donne la forme G2. Les deux sont exclus. Une explication qui
dit la situation en mots (*"3 enfants, 4 billes chacun : cela fait bien 12 billes."*) reste admise, et
une suite qui compte (*"4, 8, 12"*) aussi, comme dans l'indice.

**Point 2, `texteSupport` et `texteAVoixHaute`.** Même motif : ce sont des textes que l'enfant lit ou
entend, et le signe y ferait le même travail que dans l'énoncé. Le point 2 les couvre s'ils sont
présents. Je ne dis pas ici s'ils peuvent l'être : un texte à voix haute sur une ligne où la voix
n'est pas la compétence relève de l'arbitrage de l'utilisatrice du 2026-09-01 (`CLAUDE.md`, section
*L'enfant de CP n'est jamais seul*), pas de cette clause, et un texte-support n'a aucun usage que
j'aie examiné sur cette ligne.

**Ce qui reste hors du point 2.** `partDesignee` est une sous-chaîne de l'énoncé (`ADR-0062`) : elle
est couverte par l'énoncé. Le point 3 ne change pas : il porte sur le dividende, qui vit dans
l'énoncé.

#### Ce que je route, et ce dont je reste la garde

| Fait | À qui | Pourquoi ce n'est pas à moi |
|---|---|---|
| Le test du paquet de `MAT-CE1-18` étend l'assertion du point 1 à `tableauSupport`, et celle du point 2 à `explication`, `texteSupport` et `texteAVoixHaute`, dans le même diff que le paquet | `theo-archi` et le développeur affecté, via `sofia-sm` | La forme du test leur appartient |
| Les critères d'acceptation de `S44-MAT-DIVISION` citent `DIV-CE1-5` dans sa forme amendée et `DIV-CE1-6` dans son périmètre élargi (Q1 de l'avis, déjà routée) | `camille-po`, via `sofia-sm` | Rédaction de story |

**Ce dont je reste la seule garde sur ces deux clauses, en jouant** : que la marque de sens de
`DIV-CE1-2` dise le sens réel de l'item (R4 de l'avis), que le mot qui suit le dividende nomme des
objets (point 3, R5), et que le distracteur `b` soit plausible dans la phrase servie. Tout le reste
de `DIV-CE1-5` et de `DIV-CE1-6` est encodable, et le sera par le test du paquet.

**Allocation.** Aucun identifiant neuf, aucun renommé : `DIV-CE1-5` et `DIV-CE1-6` sont déjà alloués
dans `squad/decisions/REGISTRE-identifiants.md`, section 4, et leur texte change, pas leur nom.
