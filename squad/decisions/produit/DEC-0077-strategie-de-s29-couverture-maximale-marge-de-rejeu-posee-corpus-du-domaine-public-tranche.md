# DEC-0077 - Strategie de s29 : couverture maximale sur trois matieres bon marche ; la marge de rejeu est posee comme question ouverte ; DEC-0075 est tranchee (corpus du domaine public)

- **Type** : deux arbitrages de l'utilisatrice, plus une question ouverte posee a l'ouverture de s29.
  **Amendee le 2026-09-05 (meme jour, section 8)** : la question est tranchee - un troisieme
  arbitrage de l'utilisatrice retient la parallelisation de `nadia-prof` (deux instances, borne
  portee a 8,0) et la composition reelle de s29, apres que `nadia-prof` a decline par ecrit le
  perimetre annonce en section 2 (il valait 6,0 a 7,0, pas 5,0). **Amendee une seconde fois le
  2026-09-06 (section 9, cloture de s29)** : le cout reel du sprint (9,25 sur 8,0, marge de rejeu
  ramenee a 0,75) et le recompte corrige de la matrice (neuf lignes ferment, 52/138 = 37,7 %, et
  non huit comme l'annonce l'Instance A de `nadia-prof`) sont actes. **Amendee une troisieme fois
  le 2026-09-06 (meme jour, section 9.4)** : la reverification de `nadia-prof` sur le correctif de
  `REV-1` (`8ef4fb2`) porte l'Instance A a 4,25/5,0 ; le cout reel du sprint passe de 9,25 a
  **9,50 sur 8,0**. La marge de rejeu ne bouge pas, elle reste a **0,75** : le supplement tient
  dans le plafond individuel de l'Instance A, `nadia-prof` l'ecrit elle-meme.
- **Date** : 2026-09-05
- **Auteur** : `camille-po`
- **Sprint** : s29 (ouverture ; amendements de cloture le 2026-09-06)
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette decision arbitre
  un perimetre et une question de gouvernance de sprint ; elle ne fixe aucune competence ligne a
  ligne - ce chiffrage est celui de l'arbitrage prealable de `nadia-prof`, dont la section 13
  (amendement du 2026-09-05) remplace le chiffrage des sections 2 et 8.2 du present document pour
  s29.
- **Statut** : Acceptee ; amendee le 2026-09-05 (meme jour, section 8), le 2026-09-06 (cloture de
  s29, section 9) et le 2026-09-06 (meme jour, section 9.4).
