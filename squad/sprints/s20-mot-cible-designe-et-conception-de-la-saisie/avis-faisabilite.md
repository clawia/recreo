# Sprint 20 - avis de faisabilite de `theo-archi`

- **Auteur** : theo-archi. **Date** : 2026-08-25. **Branche** : `s20-theo`, ouverte sur `round-two`
  a `c48c0e6`.
- **Perimetre lu avant d'ecrire** : `s20-camille` (`82de824`, `e8f1c92` - `DEC-0047` et son
  amendement, `S20-1`, `S20-2`, `S19-6a`, `S19-6b`), `hs-nadia-arbitrages` (`9707538` - troisieme
  amendement de l'arbitrage prealable de la saisie), `hs-ines-nat5` (`e5a8360` - proposition
  `.mot-designe` et sa mesure). Aucune de ces trois branches n'est fusionnee ; je les lis, je ne
  les fusionne pas.
- **Ce que je ne fais pas** : aucune decision de produit ni de pedagogie, aucun Done, aucune
  valeur de plafond en pixels, aucune regle CSS, aucune priorisation de story.

## 0. Mesure de reference, avec sa methode

Toute mesure de ce document est prise sur `s20-theo` a `c48c0e6`, arbre propre, avant toute
ecriture de ma part.

- **Reacteur complet** : `bash outils/mvn-jdk21.sh -B clean verify` a la racine - **BUILD SUCCESS,
  668 tests, 0 echec**. Pas de `-am` a citer : le reacteur est complet par construction.
- **Repartition par module**, lue sur les lignes de recapitulation de surefire et de failsafe du
  meme journal : 48 `domain` + 13 `application` + 328 `adapter-web` + 181 `adapter-content` +
  92 `bootstrap` surefire + 6 `failsafe` = **668**.
- **Machine virtuelle des tests** : JDK **21.0.10**, celui de la toolchain declaree, lu dans le
  journal du build (`Detected Java version 21.0.10`). Le `java` du `PATH` de ce poste n'est pas
  celui-la, et c'est pour cela que la commande passe par l'enveloppe du depot.
- **Compte des regles ArchUnit** : **30 regles reelles**, inchange depuis `ADR-0059`. Methode :
  balayage des sources `.java` du worktree, `target/` exclu, comptage des **declarations de champ**
  `ArchRule` - 35 lignes portent la sous-chaine `ArchRule `, dont **3** dans le faux depot de
  contre-exemple de `LesGardeFousSontMisALEpreuveTest` (jamais executees comme regles) et **2** dans
  `LesGardeFousSontMisALEpreuveTest` lui-meme qui ne sont pas des declarations (une constante de
  chaine et un texte de message). 35 - 3 - 2 = 30.

## 1. `S20-1` - le mot cible de "Nom ou verbe ?"

**Verdict : faisable, moyennant un ADR - `ADR-0062`, ecrit dans le meme lot que cet avis. Le
premier geste est un changement de contrat de contenu, pas une feuille de style.**

### 1.1 Je partage le refus du contournement d'`ines-design`, et pour un motif plus dur que le sien

Elle refuse de faire deviner le mot cible au gabarit partage en parcourant les guillemets, au motif
que le gabarit generique connaitrait alors une convention de contenu que les jeux sans mot cible
n'ont jamais. Ce motif est juste. Il en existe un second, qui n'est pas une preference et qui suffit
a lui seul :

**la deduction par les guillemets ne peut pas honorer la decision de perimetre de `camille-po`.**
`cm1-synonymes` et `cm1-antonymes` portent la meme convention de guillemets que `cp-nature-mot` -
`ines-design` le mesure elle-meme, c'est ce qui lui permet d'injecter `.mot-designe` sur les trois.
Un gabarit qui marque ce qu'il trouve entre guillemets marquerait donc **les trois paquets**, alors
que `DEC-0047` decide que le renfort entre sur `cp-nature-mot` **seul** ce sprint, mesure de
regression a l'appui. La seule facon de tenir cette portee avec la deduction serait un aiguillage
par nom de paquet dans le code de rendu - c'est-a-dire la forme exacte de ce que ce depot refuse
depuis `ADR-0004`, et ce que `ADR-0061` vient de sanctionner encore le 2026-08-24 sous le nom de
recopie d'une association du catalogue.

