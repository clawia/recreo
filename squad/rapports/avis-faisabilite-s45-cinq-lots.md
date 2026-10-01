# s45 - Avis de faisabilite (`theo-archi`) - cinq lots de `DEC-0101` : `MAT-CE1-04`, `MUS-CP-05`, `EPS-CM1-09` (instance A), `LVE-CM1-06`, `SCI-CM1-14` (instance B)

- **Date** : 2026-10-01
- **Base** : `round-two` a `c15a6c4d`. Aucun diff de code ni de contenu dans cet avis, aucun autre
  fichier touche.
- **Aucun avis anterieur sur ces cinq lignes** : recherche de `S45-`, `MAT-CE1-04`, `MUS-CP-05`,
  `EPS-CM1-09`, `LVE-CM1-06` et `SCI-CM1-14` dans `squad/rapports/` et `squad/sprints/` avant
  ecriture. Aucun dossier `squad/sprints/s45-...` n'existe ; aucun fichier de `squad/rapports/` ne
  porte un avis sur l'une de ces lignes.
- **Pourquoi ce fichier est ici** : le dossier de sprint est a ouvrir par `sofia-sm`, qui reprend ou
  cite cet avis dans `planning.md`.
- **Ce que cet avis ne vaut pas** : il ne fixe aucun contenu, aucun critere (`camille-po`), aucune
  affectation (`sofia-sm`), et le Done pedagogique reste a `nadia-prof`. Il ne rejuge aucune
  condition : il dit comment la tester, ce que le depot impose deja, et ou le texte des stories
  s'ecarte de ses sources.

---

## 1. Verdicts

| Lot | Ligne | Verdict | Complexite |
|---|---|---|---|
| `S45-MAT-ADDITION` | `MAT-CE1-04` | **faisable moyennant un `ADR`**. Le garde-fou d'`ADD-CE1-2` (`ADR-0096`) range dans sa population tout paquet CE1 dont les items sont des additions, et il rendrait **rouge** tout paquet de `MAT-CE1-04` ecrit sur la forme servie (section 4.1). Son perimetre se restreint a la ligne declaree, dans un `ADR` neuf, avant ou avec le paquet. Q1 et Q2 avant l'ecriture | moyenne : un predicat de perimetre, un test de paquet entierement calculable |
| `S45-MUS-INSTRUMENTS` | `MUS-CP-05` | **faisable en l'etat**. Zero code de production. Un piege nomme : le lexique de `MUS-2` du paquet de s44 contient `frapp`, qui est le verbe de definition de la famille des percussions (section 5) | basse |
| `S45-EPS-ROLES` | `EPS-CM1-09` | **faisable sous condition de procedure**. Zero code de production. `cp-eps-regles-jeu-collectif` (`EPS-CP-08`, servi) pose deja la tache "regle appliquee a une situation decrite" sur onze items. `DEC-0101` n'a verifie `CYC-1` qu'entre les lignes du lot. Condition : `nadia-prof` lit ce paquet et pose le discriminant CM1/CP avant l'ecriture, comme `EPS-9` pour `EPS-CM1-10` (Q4) | basse sur le mecanisme, moyenne sur le test |
| `S45-LVE-ECRITURE` | `LVE-CM1-06` | **faisable sous condition, et la condition peut bloquer le lot**. Zero code de production : la mecanique `saisie` existe et sert deux paquets. Mais la story dit "bandit manchot", **son lexique est surevalue** (15 mots, pas 25 : `LVE-CM1-07` ne sert aucun mot anglais), elle omet `FER-1` sur une parenthese en partie inservable, et le motif du refus `R6` (correction automatique du clavier) n'a jamais ete instruit sur cette ligne (Q5, Q6) | basse sur le mecanisme ; le risque est pedagogique |
| `S45-SCI-NUMERIQUE` | `SCI-CM1-14` | **faisable sous condition**. Zero code de production. **La story reprend le defaut deja route a s44** : elle cite `SCI-1` dans sa formulation remplacee et vise le mauvais champ, et omet `SCI-4`, `FER-1` a `FER-3`, `HIS-3`, `CON-1`. La question Q2 de l'avis de s44 n'a jamais ete appliquee au backlog : **zero occurrence de `SCI-4` dans `docs/backlog/backlog.md`** (Q7) | basse sur le mecanisme, moyenne sur le test |

**Un `ADR` neuf (lot `MAT`), aucune `ArchRule` neuve.** Les cinq paquets sont statiques. Quatre sont
de type `question`, joues au bandit manchot. Le cinquieme, `LVE`, est de type `saisie` (section 7).

## 2. Citations des stories, ouvertes et non reprises de confiance

