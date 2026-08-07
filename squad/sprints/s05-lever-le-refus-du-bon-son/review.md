# [Sprint s05] Review - 2026-08-07

- **Horodatage de debut** : 2026-08-06 13:55 (`planning.md`)
- **Horodatage de fin** : 2026-08-07 10:41 (commit `8c6d41e`, verdict de `nadia-prof`)
- **Duree effective** : 20 h 46
- **Anime par** : sofia-sm

## Ce que ce sprint a reellement ete

Il n'a pas suivi un deroule ordonne, et je ne le lisse pas en un. Trois mouvements distincts,
dans cet ordre reel :

1. **Le perimetre planifie a ete livre.** `lea-dev` a corrige trois des quatre motifs de refus de
   "Le bon son" (crochets, indices, double reponse), `marc-dev` a complete les homophones CM1 et
   ecrit leurs indices, `theo-archi` a ferme la dette de contenu d'`ADR-0016` et livre le
   garde-fou de saillance (`ADR-0017`), `sofia-sm` a livre S5-8 (titre "Recreo").
2. **Le sprint a ensuite ete interrompu trois fois par des defauts que l'utilisatrice a trouves
   en jouant, aucun par la CI, alors que la suite etait verte a chaque fois** : une reponse
   accentuee qui plantait la manche (corrige par `lea-dev`, `ADR-0018` sur ce que cela revele du
   chemin d'erreur), un score qui ne s'incremente pas toujours - une course sur le cycle
   lire-calculer-ecrire de la session HTTP, reproduite par `yanis-qa` a 14 pertes sur 40 - et, en
   creusant ce second symptome, un coup rejoue sur une partie terminee qui rendait l'ecran
   d'erreur au lieu de l'ecran de fin (les deux tranches en un seul geste par `theo-archi`,
   `ADR-0019`). Aucun de ces trois defauts n'a ete signale par la CI : elle etait verte a chaque
   fois qu'ils existaient.
3. **Le sprint a enfin absorbe le rhabillage du gabarit (en-tete, icone, menu, contenu centre)
   que `camille-po` avait explicitement place au sprint 6 dans `DEC-0012`.** `ines-design` l'a
   livre (commits tagues `s06` : `2b15279`, fusionne par `a325af9`) et il a ete audite par
   `nadia-prof` dans le meme cycle que le reste de s05, sur le meme jar. Ce deplacement n'a fait
   l'objet d'aucune nouvelle ceremonie de planning ni d'aucun `DEC` qui modifie `DEC-0012` : je le
   constate ici, comme l'anomalie qu'il est, plutot que de le taire. Le motif pratique est simple
   - `nadia-prof` reconstruisait de toute facon le jar pour auditer "Le bon petit mot" et devait
     de toute facon relancer une instance sur le port 8080 : auditer le rhabillage dans le meme
     geste evitait un second cycle complet de construction et d'audit pour un chantier deja pret
     - mais un motif pratique n'est pas une decision ecrite, et `DEC-0012` n'a pas ete formellement
     amendee. Je le route en amendement de process dans `retro.md`.

## Story par story

| Story | Livre | Statut |
|---|---|---|
| S5-1 (residuel) | Second critere (reprise de session sur contenu retire) | **garde volontairement ouvert**, comme prevu au planning ; `nadia-prof` et `theo-archi` s'accordent sur une fenetre d'exposition nulle tant qu'aucune des cinq conditions listees n'est vraie |
| S5-2 | Recomposition du paquet "Le bon son" (majorite non resoluble par les lettres) | **non livree ce sprint**. `nadia-prof` a tranche le 2026-08-06 (avis de conception) que le motif 3 ne peut pas etre leve par un remplacement de mots : il faut une recomposition complete (15 items sur les trois seules nasales an/on/in) couplee a S5-9 (retour explicatif), hors perimetre de s05 (`DEC-0011`). Rien n'a ete code sur ce point ce sprint |
| S5-3 | Sons sans notation d'adulte (crochets retires) | **livree et acquise**, confirmee par `nadia-prof` a l'audit de conception (motif 2) |
| S5-4 | Un seul son vrai par item (items a double reponse retires) | **livree et acquise** sur les 21 items actuels (motif 4), a reverifier sur les mots neufs de la future recomposition |
| S5-5 (residuel) | Indices de `cp-le-bon-son` (21) et de `cm1-homophones` (22) | **livree**. `ADR-0016` : `QuestionContentSourceFactory` lit desormais la cle `indice` ; `ChaquePaquetPorteUnIndiceTest` derive du repertoire de contenu et sa dette est fermee (`DETTE_DE_CONTENU = Set.of()`) |
| S5-6 | Cinq couples d'homophones manquants (ce/se, ces/ses, la/la/l'a, mais/mes, peu/peut) | **livree**, cinq familles ajoutees en douze items, triplet `la/la/l'a` en deux paires pivotant sur `la` conformement a l'arbitrage de `nadia-prof` du 2026-08-06, chaque paire dans les deux sens |
| S5-7 | Variete entre deux parties CM1 | **livree**, 22 items pour 10 manches, deux parties a graines distinctes ne presentent pas les memes phrases (verifie en jeu par `nadia-prof`) |
| S5-8 | "Recreo" sans accent | **livree**, test jsoup derive du repertoire de gabarits |
| Garde-fou de saillance (routee par `theo-archi`, pas une story de `camille-po`) | Comparer deux boutons d'un meme ecran | **livree** (`ADR-0017`), puis **etendue** le lendemain pour fermer sa propre limite nommee (frontiere `accueil.html` / fragments, voir plus bas) |
| Garde-fou de premisse sur S5-1 | Test qui echoue si une des cinq conditions de reouverture devient vraie | **non livree**, non bloquant comme prevu au planning ; `theo-archi` a priorise le garde-fou de saillance et les deux correctifs imprevus |
| S6-1, S6-2 (absorbees, voir plus haut) | En-tete, icone, menu a une entree, contenu centre | **livrees et auditees ce sprint**, en dehors du planning initial de s05 |

