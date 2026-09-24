# s42 - Avis de faisabilite (`theo-archi`) - cinq lots d'histoire-geographie CM1, de questionner le monde CP, d'arts plastiques CP, d'EMC CM1 et de mathematiques CP

- **Date** : 2026-09-24
- **Base** : `round-two` a `517abd9d`, arbre de travail a ce meme commit, aucun diff de code ni de
  contenu dans cet avis.
- **Perimetre de cet avis** : les **cinq** lots que `DEC-0098`, amendee le 2026-09-24, retient pour
  s42 apres le retrait sans remplacement de `S42-FR-LECTURE-SILENCIEUSE`, sur deux terrains et deux
  instances de `nadia-prof` :
  - **terrain 1, instance A** : `S42-HG-ROIS` (`HG-CM1-05`), `S42-QLM-MATIERE` (`QLM-CP-06`) ;
  - **terrain 2, instance B** : `S42-ART-COULEURS` (`ART-CP-05`), `S42-EMC-SYMBOLES-CM1`
    (`EMC-CM1-05`), `S42-MAT-TEXTUEL` (`MAT-CP-10`, moitie textuelle seulement).
- **Ce que cet avis ne vaut pas** : il ne vaut pas livraison. Il ne fixe aucun contenu, aucun
  critere d'acceptation (`camille-po`), aucun ordre ni aucune affectation nominative (`sofia-sm`),
  et le Done pedagogique reste a `nadia-prof` seule. Les cinq lignes sont deja instruites
  pedagogiquement : je n'ouvre aucune question de contenu, je constate ce que le code et les
  garde-fous permettent aujourd'hui.

---

## 1. Verdicts

| Lot | Ligne | Verdict | Complexite |
|---|---|---|---|
| `S42-HG-ROIS` | `HG-CM1-05` | **faisable sous condition**, sans `ADR` neuf et sans regle neuve. **Le paquet que la story nomme a etendre n'existe plus** : `cm1-histoire-geographie.yaml` a ete retire a s35, scinde en quatre. La condition est de nommer le ou les paquets reels avant d'ecrire, section 5 | basse sur le mecanisme, moyenne sur la cible |
| `S42-QLM-MATIERE` | `QLM-CP-06` | **faisable en l'etat**, sans `ADR` neuf et sans regle neuve. Une adjacence avec du contenu **deja servi** est nommee en section 8.2 ; elle est partiellement discriminee **par un garde-fou existant**, ce qui est l'exception et non la regle | basse |
| `S42-ART-COULEURS` | `ART-CP-05` | **faisable en l'etat**, sans `ADR` neuf et sans regle neuve. `ART-1` est la condition qui fait de ce lot du texte seul, et elle est deja rendue, pas proposee par moi | basse |
| `S42-EMC-SYMBOLES-CM1` | `EMC-CM1-05` | **faisable sous condition**. Les **cinq** symboles du libelle sont deja servis au CP par deux paquets livres aux deux sprints precedents, et **aucun garde-fou du depot ne compare deux paquets**. La condition est que le discriminant soit pose avant d'ecrire, section 8.1 | basse sur le mecanisme, haute sur le discriminant |
| `S42-MAT-TEXTUEL` | `MAT-CP-10` | **faisable en l'etat** sur le mecanisme, sans `ADR` neuf et sans regle neuve. **La divergence que la story signale entre ses deux sources n'existe pas**, section 4. La contrainte reelle du lot est `LON-1`/`LON-2`, section 6.3 | basse sur le mecanisme, moyenne sur la redaction |

**Aucun `ADR` neuf n'est du sur les cinq lots. Aucune regle ArchUnit neuve n'est due.** Les cinq
sont des paquets statiques de type `question` joues au bandit manchot, dans cinq lots de matiere et
de niveau **deja servis**.

## 2. Verification des citations des stories - ouvertes, pas reprises de confiance

Aucun garde-fou ne verifie un numero de ligne : `ToutesLesReferencesDeDecisionResolventTest`
(`ADR-0066`) resout les identifiants `ADR-` et `DEC-`, jamais une citation de ligne. La verification
est donc a la main, ici comme a s40 et a s41.

| Code | Ligne de `MATRICE-couverture.md` citee | Resolue ? | Arbitrage cite | Resolu ? |
|---|---|---|---|---|
| `HG-CM1-05` | 1610 | oui, statut `non couvert`, avec son annotation | `ARBITRAGE-PREALABLE-s27`, sections 7.2 / 7.4 / 7.5 | oui, les trois sections existent aux lignes 386, 548, 574 |
| `QLM-CP-06` | 1564 | oui, statut `non couvert`, **sans annotation** | `ARBITRAGE-PREALABLE-s27`, section 7.1 | oui, ligne 361 |
| `ART-CP-05` | 1741 | oui, statut `non couvert`, avec son annotation `ART-1` | `squad/programme/arts-plastiques/competences-cp.md`, *Jugement de faisabilite* | oui, section en ligne 51 |
| `EMC-CM1-05` | 2002 | oui, statut `non couvert`, avec son annotation `ART-1` | `squad/programme/enseignement-moral-et-civique/competences-cm1.md`, *Jugement de faisabilite* | oui, section en ligne 56 |
| `MAT-CP-10` | 227 | oui, statut `non couvert`, avec sa mention pre-ecrite | `ARBITRAGE-PREALABLE-s32`, section 17.7 | oui, ligne 1180 - **et sa section 17.2, ligne 1026, qui porte la mention et que la story ne cite pas**, section 4 |

