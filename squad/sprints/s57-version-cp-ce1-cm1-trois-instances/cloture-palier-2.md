# [Sprint s57] Cloture du palier 2

- **Statut : palier 2 clos. Le sprint s57 n'est pas clos.** Le palier 3 n'est pas ouvert (section 14). Ce fichier est ecrit une seule fois, comme `cloture-palier-1.md` (`squad/README.md`, section 4). **Aucun Done n'est prononce par `sofia-sm`** : le pedagogique est a `nadia-prof` (section 1), le technique est juge par la CI, que personne n'a lue (section 12).
- **Horodatage de debut du palier** : 2026-10-15 20:56 (UTC+02:00), amendement d'ouverture de `planning.md` (heure inscrite avant l'ouverture des worktrees ; fusionne par `f6134910` a 21:10).
- **Horodatage de fin du palier** : 2026-10-16 22:07 (UTC+02:00), lu par `date` a l'ecriture. **Duree effective : 25 h 11 min**, sous le seuil d'alerte de 41 h 38 min (`P5'`, `D4` de `planning.md`). Le palier 1 avait dure 25 h 52 min.
- **Ecrit par** : `sofia-sm`, casquette Scrum Master. Arbre : `round-two` a `efb8d013` (cinq fusions de cloture, section 3), checkout principal. Lignee des cinq branches : `juger_lignee` de `outils/verifier-lignee.sh`, appelee avec le nom de la branche et le code de `git merge-base round-two <branche>` : cinq sorties 0, `merge-base` egal a `bb322938` pour les cinq. Je n'ai pas lance l'enveloppe du script : elle juge le worktree courant, pas la branche a fusionner.
- **Ce que "clos" veut dire ici** : chaque poste engage a un verdict ecrit de `nadia-prof` ou un report ecrit (section 1) ; les totaux sont recomptes sur l'arbre fusionne (sections 3 a 5) ; chaque job de `ci.yml` est nomme, lance ou non lance, jamais deduit (section 12) ; les ecarts du releve de cloture sont traites ou routes avec leur destinataire (section 9). **Quatre decisions sont a l'utilisatrice et ne sont pas prises** (section 13). Aucune ne conditionne un verdict.

## 1. Verdict de chaque poste engage

Les verdicts sont ceux de `nadia-prof`, relayes sans etre juges. Les postes hors `PAR` n'ont pas de verdict pedagogique.

| Poste | Instance | Verdict de `nadia-prof`, ou etat | Fichier | Fusion du code, puis du verdict |
|---|---|---|---|---|
| p2-1, arbitrage et lecture complete | A, A', B | livre, 0,5 par instance | `arbitrage-nadia-palier-2.md` | `a677002d` (arbitrage), `f3b5e4cb` et `4004fab7` (lecture) |
| p2-2, marges de rejeu | A, A', B | **non entamees** : nommees, allouees a aucun poste (0,5 par instance, 1,5 au total) | aucun | sans objet |
| p2-3, lot `-05`, `ce1-pronoms` | A | **REFUSE** le 2026-10-16 16:18 (`TRV-CE1-6`, roles distribues par genre), corrige (`42071c23`), rejoue : **VALIDE**, sans reserve. `FRA-CE1-05` | `rejeu-nadia-palier-2-a-lot05.md`, `rejeu-nadia-palier-2-a-lot05-rejeu.md`, `FICHE-bandit-manchot-pronoms-ce1.md` | lot `edcd3de9`, correction `02b4a34e`, refus `b2f6da08`, rejeu `ae116186` |
| p2-4, lot `-24`, `ce1-familles-de-mots` | A' | **VALIDE**, sans reserve, au premier passage. `FRA-CE1-24` | `rejeu-nadia-palier-2-ap-lot24.md`, `FICHE-bandit-manchot-familles-de-mots-ce1.md` | `9f110983`, verdict `9c42046b` |
| p2-5, `N1` et `N2` | B | prises en compte ; `SCI-CM1-20` **reste `couvert-valide`**, non rouverte | `rejeu-nadia-palier-2-b.md`, `FICHE-bandit-manchot-sciences-extraire-information-cm1.md` | `9cbf3571`, verdict `9aa51463` |
| p2-6, script d'`additions-cp` | transverse | fusionne, hors `PAR`, pas de verdict | `rapport-marc-dev-palier-2.md` | `97a205e8` |
| p2-7, forme commune des propositions longues | A' | fusionnee, hors `PAR` ; **juge au rejeu de p2-8** (`PA4` tenue), pas d'audit propre | `rejeu-nadia-palier-2-ap.md` 2.4 | `aca0d90f` (code `dc6f423d`, tete `78fac33d`) |
| p2-8, `TPH-CE1-R1` | A' | **levee** ; `FRA-CE1-10` **VALIDE** | `rejeu-nadia-palier-2-ap.md`, `FICHE-bandit-manchot-phrases-types-ce1.md` | verdict `9c42046b` |
| p2-9, `GNA-CE1-R1` | A' | **levee** ; `FRA-CE1-15` **VALIDE** | `rejeu-nadia-palier-2-ap.md`, `FICHE-bandit-manchot-accord-groupe-nominal-ce1.md` | verdict `9c42046b` |
| p2-10, `RCT-CE1-R1` | A | **levee** ; `FRA-CE1-04` **VALIDE**, aucune reserve ouverte | `rejeu-nadia-palier-2-a-rct.md`, `FICHE-bandit-manchot-texte-narratif-ce1.md` | correction `4e5c9314`, verdict `ae116186` |
| p2-11, `VSJ-CE1-R2` | A | **levee** ; `FRA-CE1-13` **VALIDE**, aucune reserve ouverte | `rejeu-nadia-palier-2-a.md`, `FICHE-bandit-manchot-verbe-sujet-ce1.md` | verdict `b2f6da08` |
| p2-12, `RDO-CP-R3` | B | **levee** ; jeu **VALIDE**, aucune reserve ouverte. `ART-CP-10` | `rejeu-nadia-palier-2-b-rdo-cp-r3.md`, `FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md` | code `19c8a7b2` (tete `5a1d76fa`, code `23222465`), verdict `a5a486ad` |

**Ce que le verdict couvre, et ce qu'il ne couvre pas.** Chaque verdict vaut pour le jar que `nadia-prof` a joue. Les rejeux de `-05` et de `RCT-CE1-R1` ont ete joues sur `3eef868d`, dont le code differe de `7c9ac220` par la phrase de `RDO-CP-R3` (section 11, premiere erreur) : ses SHA-256 de paquet sont egaux, et elle a constate que cette ligne ne s'affiche sur aucun des deux jeux. Ce constat est le sien, je ne l'ai pas rejoue.

**Ce qui est fusionne est ce qui a ete joue** : `git diff --quiet bb322938 efb8d013 -- adapters domain application bootstrap pom.xml outils .github` sort 0 (mesure de ce jour). `yanis-qa` et `theo-archi` mesurent le meme fait sur `bb322938` (`rapport-qualite.md` en-tete ; `confirmation-archunit-palier-2.md` section 1).

## 2. Glissements et reports