- **Lien** : `DEC-0074` (perimetre de s28, tarif "suis-je la source ?"), `DEC-0075` (tranchee par la
  presente decision), `DEC-0076` (arbitrage de cloture de s28), `ADR-0079` (l'unite de la borne de
  `nadia-prof` est le lot de matiere), `ADR-0081` (clause 5, un item ne sert qu'une ligne),
  `DEC-0064` (regle de derogation de `nombreDeManches`),
  `squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md`
  (regles `FER-1` a `FER-3`, tarif 1,0/1,5),
  `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`
  (sections 1 a 13 - le prix reel, le plafond, la parallelisation, `PAR-0` a `PAR-8`,
  `CYC-1`, `EMC-10`),
  `squad/sprints/s28-second-passage-et-chantier-palier-2/retro.md`,
  `squad/programme/MATRICE-couverture.md`,
  `squad/programme/sciences-et-technologie/competences-cm1.md`,
  `squad/programme/enseignement-moral-et-civique/competences-cp.md` et `competences-cm1.md`,
  `squad/programme/questionner-le-monde/competences-cp.md`,
  `squad/programme/histoire-geographie/competences-cm1.md`.

---

## 1. Etat de depart, recompte avant de m'en servir

Recompte ligne a ligne des dix-huit tableaux de `squad/programme/MATRICE-couverture.md`, colonne
`Statut`, sur l'etat du fichier apres la cloture de s28 (`DEC-0076`, commit `b37405d`) : **43
`couvert-valide`, 10 `en cours`, 85 `non couvert`, 71 `non couvrable en l'etat`, 209 lignes au
total, 138 lignes au denominateur atteignable (209 - 71). 43 / 138 = 0,3116... = 31,2 %.** Ce
compte converge exactement avec celui deja ecrit dans la matrice ("Synthese apres les verdicts du
sprint 28") : **aucun ecart trouve.**

Verification ciblee sur les trois matieres du premier arbitrage, ligne a ligne dans leurs tableaux
respectifs : sciences et technologie CM1 compte 19 lignes `non couvert` sur 20 (une seule `non
couvrable en l'etat`, la demarche d'investigation) ; enseignement moral et civique compte 12 lignes
`non couvert` au CP et 11 au CM1, soit 23 ; questionner le monde CP compte **12** lignes `non
couvert` - et non 15 comme l'affiche encore le "Bilan" ecrit sous ce tableau, qui date d'avant les
trois fermetures de s28 (`QLM-CP-01`, `QLM-CP-03`, `QLM-CP-05`) et n'a pas ete corrige depuis. Cette
phrase de bilan est perimee ; elle n'affecte aucun chiffre cite dans la presente decision, qui est
recompte directement sur les lignes du tableau et non sur cette phrase - je la signale plutot que de
la laisser trainer sans mention, sans la corriger ici pour ne pas elargir le perimetre de ce
document a une retouche de redaction qui n'est pas son objet.

## 2. Premier arbitrage : la strategie de s29 est la couverture maximale

**L'utilisatrice retient cinq lots de vingt items, sur les seules matieres ou `nadia-prof` est la
source du referentiel** : sciences et technologie CM1 (19 lignes `non couvert`), enseignement moral
et civique aux deux cycles (23 lignes), questionner le monde CP (12 lignes). **Total annonce : 5,0
sur une borne de 5.**

**Le raisonnement presente a l'utilisatrice, qui gouverne ce choix et doit rester lisible ici** :
depuis `FER-2` (`ARBITRAGE-PREALABLE-s28...`, section 2), **une ligne ferme a partir de dix items
qui la servent** - un lot de vingt items ferme donc environ deux lignes, quelle que soit la
matiere. Ce qui change d'une matiere a l'autre n'est pas le rendement en lignes, c'est le **prix a
l'item** : `nadia-prof` a ecrit que son tarif ne suit pas la distinction neuve/connue mais la
question **"suis-je la source de cette matiere ?"** - 1,0 quand elle l'est (elle a ecrit elle-meme
le referentiel, comme pour ces trois matieres, `DEC-0074`), 1,5 quand elle ne l'est pas et doit
verifier chaque fait contre une source externe (histoire-geographie, anglais). **Le levier de
couverture disponible pour s29 est donc le choix des matieres bon marche, pas le choix des lignes
a l'interieur d'une matiere.**

**Cible annoncee : environ dix lignes fermees, soit environ 53/138 = 38 %.** Cette cible est
**une estimation de l'orchestrateur, pas un engagement** : c'est `nadia-prof` qui chiffre le
plafond honnete de s29 dans son arbitrage prealable, en cours d'ecriture en parallele de la
presente decision, et **son chiffre remplace celui-ci** des qu'il est ecrit.

**Ce que ce choix ecarte, ecrit sans l'adoucir** :

- **Les neuf lignes d'histoire-geographie CM1 ouvertes depuis s27 ne bougent pas** dans s29. Elles
  restent `en cours`, exactement au tarif de 1,5 qui les a exclues de ce perimetre.
- **Aucune reserve de s28 n'est payee.** `POS-R1` (structure repetee des distracteurs de la
  division posee, `MAT-CM1-05`) n'est pas reprise, alors que `nadia-prof` l'a signalee prioritaire
  sur une ligne **deja verte** - une reserve sur du `couvert-valide` reste donc ouverte un sprint
  de plus. La dette des paquets a voix (six jeux, cinq indices), due depuis s26, reste due depuis
  trois sprints (`DEC-0074`, section amendee ; `DEC-0076`).

## 3. Question posee a l'ouverture de s29, non tranchee ici : que fait-on d'un refus tardif ?

La retro de s28 (`squad/sprints/s28-second-passage-et-chantier-palier-2/retro.md`) nomme un point
que ce depot n'a jamais ecrit comme un choix assume : **la reponse par defaut a un refus de
`nadia-prof` survenant pres de la fin d'un sprint deja a sa borne.** s28 s'est trouve a 5,25 sur une
borne de 5 (`DEC-0076`) parce que cette question ne s'est posee qu'a la cloture, avec trois items
faux deja en production le temps du debat.

**s29 repart a 5,0 sans marge, sur la meme construction que s28.** Si un lot est refuse en cours de
sprint, trois issues existent, et je ne tranche a la place de personne :

- **Le lot part a s30.** C'est la reponse par defaut de `ADR-0079` : la borne de `nadia-prof` est
  concue sans marge, un refus tardif se corrige au sprint suivant. Cout : le contenu refuse reste
  hors catalogue le temps du report (rien de faux ne reste servi, contrairement a s28, puisqu'un
  refus retient l'increment hors catalogue tant qu'il n'est pas leve).
- **Le sprint depasse sa borne**, comme en s28 (5,25 sur 5), sur arbitrage explicite de
  l'utilisatrice au moment du refus. Cout : `nadia-prof` audite un cinquieme lot dans le sprint qui
  l'a deja portee a sa borne, au tarif normal du lot concerne (1,0 pour les trois matieres de
  la section 2).
- **Un lot est retire d'avance du perimetre**, pour garder 0,25 de marge de rejeu des l'ouverture.
  Cout chiffre : a raison d'environ deux lignes par lot de vingt items (`FER-2`), retirer un
  cinquieme du perimetre pour se menager une marge coute **environ deux lignes** sur la cible de
  dix annoncee en section 2 - soit une cible revisee a environ huit lignes, 51/138 = 37 %.

**Cette question est ouverte.** Elle doit etre tranchee avec `nadia-prof` avant que s29 ferme son
perimetre, pas redecouverte au premier refus comme en s28.

## 4. Second arbitrage : `DEC-0075` est tranchee - corpus de reproductions d'oeuvres du domaine public

**L'utilisatrice retient l'option B de `DEC-0075`** : un corpus de reproductions d'oeuvres du
**domaine public**. `theo-archi` instruit la source et la forme technique ; `nadia-prof` valide le
choix des oeuvres retenues, comme pour tout contenu.

`DEC-0075` passe du statut `Proposee` au statut **`Acceptee`** (note et champ `Statut` mis a jour
dans le fichier lui-meme). Ce que cette decision implique :

- **Elle debloque jusqu'a neuf lignes atteignables** pour les sprints suivants : 2 en arts
  plastiques CP (lignes 9, 10), 2 en arts plastiques CM1 (lignes 7, 8, sous `ART-3`), 5 en
  histoire des arts CM1 (lignes 1, 2, 3, 4, 6). Ces neuf lignes restent `non couvert` aujourd'hui -
  cette decision ouvre un chantier d'acquisition, elle ne sert aucun contenu.
- **Elle ne ferme aucune ligne dans s29.** Constituer et verifier un corpus de reproductions n'est
  pas un lot de contenu pilotable comme les cinq lots de la section 2 : c'est un chantier de
  sourcing et de verification de droits, et s29 est deja plein a 5,0 sans lui.
- **Les questions qui restent ouvertes ne sont pas tranchees ici** : quelle source de reproductions
  (musee en licence ouverte, corpus dedie au domaine public, autre), quelle preuve de domaine
  public exiger par oeuvre (`ART-3` : identification, auteur, date, lieu de conservation), quel
  format d'integration au contenu, qui verifie chaque preuve avant qu'une oeuvre entre au depot.
  **Je les route a `theo-archi`**, via `sofia-sm` : ce sont des choix de sourcing technique et de
  forme, pas des choix pedagogiques ni des choix qui m'appartiennent.

## 5. Ce que cette decision ne fait pas

- Elle ne fixe pas la composition ligne a ligne de s29 : c'est l'arbitrage prealable de
  `nadia-prof`, qui chiffre le plafond honnete et remplace l'estimation de la section 2.
- Elle ne tranche pas la question de la section 3 : elle est ecrite comme ouverte et le reste tant
  que `camille-po` et `nadia-prof` ne l'ont pas arbitree ensemble.
- Elle ne tranche aucune des questions de sourcing ou de forme du corpus d'oeuvres (section 4) :
  routees a `theo-archi`.
- Elle ne fait bouger aucune ligne de `squad/programme/MATRICE-couverture.md` : ni les cinq lots de
  s29 (pas encore livres), ni les neuf lignes debloquees par l'option B (pas encore servies).
- Elle ne prononce aucun Done pedagogique.

## 6. Impact

- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0077` ajoutee ; ligne `DEC-0075` mise a jour
  (statut `Acceptee`, option B, renvoi a `DEC-0077`).
- `squad/decisions/produit/DEC-0075-corpus-de-reproductions-d-oeuvres-et-droits-d-usage-question-ouverte.md`
  : note ajoutee en tete, champ `Statut` mis a jour ; le corps du document (constat,
  perimetre des neuf lignes, trois options) reste tel qu'ecrit le 2026-09-03, non efface.
- Signale a `sofia-sm`, hors de ce fichier : routage vers `theo-archi` des questions de source, de
  preuve de domaine public, de format et de verification du corpus d'oeuvres (section 4) ; routage
  vers `nadia-prof` de la question de la marge de rejeu (section 3), a trancher avant la fermeture
  du perimetre de s29.
- `squad/programme/MATRICE-couverture.md` : inchangee par cette decision quant a ses lignes ;
  deux bilans de matiere perimes y sont corriges par l'amendement de la section 7 ci-dessous.

## 7. Amendement du 2026-09-05 (meme jour) - deux bilans de matiere perimes corriges, question de garde-fou routee

**Ce que j'ai verifie, a la demande de `sofia-sm`.** Le "Bilan" de la table *Questionner le monde -
CP* affichait encore "15 `non couvert`" apres les trois fermetures de s28 (`QLM-CP-01`, `QLM-CP-03`,
`QLM-CP-05`) : la fermeture avait mis a jour les trois lignes concernees sans mettre a jour le total
qui les resume. J'ai verifie les dix-sept autres bilans de matiere du meme fichier, table par table,
en recomptant chaque tableau ligne a ligne plutot qu'en reprenant un total ecrit :

- **Quatre tables (mathematiques CP, mathematiques CM1, francais CP, francais CM1) ne portent aucune
  phrase de bilan** : ce sont les quatre tables les plus anciennes, dont l'histoire est ecrite ligne
  par ligne au fil des sprints plutot que resumee par une phrase unique. Rien a corriger, rien a
  verifier de cette famille sur elles.
- **Treize tables portent une phrase "Bilan"** (les huit matieres integrees le 2026-09-02 et les dix
  referentiels integres le 2026-09-03). **Deux sont perimees** :
  - *Questionner le monde - CP* : "16 lignes, 15 `non couvert`, 1 `non couvrable en l'etat`"
    -> corrige en "16 lignes, 12 `non couvert`, 3 `couvert-valide`, 1 `non couvrable en l'etat`"
    (denominateur atteignable inchange, 15).
  - *Langues vivantes (anglais) - CM1* : "7 lignes, 4 `non couvert`, 3 `non couvrable en l'etat`"
    -> corrige en "7 lignes, 2 `non couvert`, 1 `en cours`, 1 `couvert-valide`, 3 `non couvrable en
    l'etat`" (denominateur atteignable inchange, 4).
  - **Les onze autres** (histoire-geographie CM1, langues vivantes CP, arts plastiques CP et CM1,
    education musicale CP et CM1, EPS CP et CM1, EMC CP et CM1, histoire des arts CM1, sciences et
    technologie CM1) **sont exacts** : soit la matiere n'a pas ete touchee par s28, soit son bilan
    avait deja ete mis a jour dans le meme geste que la fermeture (histoire-geographie CM1 porte
    d'ailleurs deux bilans dates, l'un a la cloture de s27, l'autre a celle de s28, tous deux
    exacts).
- **Le total du fichier n'etait pas affecte** : le recompte du 43/138 (section 1) porte sur les
  lignes elles-memes, pas sur les phrases de bilan qui les resument ; les deux corrections
  ci-dessus ne changent donc aucun chiffre cite ailleurs dans cette decision ou dans la synthese de
  cloture de s28.

**Ce que je route, sans le construire moi-meme.** Le motif de cette derive est mecanique et se
reproduira tant que rien n'y change : un verdict qui deplace une ligne (fermeture ou ouverture) met
a jour la ligne, mais rien n'oblige a mettre a jour, dans le meme geste, le total ecrit en dessous
du tableau - deux gestes distincts, dans le meme fichier, et rien qui les relie. C'est la meme
famille de defaut qu'une reference qui pointe vers rien ou qu'un chiffre sans methode : un total
qui peut se lire juste alors qu'il decrit un autre etat que celui du tableau qu'il resume, et le
mode de defaillance n'est pas l'echec mais l'ecart muet - personne n'est averti, le fichier reste
lisible, le total est simplement faux. **Je pose la question, je ne la tranche pas** : faut-il un
garde-fou qui verifie mecaniquement qu'un bilan de matiere correspond au compte reel des lignes du
tableau qu'il resume (calcul, script, ou autre forme que `theo-archi` jugera), ou bien la revue de
`sofia-sm` a chaque fusion de matrice suffit-elle comme controle humain ? Je n'ai pas d'avis a
imposer sur le moyen : c'est une question d'outillage, pas de produit. **Route a `sofia-sm`, pour
instruction par `theo-archi` si elle le juge necessaire**, a poser maintenant plutot qu'apres une
troisieme derive.

## 8. Amendement du 2026-09-05 (meme jour) - `nadia-prof` decline le perimetre annonce, l'utilisatrice
## arbitre la parallelisation, la borne de s29 passe a 8,0, et quatre points sont routes a moi

### 8.1 Le perimetre de la section 2 ne tient pas - `nadia-prof` le decline par ecrit

`squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`
(`11265c1`) recompte le tarif des quatre matieres que la section 2 annoncait a 1,0
chacune, cinq lots, 5,0 sur sa borne. **Le prix reel vaut 6,0 a 7,0, pas 5,0** : `nadia-prof` n'est
pas la source en sciences et technologie CM1 ni en enseignement moral et civique CM1 (1,5 chacune,
ecrit de sa main le 2026-09-03 dans les referentiels eux-memes), et l'EMC CP est mixte - 1,0 tant
qu'un lot reste restreint a ses sept premieres lignes, 1,5 des qu'il touche les symboles, la
devise, les droits de l'enfant, la commune ou les numeros d'urgence. **Le perimetre annonce est
decline. Ce qui tient dans 5,0 est quatre lots, pas cinq**, et son plafond de fermeture est de huit
lignes, pas dix (section 6 de son document).

### 8.2 L'utilisatrice arbitre la parallelisation - la borne de s29 passe a 8,0

Sur une question posee le meme jour - lever la borne en faisant travailler deux instances de
`nadia-prof` en parallele sur des lots disjoints - `nadia-prof` repond en section 13 de son
arbitrage (`2956c7b`), sous forme de clauses opposables (`PAR-0` a `PAR-8`) plutot que de
commentaire, motif qu'elle donne elle-meme en section 9 de son document.

**Ce que l'utilisatrice retient, integralement** :

- **La borne de s29 est de 8,0, pas 10,0.** Deux instances a 5,0 donnent une capacite brute de 10,0
  d'audit de lots ; `nadia-prof` en retire 0,5 parce que la passe transverse cesse d'etre un
  sous-produit gratuit du mode serie et devient une tache payee (`PAR-4`), 0,5 parce que les dettes
  de portee "depot entier" deviennent payables et doivent l'etre, et 1,0 parce que la seconde
  instance en est a son premier sprint de parallelisation et qu'un essai a deja produit une
  collision d'identifiant (la collision `ART-1` du 2026-09-03).
- **Plafond individuel : 5,0 par instance, au plus trois lots par instance** (`PAR-8`). Sans ce
  plafond, les 8,0 pourraient se concentrer sur une seule instance, ce que `PAR-0` refuse
  explicitement : la borne protege un jugement qui ne se divise pas, pas seulement un debit de
  lecture.
- **Une marge de rejeu de 0,5 par instance est nommee a l'ouverture et n'est allouee a aucun lot.**
  Elle ne descend dans aucune story de ce document.

### 8.3 Variante A retenue, et ce qu'elle achete est ecrit sans l'adoucir

`nadia-prof` chiffre deux variantes a 8,0 (section 13.6 de son arbitrage) : **A - dettes**, quatre
lots, +9 lignes (52/138 = 37,7 %), toutes les dettes payees, 1,0 de marge de rejeu ; **B -
couverture**, cinq lots, +11 lignes (54/138 = 39,1 %), aucune dette payee. **L'utilisatrice retient
A**, en connaissance du fait que `nadia-prof` ecrit elle-meme en tete de sa section 13.6, avant
qu'il ne soit decouvert en review : **la variante A ne ferme pas une ligne de plus qu'un sprint en
serie a 5,0** - la composition 2 de sa section 8.2, a une seule instance, plafonnait deja a neuf
lignes. **Le doublement de la borne n'achete donc aucune couverture.** Ce qu'il achete, et c'est
reel : `POS-R1` payee (une condition de `nadia-prof` violee sur une ligne qu'elle a fermee sous
cette condition), les six reserves textuelles payees, la dette des paquets a voix payee apres
quatre sprints d'attente, et 1,0 de marge de rejeu qui n'a jamais existe dans ce depot. **Elle a vu
la variante B, qui fermait deux lignes de plus sans payer aucune dette, et elle a choisi A quand
meme.**

### 8.4 Composition retenue - 7,5 planifies sur une borne de 8,0

| | **Instance A** - porte l'accumulation (`PAR-7`) | **Instance B** |
|---|---|---|
| **Lots** | Questionner le monde CP, **1,0** (`QLM-CP-02`, `QLM-CP-07`) ; histoire-geographie CM1 composition A, **1,5** (`HG-CM1-06`, `HG-CM1-07`, `HG-CM1-14`) | EMC CP restreint aux lignes 1 a 7, **1,0** (lignes 6 et 2) ; EMC CM1, **1,5** (lignes 11 et 7) |
| **Transverse** | passe transverse **0,5** (`PAR-4`) ; six reserves textuelles, un passage mot pour mot **0,25** ; dette des paquets a voix, un passage **0,25** | `POS-R1`, rejeu borne **0,5** |
| **Marge de rejeu** | **0,5**, non allouee | **0,5**, non allouee |
| **Total** | **4,0** sur un plafond individuel de 5,0 | **3,5** sur un plafond individuel de 5,0 |
| **Hors borne** | l'arbitrage prealable du palier 2 - six lignes debloquees pour s30, cout nul contre la borne | - |

Instance B porte l'EMC aux deux niveaux **parce que c'est la seule matiere du perimetre ou
personne n'a d'accumulation** (`PAR-7`) : on ne demande pas a une instance de rattraper une image
qu'elle n'a pas, on lui donne le terrain ou l'image n'existe pour personne.

**Plafond de fermeture, avec sa composition** : `QLM-CP-02` +1, `QLM-CP-07` +1, `HG-CM1-06` +1,
`HG-CM1-07` +1, `HG-CM1-14` +1, EMC CP ligne 6 +1, EMC CP ligne 2 +1, EMC CM1 ligne 11 +1, EMC CM1
ligne 7 +1 = **+9, soit 52 / 138 = 37,7 %.** **Attente raisonnable de `nadia-prof` : 7 a 8 lignes,
soit 50 a 51 / 138, c'est-a-dire 36,2 % a 37,0 %.**

### 8.5 Le mode de defaillance neuf - repris mot pour mot, pas traduit

`nadia-prof` ecrit deux phrases que cette decision reprend telles quelles, parce que les reformuler
serait exactement le defaut qu'elle nomme en section 9 de son document - un texte qui redevient
faux des qu'on le lit dans un autre registre que celui ou il a ete ecrit :

> *"Ca casse d'abord sur la couture, et ce mode de defaillance n'existait pas au sprint dernier. Il
> est silencieux : rien ne rougit, la ligne ferme, la propriete n'a ete verifiee par personne."*

> *"Deux paires d'yeux sur des lots disjoints, ce sont deux paires d'yeux, pas un double controle.
> Aucune des deux instances ne relit l'autre."*

Ce que cela signifie pour la squad, sans l'attenuer : la parallelisation ne fabrique aucune seconde
lecture d'un meme item, et elle ne remplace aucune des proprietes dont `nadia-prof` reste, seule,
la garde (section 10 de son arbitrage). Ce que le refus tardif de s28 avait deja montre sur le
calendrier reste vrai a deux instances : un defaut qui casse a la couture ne se voit qu'a la
clause `PAR-*` qui aurait du le prevenir, jamais a la CI.

### 8.6 Deux points deja adoptes, recus sans etre rediscutes

- **`nadia-prof` audite chaque lot le jour de sa livraison**, plus en bloc a la cloture (section
  8.4 de son arbitrage). C'est sa reponse au refus tardif de s28, mesuree comme le vrai probleme de
  calendrier plutot que de marge, et elle ne coute rien contre sa borne.
- **L'arbitrage prealable du palier 2 entre dans le perimetre de s29**, ecrit par `nadia-prof`
  avant que le premier paquet de designation ne soit construit (`ADR-0080`, `DEC-0074` section 4).
  Il bloque six lignes aujourd'hui et ne coute rien contre sa borne : ce n'est pas un lot de
  contenu.

### 8.7 Quatre points routes par `nadia-prof`, et ce que j'en fais

- **Deux libelles faux dans le referentiel de sciences qu'elle a ecrit elle-meme** (*vegetarien*
  pour *herbivore* ; *nutrition* rangee parmi ses propres composantes) - section 1.2 de son
  arbitrage. **Le lot de sciences n'entre pas dans la composition retenue en section 8.4** : aucune
  instance n'ecrit d'item de sciences a s29. **La correction n'attend pas pour autant** : je la
  fais dans le meme geste que le present amendement, directement dans
  `squad/programme/sciences-et-technologie/competences-cm1.md`, parce que `nadia-prof` ecrit
  qu'elle est due "avant qu'un item ne soit ecrit" et qu'un referentiel faux qui reste faux entre
  deux sprints est exactement le risque qu'elle vient de payer une fois avec `SCI-1`.
