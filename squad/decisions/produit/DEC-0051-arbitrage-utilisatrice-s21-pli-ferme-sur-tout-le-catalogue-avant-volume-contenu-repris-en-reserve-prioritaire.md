# DEC-0051 - Arbitrage de l'utilisatrice pour s21 : le pli se ferme sur tout le catalogue avant
# tout ajout de jeu, la matiere des jeux neufs vient de `contenu-repris/` avant redaction neuve

- **Type** : arbitrage de perimetre (utilisatrice), instructions de planning
- **Date** : 2026-08-27
- **Auteur** : camille-po
- **Sprint** : s21 (ouverture)
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette decision fixe un
  ordre de travail et une source de matiere, elle ne cible aucune ligne de
  `squad/programme/MATRICE-couverture.md` elle-meme ; les lignes candidates pour le volume sont
  instruites section 4, chacune avec son lien de referentiel.
- **Statut** : Acceptee

## Contexte

s20 se cloture a **15/43 (34,9 %)**, `REP-8` a `REP-10` en backlog non affecte, `cm1-reperes` de
retour au catalogue sous reserve (`DEC-0050`). L'utilisatrice formule aujourd'hui, mot pour mot :
*"je voudrai qu'un maximum de jeux soient disponibles apres le prochain sprint"*.

Deux questions lui ont ete posees, avec leurs consequences chiffrees, avant tout geste de
planning :

1. Fermer d'abord le defaut de pli mesure par `nadia-prof` (`REP-8`, `DEC-0050`) sur tout le
   catalogue, avant d'ajouter un seul jeu neuf - au prix chiffre d'avoir moins de jeux livres a s21
   qu'un sprint qui commencerait directement le volume - **ou** ouvrir le volume en premier et
   traiter `REP-8` ensuite, en acceptant que les jeux neufs de s21 naissent avec le meme defaut que
   `cm1-reperes` vient de reveler.
2. Puiser la matiere des jeux neufs dans les 61 fichiers de `contenu-repris/` - **ou** faire
   rediger du contenu neuf par la squad, sans s'appuyer sur cette reserve.

## Decision

**1. Le pli se ferme en premier, sur tout le catalogue, avant tout ajout de jeu.** Motif retenu par
l'utilisatrice : les jeux neufs naissent sans le defaut, aucune dette n'est ajoutee. Elle accepte
explicitement d'avoir **moins de jeux a s21** en echange de ne pas reproduire, sur du contenu neuf,
le defaut que `nadia-prof` vient de nommer sur `cm1-reperes` puis d'etendre a huit jeux sur
dix-sept du catalogue deja servi (`REP-8`, `DEC-0050`, section 4).

**Option ecartee** : ouvrir le volume avant `REP-8`. Chiffre a l'appui de ce qu'elle ecarte : `REP-8`
mesure deja huit jeux sur dix-sept et quarante-trois items sur cent cinquante-sept du catalogue
servi (`DEC-0050`, section 4, mesure de `nadia-prof` du 2026-08-27) - ouvrir du volume avant de
fermer ce defaut l'aurait etendu a chaque jeu neuf, dans un catalogue qui n'a encore jamais construit
une regle de forme pour l'eviter.

**2. La matiere des jeux neufs de s21 vient de `contenu-repris/`, pas d'une redaction de zero.**
Motif retenu : la redaction des enonces, des distracteurs et des listes de mots y est deja faite, et
ce contenu a deja ete joue en production sur `main`. `contenu-repris/README.md` reste la regle :
reprendre un fichier veut dire en reprendre la **matiere**, jamais sa structure - le format de
`main` est lie a ses neuf familles de chargeurs dupliques, dette n°3 de `CLAUDE.md`, que
`round-two` ne reproduit pas.

**Option ecartee** : rediger du contenu neuf de zero pour le volume de s21. Ecartee au profit de la
reserve, sans que cela ferme la redaction neuve pour toujours - simplement, elle n'est pas la
premiere source regardee ce sprint.

## 1. Verification personnelle avant d'ecrire les instructions de planning

### Etat du catalogue, verifie ce jour

`adapters/adapter-content/src/main/resources/catalogue.yaml`, HEAD `478e04d`, 2026-08-27 :
**18 jeux**, tous sur la mecanique `bandit-manchot` (`grep -c "^  - id:"` sur le fichier). Chaque
`paquet:` du catalogue correspond a exactement un fichier de
`adapters/adapter-content/src/main/resources/contenu/` et reciproquement (`diff` entre la liste des
`paquet:` du catalogue et la liste des fichiers du dossier, ecart nul) : correspondance un pour un,
aucun paquet dormant.

