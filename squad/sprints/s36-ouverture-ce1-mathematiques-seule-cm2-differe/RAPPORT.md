# Rapport de sprint s36 - ouverture du perimetre CE1, mathematiques seule (CM2 differe)

- **Duree** : 2026-09-21 16:40 -> 2026-09-21 20:36 (UTC+02:00), 3 h 56 min.
- **Objectif gele** (`planning.md`) : ouvrir le perimetre CE1 (mathematiques seule, CM2 differe,
  `DEC-0089`) sans engager de story de developpement de contenu CE1, et reprendre
  `S36-QLM11-PLANCLASSE` sur la seule geometrie des six cadres restant a tenir.

## L'increment livre, en une phrase

Le perimetre du produit s'ouvre a un troisieme niveau, CE1 (referentiel de 17+1 competences
mathematiques, aucun contenu CE1 chiffre livre), et `QLM-CP-11` (le plan de la classe) est
**valide sous reserve** apres quatre refus consecutifs ; la couverture passe de 76/138 (55,1 %) a
77/155 (49,7 %), un recul de taux qui tient entierement a l'agrandissement du denominateur.

## Les decisions du sprint

| Decision | Titre | Statut |
|---|---|---|
| `DEC-0089` | Ouverture du perimetre CE1, troisieme niveau du produit, CM2 explicitement differe, perimetre de s36 borne a mathematiques seule | **Acceptee**, amendee le meme jour (referentiel rendu, note sur `MAT-CE1-18`) |
| `DEC-0090` | L'ordre des niveaux affiches dans l'en-tete devient une propriete garantie (CP, CE1, CM1, CM2), reponse a `F1` | **Acceptee** |
| `ADR-0093` | Le nom accessible d'un bouton de zone est resolu sur le document servi et porte le libelle que le paquet declare | `Propose` -> **Accepte** (dans ce meme sprint) |
| `ADR-0094` | Le paquet declare ou se lit le libelle de ses zones, aucun gabarit ne nomme un contenu particulier | `Propose` -> **Accepte** (dans ce meme sprint, avec un amendement de sa propre decision 5 fait par son propre auteur avant l'acceptation) |

Les quatre entrees sont verifiees presentes sur le disque, avec leur statut a jour, dans
`squad/decisions/produit/DEC-index.md` et `squad/decisions/adr/ADR-index.md`. Aucune ceremonie de
ce sprint (planning, review, retro) n'a elle-meme cree de `DEC` ni d'`ADR` : les quatre ci-dessus
ont ete redigees en cours de sprint, sur des obstacles rencontres, puis enregistrees ici.

## Etat des deux Done

### Technique - verifie par `sofia-sm` sur l'arbre fusionne final, quatre jobs nommes un par un

| Job | Resultat |
|---|---|
| `build` (`bash outils/mvn-jdk21.sh -B clean verify`) | **BUILD SUCCESS**, 6 modules sur 6 |
| `chemins` | **vert** - aucun chemin de poste de travail ni nom de compte |
| `outils` | **vert** - 9 juges purs executes, 0 echec |
| `prefixes` | **vert** - tous les commits portent un prefixe d'agent connu |

**1496 tests, 0 echec, 0 erreur, 0 ignore**, mesures par moi sur le journal complet du dernier
lancement de `build` sur l'arbre fusionne (`a7f4c730`). Ce chiffre est la somme exacte des trois
mesures partielles annoncees en cours de sprint (1487 apres les six premiers lots, +1 du correctif
de `lea-dev`, +8 de la regle executable de `theo-archi`) : 1487 + 1 + 8 = 1496, sans aucun ecart a
expliquer. **ArchUnit** : 33 `@ArchTest`, recompte independamment par moi, inchange par s36.

**Porte technique verte dans son ensemble : les quatre jobs de `.github/workflows/ci.yml` sont
verifies, aucun n'est deduit.**

### Pedagogique - mixte, prononce par `nadia-prof`

| Story | Verdict | Ce qui ferme |
|---|---|---|
| `S36-QLM11-PLANCLASSE` | **VALIDE SOUS RESERVE** | `ZON-15` tenue sur les six zones ; `QLM-CP-11` ferme apres quatre refus consecutifs |
| `competences-ce1.md` (referentiel) | **Pas de Done pedagogique** - aucun contenu n'est encore jouable | aucun |

**Une validation sous reserve, un document releve sans Done car rien n'est encore jouable.** Le
Done pedagogique est prononce dans
`squad/sprints/s36-ouverture-ce1-mathematiques-seule-cm2-differe/verdicts-nadia.md` et
`squad/conformite/FICHE-designation-plan-de-la-classe.md`.

## Couverture du programme

**76/138 (55,1 %) a l'ouverture -> 77/155 (49,7 %) a la cloture**, verifie contre
`squad/programme/MATRICE-couverture.md` : le denominateur s'agrandit de 17 (le referentiel CE1
entier entre `non couvert`), un seul mouvement de statut (`QLM-CP-11`, `non couvert` ->
`couvert-valide`). Le recul du taux ne traduit aucune regression du produit.

