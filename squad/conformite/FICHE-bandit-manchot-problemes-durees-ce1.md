# Fiche de conformite - bandit-manchot-problemes-durees-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-problemes-durees.yaml`
  (24 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-problemes-durees-ce1`, titre servi *"Le temps qui
  passe"*, consigne servie *"Trouve combien de temps s'est ecoule."*, `nombreDeManches: 10`.
- **Ligne declaree** : `MAT-CE1-12`.
- **Statut global** : **EN ATTENTE D'AUDIT.** Fiche ouverte par `camille-po` a l'ouverture du
  bloc de construction du sprint s43 (paquet, catalogue et test livres et fusionnes dans
  `round-two`, commit `e980f31a`). Le bloc de validation pedagogique reste vide jusqu'a ce que
  `nadia-prof` le remplisse.
- **Story** : `S43-MAT-DUREE` - Calculer une duree simple en heures entieres ou en demi-heures
  (`docs/backlog/backlog.md`).
- **Competence programme visee** : mathematiques, CE1 (cycle 2), domaine "Grandeurs et mesures" -
  `squad/programme/mathematiques/competences-ce1.md`, `MAT-CE1-12`. Ligne `non couvert` dans
  `squad/programme/MATRICE-couverture.md` (ligne 678) au moment d'ouverture de cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage
  neuf a router pour les conditions generales de la ligne**
  (`squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md`, section 5) : *"Verdict :
  ACCEPTE SOUS CONDITIONS `DUR-CE1-1` a `DUR-CE1-6`. Servable par du texte seul - c'est de
  l'arithmetique sur des instants, aucun cadran n'est requis - et la ligne peut atteindre
  `couvert-valide` sur un paquet unique."* Une seule forme est admise (section 5.1) : deux
  instants donnes, la duree a trouver - jamais l'horaire d'arrivee. Les six conditions,
  `DUR-CE1-1` a `DUR-CE1-6`, citees mot pour mot dans la story, portent sur la forme unique, les
  bornes des instants et des durees, l'ecriture distincte instant/duree, le franchissement de la
  demi-heure, les trois familles de distracteurs et les trois rangs de la bonne reponse, et
  l'enonce, les situations, l'indice et le volume du paquet.
- **Arbitrage prealable neuf, ajoute le 2026-09-25 par `nadia-prof` avant l'ecriture du paquet,
  discriminant avec `cm1-problemes-durees` deja servi** : `DUR-CE1-7`, cinq clauses (l'indice
  nomme le pas de comptage du CE1 et ne reprend aucun des quatre indices deja servis par le
  paquet de CM1 ; au moins un tiers des items porte une duree de deux heures ou plus ; aucun
  enonce ne reprend le couple prenom-situation d'un enonce du CM1 ; l'ecriture choisie laisse
  l'erreur de base 100 ecrivable ; au moins deux items portent un distracteur superieur a 59
  minutes), cite en entier dans la story. Ce bloc pose une barre avant le premier item et ne
  prononce aucun Done pedagogique.
- **Lien decision** : `DEC-0099` (section 9.5).
- **Derniere mise a jour** : 2026-09-25 (ouverture, `camille-po`).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S43-MAT-DUREE`)

- Etant donne un item, quand il s'affiche, alors il donne deux instants et demande la duree
  ecoulee entre eux, jamais un horaire d'arrivee ou de depart a calculer, jamais une lecture
  d'heure seule, jamais une conversion d'unite de temps isolee, jamais une comparaison de durees
  (`DUR-CE1-1`).
- Etant donne les deux instants d'un item, quand on les relit, alors leurs minutes valent `00` ou
  `30` uniquement, ils appartiennent a la meme journee sans franchir minuit, aucune seconde
  n'apparait, l'ecriture est sur 24 heures (jamais "de l'apres-midi"), et la duree a trouver vaut
  au moins 30 minutes et au plus 4 heures (`DUR-CE1-2`).
- Etant donne le paquet livre, quand on compare l'ecriture des instants et celle des durees,
  alors les deux formes sont visiblement differentes et constantes dans tout le paquet (par
  exemple `14 h 30` pour un instant contre "2 heures et demie" pour une duree), et dans chaque
  item les trois propositions partagent la meme ecriture (`DUR-CE1-3`).
