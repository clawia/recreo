# DEC-0097 - Proposition de perimetre pour s42 et s43

**Statut : perimetre chiffre accepte le 2026-09-24** (les six lignes et leurs tarifs estimes des
sections 5 et 6, reprises telles quelles par `DEC-0098`). **Le dispositif a deux sprints separes a
instance unique que ce document proposait est remplace par `DEC-0098`**, qui fusionne s42 et s43 en
un seul sprint a deux instances paralleles de `nadia-prof`, sur instruction directe de
l'utilisatrice. Ce document reste la source du chiffrage ligne par ligne ; il n'ouvre toujours
aucun dossier de sprint, n'ecrit toujours aucune story dans `docs/backlog/backlog.md` et ne fait
toujours passer aucune ligne de `squad/programme/MATRICE-couverture.md` a `en cours` - ces gestes
restent a `sofia-sm`, route par `DEC-0098`.

## 0. Commande et methode

Demande de l'utilisatrice a la cloture de s41 (94/155 = 60,6 %) : proposer un perimetre pour les
deux prochains sprints, s42 et s43, qui maximise la couverture programme.

Methode suivie :

1. Lecture integrale de `squad/programme/MATRICE-couverture.md` (3 693 lignes) et releve de toutes
   les lignes `non couvert` par recherche exacte des cellules de statut (`grep -n "| non couvert"`,
   59 lignes de competence, hors lignes d'en-tete des tableaux de synthese).
2. Exclusion des lignes hors perimetre de `DEC-0089` (CE1 mathematiques seule ouverte, CM2 differe).
   **Aucune ligne trouvee a exclure sur ce motif** : la matrice ne porte aujourd'hui aucune ligne
   CM2, et le seul niveau CE1 present est mathematiques - le referentiel CE1 lui-meme ne couvre que
   cette matiere (`DEC-0089`, section 3, routage explicite a `nadia-prof`).
3. Exclusion des neuf lignes bloquees par `DEC-0075` (droits d'usage de reproductions d'oeuvres non
   tranches).
4. Exclusion des deux lignes ecartees par `DEC-0093` section 6 (`EMC-CM1-06`, `SCI-CM1-18`).
5. Recherche systematique, pour chaque ligne restante, d'un arbitrage pedagogique prealable ecrit -
   soit dans un fichier `squad/conformite/ARBITRAGE-PREALABLE-*.md`, soit dans le tableau "Jugement
   de faisabilite" que `nadia-prof` a integre a dix des dix-neuf referentiels le 2026-09-02/03, soit
   dans une decision produit anterieure qui cite l'arbitrage. Une ligne sans cette trace est classee
   candidate de second rang, hors du perimetre chiffre.
6. Verification de la borne d'audit courante avant de rien chiffrer : voir section 1.

## 1. Borne d'audit verifiee

`DEC-0095` a arbitre un dispositif a **deux instances** de `nadia-prof`, borne 8,0, **nommement
pour s41**. Aucune reconduction de ce dispositif n'est ecrite pour s42 ni pour s43. La regle par
defaut est donc l'**instance unique**, et sa borne est **5,0** par sprint
(`squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md`,
section 4.1) - **inchangee depuis s22**, confirmee a s31 et a s32 sans avoir jamais ete revisee a la
baisse ni a la hausse pour l'instance unique elle-meme.

**Lecon de s41 explicitement reprise** : le perimetre initial de s41 (2,5/5,0) portait une marge
confortable, mais son extension au dispositif a deux instances a porte le sprint a 8,0/8,0 sans
marge de rejeu sur un des deux terrains, et le sprint a coute trois refus pedagogiques leves dans
la journee (deux sur le meme lot) avant de clore a 94/155. Les deux perimetres proposes ci-dessous
tiennent volontairement une marge d'environ 1,0 sur 5,0 par sprint, plutot que de remplir la borne
a l'unite pres.

## 2. Methode de chiffrage - tarif estime, pas publie

**Aucun tarif ligne a ligne publie par `nadia-prof` n'existe pour les lignes candidates de s42 et
s43** - a la difference de `EMC-CP-08/-10/-13`, `QLM-CP-04/-13` et `SCI-CM1-06`, tarifees
individuellement dans `DEC-0095` et closes a s41. Le chiffrage ci-dessous est une **estimation de
`camille-po`**, appliquant la regle generale posee par `DEC-0074` et confirmee par `DEC-0077`
section 8.2 : **1,0 pour une matiere dont `nadia-prof` est la source factuelle** (mathematiques,
francais - elle le dit elle-meme dans les referentiels de sciences et d'EMC : *"je ne suis pas la
source en sciences comme je le suis en calcul"*) ; **1,5 pour les autres matieres**, ou l'exactitude
de chaque fait se verifie contre une source externe (`HIS-1` a `HIS-3`, `SCI-1` a `SCI-3`). Ce
n'est pas une facturation rendue par `nadia-prof` : elle est nommee comme estimation, sur le
modele explicite que `DEC-0095` a lui-meme pratique avant d'obtenir un chiffre confirme.

## 3. Lignes exclues

### 3.1 Bloquees par `DEC-0075` (droits d'usage de reproductions d'oeuvres, neuf lignes)

| Ligne | Matiere / niveau |
|---|---|
| `ART-CP-09` Decrire une oeuvre | arts plastiques CP |
| `ART-CP-10` Reconnaitre des oeuvres | arts plastiques CP |
| `ART-CM1-07` Reperer les effets produits par la matiere, la couleur, le geste et le support dans une oeuvre | arts plastiques CM1 |
| `ART-CM1-08` Decrire une oeuvre d'art avec un lexique elementaire | arts plastiques CM1 |
| `HDA-CM1-01` Decrire une oeuvre avec un lexique simple | histoire des arts CM1 |
| `HDA-CM1-02` Degager les caracteristiques techniques et formelles d'une oeuvre | histoire des arts CM1 |
| `HDA-CM1-03` Identifier les materiaux d'une oeuvre | histoire des arts CM1 |
| `HDA-CM1-04` Associer une oeuvre a une epoque et une civilisation | histoire des arts CM1 |
| `HDA-CM1-06` Identifier des reperes du patrimoine et les situer sur une frise | histoire des arts CM1 |

### 3.2 Ecartees par `DEC-0093` section 6

| Ligne | Motif |
|---|---|
| `EMC-CM1-06` Comprendre le sens des principes de la Republique | ecartee, jamais arbitree |
| `SCI-CM1-18` Identifier un effet de l'activite humaine sur l'environnement | ecartee, jamais arbitree |

### 3.3 Refus pedagogique explicite et non leve, trouve independamment de `DEC-0075`/`DEC-0093`

| Ligne | Motif |
|---|---|
| `francais CP` - Decoder un mot regulier inconnu par assemblage syllabique | **REFUSEE** a plusieurs reprises comme proxy ecrit (famille audio, emission seule ; `DEC-0068` : *"la ligne refusee reste non couvert et n'est pas reclassee"*). Ce n'est pas une absence d'arbitrage, c'est un refus tenu depuis s19 sur un motif qui n'a jamais bouge. Non candidate, sauf forme neuve que personne n'a proposee a ce jour |

### 3.4 Caution explicite de `nadia-prof`, hors toute `DEC`

| Ligne | Motif |
|---|---|
| `SCI-CM1-15` Situer la Terre dans le systeme solaire, saisons | Techniquement jouable et arbitree, mais `nadia-prof` l'a nommee elle-meme *"la plus risquee des candidates de sciences (a ne pas prendre non plus)"* (`ARBITRAGE-PREALABLE-s31`, section 4.3, citee par `DEC-0095`). Ecartee du perimetre chiffre par prudence, pas retiree du vivier |

## 4. Candidates de second rang - sans arbitrage pedagogique prealable ecrit

**Treize lignes de mathematiques CE1** (`MAT-CE1-01`, `-02`, `-04`, `-05`, `-06`, `-07`, `-08`,
`-10`, `-11`, `-12`, `-14`, `-15`, `-17`). Verification faite : aucune citation de ces codes dans
`squad/conformite/` ni `squad/decisions/` au-dela de la matrice elle-meme
(`grep -rniE "MAT-CE1-(01|02|04|05|06|07|08|10|11|12|14|15|17)\b"`, aucun resultat). Le referentiel
CE1 ne porte pas de tableau "Jugement de faisabilite" ligne a ligne, contrairement aux dix
referentiels ecrits le 2026-09-02/03. L'historique du niveau (s37, s38, s39, `MAT-CE1-03`,
`MAT-CE1-09`, `MAT-CE1-13`, `MAT-CE1-16`) montre que chaque competence CE1 a recu son propre
arbitrage **au moment de l'ouverture du sprint qui la traitait** (`ARBITRAGE-s37-...`,
`ARBITRAGE-s38-...`, `ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md`), jamais avant.
Engager l'une de ces treize lignes dans un sprint suppose donc un geste que ces sprints-la ont
tous fait en premier : demander a `nadia-prof` un arbitrage neuf, avant tout avis de faisabilite de
`theo-archi`. Ce n'est pas un obstacle pedagogique, c'est un delai que le perimetre chiffre de s42
et s43 ne prend pas.

