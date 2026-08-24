# [Sprint s19] Rapport de cloture - contenu seul et conception de la saisie

## L'increment livre, en une phrase

Quatre paquets de contenu francais CP/CM1 livres, audites et couverts (un refus pedagogique
prononce puis leve dans le meme sprint), et la conception de la mecanique de saisie posee sans
code, portant la couverture de 10/46 (21,7 %) a 13/44 (29,5 %) - le plus fort mouvement de
couverture depuis plusieurs sprints.

## Les decisions du sprint

| Numero | Titre | Statut |
|---|---|---|
| `DEC-0042` | Perimetre du sprint 19 : contenu seul en francais CP/CM1, conception de la saisie sans code, audio hors perimetre | Acceptee, amendee trois fois le meme jour (reponse de `nadia-prof` aux quatre proxys de `DEC-0030`, arbitrage sur la mecanique de saisie, entree de `SAI-12` au perimetre) |
| `DEC-0043` | `SYN-16` : residu de largeur assume avec marge de police exigee ; `S19-6` scindee en `S19-6a`/`S19-6b` et renvoyee a s20 ; `SAI-12` chiffree | Acceptee, amendee trois fois le meme jour (plafond revise a 547 px, reserve d'`ines-design` revisee de 41 a 33 px ; puis `S19-3` reauditee VALIDE, la bande basse touche trois paquets et non un seul) |
| `DEC-0044` | Le score decoratif a deux propositions est accepte sur tout le catalogue ; `NAT-5`/`NAT-9` routees a `ines-design` et `lea-dev` (trou de coordination corrige) ; `AGN-3` notee sans story neuve | Acceptee |
| `ADR-0058` | La saisie normalise dans sa mecanique, declare son mode strict dans l'item, aucun contrat ne porte de comportement | Accepte pour ses clauses de conception ; une seule de ses trois regles ArchUnit est ecrite et verte a ce jour, les deux autres sont dues avec le lot de code qui les declenchera |
| `ADR-0059` | Un plafond maison qui lie dans la bande est le mecanisme du correctif de largeur, sa valeur est un parametre, elle ne descend jamais sous la largeur mesuree necessaire | Accepte, regle ecrite et mise a l'epreuve dans le meme commit |
| `ADR-0060` | Un texte servi porte le caractere decide, jamais sa notation ; l'espace interieur d'un guillemet francais est insecable | Accepte, amende le meme jour (collision arbitree avec le lot de `yanis-qa`, ses quatre epreuves par mutation absorbees) |

Aucune decision de ce sprint n'a ete refusee ni retiree. Le detail de chacune, avec sa preuve de
morsure quand elle en porte une, vit dans `review.md`.

## Etat des deux Done

- **Technique (CI)** : vert. `mvn clean verify` sur l'arbre fusionne final (`d79ce80`) : `BUILD
  SUCCESS`, six modules. **666 tests par la methode de reference**, mesures par moi et confirmes
  independamment par `yanis-qa` et `theo-archi`. **30 regles ArchUnit**, comptees par la methode de
  `yanis-qa` et reproduites par moi a l'identique sur l'arbre final.
- **Pedagogique** : quatre paquets audites. Trois `VALIDE SOUS RESERVE` sans reserve bloquante
  (`cm1-accords-groupe-nominal`, `cm1-antonymes`, `cm1-accord-sujet-verbe` - ce dernier apres un
  `REFUSE` initial sur cinq motifs, un correctif, et une reaudition sur l'arbre fusionne). Un
  quatrieme, `cp-nature-mot`, `VALIDE SOUS RESERVE` avec **une reserve bloquante** (`NAT-5`) qui
  garde sa ligne de matrice `en cours` : aucun refus ne reste ouvert, mais tout n'est pas clos.

## Le mouvement de couverture

**Deux mouvements de nature differente, a ne pas confondre.** Le denominateur a bouge deux fois en
debut de sprint (46 vers 45 puis 44) par reclassement de lignes en `non couvrable en l'etat` - un
geste de matrice, pas une couverture gagnee. Le numerateur, lui, a gagne **trois lignes reellement
couvertes et auditees** ce sprint : "Utiliser des synonymes et antonymes" (synonymes et antonymes
CM1), "Accorder en genre et en nombre dans le groupe nominal", et "Accorder le verbe avec son
sujet". **10/46 (21,7 %) en debut de sprint devient 13/44 (29,5 %) a la cloture.**

D'apres la matrice elle-meme, c'est le mouvement le plus net depuis plusieurs sprints : `DEC-0037`
avait constate une longue periode sans mouvement de couverture, et s18 n'en avait gagne qu'une
seule ligne. Je cite ce cadrage tel que la coordination me l'a donne ; je ne l'ai pas retrace
moi-meme sprint par sprint au-dela de la valeur de depart, verifiee dans la matrice.

**Cinq stories sur six ouvertes ont ete livrees** (`S19-1` a `S19-5`) ; `S19-6` a ete scindee en
`S19-6a`/`S19-6b` et renvoyee a s20 par `DEC-0043`.

**Une reserve ouverte depuis longtemps est tranchee** : `DEC-0044` tranche le statut du score
decoratif a deux propositions sur tout le catalogue, une question posee plusieurs sprints plus tot
et jusque-la reportee.

## Ce que `nadia-prof` releve, et qui merite d'etre porte

Pour la premiere fois, une de ses reserves de redaction de contenu enfant recoit un test : le
garde-fou d'`ADR-0060` (espace insecable des guillemets francais, artefacts de notation) rend
verifiable par la machine une convention qu'elle devait auparavant surveiller seule a chaque audit.
Elle le releve elle-meme comme retirant un point a sa garde exclusive, apres l'avoir demande
plusieurs sprints de suite.

## Obstacles resolus et obstacles encore ouverts

Tableau complet, avec destinataire et etat, dans `review.md`. En resume : sept obstacles sur dix
sont clos (dont la collision d'un meme defaut route en double a deux agents, arbitree sans perte de
l'apport d'aucun des deux) ; trois restent ouverts et sequences a s20 - `NAT-5`/`NAT-9` (reserve
bloquante sur `cp-nature-mot`), `AGN-3` (correction mecanique non bloquante) et la section "Lecture
rapide" d'`ADR-index.md` (documentation, non bloquant).

## Consommation de tokens

Genere par `python outils/rapport-tokens.py --sprint s19` ; le detail complet vit dans
`RAPPORT-tokens.md` et dans `squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 138 | 276 | 55 407 | 1 296 956 | 26 322 438 |
| `sofia-sm` | claude-sonnet-5 | 74 | 148 | 25 297 | 996 372 | 16 478 442 |
| `marc-dev` | claude-sonnet-5 | 83 | 166 | 48 929 | 763 303 | 29 861 766 |
| `relecteur-pedagogique` | claude-sonnet-5 | 214 | 428 | 57 951 | 708 936 | 50 313 982 |
| `nadia-prof` | claude-opus-5 | 78 | 156 | 73 254 | 1 006 392 | 31 463 991 |
| orchestrateur (fil principal) | claude-opus-5 | 25 | 50 | 54 732 | 1 180 821 | 7 048 490 |
| **Total** | | **612** | **1 224** | **315 570** | **5 952 780** | **161 489 109** |

**Aucune invocation mesuree sur cette fenetre pour `theo-archi`, `lea-dev`, `yanis-qa` et
`ines-design`** : je le dis explicitement plutot que de laisser un chiffre absent se lire comme un
zero, alors meme que les trois premiers ont produit un travail substantiel ce sprint (trois `ADR`
pour `theo-archi`, deux paquets livres pour `lea-dev`, le rapport qualite et un garde-fou pour
`yanis-qa`). C'est une limite de mesure de cet outil sur cette fenetre, pas une mesure de leur
contribution.
