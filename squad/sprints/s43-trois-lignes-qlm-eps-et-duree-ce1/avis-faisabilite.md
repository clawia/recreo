# s43 - Avis de faisabilite (`theo-archi`) - trois lots : questionner le monde CP, education physique et sportive CM1, mathematiques CE1

- **Date** : 2026-09-25
- **Base** : `round-two` a `5bd99d74`, arbre de travail a ce meme commit, aucun diff de code ni de
  contenu dans cet avis.
- **Perimetre de cet avis** : les **trois** lots que `DEC-0099`, amendee le 2026-09-25, retient
  pour s43, sur une **instance unique** de `nadia-prof` :
  - `S43-QLM-NUMERIQUE` (`QLM-CP-08`) ;
  - `S43-EPS-EFFETS-ACTIVITE` (`EPS-CM1-10`) ;
  - `S43-MAT-DUREE` (`MAT-CE1-12`).
- **Ce que cet avis ne vaut pas** : il ne vaut pas livraison. Il ne fixe aucun contenu, aucun
  critere d'acceptation (`camille-po`), aucun ordre ni aucune affectation nominative (`sofia-sm`),
  et le Done pedagogique reste a `nadia-prof` seule. Les trois lignes sont deja instruites
  pedagogiquement : je n'ouvre aucune question de contenu, je constate ce que le code et les
  garde-fous permettent aujourd'hui.

---

## 1. Verdicts

| Lot | Ligne | Verdict | Complexite |
|---|---|---|---|
| `S43-QLM-NUMERIQUE` | `QLM-CP-08` | **faisable en l'etat**, sans `ADR` neuf et sans regle neuve. Deux adjacences avec du contenu **deja servi dans le meme lot de matiere et de niveau** sont nommees en section 8 ; aucune n'est bloquante | basse |
| `S43-EPS-EFFETS-ACTIVITE` | `EPS-CM1-10` | **faisable sous condition**, sans `ADR` neuf et sans regle neuve. La frontiere que la story instruit - celle avec les sciences CM1 - porte sur une ligne **non couverte**, donc prospective. **Le recouvrement qui existe aujourd'hui est ailleurs et la story ne le cite pas** : les trois composantes du libelle CM1 sont deja servies au CP par deux paquets, 33 items, section 7. La condition est que le discriminant soit pose avant d'ecrire | basse sur le mecanisme, haute sur le discriminant |
| `S43-MAT-DUREE` | `MAT-CE1-12` | **faisable sous condition**, et **c'est le seul lot du sprint qui porte un `ADR` du avec sa regle**, section 4. Deux couts automatiques que la story ne porte pas sont en section 5. Une collision reelle avec du contenu servi, hors du perimetre que `nadia-prof` a verifie, est en section 6 | moyenne |

**Un `ADR` neuf est du, sur un seul lot** : `S43-MAT-DUREE`. Il porte la barre de longueur d'enonce
au CE1 (`LON-CE1-1`, `LON-CE1-2`), arbitree par `nadia-prof` le 2026-09-21 et **qu'aucun automate ne
tient a ce jour**. Il s'ecrit avec sa regle, dans le meme commit que le paquet. Les deux autres lots
sont des paquets statiques de type `question` joues au bandit manchot, dans des lots de matiere deja
servis - a une nuance de perimetre pres pour l'EPS, section 3.

## 2. Verification des citations des stories - ouvertes, pas reprises de confiance

Aucun garde-fou ne verifie un numero de ligne : `ToutesLesReferencesDeDecisionResolventTest`
(`ADR-0066`) resout les identifiants `ADR-` et `DEC-`, jamais une citation de ligne. La verification
est donc a la main, ici comme a s40, s41 et s42.

| Code | Ligne de `MATRICE-couverture.md` citee | Resolue ? | Arbitrage cite | Resolu ? |
|---|---|---|---|---|
| `QLM-CP-08` | 1586 | oui, statut `non couvert`, **sans annotation** | `ARBITRAGE-PREALABLE-s29`, section 4.1 | oui, la ligne du tableau est a la ligne 369 et la citation de la story en est le mot pour mot |
| `EPS-CM1-10` | 1941 | oui, statut `non couvert`, avec son annotation | `competences-cm1.md` de l'EPS, *Jugement de faisabilite*, ligne 10 | oui, section en ligne 57, ligne 10 du tableau en ligne 88 ; la section *Recouvrement a ne pas double-compter* est en ligne 90 et la *Mention exigee d'avance* en ligne 100 |
| `MAT-CE1-12` | 678 | oui, statut `non couvert` | `ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md`, sections 5, 5.1, 5.3 | oui, aux lignes 529, 538 et 571 ; les six conditions `DUR-CE1-1` a `DUR-CE1-6` y sont |

Les trois codes resolvent aussi cote referentiel, sous la forme exacte que le motif
`^- \[([^]]+)] .+` du garde-fou d'`ADR-0081` exige :