**Une ligne conditionnelle, distincte d'un simple manque d'arbitrage** : `LVE-CM1-05` (Lire et
comprendre un texte court et simple, anglais CM1) est jouable au choix et arbitree
(`ARBITRAGE-PREALABLE-s27`, section 7.3), mais `nadia-prof` a pose le 2026-09-04 une condition
non remplie : un **second paquet lexical**, au-dela de celui deja `couvert-valide` de
`LVE-CM1-04`, doit exister avant que cette ligne s'ouvre (matrice, ligne 1650 : *"refusee d'avance
pour s28, non instruite pour s29"*). Non instruite depuis. Hors du perimetre chiffre tant que ce
prealable n'est pas leve.

## 5. Perimetre propose pour s42

**Cible : 4,0/5,0, marge 1,0.**

| Ligne | Matiere / niveau | Tarif (estimation) | Source de l'arbitrage prealable |
|---|---|---|---|
| `francais CM1` - Lire silencieusement un texte de longueur croissante et en restituer l'essentiel | francais CM1 | 1,0 | `ARBITRAGE-PREALABLE-s25-les-seize-lignes-instruites-la-famille-visuelle-et-ma-borne.md`, section 9.1 : **ACCEPTE SOUS CONDITIONS `LIS-1` a `LIS-10`, couverture partielle nommee**. Prealable technique deja pose par `nadia-prof` elle-meme : une mesure de pli par `ines-design` avant l'ecriture du paquet (meme section, ligne 837) |
| `HG-CM1-05` Nommer et situer dans le temps quelques rois qui ont marque la construction du royaume | histoire-geographie CM1 | 1,5 | `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, section 7.2, ligne 5 : **oui, choix**. Laissee de cote par le premier paquet `cm1-histoire-geographie.yaml` (`DEC-0077`, section 9) - extension du paquet existant, pas une forme neuve |
| `QLM-CP-06` Identifier de quelle matiere un objet est fait et trier des materiaux selon une propriete simple | questionner le monde CP | 1,5 | `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, section 7.1, ligne 6 : **oui, choix ; plus juste en illustre**. Vigilance ecrite d'avance : le nom de l'objet dans l'enonce donne souvent la matiere, distracteurs a surveiller |

