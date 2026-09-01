# Fiche de conformite - `bandit-manchot-semaine-cp`

- **Jeu** : `bandit-manchot-semaine-cp`, paquet `cp-semaine`, 23 items.
- **Competence visee** : Mathematiques CP, "Grandeurs et mesures" - *"Se reperer dans la journee, la
  semaine ; lire l'heure pleine sur une horloge"*, **moitie *se reperer* seule**.
- **Conditions opposables** : `SEM-1` a `SEM-9` (2026-08-31).
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
