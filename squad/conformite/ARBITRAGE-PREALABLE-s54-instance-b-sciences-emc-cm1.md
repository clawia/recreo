# Arbitrage prealable - s54, instance B : sciences CM1 et EMC CM1

`nadia-prof`, instance B, le 2026-10-07 (21:21, UTC+02:00, lu par `date`). Arbre : branche `s54-nadia-instance-b`, issue de
`round-two` a `e6024e98`. Worktree et scratchpad propres a l'instance B.

**Audit sans jeu.** Aucun des quatre paquets n'est joue ici : trois n'ont pas d'entree de catalogue, et je ne joue pas
`cm1-sciences-corps` avant sa correction. Je decide sur la lecture des fichiers de paquet, de mes fiches de s51, de
`PaquetCm1SciencesCorpsTest`, du planning, de l'avis de faisabilite et de `DEC-0114`. **Ce document ne prononce aucun Done, aucun
verdict, aucun statut de matrice. Il ne cree aucun identifiant de condition ni de reserve** : je ne cite que des identifiants
deja alloues.

Convention des blocs YAML, celle de mon arbitrage de s53 : ils portent l'espace insecable (U+00A0) devant `?`, comme les
paquets (`TYP-1`). Un bloc est un item complet, commentaire de ligne compris, a coller a la place de l'item de meme numero.

## Resume

| Code | Poste | Decision | Tarif reel |
|---|---|---|---|
| D1 | `CRP-CM1-R1` a `R3` (`cm1-sciences-corps`, items 3, 5, 6, 7, 11) | texte exact fourni, section 2 | **0,25** si applique mot pour mot ; 0,5 si l'equipe reecrit |
| D2 | `S54-BR-SCI-CM1-15` (`cm1-sciences-terre-jour-nuit-saisons`) | motif 2 leve a la lecture ; **motif 1 non leve** : item 4 a corriger, texte section 3.2 | **1,0**, tarif de lot (`PAR-14`, 9 items sur 12) |
| D3 | `S54-BR-SCI-CM1-18` (`cm1-sciences-environnement-activite-humaine`) | motif leve a la lecture ; **accepte tel quel** | **0,5** (rejeu, 2 items sur 12) |
| D4 | `S54-BR-EMC-CM1-02` (`cm1-emc-atteinte-autrui`) | quatre items a corriger avant branchement, texte section 5 | **1,0**, premier passage |
| D5 | Cet arbitrage prealable | - | **0,5** |
| D6 | `PAR-13`, `PAR-14`, barre de `DEC-0112` 5.1 (Q4) | sections 6 et 7 | - |

**Instance B : 3,25 alloues + 0,5 de marge de rejeu nommee = 3,75 sur 4,0** (`PAR-9`), 0,25 non alloue. Deux lots sous `PAR-8`
(terre, EMC). Si `marc-dev` reecrit au lieu d'appliquer le texte de D1 : 4,0 sur 4,0, sans ecart.

---

## 1. Trois constats a corriger avant de m'en servir

### 1.1 Ma condition (i) n'est pas un plancher d'instance

`DEC-0112` 5.1 retire les reprises de sciences parce qu'une instance de sciences CM1 ferait 2,0 *"contre les 4,0 qu'elle a
exiges d'une instance (condition (i), arbitrage de s52 section 6)"*, et `Q4` du planning demande si B a 3,5 remplit cette barre.
**La condition (i) ne dit pas cela.** Son texte (`ARBITRAGE-PREALABLE-s46...` section 5, repris en s52 section 6) : *"une
composition a deux instances remplit 8,0 et laisse dehors au moins 4,0 de lots instruits, comptes comme prenables par la troisieme
instance"*. C'est une condition pour **deriver une troisieme instance**, pas une charge minimale pour qu'une instance existe. Ma
phrase de s52 section 6, *"2,0 au plus, aucun lot neuf, contre 4,0"*, ne repetait pas *"pour une troisieme instance"* : elle a
pu inviter la lecture de `DEC-0112`. Je la corrige ici. **A s54, deux instances sont ouvertes : la condition (i) n'est pas en jeu,
et aucune barre de charge minimale ne s'applique a B.** Seuls `PAR-8` (5,0, trois lots) et `PAR-9` (4,0 marge comprise) la bornent.

