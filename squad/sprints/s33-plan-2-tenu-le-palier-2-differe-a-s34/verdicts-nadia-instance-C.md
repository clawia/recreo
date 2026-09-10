# s33 - Verdicts de `nadia-prof`, instance C - education physique et sportive CP

- **Auteur** : `nadia-prof`, professeure des ecoles, **seule autorite sur le Done pedagogique**.
- **Date** : 2026-09-10.
- **Instance** : **C**, condition de terrain `PAR-2` (matiere reservee), affectation fixee par le
  planning de s33, section 5, et par l'arbitrage prealable, section 6.1.
- **Perimetre** : `S33-EPS-SEC` (reprise de `EPS-CP-04`) et `S33-EPS-JEU` (reprise de `EPS-CP-08`).
  Le lot de l'effort et du corps (`EPS-CP-10`, `EPS-CP-11`) n'est pas livre - il n'a jamais recu de
  story (planning, section 10), et **je n'audite pas ce qui n'existe pas**.
- **Arbre** : `round-two` a `b45e5927`, huit stories payees sur onze, rien de pousse. Jar reconstruit
  depuis un worktree dedie, application lancee sur un port local, arretee **par l'identifiant de son
  processus** a la fin de mes parties.
- **Ce document ne touche pas `squad/programme/MATRICE-couverture.md`**, n'ecrit aucune story, ne
  repriorise rien et ne cree pas `review.md`, qui est un fichier de ceremonie de `sofia-sm`.

---

## 1. Les deux verdicts, en tete

| Ligne | Paquet | Verdict | Motif en une ligne |
|---|---|---|---|
| **`EPS-CP-04`** | `bandit-manchot-regles-securite-eps-cp` | **REFUSE** (second refus) | **`SEC-R4`** - sur les items 3 et 8, la situation met l'enfant sur sa trottinette, les propositions le decrivent a pied, et la reponse creditee n'est pas la regle qui s'applique |
| **`EPS-CP-08`** | `bandit-manchot-regles-jeu-collectif-eps-cp` | **REFUSE** (second refus) | **`JEU-R9`** - l'item 8 credite une sanction de l'epervier que le paquet contredit deux items plus haut, et marque fausse la reponse que le paquet lui-meme enseigne |

**Aucune des deux lignes ne passe a `couvert-valide`.** Le taux reste **66 / 138 = 47,8 %** sur mon
terrain ; `camille-po` tient le compte, pas moi.

**Ce que je dis avant les motifs, parce que c'est vrai et que ce n'est pas la partie facile a
ecrire** : **les onze motifs de s32 sont leves, un par un, et j'ai verifie chacun.** `SEC-R3`,
`EPS-7`, `SEC-R1`, `SEC-R2`, `JEU-R1` a `JEU-R8` : tous payes. Les deux refus d'aujourd'hui portent
sur des defauts que **la reprise a introduits** - un mot ajoute a un enonce sans que le reste de
l'item le suive, une regle remplacee par une autre sans que le paquet la porte. **Ce n'est pas le
meme refus servi deux fois, et l'equipe n'a pas travaille pour rien.**

---

## 2. J'ai joue, et voici comment

**Deux paquets, seize parties, les vingt-deux items vus a l'ecran.**

| Ce que j'ai fait | Detail |
|---|---|
| Jar reconstruit | `mvn -B -DskipTests package` depuis un worktree dedie, sur l'arbre `b45e5927` |
| Application lancee | sur un port local, arretee par l'identifiant de son processus a la fin |
| Parties jouees | **huit par paquet, dix manches chacune**, ce qui a servi **les onze items de chaque paquet** plusieurs fois |
| Erreurs volontaires | **sur chaque item non encore rencontre, j'ai presse les propositions dans l'ordre affiche**, donc j'ai lu l'indice servi apres la premiere erreur et le comportement apres la seconde |
| Ecran de fin | atteint et lu sur les deux paquets |

**Audit degrade sur un point, et je le declare, comme a s32** : j'ai joue par requetes HTTP reelles,
donc j'ai lu le **HTML servi**, pas la page rendue par un navigateur. Cela couvre les textes, l'ordre
des ecrans, l'enchainement apres une erreur, les indices, le tirage et l'ecran de fin. **Cela ne
couvre ni la couleur, ni le contraste, ni la taille, ni la position a l'ecran**, et aucune conclusion
de ce document n'en depend.

**Une chose a mon debit sur la methode, et je l'ecris parce qu'elle a failli me faire ecrire un
faux.** Mes premieres parties ont rendu **fausse la bonne reponse de chaque item**, y compris celle
que le fichier declare. J'ai failli en faire un constat de defaut. La cause etait chez moi : mon
outil de jeu envoyait les propositions accentuees dans un encodage que le serveur ne lisait pas. **Un
verdict pedagogique bati sur un outil que l'auditrice n'a pas verifie ne vaut rien**, et la regle qui
en sort est la meme qu'a s32 avec le jar verrouille : **quand l'ecran dement le fichier, on soupconne
d'abord son propre montage.**

---

## 3. Le prealable que je verifie avant tout : le Done technique

**Je ne valide ni ne refuse au fond un increment dont le Done technique n'est pas acquis.** Il l'est,
et il est nomme job par job par `sofia-sm` (planning, sections 13 et 14) : `build`, `chemins`,
`outils`, `prefixes`, quatre verts, **1443 tests**, reacteur complet a six modules. **Je ne le
deduis pas de `mvn verify`** et je ne le recompte pas : ce n'est pas ma porte.

Ce que j'y ajoute et qui est de mon ressort : **l'application se lance, les deux jeux se jouent de
bout en bout, aucun ecran ne plante, l'enfant avance toujours.** Le Done technique de ces deux
increments n'est pas une abstraction pour moi : je l'ai constate en jouant.

---

## 4. Corrections a mes propres constats, faites avant de m'en servir

**Quatre, et trois d'entre elles jouent contre moi.**

### 4.1 Mon exemple du referentiel portait le defaut que je refuse aujourd'hui

J'ai ecrit le 2026-09-03, dans le jugement de faisabilite de
`squad/programme/education-physique-et-sportive/competences-cp.md`, ligne 4 :
*"Ou regarde-t-on avant de traverser en trottinette ?"*. J'ai repris cet exemple **mot pour mot** a
s32 dans `SEC-R1` pour dire ce que j'attendais.

**L'equipe l'a suivi mot pour mot, et c'est exactement l'item que je refuse aujourd'hui.** Un enfant
de six ans qui traverse une rue avec sa trottinette **en descend et la pousse** ; il ne la traverse
pas en roulant apres avoir regarde. **Mon exemple posait la question au mauvais endroit de la
regle**, et l'equipe n'avait aucune raison de s'en mefier.

> **Ce que j'en fais.** `SEC-R1` est **levee** : elle demandait un engin roulant ou un lieu de
> pratique, elle l'a. Je ne la rouvre pas, je ne la durcis pas, et **je ne refuse pas sur elle**. Le
> refus porte un identifiant neuf et date, `SEC-R4`, sur un fait que la reprise a cree.
>
> **Et l'exemple du referentiel est a corriger** : je le route a moi-meme pour le prochain diff de ce
> fichier, plutot que de le corriger dans un document de verdicts ou personne n'ira le chercher.

