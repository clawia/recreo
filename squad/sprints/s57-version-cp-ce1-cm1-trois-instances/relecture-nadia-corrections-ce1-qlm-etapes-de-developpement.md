# s57 - seconde lecture avant `verify` de `ce1-qlm-etapes-de-developpement` : les corrections (`S57-BR-QLM-CE1-01`, `P0`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-26 11:34 (UTC+01:00), lue par `date`.
- **Arbre** : branche `s57-p5-nadia-relecture01`, base `round-two` a `648db0f9`, lue par `git`.
- **Commit lu** : `47279e05` (`[MARC]`, 2026-10-26 11:19:06 +0100), corrections du premier jet apres ma lecture
  (`lecture-nadia-premier-jet-ce1-qlm-etapes-de-developpement.md`) et l'avis de `theo-archi`
  (`avis-theo-palier-5-garde-rotation-ce1-qlm-01.md`).
- **Type** : seconde lecture avant `verify`. **Aucun Done, aucun verdict de lot**, aucun identifiant neuf, aucune condition neuve.
  Je n'ai modifie ni le paquet, ni les tests.
- **Audit degrade, dit** : rien n'est joue, le paquet n'est toujours pas au catalogue. Je lis le YAML, le diff de `47279e05` et les
  sondes. Le jeu vient a l'audit (`P2`).
- **Mesures lancees** :
  - recompte par script (Python, hors du depot, non commite) : longueur de la bonne reponse contre chaque distracteur, comparaison
    stricte, la meme que `ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest.java:198-199` ;
  - `bash outils/mvn-jdk21.sh -B -q -pl adapters/adapter-content -am test -Dtest=PaquetCe1QlmEtapesDeDeveloppementTest`, JDK 21.0.10 :
    rapport Surefire **25 tests, 0 echec, 0 erreur**. Perimetre : ce seul test, module `adapter-content` et ses dependances. Ce
    n'est ni un `verify`, ni la porte technique.

## Resume

| Point | Reponse |
|---|---|
| Items | **15 justes**. Les deux refus en l'etat (12, 15) et les quatre *a corriger* (3, 6, 7, 11) sont leves |
| Item 6 | juste, lisible, ne retombe pas dans `NRC-CE1-1` ; une observation neuve, sur ma propre proposition (2.1) |
| Sonde `R4` | **confirmee** |
| Bonne reponse strictement la plus longue | **6 sur 15** (items 1, 2, 3, 9, 11, 14), mesure par script ; seuil 7 |
| Indices *"Que vient (juste) avant ?"* | **ne me bloquent pas** ; non tranches ici |
| Cout de `P0` | **1,5 au bareme, sous 1,67** |

---

## 1. Les quinze items, contre la liste fermee de `DVP-CE1-1`

Liste (`ce1-qlm-etapes-de-developpement.yaml:21-24`) inchangee : grenouille *oeuf, tetard, jeune grenouille, grenouille* ; papillon
*oeuf, chenille, chrysalide, papillon* ; poule *oeuf, poussin, poule* ; plante *graine, jeune pousse, plante, fleur, fruit*.

