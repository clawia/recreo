# Fiche de conformite - bandit-manchot-permis-interdit-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-emc-permis-interdit.yaml`.
- **Entree de catalogue** : `bandit-manchot-permis-interdit-cp`, titre servi *"Permis ou
  interdit"*, consigne servie *"Lis bien la question, puis choisis la bonne reponse."*,
  `nombreDeManches: 10`.
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce le 2026-09-06** par
  `nadia-prof`, apres avoir joue la reprise `4dbf9b2`). **`EMC-CP-02` ferme** : `non couvert` ->
  **`couvert-valide`**. Une reserve nommee et datee, `PER-R1`, non bloquante.
- **Histoire des verdicts, aucun efface** : **2026-09-05, REFUSE** - charge de lecture d'un autre
  niveau (mediane 15 mots, maximum 20) et item 9 portant sa reponse dans son enonce ; le bloc entier
  de ce refus, avec ses deux motifs et ses trois defauts nommes, reste lisible ci-dessous.
  **2026-09-06, VALIDE SOUS RESERVE** apres reprise, second bloc en fin de fiche. **Un refus efface
  n'a aucune valeur de preuve.**
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`,
  section 4.3, et `squad/programme/enseignement-moral-et-civique/competences-cp.md` (2026-09-03).
- **Commits audites** : `5526932` (`[LEA] s29: S29-4`) le 2026-09-05, puis `4dbf9b2` (`[LEA] s29:
  reprise des trois paquets d'EMC refuses par l'instance B`) le 2026-09-06.
- **Derniere mise a jour** : 2026-09-06 (**second verdict de `nadia-prof` apres avoir joue**, s29,
  instance B).

## Competence visee

`Distinguer ce qui est permis de ce qui est interdit dans les relations entre eleves (moquerie,
insulte, violence).` (`EMC-CP-02`, enseignement moral et civique, CP, *Respecter autrui*).

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `ee3ef70`, jar reconstruit et relance, **partie complete de dix
manches par appels HTTP reels**, puis une seconde partie ou je me trompe volontairement six fois de
suite sur le meme item pour voir ce que l'enfant recoit. **Et j'ai lu les onze items** : le tirage
en a laisse un de cote (item 3, *"Malo dit a Lea des mots mechants"*), je l'ai lu.
**Ce n'est pas un audit degrade.**

### 1. Ce qui est juste dans ce paquet, et je le dis avant le refus

- **Aucune erreur de fait.** Les qualifications sont exactes : pousser tres fort sans raison est de
  la violence, dire des mots mechants pour vexer est une insulte, rire de quelqu'un a repetition est
  une moquerie. **Rien de faux n'est enseigne.**
- **`FER-1` est tenue** : les trois termes de la parenthese sont chacun en position de bonne
  reponse - *une moquerie* (items 1 et 9), *une insulte* (item 3), *de la violence* (items 5 et 11).
- **`FER-2`** : 11 items, au-dessus du plancher de dix et strictement plus que les dix manches.
- **`EMC-1` et `EMC-8` sont tenues** : aucun item ne demande a l'enfant ce qu'il fait, ressent ou
  ferait ; les onze situations mettent en scene des personnages nommes.
- **`EMC-10`** : aucune personne reelle, aucun parti, aucune organisation. Tenue.
- **`EMC-3` (amendee le 2026-09-05) n'est pas violee** : aucun item ne met l'enfant qui joue en
  position de victime ou d'auteur, aucune bonne reponse n'est une solution qu'il appliquerait seul,
  aucun item ne detaille une violence.
- **Le chemin d'erreur est bon** : l'enfant reste sur l'item et recoit un indice qui decrit sans
  nommer. Il n'est jamais bloque.

**Ce paquet n'est pas mal ecrit. Il est ecrit pour un lecteur plus age que celui a qui il
s'adresse**, et c'est le premier des deux motifs.

### 2. **Premier motif de refus - la charge de lecture est celle d'un autre niveau, et elle est systematique**

**Mesure, pas impression.** Les onze enonces, en nombre de mots :

`7, 9, 11, 13, 14, 15, 15, 17, 18, 20, 20` - **mediane 15, maximum 20, deux enonces a 20 mots.**

**Comparaison, et elle n'est pas choisie pour m'arranger** : le paquet **jumeau**, ecrit par la
**meme developpeuse**, le **meme jour**, sur le **meme niveau**, avec la **meme mecanique**
(`cp-emc-regles-de-vie`), est a **mediane 10, maximum 15**. Et le paquet de CP sur lequel j'ai pose
une reserve de longueur a s28 (`cp-qlm-corps-sens`) etait a **mediane 9**, avec **un seul** item a
19 mots.

**Ce que cette comparaison etablit, et c'est ce qui fait la difference entre une reserve et un
refus.** A s27 et a s28, j'ai deux fois traite la longueur comme une **reserve non bloquante** : les
deux fois, le defaut etait **un item isole**, a plus du double de la mediane de son propre paquet,
dans un paquet par ailleurs lisible. **Ici il n'y a pas d'item aberrant : c'est la mediane qui est
au niveau ou etaient mes items aberrants.** Cinq enonces sur onze depassent 15 mots. Ce n'est pas un
outlier a corriger, c'est le paquet a reecrire - et ce n'est donc pas la meme famille de defaut,
donc pas le meme verdict. **Je ne durcis pas ma barre de s28 : je constate qu'elle ne s'applique
pas, parce qu'elle porte sur un ecart a la mediane et qu'ici l'ecart n'existe pas.**

**Deux enonces cites en entier, parce qu'un chiffre ne dit pas ce qu'un enfant de six ans voit :**

> *"Hugo repete sans arret qu'Aya a une drole de voix, pour la faire rire des autres. Comment
> appelle-t-on ca ?"*

> *"Malo dit a Lea des mots mechants pour la vexer, sans meme la toucher. Qu'est-ce que Malo lui
> fait ?"*

Deux phrases, vingt mots, une proposition de but subordonnee, une incise negative (*"sans meme la
toucher"*), et une question abstraite (*"comment appelle-t-on ca"*) qui demande de nommer une
categorie. **Un enfant de CP lit peu et lentement** : quand il arrive a la question, il a perdu la
situation. Et l'incise *"sans meme la toucher"*, qui allonge la phrase de quatre mots, ne sert a
rien - elle est la pour distinguer l'insulte de la violence, mais **aucun des deux distracteurs de
cet item n'est la violence** (*un service*, *un cadeau*). Elle coute de la lecture et ne discrimine
rien.

**La premisse de l'adulte accompagnant ne repare pas ce motif, et je le dis avant qu'on me
l'oppose.** `CLAUDE.md` l'ecrit : cette premisse *"ne dispense d'aucune exigence de lisibilite : le
contenu reste ecrit pour un enfant qui lit encore peu"*, et elle ne vaut comme moyen de jeu **que
sur les lignes ou la voix est la competence visee**. L'EMC n'en est pas une.

**Et cette barre-la, je l'avais ecrite d'avance et pour cette ligne precise**, le 2026-09-03, dans
le referentiel : ligne 2, motif, en toutes lettres - *"Enonces courts, reponse unique, exacte."*

> **Ce qu'il faut atteindre, et c'est actionnable sans me redemander ce que je veux dire** :
> **mediane des enonces a 10 mots, aucun enonce au-dessus de 15**, c'est-a-dire le profil que le
> paquet jumeau du meme lot atteint deja. **Une seule phrase par enonce quand c'est possible.**
> Je donne la propriete et la cible ; les formulations sont a `lea-dev`.

### 3. **Second motif de refus - l'item 9 porte sa reponse dans son enonce**

> `enonce` : *"Malo raconte a toute la classe **une moquerie** sur Lea qu'il a inventee. Qu'est-ce
> que Malo fait ?"*
> `bonneReponse` : *"une moquerie"*

**Le mot a trouver est ecrit dans la question.** L'enfant n'a rien a reconnaitre : il apparie deux
occurrences du meme mot. C'est la contrefacon transposee au texte - **en effacant la competence, la
reponse reste-t-elle trouvable ? Ici, oui, et sans meme lire la situation.**

**Et ce n'est pas seulement une facilite, c'est aussi une imprecision de fond** : ce que decrit
l'item - une histoire **inventee**, racontee **a toute la classe**, sur une camarade - est plus
proche d'une **rumeur** ou d'un **mensonge** que d'une moquerie, et le paquet le sait a moitie
puisqu'il place *"une information vraie"* en distracteur, c'est-a-dire un distracteur qui joue sur
l'axe du **vrai et du faux** et non sur celui du **permis et de l'interdit**. **La ligne 2 porte sur
le second axe.** L'item est a reecrire, pas a raccourcir.

### 4. Trois defauts de plus, qui n'auraient pas suffi a refuser mais que la reprise doit corriger

Je les nomme pour que `camille-po` en fasse du backlog en une passe, plutot qu'un aller-retour.

- **Les items 7 et 8 sont le meme item.** Memes trois propositions (*permis*, *interdit*,
  *dangereux*), meme bonne reponse (*permis*), meme structure d'enonce. **Je les ai vus tous les
  deux dans une seule partie de dix manches** : le second se repond sans etre lu. Et *"dangereux"*
  est une **troisieme option morte** - l'enonce demande *"est-ce permis ou interdit ?"*, il enumere
  donc lui-meme les deux seules reponses possibles. Ces deux items sont, a l'ecran, deux pieces
  jetees a pile ou face, dont la seconde est gratuite.
- **Un distracteur enonce une regle d'ecole fausse.** Item 10 : *"parce que c'est interdit de rire
  en classe"*. Il est **interdit de se moquer**, il n'est pas interdit de rire. Sur un paquet dont
  l'objet est de dire ce qui est permis et ce qui est interdit, poser un interdit inexistant en
  proposition est exactement le mecanisme qu'`EMC-4` decrit pour les stereotypes : **l'enfant lit la
  proposition meme quand il ne la choisit pas.**
- **Le role de victime est genre.** Sur les cinq situations d'atteinte, **la victime est une fille
  quatre fois sur cinq** (Aya une fois, Lea trois fois), et **Lea n'est jamais rien d'autre qu'une
  victime** dans ce paquet, sauf pour preter un crayon. Les agresseurs, eux, sont equilibres (Malo
  deux fois, Aya deux fois, Hugo une fois). **Le defaut n'est pas dans un item, il est dans la
  serie**, et il se corrige en echangeant des prenoms - cout nul.

**Aucun de ces trois-la ne serait un refus a lui seul.** Ils sont ici parce que la reprise est de
toute facon due.

### 5. Ce que je n'oppose pas, et pourquoi je le dis

**Le commentaire du paquet declare une chose fausse sur son propre contenu** : *"aucun item ne porte
sur une moquerie repetee"*, alors que l'item 1 dit *"Hugo **repete sans arret**"*. La declaration
sert a justifier qu'`EMC-3` ne s'applique pas au paquet. **Je verifie `EMC-3` sur le contenu, pas
sur la declaration, et sur le contenu elle est tenue** : aucun item ne met l'enfant en position de
victime, aucune bonne reponse n'est une solution appliquee seul, aucune violence n'est detaillee.
**Je ne refuse donc pas pour ce motif.** Je signale l'ecart parce qu'une declaration de conformite
fausse dans un fichier commite est de la meme famille qu'un chiffre sans methode : elle sera relue
comme une preuve.

### 6. Ce dont je reste la seule garde sur ce paquet

| Propriete | Qui la tient | Automatisable ? |
|---|---|---|
| **La longueur des enonces rapportee au niveau** | **moi seule** aujourd'hui. Un compte de mots par niveau serait mordable, et personne ne l'a demande | **oui, si l'equipe le veut** |
| **Qu'un enonce ne contienne pas sa propre reponse** | **moi seule.** Aucun test ne compare l'enonce a la bonne reponse | **oui, en principe** - c'est une comparaison de chaines |
| **Que deux items d'un paquet ne soient pas le meme item** | **moi seule.** Le garde-fou de rang ne lit que des valeurs numeriques et rend `null` sur des propositions textuelles | non en l'etat |
| **`EMC-4` etendue - qu'aucun distracteur n'enonce une regle fausse** | **moi seule** | non |
| **L'equilibre des roles entre personnages sur une serie d'items** | **moi seule** | non |

### 7. Ce que ce refus ne dit pas

- **Il ne renvoie pas la ligne au depart.** Onze items existent, exacts, sur la bonne competence,
  avec deux taches distinctes. **Ce qui est a refaire est la formulation, pas la matiere.** Je
  l'estime a une reprise de textes, pas a un lot neuf.
- **Il ne bloque pas le sprint suivant.** Il renvoie l'increment a `camille-po`, qui en fait du
  backlog.
- **Il ne dit rien du paquet jumeau**, que je valide sous reserve par ailleurs. Un paquet est une
  unite de verdict ; un lot n'en est pas une.

---

# Second audit - reaudit de la reprise (s29, 2026-09-06) - **VALIDE SOUS RESERVE**

- **Commit audite** : `4dbf9b2` (`[LEA] s29: reprise des trois paquets d'EMC refuses par
  l'instance B`), arbre `round-two` a `4dbf9b2`.
