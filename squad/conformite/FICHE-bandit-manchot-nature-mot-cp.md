# Fiche de conformite - bandit-manchot-nature-mot-cp

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cp-nature-mot.yaml`
  livre, `type: question`, 16 items, story `S19-4` (`docs/backlog/backlog.md`).
  **Adaptation reelle requise, pas une reprise** : `contenu-repris/mots/cm1-nature-mot.yml` (16
  items) classe des mots isoles (hors phrase) en quatre natures (nom, verbe, adjectif,
  determinant), a un niveau CM1. La competence CP demande exactement deux natures (nom, verbe) et
  exige que le mot soit presente dans une phrase tres simple, jamais isole. Les deux ecarts (forme
  et perimetre) changent a la fois ; ce paquet repasse integralement devant `nadia-prof` comme un
  contenu neuf (`contenu-repris/README.md`).
- **Statut global** : **VALIDE par `nadia-prof` le 2026-08-25**, `NAT-5` levee, **aucune reserve
  bloquante** ; verdict **reconfirme sur l'artefact servi le 2026-08-27** (dernier bloc de cette
  fiche : increment rejoue a `77dd467`, rendu identique au centieme). Le jeu est revenu au catalogue
  par `DEC-0048`. *Cette ligne a porte "Done pedagogique non encore prononce" du 2026-08-24 au
  2026-08-27, soit trois verdicts apres qu'il l'a ete ; corrigee par `nadia-prof` a la cloture de
  s20 - un en-tete perime sur la fiche que tout le monde lit en premier est de la meme famille
  qu'un chiffre sans sa date.*
- **Arbitrage prealable de `nadia-prof` rendu le 2026-08-24**
  (`squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md`, partie B) : **la
  forme du jeu est ACCEPTEE SOUS CONDITIONS ; les seize phrases du premier brouillon
  (`squad/sprints/s19-contenu-seul-et-conception-de-la-saisie/proposition-s19-4-lea-dev.md`) sont
  REFUSEES en l'etat**, motif mesure et non suppose - les huit noms proposes etaient tous precedes
  d'un determinant, aucun des huit verbes ne l'etait, et la seule regle "petit mot juste avant" donnait
  seize bonnes reponses sur seize sans lire aucun mot cible (`NAT-1`). Conditions bloquantes `NAT-1` a
  `NAT-5`, non bloquantes `NAT-6` a `NAT-8`, detaillees dans le fichier d'arbitrage. Le paquet servi
  sous `adapters/adapter-content/src/main/resources/contenu/cp-nature-mot.yaml` est la reecriture
  demandee ; sa conformite aux conditions reste a auditer par `nadia-prof` sur l'increment livre (elle
  n'a valide aucun contenu par ce seul depot de fichier).
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, conditions M1 a M13) et **forme deja validee pour un mot
  marque en contexte de phrase** (`FICHE-bandit-manchot-synonymes-cm1.md`, conditions SYN-1, SYN-6,
  SYN-9, reprises par analogie ci-dessous pour marquer le mot cible).
- **NAT-5 (renfort visuel du mot cible), non encore instruit** : les guillemets francais seuls ne
  suffisent pas a un enfant de CP qui ne les a pas appris (`nadia-prof`, partie B.6). Le renfort est a
  proposer par `ines-design` et a trancher par `nadia-prof`, jamais par la seule couleur ; ce paquet
  garde pour l'instant les guillemets francais a espaces insecables, identiques a `cm1-synonymes`.
- **Derniere mise a jour** : 2026-08-27 (`nadia-prof`, controle de cloture de s20 : le `VALIDE` du
  2026-08-25 tient sur l'artefact servi a `77dd467`)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CP (cycle 2) | Orthographe et grammaire | Distinguer les principaux types de mots (nom, verbe) dans une phrase tres simple | `squad/programme/francais/competences-cp.md` |

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque item presente une phrase tres simple (sujet, verbe, et au plus un complement court -
  vocabulaire et longueur de niveau CP) avec le mot cible marque par une ponctuation francaise,
  jamais un mot isole.
- [ ] Les propositions de chaque item sont exactement deux : "nom" et "verbe" - aucune troisieme
  nature.
- [ ] Le paquet est classe par bonne reponse ; "nom" et "verbe" sont chacun representes par au
  moins un quart des items, aucune des deux natures dominante.
- [ ] Le vocabulaire employe (sujets, verbes, complements) est un vocabulaire courant de CP, deja
  rencontre ou plausible a ce niveau de lecture - pas le vocabulaire CM1 du paquet repris.
- [ ] Chaque item porte un indice qui aide a reconnaitre la nature du mot (par exemple : "un verbe
  dit ce qu'on fait") sans jamais nommer la reponse.
- [ ] Le paquet compte au moins 16 items.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue. L'arbitrage prealable demande ci-dessus (lisibilite des phrases
> pour un CP, absence d'indice de position) sera rendu ici ou dans un fichier d'arbitrage dedie,
> avant tout code.

### Verdict du 2026-08-24 (s19) - `S19-4`, "Nom ou verbe ?"

- **Auteur** : nadia-prof. **Increment** : `round-two` a `51301d7`, jar reconstruit depuis cet arbre
  et servi, application **arretee par son PID**.
- **Audit joue** : oui. **5 parties, 87 lectures, 16/16 items vus**, au repos et apres un echec, a
  **744 x 551** (`DEC-0040`). **`scrollY = 0` a chaque lecture, 0 sur 87.**
- **Releve** : `squad/conformite/releves-s19/s19-audit-nature-mot-744x551.json`. Capture :
  `s19-nat-744x551-medor-repos.png`.

## Verdict : **VALIDE SOUS RESERVE - une reserve BLOQUANTE, `NAT-5`, qui n'est pas dans les mains de `lea-dev`.**

### 1. Le motif de mon refus est leve, et je l'ai remesure moi-meme

J'avais refuse les seize phrases sur un seul motif chiffre : les huit noms etaient tous precedes d'un
determinant, aucun des huit verbes ne l'etait, et la regle "petit mot juste avant" donnait **16
bonnes reponses sur 16** sans lire le mot cible. **J'ai refait ce releve sur le paquet servi**,
plutot que de croire le commentaire du YAML.

| Reponse attendue | Mot immediatement avant le mot cible |
|---|---|
| **nom** (8) | *Le*, **joli**, *Le*, **bon**, **rien** (debut de phrase), **petits**, *la*, *La* |
| **verbe** (8) | chien, poisson, **Elle**, enfants, Lea, Tom, **Il**, **Tu** |

**4 noms sur 8 gardent un determinant immediat, 4 ont un adjectif ou rien. 5 verbes sur 8 ont un nom
devant, 3 un pronom sujet.** Le decompte annonce est exact au mot pres.

**Ce que la meilleure regle mecanique donne maintenant**, en chiffre plutot qu'en appreciation : "un
determinant juste avant, donc nom ; sinon verbe" rend **12 bonnes reponses sur 16**, contre 16 sur 16
avant. Les quatre erreurs sont exactement les quatre noms sans determinant adjacent. **Le raccourci
est casse au sens ou je l'avais ecrit** : il n'existe plus de signal de forme qui separe les deux
natures.

**Et le paquet fait mieux que satisfaire ma condition, ce que je note pour que le motif ne se perde
pas** : les quatre noms sans determinant adjacent sont des **contre-exemples que l'enfant rencontre
en jouant**. Un enfant qui applique le raccourci se trompe quatre fois et apprend, par le jeu
lui-meme, que sa regle est insuffisante. C'est la bonne facon de casser un raccourci, meilleure que
de le rendre seulement inoperant.

**`NAT-1` est tenue.** Je laisse une trace du residu plutot que de le taire : 12 sur 16 par une regle
de forme reste au-dessus du hasard, sur une tache a deux reponses ou le hasard donne 8 sur 16. Ce
n'est pas une reserve, c'est la nature d'une tache binaire, mais c'est le chiffre exact.

### 2. Les autres conditions

| Condition | Etat | Verifie |
|---|---|---|
| **NAT-2** aucun homographe nom/verbe connu d'un CP | **tenue** | *joue* et *lit* sont sortis ; *nage* et *court* conserves, exactement comme je l'avais tranche. **J'ai verifie aussi les huit noms** - *chat, sac, soleil, gateau, Medor, oiseaux, voiture, maitresse* - aucun n'est un verbe |
| **NAT-3** aucun indice ne nomme un mot imprime dans la phrase | **tenue** | 16 indices semantiques, zero determinant cite |
| **NAT-4** le paquet tient pour un enfant seul | **non tenue** - voir reserve bloquante |
| **NAT-5** le mot cible designe autrement que par les guillemets seuls | **NON TENUE** - voir reserve bloquante |
| **NAT-6** la phrase la plus dure remplacee | **tenue** | "Tom saute dans la flaque." |
| **NAT-7** au plus un mot cible a -ent muet | **tenue** | un seul, *chantent* |
| **NAT-8** huit/huit, seize phrases distinctes | **tenue** | verifie une par une |

**Sur `NAT-3`, une information et non un reproche.** `lea-dev` signale avoir pris la **lecture large**
et passe tous les indices en semantique pur, y compris la ou ma regle ne l'exigeait pas. **Elle est
en effet plus stricte que ma condition**, qui autorisait le test du determinant sur les items ou
aucun determinant ne precede la cible - c'est-a-dire sur *joli sac*, *bon gateau*, *petits oiseaux*.
Ces trois items etaient precisement l'endroit ou l'indice aurait pu enseigner **la vraie methode de
CP** au moment ou elle n'est pas donnee gratuitement. **C'est une occasion laissee de cote, pas une
faute**, et j'en fais une piste non bloquante (`NAT-10`). Declarer l'ecart plutot que le taire est le
bon geste et je le dis aussi.

### 3. Le rendu, mesure

| Mesure a 744 x 551 | Resultat |
|---|---|
| Propositions par item | **2 sur les 16** - *nom* et *verbe* |
| Items sur deux rangees | **0 sur 16** |
| Largeur necessaire, maximum | **au plus 400 px** (plancher de mon balayage) contre 680 px offerts |
| Bas du bloc de verdict, pire cas | **426,19 px** pour un pli a 551 px - au-dessus, 16/16 |
| Bas de la boite d'indice, pire cas | **505,19 px** - **au-dessus du pli, 16/16** |

**Ce paquet est le seul des quatre du sprint qui ne perd rien sous le pli, sur aucun item, ni verdict
ni indice.** C'est la consequence directe de phrases courtes et de deux propositions courtes, et
c'est un point de qualite.

**Le point qu'on me demandait de verifier plutot que de croire, et il n'est pas comme annonce** :
**ce paquet est bien a deux propositions.** Le fait que j'ai etabli hier s'y applique donc
entierement - trois essais, une manche resolue au deuxieme essai comptant comme reussie, donc **un
enfant qui presse au hasard finit chaque partie a 10 sur 10**. Je maintiens ce que j'ai ecrit : ce
n'est **pas** un motif contre ce paquet, la tache nom/verbe n'ayant que deux reponses possibles, et
j'ai laisse passer la meme chose sur les homophones. L'arbitrage appartient a `camille-po`.

### 4. Les reserves

**BLOQUANTE - `NAT-5`, non instruite.** *"Le mot cible est designe autrement que par les guillemets
seuls, et jamais par la couleur seule."* **Aucun renfort n'a ete propose ni ajoute** : la derniere
modification de `recreo.css` et des gabarits date de s18, et la proposition d'`ines-design` de ce
sprint porte sur le plafond de `SYN-16`, pas sur ce sujet. La fiche le dit elle-meme en tete, et
c'est exact.

**Je ne leve pas cette condition, et je ne la reproche pas a `lea-dev`.** Elle n'etait pas dans son
lot : je l'avais moi-meme routee a `ines-design`. Mais je l'ai ecrite bloquante et **je ne la
declasse pas apres coup parce que le reste du paquet est bon** - ce serait deplacer mes poteaux, dans
le sens facile. Ce que j'ai vu a l'ecran ne me permet pas de la lever non plus : les guillemets sont
nets, en gras a 2 rem, un adulte voit le mot designe instantanement, **et je ne sais pas ce qu'un
enfant de six ans qui n'a jamais appris les guillemets en fait**. C'est precisement le genre de chose
qu'aucun de mes Done n'a jamais couvert.

**BLOQUANTE, et neuve - `NAT-9` (2026-08-24).** La consigne servie est **"Le mot entre guillemets
est-il un nom ou un verbe ?"**. **Un enfant de CP ne lit pas cette phrase seul.** Dix mots, une
inversion a trait d'union (*est-il*), et surtout le mot **guillemets**, dix lettres avec le digraphe
*ill*, qui n'est ni au programme du CP ni dans son vocabulaire. La consigne est de plus
**circulaire** : pour savoir de quel mot on parle, l'enfant doit lire un mot qui nomme un signe qu'il
ne connait pas. `NAT-4` demandait que le paquet tienne pour un enfant seul ; avec cette consigne, il
ne tient pas.

*Ce qui la repare, et c'est plus court* : **"Ce mot est-il un nom ou un verbe ?"**, ou plus court
encore **"Nom ou verbe ?"** - qui est deja le titre du jeu, et qui suffit des lors que le mot est
designe visuellement. **`NAT-9` et `NAT-5` se reparent ensemble** : la consigne peut cesser de nommer
le signe exactement quand le marquage se voit sans etre nomme. C'est un seul chantier, pas deux, et
c'est pourquoi je les pose ensemble plutot que l'une apres l'autre.

**NON BLOQUANTE - `NAT-10` (2026-08-24).** Sur les trois items ou un adjectif s'intercale (*un joli
sac*, *un bon gateau*, *les petits oiseaux*), l'indice pourrait enseigner le test du determinant sans
rien donner gratuitement, puisque le determinant n'y est pas adjacent. C'est la methode que la classe
emploie ; l'occasion est la et n'est pas prise.

**NON BLOQUANTE - `NAT-11` (2026-08-24).** *"« Medor » aboie tres fort."* est le seul item ou le test
du determinant, methode enseignee au CP, **donne la mauvaise reponse** : on ne dit pas *"un Medor"*.
L'enfant n'a que la voie semantique, que l'indice lui donne. Je ne le refuse pas - le nom propre est
au programme du CP et cet item est un contre-exemple utile - mais je prefererais un nom commun sans
determinant, qui existe : *"Tom part en « vacances »."* rompt le motif sans mettre l'enfant en echec
sur sa propre methode.

### 5. Ce que ce verdict fait, et ce qu'il ne fait pas

**Le Done pedagogique est ouvert** : le jeu est jouable, son contenu est juste, et le defaut de fond
que j'avais refuse est corrige et remesure. **La ligne ne peut pas passer `couvert-valide` tant que
`NAT-5` et `NAT-9` restent ouvertes** - c'est la regle de la matrice, pas une decision de plus de ma
part, et le geste appartient de toute facon a `camille-po`.

**Ce que ce Done ne couvre pas** : ce qu'un lecteur d'ecran annonce, et **ce qu'un enfant de six ans
comprend reellement des guillemets** - qui est, ici, exactement la question ouverte.

---

## Amendement du 2026-08-25 (hors sprint) - `NAT-9` apres l'arbitrage de l'utilisatrice sur l'accompagnement du premier essai

- **Auteur** : nadia-prof. **Fait neuf** : l'utilisatrice arbitre qu'*"un enfant de CP fait toujours
  le premier essai accompagne"*. Cette question etait la mienne, routee le 2026-08-24
  (`ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md`, section 8, deuxieme point a l'utilisatrice).
- **Le verdict du 2026-08-24 ci-dessus n'est ni reecrit, ni efface, ni adouci.** Il reste lisible tel
  qu'il a ete rendu, et c'est de lui que cet amendement part.
- **Mode d'audit : degrade, et je le declare.** Je n'ai pas rejoue, et les deux motifs sont verifies
  plutot que supposes. Le paquet servi est inchange depuis mon audit
  (`contenu/cp-nature-mot.yaml`, dernier commit `99d0a75`, `[LEA] s19`) et **rien n'a bouge sous
  `adapters/adapter-web/src/main/resources/` depuis le 2026-08-24** - ni gabarit, ni feuille de
  style. L'artefact que je rejouerais serait identique a celui que j'ai joue en 5 parties et 87
  lectures. Le jeu est de plus sorti du catalogue par `DEC-0045` : le rejouer demanderait de
  reintroduire une entree sous `adapters/`, hors de mon perimetre d'ecriture. **Ce qui a change
  n'est pas l'increment, c'est une premisse.**

### 1. Deux faits que je verifie avant de m'appuyer dessus, dont un des miens qui etait inexact

**Ce que j'avais ecrit et qui est faux.** Dans mon verdict du 2026-08-24, section 4, j'ecris que
*"les guillemets sont nets, en gras a 2 rem"*. C'est vrai de la lettre et faux de l'intention :
`recreo.css` donne `font-size: 2rem` et `font-weight: bold` a `.enonce` **entier**. Les guillemets
ne sont pas mis en valeur, ils sont a la taille de tout le reste de la phrase. **Rien, dans
l'enonce, ne distingue le mot cible des autres mots, hors les deux signes eux-memes.** Ma phrase
creditait le marquage d'une emphase qu'il n'a pas. Cela rend `NAT-5` un peu plus dure que je ne
l'avais decrite, pas plus facile ; je le corrige avant de m'en servir.

**Un fait que je n'avais pas etabli et qui pese ici.** L'indice n'apparait **qu'a partir du premier
echec** de la manche (`BanditManchotSession.indice()`, condition `essaisSurLaManche == 0`, `M1`). Au
moment ou l'enfant doit choisir sur une manche fraiche, il n'a donc **que la consigne et la
phrase**. Rien d'autre a l'ecran ne lui dit de quel mot on parle. Une partie compte **10 manches**
(`nombreDeManches: 10` dans l'entree retiree par `DEC-0045`) : dix designations a refaire par
partie, sur dix phrases differentes.

### 2. Comment je lis "le premier essai accompagne", et pourquoi mon verdict ne depend pas de cette lecture

Le mot *essai* est un terme technique de cette mecanique : `M5` en donne **trois par manche**. Je ne
lis pas l'arbitrage dans ce sens - un adulte qui lirait la consigne au premier des trois essais et
disparaitrait au deuxieme n'aurait aucun sens pedagogique. Je le lis comme : **la premiere fois que
l'enfant ouvre le jeu, un adulte est a cote de lui.**

Reste une ambiguite que je ne tranche pas, parce qu'elle n'est pas la mienne : *"toujours le premier
essai"* peut designer le premier essai **de chaque jeu**, ou le premier contact de l'enfant avec
`recreo`. **Mon verdict est le meme dans les deux lectures**, parce qu'il ne porte pas sur ce qui se
passe au premier essai : il porte sur ce qui se passe **a la deuxieme partie du meme jeu**, ou
l'enfant est seul dans les deux lectures.

### 3. Ce que l'arbitrage leve, et je le leve franchement

`NAT-9` portait deux motifs. Ils ne resistent pas de la meme facon, et je les separe plutot que de
garder le tout en bloc.

**Motif de lisibilite : il tombe.** J'avais compte dix mots, l'inversion *est-il*, et le mot
*guillemets* - dix lettres, digraphe *ill*, hors programme et hors vocabulaire de CP. Un adulte qui
lit cette phrase a voix haute une fois l'annule. Et elle ne revient pas : la tache elle-meme -
*nom ou verbe ?* - est portee par le titre du jeu et par les deux boutons, que l'enfant relit a
chaque manche sans avoir besoin de la consigne. **Sur ce motif, l'arbitrage a raison contre moi et
je n'y reviens pas.**

**Motif de circularite : il tient, et la borne de l'arbitrage est exactement pourquoi.** Ce que la
consigne porte, ce n'est pas la tache, c'est la **designation** : elle est le seul element de
l'ecran qui dise de quel mot on parle. Cette designation est necessaire dix fois par partie, sur dix
phrases, et au moment precis ou l'enfant n'a rien d'autre (section 1).

Ce que l'adulte transmet au premier essai, c'est une **convention** : *le mot entre les petits
signes*. **Un enfant de CP apprend une convention en une presentation** - c'est le geste ordinaire
d'une classe et je ne vais pas pretendre le contraire. Mais celle-ci a trois proprietes qui en font
un mauvais candidat a la transmission unique, et je les nomme parce que c'est la que se joue la
reponse :

- le signe n'est **pas au programme du CP** et l'enfant ne le rencontre nulle part ailleurs dans ses
  lectures ;
- **rien a l'ecran ne le rappelle** - ni emphase, ni couleur, ni forme, comme la section 1 vient de
  l'etablir ;
- entre deux parties il peut passer **plusieurs jours**, et `DEC-0028` fait qu'aucune trace de la
  premiere session ne survit.

Une convention transmise une fois, rappelee par rien, et redemandee dix fois par partie des le
lendemain, **n'est pas tenue par l'artefact : elle est tenue par la memoire de l'enfant seul.** Dans
ma classe je ne fais jamais cela ; je laisse le repere affiche.

**Reponse a la question posee, en une phrase** : une consigne qu'un adulte lit une fois satisfait
`NAT-9` **des lors qu'elle ne porte plus la designation** ; elle ne la satisfait pas tant qu'elle la
porte seule.

### 4. Ce que `NAT-9` exige desormais - une seule chose, une seule edition

**Je ne durcis rien et je ne deplace pas mes poteaux.** La reparation que j'ai ecrite le 2026-08-24
reste mot pour mot celle-ci : *"Ce mot est-il un nom ou un verbe ?"*, ou *"Nom ou verbe ?"*.

Ce que l'arbitrage change, c'est **laquelle des deux suffit**, et il le change **dans le sens du
desserrement** :

- avant l'arbitrage, j'allais devoir regarder de pres *"Ce mot est-il un nom ou un verbe ?"* - sept
  mots et l'inversion *est-il*, pour un lecteur de CP seul ;
- apres l'arbitrage, un adulte la lit au premier essai et elle n'a plus a etre dechiffree ensuite.
  **J'accepte les deux formulations.** Le choix entre elles revient a `lea-dev` ; je n'en impose
  aucune.

**`NAT-9`, reformulee au 2026-08-25 - et c'est tout ce qu'elle demande** : *la consigne servie ne
nomme aucun signe typographique.* Une edition, dans l'entree de catalogue, sans attendre personne.

### 5. Ce que cet amendement fait a `NAT-5`, et ce qu'il n'y fait pas

**`NAT-5` est inchangee : toujours bloquante, toujours routee a `ines-design`.** L'arbitrage ne la
touche pas - il parle de la voix d'un adulte, pas de ce que l'ecran montre.

**Je reviens en revanche sur une phrase a moi.** Le 2026-08-24 j'ai ecrit que `NAT-9` et `NAT-5`
*"se reparent ensemble ... c'est un seul chantier, pas deux"*. **Ce n'est plus vrai, et c'est
l'arbitrage qui les separe** : `NAT-9` se ferme seule, par une edition de la consigne, sans attendre
le renfort visuel. Je le dis parce que ma phrase, laissee telle quelle, ferait attendre `lea-dev`
apres `ines-design` sans motif.

**Et je nomme l'etat intermediaire plutot que de le laisser decouvrir.** Une consigne qui cesse de
dire *entre guillemets* alors que `NAT-5` n'est pas faite laisse la designation portee par les seuls
guillemets - exactement ce que `NAT-5` refuse. **Cet etat n'atteint aucun enfant** : `DEC-0045` a
sorti le jeu du catalogue et sa section 2 exige qu'aucune reserve bloquante ne reste avant le
retour. Fermer `NAT-9` en premier ne fait donc courir aucun risque ; c'est meme le bon ordre, parce
que `NAT-5` sera plus facile a evaluer sur une consigne qui ne compense plus le marquage.

### 6. Sur quelles proprietes je reste la seule garde, apres cet amendement

- **Ce qu'un enfant de six ans fait des guillemets** : inchange depuis le 2026-08-24. Aucun test ne
  le dira, et c'est `NAT-5`.
- **Le fait qu'un adulte soit reellement la au premier essai.** L'arbitrage en fait une propriete du
  produit ; **rien dans le produit ne la demande a l'adulte.** Un parent qui pose l'appareil et s'en
  va ne desobeit a rien, parce que rien ne lui a rien demande. Ce n'est pas une reserve sur ce jeu -
  cela vaut pour tout le CP - et je le route a `camille-po` comme un point produit, sans le
  trancher.
- **Ce qui n'est pas couvert et ne l'est toujours pas** : ce qu'un lecteur d'ecran annonce sur cet
  enonce.

**Aucun Done n'est prononce ici.** Le verdict du 2026-08-24 reste le verdict en vigueur :
`VALIDE SOUS RESERVE`, une reserve bloquante restant ouverte (`NAT-5`), `NAT-9` etant desormais
fermable par une seule edition.

---

## Second amendement du 2026-08-25 (hors sprint) - l'arbitrage est elargi : `NAT-9` TOMBE, `NAT-4` est retiree, `NAT-5` tient sur un motif que je nomme

- **Auteur** : nadia-prof. **Fait neuf** : l'utilisatrice elargit son arbitrage - *"considere qu'un
  eleve de CP est toujours accompagne par un adulte"*. Ce n'est plus le premier essai : c'est l'etat
  durable.
- **Ni le verdict du 2026-08-24 ni le premier amendement du 2026-08-25 ne sont effaces ou reecrits.**
  On doit pouvoir lire l'histoire : un refus, une levee partielle, puis celle-ci.
- **Mode** : aucun rejeu, et pour la meme raison qu'au premier amendement, verifiee et non supposee -
  le paquet est inchange (`99d0a75`), rien n'a bouge sous `adapters/adapter-web/src/main/resources/`,
  et le jeu est hors catalogue par `DEC-0045`. Ce qui change est une premisse, pas un artefact.

### 1. `NAT-9` tombe. Je la leve entierement.

**Mon motif reposait sur un comptage, et ce comptage vient d'etre prive de sa conclusion.** J'avais
ecrit qu'une convention *"transmise une fois, rappelee par rien, et redemandee dix fois par partie
des le lendemain, n'est pas tenue par l'artefact : elle est tenue par la memoire de l'enfant seul"*.
**Il n'y a plus d'enfant seul.** Les trois proprietes que j'avais nommees pour soutenir ce motif -
le signe hors programme, l'ecran qui ne rappelle rien, les jours entre deux sessions avec `DEC-0028`
qui n'en garde rien - sont toutes les trois neutralisees par un adulte durablement present. Aucune
ne survit.

**Je ne cherche pas ce qui pourrait rester.** Une condition qui survivrait a la disparition de son
motif ne serait plus une condition, ce serait une habitude. `NAT-9` disait *"un enfant de CP ne lit
pas cette phrase seul"* ; la premisse du produit est desormais qu'il ne la lit jamais seul.

**`NAT-9` est LEVEE au 2026-08-25.** La consigne servie - *"Le mot entre guillemets est-il un nom ou
un verbe ?"* - est **acceptee telle quelle**. **`lea-dev` n'a rien a reformuler**, et je le dis en
toutes lettres pour qu'aucune story ne soit ouverte sur du vide.

*Je n'ajoute pas de recommandation de repli.* J'avais propose *"Nom ou verbe ?"* comme reparation ;
une reparation dont le defaut n'existe plus n'est pas une amelioration a poursuivre, c'est ma
condition qui reviendrait par la fenetre.

### 2. `NAT-4` n'est pas satisfaite : elle est retiree, et la nuance n'est pas cosmetique

`NAT-4` demandait que *"le paquet tienne pour un enfant seul"*. Elle figure `non tenue` dans mon
tableau du 2026-08-24. **Sa premisse est supprimee par l'arbitrage pour tout le CP.**

Je l'ecris comme un **retrait**, pas comme une levee, parce que la difference protege `lea-dev`
autant qu'elle me protege : `NAT-4` n'a pas ete satisfaite par le paquet, et personne ne doit
pouvoir ecrire plus tard qu'elle l'a ete. Elle a cesse de decrire le produit.

### 3. `NAT-5` tient, et voici exactement sur quoi - motif date d'aujourd'hui

**Ce que l'arbitrage affaiblit reellement dans `NAT-5`, et je le concede avant de defendre quoi que
ce soit.** Le motif que je lui donnais depuis le 2026-08-24 etait : *"je ne sais pas ce qu'un enfant
de six ans qui n'a jamais appris les guillemets en fait"*. Un adulte durablement present peut le lui
expliquer. **Ce motif-la est affaibli, et je ne fais pas semblant du contraire.**

**Ce qui tient, et ce n'est pas un motif de rechange : c'est le meme comptage de dix, redirige.** Le
comptage que j'ai fait ce matin faisait deux travaux a la fois, et un seul meurt :

- il soutenait `NAT-9` par **la memoire de l'enfant** entre deux sessions - **mort** ;
- il soutient `NAT-5` par **le travail demande a l'adulte a l'interieur d'une partie** - **vivant**.

**L'asymetrie est nette et c'est elle qui decide** : la consigne porte la **tache**, qui est
constante sur toute la partie - une lecture a voix haute couvre les dix manches. Le marquage porte
la **designation**, qui **change a chaque manche** - dix mots differents, dans dix phrases
differentes. Si l'ecran ne la porte pas, c'est l'adulte qui doit intervenir **dix fois par partie**,
en posant le doigt sur un mot precis a l'interieur d'une phrase.

**L'arbitrage fournit une presence, il ne prescrit pas dix interventions.** Un adulte *accompagnant*
est un adulte disponible, qui surveille et qui repond ; ce n'est pas un adulte qui pilote
l'interface item par item. Et **rien, nulle part dans le produit, ne lui demande de pointer** - c'est
le meme constat que j'ai deja pose au premier amendement, section 6.

**Pourquoi elle reste BLOQUANTE et pas simplement genante.** Le mode de defaillance est **silencieux**,
et c'est la seule raison qui me ferait bloquer quoi que ce soit. Un enfant dont l'adulte ne pointe
pas ne sait pas de quel mot on parle. La tache n'a que deux reponses, et j'ai etabli le 2026-08-24
qu'a deux propositions et trois essais **un enfant qui presse au hasard finit chaque partie a 10 sur
10**. Il obtient donc un score parfait sans avoir rien lu, et **ni lui, ni l'adulte, ni le produit
n'en voient rien**. C'est exactement la famille de defaut que ce depot sanctionne : un resultat vert
qui ne prouve rien.

**Le libelle de `NAT-5` ne change pas d'un mot** - *le mot cible est designe autrement que par les
guillemets seuls, et jamais par la couleur seule.* Je ne durcis rien ; je dis quel motif la porte
maintenant. Elle reste routee a `ines-design`.

### 4. Ce que cet amendement change pour le retour au catalogue

**`NAT-5` est desormais la seule reserve bloquante de ce jeu.** La condition de retour posee par
`DEC-0045` section 2 est inchangee et le geste appartient a `camille-po` et a `sofia-sm` ; je note
seulement que le chemin s'est raccourci d'une reserve et qu'il ne passe plus par `lea-dev`.

**Aucun Done n'est prononce ici.** Le verdict en vigueur reste `VALIDE SOUS RESERVE` du 2026-08-24.

### 5. Sur quelles proprietes je reste la seule garde

- **`NAT-5`**, sur le motif de la section 3 : aucun test ne comptera les interventions qu'un
  dispositif exige d'un adulte.
- **La presence effective de l'adulte, et maintenant ce qu'on attend de lui.** Le produit fait
  reposer tout le CP sur un adulte durablement present et **ne lui adresse toujours pas un mot**.
  C'etait un point produit au premier amendement ; il pese plus lourd depuis que la premisse est
  passee du premier essai a la session entiere. Route a `camille-po`, toujours sans le trancher.

---

## Verdict du 2026-08-25 (s20) - reaudition de `NAT-5` sur l'increment livre : **VALIDE**

- **Auteur** : nadia-prof. **Increment** : `round-two` a `f4e0808`, `S20-1` (`ADR-0062`,
  `Question.partDesignee`, classe `.mot-designe`).
- **Audit joue, non degrade.** **5 parties, 71 lectures, 16 items sur 16 vus**, a la reference
  **744 x 551**, au repos et apres un echec, en me trompant volontairement. Application **arretee
  par son PID**.
- **Releve** : `squad/conformite/releves-s20/s20-audit-nat5-744x551.json`. Captures :
  `s20-nat5-744x551-none.png` et `s20-nat5-744x551-achromatopsia.png`.

### 0. Comment j'ai pu jouer un jeu qui est hors du catalogue, et pourquoi je n'ai touche a rien

`DEC-0045` a retire l'entree : le jeu ne s'ouvre pas depuis l'accueil. **Je n'ai pas retabli
l'entree dans le depot**, meme transitoirement. J'ai construit le jar depuis l'arbre tel quel, puis
**remplace la seule ressource `catalogue.yaml` dans une copie du jar, hors du depot**, en y ajoutant
l'entree exactement telle que le commit de retrait l'avait enlevee. **`git status` est vide avant et
apres.** Aucun fichier sous `adapters/` n'a ete modifie, pas meme une seconde.

Ce que j'ai mesure est donc l'artefact **tel qu'il sera quand `camille-po` rendra le jeu au
catalogue** - c'est le bon objet a auditer, et non l'etat hors catalogue qui n'atteint aucun enfant.
Une reserve d'honnetete : le catalogue d'audit est le catalogue **courant** plus cette entree, pas
celui d'avant le retrait, qui est perime depuis que `cm1-reperes` est entre.

### 1. Le critere, et il est a moi

Je ne reprends pas le motif d'origine de `NAT-5`. Le 2026-08-25 au matin j'ai concede que le motif
initial - *"je ne sais pas ce qu'un enfant de six ans qui n'a jamais appris les guillemets en
fait"* - etait **affaibli** par l'arbitrage sur l'accompagnement, et j'ai refonde la condition sur
un autre motif, date : *"la designation change a chaque manche - dix interventions d'adulte par
partie, la ou l'arbitrage ne fournit qu'une presence"*. **C'est contre ce critere-la que je juge**,
et pas contre un autre.

**La question est donc : l'ecran porte-t-il desormais la designation, de sorte que l'adulte n'ait
plus a pointer dix fois ?**

### 2. Ce que j'ai mesure, et ce que cela repond

| Mesure, 744 x 551 | Resultat |
|---|---|
| Items portant `.mot-designe` | **16 sur 16** |
| Le mot marque est bien celui entre guillemets | **16 sur 16** |
| `partDesignee` presente **exactement une fois** dans son enonce | **16 sur 16** - aucune ambiguite d'occurrence |
| Guillemets francais conserves dans l'enonce | **2 par item, 16 sur 16** ; **0 a l'interieur de la boite** |
| Taille du mot marque contre le reste de la phrase | **36,8 px contre 32 px**, soit **1,15 exactement** |
| Graisse, couleur du texte | **identiques** au reste de l'enonce (700, `rgb(28,39,51)`) - aucune discrimination par la couleur du texte |
| Marqueur encore present **apres un echec** | **14 sur 14** |
| Items sur une seule ligne | **16 sur 16** |
| Defilement, au repos comme apres un echec | **0 partout, 71 lectures sur 71** |
| Pire bas d'indice | **512,38 px** pour un pli a 551 - **marge 38,62 px, 0 item sous le pli** |
| Pire bas de verdict | **433,38 px** - marge 117,62 px |

**Les deux chiffres d'`ines-design` sont confirmes au centieme** : 16 sur 16 sur une ligne, 0 sur 16
sous le pli, pire marge **38,62 px**. Et la conversion qu'elle signale est exacte : 2,3rem sur 2rem
donne **36,8/32 = 1,15**, le rapport qu'elle proposait, au caractere pres.

**Reponse au critere : oui, les dix interventions sont supprimees.** Un enfant qui ne sait pas lire
*guillemets* voit **le mot dans la boite**. C'est un signal qui se percoit **avant** la lecture, et
c'est exactement le geste du surligneur d'une classe - l'argument d'`ines-design` est le bon, et
c'est le bon argument pedagogique, pas seulement le bon argument graphique. Le marquage **tient
toute la manche**, y compris apres un echec, donc a chacun des moments ou l'enfant en a besoin.

### 3. Le cout sur les autres jeux : verifie, et par un argument plus fort qu'une mesure

On m'annonce un cout nul. **Ma premiere mesure ne portait en realite que sur un seul jeu** - mon
compteur comptait les entrees du catalogue, pas les lancements reussis, et la navigation entre deux
jeux tombait sur l'ecran de confirmation. **J'ai failli publier un chiffre faux ; je l'ai refaite
avec une session neuve par jeu.** Je le note parce que c'est precisement le mode de defaillance que
je reproche aux autres.

Deuxieme mesure, **18 jeux sur 18 reellement lances, 72 manches observees** :

- **0 des 17 autres jeux ne rend `.mot-designe`** ; **0 ne contient le moindre `span`** dans son
  enonce ; leur enonce est **un unique noeud texte**, et sa taille est **32 px partout**.
- `cp-nature-mot` rend `#text` / `SPAN` / `#text` : le mot dans la boite, les guillemets dehors.

