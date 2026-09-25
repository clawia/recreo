# DEC-0099 - Proposition de perimetre pour s43 et s44

**Statut : Acceptee (2026-09-25, validation de l'utilisatrice sur le perimetre rechiffre de la
section 9). Les sections 1 a 8 restent la trace de l'etat propose avant amendement.**
**Amendee le 2026-09-25 : voir section 9 - correction de fait sur le palier 2 (section 4 bis) et
perimetre rechiffre pour s43 et s44 avec les lignes de mathematiques CE1 desormais arbitrees.**
**Amendee une seconde fois le 2026-09-25 : voir section 10 - le perimetre de s44 (section 9.6),
propose et non engage par s43, passe a Accepte, engage.**

- **Date** : 2026-09-25
- **Auteur** : `camille-po`
- **Sprint** : s43 et s44 (deux sprints distincts, non fusionnes - instance unique chacun, aucune
  demande de fusion recue cette fois)
- **Prealable** : `DEC-0097` (methode de chiffrage et d'exclusion, reprise ici sur le meme
  protocole) ; `DEC-0098` (fusion de s42, cloturee - ce document ne la rouvre pas, il propose le
  perimetre des deux sprints suivants) ; `squad/programme/MATRICE-couverture.md`, notes du
  2026-09-24 (etat de sortie de s42, 98/156)
- **Competence programme visee** : transverse - ce document porte sur l'objectif, le perimetre et
  la borne de deux sprints, pas sur le contenu d'une ligne

---

## 0. Commande et methode

Demande de l'utilisatrice a la cloture de s42 (98/156 = 62,8 %) : proposer un perimetre pour les
deux prochains sprints, **s43 et s44, deux sprints distincts** cette fois - pas de fusion, borne
standard a instance unique pour chacun.

Methode suivie, identique a celle de `DEC-0097` :

1. Lecture integrale de `squad/programme/MATRICE-couverture.md` (5 821 lignes) et recompte
   independant des statuts `non couvert` et `en cours` restants, verifie contre le recompte deja
   ecrit dans le fichier (section "Synthese apres les verdicts du sprint 41", complete par la note
   de cloture de s42 en tete de fichier) : **55 lignes `non couvert`, 3 lignes `en cours`, total 58,
   contre un denominateur atteignable de 156** (98 + 3 + 55 = 156, 98 / 156 = 62,8 %, chiffre
   confirme).
2. Verification que rien n'a change depuis `DEC-0097`/`DEC-0098` : `DEC-0098` est la derniere
   reference de `DEC-index.md`, aucune `DEC-0099` n'existait avant ce document. Les cinq commits
   de s42 posterieurs a `DEC-0098` (`6aa0ddd6`, `7004286e`, `e0e1c61f`, `7334dddb`, plus le
   deplacement des identifiants de reserve qu'ils portent) sont tous du renommage de jetons de
   reserve pedagogique (`ADR-0100`) et un garde-fou technique associe : **aucun n'ouvre, ne
   ferme, ni ne modifie l'arbitrage d'une ligne candidate de ce document**.
3. Reprise a l'identique des exclusions de `DEC-0097` (`DEC-0075`, `DEC-0093` section 6, le refus
   francais CP non leve, la caution `SCI-CM1-15`), verifiees non levees (section 3).
4. Reprise des candidates de second rang de `DEC-0097` (treize lignes CE1, `LVE-CM1-05`
   conditionnelle), plus `MAT-CE1-18` qui rejoint la meme famille (section 4).
5. Traitement specifique de `MAT-CP-10`, dont la moitie textuelle a ferme a s42 : verification de
   ce que sa moitie restante (designation sur quadrillage, palier 2) requiert reellement avant de
   la considerer comme candidate (section 4 bis).
6. Verification ligne par ligne de l'arbitrage pedagogique prealable pour chacune des vingt-six
   lignes du vivier de `DEC-0097`, section 7 - **pas une reprise de confiance de ses notes** :
   quatre lignes en sortent (illustration neuve requise, avis de faisabilite de `theo-archi` a
   obtenir avant tout chiffrage), une cinquieme (`QLM-CP-10`) porte un cout non nul que la note de
   `DEC-0097` ne portait pas (section 4 ter).
7. Verification de la borne d'audit courante avant de rien chiffrer (section 1).
8. Chiffrage de deux perimetres distincts, chacun avec une marge de rejeu dimensionnee sur les
   deux evenements deja mesures dans ce depot, pas seulement sur un refus pedagogique (section 5
   et 6).

## 1. Borne d'audit verifiee

**Instance unique, borne 5,0 par sprint, inchangee.** Verification independante, pas une reprise
de `DEC-0097` :

- `ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md`, section 4.1,
  pose la borne a 5,0.
- `git log` sur ce fichier ne porte que deux commits, tous deux de son ecriture initiale a s28
  (`1cf80f47`, `537a0e14`) : **aucune revision depuis**.
