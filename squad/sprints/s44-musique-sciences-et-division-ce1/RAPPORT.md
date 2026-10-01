# Rapport de sprint s44 - education musicale CP, sciences et technologie CM1, mathematiques CE1
(division), instance unique de `nadia-prof`

- **Duree** : 2026-09-30 18:48 -> 2026-10-01 07:48:50 (UTC+02:00), 13 h 00 min 50 s - dont un
  ecart de pres de dix heures entre le Done pedagogique (21:47:31) et la cloture de la matrice
  (07:48:24, lendemain), non documente dans le depot (`review.md`, en-tete et section 9).
- **Objectif gele** (`DEC-0099`, Acceptee, section 9.6, engagement section 10, 2026-09-25) : fermer,
  avec une instance unique de `nadia-prof`, les trois lots du perimetre de s44 - `MUS-CP-11`,
  `SCI-CM1-13`, `MAT-CE1-18` -, cible 4,0/5,0, marge 1,0.

## L'increment livre, en une phrase

Trois lignes du programme ferment a `couvert-valide` : vocabulaire musical CP, evolution d'un
objet technique CM1, sens de la division CE1 (partage et groupement) - sans aucun refus
pedagogique - et la couverture du programme passe de 101/156 (64,7 %) a **104/156 (66,7 %)**.

## Les decisions du sprint

**Aucune `DEC` ni aucun `ADR` n'est cree ou amende pendant s44** (verifie par
`git diff --name-status ca277753 12a93893 -- squad/decisions` : seul
`squad/decisions/REGISTRE-identifiants.md` bouge, par allocation d'identifiants, pas par decision).
`DEC-0099` (perimetre et objectif) existe deja avant ce sprint et n'est pas amendee par lui.

Une seule decision m'appartient en propre, et elle est de capacite, pas de produit ni
d'architecture : **l'affectation de `S44-SCI-OBJET-TECHNIQUE` des l'ouverture du planning**
(`planning.md`, section 6), avec une consigne explicite au developpeur affecte - lecture
preparatoire autorisee des le debut, premier commit de code differe jusqu'a la fusion de la
correction attendue de `camille-po` sur la story (obstacle Q2). Ce choix a permis au lot de
demarrer sans delai de relais une fois la correction fusionnee (18:58:48), au prix du conflit de
fusion decrit ci-dessous et en `retro.md`, section 2.

| Decision | Nature | Statut |
|---|---|---|
| Affectation de `S44-SCI-OBJET-TECHNIQUE` des l'ouverture du planning, ecriture de code differee | Capacite (`sofia-sm`) | Appliquee, tenue jusqu'a la fusion de la correction de `camille-po` |

## Etat des deux Done

### Technique - verifie par `sofia-sm` sur l'arbre final, quatre jobs nommes un par un

| Job | Resultat |
|---|---|
| `build` (`bash outils/mvn-jdk21.sh -B clean verify`) | **BUILD SUCCESS**, 6 modules sur 6 |
| `chemins` | **vert** - aucun chemin de poste de travail ni nom de compte |
| `prefixes` | **vert** - tous les commits du sprint (`ca277753..12a93893`) portent un prefixe d'agent connu |
| `outils` | **vert** - 9 juge(s) pur(s) executes, 0 echec |

**1805 tests (1799 unitaires + 6 integration), 0 echec, 0 erreur, 0 ignore**, mesures par moi sur
`round-two` a `12a93893`, deux methodes independantes convergentes - identique au chiffre de
`theo-archi` et de `yanis-qa`. **ArchUnit** : `static final ArchRule` 36, `@ArchTest` 35, inchange
depuis s43, recompte independamment par moi - **zero ligne de Java de production sur les trois
lots**, uniquement du contenu YAML et des tests de paquet. Detail complet, y compris la fusion
conflictuelle verifiee ligne par ligne par `yanis-qa` : `review.md`, sections 2 a 4.

### Pedagogique - instance unique de `nadia-prof`, zero refus, trois VALIDE SOUS RESERVE

| Ligne | Verdict | Reserves | Ce qui ferme |
|---|---|---|---|
| Vocabulaire musical (`MUS-CP-11`) | **VALIDE SOUS RESERVE** | `MUS-CP-R1` | `couvert-valide`, mention de couverture partielle exigee d'avance |
| Evolution d'un objet technique (`SCI-CM1-13`) | **VALIDE SOUS RESERVE** | `EVO-CM1-R1` a `-R4` | `couvert-valide` |
| Sens de la division (`MAT-CE1-18`) | **VALIDE SOUS RESERVE** | `DIV-CE1-R1` | `couvert-valide`, sans mention de couverture partielle |

**Zero refus sur trois lots**, pour le deuxieme sprint consecutif : les deux discriminants
pedagogiques exiges par `MUS-CP-11` et `SCI-CM1-13` (`MUS-3`, `EVO-1`/`EVO-2`) ont ete poses par
`nadia-prof` avant l'ecriture des deux paquets. Detail complet : `review.md`, section 6 ;
`retro.md`, section 1.

