# Clause opposable de la porte pedagogique - perimetre apres les six reponses de l'utilisatrice du
# 2026-08-31. Ce que je m'engage a faire, ce que je ne fais plus, et ce que la cloture de s24 coute

- **Auteur** : nadia-prof
- **Date** : 2026-08-31
- **Sprint** : s24 (en cours, perimetre gele par `DEC-0059` et non rouvert par ce document)
- **Saisi par** : `DEC-0060`, section 9, ligne *"La clause opposable de la porte pedagogique, dans les
  mots de celle qui la tient - nadia-prof - **Due**"*. `camille-po` a refuse de la pre-rediger et
  elle a eu raison : la porte est a moi, son libelle aussi.
- **Sources lues avant d'ecrire** : `DEC-0060`, `DEC-0061`, `DEC-0062` sur `s24-camille-2` ; mon
  arbitrage prealable de s24 du 2026-08-30 ; l'avis de faisabilite de `theo-archi`, lots 1 a 5 ; la
  proposition de rendu d'`ines-design`, section 4 ; le commit `67202b9` du lot 1 sur `s24-marc`.
- **Ce document ne prononce aucun Done pedagogique**, n'en retire aucun, n'en anticipe aucun, et ne
  rouvre le perimetre d'aucun sprint.

---

## 0. Ce que ce document est, et l'ordre dans lequel il est ecrit

Il est **la clause**, et **le chiffrage du cout de cloture de s24** que cette clause fait apparaitre.

Le cout vient en section 3, avant les justifications, parce que c'est l'information de planning et
qu'elle est perissable : trois developpeurs travaillent pendant que je l'ecris, et un cout de
cloture connu le 2026-08-31 vaut ce que le meme cout ne vaudra plus decouvert devant la porte.

**Ce que ce document ne fait pas** : il ne decide aucun perimetre de sprint, ne repriorise rien,
n'ecrit aucune story, ne touche a aucun fichier sous `domain/`, `application/`, `adapters/` ou
`bootstrap/`. J'en ai lu trois pour verifier des faits ; je n'en ai modifie aucun.

---

## 1. Une precision de provenance, sous la convention que l'utilisatrice vient d'accepter

`DEC-0062` est repondue : la troisieme forme d'attribution existe, le canal s'appelle **MASTER**
(Q5), et **un enonce de cette forme ne vaut jamais motif** (Q6).

**J'applique la convention a ma propre entree.** L'enonce qui m'a saisie - *"les 27 jeux deviennent
modifies a la cloture de s24 et rentrent dans ta porte"* - est **recu de MASTER, le 2026-08-31, sans
source dans le depot**. Il m'a ete donne comme une lecture, pas comme un fait, et son auteur m'a
demande de l'instruire moi-meme plutot que de le croire.

**Par Q6, je ne peux pas le prendre pour appui.** Ce que je fonde en sections 2 et 3 est fonde sur ce
que j'ai lu et mesure moi-meme, cite avec sa methode. Que la lecture recue se revele exacte ne change
rien a cette regle : c'est justement quand un enonce sans source se revele vrai qu'on prend
l'habitude de s'y appuyer.

**Ce que Q6 me coute a moi, et je l'ecris pour ne pas l'oublier** : je ne peux plus fonder un verdict
sur une consigne recue de MASTER sans qu'elle soit d'abord ecrite quelque part. Y compris quand elle
m'arrange.

---

## 2. La clause opposable

Neuf clauses. Elles s'appliquent a partir du 2026-08-31, s24 compris.

### C1 - Je ne prononce un verdict que sur ce qui est neuf ou modifie

Et mon verdict **porte sur ce qui a change**, pas sur le jeu entier autour. Une fiche de conformite
rouverte pour une correction dit ce qui a ete rejoue et ce qui ne l'a pas ete.

### C2 - Je ne rejoue pas un element non modifie

Le verdict deja ecrit dans sa fiche tient tel qu'il est ecrit, **avec ses reserves ouvertes**. Une
reserve n'est ni fermee ni soldee par cette clause. Un `VALIDE SOUS RESERVE` reste sous reserve.

### C3 - Je peux decliner, et je le ferai par ecrit

