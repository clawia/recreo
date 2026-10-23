# s57 - proposition de composition du palier 5, centree sur le CE1 (`camille-po`)

- **Auteure** : `camille-po`, Product Owner, 2026-10-23. Arbre : `round-two` a `f3b06989`, branche `s57-camille-p5-proposition`. **Mise a jour du 2026-10-23, apres les reponses `D1` a `D5` de l'utilisatrice** (premiere version : commit `e18b13e8`).
- **Ce que ce fichier est** : une **proposition**. Ce n'est ni `composition-palier-5.md` (a `sofia-sm`), ni l'arbitrage prealable de `nadia-prof`, ni un amendement de `planning.md`. **Aucun poste n'est engage, aucun prix n'est fixe par moi** : les prix sont ceux du bareme de `nadia-prof` ou des registres, rien d'autre. La mise a jour touche `DEC-0120` (amendement A.180 a A.190), `DEC-0123`, `DEC-0125` et `DEC-index.md` ; elle ne touche ni la matrice (aucun statut ne change), ni le registre de prix (`registre-prix-palier-5` n'existe pas et n'est pas cree), ni code, ni paquet.
- **Demande** : de l'utilisatrice, relayee par l'agent lanceur : un palier 5 centre sur le CE1, pour rapprocher sa couverture de celle du CP (52 / 59 = 88,1 %) et du CM1 (66 / 79 = 83,5 %).
- **Convention de lecture** : `[lu]` = lu au disque ou mesure par une commande ecrite a cote, a `f3b06989`. `[suppose]` = ma lecture, non verifiee, dite comme telle. Ce qui n'est marque ni l'un ni l'autre est un calcul sur des chiffres `[lu]`.
- **Les mots de l'utilisatrice du 2026-10-23** (section 1) sont cites tels que relayes par l'agent lanceur, entre guillemets, avec leur date : forme 2 de `DEC-0062` G. Elles sont **consignees par `DEC-0120` A.180 a A.190** (section 10). Les cinq decisions `D1` a `D5` posees par la premiere version sont **fermees** : leurs mots sont en section 1, la section 9 ne garde que ce qui reste ouvert.

## 1. Ce que l'utilisatrice a tranche le 2026-10-23, et ce que j'en fais

