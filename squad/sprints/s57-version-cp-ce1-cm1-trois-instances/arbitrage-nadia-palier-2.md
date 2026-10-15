# s57 - arbitrage prealable du palier 2 : instances A, A' et B

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-15, 18:15 (UTC+02:00), lu par `date`.
- **Arbre** : `round-two` a `6dbb855d`, branche `s57-nadia-arb2`, worktree dedie, arbre propre avant ecriture.
- **Type** : arbitrage prealable du palier 2, pour les trois instances (`ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`,
  11.3 condition 3), avant tout lot du palier. Il remplit la condition (b) de `D2` de `planning.md` et porte la cloture du
  palier 1. Modele : `arbitrage-nadia-palier-1.md`.
- **Ce qu'il n'est pas** : la composition du palier 2 (`camille-po`), ni son planning, sa table d'isolation ou ses affectations
  (`sofia-sm`). Aucun Done, aucun verdict de jeu.
- **Audit degrade, declare** : aucun lot du palier 2 n'est branche et je n'ai rien joue. Les paquets candidats sont comptes, pas
  relus en entier ici (section 6).

## Resume

| # | Point | Decision | Section |
|---|---|---|---|
| AP1 | Cloture du palier 1 portee | 7 postes a verdict, 0 refus, 0 glissement ; consomme **5,75** sur 8,0, marges intactes (1,5), 0,75 non affectes | 1 |
| AP2 | Conditions de 11.3 et 11.4 pour le palier 2 | Tenues de mon cote ; **aucun chiffre au-dela de 8,0**, O2 non derivable | 2 |
| AP3 | Isolation du palier 1, lue | Ports tenus a deux ecarts pres ; **"aucune ecriture a la racine" non tenue**, par 8 agents sur 9, moi comprise ; **aucune occurrence de la forme (c)** | 3 |
| AP4 | Critere 3 de `S57-ARB-PALIER-1` pour `ce1-texte-narratif` | **Tenu** | 4 |
| AP5 | Ce que j'audite au palier 2, prix | Table par instance ; la somme des candidats (9,0 a 9,75) depasse 8,0 : il faut un ordre | 5 |
| AP6 | Criteres de Done pedagogique par lot candidat | `-05`, `-06`, `-07`, `-11` (X) ; `-24` (Y) | 6 |
| AP7 | Ordre de priorite parmi les restes du palier 1 | Mesure de la forme commune d'abord, puis `TPH`/`GNA`, `RCT-CE1-R1`, `RDO-CP-R3` | 7 |
| AP8 | Ce qui n'entre pas dans le palier 2 | Neuf interdits | 8 |
| AP9 | Lacunes | Les mots de l'utilisatrice pour `Q-N4` et `S2` : nommees, pas comblees | 9 |

---

## 0. Ce que j'ai lu et mesure

| Mesure | Commande ou fichier | Rendu |
|---|---|---|
| Arbre | `git log -1 --format=%h` | `6dbb855d` |
| Cloture du palier 1 sur `round-two` | `git show HEAD:squad/sprints/s57-version-cp-ce1-cm1-trois-instances/cloture-palier-1.md` | sort 0 |
| Amendement d'`ADR-0065` | `git merge-base --is-ancestor 729b0d8a HEAD` | sort 0 |
| Paquets candidats | `grep -c 'bonneReponse:'` ; `grep -c "paquet: <nom>$"` sur `catalogue.yaml` | `ce1-pronoms` 20, `ce1-inference` 20, `ce1-texte-documentaire` 20, `ce1-negation` 24, `ce1-familles-de-mots` 24 ; 0 au catalogue chacun |
| L'item *livre* | `grep -c 'bonneReponse: "librairie"'` sur `ce1-familles-de-mots.yaml` | **1** : non corrige |
| Stories des lots candidats | `grep -c "^### S57-BR-FRA-CE1-<nn>" docs/backlog/backlog.md`, pour `05`, `06`, `07`, `11` et `24` | 0 chacune |
| Critere 3, `ce1-texte-narratif` | `git merge-base --is-ancestor 13d6285f bdc7049b` ; `git diff -U0 3a3ea97e 4dd6f298` sur le paquet | sort 0 ; 5 lignes changees, deux items (section 4) |
| Arithmetique | `awk` sur les postes des sections 1 et 5 | section 1 : 5,75 / 1,50 / 0,75 / 8,00 ; section 5 : 9,00 et 9,75 |
| Isolation | script Python hors depot sur les transcriptions des deux sessions du palier ; listage des racines de scratchpad | section 3 |
| Lu en entier | `cloture-palier-1.md` ; `arbitrage-nadia-palier-1.md` ; `planning.md` ; `composition-palier-1.md` ; `duree-et-paliers.md` ; criteres de s57, 4.1 a 4.4 et 11.1 a 11.10 ; backlog, annotations de s57 (lignes 21700 a 22194) ; mes reponses du palier (`reponse-nadia-forme2-palier-1.md`, `reponse-nadia-q-palier-1.md`, `reponse-nadia-q8.md`, `rejeu-nadia-b2-palier-1.md`) ; fiches de `texte-narratif-ce1` et de `rapprocher-deux-oeuvres-cp` ; s48, sections 2, 3.5 a 3.7, 5.2 ; `squad/lessons.md` | - |