**Quatre stories de correction differee**, ouvertes par `camille-po` dans
`docs/backlog/backlog.md` au commit de cloture de la matrice (`6a2a53ae`), pour que les sept
reserves du sprint ne se perdent pas :

- `EVO-CM1-R2` seule, **priorite la plus haute** : l'item du moulin oppose l'eau et l'electricite
  comme deux sources interchangeables, en contradiction factuelle avec `cm1-sciences-energie.yaml`
  deja livre.
- `EVO-CM1-R1` + `EVO-CM1-R3` + `EVO-CM1-R4` groupees en une story du meme paquet.
- `MUS-CP-R1` seule.
- `DIV-CE1-R1` seule.

Aucune de ces quatre stories n'est engagee dans un planning a ce jour.

## Couverture du programme

**104/156 (66,7 %)**, en hausse de 2,0 points sur s43 (101/156, 64,7 %). Trois fermetures, aucune
ouverture partielle, aucun refus a lever - verifie par moi sur
`squad/programme/MATRICE-couverture.md`, commit `6a2a53ae`.

## Obstacles resolus et encore ouverts

**Resolus, tous pendant ce sprint sauf le dernier** : Q1 et Q3 (route de `sofia-sm` a
`nadia-prof`, formes d'enonce pour `pulsation`/`silence` et discriminant d'evolution, posees
avant l'ecriture des deux paquets concernes) ; Q2 (route de `sofia-sm` a `camille-po`, citation
perimee de `SCI-1` et conditions manquantes dans la story de sciences, corrigees avant le premier
commit de code) ; le rattrapage de l'amendement `DIV-CE1-5`/`DIV-CE1-6` dans les criteres de
`S44-MAT-DIVISION`, trouve par `lea-dev` et route a `camille-po` - **resolu hors sprint, le
2026-09-25, avant l'ouverture du planning**. Tableau complet, avec commits et horodatages :
`review.md`, section 7.

**Encore ouverts, aucun bloquant** : les quatre stories de correction differee des reserves,
non engagees dans un planning (section ci-dessus) ; l'ecart de pres de dix heures entre le Done
pedagogique et la cloture de la matrice, non documente ; l'ecart d'un fichier de rapport XML
(242 contre 243 selon la source), non retrace, deja signale a s43 sous une forme voisine.

## Prochain perimetre

**Non propose par cette cloture.** `camille-po` a deja ecrit `DEC-0100`
(`squad/decisions/produit/DEC-0100-proposition-de-perimetre-pour-s45.md`), une proposition pour
s45 - une ligne de mathematiques CE1 (`MAT-CE1-04`), `MUS-CP-05` et `EPS-CM1-09`, cible 4,0/5,0,
marge 1,0 - mais ce document reste **Propose**, non engage : seule une validation explicite de
l'utilisatrice, dans un geste distinct, engagerait ce perimetre. Proposer ou arbitrer un
perimetre n'est pas le role de cette cloture : le prochain perimetre reste a proposer et a
valider avant qu'un planning ne s'ouvre.

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s44`, fenetre 2026-09-30 16:48 -> 2026-10-01 05:48
UTC (horodatage de debut du planning jusqu'au dernier commit du sprint). Detail complet dans
`RAPPORT-tokens.md` (ce dossier) et cumule dans `squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 149 | 298 | 57 574 | 777 844 | 12 384 252 |
| `sofia-sm` | claude-sonnet-5 | 251 | 502 | 88 650 | 646 148 | 46 767 731 |
| `theo-archi` | claude-opus-5-5 | 67 | 134 | 20 056 | 157 911 | 3 872 532 |
| `marc-dev` | claude-sonnet-5 | 259 | 518 | 79 825 | 994 097 | 55 392 057 |
| `lea-dev` | claude-sonnet-5 | 271 | 542 | 85 273 | 736 471 | 62 007 576 |
| `yanis-qa` | claude-sonnet-5 | 113 | 226 | 26 996 | 300 299 | 8 426 107 |
| `relecteur-pedagogique` | claude-sonnet-5 | 64 | 128 | 12 180 | 272 445 | 4 561 516 |
| `nadia-prof` | claude-opus-5-5 | 191 | 382 | 107 472 | 801 339 | 22 460 822 |
| orchestrateur (fil principal) | claude-sonnet-5 | 68 | 136 | 112 487 | 550 099 | 11 285 786 |
| **Total** | | **1433** | **2 866** | **590 513** | **5 236 653** | **227 158 379** |

Aucune invocation mesuree sur cette fenetre pour `ines-design` : aucun travail de design n'etait
du sur ce sprint. Les quatre compteurs sont volontairement separes (voir `RAPPORT-tokens.md`) :
les additionner donnerait un classement faux, le cache lu depassant couramment l'output de deux
ordres de grandeur.
