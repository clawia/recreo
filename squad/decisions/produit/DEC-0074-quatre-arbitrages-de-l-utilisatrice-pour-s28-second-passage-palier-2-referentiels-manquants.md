# DEC-0074 - Quatre arbitrages de l'utilisatrice pour s28 : second passage sur les matieres neuves, anglais CM1, un lot maths CM1, palier 2 ouvert en chantier de mecanique sans fermeture de ligne, dix referentiels manquants ecrits par `nadia-prof`

- **Type** : perimetre de sprint, arbitrages multiples de l'utilisatrice.
- **Date** : 2026-09-03
- **Auteur** : `camille-po`
- **Sprint** : s28 (ouverture)
- **Competence programme visee** : transverse - pas de contenu pedagogique. Le perimetre qu'elle
  fixe touche plusieurs competences, chacune nommee avec sa propre ligne de referentiel dans la
  section 1 ci-dessous.
- **Statut** : Acceptee
- **Lien** : `DEC-0072` (regle "un premier paquet de matiere neuve ouvre une ligne, il ne la ferme
  pas", section 4 ; report de l'anglais CM1 a s28 ; recompte du denominateur a 75 le 2026-09-02) ;
  `DEC-0073` (cloture de s27) ; `ADR-0079` (l'unite de la borne d'audit de `nadia-prof` est le lot
  de matiere et de niveau, jusqu'a 20 items) ; `squad/conformite/ARBITRAGE-PREALABLE-s27-les-
  matieres-absentes-du-programme.md`, sections 2 (compte des enseignements) et 8.2 (tarifs) ;
  `ADR-0080`, en cours d'ecriture par `theo-archi` (faisabilite du palier 2, non prejugee ici).

---

## 1. Etat de la matrice a la cloture de s27, verifie ligne par ligne

Recompte effectue le 2026-09-03 sur `squad/programme/MATRICE-couverture.md` (bilan de cloture de
s27, section de synthese) : **35 `couvert-valide` / 75 lignes atteignables = 46,7 %**. Repartition
sur les 90 lignes que porte la matrice : 35 `couvert-valide`, 14 `en cours`, 26 `non couvert`, 15
`non couvrable en l'etat` (35 + 14 + 26 + 15 = 90 ; 90 - 15 = 75 atteignables).

Les **14 lignes `en cours`** se decomposent en deux blocs, tous deux verifies dans la matrice :

- **11 lignes histoire-geographie CM1**, servies par le premier paquet de la matiere
  (`bandit-manchot-histoire-geographie-cm1`) : les competences de reperage temporel (frises,
  siecles), "et avant la France ?", "le temps des rois" (une des deux competences du domaine), "le
  temps de la Revolution et de l'Empire", "decouvrir le(s) lieu(x) ou j'habite", "consommer en
  France" et "comprendre un document".
- **3 lignes questionner le monde CP**, chacune servie par son propre paquet
  (`bandit-manchot-vivant-non-vivant-cp`, `bandit-manchot-etats-eau-cp`,
  `bandit-manchot-corps-sens-cp`).

Aucune des 14 ne peut passer `couvert-valide` par le seul fait d'avoir deja recu un Done
pedagogique a s27 : `nadia-prof` a pose d'avance, dans `DEC-0072` section 4, que *"un premier
paquet de matiere neuve ouvre une ligne, il ne la ferme pas - saisie, illustre et voix ont tous
demande un second passage."* Fermer ces 14 lignes exige donc un second paquet sur chacune des deux
matieres, pas une nouvelle instruction sur les paquets deja livres.

## 2. Message de l'utilisatrice, verbatim

Juste apres la cloture de s27, en reponse a l'ordre de priorite propose par `camille-po` :
*"ok on suit ton ordre avec en plus un second passage sur les matieres neuves"*. Quatre questions
lui ont ensuite ete posees ; ses reponses sont actees ci-dessous, une section par arbitrage.

## 3. Arbitrage 1 - Perimetre de s28 : 4,5 sur la borne de 5, zero marge de rejeu

**Composition retenue**, sur la borne d'audit de `nadia-prof` (5 par sprint, unite = le lot de
matiere et de niveau jusqu'a 20 items, `ADR-0079`) et ses tarifs de s27 (`ARBITRAGE-PREALABLE-s27-
les-matieres-absentes-du-programme.md`, section 8.2 : matiere connue 1,0 ; matiere neuve textuelle
1,5 ; matiere neuve illustree 2,0) :

| Lot | Matiere au regard du tarif | Prix |
|---|---|---|
| Second paquet questionner le monde CP | desormais connue (premier paquet livre et audite a s27) | 1,0 |
| Second paquet histoire-geographie CM1 | desormais connue (premier paquet livre et audite a s27) | 1,0 |
| Anglais CM1 (lecture et ecriture, aucun oral - lignes "lire et comprendre" x2, "ecrire") | matiere neuve, textuelle | 1,5 |
| Un lot maths CM1 sur les deux lignes `non couvert` du domaine "Nombres et calculs" - multiplication posee, division euclidienne | connue | 1,0 |
| **Total** | | **4,5** |

**Cible de couverture** : jusqu'a 14 fermetures par les seconds passages, si les deux paquets
obtiennent un Done pedagogique complet et si chaque ligne qu'ils touchent est verifiee tenue sur le
second passage (pas seulement re-servie).

**Le risque, ecrit en toutes lettres parce que l'utilisatrice l'a choisi en le voyant** : la marge
residuelle sur la borne de `nadia-prof` est de **0,5 seulement**. Si un seul des quatre lots est
refuse - second passage QLM, second passage histoire-geo, anglais CM1 ou le lot maths - **aucun
rejeu de ce lot n'est possible dans s28** : la borne serait depassee. Un refus sur ce sprint se
traite au sprint suivant, pas par un second essai immediat.

## 4. Arbitrage 2 - Le palier 2 est ouvert en s28 comme chantier de mecanique, sans fermeture de ligne

Le palier 2 - designer un lieu ou un objet sur une image (plan, carte, planisphere, frise) - entre
au perimetre de s28 **comme chantier de mecanique uniquement** : `theo-archi` en ecrit l'`ADR`
(`ADR-0080`, en cours), un developpeur l'implemente. Cette decision ne prejuge ni la faisabilite ni
la forme du palier 2 : c'est le travail d'`ADR-0080`.

**Aucune ligne de matrice ne se ferme par ce chantier dans s28.** Le gain, verifie ligne par ligne
dans `squad/programme/MATRICE-couverture.md`, arrive au plus tot a s29, sur les competences
suivantes qui portent aujourd'hui le motif explicite "palier 2 requis" :

- mathematiques CP, "Espace et geometrie" : *"Se reperer et decrire des deplacements sur un
  quadrillage simple"* (`non couvert`).
- questionner le monde CP, "Se situer dans l'espace" : *"Situer un lieu ou un objet sur un plan
  simple de la classe, de l'ecole ou du quartier"* (`non couvert` - palier 2 requis, distinct de
  `non couvrable en l'etat`, `nadia-prof` : *"le palier 2 est constructible, il n'existe pas"*).
- questionner le monde CP, "Se situer dans l'espace" : *"Situer la France et quelques espaces
  etudies sur une carte ou sur un globe"* (`non couvert`, meme motif).
- questionner le monde CP, "Se situer dans le temps" : *"Situer des evenements les uns par rapport
  aux autres sur une frise chronologique simple"* - **gain partiel seulement** : la moitie
  anteriorite/posteriorite est deja jouable hors palier 2, seul le placement sur la frise en
  depend.
- histoire-geographie CM1, "Se reperer dans l'espace" : *"Se reperer sur une carte de France et sur
  un planisphere"* (`non couvert`, palier 2 requis).
- histoire-geographie CM1, "Se reperer dans l'espace" : *"Situer son lieu de vie a differentes
  echelles"* (`non couvert`) - **nuance a garder** : cette ligne porte un second obstacle
  independant du palier 2, le lieu de vie de l'enfant n'etant connu d'aucune session faute de
  persistance (`DEC-0028`) ; le palier 2 seul ne suffira pas a la fermer.

Six lignes concernees, dont une a gain partiel et une a second obstacle non leve par la mecanique
seule : aucune n'entre dans la cible de couverture de s28.

## 5. Arbitrage 3 - Les dix referentiels manquants sont ecrits en s28 et integres a la matrice

Six enseignements n'ont aucune ligne dans la matrice (`squad/conformite/ARBITRAGE-PREALABLE-s27-
les-matieres-absentes-du-programme.md`, section 2) : arts plastiques, education musicale, EPS, EMC
(cycles 2 et 3), sciences et technologie (cycle 3), histoire des arts (cycle 3) - huit enseignements
au cycle 2, dix au cycle 3, sur le critere retenu (un enseignement par chapitre de programme
portant ses propres attendus de fin de cycle). Cela represente dix fichiers de referentiel
manquants (les quatre enseignements communs aux deux cycles, deux fois chacun, plus sciences et
technologie et histoire des arts au seul cycle 3).

`nadia-prof` les ecrit en s28. `camille-po` les integre a
`squad/programme/MATRICE-couverture.md` des qu'ils sont livres.

**Consequence a ecrire sans l'adoucir : le taux affiche va rechuter fortement.** Le precedent est
deja mesure dans ce depot : le 2026-09-02, l'entree de 43 lignes (trois referentiels neufs) a fait
passer le taux de 82,9 % a 45,3 % (`DEC-0072`, denominateur 41 -> 75, `couvert-valide` inchange a
34). Ce n'est pas une regression du produit : c'est le denominateur qui devient honnete avant qu'on
investisse des sprints sur un denominateur faux. **Le chiffre exact n'est pas estime ici** : les dix
referentiels ne sont pas encore ecrits, et l'estimer avant qu'ils existent reviendrait a deviner un
denominateur, exactement ce que cette section refuse de faire ailleurs dans ce depot. Le recompte
se fera a l'integration, par `camille-po`, sur les referentiels tels que `nadia-prof` les livre.

## 6. Arbitrage 4 - s28 est execute par la squad, sans exception de process

s28 suit le meme process que tous les sprints precedents : ceremonies dans
`squad/sprints/s28-<slug>/`, commits prefixes par agent, Done technique juge par la CI, **Done
pedagogique prononce par `nadia-prof` seule**. Aucune des trois ouvertures ci-dessus (arbitrages 1
a 3) ne cree de raccourci de gouvernance.

## 7. Ce que cette decision ne fait pas

- Elle ne prononce aucun Done, technique ou pedagogique. Les quatre lots de la section 3 restent a
  developper et a auditer.
- Elle ne fixe pas le prix des lots : le tarif cite en section 3 est celui deja pose par
  `nadia-prof` a s27 (`ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, section
  8.2) ; son arbitrage prealable propre a s28, en cours d'ecriture en parallele de cette decision,
  reste seul competent pour toute condition ou reserve pedagogique nouvelle sur ces quatre lots.
- Elle n'ecrit aucune story : la traduction du perimetre en stories testables, avec criteres
  d'acceptation, releve du planning de sprint.
- Elle ne prejuge pas de la faisabilite du palier 2 : c'est `ADR-0080`, en cours d'ecriture par
  `theo-archi` en parallele de cette decision, qui la tranche.
- Elle ne fait bouger aucune ligne de matrice : `squad/programme/MATRICE-couverture.md` n'est
  modifiee qu'au planning et a la review, selon sa propre regle de mise a jour.

## 8. Amendement du 2026-09-03 (meme jour) - arbitrage prealable de `nadia-prof` : le prix reel est
## 5,0 et non 4,5, six a sept lignes fermeront et non quatorze, trois corrections a ses propres
## constats, et l'arbitrage final de l'utilisatrice sur ce qui reste dehors

**Route par** : `squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-
quatre-lots.md` (`nadia-prof`, 2026-09-03, commit `1cf80f4`), rendu apres cette decision et avant
le planning de s28. Les corrections ci-dessous portent sur les sections 1 et 3 telles qu'ecrites
plus haut ; elles ne les remplacent pas, elles les rectifient a la source.

### 8.1 Le prix reel du perimetre est 5,0, et non 4,5 : zero marge

La section 3 appliquait a l'identique le tarif de s27 (matiere neuve textuelle 1,5, matiere connue
1,0) et annoncait 4,5. `nadia-prof` corrige ce tarif **avant que le contenu ne soit produit**, sur
son propre critere : ce n'est pas *matiere neuve* contre *matiere connue* qui fixe le prix, c'est
**suis-je la source de cette matiere ?** - un cout qui se paie **a l'item**, et qui **ne s'eteint
jamais** quand la matiere devient familiere au produit.

