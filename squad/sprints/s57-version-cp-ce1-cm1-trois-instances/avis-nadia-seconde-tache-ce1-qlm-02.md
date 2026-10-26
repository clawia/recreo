# s57 - avis sur la seconde tache de `ce1-qlm-ce-que-mangent-les-animaux` (`S57-BR-QLM-CE1-02`, critere 6, `FER-3`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-26, ecrit a partir de 14:03 (UTC+01:00), lue par `date`.
- **Arbre** : branche `s57-p5-nadia-f5`, base `round-two` a `e20194d4`. La proposition lue est sur `s57-p5-camille-f5`
  (`878a904c`, non ancetre de ma base), `docs/backlog/backlog.md`, story `S57-BR-QLM-CE1-02`, critere 6, lue par `git show`.
- **Entrees** : `RAL-CE1-1`, `RAL-CE1-2` (`ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`, 3.2) ;
  `FER-1`, `FER-3` dans leurs mots (meme fichier, section 2, et `ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md`,
  section 2) ; `QC5`, `QC9` (backlog, annotation du 2026-10-25) ; voisins lus : `cm1-sciences-regimes-alimentaires.yaml`,
  `cm1-sciences-chaine-alimentaire.yaml`, et les paquets `cp-qlm-*` sur ce que mange un etre vivant (`cp-qlm-cycle-de-vie`,
  `cp-qlm-hygiene`).
- **Type** : avis prealable sur une proposition de critere. **Aucun Done, aucun verdict de lot, aucun identifiant neuf, aucune
  condition neuve.** Je ne modifie ni la story ni le code : `camille-po` reecrit le critere 6.
- **Audit degrade, dit** : il n'y a rien a jouer, le paquet n'existe pas. Je juge une forme de tache, pas des items.

## Resume

| Question | Reponse | En une ligne |
|---|---|---|
| 1. Aliment nomme lisible, sans seconde reponse defendable | **oui, reformule** | lisible au CE1 ; sans seconde reponse seulement si B ne porte que des animaux a une seule categorie et des distracteurs de l'autre categorie |
| 2. B distincte de A (`QC9`) | **oui** | A classe l'animal, B reconnait un aliment concret ; a condition qu'aucune proposition de B ne soit une categorie |
| 3. La bonne seconde tache | **oui** | c'est la seule que le texte de `RAL-CE1-1` permet ; je ne rouvre pas ma condition pour une autre |
| 4. La sonde suffit comme garde de `FER-3` | **non, reformule** | un recompte sur le commentaire garde une declaration ; le recompte doit porter sur les items, et l'operation mentale reste a moi |

Le critere 6 est reecrit en section 5. Il garde les deux taches de `camille-po` ; il ajoute les bornes de la tache B et deplace le
recompte du commentaire vers les items.

## 1. L'aliment nomme est-il lisible au CE1, sans seconde reponse defendable ?

**Lisible : oui.** *"Que mange la vache dans le pre ?"* fait sept mots ; *de l'herbe*, *des vers de terre*, *des souris* sont des
mots qu'un enfant de CE1 lit et connait. C'est meme plus concret que la tache A, ou *vegetaux* est un mot admis mais abstrait.
Une seule exigence de forme : les trois propositions portent le meme type d'article (*de l'*, *des*, *du*), sinon la grammaire
designe la reponse.

**Sans seconde reponse defendable : seulement si la tache B est bornee.** Le risque n'est pas l'enonce, il est le choix des
animaux et des distracteurs. Un animal qui mange beaucoup de choses n'a presque aucun distracteur sur : tout aliment qu'on lui
oppose, il le mange peut-etre. Lu animal par animal :

| Animal | Categorie (a sourcer, `QMT-CE1-2`) | Tache B | Motif |
|---|---|---|---|
| poule | les deux | **non** | graines, vers, insectes, herbe, et meme petits cailloux avales : aucun aliment propose n'est surement faux |
| cochon, sanglier, ours brun | les deux | **non** | mangent presque tout ce qu'on leur propose |
| merle, mesange, mulot, souris | les deux | **non** | vers ou insectes et graines ou baies selon la saison |
| ecureuil | les deux | **non** | enseigne comme mangeur de noisettes, il mange aussi des oeufs et des insectes : meme motif que le renard et le herisson de `RAL-CE1-2`. Classe *des vegetaux* en tache A, je le refuse a la lecture (`RAL-CE1-1`, source) |
| vache, mouton, chevre, cheval | des vegetaux | **oui** | bonne reponse liee au lieu que l'enonce dit (*dans le pre* : *de l'herbe*). **Jamais *du foin* a cote de *de l'herbe*** : le foin est de l'herbe sechee, que la vache mange a l'etable, c'est une seconde reponse |
| lapin | des vegetaux | **oui, si l'enonce dit le pre ou le champ** | un lapin de compagnie mange des granules du commerce, que `RAL-CE1-2` exclut ; l'enonce de lieu le ferme deja |
| escargot, chenille | des vegetaux | **oui** | feuilles, salade |
| chouette, hirondelle, araignee, grenouille | d'autres animaux | **oui** | souris, mouches, insectes. *La grenouille* seulement : le tetard mange surtout des vegetaux, il n'entre pas dans ce paquet |
| loup | d'autres animaux | oui, deconseille | `cm1-sciences-regimes-alimentaires` le dit carnivore, `CYC-1` tient ; il n'est pas un animal familier d'un enfant de CE1 |