Le refus n'est donc pas un arbitrage de gout entre deux formes propres : **une des deux ne peut pas
livrer ce que la story demande.**

### 1.2 La forme de contrat que je retiens - et ce n'est pas trois champs

`ines-design` **ne propose pas trois champs** ; elle propose un `<span class="mot-designe">` et
route explicitement la forme de la donnee. La formulation "distincte de l'avant et de l'apres de la
phrase" vient du critere d'acceptation de `S20-1`, pas d'elle. Je le note parce que trois champs est
justement la forme que je refuse.

**Un triplet `(avant, motCible, apres)` reproduit la dette 2 de la version precedente** : douze
paquets qui ne designent rien devraient remplir `motCible` et `apres` de valeurs vides, le gabarit
devrait tester cette vacuite, et le jeu non marque deviendrait le cas particulier - exactement ce
que `camille-po` demande d'eviter.

**La forme retenue, en une phrase** : `Question` gagne **une composante facultative et une seule**,
la part designee de l'enonce ; l'enonce lui-meme reste la chaine entiere qu'il est aujourd'hui ; le
controleur, et non le gabarit, derive de ces deux valeurs une **suite de segments** dont chacun dit
s'il est designe ; le gabarit fait une boucle unique. Un item qui ne designe rien rend **un** segment
non designe - la phrase telle qu'aujourd'hui, sans branche, sans valeur factice et sans cas
particulier. Le detail, les alternatives ecartees et la regle executable sont dans `ADR-0062`.

**Ce que cette forme coute, et je le nomme avant qu'on le decouvre** : ajouter une composante a
`Question` rend **rouge** `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`
(`chaqueComposanteDUnItemEstClasseeDansLIdentiteDEcranOuHorsDElle`, `adapter-content`). Ce rouge est
voulu : c'est le cliquet d'`ADR-0050`, qui refuse qu'une composante entre sans que personne ait dit
si l'enfant la voit. La reponse est **dans l'identite d'ecran** - deux items de meme enonce et de
memes propositions qui designent deux mots differents sont deux ecrans differents. Le lot le tranche
dans le meme commit ; c'est une ligne de classement, pas une reouverture.

### 1.3 Le point qui n'est pas qu'architectural, et ou je m'arrete

`camille-po` demande que le contrat serve un jeu marque et deux jeux non marques par le meme
gabarit. La forme ci-dessus le fait sans cas particulier : les deux jeux CM1 ne declarent rien, leur
enonce reste un segment unique, aucun `.mot-designe` n'apparait dans leur rendu. La regle executable
d'`ADR-0062` verifie **les deux moities** - la marque presente la ou l'item la declare, et **aucune
marque nulle part ailleurs**. C'est cette seconde moitie qui encode la portee decidee par
`DEC-0047` sans nommer aucun paquet.

Ce que la forme retenue **ne decide pas** : le choix des canaux visuels, la valeur de la regle CSS,
et la question de savoir si `NAT-5` est levee. `ines-design` propose, `nadia-prof` tranche.

### 1.4 Une consequence de sequencement que je dois nommer parce qu'elle est de moi

`ADR-0059` a ouvert le 2026-08-24 une dette datee, avec ce declencheur ecrit : *"le premier lot qui
touche un terme vertical du flux au-dessus du verdict - correctif de `S19-6` compris - porte cet
`ADR` et sa table"*. Le renfort `.mot-designe` **touche un terme vertical de ce flux** : son
`font-size: 1.15em` agrandit la boite de ligne de `.enonce`, et `ines-design` le mesure a +7,19 px
uniformement, jusqu'a +55,19 px quand un item bascule a deux lignes. `S20-1` declenche donc ma
propre dette.

