# Arbitrage prealable s24 - ma borne, `DEN`, le premier paquet de saisie, et les deux points de
# satisfaisabilite que `camille-po` a releves sans les trancher

- **Auteur** : nadia-prof
- **Date** : 2026-08-30
- **Sprint** : s24 (ouverture)
- **Saisi par** : `DEC-0059`, sections 6.1 et 6.2 ; `docs/backlog/backlog.md`, stories `S24-NAR`,
  `S24-FRA`, `S24-SAI-2`, `S24-DEN` ; avis de faisabilite de `theo-archi`, section 6.
- **Ce document ne prononce aucun Done pedagogique** : rien n'est livre. Il repond aux questions
  posees avant que le developpement ne commence, ce qui est exactement la discipline que `DEC-0057`
  a etablie apres avoir perdu un sprint a instruire des conditions une fois le code pret.

---

## 0. Ma methode, et les deux endroits ou elle est degradee

Je n'ai joue aucun increment : **il n'y en a aucun**. Ce document n'est pas un audit, c'est un
arbitrage prealable, et la lecture de documents y est le mode normal.

Ce que j'ai fait de plus qu'une lecture, parce que trois des cinq questions demandaient un chiffre
et que je refuse d'en avancer un sans methode :

- j'ai **lu le code** de `BanditManchotSession` (section 7.1) et de `QuestionContentSourceFactory`
  (section 7.3), plutot que de me fier a ce que j'avais enregistre ;
- j'ai **mesure** la classe aveugle du garde-fou d'accentuation paquet par paquet (section 1), sur
  le lexique de `ADR-0065` lui-meme ;
- j'ai **ecrit trois items reels** pour trancher le point de satisfaisabilite de `NAR` (section 5.1),
  au lieu de renvoyer la question au developpeur.

**Les deux endroits ou je reste aveugle, et je les nomme plutot que de les laisser trouver :**

1. **Je n'ai pas de tablette.** Tout ce que je dirai de la saisie sur tablette - le cout d'un accent,
   ce que corrige un clavier virtuel, `SAI-2b` - reste un raisonnement, comme le 2026-08-25. C'est
   pourquoi je choisis en section 4 un contenu **ou cette question ne mord pas**.
2. **Ma mesure de la section 1 ne reproduit pas les chiffres de `ADR-0065` et ne les corrige pas** :
   elle n'a pas le meme perimetre. Le detail est en 1.2. Je m'en sers pour **comparer des paquets
   entre eux**, jamais pour rectifier un chiffre de cet `ADR`.

---

## 1. Le fait de `ADR-0065` que `camille-po` porte contre son propre lot - je le corrige avant de m'en servir

C'est la question 3 de `DEC-0059`, section 6.1. **Elle est bien posee, et elle merite mieux qu'un
avis : elle est mesurable.**

### 1.1 Ce que la phrase dit, et ce qu'elle ne dit pas

`ADR-0065` ecrit : *"17 pour cent du vocabulaire reste juge a l'oeil, et le chiffre monte
mecaniquement avec la part de texte narratif dans le contenu."*

`camille-po` la reprend en *"la part de vocabulaire jugee a l'oeil monte mecaniquement avec la part
de texte narratif"*, et elle en deduit que le garde-fou rend **moins** sur `NAR`.

**La mesure dit que la part ne monte pas, et que ce qui monte est le compte.** Ce n'est pas une
querelle de mots : les deux lectures ne demandent pas la meme chose a mon audit.

### 1.2 Ma methode et mon perimetre, pour qu'un tiers refasse le calcul

- **Perimetre** : les 27 fichiers de `adapters/adapter-content/src/main/resources/contenu/`, lus
  **directement comme YAML** et non par le chargeur, champs `enonce`, `bonneReponse`,
  `distracteurs`, `indice`, `explication`, `texteSupport` de la liste `items`.
- **Etat de l'arbre** : `s24-nadia`, a la fusion de `s24-camille` et `s24-theo`.
- **Un mot** : toute suite maximale de lettres, mise en minuscules - la meme regle que `motsDe` du
  garde-fou.
- **La classe aveugle** : le mot est une forme du francais, **et** son squelette (lettres, accents
  retires) en est une aussi, **et** ce squelette porte au moins deux formes. Autrement dit : le
  lexique ne peut pas trancher l'accentuation. C'est la classe 1 de la section *"Ce que ce garde-fou
  ne verra pas"* de `ADR-0065`.
- **Le lexique** : `org.languagetool:french-pos-dict:0.7`, celui du POM parent, lu comme
  `LexiqueFrancais` le lit.
- **Ce que ma mesure ne voit pas, et qui explique qu'elle ne reproduise pas les chiffres de
  `ADR-0065`** : `cp-additions-jusqu-a-10.yaml` n'a pas de liste `items` - il est de type
  `additions-bornees` et ses manches sont **engendrees** depuis `parametres`. Ma lecture en tire zero
  chaine ; le chargeur, lui, l'enumere. Et je n'ai pas les six champs de catalogue que `ADR-0065`
  inclut. **Mes chiffres absolus ne sont donc pas les siens et ne les contredisent pas.** Ce que je
  compare, ce sont des paquets entre eux, avec une seule et meme regle appliquee a tous.

### 1.3 Le resultat

Sur ce perimetre : **1 637 mots distincts, 351 dans la classe aveugle, soit 21,4 pour cent.**

| Paquet | Mots distincts | Classe aveugle | Part |
|---|---|---|---|
| `cm1-comprehension` (le seul paquet narratif du depot, avec `texteSupport`) | 466 | **115** | **24,7 %** |
| `cp-mot-manquant` (le paquet CP le plus fourni en texte) | 179 | 56 | 31,3 % |
| `cm1-tables-multiplication` | 28 | **13** | **46,4 %** |
| `cp-ranger-nombres` | 27 | 11 | 40,7 % |
| `cp-soustractions` | 19 | **3** | 15,8 % |
| `cm1-mesures` | 15 | 2 | 13,3 % |
| **Ensemble** | **1 637** | **351** | **21,4 %** |