- **Verdict** : **VALIDE SOUS RESERVE**. **Done pedagogique prononce le 2026-09-06** par
  `nadia-prof`, apres avoir joue. **`EMC-CP-02` ferme** : `non couvert` -> **`couvert-valide`**.
  Aucune mention de couverture partielle n'est exigee sur cette ligne. Une reserve neuve et datee,
  `PER-R1`, non bloquante.
- **Le refus du 2026-09-05 n'est pas efface** : son bloc entier reste au-dessus, avec ses deux
  motifs et ses trois defauts nommes. **Il est leve, pas retire** - un refus efface ne prouve rien.
- **Audite par** : `nadia-prof`, **instance B** de la parallelisation.

## Conditions de l'audit

**J'ai joue.** Jar reconstruit sur `4dbf9b2` et relance, **partie complete de dix manches par
appels HTTP reels**, en me trompant volontairement sur chaque manche avant de repondre juste, pour
voir tous les indices. **Et j'ai lu les onze items** : celui que le tirage a laisse de cote est
l'item 2 (*"Pourquoi est-il interdit de se moquer d'un camarade ?"*), je l'ai lu.
**Ce n'est pas un audit degrade.**

## 1. Les deux motifs de refus, payes, et mesures

**Premier motif - la charge de lecture.** Ma barre du 2026-09-05, citee telle quelle : *"mediane des
enonces a 10 mots, aucun enonce au-dessus de 15"*.