Consequence, dans les deux sens :

- **Histoire-geographie CM1 reste a 1,5 a son second lot.** `nadia-prof` n'en sera jamais la
  source : vingt faits neufs a verifier contre autre chose que sa memoire coutent le meme prix,
  que ce soit le premier lot ou le cinquieme.
- **Questionner le monde CP descend a 1,0.** `nadia-prof` en est la source - le vivant, le corps,
  les objets, elle les enseigne. Elle l'avait deja designee a s27 comme la moins chere des trois
  matieres neuves, et facturee 1,5 quand meme, par simplicite de bareme. Elle corrige dans le sens
  qui lui coute : c'etait une surfacturation.

| Lot | Tarif applique | Prix |
|---|---|---|
| Second paquet questionner le monde CP | elle est la source, matiere connue | 1,0 |
| Second paquet histoire-geographie CM1 | elle n'est pas la source, et ca ne s'eteint pas | 1,5 |
| Anglais CM1, premier lot, textuel | elle n'est pas la source | 1,5 |
| Un lot maths CM1 sur le calcul pose | elle est la source | 1,0 |
| **Total** | | **5,0** |

Le total de 4,5 annonce en section 3 est donc **remplace par 5,0**. Le perimetre tient exactement,
au plafond de la borne de `nadia-prof`, **avec zero marge** - et seulement sous trois conditions
qu'elle nomme parce qu'aucune n'est acquise :