---

## 1. La cloture du palier 1, portee (condition 3)

**Verdicts**, tous dans `cloture-palier-1.md` section 1, chacun de ma main et dans sa fiche :

| Poste | Instance | Verdict |
|---|---|---|
| 3, `VSJ-CE1-R1` | A | levee ; `FRA-CE1-13` VALIDE SOUS RESERVE, `VSJ-CE1-R2` |
| 4, `ce1-phrases-types` | A' | VALIDE SOUS RESERVE, `TPH-CE1-R1` |
| 5, `ce1-accord-groupe-nominal` | A' | VALIDE SOUS RESERVE, `GNA-CE1-R1` |
| 6, `S54-OBS-SCI-CONSIGNE` | B | VALIDE |
| 7, `ce1-texte-narratif` | A | VALIDE SOUS RESERVE, `RCT-CE1-R1` |
| 8, rejeu d'`ART-CP-10` | B | VALIDE SOUS RESERVE, `RDO-CP-R3` ; refus du 2026-10-11 leve |
| 9, B2 | B | sans verdict propre, jouee par le poste 8 |

Aucun refus, donc rien a rejouer dans une marge ; aucun lot non audite, donc aucun glissement. **Le palier 1 est clos pour
ses trois instances au sens de ma condition 2.**

**Consomme, avec les postes qu'il additionne** (`awk`, sortie lue ce jour) :

| Instance | Postes consommes | Consomme | Marge, intacte | Non affecte | Budget |
|---|---|---|---|---|---|
| A | arbitrage 0,5 ; `VSJ-CE1-R1` 0,5 ; lot `-04` 1,0 | 2,00 | 0,5 | - | 2,5 |
| A' | arbitrage 0,5 ; lots `-10` et `-15` 2,0 | 2,50 | 0,5 | - | 3,0 |
| B | arbitrage 0,5 ; rejeu d'`ART-CP-10` 0,5 ; `S54-OBS-SCI-CONSIGNE` 0,25 | 1,25 | 0,5 | 0,75 | 2,5 |
| **Total** | | **5,75** | **1,50** | **0,75** | **8,00** |

Hors capacite, sans prix : `RP-ACCUEIL-147` (B), mes reponses `Q-R*`, `Q-T*`, `Q-F1`, `N1` a `N4`, `D1` a `D5`, prises sur les
arbitrages. Les 7,25 *"engages"* de `cloture-palier-1.md` section 7 sont ces 5,75 plus les trois marges.

---

## 2. Conditions de 11.3 et 11.4, pour le palier 2