| Point | Ses mots | Lecture et suite | Qui ecrit la suite |
|---|---|---|---|
| `QU2` | "consigner le depassement" | `PAR-8` est depasse en A' au palier 4 (5,50 sur 5,0, `registre-prix-palier-4.txt` [lu]). Forme proposee : le depassement est consigne comme **relevement du plafond de A' au palier 4**, a 5,50 plus le prix du rejeu 2 de `LP6`, **ecrit par `nadia-prof`** (la ligne est `non-chiffre`, 0,00). Je ne chiffre pas a sa place. Quand elle chiffre : la ligne du registre passe a `facture`, un amendement de `DEC-0120` ecrit le relevement avec la valeur | `nadia-prof` (le chiffre), `camille-po` (registre, amendement) |
| `DEC-0125` | "on retire" | `S57-TYP1-TEXTES-DE-GABARIT` et `S57-MESSAGE-PARTIE-PERDUE` restent retirees. `DEC-0125` reste `Acceptee`. Aucune suite | - |
| `QP7` | "on les compose au palier 5" | `LP9`, `LP7`, `LP8` sont composees au palier 5, **sans `DEC` d'ecartement**. Contrepartie ecrite en section 6 : leurs conditions de composition, avec leur proprietaire. **La story de `LP8` est ecrite au backlog avec ce fichier** (annotation datee en fin de `docs/backlog/backlog.md`) | `camille-po` (story), proprietaires de la section 6 |
| Regle apres les trois refus de A' | "on ajoute", puis "Lecture avant verify" | Section 7. Le contenu est celui qu'elle a choisi ; la portee, le declencheur et le compte du prix sont **ma lecture, a confirmer** | `camille-po` (texte), `nadia-prof` (prix de la lecture), `DEC` ensuite |
| Confirmation d'`ArchUnit` du palier 4 | "theo-archi l'ecrit" | `confirmation-archunit-palier-4.md` n'existe pas [lu : `ls` rend 0]. Elle est a `theo-archi`, avant la cloture du palier 5 au plus tard | `theo-archi` |
| `s57` | "pas encore ferme" | s57 reste ouvert. Voir F1 (section 2) : ce que le palier 5 apporte au numerateur | - |
| `QP4` (clavier de `LP1`) | "j'ai teste de mon cote c'est ok" | Consignee comme **reponse de l'utilisatrice** a `QP4`. La story `S57-SAISIE-MOTS-PROPOSES-PAR-LE-CLAVIER` se ferme sur ce test : son critere 3 etait *"l'utilisatrice le verifie sur l'appareil"*. **Reste a `theo-archi`** : rien d'obligatoire. Ses criteres 1 et 2 (une reponse sourcee, un test d'attribut) n'avaient de sens que *"si un attribut existe"* ; sans attribut a garder, ils tombent. Il peut ecrire une ligne qui dit qu'`QP4` est sans objet. **Non connu de moi** : l'appareil, et ce que "ok" constate [suppose : que le mot propose ne gene pas l'epreuve]. A redemander a l'entree de `FRA-CE1-29` (dictee), ou `nadia-prof` dit que le mot propose est probablement le mot dicte | `theo-archi` (une ligne, facultative), `camille-po` (fermeture au backlog) |
| `T5` | "ok" | `S57-GARDE-PAQUET-INTERET-COMMUN` est **engagee**, hors `PAR`, prix 0, **placee avant** `S57-EMC-CE1-04-R1` (la story le dit deja : *"Ordre : avant"*). Developpeuse : `sofia-sm` (auteure du test) ; forme : `theo-archi` ; morsures : `yanis-qa` | `sofia-sm`, `theo-archi`, `yanis-qa` |
| `D1` | "on met la barre a 20" | borne du palier 5 a **20,0** (`PAR-9`). Section 4.4 : elle ne change ni la composition ni le prix d'un lot ; c'est le stock de lots qui limite | `camille-po` (consigne), `nadia-prof` (prix) |
| `D2` | "ok pour faire une rotation" | second dessin de triangle rectangle de `LP7` : on tente la **rotation de l'existant**, sans octet. `theo-archi` est saisi en parallele pour dire si elle suffit. Section 6.3 | `theo-archi` |
| `D3` | "on reste dans s57" | **palier 6 dans s57** ; pas de s58 a la cloture du palier 5. Consequence ecrite en section 5.1 | `sofia-sm` (planning), `camille-po` |
| `D4` | "on prend l'autre option" | la regle *Lecture avant verify* vaut pour **tout paquet ecrit ou reecrit dans un lot** : `LP7` et `LP9` en relevent. Une lecture de plus pour `nadia-prof`, **prix a elle**. Section 7 | `nadia-prof` (lecture, prix) |
| `D5` | "option 3 et 5 lignes" | **rouvrir les dessins** (`DEC-0123` 3) pour cinq lignes de mathematiques CE1 : `MAT-CE1-01`, `-10`, `-11`, `-02`, `-09`. **Sous reserve de l'avis de cout de `theo-archi`**, saisi en parallele : aucun lot de dessins n'est engage avant lui. Section 5.1 | `theo-archi` (avis), `camille-po` |

Toutes ces reponses sont consignees par `DEC-0120` A.180 a A.190. `QU2` : A.186 ; `DEC-0125`, `QP4`, `T5`, confirmation d'`ArchUnit`, "s57 pas encore ferme" : A.187 ; `D1` : A.181 ; `D2` : A.182 ; `D3` : A.183 ; `D4` : A.184 ; `D5` : A.185.

## 2. L'etat, et ce que le palier 5 peut et ne peut pas faire

**Etat de la matrice** [lu : commande de la "Methode du compte" de `MATRICE-couverture.md`, filtree sur CE1] : 68 lignes, `couvert-valide` 38, `en cours` 5, `non couvert` 21, `non couvrable` 4 ; 64 atteignables ; 38 / 64 = **59,4 %**. Compteur de version (`couvert-valide` plus `en cours`) : 43 / 64 = 67,2 %. CP : 52 `couvert-valide` sur 59. CM1 : 66 sur 79.

**F1. Le palier 5 tel que l'utilisatrice l'a decide ne bouge pas le numerateur `couvert-valide`.** Les trois lots qu'elle compose (`LP9`, `LP7`, `LP8`) ont un plafond `en cours` [lu : `DEC-0120` A.32 pour `FRA-CE1-03` et `MAT-CE1-15` ; `arbitrage-nadia-palier-4.md` 8.1 pour `MAT-CE1-14`]. Gain du palier : **0 en `couvert-valide`, +3 au compteur** (43 vers 46 sur 64), si les trois obtiennent leur Done et leur mention de couverture partielle. Ce n'est pas un defaut de composition : c'est ce que le plafond ecrit permet.

**F2. Le plafond "57 / 64" ne se lit pas comme du texte seul.** Je retrouve exactement 57 : 64 moins **7 lignes `non couvert` plafonnees `en cours`** (`MAT-CE1-14`, `-15`, `FRA-CE1-03`, `-27`, `-29`, `QLM-CE1-07`, `-09`). Mais 57 suppose aussi que 8 autres lignes atteignent `couvert-valide`, et **8 exigent un materiau** : les 5 `en cours` actuelles (`MAT-CE1-01` denombrer, `-02` ligne graduee, `-09` regle, `-10` balance et recipient, `-11` horloge : images), `ART-CE1-01` et `-02` (corpus de dessins et de photographies), `QLM-CE1-11` (planisphere ou globe). **Du texte seul, sans materiau neuf, le plafond `couvert-valide` est 49 / 64 = 76,6 %** (38 plus 9 lignes de questionner le monde plus `FRA-CE1-08` et `-09`).

**F3. Ces 11 lignes (49 moins 38) ne s'ouvrent pas dans s57, par des regles de `nadia-prof` que je ne deplace pas** [lu] :
- `FRA-CE1-08`, `-09` : *"ne sont pas des lots de s57, quelle que soit sa duree"* (`ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md` 11.3, condition 4) ; au plus un des deux par sprint, `-09` d'abord.
- Questionner le monde CE1 : *"Aucun lot [...] n'est auditable a s57"* (`arbitrage-nadia-preparation-palier-3-ce1.md` 1.3) : B a audite le rejeu d'`ART-CP-10` dans s57, et sa regle dit qu'un premier lot sans cousine *"ne prend pas le sprint"* ou une autre instance en audite un.
- Arts plastiques CE1, `FRA-CE1-27` : terrains neufs, `PAR-13`, *"pas tant qu'A' existe"* ; au plus une des deux lignes d'arts plastiques par sprint (story).
- **Lecture pour s58 [suppose]** : la regle "au plus un lot sans cousine par sprint" laisse a s58 **une seule famille** parmi questionner le monde, `FRA-CE1-09` puis `-08`, arts plastiques. Je ne sais pas si elle les autorise ensemble ; question a `nadia-prof` avant tout s58. **s58 n'est pas ouvert a la cloture du palier 5** (`D3`, section 5.1).

**Conclusion pour la demande** : pour que le CE1 approche le CM1 (54 / 64 = 84,4 %, soit un point au-dessus de 83,5 %) ou le CP (57 / 64 = 89,1 %, car 56 / 64 = 87,5 % reste sous 88,1 %), **il faut s58 et du materiau**. Le palier 5 apporte du compteur et leve trois reports, pas du `couvert-valide`. **L'utilisatrice a decide de rester dans s57 (`D3`) et de rouvrir cinq lignes de dessins (`D5`)** : dans s57, le plafond du CE1 est 43 / 64 = 67,2 % (section 5.1), sous le CM1 et le CP.

## 3. Par bloc : les lignes, les stories au backlog, l'etat

Stories cherchees par id dans `docs/backlog/backlog.md` : `grep -n "^### .*<id>"`, puis `grep -c -- "<id>"`.

| Bloc | Lignes | Stories qui existent [lu] | Pret ou bloque |
|---|---|---|---|
| B1 Mathematiques `en cours` | `MAT-CE1-01`, `-02`, `-09`, `-10`, `-11` | `S57-BR-MAT-CE1-01` (l. 23108), `S51-MAT-COMPARER-RANGER`, `S39-LONGUEUR`, `S57-BR-MAT-CE1-10`, `S57-BR-MAT-CE1-11` : toutes livrees pour la moitie textuelle. **Aucune story pour la moitie manquante** (l'image) | **Bloque** par le materiau (images) ; `MAT-CE1-02` et `-09` aussi par `DEC-0123` 3 (dessins hors perimetre, condition de retour : une reponse neuve de l'utilisatrice). `MAT-CE1-01`, `-10`, `-11` ne figurent pas dans la liste de `Q-06` : aucune decision ne les couvre [suppose] ; aucun avis de `theo-archi` sur leur dessin [lu : non trouve] |
| B2 Mathematiques `non couvert` | `MAT-CE1-14` quadrillage (`LP9`), `MAT-CE1-15` figures planes (`LP7`) | `S57-BR-MAT-CE1-14-GRILLE` (l. 23298, plus cinq annotations), `S57-BR-MAT-CE1-15` (l. 23563) | `LP9` : **non pret**, conditions d'`A.149` non ecrites (section 6). `LP7` : **non pret**, paquet a reecrire et second triangle rectangle absent (section 6). Plafond `en cours` pour les deux |
| B3 Francais | `FRA-CE1-03` (`LP8`), `-08`, `-09`, `-27`, `-29` | `-08` (l. 23484), `-09` (l. 23469), `-27` (l. 23505), `-29` (l. 23494). **`FRA-CE1-03` : `grep -c` rend 0, aucune story** ; ecrite avec ce fichier | `LP8` : **pret apres la story** (paquet `ce1-mots-irreguliers`, 20 items, ecrit, hors catalogue). `-08`, `-09`, `-29` : bloques jusqu'a s58 ; `-27` : terrain neuf, aucun paquet |
| B4 Questionner le monde | `QLM-CE1-01` a `-12` | **Aucune.** `grep -c "^### .*QLM-CE1"` rend 0 ; `QLM-CE1` apparait 6 fois, toutes dans des amendements de planning ; `QLM-CE1-11` : 0 occurrence. Aucun paquet `ce1-qlm-*` [lu : `ls` du repertoire de contenu] | **Bloque** pour s57 (F3). 9 lignes `couvert-valide` (`-01` a `-06`, `-08`, `-10`, `-12`), `-07` plafond `en cours`, `-09` plafond `en cours` avec un plan dessine, `-11` sans rien tant qu'il n'y a pas de planisphere |
| B5 Arts plastiques | `ART-CE1-01`, `-02` | `S57-ART-CE1-01-ET-02` (l. 23516), non engagee, corpus absent | **Bloque** par le corpus : 12 reproductions, aucun dessin, aucune photographie, aucune paire d'un meme sujet (`nadia-prof`). `-02` ne vaut qu'apres `-01`. Motifs en section 4 |

### 3.1 Les deux lignes d'arts plastiques, leurs motifs [lu : cellules de la matrice, `competences-ce1.md`, story]

- **`ART-CE1-01`** (reconnaitre comment une oeuvre est faite : peinte, dessinee, photographiee). La mecanique existe (une reproduction par item, `ART-CP-09` `couvert-valide`). **Ce qui manque est le corpus** : `nadia-prof` ecrit qu'aucun dessin et qu'aucune photographie ne figurent parmi les douze reproductions de `squad/corpus/oeuvres-domaine-public.yaml`, et qu'une facon de faire decrite dans l'enonce serait une contrefacon (`CON-1`). Aucun plafond `en cours` n'est ecrit. Vigilance d'avance : `ART-1` refuse une paire que seule la couleur separe. Mention due a la fermeture : *"reception d'oeuvres seule ; aucune pratique plastique n'est servie par ce produit"*. Les droits d'un corpus de dessins et de photographies (`ART-3`) : route a `theo-archi`, qui doit aussi dire comment prouver une oeuvre qui est elle-meme une photographie (double preuve d'`ADR-0082`).
- **`ART-CE1-02`** (comparer deux oeuvres d'un meme sujet). La forme a deux oeuvres est `couvert-valide` (`ART-CP-10`, rejeu du 2026-10-15, `RDO-CP-R3` levee le 2026-10-16) : **seul le materiau manque**, au moins trois paires d'un meme sujet fait de deux facons. Aucun plafond `en cours`. La cellule porte encore les mots *"refusee le 2026-10-11"* que son amendement du 2026-10-19 declare perimes.
- **Ce qu'elles coutent** : 1,5 chacune (lot illustre, bareme) **plus un travail de corpus sans prix** (choix des oeuvres par `nadia-prof`, sur le modele de `CHOIX-OEUVRES-s56-art-cp-10.md`, puis la double preuve par fichier). `Q-06` ne s'applique pas : il retire les dessins *produits par le produit*, pas des reproductions d'oeuvres (`arbitrage-nadia-q05-q10.md` 5.2). Terrains neufs : pas dans s57.

## 4. Composition proposee du palier 5 et prix

### 4.1 Prix reellement factures aux paliers 2 a 4 [lu : `registre-prix-palier-*.txt`, `awk -F'|'`]

Le palier 1 n'a pas de fichier de registre [lu : `ls`] ; `cloture-palier-1.md` ecrit 7,25 engages sur 8,0 (A 2,5, A' 3,0, B 1,75).

| Palier | Total du fichier | Lots a 1,0 factures | Surcout de rejeu | Lecture |
|---|---|---|---|---|
| 2 | 8,00 (`facture` 6,25, `marge` 1,50, `nonaffecte` 0,25) | 2 (`FRA-CE1-05`, `FRA-CE1-24`) | rejeu de `FRA-CE1-05` 0,50 ; stories de reserve de 0,25 a 0,50 | une story de reserve a coute 0,25 (`VSJ-CE1-R2`, `GNA-CE1-R1`) ou 0,50 (`RCT-CE1-R1`, `TPH-CE1-R1`) |
| 3 | 8,00 (`facture` 6,50, `marge` 1,50) | 5 | 0,50 (rejeu de `-07`, route par `nadia-prof`, absent du fichier) | 0,10 par lot |
| 4 | 12,50 (`facture` 9,50, `consomme` 1,50, `non-engage` 1,50, `non-chiffre` 0,00) | 6 | **3,50** (1,50 de marge consommee, 2,00 de rejeux factures) plus le rejeu 2 de `LP6` non chiffre | 0,58 par lot : chacun des six lots a ete refuse ou valide sous reserve au premier passage |

- **Fixe par instance et par palier** : arbitrage prealable 0,50 plus marge de rejeu 0,50 = 1,00 (paliers 2 a 4).
- **Aucun lot a 1,5 n'a ete facture aux paliers 2 a 4** [lu] : le seul 1,5 inscrit est `LP9`, `non-engage`. Le prix de 1,5 est le bareme (`arbitrage-nadia-preparation-palier-3-ce1.md` 2), pas une mesure.
- **Instruction ligne par ligne d'un referentiel neuf** (questionner le monde, arts plastiques) : *"aucun prix ecrit"* (`DEC-0120` A.14) [lu]. Elle n'est pas dans les chiffres ci-dessous.

### 4.2 Plafonds `PAR` qui mordent [lu : `ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md` 11.3 et 4.2 ; `DEC-0120` A.176]

| Regle | Ce qu'elle dit | Effet au palier 5 |
|---|---|---|
| `PAR-8` | 5,0 et trois lots au plus par instance ; 8,0 a deux ou trois instances | **Depasse en A' au palier 4 : 5,50** (`QU2`, consigne ; relevement ecrit quand `nadia-prof` chiffre le rejeu 2 de `LP6`, `DEC-0120` A.186). **`D1` ne le modifie pas** : a trois instances, 3 x 5,0 = 15,0 est le plafond que `PAR-8` laisse, sous la borne de 20,0. B depasse 5,0 des que le surcout de rejeu atteint plus de 0,50 par lot (section 4.4) |
| `PAR-9` | la somme des budgets egale la borne | **borne du palier 5 : 20,0**, ses mots *"on met la barre a 20"* (`D1`, 2026-10-23), comme au palier 4 (*"on monte a 20"*, `arbitrage-nadia-palier-4.md` 8) |
| `PAR-2` | une matiere et un niveau ne sont jamais partages | mathematiques CE1 : B seule (`LP7` et `LP9`) ; francais CE1 : A (accord de s57 seul) ; EMC : A' |
| `PAR-13` | un terrain neuf par sprint, pas tant qu'A' existe | aucune instance C au palier 5 : questionner le monde, arts plastiques et `FRA-CE1-27` n'y entrent pas |
| `PAR-16` | une story par poste engage | `LP8` n'en avait pas ; elle est ecrite avec ce fichier |

### 4.3 Composition proposee (au bareme, puis au surcout mesure)

| Instance | Developpeur | Poste | Bareme | Avec surcout de rejeu mesure (0,10 a 0,58 par lot) |
|---|---|---|---|---|
| A, francais CE1 | `marc-dev` | arbitrage 0,5 plus marge 0,5 | 1,00 | 1,00 |
| | | `LP8` `FRA-CE1-03` | 1,00 | 1,10 a 1,58 |
| | | **Total A** (1 lot) | **2,00** | **2,10 a 2,58** |
| B, mathematiques CE1 | `lea-dev` | arbitrage plus marge | 1,00 | 1,00 |
| | | `LP7` `MAT-CE1-15` | 1,50 | 1,60 a 2,08 |
| | | `LP9` `MAT-CE1-14` | 1,50 | 1,60 a 2,08 |
| | | **Total B** (2 lots) | **4,00** | **4,20 a 5,16** |
| A', EMC | `sofia-sm` | arbitrage plus marge | 1,00 | 1,00 |
| | | `S57-EMC-CE1-04-R1`, `S57-EMC-CE1-02-R1`, `S57-EMC-CE1-03-R1` : prix a `nadia-prof`, 0,25 a 0,50 chacune (precedents de la ligne 2) | 0,75 a 1,50 | 0,75 a 1,50 |
| | | **Total A'** | **1,75 a 2,50** | **1,75 a 2,50** |
| C | aucun | n'ouvre pas | 0 | 0 |
| hors `PAR`, prix 0 | | `S57-GARDE-PAQUET-INTERET-COMMUN` (engagee), `S57-PLI-LP3-LP5-MESURE` (`ines-design`), `S57-GARDE-CE1-PROPOSITIONS-ET-SUPPORTS` (apres la ligne de `nadia-prof` sur `CA4`) | 0 | 0 |
| **Palier** | | | **7,75 a 8,50** | **8,05 a 10,24** |

- **Developpeurs** : ceux de `QP6` (*"lea dev pour B et sofia pour A'"*, 2026-10-21) et de `RG1` pour A [lu].
- **Trois lots au plus par instance (`PAR-8`)** : A 1, B 2. A' : trois stories de reserve. **Je ne sais pas si `nadia-prof` les compte comme des lots** [suppose : non, ce sont des corrections de paquets deja au catalogue, mais elle a compte les rejeux]. A dire par elle.
- **Ordre propose** (celui de `DEC-0120` A.35 : mathematiques, francais, puis EMC ; l'ordre de fusion est a `sofia-sm`). Si `theo-archi` juge la rotation recevable (`D2`), `LP7` n'installe aucun octet et la regle d'ordre sur `POIDS_TOTAL_MAXIMUM` ne la concerne plus. A' : `S57-GARDE-PAQUET-INTERET-COMMUN` d'abord, puis `S57-EMC-CE1-04-R1`, puis `-02-R1` et `-03-R1`. B : `LP7` puis `LP9` ; **si les deux installent des octets de dessin, `LP9` fusionne en premier** (`DEC-0120` A.149, regle d'ordre sur `POIDS_TOTAL_MAXIMUM`). A : `LP8`. Il y a **un seul lot illustre par palier** dans la composition de `nadia-prof` du palier 4 (8.4, tableau 3) : **je ne sais pas si sa limite tient au palier 5** : question a `nadia-prof`, dans son arbitrage prealable (`D2` ecarte la rotation sans octet pour `LP7`, il ne repond pas a cette question).
- **Le prix du rejeu 2 de `LP6` n'entre pas dans ce tableau** : c'est le palier 4.

### 4.4 Avec la borne de 20,0 (`D1`, *"on met la barre a 20"*)

- **Composition** : inchangee. A : `LP8`. B : `LP7`, `LP9`. A' : trois stories de reserve. C n'ouvre pas (`PAR-13`). Aucun lot n'est ajoute pour remplir la borne.
- **Total du palier** : inchange, 7,75 a 8,50 au bareme, 8,05 a 10,24 au surcout mesure. Il reste sous la borne de 11,50 a 12,25 au bareme et de 9,76 a 11,95 au surcout mesure (calcul sur les totaux de 4.3).
- **Prix par lot** : inchange. Le bareme (1,0 textuel, 1,5 illustre) et le surcout de rejeu sont ceux de `nadia-prof` ; une borne n'est pas un prix. Ce que la borne retire : le risque que la somme du palier depasse son plafond (a 8,0, le haut de la fourchette, 10,24, aurait depasse). **Ce qu'elle ne retire pas : `PAR-8` par instance.** Au surcout `s` par lot : B = 4,00 plus 2 fois `s`, donc 5,0 a `s` = 0,50 et 5,16 a 0,58 ; A = 2,00 plus `s` ; A' = 1,75 a 2,50 plus ses rejeux. A trois instances, 3 x 5,0 = 15,0 : la borne de 20,0 n'est pas atteignable par les plafonds d'instance.
- **Ce que la borne ne change pas : c'est le stock de lots qui limite.** Les lots engageables au palier 5 sont `LP8`, `LP7`, `LP9` et les trois stories de reserve d'A' ; rien d'autre n'entre (F3). Les lignes de `D5` attendent l'avis de `theo-archi`, et elles iraient dans B, deja a deux lots : un troisieme lot illustre porterait B a 1,00 plus 3 x 1,5 = 5,50 au bareme, avant tout surcout, donc au-dessus de `PAR-8` des l'inscription. Je ne l'inscris pas.

## 5. Le gain de couverture et la courbe cumulee

Denominateur : 64. Chaque ligne du tableau ajoute le lot indique a la ligne precedente. **Prix au bareme, hors surcout de rejeu et hors instruction** ; un lot qui ouvre un terrain neuf ajoute 1,00 de fixe d'instance par palier. Les lignes sous "s58" sont ce qu'un s58 ouvrirait : **aucun s58 n'est ouvert** (`D3`).

| Etape | Lots | `couvert-valide` | cv / 64 | Materiau neuf ? | Prix de l'etape |
|---|---|---|---|---|---|
| Aujourd'hui | - | 38 | 59,4 % | - | - |
| **Palier 5 (decide)** | `LP8`, `LP7`, `LP9` | 38 | 59,4 % | non pour `LP8` ; `LP7` : rotation de l'existant (`D2`), sans octet si `theo-archi` la juge suffisante ; `LP9` : 20 dessins de grille | 4,00 (lots) |
| **Palier 6 (`D3`, `D5`), dans s57** | `MAT-CE1-01`, `-10`, `-11`, `-02`, `-09`, **sous reserve** de l'avis de `theo-archi` et de la levee de chaque plafond par `nadia-prof` | 43 au plus | 67,2 % | **oui** : dessins | 1,5 par lot [suppose : bareme du lot illustre], 7,50 pour cinq, non engage |
| s58 (non ouvert), `QLM-CE1-01` a `-03` | | 46 | 71,9 % | non | 3,00 |
| s58, `QLM-CE1-04` a `-06` | | 49 | 76,6 % | non | 3,00 |
| s58, `QLM-CE1-08`, `-10`, `-12` | | 52 | 81,3 % | non | 3,00 |
| s58, `QLM-CE1-07` | moitie textuelle, plafond `en cours` | 52 | 81,3 % | non | 1,00 |
| s58 ou plus tard, `FRA-CE1-09` | | 53 | 82,8 % | non | 1,00 |
| idem, `FRA-CE1-08` | | **54** | **84,4 %** (**CM1 depasse**) | non | 1,00 |
| Corpus, `ART-CE1-01`, `-02` | | 56 | 87,5 % | **oui** : dessins, photographies, paires | 3,00 plus corpus non chiffre |
| Planisphere, `QLM-CE1-11` | | **57** | **89,1 %** (**CP depasse**) | **oui** : planisphere ou globe, **hors perimetre** par `DEC-0123` 3 | 1,00 [suppose : plafond `couvert-valide`, aucun plafond n'est ecrit] |

- **Compteur de version** (`couvert-valide` plus `en cours`) : 43 / 64 aujourd'hui, 46 / 64 = 71,9 % apres le palier 5 si les trois lots obtiennent leur Done. Le palier 6 ne le change pas : les cinq lignes de `D5` comptent deja `en cours` ; en lever une ajoute 1 au numerateur `couvert-valide` seulement.
- **Sans materiau neuf, de 38 a 49** : les 11 lots d'un s58. **Avec les cinq lignes de `D5`**, de 49 a 54 (ou de 38 a 43 dans s57 seul). Lever un plafond `en cours` est le jugement de `nadia-prof`, pas le mien : une image ne suffit pas a elle seule.
- **Prix d'un s58** : les 3 paliers de questionner le monde tiennent a **une seule instance** (`PAR-2`, `PAR-8`). A 1,0 par lot au bareme : 3,00 de lots plus 1,00 de fixe = 4,00 par palier ; au surcout du palier 4 (0,58 par lot), 5,74, au-dessus de 5,0. **Aucune instance de questionner le monde n'a de developpeur designe** (`RG2` demande un developpeur distinct) [lu].

### 5.1 Palier 6 dans s57 (`D3`, `D5`)

- **Ses mots.** `D3` : *"on reste dans s57"*. `D5` : *"option 3 et 5 lignes"*.
- **La consequence, dite avant sa reponse et maintenue.** Aucune ligne `couvert-valide` n'est possible dans s57 sans la levee des regles de `nadia-prof` (F3 : `FRA-CE1-08` et `-09`, questionner le monde, arts plastiques) ou sans le materiau de `D5`. Elle a refuse d'avance de lever ses regles (*"relever ma barre d'un cote et la baisser de l'autre"*) et l'utilisatrice ne le lui demande pas. **Le palier 6 ne peut donc faire bouger le numerateur que par `D5`.**
- **Contenu du palier 6 : a composer a partir de `D5` et des corrections. Je ne l'invente pas.** Connu aujourd'hui : (i) les cinq lignes de `D5` : aucune story n'existe pour leur moitie manquante (l'image) [lu, section 3, B1] ; elles s'ecrivent apres l'avis de `theo-archi`, parce que leurs criteres dependent de la forme de chaque dessin ; (ii) les corrections que le palier 5 produira (refus, reserves de `LP7`, `LP8`, `LP9` et des trois stories d'A') et les reports de la section 8 non soldes : ils n'existent pas encore, je ne les enumere pas.
- **Gain** : s57 clos au plus a **43 / 64 = 67,2 %** `couvert-valide`, compteur 46 / 64. Le CM1 est a 83,5 % et le CP a 88,1 % ; le CE1 reste en dessous a la cloture de s57.
- **Ce que `D5` ne fait pas** : lever aucun plafond (`nadia-prof` seule) ; poser le poids des dessins (section 9) ; rouvrir `QLM-CE1-09`, `QLM-CE1-11` ni la moitie dessinee de `QLM-CE1-07`, que `DEC-0123` 3 laisse hors perimetre (`D5` nomme cinq lignes de mathematiques).
- **Trois faits de composition que `D5` rend vrais** : (1) **`PAR-8` et `PAR-2`** : les cinq lignes sont de mathematiques CE1, donc dans B seule ; B porte trois lots au plus et 5,0 ; deux lots illustres coutent 4,00, trois 5,50 au bareme. Cinq lignes ne tiennent pas dans un palier sans depassement consigne ; (2) **`PAR-13`**, cite par `DEC-0123` 3.2 point 1 : *"une seule ligne a dessin par sprint, et pas tant qu'A' existe"*. **Que les cinq lignes y entrent est un jugement de `nadia-prof`**, que je ne rends pas ; si elles y entrent, `D3` et `D5` ne tiennent pas ensemble dans s57 au-dela de la limite qu'elle ecrit (une ligne par sprint dans le texte cite ; aucune si `LP9` compte deja pour cette ligne) ; (3) **un seul lot illustre par palier** (section 4.3) : sa limite au palier 5 et au palier 6 est a dire par elle.

## 6. `LP9`, `LP8`, `LP7` : etat, conditions de composition, ce que demanderait un troisieme ecartement

**Etat** [lu : `DEC-0120` A.177, `registre-prix-palier-4.txt`] : `LP9`, `LP7`, `LP8` sont a leur **deuxieme ecartement** (palier 3, palier 4). `LP9` : 1,50 `non-engage` au registre du palier 4. L'utilisatrice les compose au palier 5 : l'engagement solde les deux reports. **Un troisieme ecartement demanderait une `DEC` avec son motif** (`DEC-0120` A.150) : elle a repondu qu'elle n'en veut pas, ce qui rend les conditions ci-dessous **necessaires avant l'ouverture**, pas apres. Sans elles, un lot compose n'est pas engage, et le non-engagement est un troisieme ecartement de fait.

### 6.1 `LP9` `MAT-CE1-14` : les conditions d'`A.149`, etat au disque

| Proprietaire | Condition | Etat [lu] |
|---|---|---|
| `nadia-prof` | arbitrage prealable : prix (1,5 indicatif) et instance (B) confirmes ; `PAR-13` ecrit pour la forme (a) ; reponse sur *"par sa coordonnee seule"* (les 20 enonces du paquet nomment une coordonnee) ; **taille de grille et nombre d'items** (4 x 4, 5 x 5, 6 x 6 : poids de plus 66 %, 77 %, 88 % ; a 176 px, etiquettes de 14,1 px a 6 x 6, au minimum d'`ines-design`) | **non ecrite** : aucun fichier de palier 5 (`ls ... grep -c "palier-5"` rend 0). Ma recommandation, qui ne tranche pas : pas 6 x 6 tant que la mesure d'`ines-design` n'a pas depart ; la taille est a elle et a moi, apres la mesure (A.151) |
| `ines-design` | avant l'ecriture du paquet : glyphes manquants (A a F, 3, 4, 6), trois marques (objet, depart, arrivee), mesure d'un candidat a 176 px, 744 x 551 et 320 px | **non ecrite** : aucune mesure d'etiquettes de grille [lu : `grep -l -i glyphe` ne trouve que `mesure-ines-palier-3-pli-744x551.md`, autre sujet [suppose]] |
| `theo-archi` | 20 SVG (`quadrillage-a` a `-t`, `viewBox` 100, trait de 1,2, etiquettes tracees, aucun `<text`) ; test de geometrie neuf dans `bootstrap`, taille M, qui croise chaque dessin et son item, avec morsures ; `ADR-0080` non amende ; les 20 hors de `DESSINS_DE_DESIGNATION` | **non ecrite** : 0 fichier `quadrillage*` dans `static/illustrations` [lu : `grep -c quadrillage` sur le listing de `static/illustrations` rend 0] |
| `lea-dev` | constante `POIDS_TOTAL_MAXIMUM` mise a jour dans le commit des dessins, sur la valeur mesuree du commit, avec une ligne de motif | s'ecrit dans le lot |
| `sofia-sm` | composition, `planning.md`, file de fusion | apres cette proposition |
| `camille-po` | paquet reecrit : criteres 10 a 14 de l'amendement de la story (depart marque, designation sans case, etiquettes dessinees, indices *"Dessine le quadrillage"* supprimes : `grep -c "Dessine"` rend 3 aujourd'hui) | story ecrite et amendee [lu] ; le paquet se reecrit dans le lot |

