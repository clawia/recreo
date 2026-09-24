# Fiche de conformite - `bandit-manchot-semaine-cp`

- **Jeu** : `bandit-manchot-semaine-cp`, paquet `cp-semaine`. **23 items au verdict du 2026-08-31 ;
  20 items depuis le 2026-09-24** (`DEC-0096`, commit `0d48d2a3` : onze items sur les mois et les
  saisons retires, huit items neufs dans le perimetre corrige). **Les deux verdicts sont ci-dessous,
  dans l'ordre ; aucun n'efface l'autre.**
- **Competence visee** : Mathematiques CP, "Grandeurs et mesures" - *"Se reperer dans la journee, la
  semaine ; lire l'heure pleine sur une horloge"*, **moitie *se reperer* seule**.
- **Conditions opposables** : `SEM-1` a `SEM-9` (2026-08-31). **`SEM-1` a ete amendee le 2026-09-24**
  (voir plus bas, et
  `squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md`,
  section 2.3) : les mois et les saisons sortent de son perimetre.
- **Auteure du verdict** : `nadia-prof`. **Un verdict n'est jamais efface.**

---

## Verdict du 2026-08-31 (s25)

# Verdict : **VALIDE** - Done pedagogique prononce, deux reserves non bloquantes

**La ligne peut passer `couvert-valide`, avec la mention que `SEM` exige et que je reproduis ici mot
pour mot, parce qu'elle est une condition et non une remarque :**

> **"moitie 'se reperer dans la journee, la semaine' seule ; la moitie 'lire l'heure pleine sur une
> horloge' reste non couverte"**

**Sans cette mention, la ligne dirait plus que ce que le jeu sert.**

*Note de composition a l'attention de `camille-po`, sans effet sur ce verdict : si un paquet
d'horloge validait un jour la seconde moitie, cette mention tomberait et la ligne serait entiere. Les
deux moities font passer la meme ligne au vert chacune de son cote - je l'ai ecrit le 2026-08-31 et
c'est toujours vrai.*

### 1. Ce que j'ai fait

**J'ai joue** sur le jar construit depuis `s25-sofia-review` (meme reserve sur l'outil de
construction que sur les deux paquets illustres). **Et j'ai relu les vingt-trois items un par un** :
enonce, bonne reponse, deux distracteurs, indice. **Aucun echantillon.**

### 2. `SEM-1` a `SEM-9`, verifiees une par une

| Condition | Verdict | Ce que j'ai verifie, et comment |
|---|---|---|
| **`SEM-1`** - perimetre : moments du jour, jours, mois, saisons | **TENUE, 23 sur 23** | Relu item par item. Rien hors de ces quatre familles |
| **`SEM-2`** - aucun item ne demande de calculer une duree | **TENUE** | Aucun item ne fait calculer. Aucun n'empiete sur la ligne de CM1 deja verte |
| **`SEM-3`** - aucune horloge, aucun cadran, aucune aiguille | **TENUE** | Recherche des trois mots sur les vingt-trois enonces et les vingt-trois indices : **zero occurrence**. **Les deux items d'horloge de `contenu-repris/quiz/cp-durees.yml`, que j'avais nommes comme la contrefacon a ecarter, ne sont pas dans le paquet** |
| **`SEM-4`** - enonce au plus 12 mots, aucun mot de plus de neuf lettres | **TENUE, 23 sur 23** | Compte mecanique : **aucun enonce ne depasse, aucun mot ne depasse**. Le mot le plus long du paquet est `printemps`, neuf lettres, exactement a la borne que `NAR-3` posait deja |
| **`SEM-5`** - distracteurs de la meme categorie que la bonne reponse | **TENUE, 23 sur 23** | Verifie categorie par categorie contre les quatre listes fermees (jours, mois, saisons, moments). **Aucun melange** : on ne repond jamais a une question sur un jour par un mois |
| **`SEM-6`** - au moins un tiers d'items d'ordre | **TENUE largement : 14 sur 23**, plancher a 7 | Items en *"juste apres"*, *"juste avant"*, *"entre ... et ..."* |
| **`SEM-7`** - aucun presuppose | **TENUE** | Aucune configuration familiale, aucun rythme scolaire, aucun depart en vacances, aucune fete. **Le mercredi et le samedi ne portent aucune activite** - c'est le piege le plus courant sur cette competence et il est evite |
| **`SEM-8`** - indice qui dit quoi se rappeler, huit mots au plus | **TENUE** | Quatre indices en tout, de cinq a six mots, un par famille. Aucun ne donne la reponse |
| **`SEM-9`** - au moins vingt items, deux items ne posent jamais la meme question | **TENUE** | 23 items, 23 enonces distincts |