**La part ne monte pas avec le narratif : elle est plus haute sur les paquets de calcul.** Le motif
se lit en listant la classe. Sur `cm1-tables-multiplication`, les treize mots aveugles sont
`additionne ajoute change de double du la le moitie ne nombre table a` - **des mots-outils**, les
memes dans tout le depot. Un paquet de 28 mots distincts en est fait presque entierement, donc sa
part explose sans qu'aucun risque n'augmente : **je lis ces mots une fois pour tout le corpus.**

**Ce qui monte avec le narratif, ce sont deux choses, et ce sont les bonnes :**

1. **Le compte absolu** : 115 mots a juger a l'oeil sur `cm1-comprehension`, contre 13 sur
   `cm1-tables-multiplication` et 3 sur `cp-soustractions`. **Un facteur de neuf a trente-huit.**
2. **La composition, et c'est elle qui coute.** Sur `cm1-comprehension`, la classe aveugle est faite
   de verbes conjugues et de participes - `arrose`, `coince`, `fermes`, `corriges`, `gouter`, `pres`,
   `cote`, `ou`, `perche`, `pose`, `refermes`, `sucres`, `tombes`, `verglace`. Chacun est neuf,
   chacun demande **la phrase** pour etre tranche. Un mot-outil, je le connais deja ; un participe,
   je dois le lire dans sa phrase.

### 1.4 Ce que cela change - a mon audit, oui ; a ma borne, non ; a l'echange `NAR` -> `SUB`, l'inverse de ce qu'on attend

**A mon audit de `NAR`** : je porterai a la main plusieurs dizaines de formes verbales, chacune dans
sa phrase. L'ordre de grandeur attendu se lit sur le paquet CP le plus proche en volume de texte,
`cp-mot-manquant` : **56 mots**. `NAR` est plus contraint encore - `NAR-2` plafonne a 18 mots de
`texteSupport` par item, la ou `cm1-comprehension` n'a aucun plafond. C'est un cout reel, il est
borne, et je le connais avant d'entrer dans le sprint. **Il ne justifie pas de changer quoi que ce
soit.**

**A ma borne** : rien. Le garde-fou de `ADR-0065` ne m'a jamais ete promis comme couvrant le
narratif ; sa propre section le disait avant que je ne compte dessus. C'est precisement pour cela que
je demandais a la lire.

**A l'echange `NAR` -> `SUB` que `camille-po` me propose comme neutre : je ne le prends pas, et le
fait mesure joue dans l'autre sens.** `SUB-2` autorise **45 mots** de `texteSupport` et trois phrases
par item, contre 18 mots et deux phrases pour `NAR-2`. A vingt items, `SUB` porte donc environ deux
fois et demie le volume de texte narratif de `NAR`. **Sur l'axe de l'accentuation, `SUB` est le
paquet cher et `NAR` le paquet borne** - l'inverse de ce que la question suppose. Le motif de
couverture de `camille-po` (le francais CP a 27,3 pour cent) tient, et rien dans ma mesure ne le
contredit.

*Precision que je dois, pour ne pas avoir l'air de me dedire : j'ai ecrit a s23 que `SUB` etait
"le paquet du lot dont l'audit me coutera le plus cher apres `NAR`". Cela portait sur `SUB-4` et
`SUB-5`, les accords que je porte item par item. Ce classement-la est inchange. La mesure d'ici
porte sur un autre axe et n'en fait pas partie.*

---

## 2. Ma borne de capacite - question 1 de `DEC-0059`, section 6.1

### 2.1 Les trois conditions, verifiees par moi sur le depot

| Condition du 2026-08-30 | Verdict | Ce que j'ai verifie moi-meme |
|---|---|---|
| 1 - l'accentuation est gardee par un automate | **remplie** | J'ai lu `AucunMotServiNeSEcarteDeSonAccentTest`. Le garde-fou juge sur un lexique exterieur, et sa morsure est prouvee sur **le materiau reel du defaut de s22** (`leDefautReelDeS22FaitEchouerLeGardeFou`, paquet du contre-exemple pris a `be63ce3^`), pas sur une fixture. Le trou est **grave dans un test** (`laFauteDAccentSurUnMotQuiExisteSansAccentResteInvisible`), qui echouera le jour ou quelqu'un croira l'avoir ferme. C'est la meilleure forme que je pouvais demander |
| 2 - chaque paquet arrive avec ses comptes declares et leur methode | **remplie** | Pratique acquise depuis s22, verifiee exacte par moi sur les trois lots de s22 et reconduite a s23 |
| 3 - au plus un paquet du type `COMP-2` par sprint | **remplie par la selection de `camille-po`** | `NAR` est le seul du lot. `SUB` est dehors, et sa verification du fait est exacte : je l'ai ecrite en section 3 de mon arbitrage de s23, elle la cite correctement |

**La reserve que je posais avant de compter sur le garde-fou est levee.** `ADR-0065` porte la section
que je demandais, elle nomme quatre trous et elle en chiffre trois. C'est ce que je voulais savoir.

### 2.2 Je reconfirme quatre a cinq paquets, et je nomme ce que ce chiffre n'a jamais price

**Je reconfirme : quatre a cinq paquets de contenu par sprint, sur une mecanique existante.**

**Et j'ecris tout de suite ce que ce chiffre ne couvre pas, parce que s24 est le premier sprint ou
la difference existe.** Je l'ai avance a s22 dans un depot ou une seule mecanique existait et ou
aucune n'etait en construction. Il compte des **paquets**. Il n'a jamais price **l'audit d'une
mecanique neuve**, qui n'est pas de la meme nature :