- Etant donne le paquet livre, quand on compte les items dont les deux instants ont des minutes
  differentes (`00` et `30`, franchissement d'une demi-heure), alors ils representent entre un
  tiers et deux tiers du paquet (`DUR-CE1-4`).
- Etant donne les distracteurs d'un item, quand on les verifie, alors chacun correspond a une
  erreur nommee dans le fichier a cote de l'item - au minimum parmi la demi-heure oubliee, le
  comptage en base 100 ou l'heure decalee d'un rang a minutes justes - et aucune proposition
  n'egale une autre ; sur le paquet entier, chacun des trois rangs de la bonne reponse porte au
  moins 10 % et au plus 50 % des items (`DUR-CE1-5`).
- Etant donne l'enonce d'un item, quand on le relit, alors il tient dans vingt mots et trois
  phrases au plus, la derniere etant la question (`LON-CE1-1`, `LON-CE1-2`, reprises par
  `DUR-CE1-6`), toutes les donnees necessaires y figurent, la situation ne suppose ni avion, ni
  depart en vacances, ni adulte qui conduit l'enfant quelque part, et aucun retard n'y est
  dramatise.
- Etant donne l'indice d'un item, quand il s'affiche, alors il donne une methode et jamais le
  resultat, et il n'est pas identique sur tout le paquet (`DUR-CE1-6`).
- Etant donne le paquet livre, quand on compte ses items, alors il en porte au moins vingt
  (`DUR-CE1-6`).
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `MAT-CE1-12` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` le joue, alors elle confirme
  que les six conditions `DUR-CE1-1` a `DUR-CE1-6` sont tenues et que la ligne passe a
  `couvert-valide`.
- **`DUR-CE1-7` (ajout du 2026-09-25), opposable au meme titre que les six conditions
  ci-dessus** : chaque indice nomme le pas de comptage du CE1 sans reprendre l'un des quatre
  indices deja servis par `cm1-problemes-durees` (clause 1) ; au moins un tiers des items porte
  une duree de deux heures ou plus (clause 2) ; aucun enonce ne reprend le couple prenom-situation
  d'un enonce du CM1 (clause 3) ; l'ecriture choisie laisse l'erreur de base 100 ecrivable
  (clause 4) ; si un distracteur superieur a 59 minutes est servi, au moins deux items en portent
  un (clause 5).

## Ce que cette story ne fait pas

Elle ne construit aucun rendu de cadran ni aucune lecture d'heure sur une horloge - c'est
`MAT-CE1-11`, hors de son lot et hors de cette story ; elle ne sert jamais la forme "instant de
depart + duree -> horaire d'arrivee", explicitement exclue par `nadia-prof` elle-meme et non
opposable ulterieurement comme un trou de couverture ; elle ne demande jamais une conversion
d'unite de temps isolee ni une comparaison de durees sans calcul ; elle ne fixe aucun contenu
factuel precis (quelles situations, quels instants retenus) - reste a l'auteur du paquet puis a
l'audit de `nadia-prof`.

---

## Bloc de validation pedagogique

### Verdict du 2026-09-25 (`nadia-prof`, s43, instance unique) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce.** `camille-po` peut passer `MAT-CE1-12` a `couvert-valide` dans
`squad/programme/MATRICE-couverture.md`. La reserve `DUR-CE1-R1` ci-dessous ne le bloque pas, et je
dis pourquoi a l'endroit ou je la pose.

#### 1. Ce que j'ai fait, et non ce que j'ai lu

**Audit complet, pas degrade.** J'ai lance l'application (`java -jar bootstrap/target/recreo.jar`,
machine virtuelle du JDK 21 de la toolchain) et j'ai joue **quatre parties** de
`bandit-manchot-problemes-durees-ce1` :

- **trois parties completes en repondant juste**, trente manches, la duree recalculee a la main sur
  chaque enonce avant de choisir. **Les trente reponses que j'ai calculees sont les trente que le
  jeu credite** : aucun desaccord d'arithmetique entre le paquet et moi. Ecran de fin correct
  (*"Tu as trouve, tout seul : 10 / Tu as joue toutes les questions : 10"*) ;
- **une partie en me trompant volontairement**, sur l'item *"Idris regarde un film de 13 h 30 a
  15 h 30"*. Apres la premiere erreur : *"Ce n'est pas ca. Essaie encore !"* et l'indice. Apres la
  deuxieme : *"Ce n'est pas ca. / Appuie sur la bonne reponse : 2 h 00 min"*, l'item reste a
  l'ecran et l'enfant bloque avance. **Rien ne pietine et rien ne se derobe.** Le comportement du
  jeu apres erreur n'est pas en cause dans ce verdict.

#### 2. Ancrage au programme

`squad/programme/mathematiques/competences-ce1.md`, `MAT-CE1-12` : *"Calculer une duree simple en
heures entieres ou en demi-heures."* **C'est bien cette competence-la que l'increment travaille**,
et pas une voisine plus facile : les vingt-quatre items donnent deux instants et demandent la duree
ecoulee, aucun ne demande de lire une heure (`MAT-CE1-11`), aucun ne demande un horaire d'arrivee,
aucun ne convertit une unite seule, aucun ne compare deux durees.

#### 3. Les six conditions `DUR-CE1-1` a `DUR-CE1-6`, verifiees une par une

| Condition | Tenue | Ce que j'ai mesure |
|---|---|---|
| `DUR-CE1-1` | **oui** | 24 items sur 24 de la forme "deux instants, la duree a trouver". Aucune des quatre formes exclues |
| `DUR-CE1-2` | **oui** | toutes les minutes valent `00` ou `30` ; aucune seconde ; ecriture sur 24 heures partout, jamais "de l'apres-midi" ; aucun franchissement de minuit ; durees de 30 min a 4 h 00 min, bornes comprises |
| `DUR-CE1-3` | **oui, sous l'interpretation que je fixe en section 4** | les instants ne portent jamais le mot "min", les durees le portent toujours, y compris sur une duree ronde (`1 h 00 min`) |
| `DUR-CE1-4` | **oui** | items 1 a 8, 18, 19, 22, 23 franchissent la demi-heure, soit **12 sur 24 (50 %)**, entre le tiers et les deux tiers. Recompte a la main sur les items, pas repris du commentaire du paquet |
| `DUR-CE1-5` | **oui** | chaque distracteur est a +/- 30 min, a +/- 60 min, ou est l'erreur de base 100 ; aucune proposition n'en egale une autre. Rangs de la bonne reponse, recomptes item par item : **rang 1 sur 10 items (41,7 %), rang 2 sur 9 (37,5 %), rang 3 sur 5 (20,8 %)**. Les trois entre 10 % et 50 %. Mon compte tombe sur celui du commentaire du paquet |
| `DUR-CE1-6` | **non, sur un point et un seul** | volume (24 > 20), enonces, situations et donnees : tenus. **L'indice de l'item 3 donne le resultat** : voir `DUR-CE1-R1` |

#### 4. `DUR-CE1-3` - je fixe l'interpretation de ma propre clause plutot que de la retourner contre le paquet

J'ai ecrit *"dans un meme item, les trois propositions portent la meme ecriture"*. Neuf items du
paquet (1 a 4, 6, 9, 11, 13, 15) presentent `30 min` a cote de `1 h 00 min` : au pied de la lettre,
ce ne sont pas deux fois la meme chaine.

**Ce n'est pas ce que cette clause protege, et je le dis maintenant pour que personne n'ait a me le
redemander.** Je l'ai ecrite contre le melange de **deux registres** dans un meme item - *"2 heures
et demie"* a cote de *"2 h 30 min"* -, qui designerait la bonne reponse a vue. Un seul registre dont
la composante d'heures est omise quand elle vaut zero est **le meme registre** ; ecrire `0 h 30 min`
n'est pas une forme qu'on pose devant un CE1. **La clause est tenue.**

Ce qu'il fallait verifier a la place, et que j'ai mesure : **la forme courte ne designe pas la bonne
reponse.** `30 min` est la bonne reponse sur **4 items** (1 a 4) et un distracteur sur **5** (6, 9,
11, 13, 15). Un enfant qui choisirait systematiquement la proposition la plus courte se tromperait
plus souvent qu'il n'aurait juste. Aucun tell.

#### 5. `DUR-CE1-7`, le discriminant que j'avais pose avant l'ecriture - les cinq clauses

Je l'ai verifie en lisant **les deux paquets cote a cote**, comme je m'y etais engagee, et non en
lisant un vert : aucun garde-fou du depot ne compare deux paquets.

- **Clause 1 (l'indice donne la methode du CE1)** : **tenue.** Six indices, cycliques. Chacun nomme
  le pas de comptage - la demi-heure, l'heure. **Aucun ne reprend l'un des quatre indices de calcul
  de duree de `cm1-problemes-durees`**, que j'ai relus dans le fichier : *"Compte d'abord jusqu'a
  l'heure ronde, puis ajoute le reste des minutes."*, *"Une heure, c'est 60 minutes, pas 100 :
  compte bien."*, *"Regarde les deux heures donnees et calcule ce qui les separe."*, *"Avance minute
  par minute jusqu'a l'heure ronde, puis compte ce qu'il reste."* Aucune reprise, ni au mot ni a la
  methode. C'est la clause qui comptait le plus et c'est celle qui est le mieux tenue.
- **Clause 2 (l'amplitude est reellement employee)** : **tenue.** Items 16 a 24, soit **9 sur 24
  (37,5 %)**, portent une duree de 2 heures ou plus, au-dessus du tiers exige. Le paquet monte a
  3 h 30 min et 4 h 00 min, la ou **les douze items de calcul de duree du CM1 restent tous sous
  1 h 30**. Le CE1 va donc bien au-dela du CM1 sur cet axe, sans sortir de sa ligne.
- **Clause 3 (aucune transposition d'enonce)** : **tenue.** Les vingt-quatre prenoms (Nina, Sami,
  Leo, Jade, Tom, Lina, Rayan, Emma, Karim, Lucie, Yohan, Sarah, Bilal, Anna, Malik, Rose, Idris,
  Naomi, Victor, Lou, Samir, Alicia, Mateo, Farah) sont **tous neufs** : aucun des vingt et un
  prenoms du CM1 que j'avais listes. Les huit situations sont reprises, ce que j'avais
  explicitement autorise - c'est la phrase et le personnage qui devaient etre neufs, pas le decor.
  Aucun enonce du CE1 ne decalque un enonce du CM1.
- **Clause 4 (l'ecriture laisse l'erreur de base 100 ecrivable)** : **tenue.** Le paquet a pris le
  registre `H h MM min`, et l'erreur y est effectivement servie.
- **Clause 5 (la valeur au-dela de 59 minutes n'est pas isolee)** : **tenue.** Deux items la
  portent, `1 h 70 min` (item 1) et `2 h 70 min` (item 5), jamais un seul. Meme barre que celle que
  j'avais posee sur le paquet de CM1 le 2026-08-30, tenue cette fois avant l'ecriture.

#### 6. Exactitude, biais et ton

**Les vingt-quatre durees sont exactes** : recalculees une par une, et confirmees par trente manches
jouees ou aucune de mes reponses n'a ete refusee. **Les distracteurs sont des erreurs reelles**, pas
des nombres decoratifs : la demi-heure oubliee ou comptee en trop, l'heure decalee d'un rang, le
comptage en base 100. Un enfant qui se trompe se trompe d'une facon qui s'explique.

**Aucun biais.** Filles et garcons sur les memes roles : Lina et Anna regardent un match, Rayan et
Malik font un gateau et une tarte, Jade roule a velo. Aucune situation qui suppose l'avion, le
depart en vacances ou l'adulte qui conduit l'enfant - les trajets sont a velo, en bus, en train.
Aucun retard dramatise. Aucun ton condescendant.

#### 7. La reserve

> **`DUR-CE1-R1` - l'indice de l'item 3 donne le resultat au lieu de la methode (`DUR-CE1-6`).**
> Item 3 : *"Leo est a la cantine de 9 h a 9 h 30. Combien de temps y est-il reste ?"*, bonne
> reponse `30 min`, indice *"Une demi-heure, c'est 30 minutes, pas 50."* **L'indice ecrit le nombre
> de la bonne reponse.** Un enfant qui s'est trompe une fois lit "30 minutes" et trouve `30 min`
> dans les propositions sans avoir a mesurer l'intervalle. `DUR-CE1-6` dit : *"L'indice donne la
> methode, jamais le resultat."*
>
> **C'est la seule occurrence, et je l'ai cherchee partout.** Cet indice sert aussi les items 9, 15
> et 21, dont les bonnes reponses valent `1 h 00 min`, `1 h 00 min` et `3 h 00 min` : il n'y donne
> rien. Un second cas, plus faible, merite d'etre regarde en meme temps : l'item 12 (`1 h 00 min`)
> porte l'indice *"Une heure, ce sont deux demi-heures. Compte-les toutes."*, qui nomme "une heure"
> sans la donner comme reponse. Je ne le compte pas dans la reserve, je le signale.
>
> **Correction a faire** : donner a l'item 3 un indice qui ne nomme pas la valeur cherchee, par
> exemple *"Regarde si l'on passe l'heure ronde ou si l'on s'arrete avant."*
>
> **Pourquoi cette reserve n'empeche pas `couvert-valide`** : elle porte sur **un indice sur
> vingt-quatre**, affiche apres une erreur, et elle ne touche ni la competence, ni l'exactitude, ni
> le niveau. Vingt-trois items sur vingt-quatre exigent le calcul. Fermer la ligne sur ce paquet est
> juste ; laisser l'indice en l'etat ne l'est pas.

#### 8. Ce dont je reste la seule garde sur cette ligne

L'equipe doit le savoir plutot que de croire couvert ce qui ne l'est pas. **Aucun garde-fou du
depot ne compare deux paquets** (`theo-archi`, avis de faisabilite de s43, section 6) :
`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` et
`AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` ont le **meme paquet** pour perimetre. **Les
cinq clauses de `DUR-CE1-7` n'ont donc aucun garde executable**, et ce verdict est le seul endroit
ou elles ont ete verifiees. Il en va de meme pour `DUR-CE1-R1` : `ADR-0101` dit lui-meme que la
barre de longueur *"ne juge que l'enonce, ni l'indice ni les propositions"*. **Un indice qui donne
sa reponse ne fait rougir rien du tout.**

Relance du garde-fou d'identifiants sur ce diff : voir la note de `squad/decisions/REGISTRE-identifiants.md`.