- **Le lot de fermeture porte vingt-deux items en deux paquets de onze, impose par `DEC-0064`, sans
  changement de prix** (section 3.2 de son arbitrage). Ecrit dans les criteres d'acceptation de
  chaque story qui ouvre une matiere neuve a deux lignes (`S29-1`, `S29-4`, `S29-5` ci-dessous) ;
  sans objet sur le lot d'histoire-geographie, qui complete un fichier existant a onze lignes
  declarees plutot que d'en ouvrir un neuf.
- **`CYC-1`, portee neuve** - la continuite entre le cycle 2 et le cycle 3 sur un meme objet,
  qu'aucune de ses conditions ne nommait avant ce jour. **L'ajout est permis, la contradiction ne
  l'est pas.** Ecrite comme obligation de lecture dans les criteres d'acceptation des lots de
  cycle 3 de s29 ; elle ne mord aucun des trois lots d'histoire-geographie retenus en composition
  A (rois, Revolution, comprendre un document n'ont pas d'equivalent en questionner le monde CP),
  mais elle reste opposable a tout lot futur qui recouvrirait un objet deja enseigne au CP - au
  premier chef le futur lot de sciences (`QLM-CP-05`, etats de l'eau, et la ligne 1 des sciences,
  etats de la matiere).
- **Cent quarante-sept identifiants de conditions distincts sur trente prefixes, aucun registre
  nulle part, vingt-quatre portant le numero 1** (`PAR-5`). C'est un probleme de gouvernance des
  conditions que `nadia-prof` route explicitement sans en decider la forme. Je ne tranche ni la
  forme ni la priorite : je le signale a `sofia-sm`, pour instruction par `theo-archi` s'il le juge
  necessaire, au meme titre que la question du garde-fou de bilan de matiere posee en section 7.
  Aucune story de ce document ne construit ce registre.