**Total estime : 4,0/5,0.** Trois matieres (francais, histoire-geographie, questionner le monde),
deux niveaux (CM1 x2, CP x1). Si les trois lignes ferment integralement, la matrice passe de
94/155 (60,6 %) a 97/155 (62,6 %).

## 6. Perimetre propose pour s43

**Cible : 4,0/5,0, marge 1,0.**

| Ligne | Matiere / niveau | Tarif (estimation) | Source de l'arbitrage prealable |
|---|---|---|---|
| `ART-CP-05` Reconnaitre et nommer les couleurs, distinguer les couleurs primaires de celles obtenues par melange | arts plastiques CP | 1,5 | `squad/programme/arts-plastiques/competences-cp.md`, Jugement de faisabilite (`nadia-prof`, 2026-09-03), ligne 5 : **jouable au choix textuel, sous `ART-1`** |
| `EMC-CM1-05` Reconnaitre les symboles de la Republique francaise et dire ce qu'ils representent | enseignement moral et civique CM1 | 1,5 | `squad/programme/enseignement-moral-et-civique/competences-cm1.md`, Jugement de faisabilite (`nadia-prof`, 2026-09-03), ligne 5 : **jouable au choix textuel, sous `ART-1`**. Terrain sur, deja identifie dans `DEC-0095` parmi les six lignes hors perimetre du maximum recalcule de s41 par plafond de lots, pas par risque |
| `MAT-CP-10` (moitie textuelle seulement) - Se reperer et decrire des deplacements sur un quadrillage simple | mathematiques CP | 1,0 | `ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md`, section 17.7. **Ne ferme que la moitie *decrire un deplacement***, en choix textuel ; la moitie *se reperer et se deplacer* reste `non couvert` (designation sur quadrillage, palier 2, non instruite ici). La ligne passe de `non couvert` a `en cours` avec la mention de couverture partielle deja ecrite par `nadia-prof`, elle ne passe pas a `couvert-valide` par ce seul geste |

