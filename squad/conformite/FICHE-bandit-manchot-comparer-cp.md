# Fiche de conformite - bandit-manchot-comparer-cp

- **Mecanique** : bandit manchot (mecanique generique du catalogue, conditions `M1` a `M13`,
  `FICHE-bandit-manchot-additions-cp.md`). Cette fiche n'audite pas la mecanique : elle audite le
  paquet servi au travers d'elle.
- **Contenu servi** : `adapters/adapter-content/src/main/resources/contenu/cp-comparer.yaml`,
  `type: question`, **18 items** (6 longueur, 6 masse, 6 contenance), story `S21-11`, ecrit par
  `sofia-sm` sous sa casquette de developpeuse.
- **Entree de catalogue** : `bandit-manchot-comparer-cp`, titre *"Compare les grandeurs"*, consigne
  *"Regarde bien, puis compare."*, 10 manches.
- **Competence visee** : Mathematiques CP, domaine "Grandeurs et mesures", *"Comparer des
  longueurs, des masses, des contenances (sans instrument gradue)"*
  (`squad/programme/mathematiques/competences-cp.md`, ligne 19 ; verifie le 2026-08-27).
- **Conditions prealables** : `GRA-1` a `GRA-10`,
  `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md`, section 5.
- **Statut global** : **VALIDE SOUS RESERVE par `nadia-prof` le 2026-08-28**. **Les dix conditions
  prealables sont tenues, aucun item n'est indecidable, aucun n'a deux reponses defendables, et les
  18 items sur 18 sont au-dessus du pli.** Sept reserves sont ouvertes, aucune bloquante.
- **Derniere mise a jour** : 2026-08-28 (`nadia-prof`, audit de s21).

## Table de correspondance des jetons de cette fiche

Convention exigee par `squad/README.md`, section 2, amendement s16.

| Jeton | Ce qu'il designe | Ou il est ecrit |
|---|---|---|
| `GRA-1` a `GRA-10` | **Conditions prealables** a l'ecriture, posees le 2026-08-27 **avant** que le paquet existe | `ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md`, section 5.3 |
| `GRA-11` et suivants | **Reserves ouvertes a l'audit** du 2026-08-28 | cette fiche, section 5 |
| `SOR-1` a `SOR-4` | Conditions sur le **rembourrage de la sortie**, posees le 2026-08-27 | `FICHE-bandit-manchot-reperes-cm1.md`, section 3 |
| `M1` a `M13` | Conditions de la **mecanique**, hors du perimetre de cette fiche | `FICHE-bandit-manchot-additions-cp.md` |

**Regle de lecture** : `GRA-1` a `GRA-10` n'ont pas ete durcis. Une reserve numerotee `GRA-11` ou
au-dela est **nouvelle et datee**, jamais un resserrement retroactif.

## 1. Ce que j'ai fait, et ce que ma methode ne couvre pas

**J'ai joue, puis j'ai mesure.** Application construite depuis cet arbre
(`bash outils/mvn-jdk21.sh clean package -DskipTests`), servie par
`java -jar bootstrap/target/recreo.jar --server.port=<port>` sur un JDK 21, arretee **par son
identifiant de processus** et jamais par un nom d'image - `sofia-sm` a ferme des navigateurs qui
n'etaient pas les siens avec un arret par nom d'image le 2026-08-27, et c'est la raison pour
laquelle je l'ecris ici.

**Parties jouees le 2026-08-27**, par l'interface HTTP : parties completes de dix manches, en
repondant juste, en me trompant une ou deux fois, et en brulant les trois essais. **Puis j'ai relu
les dix-huit triplets un par un dans le fichier servi**, en cherchant lequel un enfant de six ans ne
pourrait pas trancher - c'est la verification que `GRA-3` demande, et elle ne se fait qu'a la main.

**Mesure de rendu**, 2026-08-27, viewport de reference de `DEC-0040`, **744 x 551**, navigateur avec
`--hide-scrollbars`, sur le **paquet entier** ; releve commite
`squad/conformite/releves-s21/s21-nadia2-comparer-paquet-entier-744x551.json`, viewport **lu du
navigateur** et non recopie de l'argument.