| Code | Referentiel | Ligne |
|---|---|---|
| `QLM-CP-08` | `squad/programme/questionner-le-monde/competences-cp.md` | 31 |
| `EPS-CM1-10` | `squad/programme/education-physique-et-sportive/competences-cm1.md` | 53 |
| `MAT-CE1-12` | `squad/programme/mathematiques/competences-ce1.md` | 54 |

**Une precision de perimetre sur la citation de `DEC-0099`, section 9.5.** Elle ecrit *"aucune
collision signalee avec les sept jeux CE1 deja servis (lot 2, section 7)"*. C'est exact, et le
titre de cette section 7 dit lui-meme son perimetre : *"Collisions avec les sept jeux CE1 deja
servis"*. **La verification faite porte sur le CE1 ; elle n'a pas porte sur les autres niveaux**, et
c'est la que la collision est, section 6.

## 3. Les lots de matiere servis, mesures - et un couple (matiere, niveau) qui est neuf

Mesure du 2026-09-25 sur les **100** fichiers de
`adapters/adapter-content/src/main/resources/contenu/`, comptes par la `matiere` et le `niveau` de
leur descripteur, un paquet par fichier, `target/` sans objet.

| Lot vise | Paquets servis | Items | Ce qu'il y a deja |
|---|---|---|---|
| Questionner le monde / CP | 10 | 123 | `cp-qlm-objet-technique` (`QLM-CP-07`), `cp-qlm-hygiene` (`QLM-CP-04`), huit autres |
| **Education physique et sportive / CM1** | **0** | **0** | **rien** - les trois paquets d'EPS servis sont tous au **CP** (44 items) |
| Mathematiques / CE1 | 7 | 144 | `ce1-additions-jusqu-a-100`, `ce1-longueurs`, `ce1-monnaie`, `ce1-solides`, trois autres |

**Aucune matiere neuve, aucun niveau neuf, aucune mecanique neuve.** En revanche le couple
**(education physique et sportive, CM1) est neuf**, et cela a un effet technique precis, pas une
generalite : l'unite de tout garde-fou de lot est le **couple (matiere, niveau)** - verifie dans le
code de `UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest` (`ADR-0079`), qui derive
son lot de `matiere + niveau` du descripteur. Le paquet d'EPS CM1 sera donc **seul dans son lot** :
une condition de lot s'y comptera sur lui seul, et les trois paquets d'EPS CP n'y entreront pas. La
consequence pratique est en section 10.

## 4. `MAT-CE1-12` - l'`ADR` du sprint : la barre de longueur du CE1 n'est tenue par personne

### 4.1 Le fait, mesure et non deduit d'un titre

`DUR-CE1-6` ecrit : *"`LON-CE1-1` et `LON-CE1-2` s'appliquent"*. Le sixieme critere d'acceptation de
la story le reprend : *"il tient dans vingt mots et trois phrases au plus, la derniere etant la
question"*. **Zero occurrence de la chaine `LON-CE1` dans le Java du depot** - mesure de ce jour,
tous modules, `target/` exclu. Aucun test ne tient cette barre.

Ce n'est pas une omission de perimetre, c'est un perimetre ferme par une constante :
`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` (`ADR-0089`) porte `NIVEAU_BORNE = "CP"`, et sa
population est *les enonces des paquets servis dont le descripteur porte le niveau borne*. **Un
paquet de CE1 n'y est pas exempte : il n'y est pas vu.** `nadia-prof` l'avait deja verifie
elle-meme, autrement que par le titre, dans `ARBITRAGE-s36-longueur-d-enonce-au-ce1.md`, section 1.

### 4.2 La regle est ecrivable aujourd'hui, et la dette qu'elle revele vaut exactement quatre

**Mesure du 2026-09-25, methode dans le meme paragraphe.** Perimetre : les paquets de
`adapters/adapter-content/src/main/resources/contenu/` dont le descripteur porte `niveau: "CE1"`,
cle `enonce` seule, sept paquets, **144 enonces**. Convention de comptage : celle d'`ADR-0089`,
reproduite **hors du build** avec ses trois motifs tels qu'ils sont ecrits dans le garde-fou -
`ESPACES = [\s ]`, un token est un mot des lors qu'il porte une lettre ou un chiffre, une fin de
phrase est un `.`, `?` ou `!` qui termine le texte ou qu'une majuscule suit. **Ce chiffre est donc
une reproduction, pas une execution** : il est a reconfirmer par la regle le jour ou elle est
ecrite, et c'est elle qui fera foi.

| Ce qui est mesure | Resultat |
|---|---|
| Enonces de CE1 servis | 144 |
| Au-dela de **20 mots** (`LON-CE1-1`) | **4**, tous dans `ce1-longueurs.yaml` : items 11 (22 mots), 12 (27), 13 (26), 14 (26) |
| Au-dela de **3 phrases**, ou trois phrases dont la derniere n'est pas la question (`LON-CE1-2`) | **0** |

