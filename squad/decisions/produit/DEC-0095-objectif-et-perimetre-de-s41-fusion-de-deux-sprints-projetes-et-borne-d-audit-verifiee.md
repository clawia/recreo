# DEC-0095 - Objectif et perimetre de s41 : fusion en un seul sprint de ce qui etait propose comme deux (EMC, sciences CM1, questionner le monde CP), et verification de la borne d'audit avant tout engagement

- **Statut** : Acceptee, amendee le 2026-09-23 (meme jour, section 9) - passage a deux instances de
  `nadia-prof` arbitre par l'utilisatrice, borne recalculee a 8,0, perimetre porte a six lignes sur
  deux terrains non chevauchants. Les six lignes de la section 6 initiale restent la base de l'un des
  deux terrains ; six lignes supplementaires s'y ajoutent, six restent hors perimetre - le detail est
  en section 9, pas dans les sections 1 a 8 qui suivent inchangees pour la memoire du raisonnement a
  instance unique.
- **Date** : 2026-09-23
- **Auteur** : `camille-po`
- **Sprint** : s41 (avant planning, avant engagement des lots)
- **Prealable, en vigueur et non rouvert par ce document** : `DEC-0089` (perimetre CE1 ouvert,
  mathematiques seule, CM2 differe) - aucune des douze lignes candidates de ce document n'est CE1,
  verifie ligne par ligne en section 5. `DEC-0075` (neuf lignes bloquees, droits d'usage de
  reproductions d'oeuvres non tranches) - aucune des douze lignes n'y figure. `DEC-0093`, section 6
  (deux lignes explicitement ecartees, jamais arbitrees) - `EMC-CM1-06` (principes de la Republique)
  et `SCI-CM1-18` (impact humain sur l'environnement) restent hors de ce document, sur le meme motif
  qu'a s40 : ce sont les lignes les plus dangereuses de leur matiere respective, et aucun arbitrage
  pedagogique prealable de `nadia-prof` ne les a instruites.
- **Lien** : `squad/decisions/adr/ADR-0082-une-reproduction-d-oeuvre-est-une-troisieme-population-d-
  images-et-sa-preuve-est-double.md`, section "Le cout, et le decoupage en taches"
  (methode du tarif, borne d'audit definie comme le tarif publie par `nadia-prof`) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md`,
  sections 4.1 a 4.4 (tarif complet, borne a 5,0, unite = lot de matiere et de niveau jusqu'a vingt
  items) ; `squad/conformite/ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-
  civique-et-ma-borne.md`, section 3 (tarif et classement de surete des lignes d'EMC CP restantes) et
  section 4.3 (classement de surete des lignes de sciences CM1 restantes) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-
  lignes.md`, lignes 368-370 (tarif et surete de `QLM-CP-04`, `QLM-CP-13`) ; `DEC-0083` (ouverture de
  la fenetre a trois instances, borne portee a 12,0) ; `DEC-0087` (cloture de s34 : *"la fenetre de
  trois sprints que DEC-0083 ouvrait pour le Plan 2 se referme avec s34"*, aucune reouverture depuis) ;
  `DEC-0091`, `DEC-0092`, `DEC-0093` (memes sprints a six lignes candidates, sans calcul de borne
  ecrit) ; `squad/programme/enseignement-moral-et-civique/competences-cm1.md` et `competences-cp.md` ;
  `squad/programme/sciences-et-technologie/competences-cm1.md` ;
  `squad/programme/questionner-le-monde/competences-cp.md` ; `squad/programme/MATRICE-couverture.md`
  (lignes citees en section 5) ; `squad/README.md`, section 2 bis (les trois formes d'attribution).
  **Pour l'amendement de la section 9** :
  `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-
  lignes.md`, section 13.3 (`PAR-1` a `PAR-7`, dont `PAR-2` - EMC CP et EMC CM1 comptent pour une
  seule matiere - et `PAR-7`) et section 13.5 (`PAR-8`, derivation initiale de la borne a deux
  instances, 8,0) ; `squad/conformite/ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-
  catechisme-civique-et-ma-borne.md`, section 1.1 (`nadia-prof` disqualifie sa propre derivation,
  refuse la correction a 9,0, retient 8,0 comme chiffre empirique) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-
  le-prix-d-une-ligne-marginale.md`, section 4.1-4.2 (*"si l'utilisatrice reste a deux instances, ma
  borne est 8,0"*), section 6.4 (la seule collision de terrain qu'elle nomme d'avance, EPS/sciences
  CM1/questionner le monde CP sur le corps et l'effort) et section 5.1 (`PAR-8` reconduit sans
  changement : plafond individuel 5,0, au plus trois lots par instance) ;
  `squad/decisions/produit/DEC-0083-strategie-de-s32-a-s34-passage-a-trois-instances-de-nadia-prof-
  borne-a-12-0-decote-par-paire-le-plan-2-retenu-le-palier-2-differe-a-theo-archi.md`, section 9.2
  (meme collision) et section sur `PAR-12`/`PAR-13` ;
  `squad/decisions/adr/ADR-0081-une-ligne-de-referentiel-a-un-code-alloue-et-un-item-servi-ne-sert-qu-
  une-ligne.md`, clause 5 (un item n'est compte que sur une ligne).
- **Competence programme visee** : transverse - ce document porte sur l'objectif, le perimetre et la
  borne d'un sprint, pas sur le contenu d'une ligne.

---

## 1. Ce que ce document fait

1. Formalise en `DEC` l'objectif recu : fusionner en un seul sprint s41 ce qui avait ete propose
   comme deux sprints separes (EMC d'une part, sciences CM1 et questionner le monde CP d'autre
   part), soit jusqu'a douze lignes candidates.
2. Nomme, sans les masquer, les limites de sourcage de ce document - section 3.
3. **Verifie, avant d'ecrire un seul critere, si douze lignes tiennent dans la borne d'audit de
   `nadia-prof`** - section 4. Reponse : non, et de loin.
4. Verifie ligne par ligne le statut et le risque connu de chacune des douze lignes candidates -
   section 5, sur le meme principe que `DEC-0093` section 4 (ne pas presumer qu'une ligne "jouable
   au choix" est sans question).
5. Retient un perimeter reduit de deux lots, choisis parmi les moins couteux et les moins risques
   des douze - section 6.
6. Route explicitement, sans les trancher, la question de la borne pour s42 et au-dela, et l'ecart
   non documente mesure sur s39 et s40 - section 7.

## 2. L'objectif, tel que recu

Recu directement comme instruction de tache, **sans fichier `planning.md` et sans relais par
`sofia-sm`** : fusionner en un seul sprint numerote s41 ce qui avait ete propose comme deux sprints
(s41 EMC, s42 sciences CM1 et questionner le monde CP), jusqu'a douze lignes candidates :

| # | Ligne | Matiere / niveau | Code referentiel |
|---|---|---|---|
| 1 | Reconnaître les symboles de la République française et dire ce qu'ils représentent | EMC CM1 | `EMC-CM1-05` |
| 2 | Identifier le rôle de quelques institutions proches (mairie, conseil municipal, école) | EMC CM1 | `EMC-CM1-09` |
| 3 | Comprendre le sens du vote et de la représentation | EMC CM1 | `EMC-CM1-10` |
| 4 | Reconnaître et nommer les symboles de la République française | EMC CP | `EMC-CP-08` |
| 5 | Identifier les principaux droits de l'enfant | EMC CP | `EMC-CP-10` |
| 6 | Identifier quelques rôles et services de l'école et de la commune | EMC CP | `EMC-CP-13` |
| 7 | Décrire le mouvement d'un objet par sa trajectoire et par sa vitesse | sciences et technologie CM1 | `SCI-CM1-03` |
| 8 | Classer des êtres vivants d'après un caractère commun observable | sciences et technologie CM1 | `SCI-CM1-06` |
| 9 | Connaître quelques règles d'usage d'un environnement numérique | sciences et technologie CM1 | `SCI-CM1-14` |
| 10 | Situer la Terre dans le système solaire et relier les mouvements de la Terre aux saisons | sciences et technologie CM1 | `SCI-CM1-15` |
| 11 | Connaître quelques règles d'hygiène de vie et leur effet sur la santé | questionner le monde CP | `QLM-CP-04` |
| 12 | Nommer et ordonner les mois de l'année et les saisons | questionner le monde CP | `QLM-CP-13` |

Toutes les douze verifiees `non couvert` ce jour dans `squad/programme/MATRICE-couverture.md`
(section 5). Explicitement exclues, sur instruction et sans arbitrage prealable de `nadia-prof` :
`EMC-CM1-06` (principes de la Republique) et `SCI-CM1-18` (impact humain sur l'environnement), les
deux lignes les plus dangereuses de leur matiere respective (`DEC-0093`, section 6).

## 3. Ce que ce document sourci, et ce qu'il ne sourci pas - a lire avant de s'appuyer dessus

**Ce document ne repose sur aucun `planning.md` et sur aucune citation entre guillemets de
l'utilisatrice.** L'instruction de fusionner les deux sprints m'est parvenue directement, hors des
neuf agents, sans qu'un fichier du depot ne l'ait deja portee avant que je l'ecrive ici : c'est la
**forme 3** de `squad/README.md` section 2 bis (*"recu de MASTER, sans source dans le depot"*), et
je le nomme comme tel plutot que de fabriquer un relais qui n'existe pas.

**Ce que je retiens malgre cette reserve, et pourquoi ce document reste `Acceptee` plutot que
`Proposee`.** Comme `DEC-0083`, `DEC-0085`, `DEC-0086`, `DEC-0091`, `DEC-0092` et `DEC-0093`, ce
document formalise un **objectif et un perimetre de sprint**, jamais un contenu pedagogique, aucune
borne de tirage, aucun texte d'indice. Ces objets-la, la forme 3 ne les fonde jamais ; rien ici n'y
deroge.

**Ce que la forme 3 ne fonde jamais non plus, et que je ne fais pas dire a l'instruction recue** :
le nombre de lignes qui fermeront reellement, ni le fait que la borne d'audit de `nadia-prof`
absorbe douze lignes. Ces deux points se verifient contre le depot, pas contre l'instruction - c'est
l'objet de la section 4.

## 4. La borne d'audit verifiee, et ce que douze lignes couteraient

### 4.1 La borne en vigueur aujourd'hui, et pourquoi ce n'est pas 12,0

Le tarif de `nadia-prof` et sa borne standard sont ecrits une fois, `ARBITRAGE-PREALABLE-s28`,
section 4.1, reconfirmes sans changement a s24, s25, s26, s27 : **quatre a cinq lots de contenu par
sprint sur une mecanique existante**, unite = **le lot de matiere et de niveau, jusqu'a vingt
items**, prix 1,0 si elle est la source de la matiere, 1,5 si elle ne l'est pas, +0,5 pour un lot
illustre. **Sa borne standard est 5,0.**

Cette borne a ete portee a 12,0 (puis 15,0) le temps d'un dispositif different - **trois instances
d'elle-meme en parallele**, arbitre par l'utilisatrice via `DEC-0083`. `DEC-0087`, cloture de s34,
le dit explicitement : *"la fenetre de trois sprints que `DEC-0083` ouvrait pour le Plan 2 se referme
avec s34"*. **Aucune `DEC` depuis n'a rouvert le passage a plusieurs instances, ni releve la borne
standard au-dela de 5,0.** `DEC-0088`, `DEC-0089`, `DEC-0091`, `DEC-0092`, `DEC-0093` ne mentionnent
ni instance ni borne. **Je ne suppose donc pas 12,0 : la borne en vigueur pour un sprint a instance
unique, sauf arbitrage contraire non ecrit a ce jour, est 5,0.**

### 4.2 Ce que douze lignes couteraient, au tarif publie et a la granularite reellement pratiquee

**Methode** : je reprends le tarif publie de `nadia-prof` (section 4.1) et je l'applique a la
granularite **effectivement pratiquee** a s39 et s40 - un lot nomme par ligne (`S40-HDA`,
`S40-EXTRACTION`, etc., `DEC-0093` section 5), pas un lot combinant plusieurs lignes. C'est une
estimation faite par mes soins, au tarif qu'elle a ecrit, pas une facturation qu'elle a rendue
elle-meme - elle seule confirme un chiffre qui la concerne.

| Matiere / niveau | Lignes candidates | Tarif par lot | Nombre de lots (un par ligne) | Cout |
|---|---|---|---|---|
| EMC CM1 | `EMC-CM1-05`, `-09`, `-10` | 1,5 (`DEC-0077`, second amendement : *"elle n'est pas la source [...] en EMC CM1 (1,5 chacune)"*) | 3 | 4,5 |
| EMC CP | `EMC-CP-08`, `-10`, `-13` | 1,5 (`ARBITRAGE-PREALABLE-s31`, section 3 : lignes 8 a 14 a 1,5) | 3 | 4,5 |
| Sciences et technologie CM1 | `SCI-CM1-03`, `-06`, `-14`, `-15` | 1,5 (`ARBITRAGE-PREALABLE-s31`, section 4 : *"1,5 par lot, sans exception de ligne [...] je ne le redecoupe pas"*) | 4 | 6,0 |
| Questionner le monde CP | `QLM-CP-04`, `-13` | 1,0 (`ARBITRAGE-PREALABLE-s28`, section 4.2 : *"j'en suis la source"*) | 2 | 2,0 |
| **Total** | **12 lignes** | | **12 lots** | **17,0** |

**17,0 contre une borne de 5,0 : plus de trois fois la capacite d'un sprint a instance unique.**
Meme en regroupant plusieurs lignes d'une meme matiere et d'un meme niveau dans un seul lot de vingt
items - possible en theorie, `FER-2` exigeant au moins dix items par ligne, donc deux lignes au
plus par lot - le minimum ne descend pas sous sept lots (2+2+2+1) pour un cout plancher d'environ
10,0, **encore le double de la borne**. Douze lignes ne tiennent dans aucun cas de figure en un seul
sprint a instance unique.

### 4.3 Un ecart trouve en verifiant, pas suppose : s39 et s40 ont deja depasse cette borne sans qu'aucune `DEC` ne le nomme

**Methode, et elle porte son incertitude** : j'applique le meme tarif publie aux six lots reellement
instruits par `DEC-0092` (s39) et `DEC-0093` (s40), sur la granularite qu'elles ecrivent elles-memes
(un lot par ligne). Aucune des deux `DEC` ne calcule ce total ; je le fais ici pour la premiere fois,
et **c'est une estimation contre son tarif ecrit, pas une facturation qu'elle a rendue** - le prix
exact d'un lot precis lui appartient.

| Sprint | Lots (un par ligne) | Cout estime | Borne | Ecart |
|---|---|---|---|---|
| s39 (`DEC-0092`) | `S39-DEN` (1,0), `S39-LONGUEUR` (1,0), `S39-MONNAIE` (1,0), `S39-SOLIDES` (1,0), `S39-ENERGIE` (1,5, sciences non source), `S39-OUTILS` (1,5, arts plastiques non source, tarif non confirme par elle sur cette ligne precise) | **~7,0** | 5,0 | **+2,0 environ** |
| s40 (`DEC-0093`) | `S40-HDA` (2,0, histoire des arts non source + illustre), `S40-EXTRACTION` (1,5), `S40-NUTRITION` (1,5), `S40-REGLES` (1,5), `S40-LOI` (1,5), `S40-DEVISE` (1,5, EMC CP ligne 9, hors des lignes 1 a 7) | **9,5** | 5,0 | **+4,5** |

**Ce que je n'affirme pas** : que `nadia-prof` a reellement depense ce temps, ou qu'elle a subi un
prejudice - je n'ai aucune mesure de son temps reel, seulement son tarif applique a ce qu'elle a
livre. **Ce que je nomme, parce que c'est ecrit nulle part ailleurs** : ni `DEC-0092` ni `DEC-0093`
ne calculent un cout contre la borne avant d'instruire six lots chacune, a la difference de la
discipline tenue de s28 a s34 (`DEC-0076` a `DEC-0080`, chaque depassement nomme et chiffre au
moment ou il est engage). **Cette discipline s'est arretee sans qu'aucune `DEC` ne le decide** ; je
ne la reprends pas silencieusement moi non plus, je le route en section 7.

## 5. Verification ligne par ligne des douze lignes candidates

**Statut dans `squad/programme/MATRICE-couverture.md`, verifie ce jour, ligne par ligne** (pas de
confiance dans un numero de ligne cite avant verification) :

| Code | Statut matrice | Arbitrage prealable existant | Risque nomme |
|---|---|---|---|
| `EMC-CM1-05` | `non couvert` (ligne 1914) | Referentiel (`competences-cm1.md`, 2026-09-03) : *"connaissance exacte et verifiable, terrain sur"*, sous `ART-1`. Aucun arbitrage dedie au-dela. | Aucun biais signale. |
| `EMC-CM1-09` | `non couvert` (ligne 1918) | Referentiel, meme date : *"connaissance verifiable [...] aucun item ne suppose la commune de l'enfant"*, sous `EMC-6`. | Aucun biais signale. |
| `EMC-CM1-10` | `non couvert` (ligne 1919) | Referentiel, meme date : *"sur la connaissance seule"*, mention de couverture partielle exigee d'avance (vote/representation seuls, participation a une decision collective non couverte). | Aucun biais signale, mais une mention obligatoire a tenir. |
| `EMC-CP-08` | `non couvert` (ligne 1860) | `ARBITRAGE-PREALABLE-s31`, section 3.1, ligne `EMC-CP-08` : *"oui (lot a 1,5) [...] `ART-1` : aucune reponse portee par une couleur percue"*. | Aucun biais signale. |
| `EMC-CP-10` | `non couvert` (ligne 1862) | `ARBITRAGE-PREALABLE-s31`, section 3.1, ligne `EMC-CP-10` : **"oui, et c'est le terrain le plus sur des cinq a 1,5"**. `BIA-4` et `EMC-1` deja poses : aucun item n'interroge l'enfant sur sa propre situation. | **La plus sure des cinq lignes d'EMC CP a ce tarif, au dire de `nadia-prof` elle-meme.** |
| `EMC-CP-13` | `non couvert` (ligne 1865) | `ARBITRAGE-PREALABLE-s31`, section 3.1, ligne `EMC-CP-13` : *"oui (lot a 1,5) [...] `EMC-6` : aucun item ne suppose la commune, l'ecole ou le foyer de l'enfant"*. | Aucun biais signale. |
| `SCI-CM1-03` | `non couvert` (ligne 2003) | `ARBITRAGE-PREALABLE-s31`, section 4.3 : classee dans *"les suivantes"* (deuxieme rang de surete sur quatre), *"porte un piege nomme : le calcul de vitesse compte sur les mathematiques"* (recouvrement a ne pas double-compter, deja documente dans le referentiel). | Feasible, piege deja documente, pas un biais. |
| `SCI-CM1-06` | `non couvert` (ligne 2006) | `ARBITRAGE-PREALABLE-s31`, section 4.3 : classee dans *"les suivantes"*, mention de couverture partielle due (*"item par item ; le tri d'une collection entiere reste non couvert"*). | Feasible, pas un biais. |
| `SCI-CM1-14` | `non couvert` (ligne 2014) | `ARBITRAGE-PREALABLE-s31`, section 4.3 : classee *"a ne pas prendre en l'etat"* **avec le couple de taches initial**, mais `nadia-prof` fournit elle-meme, dans le meme document, un couple de taches de remplacement (*"dire ce qu'une donnee personnelle permet a un inconnu de faire"* / *"dire a qui on s'adresse [...]"*). | **Prenable seulement avec le couple de taches fourni par elle** ; l'ancien couple est refuse d'avance. |
| `SCI-CM1-15` | `non couvert` (ligne 2015) | `ARBITRAGE-PREALABLE-s31`, section 4.3 : classee **"a ne pas prendre non plus"**, motif *"`SCI-1` a son point le plus exigeant [...] aucune de ces conditions n'a rencontre de contenu reel"* (le piege de la distance au Soleil confondue avec l'inclinaison de l'axe). | **La plus risquee des quatre lignes de sciences candidates, au dire de `nadia-prof` elle-meme** (2026-09-07, jamais revisee depuis). |
| `QLM-CP-04` | `non couvert` (ligne 1485) | `ARBITRAGE-PREALABLE-s29`, ligne 370 : *"moyenne, sous `BIA-4`. Terrain sur si aucun item n'interroge l'enfant sur sa propre vie"*. | Biais nomme d'avance (`BIA-4`), condition connue et deja tenue ailleurs. |
| `QLM-CP-13` | `non couvert` (ligne 1494) | `ARBITRAGE-PREALABLE-s29`, ligne 368 : *"rentable [...] `FER-1` sur les deux moities, pas sur les douze mois"*. `ARBITRAGE-PREALABLE-s27`, section 7.1 : *"terrain deja eprouve par `cp-semaine`"*. | Aucun biais signale, terrain le plus eprouve des douze. |

**Aucune des douze lignes ne souleve de question pedagogique non tranchee** au sens de `DEC-0093`
section 4 : chacune porte deja un arbitrage prealable explicite ou un jugement de faisabilite ecrit
par `nadia-prof`. `theo-archi` peut donc rendre un avis de faisabilite directement sur n'importe
laquelle sans attendre un arbitrage neuf - **la question qui reste n'est pas pedagogique, elle est
de capacite d'audit** (section 4).

## 6. Le perimetre retenu pour s41

**Deux lots, choisis parmi les moins couteux et les moins risques des douze**, conformement a la
consigne de reduire le perimetre a la borne plutot que de l'y forcer :

| Lot | Ligne | Tarif | Motif du choix |
|---|---|---|---|
| `S41-EMC-DROITS` | `EMC-CP-10` (droits de l'enfant) | 1,5 | La ligne la moins risquee des six lignes d'EMC candidates, sur le mot de `nadia-prof` elle-meme (*"le terrain le plus sur des cinq a 1,5"*, `ARBITRAGE-PREALABLE-s31`) ; `BIA-4` et `EMC-1` deja poses, aucune question ouverte. |
| `S41-QLM-SAISONS` | `QLM-CP-13` (mois et saisons) | 1,0 | La moins chere des douze lignes (`nadia-prof` en est la source), et la seule sans aucun biais nomme sur un terrain *"deja eprouve"*. |
| **Total** | | **2,5** | **Sur une borne de 5,0, marge de 2,5.** |

**Pourquoi pas un troisieme lot pour approcher la borne.** J'ai considere ajouter un lot de sciences
CM1 (`SCI-CM1-03` ou `SCI-CM1-06`, tous deux 1,5, ce qui porterait le total a 4,0) ou un second lot
d'EMC. Je ne le fais pas ici : la consigne recue demande explicitement de garder *"le lot EMC + le
lot sciences/QLM les plus surs"*, au singulier chacun, pas le maximum que la borne tolererait. Si
l'utilisatrice souhaite un troisieme lot, c'est un arbitrage a elle, pas une extension que je
m'autorise en le presentant comme deja compris dans l'instruction recue.

**Les dix lignes non retenues restent `non couvert`**, sans degradation, dans le vivier des sprints
suivants : `EMC-CM1-05`, `-09`, `-10`, `EMC-CP-08`, `-13`, `SCI-CM1-03`, `-06`, `-14`, `-15`,
`QLM-CP-04`. Aucune n'est ecartee pour une raison pedagogique propre - seule la borne d'audit les
exclut de s41. `SCI-CM1-15` porte en outre un avertissement specifique a ne pas oublier au moment
ou elle sera reprise (section 5).

## 7. Ce que ce document route

- **Instruction des deux lots** dans `docs/backlog/backlog.md` : **non faite dans ce document**, a
  la difference de `DEC-0092` et `DEC-0093`. Consigne recue explicitement pour s41 : ne pas creer le
  dossier de sprint ni toucher au backlog, ce sera fait par `sofia-sm` a l'etape suivante. Elle
  instruit `S41-EMC-DROITS` (`EMC-CP-10`) et `S41-QLM-SAISONS` (`QLM-CP-13`) sur la base de la
  section 6.
- **Avis de faisabilite de `theo-archi`**, sur les deux lots, directement : aucun arbitrage
  pedagogique prealable neuf n'est du, les deux lignes etant deja instruites (section 5).
- **Aucun arbitrage pedagogique prealable route a `nadia-prof`** sur les deux lots retenus : ce
  n'est pas necessaire, contrairement a la clause de routage habituelle quand une question reste
  ouverte (`DEC-0092`, `DEC-0093`).
- **Question routee a `nadia-prof`, via `sofia-sm`, non bloquante pour ce document mais a trancher
  avant tout troisieme lot** : sa borne standard est-elle toujours 5,0 par sprint a instance unique,
  ou le fait que s39 et s40 l'aient chacune depassee sans qu'elle le signale vaut-il confirmation
  tacite d'une capacite plus haute ? Je ne le suppose pas dans un sens ni dans l'autre (section 4.3).
- **Question routee a `sofia-sm`** : la discipline de calcul du cout contre la borne, tenue de s28 a
  s34 (`DEC-0076` a `DEC-0080`), s'est arretee sans decision ecrite a partir de `DEC-0091`. Est-ce un
  abandon assume ou un oubli ? Si c'est un oubli, il concerne aussi bien `sofia-sm` que moi : aucune
  des deux `DEC` de s39 et s40 n'est de son fait seul.
- **Confirmation de l'utilisatrice attendue avant l'ouverture effective de s41** : ce document
  repose sur une instruction forme 3 seule (section 3). S'il est infirme ou ajuste, il est amende ou
  remplace, jamais silencieusement contourne - meme clause que `DEC-0089`, `DEC-0091`, `DEC-0092` et
  `DEC-0093`.

## 8. Ce que ce document ne fait pas

- Il ne fixe aucune borne de tirage, aucune forme de distracteur, ni aucun contenu pedagogique pour
  aucune des douze lignes : ce travail reste entierement a `nadia-prof`.
- Il ne choisit aucun mecanisme technique : ce travail reste a `theo-archi`.
- Il ne rouvre ni `DEC-0089`, ni `DEC-0075`, ni les deux lignes explicitement ecartees par
  `DEC-0093` section 6 (`EMC-CM1-06`, `SCI-CM1-18`).
- Il ne rouvre pas le passage a plusieurs instances ferme par `DEC-0087` : la borne appliquee ici est
  celle a instance unique, 5,0, faute d'un arbitrage ecrit qui dirait autre chose.
- Il ne pretend pas que le calcul de cout de la section 4.3 est un verdict de `nadia-prof` sur son
  propre temps : c'est une estimation faite par mes soins contre son tarif publie, nommee comme
  telle.
- Il n'instruit aucune story dans `docs/backlog/backlog.md` et ne cree aucun dossier de sprint : ces
  deux gestes sont explicitement laisses a `sofia-sm`, sur consigne recue pour ce document.
- Il ne garantit la fermeture d'aucune des deux lignes retenues : l'avis de faisabilite de
  `theo-archi` et le Done pedagogique final de `nadia-prof` restent a rendre.
- Il ne pretend pas detenir une confirmation forme 2 de l'utilisatrice sur le detail du perimetre
  qu'il n'a pas - section 3.

---

## 9. Amendement du 2026-09-23 (meme jour) - passage a deux instances arbitre, borne recalculee a 8,0, perimetre porte a six lignes sur deux terrains non chevauchants

**Ce que cet amendement change** : les sections 1 a 8 ci-dessus raisonnaient a instance unique, borne
5,0, et retenaient deux lignes. Une instruction recue apres l'ecriture de ce document autorise le
passage a deux instances de `nadia-prof` en parallele pour s41, sur le modele de `DEC-0083`.
**Ce document reste `Acceptee` et n'est pas remplace** : le raisonnement des sections 1 a 8 n'est pas
faux, il portait sur l'hypothese qui avait cours au moment ou il a ete ecrit ; cet amendement l'etend,
il ne le retracte pas.

### 9.1 Sourcage de l'arbitrage a deux instances

**Recu directement comme instruction de tache**, dans les memes termes que la section 2 : *"L'utilisatrice
autorise explicitement le passage a deux instances de `nadia-prof` en parallele pour ce sprint s41,
sur le modele de `DEC-0083`."* Meme forme 3 que le reste de ce document (`squad/README.md` section
2 bis) : je ne detiens pas la citation exacte des mots de l'utilisatrice elle-meme, je nomme le canal
qui me la transmet comme tel, et je ne le fais pas dire plus qu'il ne dit. **Ce que je retiens malgre
cette reserve, pour le meme motif que la section 3** : `DEC-0077` (s29), `DEC-0079` (s30) et `DEC-0083`
(s32) sont les trois precedents ou l'utilisatrice a arbitre un passage a plusieurs instances sur un
sourcage du meme ordre - un enonce narre ou recu hors des neuf agents, jamais une citation entre
guillemets du detail technique.

### 9.2 La borne a deux instances, verifiee et non reprise de seconde main

**Ce que je ne fais pas** : reprendre "8,00 sur 8,0" cite en passant par `DEC-0083` section 2 comme
une preuve suffisante. Je remonte a la source.

**La chaine complete, dans l'ordre chronologique** :

1. **Origine, s29** (`ARBITRAGE-PREALABLE-s29`, section 13.5, 2026-09-05) : `nadia-prof` derive
   8,0 depuis 2 x 5,0 = 10,0 brut, moins 0,5 (passe transverse payee), moins 0,5 (dettes de portee
   **D** payables), moins 1,0 (retenue de defiance sur un premier essai de parallelisation).
   `PAR-8` : *"la borne passe de 5,0 a 8,0 pour s29. Le plafond individuel reste 5,0 par instance, et
   au plus trois lots par instance."*
2. **Tenue a s30** (`DEC-0079`, `DEC-0080`) : borne 8,0 reconduite, budget opposable 4,0 par instance
   (`PAR-9`), sprint clos a 8,50/8,0 - un depassement de 0,50, nomme comme tel.
3. **Remise en cause de sa propre methode, s31** (`ARBITRAGE-PREALABLE-s31`, section 1.1,
   2026-09-07) : `nadia-prof` trouve que sa derivation de s29 soustrayait deux fois les memes 0,5 -
   corrigee, elle donnerait 9,0, pas 8,0. **Elle refuse cette correction a la hausse** : *"Je ne prends
   pas ce +1,0 [...] `camille-po` et l'utilisatrice peuvent decider de prendre le +1,0 corrige ; moi,
   non. Ce n'est pas ma decision de produit, c'est ma discipline d'auditeur."* Elle retient **8,0
   comme chiffre empirique et date**, valable a s31 : *"C'est la valeur a laquelle trois sprints
   mesures ont produit des audits que je peux defendre."*
4. **Verification a l'unite, s31** : *"8,00 sur 8,0, instance A 4,00/4,00, instance B 4,00/4,00 -
   premier sprint sans depassement en quatre"* (cite par `ARBITRAGE-PREALABLE-s32`, section 4.2, qui
   verifie ce chiffre contre `PAR-9`).
5. **Reconfirmation explicite au moment du passage a trois instances, s32** (`ARBITRAGE-PREALABLE-s32`,
   section 4.2, 2026-09-08) : *"Si l'utilisatrice reste a deux instances, ma borne est 8,0, comme je
   l'ai ecrit ce matin meme a la cloture de s31, et rien dans ce document ne la change. 12,0 n'est pas
   une revision a la hausse de 8,0 : c'est la borne d'un autre dispositif."*
6. **Aucune reouverture depuis.** `DEC-0087` (cloture de s34) ferme la fenetre a trois instances, pas
   le chiffre 8,0 lui-meme, qui n'a jamais ete revise depuis le 2026-09-08. Aucune `DEC` entre s35 et
   s40 ne mentionne d'instance ni de borne (constat deja fait en section 4.1).

> **Borne retenue pour s41 a deux instances : 8,0.** C'est le chiffre empirique de `nadia-prof`,
> jamais corrige a la hausse par elle-meme malgre une derivation qu'elle a trouvee fausse, et jamais
> revise depuis le 2026-09-08. Je ne prends pas non plus le 9,0 corrige a sa place : ce choix lui
> appartient, elle l'a dit elle-meme, et rien dans l'instruction recue ne demande de le faire pour
> elle. Je note seulement que l'option existe, pour que `sofia-sm` sache qu'elle peut la lui reposer.
>
> **Deux clauses reconduites sans changement, sourcees a `ARBITRAGE-PREALABLE-s32` section 5.1** :
> plafond individuel dur **5,0 par instance**, et **au plus trois lots par instance** (`PAR-8`). Ce
> second plafond n'est pas une consequence du premier : avec des lots a 1,0 et 1,5, le budget
> autoriserait davantage de lots que trois avant d'atteindre 5,0. C'est un plafond distinct, et il
> se revele determinant en section 9.4.

### 9.3 Deux terrains non chevauchants - verification de la frontiere, pas supposition

**Le split recu** : terrain 1 = EMC (CP + CM1), terrain 2 = sciences et technologie CM1 +
questionner le monde CP.

**`PAR-2` (`ARBITRAGE-PREALABLE-s29`, section 13.3) force deja le terrain 1 a une seule instance, et
ce n'est pas une consequence du split recu, c'est une regle anterieure et independante** : *"Une
matiere et un niveau ne sont jamais partages [...] `EMC` CP et `EMC` CM1 comptent pour une seule
matiere au sens de cette clause."* Le terrain 1 tel que recu est donc la seule composition possible
pour l'EMC a deux instances - il ne pouvait pas etre coupe autrement sans violer `PAR-2`.

**`CYC-1` (meme document, section 13.4) s'applique au terrain 2** : *questionner le monde* CP se
scinde en *sciences et technologie* CM1 (et en *histoire-geographie* CM1, hors perimetre ici), et un
item de cycle 3 ne doit contredire aucun item de cycle 2 deja servi sur le meme objet. **Sa mise en
oeuvre est une obligation de lecture, pas une contrainte d'affectation** (texte exact de la clause) :
*"l'instance qui audite une ligne de cycle 3 lit les paquets servis de cycle 2 portant sur le meme
objet."* Le terrain 2, en mettant `QLM-CP-*` et `SCI-CM1-*` sous la **meme** instance, satisfait cette
obligation par construction - c'est la composition la plus simple pour `CYC-1`, pas seulement une
composition qui la respecte.

**Reste la question posee explicitement : le fait que les deux terrains ne se chevauchent pas
dispense-t-il d'ecrire une clause de frontiere, comme `DEC-0083` section 9.2 en avait ecrit une pour
la collision EPS/sciences CM1/questionner le monde CP sur le corps et l'effort ?** Verification faite,
pas supposee :

- **`ADR-0081` clause 5** : *"un item n'est compte que sur une ligne."* C'est le mecanisme qui rend
  une collision de contenu visible ou dangereuse - deux instances qui ecrivent, sans le savoir, sur le
  meme objet du monde reel produisent des items **factuellement redondants ou contradictoires**, meme
  si chaque item est compte sur une ligne differente et correcte.
- **Le critere que `nadia-prof` applique elle-meme pour decider d'ecrire une frontiere** (`ARBITRAGE-
  PREALABLE-s32`, section 6.4) n'est pas *"deux terrains distincts"*, c'est **un objet du monde reel
  partage entre des lignes de matieres differentes affectees a des instances differentes** - dans son
  cas, *"l'activite physique et le corps"*, porte a la fois par l'EPS (terrain C), les sciences CM1
  (terrain B) et questionner le monde CP (terrain A). Elle l'ecrit *"la seule couture que je vois
  d'avance"* : une couture n'est pas nommee par defaut, elle est nommee quand un objet reel est
  identifiable des deux cotes.
- **Verification contre les douze lignes de ce document** : le terrain 1 (EMC) porte des symboles, des
  institutions, un vote, des droits, des roles - **aucun objet materiel ou biologique**. Le terrain 2
  porte un mouvement, un classement du vivant, des regles numeriques, la Terre et les saisons, une
  hygiene de vie, des mois. **Aucune paire de lignes, une dans chaque terrain, ne porte le meme objet
  du monde reel** - a la difference du cas EPS/sciences/QLM, ou *"l'activite physique et le corps"*
  est litteralement le meme objet dans les trois libelles. La ligne la plus proche d'une collision
  serait `QLM-CP-04` (hygiene de vie) avec la ligne de sciences CM1 *"Relier l'alimentation, l'activite
  physique, le sommeil et l'hygiene au bon fonctionnement du corps"* - **mais cette derniere n'est pas
  `SCI-CM1-03`, `-06`, `-14` ni `-15`, les quatre seules lignes de sciences candidates ici** ; elle
  n'entre dans aucune composition possible de ce document.

**Conclusion : aucune clause de frontiere n'est due entre les deux terrains de s41.** L'absence n'est
pas supposee, elle est verifiee contre le seul critere que `nadia-prof` a ecrit pour en produire une -
un objet reel partage entre des lignes de matieres differentes sur des instances differentes - et ce
critere ne trouve aucune occurrence entre le terrain EMC et le terrain sciences/questionner le monde.
`PAR-2` et `CYC-1` restent opposables tels qu'ecrits, sans amendement.

### 9.4 Perimetre maximal sous la borne a 8,0 verifiee

**Methode** : je repars du vivier des douze lignes candidates (section 2), j'affecte le terrain 1 a
une instance et le terrain 2 a l'autre (imposees par `PAR-2`, section 9.3), et j'applique le tarif de
`nadia-prof` (section 4) sous les deux plafonds de `PAR-8` - 5,0 par instance, trois lots par instance
au plus.

**Terrain 1 - EMC (une instance)** : six lignes candidates, toutes a 1,5 (`EMC-CM1-05/-09/-10`,
`EMC-CP-08/-10/-13`, aucune tarifee moins cher dans ce vivier - les lignes d'EMC CP a 1,0, lignes 1 a
7, sont deja `couvert-valide` ou hors de ce vivier). **Trois lots au plus (`PAR-8`) : 3 x 1,5 = 4,5,
sous le plafond individuel de 5,0.** Je retiens les trois lignes deja identifiees comme les mieux
instruites individuellement en section 5 - toutes trois nommees et classees par `nadia-prof` dans
`ARBITRAGE-PREALABLE-s31` section 3.1, la seule des deux groupes d'EMC a porter un classement de
surete ecrit ligne par ligne :

| Lot | Ligne | Tarif | Motif |
|---|---|---|---|
| `S41-EMC-SYMBOLES` | `EMC-CP-08` | 1,5 | *"oui (lot a 1,5) [...] `ART-1` : aucune reponse portee par une couleur percue"* |
| `S41-EMC-DROITS` | `EMC-CP-10` | 1,5 | *"le terrain le plus sur des cinq a 1,5"* |
| `S41-EMC-ROLES` | `EMC-CP-13` | 1,5 | *"oui (lot a 1,5) [...] aucun item ne suppose la commune, l'ecole ou le foyer de l'enfant"* |
| **Terrain 1** | | **4,5 / 5,0** | **Trois lots, plafond de `PAR-8` atteint** |

`EMC-CM1-05`, `-09`, `-10` restent hors de ce terrain, non par risque mais par plafond de lots : elles
n'ont pas de classement de surete dedie aussi precis que les cinq lignes d'`ARBITRAGE-PREALABLE-s31`,
et a tarif egal (1,5 partout), je retiens les trois lignes dont l'evaluation ecrite est la plus fine
plutot que d'en inferer une moi-meme sur les trois autres.

**Terrain 2 - sciences CM1 + questionner le monde CP (l'autre instance)** : six lignes candidates,
deux a 1,0 (`QLM-CP-04`, `QLM-CP-13`) et quatre a 1,5 (`SCI-CM1-03/-06/-14/-15`), dont `SCI-CM1-15`
deja exclue par le risque signale en section 5 (*"a ne pas prendre non plus"*, non rouverte par
l'instruction recue). **Trois lots au plus.** Les deux lots de questionner le monde CP, les moins
chers et sans biais signale, plus la ligne de sciences la mieux classee parmi les trois restantes
(`SCI-CM1-03`, `-06`, `-14` sont toutes trois dans le rang *"les suivantes"* ou *"a reprendre avec un
couple de taches fourni"* d'`ARBITRAGE-PREALABLE-s31` section 4.3 - aucune n'est *"la plus sure"* au
sens ou `EMC-CP-10` l'est, donc le choix se fait sur la continuite avec du contenu deja ferme) :

| Lot | Ligne | Tarif | Motif |
|---|---|---|---|
| `S41-QLM-HYGIENE` | `QLM-CP-04` | 1,0 | *"moyenne, sous `BIA-4`. Terrain sur si aucun item n'interroge l'enfant sur sa propre vie"* |
| `S41-QLM-SAISONS` | `QLM-CP-13` | 1,0 | *"rentable [...] terrain deja eprouve par `cp-semaine`"* |
| `S41-SCI-CLASSER` | `SCI-CM1-06` | 1,5 | *"les suivantes"* (deuxieme rang de surete) ; continuite directe avec `QLM-CP-01` et `QLM-CP-02`, deja `couvert-valide`, sur le meme objet (le vivant) - `CYC-1` s'y applique au sens le plus favorable, la meme instance ayant deja l'image des deux paquets fermes |
| **Terrain 2** | | **3,5 / 5,0** | **Trois lots, plafond de `PAR-8` atteint avant le plafond budgetaire** |

`SCI-CM1-03` (trajectoire/vitesse) et `SCI-CM1-14` (environnement numerique, table de taches de
remplacement deja fournie par `nadia-prof`) restent hors de ce terrain par le meme plafond de lots,
pas par risque. `SCI-CM1-15` en reste exclue pour un second motif, cumulatif : le risque qu'elle porte
elle-meme (section 5).

**J'ai ajoute `QLM-CP-04` a ce terrain, une ligne que la section 6 initiale avait ecartee au profit de
la marge plutot que du plafond.** Sous la borne a instance unique (5,0), je gardais `QLM-CP-04`
dehors pour garder de la marge ; sous la borne a deux instances verifiee (8,0), le plafond de trois
lots par instance la rend prenable sans depasser aucun plafond. Rien dans mon choix initial de la
section 6 n'excluait cette ligne pour un motif propre a elle - je le dis pour que ce ne soit pas lu
comme une correction silencieuse d'un jugement de risque.

### 9.5 Le total du sprint, et ce qui reste hors perimetre

> **Terrain 1 : 4,5 / 5,0. Terrain 2 : 3,5 / 5,0. Total du sprint : 8,0 / 8,0 - la borne entiere,
> sans marge au niveau du sprint.**

**Ce que je nomme, plutot que de le laisser decouvrir en review** : ce total atteint exactement la
borne. Au niveau de chaque instance, le terrain 2 conserve 1,5 de marge individuelle (3,5 sur son
plafond de 5,0), mais le terrain 1 n'en conserve que 0,5 (4,5 sur 5,0) - **et ce 0,5 est deja le
maximum atteignable a trois lots, tous a 1,5** : aucune combinaison de trois lignes d'EMC dans ce
vivier ne coute moins que 4,5, le tarif etant uniforme. `PAR-8` (s29) nommait explicitement une
**marge de rejeu de 0,5 par instance, non allouee a aucun lot** au moment de composer un sprint a deux
instances ; **cette composition ne la reserve pas explicitement sur le terrain 1**, faute d'une
combinaison qui le permette a trois lignes. **Je ne tranche pas si c'est acceptable** : c'est un fait
chiffre, route en section 9.7 plutot que resolu par moi.

**Ce qui reste hors perimetre de s41, et pourquoi - six lignes sur les douze du vivier** :

| Ligne | Motif de l'exclusion |
|---|---|
| `EMC-CM1-05`, `-09`, `-10` | Plafond de trois lots par instance atteint sur le terrain 1 par les trois lignes d'EMC CP deja classees par `nadia-prof`. Aucun risque propre nomme. |
| `SCI-CM1-03`, `-14` | Plafond de trois lots par instance atteint sur le terrain 2. Aucun risque propre nomme - `SCI-CM1-14` dispose meme d'un couple de taches de remplacement deja fourni par `nadia-prof`, donc immediatement engageable au prochain sprint sans reinstruction. |
| `SCI-CM1-15` | Meme plafond de lots, **et** risque propre deja nomme par `nadia-prof` elle-meme (*"a ne pas prendre non plus"*), non rouvert par l'instruction recue. |

**Reponse a la question posee** : oui, le chantier depasse encore la capacite d'un seul sprint a deux
instances, meme au perimetre maximal recalcule. **Le plafond qui bloque n'est pas l'argent, c'est le
nombre de lots** : le terrain 2, par exemple, aurait budgetairement de la place pour un quatrieme lot
(3,5 plus 1,5 = 5,0 pile), mais `PAR-8` fixe *"au plus trois lots par instance"* comme une regle
distincte du budget, jamais revoquee depuis le 2026-09-05. Je ne l'ai pas contournee.

### 9.6 Le perimetre retenu, en remplacement de la section 6

**Six lots, sur deux instances, deux terrains** :

| Terrain | Instance | Lots | Cout |
|---|---|---|---|
| 1 - EMC (CP + CM1) | A | `S41-EMC-SYMBOLES` (`EMC-CP-08`), `S41-EMC-DROITS` (`EMC-CP-10`), `S41-EMC-ROLES` (`EMC-CP-13`) | 4,5 / 5,0 |
| 2 - Sciences CM1 + questionner le monde CP | B | `S41-QLM-HYGIENE` (`QLM-CP-04`), `S41-QLM-SAISONS` (`QLM-CP-13`), `S41-SCI-CLASSER` (`SCI-CM1-06`) | 3,5 / 5,0 |
| **Total** | | **6 lots** | **8,0 / 8,0** |

Ce tableau remplace celui de la section 6 comme perimetre retenu pour s41. La section 6 n'est pas
effacee : elle reste la trace du raisonnement a instance unique, et `S41-EMC-DROITS` et
`S41-QLM-SAISONS` y figuraient deja, inchanges.

### 9.7 Ce que cet amendement route, en plus de la section 7

- **A `sofia-sm` et a l'utilisatrice, non bloquant mais a peser avant l'ouverture du planning** : le
  terrain 1 (EMC) est compose sans marge de rejeu individuelle (section 9.5). Deux issues nommees,
  aucune tranchee ici : accepter le risque tel quel - un refus sur l'un des trois lots d'EMC ferait
  depasser 5,0 sur cette instance sans reallocation ecrite (`PAR-9`, clause de transfert) -, ou retirer
  une des trois lignes d'EMC pour retrouver une marge, au prix d'une ligne de moins fermee cette fois.
- **A `nadia-prof`, via `sofia-sm`, non bloquant** : le +1,0 qu'elle a explicitement refuse de
  s'attribuer elle-meme (section 9.2, point 3) reste disponible si elle change d'avis - je ne le
  presume pas et je ne le lui redemande pas, je note seulement qu'elle a ecrit que la decision
  appartenait a `camille-po` et a l'utilisatrice, pas a elle.
- **Le routage de la section 7 sur la discipline de calcul de cout interrompue depuis `DEC-0091` reste
  valable et n'est pas repris ici.**
