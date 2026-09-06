# Passe transverse de s30 - les proprietes de portee depot

**Auteur** : `nadia-prof`, instance A (celle qui porte l'accumulation, `PAR-7`).
**Date** : 2026-09-06.
**Arbre** : `round-two`, worktree `s30-nadia-A`, HEAD `dbbfe129`.
**Prix contre ma borne** : **0,50**, exactement le poste que j'avais nomme d'avance
(`ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md`,
section 3.3).
**Statut** : ce document ne prononce aucun Done pedagogique. Il mesure des proprietes que **aucun
audit de lot ne peut voir**, et il route ce qu'il trouve.

`yanis-qa` a nomme l'absence de ce fichier comme un critere de `S30-11` non encore tenu. Il existe.

---

## 0. Conditions - j'ai joue, et je dis la forme exacte

**Joue le 2026-09-06**, jar reconstruit **depuis mon propre worktree** (regle neuve du sprint) et
lance **sur le port 8082** :

- `bandit-manchot-vivant-non-vivant-cp` - partie complete, erreurs volontaires poussees jusqu'a la
  revelation sur deux items, ecran de fin atteint ;
- `bandit-manchot-droit-devoir-cm1`, `bandit-manchot-fait-opinion-cm1`,
  `bandit-manchot-objet-technique-cp`, `bandit-manchot-cycle-de-vie-cp`,
  `bandit-manchot-corps-sens-cp`, `bandit-manchot-anglais-cm1` - parties jouees volontairement mal ;
- `bandit-manchot-histoire-geographie-cm1` - **huit parties completes** jusqu'a l'ecran de fin, pour
  echantillonner plusieurs formes de bonne reponse a la revelation finale ;
- `saisie-mot-dicte-cp` et `saisie-complements-10-cp` - parties completes sur **l'autre mecanique**,
  jusqu'a l'ecran de fin, avec une saisie fausse a chaque manche.

**Audit degrade sur un point, et je le nomme comme toujours** : j'ai joue **par requetes HTTP**, donc
j'ai lu le **HTML servi**, pas la page rendue par un navigateur. Cela couvre les textes, l'ordre des
ecrans, la presence et l'absence de chaque bloc, l'enchainement apres une erreur. Cela **ne** couvre
pas la couleur, le contraste, la taille reelle ni la position. **Rien de ce document ne repose sur le
rendu visuel.**

**Une correction de methode, faite avant de m'appuyer sur un constat.** Ma premiere serie de parties
d'histoire-geographie n'atteignait jamais l'ecran de fin, et j'ai failli l'ecrire comme un defaut du
produit. C'etait **mon outil** : je repostais la valeur du bouton **telle qu'elle est echappee dans
le HTML**, apostrophes comprises, de sorte que le serveur ne reconnaissait pas la proposition.
Corrige, les parties se terminent normalement. **Je le consigne parce qu'un faux defaut publie coute
plus cher qu'un vrai defaut tu.**

**Done technique acquis, mesure par moi et non reconduit** : `bash outils/mvn-jdk21.sh -B clean
verify` sur le **reacteur complet** depuis mon worktree, **BUILD SUCCESS**. Comptage independant des
rapports XML de tous les modules : **179 rapports, 1321 tests, 0 echec, 0 erreur, 0 ignore**, machine
virtuelle rapportant **21.0.10**. Concorde avec `yanis-qa` et `theo-archi`.

---

## 1. Premiere famille - les autres endroits ou un gabarit compose une phrase autour d'un champ de contenu

**C'est la famille de `REV-1`**, que j'avais ajoutee moi-meme a la portee D en ecrivant, a s29 :
*"il en existe peut-etre d'autres du meme genre, produits par la rencontre d'un champ de contenu et
d'un gabarit, et je ne les ai pas cherches."* Je les ai cherches.

### 1.1 La methode, pour qu'un tiers refasse la mesure sans moi

Trois recherches, toutes refaites aujourd'hui :

1. **Toutes les expressions `th:text` et `th:utext` des six gabarits** (`accueil.html`,
   `fragments/manche.html`, `fragments/retour.html`, `fragments/saisie.html`,
   `fragments/confirmation.html`, `fragments/erreur.html`) - je retiens celles qui **concatenent** un
   litteral de gabarit avec une valeur ;
2. **Toutes les regles `content:` de `recreo.css`** - une feuille de style peut injecter du texte a
   cote d'une valeur de contenu, et ce texte-la n'apparait dans aucun gabarit ;
3. **Toutes les concatenations de chaines dans le code Java de production** des quatre couches, hors
   messages d'erreur techniques et identifiants.

### 1.2 Le resultat, complet

| Ou | Ce qui est compose | Verdict |
|---|---|---|
| `fragments/manche.html`, `fragments/retour.html` | `'Score : ' + ${score.reussites()}` | **sain.** La valeur est un entier ; aucune forme grammaticale en jeu |
| `accueil.html` | `'Pour le ' + ${niveauActif}` | **sain.** La population est close et connue - *CP* et *CM1*, deux valeurs, toutes deux compatibles avec *le* |
| `fragments/saisie.html` | `'Tu as ecrit : ' + ${chaineJugee} + '.'` | **la seule survivante de la famille**, section 1.3 |
| `recreo.css` | **aucune** regle `content:` ne pose de texte | **sain** |
| Java de production | **aucune** concatenation ne produit de texte lu par l'enfant | **sain** |

**`ADR-0083` tient.** Partout ailleurs, la valeur de contenu occupe **seule et entiere** le texte de
son element : `${enonce}`, `${bonneReponse}`, `${reponseRevelee}`, `${enonceRevele}`,
`${texteSupport}`, `${indice}`, `${explication}`, `${consigne}`, `${jeu.titre()}`, `${proposition}`,
`${tentative}`, `${formeDeReference}`, `${blocAdulte.stimulus()}`, `${blocAdulte.miseEnGarde()}`.
**Quatorze champs, zero enchassement.** C'est le contraire de la situation de s29.

### 1.3 `SAI-R1` - le point final que le gabarit ajoute a ce que l'enfant a ecrit

**Reserve nouvelle, datee du 2026-09-06, non bloquante.**

**Vu a l'ecran, sur les deux jeux de saisie**, en ecrivant volontairement une reponse fausse terminee
par un point :

> *"Tu as ecrit : zzz.."*

Le gabarit ajoute un point a la chaine saisie **quelle qu'elle soit**. Quand l'enfant a lui-meme tape
un point, l'ecran lui montre **deux points a la suite**, dans la phrase precise ou le produit lui
renvoie son propre geste.

**Pourquoi ce n'est pas bloquant, et pourquoi je l'ouvre quand meme.** L'incidence est faible : un
enfant de CP qui ecrit un mot dicte ou un nombre ne tape pas de point final. Rien de faux n'est
enseigne, l'enfant n'est pas bloque. Mais **c'est le dernier endroit du depot ou un texte que le
produit ne controle pas entre dans une phrase que le produit compose**, et c'est exactement le
mecanisme de `REV-1`, lequel a mis trois sprints et trois codes (`REV-1`, `REV-2`, `REV-3`) a se
resorber. **Le fermer maintenant coute une ligne de gabarit ; le laisser ouvert, c'est garder vivante
la seule instance restante d'une famille qui a deja produit quatre defauts.**

> **`SAI-R1`. Propriete a tenir** : la ponctuation finale d'une phrase qui cite ce que l'enfant a
> ecrit n'est pas ajoutee par le gabarit a une chaine dont le gabarit ignore la forme. **Je donne la
> propriete, jamais la forme** : la cause est technique et son traitement appartient a `theo-archi`.
> **Regime : mot pour mot une fois le correctif livre, 0,25.**

---

## 2. Deuxieme famille - l'homogeneite de registre au-dela de la consigne

Objet nomme d'avance : *"titres, messages de reussite, ecrans de fin - explicitement hors du
perimetre de `CONS-1`, et ou un titre m'avait deja saute aux yeux en passant (`OBJ-R1`)."*

### 2.1 Une correction d'un constat de moi, avant de m'en servir

**Ce que j'ai ecrit le 2026-09-06 dans `OBJ-R1`** :

> *"S'y ajoute que le titre est le seul du catalogue a etre nominal sans article."*

**C'est faux, et je l'ai verifie titre par titre sur les 58 entrees d'aujourd'hui.** Le registre
nominal sans article compte **six** entrees : *Petits problemes*, *Histoire et geographie*, *Anglais*,
*Droits et devoirs*, *Signal et information*, *Melanges et separations*. Les quatre premieres
existaient deja au moment ou j'ecrivais que *Fonction d'un objet* etait le seul.

**Consequence, et elle joue contre moi** : ce troisieme motif d'`OBJ-R1` etait sans fondement. Les
deux autres tenaient et tiennent - *fonction* n'est pas au programme de lecture du CP, et le titre
promettait plus etroit que le contenu -, ils suffisaient a eux seuls, et le titre livre est bon.
**Mais je n'avais pas le droit de le dire, et je n'ouvre aucune reserve sur les six titres nominaux
sans article** : un sous-registre de six entrees est un registre, pas une derive. **Une barre que
j'ai posee sur un chiffre faux, je la retire, je ne la rehabille pas.**

### 2.2 Les textes fixes que l'enfant lit, releves un par un sur les six gabarits

| Ecran | Ce qui est ecrit |
|---|---|
| Accueil | *Les jeux*, *Jouer*, *Il n'y a pas de jeu pour le moment.* |
| Manche | *Ce n'est pas ca. Essaie encore !*, *Bravo !*, *Ce n'est pas ca.*, *Appuie sur la bonne reponse :*, *Adulte, a voix haute :* |
| Saisie | *Je reponds*, *Continuer*, *On ecrit :*, *Tu as ecrit : ...* |
| Fin de partie | *Bravo, c'est juste !*, *Tu n'as pas trouve.*, *La bonne reponse :*, *C'est fini !* |
| Confirmation de sortie | *Si tu pars, ta partie s'arrete.*, *Continuer la partie* |
| Erreur | *Oups ! Le jeu a un souci.*, *Recommencer* |

**Deux ecarts apparents de registre, et je tranche les deux ici plutot que de les laisser trainer.**

- **La reponse revelee porte deux libelles selon l'ecran** : *"Appuie sur la bonne reponse :"* sur la
  manche, *"La bonne reponse :"* a la fin de partie. **Ce n'est pas un ecart, c'est une distinction
  juste** : sur la manche l'enfant a un geste a faire, a la fin il n'en a plus. Les deux partagent le
  noyau *la bonne reponse*. **Aucune demande.**
- **La mecanique de saisie dit *"On ecrit :"* la ou le bandit manchot dit *"la bonne reponse"***,
  pour la meme fonction, sur deux jeux qu'un meme enfant de CP enchaine. **Je ne demande pas
  l'uniformite** : sur une dictee, *"On ecrit :"* nomme l'acte orthographique et vaut mieux que *"la
  bonne reponse"*. **Une homogeneite qui coute de la precision n'est pas un progres.** Aucune
  demande.

### 2.3 `ERR-R1` - le seul texte du produit qui sorte du registre de l'enfant

**Reserve nouvelle, datee du 2026-09-06, non bloquante.**

> *"Oups ! Le jeu a un souci."*

C'est le seul texte servi a l'enfant qui emploie **un mot de registre familier** (*un souci* au sens
de *une panne*) et **une interjection** que rien d'autre dans le produit n'emploie. Deux motifs :

1. **Le mot n'est pas lisible dans son sens au CP.** *Souci* est un mot que l'enfant rencontre
   d'abord comme *inquietude* et comme nom de fleur ; l'emploi au sens de *panne* est un usage
   familier d'adulte. Un enfant de six ans qui decode *le jeu a un souci* ne comprend pas ce qu'on lui
   dit.
2. **Cette phrase ne prepare pas l'enfant a l'action que l'ecran lui offre.** L'ecran porte
   *Recommencer*, ce qui est bien ; la phrase qui le precede ne l'y conduit pas.

**Pourquoi ce n'est pas bloquant** : l'ecran est rare, il n'enseigne rien de faux, et le bouton de
sortie est la. **Pourquoi je l'ouvre quand meme** : c'est un texte lu par un enfant, donc ma porte, et
il n'est jamais passe devant moi - il date des premiers sprints et aucune fiche ne le mentionne. **Je
ne fournis pas de texte** : le remplacement doit dire, en mots de CP, que le jeu s'est arrete et qu'on
peut recommencer. **Regime : mot pour mot une fois propose, 0,25.**

---

## 3. Troisieme famille - les collisions d'identifiants mintes par s30

Objet nomme d'avance, **faute du registre de `PAR-5` qui n'existe toujours pas**. Deuxieme sprint de
suite ou je mesure a la main ce qu'un registre rendrait gratuit.

### 3.1 La methode

Extraction de tout motif `PREFIXE-<jeton>[-<nombre>]` sur l'ensemble des `.md`, `.java`, `.yaml`,
`.html` et `.sh` du depot, hors `.git` et `target`, puis regroupement par prefixe. Verification
separee des doublons de numero sur `squad/decisions/adr/` et `squad/decisions/produit/`.

### 3.2 Le resultat

**Aucune collision.** Ni entre deux objets de meme identifiant, ni dans la numerotation des `ADR` et
des `DEC`.

**Et la mesure fait apparaitre une convention que personne n'a ecrite mais que tout le monde
respecte** - c'est le fait utile de cette section :

| Forme | Ce que l'identifiant designe | Exemples |
|---|---|---|
| `PREFIXE-<n>` | une **condition** que je pose | `SCI-1` a `SCI-4`, `POS-1` a `POS-6`, `FER-1` a `FER-3`, `PAR-0` a `PAR-10`, `CYC-1` |
| `PREFIXE-R<n>` | une **reserve** ouverte sur un paquet | `HG-R1` a `HG-R7`, `DRO-R1` a `DRO-R3`, `FOP-R1` a `FOP-R3`, `OBJ-R1`, `VIV-R2`, `CYV-R1` |
| `PREFIXE-<NIVEAU>-<nn>` | une **ligne de referentiel** | `HG-CM1-01`, `SCI-CM1-01`, `EMC-CM1-07`, `QLM-CP-01` |

Le cas qui aurait pu casser est `SCI` : s30 y minte a la fois `SCI-4` (ma condition) et `SCI-CM1-01`
a `SCI-CM1-20` (les lignes de referentiel du lot de l'instance B). **Les deux formes se
distinguent**, exactement comme `HG-R6` se distingue de `HG-CM1-06`. **Rien a corriger.**

### 3.3 Ce que cela ne dit pas

**Cette mesure constate qu'aucune collision n'a ete creee ; elle ne construit pas le registre.** Le
risque que `PAR-5` couvre reste entier, il grandit encore ce sprint - `SAI-R1` et `ERR-R1` sont
ouvertes par ce document, d'autres le sont par mes verdicts - et **c'est la seconde des deux
conditions qui bloquent la levee de ma retenue de 1,0** (section 1.4 de mon arbitrage prealable).
**Route a `theo-archi`, deuxieme sprint consecutif.**

---

## 4. Quatrieme axe - ma propre regle etendue, verifiee sur toute sa population

**Ce n'etait pas dans l'objet annonce, et il est juste que je dise pourquoi je l'ai fait quand meme** :
j'ai etendu `DRO-R2` a **tout item binaire du depot** le 2026-09-06, dans mon arbitrage prealable,
section 1.2. Une regle de portee depot que personne ne mesure est un souhait, et je serais mal placee
pour le reprocher a quiconque. La mesure entre dans le prix deja paye de cette passe.

**La regle** : sur un item a **deux** propositions, une erreur revele la reponse par elimination ;
l'indice n'a donc plus de reponse a proteger, et **il ne lui reste qu'a enseigner le critere**. Il ne
nomme jamais la categorie.

**La mesure, sur les 58 paquets servis** : recherche du noyau de l'une ou l'autre des deux
propositions dans l'indice du meme item.

| Resultat | Compte |
|---|---|
| Items binaires du depot | **111** |
| Indices qui nomment la categorie | **0** |
| Faux positifs de ma recherche, verifies un par un | **3** |

Les trois faux positifs sont tous dans `cm1-homophones` et viennent de mon test de sous-chaine :
*"Remplace par 'l'avait'"* contient `l'a`, et *"il faut un mot de possession"* contient `ses`. **Les
trois portent le critere**, et le premier porte meme le test de substitution qui est la bonne facon
d'enseigner les homophones au CM1.

> **`DRO-R2` etendue est tenue sur 111 items sur 111. Je ne demande rien a personne sur cet axe.**
> Une passe transverse qui ne trouve rien sur une propriete doit le dire aussi clairement que
> lorsqu'elle trouve : sinon l'equipe ne sait pas ce qui a ete regarde.

---

## 5. Ce que cette passe n'a pas cherche

Ecrit pour que personne ne croie couvert ce qui ne l'est pas. C'est la clause equivalente de ma passe
de s29 qui a rendu la passe d'aujourd'hui possible.

- **Le rendu visuel** : couleur, contraste, taille, position. Jamais couvert par ma methode.
- **Les quatre entrees de catalogue de sciences et technologie CM1** (*Signal et information*, *Qui
  mange qui ?*, *Melanges et separations*, *Comment ca marche ?*) : elles sont dans le perimetre de
  **l'instance B** (`PAR-2`), je ne les juge pas. Je les ai comptees dans le releve de population de
  la section 2.1 parce qu'un comptage de population ne se coupe pas en deux ; **je ne prononce rien
  sur leur contenu.**
- **La coherence de mes propres conditions entre elles** : axe tenu a s29, non rejoue systematiquement
  aujourd'hui faute de prix. Les corrections de moi que porte ce sprint sont venues d'ailleurs - du
  `relecteur-pedagogique` pour deux d'entre elles - pas d'une recherche que j'aurais menee.
- **`CYC-1`** : vacante sur les paquets de mon perimetre ; elle mord sur le lot de sciences CM1 de
  l'instance B, et ce n'est pas a moi de la jouer ce sprint.
- **La contrefacon par tirage** - qu'un item se resolve grace a un autre item du meme paquet - n'a ete
  cherchee que sur les paquets de mon perimetre, pas sur le depot entier. Elle y a rendu deux
  trouvailles (`DRO-R4`, `FOP-R4`, dans mes verdicts) : **c'est la famille de defaut la plus
  productive du sprint, et elle reste non mesuree ailleurs.**

## 6. Ce que je route

| Fait | A qui |
|---|---|
| **`SAI-R1`** - le gabarit de saisie ajoute un point a ce que l'enfant a ecrit, derniere instance vivante de la famille `REV-1` | `theo-archi` pour la cause, `camille-po` pour la story |
| **`ERR-R1`** - *"Oups ! Le jeu a un souci."* n'est pas lisible au CP dans son sens | `camille-po` |
| **Le registre d'identifiants de `PAR-5`** n'existe toujours pas ; aucune collision aujourd'hui, mais la mesure est refaite a la main pour la deuxieme fois | `theo-archi` |
| **La convention de forme des identifiants** (section 3.2) est respectee par tout le depot et ecrite nulle part : elle est le contenu minimal du registre de `PAR-5` | `theo-archi` |
| **La contrefacon par tirage** (un item resolu par un autre item du meme paquet) est mordable en principe - c'est une recherche de chaine - et elle a produit deux reserves ce sprint | `theo-archi`, `camille-po` |
| **Mon propre motif errone dans `OBJ-R1`** (*"le seul titre nominal sans article"*), corrige en section 2.1 | pour information ; aucun verdict ne bouge |