| Citation | Resolue ? |
|---|---|
| `MATRICE-couverture.md`, cinq lignes `non couvert` | oui : `MAT-CE1-04` ligne 701, `LVE-CM1-06` ligne 1759, `EPS-CM1-09` ligne 1972, `SCI-CM1-14` ligne 2154, `MUS-CP-05` ligne 1863 |
| `ADP-CE1-1` a `ADP-CE1-6`, `ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`, section 4 | **texte partiel**, pas mot pour mot. La story omet la definition de la retenue oubliee et la derniere phrase d'`ADP-CE1-5`, et reecrit le critere d'`ADP-CE1-3` plus strictement que la source (section 4.3) |
| `MUS-CP-05`, `MUS-1`, `MUS-2`, `education-musicale/competences-cp.md` | oui, lignes 80, 90 a 98 |
| `EPS-CM1-09`, `EPS-1`, `EPS-2`, `EPS-3`, `education-physique-et-sportive/competences-cm1.md` | oui, lignes 67 a 72 et 87. La paraphrase d'`EPS-2` coupe sa seconde moitie : *"et aucune performance chiffree n'est presentee comme une norme"* |
| `ANG-S1`, `ANG-S2`, `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, section 7.6 | oui, lignes 607 a 612 |
| "`LVE-CM1-07` sert dix mots supplementaires en choix" | **faux** (section 7.2). La phrase vient de `DEC-0101`, section 5 |
| `SCI-CM1-14`, `SCI-1` a `SCI-3`, `sciences-et-technologie/competences-cm1.md` | **texte exact, condition remplacee** pour `SCI-1` ; `SCI-2` y est bornee aux risques naturels et a l'impact humain (section 8.1) |
| `DEC-0100`, sections 2 et 5 (instance A) | oui, lignes 71, 140 a 142 |
| `DEC-0101`, section 4, `CYC-1` | **exact sur son perimetre**, qui est l'interieur du lot : *"les deux seules lignes de cycle 2 du lot ... ne partagent aucun objet ... avec les trois lignes de cycle 3"*. Aucun contenu deja servi n'y est compare (sections 6.3, 8.3) |
| Le jeu `bandit-manchot-additions-soustractions-ce1`, cite dans la commande de cet avis | **n'existe pas** : `git grep` ne le trouve nulle part. Les jeux de `MAT-CE1-03` sont `bandit-manchot-additions-ce1` et `bandit-manchot-soustractions-ce1`, deux entrees distinctes du catalogue |

Les cinq codes resolvent cote referentiel sous la forme `- [CODE] libelle` qu'exige le garde-fou
d'`ADR-0081`. Tous les identifiants de condition cites ici sont alloues dans
`squad/decisions/REGISTRE-identifiants.md`, **sauf `ANG-S1` et `ANG-S2`** (section 7.5).

## 3. Le patron couvre les cinq lots, et ce qu'il coute

Le cout d'un paquet de choix textuel est mesure a s44 sur trois commits (avis
`avis-faisabilite-s44-mus-sci.md`, section 3.1) : le paquet, son entree de catalogue, son test, et
deux inventaires (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`,
`TexteAccentueExposeParLePortEntrantTest`). Aucun ne demande de classe de production : `Question`,
`ReponseAEcrire` et les trois mecaniques ne lisent aucun champ de matiere (`ADR-0006`).

| Lot | Mecanique | Couts en plus des cinq fichiers |
|---|---|---|
| `MAT` | `bandit-manchot` | l'`ADR` de la section 4.1 et son predicat ; une entree de `SousPartieDeMathematiquesCe1` (`ADR-0097`, `ADR-0098`). Paquet numerique : il **n'entre pas** dans `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`, il est juge par le garde-fou de rang (section 4.4) |
| `MUS` | `bandit-manchot` | aucun. Second paquet de la matiere, la graphie `Éducation musicale` est fixee par `cp-musique-vocabulaire` (`ADR-0049`). Au CP : 15 mots et 2 phrases (`ADR-0089`, `PLAFOND_DE_DETTE = 5`, un paquet neuf entre a zero) |
| `EPS` | `bandit-manchot` | le domaine `"Conduire et maîtriser un affrontement collectif ou interindividuel"` entre a l'inventaire. Le CP ecrit `"... un affrontement individuel ou collectif"` : ce sont deux domaines distincts de deux referentiels, aucune graphie a unifier |
| `LVE` | **`saisie`** | une entree de `PAQUETS_DE_SAISIE_SERVIS_AU_2026_08_31` (`LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest`, egalite dans les deux sens), plus l'entree de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` qu'y portent deja `cp-mot-dicte` et `cp-complements-10-saisie` |
| `SCI` | `bandit-manchot` | aucun. Domaine `"Matériaux et objets techniques"` deja a l'inventaire |

## 4. `S45-MAT-ADDITION` (`MAT-CE1-04`)

### 4.1 Le garde-fou de `MAT-CE1-03` rend rouge tout paquet de `MAT-CE1-04` ecrit sur la forme servie

`ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest` (`ADR-0096`, decision 1) prend pour
population **tout paquet servi dont le niveau est `CE1` et dont tous les items reconnaissent le motif
`a + b = ?`**. La ligne declaree n'y est pas lue. Sur cette population il exige huit bandes, dont
`SOMME_JUSQUA_20` entre 10 et 20 %.

**Un paquet de `MAT-CE1-04` ne peut pas tenir cette bande, et c'est une propriete, pas un tirage.**
Ses operandes ont deux chiffres au moins, donc une somme d'au moins 20 ; la seule somme egale a 20
est `10 + 10`, sans retenue, exclue par `ADP-CE1-2`. Sa proportion de `somme <= 20` vaut donc 0 %
quel que soit son contenu, et le garde-fou est rouge. La forme naturelle de l'enonce est celle du
precedent de calcul pose (`cm1-multiplication-posee`, `"47 × 6 = ?"`, consigne `"Pose l'opération,
puis choisis le bon résultat."`) : c'est exactement la forme que le motif reconnait.

Le garde-fou fait ce pour quoi il existe. Sa troisieme mise a l'epreuve s'appelle
`unPaquetQuiBasculeSurLAdditionAvecRetenueEstRefuse` : un paquet qui **declare** `MAT-CE1-03` et
sert `MAT-CE1-04`. Mais il ne lit pas la declaration, si bien qu'un paquet qui declare honnetement
`MAT-CE1-04` tombe sous la meme regle.

**Ce que l'`ADR` neuf decide** : la population d'`ADD-CE1-2` est l'ensemble des paquets servis de
niveau `CE1`, dont tous les items sont des additions, **et qui declarent `MAT-CE1-03`** dans
`lignesDeReferentiel`. C'est un perimetre derive (`ADR-0013`), pas une enumeration : la cle est deja
obligatoire sur chaque paquet (`ADR-0081`).

**Ce qu'il interdit reellement, verifie dans les deux sens** :

1. un paquet qui declare `MAT-CE1-03` et sert l'addition avec retenue reste rouge. La mise a
   l'epreuve 3 ne change pas ;
2. un paquet qui declare `MAT-CE1-04` sort de la population. Le glissement inverse (servir
   `MAT-CE1-03` sous le code `MAT-CE1-04`) est tenu par `ADP-CE1-2` et `ADP-CE1-3` dans le test de
   ce paquet (section 4.3). Un second paquet de `MAT-CE1-04` aurait a porter les memes assertions.

