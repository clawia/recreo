# Fiche de conformite - accueil par matiere (V1 deux etages, V2 cartes groupees, bascule en session)

- **Increment** : l'accueil `/niveaux/{niveau}` presente les jeux du niveau par matiere, en deux vues (V1 par defaut depuis le 2026-10-12, `DEC-0119` ;
  V2 par defaut avant, V1) entre lesquelles un bouton de l'accueil bascule. Stories `HS-ACCUEIL-LIBELLES`,
  `HS-ACCUEIL-V2`, `HS-ACCUEIL-V1`, `HS-ACCUEIL-BASCULE`, `HS-ACCUEIL-COMPLETUDE`, `HS-ACCUEIL-MESURE`
  (`docs/backlog/backlog.md`, section "Chantier hors sprint : accueil par matiere").
- **Mecanique** : aucune. Chantier hors sprint, ouvert par `DEC-0117` (statut `Proposee`, amendee le 2026-10-12 :
  `Q-U1` et `Q-U2` repondues, mots de l'utilisatrice non retrouves dans le depot).
- **Contenu(s)** : aucun paquet nouveau. Le texte lu par l'enfant est neuf : onze libelles courts de matiere
  (nombre au 2026-10-11, derive du catalogue), leurs pictogrammes, le libelle du bouton de bascule. Aucun n'est
  ecrit par `camille-po`.
- **Statut global** : en construction
- **Derniere mise a jour** : 2026-10-12 (criteres de `camille-po` seulement, vue par defaut V1 : `DEC-0119` ; le bloc de validation n'est pas touche) ; 2026-10-12 16:41, `nadia-prof` : verdict sur V1 par defaut, bloc de validation

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| transverse - pas de contenu pedagogique | CP, CE1, CM1 | accueil | aucune | aucune |

## Criteres pedagogiques attendus - camille-po

Ce sont les points ou le produit attend le jugement de `nadia-prof`. Je n'en juge aucun.

- [ ] Chaque libelle court de matiere est ecrit en francais correct et integralement accentue (`DEC-0002`) ;
  `nadia-prof` arbitre le mot et le pictogramme de chacune des matieres servies **avant le premier commit de
  code** (`HS-ACCUEIL-LIBELLES`).
- [ ] Le nom de la matiere est lu en texte sur chaque groupe (V2) et chaque tuile (V1) ; la couleur n'est jamais
  le seul signal (`M11` clause 1).
- [ ] Le libelle du bouton de bascule dit la vue en mots, et n'est pas un second mot de sortie (`BARRE-5`).
- [ ] Un enfant qui ouvre `/niveaux/CP` voit, sans defiler, au moins 8 cartes en V2 (10 jusqu'au 2026-10-12,
  `DEC-0118` ; 5 sur la liste d'avant) ou toutes les tuiles de matiere en V1 (CP : 8) : critere de produit
  mesure par `yanis-qa` (`DEC-0117` section 6, point 3). `nadia-prof` dit si `DEC-0053` couvre l'accueil ; elle
  ecrit que le pli de l'accueil n'est pas un motif de `REFUSE` et que 8 lui suffit (`audit-nadia.md` P8).
- [ ] Les jeux du catalogue (147 au 2026-10-11 : CP 59, CE1 30, CM1 58 ; 146 et CP 58 depuis le retrait de
  `bandit-manchot-rapprocher-deux-oeuvres-cp`, `13d00bfe`) restent atteignables dans chaque vue.
- [ ] Le choix de vue vit dans la session HTTP et disparait avec elle ; `nadia-prof` dit si cela contredit
  `S15-5` (`Q-N1`) et a quoi ramene `Les jeux` depuis un jeu en V1 (`Q-N2`).
- [ ] Les garde-fous de l'accueil existants restent verts : `TailleDesTextesEtBoutonsTest` (44 px, 1,25 rem),
  `BARRE-4`, `BARRE-5`, `BARRE-6`, `BARRE-8` de `FICHE-barre-entree-niveau.md`.

- [ ] **Vue par defaut V1** (`DEC-0119`, 2026-10-12, mots de l'utilisatrice : *"par defaut apres avoir choisi sa classe
  montre le choix de la matiere"*). Un enfant qui choisit sa classe voit d'abord une tuile par matiere, dont le nom est lu
  en texte, sans carte de jeu (CP : 8 tuiles) ; le bouton dit **"Tout voir"** ; V2 s'ouvre par ce bouton et le bouton y dit
  **"Par matière"**. `nadia-prof` dit si sa condition `N4` et sa lecture de `S15-5` tiennent avec V1 par defaut, si "8 me
  suffit" tient pour V2 atteinte par un bouton, et ce que l'etage 1 a 390 px (4 tuiles sur 8, `rapport-qualite.md` E3) change
  pour la page par defaut. Le critere "sans defiler" ci-dessus se lit : V1 etage 1 a l'ouverture (toutes les tuiles), 8 cartes pour V2 atteinte par la bascule. Criteres de mesure : `DEC-0119` section 4 (A a E).
- [ ] **Condition de fusion** (`DEC-0117` section 11) : l'audit de `nadia-prof` precede la fusion de ce changement sur
  `round-two`. Le Done ci-dessous a ete prononce sur V2 par defaut ; `camille-po` ne dit pas s'il couvre V1 par defaut.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **VALIDE SOUS RESERVE**, sous une condition avant fusion (`APM-12`). Le Done pedagogique vaut pour
  l'arbre `def23ead` (`lea-accueil-p4`) **augmente d'`APM-12`** ; fusionne sans `APM-12`, il n'est pas acquis.
- **Date** : 2026-10-11 20:45 (UTC+02:00), lue par `date`.
- **Audit joue** : oui, sur le jar, dans un navigateur emule. Detail : `squad/sprints/hors-sprint-accueil-vues/audit-nadia.md`.

### Ce qui a ete verifie

Worktree `nadia-accueil-audit`, base `def23ead`, `bash outils/verifier-lignee.sh` sort 0. `bash outils/mvn-jdk21.sh -B
clean verify` a la racine : BUILD SUCCESS, JDK 21.0.10 rapporte par l'enveloppe, 120 / 31 / 485 / 1484 / 367 / 6 tests
(`domain`, `application`, `adapter-web`, `adapter-content`, `bootstrap` unitaires puis integration), 0 echec. C'est le job
`build` seul ; je n'ai lance ni `prefixes`, ni `chemins`, ni `outils` sur l'arbre de `lea-dev`. Jar servi par `java -jar`,
port verifie libre par `netstat`, PID lu dans son journal de demarrage, arrete par ce seul PID. Chrome headless a profil
jetable, pilote par le protocole de debogage, fenetre emulee 744 x 551 puis 390 x 551 ; chaque Chrome arrete par son PID.
Deux parcours dans deux sessions, trois parties jouees jusqu'a la fin en me trompant.

- **Parcours complet** (CP, CE1, CM1) : choix du niveau, V2 par defaut, bascule vers l'etage 1, tuile, etage 2, retour a
  l'etage 1, lancement d'un jeu, "Les jeux" en fin de partie et sur la confirmation, choix d'un autre niveau.
- **`APM-1`, `APM-4`** : les onze libelles et l'ordre de N5 sur les deux vues, aux trois niveaux.
- **`APM-3`** : mot en texte sur chaque tuile et chaque bandeau ; aucun pictogramme sur une carte ni sur une puce.
- **`APM-5`** : 0 element teinte sur chaque ecran de manche, de retour d'erreur, de fin de partie et de confirmation, releve
  a chaque etape de trois parties ; 0 aussi sur la page d'un segment inconnu et sur la page 404.
- **`APM-6`** : aucune teinte grise ; le magenta d'Histoire des arts se lit comme une couleur.
- **`APM-7`** : un seul bouton de vue, dans la zone de l'accueil, aucune marque d'etat, absent des ecrans de jeu.
- **`APM-8`, `APM-9`** : apres une partie lancee depuis l'etage 2 de Maths, "Les jeux" mene a "Choisis ton niveau.", puis
  la puce CP ouvre l'**etage 1** (vue retenue), jamais l'etage 2.
- **`APM-10`** : etage 2 a une carte joue en CP (Anglais) et en CM1 (Histoire des arts) : rien ne parait casse.
- **`APM-11`** : une tuile atteinte par son adresse pendant une partie, et une puce pressee pendant une partie, rendent le
  meme ecran, *"Si tu pars, ta partie s'arrête."* ; "Continuer la partie" rend la manche intacte (meme question, score 0).
  La bascule envoyee pendant une partie rend cette confirmation et la partie reste en cours.

### Motif

L'increment fait ce que la demande voulait sur les deux ecrans ou elle se posait : l'enfant voit le nom de la matiere a
tout moment (bandeau colle en V2, tuile en V1), et les huit matieres du CP ou du CM1 tiennent sur un ecran a l'etage 1.
Les onze conditions `APM-1` a `APM-11` sont tenues sur le jar. Les pictogrammes se lisent a leur taille rendue, a cote de
leur mot. "Par matière" et "Tout voir" ne ressemblent pas a "Les jeux" : autre zone (la page claire, pas la barre
sombre), autre forme (une icone qui dessine la destination), jamais le meme mot.

**Ce qui empeche un `VALIDE` sans condition : la phrase du premier ecran.** *"Choisis ton niveau."* est un texte neuf, lu
par chaque enfant a l'ouverture et apres chaque partie, et personne ne l'avait arbitre (R4 de `lea-dev`). "Niveau", pour
un enfant de six ans, c'est le niveau d'un jeu, le facile et le difficile : la phrase invite a choisir CM1 parce que c'est
"plus fort". CP, CE1 et CM1 sont pour lui des noms de classe ("je suis en CP"). Et "niveau" porte `eau`, graphie
complexe en debut de CP, la ou "classe" se dechiffre.

### Reserves

- **`APM-12`, condition avant fusion.** La phrase de l'ecran du choix devient **"Choisis ta classe."**, mot pour mot, a
  la place de *"Choisis ton niveau."* ; l'inventaire de `TexteAccentueDesGabaritsTest` la porte comme arbitree. Verifiable
  sans moi : le texte rendu par `GET /` et par `POST /retour` est exactement cette phrase.
- **`APM-R1`, reserve, non bloquante.** Apres "Les jeux" sur l'ecran de confirmation, l'ecran demande la classe mais
  l'adresse reste celle de la page quittee. Joue : partie lancee depuis `/niveaux/CP/matieres/maths`, "Les jeux", "Les
  jeux" ; l'ecran dit *"Choisis ton niveau."*, l'adresse reste `/niveaux/CP/matieres/maths`, et **un rechargement rend
  l'etage 2 de Maths**. Attendu : apres "Les jeux" de la confirmation, l'adresse est `/`, comme apres "Les jeux" de la fin
  de partie (joue : adresse `/`). Non bloquante : il faut un rechargement, et l'ecran atteint dit sa classe, sa matiere et
  son chemin de retour. La cause est technique (R6 de `lea-dev`) ; le moyen est a `theo-archi`.

### Mentions de non-couverture opposables

- **Non verifies, et je ne les declare pas verifies** : le clavier (le bandeau colle peut-il cacher la carte qui recoit le
  focus : point 4 de ma section 6, toujours ouvert), le lecteur d'ecran, une tablette reelle, Safari, le daltonisme.
  J'ai tente une tabulation emulee ; elle ne fait pas defiler la page emulee comme le ferait un ecran reel, je ne retiens
  aucun de ses chiffres.
- **La revelation** (apres plusieurs erreurs) n'a pas ete observee dans mes parties : `APM-5` y est tenu par le gabarit,
  pas par mon jeu.
- **Aucun enfant n'a joue.** Ce que je dis de la lecture d'un enfant de CP est un jugement de maitresse.
- **Je reste seule garde** de la reconnaissance des pictogrammes, de la teinte qui ne se lit pas "desactive", de la
  confusion entre le bouton de vue et "Les jeux", et du bandeau colle sous le focus clavier : aucun test ne les tient.

### Verdict du 2026-10-12 16:41 (UTC+02:00) - V1 par defaut (`DEC-0119`) : VALIDE SOUS RESERVE

Ajout date ; le verdict du 2026-10-11 ci-dessus reste ecrit tel quel. Detail :
`squad/sprints/hors-sprint-accueil-vues/audit-nadia-v1-defaut.md`.

- **Verdict** : **VALIDE SOUS RESERVE**, **sans condition avant fusion**. Le Done pedagogique vaut pour l'arbre
  `95909939` (branche `v1-nadia` : `DEC-0119` et le code `a74ff775` de `lea-dev`), V1 par defaut. Il remplace, pour la vue
  par defaut, celui du 2026-10-11 prononce sur V2 par defaut.
- **Date** : 2026-10-12 16:41 (UTC+02:00), lue par `date`.
- **Audit joue** : oui, sur le jar, dans un Chrome headless emule a 744 x 551 et 390 x 551. Six sessions, trois parties
  jouees en me trompant d'abord.

**Ce qui a ete verifie.** Session neuve, puce CP, CE1 ou CM1 : l'etage 1, une tuile par matiere (8, 2, 8), toutes
entieres a 744, aucun titre de jeu, un seul bouton de vue, "Tout voir". "Tout voir" rend V2 (8 cartes entieres en CP,
CE1, CM1), bouton "Par matière" ; "Par matière" rend l'etage 1 ; le chevron de l'etage 2 aussi. Apres une partie jouee
jusqu'a la fin sans aucun appui sur la bascule, "Les jeux" mene a *"Choisis ta classe."* (adresse `/`) et la puce rend
l'etage 1 ; apres "Tout voir", une partie et la sortie par la confirmation, la puce rend V2 ; puis "Par matière" et une
autre puce rendent l'etage 1. Puce pressee et bascule envoyee pendant une partie : la confirmation, memes mots, la manche
intacte apres "Continuer la partie". `APM-1` a `APM-12` tenues sur ce que j'ai joue.

**Motif.** La demande de l'utilisatrice est tenue sur l'ecran ou elle la posait : apres le choix de sa classe, l'enfant
voit les matieres, chacune avec son mot et son image, et rien d'autre a lire. Pour un enfant de CP, huit choix illustres
remplacent 58 titres dont 8 a l'ecran. Mes reponses a `camille-po` :

1. **`N4` est remplacee, ma lecture de `S15-5` tient** : l'etage 1 montre toutes les matieres de la classe et aucun jeu,
   "Tout voir" est a l'ecran, aucun controle ne marque la vue, la seule puce active est celle que l'enfant a pressee, la
   session ne retient que la vue. **V1 par defaut : ACCEPTEE**, sous `APM-7`, `APM-8`, `APM-9` inchangees.
2. **Le refus d'`O2` vaut pour l'etage 1** : son motif ne dependait pas de la vue par defaut.
3. **"8 me suffit" vaut pour V2 atteinte par le bouton** : un ecran que l'enfant demande n'exige pas plus qu'un ecran
   qu'on lui impose. Mesure : 8 / 8 / 8.
4. **L'etage 1 a 390 px : ACCEPTE, sans condition.** 4 tuiles entieres sur 8 en CP et en CM1, 2 sur 2 en CE1. Le pli
   de l'accueil n'est pas un motif de `REFUSE`, a 744 comme a 390 (ecrit le 2026-10-11) ; je ne deplace pas ce poteau.
   Français et Maths sont dans la premiere rangee, et la troisieme rangee coupee au bas de l'ecran dit qu'il y a une suite.
5. **Le premier ecran est lisible** par un enfant de CP accompagne (l'adulte ne porte que "Questionner le monde", comme
   avant) et par un enfant de CM1. Je ne demande pas de consigne sur l'etage 1 : les tuiles sont les seuls choix de la page.

**Reserves.**

- **`APM-R1` : levee.** Barre ecrite le 2026-10-11 : apres "Les jeux" de la confirmation, l'adresse est `/`. Jouee depuis
  `/niveaux/CP/matieres/maths` et depuis `/niveaux/CE1` : adresse `/`, et le rechargement rend *"Choisis ta classe."*.
- **`APM-R2`, reserve neuve et datee, non bloquante.** Un "Tout voir" presse pendant une partie, puis annule par
  "Continuer la partie", est quand meme retenu. Joue : partie lancee depuis l'etage 2 de Français, retour arriere du
  navigateur (l'etage 1 s'affiche, avec "Tout voir"), "Tout voir", la confirmation, "Continuer la partie" ; puis sortie de
  la partie par "Les jeux" et la confirmation, puce CP : **V2**. Reproduit hors du navigateur dans une meme session : sans la
  bascule envoyee pendant la partie, l'etage 1 ; avec, V2. **Attendu, verifiable sans moi** : apres un appui sur le bouton
  de vue pendant une partie puis "Continuer la partie", la vue retenue en session est celle d'avant l'appui. Non bloquante :
  il faut un retour arriere pendant une partie, V2 ne cache rien, "Par matière" la defait en un appui. Le symptome precede
  `a74ff775` (meme ordre avec V2 par defaut) ; le moyen est a `theo-archi`, la propriete se tient par un test de route.
- **Correction de mon constat du 2026-10-11** : *"La bascule envoyee pendant une partie rend cette confirmation et la
  partie reste en cours"* est exact et incomplet ; je n'avais pas regarde la vue retenue apres la confirmation.

**Mentions de non-couverture opposables.**

- **Non verifies, et je ne les declare pas verifies** : le clavier (bandeau colle sous le focus en V2 ; ordre de
  tabulation de l'etage 1 devenu premier ecran), le lecteur d'ecran, une tablette reelle (la hauteur utile change la
  coupe a 390), Safari, le daltonisme ; le critere A3 de `DEC-0119` (trois etages 2 ouverts sur dix-huit) ; la session
  expiree ; le releve element par element d'`APM-5`.
- **Aucun enfant n'a joue.**
- **Je reste seule garde** de la reconnaissance des pictogrammes, de la teinte qui ne se lit pas "desactive", de la
  confusion entre le bouton de vue et "Les jeux", du bandeau colle sous le focus clavier, et du signe de suite a 390 px
  (une rangee coupee au bas de l'etage 1) : aucun test ne tient ces cinq proprietes.