### Etat de la reserve, verifie ce jour

`find contenu-repris -type f \( -iname "*.yml" -o -iname "*.yaml" \)`, HEAD `478e04d`, 2026-08-27 :
**61 fichiers**, ranges par famille de jeu de `main` : `quiz/` (24), `associer/` (11), `memory/`
(6), `mots/` (5), `puzzle/` (4), `textes-trous/` (3), `phrase/` (2), `fractions/`, `frise/`,
`syllabes/` (1 chacun), plus 3 paquets de calcul mental a la racine.

### Etat de la matrice, verifie ligne par ligne ce jour

`squad/programme/MATRICE-couverture.md`, HEAD `478e04d`, comptage par extraction des 47 lignes de
competence des quatre referentiels (mathematiques CP/CM1, francais CP/CM1) : **15 `couvert-valide`,
4 `en cours`, 24 `non couvert`, 4 `non couvrable en l'etat`**, soit 15/43 (34,9 %) sur le
denominateur atteignable, chiffre concordant avec `DEC-0050`.

## 2. `REP-8` passe en tete de sprint

La story existe deja, ecrite a la cloture de s20 avec ses criteres d'acceptation
(`docs/backlog/backlog.md`, section "Reserves de cloture s20", entree `REP-8`). Je ne la reecris
pas : je l'affecte. Mise a jour apportee au meme fichier, dans le meme geste que cette decision :
`REP-8` n'est plus "non affectee a s21" mais premiere du planning de s21, ses criteres
d'acceptation restant inchanges - c'est leur priorite qui change, pas leur contenu.

**Perimetre a fixer par `sofia-sm` au planning, que je ne tranche pas ici** : le catalogue entier
compte aujourd'hui 18 jeux, quand la mesure de `nadia-prof` du 2026-08-27 en couvrait 17 (mesure
faite avant le retour de `cm1-reperes` execute par le meme geste que `DEC-0050`). Le dix-huitieme
jeu (`cm1-reperes`, deja verifie a 14 items sur 16 au-dessus du pli sur le verdict, sous le pli sur
l'indice comme les huit autres) reste a inclure dans le perimetre de verification de `REP-8`, sans
quoi la fermeture du pli se ferait sur un catalogue qu'elle ne couvrirait pas en entier.

**Ce que je rappelle, deja ecrit dans la story et non modifie par cette decision** : le critere
d'acceptation n'est pas atteignable par une seule valeur de marge ou de rembourrage - la forme
exacte revient a `ines-design` et `theo-archi`, jamais a `lea-dev` (`nadia-prof` le route
explicitement comme un defaut de catalogue, pas une reprise de paquet).

## 3. L'obstacle des 95 items sur 296 : il reste ouvert, `REP-8` ne le ferme pas par construction

Nomme par `theo-archi` au planning de s20 (`squad/sprints/s20-mot-cible-designe-et-conception-de-la-
saisie/planning.md`, ligne 204, mesure du 2026-08-25), route a `nadia-prof` puis a moi, aucun statut
de couverture prononce a la cloture de s20 (`RAPPORT.md` de s20, obstacles ouverts).

**Meme famille de defaut que `REP-8` (budget vertical du cabinet, taille de `.proposition`),
confirmee par trois jeux communs aux deux mesures** (`bandit-manchot-problemes-additifs-cp`,
`bandit-manchot-perimetre-cm1`, `bandit-manchot-grands-nombres-cm1`) - **mais pas la meme mesure**,
et `DEC-0050` (section 4) l'a deja etabli chiffre a chiffre : perimetres de jeux differents (10
jeux, tous `couvert-valide`, contre 17 ou 18 jeux, le catalogue entier), population differente (296
items exhaustifs contre un plancher echantillonne), etat mesure different (le plus charge, trois
echecs puis revelation, contre un seul echec), metrique differente (le bloc `.message-jeu` entier
contre le verdict et l'indice mesures separement). **Les deux chiffres ne se somment pas.**

Consequence pour le planning de s21 : corriger `REP-8` (indice et verdict visibles apres un echec)
ne ferme pas par construction l'obstacle des 95 sur 296 (bloc entier visible apres trois echecs et
revelation), meme si la cause de forme est la meme. Je n'affirme ni sa fermeture ni son ouverture
persistante sur la seule foi de la correction de `REP-8` : une remesure dediee, par `theo-archi`,
sur l'etat corrige et sur son propre perimetre (10 jeux, 296 items, etat le plus charge), reste due
avant tout statut. Je ne prononce toujours aucun statut de couverture sur ces items, comme deja
etabli a `DEC-0050`.

