# DEC-0063 - Sous `Q1`, les 27 jeux du catalogue deviennent modifies a la cloture de s24 : le cout
# est chiffre maintenant et non a la porte, le regime de la matrice est tranche, la file de reaudit
# est ouverte et non engagee

- **Type** : consequence de perimetre d'un arbitrage de l'utilisatrice ; regime de la matrice de
  couverture ; routage de capacite
- **Date** : 2026-08-31
- **Auteur** : camille-po
- **Sprint** : s24 (en cours)
- **Competence programme visee** : **transverse - pas de contenu pedagogique.**
- **Statut** : Acceptee
- **Lien** : `DEC-0060` et son amendement du 2026-08-31 (`Q1`, `Q2`) ; `DEC-0059` (perimetre de s24,
  gele) ; planning de s24, sections 2, 5 et 6.

## 0. Ce que ce document fait

**Il rend le cout de cloture de s24 connu maintenant plutot qu'au moment de la porte.** C'est la
seule chose qui presse, et c'est ce qu'il faut faire d'un cout : le chiffrer avant, pas le decouvrir
pendant.

**Il ne rouvre pas le perimetre de s24.** `DEC-0059` le gele, trois developpeurs travaillent dessus,
et rien ici ne change une ligne de ce qu'ils font aujourd'hui.

**Il ne tranche pas seul.** Une seule chose y est tranchee - le regime de la matrice, qui est mon
outil - et elle l'est sous une condition explicite que `nadia-prof` peut lever. Tout le reste est
route, avec un proprietaire nomme par question.

---

## 1. Le fait, et il decoule d'une seule ligne

L'utilisatrice a repondu `Q1` : *"tout changement du code"*. Un jeu est modifie quand le code qui
produit son ecran change - **l'effet de ce changement n'entre pas dans la qualification**
(`DEC-0060`, amendement, section A.2).