**Note du 2026-09-24 (`nadia-prof`), sur la ligne `SEM-1` du tableau ci-dessus.** Ce *"TENUE, 23 sur
23"* a ete rendu contre la redaction de `SEM-1` du 2026-08-31, qui autorisait quatre familles - dont
les mois et les saisons. **Cette redaction etait fautive et je l'ai amendee le 2026-09-24** : les
mois et les saisons appartiennent a `QLM-CP-13` (questionner le monde), et `SEM-1` se limite
desormais aux moments de la journee et aux jours de la semaine. Le motif complet est dans
`squad/conformite/ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md`,
section 2.3, et le constat d'origine
dans `squad/conformite/FICHE-bandit-manchot-saisons-cp.md`. **Le verdict du 2026-08-31 n'est pas
efface et n'est pas rouvert** ; contre la redaction corrigee, onze des vingt-trois items de ce paquet
sortent du perimetre. L'utilisatrice a tranche leur retrait ; la `DEC` et la matrice sont a
`camille-po`.

### 3. Ce que j'ai regarde en plus des conditions, et qui tient

- **La forme *"entre X et Y"*** (items 13, 14, 17, 18) est le meilleur exercice du paquet : elle
  oblige a tenir deux reperes a la fois, et c'est exactement ce que *se reperer* veut dire.
- **Les distracteurs d'ordre sont plausibles**, jamais absurdes : pour *"juste avant samedi"*, on
  propose `dimanche` (qui vient apres) et `jeudi` (deux avant). **Les deux sont les erreurs qu'un
  enfant fait reellement**, pas des reponses au hasard.
- **Le premier jour de la semaine est `lundi` et le dernier `dimanche`** : c'est la convention du
  programme francais, et c'est la bonne. Un paquet qui aurait mis `dimanche` en premier aurait
  enseigne faux.
- **Aucun item ne demande de reciter une liste entiere.** L'enfant est toujours interroge sur une
  position, jamais sur une recitation.

### 4. La reserve, non bloquante

**Les items 19 et 20 sont les deux plus faibles du paquet, et ils ne travaillent pas la meme chose
que les vingt et un autres.** *"Pendant quelle saison les feuilles tombent ?"* et *"Pendant quelle
saison les fleurs poussent ?"* sont des faits de nature, pas des faits d'ordre.

- **Ils ne sont pas faux** et ils ne sont pas ambigus **parmi les trois propositions offertes** : sur
  l'item 20, `l'ete` - ou les fleurs poussent aussi - **n'est pas propose**, donc il n'y a qu'une
  reponse recevable. Verifie.
- **Mais l'item 20 serait ambigu si `l'ete` etait propose un jour**, et c'est le genre de chose qu'un
  remaniement de distracteurs casserait sans que personne s'en apercoive.