## 4. Instructions pour le planning de s21 - perimetre volume, classe par gain de couverture

Methode : chaque fichier cite ci-dessous a ete lu integralement ou en tete representative (au moins
un fichier par famille de `contenu-repris/`, 14 fichiers lus au total) et confronte ligne par ligne
a `squad/programme/MATRICE-couverture.md`, HEAD `478e04d`, le 2026-08-27. Ce n'est pas un inventaire
exhaustif des 61 fichiers : c'est une classification fondee sur un echantillon verifie par famille,
a completer par `sofia-sm` et les developpeurs si d'autres fichiers de la meme famille sont
retenus.

**Contrainte de forme rappelee avant toute proposition** : la seule mecanique implementee est
`bandit-manchot` (enonce, `bonneReponse`, `distracteurs`, `indice` optionnel - voir
`adapters/adapter-content/src/main/resources/contenu/cp-nature-mot.yaml` et `cp-le-bon-son.yaml`
pour les deux formes deja en usage, "explicit" et "pool"). Je ne propose aucune mecanique neuve :
seules les familles de la reserve dont la donnee se ramene a "un enonce, une bonne reponse, des
distracteurs" se transposent sans denaturer la competence visee.

### Niveau 1 - transposition directe, competence non couverte identifiee, format deja proche de `bandit-manchot`

- **`contenu-repris/syllabes/cp-syllabes.yml`** (mot -> nombre de syllabes, mode "pool" deja
  present dans le fichier) -> francais CP, "Denombrer les syllabes orales d'un mot (segmentation
  syllabique)" (`squad/programme/francais/competences-cp.md`, `non couvert`). A noter pour le
  planning : le domaine "Langage oral et conscience phonologique" (3 lignes CP) est aujourd'hui a
  zero ligne couverte ; ce paquet ouvrirait la premiere.
- **`contenu-repris/quiz/cp-comparer.yml`** (comparaisons long/court, lourd/leger, grand/petit,
  sans instrument ni unite) -> mathematiques CP, "Comparer des longueurs, des masses, des
  contenances (sans instrument gradue)" (`non couvert`). Correspondance directe au libelle de la
  competence, contrairement a `contenu-repris/quiz/cp-longueurs.yml` (meme domaine) qui mesure avec
  une regle et des unites (cm, m) - au verbe meme que la competence exclut ("sans instrument
  gradue") : ce second fichier n'est pas un candidat sans reformulation lourde. `cp-comparer.yml`
  couvre longueur et masse ; aucun item de contenance n'y figure et deux items portent sur la
  comparaison de deux nombres (competence deja `en cours` par ailleurs) - un tri retire ces deux
  items et un ajout de matiere sur la contenance restent necessaires avant audit, ce n'est pas une
  reprise a l'identique.
- **`contenu-repris/textes-trous/cp-mot-manquant.yml`** (phrase a trou, `question`/`bonne`/
  `distracteurs`, format quasi identique au schema `bandit-manchot`) -> francais CP, "Utiliser des
  mots precis pour designer des objets, actions, sensations du quotidien" (`non couvert`).
- **`contenu-repris/quiz/cm1-comprehension.yml`** (texte court integre a l'enonce, question,
  `bonne`/`distracteurs`) -> francais CM1, "Comprendre un texte narratif ou documentaire (questions
  explicites et implicites, inferences simples)" (`non couvert`). **Point de vigilance a router a
  `theo-archi` et `ines-design` avant tout audit** : l'enonce y integre un texte de deux phrases
  avant la question, plus long que tout enonce du catalogue actuel - a mesurer contre le budget
  vertical au coeur meme de `REP-8` avant d'ouvrir cette ligne, pour ne pas rouvrir le defaut que ce
  sprint ferme en premier.

### Niveau 2 - transposition possible mais sous condition pedagogique a lever d'abord, ou format a verifier plus finement

- **`contenu-repris/mots/cp-premiere-lettre.yml`** (mot -> premiere lettre, mode "pool", format
  identique a `cp-le-bon-son.yaml` deja en usage) -> candidat plausible sur francais CP,
  "Identifier un son (phoneme) dans un mot" (`non couvert`) ou le principe alphabetique, mais
  l'exactitude de cette correspondance (lettre initiale ecrite, contre phoneme entendu) est un
  jugement pedagogique que je ne tranche pas : arbitrage prealable de `nadia-prof` requis avant
  affectation, meme mecanisme que `NAT-1` a `NAT-8` et `DEN-1` a `DEN-8`.
