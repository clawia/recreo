# Fiche de conformite - accueil par matiere (V1 deux etages, V2 cartes groupees, bascule en session)

- **Increment** : l'accueil `/niveaux/{niveau}` presente les jeux du niveau par matiere, en deux vues (V2 par
  defaut, V1) entre lesquelles un bouton de l'accueil bascule. Stories `HS-ACCUEIL-LIBELLES`,
  `HS-ACCUEIL-V2`, `HS-ACCUEIL-V1`, `HS-ACCUEIL-BASCULE`, `HS-ACCUEIL-COMPLETUDE`, `HS-ACCUEIL-MESURE`
  (`docs/backlog/backlog.md`, section "Chantier hors sprint : accueil par matiere").
- **Mecanique** : aucune. Chantier hors sprint, ouvert par `DEC-0117` (statut `Proposee`).
- **Contenu(s)** : aucun paquet nouveau. Le texte lu par l'enfant est neuf : onze libelles courts de matiere
  (nombre au 2026-10-11, derive du catalogue), leurs pictogrammes, le libelle du bouton de bascule. Aucun n'est
  ecrit par `camille-po`.
- **Statut global** : en construction
- **Derniere mise a jour** : 2026-10-11

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
- [ ] Un enfant qui ouvre `/niveaux/CP` voit, sans defiler, au moins 10 cartes en V2 (aujourd'hui 5) ou toutes les
  tuiles de matiere en V1 (CP : 8) : critere de produit mesure par `yanis-qa` (`DEC-0117` section 6, point 3).
  `nadia-prof` dit si `DEC-0053` couvre l'accueil.
- [ ] Les 147 jeux (CP 59, CE1 30, CM1 58 au 2026-10-11) restent atteignables dans chaque vue.
- [ ] Le choix de vue vit dans la session HTTP et disparait avec elle ; `nadia-prof` dit si cela contredit
  `S15-5` (`Q-N1`) et a quoi ramene `Les jeux` depuis un jeu en V1 (`Q-N2`).
- [ ] Les garde-fous de l'accueil existants restent verts : `TailleDesTextesEtBoutonsTest` (44 px, 1,25 rem),
  `BARRE-4`, `BARRE-5`, `BARRE-6`, `BARRE-8` de `FICHE-barre-entree-niveau.md`.

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