- un paquet, j'en audite le **contenu** - des enonces, des distracteurs, un niveau ;
- une mecanique, j'en audite le **comportement** - `SEUIL-1` a `SEUIL-5`, ce que voit l'enfant apres
  un echec, apres trois, ce qu'il peut corriger, ce qui lui coute un essai. Cela se joue, plusieurs
  fois, en se trompant expres, et rien de tout cela n'est un comptage.

**Ce n'est pas un durcissement de ma borne** - la borne est confirmee telle qu'elle a ete ecrite.
C'est un **cout qu'elle ne nommait pas**, et je le nomme maintenant, date du 2026-08-30, plutot que
de le decouvrir en fin de sprint.

### 2.3 Ce que je prends a s24

| Objet | Nature | Compte |
|---|---|---|
| `NAR` | paquet de contenu, type `COMP-2` | 1 |
| `PLU` | paquet de contenu | 1 |
| `FRA` | paquet de contenu | 1 |
| `S24-SAI-2` - le premier paquet de saisie | paquet de contenu | 1 |
| `S24-SAI-1` - la mecanique de saisie | **audit de mecanique, hors compte de paquets** | - |

**Quatre paquets, plus un audit de mecanique neuve. Je suis a ma borne, pas en dessous.**

---

## 3. `DEN` - question 2 de `DEC-0059`, section 6.1

### 3.1 Ce que je reponds

**`DEN` vaut un audit. Il ne rentre pas dans ma capacite de s24.**

Ce sont deux phrases distinctes et je refuse de les confondre, parce que `camille-po` demande *"vaut-il
un audit a ce prix"* et que repondre "non" laisserait croire que je le juge sans valeur.

### 3.2 Pourquoi il vaut un audit, et je le dis contre le compteur de couverture

`DEN-2` est de moi, je la maintiens, et elle est non negociable : la ligne reste `en cours` parce que
le verbe *denombrer*, le premier de la ligne, n'est travaille par aucun jeu.

**Mais l'absence de mouvement de matrice ne mesure pas ce que ce paquet apporte.** Aujourd'hui, un
enfant de CP dispose de six jeux de mathematiques dans ce catalogue : comparer et ranger, additions,
soustractions, complements a 10, petits problemes, comparer des grandeurs. **Aucun ne travaille la
numeration** - lire, ecrire, decomposer un nombre jusqu'a cent. C'est le trou le plus visible du
CP mathematique, et c'est ce sur quoi tout le reste s'appuie : un enfant qui ne sait pas que 72
s'ecrit *soixante-douze* et vaut sept dizaines et deux unites n'ira pas loin en calcul.

Un compteur qui affiche zero sur ce paquet decrit une convention de matrice, pas ce que l'enfant
gagne. **Je le dis ici pour que le zero de `DEN-2` ne devienne pas un motif de report permanent.**

### 3.3 Pourquoi il n'entre pas a s24

Par la section 2.3 : quatre paquets et un audit de mecanique neuve, c'est ma borne. `DEN` serait le
cinquieme paquet **en plus** de l'audit de mecanique. Je ne le prends pas.

**Et je nomme la condition qui le ferait entrer, pour que ce ne soit pas a rediscuter** : si le lot
de saisie glisse en cours de sprint - `theo-archi` nomme deux points de rupture, la proposition de
rendu d'`ines-design` et le lot 5 des treize garde-fous - et que `S24-SAI-2` ne sort pas, alors
**`DEN` prend sa place sans nouvel arbitrage de ma part**. Il est arbitre depuis le 2026-08-24, ses
huit conditions sont ecrites, il n'est pas du type `COMP-2`, et son audit est de ceux que je sais
faire vite : `DEN-6`, `DEN-7` et `DEN-8` se calculent, `DEN-1` se lit d'un balayage des vingt
enonces, `DEN-4` est un comptage.

**Le perimetre reste a `camille-po`.** Je dis ce que ma porte peut porter ; je ne dis pas ce que le
sprint contient.

---

## 4. Le premier paquet de saisie - question de `DEC-0059`, section 6.2

### 4.1 Sur quel contenu il porte

**Les complements a 10, au CP, en saisie numerique.** Entree de catalogue neuve, paquet neuf de
saisie ecrit sur la meme matiere que `cp-complements-10`.

**Les cinq motifs, dans l'ordre ou ils comptent :**

1. **C'est le cas exact que j'ai nomme le 2026-08-24**, et je ne l'invente pas apres coup :
   *"sur les complements a 10, sur les additions, un enfant qui choisit parmi quatre tombe juste une
   fois sur quatre sans rien savoir. Une saisie de un a deux chiffres retire ce bruit et rend le
   score honnete."*
2. **La reponse est un entier de 0 a 10** : un ou deux chiffres, la forme meme que `SAI-11`
   recommande en premier. Aucun accent, aucune majuscule, aucune espace interne. **La question de
   l'accent sur tablette, ouverte depuis le 2026-08-24, ne mord donc pas sur ce paquet** - ce que
   `camille-po` avait vu et ecrit, et je le confirme.
3. **La liste des formes acceptees est enumerable en entier, et c'est le motif decisif.** `SEUIL-5`
   dit qu'une liste ecrite a l'estime est un bareme secret qu'aucune CI ne verra. Sur onze reponses
   possibles, la liste complete du paquet tient en vingt-deux formes et **je peux la relire
   integralement**, ce qu'aucun paquet alphabetique ne permettrait. Pour un premier paquet, le point
   le plus dangereux de la mecanique doit etre celui que je peux fermer entierement.
4. **Le contenu est deja valide, donc l'audit portera sur la mecanique.** C'est ce que je veux d'un
   premier paquet : si quelque chose ne va pas, je saurai que c'est la mecanique et pas les enonces.
5. **Le gain est mesurable, et il est plus grand que ce que j'avais ecrit.** Voir 7.1.

