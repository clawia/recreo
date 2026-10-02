# Rapport de sprint s46 - soustraction posee CE1, emotion et institutions d'EMC CM1 (instance A) et
mouvement/phenomenes de sciences CM1 (instance B), dispositif a deux instances paralleles de
`nadia-prof`

- **Duree** : 2026-10-01 16:30 -> 2026-10-02 10:31:42 (UTC+02:00), 18 h 01 min 42 s.
- **Objectif gele** (`DEC-0102`, Acceptee, engagee le 2026-10-01) : fermer cinq lignes avec deux
  instances paralleles de `nadia-prof` - `MAT-CE1-05`, `EMC-CM1-01`, `EMC-CM1-09`, `SCI-CM1-03`,
  `SCI-CM1-16`. Budget de sprint opposable 8,0, total engage 7,0/8,0, marge 1,0 non allouee.

## L'increment livre, en une phrase

Cinq lignes du programme ferment a `couvert-valide` - soustraction posee CE1, identifier une
emotion et distinguer ressentir/agir en EMC CM1, role de quelques institutions proches en EMC CM1,
trajectoire et vitesse en sciences CM1, phenomenes geologiques et meteorologiques en sciences CM1 -
apres trois refus pedagogiques reels sur trois lots distincts, tous leves dans la meme matinee par
des corrections de contenu testees et revalidees ; la couverture du programme passe de 108/156
(69,2 %) a **113/156 (72,4 %)**.

## Les decisions du sprint

**Aucune `DEC` ni aucun `ADR` n'est cree ou amende apres l'ouverture du planning** (`git diff
--name-status 8b0fe8b4 8f446902 -- squad/decisions/produit squad/decisions/adr` ne porte, en
dehors des deux decisions prealables ci-dessous, aucune creation ni aucun amendement ; seul
`squad/decisions/REGISTRE-identifiants.md` bouge, par allocation d'identifiants, pas par
decision). Deux decisions sont creees **avant** l'ouverture du planning, le meme jour, comme
prealables necessaires a son ouverture :

| Decision | Numero | Statut | Objet |
|---|---|---|---|
| `DEC-0102` | Produit | Acceptee, engagee le 2026-10-01 | Strategie de s46 : deux instances paralleles de `nadia-prof`, une ligne de mathematiques CE1 et deux lignes chacune d'EMC CM1 et de sciences et technologie CM1 |
| `DEC-0103` | Produit | Acceptee | Ouvre le perimetre CE1 : francais, deuxieme matiere du niveau, referentiel route a `nadia-prof` - hors engagement de s46 (non chiffree, aucune ligne de matrice creee) |

Aucune autre `DEC` ni `ADR` n'est touche par s46. Si le sprint n'avait produit aucune decision,
cette section le dirait explicitement ; ce n'est pas le cas ici.

Une seule decision m'appartient en propre, et elle est de capacite, pas de produit ni
d'architecture : **affecter `marc-dev` seul sur le lot de complexite moderee (`MAT-CE1-05`,
quatre formes de distracteur et regle de rang etendue au paquet entier), et repartir les quatre
autres lots de basse complexite entre `lea-dev` et `sofia-sm` (casquette dev), un par instance
chacun** (`planning.md`, section 8).

## Etat des deux Done

### Technique - verifie par `sofia-sm` sur l'arbre final, quatre jobs nommes un par un

| Job | Resultat |
|---|---|
| `chemins` | **vert** - aucun chemin de poste de travail ni nom de compte |
| `prefixes` | **vert** - tous les commits du sprint (`8b0fe8b4..8f446902`) portent un prefixe d'agent connu |
| `outils` | **vert** - 9 juge(s) pur(s) executes, 0 echec |
| `build` (`bash outils/mvn-jdk21.sh -B clean verify`) | **BUILD SUCCESS**, 6 modules sur 6, JDK constate **21.0.10** |

