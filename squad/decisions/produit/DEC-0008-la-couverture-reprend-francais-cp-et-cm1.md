# DEC-0008 - La couverture du programme reprend sur demande de l'utilisatrice : francais CP et CM1 entrent au backlog

- **Type** : perimetre produit, choix de matiere et de competences
- **Date** : 2026-08-06
- **Auteur** : camille-po
- **Sprint** : s04 (ouverture)
- **Competence programme visee** : francais, CP (cycle 2), domaine "Lecture et comprehension de
  l'ecrit" - "Associer les graphemes aux phonemes (voyelles, consonnes simples, puis digraphes
  ch, ou, on...)" ; francais, CM1 (cycle 3), domaine "Orthographe et grammaire" - "Distinguer et
  utiliser correctement les principaux homophones grammaticaux (et/est, son/sont, a/a...)"
  (`squad/programme/francais/competences-cp.md`, `competences-cm1.md`).
- **Statut** : Acceptee

## Contexte

L'utilisatrice renverse la priorisation posee par `DEC-0007` : "je ne comprends pas pourquoi on
n'a pas de nouveaux jeux." C'est elle qui decide du produit ; cette demande prime sur mon
arbitrage precedent, et je ne le redefends pas ici.

Le constat qui motive cette decision, pas seulement la demande elle-meme : apres quatre sprints
(s00 a s03), le produit compte **un seul jeu, une seule competence**, en additions CP. `DEC-0007`
avait un motif defendable (une condition ecrite de `nadia-prof`, deja reportee une fois, qu'ouvrir
une deuxieme ligne sans fermer la premiere aggraverait), mais le resultat net est que **le
principe directeur du produit n'a jamais ete mis a l'epreuve**. `CLAUDE.md` et `ADR-0004`
affirment qu'une mecanique de jeu sert n'importe quelle matiere sans modification de code ; avec
un seul contenu sur une seule matiere depuis quatre sprints, rien ne le demontre. Le CM1 n'a par
ailleurs **aucun** contenu a ce jour, alors qu'il est dans le perimetre du produit au meme titre
que le CP.

### Ce qui devient de la ligne mathematiques CP et de sa condition

Je ne fais disparaitre aucune condition ecrite par une autorite pedagogique. `nadia-prof` a pose
en s01 une condition ecrite pour que la ligne mathematiques CP passe a `couvert-valide` (la
repartition des sommes) ; s03 l'a instruite en story (S3-1, S3-2) et l'increment corrige est
fusionne sur `round-two`, mais **le sprint s03 n'est pas encore cloture** : `nadia-prof` n'a pas
encore audite l'increment corrige, et je ne prononce jamais ce verdict a sa place. La ligne
mathematiques CP reste donc `en cours`, exactement comme `DEC-0007` l'a laissee : **cette
condition n'est ni tenue ni levee par cette decision, elle est simplement inchangee et en
attente de l'audit de cloture de s03.**

Cette decision **ne remplace pas** `DEC-0007` dans son ensemble. Ses points 2 a 8 (repartition des
sommes, declenchement de l'indice, appui rate, largeur d'ecran, titre accentue, question de
process, ecran de catalogue vide) restent des decisions en vigueur, deja executees ou en cours
d'audit : rien dans ce que demande l'utilisatrice ne les remet en cause. **Seul le point 1 de
`DEC-0007`** ("s03 ne cree aucun contenu pour une matiere ou une competence nouvelle") est
**remplace** par la presente decision, a partir de maintenant : la couverture du programme reprend
et une nouvelle matiere entre au backlog, en parallele de l'audit en attente sur mathematiques CP,
pas a sa place.

## Inventaire de `contenu-repris/` avant choix

`contenu-repris/README.md` le dit explicitement : c'est une reserve de matiere premiere, pas un
cahier des charges, pas du contenu cable, pas valide pour `round-two`. J'ai lu les 61 fichiers
avant de choisir plutot que de deviner. Trois faits structurent le choix :