Une mise a l'epreuve neuve est due : un paquet CE1 d'additions qui declare `MAT-CE1-04` n'est pas
dans la population, et ce meme paquet redeclare `MAT-CE1-03` y entre et rougit.

**L'alternative ecartee** : ecrire les enonces autrement (`"Pose et calcule 247 + 186."`) pour que le
motif ne les reconnaisse plus. Le rouge disparait, mais le perimetre d'`ADD-CE1-2` dependrait alors
d'une formulation, et un paquet de `MAT-CE1-03` ecrit de la meme facon en sortirait sans bruit.
C'est un faux vert construit.

**Ce qui me ferait changer d'avis** : un paquet qui servirait `MAT-CE1-03` et `MAT-CE1-04` a la fois,
en declarant sa ligne item par item comme `cm1-anglais`. La population se definirait alors par item,
et non par paquet. Aucun paquet de mathematiques CE1 ne le fait aujourd'hui.

`ChaquePaquetDeSoustractionsDeCe1TientSaRepartitionParFormeTest` a la meme construction, motif `a - b = ?` : le jour ou `MAT-CE1-05` est engagee, la meme regle de
perimetre s'y applique. Je ne la change pas avant : aucun paquet de `MAT-CE1-05` n'existe.

Ce n'est pas une regle ArchUnit, pour le motif deja ecrit par `ADR-0096` : la population est du YAML
et des couples d'entiers, pas du bytecode.

### 4.2 La sous-partie de mathematiques CE1 est obligatoire

`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` rend rouge tout paquet CE1 de
mathematiques dont la competence n'est pas allouee dans `SousPartieDeMathematiquesCe1`. Neuf entrees
aujourd'hui, aucune pour `MAT-CE1-04`. Une dixieme entree portant `MAT-CE1-04` et la chaine exacte
du descripteur est due dans le meme diff que le paquet.

### 4.3 Les six conditions dans le test du paquet

Toutes sauf `ADP-CE1-1` sont calculables a partir des deux operandes. Le test lit les operandes dans
l'enonce, comme `PaquetCm1MultiplicationPoseeTest`.

| Condition | Assertion | Ce qui reste a `nadia-prof` |
|---|---|---|
| `ADP-CE1-1` | **non encodable par le test du paquet** : c'est un texte de la matrice. Son motif ajoute une exigence de catalogue que la story ne reprend pas : *"la consigne de catalogue doit le lui demander quand meme"*. Assertion possible, et peu couteuse : la consigne de l'entree contient `Pose` | la mention, entierement |
| `ADP-CE1-2` | chaque item a au moins une retenue (unites ou dizaines) ; au moins 3 items aux deux operandes de deux chiffres, au moins 3 aux deux operandes de trois chiffres ; au moins 2 items avec retenue des dizaines vers les centaines ; au moins 1 item a deux retenues. Exiger des operandes de meme taille tient toute lecture de la source | rien |
| `ADP-CE1-3` | au moins `ceil(2n/3)` items ont un operande de trois chiffres **ou** une somme superieure a 100 | rien |
| `ADP-CE1-4` | les trois propositions ont le meme nombre de chiffres ; au moins un distracteur partage le chiffre des unites | "meme ordre de grandeur", s'il dit plus que le nombre de chiffres |
| `ADP-CE1-5` | **integral, sous Q1** : chaque distracteur est l'une des valeurs que le test calcule a partir des operandes pour les trois erreurs nommees ; entier positif, distinct des deux autres propositions. La "declaration dans le paquet" se fait par un marqueur par item (`# ADP-CE1-5: retenue oubliee`, par exemple), relu et compare au calcul | que les erreurs soient celles d'un enfant |
| `ADP-CE1-6` | l'enonce reconnait strictement le motif `a + b = ?`, sans lettre ni virgule ; aucune proposition ne porte autre chose que des chiffres | rien |
| ligne declaree | deja tenue par le garde-fou d'`ADR-0081` | rien |

**`ADP-CE1-5` peut etre mordu la ou `POS-3` ne l'a pas ete.** `PaquetCm1MultiplicationPoseeTest` ecrit
que `POS-1` et `POS-3` *"ne sont pas des proprietes calculables sur les items charges"*. Pour
l'addition, `ADP-CE1-5` l'est :
les trois erreurs sont des fonctions des deux operandes, une fois definies. Elles ne le sont pas
encore (Q1).

**Ecarts de la story avec sa source, a corriger avant l'ecriture (Q2)** :

1. le critere de la story est *"les items dont les deux nombres sont inferieurs a 100 representent au
   plus un tiers"*. La source est *"au moins les deux tiers mettent en jeu un nombre de trois chiffres
   ou produisent une somme superieure a 100"*. `56 + 67 = 123` satisfait la source et compte contre
   la story. Le test encode la source ;
2. la definition de la retenue oubliee et la derniere phrase d'`ADP-CE1-5` (*"si une forme coincide
   avec la bonne reponse ou avec un distracteur deja choisi, une autre forme de la liste est prise"*)
   manquent dans la story.

### 4.4 Les trois erreurs, le nombre de chiffres et le rang : une contrainte a connaitre avant d'ecrire

**La source se contredit sur la retenue oubliee, et `ADP-CE1-4` tranche.** La definition dit *"chaque
colonne additionnee separement, le chiffre des dizaines de la somme d'une colonne etant perdu"* :
`48 + 26` donne `64`. L'exemple qui suit dit *"`48 + 26 -> 614`"* : les sommes de colonnes ecrites
cote a cote. Cette seconde lecture donne toujours plus de chiffres que la bonne reponse (`614` contre
`74`, `31213` contre `433` pour `247 + 186`), puisque chaque item porte une retenue. Elle viole
`ADP-CE1-4` sur tous les items. Seule la premiere lecture est compatible.

