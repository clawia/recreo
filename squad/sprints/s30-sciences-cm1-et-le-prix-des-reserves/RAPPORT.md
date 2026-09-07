# RAPPORT de cloture - s30, sciences CM1 et le prix des reserves

## L'increment livre

**recreo sert pour la premiere fois des sciences et technologie au CM1** - quatre lignes fermees
(`SCI-CM1-05`, `SCI-CM1-17`, `SCI-CM1-02`, `SCI-CM1-12`), matiere qui n'avait jamais recu un item -
et paie treize des quinze reserves de contenu ouvertes depuis s29, dont deux reaudites et corrigees
apres refus dans le sprint lui-meme. La couverture programme passe de 52/138 (37,7 %) a 56/138
(40,6 %), exactement le plafond arithmetique pose avant que le sprint soit joue. `CONS-1` (la
consigne generique de catalogue) est levee sur neuf entrees de dix. `REV-3` (l'ecran de fin de
partie qui nommait une reponse sans porter sa question) est validee.

## Toutes les decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| `ADR-0084` | La reponse d'une fin de partie voyage avec la question qu'elle commente (`REV-3`) | Architecture | Accepte |
| `DEC-0078` | `CONS-1` - la consigne generique cesse d'etre reprise par une entree neuve de catalogue | Produit | Acceptee, amendee (population corrigee a 43/54, `CONS-3` routee au backlog de s31) |
| `DEC-0079` | Strategie de s30 : sciences et technologie CM1 en bloc, quatre arbitrages de l'utilisatrice, budget opposable par instance (`PAR-9`) | Produit | Acceptee, amendee (`PAR-11` referencee) |
| `DEC-0080` | Cloture de s30 : les deux paquets de sciences refuses et le reliquat de `CONS-1` sont corriges et reaudites dans le sprint, troisieme depassement de borne assume | Produit | Acceptee, executee (amendee : les deux reaudits confirmes au centime pres) |

## Etat des deux Done