**"Le bon son CP" n'a pas ete audite ce sprint**, conformement a l'avis de `nadia-prof` du
2026-08-06 : la recomposition qu'elle exige n'est pas codee, et elle a explicitement refuse de
rejouer un paquet dont le motif 3 est intact - "je prononcerais REFUSE une seconde fois [...] ce
qui ne renseignerait personne". Elle a date une note de cloture dans sa fiche pour que ce silence
ne passe pas pour un accord : le jeu reste hors catalogue (`DEC-0009` tenue a la lettre, verifie
en relancant l'application - l'accueil ne propose que "Calcul CP" et "Le bon petit mot").

## Rapport qualite - yanis-qa

- **Commande** : `mvn -B clean verify`, JDK 21.0.10. **BUILD SUCCESS, 226 tests, 0 echec** sur
  l'arbre juge (avant la fusion du lot d'`ADR-0019`).
- **Chantier prioritaire** : a reproduit et documente la course sur le score (section 1 de son
  rapport), 14 pertes sur 40 essais concurrents, routee a `theo-archi`.
- **Deux affirmations fortes verifiees et trouvees fausses par la mesure, pas sur parole** :
  `PLAFOND_DE_DETTE` reste a 2 alors que `DETTE_DE_CONTENU` est vide (routee a `theo-archi`) ;
  `PropositionsAccentueesJouablesIT` reste vert quand on desactive le correctif d'encodage, parce
  que son corps de requete est pourcent-encode et ne declenche jamais le defaut d'origine (routee
  a `sofia-sm`, corrigee par `lea-dev` dans le meme sprint, voir "Deux garde-fous decoratifs"
  en `retro.md`).
- **Trou de frontiere confirme** : le lien "Accueil" de S6-1 (deja fusionne a ce moment) n'est
  confronte a aucun bouton d'action des fragments de jeu par aucun garde-fou automatique, exactement
  la limite qu'`ADR-0017` nommait lui-meme. Confirme a la lecture, pas retranche.

## Confirmation ArchUnit - theo-archi

- **Premiere mesure (commit `099e750` + son propre commit)** : `mvn -B clean verify`, BUILD
  SUCCESS, 206 tests, 0 echec. Chiffre de "29 regles ArchUnit" annonce sans methode.