- **`contenu-repris/fractions/cm1-compare.yml`** (deux fractions, signe `<`/`>`/`=`) -> candidat sur
  mathematiques CM1, "Notion de fraction simple (unites fractionnaires, fractions inferieures a 1)
  et reperage sur une droite numerique" (`non couvert`), mais la competence nomme explicitement un
  "reperage sur une droite numerique" que ce fichier ne couvre pas : risque de repeter le motif de
  couverture partielle deja sanctionne sur ce produit (additions "jusqu'a 10 (puis 20)", homophones
  "principaux", conjugaison "un temps sur trois", monnaie CP "reconnaitre contre utiliser"), a
  soumettre a `nadia-prof` en arbitrage prealable avant tout developpement, pas a decider par le
  contenu seul.
- **`contenu-repris/associer/cp-chiffre-mot.yml`** (chiffre <-> mot-nombre, 0 a 10, un seul sens) ->
  thematiquement proche de mathematiques CP, "Denombrer, lire, ecrire et decomposer les nombres
  jusqu'a 100", **deja arbitree** par `nadia-prof` sous huit conditions non negociables (`DEN-1` a
  `DEN-8`, `squad/conformite/ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`,
  section 1.2). Verifie contre ces conditions : ce fichier ne les tient pas (s'arrete a 10 au lieu
  de 100, un seul sens alors que `DEN-3` exige les deux, aucun item de decomposition pour `DEN-4`,
  aucun nombre de la tranche 60-99 pour `DEN-6`). Ce n'est pas une reprise de matiere, c'est une
  redaction quasi entiere a refaire sous huit conditions deja ecrites. **A signaler pour le
  planning** : meme parfaitement tenu, `DEN-2` interdit a cette ligne de passer `couvert-valide` -
  elle resterait `en cours`. Priorite basse pour un sprint classe par gain de couverture.

### Niveau 3 - la reserve exige une mecanique que le produit n'a pas ; je ne la propose pas

- **`contenu-repris/puzzle/*`** (4 fichiers) : reconstitution de mot lettre par lettre - mecanique
  de reordonnancement, pas un choix entre distracteurs.
- **`contenu-repris/memory/*`** (6 fichiers) : jeu de paires - mecanique d'appariement par memoire.
- **`contenu-repris/phrase/*`** (2 fichiers) : remise en ordre de mots dans une phrase - le fichier
  `phrase/cp-phrases.yml` se declare lui-meme "declinaison mots de la mecanique puzzle (s25)" de
  `main` : meme famille que ci-dessus.
- **`contenu-repris/associer/*`** (11 fichiers, hors `cp-chiffre-mot.yml` traite ci-dessus) :
  appariement objet-cible. Une transposition en `bandit-manchot` existe deja comme precedent
  (`cm1-synonymes.yml`, matiere reprise et **reformulee** en phrases completes avec mot cible entre
  guillemets, condition `SYN-1` de l'arbitrage prealable de s17) mais elle change la tache posee a
  l'enfant (choisir parmi des distracteurs dans une phrase, plutot qu'apparier deux listes) : ce
  n'est pas une reprise de matiere au sens strict de `contenu-repris/README.md`, c'est une
  reecriture guidee par un precedent deja accepte. Les fichiers restants de cette famille n'ont, de
  plus, pour la plupart aucune ligne de referentiel actuelle a laquelle s'accrocher (formes
  geometriques deja refusees en glyphe, quadrilateres deja `non couvrable en l'etat`, monnaie en
  centimes hors du libelle CM1, masculin/feminin et majuscule/minuscule sans ligne dediee).

### Niveau 4 - aucun referentiel n'existe pour la matiere ; hors perimetre, pas une decision de ce document