**Et le delta CSS entier est une seule regle** : `git diff` entre l'etat d'avant et `f4e0808` sur
`recreo.css` donne **17 lignes ajoutees, 0 supprimee, 0 modifiee**, un unique selecteur
`.mot-designe`. Un selecteur neuf qui ne correspond a rien ailleurs, sur un DOM structurellement
identique, **ne peut pas changer un pixel ailleurs**. C'est une preuve, pas un releve, et elle est
plus solide que la comparaison de deficits qu'on m'a proposee.

### 4. Le point sur lequel j'ai verifie plutot que cru, et ou l'annonce est a moitie fausse

On me presente **trois canaux non colores**. **La boite n'en est pas un**, et je l'ai mesure.

Fond de la boite `#fff59d` contre le fond effectif de la page `rgb(244,247,251)` : **contraste
1,04:1**. Sous achromatopsie emulee, la boite rend `(242,242,242)` contre `(247,247,247)` -
**1,045:1**, cinq niveaux sur 255. **La boite est un canal de teinte, pas de luminance : retirez la
couleur, elle disparait.**

La formule qui m'est rapportee - *"si l'enfant est daltonien ou ne distingue pas le jaune, la boite
et l'agrandissement restent visibles"* - est donc **juste sur une moitie et fausse sur l'autre**, et
la distinction compte :

