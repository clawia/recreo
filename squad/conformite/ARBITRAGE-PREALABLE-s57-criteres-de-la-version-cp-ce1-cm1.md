# Arbitrage prealable de s57 - mes criteres pour la version CP-CE1-CM1 : ce qui fait compter une ligne
# "en cours", le CE1 hors francais et mathematiques, `S54-OBS-SCI-CONSIGNE`, `A10` et la borne, le rang B

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-13, 12:33 (UTC+02:00), lu par `date`.
- **Arbre** : `round-two` a `59c4b56f`, branche `criteres-nadia`, arbre propre avant ecriture (`git status --short` vide).
- **Type** : arbitrage prealable, hors sprint (s57 n'est pas ouvert : ni `planning.md`, ni composition). Il ecrit ce que
  `DEC-0120` (A.5, A.17) route a `nadia-prof` et ce que l'utilisatrice me demande ; il ne prend aucune decision de
  `camille-po` ni de `sofia-sm`, et les nomme en section 7.
- **Statut de mes decisions** : elles s'appliquent des ce commit. Celles qui reposent sur les mots de l'utilisatrice le
  disent ; les questions qui restent sont en section 8.
- **Lien** : `DEC-0120` (sections 1 a 4, A.1 a A.17) ; `DEC-0062` (forme 2) ; `DEC-0009` ; `DEC-0114` (A.25, A.26) ;
  `squad/programme/MATRICE-couverture.md` (section "Comment la lire", statuts) ; mes arbitrages
  `ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md` (formes (a), (b), (c)),
  `ARBITRAGE-PREALABLE-s46-trois-instances-refusees-le-vivier-borne-avant-la-capacite.md` (section 5),
  `ARBITRAGE-PREALABLE-s48-francais-ce1.md` (section 7), `ARBITRAGE-PREALABLE-s51-borne-a-trois-instances.md` (section 2),
  `ARBITRAGE-PREALABLE-s52-instances-ce1.md` (sections 2, 4.2, 5, 6), `ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md`
  (sections 1.1, 6), `ARBITRAGE-COMPLEMENT-s55-instance-a.md` (sections 2.5, 3) ;
  `squad/sprints/s54-branchement-des-premiers-paquets-et-reports-de-s53/arbitrage-nadia-c.md` (section 3.4) ;
  `squad/sprints/s56-paquet-art-cp-10-et-francais-ce1-13/retro.md`, `cloture-nadia.md`.
- **Fichiers ecrits avec ce document** : `squad/programme/questionner-le-monde/competences-ce1.md` et
  `squad/programme/enseignement-moral-et-civique/competences-ce1.md` (section 2). La matrice n'est pas touchee.
- **Audit degrade, et je le declare** : aucun increment n'est en jeu, je n'ai rien joue pour ce document. Ce que j'ai fait a
  la place est en section 0.

## Les mots de l'utilisatrice (forme 2 de `DEC-0062`)

Reponses de l'utilisatrice du 2026-10-13, telles que l'agent qui m'a lance me les transmet. **Je ne les ai pas lues de sa
main et je ne peux pas certifier qu'elles sont mot pour mot** (`squad/README.md` section 2 bis : un enonce qu'on ne peut
pas citer exactement se signale comme tel). Elles repondent a `Q-U8` de `DEC-0120` (A.6, A.10) :

1. *"pas de date de sortie"* - `Q-U8` (b).
2. *"la version compte toutes les lignes de la matrice dont le jeu est jouable, meme en cours"* - `Q-U8` (c).
3. *"pour le CE1, ouvrir d'autres matieres que francais et maths"* - `Q-U8` (d).
4. Elle me demande mes criteres.

**Ce qu'ils fondent** : que la version ne vise pas une date, qu'une ligne `en cours` peut compter dans la version, que le
CE1 s'ouvre a d'autres matieres. **Ce qu'ils ne fondent pas** : ce que "jouable" veut dire (section 1), quelles matieres
s'ouvrent et lesquelles ont un contenu propre au CE1 (section 2), ni ma borne (section 4).

## Resume

| # | Point | Decision | Section |
|---|---|---|---|
| D1 | Jeu "jouable" pour compter une ligne `en cours` | **Cinq criteres cumulatifs**, dont un verdict ecrit de moi, non refuse, sur le jar qui sert l'entree. Un jeu branche que je n'ai pas joue ne compte pas | 1 |
| D2 | Done pedagogique d'une version | **Je n'en prononce pas** : le Done se prononce par jeu et par ligne. Une ligne `en cours` dans la version reste `en cours`, avec sa mention de couverture partielle | 1.4 |
| D3 | CE1, autres matieres | **Ouvertes et ecrites** : questionner le monde (12 lignes) et EMC (5 lignes, 4 atteignables). **Reportees** : arts plastiques, langues vivantes. **Refusees** : education musicale, EPS | 2 |
| D4 | `S54-OBS-SCI-CONSIGNE` | **Criteres ecrits** (cinq), prix de mon rejeu 0,25, instance B. La placer ou la retirer reste a `camille-po` | 3 |
| D5 | `A10` (verification de s54) | **Condition (ii) non remplie pour s54** : une occurrence de la forme (c), l'instance C en cause | 4.1 |
| D6 | `PAR-2` pour A' | **Accord pour s57 seul**, a six conditions | 4.2 |
| D7 | Borne (`PAR-8`) | **O1 : 8,0 pour trois instances.** O2 n'est pas derivable aujourd'hui. Repartition en 4.3 | 4.3 |
| D8 | Rang B | Ecriture possible : 8 lignes sur 10, dont 5 sous condition d'un materiau ; non pour `HDA-CM1-06` et `ART-CM1-07` aujourd'hui. **Aucun prix mesure** sur les dix | 5 |

---

## 0. Ce que j'ai lu et mesure