- Aucune `DEC` entre `DEC-0095` (qui n'arbitrait deux instances que nommement pour s41) et ce
  document ne touche a la borne d'instance unique. `DEC-0098` a verifie et confirme la borne a
  **deux** instances (8,0, pour s41 et s42 seuls) sans jamais toucher a la borne a instance
  unique.
- Aucune demande de fusion ni d'instances paralleles n'a ete recue pour s43 ou s44 : la regle par
  defaut s'applique, **5,0 par sprint, une instance**.

## 2. Methode de chiffrage - tarif estime, pas publie

Identique a `DEC-0097`, section 2 : aucun tarif ligne a ligne publie par `nadia-prof` n'existe pour
les lignes candidates de s43/s44. Estimation de `camille-po`, regle generale posee par `DEC-0074`
et confirmee par `DEC-0077` section 8.2 : **1,0 pour une matiere dont `nadia-prof` est la source
factuelle** (mathematiques, francais) ; **1,5 pour les autres matieres**. Les quatre lignes
retenues ci-dessous (questionner le monde, education physique et sportive, education musicale,
sciences et technologie) sont toutes hors mathematiques et francais : **tarif 1,5 chacune, sans
exception.**

## 3. Lignes exclues - reprises de `DEC-0097`, verifiees non levees

### 3.1 Bloquees par `DEC-0075` (droits d'usage de reproductions d'oeuvres, neuf lignes)

Inchangees : `ART-CP-09`, `ART-CP-10`, `ART-CM1-07`, `ART-CM1-08`, `HDA-CM1-01`, `HDA-CM1-02`,
`HDA-CM1-03`, `HDA-CM1-04`, `HDA-CM1-06`. Aucune `DEC` posterieure a `DEC-0075` ne les leve.

### 3.2 Ecartees par `DEC-0093` section 6

Inchangees : `EMC-CM1-06` (principes de la Republique), `SCI-CM1-18` (impact humain sur
l'environnement). Dernier commit touchant `DEC-0093` : `7c10add1`, s40 - anterieur a `DEC-0097`.

### 3.3 Refus pedagogique explicite et non leve

`francais CP` - decoder un mot regulier inconnu par assemblage syllabique - reste refusee
(`DEC-0068`). Non candidate.

### 3.4 Caution explicite de `nadia-prof`, hors toute `DEC`

`SCI-CM1-15` (Terre, systeme solaire, saisons) reste ecartee par prudence, nommee par
`nadia-prof` elle-meme comme *"la plus risquee des candidates de sciences"*
(`ARBITRAGE-PREALABLE-s31`, section 4.3). Elle reste dans le vivier, pas dans le perimetre
chiffre.

### 3.5 Retiree apres verification concrete, hors vivier tant que le pli n'est pas revu

**`francais CM1` - Lire silencieusement un texte de longueur croissante et en restituer
l'essentiel** (`MATRICE-couverture.md`, ligne 1073, toujours `non couvert`, verifie). Choisie par
`DEC-0097` pour s42, **retiree en cours de sprint** (`DEC-0098`, amendement du 2026-09-24) apres
un verdict negatif de mesure de pli par `ines-design` : au plafond haut de `LIS-2`/`LIS-3`, le
bloc des trois propositions deborde de 357 px. **Cette ligne n'est pas reintroduite dans le
perimetre chiffre ci-dessous.** Elle ne rejoint pas non plus le vivier tel quel : la reserve
`LIS-1` a `LIS-10` (`ARBITRAGE-PREALABLE-s25`, section 9.1) reste opposable, mais toute
reproposition suppose un texte plus court, une autre forme de proposition ou un gabarit
different, **et donc une nouvelle mesure de pli avant toute chiffrage** - pas une simple reprise
du paquet refuse. Signale ici explicitement, comme demande.

## 4. Candidates de second rang - sans arbitrage pedagogique prealable ecrit

**Quatorze lignes de mathematiques CE1**, et non treize : `MAT-CE1-01`, `-02`, `-04`, `-05`,
`-06`, `-07`, `-08`, `-10`, `-11`, `-12`, `-14`, `-15`, `-17` (deja sans arbitrage a `DEC-0097`),
**plus `MAT-CE1-18`** (sens de la division), ajoutee au referentiel le 2026-09-21 et a la matrice
le 2026-09-24 (correction de tenue, hors sprint). Verification faite : `MAT-CE1-18` est citee dans
cinq `DEC` (`DEC-0089`, `DEC-0091`, `DEC-0092`, `DEC-0093`, `DEC-0097`) **toujours comme un ecart
de tenue ou une exclusion explicite**, jamais comme une ligne arbitree pour un contenu. Le
referentiel CE1 ne porte toujours aucun tableau "Jugement de faisabilite" ligne a ligne. Engager
l'une de ces quatorze lignes suppose, comme a chaque sprint CE1 precedent (s37 a s39), un
arbitrage neuf demande a `nadia-prof` avant tout avis de faisabilite de `theo-archi` - un delai
que le perimetre chiffre de s43/s44 ne prend pas.

**Amendement du 2026-09-25 (section 9.2).** Ce delai est paye : les quatorze lignes ont desormais
chacune un arbitrage pedagogique prealable ecrit, rendu le 2026-09-25 en trois lots
(`ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`, `-lot-2.md`, `-lot-3.md`). Le perimetre
rechiffre qui en tient compte est en section 9 ; cette section 4 reste inchangee comme trace de
l'etat qui a motive la demande d'arbitrage.

**Une ligne conditionnelle inchangee** : `LVE-CM1-05` (lire et comprendre un texte court et
simple, anglais CM1) reste jouable au choix et arbitree (`ARBITRAGE-PREALABLE-s27`, section 7.3),
mais la condition posee le 2026-09-04 par `nadia-prof` - un second paquet lexical au-dela de celui
deja `couvert-valide` de `LVE-CM1-04` - reste non remplie. Verifie contre la derniere fiche
touchant l'anglais CM1 (`FICHE-bandit-manchot-anglais-cm1.md`, ligne 655) : *"`LVE-CM1-05`,
`LVE-CM1-06` : `non couvert`, inchangees, non touchees par ce lot"*. Hors du perimetre chiffre.

## 4 bis. `MAT-CP-10`, moitie visuelle - hors de portee technique, pas seulement pedagogique

La moitie textuelle de `MAT-CP-10` (*decrire un deplacement*) a ferme a s42 (`en cours`, VALIDE
SOUS RESERVE au second passage). Reste **`se reperer et se deplacer sur un quadrillage`**, qui
exige une **designation sur image** - ce que le depot appelle le **palier 2**.

**Ce n'est pas une question d'arbitrage pedagogique manquant : `nadia-prof` l'a deja tranchee.**
Dans le meme sprint qui a ferme la moitie textuelle, elle a elle-meme verifie qu'**aucun** item du
paquet servi ne demandait une designation sur image, confirmant *"palier 2 hors de portee,
respecte sans ambiguite"* (`squad/conformite/FICHE-bandit-manchot-deplacements-quadrillage-cp.md`,
ligne 113). Et son arbitrage anterieur (`ARBITRAGE-PREALABLE-s32`, section 17.7) est explicite :
*"Oui, `MAT-CP-10` demande le palier 2, et pour une moitie seulement."*

**Ce que "palier 2" designe reellement** : une mecanique de `designation` entierement absente du
produit a ce jour - *"le palier 2 n'est jouable par personne"* (`ARBITRAGE-PREALABLE-s32`,
section 9, ligne 671), qui bloquerait dix a onze lignes de la matrice si elle etait construite.
**`nadia-prof` elle-meme recommande explicitement de ne pas la financer** dans ce meme document
(section "je ne recommande pas de financer le palier 2"), sur un calcul de rendement qu'elle y
detaille : le palier 2 coute environ quatre fois la ligne la moins chere du depot.

**Consequence pour ce document** : la moitie visuelle de `MAT-CP-10` n'est **pas un candidat
serieux** pour s43 ni pour s44. Ce n'est pas une ligne qui manque d'arbitrage - c'est une ligne
dont l'arbitrage dit deja non, pour un motif d'architecture (mecanique absente) que je n'ai pas
autorite a trancher. Si ce choix devait etre rouvert, ce serait une decision d'investissement sur
le palier 2 lui-meme, routee a `theo-archi` via `sofia-sm`, et couvrant bien plus qu'une seule
ligne - pas un geste de ce document.

**Correction de fait, amendement du 2026-09-25 (section 9.1).** Le paragraphe ci-dessus est faux
sur un point precis et il ne l'etait pas au moment ou je l'ai ecrit. La mecanique de `designation`
n'est plus *"entierement absente du produit"* : elle est servie par deux entrees de catalogue, et
`nadia-prof` a prononce le Done pedagogique sur l'une d'elles le 2026-09-21. Le fait est signale
independamment par les trois lots d'arbitrage prealable de mathematiques CE1 de s43
(`ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`, section 0.2, et `-lot-3.md`, section 0.1).
**Ce que cela ne change pas** - et c'est `nadia-prof` elle-meme qui le dit dans les deux lots : la
conclusion de cette section sur `MAT-CP-10` tient, la moitie visuelle de cette ligne reste hors
perimetre de s43/s44, et une decision d'investissement sur le palier 2 comme tel reste a router a
`theo-archi`. **Ce que cela change** : la mecanique existe deja pour toute ligne qui se contente de
montrer une image et de faire designer une zone dessus, sans franchir le palier 2 comme
investissement a part. Plusieurs candidates de mathematiques CE1 en dependent - detail en
section 9.

## 4 ter. Quatre lignes du vivier sorties : illustration neuve requise, avis de faisabilite du a `theo-archi`

Verification ligne par ligne du vivier de `DEC-0097` (vingt-six lignes) : quatre portent un motif
d'illustration neuve non instruite, distinct d'un simple manque d'arbitrage pedagogique -
**`nadia-prof` les a deja jugees jouables sur le fond**, mais aucune n'est chiffrable sans un avis
technique prealable :

| Ligne | Motif |
|---|---|
| `QLM-CP-12` Situer la France et quelques espaces sur une carte ou un globe | arbitree jouable, mais requiert le palier 2 **et** une illustration neuve (carte, globe) jamais dessinee dans ce depot |
| `HG-CM1-09` Se reperer sur une carte de France et un planisphere | meme motif que `QLM-CP-12` |
| `HG-CM1-10` Situer son lieu de vie a differentes echelles | meme motif, **plus** le lieu de vie de l'enfant, non connu du produit (`DEC-0028`) - un second obstacle, distinct du premier |
| `HDA-CM1-09` Se reperer dans un musee, un lieu d'art ou un site patrimonial | arbitree jouable, **pas** bloquee par `DEC-0075` (aucune reproduction d'oeuvre requise), mais illustration neuve (plan de musee) a instruire |

