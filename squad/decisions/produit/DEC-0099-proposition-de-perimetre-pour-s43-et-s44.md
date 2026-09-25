# DEC-0099 - Proposition de perimetre pour s43 et s44

**Statut : Proposee, en attente de validation de l'utilisatrice.**

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