Toute demande d'audit portant sur un element non modifie, je la decline en citant `DEC-0060`, sans
avoir a en negocier le motif. **Un declin s'ecrit** : dans la fiche concernee ou dans le document du
sprint, avec sa date. Un declin non ecrit n'a pas eu lieu, comme un refus non ecrit.

### C4 - Un element modifie n'est pas exempte, mais l'audit que je lui dois est proportionne a ce qui a change

C'est la clause de fond, et elle repond a Q1 sans la contredire. Trois regimes, nommes ici pour la
premiere fois, dates du 2026-08-31.

| Regime | Ce qui a change | Ce que je fais | Ce que ca coute a ma borne |
|---|---|---|---|
| **R-A - audit de paquet** | le contenu ou l'ecran d'un jeu : enonce, bonne reponse, distracteur, indice, explication, texte support, consigne, retour lu par l'enfant | mes cinq etapes, en entier, **sur la totalite des items** | **1 paquet.** C'est ce que ma borne de 4-5 compte, et la seule chose qu'elle ait jamais comptee |
| **R-B - audit de mecanique** | le comportement d'une mecanique, donc de tous les jeux qu'elle sert a la fois, **sans qu'aucun contenu ne bouge** | je joue une **couverture exhaustive de la partition** des cas que la propriete changee distingue - jamais un echantillon, section 5 | **hors compte de paquets**, comme l'audit d'une mecanique neuve depuis le 2026-08-30 |
| **R-C - changement de code a rendu invariant** | la forme du code, et rien de ce que l'enfant recoit | **je ne rejoue pas**, et je le dis - mais ce n'est pas une exemption : c'est une charge **reportee sur la porte technique**, et la section 4 dit exactement ce qu'elle doit produire pour que je m'y appuie | **rien, si et seulement si la condition de la section 4 est remplie.** Sinon, rien n'est discharge et je l'ecris |

**Ce que R-C n'est pas** : R-C n'est pas "les changements techniques sortent de ma porte". Sous Q1
ils y sont. R-C dit **par quel acte je les en fais sortir**, et cet acte a une condition que
quelqu'un d'autre doit remplir. Si elle n'est pas remplie, les jeux restent dans ma porte sans
verdict, ce qui est un etat inconfortable et c'est voulu : sans cette possibilite-la, R-C serait un
tampon.

### C5 - Ma porte n'est pas un harnais de non-regression, et je refuse de le devenir

Quand la seule chose qui a change est **l'identite de ce qui est rendu**, la preuve de cette identite
est technique et se mesure. Un jugement pedagogique ne s'y applique pas : mes etapes 2, 3, 4 et 5
- ancrage au programme, niveau, exactitude, biais - portent sur un contenu qui, par hypothese, n'a
pas bouge d'un caractere.

Rejouer 27 jeux a la main pour verifier qu'un retypage n'a rien casse serait le harnais de
non-regression le plus lent et le plus cher que ce depot puisse se donner : **il mettrait cinq a sept
sprints a passer une fois**, sur un arbre qui aura change entre-temps. Je le refuse comme methode,
pas comme exigence : l'exigence est bonne, c'est le moyen qui est faux.

### C6 - Je n'echantillonne jamais le contenu, et R-B n'y touche pas

Ma condition de s17 - **la totalite des items et non un echantillon** - est inchangee, et elle
concerne R-A. R-B ne l'entame pas, parce que R-B ne juge pas de contenu : il partitionne des **jeux**
selon la seule variable que la propriete changee distingue, et il les couvre **tous**. La difference
est en section 5.2, et elle n'est pas rhetorique : un echantillon extrapole, une partition n'extrapole
rien.

### C7 - Le constat reste ouvert, et il ne consomme pas ma borne

Par Q3. Je peux lire du code, compter des items, signaler un defaut sur un jeu existant et le router,
**sans que cela soit valider ni retester**. C'est ce qui m'a permis d'elargir ma reserve 5 le
2026-08-30 et de faire apparaitre le defaut que `DEC-0061` tranche.

**Deux bornes que je m'impose, pour que le constat ne redevienne pas un audit deguise :**
- un constat **ne prononce, ne retire et ne modifie aucun verdict** ;
- un constat est **route**, jamais transforme en exigence par moi seule. Il produit un fait avec sa
  methode ; ce qu'on en fait appartient a `camille-po`.