Les deux cas que la proposition cite se resolvent donc ainsi : **la vache entre en tache B, sans foin a cote de l'herbe ; la poule
n'y entre pas**, elle sert la tache A avec *les deux*.

**La regle qui ferme la seconde reponse**, sans condition neuve : en tache B, l'animal a une seule categorie selon sa source, et ses
deux distracteurs sont des aliments **de l'autre categorie** (pour la vache : des animaux ; pour la chouette : des vegetaux). C'est
la seule famille de distracteurs dont la source de la categorie suffit a dire que l'animal ne la mange pas. Un distracteur de la
meme categorie que la bonne reponse (*des pommes* pour la vache) demande une source de plus pour etre faux, et souvent il ne l'est pas.
C'est l'application de *"une seule acceptable"* (`TRV-CE1-2`) et de *"pas d'animal au regime discute"* (`RAL-CE1-2`), pas une barre
nouvelle.

**La liste des animaux** est fermee et ecrite dans le commentaire du paquet, animal par animal, avec sa categorie et sa source.
*"Animaux de la nature ou de la ferme"* est un domaine, pas une liste : `QMT-CE1-2` demande deja la source de chaque regime, la liste
en est la consequence.

## 2. La tache B est-elle une operation mentale distincte de la tache A ?

**Oui.** `FER-3` porte sur l'operation demandee, pas sur la forme de la phrase (s28, section 2) :

- **A** part d'un animal et demande une categorie : l'enfant generalise ce qu'il sait de l'animal (*elle mange de l'herbe, des
  feuilles : des vegetaux*).
- **B** part du meme animal et demande, parmi trois aliments concrets, celui qu'il mange : c'est le verbe de la ligne, *relier un
  animal a ce qu'il mange*. L'enfant reconnait un aliment ; s'il passe par la categorie, il doit en plus classer les trois aliments
  eux-memes, ce que A ne lui demande jamais.

C'est la paire que j'ai deja retenue sur `cp-qlm-vivant-non-vivant` a s28 (categoriser, puis selectionner) : deux taches, une meme
connaissance.

**Ce qui la ferait retomber en une seule tache** : une proposition de B qui serait une des trois categories (*"des vegetaux"* parmi
deux aliments). L'item redeviendrait A avec un mot change. Le critere reecrit le refuse.

## 3. Est-ce la bonne seconde tache ?

**Oui.** `RAL-CE1-1` dit, dans mes mots : *"La bonne reponse est des vegetaux, d'autres animaux ou les deux, ou un aliment nomme."*
Une seconde tache ne peut avoir pour bonne reponse que l'une de ces quatre choses. Les trois premieres sont la tache A ; il reste
l'aliment nomme, et c'est ce que `camille-po` propose.

J'ai examine l'autre seconde tache que la ligne suggere : partir d'un aliment ou d'une categorie et choisir l'animal (*"Lequel de ces
trois animaux mange d'autres animaux ?"*). Elle est defendable, et elle est plus nettement distincte de A. Mais sa bonne reponse est
un animal, que `RAL-CE1-1` n'autorise pas. La retenir demanderait de rouvrir ma propre condition apres coup, pour une tache qui
n'apprend pas davantage. **Je ne la retiens pas**, et la proposition de `camille-po` n'a pas a etre remplacee.

## 4. La sonde suffit-elle comme garde de `FER-3` ?

**Non, telle qu'ecrite.** *"Recompte sur le commentaire du paquet"* compte ce que le paquet declare, pas ce qu'il sert : un
commentaire qui annonce 4 items de B au-dessus de 2 items de B passe. C'est la famille du garde-fou qui garde sa propre declaration.

**Reformulation.** Les deux taches se distinguent sur les items eux-memes, sans commentaire : un item dont la bonne reponse est
*des vegetaux*, *d'autres animaux* ou *les deux* pose A, tout autre item pose B. Un test peut donc recompter sur les items, et
refuser un item de B dont une proposition est une categorie (section 2). La forme est a `theo-archi` ; si elle n'est pas possible,
*"non garde : `FER-3`"*, lu par moi.