**Et mon declencheur, ecrit tel quel, produirait un travail perime le jour de sa livraison** :
`S20-1` et `S19-6a` changent tous deux la mise en page verticale de cette zone, dans le meme sprint.
Une table epinglee par le premier des deux serait fausse des la fusion du second. Je corrige le
declencheur dans `ADR-0059` (amendement de ce lot) plutot que de laisser deux lots se renvoyer la
dette : la table est portee par **le dernier** des lots de s20 qui touche un de ces termes, et
aucun des deux n'est fusionne en declarant la dette fermee. Le point de controle appartient a
`sofia-sm` au planning.

## 2. `S20-2` - la competence des reperes, contenu seul

**Verdict : faisable en l'etat. Aucun ADR n'est du, aucune ligne de production Java n'est
touchee.** La forme est celle que la mecanique existante sert deja - une phrase, quatre propositions
courtes - et `nadia-prof` a instruit `REP-1` a `REP-4` avant tout code.

### 2.1 Les garde-fous dont cette story change le perimetre

**Cette liste n'est pas exhaustive, et je l'ecris parce que j'ai etabli a s19 que l'enumeration
complete des garde-fous d'une story n'est pas atteignable par construction** : le perimetre de la
plupart d'entre eux est **derive du repertoire de contenu** (`ADR-0013`, obligation 1), donc un
paquet neuf entre dans leur population par le seul fait d'exister, sans qu'aucune liste ne le
nomme. Ce qui suit sont ceux dont ce paquet-ci change le perimetre d'une facon qui demande une
**ecriture** dans le lot, pas ceux qui le jugeront.