### C8 - Je nomme, a chaque verdict, les proprietes dont je reste la seule garde

Inchange, et c'est la clause qui rend les autres tenables. Quatre defauts visibles par un enfant ont
traverse une CI entierement verte sur ce depot. Chaque fois que R-C ou C2 me font ne pas rejouer
quelque chose, **l'equipe doit savoir que rien d'autre ne le regarde**. La liste du 2026-08-30
(section 8 de mon arbitrage prealable) reste valable et n'est pas recopiee ici pour qu'elle n'y
pourrisse pas a moitie.

### C9 - Je n'efface rien, et je ne deplace pas mes poteaux

Un verdict, un declin, une reserve levee : j'ajoute et je date, je ne reecris pas. Les trois regimes
de C4 sont **neufs et dates du 2026-08-31** ; ils ne sont ni un durcissement ni un assouplissement
retroactif de ce que j'ai ecrit avant, et aucun verdict deja rendu n'est relu a leur lumiere.

---

## 3. Le cout de cloture de s24 - la reponse a la question qui presse

### 3.1 Ce que ma porte doit deja a s24, avant que les 27 n'entrent

Rappel du 2026-08-30, section 2.3, et **une precision que la lecture recue de MASTER omet** :

| Objet | Regime | Compte |
|---|---|---|
| `S24-NAR` - paquet `cp-texte-narratif` | R-A | 1 |
| `S24-PLU` - paquet `cp-pluriel` | R-A | 1 |
| `S24-FRA` | R-A | 1 |
| `S24-SAI-2` - premier paquet de saisie, complements a 10 | R-A | 1 |
| `S24-SAI-1` - la mecanique de saisie, neuve | audit de mecanique | **hors compte** |

**Ma borne est de 4 a 5 paquets, et l'audit d'une mecanique neuve n'y est pas price.** Je suis donc
deja **a** ma borne, pas en dessous : quatre paquets plus un audit de mecanique. L'enonce recu de
MASTER dit *"27 jeux plus les quatre paquets neufs, contre la borne de 4 a 5"* ; il compte juste les
quatre, et il **oublie l'audit de mecanique**, qui est le plus lourd des cinq objets et le seul dont
`SEUIL-1` a `SEUIL-5` dependent. Correction faite ici, elle est a mon avantage et je la fais quand
meme.

### 3.2 Ce que Q1 ajoute - et ma reponse a *"ma lecture est-elle juste ?"*

**Elle est juste sur le fait, et fausse sur l'inference.**

**Juste** : sous Q1, *modifie* se lit sur tout changement du code qui produit l'ecran de l'enfant.
Les lots 1 et 3 changent ce code. Les 27 jeux sont donc modifies au sens de `DEC-0060`, et la clause
d'exemption C2 cesse de les couvrir. **Je ne conteste pas cet arbitrage et je ne cherche pas a le
contourner** : il est de l'utilisatrice, il est clair, et il repond exactement a la question que
`camille-po` avait posee en deux branches.

**Faux** : *"ils rentrent dans ta porte"* si l'on entend par la 27 audits. Q1 dit **quels jeux sont
modifies**. Elle ne dit pas **quel audit une modification appelle** - c'est ma porte, c'est C4, et
cela n'a jamais ete proportionnel au nombre de jeux touches mais a **ce qui a change**.

**Un element de mon propre travail contredit-il cette lecture ?** Je suis allee chercher celui qui
me derange, pas celui qui m'arrange. C'est ma section 4.2 du 2026-08-30 : *"Un jeu jouable non audite
est un jeu non garde."* **Elle coupe dans le sens de MASTER, pas dans le mien** : elle dit qu'on ne
laisse pas un jeu jouable hors de toute garde. R-C n'y contrevient pas, parce que R-C ne laisse pas
les 27 sans garde - il **nomme** ce qui les garde et **exige** que cette garde produise quelque
chose. Ce qui y contreviendrait, ce serait R-C sans la condition de la section 4.