Les cinq codes resolvent aussi cote referentiel, sous la forme exacte que le motif
`^- \[([^]]+)] .+` du garde-fou d'`ADR-0081` exige :

| Code | Referentiel | Ligne |
|---|---|---|
| `HG-CM1-05` | `squad/programme/histoire-geographie/competences-cm1.md` | 47 |
| `QLM-CP-06` | `squad/programme/questionner-le-monde/competences-cp.md` | 29 |
| `ART-CP-05` | `squad/programme/arts-plastiques/competences-cp.md` | 35 |
| `EMC-CM1-05` | `squad/programme/enseignement-moral-et-civique/competences-cm1.md` | 42 |
| `MAT-CP-10` | `squad/programme/mathematiques/competences-cp.md` | 31 |

## 3. Le mecanisme est deja la, et les cinq lots de matiere sont servis

Mesure du 2026-09-24 sur les **96** fichiers de
`adapters/adapter-content/src/main/resources/contenu/`, comptes par la `matiere` et le `niveau` de
leur descripteur, un paquet par fichier, `target/` sans objet.

| Lot de matiere vise | Paquets servis aujourd'hui | Precedents directs |
|---|---|---|
| Histoire-geographie / CM1 | 4 | les quatre issus de la scission de s35, section 5 |
| Questionner le monde / CP | 7 | `cp-qlm-objet-technique` (`QLM-CP-07`), `cp-qlm-etats-eau` (`QLM-CP-05`), cinq autres |
| Arts plastiques / CP | 1 | `cp-arts-outils` (`ART-CP-11`) |
| Enseignement moral et civique / CM1 | 4 | `cm1-emc-regle-loi` (`EMC-CM1-08`, s40), trois autres |
| Mathematiques / CP | 13 | `cp-figures-planes`, `cp-semaine`, onze autres |

**Aucune matiere neuve, aucun niveau neuf, aucune mecanique neuve.**

## 4. `MAT-CP-10` - laquelle des deux sources est correcte, et la reponse est : les deux

C'est la question que `sofia-sm` route explicitement. **J'ai ouvert les deux textes aux lignes
citees, et il n'y a pas de divergence entre eux sur la question de fond.**

### 4.1 Ce que chaque source dit, mot pour mot

`ARBITRAGE-PREALABLE-s32`, **section 17.7**, lignes 1182 a 1185 :

> *"Oui, `MAT-CP-10` demande le palier 2, et pour une moitie seulement. Se reperer et se deplacer
> sur un quadrillage est une designation sur un dessin et rien d'autre ne la sert ; decrire un
> deplacement n'est pas une designation et se sert en choix textuel, dans un second paquet. La
> ligne ne ferme donc pas par le palier 2 seul, et la mention du 17.2 est due."*

`MATRICE-couverture.md`, **ligne 227**, corps de la cellule :

> *"se reperer et se deplacer sur un quadrillage est une designation sur un dessin et rien d'autre
> ne la sert ; decrire un deplacement n'est pas une designation et se sert en choix textuel, dans
> un second paquet."*

**C'est la meme phrase.** Les deux sources disent identiquement que la moitie *decrire un
deplacement* est la moitie textuelle et que la moitie *se reperer et se deplacer* est la moitie de
designation. **Sur la question que la story pose - laquelle des deux moities est couverte par le
choix textuel - il n'y a rien a trancher : les deux sources concordent, et la direction que la
story retient est la bonne.**

### 4.2 Ce que la story a lu comme une inversion, et ce que c'est reellement

La story compare la phrase de l'arbitrage a un **autre** element de la meme cellule : la mention de
couverture partielle pre-ecrite,

> *"moitie 'se reperer et se deplacer sur un quadrillage' seule ; la moitie 'decrire un deplacement'
> reste non couverte"*.

**Cette mention n'est pas une affirmation sur la nature des deux moities. C'est le libelle a porter
dans un etat de couverture precis, et l'arbitrage l'ecrit lui-meme**, dans une section que la story
ne cite pas : `ARBITRAGE-PREALABLE-s32`, **section 17.2**, tableau de la ligne 1026, colonne
*"Mention de couverture partielle due"* :

> **`MAT-CP-10`** | *"OUVRE SANS FERMER. Decrire un deplacement, c'est le produire ; l'enfant qui
> appuie sur une case n'a rien decrit. Aucun item de designation ne servira jamais cette moitie"* |
> **due** : *"moitie 'se reperer et se deplacer sur un quadrillage' seule ; la moitie 'decrire un
> deplacement' reste non couverte"*. **Elle tombe le jour ou un second paquet, textuel, sert
> decrire**

La cellule de la matrice recopie cette mention **mot pour mot**, et recopie aussi sa condition
d'application, sous la forme *"due tant que le second paquet textuel n'existe pas"*.

**Conclusion, et c'est la seule que je rends.** La mention pre-ecrite de la matrice est le libelle
du a l'etat *"palier 2 livre, paquet textuel absent"*. Le tableau de la section 17.2 instruit
l'effet du palier 2 : il suppose donc l'ordre *designation d'abord, texte ensuite*. **s42 prend
l'ordre inverse** - il livre la moitie textuelle en premier, le palier 2 n'etant pas engage. Dans
cet ordre-la, c'est la mention **miroir** qui est due, et c'est exactement celle que la story ecrit
dans son critere d'acceptation.

Donc, terme a terme :