**Ce que cette mesure dit de la valeur de la regle, et c'est le point.** `ce1-longueurs` a recu son
Done pedagogique a **s39**, soit apres l'arbitrage du **2026-09-21** qui pose `LON-CE1-1`. Quatre
enonces depassent la barre que `nadia-prof` avait posee, **et rien ne l'a dit** - ni un rouge, ni un
avis. C'est la preuve sur materiau reel qu'une regle non encodee n'en est pas une, et c'est la
demonstration que je prefere a une fixture de ma main.

`nadia-prof` a **refuse d'avance** de rouvrir ce Done (*"une barre qu'on releve apres qu'une equipe
l'a atteinte fait de ma porte un caprice"*, arbitrage du lot 2, section 8). La regle se pose donc
avec une **table de dette nommee** - `ce1-longueurs.yaml` -> 4 - et un **plafond valant exactement
4**, sur la forme deja servie par `ADR-0089` : `PLAFOND_DE_DETTE = 5` y vaut exactement `1 + 1 + 3`,
la somme de sa table. Un plafond au-dessus de la dette ne serait pas un cliquet.

### 4.3 Pourquoi cette regle mord vraiment sur ce lot-ci, chiffres a l'appui

Le seul paquet servi qui porte **exactement la forme** de `MAT-CE1-12` est `cm1-problemes-durees`
(section 6). Mesure de ses 24 enonces, meme convention : **17 depassent 20 mots**, et **9 de ses 12
items de la forme *deux instants donnes, duree a trouver* les depassent** (de 20 a 24 mots). Ce
paquet est au CM1, ou aucune barre ne s'applique - il n'est pas en faute.

**La consequence pour s43 est directe** : le precedent le plus proche du lot, ecrit par la meme
equipe, franchit la barre du CE1 sur les trois quarts de ses items. Un paquet de `MAT-CE1-12` ecrit
sur ce modele serait trop long, et **aujourd'hui rien ne le dirait**. La regle interdit donc quelque
chose de reel et de probable, pas quelque chose d'imaginable.

### 4.4 Ce que l'`ADR` decide, et ce qu'il ne decide pas

- **Ce qu'il decide** : au CE1, un enonce servi compte au plus 20 mots et au plus 3 phrases, la
  derniere etant la question ; la convention de comptage est celle d'`ADR-0089`, **citee et non
  recopiee** (`ADR-0061`) ; la dette est nommee paquet par paquet et son plafond vaut sa somme.
- **Le cout en code est faible et il est mesure, pas estime** : le recensement est **deja
  parametre par niveau** dans le garde-fou existant - `static List<Enonce> enonces(List<Path>
  repertoiresDeContenu, String niveau)` (`ADR-0078`). La regle du CE1 reutilise ce recensement et ne
  redecrit ni le format des paquets, ni la convention de comptage.
- **Ce qu'il ne decide pas** : la barre elle-meme. Elle est de `nadia-prof`, rendue le 2026-09-21.
  Je l'encode, je ne l'arbitre pas, et je ne la transpose a aucun autre niveau : le CM1 reste sans
  barre, comme `ADR-0089` l'a decide et comme le constat de s42 le redisait.
- **Ce qui me ferait changer d'avis plus tard** : si un troisieme niveau recevait une barre, trois
  classes quasi identiques vaudraient moins qu'une classe parametree par (niveau, mots, phrases).
  **Deux ne le valent pas** - trois lignes semblables valent mieux qu'une abstraction prematuree, et
  la seconde barre est precisement l'occasion de ne pas la faire.

## 5. `MAT-CE1-12` - deux couts automatiques que la story ne porte pas

### 5.1 La sous-partie de mathematiques CE1 est obligatoire, et elle s'alloue dans le meme diff

`ADR-0097` / `ADR-0098`, encodes dans
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` :
**tout** paquet dont le descripteur porte `CE1` et la matiere *Mathematiques* doit declarer une
competence **allouee dans l'enumeration `SousPartieDeMathematiquesCe1`**, avec la ligne de
referentiel que le paquet declare. La table compte sept entrees aujourd'hui (`ADDITIONS`,
`SOUSTRACTIONS`, `COMPLEMENTS_A_LA_DIZAINE`, `MOITIES`, `LONGUEURS`, `MONNAIE`, `SOLIDES`). **Le
paquet de durees en demande une huitieme**, portant `MAT-CE1-12` et la chaine de competence
**exacte** que son descripteur ecrira.

La polarite de ce garde-fou est du cote qui exige : un paquet de CE1 non alloue est **rouge**, pas
vert. Le geste est donc obligatoire et il est dans le meme diff que le paquet. La story ne le cite
pas ; il ne coute rien a qui le sait d'avance, et un demi-sprint a qui le decouvre au premier rouge.

### 5.2 L'ecriture des durees decide si un garde-fou existant mesure ce que l'enfant voit

`DUR-CE1-5` ecrit : *"chacun des trois rangs de la bonne reponse porte au moins 10 % et au plus
50 % des items"*. **Cette clause est deja encodee**, et avec les memes nombres :
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` porte `SEUIL_MINIMUM = 0.10` et
`SEUIL_MAJORITE = 0.5`, et la Javadoc de `SEUIL_MINIMUM` dit d'ou il vient - *"Reserve nadia-prof du
2026-08-15 : aucun rang de la bonne reponse ne devrait apparaitre sur moins de 10 % des items d'un
paquet. Ce seuil est le sien, pas un choix d'ingenieur."* **Aucune regle neuve n'est due pour cette
clause. Elle n'est tenue, en revanche, que si les trois propositions se lisent.**

