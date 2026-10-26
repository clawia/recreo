# s57 - lecture avant `verify` du premier jet de `ce1-qlm-besoins-de-la-plante` (`S57-BR-QLM-CE1-03`, `P1`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : debut 2026-10-26 14:31 (UTC+01:00), ecriture 14:47, lues par `date` ; fin en section 8.
- **Arbre** : branche `s57-p5-nadia-lecture03`, base `round-two` a `ccfd1a0c`, lue par `git`.
- **Commit lu** : `7fe939a6` (`[MARC]`, 2026-10-26 14:13:36 +0100), premier jet de `marc-dev`, fusionne dans `round-two`.
- **Type** : lecture avant `verify` (`QC7`). **Aucun Done, aucun verdict de lot**, aucun identifiant neuf, aucune condition neuve.
  Je n'ai modifie ni le paquet, ni les tests.
- **Audit degrade, dit** : rien n'est joue a l'ecran. Le paquet n'est pas au catalogue (`QC3` : `grep -c "ce1-qlm" catalogue.yaml`
  rend 0). J'ai joue chaque item sur le papier, comme un enfant de CE1, y compris en me trompant ; le jeu a l'ecran vient a l'audit (`P2`).
- **Lu** : `ce1-qlm-besoins-de-la-plante.yaml` (14 items) ; `PaquetCe1QlmBesoinsDeLaPlanteTest.java` ; son jeu fautif
  `contreexemple/PaquetCe1QlmBesoinsDeLaPlanteTest/contenu/besoins-fautifs.yaml` (22 sondes) ; l'entree de rang du paquet ; story
  `S57-BR-QLM-CE1-03` et le bloc commun (`QC1` a `QC10`, `TRV-CE1-1`, `-2`, `-5`, `-6`, `NRC-CE1-1`, `QMT-CE1-1`, `-2`, `CYC-1`) ;
  `planning.md`, amendement du 2026-10-26 13:35 ; `arbitrage-nadia-palier-5-qlm-neuf-lots-avant-ouverture.md` (ligne `-03`) ;
  `LON-CE1-1`, `-2` (`ARBITRAGE-s36-longueur-d-enonce-au-ce1.md`), `LON-CE1-5` (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`) ; ma
  lecture et ma relecture de `-01` (modele) ; les voisins `cp-qlm-hygiene`, `cp-qlm-cycle-de-vie`, `cp-qlm-vivant-non-vivant`,
  `cp-qlm-modes-de-vie`, `ce1-qlm-etapes-de-developpement`, `cm1-sciences-chaine-alimentaire` ;
  `avis-theo-palier-5-garde-fer-3-ce1-qlm-02.md` et mon `avis-nadia-seconde-tache-ce1-qlm-02.md` (pour `S4`).
- **Mesures lancees** :
  - recompte par script (Python, hors du depot, non commite), comparaison stricte de longueur, la meme que
    `ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest.java:198-199`, et compte de mots de `PaquetLu.mots`, sur le
    paquet puis sur une copie portant mes corrections ;
  - `bash outils/mvn-jdk21.sh -B -q -pl adapters/adapter-content -am test -Dtest=PaquetCe1QlmBesoinsDeLaPlanteTest`, JDK 21.0.10
    (propriete `java.version` du rapport) : **26 tests, 0 echec, 0 erreur**. Perimetre : ce seul test, module `adapter-content` et ses
    dependances. Ce n'est ni un `verify`, ni la porte technique.

## Resume

| Point | Reponse |
|---|---|
| Items | **6 justes, 8 a corriger, 0 refuse en l'etat** (section 2) |
| Ce qui revient le plus | **six indices disent la reponse** au lieu d'une methode (`TRV-CE1-5`), dont celui de l'arrosoir (section 2.1) |
| Distracteurs *du sucre*, *du lait*, *du bruit* | **confirmes**, avec une precision sur ma propre condition (section 1.1) |
| `NRC-CE1-1`, `CYC-1` | **tenues** : aucun paquet de CP ne sert les besoins de la plante, aucun voisin n'est contredit (section 1.2) |
| `I1` a `I6` de `marc-dev` | `I1` garde ; `I2` reformulee ; `I3` confirmee, source a recrire ; `I4` confirmee ; `I5` 7 sur 14, inchange apres correction ; `I6` conforme, compte corrige (section 3) |
| `S4` de `theo-archi` (`-02`) | **confirme** ; six paraphrases nommees entrent dans l'ensemble ferme (section 4) |
| Cout | **1,0 au bareme a cette ecriture** ; 1,5 si mes corrections appellent une seconde lecture (section 7) |

---

## 1. Ce que j'ai verifie avant les items

- **`FER-1`** : *l'eau* (items 1, 5, 13), *la lumiere* (4, 7, 10), *la chaleur* (3, 8, 11) sont en bonne reponse. Mes corrections
  n'en retirent aucune.
- **`FER-3` et son operation mentale** : *besoin* 9 items, *privation* 5. Les deux taches sont deux operations : reconnaitre un besoin
  parmi des choses dont la plante n'a pas besoin ; prevoir ce qui arrive quand un besoin manque. L'enfant ne resout pas la seconde avec
  la premiere : savoir que la plante a besoin d'eau ne dit pas qu'elle se fane, ni que la graine reste une graine au froid.
- **`FER-2`** : 14 items, au-dessus du plancher de 11 (`DEC-0120` A.258).
- **`BPL-CE1-1`, les quatre mots** : ni *air*, ni *terre*, ni *engrais*, ni *sels mineraux* dans aucun champ lu.
- **`BPL-CE1-2`** : aucun champ ne lie la graine a la lumiere, en une phrase ou en deux ; aucune graine n'est privee de lumiere.
- **`QMT-CE1-1`** : aucun des quinze mots exclus. *Graine*, *pousse*, *temperature* sont admis ; *germer* est le verbe de *germination*,
  que la ligne nomme. *Temperature* n'est pas un mot difficile au CE1 (le thermometre de la classe, la meteo) : aucun exemple n'est du.
- **Lisibilite CE1, `LON-CE1-1`, `-2`, `-5`** : enonces de 7 a 13 mots, une ou deux phrases, la derniere est la question ;
  propositions de 2 a 6 mots, une phrase ; indices de 6 a 10 mots (au plus 11 apres correction). Aucun mot hors du niveau : *se fane*,
  *pale*, *germer*, *semee* sont des mots du jardinage de classe.
- **`TRV-CE1-6`** : aucun prenom, aucun foyer. Un seul personnage, *le jardinier* de l'item 13, retire par ma correction (2.1).
- **`TRV-CE1-1`** : niveau `CE1`, aucun `texteSupport`, aucun champ a voix haute.
- **Rang** : entree de rang 14 items, 42 propositions, compte exact (14 fois 3). Mes corrections n'ajoutent ni ne retirent d'item :
  l'entree garde ses totaux (`QC10`).
- **Accents, orthographe, ponctuation** : au `relecteur-pedagogique` (`DEC-0002`, `DEC-0056`). Je n'ai rien vu qui manque.

### 1.1 Les distracteurs *du sucre*, *du lait*, *du bruit* (`QC9`, critere 5)

**Confirmes, comme famille et item par item.** Ce sont les miens ; je les relis contre ce qu'un enfant de CE1 croit, pas contre ce que
j'ai ecrit.

- *Du sucre* et *du lait* sont les bons pieges : un enfant de CE1 prete a la plante ce qui le nourrit, lui. *"La plante mange comme
  nous"* est la conception que la ligne doit deplacer. Aucun n'est defendable : on n'apporte ni sucre ni lait a une plante pour
  qu'elle vive.
- *Du bruit* est le plus faible des trois, pas un piege absurde : *"parler aux plantes"*, *"la musique les fait pousser"* sont des
  croyances que les enfants rapportent. Je le garde. Il est dans 7 items besoin sur 9 ; je regarderai en jouant s'il s'ecarte d'emblee.
- **Correction de mon propre ecrit.** J'ai ecrit *"ce dont une plante n'a pas besoin (du sucre ...)"*. A la lettre, une plante a besoin
  de sucre : elle le fabrique elle-meme, avec la lumiere. Ce que ma condition veut dire, et que je lis ainsi depuis le debut : **ce
  qu'on n'apporte pas a une plante pour qu'elle vive**. Aucun item ne dit qu'une plante n'a pas de sucre ou n'en fabrique pas : rien
  ici ne sera contredit au college (`CYC-1` vers le haut). Ce n'est pas une condition neuve ; c'est ma lecture de mes mots, ecrite
  pour qu'on ne la deduise pas.
- **Ce que je regarderai en jouant, sans le demander** : la tache besoin oppose toujours un des trois besoins a deux des trois
  non-besoins. Apres quelques manches, l'enfant peut reconnaitre la liste au lieu de raisonner. C'est la tache que ma condition
  decrit ; je ne la durcis pas. Si le jeu montre que l'enfant ne lit plus l'enonce, ce sera une reserve neuve et datee.

### 1.2 `NRC-CE1-1` sur le sujet, et `CYC-1`

- **Aucun paquet de CP ne sert les besoins de la plante.** Je l'ai verifie au-dela du commentaire du paquet : `grep` des mots
  *plante*, *graine*, *arros*, *besoin*, *se fane* dans `cp-qlm-*.yaml`. Les seuls items de CP qui touchent une plante sont
  `cp-qlm-cycle-de-vie` (une plante vieille meurt, ses graines donnent de nouvelles plantes), `cp-qlm-vivant-non-vivant` (un arbre
  nait, grandit, a des graines), `cp-qlm-modes-de-vie` (le riz pousse dans l'eau, le bananier ne supporte pas le gel). Aucun ne pose la
  question d'un besoin ni d'une privation : **aucun item de ce paquet ne reprend la question ni la bonne reponse d'un item de CP**.
- **Le voisin que la story nomme, `cp-qlm-hygiene`**, parle du sucre et des besoins *du corps de l'enfant*. Un enfant de CP peut
  transposer *"trop de sucre n'apporte pas ce dont le corps a besoin"* a la plante, pour les items 1 et 13. Je ne retire pas ces items :
  la transposition du corps a la plante, *la plante aussi est un vivant qui a des besoins*, est le pas que la ligne de CE1 demande ; le
  paquet de CP ne le fait pas.
- **`CYC-1`** : rien ne contredit `cp-qlm-modes-de-vie` (le bananier et le gel vont avec *la chaleur* ; le riz et l'eau avec *l'eau*),
  ni `ce1-qlm-etapes-de-developpement` (la graine qui germe donne la jeune pousse ; la graine sans eau qui *ne devient pas une plante*
  est la meme suite, arretee), ni `cm1-sciences-chaine-alimentaire` (la plante y est mangee, ses besoins n'y sont pas). Le commentaire
  du paquet ne nomme que `cp-qlm-cycle-de-vie` ; `cp-qlm-modes-de-vie` porte aussi la plante et le froid. Observation, pas une demande.

## 2. Verdict par item

Numerotation : ordre du fichier, ligne de l'enonce. *Juste* : rien a changer. *A corriger* : je demande la correction avant `verify` ;
s'il restait tel quel a l'audit, ce serait une reserve. Aucun item n'est refuse en l'etat : aucun fait n'est faux, aucun item n'a deux
reponses defendables.

| # | Ligne | Tache, sujet | Item (enonce abrege ; bonne reponse ; distracteurs) | Verdict | Motif |
|---|---|---|---|---|---|
| 1 | `:60` | besoin, plante | besoin pour vivre et grandir ; *de l'eau* ; *du sucre*, *du bruit* | **juste** | Le libelle mot pour mot. Indice d'elimination : une methode |
| 2 | `:68` | privation d'eau, plante | *elle se fane* ; *elle grandit plus vite*, *elle devient plus verte* | **juste** | Fait sur. Indice *"Imagine une plante que personne n'arrose pendant longtemps"* : une situation a simuler, l'enfant trouve seul ce qui arrive |
| 3 | `:76` | besoin, plante | aide a bien pousser ; *la chaleur* ; *le bruit*, *le sucre* | **a corriger** | Indice *"Pense à ce qui réveille les plantes au printemps"* : periphrase de la reponse (2.1) |
| 4 | `:84` | besoin, plante a feuilles | *de la lumière* ; *du bruit*, *du lait* | **a corriger** | Indice *"Pense à ce que les feuilles reçoivent près d'une fenêtre"* : periphrase (2.1). L'enonce est juste et tient `BPL-CE1-2` a la lettre |
| 5 | `:92` | besoin, graine | germer ; *de l'eau* ; *du bruit*, *du lait* | **a corriger** | Fait sur (le coton mouille de la classe). Indice *"Pense à ce qu'on verse sur une graine semée"* : periphrase (2.1) |
| 6 | `:100` | privation de lumiere, plante a feuilles | *elle devient pâle et pousse mal* ; *elle devient plus verte*, *elle devient plus solide* | **juste** | Fait sur (3, `I3`). Les deux distracteurs sont les contraires de la bonne reponse. Indice : situation a simuler |
| 7 | `:108` | besoin, feuilles | *de la lumière* ; *du lait*, *du sucre* | **juste** | Les feuilles privees de lumiere restent petites : *"pour grandir"* est exact. Indice *"Pense aux plantes qui se tournent vers la fenêtre"* : une observation a refaire, pas le nom de la reponse |
| 8 | `:116` | besoin, plante au froid | *de la chaleur* ; *du lait*, *du bruit* | **a corriger** | Indice *"Pense à ce qui manque quand il fait très froid"* : la definition de la reponse, et l'enonce dit deja *"très froid"* (2.1) |
| 9 | `:124` | privation d'eau, graine | *elle ne germe pas* ; *elle devient une plante*, *elle donne des fruits* | **a corriger** | Fait sur. **Distracteur *elle donne des fruits*** : une graine ne donne jamais de fruits, avec ou sans eau. L'enfant l'ecarte par l'ordre de `-01`, sans rien savoir du besoin (2.2) |
| 10 | `:132` | besoin, plante a feuilles vertes | *de la lumière* ; *du sucre*, *du bruit* | **a corriger** | Indice *"Pense à ce que les feuilles vertes cherchent au soleil"* : periphrase (2.1) |
| 11 | `:140` | besoin, graine qui a deja de l'eau | *de la chaleur* ; *du bruit*, *du sucre* | **juste** | Fait sur, et c'est l'item que `BPL-CE1-2` rend possible : la graine a besoin d'eau **et** de chaleur, pas de lumiere. Indice *"Pense à la température d'un endroit où une graine germe"* : il nomme la grandeur a regarder, pas la reponse ; l'enfant juge seul si l'endroit est chaud ou froid |
| 12 | `:148` | privation de chaleur, plante | *elle arrête de pousser* ; *elle pousse plus vite*, *elle fait plus de fleurs* | **juste** | Fait sur (3, `I3`). Indice *"plein hiver"* : situation a simuler |
| 13 | `:156` | besoin, plante | a apporter souvent ; *de l'eau* ; *du lait*, *du sucre* | **a corriger** | Indice *"Pense à ce que le jardinier verse avec son arrosoir"* : un arrosoir verse de l'eau, c'est la reponse par la fonction de l'objet (2.1). Il porte aussi le seul personnage du paquet, un role au masculin (`TRV-CE1-6`) |
| 14 | `:164` | privation de chaleur, graine | *elle reste une graine* ; *elle donne des fleurs*, *elle pousse plus vite* | **a corriger** | Fait sur, et bien dit : la graine ne meurt pas, elle attend. **Distracteur *elle donne des fleurs*** : meme motif que l'item 9 (2.2) |

**Sur le numero de l'item de l'arrosoir.** La demande qui m'a ete transmise le nomme *item 14*. Dans l'ordre du fichier, c'est
l'item 13 (`:156`, indice `:159`) ; l'item 14 est la graine au grand froid. Je numerote par l'ordre du fichier et je donne la ligne.

### 2.1 Six indices qui disent la reponse (items 3, 4, 5, 8, 10, 13)

`TRV-CE1-5` : *"une methode, jamais la reponse"*. Ma lecture de `-01` (`arbitrage-nadia-indices-ce1-qlm-etapes-de-developpement.md`,
section 3) : *"L'indice n'a pas a dire quelle etape chercher : la dire serait donner la reponse. Il donne une methode, que l'enfant
peut appliquer seul."* Je l'applique ici sans la changer.

**Le critere, lisible par un tiers** : un indice de la forme *"Pense à ce que (ce qui) ..."* dont l'objet designe est la bonne
reponse est une periphrase de la reponse. *"Ce que le jardinier verse avec son arrosoir"*, c'est l'eau ; *"ce qui manque quand il fait
très froid"*, c'est la chaleur ; *"ce que les feuilles reçoivent près d'une fenêtre"* et *"ce que les feuilles vertes cherchent au
soleil"*, c'est la lumiere ; *"ce qui réveille les plantes au printemps"*, c'est la chaleur ; *"ce qu'on verse sur une graine semée"*,
c'est l'eau. L'enfant n'a rien a raisonner : il traduit.

**Ce qui est une methode, dans ce paquet meme** : les cinq indices de la tache privation (*"Imagine une plante ..."*), qui posent une
situation a simuler, et les indices des items 1, 7 et 11 (eliminer ; refaire une observation ; regarder une grandeur). Mes
remplacements prennent ces trois formes : comparer deux situations, imaginer une privation, eliminer.

**Pourquoi je ne le tranche pas plus tot** : ma condition le disait, le premier jet ne la tient pas sur six items. Ce n'est pas une
barre neuve. La garde de `TRV-CE1-5` compte les mots et refuse un indice repete ; elle ne lit pas si un indice designe la reponse.
**Non garde, a ma lecture.**

### 2.2 Les effets impossibles au sujet (items 9, 14)

*Elle donne des fruits* et *elle donne des fleurs* pour une graine : aucune graine ne le fait, quoi qu'il lui manque. C'est le motif de
ma lecture de `-01`, section 2.1 (le tetard dans la vie d'une plante) : un distracteur qui s'ecarte sans la competence en jeu fait de
l'item un choix entre deux. Le remplacement est un contraire de la bonne reponse, comme *elle devient une plante* que l'item 9 porte
deja et qui est le meilleur piege de la tache : c'est ce qui arrive **avec** de l'eau.

## 3. Les six interpretations de `marc-dev`

| Code | Interpretation | Reponse | Motif |
|---|---|---|---|
| `I1` | La garde des quatre mots refuse aussi l'enonce et l'indice, plus stricte que *"proposes"* | **confirmee, a garder** | Ma condition ecarte ces mots parce qu'ils sont de vrais besoins hors de la parenthese (air, sels mineraux, engrais) ou un besoin discute (la terre). Dans un indice, ils menent l'enfant vers eux (sonde 20, *"Pense à l'air autour de la plante"*) : c'est le meme dommage que dans une proposition. Dans un enonce, un decor (*"semée dans la terre"*) n'affirme rien, mais aucun item de ce paquet n'en a besoin, et la garde ne refuse rien d'ecrit. Meme position que pour la garde plus stricte de `-01` (ma lecture, 4.3). **Clause** : si une correction future a besoin d'un de ces mots dans un decor, elle me revient ; la garde ne s'assouplit pas en silence |
| `I2` | Famille b de la tache privation : des consequences que la privation n'a jamais, *"contraire ou sans rapport"* | **reformulee** | Ma condition ne nommait les distracteurs que pour la tache besoin ; la tache privation applique *"une seule acceptable"* (`TRV-CE1-2`) et la plausibilite que je demande a tout distracteur. Famille b, texte a ecrire en 5, point 9 : **le contraire de la consequence** (ce qui arriverait s'il ne manquait rien, ou mieux encore) ; **jamais une consequence que la privation a vraiment** ; **jamais un effet impossible au sujet quel que soit le besoin**. *"Sans rapport"* est retire : c'est la porte de *elle donne des fruits*. Un cas a connaitre : **dans le noir, la tige d'une plante s'allonge vite en cherchant la lumiere** ; *elle grandit plus vite* ou *elle pousse plus vite* ne va donc jamais dans un item de lumiere, ce serait une seconde reponse defendable. Aucun item ne le fait aujourd'hui ; la table `EFFETS_CONTRAIRES` est commune aux trois privations et ne l'empeche pas. **Non garde, a ma lecture** ; la forme, si on la veut, est a `theo-archi` |
| `I3` | Les cinq consequences, sans source externe (`QMT-CE1-2`) ; deux a relire | **confirmee ; la source s'ecrit autrement** | Les cinq sont justes au CE1, et ce sont celles des experiences de classe (coton sec et coton mouille, placard, frigo). *La plante a feuilles dans le noir devient pale et pousse mal* : exact, c'est l'etiolement (feuilles jaunes et petites, tige longue et molle) ; *pousse mal* couvre la tige qui s'allonge sans forcir. *La privation de chaleur lue comme "il fait tres froid"* : c'est la seule lecture possible au CE1, et je la tiens. *Elle arrete de pousser* est vrai de toute plante au grand froid (le gel peut aussi la tuer, ce qui ne contredit pas l'item) ; *elle reste une graine* est vrai et dit ce qu'il faut : la graine ne meurt pas, elle germera quand il fera plus chaud. **La source** : *"le redacteur n'a consulte aucune source externe ... celles que le test ecrit"* fait du test la source de lui-meme. Comme a `-01`, ou la liste etait la mienne, la source devient ma lecture : texte en 5, point 8 |
| `I4` | *"La lumiere porte sur la plante qui a deja des feuilles"* lue comme : l'enonce de chaque item de lumiere nomme les feuilles (4 items) | **confirmee** | C'est ce que ma condition veut : que la lumiere ne soit jamais demandee pour une graine, et qu'elle le soit pour une plante qui a de quoi la recevoir. Nommer les feuilles dans l'enonce le rend lisible par l'enfant et par le test. Le sens inverse n'est pas demande : un item d'eau peut nommer les feuilles. Ce que le test ne voit pas et que je lis : que les feuilles soient celles de la plante de l'enonce. Les quatre items (4, 6, 7, 10) le tiennent |
| `I5` | Bonne reponse strictement la plus longue dans 7 items sur 14, le plafond | **7 sur 14, mesure par script ; mes corrections n'y touchent pas** | Items 3, 4, 6, 7, 8, 10, 11. Les indices ne comptent pas ; mes deux distracteurs neufs laissent les items 9 (17 contre 23 et 20) et 14 (21 contre 24 et 21) hors du compte. Recompte sur une copie corrigee : **7 sur 14**. Ce compte est au plafond de `TRV-CE1-2` (*"la moitie au plus"*) : toute correction qui rend la bonne reponse strictement la plus longue dans un des items 1, 2, 5, 9, 12, 13, 14 le fait rougir, et se recompte dans le meme commit |
| `I6` | Deux items de graine (eau, chaleur), aucun sur la lumiere | **conforme a `BPL-CE1-2` ; le compte est quatre** | Quatre items portent la graine : 5 et 11 (besoin, eau puis chaleur), 9 et 14 (privation, eau puis chaleur). Chacun porte sur l'eau ou la chaleur, aucun sur la lumiere, aucune graine n'est privee de lumiere. *"Deux"* est le compte de la tache besoin seule |

## 4. Question `S4` de `theo-archi` (story `-02`)

Je la traite ici : elle ne demande que la lecture de mon propre texte du critere 6 (`avis-nadia-seconde-tache-ce1-qlm-02.md`, section
5), sans rien ouvrir de `-03`.

- **`S4` est confirme.** Mon texte de la tache A dit *"les trois propositions sont des vegetaux, d'autres animaux, les deux"*. Un item
  dont la bonne reponse est une categorie et dont un distracteur est un aliment melange les deux operations que la tache B doit
  separer : il gonfle A sans la poser. Ce n'est pas une condition neuve, c'est la symetrie de la sonde 3 que j'ai ecrite.
- **Les paraphrases entrent dans l'ensemble ferme, et ce sont celles-ci** : *des plantes* (pour *des vegetaux*) ; *des animaux*,
  *de la viande* (pour *d'autres animaux*) ; *un peu des deux*, *les deux à la fois*, *de tout* (pour *les deux*). Chacune nomme une
  categorie entiere avec d'autres mots. En tache B, elle serait soit la categorie deguisee que mon texte exclut, soit une seconde
  reponse defendable (*des plantes* a cote de *de l'herbe* pour la vache ; *de la viande* a cote de *des souris* pour la chouette). Six
  lignes, par egalite apres la normalisation de `theo-archi` (2.2), jamais par sous-chaine.
- **Ce qui reste a ma lecture** : toute autre paraphrase, et la frontiere entre un aliment et une categorie (*des insectes*, *des
  graines*, *de l'herbe* sont des aliments, pas des categories : ils n'entrent pas).
- A `marc-dev` par `sofia-sm` pour le test de `-02` ; a `camille-po` si elle veut le porter dans la story.

## 5. Ce que je demande a `marc-dev`

Texte exact, dans `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-besoins-de-la-plante.yaml`. Rien d'autre ne change :
ni l'enonce, ni la bonne reponse, ni les declarations des items. Aucun texte neuf ne porte d'espace avant `?` ou `:`.

| Point | Item, ligne | Texte actuel | Texte a ecrire |
|---|---|---|---|
| 1 | 3, `:79` | `Pense à ce qui réveille les plantes au printemps.` | `Compare une plante en hiver et la même plante au printemps.` |
| 2 | 4, `:87` | `Pense à ce que les feuilles reçoivent près d'une fenêtre.` | `Imagine cette plante dans un placard fermé, puis près d'une fenêtre.` |
| 3 | 5, `:95` | `Pense à ce qu'on verse sur une graine semée.` | `Écarte ce qu'on ne donne jamais à une graine semée.` |
| 4 | 8, `:119` | `Pense à ce qui manque quand il fait très froid.` | `Imagine la même plante un jour d'été.` |
| 5 | 10, `:135` | `Pense à ce que les feuilles vertes cherchent au soleil.` | `Compare une plante au soleil et une plante à l'ombre.` |
| 6 | 13, `:159` | `Pense à ce que le jardinier verse avec son arrosoir.` | `Imagine une plante de la classe oubliée pendant les vacances.` |
| 7a | 9, `:126` | `"elle donne des fruits"` | `"elle germe plus vite"` |
| 7b | 14, `:166` | `"elle donne des fleurs"` | `"elle germe tout de suite"` |

Les six indices sont distincts entre eux et des huit autres ; 7 a 11 mots (`TRV-CE1-5`, douze au plus) ; aucun ne porte un des quatre
mots de `BPL-CE1-1` ni ne lie la graine a la lumiere. L'indice du point 3 garde la graine sans la lumiere : je n'ai pas pris le sachet
de graines, qui est sombre et menerait l'enfant vers la lumiere (`BPL-CE1-2`).

8. **Commentaire, lignes `:28` a `:34`**, remplacer le paragraphe *"Source (QMT-CE1-2) : ..."* par :

```
# Source (QMT-CE1-2) : les trois besoins sont ceux de la parenthese du libelle. Les cinq consequences
# de la tache privation, une par couple (sujet, besoin retire), sont celles que nadia-prof a relues et
# tenues pour exactes au CE1 (squad/sprints/s57-version-cp-ce1-cm1-trois-instances/
# lecture-nadia-premier-jet-ce1-qlm-besoins-de-la-plante.md, section 3, I3) : la plante privee d'eau
# se fane ; la plante a feuilles privee de lumiere devient pale et pousse mal ; la plante privee de
# chaleur (il fait tres froid) arrete de pousser ; la graine privee d'eau ne germe pas ; la graine
# privee de chaleur reste une graine. Le test PaquetCe1QlmBesoinsDeLaPlanteTest les ecrit dans sa
# table CONSEQUENCES et relit le paquet sur elle.
```

9. **Commentaire, ligne `:43`**, remplacer la ligne de la famille b par :

```
#   famille b : le contraire de la consequence (ce qui arriverait s'il ne manquait rien, ou mieux
#   encore) ; jamais une consequence que la privation a vraiment ; jamais un effet impossible au
#   sujet quel que soit le besoin (une graine ne donne ni fleurs ni fruits)
```

10. **Test du paquet** : la table `EFFETS_CONTRAIRES` doit admettre *elle germe plus vite* et *elle germe tout de suite* ; ce qu'elle
    garde ou retire d'autre, et la forme, sont a `marc-dev` et `theo-archi`. Le nombre d'items ne change pas (14) : l'entree de rang
    garde ses totaux. Le compte de `TRV-CE1-2` reste 7 sur 14 (`I5`).

**Ce dont je reste la seule garde sur ce paquet, apres correction** : l'exactitude des cinq consequences et des trois besoins ; la
plausibilite de chaque distracteur, dont la regle de `I2` sur la lumiere et l'etiolement ; qu'un indice soit une methode et pas une
periphrase de la reponse ; `NRC-CE1-1` sur le sujet et `CYC-1` ; l'operation mentale de `FER-3` ; les paraphrases de `BPL-CE1-2` en
deux phrases. **Aucune n'est couverte par un test.**

## 6. Signale, hors de mon perimetre

- **A `theo-archi`, symptome** : dans `verifierQueLaGraineNAPasBesoinDeLumiere`, le motif `LUMIERE` (`lumière|soleil`) est applique au
  texte brut, alors que les quatre mots de `BPL-CE1-1` le sont au texte sans accent. Une bonne reponse *"de la lumiere"* ecrite sans
  accent pour une graine ne serait pas vue par cette garde. Le `relecteur-pedagogique` la refuserait pour l'accent ; je decris le
  symptome, la cause et la forme sont a `theo-archi`.

## 7. Cout

**Au bareme** (1,0 par lot, *"lecture avant `verify` et correction qui la suit comprises"*) : **1,0** a cette ecriture. Mes
corrections touchent huit items : si `marc-dev` les applique telles qu'ecrites, une seconde lecture courte suffit, a 0,5 **hors marge**
(`DEC-0120` A.270, point 4 : toute seconde lecture avant `verify` d'un lot de l'instance neuve), soit **1,5**. Le seuil de 1,67 a ete
ecrit pour `-01` (`planning.md:2483`) ; aucun seuil propre a `-03` n'est ecrit. Lu par analogie, il n'est franchi ni a cette ecriture
ni a 1,5 ; il ne le serait qu'a une troisieme lecture. Je ne convertis pas une duree en bareme : aucune regle ne le fait.

## 8. Duree de lecture

Mesuree par `date` : debut **2026-10-26 14:31:36**, ecriture de ce fichier 14:47:00, fin **14:54:28**, avant le commit. **Duree :
22 min 52 s**, dont la mesure du test du paquet et celle du garde-fou des identifiants
(`bash outils/mvn-jdk21.sh -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` : *"Tests run: 10,
Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS`, ce fichier present dans l'arbre ; ce fichier n'ecrit aucun identifiant neuf).
Aucun seuil de duree ne s'applique a une lecture : le seul seuil de duree du sprint est celui de 41 h 38 min par palier (`D4`), qui
ne se compte pas sur un geste. Au bareme, section 7.

## 9. Routage

| A qui | Quoi |
|---|---|
| `marc-dev` | section 5, points 1 a 10 |
| `theo-archi` | `I2` (la lumiere et *elle grandit plus vite*, non garde ou forme) ; section 6 |
| `marc-dev`, par `sofia-sm` ; `camille-po` | section 4 (`S4`, les six paraphrases), pour `-02` |
| `sofia-sm` | le cout est en section 7 ; la ligne de seconde lecture, s'il y en a une, s'inscrit sur la ligne hors marge |

## 10. Ce que ce document ne fait pas

Il ne prononce aucun Done et aucun verdict de lot, n'ecrit aucune condition neuve, n'alloue aucun identifiant, ne modifie ni le
paquet, ni un test, ni le catalogue, ni `DEC`, ni matrice, ni registre, ni backlog.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-26.
