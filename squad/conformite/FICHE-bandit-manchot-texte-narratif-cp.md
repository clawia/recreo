# Fiche de conformite - bandit-manchot-texte-narratif-cp

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-texte-narratif.yaml`,
  `type: question`, **20 items**, tous a `texteSupport`, story `S24-NAR` (`DEC-0059`). Ecriture
  neuve de `lea-dev`.
- **Entree de catalogue** : `bandit-manchot-texte-narratif-cp`, titre *"Lis et réponds"*, consigne
  *"Lis le petit texte, puis réponds à la question."*, 10 manches, niveau CP.
- **Conditions applicables** : `NAR-1` a `NAR-10`, arbitrage prealable de `nadia-prof` du
  2026-08-30, complete par l'arbitrage prealable de s24, section 5.1 (satisfaisabilite de `NAR-2`
  contre `NAR-5`, et unites de compte).
- **Type `COMP-2`** : ce paquet consomme a lui seul ma condition d'au plus un paquet de ce type par
  sprint. `NAR-6` n'est vue par aucun automate.
- **Statut global** : **Done pedagogique prononce le 2026-08-31, sous une reserve non bloquante.**

## Competences visees

Francais CP, lecture et comprehension de l'ecrit - *"Comprendre un texte narratif tres court
(questions explicites : qui, ou, quoi)"*. **Une seule ligne de matrice.**

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-08-31 (s24) - `S24-NAR`

## Verdict : **VALIDE SOUS RESERVE** - une reserve, non bloquante

Done pedagogique ouvert. `camille-po` peut passer la ligne de matrice a `couvert-valide` et fera de
la reserve 1 une story d'un sprint suivant.

### 1. Ce que j'ai fait

**J'ai joue**, depuis le jar servi construit sur l'arbre de cloture, sur le JDK 21 de la toolchain
declaree. Le jeu s'ouvre depuis la liste du niveau CP. J'ai joue plusieurs parties et **repondu faux
volontairement**.

**Ce que j'ai vu** : le texte support s'affiche entre la consigne et la question, dans un style
distinct des deux ; les trois propositions sont sous la question ; un appui faux rend *"Ce n'est pas
ça. Essaie encore !"* et fait apparaitre l'indice ; la proposition pressee reste marquee ; au
troisieme echec la reponse est nommee et l'enfant peut avancer.

**Puis j'ai lu les 20 items, tous.** Sur un paquet de type `COMP-2`, `NAR-6` se verifie **item par
item, texte masque**, et c'est ce que j'ai fait : sur les 20, la question ne se repond pas sans le
texte. Les items *"Qui"* proposent trois prenoms tous presents dans le texte, les items *"Ou"* trois
lieux tous presents, les items *"Que"* trois objets tous presents. **Aucun ne se devine.**

### 2. Mes dix conditions, une par une

*Mesures du 2026-08-31, par lecture du fichier YAML servi, sur les 20 items. Unites de compte :
celles de mes conditions - le mot est une suite de caracteres sans espace ; le mot graphique de
`NAR-3` est delimite par les espaces, les apostrophes **et les traits d'union**, precision que j'ai
rendue le 2026-08-30. Aucun build.*

| Condition | Verdict | Mesure ou lecture |
|---|---|---|
| **`NAR-1`** - support en `texteSupport`, item en `enonce` | **tenue** | Les 20 items portent les deux cles |
| **`NAR-2`** - support au plus 18 mots et deux phrases ; support plus enonce au plus 25 mots | **tenue, a la borne** | Support le plus long : **18 mots**, borne 18. Phrases : **2 au plus**. Support plus enonce : **23 mots** au plus, borne 25 |
| **`NAR-3`** - aucun mot de plus de neuf lettres | **tenue, a la borne** | Mot le plus long : **9 lettres**, borne 9. *Sous une unite de compte qui ne couperait pas aux traits d'union, trois formes verbales inversees passeraient a onze - `range-t-elle`, `oublie-t-elle`, `laisse-t-elle`. **C'est l'unite que j'ai fixee le 2026-08-30 qui s'applique**, et je le note pour qu'un relecteur ne croie pas a un depassement* |
| **`NAR-4`** - question explicite, commencant par *Qui*, *Ou* ou *Que* | **tenue** | Les 20 enonces. **Aucune inference demandee** : la reponse se lit litteralement |
| **`NAR-5`** - les trois propositions figurent en toutes lettres dans le support de leur propre item | **tenue** | **Zero absence sur les 60 propositions.** C'est la condition qui empeche un lecteur debutant de reussir par simple appariement de chaines |
| **`NAR-6`** - texte masque, la question ne se repond pas | **tenue** | Verifie item par item, sur les 20, jamais sur un echantillon. **Aucun automate ne voit cette propriete** |
| **`NAR-7`** - prenoms equilibres en genre a un pres, aucun role distribue par genre | **tenue** | 20 prenoms distincts, **10 masculins et 10 feminins**. Actions melangees : les filles rangent, ouvrent une fenetre, comptent des marches, fouillent un sac, oublient une craie ; les garcons ramassent, posent, effacent, plient, deposent |
| **`NAR-8`** - aucune situation ne suppose une configuration familiale, un logement, un animal, des vacances ou un objet possede | **tenue sur 19 items** | Voir la **reserve 1** : l'item 2 fait aller Nina *"au salon"* |
| **`NAR-9`** - l'indice dit ou regarder, jamais la reponse ; huit mots au plus | **tenue** | Le plus long : **7 mots**, borne 8. Aucun indice ne contient la bonne reponse |
| **`NAR-10`** - au moins vingt items, deux items ne partagent jamais un support | **tenue** | 20 items, **20 supports distincts** |

### 3. Reserve 1 - *"au salon"*, item 2, contre `NAR-8`

**Le fait.** Item 2 : *"Nina range le ballon sous l'escalier. Puis elle va au salon et s'assoit près
de la fenêtre."* Question : *"Où Nina range-t-elle le ballon ?"*, reponse *"sous l'escalier"*.

**Ce qui accroche.** `NAR-8` interdit qu'une situation suppose **un logement**. *"Au salon"* nomme
une piece d'habitation ; tous les enfants n'en ont pas une, et le reste du paquet est entierement
situe a l'ecole - cour, classe, preau, tableau, couloir, scene, atelier, hall. **L'item 2 est le
seul a sortir de ce decor, et il en sort par le seul mot que ma condition nomme.**

**Pourquoi c'est une reserve et non un refus.** L'item reste jouable et exact : la reponse ne depend
pas du salon, `NAR-5` est tenue puisque *"au salon"* est un des trois lieux proposes, et un enfant
qui ne connait pas le mot repond quand meme. Le cout est une exclusion legere, pas un blocage. **Je
ne refuse pas un paquet de vingt items pour un mot qui n'empeche personne de jouer.**

**Ce que je demande, et c'est actionnable sans me redemander ce que je voulais dire** : que le lieu
*"au salon"* de l'item 2 soit remplace par un lieu qui ne suppose aucun logement, en gardant les
trois lieux presents dans le support (`NAR-5`) et la reponse dans la premiere phrase (l'equilibre
mesure en section 4). **Je ne redige pas le texte** : je pose le resultat.

### 4. Un fait que je verse, et qui n'est pas une reserve

**La strategie a phrase fixe.** `ADR-0072` a ete ecrit ce jour a partir d'un defaut que `lea-dev` a
trouve **en jouant son propre paquet**, et qu'aucun automate ne voyait. Son paquet corrige passe le
garde-fou a **50,0 % pile, marge nulle**.

**J'ai refait la mesure, independamment** - meme decoupage en phrases sur les trois ponctuations
fortes, meme repliage de casse, meme occurrence par sous-chaine, sur les 20 items du paquet servi :

| Phrase lue seule | Gains **decisifs** (la metrique du garde-fou) | **Esperance de gain** |
|---|---|---|
| phrase 1 | 10 sur 20, **50,0 %** | **56,7 %** |
| phrase 2 | 10 sur 20, **50,0 %** | 50,0 % |

**Je reproduis le 50,0 % a la decimale**, ce qui vaut verification de la mesure de `theo-archi`. La
colonne de droite est la mienne : elle ajoute le cas ou la phrase lue porte la bonne reponse **et**
un distracteur, que le garde-fou compte a zero parce qu'il compte des gains decisifs. **Sur ces
items l'enfant n'est pas ramene au hasard, il est ramene a un choix entre deux.** Contre 33,3 % par
hasard, l'avantage est reel.

**Ce que je n'en fais pas, et je le dis pour qu'on ne me le prete pas** : je ne refuse pas et je ne
pose pas de reserve sur ce fondement. **Aucune de mes conditions `NAR-1` a `NAR-10` ne portait cette
propriete avant aujourd'hui**, et refuser sur une barre posee apres coup serait deplacer mes
poteaux. `NAR-11`, ecrite le 2026-08-31, est **non retroactive** et sans seuil chiffre : elle vaut
pour les paquets a texte support suivants.

**Ce que j'ajoute, parce que c'est utile et que personne ne l'a dit** : la marge nulle est epinglee
dans un **contre-exemple gele**, pas dans le paquet servi. **Ameliorer le paquet servi ne casse
aucun test et ne coute rien.** La lecture inverse - *"on ne peut pas y toucher, un test epingle
50,0 %"* - serait fausse et decouragerait la seule amelioration disponible.

**Et je note ce que `lea-dev` a fait** : elle a joue son propre paquet avant de le pousser et y a
trouve un defaut qu'aucun automate ne voyait. C'est exactement la conduite que je decris comme la
seule garde disponible sur cette famille de proprietes. Que sa **description** du defaut ait ete
inexacte - `theo-archi` l'a corrigee et il a eu raison - ne retire rien au constat.

### 5. Contenu et niveau

- **Exactitude** : les 20 reponses sont justes, chacune litteralement dans son texte. **Aucune
  erreur factuelle.**
- **Lisibilite CP** : deux phrases courtes, une question de quatre a six mots, trois propositions
  breves. C'est au niveau.
- **Charge cognitive** : l'enfant tient un texte de deux phrases et une question. C'est le maximum
  raisonnable au CP, et `NAR-2` le borne.
- **Traitement de l'erreur** : l'indice renvoie a une phrase precise - *"Relis la deuxième
  phrase."*, *"Regarde la fin de la première phrase."* - ce qui apprend **ou chercher**. C'est ce
  qu'un indice de comprehension doit faire.
- **Biais et ton** : equilibre verifie en section 2. Aucun ton condescendant.

### 6. Sur quelles proprietes je reste la seule garde

- **`NAR-6`** integralement. C'est la definition de `COMP-2`.
- **`NAR-4`** - qu'aucune question ne demande une inference.
- **`NAR-7`** - la distribution des roles, que le seul comptage des prenoms ne dit pas.
- **`NAR-8`** - ce qu'une situation presuppose. **C'est cette garde-la qui a trouve la reserve 1**,
  et rien d'autre ne l'aurait trouvee.
- **La classe aveugle du garde-fou d'accentuation** sur les formes verbales de ce paquet.
- **`NAR-11`** desormais, pour les paquets suivants.

### 7. Ce que ce verdict fait, et ce qu'il ne fait pas

- Il **ouvre le Done pedagogique** sur `bandit-manchot-texte-narratif-cp`, avec **une** reserve
  nommee et actionnable.
- Il **ne bloque pas** la ligne de matrice.
- Il **n'est pas fonde sur la CI**, ni sur le vert d'`ADR-0072`.
- Il **ne dit rien** du score de fin de partie, qui reste sous la reserve 5 elargie du 2026-08-30 et
  tranche par `DEC-0061`.

---

### Verdict du 2026-08-31, second passage (s24) - apres le correctif de `lea-dev`

## Verdict : **VALIDE** - la reserve 1 est **levee**

**Le verdict du premier passage n'est pas efface.** Il est date, il porte sa reserve, et c'est lui
qui a fait exister le correctif.

*Arbre joue : ma branche `s24-nadia-2`, qui reunit les trois correctifs de ce second passage.
Empreinte du fichier audite : `3f3911392df562af8c3e6284ed15d474f273a7fe`.*

### 1. La reserve 1 - ce que je demandais, et ce que j'ai obtenu

**Ce que je demandais**, mot pour mot : *"que le lieu au salon de l'item 2 soit remplace par un lieu
qui ne suppose aucun logement, en gardant les trois lieux presents dans le support (`NAR-5`) et la
reponse dans la premiere phrase"*.

**Ce que j'ai verifie sur l'item livre, et joue a l'ecran** :

| Ce que j'exigeais | Etat |
|---|---|
| Aucun lieu ne suppose un logement | **tenu.** Le lieu d'habitation a disparu ; celui qui le remplace est un equipement scolaire, comme le reste du paquet. Balayage du paquet entier : **aucun mot de logement ni de configuration familiale** |
| Les trois lieux presents dans le support (`NAR-5`) | **tenu.** Les trois propositions figurent en toutes lettres |
| La reponse dans la premiere phrase | **tenu** |
| `NAR-2` | **tenu.** Support **17 mots** (borne 18), **2 phrases**, support plus enonce **22 mots** (borne 25) |
| `NAR-3` | **tenu.** Mot le plus long de l'item : **8 lettres** (borne 9) |

### 2. Ce qu'elle a fait de plus que ce que je demandais, et qui vaut mieux

**Elle n'a pas seulement retire le mot : elle a deplace un distracteur dans la premiere phrase.**

Avant, la premiere phrase portait la bonne reponse et rien d'autre : un enfant qui ne lisait que la
premiere phrase gagnait. **Maintenant elle porte la bonne reponse et un distracteur**, et le
personnage **passe** pres d'un lieu sans y ranger l'objet. L'enfant doit donc distinguer deux verbes
plutot que reperer un lieu.

**Je l'ai joue** : j'ai presse le distracteur de la premiere phrase, il est refuse, et l'indice
*"Relis la première phrase."* renvoie a une phrase ou les deux lieux cohabitent. **L'indice dit
toujours ou regarder et il oblige maintenant a lire le verbe.** C'est un meilleur item qu'avant, pas
seulement un item conforme.

Sa formule - *"l'enfant doit lire le verbe, pas reperer un lieu"* - est exactement ce que la
competence demande : *"comprendre un texte narratif tres court"*, pas apparier des chaines.

### 3. Les quatre chiffres, reproduits par moi

**Elle a reproduit mes quatre chiffres sur l'etat d'avant avant de mesurer l'apres. J'ai refait les
huit.**

*Methode : la mienne du 2026-08-31, celle qui a servi au premier verdict - decoupage sur les trois
ponctuations fortes, repliage de casse, occurrence par sous-chaine, sur les 20 items ; gain decisif
quand la phrase porte la bonne reponse et aucun distracteur ; esperance de gain quand elle porte la
bonne reponse et des distracteurs. Aucun build.*

| | Gains decisifs, phrase 1 | Esperance, phrase 1 | Gains decisifs, phrase 2 | Esperance, phrase 2 |
|---|---|---|---|---|
| **Avant** | 10 / 20 = **50,0 %** | **56,7 %** | 10 / 20 = 50,0 % | 50,0 % |
| **Apres** | 9 / 20 = **45,0 %** | **54,2 %** | 10 / 20 = **50,0 %** | 50,0 % |

**Les quatre chiffres qu'elle annonce sont exacts a la decimale**, et les quatre d'avant aussi.

**Et elle ne cache pas ce qui ne bouge pas** : le maximum reste a **50,0 %**, desormais porte par la
**seconde** phrase - a laquelle l'item corrige n'apportait deja rien. **Dire cela plutot que
d'annoncer une baisse est la seule conduite qui rende un chiffre utilisable**, et je la note.

### 4. Le paquet entier, reverifie et non suppose

Un item a change ; deux de mes conditions se comptent sur le paquet et pouvaient bouger avec lui. Je
les ai toutes refaites plutot que de raisonner sur le diff.

| Condition | Etat apres correction |
|---|---|
| `NAR-2` | support max **18 mots**, **2 phrases** max, support plus enonce max **23 mots** |
| `NAR-3` | mot le plus long **9 lettres**, a la borne |
| `NAR-4` | les 20 enonces commencent par *Qui*, *Ou* ou *Que* |
| `NAR-5` | **zero proposition absente** de son support, sur les 60 |
| `NAR-7` | **20 prenoms distincts, 10 masculins et 10 feminins.** L'item corrige reemploie un prenom deja present : l'equilibre est inchange |
| `NAR-8` | **aucun mot de logement ni de configuration familiale** dans le paquet |
| `NAR-9` | indice le plus long **7 mots**, borne 8 ; aucun indice ne nomme sa reponse |
| `NAR-10` | 20 items, **20 supports distincts** |

### 5. Faut-il aller plus loin ? Non, et le motif n'est pas la fatigue

`lea-dev` refuse de rouvrir seule un audit `COMP-2` et remonte le point : descendre le maximum de
50,0 % demanderait deux ou trois items de plus **et mon accord prealable**. Elle a raison de ne pas
le faire sans moi. **Ma reponse est non, et pour un motif de fond.**

**Ajouter des items ne retire aucun raccourci : cela dilue un denominateur.** Les dix items
gagnables en ne lisant que la seconde phrase resteraient gagnables, un par un, exactement comme
aujourd'hui. Avec 23 items au lieu de 20, le taux tomberait mecaniquement vers 43 pour cent et le
garde-fou verdirait **sans qu'un seul enfant ait a lire une phrase de plus**. **Ce serait rendre un
compteur plus vert en laissant le defaut ou il est**, c'est-a-dire la famille de defaut exacte que ce
depot vient de sanctionner trois fois en un jour. *"Compter une population n'est pas la juger"*
vaut aussi quand c'est moi qui commanderais le comptage.

**Ce qui retirerait vraiment un raccourci est ce que `lea-dev` a fait sur l'item 2** : mettre un
distracteur dans la meme phrase que la reponse, pour qu'aucune phrase seule ne decide. Cela se fait
**item par item, sur les items existants**, et c'est un travail de reecriture, pas d'ajout.

**Je n'ouvre pas de reserve neuve sur ce paquet, et je dis pourquoi plutot que de le laisser
supposer.** Trois raisons, dans l'ordre de leur poids :

1. **`NAR-11` est non retroactive, je l'ai ecrite ainsi hier, et je m'y tiens.** Ouvrir aujourd'hui
   une reserve qui applique une barre posee apres coup serait deplacer mes poteaux sur le meme paquet
   deux jours de suite.
2. **Au CP, reperer la phrase qui porte la reponse est une part de la competence**, pas un
   contournement pur. Ce n'est pas comparable a presser toujours la plus grande des trois valeurs
   numeriques sans lire : l'enfant lit une phrase et il apparie ce qu'il y trouve. **Le raccourci est
   reel, et il est moins grave que celui de s12.**
3. **La propriete est desormais gardee pour la suite** : `ADR-0072` la tient sur tout paquet a texte
   support, et `NAR-11` la tient de ma main sur les paquets a venir. **Le trou se referme par
   l'avant, pas par la reecriture du passe.**

**Ce que je dis a `camille-po`, pour que ce ne soit pas a rediscuter** : si le produit veut abaisser
ce taux sur `cp-texte-narratif`, c'est **du backlog et non une affaire de porte** ; le libelle utile
est *"reecrire les items dont une seule phrase decide, pour qu'aucune phrase seule ne suffise"*, et
**surement pas** *"ajouter des items"*. Je ne le demande pas et je ne le bloque pas.

### 6. Ce que ce second verdict fait, et ce qu'il ne fait pas

- Il **leve la reserve 1** et **prononce le Done pedagogique** sur
  `bandit-manchot-texte-narratif-cp`.
- Il **n'efface pas** le verdict du premier passage ni sa reserve, qui restent lisibles avec leur
  date.
- Il **n'ouvre aucune reserve neuve** et n'applique `NAR-11` a aucun item de ce paquet.
- Il **n'est pas fonde sur la CI** ni sur le vert d'`ADR-0072`.
- **Sur quelles proprietes je reste la seule garde** : inchange par rapport au premier passage -
  `NAR-6` integralement, `NAR-4`, `NAR-7`, `NAR-8`, et desormais `NAR-11` pour les paquets suivants.
