# Arbitrage prealable - s21, `S21-8` : `cp-syllabes`, `cp-mot-manquant`, `cp-comparer`

- **Auteur** : `nadia-prof`. **Date** : 2026-08-27.
- **Saisie par** : `sofia-sm`, planning de s21 (`squad/sprints/s21-fermer-le-pli-avant-le-volume/planning.md`,
  commit `caad2f0`), story `S21-8`, sur le chemin critique de `S21-9`, `S21-10` et `S21-11`.
- **Perimetre** : les trois paquets de volume retenus en decision 3 du planning, puises dans
  `contenu-repris/` par `camille-po` (`DEC-0051`, section 4, niveaux 1 et 2).

## 0. Ce que ce document est, et ce qu'il n'est pas

**Ce n'est pas un Done pedagogique.** Aucun des trois paquets n'existe : il n'y a rien a valider et
rien a refuser. Ce document dit, paquet par paquet, **la forme que j'autorise, les conditions sous
lesquelles je l'autorise, ce que la matiere reprise vaut, ce qui doit en etre ecarte et ce qui doit
y etre ajoute**. Le Done viendra apres, sur l'increment servi et joue, et il peut encore etre
`REFUSE` : les conditions ci-dessous sont necessaires, elles ne sont pas suffisantes.

**Ce document existe parce que son absence a coute un refus.** A s19, un arbitrage prealable a evite
un cycle. A s20, il n'y en a pas eu sur `cm1-reperes`, le paquet a ete ecrit, livre, puis refuse sur
deux motifs **dont les deux causes racines etaient dans ma propre specification** : des libelles que
j'avais dits courts et qui faisaient 34 caracteres, et quatre libelles a qui je demandais de porter
quatre reperes plus le cas correct - il en fallait cinq
(`FICHE-bandit-manchot-reperes-cm1.md`, verdict du 2026-08-26, sections 2 a 4). Les deux fautes sont
des fautes de **comptage**, faites a l'ecriture, pas a la relecture. Ce document les traite en
comptant avant, et en le montrant.

**Ce document n'ecrit ni le contenu ni le backlog.** Les exemples qu'il contient sont des
**illustrations de faisabilite**, pas des libelles a recopier : je les ai fabriques uniquement pour
verifier que mes conditions sont tenables, et je les ai mesures pour le prouver. **A s20, `lea-dev`
a recopie mes libelles au caractere pres parce que je les avais ecrits ; elle a eu raison de suivre
ma specification, et c'est ma specification qui etait fausse.** Aucun exemple de ce document n'est
une obligation. Ce qui oblige, ce sont les conditions numerotees.

## 1. Ce que j'ai fait avant d'ecrire, et ce que ma methode ne couvre pas

**J'ai joue, puis j'ai mesure.** Les trois paquets n'existant pas, je ne pouvais pas les jouer :
j'ai joue le jeu CP servi le plus proche par la forme, `bandit-manchot-le-bon-son-cp` (trois
propositions, enonce d'un mot, indice apres echec), au viewport de reference de `DEC-0040`,
744 x 551, depuis le fat jar deja construit ; puis j'ai **substitue dans le document rendu** les
textes candidats des trois paquets et relu la geometrie reelle. Aucun `mvn` n'a ete lance, aucun
fichier de production n'a ete touche, et le serveur a ete arrete par son identifiant de processus.

Commande exacte de la mesure de forme, 2026-08-27, outil neuf commite dans ce meme geste :

```
CHROME=<navigateur> PORT_DEV=9351 python squad/outils/nadia-prof/mesurer-un-contenu-candidat.py \
    http://<hote>:<port> bandit-manchot-le-bon-son-cp \
    squad/conformite/releves-s21/s21-nadia-candidats-corpus.json \
    squad/conformite/releves-s21/s21-nadia-candidats-budget-de-forme-744x551.json
```

Releves commites, tous du 2026-08-27, tous a 744 x 551 :

| Releve | Ce qu'il porte |
|---|---|
| `releves-s21/s21-nadia-candidats-budget-de-forme-744x551.json` | boite offerte, budgets de rangee, largeur rendue de 96 textes candidats, 10 cas lus au pli |
| `releves-s21/s21-nadia-candidats-repli-de-rangee-744x551.json` | 15 triplets de libelles, recherche du seuil de repli de la rangee |
| `releves-s21/s21-nadia-candidats-bornes-verticales-744x551.json` | consigne, enonce et indice a une puis deux et trois lignes |
| `releves-s21/s21-nadia-candidats-avec-et-sans-s21-2-744x551.json` | les memes cinq configurations, avec et sans les deux declarations de `S21-2` injectees |
| `releves-s21/s21-nadia-candidats-journal-744x551.json` | la partie jouee, coup par coup, et les trois captures ci-dessous |
| `releves-s21/s21-nadia-le-bon-son-apres-un-echec-744x551.png` | ce que l'enfant voit apres un echec sur le jeu temoin |
| `releves-s21/s21-nadia-candidat-comparer-avec-article-744x551.png` | le repli de rangee de `cp-comparer` avec articles, a l'ecran |
| `releves-s21/s21-nadia-candidat-comparer-sans-article-744x551.png` | le meme item sans articles, sur une seule rangee |
| `releves-s21/s21-nadia-candidat-syllabes-744x551.png` | un item de `cp-syllabes` a trois libelles chiffres |

**Un ecart muet trouve pendant cette mesure, et corrige avant tout chiffre.** Ma premiere execution
a rendu des largeurs **3,2 fois trop petites, sans un mot d'erreur** : la mesure au canevas retombe
silencieusement sur `10px sans-serif` quand la police lui vient d'un element que htmx a detache du
document entre-temps. Je ne l'ai vu qu'en comparant a une valeur que j'avais deja commitee ce matin
(`quatre-vingt-seize mille deux cent trois` = 590,05 px). **L'outil commite verifie desormais ce
calibrage a chaque execution et s'arrete si l'ecart depasse le pixel** : sans ce controle, cette
mesure-ci aurait produit un faux vert. Aucun chiffre de ce document ne vient de l'execution fautive.

**Limites, nommees avec l'etat exact qu'elles couvrent :**

- **Un seul viewport, 744 x 551**, navigateur avec `--hide-scrollbars` - limite levee ce matin **a
  ce viewport et a lui seul** (`FICHE-bandit-manchot-reperes-cm1.md`, section 7). **360 px n'est pas
  couvert** et la conclusion de geometrie n'y vaut pas.
- **Les textes candidats sont mesures dans le document d'un autre paquet.** La geometrie mesuree est
  reelle, la chaine de chargement ne l'est pas : rien ici ne prouve que le chargeur lira ces items.
  **La porte reste la regle 4** - `itemsIndiceSousLePli` et `itemsVerdictSousLePli` vides sur
  l'increment servi. Ce document ne la remplace pas, il dit avec quelle marge on l'atteindra.
- **Aucun enfant n'a joue ces paquets** : ils n'existent pas. Tout ce qui suit sur la charge de
  lecture est un jugement de professeure, pas une observation.