**Le garde-fou de rang s'applique**, et il contraint le choix des distracteurs.
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` classe la bonne reponse par sa valeur parmi
les trois propositions. Chaque rang doit porter au moins 10 % des items (`SEUIL_MINIMUM`) et aucun
plus de la moitie (`SEUIL_MAJORITE`). Calcul fait a la main, a verifier par le test du paquet :

| Item | Bonne reponse | Retenue oubliee | Retenue reportee deux fois | Retenue des unites reportee aux centaines |
|---|---|---|---|---|
| `247 + 186` | 433 | 323 (les deux), 423 (unites), 333 (dizaines) | 443 (unites), 533 (dizaines) | 523 |
| `48 + 26` | 74 | 64 | 84 | 164 : trois chiffres, viole `ADP-CE1-4` |

Consequences :

1. sur un item a deux chiffres et une seule retenue, si la "mauvaise colonne" est celle des
   centaines, il reste une erreur plus petite et une plus grande. La bonne reponse y est **toujours au
   rang du milieu** ;
2. dans les lectures du tableau, la bonne reponse n'est la plus grande que si les deux distracteurs
   sont plus petits : deux formes de retenue oubliee, ce qui demande deux retenues et une somme assez grande pour garder le nombre de
   chiffres. Il faut au moins 10 % de tels items, soit 2 sur un paquet de 12 a 20 items. `ADP-CE1-2`
   n'en exige qu'un.

Rien de cela n'est un obstacle : c'est un plan de fabrication a connaitre avant le premier item. La
"mauvaise colonne" et la "retenue reportee deux fois" ont plusieurs lectures. Le test ne peut en
encoder qu'une, et la definition est pedagogique (Q1).

### 4.5 Collisions

**Avec `MAT-CE1-03`, mesure sur le paquet servi.** `ce1-additions-jusqu-a-100` porte 32 items, dont 4
de la forme `AVEC_RETENUE` : `27 + 15`, `36 + 28`, `48 + 26`, `59 + 23`. Ce sont des couples a deux
chiffres, somme inferieure a 100, exactement le tiers que `ADP-CE1-3` laisse au paquet neuf.
`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` a pour perimetre un seul paquet, et aucun
garde-fou ne compare deux paquets. Assertion proposee, peu couteuse et fidele au motif
d'`ADP-CE1-3` : aucun couple du paquet neuf n'est un couple de `ce1-additions-jusqu-a-100`, lu dans
le fichier. Je ne la pose pas comme condition : ce n'est pas la mienne.

**Avec `MAT-CE1-05`** : aucune collision de contenu. La ligne n'est pas engagee, aucun paquet ne la
sert, et ses conditions (`SOP-CE1-1` et suivantes) portent sur la soustraction. Le seul lien est le
garde-fou jumeau de la section 4.1.

**Avec le CM1** : `nadia-prof` l'a verifie dans sa source (section 4.1) : aucune ligne de CM1 ne sert
l'addition posee.

## 5. `S45-MUS-INSTRUMENTS` (`MUS-CP-05`)

Sur le patron de `PaquetCpMusiqueVocabulaireTest`.

| Condition | Assertion | Ce qui reste a `nadia-prof` |
|---|---|---|
| `MUS-2` | **structurel** : `texteAVoixHaute()` vide sur chaque item, comme a s44. **Lexical : ne pas recopier `MOTS_INTERDITS_SOUS_CHAINE`.** Il contient `frapp`, et le test voisin `mus2AucunTexteNePorteLeVerbeTaper` interdit `taper`. Or une percussion se definit comme un instrument qu'on frappe : *"On frappe un tambour pour en jouer"* est un indice exact et serait rouge. Garder `adulte`, `fredonn` ; retirer `frapp` et `taper` | un adulte a qui l'on demande de frapper un rythme, sans ces mots |
| `MUS-1` | **partiel, lexical** : `écout`, `entend`. Ne pas y ajouter `son` (possessif) ni `grave` (adjectif courant) | un enonce qui decrit un timbre sans ces deux mots |
| `FER-1` | `cordes`, `vents`, `percussions` chacune en bonne reponse au moins une fois, et au moins deux instruments distincts par famille dans les enonces | que chaque item serve vraiment la famille |
| `FER-2` | au moins `nombreDeManches + 1` items (`DEC-0064`) | rien |
| `FER-3` | deux taches distinguees par une propriete lisible (bonne reponse dans l'ensemble des familles, ou non) ; la moins servie a trois items | que ce soient deux operations mentales |
| `MUS-CP-R1`, sa forme generale | aucun enonce ne contient le texte d'une de ses propositions. C'est la reserve de s44 rendue executable, pas une condition neuve | rien |

**Collision : aucune**, recherche lexicale sur les 106 paquets servis (noms d'instruments courants,
`instrument`, `percussion`, `cordes`). Le seul instrument present est `"une flûte"`, distracteur d'un
item de longueurs de `cp-comparer`. `cp-musique-vocabulaire` (`MUS-CP-11`) n'en porte aucun. Une
adjacence de sens, nommee pour qu'elle ne soit pas trouvee a l'audit : `"un triangle"` apparait neuf
fois au CP, en bonne reponse ou en distracteur, comme figure plane (`cp-figures-planes`) ; s'il devient un instrument de percussion dans le
paquet neuf, c'est le meme mot au meme niveau avec deux sens.

**Le seul exemple de la source tombe sous la regle de la derniere ligne du tableau** (Q3, non
bloquant). *"La guitare est-elle un instrument à cordes ou à vent ?"* nomme deux des trois
propositions, et la troisieme (`percussions`) s'ecarte a la lecture : l'item devient un choix entre
deux. C'est la famille de `MUS-CP-R1`, que `nadia-prof` a reconnue dans son propre exemple de
`MUS-CP-11`.

## 6. `S45-EPS-ROLES` (`EPS-CM1-09`)

### 6.1 Les conditions que la story ne cite pas, et qui valent

Verifiees a leur source, toutes applicables a cette ligne :
`BIA-4` (`education-physique-et-sportive/competences-cm1.md`, ligne 72), `EPS-4` et `EPS-6`
(`squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/verdicts-nadia-instance-C.md`, lignes 464
et 486), `EPS-7` (`ARBITRAGE-PREALABLE-s33-le-plan-2-applique-a-s33-...md`, section 3.5) et `EPS-8`
(`squad/sprints/s33-plan-2-tenu-le-palier-2-differe-a-s34/verdicts-nadia-instance-C.md`, ligne 508), `FER-1` a `FER-3`, et la mention de matiere des lignes 9 et 10, deja ecrite sur
`EPS-CM1-10` dans la matrice. `ARBITRAGE-PREALABLE-s32-...-ligne-marginale.md`, section 13, refus 3,
nomme cette ligne-ci : *"que fais-tu si un joueur triche ?" est l'item le plus evident de la ligne et
le plus faux*.

### 6.2 Conditions encodables

| Condition | Assertion | Ce qui reste a `nadia-prof` |
|---|---|---|
| trois roles | chacun des mots `arbitre`, `observateur`, `marqueur` figure dans l'enonce d'au moins un item | que l'item porte vraiment la tache du role |
| `EPS-1` | **partiel** : chaque ligne `- enonce:` porte un marqueur `# EPS-1: rappelee` ou `# EPS-1: universelle`, lu dans le fichier brut comme les marqueurs `HIS-3` | que la regle soit rappelee ou universelle |
| `EPS-3` | **partiel, lexical** : aucun enonce ne contient `que fais-tu` ni `que dois-tu` (refus 3 de s32) | toute attitude portee par une bonne reponse |
| `EPS-2` | aucun : une interdiction des chiffres empecherait un item de marqueur qui compte des points, qui n'est pas une performance | entierement |
| `EPS-4`, `EPS-6` a `EPS-8` | aucun : ce sont des jugements de registre | entierement |
| `FER-2`, `FER-3` | au moins `nombreDeManches + 1` items ; deux taches, trois items au moins pour la moins servie | que ce soient deux taches |