| Mesure | Commande ou fichier | Rendu |
|---|---|---|
| Matrice, par matiere et niveau | `awk` de mon arbitrage de s51 (section 2.2, filtre `NF>=8`), cle `$2"/"$3`, sur `59c4b56f` | `couvert-valide` 142, `en cours` 4, `non couvert` 36, `non couvrable` 76 ; au CP : questionner le monde 12 / 3 / 1, EMC 11 / 1 / 2, arts plastiques 3 / 1 / 7, musique 2 / 0 / 9, EPS 4 / 0 / 7, anglais 1 / 0 / 5 (`cv` / `nc` / `ncb`) |
| Les quatre lignes `en cours` | meme `awk`, statut `en cours` ; `grep -c "id: bandit-manchot-<jeu>$"` sur `catalogue.yaml` | `MAT-CP-01`, `MAT-CP-10`, `MAT-CE1-02`, `MAT-CE1-09` : chacune a un jeu au catalogue (1, 1, 1, 1) et un verdict ecrit de moi, limite a la part servie |
| Paquets de CE1 sans entree de catalogue | `comm -23` des fichiers de `contenu/` et des `paquet:` de `catalogue.yaml`, filtre `ce1-`, puis `lignesDeReferentiel` de chacun | 17 ; dont `G4` textuels : `ce1-nombres-jusqu-a-1000` (`MAT-CE1-01`), `ce1-masses-contenances` (`-10`), `ce1-calendrier-dates` (`-11`), `ce1-figures-planes` (`-15`) |
| s54, perimetre | `git rev-list --no-merges --count ccd47543..4b005324` (planning a review) ; `--merges` | 35 ; 25 |
| s54, forme (a) | `git show --numstat` de `catalogue.yaml` sur chaque commit de la plage ; `git log --name-only` sur `contenu/` | six commits `6 0`, aucune consigne modifiee ensuite ; trois paquets modifies apres leur entree, consigne vraie (4.1) |
| s54, forme (b) | `awk` de la matrice sur `git show 4b005324:` | 137 / 4 / 41 / 76 : le 137 / 182 de la review tient |
| s54, forme (c) | transcriptions de sous-agent hors du depot, `$HOME/.claude/projects/<projet>/90e1510b-aee1-44a7-8950-39b26178b59e/subagents/` : `agent-a8bbf81e5400158ad` (A), `agent-a760922a3e77948d2` (B), `agent-a8ec132be800a837d` (C), `agent-afef796df1b243ca2` (A, cloture) ; script de poste qui liste chaque appel d'outil, ses chemins, ses ports | section 4.1 |
| s56, transcriptions | `grep -l` des chemins de worktree de s56 sur la session `d4ae0dae-593c-4b3c-ae16-5b5dcb518539` | elles existent (arbitrages A et B ; l'audit d'`ART-CP-10`, et celui de `-13` par le meme agent, comme `cloture-nadia.md` le dit). **Je ne les ai pas lues contre les trois formes** (section 4.3) |
| `S54-OBS-SCI-CONSIGNE` | `grep -c "puis réponds à la question"` sur `cm1-sciences-extraire-information.yaml` ; `grep -n "enonce:"` | 12 ; les 12 enonces commencent par *"Lis ce tableau, puis réponds à la question."* (items 1 a 6) ou *"Lis ce texte, puis réponds à la question."* (7 a 12), puis *"D'après ce tableau"* ou *"D'après ce texte"* |

Non lance : aucun jar, aucun jeu. Les transcriptions de s56 ne sont lues que pour savoir lesquelles existent.

---

## 1. Point 2 : ce qui fait compter une ligne `en cours` dans la version

### 1.1 Le probleme que les mots posent

La matrice definit `en cours` comme *"un jeu existe et cible cette competence, mais n'a pas encore recu le verdict VALIDE
de nadia-prof"* (section "Comment la lire"). Lu a la lettre, un jeu branche que personne n'a joue suffit. **C'est
precisement ce que je ne peux pas laisser compter** : quatre defauts visibles par un enfant ont traverse une CI entierement
verte, et `DEC-0009` a retire un jeu que l'enfant jouait dans l'etat que j'avais refuse. Les mots de l'utilisatrice disent
*"dont le jeu est jouable"* : je prends "jouable" au sens d'un enfant qui joue, pas d'une partie qui va au bout.

### 1.2 Ce qui suffit : cinq criteres, tous necessaires

Une ligne `en cours` compte dans la version **si et seulement si** son jeu remplit les cinq :

1. **Servi** : une entree de `catalogue.yaml`, atteignable depuis l'accueil du niveau.
2. **Done technique** : la CI verte, ses jobs nommes, sur l'arbre de la version.
3. **Un verdict ecrit de moi, qui n'est pas `REFUSE`**, sur `squad/conformite/FICHE-<jeu>.md`, prononce apres avoir joue
   le jar qui sert cette entree : `VALIDE`, `VALIDE SOUS RESERVE`, ou `VALIDE` limite a la part servie. C'est le critere qui
   porte l'exactitude du contenu, la lisibilite au niveau et le traitement de l'erreur, que la CI ne voit pas.
4. **Un plafond ecrit par moi qui autorise `en cours`** sur cette ligne, et la mention de couverture partielle que j'ai
   exigee reprise dans la cellule de la matrice (`camille-po`).
5. **Aucune reserve bloquante ouverte** sur ce jeu, et aucun verdict posterieur qui le refuse.

### 1.3 Ce qui ne suffit pas