**1898 tests (1892 unitaires + 6 integration), 0 echec, 0 erreur, 0 ignore**, mesures par moi sur
`round-two` a `8f446902` par sommation independante des rapports XML (251 fichiers), identique au
chiffre de `theo-archi` et de `yanis-qa` (mesure a `346274c8`, aucun test n'a bouge depuis).
**ArchUnit** : `static final ArchRule` 36, `@ArchTest` 35 (`contreexemple` exclu), inchange depuis
s45 - coherent avec l'absence de toute regle neuve due sur ces cinq lots de contenu statique.
**Zero ligne de Java de production sur les cinq lots** : contenu YAML et tests de paquet
seulement. Detail complet, y compris les deux garde-fous mis a l'epreuve par mutation reelle sur
le materiau servi et le trou de mesure reactorielle trouve et corrige avant la cloture :
`review.md`, sections 2 a 4 ; `retro.md`, section 1.

### Pedagogique - deux instances, trois refus leves en cours de sprint, cinq Done a la cloture

| Ligne | Instance | Verdict final | Reserves | Ce qui ferme |
|---|---|---|---|---|
| Soustraction posee (`MAT-CE1-05`) | A | **VALIDE SOUS RESERVE**, au 2e passage (1 refus) | `SOP-CE1-R1` (arbitrage adresse a `nadia-prof` elle-meme) | `couvert-valide`, mention de couverture partielle |
| Identifier une emotion, ressentir et agir (`EMC-CM1-01`) | A | **VALIDE SOUS RESERVE**, des le 1er passage | `EMC-CM1-R5`, `EMC-CM1-R6` | `couvert-valide`, mention de couverture |
| Role de quelques institutions proches (`EMC-CM1-09`) | A | **VALIDE SOUS RESERVE**, au 3e passage (2 refus) | `EMC-CM1-R7` | `couvert-valide`, mention de couverture |
| Trajectoire et vitesse (`SCI-CM1-03`) | B | **VALIDE SOUS RESERVE**, des le 1er passage | `MVT-CM1-R1` a `MVT-CM1-R4` | `couvert-valide` |
| Phenomenes geologiques et meteorologiques (`SCI-CM1-16`) | B | **VALIDE, sans reserve**, au 3e passage (2 refus) | aucune (quatre reserves ouvertes, toutes levees) | `couvert-valide` |

**Trois refus pedagogiques reels sur trois lots distincts, tous leves dans la meme matinee** :
`MAT-CE1-05` (`SOP-CE1-4` non tenue sur deux items sans emprunt, corrige par `marc-dev`) ;
`EMC-CM1-09` (item 4 faux puis items 10-11 reecrits en formulation exclusive fausse, corriges par
`sofia-sm` en deux temps) ; `SCI-CM1-16` (item 2, le Vesuve confondu avec un lieu distinct du
volcan, puis item 11, une consigne de securite contredisant l'item 7 du meme paquet, corriges par
`sofia-sm` en deux temps). Chronologie complete, commits et horodatages : `review.md`, sections 6
et 7.

**Un obstacle leve avant l'engagement du sprint, qui conditionnait la faisabilite meme de
`MAT-CE1-05`** : `theo-archi` avait rendu ce lot infaisable en l'etat (les trois formes d'origine
du distracteur de `SOP-CE1-5` rendaient la bonne reponse toujours predictible) ; `nadia-prof` a
leve ce blocage par un amendement ecrit de `SOP-CE1-5` avant le planning (quatrieme forme,
"emprunt reporte deux fois"). Le seul refus que ce lot a subi ensuite (`SOP-CE1-4`) est sans
rapport avec cet amendement, qui a tenu du premier coup.

**Six stories de correction differee**, deja ecrites par `camille-po` dans
`docs/backlog/backlog.md` au moment de la cloture de la matrice (commit `8495e916`) :

- `SOP-CE1-R1` seule, **story d'arbitrage adressee a `nadia-prof` elle-meme** : sur les deux items
  sans emprunt de `MAT-CE1-05`, sa propre condition n'admet que la somme comme distracteur, qui
  depasse toujours le premier terme et se repond sans poser l'operation. Le paquet n'est pas repris
  avant que cet arbitrage soit rendu.
- `EMC-CM1-R5`, `EMC-CM1-R6` groupees (meme paquet, "Ressentir et agir").
- `EMC-CM1-R7` seule ("Les institutions de ma commune").
- `MVT-CM1-R1` a `MVT-CM1-R4` groupees (meme paquet, "Trajectoire et vitesse").

