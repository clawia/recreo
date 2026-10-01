# Rapport de sprint s45 - addition avec retenue CE1, instruments de musique CP, roles d'EPS CM1
(instance A) et usages numeriques CM1 (instance B), dispositif a deux instances paralleles de
`nadia-prof`

- **Duree** : 2026-10-01 10:58 -> 2026-10-01 14:59:54 (UTC+02:00), 4 h 01 min 54 s.
- **Objectif gele** (`DEC-0101`, Acceptee, engagee le 2026-10-01, amendee le meme jour section 10) :
  fermer, avec deux instances paralleles de `nadia-prof`, quatre des cinq lignes initialement
  engagees - `MAT-CE1-04`, `MUS-CP-05`, `EPS-CM1-09`, `SCI-CM1-14` -, apres le retrait de
  `LVE-CM1-06` (`S45-LVE-ECRITURE`) le meme jour, avant l'ouverture du planning, au motif `R6`
  (refus de la saisie sous correction automatique de clavier). Budget de sprint opposable 8,0,
  **total reel engage 5,5/8,0** (et non 7,0/8,0, le tarif initial avant retrait), marge 1,0.

## L'increment livre, en une phrase

Quatre lignes du programme ferment a `couvert-valide` - addition posee avec retenue CE1,
instruments de musique CP, roles d'arbitre/observateur/marqueur en EPS CM1, usages numeriques
CM1 -, apres un refus pedagogique reel sur la quatrieme, leve dans la meme apres-midi par une
correction de contenu testee et revalidee ; la couverture du programme passe de 104/156 (66,7 %)
a **108/156 (69,2 %)**.

## Les decisions du sprint

**Aucune `DEC` ni aucun `ADR` n'est cree ou amende apres l'ouverture du planning** (verifie par
`git diff --name-status 00c9139b 88cc3dd9 -- squad/decisions` : apres `5ef0ac7f`, dernier commit
avant l'ouverture, seul `squad/decisions/REGISTRE-identifiants.md` bouge, par allocation
d'identifiants, pas par decision). Deux decisions sont creees **avant** l'ouverture du planning,
le meme jour, comme prealables necessaires a son ouverture :

| Decision | Numero | Statut | Objet |
|---|---|---|---|
| `DEC-0101` | Produit | Acceptee, amendee le 2026-10-01 | Augmente le perimetre de s45 a deux instances paralleles de `nadia-prof` ; amendee le meme jour pour retirer `LVE-CM1-06` (motif `R6`) |
| `ADR-0103` | Architecture | Accepte | Restreint le perimetre du garde-fou `ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest` aux paquets qui declarent `MAT-CE1-04` plutot que `MAT-CE1-03`, condition de faisabilite de `S45-MAT-ADDITION` ; amende `ADR-0096` |

Aucune autre `DEC` ni `ADR` n'est touche par s45. Si le sprint n'avait produit aucune decision,
cette section le dirait explicitement ; ce n'est pas le cas ici, mais les deux decisions
precedent l'horodatage de debut du planning (`review.md`, section "Decisions prises").

Une seule decision m'appartient en propre, et elle est de capacite, pas de produit ni
d'architecture : **regrouper les deux lots de basse complexite mecanique (`S45-MUS-INSTRUMENTS`,
`S45-SCI-NUMERIQUE`) sur moi-meme, casquette dev, un par instance**, et affecter
`S45-MAT-ADDITION` a `marc-dev`, `S45-EPS-ROLES` a `lea-dev` (`planning.md`, section 6).

| Decision | Nature | Statut |
|---|---|---|
| Regroupement des deux lots de basse complexite mecanique sur `sofia-sm`, un par instance ; affectation nominative des deux autres lots | Capacite (`sofia-sm`) | Appliquee, tenue jusqu'a la cloture |

## Etat des deux Done

### Technique - verifie par `sofia-sm` sur l'arbre final, quatre jobs nommes un par un

| Job | Resultat |
|---|---|
| `build` (`bash outils/mvn-jdk21.sh -B clean verify`) | **BUILD SUCCESS**, 6 modules sur 6 |
| `chemins` | **vert** - aucun chemin de poste de travail ni nom de compte |
| `prefixes` | **vert** - tous les commits du sprint (`00c9139b..88cc3dd9`) portent un prefixe d'agent connu |
| `outils` | **vert** - 9 juge(s) pur(s) executes, 0 echec |

