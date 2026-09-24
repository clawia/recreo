# Fiche de conformite - bandit-manchot-matiere-objets-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-qlm-matiere.yaml`
  (11 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-matiere-objets-cp`, titre servi *"La matiere des
  objets"*, consigne servie *"Lis bien, puis dis en quelle matiere est fait l'objet, ou trie les
  matieres selon leurs proprietes."*, `nombreDeManches: 10`.
- **Ligne declaree** : `QLM-CP-06`.
- **Statut global** : **VALIDE SOUS RESERVE** (2026-09-24, s42, `nadia-prof` instance A). Quatre
  reserves neuves et datees - `MOB-R1` a `MOB-R4` -, aucune bloquante. Voir le bloc de validation
  pedagogique en bas de fiche. Fiche ouverte par `camille-po` a l'ouverture du bloc de construction
  du sprint s42 (paquet et catalogue livres et fusionnes dans `round-two`, commit `cdf0229f`,
  contenu au commit `cbe35d0b`).
- **Story** : `S42-QLM-MATIERE` - Identifier de quelle matiere un objet est fait et trier des
  materiaux selon une propriete simple (`docs/backlog/backlog.md`).
- **Competence programme visee** : questionner le monde, CP (cycle 2), domaine "Le vivant, la
  matiere et les objets" - `squad/programme/questionner-le-monde/competences-cp.md`, `QLM-CP-06`.
  Ligne `non couvert` dans `squad/programme/MATRICE-couverture.md` (ligne 1564) au moment
  d'ouverture de cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage
  neuf a router** (`ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, section 7.1,
  ligne 6) : *"6 matiere d'un objet | oui, choix ; plus juste en illustre | Le nom de l'objet dans
  l'enonce donne souvent la matiere ('une cuillere en bois'). Distracteurs a surveiller."*
- **Lien decision** : `DEC-0098`.
- **Derniere mise a jour** : 2026-09-24 (verdict pedagogique, `nadia-prof` instance A).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S42-QLM-MATIERE`)

- Etant donne un item, quand il s'affiche, alors il demande d'identifier la matiere d'un objet
  (bois, metal, plastique, verre, tissu...) ou de trier des materiaux selon une propriete simple
  (dur/mou, qui flotte/qui coule...), sur le patron du libelle.
- Etant donne l'enonce d'un item, quand on le relit, alors le nom de l'objet ne revele pas la
  matiere attendue avant reponse (vigilance posee d'avance par `nadia-prof` : le nom de l'objet
  donne souvent la matiere, par exemple "une cuillere en bois").
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres materiaux reellement plausibles
  pour l'objet decrit, jamais des elements sans rapport.
- Etant donne le paquet livre, quand on le relit dans son entier, alors plusieurs materiaux
  distincts (au moins bois, metal, plastique, verre) sont chacun en bonne reponse au moins une
  fois.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `QLM-CP-06` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` (instance A) le joue, alors
  elle confirme qu'aucun enonce ne revele la matiere par le nom de l'objet et que les distracteurs
  sont des materiaux plausibles.

## Ce que cette story ne fait pas

Elle ne demande jamais a l'enfant de manipuler ou de toucher un materiau reel - hors de portee du
palier 1 ; elle ne fixe aucun contenu factuel precis (quels objets, quels materiaux retenus) -
reste a l'auteur du paquet puis a l'audit de `nadia-prof`.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **VALIDE SOUS RESERVE**
- **Date** : 2026-09-24 (s42)
- **Auteur** : `nadia-prof`, **instance A** (`DEC-0098`, terrain 1)
- **Arbre** : `round-two` a `9817b4f0`
- **Audit joue** : **oui.** Jar reconstruit depuis cet arbre, lance sur le JDK `21.0.10` de la
  toolchain. **Une partie complete de 10 manches**, chaque manche jouee en pressant les
  propositions dans l'ordre affiche jusqu'a la bonne, pour lire ce que l'enfant lit apres une
  erreur, apres deux erreurs, et a l'ecran de fin. Consigne, enonces, propositions, indices et
  messages lus **a l'ecran**, pas dans le YAML - le YAML n'a servi qu'a verifier ensuite ce que
  l'ecran m'avait montre.
- **Mouvement de matrice** : `QLM-CP-06` peut passer a `couvert-valide`. `camille-po` decide,
  pas moi.

### 1. Ce que j'avais pose d'avance, et qui est tenu