### 1.2 P9 de s53 n'a pas accepte `cm1-emc-atteinte-autrui` "sans changement"

Le planning et `DEC-0114` ecrivent que ce paquet a ete *"accepte sans changement a s53 (P9)"*. Mon point P9 ne decidait qu'une chose :
*"Je n'exige pas de ligne `DEGRADE`"*, apres avoir verifie qu'aucun verbe du libelle n'est laisse sans item. **Je n'ai pas audite les
items a s53.** Le paquet passe ici sa premiere lecture item par item (section 5).

### 1.3 Le compte des items touches de la reprise de terre est 9, pas 7

Le risque R3 du planning lit dans l'en-tete *"environ 7 items de 12 (2, 3, 4, 7, 9, 10, 11)"*. Comparaison champ par champ des deux
fichiers (enonce, bonne reponse, distracteurs, indice), par script de lecture seule : **items 1, 2, 3, 4, 7, 8, 9, 10, 11 changent,
5, 6 et 12 sont identiques. 9 sur 12, 75 %.** L'en-tete ne mentionne pas les items 1 (indice) et 8 (enonce, bonne reponse). Pour
`activite-humaine` : items 5 (indice) et 11 (enonce, indice), **2 sur 12**.

---

## 2. D1 - `CRP-CM1-R1` a `R3` : le texte exact

**La barre ne bouge pas.** Elle reste celle de `FICHE-bandit-manchot-corps-cm1.md` section 8, mot pour mot. Ma fiche disait *"Les
formulations sont a l'equipe"* : le texte ci-dessous est **une** facon de l'atteindre, que je fournis pour que le poste se ferme
au tarif de 0,25. Un autre texte qui atteint la barre sera leve aussi, au tarif de 0,5. Je ne juge pas la reecriture d'un autre
contre mon texte, je la juge contre la barre.

Contraintes verifiees sur ces cinq blocs, par script contre les motifs de `PaquetCm1SciencesCorpsTest` : indice d'une phrase finie
par un point, sans la bonne reponse ; aucun jeton d'adresse, aucun mot de repas, de lit ou de routine (`BIA-4`) ; aucun mot de
`EPS-9` ni d'organe de la liste du test ; espace insecable devant `?`. Douze items, deux distracteurs chacun : le motif de
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` ("12 items", "36 propositions") ne change pas.

### 2.1 `CRP-CM1-R1`, item 3 : indice faux

Le corps ne *"fait rien d'autre"* la nuit est faux. Le nouvel indice dit pourquoi les deux conceptions fausses le sont, sans nommer
la bonne reponse.

```yaml
  - enonce: "Le corps grandit et se répare surtout quand il est au repos la nuit. Que lui faut-il pour cela ?"  # tache: A # element: sommeil # HIS-3: la croissance et la réparation de l'organisme se font en grande partie pendant le sommeil, fait enseigné au cycle 3 (fonctionnement du corps), compatible avec le CP (le corps se repose et récupère)
    bonneReponse: "assez de sommeil"
    distracteurs: ["rester éveillé le plus tard possible", "boire des boissons qui empêchent de dormir"]
    indice: "Rester éveillé tard ou boire ce qui empêche de dormir raccourcit le temps où le corps grandit et se répare."
```

### 2.2 `CRP-CM1-R1`, item 5 : indice inexact

Le corps utilise beaucoup de sucres ; ce qui est en trop dans une boisson sucree, c'est un apport qu'il trouve deja ailleurs.

```yaml
  - enonce: "Le corps est fait en grande partie d'eau et en perd sans cesse. Quelle boisson lui convient le mieux ?"  # tache: A # element: alimentation # HIS-3: l'eau est la boisson adaptée aux besoins du corps, le corps humain adulte est composé d'environ 60 pour cent d'eau, fait enseigné au cycle 3 (besoins du corps)
    bonneReponse: "l'eau"
    distracteurs: ["les jus de fruits sucrés", "les sodas"]
    indice: "Les boissons sucrées apportent du sucre en trop, car le corps tire déjà des aliments les sucres dont il a besoin."
