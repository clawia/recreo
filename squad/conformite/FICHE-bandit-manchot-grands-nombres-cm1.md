# Fiche de conformite - bandit-manchot-grands-nombres-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : contenu neuf a creer (`type: question`, au moins 24 items), story `S13-1`
  (`docs/backlog/backlog.md`). Aucune matiere premiere de `contenu-repris/` identifiee pour cette
  ligne - le proxy de transcription chiffres/lettres qui la rend servable n'a jamais ete cable sur
  ce produit.
- **Statut global** : **REFUSE** par `nadia-prof` le 2026-08-15, apres avoir joue l'increment
  (S13-1, paquet `cm1-grands-nombres.yaml`). Deux motifs, tous deux actionnables : le verbe
  "decomposer" n'est pas travaille par les items qui le portent, et deux indices sont faux sur
  `600 040`. Detail, mesures et reserves non bloquantes dans le bloc de validation. Ce refus renvoie
  l'increment a `camille-po`, qui en fait du backlog ; **il ne bloque pas le sprint suivant**, et le
  jeu peut rester au catalogue en l'etat si elle le decide - il n'est ni dangereux ni globalement
  faux, mais il ne couvre pas ce qu'il annonce.
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
- **Derniere mise a jour** : 2026-08-15 (Done pedagogique REFUSE par `nadia-prof`, apres avoir joue)

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