**Ce que j'ecarte, et pourquoi, pour que personne ne le repropose** :

- **`cp-additions-jusqu-a-10`** : le paquet est **engendre** (`type: additions-bornees`,
  `parametres`), pas enumere. En faire une version de saisie pose une question de mecanique de
  generation que je ne sais pas juger et qui n'a rien de pedagogique. Un premier paquet ne doit pas
  trainer une question technique derriere lui.
- **Un paquet alphabetique**, quel qu'il soit : `SAI-11` l'autorise depuis le 2026-08-25 et je ne
  reviens pas dessus, mais il ferait mordre sur le premier increment une question a laquelle
  personne n'a repondu - ce que coute un accent sur la tablette visee. Ce n'est pas un refus, c'est
  un ordre : l'alphabetique apres, quand la mecanique aura ete jouee une fois.
- **Une ligne `non couvert`**, y compris la moitie *ecrire* de `DEN` qui serait pourtant de la saisie
  numerique courte : elle ne ferait bouger aucune ligne non plus (`DEN-2`), et elle melangerait
  l'audit d'un contenu neuf a celui d'une mecanique neuve. **Un seul objet neuf a la fois.**

### 4.2 Un Done pedagogique complet ? Oui - la lecture de `camille-po` est juste, et je la precise

**Oui, Done pedagogique complet et `FICHE-<jeu>.md` neuve.** Trois raisons, et une precision qui
compte davantage que les trois.

1. **Une entree de catalogue neuve est un jeu qu'un enfant peut ouvrir.** Un jeu jouable non audite
   est un jeu non garde ; le statut de la ligne n'y change rien.
2. **L'essentiel de cet audit n'est pas le contenu, c'est la mecanique.** `SEUIL-1` a `SEUIL-5`
   n'ont jamais ete joues. C'est le premier et le seul moment ou ils le seront.
3. **La ligne de matrice ne bouge pas**, et la fiche doit le dire en toutes lettres, sinon deux
   entrees sur une meme ligne se liront un jour comme une double couverture.

**La precision, et elle est la vraie reponse** : **un REFUSE sur cette fiche ne touche pas le Done de
`bandit-manchot-complements-10-cp`.** Ce sont deux increments, deux fiches, deux verdicts. Je l'ecris
maintenant parce que la confusion inverse serait desastreuse : personne ne doit hesiter a me livrer
un jeu de saisie de peur de faire tomber une ligne verte.

### 4.3 Les conditions qui s'ajoutent a `SAI-1` a `SAI-12` et `SEUIL-1` a `SEUIL-5`

`camille-po` a raison sur le fond de sa question : ces conditions decrivent une **mecanique**, pas un
**paquet**. Voici celles du paquet. Elles sont neuves, datees du 2026-08-30, et ne s'appliquent qu'a
lui.

- **`CPL-1` - Cible.** Le paquet porte les complements a 10 et rien d'autre. La ligne mathematiques
  CP *"Complements a 10"* est deja `couvert-valide` et **ne bouge pas**. La fiche le porte ecrit.
- **`CPL-2` - Forme de la reponse.** Toute bonne reponse est un entier de 0 a 10 : au plus deux
  chiffres. Aucun item ne demande autre chose.
- **`CPL-3` - Les formes acceptees, enumerees et relues par moi** (`SAI-6`, `SAI-7`, `SEUIL-5`). Pour
  chaque item, et pour ce paquet seulement, sont acceptees : **l'ecriture en chiffres** du nombre,
  zero initial tolere (`07` vaut `7`), espaces de tete et de fin ignorees ; **et l'ecriture en
  lettres du meme nombre** - `zéro`, `un`, `deux`, `trois`, `quatre`, `cinq`, `six`, `sept`, `huit`,
  `neuf`, `dix` - accents et majuscules tolerees par `SAI-1` et `SAI-3`. *Motif de la seconde
  moitie : un enfant qui repond "sept" a repondu juste. `SEUIL-2` interdit qu'une reponse juste
  refusee sur une question de forme lui coute un essai, et c'est exactement ce qui arriverait.*
  **Aucune autre forme n'est acceptee** : ni operation ecrite, ni phrase.
- **`CPL-4` - Aucun mode strict.** Le paquet ne declare la stricte egalite sur aucun item
  (`SAI-2a`). La competence ne porte pas sur l'orthographe, et `SAI-2b` l'interdirait de toute facon.
- **`CPL-5` - Quatre indices sont a reecrire, et je les nomme.** Les indices de `cp-complements-10`
  ne peuvent pas etre repris tels quels : quatre d'entre eux **nomment la reponse**, ce qui, avec des
  boutons, etait un raccourci parmi trois choix affiches, et devient avec un champ de saisie **la
  reponse donnee**. Voir 7.3 pour le detail et pour ce que cela ne remet pas en cause. Les quatre :
  `0 + ? = 10` (*"il manque tes 10 doigts"*), `5 + ? = 10` (*"il en reste 5"*), `9 + ? = 10`
  (*"compte celui qui manque"*, le singulier dit un), `10 + ? = 10` (*"il n'en manque plus aucun"*,
  qui dit zero). Les sept autres conduisent a la reponse sans la nommer et conviennent.
- **`CPL-6` - L'enfant voit ou va ce qu'il ecrit.** Il doit comprendre que sa saisie prend la place
  du `?` de `3 + ? = 10`. **Je fixe le resultat, jamais le chemin** : la forme appartient a
  `ines-design` et `theo-archi`.
- **`CPL-7` - Au CP, entrer un chiffre ne doit pas obliger l'enfant a chercher parmi des lettres.**
  Un enfant de six ans qui balaie un clavier alphabetique pour trouver le `7` perd en route ce qu'il
  avait calcule - c'est le motif de charge cognitive que j'avais ecrit le 2026-08-24, et il vaut
  encore pour les chiffres meme apres la levee de `SAI-11`. **Resultat fixe, moyen libre.**