```

### 2.3 `CRP-CM1-R2`, item 6 : portee du geste, personne nommee

*"limite le plus"* au lieu de *"évite"*, comme l'item 4 ; *"cette personne"* au lieu de *"lui"*. Bonne reponse, distracteurs et
indice inchanges.

```yaml
  - enonce: "Un enfant malade tousse près d'une autre personne. Quel geste limite le plus la transmission de ses microbes à cette personne ?"  # tache: A # element: hygiene # HIS-3: tousser dans le pli du coude évite de déposer des microbes sur les mains puis sur les objets et les personnes, geste d'hygiène enseigné au cycle 3
    bonneReponse: "tousser dans son coude"
    distracteurs: ["tousser dans sa main", "tousser en se tenant bien droit"]
    indice: "Les mains touchent ensuite des objets et d'autres personnes."
```

### 2.4 `CRP-CM1-R3`, item 7 : distracteurs plausibles, indice qui repond aux deux

Deux idees que des enfants de 9 ans tiennent reellement : bouger *use* le corps (la meme famille que *"rester immobile pour garder
ses forces"*, item 2), et bouger dispense de manger varie. L'indice repond a chacune.

```yaml
  - enonce: "À quoi sert l'activité physique pour le fonctionnement du corps ?"  # tache: B # element: activite # HIS-3: l'activité physique fait travailler les muscles et les articulations, fait enseigné au cycle 3 (fonctionnement du corps), aucun effet à long terme énoncé
    bonneReponse: "à faire travailler les muscles et les articulations"
    distracteurs: ["à user les muscles et les articulations", "à remplacer une alimentation variée"]
    indice: "Bouger souvent n'use pas le corps, et aucun exercice ne remplace une alimentation variée."
```

### 2.5 `CRP-CM1-R2`, item 11 : role exact des fibres

La bonne reponse reprend les mots de ma barre (*"elles aident l'intestin a bien fonctionner"*). **Frontiere avec `SCI-CM1-09`
verifiee** : l'objet de la question reste un constituant de l'alimentation ; l'intestin est nomme dans le role, il n'est pas la
reponse a *"quel organe"*. C'est la lecture de ma fiche, section 2 (*"comme objet de la question"*). Indice inchange, toujours vrai
et sans la nouvelle bonne reponse.

```yaml
  - enonce: "À quoi servent les fibres des fruits, des légumes et des céréales complètes ?"  # tache: B # element: alimentation # HIS-3: les fibres alimentaires favorisent le transit intestinal et ne sont presque pas absorbées, fait enseigné au cycle 3 (alimentation)
    bonneReponse: "à aider l'intestin à bien fonctionner"
    distracteurs: ["à fabriquer les os", "à donner de l'énergie tout de suite"]
    indice: "Les fibres ne sont presque pas absorbées par le corps."