`squad/programme/` ne contient que quatre referentiels (mathematiques CP/CM1, francais CP/CM1,
verifie ce jour par `find squad/programme -type f -name "competences-*.md"`). La reserve porte des
fichiers d'histoire, geographie, sciences, EMC et anglais (`contenu-repris/quiz/cm1-histoire.yml`,
`cm1-geographie.yml`, `cm1-sciences.yml`, `cm1-solides.yml`, `cm1-vrai-faux-sciences.yml`,
`cm1-emc.yml`, `cp-emc.yml`, `cp-questionner-le-monde.yml`, `frise/cm1-histoire.yml`,
`mots/cm1-anglais-lexique.yml`, `memory/memory-anglais-cm1.yml`,
`associer/anglais-associer-cm1.yml`, entre autres) - **aucun n'a de ligne a laquelle s'accrocher
aujourd'hui.** Ouvrir un de ces fichiers exigerait d'abord un referentiel neuf, une decision d'une
toute autre nature que le choix d'un paquet a s21, hors du perimetre de cette decision. Je ne la
propose pas ; je la nomme pour que le silence sur ces 24 fichiers de `quiz/` (sur 24 au total) ne
soit pas lu comme un oubli.

Sont egalement ecartees du volume de s21, deja couvertes par un jeu existant et donc sans gain de
couverture : `contenu-repris/associer/cm1-synonymes.yml` (redondant avec
`bandit-manchot-synonymes-cm1`), `contenu-repris/mots/cm1-homophones.yml` (redondant avec
`bandit-manchot-homophones-cm1`), `contenu-repris/quiz/cm1-perimetre.yml` et
`associer/cm1-mesures.yml` (redondants avec `bandit-manchot-perimetre-cm1` et
`bandit-manchot-conversions-unites-cm1`), `contenu-repris/cm1-tables-multiplication.yml` (redondant
avec `bandit-manchot-tables-multiplication-cm1`), `contenu-repris/mots/cp-le-bon-son.yml`
(deja la source de `bandit-manchot-le-bon-son-cp`, `en cours`).

## 5. Le goulot reel : la porte pedagogique, pas le developpement

A s20, deux paquets ont chacun ete livres en un lot par `marc-dev` et `lea-dev` (`RAPPORT.md` de
s20) : le developpement n'a pas ete le facteur limitant. Ce qui a consomme le sprint et produit un
refus : l'audit de `nadia-prof`, qui joue chaque item.

**Chiffre, avec sa methode et son perimetre, cite plutot qu'estime** : sur s20 (`RAPPORT.md`,
tableau de consommation de tokens, fenetre du 2026-08-25 15:00 au 2026-08-27 09:15 UTC),
`nadia-prof` a consomme 372 tours et 227 411 tokens de sortie pour auditer **deux** paquets, dont
**un seul sans cycle de refus** (`bandit-manchot-nature-mot-cp`, reconfirmation sur une reserve deja
arbitree au prealable) et l'autre avec un cycle complet REFUSE -> correction -> reaudit dans le
meme sprint (`bandit-manchot-reperes-cm1`, `DEC-0049` puis `DEC-0050`). A s19 (`RAPPORT.md` de s19,
ligne 5 et 54-57), quatre paquets ont ete audites dans le sprint : trois valides sans reserve
bloquante, dont un avec son propre cycle refus -> correctif -> reaudition sur cinq motifs ; le
quatrieme (`cp-nature-mot`) valide avec une reserve bloquante qui n'a ferme qu'au sprint suivant.

**Ce que ces deux sprints disent ensemble, et le nombre que j'en tire pour le planning** : sur les
six paquets audites en deux sprints, trois ont exige un cycle de refus dans le sprint meme. Le
debit de `nadia-prof` n'est pas de "deux a quatre paquets par sprint" au sens ou ce nombre serait
garanti sans reprise : c'est plutot **deux a trois paquets qui atteignent un Done pedagogique dans
le sprint**, dont au moins un qui consomme un cycle de refus complet en plus de son premier audit.
Je tiens donc **trois paquets neufs comme la borne haute auditable a s21** sans compter un aleas de
refus supplementaire, et **deux** comme un compte plus sur si l'un d'eux suit le motif habituel
(refus puis correction). `REP-8` en tete de sprint reduit encore ce budget avant tout paquet neuf :
ses criteres d'acceptation portent une part pedagogique non automatisable (comprehension du
defilement par un enfant de neuf ans) que `nadia-prof` devra elle aussi juger, meme si son cout est
plus proche d'une verification transverse que d'un audit de contenu neuf ligne par ligne.