**Et c'est la qu'il y a un piege, et il est de mon cote.** `DUR-CE1-3` propose deux ecritures de
duree : *"2 heures et demie"* **ou** *"2 h 30 min"*. Le lecteur de valeurs de ce garde-fou
(`lireValeur`) essaie dans l'ordre une duree en heures, une duree en minutes, une somme de termes,
puis un nombre nu. Son motif de duree est
`^(\d+)\s*h(?:eures?)?\.?(?:\s+(\d+)\s*(?:min(?:utes?)?)?\.?)?$` :

| Ecriture | Ce que le lecteur en fait | Valeur |
|---|---|---|
| `2 h 30 min` | motif de duree | 150 |
| `2 heures 30 minutes` | motif de duree | 150 |
| `14 h 30` (un instant) | motif de duree, en minutes depuis minuit | 870 |
| **`2 heures et demie`** | **le motif echoue** (il exige des chiffres apres les heures) ; la lecture retombe sur le nombre nu, le reste *"heures et demie"* ne porte aucun chiffre et passe | **2** |

**Consequence concrete, et c'est exactement la famille de defaut que ce depot sanctionne.** Sur un
paquet ecrit *"et demie"*, *"1 heure et demie"* vaut **1** et *"1 heure"* vaut **60** : la bonne
reponse serait classee *la plus petite* la ou l'enfant la voit au milieu. Le garde-fou de rang
resterait **vert sur une distribution qui n'est pas celle qui est servie** - il passerait sans rien
prouver.

**Deux issues, et le choix de l'ecriture n'est pas le mien** - c'est une forme de contenu, donc
`nadia-prof`, et son arbitrage laisse deja les deux ouvertes :

1. **Le paquet ecrit `2 h 30 min`** (ou *"2 heures 30 minutes"*). Rien a faire : zero ligne de Java,
   et c'est l'ecriture que `cm1-problemes-durees` sert deja sous `D-2`. **C'est ce que je
   recommande**, et c'est le seul avis d'ecriture que je donne, pour un motif technique et non
   pedagogique.
2. **Le paquet ecrit `2 heures et demie`**. Alors **le lecteur de valeurs doit etre etendu dans le
   meme commit**, avec sa mise a l'epreuve (`ADR-0013` obligation 3), faute de quoi le garde-fou de
   rang mesure autre chose que ce qui est servi. Ce n'est pas un refus : c'est un cout, et il se
   paie avant le paquet, pas apres.

*Note sur le reste de `DUR-CE1-5`. La clause "aucune proposition egale a une autre" est tenue en
deux endroits qui existent : `AucunePropositionNEstEgaleAUneAutreDansUnItemTest` sur le texte, et
`aucuneValeurDeProductionNeCoincideAvecUneAutreDansLeMemeItem` sur la valeur lue. La clause "trois
erreurs reelles" ne l'est par rien : juger qu'un nombre exact est une erreur credible d'un enfant
demande de lire le sens, `nadia-prof` l'a ecrit elle-meme (arbitrage du lot 2, section 9, point 2),
et une regle qui pretendrait le faire serait decorative. Je n'en propose pas.*

## 6. `MAT-CE1-12` - la collision reelle est hors du perimetre qui a ete verifie

**Ce que `nadia-prof` a verifie, et qui est exact.** Section 7 de son lot 2 : les **sept** jeux CE1
deja servis, item par item. Verdict *"Aucune"* pour `bandit-manchot-solides-ce1` sur toutes ses
lignes. **Verifie de mon cote sur les items** : aucun des sept paquets de CE1 ne porte un seul
instant ni une seule duree - zero occurrence d'une ecriture `h` suivie de minutes, zero occurrence
du mot *duree*, sur les 144 items. Sa conclusion tient, et l'alerte relayee par la commande sur une
collision possible avec `bandit-manchot-solides-ce1` **ne se confirme pas** : ce paquet sert des
solides, il ne touche ni le temps ni la mesure.

**Ce qui n'a pas ete verifie, parce que ce n'etait pas le perimetre annonce : les autres niveaux.**

`cm1-problemes-durees` (`MAT-CM1-10`, entree de catalogue `bandit-manchot-problemes-durees-cm1`, 24
items, livre a s13 et valide a s22-s23) **sert deja la forme exacte de `MAT-CE1-12`** sur ses douze
premiers items. Son propre commentaire d'entete l'ecrit : *"les douze premiers items calculent une
duree ecoulee entre deux instants donnes, les douze suivants calculent un horaire d'arrivee"*.