**Reponse de l'utilisatrice, deja ecrite** : *"LP9 oui on peut augmenter le poids des dessins"* (2026-10-21) ; *"QP3 plafond accepté"* [lu : `preparation-palier-4-camille.md`] ; et ce *oui* **ne couvre que les grilles de cette ligne** (`DEC-0120` A.146). Le plafond de la ligne reste `en cours` : mention *"se reperer et se deplacer servis sur un quadrillage montre, reponse au choix sans designation ; 'coder un deplacement' servi du cote reception ; la production d'un code reste non couverte"* (`arbitrage-nadia-palier-4.md` 8.1).

### 6.2 `LP8` `FRA-CE1-03` : la story est ecrite avec ce fichier

- **Etat** [lu] : cousinage juge recevable (cousine `GRC`, `ce1-graphemes-complexes`, `FRA-CE1-01` `couvert-valide`) par `nadia-prof` (`arbitrage-nadia-palier-4.md` 3.1) ; plafond `en cours`, mention *"lecture exacte des mots irreguliers servie ; reconnaissance immediate des mots frequents non servie"* (`ARBITRAGE-COMPLEMENT-s55-instance-a.md` section 3) ; paquet `ce1-mots-irreguliers`, 20 items, 12 mots cibles distincts, ecrit par `lea-dev` hors sprint, **hors catalogue et sans test** (`grep -c "paquet: ce1-mots-irreguliers$"` sur `catalogue.yaml` rend 0 ; `git ls-files` ne nomme aucun `PaquetCe1MotsIrreguliersTest`).
- **Ce qui reste a faire avant l'ouverture** : l'arbitrage prealable de A au palier 5 (`nadia-prof`, 0,5) ; `marc-dev` designe (`RG1`) ; rien d'autre.
- **Verifie contre l'existant** (regle des criteres correctifs) : sur ce paquet, **seuls deux criteres sont rouges aujourd'hui**, l'entree au catalogue et le test du paquet ; les autres sont verts et dits comme tels dans la story. Je n'ai pas de critere rouge a inventer pour eux.

