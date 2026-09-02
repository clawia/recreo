# DEC-0071 - La matrice de couverture ne porte que deux matieres sur le programme des cycles 2 et 3 : ecart etabli, effet mecanique sur le taux, perimetre de s27 instruit sur trois matieres neuves, arithmetique de capacite posee et non tranchee

- **Statut** : Proposee. Ce document constate un fait, instruit un perimetre et pose des
  arbitrages ; il ne fixe aucune competence, ne tranche aucune priorite entre les rangs de s27 et
  ne prononce aucun Done.
- **Date** : 2026-09-02
- **Auteur** : `camille-po`
- **Sprint** : hors-sprint (branche `retours-utilisatrice`, avant ouverture de s27)
- **Retours qui la fondent** : l'utilisatrice, deux messages du 2026-09-02, cites en sections 1 et
  5.
- **Competence programme visee** : transverse - ce document porte sur le perimetre du referentiel
  lui-meme, pas sur le contenu d'une ligne.

---

## 0. Ce que ce document fait, dans l'ordre demande

1. Etablit le fait - l'ecart entre ce que la matrice couvre et le programme des cycles 2 et 3.
2. Ecrit ce que le denominateur devient, et pose la regle de non-regression qui doit s'appliquer
   quand il bouge.
3. Instruit le perimetre de s27 sur trois matieres neuves nommees par l'utilisatrice, chacune
   rangee sous le bon niveau.
4. Ne tranche rien de pedagogique : aucune competence precise n'est fixee par moi, tout est pose a
   `nadia-prof`.
5. Pose l'arithmetique de capacite face au rang 1 deja propose en `DEC-0069`, sans decider la
   priorite.

---

## 1. Le fait, etabli par moi et non repris d'un autre document

**Retour de l'utilisatrice, 2026-09-02, verbatim** : *"il n'y a pas de jeux d'anglais
(vocabulaire), histoire / geographie, questionner le monde ajoute les au prochain sprint"*.

**Verification faite avant d'ecrire ce document, sur le depot tel qu'il est aujourd'hui** :

- `squad/programme/MATRICE-couverture.md` ne contient **que quatre tableaux de matiere** :
  "Mathematiques - CP", "Mathematiques - CM1", "Francais - CP", "Francais - CM1" (les quatre seuls
  titres `##` de ce fichier qui nomment une matiere). Aucune section pour "Questionner le monde",
  "Histoire-Geographie", "Langues vivantes", "Sciences et technologie", "Enseignement moral et
  civique", "Enseignements artistiques" ou "Education physique et sportive".
- `squad/programme/` ne contient **que deux dossiers de referentiel** : `mathematiques/` et
  `francais/`. Aucun fichier `competences-<niveau>.md` n'existe pour aucune des six autres
  matieres du programme.
- Les deux occurrences du mot "histoire" dans `MATRICE-couverture.md` sont des tournures de prose
  ("cette histoire n'est pas effacee", "meme histoire d'avancement"), pas la matiere.
- `CLAUDE.md`, ligne 4, decrit le produit comme visant *"a couvrir **toutes les matieres** du
  programme de l'Education nationale"* ; ligne 10, *"`squad/programme/MATRICE-couverture.md`
  pilote l'atteinte de la couverture complete du programme"*.

