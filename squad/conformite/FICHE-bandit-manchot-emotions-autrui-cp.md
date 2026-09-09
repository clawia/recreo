# Fiche de conformite - Ce que ressent l'autre (`bandit-manchot-emotions-autrui-cp`)

- **Mecanique** : `bandit-manchot`
- **Contenu(s)** : `cp-emc-emotions-autrui` (11 items)
- **Statut global** : **VALIDE SOUS RESERVE** (`EMC-CP-01` **fermee le 2026-09-07** ; **etat des
  reserves au 2026-09-09** : `EMO-R1` **levee**, `EMO-R2` **levee**, `EMO-R3` **ouverte**, non
  bloquante - bloc de constat en fin de fiche. **La ligne n'est pas rouverte**)
- **Derniere mise a jour** : 2026-09-09

> **Fiche ouverte par `nadia-prof` et non par `camille-po`.** Le paquet est neuf, sa fiche n'existait
> pas au moment de l'audit. J'ai rempli les sections descriptives depuis la story `S31-1` du backlog
> et depuis le referentiel, sans rien y ajouter de mon cru ; **le bloc de validation est le mien,
> comme toujours**. Si `camille-po` veut reprendre la main sur les sections qui lui reviennent, elle
> le peut : elles ne portent aucun jugement.

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| Enseignement moral et civique | CP (cycle 2) | Respecter autrui | Identifier et nommer les emotions d'autrui dans une situation simple (joie, tristesse, colere, peur) | `EMC-CP-01`, `squad/programme/enseignement-moral-et-civique/competences-cp.md` |

## Criteres pedagogiques attendus - camille-po

Repris de `docs/backlog/backlog.md`, story `S31-1`.

- [x] Onze items au moins, `nombreDeManches: 10` (`DEC-0064`)
- [x] Les quatre emotions de la parenthese chacune en bonne reponse au moins une fois (`FER-1`)
- [x] Deux taches distinctes, la moins servie a trois items au moins (`FER-3`)
- [x] Chaque situation n'admet qu'une emotion evidente (`EMC-2`, `EMC-5`)
- [x] Aucun item n'interroge l'enfant sur lui-meme (`EMC-1`), aucun ne lui fait declarer un
      comportement (`EMC-8`), aucun ne nomme de personne reelle (`EMC-10`)
- [x] La bonne reponse ne se reconnait pas a son ton (`CAT-1`)
- [x] Chaque proposition est un candidat serieux (`PLA-1`)
- [x] Pas de bonne reponse invariante ni de distracteurs pris dans le vivier de la tache (`FAB-1`)

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **VALIDE SOUS RESERVE**
- **Date** : 2026-09-07
- **Audit joue** : **oui.** Jar reconstruit depuis mon worktree `s31-nadia-A` a `3181b5e6`,
  application lancee sur le port 8082. **Deux parties completes de dix manches** : une avec deux
  erreurs volontaires par manche, une avec **trois erreurs sur les dix manches**, donc l'ecran de
  revelation atteint dix fois sur dix. Les onze items ont ete vus, dix par partie, l'item non tire
  differant d'une partie a l'autre.
- **Audit degrade sur un point, et je le declare** : j'ai joue par requetes HTTP reelles, donc j'ai
  lu le **HTML servi**, pas la page rendue par un navigateur. Cela couvre les textes, l'ordre des
  ecrans, l'enchainement apres une erreur et apres trois, et le tirage. **Cela ne couvre ni la
  couleur, ni le contraste, ni la taille, ni la position a l'ecran**, et aucune conclusion de cette
  fiche n'en depend.

### Ce qui a ete verifie

- **Ancrage au programme** : la competence annoncee est bien celle travaillee. Le libelle demande
  *"identifier et nommer les emotions d'autrui dans une situation simple"* ; les six premiers items
  font inferer l'emotion d'un evenement decrit a un personnage nomme, les cinq suivants font lire un
  signe corporel. **Ce n'est pas une competence voisine plus facile** : nommer une emotion a partir
  d'un corps qui la montre est precisement la seconde moitie du libelle.
  **`FER-1` tenue** : *joie* (items 2, 6, 9), *tristesse* (1, 5, 8), *colere* (4, 7, 11), *peur*
  (3, 10). Les quatre elements de la parenthese sont chacun en bonne reponse.
  **`FER-2` tenue** : onze items enumeres, aucun n'est deflate par `FAB-1a` (voir ci-dessous), la
  ligne est servie par ce seul paquet.
  **`FER-3` tenue** : deux operations mentales distinctes - inferer d'un evenement (6 items),
  lire un signe corporel (5 items). La moins servie en porte cinq, au-dessus du plancher de trois.
  **C'est la seconde tache que je disais ne pas voir le 2026-09-05** et que j'ai nommee moi-meme le
  2026-09-07 ; elle est ici, et elle est bien deux operations et non une phrase a nom variable
  (`FER-3`, precision du 2026-09-03).
- **Lisibilite au niveau vise** : **c'est le point faible du paquet, et il porte ma reserve
  `EMO-R1`.** Mesure mecanique sur les onze enonces : de 13 a **19 mots**. Les deux paquets d'EMC CP
  que j'ai fermes le 2026-09-05 plafonnaient a **15 et 14 mots**. Deux enonces passent au-dessus :
  l'item 4 (18 mots, une relative plus *"venait de finir"*) et l'item 5 (**19 mots**, deux
  subordonnees enchainees par *"et que"*, plus un futur *"ne pourront plus"*). Le reste du paquet
  tient sous ou a 17.
- **Charge cognitive** : basse et constante sur les deux taches. L'enfant tient une situation et
  choisit parmi trois mots qu'il connait. Les trois propositions sont toujours du meme moule
  (*"de la ..."*), ce qui laisse toute la lecture a l'enonce. **La regularite de la forme est un
  atout ici, exactement comme sur `cp-qlm-vivant-non-vivant` a s27, et je la maintiens.**
- **Progression** : la difficulte ne monte pas d'un item a l'autre a l'interieur d'une tache, et
  c'est acceptable : la tache B (lire un signe corporel) est plus abstraite que la tache A, et
  l'ordre du paquet les place dans cet ordre. Le tirage etant aleatoire, la progression n'est de
  toute facon pas garantie a l'ecran - ce n'est pas propre a ce paquet.
- **Traitement de l'erreur** : verifie a l'ecran. Apres une erreur, l'indice s'affiche et le
  reessai reste ouvert ; apres trois erreurs, la bonne reponse est nommee et l'enfant peut avancer.
  **Aucun enfant ne reste bloque.** Les indices de la tache A font ce qu'ils doivent faire (ils
  reformulent l'evenement en termes de perte, d'obtention, de destruction). **Ceux de la tache B
  posent un probleme de vocabulaire, et c'est `EMO-R2`** : *"Ses yeux et sa **posture** se
  ferment"* (item 8), *"Son travail est detruit **volontairement**"* (item 4), *"ses gestes
  deviennent **brusques**"* (item 11). L'indice est le texte que lit l'enfant **qui vient
  d'echouer** : il doit etre plus accessible que l'enonce, pas moins.