**Ce que je demande pour un sprint suivant, sans que cela retienne le Done** : que ces deux items
soient soit remplaces par des items d'ordre sur les saisons - *"quelle saison vient juste apres
l'ete ?"* -, soit conserves avec la garantie ecrite que `l'ete` ne peut pas devenir un distracteur de
l'item 20. **C'est une reserve de contenu, pas un motif de refus.**

### 4bis. Seconde reserve - deux enonces sur vingt-trois ne sont pas de la langue ecrite

**Fait mesure par le `relecteur-pedagogique` et route a moi, refait par moi** : sur les vingt-trois
enonces, **deux sont a la forme sans inversion** - les items 21 et 22, *"Le soleil se leve a quel
moment ?"* et *"Le soleil se couche a quel moment ?"*. **Les vingt-et-un autres portent le mot
interrogatif en tete.** *Methode : recherche du mot interrogatif en premiere position sur les
vingt-trois enonces.*

**Il a eu raison de ne pas corriger : ce n'est ni une faute d'orthographe ni une faute de
ponctuation, c'est un registre, et le registre est a moi.**

**Mon jugement, et il tient en deux points.**

- **La forme n'est pas fausse et un CP la comprend** : c'est la question telle qu'on la pose en
  parlant, et c'est meme la plus proche de ce qu'un enfant de six ans entend a la maison. **Je ne
  refuse pas sur ce point.**
- **Mais ce que l'enfant lit ici est de la langue ecrite**, et un paquet qui modele la forme correcte
  vingt-et-une fois puis l'abandonne deux fois n'enseigne rien de cette forme : **c'est
  l'inconsistance qui est le defaut, plus que la forme elle-meme.** Un enfant qui apprend a lire se
  construit des modeles de phrase ; deux exceptions sur vingt-trois les brouillent sans les
  remplacer.

**Ce que je demande pour un sprint suivant, sans que cela retienne le Done** : que ces deux enonces
prennent la forme des vingt-et-un autres. **Je n'ecris pas le texte** ; je pose le resultat, et le
paquet porte deja son propre modele - l'item 23, *"Quand fait-il tout noir dehors ?"*, est la meme
question sur le meme registre, bien formee.

*Je ne fais pas de cela une condition numerotee : elle vaudrait pour tout le depot et non pour les
seuls paquets a venir, et une exigence de forme interrogative sur l'ensemble du contenu enfant
appartient a une relecture systematique, donc au `relecteur-pedagogique`, pas a ma porte
(`DEC-0056`). **Je la lui route comme une question de perimetre** : si la forme interrogative entre
dans son controle systematique, elle cesse d'etre une reserve de ma part.*

### 5. Sur quelles proprietes je reste la seule garde sur ce jeu

- **`SEM-2`** - qu'aucun item ne fasse calculer une duree. Un automate lirait mal la difference entre
  se reperer et calculer.
- **`SEM-7`** - les presupposes. **Aucun automate ne voit un presuppose**, et c'est ici qu'un paquet
  sur la semaine derape le plus facilement.
- **L'exactitude des vingt-trois reponses**, y compris les conventions - premier jour, premier mois,
  ordre des saisons.
- **La plausibilite des distracteurs d'ordre.**

**`SEM-3`, en revanche, je recommande toujours qu'elle devienne un test** : elle se cherche par trois
mots, et c'est la condition qui reintroduirait la contrefacon sans qu'on s'en apercoive. Je l'avais
ecrit a l'ouverture ; le paquet la tient aujourd'hui, **et rien ne garantit que le prochain la
tienne**.

### 6. Ce que ce verdict ne fait pas

- Il **n'est pas fonde sur la CI**.
- Il **ne couvre pas** la moitie *lire l'heure pleine*, et la mention l'ecrit.
- Il **n'exige aucune reecriture** du paquet livre.
- Il **ne modifie aucun fichier** de code ni de contenu.

---

## Verdict du 2026-09-24 (second passage, hors sprint) - paquet corrige, 20 items

# Verdict : **VALIDE SOUS RESERVE** - Done pedagogique confirme sur le paquet a vingt items, deux reserves neuves et datees

**Le Done pedagogique de `bandit-manchot-semaine-cp` tient sur le paquet tel qu'il est aujourd'hui.**
`camille-po` peut laisser `MAT-CP-09` a `couvert-valide` : **la mention exigee le 2026-08-31 ne
bouge pas d'un mot** et reste celle-ci -

> **"moitie 'se reperer dans la journee, la semaine' seule ; la moitie 'lire l'heure pleine sur une
> horloge' reste non couverte"**

**Aucune ligne de matrice n'a a bouger.** La mention ne portait deja que sur la journee et la
semaine ; le retrait des mois et des saisons retire du paquet ce qui n'etait, de toute facon, pas
compte sur cette ligne-la.

### 1. Ce que j'ai fait, et ce que je n'ai pas fait

**J'ai joue, ce n'est pas un audit degrade.** Jar reconstruit depuis `round-two` a jour
(`0d48d2a3`), application lancee, parties jouees par appels HTTP reels sur le jar - le meme geste
qu'un enfant qui appuie sur un bouton. J'ai verifie que le contenu servi par le jar est bien le
paquet corrige : **vingt items dans l'archive servie**, pas vingt-trois.

- **Une partie entiere sans erreur**, ecran par ecran.
- **Une partie entiere tout faux sur le meme item**, pour voir ce que l'enfant recoit apres une, deux
  puis trois erreurs, et ce qu'il recoit ensuite.
- **Une partie mi-juste mi-fausse**, jusqu'a l'ecran de fin.
- **Cent quarante parties supplementaires** jouees en enchainement pour voir **les vingt items, y
  compris les huit neufs** : `items distincts vus : 20 sur 20`, aucun item injoignable.
- **Et j'ai relu les vingt items un par un** - enonce, bonne reponse, deux distracteurs, indice.
  Aucun echantillon.

**Ce que le jeu fait apres une erreur, verifie a l'ecran** : premiere et deuxieme erreur, *"Ce n'est
pas ca. Essaie encore !"* avec l'indice ; **des la troisieme, le jeu designe la bonne reponse** -
*"Appuie sur la bonne reponse : vendredi"*. **Un enfant bloque n'est jamais bloque.** L'ecran de fin
separe *"Tu as trouve, tout seul : 1"* de *"Tu as joue toutes les questions : 10"*, sans reproche.
*C'est la mecanique deja validee ailleurs ; je la reverifie parce que je juge un increment joue, pas
un fichier.*

**Une partie tire dix items sur vingt et ne repete jamais deux fois le meme item** - verifie sur
quatre-vingts parties : `parties avec un item repete : 0`.

### 2. `SEM-1` a `SEM-9` sur le paquet complet d'aujourd'hui

**`SEM-1` est verifiee dans sa redaction corrigee du 2026-09-24. Les huit autres sont verifiees dans
leur redaction d'origine, que je ne durcis pas.**

| Condition | Verdict | Ce que j'ai verifie, et comment |
|---|---|---|
| **`SEM-1`** (amendee) - moments de la journee et jours de la semaine, rien d'autre | **TENUE, 20 sur 20** | Relu item par item et classe : **douze items sur les jours, huit sur les moments de la journee**. **Zero item sur les mois, zero sur les saisons.** Les onze items que j'avais nommes sont partis, et rien n'est rentre par une autre porte |
| **`SEM-2`** - aucun item ne demande de calculer une duree | **TENUE, 20 sur 20** | Aucun item ne fait calculer. Les huit neufs demandent une **position** dans une suite, jamais un ecart entre deux |
| **`SEM-3`** - aucune horloge, aucun cadran, aucune aiguille | **TENUE** | Recherche de `horloge`, `cadran`, `aiguille`, et j'ai ajoute `heure` et `minute` : **zero occurrence** sur les vingt enonces et les vingt indices |
| **`SEM-4`** - enonce au plus 12 mots, aucun mot de plus de neuf lettres | **TENUE, 20 sur 20** | Compte mecanique : **enonce le plus long, 9 mots** (*"Quel est le premier jour de la semaine ?"* et son jumeau sur le dernier jour) ; **mot le plus long du paquet, `dimanche`, huit lettres**. La marge s'est elargie, parce que `septembre` est parti |
| **`SEM-5`** - distracteurs de la meme categorie que la bonne reponse | **TENUE, 20 sur 20** | Verifie categorie par categorie contre les deux listes fermees qui restent (jours, moments). **Aucun melange** : on ne repond jamais a une question sur un jour par un moment de la journee |
| **`SEM-6`** - au moins un tiers d'items d'ordre | **TENUE largement : 15 sur 20**, plancher a 7 | Onze items en *"juste apres"* ou *"juste avant"*, quatre en *"entre ... et ..."*. **Meme en ne comptant que les onze stricts, le plancher est double** |
| **`SEM-7`** - aucun presuppose | **TENUE** | Les huit items neufs relus un par un pour ce seul motif : aucune configuration familiale, aucun rythme scolaire, aucun depart en vacances, aucune fete, aucun pays. **Le mercredi et le samedi ne portent toujours aucune activite**, alors que quatre des huit items neufs les nomment |
| **`SEM-8`** - indice qui dit quoi se rappeler, huit mots au plus | **TENUE** | **Deux indices** desormais, un par famille, **six mots chacun** : *"Recite les jours de la semaine."* et *"Pense au fil de la journee."* Aucun ne donne la reponse. Les deux indices sur les mois et les saisons sont partis avec leurs items |
| **`SEM-9`** - au moins vingt items, deux items ne posent jamais la meme question | **TENUE, au plancher exact** | **20 items, 20 enonces distincts.** Comparaison mecanique des vingt enonces : aucun doublon. **Le paquet est exactement a la borne, sans marge** : le prochain retrait d'item, quel qu'en soit le motif, le fait tomber sous ma condition |

### 3. Ce que j'ai verifie sur le fond des huit items neufs

**L'exactitude d'abord, parce que c'est le seul point ou je refuse sans discuter.** Les huit reponses
sont justes, y compris les deux qui traversent la fin de la semaine : *"Quel jour vient entre samedi
et lundi ?"* - `dimanche` - est exact **parce que la semaine du programme francais va de `lundi` a
`dimanche`**, et le paquet le pose lui-meme deux fois (*premier jour*, *dernier jour*). Le cycle
etait deja admis par l'item *"juste apres dimanche"* du paquet valide ; le nouvel item ne l'invente
pas, il s'y accorde.

**Les quatre items neufs sur les jours sont bien faits.** Leurs distracteurs sont **les erreurs qu'un
enfant fait reellement** : pour *"juste apres lundi"*, on propose `dimanche` - le jour d'avant, donc
la confusion avant/apres - et `mercredi` - deux apres. C'est exactement la propriete que je creditais
le 2026-08-31, et elle tient sur ces quatre-la.

**`l'apres-midi` entre dans le paquet, et c'est bien.** C'est le seul moment de la journee que le
paquet ignorait ; **je l'avais moi-meme nomme dans `SEM-1`**, des la redaction d'origine. Il compte
dix lettres avec son trait d'union et **ce n'est pas un motif** : `SEM-4` n'a jamais porte que sur
l'enonce, jamais sur les propositions, et je ne l'etends pas apres coup sur un paquet deja livre. Un
enfant de CP entend ce mot tous les jours et le decoupe en syllabes sans peine.