**1850 tests (1844 unitaires + 6 integration), 0 echec, 0 erreur, 0 ignore**, mesures par moi sur
`round-two` a `88cc3dd9` par sommation independante des rapports XML (246 fichiers), identique au
chiffre de `theo-archi` et de `yanis-qa`. **ArchUnit** : `static final ArchRule` 36, `@ArchTest`
35, recompte independamment par moi avec la methode d'exclusion de `contreexemple` - l'ecart 36/38
signale par `yanis-qa` est une methode de comptage qui ne filtre pas une fixture de test, pas une
erreur du chiffre de s44 (tranche par `theo-archi`, `confirmation-archunit.md`, section 2).
**Zero ligne de Java de production sur les quatre lots** : contenu YAML et tests de paquet
seulement. Detail complet, y compris les quatre fusions conflictuelles verifiees ligne par ligne
par `yanis-qa` et `ADR-0103` mis a l'epreuve dans les deux sens sur le paquet servi : `review.md`,
sections 2 a 4.

### Pedagogique - deux instances, un refus leve en cours de sprint, quatre VALIDE/VALIDE SOUS
RESERVE a la cloture

| Ligne | Instance | Verdict final | Reserves | Ce qui ferme |
|---|---|---|---|---|
| Addition posee avec retenue (`MAT-CE1-04`) | A | **VALIDE**, aucune reserve | aucune | `couvert-valide` |
| Instruments de musique (`MUS-CP-05`) | A | **VALIDE SOUS RESERVE** | `MUS-CP-R2`, `MUS-CP-R3` (defaut technique, deja ferme - voir ci-dessous) | `couvert-valide`, mention de couverture partielle |
| Roles d'EPS (`EPS-CM1-09`) | A | **VALIDE SOUS RESERVE** | `EPS-CM1-R5`, `EPS-CM1-R6` | `couvert-valide` |
| Usages numeriques (`SCI-CM1-14`) | B | **REFUSE** au premier passage, **VALIDE SOUS RESERVE** au second | `USN-CM1-R1` a `USN-CM1-R9` | `couvert-valide`, au second passage |

**Un refus pedagogique reel sur `SCI-CM1-14`, le premier depuis plusieurs sprints** : deux erreurs
factuelles (age d'accord parental a l'envers, mot de passe sous-dimensionne contre la
recommandation CNIL de 2022), hors de portee des criteres de story deja corriges avant l'ecriture.
Corrige par moi (casquette dev, commit `1c8d98d7`) et revalide par `nadia-prof` le meme
apres-midi. Detail complet, chronologie des trois commits : `review.md`, section 6 ; `retro.md`,
section 2.

**Un defaut technique trouve pendant l'audit, sans consequence sur le contenu livre** :
`MUS-CP-R3`, deux garde-fous de test d'education musicale CP (un de s44, un de s45) qui ne
repliaient pas les accents et laissaient passer une forme d'enonce deja refusee. Corrige et
prouve par mutation par `theo-archi` (commit `a11d59ee`) - le contenu reellement servi etait sain,
les exemples qui avaient alerte `nadia-prof` venaient de ses propres mutations de test. Detail :
`review.md`, section 7.

**Six stories de correction differee**, ouvertes par `camille-po` dans `docs/backlog/backlog.md`
au commit de cloture de la matrice (`32447c99`), pour que les quinze reserves du sprint ne se
perdent pas :

- `USN-CM1-R9` seule, **priorite la plus haute** : une date de source fausse dans le marqueur
  `HIS-3` de l'item 2, introduite par `nadia-prof` elle-meme en corrigeant son propre constat du
  premier passage.
- `USN-CM1-R1` a `USN-CM1-R8` groupees en une story du meme paquet.
- `MUS-CP-R2` seule.
- `EPS-CM1-R5` seule, **priorite haute** : deux regles sportives inexactes.
- `EPS-CM1-R6` seule.