### 4.2 `EPS-7` disait quel mot retirer, jamais quel fait mettre a la place

`EPS-7`, que j'ai ecrite le 2026-09-09 : *"aucun indice d'EPS ne nomme le champ lexical qui distingue
la bonne reponse"*. **C'est une condition de retrait, et elle a ete tenue par retrait.** Trois des
quatre indices reecrits n'enseignent plus rien (`SEC-R5` ci-dessous). **L'equipe a fait ce que ma
condition demandait ; c'est ma condition qui etait incomplete.**

> **Ce que j'en fais.** `SEC-R5` est une reserve **non bloquante**, et la condition qui la
> generalise, **`EPS-8`, est datee du 2026-09-10 et n'est opposable qu'au prochain lot** (section
> 10). **Je ne refuse pas sur une exigence que je viens d'ecrire.**

### 4.3 `JEU-R6` : mon propre seuil me dit de la lever, et je la leve

`JEU-R6` portait sur les items 10 et 11. **L'item 11 est corrige au fond** ; l'item 10 ne l'est pas
(`JEU-R10`). Mon seuil de `FAB-1c` est *plus d'un tiers des items de la tache* : sur une tache de
cinq, il est franchi **a deux items**. Il en reste **un**.

> **`FAB-1c` est donc tenue sur la tache B, et `JEU-R6` est levee.** C'est le meme geste qu'a s32,
> ou j'ai ecarte l'item 6 du paquet de securite en ecrivant *"un item sur six ne fait pas une tache
> qui se gagne au ton"*. **Une barre qu'on applique dans un sens s'applique dans l'autre**, et ce
> qui reste sur l'item 10 est une reserve, pas un motif.

### 4.4 Deux comptes d'items que j'avais ecrits sont faux, et l'un change un prix

**Compte de `S33-EPS-JEU`.** J'ai ecrit *"six items sur onze"* dans mon arbitrage (section 5.5), et
`DEC-0085` comme le planning l'ont repris. **Le diff livre en touche sept** : item 4 (enonce), item 5
(entier), item 6 (entier), item 7 (entier), item 8 (entier), item 10 (distracteurs), item 11
(distracteurs). Mon compte de six oubliait l'item 6, que `JEU-R7` a fait reecrire. **Sept sur onze,
et le tarif de `PAR-14` ne change pas** - il etait deja au tarif de lot.

**Compte de `S33-EPS-SEC`, et celui-ci change un prix.** Le perimetre touche est de **six items sur
onze** : item 1 (indice), item 3 (enonce), item 8 (enonce, deux distracteurs, indice), items 9, 10 et
11 (deux distracteurs et l'indice chacun). **Six sur onze est plus de la moitie de onze**, donc
`PAR-14` s'applique **aussi a cette reprise**, et elle vaut **1,0 et non 0,5**.

> **La phrase fautive est de moi et je la nomme** : *"Aucune autre reprise de ce sprint n'atteint la
> moitie de son paquet ; la clause ne s'applique donc qu'a `S33-EPS-JEU`"* (planning, section 8,
> reprise de `DEC-0085`, section 8, elle-meme reprise de mon arbitrage). **Elle etait deja fausse sur
> le perimetre planifie**, avant tout diff : `SEC-R3` et `EPS-7` touchent quatre items, `SEC-R1` en
> ajoute un, `SEC-R2` un autre - six.
>
> **La correction est contre l'equipe et je la fais quand meme** : elle rencherit un poste. `PAR-14`
> est une regle de mon budget d'audit et **elle ne releve la barre d'aucun increment** - aucun des
> deux refus d'aujourd'hui n'en depend. Consequence chiffree en section 14.

---

## 5. `EPS-CP-04` - `bandit-manchot-regles-securite-eps-cp` - **REFUSE**

### 5.1 Ce que la reprise a paye, et que je ne redemanderai pas

| Motif de s32 | Etat | Ce que j'ai mesure a l'ecran |
|---|---|---|
| **`SEC-R3`** (motif bloquant du premier refus) | **LEVEE** | Sur les items 8, 9, 10 et 11, **les trois propositions nomment desormais un danger, une blessure ou une consequence, dont deux fausses.** Verifie une par une : item 8 *trebucher* / *se cogner* / *voiture* ; item 9 *eau trop froide* / *compter les nageurs* / *porter secours* ; item 10 *tomber en attendant* / *glisser trop vite* / *se heurter* ; item 11 *avaler de l'eau* / *glisser sur le bord* / *se blesser au fond*. **La strategie "prendre la reponse qui parle de se faire mal" ne gagne plus rien** |
| **`EPS-7`** | **LEVEE a la lettre** | Aucun des quatre indices ne reprend le mot qui distinguait la bonne reponse : *voiture*, *danger*, le choc, *blesser* ont tous disparu de l'indice de leur item. **Sur la lettre de ma condition, c'est fait.** Ce qui reste est `SEC-R5`, non bloquant, et sa cause est chez moi (section 4.2) |
| **`SEC-R1`** | **LEVEE** | Les items 3 et 8 portent un engin roulant. `EPS-5` demandait *"un engin roulant, l'eau, un agres, un parcours, un gymnase, une cour"* ; la trottinette en est un. **Je ne rediscute pas ma propre condition apres coup** |
| **`SEC-R2`** | **LEVEE** | L'indice de l'item 1 ecarte desormais la creme solaire explicitement : *"La crème solaire protège du soleil, pas de l'eau. Il faut d'abord l'autorisation de l'adulte."* C'est la formulation que j'avais donnee, et l'enfant qui choisit la creme sait maintenant pourquoi elle ne convient pas |

**Le reste du paquet est intact et reste bon** : la tache A, les onze bonnes reponses hors des deux
items en cause, la lisibilite - six a dix mots par enonce, une phrase, aucun mot hors du vocabulaire
de CP -, le traitement de l'erreur, l'absence de tout stereotype. **`FER-1` vacuement tenue, `FER-2`
onze items, `FER-3` deux taches a six et cinq items, `BIN-1` zero item binaire, `FAB-1a`, `FAB-1b`,
`PLA-1`, `EPS-2`, `EPS-3`, `BIA-4` tenues** - reverifiees, non rediscutees.

### 5.2 Le motif du refus - **`SEC-R4`**

**Items 3 et 8. Deux elements, et le second est celui qui bloque.**

**Element 1 - le mot ajoute a l'enonce n'a pas ete porte dans les propositions.** N'importe qui peut
le verifier, aucune expertise n'est requise.

| Item | Ce que dit l'enonce | Ce que disent les propositions |
|---|---|---|
| 3 | *"Avant de traverser une rue **en trottinette**, que doit-on faire ?"* | *"courir sans s'arrêter"* - **on ne court pas sur une trottinette** ; *"attendre qu'un camarade traverse d'abord"* - deux pietons |
| 8 | *"Pourquoi regarde-t-on des deux côtés avant de traverser **en trottinette** ?"* | *"pour éviter de trébucher sur le trottoir"*, *"pour ne pas se cogner à un passant"* - **un enfant a pied sur un trottoir** |