### 6.3 `LP7` `MAT-CE1-15` : ce qui manque

| Proprietaire | Condition | Etat [lu] |
|---|---|---|
| `camille-po` | la story porte K1 a K10, `FIG-CE1-1` a `-3` et le plafond `en cours` | ecrite. **Une valeur est fausse** : elle ecrit *"18 dessins `figure-*`"* ; `ls ... grep -c "^figure-"` rend **12**. Corrigee dans l'annotation du backlog, avec le critere du second triangle rectangle |
| `nadia-prof` | **condition neuve du 2026-10-21** (`arbitrage-nadia-palier-4.md` 3.2) : le triangle rectangle est montre dans au moins deux dessins qui different par l'orientation ou les proportions | **non tenue** : `grep -c figure-triangle-rectangle` sur le listing de `static/illustrations` rend 1 |
| `theo-archi` | un second dessin de triangle rectangle, ou la rotation de l'existant ; si le lot exige un dessin neuf, *"le lot s'arrete"* (story) | **saisi en parallele le 2026-10-23** pour dire si la rotation suffit (`D2`) ; avis non rendu a la date de cette mise a jour |
| utilisatrice | un dessin neuf ajoute des octets : le *oui* de `LP9` **ne couvre pas** un lot qui n'est pas `LP9` (`DEC-0120` A.146, point 3 ; A.149 regle d'ordre, point 3) | **repondu** (`D2`) : *"ok pour faire une rotation"*, sans octet. **Ne vaut pas** pour un fichier neuf : si `theo-archi` juge la rotation insuffisante, la question du fichier neuf se repose (section 9) |
| `nadia-prof` | arbitrage du palier 5 : prix (1,5), instance B, **et si deux lots illustres tiennent dans un palier** (8.4 tableau 3 : *"un seul"*) | **non ecrit** |
| `lea-dev` | reecrire le paquet avec ses figures : `grep -cE '^\s+illustration:'` rend 0 aujourd'hui (rouge), au moins 20 apres ; l'en-tete `DEGRADE` reecrit | dans le lot |