| Ce que `DUR-CE1-*` prescrit | Ce que `cm1-problemes-durees` sert deja |
|---|---|
| `DUR-CE1-1` : deux instants donnes, la duree a trouver | ses douze premiers items, mot pour mot la meme forme |
| `DUR-CE1-3` : deux ecritures visiblement differentes, constantes sur tout le paquet | son `D-2` : *"un instant s'ecrit en abrege (`10 h 15`), une duree s'ecrit en toutes lettres (`25 minutes`)"* - la meme conception |
| `DUR-CE1-6` : situations - l'ecole, la recreation, la cantine, un trajet, un film, un match, une recette, une visite | **les huit y sont deja** : *"La recreation de Malo..."*, *"Aya arrive a la cantine..."*, *"Le bus scolaire d'Elio..."*, *"Fatou regarde un film..."*, *"Diego regarde un match..."*, *"Iris prepare une recette..."*, *"Noah visite la ferme..."*, *"Manon entre dans la bibliotheque..."* |

**Ce que cela ne veut pas dire.** Ce n'est **pas** une faute et ce n'est **pas** un double-compte de
couverture : deux lignes distinctes (`MAT-CM1-10` et `MAT-CE1-12`), deux niveaux distincts, et
`ADR-0081` clause 5 tient. Un meme fait scolaire servi a deux niveaux est le fonctionnement normal
d'un programme.

**Ce que cela veut dire.** Le discriminant entre les deux paquets ne sera pas la situation ni la
forme de l'enonce : ce sera `DUR-CE1-2` seul - minutes valant `00` ou `30`, duree de 30 minutes a
4 heures - la ou le paquet de CM1 emploie des minutes quelconques (`10 h 15`, `9 h 55`). **Et aucun
garde-fou du depot ne compare deux paquets** : `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`
(`ADR-0032`) et `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` ont tous deux le **meme paquet**
pour perimetre, leur nom le dit et leur code le fait. Un paquet de CE1 qui reprendrait les douze
enonces du CM1 en arrondissant les minutes passerait **tous** les garde-fous.

**Ce que je route, et a qui** : le fait ci-dessus a `nadia-prof`, via `sofia-sm`, **avant** que le
paquet ne soit ecrit. Je ne dis pas quel doit etre le discriminant - c'est du pedagogique. Je ne
propose pas non plus de regle : comparer deux paquets sur le sens demande de lire, et la regle
serait decorative.

*Une precision qui evite un contresens : la ressemblance est une ressource autant qu'un risque. Les
trois familles d'erreur de `D-6` du paquet de CM1 - report d'une heure oublie ou en trop, base 100,
inversion des deux instants - sont **exactement** les trois erreurs que `DUR-CE1-5` demande, a la
demi-heure pres. Le lot n'invente pas ses distracteurs ; il en a un modele servi, valide, et dont
les motifs sont ecrits.*

## 7. `EPS-CM1-10` - la frontiere instruite par la story n'est pas celle qui mord aujourd'hui

### 7.1 La frontiere avec les sciences est prospective, verifiee

La story cite integralement la section *Recouvrement a ne pas double-compter* : *"Sciences et
technologie CM1 porte deja : 'Relier l'alimentation, l'activite physique, le sommeil et l'hygiene au
bon fonctionnement du corps'"*. **Verifie dans la matrice, ligne 2118 : `SCI-CM1-10` est
`non couvert`.** Verifie aussi cote contenu : les **12** paquets de sciences CM1 servis declarent
`SCI-CM1-01`, `-02`, `-04`, `-05`, `-06`, `-07`, `-08`, `-09`, `-11`, `-12`, `-17`, `-20`. **La
ligne 10 n'est servie par aucun paquet.** Il n'y a donc **aucun double-compte possible aujourd'hui**
- la frontiere est une consigne de redaction pour plus tard, pas une contrainte presente.

Un seul item servi touche l'effort hors EPS, et je le nomme pour qu'il ne soit pas repris :
`cm1-sciences-nutrition` (`SCI-CM1-09`), item 5 - *"Quand on court vite, quel organe se remplit d'air
plus vite ?"*. C'est un effet de l'effort, servi sous une ligne de sciences. Un item d'EPS qui
poserait la meme question serait le double-compte que la section instruit, cette fois reel.

### 7.2 Le recouvrement qui existe aujourd'hui est vertical, et la story ne le cite pas

**Mesure, pas impression.** Le libelle de `EPS-CM1-10` est *"Connaitre les effets de l'activite
physique sur le corps (echauffement, essoufflement, recuperation, hydratation) et les regles de
securite qui l'entourent"*. Ses trois composantes sont **deja servies au CP**, par deux paquets :

