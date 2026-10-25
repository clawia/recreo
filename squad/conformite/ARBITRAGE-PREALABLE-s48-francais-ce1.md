# Arbitrage préalable - s48, instance B : le référentiel de français CE1, vingt-six lignes
# (`FRA-CE1-01` à `FRA-CE1-26`)

- **Auteure** : `nadia-prof`. **Date** : 2026-10-03. **Sprint** : s48, instance B, hors chiffrage
  de la borne de l'instance A (`DEC-0106`, section 4).
- **Ce qui m'est posé** : le Jugement de faisabilité ligne par ligne du référentiel de français CE1,
  intégré à la matrice par `DEC-0104` (section 1.2) sans qu'aucune de ses lignes ait été instruite,
  et deux points nommés : les bornes de lisibilité au CE1 (`ARBITRAGE-s36-longueur-d-enonce-au-ce1.md`
  s'applique-t-il tel quel au français ?) et la borne de longueur du texte support.
- **Ce document ne prononce aucun Done**, pédagogique ou technique, ne crée ni ne déplace aucune ligne
  de `squad/programme/MATRICE-couverture.md`, n'écrit ni backlog ni garde-fou, **ne fixe aucun tarif
  et ne rend aucun avis de faisabilité technique**.
- **Je n'ai rien joué, et ce n'est pas un audit dégradé : il n'y a rien à jouer.** Aucun paquet de
  français CE1 n'existe. Ce que j'ai fait à la place : relu les trois référentiels de français (CP,
  CE1, CM1) et la section "Français - CE1 (cycle 2)" de la matrice ; relu mes arbitrages de s36 et de
  s47 sur les longueurs ; relu le code des deux garde-fous de longueur ; **lu les items servis** des
  paquets voisins (`cp-le-bon-son`, `cp-digraphe-du-son`, `cp-lettre-du-son`, `cp-nature-mot`,
  `cp-pluriel`, `cp-mot-manquant`, `cp-texte-narratif`, `cp-ranger-nombres`, `cm1-homophones`,
  `cm1-accords-groupe-nominal`, `cm1-accord-sujet-verbe`, `cm1-conjugaison-present`, `cm1-reperes`,
  `cm1-synonymes`, `cm1-antonymes`, `cm1-substituts`) pour vérifier les collisions sur des items réels ;
  et **mesuré** la longueur des propositions servies en français (section 1.3).
- **Lien** : `DEC-0104`, `DEC-0105`, `DEC-0106` ; `squad/programme/francais/competences-ce1.md` ;
  `ARBITRAGE-s36-longueur-d-enonce-au-ce1.md` (`LON-CE1-1`, `LON-CE1-2`) ;
  `ARBITRAGE-s47-longueur-du-texte-support-au-ce1.md` (`LON-CE1-3`, `LON-CE1-4`) ;
  `ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` (modèle de ce document) ;
  `ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` (`NAR-5` à
  `NAR-10`) ; `ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md` (`SAI-11`) ;
  `ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md`.

---

## 0. Trois corrections avant de m'appuyer sur le dossier

**0.1 - La borne du texte support n'est plus "non posée".** `DEC-0106` (section 4) et le planning
de s48 (section 4) me demandent de statuer sur une borne "que `DEC-0104` section 1.1 signale non
posée". C'était vrai le 2026-10-02 au matin. **Je l'ai posée le même jour** :
`ARBITRAGE-s47-longueur-du-texte-support-au-ce1.md`, `LON-CE1-3` (au plus 30 mots) et `LON-CE1-4`
(au plus quatre phrases, aucune de plus de 12 mots), alloués au registre. Le référentiel le dit
(section "Ce que ce référentiel ne dit pas") et la matrice aussi (note du 2026-10-02). Les deux
documents de s48 citent donc un état périmé d'un jour. **Je ne repose pas cette borne et je ne la
déplace pas** : aucune équipe ne l'a encore atteinte, mais une borne réécrite à chaque fois qu'on me
la redemande cesse d'être une borne. Section 1.2 pour ce que j'en vérifie. Correction routée à
`camille-po` (section 10) : `DEC-0106` est sa décision, pas la mienne.

**0.2 - `FRA-CE1-27` à `FRA-CE1-31` n'ont pas été écartées par `DEC-0104`.** `DEC-0104`, section
1.1 (B1), m'a **routé** ces cinq attendus et dit explicitement ne pas leur allouer de code. C'est moi
qui les ai codés et déclarés `non couvrable en l'état` le 2026-10-02, motif écrit sur chaque ligne de
la matrice (voix de l'adulte, voix ou geste de l'enfant, texte libre). **Elles ne font pas partie des
vingt-six lignes instruites ici** et je ne les réinstruis pas : leur verdict tient. C'est aussi ce
qui explique la section 12 : les attendus que ce produit ne peut pas servir ont déjà été sortis du
référentiel instruit.

**0.3 - Le garde-fou de `LON-CE1-2` lit ma clause plus étroitement que je ne l'ai écrite, sur deux
points.** Je l'ai vérifié dans le code plutôt que de le supposer
(`AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest`, méthode `defautsDeForme` ; découpage des
phrases dans `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`, motif `FIN_DE_PHRASE`) :

1. **Il n'exige que la dernière phrase interroge qu'à partir de deux phrases.** Un énoncé d'une
   seule phrase non interrogative est vert. C'est **ce que je voulais** : le troisième exemple de mon
   propre tableau de s36, *"Range ces nombres du plus petit au plus grand : 308, 83, 380, 38."*, est
   une phrase unique, impérative, et je l'ai comptée conforme. Mon texte de s36 disait plus que mon
   exemple ; le garde-fou suit l'exemple, et il a raison.
2. **Il ne coupe pas une phrase après un point suivi d'un guillemet fermant.** Une fin de phrase est
   un `.`, `?` ou `!` suivi d'une majuscule ou de la fin du texte ; dans `lait. » Quel`, le point est
   suivi de `»`. Une phrase citée entre guillemets puis la question comptent donc **une** phrase pour
   le garde-fou, **deux** pour moi. Au CE1 de mathématiques, aucun énoncé ne cite de phrase ; en
   français, presque tous le feront. Conséquence en section 1.1.

---

## 1. Les bornes de lisibilité au CE1, revérifiées pour le français

### 1.1 `LON-CE1-1` et `LON-CE1-2` s'appliquent au français telles quelles

**Je n'adapte pas la borne d'énoncé, et je l'ai vérifié au lieu de le présumer.** En français, l'énoncé
porte deux choses à la fois : la **phrase objet** (celle dont on cherche le sujet, l'erreur, le type)
et la **question**. C'est le risque propre à cette matière. Cinq énoncés, comptés sous la convention
d'`ADR-0089` reprise sans changement par s36 :

| Énoncé | Mots | Phrases (ma lecture) | Phrases (garde-fou) | Ligne |
|---|---|---|---|---|
| *« Les petits chats boivent du lait. » Quel est le sujet du verbe « boivent » ?* | 13 | 2 | 1 | `FRA-CE1-13` |
| *« Les enfants joue dans la cour. » Quel mot est mal écrit ?* | 11 | 2 | 1 | `FRA-CE1-09` |
| *dort / chat / Le / lit / le / sur / .* | 6 | 1 | 1 | `FRA-CE1-08` |
| *« venir » : vous ___ avec nous.* | 5 | 1 | 1 | `FRA-CE1-17` |
| *« Quelle belle journée ! »* | 3 | 1 | 1 | `FRA-CE1-10` |

**Le plus long fait 13 mots.** Vingt mots laissent la place à une phrase objet de huit à dix mots et à
sa question, ce qui est déjà la limite haute de ce qu'un enfant de CE1 doit tenir en tête pour
analyser une phrase. Aucune ligne de ce référentiel ne demande davantage. **Ce que la borne refuse,
en français** :

> *« Ce matin, avant de partir à l'école, Léo et sa petite sœur mangent des tartines. » Quel est le
> sujet du verbe « mangent » ?*

**22 mots** : il échoue sur `LON-CE1-1`. C'est la phrase objet qui déborde, et elle déborde
précisément là où l'analyse devient difficile (sujet coordonné, complément en tête). `VSJ-CE1-2` la
refuserait aussi pour son fond.

**Précision sur `LON-CE1-2`, pas un amendement.** *"La dernière est la question"* se lit comme mon
exemple de s36 et comme le garde-fou la lit : **quand l'énoncé compte plusieurs phrases, la dernière
est celle qui interroge** ; un énoncé d'une seule phrase peut être la phrase objet ou une
instruction, la question étant alors portée par la consigne du jeu. C'est la forme de
`cp-nature-mot`, et la plupart des items de grammaire de CE1 la prendront. Aucune barre ne bouge :
c'est la lecture que le garde-fou exécute depuis s36 sur 144 énoncés de CE1.

**Le point 2 de la section 0.3 ne change aucun verdict, et je dis pourquoi.** Une phrase citée puis la
question font deux phrases ; trois phrases autorisées laissent donc encore une phrase de contexte. Un
énoncé que le garde-fou compterait sous la barre et moi au-dessus devrait citer **trois** phrases avant
la question : aucune ligne de ce référentiel n'en demande autant, et `LON-CE1-1` l'arrêterait presque
toujours à vingt mots. **Je tiens cet écart à la main, en comptant**, et je le route comme
observation (section 10), pas comme demande.

### 1.2 `LON-CE1-3` et `LON-CE1-4` s'appliquent telles quelles aux quatre lignes de compréhension

La borne du texte support gouverne `FRA-CE1-04` à `FRA-CE1-07`, et elles seules. J'ai relu mes deux
textes d'exemple de s47 contre les conditions de la section 3 : ils restent servables sous `RCT`,
`PRN`, `INF` et `DOC`. **Un défaut que je trouve dans l'un d'eux, et je le dis** : sur le texte du
hérisson, la question *"Quand le hérisson se réveille-t-il ?"* admet deux réponses lisibles dans le
texte (*au printemps*, à la sortie de l'hiver ; *la nuit*, chaque jour). Le texte reste conforme à la
borne ; c'est la question qui serait fautive, et c'est exactement la propriété qu'aucun compteur ne
voit (section 9, point 2).

**Le manque que s47 laissait ouvert ne s'ouvre pas en français CE1.** J'écrivais alors qu'un texte
support sur une autre ligne *"demandera sa propre décision"*. Je la prends ici : **aucune autre ligne
de français CE1 ne porte de `texteSupport`** (`TRV-CE1-1`). La phrase objet des lignes de grammaire,
d'orthographe et de vocabulaire vit dans l'énoncé, sous `LON-CE1-1`. Une seule borne par champ, pas
deux bornes qui se disputent la même phrase.

### 1.3 Une borne neuve : la longueur d'une proposition

**Le manque est réel et propre au français.** En mathématiques, une proposition est un nombre. En
français CE1, quatre lignes (`FRA-CE1-04`, `-08`, `-09`, `-11`) proposent des **phrases** ou des
groupes de mots, et l'enfant en lit trois avant de choisir. s36 a exclu les propositions de sa borne,
avec un motif juste : je n'avais pas compté cette population. **Je l'ai comptée aujourd'hui**, sur
les propositions servies en français (script de poste non commité, convention d'`ADR-0089`,
bonne réponse et distracteurs) :

| Niveau | Propositions | Médiane | 95e centile | Maximum |
|---|---|---|---|---|
| CP | 521 | 1 mot | 2 mots | 4 mots (`cp-texte-narratif`) |
| CM1 | 610 | 1 mot | 3 mots | 6 mots (`cm1-comprehension`) |

> **`LON-CE1-5`** - au CE1, en français, une proposition compte **au plus six mots** et **une seule
> phrase**.

**Pourquoi six.** Le CE1 a besoin de phrases entières là où le CP et le CM1 servis n'en proposent
presque pas : *"Le chat dort sur le lit."* (6), *"Léo n'aime pas les épinards."* (5), *"Inès ouvre son
parapluie."* (4). Six mots couvrent ces trois formes et restent au maximum que le CM1 sert aujourd'hui :
un enfant de CE1 ne lit pas trois propositions plus longues que celles qu'on donne à un enfant de CM1.
**Ce qu'elle refuse** : *"Le petit chat noir dort sur le lit."*, 8 mots, proposé à côté de deux
variantes de même longueur - 24 mots à comparer mot à mot avant de répondre.

**Population** : la bonne réponse et les distracteurs de tout item d'un paquet dont le descripteur porte
`CE1` et la matière français. **Elle est vide aujourd'hui.** La clause se décide sur l'item seul. **Elle
ne vaut pas pour les mathématiques CE1**, dont la population existe et que je n'ai pas mesurée : une
borne étendue à une population non comptée serait une règle que personne n'a décidée. Si le premier
paquet montre que six était trop haut, j'ouvrirai une réserve neuve et datée ; je ne durcirai pas
`LON-CE1-5`.

### 1.4 Ce qu'un item de français CE1 fait lire, au plus

Sur une ligne de compréhension : 30 mots de texte, 20 d'énoncé, 3 propositions de 6, soit **68 mots au
plus**. Ailleurs : **38 mots au plus**. Ce sont des plafonds, pas des cibles ; mes exemples tiennent
entre 15 et 40.

---

## 2. Sept conditions transverses, valables pour chacune des vingt-six lignes

Elles s'ajoutent aux conditions propres à chaque ligne, sans les remplacer.

> **`TRV-CE1-1` - déclaration et supports.** Le descripteur porte `niveau: "CE1"` ; chaque item déclare
> une ligne `FRA-CE1-nn` et une seule (`ADR-0081`). `LON-CE1-1`, `LON-CE1-2` et `LON-CE1-5`
> s'appliquent à tout item ; `LON-CE1-3` et `LON-CE1-4` au `texteSupport`. **Seules `FRA-CE1-04` à
> `FRA-CE1-07` portent un `texteSupport`.** **Aucun item ne porte de champ à voix haute** : la prémisse
> de l'adulte qui lit (`DEC-0046`, `DEC-0068`) n'est arbitrée que pour le CP, et je ne l'étends pas
> par ce document.

**Annotation du 2026-10-25 (`nadia-prof`, s57, palier 5), sur `TRV-CE1-1`.** Le texte ci-dessus reste
tel qu'écrit le 2026-10-03. Depuis le 2026-10-21, sa phrase *"Aucun item ne porte de champ à voix
haute"* souffre **deux exceptions, et deux seulement** : le champ `texteAVoixHaute` est admis sur
`FRA-CE1-27` (*écouter et comprendre un texte lu par l'adulte*) et sur `FRA-CE1-29` (*écrire sous la
dictée*). Partout ailleurs au CE1, il reste refusé.

- **Source** : la réponse de l'utilisatrice à `Q-08` (*"oui, prémisse étendue"*), consignée dans
  `DEC-0123`, section 5, qui limite l'extension à ces deux lignes nommées ; mon arbitrage
  `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/arbitrage-nadia-q05-q10.md`, section 3, qui
  a décidé cette modification le 2026-10-21. Je l'écris ici quatre jours plus tard, après deux
  reports à ma charge (`arbitrage-nadia-palier-4.md`, 8.2 ; `arbitrage-nadia-palier-5-qlm-v3.md`,
  section 9).
- **La borne** (`DEC-0068`, A.1) : l'adulte lecteur n'est admis que là où la voix est la compétence.
  Ces deux lignes la remplissent. L'extension ne vaut pour aucune autre ligne de CE1, ni pour la
  lisibilité d'aucun paquet : la question et les propositions que l'enfant lit restent sous
  `LON-CE1-1`, `LON-CE1-2` et `LON-CE1-5`. Une troisième ligne demanderait une nouvelle réponse de
  l'utilisatrice (`DEC-0123`, section 5).
- **Ce que l'annotation ne fait pas** : elle n'écrit aucune condition de Done. Celles des deux lignes
  vivent dans `arbitrage-nadia-q05-q10.md`, 3.1 et 3.2, et dans `arbitrage-nadia-palier-4.md`, 8.2,
  avec leur plafond `en cours` et ce qui les bloque encore. Elle n'ouvre aucun paquet et ne prononce
  aucun Done. Elle n'alloue aucun identifiant.
- **Ce qu'elle rend périmé dans ce document, sans l'effacer** : en section 0.2, *"leur verdict
  tient"* ne vaut plus pour `FRA-CE1-27` (moitié *écouter*) et `FRA-CE1-29` (mots sous la dictée),
  passées de `non couvrable en l'état` à `non couvert` le 2026-10-21 (`DEC-0120`, A.135, note de
  `MATRICE-couverture.md`) ; en section 8, *"Il n'étend pas la prémisse de l'adulte qui lit au CE1"*
  reste vrai de ce document, l'extension venant de l'utilisatrice et non de lui.

> **`TRV-CE1-2` - propositions.** Trois propositions, ou deux là où la ligne est un choix binaire par
> nature (`FRA-CE1-02`, `-16`, `-22`, forme déjà servie par `cm1-homophones`). Deux à deux
> distinctes, **une seule acceptable**. Chaque distracteur appartient à une famille nommée par la
> condition de sa ligne, et le fichier nomme la famille à côté de l'item, comme `TAB-CE1-6` et
> `PRO-CE1-5`. **Sur le paquet entier, la bonne réponse n'est la proposition strictement la plus longue
> que dans la moitié des items au plus.** Motif : le garde-fou du rang de la bonne réponse ne lit que
> des propositions numériques ; en français, le biais qui le remplace est la longueur, et la négation
> (`FRA-CE1-11`) le porte par construction (*"Il ne mange pas"* est toujours plus long que *"Il mange
> pas"*).

> **`TRV-CE1-3` - un son se désigne par un mot.** Aucune notation phonétique, aucun graphème isolé
> employé comme nom d'un son (*"le son ain"*). Un son est toujours désigné par un **mot de référence**
> entre guillemets, que l'enfant lit (*"comme dans « main »"*). **Aucun item ne dépend d'une
> prononciation qui varie selon les régions** : opposition é/è, o ouvert ou fermé, `brin`/`brun`,
> lecture de `les`, `des`, `mes`, `ses`, `ces`, `est`.

> **`TRV-CE1-4` - les mots de grammaire que l'enfant lit.** Dans l'énoncé, l'indice et les
> propositions, **sont exclus** : *graphème*, *phonème*, *préfixe*, *suffixe*, *radical*,
> *participe*, *auxiliaire*, *complément*, *antonyme*, *homophone*, *inférence*. Ce sont des mots que
> l'adulte peut employer, pas des mots que l'enfant de CE1 lit seul. *Nom*, *verbe*, *déterminant*,
> *adjectif*, *sujet*, *phrase*, *majuscule*, *point*, *pluriel*, *singulier*, *masculin*, *féminin*,
> *présent*, *passé*, *futur*, *passé composé*, *contraire*, *famille*, *rime*, *ordre alphabétique*
> et les noms des trois types de phrase sont admis : ils sont nommés par les lignes elles-mêmes.

> **`TRV-CE1-5` - indice et explication.** L'indice donne une méthode ou dit où regarder, **jamais la
> réponse**, **douze mots au plus**, et il n'est pas identique sur tout le paquet (reprise de
> `PRO-CE1-7`). Il n'enseigne jamais une règle fausse. **Quand l'énoncé montre une forme écrite fautive**
> (`FRA-CE1-09`), l'explication redonne la forme juste : un enfant ne quitte pas une manche avec, sous
> les yeux, la dernière phrase fausse qu'on lui a montrée.

> **`TRV-CE1-6` - personnages et situations.** Reprise de `NAR-7`, `NAR-8` et `PRO-CE1-6` : prénoms
> équilibrés en genre à un près sur le paquet et variés dans leurs origines ; aucun rôle, aucune
> activité, aucune émotion distribués par genre ; rien de ce qui s'adresse à l'enfant (*"tu"*) ne
> suppose une configuration familiale, un logement, un jardin, un animal, des vacances ou un objet
> possédé.

> **`TRV-CE1-7` - volume.** **Au moins vingt items** par paquet, et deux items ne partagent jamais le
> même `texteSupport` (reprise de `NAR-10`).

---

## 3. Lecture et compréhension de l'écrit (`FRA-CE1-01` à `FRA-CE1-07`)

### 3.1 `FRA-CE1-01` - lire des mots à graphèmes complexes et les associer à leur son

**Verdict : ACCEPTÉ SOUS CONDITIONS `GRC-CE1-1` à `GRC-CE1-3`.** Plafond : `couvert-valide`.

**Ce que la ligne demande, sans voix.** Au CP, l'association graphème/phonème a été servie avec un
adulte qui dit le mot. Au CE1, je ne l'ai pas (`TRV-CE1-1`). La ligne reste servable, parce que le
son peut être porté par un **mot de référence que l'enfant lit déjà** :

> *Quel mot contient le même son que « main » ?* - **plein** / plaine / maman

8 mots. Pour répondre, l'enfant doit **lire** *plein* et *plaine*, et c'est précisément le piège que la
ligne existe pour défaire : `ain` suivi d'un `e` ne se lit plus comme dans *main*.

**Collision avec `FRA-CP-04`, vérifiée sur les items.** `cp-le-bon-son` sert les nasales `an`/`en`,
`on`, `in` ; `cp-digraphe-du-son` sert `ou`, `oi`, `ch` ; `cp-lettre-du-son` les consonnes simples.
La ligne de CE1 nomme d'autres graphèmes. `GRC-CE1-1` ferme le glissement.

> **`GRC-CE1-1` - périmètre.** Le graphème cible de chaque item appartient à l'une des six familles de
> la ligne : `ain`/`ein`, `eil`/`eille`, `ill`, `gn`, `oin`, `ien`. Chaque famille est cible d'**au
> moins un dixième** des items. Les graphèmes servis au CP (`an`, `en`, `on`, `in`, `ou`, `oi`, `ch`)
> ne sont jamais la cible ; ils peuvent figurer dans un distracteur.

> **`GRC-CE1-2` - une seule proposition porte le son.** Exactement une proposition contient le son du
> mot de référence. Jamais deux propositions qui le portent sous deux graphies (*pain* et *plein*
> ensemble). Sont exclus les mots où le graphème n'a pas sa valeur ordinaire : `ill` lu comme dans
> *ville*, *mille*, *village*, *tranquille* ; la terminaison verbale `-ient` (*ils étudient*).

> **`GRC-CE1-3` - distracteurs.** Trois familles : **(a)** les mêmes lettres suivies d'une voyelle qui
> change la lecture (*plaine*, *reine*, *chienne*) ; **(b)** un graphème visuellement voisin (*ai*
> dans *maison* pour `ain`, *oi* dans *roi* pour `oin`) ; **(c)** l'inversion de lettres (*lion* face à
> *loin*, *chien* face à *plein*). La famille (a) figure dans **au moins un tiers** des items.

**Correction du 2026-10-03 (`nadia-prof`, s49), sur mon propre exemple.** *"chien face à plein"* est
faux deux fois : ce n'est pas une inversion de lettres, et *chien* et *plein* portent tous deux le son
de *« main »*. L'exemple contredit `GRC-CE1-2`, écrite trois lignes plus haut (*"pain et plein
ensemble"*), et le premier paquet l'a reproduit sur cinq items (`FICHE-bandit-manchot-graphemes-complexes-ce1.md`,
motif 1). La condition ne change pas : c'est `GRC-CE1-2` qui tranche. **Sur un item dont la cible est
`ain`/`ein` ou `ien`, aucun distracteur ne contient le son de *« main »*, quelle que soit sa graphie.**
L'exemple est retiré comme modèle ; il reste écrit ci-dessus, pour que l'erreur se lise.

### 3.2 `FRA-CE1-02` - la valeur d'une lettre selon les lettres qui l'entourent

**Verdict : ACCEPTÉ SOUS CONDITIONS `VLE-CE1-1` à `VLE-CE1-3`.** Plafond : `couvert-valide`.

> *Dans « pigeon », le g se lit comme dans quel mot ?* - **gilet** / gare

10 mots. Chaque lettre de la ligne n'a que deux valeurs : un troisième choix serait un distracteur
absurde, donc une devinette. Deux propositions, sur le précédent de `cm1-homophones` (`TRV-CE1-2`).

> **`VLE-CE1-1` - lettres servies.** `c` et `ç` ensemble, `g`, `s` : chacun cible d'**au moins un
> quart** des items. Pour chaque lettre, **chacune de ses deux valeurs** est la bonne réponse d'au moins
> un tiers des items de cette lettre. Les items où la valeur de `g` dépend d'un `e` ou d'un `u` inséré
> (*pigeon*, *guitare*) font au moins un tiers des items de `g`.

> **`VLE-CE1-2` - mots de référence stables.** Pour chaque valeur, le paquet emploie **le même mot de
> référence** d'un bout à l'autre, déclaré en tête de fichier (par exemple *car* et *cerise*, *gare* et
> *girafe*, *sel* et *rose*). Dans ce mot, la lettre n'a qu'une lecture possible. Motif : c'est la
> pratique de la classe (un mot-repère par valeur), et cela retire à chaque manche le coût de lire un
> référent neuf.

> **`VLE-CE1-3` - exclusions.** Les exceptions à la règle (*second*, *gageure*), les groupes `sc`,
> `cc`, `ch`, `gn`, le `s` final muet, le `s` après une consonne (*penser*) et le `x`. Le `ç` n'est
> jamais proposé devant `e` ou `i`.

### 3.3 `FRA-CE1-03` - reconnaître les mots fréquents et irréguliers

**Verdict : ACCEPTÉ SOUS CONDITIONS `MFR-CE1-1` à `MFR-CE1-3`.** Plafond : **`en cours`**, avec la
mention : *"lecture exacte des mots irréguliers servie ; reconnaissance immédiate des mots fréquents
non servie"*.

**Pourquoi la ligne ne peut pas fermer.** *Reconnaître* un mot fréquent, au CE1, c'est le lire sans le
déchiffrer : une question de vitesse. Ce produit ne mesure pas le temps, et je ne le lui demande pas -
un chronomètre mesurerait aussi l'habitude de l'écran. **Ce qui est servable** : vérifier que l'enfant
lit **juste** un mot que le déchiffrage régulier lit faux. La rime le fait sans voix :

> *Quel mot rime avec « femme » ?* - **dame** / crème / ferme

5 mots. *Crème* est ce qu'on obtient en déchiffrant *femme* comme un mot régulier : l'erreur réelle,
nommée.

> **`MFR-CE1-1` - mots cibles.** Chaque mot cible est un mot fréquent que le déchiffrage régulier lit
> faux (*femme*, *monsieur*, *second*, *oignon*, *fils*...). Le paquet déclare sa liste en tête, **au
> moins douze mots distincts**. Un mot-outil n'est cible que si sa lecture est irrégulière.

> **`MFR-CE1-2` - forme et distracteurs.** Le mot cible rime avec exactement une proposition, un mot
> régulier et fréquent. Distracteurs : **(a)** un mot qui rime avec la lecture régulière fautive du mot
> cible (*crème* pour *femme*), dans **au moins la moitié** des items ; **(b)** un mot visuellement
> proche du mot cible (*ferme*).

> **`MFR-CE1-3` - exclusions.** Tout mot dont la lecture varie selon les locuteurs (`TRV-CE1-3`), et
> tout mot qu'une autre ligne de ce référentiel sert par sa règle (`FRA-CE1-01`, `-02`).

### 3.4 `FRA-CE1-04` - comprendre un texte narratif court

**Verdict : ACCEPTÉ SOUS CONDITIONS `RCT-CE1-1` à `RCT-CE1-3`**, sous `LON-CE1-3` et `LON-CE1-4`.
Plafond : `couvert-valide`.

**Ce qui en fait une ligne de CE1 et pas `FRA-CP-07` réétiquetée.** `cp-texte-narratif` sert *qui*,
*où*, *quoi* sur des textes de deux phrases (`NAR-4`). La ligne de CE1 ajoute **le moment** et
**l'ordre chronologique**. Un paquet de *qui* et de *où* tiendrait la lettre de l'intitulé et servirait
le CP. `RCT-CE1-1` le borne.

> *Que se passe-t-il juste après « Il se met à pleuvoir » ?* - **Inès ouvre son parapluie.** / Ils
> arrivent tout secs. / Inès part à l'école.

> **`RCT-CE1-1` - les quatre questions.** Personnages, lieu, moment, ordre chronologique. Les questions
> d'ordre font **au moins un quart** des items, celles de moment **au moins un cinquième** ; personnages
> et lieu réunis **au plus la moitié**. La réponse est écrite dans le texte : aucune inférence
> (`FRA-CE1-06`).

> **`RCT-CE1-2` - les trois propositions sont dans le texte.** Chaque proposition est une suite de mots
> copiée du `texteSupport` de son item, éventuellement raccourcie. Reprise de `NAR-5`, pour le même
> motif : si une seule proposition figure dans le texte, l'enfant apparie des mots sans comprendre.

> **`RCT-CE1-3` - les textes.** Au plus trois personnages nommés. Un texte qui sert une question d'ordre
> porte au moins trois événements. Aucun passé simple.

### 3.5 `FRA-CE1-05` - à qui renvoie un pronom ou un substitut

**Verdict : ACCEPTÉ SOUS CONDITIONS `PRN-CE1-1` à `PRN-CE1-3`**, sous `LON-CE1-3` et `LON-CE1-4`.
Plafond : `couvert-valide`.

**Collision avec `FRA-CM1-03`, vérifiée.** `cm1-substituts` pose trois personnages de même genre et des
textes de trois phrases longues (*"Nadia répète son exposé depuis deux semaines, pendant que Lila
installe les chaises."*, 13 mots). La ligne de CE1 vise les pronoms sujets et un substitut simple, sur
des phrases de douze mots au plus (`LON-CE1-4`). Même geste, autre niveau : c'est la progression du
programme, pas un doublon.

> **`PRN-CE1-1` - ce qui est servi.** La question cite le pronom ou le substitut entre guillemets et
> demande à qui il renvoie. `ils` et `elles` réunis font **au moins un cinquième** des items ; les
> substituts simples (*le garçon*, *l'animal*) **au plus un tiers**.

> **`PRN-CE1-2` - le sens décide, pas seulement l'accord.** Dans **au moins un tiers** des items, deux
> candidats du texte ont le genre et le nombre du pronom. Dans les autres, l'accord suffit, et c'est
> un indice légitime au CE1.

> **`PRN-CE1-3` - une seule occurrence, un seul référent.** Le mot cité n'apparaît **qu'une fois** dans
> le texte ; son référent est dans la même phrase ou dans la précédente ; un `il` impersonnel (*il
> pleut*, *il faut*) n'est jamais la cible. Chaque proposition désigne un personnage ou un objet du
> texte, avec les mots du texte.

### 3.6 `FRA-CE1-06` - faire une inférence simple

**Verdict : ACCEPTÉ SOUS CONDITIONS `INF-CE1-1` à `INF-CE1-3`**, sous `LON-CE1-3` et `LON-CE1-4`.
Plafond : `couvert-valide`.

> *Pourquoi Inès ouvre-t-elle son parapluie au-dessus de lui ?* - **Pour qu'il ne soit pas mouillé.** /
> Parce qu'il fait nuit. / Pour aller à l'école.

> **`INF-CE1-1` - une inférence, et une seule.** Trois sortes : un sentiment, un lieu, une cause,
> chacune dans **au moins un cinquième** des items. La réponse relie au plus deux faits du texte.
> Jamais une chaîne de déductions.

> **`INF-CE1-2` - la réponse n'est pas écrite.** La bonne réponse n'est pas une suite de mots du texte.
> C'est la branche inverse de `RCT-CE1-2`, et ce qui sépare les deux lignes.

> **`INF-CE1-3` - distracteurs.** **(a)** un élément présent dans le texte qui ne répond pas à la
> question ; **(b)** une réponse vraisemblable en général que le texte contredit. Les sentiments sont
> pris parmi les plus courants (content, triste, en colère, avoir peur, surpris).

### 3.7 `FRA-CE1-07` - comprendre un texte documentaire ou une consigne, et y prélever une information

**Verdict : ACCEPTÉ SOUS CONDITIONS `DOC-CE1-1` à `DOC-CE1-3`**, sous `LON-CE1-3` et `LON-CE1-4`.
Plafond : `couvert-valide`.

**Le risque propre à cette ligne** : un texte sur le hérisson ou sur l'eau rejoint ce que questionner le
monde enseigne au CP. Un enfant qui sait déjà répondrait sans lire, et l'item mesurerait sa culture,
pas sa lecture. `DOC-CE1-2` réduit ce risque ; la vérification du texte masqué reste dans ma seule
main (section 9).

> **`DOC-CE1-1` - les deux genres.** Textes documentaires dans **au moins la moitié** des items ;
> consignes écrites (deux à quatre instructions, *"Prends une feuille. Plie-la en deux."*) dans **au
> moins un quart**. Une question sur une consigne porte sur l'ordre ou le contenu d'une instruction.

> **`DOC-CE1-2` - prélever.** La bonne réponse figure dans le texte, **et au moins un distracteur
> aussi** : le prélèvement doit choisir, pas apparier.

> **`DOC-CE1-3` - exactitude.** Chaque fait est exact et vérifiable dans un ouvrage de référence
> général. Aucun nombre supérieur à 1000 (`MAT-CE1-01`). Le texte porte sur un être, un objet ou un
> geste qu'un enfant de CE1 connaît ou se représente sans image.

---

## 4. Écriture (`FRA-CE1-08`, `FRA-CE1-09`)

### 4.1 `FRA-CE1-08` - remettre dans l'ordre les mots d'une phrase

**Verdict : ACCEPTÉ SOUS CONDITIONS `OMP-CE1-1` à `OMP-CE1-3`.** Plafond : `couvert-valide`.

**Ce que je décide, et le précédent qui le fonde.** Aucune des trois mécaniques (bandit manchot,
désignation, saisie) ne fait déplacer des mots. L'enfant **choisit** l'ordre juste parmi trois. C'est
exactement le choix que j'ai fait au CP pour *ranger des nombres* (`cp-ranger-nombres`, ligne
`couvert-valide`) : choisir parmi trois listes ordonnées vaut *ranger*, **à condition que les
distracteurs soient des erreurs réelles**. Des mots en vrac s'écartent d'un regard ; ce sont eux que
`OMP-CE1-2` interdit.

> *dort / chat / Le / lit / le / sur / .* - **Le chat dort sur le lit.** / Le lit dort sur le chat. / le
> chat dort sur Le lit.

> **`OMP-CE1-1` - la phrase.** L'énoncé donne les mots dans le désordre, séparés par ` / `, le mot à
> majuscule et le point compris. **Quatre à six mots** (`LON-CE1-5`), aucune virgule. Les mots donnés
> n'admettent **qu'un seul ordre** qui fasse une phrase correcte et sensée, majuscule en tête et point
> à la fin.

> **`OMP-CE1-2` - distracteurs.** **(a)** deux groupes échangés, la phrase restant correcte mais absurde
> ou de sens différent (*"Le lit dort sur le chat."*), dans **au moins un tiers** des items ; **(b)** le
> mot à majuscule ailleurs qu'en tête, ou le point ailleurs qu'à la fin ; **(c)** un déterminant ou un
> adjectif mal placé (*"Le chat petit dort."*). Jamais une suite de mots sans aucune construction.

> **`OMP-CE1-3` - frontière.** L'énoncé d'un item de cette ligne est toujours une liste de mots, jamais
> une phrase écrite. Une phrase à corriger relève de `FRA-CE1-09`, une phrase à reconnaître de
> `FRA-CE1-10`.

### 4.2 `FRA-CE1-09` - relire une phrase et y repérer une erreur

**Verdict : ACCEPTÉ SOUS CONDITIONS `RLP-CE1-1` à `RLP-CE1-3`.** Plafond : `couvert-valide`.

**Collision avec `FRA-CM1-10`, vérifiée.** `cm1-reperes` sert la majuscule et le point sur des phrases
de forme CE1 (*"le chat dort sur le tapis."*). La ligne de CE1 en sert davantage : le mot manquant et
l'accord oublié. `RLP-CE1-1` l'impose, pour qu'un paquet de CE1 ne serve pas seulement la moitié que le
CM1 sert déjà.

> **`RLP-CE1-1` - une erreur, quatre sortes.** L'énoncé cite **une** phrase qui porte **exactement une**
> erreur : mot manquant, accord oublié (sujet-verbe ou groupe nominal), majuscule initiale absente,
> point final absent. Chaque sorte dans **au moins un cinquième** des items.

> **`RLP-CE1-2` - deux formes de question.** *"Quelle erreur ?"*, avec trois étiquettes prises dans une
> liste fermée déclarée en tête (*"Il manque un mot."*, *"Il manque la majuscule."*, *"Il manque le
> point."*, *"Un mot est mal accordé."*) ; ou *"Quel mot est mal écrit ?"*, avec trois mots de la
> phrase, pour les accords. Une erreur d'accord n'est jamais en même temps une faute d'orthographe
> d'usage.

> **`RLP-CE1-3` - l'explication redonne la phrase juste** (`TRV-CE1-5`).

---

## 5. Orthographe et grammaire (`FRA-CE1-10` à `FRA-CE1-22`)

### 5.1 `FRA-CE1-10` - reconnaître une phrase et identifier son type

**Verdict : ACCEPTÉ SOUS CONDITIONS `TPH-CE1-1` à `TPH-CE1-3`.** Plafond : `couvert-valide`.

> **`TPH-CE1-1` - les deux moitiés.** *Reconnaître une phrase* dans **au moins un tiers** des items,
> *identifier son type* dans **au moins la moitié** ; chacun des trois types est la bonne réponse d'au
> moins un sixième du paquet.

> **`TPH-CE1-2` - reconnaître.** Trois groupes de mots ; un seul a la majuscule initiale, le point final
> et un sens. Distracteurs : **(a)** sans majuscule ; **(b)** sans ponctuation finale. Aucun distracteur
> en désordre (`FRA-CE1-08`).

> **`TPH-CE1-3` - le type.** Les propositions sont les trois noms de type. **Aucune phrase impérative** :
> la ligne ne la nomme pas, et *"Viens ici !"* serait classée exclamative par sa ponctuation. Jamais
> deux signes combinés (`?!`, `...`).

### 5.2 `FRA-CE1-11` - passer de l'affirmative à la négative, et inversement

**Verdict : ACCEPTÉ SOUS CONDITIONS `NEG-CE1-1` à `NEG-CE1-3`.** Plafond : `couvert-valide`.

> *Léo aime les épinards.* - **Léo n'aime pas les épinards.** / Léo aime pas les épinards. / Léo ne aime
> pas les épinards.

> **`NEG-CE1-1` - périmètre.** `ne... pas` seul (ni *plus*, ni *jamais*, ni *rien*), verbe au présent.
> Vers la négative dans **au moins la moitié** des items, vers l'affirmative dans **au moins un quart**.

> **`NEG-CE1-2` - l'élision.** Un verbe commençant par une voyelle, donc `n'`, dans **au moins un
> cinquième** des items.

> **`NEG-CE1-3` - distracteurs.** **(a)** le `ne` omis, forme de l'oral ; **(b)** `pas` mal placé ;
> **(c)** l'élision absente ou fautive ; **(d)** vers l'affirmative, un `ne` ou un `pas` resté. Hors de
> la négation, les trois propositions reprennent les mêmes mots. `TRV-CE1-2` (longueur) s'applique ici
> plus qu'ailleurs.

### 5.3 `FRA-CE1-12` - identifier la classe d'un mot

**Verdict : ACCEPTÉ SOUS CONDITIONS `CLM-CE1-1` à `CLM-CE1-3`.** Plafond : `couvert-valide`.

**Collision avec `FRA-CP-11`, vérifiée.** `cp-nature-mot` sert *nom* ou *verbe*, deux propositions, le mot
cible désigné (`NAT-5`). La ligne de CE1 nomme cinq classes.

> **`CLM-CE1-1` - les cinq classes.** Le mot cible est désigné dans la phrase (`partDesignee`,
> `ADR-0062`). Trois propositions prises parmi *nom commun*, *nom propre*, *verbe*, *déterminant*,
> *adjectif* ; chacune des cinq est la bonne réponse d'**au moins un dixième** des items ; *nom commun*
> et *verbe* réunis **au plus la moitié** (sinon le paquet sert `FRA-CP-11`).

> **`CLM-CE1-2` - exclusions.** Le mot cible n'ouvre jamais la phrase : sa majuscule y serait un faux
> indice de nom propre. Aucun pronom, adverbe, préposition ni conjonction n'est désigné : ces classes
> ne sont pas dans la ligne.

> **`CLM-CE1-3` - aucun signe de surface ne répond à la place de l'enfant** (reprise de `NAT-1`). Un nom
> n'est pas toujours précédé d'un déterminant ; l'adjectif est placé avant le nom dans au moins un tiers
> des items d'adjectif, après dans au moins un tiers.

### 5.4 `FRA-CE1-13` - identifier le verbe et son sujet

**Verdict : ACCEPTÉ SOUS CONDITIONS `VSJ-CE1-1` à `VSJ-CE1-3`.** Plafond : `couvert-valide`.

> **`VSJ-CE1-1` - la question.** Au moins la moitié des items demandent **le sujet** d'un verbe cité ;
> les autres demandent le verbe. Les propositions sont des mots ou des groupes copiés de la phrase.
> *Frontière avec `FRA-CE1-12`* : là, on nomme la classe d'un mot désigné ; ici, on trouve un mot dans
> la phrase.

> **`VSJ-CE1-2` - le sujet.** Pronom personnel, nom seul, groupe nominal de trois mots au plus : chacun
> dans **au moins un cinquième** des items. Le sujet précède toujours le verbe ; un seul verbe
> conjugué par phrase ; aucun sujet coordonné.

> **`VSJ-CE1-3` - distracteurs.** **(a)** le groupe placé après le verbe (*"du lait"*) ; **(b)** un autre
> nom de la phrase ; **(c)** pour les items de verbe, un nom ou un adjectif de la phrase.

### 5.5 `FRA-CE1-14` - accorder le verbe avec son sujet au présent

**Verdict : ACCEPTÉ SOUS CONDITIONS `ACV-CE1-1` à `ACV-CE1-3`.** Plafond : `couvert-valide`.

**Collision avec `FRA-CM1-06`, vérifiée.** `cm1-accord-sujet-verbe` éloigne le sujet du verbe (*"Le chat
de ma grand-mère, malgré son âge, ___ encore..."*). La ligne de CE1 dit le contraire : sujet **placé
juste avant** le verbe.

> **`ACV-CE1-1` - le sujet.** Placé immédiatement avant le verbe, sans mot entre eux. Sujet nominal
> (nom ou groupe nominal) dans **au moins deux tiers** des items ; sujet pluriel et sujet singulier
> chacun dans **au moins un tiers**. Aucun sujet coordonné.

> **`ACV-CE1-2` - les propositions.** Trois formes du présent du même verbe (*joue* / *joues* /
> *jouent*). Verbes : ceux de `PRS-CE1-1`, et aucun autre.

> **`ACV-CE1-3` - frontière avec `FRA-CE1-17`.** Ici, le nombre se lit sur le sujet, et le sujet est
> surtout un nom ; là, le sujet est toujours un pronom et la difficulté est la forme du verbe.

### 5.6 `FRA-CE1-15` - accorder dans le groupe nominal

**Verdict : ACCEPTÉ SOUS CONDITIONS `GNA-CE1-1` à `GNA-CE1-3`.** Plafond : `couvert-valide`.

**Collision avec `FRA-CP-10`, vérifiée.** `cp-pluriel` sert déterminant et nom avec `-s` (*"une
assiette"*, *"les crayons"*). Ce qui fait la ligne de CE1 : l'adjectif, le `-x`, le féminin.

> **`GNA-CE1-1` - ce qui est servi.** Un adjectif dans **au moins la moitié** des groupes nominaux.
> Pluriel en `-s`, pluriel en `-x`, féminin en `-e` : chacun dans **au moins un cinquième** des items.

> **`GNA-CE1-2` - exactitude.** Le `-x` seulement pour les mots en `-eau`, `-au`, `-eu`, hors
> exceptions (*bleu*, *pneu*, *landau*). Ni `-al`/`-aux`, ni `-ou`/`-oux` : la ligne ne les nomme pas.
> Féminin par ajout d'un `-e` sans autre changement (*petit*/*petite*) ; aucun adjectif déjà terminé
> par `-e`, aucun féminin irrégulier (*beau*/*belle*).

> **`GNA-CE1-3` - distracteurs.** Le groupe nominal entier est proposé. **(a)** la marque oubliée sur
> un mot ; **(b)** la marque posée sur le mauvais mot ; **(c)** `-s` à la place de `-x`.

### 5.7 `FRA-CE1-16` - les homophones `a`/`à` et `et`/`est`

**Verdict : ACCEPTÉ SOUS CONDITIONS `HOM-CE1-1` à `HOM-CE1-3`.** Plafond : `couvert-valide`.

**Collision avec `FRA-CM1-08`, vérifiée.** `cm1-homophones` sert ces deux paires, plus `son`/`sont` et
`on`/`ont`, sur des phrases courtes, avec deux propositions et l'indice par substitution. Le programme
place ces deux paires au CE1 et les reprend au CM1 : deux lignes, deux niveaux, aucun doublon.

> **`HOM-CE1-1` - périmètre.** `a`/`à` et `et`/`est` seulement, chaque paire dans **au moins un tiers**
> des items, chaque forme bonne réponse d'au moins un tiers des items de sa paire.

> **`HOM-CE1-2` - forme.** Une phrase à trou, les deux formes de la paire en propositions. La phrase
> n'admet qu'une forme.

> **`HOM-CE1-3` - indice.** La substitution (*avait*, *était*) sans la réponse, comme `cm1-homophones`
> le fait depuis `DEC-0024`, point 5.

### 5.8 `FRA-CE1-17` - conjuguer au présent

**Verdict : ACCEPTÉ SOUS CONDITIONS `PRS-CE1-1` à `PRS-CE1-3`.** Plafond : `couvert-valide`.

> **`PRS-CE1-1` - verbes.** *Être*, *avoir*, les verbes du premier groupe, *aller*, *faire*, *dire*,
> *venir*. Les six verbes nommés dans **au moins la moitié** des items. **Exclus** : les verbes du
> premier groupe à modification orthographique (`-ger`, `-cer`, `-eler`, `-eter`, `-yer`, *acheter*,
> *lever*).

> **`PRS-CE1-2` - sujets.** Toujours un pronom personnel ; chacune des six personnes dans **au moins un
> dixième** des items.

> **`PRS-CE1-3` - distracteurs.** **(a)** le même verbe à une autre personne ; **(b)** la forme
> régularisée d'un verbe irrégulier (*vous disez*, *ils allent*) ; **(c)** l'infinitif.

### 5.9 `FRA-CE1-18` - conjuguer au futur

**Verdict : ACCEPTÉ SOUS CONDITIONS `FUT-CE1-1` à `FUT-CE1-3`.** Plafond : `couvert-valide`.

> **`FUT-CE1-1` - verbes.** *Être*, *avoir*, premier groupe, mêmes exclusions que `PRS-CE1-1`. *Être*
> et *avoir* dans **au moins un quart** des items.

> **`FUT-CE1-2` - sujets.** Comme `PRS-CE1-2`.

> **`FUT-CE1-3` - distracteurs, et ce qu'ils ne sont jamais.** **(a)** le futur à une autre personne
> (*-ras*/*-ra*, *-rons*/*-ront*) ; **(b)** le présent du même verbe ; **(c)** un radical régularisé
> pour *être* ou *avoir*. **Jamais une forme juste d'un temps que le CE1 n'étudie pas** : ni
> conditionnel (*je chanterais*), ni futur proche (*je vais chanter*), ni imparfait. Ce sont des formes
> correctes du français ; les proposer comme fausses enseignerait une erreur.

### 5.10 `FRA-CE1-19` - conjuguer au passé composé avec `avoir`

**Verdict : ACCEPTÉ SOUS CONDITIONS `PCO-CE1-1` à `PCO-CE1-3`.** Plafond : `couvert-valide`.

> **`PCO-CE1-1` - périmètre.** Auxiliaire `avoir` seul. *Être* (*j'ai été*), *avoir* (*j'ai eu*) et les
> verbes du premier groupe qui se conjuguent avec `avoir`. **Exclus** : tout verbe qui prend `être`
> (*arriver*, *entrer*, *rester*, *tomber*), les verbes pronominaux, tout complément placé avant le
> verbe (aucun accord du participe).

> **`PCO-CE1-2` - sujets.** Comme `PRS-CE1-2`.

> **`PCO-CE1-3` - distracteurs.** **(a)** l'infinitif à la place du participe (*j'ai chanter*), dans
> **au moins un tiers** des items - c'est l'erreur du CE1 ; **(b)** l'auxiliaire à une autre personne
> (*tu a chanté*) ; **(c)** le présent du verbe. Jamais l'imparfait.

### 5.11 `FRA-CE1-20` - situer une phrase dans le temps

**Verdict : ACCEPTÉ SOUS CONDITIONS `TPS-CE1-1` à `TPS-CE1-3`.** Plafond : `couvert-valide`.

> **`TPS-CE1-1` - propositions.** Toujours *passé*, *présent*, *futur* ; chacun bonne réponse d'**au
> moins un quart** des items.

> **`TPS-CE1-2` - le verbe décide.** **Au moins la moitié** des items ne portent aucun mot de temps :
> sinon *hier* répond à la place du verbe. Les mots de temps admis sont *hier*, *aujourd'hui*,
> *demain*, *maintenant*, toujours cohérents avec le verbe.

> **`TPS-CE1-3` - les temps.** Passé : passé composé seul. Futur : futur simple seul ; jamais le futur
> proche, qu'un enfant de CE1 lit comme un présent (*je vais nager*).

### 5.12 `FRA-CE1-21` - orthographier les mots invariables et les mots fréquents

**Verdict : ACCEPTÉ SOUS CONDITIONS `MIV-CE1-1` à `MIV-CE1-3`.** Plafond : **`en cours`**, avec la
mention : *"reconnaître la graphie juste d'un mot invariable ou fréquent servi ; l'écrire sans modèle
non servi"*.

**Pourquoi la ligne ne peut pas fermer en l'état.** *Orthographier*, c'est écrire. Écrire un mot sans
le voir demande soit de l'**entendre** - la dictée, `FRA-CE1-29`, `non couvrable en l'état` parce que
la voix de l'adulte n'est arbitrée qu'au CP -, soit de le **retrouver par le contexte et le taper** -
une saisie alphabétique au CE1, que `SAI-11` ne règle pas (elle ne porte que sur le CP) et que je
n'arbitre pas par ricochet dans ce document. Ce qui reste servable est réel : choisir la graphie juste
parmi trois qui se lisent pareil.

> *Il pleut ___ en novembre.* - **souvent** / souvant / souvens

> **`MIV-CE1-1` - mots cibles.** Mots invariables et mots fréquents, liste déclarée en tête, **au moins
> quinze mots distincts**. **Exclus** : les mots qu'une règle d'une autre ligne décide (`a`/`à`,
> `et`/`est`, `FRA-CE1-16` ; `m` devant `m`, `b`, `p`, `c`/`ç`, `g`/`ge`/`gu`, `FRA-CE1-22`).

> **`MIV-CE1-2` - les trois graphies se lisent pareil.** Les distracteurs sont des graphies fautives
> **qui se prononcent comme le mot** (*souvant*). Une graphie qui se lit autrement ferait de l'item un
> exercice de lecture, pas d'orthographe.

> **`MIV-CE1-3` - exposition.** Un mot cible est bonne réponse d'au plus deux items. Une graphie fautive
> n'apparaît que parmi les propositions, jamais dans l'indice ni dans l'explication.

### 5.13 `FRA-CE1-22` - choisir la graphie d'un son selon la lettre qui suit

**Verdict : ACCEPTÉ SOUS CONDITIONS `GRL-CE1-1` à `GRL-CE1-3`.** Plafond : `couvert-valide`.

> **`GRL-CE1-1` - les trois règles.** `m` devant `m`, `b`, `p` ; `c`/`ç` ; `g`/`ge`/`gu` : chacune dans
> **au moins un quart** des items.

> **`GRL-CE1-2` - forme.** Deux propositions : le même mot fréquent écrit deux fois, ne différant que par
> la graphie que la règle décide (*une jambe* / *une janbe*, *un garçon* / *un garcon*).

> **`GRL-CE1-3` - exclusions et frontière.** Les exceptions à la règle du `m` (*bonbon*, *néanmoins*)
> sont exclues. *Frontière avec `FRA-CE1-02`* : là, l'enfant lit un mot entièrement écrit ; ici, il
> choisit comment l'écrire.

---

## 6. Vocabulaire (`FRA-CE1-23` à `FRA-CE1-26`)

### 6.1 `FRA-CE1-23` - ranger des mots dans l'ordre alphabétique

**Verdict : ACCEPTÉ SOUS CONDITIONS `ALP-CE1-1` à `ALP-CE1-3`.** Plafond : `couvert-valide`, sur le même
précédent que `FRA-CE1-08` (section 4.1).

> *Quel mot vient en premier dans l'ordre alphabétique ?* - **lapin** / lune / loup

> **`ALP-CE1-1` - formes.** *Quel mot vient en premier* dans **au moins la moitié** des items ; trois
> listes des trois mêmes mots, sur la forme de `cp-ranger-nombres`, dans les autres.

> **`ALP-CE1-2` - deuxième lettre.** Des mots de même première lettre dans **au moins un tiers** des
> items. Jamais de décision à la troisième lettre : la ligne s'arrête à la deuxième. Aucun mot dont les
> deux premières lettres portent un accent, une apostrophe, un trait d'union ou une majuscule.

> **`ALP-CE1-3` - distracteurs des listes.** **(a)** l'ordre inverse ; **(b)** l'ordre juste sur la
> première lettre et faux sur la deuxième.

### 6.2 `FRA-CE1-24` - familles de mots, préfixes et suffixes fréquents

**Verdict : ACCEPTÉ SOUS CONDITIONS `FAM-CE1-1` à `FAM-CE1-3`.** Plafond : `couvert-valide`.

> *Quel mot est de la même famille que « dent » ?* - **dentiste** / danse / bouche

> **`FAM-CE1-1` - ce qui est servi.** Familles dans **au moins la moitié** des items ; préfixes et
> suffixes dans **au moins un quart**. Affixes : `re-`, `dé-`, `-eur`, plus deux autres au plus,
> déclarés en tête.

> **`FAM-CE1-2` - familles exactes.** Une famille se vérifie au dictionnaire ; aucune famille savante ou
> discutable. Distracteurs : **(a)** une fausse famille, mêmes lettres ou même son sans le sens
> (*danse*) ; **(b)** un mot du même thème sans le radical (*bouche*).

> **`FAM-CE1-3` - l'affixe par son sens.** Un item d'affixe demande ce que le mot veut dire (*"Que veut
> dire « refaire » ?"*), jamais le nom de l'affixe (`TRV-CE1-4`). Les contraires formés par préfixe
> (*défaire*, *impossible*) appartiennent à cette ligne, pas à `FRA-CE1-25`.

### 6.3 `FRA-CE1-25` - trouver un synonyme ou un contraire

**Verdict : ACCEPTÉ SOUS CONDITIONS `SYC-CE1-1` à `SYC-CE1-3`.** Plafond : `couvert-valide`.

**Collision avec `FRA-CM1-11`, vérifiée.** `cm1-synonymes` et `cm1-antonymes` emploient la forme que je
reprends (le mot cible entre guillemets dans une phrase) avec un vocabulaire de CM1 (*aride*,
*discret*). Au CE1 : des mots fréquents.

> **`SYC-CE1-1` - les deux relations.** Synonymes et contraires chacun dans **au moins un tiers** des
> items. La question nomme la relation demandée. Le mot cible est fréquent et placé dans une phrase
> courte qui fixe son sens.

> **`SYC-CE1-2` - distracteurs.** **(a)** la relation inverse (le contraire quand on demande un
> synonyme, et l'inverse), dans **au moins un tiers** des items ; **(b)** un mot du même thème, de sens
> différent. Aucun distracteur qu'un dictionnaire donnerait aussi comme réponse.

> **`SYC-CE1-3` - frontière.** Aucun contraire formé par préfixe (`FRA-CE1-24`).

### 6.4 `FRA-CE1-26` - classer des mots par catégorie et trouver le mot qui les désigne

**Verdict : ACCEPTÉ SOUS CONDITIONS `CTG-CE1-1` à `CTG-CE1-3`.** Plafond : `couvert-valide`.

> *Quel mot désigne : pomme, poire, cerise ?* - **fruit** / légume / arbre

> **`CTG-CE1-1` - formes.** *Le mot qui les désigne tous*, trois mots donnés, dans **au moins la
> moitié** des items ; *classer* (lequel est un vêtement, lequel n'en est pas un) dans les autres.

> **`CTG-CE1-2` - catégories.** Catégories de la vie courante, liste fermée déclarée en tête (fruits,
> légumes, animaux, vêtements, meubles, outils, instruments de musique...). **Aucune classification
> scientifique** : vivant et non vivant, classement des êtres vivants relèvent de questionner le monde
> et des sciences. Aucun membre discuté (*tomate*, *champignon*, *baleine*).

> **`CTG-CE1-3` - distracteurs.** **(a)** la catégorie sœur (*légume*) ; **(b)** un mot trop étroit ou
> l'un des mots donnés ; **(c)** un mot associé qui n'est pas une catégorie (*arbre*, *marché*).

---

## 7. Frontières, entre lignes de CE1 et avec les lignes servies

| Lignes | Ce qui les sépare | Où c'est écrit |
|---|---|---|
| `FRA-CE1-01` / `FRA-CP-04` | graphèmes complexes contre graphèmes simples, sans voix contre avec voix | `GRC-CE1-1`, `TRV-CE1-1` |
| `FRA-CE1-02` / `FRA-CE1-22` | lire un mot écrit / choisir comment l'écrire | `GRL-CE1-3` |
| `FRA-CE1-04` / `FRA-CP-07` | moment et ordre chronologique imposés | `RCT-CE1-1` |
| `FRA-CE1-04` / `FRA-CE1-06` | réponse écrite / réponse non écrite | `RCT-CE1-2`, `INF-CE1-2` |
| `FRA-CE1-05` / `FRA-CE1-04` | la question cite le pronom ; sinon c'est une question de personnage | `PRN-CE1-1` |
| `FRA-CE1-08` / `-09` / `-10` | liste de mots / phrase fautive / groupes à reconnaître | `OMP-CE1-3`, `RLP-CE1-1`, `TPH-CE1-2` |
| `FRA-CE1-12` / `FRA-CE1-13` | nommer la classe d'un mot désigné / trouver un mot dans la phrase | `VSJ-CE1-1` |
| `FRA-CE1-12` / `FRA-CP-11` | cinq classes, nom et verbe au plus la moitié | `CLM-CE1-1` |
| `FRA-CE1-14` / `FRA-CE1-17` | sujet surtout nominal / sujet toujours pronom | `ACV-CE1-3` |
| `FRA-CE1-15` / `FRA-CP-10` | adjectif, `-x`, féminin imposés | `GNA-CE1-1` |
| `FRA-CE1-21` / `-16` et `-22` | mémoire du mot / règle | `MIV-CE1-1` |
| `FRA-CE1-24` / `FRA-CE1-25` | contraires par préfixe d'un seul côté | `FAM-CE1-3`, `SYC-CE1-3` |
| `FRA-CE1-26` / questionner le monde et sciences | catégories de la vie courante, jamais une classification scientifique | `CTG-CE1-2` |

**`CYC-1` n'est pas due** : les vingt-six lignes sont de cycle 2. Les lignes de CM1 qui partagent un
objet avec elles (`FRA-CM1-03`, `-06`, `-07`, `-08`, `-09`, `-10`, `-11`) sont déjà servies ; je les ai
lues pour tracer les frontières ci-dessus, et je n'en rouvre aucune.

---

## 8. Ce que ce document ne fait pas

- Il ne prononce **aucun Done**. Aucune ligne ne bouge dans la matrice de son fait.
- Il ne chiffre **aucun tarif** et ne rend **aucun avis de faisabilité technique**.
- Il ne rouvre ni `LON-CE1-1` à `LON-CE1-4`, ni aucune condition de mathématiques CE1, ni le verdict de
  `FRA-CE1-27` à `FRA-CE1-31`. `LON-CE1-5` s'y ajoute sans en amender aucune.
- Il ne rouvre **aucun Done déjà prononcé**, sur aucun des paquets lus en section 7.
- Il **n'étend pas** la prémisse de l'adulte qui lit au CE1, et **n'arbitre pas** la saisie alphabétique
  au CE1 : les deux restent hors de ce document (section 5.12).
- Il ne garantit `couvert-valide` sur aucune ligne : vingt-quatre peuvent l'atteindre, deux sont
  plafonnées `en cours`. Le Done se prononce après que j'ai joué le paquet livré, jamais avant.

---

## 9. Ce dont je reste la seule garde, et qu'aucun automate ne verra

Les conditions de ce document sont, pour la plupart, décidables sur l'item ou sur la liste des items.
**Tant qu'aucun garde-fou ne les exécute, c'est moi qui les tiens, en comptant.** Restent en plus dans
ma seule main, et je les nomme pour que l'équipe ne les croie pas couvertes :

1. **Le texte masqué** (`NAR-6`, transposé) sur `FRA-CE1-04` à `FRA-CE1-07` : un enfant qui n'a pas lu
   le texte ne fait pas mieux que le hasard. C'est la propriété la plus exposée de ce référentiel, et
   `FRA-CE1-07` la plus exposée des quatre (section 3.7).
2. **Une seule réponse acceptable** : inférences, synonymes, catégories, ordre des mots, questions de
   moment. Mon propre texte du hérisson montre qu'une question exacte en apparence en admet deux
   (section 1.2).
3. **Les trois graphies d'un item de `FRA-CE1-21` se lisent pareil** (`MIV-CE1-2`).
4. **L'exactitude** : faits documentaires, familles de mots, exceptions orthographiques, formes
   conjuguées, et chaque distracteur.
5. **La vraisemblance d'un distracteur** comme erreur réelle d'un enfant de CE1.
6. **La lisibilité réelle**, l'accessibilité du vocabulaire et la charge d'un item conforme à
   `LON-CE1-1` à `LON-CE1-5`.
7. **Que chaque paquet travaille la ligne qu'il déclare**, et pas la voisine plus facile ou déjà
   fermée (section 7) : `ADR-0081` vérifie qu'un code résout, jamais qu'il dit vrai.
8. **Le compte des phrases d'un énoncé qui cite une phrase entre guillemets** (section 0.3, point 2).
9. **L'absence de stéréotype** dans les personnages, les situations et les émotions.
10. **La progression à l'intérieur d'une partie** : toutes mes proportions sont des distributions,
    aucune ne dit l'ordre des manches.

---

## 10. Ce que je route, et à qui

| Fait | À qui | Pourquoi ce n'est pas à moi |
|---|---|---|
| `DEC-0106` (section 4) et le planning de s48 (section 4) citent la borne du texte support comme non posée ; elle l'est depuis le 2026-10-02 (section 0.1) | `camille-po` | `DEC-0106` est sa décision ; une correction s'écrit chez son auteure |
| Toute story qui servira une ligne de `FRA-CE1-01` à `FRA-CE1-26` cite `TRV-CE1-1` à `TRV-CE1-7`, `LON-CE1-1` à `LON-CE1-5` et les conditions de sa ligne. Deux mentions de couverture partielle sont dues d'avance : `FRA-CE1-03` et `FRA-CE1-21` | `camille-po` | Rédaction de story et de matrice |
| `LON-CE1-5` et la plupart des conditions de ce document sont automatisables, sur le patron d'`ADR-0089` et de `TAB-CE1-1` à `TAB-CE1-6`. Leur population est vide aujourd'hui | `theo-archi`, via `sofia-sm` | La forme du test lui appartient ; `ADR-0051` a une doctrine sur une population vide, et c'est la sienne |
| **Observation, pas demande** : le garde-fou de `LON-CE1-2` compte une phrase citée et la question pour une seule phrase (section 0.3). Sans effet sur un verdict aujourd'hui | `theo-archi`, via `sofia-sm` | Le découpage des phrases est son code |
| L'avis de faisabilité technique sur le référentiel français CE1, que le planning de s48 (section 5) lui attribue une fois cet arbitrage écrit | `theo-archi` | Coût, mécanique, collisions ArchUnit |
| Si `camille-po` veut viser `couvert-valide` sur `FRA-CE1-21` : soit l'extension de la prémisse de l'adulte au CE1, soit un arbitrage de la saisie alphabétique au CE1 | l'utilisatrice pour la prémisse, via `sofia-sm` ; moi, dans un arbitrage distinct, pour la saisie | La prémisse est un arbitrage de l'utilisatrice (`DEC-0046`, `DEC-0068`) ; la saisie au CE1 mérite son propre document, pas un ricochet |

**Les vingt-six lignes sont arbitrées, donc instruites** : `theo-archi` peut rendre un avis de
faisabilité sur chacune. Aucune n'aura de Done pédagogique avant que j'aie joué le paquet livré.

---

## 11. Les identifiants

**La forme se copie, elle ne s'invente pas.** Tous les identifiants neufs portent **trois segments**,
`PRÉFIXE-CE1-<n>`, la forme exacte de `LON-CE1-4`, `PRO-CE1-7` et `TAB-CE1-6`, déjà alloués en section
4 du registre. Aucun ne porte de marqueur de réserve : ce sont des conditions.

| Ligne | Identifiants |
|---|---|
| bornes | `LON-CE1-5` |
| toutes | `TRV-CE1-1` à `TRV-CE1-7` |
| `FRA-CE1-01` | `GRC-CE1-1` à `GRC-CE1-3` |
| `FRA-CE1-02` | `VLE-CE1-1` à `VLE-CE1-3` |
| `FRA-CE1-03` | `MFR-CE1-1` à `MFR-CE1-3` |
| `FRA-CE1-04` | `RCT-CE1-1` à `RCT-CE1-3` |
| `FRA-CE1-05` | `PRN-CE1-1` à `PRN-CE1-3` |
| `FRA-CE1-06` | `INF-CE1-1` à `INF-CE1-3` |
| `FRA-CE1-07` | `DOC-CE1-1` à `DOC-CE1-3` |
| `FRA-CE1-08` | `OMP-CE1-1` à `OMP-CE1-3` |
| `FRA-CE1-09` | `RLP-CE1-1` à `RLP-CE1-3` |
| `FRA-CE1-10` | `TPH-CE1-1` à `TPH-CE1-3` |
| `FRA-CE1-11` | `NEG-CE1-1` à `NEG-CE1-3` |
| `FRA-CE1-12` | `CLM-CE1-1` à `CLM-CE1-3` |
| `FRA-CE1-13` | `VSJ-CE1-1` à `VSJ-CE1-3` |
| `FRA-CE1-14` | `ACV-CE1-1` à `ACV-CE1-3` |
| `FRA-CE1-15` | `GNA-CE1-1` à `GNA-CE1-3` |
| `FRA-CE1-16` | `HOM-CE1-1` à `HOM-CE1-3` |
| `FRA-CE1-17` | `PRS-CE1-1` à `PRS-CE1-3` |
| `FRA-CE1-18` | `FUT-CE1-1` à `FUT-CE1-3` |
| `FRA-CE1-19` | `PCO-CE1-1` à `PCO-CE1-3` |
| `FRA-CE1-20` | `TPS-CE1-1` à `TPS-CE1-3` |
| `FRA-CE1-21` | `MIV-CE1-1` à `MIV-CE1-3` |
| `FRA-CE1-22` | `GRL-CE1-1` à `GRL-CE1-3` |
| `FRA-CE1-23` | `ALP-CE1-1` à `ALP-CE1-3` |
| `FRA-CE1-24` | `FAM-CE1-1` à `FAM-CE1-3` |
| `FRA-CE1-25` | `SYC-CE1-1` à `SYC-CE1-3` |
| `FRA-CE1-26` | `CTG-CE1-1` à `CTG-CE1-3` |

**Vingt-sept préfixes neufs**, cherchés avant d'écrire dans la section 4 du registre, sur l'arbre et
sur toutes les branches locales : aucun n'y figurait. `ASV` et `AGN` (accords du CM1), `SYN` et `ANT`
(vocabulaire du CM1), `CAT`, `NAT` et `ORD` existaient déjà pour d'autres familles ; **je ne les ai
pas repris**, pour qu'aucun préfixe ne porte deux sens. `LON` n'est pas neuf : `LON-CE1-5` suit
`LON-CE1-4`.

**Le compte, lu sur l'outil** : voir la note datée de ce jour en tête de
`squad/decisions/REGISTRE-identifiants.md`, qui recopie ce que
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` affiche sur ce diff. Je n'écris pas ici un
chiffre que je n'ai pas lu sur l'outil.

---

## 12. Récapitulatif des vingt-six verdicts

| Ligne | Intitulé abrégé | Verdict | Plafond atteignable |
|---|---|---|---|
| `FRA-CE1-01` | graphèmes complexes | **ACCEPTÉ SOUS CONDITIONS** `GRC-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-02` | valeur d'une lettre | **ACCEPTÉ SOUS CONDITIONS** `VLE-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-03` | mots fréquents et irréguliers | **ACCEPTÉ SOUS CONDITIONS** `MFR-CE1-1` à `-3` - *reconnaissance immédiate non servie* | `en cours` |
| `FRA-CE1-04` | texte narratif | **ACCEPTÉ SOUS CONDITIONS** `RCT-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-05` | pronom et substitut | **ACCEPTÉ SOUS CONDITIONS** `PRN-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-06` | inférence simple | **ACCEPTÉ SOUS CONDITIONS** `INF-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-07` | texte documentaire, consigne | **ACCEPTÉ SOUS CONDITIONS** `DOC-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-08` | ordre des mots d'une phrase | **ACCEPTÉ SOUS CONDITIONS** `OMP-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-09` | repérer une erreur | **ACCEPTÉ SOUS CONDITIONS** `RLP-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-10` | phrase et type de phrase | **ACCEPTÉ SOUS CONDITIONS** `TPH-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-11` | négation | **ACCEPTÉ SOUS CONDITIONS** `NEG-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-12` | classe d'un mot | **ACCEPTÉ SOUS CONDITIONS** `CLM-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-13` | verbe et sujet | **ACCEPTÉ SOUS CONDITIONS** `VSJ-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-14` | accord sujet-verbe | **ACCEPTÉ SOUS CONDITIONS** `ACV-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-15` | accords du groupe nominal | **ACCEPTÉ SOUS CONDITIONS** `GNA-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-16` | `a`/`à`, `et`/`est` | **ACCEPTÉ SOUS CONDITIONS** `HOM-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-17` | présent | **ACCEPTÉ SOUS CONDITIONS** `PRS-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-18` | futur | **ACCEPTÉ SOUS CONDITIONS** `FUT-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-19` | passé composé | **ACCEPTÉ SOUS CONDITIONS** `PCO-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-20` | situer dans le temps | **ACCEPTÉ SOUS CONDITIONS** `TPS-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-21` | mots invariables et fréquents | **ACCEPTÉ SOUS CONDITIONS** `MIV-CE1-1` à `-3` - *écrire sans modèle non servi* | `en cours` |
| `FRA-CE1-22` | graphie selon la lettre qui suit | **ACCEPTÉ SOUS CONDITIONS** `GRL-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-23` | ordre alphabétique | **ACCEPTÉ SOUS CONDITIONS** `ALP-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-24` | familles, préfixes, suffixes | **ACCEPTÉ SOUS CONDITIONS** `FAM-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-25` | synonymes et contraires | **ACCEPTÉ SOUS CONDITIONS** `SYC-CE1-1` à `-3` | `couvert-valide` |
| `FRA-CE1-26` | catégories | **ACCEPTÉ SOUS CONDITIONS** `CTG-CE1-1` à `-3` | `couvert-valide` |

**Compte** : 0 ACCEPTÉ sans condition propre, **26 ACCEPTÉ SOUS CONDITIONS** (dont 24 à
`couvert-valide` atteignable et 2 plafonnées `en cours`), **0 NON COUVRABLE PAR CE PRODUIT**. Les cinq
attendus que ce produit ne peut pas servir sont `FRA-CE1-27` à `FRA-CE1-31`, sortis le 2026-10-02 avec
leur motif (section 0.2).

**Signé** : `nadia-prof`, s48, instance B, 2026-10-03.