| Situation | Compte ? | Motif |
|---|---|---|
| Un paquet ecrit, sans entree de catalogue (17 au CE1 aujourd'hui) | non | aucun enfant ne le joue |
| Une entree de catalogue, CI verte, que je n'ai pas jouee | **non** | critere 3 : c'est le cas que `DEC-0009` a coute. Si un tel jeu est dans la version, il y est hors de ma porte, et je demande qu'il en sorte ou qu'il soit audite avant |
| Un jeu refuse, meme encore au catalogue | non | `DEC-0009` |
| Un jeu dont mon verdict dit qu'il *"ne vaut pour aucune ligne"* (`cp-arts-oeuvres-familles` pour `ART-CP-10`) | non pour cette ligne | il ne travaille pas la competence (critere 4) |
| Un paquet que j'ai plafonne a `non couvert` (`G7` a `G11`, quinze sur seize), meme branche et joue | non | le plafond dit qu'il sert la competence voisine : le compter ferait mentir la version |

### 1.4 Ma position sur le Done pedagogique d'une version publiee

- **Il n'y a pas de Done pedagogique d'une version, et je n'en prononcerai pas.** Le Done se prononce par jeu, sur sa
  fiche, et fait passer une ligne a `couvert-valide`. Publier ne change aucun statut.
- **Une ligne `en cours` qui compte dans la version reste `en cours`**. Ce que je garantis sur elle est ce que dit son
  verdict : le contenu servi est exact, lisible au niveau, et l'enfant comprend son erreur. Ce que je ne garantis pas est
  ce que dit sa mention : la part de la competence qui n'est pas servie.
- **Si la version est presentee a un adulte**, elle distingue les deux comptes : lignes `couvert-valide`, lignes `en cours`
  avec leur mention. Annoncer un total sans cette distinction ferait passer une couverture partielle pour une couverture.
  Le libelle est a `camille-po` ; l'enfant, lui, ne voit pas la matrice.
- **Ce que j'en dis pour aujourd'hui, mesure en section 0** : les quatre lignes `en cours` remplissent les cinq criteres.
  Les six lignes plafonnees `en cours` par moi (`MAT-CE1-01`, `-10`, `-11`, `-15`, `FRA-CE1-03`, `-21`) ne les rempliront
  qu'apres un lot audite chacune. **Le compte de la version est a `camille-po`** ; je n'en ecris pas le chiffre.
- **Ce que le point 2 change, et que je nomme sans le trancher** : les lots de `G3` et `G4`, que le rang 4 de `DEC-0120`
  dit *"ne ferment aucune ligne"*, font desormais compter une ligne dans la version. Leur rang est a `camille-po`.

---

## 2. Point 3 : le CE1, autres matieres

### 2.1 Mon critere, et pourquoi

Les programmes du cycle 2 hors francais et mathematiques sont ecrits pour le cycle, pas pour l'annee. Le CP a deja ses
lignes dans six de ces matieres. **Une ligne de CE1 ne s'ecrit que si un paquet de CE1 doit y servir autre chose qu'un
paquet de CP sur la ligne voisine**, et sa frontiere avec le CP s'ecrit avec elle. Sans ce critere, chaque ligne de CP
aurait une jumelle de CE1 que le meme genre de paquet fermerait avec des items plus longs : la matrice gagnerait des
lignes, l'enfant rien. C'est la propriete *"chaque paquet travaille la ligne qu'il declare, pas la voisine"*
(`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section 9, point 7), appliquee au referentiel lui-meme.

### 2.2 Matiere par matiere

| Matiere (cycle 2) | Decision | Motif |
|---|---|---|
| Questionner le monde | **ouverte, referentiel ecrit** : 12 lignes, `QLM-CE1-01` a `-12`, toutes atteignables ; 9 jouables au choix textuel, 3 en attente d'un dessin (circuit, plan avec legende, planisphere) | contenus propres au CE1 : metamorphose et germination, besoins des plantes, familles d'aliments, troisieme etat de l'eau, aimant, electricite, legende et itineraire, continents, generations. Neuf lignes de CP n'ont pas de jumelle (le fichier le dit) |
| Enseignement moral et civique | **ouverte, referentiel ecrit** : 5 lignes, `EMC-CE1-01` a `-05`, 4 atteignables, 1 `non couvrable en l'etat` (cooperer, conduite) | biens collectifs, regle et sanction, couples droit-devoir, interet d'un seul et interet de tous. Les quatorze lignes du CP couvrent le reste ; l'egalite filles-garcons a deja sa seule forme jouable dans `EMC-CP-04` |
| Arts plastiques | **reportee** | les lignes de CP atteignables sont les couleurs, les outils, decrire une oeuvre et rapprocher deux oeuvres. Les deux premieres n'ont pas de contenu propre au CE1 ; les deux dernieres reposent sur des reproductions, et la forme qui en montre deux ensemble est refusee (`ART-CP-10`, s56) et pas encore rejouee. **Je reprends ce referentiel apres le verdict du rejeu d'`ART-CP-10`**, pas avant : ecrire des lignes de CE1 sur une forme non prouvee, c'est ecrire contre une barre que je n'ai pas vue tenir |
| Langues vivantes (anglais) | **reportee** | mes deux motifs de CP (`squad/programme/langues-vivantes/competences-cp.md`) : le premier, l'interference avec l'apprentissage du code, faiblit au CE1 sans disparaitre (`FRA-CE1-01` et `-02` travaillent encore des graphemes) ; le second tient entier : un programme oral d'abord, aucun son, et aucun adulte qui prononce l'anglais (reserve du 2026-09-02). Seule la decouverte culturelle se sert, et elle est la jumelle de `LVE-CP-06`. **Ce qui la rouvre** : une decision de l'utilisatrice sur un son d'anglais (`Q2`) |
| Education musicale | **refusee** | au CP, deux lignes atteignables sur onze (instruments, vocabulaire) ; les neuf autres demandent un son ou une production (`non couvrable`). Au CE1, les memes connaissances sans contenu propre : uniquement des jumelles ou des lignes non couvrables |
| Education physique et sportive | **refusee** | au CP, quatre lignes atteignables, toutes des connaissances (securite, regles d'un jeu, effets de l'effort) ; le reste est un geste moteur, de nature irreversible. Au CE1, uniquement des jumelles ou des lignes non couvrables |

**Effet sur le CE1, a compter par `camille-po`** : 17 lignes neuves, dont 16 atteignables. Je n'ecris pas les lignes de la
matrice ; la situation de chacune a son entree est dans le jugement de faisabilite de chaque referentiel.

### 2.3 Ce qui reste du avant le premier paquet

**L'instruction ligne par ligne** des deux referentiels (conditions, frontieres d'items, plafonds), comme a s48 pour le
francais CE1. Elle n'est pas faite ici. Son prix n'est pas ecrit : je le fixerai dans l'arbitrage qui l'engage, et je ne
l'invente pas. Aucun paquet de ces lignes ne s'ecrit avant.

---

## 3. Point 3 de la demande : `S54-OBS-SCI-CONSIGNE`, mes criteres

`DEC-0120` A.3 point 1 la rend candidate de B a une seule condition : que j'ecrive ses criteres. Les voici.

**Le defaut, relu sur le paquet a `59c4b56f`** (section 0) : a chaque manche, l'enfant lit la consigne *"Lis le texte ou
le tableau, puis réponds à la question."*, puis un enonce qui la redit (*"Lis ce tableau, puis réponds à la question."*),
puis *"D'après ce tableau, ..."*. Le document est nomme deux fois, l'instruction deux fois. **Ce n'est pas une erreur,
c'est une charge de lecture inutile sur les douze items.** La premiere phrase de chaque enonce n'apporte plus rien : la
seconde nomme deja le document.

**Criteres d'acceptation** (une story, un fichier) :

1. Les 12 enonces de `cm1-sciences-extraire-information.yaml` perdent leur premiere phrase : chacun commence par
   *"D'après ce tableau,"* ou *"D'après ce texte,"*. Mesure : `grep -c "puis réponds à la question"` sur le paquet rend 0.
2. Rien d'autre ne change dans le paquet : ni document, ni bonne reponse, ni distracteur, ni indice (`git diff` ne touche
   que les 12 lignes `enonce:`).