```

**Rien d'autre ne change dans le fichier.** Le commit touche ces cinq items et aucun autre ; je rejoue les cinq sur le jeu servi,
avec une erreur sur chacun, et je leve les reserves a la barre de la fiche.

---

## 3. D2 - `cm1-sciences-terre-jour-nuit-saisons` (`SCI-CM1-15`)

### 3.1 Motif 2 (item 11) : leve a la lecture

Distracteurs *"l'hiver"* et *"l'automne"*. En France metropolitaine, tous les jours d'ete durent douze heures ou plus, tous ceux
d'automne douze heures ou moins : une seule saison a les jours les plus longs parmi les trois. C'est la correction que la fiche
proposait. Les points non bloquants 1 (items 9, 10, 11 en *"France métropolitaine"*, indice de l'item 10 vrai), 3 (indices des items
3 et 9) et 4 (indice de l'item 2) sont pris a la lecture.

### 3.2 Motif 1 (item 4) : **non leve**. Texte exact

Le distracteur neuf est *"le Soleil s'est rapproché de cette région"*. **Il est vrai a la lettre dans le repere que le paquet
emploie lui-meme.** L'item 9 parle du Soleil *"plus haut dans le ciel"* : le repere de l'observateur. Dans ce repere, entre la nuit
et le jour, le Soleil se rapproche bien de la region, de quelques milliers de kilometres (au plus le diametre de la Terre). C'est le
meme ordre de grandeur et le meme fait que celui qui a motive le refus : *"plus proche du Soleil que le reste de la Terre - de
quelques milliers de kilometres"*. La barre de la fiche, *"un second distracteur faux, qui porte la conception de la distance sans
etre vrai a la lettre"*, n'est pas atteinte. Je ne la releve pas : je l'applique au texte neuf comme a l'ancien.

**L'indice est inexact lui aussi** : *"La distance entre la Terre et le Soleil reste la même toute la journée."* Elle change de
plusieurs dizaines de milliers de kilometres par jour selon la saison (orbite elliptique). Regime de `MEL-R1` (indice inexact a cote
d'une bonne reponse exacte) : ce ne serait pas un motif seul. Je le corrige dans le meme bloc parce qu'il le faut de toute facon.

Correction : *"beaucoup"* rend le distracteur faux dans tous les reperes (quelques milliers de kilometres sur cent cinquante millions)
et garde la conception ; l'indice dit pourquoi elle est fausse sans decrire la position attendue (point de la fiche, `SCI-1`).

```yaml
  - enonce: "Dans une région de la Terre, c'est le jour. Dans quelle position est cette région ?"  # tache: A # element: jour # HIS-3: il fait jour dans la partie de la Terre tournee vers le Soleil, la distance au Soleil change tres peu entre la nuit et le jour, fait enseigne au cycle 3
    bonneReponse: "elle est tournée vers le Soleil"
    distracteurs: ["elle est cachée par la Lune", "le Soleil s'est beaucoup rapproché de cette région"]
    indice: "Entre la nuit et le jour, la distance au Soleil ne change presque pas."
```

**Condition avant branchement** : ce bloc, ou un autre texte qui atteint la barre de la fiche. Sans correction, je rejoue l'item 4 en
premier et le motif 1 est maintenu sur ce que j'ecris ici.

### 3.3 Ce que je regarderai en jouant, sans en faire une condition

- Item 1 : l'indice neuf (*"Cet astre éclaire et réchauffe toutes les planètes."*) decrit la bonne reponse. L'ancien aussi, et je
  ne l'ai pas releve a s51 : je ne releve pas la barre ici.
- Item 3 : l'indice parle de la revolution (*"les planètes ... tournent autour du Soleil"*) dans un item sur la rotation. Il n'est
  pas faux ; je verifie a l'ecran qu'il ne pousse pas vers la confusion rotation-revolution.
- Item 7 : *"la Lune cache le Soleil en hiver"* est faux dans toutes les lectures (point 2 pris) ; sa plausibilite se juge en
  jouant, a cote de *"la Lune éteint la lumière du Soleil"* (item 5).

---

## 4. D3 - `cm1-sciences-environnement-activite-humaine` (`SCI-CM1-18`) : accepte tel quel

**Motif maintenu deux fois, item 11 : leve a la lecture.** L'enonce nomme maintenant le gaz naturel avec le petrole et le charbon et
les dit *"énergies fossiles qui ne se renouvellent pas"* ; la question porte sur *"ces énergies"*. C'est la correction que ma fiche
nommait la plus courte (S2-3, *"un enonce qui nomme le gaz naturel avec le petrole et le charbon ... quand le gaz reste en
distracteur"*). Une seule proposition preserve ces energies. L'indice ne juge plus sur un critere que l'enonce ne pose pas.

Item 5 : l'indice neuf (*"Ce gaz n'est pas de l'oxygène et ne rend pas l'air plus pur."*) est exact et repond au distracteur de
l'oxygene ; il remplace l'indice que j'avais observe ecrit pour l'ancienne question (S2-2). Les dix autres items sont identiques
octet pour octet a ceux du second passage. **Aucun YAML a changer.** Je rejoue l'item 11 en premier, avec l'erreur *"plus de gaz
naturel"*.

---

## 5. D4 - `cm1-emc-atteinte-autrui` (`EMC-CM1-02`) : quatre items a corriger avant branchement

Lu item par item contre `EMC-1` a `EMC-9`, `BIA-*`, `FER-1` a `FER-3`. Tenu a la lecture : les quatre termes de la parenthese sont
en bonne reponse (items 1 a 4) ; la bonne reponse des items 7 a 11 est toujours un adulte (`EMC-3`) ; aucune adresse a l'enfant,
aucune declaration de comportement (`EMC-8`), aucune violence detaillee ; les indices des items 7 a 11 disent au present ce qu'il faut
faire dans la vie (`EMC-9`) ; aucun fait institutionnel n'est affirme (je n'ai pas de source a exiger : tarif de 1,0). Quatre
defauts, aucun n'est un motif de refus a lui seul ; corriges avant le branchement, ils ne deviendront pas des reserves.

### 5.1 Items 2 et 3 : un partage de genre et d'origine

Les deux auteurs nommes sont des garcons (Hugo, Malo) et leurs deux cibles des filles (Aya, Lina) ; Aya est la cible deux fois
(items 1 et 3). Un enfant qui enchaine les items lit *"les garcons blessent les filles"* et *"Aya est celle qu'on vise"*. Meme
famille que `SYC-CE1-R1`. Correction : l'item 2 inverse les roles, l'item 3 change de cible. Rien d'autre ne change.

```yaml
  - enonce: "Dans la cour, Lina lance à Malo un mot grossier et blessant devant ses camarades. Comment appelle-t-on ce comportement ?"
    bonneReponse: "une insulte"
    distracteurs: ["une mise à l'écart", "un simple désaccord"]
    indice: "Ce sont des mots grossiers qui visent directement la personne."
  - enonce: "Pour un travail de groupe, trois élèves tournent le dos à Jade et refusent de lui parler. Comment appelle-t-on ce comportement ?"
    bonneReponse: "une mise à l'écart"
    distracteurs: ["une moquerie", "un simple désaccord"]
    indice: "On laisse volontairement quelqu'un en dehors du groupe."