1. **`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (`adapter-content`) - une ecriture est
   due.** Ce garde-fou tient une table nommee des paquets **hors du domaine numerique**, chacun avec
   le motif mesure de son exclusion (aujourd'hui `cm1-synonymes`, `cm1-accords-groupe-nominal`,
   `cm1-accord-sujet-verbe`, `cm1-antonymes`, `cp-nature-mot`, entre autres). Les propositions de
   `cm1-reperes` sont des phrases ("il manque un point"), donc aucune n'a de valeur numerique : le
   paquet doit entrer dans cette table avec son comptage reel, mesure sur le paquet servi, dans le
   commit qui livre le paquet. Sans cette ecriture, l'exclusion est silencieuse - c'est le premier
   des deux angles morts qu'`ADR-0041` existe pour fermer.
2. **`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` - juge sans ecriture, mais la forme du
   paquet le frole.** L'identite d'ecran est le couple **enonce + jeu de propositions, en ensemble**.
   `REP-4` fait porter aux items les **memes quatre propositions** ; deux items ne se distinguent
   donc que par leur phrase. C'est vert tant que deux phrases du paquet ne sont pas identiques, et
   rouge sinon - la marge est plus mince que sur un paquet dont les propositions varient. A dire a
   l'auteur du contenu avant qu'il ecrive, pas apres.
3. **`AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` - ne mord pas ce paquet, et j'explique
   pourquoi plutot que de le taire.** Sa detection est un motif litteral de conjugaison
   (`"Avec 'tu', le verbe se termine par '-es'"`). Aucun indice de reperes ne peut le declencher.
   Le fichier porte en outre un **cliquet a zero** : il assertionne que le nombre d'indices de
   production examines vaut exactement `0`, avec l'instruction de retirer le cliquet si ce chiffre
   monte. Si un indice de `cm1-reperes` prenait par hasard cette forme, la CI rougirait sur le
   cliquet et non sur la contradiction. Ce n'est pas un defaut : c'est un plafond a la taille exacte
   de la dette, la propriete que ce depot exige d'un cliquet.
4. **`ChaquePaquetPorteUnIndiceTest`, `AucunePropositionNEstEgaleAUneAutreDansUnItemTest`,
   `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`, `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest`,
   `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest`, `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest`,
   `AucuneEntreeDuCatalogueNEnDoubleUneAutreTest`** : perimetre derive, jugent le paquet des qu'il
   existe, aucune ecriture due. Les criteres de `S20-2` sont deja compatibles avec chacun.
5. **Ce qu'aucun de ces garde-fous ne dit**, et c'est la porte de `nadia-prof` seule : que
   `REP-1` a `REP-4` soient reellement tenus. Aucun test de ce depot ne compte les items par repere
   teste ni ne juge qu'une phrase est de niveau CM1. `S20-2` l'ecrit deja ; je le confirme du cote
   technique.

**Un point de vigilance qui n'est pas un garde-fou** : `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest`
juge la notation d'un caractere et l'espace insecable des guillemets francais. Il ne contraint pas
l'absence d'un point final. Un paquet dont **la matiere meme** est une phrase a qui il manque son
point ne se heurte donc a rien - je le verifie plutot que de le supposer, parce que c'etait le
premier risque a ecarter sur cette competence.

## 3. `S19-6a` - le plafond de largeur, avec le pire cas a 647 px

**Verdict : faisable en l'etat pour le mecanisme ; `ADR-0059` tient, mais deux de ses chiffres sont
perimes et ma propre phrase sur le cout d'une remesure etait fausse. Amendement ecrit dans ce lot.**

### 3.1 Ce que `ADR-0059` promettait et ce qu'il tient

Il promettait qu'une valeur de plafond puisse etre acceptee sans que l'`ADR` soit reecrit, parce que
la valeur est un parametre soumis a une seule inegalite : **`P >= L + R + 40`**, ou `L` est la
largeur de rangee mesuree necessaire et `R` la reserve de police que le produit choisit.

**Le mecanisme tient, verifie et non suppose.** La forme du correctif ne bouge pas, les deux
litteraux de bande ne bougent pas, la regle ne change pas d'un caractere, aucun selecteur ne bouge.
Sur ce point l'`ADR` a fait ce pour quoi il a ete construit.

### 3.2 Trois choses qui ne tiennent pas, et la troisieme est la plus couteuse

**a) L'inegalite ecarte la valeur proposee.** Avec `L = 647`, `P >= 687 + R`. La proposition de
**620 px** d'`ines-design`, calculee quand `L` valait 547, est **sous le plancher** : elle capte la
rangee a 580 px la ou `cm1-accord-sujet-verbe` en demande 647. Elle ne corrige donc **aucune** largeur
de la bande pour ce jeu. `DEC-0047` avait deja ecrit qu'elle n'etait plus confirmee ; l'arithmetique
dit plus que "non confirmee", elle dit **exclue**. La demande de `camille-po` d'une marge de police
non nulle se lit `R > 0`, donc `P > 687` strictement.

**b) La couverture annoncee est perimee, et l'ecart n'est pas cosmetique.** `ADR-0059` demontrait,
avec `L = 539`, que la couverture est binaire en `P` : **141 largeurs sur 144, ou zero**, les trois
pixels hors de portee etant 576, 577 et 578. La demonstration est intacte, ses chiffres ne le sont
pas. Avec `L = 647`, aucune valeur de `P` ne peut couvrir un viewport sous `687` px, parce que la
rangee y vaut au mieux `V - 40` : la couverture devient **33 largeurs sur 144** (687 a 719 px), ou
zero. **Le residu de bande passe de 3 px a 111 px**, soit 77 % de la bande que la story est chargee
de fermer.

C'est un fait de perimetre produit, pas une decision d'architecture, et je ne le tranche pas.
`S19-6a` porte deja le critere qui l'accueille (*"le residu de bande qui reste hors correctif [...]
est un residu assume et documente avec son motif"*), mais il a ete ecrit quand le residu valait trois
pixels. **`camille-po` et `nadia-prof` doivent savoir qu'elles arbitrent un residu de 111 px avant de
trancher la valeur**, pas apres. L'autre levier existe et n'est pas le mien : reduire `L` - c'est
la largeur **demandee** par la rangee de `cm1-accord-sujet-verbe` (famille `ADR-0055`), pas une
fatalite du viewport.

**c) Ma phrase sur le cout d'une remesure etait fausse, et je l'ai verifiee au lieu de la relire.**
`ADR-0059` ecrit : *"Aucune des deux ne touche le mecanisme ni le texte de la regle - elles changent
une ligne de constante chacune."* Faux. Porter `LARGEUR_DE_RANGEE_NECESSAIRE_PX` de 539 a 647 porte
`PLAFOND_MINIMAL_PX` de 579 a 687, et **le jeu fautif commite bascule avec** : son **cas vert**
declare `max-width: 640px`
(`adapters/adapter-web/src/test/resources/contreexemple/UnPlafondMaisonNAffamePasLaRangeeTest/plafond-maison-suffisant/`),
donc il devient **rouge** apres la remesure. Le cout reel est de **deux fichiers** - la constante et
la valeur du cas vert du contre-exemple - et le second n'est pas une correction mecanique : c'est le
cas vert **sans lequel les quatre morsures ne prouvent que la moitie**, comme l'`ADR` l'ecrit
lui-meme. Il faut le rechoisir en connaissance de cause, pas le remonter distraitement.

La production, elle, reste verte : les deux plafonds maison declares valent `720px`, au-dessus du
nouveau plancher de 687.

### 3.3 Une consequence de forme que la valeur haute entraine

Avec `P` autour de 687 px au lieu de 579, la zone cesse d'etre centree jusqu'a 687 px au lieu de 579.
La clause de `SYN-14` que `S19-6a` demande de ne pas faire regresser - *"aucun element a presser ne
se rapproche du bord plus que le rembourrage de la zone"* - a ete tenue **a un plafond de 579 px, sur
huit viewports mesures**. Elle n'a jamais ete mesuree sur une zone qui s'etale bord a bord jusqu'a
687 px. Ce n'est pas un obstacle d'architecture ; c'est une remesure due a `nadia-prof` avec la
valeur retenue, et elle n'est pas incluse dans les huit deja faites.

## 4. `S19-6b` - avis de faisabilite sur `BPM-8`

**Verdict : infaisable comme story ce sprint, et le motif n'est pas la capacite. Une mesure prise
aujourd'hui decrirait une mise en page que `S19-6a` va changer dans le meme sprint.**

C'est une demande d'avis, pas une story ; voici l'avis.

### 4.1 Le mecanisme n'est pas independant de celui de `S19-6a`, contrairement a ce que la scission supposait

`DEC-0043` a scinde `S19-6` en deux au motif que `BPM-8` relevait d'un *"mecanisme different : un
defaut de hauteur de contenu (l'indice, pas les propositions)"*. La distinction est juste sur le
symptome et **fausse sur la cause**.

L'indice est coupe par le pli parce que ce qui le precede dans le flux est trop haut. Ce qui precede
est haut parce que le texte se replie sur plus de lignes. Il se replie parce que la largeur offerte
dans la bande est petite : **500 px aujourd'hui**, quelle que soit la largeur du viewport, la feuille
tierce captant la zone a 540 px. Le correctif de `S19-6a` est precisement ce qui remplace ce 500 px
par `min(V, P) - 40`, soit jusqu'a 647 px avec le plancher calcule en section 3. Et le plafond maison
porte sur `.entete-contenu` et `.zone-jeu`, **selecteurs partages par tous les jeux** : "Le bon petit
mot" recoit ce changement en meme temps que les trois jeux nommes, sans qu'aucune ligne ne le vise.

**Consequence directe** : les 6 a 7 items sur 24 mesures coupes a 700 et 719 px l'ont ete a 500 px
offerts. Apres `S19-6a`, a 700 px, la zone offre 647 px. Le comptage n'a aucune raison de tenir, et je
ne prejuge pas de son sens - il peut tomber a zero comme rester non nul.

### 4.2 Le chiffre qui manque, et pourquoi ce n'est pas un chiffre

`S19-6a` est devenue affectable parce qu'un chiffre unique la resumait : une largeur de rangee
necessaire, confrontee a une formule reproduite sur onze mesures de navigateur. **`BPM-8` n'a pas
d'equivalent, et ce n'est pas un manque de travail.** La position verticale de l'indice est la somme
des hauteurs de **tout ce qui le precede dans le flux**, freres compris - c'est exactement le
perimetre que j'ai nomme dans `ADR-0059` en refusant d'ecrire un garde-fou vertical sur la chaine
d'ancetres, qui serait reste vert pendant qu'un rembourrage de `.enonce` pousse le contenu vers le
bas.

Ce qui est du avant qu'une story soit ecrivable n'est donc pas "une hauteur necessaire", c'est une
**decomposition** : a une largeur donnee de la bande, la hauteur de chaque bloc au-dessus de
l'indice, et lequel de ces blocs est compressible. C'est la meme forme de travail que la mesure de
largeur, sur un axe de plus.

### 4.3 Ce que je recommande, en une ligne, sans reprioriser

Mesurer `BPM-8` **apres** la fusion de `S19-6a` et pas avant. Prendre la mesure maintenant coute le
travail deux fois et, plus grave, produit un chiffre qui aura l'air valide dans une fiche pendant
qu'il decrira un arbre disparu - la famille de defaut que `CLAUDE.md` nomme l'ecart muet. Le
sequencement appartient a `sofia-sm`, la priorite a `camille-po` ; je donne le fait, pas le rang.

## 5. Le chantier de conception de la saisie - ce que `SAI-2c` change

Pas de story, pas de code. Ce que `nadia-prof` amende le 2026-08-25 entre dans `ADR-0058` par un
amendement ecrit dans ce lot. Resume, le detail est dans l'`ADR`.

- **`SAI-2a`** : inchangee, rien a instruire.
- **`SAI-2b`** : c'est une **regle d'ecriture de contenu**, pas une propriete de code. Aucune ligne
  d'architecture ne bouge, et aucun automate ne la gardera - il faudrait un dictionnaire, et
  `ADR-0039` et `ADR-0049` ont deja ferme cette porte. Je le confirme du cote technique et je
  n'invente pas de regle decorative pour faire semblant.
- **`SAI-2c`** : **la seule des trois qui coute une piece**, et elle contredit une phrase de ma
  section 6 d'`ADR-0058`. J'y ai ecrit que la saisie garderait le champ `derniereTentative`
  *"avec la meme duree de vie"* que la mecanique existante. Cette duree de vie est exactement ce que
  `SAI-2c` casse : dans `BanditManchotSession.jouer`, une bonne reponse appelle `resoudre(true)`, qui
  **tire la manche suivante dans la meme transition** et reconstruit la session avec
  `Optional.empty()` pour ce champ. **Au moment ou l'ecran de reussite se rend, la chaine recue
  n'existe plus.** `SAI-2c` ne peut donc pas etre servie par ce champ.
- **Ce que je retiens** : la chaine jugee voyage sur le **verdict**, seul objet qui survit a la
  resolution d'une manche. Ce n'est pas une forme inventee pour l'occasion, c'est **mot pour mot le
  motif d'`ADR-0024`**, deja ratifie et deja en production sur `Verdict.explication` : *"le verdict
  porte l'explication de l'item qui vient d'etre joue, parce qu'il est la seule trace de cet item"*.
- **`R6`** : refus de ligne, pas d'architecture. Il ne m'appelle rien, et je ne le contourne pas.
- **La limite qu'elle nomme sur `SAI-9`** - l'enfant tape `bato`, l'appareil envoie `baton`, le
  retour lui dit une chaine qu'il n'a jamais ecrite - **n'a pas de reponse technique et je n'en
  fabrique pas une.** Le serveur ne connait que ce qu'il recoit. Toute tentative de distinguer les
  deux passerait par la neutralisation de la correction, que l'utilisatrice a ecartee ; je ne la
  reintroduis pas sous un autre nom.

## 6. Ce que j'ai trouve de faux, chez les autres et chez moi

Par ordre de cout, le plus cher d'abord.

1. **Chez moi, `ADR-0059`** : *"elles changent une ligne de constante chacune"*. Faux - une remesure
   de `L` fait basculer le cas vert du jeu fautif commite. Mesure en section 3.2 c). Amende.
2. **Chez moi, `ADR-0059`** : *"141 largeurs de viewport sur 144, ou zero"*, repris tel quel dans
   `ADR-index.md`. Vrai a `L = 539`, faux a `L = 647` ou le compte est 33 sur 144. La demonstration
   tient, ses chiffres non - et un chiffre d'index se lit isole. Amende, index corrige.
3. **Chez moi, `ADR-0059`** : le declencheur de la dette verticale, tel qu'ecrit, ferait produire
   une table perimee le jour de sa livraison si `S20-1` et `S19-6a` sont sequences dans cet ordre.
   Amende en nommant le dernier lot plutot que le premier, sans desserrer la dette.
4. **Dans le brief qui m'ouvre ce sprint** : *"elle propose trois champs"*. `ines-design` ne propose
   nulle part trois champs - son document (`e5a8360`) propose un `<span class="mot-designe">` et
   route explicitement la forme de la donnee a `theo-archi` et `sofia-sm`. La formulation
   "avant / mot / apres" est celle du critere d'acceptation de `S20-1`, ecrite par `camille-po`.
   L'ecart n'est pas anodin : trois champs est precisement la forme que je refuse en section 1.2, et
   l'attribuer a `ines-design` aurait fait porter a une proposition mesuree un choix qu'elle n'a
   jamais fait.
5. **Dans le brief** : *"c'est un refus de ligne, et il ne t'appelle rien"* a propos de `R6` - exact,
   verifie, je le confirme plutot que de le corriger.
6. **La correction de `nadia-prof` sur son propre constat est juste, verifiee et non reprise sur
   parole** : `catalogue.yaml` porte bien `mecanique: bandit-manchot` pour
   `bandit-manchot-homophones-cm1`, et aucun paquet de saisie n'existe dans
   `adapters/adapter-content/src/main/resources/contenu/`. Le brief signale avoir relaye l'alarme
   sans la verifier ; je n'ai trouve aucune autre trace de cette alarme dans les lots que j'ai lus,
   ni dans `DEC-0047` et son amendement, ni dans `S20-1`, `S20-2` ou `S19-6a`. `DEC-0047` ecrit
   d'ailleurs la formulation prudente - *"ce qui **serait** grave sur un paquet en mode strict"*, au
   conditionnel et sans nommer de paquet valide.

## 7. Ce que je route

- **`sofia-sm`** : le point de controle de la dette verticale d'`ADR-0059` sur le **dernier** des
  lots `S20-1` / `S19-6a` (section 1.4) ; le sequencement de la mesure de `BPM-8` apres `S19-6a`
  (section 4.3).
- **`camille-po`** : le residu de bande de `S19-6a` vaut desormais **111 px sur 144**, pas 3
  (section 3.2 b). C'est une tolerance produit, pas une contrainte technique.
- **`ines-design`** : la valeur de 620 px est exclue par l'inegalite, le plancher est
  **687 px strictement depasse** pour une reserve non nulle (section 3.2 a).
- **`nadia-prof`** : `SYN-14` n'a jamais ete mesuree sur une zone qui s'etale jusqu'a 687 px
  (section 3.3) ; et la forme du renfort de `NAT-5` reste entierement sienne.
- **L'auteur du contenu de `S20-2`** : l'entree due dans la table d'exclusion de
  `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, et la marge etroite de l'identite
  d'ecran quand les quatre propositions sont partagees (section 2.1).