- **Exactitude du contenu** : verifiee item par item. Les onze appariements situation/emotion sont
  justes, et aucune situation n'admet deux emotions defendables parmi les trois proposees
  (`EMC-2`, `EMC-5`) : sur l'item 4 (*"renverser expres la tour de cubes"*), *joie* et *peur* ne
  sont pas defendables ; sur l'item 7 (*"serre les poings et crie tres fort"*), *"serre les
  poings"* leve a lui seul l'ambiguite que *"crie tres fort"* aurait pu porter avec la joie. **J'ai
  cherche l'item a deux reponses, qui est le risque propre de cette ligne que j'avais nomme le
  2026-09-03 ; je ne l'ai pas trouve.**
- **`CAT-1` - tenue, et c'est la premiere fois qu'elle rencontre du contenu reel.** Les trois
  propositions de chaque item sont trois noms d'emotion. Aucune n'est polie ni grossiere ; ce qui
  les distingue est ce que la situation ou le corps decrit, jamais un registre. **Un enfant qui ne
  sait pas lire une emotion ne gagne aucun item par le ton.**
- **`PLA-1` - tenue.** Les emotions sont une categorie fermee enseignee ; chaque proposition
  appartient a la categorie. C'est la premiere branche de `PLA-1` (*"une categorie qu'on lui a
  enseignee"*), satisfaite sans discussion.
