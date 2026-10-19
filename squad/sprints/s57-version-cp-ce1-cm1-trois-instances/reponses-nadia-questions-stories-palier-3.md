# s57 - reponses de `nadia-prof` aux questions des stories du palier 3, et arbitrage prealable du palier 3 par instance

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-19, 22:50 (UTC+02:00), lu par `date`.
- **Arbre** : `round-two` a `98dc6574`. Non suivis ou modifies avant ecriture : `docs/backlog/backlog.md` (annotation "Palier 3
  de s57" de `camille-po`), `.claude/settings.local.json`.
- **Lu** : l'annotation "Palier 3 de s57" du backlog en entier ; `arbitrage-nadia-preparation-palier-3-ce1.md` ;
  `ARBITRAGE-PREALABLE-s48-francais-ce1.md` 1.3, 2, 3.7, 5.2 ; `ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`
  1, 2, 4.1, 5.1 ; `ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` (table des conditions) ; criteres de s57, 11.3 ;
  `cloture-palier-2.md` sections 1, 2, 6, 14 ; `planning.md` `D2` ; `ce1-texte-documentaire.yaml` (en-tete, commentaires d'item).
- **Audit degrade, declare** : rien n'est joue. Ce document ne prononce aucun Done, n'amende aucune story (c'est `camille-po`),
  n'ecrit ni n'alloue aucun identifiant de condition ou de reserve, et il n'est pas commite par moi.

## Resume

| Point | Reponse | Story a amender, par `camille-po` |
|---|---|---|
| `Q-X1` | Mathematiques CE1 : **ni `LON-CE1-5` ni `TRV-CE1-1` a `-7`**, s48 fait foi. EMC CE1 : `TRV-CE1-1`, `-2`, `-5`, `-6`, `LON-CE1-1`, `-2`, `-5`, pas `-3`, `-4`, `-7`. Questionner le monde CE1 : idem EMC, plus `BIA-4` ; aucun audit a s57 | `NB8`, "Gardes de contenu communs", `EB1`, `EB6` |
| `Q-X2` | **Caracteres** | `NE5`, `EB7` |
| `Q-X3` | La preparation **ne suffit pas**. L'arbitrage prealable par instance est la section 7 de ce fichier | `K1` (b) |
| `Q-X4` | Forme testable donnee pour la part "ce que l'enfant possede" ; le reste de `BCO-CE1-2` reste a moi | `S57-BR-EMC-CE1-01`, criteres |
| `DOC-CE1-1` | **Corrige** : le genre se lit sur le texte, pas sur la question | `DO2` |
| `Q-X6` | **Story separee**, comme au palier 2 ; `LC` sort des cinq stories de branchement | les cinq `S57-BR-*`, et `S57-ARB-PALIER-3` a ecrire |

---

## 1. `Q-X1` : portee de `LON-CE1-5` et de `TRV-CE1-1` a `-7`

**Reponse.** s48 fait foi. `TRV-CE1-1` a `-7` sont ecrites *"valables pour chacune des vingt-six lignes"* de francais CE1 (s48,
section 2), et `LON-CE1-5` *"ne vaut pas pour les mathematiques CE1"* (s48, 1.3). Rien ne les a etendues depuis.

| Matiere | Ce qui vaut | Ce qui ne vaut pas |
|---|---|---|
| Mathematiques CE1 (`MAT-CE1-01`) | `LON-CE1-1`, `-2` (s36, repris par s43 lot 1) ; `NUM-CE1-1` a `-7` | `LON-CE1-3` a `-5`, `TRV-CE1-1` a `-7` |
| EMC CE1 (`EMC-CE1-01`) | instruction du 2026-10-13, section 2, *"reprises telles qu'ecrites"* : `TRV-CE1-1`, `-2`, `-5`, `-6`, `LON-CE1-1`, `-2`, `-5`, `FER-1` a `-3`, `CYC-1`, `NRC-CE1-1`, `EMC-1` a `EMC-7` ; au moins 20 items (preparation, 4.4) | `TRV-CE1-3`, `-4`, `-7` : non repris |
| Questionner le monde CE1 | idem EMC, plus `BIA-4`, `QMT-CE1-1`, `-2` | idem ; **aucun lot audite a s57** (preparation, 1.3) |

**Motif.** *"Une borne etendue a une population non comptee serait une regle que personne n'a decidee"* (s48, 1.3). Le point 1
de ma preparation, 4.1 (*"communes a tous les lots du palier 3"*), recopie la section 6.1 du palier 2, ou tous les lots etaient
du francais. **Correction de mon propre constat, trois fois** :

1. Preparation, 4.1, point 1 : se lit *"pour les lots de francais CE1"* ; l'EMC prend la liste de la ligne EMC ci-dessus, les
   mathematiques celle de la ligne mathematiques.
2. Preparation, section 5, premiere ligne : *"`LON-CE1-3` a `-5` et `TRV-CE1-1` sur les douze paquets de s53"* inclut cinq
   paquets de mathematiques ou ces conditions ne valent pas. Elle se lit sur les paquets de francais de s53 et sur l'EMC CE1.
3. Instruction du 2026-10-13, 5.1, ligne questionner le monde : *"`LON-CE1-*` et `TRV-CE1-*` ont mordu le CE1 en francais et en
   mathematiques"*. En mathematiques, seules `LON-CE1-1` et `-2` ont mordu : 5 des 16 fiches qui citent `MAT-CE1` citent
   `LON-CE1`, aucune ne cite `TRV-CE1` ni `LON-CE1-5` (`grep -l`, ce jour). La conclusion de la ligne (terrain non neuf) ne
   change pas.

**A amender.** `NB8` : retirer la phrase sur `Q-X1` ; *"propositions de 3 mots au plus"* reste une mesure du jour, pas une
condition (`NUM-CE1-7` est la condition des propositions). "Gardes de contenu communs" : `LON-CE1-3`, `-4`, `-5` et
`TRV-CE1-1` a `-7` valent pour `-06`, `-07`, `-11` ; pour `EMC-CE1-01`, la liste ci-dessus ; pour `MAT-CE1-01`, aucune.
`EB1` : la source du plancher de 20 est ma preparation, 4.4, pas `TRV-CE1-7`. `EB6` : retirer `TRV-CE1-4`.

## 2. `Q-X2` : *"strictement la plus longue"*, en caracteres

**Reponse.** En **caracteres** : le nombre de caracteres du texte de la proposition tel que l'enfant le lit, espaces et
ponctuation compris. Les mots ne comptent pas.

**Motif.** Le biais vise est ce que l'oeil voit : un bouton plus long que les deux autres. Compte en mots, *"Il ne mange pas"*
et *"Il mange pas du tout"* se departagent mal, et les egalites rendent la borne inoperante. Surtout, c'est l'unite des 19
tests de paquet CE1 qui assertent `TRV-CE1-2` (tous par `String.length()` ; 14 sous la forme
`bonneReponse().length() > d.length()`, 5 sous une forme voisine, lus ce jour) et sur lesquels mes
verdicts de francais CE1 ont ete rendus : choisir les mots maintenant changerait apres coup la barre de paquets deja valides.

**A amender.** `NE5` : le test asserte les caracteres seuls ; la mesure en mots est retiree (elle rend 0, comme les caracteres,
et n'est pas une condition). `EB7` : la meme unite, ecrite.

## 3. `Q-X3` : l'arbitrage prealable par instance

**Reponse.** Ma preparation **ne suffit pas** comme condition (b) de `D2`. Elle le dit elle-meme dans son en-tete (*"pas
l'arbitrage prealable du palier 3"*), et la condition 3 de 11.3 exige qu'il *"porte la cloture du palier precedent : les
verdicts, et le consomme"*, ce que la preparation, ecrite avant `cloture-palier-2.md`, ne fait pas. **Il n'y a pas de second
fichier** : l'arbitrage prealable est la section 7 ci-dessous, sur la consigne qui m'a ete donnee de l'ecrire ici.

**A amender.** `K1` (b) : remplacer la commande par
`git show round-two:squad/sprints/s57-version-cp-ce1-cm1-trois-instances/reponses-nadia-questions-stories-palier-3.md > /dev/null`,
qui sort non zero aujourd'hui (fichier non commite) : rouge tant qu'il n'est pas sur `round-two`. `planning.md` `D2` (b)
reference ce fichier.

## 4. `Q-X4` : *"un item sur ce que l'enfant possede"*, forme testable

**Reponse.** La part mesurable : sur les champs que l'enfant lit (`enonce`, `bonneReponse`, `distracteurs`, `indice`,
`explication` s'il existe), **aucun** des mots suivants en mot entier, sans casse : *ton, ta, tes, tien, tienne, tiens,
tiennes, chez, maison*. Cible : 0 occurrence sur le paquet. Le test du paquet l'asserte, ou le rapport porte
*"non garde : `BCO-CE1-2`"*.

**Motif.** `BCO-CE1-2` situe les biens personnels *"a l'ecole"* et chez *"un personnage nomme"* ; un possessif de deuxieme
personne ou un mot du foyer est le signe ecrit qu'un item parle de ce que l'enfant a, ou de chez lui. Un faux rouge (*"tiens"*
verbe) se voit et se corrige ; c'est le sens d'erreur que je prefere.

**Ce que la mesure ne voit pas, et dont je reste la seule garde** : un item sans possessif qui vise l'enfant (*"Ce cahier est
a qui ?"* sans personnage), qu'un bien personnel soit bien celui d'un personnage nomme, qu'aucun item ne compare des biens,
que preter ne soit jamais un devoir. Je les lis a l'audit.

**A amender.** `S57-BR-EMC-CE1-01` : ajouter cette mesure aux criteres d'acceptation, et retirer de "Ce que la story ne dit
pas" la seule part *"un item sur ce que l'enfant possede"* ; le reste de `BCO-CE1-2` y demeure.

## 5. `DOC-CE1-1`, mesure de `DO2` : corrigee

**Reponse.** La reconnaissance par l'`enonce` (*"faut-il"*, *"met-on"*) ne lit pas `DOC-CE1-1`. La condition definit le genre
par **le texte** : une consigne ecrite est un texte de *"deux a quatre instructions"*. *"Que faut-il au herisson pour
hiberner ?"* poserait une question en *faut-il* sur un texte documentaire et serait comptee consigne. Elle rend 9 aujourd'hui
parce que le contenu actuel s'y prete, pas parce qu'elle mesure le genre.

**Mesure que je retiens.** Le paquet marque le genre au-dessus de chaque item : `grep -c '^  # consigne$' F` rend **9**,
`grep -c '^  # documentaire$' F` rend **11** (ce jour), somme 20. Le test du paquet : (a) chaque item porte exactement un des
deux marqueurs ; (b) au moins 10 `documentaire` et au moins 5 `consigne` ; (c) chaque texte marque `consigne` compte 2 a 4
phrases, par la regle de s47. **Ce que le test ne voit pas** : qu'une phrase soit une instruction, qu'un texte marque
`documentaire` n'en soit pas une, et que la question de consigne porte sur l'ordre ou le contenu d'une instruction. Je les lis.

**A amender.** `DO2` : remplacer la commande sur l'`enonce` par les deux comptes de marqueurs et les clauses (a) a (c).

## 6. `Q-X6` : avis, la question est a `sofia-sm`

**Avis.** Une story transverse `S57-ARB-PALIER-3`, comme `S57-ARB-PALIER-2`, et `LC` retire des cinq stories de branchement.

**Motif.** La lecture complete est payee sur le 0,5 d'arbitrage prealable de chaque instance (preparation, section 2) : son
critere appartient au poste qui la paie. Dans une story de branchement, `LC` est un critere d'acceptation que le developpeur
ne peut pas tenir et qui ne conditionne pas son commit, seulement mon verdict. Le commit de la ligne ancetre du commit du
verdict, verifie par `sofia-sm` a la cloture, se lit mieux sur une seule story que sur cinq.

**A amender.** Les cinq `S57-BR-*` : `LC` devient un renvoi (*"le verdict suppose `LC` de `S57-ARB-PALIER-3`"*). La commande de
mesure de `LC` passe telle quelle dans `S57-ARB-PALIER-3`, avec la section 7 de ce fichier comme livrable.

---

## 7. Arbitrage prealable du palier 3, instances A, A' et B

Condition 3 de 11.3 et `D2` (b) de `planning.md`. Ecrit avant tout lot du palier, reprend ma preparation sans la reecrire, avec
les corrections des sections 1 a 5.

### 7.1 La cloture du palier 2, portee

Source : `cloture-palier-2.md` sections 1, 2 et 6, lues ce jour. Je ne la recalcule pas.

| Poste | Instance | Verdict |
|---|---|---|
| `-05`, `ce1-pronoms` | A | REFUSE le 2026-10-16 (`TRV-CE1-6`), corrige, rejoue : VALIDE |
| `-24`, `ce1-familles-de-mots` | A' | VALIDE, premier passage |
| `RCT-CE1-R1`, `VSJ-CE1-R2` | A | levees ; `FRA-CE1-04`, `-13` VALIDE |
| `TPH-CE1-R1`, `GNA-CE1-R1` | A' | levees ; `FRA-CE1-10`, `-15` VALIDE |
| `RDO-CP-R3` | B | levee ; `ART-CP-10` VALIDE |
| `N1`, `N2` | B | prises en compte ; `SCI-CM1-20` reste `couvert-valide` |

Aucun glissement. **Consomme** : 6,25 factures sur 8,0 composes (A 2,75, A' 2,25, B 1,25) ; marges 1,5 non entamees et 0,25
non affecte, tombes (`DEC-0120` A.114). Rien n'est reporte au palier 3. **Reste ouvert, a moi** : la lecture de l'isolation du
palier 2 (`cloture-palier-2.md`, section 10). Elle n'est pas une condition de `D2` ; je ne la fais pas ici.

### 7.2 Capacite et affectation, confirmees (`CR41`)

| Instance | Arbitrage | Marge de rejeu | Lots, prix | Somme | Lots |
|---|---|---|---|---|---|
| A | 0,5 | 0,5 | `-06`, `-07`, `-11`, 1,0 chacun | 4,0 | 3 sur 3 |
| A' | 0,5 | 0,5 | `EMC-CE1-01`, 1,0 | 2,0 | 1 |
| B | 0,5 | 0,5 | `MAT-CE1-01`, 1,0 | 2,0 | 1 |
| **Palier** | | **1,5, nommee, allouee a aucun lot** | | **8,0** | 5 |

`PAR-8` et `PAR-9` tenus. Variante (i) de ma preparation (2.2), ecrite par `camille-po`. Les cinq lots sont textuels, 1,0 ;
pour `EMC-CE1-01`, le prix est celui de l'audit, l'ecriture du paquet n'est pas un poste de mon bareme. Aucun prix neuf.

### 7.3 Conditions de Done, par lot

- **Communes** : preparation 4.1, points 2 a 6, inchanges ; point 1 lu par matiere (section 1 ci-dessus).
- **A** : preparation 4.2, inchangee, avec `DOC-CE1-1` mesure comme en section 5 et `TRV-CE1-2` en caracteres (section 2).
- **B** : preparation 4.3, ligne `MAT-CE1-01`, inchangee. Plafond `en cours`, mention de `NUM-CE1-2` mot pour mot.
- **A'** : preparation 4.4, ligne `EMC-CE1-01`, inchangee, avec la mesure de la section 4.

### 7.4 Ce qui precede un lot, et ce qui n'entre pas

- **A'** : la reponse de `theo-archi` a `Q-X5` (c), *l'amendement d'`ADR-0065` suppose-t-il qu'A' ne porte que du francais ?*,
  avant le premier worktree d'A' (preparation, 2.2), en plus de `K1` (c).
- **N'entrent pas au palier 3** : `-08`, `-09` (condition 4 de 11.3) ; tout lot de questionner le monde CE1 (preparation, 1.3) ;
  `MAT-CE1-10`, `-11`, `-14`, `-15` ; `EMC-CE1-02` a `-04` ; tout lot sans story.

## 8. Ce dont je reste la seule garde, ajoute ici

| Propriete | Pourquoi aucun test ne la tient |
|---|---|
| Qu'un texte marque `consigne` soit fait d'instructions, et `documentaire` non | un marqueur est une declaration de l'auteur |
| Qu'un item d'EMC sans possessif ne vise pas l'enfant | la mesure de la section 4 lit des mots |

**Signe** : `nadia-prof`, s57, ouverture du palier 3, 2026-10-19.