| Vision emulee | Boite contre fond | La boite reste-t-elle visible ? |
|---|---|---|
| normale | 1,04:1, mais teinte franche | **oui** |
| deuteranopie | `(255,245,161)` contre `(245,246,251)` | **oui** - le jaune est preserve |
| protanopie | `(255,239,150)` | **oui** |
| tritanopie | `(255,234,223)` | **oui** |
| achromatopsie | `(242,242,242)` contre `(247,247,247)` | **non** |

**Pour le daltonisme courant - rouge-vert, celui qui touche environ un garcon sur douze - la boite
tient.** Elle ne tombe qu'en achromatopsie, ou sur un rendu monochrome.

**Et pourtant ma condition est satisfaite, et je le dis franchement.** `NAT-5` demande que le mot
soit designe *"autrement que par les guillemets seuls, et **jamais par la couleur seule**"*. Retirez
toute la couleur : **l'agrandissement de 15 % reste, et il se voit** - la capture sous achromatopsie
le montre, *chantent* est visiblement plus grand que les mots qui l'entourent. La designation
survit a la suppression complete de la couleur. **C'est exactement ce que ma condition exigeait, et
c'est atteint.**

## Verdict : **VALIDE. `NAT-5` est LEVEE au 2026-08-25.**

**Plus aucune reserve bloquante n'est ouverte sur ce jeu.** `NAT-9` est tombee ce matin,
`NAT-5` tombe ce soir sur mesure. La condition de retour au catalogue posee par `DEC-0045`
section 2 est remplie ; **le geste appartient a `camille-po`**, je ne le fais pas et je ne le
demande pas.