| Composante du libelle CM1 | Paquet servi au CP | Ligne | Items |
|---|---|---|---|
| effets de l'activite physique sur le corps | `cp-eps-corps-effort` | `EPS-CP-10` - *"Reconnaitre les effets de l'effort sur son corps (essoufflement, coeur qui bat vite, chaleur, soif)"* | 22 (partage avec la ligne suivante) |
| echauffement, hydratation, recuperation | `cp-eps-corps-effort` | `EPS-CP-11` - *"Connaitre quelques regles d'echauffement, d'hydratation et de recuperation autour de l'effort"* | idem |
| regles de securite qui l'entourent | `cp-eps-regles-de-securite` | `EPS-CP-04` | 11 |

Les enonces servis au CP sont *"Pourquoi echauffe-t-on ses muscles avant un effort intense ?"*,
*"Pourquoi boit-on de l'eau autour d'un effort physique ?"*, *"Que risque-t-on si l'on commence un
effort intense sans echauffement ?"*. La story ecrit son besoin ainsi : *"savoir pourquoi on
s'echauffe, on s'hydrate et on recupere apres l'effort"*. **C'est la meme phrase, a deux niveaux.**

**Et l'architecture ne fournit rien pour les distinguer, contrairement a d'autres coutures.**

| Lot | Niveau | Ce qui le differencie **structurellement** du contenu voisin deja servi |
|---|---|---|
| `S43-QLM-NUMERIQUE` | CP | rien de vertical : ses voisins sont au **meme** niveau, section 8 |
| `S43-EPS-EFFETS-ACTIVITE` | CM1 | **rien**. `NIVEAU_BORNE = "CP"` : aucune barre de longueur ne s'applique au paquet neuf, et le paquet voisin, lui, **est** borne a 15 mots et 2 phrases. Rien dans le depot ne force le paquet de CM1 a etre plus exigeant que celui de CP |

C'est le constat de s42, section 8.1, a l'identique : un paquet de CM1 qui reprendrait les 22
questions du paquet de CP en les rallongeant passerait tous les garde-fous.

**Ma condition de verdict est donc de procedure, pas de contenu** : que `nadia-prof` lise
`cp-eps-corps-effort` et `cp-eps-regles-de-securite` **avant** que le paquet de CM1 ne soit ecrit, et
pose le discriminant. Je ne dis pas ce qu'il doit etre. L'instance etant **unique** ce sprint-ci, le
risque de s42 - deux instances qui ne se lisent pas - ne se pose pas ; il reste celui d'un contenu
livre il y a plusieurs sprints et que personne ne relit.

*Un fait a verser au dossier : la section que la story cite dit elle-meme, en sa derniere phrase,
"c'est la meme frontiere que celle posee le meme jour entre l'EPS de CP et questionner le monde".
La frontiere verticale EPS CP / EPS CM1, elle, n'est posee nulle part.*

## 8. `QLM-CP-08` - deux adjacences dans son propre lot, aucune bloquante

`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` a le **meme paquet** pour perimetre. Les deux
adjacences ci-dessous sont **entre paquets**, donc invisibles a tout automate du depot.

1. **`cp-qlm-hygiene` (`QLM-CP-04`) sert deja une regle d'usage d'un ecran**, en distracteur : item 3,
   enonce *"Quelle habitude aide a bien s'endormir le soir ?"*, distracteur *"regarder un ecran juste
   avant de dormir"*. C'est le meme lot de matiere et le meme niveau. Un item de `QLM-CP-08` sur
   l'usage des ecrans le soir reprendrait ce fait exact. Rien ne l'interdit, rien ne le signale.
2. **`cp-qlm-objet-technique` (`QLM-CP-07`)** partage le vocabulaire des objets du quotidien
   (ciseaux, parapluie, velo, stylo, balai). **Les taches sont distinctes** - *a quoi sert cet
   objet ?* contre *cet objet est-il numerique ?* - donc `ADR-0081` clause 5 est tenue. C'est une
   adjacence de vocabulaire, pas de competence ; elle est nommee, pas opposee.

Une troisieme, plus lointaine et que je nomme pour qu'elle ne soit pas trouvee au moment de l'audit :
`cm1-sciences-signal-information` (`SCI-CM1-05`) interroge deja *"un feu tricolore, une alarme, un
reveil, l'ecran d'un four"*. La tache est *"quel type de signal est-ce ?"*, pas *"cet objet est-il
numerique ?"*, et le niveau est le CM1. Pas de collision ; un vocabulaire commun.

**Ce qui est encode et mordra sur ce lot** : `LON-1` et `LON-2` (`ADR-0089`) des le premier commit -
le paquet est au CP, il entre dans la population par son descripteur et il est borne a **zero**
enonce trop long, la table `DETTE_DE_LONGUEUR` ne nommant que trois paquets d'EMC CP ;
`LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` (`ADR-0086`) ;
`AucunTexteServiNEnchasseLaReponseDansUnePhraseTest` (`ADR-0083`), qui prendra un enonce nommant
l'objet dont la reponse est le nom.

**Ce qui n'est encode par rien, et qui est la vigilance centrale de la ligne** : *"risque de
moraliser plutot que d'enseigner"*. Juger qu'un enonce moralise demande de lire le sens. C'est la
garde de `nadia-prof`, entierement, et le dire evite qu'un vert soit lu comme une preuve du critere.

