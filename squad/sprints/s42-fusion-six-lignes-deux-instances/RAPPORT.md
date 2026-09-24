# Rapport de sprint s42 - fusion de deux sprints projetes (histoire-geographie CM1, questionner le
monde CP, arts plastiques CP, EMC CM1, mathematiques CP), deux instances de `nadia-prof`

- **Duree** : 2026-09-24 17:26:51 -> 2026-09-24 20:37:19 (UTC+02:00), 3 h 10 min 28 s.
- **Objectif gele** (`DEC-0098`, amendee le 2026-09-24) : fermer, autant que possible, les cinq lots
  restants du perimetre fusionne de s42/s43 (`DEC-0097`) sur deux terrains non chevauchants, avec
  deux instances paralleles de `nadia-prof`, borne d'audit verifiee a 8,0, consommee a 7,0/8,0 apres
  le retrait de la sixieme ligne sur verdict negatif d'une mesure de gabarit.

## L'increment livre, en une phrase

Quatre lignes du programme ferment a `couvert-valide` : les rois de la construction du royaume CM1,
la matiere des objets CP, les couleurs CP, les symboles de la Republique CM1 ; une cinquieme
(deplacements sur un quadrillage CP) ouvre en `en cours` sur sa seule moitie textuelle - la
couverture du programme passe de 94/156 (60,3 %) a **98/156 (62,8 %)**.

## Les decisions du sprint

| Decision | Titre | Statut |
|---|---|---|
| `DEC-0097` | Proposition de perimetre chiffre pour s42 et s43, six lignes, tarifs estimes - reprise et amendee par `DEC-0098`, jamais engagee telle quelle | **Proposee**, non engagee |
| `DEC-0098` | Fusion de s42 et s43 en un seul sprint, deux instances paralleles de `nadia-prof`, sur le modele de `DEC-0095` (s41) ; amendee le meme jour pour retirer `S42-FR-LECTURE-SILENCIEUSE` apres le verdict negatif de la mesure de pli d'`ines-design` (357 px de debordement), perimetre ramene a cinq lots, borne consommee a 7,0/8,0 | **Acceptee, amendee le 2026-09-24** |

Les deux entrees sont verifiees presentes sur le disque, avec leur statut a jour, dans
`squad/decisions/produit/DEC-index.md`. **Aucun `ADR` n'est cree ni amende ce sprint** - verifie par
diff independant (`git diff --stat` sur `squad/decisions/adr/` entre l'avis de faisabilite et l'arbre
final : vide). Aucune ceremonie de ce sprint (planning, review, retro) n'a elle-meme cree de `DEC` ni
d'`ADR` : `DEC-0098` a ete redigee par `camille-po` avant l'ouverture du planning et amendee le jour
meme, sur un obstacle de design recu independamment, et est enregistree ici pour memoire.

## Etat des deux Done

### Technique - verifie par `sofia-sm` sur l'arbre final, quatre jobs nommes un par un

| Job | Resultat |
|---|---|
| `build` (`bash outils/mvn-jdk21.sh -B clean verify`) | **BUILD SUCCESS**, 6 modules sur 6 |
| `chemins` | **vert** - aucun chemin de poste de travail ni nom de compte |
| `outils` | **vert** - 9 juge(s) pur(s) execute(s), 0 echec |
| `prefixes` | **vert** - tous les commits portent un prefixe d'agent connu |

**1729 tests (1723 unitaires + 6 integration), 0 echec, 0 erreur, 0 ignore**, mesures par moi sur le
journal complet du lancement de `build` sur `round-two` a `7389e9e1`, deux methodes independantes
convergentes (blocs `[INFO] Results:` par module, et somme des blocs par classe du journal). **Ce
chiffre differe de 2 de celui de `yanis-qa` et de `theo-archi` (1727), et l'ecart est retrace, pas
suppose** : une correction de `lea-dev` posterieure a leurs deux mesures (`773de974`, apres le refus
de `nadia-prof` sur `MAT-CP-10`) ajoute deux methodes de test a
`PaquetCpDeplacementsQuadrillageTest`. **ArchUnit** : `static final ArchRule` 36, `@ArchTest` 35,
inchange depuis s41, recompte independamment par moi - **zero ligne de Java de production sur les
cinq lots**, uniquement du contenu YAML et des tests de paquet.

**Porte technique verte dans son ensemble : les quatre jobs de `.github/workflows/ci.yml` sont
verifies, aucun n'est deduit.**

### Pedagogique - mixte, prononce par deux instances paralleles de `nadia-prof`

| Ligne | Verdict | Ce qui ferme |
|---|---|---|
| Les rois de la construction du royaume (`HG-CM1-05`) | **VALIDE SOUS RESERVE** (trois reserves non bloquantes) | `couvert-valide` |
| La matiere des objets (`QLM-CP-06`) | **VALIDE SOUS RESERVE** (quatre reserves non bloquantes) | `couvert-valide` |
| Les couleurs (`ART-CP-05`) | **VALIDE**, sans reserve | `couvert-valide` |
| Les symboles de la Republique CM1 (`EMC-CM1-05`) | **VALIDE SOUS RESERVE** (la difference promise avec le CP ne tient reellement que sur 5 items sur 13) | `couvert-valide` |
| Deplacements sur un quadrillage, moitie textuelle (`MAT-CP-10`) | **REFUSE** (deux motifs suffisants) puis **VALIDE SOUS RESERVE** au second passage | `en cours`, couverture partielle |

