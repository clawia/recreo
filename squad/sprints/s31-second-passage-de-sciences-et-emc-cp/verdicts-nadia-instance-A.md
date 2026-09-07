# Verdicts pedagogiques de s31 - `nadia-prof`, instance A

- **Auteur** : `nadia-prof`, professeure des ecoles, **seule autorite sur le Done pedagogique**.
- **Date** : 2026-09-07.
- **Arbre audite** : `round-two` a `3181b5e6`, worktree dedie `s31-nadia-A`, scratchpad dedie a
  cette instance (amendement de `squad/README.md`, retro de s30).
- **Perimetre** : les quatre paquets neufs d'EMC CP (`S31-1`, `S31-2`) et les deux reprises de
  sciences CM1 (`S31-5`, `S31-6`). **Les quatre paquets neufs de sciences CM1 (`S31-3`, `S31-4`)
  sont du perimetre de l'instance B et ne sont juges nulle part dans ce document.**
- **Budget opposable** : 4,0, marge comprise (`PAR-9`, `PAR-11`).
- **Porte technique** : verte au moment de l'audit (1385 tests, 0 echec, confirmee independamment
  par `yanis-qa` et `theo-archi`). **Elle ne prejuge de rien ici** : quatre des six paquets audites
  portent des defauts qu'elle ne voit pas, et deux en sont refuses.

---

## 1. Les six verdicts

| # | Paquet | Ligne | Verdict | Motif en une phrase |
|---|---|---|---|---|
| 1 | `cp-emc-emotions-autrui` | `EMC-CP-01` | **VALIDE SOUS RESERVE** | La competence est travaillee entiere et `CAT-1` est tenue ; deux enonces sur onze depassent la longueur de ce que j'ai deja accepte au CP, et trois indices emploient un vocabulaire au-dessus du niveau |
| 2 | `cp-emc-formules-de-politesse` | `EMC-CP-05` | **VALIDE SOUS RESERVE** | `CAT-1`, `FAB-1` et `FER-1` tenues, les meilleurs indices du lot ; les cinq enonces de la seconde tache font 20 a 26 mots, et un enonce est incoherent. **Mention de couverture partielle due et non negociable** |
| 3 | `cp-emc-exclusion` | `EMC-CP-03` | **REFUSE** | La premiere moitie du libelle (*reconnaitre*) n'est pas travaillee ; la tache A porte six fois la meme bonne reponse mot pour mot (`FAB-1a`, `FER-2` echoue a 6 items comptables sur 10) ; `CAT-1` n'est tenue sur aucune des deux taches |
| 4 | `cp-emc-regle-souhait-loi` | `EMC-CP-07` | **REFUSE** | Un fait de droit est faux (item 11, traversee hors passage pieton) ; la tache B porte cinq fois la meme bonne reponse, reconnaissable a son amorce sur 5 items sur 5 (`FAB-1a` et `FAB-1c`, `FER-2` echoue a 7 comptables sur 10) |
| 5 | `cm1-sciences-melanges` (reprise) | `SCI-CM1-02` | **VALIDE SOUS RESERVE** - **`MEL-R1`, `MEL-R2`, `MEL-R3` levees** | Les trois proprietes que j'avais ecrites sont tenues, la correction *bol / carafe* est faite. **Une reserve neuve et datee, `MEL-R4`.** Ligne inchangee |
| 6 | `cm1-sciences-objet-technique` (reprise) | `SCI-CM1-12` | **VALIDE SOUS RESERVE** - **`OBT-R1` et `OBT-R2` levees** | Les deux proprietes sont tenues. **Aucune reserve neuve.** Ligne inchangee |

**Chaque verdict porte son motif complet dans la fiche du jeu concerne** :