## 9. Cliquets, plafonds et bornes - mesures et non devines

Tous verifies dans le code a `5bd99d74`, et **tous identiques a leur valeur de s42** :

| Cliquet | Constante et valeur | Effet des trois lots |
|---|---|---|
| Poids des dessins | `POIDS_TOTAL_MAXIMUM = 51307`, `POIDS_D_UN_DESSIN_MAXIMUM = 4633` | **aucun** - aucun fichier ajoute sous `static/illustrations/` |
| Poids des reproductions d'oeuvres | `POIDS_TOTAL_MAXIMUM = 0` | **aucun** |
| Paquets de designation en production | `PLAFOND_DE_DESIGNATION_EN_PRODUCTION = 2`, `PAQUETS_DE_DESIGNATION_PORTANT_LA_DETTE_DE_LISIBILITE = 2` | **aucun** - les trois lots sont du bandit manchot a choix textuel |
| Dette de longueur d'enonce au CP | `PLAFOND_DE_DETTE = 5` = `1 + 1 + 3` sur trois paquets d'EMC CP ; `PLAFOND_DE_DETTE_DE_FORME = 0` | **aucun** - le paquet de CP entre borne a zero |
| Dette de rang | `PLAFOND_DE_DETTE = 2` (`cm1-perimetre`, `cm1-mesures`), `PLAFOND_DE_DETTE_SEUIL_MINIMUM = 3` | **aucun** - a condition que les trois paquets tiennent les seuils, section 5.2 |
| Dette de declaration de ligne | `PLAFOND_DE_DETTE = 0` | **aucun** - chaque paquet declare sa ligne, cas nominal |
| Registre d'identifiants | compte courant **774** identifiants, **125** prefixes | **aucun** si aucune reserve neuve n'est ouverte, section 9.1 |

Aucun plafond existant n'est atteint ni approche. **La regle de la section 4 en cree un neuf**, et sa
valeur est fixee par la mesure : 4.

### 9.1 Les identifiants de condition que les trois lots vont citer sont deja alloues

`ADR-0085` / `PAR-5` : tout identifiant de condition ecrit dans le depot est alloue dans
`squad/decisions/REGISTRE-identifiants.md`, et la ligne de compte courant vaut le compte reel de la
table (clause 5, verifiee par la CI). Valeur lue ce jour a la ligne 263 : **774** identifiants,
**125** prefixes.

Verifies un a un dans le tableau d'allocation de la section 4 : `DUR-CE1-1` a `DUR-CE1-6` (lignes 884
a 889, alloues par `nadia-prof` dans le meme commit que son arbitrage), `LON-CE1-1` et `LON-CE1-2`
(ligne 1249), `EPS-1`, `EPS-2`, `EPS-3` (lignes 1071 a 1073), `BIA-4` (ligne 943). **Les douze sont
alloues, une fois chacun.** Aucun geste d'allocation n'est du par les lots, sauf si `nadia-prof`
ouvre une reserve neuve en auditant - auquel cas l'identifiant et le compte courant remontent dans
le meme diff.

*Cet avis ne donne d'exemple d'aucun identifiant de reserve possible, et c'est delibere :
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` ne peut pas distinguer un identifiant cite
en exemple d'un identifiant pose, et l'y ecrire le rendrait rouge. La regle de la section 4 cite
`LON-CE1-1` et `LON-CE1-2`, qui sont alloues.*

## 10. Pieges de fabrication communs aux trois lots, nommes avant le premier rouge

1. **Le marqueur `# HIS-1:` ne se pose pas par analogie.** `HIS-1` est **derivee** : un lot est
   soumis des qu'un seul de ses items porte le marqueur, et alors **chaque** item du lot doit en
   porter un. Mesure de ce jour : cinq paquets en portent - les quatre d'histoire-geographie CM1 et
   `cm1-emc-symboles`, livre a s42. **Ni le lot questionner le monde CP (10 paquets, 123 items), ni
   aucun lot d'EPS, ni les mathematiques CE1 n'en portent.** Un seul marqueur pose dans le paquet de
   `QLM-CP-08` rendrait rouges les dix paquets du lot d'un coup. Aucune des trois stories ne cite
   `HIS-1` ; elles ont raison.
2. **L'entree dans `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` est due pour les lots textuels et interdite
   pour celui des durees.** Cette table est verifiee **dans les deux sens** : un paquet hors du
   domaine et non declare est rouge, un paquet declare qui contribue des items l'est aussi.
   `S43-QLM-NUMERIQUE` et `S43-EPS-EFFETS-ACTIVITE` doivent s'y declarer avec leur compte **mesure**.
   **`S43-MAT-DUREE` ne doit pas s'y declarer** : ses propositions sont lues comme des durees
   (section 5.2), il est dans le domaine.