| Element | Verdict |
|---|---|
| La direction que la story retient (*decrire* = textuel) | **correcte**, concordante avec les deux sources |
| Le libelle de mention que le critere d'acceptation de la story impose | **correct** pour l'ordre de livraison de s42 |
| Le diagnostic de la story - *"la matrice inverse les deux moities par rapport a la source"* | **inexact** : la matrice recopie fidelement la mention que l'arbitrage prescrit en 17.2, laquelle n'est pas un enonce sur la nature des moities |
| L'etat de la cellule 227 aujourd'hui | **rien n'y est faux** : le statut est `non couvert`, aucune des deux moities n'est servie, la mention y est prospective et non affirmee |

**Ce qui reste donc a faire de cette cellule, et a qui.** Il n'y a **pas** d'ecart de tenue a
corriger aujourd'hui. Il y a une cellule dont la mention pre-ecrite suppose un ordre de livraison
que s42 renverse, et qui devra porter la mention miroir le jour ou `S42-MAT-TEXTUEL` est valide.
**C'est une tenue de matrice, elle appartient a `camille-po`**, qui possede la cellule et qui a
ecrit la story ; je ne touche ni la matrice ni le backlog. Je signale seulement que **la correction
due n'est pas celle que la story annonce** : la story annonce une inversion a corriger, et ce qu'il
y a est une mention a remplacer au moment du Done.

*Pourquoi je ne vais pas plus loin : quel libelle exact la ligne porte a son Done, et si la ligne
passe a `en cours` ou reste `non couvert`, sont des questions de couverture et de tenue produit. Je
dis ce que les deux textes disent ; je ne decide pas ce que la matrice dira.*

## 5. `HG-CM1-05` - le paquet que la story dit d'etendre n'existe plus

**Le fait, verifie sur l'arbre.** La story ecrit *"elle n'ouvre pas de paquet neuf, elle etend
`cm1-histoire-geographie.yaml` a cette seule ligne"*, et la matrice, ligne 1610, cite le meme nom
dans l'annotation de `nadia-prof`. **Ce fichier n'existe pas dans
`adapters/adapter-content/src/main/resources/contenu/`.** Il a ete retire a s35 par `S35-CONS-4`,
qui l'a scinde en quatre, sur une decision de grain rendue conjointement par `camille-po` et
`nadia-prof` (`ADR-0081`) - *"une entree par tache reellement distincte plutot qu'une par ligne de
referentiel"* - et le commentaire d'entete de chacun des quatre paquets porte cette genealogie.

L'etat servi aujourd'hui, avec les lignes que chaque paquet declare :

| Paquet | `lignesDeReferentiel` | Items |
|---|---|---|
| `cm1-histoire-geographie-situer-dans-le-temps` | `HG-CM1-01`, `HG-CM1-02`, **`HG-CM1-07`** | 25 |
| `cm1-histoire-geographie-nommer-en-histoire` | `HG-CM1-03`, `HG-CM1-04`, `HG-CM1-06`, **`HG-CM1-07`**, `HG-CM1-08` | 45 |
| `cm1-histoire-geographie-nommer-en-geographie` | `HG-CM1-11`, `HG-CM1-12`, `HG-CM1-13` | 30 |
| `cm1-histoire-geographie-lire-un-document` | `HG-CM1-14` | 10 |

**Ce que cela change, et ce que cela ne change pas.**

1. **Le lot reste faisable sans `ADR`.** `HG-CM1-05` est conjonctive - *"Nommer **et** situer dans
   le temps"* - et sous la decision de grain, ses deux verbes tombent dans deux paquets differents,
   `nommer-en-histoire` et `situer-dans-le-temps`. **Cette configuration existe deja et elle est
   verte** : `HG-CM1-07` est declaree par ces deux memes paquets. Une ligne servie par deux entrees
   de catalogue n'est donc pas un cas neuf sur ce lot de matiere.
2. **L'allocation de sous-partie n'est pas due, et c'est verifie plutot que suppose.** Mon avis de
   s41, section 5.2, ecrivait le declencheur : *"le jour ou une seconde entree de catalogue
   declarera l'une de ces lignes, l'allocation de sous-partie redevient due"*. **Ce declencheur ne
   se leve pas ici** : `ADR-0098` porte sur `SousPartieDeMathematiquesCe1`, et le mecanisme est
   borne aux mathematiques de CE1 - verifie, la cle `sousPartie` n'apparait que dans les sept
   paquets `ce1-*`. Le declencheur de s41 valait pour six lignes qu'aucun paquet ne declarait ;
   `HG-CM1-05` est dans un lot ou la configuration a deux paquets est deja servie, auditee et
   verte. **Ecrire un `ADR` pour la reconduire serait une abstraction sur un usage deja couvert.**
3. **La condition du verdict** : que le lot nomme sa cible reelle. Etendre `nommer-en-histoire`
   seul ne sert que le verbe *nommer* ; servir les deux verbes du libelle demande les deux paquets.
   **Laquelle des deux voies est prise est une decision de grain de contenu, donc `camille-po` et
   `nadia-prof`, pas moi.** Ce que je dis est que la story designe aujourd'hui un fichier retire, et
   qu'aucune des deux voies ne demande un `ADR`.

**Le cout technique reel de l'extension, mesure.** `HIS-1` **est encodee**, dans
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest`
(`ADR-0079`), et elle se compte **sur le lot de matiere**, jamais sur le fichier. Deux consequences
que la story ne porte pas :

- **Chaque item neuf doit porter son marqueur `# HIS-1: <type>`.** Le garde-fou rougit des qu'un
  item d'un lot soumis n'en porte pas, et le lot histoire-geographie CM1 **est** soumis : mesure de
  ce jour, les quatre paquets portent 110 marqueurs pour 110 items.