- **Complement du 2026-08-07, sur l'arbre fusionne (`d53adfc` + son commit)** : corrige son propre
  chiffre a **25** regles, avec la methode de comptage ecrite (parcours des modules du pom, pas
  d'un `grep` global) et trois pieges nommes pour expliquer une divergence possible avec le
  comptage de `yanis-qa` : le faux depot de `LesGardeFousSontMisALEpreuveTest` (+2 si comptees a
  tort), les worktrees d'agents s'ils vivent dans l'arbre parcouru (double-compte), et deux
  moteurs JUnit (ArchUnit + Jupiter) qui ecrivent sur le meme rapport `.txt` par classe et n'en
  gardent que le dernier (perte de 7 regles si on compte sur les `.txt` plutot que sur l'agregat
  Maven ou les `TEST-*.xml`). 228 tests, 0 echec a ce point.
- **Ferme le trou de frontiere d'`ADR-0017`** que `yanis-qa` venait de confirmer : la regle passe
  d'"un ecran = un fichier de gabarit" a "un ecran = la zone `hx-target` echangee par htmx plus ce
  qui lui survit", cible lue dans les gabarits et non ecrite dans le test. Prouve mordant en
  revenant a l'ancienne semantique (2 tests rougissent alors).
- **Corrige le plafond de dette d'`ADR-0016`** signale par le relecteur : `PLAFOND_DE_DETTE` passe
  de 2 a 0, la dette de contenu etant reellement vide.
- **Complement du 2026-08-07, apres fusion du lot `yanis-qa`** : tranche la course sur le score en
  un seul geste, deux clauses (`ADR-0019`) - exclusion mutuelle sur la session HTTP
  (`HttpSessionMutexListener`) et refus de rejouer un coup sur une partie terminee. **238 tests, 0
  echec.** Verifie par moi-meme ce jour, sur JDK 21.0.10, arbre `HEAD` (avant le commit de
  `nadia-prof`) : **BUILD SUCCESS, 238 tests, 0 echec** ; **27 `ArchRule` reellement executees**
  (25 mesures par `theo-archi` avant la fusion d'`ADR-0019`, plus les 2 d'`AccesALEtatDeSessionTest`
  que ce commit ajoute - la progression est coherente avec sa propre methode, je ne trouve pas
  d'ecart).
- **Avis** : porte architecture **ouverte** a la premiere mesure du 2026-08-06 (le trou de saillance
  qu'`ADR-0017` venait de fermer aurait echappe a la CI jusque-la), refermee par son propre
  complement une fois le trou traite.

## Relecture - relecteur-pedagogique

- **Une faute corrigee, repetee dans trois fichiers** : `a/a` remplaçait `a/à` (couple d'homophones
  devenu un mot cite deux fois) dans `cm1-homophones.yaml`, `FICHE-bandit-manchot-homophones-cm1.md`
  et `MATRICE-couverture.md`.
- **La meme faute, hors perimetre de s05, signalee et non corrigee en silence** : `DEC-0008` et la
  section Sprint 4 du backlog portent le meme defaut (`a/a`, `ou/ou` pour `a/à`, `ou/où`) sur du
  texte d'un sprint clos. Route a `camille-po` : soit elle corrige comme une faute d'orthographe
  ordinaire, soit elle explique le maintien.
- **Ecart entre `ADR-0016` et le code trouve avant `theo-archi` lui-meme** : la dette de contenu
  etait fermee dans le code sans que `confirmation-archunit.md` le confirme, comme le planning
  l'exigeait explicitement. Route a `theo-archi`, traite dans son complement du 2026-08-07.
- **Contenu enfant neuf relu caractere par caractere** : 21 indices de `cp-le-bon-son`, 22 items et
  indices de `cm1-homophones`, le libelle "Accueil". Aucune faute d'accentuation, d'apostrophe ou
  de ponctuation. Ce constat ne prejuge de rien sur le fond, c'est le mandat de `nadia-prof`.

## Tableau des obstacles du sprint

| Description | Categorie | Agent route | Decision / artefact attendu | Statut |
|---|---|---|---|---|
| S5-2 recomposee et S5-9 sont un seul lot indivisible ; le motif 3 du refus de "Le bon son" ne peut pas etre leve par un remplacement de mots | pedagogie | nadia-prof (a tranche), camille-po (ecrit les stories), theo-archi, lea-dev (codent une fois ecrites) | Avis de conception de `nadia-prof` du 2026-08-06 dans `FICHE-bandit-manchot-le-bon-son-cp.md` : recomposition a trois sons (an/on/in), 15 items, S5-9 dans le meme lot | route, non code ce sprint |
| Ligne francais CM1 non levee : deux items neufs supplementaires (mot decisif non voisin du trou, dans la/l'a, ces/ses ou peu/peut) | pedagogie + produit | camille-po, marc-dev | Condition retrecie et ecrite au mot pres par `nadia-prof`, verdict du 2026-08-07 | route, non clos |
| Indice de "J'ai range ___ affaires" instruit une substitution qui ne produit pas du francais ("les miennes affaires") | pedagogie + contenu | camille-po, marc-dev | Correction d'une ligne, modele deja present dans le paquet (item `son`), demandee par `nadia-prof` | route, non clos |
| Quatre reserves de forme sur le rhabillage, dont une regression chiffree : `.zone-jeu { padding: 2rem 0 }` ecrase le rembourrage lateral de `.container`, texte et boutons au bord de l'ecran sous 1200 px | design | ines-design, theo-archi | Reserves de `nadia-prof`, `FICHE-bandit-manchot-additions-cp.md`, verdict du 2026-08-07, point A.2 - la premiere est chiffree et remesurable | route, non clos |
| Identifiant `id="jeu"` porte en double apres chaque echange htmx (le `main` du gabarit et le `div` du fragment recu) | technique | theo-archi | Constat de `nadia-prof`, mesure et non suppose, defaut d'accessibilite | route, non clos |
| `GET /parties/{id}/manche` sur une partie terminee rend un ecran mort (consigne, aucun enonce, aucun bouton) | technique | theo-archi | Constat de `nadia-prof` : aucun chemin atteignable par un enfant trouve, mais l'ecran existe | route, non clos |
| `GestionnaireErreurBruteContreExemple` (contre-exemple d'`ADR-0011`) est enregistre comme un vrai bean Spring dans tout `@SpringBootTest` complet de `bootstrap`, a cause de `scanBasePackages = "com.recreo"` qui couvre aussi le classpath de test ; il intercepte l'exception avant `GestionnaireDesErreurs` et rend un 500 a corps brut | technique | theo-archi | Trouve et documente par `lea-dev` (commit `94a1a8f`) en verifiant la morsure de `PropositionsAccentueesJouablesIT` ; hors mandat de son propre commit | route, non clos |
| `PLAFOND_DE_DETTE` d'`ADR-0016` restait a 2 alors que `DETTE_DE_CONTENU` etait vide | technique | theo-archi | Corrige dans son complement du 2026-08-07, plafond porte a 0 | **clos**, `ADR-0016` amende |
| `PropositionsAccentueesJouablesIT` restait vert quand le correctif d'encodage etait desactive (corps pourcent-encode, ne declenche jamais le defaut) | technique | lea-dev | Corrige dans le meme sprint (commit `94a1a8f`), corps reconstruit en Latin-1 non pourcent-encode | **clos** |
| Course sur le cycle lire-calculer-ecrire de la session HTTP (score perdu) et coup rejoue sur une partie terminee (ecran d'erreur) | technique | theo-archi | Tranche en un seul geste, deux clauses, `ADR-0019` | **clos** pour le code ; le rendu du double appui reste soumis a `nadia-prof` |
| DEC-0012 placait le rhabillage du gabarit au sprint 6 ; il a ete livre et audite dans s05 sans amendement ecrit de cette DEC | process | sofia-sm | Constat ici ; amendement de process propose en `retro.md` | ouvert, route vers moi-meme |
| Le fichier de toolchains mal nomme continue de faire conclure a tort qu'aucun JDK 21 n'existe (quatre agents ce sprint, JDK 8 par defaut) | qualite / methode | sofia-sm | Amendement propose en `retro.md` | ouvert |
| Provisionnement d'un worktree sur `main` plutot que `round-two` (`yanis-qa` ce sprint) | process | sofia-sm | Amendement propose en `retro.md` | ouvert |

Rappel : un obstacle n'est `clos` que si le fichier qui le tranche existe physiquement. Verifie
pour chaque ligne `clos` ci-dessus (`ADR-0016`, `ADR-0019`, commit `94a1a8f`).

## Porte pedagogique - nadia-prof

Audit joue, non degrade, sur le jar du commit `c0b5241`, JDK 21.0.10, instance de l'utilisatrice
arretee pour reconstruire puis relancee sur le port 8080. Trois blocs juges separement.

- **Bloc 1 - contenu francais CM1 (`bandit-manchot-homophones-cm1`)** : **VALIDE SOUS RESERVE**.
  Done pedagogique reconduit et elargi aux douze items neufs et aux vingt-deux indices. **La ligne
  de matrice francais CM1 n'est PAS levee.** Cinq des six points de sa condition sont tenus, dont
  ses trois risques nommes (`ces/ses`, `peu/peut`, `mais/mes`). Le point 5 ne passe pas : 5 items
  neufs sur 12 obligent a regarder au-dela du mot voisin, il en faut 6. Le motif de `marc-dev` (qui
  a lui-meme signale l'ecart) est jugé recevable a moitie - `ce/se` et le versant determinant de
  `mais/mes` sont locaux par nature et ne seront pas redemandes - mais quatre de ses items ailleurs
  sont non locaux, donc ce qui est possible quatre fois l'est six fois. **Sa demande est retrecie a
  deux items de plus**, pris dans `la/l'a`, `ces/ses` ou `peu/peut`. Second point bloquant, trouve a
  l'audit et non signale en amont : l'indice de "J'ai range ___ affaires" est le seul des quinze
  indices de substitution a ne pas montrer sa phrase substituee, parce qu'elle n'est pas du
  francais ("les miennes affaires"). Correction d'une ligne.
- **Bloc 2 - rhabillage du gabarit** : **VALIDE SOUS RESERVE**, pas de troisieme refus. Son critere
  chiffre (l'action ne declare rien de moins que la sortie) tient contre les deux sorties de l'ecran
  de retour. Quatre reserves, dont une chiffree et remesurable : `.zone-jeu { padding: 2rem 0 }`
  ecrase le rembourrage lateral de `.container`, donc sous 1200 px la consigne, l'enonce et les
  boutons partent de x=0 et vont au bord droit pendant que l'en-tete garde ses 12 px - la ou
  `body { margin: 2rem }` donnait 32 px partout avant ce sprint. Invisible sur grand ecran, ce qui
  explique que **quatre portes vertes** (CI, ArchUnit, `leContenuDeLAccueilEstCentreDansUneLargeurMaximale`,
  et la lecture d'`ines-design` elle-meme) l'aient laissee passer.
- **Bloc 3 - double appui (`ADR-0019`)** : **VALIDE**. Retient le choix de `theo-archi` avec un
  motif propre : un double appui est une habitude motrice, pas une erreur de l'enfant, et un geste
  involontaire ne doit pas produire de consequence a interpreter. A verifie que l'ecran est gele a
  l'octet pres dans les deux sens (juste puis faux, faux puis juste). Les deux correctifs du score
  rejoues : aucun appui perdu sur 25 parties, aucune des trois formes de corps accentue ne rend
  l'ecran d'erreur.
- **"Le bon son" (CP)** : non audite, note datee dans sa fiche, refus du 2026-08-06 en vigueur,
  entier. `DEC-0009` tenue, verifiee en relancant l'application.

## Decisions prises

Aucune `DEC` ni aucun `ADR` n'est cree par cette review elle-meme. Elle constate et cite les
decisions ecrites pendant le sprint : `ADR-0016` (amende deux fois, dette et plafond), `ADR-0017`
(amende, frontiere fermee), `ADR-0018`, `ADR-0019`. Aucune `DEC` n'a ete creee ni amendee pendant
s05 sur le fond produit ; `DEC-0012` aurait du l'etre pour refleter le deplacement du rhabillage
vers ce sprint, et ne l'a pas ete - je le route en amendement de process plutot que d'ecrire cette
`DEC` a sa place, ce qui n'est pas mon role.