**Consequence pour le choix parmi les candidats du niveau 1** : trois candidats de niveau 1 y
figurent aujourd'hui (`cp-syllabes`, `cp-comparer` une fois trie et complete sur la contenance,
`cp-mot-manquant`), plus un quatrieme sous condition de forme (`cm1-comprehension`). Retenir les
trois premiers epuise deja la borne haute que je tiens pour s21 sans aucun aleas de refus ; les deux
candidats de niveau 2 restent des arbitrages prealables a poser des maintenant aupres de
`nadia-prof`, pour ne pas perdre un sprint de plus a les instruire une fois le developpement pret,
comme cela s'est deja produit sur `NAT-5` et sur `DEN-1` a `DEN-8`.

## Ce que je route

- **`sofia-sm`** : affectation de `REP-8` en tete de planning de s21, perimetre exact du catalogue
  a fixer (17 ou 18 jeux) ; choix, parmi les candidats de niveau 1 et 2 ci-dessus, du nombre de
  paquets neufs a instruire en story a s21, dans la borne de deux a trois paquets sans aleas de
  refus que je tiens pour ce sprint.
- **`theo-archi`** et **`ines-design`** : `REP-8`, cause de forme (budget vertical du cabinet,
  taille de `.proposition`) - rappel deja route par `DEC-0050`, non modifie ici ; et, si
  `cm1-comprehension` est retenu, une mesure prealable de la longueur d'enonce contre ce meme budget
  avant tout audit.
- **`nadia-prof`** : arbitrage prealable, si `sofia-sm` retient l'un des deux candidats de niveau 2
  (premiere lettre -> phoneme ; comparaison de fractions sans droite numerique), avant tout
  developpement.
- **`marc-dev`** et **`lea-dev`** (via `sofia-sm`) : reprise de matiere pour les paquets de niveau 1
  retenus, sous la forme du schema `bandit-manchot` deja en usage (`schema: 1`, `type: question`),
  jamais sous la structure d'origine de `contenu-repris/`.

## Ce que je ne fais pas

- Je ne fixe pas la liste finale des paquets de s21 : c'est une decision de capacite qui appartient
  a `sofia-sm`, informee par ce classement.
- Je ne prononce aucun statut de couverture, ni sur `REP-8` ni sur l'obstacle des 95 sur 296 :
  aucun des deux n'a de Done pedagogique a ce jour.
- Je ne propose aucune mecanique neuve : les familles de la reserve qui en exigeraient une (niveau
  3) sont nommees, pas instruites.
- Je ne cree pas de referentiel neuf pour couvrir les fichiers d'histoire, geographie, sciences,
  EMC ou anglais de la reserve (niveau 4) : c'est hors de mon perimetre d'aujourd'hui.
- Je ne cree ni le repertoire de sprint ni `planning.md` : `sofia-sm` les ecrit.

## Impact

- `squad/decisions/produit/DEC-0051-...md` (ce fichier) : cree.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0051` ajoutee.
- `docs/backlog/backlog.md` : entree `REP-8` mise a jour (affectee en tete de s21, criteres
  inchanges) ; nouvelle section "Backlog - perimetre volume s21" ajoutee en fin de fichier avec le
  classement du present document.
- Aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/` n'est modifie par cette
  decision. `squad/programme/MATRICE-couverture.md` n'est pas modifiee : aucune ligne ne change de
  statut aujourd'hui.

## Amendement du 2026-08-27 (s21, en cours) - la population des chiffres cites est un echantillon, jamais le catalogue entier ; l'arbitrage tient quand meme