- **`CPL-8` - Le retour de reussite ne felicite pas l'appareil.** `SAI-8` demande de montrer la forme
  de reference quand une tolerance a joue. Sur ce paquet, la tolerance la plus frequente sera
  l'ecriture en lettres : *"Oui ! 7, c'est bien ça."* convient. Aucune formulation ne reproche a
  l'enfant d'avoir ecrit en lettres.

### 4.4 Reponse aux trois questions que `theo-archi` me route sur `SAI-12`

Il a etabli lui-meme le point qui n'etait pas de moi - represser un bouton deja marque consomme un
essai, donc une chaine retapee qui consomme un essai est **symetrique** de l'existant. Je le
confirme, je l'ai relu dans `jouer`. Restent ses trois questions :

1. **Une tentative ou trois a l'ecran ?** **Toutes celles de la manche**, cumulativement : une au
   deuxieme essai, deux au troisieme. C'est l'equivalent exact de `M9`, ou l'enfant voit **toutes**
   les propositions deja pressees et pas seulement la derniere.
2. **Une repetition vue ou non vue ?** **Vue, et deux fois dans la liste.** Si l'enfant a ecrit deux
   fois la meme chose, il doit le voir : c'est precisement le defaut que `SAI-12` existe pour
   rendre visible. Deduplier reviendrait a lui cacher que sa correction n'a rien corrige.
3. **La comparaison avant ou apres normalisation ?** **La question tombe avec la reponse 2** :
   puisque rien n'est deduplique, rien n'est compare pour cet affichage. La liste montre les chaines
   **brutes, telles que l'enfant les a tapees** - `SAI-9a` dit qu'il garde sous les yeux ce que
   **lui** a produit. Un enfant qui ecrit `sept` puis `Sept` doit voir les deux : c'est ainsi qu'il
   apprend que la majuscule n'etait pas le probleme.

---

## 5. Les deux points de satisfaisabilite - je tranche, et le developpeur n'a pas de detour a faire

`camille-po` les a poses correctement et a eu raison de ne pas les trancher. Ils sont a moi.

### 5.1 `NAR-2` contre `NAR-5`, et la question en *"Ou"*

**Verdict : les deux conditions sont satisfaisables ensemble, y compris sur la forme *"Ou"*.** Aucune
n'est amendee. J'ai fait l'etape 2 de ma propre procedure - enumerer et compter - au lieu de la
laisser au developpeur.

**Deux precisions d'unite de compte d'abord, parce que sans elles la mesure n'est pas la meme d'une
main a l'autre. Elles vont toutes les deux dans le sens qui desserre, jamais qui resserre :**

- **`NAR-2`, unite de compte, precisee le 2026-08-30** : *un mot est une suite de caracteres sans
  espace*, **la ponctuation isolee non comptee**. Un `?` separe par une espace n'est pas un mot.
  *Motif : `D-8` m'avait deja montre a s22 qu'un compte declare sans sa methode se recompte
  autrement, et je ne veux pas d'un refus qui tiendrait a un point d'interrogation.*
- **`NAR-3`, unite de compte, precisee le 2026-08-30** : les neuf lettres se comptent sur le mot
  graphique delimite par les espaces, **les apostrophes et les traits d'union**. `range-t-elle` se
  lit `range`, `t`, `elle`, et non comme un mot de douze lettres. *Motif : c'est ainsi qu'un enfant
  de CP le decode, et une question correcte en francais ecrit demande souvent l'inversion.*

**Les trois items que j'ai ecrits pour le prouver, un par forme de question.** Ce ne sont pas les
items du paquet : c'est la preuve que les vingt sont ecrivables. Le developpeur les reprend ou les
jette.

| | *Qui* | *Ou* - le cas le plus serre | *Que* |
|---|---|---|---|
| `texteSupport` | Malo, Sara et Tom jouent dans la cour. Tom ramasse une feuille et la garde. | Nina range le ballon sous l'escalier. Puis elle va au salon et s'assoit près de la fenêtre. | Nour ouvre une boîte et sort un vieux train. Puis elle range un ballon et une corde. |
| Mots / phrases | 15 / 2 | **17 / 2** | 17 / 2 |
| `enonce` | Qui ramasse une feuille ? | Où Nina range-t-elle le ballon ? | Que sort Nour de la boîte ? |
| Total `NAR-2` | 19 / 25 | **22 / 25** | 23 / 25 |
| Propositions | Tom / Malo / Sara | sous l'escalier / au salon / près de la fenêtre | un vieux train / un ballon / une corde |
| `indice` | Relis la deuxième phrase. | Relis la première phrase. | Regarde la fin de la première phrase. |

**Verification condition par condition sur la colonne *"Ou"***, celle que `camille-po` designait :
`NAR-2` 17 mots et 2 phrases, total 22 - **il reste de la marge** ; `NAR-3` mot le plus long
`escalier`, 8 lettres ; `NAR-4` commence par *"Ou"*, reponse litterale dans le texte ; `NAR-5` les
trois propositions figurent en toutes lettres dans ce texte ; `NAR-6` texte masque, aucun lieu n'est
le lieu evident d'un ballon, aucun distracteur ne s'elimine seul ; `NAR-8` aucune configuration
familiale, aucun logement designe comme celui de quelqu'un, aucun animal, aucun depart, **et
`le ballon` et non `son ballon`** - c'est l'objet possede que `NAR-8` interdit ; `NAR-9` quatre mots.

**Ce qui rend la forme *"Ou"* tenable et que la question n'avait pas vu** : les trois lieux n'ont pas
besoin d'etre trois lieux distincts d'une meme action. Deux suffisent dans la seconde phrase comme
etapes d'un deplacement, le troisieme etant celui de la premiere phrase. **Un deplacement, pas une
enumeration** - c'est ce qui fait tenir trois lieux en dix-sept mots.