```

### 5.2 Item 9 : un indice qui promet trop

*"Un adulte sait comment faire cesser ces messages."* Pour un enfant qui vit la situation, c'est une promesse qu'un adulte ne peut
pas toujours tenir. L'item 7 dit juste : *"peut agir"*. Sur cette ligne, je ne laisse pas un retour affirmer plus que le vrai
(`EMC-9`, motif : *"un sujet ou le mensonge est grave"*).

```yaml
  - enonce: "Un élève reçoit des messages méchants d'un camarade sur un téléphone. À qui est-il important d'en parler ?"
    bonneReponse: "à un adulte de confiance, par exemple un enseignant"
    distracteurs: ["à l'auteur des messages, en lui répondant", "à toute la classe, en montrant les messages"]
    indice: "Un adulte peut agir pour faire cesser ces messages."
```

### 5.3 Item 10 : un distracteur que personne ne croit

*"à un autre élève, pour qu'il joue à sa place avec les autres"* n'a pas de sens : l'item devient une devinette (famille de
`CRP-CM1-R3`). Le remplacant porte l'idee fausse la plus repandue chez un enfant ecarte, se taire et attendre ; l'indice existant y
repond deja. Il n'est pas defendable (`EMC-5`) et n'est pas un adulte (`EMC-3`).

```yaml
  - enonce: "Un élève est écarté de tous les jeux depuis plusieurs jours. À qui est-il important d'en parler ?"
    bonneReponse: "à son enseignant ou à un adulte de l'école"
    distracteurs: ["à personne, en attendant que cela passe", "à ceux qui l'écartent, pour qu'ils changent d'avis"]
    indice: "Une situation qui dure demande l'aide d'un adulte."