**L'equilibre du paquet a change, et dans le bon sens** : les deux objets de la ligne - la journee et
la semaine - sont desormais servis huit contre douze, la ou les mois et les saisons prenaient onze
items sur vingt-trois. **Le paquet travaille maintenant la competence annoncee sur la totalite de ses
items**, et c'est le resultat que `DEC-0096` cherchait.

### 4. Reserve neuve n. 1 (2026-09-24) - deux items se repondent l'un l'autre sans etre lus

**C'est une reserve nouvelle et datee, pas un durcissement d'une ancienne** : je l'ai mesuree absente
du paquet que j'ai valide le 2026-08-31, et presente dans le paquet corrige. Ce que je verifie avant
de m'en servir, et voici la mesure.

**Le defaut.** Deux paires d'items offrent **exactement les trois memes propositions et exactement la
meme bonne reponse** :

- *"Quand fait-il tout noir dehors ?"* et *"Quel moment vient juste apres le soir ?"* - propositions
  `la nuit` / `le matin` / `le midi`, reponse `la nuit` dans les deux cas ;
- *"Quel moment vient juste apres le midi ?"* et *"Quel moment vient juste avant le soir ?"* -
  propositions `l'apres-midi` / `la nuit` / `le matin`, reponse `l'apres-midi` dans les deux cas.