- **Le plafond est calcule sur le lot entier, pas sur les items neufs.** Mesure de ce jour :
  **110 items, 23 de restitution, plafond `110 / 3 = 36`**, soit **13 items de restitution de
  marge**. Un lot de dix items neufs porte le plafond a `120 / 3 = 40` : meme entierement de
  restitution, il passerait. **Le critere d'acceptation de la story - *"au plus un tiers"* compte
  *"quand on compte les items"* du paquet - est donc plus strict que la regle encodee, pas moins.**
  C'est `nadia-prof` qui tient la version stricte ; le garde-fou ne tient que la version de lot, et
  le dire evite qu'un vert soit lu comme une preuve du critere.

## 6. Cliquets, plafonds et bornes - mesures et non devines

### 6.1 Ce que cinq lots de contenu textuel **ne** deplacent **pas**

Tous verifies dans le code a `517abd9d`, et **tous identiques a leur valeur de s41** :

| Cliquet | Constante et valeur | Effet de cinq lots de texte |
|---|---|---|
| Poids des dessins | `POIDS_TOTAL_MAXIMUM = 51307`, `POIDS_D_UN_DESSIN_MAXIMUM = 4633`, egalite exacte | **aucun** - aucun fichier ajoute sous `static/illustrations/` |
| Poids des reproductions d'oeuvres | `POIDS_TOTAL_MAXIMUM = 0` | **aucun** |
| Paquets de designation en production | `PLAFOND_DE_DESIGNATION_EN_PRODUCTION = 2` et `PAQUETS_DE_DESIGNATION_PORTANT_LA_DETTE_DE_LISIBILITE = 2` | **aucun** - les cinq lots sont du bandit manchot a choix textuel |
| Dette de longueur d'enonce au CP | `PLAFOND_DE_DETTE = 5`, reparti sur trois paquets nommes ; `PLAFOND_DE_DETTE_DE_FORME = 0` | **aucun** - tout paquet de CP absent de la table est borne a **zero**, section 6.3 |
| Dette de declaration de ligne | `PLAFOND_DE_DETTE = 0`, `DETTE_DE_DECLARATION` vide | **aucun** - a condition que chaque paquet declare sa ligne, ce qui est le cas nominal |
| Homonymes du registre d'identifiants | compte courant inchange : **682** identifiants, **111** prefixes | **aucun**, section 6.2 |

**Aucun plafond existant n'est atteint ni approche par cinq lots de contenu textuel.**

### 6.2 Les identifiants de condition que les cinq lots vont citer sont deja alloues

`ADR-0085` / `PAR-5` : tout identifiant de condition ecrit dans le depot doit etre alloue dans
`squad/decisions/REGISTRE-identifiants.md`, et la ligne de compte courant doit valoir le compte reel
de la table (clause 5). Le commentaire d'entete d'un paquet cite ses conditions ; c'est donc un cout
reel du lot.

Verifie ce jour, un a un, dans le tableau d'allocation de la section 4 du registre : `ART-1`,
`HIS-1`, `HIS-2`, `HIS-3`, `BIA-5`, `PLA-1`, `FER-1`, `FER-2`, `FER-3`. **Les neuf sont alloues,
une fois chacun.**

**Le seul geste qui reste du** : si `nadia-prof` ouvre une reserve neuve en auditant un lot, cet
identifiant doit etre alloue **et** le compte courant remonte dans le meme diff. C'est le cas
nominal, et c'est aussi le point ou deux instances paralleles se rencontrent, section 9.2.

*Cet avis ne donne d'exemple d'aucun identifiant de reserve possible, et c'est delibere :
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` ne peut pas distinguer un identifiant cite
en exemple d'un identifiant pose, et l'y ecrire le rendrait rouge.*

### 6.3 Les trois lots de CP tombent sous la barre de longueur, et sur `MAT-CP-10` c'est la vraie contrainte du lot

`ADR-0089`, `LON-1` et `LON-2`, encodes dans
`bootstrap/src/test/java/com/recreo/archi/AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` : au
CP, un enonce compte au plus **15 mots** (`MOTS_MAXIMUM`) et au plus **2 phrases**
(`PHRASES_MAXIMUM`), la seconde etant la question. Le niveau est lu dans le descripteur
(`NIVEAU_BORNE = "CP"`), jamais dans une liste de paquets : **`S42-QLM-MATIERE`,
`S42-ART-COULEURS` et `S42-MAT-TEXTUEL` sont dans la population des leur premier commit, bornes a
zero enonce trop long.** La table `DETTE_DE_LONGUEUR` ne nomme que trois paquets d'EMC CP ; aucun
lot de s42 n'y entre.

**Aucune barre equivalente n'existe pour le CM1** : `NIVEAU_BORNE` est la seule constante de niveau
du garde-fou. `S42-HG-ROIS` et `S42-EMC-SYMBOLES-CM1` ne sont pas contraints sur cet axe - fait a
connaitre, et il porte une consequence en section 8.1.

**Sur `S42-MAT-TEXTUEL`, cette barre est l'obstacle de conception du lot, et je le nomme avant qu'il
ne soit trouve au premier rouge.** Decrire un deplacement sans image oblige a **poser la scene en
mots** - d'ou part-on, dans quelle direction regarde-t-on, ou va-t-on - **puis** a poser la
question, le tout en 15 mots et 2 phrases. Les autres lots de s42 interrogent un fait qui se pose en
une question courte ; celui-ci interroge une situation qu'il faut d'abord construire. C'est
mesurable et c'est serre.

**Le moyen qui existe deja, et son prix, dit maintenant plutot qu'au premier rouge.** `LON-1` et
`LON-2` s'executent sur les **enonces** seuls - le garde-fou collecte la cle `enonce`, verifie dans
son code. La composante optionnelle `texteSupport` de `Question` peut donc porter la description du
quadrillage hors de l'enonce, et elle a un precedent servi au CP : `cp-texte-narratif`. **Le prix
est `ADR-0072`**, encode dans
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest` :
un paquet dont plus de la moitie des items se gagnent en lisant une seule phrase de support est
rouge, et ce n'est pas un faux rouge. Un paquet dont tous les items partagent la meme description de
quadrillage, avec la reponse lisible dedans, tombe exactement dessus. **Je ne decide pas que ce lot
emploie un `texteSupport`** : c'est une forme de contenu, donc `nadia-prof`. Je constate qu'il est
disponible, sans une ligne de Java, et je nomme son prix.