**L'outil du depot ne pouvait pas mesurer ce paquet, et le defaut est reel.**
`squad/outils/nadia-prof/auditer-un-paquet-entier.py` identifie un item **par la chaine de son
enonce** et cherche sa bonne reponse dans une table indexee par cette chaine. Or `cp-comparer` porte
**dix chaines d'enonce distinctes pour dix-huit items** : quatre d'entre elles servent trois items
chacune (reserve `GRA-13`). La table en confond donc trois a chaque fois, l'outil clique une bonne
reponse qui n'appartient pas a l'item affiche, et **il s'arrete en erreur** (execution du
2026-08-27). J'ai mesure avec une **variante qui identifie un item par le couple (enonce, triplet
trie de ses propositions)**, cle unique sur ce paquet par `GRA-5`. Le releve commite porte les 18
items. **Cette limite de l'outil est une suite a router, pas un defaut du paquet** : voir section 7.

**Limites, avec l'etat exact qu'elles couvrent :**

- **Un seul viewport, 744 x 551. 360 px n'est pas couvert** et rien de ce que je dis sur la
  geometrie n'y vaut.
- **Aucun enfant n'a joue ce paquet.** Le jugement sur ce qu'un enfant de six ans sait ranger sans
  instrument est un **jugement de professeure**, pas une observation.
- **L'outillage s'attache au premier navigateur qui repond sur un port de deverminage fixe** sans
  verifier que c'est le sien (`theo-archi`, `S21-1`, 2026-08-27). Ports distincts par execution
  (9381 a 9403) : mitigation, pas garantie.
- **`SOR-4` a ete verifie par un clic programme au centre exact de la boite** (section 6), pas par
  un doigt d'enfant. C'est ce que je peux faire ; ce n'est pas la meme chose.

## 2. Ancrage au programme, et le tri que j'avais demande

La competence annoncee par le descripteur est **mot pour mot** la ligne 19 du referentiel. Les
**trois** grandeurs qu'elle nomme sont travaillees, ce qui n'etait pas le cas du fichier repris.

