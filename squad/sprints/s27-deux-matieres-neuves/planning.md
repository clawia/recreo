# [Sprint 27] Planning - 2026-09-02

- **Horodatage de debut** : 2026-09-02 22:08
- **Anime par** : sofia-sm
- **Participants** : sofia-sm (SM et dev), theo-archi (en cours, en parallele, voir section
  "Avis de faisabilite"), marc-dev, lea-dev, nadia-prof (arbitrages prealables deja rendus,
  merges ci-dessous), camille-po (decisions produit deja rendues, merges ci-dessous),
  ines-design (obstacle route, gate sur le lot technique de theo-archi)

## 0. Ce qui entre au sprint avant qu'il ne s'ouvre

Trois branches portaient un travail deja fait, hors de `round-two` - le defaut deja nomme quatre
fois depuis s26 (une decision qui s'appuie sur un fichier reste sur une branche separee). Fait
entrer avant l'ouverture, dans ce worktree dedie (`s27-sofia`, ancetre verifie de `round-two` par
`outils/verifier-lignee.sh`) :

| Branche | Commit | Contenu | Fusion |
|---|---|---|---|
| `retours-utilisatrice` | `c5e7e2c` | `camille-po` : matrice corrigee (43 lignes neuves), `DEC-0070`, `DEC-0071`, `DEC-0072`, quatre referentiels | `f6801b2` |
| `matieres-absentes` | `fcdc365` | `nadia-prof` : referentiel des matieres absentes, arbitrage titre/couleur, deux fiches de conformite | `efa5c95` |
| `design-monnaie` | `bf18ee0` | `ines-design` : **rien de commite** - son tip est un ancetre deja present dans `round-two` (`git log round-two..design-monnaie` vide), sa proposition vit hors du depot | Rien a fusionner |

**Fusionnees sans conflit** (aucun fichier commun entre les deux lots). Poussees sur `origin/round-two`
en fast-forward (`9294632..efa5c95`) depuis le worktree `s27-sofia`, puis rapatriees en fast-forward
dans le worktree principal par un `git fetch` + `git merge --ff-only` (aucune ecriture de
fichier dans ce worktree - seule une synchronisation de reference).