3. **La consigne du catalogue ne change pas.** Toucher le paquet et sa consigne dans la meme story serait la forme (a) que
   je garde ; la consigne reste vraie pour un enonce raccourci.
4. Les tests qui lisent ce paquet restent verts, garde-fou de la phrase du texte support compris ; le developpeur nomme
   ceux qu'il a lances.
5. **Rejeu par moi**, instance B, sur le jar qui sert l'entree : une partie de dix manches, chaque enonce lu a l'ecran une
   fois. `SCI-CM1-20` reste `couvert-valide`, non rouverte.

**Prix** : mon rejeu, **0,25**, sur le precedent d'un rejeu mot pour mot (`FICHE-bandit-manchot-passe-compose-ce1.md`,
regime et prix de `PCO-CE1-R1`). Developpeur : a designer par `sofia-sm`. **Mon avis** : la placer en B. Elle est
visible sur toutes les manches et ne coute presque rien. Si elle n'est pas engagee a s57, la retraite de `DEC-0120` 4.1
tient, et je ne demande pas de troisieme report.

---

## 4. Point 4 : `A10`, `PAR-2` pour A', la borne, la duree

### 4.1 `A10` : la verification de s54 contre les formes (a), (b), (c)

**Quelle `A10`.** `retro.md` de s56 porte deux lignes `A10` (verifie a `59c4b56f`) : en section 3, celle de la retro de
s55, *"Verification de s54 (trois instances) contre les formes (a), (b), (c)"* ; en section 5, une action neuve de s57,
*"Ecrire dans le depot les deux arrets d'agent et le watchdog"*. Ici, `A10` est la premiere, reprise par `A13` de la
retro de s56. La seconde est a `sofia-sm`. Je n'edite pas une retro close.

**Les formes, inchangees** (`ARBITRAGE-PREALABLE-s31...`, section 8) : (a) une story qui modifie un paquet et une story qui
modifie sa consigne dans le meme sprint, laissant la consigne fausse ; (b) un chiffre de sprint que ni l'une ni l'autre
instance ne peut verifier depuis sa moitie, et qu'un tiers trouve faux en recomptant ; (c) deux instances ecrivant dans
une meme ressource partagee sans le savoir. *"Une seule occurrence de l'une des trois formes suffit."*

**s54 : trois couloirs.** Fenetres lues dans les transcriptions (UTC+02:00) :

| Couloir | Agent | Fenetre | Worktree | Scratchpad | Port |
|---|---|---|---|---|---|
| A | `nadia-prof` A, audits | 12:03:22 - 12:28:49 | `recreo-s54-nadia-a` | **racine** (`jouer.py`, `ck`, `jar/`, `compte.py`, `ids.py`, `verify.log`) | 18854 |
| B | `nadia-prof` B, audits | 12:03:24 - 12:20:46 | `recreo-s54-nadia-b` | `nadia-b/` | 18754, serveur arrete a 12:18:23 (`taskkill` par PID) |
| C | `nadia-prof` C, audit | 12:26:51 - 12:42:32 | `recreo-s54-nadia-c` | **racine** (`jouer.py`) | 18754 |
| A | `nadia-prof` A, cloture | 13:08:02 - 13:23:18 | `recreo-s54-nadia-cloture` | **racine** (`jouer.py`) | 18871 |

**Forme (a) - aucune occurrence.**

| Constat | Methode |
|---|---|
| Six consignes ecrites une fois, dans le commit qui cree l'entree, jamais modifiees ensuite | `git show --numstat` de `catalogue.yaml` : `fb42d5f1`, `269ce5ce`, `95aa8f39`, `ecf34b10`, `557ebd8a`, `920e19e2`, `6 0` chacun ; aucun autre commit de la plage ne touche ce fichier |
| `cp-arts-oeuvres-familles` modifie 11 minutes apres son entree (`9a0011b1`, apres `920e19e2`) : les six corrections decidees par C | consigne *"Lis bien, puis choisis la bonne réponse."*, vraie sur le paquet corrige ; la fiche dit l'audit joue sur le paquet de `9a0011b1` |
| `ce1-passe-compose` modifie apres son verdict (`7d32c1e3`, `PCO-CE1-R1`) | indices seuls ; rejeu sur le paquet final (fiche, bloc de rejeu) |

**Forme (b) - aucune occurrence.** 137 / 182 se recompte sur `4b005324` (section 0). **Un chiffre que je n'y classe pas,
et je dis pourquoi** : `DEC-0114` A.25 ecrivait une consommation de **7,5** (A 3,25), corrigee en **8,0** (A 3,75) par
A.26. C'est un chiffre de sprint fait de trois moities, et il etait faux. Mais **il etait visible depuis la moitie de A** :
mon arbitrage de s54 instance A portait 3,75 et ecrivait *"L'ecart avec A.25 de `DEC-0114` (3,25) est a `camille-po`"*.
La forme (b) exige un chiffre invisible de l'interieur de chaque moitie (`ARBITRAGE-PREALABLE-s31...`, section 8 : *"invisible
de l'interieur de chacune des deux moities"*). C'est un chiffre relaye avant la fin du travail de A, de la famille du
*"568 trailers"* que je n'ai pas classe non plus a s52 (`ARBITRAGE-COMPLEMENT-s55-instance-a.md`, 2.2).

**Forme (c) - une occurrence, constatee dans les transcriptions.**

| Heure (UTC+02:00) | Instance | Geste |
|---|---|---|
| 12:10:27 | A | cree `jouer.py` **a la racine du scratchpad de session** (*"File created successfully"*), son script de partie ; l'execute jusqu'a 12:11:22 |
| 12:22 - 12:25 | A | ecrit ses fiches ; ne relit plus `jouer.py` |
| 12:33:47 | C | ecrit `jouer.py` **au meme chemin** : l'outil rend *"has been updated successfully"*. **Le fichier de A est ecrase**, et C ne le dit nulle part |
| 13:11:24 | A (cloture) | reecrit `jouer.py` au meme chemin pour le rejeu de `PCO-CE1-R1`, sur le fichier de C |