Aucune de ces cinq stories n'est engagee dans un planning a ce jour. `MUS-CP-R3`, signalee dans le
meme commit comme signalement technique et non comme story de contenu, est deja fermee par
`theo-archi` avant la cloture de la matrice (section precedente).

## Couverture du programme

**108/156 (69,2 %)**, en hausse de 2,5 points sur s44 (104/156, 66,7 %). Quatre fermetures,
aucune ouverture partielle, aucun refus restant a lever - verifie par moi sur
`squad/programme/MATRICE-couverture.md`, commit `32447c99`.

## Obstacles resolus et encore ouverts

**Resolus, tous avant l'horodatage de debut du planning (2026-10-01 10:58), le meme jour** : les
huit questions de l'avis de faisabilite de `theo-archi` (Q1 a Q8 - Q6, relative a `S45-LVE-
ECRITURE`, devenue sans objet par le retrait du lot ; Q8 leve son volet `ADR`, sa dette de
registre reste ouverte mais sans objet pour ce sprint) et le retrait de `S45-LVE-ECRITURE`
lui-meme (motif `R6`). **Resolus pendant le sprint** : le refus pedagogique de `SCI-CM1-14` (deux
erreurs factuelles, corrigees et revalidees le meme apres-midi) et le defaut technique
`MUS-CP-R3` (deux garde-fous de test, corriges et prouves par mutation). Tableau complet, avec
commits et horodatages : `review.md`, section 7.

**Encore ouverts, aucun bloquant** : les cinq stories de correction differee des reserves, non
engagees dans un planning (section ci-dessus) ; la decouverte retroactive `S46-VERIF-SAISIE-MOT-
DICTE-CP` (priorite haute, non traitee dans ce sprint - le motif `R6` n'a jamais ete instruit sur
`saisie-mot-dicte-cp`, `couvert-valide` depuis s26) ; la dette de registre `ANG-S1`/`ANG-S2`
(section Q8 ci-dessus), sans objet tant que l'anglais CM1 n'est pas repris.

## Prochain perimetre

**Non propose par cette cloture, et aucune proposition n'existe a ce jour dans le depot** :
`git log` ne porte aucune `DEC` de proposition de perimetre pour s46 au moment de cette cloture -
a la difference de s44, ou `DEC-0100` existait deja. Proposer ou arbitrer un perimetre n'est pas
le role de cette cloture : il appartient a `camille-po`, et reste entierement a ecrire.

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s45`, fenetre 2026-10-01 08:58 -> 2026-10-01 12:59
UTC (horodatage de debut du planning jusqu'au dernier commit du sprint). Detail complet dans
`RAPPORT-tokens.md` (ce dossier) et cumule dans `squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 93 | 186 | 51 477 | 442 817 | 8 046 889 |
| `sofia-sm` | claude-sonnet-5 | 566 | 1 132 | 154 246 | 1 574 676 | 88 037 309 |
| `theo-archi` | claude-opus-5-5 | 117 | 234 | 35 515 | 341 933 | 6 286 463 |
| `marc-dev` | claude-sonnet-5 | 251 | 502 | 119 981 | 2 156 613 | 57 130 565 |
| `lea-dev` | claude-sonnet-5 | 435 | 870 | 95 975 | 642 343 | 77 735 596 |
| `yanis-qa` | claude-sonnet-5 | 181 | 362 | 41 407 | 372 743 | 18 730 301 |
| `relecteur-pedagogique` | claude-sonnet-5 | 71 | 142 | 20 260 | 532 557 | 8 037 322 |
| `nadia-prof` | claude-opus-5-5 | 286 | 572 | 127 012 | 854 815 | 28 384 474 |
| orchestrateur (fil principal) | claude-sonnet-5 | 50 | 100 | 110 267 | 90 848 | 15 145 515 |
| **Total** | | **2050** | **4 100** | **756 140** | **7 009 345** | **307 534 434** |

Aucune invocation mesuree sur cette fenetre pour `ines-design` : aucun travail de design n'etait
du sur ce sprint. Les quatre compteurs sont volontairement separes (voir `RAPPORT-tokens.md`) :
les additionner donnerait un classement faux, le cache lu depassant couramment l'output de deux
ordres de grandeur.