---

## Amendement du 2026-08-25, meme jour - `camille-po` retire le correctif de largeur, je chiffre la piste verticale, et trois de mes verdicts tombent

`camille-po` ne maintient pas le correctif de largeur, sur mon propre recalcul (33 largeurs sur 144,
residu de 111 px) et sur un motif que le chiffre seul ne portait pas : un plancher a 687 px met hors
de portee l'ecran de 1280 px partage en deux, soit environ 640 px, **le cas d'usage meme qui
justifiait la bande**. Elle suspend toute valeur de plafond, declare 620 px exclue, et rouvre la
famille verticale.

**Je ne reecris pas les sections d'origine** : elles restent lisibles telles qu'ecrites, a l'heure ou
le correctif de largeur etait encore au perimetre. Cet amendement dit ce qui tombe.

La mesure complete, sa methode et ses tableaux vivent dans `mesure-budget-vertical.md`, a cote de ce
fichier. Ce qui suit n'en est que la consequence sur mes quatre verdicts.

### Ce qui tombe, et le remplacement

| Section | Ce que j'y ai ecrit | Ce que la mesure dit |
|---|---|---|
| **3** (`S19-6a`) | faisable, mecanisme inchange | **sans objet** : la story est retiree du perimetre par `camille-po`. L'arithmetique reste juste et `ADR-0059` reste valide ; il n'a plus d'objet ce sprint. |
| **4** (`S19-6b`, `BPM-8`) | **infaisable ce sprint**, parce qu'une mesure prise avant `S19-6a` decrirait une mise en page sur le point de changer | **le motif est tombe avec `S19-6a`**, et la mesure existe : `BPM-8` porte **8 items sous le pli sur 22 vus, pire deficit 32,19 px**, et il est **entierement ferme par 80 px** de budget vertical. C'est le moins cher des dix jeux touches. Il n'a plus besoin d'un avis de faisabilite, il a une mesure. |
| **4.1** | 576 px est le pire cas de la bande | **il n'y a pas de pire cas** : 251 items mesures a 576 et a 719 px rendent le meme pixel, au centieme, zero exception. La bande est **une seule mise en page**, la feuille tierce y captant la zone a 540 px partout. |
| **4.1** | `BPM-8` et `SYN-16` relevent de mecanismes differents (reprise de `DEC-0043`) | **meme ecran, meme cause, meme population** : le bloc de message passe sous le pli sur **dix jeux**, pas deux. |
| **1** (`S20-1`) et **2** (`S20-2`) | faisable moyennant `ADR-0062` / faisable en l'etat | **inchanges.** Rien dans cette mesure ne les touche. |