- **L'outillage de mesure de ce repertoire s'attache au premier navigateur qui repond sur un port de
  deverminage fixe, sans verifier que c'est le sien** (defaut trouve par `theo-archi` le 2026-08-27,
  story `S21-1`, non corrige a l'heure de cette mesure). J'ai mitige en donnant a chaque execution un
  port distinct (9337, 9339, 9341, 9342, 9343, 9345, 9351) ; c'est une mitigation, pas une garantie.

## 2. Le cadre commun aux trois paquets : ce que le pli exige, en chiffres mesures

Ces chiffres valent pour les trois paquets et ne sont pas repetes ensuite.

### 2.1 La boite, lue dans le style calcule et non supposee (2026-08-27, 744 x 551)

| Element | Valeur lue |
|---|---|
| `.propositions` | largeur **680 px**, `display:flex`, `flex-wrap:wrap`, `column-gap:16px` |
| `.proposition` | rembourrage lateral **20 px + 20 px**, bordure **1 px + 1 px**, `box-sizing:border-box`, `flex:1 1 auto`, `min-width:44px`, police **700 32px** |
| `.enonce` | largeur offerte **696 px**, police **700 32px**, une ligne coute **48 px** |
| `.consigne` | largeur offerte **696 px**, police **400 20px**, une ligne coute **30 px** |
| `.indice` | largeur offerte **696 px**, police **400 20px**, interligne **30px**, une ligne de plus coute **30 px** |

**Budget de texte d'une rangee, calcule sur ces valeurs lues** : `680 - 3 x 42 - 2 x 16` =
**522 px pour trois libelles**, `680 - 2 x 42 - 16` = **580 px pour deux libelles**. C'est la
**somme** des trois largeurs de texte qui compte, pas la plus grande : les boutons sont elastiques.

**Ce budget est verifie, pas deduit.** 15 triplets mesures le 2026-08-27, 15 sur 15 conformes ; les
deux plus proches de la borne encadrent le chiffre au demi-pixel : **508,08 px reste sur une rangee**
(`une baignoire` + `une girafe` + `un caillou`), **522,27 px passe a deux rangees**
(trois fois `une cuillère`). Trois libelles de dix `a` (516,57 px) tiennent ; onze `a` (568,23 px)
ne tiennent plus.

### 2.2 Le budget vertical, mesure dans les deux etats du depot

`S21-2` (`marc-dev`) doit poser `.score{margin:0.25rem 0}` et `.proposition{padding:0.5rem 1.25rem}`.
J'ai mesure les memes configurations **avec ces deux declarations injectees et sans elles**, pour que
personne n'ait a projeter. Pli = 551 px.

| Configuration, trois propositions | Bas de l'indice, CSS servie | Bas de l'indice, avec `S21-2` |
|---|---|---|
| 1 rangee, consigne 1 ligne, enonce 1 ligne, indice 1 ligne | **505,19** (marge 45,81) | **489,19** (marge 61,81) |
| idem, indice **2 lignes** | **535,19** (marge 15,81) | **519,19** (marge 31,81) |
| idem, indice **3 lignes** | **565,19** (**deficit 14,19**) | non mesure |
| idem, **consigne 2 lignes** | **535,19** (marge 15,81) | non mesure |
| idem, **enonce 2 lignes** | **553,19** (**deficit 2,19**) | **537,19** (marge 13,81) |
| **2 rangees** (trois libelles au-dela de 522 px) | **595,19** (**deficit 44,19**) | **571,19** (**deficit 20,19**) |
| **2 rangees** (quatre propositions) | **589,19** (**deficit 38,19**) | **565,19** (**deficit 14,19**) |

L'ecart apporte par `S21-2` est **-8 px a plat, plus -8 px par rangee** : verifie sur les cinq
configurations mesurees dans les deux etats, identique aux chiffres qu'`ines-design` avait rendus et
que j'avais dits tenables ce matin. Les deux valeurs "non mesure" restent non mesurees et je ne les
fais pas couvrir par cette regularite.

### 2.3 Ce que ces chiffres autorisent, et ce qu'ils ferment

**J'ecris ce qui est autorise, parce que la formulation inverse m'a deja coute un levier.** A s20,
ma phrase *"je ne demande pas de toucher aux seize phrases"* a ete lue par `lea-dev` comme une
interdiction et lui a ferme le levier qui reglait `REP-9` ; je l'ai ecrit dans mon propre verdict du
2026-08-27. Donc, pour les trois paquets neufs :

1. **Trois propositions par item est autorise et suffisant ; quatre est ferme, et ce n'est pas une
   convention mais une mesure.** Quatre propositions font deux rangees a ce viewport, et l'indice
   tombe **38,19 px sous le pli** aujourd'hui, **14,19 px** encore apres `S21-2`. C'est la regle 1
   du contrat de forme, que j'ai autorisee ce matin comme regle encodable ; sur ces trois paquets,
   elle n'est pas une preference de forme, c'est la difference entre un indice lu et un indice
   perdu.
2. **Deux propositions sont autorisees** quand la competence n'en demande pas trois - c'est deja la
   forme de `cp-nature-mot` - et le budget de rangee monte alors a 580 px.
3. **Un enonce sur une ligne est le bon reflexe, pas un veto.** Il coute 48 px et c'est le levier le
   plus rentable ; mais **la barre est 696 px de texte rendu**, pas un nombre de caracteres. Le
   chiffre en caracteres ne suffit pas, et j'en ai la preuve dans mes propres mesures : **deux
   enonces de 38 caracteres exactement** mesurent **526,69 px** (`Le chien ___ quand il voit le
   facteur.`) et **551,80 px** (`Qu'est-ce qui contient le plus d'eau ?`), soit 25 px d'ecart a
   nombre de caracteres identique. Extrapole a 696 px, cela donne **48 a 50 caracteres**, quand
   j'avais ecrit "environ 47" ce matin sur une autre chaine de reference. **C'est le chiffre en
   pixels qui fait foi, et le comptage de caracteres n'est qu'un ordre de grandeur.**
   Un enonce a deux lignes n'est pas interdit : il coute 2,19 px de deficit aujourd'hui, et il
   repasse au-dessus du pli apres `S21-2`. **Je ne l'autorise pas pour autant a s21** : un paquet
   neuf ne doit pas naitre en dependant d'une autre story qui n'a pas encore fusionne.
4. **Un indice d'une ligne laisse 45,81 px de marge ; deux lignes en laissent 15,81 ; trois lignes
   passent sous le pli.** Donc : **deux lignes au plus, soit environ 1 392 px de texte rendu**, et
   une ligne quand la phrase le permet.
5. **Un libelle long n'est pas interdit en soi.** Ce qui est ferme, c'est un triplet dont la somme
   depasse 522 px. Un libelle de 200 px accompagne de deux libelles courts tient sans probleme :
   `une montagne` (219,25 px) + `un bol` + `un bol` = 411,35 px, une rangee, mesure.

## 3. `cp-syllabes` - francais CP, "Denombrer les syllabes orales d'un mot (segmentation syllabique)"

Ligne de referentiel : `squad/programme/francais/competences-cp.md`, domaine "Langage oral et
conscience phonologique". Etat dans `squad/programme/MATRICE-couverture.md`, ligne 485, verifie le
2026-08-27 : **`non couvert`**. Le domaine compte trois lignes CP et **aucune n'est couverte** : ce
paquet ouvrirait la premiere. L'annotation heritee de `main` qui declarait cette ligne couverte par
un jeu inexistant a ete supprimee du referentiel sur ma demande, et cette suite est close
(`FICHE-bandit-manchot-le-bon-son-cp.md`, section "Suites hors de ma main") ; la ligne est propre.