### 5.2 `FRA-2` forme (d) contre `FRA-5` - la lecture est arretee

**La lecture que `camille-po` juge vraisemblable est la bonne, et je l'arrete formellement.**

- **`FRA-5`, famille (a), sur la forme (d) - lecture arretee le 2026-08-30** : *le numerateur et le
  denominateur echanges* produit, sur un item dont la bonne reponse est un nombre, **le numerateur de
  la fraction enoncee**. Sur *"Dans 3/8, en combien de parts égales le tout est-il partagé ?"*, la
  bonne reponse est `8` et le distracteur de famille (a) est `3`.

**Ce n'est pas une lecture de commodite : c'est la seule qui garde a la famille (a) le meme sens
qu'ailleurs.** Sur les formes (a) et (b), la famille (a) attrape l'enfant qui a **interverti les deux
termes**. Sur la forme (d), l'enfant qui intervertit les deux termes repond `3`. C'est exactement la
meme erreur, et c'est **la** erreur que la forme (d) existe pour trouver - confondre ce que dit le
nombre du haut et ce que dit le nombre du bas est la premiere difficulte de la notion de fraction. Un
distracteur qui ne serait pas celui-la manquerait la cible.

**Une clause neuve, parce que les deux familles peuvent produire la meme valeur et que `FRA-6` exige
trois propositions distinctes :**

- **`FRA-10` (neuve, 2026-08-30)** - Sur la forme (d), quand la famille (b) - *le denominateur decale
  de un* - produit la meme valeur que la famille (a), **c'est l'autre decalage qui est retenu**.
  Exemple : sur `3/4`, la bonne reponse est `4`, la famille (a) donne `3`, et la famille (b) doit
  donc donner `5` et non `3`. *Un decalage au moins est toujours disponible : le numerateur ne peut
  pas valoir a la fois `d-1` et `d+1`. Et `FRA-1` ne s'applique pas aux distracteurs - elle le dit -
  donc un `11` en distracteur est licite.*

**Une precision sur `FRA-5` que la forme (d) rend necessaire** : la clause dit qu'un tiers doit
pouvoir *"recalculer les deux distracteurs a partir de la bonne reponse et de la famille declaree"*.
Sur la forme (d), la bonne reponse seule ne suffit pas - `8` ne donne pas `3`. **Sur cette forme, le
recalcul se fait a partir de la fraction enoncee**, qui est dans l'item. Ce n'est pas un
assouplissement : la verification par un tiers reste entiere.

**La famille (c) reste exclue de la forme (d)**, comme `FRA-5` l'ecrit deja : une denomination formee
sur le numerateur n'a pas de sens quand la reponse attendue est un nombre. Deux familles suffisent,
un item n'ayant besoin que de deux distracteurs.

---

## 6. Le zero de couverture de la saisie - je le confirme, et j'instruis cinq lignes de plus

**Je confirme : la mecanique de saisie, telle que je l'ai arbitree, n'ouvre aucune ligne de la
matrice. Le chiffre est zero.**

J'ai refait la lecture de `camille-po` sur ses 18 lignes `non couvert` : elle est exacte, ligne par
ligne, et elle cite mes refus correctement. **Elle n'a pas invente un motif ni etendu un refus.**

**Et j'instruis les cinq lignes que son perimetre ne couvrait pas**, parce qu'un "zero" instruit sur
une partie du tableau seulement n'est pas un zero. Comptes faits sur les quatre tableaux de
`squad/programme/MATRICE-couverture.md` a l'etat de `s24-camille` : 47 lignes, dont 24
`couvert-valide`, 4 `en cours`, 15 `non couvert`, 4 `non couvrable en l'etat`. Les 18 de
`camille-po` sont les 15 `non couvert` plus les 3 qu'elle vient de passer `en cours`. Restent cinq
lignes qu'elle n'a pas instruites :

| Ligne | La saisie l'ouvre-t-elle ? | Motif |
|---|---|---|
| *Lire a voix haute un texte court avec fluidite* (fr. CP), `non couvrable` | **non** | Production orale. Un clavier ne fait pas parler un enfant |
| *Relire et corriger son texte* (fr. CM1), `non couvrable` | **non** | `R4`, deja ecrit, et reconduit ici sans changement |
| *Reconnaitre, nommer et tracer des figures planes avec outils* (maths CM1), `non couvrable` | **non** | Le trace a la regle et au compas est la competence elle-meme |
| *Reconnaitre et utiliser la symetrie axiale* (maths CM1), `non couvrable` | **non** | Famille visuelle |
| *Associer les graphemes aux phonemes* (fr. CP), `en cours` depuis s03 | **non** | Ce qui manque a cette ligne, ce sont les voyelles et consonnes simples et les digraphes que `cp-le-bon-son` ne porte pas encore. C'est un **trou de contenu sur la mecanique de choix**, pas un besoin de saisie. Ecrire le grapheme d'un son demande de **l'entendre** : famille audio, et sinon c'est de la copie, donc `R1` |

**Zero sur 23 lignes instruites, soit toutes celles que la saisie pourrait faire bouger.** Le chiffre
de 28 a 33 lignes sur 43 n'a aucun fondement dans mes arbitrages, et rien de ce que j'instruis
aujourd'hui ne le rattrape - y compris la moitie *ecrire* de `DEN`, qui est bien de la saisie
numerique courte mais que `DEN-2` empeche de faire bouger sa ligne.

**Ce que je demande a `sofia-sm`** : que ce zero soit ecrit dans le rapport de sprint avec la valeur
que la saisie apporte reellement, qui est en section 7.1. Un lot juge sur un compteur qui ne le
mesure pas sera reporte au premier sprint charge.

---

## 7. Trois faits que je verse, et deux d'entre eux corrigent ce que j'avais moi-meme enregistre