### 6.3 Le recouvrement est vertical, et personne ne l'a verifie

`cp-eps-regles-jeu-collectif` (`EPS-CP-08`, *"Connaître et respecter les règles d'un jeu collectif
simple."*) sert onze items. Cinq d'entre eux, ceux qui finissent par *"Que se passe-t-il ?"*, posent exactement la tache de l'arbitre telle que la
source de `EPS-CM1-09` la decrit : une situation de jeu decrite, la regle a appliquer.
*"Au chat perché, Léa touche Hugo monté sur un banc. Que se passe-t-il ?"*, *"Aux déménageurs, Hugo
porte deux objets à la fois. Que se passe-t-il ?"*. L'exemple de `nadia-prof` pour la ligne CM1 (*"Le
ballon sort sur la ligne de côté, à qui la remise en jeu ?"*) a la meme forme.

C'est le constat de s43 sur `EPS-CM1-10`, section 7.2 de son avis : un paquet de CM1 qui reprendrait
cette forme sur des jeux du CP passerait tous les garde-fous. La barre de longueur ne borne que le
CP et le CE1. `nadia-prof` y a repondu par `EPS-9`, propre a `EPS-CM1-10` : il ne vaut pas ici.
**Condition du verdict** : `nadia-prof` lit `cp-eps-regles-jeu-collectif` avant l'ecriture et pose le
discriminant CM1/CP de cette ligne (Q4). Je ne dis pas ce qu'il doit etre.

Adjacence de meme niveau, sans recouvrement : `cm1-emc-regles-vie-collective` et `cm1-emc-regle-loi`
portent la regle comme objet civique, pas la regle d'un jeu.

## 7. `S45-LVE-ECRITURE` (`LVE-CM1-06`)

### 7.1 La mecanique existe, et ce n'est pas celle que la story nomme

La story ecrit *"en jouant au bandit manchot"*. La ligne est jouable par **saisie** (matrice, ligne
1759 ; source, section 7.6). `LVE-CM1-04`, que la commande cite en modele, est servie au bandit
manchot : c'est un jeu de choix, pas un precedent de saisie.

Ce qui existe : la mecanique `saisie` (`Saisie`, `SaisieSession`, `ReponseAEcrire`), deux paquets
servis (`cp-complements-10-saisie`, numerique ; `cp-mot-dicte`, alphabetique, avec voix). Le
comportement est en Java pur dans le domaine (`ADR-0008`, `ADR-0058`) :

- comparaison des chaines entieres, sans distance (`SAI-5`) ; repliage des accents, de la casse et
  des blancs en mode `libre` (`NormalisationDeSaisie`, `Locale.ROOT`, aucune langue) ;
- indice affiche des le premier echec (`M1`), masque si le paquet declare `indicesNommentLaReponse`
  (`M21`) ;
- trois essais avant la revelation (`M5`) ; reussite comptee au premier coup seulement.

**Aucune ligne de production n'est due.** `ReponseAEcrire` ne porte **pas d'illustration** : c'est la
limite de la section 7.3.

### 7.2 Le lexique ferme compte 15 mots, pas 25

Mesure sur `cm1-anglais.yaml`. Les 15 items de `LVE-CM1-04` servent 15 noms au singulier, sans
article (`ANG-5`) : `house`, `dog`, `friend`, `morning`, `bedroom`, `street`, `bridge`, `hill`,
`town`, `field`, `witch`, `castle`, `fairy`, `king`, `ghost`. Les distracteurs sont pris dans ces
memes mots. Les 10 items de `LVE-CM1-07` sont des questions en francais sur des aspects culturels
(Londres, Big Ben, Thanksgiving). La matrice et `nadia-prof` le disent : *"découverte culturelle seule,
en français ; aucune compétence linguistique n'est servie"* (s32, section 13, refus 8). **Aucun mot de
`LVE-CM1-07` n'est un mot anglais enseigne en choix.** Un `Thanksgiving` a ecrire violerait `ANG-S1`
et ce refus a la fois.

`ANG-S1` reste tenable : 15 mots suffisent au plancher de la story (cinq) et a `FER-2`. La story doit
retirer `LVE-CM1-07` de son premier critere (Q6). **Aucune expression** n'est servie en choix : le mot
"expressions" du libelle est inservable sous `ANG-S1` aujourd'hui.

### 7.3 `FER-1` sur ce libelle, que la story ne cite pas

`FER-1` vaut pour toute ligne depuis s28 : chaque element enumere par le libelle, *"et notamment par
sa parenthese"*, est servi en bonne reponse, ou la ligne ferme avec mention de couverture partielle.
Libelle : *"Écrire des mots et des expressions mémorisés (légender une image, renseigner une
fiche)."*