### 3.1 La question qui doit etre tranchee avant tout le reste : un support ecrit peut-il porter une competence orale ?

La competence dit **syllabes orales**. Le support est ecrit et l'application n'emet aucun son.
**J'ai refuse exactement ce genre de transposition il y a trois sprints** : le 2026-08-24, j'ai
refuse le proxy ecrit de "Decoder un mot regulier inconnu par assemblage syllabique"
(`ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`, section 3, conditions `SYL-1` a
`SYL-4`), au motif qu'un choix parmi des mots ecrits **affiche toujours le mot qui doit rester
inconnu**. Je dois donc dire pourquoi je ne refuse pas celui-ci, et le dire de facon qu'un tiers
puisse verifier que je ne me contredis pas.

**La difference est dans l'objet de la tache, pas dans mon humeur.**

- Pour l'**assemblage**, l'objet est la conversion des lettres en sons sur un mot que l'enfant n'a
  jamais vu. Montrer le mot detruit la tache : il ne reste qu'une silhouette a reconnaitre. Le
  defaut est structurel et aucune regle d'ecriture ne le repare. **Refuse, et ce refus tient.**
- Pour le **denombrement**, l'objet est de compter. Le mot est fait pour etre lu et dit ; la reponse
  est un **nombre**, pas un mot. Il n'y a rien a fuiter : montrer le mot est la donnee de l'exercice,
  pas sa reponse.

**La fuite reelle est ailleurs, et je la nomme parce qu'elle est le vrai prix de ce paquet.** Un
enfant peut compter les **groupes de lettres** au lieu des **syllabes entendues**. Sur les mots ou
l'ecrit et l'oral coincident, les deux routes donnent le meme nombre, et **ce paquet ne saura pas
distinguer les deux enfants**. Les conditions ci-dessous excluent precisement les mots ou les deux
routes divergent - ce qui ferme le risque d'erreur factuelle **et ferme du meme coup la seule
occasion de reperer la fuite**. J'accepte ce compromis, je l'ecris, et j'en tire une consequence de
statut : **cette ligne pourra passer `couvert-valide` sur ce paquet, mais le domaine "conscience
phonologique" ne sera pas traite pour autant** - deux de ses trois lignes restent hors d'atteinte
sans emission de son (`DEC-0042`, arbitrage 2, inchange).

### 3.2 Ce que la matiere reprise vaut, mot par mot

`contenu-repris/syllabes/cp-syllabes.yml` porte 14 mots. **Je ne lui accorde aucune anciennete** :
je les ai comptes moi-meme, et je les juge sur deux criteres - la coupe est-elle stable dans une
classe de CP, et le mot est-il dechiffrable par un lecteur de CP ?

| Mot repris | Compte declare | Mon avis | Motif |
|---|---|---|---|
| `chat`, `riz`, `loup` | 1 | **RETENUS** | coupe stable, lettres finales muettes courantes |
| `papa`, `lapin`, `bateau`, `cadeau`, `maison` | 2 | **RETENUS** | coupe stable, graphemes au programme (`ai`, `on`, `in`, `eau`) |
| `chocolat` | 3 | **RETENU** | coupe stable, `ch` au programme |
| `éléphant` | 3 | **ECARTE** | le compte est juste, mais `ph` n'est pas au programme de CP, et il arrive au milieu d'un mot de huit lettres : ce que l'item mesure devient le dechiffrage |
| `parapluie` | 3 | **ECARTE** | `pluie` vaut une syllabe a l'oral, deux sous les mains de beaucoup d'enfants : la coupe n'est pas stable, l'item n'a donc pas **une** bonne reponse |
| `dinosaure` | 3 | **ECARTE** | `e` muet final : 3 ou 4 selon l'usage de la classe. Meme motif |
| `ordinateur` | 4 | **RETENU sous reserve de la plage** (voir 3.3) | coupe stable |
| `hélicoptère` | 4 | **ECARTE** | `e` muet final **et** `h` muet initial : deux instabilites sur le meme mot |

**Bilan : 10 mots sur 14 sont repris, 4 sont ecartes, et aucun des 4 ne l'est pour une faute de
comptage de l'auteur d'origine** - les quatre comptes declares sont justes a l'oral standard. Ils
sont ecartes parce qu'ils ne sont pas **decidables** par un enfant de six ans, ce qui n'est pas la
meme chose.

**Ce que le fichier repris n'apporte pas et qu'il faut ecrire** : les distracteurs. Le fichier de
`main` les calculait ("nombres voisins") ; **`round-two` n'a pas de mode de ce genre**. Le chargeur
ne connait que deux types de contenu, `question` (items enumeres, `distracteurs` obligatoire item par
item) et `additions-bornees` (`YamlContentPackLoader`, verifie le 2026-08-27). Le planning de s21
decrit `cp-syllabes` comme reprenant un "mode pool deja present dans le fichier" : c'est vrai du
fichier de `main`, **il n'y a rien de tel a reprendre dans `round-two`**, et chaque item devra porter
ses deux distracteurs en toutes lettres. Ce n'est pas un obstacle, c'est une ligne de plus par item ;
je le signale pour que `marc-dev` ne cherche pas une fonction qui n'existe pas.

### 3.3 Conditions, `SEG-1` a `SEG-9`

- **`SEG-1`** - **Chaque item porte une coupe stable.** Sont exclus : les mots dont la forme ecrite
  se termine par un `e` muet, les mots contenant une semi-voyelle en fin de mot (`-uie`, `-ie`,
  `-ion` quand il fait diphtongue), et les mots ou un `e` interieur peut disparaitre a l'oral
  (`samedi` se dit couramment en deux syllabes). **Motif** : un item dont deux comptes sont
  defendables n'a pas une bonne reponse, il en a deux, et c'est une erreur factuelle.
- **`SEG-2`** - **Chaque mot est dechiffrable par un lecteur de CP** : graphemes du referentiel
  (voyelles, consonnes simples, `ch`, `ou`, `on`, `an`, `in`, `oi`, `au`/`eau`), pas de `ph`, pas de
  `gn`, pas de lettre muette initiale. **Motif** : un mot que l'enfant ne peut pas lire mesure son
  dechiffrage, pas son denombrement.
- **`SEG-3`** - **La plage des reponses est 1, 2 et 3, et les trois propositions sont ces trois
  memes valeurs a chaque item.** **Motif de comptage, fait avant et non apres** : trois etiquettes
  doivent porter trois reperes et aucun cas supplementaire ; 3 = 3, la specification tient. C'est
  exactement le comptage que j'ai rate a s20, ou quatre etiquettes devaient porter quatre reperes
  **plus** le cas correct ; ici il n'y a pas de cas correct a loger, l'etiquette **est** la reponse.
  Un jeu de propositions constant est aussi la charge de lecture la plus faible possible au CP.
  **Cout que je nomme et que j'assume** : les mots de quatre syllabes sortent du paquet, `ordinateur`
  avec eux. Le referentiel ne nomme aucune plage ; 1 a 3 est la plage de travail ordinaire du CP.