### 8.8 Ce que cet amendement ne fait pas

- Il ne remplace pas les sections 1 a 7 : la decision sur `DEC-0075` (section 4) et les deux
  bilans de matiere corriges (section 7) restent tels qu'ecrits.
- Il ne prononce aucun Done pedagogique et ne fait bouger aucune ligne de
  `squad/programme/MATRICE-couverture.md` : les lots de la composition retenue en section 8.4 ne
  sont pas encore construits.
- Il ne tranche pas la forme d'un registre d'identifiants de conditions (`PAR-5`) ni celle d'un
  garde-fou de bilan de matiere (section 7) : les deux restent routees a `sofia-sm` et
  `theo-archi`.

## 9. Amendement du 2026-09-06 (cloture de s29) - le sprint ferme neuf lignes, pas huit ; le cout
## reel et la marge de rejeu, actes

### 9.1 Le recompte des lignes fermees, avec l'ecart trouve

`nadia-prof` annonce, dans son releve d'Instance A (`verdicts-nadia-instance-A.md`) : *"Total
Instance A : cinq lignes ferment. Avec les trois de l'Instance B, huit pour le sprint [...] 51 /
138 = 37,0 %."* **Ce compte est le sien ; le compte de la matrice est le mien** (section 8.3 le
disait deja, avant que le sprint ne soit joue), et il differe.