- *légender une image* : un item de saisie ne porte pas d'image (section 7.1). Hors de portee sans
  code de production ;
- *expressions* : exclues par `ANG-S1` (section 7.2) ;
- *renseigner une fiche* : faisable en texte ou non, c'est un jugement pedagogique.

La story ne prevoit aucune mention de couverture partielle. Elle est probable. Je ne la redige pas
(Q5).

### 7.4 Conditions encodables

| Condition | Assertion | Ce qui reste a `nadia-prof` |
|---|---|---|
| `ANG-S1` | **integral sur sa part de contenu** : chaque `formeDeReference` appartient a l'ensemble **derive de `cm1-anglais.yaml`**, lu par le test : bonnes reponses anglaises et mots entre guillemets des enonces des items `LVE-CM1-04`. Aucune liste recopiee : si le paquet de choix perd un mot, le paquet de saisie rougit | que le paquet de choix soit valide |
| `ANG-S2` | chaque `indice` est non vide, contient la longueur de `formeDeReference` en chiffres et sa premiere lettre isolee (une lettre seule, bornee par des non-lettres), et ne contient pas `formeDeReference`. Le paquet ne pose pas `indicesNommentLaReponse` | que l'indice soit lisible |
| plancher de la story | au moins cinq `formeDeReference` distinctes | rien |
| aucune voix | `texteAVoixHaute()` vide sur chaque item : *"aucun dispositif ni aucun indice ne demande a un adulte de prononcer un mot d'anglais"* (`cm1-anglais`, reserve du 2026-09-02) | rien |
| `FER-2` | au moins `nombreDeManches + 1` items | rien |
| `FER-3` | deux taches lisibles (une seconde tache reste a definir) | laquelle (Q5) |
| ligne declaree | `lignesDeReferentiel: ["LVE-CM1-06"]`, deja tenu (`ADR-0081`) | rien |

`SAI-6` et `SAI-7` : le bareme des formes acceptees est relu par `nadia-prof`.
`LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest` prouve qu'il est lu de bout en bout,
jamais qu'il est bon.

### 7.5 Une dette de garde-fou, a moi : `ANG-S1` et `ANG-S2` ne sont pas alloues, et le registre ne les voit pas

`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (`ADR-0085`) reconnait un identifiant par le
motif `([A-Z]{2,5})-(R?[0-9]{1,2})`. `ANG-S1` porte une lettre `S` qui n'est pas `R` : le motif ne le
voit pas, et il n'est pas dans le registre. Methode : `git grep -o` sur les fichiers suivis a `c15a6c4d`, avant cet avis,
arbres `contreexemple` exclus. **`ANG-S1` : 26 occurrences, `ANG-S2` : 18.** C'est le trou que `ADR-0088`,
clause D, a ferme pour les identifiants a trois segments, sous une autre forme. Les sous-clauses
lettrees (`SAI-9a`, `FAB-1c`) ont la meme forme mais designent des parties d'un identifiant alloue ;
`ANG-S1` n'est la partie d'aucun.

**Non bloquant pour s45** : le paquet de saisie citera ces deux identifiants, et rien ne rougira. Je
l'ouvre ici comme dette explicite, a planifier par `sofia-sm`. La forme de l'identifiant appartient a
`nadia-prof`. Elargir le motif est une decision de l'`ADR-0085`.

### 7.6 Le motif de `R6` n'a jamais ete instruit sur cette ligne

`ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md`, troisieme amendement (2026-08-25, correction
automatique acceptee sur ordinateur et tablette) : `R6` refuse a la saisie une ligne qui demande de
produire des lettres, parce que *"sur un clavier qui corrige c'est l'appareil qui les produit"*. Le
meme texte ecrit, section 5 : *"Il n'y a aucune ligne equivalente au CM1"*. C'etait vrai le
2026-08-25. Le referentiel d'anglais CM1, et `LVE-CM1-06` avec lui, date du 2026-09-02, et `ANG-S1`,
`ANG-S2` ne parlent pas du clavier.

Sur cette ligne, l'effet est plus fort qu'en francais. Un clavier configure en francais peut
corriger un mot anglais juste en mot francais : une reponse juste refusee, le cas de `SEUIL-2`. Un
clavier qui connait l'anglais peut corriger un mot faux en mot juste : la ligne est servie par
l'appareil. **Ce n'est pas un jugement technique, et je ne le tranche pas** : rien dans le produit ne
peut le mesurer, et l'utilisatrice a ecarte toute neutralisation. C'est une question a `nadia-prof`,
**a poser avant l'ecriture**, parce que sa reponse peut retirer le lot (Q5).

## 8. `S45-SCI-NUMERIQUE` (`SCI-CM1-14`)

### 8.1 Les conditions dans leur version qui vaut

1. **`SCI-1`** : la story cite la formulation du 2026-09-03 et ecrit *"quand le retour s'affiche"*.
   Celle qui vaut date du 2026-09-06 (`ARBITRAGE-PREALABLE-s30-...-quinze-reserves.md`, section 1.1) :
   **l'`indice`**, sur les items dont un distracteur porte une conception fausse courante, dit
   pourquoi elle est fausse en une phrase, sans nommer la bonne reponse ni dire *"ta réponse"*.
2. **`SCI-4`** (meme document, section 1.2) : un tel item offre trois propositions au moins.
3. **`SCI-2`** : la source la borne *"sur les risques naturels et l'impact humain"* (referentiel) et
   *"sur les lignes 16 et 18"* (s30, section 2.5). La story l'applique a la ligne 14. Le critere est
   defendable, mais il n'est pas cite "mot pour mot" : c'est une extension, a ecrire comme telle ou a
   retirer.
4. **`FER-1`** : s30, tableau 2.2, ligne 14 : parenthese *exemplative*, **2 des 4 elements** en bonne
   reponse au moins.
5. **`FER-3`** : couple de taches d'exemple, meme ligne : *"reconnaitre la bonne conduite / dire
   pourquoi une conduite est risquee"*.
6. **`HIS-3`** et **`CON-1`**, *"a l'identique"* (s30, section 2.5).

C'est le meme ecart qu'a s44 sur `SCI-CM1-13`. Q2 de cet avis-la le routait a `camille-po` ; le
backlog n'a pas ete amende, et le referentiel non plus. **Un developpeur qui suit la story remplit
`explication`**, que l'enfant ne voit presque jamais (s30, section 1.1). C'est la condition du
verdict (Q7).

### 8.2 Conditions encodables

Sur le patron de `PaquetCm1SciencesEvolutionObjetTechniqueTest`.

| Condition | Assertion | Ce qui reste a `nadia-prof` |
|---|---|---|
| `SCI-1` | chaque indice compte une phrase (convention d'`ADR-0089`) et ne contient ni la bonne reponse de son item ni `ta réponse` | que l'indice dise pourquoi la conception est fausse |
| `SCI-4` | deux distracteurs sur chaque item, plus simple que de designer les items "a conception fausse" | quels items en portent une |
| `SCI-3` | items dont la bonne reponse porte un chiffre : au plus `floor(n / 6)` (demonstration ci-dessous) | les valeurs ecrites en lettres |
| `FER-1` | marqueur par item `# element: identification`, `courriel`, `recherche` ou `donnees`, lu dans le fichier brut ; au moins deux elements distincts | que l'item serve l'element |
| `FER-2`, `FER-3` | au moins `nombreDeManches + 1` items ; deux taches, trois items pour la moins servie | que ce soient deux taches |
| `HIS-3` | chaque ligne `- enonce:` porte `# HIS-3:` suivi d'un texte non vide | que la source dise le fait |
| `CON-1` | rien de plus que les garde-fous existants (`ADR-0083`, `ADR-0086`) | le fond |