| # | Ligne | Item | Change par `47279e05` | Verdict | Motif |
|---|---|---|---|---|---|
| 1 | `:57` | grenouille, apres le tetard : *la jeune grenouille* | non | juste | inchange depuis ma lecture |
| 2 | `:64` | papillon, apres la chenille : *la chrysalide* | non | juste | inchange |
| 3 | `:71` | plante, apres la graine : *la jeune pousse* ; *la fleur*, *le fruit* | distracteurs | **juste** | ma proposition, recopiee. Deux etapes de la plante, rangs 3 et 4, aucune voisine de la jeune pousse (rang 1). L'enfant doit connaitre l'ordre pour les ecarter |
| 4 | `:78` | grenouille, ordre : *oeuf, tetard, grenouille* | non | juste | aucun distracteur n'est une rotation |
| 5 | `:85` | grenouille, avant le tetard : *l'oeuf* | non | juste | indice : section 4 |
| 6 | `:92` | poule, apres le poussin : *la poule* ; *l'oeuf*, *le tetard* | item remplace | **juste** | section 2 |
| 7 | `:99` | plante, apres la jeune pousse : *la plante* ; *la graine*, *le fruit* | distracteurs | **juste** | ma proposition, recopiee. *La graine* (rang 0) n'est pas voisine de la plante (rang 2) : c'est le piege du sens (avant au lieu d'apres), plausible ; *le fruit* (rang 4) hors de place |
| 8 | `:106` | papillon, ordre : *chenille, chrysalide, papillon* | non | juste | aucun distracteur n'est une rotation |
| 9 | `:113` | grenouille, apres la jeune grenouille : *la grenouille* | non | juste | observation de ma lecture maintenue (2.1) |
| 10 | `:120` | papillon, apres l'oeuf : *la chenille* | non | juste | inchange |
| 11 | `:127` | plante, avant la jeune pousse : *la graine* ; *la fleur*, *le fruit* | distracteurs | **juste** | ma proposition, recopiee. *Le fruit* precede la graine dans le cycle, mais pas la jeune pousse : il n'est pas defendable *"juste avant la jeune pousse"*. Indice : section 4 |
| 12 | `:134` | plante, ordre : *graine, plante, fleur* ; *plante, graine, fleur*, *graine, fleur, plante* | un distracteur | **juste** | le refus est leve : *"fleur, graine, plante"* est retire ; *"graine, fleur, plante"* est mon option. Les rotations du bon ordre sont *fleur, graine, plante* et *plante, fleur, graine* : aucune n'est proposee |
| 13 | `:141` | grenouille, avant la jeune grenouille : *le tetard* | non | juste | inchange |
| 14 | `:148` | papillon, avant le papillon : *la chrysalide* | non | juste | indice : section 4 |
| 15 | `:155` | poule, ordre : *oeuf, poussin, poule* ; *poussin, oeuf, poule*, *poule, poussin, oeuf* | un distracteur | **juste** | le refus est leve : *"poule, oeuf, poussin"* est retire ; *"poule, poussin, oeuf"* est mon option. Aucun des deux distracteurs ne se lit dans le sens du cycle : un poussin ne pond pas, un oeuf ne devient pas une poule sans passer par le poussin |

Ma barre etait ecrite en section 5 de ma lecture, points 1 a 4 et 6 : chacun est tenu tel que je l'avais ecrit, et je leve les
reserves correspondantes sans en ajouter. Le commentaire de la famille c (`:41`) reprend mes mots ; les declarations `TRV-CE1-2`
des items 3, 7 et 11 sont *a, a* (`:70`, `:98`, `:126`).

## 2. L'item 6

`:92-95` : *"Dans la vie de la poule, quelle étape vient juste après le poussin ?"*, *la poule*, distracteurs *l'œuf* et *le têtard*,
indice *"Le poussin grandit. Que devient-il ?"*.

- **Juste** : poussin puis poule, dans ma liste. *L'oeuf* (famille a) n'est pas defendable : il vient avant le poussin, et apres la
  poule seulement dans le cycle, jamais *"juste apres le poussin"*. C'est le piege du sens, comme l'item 7.
- **Lisible** : 13 mots, la construction des dix autres items *voisine* ; l'indice fait 6 mots, deux phrases courtes, mots du CE1.
- **Pas de retour a `NRC-CE1-1`** : le seul item de la poule dans `cp-qlm-cycle-de-vie` est *"Un poussin sort de l'œuf, que
  fait-il ?"* -> *"il naît"* (`cp-qlm-cycle-de-vie.yaml:45-48`). Aucun item de CP ne dit ce que devient le poussin ; l'ancien item 6
  (apres l'oeuf, le poussin) se resolvait par cette phrase, le nouveau ne s'y resout pas. Le mot *grandit* de l'indice est un mot du
  paquet de CP (`:47`, `:54`) : l'indice s'appuie sur une notion de CP pour atteindre l'etape de CE1, c'est une progression, pas une
  reprise. L'indice ne parle plus de la sortie de l'oeuf, comme je l'avais demande.
- **Le tetard** (famille b) : un jeune animal sorti d'un oeuf, comme le poussin. C'est le standard que j'ai admis entre deux
  animaux (ma lecture, 2.1) ; je ne le durcis pas.

### 2.1 Observation neuve, datee du 2026-10-26, sur ma propre proposition

C'est moi qui ai propose cet item, et je n'ai pas vu qu'il reproduisait ce que j'avais note sur l'item 9 : **la bonne reponse est
le nom de l'espece de l'enonce** (*"la vie de la poule"* -> *la poule* ; *"la vie de la grenouille"* -> *la grenouille*). Deux items
sur quinze peuvent donc se repondre en recopiant un mot de la question. Ce n'est **pas une demande** : l'item tient la barre que
j'avais ecrite. Une autre forme existait, que je n'ai pas proposee : *"juste avant la poule"* -> *le poussin*, que le paquet de CP
ne resout pas non plus. Je ne la demande pas maintenant : ce serait relever apres coup une barre que `marc-dev` a atteinte. Je
regarde les items 6 et 9 en jouant, a l'audit ; si un enfant les reussit en recopiant le mot de la question, ce sera une reserve,
ecrite a ce moment-la, et cette forme en sera la correction proposee.

## 3. Garde de rotation et sonde `R4`

- **`R4` confirmee.** *"poussin, poule, œuf"* pour la bonne suite *"œuf, poussin, poule"* se lit dans le sens du cycle : le poussin
  devient la poule, la poule pond l'oeuf. Deux suites defendables, `DVP-CE1-2` la refuse au meme titre que `R1`. C'est la troisieme
  rotation de ma phrase (*"trois ordres sur six sont des rotations du bon ordre, dont la bonne reponse"*), et `theo-archi` a montre
  qu'une garde qui ne teste qu'un decalage passe mes trois sondes (`M1`). Ce n'est pas une condition neuve.
- **Ce que j'ai lu, sans le juger** (la forme est a `theo-archi`) : la garde (`PaquetCe1QlmEtapesDeDeveloppementTest.java:278-279`,
  `:300`), les sondes 15 a 18 de `etapes-fautives.yaml` (15 = `R1`, 16 = `R2`, 17 = `R3`, 18 = `R4`, contenus conformes a mes sondes
  et a celle de `theo-archi`), le test qui attend *"fait tourner"* sur 15, 16, 18 (`:573-579`) et aucune exception sur 17 (`:588-589`).
- **Ce qui reste a moi** : que chaque espece de ma liste soit un cycle (`theo-archi`, section 5) - c'est vrai des quatre ; et
  lequel des trois ordres admis est un bon piege. Sur les items 4, 8, 12 et 15, les distracteurs gardes sont plausibles : chacun
  intervertit deux etapes voisines ou inverse la suite.

## 4. Les indices *"Que vient (juste) avant ?"* (`:88`, `:130`, `:151`)

Inchanges par `47279e05`. **La presente formulation ne me bloque pas** : la methode (remonter la vie de l'espece) est juste, et un
enfant de CE1 comprend la question. La construction reste au `relecteur-pedagogique` ; j'arbitrerai sa proposition quand elle sera
ecrite, et je ne tranche rien ici.

## 5. Conditions transverses, recomptees

- **Bonne reponse strictement la plus longue** (`TRV-CE1-2`) : **6 sur 15**, items 1, 2, 3, 9, 11, 14, par le script decrit en
  en-tete. Seuil : la moitie au plus, soit 7. **Correction de mon propre ecrit** : mon *"5 sur 15"* (ma lecture, `:68`) comptait le
  premier jet et il etait exact pour lui ; le 6 de `marc-dev` compte le paquet corrige, ou l'item 11 devient le sixieme (*la graine*,
  9 caracteres, contre *la fleur* et *le fruit*, 8). Ma projection *"6 sur 15 apres mes corrections"* tombe juste, mais je l'avais
  calculee a la main ; c'est le script qui la confirme.
- **`FER-1`** : les six etapes de la parenthese restent en bonne reponse de la tache *voisine* : oeuf (5), tetard (13), grenouille
  (9), graine (11), jeune pousse (3), plante (7).
- **`FER-3`** : *voisine* 11 items, *ordre* 4 (4, 8, 12, 15).
- **Rang de la bonne reponse** : 15 items et 45 propositions, l'entree de rang garde ses totaux ; la mecanique melange a chaque
  manche, la place a l'ecran ne trahit rien.
- **Indices** : quinze, tous distincts, 6 a 11 mots (script).
- **Observation pour le jeu, pas une demande** : les items 3 et 11 portent la meme paire de distracteurs (*la fleur*, *le fruit*),
  ce sont mes propositions. Je le regarde en jouant.

## 6. Cout reel de `P0`

- **Au bareme** : 1,0 (lot, lecture et correction comprises) + 0,5 (cette seconde lecture, sur la marge) = **1,5, sous 1,67**.
- Cette lecture ne demande aucune correction : elle n'appelle pas de troisieme lecture.
- **Ce que je nomme pour que personne ne le croie couvert** : l'arbitrage des trois indices (section 4) viendra apres. S'il n'en
  change que la formulation, je le lis sur ces trois lignes, pas sur le paquet ; s'il devait etre compte comme une troisieme lecture
  du lot, `P0` atteindrait 2,0 et depasserait 1,67. Le classement est a `camille-po` (A.244), qui tient la projection.
- **A la consommation** : non mesure pour cette lecture. Je n'ecris pas un chiffre que je n'ai pas lu.

## 7. Ce dont je reste la seule garde

L'exactitude de chaque ordre contre ma liste ; `NRC-CE1-1` sur le sujet ; `CYC-1` ; la plausibilite des distracteurs, rotation
comprise une fois la garde passee ; la methode de chaque indice ; l'operation mentale de la tache *ordre* ; les deux items dont la
reponse est le nom de l'espece. Aucune n'est couverte par un test.

## 8. Routage

| A qui | Quoi |
|---|---|
| `marc-dev` | rien a corriger |
| `theo-archi` | `R4` confirmee (section 3) |
| `relecteur-pedagogique` | les trois indices de la section 4, comme prevu |
| `sofia-sm`, `camille-po` | `P0` a 1,5 ; le classement de l'arbitrage des indices (section 6) |

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-26.