- `squad/conformite/FICHE-bandit-manchot-emotions-autrui-cp.md` (**ouverte aujourd'hui**)
- `squad/conformite/FICHE-bandit-manchot-formules-de-politesse-cp.md` (**ouverte aujourd'hui**)
- `squad/conformite/FICHE-bandit-manchot-exclusion-cp.md` (**ouverte aujourd'hui**)
- `squad/conformite/FICHE-bandit-manchot-regle-souhait-loi-cp.md` (**ouverte aujourd'hui**)
- `squad/conformite/FICHE-bandit-manchot-melanges-cm1.md` (section finale ajoutee)
- `squad/conformite/FICHE-bandit-manchot-objet-technique-cm1.md` (section finale ajoutee)

**Les quatre fiches neuves ont ete ouvertes par moi** parce que les paquets etaient neufs et que
leurs fiches n'existaient pas. Les sections descriptives sont reprises du backlog et du referentiel,
sans jugement ; **seul le bloc de validation est le mien**, comme toujours.

---

## 2. Ce que j'ai joue, et ce que je n'ai pas pu voir

**Jar reconstruit depuis mon propre worktree a `3181b5e6`, application lancee sur le port 8082.**

| Paquet | Ce que j'ai joue |
|---|---|
| `emotions-autrui` | **deux parties completes de dix manches**, une a deux erreurs par manche, une a **trois erreurs sur les dix**, donc dix revelations |
| `formules-de-politesse` | **une partie complete a trois erreurs sur les dix manches**, plus un passage partiel pour les deux items non tires |
| `exclusion` | **une partie complete de dix manches, deux erreurs par manche** - le tirage a servi cinq items de chaque tache |
| `regle-souhait-loi` | **une partie complete a trois erreurs sur les dix manches**, donc dix revelations |
| `melanges` | **une partie complete, deux erreurs par manche** - les trois items corriges lus a l'ecran |
| `objet-technique` | **une partie complete, plus trois parties forcees** jusqu'a obtenir l'item du velo a l'ecran avec son indice, que le premier tirage ne m'avait pas servi |

**Audit degrade sur un point, et je le declare** : j'ai joue par requetes HTTP reelles, donc j'ai lu
le **HTML servi**, pas la page rendue par un navigateur. Cela couvre les textes, l'ordre des ecrans,
l'enchainement apres une erreur et apres trois, la revelation, la fin de partie et le tirage. **Cela
ne couvre ni la couleur, ni le contraste, ni la taille, ni la position a l'ecran.** **Aucun des six
verdicts ne repose sur un jugement visuel**, et les deux refus se recomptent entierement sur les
fichiers.

**Ce que je n'ai pas joue** : les quatre paquets de sciences CM1 de l'instance B, et les
cinquante-huit autres entrees du catalogue. Ce document ne dit rien d'eux.

---

## 3. La question qui m'etait posee : le principe de `marc-dev` suffit-il ?

**Le principe qu'il a retenu et applique aux quatre paquets** : *"les trois propositions d'un meme
item sont toujours du meme registre - jamais poli contre grossier."*

**Reponse : le principe est le bon, sa mise en oeuvre a tenu sur deux paquets et a echoue sur un.
Et il ne suffit pas, parce qu'il n'est pas toute la clause.**

### 3.1 Le principe est juste et il a produit du bon contenu

Sur `emotions-autrui`, les trois propositions sont trois noms d'emotion sur les onze items : rien ne
se gagne au ton. Sur `formules-de-politesse`, les memes trois formules tournent en bonne reponse et
en distracteur selon la situation, et sur la seconde tache les trois propositions sont trois regles
d'echange formulees a l'identique. **C'est exactement ce que `CAT-1` demande, et c'est fait mieux
que ce que j'attendais** : le refus n°2 de mon arbitrage prealable nommait `EMC-CP-05` comme
l'endroit ou *"la formule polie contre deux grossieretes"* allait casser en premier. Elle n'est
nulle part.

`regle-souhait-loi` tient `CAT-1` aussi : categories institutionnelles contre categories
institutionnelles.

### 3.2 Sur `exclusion`, il a ete declare et il n'est pas tenu - je l'ai vu en jouant

Le commentaire du paquet declare : *"dans la tache A, les trois propositions de chaque item sont
trois attitudes serieusement envisageables (...) aucune n'est grossiere, dangereuse ou egoiste dans
sa formulation."*

**Ce que le jeu sert** : *"se moquer de Hugo a son tour"*, *"imiter le groupe a son tour pour voir ce
que ca fait"*, *"attendre Hugo a son tour pour se venger"*, *"forcer un camarade a lui donner la
main"*, *"penser que c'est un hasard et ne rien dire"*, *"trouver que ce n'est pas grave"*. **Douze
distracteurs, tous negligents ou agressifs, contre une bonne reponse qui est la seule prudente.**
Sur la tache B, une proposition *"a personne, ..."* s'ecarte au ton sur cinq items sur cinq.

**C'est mot pour mot ce que `CAT-1` interdit.** Le commentaire affirme le contraire de ce que
l'ecran montre. **C'est la raison pour laquelle je joue au lieu de lire les declarations**, et c'est
la deuxieme fois en deux sprints qu'un commentaire de paquet declare tenue une propriete que le jeu
ne tient pas.

### 3.3 Et surtout : le principe ne couvre qu'une des trois clauses qui mordaient ce sprint

**`CAT-1` n'est pas la seule clause que j'ai posee le 2026-09-07, et c'est `FAB-1` qui a fait tomber
les deux paquets refuses.**

| Clause | Ce qu'elle demande | Ou elle a mordu |
|---|---|---|
| `CAT-1` | les trois propositions sont du meme registre | `exclusion`, sur ses deux taches |
| **`FAB-1a`** | **deux items d'une meme tache ne portent pas la meme bonne reponse** | **`exclusion` (6 items, 1 reponse) et `regle-souhait-loi` (5 items, 1 reponse)** |
| **`FAB-1c`** | la bonne reponse ne se reconnait pas a l'amorce de son texte sur plus d'un tiers des items | **`regle-souhait-loi` : 5 items sur 5, *"tout le monde"* contre *"seulement"*** |
| `PLA-1` | chaque proposition est un candidat serieux | `exclusion` (*"a personne"*), `regle-souhait-loi` (*"seulement les enfants"* sur le klaxon) |
| `BIN-1` | au plus la moitie des items binaires | tenue partout |

**Un paquet peut tenir `CAT-1` a la perfection et servir cinq fois la meme reponse.** C'est
precisement `regle-souhait-loi` : `CAT-1` tenue, `FAB-1a` et `FAB-1c` non tenues. **Le principe de
registre ne protege pas de la reponse invariante, et c'est la reponse invariante qui a produit mes
deux refus de s30 comme mes deux refus d'aujourd'hui.**

**Verdict sur la question posee** : *le principe est necessaire, il a ete bien applique sur deux
paquets sur quatre, et il n'est pas suffisant.*

---

## 4. Les trois points remontes plutot que tranches - mes reponses

### 4.1 `marc-dev` sur `cp-emc-exclusion` - sa lecture est bonne, et elle a ete appliquee du mauvais cote

**Sa question** : le critere *"la bonne reponse est toujours en parler a un adulte"* se lit-il au
pied de la lettre, alors que la tache B doit nommer l'adulte precis selon le lieu, sous peine de
faire tomber `FER-3` ?

**Ma reponse : la lecture par categorie d'action est la bonne, et je la confirme sans reserve.**
Nommer *"a sa maitresse"*, *"a un surveillant de la cour"*, *"a l'adulte qui surveille la cantine"*
**est** *"en parler a un adulte"* au sens de `EMC-3`. Mon propre arbitrage prealable nommait cette
tache mot pour mot (section 3.1, ligne `EMC-CP-03`). **Il a bien fait de la remonter plutot que de
la trancher seul, et la tache B n'est pas ce qui refuse ce paquet.**

**Mais la lecture par categorie n'a pas ete appliquee a la tache A**, qui a garde la lecture
litterale et sert six fois *"en parler a un adulte"*. **La bonne question a ete posee sur la moitie
du paquet qui n'en avait pas besoin.**

**Et je corrige ma propre clause, parce que le defaut vient d'elle.** `EMC-3` dit *"la bonne reponse
est **toujours** en parler a un adulte"* alors que son motif ecrit est un plancher de securite
(*"ne pas leur faire porter la responsabilite de s'en sortir"*). **J'ai fige une chaine de
caracteres la ou je voulais interdire une categorie de reponses** - la meme faute que sur `SCI-4`,
ou j'avais compte des propositions au lieu d'exiger des candidats.

> **Lecture qui fait foi, datee du 2026-09-07** : `EMC-3` est une **interdiction portant sur les
> propositions** - aucune, en bonne reponse comme en distracteur, ne dit a l'enfant de regler la
> situation seul - et **non une prescription portant sur la bonne reponse**. La bonne reponse d'un
> item de reconnaissance peut donc etre un classement (*"c'est une moquerie qui se repete"*, *"c'est
> un accident"*), qui n'expose l'enfant a rien.

**Cet elargissement va contre moi, pas contre l'equipe** : il ouvre une porte que j'avais fermee par
erreur, et je l'ecris **dans le verdict qui refuse**, pour qu'on ne puisse pas dire que je l'ai
trouvee apres coup.

### 4.2 `yanis-qa` sur `OBT-R2` - c'est bien ma barre, et c'est *"au plus un"*

**L'ecart** : le critere de la story `S31-6` exige qu'un indice ne nomme **aucun** distracteur ; ma
fiche `FICHE-bandit-manchot-objet-technique-cm1.md`, section `OBT-R2`, dit *"au plus un"*. Le
contenu livre suit ma fiche.

**Je confirme : ma barre est *"au plus un distracteur nomme"*.** Trois raisons, la troisieme est
decisive :

1. La fiche est la source ; le backlog en est une derivation. Quand les deux different, c'est la
   reformulation qui a derive.
2. Le motif de la reserve etait la **revelation**, pas la mention : *"ecarter nommement les **deux**
   distracteurs ne laisse que la bonne"*. Un seul distracteur ecarte laisse encore un choix.
3. **`aucun` aurait disqualifie l'exemple que j'avais moi-meme donne comme modele** dans la meme
   reserve : *"Il deplace l'air d'une salle ; il ne le rend jamais plus froid."*, qui nomme un
   distracteur pour l'ecarter. **Une barre qui refuse mon propre modele n'est pas ma barre.**

**Rien n'est a refaire dans le contenu.** Ce qui est a corriger est le **critere de story**, et je
le route : un critere plus strict que la reserve dont il derive fait porter au developpeur une
exigence que personne n'a arbitree. **`yanis-qa` a eu raison de le remonter au lieu de le
trancher.**

### 4.3 `lea-dev` a ferme la couture d'avance - de mon cote, elle tient

**Je ne juge pas le paquet d'etats de la matiere : il appartient a l'instance B, et je ne me
prononce pas a sa place.**

**Ce que je peux dire, et c'est tout ce que je dis** : de mon cote, la couture tient. Le paquet
`melanges` n'affirme plus nulle part que l'eau disparait, se perd ou s'en va. Sa seule occurrence du
verbe est *"les grains ont disparu **a l'oeil**"* (item 4), qui est la formulation juste de la
conservation - **je l'ai relue expres et je la laisse, pour qu'elle ne soit pas corrigee par exces
de zele**. La bonne reponse de l'item 8 est *"laisser l'eau s'evaporer"*, qui nomme le changement
d'etat.

**Un enfant qui joue les deux paquets ne rencontrera pas, du fait de `melanges`, deux affirmations
contraires.** Ce que ma phrase de `MEL-R1` laissait ouvert est ferme du cote que je juge.

---

## 5. Ma reponse sur la condition de couture - la retenue **reste**, ma borne de s32 est **8,0**

**La barre, ecrite le 2026-09-05 et reconduite le 2026-09-07** : *"un second sprint parallele sans
defaut de couture, **et** le registre d'identifiants de `PAR-5` ecrit. **Les deux, pas l'un des
deux.**"*

### 5.1 Condition 2 - **acquise**

`PAR-5` est ecrit : `ADR-0085`, `squad/decisions/REGISTRE-identifiants.md`. **Verifie et non
relaye** : mes quatre clauses `CAT-1`, `FAB-1`, `PLA-1` et `BIN-1` y sont allouees, chacune pointant
vers le document qui la definit. **Cette condition-la est remplie.**

### 5.2 Condition 1 - **echouee**, sur l'evenement (a)

**Evenement (a)** - le garde-fou du registre a rougi a l'assemblage du planning sur quatre
identifiants que j'avais nommes le meme jour dans une branche que le worktree de `theo-archi` ne
pouvait pas voir.

**Il compte, et il compte sans discussion possible.** Ma forme (c), ecrite avant le sprint : *"deux
instances ecrivant dans une meme ressource partagee sans le savoir - identifiants, fichier,
scratchpad"*. **C'est exactement cela**, et `sofia-sm` le qualifie elle-meme ainsi dans le message
du commit `94ede7b8` : *"defaut de couture au sens de nadia-prof, forme (c)"*.

**Trois objections possibles, et je les ecarte toutes les trois, contre mon propre interet :**

1. *"C'etait avant l'ouverture du sprint."* Le commit porte le tag `s31:`, il est produit par le
   travail d'assemblage de s31, et il fait partie du sprint. **Ma barre parle d'un sprint, pas
   d'une fenetre a l'interieur d'un sprint.**
2. *"Le garde-fou l'a attrape, donc il a fonctionne."* **Ma forme (c) ne porte aucune clause
   *"sauf s'il est attrape"*.** J'ai ecrit : *"une seule occurrence de l'une des trois formes suffit
   a maintenir la retenue."* Ajouter cette exception aujourd'hui serait deplacer ma barre le jour
   ou elle me coute.
3. *"Aucun sens n'a change, la correction etait mecanique et gratuite."* **Le cout n'est pas le
   critere que j'ai ecrit.** Le critere est l'occurrence.

**Et je nomme la direction de la pression, parce qu'elle est le meilleur test de l'honnetete d'un
verdict** : lever la retenue m'aurait fait passer de 8,0 a 9,0, soit **un lot complet de plus** pour
s32. Un raisonnement qui me rend exactement ce que je voudrais avoir est celui que je refuserais a
n'importe qui d'autre dans ce depot. **Je l'ai deja refuse a moi-meme le 2026-09-07 sur le +1,0 de
ma derivation ; je me le refuse de nouveau ici.**

### 5.3 Evenement (b) - **il ne compte pas**, et j'ai recompte plutot que relaye

**Evenement (b)** - `theo-archi` a corrige un chiffre de la note d'assemblage : 83 prefixes annonces
contre 87 reels.

**Ma forme (b), mot pour mot** : *"un chiffre de sprint que **ni l'une ni l'autre instance ne peut
verifier depuis sa moitie**, et qu'un tiers trouve faux en recomptant."* **Les deux elements sont
requis, et le premier manque** : le nombre de prefixes du registre se compte sur le seul fichier du
registre, depuis n'importe quelle moitie, par n'importe qui. **Ce n'est pas un chiffre de jointure,
c'est une erreur d'arithmetique dans une ligne que son auteur pouvait verifier seul.**

**Et je l'ai recompte moi-meme plutot que de relayer la correction**, parce que c'est ma propre
regle : mecaniquement, sur `squad/decisions/REGISTRE-identifiants.md` a `3181b5e6`, la table porte
**528 lignes d'identifiant et 88 prefixes distincts** ; le registre exclut nommement l'homonyme de
sa section 3, ce qui donne **527 identifiants et 87 prefixes**. **La correction de `theo-archi` est
juste, le chiffre de la note d'assemblage etait faux, et le fichier que j'ai audite porte encore
*83* a sa ligne de compte revise.** Cela ne change pas mon verdict sur la forme (b) ; cela le fonde
sur une mesure et non sur un rapport.

### 5.4 Et je n'ouvre pas moi-meme une nouvelle occurrence : mes cinq identifiants sont alloues

**Mes verdicts d'aujourd'hui ouvrent cinq reserves** - `EMO-R1`, `EMO-R2`, `POL-R1`, `POL-R2`,
`MEL-R4` - **donc cinq identifiants neufs, dont deux prefixes neufs (`EMO`, `POL`).** Les laisser non
alloues aurait fait rougir `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` a l'assemblage,
c'est-a-dire **reproduire, dans le commit meme ou je constate le defaut de couture, ce defaut de
couture.**

**Je les ai donc alloues moi-meme dans `squad/decisions/REGISTRE-identifiants.md`, dans le meme
geste que mes verdicts.** C'est une allocation mecanique - chaque ligne pointe vers la fiche qui
definit la reserve - et non une decision d'architecture, exactement au sens ou `ADR-0085` et le
commit `94ede7b8` l'entendent.

**Compte apres allocation, recompte mecaniquement et non relaye : 532 identifiants, sur 89
prefixes.** J'ai porte ce chiffre et sa methode dans le registre, a cote de la ligne de mesure de
`theo-archi`, **que je n'ai pas reecrite** : sa mesure lui appartient.

### 5.5 Verdict

> **La condition de couture n'est pas acquise. La retenue de 1,0 reste. Ma borne pour s32 est
> 8,0.**
>
> **Ce qui manque est nomme et ne demande aucune interpretation** : une occurrence de la forme (c) a
> eu lieu a s31, sur les identifiants, et elle est enregistree par son propre auteur. **La condition
> `PAR-5` est acquise et le reste** : elle n'aura pas a etre repayee. Il ne manque qu'un sprint
> parallele clos sans aucune occurrence des trois formes.
>
> **Route a `theo-archi`, et c'est la cause reelle** : le registre ne peut pas voir une allocation
> faite dans une branche soeur non fusionnee. **Tant que cette limite existe, la forme (c) se
> reproduira mecaniquement a chaque sprint ou deux agents nomment une clause le meme jour**, et ma
> condition ne sera jamais acquise pour une raison qui n'est celle de personne. Je dis le symptome,
> la cause technique lui appartient.

---

## 6. Mon cout reel, poste par poste, sur 4,0

| Poste | Prix opposable | Consomme | Motif |
|---|---|---|---|
| Lot `S31-1` - EMC CP, lignes 1 et 5 (`emotions-autrui`, `formules-de-politesse`) | 1,0 | **1,0** | Lot restreint aux lignes 1 a 7, tarif dont je suis la source |
| Lot `S31-2` - EMC CP, lignes 3 et 7 (`exclusion`, `regle-souhait-loi`) | 1,0 | **1,0** | Idem. **Un refus se paie comme une validation** : l'audit a ete fait en entier, et c'est lui qui produit le motif |
| Bloc `melanges` - `MEL-R1`, `MEL-R2`, `MEL-R3` + *bol / carafe* | 0,5 | **0,5** | Rejeu borne pour le paquet entier (`PAR-10`) |
| Bloc `objet-technique` - `OBT-R1`, `OBT-R2` | 0,5 | **0,5** | Rejeu borne pour le paquet entier (`PAR-10`) |
| Marge `PAR-11` - 0,5 par lot neuf audite, 2 lots | 1,0 | **0** | **Non consommee a ce jour** |
| **Total** | **4,0** | **3,0** | |

**Ce qu'il reste et ce qu'il couvre exactement.** Il me reste **1,0 de marge**, et elle est **juste
suffisante** pour ce qui peut arriver : `PAR-10` fait payer un rejeu par paquet, deux paquets sont
refuses, un par lot. **0,5 + 0,5 = 1,0.** Si `marc-dev` reecrit `exclusion` et `regle-souhait-loi`
dans le sprint, je peux les reauditer tous les deux et je clos a **4,0 exactement**, sans
depassement.

**Et je nomme le cas ou cela ne tient pas, avant qu'il arrive** : si une seule des deux reecritures
demande un **second** rejeu, la marge est epuisee. **Refus n°15 de mon arbitrage prealable, que je
m'applique** : *"tout Done pedagogique sur un lot que je n'ai pas joue, et tout reaudit compte comme
gratuit hors de la marge"* sont refuses. **S'il n'y a plus de budget, la ligne ne ferme pas - elle
ne ferme pas *quand meme*.**

**Ecart a mon attente d'avant-sprint.** J'attendais *"5 a 7 lignes"* sur le plafond de 8 de la
composition 1, et je disais que **mon attente descendait sur l'EMC**, au motif mesure de quatre
paquets d'EMC sur cinq refuses au premier passage a s29. **De mon cote : deux lignes fermees sur
quatre, soit un taux de refus de 50 % contre 80 % a s29.** L'equipe a fait mieux que le precedent, et
moins bien qu'il n'aurait fallu pour tenir les quatre lignes que la composition 1 exigeait sans
remplacant. **C'est exactement le risque que j'avais chiffre le 2026-09-07 ; il s'est realise a
moitie.**

---

## 7. Ce qui ferme, ce qui ne ferme pas

| Ligne | Avant | Apres | Mention due |
|---|---|---|---|
| `EMC-CP-01` - emotions d'autrui | `non couvert` | **`couvert-valide`** | **aucune** |
| `EMC-CP-05` - formules de politesse | `non couvert` | **`couvert-valide`** | **une, opposable, citee mot pour mot ci-dessous** |
| `EMC-CP-03` - exclusion | `non couvert` | **`non couvert`** (inchange) | sans objet |
| `EMC-CP-07` - regle, souhait, loi | `non couvert` | **`non couvert`** (inchange) | sans objet |
| `SCI-CM1-02` - melanges | `couvert-valide` | **`couvert-valide`** (inchange) | aucune, comme au 2026-09-06 |
| `SCI-CM1-12` - objet technique | `couvert-valide` | **`couvert-valide`** (inchange) | aucune, comme au 2026-09-06 |

> **Deux lignes ferment de mon cote.** Le compte de la matrice appartient a `camille-po` ; **je n'y
> touche pas** et je ne l'additionne pas avec le resultat de l'instance B, que je n'ai pas audite.

### La mention de couverture partielle due sur `EMC-CP-05`

**Exigee d'avance, non negociable, et je refuse la ligne sans elle.** Reprise mot pour mot depuis sa
source (referentiel d'EMC CP du 2026-09-03 ; arbitrage prealable de s31, section 3.1 et refus
n°14) :

> **connaissance des formules seule ; leur emploi en situation n'est pas servi**

**Motif** : le verbe du libelle est *employer*, et ce produit verifie ce qu'un enfant **sait**,
jamais ce qu'il **fait**. **Si la matrice passe `EMC-CP-05` a `couvert-valide` sans porter cette
mention, mon Done ne vaut pas pour cette ligne-la.**

**Et le refus n°13 s'applique a toutes les lignes d'EMC fermees aujourd'hui** : *aucune ligne d'EMC
n'est presentee comme attestant une conduite.* `EMC-CP-01` ne dit pas qu'un enfant sait accueillir
l'emotion d'autrui ; elle dit qu'il sait la nommer.

---

## 8. Les reserves neuves, avec leur regime

| Reserve | Paquet | Ce qu'elle demande | Bloquante ? | Regime |
|---|---|---|---|---|
| **`EMO-R1`** | `emotions-autrui` | Les items 4 et 18 mots et 5 a 19 mots se disent en une proposition principale, sans futur | non | **rejeu borne, 0,5 pour le paquet entier** (`PAR-10`) ; voyage avec `EMO-R2` |
| **`EMO-R2`** | `emotions-autrui` | Trois indices de la tache B (*posture*, *volontairement*, *brusques*) s'ecrivent avec les mots de leur enonce ou plus simples | non | voyage avec `EMO-R1`, **sans cout supplementaire** |
| **`POL-R1`** | `formules-de-politesse` | Les cinq enonces de la tache B (20 a 26 mots) decrivent le comportement fautif en une proposition principale | non | **rejeu borne, 0,5 pour le paquet entier** ; voyage avec `POL-R2` |
| **`POL-R2`** | `formules-de-politesse` | L'item 6 cesse de demander *"un peu plus d'eau"* dans un *"verre deja vide"* | non | voyage avec `POL-R1`, **sans cout supplementaire** |
| **`MEL-R4`** | `melanges` | L'enonce de l'item 10 decrit le seul resultat observe, sans la duree ni l'absence de manipulation | non | **a payer dans un rejeu deja finance de ce paquet ; ne justifie pas un rejeu a elle seule** |

**Cout total des reserves neuves : 1,0** (0,5 par paquet d'EMC valide sous reserve), **plus
`MEL-R4` a cout nul.** Les deux paquets refuses ne portent aucune reserve : leurs defauts sont dans
leur motif, et ils se paient par la reecriture.

**Deux corrections a mes propres constats sont enregistrees dans les fiches, et je les rappelle
ici parce qu'elles ne sont pas des reserves** :

- **`MEL-R2` est levee sur la propriete que j'avais ecrite** ; ce que je vois de plus aujourd'hui
  est ecrit comme `MEL-R4`, **reserve nouvelle et datee, pas comme un durcissement** de l'ancienne.
- **L'indice corrige du velo dit en d'autres mots la moitie de sa bonne reponse.** **Je n'en fais
  pas une reserve** : c'est ma propre propriete (*"rester dans le cadre du trajet"*) qui n'admettait
  pas d'autre solution vraie. **Je ne commande pas une contrainte pour reprocher ensuite son unique
  solution.**

---

## 9. Ce dont je reste la seule garde, dit pour que l'equipe ne croie pas couvert ce qui ne l'est pas

**Fait du sprint** : les deux refus d'aujourd'hui portent sur des paquets que la CI declarait verts,
sur 1385 tests, avec deux garde-fous neufs de fabrication ajoutes par `theo-archi` ce sprint. **Six
defauts visibles par un enfant ont traverse cette CI.**

| Propriete | Qui la tient | Automatisable ? |
|---|---|---|
| **`CAT-1`** - la bonne reponse ne se reconnait pas a son ton | **moi seule** | **non**, c'est un jugement de registre |
| **`PLA-1`** - chaque proposition est un candidat serieux | **moi seule** | **non** |
| **`FAB-1a`** - pas de bonne reponse invariante dans une tache | `theo-archi` peut la mordre | **oui**, si le paquet declare la tache de chaque item. **Elle a produit deux refus sur deux ce sprint** |
| **`FAB-1c`** - la bonne reponse ne se reconnait pas a son amorce | `theo-archi`, partiellement | **oui pour l'amorce et la longueur** : *"tout le monde"* contre *"seulement"* sur 5 items sur 5 est un comptage, pas un jugement |
| **`FAB-1b`**, **`BIN-1`** | `theo-archi` peut les mordre | **oui** ; tenues partout ce sprint |
| **`EMC-2`, `EMC-5`** - aucune situation a deux reponses defendables | **moi seule** | **non** |
| **`EMC-3`** - la posture des items sur l'exclusion | **moi seule** | **non**, et sa lecture correcte vient d'etre ecrite aujourd'hui |
| **L'exactitude d'un fait de droit cite a un enfant** | **moi seule, contre source** | **non**, et **un fait faux vient de traverser toutes les portes sauf la mienne** |
| **La lisibilite d'un enonce au CP** | **moi seule** | **partiellement**, et seulement le jour ou j'aurai ecrit une barre chiffree. **Elle n'existe pas ; je ne la cree pas en cours de sprint** |

---

## 10. Ce que je route

| # | Suite | Destinataire | Ce que j'attends |
|---|---|---|---|
| 1 | **`EMC-CP-01` -> `couvert-valide`**, sans mention | `camille-po` | Le compte de la matrice lui appartient |
| 2 | **`EMC-CP-05` -> `couvert-valide`, avec la mention citee mot pour mot** en section 7 | `camille-po` | **La mention est opposable** ; la ligne sans elle n'est pas couverte par mon Done |
| 3 | **`EMC-CP-03` et `EMC-CP-07` restent `non couvert`** ; les motifs des deux refus deviennent du backlog | `camille-po`, via `sofia-sm` | **La tache A de `regle-souhait-loi` est a conserver telle quelle** : la story ne reecrit que sa tache B et son item 11 |
| 4 | **La lecture de `EMC-3` du 2026-09-07** - interdiction sur les propositions, non prescription sur la bonne reponse | `camille-po` | A reprendre dans le critere de la story qui reecrit `exclusion`, sans quoi le meme mur se represente |
| 5 | **`EMO-R1`, `EMO-R2`, `POL-R1`, `POL-R2`, `MEL-R4`** | `camille-po`, via `sofia-sm` | En faire des stories. Regimes en section 8 |
| 6 | **Le critere de `S31-6` dit *"aucun distracteur nomme"* la ou `OBT-R2` dit *"au plus un"*** | `camille-po`, via `sofia-sm` | Corriger le critere de story. **Rien a refaire dans le contenu** |
| 7 | **`FAB-1a` et `FAB-1c` mordables** | `theo-archi` | Deja routees le 2026-09-07. **Les deux refus de ce sprint en sont la demonstration** ; je dis la propriete, jamais la forme du test |
| 8 | **Le registre `PAR-5` ne voit pas une allocation faite dans une branche soeur non fusionnee** | `theo-archi` | Cause reelle de l'echec de ma condition de couture. **Tant qu'elle existe, la forme (c) se reproduit mecaniquement.** Je dis le symptome, la cause technique lui appartient |
| 9 | **Un plafond chiffre de longueur d'enonce au CP** | moi-meme, arbitrage prealable de s32 | Trois paquets d'EMC CP livres, aucune barre ecrite : c'est a moi de la poser **avant** un sprint |
| 10 | **Faut-il exiger la source d'un fait de droit dans le commentaire d'un paquet**, comme `HIS-3` en histoire et `EMC-7` pour les numeros d'urgence ? | moi-meme, arbitrage prealable de s32 | **Je ne l'etends pas retroactivement** ; mais un fait de droit faux vient de passer toutes les portes sauf la mienne |
| 11 | **Cinq identifiants de reserve alloues par moi dans `squad/decisions/REGISTRE-identifiants.md`** (`EMO-R1`, `EMO-R2`, `POL-R1`, `POL-R2`, `MEL-R4`), avec le recompte a 532 / 89 | `theo-archi` | **Allocation mecanique deja faite** ; je la signale pour qu'elle soit relue, pas pour qu'elle soit refaite. **La ligne de mesure de la section 1 du registre, qui dit encore *83 prefixes*, est la sienne et je ne l'ai pas reecrite** |
| 12 | **La conclusion de ce document dans `review.md`** | `sofia-sm` | Le fichier n'existe pas encore ; la reprise est due a la cloture, et elle m'appartient |

---

## 11. Ce que ce document ne fait pas

- **Il ne touche pas a `squad/programme/MATRICE-couverture.md`.** Son compte appartient a
  `camille-po`.
- **Il ne corrige aucun contenu a la place d'un developpeur** : chaque motif de refus et chaque
  reserve dit une **propriete a tenir**, jamais un texte a ecrire.
- **Il ne modifie aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/`.** J'ai
  lu six paquets YAML et joue neuf parties ; je n'y ai rien ecrit.
- **Il ne juge aucun paquet du perimetre de l'instance B**, ni le paquet d'etats de la matiere, ni
  aucune des quatre lignes de sciences neuves.
- **Il ne priorise pas et n'ouvre aucun perimetre** (`camille-po`), **ne gele rien et n'affecte
  personne** (`sofia-sm`).
- **Il n'efface aucun verdict** : les Done du 2026-09-06 sur `melanges` et `objet-technique`, y
  compris le REFUSE de `objet-technique`, restent lisibles dans leurs fiches, avec la date de levee
  de leurs reserves ajoutee en dessous.
- **Il ne durcit aucune clause anterieure.** `EMC-3` est **elargie**, ce qui va contre moi ;
  `MEL-R2` et `OBT-R1` sont levees sur les proprietes que j'avais ecrites, pas sur celles que je
  vois aujourd'hui ; `MEL-R4` est neuve et datee ; et je ne cree aucune barre chiffree de lisibilite
  en cours de sprint.
