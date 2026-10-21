# s57 - composition du palier 4 : affectation, file de fusion, table d'isolation, point de controle poste/story

- **Auteure** : `sofia-sm`, casquette Scrum Master, 2026-10-21 22:10 (UTC+02:00), lu par `date`. Arbre : `round-two` a `eca79363`, `bash outils/verifier-lignee.sh` sort 0, `git status --short` vide avant ecriture, `git worktree list` rend 2 lignes (le checkout et celui de `main`), `git branch --list 's57-*'` rend 0.
- **Ce que ce fichier est** : ce qui est a moi dans la composition du palier 4 : l'affectation nominative, la file de fusion et ses regles (`DEC-0120` A.130), la table d'isolation (condition (d) de `D2`), le point de controle poste/story (`DEC-0086` section 5, `PAR-16`), l'etat de ce qui doit exister avant l'ouverture. **Ce qu'il n'est pas** : la composition de `camille-po` (lots, instances, ordre produit : `DEC-0120` A.145 a A.152, `preparation-palier-4-camille.md` sections 11 a 13) ni les prix et la capacite de `nadia-prof` (`arbitrage-nadia-palier-4.md`, sections 1 a 8). Je les reprends sans modifier un prix ni un lot. Modele : `composition-palier-3.md`.
- **Statut** : porte par l'amendement de `planning.md` du meme jour, qui ouvre le palier 4 et designe ce fichier comme porteur de la table d'isolation (meme ecart a la lettre de `D2` (d) qu'au palier 3, dit : une seule copie). Ce fichier n'ouvre aucun worktree, ne cree aucune branche, n'ecrit aucun code.
- **Mots de l'utilisatrice**, forme 2 de `DEC-0062` (cites mot pour mot, 2026-10-21, `E5` ferme) : *"LP9 oui on peut augmenter le poids des dessins / QP3 plafond accepté / composition de palier 4 OK / pousse après tout ça"* ; *"1 quatre instances"* ; *"QP6 lea dev pour B et sofia pour A'"* ; *"QP7 après un palier 5 voir 6 je ne sais pas encore"* ; *"QP5 oui"* ; *"E5 oui ça vaut pour mes mots"*. Seuil de 20,0 : quatre instances de 5,0, `PAR-8` inchange (5,0 par instance, trois lots au plus).
- **Sources** : `arbitrage-nadia-palier-4.md` (`f197dc39`) ; `avis-theo-palier-4.md` (`fedaf20f`) ; `preparation-palier-4-camille.md` et `DEC-0120` A.145 a A.152 (`e573613d`) ; amendement de `S57-BR-FRA-CE1-21` au backlog (`eca79363`) ; `cloture-palier-3.md` (`e4d54795`) ; `retro-palier-3.md` (`456654f8`) ; `composition-palier-3.md` ; `planning.md` (sections "Charge des developpeurs", "Isolation", ouverture du palier 3) ; `docs/backlog/backlog.md` (`eca79363`).

## Resume

| Code | Point | Valeur |
|---|---|---|
| CO1 | Total compose | **10,5** sur 20,0 (quatre instances de 5,0), 7 lots. Au-dela de 8,0 c'est la decision de l'utilisatrice, ecrite comme telle par `nadia-prof` (8.4), pas `O2` |
| CO2 | Par instance | A 2,0 (1 lot) ; B 4,5 (3 lots) ; A' 4,0 (3 lots) ; C n'ouvre pas, aucun lot recevable |
| CO3 | Engage a l'ouverture | **7,5** (arbitrages 1,5 ; six lots 6,0) ; **non engage 1,5** (`LP9`) ; marges 1,5 allouees a aucun poste. **Ecart a `camille-po`** (`engage 9.00`) : elle compte `LP9` engageable, je le compte non engage tant que les conditions d'`A.149` ne sont pas ecrites (section 2) |
| CO4 | Developpeurs | `marc-dev` (A) ; `lea-dev` (B) ; `sofia-sm`, casquette developpeuse (A') : `QP6`, `RG2` tenue, aucun ecart |
| CO5 | Fusion pendant que je suis developpeuse de A' | **personne d'autre** : ma regle du 2026-10-21 (`cloture-palier-3.md`, amendement 20:59) tient, ses consequences sont ecrites section 5 |
| CO6 | Avant l'ouverture, **non tenu** | huit points, section 8 : rapports de `marc-dev` et de `lea-dev`, garde `E3`, registre du palier 3, story d'arbitrage, conditions de `LP9`, extension de `verifier-chemins.sh`, commentaires de `src/`, lecture de `nadia-prof` sur les racines |
| CO7 | Hors `PAR` engage | `S57-GARDE-NOMBRE-DE-MANCHES`, `marc-dev`, apres `LP1` |
| CO8 | Reports a l'ouverture | `LP9` : premier report (non engage). `LP6` : engage sous la condition de `nadia-prof` ; report a la cloture si elle ne tient pas |

## 1. Les bornes, et comment cette composition les tient

| Regle | Source | Cette composition |
|---|---|---|
| 20,0 par palier, quatre instances | mots de l'utilisatrice, `DEC-0120` A.138 | 10,5 compose ; 20,0 est un plafond, non un objectif : le stock de lots prets limite, pas la borne (`nadia-prof` 2.3, 8.4) |
| 5,0 et trois lots au plus par instance | `PAR-8` | A 2,0 et 1 lot ; B 4,5 et 3 lots (plafond de lots atteint) ; A' 4,0 et 3 lots (plafond de lots atteint) |
| Une matiere et un niveau ne sont jamais partages entre instances | `PAR-2` | francais CE1 a A, mathematiques CE1 a B, EMC CE1 a A' |
| Aucun terrain neuf tant qu'A' existe | `PAR-13` | aucun lot sur ligne sans cousine ; C n'a aucun lot (`nadia-prof` 8.4 point 3) |
| Aucun prix alloue a un poste sans story | `PAR-16` | section 3 : un seul ecart, le poste d'arbitrage |
| `PAR-9` : egalite ou plafond a 10,5 sur 20,0 | `DEC-0120` A.151 point 3 | **question a `nadia-prof`**, non tranchee ici |

## 2. Les postes et leurs prix

Prix, capacite et instance : `nadia-prof`. Composition : `camille-po`, `DEC-0120` A.148, approuvee par l'utilisatrice (*"composition de palier 4 OK"*).

| Instance | Poste | Prix | Etat a l'ouverture |
|---|---|---|---|
| A, `marc-dev` | arbitrage prealable | 0,5 | engage, part ecrite livree (`f197dc39`) |
| | marge de rejeu | 0,5 | marge, allouee a aucun poste |
| | `LP1` `S57-BR-FRA-CE1-21`, `ce1-mots-invariables` en saisie | 1,0 | engage |
| | **Total A** | **2,0** | 1 lot |
| B, `lea-dev` | arbitrage prealable | 0,5 | engage |
| | marge de rejeu | 0,5 | marge |
| | `LP2` `S57-BR-MAT-CE1-10`, `ce1-masses-contenances` | 1,0 | engage |
| | `LP3` `S57-BR-MAT-CE1-11`, `ce1-calendrier-dates` | 1,0 | engage |
| | `LP9` `S57-BR-MAT-CE1-14-GRILLE`, grille dessinee forme (a) | 1,5 | **non engage** : conditions d'`A.149` non ecrites |
| | **Total B** | **4,5** | 3 lots |
| A', `sofia-sm` | arbitrage prealable | 0,5 | engage |
| | marge de rejeu | 0,5 | marge |
| | `LP4` `S57-BR-EMC-CE1-02`, `ce1-emc-regle-sanction` | 1,0 | engage |
| | `LP5` `S57-BR-EMC-CE1-03`, `ce1-emc-droit-devoir` | 1,0 | engage |
| | `LP6` `S57-BR-EMC-CE1-04`, `ce1-emc-interet-commun` | 1,0 | engage sous la condition de `nadia-prof` 3.3 |
| | **Total A'** | **4,0** | 3 lots |
| **Palier** | | **10,5** | 7 lots |

Arithmetique : treize lignes `instance`, `poste`, `prix`, `etat` separees par une barre verticale, lues par `awk -F` sur ce separateur le 2026-10-21 22:10 : `A 2.00` (1 lot), `Ap 4.00` (3 lots), `B 4.50` (3 lots), `total 10.50`, `engage 7.50`, `non-engage 1.50`, `marge 1.50`, 7 lots. **Le registre de prix du palier 4 est a `camille-po`** (`registre-prix-palier-3.txt` est le modele) : il n'existe pas (`ls` rend 0) ; ce recompte ne le remplace pas.

**Pourquoi `LP9` n'est pas engage.** `camille-po` le dit *"engageable"* : *"le lot ne s'ouvre (worktree, ecriture du paquet) que lorsque ces conditions sont ecrites, chacune a son proprietaire"* (`DEC-0120` A.149). Au palier 3, un lot retenu et dont une condition manquait (`p3-8`, `p3-9`) a ete ecrit non engage et compte comme premier report (`DEC-0120` A.66). Je fais de meme : `LP9` est non engage, premier report, et devient engage par un amendement date de `planning.md` quand les conditions de la section 8 (OV5) sont ecrites. **Le compte est a `camille-po`** : sa lecture (*"soldes par l'engagement"*, A.150) et la mienne different sur le mot, pas sur les faits.

## 3. Point de controle poste/story (`DEC-0086` section 5, `PAR-16`)

Moment : a l'ouverture, avant le premier daily. Titres lus par `grep -c "^#\{3,4\} .*<story>" docs/backlog/backlog.md` sur `eca79363` ; le compte compte aussi les annotations qui portent le meme titre.

| # | Poste | Story, titres au backlog | Inst. | Agent nominatif | Prix | Etat |
|---|---|---|---|---|---|---|
| p4-1 | Arbitrage prealable et lecture complete | **`S57-ARB-PALIER-4` : 0 titre** (`S57-ARB-PALIER-3` est celle du palier 3) | A, A', B | `nadia-prof` | 0,5 par instance | **engage sans story**, ecart dit, a `camille-po` (creer la story ou etendre celle du palier 3). Part ecrite livree ; reste la ligne datee *"lecture complete"* de chaque paquet avant son premier verdict |
| p4-2 | Marges de rejeu | aucune | A, A', B | aucun | 0,5 par instance | nommees, allouees a aucun poste ; pas un poste de travail |
| p4-3 | `LP1` `ce1-mots-invariables` | `S57-BR-FRA-CE1-21` (2) | A | `marc-dev` ; audit `nadia-prof` (A) | 1,0 | **engage** ; porte d'ouverture du worktree : OV1 |
| p4-4 | `LP2` `ce1-masses-contenances` | `S57-BR-MAT-CE1-10` (1) | B | `lea-dev` ; audit `nadia-prof` (B) | 1,0 | **engage** ; porte : OV1 |
| p4-5 | `LP3` `ce1-calendrier-dates` | `S57-BR-MAT-CE1-11` (1) | B | `lea-dev` ; audit `nadia-prof` (B) | 1,0 | **engage** ; porte : OV1 |
| p4-6 | `LP9` grille dessinee | `S57-BR-MAT-CE1-14-GRILLE` (6) | B | `lea-dev` ; test croise : forme `theo-archi` ; audit `nadia-prof` (B) | 1,5 | **non engage**, premier report |
| p4-7 | `LP4` `ce1-emc-regle-sanction`, paquet a ecrire dans le lot | `S57-BR-EMC-CE1-02` (1) | A' | `sofia-sm`, casquette developpeuse ; audit `nadia-prof` (A') | 1,0 | **engage** |
| p4-8 | `LP5` `ce1-emc-droit-devoir`, idem | `S57-BR-EMC-CE1-03` (1) | A' | `sofia-sm`, casquette developpeuse | 1,0 | **engage** |
| p4-9 | `LP6` `ce1-emc-interet-commun`, idem | `S57-BR-EMC-CE1-04` (1) | A' | `sofia-sm`, casquette developpeuse | 1,0 | **engage sous condition** : aucun worktree avant un verdict de `LP4` ou `LP5` sans refus |
| p4-10 | Garde de la longueur de partie, liste fermee (`F4`) | `S57-GARDE-NOMBRE-DE-MANCHES` (1) | hors `PAR` | `marc-dev` (developpeur, designe ici) ; forme `theo-archi` ; morsures `yanis-qa` | 0 | **engage**, apres la livraison de `LP1` (`RG4`, un worktree a la fois) ; la story dit *"developpeur non designe"* : c'est moi qui le designe (`DEC-0120` A.152) |

**Deux issues, jamais une troisieme** (`DEC-0086`) : `p4-6` a sa story, son instance, son prix et son agent, il lui manque des conditions : non engage, budget non libere, le 1,5 reste dans B, aucun worktree sans amendement date.

**Hors composition, non engages, aucun developpeur designe**, avec leur motif :

| Story ou geste | Titres | Motif | Ou est la decision |
|---|---|---|---|
| `S57-GARDE-CE1-PROPOSITIONS-ET-SUPPORTS` (garde generique `E3`) | 1 | **la story dit `CA4` absent et *"sans lui, la story n'entre pas en sprint"*** ; `theo-archi` ecrit que `CA4` est fourni depuis le palier 3 (`avis-theo-palier-4.md` section 4) ; **ecart non tranche** entre ces deux fichiers. Aucun test n'existe (`git ls-files` rend 0 pour `LesPropositionsEtLesTextesSupportsDeCe1TiennentLeursBornesTest`) | `camille-po` (story), `nadia-prof` (`CA4`) |
| `S57-TYP1-TEXTES-DE-GABARIT` | 2 | deuxieme ecartement ; `Q-T12` sans reponse de `theo-archi` | `camille-po` : *"une `DEC` avec le motif"* si elle n'entre pas (`DEC-0120` A.150) |
| `S57-MESSAGE-PARTIE-PERDUE` | 1 | deuxieme ecartement ; mesure des lignes a 744 et 1200 non faite, **a moi** : elle demande un jar, que je ne lance pas sous cette casquette | `camille-po` (`DEC`) ; moi (la mesure, quand un jar du palier existe) |
| Commentaires de `src/` : ecarts 33, 40 a 44, part code des ecarts 37 et 38 | 0 | **aucune story** (`grep` rend 0 sur le backlog) ; `avis-theo-palier-4.md` section 4 me les attribue (*"`sofia-sm` (engagement)"*) ; la relecture du `relecteur-pedagogique` precede la fusion (A-10) | `camille-po` (story), puis moi |
| Extension de `verifier-chemins.sh` (ecart 39) | 0 | un commit de `theo-archi` avec l'amendement d'`ADR-0010` et son cas de test ; non fait | `theo-archi` |
| `LP7` `MAT-CE1-15`, `LP8` `FRA-CE1-03` | - | deuxieme ecartement ecrit (`DEC-0120` A.150), premiers de la file du palier 5 | `camille-po` |

## 4. Affectation nominative des developpeurs

Regles ecrites reprises : `RG1` (`lea-dev` n'a aucun lot de francais CE1), `RG2` (developpeurs distincts sur des instances distinctes), `RG3` (`marc-dev` pour un lot a texte support), `RG4` (un worktree a la fois par developpeur), `RG5` (ma casquette developpeuse : declaree, aucun arbitrage de mon propre obstacle), `RG6` (l'agent de la story est celui que la story nomme) ; source : `composition-palier-3.md` section 4.1.

| Developpeur | Postes, dans cet ordre | Deduit de | Etat |
|---|---|---|---|
| `marc-dev` | p4-3 (`LP1`), puis p4-10 | `RG1`, `RG3` ; `QP6` | **deduit** |
| `lea-dev` | p4-4 (`LP2`), p4-5 (`LP3`), puis p4-6 (`LP9`) | `QP6` : *"lea dev pour B"* ; `LP9` en dernier (`DEC-0120` A.148) | **decide par ses mots** |
| `sofia-sm`, casquette developpeuse | p4-7 (`LP4`), p4-8 (`LP5`), puis p4-9 (`LP6`) | `QP6` : *"sofia pour A'"* | **decide par ses mots** ; regle des deux casquettes, section 5 |

`RG2` est tenue : trois instances, trois developpeurs. Aucun ecart a dire, contrairement a `composition-palier-3.md` section 12.3.

**A-5 de `retro-palier-3.md`, applique tel qu'ecrit.** *"Je ne compose pas un poste d'un developpeur dont le rapport du palier precedent manque."* `ls squad/sprints/s57-*/rapport-*palier-3.md` rend `rapport-sofia-dev-palier-3.md` et `rapport-qualite-palier-3.md` : **aucun rapport de `marc-dev` ni de `lea-dev`** (`K7`). Je ne peux pas les ecrire a leur place. **Lecture que je prends, dite** : A-5 est une porte d'ouverture de worktree, pas une porte d'engagement : p4-3, p4-4, p4-5 et p4-10 sont engages, mais **aucun worktree de `marc-dev` ni de `lea-dev` ne s'ouvre avant que son `rapport-<developpeur>-palier-3.md` existe sur `round-two`** (jobs de `ci.yml` lances, ligne `TRV-CE1-5` par paquet, racine du scratchpad). Une porte d'engagement aurait fait compter un premier report a quatre postes pour un manque que la squad comble en une tache. **Si l'utilisatrice veut la lecture stricte, elle l'ecrit** ; je ne tranche pas mon propre controle.

## 5. Ordre, file de fusion, et qui fusionne

### 5.1 Ordre

**Produit** (`camille-po`, `DEC-0120` A.148, A.35) : mathematiques, francais, EMC. B : `LP2`, `LP3`, `LP9` en dernier (aucun lot ne l'attend). A : `LP1`. A' : `LP4`, `LP5`, `LP6`. **Planning** : le rang est l'ordre de demarrage ; l'ordre de fusion est *"premier pret, premier fusionne"* (`avis-theo-palier-4.md` 3.3). Je l'engage comme ordre de planning, pas comme decision de produit ni d'architecture.

### 5.2 Fusion : les regles (RF)

Elles reprennent la section 3.3 de `avis-theo-palier-4.md`, que `theo-archi` laisse a `sofia-sm` pour la mecanique (*"La mecanique exacte est a `sofia-sm`"*), et les actions A-1, A-6, A-7 et A-8 de `retro-palier-3.md`. **Mesure de reference** : un `verify` complet dure 11 min 39 s sur arbre propre, 2855 tests (`avis-theo-palier-4.md` 3.5, mesure de `theo-archi`, **pas la mienne** ; le total apres fusion, je l'etablis moi-meme sur l'arbre fusionne et je dis d'ou vient l'ecart).

| Code | Regle |
|---|---|
| RF1 | **Un seul `verify` complet a la fois sur le poste, tous agents confondus.** Jeton : le repertoire `s57-p4-jeton-verify`, nomme dans la table section 6, cree par `mkdir` (atomique) a la racine du scratchpad, seule exception a l'interdiction d'ecrire a la racine ; l'agent y ecrit un fichier `<agent>-<heure>.txt`, lance `bash outils/mvn-jdk21.sh -B clean verify` a la racine d'un worktree propre, puis supprime le repertoire. Si `mkdir` echoue, il attend (boucle `until`) et ne lance rien. **Mecanique non encore mise a l'epreuve** : sa premiere utilisation est sa mesure. Un jeton dont le proprietaire est arrete est retire par moi (RF9) |
| RF2 | **Toute autre mesure est partielle** : `-pl <module> -am`, perimetre nomme a cote du chiffre (`CLAUDE.md`, troisieme exigence de build) |
| RF3 | **Une seule fusion a la fois**, premier pret premier fusionne. Resolution des fichiers partages (`catalogue.yaml`, inventaire de `TexteAccentueExposeParLePortEntrantTest`, `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`, `REGISTRE-identifiants.md`) : par ajout des deux cotes, **jamais une assertion affaiblie** ; si deux tests se contredisent apres fusion, l'un dit faux et je trouve lequel |
| RF4 | **Un seul `verify` complet par fusion.** La branche du lot fusionne le sommet courant de `round-two` (fusion, jamais rebase, jamais par un agent sur `round-two`) dans son worktree **avant** son `verify`. Apres la fusion dans `round-two`, `git diff --quiet <commit verifie> <commit de fusion>` : sortie 0, la fusion n'est pas rejugee ; sortie non nulle, elle l'est, au jeton (RF1). La commande et son code de sortie s'ecrivent a cote de la phrase (A-8) |
| RF5 | **Les lots d'une meme instance partent du sommet qui contient le precedent** : `LP5` apres la fusion de `LP4`, `LP3` apres celle de `LP2`, `LP9` apres celle de `LP3`. Les trois lots d'A' touchent chacun `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`, troisieme fichier partage, neuf au palier 4 |
| RF6 | **Les postes sans fichier partage passent en tete, groupes** : p4-10 ne touche ni le catalogue ni l'inventaire |
| RF7 | **Le `verify` se lance sur un worktree ou une copie propres**, jamais dans le checkout partage (fichiers non suivis des autres agents lus par les gardes de `bootstrap`) |
| RF8 | **Allouer avant de fusionner** (A-6) : avant de fusionner une branche dont le diff ecrit un identifiant, je lance `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (`-pl bootstrap -am`) sur l'arbre fusionne, dans un worktree detache. Rouge : je route l'allocation a `theo-archi` et **j'attends son commit** avant de fusionner |
| RF9 | **Arret d'un agent** (A-1) : une ligne datee dans `planning.md` **au moment ou il survient** : qui, quand, ce que la branche portait (`git log --oneline <base>..<tete>`, `git status --short` du worktree). Les faits que seule l'utilisatrice a (arrets de s56 et du palier 3, `Q-U6` de `DEC-0116`) : **faits non fournis**, je n'ecris ni heure ni motif. **Reprise** : sur le meme worktree et la meme branche apres relecture de ces deux commandes ; un worktree neuf seulement si l'ancien est inutilisable, sur la meme branche, apres que son `git status --short` est vide ou que son diff est sauve dans le repertoire de l'agent ; le jeton du proprietaire arrete est retire par moi |
| RF10 | **Lignee avant chaque fusion** : `bash outils/verifier-lignee.sh`, lancee dans le worktree de la branche, sortie et code ecrits, meme si l'agent dit l'avoir faite. Les agents ne rebasent jamais |
| RF11 | **Engagements hors tableau** (A-7) : chaque story livree dans le palier, composition ou non, a une ligne dans un amendement de `planning.md` **avant le commit de sa fusion**. A chaque fusion je relis la liste *"hors composition, non engages"* de la section 3 |
| RF12 | **Poids des dessins** (`DEC-0120` A.149) : `POIDS_TOTAL_MAXIMUM` se deplace dans le commit qui installe les octets, sur la valeur mesuree. `LP9` est le seul lot du palier qui installe des dessins ; si un autre le fait, `LP9` fusionne en premier et le second recalcule la constante sur l'arbre fusionne. **Je relis lot par lot** les paquets d'EMC a ecrire, que `camille-po` n'a pas pu verifier |
| RF13 | **Jeu de `nadia-prof`** : elle joue le jar du commit qui sera fusionne et le nomme dans son verdict. Avant la fusion, `git diff --name-only <commit joue> <commit de fusion>` ; si la liste nomme le paquet du lot, ses dessins, un gabarit, une feuille de style ou du code sous `src/main`, **elle decide du rejeu**, sur la marge de son instance. Une entree d'un autre lot au catalogue ne rejoue rien |

**Comptes de fusion.** `catalogue.yaml` : 157 entrees aujourd'hui (`grep -c "^  - id:"`, 2026-10-21), **164 apres les sept lots** ; `grep -c "paquet: <nom>$"` vaut 0 aujourd'hui pour `ce1-mots-invariables`, `ce1-masses-contenances`, `ce1-calendrier-dates`, `ce1-deplacements-quadrillage` (le paquet existant que reecrit `LP9`), `ce1-emc-regle-sanction`, `ce1-emc-droit-devoir`, `ce1-emc-interet-commun`, et 1 apres chaque fusion. **Les trois paquets d'EMC sont des fichiers neufs** (`ls` rend 0). **Une ligne par fusion dans `cloture-palier-4.md`** : SHA, compte d'avant, entrees fusionnees, compte d'apres.

**Ce que la file coute**, calcule par `theo-archi` et non mesure (`avis-theo-palier-4.md` 3.3) : sept lots, `verify` 1 h 19 a 1 h 29 ; job `build` entier 2 h 25 a 2 h 41. Je ne l'ai pas remesure.

### 5.3 Qui fusionne pendant que je suis developpeuse de A'

Ma regle du 2026-10-21 (`cloture-palier-3.md`, amendement 20:59, *"Proposition de `sofia-sm` pour ses deux casquettes"*), qu'aucun fichier du depot ne contredit et dont je fais ici la regle du palier :

1. **Un lot de A' commence et finit sous la seule casquette de developpeuse.** Pendant un lot je ne fusionne rien, je ne juge aucune lignee, je n'anime aucun amendement de `planning.md`. Le seul geste de Scrum Master que je ne retarde pas : ecrire un arret d'agent (RF9).
2. **Personne d'autre ne fusionne.** Les fusions de A et de B, le routage et les amendements se font **entre deux lots de A'**, sous ma casquette de Scrum Master, lancee comme tache distincte par l'agent lanceur.
3. **Ma propre branche n'est pas jugee par moi.** La lignee de la branche de A' est lue par `theo-archi` (`git merge-base --is-ancestor`, resultat ecrit), le rapport qualite est celui de `yanis-qa` seul, le Done pedagogique est a `nadia-prof`. **Je ne fusionne la branche de A' qu'apres ces deux ecrits sur le disque.** Le total de tests apres un lot de A' est mesure par `yanis-qa`.
4. **Consequence, a dire** : pendant un lot de A', une branche prete de `marc-dev` ou de `lea-dev` attend ; et comme `RF5` interdit a `lea-dev` de couper `LP3` avant la fusion de `LP2`, **la file de B avance au rythme de mes retours sous la casquette de Scrum Master**. Je demande a l'agent lanceur de **ne pas lancer un lot de A' tant qu'une branche prete attend sa fusion** : un lot de A' vient apres la file, pas devant. Ni la charge de `lea-dev` ni la mienne ne sont mesurees (le palier 3 n'a mesure ni l'une ni l'autre).
5. **Reste a l'utilisatrice** : si elle juge que le controle de ma branche (lignee par `theo-archi`, qualite par `yanis-qa` seul, pedagogie par `nadia-prof`) ne suffit pas pour une branche dont l'auteure fusionne, elle l'ecrit.

## 6. Table d'isolation du palier 4 (condition (d) de `D2`)

`I1` a `I3` du palier 1 s'appliquent **sans changement** : un repertoire de scratchpad par agent, par instance et par palier, a un seul niveau sous la racine, nomme `s57-p4-<agent>-<instance>`, jamais reutilise d'un palier a l'autre ; un port de jeu par agent, par instance et par palier ; avant chaque lancement le port est libre (`netstat -ano` filtre sur `":<port> "`), apres, la ligne *"Tomcat started on port <port>"* et le PID sont lus dans **son** journal, arret par PID, jamais par nom d'image. Port de debogage de Chrome : port de jeu plus 700. **Remplace** la table du palier 3.

**Etat a l'ecriture, lu le 2026-10-21 22:10** : `netstat -ano` filtre sur les ports 185xx et 192xx rend 0 ligne. Racine du scratchpad de la session, `find <racine> -maxdepth 1 -mindepth 1 -type f` : **72 fichiers** ; `-type d` : **28 repertoires** (29 apres la creation de mon propre `s57-p4-sofia-sm-t`), dont un seul de la table du palier 4, `s57-p4-camille-po-t`. Aucune de ces valeurs n'est une garde : c'est un instantane (A-4).

**Les ports sont ceux du palier 3, par ligne** : aucun numero neuf. Il y a **une ligne par agent lance**, `ines-design`, `yanis-qa`, `nadia-prof`, `camille-po` et `relecteur-pedagogique` compris ; **`ines-design` a deux lignes**, reprises du palier 2, parce qu'au palier 3 elle a servi sur sept ports qu'aucune table ne donnait (`cloture-palier-3.md` section 10).

| Agent | Instance | Postes | Repertoire de scratchpad | Port de jeu | Port de debogage |
|---|---|---|---|---|---|
| `nadia-prof` | A | lecture et audit de p4-3 ; **jeu de la saisie de `LP1`** | `s57-p4-nadia-prof-a` | 18511 | 19211 |
| `nadia-prof` | A' | audits de p4-7, p4-8, p4-9 | `s57-p4-nadia-prof-ap` | 18512 | 19212 |
| `nadia-prof` | B | audits de p4-4, p4-5, p4-6 | `s57-p4-nadia-prof-b` | 18513 | 19213 |
| `marc-dev` | A | p4-3, puis p4-10 | `s57-p4-marc-dev-a` | 18521 | 19221 |
| `lea-dev` | B | p4-4, puis p4-5, puis p4-6 | `s57-p4-lea-dev-b` | 18531 | 19231 |
| `sofia-sm` (developpeuse) | A' | p4-7, puis p4-8, puis p4-9 | `s57-p4-sofia-dev-ap` | 18532 | 19232 |
| `yanis-qa` | transverse | mutations, rapport qualite, total de tests de A' | `s57-p4-yanis-qa-t` | 18541 | 19241 |
| `theo-archi` | transverse | lecture de lignee de A', forme de p4-10 et du test croise de p4-6, confirmation ArchUnit | `s57-p4-theo-archi-t` | 18542 | 19242 |
| `ines-design` | transverse | releves d'ecran de chaque lot ; glyphes et mesure de p4-6 | `s57-p4-ines-design-t` | 18543 | 19243 |
| `ines-design` | transverse, second jar | jar de base, mesure appariee | `s57-p4-ines-design-t2` | 18544 | 19244 |
| `camille-po`, `relecteur-pedagogique`, `sofia-sm` (Scrum Master) | transverse | aucun jar | `s57-p4-camille-po-t`, `s57-p4-relecteur-t`, `s57-p4-sofia-sm-t` | aucun | aucun |
| jeton de `verify` (RF1) | - | exclusion mutuelle, pas un agent | `s57-p4-jeton-verify` | aucun | aucun |

**Un agent transverse n'a qu'un lancement vivant a la fois** ; un second lancement concurrent recoit un repertoire `-t2` et un port de la table avant son lancement, par ligne ajoutee a `planning.md`. Un agent a plusieurs lots (`lea-dev`, `sofia-sm`, `marc-dev`) a **une seule ligne et ne l'entrelace pas** : un worktree a la fois. Si `nadia-prof` ou `ines-design` doivent servir deux jars en meme temps, la ligne s'ajoute avant le lancement.

**Collisions evitees** : 18522 n'est donne a personne (19222 est fixe en dur par `pilote-q7.mjs`) ; 19333 et 19334 (`pilote-accueil.mjs`, `capture-colle.mjs`) sont fixes en dur, donnes a personne ; `outils/mesurer-accueil.py` ouvre Chrome sur un port aleatoire, a lancer seul. Les 18671 a 18721 de `ines-design` au palier 3 ne sont plus a elle.

**Consigne ecrite, a repeter en tete du message de lancement de chaque agent du palier 4.** Ce n'est ni un moyen de garde ni le crochet : le crochet est `Q4` de `theo-archi`, sans reponse de l'utilisatrice dans le depot ; elle n'a pas tenu au palier 3 (69 fichiers a la racine).

> 1. Ton repertoire de scratchpad est celui de la table du palier 4 de `composition-palier-4.md`, copie lettre pour lettre. Ta premiere commande le cree (`mkdir -p`). Tu n'en utilises aucun autre.
> 2. **Tout ce que tu ecris, tu l'ecris dans ce repertoire, quel que soit l'outil** : l'outil d'ecriture de fichier, chaque redirection du shell (`>`, `>>`, `2>`, `&>`, `tee`), chaque journal de build ou de jar, chaque profil de Chrome, chaque capture, chaque fichier de PID, chaque sortie d'un script Python ou Node.
> 3. **Chemin absolu, toujours, construit a partir de ton repertoire.** Le repertoire courant est reinitialise entre deux commandes.
> 4. Trois endroits sont interdits pour tes fichiers : la racine du scratchpad (sauf le repertoire de jeton de RF1), le repertoire parent des worktrees, le repertoire temporaire du systeme.
> 5. Tu ne deplaces, ne renommes et ne supprimes aucun fichier que tu n'as pas cree.
> 6. Avant de rendre la main : tu listes la racine du scratchpad et le repertoire parent des worktrees ; tout fichier que tu y as ecrit est nomme dans ton rapport, puis deplace dans ton repertoire. Ton rapport porte une ligne *"racine : propre"* ou la liste des noms, **avec la commande et sa sortie**. Tu ne declares jamais propre une racine que tu n'as pas listee.

**Mesure de la racine** (`nadia-prof` 8.3 point 2) : je ne demande pas un autre mesureur ; **la commande et sa sortie sont collees dans l'amendement de `planning.md` de chaque etat des postes (A-4) et dans `cloture-palier-4.md`**. Je les mesure dans le scratchpad ou j'ecris moi-meme : c'est un conflit d'interet dit, et `nadia-prof` les lit.

**Branches et worktrees.** Une branche et un worktree dedies par poste, voisins du checkout (`recreo-<branche>`), sans rebase, sans push, sans fusion par les agents ; chaque agent note `git log -1 --format=%h` dans son premier message ; **je verifie la lignee avant chaque fusion**. Aucune de ces branches n'existe (`git branch --list 's57-*'` rend 0).

| Poste | Branche | Worktree | S'ouvre |
|---|---|---|---|
| p4-3 | `s57-marc-fra-ce1-21` | `../recreo-s57-marc-fra-ce1-21` | apres la fusion de l'amendement d'ouverture et OV1 |
| p4-10 | `s57-marc-garde-nombre-de-manches` | `../recreo-s57-marc-garde-nombre-de-manches` | apres la livraison de p4-3 |
| p4-4 | `s57-lea-mat-ce1-10` | `../recreo-s57-lea-mat-ce1-10` | apres l'amendement d'ouverture et OV1 |
| p4-5 | `s57-lea-mat-ce1-11` | `../recreo-s57-lea-mat-ce1-11` | apres la fusion de p4-4 |
| p4-6 | `s57-lea-mat-ce1-14` | `../recreo-s57-lea-mat-ce1-14` | apres la fusion de p4-5 **et** un amendement date qui l'engage |
| p4-7 | `s57-sofia-emc-ce1-02` | `../recreo-s57-sofia-emc-ce1-02` | apres l'amendement d'ouverture |
| p4-8 | `s57-sofia-emc-ce1-03` | `../recreo-s57-sofia-emc-ce1-03` | apres la fusion de p4-7 |
| p4-9 | `s57-sofia-emc-ce1-04` | `../recreo-s57-sofia-emc-ce1-04` | apres la fusion de p4-8 **et** un verdict de `LP4` ou `LP5` sans refus |
| audits de `nadia-prof` | `s57-nadia-p4-a`, `-ap`, `-b` | `../recreo-s57-nadia-p4-a`, `-ap`, `-b` | au fil des livraisons |
| cloture de palier | `s57-sofia-cloture-p4` | `../recreo-s57-sofia-cloture-p4` | a la cloture |

## 7. Compte des reports

Regles de `DEC-0120` A.66 a A.73, comme aux paliers 1 a 3 : un report est un poste retenu par la composition et non engage a l'ouverture ; un poste glisse au plus une fois ; le troisieme report est interdit.

| Poste | Etat a l'ouverture | Compte |
|---|---|---|
| `LP1` a `LP5` | engages | pas de report ; premier ecartement du palier 3 solde par l'engagement (`DEC-0120` A.150) |
| `LP6` | engage sous condition de `nadia-prof` | s'il ne part pas : deuxieme ecartement, report compte a la cloture ; un troisieme demande une `DEC` |
| `LP9` | **non engage** | **premier report**, ecrit ici ; deuxieme ecartement si ses conditions ne tiennent pas a la cloture ; un troisieme demande une `DEC` |
| `LP7`, `LP8` | hors composition | deuxieme ecartement ecrit par `camille-po` (A.150) |
| p4-10 | engage | premiere inscription dans une composition, hors `PAR` |
| trois stories hors `PAR` (`TYP1`, `MESSAGE-PARTIE-PERDUE`, `PROPOSITIONS-ET-SUPPORTS`) | non engagees | **troisieme non-entree pour deux d'entre elles** : la `DEC` avec le motif est a `camille-po` (A.150) ; ce fichier ecrit les motifs de la section 3 |

## 8. Ce qui doit exister avant l'ouverture, et ce qui n'existe pas

Les quatre conditions de `D2` sont dans l'amendement de `planning.md`. **Ce tableau est ce que `D2` ne demande pas**, nomme pour qu'aucun point ne passe pour tenu.

| Code | Exigence | Source | Etat | Ce que ca bloque |
|---|---|---|---|---|
| OV1 | `rapport-marc-dev-palier-3.md` et `rapport-lea-dev-palier-3.md` | A-5 de `retro-palier-3.md`, `K7` | **non tenu** : `ls` rend 0 pour les deux | le worktree de `marc-dev` et ceux de `lea-dev` (section 4) |
| OV2 | Garde generique `E3`, `LesPropositionsEtLesTextesSupportsDeCe1TiennentLeursBornesTest` | `avis-theo-palier-4.md` section 4 : *"avant, recommande, non bloquant"* | **non tenu** : aucun test, aucun developpeur designe, story en ecart avec `theo-archi` sur `CA4` (section 3) | rien a l'ouverture ; **ecrit pendant, un paquet fusionne avant lui peut rendre rouge son commit** (`avis-theo-palier-4.md` section 4) |
| OV3 | Ligne de rejeu de `-07` dans `registre-prix-palier-3.txt` | `arbitrage-nadia-palier-4.md` 5.5 | **non tenu** : le fichier porte toujours `A`, `marge de rejeu`, `0.50`, `marge` (dernier commit `53dc13ff`) | rien a l'engagement ; l'arbitrage de `nadia-prof` le range *"avant l'ouverture"*. A `camille-po` |
| OV4 | Story du poste d'arbitrage (`S57-ARB-PALIER-4`) et registre de prix du palier 4 | `PAR-16` ; modele du palier 3 | **non tenu** : 0 titre, 0 fichier | p4-1 engage sans story, ecart dit |
| OV5 | Conditions de `LP9` : arbitrage de `nadia-prof` (prix, `PAR-13` en forme (a), *"ne la nomme pas par sa coordonnee seule"*, taille de grille, nombre d'items) ; glyphes et mesure d'`ines-design` ; **taille de grille ouverte** (`DEC-0120` A.151) | `DEC-0120` A.149 | **non tenu** : aucune des trois n'est ecrite | p4-6 (non engage) |
| OV6 | Extension de `verifier-chemins.sh` (ecart 39) | `avis-theo-palier-4.md` section 5 | **non tenu** : `git grep -c -E '[\\/][A-Za-z]--[0-9A-Za-z]' HEAD` rend 2 lignes dans `releve-relecteur-cloture-palier-3.md` ; le garde ne les voit pas | les captures de trois instances ; a `theo-archi` |
| OV7 | Commentaires de `src/` (ecarts 33, 40 a 44, part code des 37 et 38) relus avant leur fusion | A-10 de `retro-palier-3.md` | **non tenu** : aucune story, aucun commit | rien a l'ouverture |
| OV8 | Lecture par `nadia-prof` des racines de scratchpad des paliers 2 et 3, et annotation de `TRV-CE1-1` | `arbitrage-nadia-palier-4.md` 1 et 8.2 | **non tenu** : aucun fichier | rien a l'ouverture ; condition de `FRA-CE1-27` et `-29`, hors palier |
| OV9 | Lecture par `nadia-prof` de l'amendement de `S57-BR-FRA-CE1-21` comme tenant sa condition 5.2 | `arbitrage-nadia-palier-4.md` 5.2 | **non ecrite** : l'amendement existe (`eca79363`), sa lecture est a elle | je ne la presume pas ; elle la dit dans sa lecture complete de `LP1` |
| OV10 | Amendement de `CLAUDE.md` sur le CE1 (`DEC-0123` 5.4) | `cloture-palier-3.md` section 14 | **non fait** : fichier que tous les agents lisent, je ne l'ecris pas sans l'utilisatrice | rien |

**Tenu** : `cloture-palier-3.md` et `retro-palier-3.md` sur `round-two` ; arbitrage de `nadia-prof` ; avis de `theo-archi` ; composition et approbation ; amendement de `S57-BR-FRA-CE1-21` ; `E5` ferme ; `QP3`, `QP5`, `QP6` repondues.

## 9. A decider, et routage

Je classe, je ne reponds a aucune.

| Code | Question | Qui decide | Ce que le planning engage en attendant |
|---|---|---|---|
| AP1 | Lecture stricte d'A-5 (porte d'engagement, quatre premiers reports) ou lecture retenue ici (porte d'ouverture de worktree) | utilisatrice | la lecture retenue, dite |
| AP2 | Compte de `LP9` : premier report (ma lecture) ou solde par l'engagement (`camille-po`) | `camille-po` | premier report ecrit |
| AP3 | `PAR-9` a 10,5 sur 20,0 ; une ligne de `PAR-8` pour quatre instances | `nadia-prof` | somme des lignes, decision de l'utilisatrice |
| AP4 | Story d'`E3` : `CA4` fourni (`theo-archi`) ou absent (story) | `camille-po`, `nadia-prof` | non engagee |
| AP5 | Un `DEC` pour les deux stories hors `PAR` a leur troisieme non-entree | `camille-po` | non engagees, motifs section 3 |
| AP6 | Les quatre decisions de `cloture-palier-2.md` section 13 (`Q4`, `node` et Python dans `ci.yml`, `TMPDIR`, retro des erreurs) | utilisatrice | inchangees : je n'ai modifie ni `ci.yml` ni `outils/mvn-jdk21-test.sh` |

## 10. Ce que ce fichier ne fait pas

- N'ouvre aucun worktree, ne cree aucune branche, n'ecrit aucun code, ne lance aucun agent, ne fusionne rien, ne pousse rien.
- Ne modifie ni un prix, ni un lot, ni une instance, ni un critere de story, ni la matrice (152 / 202).
- Ne prononce aucun Done, ne juge aucun contenu. Ne decide ni le produit, ni l'architecture, ni la pedagogie.
- Ne cree ni n'amende aucune `ADR` ni aucune `DEC`. L'amendement de `DEC-0120` qui porte l'etat d'ouverture est a `camille-po`.
- N'ecrit aucun identifiant de condition ou de reserve : `CO1` a `CO8`, `RF1` a `RF13`, `OV1` a `OV10`, `AP1` a `AP6` sont des reperes de ce fichier.

## 11. Jobs de `ci.yml` lances pour ce fichier

Voir l'amendement de `planning.md` du meme jour, section "Jobs" : les memes commandes couvrent les trois fichiers.

## 12. Amendement du 2026-10-21 22:31 (UTC+02:00) : reponse de l'utilisatrice a AP1

- **Ecrit par** : l'agent lanceur, qui cite les mots de l'utilisatrice (README section 2 bis : un enonce cite mot pour mot avec sa date vaut forme 2 de DEC-0062). Le fichier est celui de sofia-sm : elle n'a pas ecrit cet amendement, et peut l'annoter.
- **La question posee** : *"AP1 : lire strictement la regle A-5 (rapports de palier 3 de Marc et Lea avant tout worktree), ou accepter qu'ils ouvrent avec le rapport ecrit pendant le lot ?"*
- **La reponse** (2026-10-21, mots cites tels quels) : *"note pour AP1 qu'on l'ouvre et que le rapport est écris pendant le lot, ne lance rien de plus"*.
- **Ce que ca decide** : AP1 est tranchee. Les worktrees de marc-dev et de lea-dev s'ouvrent sans que leur rapport du palier 3 existe, et le rapport du palier 3 s'ecrit pendant leur lot. Ni la lecture stricte (porte d'engagement) ni la lecture retenue plus haut (porte d'ouverture de worktree) ne s'appliquent a OV1 : celle-ci est levee comme condition d'ouverture. A-5 de retro-palier-3.md reste ecrit tel quel ; ce fichier dit qu'il ne conditionne pas ces deux ouvertures.
- **Ce que ca ne decide pas** : la fin du rapport (a quel moment il est du : avant la fusion du lot, ou avant la cloture du palier) n'est pas dite par l'utilisatrice ; ni la taille de la grille de MAT-CE1-14, ni l'engagement de LP9, ni LP6.
- **"Ne lance rien de plus"** : au moment de cet amendement, aucun lot, aucun agent et aucun worktree du palier 4 n'est lance. Le palier est ouvert sur le papier (planning.md) et rien d'autre.