Aucune de ces stories n'est engagee dans un planning a ce jour. Les quatre reserves de
"Les risques naturels" (`PHN-CM1-R1` a `PHN-CM1-R4`) sont deja levees au troisieme passage de
`nadia-prof` : aucune story de correction n'est due sur ce paquet.

## Couverture du programme

**113/156 (72,4 %)**, en hausse de 3,2 points sur s45 (108/156, 69,2 %). Cinq fermetures, aucune
ouverture partielle, aucun refus restant a lever - verifie par moi sur
`squad/programme/MATRICE-couverture.md`, note de sprint en tete et les cinq lignes elles-memes.

## Obstacles resolus et encore ouverts

**Resolus avant l'ouverture du planning, le meme jour** : le blocage de `SOP-CE1-5` sur
`MAT-CE1-05` (amendement de `nadia-prof`) et le refus de la derivation d'une borne a trois
instances (arbitrage prealable de `nadia-prof`, non bloquant pour s46). **Resolus pendant le
sprint** : le trou de mesure reactorielle sur `MAT-CE1-05` (corrige par `sofia-sm` avant la mesure
de cloture) et les trois refus pedagogiques reels, chacun leve par une correction de contenu testee
et revalidee dans la meme matinee. Tableau complet, avec commits et horodatages : `review.md`,
section 7.

**Encore ouverts, aucun bloquant** : `SOP-CE1-R1`, arbitrage adresse a `nadia-prof` elle-meme, non
engage dans un planning ; les deux stories de correction differee groupees (`EMC-CM1-R5`/`R6`,
`MVT-CM1-R1` a `R4`) et la story seule `EMC-CM1-R7`, non engagees non plus ; la question d'une
troisieme instance de `nadia-prof`, refusee par elle, dont les trois conditions cumulatives de
reouverture ne sont pas reunies a ce jour.

## Prochain perimetre

**Non propose par cette cloture, et aucune proposition n'existe a ce jour dans le depot** :
proposer ou arbitrer un perimetre n'est pas le role de cette cloture, il appartient a `camille-po`.
Une candidate signalee mais non engagee reste ouverte depuis `DEC-0103` : l'integration du
referentiel francais CE1 (26 lignes, `squad/programme/francais/competences-ce1.md`, ecrit le
2026-10-01) a `squad/programme/MATRICE-couverture.md`, prealable a tout chiffrage d'un premier lot
de francais CE1.

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s46`, fenetre 2026-10-01 14:30 -> 2026-10-02 08:31
UTC (horodatage de debut du planning jusqu'au dernier commit du sprint). Detail complet dans
`RAPPORT-tokens.md` (ce dossier) et cumule dans `squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 254 | 508 | 149 134 | 1 737 336 | 36 843 268 |
| `sofia-sm` | claude-sonnet-5 | 432 | 864 | 199 418 | 2 095 092 | 65 440 570 |
| `theo-archi` | claude-opus-5-5 | 148 | 296 | 62 120 | 428 890 | 11 129 954 |
| `marc-dev` | claude-sonnet-5 | 179 | 358 | 106 576 | 640 991 | 25 119 182 |
| `lea-dev` | claude-sonnet-5 | 463 | 926 | 122 373 | 1 918 863 | 120 751 110 |
| `yanis-qa` | claude-sonnet-5 | 177 | 354 | 48 172 | 519 392 | 25 798 800 |
| `relecteur-pedagogique` | claude-sonnet-5 | 102 | 204 | 18 136 | 445 142 | 13 723 100 |
| `nadia-prof` | claude-opus-5-5 | 607 | 1 214 | 292 549 | 2 345 138 | 57 210 029 |
| orchestrateur (fil principal) | claude-sonnet-5 | 397 | 794 | 359 171 | 1 808 055 | 106 403 514 |
| **Total** | | **2759** | **5 518** | **1 357 649** | **11 938 899** | **462 419 527** |

Aucune invocation mesuree sur cette fenetre pour `ines-design` : aucun travail de design n'etait
du sur ce sprint. Les quatre compteurs sont volontairement separes (voir `RAPPORT-tokens.md`) :
les additionner donnerait un classement faux, le cache lu depassant couramment l'output de deux
ordres de grandeur.