**Le programme officiel des cycles 2 et 3 compte, en domaines d'enseignement, plus de deux
matieres.** Je le pose ici avec sa reserve de fiabilite exacte, dans le meme esprit que les deux
referentiels existants du depot, qui portent chacun cette meme reserve depuis leur creation
(`squad/programme/mathematiques/competences-cp.md`, en-tete : *"Socle realiste de depart, non
exhaustif et non officiel [...] Ne remplace pas une lecture des programmes officiels de
l'Education nationale"*). Structure telle que je la connais du programme de l'Education nationale,
**a confirmer par un referentiel ecrit dans le depot, exactement comme cela a ete fait pour
mathematiques et francais, pas par cette phrase** :

- **Cycle 2 (CP)** : Francais, Mathematiques, Langues vivantes (etrangere ou regionale),
  Enseignement moral et civique, Questionner le monde, Enseignements artistiques (arts plastiques,
  education musicale), Education physique et sportive - **7 domaines** (8 si les deux
  enseignements artistiques comptent separement).
- **Cycle 3 (CM1)** : Francais, Mathematiques, Langues vivantes, Enseignement moral et civique,
  Histoire-Geographie, Sciences et technologie, Enseignements artistiques, Education physique et
  sportive - **7 domaines** (8 si les deux enseignements artistiques comptent separement).

**L'ecart** : la matrice porte **2 matieres sur 7 (28,6 %), ou 2 sur 8 (25,0 %) si l'on compte les
deux arts separement, par niveau** - pas les *"toutes les matieres"* que `CLAUDE.md` annonce.
**Le taux de 82,9 % ecrit a la cloture de s26 (`DEC-0069`) est un taux sur ce perimetre reduit** :
il ne dit rien de la couverture des six autres matieres, faute d'y avoir jamais eu une seule ligne
de referentiel. C'est mon propre defaut de la cloture de s25, applique cette fois a l'outil de
pilotage lui-meme et non a un seul sprint : *"une enumeration qui ne se declare pas partielle se
lit comme complete."* Je l'ecris ici pour qu'aucun rapport futur ne cite 82,9 % sans cette reserve.

---

## 2. Ce que le denominateur devient - un effet mecanique, pas une regression

**Precedent a suivre, retrouve dans le depot** : `squad/programme/MATRICE-couverture.md`, section
"Synthese a l'ouverture de s19", quand le denominateur est passe de 46 a 45 par le seul effet d'un
reclassement, sans aucun mouvement de couverture reelle - la formule qui y est ecrite est *"[...]
devient [...] par le seul effet du denominateur - ce n'est pas un mouvement de couverture, et je
l'ecris pour qu'aucun rapport futur ne le presente comme tel"*. Le mouvement d'aujourd'hui va dans
l'autre sens (le denominateur va grandir, pas retrecir) mais la regle est la meme : **le prochain
recul du taux affiche, quand il viendra, doit etre ecrit d'avance comme un effet de perimetre, pas
decouvert et lu comme une regression du produit.**

**Ce que je peux ecrire aujourd'hui, honnetement, et ce que je ne peux pas :**

- Je **ne peux pas** donner le denominateur exact que prendra la matrice une fois les six matieres
  manquantes entrees : cela suppose un referentiel de competences par matiere et par niveau, que je
  ne redige pas - c'est `nadia-prof` qui le fait, comme elle l'a fait pour les deux referentiels
  existants (voir section 4). Inventer un chiffre ici reproduirait exactement le defaut que
  `DEC-0062` a ete ecrite pour proscrire.
- Je **peux** dire que ce denominateur augmentera necessairement, et que chaque ligne neuve entre a
  `non couvert` (statut de depart de toute ligne de referentiel, `MATRICE-couverture.md`, section
  "Comment la lire", ligne 23) : **le taux affiche baissera mecaniquement des la premiere matiere
  neuve entree**, avant qu'un seul jeu n'existe pour elle.
- **Ordre de grandeur, a titre d'illustration et non de prevision** : les quatre tableaux existants
  comptent respectivement 11 (mathematiques CP), 12 (mathematiques CM1), 12 (francais CP) et 12
  (francais CM1) lignes, soit **47 lignes pour deux matieres sur sept**. Si les six matieres
  manquantes suivaient un ordre de grandeur comparable, le referentiel total se compterait en
  centaines de lignes plutot qu'en dizaines - **ce n'est pas un calcul, c'est une mise en garde
  contre l'idee que le denominateur grandirait d'une poignee de lignes.**

**Regle que je pose pour la suite** : au sprint ou une premiere ligne de matiere neuve entre dans
la matrice, la synthese qui l'accompagne doit reprendre la forme du precedent de s19 - ancien
denominateur, nouveau denominateur, ancien taux, nouveau taux, et la phrase *"par le seul effet du
denominateur"*. Je m'y engage moi-meme, sur ce document.

---

## 3. Perimetre de s27 sur les trois matieres nommees, rangees sous leur niveau

Ce que l'utilisatrice nomme est trois matieres, sur deux niveaux, et ce ne sont pas trois noms
d'une meme chose :

| Matiere nommee | Niveau | Domaine officiel |
|---|---|---|
| Anglais (vocabulaire) | non precise par l'utilisatrice - voir section 5 | Langues vivantes (etrangere) |
| Histoire / geographie | **CM1 (cycle 3)** | Histoire-Geographie |
| Questionner le monde | **CP (cycle 2)** | Questionner le monde |

**Je ne fabrique aucune liste de competences pour ces trois matieres.** Aucun referentiel
n'existe aujourd'hui dans `squad/programme/` pour aucune des trois ; en creer un de ma main
reproduirait le defaut deja nomme dans ce depot pour `NAT-5` et `DEN-1` a `DEN-8` : poser un
arbitrage de contenu sans passer par `nadia-prof` d'abord, au risque de perdre un sprint a
redefaire le travail. **Cette instruction pose le perimetre des matieres, pas leurs competences.**

**Ce que je route, et a qui** :

- **Un referentiel de competences par matiere et par niveau** (`squad/programme/anglais/
  competences-<niveau>.md`, `squad/programme/histoire-geographie/competences-cm1.md`,
  `squad/programme/questionner-le-monde/competences-cp.md`), sur le meme modele que les deux
  referentiels existants (socle realiste, non exhaustif, non officiel, a completer et faire
  valider par l'utilisatrice) : `nadia-prof`, saisie en parallele par la coordination de la squad.
- **Une ligne par competence, ajoutee a `MATRICE-couverture.md`** une fois chaque referentiel
  ecrit, sous une nouvelle section `##` par matiere, au meme format que les quatre existantes :
  `camille-po`, des que le referentiel de `nadia-prof` existe.

---

## 4. Ce que je ne tranche pas

Aucune competence precise, pour aucune des trois matieres, n'est fixee par ce document. C'est
`nadia-prof` qui le fait, et je ne pre-remplis rien qui l'anticipe.

---

## 5. L'anglais : la forme est tranchee par l'utilisatrice, deux points restent ouverts

**Retour de l'utilisatrice, 2026-09-02, verbatim** : *"pour l'anglais une image ou le mot en
français et il faut trouver son équivalent en anglais (français) pas de son"*.

**Ce que cette phrase fixe, sans ambiguite** :

- **Pas de son** : definitif, aucune synthese vocale ni aucun enregistrement pour cet exercice.
- **Le stimulus** : une image, ou le mot ecrit en francais.
- **La reponse** : l'equivalent dans l'autre langue, en choix parmi des propositions.

**Hypothese de calibrage, a verifier et non a tenir pour acquise** : cette forme correspond, mot
pour mot, au paquet illustre du palier 1 valide a s25 (`ADR-0073`) pour la variante image, et a un
`bandit-manchot` ordinaire pour la variante mot francais vers mot anglais. **Si cette
correspondance se confirme, aucune mecanique neuve et aucune forme d'item neuve ne seraient
necessaires** - mais je l'ecris comme une hypothese, pas comme un fait etabli : le precedent selon
lequel une famille d'items neuve ne coute rien a la borne de `nadia-prof` (palier 1, s25) ne s'est
**pas** transpose sans verification a s26, ou `theo-archi` a montre que l'hypothese similaire avait
fait rougir `ADR-0032` sur trois paquets sur quatre. **Verification a faire par `nadia-prof` avant
que quiconque ne compte ce lot comme gratuit dans sa borne.**

**Deux points restent hors de ma main, et je ne les tranche pas :**

1. **Le sens de l'exercice.** La parenthese *"(français)"* de l'utilisatrice se lit soit comme *les
   deux sens de traduction*, soit comme une precision sur la langue de depart. **Ambiguite
   declaree, pas choix fait.** `nadia-prof` instruit les deux lectures et recommande ; la question
   est ensuite reposee a l'utilisatrice avec cet avis en main.
2. **Le niveau.** L'utilisatrice n'a precise ni CP ni CM1 pour l'anglais (a la difference de
   l'histoire-geographie et de questionner le monde, rangees en section 3). `nadia-prof` le dit :
   reconnaitre un mot ecrit dans une langue dont on ne connait pas encore les correspondances
   graphie-son n'est pas la meme chose a six ans qu'a neuf ans.

---

## 6. Arithmetique de capacite face au rang 1 de `DEC-0069` - je dis le calcul, je ne tranche pas la priorite

**La borne, dans les mots de `nadia-prof`, reconfirmee le 2026-08-30** (`DEC-0065`, section 6) :
*"quatre a cinq paquets de contenu par sprint, sur une mecanique existante"*, l'audit d'une
mecanique **ou d'un format** neuf etant **hors compte**. s26 a livre exactement cinq paquets, la
borne haute.

**Rang 1 deja propose pour s27** (`DEC-0069`) : un paquet de voyelles simples, qui ferme la ligne
des graphemes - fait arithmetique, cout de composition le plus bas du backlog, sur mecanique
existante. **1 paquet.**

**Les trois matieres neuves de ce document, dans l'hypothese optimiste ou chacune n'exige qu'un
seul paquet pour ouvrir sa premiere ligne** (verifiee pour l'anglais seulement, section 5 ; non
verifiee pour l'histoire-geographie et pour questionner le monde, dont aucune forme d'item n'est
seulement esquissee) : **3 paquets.**

**Total brut, sur le seul compte de paquets : 4**, dans la borne basse (4) de `nadia-prof`, sans
aucune marge pour un alea de refus - motif de prudence deja nomme a plusieurs sprints passes
(`DEC-0051`, section 5).

**Mais ce compte ne porte pas ce que la borne ne compte pas.** `DEC-0065`, section 6, point 1, l'a
deja nomme sur un seul arbitrage neuf de type `COMP-2` : *"l'arbitrage de s23 - huit candidats - lui
a pris un document de 88 kilo-octets, et il a ete rendu dans un sprint ou elle auditait par
ailleurs"*. Ici, ce ne serait pas un mais potentiellement **trois arbitrages de matiere neuve
simultanes** - trois referentiels a naitre, une forme d'item a confirmer pour l'anglais et deux
formes d'item entierement a definir pour les deux autres matieres - dans le meme sprint qu'un
paquet de contenu supplementaire. **Aucun point de comparaison de ce depot n'a jamais porte trois
arbitrages de premiere matiere a la fois** ; le seul point de comparaison disponible (un arbitrage
`COMP-2`) a deja pris un document entier a lui seul.

**Je dis l'arithmetique, je ne tranche pas la priorite** : soit s27 retient le paquet de voyelles
simples et reporte l'ouverture des trois matieres neuves a un rang ulterieur, soit il ouvre les
trois matieres neuves (ne serait-ce que leurs referentiels et leurs arbitrages prealables, sans
necessairement livrer un paquet chacune ce sprint) et reporte le paquet de voyelles simples, soit
un panachage plus modeste des deux. **C'est a l'utilisatrice de dire ce qui passe devant.**

---

## 7. Ce que ce document demande, et a qui

| Ce qui est du | Proprietaire | Etat |
|---|---|---|
| Trois referentiels de competences (anglais, histoire-geographie CM1, questionner le monde CP) | `nadia-prof` | **Route, section 3** |
| Verification de l'hypothese de calibrage sur l'anglais (mecanique et forme d'item existantes) | `nadia-prof` | **Route, section 5** |
| Instruction des deux lectures possibles du sens de traduction, avec recommandation | `nadia-prof` | **Route, section 5**, puis reponse de l'utilisatrice |
| Niveau de l'anglais (CP, CM1, ou les deux) | `nadia-prof` puis l'utilisatrice | **Route, section 5** |
| Priorite entre le paquet de voyelles simples (rang 1 de `DEC-0069`) et l'ouverture des trois matieres neuves | l'utilisatrice | **Pose, section 6, non tranche** |
| Trois sections neuves de `MATRICE-couverture.md`, une fois les referentiels ecrits | `camille-po` | **A faire des que les referentiels existent** |

---

## 8. Ce que ce document ne fait pas

- Il ne fixe aucune competence pedagogique precise pour aucune des trois matieres.
- Il ne choisit pas le sens de traduction ni le niveau de l'anglais.
- Il ne tranche pas la priorite entre le rang 1 de `DEC-0069` et l'ouverture des matieres neuves.
- Il n'ecrit aucune story de developpement dans `docs/backlog/backlog.md` : rien n'est
  developpable avant qu'un referentiel de competences existe pour au moins une des trois matieres,
  exactement comme le depot l'a deja pratique pour les candidats de niveau 2 de `DEC-0051` -
  arbitrages prealables poses, aucun developpement affecte tant qu'ils ne sont pas rendus.
- Il ne modifie aucune ligne de `MATRICE-couverture.md` : aucune matiere neuve n'y entre tant
  qu'aucun referentiel n'existe pour elle.