**Une precision de fabrication qui evite un rouge previsible sur les trois lots de CP.** Les
propositions de `S42-MAT-TEXTUEL` (avancer, reculer, tourner a gauche, tourner a droite), de
`S42-ART-COULEURS` (noms de couleurs) et de `S42-QLM-MATIERE` (noms de materiaux) sont hors du
domaine numerique. Chaque paquet devra porter son entree dans `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`
de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, avec le compte **mesure** : cette
table n'a pas de plafond, deliberement, mais elle est verifiee dans les deux sens et ses comptes
ecrits sont compares au paquet reel. **C'est particulierement a noter sur `S42-MAT-TEXTUEL`**, qui
est un paquet de **mathematiques** dont aucun item n'entre dans le calcul de rang numerique - la
configuration est legitime, elle passe par la porte qui existe, et elle surprendra qui lira le nom
du lot.

## 7. Obstacles deja documentes, lot par lot

Aucun des cinq lots n'est bloque. Les conditions ci-dessous sont toutes deja rendues ; je les
constate, je n'en ajoute aucune.

### 7.1 `HG-CM1-05` - `BIA-5`, `HIS-1`, `HIS-2`, `HIS-3`

`BIA-5` (*"aucun item ne fait porter un jugement de valeur sur un personnage"*), `HIS-2` (*"aucun
item n'a deux reponses defendables"*) et `HIS-3` (*"chaque fait porte sa source dans le commentaire
du paquet"*) **ne sont encodees par rien, et ne peuvent pas l'etre** : juger qu'une reponse est
discutable ou qu'un enonce porte un jugement de valeur demande de lire le sens. C'est la garde de
`nadia-prof`. `HIS-1` est la seule des quatre a etre encodee, sur le lot, section 5.

Ce qui **est** encode et mordra sur ce lot comme sur les autres :
`LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` (`ADR-0086`, `FAB-1a` a `FAB-1c` sur le
depot entier) et `AucunTexteServiNEnchasseLaReponseDansUnePhraseTest` (`ADR-0083`).

### 7.2 `QLM-CP-06` - la vigilance de `nadia-prof` sur le nom de l'objet

`ARBITRAGE-PREALABLE-s27`, section 7.1, ligne 6 : *"Le nom de l'objet dans l'enonce donne souvent la
matiere ('une cuillere en bois'). Distracteurs a surveiller."* **Le cas est en partie pris par un
garde-fou existant** : `AucunTexteServiNEnchasseLaReponseDansUnePhraseTest` (`ADR-0083`) refuse
qu'une valeur de contenu soit enchassee dans une phrase servie. Il prendra *"une cuillere en bois"*
suivi de *"bois"* en bonne reponse. **Il ne prendra pas** les cas ou la matiere se deduit du nom sans
y figurer - *"une vitre"*, *"un pull"* - qui demandent de savoir de quoi est faite une vitre. **Ce
reste-la est la garde de `nadia-prof`**, et le dire vaut mieux que de laisser croire que le
garde-fou couvre toute la vigilance.

### 7.3 `ART-CP-05` - `ART-1`, et c'est la condition qui fait tout le lot

`ART-1` : *"Aucun item d'arts plastiques ne fait porter la reponse par une couleur percue. Pas de
pastille coloree, pas de 'de quelle couleur est... ?' sur une image."* **C'est un arbitrage rendu
le 2026-09-03, pas une economie que je propose**, et la matrice, ligne 1741, le porte deja.

**Aucun garde-fou ne mord `ART-1`** - juger qu'une reponse repose sur une couleur percue demande de
lire le sens. C'est le meme constat qu'a s41 sur `EMC-CP-08`, et il vaut ici a l'identique.

Ce que couterait la voie illustree, chiffre pour qu'on ne la rouvre pas sans le voir : une pastille
coloree entre dans `static/illustrations/` et deplace un cliquet a **egalite exacte**
(`POIDS_TOTAL_MAXIMUM = 51307`, `POIDS_D_UN_DESSIN_MAXIMUM = 4633`), a payer sur la valeur mesuree
et jamais sur une valeur predite (`ADR-0077`). **Et `ART-1` l'interdit de toute facon.** Le lot est
du texte seul.

### 7.4 `EMC-CM1-05` - `ART-1`, `HIS-2`, `HIS-3`, et un piege de fabrication

Memes conditions, memes constats : `ART-1` non encodable, `HIS-2` et `HIS-3` non encodables.

**Le piege, qui est de mon cote et que je nomme.** `HIS-1` est derivee : *"un lot y est soumis des
qu'un de ses items porte le marqueur de cette condition"*. Le lot EMC CM1 **n'est pas soumis
aujourd'hui** - mesure de ce jour, aucun de ses quatre paquets ne porte de marqueur `# HIS-1:`, et
seul le lot histoire-geographie CM1 en porte. **Un seul marqueur `# HIS-1:` pose par analogie dans
le paquet neuf soumettrait tout le lot EMC CM1**, et les quatre paquets servis, qui n'en portent
aucun, deviendraient rouges d'un coup. La story ne cite pas `HIS-1` pour cette ligne ; elle a
raison, et l'auteur du paquet ne doit pas l'ajouter de lui-meme parce qu'il a vu le marqueur chez le
voisin d'histoire.

### 7.5 `MAT-CP-10` - aucune condition pedagogique bloquante

L'arbitrage ne pose aucun refus d'avance sur cette ligne, aucun biais signale. La contrainte du lot
est de redaction et elle est en section 6.3.

## 8. Les adjacences avec du contenu **deja servi**, que rien ne garde

**Le fait d'architecture qui gouverne toute cette section, verifie et non suppose** :
`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` (`ADR-0032`) et
`AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` ont tous deux le **meme paquet** pour perimetre,
leur nom le dit et leur code le fait. **Aucun garde-fou du depot ne compare le contenu de deux
paquets differents.** `ADR-0081` clause 5 garantit qu'un item ne compte que sur une ligne ; elle ne
dit rien de deux items de deux paquets qui parleraient du meme fait.

C'est le meme constat qu'a s41, section 6.4. **A s42 il ne porte pas sur une couture marginale : il
porte sur le lot le moins cher du sprint.**

### 8.1 `EMC-CM1-05` - les cinq symboles du libelle sont deja servis au CP, par deux paquets livres aux deux sprints precedents

**Mesure, pas impression.** Le libelle de `EMC-CM1-05` est *"Reconnaitre les symboles de la
Republique francaise et dire ce qu'ils representent (drapeau, devise, Marianne, hymne, fete
nationale)"*.

