# Fiche de conformite - bandit-manchot-grands-nombres-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : contenu neuf a creer (`type: question`, au moins 24 items), story `S13-1`
  (`docs/backlog/backlog.md`). Aucune matiere premiere de `contenu-repris/` identifiee pour cette
  ligne - le proxy de transcription chiffres/lettres qui la rend servable n'a jamais ete cable sur
  ce produit.
- **Statut global** : **REFUSE une seconde fois** par `nadia-prof` le 2026-08-16, sur un motif neuf
  et un seul. **Les deux motifs du refus du 2026-08-15 sont leves** : le verbe "decomposer" est
  desormais reellement travaille, et les deux indices faux sur `600 040` sont exacts. Le motif neuf
  est de rendu : a 360 px, treize items sur vingt-huit affichent un groupe de chiffres **hors de sa
  colonne**, sur le fond sombre du cabinet ou hors de l'ecran, et le nombre que l'enfant lit n'est
  plus le nombre ecrit - `1 000 000` se lit `000 000`. Detail, mesures et reserves dans le bloc de
  validation. Ce refus renvoie l'increment a `camille-po`, qui en fait du backlog ; **il ne bloque
  pas le sprint suivant**.
  Historique des verdicts, aucun efface : **REFUSE le 2026-08-15** (verbe "decomposer" non
  travaille, deux indices faux), **REFUSE le 2026-08-16** (debordement horizontal a 360 px).
- **Arbitrage prealable rendu avant l'ouverture de cette fiche** :
  `squad/conformite/ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`, section 1 - `nadia-prof` a
  **accepte**, sans reserve de principe, le proxy de transcription entre l'ecriture chiffree et
  l'ecriture en toutes lettres d'un nombre, pour les verbes "lire" et "ecrire", sous deux conditions
  (GN-1, GN-2) reprises au mot pres dans les criteres d'acceptation de S13-1. Les verbes "decomposer"
  et "comparer" sont servables sans aucun proxy. **Cette ligne peut ouvrir en entier**, sans mention
  de couverture partielle.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, branche `s10-nadia-mecanique` et audits suivants) :
  conditions M1 a M13, dont deux ont ete bloquantes pour d'autres jeux du catalogue - M4 (le message
  d'erreur doit etre vu sans defiler a 360 x 640) et la clause 1 de M13 (apres une reponse, la
  tabulation ne detruit jamais la partie sans confirmation). Rien n'est mesure ici a l'ouverture de la
  fiche, faute d'increment a mesurer.
- **Derniere mise a jour** : 2026-08-16 (second Done pedagogique REFUSE par `nadia-prof`, apres
  avoir rejoue le lot de reprise)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CM1 (cycle 3) | Nombres et calculs | Lire, ecrire, decomposer et comparer les nombres entiers jusqu'au million | `squad/programme/mathematiques/competences-cm1.md` |