**Consequence a l'ecran** : quand les deux items d'une paire tombent dans la meme partie, **le second
se repond en se rappelant le bouton, sans lire la question**. Ce n'est plus un exercice de reperage,
c'est un exercice de memoire immediate.

**Ce n'est pas theorique, c'est mesure sur des parties jouees** : sur quatre-vingts parties, la
premiere paire est tombee ensemble **24 fois**, la seconde **14 fois**. **Environ deux parties sur
cinq contiennent au moins une de ces paires** - une manche sur dix qui n'apprend rien, assez souvent
pour que l'enfant le remarque avant nous.

**Pourquoi c'est une reserve et non un refus.** Rien n'est faux, rien n'est ambigu, aucune de mes
neuf conditions n'est enfreinte : `SEM-9` interdit que deux items posent la meme question, et ces
items posent bien des questions differentes. **Le paquet reste utilisable tel quel.**

**Ce que j'avais ecrit sans le conditionner, et qui s'est perdu ici.** Le paquet du 2026-08-31
portait deja une paire d'items aux trois memes propositions - les deux items de saisons - **mais avec
deux bonnes reponses differentes**, ce qui est le contraire d'un defaut : trois memes boutons et deux
reponses opposees **obligent** a lire. Les huit items neufs ont repris la forme et perdu la
propriete.

