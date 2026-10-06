# DEC-0110 - Ouverture de s51 : trois instances de `nadia-prof`, une matiere et un niveau chacune
# (francais CE1, sciences et technologie CM1, mathematiques CE1) ; le vivier de mathematiques CE1
# n'etait pas epuise, et l'instance C est engagee sous une condition suspensive ecrite

- **Type** : decision d'ouverture de sprint (objectif, perimetre, chiffrage, instances, routage).
- **Date** : 2026-10-05
- **Auteur** : `camille-po`
- **Sprint** : s51
- **Competences programme visees** : `FRA-CE1-22`, `FRA-CE1-20`, `FRA-CE1-23`
  (`squad/programme/francais/competences-ce1.md`) ; `SCI-CM1-10`, `SCI-CM1-15`, `SCI-CM1-18`
  (`squad/programme/sciences-et-technologie/competences-cm1.md`) ; `MAT-CE1-17`, `MAT-CE1-06`,
  `MAT-CE1-02` (`squad/programme/mathematiques/competences-ce1.md`).
- **Statut** : Acceptee. **Amendee le 2026-10-05 (section 14)** : `nadia-prof` a refuse la borne a
  trois instances ; l'instance C est *non engagee*, report a s52. s51 ouvre a deux instances, A et B,
  8,0. Les sections 2, 5, 7, 8, 9 et 11 sont conservees telles qu'ecrites et se lisent avec la
  section 14, qui fait foi en cas d'ecart. **Amendee le 2026-10-06** : section 15 (verdicts de
  l'instance B), puis section 16 (cloture de s51, etat final, fait foi sur la section 15).
- **Lien** : `DEC-0108` (sections 3, 4, 6 ; section 6 corrigee ici) ; `DEC-0109` ;
  `DEC-0107` (section 4) ; `DEC-0104` (section 2.1) ; `DEC-0102` (section 2) ; `DEC-0099`
  (section 9.2) ; `DEC-0098` (section 3) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`
  (section 13 : `PAR-1`, `PAR-2`, `PAR-7`, `PAR-8`, `PAR-10`) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md`
  (`PAR-13`) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s46-trois-instances-refusees-le-vivier-borne-avant-la-capacite.md`
  (sections 4 et 5) ; `squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md` ;
  `squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md` ;
  `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` et `...-lot-3.md` ;
  `squad/conformite/ARBITRAGE-PREALABLE-s51-borne-a-trois-instances.md` (section 14) ;
  `squad/README.md` (section 2 bis ; section 4, `PAR-16`).

---

## 0. Provenance et methode

**Instruction recue, citee mot pour mot** (forme 3, `squad/README.md` section 2 bis) : *recue de
MASTER, sans source dans le depot*, le 2026-10-05 : *"plannifie le sprint suivant avec un maximum
d'instances et une couverture maximale"*. Puis, sur trois propositions que j'avais numerotees `O1`,
`O2`, `O3` dans l'echange (leur texte n'est pas repris dans le depot que j'ai lu pour cette decision) :
*"option 2"*.

**Ce que cet enonce ne fonde pas** (premiere borne de la forme 3 : un enonce recu de MASTER ne vaut
jamais motif). Il ne fonde ni le nombre d'instances, ni une ligne, ni un tarif. **La composition de
`O2` est donc reecrite en entier dans les sections 2 a 6 de ce document, avec ses motifs, et c'est
cette ecriture qui fait foi, pas l'enonce.** Le nombre d'instances est justifie en section 7 contre
les conditions que `nadia-prof` a ecrites le 2026-10-01.

**Methode des comptes.** Arbre `round-two` a `f68458de`. Les comptes de statuts viennent d'un `awk`
sur les tableaux de `squad/programme/MATRICE-couverture.md` (une ligne de donnees = `| matiere |
niveau | domaine |...`, statut lu dans la septieme cellule) : 123 `couvert-valide`, 3 `en cours`,
56 `non couvert`, 76 `non couvrable en l'etat`, soit 258 lignes et un denominateur atteignable de
182 ; 123/182 = 67,6 %. Ces chiffres recoupent `squad/sprints/s50-francais-ce1-sourcing-bloque-par-cloudflare/RAPPORT.md`
(section "Couverture du programme" et phrase de synthese : 120/182 a 123/182). Les corrections de
fait sont signalees au passage ; aucune n'est reprise de confiance.

---

## 1. Point tranche en premier : le vivier de mathematiques CE1 n'etait pas epuise

### 1.1 L'affirmation, et ce que le depot dit

`DEC-0108` section 6 ecrit : *"aucune ligne neuve de mathematiques CE1 n'est disponible pour
l'etoffer, le vivier etant instruit epuise depuis s48 (`DEC-0106`, section 4 ; planning de s50,
section 4, reverifie sans changement)"*. Le planning de s49 (section 3.1) et `DEC-0107` (section 4)
disent la meme chose.

Trois constats, chacun verifie au disque :

1. **La reference ne tient pas.** `DEC-0106` section 4 traite l'instance B d'instruction du francais
   CE1 et la borne de longueur du texte support (`grep -n "^## " DEC-0106*.md`, sections 1 a 6). Elle
   ne dit rien d'un epuisement. C'est une reference qui pointe vers rien, ecrite par moi.
2. **La phrase du planning de s49 est contredite par la matrice.** Il ecrit que chaque ligne de
   `MAT-CE1-01` a `MAT-CE1-18` hors `MAT-CE1-09` est `couvert-valide`. Le tableau "Mathematiques - CE1"
   de `MATRICE-couverture.md` porte **huit lignes `non couvert`** (`MAT-CE1-01`, `-02`, `-06`, `-10`,
   `-11`, `-14`, `-15`, `-17`) et une `en cours` (`-09`) : 9 `couvert-valide`, 1 `en cours`, 8 `non
   couvert`, soit les 18 lignes du referentiel (meme `awk` que la section 0, filtre
   mathematiques/CE1).
3. **Ce qui est epuise, c'est autre chose.** Les lignes fermables par un paquet **entierement
   textuel** : `MAT-CE1-03`, `-04`, `-05`, `-07`, `-08`, `-12`, `-18` sont toutes `couvert-valide`.
   `DEC-0099` section 9.2 les nommait "groupe A". L'epuisement du groupe A a ete lu comme celui du
   vivier.

### 1.2 Origine de l'ecart, ecrite pour qu'il ne se reproduise pas