**Mesure, faite par moi sur les onze enonces du fichier** : `7, 9, 9, 10, 10, 10, 11, 12, 12, 13,
14` - **mediane 10, maximum 14**. Le paquet etait a **mediane 15, maximum 20**. **La barre est
atteinte**, et le maximum est **sous** celui du paquet jumeau que je donnais comme cible (15). Les
onze enonces sont une phrase de situation suivie d'une question courte. L'incise *"sans meme la
toucher"*, que j'avais nommee comme du cout de lecture sans discrimination, a disparu.
**Motif leve.**

**Second motif - l'item 9 portait sa reponse.** Il est reecrit : *"Malo imite Lea devant la classe
pour rire d'elle. Qu'est-ce que Malo fait ?"* -> *"une moquerie"*. **Le mot a trouver ne figure plus
dans l'enonce.** Et la seconde moitie du motif est payee aussi, celle qui portait sur le fond : la
situation decrite est **bien une moquerie** - imiter quelqu'un pour faire rire a ses depens - et non
une rumeur ; le distracteur *"une information vraie"*, qui posait l'axe du vrai et du faux la ou la
ligne porte sur le permis et l'interdit, est remplace par *"une insulte"*, qui est sur l'axe de la
ligne. **Les deux moities sont payees. Motif leve.**