| Condition | Etat | Ce qui reste, et a qui |
|---|---|---|
| 1. `PAR-8` et `PAR-9` seuls : 8,0 au total, 5,0 et trois lots par instance, marge de 0,5 par instance **qui porte un lot ou un rejeu** (lecture corrigee le 2026-10-14, `arbitrage-nadia-palier-1.md` section 2) | a tenir par la composition | `camille-po` ; je la verifie sur l'amendement de `planning.md` |
| 2. Le palier precedent est clos pour toutes ses instances | **tenue** (section 1) | - |
| 3. Arbitrage ecrit avant le premier lot, portant la cloture | **ce document** | - |
| 4. Les six conditions de `PAR-2` (4.2), par palier | 1, 2, 3, 4, 6 : tenues ou sans objet. **5 : non tenue au palier 1** (section 3) | `sofia-sm` : table du palier 2 ; `theo-archi` : le moyen (section 3.4) |
| 5. `PAR-13` | **tenue** : `-05`, `-06`, `-07` ont pour cousines `NAR` et `RCT` ; `-11` et `-24` ont la leur (4.2, troisieme ligne) ; B ne porte que des rejeux de jeux servis | - |
| 6. Un palier sans lot instruit ne s'ouvre pas | cinq lignes de rang 1 instruites, cinq paquets ecrits (section 0) ; **aucune story** | `camille-po` |
| 11.4 : rien au-dela de 8,0 | (ii) non etablie : s56 jamais verifie par moi, et un palier clos ne compte pas pour (ii) | aucun chiffre au-dela de 8,0 au palier 2 |

---

## 3. L'isolation du palier 1, lue

**Fenetre** : 2026-10-14 15:45 a 2026-10-15 17:37 (UTC+02:00), deux sessions, donc deux racines de scratchpad. **Methode** : un
script lit, dans chaque transcription de la fenetre, les appels d'outils (chemin des ecritures de fichier, commandes du shell,
lancements de jar avec leur port, arrets de processus), puis chaque fichier present a la racine est rattache a la premiere
commande qui le nomme. **Limites** : une ecriture faite par un programme (un `open()` Python) n'est vue que par le nom du fichier ;
les ports de Chrome passes en argument a un script ne sont pas tous lisibles. Je ne compte rien que je n'ai pas rattache a une
transcription.

### 3.1 Ports et processus (`I2`, `I3`) : tenus, deux ecarts

| Agent | Port de la table | Port lance | Ecart |
|---|---|---|---|
| `nadia-prof` A, A', B | 18511, 18512, 18513 | les memes | aucun |
| `marc-dev` A, A' | 18521, 18522 | les memes | aucun |
| `lea-dev` B | 18531 | 18531 ; **19231 de 15:33:12 a 15:33:45** le 2026-10-15 (son port de debogage de Chrome, `engagement-poste-8.md`), arrete par son PID puis relance sur 18531 | port d'une autre sorte, a elle seule, sans collision |
| `ines-design` | 18543 | **18457**, 2026-10-14, 18:22 a 22:29 | port hors table ; aucun autre agent ne l'a pris |

Chaque arret est fait par PID ; **aucun arret par nom d'image**. Deux jars n'ont jamais servi le meme port en meme temps.

### 3.2 Repertoires (`I1`, ma condition 5) : non tenue

**Un crochet du harnais refuse l'outil d'ecriture de fichier a la racine du scratchpad** (7 refus dans la fenetre). Il est dans un
reglage local hors depot : aucun fichier du depot ne dit qui l'a pose ni quand. **Il ne voit pas les redirections du shell**, et
c'est par elles que la racine a ete ecrite.