1. **Seules deux matieres ont un referentiel** sous `squad/programme/` : mathematiques et francais,
   chacun en CP et en CM1. `contenu-repris/` contient aussi de l'anglais, des sciences, de
   l'histoire-geographie et de l'EMC (par exemple `anglais-associer-cm1.yml`, `cm1-sciences.yml`,
   `cm1-histoire.yml`, `cm1-geographie.yml`, `cm1-emc.yml`, `cp-emc.yml`,
   `cp-questionner-le-monde.yml`), mais **ouvrir une de ces matieres suppose d'abord d'ecrire son
   referentiel de competences** - un prealable que cette decision ne prend pas, pour ne pas fabriquer
   une ligne de matrice sans reference programme derriere. Cela reste l'intention deja notee au bas
   du backlog ("etendre le referentiel a d'autres matieres [...] ce qui suppose au prealable
   d'ecrire leurs referentiels de competences").
2. **Une seule forme d'item est jouable aujourd'hui par le domaine** : `Question` (`enonce`,
   `bonneReponse`, `distracteurs`), la seule permission de l'interface scellee `Item` (`ADR-0004`),
   consommee par la seule mecanique existante, le bandit manchot. Sur les neuf familles de
   `contenu-repris/` (`associer`, `quiz`, `memory`, `mots`, `phrase`, `puzzle`, `frise`,
   `fractions`, `syllabes`, `textes-trous`), seules `quiz`, `mots` et `textes-trous` ecrivent deja
   une question a reponse unique avec des distracteurs textuels - la forme qui correspond a
   `Question` sans qu'une nouvelle forme d'item ou une nouvelle mecanique soit necessaire.
   `associer`, `memory`, `puzzle`, `frise` et `fractions` (paires, associations, sequences) ne s'y
   pretent pas : ouvrir l'une de ces familles demanderait une deuxieme mecanique, question que
   cette decision ne tranche pas et que je remonte a `sofia-sm`/`theo-archi` si elle devient
   pressante (voir le backlog non detaille, deja explicite sur ce point depuis s03).
3. **Dans les familles compatibles, deux fichiers de francais correspondent, mot pour mot, a une
   ligne du referentiel existant, sans transformation de leur matiere** :
   `mots/cm1-homophones.yml` (couples `a/a`, `et/est`, `on/ont`, `son/sont`, `ou/ou`) reprend
   exactement les exemples cites par la competence CM1 "Distinguer et utiliser correctement les
   principaux homophones grammaticaux (et/est, son/sont, a/a...)" ; `mots/cp-le-bon-son.yml`
   (24 mots, phonemes `[an]`, `[on]`, `[in]`, `[ou]`, `[oi]`, `[ch]`) recouvre precisement les
   digraphes cites en exemple par la competence CP "Associer les graphemes aux phonemes [...] puis
   digraphes ch, ou, on...". Aucun autre fichier de la reserve n'offre une correspondance aussi
   directe a une ligne de referentiel deja ecrite.

**Ce qui est reutilisable en l'etat** (au sens de la matiere : enonces, reponses, distracteurs
deja ecrits, aucune reecriture de contenu) : ces deux fichiers. **Ce qui demande une reprise avant
d'etre reutilisable** : leur enveloppe. Aucun des 61 fichiers de `contenu-repris/` n'est charge par
l'application telle quelle - `ADR-0007` l'a deja tranche pour tout le dossier : reprendre un
paquet demande de le reecrire dans le schema commun (`schema`, `id`, `type: question`,
`descripteur`, `items` avec `enonce`/`bonneReponse`/`distracteurs`). C'est du travail de contenu,
sans surprise technique, deja annonce par `ADR-0007`. **Ce qui ne sert a rien pour cette
decision** : les familles `associer`, `memory`, `puzzle`, `frise`, `fractions`, `syllabes`
(comptage de syllabes, distracteurs numeriques calcules a la volee dans `main`, pas de forme
explicite ici) et les six matieres sans referentiel (anglais, sciences, histoire-geographie, EMC,
questionner le monde) - reserve reelle pour des sprints suivants, mais hors de portee de la
mecanique et/ou du referentiel disponibles aujourd'hui.