## 2. Les trois defauts supplementaires, payes aussi

- **Le doublon des items 7 et 8** : deux items desormais distincts - *"Lea prete son crayon a
  Malo"* -> *"un service"*, et *"Hugo explique calmement pourquoi il n'est pas d'accord"* -> *"une
  discussion"* - chacun oppose a **deux des trois qualifications de l'interdit** plutot qu'a un
  troisieme bouton *"dangereux"* qui n'etait jamais correct. **Je les ai vus tous les deux dans ma
  partie** (manches 4 et 5) : le second ne se repond plus par le premier. Paye.
- **Le distracteur qui enoncait une regle d'ecole fausse** : *"parce que c'est interdit de rire en
  classe"* -> *"parce que ca fait beaucoup de bruit"*. **Aucun interdit inexistant n'est plus pose
  en proposition** sur un paquet dont l'objet est de dire ce qui est interdit. Paye.
- **Le role de victime genre** : recompte par moi sur les cinq situations d'atteinte - la victime
  est **une fille deux fois sur cinq** (etait quatre fois sur cinq), l'agresseur **une fille deux
  fois sur cinq**, et **Lea n'est plus seulement une victime**. Paye, et pour le cout que j'avais
  annonce : deux prenoms echanges.

## 3. Un ecart que j'avais signale sans refuser, et qui se ferme

Le commentaire du paquet declarait *"aucun item ne porte sur une moquerie repetee"* alors que
l'item 1 disait *"Hugo repete sans arret"*. L'item 1 est desormais *"Hugo rit de la voix d'Aya"* :
**la declaration est devenue vraie.** Je le note parce que j'avais ecrit l'ecart, et qu'un ecart
signale se suit jusqu'a sa fermeture, meme quand il n'a jamais ete un motif.