- **`cp-emc-symboles`** (`EMC-CP-08`, livre a **s41**, le sprint precedent, 12 items) porte la
  competence *"Reconnaitre et nommer les symboles de la Republique francaise (drapeau tricolore,
  Marianne, La Marseillaise, la fete nationale)"*. Ses douze enonces, tels qu'ils sont servis,
  couvrent **quatre des cinq symboles** du libelle CM1, et **ses quatre derniers items sont
  exactement le second verbe de la ligne CM1** : *"Que represente le drapeau francais ?"*, *"Que
  represente Marianne ?"*, *"Que represente la Marseillaise ?"*, *"Que represente le 14 juillet ?"*.
- **`cp-emc-devise`** (`EMC-CP-09`, livre a **s40**) porte *"Connaitre la devise de la Republique
  et donner un exemple concret de liberte, d'egalite et de fraternite"* : **le cinquieme symbole**,
  la devise, celui qui manque au paquet precedent.

**Les deux verbes de la ligne CM1 - reconnaitre, dire ce qu'ils representent - et ses cinq symboles
sont donc tous servis au CP, a la formulation pres.** Le discriminant du lot de s42 ne peut pas etre
le sujet ; il ne peut etre que le niveau d'exigence.

**Et c'est la que l'architecture ne fournit rien, contrairement au lot voisin.** Comparez :

| Lot | Niveau du paquet neuf | Ce qui le differencie structurellement du contenu voisin deja servi |
|---|---|---|
| `S42-QLM-MATIERE` | CP | **`LON-1` s'applique au paquet neuf** : 15 mots, 2 phrases. Le contenu voisin est au CM1 et n'est pas borne. La differenciation est donc **en partie automatique**, section 8.2 |
| `S42-EMC-SYMBOLES-CM1` | CM1 | **rien**. `NIVEAU_BORNE = "CP"` : aucune barre ne s'applique au paquet neuf, et le paquet voisin, lui, est deja borne a 15 mots. **Rien dans le depot ne force le paquet de CM1 a etre plus exigeant que celui de CP** |

**Ce que le depot en garde : rien du tout.** Un paquet de CM1 qui reprendrait les douze questions du
paquet de CP en les rallongeant passerait **tous** les garde-fous, y compris `ADR-0032`, qui ne
regarde qu'a l'interieur d'un paquet.

**Un fait de routage que j'ajoute, parce qu'il est verifiable et qu'il compte.** `S41-EMC-SYMBOLES`
(`EMC-CP-08`) a ete audite par **l'instance A**, terrain 1 de s41
(`squad/sprints/s41-fusion-emc-sciences-qlm/planning.md`, lignes 26 a 31 et 138).
`S42-EMC-SYMBOLES-CM1` est affecte a **l'instance B**. **L'instance qui auditera le paquet de CM1
n'est pas celle qui a audite le paquet de CP le sprint precedent**, et aucune des deux n'a de moyen
automatique de voir l'autre.

**Ma condition de verdict est donc de procedure, pas de contenu** : que `nadia-prof`, instance B,
lise `cp-emc-symboles` et `cp-emc-devise` **avant** que le paquet de CM1 soit ecrit, et pose le
discriminant. **Je ne dis pas ce qu'il doit etre** - c'est du pedagogique, et je m'arrete la. **Je
ne propose pas non plus de regle** : comparer deux paquets sur le sens demande de lire, et une
regle qui pretendrait le faire serait decorative. **Je le route a `nadia-prof` via `sofia-sm`**,
sous la forme d'un fait a lire avant d'ecrire.

### 8.2 `QLM-CP-06` - un paquet de CM1 sert deja les deux memes taches, et la barre de CP en discrimine une partie