**Consequence de contenu : aucune, et je l'ai verifiee au lieu de la supposer.** Chaque instance ecrit son script avant de
l'executer (A : 12:10:27 puis 12:10:32 ; C : 12:33:47 puis 12:33:52 ; A cloture : 13:11:24 puis 13:11:29). Les fiches de A
precedent l'ecriture de C ; aucun verdict de s54 ne repose sur le fichier d'une autre instance. **Cela ne change pas la
qualification** : comme a s50, la forme (c) se constate a l'ecriture d'une meme ressource physique par deux instances qui
ne le savent pas, pas a son effet (`ARBITRAGE-PREALABLE-s51-borne-a-trois-instances.md`, 2.3). Les fenetres de A et de C se
chevauchent de 12:26:51 a 12:28:49, mais A ne touchait plus `jouer.py` depuis 12:11:22 ; la definition n'exige pas
d'ecritures simultanees, et l'objet est celui de s50 : `jouer.py` a la racine.
**La regle qui l'empechait etait ecrite** : ma reserve du 2026-10-06, un sous-repertoire par agent et *"aucune instance a
la racine"* (`ARBITRAGE-PREALABLE-s52-instances-ce1.md`, 2.3). B l'a tenue ; A et C non.

**Ce qui n'est pas une forme (c)**, verifie :

| Ressource | Constat | Qualification |
|---|---|---|
| Port 18754 | B puis C, B arrete a 12:18:23, C demarre a 12:32:29 ; jamais deux serveurs en meme temps | usage successif, pas d'ecriture concurrente |
| `FICHE-bandit-manchot-atteinte-autrui-cm1.md` (fiche de B) | A y ajoute trois lignes a 13:23 (`78da0199`), une correction de son propre constat, et ecrit *"Ce verdict-ci n'en change pas"* | A savait que la fiche etait celle de B : ecrite en le sachant, verdict de B intact |
| `REGISTRE-identifiants.md` | seule l'instance A alloue (`EXC-R15`) ; B et C n'allouent rien | une seule instance ecrit |
| Entrees de catalogue | les six `paquet:` presents a `4b005324` (`grep -c`, 1 chacun) | aucune ligne perdue |

> **Verdict `A10` : condition (ii) non remplie pour s54.** s54 est clos avec une occurrence de la forme (c), constatee le
> 2026-10-08 a 12:33:47 (UTC+02:00), non ecrite dans le depot jusqu'a ce document. **L'instance C est en cause** : la
> condition 2 de mon arbitrage de C (`arbitrage-nadia-c.md`, 3.4) la fait compter contre la condition (ii) a la prochaine
> derivation. Ma propre instance A est en cause aussi : c'est elle qui a ecrit a la racine la premiere.

**Prix** : 0,25 (quatre transcriptions, une plage de 60 commits, trois fichiers partages), hors capacite de s57 : ce
document n'est pas un lot.

### 4.2 `PAR-2` pour A' : accord pour s57 seul, a six conditions

`PAR-2` : *"Une matiere et un niveau ne sont jamais partages, ni dans un sprint ni entre deux lots du meme sprint."* Je l'ai
refuse pour le francais CE1 le 2026-10-06 et j'ai ecrit ce qui me ferait reexaminer : *"une proposition ecrite de
`camille-po` qui decoupe le francais CE1 sans couper aucune frontiere de la table de s48 section 7, sur un sprint ou aucune
autre instance ne porte de terrain neuf, avec un avis de `theo-archi` qui dit comment deux instances n'amendent pas
`ADR-0065` en meme temps. Je jugerai alors le decoupage, pas le nombre"* (`ARBITRAGE-PREALABLE-s52-instances-ce1.md`,
section 5). Je juge le decoupage de `DEC-0120` section 1 : X (`-04`, `-05`, `-06`, `-07`, `-11`), Y (`-08`, `-09`, `-10`,
`-15`, `-24`).

| Ce que j'avais ecrit | Etat | Lecture |
|---|---|---|
| Aucune frontiere de la table de s48 (section 7) coupee | **tenu** | `-04` / `-06`, `-05` / `-04` : tous en X. `-08` / `-09` / `-10` : tous en Y. `-24` / `-25` : `-25` est servie. La dependance de `RLP-CE1-1` a `-14` et `-15` : `-14` est servie, `-15` est en Y |
| Aucune autre instance sur un terrain neuf | **tenu** | B porte les arts plastiques CP, ou la forme a reproductions a rencontre du contenu a s56 (refus), et les sciences CM1 (19 lignes `couvert-valide`) |
| Le motif 2 de mon refus : le decoupage ferait de chaque groupe un terrain neuf (`PAR-13`) | **tenu a s57, par le cousinage** | ma lecture de s55 (1.1) : `RCT`, `INF`, `DOC`, `PRN` ont pour cousines `NAR` et `COMP`, `FAM` a `SYC` ; `OMP` et `RLP` n'en ont pas. **Sans `-08` ni `-09` a s57 (condition 4), ni X ni Y n'a de lot sans cousine** |
| L'avis de `theo-archi` sur `ADR-0065` | **engage, pas ecrit** | `S57-ADR-0065-DEUX-INSTANCES`, `DEC-0120` A.2 : condition 2 |

> **Accord sur `PAR-2` pour le francais CE1, pour s57 seulement**, aux six conditions suivantes. Une condition non tenue
> retire l'accord pour le lot qu'elle touche ; l'accord d'un autre sprint se rejuge.
>
> 1. Le decoupage est celui de `DEC-0120` section 1 ; **aucun lot ne change de groupe** pendant le sprint.
> 2. **L'amendement d'`ADR-0065` est fusionne avant le premier worktree d'un lot de A'.** Aucune des deux instances
>    n'amende `ADR-0065` ni le garde-fou de l'accent : un besoin se route a `theo-archi`.
> 3. `VSJ-CE1-R1` reste a A, avec `-13` (`DEC-0120` A.3 point 3).
> 4. **`-08` et `-09` ne sont pas des lots de s57** : ma regle de s55 (1.1) - au plus un lot sans cousine par sprint, *"et
>    jamais dans le sprint ou une autre instance audite le paquet illustre d'`ART-CP-10`"* - et B rejoue ce paquet a s57.
>    `-09` vient de toute facon apres `-15` (`RLP-CE1-1`).
> 5. **Un sous-repertoire de scratchpad par agent et par instance, aucune ecriture a la racine, un port par agent.**
>    C'est la cause exacte de l'occurrence de s54 (4.1).
> 6. Chaque instance alloue ses identifiants sur la copie du registre de son worktree et ecrit qu'elle sait l'autre ; la
>    fusion reverifie le compte (`ADR-0085`, clause 5).