**Verification technique sur l'arbre fusionne**, `round-two` a `efa5c95` : `mvn -B clean verify`
sur le reacteur complet (racine, pas de `-pl`). **BUILD SUCCESS. 1090 tests, 0 echec, 0 erreur**
(somme de l'attribut `tests="..."` des 150 rapports XML surefire/failsafe du reacteur, methode de
l'amendement s11). Chiffre inchange par rapport a la cloture de s26 (`a5147d8`, meme total) : les
deux lots fusionnes ne touchent aucun fichier Java, seulement de la documentation et des
referentiels.

## Objectif du sprint

**Fermer la derniere ligne de graphemes du francais CP, ouvrir la premiere ligne de deux matieres
neuves (questionner le monde CP, histoire-geographie CM1), et corriger le rendu couleur de la
monnaie sur le motif releve par l'utilisatrice - sans promettre plus qu'une seule fermeture de
ligne de matrice.**

Perimetre arbitre par l'utilisatrice, verbatim (`DEC-0072`, section 3) : *"Voyelles simples +
Questionner le monde + Histoire-geographie. L'anglais CM1 est reporte a s28."*

**Le taux d'entree du sprint est 34/75 = 45,3 %**, pas 82,9 %. Ce n'est pas une regression : la
matrice a ete corrigee **avant** l'ouverture de ce sprint, sur decision de l'utilisatrice, pour que
la baisse se lise comme une correction de mesure (denominateur 41 -> 75 par l'entree de 43 lignes
de trois matieres jusque-la absentes du referentiel, numerateur inchange a 34). Deux comptages
independants concordent au chiffre pres : celui de `nadia-prof` (son arbitrage, section 3) et celui
de `camille-po` (`DEC-0072`, section 2, et `MATRICE-couverture.md`, section "Integration des
matieres absentes du 2026-09-02").

**Plafond honnete du sprint, de `nadia-prof`** (arbitrage prealable, section 8.4) : *"un premier
paquet de matiere neuve ouvre une ligne, il ne la ferme pas - saisie, illustre et voix ont tous
demande un second passage."* **Une seule fermeture de ligne est possible en s27** : celle des
graphemes, par `S27-1`. Les paquets de questionner le monde et d'histoire-geographie passeront
leurs lignes au plus a `en cours`, jamais a `couvert-valide`, quel que soit leur Done pedagogique.
Aucun texte de ce sprint ne promet davantage.

**Prix face a la borne de `nadia-prof` (4 a 5 paquets par sprint sur mecanique existante)** :
voyelles (1) + questionner le monde (1,5) + histoire-geographie (1,5) + rejeu de la monnaie apres
correctif de couleur (0,5) = **4,5 sur une borne de 5**. Nadia l'ecrit elle-meme : *"c'est plein.
Tout ajout supplementaire me fait declarer par ecrit."*

## Stories retenues

| Story | Titre | Competence programme visee | Decision liee | Affectee a |
|---|---|---|---|---|
| `S27-1` | Le paquet de voyelles simples ferme la ligne des graphemes | Francais CP - "Associer les graphemes aux phonemes" (clause 1 restante) | `DEC-0069` (rang 1), `DEC-0072` | `lea-dev` |
| `S27-2` | Premier paquet de questionner le monde (CP) | Questionner le monde CP, matiere neuve - lignes jouables listees section 7.1 de l'arbitrage de `nadia-prof` | `DEC-0072` | `marc-dev` |
| `S27-3` | Premier paquet d'histoire-geographie (CM1) | Histoire-geographie CM1, matiere neuve - lignes jouables listees section 7.2 de l'arbitrage de `nadia-prof` | `DEC-0072` | `marc-dev` |
| `S27-4` | Le jeu d'additions CP porte un titre du meme registre que les autres jeux du catalogue | Mathematiques CP - contenu et mecanique inchanges, seul le titre bouge | `DEC-0070`, arbitrage `nadia-prof` du 2026-09-02 (titre choisi : **"Les petites additions"**) | `sofia-sm` |

`S27-4` etait bloquee (`docs/backlog/backlog.md`, "non affectee a un sprint... ne peut pas etre
developpee avant que `nadia-prof` ait choisi le titre") : elle est debloquee par son arbitrage
fusionne ce jour (`efa5c95`) et affectee ici pour la premiere fois.

**Rejeu de `cp-monnaie` (0,5, hors story de catalogue)** : n'est pas une story de developpement
mais une verification de `nadia-prof` sur les douze items et leurs illustrations, une fois le
correctif de couleur d'`ines-design` merge (voir "Lots techniques" ci-dessous). Prix compte dans le
total du sprint (section "Objectif").

## Lots techniques - hors stories de contenu, affectes par moi

| Lot | Description | Affecte a | Etat a l'ouverture |
|---|---|---|---|
| **a. Retrecissement du garde-fou de couleur** | Le garde-fou interdit aujourd'hui tout `fill` et applique `FIG-4` (clause du seul paquet des figures planes) a toute la population des illustrations, la ou `FIG-3`/`HOR-3` n'exigent que `COU-2` (rendu en niveaux de gris, meme reponse) - motif et remede ecrits par `nadia-prof` dans son arbitrage, section 2 | `theo-archi` | **En cours** - lance en parallele avant l'ouverture de ce planning ; worktree `s27-theo`, un fichier de test modifie et non commite au moment de ce planning (`ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest.java`) |
| **b. Cliquet de poids** (`ADR-0036` clause 5) | +90 octets par fichier, 660 au total, entierement dus aux attributs `fill` - a relever dans le meme commit que (a), avec motif et valeur mesuree | `theo-archi` | Route avec (a), meme commit attendu |
| **c. Regle generale executable "aucun titre de jeu ne porte son niveau"** | Recommandee par `DEC-0070` section 3 et confirmee par l'arbitrage de `nadia-prof` (le releve des 40 titres, registre nominal dominant) ; forme technique non choisie (test de contenu sur `adapter-content`, ou script au meme titre que `verifier-chemins.sh`) | `theo-archi` (route par moi, apres `S27-4`) | Route, pas encore instruit |
| **d. Commit des douze SVG d'`ines-design`** | Sa proposition de correctif de couleur n'est aujourd'hui commitee nulle part (`design-monnaie` a `bf18ee0` ne porte rien de neuf) | `ines-design` | **Bloque par (a)** : elle ne peut commiter un rendu qui depend d'un garde-fou pas encore retreci |

## Avis de faisabilite - theo-archi

| Story | Avis | ADR requis |
|---|---|---|
| `S27-1` | **En attente.** `theo-archi` est engage sur les lots (a)/(b) au moment de ce planning ; la story s'appuie sur le champ d'item existant (`ADR-0075`) et sur `cp-le-bon-son.yaml` sans le modifier (motif deja chiffre a s26) - aucun signal d'infaisabilite connu, mais aucun avis ecrit ne remplace le sien | Non attendu, sur la lecture actuelle |
| `S27-2` | **En attente**, meme motif | A confirmer : premiere matiere en choix textuel pur, sur la mecanique bandit-manchot existante |
| `S27-3` | **En attente**, meme motif | A confirmer, meme famille que `S27-2` |
| `S27-4` | **En attente** | Non attendu : changement de donnee (`catalogue.yaml`) et d'un test qui la cite en dur, aucune ligne de mecanique touchee |

**Cette table sera completee par une entree datee de `theo-archi`, pas reecrite** (section 2 de
`squad/README.md`, exception nommee pour une ceremonie encore ouverte), des qu'il rend son avis sur
les quatre stories ci-dessus - actuellement absorbe par les lots (a)/(b) qu'il a commences avant ce
planning et que je n'ai pas interrompus.

## Ce que `nadia-prof` route, porte ici pour tracabilite

- **Le compte des enseignements de cycle 3 est incoherent dans son propre arbitrage** (neuf annonces
  en titre de section, dix nommes dans son propre tableau) : `camille-po` l'a trouve en verifiant
  ligne par ligne (`DEC-0072`, section 5) ; sans consequence sur la matrice tant qu'aucune ligne
  d'arts, d'EPS, d'EMC, de sciences et technologie ou d'histoire des arts n'y entre, mais **a
  trancher par `nadia-prof`** avant que l'une de ces matieres n'entre. Route a `nadia-prof`.
- **`HIS-1` (au plus un tiers d'items de restitution), `HIS-2` (aucun item a deux reponses
  defendables), `HIS-3` (chaque fait porte sa source)** : conditions bloquantes de `nadia-prof` sur
  le paquet d'histoire-geographie (`S27-3`), motif *"le bandit-manchot produit spontanement du quiz
  de dates, ce que l'histoire de cycle 3 n'est pas."* Deja portees dans les criteres d'acceptation
  de `S27-3` (`docs/backlog/backlog.md`) : je les reporte ici pour qu'elles restent visibles au
  daily et a la review.
- **La dette "un fichier reste sur une branche pendant qu'une decision s'appuie dessus"**, quatre
  incidents constates depuis s26 (le quatrieme ce matin, sur les trois branches de la section 0
  ci-dessus). `ADR-0066` n'en attrape qu'une partie (les identifiants `ADR-*`/`DEC-*`) ; l'extension
  necessaire reste routee a `theo-archi`, non tranchee. Je la reroute explicitement ici plutot que
  de la laisser vivre seulement dans la memoire de la retro de s26.

## Decisions prises

Aucune decision produit ni architecture n'est prise par moi dans ce planning : je merge du travail
deja tranche par ses auteurs (`camille-po`, `nadia-prof`), j'affecte nominativement les stories et
les lots techniques, et je route ce qui reste ouvert. Les seules "decisions" de ce document sont
d'organisation (affectations, ordre de traitement) et n'entrent pas dans la table des `ADR`/`DEC`.

## Prochaines etapes

- `theo-archi` : termine et committe les lots (a) et (b) dans le meme commit, worktree `s27-theo`,
  puis rend son avis de faisabilite sur `S27-1` a `S27-4` (addendum date a ce fichier).
- `ines-design` : commite ses douze SVG des que (a) est fusionne sur `round-two`.
- `nadia-prof` : rejoue `cp-monnaie` (0,5) des que le correctif de couleur est fusionne ; tranche le
  compte des enseignements de cycle 3 quand elle le peut (pas bloquant pour s27).
- `lea-dev` (`S27-1`), `marc-dev` (`S27-2`, `S27-3`), `sofia-sm` (`S27-4`) : demarrent des que
  l'avis de `theo-archi` est rendu, chacun dans un worktree dedie (`git worktree add
  ../recreo-s27-<agent> -b s27-<agent> round-two`), verifie par `outils/verifier-lignee.sh` avant
  le premier commit.

---

## Addendum du 2026-09-02 (sofia-sm) - ecart d'affectation journalise, jugement rendu, deux points routes

- **Auteure de l'addendum** : `sofia-sm`.
- **Ce qu'il ne fait pas** : rien du texte ci-dessus n'est modifie ni retire.

### L'ecart d'affectation, et son auteur

**L'auteur de l'ecart est l'orchestrateur**, meme motif qu'a s26 avec une circonstance differente :
la, il n'avait pas lu la table ; ici, elle n'etait pas encore ecrite quand `lea-dev` et `marc-dev`
ont ete lances. La table "Stories retenues" ci-dessus affecte `S27-2` a `marc-dev`. Dans les faits,
**`S27-2` tourne sous `lea-dev`**.

| Story | Ecrit a l'ouverture | Ce qui tourne reellement |
|---|---|---|
| `S27-1` voyelles simples | `lea-dev` | `lea-dev` - concorde |
| `S27-2` questionner le monde CP | `marc-dev` | **`lea-dev`** |
| `S27-3` histoire-geographie CM1 | `marc-dev` | `marc-dev` - concorde |

**Table d'affectation, mise en accord avec ce qui tourne reellement, a la date de cet addendum** :

| Story | Titre | Competence programme visee | Decision liee | Affectee a |
|---|---|---|---|---|
| `S27-1` | Le paquet de voyelles simples ferme la ligne des graphemes | Francais CP - "Associer les graphemes aux phonemes" (clause 1 restante) | `DEC-0069` (rang 1), `DEC-0072` | `lea-dev` |
| `S27-2` | Premier paquet de questionner le monde (CP) | Questionner le monde CP, matiere neuve | `DEC-0072` | **`lea-dev`**, et non `marc-dev` comme ecrit a l'ouverture |
| `S27-3` | Premier paquet d'histoire-geographie (CM1) | Histoire-geographie CM1, matiere neuve | `DEC-0072` | `marc-dev` |
| `S27-4` | Titre "Les petites additions" | Mathematiques CP - titre seul | `DEC-0070` | `sofia-sm` |

### Jugement sur la reaffectation - c'est mon role de le rendre, pas de la subir

**Motif transmis** : `lea-dev` a ecrit les deux paquets de graphemes de s26 et connait de premiere
main les conditions de distribution de `nadia-prof` - les voyelles lui reviennent naturellement.
`histoire-geographie` seule est un lot lourd (`HIS-3` oblige a verifier chaque fait contre une
source, cout que `nadia-prof` facture explicitement - `DEC-0072`, section 5, citant sa propre
section 8.2 : *"le cout de verification factuelle est [...] le plus eleve"* sur l'histoire des
trois paquets du sprint). Deux paquets a `marc-dev`, dont celui-la, auraient ete desequilibres.

**Je retiens cette repartition.** Elle est verifiable sur deux faits ecrits ailleurs dans ce depot,
pas seulement sur l'intuition qui la motive : `S27-3` porte a elle seule le cout d'audit le plus
eleve des trois paquets (`DEC-0072` section 5, chiffre non invente ici), et `lea-dev` porte deja le
contexte de premiere main sur les conditions de `nadia-prof` pour un paquet de graphemes (source :
son propre travail livre a s26, `s26-contenu`, `ab574e2`). Charger `marc-dev` de `S27-3` seul et
`lea-dev` de `S27-1` + `S27-2` repartit le poids reel (audit + connaissance acquise), pas seulement
le compte de paquets par tete. **Je ne redistribue pas.**

### Deux points routes

1. **`ines-design` et les douze SVG** : toujours rien dans le depot a la date de cet addendum, lot
   bloque par (a) (`theo-archi`, garde-fou de couleur) - inchange depuis l'ouverture de ce planning.
   **Je demande a l'orchestrateur de la relancer des que le lot (a) de `theo-archi` est fusionne sur
   `round-two`** : je n'ai moi-meme aucun moyen d'invoquer un autre agent de la squad dans cette
   session.
2. **`DEC-0070`** reste au conditionnel dans son propre fichier (statut "Proposee", choix du titre
   au conditionnel) alors que `nadia-prof` a tranche ("Les petites additions", arbitrage du
   2026-09-02, quatre motifs dont la sous-specification de "Calcul CP" que personne n'avait vue
   avant elle). Je n'ai pas amende cette `DEC` a la place de `camille-po` - c'est bien son fichier -
   et l'orchestrateur confirme la relancer dessus. Sans consequence sur `S27-4`, dont les criteres
   d'acceptation citent deja le titre choisi independamment du statut de la `DEC`.

> **Note du 2026-09-03 (`sofia-sm`)** : le point 2 ci-dessus est perime treize minutes apres
> son ecriture - `camille-po` a amende `DEC-0070` en "Acceptee" le meme jour, avec le titre
> "Les petites additions" tranche par `nadia-prof`. Sans consequence fonctionnelle, releve par
> le `relecteur-pedagogique`. Le recit est laisse tel quel au-dessus : il decrit fidelement
> l'etat au moment ou il a ete ecrit, cette note dit ce qui a change depuis.

### Etat des lots en vol a la date de cet addendum

| Agent | Lot | Etat |
|---|---|---|
| `theo-archi` | Garde-fou de couleur (a) + cliquet de poids (b) | En cours, worktree `s27-theo` |
| `lea-dev` | `S27-1` (voyelles), puis `S27-2` (questionner le monde) | En cours |
| `marc-dev` | `S27-3` (histoire-geographie) | En cours |
| `ines-design` | Commit des douze SVG | Bloque par (a), pas encore lance |
| `sofia-sm` | `S27-4` (titre) | Pas encore commencee |

Rien d'autre n'est lance a cette date.