L'enonce dit que l'enfant roule, les propositions disent qu'il marche. **Le mot a ete insere,
l'item n'a pas ete reconstruit autour.**

**Element 2 - la reponse creditee n'est pas la regle qui s'applique, sur une ligne dont l'objet est
la securite du corps.** A la question *"Avant de traverser une rue en trottinette, que doit-on
faire ?"*, le jeu credite **"regarder des deux côtés"** et ne propose rien d'autre. Ce qu'on apprend a
un enfant de cet age, et ce que dit l'education a la route a l'ecole, c'est qu'**on descend de sa
trottinette et qu'on la pousse a la main pour traverser**. Regarder vient ensuite, et ne vient jamais
seul.

**Un enfant de six ans qui joue cet item retient qu'on traverse la rue sur sa trottinette apres avoir
regarde.** Le premier geste de la regle manque, et c'est celui qui sort le corps de la chaussee.

**Pourquoi c'est un refus et non une reserve.** Mon critere, ecrit le 2026-09-06 et que je n'amende
pas : un defaut est bloquant si *quelque chose de faux est enseigne* **ou** si *un item se gagne sans
la competence*. **La premiere branche mord.** Aucune phrase du paquet n'est fausse prise isolement -
*"regarder des deux côtés"* est vrai. Mais **un jeu de choix n'enseigne pas des phrases : il enseigne
la reponse qu'il credite**, et celle-ci est la mauvaise reponse a la question posee. Devant un eleve
qui me repondrait cela dans ma classe, je corrigerais ; le jeu, lui, affiche *"Bravo !"*.

**Ce que j'attends - deux voies, et je dis laquelle je prendrais.**

1. **Remplacer les deux items par deux items d'une activite d'EPS** - la cour, le gymnase, un agres,
   un parcours d'equilibre, l'eau. **C'est la voie que je prendrais** : elle regle la regle de
   securite *et* l'ancrage, et le paquet en sort entierement dans son champ.
2. **Ou corriger la regle** : *"En trottinette, comment traverse-t-on une rue ?"* avec *"on descend
   et on la pousse"* en bonne reponse, et deux propositions qui decrivent aussi un enfant sur une
   trottinette. L'item 8 suit : *"Pourquoi descend-on de sa trottinette pour traverser ?"*

**Dans les deux cas, `FER-2` reste a onze items, `FER-3` ne bouge pas, la tache A n'est pas touchee,
et rien de technique n'est en cause.** Le refus est proportionne : **deux items sur onze.**

### 5.3 La reserve non bloquante - **`SEC-R5`**

**Trois des quatre indices reecrits n'enseignent plus rien.** Releves a l'ecran apres ma premiere
erreur sur chaque item.

| Item | Indice avant s33 | Indice servi aujourd'hui | Ce qu'il apprend a l'enfant qui vient d'echouer |
|---|---|---|---|
| 8 | *"Une voiture peut arriver de la gauche comme de la droite."* | *"Regarder des deux côtés permet de voir ce qui arrive avant de traverser."* | **Rien** : il repete l'enonce en le retournant |
| 9 | *"Un danger dans l'eau peut survenir très vite."* | *"Un adulte présent peut réagir tout de suite si besoin."* | **Le fait qui tranche.** Celui-ci est bon, et il montre que c'etait faisable |
| 10 | *"Une glissade à deux peut faire tomber l'un des deux enfants."* | *"Descendre seul laisse de la place en bas du toboggan."* | Un fait vrai, mais **pas la raison de la regle** : la place en bas n'est pas ce qui evite le choc |
| 11 | *"Plonger dans une eau trop basse peut blesser la tête ou le cou."* | *"Vérifier avant de plonger évite les mauvaises surprises."* | **Rien**, et *"les mauvaises surprises"* est de surcroit une formule que le CP lit au premier degre - une surprise, a six ans, est une bonne chose |

**Les trois propositions d'un item nommant desormais toutes un danger, l'indice est le seul texte qui
peut dire a l'enfant lequel est la vraie raison. Trois fois sur quatre, il ne le dit plus.**

**Non bloquant, et le motif est de gouvernance** : `EPS-7`, telle que je l'ai ecrite, ne demandait
que le retrait d'un mot. **L'equipe a tenu ma condition ; je ne refuse pas sur ce que je n'avais pas
ecrit.** La condition qui l'ecrit est `EPS-8`, datee d'aujourd'hui, opposable au prochain lot.

**Ce que j'attends** : que chaque indice de la tache B donne **un fait sur la situation** que l'enfant
ne peut pas deviner, comme le fait celui de l'item 9. Exemple sur l'item 11 : *"Sous l'eau, le fond
est plus près qu'il n'en a l'air."*

### 5.4 Ce que je ne transforme pas en reserve, et je le dis pour qu'on ne me le redemande pas

- **L'indice de l'item 3 (tache A) dit *"aucune voiture n'arrive"*, et c'est la bonne reponse de
  l'item 8 (tache B).** J'ai regarde si cela designait l'item 8 : **je l'ecarte.** Un indice de la
  tache A qui enseigne la raison, puis un item de la tache B qui la demande, c'est un ordre
  d'apprentissage correct - le contraire du defaut de `JEU-R7`. Meme constat sur le toboggan entre
  l'item 4 et l'item 10. **C'est du bon travail et je ne le penalise pas.**
- **L'indice de l'item 1 fait deux phrases et dix-sept mots**, le plus long du paquet. `LON-1` et
  `LON-2` portent sur les **enonces** et sont executees par `ADR-0089` ; **je n'ai aucune barre sur
  la longueur des indices et je n'en cree pas une en cours de sprint.** Je note que la question
  existe et je ne la transforme pas en exigence.
- **Le mot *maîtresse*** reste dans un distracteur de l'item 4, et c'est le seul adulte d'ecole genre
  du paquet. **Un sur un ne fait pas une population** ; je l'avais ecarte a s32 et je l'ecarte encore.