3. **La graphie de la matiere est une cle.** `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest`
   (`ADR-0049`) refuse deux graphies d'une meme matiere : le paquet d'EPS CM1 ecrit
   `"Éducation physique et sportive"` exactement comme les trois paquets de CP, accent compris.
4. **Les fichiers partages que touche chaque lot qui cree un paquet** : `catalogue.yaml` (en fin de
   fichier), `TexteAccentueExposeParLePortEntrantTest` (identifiant de jeu, titre, consigne, et toute
   chaine de descripteur non deja inventoriee), `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`
   pour les deux lots textuels, et pour le seul lot de mathematiques CE1
   `ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`. **Un test de paquet dedie n'est pas
   obligatoire** - verifie plutot que suppose : aucun `Paquet*Test` ne couvre les trois paquets
   d'EPS servis. C'est une convention, pas un garde-fou.
5. **Le titre du jeu ne porte jamais son niveau** (`AucunTitreDeJeuNePorteSonNiveauTest`). Le point
   compte pour `S43-MAT-DUREE` : l'entree servie `bandit-manchot-problemes-durees-cm1` porte deja le
   titre *"Les durees et les horaires"*, et la tentation d'ecrire *"Les durees CE1"* pour distinguer
   les deux est exactement ce que ce garde-fou refuse.

## 11. Ce que je n'ai pas fait, et pourquoi

- **Un seul `ADR` est du**, sur `MAT-CE1-12`, et il s'ecrit avec sa regle dans le meme commit que le
  paquet, pas ici : je ne l'ecris pas d'avance parce que sa table de dette doit porter le chiffre
  **que la regle mesure**, pas celui que ma reproduction hors build a rendu (section 4.2).
- **Aucune autre regle ArchUnit neuve n'est due.** Compte du depot inchange : **36
  `static final ArchRule`, 35 `@ArchTest`**, mesure du 2026-09-25. Methode, celle d'`ADR-0095` et
  d'`ADR-0098` : occurrences litterales de ces deux chaines sous les arbres de source des cinq
  modules declares au pom agregateur, **`target/` exclu** et **arbres de contre-exemple exclus** ; un
  balayage brut sur ce worktree, sans `target/` puisqu'il est neuf, rend **38**, les deux unites
  d'ecart etant portees par l'unique copie de `RegleContreExemple.java` du faux module de
  `LesGardeFousSontMisALEpreuveTest`. L'ecart d'une unite entre 36 et 35 a la cause qu'`ADR-0094` a
  nommee : une `ArchRule` du depot n'est pas annotee `@ArchTest`.
- **Je ne propose aucune regle qui comparerait deux paquets sur le sens** - ni pour l'EPS
  (section 7.2), ni pour les durees (section 6). Elle serait decorative.
- **Je ne tranche pas l'ecriture des durees** : je dis ce que le lecteur de valeurs lit et ce qu'il
  ne lit pas, et je nomme le cout de chacune des deux voies (section 5.2). La forme du contenu
  appartient a `nadia-prof`.
- **Aucun contenu, aucun critere d'acceptation, aucune affectation, aucun arbitrage pedagogique.**
- **Je ne touche a aucun fichier hors de celui-ci** : ni code, ni matrice, ni backlog, ni registre,
  ni `ADR-index.md`, qui n'a rien a enregistrer puisque je n'ecris aucun `ADR` dans ce commit.

## 12. Mesure de la porte technique

Commande, sur l'arbre de `5bd99d74`, reacteur **complet** depuis la racine - donc sans la question
du `-am`, qui ne concerne que les mesures partielles :

```
bash outils/mvn-jdk21.sh -B clean verify
```

Sortie, telle qu'elle est :

```
Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10
...
[INFO] recreo ............................................. SUCCESS [  0.687 s]
[INFO] recreo-domain ...................................... SUCCESS [ 12.889 s]
[INFO] recreo-application ................................. SUCCESS [  4.486 s]
[INFO] recreo-adapter-web ................................. SUCCESS [ 44.438 s]
[INFO] recreo-adapter-content ............................. SUCCESS [ 29.671 s]
[INFO] recreo-bootstrap ................................... SUCCESS [02:04 min]
[INFO] BUILD SUCCESS
[INFO] Total time:  03:36 min
[INFO] Finished at: 2026-09-25T14:52:18+02:00
```

**Version mesuree : 21.0.10**, telle que le JDK resolu la rapporte a l'enveloppe, et non telle que
l'entree de toolchain la declare (`ADR-0071`). Maven 3.9.9.

**Ce que ce vert ne dit pas, et le dire fait partie de la mesure** (`CLAUDE.md`, amendement du
2026-09-08) : `mvn verify` est le contenu du **job `build` seul**. `.github/workflows/ci.yml` porte
quatre jobs - `prefixes`, `chemins`, `outils`, `build` - et les trois premiers ne sont pas lances par
`mvn`. **Cet avis n'annonce donc pas la porte technique** ; il rapporte le resultat d'une commande
nommee, sur un arbre nomme. Les jobs que j'ai lances sur mon propre diff avant de commiter sont
nommes dans le corps du commit.