### 4.3 La borne : O1, 8,0 pour trois instances

**Ce que dit ma regle.** Une borne au-dela de 8,0 ne se derive que si trois conditions cumulatives tiennent
(`ARBITRAGE-PREALABLE-s46...`, section 5).

| Condition | Etat le 2026-10-13 |
|---|---|
| (i) une composition a deux instances remplit 8,0 et laisse dehors au moins 4,0 de lots instruits, prenables par la troisieme sous `PAR-1` et `PAR-2` | **devenue atteignable par le point 2 de l'utilisatrice, non montree.** `DEC-0120` A.5 mesurait 0,5 prenable par B. Les quatre lots textuels de `G4` (mathematiques CE1, plafond `en cours`, 1,0 chacun) font maintenant compter une ligne dans la version et sont prenables par B : 4,0 + 0,5. Le montrer est a `camille-po`, dans un document de planning |
| (ii) le dernier sprint parallele clos est sans occurrence des formes (a), (b), (c) | **non etablie.** s54 : une occurrence (4.1). Le dernier sprint parallele clos est s56 : **je ne l'ai pas verifie** contre les trois formes, et je ne presume pas une verification que je n'ai pas faite. `cloture-nadia.md` de s56 dit qu'un seul agent a repris les audits des deux instances apres l'arret de B : savoir si s56 a ete parallele jusqu'au bout fait partie de cette verification |
| (iii) `PAR-13` | **tenue** (4.2) |

> **Decision : O1. La borne de s57 est 8,0, pour trois instances.** O2 n'est pas derivable : la condition (ii) n'est pas
> etablie, et la condition (i) n'est pas montree. Je ne pose aucun chiffre au-dela de 8,0, ni 12,0 ni un autre.

**Repartition (`PAR-9` : la somme des budgets egale la borne)**, sur mes tarifs : arbitrage prealable 0,5 et marge de rejeu
0,5 par instance, lot neuf 1,0, `VSJ-CE1-R1` 0,5, rejeu d'`ART-CP-10` 0,5 (`cloture-nadia.md` de s56, section 3),
`S54-OBS-SCI-CONSIGNE` 0,25 (section 3). Deux repartitions, selon que `camille-po` met ou non un lot de `G4` dans B :

| Instance | Sans lot de `G4` | Avec un lot de `G4` |
|---|---|---|
| A (X) | **2,5** : arbitrage, marge, `VSJ-CE1-R1`, 1 lot | **2,5** : idem |
| A' (Y) | **3,0** : arbitrage, marge, 2 lots | **2,75** : arbitrage, marge, 1 lot ; 0,25 non affectes [faux : 0,75, section 10.2] |
| B | **2,5** : arbitrage, marge, rejeu d'`ART-CP-10`, `S54-OBS-SCI-CONSIGNE` ; 0,75 non affectes | **2,75** : idem, plus 1 lot de `G4` |
| Somme | 8,0 ; 3 lots de francais CE1 | 8,0 ; 2 lots de francais CE1, 1 lot de mathematiques CE1 |

Sous le point 2 de l'utilisatrice, les deux colonnes font gagner a la version trois lignes au mieux : la premiere en
`couvert-valide`, la seconde deux en `couvert-valide` et une en `en cours`. **Le choix est un ordre, donc a `camille-po`.**
Si `S54-OBS-SCI-CONSIGNE` n'est pas placee, B rend 0,25.

**Ce qui me ferait deriver O2 pour s58** : s57 clos sans aucune occurrence des trois formes, verifie par moi transcriptions
des trois couloirs comprises, et la condition (i) montree par `camille-po`. La derivation se ferait alors dans un document
neuf, sur les mesures de ce jour-la.

### 4.4 La duree du sprint, dans mon role seulement

La duree est a `sofia-sm` (`squad/README.md` section 3 : pas de timebox, un sprint est un increment). **Ce que mon role en
dit** :

1. **Un sprint ne se clot pas sur un lot non audite.** Un lot engage a, a la cloture, un verdict ecrit, ou il est un report.
   L'utilisatrice n'a pas de date (point 1) : rien ne me demande de juger plus vite.
2. **Allonger un sprint n'augmente pas ma borne.** Trois lots par instance (`PAR-8`) bornent mon attention par lot, pas le
   temps. Un sprint plus long qui empilerait plus de lots par instance, je le refuserais au meme motif.
3. **La duree doit laisser un rejeu** : un refus, sa correction et mon rejeu tiennent dans la marge de 0,5 de chaque
   instance. Le temps de correction entre mon refus et le rejeu n'est pas le mien a mesurer ; s56 en a montre le prix
   (`retro.md` de s56, C1).
4. **Mon rythme mesure** est en lots, pas en heures : 2, 2, 1 lot de francais CE1 par sprint a l'instance A, s54 a s56
   (`DEC-0120` A.11, mesure de `camille-po`). Je n'ai pas de mesure en heures et je n'en ecris pas.

---

## 5. Point 5 : les dix lignes du rang B

"Ecriture possible" dit si je sais ecrire, aujourd'hui, ce qu'un paquet devrait faire pour que la ligne puisse fermer ; un
prerequis qui n'est pas a moi (un materiau, une forme) est nomme. **Aucune des dix lignes n'a de prix mesure** : aucune n'a
jamais ete auditee sous une forme qui puisse la fermer. La colonne "prix ecrit" donne un tarif avec sa source, ou rien.