- **La consigne de catalogue** (*"Lis bien, puis dis quelle règle de sécurité s'applique, ou pourquoi
  elle existe."*) nomme les deux taches et ne reprend pas la formule generique. **`DEC-0078` tenue,
  je ne la conteste pas.**

---

## 6. `EPS-CP-08` - `bandit-manchot-regles-jeu-collectif-eps-cp` - **REFUSE**

**`PAR-14` s'applique : sept items sur onze retouches, donc j'ai rebalaye toutes les conditions du
paquet comme pour un premier passage.** C'est ce que la clause achete, et voici ce qu'elle a rendu.

### 6.1 Les conditions, une par une, sur le paquet entier

| Condition | Etat | Ce que j'ai mesure |
|---|---|---|
| **`FER-1`** | **tenue, vacuement** | Le libelle ne porte aucune parenthese enumerative |
| **`FER-2`** | **tenue** | Onze items, plancher de dix |
| **`FER-3`** | **tenue** | Tache A - reconnaitre la regle d'un jeu nomme, items 1 a 6. Tache B - dire ce qui se passe dans une situation decrite, items 7 a 11. **Cinq au minimum, plancher de trois** (`MINIMUM_PAR_TACHE = 3`, confirme dans le code a s32) |
| **`EPS-1`** | **NON TENUE sur l'item 8** | Voir 6.3. **C'est le motif du refus** |
| **`EPS-2`** | **tenue** | Aucune comparaison de corps, de performance ni de capacite ; aucune performance chiffree |
| **`EPS-3`** | **tenue** | La tache B juge le respect **factuel** d'une regle, jamais une intention ni une conduite morale. La contrefacon que j'ai refusee d'avance sur `EPS-CP-09` n'est pas reproduite |
| **`EPS-6`** | **tenue** | *"Le loup grandit au fil de la partie"* a disparu. Aucun indice ne decrit une dynamique par une image. **Reserve voisine mais distincte : `JEU-R11`** |
| **`BIA-4`** | **tenue** | Les situations mettent en scene des personnages nommes, exterieurs a l'enfant |
| **`CAT-1`, `FAB-1c`** | **tenues** | Un seul item de la tache B se gagne encore a la forme (`JEU-R10`), sous mon seuil de deux sur cinq. **`JEU-R6` est levee** (section 4.3) |
| **`FAB-1a`** | **tenue** | Six bonnes reponses distinctes sur la tache A, cinq sur la tache B |
| **`FAB-1b`** | **tenue** | Verifie sur les vingt-deux distracteurs. Le cas limite est traite en 6.5 |
| **`PLA-1`** | **tenue** | **Amelioration nette** : les deux boutons de remplissage de s32 (*oui* / *non*) ont disparu ; les vingt-deux distracteurs sont des candidats serieux. **L'un l'est trop, et c'est `JEU-R9`** |
| **`BIN-1`** | **tenue, et amelioree** | **Zero item binaire sur onze**, contre deux a s32 |
| **`LON-1`, `LON-2`** | **tenues** | Mordues par `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` depuis `ADR-0089`, job `build` vert. **Je ne recompte pas a la main ce qu'un garde-fou compte mieux que moi**, et j'ai verifie que le plus long enonce neuf - l'item 5, quinze mots, deux phrases - est bien celui que la barre laisse passer a egalite |
| **`EXA-1`** | **NON TENUE sur l'item 8** | Voir 6.3 |

### 6.2 Ce que la reprise a paye, et que je ne redemanderai pas

| Motif de s32 | Etat | Ce que j'ai mesure a l'ecran |
|---|---|---|
| **`JEU-R1`** (erreur de regle du chat perche) | **LEVEE, et bien** | L'item 7 demande desormais l'**effet** et non la faute : *"Au chat perché, Léa touche Hugo monté sur un banc. Que se passe-t-il ?"* -> *"rien, Hugo est perché"*, avec *"Hugo est attrapé"* et *"Hugo devient chat"* en face. L'indice ne ment plus : *"Toucher un joueur perché ne change rien pour lui."* **La regle enseignee est redevenue vraie, et l'item est meilleur qu'avant** |
| **`JEU-R2`** (variante non rappelee) | **LEVEE** | *"Au jeu du loup et des moutons, un mouton touché devient loup. Que fait-il ensuite ?"* : la variante jugee est dans l'enonce. **Un enfant qui joue l'autre variante dans sa cour n'est plus declare faux** |
| **`JEU-R3`** (indice faux au premier degre) | **LEVEE** | *"Chaque mouton touché devient un loup de plus."* - mot pour mot ce que j'attendais |
| **`JEU-R4`** (item 8 sans regle en jeu) | **LEVEE a la lettre** | L'item met une regle en jeu et porte trois propositions. **La regle choisie est le probleme, et c'est `JEU-R9`** |
| **`JEU-R5`** (pronom sans antecedent) | **LEVEE** | *"À la balle au prisonnier, que devient un joueur touché ?"* - l'antecedent est dans l'enonce, les trois propositions n'ont pas bouge |
| **`JEU-R6`** (la tache B se gagne a la forme) | **LEVEE** | L'item 11 demande desormais **laquelle** des trois sanctions s'applique : *retourner au départ* contre *rester immobile pendant un tour* et *changer de place*. Reste l'item 10, sous seuil : `JEU-R10` |
| **`JEU-R7`** (regle jamais posee) | **LEVEE, et c'est le meilleur geste de la reprise** | L'item 6 enseigne desormais la regle en **bonne reponse** - *"Aux déménageurs, combien d'objets porte-t-on à chaque passage ?"* -> *"un seul objet"* - **avant** que l'item 10 ne la juge |
| **`JEU-R8`** (tournure illisible) | **LEVEE** | *"Au jeu du loup et des moutons"* |

### 6.3 Le motif du refus - **`JEU-R9`**, l'item 8

**A l'ecran** : *"À l'épervier, Malo traverse en sortant du couloir. Que se passe-t-il ?"* -
propositions *"Malo est éliminé"* (creditee), *"Malo change de rôle"*, *"Malo continue de jouer"*.
Indice : *"Le couloir marque les limites que les joueurs doivent respecter."*

**Trois faits, et chacun suffirait.**

1. **Le paquet se contredit sur le meme jeu, a cinq items d'intervalle.** L'item 3, tache A,
   **inchange depuis s32**, sert cet indice : *"Un joueur touché devient épervier à son tour."* Le
   paquet enseigne donc que le joueur pris a l'epervier **change de role**. L'item 8 credite
   **l'elimination** et **marque fausse** la proposition *"Malo change de rôle"*. **L'enfant qui a
   retenu ce que le paquet lui a appris cinq minutes plus tot est marque faux avec.** N'importe qui
   peut verifier ce point sans connaitre l'epervier : les deux items sont dans le meme fichier.
2. **`EPS-1` n'est pas tenue.** *"Sortir du couloir"* et sa sanction ne sont **rappeles dans aucun
   enonce du paquet**, et l'elimination n'est pas la forme universellement scolaire de ce jeu - dans
   la forme courante, le joueur pris rejoint les eperviers, ce qui est precisement ce que dit l'item
   3. **C'est le defaut exact de `JEU-R2`, celui que la meme reprise vient de payer sur le loup et
   les moutons, reapparu sur l'item que cette reprise reecrivait.**
3. **`EXA-1`** : la proposition marquee fausse est defendable et probablement la bonne. Ce n'est pas
   `PLA-1` - le distracteur est plausible, c'est meme le probleme - **c'est de l'exactitude, et une
   erreur factuelle dans un contenu scolaire est un motif de refus a elle seule.**

**L'indice n'arrange rien** : il dit qu'il y a une limite, jamais ce qui arrive quand on la franchit.
L'enfant qui echoue lit une phrase qui ne l'aide pas a choisir entre trois consequences.

**Ce que j'attends, et le paquet porte deja la solution.** `JEU-R7` a ete payee en faisant enseigner
la regle des demenageurs par l'item 6 de la tache A **avant** que l'item 10 de la tache B ne la juge.
**C'est le bon geste, il est dans ce diff, et il n'a pas ete applique a l'epervier.** Deux voies :

1. **L'item 3 (tache A) enseigne la limite et sa consequence** avant que l'item 8 ne la juge -
   exactement le patron des demenageurs.
2. **Ou l'item 8 credite la sanction que le paquet enseigne deja** : *"Malo est pris, il devient
   épervier"*, avec deux consequences plausibles en face.

**Aucun item a ajouter, aucune mecanique a toucher, `FER-2` et `FER-3` inchangees.**

### 6.4 Les corrections tiennent-elles ensemble comme un paquet - et non une a une

**C'est la question que `PAR-14` me fait payer, et voici la reponse : six des sept corrections
tiennent comme un paquet, une le casse.**

**Ce qui tient, et c'est du travail de paquet, pas d'item** : le chat perche est desormais coherent
**sur trois items a la fois**. L'item 2 (tache A, inchange) dit *"Un joueur touché en hauteur n'est
pas pris."* ; l'item 7 (reecrit) dit qu'un joueur perche touche ne subit rien ; l'item 9 (inchange)
dit qu'un joueur au sol touche est attrape. **Les trois disent la meme regle sous trois angles, et
l'enfant qui les rencontre dans n'importe quel ordre apprend la distinction juste.** La correction de
`JEU-R1` ne s'est pas contentee de reparer son item : elle a rendu le paquet coherent autour de lui.
Meme constat sur les demenageurs (items 6 et 10) apres `JEU-R7`.

**Ce qui le casse** : l'epervier. **La reprise a rendu les items 3 et 8 contradictoires** en ne
regardant que l'item 8. Le geste qui manquait est celui que la meme reprise venait d'inventer pour
les demenageurs. **Le defaut n'est pas dans la correction prise seule - elle repond mot pour mot a
`JEU-R4` - il est dans le fait qu'elle a ete faite item par item alors que sa regle appartient au
paquet.**

**Et l'item 10 est le second symptome de la meme chose** : les deux distracteurs que j'avais ecrits a
s32 (*"Hugo passe son tour"*, *"Hugo laisse un objet à l'arrivée"*) ont ete deposes tels quels, sans
que la bonne reponse bouge. **Une correction recopiee n'est pas une correction integree** ; voir
`JEU-R10`.

### 6.5 Les reserves non bloquantes

#### `JEU-R10` - item 10 : la bonne reponse est la seule proposition qui n'est pas une consequence

**A l'ecran** : *"Aux déménageurs, Hugo porte deux objets à la fois. Que se passe-t-il ?"* - *"Hugo
n'a pas respecté la règle"* (creditee), *"Hugo passe son tour"*, *"Hugo laisse un objet à
l'arrivée"*.

**Ce que j'avais ecrit a s32, en entier** : *"que les deux distracteurs soient eux aussi des
consequences plausibles d'une faute (...), **de sorte que l'enfant doive savoir laquelle est la
sanction du jeu**, et non seulement qu'il y en a une."* La premiere moitie est faite ; **la seconde
ne l'est pas**, parce que la bonne reponse est restee un **verdict** quand les deux autres sont
devenues des **sanctions**. **La forme designe toujours, elle a seulement change de sens** : hier la
seule sanction parmi deux recompenses, aujourd'hui le seul verdict parmi deux sanctions.

**Non bloquant** : un item sur cinq, sous mon seuil de `FAB-1c` (section 4.3), et la regle jugee est
desormais enseignee par l'item 6, donc l'enfant qui gagne cet item a bien de quoi le gagner.

**Ce que j'attends** : que la bonne reponse soit elle aussi une consequence. Le paquet le fait deja
sur l'item 11. **Et je nomme la difficulte plutot que de la laisser trouver** : les demenageurs n'ont
pas de sanction codifiee a l'ecole. Si aucune consequence juste n'est ecrivable, **l'item ne doit pas
etre reforme, il doit changer de jeu** - `1, 2, 3, soleil` en a une, et l'item 11 la sert deja.

#### `JEU-R11` - item 6 : l'indice presente une regle comme un moyen d'aller plus vite

**Indice servi** : *"Porter un seul objet permet d'aller plus vite entre les deux zones."* La regle
*un objet par passage* n'existe pas pour aller plus vite - elle existe pour que le jeu soit une
course a plusieurs voyages. **L'indice transforme une regle en tactique**, sur le paquet meme dont
l'item 10 sanctionne un enfant qui ne l'a pas respectee. Un enfant qui lit les deux peut en conclure
qu'Hugo a joue moins vite, pas qu'il a triche.

**Non bloquant** : la bonne reponse et l'enonce sont justes, et l'item 10 dit clairement que c'est une
regle. **Ce que j'attends** : *"Aux déménageurs, on ne transporte qu'un objet par voyage."*

#### `JEU-R12` - apres la reprise, le seul personnage qui enfreint une regle est un garcon, deux fois

**Constat, compte sur les onze items.** A s32 j'avais ecrit que les quatre personnages se
repartissaient les roles sans stereotype - *"une fille et un garcon enfreignent, une fille et un
garcon respectent"*. **La reprise a defait cet equilibre sans le vouloir** : l'item 7 ne contient plus
de faute (Lea ne fait plus rien de repréhensible, ce qui est la correction juste de `JEU-R1`), et il
reste **Hugo, deux fois, seul fautif du paquet** (items 10 et 11). Aucune fille n'enfreint plus rien.

**Non bloquant, et je borne ce que je dis** : deux occurrences sur onze items et quatre personnages
ne font pas une mesure. **Je l'ecris parce que j'ai deja vu ce que coute un signalement non ecrit** :
sur l'EMC CP, j'ai signale le genre de l'adulte d'ecole le 2026-09-05, le lot suivant l'a aggrave
(`REG-R3`, 17 contre 1), et cela figure aujourd'hui dans le registre de dette. **Je le nomme au
premier ecart, pas au septieme.**

**Ce que j'attends** : qu'un des deux items de faute soit porte par Aya ou Lea. **Cela ne coute qu'un
prenom.**

### 6.6 Ce que je ne transforme pas en reserve

- **Le distracteur *"Hugo est attrapé"* de l'item 7 et la bonne reponse *"Aya est attrapée"* de
  l'item 9.** J'ai regarde `FAB-1b` : elle est tenue a la lettre, et **je ne fais pas de reserve sur
  l'esprit ici, parce que l'esprit est bon** - deux items qui opposent le meme geste dans deux
  positions differentes, c'est la meilleure facon d'enseigner cette distinction. **Ecarte
  volontairement, et dit pour qu'on ne me le redemande pas.**
- **Les cinq items de la tache B posent desormais la meme question, *"Que se passe-t-il ?"*.** J'ai
  regarde si cette uniformite aidait a repondre : **non**, elle ne dit rien du contenu, et au CP une
  question stable d'un item a l'autre reduit la charge de lecture. **Je l'ecarte, et je note que
  c'est une amelioration.**
- **Le commentaire d'en-tete du fichier declare qu'aucun marqueur de restriction n'est employe** ;
  *"un seul objet"* en porte un depuis cette reprise, en bonne reponse. **Le garde-fou d'`ADR-0086`
  ne mord que dans l'autre sens** - marqueur sur tous les distracteurs et sur aucune part de la bonne
  reponse - et **il a raison de ne pas mordre ici** : l'enonce demande un nombre, les trois
  propositions sont des quantites, rien n'est designe par la forme. **Le defaut est dans le
  commentaire, pas dans le contenu** : je le route au `relecteur-pedagogique`, ce n'est pas ma porte.
- **La consigne de catalogue** (*"Lis bien, puis dis quelle est la règle du jeu, ou ce qui se passe
  dans la situation."*) nomme les deux taches. **`DEC-0078` tenue.**

---

## 7. L'ancrage au programme d'EPS du cycle 2 - verifie, pas suppose

**C'est le terrain le plus recent du produit et la question meritait d'etre reposee : ce que ces deux
paquets travaillent est-il la competence du programme, ou la description d'une activite ?**

| Ce que j'ai verifie | Resultat |
|---|---|
| **Le champ d'apprentissage** | Le programme d'EPS de cycle 2 s'organise en **quatre champs d'apprentissage**. `EPS-CP-04` est rangee dans *"Adapter ses deplacements a des environnements varies"*, `EPS-CP-08` dans *"Conduire et maitriser un affrontement individuel ou collectif"*. **Les deux paquets declarent ce domaine dans leur descripteur, et il correspond au champ.** Chacun ne declare **qu'une ligne** sous `lignesDeReferentiel` |
| **La competence, et non l'activite** | `EPS-CP-08` fait bien **connaitre la regle** - *"que devient un joueur touché ?"*, *"que se passe-t-il ?"* - et non decrire un jeu. Un item qui dirait *"a quoi joue-t-on avec un epervier ?"* serait une description d'activite ; **il n'y en a aucun.** Meme constat sur `EPS-CP-04` : la tache B demande **pourquoi** la regle existe, ce qui est plus exigeant que la reciter |
| **La competence visee, et non une voisine plus facile** | `EPS-CP-07` (*tenir un role*) et `EPS-CP-09` (*accepter le resultat*) sont **irreversiblement hors de portee** du produit, et je les ai cherchees dans les items : **aucun item ne les sert ni ne pretend les servir.** Aucune ligne n'est comptee deux fois |
| **Le repertoire des jeux** | 1, 2, 3 soleil, le chat perche, l'epervier, la balle au prisonnier, le loup et les moutons, les demenageurs : **ce sont bien les jeux traditionnels et les jeux collectifs du cycle 2**, pas des sports a reglement. **La bonne echelle pour du CP**, et je le confirme apres l'avoir joue |
| **La moitie servie, et l'autre** | **Les deux paquets ne servent que la moitie *connaissance*** de leur libelle - *connaitre* et non *respecter*, la regle et non sa mise en oeuvre. **C'est declare d'avance dans le referentiel** et ce n'est pas un defaut ; **c'est une mention due le jour ou ces lignes bougeront**, et je la redonne ci-dessous |

**La mention exigee d'avance, reprise mot pour mot, et je n'en retire pas un mot** :

> *"connaissances qui entourent la pratique physique seules ; aucune activite physique, aucun geste
> et aucune performance ne sont servis par ce produit"*

**Elle est le refus d'avance numero 7 de mon arbitrage de s33** : sans elle, je refuse la fermeture de
n'importe quelle ligne d'EPS CP, meme quand son contenu sera bon. **La question ne se pose pas
aujourd'hui, les deux lignes ne fermant pas** ; je l'ecris quand meme, pour qu'elle soit lue avant la
prochaine tentative et non redemandee apres.

**Un point que je signale a `camille-po` via `sofia-sm`, sans le trancher ici** : `EPS-CP-04` porte
une regle de securite qui, sur les items 3 et 8, releve de la **premiere education a la route** et
non d'un champ d'EPS. `EPS-5` la rattache par l'engin roulant et **je l'applique telle que je l'ai
ecrite** ; mais le referentiel gagnerait a dire que la securite routiere du pieton, trottinette ou
non, n'est pas cette ligne. **C'est une correction de mon referentiel, pas un motif contre ce lot.**

---

## 8. La classe de defaut de la section 11 du planning, mesuree sur ce terrain

**La retro de s32 a nomme deux classes ; les deux mordent ici, et pas sous la forme attendue.**

**La compression qui casse la langue.** La retro attendait des phrases cassees - une subordonnee
retiree sans reconstruire autour. **Je n'en ai trouve aucune** : j'ai relu les quatorze textes
reecrits des deux paquets, recolle chaque question a chacune de ses propositions, et **le francais
tient partout** - antecedents en place, phrases entieres, aucun referent casse. C'est un progres reel
sur s32 et je le dis.

**Mais la classe s'est reproduite un cran plus loin, et sans casser une seule phrase** :

| Ou | Ce qui a ete fait | Ce qui n'a pas suivi |
|---|---|---|
| `SEC-R5`, indices 8, 10, 11 | Le mot interdit par `EPS-7` a ete retire | **Le fait qu'il portait n'a pas ete remplace.** Les phrases sont correctes et ne disent plus rien |
| `SEC-R4`, item 3 | *"en trottinette"* a ete ajoute a l'enonce | **Les propositions sont restees celles d'un pieton**, et la regle enseignee est restee celle d'un pieton |
| `JEU-R9`, item 8 | Une vraie regle a ete mise en jeu, comme demande | **Le paquet n'a pas ete relu autour** : l'item 3 dit deja le contraire |
| `JEU-R10`, item 10 | Mes deux distracteurs ont ete recopies mot pour mot | **La bonne reponse n'a pas bouge**, donc la forme designe toujours |

**La formulation juste, pour la retro** : ce n'est pas *la compression casse la langue*, c'est **une
correction executee a la lettre de la reserve et pas a son objet**. La langue tient ; c'est le sens
qui se vide. **Un garde-fou de longueur ne verra jamais cela, et un garde-fou de francais non plus.**

**Le correctif qui reproduit la classe de defaut qu'il paie.** Elle mord, une fois, et de facon
exemplaire : **`JEU-R4` a ete corrigee en reproduisant `JEU-R2`** - une regle de jeu jugee sans
qu'aucun enonce ne la rappelle - **dans le meme fichier, dans le meme commit, quatre items plus
loin**, alors que `JEU-R2` etait payee correctement a cote. C'est le troisieme sprint consecutif ou
cette classe apparait (`CAT-1` a s32, `EMC-11` a s33 sur l'EMC, `JEU-R9` ici). **Ce n'est pas une
negligence : c'est ce qui arrive quand on corrige des items au lieu de corriger un paquet**, et c'est
pourquoi `PAR-14` fait payer un lot - moi comprise.

---

## 9. Le score, mesure une quatrieme fois en jouant

**Non demande, trouve en jouant, et deja route** (`SCO-1`, `DEC-0061`, `S33-SCO-1`).

Mesure d'aujourd'hui, methode a cote du chiffre : partie complete de dix manches sur
`bandit-manchot-regles-securite-eps-cp`, en pressant les propositions dans l'ordre affiche. **Deux
manches gagnees a la troisieme tentative, deux a la deuxieme, six a la premiere. Score final affiche
a l'enfant : 10 sur 10.** Un enfant qui savait tout et un enfant qui a elimine deux mauvaises
reponses sur deux items **recoivent le meme 10**.

**Je ne le rechiffre pas et je ne tranche pas le remede** - `theo-archi` a etabli qu'ajouter une
proposition n'est pas disponible, et le choix entre *crediter moins* et *permettre moins* est
pedagogique, donc a moi, mais il ne se tranche pas dans un verdict de paquet. **Je confirme
seulement que le defaut est toujours la, et qu'il l'est sur un terrain de plus.**

---

## 10. La condition neuve que cet audit me fait ecrire

**Une seule, et elle n'est opposable a aucun des deux increments d'aujourd'hui.**

> **`EPS-8` - nouvelle, datee du 2026-09-10. Un indice d'EPS donne un fait sur la situation que
> l'enfant ne peut pas deviner, et qui lui permet de choisir entre les propositions.** Il ne
> reformule pas l'enonce, et il ne se contente pas de ne plus nommer le mot qu'`EPS-7` ecarte.
>
> **Motif, mesure en jouant** : `EPS-7` est une condition de retrait, et elle a ete tenue par
> retrait - trois indices sur quatre ont perdu leur fait en perdant leur mot (`SEC-R5`). **Quand les
> trois propositions d'un item nomment toutes un danger, l'indice est le seul texte qui peut dire
> lequel est la vraie raison** ; s'il ne le dit pas, l'enfant qui vient d'echouer relit sa question.
> **`EPS-7` porte sur ce que l'indice ne doit pas nommer, `EPS-8` sur ce qu'il doit apporter.**
>
> **Ce n'est pas un durcissement d'`EPS-7`** : `EPS-7` a rencontre son premier contenu aujourd'hui et
> **elle est tenue**. Je complete une condition apres son premier lot, en le disant, et
> **`SEC-R5` reste non bloquante pour ce lot-ci.**

---

## 11. Ce dont je reste la seule garde sur ces deux paquets

**Dit explicitement pour que l'equipe ne croie pas couvert ce qui ne l'est pas.** Deux entrees ont
bouge depuis s32, et je les nomme.

| Propriete | Garde executable ? |
|---|---|
| **L'exactitude de chaque regle de jeu et de chaque regle de securite** | **aucun.** **C'est le motif de mes deux refus d'aujourd'hui, et les quatre jobs sont verts sur l'arbre qui les porte.** Un automate ne sait pas ce que dit l'epervier, ni ce qu'on fait d'une trottinette au bord d'une chaussee |
| **La coherence d'un paquet avec lui-meme** - deux items du meme jeu qui disent la meme regle | **aucun**, et c'est la propriete neuve la plus dangereuse de ce terrain. **Elle n'existait pas dans mes conditions avant aujourd'hui** : je l'ai trouvee en jouant, et elle est mesurable en principe - un paquet declare ses jeux - mais **je ne pose pas de barre chiffree en cours de sprint** |
| **`EPS-1`** - la regle est rappelee ou universellement scolaire | **aucun**, et il en faudrait un catalogue de variantes scolaires que personne n'a. **Deuxieme sprint consecutif ou elle est le motif d'un refus** |
| **`EPS-4`, `EPS-7`, `EPS-8`** - ni les propositions ni l'indice ne font du champ du danger le discriminant, et l'indice apporte un fait | **aucun** : jugement de registre, puis jugement de substance |
| **`FAB-1c` branche registre, `CAT-1`** | **aucun.** Elles sont **tenues aujourd'hui sur les deux paquets**, et c'est la premiere fois sur ce terrain |
| **`PLA-1`** | **aucun**, et le cas d'aujourd'hui est l'inverse de celui de s32 : le distracteur n'est plus invraisemblable, il est **defendable**, ce qui est pire et qu'aucun automate ne distingue |
| **`EPS-2`, `EPS-3`, `EPS-6`** | **aucun**, toutes tenues |
| **Le genre des personnages qui enfreignent une regle** (`JEU-R12`) | **mesurable** - un comptage de prenoms sur les items d'un paquet - **mais la population est trop petite pour une barre**, et je n'en pose pas |
| **`LON-1`, `LON-2`** | **`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`, depuis `ADR-0089`.** Second sprint ou je ne recompte pas a la main. **Ma barre est necessaire, jamais suffisante** : elle n'a rien vu de `SEC-R4`, qui est un defaut a dix mots |
| **`FER-1`, `FER-2`, `FER-3`, `BIN-1`, `FAB-1a`, `FAB-1b`** | **aucun sur ces deux paquets** : les tests de paquet a la `PaquetCm1EmcDroitDevoirTest` **n'existent toujours pas pour l'EPS**. **Je les ai comptes a la main pour la deuxieme fois, et c'est toujours une mauvaise facon de depenser une porte pedagogique** - route a `theo-archi` a s32, non paye |

---

## 12. La porte technique sur mon propre diff - les quatre jobs, nommes un par un

**Je ne deduis aucun job d'un autre et je ne confonds pas la CI avec `mvn verify`** (`CLAUDE.md`,
amendement du 2026-09-08). Mon diff est documentaire : trois fichiers de `squad/` et le registre
d'identifiants.

| Job de `.github/workflows/ci.yml` | Ce que j'ai lance | Resultat |
|---|---|---|
| `build` | `mvn -B clean verify` sur la racine, reacteur complet a six modules, par l'enveloppe de toolchain | **vert** - `BUILD SUCCESS`, **1443 tests** (somme de l'attribut `tests` sur les 193 fichiers `TEST-*.xml` de `surefire-reports` et `failsafe-reports`), 0 echec, 0 erreur, 0 ignore. **JDK 21.0.10 rapporte par la machine virtuelle des tests**, et non lu dans l'entree de toolchain (`ADR-0071`) |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh`, puis `bash .github/scripts/verifier-chemins.sh` | **vert** - *"Tous les cas passent : le garde-fou de chemins mord, et sur le bon motif."*, puis *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | les trois motifs du job (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`), avec la regle du balayage vide | **vert** - **9 juges purs executes**, aucun motif vide |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis les messages de `1740807e..HEAD` passes a `verifier-prefixes.sh` | **vert** - *"Tous les commits portent un prefixe d'agent connu."* |

**Deux choses que ce job a trouvees et que je n'aurais pas devinees.** Elles sont la raison pour
laquelle on lance la commande de la CI sur son propre diff avant de commiter.

1. **`build` a d'abord ete rouge, sur le garde-fou d'`ADR-0085` clause 5** : le registre declarait
   `594` et sa table en portait `601`. **Les instances A et B ont alloue six identifiants chacune
   dans le meme arbre que moi**, et non sur des branches separees : la limite structurelle de `PAR-5`
   s'est manifestee au poste et non a la fusion. **Reparation arithmetique**, faite et datee dans le
   registre - recompte sous le motif du garde-fou, **601 / 97, zero doublon**, aucune allocation
   ajoutee ni retiree, **mon apport reste sept**.
2. **`build` a ensuite ete rouge une fois de plus, et pour une raison qui n'a rien a voir avec
   l'arbre** : `JarRecreoIT` a echoue sur *"Port 51482 was already in use"*. **Un echec de build cause
   par une collision de port entre instances n'est pas un Done technique rouge**, et il aurait pu
   etre lu comme tel par qui n'aurait vu que la ligne `BUILD FAILURE`. Je l'ecris pour que personne
   n'ait a le deduire ; la mesure verte ci-dessus est celle de l'execution suivante, sur le meme
   arbre et sans aucune modification entre les deux.

---

## 13. Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`EPS-CP-04` et `EPS-CP-08` restent `non couvert`** | `camille-po` | Les deux lignes ne passent pas a `couvert-valide`. **Je ne touche pas la matrice** |
| **`SEC-R4`** (motif bloquant) | `camille-po`, via `sofia-sm` | Deux items sur onze. **Puis rejeu par moi** |
| **`JEU-R9`** (motif bloquant) | `camille-po`, via `sofia-sm` | Un item sur onze. **Puis rejeu par moi** |
| **`SEC-R5`, `JEU-R10`, `JEU-R11`, `JEU-R12`** (reserves) | `camille-po`, via `sofia-sm` | A traiter dans la meme reprise, les deux paquets etant de toute facon rouverts |
| **L'exemple *"traverser en trottinette"* du referentiel d'EPS CP** | moi-meme | Correction dans le prochain diff de `squad/programme/education-physique-et-sportive/competences-cp.md`. **C'est mon fichier et c'est ma faute** |
| **La frontiere entre `EPS-CP-04` et la premiere education a la route** | `camille-po`, via `sofia-sm`, et moi | A ecrire dans le referentiel, **pas opposable retroactivement a ce lot** |
| **Le commentaire d'en-tete de `cp-eps-regles-jeu-collectif.yaml`** (`ADR-0086`, marqueur de restriction) | `relecteur-pedagogique` | Le commentaire affirme une propriete que le contenu ne porte plus. **Ce n'est ni le contenu de l'enfant ni ma porte** |
| **`EPS-8`** | toute l'equipe, pour le prochain lot d'EPS | Condition opposable, allouee au registre dans ce meme diff |
| **Un test de paquet pour les deux paquets d'EPS** | `theo-archi`, via `sofia-sm` | `FER-2`, `FER-3`, `BIN-1`, `FAB-1a`, `FAB-1b` sont mordables. **Route a s32, non paye, recompte a la main aujourd'hui** |
| **`PAR-14` s'applique aussi a `S33-EPS-SEC`** (section 4.4) | `camille-po` et `sofia-sm` | Correction de chiffrage, a reprendre a la cloture. **La correction est de moi et elle est contre l'equipe** |
| **`SCO-1`** | `theo-archi` pour le remede, moi pour le choix pedagogique | Quatrieme mesure, section 9. **Rien de neuf a decider ici** |

---

## 14. Le cout de cet audit contre mon budget

**Instance C, allocation de 4,0** (planning, section 2 ; `DEC-0085`, section 3).

| Poste | Prix planifie | Prix reel | Motif |
|---|---|---|---|
| Reprise `EPS-CP-04` (`S33-EPS-SEC`) | 0,5 | **1,0** | `PAR-14` s'applique : six items sur onze touches (section 4.4). **Correction de mon propre chiffrage** |
| Reprise `EPS-CP-08` (`S33-EPS-JEU`) | 1,0 | **1,0** | Deja au tarif de lot. Sept items sur onze, et non six ; le tarif ne change pas |
| Lot de l'effort et du corps `EPS-CP-10` / `-11` | 1,5 | **0,0** | **Non livre - aucune story ecrite** (planning, section 10). Je n'audite pas ce qui n'existe pas |
| Reserves du lot, soldees dans le sprint | 0,5 | **0,0** | Sans objet, le lot n'existant pas |
| Marge `PAR-11` | 0,5 | **0,0** | Non engagee |
| **Total** | **4,0** | **2,0** | **2,0 non depenses sur mon terrain** |

**Lignes fermees par l'instance C : zero, sur quatre visees.** L'ecart n'est pas du a une barre que
j'aurais relevee : **deux des quatre lignes n'ont jamais recu de story**, et les deux autres sont
refusees sur des defauts que la reprise a introduits.

---

## 15. Les identifiants alloues dans ce meme diff (`PAR-5`)

**Sept identifiants neufs, tous sur des prefixes deja alloues, donc aucun prefixe neuf.**

| Identifiant | Ce qu'il designe | Section |
|---|---|---|
| `SEC-R4` | Items 3 et 8 de `EPS-CP-04` : la situation met l'enfant sur sa trottinette, les propositions le decrivent a pied, et la reponse creditee n'est pas la regle qui s'applique. **Bloquant** | 5.2 |
| `SEC-R5` | Trois des quatre indices reecrits de la tache B n'apportent plus de fait a l'enfant qui vient d'echouer | 5.3 |
| `JEU-R9` | Item 8 de `EPS-CP-08` : la sanction creditee de l'epervier contredit celle que l'item 3 enseigne, n'est rappelee par aucun enonce, et marque fausse une reponse defendable. **Bloquant** | 6.3 |
| `JEU-R10` | Item 10 : la bonne reponse est le seul verdict parmi deux sanctions ; la forme designe encore | 6.5 |
| `JEU-R11` | Item 6 : l'indice presente la regle *un objet par passage* comme un moyen d'aller plus vite | 6.5 |
| `JEU-R12` | Apres la reprise, le seul personnage qui enfreint une regle est un garcon, deux fois | 6.5 |
| `EPS-8` | Un indice d'EPS apporte un fait qui permet de choisir ; `EPS-7` dit ce qu'il ne nomme pas, `EPS-8` ce qu'il apporte | 10 |

**Methode du comptage, a cote de son chiffre** : comptage des lignes de la table de la section 4 du
registre sous le motif du garde-fou lui-meme (`ALLOCATION`, identifiant et document entre accents
graves, `ADR-0085` clause 5), **avant** mon diff, sur l'arbre `b45e5927` - **582 identifiants
distincts, 97 prefixes distincts, 0 doublon**, ce qui confirme la ligne d'etat courant du fichier.
Plus mes sept allocations : **589 / 97**. Ligne d'etat courant portee de **582 / 97 a 589 / 97**.

**Ce que ce total est et ce qu'il n'est pas, et ce qui s'est passe pendant que je l'ecrivais.** Il
etait juste **sur l'arbre que j'ai lu**, et il a cesse de l'etre avant que je ne lance la CI : **les
instances A et B ont alloue six identifiants chacune dans le meme arbre que moi**, ce qui a rendu
`build` rouge sur la clause 5 d'`ADR-0085` (declaration `594`, table `601`). C'est la limite
structurelle de `PAR-5` que `theo-archi` a choisi de laisser nommee plutot que d'encoder, **survenue
au poste plutot qu'a la fusion parce que les trois instances partagent une copie de travail**.

**Reparation faite, arithmetique, datee dans le registre** : recompte sous le motif du garde-fou
apres les trois lots - **601 identifiants distincts, 97 prefixes distincts, 0 doublon** - et ligne
d'etat courant portee a **601 / 97**. **Aucune allocation ajoutee, aucune retiree, aucun sens
touche, et mon apport reste sept.** Si une instance alloue encore apres cette ligne, `build`
rougira a son tour et elle la refera : c'est exactement ce que la clause 5 est faite pour produire,
et **c'est le garde-fou qui verifie ce chiffre, pas moi.**
