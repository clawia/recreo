# [Sprint s10] Retrospective - 2026-08-14

- **Anime par** : sofia-sm

## Ce qui a marche

- **Un refus pedagogique s'est resserre en deux tours d'audit honnetes, sans jamais bouger les
  poteaux.** `nadia-prof` a refuse le sprint deux fois (M4 et M13 clause 1 d'abord, sur sept jeux ;
  puis M13 clause 1 seule, resserree a l'ecran de revelation), et chaque fois qu'elle a trouve que
  sa propre mesure du tour precedent etait incomplete ("j'avais ecrit quatorze cas sur quatorze et
  mesure quatorze cas, pas vingt-cinq"), elle l'a ecrit comme un manque de sa mesure, jamais comme
  un assouplissement de sa condition.
- **`ADR-0038` est le premier `ADR` de ce depot qui reprend a un automate une propriete qu'on
  croyait reservee a `nadia-prof`** (tabulation depuis un point de focus), et il le fait en nommant
  explicitement huit choses que son futur vert ne prouvera pas, plus un vert facile identifie
  d'avance (le contenu qui cesserait d'offrir plus d'une proposition). C'est la troisieme fois ce
  sprint que cette pratique apparait (`ADR-0036`, `ADR-0037`, `ADR-0038`) sans avoir jamais ete
  ecrite comme une convention du depot - je la route a `theo-archi` dans le tableau des obstacles
  de `review.md` plutot que de l'acter moi-meme.
- **`yanis-qa` a etabli une propriete commune a quatre garde-fous casses-puis-restaures ce
  sprint** (chacun vert par absence de rencontre avec l'objet qu'il pretendait garder, jamais par
  absence de defaut), par mutation reelle a chaque fois, jamais par lecture seule. `theo-archi` a
  retrouve la meme forme de defaut une cinquieme fois en instruisant `nashorn-core` (`ADR-0014`
  ecartait le webjar en prose sans rien encoder, deux chemins d'entree du garde-fou de provenance
  restaient verts).
- **Les deux levees de l'utilisatrice sont restees gouvernees par ecrit jusqu'au bout.** Quand
  `theo-archi` a mesure que le defaut de tabulation de `DEC-0026` touchait aussi l'ecran de reprise,
  personne n'a elargi la levee en silence : `camille-po` a verifie le fait par elle-meme sur le
  code, corrige une phrase devenue fausse de son propre texte, et etendu le declencheur de
  reouverture en consequence, dans le meme geste que la fusion.
- **Un ecart de redaction (ligne de competences perimee) a ete trouve deux fois independamment**,
  par `nadia-prof` et par une relecture, chacune hors de son propre perimetre de correction, et
  chacune l'a signale plutot que de le corriger elle-meme. `camille-po` l'a corrige une seule fois.

## Ce qui a coince

### 1. Une decision produit ecrite avant que le commit qu'elle decrit n'existe - deux fois

`DEC-0025` portait un chiffre faux parce qu'elle a ete redigee avant le commit qui realise le
resserrement de marges qu'elle decrit, et jamais reverifiee contre lui une fois qu'il a existe ;
le message du commit d'implementation, trente-quatre minutes plus tard, nommait deja deux jeux
differents. `yanis-qa` l'a trouve et `camille-po` l'a corrige par une note datee, sans rien
effacer. **`DEC-0026` portait le meme defaut de fond pour la meme raison** : bornee au seul ecran
mesure par `nadia-prof` (la revelation), alors qu'aucune mesure ne couvrait encore l'ecran de
reprise au moment ou la decision a ete ecrite. `theo-archi` a mesure l'ecart, `camille-po` l'a
verifie et corrige par un amendement, encore une fois sans rien effacer. **C'est le meme defaut
deux fois dans le meme sprint : une decision qui court devant sa mesure.** Aucun amendement au
process n'est ecrit pour ce point precis - la doctrine de reverification existe deja
(`ADR-0003`, "un chiffre annonce se reverifie, il ne se relaie pas") ; ce qui manque est son
application au moment ou une decision est ecrite, pas une regle de plus.

### 2. Le fil dominant du sprint : des garde-fous verts qui n'ont jamais rencontre leur objet

Six occurrences distinctes de la meme forme de defaut ce sprint (quatre etablies par `yanis-qa`
par mutation reelle, une sixieme par `theo-archi` sur `nashorn-core`/le webjar, et `ADR-0038`
lui-meme qui nomme d'avance un vert facile de sa propre clause 2). Aucune n'est un defaut du code
de production : chaque fois, le garde-fou existait, tournait vert, et ne rencontrait jamais
l'objet qu'il pretendait garder - un corpus qui ne produit pas l'etat fautif, un selecteur qui ne
matche plus depuis un changement de bibliotheque, une doctrine ecrite en prose sans regle. Trois
propositions de methode en repondent, routees dans `review.md` : la mutation-sur-materiau-reel
systematique (que j'ai arbitree - acceptee, `squad/README.md` amende), un outil de cycle de vie du
serveur de mesure (routee a `theo-archi`), et la convention de nommer ce qu'une regle ne prouve pas
(routee a `theo-archi`).

### 3. Aucune ceremonie de planning ecrite pour le sprint 10

Aucun fichier `planning.md` n'existe dans ce dossier. Le seul artefact anterieur au developpement
est `avis-faisabilite.md` de `theo-archi` (2026-08-12), qui n'est pas un planning : il porte un
avis de faisabilite sur trois constats, pas un objectif gele ni des affectations nominatives. Je
ne fabrique pas ce fichier retroactivement - une ceremonie sans fichier n'a pas eu lieu, je le
constate ici plutot que de le maquiller. Consequence directe : `review.md` ne peut pas porter de
duree effective, faute d'un horodatage de debut officiel.

### 4. Cinq worktrees de sprint laisses sur le disque

Trois worktrees du premier lot du sprint (`s10-camille-levee-2`, `s10-nadia-verdict-2`,
`s10-yanis-qualite`), deja fusionnes avant cette cloture, restaient ouverts sur le disque au moment
ou j'ai commence cette cloture, en plus des deux worktrees de cette fusion elle-meme. Les cinq
branches etaient des ancetres verifies de `HEAD` : nettoyees a cette cloture (casquette
developpeuse), mais elles n'auraient pas du s'accumuler jusque-la.

### 5. Aucun fichier de relecture dedie, pour la deuxieme fois en deux sprints consecutifs

Aucun commit `[REL]` ni fichier `relecture.md` n'existe pour s10, comme pour s09 ou c'etait
`confirmation-archunit.md` de `theo-archi` qui manquait. Le fond n'est pas perdu ici non plus (la
correction attribuable a la relecture est bien fusionnee, voir `review.md`), mais c'est la seconde
ceremonie de forme differente qui laisse une trace incomplete en deux sprints d'affilee. Je le note
sans en faire une regle nouvelle : a surveiller une troisieme fois avant de conclure qu'il faut un
gabarit de fichier obligatoire meme quand il n'y a rien a corriger.

### 6. Un incident hors depot, signale sans le juger

Un arret de processus par nom d'image, pendant ce sprint, a ferme des applications sans rapport
avec ce projet sur le poste de travail. La contrainte tenue depuis est de n'arreter un processus
que par son PID exact, jamais par un nom qui peut correspondre a plusieurs instances. Hors
perimetre du depot, je le consigne ici pour tracabilite.

## Amendements a `squad/README.md`

**Un amendement, deja ecrit dans le meme geste que `review.md` de cette cloture** (section 9,
point 4, "Porte technique") : le rapport de cloture de `yanis-qa` inclut desormais
systematiquement un tirage d'un ou deux garde-fous recemment ecrits ou modifies, mutes sur le
materiau de production reel, avant chaque `review.md` - proposition 1 de `yanis-qa`, acceptee.

Les deux autres propositions de methode de `yanis-qa` (outil de cycle de vie du serveur de mesure ;
convention d'`ADR` nommant ce qu'une regle ne prouve pas) ne sont pas actees ici : elles sont
routees a `theo-archi` dans le tableau des obstacles de `review.md`, parce qu'elles engagent un
choix d'architecture ou d'outillage qui n'est pas le mien a trancher.

## Decisions prises

1. **L'ordre de fusion (`theo-archi` avant `camille-po`) est respecte** : l'amendement d'etendue de
   `DEC-0026` s'appuie sur la mesure d'`ADR-0038`, et l'ordre reste lisible dans l'historique.
2. **Cinq worktrees de sprint sont nettoyes** (casquette developpeuse), chacun verifie ancetre de
   `HEAD` avant suppression.
3. **`mvn -B clean verify` est rejoue independamment sur l'arbre fusionne final**, apres un `clean`
   complet, plutot que recopie sur l'annonce d'un agent : 406 tests, 28 regles, memes methodes que
   `yanis-qa` et `theo-archi`, memes resultats.
4. **La proposition 1 de `yanis-qa` est acceptee et actee dans `squad/README.md`** ; les
   propositions 2 et 3 sont routees a `theo-archi` pour le sprint 11.
5. **Aucun `planning.md` n'est fabrique retroactivement pour s10** : son absence est constatee
   ci-dessus, pas maquillee.
6. **Le declencheur de reouverture de `DEC-0026` (au moins une des deux stories de tabulation
   planifiee pour s11) et celui de `DEC-0025` (les trois stories de raccourcissement/reecriture/
   ampoule) sont portes dans le tableau des obstacles de `review.md`** : ce sera a moi de les
   affecter a l'ouverture du sprint 11, pas une intention laissee sans suite.