```

Onze items, deux distracteurs chacun : le motif de `LeRang...` (11 items, 33 propositions) ne change pas. L'en-tete du paquet parle
de *"prenoms fictifs repris des paquets voisins"* : *Jade* l'est (`ce1-pronoms`, `ce1-texte-narratif`, `ce1-problemes-durees`).

**Ce qui se passe si ces quatre blocs ne sont pas appliques** : je les ouvre en reserves datees au premier passage, je ne refuse pas
pour eux seuls.

---

## 6. Q3 et Q4 - tarifs reels de l'instance B

| Poste | Regle | Prix |
|---|---|---|
| `CRP-CM1-R1` a `R3` | `PAR-10`, bareme de s30 : *"correction reprise mot pour mot d'un texte que j'ai fourni, puis reverification = 0,25"* ; rejeu borne reecrit par l'equipe = 0,5. 5 items sur 12 : `PAR-14` ne s'applique pas | **0,25** (0,5 si reecrit) |
| `SCI-CM1-15`, reprise de terre | `PAR-14` : 9 items sur 12 changent, l'audit de s51 ne couvre plus le paquet. Je suis la source des motifs : tarif de lot 1,0. **Compte comme un lot sous `PAR-8`** : c'est un second premier passage | **1,0** |
| `SCI-CM1-18`, reprise d'activite humaine | 2 items sur 12 : rejeu (`PAR-10`). **N'est pas un lot** (`DEC-0107` section 4) | **0,5** |
| `EMC-CM1-02` | premier passage complet, matiere dont je suis la source des conditions, aucun fait institutionnel affirme | **1,0** |
| Cet arbitrage | precedent de s53 section 14 : quatre paquets lus item par item, dix blocs de texte que je rejugerai | **0,5** |
| **Alloue** | | **3,25** |
| Marge de rejeu nommee, allouee a aucun lot | `PAR-8`, `PAR-9` | 0,5 |
| **Total / 4,0** | `PAR-9` | **3,75** ; 0,25 non alloue |
| Lots / 3 | `PAR-8` | **2** (terre, EMC) |

**La marge de 0,5 finance un rejeu.** Un second refus dans B est un lot non ferme, dit comme tel a la review. Les corrections que
je decide ici rendent les audits moins longs, pas moins chers.

**Q4, barre de `DEC-0112` 5.1** : section 1.1. Aucun amendement de ma part n'est necessaire pour que B porte les deux reprises :
la condition (i) ne s'applique pas a une instance d'un sprint a deux instances. `camille-po` corrige la lecture de `DEC-0112` 5.1 si
elle le juge utile.

**La part de Q3 qui n'est pas a B (prix de R3, de `PAR-4`, de l'arbitrage de A), Q5, Q7, Q8, R3, plafond d'`ART-CP-10`, `PAR-13` sur
`FUT` et `PCO`** : routes a l'instance A par le planning (affectations nominatives). Je n'y reponds pas : deux instances qui ecrivent la meme decision sans le savoir, c'est la forme (c).

## 7. `PAR-13` et `PAR-14`

**`PAR-13` : B ne porte aucun terrain neuf.** Je la juge au niveau du terrain, comme a s51 et s52. Sciences CM1 : quinze lignes au
moins sont `couvert-valide` (filtre sur la colonne de statut, qui perd les lignes sans barre finale, piege nomme a s51 section 2.2) ;
`SCI-1`, `SCI-3`, `SCI-4` ont mordu du contenu reel. EMC CM1 : huit lignes `couvert-valide`, huit jeux servis (`grep -c "paquet:
cm1-emc"` sur `catalogue.yaml`). **Ce que je nomme sans en faire une barre** : `EMC-9` est propre a la ligne 2 et n'a jamais rencontre
de contenu. Quelle que soit la lecture de A sur le francais CE1, `PAR-13` tient pour le sprint du cote de B.

**`PAR-14`** : section 6. Terre au tarif de lot (75 %), activite humaine au rejeu (16,7 %), `CRP` sous la moitie (41,7 %, le chiffre
de `DEC-0112` 5.2 est juste).

## 8. Conditions d'entree de catalogue (titre, consigne), pour les trois branchements

L'enfant lit le titre et la consigne : ils sont a moi au meme titre que les items.

1. **Titres neufs** pour les deux reprises, ni *"La Terre, le jour et les saisons"* ni *"L'activité humaine et la nature"* (avis de
   faisabilite, `LesJeuxRetiresDuCatalogueNeSontPasServisTest`), sans niveau (`AucunTitreDeJeuNePorteSonNiveauTest`). Le titre dit le
   contenu a un enfant de 9 ans. Le choix est a l'auteur.