Rien ci-dessus n'est reecrit. Cet amendement dit sur quelle population portent les chiffres deja
ecrits dans ce document, parce que personne ne l'avait dit - `nadia-prof` la premiere, contre sa
propre mesure du matin (`round-two` a `3cfe9e3`, 2026-08-27, message de commit, section "Correction
plus grave contre moi").

**Ce que `nadia-prof` a etabli, avec sa methode.** `squad/conformite/releves-s21/s21-pli-du-
catalogue-744x551.json` porte `parties: 1` et `itemsVus: 10` (7 pour un jeu combinatoire) pour
chacun des 17 jeux qu'il balaie. Verifie ce jour par lecture directe du fichier : somme des
`itemsVus`, **167**. Le catalogue compte **428** items (somme des `items` de chaque paquet YAML
servi, 18 fichiers, verifie ce jour). **167 sur 428, soit 39 %.** Sur les quatre paquets qu'elle a
joues **entiers**, item par item, avec l'outil neuf `squad/outils/nadia-prof/auditer-un-paquet-
entier.py` (memes commit et date) : **92 items exhaustifs, 42 sous le pli avant les leviers de
forme de `S21-2`, 40 encore apres**.

**Ce qui, dans ce document-ci, portait deja cette meme population sans le nommer aussi nettement.**
La section "Option ecartee" du premier paragraphe de la Decision cite *"huit jeux sur dix-sept et
quarante-trois items sur cent cinquante-sept du catalogue servi"* (`DEC-0050`, section 4). `DEC-
0050` qualifiait deja ce chiffre de **"plancher"**, tirage aleatoire, 7 a 10 items vus par jeu - je
ne le decouvre pas, je le rends explicite ici, parce que la formule reprise dans ce document,
*"cent cinquante-sept du catalogue servi"*, ne porte plus elle-meme ce mot en la recopiant, et
c'est exactement la coupure que `nadia-prof` a mesuree le 2026-08-27 sur cinq documents distincts,
le sien compris : un chiffre d'echantillon relu comme un compte de catalogue. **157 et 167 sont deux
mesures du meme type (plancher, une partie par jeu), prises a deux dates differentes (avant et apres
le retour de `cm1-reperes`), et aucune des deux n'est un inventaire.**

**Ce que je ne fais pas ici.** Je ne recalcule aucun pourcentage catalogue-entier a partir des 92
items exhaustifs : quatre paquets sur dix-huit ne permettent d'en tirer aucune moyenne fiable, et
`nadia-prof` le dit elle-meme en refusant de reenoncer un chiffre a cette echelle faute d'avoir joue
les treize autres paquets. Ce document ne le fait pas non plus.

**Ce que cela change, ou non, a l'arbitrage de l'utilisatrice.** Son arbitrage ("fermer le pli
d'abord, puis le volume") repose, dans ce document, sur un motif qui ne depend pas de la taille
exacte de la population mesuree : les jeux neufs heriteraient du meme defaut de forme (budget
vertical du cabinet, taille de `.proposition`) si le volume s'ouvrait avant que ce defaut soit
traite, quel que soit le pourcentage du catalogue qu'il touche aujourd'hui. Le chiffre servait a
motiver l'urgence, pas la nature de la decision. Sur la seule population que `nadia-prof` a
mesuree en entier, le taux est **plus defavorable** que le plancher cite ici (42 sur 92, 45,7 %,
contre 43 sur 157, 27,4 %) : la correction va dans le sens qui renforce le motif retenu, pas dans
celui qui l'affaiblirait. **Je ne repose donc pas la question a l'utilisatrice** : rien dans la
population reelle, telle qu'elle est connue a ce jour sur quatre paquets sur dix-huit, ne contredit
le prix qu'elle a explicitement accepte de payer. Si les treize paquets restants, une fois mesures
en entier, renversaient ce sens, la question redeviendrait sienne - ce n'est pas le cas aujourd'hui.

## Amendement du 2026-08-28 (troisieme, `camille-po`) - la population reelle du catalogue est 478
## items, pas 428 ; le catalogue entier est mesure, ce n'est plus un plancher

**Ce que je corrige contre moi-meme, avant que quiconque d'autre n'ait a le faire.** Le second
amendement ci-dessus, ecrit le 2026-08-27 au matin, porte la phrase *"Le catalogue compte 428
items... 18 fichiers, verifie ce jour"*. C'etait exact au moment ou je l'ai ecrite : le catalogue
comptait alors 18 fichiers de contenu, avant que `S21-9`, `S21-10` et `S21-11` ne livrent leurs
trois paquets neufs. **Ce n'est plus l'etat du catalogue aujourd'hui**, et une phrase qui dit "le
catalogue compte" au present, sans la date qui la borne, vieillit mal - exactement le defaut
qu'`ADR-0061` et `squad/README.md` (section 4, amendement hors sprint du 2026-08-24) nomment deja.
Je ne reecris pas la phrase d'origine : elle reste exacte pour l'etat qu'elle decrivait. J'ecris
ici l'etat d'aujourd'hui.

**Verifie ce jour (2026-08-28), contre la source primaire et non repris d'un autre document** :
`ls adapters/adapter-content/src/main/resources/contenu/*.yaml` compte **21 fichiers** (18 d'avant
s21, plus `cp-syllabes.yaml`, `cp-mot-manquant.yaml`, `cp-comparer.yaml`) ; `grep -c "^  - id:"` sur
`catalogue.yaml` compte **21 entrees**, correspondance un pour un maintenue. La somme du nombre
d'occurrences du champ `enonce:` sur les 21 fichiers vaut **478** - `bandit-manchot-additions-cp`
(paquet `additions-bornees` genere, sans item statique) y compte pour 0, conforme a ce que
`nadia-prof` ecrit (*"20 jeux enumeres sur 21"*). **478 = 428 (etat du 2026-08-27 matin) + 50 (16 +
16 + 18, les trois paquets neufs)** : les deux chiffres ne se contredisent pas, ils decrivent deux
etats successifs du meme catalogue.

**Ce que `nadia-prof` a mesure sur cette population, avec sa methode et sa date, verifie contre sa
source primaire (`squad/conformite/FICHE-bandit-manchot-comparer-cp.md`, section 7, et
`avis-nadia-done-pedagogique-s21.md`, `s21-nadia-2`, 2026-08-27)** : le **catalogue entier**, item
par item, 744 x 551, **478 items sur 478** - **ce n'est plus un plancher echantillonne comme 167 sur
428 ou 157 sur 428 (`DEC-0050`) l'etaient : c'est un compte.**

| Etat, meme arbre, sans mutation de fichier | Indice sous le pli | Verdict sous le pli |
|---|---|---|
| **Servi** (arbre fusionne de s21) | **64** sur 478 | **10** sur 478 |
| **Avant s21** (trois declarations d'avant reinjectees) | **114** sur 478 | **15** sur 478 |
| **Ecart ferme** | **50** | **5** |

**Ce que ce chiffre ne fait pas, dans les mots de `nadia-prof` que je reprends sans les
affaiblir.** Les trois declarations retirees pour obtenir la colonne "avant s21" (`.score` a
`0.25rem 0`, `.proposition` a `0.5rem 1.25rem`, les deux sorties a `0.5rem 1.1rem`, toutes
reposees a leur valeur d'avant s21 ensemble) melangent l'effet de `S21-2` (`marc-dev`) et celui de
la descente de la sortie sous `SOR-1` a `SOR-4` (`ines-design`, `nadia-prof`). **50 est donc l'effet
du travail vertical de s21 dans son entier, pas celui de `S21-2` seul, et il ne remplace pas le 26**
du second amendement ci-dessus, qui portait sur `S21-2` seul et sur un echantillon de 167 items sur
428. Les deux chiffres restent chacun exacts pour ce qu'ils mesurent ; je ne les substitue pas l'un
a l'autre.

**Ce que la mesure du catalogue entier apprend, et qui va au perimetre volume de s21 (section 4
ci-dessus, inchangee)** : **le pli est ferme sur les 9 jeux CP enumeres (162 items, 0 sous le pli,
contre 22 avant s21)**. Les **64 items qui restent sous le pli sont tous CM1**, sur 316. Quatre jeux
en portent 58 sur 64 : `bandit-manchot-accord-sujet-verbe-cm1` (20 sur 21),
`bandit-manchot-reperes-cm1` (16 sur 16), `bandit-manchot-grands-nombres-cm1` (14 sur 28),
`bandit-manchot-accords-groupe-nominal-cm1` (8 sur 24). Les deux jeux ou s21 a le plus ferme sont
`bandit-manchot-problemes-additifs-cp` (22 sur 22 avant s21, 0 sur 22 aujourd'hui) et
`bandit-manchot-perimetre-cm1` (20 sur 22 avant, 1 sur 22 aujourd'hui), qui a eux deux portent 41
des 50 items fermes.

**Ce que je ne fais pas ici.** Je ne prononce aucun statut de couverture sur ces items. Je ne rouvre
pas l'arbitrage de la section "Decision" ci-dessus : le motif retenu par l'utilisatrice (les jeux
neufs ne doivent pas naitre avec le defaut de pli) est confirme, pas affaibli, par une mesure plus
complete que celle dont elle disposait. **Ce que j'en tire pour le planning suivant, sans le
trancher ici** : le pli n'est plus un defaut de catalogue generique - il est concentre sur quatre
jeux CM1 nommement identifies, ce qui change la nature du prochain geste (quatre paquets a corriger,
pas une regle de forme a generaliser). Route a `sofia-sm` pour le planning du sprint suivant.

### Impact de cet amendement

- `squad/decisions/produit/DEC-0051-...md` (ce fichier) : cette section ajoutee, rien reecrit
  au-dessus.
- `squad/decisions/produit/DEC-0052-...md` : renvoie ici pour la population du catalogue plutot que
  de la recopier.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0051` mise a jour.
- Aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/` n'est modifie.
  `squad/programme/MATRICE-couverture.md` n'est pas modifiee par cet amendement : aucune ligne ne
  bouge sur la foi de ce chiffre.