- **Aucun poste de la table ne glisse.** Les six premiers reports (p2-7 a p2-12, comptes a l'ouverture par `DEC-0120` A.66) sont tous audites dans le palier ; un engagement tardif n'efface pas le report, il evite le suivant (A.83). Le troisieme report interdit (A.67, A.72) ne se pose pour aucun poste. `p2-1` a `p2-6` n'ont aucun report. **Reserve sur p2-7** : son audit est celui du rejeu de p2-8, pas un audit propre ; si `camille-po` ou `nadia-prof` lisent autrement le jugement d'un poste hors `PAR` sans rejeu propre, c'est a eux de l'ecrire (`planning.md`, amendement de 20:15, section 6).
- **Un engagement hors tableau glisse** : le commit de `RL5` a `RL7`, engage par moi le 2026-10-16 19:40 et non livre. `camille-po` l'ecrit comme premier glissement d'un poste engage (`DEC-0120` A.115, rang 2). Report ecrit au palier 3, avec sa condition, dans `planning.md` (amendement de 21:46, section 9). Il n'entre dans aucun lot et n'a aucun prix.
- **`-06`, `-07`, `-11` (groupe X)** : deuxieme ecartement, ecrit par `camille-po` (`DEC-0120` A.115). **Je ne le recalcule pas.** Premier : composition du palier 1, 2026-10-14. Deuxieme : composition du palier 2, 2026-10-15. Si `-06` n'entre pas au palier 3, c'est le troisieme et une `DEC` le dit avec son motif.

## 3. Fusions et comptage du catalogue

Compte de depart sur `round-two` a `f6134910` : 150. `git show <fusion>:adapters/adapter-content/src/main/resources/catalogue.yaml`, lignes `^  - id:`, lu **dans chaque commit de fusion**, pas sur l'arbre de travail. Sur les 39 fusions du palier (`git log --first-parent --merges f6134910..efb8d013`), le compte ne bouge qu'a deux.

| Fusion | SHA | Compte avant | Entrees | Compte apres | `grep -c "paquet: <nom>$"` | Tests du reacteur apres la fusion |
|---|---|---|---|---|---|---|
| p2-3 `s57-marc-fra-ce1-05` | `edcd3de9` | 150 | 1 | 151 | `ce1-pronoms` : 1 | 2640 |
| p2-4 `s57-sofia-fra-ce1-24` | `9f110983` | 151 | 1 | **152** | `ce1-familles-de-mots` : 1 | 2664 |

Les 37 autres fusions du palier laissent le compte inchange, **les cinq de ce jour comprises** (`286e47c0`, `27a20e92`, `31fe0126`, `376694c9`, `efb8d013`) : `catalogue.yaml` a `efb8d013` rend 152. `git diff-tree --cc` ne rend aucune ligne de conflit pour 38 des 39 fusions. **Une fusion avait un conflit** : `2764310d` (`s57-camille-p2-pn8`), double ajout en fin de `docs/backlog/backlog.md`, resolu en gardant les deux annotations (`planning.md`, amendement de 12:01, section 1) ; aucune assertion touchee.

**Les cinq fusions de cloture de ce jour**, lignee jugee avant chacune :

| Branche | Tete | Fusion | Delta |
|---|---|---|---|
| `s57-camille-p2-matrice` | `27a7dce6` | `286e47c0` | 6 fichiers, 139 insertions, 10 suppressions : `DEC-0120` A.111 a A.116, `DEC-index.md`, matrice, backlog, deux fiches |
| `s57-nadia-p2-cloture` | `65d218f9` | `27a20e92` | 4 fichiers, 19 insertions, 5 suppressions : trois fiches, `reponse-nadia-q-palier-2.md` |
| `s57-theo-p2-cloture` | `d2b7e0b5` | `31fe0126` | 1 fichier neuf, 221 lignes : `confirmation-archunit-palier-2.md` |
| `s57-relecteur-p2c` | `72c2b198` | `376694c9` | 1 fichier neuf, 105 lignes : `relecture-cloture.md` |
| `s57-yanis-p2-cloture` | `3b5a0ccb` | `efb8d013` | 1 fichier neuf, 188 lignes : `rapport-qualite.md` |

**Fiche des pronoms, conflit potentiel entre `camille-po` et `nadia-prof`** : les deux branches modifiaient `FICHE-bandit-manchot-pronoms-ce1.md` ; la fusion automatique a garde les deux cotes, sans marqueur. Lu apres fusion : l'en-tete de `camille-po` (statut global **VALIDE**, ligne 20) et l'annotation de `nadia-prof` du 2026-10-16 21:25 (en pied, sur `Non pousse` et sur le statut de tete). **L'annotation de `nadia-prof` decrit l'etat avant la fusion de la branche de `camille-po`** (*"le statut de tete de cette fiche dit encore refuse"*) : elle est datee et reste ecrite, l'en-tete est a jour. Aucune assertion affaiblie.

## 4. Total de tests

**2669 sur l'arbre fusionne, mesure par moi** : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, sur `efb8d013` : `BUILD SUCCESS`, 4 min 57 s. Lignes `Tests run` du reacteur : domain 120, application 31, adapter-web 501, adapter-content 1630, bootstrap 381, integration 6 ; 0 echec, 0 erreur, 0 ignore. Recompte a part par l'attribut `tests` des 313 fichiers `TEST-*.xml` : 2669. JDK 21.0.10 (`java.version` des 313 rapports, une seule valeur).

**Ecart avec 2597** (total de la cloture du palier 1 et de l'ouverture du palier 2) : **+72**, par module adapter-web +9 (492 a 501), adapter-content +63 (1567 a 1630), domain, application, bootstrap et integration 0. Chaine des totaux, chacun mesure par moi a la fusion indiquee (`planning.md`, amendements de 12:01, 12:46 et 13:53) :

| Fusion | Ce qu'elle ajoute | Delta | Total |
|---|---|---|---|
| `f6134910` (ouverture) | - | - | 2597 |
| `1ac68087` | `ADR-0065` A3, `AucunMotServiNeSEcarteDeSonAccentTest` | +2 (adapter-content) | 2599 |
| `aca0d90f` | forme commune, `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest` | +4 (adapter-web) | 2603 |
| `edcd3de9` | `PaquetCe1PronomsTest` | +37 (adapter-content) | 2640 |
| `9f110983` | `PaquetCe1FamillesDeMotsTest` | +24 (adapter-content) | 2664 |
| `e138ec97` | garde de `C1'` (4 tests) et `TL9` (1) | +5 (adapter-web) | 2669 |

2 + 4 + 37 + 24 + 5 = 72. Les fusions de correction (`02b4a34e`, `4e5c9314`, `19c8a7b2`), de contenu (`9cbf3571`) et d'outil (`97a205e8`) ne changent aucun compte Maven. **D'ou vient l'ecart entre les chiffres annonces et le total** : chaque agent mesure la base de sa branche plus son lot. `marc-dev` annonce 2634 pour `-05` (2597 + 37 ; la base n'avait pas les +2 et +4), `lea-dev` 2601 (2597 + 4), `theo-archi` et `yanis-qa` 2645 sur `d9c6cc69` (2640 + 5), `sofia-sm` developpeuse 2621 avec un echec (2597 + 24). Les trois fusions de cloture qui annoncent 2669 (`theo-archi`, `yanis-qa`, `nadia-prof`) mesurent un arbre dont le code est celui-ci : **je ne relaie pas leur chiffre, je l'ai mesure**, et il est egal.

**ArchUnit.** Methode d'`ADR-0104` (`static final ArchRule` et `@ArchTest` dans les `*.java` suivis par git), mesuree par `theo-archi` a `f6134910` et a `bb322938` : **41 et 38, dans 16 fichiers**, ecart nul. Dont deux declarations dans un faux depot de contre-exemple : regles reelles, 39 declarations dans 15 fichiers (`confirmation-archunit-palier-2.md` section 2). Je n'ai pas recompte ; le code n'a pas change depuis `bb322938`. Les quinze classes porteuses (88 tests) sont dans le `build` ci-dessus.

## 5. Matrice

Commande de la "Methode du compte" de `squad/programme/MATRICE-couverture.md`, lancee par moi sur l'arbre fusionne, sortie telle quelle :

```
nc 48
ec 4
ncb 77
cv 148
total 277
```

`couvert-valide` 148, `en cours` 4, `non couvert` 48, `non couvrable en l'etat` 77, total 277. Denominateur atteignable 277 - 77 = 200. **148 / 200 = 74,00 %**, le chiffre attendu. Par niveau (lignes atteignables, `couvert-valide`, meme commande restreinte au niveau, `non couvrable` ecartees) : CP 59 / 52, CE1 62 / 30, CM1 79 / 66, egal a `camille-po` (`DEC-0120` A.112).

**Le palier 2 passe de 146 a 148** : `FRA-CE1-24` (A.107, verdict `-24`) et `FRA-CE1-05` (A.112, rejeu apres correction). **Cellules mises a jour sans changer de statut** : `FRA-CE1-04` et `ART-CP-10` (reserves `RCT-CE1-R1` et `RDO-CP-R3` levees), `FRA-CE1-10`, `-13`, `-15` (reserves levees). `SCI-CM1-20` : non rouverte. Compteur de version de `camille-po` (A.32) : 148 + 4 = 152 / 200 = 76,00 % **par la formule** ; son critere 2 (CI verte sur l'arbre de la version) n'est pas lu.

## 6. Consommation par instance, et tokens

Prix de `nadia-prof`. Registre commite : `registre-prix-palier-2.txt` (seize lignes, reconstruit d'`DEC-0120` A.105, `planning.md` amendement de 21:46, section 10). `awk -F'|' '{p[$1]+=$3; t+=$3} END{for(k in p) printf "%s %.2f\n",k,p[k]; printf "total %.2f\n",t}' registre-prix-palier-2.txt | sort`, sortie lue ce jour : `A 3.25`, `Ap 3.00`, `B 1.75`, **`total 8.00`**.

| Instance | Composition | Facture | Detail | Marge | Non affecte |
|---|---|---|---|---|---|
| A (groupe X) | 3,25 | **2,75** | arbitrage 0,5 ; `-05` 1,0 ; rejeu de `-05` 0,5 ; `VSJ` 0,25 ; `RCT` 0,5 | 0,5 | 0 |
| A' (groupe Y) | 3,00 | **2,25** | arbitrage 0,5 ; `-24` 1,0 ; `TPH` 0,5 ; `GNA` 0,25 | 0,5 | 0,25 |
| B | 1,75 | **1,25** | arbitrage 0,5 ; `N1` et `N2` 0,25 ; `RDO-CP-R3` 0,5 | 0,5 | 0 |
| **total** | **8,00** | **6,25** | | **1,5** | **0,25** |

Bornes : somme 8,0 (`PAR-9`) ; plafond de 5,0 par instance (`PAR-8`), la plus chargee est A a 3,25 composee et 2,75 facturee ; trois lots au plus par instance (A un, A' un, B aucun). Marges intactes aux trois instances : **les deux rejeux d'A ont leve du premier coup**, aucun rejeu supplementaire n'a ete ouvert. **Le non affecte de A' (0,25) tombe, et les marges inutilisees (1,5) aussi** : `camille-po`, `DEC-0120` A.114 ; la capacite du palier 3 est celle que `nadia-prof` ecrira, rien n'est reporte. 6,25 factures sur 8,0. Les prix sont ceux de `nadia-prof` : je ne les juge pas.

**Rapport de tokens.** `python outils/rapport-tokens.py --sprint s57 --dry-run` fonctionne sur ce poste (`python`, pas `python3`) et ne separe pas le palier 2 : sa fenetre est celle du sprint, de l'horodatage de `planning.md` a celui de `review.md`, qui n'existe pas encore ; le script la laisse ouverte jusqu'a l'instant du lancement. Un `RAPPORT-tokens.md` ecrit aujourd'hui serait une fenetre partielle et mouvante, presentee comme un rapport. **Je ne l'ai pas ecrit et je n'ai commite aucun chiffre de tokens.** Le rapport est un seul pour le sprint, a la review (`squad/README.md`, section 6). La consommation en prix de `nadia-prof` ci-dessus n'est pas une consommation de tokens.

## 7. Controle croise de `yanis-qa` : ce que la mutation a montre

Sur `bb322938`, 14 lancements sur copies (`rapport-qualite.md`, section 2), restauration identique 14 fois sur 14, temoin vert avant toute mutation : **7 rouges** (`X1`, `X5`, `X7`, `X9`, `X10`, `X11`, `X13`) et **7 verts**. Les verts sont vrais dans le perimetre de la commande lancee (`W5`).

| Propriete | Lancement | Resultat | Etat |
|---|---|---|---|
| Position de l'indice au-dessus de y = 551 a 744 de large | `git grep`, pas de mutation (section 5) | seuls des commentaires et le juge de l'ecran a deux oeuvres | **non gardee** : une valeur de contenu qui allonge un indice ne rougit rien |
| Roles par genre (`TRV-CE1-6`) | lu, pas mute | le Javadoc de `PaquetCe1PronomsTest` le dit hors du test | **non gardee** |
| Ordre des faits (`RCT-CE1-R1`) | `X6`, bonne reponse d'Ines echangee avec un distracteur | **vert** | **non gardee** : `rct3` compte des phrases, pas des faits |
| Phrase de revelation dans sa boite | texte et feuille epingles (`X10`, `X11` rouges) ; la boite n'est mesuree par aucun test | mesure de `lea-dev` puis de `nadia-prof`, au jar | **non gardee** : tient tant que ni le texte ni la feuille ne bougent et que la pile de polices est celle mesuree |
| Item 12 de `-24`, *chatte* pour *chaton* (`E3`) | `X3` | **vert** | **non gardee en Java** ; `FM5-2` et `FM5-3` la gardent par commande |
| Mot cite de `N2` figurant une fois dans la phrase designee | `X4`, *aussi* devient *fleur* | **vert** | **non gardee** |
| Arret par nom dans le script d'`additions-cp` (`Stop-Process -Name`) | `X8` | **vert** (juge `.sh`, sortie 0) ; `X7` (`taskkill`) rouge | **non gardee** : le juge lit trois formes (`taskkill`, `pkill`, `killall`), le critere dit *"aucun arret par nom"* |
| `nombreDeManches` de `-24` | `X2a` (Surefire), `X2b` (Failsafe) | **verts** | **non garde**, le trou que `C8` du palier 1 avait note |
| `Z1`, virgule dans `:has(...)` | `X12` | **vert** | **trou de garde-fou ouvert** (section 8) |

`yanis-qa` ne propose aucune correction : le juge du script est a `marc-dev`, le critere 5 de `S57-SCRIPT-ADDITIONS-CP` a `camille-po`. Aucun de ces verts ne change un verdict de `nadia-prof`, qui est seule garde de ces proprietes. **Ce qui est route** : le juge du script (`X8`) a `marc-dev`, avec le critere a `camille-po` ; les autres proprietes sont a la lecture de `nadia-prof`, rien n'est engage pour les garder.

## 8. Architecture : confirmation de `theo-archi`, `ADR-0031`, dettes

**Confirmation** (`confirmation-archunit-palier-2.md`) : 41 et 38 dans 16 fichiers inchanges, aucune regle affaiblie, aucun `@Disabled` ni `allowEmptyShould` ajoute sur les dix fichiers `*.java` du palier. Les trois regles ajoutees ou amendees au palier sont des tests executables et mordent sur l'arbre fusionne : `ADR-0065` A3 (`B3`, 1 echec sur 7), `ADR-0031` amendee (`B2`), l'epinglage de la forme commune (`B1`). Aucun `ADR` neuf n'est appele par le palier 2.

**`E3` fermee.** La lecture du diff de B1 par `theo-archi` existe sur le disque : `avis-theo-palier-2.md`, section 1, 2026-10-16 08:26 : *conforme*, aucun ecart bloquant, lue apres la fusion de B1 (elle ne remplace pas la garde qui manquait avant). `Q-S7` etait tranchee par l'utilisatrice : la trace couvre tout le palier.

**`TL9` et `TL10` fermes.** Le fichier existe : `confirmation-archunit-palier-2.md`, section 3, morsure `B1` sur l'arbre fusionne (`column-gap` ajoute a la regle de `R3-3` : 1 echec, `laFormeCommuneEstCelleQuiAEteMesureeSurLaProduction`), que `planning.md` du 13:53 demandait. Je l'ai relu : `git ls-files` rend quatre repertoires sous `contreexemple/LaFormeCommuneDesPropositionsLonguesEstEpingleeTest/` et la Javadoc dit *"quatre feuilles fautives"*. `yanis-qa` mord la meme classe par `X11` (rouge).

**`F1` et `F2` : `ADR-0031` contredit par le code, deux amendements proposes, non appliques.**

| Code | Constat de `theo-archi` | Proposition | Etat |
|---|---|---|---|
| `F1` | la decision 1 ne mord plus sur son exemple depuis `5c60102e` (s10) : `manche.html` pose `est-juste` sur son `.signal`, les deux jetons coexistent, `body:has(.est-juste) .cabinet-bandeau` passe (`Z5b`). Du 2026-08-13 au 2026-10-16 il n'etait rouge que pour une raison de registre (`Z5a`) | `P1` : amendement de l'`ADR` (la decision 1 reste juste pour ce qu'elle juge, ce n'est plus elle qui garde `C4` sur l'ecran de manche), plus la mutation `Z5b` comme cas nomme | **a verser par `theo-archi`** dans le commit de `S57-GARDE-C1-VIRGULE-ET-IMBRIQUE` |
| `F2` | la decision de l'amendement du 2026-10-16 promet `:has(...)` compris, et deux formes passent : `Z1` (virgule) et `Z2` (CSS imbrique), 12 tests, 0 echec | `P2` : les deux formes dans *"Ce que cette regle ne garde pas"*, puis la correction dans le meme commit | **a verser**, meme commit |

**Dettes d'architecture, routees** (`confirmation-archunit-palier-2.md`, section 5). `Z1` et `Z2` : story `S57-GARDE-C1-VIRGULE-ET-IMBRIQUE`, **en tete du palier 3** ; `theo-archi` corrige le decoupeur partage, refuse le CSS imbrique, et dit que cela amende deux criteres de la story (4 et 5 c) : a `camille-po`, avant le commit. `Z3` : aucune ecriture, declencheur ecrit. `Z4` : cliquet sur 3 regions d'annonce, meme commit que `Z1`. `Z5` : `P1`. `PP`, pile de polices non gardee (`DEC-0122` 3.5) : `S57-GARDE-PILE-DE-POLICES`, un test d'`adapter-web`, amendement d'`ADR-0054` dans le meme commit, morsures a `yanis-qa`. `TQ`, 224 lignes a espace ordinaire avant `?` : aucune decision d'`ADR` a prendre (cliquet de 490 occurrences, `ADR-0067`) ; restent ouverts le choix du caractere (`ines-design`, `nadia-prof`), la dette (`camille-po`) et le `?` d'inconnue (`nadia-prof`). `E4`, `FM3b` : confirme, aucun amendement. **Pile de polices (`DEC-0122`)** : decision prise le 2026-10-16, `Acceptee` ; la garde est la story, pas encore ecrite.

**`RL5` a `RL7`** : le commentaire de `manche.html` est servi (octet servi, pas l'affichage), le report est ecrit (`planning.md`, amendement de 21:46, section 9) avec sa condition : avant le premier jar rejoue du palier 3.

## 9. Releve du `relecteur-pedagogique` : 19 ecarts, traitement et destinataires

`relecture-cloture.md` : 19 ecarts (`R1` a `R10`, `N1`, `N2`, `P1` a `P3`, `C1` a `C4`), 0 en conventions de redaction. **Etat a la fusion de ce fichier** :

| Ecart | Etat | Qui |
|---|---|---|
| `C1`, `C2`, `C3` | **traites**, annotation datee dans `planning.md` (21:46, sections 1, 3 et 4) | `sofia-sm` |
| `R3` | **traite** (section 6 de l'annotation) ; le champ existe dans les fiches, pas dans les rejeux | `sofia-sm` |
| `R9` | **traite** (`Q-P8` refermee, section 7 de l'annotation) | `sofia-sm` |
| `N2` | **traite** : registre commite (section 6 ci-dessus) | `sofia-sm` |
| `R5` | **reporte au palier 3**, etat et condition ecrits (section 9 de l'annotation) | `sofia-sm` |
| `R1` | planning **traite** ; reste `rapport-qualite-garde-c1.md` ligne 100 (`ADR-0031-...md`) | `yanis-qa` |
| `R2` | `nadia-prof` **traite** (`reponse-nadia-q-palier-2.md`, renvoi par l'horodatage de l'amendement) ; reste `rapport-qualite-lot-24.md` ligne 79 | `yanis-qa` |
| `N1` | planning **traite** (section 8 de l'annotation) ; reste `DEC-index.md` ligne 128 | `camille-po` |
| `C4` | **traite** : en-tete de la fiche des pronoms par `camille-po`, `Non pousse` annote par `nadia-prof` dans les deux fiches ; matrice a 148 | `camille-po`, `nadia-prof` |
| `P1` | `nadia-prof` **traite** (c), source unique nommee ; reste (a) la date du relais et la regle pour une reponse relayee (`E5` de `cloture-palier-1.md`, ouvert) | `camille-po` |
| `P2` | inscription **faite** par `camille-po` (`DEC-0120` A.111, mots *"fenetre bornee"*, forme 2) ; annotation de `planning.md` faite (section 2) ; reste le renvoi dans `rapport-marc-dev-palier-2-corrections.md` ligne 84 | `marc-dev` |
| `P3` | **ouvert** : citer *"oui"* en A.94 (a) et (b) ; renvoi a A.94 dans `avis-theo-palier-2.md` ligne 6 | `camille-po`, `theo-archi` |
| `R4` | **ouvert**, collision de `Q-T11` : le `relecteur-pedagogique` donne la convention (`Q-T11` = libelles de gabarit, `camille-po` ; `Q-T13` = reduction a 720, `theo-archi`), vraie pour tout texte neuf. Annotations datees a ecrire dans `avis-theo-poste-8-art-cp-10.md` lignes 816, 839, 860, `rapport-ines-r8-forme-commune.md` ligne 142, `FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md` ligne 308. `P8` de `cloture-palier-1.md` a sa reponse sur le disque | `theo-archi`, `ines-design`, `nadia-prof` |
| `R6` | **ouvert** : trois commentaires d'en-tete de paquet (`ce1-pronoms.yaml`, `ce1-texte-narratif.yaml`, `ce1-familles-de-mots.yaml`, ligne 14). Ce sont des fichiers de `src/main` : un commit de developpeuse ou de `marc-dev`, a placer a la composition du palier 3, avec la famille de `D5`. Je ne les engage pas ici | `marc-dev` ou developpeur nomme au palier 3 |
| `R7` | **ouvert** : citation en ASCII dans `rapport-lea-dev-palier-2-rdo-cp-r3.md` lignes 10 et 57 | `lea-dev` |
| `R8` | **ouvert** : numerotation des items de `RC1-1` a `RC1-3` sans base (*"comptes depuis 1"*) dans `rapport-marc-dev-palier-2-corrections.md` lignes 52 a 54 | `marc-dev` |
| `R10` | **ouvert** : citation coupee sans marque dans `DEC-0120` A.107 | `camille-po` |

**Les ecarts qui ne sont pas a moi, avec leur destinataire** : `R4` a `theo-archi`, `ines-design`, `nadia-prof` (collision de `Q-T11`) ; `P3` a `camille-po` et `theo-archi` (mots absents de A.94 (a) et (b) et de l'avis de `theo-archi`). Les autres lignes ouvertes ci-dessus sont dans le meme cas. La liste est reprise dans le tableau d'obstacles de `review.md` a la cloture du sprint.

## 10. Isolation

**Ma mesure** (consigne du 2026-10-15 20:56, section 6 : *"je liste (`ls`) les racines que ma session peut lire et je compte les fichiers qui n'y ont rien a faire"*) : c'est une mesure, pas une garde, et un instantane.

- **Racine du scratchpad de la session** : 16 repertoires, **0 fichier** (`find -maxdepth 1 -type f`). Les 16 noms sont ceux de la table du palier 2 (`s57-p2-camille-po-t`, `-ines-design-t`, `-ines-design-t2`, `-lea-dev-ap`, `-lea-dev-b`, `-marc-dev-a`, `-marc-dev-b`, `-marc-dev-t`, `-nadia-prof-a`, `-nadia-prof-ap`, `-nadia-prof-b`, `-relecteur-t`, `-sofia-dev-ap`, `-sofia-sm-t`, `-theo-archi-t`, `-yanis-qa-t`) ; la table en compte seize, aucun nom hors table. Le palier 1 comptait 8 agents sur 9 qui avaient ecrit a la racine (`arbitrage-nadia-palier-2.md` 3.2) ; **je n'ai aucun fichier a la racine au palier 2**. Je n'ai pas de liste de la racine du palier 1 pour dire ce qui y a ete ramasse depuis.
- **Repertoire parent des worktrees** : les 18 entrees hors `recreo*` ont ete datees une a une, la plus recente est du 2026-10-11, avant l'ouverture de s57 (2026-10-14) : aucun fichier d'agent du palier 2 n'y est. `git worktree list` rend 94 lignes qui portent `s57` ; **leur nettoyage n'est decide par personne**, je le dis sans le decider.
- **Ce que je n'ai pas mesure** : le repertoire temporaire du systeme (`$TEMP`), ou des JVM et des outils peuvent ecrire sans que le crochet les voie ; mes runs de Maven ont recu un `TMPDIR` dans mon repertoire, la valeur par defaut de la JVM n'est pas redirigee.
- **Dernier listing**, fait apres le dernier commit de ce fichier : voir le message de livraison de la tache.

**Ce que les rapports du palier 2 declarent** (`git grep` sur les fichiers ajoutes depuis la cloture du palier 1) :

| Source | Declaration |
|---|---|
| `rapport-marc-dev-palier-2.md` ligne 82, `rapport-marc-dev-palier-2-corrections.md` ligne 88 | **racine : propre**, racine du scratchpad et parent des worktrees listes a la fin ; ports verifies libres, arret par PID |
| `rapport-lea-dev-palier-2.md` ligne 70 | tout ce qui a ete ecrit est dans son repertoire ; la liste de la racine est renvoyee au *"message de rendu"*, **qui n'est pas dans le depot** : la declaration n'est pas lisible |
| `cote-de-reference-forme-commune-2026-10-16.md` ligne 209 (`ines-design`) | meme renvoi au *"message de rendu"*, meme absence |
| `rapport-qualite.md` `W6` (`yanis-qa`) | une redirection ecrite par erreur vers la racine du systeme de fichiers, **refusee** (*Permission denied*) : rien cree, ni dans `$TEMP` ni ailleurs |
| `planning.md` amendement de 13:53, section 7 (de moi) | le lanceur signale que `yanis-qa` a cree puis supprime un fichier temporaire hors du depot au rapport de la garde ; le rapport ne le mentionne pas, **non verifie** |
| `mesure-ines-pa7-...` ligne 110, `mesure-ines-pa8-...` ligne 112 | aucun fichier d'un tiers deplace ou supprime ; jars arretes par PID |
| `rapport-sofia-dev-palier-2.md` ligne 12 (de moi, developpeuse) | repertoire et ports, aucun jar ; **aucune ligne sur la racine** : mon rapport ne declare ni propre ni sale |

**La lecture de `nadia-prof` pour le palier 2 n'est dans aucun fichier du depot.** Elle l'a annoncee (`arbitrage-nadia-palier-2.md` 3.4, point 3 : *"je lis les transcriptions a la cloture du palier 2"*). Ce n'est pas un verdict : **ouvert, a `nadia-prof`**. Je ne m'y substitue pas : les racines des autres sessions et les redirections ne sont lisibles que dans les transcriptions.

## 11. Erreurs du palier, ecrites comme telles

1. **Mon erreur sur le sommet de rejeu.** L'amendement de 19:20 ecrit que *"le code et le contenu servis de `round-two` sont ceux de `7c9ac220`"*. C'etait vrai a `1cf4e3a9` ; c'est devenu faux a la fusion de `s57-lea-rdo-cp-r3` (19:40), et je n'ai pas relu la phrase apres cette fusion. L'amendement de 19:40 a corrige le sommet mais gardait *"code de `7c9ac220`"* pour l'instance A. J'ai donne a l'agent principal le sommet `3eef868d` pour le rejeu de l'instance A avec un code qui n'etait pas celui annonce : cinq fichiers differaient (`manche.html` et quatre tests). **`nadia-prof` a verifie l'annonce, l'a trouvee inexacte a la lettre et l'a ecrit** (`rejeu-nadia-palier-2-a-lot05-rejeu.md`, `rejeu-nadia-palier-2-a-rct.md`) ; je l'ai corrige a 20:15 (`planning.md`, section 1). Le releve du `relecteur-pedagogique` a verifie le correctif (*"Verifie, et qui tient"*, section 6).
2. **L'erreur sur `S2`, la mienne et celle de l'agent principal.** `cloture-palier-1.md` `P5` ecrit que `DEC-0120` 4.5 *"ne porte pas ses mots : non trouves"* et que *"l'agent lanceur doit fournir les mots et la date"*. `arbitrage-nadia-palier-2.md` section 9 le reprend (*"`S2` ... ses mots"* manquent, a obtenir de l'agent lanceur). **Les mots sont dans le depot** : *"ajoute le au repo et ping nadia dessus sur le sprint prochain"*, `DEC-0120` section 4.5 (ligne `RP-ACCUEIL-147`) et `squad/rapports/JOURNAL-gouvernance-hors-sprint.md` ligne 180, entree du 2026-10-12. La commande qui les trouve est `git grep -n "ping nadia dessus"`. **Je ne l'avais pas lancee** ; l'agent principal, designe par `P5` pour fournir les mots, ne les a pas fournis et n'a pas signale qu'ils etaient au depot. `camille-po` les a retrouves (`DEC-0120` A.114). Ce qui manque est autre : aucune des deux entrees ne dit par quel chemin les mots lui sont parvenus.
3. **Mes autres ecarts, nommes par le releve et traites en section 9** : `C1` (j'ai attribue a l'utilisatrice une condition de ma main), `C2` (*"tous les postes sont audites"*), `C3` (l'etat de `p2-7` a l'ouverture), `R1` (noms tronques), `R3` (un nom de champ), `R9` (`Q-P8` non refermee), `N1` (deux comptes d'un module sans le module), `N2` (trois recomptes sur un registre non commite). **Et `R5`** : j'ai engage le commit de `RL5` a `RL7` a 19:40, ne l'ai pas rappele a 20:15, et il n'existe pas.
4. **Ce que `cloture-palier-1.md` disait a tort** : `P5`, sur `S2`, comme ci-dessus. Le reste de `P5` (`S1`, `S3`, `S4`) n'est pas relu ici : je ne dis rien de son exactitude.
5. **Ce que `arbitrage-nadia-palier-2.md` section 9 disait a tort** : *"`S2` ... ses mots"* absents (point 2). Le meme tableau dit que les mots de `Q-N4` manquent : ils ont ete fournis depuis (*"le pli n'est pas bloquant"*, forme 2, date de l'echange d'origine non fournie, `DEC-0120` A.114). Son erreur de notation sur `Q-N4` (`P1` (c) du releve) est corrigee par ses annotations du 2026-10-16 21:25.
6. **Une affirmation qui n'avait pas ete verifiee et que le releve a verifiee** : le releve a cherche le champ *"Tarif consomme"* dans les rejeux et conclu qu'il n'existait que dans un seul fichier ; il existe aussi dans deux fiches (`R3`, section 6 de l'annotation). Je le dis pour que l'ecart ne soit pas recopie plus large qu'il n'est.

## 12. Porte technique : jobs de `ci.yml` lances pour ce commit

Lances sur `round-two` a `efb8d013` (cinq fusions de cloture), puis avec `cloture-palier-2.md`, `retro-palier-2.md`, `registre-prix-palier-2.txt` et l'annotation de `planning.md` presents dans l'index, avant commit. **Je ne deduis aucun job que je n'ai pas lance.**

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`, deux fois : sur `efb8d013` seul (`BUILD SUCCESS`, exit 0, 4 min 57 s), puis avec les fichiers ci-dessus presents (`BUILD SUCCESS`, exit 0, 5 min 06 s). **2669 tests, 0 echec, 0 erreur, 0 ignore** aux deux runs (120, 31, 501, 1630, 381, 6 d'integration, lignes `Tests run` du reacteur, somme faite par moi, recomptee a part par l'attribut `tests` des 313 fichiers `TEST-*.xml` : 2669). JDK 21.0.10 (`java.version` des 313 rapports, une seule valeur). **Delta nul avec `bb322938`**, comme attendu : les cinq fusions et ce commit ne portent que `squad/`. Des tests de `bootstrap` lisent `squad/` : le second run a lu ce fichier avant l'ajout de la presente section et d'une correction d'une phrase de la section 14, qui n'ajoutent aucun tableau ni aucune reference de decision.
- **`build`, etape "Package du fat jar"** : lancee sous sa forme, apres le second `verify` et sans `clean`, par l'enveloppe de toolchain : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package` : `BUILD SUCCESS`, exit 0, 3 min 24 s, tests Surefire rejoues (120, 31, 501, 1630, 381, 0 echec), ligne `Replacing main artifact ... with repackaged archive` dans la sortie, `bootstrap/target/recreo.jar` produit (22 492 790 octets). **La CI lance `mvn -B -pl bootstrap -am package` : la difference est l'enveloppe, qui choisit le JDK 21 par la toolchain.** Le jar n'a pas ete demarre (l'etape de la CI ne le demarre pas non plus). **`yanis-qa` n'a pas lance cette commande** : il a lance `bash outils/mvn-jdk21.sh -B -DskipTests package` sur le reacteur entier, parce que `verify` venait de jouer les tests, et le dit (`rapport-qualite.md`, section 1) ; il a demarre le jar sur son port et joue cinq requetes. `theo-archi` ne l'a pas relancee a part.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins `.github/scripts/verifier-chemins-test.sh`), ces fichiers compris, passe a `verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; `commit-atteignable.sh bb322938` sort 0 ; les 5 sujets d'agents hors fusions de `bb322938..HEAD` et le sujet prevu du commit passent dans `verifier-prefixes.sh` : *"Tous les commits portent un prefixe d'agent connu."*, sortie 0 ; les sujets avec fusions aussi, sortie 0. L'etape *"Verifier le proprietaire du garde-fou de l'accent"* est lancee sous la forme `bash .github/scripts/verifier-garde-fou-accent.sh "bb322938..HEAD"` (la base d'un push, `github.event.before`, n'existe pas en local) : *"0 commit(s) le touchent dans la plage, tous [THEO]."*, sortie 0.
- **`outils`** : les trois balayages (`outils/*-test.sh` : 6 ; `squad/outils/*/*-test.sh` : 2 ; `outils/*-test.py` : 5), **13 juges** lances (`bash` pour les `.sh`, `python` pour les `.py`), 13 sortis 0, aucun balayage vide, `TMPDIR` en forme POSIX dans mon repertoire de travail. **Reserve** : `ci.yml` choisit `python3` des qu'il resout, et sur ce poste `python3` est un executable du magasin d'applications qui echoue : la branche `python3` de `ci.yml` n'est pas celle que j'ai exercee. Avec un `TMPDIR` en forme mixte, `mvn-jdk21-test.sh` sort 1 (`yanis-qa` et `theo-archi`, mesure ; section 13 (c)).
- **Code inchange par ce commit** : `git diff --quiet bb322938 -- adapters domain application bootstrap pom.xml outils .github` sort 0.

**Jobs lances par les autres agents a la cloture, sur `bb322938`, tels qu'ils les declarent** : `yanis-qa` (`rapport-qualite.md`, section 1) : `build` 2669, `chemins`, `prefixes`, `outils` (13 sur 13 avec `TMPDIR` POSIX, 12 sur 13 avec `TMPDIR` mixte), `-DskipTests package` seulement ; `theo-archi` (`confirmation-archunit-palier-2.md`, section 8) : `build` 2669 (7 min 50 s puis 8 min 23 s), `chemins`, `prefixes`, `outils` 13 sur 13, etape de package non relancee a part ; `relecteur-pedagogique` (`relecture-cloture.md`, section 8) : `verifier-chemins.sh` sur le diff du palier, quatre classes de `bootstrap` qui lisent `squad/` (19 tests, mesure partielle avec `-am`), `verifier-prefixes.sh` sur son sujet : **pas `mvn verify`**.

**La CI distante n'est lue par personne.** `gh run list --branch round-two` rend `HTTP 404` depuis ce poste (le meme resultat que `theo-archi` a rapporte) ; il n'y a pas de `.env` dans ce checkout, et `gh` s'appuie sur la session enregistree du poste. `yanis-qa` et le `relecteur-pedagogique` ecrivent ne pas l'avoir lue. **Aucun des resultats ci-dessus n'est celui de la CI. Le Done technique n'est pas prononce par moi** : il est juge par la CI sur le commit pousse, et personne n'a lu son verdict pour le palier 2.

## 13. A decider par l'utilisatrice

**Quatre decisions, posees, aucune prise.** Je les pose avec l'avis ecrit de chaque agent qui en a donne un, et je dis quand un agent n'en a pas ecrit. **Ni `ci.yml` ni `outils/mvn-jdk21-test.sh` ne sont modifies par ce fichier.**

### (a) `Q4` : un crochet du harnais sur les redirections du shell

**Question.** Aujourd'hui un crochet local du harnais refuse l'outil d'ecriture de fichier a la racine du scratchpad (7 refus mesures au palier 1) ; il ne voit pas les redirections du shell, par lesquelles la racine a ete ecrite. Faut-il : (1) un crochet qui lit le texte de la commande, (2) un controle de l'effet par commande, ou (3) rien de plus que la mesure de cloture de palier ? En attendant, rien ne change.

| Agent | Avis ecrit | Source |
|---|---|---|
| `theo-archi` | **(1) non recommande** : il enumere des formes d'ecriture et echoue par omission (script qui ecrit lui-meme, variable qui porte le chemin, `cd` puis chemin relatif, journal de Maven, profil de Chrome). **(2)** voit toute ecriture, quel que soit l'outil ; sa limite est l'attribution, pas la detection. **(3)** est le meme controle de l'effet, une fois par palier : detecte, n'empeche pas, n'attribue pas. Il ne recommande pas entre (2) et (3) | `avis-theo-palier-2.md` section 4 ; `confirmation-archunit-palier-2.md` 5.8 (*"inchange"*) |
| `nadia-prof` | **constat, sans choix** : le crochet est dans un reglage local hors depot, aucun fichier ne dit qui l'a pose ni sur quels mots ; ce qui manque est *"un moyen qui voie le shell"*, pas a elle ; elle lit les transcriptions a la cloture de chaque palier | `arbitrage-nadia-palier-2.md` 3.2 et 3.4 |
| `yanis-qa` | **aucun avis ecrit** | - |
| `camille-po` | **aucun avis ecrit** | - |
| `sofia-sm` | **constat** : la consigne du 2026-10-15 20:56 n'est pas un moyen de garde. Au palier 2, ma mesure (section 10) trouve la racine sans fichier ; c'est un instantane, pas une preuve que personne n'a ecrit | `planning.md` 20:56 section 6 ; section 10 ci-dessus |

### (b) Declarer `node` et Python dans le job `outils` de `ci.yml`

**Question.** `ci.yml` n'a ni `actions/setup-node` ni `actions/setup-python` ; Java est declare (`actions/setup-java`) dans le job `build` seul. Depuis `baefae1d` (juge du script d'`additions-cp`), un des 13 juges lance `node` et le commentaire du job (*"sans aucune dependance au poste"*) n'est plus exact. Declarer les deux, avec une version majeure fixee, ou aucun ?

| Agent | Avis ecrit | Source |
|---|---|---|
| `theo-archi` | **declarer les deux**, chacun a une majeure fixee, celle que le poste de mesure rapporte (`node` v24.12.0, Python 3.14.0, lus ce jour). Motif : personne ne peut lire ce que l'image porte (`gh run list` rend HTTP 404 depuis ce poste) ; declarer rend le verdict independant de l'image. **Pas d'`ADR`.** La disponibilite de ces majeures dans les deux actions n'est pas verifiee, a verifier au commit | `confirmation-archunit-palier-2.md` 6.1 |
| `yanis-qa` | **declarer `node` par `actions/setup-node` a majeure fixee, dans le seul job `outils`, et Python de la meme famille ; sinon aucun des deux.** Ce n'est ni un blocage ni un faux vert : l'absence de `node` rend le juge rouge et bruyant (`ECHEC node absent`). Un seul des 13 juges lance `node` ; cinq juges Python plus un balayage, toute version 3 convient. Non mesure : la version sur l'image `ubuntu-latest` | `rapport-qualite.md` 4.2 |
| `camille-po`, `nadia-prof` | **aucun avis ecrit** | - |

Les avis concordent. Le commit de `ci.yml` serait a un developpeur que j'engage, la mutation (job lance sans `node`, rouge) a `yanis-qa`.

### (c) Normaliser `TMPDIR` dans `outils/mvn-jdk21-test.sh` par `cd` puis `pwd`

**Question.** Avec un `TMPDIR` en forme mixte (lettre de lecteur, deux-points), `mvn-jdk21-test.sh` sort 1 avec 10 cas en echec ; en forme POSIX, il sort 0. Sur la CI Linux le cas ne se produit pas. Normaliser, refuser ce `TMPDIR`, ou ne rien changer ?

| Agent | Avis ecrit | Source |
|---|---|---|
| `theo-archi` | **normaliser**, pas refuser : le juge teste l'enveloppe, pas l'environnement. Une ligne (ci-dessous), avec une garde contre le banc vide (sans elle, un `mktemp -d` en echec ecrirait le banc sous `/bin`). Mesure : mixte avant 10 echecs, mixte apres 0, POSIX apres 0. Preuve manuelle et datee dans le message du commit | `confirmation-archunit-palier-2.md` 6.2 |
| `yanis-qa` | **normaliser** (avis reconfirme et mesure) : copie du script avec `cd` puis `pwd`, `TMPDIR` mixte : sortie 0, 14 `ok`. **Correction a son avis precedent** : sur 13 juges lances avec `TMPDIR` mixte, 12 sortent 0, seul celui-ci sort 1 ; la correction tient dans un seul fichier. Limite : la CI Linux n'executera jamais la forme mixte, la correction n'aura pas de contre-exemple en CI. Il n'explique pas pourquoi la forme a barres inverses passe | `rapport-qualite.md` 4.1 |
| `camille-po`, `nadia-prof` | **aucun avis ecrit** | - |

La ligne proposee par `theo-archi`, qui remplace la ligne 23 du script (copie de `outils/` mesuree, script non modifie) :

```
BAC="$(mktemp -d)" && BAC="$(cd "${BAC}" && pwd)" && [ -n "${BAC}" ] || { echo "ECHEC repertoire de banc"; exit 1; }
```

Les avis concordent. Le script est a `theo-archi` ou `sofia-sm` (`yanis-qa`) ; `theo-archi` dit que `outils/` n'est pas a lui : le commit serait a un developpeur que j'engage.

### (d) Fusion de la retro des erreurs du palier

**Question, telle que le lanceur la pose** : *"fusion du retro des erreurs du palier"*. **Je la lis ainsi, et le dis pour qu'on me corrige** : `retro-palier-2.md` (a cote de ce fichier) porte les lecons du palier 2 et la section 11 de ce fichier les erreurs. `squad/README.md` (section 4) veut **une seule `retro.md` par sprint**, ecrite a la cloture du sprint. **Faut-il verser les lecons de `retro-palier-2.md` telles quelles a la `retro.md` du sprint, ou la retro du sprint repart-elle d'une page neuve et celles-ci ne sont qu'une source ?** Je n'ai pas ecrit `retro.md` : un fichier de ceremonie ecrit avant la fin du sprint serait une retro qui n'a pas eu lieu.

| Agent | Avis ecrit |
|---|---|
| tous | **aucun avis ecrit.** La question n'a ete posee a aucun agent |
| `sofia-sm` | **ma recommandation, non une decision** : verser les lecons dans la retro du sprint, sans les reecrire, comme sources citees ; l'amendement a `squad/README.md` qu'une lecon appellerait est a ecrire a la retro du sprint, pas ici |

## 14. Ce qui reste a faire au palier 3

Rien n'est decide ici : le palier 3 n'est pas ouvert et ce fichier ne le compose pas. **Pour l'ouvrir** (`D2` de `planning.md`, quatre conditions) : (a) ce fichier sur `round-two` ; (b) l'arbitrage prealable de `nadia-prof` pour chaque instance du palier, qui ecrit la capacite ; (c) la composition de `camille-po`, avec un amendement date de `planning.md` inscrit avant l'ouverture des worktrees ; (d) la table d'isolation, que j'ecris apres la composition.

**Proposition de `camille-po` (`DEC-0120` A.115), sans engagement ; l'engagement est a moi, la capacite et les prix a `nadia-prof`.** Etat de depart : 148 / 200 ; 48 `non couvert` (francais CE1 7, questionner le monde CE1 12 et CP 3, histoire des arts CM1 6, mathematiques CE1 5, enseignement moral et civique CE1 4 et CP 1, arts plastiques CE1 2 et CM1 2, histoire-geographie CM1 2, langues vivantes CM1 2, francais CM1 1 et CP 1) et 4 `en cours`.

| Rang | Quoi | Prix | Remarque |
|---|---|---|---|
| 1 | `S57-GARDE-C1-VIRGULE-ET-IMBRIQUE`, puis `S57-GARDE-PILE-DE-POLICES` | 0, hors `PAR` (`theo-archi`) | avant tout poste qui modifie `recreo.css` ; `P1` et `P2` d'`ADR-0031` dans le premier commit |
| 2 | commit de `RL5`, `RL6`, `RL7` | 0, hors `PAR` | avant tout autre commit de code et **avant le premier jar rejoue** ; premier glissement ecrit |
| 3 | `-06` (`ce1-inference`), puis `-07` (`ce1-texte-documentaire`), puis `-11` (`ce1-negation`), groupe X, dans l'ordre de `nadia-prof` | 1,0 chacun | deuxieme ecartement ecrit ; `-06` est le troisieme s'il n'entre pas, et une `DEC` le dit |
| 4 | `S57-GARDE-NOMBRE-DE-MANCHES` | 0, hors `PAR` | aucun rejeu |
| 5 | `S57-TYP1-TEXTES-DE-GABARIT`, puis `S57-MESSAGE-PARTIE-PERDUE` | 0, hors `PAR` | la seconde attend la mesure du nombre de lignes a 744 et a 1200 |

**Les stories de `-06`, `-07` et `-11` n'existent pas** : `camille-po` les ecrit a l'ouverture du palier 3, avant l'arbitrage prealable. Aucun lot propose pour A' ni B (une ligne sans paquet instruit n'est pas un lot). **A cote de la proposition, ce que le releve et ce fichier laissent** : les corrections `R6`, `R7`, `R8`, `R10`, `P3`, `R4` (section 9) ; `Q-N5`, ouverte pour `nadia-prof` seule, sans echeance ; la lecture de l'isolation du palier 2 par `nadia-prof` (section 10) ; les trois decisions de la section 13 si l'utilisatrice les prend ; le nettoyage des worktrees, que personne n'a decide.

## 15. Obstacles du palier

Repris dans `review.md` a la cloture du sprint. **Un obstacle n'est clos que quand le fichier qui le tranche existe sur le disque.**

| Obstacle | Categorie | Agent | Artefact | Etat |
|---|---|---|---|---|
| `Q-U11`, paquet refuse servi | produit | `camille-po` | `DEC` | **clos** : `DEC-0120` A.111 |
| `Q2` de `marc-dev`, jeu fautif de `TRV-CE1-6` | produit | `camille-po` | `DEC` | **clos** : `DEC-0120` A.113, pas de story |
| Matrice a jour | produit | `camille-po` | matrice | **clos** : 148 / 200, `DEC-0120` A.112 |
| `E3`, lecture du diff de B1 | technique | `theo-archi` | trace | **clos** : `avis-theo-palier-2.md` section 1 |
| `TL9` et `TL10` | qualite | `theo-archi`, `yanis-qa` | mesure de morsure | **clos** : `confirmation-archunit-palier-2.md` section 3 |
| `RCT-CE1-R1`, `RDO-CP-R3`, `TPH-CE1-R1`, `GNA-CE1-R1`, `VSJ-CE1-R2` | pedagogie | `nadia-prof` | avis dans la fiche | **clos** : cinq rejeux, sections 1 |
| Pile de polices | produit | `camille-po` | `DEC` | **clos** : `DEC-0122` ; la garde est une story non ecrite |
| `F1` et `F2`, `ADR-0031` | technique | `theo-archi` | amendement | **ouvert** : `P1` et `P2` proposes, non versees |
| `Z1`, `Z2`, `Z3`, `Z4`, `Z5` | technique | `theo-archi` | story et amendement | **ouvert** : palier 3, rang 1 |
| `RL5` a `RL7` | redaction | `sofia-sm`, developpeur | commit | **ouvert** : report ecrit, palier 3 |
| `R4`, collision de `Q-T11` | redaction | `theo-archi`, `ines-design`, `nadia-prof` | annotations | **ouvert**, convention donnee par le releve |
| `P3`, mots absents de A.94 | redaction | `camille-po`, `theo-archi` | citations | **ouvert** |
| Reponse relayee, `E5` | produit | `camille-po` | regle | **ouvert** |
| `Q-N5` | pedagogie | `nadia-prof` | reponse | **ouvert**, sans echeance |
| `Q4`, `ci.yml` et `node`, `TMPDIR` | technique | utilisatrice | decisions | **ouvert** : section 13 |
| Lecture de l'isolation du palier 2 | pedagogie | `nadia-prof` | lecture ecrite | **ouvert** |
| Rapport de tokens | capacite | `sofia-sm` | rapport | **a la review** (section 6) |
| Items `R6`, `R7`, `R8`, `R10` du releve | redaction | `marc-dev`, `lea-dev`, `camille-po` | corrections | **ouvert** |

## Decisions prises

**Ce fichier ne cree ni n'amende aucune `ADR` ni `DEC`.** Liste des decisions du palier 2 (de `f6134910` a `efb8d013`, `git diff --name-status f6134910 efb8d013 -- squad/decisions`), avec statut :

| Decision | Cree ou modifie | Statut |
|---|---|---|
| `DEC-0120` (ouverture de s57) | modifiee, amendements quinze a dix-huit (`A.94` a `A.116`) : arbitrages du palier 2, garde de `C1'`, prix et paquet refuse servi, cloture (`Q-U11`, matrice 148 / 200, `Q2`, `Q-N5`, proposition de palier 3) | `Acceptee`, inchange |
| `DEC-0122` (perimetre de garantie d'une cote : la pile de polices servie) | **creee**, 2026-10-16, `camille-po` | `Acceptee` |
| `ADR-0031` (un ecran ne gouverne jamais l'apparence d'un autre) | modifiee : amendement du 2026-10-16, garde de `C1'` point 1 (`b0c47c97`). **Deux amendements proposes par `theo-archi` (`P1`, `P2`), non verses** | `Accepte` |
| `ADR-0065` (un mot lu par un enfant est juge sur un lexique exterieur) | modifiee : amendement `A3` du 2026-10-16 (`76642a48`) | `Accepte` |

`ADR-index.md` et `DEC-index.md` portent les lignes correspondantes (`DEC-index.md` lignes 128 pour `DEC-0122`, 496 a 500 pour les amendements de `DEC-0120`).