2. **EMC** : ni le titre ni la consigne ne s'adressent a l'enfant comme cible ou auteur possible (`EMC-3`), et aucun ne laisse
   croire qu'en repondant il previent quelqu'un (`EMC-9`). *"Lis bien, puis choisis la bonne réponse."* (consigne existante) convient.
3. Les consignes servies a s51 pour les deux paquets de sciences restent acceptables ; la consigne existante ci-dessus aussi.

## 9. Ce que je ne peux pas decider sans voir le jeu

Je reste la seule garde de chacun de ces points : aucun garde-fou ne les mesure.

1. **`EMC-9` sur l'ecran entier** : le retour d'une bonne reponse, l'ecran de revelation et l'ecran de fin, sur les items 7 a 11.
   Aucun ne doit dire ou laisser croire que l'enfant vient de prevenir quelqu'un. C'est la mecanique qui ecrit ces retours, pas le
   paquet : je le juge en jouant.
2. **Item 4 de terre corrige** : que *"beaucoup"* se lise comme je l'entends, et que l'indice ne donne pas la position.
3. **Item 11 d'activite humaine** : que *"plus de gaz naturel"* se lise *davantage* et non *ne ... plus*, a l'ecran.
4. **Fuite d'un indice vers la reponse d'un autre item** (EMC items 7 a 11, tous tournes vers l'adulte ; corps item 7 et item 1) :
   lecon reprise ou reponse soufflee selon l'ordre de tirage.
5. **`CYC-1` de l'EMC** contre `cp-emc-exclusion`, `cp-emc-permis-interdit` et `cm1-emc-institutions`, que je lis a cote du paquet.
6. **La mention exigee d'avance** sur l'EMC CM1, reproduite mot pour mot par `camille-po` si la ligne ferme : je la juge au verdict.
7. **Le vocabulaire a l'ecran reel** : *"aux dépens de"* (EMC item 1), *"la cible"* (item 11), *"énergies fossiles"*.
8. **Les tests de paquet des trois branchements** (`T1` de l'avis : dus par convention, aucun n'existe pour les deux reprises sous leur
   nouveau nom ni pour l'EMC) : s'ils manquent, je suis seule a tenir `SCI-1`, `SCI-3`, `SCI-4` sur ces paquets, et je le dirai dans la
   fiche.

## 10. Ordre de jeu

`CRP` d'abord (jeu servi, premier fusionne) : les cinq items. Puis terre, item 4 et item 11 en premier, puis les quatre points
non bloquants de la fiche, puis le reste. Puis activite humaine, item 11 en premier. Puis EMC, les quatre items de la section 5 en
premier, puis les onze. Un port de serveur et un sous-repertoire de scratchpad propres a l'instance B, choisis au lancement.

## 11. Routage

| A qui | Quoi |
|---|---|
| `marc-dev` | Section 2 : les cinq blocs, mot pour mot, dans un seul commit de `S51-SCI-CORPS-CM1-RESERVES` |
| `lea-dev` | Section 3.2 (item 4 de terre) et section 5 (items 2, 3, 9, 10 de l'EMC), dans le commit de branchement de chaque paquet (critere C2 : correction decidee par `nadia-prof`). Section 8 pour les titres et consignes. Activite humaine : aucun YAML |
| `camille-po` | Section 1.1 (lecture de `DEC-0112` 5.1) et 1.2 (P9) ; tarifs de la section 6 au backlog |
| `sofia-sm` | Section 1.3 (compte de R3 du planning) ; tarifs de B a la review |

## 12. Ce que ce document ne fait pas

- Il ne prononce aucun Done, ne touche aucune fiche, ni la matrice, ni le backlog, ni un YAML, ni le registre d'identifiants.
- Il ne cree aucun identifiant de condition ou de reserve.
- Il ne releve aucune barre ecrite a s51 : il les applique aux textes neufs.
- Il ne repond a aucune question routee a l'instance A.