### 7.1 Le score de ce produit est parfait par elimination sur 94,6 pour cent des items - correction et elargissement de ma reserve 5

**Ce que j'avais ecrit le 2026-08-24**, en corrigeant deja une premiere inexactitude : *"le score
d'un paquet a **deux** propositions n'est pas seulement peu informatif, il est toujours parfait"*.

**C'est exact et c'est trop etroit. La meme demonstration vaut a trois propositions, et je ne
l'avais pas vue.** Relu dans `BanditManchotSession` plutot que de memoire :
`ESSAIS_AVANT_REVELATION` vaut **3** ; `jouer` teste la bonne reponse **avant** de compter l'essai ;
`resoudre(true)` incremente `reussites` **quel que soit le nombre d'essais deja depenses** ; et `M9`
laisse les propositions deja pressees **marquees et visibles**. Donc sur un item a trois
propositions, un enfant qui presse au hasard sans jamais represser une proposition barree se trompe
au plus deux fois et **a necessairement raison au troisieme essai, qui compte comme une reussite**.

**Mesure, meme perimetre et meme methode qu'en 1.2** : sur les 615 items enumeres des 27 paquets,
**534 portent trois propositions et 48 en portent deux, soit 582 - 94,6 pour cent.** Seuls **33
items** en portent quatre (`cm1-conjugaison-present` 25, `cm1-accords-groupe-nominal` 8) et laissent
au hasard une chance reelle d'echouer. *`cp-additions-jusqu-a-10` est engendre et n'entre pas dans ce
compte ; il declare `nombreDePropositions: 3`, donc il est dans le meme cas.*

**Ce que j'en fais, et ce que je n'en fais pas.**

- **Je ne refuse rien et je ne retire aucun Done.** Tous ces paquets ont ete valides sur leur
  contenu, qui est juste. Me servir de ce fait contre eux serait deplacer mes poteaux, et je l'ai
  deja refuse une fois le 2026-08-24 pour la meme raison.
- **J'elargis la reserve 5, observation neuve datee du 2026-08-30** : le score d'une partie ne
  mesure pas ce que l'enfant sait, sur presque tout le catalogue. Ce n'est pas un defaut de contenu,
  c'est une propriete de `M5` face au nombre de propositions.
- **C'est la valeur de la mecanique de saisie, et elle se chiffre.** Sur les complements a 10, un
  enfant qui ne sait rien obtient aujourd'hui 7 manches sur 7. En saisie, avec trois essais sur onze
  reponses possibles, il en obtient au plus trois sur onze par manche. **La saisie est la premiere
  mecanique de ce produit ou un enfant qui ne sait pas peut echouer** - et c'est ce qu'un compteur de
  couverture ne verra jamais.
- **`camille-po` a un arbitrage a rendre et il n'est toujours pas de mon ressort** : soit les paquets
  a deux et trois propositions assument un score decoratif, soit `M5` cesse de donner trois essais
  quand le nombre de propositions les rend suffisants, ce qui est une question de mecanique pour
  `theo-archi`. **Je ne tranche ni l'un ni l'autre. Je fournis le fait, corrige et elargi.**

### 7.2 Ce depot possede desormais un lexique morphologique - un fait que j'ai ecrit et qui n'est plus vrai

J'ai ecrit dans mon arbitrage de s23, sur `SUB-4` et `SUB-5` : *"il faudrait un lexique
morphologique, que ce depot n'a pas"*. **C'etait vrai ce jour-la. Ce ne l'est plus depuis
`ADR-0065`, accepte le meme jour**, et personne ne l'a rapproche.

Verifie en interrogeant le lexique : `french-pos-dict:0.7` porte le lemme, la categorie, **le genre
et le nombre** - `chattes` rend `[chat|N f p]`, `filles` rend `[fille|N f p]`, `elle` rend
`[elle|R pers suj 3 f s]`.

**Ce que j'en tire, et ce que je ne demande pas** :

- **`SUB-4` et `SUB-5`** (le genre et le nombre ne decident jamais seuls ; la proximite ne decide
  pas toujours) cessent d'etre hors de portee d'un automate. Je les porte toujours a la main
  aujourd'hui, et je les porterai tant que le test n'existe pas.
- **`PLU-1`** (la phrase ne porte aucun autre indice de nombre pour le groupe) est de la meme
  famille : reperer dans la phrase un mot porteur d'une marque de pluriel est desormais mecanisable.
- **Je ne demande pas que ces tests soient ecrits dans ce lot**, exactement comme pour `NAR-5` :
  **je dis qu'ils sont possibles**, et ces proprietes sortiront de ma main le jour ou ils existeront.
  C'est a `theo-archi` et `yanis-qa`, et c'est du backlog.

### 7.3 Quatre indices de `cp-complements-10` nomment la reponse

Verifie dans le fichier et dans `QuestionContentSourceFactory` : la cle `indicesNommentLaReponse` de
`M21` est **facultative et opt-in**, et seul `cm1-conjugaison-present.yaml` la declare.
`cp-complements-10.yaml` ne la declare pas : ses indices s'affichent des le premier echec.

Quatre d'entre eux nomment la reponse : `0 + ? = 10` (*"il manque tes 10 doigts"*), `5 + ? = 10`
(*"il en reste 5"*), `9 + ? = 10` (*"compte celui qui manque"* - le singulier dit un), `10 + ? = 10`
(*"il n'en manque plus aucun"* - qui dit zero). Les sept autres disent *"compte ceux qui manquent"*
et conduisent sans nommer.

**Je ne rouvre pas le Done de `bandit-manchot-complements-10-cp`.** Avec trois boutons a l'ecran dont
la bonne reponse, la difference entre nommer et conduire est mince, et c'est sur ce jeu-la que j'ai
prononce. **Sur un champ de saisie, elle est totale** : l'indice donne litteralement la reponse. Ce
n'est donc pas un durcissement retroactif, c'est `CPL-5` sur un increment neuf.