## 4. Ce que j'ai verifie pour m'assurer que la reprise n'a rien casse

- **Exactitude** : les onze qualifications sont justes. Le raccourcissement n'en a abime aucune.
  **Le point que je regardais en priorite** : *"Malo dit des mots mechants a Hugo"* reste bien une
  insulte apres avoir perdu son incise, et *"une moquerie"* **n'est pas offerte sur cet item** (les
  distracteurs sont *un service* et *un cadeau*) - le raccourcissement n'a donc pas cree
  d'ambiguite la ou il enlevait la precision qui l'ecartait.
- **`FER-1`** : les trois termes de la parenthese sont chacun en bonne reponse - *une moquerie*
  (items 1, 9), *une insulte* (item 3), *de la violence* (items 5, 11). Tenue.
- **`FER-2`** : 11 items. **`FER-3`** : deux taches, sept items de qualification et quatre de
  raison d'etre. Tenues.
- **`EMC-1`, `EMC-5`, `EMC-8`, `EMC-10`, `EMC-3` (amendee)** : tenues, verifiees sur le contenu et
  non sur la declaration.
- **Chemin d'erreur** : l'enfant reste sur l'item, recoit un indice qui **decrit sans nommer**, et
  n'est jamais bloque. Verifie sur les dix manches, y compris sur les items a trois propositions ou
  je me suis trompee deux fois de suite.

## 5. **`PER-R1`** - reserve neuve, datee du 2026-09-06, non bloquante

**La question et la bonne reponse ne s'enchainent pas toujours en francais correct.** Sur les items
de qualification, la question est *"Qu'est-ce que X fait ?"* et la reponse se lit avec le verbe
*faire* : *"elle fait un service"* (on **rend** un service), *"il fait une discussion"* (on **a** une
discussion, ou on **discute**), *"elle fait de la violence"* (on **est** violent). L'item 8 est le
plus visible : *"Hugo explique calmement pourquoi il n'est pas d'accord"* -> *"une discussion"*.

**Ce n'est ni l'orthographe, ni l'accentuation, ni la ponctuation** - ce controle-la appartient au
`relecteur-pedagogique` (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`) et je ne m'en mele pas.
**C'est la formulation de ce que l'enfant lit et le niveau scolaire vise** : le CP est precisement
l'annee ou l'on construit ces tournures, et une question qui ne se recolle pas a sa reponse est un
modele bancal donne a lire.

> **Propriete a tenir** : sur un item de qualification, la question et la bonne reponse forment une
> phrase correcte lorsqu'on les lit ensemble. Une question du type *"Qu'est-ce que X fait ?"* n'est
> pas la seule forme possible ; *"Comment appelle-t-on ca ?"*, deja employee par l'item 1 de ce
> paquet, accepte les trois qualifications sans forcer le verbe. **Les formulations sont a
> `lea-dev`.**

**Pourquoi une reserve et non un refus** : aucune reponse n'en devient fausse, aucune ambiguite
n'est creee, et un enfant de CP accompagne comprend ce qui lui est demande - je l'ai verifie a
l'ecran. **La ligne ferme sans attendre cette correction.**

## 6. Ce dont je reste la seule garde sur ce paquet

| Propriete | Qui la tient | Automatisable ? |
|---|---|---|
| **La longueur des enonces rapportee au niveau** | **moi seule.** Rien n'a change de ce cote depuis le 2026-09-05 : le profil est bon aujourd'hui, aucun garde-fou ne l'empechera de deriver demain | **oui, si l'equipe le veut** |
| **Qu'un enonce ne contienne pas sa propre reponse** | **moi seule** | **oui, en principe** |
| **Que deux items d'un paquet ne soient pas le meme item** | **moi seule** | non en l'etat |
| **Qu'aucun distracteur n'enonce une regle fausse** (`EMC-4` etendue) | **moi seule** | non |
| **L'equilibre des roles entre personnages sur une serie** | **moi seule** | non |
| **`PER-R1`** - que question et reponse forment une phrase correcte | **moi seule** | non |

**Ces six proprietes sont exactement celles sur lesquelles la CI verte ne dit rien**, et les cinq
premieres sont celles qui ont produit mon refus du 2026-09-05 sur un paquet dont le Done technique
etait acquis.