Aucun de ces deux fichiers n'est valide pedagogiquement pour `round-two`. Une fois reecrits dans
le schema commun, ils repassent devant `nadia-prof` comme un contenu neuf, exactement comme tout
autre contenu de la reserve (`contenu-repris/README.md`).

## Decision

### 1. Deux jeux entrent au backlog, un par niveau, tous deux en francais

- **`bandit-manchot-le-bon-son-cp`** : francais, CP, competence "Associer les graphemes aux
  phonemes (voyelles, consonnes simples, puis digraphes ch, ou, on...)", matiere premiere
  `contenu-repris/mots/cp-le-bon-son.yml`.
- **`bandit-manchot-homophones-cm1`** : francais, CM1, competence "Distinguer et utiliser
  correctement les principaux homophones grammaticaux (et/est, son/sont, a/a...)", matiere
  premiere `contenu-repris/mots/cm1-homophones.yml`.

Detail des criteres d'acceptation : stories S4-1 (CM1) et S4-2 (CP) de `docs/backlog/backlog.md`.

### 2. Pourquoi le francais, et pourquoi ces deux competences precisement

Trois criteres, aucun d'eux tranche seul :

- **Le test du decouplage.** Le francais est la matiere la plus eloignee de l'arithmetique parmi
  celles qui ont deja un referentiel : aucune notion de borne numerique, aucun tirage combinatoire,
  un vocabulaire de contenu entierement different (mots, phonemes, homophones plutot qu'operandes
  et sommes). Si le bandit manchot joue ces deux contenus sans qu'une seule ligne de
  `domain/mechanic` change, c'est la premiere preuve reelle que le principe directeur du produit
  tient au-dela d'un seul contenu. Si ca casse, c'est un signal majeur (point 5 ci-dessous), pas un
  detail d'implementation.
- **Ce que le programme rend le plus attendu.** Pour un CP, apprendre a lire - associer une lettre
  ou un groupe de lettres au son qu'il fait - est le coeur du programme, avant meme le calcul. Pour
  un CM1, ne plus confondre les homophones grammaticaux les plus frequents (a/a, et/est, son/sont)
  est une attente explicite et frequemment citee des programmes de cycle 3 en orthographe.
- **Le trou CM1.** Quatre sprints, zero ligne de matrice CM1. `bandit-manchot-homophones-cm1` est
  le premier contenu du produit pour ce niveau, quelle que soit la matiere.