Ces quatre lignes restent au vivier (section 7), disponibles des qu'un avis de faisabilite de
`theo-archi` existe - ce n'est pas le geste de ce document.

**Une cinquieme ligne, `QLM-CP-10` (vocabulaire de position), reste dans le vivier mais porte un
cout different de celui note par `DEC-0097`.** Sa note d'origine (*"jouable, `CON-2`"*) ne
mentionne pas que `nadia-prof` exige explicitement le **palier 1 illustre** pour cette ligne
(*"gauche/droite sans image est la difficulte classique du CP"*) avec un cout additionnel qu'elle
chiffre elle-meme a **+0,5 sur le lot** (`ARBITRAGE-PREALABLE-s29`, section 4.1, ligne
`QLM-CP-10`). Le palier 1 illustre existe deja dans le produit (`ADR-0073`, deja utilise par
plusieurs jeux dont `bandit-manchot-solides-ce1`) - ce n'est pas le palier 2 manquant - mais son
cout reel est **2,0, pas 1,5**. Non retenue ce tour-ci faute d'un motif de priorite face aux
quatre lignes choisies ci-dessous ; reste au vivier avec son cout corrige.

## 5. Perimetre propose pour s43

**Cible : 3,0/5,0, marge 2,0.**

| Ligne | Matiere / niveau | Tarif (estimation) | Source de l'arbitrage prealable |
|---|---|---|---|
| `QLM-CP-08` Reconnaitre les objets numeriques du quotidien et quelques regles simples de leur usage | questionner le monde CP | 1,5 | `ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`, section 4.1, ligne `QLM-CP-08` : rentabilite **moyenne**. **Vigilance explicite de `nadia-prof`, reprise ici et non passee sous silence** : *"les regles d'usage au CP sont peu nombreuses ; risque de moraliser plutot que d'enseigner"* - a l'auteur du paquet de rester sur des faits verifiables (quel objet est numerique, quelle regle simple s'applique), jamais sur une injonction |
| `EPS-CM1-10` Connaitre les effets de l'activite physique sur le corps et les regles de securite qui l'entourent | education physique et sportive CM1 | 1,5 | `squad/programme/education-physique-et-sportive/competences-cm1.md`, Jugement de faisabilite (`nadia-prof`, 2026-09-03), ligne 10 : **jouable au choix textuel, sous `EPS-2` et `BIA-4`**. **Frontiere posee d'avance par `nadia-prof` elle-meme** (meme fichier, section "Recouvrement a ne pas double-compter") : les items portent sur les effets de l'effort et son encadrement (echauffement, hydratation, recuperation, securite) et ne doivent **pas** deriver vers le sommeil, les repas ou l'hygiene du corps - matiere deja couverte par `SCI-CM1-10` (non retenue dans ce perimetre, distincte de la ligne ici choisie) |