- **`SEG-4`** - **La plage 1 a 4 reste autorisee**, a une condition qui se verifie par comptage :
  aucun sous-ensemble de trois valeurs ne doit determiner la reponse. Si les distracteurs sont pris
  mecaniquement chez les voisins, le triplet affiche renseigne sur la reponse sans qu'on compte quoi
  que ce soit. **`SEG-3` est la route la moins risquee ; `SEG-4` est ouverte et je la mesurerai comme
  le reste.** L'auteur choisit.
- **`SEG-5`** - **Au moins 16 items.** Une partie tire 10 manches sans remise dans le paquet
  (`ListContentSource.next`, verifie le 2026-08-27) : sous 16 items, deux parties successives
  montrent presque le meme jeu.
- **`SEG-6`** - **Aucune valeur de reponse n'est correcte sur plus de la moitie des items, et chacune
  l'est sur au moins un quart.** Meme barre que `REP-4`, que je ne deplace pas. Sur 16 items, cela
  fait entre 4 et 8 items par valeur. **Motif** : sans cela, appuyer toujours sur le meme bouton
  devient une strategie.
- **`SEG-7`** - **Chaque item porte un indice non vide qui apporte quelque chose sans donner le
  compte.** Ce que l'indice ne peut pas faire : segmenter le mot, puisque compter les morceaux
  affiches donne la reponse. Une route qui marche pour les mots de deux et trois syllabes : **nommer
  la premiere syllabe seulement** - *"Ce mot commence par « cho ». Continue a taper."* ne dit ni 2 ni
  3. **Elle ne marche pas pour les mots d'une syllabe**, ou nommer la premiere syllabe nomme le mot
  entier : ces items-la prendront un indice de procede. **Je ne dicte pas la formule** : je verifierai
  item par item, a l'audit, qu'aucun indice ne donne le compte et qu'aucun ne se contente de repeter
  la consigne.
- **`SEG-8`** - **La consigne du catalogue nomme le geste oral.** `cp-le-bon-son` dit *"Dis le mot.
  Quel son entends-tu ?"* et c'est le bon patron : la consigne est le seul endroit ou l'on peut dire
  a l'enfant de passer par sa voix. Mesure : *"Tape dans tes mains. Combien de syllabes ?"* fait
  **382,48 px** sur 696, donc une ligne. **Illustration, pas obligation.**
- **`SEG-9`** - **Si les mots sont ecrits en capitales** - c'est la forme de `cp-le-bon-son`, et elle
  se defend au CP - **les accents sont conserves sur les capitales**. Le contenu lu par un enfant est
  integralement accentue (`DEC-0002`, amendement du 2026-08-04).

### 3.4 Ce que le pli exige de ce paquet, en chiffres

Ce paquet est le plus a l'aise des trois. Mesure du 2026-08-27, cas
`syllabes - mot court, 3 libelles chiffres, indice 1 ligne` :

- **libelles** `1`, `2`, `3` : **18,41 px chacun, 55,23 px au total, soit 10,6 % du budget de
  522 px.** La rangee ne peut pas se replier, quel que soit le mot.
- **enonce** : le plus large mot que j'aie mesure, `HELICOPTERE` en capitales sans accent, fait
  **205,25 px** sur 696 ; il sert de borne haute et rien d'autre, puisque `SEG-1` l'ecarte du paquet
  et que `SEG-9` l'ecrirait de toute facon accentue, ce que je n'ai pas mesure. `CHOCOLAT` fait
  **168,67 px**. **Aucun mot francais de trois syllabes n'approche la borne** : une ligne, toujours.
- **indice** : *"Tape dans tes mains a chaque morceau du mot."* = **418,69 px** sur 696, une ligne.
  Deux lignes restent autorisees, soit environ **1 392 px de texte rendu**.
- **resultat mesure** : bas de l'indice a **505,19 px**, soit **45,81 px de marge** sous le pli,
  **61,81 px apres `S21-2`**. Capture : `s21-nadia-candidat-syllabes-744x551.png`.