L'arbitrage prealable que la story cite portait **une seule vigilance**
(`ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, section 7.1, ligne 6) : *"Le nom
de l'objet dans l'enonce donne souvent la matiere ('une cuillere en bois'). Distracteurs a
surveiller."*

**Tenue, verifiee item par item sur les onze.** Aucun enonce ne nomme la matiere de l'objet qu'il
decrit : *fenetres*, *clous*, *porte peinte*, *pull*, *seau de plage*, *casserole*. Pas une
"cuillere en bois". L'enfant doit reellement savoir de quoi la chose est faite.

**Les distracteurs sont des materiaux, jamais des elements sans rapport** - et ce sont des
materiaux que l'objet decrit pourrait plausiblement avoir. Le seau de plage contre *metal* et
*verre*, la casserole contre *plastique* et *bois* : plausibles pour un enfant de CP, faux pour
l'objet decrit. Un seul cas m'arrete, et c'est `MOB-R4` ci-dessous.

**Les cinq materiaux sont chacun en bonne reponse** : verre (items 1 et 9), metal (2, 6, 8), bois
(3, 7), tissu (4, 10), plastique (5, 11). Le critere n'en demandait que quatre.

**Les deux taches sont bien deux taches.** Identifier la matiere d'un objet (manches sur les items
1 a 6), trier des materiaux selon une propriete (items 7 a 11). Je les ai vues alterner a l'ecran
dans la meme partie, et l'enfant sent la difference.

**Les indices sont exacts et ils separent les trois propositions.** *"On voit au travers, et elle
se casse si on la laisse tomber."* ne designe ni le tissu ni le metal. *"Elle est lourde, comme une
piece de monnaie qui tombe au fond."* ne designe ni le bois ni le plastique. C'est ce que je verifie
en pressant les mauvaises propositions d'abord, et c'est ce que j'ai fait sur les dix manches.

**Exactitude** : rien de faux. Le bois flotte, le metal coule, le verre casse, le tissu est doux,
le plastique reste leger. Ce sont les proprietes que le CP travaille, au niveau ou il les travaille.

**Traitement de l'erreur** : *"Ce n'est pas ca. Essaie encore !"* suivi de l'indice, a chaque
tentative fausse. L'enfant n'est jamais bloque, et il ne recoit pas un simple *"faux"*. Mecanique
deja validee ailleurs, elle se comporte ici comme attendu.

**`ADR-0023`** : 11 items pour 10 manches. Marge de 1, comme une trentaine d'entrees deja au
catalogue. **Je ne fais pas de cette marge une reserve** : ce serait une barre neuve appliquee a
un paquet seul, et je ne releve pas une barre apres coup.

**`QLM-CP-06` et elle seule** : le paquet ne declare que cette ligne.

### 2. Les quatre reserves, neuves et datees du 2026-09-24, **aucune bloquante**

#### `MOB-R1` - le mot *proprietes* est dans la consigne que l'enfant de CP lit

**Consigne servie, lue a l'ecran** : *"Lis bien, puis dis en quelle matiere est fait l'objet, ou
trie les matieres selon leurs proprietes."*

**Le mot *proprietes* n'est pas au vocabulaire du CP dans ce sens-la.** Un enfant de six ans
connait *"propre"*, il ne connait pas *"une propriete"* comme caracteristique d'un materiau. C'est
le mot du referentiel, ecrit pour un adulte : le libelle `QLM-CP-06` dit *"trier des materiaux
selon une propriete simple"*, et ce libelle a ete recopie dans la phrase que l'enfant lit.

**Mesure** : j'ai cherche ce mot dans tout le texte servi a l'enfant par le catalogue. **Il y
apparait une seule fois, ici.** Les autres consignes de CP disent la chose en mots concrets -
*"dis si l'eau est solide ou liquide"*, *"dis ce que ressent la personne"*. Celle-ci ne le fait pas.

**Ce que je ne reproche pas** : la longueur. 17 mots, et j'ai deja valide des consignes de CP a 17
et a 18 mots (`bandit-manchot-exclusion-cp`, `bandit-manchot-regles-jeu-collectif-eps-cp`).
**Relever la barre de longueur aujourd'hui ferait de ma porte un caprice.** La reserve porte sur
**un mot**, pas sur le nombre de mots.

> **`MOB-R1`.**
> - **Objet** : que la seconde moitie de la consigne dise la tache en mots de CP, sans le terme
>   *proprietes*.
> - **Mode de defaillance a eviter** : remplacer *proprietes* par une formule qui ne dirait plus
>   quelle tache l'enfant fait. La consigne doit continuer a annoncer les **deux** taches du paquet,
>   c'est ce que `CONS-4` exige.
> - **Perimetre** : la consigne de cette entree de catalogue, et rien d'autre. Aucun item ne bouge.
> - **Non bloquante.**

#### `MOB-R2` - *"reste legere meme en grand objet"* n'est pas une phrase que lit un CP

**Item 11, lu a l'ecran** : *"Parmi ces trois matieres, laquelle reste legere meme en grand objet ?"*

**Deux choses a la fois, et aucune des deux n'est au niveau.** La tournure *"meme en grand objet"*
n'est pas du francais courant - on dirait *"meme quand l'objet est grand"* -, et le raisonnement
demande n'est pas celui d'un CP : il faut comprendre qu'une propriete se conserve quand la taille
change. Les dix autres items du paquet demandent une propriete directe et observable (flotte,
coule, casse, doux). **Celui-ci est le seul qui demande une comparaison a taille variable, et il est
aussi le seul dont la formulation m'arrete.**

L'enfant trouvera quand meme, par elimination ou grace a l'indice (*"On l'utilise pour les jouets et
les bidons, car il ne pese pas lourd."*, qui est bon). **Il trouvera sans avoir lu la question.**
C'est pour cela que la reserve n'est pas bloquante, et c'est aussi pour cela qu'elle doit etre payee.

> **`MOB-R2`.**
> - **Objet** : que l'enonce de l'item 11 demande une propriete du plastique lisible par un CP, dans
>   une phrase de francais courant.
> - **Mode de defaillance a eviter** : tomber sur une propriete deja servie par un autre item
>   (*flotte*, *casse pas*) - le paquet perdrait une de ses cinq proprietes distinctes.
> - **Perimetre** : l'enonce de l'item 11. La bonne reponse, les deux distracteurs et l'indice ne
>   bougent pas.
> - **Non bloquante.**

#### `MOB-R3` - *"Une casserole qui va sur le feu."* n'a pas de verbe

**Item 6, lu a l'ecran** : *"Une casserole qui va sur le feu. En quelle matiere est-elle souvent
faite ?"*

La premiere phrase n'a pas de verbe principal : c'est un groupe nominal suivi d'un point. **Les dix
autres items du paquet ouvrent tous sur une phrase complete** - *"Les clous sont durs et brillent un
peu."*, *"Un pull tient bien chaud en hiver."* Un enfant qui apprend a lire s'appuie sur la
regularite de ce qu'on lui donne a lire ; celui-ci rompt la sienne, au milieu de son propre paquet.

**Je ne compte pas cela comme une faute d'orthographe ni de ponctuation** - ce controle-la est
systematique et appartient au `relecteur-pedagogique` (`DEC-0002`, amendement du 2026-08-04). Je le
compte comme **une phrase qui ne se lit pas comme les autres au CP**, et cela, c'est ma porte.

> **`MOB-R3`.**
> - **Objet** : que la premiere phrase de l'item 6 soit une phrase complete, sur le patron des dix
>   autres items du paquet.
> - **Perimetre** : la premiere phrase de l'enonce de l'item 6, et rien d'autre.
> - **Non bloquante.**

#### `MOB-R4` - une porte peinte en metal existe, et *metal* est un de ses distracteurs

**Item 3, lu a l'ecran** : *"Une porte peinte est souvent lourde et solide. En quelle matiere
est-elle faite ?"*, contre *metal* et *verre*.

**Une porte d'entree en metal est peinte, lourde et solide.** L'enfant qui repond *metal* a raison
sur le monde et tort sur la reponse attendue. C'est la famille de `HG-R20`, que j'ai datee du
2026-09-11 et gradee non bloquante : je la grade pareil ici.

**Un detail aggrave le cas et le rend facile a corriger** : les items 4, 5 et 6 posent leur question
avec *"souvent"* (*"En quelle matiere est-il souvent fait ?"*), l'item 3 ne l'a que dans sa premiere
phrase, ou il qualifie *lourde et solide* et non la matiere. **La question de l'item 3 affirme donc
une matiere unique pour toutes les portes peintes.** L'indice, lui, tranche bien (*"On peut la
clouer et la peindre de toutes les couleurs."*), mais il n'arrive qu'apres une erreur.

> **`MOB-R4`.**
> - **Objet** : que l'item 3 cesse d'avoir deux reponses defendables - soit en decrivant un objet
>   de bois que le metal ne dispute pas, soit en portant *"souvent"* dans sa question comme les
>   items 4, 5 et 6.
> - **Mode de defaillance a eviter** : nommer le bois, ou un mot qui l'appelle, dans l'enonce - la
>   vigilance de l'arbitrage prealable tomberait.
> - **Perimetre** : l'enonce de l'item 3. Les propositions ne bougent pas.
> - **Non bloquante.**

### 3. Ce dont je reste la seule garde sur ce paquet

Je le nomme pour que personne ne croie couvert ce qui ne l'est pas.

| Propriete | Garde executable ? |
|---|---|
| **Qu'un enonce ne revele pas la matiere par le nom de l'objet** | **aucun.** Aucun test ne sait lire un enonce. C'est moi, item par item |
| **Qu'un distracteur ne soit pas defendable** (`MOB-R4`) | **aucun.** Un garde-fou verifie qu'il y a trois propositions distinctes, pas qu'une seule est vraie |
| **Que le vocabulaire d'une consigne soit au niveau du CP** (`MOB-R1`) | **aucun.** Le comptage de mots existe, le niveau de chaque mot n'existe pas |
| **Qu'une phrase servie a un CP se lise** (`MOB-R2`, `MOB-R3`) | **aucun.** `LON-1` et `LON-2` comptent les mots et les phrases, et les onze enonces les passent. Un enonce de 12 mots illisible passe vert |

**Ce dernier point est le plus important de cette fiche pour l'equipe** : les quatre reserves
ci-dessus vivent toutes dans des enonces qui respectent `LON-1` et `LON-2`. **Le reacteur est vert
sur ce paquet, et il le serait encore si les quatre defauts etaient quatre fois pires.**