**Ce qui reste a moi quelle que soit la forme** (`QC9`) : que chaque item de B soit bien une reconnaissance d'aliment et non une
devinette ; qu'aucun distracteur de B ne soit mange par l'animal ; la categorie de chaque animal contre sa source et contre
`cm1-sciences-regimes-alimentaires` (`CYC-1`). **Sur ces trois proprietes, je suis la seule garde.** Un recompte vert ne les couvre
pas.

## 5. Texte du critere 6, a mettre a la place de la proposition

Texte exact, que `camille-po` reporte dans la story ; la proposition du 2026-10-26 reste dans l'historique du depot.

> 6. **`FER-3`, deux taches** [arbitre par `nadia-prof` le 2026-10-26, `avis-nadia-seconde-tache-ce1-qlm-02.md` ; remplace la
>    proposition du meme jour]. Cadre : la seconde tache n'utilise que ce que `RAL-CE1-1` autorise deja (*"ou un aliment nomme"*),
>    ses conditions `RAL-CE1-1` et `RAL-CE1-2` et le cadre de la story. Aucune competence, aucun identifiant, aucune condition
>    nouvelle.
>    - **Tache A, categorie** : l'enonce nomme un animal et le lieu ou il mange (*"Que mange la vache dans le pre ?"*) ; les trois
>      propositions sont *des vegetaux*, *d'autres animaux*, *les deux* ; la tache porte les trois categories en bonne reponse
>      (critere 1, `FER-1`).
>    - **Tache B, aliment nomme** : meme forme d'enonce ; la bonne reponse est un aliment que l'animal mange a ce lieu (*de
>      l'herbe* pour la vache dans le pre) ; les trois propositions sont trois aliments au meme type d'article, et aucune n'est
>      *des vegetaux*, *d'autres animaux* ou *les deux*. N'entrent en tache B que les animaux dont la source donne une seule
>      categorie ; un animal classe *les deux* (la poule) n'y entre pas. Les deux distracteurs sont des aliments de l'autre
>      categorie (pour la vache, des animaux). Jamais deux aliments que l'animal mange (*du foin* a cote de *de l'herbe*).
>    - **Liste des animaux** : fermee, ecrite dans le commentaire du paquet avec, pour chaque animal, sa categorie et sa source
>      (`QMT-CE1-2`) ; aucun des quatre animaux exclus par `RAL-CE1-2`.
>    - **Entree / sortie observables** : le commentaire du paquet nomme les deux taches et leur compte (`QC5`). Le recompte porte
>      sur les items, pas sur le commentaire : un item dont la bonne reponse est *des vegetaux*, *d'autres animaux* ou *les deux*
>      compte pour A, tout autre item pour B ; la moins servie porte au moins 3 items. Aujourd'hui 0 et 0, **rouge**. Sondes : un
>      paquet dont tous les items posent A est refuse ; un paquet dont B ne porte que 2 items est refuse ; un item de B dont une
>      proposition est *des vegetaux*, *d'autres animaux* ou *les deux* est refuse. Forme a `theo-archi` ; si elle n'est pas
>      possible, *"non garde : `FER-3`"* et lu par `nadia-prof`.
>    - **Lu par `nadia-prof`, aucun test** (`QC9`) : que A et B soient deux operations distinctes sur chaque item ; qu'aucun
>      distracteur de B ne soit une seconde reponse defendable ; la categorie de chaque animal contre sa source et contre
>      `cm1-sciences-regimes-alimentaires` (`CYC-1`).

## 6. Ce que j'ai vu en lisant les voisins, hors de cette story

- **`NRC-CE1-1`** : aucun paquet de CP ne demande ce que mange un animal (`cp-qlm-cycle-de-vie` demande *"que fait-il ?"* ->
  *"il se nourrit"*, autre tache). Aucun recouvrement a craindre de ce cote.
- **`CYC-1`** : `cm1-sciences-regimes-alimentaires` classe la vache, le lapin et la chevre herbivores, le loup carnivore, le
  sanglier et l'ours omnivores ; le tableau de la section 1 ne les contredit pas. `cm1-sciences-chaine-alimentaire` pose deja
  *"Dans la foret, que mange le lapin ?"* -> *"de l'herbe"* : la tache B de CE1 est coherente avec lui. Je lirai a l'audit qu'aucun
  item de CE1 n'en soit la copie.
- **Hors perimetre, signale** : dans `cm1-sciences-chaine-alimentaire`, le premier item (*"Dans la foret, qui mange les glands du
  chene ?"*, bonne reponse *l'ecureuil*) porte *le mulot* en distracteur ; le mulot mange aussi des glands, c'est une seconde
  reponse defendable. Ce n'est pas un verdict : je le signale a `camille-po` via `sofia-sm`, pour l'instance qui audite le CM1.