Ce que je maintiens contre MASTER, c'est le denominateur : ma borne de 4-5 compte des **audits de
paquet**. Elle ne price pas un controle d'identite de rendu, **parce que je n'en ai jamais fait un**.
Chiffrer les 27 a 5,4-6,75 sprints en les comptant comme des R-A serait gonfler le cout d'un travail
que je ne compte pas faire sous ce regime-la. Je ne le fais pas.

### 3.3 Le cout, par chemin, avec ce qui le determine

**La cloture de s24 n'est pas impossible. Elle depend d'une phrase que `theo-archi` n'a pas encore
ecrite**, et elle a trois issues de cout tres inegal.

| Chemin | Condition | Ce que je fais | Cout, dans s24 |
|---|---|---|---|
| **A** | `theo-archi` **mesure** l'invariance de rendu des 27, sous les quatre exigences de la section 4 | **un seul acte** : je lis la mesure, je verifie que son perimetre nomme les 27 et qu'elle compare ce que l'enfant recoit, et je discharge les 27 sous R-C dans un bloc date | **tenable.** Cet acte est du meme ordre que ma lecture d'`ADR-0065` du 2026-08-30, faite dans la meme session que trois autres questions |
| **B** | pas de mesure, mais `theo-archi` **etablit que le changement est uniforme** - qu'aucune branche introduite par les lots 1 et 3 ne depend du contenu d'un paquet | je joue une **couverture de partition** sous R-B : **2 jeux**, section 5.3, et j'ecris le verdict comme **degrade**, en disant ce qu'il ne couvre pas | **tenable, et plus faible.** C'est un audit degrade et il sera nomme tel dans la fiche |
| **C** | ni l'une ni l'autre | **je ne prononce rien sur les 27**, et j'ecris pourquoi | **la cloture de s24 est incomplete sur 27 jeux**, section 3.4 |

**Ce que le chemin C signifie exactement, pour qu'il ne se decouvre pas** : les Done deja prononces
sur les 27 restent ecrits, ils ne sont ni retires ni contestes - mais ils portent sur l'etat du code
**avant** s24, et sous Q1 ils ne portent plus sur l'etat **apres**. Rien ne les remplace. Ce n'est ni
un `REFUSE` - je n'ai trouve aucun defaut - ni un `VALIDE`. C'est **27 jeux entres dans la porte et
non ressortis**, et `camille-po` devra decider ce que la matrice en dit.

### 3.4 Mon estimation du chemin le plus probable, et pourquoi je la donne

**Je pense que le chemin A est atteignable pour le lot 1, et que le lot 3 est le vrai sujet.** Ce
n'est pas une mesure, c'est une lecture, et je la donne comme telle.

**Lot 1, lu par moi dans le commit `67202b9` sur `s24-marc`** - la totalite de son empreinte de
production sur le chemin des 27 jeux tient en quatre gestes :

- une branche `case ReponseAEcrire` ajoutee au `switch` de `BanditManchotSession.questionDe`, qui
  leve une exception ;
- une seconde permission a la couture scellee d'`Item` ;
- une entree de plus dans la table des fabriques de `YamlContentPackLoader`, sous la cle `saisie` ;
- de la Javadoc.

**Aucun des 27 paquets ne peut atteindre ces branches.** Mesure M5, section 6 : les 27 declarent
`question` (26) ou `additions-bornees` (1) ; **aucun ne declare `saisie`**. C'est une lecture, pas une
preuve : je n'ai lance aucun build, et l'inatteignabilite d'une branche lue a l'oeil n'est pas
l'invariance d'un rendu. **C'est exactement la frontiere entre ce que je peux dire et ce que
`theo-archi` doit mesurer**, et c'est pourquoi je ne discharge rien sur cette base.

**Lot 3 est d'une autre nature, et c'est lui que je surveille.** `EtatDePartie` porte `consigne` et la
session ; il est **directement sur le chemin de ce que l'enfant lit**. Le retyper de
`BanditManchotSession` vers `GameSession` est un elargissement de type, pas un ajout de branche
morte. C'est la que l'invariance se joue et c'est la qu'une mesure a de la valeur.

### 3.5 Ce que je demande, en une phrase, et a qui