| Agent | Ecrit a la racine, ou hors du scratchpad (heures UTC+02:00) |
|---|---|
| `camille-po` | 8 fichiers le 2026-10-14 (18:19 - 18:23) et 4 le 2026-10-15 (11:19 - 11:32) ; repertoires `camille`, `camille-po-s57-p1`, `s57-p1-camille-po` au lieu de `s57-p1-camille-po-t` |
| `marc-dev` | `m1.log` le 2026-10-14 a 16:15 (**poste 4, A'**) ; un journal du poste 3 (A) dans le repertoire parent des worktrees, supprime depuis ; deux journaux de `F1` le 2026-10-15 |
| `lea-dev` | journaux et fichiers de B1 (12:08 - 12:21) et de B2 (15:34 - 15:48), deux profils de Chrome ; journal et PID du jar de B2 dans le repertoire temporaire du systeme (`$TEMP`) |
| `sofia-sm` | poste 5 (A') : journaux de build dans `$TEMP`, repertoire `s57-p1-sofia-dev-ap` jamais cree ; cloture : 7 fichiers a la racine (10:44, puis 17:13 - 17:37) |
| `theo-archi` | 7 fichiers le 2026-10-15 (11:23 - 16:11) ; repertoire `theo-s57-avis` au lieu de `s57-p1-theo-archi-t` |
| `yanis-qa` | 2 fichiers le 2026-10-14, puis **le deplacement de 7 fichiers de `camille-po` et du `relecteur-pedagogique`** dans son repertoire, a 22:05:56, rendus a 22:06:15 ; 5 fichiers le 2026-10-15 |
| `relecteur-pedagogique` | 5 fichiers le 2026-10-14 (21:48 - 22:01) |
| non rattache | 4 journaux de tests ecrits a la racine le 2026-10-14 a 22:03 - 22:04 : aucune commande lue ne les ecrit ; je ne les attribue a personne |
| `ines-design` | rien a la racine ; repertoire `ines-s57` au lieu de `s57-p1-ines-design-t` |
| **`nadia-prof`, moi** | un `.png` au nom non developpe (`s57-p1-nadia-prof-a$f.png`) le 2026-10-14 a 19:15, pendant mes audits de A, A' et B ; 5 fichiers le 2026-10-15 (12:17 - 17:06), dont deux journaux de build du rejeu de B2 ; un repertoire `s57-p1-nadia-prof-a-poste7` hors du format |

**Huit agents sur neuf ont ecrit a la racine, moi comprise.** Ma condition 5 (*"aucune ecriture a la racine"*) n'est pas tenue au
palier 1.

### 3.3 Forme (c) : aucune occurrence

Aucun chemin n'a ete ecrit par deux instances. Les seuls noms ecrits deux fois l'ont ete par le meme agent (`build.log`,
`theo-archi` ; un brouillon du backlog, `camille-po`).

**Ce qui s'en approche le plus, et pourquoi je ne le classe pas** : le deplacement de `yanis-qa`. Il a pris pour siens sept
fichiers de deux autres agents, sans le savoir, pendant que la session du relecteur etait ouverte (jusqu'a 22:07:25) ; il les a
rendus 19 secondes plus tard, et le relecteur n'y a pas touche entre-temps (ses deux gestes de cette minute portent sur son
propre releve). C'est le mecanisme de s54. **Ce n'est pas la forme (c) a la lettre** : elle parle de deux instances, et aucun de
ces trois agents n'en est une. Je ne l'etends pas apres coup ; je l'ecris ici pour que la verification de (ii) a la cloture de
s57 le lise.

**Un fait pour la meme verification, sans qualification** : mes audits des postes 3 a 6 (A, A', B) ont ete faits par une seule
invocation, le 2026-10-14 de 18:49 a 20:53, avec un repertoire et un port par instance.

### 3.4 Consequence

1. **Aucun verdict ne change.** Chacun repose sur le jar joue, et le motif de ma condition 5, deux instances qui ecrivent la meme
   ressource (s54), ne s'est pas produit (3.3).
2. **Ma clause de 4.2, *"une condition non tenue retire l'accord pour le lot qu'elle touche"*, ne mord pas ici** : les lots
   touches (`-10`, `-15` par mes audits et `m1.log`, `-04`, `VSJ-CE1-R1`) sont audites et fusionnes. Lue a la cloture, la clause ne
   peut que constater. C'est un defaut de la clause, pas une indulgence, et je le dis.
3. **Pour le palier 2, la condition 5 reste telle qu'ecrite.** Ce qui manque est un moyen qui voie le shell. Le moyen n'est pas
   a moi : `theo-archi` (`Q4` de son avis) et `sofia-sm` (table et consignes de lancement). D'ici la, je lis les transcriptions a
   la cloture du palier 2 comme ici, redirections comprises.

---

## 4. Critere 3 de `S57-ARB-PALIER-1` pour `ce1-texte-narratif` : tenu

- **Critere 2** : la ligne *"lecture complete : `ce1-texte-narratif`, 20 items lus"* est dans `rejeu-nadia-palier-1.md` section 7 ;
  20 egale le compte du paquet.
- **Critere 3** : elle entre par `13d6285f` ; le verdict est `bdc7049b` ; `git merge-base --is-ancestor 13d6285f bdc7049b` sort 0.
- **Ce que la ligne lit** : la version de `3a3ea97e`. Entre elle et le commit joue (`4dd6f298`), le paquet change de 5 lignes, sur
  deux items seulement (Sara : bonne reponse, distracteurs, indice ; Nour : bonne reponse, distracteurs). Ces deux items sont
  relus au jeu dans le verdict meme (fiche, sections 1 et 3), et la ligne dit qu'ils le seront.

**Valeur : tenu.** Les 18 autres items lus sont ceux qui sont servis ; les deux qui ont change ont ete relus avant le verdict.

---

## 5. Ce que j'audite au palier 2, et a quel prix

**Prix** : mes tarifs ecrits. Lot neuf 1,0 ; rejeu borne par paquet (`PAR-10`) 0,5 quand une forme ou des textes changent, 0,25
quand le paquet ne change pas et qu'aucune forme neuve n'est a juger ; regard sans prix au premier rejeu qui suit (`Q-T7`,
`Q-T11`). Arbitrage 0,5 et marge 0,5 par instance qui porte un lot ou un rejeu.

| Instance | Poste candidat | Prix | Source du prix |
|---|---|---|---|
| **A (X)** | lots `-05`, `-06`, `-07`, `-11` : **trois au plus** | 1,0 chacun | bareme |
| | `RCT-CE1-R1` | 0,25 paquet inchange ; 0,5 si des textes changent | `reponse-nadia-forme2-palier-1.md` 5.1 |
| | `VSJ-CE1-R2` | idem | idem |
| **A' (Y)** | lot `-24`, apres `S57-FRA-FAMILLES-LIVRE` | 1,0, correction comprise | bareme |
| | `TPH-CE1-R1`, premier rejeu de la forme commune, clause `Q-R4` comprise | 0,5 | 5.1 |
| | `GNA-CE1-R1`, meme jar | 0,25 ; 0,5 si la forme de ce paquet differe | 5.1 |
| **B** | `RDO-CP-R3` | 0,5 | `PAR-10`, fiche section 6 |
| | `N1` et `N2`, si `camille-po` les prend | 0,25 les deux | `reponse-nadia-q-palier-1.md` 4.1 |
| sans prix | `S57-MESSAGE-PARTIE-PERDUE`, `S57-TYP1-TEXTES-DE-GABARIT`, verdict de designation et de saisie | 0 | `Q-T7`, `Q-T11` |
| hors `PAR` | `D5`, `S57-GARDE-NOMBRE-DE-MANCHES` | 0, aucun audit | 5.5 ; story |

**Tout ne tient pas** (`awk`, arbitrage et marge comptes partout) : A 4,5 a 5,0 avec trois lots, A' 2,75 a 3,0, B 1,75 ; **somme
9,0 a 9,75 pour une borne de 8,0**. La composition est donc un ordre ; la section 7 donne le mien. Je ne compose pas.

**La mesure de la forme commune et la cote de reference de `Q-R4` ne sont pas des postes de ma capacite** : `ines-design` et un
developpeur les produisent ; je les lis dans le rejeu de `TPH-CE1-R1`.

---

## 6. Criteres de Done pedagogique, par lot candidat

### 6.1 Communs a tous les lots du palier 2

1. `TRV-CE1-1` a `-7`, `LON-CE1-1`, `-2`, `-5` ; `LON-CE1-3` et `-4` pour les lignes a texte support, comptes par la regle de s47.
2. **Lecture complete avant le premier verdict** : une ligne datee *"lecture complete : <paquet>, N items lus"*, N egal au compte,
   dans un commit ancetre de celui du verdict (meme forme qu'au palier 1).
3. **Mes points d'audit deja ecrits**, rien de neuf : la bonne reponse n'est jamais la seule proposition a majuscule (Unicode) ;
   l'intrus du premier mot, compte et juge s'il devient regulier sur une question (fiche de `texte-narratif-ce1`, section 3) ; un
   mot cite dans un indice ou un enonce est entre chevrons (`reponse-nadia-q-palier-1.md` 4.2) ; l'indice dit ou regarder et ne
   fait rien deduire du monde.
4. **Le pli** : a 744 x 551, apres une erreur, un indice sous le pli est une reserve non bloquante, nommee et datee (precedent de
   `RDO-CP-R1`). Voir la section 9 pour ce que le depot ne porte pas.
5. **Exactitude** : chaque bonne reponse et chaque distracteur, relus a l'ecran. Une erreur de fait est un refus a elle seule.

### 6.2 Par lot

| Lot | Ce qui decide le Done, en plus de 6.1 | Ce qui serait un refus |
|---|---|---|
| `-05`, `ce1-pronoms` | `PRN-CE1-1` a `-3` ; la frontiere avec `-04` : la question cite le pronom, sinon c'est une question de personnage | un pronom cite deux fois dans le texte, ou un `il` impersonnel pris pour cible (`PRN-CE1-3`) |
| `-06`, `ce1-inference` | `INF-CE1-1` a `-3` ; la frontiere avec `-04` : la bonne reponse n'est pas une suite de mots du texte (`INF-CE1-2`) | une chaine de deductions, ou une bonne reponse ecrite telle quelle dans le texte |
| `-07`, `ce1-texte-documentaire` | `DOC-CE1-1` a `-3` ; **je joue chaque item texte masque** : un item auquel un enfant repond sans lire mesure sa culture | un fait faux (`DOC-CE1-3`) ; une bonne reponse seule presente dans le texte (`DOC-CE1-2`) |
| `-11`, `ce1-negation` | `NEG-CE1-1` a `-3` ; **`TRV-CE1-2` recompte sur les champs** : la bonne reponse strictement la plus longue dans la moitie des items au plus, biais que la negation porte par construction | une negation hors `ne... pas` ou hors du present ; le biais de longueur depasse |
| `-24`, `ce1-familles-de-mots` | `FAM-CE1-1` a `-3` ; **l'item *livre* corrige** (`S57-FRA-FAMILLES-LIVRE`, `grep` a 0) ; *prefixe*, *suffixe*, *radical* absents de ce que l'enfant lit (`TRV-CE1-4`) | *librairie* encore attendu, ou une autre famille savante (motif deja ecrit : `arbitrage-nadia-palier-1.md` 6.3) |

### 6.3 Rejeux : la levee est celle de la fiche, inchangee

| Reserve | Ce qui la leve |
|---|---|
| `TPH-CE1-R1`, `GNA-CE1-R1`, `VSJ-CE1-R2`, `RCT-CE1-R1` | a 744 x 551, apres une erreur, le bas de `.indice` au-dessus de y = 551 sur tous les items du paquet ; la revelation n'entre pas dans la levee (`Q-R2`) ; pour la forme commune, la clause `Q-R4` tenue sur la cote de reference ecrite avant le rejeu, base au sens de `Q-R5`, 65 enonces d'`additions-cp` (`reponse-nadia-q8.md`) |
| `RDO-CP-R3` | fiche, section 6 : a 720, 744 et 768 x 551, a la revelation, sur les six items, avec et sans barre, aucun mot de `.reponse-revelee` ne passe le bord droit de sa boite et `scrollWidth` egale `clientWidth` |

---

## 7. Ordre de priorite pedagogique parmi les restes du palier 1

1. **La mesure d'`ines-design` de la forme commune sans `L1` ni `L2`, avec la cote de reference de `Q-R4` sur le meme jar de base**
   (65 enonces d'`additions-cp` par script, `comprehension-cm1`, les items deja au-dessus du pli). Motif : elle decide la voie de
   quatre reserves, et une cote de reference prise apres la forme ne prouve plus rien. Aucun prix pour moi.
2. **`TPH-CE1-R1`, avec `GNA-CE1-R1` sur le meme jar.** Sur `ce1-phrases-types`, l'indice est entierement sous le pli sur 21 items
   sur 24 : c'est l'enfant bloque qui ne voit pas l'aide. `GNA-CE1-R1` est plus leger qu'il n'en a l'air (22 items ou seul le
   cadre est coupe de 17 px, un seul a 99 px) : il suit, il ne precede pas.
3. **`RCT-CE1-R1`.** Neuf items sur vingt, dont deux (Ibrahim, Chloé) ou l'enfant ne voit ni le verdict ni l'indice. Par la forme
   si elle gagne 133 px, sinon par les textes, qui n'attendent pas la mesure.
4. **`RDO-CP-R3`.** Un seul jeu, et seulement entre 720 et 768 de large ; mais a 720 un enfant de CP lit *"qui va ave"*.
5. **`VSJ-CE1-R2`.** Deux items, 31 px.
6. **`N1` et `N2`.** Recommandations, non bloquantes ; leur prise est un choix de produit.
7. **`S57-TYP1-TEXTES-DE-GABARIT`**, prix 0. *"Score :"* est lu a chaque manche, mais personne n'a vu le deux-points passer seul
   a la ligne sur la cible.
8. **`S57-MESSAGE-PARTIE-PERDUE`**, prix 0 ; la mesure du nombre de lignes a 744 et a 1200 d'abord.
9. **`D5`.** Aucun effet sur ce que l'enfant lit ; hors `PAR`.

**Entre ces restes et les lots neufs** : je prefere que la forme commune soit fusionnee avant l'audit des lots a texte support
(`-05`, `-06`, `-07`), pour qu'ils soient juges sur l'ecran que l'enfant aura. **C'est une preference, pas une condition** : sous
le regime du pli (6.1, point 4), un lot audite avant portera une reserve de la famille de `RCT-CE1-R1`. Parmi les lots de X, mon
ordre est `-05`, `-06`, `-07`, `-11` : prelever (`-04`, servi), puis suivre une reprise, puis deduire ; `-07` porte le risque de
culture ; rien ne depend de `-11`.

---

## 8. Ce qui n'entre pas dans le palier 2

1. **`-08` et `-09`**, a aucun palier de s57 (4.2, condition 4).
2. **Un lot qui change de groupe** (4.2, condition 1).
3. **Le lot `-24` avant que l'item *livre* soit corrige** dans le commit que je jouerai : ce serait un refus ecrit d'avance.
4. **Un rejeu de la forme commune** (`TPH`, `GNA`, et `RCT` ou `VSJ` par la forme) **avant la mesure d'`ines-design` et avant que
   la cote de reference de `Q-R4` soit ecrite dans un fichier date** (`reponse-nadia-forme2-palier-1.md` 5.1 ; `Q-R5`).
5. **`L1` ou `L2` accordes d'avance** a tous les jeux (`Q-T5`).
6. **Un lot sur un terrain neuf** tant que A' existe (`PAR-13`, condition 5) : `ART-CE1-01`, `ART-CE1-02`, `HDA-CM1-06` et toute ligne
   sans paquet ni cousine. Le verdict d'`ART-CP-10` rend possible l'instruction d'`ART-CE1-02` et d'`HDA-CM1-06`, qui est a moi ;
   une ligne non instruite n'est pas un lot.
7. **Un chiffre au-dela de 8,0**, ou un prix pris sur les marges du palier 1.
8. **Une story a prix 0 comptee comme un lot** : `S57-MESSAGE-PARTIE-PERDUE`, `S57-TYP1-TEXTES-DE-GABARIT`, `D5`,
   `S57-GARDE-NOMBRE-DE-MANCHES` ne prennent ni une place de lot ni une part des 8,0.
9. **Un lot dont la story n'existe pas** : aucune des cinq lignes candidates n'en a une aujourd'hui (section 0).

---

## 9. Lacunes : ce que je ne comble pas

| Point | Ce que le depot porte | Ce qui manque | Ce qui en depend |
|---|---|---|---|
| `Q-N4` | la notation *"`Q-N4` = non"* (`DEC-0120` A.87, et moi) | **les mots de l'utilisatrice et leur date** | le regime du pli pour les lots du palier 2 repose sur le precedent de `RDO-CP-R1`, ecrit avant la question, pas sur ces mots (`reponse-nadia-q-palier-1.md` 5.3). **Si ses mots disaient autre chose que *"non"***, le regime changerait pour les lots audites apres leur entree dans le depot, pas avant |
| `S2` | *"comme l'utilisatrice l'a demande le 2026-10-12"* (`planning.md`, `RP-ACCUEIL-147`) | ses mots | **aucune valeur de ce document** : `RP-ACCUEIL-147` est fait |
| Le crochet de la racine du scratchpad | rien | qui l'a pose, quand, sur quels mots (`Q4` de `theo-archi` etait routee a l'utilisatrice) | rien de pedagogique ; la lecture de 3.4 |

L'agent lanceur fournit les mots et la date ; je les annote alors. Je ne les reconstruis pas.

---

## 10. Ce dont je reste la seule garde au palier 2

| Propriete | Pourquoi aucun test ne la tient |
|---|---|
| L'isolation, redirections du shell comprises | le crochet ne voit que l'outil d'ecriture de fichier, et il n'est pas dans le depot |
| La reponse d'un item documentaire impossible sans le texte (`-07`) | seul un jeu texte masque le dit |
| Les familles exactes et non savantes (`-24`) | un test voit un mot, pas son etymologie |
| La position de `.indice` et le debordement de `M4` | aucun automate du reacteur ne rend une page |
| L'intrus du premier mot et la majuscule devenus reguliers sur une question | un signe se compte, seule la lecture dit s'il repond a la place de l'enfant |

## 11. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | la composition du palier 2 sous les sections 5, 7 et 8 ; les stories des lots retenus ; `S57-FRA-FAMILLES-LIVRE` avant `-24` |
| `sofia-sm` | l'amendement date de `planning.md` (conditions (c) et (d) de `D2`) ; la table d'isolation du palier 2 et les consignes de lancement, au vu de la section 3 ; les mots de `Q-N4` et de `S2` a obtenir de l'agent lanceur |
| `theo-archi` | un moyen qui voie les ecritures du shell a la racine (3.4) ; `Q-T12` reste a lui |
| `ines-design` | la mesure de la section 7, point 1 ; son port et son repertoire selon la table du palier |
| `yanis-qa` | rien a refaire : le deplacement de 3.3 est rendu ; je l'ecris pour la cloture de s57 |

## 12. Ce que ce document ne fait pas

- Il ne compose pas le palier 2, n'ecrit pas `planning.md`, n'engage aucune ligne, ne designe aucun developpeur.
- Il ne prononce aucun Done, ne joue aucun jeu, ne touche ni la matrice, ni un catalogue, ni un paquet, ni du code.
- Il n'ecrit et n'alloue **aucun identifiant de condition ou de reserve**. `AP1` a `AP9` sont des reperes de ce document.
- Il ne verifie pas s56 pour la condition (ii), et ne derive pas O2.

**Tarif** : aucun prix neuf. Ce document est pris sur les 0,5 d'arbitrage de chacune des trois instances du palier 2.

**Signe** : `nadia-prof`, s57, palier 2, instances A, A' et B, 2026-10-15.

## 13. Jobs de `ci.yml` lances pour ce commit

Sur ce worktree, ce fichier dans l'index, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou), ce fichier compris, passe a
  `verifier-chemins.sh` : sort 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; le
  sujet du commit passe dans `verifier-prefixes.sh` : *"Tous les commits portent un prefixe d'agent connu."*, sortie 0. Le controle
  du proprietaire du garde-fou de l'accent n'est pas lance : aucun de ses chemins n'est touche.
- **`build`, partiel** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test` restreint a
  `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` et `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`,
  perimetre `bootstrap` et ses amonts : `BUILD SUCCESS`, 15 tests (5 et 10), 0 echec. Lance parce que ces deux tests lisent
  `squad/` ; aucun identifiant n'est alloue. Le reste du `build` et l'etape *"Package du fat jar"* ne sont pas lances : ce commit
  ne touche ni code ni contenu. Le build a lu ce fichier avant l'ajout de cette section, qui ne contient aucun tableau.
- **`outils`** : non lance ; aucun outil n'est modifie.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.