Je n'ai pas retenu les autres candidats "en l'etat" identifies dans l'inventaire
(`textes-trous/cm1-accords.yml`, `textes-trous/cm1-conjugaison.yml`) pour cette decision : ils
correspondent chacun a une **partie seulement** de leur competence de referentiel (l'accord de
l'adjectif est une partie de "accorder en genre et en nombre dans le groupe nominal", le present
de l'indicatif est une partie de "conjuguer [...] au present, futur et imparfait"), alors que les
deux retenus correspondent integralement aux exemples cites par leur ligne de referentiel. Ils
restent une reserve directe pour le sprint suivant, sans nouvel arbitrage de matiere a faire.

### 3. Ce que cette decision ne fait pas

- Elle ne fait passer aucune ligne de matrice a `couvert-valide` : ce statut n'appartient qu'a
  `nadia-prof`, apres audit reel, pour ces deux nouveaux jeux comme pour mathematiques CP.
- Elle ne rouvre pas le debat tranche par `DEC-0007` points 2 a 8 : ces corrections restent en
  vigueur et suivent leur cours (audit de cloture de s03).
- Elle ne tranche aucune question technique. Si le bandit manchot ne peut pas jouer l'un de ces
  deux contenus sans modification de `domain/mechanic` ou de l'interface scellee `Item`, c'est un
  constat majeur a remonter a `sofia-sm`, qui routera vers `theo-archi` - je ne le presuppose pas
  ici, l'avis de faisabilite de `theo-archi` au planning le confirmera ou l'infirmera.
- Elle ne tranche rien sur la bibliotheque d'interface tierce dont `theo-archi` acte l'entree en
  parallele. Les criteres des deux stories ne supposent aucune structure de gabarit ou de classe
  CSS particuliere au-dela de ce que le bandit manchot rend deja pour `bandit-manchot-additions-cp`.

## Impact

- `docs/backlog/backlog.md`, nouvelle section "Sprint 4" : stories S4-1 (homophones CM1) et S4-2
  (le bon son CP), toutes deux des stories de **contenu**, aucune de **code de mecanique** (voir
  section suivante). La liste "Backlog - sprints suivants" est mise a jour : l'intention "ouvrir
  la premiere competence de francais" devient une story concrete, elle est retiree de la liste
  d'intentions non detaillees.
- `squad/programme/MATRICE-couverture.md` : deux lignes passent de `non couvert` a `en cours`
  (francais CP - graphemes/phonemes ; francais CM1 - homophones grammaticaux), avec jeu et
  mecanique renseignes. La synthese chiffree est recalculee.
- `squad/decisions/produit/DEC-index.md` : ligne DEC-0008 ajoutee ; amendement note sur la ligne
  DEC-0007 (point 1 remplace, points 2 a 8 inchanges).
- `squad/validation/FILE-ATTENTE.md` n'est pas modifie par cette decision : aucun increment
  n'atteint encore le Done technique. Ces deux jeux y entreront, comme tout increment, au moment
  ou leur Done technique sera atteint et qu'ils attendront l'audit de `nadia-prof`.

## Ce que le code doit demontrer, explicitement

Ce point n'est pas secondaire : c'est la raison produit d'avoir choisi ces deux contenus plutot que
n'importe quel autre. Si l'increment de s04 respecte `ADR-0004` et `ADR-0007` a la lettre :

- **Aucune ligne n'est ajoutee ni modifiee dans `domain/mechanic` ni dans
  `domain/mechanics/banditmanchot`** (ou le nom reel du paquet de la mecanique existante). Le
  bandit manchot ne doit rien savoir du francais, comme il ne sait rien des mathematiques
  aujourd'hui.
- Le travail reel est un fichier YAML par jeu (paquet de contenu, type `question`, deja connu) et
  une ligne de catalogue par jeu (`ADR-0007`, tableau "comment on ajoute quelque chose" : "Zero
  ligne de Java"). C'est donc, par construction, du **contenu**, pas du **code**.

Si l'avis de faisabilite de `theo-archi` montre l'inverse - qu'un des deux contenus a besoin d'une
permission supplementaire sur `Item` ou d'un changement de mecanique - je le dis explicitement
dans la story concernee plutot que de forcer un contenu dans une forme qui ne lui convient pas :
voir la note de frontiere de chaque story du backlog.

## Ce qui reste non testable par construction

- Que le francais soit effectivement "la matiere qui teste le mieux le decouplage" est un jugement
  qualitatif de ma part, motive dans la section 2 ci-dessus ; ce n'est pas une mesure. La preuve
  reelle viendra du code (aucune ligne touchee dans `domain/mechanic`) et sera verifiee par
  `theo-archi`, pas par cette decision.
- Que le choix de ces deux competences precises soit "le plus attendu" du programme reste une
  appreciation produit, pas une donnee du referentiel (les referentiels de `squad/programme/` sont
  eux-memes annonces comme "non exhaustifs et non officiels").
- Que le contenu repris (mots, phonemes, phrases) soit pedagogiquement correct et adapte a son
  niveau n'est pas tranche ici : c'est le travail de `nadia-prof`, au meme titre que n'importe quel
  contenu neuf, y compris sur des points deja visibles dans la matiere premiere (la notation des
  phonemes entre crochets, les mots-prompts entierement en majuscules).