**A `theo-archi`, porte par `sofia-sm`** : la phrase de cloture que `camille-po` lui a deja routee -
*l'invariance de rendu des 27 est-elle mesuree ou attendue ?* - **devient determinante pour ma
porte**, alors qu'elle etait informative il y a vingt-quatre heures. Section 4 dit ce qu'elle doit
contenir. **Si elle ne peut pas etre produite, la reponse "elle est attendue, non mesuree" est une
reponse recevable et je prefere l'avoir** : elle me met sur le chemin B ou C, et je saurai lequel.

---

## 4. Ce qu'une mesure d'invariance doit contenir pour que je m'y appuie

Sans cette section, R-C serait "la CI est verte donc je ne rejoue pas", ce que je refuse depuis
toujours. **Quatre exigences, et elles sont refusables.**

1. **Le perimetre nomme les 27 jeux**, un par un ou par une enumeration qui les couvre tous et qu'un
   tiers peut recompter. *"Le module `adapter-web` est vert"* n'est pas un perimetre : c'est un
   module, et un module mesure n'est pas le depot.
2. **La comparaison porte sur ce que l'enfant recoit** - l'enonce, les propositions, l'indice,
   l'explication, le retour apres une reponse, le score - et non sur le fait que des tests passent.
   Un test vert prouve que ce que le test regarde n'a pas bouge ; il ne dit rien de ce qu'il ne
   regarde pas, et les quatre defauts qui ont traverse une CI verte sur ce depot etaient tous dans
   cet angle-la.
3. **La methode est ecrite et reproductible**, et toute mesure partielle du reacteur porte `-am`.
4. **Si une telle mesure n'est pas productible, on le dit** plutot que d'en produire une plus faible
   sous le meme nom. Une mesure qui promet plus qu'elle ne tient est pire qu'une absence de mesure,
   parce que je m'appuierai dessus.

**Et une exigence de retour, qui est le prix de ma confiance** : si la mesure fait apparaitre une
difference sur un jeu, **ce jeu quitte R-C et passe en R-A**. Il compte alors dans ma borne, et il la
depasse si les quatre paquets de s24 sont deja pris. Je ne m'engage pas d'avance a l'absorber.

---

## 5. Q2 - ce qu'elle implique pour moi, et le regime d'audit de mecanique

### 5.1 Ce que Q2 tranche, et ce qu'elle ne tranche pas

Q2 etait : *"quand une correction partagee change le comportement des 27 jeux existants d'un coup,
rentrent-ils dans la porte ?"* Reponse : **"1 ils rentrent"**.

**Ils rentrent. Je le prends tel quel et je ne le rediscute pas.** L'issue 1 telle que `camille-po`
l'a decrite portait de plus une consequence chiffree - *"la correction s'etale sur cinq a sept
sprints d'audit"*. **Cette consequence etait son estimation, pas la question posee**, et elle reposait
sur l'hypothese que chaque jeu entre appelle un audit de paquet. C4 dit que non : ce qui entre appelle
l'audit de **ce qui a change**, et ce qui change dans une correction de `M5` est une propriete de
mecanique, pas 27 contenus.

Je ne corrige pas `camille-po` sur son perimetre : elle a ecrit elle-meme que l'issue 3 - un regime
d'audit de mecanique - *"n'existe nulle part dans ce depot aujourd'hui et qu'il faudrait ecrire"*, et
que l'ecrire demandait *"l'accord de celle qui tient la porte"*. **C'est ce que je fais ici.** L'issue
1 et le regime R-B ne s'excluent pas : les 27 rentrent, et R-B est la forme sous laquelle je les
traite.

### 5.2 Pourquoi R-B n'est pas un echantillon - et je tiens ma condition de s17

**Un echantillon** prend une partie de la population, la juge, et **extrapole** au reste. Il repose
sur une hypothese de representativite que personne ne verifie. C'est ce que j'ai refuse a s17 et je
le refuse encore : sur du contenu, **la totalite des items**.

**Une partition** identifie la ou les variables dont la propriete changee depend, enumere les valeurs
que ces variables prennent **reellement dans le catalogue**, et **couvre toutes les cellules
occupees**. Elle n'extrapole rien : elle etablit que tout jeu non joue appartient a une cellule dont
un representant a ete joue, **et que la propriete ne distingue pas les membres d'une cellule**.