| Condition | Si elle tombe |
|---|---|
| L'anglais CM1 est textuel, jamais illustre | +0,5 (`REC-1` s'applique) -> 5,5 : elle decline par ecrit |
| Le lot de questionner le monde n'ouvre pas les lignes 15 ou 16 | elle ne facture pas de supplement, mais la fermeture des trois lignes tombe : un lot ne peut pas a la fois fermer et ouvrir |
| La dette des paquets a voix reste dehors du sprint | +0,25 au regime du mot pour mot -> 5,25 : ne tient pas |

Elle decline par ecrit, sur le meme modele qu'a s27, les compositions qui depassent la borne :
perimetre annonce avec anglais **illustre** plutot que textuel (5,5) et perimetre annonce plus la
dette des paquets a voix (5,25). Deux compositions restent sous la borne si un lot en sort : sans
le lot de mathematiques (4,25, marge 0,75) ou sans l'anglais (3,75, large marge). Elle ne choisit
pas laquelle : elle dit seulement que le lot de mathematiques est celui dont le retrait ne referme
aucune matiere entiere.

### 8.2 Six a sept lignes fermeront en s28, pas quatorze

La section 3 annoncait une cible "jusqu'a 14 fermetures". `nadia-prof` la remplace par un plafond
arithmetique et une attente raisonnable, chiffres a dire avant le sprint et non en review :