**Mon recompte, ligne a ligne, colonne par colonne, sur les dix-huit tableaux de
`squad/programme/MATRICE-couverture.md` apres application de tous les verdicts de s29** :
`couvert-valide` 52, `en cours` 7, `non couvert` 79, `non couvrable en l'etat` 71, 209 lignes,
138 au denominateur atteignable. **52 / 138 = 0,3768... = 37,7 %.**

**L'ecart** : neuf lignes ferment, pas huit. `nadia-prof` compte cinq fermetures pour l'Instance A
et trois pour l'Instance B (`EMC-CP-02`, `EMC-CM1-11`, `EMC-CM1-07`, toutes fermees a son reaudit
du 2026-09-06). **Son compte omet `EMC-CP-06`** (`bandit-manchot-regles-de-vie-cp`), fermee des le
premier passage de l'Instance B le 2026-09-05 (`5c35f6c`) : *"EMC-CP-06 ferme, sous reserve non
bloquante"*, avec Done pedagogique prononce et mention exigee ecrite dans
`squad/conformite/FICHE-bandit-manchot-regles-de-vie-cp.md`. Cette ligne n'est ni un refus, ni un
verdict retire : elle est fermee, datee, sourcee, et son statut n'a jamais bouge depuis. Le fait
que `nadia-prof` la nomme separement dans son releve d'Instance A (*"EMC-CP-06 avait ferme au
premier passage de l'Instance B"*), sans l'ajouter a son propre compte de huit, est la cause
exacte de l'ecart.