### Ce que la mesure ajoute et que je n'avais pas

- **La piste verticale ne ferme pas la bande** : 308,19 px a trouver au pire, contre 80 px
  disponibles sans rouvrir d'arbitrage et 136 px en rouvrant le meuble. Elle ferme 90 puis 114 des
  134 items sous le pli, et **aucun** des dix items a trois rangees.
- **Elle n'est pas nulle non plus** : elle ferme entierement trois jeux sur dix a 80 px, dont
  `BPM-8`.
- **Le probleme n'est pas celui de la bande.** A la reference du produit, 744 x 551, **95 items sur
  296 (32 %) ont deja leur bloc de message sous le pli**, sur dix jeux, dont deux `couvert-valide`.
  La bande aggrave un ecart qui existe a la reference, elle ne le cree pas.
- **La troisieme option de `camille-po` est chiffree** : la mise en page ne dependant pas de la
  hauteur du viewport, la courbe est exacte - le pli a 600 px ferme 76 des 134 items, a 860 px il les
  ferme tous.

### Ce que je maintiens de la section 3, parce qu'il survit au retrait

Les trois corrections que j'ai apportees a `ADR-0059` tiennent, retrait ou pas : le cout reel d'une
remesure de `L` est de deux fichiers et non d'une ligne, la couverture n'est pas de 141 sur 144, et
le declencheur de la dette verticale etait mal ecrit. Elles portaient sur ce que l'`ADR` **dit**, pas
sur ce que le sprint decide d'en faire.