- `DEC-0099` section 9.2 classe `MAT-CE1-17` en groupe B (*"couvert-valide atteignable, image
  requise (mecanique existante, materiau a produire)"*) mais **`MAT-CE1-02` et `MAT-CE1-06` en groupe C
  (*"plafonnees `en cours`"*)**. `nadia-prof`, `ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`
  section 1 (table des verdicts), ecrit pour `MAT-CE1-02` : *"`couvert-valide` atteignable **si**
  une ligne graduee reellement dessinee est servie en designation"*, et pour `MAT-CE1-06` :
  *"`couvert-valide` atteignable avec les dessins de `MUL-CE1-5`"*. **Sur l'atteignabilite
  pedagogique, son arbitrage fait foi (forme 1) ; mon classement de `DEC-0099` etait plus pessimiste
  que lui.**
- `DEC-0104` section 2.1 lit `MAT-CE1-17` comme *"Refuse par texte seul"* et *"non instruit pour
  s47"*. `ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md` section 3 rend **ACCEPTE SOUS
  CONDITIONS, `SYM-CE1-1` a `SYM-CE1-3`**. "Refuse par texte seul" est la condition `SYM-CE1-1`
  (l'axe se reconnait sur un dessin), pas un verdict de refus de la ligne.
- `squad/sprints/s43-trois-lignes-qlm-eps-et-duree-ce1/review.md` (ligne "Le materiau visuel de
  `MAT-CE1-17`") le porte comme *"Ouvert, non tranche"*, route a `theo-archi`. **Aucun avis de
  faisabilite ne le traite** : `grep -rl "MAT-CE1-17\|MAT-CE1-06\|MAT-CE1-02" squad/rapports` ne
  rend aucun fichier, et les neuf avis de `squad/rapports/` (s44 a s50) sont tous sur d'autres
  lignes.

### 1.3 Les conditions d'atteignabilite sont-elles satisfaites par l'existant ?

| Ligne | Verdict de `nadia-prof` (source) | Ce qu'il faut | Ce que l'existant fournit | Ce qui manque |
|---|---|---|---|---|
| `MAT-CE1-17` | ACCEPTE SOUS CONDITIONS `SYM-CE1-1` a `-3`, `couvert-valide` atteignable avec mention (lot-3, section 3) | designation sur un dessin d'axe et de quadrillage | mecanique `designation` en production : `domain/.../content/ZoneADesigner.java` (illustration et texte alternatif obligatoires, `bonneZone`), `domain/.../mechanics/designation/DesignationSession.java`, deux entrees `designation` dans `adapters/adapter-content/src/main/resources/catalogue.yaml` (`designation-frise-de-la-journee`, `designation-plan-de-la-classe`), les deux lignes `QLM-CP-14` et `QLM-CP-11` `couvert-valide` (matrice, sections QLM CP) | **le materiau** : `ls adapters/adapter-web/src/main/resources/static/illustrations` ne contient aucune figure a axe ni aucun quadrillage (12 figures planes de CP, 2 frises, 1 plan, 12 monnaies, 15 solides) |
| `MAT-CE1-06` | ACCEPTE SOUS CONDITIONS `MUL-CE1-1` a `-7`, `couvert-valide` atteignable avec les dessins de `MUL-CE1-5`, `en cours` sinon (lot-1, section 6) | des dessins de reseaux rectangulaires en item illustre | forme d'item illustre sur `bandit-manchot` : `contenu/ce1-solides.yaml` porte 15 items qui declarent `illustration` et `illustrationTexteAlternatif` (`grep -c "illustration:"` rend 15), `bandit-manchot-solides-ce1` `couvert-valide` a s39 sur 15 dessins | **le materiau** : aucun dessin de reseau de points. **Aucune mecanique neuve** (lot-1, section 6.1 : *"Ce qui manque ici est le materiau, pas la mecanique"*) |
| `MAT-CE1-02` | COUVERTURE PARTIELLE nommee sur texte seul ; `couvert-valide` atteignable si une ligne graduee dessinee est servie en designation (lot-1, sections 1 et 3.1) | un paquet textuel (comparer, ranger, encadrer) **et** un paquet de designation sur une ligne graduee | moitie textuelle : `bandit-manchot` et le precedent `RAN-1` a `RAN-8` (`FICHE-bandit-manchot-ranger-nombres-cp.md`, `couvert-valide`) ; moitie designation : la mecanique, comme ci-dessus | **deux paquets, donc deux lots**, et pour la moitie designation : le materiau (la ligne), plus deux points qu'aucun texte ne tranche (section 1.5) |

**Verdict, dit sans adoucir.** Les conditions de `MAT-CE1-17` et de `MAT-CE1-06` **sont satisfaites
par les mecaniques existantes et par la chaine d'illustration existante** ; ce qui manque est un
materiau (des dessins), du meme genre que les quinze dessins de solides livres a s39. `MAT-CE1-02`
l'est pour sa moitie textuelle seule ; sa moitie designation est constructible sur la mecanique
existante mais ne tient pas dans ce sprint (section 5). Le vivier n'etait pas epuise : il etait
**non chiffre sur son materiau**, ce qui est un autre defaut, et c'est le mien de l'avoir lu
comme un epuisement.

**Une quatrieme ligne de la meme famille, que la tache ne demandait pas et que je nomme pour que le
vivier soit compte entier : `MAT-CE1-14`** (se reperer, se deplacer, coder un deplacement sur un
quadrillage). `ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md` section 1 rend **ACCEPTE SOUS
CONDITIONS, `DEP-CE1-1` a `DEP-CE1-4`** : se reperer et se deplacer par designation sur un quadrillage
dessine, coder du cote reception (choix d'un code parmi trois, texte illustre) ; un paquet textuel
seul plafonne a `en cours`, `couvert-valide` suppose la moitie designation, avec la mention
*"se reperer et se deplacer servis par designation sur quadrillage ; 'coder un deplacement' servi du
cote reception (choix d'un code parmi trois), la production d'un code reste non couverte"*. Deux
paquets, donc deux lots : elle n'entre pas dans les trois lots de l'instance C. `DEC-0102` section 2
et `DEC-0104` section 2.1 la notent *"palier 2 requis"* / *"non instruit pour s47"* ; la designation
existe (ci-dessus). `MAT-CE1-15` reste plafonnee `en cours` (aucune mecanique ne recoit un trace :
lot-3 section 2).

### 1.4 Ce que je ne verifie pas, et que je route

- **Le cout du materiau** (qui dessine, combien de dessins, quel poids, quels cliquets
  `LIllustrationDeContenuResoutEtAucuneNeDortTest` et poids total) : decision de charge et
  d'architecture, a `theo-archi` par `sofia-sm`. Le cout de reference est celui de s39 (15 dessins,
  `squad/sprints/s39-couverture-maximale-six-lignes-candidates/contrainte-ines-solides-ce1.md`).
- **Les contraintes de dessin** (lisibilite a la taille de rendu, trait, couleur jamais
  discriminante) : `ines-design` propose, comme a s39 ; `nadia-prof` juge a l'audit.

### 1.5 Deux points techniques que je nomme sans les trancher

Tires de `ARBITRAGE-PREALABLE-s29-le-palier-2-ce-qu-un-paquet-de-designation-doit-apporter.md`, a
rendre a `theo-archi` dans son avis :

1. **`ZON-13` (deux zones d'un item ne se chevauchent pas) contre des lignes candidates qui se
   croisent.** `SYM-CE1-1` demande des lignes candidates tracees sur la figure, et `SYM-CE1-2` y
   met la diagonale d'un rectangle ; les lignes d'un rectangle se croisent en son centre. La
   non-superposition est derivable de quatre nombres par zone (`ZON-13`) ; comment poser des zones
   sur des lignes qui se croisent est une question de conception, pas de pedagogie.
2. **Le nombre de zones sur une ligne graduee** (`ZON-8` : plancher de trois zones, plafond des
   zones homogenes derive de la cible tactile, *"4 x 4 aujourd'hui"*, et `ORD-CE1-6`). Ce point ne
   bloque pas ce sprint (la moitie designation de `MAT-CE1-02` n'y est pas engagee) ; il est nomme
   ici pour qu'il ne soit pas redecouvert.

### 1.6 Report compte

`MAT-CE1-17` est nommee par `DEC-0099` (s43) comme couvert-valide atteignable, non engagee a s43, a
s44, a s45, a s46 (`DEC-0102` section 2 : onze lignes au vivier, une retenue), a s47 (`DEC-0104`
section 2.1 : lue a tort comme refusee), a s48 (`DEC-0106` : `MAT-CE1-08` seule), a s49 et a s50
(*"epuise"*). **Huit sprints consecutifs, s43 a s50, dont deux sur une affirmation fausse.** La
regle "une seule ligne de mathematiques CE1 par sprint" a ete reconduite cinq fois (s43 a s47,
`DEC-0104` section 2.1, qui la disait deja *"commence a ressembler a une direction non choisie"*) ;
son motif etait la marge, pas le fond. **Elle ne s'applique pas a ce sprint** : trois lignes de
mathematiques CE1 entrent, dans une instance qui ne porte que cette matiere et garde 2,0 de marge
(section 2). Je ne la reconduis pas une sixieme fois.

---

## 2. Perimetre chiffre

**Objectif gele de s51.** Porter a `couvert-valide` jusqu'a huit lignes (`FRA-CE1-22`, `-20`, `-23` ;
`SCI-CM1-10`, `-15`, `-18` ; `MAT-CE1-17`, `MAT-CE1-06`), faire passer `MAT-CE1-02` de `non couvert` a
`en cours` sur sa moitie textuelle, et lever `HOM-CE1-R1`, sans qu'aucune instance de `nadia-prof`
depasse 5,0. Mesure : `MATRICE-couverture.md`, 123/182 au depart, 131/182 au mieux. L'objectif ne
promet aucune fermeture : un Done pedagogique refuse reste un refus.

| Instance | Poste | Tarif | Source du tarif |
|---|---|---|---|
| A (francais CE1) | `FRA-CE1-22` | 1,0 | `DEC-0108` section 3 (`nadia-prof` source du referentiel, `DEC-0103`) |
| A | `FRA-CE1-20` | 1,0 | idem |
| A | `FRA-CE1-23` | 1,0 | idem |
| A | `HOM-CE1-R1` (correction, `bandit-manchot-homophones-ce1`) | 0,5 | `PAR-10`, `DEC-0106` section 2 |
| **Total A** | | **3,5 / 5,0** | marge 1,5 dont rejeu 0,5 ; 3 lots |
| B (sciences et technologie CM1) | `SCI-CM1-10` | 1,5 | `ARBITRAGE-PREALABLE-s30...` section 3.1 : *"1,5 partout sur sciences et technologie CM1, sans exception de ligne"*, applique ligne par ligne par `DEC-0095` (`S41-SCI-CLASSER`) et `DEC-0102` section 6 |
| B | `SCI-CM1-15` | 1,5 | idem |
| B | `SCI-CM1-18` | 1,5 | idem |
| **Total B** | | **4,5 / 5,0** | marge 0,5 = le rejeu de `PAR-8` ; 3 lots |
| C (mathematiques CE1) | `MAT-CE1-17` | 1,0 | maths source, mecanique existante ; precedent `S39-SOLIDES` a 1,0 pour un lot illustre de maths CE1 (`DEC-0095`, tableau de s39) |
| C | `MAT-CE1-06` | 1,0 | idem |
| C | `MAT-CE1-02`, **moitie textuelle seule** | 1,0 | maths source, mecanique existante |
| **Total C** | | **3,0 / 5,0** | marge 2,0 ; 3 lots |
| **Total du sprint** | | **11,0** | A + B = 8,0 ; C = 3,0 |

**Lecture des tarifs.** Je n'applique pas le supplement de 0,5 du lot illustre (`ARBITRAGE-PREALABLE-s28...`
section 4.3, `REC-1`) a `MAT-CE1-17` ni a `MAT-CE1-06` : le precedent le plus proche, `S39-SOLIDES`, a ferme
a 1,0. Je ne decide pas si `nadia-prof` le demandera sur une designation inedite ; **si elle le
demande, l'instance C monte a 4,0 au plus, sous son plafond.** J'applique le 1,5 de sciences ligne
par ligne comme `DEC-0102`, et non la lecture du 2026-09-05 (*"un lot vaut jusqu'a 22 items quand il
est compose de deux paquets"*, 0,75 par ligne), que les sprints suivants n'ont pas utilisee ; elle
liberait 1,5 sur B, je ne m'en sers pas pour elever le plafond individuel.

**`PAR-8` verifie** : chaque instance sous 5,0 ; au plus trois lots par instance (les corrections ne
comptent pas comme lots, meme regle que `DEC-0107` section 4) ; une marge de rejeu de 0,5 nommee par
instance, allouee a aucun lot. **`PAR-1`** : aucun lot partage entre deux instances. **`PAR-2`** :
chaque instance porte un seul couple matiere/niveau (francais CE1, sciences et technologie CM1,
mathematiques CE1).

**Effet de couverture, au mieux et sans promesse.** Si les huit lignes visees a la fermeture ferment
(trois de francais, trois de sciences, `MAT-CE1-17` et `MAT-CE1-06`), la couverture passe de 123/182
a 131/182 (72,0 %). `MAT-CE1-02` passe de `non couvert` a `en cours`, sans toucher au numerateur.

---

## 3. Instance A - francais CE1 : `FRA-CE1-22`, `FRA-CE1-20`, `FRA-CE1-23`

**Vivier, recompte.** Le tableau "Francais - CE1" de la matrice porte 23 lignes `non couvert` ;
`FRA-CE1-03` et `FRA-CE1-21` sont plafonnees `en cours` (`ARBITRAGE-PREALABLE-s48...` sections 3.3
et 5.12). **21 lignes restent donc atteignables a `couvert-valide`**, pas les 22 de l'enonce de la
tache : `FRA-CE1-01`, `-02` et `-16` sont fermees (matrice, dates des 2026-10-03 et 2026-10-04).
Apres ce sprint, 18 resteraient.

**Critere de choix : le moins de conditions transverses neuves, puis le moins de frontieres a tenir.**
Lu dans `ARBITRAGE-PREALABLE-s48...` : section 2 (`TRV-CE1-1` a `-7`), section 7 (table des
frontieres), sections 4 a 6 (conditions de chaque ligne).

| Ligne | `texteSupport`, mot designe ou voix (`TRV-CE1-1`, `CLM-CE1-1`) | Frontieres a tenir (table section 7) | Forme deja servie et fermee |
|---|---|---|---|
| `FRA-CE1-22` | aucun | 1 : `FRA-CE1-02`, fermee a s50 (`GRL-CE1-3`) | choix binaire (`TRV-CE1-2` : `FRA-CE1-02`, `-16`, `-22`), servi par `cm1-homophones` et par les deux lignes de s50 |
| `FRA-CE1-20` | aucun | 0 | trois propositions fixes, aucune forme neuve |
| `FRA-CE1-23` | aucun | 0 | trois listes de trois mots : forme de `cp-ranger-nombres`, `couvert-valide` |
| ecartees : `FRA-CE1-04` a `-07` | `texteSupport` (`LON-CE1-3`, `LON-CE1-4`, texte masque `NAR-6`) | 1 a 2 | conditions jamais eprouvees en contenu reel |
| ecartees : `FRA-CE1-12` | mot designe (`partDesignee`, `ADR-0062`) | 2 | - |
| ecartees : `FRA-CE1-14`, `-17` a `-19` | formes conjuguees exactes, collision `FRA-CM1-06` pour `-14` | 1 a 2 | exactitude de chaque forme et de chaque distracteur |
| ecartees : `FRA-CE1-11` | - | 0 | `TRV-CE1-2` (biais de longueur) y mord *"plus qu'ailleurs"* |
| ecartees : `FRA-CE1-08` | enonce en liste separee par ` / ` | 1 | forme neuve |

Je n'ai pas lu chaque ligne ecartee au meme niveau de detail ; la table dit pourquoi les trois
retenues passent avant, pas que les autres soient mauvaises.

**Risque nomme sur `FRA-CE1-22`.** `FRA-CE1-02` a ete refusee au premier passage a s50 sur le `c`/`ç`
(`RAPPORT.md` s50, table des verdicts : *"`ç` absent, indices de `s` faux"*). `GRL-CE1-1` met `c`/`ç`
dans au moins un quart des items de `FRA-CE1-22`. Le rejeu de 0,5 et la marge de 1,5 de l'instance A
couvrent un refus ; ils ne couvrent pas deux refus sur deux lignes.

**`HOM-CE1-R1`** : story ecrite (`docs/backlog/backlog.md`, section "Backlog - sprints suivants",
*"Story de correction differee - reserve `HOM-CE1-R1`"*), ouverte le 2026-10-04 ; **premier
engagement, pas un report**. Elle ne touche pas la majorite des items du paquet : `PAR-14` ne
s'applique pas, `PAR-10` s'applique.

---

## 4. Instance B - sciences et technologie CM1 : `SCI-CM1-10`, `SCI-CM1-15`, `SCI-CM1-18`

Les trois lignes sont les trois derniers `non couvert` de la matiere (matrice, tableau "Sciences et
technologie - CM1" : 16 `couvert-valide`, 3 `non couvert`, 1 `non couvrable en l'etat`). Verdicts de
`ARBITRAGE-PREALABLE-s30...` section 2.2, lignes 10, 15 et 18 : jouables au choix textuel, sous
`BIA-4`, `SCI-1` + `SCI-4`, et `SCI-2` respectivement. La matrice (cellules de ces trois lignes)
n'exige aucune mention de couverture partielle pour elles.

**Ce qui a change depuis s30, verifie.** `nadia-prof` les rangeait en *"a ne pas prendre au premier
lot"* parce que `SCI-1`, `SCI-2` et `BIA-4` n'avaient *"jamais rencontre de contenu reel"*. Elles en
ont rencontre depuis : `grep -l` sur `squad/conformite/FICHE-*.md` trouve `SCI-1` dans onze fiches de
sciences (dont `mouvement-cm1`, `usages-numeriques-cm1`), `SCI-2` dans six (dont `chaine-alimentaire-cm1`,
`phenomenes-naturels-cm1`, `usages-numeriques-cm1`), `BIA-4` dans douze fiches (dont
`regimes-alimentaires-cm1`) - citees comme conditions opposables, ce qui n'est pas la meme chose
que verifiees sur une ligne equivalente. **`SCI-2` n'a toutefois ete tenue que par extension** (`FICHE...usages-numeriques-cm1.md` :
*"extension confirmee"*), jamais sur sa ligne native, `SCI-CM1-18`.

**Risque nomme : `SCI-CM1-18`, la ligne que `nadia-prof` appelait *"la plus dangereuse du lot"*.** Un
precedent proche coute cher : `SCI-CM1-16` (`BIA-2`) a ferme au **troisieme** passage a s46. Avec une
marge de 0,5, l'instance B tient un rejeu borne ; un deuxieme refus sur une autre ligne irait en
report. Je garde les trois lignes parce que la composition est arretee, et j'ecris ce risque pour
qu'il ne soit pas decouvert au Done.

**Frontieres et `CYC-1` a lire avant d'ecrire** (obligation de lecture du depot, cout nul en
supplement, `ARBITRAGE-PREALABLE-s30...` section 2.6) :

- `SCI-CM1-10` : `cm1-eps-effets-activite` (`EPS-CM1-10`, ferme ; sa fiche tient la frontiere *"sans
  exception"*), `cm1-sciences-nutrition` (`SCI-CM1-09`, ferme ; item 5 a ne pas reprendre),
  `cp-qlm-hygiene`, `cp-qlm-corps-sens`, `cp-eps-corps-effort`. **L'horizon long des effets de l'activite
  est interdit** (`EPS-9`, backlog `S43-EPS-EFFETS-ACTIVITE`).
- `SCI-CM1-15` : `cp-qlm-saisons` (`QLM-CP-13`, ferme). `CYC-1` n'etait pas nommee pour cette ligne en
  s30 (le paquet de saisons CP date de s41) : **nouvelle lecture due**.
- `SCI-CM1-18` : aucun paquet de cycle 2 sur le meme objet identifie par `ls adapters/adapter-content/src/main/resources/contenu`
  (aucun nom portant environnement, dechet, planete ou ecologie) ; a confirmer par `nadia-prof`.

---

## 5. Instance C - mathematiques CE1 : `MAT-CE1-17`, `MAT-CE1-06`, `MAT-CE1-02` (moitie textuelle)

**Le choix de la moitie textuelle de `MAT-CE1-02`, et ce que j'ecarte.** Dans la pratique des `DEC` d'ouverture depuis
s42, un paquet audite est un lot, une ligne un tarif (`DEC-0102` section 6) ; les corrections ne
sont pas des lots (`DEC-0107` section 4). `MAT-CE1-02` demande **deux paquets** (textuel, puis designation
sur ligne graduee) : deux lots. Avec `MAT-CE1-17` et `MAT-CE1-06`, cela fait **quatre lots, au-dela du
plafond de trois de `PAR-8`**, qui est une clause de `nadia-prof` et que je n'eleve pas. J'ai pese
trois compositions a trois lots :

| Composition | Fermetures possibles | Lecture |
|---|---|---|
| `MAT-CE1-17` + `MAT-CE1-02` (deux paquets) | 2 (`-17`, `-02`) | deux paquets de designation inedits au CE1 dans la meme instance ; si la mecanique est refusee, **les deux** tombent |
| `MAT-CE1-17` + `MAT-CE1-06` + `MAT-CE1-02` (texte seul) | 2 (`-17`, `-06`) | un seul paquet de designation, un item illustre dont le precedent est ferme, un paquet textuel de precedent ferme (`RAN-*`) |
| `MAT-CE1-06` + `MAT-CE1-02` (deux paquets) | 2 (`-06`, `-02`) | retire `MAT-CE1-17`, dont la mecanique est eprouvee (`QLM-CP-11`, `QLM-CP-14`), au profit d'un second paquet de designation dont les deux points de la section 1.5 sont ouverts |

**Je retiens la deuxieme** : meme nombre de fermetures au mieux, risque reparti sur trois formes
differentes, et `MAT-CE1-02` passe a `en cours` avec la mention que `nadia-prof` a fixee d'avance
(`ORD-CE1-5`, section 10). **La moitie designation de `MAT-CE1-02` est reportee a s52 : premier
report, s51** ; motifs ecrits : le plafond de trois lots, et les deux points de la section 1.5 qui
n'ont pas d'avis de faisabilite.

**Terrain neuf (`PAR-13`).** C est la seule instance de ce sprint sur un terrain dont les
conditions n'ont jamais rencontre de contenu reel : `SYM-CE1-1` a `-3`, `MUL-CE1-4` et `MUL-CE1-5`
(deux conditions sur ce qu'un dessin montre, que `nadia-prof` tient seule : lot-1 section 7, points
4 et 6), et la designation au CE1. Les conditions de forme (`LON-CE1-1`, `LON-CE1-2`, `FER-1` a
`FER-3`) ont, elles, rencontre du contenu reel sur dix lignes fermees.

**Ordre de dependance.** Avant toute ecriture de contenu de C : avis de faisabilite de `theo-archi`
(materiau, cliquets, points de la section 1.5), contraintes de dessin d'`ines-design`. Ce sont des
postes de lecture et de conception, hors tarif de la borne d'audit ; ils sont routes en section 11.

---

## 6. Perimetre explicitement exclu de s51

Apres s51, au mieux, il reste **47 lignes `non couvert`** (56 au total, section 0, moins les 9
engagees). Decomposition par `awk` sur la matrice (meme methode que la section 0) : francais CE1 20,
mathematiques CE1 5, histoire des arts CM1 6, arts plastiques CM1 2, arts plastiques CP 1,
questionner le monde CP 3, histoire-geographie CM1 2, enseignement moral et civique 4, anglais CM1
2, francais CM1 1, francais CP 1 ; 20 + 5 + 6 + 2 + 1 + 3 + 2 + 4 + 2 + 1 + 1 = 47.

| Ce qui reste dehors | Lignes | Motif |
|---|---|---|
| `ART-CP-10` | 1 | constructible, jamais `non couvrable en l'etat` : exige la vue simultanee de deux oeuvres. **Un `ADR` de `theo-archi` seulement, hors capacite de ce sprint** ; aucune story de contenu |
| QLM CP et HG CM1, "palier 2" | 4 : `QLM-CP-12` et 2 HG CM1 (*"palier 2 requis"*, cellules de la matrice) ; `QLM-CP-10`, dont la cellule de statut ne porte aucun motif | **Ecart a signaler, non traite ici** : la designation existe depuis s36 (section 1.3). Si la limite reelle est un materiau (cartes) ou `DEC-0028` (lieu de vie de l'enfant), la matrice doit le dire. Route a `sofia-sm` |
| `HDA-CM1-*` et `ART-CM1-*` | 8 : 7 notees *"image d'oeuvre requise"* (5 HDA, 2 ART), 1 HDA "palier 2" (se reperer dans un musee) | en attente de `T7` d'`ADR-0082` (restauration des reproductions retirees par `DEC-0109` section 9) pour les sept ; la huitieme releve du meme ecart "palier 2" que la ligne ci-dessus |
| Francais CE1 | 20 : 18 atteignables a `couvert-valide`, plus `FRA-CE1-03` et `FRA-CE1-21` plafonnees `en cours` | instruites, non engagees : `PAR-2` interdit une seconde instance sur ce couple matiere/niveau |
| Mathematiques CE1 | 5 : `MAT-CE1-01`, `-10`, `-11`, `-15` plafonnees `en cours` (arbitrages s43, lots 1 a 3 ; `DEC-0104` section 2.1 pour `-10` et `-11`, non relus ici) ; `MAT-CE1-14` atteignable a `couvert-valide` sur deux paquets (section 1.3), hors des trois lots de C | hors capacite de C |
| `QLM-CP-16`, `EMC-CP-04`, `EMC-CM1-02`, `EMC-CM1-03` | 4 | *"ecartees pour motif de fond"* (`DEC-0102` section 3, `ARBITRAGE-PREALABLE-s46...` section 2.2), non reouvertes |
| Une ligne d'EMC CM1, anglais CM1 2, francais CM1 1, francais CP 1 | 5 | non reexaminees ici ; motifs des cellules de la matrice, a ne pas lire comme un refus de ma part |

Le tableau compte 1 + 4 + 8 + 20 + 5 + 4 + 5 = 47 ; la ligne du musee est comptee avec
`HDA`/`ART-CM1`, pas avec le "palier 2" de QLM et HG.

---

## 7. Nombre d'instances : trois, le plafond de quatre reste disponible et non atteint

**Plafond.** *"jusqu'a 4 instances de nadia-prof si besoin"* (recu de MASTER le 2026-10-03, cite par
`DEC-0108` section 6) reste un plafond disponible, pas un objectif ; premiere borne de la forme 3.

**Les trois conditions de reouverture que `nadia-prof` a ecrites** (`ARBITRAGE-PREALABLE-s46...`
section 5, cumulatives) et ce que j'en juge :

| Condition | Mon jugement | Preuve |
|---|---|---|
| **(i)** un document de planning de `camille-po`, ecrit avant le sprint, montre une composition a deux instances qui remplit 8,0 (`PAR-8` respecte) **et laisse dehors au moins 4,0 de lots instruits** | **remplie** | Ce document. A (3,5) + B (4,5) = 8,0, chacune sous 5,0, trois lots, rejeu 0,5 nomme. Reste dehors de cette composition a deux : 18 lignes de francais CE1 instruites (`ARBITRAGE-PREALABLE-s48...`, 26/26 acceptees sous conditions) au tarif de 1,0 = 18,0, et les trois lignes de mathematiques CE1 (3,0). Le levier que `nadia-prof` nommait (referentiel de francais CE1 ecrit, lignes annotees) existe depuis `DEC-0103` et s48 |
| **(ii)** le dernier sprint parallele avant la derivation est clos sans aucune occurrence des formes (a), (b) ou (c), **et c'est elle qui le verifie** | **non remplie, et je ne peux pas la remplir** | Le dernier sprint parallele est s50 (deux instances, `DEC-0109`). Sa clause dit que `nadia-prof` le verifie dans son document de derivation. Aucun document de ce type n'existe pour s50. J'ai cherche les mots *couture*, *collision* et *forme (a/b/c)* dans `retro.md`, `review.md` et `RAPPORT.md` de s50 : aucune occurrence. **Une absence de mention n'est pas une verification**, et la clause ne m'en confie pas la charge |
| **(iii)** l'affectation tient `PAR-13` : au plus une instance sur un terrain dont ses conditions n'ont jamais rencontre de contenu reel, et le francais CE1 *"sera ce terrain-la"* | **remplie sur ma lecture, a confirmer par elle** | Le francais CE1 a rencontre du contenu reel : l'appareil transverse (`TRV-CE1-1` a `-7`, `LON-CE1-1`, `-2`, `-5`) a ete verifie sur `FRA-CE1-01` (`DEC-0108` section 3, citant `FICHE-bandit-manchot-graphemes-complexes-ce1.md`), puis `VLE-CE1-*` et `HOM-CE1-*` sur `FRA-CE1-02` et `-16` a s50 (matrice, dates). Le seul terrain neuf de s51 est C (section 5). B n'en est pas un (section 4), avec la reserve nommee sur `SCI-CM1-18` |

**Consequence : je ne pretends pas que les trois conditions sont reunies.** Deux le sont sur mes
lectures ; la troisieme est un jugement de `nadia-prof` qui n'existe pas. L'instruction du 2026-10-05
ne remplace pas ce jugement (section 0). La section 8 l'organise.

**Pourquoi pas quatre.** Une quatrieme instance exigerait un couple matiere/niveau disjoint portant
un vivier instruit non ecarte (`PAR-2`, `PAR-1`). Il n'y en a pas : le francais CE1 est deja a A ;
`HDA`/`ART-CM1` attendent `T7` ; QLM et HG sont notes *"palier 2"* ; trois lignes d'EMC et `QLM-CP-16` sont ecartees pour motif de fond (`DEC-0102` section 3), l'anglais
CM1 porte des conditions ouvertes (cellules de la matrice) ; francais
CP et CM1 ne comptent chacun qu'une ligne que je n'ai pas instruite. **Condition de reouverture d'un
quatrieme terrain : une ligne de ces couples qui passe a instruite.**

---

## 8. Condition suspensive de l'instance C

**Enonce.** L'instance C ouvre, et son travail d'ecriture de contenu commence, **quand `nadia-prof` a
ecrit, avant la cloture du planning de s51, un document neuf** (ou elle choisit le nom) qui :

1. verifie s50 contre les formes (a), (b) et (c) et dit s'il y a eu une occurrence (condition (ii)) ;
2. confirme ou infirme ma lecture de la condition (iii) ;
3. **derive la borne a trois instances sur les mesures de ce jour**, et dit si 11,0 y tient. Elle ne
   reprend pas 12,0 d'avance (`ARBITRAGE-PREALABLE-s46...` section 5, dernier alinea) ;
4. dit si la passe transverse (`PAR-4`, 0,5) est due pour trois instances, et a laquelle (`PAR-7`).
   **Elle n'est pas engagee ici** : aucun poste, aucun budget alloue, aucune story ;
5. dit si le premier paquet de designation au CE1 doit etre audite par une seule instance.

**Si elle l'ecrit et que 11,0 tient** : C ouvre comme en section 5.

**Si sa borne est comprise entre 8,0 et 11,0** : les postes de C sont retires dans cet ordre, pour
tenir la borne : `MAT-CE1-02` (aucune fermeture), puis `MAT-CE1-17` (terrain le plus neuf), en
gardant `MAT-CE1-06` en dernier (aucune mecanique neuve).

**Si elle refuse, ou si le document n'existe pas a la cloture du planning** : les trois postes de C
sont ecrits *"non engage, budget libere"* (`PAR-16`), montant libere 3,0, destination : report a s52.
A et B sont inchanges : 8,0, le chiffre que `DEC-0098` section 3 a verifie non revise depuis s41 et que
s41 a tenu a l'unite.

**Ce qui peut commencer avant la condition** : l'avis de faisabilite de `theo-archi` et les contraintes
d'`ines-design` sur le materiau (lecture et conception, pas de contenu), parce qu'ils servent aussi
s52 si C ne s'ouvre pas.

---

## 9. Isolation des instances (`PAR-2`, `PAR-13`, `squad/README.md` section 9 et amendements s21, s30, s31)

| Instance | Branche | Worktree (dossier voisin du checkout) | Scratchpad |
|---|---|---|---|
| A | `s51-nadia-instance-a` | `../recreo-s51-nadia-instance-a` | un repertoire temporaire hors depot, propre a A |
| B | `s51-nadia-instance-b` | `../recreo-s51-nadia-instance-b` | un repertoire temporaire hors depot, propre a B |
| C | `s51-nadia-instance-c` | `../recreo-s51-nadia-instance-c` | un repertoire temporaire hors depot, propre a C |

**Trois branches ET trois worktrees dedies**, ouverts par `sofia-sm` avant le premier ecrit de chaque
instance (README section 9 : une branche dediee implique un worktree dedie ; `sofia-sm` ouvre le
worktree de l'agent qu'elle route). **Un scratchpad par instance, jamais partage** (`PAR/s31`,
amendement de la retro de s30 : deux instances de `nadia-prof` partageaient un scratchpad et l'une a
ecrase le fichier de l'autre). **Une instance qui ecrit dans `squad/decisions/REGISTRE-identifiants.md`
le fait depuis son propre worktree**, jamais dans le checkout partage (README, amendement du
2026-09-10). Les identifiants de condition de ce sprint sont deja alloues (section 12) : le risque de
collision de la forme (c) ne porte que sur les reserves que les trois instances ouvriront.

---

## 10. Mentions de couverture exigees si une ligne ferme ou bouge sous plafond

Reprises mot pour mot des arbitrages, pour que la matrice les recopie sans les reformuler.

- **`MAT-CE1-17`**, si elle ferme (`ARBITRAGE-PREALABLE-s43...-lot-3.md` section 3, *"Mention de
  couverture, fixee d'avance"*) : *"reconnaitre un axe de symetrie, et completer une figure par
  symetrie a raison d'une case par item ; completer une figure a plusieurs cases reste non couvert,
  aucune mecanique ne recoit une suite de gestes dans une meme manche"*.
- **`MAT-CE1-02`**, ce sprint, sur paquet textuel (`ORD-CE1-5`) : *"comparer, ranger et encadrer
  seuls ; le verbe 'placer sur une ligne graduee' n'est servi par aucun jeu et ne peut pas l'etre
  par du texte"*. **La ligne reste `en cours`.**
- **`MAT-CE1-06`** : si les dispositions sont dessinees et que `MUL-CE1-4` et `MUL-CE1-5` sont tenues,
  aucune mention n'est due et la ligne peut atteindre `couvert-valide` ; si le paquet livre ne porte
  que de l'addition reiteree (`MUL-CE1-7`) : *"addition reiteree seule ; la disposition en lignes et
  en colonnes n'est servie par aucun jeu et ne peut pas l'etre par du texte"*, et **la ligne reste
  `en cours`**.
- **Francais CE1** (`FRA-CE1-22`, `-20`, `-23`) : plafond `couvert-valide`, aucune mention due
  (`ARBITRAGE-PREALABLE-s48...` sections 5.13, 5.11, 6.1).
- **Sciences CM1** (`SCI-CM1-10`, `-15`, `-18`) : aucune mention due (arbitrage de s30 section 2.4 :
  mentions des seules lignes 6, 14 et 20).

---

## 11. Postes, stories et routage (`PAR-16`)

Un poste n'est engage que s'il porte une story dans `docs/backlog/backlog.md`. Les neuf stories
manquantes sont ecrites dans le meme mouvement, section "Sprint 51", au format existant ;
l'affectation reste *"a affecter par `sofia-sm`"*.

| Poste | Story | Etat |
|---|---|---|
| `FRA-CE1-22` | `S51-FRA-GRAPHIE` | ecrite ce jour |
| `FRA-CE1-20` | `S51-FRA-TEMPS` | ecrite ce jour |
| `FRA-CE1-23` | `S51-FRA-ALPHABETIQUE` | ecrite ce jour |
| `HOM-CE1-R1` | story de correction differee existante | ecrite le 2026-10-04 |
| `SCI-CM1-10` | `S51-SCI-CORPS-CM1` | ecrite ce jour |
| `SCI-CM1-15` | `S51-SCI-TERRE-CM1` | ecrite ce jour |
| `SCI-CM1-18` | `S51-SCI-ACTIVITE-HUMAINE-CM1` | ecrite ce jour |
| `MAT-CE1-17` | `S51-MAT-SYMETRIE` | ecrite ce jour, sous la condition de la section 8 |
| `MAT-CE1-06` | `S51-MAT-MULTIPLICATION` | ecrite ce jour, sous la condition de la section 8 |
| `MAT-CE1-02` (moitie textuelle) | `S51-MAT-COMPARER-RANGER` | ecrite ce jour, sous la condition de la section 8 |
| Avis de faisabilite sur le materiau de C | - | a `theo-archi`, par `sofia-sm` : poste de lecture, pas de story de contenu |
| Contraintes de dessin de C | - | a `ines-design`, par `sofia-sm` |
| Passe transverse (`PAR-4`) | - | **non engage, budget libere : aucun montant n'etait alloue** ; question posee a `nadia-prof` (section 8, point 4) |

**Ce que je route, et a qui.**

- `sofia-sm` : ouverture du dossier de sprint, des trois branches et des trois worktrees,
  affectation nominative de chaque developpeur, controle poste/story de `PAR-16` a la cloture du
  planning, trace de la condition de la section 8, signalement de l'ecart *"palier 2"* de la
  section 6.
- `theo-archi` : avis de faisabilite sur les neuf stories ; sur C, materiau, cliquets d'illustration,
  points de la section 1.5. Il ecrit en parallele un `ADR` sur la vue simultanee d'`ART-CP-10` : ce
  document n'y touche pas.
- `nadia-prof` : le document de la section 8 ; ses arbitrages prealables sont deja ecrits pour les
  neuf lignes, aucun nouveau n'est du.
- `ines-design` : contraintes de dessin des dispositions de points (`MAT-CE1-06`) et des figures a
  axe et grilles (`MAT-CE1-17`).

---

## 12. Identifiants

**Aucun identifiant de condition neuf, aucune reserve neuve.** Verifie dans
`squad/decisions/REGISTRE-identifiants.md` (`grep -c` sur chaque prefixe) : `GRL-CE1`, `TPS-CE1`,
`ALP-CE1`, `ORD-CE1`, `MUL-CE1`, `SYM-CE1`, `HOM-CE1-R1`, `BIA-4`, `SCI-1` a `SCI-4`, `TRV-CE1`,
`LON-CE1`, `CYC-1` y sont alloues. Les codes de story `S51-*` ne sont pas des identifiants de
condition. Les reserves que les audits ouvriront seront allouees par l'instance qui les ouvre, dans
son worktree.

---

## 13. Ce que cette decision ne fait pas

- Elle ne deplace aucun statut de `MATRICE-couverture.md` : aucune ligne ne devient `couvert-valide`
  avant le Done pedagogique de l'instance qui l'audite.
- Elle ne prononce aucun Done, technique ou pedagogique, et ne juge la qualite d'aucun contenu.
- Elle ne derive pas la borne a trois instances : c'est le travail de `nadia-prof`.
- Elle ne rend aucun avis de faisabilite et ne choisit aucun moyen technique pour les dessins.
- Elle n'amende pas `DEC-0108`, `DEC-0107`, `DEC-0104` ni le planning de s49 ; elle corrige leur
  affirmation en section 1 et les laisse lisibles.
- Elle ne reconduit pas la regle d'une ligne de mathematiques CE1 par sprint.

## Decisions prises

1. L'affirmation *"vivier de mathematiques CE1 epuise"* est fausse (huit `non couvert` dans la
   matrice) ; `MAT-CE1-17` et `MAT-CE1-06` sont atteignables a `couvert-valide` sur les mecaniques
   et la chaine d'illustration existantes, le materiau restant a produire ; `MAT-CE1-02` l'est pour
   sa moitie textuelle seule dans ce sprint, et `MAT-CE1-14` sur deux paquets, non engagee. Report
   compte : huit sprints pour `MAT-CE1-17`.
2. s51 ouvre a trois instances de `nadia-prof`, une matiere et un niveau chacune : A (francais CE1,
   3,5/5,0), B (sciences et technologie CM1, 4,5/5,0), C (mathematiques CE1, 3,0/5,0), 11,0 au total.
3. L'instance C est engagee sous la condition suspensive de la section 8 ; A et B ne le sont pas.
4. Le plafond de quatre instances reste disponible, non atteint (section 7).
5. Trois branches, trois worktrees, trois scratchpads dedies (section 9).
6. Neuf stories ecrites, aucun poste sans story (section 11).
7. Exclusions : `ART-CP-10` (`ADR` de `theo-archi` seulement), QLM et HG "palier 2", `HDA`/`ART-CM1`
   (`T7`), et le reste (section 6).

---

## 14. Amendement du 2026-10-05 : `nadia-prof` refuse la borne a trois instances, l'instance C n'est pas engagee

- **Auteur** : `camille-po`.
- **Source du refus** : `nadia-prof`, `squad/conformite/ARBITRAGE-PREALABLE-s51-borne-a-trois-instances.md`
  (forme 1), sections 2.3, 2.4, 3, 4 et 5. Le motif est le sien et n'est pas reformule ici.
- **Ce que cet amendement tranche** : l'issue de la section 8 qui s'applique, et ses consequences sur
  le perimetre. Rien d'autre.

### 14.1 Provenance de l'acceptation du refus

Arbitrage de l'utilisatrice sur la question Q1, **recu de MASTER, sans source dans le depot**, le
2026-10-05 (forme 3, `squad/README.md` section 2 bis) : *"Q1"*, en reponse a la question qui opposait
*"Accepter le refus"* a *"Maintenir C malgre le refus"*. Cet enonce **ne fonde pas le refus** (premiere
borne de la forme 3) : le motif du refus est celui de `nadia-prof`, ci-dessus, et c'est lui qui fait
foi. L'enonce ne dit que ce que la squad fait du refus : elle s'y range.

### 14.2 Issue de la section 8 : la troisieme (refus)

| Point | Constat de `nadia-prof` (arbitrage prealable, sections 2.4, 3 et 4) |
|---|---|
| Condition (ii) | **Non remplie.** s50, dernier sprint parallele, porte une occurrence de la forme (c) : deux instances de `nadia-prof` ont ecrit `jouer.py` dans le meme scratchpad, a 21:31:21 et 21:31:34 (UTC+02:00), sans le savoir. La barre est *"Une seule occurrence de l'une des trois formes suffit"* |
| Condition (i) | Remplie, dans la lecture en lots appliquee depuis s41 |
| Condition (iii) (`PAR-13`) | Remplie : une seule instance sur un terrain neuf, C |

Les trois conditions sont cumulatives : la section 8 est refusee sur la seule condition (ii).
Application de la derniere branche de la section 8 : **les trois postes de C (`MAT-CE1-17`,
`MAT-CE1-06`, `MAT-CE1-02` moitie textuelle) sont *"non engage, budget libere"*** (`PAR-16`), **3,0
libere, destination : report a s52.** A et B sont inchanges : 8,0. Aucune ligne de C n'est gardee dans
la borne a deux : A + B remplissent exactement 8,0, et retirer un de leurs postes pour en loger un de
C serait un arbitrage de planning que personne n'a demande.

**Ce que la condition (ii) rouvre pour s52** (arbitrage prealable, section 8) : si s51 clot sans aucune
occurrence des formes (a), (b) ou (c), verifiee par `nadia-prof` contre les trois formes,
transcriptions comprises, la derivation a trois instances pour s52 se fait dans un document neuf.
Aucune borne n'est supposee d'avance ici.

### 14.3 Objectif gele corrige

Il remplace celui de la section 2 : **porter a `couvert-valide` jusqu'a six lignes** (`FRA-CE1-22`,
`-20`, `-23` ; `SCI-CM1-10`, `-15`, `-18`) et lever `HOM-CE1-R1`, sans qu'aucune instance de
`nadia-prof` depasse 5,0. Total du sprint : **A 3,5 + B 4,5 = 8,0.** Mesure : `MATRICE-couverture.md`,
123/182 au depart, **129/182 au mieux (70,9 %)**, contre 131/182 (72,0 %) a trois instances. Ce n'est
pas une promesse : un Done pedagogique refuse reste un refus. **`MAT-CE1-02` ne passe pas a `en cours`
a s51** : cette bascule dependait de la moitie textuelle de l'instance C.

### 14.4 Isolation (remplace la table de la section 9)

| Instance | Branche | Worktree (dossier voisin du checkout) | Scratchpad |
|---|---|---|---|
| A | `s51-nadia-instance-a` | `../recreo-s51-nadia-instance-a` | un repertoire temporaire hors depot, propre a A |
| B | `s51-nadia-instance-b` | `../recreo-s51-nadia-instance-b` | un repertoire temporaire hors depot, propre a B |

Aucune branche, aucun worktree, aucun scratchpad pour C. **Rappel de la regle, parce que s50 l'a
violee** : `squad/README.md` section 9 (amendement de s30, applicable depuis s31) - *"toute paire
d'instances du meme agent tournant en parallele recoit un scratchpad aussi dedie que son worktree"*.
A et B ont chacune **un worktree, une branche ET un scratchpad dedies**, ouverts avant le premier
ecrit de chaque instance. `DEC-0109` ne citait pas cette regle ; cette decision la cite, et `sofia-sm`
la fait appliquer (14.8). Une instance qui ecrit dans `squad/decisions/REGISTRE-identifiants.md` le
fait depuis son propre worktree.

### 14.5 Stories de C : non engagees, reportees a s52, non supprimees

| Poste | Story | Etat apres amendement |
|---|---|---|
| `MAT-CE1-17` | `S51-MAT-SYMETRIE` | **non engagee a s51, reportee a s52** |
| `MAT-CE1-06` | `S51-MAT-MULTIPLICATION` | **non engagee a s51, reportee a s52** |
| `MAT-CE1-02` (moitie textuelle) | `S51-MAT-COMPARER-RANGER` | **non engagee a s51, reportee a s52** |

Les trois stories restent ecrites dans `docs/backlog/backlog.md` (section "Sprint 51"). Controle
`PAR-16` : aucun poste engage sans story, et aucune story de C ne porte de poste engage.

**Report compte.** `MAT-CE1-17` : huit sprints consecutifs sans engagement, s43 a s50 (section 1.6),
**neuf avec celui-ci, s43 a s51.** Le motif de ce neuvieme n'est ni l'epuisement suppose du vivier ni
la regle d'une ligne par sprint : c'est un refus ecrit de `nadia-prof` sur la borne. Le compte ne
s'arrete pas pour autant. La moitie textuelle de `MAT-CE1-02`, qui devait entrer a s51, est reportee
avec sa moitie designation (section 5, *"premier report, s51"*).

### 14.6 Arbitrages de `nadia-prof` acquis pour s52

Cites de `ARBITRAGE-PREALABLE-s51-borne-a-trois-instances.md` (forme 1). Ils repondent aux deux points
de la section 1.5 pour ce qui est pedagogique, et valent pour tout paquet de designation de C.

**`ZON-13` contre les lignes candidates qui se croisent** (section 6.3) :

1. *"`ZON-13` ne se relache pas pour les figures de symetrie. Deux zones ne se chevauchent pas. Le motif
   est le doigt de l'enfant : "il croit montrer l'un et presse l'autre". Il vaut a plus forte raison
   quand les deux lignes sont un axe et une diagonale, puisque c'est la confusion que `SYM-CE1-2`
   existe pour tester."*
2. *"Une zone n'a pas a couvrir toute sa ligne. Elle couvre une portion ou cette ligne est seule
   dessinee. Le point de croisement n'appartient a aucune zone."*
3. *"Prolonger les lignes candidates au-dela de la figure est recevable, et c'est l'usage en classe :
   on trace l'axe de part en part. La zone peut donc se poser sur la partie exterieure de la ligne."*
4. *"Parmi les lignes candidates d'un item, une seule est un axe de symetrie de la figure."*
5. *"Je l'auditerai par `ZON-9`. Zones masquees, je trouve l'axe sur le dessin a la taille servie. Puis
   je revele les zones : la bonne zone doit etre sur la ligne que j'ai trouvee, et rien dans sa surface
   ne doit appartenir a une autre ligne."*

**Zones sur une ligne graduee** (section 6.4) :

1. *"Les zones d'une ligne graduee sont homogenes au sens de `ZON-8`. L'enfant n'enumere pas des choses
   differentes : il lit une echelle. Le plafond de six au CP et douze au CM1 ne s'applique donc pas. Le
   plancher de trois zones s'applique."*
2. *"Les zones sont posees sur des graduations, jamais entre deux, conformement a `ORD-CE1-6`."*
3. *"La bonne zone ne se trouve pas par approximation. Quand elles existent, les deux graduations
   voisines de la bonne sont aussi des zones."*

**Premier paquet de designation au CE1** (section 6.2, reponse au point 5 de la section 8) : une seule
instance l'audite, et elle ne porte aucun autre terrain neuf dans le meme sprint (`PAR-13`).

**Ce que `nadia-prof` laisse a d'autres, et que cet amendement ne tranche pas** : la forme d'une zone
sur une ligne oblique (`theo-archi`) et le plafond numerique de zones d'une ligne graduee (`theo-archi`
et `ines-design`), dans l'avis de faisabilite sur le materiau de C qui sert s52 (section 1.5, points 1
et 2).

### 14.7 Deux points ouverts pour s52, non tranches

1. **`PAR-4` (passe transverse, 0,5) a deux instances.** `nadia-prof` constate (section 6.1) que
   `DEC-0095` et `DEC-0098` sont les seules `DEC` de s41 a s50 qui la citent, comme poste retranche de
   la derivation de 8,0, et qu'aucun sprint parallele depuis s41 ne l'a engagee. Elle ne releve pas la
   barre de s51 pour cela. **Question posee a s52 : la passe transverse est-elle engagee a deux
   instances, et dans quel poste ?**
2. **L'ecart entre `PAR-9` et la pratique depuis s41.** `PAR-9` (s30) dit *"4,0 par instance, marge de
   rejeu comprise"* ; la pratique depuis s41 (`DEC-0095`, `DEC-0098`) compte 8,0 de lots, rejeu hors
   compte, plafond individuel de 5,0 (arbitrage prealable, section 1.2). **Question posee a s52 :
   quelle lecture fait foi, `PAR-9` est-elle amendee ou la pratique corrigee ?**

### 14.8 Routage (une decision sans proprietaire n'est pas prise)

| A qui | Quoi |
|---|---|
| `sofia-sm` | Ouvrir s51 a **deux** branches et **deux** worktrees (A et B), avec un scratchpad par instance (14.4) ; aucune branche ni worktree pour C. Ecrire l'occurrence de la forme (c) de s50 en amendement date de la retro de s50 (arbitrage prealable, section 7). Controle `PAR-16` a la cloture du planning : aucun poste de C engage |
| `theo-archi` | Avis de faisabilite sur le materiau de C : il sert s52, plus s51 (section 8, dernier alinea, conserve). Y traiter la forme d'une zone sur une ligne oblique et le plafond de zones d'une ligne graduee (14.6) |
| `ines-design` | Contraintes de dessin de C : servent s52 |
| `nadia-prof` | Instances A et B inchangees. A la cloture de s51 : la verification des formes (a), (b) et (c) qui rouvrirait la derivation pour s52 (14.2) |

**Ce que cet amendement ne fait pas.** Il ne change aucune ligne de l'instance A ni de l'instance B. Il
ne deplace aucun statut de `MATRICE-couverture.md`. Il ne prononce aucun Done. Il ne conteste pas le
refus : il en tire les consequences.

## Decisions prises (amendement du 2026-10-05)

8. Le refus de `nadia-prof` est accepte ; la borne a trois instances n'est pas derivee pour s51. Les
   decisions 2, 3 et 5 ci-dessus sont remplacees : s51 ouvre a deux instances, A (3,5/5,0) et B
   (4,5/5,0), 8,0 au total ; l'instance C est *non engagee, budget libere* (3,0), report a s52.
9. Objectif gele : au mieux six fermetures, 129/182 (70,9 %). `MAT-CE1-02` ne passe pas a `en cours`.
10. Deux branches, deux worktrees, deux scratchpads dedies (14.4).
11. `S51-MAT-SYMETRIE`, `S51-MAT-MULTIPLICATION` et `S51-MAT-COMPARER-RANGER` : non engagees a s51,
    reportees a s52, non supprimees. `MAT-CE1-17` : neuf sprints (s43 a s51).
12. Arbitrages de `nadia-prof` sur `ZON-13` et sur les zones d'une ligne graduee : acquis pour s52.
13. Deux points ouverts pour s52, non tranches : `PAR-4` a deux instances, ecart `PAR-9`/pratique.

---

## 15. Amendement du 2026-10-06 : verdicts de l'instance B, un seul rejeu engage (`SCI-CM1-18`), `SCI-CM1-15` reportee a s52

- **Auteur** : `camille-po`.
- **Sources** : les trois fiches de `nadia-prof` du 2026-10-06 (s51, instance B) :
  `squad/conformite/FICHE-bandit-manchot-corps-cm1.md` (`SCI-CM1-10`, VALIDE SOUS RESERVE, reserves
  `CRP-CM1-R1` a `CRP-CM1-R3`), `FICHE-bandit-manchot-terre-cm1.md` (`SCI-CM1-15`, REFUSE, motifs
  items 4 et 11, quatre points non bloquants), `FICHE-bandit-manchot-activite-humaine-cm1.md`
  (`SCI-CM1-18`, REFUSE, motif item 11, deux points non bloquants). Les motifs sont les siens et ne
  sont pas reformules ici ; les criteres des stories les reprennent mot pour mot.
- **Ce que cet amendement tranche** : quel rejeu s51 engage, ce que deviennent les trois reserves de
  `SCI-CM1-10`, et la couverture au mieux. Les sections 1 a 14 sont inchangees.

### 15.1 Regles lues, avec leur texte

- **`PAR-8`** (`ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`,
  section 13) : plafond individuel de 5,0 par instance. La section 2 de cette decision et la section 14
  nomment la marge de B, 0,5, comme etant le rejeu.
- **`PAR-10`** : un rejeu coute 0,5 **par paquet** (application : `DEC-0106` section 2 pour `HOM-CE1-R1`, ligne `HOM-CE1-R1` du tableau de la section 2 de cette decision).
- **`PAR-14`** (`ARBITRAGE-PREALABLE-s33...`, section 5.5) : *"Une reprise dont les corrections touchent
  plus de la moitie des items d'un paquet se paie au tarif d'un lot - 1,0 quand je suis la source des
  motifs, 1,5 sinon - et non au tarif d'un rejeu (0,5)."*
- **`PAR-16`** : un poste n'est engage que s'il porte une story au backlog.

### 15.2 Budget de l'instance B, chiffre

| Poste | Montant | Cumul |
|---|---|---|
| Trois premiers passages (`SCI-CM1-10`, `-15`, `-18`), consommes le 2026-10-06 | 4,5 | 4,5 |
| Rejeu `SCI-CM1-18`, 0,5 (`PAR-10`) | 0,5 | **5,0 = plafond de `PAR-8`, marge 0** |
| Rejeu `SCI-CM1-15`, 0,5 | 0,5 | 5,5, **refuse** : depasse 5,0 |
| Correction `CRP-CM1-R1` a `-R3`, 0,5 | 0,5 | 5,5, **refuse** : depasse 5,0 |

Au plus un des deux postes de 0,5 tient, et le rejeu d'un refus passe avant la correction d'une
reserve. Je n'eleve pas le plafond : c'est une clause de `nadia-prof`, et la marge de 0,5 est,
dans les sections 2 et 14, le rejeu.

### 15.3 Rejeu retenu : `SCI-CM1-18`. `SCI-CM1-15` est reportee a s52

**Comptes, verifies dans les fiches.**

| Ligne | Items a corriger pour lever le refus | Points non bloquants | Items touches si tout est pris | Plus de la moitie (plus de 6 sur 12) ? |
|---|---|---|---|---|
| `SCI-CM1-18` | 1 (item 11) | 2 points : items 8, 10, 12 (indices) et item 5 | **5** : 5, 8, 10, 11, 12 | **non** : `PAR-14` ne s'applique pas, 0,5 |
| `SCI-CM1-15` | 2 (items 4 et 11) | 4 points : item 10 (indice et *"en France metropolitaine"*, avec les items 9 et 11), item 7, items 3 et 9, item 2 | **7** : 2, 3, 4, 7, 9, 10, 11 | **oui** si tout est pris : `PAR-14`, 1,0 (`nadia-prof` est source des motifs) |

**Motifs du choix, par ordre de poids.**

1. **Le prix.** Le rejeu de `SCI-CM1-18` prend ses deux points non bloquants dans le meme diff et reste
   a 0,5. Celui de `SCI-CM1-15` ne tient a 0,5 que si les quatre points non bloquants sont laisses de
   cote, c'est-a-dire ouverts ensuite en reserves datees (fiche, section 8) : un second passage a
   payer. Pris ensemble, ils passent le seuil de `PAR-14` et coutent 1,0, ce qui ne tient pas dans le
   plafond de 5,0.
2. **La chance de fermer a s51.** `SCI-CM1-18` n'a plus qu'un motif, sur un item ; `nadia-prof` ecrit que
   `SCI-2`, *"la condition la plus dangereuse du lot"*, est tenue sur les douze items. Le rejeu a une
   seule chose a tenir. `SCI-CM1-15` porte deux motifs bloquants, dont une cle fausse sur le jour et la
   nuit et une sur les saisons, plus un indice faux (item 10) qui n'est pas bloquant.
3. **Le risque nomme en section 4 est leve en grande partie.** Cette decision gardait `SCI-CM1-18` comme
   la ligne la plus risquee : `SCI-2` n'avait jamais ete tenue sur sa ligne native. Elle l'est
   desormais, et la reprise ne la rouvre pas.

**Ce que ce choix ne dit pas.** Il ne dit rien de la valeur pedagogique des deux paquets. Il tient au
prix, a la distance a la fermeture et au plafond, qui sont des grandeurs de planning.

**Garde-fou du rejeu de `SCI-CM1-18`.** Le rejeu touche les items 5, 8, 10, 11 et 12 et aucun autre. Si
l'ecriture en touchait sept ou plus, `PAR-14` s'appliquerait (1,0) et le plafond serait depasse : les
deux points non bloquants sortent alors du rejeu et sont ouverts par `nadia-prof` en reserves datees ;
l'item 11 reste, lui, dans le rejeu.

**Report de `SCI-CM1-15`, compte.** Premier report du rejeu : s51, motif le plafond de `PAR-8`
(15.2). `SCI-CM1-15` reste `non couvert`. Story : `S51-SCI-TERRE-CM1-REPRISE`, ecrite ce jour, **non
engagee a s51, reportee a s52**. Tarif prevu a s52 : **1,0** si les six points (deux motifs, quatre
non bloquants) sont repris dans le meme diff (`PAR-14`, 7 items sur 12), a fixer par `nadia-prof` au
rejeu ; **0,5** si elle ne rejoue que les deux motifs, auquel cas les quatre points deviennent des
reserves datees a payer ensuite. Je prefere un seul passage a 1,0 : le meme prix, un audit au lieu de
deux. Ce choix est a confirmer par `nadia-prof`, qui fixe le prix.

### 15.4 Reserves de `SCI-CM1-10` : une story, non engagee a s51

`CRP-CM1-R1`, `-R2` et `-R3` sont ecrites en **une seule story de correction differee, groupee par
paquet** : `S51-SCI-CORPS-CM1-RESERVES`. **Tarif 0,5** (`PAR-10`), non engagee a s51 (15.2), reportee a
s52.

**Motif de la story unique.** `PAR-10` fixe le rejeu au paquet, pas a la reserve. Les trois reserves
touchent cinq items sur douze (3, 5, 6, 7, 11) du meme fichier, sont issues du meme audit et se rejouent
en un seul passage ; trois stories a 0,5 feraient 1,5 pour un paquet que la regle fixe a 0,5. Precedent :
`USN-CM1-R1` a `USN-CM1-R8`, groupees en une story. Cinq items sur douze ne passent pas le seuil de
`PAR-14`.

**Rang.** Ces reserves ne sont pas des motifs de refus (fiche, section 7) :
aucune bonne reponse n'est fausse, aucun distracteur n'est vrai. Elles sont dues au meme titre que
`HOM-CE1-R1`, au rang d'une correction differee.

### 15.5 `SCI-CM1-10` passe a `couvert-valide` sans mention de couverture

Confirme. A la cloture de s51, je ferai passer la ligne de `non couvert` a `couvert-valide` sur la
seule base du verdict de `nadia-prof` du 2026-10-06 (`FICHE-bandit-manchot-corps-cm1.md`, bloc de
validation : *"Done pedagogique prononce le 2026-10-06"*, *"sans mention de couverture"*, `DEC-0110`
section 10). Les trois reserves ouvertes ne la rouvrent pas. Cette decision ne juge pas la porte
technique : elle reste a juger sur les jobs de `.github/workflows/ci.yml`, que je n'ai pas lances, et
la matrice n'est pas modifiee ici.

### 15.6 Couverture au mieux

Avant l'amendement : 129/182 (70,9 %), six fermetures (section 14.3). **Apres : 128/182 (70,3 %), cinq
fermetures** : `FRA-CE1-22`, `-20`, `-23` (instance A), `SCI-CM1-10` (verdict rendu), `SCI-CM1-18`
(sous le rejeu). `SCI-CM1-15` sort du numerateur de s51. Le calcul suppose que l'instance A ferme ses
trois lignes, ce que je n'ai pas lu : si le rejeu de `SCI-CM1-18` est refuse a son tour, le plancher de
B est 124/182 (une seule fermeture, `SCI-CM1-10`) avant le resultat de A. Ce n'est pas une promesse.

### 15.7 Routage (une decision sans proprietaire n'est pas prise)

| A qui | Quoi |
|---|---|
| `sofia-sm` | Affecter `S51-SCI-ACTIVITE-HUMAINE-CM1-REJEU` dans s51 (developpeur, puis instance B de `nadia-prof` pour le rejeu) ; porter au planning de s51 que le poste est engage a 0,5 et que B est a 5,0 ; porter `S51-SCI-TERRE-CM1-REPRISE` et `S51-SCI-CORPS-CM1-RESERVES` comme *"non engagees, reportees a s52"* (controle `PAR-16`). Les trois stories sont ecrites au backlog, section "Sprint 51" |
| `nadia-prof` | Rejouer `SCI-CM1-18` apres correction (fiche, section 7 : *"une correction se verifie a l'ecran, pas au diff"*) ; confirmer ou fixer le prix de `S51-SCI-TERRE-CM1-REPRISE` (15.3) |
| `theo-archi` | Aucun avis nouveau : les trois stories modifient des paquets YAML existants, sans mecanique ni schema neufs |
| `yanis-qa` | Les criteres du rejeu et des deux stories reportees sont ecrits en entree/sortie ; ce qu'aucun automate ne voit est liste dans chaque story pour `nadia-prof` |

**Ce que cet amendement ne fait pas.** Il ne modifie ni la matrice, ni les fiches, ni le registre, ne
prononce aucun Done et ne juge aucun contenu. Il n'ecrit aucun identifiant neuf : les codes `S51-*` ne
sont pas des identifiants de condition, et `CRP-CM1-R1` a `-R3` sont ceux de `nadia-prof`.

## Decisions prises (amendement du 2026-10-06)

14. Le rejeu de s51 est `SCI-CM1-18` (0,5), l'instance B atteint son plafond : 5,0, marge 0.
15. `SCI-CM1-15` reste `non couvert` ; `S51-SCI-TERRE-CM1-REPRISE` est non engagee, reportee a s52
    (premier report du rejeu), tarif prevu 1,0 (`PAR-14`) ou 0,5 selon le perimetre que `nadia-prof`
    rejoue.
16. `CRP-CM1-R1` a `-R3` : une story, `S51-SCI-CORPS-CM1-RESERVES`, 0,5 (`PAR-10`), non engagee, reportee a s52.
17. `SCI-CM1-10` passe a `couvert-valide`, sans mention, a la cloture de s51, sur le verdict de
    `nadia-prof`.
18. Couverture au mieux : 128/182 (70,3 %), cinq fermetures.


---

## 16. Amendement du 2026-10-06 (cloture de s51) : etat final, budget reel, reprises reportees a s52, perimetre propose pour s52

- **Auteur** : `camille-po`.
- **Sources** : les sept fiches de `nadia-prof` du 2026-10-06, relues en entier pour cet amendement, sous
  `squad/conformite/` : `FICHE-bandit-manchot-graphie-ce1.md` (`FRA-CE1-22`), `-temps-ce1.md`
  (`FRA-CE1-20`), `-ordre-alphabetique-ce1.md` (`FRA-CE1-23`), `-homophones-ce1.md` (`HOM-CE1-R1`),
  `-corps-cm1.md` (`SCI-CM1-10`), `-terre-cm1.md` (`SCI-CM1-15`), `-activite-humaine-cm1.md`
  (`SCI-CM1-18`). Commits des verdicts : `c886a0fd` (instance A, premier passage), `7efcb400` (instance A,
  second passage de `FRA-CE1-22`), `438f0d1e` (instance B), `81908b7d` (instance B, second passage de
  `SCI-CM1-18`, rejeu `5a4ca6d8`). Porte technique : `rapport-qualite.md` de `yanis-qa` dans le dossier de
  s51 (quatre jobs de la CI lances sur `ca4c934a`, 2076 tests, 0 echec), cite et non relance par moi.
- **Ce que cet amendement tranche** : l'etat final de s51, le budget reel des deux instances, le sort des
  reserves de l'instance A, de `SCI-CM1-18` et de `SCI-CM1-15`, ce qui est perime dans la section 15, et un
  perimetre propose pour s52. **Ce qu'il ne fait pas** : il ne prononce aucun Done, ne juge aucun contenu et
  n'ouvre pas s52. Les sections 1 a 15 sont inchangees ; en cas d'ecart, la section 16 fait foi.

### 16.1 Etat final des sept postes

Chaque verdict est lu dans la fiche, pas repris de la consigne de cloture.

| Poste | Verdict de `nadia-prof` (2026-10-06) | Statut de la matrice | Reserve ou motif |
|---|---|---|---|
| `FRA-CE1-22` | **REFUSE** au premier passage (items 13 et 16), **VALIDE sans reserve** au second (`df608669`, `7efcb400`) | `couvert-valide` | aucune |
| `FRA-CE1-20` | **VALIDE SOUS RESERVE** | `couvert-valide` | `TPS-CE1-R1` |
| `FRA-CE1-23` | **VALIDE SOUS RESERVE** | `couvert-valide` | `ALP-CE1-R1` |
| `HOM-CE1-R1` (`FRA-CE1-16`) | **VALIDE**, reserve levee (`cc5686e0`) | inchange, deja `couvert-valide` | levee |
| `SCI-CM1-10` | **VALIDE SOUS RESERVE**, *"sans mention de couverture"* | `couvert-valide` | `CRP-CM1-R1` a `CRP-CM1-R3` |
| `SCI-CM1-15` | **REFUSE**, premier passage, aucun rejeu finance | `non couvert` | items 4 et 11 |
| `SCI-CM1-18` | **REFUSE** au premier passage, **REFUSE** au second (rejeu `5a4ca6d8`) | `non couvert` | item 11, maintenu |

Quatre fermetures sur six lignes engagees ; aucune mention de couverture partielle n'est due (section 10).
La matrice est mise a jour dans le meme geste (`MATRICE-couverture.md`, note du 2026-10-06).

**Ce que le choix de 15.3 a donne.** J'avais retenu le rejeu de `SCI-CM1-18` sur le prix et sur la chance de
fermer (motif 2 de 15.3 : un seul motif, `SCI-2` tenue). Le rejeu n'a pas ferme : ce motif etait un pari, et
il a ete perdu. Je ne sais pas ce qu'aurait donne le rejeu de `SCI-CM1-15` a sa place.

### 16.2 Budget reel des deux instances

| Instance | Poste | Montant | Cumul |
|---|---|---|---|
| A | `FRA-CE1-22`, `FRA-CE1-20`, `FRA-CE1-23`, un lot chacune | 3,0 | 3,0 |
| A | `HOM-CE1-R1`, correction | 0,5 | 3,5 |
| A | rejeu `FRA-CE1-22` (`PAR-10` ; 2 items sur 24 touches, `PAR-14` ne s'applique pas) | 0,5 | **4,0** |
| A | marge non consommee (plafond 5,0) | 1,0 | |
| B | `SCI-CM1-10`, `SCI-CM1-15`, `SCI-CM1-18`, un lot chacune | 4,5 | 4,5 |
| B | rejeu `SCI-CM1-18` (`PAR-10`) | 0,5 | **5,0** |
| B | marge non consommee | 0 | |

**Engage au planning : 8,0. Consomme : 9,0** (A 4,0 + B 5,0). L'ecart de 1,0 est celui des deux rejeux, pris
chacun sur la marge de rejeu nommee en sections 2 et 14 (A : 1,5 dont 0,5 ; B : 0,5). Aucun plafond de
`PAR-8` n'est depasse. B ferme une ligne sur trois pour 5,0.

### 16.3 Reserves de l'instance A : `TPS-CE1-R1` et `ALP-CE1-R1`, non engagees a s51

**Elles tiennent dans le budget, et je ne les engage pas.** Arithmetique : 4,0 + 0,5 + 0,5 = 5,0, le plafond
de `PAR-8`, marge 0 (comme B en 15.2). `PAR-14` ne s'applique pas : quatre items sur vingt-quatre pour
`TPS-CE1-R1` (4, 7, 9, 11), trois pour `ALP-CE1-R1` (1, 6, 11). Trois motifs de ne pas les engager :

1. **`PAR-16`** : un poste n'est engage que s'il porte une story et une affectation nominative au planning ;
   le planning de s51 est clos et ne les porte pas. Les engager a la cloture serait un arbitrage de planning
   que personne n'a ecrit.
2. **La porte technique est mesuree.** Toute correction de YAML apres `ca4c934a` oblige a une nouvelle
   mesure des quatre jobs et a un rejeu par l'instance A. Je juge qu'une reserve non bloquante ne vaut pas de
   rouvrir la mesure de cloture.
3. **Marge 0.** Engagees ensemble, elles laissent A sans rejeu si l'une des deux corrections est refusee.

Aucune des deux reserves ne rouvre sa ligne : `FRA-CE1-20` et `FRA-CE1-23` sont `couvert-valide`. Deux
stories de correction differee sont ecrites au backlog (`docs/backlog/backlog.md`, fin de la section "Sprint
51"), au format et au tarif de `HOM-CE1-R1` (0,5 chacune), **non engagees a s51, reportees a s52 : premier
report**, avec les criteres de levee de `nadia-prof` mot pour mot. `HOM-CE1-R1`, ouverte a s50, a ete engagee
a s51 : meme delai vise.

### 16.4 `SCI-CM1-18` : deux refus, motif maintenu, reprise a s52

`nadia-prof` maintient un seul motif, l'item 11 : *"la correction deplace le defaut sans le supprimer"*
(fiche, second passage, S2-3). La question demande de preserver *"ces ressources"*, le petrole et le charbon,
et *"produire de l'électricité avec plus de gaz naturel"* y repond comme la bonne reponse. Deux points
du premier motif sont leves (l'enonce ne fixe plus un but que le stockage atteint a la lettre ; *"extraire
plus vite"* est faux sans discussion) ; les deux points non bloquants du premier passage sont pris (S2-2). Je ne
conteste pas le motif et je ne le reformule pas.

**Correction la plus courte, celle de la fiche** : *"un énoncé qui nomme le gaz naturel avec le pétrole et le
charbon, ou qui parle des énergies fossiles, quand le gaz reste en distracteur"*, formulations laissees a
l'equipe. Barre inchangee : *"Je ne releverai pas cette barre au rejeu suivant."* Aucune autre modification
n'est une condition de levee, ni sur l'item 11 ni ailleurs.

Story : `S51-SCI-ACTIVITE-HUMAINE-CM1-REPRISE`, ecrite au backlog, **non engagee a s51** (B est a 5,0),
candidate a s52, troisieme passage. Tarif prevu 0,5 (`PAR-10`, un seul item touche), a confirmer par
`nadia-prof`, qui fixe le prix. Precedent de la ligne voisine : `SCI-CM1-16` a ferme au troisieme passage a
s46 (section 4).

**Si le troisieme passage refuse encore**, je n'ecris pas ici de quatrieme passage : la suite est une decision
de s52. Question posee a `nadia-prof`, sans que j'y reponde : retirer l'item 11 du paquet leve-t-il le motif ?
Elle a ecrit cette voie recevable pour `FRA-CE1-22` (*"Supprimer les deux items sans les remplacer est aussi
recevable"*), pas pour cette fiche.

### 16.5 `SCI-CM1-15` : reprise a s52, et laquelle des deux lectures vaut pour les quatre points

Le backlog portait deux lectures contradictoires dans la meme story. La premiere (tarif prevu) : 1,0 si les
six points sont repris dans le meme diff, 0,5 si seuls les deux motifs le sont. La seconde (paragraphe *"Ce que
cette story ne fait pas"*) : les quatre points non bloquants, *"laisses en l'etat"*, deviennent des reserves
datees. **La lecture qui vaut est la premiere : les quatre points sont repris dans le meme diff, sept items
sur douze, 1,0 (`PAR-14`, `nadia-prof` source des motifs).** C'est celle que preferait 15.3 (un audit au lieu
de deux, pour le meme prix) et celle que les criteres de la story ecrivent deja. Le second paragraphe decrit
le cas de repli, et le backlog est corrige en ce sens. **Ce qui reste a `nadia-prof`** : fixer le prix au
rejeu et, si un point est laisse en l'etat, l'ouvrir en reserve datee (fiche, section 8).

`SCI-CM1-15` reste `non couvert`. **Premier report du rejeu : s51**, motif le plafond de `PAR-8` (15.2).
Story : `S51-SCI-TERRE-CM1-REPRISE`, non engagee a s51, candidate a s52.

### 16.6 `SCI-CM1-10` et ses trois reserves

`SCI-CM1-10` est `couvert-valide` dans la matrice, sans mention de couverture, sur le seul verdict de
`nadia-prof`. Les trois reserves ne la rouvrent pas. La story unique `S51-SCI-CORPS-CM1-RESERVES` (15.4) est
maintenue telle quelle : tarif 0,5 (`PAR-10`), cinq items sur douze (`PAR-14` ne s'applique pas), non
engagee a s51, candidate a s52, premier report.

### 16.7 Couverture finale

**127 / 182 = 69,8 %**, au lieu des 128 / 182 de 15.6 (cinq fermetures au mieux) et des 129 / 182 de 14.3
(six). Compte par la commande ecrite en tete de `MATRICE-couverture.md` (note du 2026-10-06), lancee sur la
matrice modifiee : `couvert-valide` 127, `en cours` 3, `non couvert` 52, `non couvrable en l'etat` 76, total
258, denominateur 182. Avant ce geste, la meme commande rendait 123 et 56 : +4 sur le numerateur, -4 sur les
`non couvert`. Le plancher de 15.6 (124 / 182) n'est pas atteint : A a ferme ses trois lignes.

### 16.8 Section 15 : ce qui est perime, et deux corrections

Lignes comptees sur la version de la section 15 a `db1fc6ed`, avant cet amendement. La section 15 n'est pas
reecrite.

- **15.2, tableau et paragraphe qui le suit (lignes 699 a 709)** : perimes. Le budget de B y est un budget
  prevu, avec un seul rejeu tenable ; le budget reel est en 16.2.
- **15.3 (lignes 711 a 749)** : le *choix* du rejeu de `SCI-CM1-18` est un fait date et reste lisible. Le
  tarif prevu de `SCI-CM1-15` (*"a fixer par `nadia-prof`"*, avec ma preference) est remplace par 16.5, et le
  garde-fou *"sept ou plus"* est sans objet : le rejeu a touche cinq items et a ete refuse.
- **15.6 (lignes 776 a 782)** : perimee, remplacee par 16.7. La phrase *"ce que je n'ai pas lu"*, ligne 781,
  qui portait sur l'instance A, **n'est plus vraie** : j'ai lu les fiches de l'instance A (16.1), et A a
  ferme ses trois lignes.
- **Decisions prises 14 a 18 (lignes 799 a 807)** : a lire comme historique, chacune reprise ici. 14 (rejeu de
  `SCI-CM1-18`, B a 5,0) : executee, issue en 16.4. 15 (`SCI-CM1-15` reportee, tarif 1,0 ou 0,5) : lecture
  fixee en 16.5. 16 (`S51-SCI-CORPS-CM1-RESERVES`) : maintenue (16.6). 17 (`SCI-CM1-10` `couvert-valide`) :
  executee dans la matrice. 18 (128 / 182) : remplacee par 127 / 182 (16.7).
- **Erratum de citation, ligne 789 (15.7, ligne `nadia-prof`)** : la phrase *"une correction se verifie a
  l'ecran, pas au diff"* y est citee comme *"fiche, section 7"*. **Elle n'est pas dans la section 7 de
  `FICHE-bandit-manchot-activite-humaine-cm1.md`** : elle est dans le paragraphe d'en-tete du premier verdict,
  avant la section 1 (*"Le lot repasse devant moi apres correction, et je le rejoue avant de juger : une
  correction se verifie a l'ecran, pas au diff."*). La section 7 porte le motif de l'item 11. Le sens de la
  ligne 789 est inchange.

**Une limite sur ce que j'ai recu.** La consigne de cloture annoncait une liste de defauts du relecteur ; je
n'en ai recu que les quatre plages de lignes ci-dessus, la citation de la ligne 789 et la ligne 781. Si la
liste en porte d'autres, ils ne sont pas traites ici.

### 16.9 Deux paquets refuses restent dans le catalogue jusqu'a s52 (decision de l'utilisatrice du 2026-10-06)

`catalogue.yaml` porte les entrees `bandit-manchot-activite-humaine-cm1` et `bandit-manchot-terre-cm1`
(`grep -n "activite-humaine-cm1\|terre-cm1" adapters/adapter-content/src/main/resources/catalogue.yaml` :
deux entrees). Deux paquets que `nadia-prof` a refuses le 2026-10-06 sont donc dans le catalogue servi. Les
deux refus portent sur une cle de reponse fausse : l'enfant est dit en faute sur une reponse vraie ou
acceptable. Je n'avais pas pris de decision de retrait : elle change le contenu servi, elle n'etait pas dans
la consigne, et le retrait (dans `ADR-0061`, l'absence d'entree reste le seul retrait) touche des tests que je
ne mesure pas. J'ai pose la question a l'utilisatrice et recommande le retrait.

**Decision de l'utilisatrice, 2026-10-06.** Question : *"Retirer du catalogue servi les deux paquets refuses
par nadia-prof (activite-humaine-cm1 et terre-cm1) ?"* Reponse de l'utilisatrice : *"Garder jusqu'a s52"*.
Elle est citee mot pour mot, en forme 3 de `squad/README.md` section 2 bis : recu de MASTER, sans source dans
le depot, le 2026-10-06. Cette forme ne vaut pas motif sans ecriture prealable : c'est cette section qui l'ecrit, et la
`DEC` de s52 s'appuie sur elle et non sur l'enonce de MASTER.

**Decision : les deux entrees restent dans le catalogue servi jusqu'a s52.** Aucun changement de code, de
catalogue ni de test n'en decoule, donc aucune story de retrait : `sofia-sm` n'a rien a executer au titre de
cette section, et la recommandation de retrait que j'avais faite n'est pas suivie.

**Le risque que cette decision accepte, et que `nadia-prof` a ecrit dans ses fiches.** Jusqu'a s52, un enfant
qui lance l'application peut jouer ces deux paquets dans l'etat refuse. Sa phrase, dans
`squad/conformite/FICHE-bandit-manchot-terre-cm1.md`, section 7, motif 1 (item 4) : *"L'enfant qui la choisit
recoit "Ce n'est pas ça." : il apprend comme faux un fait vrai. C'est une cle de reponse fausse."* Et dans
`squad/conformite/FICHE-bandit-manchot-activite-humaine-cm1.md`, section 7, motif de l'item 11 : *"L'enfant
qui raisonne juste sur la question ecrite et choisit le charbon recoit "Ce n'est pas ça." - je l'ai fait a
l'ecran."* Le risque n'est pas theorique : elle l'a observe a l'ecran. Il porte sur `terre-cm1` aux items 4 et
11 et sur `activite-humaine-cm1` a l'item 11, soit les items nommes par ses motifs et aucun autre.

**Difference avec `DEC-0009`, et la correction de ma comparaison.** Dans la premiere redaction de cette
section, j'ecrivais que c'etait *"la situation de `DEC-0009`"* (`bandit-manchot-le-bon-son-cp`, s04). Le
defaut est de la meme famille, une cle fausse qui dit l'enfant en faute. Les deux situations different sur
ce qui a ete decide : `DEC-0009` retirait le jeu du catalogue, par une decision de produit, pour cinq motifs
cumules (dont 23 items sur 24 qui se reussissaient sans le phoneme vise) ; ici l'utilisatrice a arbitre le
contraire, garder, pour une duree bornee a s52, sur des paquets dont `nadia-prof` nomme un ou deux items.
**Je ne tire de cette difference aucun jugement sur la gravite pedagogique des defauts : ce jugement est celui
de `nadia-prof`.** Je ne dis pas non plus que `DEC-0009` s'applique ici ou ne s'applique pas : la regle que
ce depot a tiree de s04 (une decision de retrait sans proprietaire fait croire que le probleme est traite)
reste vraie, et c'est pourquoi la borne ci-dessous est ecrite comme une obligation et non comme une intention.

**Report compte.** Premier report du retrait de ces deux paquets : ecarte le 2026-10-06, cloture de s51,
garde jusqu'a s52. Un second report a s52 serait le deuxieme et s'ecrirait comme tel.

### 16.10 Perimetre propose pour s52, non ouvert

L'ouverture de s52 est une `DEC` de s52. Cette section liste les candidats, leur tarif et ce qui les
conditionne. **Aucune borne a trois instances n'est supposee** : sa derivation est un document de
`nadia-prof`, et la verification des formes (a), (b) et (c) qu'elle a annoncee pour la cloture de s51
(`ARBITRAGE-PREALABLE-s51-borne-a-trois-instances.md`, section 8) n'existe pas dans le depot a cette date
(`ls squad/conformite` : aucun fichier de derivation ni de s52).

| Candidat | Couple matiere/niveau (`PAR-2`) | Tarif prevu | Conditions |
|---|---|---|---|
| `S51-SCI-ACTIVITE-HUMAINE-CM1-REPRISE` (`SCI-CM1-18`, troisieme passage) | sciences et technologie CM1 | 0,5 | prix fixe par `nadia-prof` ; question de 16.4 |
| `S51-SCI-TERRE-CM1-REPRISE` (`SCI-CM1-15`) | sciences et technologie CM1 | 1,0 (ou 0,5) | prix fixe par `nadia-prof` (16.5) |
| `S51-SCI-CORPS-CM1-RESERVES` (`CRP-CM1-R1` a `R3`) | sciences et technologie CM1 | 0,5 | aucune |
| `TPS-CE1-R1`, `ALP-CE1-R1` | francais CE1 | 0,5 chacune | aucune ; premier report |
| nouveaux lots de francais CE1 | francais CE1 | 1,0 par ligne | 20 `non couvert` au disque, dont 18 atteignables et 2 plafonnees (`FRA-CE1-03`, `-21`) selon `DEC-0110` section 6 ; choix des lignes et avis de `theo-archi` a la `DEC` d'ouverture |
| instance C : `MAT-CE1-17`, `MAT-CE1-06`, `MAT-CE1-02` (moitie textuelle) | mathematiques CE1 | 3,0 | a rouvrir **sous la condition de `nadia-prof`** : document de derivation et verification des formes (a), (b), (c) a la cloture de s51 ; `ADR-0080` A2 ecrit avant le premier paquet de designation de CE1 ; materiau et contraintes de dessin (`ines-design`) ; arbitrages de 14.6 acquis. **Report compte : `MAT-CE1-17` en est a neuf sprints (s43 a s51) ; sans engagement a s52, dix** |
| `ART-CP-10` | arts plastiques CP | non chiffre | `ADR-0104` est `Propose`, suspendu a `C5` clause 1 de `nadia-prof` ; **aucun budget avant sa reponse**. Exclue de s50 (`DEC-0109`, 10.1) puis de s51 (section 6) : deux sprints |

Le vivier de sciences et technologie CM1 se reduit aux trois premiers postes (2 `non couvert` au disque) :
une instance de sciences a s52 porterait 2,0 au plus, sans lot neuf a ajouter.

**Exigence de s52 sur les deux paquets gardes (16.9).** La `DEC` d'ouverture de s52 tranche, **avant tout
autre poste touchant `bandit-manchot-activite-humaine-cm1` ou `bandit-manchot-terre-cm1`**, entre deux
issues : le retrait du catalogue servi, ou la reprise (`S51-SCI-ACTIVITE-HUMAINE-CM1-REPRISE`,
`S51-SCI-TERRE-CM1-REPRISE`) qui les repasse devant `nadia-prof`. Un poste de reprise ne s'ouvre pas tant
que cette tranche n'est pas ecrite, et la `DEC` ne s'ouvre pas en les laissant servis sans le dire. La
decision de l'utilisatrice court jusqu'a s52 : elle n'est pas reconduite par defaut.

**Questions deja ouvertes, a trancher par la `DEC` de s52** : `PAR-4` (passe transverse) a deux instances et
ecart `PAR-9` / pratique (14.7) ; ecart *"palier 2"* des cellules de la matrice (`DEC-0110` section 6, point
R10 du planning de s51), que cette cloture ne traite pas.

### 16.11 Routage

| A qui | Quoi |
|---|---|
| `sofia-sm` | Porter a la cloture de s51 le budget reel de 16.2 (A 4,0, B 5,0) ; `S51-SCI-ACTIVITE-HUMAINE-CM1-REPRISE`, `S51-SCI-TERRE-CM1-REPRISE`, `S51-SCI-CORPS-CM1-RESERVES` et les deux stories de correction `TPS-CE1-R1` et `ALP-CE1-R1` comme *"non engagees, reportees a s52"* (`PAR-16`). Q1 (16.9) des qu'elle a une reponse |
| `nadia-prof` | Fixer le prix de `S51-SCI-TERRE-CM1-REPRISE` et du troisieme passage de `SCI-CM1-18` ; repondre a la question de 16.4 ; la verification des formes (a), (b), (c) de s51 (R12 du planning) ; sa reponse a `C5` clause 1 pour `ADR-0104` |
| `theo-archi` | Aucun avis nouveau pour les reprises : YAML seul. Avis sur C et `ADR-0080` A2 pour s52 (14.8, inchange) |
| `yanis-qa` | Criteres des cinq stories ecrits en entree/sortie ; ce qu'aucun automate ne voit est liste dans chacune pour `nadia-prof` |

## Decisions prises (amendement de cloture du 2026-10-06)

19. s51 clot a quatre fermetures : `FRA-CE1-22`, `FRA-CE1-20`, `FRA-CE1-23`, `SCI-CM1-10`. Couverture
    127 / 182 (69,8 %), commande ecrite dans la matrice.
20. Budget reel : A 4,0, B 5,0, total 9,0 pour 8,0 engages (deux rejeux). `TPS-CE1-R1` et `ALP-CE1-R1` tiennent
    dans le plafond de A a 5,0 et ne sont pas engagees a s51.
21. `SCI-CM1-18` : motif maintenu, reprise a s52 par `S51-SCI-ACTIVITE-HUMAINE-CM1-REPRISE`, 0,5 prevu.
22. `SCI-CM1-15` : reprise a s52, les quatre points non bloquants repris dans le meme diff, 1,0 prevu.
23. Stories de correction differee `TPS-CE1-R1` et `ALP-CE1-R1`, 0,5 chacune, reportees a s52 (premier
    report).
24. La section 15 est lue avec la section 16 (16.8), citation de la ligne 789 corrigee.
25. Perimetre de s52 propose en 16.10, non ouvert ; l'ouverture est une `DEC` de s52.
26. Q1 (16.9) est ouverte : deux paquets refuses restent dans le catalogue tant que l'utilisatrice n'a pas
    repondu.
