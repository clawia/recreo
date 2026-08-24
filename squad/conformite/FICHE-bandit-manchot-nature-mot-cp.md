# Fiche de conformite - bandit-manchot-nature-mot-cp

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cp-nature-mot.yaml`
  (a creer, `type: question`, au moins 16 items), story `S19-4` (`docs/backlog/backlog.md`).
  **Adaptation reelle requise, pas une reprise** : `contenu-repris/mots/cm1-nature-mot.yml` (16
  items) classe des mots isoles (hors phrase) en quatre natures (nom, verbe, adjectif,
  determinant), a un niveau CM1. La competence CP demande exactement deux natures (nom, verbe) et
  exige que le mot soit presente dans une phrase tres simple, jamais isole. Les deux ecarts (forme
  et perimetre) changent a la fois ; ce paquet repasse integralement devant `nadia-prof` comme un
  contenu neuf (`contenu-repris/README.md`).
- **Statut global** : **ouverte, aucun increment livre a ce jour.** Rien n'a encore ete joue.
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
- **Derniere mise a jour** : 2026-08-24 (`lea-dev`, depot du paquet reecrit selon `NAT-1` a `NAT-8`)

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