**Total estime : 4,0/5,0.** Trois matieres (arts plastiques, enseignement moral et civique,
mathematiques), deux niveaux (CP x2, CM1 x1). Si les deux premieres lignes ferment integralement
et que la troisieme passe a `en cours`, la matrice passe de 97/155 (62,6 %, projection de s42) a
99/155 (63,9 %).

## 7. Vivier - candidates pretes, non retenues dans le perimetre chiffre

Arbitrage pedagogique prealable deja ecrit pour chacune, disponibles pour s44 et au-dela sans delai
d'arbitrage neuf :

| Ligne | Matiere / niveau | Note |
|---|---|---|
| `QLM-CP-08` Reconnaitre les objets numeriques du quotidien | questionner le monde CP | jouable, sans reserve particuliere |
| `QLM-CP-10` Utiliser le vocabulaire de position | questionner le monde CP | jouable, `CON-2` (l'enonce ne decrit pas la position) |
| `QLM-CP-15` Comparer des modes de vie d'autrefois et d'aujourd'hui | questionner le monde CP | jouable, `BIA-3` |
| `QLM-CP-16` Comparer des modes de vie ici et ailleurs | questionner le monde CP | jouable mais **"la ligne la plus dangereuse du lot"** (`BIA-1`, `BIA-2`) - a instruire avec soin superieur au reste du vivier |
| `QLM-CP-12` Situer la France et quelques espaces sur une carte ou un globe | questionner le monde CP | arbitree jouable, mais requiert le palier 2 **et** une illustration neuve (carte, globe) que le depot n'a jamais dessinee - avis de faisabilite de `theo-archi` a obtenir avant tout chiffrage |
| `HG-CM1-09` Se reperer sur une carte de France et un planisphere | histoire-geographie CM1 | meme motif que `QLM-CP-12` |
| `HG-CM1-10` Situer son lieu de vie a differentes echelles | histoire-geographie CM1 | meme motif, plus le lieu de vie de l'enfant non connu du produit (`DEC-0028`) |
| `MUS-CP-05` Identifier des instruments et leurs familles (connaissance seule) | education musicale CP | jouable, mention de couverture partielle exigee d'avance |
| `MUS-CP-11` Nommer des reperes du vocabulaire musical | education musicale CP | jouable, `MUS-1` |
| `EPS-CM1-09` Tenir les roles d'arbitre, d'observateur, de marqueur (connaissance seule) | EPS CM1 | jouable, `EPS-1`, mention de couverture partielle exigee d'avance |
| `EPS-CM1-10` Effets de l'activite physique et regles de securite | EPS CM1 | jouable, `EPS-2`, `BIA-4` |
| `EMC-CP-04` Reconnaitre que les personnes sont differentes | EMC CP | jouable sous `EMC-4` **mais non retenue a s31** (`DEC-0081`, section 7) : `EMC-4` n'autorise qu'une seule forme de tache, ce qui a deja epuise `FER-3` avant qu'un item ne soit ecrit. A traiter avec la meme prudence si reprise |
| `EMC-CP-14` Connaitre les gestes d'alerte | EMC CP | jouable, `EMC-7`, piege d'exactitude connu (18 vs 112) deja documente |
| `EMC-CM1-01` Identifier une emotion, distinguer ressentir et agir | EMC CM1 | jouable, risque de reponses doublement defendables |
| `EMC-CM1-02` Reconnaitre une atteinte a autrui | EMC CM1 | jouable mais **"la plus delicate du lot"** avec la ligne 6 (exclue) |
| `EMC-CM1-03` Ce qu'interdit le respect des differences | EMC CM1 | meme risque `EMC-4` que `EMC-CP-04` ci-dessus |
| `EMC-CM1-09` Role des institutions proches | EMC CM1 | jouable, `EMC-6` |
| `EMC-CM1-10` Sens du vote et de la representation | EMC CM1 | jouable, connaissance seule |
| `HDA-CM1-09` Se reperer dans un musee, un lieu d'art ou un site patrimonial | histoire des arts CM1 | arbitree jouable (palier 2, **pas** bloquee par `DEC-0075` - aucune reproduction d'oeuvre requise), mais illustration neuve (plan de musee) a instruire par `theo-archi` |
| `LVE-CP-06` Decouvrir quelques aspects culturels des pays de la langue etudiee | langues vivantes CP | jouable en francais seul, mention exigee d'avance |
| `LVE-CM1-06` Ecrire des mots et expressions memorises | langues vivantes CM1 | jouable par saisie, `ANG-S1`/`ANG-S2`, s'appuie sur le lexique de `LVE-CM1-04` deja `couvert-valide` |
| `SCI-CM1-03` Decrire le mouvement d'un objet par sa trajectoire et sa vitesse | sciences CM1 | jouable, description seule |
| `SCI-CM1-10` Relier alimentation, activite physique, sommeil et hygiene au corps | sciences CM1 | jouable, `BIA-4` |
| `SCI-CM1-13` Reperer l'evolution d'un objet technique | sciences CM1 | jouable, `BIA-3` |
| `SCI-CM1-14` Regles d'usage d'un environnement numerique | sciences CM1 | jouable, connaissances seules |
| `SCI-CM1-16` Phenomenes geologiques et meteorologiques, risques | sciences CM1 | jouable, `BIA-2` |