**Ce que je demande pour un sprint suivant, sans que cela retienne le Done** : que **l'une des deux
paires de propositions soit modifiee dans chaque paire**, de facon qu'aucun couple d'items du paquet
n'offre a la fois le meme triplet et la meme reponse. **Je n'ecris pas le contenu**, je pose le
resultat attendu.

### 5. Reserve neuve n. 2 (2026-09-24) - sur deux items neufs, le distracteur voisin manque

**Meme regime : reserve nouvelle et datee.** Sur *"Quel moment vient juste apres le midi ?"*
(`l'apres-midi`), les distracteurs sont `le matin` et `la nuit` ; sur *"Quel moment vient juste avant
le soir ?"* (`l'apres-midi`), ce sont les memes. **Dans les deux cas, le voisin immediat dans la
suite manque** : `le soir` pour le premier, `le midi` pour le second. **Or c'est precisement la
reponse qu'un enfant donne quand il saute `l'apres-midi`** - l'erreur que ces deux items sont censes
attraper.

**Consequence** : ces deux items **se repondent en eliminant deux propositions manifestement
eloignees**, et ils sont plus faciles que les items sur les jours de la meme forme, qui offrent tous
leur voisin. Le 2026-08-31 j'avais credite le paquet de cette propriete par son nom - *"les deux sont
les erreurs qu'un enfant fait reellement, pas des reponses au hasard"*. **Elle n'etait pas une
condition numerotee, et je ne la transforme pas en condition aujourd'hui** : je constate qu'elle
n'est pas tenue sur ces deux items-la et je le route comme une reserve.

**Ce que je demande pour un sprint suivant** : que chacun de ces deux items offre **le moment voisin
dans la suite** parmi ses distracteurs. *Remarque a l'attention de `camille-po` : la meme retouche
paie les reserves n. 1 et n. 2 d'un seul geste sur la seconde paire.*

### 6. Ou en sont les deux reserves du 2026-08-31

- **La reserve de la section 4 (items de saisons ambigus si `l'ete` devenait un distracteur) est
  CLOSE, et non pas levee** : les deux items n'existent plus. Le risque que je decrivais a disparu
  avec son objet, par un chemin qui n'etait pas celui que je demandais. **Je le note comme une
  cloture, pas comme une reponse a ma demande.**