**La partition a une condition de validite, et elle n'est pas de moi** : que la propriete changee ne
depende d'aucune autre variable que celles que j'ai partitionnees. C'est une affirmation sur le code,
elle appartient a `theo-archi`, et **elle doit etre ecrite dans l'`ADR` de la correction**. Sans
elle, ma partition est un echantillon qui s'ignore, et je ne l'emploierai pas.

**Consequence de calendrier, et elle inverse un ordre** : la partition d'une correction de `M5` **ne
peut pas etre ecrite avant l'`ADR` de cette correction**, puisqu'elle en depend. Je ne peux donc pas
donner aujourd'hui la liste des jeux que je jouerai a s25. Je donne la **methode**, et je m'engage a
produire la partition dans les jours qui suivent la publication de l'`ADR`, avant que le code ne soit
ecrit - c'est la discipline que `DEC-0057` a etablie.

### 5.3 Ce que la partition donne aujourd'hui, sur les faits que j'ai mesures

**Je le donne comme un ordre de grandeur, pas comme la liste de s25**, pour la raison de 5.2. Il
repond a la question *"est-ce concevable ?"* par un chiffre plutot que par une intention.

**Pour une correction du score parfait par elimination** (`DEC-0061`), la variable que la propriete
distingue est **le nombre de propositions d'un item**, eventuellement croisee avec **le mode de
production des items** (enumeres ou engendres) et avec **le moment d'apparition de l'indice**.
Mesures M3, M4, M5 et M6 de la section 6 :

| Cellule | Population | Un jeu qui la couvre |
|---|---|---|
| 2 propositions, enumere | 48 items, dans 3 paquets | `cm1-accords-groupe-nominal` |
| 3 propositions, enumere | 534 items, dans 23 paquets | `cm1-accords-groupe-nominal` |
| 4 propositions, enumere, indice immediat | 8 items, 1 paquet | `cm1-accords-groupe-nominal` |
| 4 propositions, enumere, indice differe (`indicesNommentLaReponse`) | 25 items, 1 paquet | `cm1-conjugaison-present` |
| 3 propositions, **engendre** | 1 paquet, items non enumeres | `cp-additions-jusqu-a-10` |

**Cinq cellules, couvertes par trois jeux** - et `cm1-accords-groupe-nominal` en couvre trois a lui
seul, ce qui n'est pas un hasard : c'est le seul paquet du depot qui porte les trois cardinalites.
**Aucun des 24 autres jeux ne presente a la propriete un cas que ces trois ne presentent pas.**

**Trois jeux, hors compte de paquets, contre 27 audits.** C'est la reponse a *"peux-tu concevoir un
regime d'audit de mecanique sans echantillonner"* : oui, et il n'est pas de cinq a sept sprints, il
est d'un sprint.

**Ce que R-B ne couvrira pas, et je le nomme maintenant** : la formulation du retour que l'enfant lit
apres la correction. Si la correction de `M5` change le texte lu par l'enfant - et une correction du
score en changera probablement un - **ce texte-la est du contenu et il passe en R-A**. Il est commun
aux 27, donc c'est **un** R-A, pas 27. Mais il en est un, et il compte dans ma borne.

---

## 6. Mes mesures, avec leur methode et leur perimetre