**Je ne retiens pas la boite contre l'increment.** J'ai mesure plus finement que ma condition ne
demandait ; une barre qu'on releve apres coup parce qu'on voit mieux fait de cette porte un caprice.
Ce que j'ai vu de plus s'ecrit ci-dessous comme une observation neuve et datee, **non bloquante**.

### 5. Une reserve NEUVE, datee du 2026-08-25, **NON BLOQUANTE** - `NAT-12`

- **`NAT-12`** - *La boite de `.mot-designe` ne porte pratiquement aucun contraste de luminance
  (1,04:1 contre le fond de page). Elle est un canal de teinte. Le marquage ne repose pas sur elle
  seule - l'agrandissement le sauve - mais le troisieme canal annonce n'existe pas en rendu
  monochrome.*

Elle n'est pas bloquante et je ne veux pas qu'elle le devienne par glissement : le jeu fonctionne
pour la quasi-totalite des enfants, y compris daltoniens. Je la pose pour deux raisons - qu'on cesse
d'ecrire que la boite est sure sans la couleur, et parce qu'elle se corrige a cout nul si
`ines-design` le souhaite : **la regle porte un `border-radius` mais aucune bordure**, et un liseret
sombre donnerait a la boite un contour independant de la teinte sans deplacer un seul pixel de mise
en page. **Je ne prescris rien** - `ines-design` propose, je tranche ensuite, comme d'habitude.