- **La reserve de la section 4bis (forme interrogative) RESTE OUVERTE, inchangee et non aggravee** :
  *"Le soleil se leve a quel moment ?"* et *"Le soleil se couche a quel moment ?"* sont toujours la,
  et restent **les deux seuls enonces du paquet sans inversion** - deux sur vingt, la ou c'etait deux
  sur vingt-trois. **Les huit items neufs portent tous le mot interrogatif en tete** et n'ont donc
  rien ajoute au defaut. La reserve reste routee comme elle l'etait, au `relecteur-pedagogique` pour
  le perimetre, a `camille-po` pour le contenu.

### 7. Un constat que je pose sans en faire une reserve

**`jeudi` n'est jamais une bonne reponse du paquet** - il n'apparait que comme repere dans un enonce
ou comme distracteur. **Ce n'etait deja pas le cas dans le paquet du 2026-08-31 : je l'ai verifie
avant de l'ecrire**, donc le correctif n'a rien introduit ici et je ne le lui impute pas. Je le pose
comme une observation a l'usage de `camille-po` si un enrichissement du paquet se presente un jour ;
**ce n'est ni une condition, ni une reserve, et cela ne retient rien.**

### 8. Sur quelles proprietes je reste la seule garde sur ce jeu

`mvn verify` est vert sur ce correctif et **cela ne dit rien de ce qui suit**. Aucun test de ce depot
ne voit :

- **`SEM-1` dans sa redaction corrigee** - qu'aucun item ne rentre par les mois ou les saisons. Le
  paquet est propre aujourd'hui ; **rien n'empeche un item de revenir demain**, et c'est exactement
  ainsi que l'ecart d'origine s'est installe.
- **`SEM-2`** - qu'aucun item ne fasse calculer une duree. Un automate lirait mal la difference entre
  se reperer et calculer.
- **`SEM-7`** - les presupposes. **Aucun automate ne voit un presuppose.**
- **L'exactitude des vingt reponses**, y compris la convention de la semaine et les deux items qui
  traversent sa fin.
- **La plausibilite des distracteurs** - la reserve n. 2 est la preuve qu'elle se perd sans bruit
  sous une CI verte.
- **Le triplet de propositions identique a bonne reponse identique** (reserve n. 1). **C'est une
  propriete qu'un test verrait sans peine** - elle se calcule sur le fichier, sans jouer - et je
  recommande qu'elle en devienne un. **Tant qu'il n'existe pas, c'est moi qui la garde, et je ne l'ai
  vue qu'en jouant.**
- **`SEM-3`** - je maintiens ma recommandation du 2026-08-31 : elle se cherche par trois mots et
  devrait etre un test.

### 9. Ce que ce second verdict ne fait pas

- Il **n'efface ni ne rouvre** le verdict du 2026-08-31, qui reste ecrit au-dessus et qui a ete rendu
  contre la redaction d'alors.
- Il **n'est pas fonde sur la CI** : la CI ne sait rien des vingt enonces.
- Il **ne couvre toujours pas** la moitie *lire l'heure pleine*, et la mention l'ecrit.
- Il **ne modifie aucun fichier** de code ni de contenu.
- Il **ne change aucune ligne de la matrice** et n'en demande aucune.