**Le tri que j'avais demande a ete fait, et je l'ai reverifie item par item** : les trois items hors
sujet du fichier repris (deux comparaisons de nombres et **la vitesse**, ce troisieme-la que ni le
planning ni `DEC-0051` n'avaient compte) sont sortis ; l'item indecidable (`crayon` contre
`trousse`) est reecrit ; les six items retenus sont la ; la contenance, absente sur 10 items sur 10,
est ecrite. **Le compte corrige que j'avais donne a `camille-po` et `sofia-sm` a ete pris.**

## 3. Les conditions `GRA-1` a `GRA-10`, verifiees une par une

| Condition | Verdict | Ce que j'ai verifie, et comment |
|---|---|---|
| `GRA-1` aucune autre grandeur | **TENUE, 18 sur 18** | Les dix-huit items relus le 2026-08-27 : six longueurs, six masses, six contenances. **Aucun nombre, aucune vitesse, aucune duree, aucun prix, aucune quantite d'objets.** La ligne du referentiel n'est couverte par rien qu'elle ne nomme pas |
| `GRA-2` trois grandeurs, aucune sous le quart | **TENUE** | 6 / 6 / 6 sur 18. Le quart de 18 est 4,5 ; les trois sont a 6 |
| `GRA-3` decidable a six ans sans instrument | **TENUE, 18 sur 18** | **C'est la condition la plus difficile du jeu, et c'est celle qui est le mieux tenue.** Les dix-huit triplets pris un par un : je n'en trouve aucun dont deux objets puissent etre ranges dans les deux sens selon l'exemplaire qu'on imagine. Les ecarts sont francs partout (`tabouret`/`table`/`armoire` ; `baleine`/`chat`/`sardine` ; `camion`/`trottinette`/`velo` ; `piscine`/`gourde`/`tasse`). Le piege que j'avais nomme - `un crayon` contre `une trousse` - n'a pas ete reintroduit |
| `GRA-4` au moins un tiers dans le sens "le moins" | **TENUE, largement** | Compte a la main : **9 items sur 18** demandent le plus court, le plus petit, le plus bas, le plus leger ou le moins d'eau. Un tiers etait la barre ; la moitie est servie. Un enfant qui ne lit pas l'adjectif et designe toujours "le plus gros" se trompe une fois sur deux |
| `GRA-5` aucun triplet repete | **TENUE** | Les dix-huit triplets compares deux a deux. Aucun doublon. Une seule paire partage deux objets sur trois (items 16 et 17, `cuillere` et `marmite`), ce que ma condition tolerait deja sur les items 1 et 2 du fichier repris. **Je ne deplace pas ce poteau** ; je le signale en `GRA-14` |
| `GRA-6` formulations propres a chaque grandeur | **TENUE** | Verifie sur les dix chaines d'enonce : longueur = long / court / grand / petit / haut / bas ; masse = lourd / leger ; contenance = plus d'eau / moins d'eau. **Aucune formulation ne sert deux grandeurs.** C'est le comptage d'etiquettes de ce paquet, et il est juste |
| `GRA-7` vocabulaire de contenance au niveau CP | **TENUE sur les enonces** | *"Ou peut-on mettre le plus d'eau ?"* et *"Ou peut-on mettre le moins d'eau ?"*. Les mots `contenance` et `contenir` n'apparaissent **nulle part** dans le paquet. C'est la route que j'avais illustree, et elle se lit au CP. **Deux endroits echappent a la lettre de ma condition** et font l'objet de reserves : le libelle `thermos` (`GRA-11`) et le titre du jeu (`GRA-12`) |
| `GRA-8` trois propositions | **TENUE, 18 sur 18** | Verifie dans le fichier et a l'ecran |
| `GRA-9` au moins 16 items | **TENUE** | 18 items, soit 12 items neufs et non 10 |
| `GRA-10` indice qui oriente sans nommer le gagnant | **TENUE, 18 sur 18** | Les dix-huit indices lus a l'ecran apres un echec. **Aucun ne nomme l'objet gagnant.** Le patron que j'avais indique est bien la (*"Imagine que tu remplis chaque objet avec de l'eau."*, *"Imagine que tu portes chaque animal dans tes bras."*). **Trois indices tiennent la lettre de la condition sans en servir l'esprit** : reserves `GRA-15` et `GRA-16` |

**Sur le libelle sans article, qui etait le point de forme le plus incertain.** Les six items de
contenance emploient `baignoire` plutot que `une baignoire`, route que j'avais explicitement
autorisee. Ce que j'avais dit verifier a l'audit, en plus du pli : *"que le raccourcissement des
libelles n'a pas transforme la comparaison en devinette"*. **Je l'ai verifie en jouant, et ce n'est
pas le cas** : comparer `baignoire`, `verre` et `cuillere` est la meme tache mathematique que
comparer `une baignoire`, `un verre` et `une cuillere`. L'article ne porte pas la comparaison.
**Cette verification-la n'est mecanisable par aucun script, et c'est moi qui l'ai faite.**

## 4. Le pli : mesure sur le paquet entier, pas sur un echantillon

Mesure du **2026-08-27**, a **744 x 551**, outil cite en section 1, releve
`squad/conformite/releves-s21/s21-nadia2-comparer-paquet-entier-744x551.json`. **Population : les 18
items du paquet, tous vus, en 4 parties. Ce n'est pas un echantillon.**

| Grandeur lue, apres un echec | Pire valeur sur les 18 items |
|---|---|
| Rangees de propositions | **1** |
| Lignes de l'enonce | **1** |
| Lignes de l'indice | **1** |
| Somme des trois libelles | **501,94 px** sur un budget de **522 px** (`un tabouret` / `une armoire` / `une table`), soit **96,2 %** |
| Bas du verdict | **407 px** |
| Bas de l'indice | **486 px**, soit **65 px au-dessus du pli de 551** |
| Defilement | **0** |

**18 items sur 18 sont entierement au-dessus du pli, verdict et indice compris.** C'etait le seul
des trois paquets ou le pli mordait, et il ne mord plus : `sofia-sm` a reecrit les deux libelles qui
tombaient a deux rangees, **et c'est la mesure qui a tranche, pas une estimation par nombre de
caracteres**. Je le confirme moi-meme sur le paquet entier.

**La marge est reelle mais mince sur deux items** : 501,94 px et 500,58 px contre un budget de
522 px, soit **20,06 px et 21,42 px de marge**. Un seul libelle articule plus long sur ces deux
items-la ferait replier la rangee. Ce n'est pas une reserve - le paquet passe - mais c'est le
chiffre a connaitre avant d'y toucher.

## 5. Reserves ouvertes a l'audit - **nouvelles et datees**, pas un durcissement

Aucune n'est bloquante. Le Done est ouvert. Elles sont pour `camille-po`, qui en fera des stories.

- **`GRA-11` (2026-08-28) - `thermos` n'est pas lisible par un lecteur de CP.** Item 18,
  `thermos` / `baignoire` / `piscine`. Le mot porte un `th` et un `s` final **prononce**, deux
  irregularites qu'un enfant de CP n'a pas apprises ; et beaucoup d'enfants de six ans ne savent pas
  ce qu'est un thermos. La reponse reste juste et l'item reste decidable pour qui connait l'objet :
  c'est la **lecture** qui bloque, pas la mathematique. `GRA-7` visait les enonces et non les
  libelles - **c'est un manque de ma specification, pas une faute de `sofia-sm`.** Correction
  possible en un mot (`gourde` est deja dans le paquet et se lit).
- **`GRA-12` (2026-08-28) - le titre du jeu emploie un mot d'adulte, sur la carte que l'enfant
  choisit.** L'accueil CP affiche *"Compare les grandeurs"* a cote de *"Compare les nombres"*
  (les dix cartes CP lues a l'ecran le 2026-08-27). Un enfant de CP doit donc distinguer deux cartes
  qui commencent par le meme mot, sur un mot - `grandeurs` - qu'il ne sait ni decoder (`eu`, `rs`
  final) ni interpreter. J'avais ecrit dans `GRA-7` que `contenance` etait un mot d'adulte ;
  `grandeurs` l'est autant, et il est a l'endroit le plus visible du jeu. **Ma condition ne couvrait
  pas le titre** : reserve nouvelle, pas un motif.
- **`GRA-13` (2026-08-28) - quatre chaines d'enonce servent trois items chacune, et l'enfant les
  relit.** Le paquet porte **dix chaines d'enonce distinctes pour dix-huit items** : *"Qu'est-ce qui
  est le plus lourd ?"*, *"...le plus leger ?"*, *"Ou peut-on mettre le plus d'eau ?"* et *"...le
  moins d'eau ?"* apparaissent **trois fois chacune**. Une partie de dix manches en tire donc
  regulierement deux identiques : je l'ai vu en jouant le 2026-08-27 - manches 1 et 6 toutes deux
  *"le plus lourd"*, manches 3 et 5 toutes deux *"le plus leger"*, manches 8 et 10 toutes deux
  *"le moins d'eau"*, soit **six manches sur dix portant un enonce vu deux fois**. **`GRA-6`
  n'exigeait pas une formulation par item**, seulement qu'aucune ne serve deux grandeurs : la
  condition est tenue et je ne la durcis pas retroactivement. Mais c'est de la monotonie de lecture,
  et c'est aussi ce qui casse l'outil de mesure du depot (section 1).
- **`GRA-14` (2026-08-28) - deux paires d'items sont proches au point de faire double emploi.**
  Items 8 et 10 : *"le plus leger"* avec `plume`/`caillou`/`brique` et avec
  `papillon`/`pierre`/`brique` - meme question, meme distracteur `brique`, et `pierre` et `caillou`
  sont le meme objet pour un enfant de six ans. Items 16 et 17 : meme grandeur, sens inverse, deux
  objets communs sur trois (`cuillere`, `marmite`). `GRA-5` n'interdisait que le triplet identique,
  et il est tenu ; **je ne deplace pas ce poteau**, je signale que le paquet gagnerait a ecarter ces
  quatre items les uns des autres.
- **`GRA-15` (2026-08-28) - un indice enseigne une regle fausse.** Item 9,
  `baleine`/`chat`/`sardine`, indice : *"Pense a la taille de chaque animal pour deviner son
  poids."* **La taille ne donne pas la masse**, et c'est precisement la confusion que la competence
  "comparer des masses" existe pour defaire : un gros ballon est leger, un petit caillou est lourd.
  Sur ce triplet-ci la regle donne la bonne reponse, ce qui la rend d'autant plus credible aux yeux
  de l'enfant. **L'indice n'apparait qu'apres un echec, sur 1 item de 18, et il ne rend pas la
  reponse fausse** : c'est pourquoi c'est une reserve et non un motif. La correction est dans le
  paquet lui-meme, a l'item 7 : *"Imagine que tu portes chaque animal dans tes bras."*
- **`GRA-16` (2026-08-28) - deux indices ne font que reformuler l'enonce.** Item 14 : enonce
  *"Ou peut-on mettre le moins d'eau ?"*, indice *"Pense a celui qui peut recevoir le moins d'eau
  avant de deborder."* Item 15 : la meme redite, au positif. Un enfant qui a echoue et qui lit cet
  indice n'apprend rien de neuf. `GRA-10` n'exigeait que de ne pas nommer le gagnant, et c'est
  tenu ; ce que j'avais ecrit sur la redite de la consigne appartient a `SEG-7`, pour un autre
  paquet, et **je ne le transporte pas retroactivement ici**.
- **`GRA-17` (2026-08-28) - la consigne du catalogue ne dit rien.** *"Regarde bien, puis compare."*
  n'apprend a l'enfant ni quoi comparer ni comment, la ou `cp-syllabes` dit *"Tape dans tes mains.
  Combien de syllabes ?"* et nomme le geste. Ici l'enonce porte toute la question et la consigne est
  du remplissage. Non bloquant : l'enonce suffit.

## 6. `SOR-4` - joue, et je la ferme

`SOR-4`, que j'avais ecrite le 2026-08-27 : *"la sortie ne doit pas devenir difficile a atteindre.
Ce que je verifierai a l'audit, en jouant : que `Les jeux` reste visible, lisible et pressable du
premier coup."* **C'est la seule des quatre que je pouvais fermer, et je la ferme.**

`ines-design` a pose la valeur (`0.5rem 1.1rem`) et verifie `SOR-1` a `SOR-3` au pixel.
`theo-archi` note que sur l'arbre fusionne l'action ne declare plus moins que la sortie, et que
**c'est la premiere fois que le garde-fou le dit en ayant lu la bonne declaration**. Rien de tout
cela ne dit qu'un enfant atteint la sortie ; c'est ce que j'ai mesure en la pressant.

**Mesure du 2026-08-27**, a **744 x 551**, **six cas** - les trois jeux neufs, chacun en cours de
manche **et** apres un echec ; releve
`squad/conformite/releves-s21/s21-nadia2-sor4-joue-744x551.json` :

| Ce que j'ai lu et presse | Valeur, identique sur les 6 cas |
|---|---|
| `.lien-accueil`, libelle | **"Les jeux"** |
| Boite rendue | **108,83 x 50,00 px** - `min-height` declare 44 px, **6 px au-dessus du plancher tactile**, exactement le chiffre d'`ines-design` |
| Rembourrage calcule | **8 px x 17,6 px** (soit `0.5rem 1.1rem`) ; bordure 2 px ; police **20 px** |
| Entierement dans le viewport | **oui** (haut 16 px, bas 66 px, aucun bord hors champ) |
| Defilement necessaire pour l'atteindre | **0** |
| **Centre de la boite libre** | **oui** - le point au centre exact rend **la sortie elle-meme**, rien ne la recouvre |
| Distance au bouton de reponse le plus proche | **212 px** - aucun risque d'appui de travers |
| **Premier appui** | ouvre l'ecran de confirmation : *"Si tu pars, ta partie s'arrete. / Continuer la partie / Les jeux"* |
| Sortie de la confirmation (`.retour-accueil`) | **108,83 x 50,00 px**, boite **identique** a celle de l'en-tete - **`SOR-3` tient a l'ecran, pas seulement dans la feuille** |
| **Second appui** | ramene aux **21 jeux** du catalogue |

**`SOR-4` est TENUE, sur les six cas.** La sortie est visible sans defiler, sa cible fait 50 px de
haut pour 108,83 de large, rien ne la recouvre, elle est loin des boutons de reponse, et **chaque
appui porte du premier coup** - le passage par la confirmation est voulu (`ADR-0017`, `S14-1`), ce
n'est pas un appui rate. La descente du rembourrage de 0,6 rem a 0,5 rem **n'a pas rendu la sortie
difficile a atteindre**. J'avais choisi de faire descendre la sortie plutot que de reprendre le gain
de `marc-dev` : **ce choix ne coute rien a l'enfant, et je le confirme en l'ayant presse.**

**Limite, avec l'etat exact qu'elle couvre** : mesure a **744 x 551 uniquement**, par un **clic
programme au centre geometrique de la boite**. Elle ne dit rien de **360 px**, ni d'un doigt qui
vise mal. **Sur ces deux points-la je reste sans mesure, et personne d'autre n'en a.**

## 7. `S21-2` et le pli du catalogue : ce que j'ai mesure, sur quelle population, et ce que je ne dis pas

**Le probleme que je devais lever.** J'avais confirme l'effet de `S21-2` **par item** (-8 px a plat,
-8 px par rangee) mais **pas le compte de 26**, etabli sur un **echantillon de 167 items sur 428** -
une partie de dix manches par jeu, sur des paquets de seize a quatre-vingt-dix-neuf items.
**C'etait un echantillon presente comme un compte**, et c'est le defaut que j'ai moi-meme trouve le
2026-08-27 et qui traversait cinq documents.

**Ce que j'ai mesure a la place.** Le **catalogue entier, item par item**, le 2026-08-27, a
**744 x 551**, viewport **lu du navigateur** ; releve commite
`squad/conformite/releves-s21/s21-nadia2-catalogue-entier-744x551.json`.

**Population : 478 items sur 478**, soit **20 jeux enumeres sur 21**. **Ce n'est pas un
echantillon.** Le vingt-et-unieme, `bandit-manchot-additions-cp`, est un paquet `additions-bornees`
genere et non enumere : **il n'est pas mesure, et je ne le fais couvrir par rien.**

| Etat du depot, meme arbre, sans aucune mutation de fichier | Items dont **l'indice** tombe sous le pli | Items dont le **verdict** tombe sous le pli |
|---|---|---|
| **Servi** (arbre fusionne de s21) | **64** sur 478 | **10** sur 478 |
| **Avant s21** (les trois declarations d'avant reinjectees) | **114** sur 478 | **15** sur 478 |
| **Ecart ferme** | **50** | **5** |

**Ce que ce chiffre dit, et ce qu'il ne dit pas.** Les deux etats ont ete obtenus **sur le meme
arbre**, en injectant ou non une feuille qui repose les trois valeurs d'avant s21
(`.score` a `0.5rem 0`, `.proposition` a `0.75rem 1.25rem`, les deux sorties a `0.6rem 1.1rem`).
**Les trois ont ete reinjectees ensemble : 50 est donc l'effet du travail vertical de s21 dans son
entier - `S21-2` et la descente de la sortie sous `SOR-1` a `SOR-4` reunies -, et non l'effet de
`S21-2` seul.** Je ne le decompose pas et **je n'attribue pas les 50 a `S21-2`**. Le chiffre de 26
portait sur `S21-2` seul et sur 167 items : les deux ne sont pas comparables, et remplacer l'un par
l'autre serait refaire l'erreur que je viens de corriger.

**Un fait qu'il faut connaitre pour ne pas se tromper de mesure** : sur cet arbre, l'outil du depot
qui mesure "avec et sans `S21-2`" rend **deux colonnes identiques au centieme**, parce que `S21-2`
**est deja dans la feuille servie** (`recreo.css`, `.score` ligne 1107, `.proposition` ligne 737,
relues le 2026-08-27). Une mesure "avec et sans" ne mesure plus rien sur cet arbre, et un lecteur
presse pourrait en conclure que `S21-2` est sans effet.

**Ce que la mesure du catalogue entier apprend, et qui n'est pas de mon lot du jour :**

- **Le pli est ferme sur CP et il ne l'est pas sur CM1.** Les **9 jeux CP enumeres, 162 items,
  n'ont plus aucun item sous le pli** (contre 22 avant s21). Les **64 items qui restent sont tous
  CM1**, sur 316. **Route a `camille-po`.**
- **La concentration** : `bandit-manchot-accord-sujet-verbe-cm1` **20 items sur 21**,
  `bandit-manchot-reperes-cm1` **16 sur 16**, `bandit-manchot-grands-nombres-cm1` **14 sur 28**,
  `bandit-manchot-accords-groupe-nominal-cm1` **8 sur 24**. Quatre jeux portent 58 des 64.
- **Ou s21 a le plus porte** : `bandit-manchot-problemes-additifs-cp` passe de **22 sur 22 a 0 sur
  22**, `bandit-manchot-perimetre-cm1` de **20 sur 22 a 1 sur 22**. Ces deux jeux font 41 des 50.
- **Une divergence entre deux de mes propres releves, que je signale sans l'exploiter.** Mon verdict
  du 2026-08-27 sur `cm1-reperes` porte *"verdict entierement visible sur 14 items sur 16"* ; la
  mesure du soir rend **le verdict au-dessus du pli sur 16 items sur 16** pour ce meme jeu. Les deux
  mesures n'emploient pas le meme outil et je ne sais pas laquelle des deux definitions est la plus
  etroite. **Je ne me sers d'aucune des deux pour bouger quoi que ce soit** : le
  `VALIDE SOUS RESERVE` de `cm1-reperes` est inchange, et la reconciliation est une suite ouverte.
  Un chiffre que je ne sais pas reconcilier ne doit pas servir a lever une reserve.

**Suite a router, trouvee en mesurant** : `squad/outils/nadia-prof/auditer-un-paquet-entier.py`
identifie un item par la seule chaine de son enonce et **s'arrete en erreur** sur tout paquet qui en
repete une (section 1). Le mode de defaillance est ici bruyant, donc visible ; **rien ne garantit
qu'il le soit toujours** - une table qui confond deux items peut aussi rendre un chiffre au lieu de
s'arreter, et ce depot a deja sanctionne plusieurs fois cette famille-la. A `theo-archi`, ou a qui
`sofia-sm` designera.

## 8. Sur quelles proprietes je reste la seule garde

- **Dix conditions, dont une seule partiellement tenue par un test.** La regle 1 du contrat de forme
  (`S21-5`) couvre la **clause de nombre** de `GRA-8`, et rien d'autre. **`GRA-1` a `GRA-7`, `GRA-9`
  et `GRA-10` n'ont aucun garde-fou et ne doivent pas en avoir** : un classifieur qui deciderait
  tout seul si une comparaison est decidable a six ans se tromperait, et une regle qui passe pour
  une mauvaise raison est pire qu'une regle absente.
- **La decidabilite des dix-huit comparaisons.** Personne d'autre ne les relit. C'est la propriete
  sur laquelle ce paquet est le meilleur des trois, et **rien dans la CI ne l'aurait dit** : la
  porte technique de s21 est verte a 722 tests, 0 echec.
- **Qu'un libelle raccourci pour tenir dans le budget de forme enseigne encore quelque chose**
  (section 3, dernier paragraphe). **Non mecanisable par aucun script**, et c'etait le risque
  principal de ce paquet.
- **Qu'un indice n'enseigne pas une regle fausse** (`GRA-15`). Aucun test ne lit le sens d'un indice.
- **Le pli a 744 x 551** et **`SOR-4`**, tous deux hors `mvn verify`.

## 9. Ce qui appartient a d'autres, et que je ne touche pas

- **La ligne 89 de `MATRICE-couverture.md`.** Un `VALIDE SOUS RESERVE` ouvre le Done pedagogique :
  **`camille-po` peut passer la ligne a `couvert-valide`.** Le geste est le sien, pas le mien, et je
  n'ai touche ni la matrice ni le catalogue.
- **Les sept reserves `GRA-11` a `GRA-17`** deviennent des stories par `camille-po`. Je produis des
  motifs, je n'ecris pas le backlog et je ne repriorise pas.
- **La correction de l'outil de mesure** (section 7) : a `theo-archi`.

---

## Verdict du 2026-08-28 (s21) - `S21-11`, "Compare les grandeurs"

## Verdict : **VALIDE SOUS RESERVE**

**Done pedagogique prononce.** `camille-po` peut passer la ligne 89 de `MATRICE-couverture.md` a
`couvert-valide`.

**Ce que j'ai verifie, et sur quoi** - increment **construit, servi et joue** le 2026-08-27 depuis
le worktree `s21-nadia-2`, sur `round-two` a `8e21613` :

- **Les dix conditions `GRA-1` a `GRA-10` sont tenues**, verifiees une par une en section 3.
- **`GRA-3`, la plus difficile, tient sur 18 items sur 18** : aucun triplet n'est indecidable pour
  un enfant de six ans sans instrument. C'est ce que j'avais demande, et c'est fait.
- **Aucune erreur factuelle. Aucun item n'a plus d'une reponse defendable.** C'est ce qui separe ce
  verdict des deux autres que je rends aujourd'hui.
- **Les trois grandeurs sont travaillees, 6 / 6 / 6**, et la contenance - absente sur 10 items sur
  10 du fichier repris - est ecrite et se lit au CP.
- **9 items sur 18 demandent "le moins"**, contre un tiers exige : un enfant qui ne lit pas
  l'adjectif se trompe une fois sur deux.
- **Le pli est tenu sur 18 items sur 18**, mesure sur le **paquet entier et non sur un echantillon**
  (744 x 551, 2026-08-27) : une rangee, un enonce d'une ligne, un indice d'une ligne, bas de
  l'indice a **486 px** pour un pli a 551, **zero defilement**. Marge la plus mince : **20,06 px**
  sur le budget de rangee de 522 px.
- **Le raccourcissement des libelles n'a pas transforme la comparaison en devinette** - la seule
  verification de ce paquet qu'aucun script ne peut faire, et je l'ai faite en jouant.

**Reserves, aucune bloquante**, a transformer en stories par `camille-po` : `GRA-11` le libelle
`thermos`, illisible par un lecteur de CP ; `GRA-12` le titre *"Compare les grandeurs"*, mot
d'adulte sur la carte que l'enfant choisit, a cote de *"Compare les nombres"* ; `GRA-13` quatre
enonces servis trois fois chacun, six manches sur dix en portant un vu deux fois ; `GRA-14` deux
paires d'items proches au point de faire double emploi ; `GRA-15` l'indice de l'item 9, qui enseigne
que la taille donne la masse - la confusion meme que la competence existe pour defaire ; `GRA-16`
deux indices qui ne font que reformuler l'enonce ; `GRA-17` une consigne de catalogue qui n'apprend
rien. **Aucune de ces reserves n'est un durcissement de `GRA-1` a `GRA-10`** : ce sont des choses
que je vois maintenant que le jeu existe, et deux d'entre elles (`GRA-11`, `GRA-12`) sont des
manques de ma propre specification, pas des fautes de `sofia-sm`.

**`SOR-4` est TENUE et je la ferme** (section 6) : sortie visible sans defiler, 108,83 x 50,00 px,
centre libre, 212 px du bouton de reponse le plus proche, chaque appui portant du premier coup, sur
les six cas mesures. **Limite : 744 x 551 seulement, par un clic programme au centre de la boite -
360 px et un doigt qui vise mal restent sans mesure, et personne d'autre n'en a.**

**Sur le pli du catalogue** (section 7) : j'ai remplace le compte d'echantillon par une mesure sur
**478 items sur 478**, et **50 items** sont passes au-dessus du pli entre l'etat d'avant s21 et
l'etat servi. **Ces 50 sont l'effet du travail vertical de s21 dans son entier, `S21-2` et la
descente de la sortie reunies : je ne les attribue pas a `S21-2` seul**, et ils ne remplacent pas le
26. **Le pli est desormais ferme sur les 9 jeux CP enumeres, 162 items, et les 64 items qui restent
sous le pli sont tous CM1** - route a `camille-po`.

- **Auditrice** : `nadia-prof`. **Verdict prononce le** 2026-08-28. **Mesures et parties** :
  2026-08-27, entre 22:02 et 22:35. **Arbre audite** : `round-two` a `8e21613`.
- **Audit non degrade** : l'application a ete construite, servie et **jouee**.