**Mesure.** `cm1-sciences-familles-materiaux` (`SCI-CM1-11`, livre a s31) porte la competence
*"Identifier les principales familles de materiaux et choisir un materiau d'apres une propriete
(resistance, isolation, souplesse, impermeabilite)"*. `QLM-CP-06` dit *"Identifier de quelle matiere
un objet est fait **et** trier des materiaux selon une propriete simple"*. **Ce sont les deux memes
taches, sur le meme vocabulaire de materiaux** - bois, metal, verre, plastique, tissu -, a deux
niveaux.

**Ce qui differencie ici, et qui ne coute rien parce qu'il existe deja.** Les enonces du paquet de
CM1 font 20 a 35 mots (*"Une bouteille transparente, qu'on voit au travers, mais qui se brise en
morceaux tranchants si elle tombe sur un carrelage : de quelle famille de materiau est-elle
faite ?"*). Le paquet de `S42-QLM-MATIERE` est au **CP** : `LON-1` le borne a **15 mots** et `LON-2`
a **2 phrases**, des son premier commit. **La forme du paquet neuf ne peut pas ressembler a celle du
paquet voisin**, et c'est un garde-fou existant qui l'impose, pas une intention.

C'est le seul endroit du sprint ou une adjacence inter-paquets est **partiellement** discriminee par
un automate, et je le note pour la raison inverse de celle qu'on attend : la barre borne la
**forme**, jamais le **fond**. Qu'un item de CP et un item de CM1 interrogent le meme fait reste
invisible au depot. Le fond appartient a `nadia-prof`, instance A.

**Une seconde adjacence, plus faible, que je nomme pour ne pas la laisser trouver.**
`cp-qlm-objet-technique` (`QLM-CP-07`) est servi dans le meme lot de matiere et de niveau, et son
vocabulaire d'objets - ciseaux, parapluie, velo, stylo, balai - est exactement celui qu'un paquet
sur la matiere des objets emploiera. **Les taches sont distinctes** (*a quoi sert un parapluie ?*
contre *de quelle matiere est-il fait ?*), donc `ADR-0081` clause 5 est tenue et rien n'est en
faute. C'est une adjacence de vocabulaire, pas de competence ; elle est nommee, pas opposee.

## 9. Le risque technique de deux instances en parallele

**Precision de perimetre** : `DEC-0098` parle de deux instances de `nadia-prof` **auditant** en
parallele. L'audit lui-meme ne touche aucun fichier de code. Le risque decrit ici est celui du
**travail de developpement** des deux terrains en parallele. Si les cinq lots sont ecrits en serie
sur une seule branche, rien de cette section ne s'applique.

### 9.1 Quatre lots sur cinq ont la meme surface de contact, le cinquieme en a une autre

Sur la forme etablie d'un lot de contenu (cinq fichiers, dont deux propres et trois partages), les
**quatre** lots qui creent un paquet - `S42-QLM-MATIERE`, `S42-ART-COULEURS`,
`S42-EMC-SYMBOLES-CM1`, `S42-MAT-TEXTUEL` - se touchent aux trois memes endroits, tous **en fin de
liste**, c'est-a-dire au pire endroit possible pour une fusion :

| Fichier | Propre ou partage | Ce que chaque lot y ajoute |
|---|---|---|
| `adapters/adapter-content/src/main/resources/contenu/<paquet>.yaml` | propre | le fichier entier |
| `adapters/adapter-content/src/test/java/com/recreo/adapter/content/Paquet<X>Test.java` | propre | le fichier entier |
| `adapters/adapter-content/src/main/resources/catalogue.yaml` | **partage** | six lignes en fin de fichier |
| `.../LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java` | **partage** | une `Map.entry` en fin de `Map.ofEntries`, avec ses comptes mesures |
| `bootstrap/src/test/java/com/recreo/bootstrap/catalogue/TexteAccentueExposeParLePortEntrantTest.java` | **partage** | **trois** chaines, dans **trois blocs differents** du meme fichier |

**`S42-HG-ROIS` est le seul dont la surface differe, et elle est plus petite mais d'une autre
nature.** Il etend un paquet servi : **aucune entree de catalogue neuve** (les quatre entrees
`bandit-manchot-histoire-geographie-*-cm1` existent deja), **aucune chaine neuve** dans
`TexteAccentueExposeParLePortEntrantTest`. En revanche son entree dans
`PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` **existe deja** et porte des comptes mesures : le lot doit la
**modifier**, pas l'ajouter. **Une modification au milieu d'une table que quatre autres lots
allongent par la fin fusionne differemment d'un ajout**, et c'est le seul point de ce sprint ou deux
lots ecrivent dans le meme fichier a des endroits de nature differente.

**Aucun de ces fichiers n'est un cliquet a plafond.** Leur echec est bidirectionnel, ce qui est la
bonne propriete ici : `TexteAccentueExposeParLePortEntrantTest` rougit aussi bien sur une entree de
catalogue non inventoriee que sur une chaine inventoriee qui n'est plus exposee. **Une fusion qui
perdrait la moitie d'un ajout est donc rouge, pas verte.**

### 9.2 Le seul point ou un conflit pourrait passer inapercu : le compte courant du registre

`squad/decisions/REGISTRE-identifiants.md` porte une ligne de compte courant (**682** identifiants,
**111** prefixes ce jour), et `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` clause 5
verifie qu'elle vaut le compte reel de la table. Si les deux terrains ouvrent chacun une reserve
pendant leur audit, chacun ajoute une ligne **et** incremente le meme nombre. **Une fusion qui garde
les deux lignes et un seul increment est rouge** - la clause 5 la prend. C'est donc gere, et je le
note pour la raison inverse de celle qu'on attend : c'est le seul endroit du dispositif ou une
regle existante fait exactement le travail d'un garde-fou de parallelisme.

### 9.3 Collisions d'identifiants : verifiees, aucune

- **Codes de ligne de referentiel** : les cinq sont distincts, et quatre ne sont declares par aucun
  paquet servi. Le cinquieme, `HG-CM1-05`, va dans un lot ou la configuration a deux paquets sur une
  ligne est deja servie et verte, section 5.
- **Identifiants de jeu du catalogue** : `AucuneEntreeDuCatalogueNEnDoubleUneAutreTest` mord si deux
  entrees se doublent. Rien dans les cinq lots ne pousse deux terrains vers le meme nom, les
  prefixes de matiere differant.
- **Identifiants de condition** : clause 1 d'`ADR-0085`, *"allouer deux fois le meme identifiant"*,
  est precisement la clause ecrite pour le cas de deux instances qui ne se lisent pas. Elle est en
  place et elle est verte.

### 9.4 Ce qui n'est garde par rien

**Deux instances qui ecrivent, sans le savoir, sur le meme objet du monde reel ne sont detectees par
aucun automate de ce depot.** A s42, le risque n'est pas entre les deux terrains : il est entre un
lot du terrain 2 et **du contenu deja servi au sprint precedent**, section 8.1, et entre un lot du
terrain 1 et **du contenu servi a s31**, section 8.2. Les deux sont nommes et routes.

## 10. Ce que je n'ai pas fait, et pourquoi

- **Aucun `ADR` ecrit.** Aucun des cinq lots n'en demande un. Le declencheur que mon avis de s41
  avait ecrit d'avance - une seconde entree de catalogue sur une meme ligne - **ne se leve pas**,
  section 5, point 2 : le mecanisme de sous-partie est borne aux mathematiques de CE1 et la
  configuration a deux paquets est deja servie et verte sur le lot histoire-geographie CM1.
- **Aucune regle ArchUnit neuve.** Le compte du depot est inchange : **36 `static final ArchRule`,
  35 `@ArchTest`**, mesure du 2026-09-24. Methode, celle d'`ADR-0095` et d'`ADR-0098` : occurrences
  litterales de ces deux chaines sous les arbres de source des cinq modules declares au pom
  agregateur, **`target/` exclu** et **arbres de contre-exemple exclus** - un balayage brut qui les
  inclut rend **40** et 35, les quatre unites d'ecart etant portees par deux copies du meme fichier,
  `RegleContreExemple.java` du faux module de `LesGardeFousSontMisALEpreuveTest`, une sous
  `src/test/resources/` et une sous `target/test-classes/`, chacune portant deux regles. L'ecart
  d'une unite entre 36 et 35 a la cause qu'`ADR-0094` a nommee : une `ArchRule` du depot n'est pas
  annotee `@ArchTest`.
- **Aucun contenu, aucun critere d'acceptation, aucune affectation, aucun arbitrage pedagogique.**
- **Je ne tranche pas la question produit de `MAT-CP-10`.** Je dis laquelle des deux lectures est
  correcte - les deux sources concordent, la story a la bonne direction, son diagnostic d'inversion
  est inexact - et je route la tenue de la cellule 227 a `camille-po`, section 4.2.
- **Je ne decide pas quel paquet `S42-HG-ROIS` etend.** Je constate que celui que la story nomme
  n'existe plus et que les deux voies possibles sont faisables sans `ADR` ; le grain est une
  decision de `camille-po` et de `nadia-prof`, section 5.
- **Je ne touche a aucun fichier hors de celui-ci** : ni code, ni matrice, ni backlog, ni registre,
  ni `ADR-index.md`, qui n'a rien a enregistrer puisque je n'ecris aucun `ADR`.

## 11. Mesure de la porte technique

Commande, sur l'arbre de `517abd9d`, reacteur **complet** depuis la racine - donc sans la question
du `-am`, qui ne concerne que les mesures partielles :

```
bash outils/mvn-jdk21.sh -B clean verify
```

Sortie, telle qu'elle est :

```
Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10
...
[INFO] recreo ............................................. SUCCESS [  0.607 s]
[INFO] recreo-domain ...................................... SUCCESS [ 15.287 s]
[INFO] recreo-application ................................. SUCCESS [  4.931 s]
[INFO] recreo-adapter-web ................................. SUCCESS [01:00 min]
[INFO] recreo-adapter-content ............................. SUCCESS [ 33.910 s]
[INFO] recreo-bootstrap ................................... SUCCESS [02:18 min]
[INFO] BUILD SUCCESS
[INFO] Total time:  04:14 min
[INFO] Finished at: 2026-09-24T17:12:10+02:00
```

**Version mesuree : 21.0.10**, telle que le JDK resolu la rapporte a l'enveloppe, et non telle que
l'entree de toolchain la declare (`ADR-0071`). Maven 3.9.9.

**Ce que ce vert ne dit pas, et le dire fait partie de la mesure** (`CLAUDE.md`, amendement du
2026-09-08) : `mvn verify` est le contenu du **job `build` seul**. `.github/workflows/ci.yml` porte
quatre jobs - `prefixes`, `chemins`, `outils`, `build` - et les trois premiers ne sont pas lances
par `mvn`. **Cet avis n'annonce donc pas la porte technique** ; il rapporte le resultat d'une
commande nommee, sur un arbre nomme. Les jobs que j'ai lances sur mon propre diff avant de commiter
sont nommes dans le corps du commit.