**`SCI-3` sur le lot, mesure de ce jour.** Script Python de poste, non commite, cle `bonneReponse` des
13 paquets `cm1-sciences-*` servis : **145 items, 0 bonne reponse portant un chiffre**. Avec `k`
items numeriques sur `n` dans le paquet neuf, `k <= floor(n / 6)` entraine `k <= (145 + n) / 6` : le
test du paquet tient la condition de lot. **Ce lot-ci y est plus expose que les precedents** : la
longueur d'un mot de passe ou l'age d'acces a un service sont des valeurs a restituer.

**Le piege `HIS-1` de s44, section 5.3, vaut toujours** : zero marqueur `# HIS-1:` dans les 13 paquets
du lot (meme script). Un marqueur `HIS-1` recopie de l'histoire-geographie soumettrait tout le lot a
`ADR-0079`.

### 8.3 Collisions et adjacences

Aucun paquet servi ne porte un mot de passe, un courriel, une recherche en ligne ou des donnees
personnelles (recherche lexicale sur les 106 paquets). Le seul referentiel voisin est
`QLM-CP-08`, servi par `cp-qlm-objets-numeriques` (*"Reconnaître les objets numériques du quotidien et
quelques règles simples de leur usage."*). Ses six items de regles en portent cinq sur le materiel (recharger, ne pas
mouiller, ne pas faire tomber, son du casque, ecran dans le noir) et un sur l'aide d'un adulte. Adjacence verticale, pas
recouvrement : `CYC-1` impose de la lire. Le tableau de `CYC-1` de s30 (section 2.6) ne nomme pas la
ligne 14, parce que ce paquet de CP a ete servi apres (s43).

## 9. Aucune `ArchRule` neuve, et un `ADR`

Chaque condition des sections 4 a 8 est une propriete d'un item ou d'un paquet de YAML. ArchUnit lit
du bytecode : le mecanisme automatique qui les garde est le test de chaque paquet, execute par le
job `build`. La seule decision d'architecture est le perimetre de la section 4.1, qui est un
garde-fou de contenu.

Aucune mecanique neuve, aucun champ neuf, aucune dependance neuve entre mecaniques : les regles de
`DecouplageMecaniqueContenuTest` n'ont rien de neuf a interdire ici.

Compte inchange depuis s44 : **36 `static final ArchRule`, 35 `@ArchTest`**. Methode : `git grep -c`
sur les fichiers suivis de `domain`, `application`, `adapters` et `bootstrap`, arbres `contreexemple`
exclus, donc `target/` et worktrees exclus par construction. Sans l'exclusion, 38 et 35.

## 10. Pieges de fabrication communs, nommes avant le premier rouge

1. **Les deux inventaires** (section 3) : `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` pour les trois paquets
   textuels et pour la saisie, jamais pour le paquet de `MAT`. `TexteAccentueExposeParLePortEntrantTest`
   pour toute chaine de descripteur neuve.
2. **`AucunTitreDeJeuNePorteSonNiveauTest`** : aucun titre ne porte `CP`, `CE1` ni `CM1`. Le titre
   `"Les grandes additions"` est pris par `MAT-CE1-03`.
3. **Le test de paquet** est ici la seule garde automatique des conditions, et il est du sur les cinq
   lots.
4. **Le paquet de saisie** s'ajoute a `PAQUETS_DE_SAISIE_SERVIS_AU_2026_08_31`, et son entree de
   catalogue porte `mecanique: saisie`.