Toutes faites le **2026-08-31**, dans un worktree sur `s24-sofia` (fusion d'ouverture), **sans build,
sans `mvn`**, par lecture des fichiers. **Perimetre commun** : les 27 fichiers de
`adapters/adapter-content/src/main/resources/contenu/` et le catalogue voisin. Aucune de ces mesures
ne porte sur un module compile ; la question du `-am` ne se pose donc pas, et je le dis plutot que
de laisser croire que je l'ai oublie.

| # | Mesure | Methode | Resultat |
|---|---|---|---|
| **M1** | Nombre de paquets et d'entrees de catalogue | comptage des fichiers `*.yaml` du repertoire ; comptage des occurrences de la cle `paquet:` du catalogue | **27 et 27** |
| **M2** | Items enumeres | occurrences de la cle `enonce` en tete d'element de liste, fichier par fichier | **615 items sur 26 fichiers.** Le 27e, `cp-additions-jusqu-a-10`, n'a pas de liste `items` |
| **M3** | Nombre de propositions par item | 1 (la bonne reponse) + nombre de chaines de la liste `distracteurs` de l'item | **2 -> 48 items ; 3 -> 534 ; 4 -> 33** |
| **M4** | Paquets portant une cardinalite autre que 3 | agregation de M3 par fichier | **quatre** : `cm1-accords-groupe-nominal` (8/8/8 en 2/3/4), `cm1-conjugaison-present` (25 en 4), `cm1-homophones` (24 en 2), `cp-nature-mot` (16 en 2). Les 22 autres paquets enumeres sont uniformement a 3 |
| **M5** | Type declare par chaque paquet | cle `type:` de premier niveau | **26 `question`, 1 `additions-bornees`, 0 `saisie`** |
| **M6** | Indices | fichiers declarant `indicesNommentLaReponse` ; comparaison du nombre d'items et du nombre de cles `indice` par fichier | **un seul fichier declare la cle** (`cm1-conjugaison-present`) ; **aucun fichier n'a d'ecart** entre son nombre d'items et son nombre d'indices |

**M3 reproduit exactement les chiffres que j'avais publies le 2026-08-30** (48, 534, 33 sur 615), par
une methode differente de celle de ce jour-la. Je le verifie avant de m'en servir plutot que de me
citer : c'est la deuxieme fois cette semaine que je verifie un de mes propres constats, et la
premiere fois il etait faux.

**Ce que ces mesures ne disent pas** : rien sur le rendu, rien sur le comportement, rien sur les
paquets neufs de s24, qui ne sont pas encore fusionnes. Elles decrivent le catalogue servi
aujourd'hui, et c'est tout ce que je leur fais dire.

---

## 7. Q3 - ce que le constat ouvert me rend, et ce que j'en fais a s24

**Le constat reste ouvert.** C'est la reponse la plus consequente des six pour ma pratique, et je
dis pourquoi : sans elle, ma reserve 5 elargie du 2026-08-30 n'aurait pas pu etre ecrite, `DEC-0061`
n'existerait pas, et le score du produit serait toujours parfait par elimination sur 94,6 pour cent
des items **sans que personne ne l'ait ecrit**. Le defaut serait la, exactement aussi reel, et
invisible.

C'est aussi la clause qui empeche `DEC-0060` de devenir ce que sa section 5.c decrit : une part du
catalogue qui grandit et que plus personne ne regarde. **Le constat est le seul canal par lequel un
defaut ancien peut encore remonter**, et il ne coute rien a ma borne. Je m'engage a m'en servir.

---

## 8. La dependance `D5` d'`ines-design`

Elle est due avant la fusion du lot 4 et elle est tranchee, dans un document separe pour qu'un
developpeur la trouve sans lire celui-ci :
`squad/conformite/ARBITRAGE-D5-le-champ-de-saisie-apres-une-tentative-fausse.md`, du 2026-08-31.

**En un mot** : variante A - le champ reste rempli et corrigeable - **avec une condition** qui
supprime le risque qu'`ines-design` a nomme sans le minimiser.

---

## 9. Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **La phrase de cloture sur l'invariance de rendu des 27 est devenue determinante pour ma porte**, et la section 4 dit ce qu'elle doit contenir | `theo-archi`, porte par `sofia-sm` | Une reponse, y compris *"attendue, non mesuree"*. Elle me met sur le chemin A, B ou C |
| **Le cout de cloture de s24 par chemin** (3.3) | `sofia-sm`, `camille-po` | Que le chemin soit connu **avant** la cloture. Le chemin C laisse 27 jeux sans verdict, ce n'est pas un accident a decouvrir |
| **La clause opposable existe**, `DEC-0060` section 9 est servie | `camille-po` | Rien a faire, sinon la lire et me contredire si je l'ai mal comprise |
| **R-B est ecrit et il ne demande pas d'echantillon** (5.2, 5.3) | `camille-po` | L'issue 1 de Q2 et R-B ne s'excluent pas. La correction de `M5` n'est pas de cinq a sept sprints d'audit sous ce regime |
| **La partition d'une correction de `M5` depend de son `ADR` et ne peut pas etre ecrite avant** (5.2) | `theo-archi` | Que l'`ADR` de la correction **ecrive de quoi la propriete corrigee depend**. Sans cela, ma partition est un echantillon qui s'ignore |
| **Le texte lu par l'enfant apres une correction de `M5` sera du contenu**, donc un R-A qui compte dans ma borne (5.3) | `camille-po`, `sofia-sm` | Qu'il soit compte a s25, pas decouvert |
| **`D5` tranchee** | `ines-design`, `marc-dev`, `sofia-sm` | Le document separe. Avant la fusion du lot 4 |
| **Q6 me lie aussi** : je ne fonde plus un verdict sur un enonce recu de MASTER sans source (section 1) | `camille-po`, `sofia-sm` | Rien. C'est une contrainte que je m'applique et que j'ecris pour qu'on puisse me la rappeler |

---

## 10. Ce que ce document ne fait pas

- Il **ne prononce aucun Done pedagogique**, n'en retire aucun, n'en anticipe aucun.
- Il **ne ferme aucune reserve**. `R1` a `R6`, la reserve 5 elargie et les conditions de mecanique
  restent ce qu'elles sont.
- Il **ne rouvre pas le perimetre de s24** et ne demande a aucun developpeur d'ouvrir quoi que ce
  soit dans ce sprint.
- Il **ne contredit aucune des six reponses de l'utilisatrice**. Sous Q1 les 27 jeux sont modifies :
  je l'ecris, je ne le contourne pas, et C4 dit ce que ma porte fait d'un jeu modifie.
- Il **ne decide rien de technique** : la mesure d'invariance, la forme d'une correction de `M5` et
  l'uniformite d'un changement sont a `theo-archi`. Je dis ce dont j'ai besoin, jamais comment
  l'obtenir.
- Il **ne repriorise rien** et n'ecrit aucune story.
- Il **ne modifie aucun fichier** sous `domain/`, `application/`, `adapters/` ou `bootstrap/`. J'ai
  lu `Item`, `BanditManchotSession`, `YamlContentPackLoader` et `EtatDePartie` pour verifier quatre
  faits ; je n'en ai touche aucun.

---

## 11. Ajout date du 2026-08-31, apres-midi - ce que la livraison des quatre lots change a cette clause

Le texte ci-dessus n'est pas modifie. Cette section s'y ajoute, comme la clause C9 l'exige.

- **L'exigence 2 de la section 4 gagne une voie alternative**, sous double condition conjointe -
  code de rendu non modifie **et** code ajoute inatteignable, l'inatteignabilite etant enumeree et
  non lue. Le libelle et le motif sont dans
  `squad/conformite/SUITES-s24-apres-la-livraison-des-quatre-lots-2026-08-31.md`, section 2.2.
  **Le libelle d'origine de l'exigence 2 reste en vigueur partout ou les deux conditions ne sont pas
  reunies ensemble.**
- **Le lot 1 est discharge sous R-C** : chemin A acquis, aucun des 27 jeux n'entre en rejeu de son
  fait.
- **Le lot 3 existe** - `3dac4f5`, pousse sur `origin/s24-marc` le 2026-08-31 a 10:02 - contrairement
  a ce qui m'a ete transmis et a ce que la reponse d'invariance de `theo-archi`, ecrite quatorze
  minutes plus tard, a pu constater. **Le chemin C n'est donc pas ecarte pour s24**, et la section 3.3
  ci-dessus reste applicable telle qu'elle est ecrite.
- **La capture de reference que `theo-archi` chiffre ne contredit pas la clause C5.** C5 refuse que
  **je** sois le harnais de non-regression, pas qu'il en existe un. Je ne la demande pas ; si elle est
  financee, je m'en servirai.
- **Deux conditions neuves et non retroactives** sont posees ce jour : `NAR-11` sur les paquets a
  texte support, `CPL-9` sur le titre et la consigne d'une entree de catalogue. Elles vivent dans le
  document de suites, sections 4.3 et 5.
- **Une condition de contenu est amendee** : `FRA-5` gagne une famille (e) sur la forme (d) de
  `FRA-2`, avec son plancher et sa verification de satisfaisabilite
  (`squad/conformite/ARBITRAGE-FRA-la-forme-d-contre-le-seuil-minimal-de-rang.md`).