## 8. Observation hors perimetre - gap de matrice trouve en verifiant

`squad/programme/mathematiques/competences-ce1.md` porte **dix-huit** lignes (`MAT-CE1-01` a
`MAT-CE1-17`, plus `MAT-CE1-18` ajoutee le 2026-09-21 sur signalement de `nadia-prof` elle-meme).
`squad/programme/MATRICE-couverture.md`, section "Mathematiques - CE1", n'en porte que **dix-sept**
(`grep -n "MAT-CE1-18" squad/programme/MATRICE-couverture.md` ne retourne rien) : `MAT-CE1-18`
(sens de la division) n'a jamais ete integree a la matrice. Ce n'est pas une ligne candidate de ce
document - c'est un ecart de tenue de la matrice elle-meme, a corriger independamment du perimetre
de s42/s43, par l'ajout d'une ligne `non couvert` au tableau CE1.

## 9. Ce que ce document ne fait pas

Ni `docs/backlog/backlog.md`, ni `squad/sprints/`, ni `squad/programme/MATRICE-couverture.md` ne
sont modifies par ce document. Aucun perimetre n'est engage. Si l'utilisatrice valide tout ou
partie de ce qui precede, l'ecriture des stories et l'ouverture des dossiers de sprint reviennent
a `sofia-sm`.