**Total estime : 3,0/5,0.** Deux matieres (questionner le monde, education physique et sportive),
deux niveaux (CP, CM1). Si les deux lignes ferment integralement, la matrice passe de 98/156
(62,8 %) a 100/156 (64,1 %).

## 6. Perimetre propose pour s44

**Cible : 3,0/5,0, marge 2,0.**

| Ligne | Matiere / niveau | Tarif (estimation) | Source de l'arbitrage prealable |
|---|---|---|---|
| `MUS-CP-11` Nommer quelques reperes du vocabulaire musical rencontre (refrain, couplet, pulsation, silence) | education musicale CP | 1,5 | `squad/programme/education-musicale/competences-cp.md`, Jugement de faisabilite (`nadia-prof`, 2026-09-03), ligne 11 : **jouable au choix textuel, sous `MUS-1`**. Mention de couverture partielle exigee d'avance si la ligne bouge : *"vocabulaire musical et familles d'instruments seuls, sur le nom ecrit ; aucune competence auditive ni vocale n'est servie"* |
| `SCI-CM1-13` Reperer l'evolution d'un objet technique au cours du temps et ce qui a change dans sa fabrication ou dans son usage | sciences et technologie CM1 | 1,5 | `squad/programme/sciences-et-technologie/competences-cm1.md`, Jugement de faisabilite (`nadia-prof`, 2026-09-03), ligne 13 : **jouable au choix textuel, sous `BIA-3`**. *"On decrit un changement de materiau, de fonction ou d'usage. Aucun item ne dit que l'objet ancien etait moins bien"* |

**Total estime : 3,0/5,0.** Deux matieres (education musicale, sciences et technologie), deux
niveaux (CP, CM1). Si les deux lignes ferment integralement et que s43 a deja ferme, la matrice
passe de 100/156 (64,1 %, projection de s43) a 102/156 (65,4 %).

## 6 bis. Pourquoi 3,0/5,0 et non 4,0/5,0 ou 4,5/5,0 - la marge dimensionnee sur deux evenements, pas un seul

**Toutes les lignes disponibles sans delai d'arbitrage sont tarifees 1,5** (section 2) : aucune
ligne a 1,0 n'est disponible dans le vivier sans arbitrage neuf (mathematiques CE1) ou sans
mesure prealable a reprendre (francais CM1, section 3.5). Avec un tarif uniforme a 1,5, les seules
compositions possibles d'un sprint a instance unique sont : deux lignes (3,0/5,0, marge 2,0) ou
trois lignes (4,5/5,0, marge 0,5).

**La composition a trois lignes est ecartee sur un precedent deja ecrit dans ce depot, pas sur une
prudence nouvelle.** `DEC-0098`, amendement du 2026-09-24 (section 9), a affronte exactement ce
choix - completer un terrain a 3,0 avec une troisieme ligne a 1,5 disponible dans le meme vivier -
et l'a refuse dans les termes suivants : porter le terrain a *"4,5/5,0, soit une marge
individuelle de 0,5 - exactement le facteur que la section 6 [de `DEC-0098`] identifie comme la
cause directe du risque materialise a s41"* (terrain EMC a 4,5/5,0, deux refus sur le meme lot le
meme jour). Ce document applique le meme raisonnement, sur le meme tarif, au meme vivier plus
recent.