**Un seul cas fait tomber ce paquet sous le pli : quatre propositions** (`SEG-3` l'exclut) - mesure
a **589,19 px**, 38,19 px de deficit.

## 4. `cp-mot-manquant` - francais CP, "Utiliser des mots precis pour designer des objets, actions, sensations du quotidien"

Ligne de referentiel : `competences-cp.md`, domaine "Vocabulaire". `MATRICE-couverture.md`,
ligne 496, verifie le 2026-08-27 : **`non couvert`**.

### 4.1 Ce que la matiere reprise vaut : la forme, oui ; les distracteurs, non

`contenu-repris/textes-trous/cp-mot-manquant.yml` porte 5 items. Je les ai lus un par un.

**La forme - une phrase a trou, un mot a choisir - est la bonne forme pour cette ligne, et je
l'autorise sans reserve.** Elle se transpose au schema `question` sans mecanique neuve.

**La matiere, elle, ne travaille pas la competence annoncee.** Sur les **15 distracteurs** des cinq
items, **aucun n'oblige a choisir un mot precis** : ils s'eliminent tous soit par incompatibilite
grossiere de sens, soit par la grammaire.

| Item repris | Distracteurs | Ce qui les elimine sans vocabulaire |
|---|---|---|
| `Le chat boit du ___.` (lait) | pain, vélo, chien | on ne boit ni pain, ni vélo, ni chien : incompatibilite grossiere, et deux des trois ne se construisent meme pas avec `du` |
| `Je dors dans mon ___.` (lit) | bol, chat, arbre | idem |
| `Le soleil est dans le ___.` (ciel) | sac, pied, livre | idem |
| `On écrit avec un ___.` (crayon) | ballon, gâteau, nuage | idem |
| `Le poisson nage dans l'___.` (eau) | air, herbe, feu | **`feu` s'elimine par la grammaire seule** : `l'` impose une initiale vocalique. Les deux autres, par incompatibilite grossiere |

**Bilan : 2 phrases sur 5 sont reprenables telles quelles** (`Le chat boit du ___.` et
`On écrit avec un ___.`, dont le cadre syntaxique est sain), **3 sont a jeter**, et **0 distracteur
sur 15 est reprenable**. Ce paquet n'est donc **pas une reprise** : c'est une ecriture neuve qui
emprunte deux cadres de phrase. Je le dis en chiffres pour que `camille-po` et `sofia-sm` ne
l'estiment pas comme une transposition - le planning le classe aujourd'hui en "transposition
directe", et sur la matiere ce n'en est pas une.

**Ce que le paquet repris travaille reellement** : comprendre le sens general d'une phrase tres
simple. C'est une competence voisine, plus facile, et elle appartient a un autre domaine du
referentiel ("Lecture et comprehension de l'ecrit"). **Un paquet ecrit sur ce patron ne pourrait pas
faire passer la ligne "Vocabulaire" a `couvert-valide`**, et c'est exactement le glissement que je
dois arreter ici plutot qu'a l'audit.

### 4.2 Conditions, `PRE-1` a `PRE-8`

- **`PRE-1`** - **Les trois propositions sont toutes admissibles grammaticalement dans le trou** :
  meme genre, meme nombre, meme classe de mot, compatibles avec le determinant, l'elision ou la
  preposition qui precede. **Motif** : des qu'une proposition ne "colle" pas grammaticalement, elle
  s'elimine sans vocabulaire, et l'item devient un exercice de grammaire deguise - c'est le defaut
  exact de l'item `Le poisson nage dans l'___.`
- **`PRE-2`** - **Au moins deux des trois propositions appartiennent au meme champ que la bonne
  reponse**, de sorte que le choix se fasse sur la precision et non sur l'absurdite. **Motif** : un
  distracteur absurde transforme un exercice en devinette ; c'est un principe que je tiens depuis le
  debut de ce produit et les 15 distracteurs repris le violent tous.
- **`PRE-3`** - **Aucun item n'a de reponse defendable autre que celle attendue.** Verification a
  faire item par item par l'auteur, avant livraison, en essayant de justifier chaque distracteur.
- **`PRE-4`** - **Les trois familles nommees par la competence sont representees : objets, actions,
  sensations.** Aucune en dessous d'un quart des items. **Motif** : la ligne du referentiel les nomme
  toutes les trois ; un paquet qui ne travaille que les objets couvre un tiers de la ligne, et ce
  produit a deja ete repris plusieurs fois sur cette famille de couverture partielle. **J'ai verifie
  que la barre est tenable avant de la poser** : les trois familles se logent dans une phrase a trou
  au CP (un nom d'objet, un verbe d'action, un adjectif de sensation), et les trois propositions
  restent alors de meme classe, ce que `PRE-1` exige.
- **`PRE-5`** - **Au moins 16 items** (meme motif que `SEG-5`).
- **`PRE-6`** - **Trois propositions par item.** Le fichier repris en porte quatre ; il faut en
  retirer une. **Ce n'est pas un appauvrissement** : `PRE-2` demande des distracteurs plus proches,
  et deux distracteurs plausibles valent mieux que trois absurdes.
- **`PRE-7`** - **Chaque item porte un indice non vide qui oriente vers le sens sans nommer la
  reponse ni la decrire par un synonyme evident.** Le defaut a eviter porte deja un nom dans ce
  depot : `M21`, un indice qui nomme la reponse est une revelation deguisee, et je l'ai retrouve a
  s20 sur deux items de `cm1-reperes`.
- **`PRE-8`** - **Le trou est marque de facon constante et lisible.** Le fichier repris emploie
  `___` ; je ne demande pas d'en changer, et je ne l'impose pas non plus. Ce que je verifierai a
  l'ecran : que la marque se distingue nettement d'un mot et qu'elle ne se confond pas avec un trait
  de soulignement du gabarit.

### 4.3 Ce que le pli exige de ce paquet, en chiffres

- **libelles** : le plus large candidat mesure, `fourchette`, fait **157,83 px** ; les triplets
  illustres font **165,58 px** (`lait`/`jus`/`sirop`) et **247,98 px** (`aboie`/`miaule`/`bêle`),
  soit **32 % et 48 % du budget de 522 px**. Un mot francais courant de CP ne met pas cette rangee
  en danger : il faudrait trois mots de plus de dix lettres dans le meme item.
- **enonce** : c'est la seule contrainte reelle. La phrase entiere, trou compris, doit tenir en
  **696 px**. Mesures : `Le chat boit du ___.` = 281,92 px ; `On écrit avec un ___.` = 300,17 px ;
  `Le chien ___ quand il voit le facteur.` = **526,69 px pour 38 caracteres**. **La borne est donc
  atteinte vers 48 a 50 caracteres de cette densite**, et l'auteur doit mesurer plutot que compter.
- **indice** : *"Un seul de ces trois mots se boit."* = 290,30 px ; *"Pense a ce que le chien fait
  avec sa voix."* = 352,23 px. Une ligne. **Deux lignes restent autorisees.**
- **resultat mesure**, cas `mot-manquant - enonce long (38 car.), 3 libelles` : bas de l'indice a
  **505,19 px**, **45,81 px de marge**.

## 5. `cp-comparer` - mathematiques CP, "Comparer des longueurs, des masses, des contenances (sans instrument gradue)"

Ligne de referentiel : `squad/programme/mathematiques/competences-cp.md`, domaine "Grandeurs et
mesures". `MATRICE-couverture.md`, ligne 89, verifie le 2026-08-27 : **`non couvert`**.
`sofia-sm` le nomme comme le plus fragile des trois, et elle a raison ; je trouve **deux
defauts de plus** que les deux qu'elle nomme.

### 5.1 Ce que la matiere reprise vaut : le tri, item par item

`contenu-repris/quiz/cp-comparer.yml` porte 10 items. Je les ai comptes moi-meme.

| # | Item repris | Mon avis | Motif |
|---|---|---|---|
| 1 | le plus **long** : règle / crayon / gomme | **RETENU** | longueur, decidable sans mesurer |
| 2 | le plus **court** : crayon / règle / trousse | **A REECRIRE** | **item indecidable** : selon les exemplaires qu'un enfant a sous les yeux, une trousse d'ecolier est plus longue ou plus courte qu'un crayon. Le triplet n'a pas **une** bonne reponse, et le paquet en declare pourtant une |
| 3 | le plus **lourd** : éléphant / souris / fourmi | **RETENU** | masse |
| 4 | le plus **leger** : plume / caillou / brique | **RETENU** | masse |
| 5 | le plus **grand** : girafe / chat / lapin | **RETENU** | longueur (taille) |
| 6 | le plus **petit** : fourmi / chien / cheval | **RETENU** | longueur (taille) |
| 7 | quel nombre est le plus grand : 7 ou 4 | **A RETIRER** | **hors sujet** : comparaison de nombres, ligne differente du referentiel |
| 8 | quel nombre est le plus petit : 3 ou 9 | **A RETIRER** | idem |
| 9 | le plus **haut** : montagne / maison / chaise | **RETENU** | longueur (hauteur) |
| 10 | qui va le plus **vite** : voiture / escargot / tortue | **A RETIRER** | **hors sujet, et ce troisieme-la n'etait pas nomme** : la vitesse n'est ni une longueur, ni une masse, ni une contenance |

**Bilan : 6 items sur 10 sont retenus, 3 sont hors sujet et non 2, 1 n'est pas decidable, et la
contenance est absente sur 10 items sur 10.** Le planning et `DEC-0051` annoncent deux items a
trier ; **il y en a trois**. C'est le genre de comptage que seul un humain fait, et c'est celui que
j'avais rate a s20 : je le refais ici avant le code plutot qu'apres.

**Repartition des 6 items retenus** : longueur 4, masse 2, contenance 0. **La competence nomme trois
grandeurs ; le paquet repris en travaille deux, et l'une d'elles deux fois seulement.**

### 5.2 Conditions, `GRA-1` a `GRA-10`

- **`GRA-1`** - **Aucun item ne compare autre chose qu'une longueur, une masse ou une contenance.**
  Sont exclus les nombres (items 7 et 8), **la vitesse (item 10)**, ainsi que les durees, les prix et
  les quantites d'objets. **Motif** : une ligne de referentiel ne se couvre pas avec une grandeur
  qu'elle ne nomme pas.
- **`GRA-2`** - **Les trois grandeurs sont representees, aucune sous un quart des items.** Sur 16
  items, au moins 4 par grandeur. **Motif** : la competence les nomme toutes les trois ; sans cela
  la ligne ne peut pas passer `couvert-valide`, et ce serait la quatrieme couverture partielle de ce
  produit.
- **`GRA-3`** - **Chaque triplet est decidable par un enfant de six ans sans aucun instrument.** Si
  deux des trois objets peuvent etre ranges dans les deux sens selon l'exemplaire qu'on imagine
  (`un crayon` et `une trousse`), l'item est refuse. **Motif** : une erreur factuelle dans un contenu
  scolaire est un motif de refus a elle seule, et un item indecidable en est une.
- **`GRA-4`** - **Au moins un tiers des items demandent le plus petit, le plus leger, le plus court
  ou le moins.** **Motif verifie sur le paquet repris** : les objets choisis sont des extremes
  familiers, et un enfant qui ne lit pas l'adjectif et designe toujours "le plus gros" obtient
  **4 items sur les 6 retenus** (le plus long, le plus lourd, le plus grand, le plus haut).
  L'alternance oblige a lire l'enonce.
- **`GRA-5`** - **Deux items ne partagent pas le meme triplet d'objets.** **Motif** : sinon l'enfant
  memorise une position plutot qu'une comparaison. Le paquet repris s'en approche deja (items 1 et 2
  partagent `règle` et `crayon`).
- **`GRA-6`** - **Chaque grandeur a ses propres formulations d'enonce, et aucune formulation ne sert
  deux grandeurs differentes.** **C'est le comptage d'etiquettes de ce paquet-ci** : les libelles
  sont propres a chaque item, il n'y a donc pas de jeu fixe a verifier cote boutons, mais il y en a
  un cote enonces. Si `le plus grand` sert une fois pour une taille et une fois pour une contenance,
  la distinction que la competence demande disparait. **Verification faite : les six enonces retenus
  n'ont aujourd'hui aucun recouvrement de ce genre.**
- **`GRA-7`** - **Le vocabulaire de la contenance est celui d'un enfant de CP.** `contenance` est un
  mot d'adulte. *"Où peut-on mettre le plus d'eau ?"* se lit au CP ; *"Qu'est-ce qui contient le plus
  d'eau ?"* est deja plus dur (`contenir`). **Illustration, pas obligation** : la barre est qu'un
  enfant de CP lise l'enonce seul.
- **`GRA-8`** - **Trois propositions par item.** Les items 7 et 8 du fichier repris n'en portent que
  deux ; ils sortent de toute facon par `GRA-1`.
- **`GRA-9`** - **Au moins 16 items** (meme motif que `SEG-5`). Cela suppose **10 items neufs au
  moins** : 6 retenus, 1 reecrit, et la contenance entierement a ecrire.
- **`GRA-10`** - **Chaque item porte un indice non vide qui oriente la comparaison sans nommer
  l'objet gagnant.** Le patron qui marche : rappeler la grandeur en jeu et le geste
  (*"Imagine que tu remplis chaque objet avec de l'eau."*, mesure a 448,44 px, une ligne).

### 5.3 Ce que le pli exige de ce paquet, en chiffres - et c'est le seul des trois ou le pli mord

Les libelles de ce paquet sont des **groupes nominaux**, pas des mots isoles. C'est la que le budget
de 522 px se joue, et j'ai mesure les deux formes.

| Triplet, mesure le 2026-08-27 | Somme des libelles | Rangees | Bas de l'indice |
|---|---|---|---|
| `une règle` + `un crayon` + `une gomme` | 469,86 px | 1 | **505,19** (marge 45,81) |
| `un éléphant` + `une souris` + `une fourmi` | 500,58 px | 1 | **505,19** (marge 45,81) |
| `une baignoire` + `une bouteille` + `une cuillère` | **580,40 px** | **2** | **595,19 (deficit 44,19)** |
| `baignoire` + `bouteille` + `cuillère` | 385,82 px | 1 | **505,19** (marge 45,81) |

**Le triplet de contenance le plus naturel ne passe pas avec ses articles**, et il ne passe toujours
pas apres `S21-2` (**571,19 px, deficit 20,19**, mesure). Les deux captures sont commitees :
`releves-s21/s21-nadia-candidat-comparer-avec-article-744x551.png` et
`releves-s21/s21-nadia-candidat-comparer-sans-article-744x551.png`.

**Ce que j'autorise, et je l'ecris comme une autorisation et non comme un interdit :**

1. **Les articles ne sont pas interdits.** Ce qui est ferme, c'est la somme au-dela de **522 px**.
   Un triplet articule de noms courts passe largement : `un bol` + `un verre` + `un seau` =
   **336,12 px**, soit 64 % du budget.
2. **Les libelles nus sont autorises** et reglent la contenance sans rien couter a la competence :
   comparer `baignoire`, `bouteille` et `cuillère` est la meme tache que comparer `une baignoire`,
   `une bouteille` et `une cuillère`. **La comparaison est mathematique ; l'article ne la porte pas.**
3. **Un libelle long reste possible s'il est accompagne de deux courts** : `une montagne`
   (219,25 px) + `un bol` + `un bol` tient sur une rangee, mesure.
4. **L'auteur choisit ; la barre est la somme de 522 px et la regle 4.** Ce que je verifierai a
   l'audit, en plus du pli : que le raccourcissement des libelles n'a pas transforme la comparaison
   en devinette. Un contrat de forme ne doit jamais produire un paquet qui passe la porte en
   n'enseignant plus rien, et **cette verification-la n'est mecanisable par aucun script**.

Cinq libelles articules parmi les candidats naturels depassent **a eux seuls** le tiers du budget
(176 px) : `une montagne` 219,25 - `une baignoire` 208,45 - `une bouteille` 197,86 - `une gomme`
180,17 - `un éléphant` 179,41. **`une gomme` est dans le tout premier item du fichier repris** : la
reprise litterale, avec articles, depasse donc le tiers du budget des son premier item, de
4,17 px. Elle passe malgre tout
(469,86 px de somme), et c'est precisement pourquoi la regle utile est la somme et non le libelle.

## 6. Corrections que je porte contre moi, datees

1. **Mon budget de rangee du matin etait exprime par libelle ; il est faux comme critere de repli.**
   J'ai ecrit le 2026-08-27, a 14:23, *"un libelle de proposition a 176 px de texte utile a trois
   colonnes"*. **Le chiffre est juste - c'est bien le tiers du budget, et je le retrouve ici a
   l'identique - mais il ne dit pas quand la rangee se replie.** Trois libelles de 174,09 px, tous
   **sous** 176, se replient (mesure du 2026-08-27) ; un libelle de 219,25 px, **au-dessus** de 176,
   ne se replie pas s'il est accompagne de deux courts. **Le critere de repli est la somme des trois,
   contre 522 px.** Je ne retire pas le chiffre de 176 px, qui reste la bonne facon de dire ce que
   coute un libelle en moyenne ; j'ajoute celui qui gouverne reellement le repli. La correction est
   de moi et contre moi.
2. **Le contrat de forme ne devient pas plus severe pour autant, et je ne deplace pas mes poteaux.**
   La regle 2 (libelle de 10 caracteres) reste **refusee comme veto**, exactement comme ce matin :
   ce que je pose ici est un budget de somme, pas un plafond par libelle, et il n'ecarte aucune
   competence. Si un paquet futur ne peut pas tenir 522 px de somme parce que sa competence impose
   trois libelles longs, c'est la competence qui gagne et l'auteur ferme la regle 4 autrement -
   deux propositions au lieu de trois, ce qui porte le budget a 580 px.
3. **Le planning classe `cp-mot-manquant` en "transposition directe".** Sur la forme, c'est exact.
   Sur la matiere, **0 distracteur sur 15 est reprenable** : ce n'est pas une transposition, c'est
   une ecriture neuve. Je ne modifie pas le planning, qui n'est pas a moi ; je donne le chiffre a
   `sofia-sm` pour qu'elle sache que la story `S21-10` n'est pas moins chere que `S21-11`.

## 7. Sur quelles proprietes je reste la seule garde

Je les nomme pour que personne ne croie couvert ce qui ne l'est pas. **Quatre defauts visibles par un
enfant ont deja traverse une CI entierement verte sur ce produit.**

- **27 conditions, dont 3 seront partiellement tenues par un test et 24 pas du tout.** Le seul
  garde-fou qui en attrapera quelque chose est celui de `S21-5`, la regle 1 du contrat de forme
  (au plus trois propositions par item) : il couvre la clause de nombre de `SEG-3`, `PRE-6` et
  `GRA-8`, et rien de plus - ni la plage des reponses, ni la constance du jeu de propositions.
  **Les 24 autres conditions ne doivent pas devenir des tests**, et je le dis pour qu'on ne me le
  demande pas : un classifieur qui deciderait tout seul si un distracteur est plausible ou si une
  coupe syllabique est stable se tromperait, et une regle qui passe pour une mauvaise raison est
  pire qu'une regle absente - c'est la position que `lea-dev` a defendue a s20 et que je partage.
  **La contrepartie est que j'en suis le seul garde-fou.**
- **La suffisance d'un jeu d'etiquettes.** Fait pour `cp-syllabes` en `SEG-3` (3 etiquettes, 3
  reperes, aucun cas supplementaire) et pour `cp-comparer` en `GRA-6` (le jeu fixe y est du cote des
  enonces, pas des boutons). **Aucun test ne le verifie**, et c'est le comptage sur lequel je me suis
  trompee a s20.
- **L'exactitude du contenu.** Les comptes de syllabes, la decidabilite des comparaisons, la
  plausibilite des distracteurs : personne d'autre ne les relit.
- **Qu'un paquet qui passe la porte de forme enseigne encore quelque chose.** Non mecanisable.
- **Le pli a 744 x 551.** Aucun test du depot ne mesure ce que l'enfant voit sans defiler ; la
  regle 4 s'appuie sur un outillage a navigateur, hors `mvn verify`.

## 8. Obstacles routes, avec leur destinataire

| Obstacle | Destinataire | Ce que je demande |
|---|---|---|
| Le retour apres une **bonne** reponse ne dit rien d'autre que "Bravo !" - joue et capture le 2026-08-27. Pour `cp-syllabes`, l'enseignement **est** la segmentation, et elle a sa place sur l'ecran de retour, pas dans un indice qui donnerait le compte. Or le champ `explication` (`ADR-0024`) est aujourd'hui **verrouille derriere `graphie`** : `ChaquePaquetExpliqueSesItemsAVariationTest` fait rougir tout paquet qui porte une explication sans declarer une graphie sur chacun de ses items. Declarer `graphie: "cho-co-lat"` sur un paquet de syllabes ferait passer le garde-fou **en detournant le sens d'un champ de phonologie** : je ne l'autorise pas. | `theo-archi`, `camille-po` | **Constat, pas story de s21.** Dire si un paquet peut porter une explication hors du regime `graphie`. Tant que non, `cp-syllabes` n'a pas de retour pedagogique apres une bonne reponse, et je le compte comme une reserve ouverte, pas comme un motif de refus. |
| Le planning decrit `cp-syllabes` comme reprenant un "mode pool deja present dans le fichier". Ce mode est celui de `main` ; `round-two` n'a que `question` et `additions-bornees`. | `sofia-sm` | Une phrase de planning, pas un blocage. Chaque item portera ses deux distracteurs en toutes lettres. |
| `DEC-0051` et le planning annoncent **deux** items hors sujet dans `cp-comparer` ; il y en a **trois** (la vitesse). La story `S21-11` est donc un peu plus chere que chiffree. | `camille-po`, `sofia-sm` | Prendre le chiffre corrige. Aucune reecriture de decision demandee. |
| Le domaine "Langage oral et conscience phonologique" comptera **une ligne couverte sur trois** apres `cp-syllabes` ; les deux autres restent hors d'atteinte sans emission de son (`DEC-0042`, arbitrage 2). | `camille-po` | Ne pas lire l'ouverture de cette premiere ligne comme l'ouverture du domaine. |

## 9. Conclusion

**Les trois paquets sont autorises a etre ecrits, sous les conditions numerotees ci-dessus** :
`SEG-1` a `SEG-9` pour `cp-syllabes`, `PRE-1` a `PRE-8` pour `cp-mot-manquant`, `GRA-1` a `GRA-10`
pour `cp-comparer`. `S21-9`, `S21-10` et `S21-11` peuvent ouvrir.

**Aucun des trois n'est valide pedagogiquement par ce document, et aucun ne le sera avant d'avoir
ete joue.** Les conditions sont des necessaires, pas des suffisants : un paquet qui les tient toutes
peut encore etre refuse sur ce qu'un enfant en fait a l'ecran.

**Ce que je dis a `camille-po` sur les trois lignes de matrice** : aucune ne bouge aujourd'hui. Les
trois sont `non couvert` et le restent tant qu'un Done pedagogique n'est pas prononce.

**Le risque le plus eleve des trois n'est pas celui que le planning designe.** `sofia-sm` nomme
`cp-comparer` comme le plus fragile, et il l'est sur le tri et sur le pli - mais son defaut est
**visible et chiffre** : trois items a retirer, un a reecrire, une grandeur a ecrire, une somme de
522 px a respecter. **Le plus fragile a l'audit sera `cp-mot-manquant`**, parce que la qualite d'un
distracteur ne se mesure avec aucun chiffre : elle se juge item par item, et 15 distracteurs sur 15
viennent d'echouer ce jugement.

## 10. Amendement de `SEG-1`, clause (c) - 2026-08-28

**Rien de ce qui precede n'est reecrit.** Cette section s'ajoute et se date : la trace de ce qui a
ete pose puis borne est ce qui rend la decision relisible.

### 10.1 Ce qui a declenche cet amendement

L'utilisatrice a tranche le **2026-08-28** : *"pour les syllabes cheminee vaut bien 3 syllabes"*.
C'est une decision de son rang et elle prime sur le verdict pedagogique. J'avais refuse
`bandit-manchot-syllabes-cp` le meme jour, sur ce seul item.

**Mon refus n'etait pas une erreur d'application : c'etait l'application exacte de la clause (c)
que j'avais ecrite le 2026-08-27**, laquelle exclut *"les mots ou un `e` interieur peut disparaitre
a l'oral (`samedi` se dit couramment en deux syllabes)"*. Si `cheminee` vaut trois syllabes, **ce
n'est donc pas l'item qui etait mauvais, c'est ma clause qui etait trop large.** Une exception
nommee pour `cheminee` ne tiendrait pas : elle se dissoudrait au premier `petit` venu.

### 10.2 Ce que j'ai mesure avant de trancher, avec sa population et sa date

**Population** : le lexique francais **reellement mis devant un enfant de CP par ce produit** - les
10 paquets CP servis (`adapters/adapter-content/src/main/resources/contenu/cp-*.yaml`) et les
fichiers CP de `contenu-repris/`, soit **29 fichiers**. Seuls les champs que l'enfant lit sont
retenus (enonces, propositions, indices, explications). **650 mots francais distincts**, mesure du
**2026-08-28**. **Ce n'est pas un echantillon** : c'est tout le corpus CP du depot.

Clause (c) appliquee mecaniquement, en cherchant un `e` **nu en syllabe ouverte** - precede d'une
lettre, suivi d'**une seule** consonne puis d'une voyelle. Un `e` suivi de deux consonnes est en
syllabe fermee : c'est un [E] plein et jamais un schwa, ce qui est le cas d'`escargot`,
d'`escalier` et d'`esprit`.

| | Mots |
|---|---|
| Corpus CP du depot | **650** |
| Attrapes par la clause (c) d'origine | **40** |
| Dont artefact de detection : `VELO`, capitale privee de son accent (`contenu-repris/mots/cp-premiere-lettre.yml`, ligne 32) | **1** |
| **Cas de schwa reels** | **39** |
| **Ecartes a tort** - l'articulation lente restitue le `e` | **30** |
| **Ecartes a bon droit** - la forme reduite *est* la forme de citation ordinaire | **9** |

**Les 30 ecartes a tort** : `besoin`, `celui`, `cheminee`, `cheval`, `dehors`, `demande`,
`devienne`, `devient`, `deviner`, `genou`, `jetons`, `lequel`, `lever`, `leves`, `mesure`,
`mesurer`, `petit`, `petite`, `petites`, `petits`, `premier`, `recevoir`, `regarde`, `regardes`,
`remet`, `renard`, `recoit`, `seconde`, `semaine`, `tournevis`.

**C'est le coeur du lexique de CP.** Ma clause d'origine ecartait `petit`, `cheval`, `renard`,
`semaine`, `regarde` et `premier` d'un jeu de denombrement syllabique destine a des enfants de six
ans. Elle etait **environ quatre fois trop large** : 39 mots attrapes pour 9 cas legitimes.

**Les 9 ecartes a bon droit** : `acheter`, `casserole`, `doucement`, `facilement`, `lentement`,
`prevenir`, `samedi`, `seulement`, `soulever`.

**`samedi`, mon exemple fondateur, est dans ce second groupe.** La clause ne reposait donc **pas**
sur rien : elle visait un phenomene reel, et elle l'a manque en attrapant quatre fois trop large.
C'est pourquoi j'amende plutot que je ne supprime. **Je le dis explicitement parce que la
suppression pure etait sur la table et que c'est la mesure qui l'a ecartee.**

### 10.3 Le mecanisme qui separe les trois clauses, et pourquoi il ne se dissout pas au cas suivant

En cherchant ce qui distingue `cheminee` de `dinosaure`, j'ai trouve un critere qui n'est pas propre
a ce mot, et qui explique pourquoi les clauses (a) et (b) survivent quand la clause (c) tombe.

**La tache elle-meme demande a l'enfant de dire le mot lentement** : la consigne dit *"Tape dans tes
mains"*, et les indices du paquet disent *"en disant le mot tout doucement"* et *"Prononce le mot
tres lentement"*. Or l'articulation lente et deliberee est precisement le registre ou les schwas du
francais **reapparaissent**.

- **Clause (c)** : l'articulation lente **resout** l'ambiguite. `che-mi-nee`, `pe-tit`, `che-val` se
  disent avec leur `e` des qu'on ralentit. **La tache guerit le probleme.**
- **Clauses (a) et (b)** : l'articulation lente **cree** l'ambiguite, en ajoutant une syllabe.
  Ralentir `dinosaure` invite a faire sonner le `e` final (`di-no-sau-re`, 4 au lieu de 3) ;
  ralentir `parapluie` invite a scinder la semi-voyelle (`plu-ie`, 2 au lieu de 1). **La tache
  aggrave le probleme.**

**Ce critere est general et verifiable sur n'importe quel mot** : il ne depend ni de `cheminee`, ni
de mon oreille. C'est ce qui l'empeche de se dissoudre au premier cas suivant.

### 10.4 `SEG-1`, clause (c), dans sa forme amendee

La clause (c) du 2026-08-27 disait : *"les mots ou un `e` interieur peut disparaitre a l'oral
(`samedi` se dit couramment en deux syllabes)"*. **Elle est remplacee, a compter du 2026-08-28,
par :**

> **`SEG-1` clause (c), amendee** - sont exclus **les mots dont la forme reduite est la forme de
> citation ordinaire**, c'est-a-dire ceux ou **l'articulation lente ne restitue pas le `e`**
> (`medecin`, `acheter`, `samedi`, et les adverbes en `-ement` du type `doucement`, `lentement`).
> **Un `e` que l'articulation lente restitue n'est pas un motif d'exclusion** : `petit`, `cheval`,
> `cheminee`, `renard`, `semaine` et `regarde` sont **autorises**, parce que la tache demande a
> l'enfant de dire le mot lentement et que le compte y est alors stable.

**Ce qu'elle cesse d'exclure** : les 30 mots listes en 10.2, dont tout mot a schwa initial - la
famille `pe-`, `che-`, `re-`, `de-`, `se-`, qui est la plus courante du lexique de CP.

**Ce qu'elle exclut toujours** : les 9 mots listes en 10.2, et tout mot dont la prononciation de
reference a lexicalise la chute du `e`.

**Les clauses (a) et (b) sont inchangees.** `dinosaure`, `parapluie` et `helicoptere` restent
ecartes, pour le motif de 10.3 : sur eux, ralentir aggrave.

### 10.5 Ce que cet amendement ne fait pas

- **Il ne rouvre aucun verdict rendu contre un autre paquet.** `PRE-1` a `PRE-8` et `GRA-1` a
  `GRA-10` ne sont pas touches.
- **Il n'est pas retroactif contre les auteurs.** `marc-dev` a suivi ma specification ; c'est ma
  specification qui etait trop large - comme a s20, ou `lea-dev` avait recopie des libelles que
  j'avais dits courts et qui faisaient 34 caracteres. **C'est la deuxieme fois en deux sprints
  qu'une de mes conditions prealables, et non le travail qu'elle encadrait, est la cause racine
  d'un refus.** Je l'ecris ici parce que c'est le genre de motif qui doit se compter.
- **Il ne change ni le pli, ni aucune mesure de forme.**

### 10.6 Suite routee, trouvee en mesurant

`contenu-repris/mots/cp-premiere-lettre.yml`, ligne 32, porte `prompt: "VELO"` - une capitale
**privee de son accent**. Ce fichier est de la **matiere premiere de reprise et n'est pas servi a un
enfant aujourd'hui** : ce n'est donc pas un defaut en production. Mais si ce paquet est repris, la
capitale doit porter son accent, par `SEG-9` et par l'amendement du 2026-08-04 a `DEC-0002`.
**A `camille-po`**, pour le jour ou cette matiere passera devant moi.