- **`FAB-1` - tenue sur ses trois branches.** (a) la bonne reponse varie a l'interieur de chaque
  tache - tache A : tristesse, joie, peur, colere, tristesse, joie ; tache B : colere, tristesse,
  joie, peur, colere. **Aucune tache n'est deflatee**, les onze items comptent. (b) le vivier est
  celui d'un classement a categories fermees, ce que `FAB-1` autorise explicitement, et la bonne
  reponse varie. (c) aucune amorce, aucune longueur ni aucun registre ne distingue la bonne
  reponse : les trois propositions sont *"de la X"* sur les onze items.
- **`BIN-1` - sans objet** : la ligne n'est pas un classement a deux categories, et les onze items
  portent trois propositions.
- **Biais et ton** : aucun stereotype de genre, d'origine ou de milieu. Les cinq personnages
  (Lea, Hugo, Aya, Malo, Tom) portent des emotions sans repartition genree - la colere echoit a Tom,
  a Malo et a Lea ; la peur a Aya et a Malo ; la tristesse a Lea, Tom et Aya. **`EMC-1` tenue** :
  aucun item n'interroge l'enfant sur lui-meme. **`EMC-6` : j'ai examine l'item 10 (*"Malo se cache
  derriere sa maman"*) et je ne le retiens pas.** La condition interdit de *presupposer* une
  configuration familiale ; ici elle est **dans l'enonce**, attribuee a un personnage tiers, ce que
  `EMC-6` autorise en toutes lettres (*"toute donnee de contexte est dans l'enonce ou n'existe
  pas"*). **Je le dis pour qu'on ne me le redemande pas.**

### Motif

**Le paquet travaille la competence annoncee, et il la travaille entiere.** Les conditions d'EMC
applicables a cette ligne (`EMC-1`, `EMC-2`, `EMC-5`, `EMC-6`, `EMC-8`, `EMC-10`), les trois
conditions de fermeture (`FER-1`, `FER-2`, `FER-3`) et les quatre clauses que j'ai posees le matin
meme (`CAT-1`, `FAB-1`, `PLA-1`, `BIN-1`) sont tenues, y compris `CAT-1`, qui est la plus dangereuse
de la matiere et que ce paquet est le premier a rencontrer.

**Ce qui empeche le VALIDE sec est de lisibilite, pas de fond** : deux enonces sur onze depassent la
longueur maximale des deux paquets d'EMC CP deja fermes, et trois indices de la tache B emploient un
vocabulaire au-dessus du CP - au moment exact ou l'enfant en a le plus besoin, c'est-a-dire apres
son erreur. **Ni l'un ni l'autre n'empeche de jouer, ni n'enseigne quoi que ce soit de faux.**

**La ligne `EMC-CP-01` ferme.**

### Reserves

#### `EMO-R1` - deux enonces au-dessus de la longueur des paquets d'EMC CP deja fermes

**Ouverte le 2026-09-07, non bloquante.**

- item 4 : *"Malo voit son petit frere renverser expres la tour de cubes qu'il venait de finir. Que
  ressent-il ?"* - **18 mots**, une relative et un passe recent.
- item 5 : *"Tom apprend que son meilleur ami demenage tres loin et qu'ils ne pourront plus jouer
  ensemble. Que ressent-il ?"* - **19 mots**, deux subordonnees enchainees par *"et que"*, un futur.

**Ce n'est pas une barre chiffree que je pose ici, et je le dis pour ne pas deplacer mes poteaux.**
Je n'ai jamais ecrit de plafond de mots pour l'EMC CP. Le chiffre que je cite est **observe** : les
deux paquets d'EMC CP que j'ai fermes le 2026-09-05 plafonnent a 15 et 14 mots, et je m'appuie sur
ce que j'ai deja accepte, pas sur ce que je decouvre aujourd'hui.

> **Propriete a tenir** : ces deux enonces disent la meme situation en une proposition principale et
> sans futur. Un enfant de CP lit peu et lentement ; deux subordonnees enchainees lui coutent plus
> que l'inference qu'on lui demande. La formulation appartient au developpeur.

**La question du plafond chiffre pour le CP est routee a mon propre arbitrage prealable de s32**,
pas posee ici : mes clauses se posent avant un sprint, jamais pendant.

#### `EMO-R2` - trois indices de la tache B emploient un vocabulaire au-dessus du CP

**Ouverte le 2026-09-07, non bloquante.**

- item 8 : *"Ses yeux et sa **posture** se ferment, comme pour se proteger."*
- item 4 : *"Son travail est **detruit volontairement** par quelqu'un d'autre."*
- item 11 : *"Son visage et ses gestes deviennent **brusques** d'un coup."*

*posture*, *volontairement* et *brusques* ne sont pas du lexique du CP. Et *"ses yeux se ferment"*
au sens figure, sur un item qui vient de dire *"les larmes aux yeux"* au sens propre, demande une
lecture que cette classe n'a pas.

**Le motif n'est pas le style, c'est le moment** : l'indice ne s'affiche qu'apres une erreur. Il est
lu par l'enfant qui vient d'echouer, donc par celui qui lit le moins bien. **Un indice moins
accessible que son enonce n'aide personne.**

> **Propriete a tenir** : chaque indice de ce paquet s'ecrit avec les mots de l'enonce qu'il
> eclaire, ou avec des mots plus simples - jamais avec des mots plus savants. La formulation
> appartient au developpeur.

**Regime des deux reserves** : **rejeu borne, 0,5 pour le paquet entier** (`PAR-10`). Elles voyagent
ensemble ; aucune ne se paie separement.

### Points que je ne transforme pas en reserve, et je le dis pour qu'on ne me les redemande pas

- **L'item 10 nomme *"sa maman"***. Traite ci-dessus : `EMC-6` est tenue, la donnee est dans
  l'enonce et attribuee a un personnage tiers.
- **Les situations de la tache A sont tres typees** (doudou perdu, cadeau recu, gros chien). C'est
  ce que `EMC-2` exige : *"un item sur une emotion ne retient que les situations a emotion unique et
  evidente"*. **On ne peut pas me demander l'evidence et me reprocher la typicite.**
- **La consigne du catalogue** (*"Lis bien, puis dis ce que ressent la personne, ou ce que montre
  son geste."*) nomme les deux taches et tient sur une ligne. Elle est juste sur le paquet servi.
  Je ne la conteste pas.

### Mentions de couverture partielle

**Aucune n'est due sur cette ligne.** Le libelle enumere quatre emotions ; les quatre sont servies
en position de bonne reponse. Les deux verbes du libelle - *identifier* et *nommer* - sont tous deux
servis, le second par le geste meme de choisir le mot. **La ligne ferme sans reserve de perimetre,
et je n'en ajoute pas une apres coup.**

### Mentions de non-couverture opposables

> **`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire defiler la page, un element
> quelconque du jeu, y compris le retour qui suit sa reponse - verdict, indice, proposition. Cette
> propriete est sortie de la porte pedagogique par arbitrage de l'utilisatrice du 2026-08-28
> (`DEC-0053`) ; sa dette est ouverte, croissante et assumee. Aucune mesure ne l'a instruite sur ce
> paquet.

### Ce dont je reste la seule garde sur ce paquet

**Dit explicitement pour que l'equipe ne croie pas couvert ce qui ne l'est pas.**

| Propriete | Garde executable ? |
|---|---|
| **`CAT-1`** - la bonne reponse ne se reconnait pas a son ton | **aucun**, et c'est un jugement de registre qu'aucun automate ne rend |
| **`EMC-2`, `EMC-5`** - aucune situation a deux emotions defendables | **aucun** |
| **`PLA-1`** - chaque proposition est un candidat serieux | **aucun** |
| **La justesse de chaque appariement situation / emotion** | **aucun** |
| **`EMO-R2`** - qu'un indice ne soit pas moins lisible que son enonce | **aucun**, et la partie mesurable (longueur, mots longs) serait mordable si une barre chiffree existait pour le CP. Elle n'existe pas ; je ne la cree pas en cours de sprint |
| **`FAB-1a`, `FAB-1b`, `FAB-1c`, `BIN-1`** | **aucun a ce jour**, et ce sont les quatre que j'ai routees a `theo-archi` le 2026-09-07. **Sur ce paquet, c'est donc moi qui les ai verifiees a la main** |

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`EMC-CP-01` : `non couvert` -> `couvert-valide`**, sans mention de couverture partielle | `camille-po` | Le compte de la matrice lui appartient ; je ne la touche pas |
| **`EMO-R1` et `EMO-R2`** | `camille-po`, via `sofia-sm` | En faire une story. Regime : **rejeu borne, 0,5 pour le paquet entier** (`PAR-10`) |
| **Un plafond chiffre de longueur d'enonce au CP** | moi-meme, arbitrage prealable de s32 | Trois paquets d'EMC CP livres et aucune barre ecrite : c'est a moi de la poser avant un sprint, pas pendant |


---

## Bloc de constat de paiement - 2026-09-09 - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. **Il s'ajoute au bloc du
> 2026-09-07 et ne le remplace pas.** Il porte sur le correctif `S32-4` de `lea-dev` (`ba2871ea`),
> et sur sa reprise `c0c62792`.

- **Verdict** : **VALIDE SOUS RESERVE - maintenu.** **`EMC-CP-01` reste `couvert-valide` et n'est pas
  rouverte.**
- **`EMO-R1` : LEVEE. `EMO-R2` : LEVEE.** Les deux reserves de ce paquet sont payees, dans les
  termes exacts ou je les avais ecrites.
- **`EMO-R3` : ouverte**, datee du 2026-09-09, non bloquante - **et elle nait d'une erreur de mon
  propre comptage, pas d'un manquement du correctif.**
- **Date** : 2026-09-09.
- **Arbre audite** : `round-two` a `fd19d18f`, worktree dedie `s32-nadia-b`.
- **Audit joue** : **oui, une partie complete de dix manches**, erreurs volontaires jusqu'a
  epuisement des propositions sur chaque manche dont la premiere reponse n'etait pas la bonne, donc
  **tous les indices lus a l'ecran** - ce qui est exactement le moment que `EMO-R2` visait. Jar
  reconstruit depuis mon worktree, JDK de la toolchain 21 (21.0.10 rapporte par la machine
  virtuelle), port local. Le tirage m'a servi **dix items sur onze**. **Releve integral dans
  `squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/verdicts-nadia-instance-B.md`,
  section 6.**
- **Audit degrade sur un point, declare** : lecture du **HTML servi**, pas de la page rendue par un
  navigateur.
- **Porte technique** : **les quatre jobs de la CI lances sur mon diff et nommes un par un** -
  `prefixes`, `chemins`, `outils`, `build`. Chiffres dans mon releve, section 9.

### `EMO-R1` est levee

| Item | Ce que la reserve nommait | Ce que l'ecran sert aujourd'hui | Mots |
|---|---|---|---|
| 4 | *"Malo voit son petit frere renverser expres la tour de cubes **qu'il venait de finir**."* - relative, passe recent | *"Malo voit sa tour de cubes renversee expres par son petit frere."* | **14** |
| 5 | *"...demenage tres loin **et qu'ils ne pourront plus jouer ensemble**."* - deux subordonnees enchainees, un futur | *"Tom apprend que son meilleur ami demenage tres loin."* | **11** |

**La propriete que j'avais posee** - *"ces deux enonces disent la meme situation en une proposition
principale et sans futur"* - **est tenue** : la relative est partie, la seconde subordonnee et le
futur aussi, le fait rapporte et la bonne reponse sont intacts. **Les deux enonces sont sous
`LON-1`.**

**Et je dois dire que l'item 4 a ete corrige deux fois.** La premiere reecriture disait *"renverser
**sa** tour de cubes"*, ou *sa* pouvait se rattacher au petit frere aussi bien qu'a Malo.
**`lea-dev` l'a trouve elle-meme en relisant**, apres que `relecteur-pedagogique` a trouve le
partitif casse sur un autre paquet, et elle a nomme la cause commune : une subordonnee retiree sans
reconstruire la phrase autour. **C'est ma barre qui a produit ces trois defauts, et c'est le
paragraphe de mon arbitrage prealable qui disait qu'elle ne suffirait pas.** Je ne l'aurais pas vu
avant elle sur ce paquet-la : je jouais, et le tirage m'a servi l'item 4 en derniere manche.

### `EMO-R2` est levee

| Item | Indice condamne | Indice servi aujourd'hui |
|---|---|---|
| 4 | *"Son travail est **detruit volontairement** par quelqu'un d'autre."* | *"Son travail est casse expres par quelqu'un d'autre."* |
| 8 | *"Ses yeux et sa **posture** se ferment, comme pour se proteger."* | *"Elle a du mal a retenir ses larmes, et n'ose pas regarder les autres."* |
| 11 | *"Son visage et ses gestes deviennent **brusques** d'un coup."* | *"Son corps bouge fort et vite, tout d'un coup."* |

**Les trois mots savants sont partis, aucun n'est remplace par un autre du meme rang, et l'indice de
l'item 8 cesse de demander une lecture figuree** - c'etait le fond de ma reserve, pas seulement le
lexique. **Lu a l'ecran apres erreur volontaire sur les trois items.** Aucun indice ne nomme
l'emotion attendue, ce que j'avais aussi verifie au premier passage.

**Un point que j'ai regarde et que je ne transforme pas en reserve, pour qu'on ne me le redemande
pas** : l'indice de l'item 7 dit *"Son corps **se raidit** et sa voix devient forte d'un coup."*
*Raide* est a la portee d'un CP, ce que *posture*, *volontairement* et *brusques* n'etaient pas, et
l'enonce que cet indice eclaire (*"Tom serre les poings et crie tres fort"*) le porte. **Je le nomme
parce que la propriete de `EMO-R2` est generale a tous les indices du paquet et qu'un lecteur pourrait
me l'opposer : je l'ai jugee, elle est tenue.**

### `EMO-R3` - l'item 6 fait 16 mots et porte un pronom relatif que le CP n'a pas

**Ouverte le 2026-09-09, non bloquante.**

*"Lea gagne la partie **a laquelle** elle joue avec ses amis dans la cour. Que ressent-elle ?"* -
**16 mots**, un mot au-dessus de `LON-1`.

**Ce n'est pas un manquement du correctif, c'est une erreur de mon comptage, et elle m'a ete rendue
avant que je juge.** Ma reserve `EMO-R1` nommait **deux** enonces trop longs ; **il y en avait
trois**, et l'item 6 etait compte parmi les neuf que je declarais conformes. `lea-dev` l'a confirme
par son propre script reproduisant ma convention, et le garde-fou d'`ADR-0089` porte ce reste comme
une dette declaree de **1** enonce sur ce paquet. **J'ai recompte les onze enonces moi-meme avant
d'ecrire ceci** : de 11 a 16 mots, un seul au-dessus de la barre, c'est celui-la.

**Et le mot en trop n'est pas le pire.** *A laquelle* est un relatif compose : c'est la construction
la plus lourde des onze enonces du paquet, plus lourde que *"qu'il venait de finir"* que j'ai fait
retirer de l'item 4. **La longueur est ce qui se mesure ; la relative est ce qui coute a l'enfant.**

> **Propriete a tenir** : cet enonce dit la meme situation sans pronom relatif compose, et sous
> `LON-1`. La formulation appartient au developpeur. **Et elle se reconstruit autour du retrait, pas
> par soustraction** - c'est la lecon des trois referents casses de ce sprint, et elle vaut d'abord
> pour les enonces que ma propre barre fait raccourcir.

**Regime** : **rejeu borne, 0,5 pour le paquet entier** (`PAR-10`). Un seul enonce est en cause.

### Ce que j'ai verifie et qui ne fait pas reserve

- **`FER-1`, `FER-2`, `FER-3`** : quatre emotions en position de bonne reponse, onze items, deux
  taches (nommer l'emotion d'une situation, lire l'emotion d'un signe corporel). Tenues a
  l'identique, recomptees.
- **`LON-2`** : les onze enonces comptent deux phrases, la seconde etant toujours la question. Zero
  faute.
- **`EMC-1`** : aucun item n'interroge l'enfant sur ce qu'il ressent lui-meme. Tenue.
- **`EMC-5`** : aucun item a deux emotions defendables. Reverifiee sur les onze, y compris sur les
  deux enonces reecrits - *"Malo voit sa tour renversee expres"* laisse la colere seule defendable,
  *"son meilleur ami demenage tres loin"* laisse la tristesse seule.
- **`CAT-1`** : sans objet sur ce paquet. Nommer une emotion n'oppose aucun registre poli a un
  registre fautif ; les quatre propositions sont les quatre emotions du libelle.

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`EMC-CP-01` reste `couvert-valide`** | `camille-po` | **Aucun mouvement de matrice.** La mention de couverture partielle du 2026-09-07 reste due et inchangee |
| **`EMO-R3`** | `camille-po`, via `sofia-sm` | En faire une story, avec les deux autres restes de longueur du meme jour (`EXC-R6`, `POL-R3`) : **les trois sont le meme defaut sur trois paquets, et ils sont deja portes par la dette declaree d'`ADR-0089`** |
| **La dette de longueur d'`ADR-0089` nomme `EMO-R1` pour ce paquet** | `theo-archi` | **Constat, pas demande** : `EMO-R1` est levee et l'enonce que la dette compte est celui de `EMO-R3`. Le chiffre reste juste - **1** -, c'est son etiquette qui a change de reserve. Le cliquet mord toujours ; il nomme desormais la mauvaise reserve dans sa Javadoc |