### 6. Ce que ce Done couvre, et ce qu'il ne couvre pas

**Couvert** : `NAT-1` a `NAT-3`, `NAT-6` a `NAT-8` (verdict du 2026-08-24 ; le paquet est inchange
sur ces points, seul `partDesignee` s'y est ajoute), `NAT-5` et `NAT-9` (ce jour), la designation du
mot cible, la mise en page a la reference, et le cout nul sur les autres jeux.

**Non couvert, et je le nomme pour que personne ne le croie couvert** :

- **Ce qu'un lecteur d'ecran annonce** de l'enonce segmente. `ADR-0062` decoupe l'enonce en `span` ;
  **je n'ai pas ecoute**, personne dans cette squad ne le peut (`F11`), et un mot isole dans un
  `span` peut s'annoncer autrement qu'un mot dans une phrase continue. **Ce n'est pas une reserve** -
  je n'ai aucun element disant que c'est mauvais - mais c'est un angle mort du produit qui vient de
  s'elargir, et il n'existait pas avant `S20-1`.
- **Toute largeur autre que 744 px.** La bande 576-719 est sortie de la cible ; je n'ai rien mesure
  sous la reference, et un mot marque qui tomberait sur une fin de ligne verrait sa boite coupee en
  deux. **Non observe sur 16 items sur 16 a la reference** ; non mesure ailleurs.
- **`NAT-10` et `NAT-11`** restent ouvertes, non bloquantes, inchangees.

### 7. Sur quelles proprietes je reste la seule garde

- **Que `partDesignee` designe le bon mot.** Rien ne verifie qu'une valeur de contenu corresponde au
  mot entre guillemets, ni qu'elle n'apparaisse qu'une seule fois dans la phrase. **Je l'ai verifie
  a la main, 16 sur 16 les deux fois.** Un paquet futur ou `partDesignee` vaudrait *"le"* marquerait
  le premier *le* venu, et la CI serait verte.
- **`NAT-12`** - aucun test de ce depot ne mesure un contraste de luminance.
- **Ce qu'un enfant de six ans fait de la boite.** Je l'ai jugee convaincante en la voyant ; c'est un
  jugement de maitresse, pas une mesure, et il reste le mien.

### 8. Un constat hors de ce jeu, que je route sans le traiter

En construisant mon catalogue d'audit j'ai vu que **`bandit-manchot-reperes-cm1` ("Majuscule ou
point ?") est expose dans `catalogue.yaml` a `f4e0808`, et que le bloc de validation de
`squad/conformite/FICHE-bandit-manchot-reperes-cm1.md` est vide** - aucun audit, aucun verdict.
`DEC-0045` section 3 dit qu'un jeu ne doit pas etre expose en cas d'*"absence de tout audit sur
l'increment servi"*. **C'est exactement le trou que `DEC-0045` avait ete ecrite pour fermer, rouvert
en un sprint.** Je ne touche pas au catalogue et je n'audite pas ce jeu aujourd'hui : je le route a
`sofia-sm` et `camille-po`, et je le mets dans ma file d'audit.

---

## Controle du 2026-08-27 (s20, cloture) - mon `VALIDE` porte-t-il encore sur ce qui est servi aujourd'hui ?

- **Auteure** : nadia-prof. **Objet** : verifier, avant la cloture de s20, que l'artefact servi a
  `round-two` `77dd467` est bien celui sur lequel j'ai prononce `VALIDE` le 2026-08-25 - mon verdict
  portait sur `f4e0808` avec `catalogue.yaml` rapiece dans une copie du jar, et trois commits ont
  suivi, dont l'entree de catalogue ecrite par `camille-po` (`DEC-0048`) et la validation de forme
  d'`ines-design` (`2,3rem`).
- **Je ne prends pas la reponse sur parole, et je ne la deduis pas d'un diff.** J'ai joue.

### 1. Ce que j'ai verifie avant de jouer, et comment

**L'objet mesure est bien l'arbre.** Arbre propre et `77dd467` confirmes par `git status
--porcelain` (vide) et `git rev-parse HEAD`, le 2026-08-27. Le jar servi est
`bootstrap/target/recreo.jar`, recopie hors du depot avant toute mesure parce que `yanis-qa` tenait
le reacteur en meme temps que moi et l'a efface deux fois sous ma main ; **je n'ai lance aucun
`mvn`**. J'ai compare octet par octet les ressources du jar aux fichiers de l'arbre - `catalogue.yaml`,
`contenu/cm1-reperes.yaml`, `contenu/cp-nature-mot.yaml`, `static/css/recreo.css`,
`templates/accueil.html`, `templates/fragments/manche.html`, `templates/fragments/retour.html` :
**sept sur sept identiques**, le 2026-08-27, par un script `zipfile` qui ouvre le jar imbrique
`BOOT-INF/lib/recreo-adapter-content-...jar` et compare les empreintes `sha256`. Le jar est donc
l'artefact de l'arbre, pas un reliquat.

**Ce qui a change entre l'increment que j'ai juge et celui d'aujourd'hui.** `git diff --stat
f4e0808..77dd467 -- adapters domain application bootstrap`, le 2026-08-27 : cinq fichiers, dont
**aucune feuille de style, aucun gabarit, aucun fichier de `cp-nature-mot`**. Le seul changement qui
touche ce jeu est l'entree de catalogue (`git diff f4e0808..77dd467 --
adapters/adapter-content/src/main/resources/catalogue.yaml`, le 2026-08-27 : quatre lignes
remplacees, `cm1-reperes` sort, `cp-nature-mot` entre). **La forme visuelle d'`ines-design` et le
contrat `ADR-0062` de `marc-dev` etaient deja dans `f4e0808`** : le `2,3rem` que son document du
2026-08-25 valide est celui que j'avais deja sous les yeux quand j'ai mesure `36,8 px`. Sa
validation ne change pas ce que l'enfant voit, elle l'accepte.

**L'entree de catalogue elle-meme est celle que j'ai jouee.** La consigne servie aujourd'hui est
*"Le mot entre guillemets est-il un nom ou un verbe ?"* ; c'est mot pour mot celle enregistree dans
mon releve du 2026-08-25 (`s20-audit-nat5-744x551.json`, champ `consigne`, valeur unique sur les 16
items). `camille-po` n'a pas reecrit le texte que l'enfant lit.

### 2. Rejoue, a la reference, sur l'artefact d'aujourd'hui

**Audit joue, non degrade.** Deux campagnes le 2026-08-27, jar de `77dd467` servi hors du depot sur
un port local, machine virtuelle du JDK 21 lu dans `~/.m2/toolchains.xml`, **applications arretees
par leur PID** (`Stop-Process -Id`, jamais par nom d'image).

Premiere campagne, commande exacte : `python squad/outils/nadia-prof/auditer-paquet.py
<url_locale> bandit-manchot-nature-mot-cp 16 <fichier_de_releve>`, avec les variables `CHROME` et
`PAQUET` positionnees. Resultat du 2026-08-27, **4 parties, 76 lectures, 16 items sur 16 vus a
744 x 551**, chaque item joue au repos **et apres une erreur volontaire** :

| Mesure, 2026-08-27, 16 items sur 16 | Resultat | Ma mesure du 2026-08-25 |
|---|---|---|
| Propositions par item / rangees | 2 / **1 rangee** | 2 / 1 rangee |
| Items a deux rangees | **0 sur 16** | 0 sur 16 |
| Bas du verdict apres un echec | **433,38 px** (valeur unique sur 16) | 433,38 px |
| Bas de l'indice apres un echec | **512,38 px** (valeur unique sur 16) | 512,38 px |
| Verdict sous le pli | **0 sur 16** | 0 sur 16 |
| Indice sous le pli | **0 sur 16** | 0 sur 16 |
| Lectures a defilement non nul | **0 sur 76** | 0 |

**Identique au centieme.** Releve :
`squad/conformite/releves-s20/s20-reaudit-naturemot-servi-744x551.json`.

Seconde campagne, sur le marquage lui-meme : une partie complete de **10 manches**, sonde de rendu
ecrite pour l'occasion, la premiere manche jouee **faux d'abord**. Resultat du 2026-08-27,
**10 manches sur 10** : `.mot-designe` present 10/10, **exactement un** par enonce 10/10, taille du
mot **36,8 px** contre **32 px** pour le reste de la phrase (rapport 1,15, la conversion exacte de
`2,3rem` sur les `2rem` de `.enonce`), fond `rgb(255, 245, 157)`, aucune bordure, **enonce sur une
seule ligne 10/10**, **deux guillemets francais dans l'enonce et zero dans la boite 10/10**, hauteur
du document **551 px** - le document ne depasse pas le pli, meme apres un echec. Le HTML servi est
`Tu &laquo;&nbsp;<span class="mot-designe">dessines</span>&nbsp;&raquo; un beau soleil.` Releve :
`squad/conformite/releves-s20/s20-reaudit-naturemot-marquage-744x551.json`. Capture apres un echec :
`squad/conformite/releves-s20/s20-naturemot-servi-744x551-apres-echec.png`.

**Le cout sur les autres jeux, reverifie autrement que la premiere fois.** Le 2026-08-25 je l'avais
mesure jeu par jeu, sur un echantillon de manches ; aujourd'hui je le verifie sur la source, ce qui
est plus fort qu'un echantillon : `grep -rl "partDesignee"
adapters/adapter-content/src/main/resources/contenu/`, le 2026-08-27, rend **un seul fichier**,
`cp-nature-mot.yaml` (17 occurrences comptees par `grep -rc`). **Aucun autre paquet du depot ne peut
declencher le marquage**, et ce n'est plus un constat d'echantillonnage.

### 3. Conclusion : **mon `VALIDE` du 2026-08-25 tient sur l'artefact du 2026-08-27**

Ce que l'enfant voit aujourd'hui est, au centieme de pixel et a l'octet du HTML, ce que j'ai juge.
`NAT-5` reste levee. **Aucune reserve bloquante sur ce jeu.** Le retour au catalogue execute par
`DEC-0048` porte sur l'increment que j'ai valide ; il n'y a rien a reprendre.

**Consequence sur `catalogue.yaml`, que je nomme sans y toucher** : rien ne change. L'entree
`bandit-manchot-nature-mot-cp` est legitime et le reste. Le geste appartenait a `camille-po` et
`sofia-sm`, il est fait, je le constate.

### 4. Un ecart de redaction que je route, parce qu'il decrit ma propre condition de travers

Le commentaire de `.mot-designe` dans `adapters/adapter-web/src/main/resources/static/css/recreo.css`
annonce *"Trois canaux non colores comme nadia-prof l'exige"* et compte la boite de fond parmi eux.
**J'ai mesure le contraire le 2026-08-25 et je le maintiens** : la boite est a **1,04:1** contre le
fond, **1,045:1** sous achromatopsie emulee - c'est un canal colore, il disparait quand la couleur
disparait. Ce qui subsiste sans couleur, capture a l'appui le 2026-08-27
(`Emulation.setEmulatedVisionDeficiency` en `achromatopsia`, sur l'artefact d'aujourd'hui), c'est
**l'agrandissement de 15 %**, et lui seul. Le commentaire compte trois canaux la ou il y en a un.
**Je ne modifie aucun fichier sous `adapters/`** : je route la phrase a `relecteur-pedagogique` et
`ines-design`. Cela ne rouvre pas `NAT-5` - ma condition est satisfaite par le canal qui reste, je
l'ai ecrit le 2026-08-25 et je ne deplace pas ce poteau-la.

### 5. Sur quelles proprietes je reste la seule garde, et ce que ce controle ne couvre pas

- **Que `partDesignee` designe le bon mot.** Inchange : aucun test ne verifie qu'une valeur de
  contenu corresponde au mot entre guillemets. Verifie a la main **16 sur 16 une troisieme fois** le
  2026-08-27.
- **`NAT-12`** (la boite sans contraste de luminance), **`NAT-10`, `NAT-11`** : ouvertes, non
  bloquantes, inchangees.
- **Ce qu'un lecteur d'ecran annonce** de l'enonce segmente en `span` : toujours non ecoute, par moi
  ni par personne dans cette squad. **Angle mort declare, pas une reserve.**
- **Ce qu'un enfant de six ans fait de la boite.** Je l'ai jugee convaincante en la regardant ; c'est
  un jugement de maitresse, **pas une mesure**, et aucune commande ne le remplacera. Le verdict de la
  section 3 s'appuie dessus : **propriete declaree degradee**, ici, dans le paragraphe qui s'en sert.
- **Limite de mesure, etat exact couvert.** Mes deux campagnes du 2026-08-27 tournent sur un
  navigateur lance avec `--hide-scrollbars` (c'est le mode de `balayage-largeur-rangee.py`, donc de
  tout l'outillage `nadia-prof`). **L'etat que je n'ai pas mesure est precisement celui-la** : le
  rendu avec une barre de defilement classique peinte, qui prendrait de la largeur au document. Sur
  ce jeu la question est sans consequence mesuree - le document fait **551 px**, il n'y a rien a
  faire defiler - mais je nomme l'etat plutot qu'une categorie voisine.
- **Toute largeur autre que 744 px** : non mesuree, inchange.

> **Note du 2026-08-27, ajoutee juste apres le depot de ce lot.** `round-two` a avance pendant mon audit : `9ccbb1d` (`yanis-qa`) puis `6228d77` (`theo-archi`) se sont poses au-dessus de `77dd467`. **Ils ne changent rien a ce que j'ai mesure**, et je le verifie plutot que je le suppose : `git diff --name-only 77dd467..6228d77 -- adapters domain application bootstrap`, le 2026-08-27, ne rend **aucun fichier** - les deux commits ne portent que des documents de `squad/`. L'artefact servi a la tete de branche est donc identique a celui que j'ai joue.