## 7. Regle de composition : lecture avant verify

**Ses mots, 2026-10-23, relayes** : *"Lecture avant verify"* : pour un lot de A' sur un paquet neuf, `nadia-prof` lit le premier jet du paquet avant le verify, et le rejeu coute alors dans le lot. **Consignee par `DEC-0120` A.184.** **Portee : tranchee par `D4`** (*"on prend l'autre option"*). Les alineas ci-dessous sont **ma redaction ; le declencheur, l'ecrit et le compte du prix restent ma lecture**, a confirmer.

1. **Portee (`D4`).** **Tout paquet ecrit ou reecrit dans un lot**, toutes instances. Au palier 5 : **`LP7`** (paquet reecrit avec ses figures) et **`LP9`** (paquet neuf avec 20 dessins de grille) en relevent : **deux lectures de `nadia-prof`**, toutes deux en B. Son prix est a elle ; la regle ne l'a pas fixe. **Hors portee, ma lecture a confirmer par `nadia-prof`** : `LP8` (le paquet `ce1-mots-irreguliers` a ete ecrit hors sprint, le lot ne fait que le brancher) et les trois stories de reserve d'A' (elles corrigent des items de paquets deja au catalogue, ce qu'elle relit deja *"sans rejeu complet"*, `DEC-0120` A.173). **Condition sur le premier jet** : les items de `LP7` et de `LP9` nomment des dessins (`illustration:`) ; le premier jet a lire est donc le paquet **avec** ses dessins, et la lecture ne peut avoir lieu qu'une fois ceux-ci sur la branche.
2. **Declencheur.** Le developpeur du lot (pour A', `sofia-sm` ; pour B, `lea-dev`) commite le premier jet complet du paquet sur sa branche et l'ecrit dans `planning.md` (une ligne : lot, commit, heure) **avant de lancer un `verify` complet**. Pas de `verify` complet sur ce paquet avant la lecture.
3. **Ce que `nadia-prof` produit.** Un ecrit date dans le dossier du sprint, `lecture-nadia-premier-jet-<paquet>.md` : les items qu'elle refuserait et leur motif, **sans verdict**. Ce n'est pas un Done : le Done reste prononce sur le commit joue, apres le `verify` (`CLAUDE.md`, double Done). Aucune fiche de conformite n'est modifiee par cet ecrit.
4. **Ce que le developpeur fait.** Il corrige les items nommes, puis lance son `verify`. La correction est faite avant le premier `verify`, pas apres un refus.
5. **Le prix.** La lecture et la correction qui en suit sont **comprises dans le prix du lot** (1,0 au bareme ; 1,5 pour `LP7` et `LP9`), **pas sur la marge de rejeu, pas en ligne de registre separee**. Ses mots : *"le rejeu coute alors dans le lot"*. **Lecture [suppose]** : "le rejeu" designe la relecture apres le premier jet ; un refus prononce **apres** le `verify` se facture comme avant (marge, puis ligne de rejeu). **Si elle voulait dire autrement, le prix du lot change et c'est `nadia-prof` qui le chiffre**, pas moi.
6. **Ce que la regle ne change pas.** Le plafond de chaque ligne, les conditions de Done, l'audit sur le commit joue, la marge de 0,5 par instance.
7. **Condition de composition de `LP6`, ancienne** : *"aucune regle neuve sans eux"* (`P9` de `arbitrage-camille-cloture-palier-4.md`) : les verdicts de `nadia-prof` et de l'utilisatrice precedent l'ajout. L'utilisatrice a repondu *"on ajoute"* ; la regle ci-dessus est son choix.

## 8. Reserves et dettes reportees par la cloture du palier 4 qui se melent au palier 5

Source : `arbitrage-camille-cloture-palier-4.md`, `cloture-palier-4.md` section 5, backlog (annotation du 2026-10-23). **Report 1** = reporte une fois au palier 4 ; un report au palier 5 est le deuxieme ; **le troisieme demande une `DEC`**.

| Poste | Etat | Effet au palier 5 |
|---|---|---|
| `S57-GARDE-PAQUET-INTERET-COMMUN` | **engagee** (`T5`, "ok"), hors `PAR`, prix 0, report 1 solde | avant `S57-EMC-CE1-04-R1` ; porte aussi `G5`, `G11`, `LG5`, `LG11` et le message des sondes 46 a 49 (criteres 2 a 4 de la story) |
| `S57-EMC-CE1-04-R1` | report 1 ; reserves 6 et 7 de `LP6` ; prix a `nadia-prof` | dans la composition d'A' (section 4.3) ; elle relit les items touches et le compte de la regle des mots, sans rejeu complet |
| `S57-EMC-CE1-02-R1`, `S57-EMC-CE1-03-R1` | report 1 | dans la composition d'A' |
| `S57-PLI-LP3-LP5-MESURE` | report 1, hors `PAR`, prix 0 | `ines-design` mesure le pli ; `nadia-prof` lit. Aucun lot ne l'exige |
| `S57-PLI-MOTS-INVARIABLES-REVELATION` | report 1 | **se ferme sans suite** si `nadia-prof`, apres la mesure d'`ines-design` a `f8d5d875`, leve la reserve ; sinon elle entre en composition |
| `S57-SAISIE-MOTS-PROPOSES-PAR-LE-CLAVIER` | report 1 | **fermee** sur la reponse de l'utilisatrice (section 1) |
| `S57-GARDE-CE1-PROPOSITIONS-ET-SUPPORTS` (ecart `E3`) | report 1 | **bloquee par une ligne de `nadia-prof`** : `Q-X1` (`reponses-nadia-questions-stories-palier-3.md`) vaut-elle `CA4` ? **Pese sur s58** : sans garde generique `F1`, chaque paquet de questionner le monde (9 a 10) porte son propre test (`F3` de `note-sofia-garde-ce1-e3.md`) ; recommandation : la ligne de `nadia-prof` et la garde avant le premier paquet de questionner le monde |
| Commentaires de `src/` (ecarts 33, 40 a 44, part code de 37 et 38) | **0 titre au backlog**, premier report a ma charge | **non ecrite ici, non lue ici** : hors de la demande. A ecrire avant la composition ; je la signale comme dette |
| `confirmation-archunit-palier-4.md` | absente | `theo-archi` (section 1) |
| Ecart 24 juges (`yanis-qa`) contre 13 (job `outils`) | non explique | `yanis-qa` |
| Rejeu 2 de `LP6` | `non-chiffre` | `nadia-prof` ; condition du relevement de `PAR-8` en A' (`QU2`, consigne par `DEC-0120` A.186) : je ne chiffre pas a sa place |
| Aucun fichier de palier 5 de `nadia-prof` | absent | arbitrage prealable du palier 5, par instance (0,5 chacune), avant le premier lot ; **il porte la cloture du palier 4** (condition 3 de 11.3) |

## 9. Ce qui reste a l'utilisatrice

`D1` a `D5`, `QU2`, `DEC-0125`, `QP7`, la regle de `LP6`, `QP4`, `T5` et "s57 pas encore ferme" sont **fermes** par ses reponses du 2026-10-23 (section 1). **Je ne pose aucune question aujourd'hui.** Trois questions existent, chacune avec son declencheur ecrit, parce que chacune depend d'un chiffre qui n'est pas ecrit :

1. **Un fichier neuf pour `LP7`.** Declencheur : `theo-archi` juge la rotation insuffisante. *"ok pour faire une rotation"* ne vaut pas oui pour un fichier neuf (les octets d'un second dessin, une hausse de `POIDS_TOTAL_MAXIMUM`, un conflit de constante avec `LP9`) ; je ne l'etends pas.
2. **Le poids des dessins de `D5`.** Declencheur : l'avis de `theo-archi` chiffre des octets qui portent le total au-dela de 161 863, borne du *oui* de `DEC-0123` (septieme amendement), apres les 20 dessins de `LP9`. Sa reponse a `D5` ne cite pas le poids.
3. **Le nombre de paliers de s57 apres le palier 5, et la borne du palier 6.** *"On reste dans s57"* ne dit pas combien de paliers ; je n'ecris ni *"un seul"* ni *"autant que necessaire"*. Declencheur : la composition du palier 6, quand l'avis de `theo-archi` et les prix de `nadia-prof` disent combien de lignes de `D5` tiennent dans B (section 5.1, premier fait) et si `PAR-13` les laisse passer. Aucune borne n'est posee pour le palier 6.

## 10. Ce que ce fichier ne fait pas, et ce qui reste a ecrire

- **Les reponses du 2026-10-23 sont consignees** : `DEC-0120` A.180 a A.190 (`QU2`, `DEC-0125`, `QP7`, regle de lecture, `QP4`, `T5`, s57, `D1` a `D5`), amendements de `DEC-0123` (huitieme) et de `DEC-0125` (premier), ligne de `DEC-index.md`. Le relevement de `PAR-8` en A' (`QU2`) est consigne sans valeur finale : elle s'ecrit quand `nadia-prof` chiffre le rejeu 2 de `LP6`.
- **Aucun prix n'est fixe**, aucun poste engage, aucune ligne de matrice ni de registre modifiee. **Aucun lot de dessins n'est engage** (`D5`) avant l'avis de `theo-archi`, et aucune story n'est ecrite pour les cinq lignes d'images : elles suivent cet avis.
- **Ce fichier est avec une annotation de backlog** : la story de `LP8`, la correction de `LP7`, la fermeture de `S57-SAISIE-MOTS-PROPOSES-PAR-LE-CLAVIER` et l'engagement de `S57-GARDE-PAQUET-INTERET-COMMUN`.
- **A ecrire par d'autres avant la composition** : l'arbitrage prealable du palier 5 de `nadia-prof` (par instance, avec le chiffre du rejeu 2 de `LP6` et sa ligne sur `CA4`) ; la mesure d'`ines-design` sur les etiquettes de grille ; le complement de `theo-archi` (20 SVG, test croise, second triangle rectangle) ; `confirmation-archunit-palier-4.md`.

## 11. Verifications lancees

Voir le message de livraison de cette mise a jour : tables scannees cellule par cellule, classes de `bootstrap` qui jugent `squad/` et `docs/backlog` lancees en `-pl bootstrap -am`, `prefixes` et `chemins`. Je ne declare pas la porte technique : le `build` complet n'est pas lance.