**Perimetre exact, assume a l'ouverture** : les quatre verbes du referentiel (lire, ecrire,
decomposer, comparer) sont tous vises par ce paquet, sur le proxy accepte par `nadia-prof` pour
"lire" et "ecrire" (l'ecriture en toutes lettres tient lieu de designation orale, ce produit n'ayant
et n'aurant aucune modalite audio). Un Done pedagogique accorde sur un paquet qui couvre les quatre
verbes dans les proportions posees ci-dessous ferme cette ligne en entier, sans reste connu a ce
jour.

## Criteres pedagogiques attendus - camille-po

- [ ] Dans le sens chiffres vers lettres, chaque distracteur differe de la bonne reponse par la
  valeur qu'il designe (classe ou position confondue), jamais par la seule orthographe des
  mots-nombres (GN-1).
- [ ] Dans le sens lettres vers chiffres, la designation litterale de chaque enonce est ecrite sans
  faute, sans ambiguite, et de facon constante dans tout le paquet (GN-2).
- [ ] Chaque item "decomposer" propose un nombre entier jusqu'au million et demande sa decomposition
  exacte en valeurs de position.
- [ ] Chaque item "comparer" propose deux nombres entiers distincts jusqu'au million et demande le
  signe exact parmi <, > et =.
- [ ] Les quatre verbes (lire, ecrire, decomposer, comparer) sont chacun representes par au moins un
  quart du paquet.
- [ ] Aucune proposition n'est egale a une autre dans le meme item.
- [ ] Le paquet compte au moins 24 items, avec au moins 6 par verbe.
- [ ] Chaque item porte un indice non vide, exact et comprehensible par un enfant de CM1, qui ne
  paraphrase pas l'enonce.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue.

### Done pedagogique du 2026-08-15 - REFUSE (S13-1, paquet `cm1-grands-nombres.yaml`)

- **Verdict** : **REFUSE**. Aucun Done pedagogique. La ligne mathematiques / CM1 / "Lire, ecrire,
  decomposer et comparer les nombres entiers jusqu'au million" **ne passe pas** a `couvert-valide`
  et reste ou elle est. Ce refus renvoie l'increment a `camille-po`, qui en fait du backlog ; **il
  ne bloque pas le sprint suivant**.
- **Auteur** : nadia-prof. **Increment audite** : branche `round-two`, tete `c8766cd`, arbre propre,
  **483 tests, 28 regles**. Mon propre lot est sur `s13-nadia-audit` et n'ecrit que de la
  documentation et un releve : **483 tests et 28 regles ne bougent pas de mon fait**.
- **Mode** : **j'ai joue**, ce n'est pas un audit degrade. Prerequis : un JDK 21 (21.0.10 ici) ;
  `mvn -B verify` vert avant toute mesure, jar reconstruit depuis mon propre worktree, serveur lance
  sur un port a moi. **Un premier echec reellement joue sur chacun des 28 items**, plus des parties
  completes de dix manches, des manches justes, des manches menees jusqu'a la revelation au
  troisieme essai. Rendu mesure a 360 x 640 par `outils/mesurer-rendu.sh`, et a 720 px pour savoir si
  ce que je vois tient a la largeur.
- **Releves commites** : `releves-s13/releve-grands-nombres-pli-360x640.json` (les 28 items, position
  au pixel du message et de l'indice), `releves-s13/grands-nombres-decomposer-360x640.png`,
  `releves-s13/grands-nombres-decomposer-720x1000.png`,
  `releves-s13/grands-nombres-lire-360x640.png`.
- **Conditions de reference** : **GN-1 et GN-2** de mon
  `squad/conformite/ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`, section 1, et les neuf criteres
  d'acceptation de `camille-po` ci-dessus.

#### 1. Ce qui est acquis, et que je ne rediscute pas

Je le pose avant les motifs, parce qu'un refus qui ne dit pas ce qui est tenu ne se corrige pas.

- **Exactitude : 28 bonnes reponses sur 28 justes.** Recalculees une a une, y compris les sept
  decompositions et les sept comparaisons. Aucune erreur de fait dans une bonne reponse.
- **GN-2 est tenue, et l'orthographe des mots-nombres est irreprochable.** J'ai relu les **28
  designations litterales** du paquet - 7 enonces "ecrire", 7 bonnes reponses "lire", 14
  distracteurs "lire". Le "s" de "cent" est juste partout : *"quarante-sept mille trois cents"* et
  *"deux cent treize mille cinq cents"* le portent parce que "cents" termine le nombre, *"huit cent
  seize mille deux cent trois"*, *"six cent mille quarante"* et *"quatre cent cinquante-deux mille"*
  ne le portent pas parce qu'un numeral suit. *"quatre-vingt-dix"* et *"quatre-vingt-seize"* n'ont
  pas de "s" a "vingt", pour la meme raison. Sur ce paquet, l'orthographe **est** le contenu, et
  elle est juste.
- **GN-1 est tenue dans les deux sens.** Aucun distracteur "lire" ou "ecrire" ne se distingue de la
  bonne reponse par la seule orthographe : les 42 propositions de ces quatorze items designent
  quatorze fois trois valeurs deux a deux differentes. Ce sont bien des erreurs de classe et de
  position, celles que j'avais nommees.
- **Les quatre verbes sont la, a parts egales** : sept items chacun, exactement un quart, la ou
  `camille-po` demandait au moins un quart et au moins six.
- **Les 28 indices sont non vides, et 24 sur 28 sont utiles.** Ils ne paraphrasent pas l'enonce et
  disent une methode, pas une reponse - *"Repere le mot 'mille' : tout ce qui precede donne les
  milliers"*, *"Un million, c'est mille fois mille."* Les quatre autres sont traites plus bas.
- **La revelation au troisieme essai fonctionne sur les quatre verbes**, et l'enfant bloque avance.
  Sur un item "comparer" elle donne *"C'est >. Appuie sur >."* : cela se lit mal, mais
  `cp-comparer-nombres` est en production avec exactement cette forme, elle est couverte par la
  mecanique et **je ne l'impute pas a ce lot**.

#### 2. Motif 1 (bloquant) - le verbe "decomposer" n'est pas travaille par les sept items qui le portent

C'est le motif principal, et il a deux moities qui se renforcent. La fiche dit qu'un Done accorde
ici ferme la ligne **en entier**, les quatre verbes ; je ne peux pas fermer un verbe que l'enfant ne
fait pas.

**Moitie A - les sept items ne font varier que leur premier terme.** Mesure sur le paquet :

| enonce | bonne reponse | ce qui change dans les distracteurs |
|---|---|---|
| `40 705` | `40 000 + 700 + 5` | `4 000`, `20 000` - le premier terme seul |
| `305 421` | `300 000 + 5 000 + 400 + 20 + 1` | `600 000`, `900 000` - le premier terme seul |
| `128 000` | `100 000 + 20 000 + 8 000` | `300 000`, `900 000` - le premier terme seul |
| `600 040` | `600 000 + 40` | `60 000`, `900 000` - le premier terme seul |
| `90 090` | `90 000 + 90` | `9 000`, `80 000` - le premier terme seul |
| `452 000` | `400 000 + 50 000 + 2 000` | `700 000`, `900 000` - le premier terme seul |
| `8 004` | `8 000 + 4` | `800`, `4 000` - le premier terme seul |

**Sept fois sur sept, tout ce qui suit le premier terme est identique dans les trois propositions.**
La suite de la decomposition - celle qui porte les centaines, les dizaines, les unites, et le zero
intercalaire qui est le coeur de la difficulte - n'est jamais en jeu. L'enfant n'a jamais a la lire.

**Moitie B - sur trois items, les deux distracteurs ne sont pas des erreurs.** `305 421` se voit
oppose `600 000` et `900 000` ; `128 000` se voit oppose `300 000` et `900 000` ; `452 000` se voit
oppose `700 000` et `900 000`. **Aucun enfant ne decompose 305 421 en commencant par 600 000** : ce
n'est pas une erreur de rang, ni de classe, ni de zero oublie, c'est un chiffre remplace par un
autre. Sur ces trois items, il suffit de regarder le **chiffre le plus a gauche de l'enonce** et de
retrouver la colonne qui commence par lui. C'est un appariement de chiffre, pas une decomposition.
Les quatre autres items s'en tirent mieux : `4 000` contre `40 000`, `60 000` contre `600 000`,
`9 000` contre `90 000`, `800` contre `8 000` sont de vraies erreurs de rang, plausibles et
instructives.

**Moitie C - a l'ecran, la decomposition n'est plus lisible, et ce n'est pas un probleme de petit
telephone.** Les milliers sont separes par une espace ordinaire, qui est un point de coupure de
ligne. Dans une colonne de proposition, `900 000 + 5 000 + 400 + 20 + 1` se rend sur **neuf lignes**
et un CM1 lit :

```
900
000
+ 5
000
+
400
+
20
+ 1
```

`900 000` est coupe en `900` et `000`, `5 000` en `+ 5` et `000`. Ce que l'enfant a sous les yeux
n'est plus une somme de valeurs de position, c'est une colonne de nombres empiles ou la coupure du
separateur et le signe `+` ne se distinguent plus. **Et ce n'est pas la faute de 360 px : a 720 px,
soit le double, la coupure est toujours la** (`releves-s13/grands-nombres-decomposer-720x1000.png`,
`300 / 000 + 5 / 000 + / 400 + / 20 + 1`). Trois colonnes cote a cote ne tiennent pas une
decomposition de vingt-neuf caracteres sur une ligne, a aucune largeur d'ecran realiste.

**Les trois moities disent la meme chose** : sur le seul verbe du paquet qui demande de manipuler la
valeur de position, l'enfant lit la premiere ligne d'une colonne et rien d'autre - et c'est
exactement la seule chose que les items font varier.

**Ce que je demande, et je ne prescris pas la solution technique** : que les distracteurs d'un item
"decomposer" portent une erreur de decomposition **ailleurs que sur le premier terme** (un rang
confondu a l'interieur du nombre, un zero avale, un terme manquant), et que la decomposition se lise
d'un bloc a l'ecran, sans qu'un nombre soit coupe en deux. Le second point est une question de
rendu, pas de contenu : je decris le symptome et je laisse `theo-archi` traiter la cause.

#### 3. Motif 2 (bloquant) - deux indices sont faux sur `600 040`

Une erreur de fait dans un contenu scolaire est un motif de refus a elle seule, et un indice est du
contenu : c'est le texte que l'enfant lit **au moment ou il est en difficulte**.

- Item `Ecris en chiffres : six cent mille quarante.` - indice : *"Il n'y a ni dizaine ni centaine de
  mille ici : seulement des centaines de mille et des unites."*
- Item `Decompose 600 040 en valeurs de position.` - indice : *"Il n'y a ni centaine ni dizaine de
  mille ici : seulement des centaines de mille et des unites."*

Les deux se contredisent dans la meme phrase : elles nient les "centaines de mille" puis affirment
qu'il n'y a "que" des centaines de mille - or 600 040 en a six. Et les deux nomment `40` "des
unites", alors que ce sont **quatre dizaines** - ce que la bonne reponse de l'item "decomposer" dit
elle-meme, puisqu'elle s'ecrit `600 000 + 40`. Le rang reellement absent de 600 040, c'est la
**dizaine de mille**, l'unite de mille, la centaine et l'unite. Un enfant qui vient d'echouer et qui
lit cette phrase apprend une chose fausse sur le nombre qu'il a devant lui.

#### 4. Reserves - ecrites, datees, non bloquantes

Elles ne fondent pas le refus et je ne veux pas qu'on les lise comme telles. Elles deviennent des
stories pour `camille-po` **au moment ou les deux motifs sont repris**, pas avant.

- **Reserve A (2026-08-15) - a 360 x 640, l'indice est sous le pli sur 28 items sur 28, et le message
  sur 18 sur 28.** Mesure au pixel sur un premier echec joue pour chacun des 28 items
  (`releves-s13/releve-grands-nombres-pli-360x640.json`) : le bas du message depasse le pli de
  **+26 a +362 px** sur 18 items, le bas de l'indice de **+14 a +476 px** sur les 28. Pire cas :
  `Decompose 305 421 en valeurs de position.`, message a 1 002 px et indice a 1 116 px pour un pli a
  640. **Ce n'est pas un motif de refus** : M4 ne bloque plus depuis `DEC-0025`, ou l'utilisatrice a
  assume par ecrit le defaut de pli, et je ne la ressuscite pas comme condition bloquante sur un lot
  qui n'en etait pas averti - c'est le meme traitement que la reserve 2 de
  `FICHE-bandit-manchot-perimetre-cm1.md`. Mais le fait est neuf et il est **plus lourd que sur
  aucun jeu du catalogue** : `cm1-perimetre`, le pire jusqu'ici, depassait de 27 a 75 px pour le
  message et de 111 a 159 px pour l'indice. Ici c'est jusqu'a cinq fois plus, et la cause est
  nommee : les propositions occupent la hauteur que la coupure de ligne leur fait prendre.
- **Reserve B (2026-08-15) - le signe `=` n'est jamais la bonne reponse, sur 7 items "comparer" sur
  7.** Le critere de `camille-po` demande deux nombres **distincts**, donc `=` ne peut jamais etre
  juste par construction. Une proposition sur trois est morte sur la totalite du bloc, et un enfant
  qui a joue trois manches le sait : il choisit entre deux, pas entre trois. Ce n'est pas grave sur
  sept items, mais c'est la meme forme que ce que j'ai refuse sur les tables le 2026-08-14 - une
  regle qui elimine une proposition sans lire l'enonce. Une piste qui ne coute rien : un item du
  type `Compare 40 000 + 700 + 5 et 40 705.` a `=` pour reponse juste, travaille la comparaison et
  la decomposition ensemble, et rend la troisieme proposition vivante. C'est une piste, pas une
  condition ; `camille-po` decide.
- **Reserve C (2026-08-15) - la coupure de ligne des nombres deborde du verbe "decomposer".** Sur les
  sept items "ecrire", les 21 propositions chiffrees sont coupees a leur separateur de milliers :
  `40 705` se lit `40 / 705`, `1 000 000` se lit `1 / 000 / 000`. Sur deux enonces "comparer" -
  `Compare 999 999 et 1 000 000.` et `Compare 70 018 et 78 000.` - c'est l'enonce lui-meme qui coupe
  le nombre en deux lignes. Sur les sept items "lire", les designations litterales se rendent **un
  mot par ligne**, sept lignes de haut (`releves-s13/grands-nombres-lire-360x640.png`) : c'est
  lisible, mais lent et laid. **Un effet mesure de cette coupure** : sur `Ecris en chiffres : un
  million.`, la bonne reponse `1 000 000` est la seule colonne sur **trois** lignes, contre deux et
  une pour les distracteurs - un enfant qui ne sait rien presse la colonne la plus haute et gagne.
  C'est un item sur 28, pas un taux, mais c'est un indice de forme qui ne devrait pas exister. Je
  compte cette reserve a part du motif 1 parce que, sur "ecrire" et "comparer", la coupure gene la
  lecture sans la fausser : les groupes de chiffres restent dans l'ordre. Sur "decomposer" elle la
  fausse, et c'est pour cela qu'elle est dans le motif et pas ici.
- **Reserve D (2026-08-15) - deux distracteurs "ecrire" ne sont pas des erreurs plausibles.** `3 521`
  face a *"trois cent cinq mille quatre cent vingt-et-un"* perd le `4` et le `0` du nombre : aucune
  lecture, meme fautive, n'y mene. `820 000` face a *"cent vingt-huit mille"* ne contient pas le `1`
  du nombre. Deux sur 21 ; les dix-neuf autres sont de bonnes erreurs de classe ou de position.
- **Reserve E (2026-08-15) - trois indices sont inexacts ou inutiles.** Ils ne sont pas faux comme
  ceux du motif 2, mais ils n'aident pas. *"Un seul chiffre change de position : repere lequel."* sur
  `452 000` contre `425 000` : **deux** chiffres changent de position, le 5 et le 2. *"Repere si un
  zero se cache au milieu du nombre"* sur `213 500` : ce nombre n'a pas de zero au milieu, ses zeros
  sont a la fin. *"Compare d'abord le nombre de chiffres"* sur `70 018` contre `78 000` : les deux en
  ont cinq, l'enfant compte et n'apprend rien.
- **Reserve F (2026-08-15) - `un million` est pose deux fois, avec les memes distracteurs.** L'item
  `Ecris en chiffres : un million.` et l'item `Lis ce nombre : 1 000 000.` sont le meme couple, dans
  les deux sens, avec les memes deux valeurs opposees (`100 000` et `10 000`). Le paquet compte donc
  28 items pour **27 designations litterales distinctes**. Un enfant qui tire les deux dans la meme
  partie repond deux fois a la meme question. C'est le pendant de la reserve A des tables, en plus
  petit.
- **Reserve G (2026-08-15) - aucun de ces nombres ne designe quoi que ce soit.** Vingt-huit items,
  pas une population, pas une distance, pas un prix. Un CM1 qui ecrit `452 000` vingt-huit fois
  apprend une technique de transcription ; il n'apprend pas a quoi sert un grand nombre. Deux ou
  trois enonces habilles - les habitants d'une ville, les kilometres d'un fleuve - suffiraient. Meme
  remarque que la reserve 3 de `FICHE-bandit-manchot-perimetre-cm1.md`, et meme statut : ce n'est
  pas un manquement a un critere, c'est un manque.

Rien a signaler sur les biais : ce paquet ne met en scene personne, donc ni stereotype de genre,
d'origine, de milieu ni de configuration familiale, et le ton n'est jamais condescendant.

#### 5. Une correction de mon propre constat, avant qu'on s'appuie dessus

- **La coupure de ligne des nombres vient en partie de ma propre condition, et je le dis.** MD-4 de
  mon arbitrage prealable s13 exige que "les milliers soient separes par une espace", et j'avais
  raison de l'exiger - `4500` n'est pas une ecriture d'ecole. Ce que je n'avais pas prevu, et que je
  ne pouvais pas voir sans jouer, c'est qu'une espace ordinaire **coupe la ligne**. Ma condition est
  maintenue ; elle est desormais incomplete telle qu'elle est ecrite, et c'est **une reserve
  nouvelle et datee de ce jour**, pas un durcissement retroactif de MD-4. `marc-dev` n'a pas devie de
  ma condition, il l'a appliquee.
- **Je ne compte pas `vingt-et-un` comme un ecart a GN-2, et j'explique pourquoi.** GN-2 demande une
  ecriture "constante dans tout le paquet, avec les traits d'union de la reforme ou sans".
  *"quatre cent vingt-et-un"* prend le trait d'union de la reforme alors que le reste du paquet
  ecrit les classes sans. Mais **c'est le seul "et" de tout le paquet** - aucun autre de ses
  vingt-sept nombres n'en contient un - donc aucune inconstance n'est visible par l'enfant, et la
  constance de GN-2 n'est pas prise en defaut. **Et cette graphie est celle de mon propre exemple**,
  ecrit de ma main dans la section 1 de l'arbitrage prealable : `marc-dev` a recopie ce que je lui
  avais donne. Pour le paquet suivant, la convention a fixer est celle de la reforme, appliquee
  partout ou nulle part ; je l'ecris ici pour qu'elle soit connue **avant**, pas apres.

#### 6. La question que `marc-dev` m'a posee - la forme d'item qui rendrait la mesure de rang aveugle

`marc-dev` signale que le garde-fou de rang lit **le premier terme** d'une decomposition et non sa
valeur, que sur son paquet les trois premiers termes sont distincts, donc que le classement est
juste aujourd'hui - et il demande si la forme d'item qui ferait varier un autre terme m'interesse.

**Elle m'interesse, et c'est meme celle que le motif 1 exige.** Les erreurs qui valent la peine
d'etre mises en distracteur sur une decomposition gardent presque toutes le premier terme intact :
`300 000 + 50 000 + 400 + 20 + 1` pour l'enfant qui lit le 5 comme une dizaine de mille,
`600 000 + 4` pour celui qui avale le zero de `600 040`, `300 000 + 5 000 + 400 + 21` pour celui qui
oublie un terme. Autrement dit : **la forme qui rend la mesure aveugle est exactement la forme
pedagogiquement juste**, et la forme qui garde la mesure exacte est celle que je refuse aujourd'hui.

Ce n'est pas un reproche a la mesure : elle ne ment pas sur ce paquet, `marc-dev` l'a verifie et je
l'ai reverifie. C'est un ordre a connaitre avant de corriger : **le jour ou le motif 1 est traite, le
garde-fou de rang devient muet sur les sept items "decomposer" sans qu'aucun diff ne le montre** -
les trois propositions auront le meme premier terme, donc la meme valeur lue, et l'item sortira du
domaine ou y restera avec un ordre faux. `ADR-0041` a ferme le cas du paquet juge sur une partie de
ses items ; il n'a pas ferme celui du **classement faux entre trois propositions de meme valeur
lue**. Je le signale a `theo-archi` par `sofia-sm`, avec le symptome et sans prescrire : la cause est
technique et elle n'est pas la mienne.

#### 7. Les proprietes dont je reste la seule garde sur ce jeu

Je les nomme pour que personne ne croie couvert ce qui ne l'est pas.

- **Les 14 items que le garde-fou de rang n'atteint pas** - les sept "lire", dont les propositions
  sont des mots, et les sept "comparer", dont les propositions sont `<`, `>`, `=`. `ADR-0041` oblige
  desormais a **declarer** cet ecart, et `marc-dev` l'a fait : le taux de 35,7 / 28,6 / 35,7 porte
  sur **14 items, pas sur 28**, et le depot le dit. Declarer n'est pas mesurer : sur ces quatorze
  items, **le rang de la bonne reponse n'est garde par personne d'autre que moi, en jouant**. Je les
  ai lus un par un ce jour ; c'est la meme situation que `cp-comparer-nombres` en production.
- **L'exactitude de chaque bonne reponse et de chaque indice**, y compris l'arithmetique des
  decompositions et des comparaisons.
- **L'orthographe des mots-nombres.** Le test de `marc-dev` prouve que deux propositions designent
  des valeurs differentes en s'appuyant sur une table qu'il a ecrite a la main : si une graphie etait
  fautive dans le YAML **et** dans la table, il resterait vert. Sur ce paquet, ou l'orthographe est
  le contenu, c'est moi le garde-fou.
- **La plausibilite de chaque distracteur** - c'est elle qui fonde la moitie B du motif 1 et la
  reserve D, et aucun automate ne la voit.
- **L'utilite reelle de chaque indice**, lue apres un echec reel et non dans le fichier.
- **Ce qu'un nombre devient quand la ligne le coupe**, tant qu'aucun garde-fou ne connait la largeur
  d'un ecran.
- **Le niveau tel qu'il se joue.**

#### 8. Ce que la matrice doit dire, et que `camille-po` ne doit pas perdre

- La ligne **ne passe pas** a `couvert-valide`. Elle reste ou elle est, et la mention "ouverte en
  entier" du perimetre de cette fiche reste vraie : c'est le paquet qui ne la couvre pas encore, pas
  le perimetre qui retrecit.
- **Les proxys GN-1 et GN-2 ne sont pas en cause et ne sont pas a rediscuter.** Le proxy de
  transcription chiffres/lettres tient exactement ce que j'avais dit qu'il tiendrait : les quatorze
  items "lire" et "ecrire" travaillent bien la lecture et l'ecriture des grands nombres. Un lot de
  reprise n'a aucune raison d'y revenir.
- **Sept des neuf criteres de `camille-po` sont tenus.** Les deux qui ne le sont pas : *"chaque item
  porte un indice non vide, exact et comprehensible"* (motif 2, et reserve E) et, pour ce qui est de
  l'intention plutot que de la lettre, *"chaque item decomposer demande sa decomposition exacte en
  valeurs de position"* - la lettre est respectee, l'exercice ne l'est pas (motif 1).

---

### Done pedagogique du 2026-08-16 - REFUSE (second verdict, lot de reprise de S13-1)

> Le verdict du 2026-08-15 ci-dessus n'est ni efface ni modifie. Cette section s'empile dessus. Un
> refus efface n'a aucune valeur de preuve, y compris quand ses motifs sont leves : c'est lui qui
> rend lisible ce que le lot de reprise a corrige.

- **Verdict** : **REFUSE**, sur **un motif neuf et un seul**. La ligne mathematiques / CM1 / "Lire,
  ecrire, decomposer et comparer les nombres entiers jusqu'au million" **ne passe pas** a
  `couvert-valide`. Ce refus renvoie l'increment a `camille-po` ; **il ne bloque pas le sprint
  suivant**.
- **Mes deux motifs du 2026-08-15 sont LEVES**, l'un et l'autre, sans reserve. Je le pose en tete
  parce que c'etait la question principale et que la reponse est nette.
- **Auteur** : nadia-prof. **Increment audite** : branche `round-two`, tete `4000bab`, arbre propre,
  **487 tests, 28 regles**, `mvn -B verify` vert rejoue depuis mon propre worktree avant toute
  mesure (47 + 7 + 227 + 120 + 80 + 6 = 487). Mon lot est sur `s13-nadia-verdict-2` et n'ecrit que
  de la documentation et des releves : **487 tests et 28 regles ne bougent pas de mon fait**.
- **Mode** : **j'ai joue**, ce n'est pas un audit degrade. Prerequis : un JDK 21 (21.0.10 ici). Jar
  reconstruit depuis mon worktree, serveur lance sur un port a moi, arrete par moi sur le PID reel
  de la JVM. **80 manches jouees a la main en echouant volontairement**, les 28 items rencontres au
  moins une fois, plus **2 800 manches tirees** pour la mesure de rang, plus des manches menees
  jusqu'a la revelation au troisieme essai. Rendu mesure a **360 px** (largeur de reference de ce
  produit depuis `DEC-0016` et M4), et verifie a **412 px** et **720 px**.
- **Releves commites ce jour** :
  `releves-s13/releve-grands-nombres-debordement-360.json` (les 28 items, largeur du jeton
  insecable le plus large et debord par cote au dixieme de pixel),
  `releves-s13/releve-grands-nombres-rang.json` (les deux mesures de rang),
  `releves-s13/grands-nombres-debordement-128000-360x1200.png`,
  `releves-s13/grands-nombres-debordement-million-360x1200.png`,
  `releves-s13/grands-nombres-debordement-million-agrandi.png`,
  `releves-s13/grands-nombres-debordement-305421-360x1200.png`.

#### 1. Motif 1 du 2026-08-15 - LEVE

**Moitie A - levee, sept items sur sept.** Les sept items "decomposer" gardent le premier terme
identique dans les trois propositions et deplacent l'erreur sur un terme suivant. C'est exactement
la forme que j'avais decrite. Recompte item par item :

| enonce | bonne reponse | ce que les distracteurs deplacent |
|---|---|---|
| `40 705` | `40 000 + 700 + 5` | `+ 70` (rang confondu), terme des unites manquant |
| `305 421` | `300 000 + 5 000 + 400 + 20 + 1` | `+ 2` a la place de `+ 20`, `+ 50 000` a la place de `+ 5 000` |
| `128 000` | `100 000 + 20 000 + 8 000` | `+ 2 000`, `+ 800` |
| `600 040` | `600 000 + 40` | `+ 4` (zero avale), `+ 400` (rang confondu) |
| `90 090` | `90 000 + 90` | `+ 9`, `+ 900` |
| `452 000` | `400 000 + 50 000 + 2 000` | `+ 5 000`, `+ 200` |
| `8 004` | `8 000 + 4` | `+ 40`, `+ 400` |

**Moitie B - levee.** Les quatorze distracteurs sont desormais quatorze erreurs reelles de valeur de
position : rang confondu, zero avale, terme manquant. Plus un seul chiffre substitue, plus un seul
item resolvable par appariement du chiffre le plus a gauche. Un enfant doit lire la decomposition
jusqu'au bout pour choisir. **C'est ce que je demandais, et c'est fait.**

**Et j'ai verifie que la correction n'a pas ouvert une autre porte** : aucun raccourci de forme n'est
apparu a la place. Sur les sept items, la bonne reponse est trois fois la valeur intermediaire, trois
fois la plus grande, une fois la plus petite ; le nombre de termes ne la designe pas non plus. Il n'y
a pas de regle a apprendre autre que decomposer.

**Moitie C (le rendu) - levee sur sa lettre, et je ne la durcis pas retroactivement.** J'avais ecrit :
*"que la decomposition se lise d'un bloc a l'ecran, sans qu'un nombre soit coupe en deux"*. Plus aucun
nombre n'est coupe en deux, ni a 360 px, ni a 720 px. La barre que j'avais posee est atteinte, donc
je la leve. Ce qui se passe a la place est un defaut **different**, que je constate ce jour et que
j'ecris comme un motif neuf et date au point 3 - pas comme un durcissement de l'ancien.

#### 2. Motif 2 du 2026-08-15 - LEVE

Les deux indices de `600 040` sont exacts, et je les ai lus a l'ecran apres un echec reellement joue,
pas dans le fichier.

- `Ecris en chiffres : six cent mille quarante.` - *"Aucune dizaine de mille, aucun millier, aucune
  centaine ni unite ici : seulement des centaines de mille et des dizaines."*
- `Decompose 600 040 en valeurs de position.` - *"Aucune dizaine de mille, aucun millier, aucune
  centaine ni unite dans ce nombre : il ne reste que des centaines de mille et des dizaines."*

La contradiction interne a disparu, et `40` n'est plus appele "des unites" mais "des dizaines", ce qui
est juste et coherent avec la bonne reponse `600 000 + 40`. **Les rangs absents sont exactement ceux
que la phrase nomme.** Motif leve.

J'ai relu les 26 autres indices avec eux : aucun n'est faux. Les trois inexacts de ma reserve E sont
toujours la, ils n'etaient pas dans le perimetre du lot, ils y restent.

#### 3. Motif neuf du 2026-08-16 (bloquant) - a 360 px, treize items affichent un nombre qui n'est pas le nombre ecrit

C'est le seul motif de ce refus, et c'est celui que je n'avais pas pu voir avant, parce qu'il n'existait
pas avant.

**Ce qui se passe.** Le separateur de milliers etant devenu insecable, `100 000` est desormais un seul
jeton que rien ne peut couper. Il mesure **119,3 px** a la police du bouton (32 px). La colonne d'une
proposition mesure **96,0 px** a 360 px de large. Le texte deborde donc de **11,6 px de chaque cote**,
et la gouttiere entre deux colonnes ne fait que **16 px** : **les textes de deux colonnes voisines se
recouvrent de 7 px**. Ce qui deborde tombe sur le fond bleu nuit du cabinet, en texte sombre : ce n'est
pas seulement dehors, c'est **illisible**.

**Ce que l'enfant lit vraiment.** Mesure sur la page rendue, capture a l'appui :

| item | ce qui est ecrit | ce que l'enfant voit a 360 px |
|---|---|---|
| `Ecris en chiffres : un million.` | `1 000 000` | `000 000`, le `1` sur le fond sombre et le dernier `0` **hors de l'ecran** (texte de 219 a 365 px pour un ecran de 360) |
| meme item | `100 000` | `00 000` |
| `Decompose 128 000...` | `100 000 + 20 000 + 8 000` | `00 000 + 20 000 + 8 00`, sur les trois colonnes a la fois |

**Sur `Ecris en chiffres : un million.`, la bonne reponse n'est pas lisible sur l'ecran.** Les trois
colonnes montrent `00 000`, `10 000` et `000 000`. Un enfant qui sait parfaitement qu'un million s'ecrit
`1 000 000` ne le trouve pas. Et quand il echoue trois fois, la revelation lui dit *"C'est 1 000 000.
Appuie sur 1 000 000."* en le renvoyant vers un bouton ou ce nombre n'est pas ecrit.

**Une erreur de fait dans un contenu scolaire est un motif de refus a elle seule.** Un nombre ampute
d'un chiffre est un autre nombre. Que la cause soit un rendu et non un YAML ne change rien pour
l'enfant : c'est ce qu'il a sous les yeux. Je decris le symptome et **je ne prescris aucune solution
technique** : la cause est technique, elle est a `theo-archi`.

**Etendue exacte, mesuree, et honnete sur ce qui n'est pas imputable a ce lot.**
`releves-s13/releve-grands-nombres-debordement-360.json` :

- **20 items sur 28** ont au moins une proposition qui deborde de sa colonne a 360 px.
- **13 le doivent au separateur de milliers**, et ces treize sont **nouveaux** : les 7 items "ecrire"
  et 6 des 7 items "decomposer". Debord par cote : 11,6 px sur dix d'entre eux, 2,5 px sur trois,
  **25,2 px sur `1 000 000`**.
- **7 le doivent a un mot francais long** (`cinquante-`, `quarante-`, `soixante-` : jusqu'a 33 px par
  cote sur `Lis ce nombre : 528 006.`). **Ceux-la debordaient deja avant ce lot, le separateur n'y est
  pour rien, et je les avais manques le 2026-08-15** - voir le point 6.
- Les 7 items "comparer" ne debordent pas.

**Ce n'est pas une exigence neuve de ma part.** Le meme defaut, a **2,01 px** de debord sur `mangeons`,
a fait l'objet de `DEC-0018` et d'une reprise au sprint 8 sur `cm1-conjugaison-present`, sur mon propre
arbitrage du 2026-08-11. Ici c'est **11,6 px**, six fois plus, et jusqu'a 25,2. La barre est celle que
j'ai deja posee, appliquee a l'identique.

**A quelle largeur cela cesse.** A **412 px** le debord tombe a 3 px par cote et les colonnes ne se
recouvrent plus (sauf `1 000 000`, 17 px). A **720 px** il n'y a plus aucun debord et **aucun groupe
n'est coupe** : la mesure de `marc-dev` est exacte et je la confirme. Le defaut est **specifique aux
petites largeurs**, et 360 px est la largeur de reference de ce produit depuis `DEC-0016`.

#### 4. Le debordement contre la coupure - lequel est pire, et ma reponse

`sofia-sm` me demande de trancher. **Le debordement est pire, et nettement.** Ce n'est pas un
arbitrage de gout, il y a une frontiere entre les deux cas et elle est nette :

- **La coupure de ligne degradait la lecture. Tous les chiffres restaient visibles, dans le bon ordre,
  dans la bonne colonne.** Un enfant lent, gene, qui devait rassembler `900` et `000` sur deux lignes -
  mais qui, s'il prenait le temps, lisait le bon nombre. C'etait un obstacle a la lecture.
- **Le debordement fausse le contenu. Des chiffres disparaissent** - sortis de l'ecran, ou poses en
  sombre sur du sombre - **et les colonnes voisines se melangent.** Un enfant qui prend tout le temps
  du monde lit `000 000` la ou il est ecrit `1 000 000`. Il n'y a plus de bonne reponse a l'ecran, et
  rien n'avertit qu'il en manque une partie.

Un enfant peut surmonter un texte mal coupe. Il ne peut pas deviner un chiffre qu'on ne lui montre
pas. **Et les deux sont inacceptables** : que le debordement soit pire ne rend pas la coupure
acceptable, et je ne veux pas qu'on lise ma comparaison comme une autorisation de revenir en arriere.
Le retour a l'espace ordinaire ne serait pas une correction, ce serait l'echange d'un defaut contre un
autre, deja refuse. Ce qu'il faut, c'est que le nombre entre dans sa colonne.

#### 5. Le caractere du separateur - U+00A0 ou U+202F, ma reponse

**Le choix de `marc-dev` me convient, et je retiens U+00A0.** Ma condition MD-4 est tenue par les
deux, la classe de rupture est la meme, et son motif est mesure. Je ne fais pas payer a l'equipe une
preference typographique.

**Et je l'ai verifie sur le seul plan qui pouvait renverser mon avis** : l'ecart de largeur. U+202F
est plus etroite, mais mesure a la police reelle du bouton, elle ne repare rien.

| jeton | U+00A0 | U+202F | gain | debord par cote (colonne de 96 px) |
|---|---|---|---|---|
| `100 000` | 119,3 px | 114,8 px | 4,5 px | 11,6 -> 9,4 px |
| `1 000 000` | 146,5 px | 137,7 px | 8,8 px | 25,2 -> 20,8 px |

**L'espace fine economise 3 a 5 % de largeur, la ou il en faudrait 20 a 35 %.** Le debordement du
point 3 subsisterait a l'identique, et les colonnes se recouvriraient encore. La cause du defaut est
la largeur de la colonne, pas le caractere du separateur - et cela evite a quiconque de perdre un lot
a changer le separateur en croyant corriger le rendu.

Reste l'argument typographique, qui est reel : U+202F est la bonne espace pour un separateur de
milliers en francais, et ce texte est lu par un enfant. **Je le classe comme une reserve, la
huitieme, pas comme une condition** - l'ecart est de 4,5 px sur un nombre de six chiffres, aucun
enfant de CM1 ne le voit, et la reproductibilite du test est un argument que j'accepte. Si un jour un
test d'integration sait manipuler U+202F sans peine, qu'on bascule ; d'ici la, U+00A0 est le bon
choix et je l'assume avec `marc-dev`.

#### 6. Deux corrections de mes propres constats, avant qu'on s'appuie dessus

- **J'ai manque un debordement qui existait deja le 2026-08-15.** Ma reserve C decrivait les sept items
  "lire" comme *"lisible, mais lent et laid"* : un mot par ligne, sept lignes de haut. C'etait faux sur
  un point. `cinquante-`, `quarante-`, `soixante-` **sortaient deja de leur colonne**, jusqu'a 33 px
  par cote, et je ne l'avais pas mesure - j'avais regarde la hauteur et pas la largeur. Ces sept items
  ne sont donc **pas** un effet du lot de reprise, et il serait injuste de les mettre au compte de
  `marc-dev`. Ils entrent dans ma reserve C, qui s'aggrave d'autant et devient une reserve **de rendu**
  et non plus seulement de lenteur de lecture. Le motif du point 3 ne porte que sur les treize items
  ou la cause est le separateur.
- **Ma prediction du 2026-08-15 sur le garde-fou de rang etait juste, mais elle visait le mauvais
  axe, et il faut le savoir.** J'avais annonce que traiter le motif 1 rendrait le garde-fou muet sur
  les sept items "decomposer" ; c'est arrive exactement, la branche est sortie rouge et il a fallu
  deux lots pour la rouvrir. Ce que je n'avais pas dit, et que j'ai mesure aujourd'hui : **la
  mecanique melange les propositions a chaque manche** (melange de Fisher-Yates, methode `melanger`
  de `BanditManchotSession`), donc **le rang a l'ecran n'est pas une propriete du paquet**. Sur
  **2 800 manches reellement tirees**, la bonne reponse tombe a **31,8 / 34,1 / 34,1 %** entre la
  colonne de gauche, du milieu et de droite, et aucun item ne s'ecarte de facon exploitable. Aucun
  enfant ne peut gagner en pressant toujours la meme colonne.

#### 7. Le rang, mesure par moi et par personne d'autre

On me signale deux chiffres contradictoires - **3/6/5, max 42,9 %** cote `marc-dev`, **5/4/5, max
35,7 %** dans `ADR-0041`. **Je n'en reprends aucun** et je n'arbitre pas entre eux : `theo-archi`
reconcilie. J'ai recompte, seule, sur les 28 items, en calculant moi-meme la valeur de chaque
proposition - entier pour une ecriture chiffree, **total de la somme** pour une decomposition, valeur
designee pour une ecriture en toutes lettres :

- **21 items classables** (les 7 "comparer" n'ont pas de valeur) : **3 fois la plus petite, 9 fois
  l'intermediaire, 9 fois la plus grande - 14,3 / 42,9 / 42,9 %.**
- Par verbe : "ecrire" 2/3/2, "lire" 0/3/4, "decomposer" 1/3/3.

**Aucun rang ne depasse 50 % ni ne tombe sous 10 % : le seuil en vigueur sur ce paquet est tenu, et
le rang ne fonde pas ce refus.** Je note au passage que la barre plus haute de MD-5 (40 % / 20 %) a
ete ecrite pour S13-2 et S13-3, sur un contenu ou l'echelle des valeurs est reguliere ; **je ne
l'applique pas retroactivement ici**, elle ne l'a jamais visee.

Une reserve neuve en sort, non bloquante : sur les 7 items "lire", la bonne reponse n'est **jamais**
la plus petite valeur (0 sur 7). Sept items, c'est trop peu pour un motif ; c'est assez pour l'ecrire.

**Addendum du 2026-08-16, apres coup et sans effet sur ce verdict.** `round-two` a avance en
`34abbb7` pendant que j'ecrivais, sur **de la documentation seule** (`032d34e`, note de `theo-archi`
sur `ADR-0041` ; le paquet, la feuille de style et le code sont inchanges, donc toutes mes mesures
tiennent sur cette tete-la aussi). Sa reconciliation conclut a **3/6/5, max 42,9 % sur 14 items**, et
attribue le 5/4/5 au contenu **d'avant** la reprise - celui que j'avais refuse. **Mon recompte a la
main, fait avant de lire sa note, donne exactement 3/6/5 sur ces memes 14 items** ("ecrire" 2/3/2 +
"decomposer" 1/3/3). Les 7 items "lire" (0/3/4) sont hors du domaine du garde-fou et n'entrent pas
dans son compte : c'est toute la difference entre son 14 et mon 21. Les deux mesures disent la meme
chose, je n'ai fonde mon verdict sur aucune des siennes, et je le note pour que la concordance soit
lisible.

#### 8. Reserves - l'etat au 2026-08-16

**Mes sept reserves du 2026-08-15 (A a G) restent ouvertes, non bloquantes, et non traitees**, ce qui
etait mon cadrage. Je ne les transforme pas en refonte et je n'en fais pas des conditions. Deux
evoluent, aucune ne durcit :

- **Reserve A (pli)** : inchangee dans son principe. Le paquet garde des propositions hautes, donc un
  message et un indice bas. Non bloquante, `DEC-0025` tient.
- **Reserve C (coupure de ligne)** : sa moitie "ecrire" et "comparer" est levee, plus aucun groupe de
  chiffres ne se coupe. Sa moitie "lire" reste, aggravee du constat de largeur du point 6.
- **Reserves B, D, E, F, G** : inchangees, mot pour mot.

Deux reserves neuves, datees du 2026-08-16 :

- **Reserve H (2026-08-16) - le separateur devrait etre U+202F le jour ou un test saura le
  reproduire.** Motif au point 5. Aucune urgence, aucun enfant ne voit les 4,5 px d'ecart.
- **Reserve I (2026-08-16) - sur les 7 items "lire", la bonne reponse n'est jamais la plus petite
  valeur.** Motif au point 7. Une piste qui ne coute rien : un distracteur plus grand que la bonne
  reponse sur un ou deux de ces items suffirait.

Rien a signaler sur les biais : ce paquet ne met en scene personne, et le ton n'est jamais
condescendant.

#### 9. Les proprietes dont je reste la seule garde sur ce jeu

Je les renomme, parce que la liste a change et que personne ne doit croire couvert ce qui ne l'est pas.

- **Ce qu'un nombre devient a l'ecran** - qu'il tienne dans sa colonne, qu'aucun chiffre ne tombe sur
  le fond sombre ni hors de l'ecran, que deux colonnes voisines ne se recouvrent pas. **Aucun
  garde-fou ne connait la largeur d'un ecran**, et c'est le motif meme de ce refus : quatre lots ont
  traverse une CI verte avec ce defaut. Tant que rien ne mesure cela, c'est moi, en jouant, a 360 px.
- **La plausibilite de chaque distracteur.** Elle est desormais bonne sur "decomposer" ; c'est moi qui
  l'ai verifiee, aucun automate ne la voit.
- **L'exactitude de chaque bonne reponse et de chaque indice**, y compris l'arithmetique des
  decompositions et le nom des rangs.
- **L'orthographe des mots-nombres** - le test compare le YAML a une table ecrite a la main : une
  graphie fautive dans les deux resterait verte.
- **L'utilite reelle de chaque indice**, lue apres un echec reel.
- **Le rang de la bonne reponse sur les 7 items "comparer"**, que le garde-fou n'atteint pas et
  n'atteindra pas : `<`, `>` et `=` n'ont pas de valeur.
- **Le niveau tel qu'il se joue.**

Une propriete **sort** de cette liste et je le dis pour que le credit aille ou il revient : le
classement des propositions de valeur egale et la lecture du total d'une somme sont desormais gardes
par le code (`lea-dev`, ratifie par `theo-archi`). Sur les 7 items "decomposer", je ne suis plus le
seul garde-fou du rang.

#### 10. Ce que `camille-po` doit retenir

- La ligne **ne passe pas** a `couvert-valide`. Le perimetre de la fiche reste ouvert en entier.
- **Une seule story a ecrire pour lever ce refus** : que les propositions du paquet tiennent dans
  leur colonne a 360 px, sans qu'aucun chiffre sorte du bouton ni de l'ecran. C'est un travail de
  rendu, pas de contenu - le YAML n'est plus en cause. `theo-archi` traite la cause.
- **Le contenu, lui, est bon.** Les neuf criteres d'acceptation sont tenus, les quatre verbes sont
  travailles, les 28 bonnes reponses sont justes, les 28 indices sont exacts, les 56 distracteurs sont
  des erreurs reelles. Si le rendu est corrige sans toucher au YAML, je n'aurai plus rien a objecter
  sur le fond, et je le dis d'avance pour que personne ne rouvre le contenu par precaution.
- **Ne pas revenir a l'espace ordinaire** pour corriger le debordement : ce serait rouvrir mon motif
  du 2026-08-15. Voir le point 4.
- **Ne pas changer le separateur pour U+202F en croyant corriger le rendu** : mesure au point 5, cela
  ne repare rien.
- Les sept items "lire" debordaient **avant** ce lot (point 6) : ils entrent dans la meme story de
  rendu, ils ne sont pas une regression de `marc-dev`.

---

### Note du 2026-08-16 (soir) - la voie de rendu est tranchee, mon refus tient jusqu'a sa livraison

- **Auteur** : nadia-prof. **Ce bloc ne modifie aucun verdict** : le refus du 2026-08-16 reste
  entier et ses reserves aussi. Il dit seulement ce qui le levera.

`theo-archi` a chiffre quatre voies (`avis-jeton-en-image.md`, branche `s13-theo-jeton`) et j'ai
tranche dans `ARBITRAGE-PREALABLE-s13-voie-de-rendu-des-propositions.md` : **voie 4, la colonne
recule devant son contenu**, sous mes conditions VR-1 a VR-7.

**Ce que cela veut dire pour ce jeu.** Mon refus du 2026-08-16 portait un motif unique - a 360 px,
treize items affichent un nombre qui n'est pas le nombre ecrit. Ce motif tombe le jour ou la story
de rendu est livree, **sans toucher au YAML**, comme je l'avais ecrit d'avance. Rien de ce que j'ai
dit du contenu ne change : il est bon, et je le redis.

**Deux precisions que je dois a l'honnetete du dossier.**

1. **Je n'ai pas pu jouer ce jeu aujourd'hui.** `DEC-0031` l'a sorti du catalogue et il n'existe plus
   de chemin pour l'atteindre depuis l'ecran d'accueil. Ce que j'affirme de son comportement sous la
   voie 4 repose sur la mesure de `theo-archi` (+132 px, zero debordement) et non sur une partie que
   j'aurais jouee. **C'est la seule zone degradee de mon arbitrage, et il faudra que je rejoue ce jeu
   avant tout Done pedagogique** - la remise au catalogue est donc un prealable a ma reverification,
   pas une consequence de mon accord.
2. **La question de l'utilisatrice - transformer le nombre en image - est fermee, et par une
   mesure.** Une image de `1 000 000` reduite pour tenir dans une colonne de 96 px donne des glyphes
   de 18,9 a 30,5 px, dont un sous mon plancher de 1,25 rem. **Elle n'achete pas un pixel** : le
   probleme n'a jamais ete la nature du nombre, c'est la largeur de la colonne. Ma condition C5 est
   maintenue sur ce cas, sur un motif que je date du 2026-08-16 et que j'explique dans l'arbitrage :
   ce qu'un enfant lit dans une boite de proposition doit rester verifiable par autre chose que moi.

---

### Done pedagogique du 2026-08-16 (verification apres livraison de la voie 4) - VALIDE SOUS RESERVE

- **Verdict** : **VALIDE SOUS RESERVE**. Le Done pedagogique est ouvert. `camille-po` peut passer la
  ligne "Lire, ecrire, decomposer et comparer les nombres entiers jusqu'au million" a
  `couvert-valide`, et l'entree de catalogue peut rejoindre `round-two` par le mecanisme ordinaire
  de l'amendement du 2026-08-16 a `DEC-0031`, section 2.
- **Auteur** : nadia-prof. **Troisieme passage de ce jeu devant moi** : refuse le 2026-08-15, refuse
  le 2026-08-16, favorable aujourd'hui. Je le compte comme tel, ainsi que l'amendement de
  `DEC-0031` me demande de le faire.
- **Les deux refus ne sont pas effaces** : ils restent lisibles ci-dessus, avec leurs motifs. Un
  refus efface n'a aucune valeur de preuve. J'empile.
- **Arbre juge** : worktree dedie `s13-nadia-verification-voie-4`, branche du meme nom, posee sur la
  tete de `round-two` a `2d79f2e`, **plus une seule modification de ma main** : la reinstallation de
  l'entree `id: bandit-manchot-grands-nombres-cm1` dans `catalogue.yaml`, telle que `fdf6bae`
  l'avait retiree, mot pour mot. `mvn verify` vert sur JDK 21.0.10, **488 tests**, aucune regle
  d'architecture touchee. **Cette branche n'est pas destinee a la fusion** : si ce verdict est suivi,
  c'est `sofia-sm` qui recable l'entree sur `round-two`.
- **Mode : j'ai joue, et c'est la premiere mesure independante apres implementation.** Jar
  reconstruit depuis ce worktree, lance par l'ecran de catalogue reel, navigateur pilote a
  360 x 640. **28 items sur 28 vus, 94 manches.** J'ai joue **en parallele l'etat que j'avais
  refuse** - second jar construit depuis le meme worktree avec la seule feuille de style de
  `c0c51c5` - pour comparer item par item plutot que de croire un avant que je n'aurais pas remesure.
  Releve : `releves-s13/releve-verification-voie4-360x640.json`.

#### 1. Le motif de mes deux refus est ferme, et je l'ai mesure sur les deux etats

| | etat refuse (`c0c51c5`) | voie 4 livree (`2d79f2e`) |
|---|---|---|
| items qui debordent de leur boite | **20 sur 28** | **0 sur 28** |
| pire debord par cote | 33,07 px | 0 px |
| propositions sortant de l'ecran | 0 | 0 |
| police de proposition | 32 px | 32 px |

**Mes chiffres d'avant se retrouvent exactement.** Mon second refus comptait treize items montrant
un nombre qui n'est pas le nombre ecrit, et sept items "lire" qui debordaient deja avant le lot de
`marc-dev` : 13 + 7 = **20**, et c'est ce que la mesure d'aujourd'hui donne sur l'etat refuse. Je
n'ai fonde ce verdict sur aucun chiffre de `theo-archi` ni de `lea-dev`.

**Et ce que l'ecran affiche est bien ce que le YAML ecrit.** Sur les 28 items, les trois
propositions rendues sont identiques **caractere pour caractere** aux chaines du paquet, espace
insecable U+00A0 comprise. Aucun chiffre n'est perdu, aucun separateur n'est transforme. C'etait le
coeur de mes deux refus - un enfant lisait autre chose que ce qui est ecrit - et ce n'est plus le
cas sur aucun item.

Le contenu, lui, n'a pas ete rouvert et n'avait pas a l'etre : les neuf criteres etaient tenus, les
28 bonnes reponses justes, les 28 indices exacts, les 56 distracteurs des erreurs reelles. Je
l'avais ecrit d'avance, je le confirme apres avoir joue.

#### 2. Je corrige une mesure de ma main avant qu'on s'appuie dessus

Mon premier depouillement d'aujourd'hui comptait le debord contre la **boite de contenu** du bouton
(rembourrage exclu) et annoncait des chiffres gonfles - 37,4 px la ou la convention en donne 16,4.
Il donnait aussi quatorze items debordants sur "Le bon verbe", jeu qui n'a jamais deborde. **La
convention que j'ai posee dans le verdict du 2026-08-16 compte le debord contre la boite du bouton**,
et c'est la bonne : un texte plus large que la boite de contenu mais plus etroit que la boite mange
son rembourrage, il ne sort pas du bouton et l'enfant lit tout. Depouillement refait sous la bonne
convention ; ce sont ces chiffres-la qui sont ci-dessus, et ils concordent avec les miens de
`releve-voies-de-rendu-360x640.json` (mesures 8 items / 16,4 px ; perimetre 25,3 px).

#### 3. Ce qui n'est pas tenu : ma condition VR-4, et de huit fois

**Je l'ecris avant tout le reste parce que c'est le point le plus defavorable du dossier.** VR-4
disait : aucune proposition entierement invisible sous le pli, **sauf** l'item
`Le perimetre d'une figure, c'est...`, seul cas que j'admettais parce que je l'avais mesure et pese,
et j'ajoutais : *"un deuxieme item dans ce cas me revient"*.

Sur ce paquet, a 360 x 640, **huit items sur 28 placent une proposition entierement sous le pli** :

| item | defilement necessaire |
|---|---|
| `Lis ce nombre : 47 300.` | 259 px |
| `Lis ce nombre : 816 203.` | **355 px** |
| `Lis ce nombre : 32 010.` | 259 px |
| `Lis ce nombre : 213 500.` | 259 px |
| `Lis ce nombre : 528 006.` | 243 px |
| `Decompose 305 421 en valeurs de position.` | 307 px |
| `Decompose 452 000 en valeurs de position.` | 307 px |
| `Decompose 128 000 en valeurs de position.` | 243 px |

Ce n'est pas disperse : ce sont **cinq des sept items "lire" et trois des sept items "decomposer"**,
c'est-a-dire exactement les deux verbes dont les propositions sont longues. Les sept "ecrire" et les
sept "comparer" ne sont jamais concernes.

Comme la mecanique melange les propositions a chaque manche, **la proposition cachee est la bonne
reponse une fois sur trois** sur ces items. Sur les huit manches que j'ai relevees, c'etait le cas
cinq fois. En partie de dix manches, un enfant rencontre en moyenne trois de ces items, donc
**environ une manche par partie ou la bonne reponse n'est pas a l'ecran au moment de choisir**.
Capture : `releves-s13/verif-voie4-grands-nombres-816203-pli-360x640.png`.

#### 4. Pourquoi c'est une reserve et pas un troisieme refus - et ce que ca m'a coute de le decider

Je ne valide pas parce que c'est le troisieme passage, ni parce que l'equipe a livre ce que j'avais
demande. Voici mon raisonnement, avec ce qui plaide contre.

**Ce que j'ai verifie avant de trancher, et qui compte :**

- **Sur les huit items, sans exception, une boite est coupee par le pli.** L'ecran ne parait jamais
  complet : l'enfant voit qu'il reste quelque chose dessous. Si un seul de ces huit items s'etait
  termine proprement au-dessus de 640 px, sans aucun indice, j'aurais refuse - un ecran qui parait
  fini et qui cache un choix ne se surmonte pas, il trompe.
- **Le choix cache est atteignable** : 243 a 355 px de defilement, et une fois atteint il est
  integralement lisible, chaque chiffre dans sa boite
  (`releves-s13/verif-voie4-grands-nombres-128000-page-entiere.png`).
- **L'enfant qui se trompe n'est pas puni sans recours** : je me suis trompee volontairement trois
  fois de suite sur `Decompose 128 000`. La proposition pressee est barree et reste a sa place, les
  trois essais tiennent, et le troisieme echec revele la bonne reponse en la nommant
  (*"C'est 100 000 + 20 000 + 8 000. Appuie sur 100 000 + 20 000 + 8 000."*). A partir du deuxieme
  essai, le focus porte la page vers le bas et tout devient visible.
- **Rien de faux n'est montre.** C'etait mon motif de refus, et il est ferme.

**Ce que ca coute quand meme, et je ne l'habille pas :** au premier echec sur un de ces huit items,
le message *"Ce n'est pas ca. Essaie encore !"* tombe a 851 px et l'enfant qui n'a pas defile ne
voit qu'une boite barree, sans un mot. `DEC-0025` admet le message et l'indice sous le pli, donc ce
n'est pas un motif neuf ; c'est une aggravation de ce que `DEC-0025` a accepte, et je la nomme.

**Ma frontiere, appliquee dans le meme sens que les deux fois precedentes** : *un obstacle qu'on
surmonte n'est pas du meme ordre qu'un texte qu'on ne peut pas lire*. C'est par ce raisonnement que
j'ai tranche le debordement contre la coupure, puis accepte l'item du perimetre. Un choix sous le
pli, avec un indice visible et un chemin de retour, est un obstacle. **Le tenir pour un refus ici
apres l'avoir tenu pour un cout acceptable la-bas, le meme jour, sur la meme classe de fait, serait
appliquer ma porte dans deux sens opposes selon le jeu.**

**Et la ou je dois etre franche : huit items, ce n'est pas "un deuxieme item".** La difference avec
le perimetre est une difference de **frequence, pas de nature** - une manche par partie contre une
manche sur six parties - et je refuse de la maquiller en difference de principe. C'est cette
frequence qui fait que **je n'accorde pas un Done plein** : VR-4 n'est pas tenue, la reserve
ci-dessous est ecrite pour cela, et elle est la plus utile des trois.

#### 5. Reserves - l'etat au 2026-08-16 (soir)

**Reserve J (neuve, 2026-08-16) - huit items placent un choix entierement sous le pli a 360 x 640.**
Motif au point 3. **Critere de levee, ecrit pour qu'un tiers puisse le verifier sans me le
redemander** : a 360 x 640, sur les 28 items du paquet, aucune proposition n'a son bord superieur a
640 px ou au-dela. C'est un travail de rendu et il ne touche pas le YAML : ce qui pousse le bloc
vers le bas, ce sont les trois lignes d'enonce a 2 rem, le bandeau du cabinet et ce qui les
surmonte, pas les propositions. `theo-archi` traite la cause ; je ne prescris aucune voie.

**Les reserves A a I restent ouvertes.** Deux evoluent, aucune ne durcit :

- **Reserve C (coupure de ligne)** : sa moitie "lire", que le constat de largeur du 2026-08-16
  aggravait, **retrouve son etat d'origine** - plus aucun texte ne deborde. Verifie : sous le repli,
  les propositions ne se coupent qu'aux espaces ordinaires, **aucun groupe de chiffres n'est
  separe**, l'espace insecable tient. La reserve reste, non aggravee.
- **Reserve H (U+202F)** : inchangee, et je confirme que le separateur rendu est toujours U+00A0.
  Aucune urgence.
- **Reserves A, B, D, E, F, G, I** : inchangees, mot pour mot.

Rien a signaler sur les biais ni sur le ton : ce paquet ne met en scene personne.

#### 6. Ce dont je reste la seule garde sur ce jeu

La liste du 2026-08-16 est inchangee sauf sur un point, et elle **s'agrandit d'une entree** :

- **Qu'un texte tienne dans sa boite a 360 px.** Toujours a moi. La regle livree avec la voie 4 garde
  **la cause** (un conteneur qui se replie, une base de colonne non nulle), jamais **l'effet**. Un
  debordement reintroduit par une largeur en pixels ou un `white-space` resterait vert - `lea-dev`
  l'ecrit elle-meme, et c'est exact.
- **Qu'aucun choix ne passe sous le pli** (nouveau ici, et c'est la reserve J). Rien ne le mesure.
- L'exactitude de chaque bonne reponse et de chaque indice, la plausibilite de chaque distracteur,
  l'orthographe des mots-nombres, l'utilite reelle de chaque indice, le rang sur les 7 items
  "comparer", le niveau tel qu'il se joue : inchanges.

#### 7. Ce que `camille-po` doit retenir

- **La ligne peut passer a `couvert-valide`**, avec la reserve J ouverte.
- **Une story de rendu a ecrire** : critere au point 5. Elle concerne aussi "Le perimetre", qui garde
  son item sous le pli - le mien, celui que j'ai accepte.
- **Un point de code que ma verification fait apparaitre et que je signale sans le corriger** :
  `a9a41aa` a retire la methode `leJeuDuCatalogueChargeBienLePaquetCm1GrandsNombres` en meme temps
  que l'entree de catalogue. Sur ma branche, l'entree est revenue **et la suite reste a 488 tests** :
  autrement dit, **rien ne verifie aujourd'hui que cette entree charge bien ce paquet**. Le jour ou
  `sofia-sm` recable l'entree sur `round-two`, cette methode doit revenir dans le meme geste, sans
  quoi le catalogue rentrera avec une entree que plus aucun test ne relie a son contenu. Ce n'est pas
  un motif pedagogique et ce n'est pas mon perimetre : c'est un fait que seule ma reinstallation
  pouvait reveler.