- **Plafond arithmetique : +9 lignes, soit 44/75 = 58,7 %**, et seulement si les quatre lots
  tiennent chacune de leurs conditions (`FER-1` a `FER-3` sur les deux lots de fermeture, `ANG-4`
  sur l'anglais, `POS-1` a `POS-4` sur le calcul pose).
- **Attente raisonnable, dite avant le sprint : +6 a +7 lignes.** Les deux lots les plus fragiles
  sont ceux qui appliquent pour la premiere fois une condition qui n'a jamais rencontre de contenu
  reel - le lot de mathematiques (`POS-2`, `POS-3`) et le lot d'anglais (`REC-5`, `REC-6`, matiere
  dont elle n'est pas la source).

Le motif qui fonde ce chiffre est **mesure, pas suppose** : `cm1-histoire-geographie.yaml` sert
**onze lignes de referentiel avec vingt items**, soit 1,8 item par competence, et **six des onze
lignes sont servies par exactement un item**. Une competence servie par un seul item n'est pas
couverte, elle est illustree une fois. Fermer les onze lignes demanderait quatre-vingt-dix items
neufs, soit **6,75 lots contre la borne de 5** - pour cette seule matiere. Consequence directe :

- **Questionner le monde CP** : les trois lignes ouvertes (vivant/non-vivant, corps et sens,
  etats de l'eau) peuvent toutes fermer, **si et seulement si** le second lot leur est
  integralement consacre et n'ouvre aucune ligne neuve de la matiere (`FER-1` a `FER-3`).
- **Histoire-geographie CM1** : trois lignes au mieux ferment sur les onze, jamais plus dans un
  seul lot de vingt items. Deux compositions exclusives tiennent en vingt items - composition A
  (trois lignes d'histoire : roi/realisation, Revolution, comprendre un document) ou composition B
  (deux lignes de geographie : types d'espaces, eau/energie/aliments, qui paie de surcroit le
  desequilibre releve par `nadia-prof` a la porte de s27, seize items d'histoire pour quatre de
  geographie). `nadia-prof` ne choisit pas entre les deux : c'est un arbitrage produit.
- Dans tous les cas, **huit ou neuf des quatorze lignes ouvertes restent `en cours` a la fin de
  s28**, meme si les quatre lots obtiennent un Done pedagogique complet.

### 8.3 Trois corrections que `nadia-prof` fait a ses propres constats avant de s'en servir

- **Son motif de s27 pour ne pas fermer est epuise, et n'est plus le motif retenu.** Elle avait
  ecrit le 2026-09-02 que les paquets de matiere neuve ne fermeraient aucune ligne parce qu'ils
  rencontraient des conditions (`REC-*`, `BIA-*`, `HIS-*`) qu'aucun paquet n'avait encore eu a
  tenir. **C'etait une probabilite, et l'audit de s27 l'a dementie** : cinq verdicts VALIDE et un
  VALIDE SOUS RESERVE non bloquante, aucune erreur de fait sur onze faits verifies un par un.
  Maintenir les quatorze lignes ouvertes sur ce motif reviendrait a deplacer ses propres poteaux
  dans le sens qui l'arrange. **Le motif qui remplace le sien est la densite**, mesure en section
  8.2 et tourne en regle opposable (`FER-1` a `FER-3` : extension, au moins dix items par ligne,
  au moins deux taches distinctes) - a elle-meme comme a l'equipe, a partir de s28.
- **La dette des paquets a voix touche six jeux et cinq indices, pas trois paquets et un indice.**
  Le backlog attribuait cette dette a trois paquets
  (`bandit-manchot-lettre-du-son-cp`, `bandit-manchot-digraphe-du-son-cp`,
  `bandit-manchot-voyelle-du-son-cp`). `nadia-prof` a mesure, entree par entree, que la consigne et
  l'enonce se repetent sur **six** jeux (les trois precedents plus `bandit-manchot-position-
  syllabe-cp`, `bandit-manchot-son-dans-mot-cp` et `saisie-mot-dicte-cp`, ce dernier
  partiellement), et que **cinq** de leurs indices, non un seul, invitent l'adulte a un geste
  interdit par `VOI-3`. Elle nomme `cp-son-dans-mot` comme le cas le plus grave : son indice
  demande a l'adulte de redire le mot "lentement", quand `VOI-3` exige "a vitesse normale" - sur le
  paquet meme ou la competence est d'entendre un son dans un mot, c'est-a-dire la ou un mot ralenti
  donne la reponse. Cette correction ne revoque aucun Done ; c'est une reserve nouvelle, datee du
  2026-09-03, qui elargit une reserve deja ecrite a s27.
- **Son audit de s27 n'a verifie la plausibilite des distracteurs que sur les items tires en
  jouant.** Elle avait ecrit avoir verifie onze faits d'histoire "sur les items joues" ; le paquet
  porte vingt items tires uniformement sans remise sur dix manches, donc **la moitie du paquet
  n'est jamais vue en jouant**. En relisant l'autre moitie, elle a trouve un item ou les deux
  distracteurs sont absurdes et ou la bonne reponse reduit un espace rural a un seul trait
  (`HG-R3`). **C'est nouveau, date du 2026-09-03, et non bloquant** : le Done pedagogique de s27
  tient, la correction est due dans le lot de s28.

### 8.4 Arbitrage de l'utilisatrice du 2026-09-03 : rien ne sort, les quatre lots restent a 5,0, la
### dette des paquets a voix est reportee a s29

Presentee avec les trois compositions qui tiennent sous la borne de 5 (perimetre integral a 5,0 ;
perimetre moins le lot de mathematiques plus la dette a 4,25 ; perimetre moins l'anglais plus la
dette a 3,75), l'utilisatrice tranche : **rien ne sort. Les quatre lots de la section 3 restent
inchanges, a 5,0, zero marge. La dette des paquets a voix (section 8.3) est reportee a s29.**

**Ce que ce choix coute, dans les termes de `nadia-prof`, sans les adoucir :**

- **Le sprint est plein.** A 5,0 sur une borne de 5, **aucun rejeu n'est possible si un seul des
  quatre lots est refuse en cours de sprint** : la borne serait depassee. Un refus se traite au
  sprint suivant, pas par un second essai immediat - le risque deja ecrit en section 3 est
  confirme, non attenue par la correction de prix.
- **Pendant ce temps, le produit continue de demander par ecrit a l'adulte, sur cinq indices, un
  geste que `VOI-3` lui interdit** - redire un mot "doucement" ou "lentement" la ou la competence
  exige qu'il soit dit "d'un seul tenant, a vitesse normale" - **sur des lignes toutes
  `couvert-valide`**. `nadia-prof` l'a ecrit elle-meme et ce document le reprend sans le reformuler
  : *"une ligne fermee n'est plus regardee"*. Un defaut sur une ligne fermee est de la famille de
  ceux qui restent, et `VOI-3` n'a et n'aura aucun garde-fou automatise - `nadia-prof` est la seule
  garde de cette propriete, et elle n'est pas dans la piece quand l'enfant joue.
