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