| Ligne | Ecriture possible | Ce qui manque, ou la condition | Prix ecrit (source) | Prix mesure |
|---|---|---|---|---|
| `QLM-CP-10` | **oui** | un paquet illustre au palier 1 (`ADR-0073`, existant) ; le fichier sans image est plafonne `non couvert` | **2,0**, ecrit par `camille-po` (`DEC-0099`, 4 ter) a partir de mon supplement de lot illustre | aucun |
| `QLM-CP-16` | **oui** | un paquet neuf ou *comparer* est la tache de la plupart des items, sous `BIA-1` et `BIA-2` ; textuel | aucun ; mon bareme de lot neuf donne 1,0 (`ARBITRAGE-PREALABLE-s28...`, 4.3), application, pas mesure | aucun |
| `ART-CM1-07` | **non, aujourd'hui** | *"reperer, dans une oeuvre observee, les effets produits par la matiere, la couleur, le geste et le support"* : sur une reproduction a l'ecran, une touche ou une matiere se voit-elle a la taille rendue ? Je ne le sais pas sans une reproduction mesuree ; et un enonce qui decrirait l'effet est une contrefacon (`CON-1`). Je l'ecrirai devant une reproduction rendue | aucun | aucun |
| `ART-CM1-08` | **oui, sous condition** | la forme de `cp-decrire-une-oeuvre` (une reproduction par item, `ART-CP-09` `couvert-valide`) ; prerequis : des reproductions, corpus de 12 oeuvres (`DEC-0120` A.11) dont je n'ai pas juge la suffisance. **Et une frontiere avec `HDA-CM1-01`** (ci-dessous) | aucun ; bareme 1,0 + 0,5 lot illustre (s28, 4.3), application | aucun |
| `HDA-CM1-01` | **oui, sous condition** | meme forme, meme prerequis. **Constat** : son intitule et celui d'`ART-CM1-08` decrivent la meme competence (decrire une oeuvre avec un lexique : support, format, matiere, couleur, sujet). Un seul paquet fermerait deux lignes. J'ecris leur frontiere avant tout paquet de l'une ou de l'autre | aucun ; bareme 1,5, application | aucun |
| `HDA-CM1-02` | **oui, sous condition** | une reproduction par item ; caracteristiques techniques et formelles **de l'oeuvre vue**, jamais definies dans l'enonce | aucun ; bareme 1,5, application | aucun |
| `HDA-CM1-03` | **oui, sous condition** | idem ; le materiau doit se reconnaitre sur la reproduction (marbre, bronze, bois), meme reserve de visibilite qu'`ART-CM1-07`, plus faible : un materiau se voit mieux qu'un geste | aucun ; bareme 1,5, application | aucun |
| `HDA-CM1-04` | **oui, sous condition** | idem ; risque propre : un quiz de culture (*"de quelle epoque ?"*) au lieu d'une deduction depuis ce qui se voit, et deux epoques defendables (`HIS-2`) | aucun ; bareme 1,5, application | aucun |
| `HDA-CM1-06` | **non, aujourd'hui** | la mention exigee est *"identification et ordre entre deux oeuvres seuls"* : l'ordre demande deux oeuvres montrees ensemble, la forme d'`ART-CP-10`, refusee a s56 et pas rejouee. **Je l'ecris apres le verdict de ce rejeu** | aucun | aucun |
| `LVE-CM1-05` | **oui** | ma condition de s55 (section 6) : un second paquet lexical valide avant que la ligne s'ouvre, donc deux audits | aucun ; bareme 2 x 1,0, application | aucun |

**Le seul chiffre mesure qui approche** : 2,5 consommes pour l'audit du premier paquet a reproductions d'`ART-CP-10`
(`cloture-nadia.md` de s56, section 6), refuse. Forme a deux oeuvres, premier passage : il ne se transpose a aucune des
dix. Je ne l'applique pas.

---

## 6. Ce dont je reste la seule garde

| Propriete | Qui la tient |
|---|---|
| Le critere 3 de la section 1 : qu'une ligne comptee dans la version a ete jouee par moi | **moi seule** ; aucun garde-fou ne lit une fiche |
| La forme (c) sur un scratchpad ou un port | **moi seule**, en lisant des transcriptions hors du depot |
| Les formes (a) et (b) | moi seule |
| L'exactitude et la lisibilite au CE1 des deux referentiels neufs, et qu'aucune ligne neuve n'est la jumelle d'une ligne de CP | moi seule |
| La frontiere `ART-CM1-08` / `HDA-CM1-01` | moi seule, a ecrire |

---

## 7. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | Integrer `QLM-CE1-01` a `-12` et `EMC-CE1-01` a `-05` a la matrice, avec la situation de chaque ligne ecrite dans son referentiel ; compter la version selon la section 1 ; le rang de `G3` et `G4` sous le point 2 (1.4) et le choix de la colonne de 4.3 ; placer ou retirer `S54-OBS-SCI-CONSIGNE` (section 3) ; la condition (i) si une derivation est voulue pour s58 (4.3) ; le libelle qui distingue `couvert-valide` et `en cours` devant un adulte (1.4) ; amender `DEC-0120` (A.5, A.17) avec ce document |
| `sofia-sm` | La duree et le planning de s57 (4.4) ; l'heure de fusion d'`ADR-0065` avant A' (4.2, condition 2) ; les sous-repertoires et ports par agent (condition 5) ; le developpeur de `S54-OBS-SCI-CONSIGNE` ; la seconde `A10` de la retro de s56 (4.1) |
| `theo-archi` | `S57-ADR-0065-DEUX-INSTANCES` avant le premier worktree de A' (4.2, condition 2) |
| `nadia-prof`, s57 | Les arbitrages des trois instances ; l'instruction des deux referentiels (2.3) ; la frontiere `ART-CM1-08` / `HDA-CM1-01` ; la verification de s57 a sa cloture (4.3) |
| Utilisatrice | Section 8 |

## 8. Questions pour l'utilisatrice

| Code | Question |
|---|---|
| `Q1` | **Le CE1 doit-il porter des lignes jumelles de celles du CP** (meme competence, items plus longs) dans les matieres dont le programme est ecrit pour le cycle ? Je refuse par defaut (2.1) : elles grossiraient la matrice sans rien servir de plus. Si elle les veut, c'est une decision de produit, et je les ecrirai en les nommant comme telles |
| `Q2` | **Veut-elle rouvrir la question d'un son** (enregistre ou synthetique) ? C'est ce qui rouvrirait l'anglais au CE1 et une partie de la musique. Sans son, je ne les ouvre pas (2.2) |

## 9. Ce que ce document ne fait pas

- Il ne prononce aucun Done et ne touche aucune fiche de jeu, aucun paquet, aucun catalogue, aucun code, ni la matrice.
- Il ne compose pas s57, ne choisit pas les lots, ne fixe pas la duree, ne place ni ne retire `S54-OBS-SCI-CONSIGNE`.
- Il n'alloue aucun identifiant de condition ou de reserve. Les codes `QLM-CE1-*` et `EMC-CE1-*` sont des codes de ligne,
  declares par leurs referentiels (`ADR-0081`).