## Le registre de dette

**Inchange, 9,25** (`review.md`, section 7). Aucune des reserves ouvertes ou levees par s36 ne porte
sur l'un des seize postes de `DEC-0086`/`DEC-0087` : les deux paquets de designation concernes
(`cp-plan-de-la-classe`, `cp-designation-frise-de-la-journee`) sont des paquets du palier 2, suivis
dans la matrice de couverture et non dans le registre numerique.

## Obstacles resolus et encore ouverts

**Resolus** : la faisabilite de `S36-QLM11-PLANCLASSE` (`ADR-0093` accepte) ; la regle executable
manquante et l'ecart de la decision 5 d'`ADR-0094`, tous deux trouves et payes par `theo-archi`
lui-meme le jour meme ; le defaut de tracabilite sur le relais 1 d'`ADR-0094`, trouve par
`yanis-qa` et ferme par `lea-dev` ; l'allocation manquante de quatre identifiants de reserve
(`PCL-R3` a `PCL-R6`) dans le registre d'identifiants, trouvee a l'assemblage et reparee par
`sofia-sm` ; la ligne manquante `MAT-CE1-18`, trouvee par `nadia-prof` a sa propre relecture et
ajoutee le jour meme ; `F1` (ordre des puces de niveau) en principe, par `DEC-0090` ; `F3` (borne
de longueur d'enonce CE1), par `ARBITRAGE-s36-longueur-d-enonce-au-ce1.md`.

**Encore ouverts** : `F2` (repli de la barre a trois puces sous 360 px) - la proposition
d'`ines-design` est infirmee par la mesure de `theo-archi` sur le rendu reellement servi (ecart de
12 px), non corrigee ; la story `S36-ORDRE-NIVEAUX` (mecanisme de `DEC-0090`), non engagee faute de
developpeur libre ; quatre reserves non bloquantes sur `cp-plan-de-la-classe` (`PCL-R1`, `PCL-R2`,
ouvertes depuis s35 ; `PCL-R3`, `PCL-R4`, neuves) et deux defauts transverses aux deux paquets de
designation servis (`PCL-R5`, `PCL-R6`, dates de s34, non une regression de s36), tous a composer en
stories par `camille-po`.

Tableau complet, avec la categorie et la route de chaque obstacle : `review.md`, section 6.

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s36`, fenetre 2026-09-21 14:40 -> 18:36 UTC. Detail
complet dans `RAPPORT-tokens.md` (ce dossier) et cumule dans `squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 39 | 78 | 21 264 | 433 178 | 7 360 885 |
| `sofia-sm` | claude-sonnet-5 | 139 | 278 | 29 740 | 672 841 | 15 757 906 |
| `theo-archi` | claude-opus-5 | 220 | 440 | 118 709 | 551 496 | 28 110 255 |
| `lea-dev` | claude-sonnet-5 | 67 | 134 | 12 763 | 163 889 | 3 361 220 |
| `yanis-qa` | claude-sonnet-5 | 71 | 142 | 24 520 | 117 337 | 10 323 485 |
| `nadia-prof` | claude-opus-5 | 191 | 382 | 79 832 | 742 360 | 21 813 083 |
| orchestrateur (fil principal) | claude-sonnet-5 | 31 | 62 | 34 024 | 471 019 | 6 278 546 |
| **Total** | | **758** | **1 516** | **320 852** | **3 152 120** | **93 005 380** |

Aucune invocation mesuree sur cette fenetre pour : `marc-dev`, `relecteur-pedagogique`,
`ines-design`.

Les quatre compteurs sont volontairement separes (voir `RAPPORT-tokens.md`) : les additionner
donnerait un classement faux, le cache lu depassant couramment l'output de deux ordres de
grandeur.