## 11. Risques

| Code | Risque | Direction | Parade |
|---|---|---|---|
| R1 | Paquet de `MAT` ecrit avant l'`ADR` de la section 4.1 | rouge certain sur `ADD-CE1-2` | `ADR` d'abord, ou dans le meme diff |
| R2 | Enonces de `MAT` reformules pour echapper au motif | faux vert construit | refuser cette voie (section 4.1) |
| R3 | Distracteurs de `MAT` choisis sans le plan de rang (section 4.4) | rouge du garde-fou de rang, reecriture | Q1 avant l'ecriture |
| R4 | Lexique de `MUS-2` recopie de s44 | faux rouge sur les percussions | retirer `frapp` et `taper` (section 5) |
| R5 | Paquet d'EPS CM1 qui refait `EPS-CP-08` | invisible a tout automate | Q4 avant l'ecriture |
| R6 | Correction automatique sur des mots anglais, le motif du refus `R6` de s19 | verdict qui depend de l'appareil | Q5, avant l'ecriture |
| R7 | Paquet d'anglais ecrit en choix, ou sur un mot de `LVE-CM1-07` | refus a l'audit | Q6 |
| R8 | Paquet de sciences ecrit contre la `SCI-1` citee par la story | faux vert, refus a l'audit | Q7 |

## 12. Questions routees, via `sofia-sm`

| Code | Question | A qui | Bloquante ? |
|---|---|---|---|
| Q1 | `ADP-CE1-5` : quelle definition calculable des trois erreurs ? Confirmer que la retenue oubliee est `48 + 26 -> 64` (la definition) et non `614` (l'exemple, incompatible avec `ADP-CE1-4`) ; dire quelle est la "mauvaise colonne" et ce qu'est "reportee deux fois". La reponse decide les rangs atteignables (section 4.4) | `nadia-prof` | avant l'ecriture |
| Q2 | `S45-MAT-ADDITION` : remplacer le critere d'`ADP-CE1-3` par le texte de la source ; ajouter la definition et la derniere phrase d'`ADP-CE1-5`, et l'exigence de consigne du motif d'`ADP-CE1-1` | `camille-po` | avant l'ecriture |
| Q3 | `MUS-CP-05` : la forme de l'exemple du referentiel, qui nomme deux des trois propositions, est-elle admise malgre `MUS-CP-R1` ? | `nadia-prof` | non |
| Q4 | `EPS-CM1-09` : quel discriminant CM1/CP face a `cp-eps-regles-jeu-collectif` (section 6.3) ? Et comment `FER-1` se lit sur les trois roles : role en bonne reponse, ou role porte par l'enonce ? | `nadia-prof` | **condition du verdict** |
| Q5 | `LVE-CM1-06` : le motif de `R6` vaut-il sur cette ligne (section 7.6) ? Quelle mention de couverture partielle pour *légender une image* et *expressions* (section 7.3) ? Quelle seconde tache pour `FER-3` ? | `nadia-prof` | **avant l'ecriture, peut retirer le lot** |
| Q6 | `S45-LVE-ECRITURE` : mecanique `saisie` et non bandit manchot ; lexique de 15 mots de `LVE-CM1-04`, sans `LVE-CM1-07` ; citer `FER-1` a `FER-3`. La meme correction de fait vaut pour `DEC-0101`, section 5 | `camille-po` | avant l'ecriture |
| Q7 | `S45-SCI-NUMERIQUE` : citer `SCI-1` du 2026-09-06 et viser l'`indice` ; ajouter `SCI-4`, `FER-1` a `FER-3`, `HIS-3`, `CON-1` ; ecrire `SCI-2` comme une extension. Appliquer aussi Q2 de s44, restee sans suite | `camille-po` | **condition du verdict** |
| Q8 | Planifier l'`ADR` de la section 4.1 (`theo-archi`) avant le paquet de `MAT`, et la dette de la section 7.5 | `sofia-sm` | l'`ADR` oui, la dette non |

## 13. Ce que cet avis ne fait pas

Il n'ecrit ni l'`ADR` de la section 4.1, ni les paquets, ni leurs tests : les tableaux des sections 4
a 8 sont la specification que le developpeur affecte encode, dans le meme commit que son paquet. Il
ne corrige aucune story, ne tranche aucune question, et ne touche aucun garde-fou existant.

## 14. Mesure

- **Faits de contenu** (106 paquets, 13 paquets de sciences CM1, 145 items, comptes de marqueurs) :
  script Python de poste lisant `adapters/adapter-content/src/main/resources/contenu/`, non commite.
  Le nombre de paquets est confirme par `git ls-files` sur le meme repertoire (106).
- **Garde-fous cites** : lus dans le code a `c15a6c4d`. Aucun n'a ete execute sur un paquet neuf,
  puisqu'aucun n'existe : leurs verdicts sur les lots sont deduits de leur code, et le premier
  `mvn -B verify` de chaque paquet les confirmera.
- **Calculs de la section 4.4** : faits a la main, non executes. Le test du paquet les refera.
- **Porte technique sur ce diff** : `bash outils/mvn-jdk21.sh -B clean verify`, machine virtuelle des
  tests en Java 21.0.10 (version rapportee par ArchUnit dans le journal, pas par la toolchain).
  `BUILD SUCCESS`. Surefire : `domain` 120, `application` 17, `adapter-web` 400, `adapter-content`
  974, `bootstrap` 288 ; Failsafe : `bootstrap` 6 ; zero echec, zero erreur. Le texte de cet avis a
  ete retouche pendant ce build : les deux garde-fous qui lisent ce fichier,
  `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` et `ToutesLesReferencesDeDecisionResolventTest`,
  ont ete relances sur son etat final (`-pl bootstrap -am`), verts.
- **Autres jobs de la CI** : `chemins` (test du garde-fou puis verification des fichiers suivis) lance
  en local, vert. `prefixes` est lance sur le commit avant fusion. `outils` n'est pas lance : ce diff
  ne touche aucun outil de poste.
