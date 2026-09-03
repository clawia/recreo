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