**Un refus sur cinq lots, corrige et revalide dans le meme sprint.** Aucun des deux motifs de refus
n'a ete vu par la CI : ce sont des jugements sur la nature reelle de la tache proposee a l'enfant et
sur la deductibilite d'une reponse, qu'aucun garde-fou de ce depot ne rend. Le Done pedagogique est
prononce dans cinq fiches de `squad/conformite/`, citees en detail dans `review.md`, section 7.

## Un increment retire, pas refuse

`S42-FR-LECTURE-SILENCIEUSE` (francais CM1, lire silencieusement) faisait partie du perimetre chiffre
de `DEC-0097`. Elle n'a pas ete refusee par `nadia-prof` : `ines-design`, avant tout premier commit
de contenu, a mesure le gabarit au plafond haut du critere d'acceptation et trouve que le bloc des
trois propositions de reponse deborde de 357 px sous le pli de reference (744 x 551 px), deux
propositions sur trois entierement hors champ sans defiler. `camille-po` a retire la ligne sans
remplacement avant l'ouverture du planning (`DEC-0098`, amendee) : aucune ligne de tarif equivalent
n'etait disponible dans le vivier sans arbitrage neuf. La ligne reste `non couvert`. Detail complet :
`review.md`, section 2.

## Couverture du programme

**98/156 (62,8 %)**, en hausse de 2,5 points sur s41 (94/156, 60,3 %). Quatre fermetures, une
ouverture partielle - verifie par moi sur `squad/programme/MATRICE-couverture.md`, commit `7389e9e1`.

## Le registre de dette

**Inchange, 9,25** (`review.md`, section 9). Les cinq lignes de s42 ne sont pas des postes de la
composition du Plan 2 (`DEC-0086`/`DEC-0087`) : verifie par recherche des identifiants de ce sprint
dans les deux textes, aucune occurrence.

## Obstacles resolus et encore ouverts

**Resolus** : le perimetre du sprint, fusionne et amende en `DEC-0098` avant son ouverture ; le
retrait de `S42-FR-LECTURE-SILENCIEUSE` sur verdict negatif de mesure de gabarit ; le refus sur
`MAT-CP-10`, corrige par `lea-dev` et revalide dans le meme sprint ; une consigne de catalogue laissee
fausse apres une premiere correction, trouvee et corrigee par `lea-dev` elle-meme ; le rouge de
registre d'identifiants cause par `nadia-prof` sur ses sept identifiants de l'instance A, repare par
elle-meme ; le gap `MAT-CE1-18`, corrige hors sprint avant l'ouverture de s42, apres cinq sprints
consecutifs de signalement.

**Encore ouverts** : la tenue du test qui pretend garder `BIA-5` sur `HG-CM1-05` sans verifier
reellement l'absence de jugement de valeur qu'il annonce (route a `sofia-sm` pour le choix de
correction, le fond restant la garde de `nadia-prof`) ; sept identifiants de reserve de l'instance B,
ecrits sous une forme que le garde-fou `PAR-5`/`ADR-0085` ne sait pas lire, trouve par moi a la
cloture, hors de la garantie d'unicite sans qu'aucun rouge ne le signale ; l'absence de marge de
rejeu sur le terrain EMC de s41, toujours non reconciliee par aucune `DEC`.

**Un point de process, note en retro plutot qu'amende dans `squad/README.md`** : c'est la deuxieme
fois en deux sprints consecutifs (s40, s42) que `nadia-prof` ecrit des identifiants de condition
sans les allouer dans le meme commit, causant un rouge trouve par un developpeur plutot que par
elle-meme. Detail et action proposee : `retro.md`, sections 2 et 3.

Tableau complet, avec la categorie et la route de chaque obstacle : `review.md`, section 8.

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s42`, fenetre 2026-09-24 15:26 -> 2026-09-24 18:37
UTC (horodatage de debut du planning, precise par amendement pour que le script puisse ouvrir la
fenetre, jusqu'au dernier commit du sprint). Detail complet dans `RAPPORT-tokens.md` (ce dossier)
et cumule dans `squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 105 | 210 | 37 755 | 610 756 | 8 936 523 |
| `sofia-sm` | claude-sonnet-5 | 199 | 398 | 125 004 | 921 186 | 47 577 054 |
| `theo-archi` | claude-opus-5 | 152 | 304 | 64 898 | 468 506 | 18 262 674 |
| `marc-dev` | claude-sonnet-5 | 345 | 690 | 123 338 | 763 457 | 92 546 499 |
| `lea-dev` | claude-sonnet-5 | 303 | 606 | 131 077 | 993 299 | 47 043 216 |
| `yanis-qa` | claude-sonnet-5 | 213 | 426 | 54 149 | 392 713 | 28 717 104 |
| `nadia-prof` | claude-opus-5 | 292 | 584 | 137 897 | 1 298 002 | 29 797 341 |
| `ines-design` | claude-sonnet-5 | 1 | 2 | 70 | 185 733 | 7 205 |
| orchestrateur (fil principal) | claude-sonnet-5 | 53 | 106 | 62 949 | 103 948 | 15 335 790 |
| **Total** | | **1663** | **3 326** | **737 137** | **5 737 600** | **288 223 406** |

Aucune invocation mesuree sur cette fenetre pour `relecteur-pedagogique` : aucun travail de
relecture n'etait du sur ce sprint (section 6 de `review.md`). Les quatre compteurs sont
volontairement separes (voir `RAPPORT-tokens.md`) : les additionner donnerait un classement faux,
le cache lu depassant couramment l'output de deux ordres de grandeur.