**La marge de 2,0 n'est pas seulement dimensionnee sur un refus pedagogique** (lecon de s41 :
zero marge a coute trois refus dans le sprint fusionne) **mais sur le retrait complet d'une ligne
apres verification concrete** (lecon de s42 : `S42-FRA-LECTURE`, retiree sans remplacement faute
d'une ligne a 1,0 disponible dans le vivier au moment du retrait). Une marge de 2,0 sur un sprint
a deux lignes de 1,5 absorbe integralement le retrait d'une des deux lignes sans faire tomber le
sprint a zero contenu ferme, ce qu'une marge de 0,5 ou meme de 1,0 ne permettrait pas sur ce
tarif.

## 7. Vivier - candidates pretes, non retenues dans le perimetre chiffre

Vingt-deux lignes restent disponibles sans delai d'arbitrage neuf (les vingt-six de `DEC-0097`,
moins les quatre choisies ci-dessus), plus les cinq signalees en section 4 ter (quatre en attente
d'un avis de faisabilite de `theo-archi`, une - `QLM-CP-10` - avec un cout corrige a 2,0) :

| Ligne | Matiere / niveau | Note |
|---|---|---|
| `QLM-CP-15` Comparer des modes de vie d'autrefois et d'aujourd'hui | questionner le monde CP | jouable, `BIA-3` |
| `QLM-CP-16` Comparer des modes de vie ici et ailleurs | questionner le monde CP | jouable mais **"la ligne la plus a risque de biais de tout le referentiel"** (`BIA-1`, `BIA-2`) - a instruire avec un soin superieur au reste du vivier |
| `QLM-CP-10` Utiliser le vocabulaire de position | questionner le monde CP | jouable, `CON-2`, **mais cout corrige a 2,0** (palier 1 illustre exige, `ARBITRAGE-PREALABLE-s29` section 4.1) - voir section 4 ter |
| `MUS-CP-05` Identifier des instruments et leurs familles (connaissance seule) | education musicale CP | jouable, mention de couverture partielle exigee d'avance |
| `EPS-CM1-09` Tenir les roles d'arbitre, d'observateur, de marqueur (connaissance seule) | EPS CM1 | jouable, `EPS-1`, mention de couverture partielle exigee d'avance |
| `EMC-CP-04` Reconnaitre que les personnes sont differentes | EMC CP | jouable sous `EMC-4` **mais non retenue a s31** (`DEC-0081`) : `EMC-4` n'autorise qu'une seule forme de tache, deja epuisee par `FER-3`. A traiter avec prudence si reprise |
| `EMC-CP-14` Connaitre les gestes d'alerte | EMC CP | jouable, `EMC-7`, piege d'exactitude connu (18 vs 112) deja documente |
| `EMC-CM1-01` Identifier une emotion, distinguer ressentir et agir | EMC CM1 | jouable, risque de reponses doublement defendables |
| `EMC-CM1-02` Reconnaitre une atteinte a autrui | EMC CM1 | jouable mais **"la plus delicate du lot"** avec `EMC-CM1-06` (exclue, section 3.2) |
| `EMC-CM1-03` Ce qu'interdit le respect des differences | EMC CM1 | meme risque `EMC-4` que `EMC-CP-04` ci-dessus |
| `EMC-CM1-09` Role des institutions proches | EMC CM1 | jouable, `EMC-6` |
| `EMC-CM1-10` Sens du vote et de la representation | EMC CM1 | jouable, connaissance seule |
| `LVE-CP-06` Decouvrir quelques aspects culturels des pays de la langue etudiee | langues vivantes CP | jouable en francais seul, mention exigee d'avance |
| `LVE-CM1-06` Ecrire des mots et expressions memorises | langues vivantes CM1 | jouable par saisie, `ANG-S1`/`ANG-S2`, s'appuie sur le lexique de `LVE-CM1-04` deja `couvert-valide` |
| `SCI-CM1-03` Decrire le mouvement d'un objet par sa trajectoire et sa vitesse | sciences CM1 | jouable, description seule |
| `SCI-CM1-10` Relier alimentation, activite physique, sommeil et hygiene au corps | sciences CM1 | jouable, `BIA-4`. **Frontiere avec `EPS-CM1-10`** (retenue en s43, section 5) a respecter si les deux sont ecrites un jour : effets de l'effort a l'EPS, alimentation/sommeil/hygiene aux sciences |
| `SCI-CM1-14` Regles d'usage d'un environnement numerique | sciences CM1 | jouable, connaissances seules |
| `SCI-CM1-16` Phenomenes geologiques et meteorologiques, risques | sciences CM1 | jouable, `BIA-2` |
| `QLM-CP-12` Situer la France et quelques espaces sur une carte ou un globe | questionner le monde CP | avis de faisabilite `theo-archi` du - voir section 4 ter |
| `HG-CM1-09` Se reperer sur une carte de France et un planisphere | histoire-geographie CM1 | avis de faisabilite `theo-archi` du - voir section 4 ter |
| `HG-CM1-10` Situer son lieu de vie a differentes echelles | histoire-geographie CM1 | avis de faisabilite `theo-archi` du, plus obstacle `DEC-0028` - voir section 4 ter |
| `HDA-CM1-09` Se reperer dans un musee, un lieu d'art ou un site patrimonial | histoire des arts CM1 | avis de faisabilite `theo-archi` du - voir section 4 ter |

## 8. Ce que ce document ne fait pas

Ni `docs/backlog/backlog.md`, ni `squad/sprints/`, ni `squad/programme/MATRICE-couverture.md` ne
sont modifies par ce document. Aucun perimetre n'est engage. Il ne tranche aucune question
technique (le palier 2, le cout du palier 1 illustre sur `QLM-CP-10`, l'illustration neuve des
quatre lignes de la section 4 ter restent a `theo-archi`) et ne juge la qualite pedagogique
d'aucun contenu - les quatre lignes retenues restent `non couvert` jusqu'au Done pedagogique de
`nadia-prof`. Si l'utilisatrice valide tout ou partie de ce qui precede, l'ecriture des stories et
l'ouverture des dossiers de sprint reviennent a `sofia-sm`.

---

## 9. Amendement du 2026-09-25 - correction de fait et perimetre rechiffre

**Auteur** : `camille-po`. **Motif** : les quatorze lignes de mathematiques CE1 que la section 4
rangeait en candidates de second rang, faute d'arbitrage prealable, ont recu cet arbitrage le
2026-09-25, en trois lots ecrits par `nadia-prof` :
`squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` (`MAT-CE1-01`, `-02`, `-04`,
`-05`, `-06`), `-lot-2.md` (`MAT-CE1-07`, `-08`, `-10`, `-11`, `-12`), `-lot-3.md` (`MAT-CE1-14`,
`-15`, `-17`, `-18`). Cette section rechiffre le perimetre de s43 et de s44 en integrant ce qui en
resulte. **Elle ne reecrit aucune des sections precedentes** : les sections 1 a 8 restent la trace
de l'etat au moment ou ce document a ete propose ; cette section les complete et, la ou elle les
contredit, le dit explicitement plutot que de corriger en place.

### 9.1 Correction de fait deja notee en section 4 bis

Deplacee ici pour memoire : la section 4 bis decrivait le palier 2 (mecanique de `designation`)
comme *"entierement absente du produit a ce jour"*. C'est faux au 2026-09-25 - deux entrees de
catalogue la servent et `nadia-prof` a prononce un Done pedagogique sur l'une d'elles le
2026-09-21. La conclusion de la section 4 bis sur `MAT-CP-10` ne bouge pas ; ce qui bouge est la
nature du manque pour d'autres lignes : plusieurs verbes de mathematiques CE1 qui semblaient
bloques par une **mecanique absente** ne le sont en realite que par un **materiau absent**
(un dessin qui n'existe pas encore), ce qui n'est pas la meme nature de manque pour un avis de
faisabilite a venir. Detail complet en tete de section 4 bis.

### 9.2 Les quatorze lignes, reclassees par ce que l'arbitrage permet d'atteindre

| Groupe | Lignes | Ce que l'arbitrage permet |
|---|---|---|
| **A - couvert-valide atteignable, aucune image requise** | `MAT-CE1-04`, `-05`, `-07`, `-08`, `-12`, `-18` | ACCEPTE SOUS CONDITIONS ; un paquet entierement textuel suffit a fermer la ligne |
| **B - couvert-valide atteignable, image requise (mecanique existante, materiau a produire)** | `MAT-CE1-17` | ACCEPTE SOUS CONDITIONS ; ferme par designation sur un dessin d'axe/de quadrillage, de la meme famille que les dessins de solides deja livres |
| **C - plafonnees `en cours`, couverture partielle** | `MAT-CE1-01`, `-02`, `-06`, `-10`, `-11`, `-14`, `-15` | un verbe au moins reste hors de portee du produit (denombrer, mesurer, lire l'heure, tracer, coder en production, ou une image non encore instruite) ; mention de couverture partielle exigee mot pour mot dans la matrice si la ligne bouge |

Le groupe A est le plus rapide a engager : aucune depense de mecanique ni de materiau visuel
neuf, uniquement du texte sur un patron deja rode sept fois au CE1. Le groupe B ferme lui aussi
`couvert-valide`, au prix d'un materiau (dessins) que je ne chiffre pas - c'est a `theo-archi`.
Le groupe C n'est pas retenu ce tour-ci (section 9.5) : chacune de ses sept lignes plafonne a
`en cours` sans un investissement (rendu de cadran, de balance, de recipient gradue, ou une
interaction de trace qui n'existe dans aucune mecanique du produit a ce jour pour `MAT-CE1-15`),
et je juge que la couverture partielle qu'elles offriraient ne vaut pas le cout ce tour-ci face a
six lignes du groupe A qui atteignent `couvert-valide` pour le meme tarif.

### 9.3 Collisions internes verifiees avant de choisir

Trois collisions sont deja signalees par les lots eux-memes, et elles sont deja **fermees par une
condition ecrite dans l'arbitrage**, pas des blocages :

| Collision | Lignes | Fermee par |
|---|---|---|
| `MAT-CE1-04`/`-05` reprendraient `MAT-CE1-03` (bandit-manchot-additions/soustractions-ce1) si le seuil "avec retenue/emprunt" n'est pas tenu | `MAT-CE1-04`, `-05` vs `MAT-CE1-03` | `ADP-CE1-3`, `SOP-CE1-3` (au moins deux tiers des items a trois chiffres ou plus de 100) |
| La table de 2 de `MAT-CE1-07` est le meme fait numerique que les doubles deja servis | `MAT-CE1-07` vs `MAT-CE1-03` | `TAB-CE1-5` (la formulation par le double est exclue de l'enonce) |
| Les problemes d'achat de `MAT-CE1-08` reprendraient les items de rendu de monnaie deja exiges par `MON-CE1-3` sur `bandit-manchot-monnaie-ce1` | `MAT-CE1-08` vs `MAT-CE1-13` (`MON-CE1-3`/`PRO-CE1-4`) | `PRO-CE1-4` (aucun achat, prix, piece, billet, euro ni centime) |

Ces trois collisions ne m'ont pas ecartee des lignes concernees : elles disent qu'un paquet doit
tenir une condition supplementaire, deja ecrite, deja verifiee satisfaisable par `nadia-prof`. Je
ne retiens pas `MAT-CE1-04`, `-05`, `-07` ni `-08` ce tour-ci (section 9.5), pour une raison de
marge et non de collision : elles restent au premier rang du vivier pour un prochain sprint.

Une quatrieme collision touche une ligne que je retiens (section 9.5) : `MAT-CE1-18` reprendrait
le fait "moitie de n" deja servi par `bandit-manchot-moities-ce1` sous `MAT-CE1-03`. Fermee par
`DIV-CE1-4` (aucun item de division par 2 ne dit *moitie* ni ne se reduit au fait de repertoire
deja servi). Une cinquieme, non fermee mais sans effet sur ce perimetre parce que la ligne en face
n'y est pas engagee : `MAT-CE1-18` et `MAT-CE1-06` (sens de la multiplication, hors de ce
perimetre) se recoupent sur le groupement lu comme une multiplication a trou ; les deux lots le
signalent sans trancher, et je le route a `sofia-sm` (section 9.7) pour que la frontiere soit
posee avant que l'une ou l'autre ligne ne soit ecrite, pas seulement avant que les deux le soient
ensemble.

### 9.4 Marge de rejeu - le calcul, et pourquoi je m'arrete avant le plafond

Tarif des lignes de mathematiques CE1 : **1,0** chacune (section 2, matiere dont `nadia-prof` est
la source factuelle). Les quatre lignes deja proposees restent a 1,5 chacune, inchangees.

Le plafond mecanique serait d'ajouter deux lignes de mathematiques CE1 par sprint (2,0) aux deux
lignes deja proposees (3,0), soit une cible de **5,0/5,0, marge 0,0**. Je n'engage pas ce plafond.
**C'est le meme raisonnement que celui qui a fait ecarter, en section 6 bis, une troisieme ligne a
1,5 portant le sprint a 4,5/5,0 marge 0,5** - `DEC-0098` attribue a une marge de 0,5 les deux refus
essuyes le meme jour a s41. Une marge de 0,0 est strictement pire que la marge de 0,5 deja jugee
insuffisante ; l'appliquer ici contredirait le motif meme que cette section invoque.

**Je retiens donc une seule ligne de mathematiques CE1 par sprint, pas deux.** Cible par sprint :
1,5 + 1,5 + 1,0 = **4,0/5,0, marge 1,0**. Cette marge egale le tarif de la seule ligne neuve
ajoutee : si elle est retiree en cours de sprint (le precedent de `S42-FRA-LECTURE` montre que
cela arrive), le sprint retombe sur les deux lignes deja proposees, jamais a zero contenu ferme -
exactement la garantie que la marge de 2,0 apportait a la version precedente du perimetre, ici
recalculee pour un tarif plus bas. La seconde place que le tarif aurait permise reste au vivier
(section 9.6) : rien n'empeche de la reclamer si `theo-archi` confirme un cout inferieur a
l'estimation, ou si l'utilisatrice prefere assumer la marge nulle en connaissance de cause.

### 9.5 Perimetre rechiffre pour s43

**Cible : 4,0/5,0, marge 1,0.**

| Ligne | Matiere / niveau | Tarif | Motif du choix |
|---|---|---|---|
| `QLM-CP-08` | questionner le monde CP | 1,5 | inchangee (section 5) |
| `EPS-CM1-10` | education physique et sportive CM1 | 1,5 | inchangee (section 5) |
| `MAT-CE1-12` Calculer une duree simple en heures entieres ou en demi-heures | mathematiques CE1 | 1,0 | **ACCEPTE SOUS CONDITIONS** `DUR-CE1-1` a `-6`, `couvert-valide` atteignable sur texte seul, **aucune collision signalee** avec les sept jeux CE1 deja servis (lot 2, section 7) - la plus nette des six lignes du groupe A |

### 9.6 Perimetre rechiffre pour s44

**Cible : 4,0/5,0, marge 1,0.**

| Ligne | Matiere / niveau | Tarif | Motif du choix |
|---|---|---|---|
| `MUS-CP-11` | education musicale CP | 1,5 | inchangee (section 6) |
| `SCI-CM1-13` | sciences et technologie CM1 | 1,5 | inchangee (section 6) |
| `MAT-CE1-18` Comprendre le sens de la division (partage et groupement) | mathematiques CE1 | 1,0 | **ACCEPTE SOUS CONDITIONS** `DIV-CE1-1` a `-5`, `couvert-valide` atteignable sur texte seul, **seule des quatre lignes de son lot a ne demander ni image ni mecanique neuve** ; sa collision avec les moities deja servies est fermee par `DIV-CE1-4` (section 9.3) |

**Si les six lignes ferment integralement sur les deux sprints**, la matrice passe de 98/156
(62,8 %, etat de sortie de s42) a **104/156 (66,7 %)**.

### 9.7 Lignes CE1 ecartees ce tour-ci, et motif - pour qu'elles ne se reperdent pas au vivier

| Ligne | Verdict de l'arbitrage | Pourquoi elle n'est pas dans le perimetre chiffre |
|---|---|---|
| `MAT-CE1-04` Poser et effectuer une addition avec retenue | ACCEPTE SOUS CONDITIONS, `couvert-valide` atteignable | groupe A, ecartee par la marge (section 9.4) seulement ; premiere candidate du prochain sprint |
| `MAT-CE1-05` Poser et effectuer une soustraction | ACCEPTE SOUS CONDITIONS, `couvert-valide` atteignable | idem |
| `MAT-CE1-07` Tables de 2, 5, 10, puis 3 et 4 | ACCEPTE SOUS CONDITIONS, `couvert-valide` atteignable | idem |
| `MAT-CE1-08` Problemes a une ou deux etapes | ACCEPTE SOUS CONDITIONS, `couvert-valide` atteignable | idem, la plus riche en conditions (sept) des six du groupe A |
| `MAT-CE1-17` Axe de symetrie et completement par symetrie sur quadrillage | ACCEPTE SOUS CONDITIONS, `couvert-valide` atteignable | groupe B : demande un materiau visuel neuf (dessins d'axes et de quadrillages) que je ne chiffre pas ; a proposer avec l'avis de faisabilite de `theo-archi` plutot que dans ce chiffrage textuel |
| `MAT-CE1-01`, `-02`, `-06`, `-10`, `-11`, `-14`, `-15` | COUVERTURE PARTIELLE ou plafond `en cours` (groupe C) | non retenues : chacune plafonne `en cours` sans un investissement (rendu de cadran, de balance, de recipient gradue, de trace) que je ne juge pas prioritaire face a six lignes qui atteignent `couvert-valide` au meme tarif. Restent au vivier avec leur arbitrage, disponibles si l'utilisatrice preferait la couverture partielle a l'attente |

### 9.8 Ce que cette section route, et a qui

| Fait | A qui | Pourquoi ce n'est pas a moi |
|---|---|---|
| La frontiere entre `MAT-CE1-18` (groupement) et `MAT-CE1-06` (sens de la multiplication), signalee par les deux lots sans etre tranchee, doit etre posee avant que l'une des deux lignes ne soit ecrite | `sofia-sm`, qui route a `theo-archi` et a l'instance qui ecrira la story | Je choisis le perimetre, je ne tranche pas ou passe la frontiere entre deux competences voisines |
| Le materiau visuel de `MAT-CE1-17` (dessins d'axe et de quadrillage) et son cout | `theo-archi` | Decision d'architecture et de charge, hors de ma main |
| L'ecriture des stories et l'ouverture des dossiers de sprint pour les six lignes retenues (9.5, 9.6), si l'utilisatrice valide | `sofia-sm` | Section 8, inchangee |

### 9.9 Ce que cet amendement ne fait pas

Il ne modifie ni `squad/sprints/`, ni `squad/programme/MATRICE-couverture.md`. **Amendement du
2026-09-25, second passage** : le statut de ce document passe d'*"en attente de validation"* a
**Acceptee**, l'utilisatrice ayant valide le perimetre rechiffre de la section 9 (trois lignes pour
s43 : `QLM-CP-08`, `EPS-CM1-10`, `MAT-CE1-12`). L'ecriture des stories correspondantes dans
`docs/backlog/backlog.md` est faite par `camille-po` dans le meme mouvement que cette validation,
conformement a la section 9.8. Il ne rouvre `MAT-CP-10` ni aucune des quatre lignes de la
section 4 ter. Il ne rend aucun avis de faisabilite technique et ne prononce aucun Done
pedagogique : les six lignes du perimetre rechiffre restent `non couvert` jusqu'au Done que
`nadia-prof` prononcera apres avoir joue les paquets livres.

---

## 10. Amendement du 2026-09-25, troisieme passage - engagement du perimetre de s44

**Auteur** : `camille-po`. **Motif** : l'utilisatrice valide l'engagement du perimetre de s44 tel
que chiffre en section 9.6 (`MUS-CP-11`, `SCI-CM1-13`, `MAT-CE1-18`, cible 4,0/5,0, marge 1,0),
sans le rouvrir. La section 9.9 n'enregistrait la validation de l'utilisatrice que sur le
perimetre de s43 ; celui de s44 restait explicitement **propose, non engage**, ce que confirme
`squad/sprints/s43-trois-lignes-qlm-eps-et-duree-ce1/RAPPORT.md`, tableau des decisions du sprint,
qui le nomme mot pour mot : *"Propose egalement un perimetre pour s44 (`MUS-CP-11`, `SCI-CM1-13`,
`MAT-CE1-18`), non engage par ce sprint"*.

### 10.1 Verification faite avant d'enregistrer l'engagement - rien n'a bouge

Je ne reprends pas de confiance l'etat de la section 9.6 ; verifie ce jour, independamment de la
cloture de s43 :

- `MUS-CP-11` : toujours `non couvert` (`MATRICE-couverture.md`, ligne 1852 - "jouable au choix
  textuel, sous `MUS-1`"). Aucun refus, aucune reserve nouvelle depuis le jugement de faisabilite
  du 2026-09-03 (`squad/programme/education-musicale/competences-cp.md`).
- `SCI-CM1-13` : toujours `non couvert` (`MATRICE-couverture.md`, ligne 2136 - "jouable au choix
  textuel, sous `BIA-3`"). Meme constat, meme source
  (`squad/programme/sciences-et-technologie/competences-cm1.md`).
- `MAT-CE1-18` : toujours `non couvert` (`MATRICE-couverture.md`, ligne 689). Son arbitrage
  pedagogique prealable - **ACCEPTE SOUS CONDITIONS**, `DIV-CE1-1` a `DIV-CE1-5` - reste celui
  rendu le 2026-09-25 par `nadia-prof`
  (`squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md`, section 4), non revise
  depuis.
- Le sprint s43, cloture le meme jour, ferme trois autres lignes (`QLM-CP-08`, `EPS-CM1-10`,
  `MAT-CE1-12`, `RAPPORT.md`) sans toucher a aucune des trois lignes de s44 ni au reste du vivier -
  verifie contre la matrice et contre le rapport de cloture.
- Une seule reserve touche ce perimetre, et elle ne l'invalide pas : la frontiere entre
  `MAT-CE1-18` et `MAT-CE1-06` (section 9.3, cinquieme collision), signalee non tranchee par les
  lots d'arbitrage prealable et deja routee a `sofia-sm` en section 9.8, reste **ouverte**
  (`RAPPORT.md`, "Obstacles [...] encore ouverts"). Elle ne remet pas en cause le verdict de
  `nadia-prof` sur `MAT-CE1-18`, qui demeure ACCEPTE SOUS CONDITIONS ; elle conditionne l'ecriture
  du paquet, pas l'engagement du perimetre - detail en section 10.2 et dans la story
  `S44-MAT-DIVISION`. Elle est, au moment ou j'ecris cette section, en cours de resolution
  (`sofia-sm` vers `theo-archi`), dans un mouvement distinct de celui-ci : je ne la reproduis pas
  ici, je la cite.

Aucun fait constate ne contredit le chiffrage de la section 9.6. Rien ne justifie de le rouvrir.

### 10.2 Statut

Le perimetre de s44 (section 9.6 : `MUS-CP-11`, `SCI-CM1-13`, `MAT-CE1-18`, cible 4,0/5,0, marge
1,0) passe de **propose, non engage** a **Accepte, engage**, ce jour, 2026-09-25. L'ecriture des
trois stories correspondantes est faite par `camille-po` dans le meme mouvement, dans
`docs/backlog/backlog.md`, section "Sprint 44" - meme geste que celui fait pour s43 en
section 9.9. La story `S44-MAT-DIVISION` porte explicitement le prealable non leve de la section
10.1 : le paquet ne s'ecrit pas avant que la frontiere `MAT-CE1-06`/`MAT-CE1-18` soit posee et
fusionnee dans le depot.

### 10.3 Ce que cet amendement ne fait pas

Il ne rouvre aucune des sept lignes ecartees en section 9.7, ni le vivier restant apres retrait
des six lignes desormais engagees sur s43 et s44. Il ne tranche pas la frontiere
`MAT-CE1-06`/`MAT-CE1-18`, qui reste a `theo-archi` via `sofia-sm`. Il ne modifie pas
`squad/programme/MATRICE-couverture.md` : les trois lignes de s44 restent `non couvert` jusqu'au
Done pedagogique que `nadia-prof` prononcera apres avoir joue les paquets livres.