---

## 8. Sur quelles proprietes je reste la seule garde a s24

Je le nomme explicitement, pour que l'equipe ne croie pas couvert ce qui ne l'est pas - quatre
defauts visibles par un enfant ont deja traverse une CI entierement verte sur ce depot.

- **`NAR-6`** - texte masque, la question ne se repond pas. Irreductible, du type de `COMP-2`. Trois
  items sur vingt attrapes le 2026-08-29 sur `cm1-comprehension`, precisement les trois que l'auteur
  croyait bons.
- **`NAR-4`, `NAR-7`, `NAR-8`, `NAR-9`** - la forme de la question, l'equilibre des prenoms, ce que
  la situation presuppose, ce que dit l'indice.
- **`PLU-1`** et **`FRA-8`** - un jugement de langue et ce que presuppose une situation de partage.
- **La classe aveugle du garde-fou d'accentuation** : 351 mots sur le corpus actuel, et plusieurs
  dizaines de formes verbales neuves sur `NAR`, chacune a lire dans sa phrase (section 1.3).
- **`SAI-6` et `SAI-7`** - les formes acceptees. Le point le plus dangereux de la mecanique de
  saisie : un bareme faux passe une CI verte sans laisser de trace. Sur ce premier paquet, `CPL-3`
  ferme la liste entierement, et **c'est le seul paquet ou je pourrai le faire**.
- **`SAI-8`, `SAI-9a`, `SAI-9b`, `SAI-2c`, `SAI-12`** - la formulation de tout ce que l'enfant lit
  apres avoir repondu. Un test verifie que deux chaines sont affichees ; il ne dit pas si un enfant
  de six ans comprend ce qu'il lit.
- **`R1` a `R6`** - six refus que rien d'autre que mon fichier de s19 ne tient. Le jour ou une story
  ouvrira une de ces lignes en saisie, la CI sera verte et je serai la seule a dire non.
- **`SEUIL-2`** - qu'une reponse juste ne coute jamais un essai. Aucune regle n'existe qui l'empeche.
- **Ce que je ne peux pas garder du tout** : le comportement d'un clavier virtuel de tablette.
  Personne dans cette squad n'a l'appareil. `SAI-2c` est ma seule protection ecrite contre ce trou,
  et elle rend une defaillance **visible**, pas impossible.

---

## 9. Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| Borne reconfirmee a 4-5 paquets ; l'audit d'une mecanique neuve n'y est pas price (2.2) | `sofia-sm`, `camille-po` | Que le compte de s24 soit lu comme 4 paquets **plus** un audit de mecanique |
| `DEN` vaut un audit mais n'entre pas a s24 ; il prend la place de `S24-SAI-2` sans nouvel arbitrage si le lot de saisie glisse (3) | `camille-po`, `sofia-sm` | Le perimetre est a `camille-po`. Je dis seulement ce que ma porte porte |
| Premier paquet de saisie : complements a 10 en saisie numerique, `CPL-1` a `CPL-8` (4) | `camille-po`, developpeurs | De quoi ecrire `S24-SAI-2` sans me redemander ce que je voulais dire |
| Done complet et fiche neuve sur ce paquet ; **un REFUSE n'y touche pas la ligne verte** (4.2) | `camille-po` | Confirmation de sa lecture, avec la precision qui compte |
| `NAR-2` x `NAR-5` satisfaisable, trois items de preuve, deux unites de compte precisees (5.1) | developpeurs, `sofia-sm` | Le point est clos. Aucun aller-retour n'est du |
| `FRA-5` famille (a) sur la forme (d), et `FRA-10` neuve (5.2) | developpeurs, `sofia-sm` | Le point est clos. Le developpeur ne choisit toujours pas la lecture : elle est arretee ici |
| `SAI-12` : trois reponses (4.4) | `theo-archi` | De quoi finir le lot 3 sans attendre |
| Zero ligne de couverture, confirme et etendu a 23 lignes instruites (6) | `sofia-sm`, l'utilisatrice | Que le chiffre de 28 a 33 cesse de circuler, et que la valeur reelle de 7.1 le remplace |
| Score parfait par elimination sur 94,6 pour cent des items - reserve 5 elargie (7.1) | `camille-po`, `theo-archi` | Un arbitrage qui n'est pas de moi : score decoratif assume, ou `M5` module par le nombre de propositions |
| Le depot a desormais un lexique morphologique : `SUB-4`, `SUB-5`, `PLU-1` deviennent mecanisables (7.2) | `theo-archi`, `yanis-qa`, `camille-po` | Du backlog, pas ce lot. Je dis que c'est possible |
| Ce que coute un accent sur la tablette visee | l'utilisatrice, par `sofia-sm` | **Ne mord pas sur s24** avec le paquet de la section 4. Mordra sur le premier paquet alphabetique |

---

## 10. Ce que ce document ne fait pas

- Il **ne prononce aucun Done pedagogique** et n'en anticipe aucun.
- Il **ne decide aucun perimetre de sprint** : `DEN`, l'ordre des lots et l'affectation sont a
  `camille-po` et `sofia-sm`.
- Il **ne decide rien de technique** : `ADR-0058`, `ADR-0059`, `ADR-0069`, `ADR-0070` font foi, et
  ce que je nomme en 4.3 et 4.4 est un resultat attendu, jamais un chemin.
- Il **n'efface aucun verdict anterieur.** Les deux corrections de la section 7 sont ecrites comme
  des ajouts dates, et les textes corriges restent lisibles a leur place.
- Il **ne modifie aucun fichier** sous `domain/`, `application/`, `adapters/` ou `bootstrap/`. Je
  les ai lus pour verifier trois faits ; je n'en ai touche aucun.