**Ce chiffre n'est pas surprenant : il est celui que la composition de la section 8.4 annoncait
comme plafond arithmetique avant que le sprint ne soit joue** - "`QLM-CP-02` +1, `QLM-CP-07` +1,
`HG-CM1-06` +1, `HG-CM1-07` +1, `HG-CM1-14` +1, EMC CP ligne 6 +1, EMC CP ligne 2 +1, EMC CM1 ligne
11 +1, EMC CM1 ligne 7 +1 = +9, soit 52 / 138 = 37,7 %" (section 8.4 ci-dessus). **Les neuf lignes
visees ferment toutes les neuf : le sprint atteint son plafond, pas seulement le haut de la
fourchette attendue (36,2 % a 37,0 %).** `squad/programme/MATRICE-couverture.md` est mis a jour a
52/138 = 37,7 % dans le meme geste que le present amendement ; le detail ligne a ligne, les
mentions exigees et les reserves ouvertes y sont ecrits.

**Ce que je ne fais pas de cet ecart** : il ne remet en cause aucun des neuf verdicts eux-memes,
qui restent ceux de `nadia-prof` avec leurs motifs entiers. C'est une erreur d'addition sur un
total qu'elle a elle-meme qualifie de n'etre pas le sien a tenir (*"le compte de la matrice
appartient a `camille-po` ; je ne la touche pas"*) - exactement pourquoi ce compte est verifie ici
plutot que recopie.

### 9.2 Le cout reel du sprint, et la marge de rejeu qu'il consomme

**Instance A** : 4,0 sur son plafond individuel de 5,0 (3,5 planifies + 0,5 non planifies -
`VIV-R2` tranchee et le jugement du texte neuf `hill`/`colline` - absorbes par sa marge
individuelle de 0,5, `PAR-8`). **Aucun tirage sur la marge de rejeu du sprint.**

**Instance B** : 5,25 sur son plafond individuel de 5,0 (3,0 au premier passage + 2,25 au reaudit).
Son plafond individuel absorbe 2,0 des 2,25 non planifies ; **les 0,25 restants sont tires sur la
marge de rejeu du sprint**, qu'elle nomme elle-meme comme le geste pour lequel cette marge existe.

**Total du sprint : 9,25 sur la borne de 8,0** (`PAR-8`, section 8.2 ci-dessus). La marge de rejeu
nommee a l'ouverture (1,0, non allouee) est tiree a hauteur de 0,25 par l'Instance B ; **elle
passe de 1,0 a 0,75 pour ce qu'il en reste apres s29** - la meme forme de gestion que celle que
`DEC-0076` avait actee a la cloture de s28 (5,25 sur une borne de 5,0), reconduite ici sur une
borne doublee et une marge nommee plutot que decouverte au refus.

**Ce que ce depassement achete** : `nadia-prof` a trouve, au reaudit, une propriete qu'elle
n'avait pas vue au premier passage (`POS-R2`, la troisieme lecture de la division posee, section 2
de son releve d'Instance B) et a corrige deux de ses propres conditions incoherentes entre elles
(`ANG-R1`, `VIV-R2`, section 5.1 de la passe transverse). **Elle n'a facture aucun supplement
pour ces deux corrections** : le tirage de 0,25 ne vient que de la recherche de la troisieme
lecture sur la division, qu'elle avait annoncee d'avance comme une question ouverte et non une
liste a cocher.

### 9.3 Ce que cet amendement ne fait pas

- Il ne revoque aucun verdict de `nadia-prof` : les neuf fermetures, leurs motifs, leurs mentions
  exigees et leurs reserves restent siens, dans les fiches ou elle les a ecrits.
- Il ne prononce aucun Done pedagogique.
- Il ne tranche pas `CONS-1` (traite par `DEC-0078`) ni la cause technique de `REV-1` (routee a
  `theo-archi`, cf. `docs/backlog/backlog.md`).

### 9.4 Amendement du 2026-09-06 (meme jour) - la reverification de `REV-1` porte le cout reel a
### 9,50 ; la marge de rejeu ne bouge pas

`nadia-prof` a mene une reverification apres la cloture actee en section 9.1 et 9.2, sur le
correctif de `REV-1` livre entre-temps (`theo-archi`, `ADR-0083`, commit `8ef4fb2`) : **VALIDE SOUS
RESERVE**, deux reserves neuves ouvertes (`REV-2`, refusee et remplacee par un texte prescrit mot
pour mot ; `REV-3`, routee, cause structurelle). Ecrit au long dans
`squad/sprints/s29-parallelisation-et-dettes-payees/verdicts-nadia-instance-A.md`, derniere
section, et dans `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section
"Reverification du 2026-09-06". **Prix : 0,25, annonce d'avance.**

**Mon recompte, poste par poste, sur les chiffres actes en section 9.2** : l'Instance A passe de
**4,0/5,0 a 4,25/5,0** (3,5 planifies + 0,5 non planifies a la cloture + 0,25 de la
reverification, toujours sous son plafond individuel de 5,0). L'Instance B ne bouge pas (5,25/5,0,
inchangee). **Le total du sprint passe de 9,25 a 9,50 sur la borne de 8,0** (4,25 + 5,25).

**La marge de rejeu ne bouge pas.** `nadia-prof` l'ecrit elle-meme, mot pour mot, en cloture de son
releve : *"Rien de tire sur la marge de rejeu du sprint, qui garde ses 0,75."* Le supplement de
0,25 tient dans le plafond individuel de l'Instance A (4,0 -> 4,25, toujours sous 5,0) ; il ne
touche pas la marge de rejeu du sprint, qui reste a **0,75** apres le seul tirage de l'Instance B
acte en section 9.2.

**Ce que cet amendement corrige, et ce qu'il ne fait pas.** Le chiffre de 9,25 ecrit en section 9.2
etait exact au moment ou il a ete ecrit (commit `b93ef31`), avant que cette reverification n'ait
eu lieu ; il est perime depuis. Corrige dans le meme geste dans `DEC-index.md` (ligne `DEC-0077`)
et dans `squad/programme/MATRICE-couverture.md` (synthese de cloture de s29). **Il ne revoque
aucun des neuf verdicts de fermeture de ligne, ni le verdict de `REV-1` lui-meme** : aucune ligne
de la matrice ne bouge par ce recompte. Il ne prononce aucun Done pedagogique et ne tranche ni
`REV-2` ni `REV-3` - la premiere est deja tranchee par `nadia-prof` dans son releve (texte de
remplacement prescrit), la seconde reste routee a `theo-archi`.