- Il ne verifie pas s56 contre les trois formes, et ne derive aucune borne au-dela de 8,0.
- Il n'edite ni `retro.md` de s56, ni `DEC-0120`, ni aucun fichier d'un sprint clos.

**Signe** : `nadia-prof`, hors sprint, 2026-10-13.

---

## 10. Amendement du 2026-10-13, 16:33 (UTC+02:00) (`nadia-prof`, apres `DEC-0120` A.27 a A.37)

Annotation datee, branche `s57-nadia` a `9d101ea0`. **Rien au-dessus n'est reecrit**, sauf un renvoi ajoute dans la
cellule fausse de 4.3. Ce que `DEC-0120` A.36 me route : `PAR-13` pour les deux matieres neuves de CE1, l'instruction
des deux referentiels et son prix, `Q-N1`. S'y ajoutent `LVE-CM1-06` et la conformite a la reponse de l'utilisatrice.

### 10.1 Instruction et `PAR-13` : faites, dans un document a part

`squad/conformite/ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`. Ce que 2.3 disait *"pas faite
ici"* l'est la : 16 lignes acceptees sous conditions, `EMC-CE1-05` confirmee non couvrable, `QLM-CE1-07` plafonnee
`en cours` sur sa moitie textuelle. `PAR-13` : ni l'EMC CE1 ni les lignes textuelles de questionner le monde CE1 ne sont
un terrain neuf ; chacune des trois lignes a dessin en est un. **Prix de l'instruction : aucun prix mesurable**, la
methode et la source (`DEC-0106` section 4) sont dans ce document, section 6.

### 10.2 `Q-N1`

**(a) L'ecart est reel, et il est de moi.** Colonne "avec un lot de `G4`", A' : 0,5 + 0,5 + 1,0 = 2,0 ; 2,75 moins 2,0 =
**0,75 non affectes, pas 0,25**. La somme de 8,0 etait juste, `PAR-9` tient. `camille-po` l'a lu juste (`DEC-0120` A.33,
motif 4) : les deux colonnes affectent 7,25 et laissent 0,75 non affectes. Le tableau corrige :

| Instance | Sans lot de `G4` | Avec un lot de `G4` |
|---|---|---|
| A (X) | 2,5 | 2,5 |
| A' (Y) | 3,0 | 2,75, dont **0,75** non affectes |
| B | 2,5, dont 0,75 non affectes | 2,75 |
| Somme | 8,0 | 8,0 |

**(b) L'instruction n'entre pas dans les 0,75 non affectes de B, parce qu'elle n'a plus a y entrer** : elle est faite
hors sprint (10.1), comme celle du francais CE1 l'a ete hors chiffrage de la borne d'audit a s48. Les 0,75 de B restent
non affectes. **Aucun lot de ces dix-sept lignes n'y entre non plus** : un lot neuf vaut 1,0 a mon bareme, plus que
0,75. La repartition de `camille-po` (A 2,5, A' 3,0, B 2,5) ne change pas.

### 10.3 `LVE-CM1-06` : la valeur que j'estime juste

La cellule de la matrice dit *"non couvert - jouable par saisie, sous conditions `ANG-S1` et `ANG-S2`"*. **Elle est
fausse depuis le 2026-10-01.** Valeur que j'estime juste, statut inchange :

> `non couvert` - **refusee a la saisie** depuis le 2026-10-01 (`nadia-prof`, `ARBITRAGE-PREALABLE-s45-cinq-lots.md`,
> section 5.1, motif `R6`) : sur un clavier qui corrige, c'est l'appareil qui produit les lettres du mot anglais.
> Aucune autre mecanique ne la sert : au choix, l'enfant reconnait le mot, c'est `LVE-CM1-04`. Hors vivier. Levable
> aux conditions de `R6` (correction automatique non acceptee, ou appareil ou elle ne s'applique pas), que
> l'utilisatrice a ecartees.

**Pourquoi `non couvert` et pas `non couvrable en l'etat`** : ce statut est reserve a une contrainte fondatrice du
produit ou a un jugement humain sur une production libre ("Comment la lire"). `R6` n'est ni l'une ni l'autre : la
saisie existe, c'est l'appareil qui la defait, et une decision de l'utilisatrice la leverait. Le denominateur ne change
pas. **La matrice est a `camille-po`** ; je ne la touche pas.

### 10.4 La reponse de l'utilisatrice du 2026-10-13 : conformite verifiee

Mots transmis par `DEC-0120` A.27, forme 2, que je n'ai pas lus de sa main : `Q1` *"non nouveaux items"*, `Q2` *"non pas
de son"*. Les deux questions de la section 8 sont closes.

| Point | Etat | Geste |
|---|---|---|
| Critere de 2.1 (pas de ligne jumelle) | **conforme** : c'est la reponse de `Q1` | aucun |
| Referentiels de CE1, au niveau des lignes | **conformes** : chaque ligne a un contenu propre et sa frontiere | aucun |
| Referentiels de CE1, au niveau des items | **non conformes avant ce jour** : quatre lignes pouvaient recevoir un item de CP (`QLM-CE1-04`, `-05`, `-11`, `EMC-CE1-01`), et une demi-ligne (`QLM-CE1-12`) | **corrige** : `NRC-CE1-1` et cinq conditions de ligne (instruction, 1.2) ; une note datee dans chaque referentiel |
| Pas de son : les deux referentiels | **conformes** : aucune ligne ne demande une voix ; `TRV-CE1-1` les couvre | aucun |
| Pas de son : l'anglais au CE1 (2.2, *"reportee"*) | **le statut ne tient plus** : il ne restait qu'un declencheur, une decision sur le son, et elle est refusee | **l'anglais au CE1 passe de reportee a refusee**, au motif deja ecrit en 2.2 (programme oral d'abord, aucun son, aucun adulte qui prononce l'anglais). La ligne de 2.2 reste ecrite |
| Education musicale, EPS au CE1 | refusees, inchangees | aucun |
| Arts plastiques au CE1 | reportee, inchangee : aucun son en cause ; quand je l'ecrirai, `NRC-CE1-1` vaudra pour elle | aucun |

### 10.5 Ce que cet amendement ne fait pas

Il ne touche ni la matrice, ni `DEC-0120`, ni un paquet, ni le code. Il ne compose pas s57. Il ne prononce aucun Done.

**Signe** : `nadia-prof`, hors sprint, 2026-10-13.