**Technique : prononce.** `mvn -B clean verify` vert sur le reacteur complet, `round-two` a
`cff5949e`. **179 rapports XML, 1321 tests, 0 echec, 0 erreur** (methode de l'amendement s11, mesure
par moi sur l'arbre fusionne final, sans `-pl`). Confirme independamment par `yanis-qa` et
`theo-archi` (memes chiffres, sur `dbbfe129`, avant les quatre derniers commits de cloture qui ne
touchent aucun fichier Java de test). Zero `ArchRule` ajoutee ce sprint (33 regles, inchangees) ;
zero fichier Java de production touche par les deux lots de contenu (24 fichiers, tous YAML ou
tests). Le seul Java de production modifie dans tout le sprint est le correctif de `REV-3`
(`ADR-0084`), tranche et livre avant l'ouverture des lots de contenu.

**Pedagogique : prononce.** Deux instances disjointes de `nadia-prof`, chacune reauditant apres son
propre refus, aucune ne relisant l'autre. Quatre lignes ferment : `SCI-CM1-05`, `SCI-CM1-17`,
`SCI-CM1-02`, `SCI-CM1-12` - le plafond arithmetique pose par `DEC-0079` avant le sprint, atteint
exactement. Les deux paquets de sciences refuses au premier passage (`SCI-CM1-17`,
`SCI-CM1-12`) ont ete corriges et valides au reaudit dans le sprint. `REV-3` validee. `CONS-1`
levee sur neuf entrees de dix ; la dixieme (`histoire-geographie-cm1`) part a s31 en rejeu borne,
sa consigne corrigee et servie mais non rejouee dans ce sprint, par arbitrage de l'utilisatrice.
Onze reserves de s29 levees sur les onze du perimetre de l'Instance A, cinq reserves neuves
ouvertes, aucune bloquante.

**Prix reel du sprint : 8,50 sur une borne de 8,0** (Instance A 4,00/4,00, Instance B 4,50/4,00),
recompte independamment par `camille-po`. C'est le troisieme depassement consecutif de la borne
d'un sprint (s28 : +0,25 ; s29 : +1,50 ; s30 : +0,50) - la tendance baisse d'un ordre de grandeur,
mais le fait se repete. Il est, pour la premiere fois, arbitre et chiffre avant d'etre consomme
(`DEC-0080`), plutot que decouvert au comptage de cloture comme a s29 : c'est directement l'effet
de `PAR-9`, ecrite par `nadia-prof` a l'ouverture de ce sprint contre elle-meme.

**Ce que la reprise des deux paquets refuses n'a pas fait, dit sans l'adoucir** : aucun garde-fou
nouveau sur les proprietes que les deux refus ont fait payer (`OBJ-R2`, `OBT-R1`, `OBT-R2`) - elles
restent gardees par `nadia-prof` seule, et elle le signale explicitement pour qu'on ne les croie pas
couvertes par la correction elle-meme.

## Obstacles resolus

- `S30-7` (bloc E, `FOP-R1`) exigeait d'ecrire `catalogue.yaml`, reserve a `lea-dev` seule par la
  consigne d'affectation du planning - reassignee a `lea-dev`, ajoutee en dernier de sa serie sur ce
  fichier.
- Fichier de test ecrase par `Write` sans relecture prealable (`marc-dev`) - six tests d'origine et
  une regression d'encodage - detecte et corrige par lui-meme le jour meme, avant tout commit
  errone.
- Deux paquets de sciences refuses au premier audit de l'Instance B - corriges et valides au
  reaudit dans le sprint.
- `PAR-9` calculait la marge de rejeu par moitie egale du sprint alors que `PAR-2` concentre les
  deux lots de sciences sur une seule instance - corrige par `PAR-11` (marge par instance, sur son
  exposition reelle).
- Population de `DEC-0078` sous-comptee d'une entree (`bandit-manchot-semaine-cp`) - trouvee par
  `nadia-prof` en recomptant pendant le reaudit, `DEC-0078` amendee, population corrigee a 43/54.
- Cinq entrees de `CONS-1` corrigees au reaudit (consignes ne nommant pas la tache reelle du jeu),
  neuf entrees sur dix tiennent desormais.

## Obstacles encore ouverts, a porter a s31

- **Le reliquat de `CONS-1`** sur `bandit-manchot-histoire-geographie-cm1` - consigne corrigee et
  servie une troisieme fois, non rejouee dans ce sprint par arbitrage de l'utilisatrice, rejeu
  borne de 0,5 du.
- **`OBT-R2`** - un defaut fabrique par la correction d'`OBJ-R2`, troisieme cas de cette famille en
  deux jours apres `REV-2` et `REV-3` - reserve versee au backlog, rejeu borne de 0,5 du.
- **Aucun garde-fou n'a ete ajoute** sur les proprietes que les deux refus de sciences ont fait
  payer (`OBJ-R2`, `OBT-R1`, `OBT-R2`) - routees a `theo-archi` et `yanis-qa`.
- **Le registre `PAR-5`** des identifiants de conditions - toujours pas ecrit, troisieme sprint
  consecutif, dix identifiants de plus mintes ce sprint - route a `theo-archi`.
- **`CONS-3`** - `bandit-manchot-semaine-cp` porte le meme defaut que `CONS-1` sous une autre
  chaine, routee au backlog de s31, prix non chiffre.
- **Dix-sept reserves non bloquantes** versees au backlog de s31 (neuf de l'Instance B, sept de
  l'Instance A dont le reliquat de `CONS-1`, plus `CONS-3`).
- **Le scratchpad partage des deux instances de `nadia-prof`** - defaut d'orchestration constate en
  cours de sprint, sans consequence de contenu, amendement pose en retro pour s31.

## Tableau de consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s30`
(`squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/RAPPORT-tokens.md`) :

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 238 | 476 | 109 382 | 981 674 | 35 676 912 |
| `sofia-sm` | claude-sonnet-5 | 153 | 306 | 61 527 | 1 761 399 | 31 282 627 |
| `theo-archi` | claude-opus-5 | 116 | 232 | 40 047 | 228 327 | 7 029 513 |
| `marc-dev` | claude-sonnet-5 | 221 | 442 | 141 179 | 772 396 | 57 447 143 |
| `lea-dev` | claude-sonnet-5 | 695 | 1 390 | 296 688 | 2 168 007 | 181 189 799 |
| `yanis-qa` | claude-sonnet-5 | 209 | 418 | 63 272 | 1 151 956 | 39 562 272 |
| `relecteur-pedagogique` | claude-sonnet-5 | 163 | 326 | 53 590 | 503 511 | 25 814 307 |
| `nadia-prof` | claude-opus-5 | 383 | 766 | 313 011 | 2 092 382 | 62 955 989 |
| orchestrateur (fil principal) | claude-opus-5 | 81 | 162 | 223 992 | 1 857 286 | 19 028 855 |
| **Total** | | **2259** | **4 518** | **1 302 688** | **11 516 938** | **459 987 417** |

**Aucune invocation mesuree sur cette fenetre pour** : `ines-design` - pas de zero invente, le
rapport le dit explicitement.

**Fenetre de mesure** : 2026-09-06 15:45 a 2026-09-07 08:03 UTC (`RAPPORT-tokens.md`, genere avant
l'ecriture du present rapport ; les derniers commits de cloture et la redaction de `review.md`,
`retro.md` et de ce rapport ne sont donc pas dans cette fenetre).

Les quatre compteurs sont volontairement separes (voir `RAPPORT-tokens.md`) : les additionner
donnerait un classement faux, le cache lu depassant couramment l'output de deux ordres de grandeur.
Le cout d'audit de `8,50` cite plus haut est un chiffre d'arbitrage produit (borne de `nadia-prof`,
lots de matiere), sans rapport avec les compteurs de tokens de ce tableau : rien ne relie l'un a
l'autre, et aucun des deux ne se deduit de l'autre.