**Les lots 1 et 3 de s24 retypent du code de production partage par les 27 jeux du catalogue** :
`domain` (le type d'item et sa couture scellee), `adapter-content` (la fabrique), `bootstrap`, et
`adapter-web` (`EtatDePartie` et `EtatDePartieEnSession` retypes sur `GameSession`, **20 lignes dans
3 fichiers de production**, cout mesure par `theo-archi`, non reproduit par moi).

**Donc, a la fusion de ces lots, les 27 jeux du catalogue sont modifies.** Ce n'est pas une lecture,
c'est l'application litterale du critere retenu.

### Le chiffre, avec son unite et sa reserve

| Ce qui se presente a la porte de `nadia-prof` a la cloture de s24 | Nombre |
|---|---|
| Jeux existants devenus modifies | **27** |
| Paquets de contenu neufs (`S24-NAR`, `S24-PLU`, `S24-FRA`) | 3 |
| Premier paquet de saisie (`S24-SAI-2`), ou `S24-DEN` sous la bascule | 1 |
| Audit d'une mecanique neuve | 1, **hors borne** |
| **Borne reconfirmee par `nadia-prof` le 2026-08-30** | **4 a 5 paquets**, audit de mecanique non compris |

**31 contre 4 a 5.** Facteur six a sept.

**La reserve qui accompagne ce chiffre, et elle est decisive** : les deux nombres ne sont pas dans la
meme unite. La borne de `nadia-prof` se compte en **paquets neufs** - une vingtaine d'items a lire un
par un, contre une dizaine de conditions ecrites. **Rejouer un jeu dont le contenu n'a pas bouge
apres un retypage est un autre acte, et personne n'a encore dit ce qu'il coute.** Voir la section 4 :
c'est la question qui decide si le chiffre reel est 31 ou beaucoup moins, et elle n'est pas de moi.

---

## 2. Ce qui est reellement en cause, et ce n'est pas le sprint - c'est la matrice

Un sprint qui se cloture avec une file d'audit trop longue est un probleme de sequencement, et il se
resout. **Le probleme qui ne se resout pas tout seul est celui-ci** : la matrice porte aujourd'hui
**24 lignes `couvert-valide`** sur 43 atteignables, et chacune de ces lignes est adossee a un Done
prononce par `nadia-prof` **sur un etat du code qui, a la fusion des lots 1 et 3, n'existe plus**.

Trois lectures sont possibles a la cloture. Deux sont fausses.

| Lecture | Ce qu'elle produit |
|---|---|
| **(i) Les lignes restent `couvert-valide` sans rien dire** | La matrice affirme une couverture qu'aucun Done en vigueur n'adosse. **Un compteur qui affirme plus que ce qui le fonde** - la famille de defaut que ce depot sanctionne depuis s05, et celle que je suis chargee d'empecher |
| **(ii) Les lignes retombent a `en cours`** | La matrice passe de **24/43 a 0/43** a la cloture de s24. Enonce vrai au regard de la regle et **faux au regard du produit** : les 27 jeux fonctionnent, leur contenu a ete valide, et rien n'indique qu'ils aient casse |
| **(iii) Les lignes gardent `couvert-valide` et portent une mention datee** | La matrice dit exactement ce qui est : couvert, valide a telle date, sur un etat anterieur a tel changement, reaudit du |

---

## 3. Ce que je tranche - le regime de la matrice, et sous une condition explicite

**Je retiens la lecture (iii).** La matrice est mon outil et son regime est de ma main.

> **Une ligne dont le jeu devient modifie par un changement de code partage garde son statut
> `couvert-valide` et recoit une mention datee, sous la forme :** *"Done prononce le `<date>`, sur un
> etat du code anterieur a `<lot ou sprint>` ; reaudit du au titre de `DEC-0060`, `Q1`."*
>
> **La mention est retiree quand `nadia-prof` a reaudite, et par ce geste-la seulement.** Elle ne
> s'efface ni par le temps, ni par une CI verte, ni par moi.

**Le motif principal, et il vient de sa propre reponse.** L'utilisatrice a choisi `Q2` en designant
l'issue 1, dont le texte ecrit disait : *"les 27 rentrent, et la correction s'etale sur cinq a sept
sprints d'audit"*. **Etaler un reaudit sur plusieurs sprints n'a de sens que si la ligne ne
s'effondre pas entre-temps.** La lecture (ii) rendrait sa propre reponse inapplicable : elle
afficherait 0/43 pendant cinq sprints sur un produit qui n'a rien perdu. **La lecture (iii) est celle
qui rend sa reponse operable, et c'est le motif qui la choisit - pas mon confort de compteur.**

**Motif secondaire, et il joue contre moi** : la lecture (iii) est aussi celle qui protege le chiffre
de couverture dont je reponds. **Je le signale plutot que de le taire**, parce qu'un arbitrage rendu
par la personne dont il preserve l'indicateur doit etre lisible comme tel. C'est pour cela que la
condition ci-dessous existe.

### La condition, et elle n'est pas de forme

**Ce regime suppose qu'un Done de `nadia-prof` survit, assorti d'une mention, a un changement du code
qui produit l'ecran. Cela, c'est une affirmation sur son Done, et son Done est a elle.**

> **Si `nadia-prof` ecrit que son Done ne survit pas a un changement de code partage, meme mentionne,
> alors les lignes concernees retombent a `en cours` et j'applique sa lecture dans le meme geste,
> sans discussion et sans y opposer le cout d'affichage.** Sa clause gouverne la porte ; ce document
> ne gouverne que la matrice.

---

## 4. Ce que je ne tranche pas - trois questions, trois proprietaires

### 4.1 A `nadia-prof` - la question qui decide la magnitude de tout le reste

**Un reaudit apres retypage compte-t-il comme un paquet contre votre borne de 4 a 5, ou est-ce un
acte d'une autre nature et d'un autre cout ?**

C'est la question la plus utile de ce document, et voici pourquoi :

- **Si un reaudit de ce type coute un paquet**, alors 27 jeux valent cinq a sept sprints d'audit
  pleins, et le catalogue redevient modifie a chaque sprint qui touche du code partage - voir la
  section 5. **La regle telle qu'elle est repondue et la borne telle qu'elle est posee seraient alors
  arithmetiquement incompatibles**, et c'est un fait qui devrait retourner a l'utilisatrice.
- **Si c'est un acte plus court** - verifier qu'un ecran deja valide n'a pas bouge, sur un contenu
  qu'elle a deja lu item par item - alors 27 est une file, pas un mur, et tout ce document se
  degonfle.

**Je ne prejuge d'aucune des deux reponses et je n'en suggere aucune.** Je dis seulement que le
chiffre de la section 1 n'a pas de sens tant qu'elle n'a pas repondu, et je refuse de le faire
circuler sans cette reserve attachee.

**Ce que je peux offrir comme entree, et ce n'est pas une reponse** : `DEC-0060`, section 5.a, cite
sa propre liste des proprietes qu'aucun garde-fou executable ne tient. Ce sont celles-la qu'un
reaudit devrait regarder, et pas le contenu qu'elle a deja valide. **Le perimetre de ce qu'elle
regarde reste le sien.**

### 4.2 A `sofia-sm` - le sequencement, la capacite, et la porte de cloture

- **La file de reaudit des 27 n'est pas engagee a s24** (section 6). Son rang et son etalement se
  decident au planning de s25, quand la reponse de `nadia-prof` sera connue.
- **La porte de cloture de s24 peut-elle etre prononcee** avec une file de reaudit ouverte ? Ma
  reponse pour ce qui me concerne est oui : le Done technique est juge par la CI, le Done pedagogique
  des quatre paquets neufs est juge par `nadia-prof`, et la file des 27 est une dette nommee, pas un
  blocage. **Mais la conduite de la ceremonie est a elle**, et je ne prononce pas sa cloture.
- **La file de reaudit doit figurer au tableau des obstacles de `review.md` et dans `RAPPORT.md`**,
  ouverte tant qu'elle n'est pas videe.

### 4.3 A `theo-archi`, par `sofia-sm` - deux faits, aucune decision

- **L'enumeration des lots de s24 qui touchent du code producteur d'ecran.** Les lots 1 et 3 en sont
  sans doute possible. Pour les lots 2, 4 et 5b, la frontiere entre production et test se lit dans
  l'arbre. **Le critere est ferme (`DEC-0060`, A.2) ; l'enumeration est technique et elle est a
  lui.**
- **Une mesure d'invariance de rendu des 27 jeux existe-t-elle deja, ou se derive-t-elle de ce que
  les lots produisent de toute facon ?** Elle **ne change pas** la qualification - le critere porte
  sur le code, pas sur son effet - mais elle change ce que le reaudit coute a `nadia-prof`. **Si
  l'etablir demandait un lot neuf, la decision de le prendre est de capacite, donc de `sofia-sm`, et
  je ne la prends pas.** Une reponse *"non, et cela couterait un lot"* est une reponse complete.

---

## 5. Le fait structurel que je nomme maintenant, sans rouvrir l'arbitrage

**Sous `Q1`, ce n'est pas un evenement de s24 : c'est le regime permanent.** Tout sprint qui touche
du code de production partage - un retypage, un correctif de mecanique, une feuille de style commune,
`M5` - **remodifie l'integralite du catalogue**. s24 le fait. La plupart des sprints le feront.

**Je ne conteste pas l'arbitrage** : il est de l'utilisatrice, il a ete rendu sur des questions qui
en nommaient les consequences, et la lecture qu'elle a choisie a un merite reel que ma proposition
n'avait pas - **elle constate un geste au lieu d'interroger un effet, donc elle ne se discute pas au
cas par cas.** C'est une regle qui tient toute seule, ce que la mienne n'aurait pas fait.

**Ce que je fais, c'est nommer la condition de retour.** Si `nadia-prof` repond en 4.1 qu'un reaudit
apres retypage coute un paquet, alors la regle et la borne sont incompatibles par arithmetique, et
**ce fait-la doit retourner a l'utilisatrice avec les deux chiffres a cote l'un de l'autre**. Ce
n'est pas reposer une question qu'elle vient de trancher : c'est lui rendre un fait qu'aucun de nous
n'avait quand elle l'a tranchee.

---

## 6. Ce que la cloture de s24 doit porter, et rien de plus

1. **La file de reaudit des 27 jeux est ouverte, nommee, et non engagee a s24.** Elle ne consomme
   aucune capacite de ce sprint.
2. **Elle est comptee a partir d'aujourd'hui** : ouverte a s24, le 2026-08-31, premier arbitrage de
   rang du au planning de s25. **Si elle n'est toujours pas engagee ni arbitree a l'ouverture de
   s26, c'est un report et il devra etre ecrit comme tel** - la discipline de `DEC-0007` vaut pour ce
   que je cree comme pour ce que je repousse.
3. **La mention datee de la section 3 est portee sur les lignes concernees de
   `squad/programme/MATRICE-couverture.md` au moment de la fusion des lots 1 et 3**, pas avant : les
   lots ne sont pas fusionnes aujourd'hui et je n'ecris pas dans la matrice un fait qui n'est pas
   encore vrai. **C'est de ma main et je le porterai.**
4. **Aucune ligne ne passe `couvert-valide` du fait de ce document**, et aucune n'en sort.
5. **Le `RAPPORT.md` de s24 porte le chiffre de la section 1 avec sa reserve d'unite attachee.** Un
   "31 contre 4 a 5" cite sans la reserve de la section 4.1 serait un chiffre sans son perimetre.

---

## 7. Ce que cette decision ne fait pas

- Elle **ne rouvre pas le perimetre de s24** et ne demande a aucun developpeur de changer quoi que ce
  soit a ce qu'il fait.
- Elle **ne prononce aucun Done pedagogique**, n'en anticipe aucun, et n'en retire aucun.
- Elle **ne modifie aucune ligne de la matrice aujourd'hui.**
- Elle **ne tranche rien de technique** : l'enumeration des lots et la question de l'invariance de
  rendu sont a `theo-archi`.
- Elle **ne dit pas ce que `nadia-prof` doit regarder dans un reaudit**, ni ce qu'il doit lui couter.
- Elle **ne conteste pas l'arbitrage de l'utilisatrice** et ne le repose pas. Elle nomme une
  condition sous laquelle un fait neuf devra lui etre rendu.

---

## Amendement du 2026-08-31 (cloture de s24) - la mention prevue en section 3 n'est pas ecrite, et c'est `nadia-prof` qui l'a rendue sans objet

### A.1 Ce qui s'est produit

La section 3 prevoyait qu'une ligne dont le jeu devient modifie par un changement de code partage
garde `couvert-valide` **et recoive une mention datee** *"reaudit du au titre de `DEC-0060`, `Q1`"*.
Sous `Q1`, les lots 1 et 3 rendaient bien les 27 jeux modifies, et le chiffre de la section 1 - 31
contre une borne de 4 a 5 - etait exact a sa date.

**La mention n'est pas ecrite dans la matrice, parce qu'elle serait fausse.** `nadia-prof` a prononce
le 2026-08-31 :

> *"Les lots 3 et 4 sont discharges sous R-C. Les 27 jeux existants ne sont pas rejoues, et le chemin
> C est ecarte pour s24."*
> `squad/conformite/CLAUSE-PORTE-PEDAGOGIQUE-perimetre-apres-les-six-reponses-du-2026-08-31.md`,
> section 12.

**Aucun reaudit n'est du. La file de reaudit ouverte par la section 6 est videe avant d'avoir servi.**

### A.2 Ce que cela dit de cette decision, sans l'arranger

**La condition explicite de la section 3 a joue exactement comme elle etait ecrite**, et dans le sens
que je n'avais pas anticipe. J'avais ecrit : *"Sa clause gouverne la porte ; ce document ne gouverne
que la matrice"*, en prevoyant le cas ou elle durcirait. **Elle a allege**, et la matrice suit de la
meme facon. Une condition qui ne vaudrait que dans le sens defavorable ne serait pas une condition.

**Ce que j'avais mal estime, et je l'ecris plutot que de le laisser passer** : la section 4.1 posait
a `nadia-prof` la question *"un reaudit apres retypage compte-t-il comme un paquet contre votre
borne ?"* en presentant deux issues - un paquet chacun, ou un acte plus court. **Elle en a trouve une
troisieme que je n'avais pas vue** : etablir que le reaudit n'a pas lieu d'etre, par une preuve de
structure - garde invariablement vraie plus corps identique a l'octet - qu'elle a ajoutee a ses
propres exigences comme une voie `(iii)`, datee et non retroactive, apres que `theo-archi` l'a
etablie et qu'elle l'a reverifiee elle-meme. **Mon tableau de la section 2 offrait trois lectures a
la matrice ; il n'envisageait pas que la question elle-meme puisse tomber.**

**Le chiffre de la section 1 n'est pas efface** : il etait exact le matin du 2026-08-31, avec sa
reserve d'unite attachee, et c'est lui qui a rendu le cout de cloture connu **avant** la porte plutot
que pendant - ce qui etait l'objet de ce document et reste acquis.

### A.3 Ce qui reste vrai, et ce qu'il faut en garder

- **La section 5 tient integralement.** Sous `Q1`, tout sprint qui touche du code de production
  partage remodifie l'integralite du catalogue. C'est le regime permanent, s24 en a ete la premiere
  application, et il s'est bien produit.
- **La decharge ne cree aucune presomption**, et c'est `nadia-prof` qui l'ecrit : chaque lot partage
  est instruit pour lui-meme, et les deux clauses de sa voie `(iii)` sont a etablir a chaque fois.
  **Un lot qui modifierait un corps qui s'execute ferait rentrer les 27 dans la porte, et ce jour-la
  la mention de la section 3 sera due.** Elle n'est pas retiree de cette decision : elle est en
  attente d'un cas.
- **La section 3 reste le regime de la matrice** pour ce cas-la. Elle n'est ni annulee ni remplacee.
- **La condition de retour a l'utilisatrice de la section 5 n'est pas declenchee** : `nadia-prof` n'a
  pas repondu qu'un reaudit coute un paquet, elle a etabli un chemin ou il n'y en a pas. **La regle
  et la borne ne sont donc pas montrees incompatibles**, et rien ne remonte a l'utilisatrice de ce
  fait.
- **Ce qui remonte, en revanche** : la voie `(iii)` a un cout, et il est nomme par `nadia-prof`
  elle-meme - la capture de reference chiffree par `theo-archi` *"reste la seule voie pour un lot qui
  modifie un corps qui s'execute"*, et elle n'est pas financee. **C'est du backlog d'outillage, avec
  un proprietaire technique, et je le note au rang de s25 sans l'engager.**

### A.4 Ce que cet amendement ne fait pas

- Il **ne retire pas la section 3** et ne l'annule pas : elle vaut pour le prochain lot partage qui
  ne pourra pas etre discharge.
- Il **ne prononce aucun Done** et n'en interprete aucun. La decharge est de `nadia-prof`, je la
  reporte.
- Il **ne rouvre pas s24**, clos.
