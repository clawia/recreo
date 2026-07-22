# Backlog produit — `recreo`

Tenu par `camille-po`. Chaque story cite la **compétence programme visée** (obligatoire) et des
critères d'acceptation **testables**, repris par `yanis-qa` pour écrire les tests correspondants.
Toute story qui touche à un contenu pédagogique met à jour
`squad/programme/MATRICE-couverture.md`.

> Ce fichier est le miroir humain lisible du backlog ; les décisions produit détaillées et leur
> statut de validation vivent dans `squad/decisions/produit/` (DEC).

---

## Sprint s01 — Amorçage

### Story S01-1 — Socle Clean Architecture + squad opérationnelle

**En tant que** porteuse du projet, **je veux** un dépôt `recreo` avec un squelette Maven
multi-module Clean Architecture, une CI verte à vide et une squad d'agents fonctionnelle, **afin
de** pouvoir enchaîner les incréments suivants sur des bases saines.

- **Compétence programme visée** : sans objet (story de fondation technique, pas de contenu
  pédagogique).

Critères d'acceptation :
- [ ] `pom.xml` parent + 5 modules (`domain`, `application`, `adapters/adapter-web`,
      `adapters/adapter-persistence`, `bootstrap`) déclarés et compilant à vide (`mvn compile`).
- [ ] Java 21 configuré dans le POM parent.
- [ ] `.github/workflows/ci.yml` exécute `mvn verify` et le job est vert sur un dépôt vide de
      code métier.
- [ ] Les 6 fichiers `.claude/agents/*.md` existent (camille-po, sofia-sm, theo-archi, yanis-qa,
      marc-dev, lea-dev).
- [ ] `squad/README.md`, `squad/TEMPLATES.md` et l'arborescence `squad/` (ceremonies, decisions,
      programme, conformite, validation) existent.
- [ ] `CLAUDE.md` à la racine pointe vers `squad/README.md`.
- [ ] ADR-0001 (Clean Architecture), ADR-0002 (Maven multi-module), ADR-0003 (htmx + Thymeleaf,
      fat jar) sont écrits et `Accepté`.
- [ ] `docs/dod/definition-of-done.md` existe et distingue Done technique / Done pédagogique.
- [ ] `squad/programme/MATRICE-couverture.md` existe avec au moins les lignes calcul mental
      CP/CM1 et quelques lignes « non couvert » d'autres matières.
- [ ] Une 1re cérémonie de planning est consignée dans `squad/ceremonies/` avec horodatage de
      début.

### Story S01-2 — Bandit manchot jouable, calcul mental CP

**En tant qu'**élève de CP, **je veux** réviser les additions et soustractions jusqu'à 10 via un
bandit manchot qui me propose une opération et plusieurs réponses possibles, **afin de**
m'entraîner en calcul mental de façon ludique et sans me lasser.

- **Compétence programme visée** : Mathématiques — CP — Nombres et calculs — « Calcul mental,
  additions et soustractions dans la limite de 10 » (voir
  `squad/programme/mathematiques/competences-cp.md`).

Critères d'acceptation :
- [ ] Un `CalculMentalGenerator` produit des `Item` d'addition et de soustraction dont les deux
      opérandes et le résultat restent dans l'intervalle [0, 10].
- [ ] Chaque `Item` généré est accompagné d'un nombre de distracteurs configurable (2 pour la
      config CP) qui ne contiennent jamais la bonne réponse en double.
- [ ] Un test unitaire déterministe (`RandomSource` fixé) vérifie sur au moins 100 tirages qu'aucun
      opérande ni résultat ne sort de [0, 10] en configuration CP.
- [ ] La `BanditManchotMechanic` démarre une session à partir d'une `GameConfig` « calcul mental
      CP » et retourne un `GameSession` valide (round courant + score initial).
- [ ] Soumettre la bonne réponse via `apply(session, Move)` incrémente le score et fait avancer
      au round suivant ; soumettre une mauvaise réponse ne l'incrémente pas.

### Story S01-3 — Bandit manchot jouable, calcul mental CM1

**En tant qu'**élève de CM1, **je veux** réviser mes tables de multiplication via un bandit
manchot, **afin de** les automatiser sans que ça ressemble à une leçon.

- **Compétence programme visée** : Mathématiques — CM1 — Nombres et calculs — « Calcul mental,
  tables de multiplication jusqu'à ×10 » (voir
  `squad/programme/mathematiques/competences-cm1.md`).

Critères d'acceptation :
- [ ] Le même `CalculMentalGenerator`, paramétré par la `DifficultyConfig` CM1, produit des
      multiplications dont les deux facteurs sont dans [1, 10].
- [ ] La config CM1 utilise 3 distracteurs (contre 2 en CP), sans modifier une ligne de code de
      la mécanique (`BanditManchotMechanic` inchangée entre CP et CM1).
- [ ] Un test unitaire déterministe vérifie sur au moins 100 tirages que les items générés en
      CM1 sont bien des multiplications avec facteurs dans [1, 10].
- [ ] Un test ArchUnit interdit à `domain.mechanic..` / `domain.mechanics..` de dépendre des
      générateurs de contenu concrets (preuve du découplage mécanique/contenu, cf. ADR
      d'architecture).
- [ ] Un test paramétré démarre une session CP puis une session CM1 via la **même**
      `BanditManchotMechanic` et vérifie la conformité des items à chaque config (preuve du
      découplage à l'exécution).

---

## Sprint s02 — Navigation + 1re matière français (2 évolutions)

Issu du playtest s01 (DEC-0001 / cérémonies `2026-07-18-s01-demo-playtest.md` et
`2026-07-18-s02-planning.md`).

**US-s02-1 — Écran de sélection de niveau puis jeu plein écran centré.**
> En tant qu'enfant, je choisis d'abord mon niveau sur un écran dédié, puis je joue au bandit
> manchot centré à l'écran, avec un seul bouton « retour » pour revenir choisir un autre niveau.

Critères d'acceptation :
- [ ] Un écran de sélection présente **uniquement** les niveaux (CP / CM1), sans zone de jeu.
- [ ] Cliquer un niveau **bascule vers l'écran de jeu** correspondant (le catalogue disparaît).
- [ ] L'écran de jeu affiche le **bandit manchot centré** et une **flèche « retour »** unique qui
      ramène à la sélection de niveau.
- [ ] Aucune régression de l'animation des rouleaux ni du placement du message de points.
- [ ] Découplage préservé : la sélection ne fait que choisir un `catalogEntryId` ; le domaine est
      inchangé.

**US-s02-2 — Français CP joué avec le bandit manchot (évolution 2).**
> En tant qu'élève de CP, je révise une compétence de français avec le bandit manchot.

Critères d'acceptation :
- [ ] Nouveau `ContentGenerator` français CP (`domain/content/impl`), piloté par des règles,
      produisant des items conformes au programme CP (compétence figée par Camille).
- [ ] Entrée catalogue `francais-cp` réutilisant **`BanditManchotMechanic` sans modification** de
      la mécanique (vérifié par ArchUnit + test paramétré maths/français sur la même mécanique).
- [ ] **Enabler technique (ADR-0004)** : l'affichage des rouleaux ne parse plus le calcul côté
      front ; la mécanique/le view-model expose des **tokens de rouleaux génériques**.
- [ ] La `MATRICE-couverture` reflète la couverture français CP (en cours → couvert-validé).

---

## Sprint s03 — Navigation à deux axes + paramétrage des jeux

Demandé après la démo s02 (cérémonie `2026-07-18-s02-demo-playtest.md`). **Prérequis process**
(rétro s02) : Camille fournit une esquisse d'ergonomie (DEC) avant implémentation ; Théo cadre le
modèle de catalogue + paramètres (ADR).

**US-s03-1 — Deux façons de trouver un jeu.**
> En tant qu'enfant (ou accompagnant), je peux parcourir soit **par niveau puis jeux**, soit
> **par jeux**, pour arriver au jeu voulu.

Critères d'acceptation :
- [ ] Écran d'accueil proposant deux entrées : **« Par niveau »** et **« Par jeu »**.
- [ ] *Par niveau* : choisir CP ou CM1 → liste des jeux disponibles pour ce niveau.
- [ ] *Par jeu* : liste des jeux → (si le jeu existe sur plusieurs niveaux) choix du niveau.
- [ ] Le catalogue expose les **métadonnées** nécessaires au regroupement (matière, niveau(x),
      identifiant de jeu/mécanique) — sans coupler le front au contenu.
- [ ] Depuis n'importe quel jeu, la flèche retour ramène à l'entrée de navigation d'origine.

**US-s03-2 — Bouton « paramètres » par jeu.**
> En tant qu'accompagnant, je règle la difficulté d'un jeu avant de lancer une partie.

Critères d'acceptation :
- [ ] Chaque jeu a un **bouton paramètres** ouvrant un écran de réglages **propre au jeu**.
- [ ] **Maths** : fenêtre de nombres (min/max) + opérations activées (addition, soustraction,
      multiplication…).
- [ ] **Français (syllabes)** : nombre de **syllabes max** (filtre la liste de mots), etc.
- [ ] Les réglages sont passés au `ContentGenerator` **au démarrage de la session** (via les
      paramètres de `GameConfig`) — la mécanique reste inchangée.
- [ ] Des **valeurs par défaut** reproduisent le comportement actuel (CP/CM1, français CP).
- [ ] Un réglage hors-programme est impossible (bornes cohérentes avec le niveau).
- **Question produit ouverte (à trancher au planning s03)** : les paramètres sont-ils réglés par
  un **adulte/enseignant** (hypothèse de départ) ou par l'enfant ? Sont-ils **persistés** ou
  valables pour la session courante ? Hypothèse s03 : réglage par un adulte, appliqué à la
  session, non persisté (persistance = sprint ultérieur).

---

## Sprint s04 — Déclinaisons du bandit manchot (gains rapides)

Réutilisent la mécanique existante (pur contenu, htmx). Cf. `PROPOSITIONS-jeux.md` et
`2026-07-18-s04-planning.md`.

**US-s04 — 6 nouveaux jeux bandit manchot.**
> En tant qu'élève, je révise de nouvelles notions avec le même jeu de machine à sous.

Critères d'acceptation (pour chacun des 6 jeux) :
- [ ] Un `ContentGenerator` dédié (liste de contenu **vérifiée**) produit des items conformes à la
      compétence visée (réponse correcte + distracteurs plausibles), testé en déterministe.
- [ ] Une entrée de catalogue (matière/niveau/gameKey) apparaît dans les **deux axes** de navigation.
- [ ] `BanditManchotMechanic` **non modifiée** (vérifié par ArchUnit + réutilisation).
- [ ] Les jeux sans réglage n'affichent pas de bouton « Paramètres ».
- [ ] Matrice + fiche(s) mises à jour ; Done pédagogique après démo.

Jeux : compléments à 10 (Maths CP), comparaison de nombres (Maths CP), la première lettre
(Français CP), homophones (Français CM1), nature d'un mot (Français CM1), lexique anglais
(Anglais CM1).

---

## Sprint s05 — Mode session (N questions, sans doublon des réussis) + vocabulaire anglais

Déclenché par la démo s04. Cf. `2026-07-19-s05-planning.md`.

**US-s05-1 — Session de N questions, sans redemander un item réussi.**
Critères : longueur cible réglable par un adulte (défaut 20, bornes 5..50, ≤ vivier distinct) ;
item réussi non reproposé, item raté peut revenir ; écran de fin avec score ; découplage préservé
(la mécanique exclut des clés, sans interpréter le contenu) ; ADR-0006.

**US-s05-2 — Vocabulaire anglais ≥ 50 mots.**
Critères : `AnglaisLexiqueGenerator` ≥ 50 paires FR→EN vérifiées ; session de 50 sans doublon
possible.

---

## Sprint s06 — 2e mécanique : Texte à trous

Première nouvelle mécanique de la bibliothèque. Cf. `2026-07-19-s06-planning.md`, ADR-0007.

**US-s06 — Mécanique « Texte à trous » + jeux.**
Critères : mécanique `TexteATrouMechanic` isolée de bandit manchot (ArchUnit 5), logique de session
partagée dans `domain/mechanic` ; présentation phrase-à-trou + choix du mot ; jeux `conjugaison-cm1`,
`accords-cm1`, `mot-manquant-cp`, `egalite-cp` (contenus vérifiés) ; navigation 2 axes + réglage
« nombre de questions » + écran de fin ; sélection de la vue selon la mécanique ; `mvn verify` vert.

---

## Sprint s07 — Quiz-aventure (3e mécanique) + tests de non-régression UI

Cf. `2026-07-19-s07-planning.md`.

**US-s07-1 — Mécanique Quiz-aventure.** `QuizAventureMechanic` isolée (réutilise SessionEngine),
fragment carte-question + réponses ; jeux questionner-le-monde CP, sciences/histoire/géo CM1
(contenus vérifiés) → ouvre des matières « non couvert ». Navigation 2 axes + mode session réutilisés.

**US-s07-2 — Tests de non-régression UI (MockMvc).** Pour chaque mécanique : écran de fin absent
au start/en cours et présent à la fin ; icône ⚙️ (emoji) sur Paramètres ; bouton Rejouer sans
classe `big` ; `.game-over` sans fond/ombre dans le CSS servi. Décision : test UI = niveau HTML
(pas de Playwright dans cet env) ; rendu pixel = vérif manuelle en démo.

---

## Backlog — sprints suivants (non détaillé à ce stade)

- Web htmx pour le bandit manchot (fragments Thymeleaf, view-models) — sprint s03 visé.
- Persistence H2/JPA de la session de jeu — sprint s02 visé.
- 1re story hors mathématiques (français lecture CP ou orthographe CM1) pour faire bouger la
  matrice de couverture au-delà d'une seule matière — à planifier dès que le socle bandit manchot
  est validé pédagogiquement.

---

## Sprint s17 — Jokers (3 cœurs) + mécaniques « Associer/Classer » & « Memory »

Gros incrément unique couvrant la roadmap A1 (jokers) + A2 (associer/classer) + B (memory). Cf.
`2026-07-22-s17-planning.md`, ADR-0016, DEC-0013, DEC-0014.

### Story S17-1 — « 3 jokers » : réessayer au lieu d'enchaîner sur une erreur  ✅ **LIVRÉE (s17)**
**En tant qu'** enfant, **je veux**, quand je me trompe, **pouvoir réessayer la même question**
(3 jokers / 3 ❤️ par session) plutôt que de passer à une nouvelle, **afin d'**apprendre en corrigeant.
Sur un jeu à clic, la case fausse cliquée devient non cliquable.

- **Compétence programme visée** : transverse (posture d'apprentissage / droit à l'erreur).
- **Ajoutée le** : 2026-07-21, sur demande de l'utilisatrice. **Décisions tranchées** le 2026-07-22
  (cf. DEC-0013) : 3 cœurs fixes, cœur consommé **par clic faux**, bonne réponse après retente = **+10
  plein**, **0 cœur = fin de session**.
- **Détail & conception** : `squad/programme/PLAN-jokers-retenter.md` (✅ implémenté s17).
- **Critères d'acceptation** : une erreur consomme 1 cœur et NE change PAS d'item tant qu'il reste des
  cœurs ; l'option fausse déjà tentée est désactivée ; ❤️ restants visibles ; **à 0 cœur, une erreur
  TERMINE la session** (écran de fin avec le score courant) ; l'écran de fin n'apparaît pas pendant
  les retentes (non-régression UI étendue).
- **Statut** : ✅ **LIVRÉE en s17** (2026-07-22) — Done technique CI à confirmer ; Done pédagogique
  **À VALIDER** (DEC-0013).

### Story S17-2 — 5e mécanique « Associer / Classer » (coup `SubmitAssociations`)  ✅ **LIVRÉE (s17)**
**En tant qu'**élève, **je veux** **apparier** des paires ou **classer** des items par catégorie,
**afin de** réviser des correspondances (lexique, tri du vivant) autrement que par choix ou ordre.

- **Compétence programme visée** : Anglais CM1 — appariement lexique FR↔EN ; Questionner le monde CP —
  distinguer vivant / non-vivant.
- **Critères d'acceptation** : mécanique isolée (ArchUnit — slices dynamiques) réutilisant le
  `SessionEngine` ; coup `SubmitAssociations(Map)` + `Item.encodeAssociations` (encodage trié) ; 2
  jeux `anglais-associer-cm1`, `vivant-non-vivant-cp` **externalisés en YAML dès le départ**
  (`AssocPackLoader`) ; sur erreur : retente plein plateau + 1 cœur (pas de verrouillage partiel
  DEC-0008). Matrice + fiche `FICHE-associer-s17.md` mises à jour.
- **Statut** : ✅ **LIVRÉE en s17** — Done technique CI à confirmer ; Done pédagogique **À VALIDER**
  (DEC-0014, ADR-0016).

### Story S17-3 — 6e mécanique « Memory » (cartes retournées, réutilise `SubmitAssociations`)  ✅ **LIVRÉE (s17)**
**En tant qu'**élève, **je veux** retrouver des paires en retournant des cartes, **afin de** réviser
en sollicitant ma mémoire (quantité↔chiffre, fraction↔représentation, FR↔EN, mot↔image).

- **Compétence programme visée** : Maths CP — quantité↔chiffre ; Maths CM1 — fractions↔représentation ;
  Anglais CM1 — lexique ; Français CP — mot↔image (lecture).
- **Critères d'acceptation** : mécanique isolée réutilisant `SubmitAssociations` ; **îlot JS de flip**
  (`memory.js`, zéro dépendance externe) ; 4 jeux `memory-quantite-cp`, `memory-fraction-cm1`,
  `memory-anglais-cm1`, `memory-mot-image-cp` **externalisés en YAML** (`MemoryPackLoader`). Matrice +
  fiche `FICHE-memory-s17.md` mises à jour.
- **Statut** : ✅ **LIVRÉE en s17** — Done technique CI à confirmer ; Done pédagogique **À VALIDER**
  (DEC-0014, ADR-0016).

---

## Sprint s18 — Raffinements UX de s17 (après démo utilisatrice)

Retouches issues de la démo de s17. **Aucune nouvelle mécanique, aucun nouveau coup, pas de nouvel
ADR** (seul ajout : record domaine pur `AssociationBoard`). Cf. `2026-07-22-s18-planning.md`,
sections « Raffinements s18 » de DEC-0013 et DEC-0014, note ADR-index s18. Validation **groupée avec
s17**.

### Story S18-1 — Le memory consomme un cœur sur une mauvaise paire  ✅ **LIVRÉE (s18)**
**En tant qu'**enfant, **je veux** que le memory suive la même règle de cœurs que les autres jeux,
**afin que** me tromper ait le même enjeu partout.
- **Compétence programme visée** : transverse (droit à l'erreur — DEC-0013).
- **Critères d'acceptation** : chaque **mauvaise paire retournée** coûte **1 cœur** (en s17 : aucun) ;
  à **0 cœur**, une mauvaise paire **termine la session** ; synchro cœurs client/serveur (`memory.js`).
- **Statut** : ✅ **LIVRÉE en s18** — Done technique CI à confirmer ; Done pédagogique **À VALIDER** (DEC-0013).

### Story S18-2 — Verrouillage partiel de « Associer / Classer »  ✅ **LIVRÉE (s18)**
**En tant qu'**enfant, **je veux** que mes cases bien classées restent en place sur une erreur, **afin
de** ne corriger que ce qui est faux (comme au glisser-déposer).
- **Compétence programme visée** : QLM CP vivant/non-vivant · Anglais CM1 appariement (mêmes qu'en s17).
- **Critères d'acceptation** : sur erreur, **seules les cases mal placées** reviennent en réserve, les
  éléments bien classés restent **verrouillés** (alignement DEC-0008) ; via le record domaine pur
  `AssociationBoard` (calqué sur `ArrangementBoard`) ; ArchUnit **5/5 inchangé**. Scope explicitement
  différé en s17, maintenant repris.
- **Statut** : ✅ **LIVRÉE en s18** — Done technique CI à confirmer ; Done pédagogique **À VALIDER** (DEC-0014).

### Story S18-3 — Écran de fin « Essaye encore »  ✅ **LIVRÉE (s18)**
**En tant qu'**enfant, **je veux** un titre de fin qui distingue la victoire de l'échec par manque de
cœurs, **afin de** comprendre pourquoi la partie s'est arrêtée.
- **Compétence programme visée** : transverse (DEC-0013).
- **Critères d'acceptation** : titre **« Bravo »** sur victoire (toutes les questions traitées) ;
  **« Essaye encore »** quand la session s'arrête faute de cœurs (0 cœur + erreur).
- **Statut** : ✅ **LIVRÉE en s18** — Done technique CI à confirmer ; Done pédagogique **À VALIDER** (DEC-0013).

### Story S18-4 — Score max correct en fin par cœurs épuisés  ✅ **LIVRÉE (s18)**
**En tant qu'**enfant, **je veux** un score final juste quand la partie s'arrête sur une erreur,
**afin que** la question ratée soit comptée.
- **Compétence programme visée** : transverse (DEC-0013).
- **Critères d'acceptation** : en fin par cœurs épuisés, le **dénominateur** compte la question **en
  cours** (répondues + 1) — `SessionEngine.onWrong` incrémente `answered` **avant** `FINISHED`.
- **Statut** : ✅ **LIVRÉE en s18** — Done technique CI à confirmer ; Done pédagogique **À VALIDER** (DEC-0013).

### Story S18-5 — Memory quantités CP en faces de dés  ✅ **LIVRÉE (s18)**
**En tant qu'**élève de CP, **je veux** des faces de dés plutôt que des points, **afin de** relier
plus vite une quantité à son chiffre.
- **Compétence programme visée** : Maths CP — associer une quantité à son chiffre (Memory, mêmes qu'en s17).
- **Critères d'acceptation** : `memory-quantite-cp` affiche des **faces de dés** (⚀⚁⚂⚃⚄⚅) au lieu de
  points ; jeu porté à **6 paires** (changement de contenu YAML).
- **Statut** : ✅ **LIVRÉE en s18** — Done technique CI à confirmer ; Done pédagogique **À VALIDER** (DEC-0014).

### Story S18-6 — Memory anglais CM1 avec images côté français  ✅ **LIVRÉE (s18)**
**En tant qu'**élève de CM1, **je veux** associer une **image** à un mot anglais, **afin de** m'appuyer
sur le sens plutôt que sur le mot français écrit.
- **Compétence programme visée** : Anglais CM1 — lexique / appariement (Memory, mêmes qu'en s17).
- **Critères d'acceptation** : `memory-anglais-cm1` remplace le côté français par des **images**
  (🐶🐱🏠🐟☀️🍎) associées au mot anglais (changement de contenu YAML).
- **Statut** : ✅ **LIVRÉE en s18** — Done technique CI à confirmer ; Done pédagogique **À VALIDER** (DEC-0014).

---

## Sprint s19 — Compréhension & grammaire CM1 + cœurs adaptés (contenu + polish)

Contenu + polish, **aucune nouvelle mécanique**. Réalise IDEE-1 et IDEE-2 (ci-dessous, marquées
livrées). Cf. `2026-07-22-s19-planning.md`, DEC-0015, note ADR-index s19. Enchaîné avec s20, sans
démo intermédiaire — validation **groupée s19 + s20** à venir.

### Story S19-1 — Capital de jokers dépendant de la mécanique (memory : 6 cœurs)  ✅ **LIVRÉE (s19)**
**En tant qu'**enfant jouant au **memory**, **je veux** un capital de **6 cœurs** (au lieu de 3),
**afin de** ne pas voir la partie s'arrêter trop vite alors que retourner des cartes produit
mécaniquement plus d'essais/erreurs qu'un jeu à clic. **Réalise IDEE-1.**

- **Compétence programme visée** : transverse (posture d'apprentissage / droit à l'erreur — DEC-0013).
- **Critères d'acceptation** : le capital de cœurs par session dépend de la mécanique
  (`SessionEngine(mechanicId, defaultJokers)`) ; le **memory démarre à 6 cœurs** (`MemoryMechanic.JOKERS = 6`) ;
  les autres mécaniques restent à **3** (DEC-0013 inchangé) ; l'affichage `❤️ × jokers` reflète le
  capital réel ; non-régression de la fin de session à 0 cœur.
- **Statut** : ✅ **LIVRÉE en s19** — Done technique CI à confirmer ; Done pédagogique **À VALIDER** (DEC-0015).

### Story S19-2 — Agrandir les dés du « Memory des quantités » CP  ✅ **LIVRÉE (s19)**
**En tant qu'**élève de CP, **je veux** des faces de dés **plus grandes** sur le memory des
quantités, **afin de** lire facilement la quantité malgré ma vue de jeune enfant. **Réalise IDEE-2.**

- **Compétence programme visée** : Maths CP — associer une quantité à son chiffre (`memory-quantite-cp`,
  mêmes qu'en s17/s18, pas de nouvelle couverture).
- **Critères d'acceptation** : les faces de dés de `memory-quantite-cp` sont affichées à une taille
  nettement plus lisible via une variante CSS `.memory-des .memory-face` (classe conditionnelle sur
  l'entryId) ; pas de débordement du plateau ; aucun impact sur les autres jeux memory ; aucun
  changement de contenu ni de domaine.
- **Statut** : ✅ **LIVRÉE en s19** — Done technique CI à confirmer ; Done pédagogique **À VALIDER** (DEC-0015).

### Story S19-3 — Compréhension de texte CM1 (quiz-aventure)  ✅ **LIVRÉE (s19)**
**En tant qu'**élève de CM1, **je veux** lire un court texte puis répondre à une question, **afin de**
travailler la compréhension de lecture.

- **Compétence programme visée** : Français CM1 — compréhension d'un texte narratif court (questions
  explicites/implicites) — comble un trou `non couvert` de la matrice.
- **Critères d'acceptation** : nouveau jeu `content/quiz/cm1-comprehension.yml` (gameKey `comprehension`),
  **8 items** « lis un court texte + question » ; réponse unique + distracteurs plausibles ; **externalisé
  YAML auto-enregistré** par `QuizPackLoader` (aucun code) ; matrice + `FICHE-comprehension-s19.md` à jour.
- **Statut** : ✅ **LIVRÉE en s19** — Done technique CI à confirmer ; Done pédagogique **À VALIDER** (DEC-0015).

### Story S19-4 — Nom, verbe ou adjectif CM1 (associer/classer)  ✅ **LIVRÉE (s19)**
**En tant qu'**élève de CM1, **je veux** classer des mots selon leur nature (nom / verbe / adjectif),
**afin de** consolider ma grammaire par le tri plutôt que par le simple choix.

- **Compétence programme visée** : Français CM1 — grammaire (reconnaître la nature des mots).
- **Critères d'acceptation** : nouveau jeu `content/associer/cm1-nature-mots.yml` (gameKey `classer`),
  **3 puzzles** de catégorisation ; **externalisé YAML auto-enregistré** par `AssocPackLoader` ; décline
  la mécanique associer/classer de s17 (catégorisation, comme `vivant-non-vivant-cp`) ; jokers cohérents
  (DEC-0013) ; matrice + `FICHE-comprehension-s19.md` à jour.
- **Statut** : ✅ **LIVRÉE en s19** — Done technique CI à confirmer ; Done pédagogique **À VALIDER** (DEC-0015).

---

## Sprint s20 — La monnaie (Grandeurs et mesures, pur contenu YAML)

Ouvre le domaine **Grandeurs et mesures — monnaie** (jusqu'ici absent) par du **pur contenu**,
sur les mécaniques existantes (quiz-aventure, associer/classer). **Aucun code, aucun nouvel ADR.**
Cf. `2026-07-22-s20-planning.md`, DEC-0016, note ADR-index s20. Validation **groupée avec s19**.

### Story S20-1 — Monnaie CP (quiz-aventure)  ✅ **LIVRÉE (s20)**
**En tant qu'**élève de CP, **je veux** compter des euros et rendre la monnaie sur de petites sommes,
**afin de** relier les nombres à l'argent du quotidien.

- **Compétence programme visée** : Maths CP — Grandeurs et mesures — monnaie (compter des euros,
  petites sommes, rendre la monnaie).
- **Critères d'acceptation** : `content/quiz/cp-monnaie.yml` (gameKey `monnaie`) **externalisé YAML
  auto-enregistré** par `QuizPackLoader` ; montants et rendus adaptés au CP ; matrice + `FICHE-monnaie-s20.md` à jour.
- **Statut** : ✅ **LIVRÉE en s20** — Done technique CI à confirmer ; Done pédagogique **À VALIDER** (DEC-0016).

### Story S20-2 — Monnaie CM1 (quiz-aventure)  ✅ **LIVRÉE (s20)**
**En tant qu'**élève de CM1, **je veux** rendre la monnaie et convertir euros/centimes, **afin de**
maîtriser la monnaie sur des sommes plus larges.

- **Compétence programme visée** : Maths CM1 — Grandeurs et mesures — monnaie (rendre la monnaie,
  conversions euros/centimes).
- **Critères d'acceptation** : `content/quiz/cm1-monnaie.yml` (gameKey `monnaie`) **externalisé YAML
  auto-enregistré** ; conversions et rendus corrects (contenu vérifié) ; matrice + fiche à jour.
- **Statut** : ✅ **LIVRÉE en s20** — Done technique CI à confirmer ; Done pédagogique **À VALIDER** (DEC-0016).

### Story S20-3 — Euros ↔ centimes CM1 (associer/classer)  ✅ **LIVRÉE (s20)**
**En tant qu'**élève de CM1, **je veux** apparier une somme en euros à sa valeur en centimes, **afin de**
consolider la conversion par l'appariement.

- **Compétence programme visée** : Maths CM1 — Grandeurs et mesures — monnaie (associer une somme en
  euros à sa valeur en centimes).
- **Critères d'acceptation** : `content/associer/cm1-monnaie-centimes.yml` (gameKey `associer`)
  **externalisé YAML auto-enregistré** par `AssocPackLoader` ; appariement euros ↔ centimes correct
  (comme `anglais-associer-cm1`) ; jokers cohérents (DEC-0013) ; matrice + fiche à jour.
- **Statut** : ✅ **LIVRÉE en s20** — Done technique CI à confirmer ; Done pédagogique **À VALIDER** (DEC-0016).

---

## Sprint s21 — Puzzle (7e mécanique) + jokers réglables + navigation par matière + externalisation frise/fractions + déclinaisons CP

Gros incrément **parallèle** (5 chantiers, contrats gelés en amont), couvrant la roadmap **B**
(puzzle), **A3** (cœurs réglables), **E** (navigation par matière), **D** (externalisation
frise/fractions — dette remboursée) et **A2** (déclinaisons associer/classer). Cf.
`2026-07-22-s21-planning.md`, ADR-0017, DEC-0017, DEC-0018.

### Story S21-1 — Déclinaisons « Associer / Classer » Français CP  ✅ **LIVRÉE (s21)**
**En tant qu'**élève de CP, **je veux** apparier chaque lettre majuscule à sa minuscule et classer
des noms selon le / la, **afin de** consolider le principe alphabétique et le genre du nom.
- **Compétence programme visée** : Français CP — principe alphabétique (correspondance
  majuscule ↔ minuscule) ; Français CP — genre du nom (masculin / féminin).
- **Critères d'acceptation** : 2 jeux **externalisés YAML** auto-enregistrés par `AssocPackLoader` —
  `cp-majuscule-minuscule` (**appariement** A ↔ a) et `cp-masculin-feminin` (**classer** le / la) ;
  correspondance correcte unique vérifiée (encodage canonique trié, coup `SubmitAssociations`) ;
  jokers cohérents (DEC-0013 / DEC-0017) ; matrice + `FICHE-associer-s17.md` (ou fiche dédiée) à jour.
- **Statut** : ✅ **LIVRÉE en s21** — Done technique CI à confirmer ; Done pédagogique **À VALIDER** (DEC-0018).

### Story S21-2 — Jokers paramétrables via YAML + réglage adulte  ✅ **LIVRÉE (s21)**
**En tant qu'**adulte accompagnant, **je veux** régler le **nombre de cœurs** d'une session (et le
définir par jeu dans le contenu), **afin d'**adapter le droit à l'erreur à l'enfant et à la mécanique.
- **Compétence programme visée** : transverse (posture d'apprentissage / droit à l'erreur — DEC-0013).
  **Réalise la réserve « cœurs réglables » (roadmap A3).**
- **Critères d'acceptation** : champ **`jokers` optionnel** dans l'en-tête YAML, lu par
  `SessionEngine` (`settings["jokers"]`) ; le **capital 6 du memory provient désormais du YAML** (plus
  de constante Java) ; réglage adulte « nombre de cœurs » dans l'écran paramètres (`JokersSettingsView`,
  param POST `jokers`) appliqué à la session ; **défaut 3 préservé** (DEC-0013) si ni YAML ni réglage
  ne précisent ; non-régression de la fin de session à 0 cœur.
- **Statut** : ✅ **LIVRÉE en s21** — Done technique CI à confirmer ; Done pédagogique **À VALIDER** (DEC-0017).

### Story S21-3 — Externalisation frise + fractions (dette remboursée)  ✅ **LIVRÉE (s21)**
**En tant que** mainteneuse du contenu, **je veux** que les événements de la frise et le pool de
fractions comparées vivent dans des YAML, **afin de** les enrichir sans dev (fin du contenu en dur).
- **Compétence programme visée** : transverse (technique — **aucune nouvelle couverture** ; frise
  chronologique CM1 et « Compare les fractions » CM1 restent `couvert-validé`).
- **Critères d'acceptation** : `FriseChronologiqueGenerator` et `CompareFractionsGenerator`
  **reçoivent leur liste par constructeur**, chargée depuis `content/frise/*.yml` et
  `content/fractions/*.yml` (nouveaux loaders, même patron que ADR-0009→0011) ; **comportement
  constant** (non-régression frise + compare-fractions) ; ArchUnit 5/5 ; pas de nouvel ADR.
- **Statut** : ✅ **LIVRÉE en s21** — Done technique CI à confirmer ; Done pédagogique **À VALIDER** (DEC-0018).
- **Dette** : la dette **« générateurs = listes finies en dur » (frise, fractions)** de la roadmap §D
  est **remboursée (s21)** — plus aucun contenu à liste finie n'est codé en Java.

### Story S21-4 — Navigation « par matière » (3e axe)  ✅ **LIVRÉE (s21)**
**En tant qu'**enfant ou accompagnant, **je veux** parcourir les jeux **par matière**, **afin d'**
arriver au jeu voulu par un 3e chemin en plus de « par niveau » et « par jeu ».
- **Compétence programme visée** : sans objet (fonctionnalité de navigation, pas de contenu).
- **Critères d'acceptation** : pages `/matieres` (liste des matières couvertes) et
  `/matiere/{matiere}` (jeux d'une matière, tous niveaux) reposant sur `CatalogueQuery.matieres()` et
  `byMatiere()` (catalogue enrichi, ADR-0005) ; axes « par niveau » et « par jeu » conservés ; retour
  vers l'axe d'origine ; pur read-model, aucun changement de domaine (pas de nouvel ADR).
- **Statut** : ✅ **LIVRÉE en s21** — Done technique CI à confirmer ; Done pédagogique **À VALIDER** (DEC-0017).

### Story S21-5 — 7e mécanique « Puzzle / assemblage » — reconstituer un mot  ✅ **LIVRÉE (s21)**
**En tant qu'**élève, **je veux** reconstituer un mot en remettant ses lettres dans l'ordre (avec un
indice image ou définition), **afin de** travailler l'orthographe active et le vocabulaire.
- **Compétence programme visée** : Français CP — écriture / lecture (reconstituer un mot, indice
  image) ; Français CM1 — vocabulaire / orthographe lexicale (reconstituer un mot, indice définition).
- **Critères d'acceptation** : mécanique **isolée** `mechanics/puzzle` (ArchUnit — slices dynamiques)
  réutilisant le coup **`SubmitArrangement`** (comme memory réutilise `SubmitAssociations`, ADR-0017),
  **sans nouveau coup** ; générateur `MotPuzzleGenerator` (lettres à remettre dans l'ordre + indice) ;
  2 jeux **externalisés YAML** `content/puzzle/cp-mots-images.yml` (indice image) et
  `content/puzzle/cm1-mots.yml` (indice définition) ; **îlot JS `puzzle.js`** (assemblage lettres,
  zéro dépendance externe ; l'ordre attendu n'est pas révélé dans le DOM) ; matrice +
  `FICHE-puzzle-s21.md` à jour.
- **Statut** : ✅ **LIVRÉE en s21** — Done technique CI à confirmer ; Done pédagogique **À VALIDER** (DEC-0017, ADR-0017).

---

## Sprint s22 — Filtres « Par jeu » + 8e mécanique « Droite graduée »

Deux livrables : ergonomie (filtres de la liste « Par jeu », réalise **IDEE-3**) + une **8e
mécanique** (droite graduée, roadmap §B). Cf. `2026-07-22-s22-planning.md`, ADR-0018, DEC-0019.

### Story S22-1 — Filtres « Par jeu » (niveau / matière)  ✅ **LIVRÉE (s22)**
**En tant qu'**enfant (ou accompagnant), **je veux** filtrer la liste « Par jeu » par **niveau**
(CP / CM1) et/ou par **matière** grâce à des **boutons**, **afin de** réduire vite la liste à ce qui
m'intéresse sans changer d'écran. **Réalise IDEE-3.**

- **Compétence programme visée** : transverse (ergonomie / navigation — **pas de contenu**).
- **Critères d'acceptation** : sur l'écran **« Par jeu »** (`/jeux`), une **barre de boutons** de
  filtre niveau (Tous / CP / CM1) et matière (Toutes / Mathématiques / Français / …) ; l'état actif
  est visible ; cliquer un filtre affiche **le sous-ensemble** correspondant ; « Tous / Toutes »
  réinitialise ; filtres **combinables** (niveau ET matière) ; sous-ensemble vide = message clair ;
  **filtrage serveur par query params** (`/jeux?niveau=&matiere=`), pur **read-model / vue**, **aucun
  changement de domaine** ; libellés au format contextuel (cohérent s21).
- **Statut** : ✅ **LIVRÉE en s22** — Done technique CI à confirmer ; Done pédagogique **À VALIDER**
  (DEC-0019).

### Story S22-2 — 8e mécanique « Droite graduée » — placer un nombre entier  ✅ **LIVRÉE (s22)**
**En tant qu'**élève, **je veux** placer un nombre sur une droite graduée (0 → N) en cliquant la
bonne graduation, **afin de** travailler le sens du nombre (repérage, ordre de grandeur).

- **Compétence programme visée** : Maths CP — situer / placer un nombre sur une droite graduée
  (0 → 20) ; Maths CM1 — situer / placer un nombre sur une droite graduée (0 → 100).
- **Critères d'acceptation** : mécanique **isolée** `mechanics/droitegraduee` (ArchUnit — slices
  dynamiques) réutilisant le coup **`SpinAndPick`** (comme puzzle réutilise `SubmitArrangement`,
  memory `SubmitAssociations` — ADR-0018), **sans nouveau coup** ; générateur `DroiteGradueeGenerator`
  **combinatoire** (bornes câblées dans `BeansConfig`) ; 2 jeux `droite-graduee-cp` (0 → 20) et
  `droite-graduee-cm1` (0 → 100) ; **îlot JS de vue `droitegraduee`** (droite + clic de graduation +
  tick grisé, zéro dépendance externe ; la bonne graduation n'est pas révélée dans le DOM) ; **jokers
  automatiques** (tick faux = 1 cœur perdu + tick grisé ; 0 cœur + tick faux = fin — DEC-0013 /
  DEC-0017) ; matrice + `FICHE-droite-graduee-s22.md` à jour.
- **Statut** : ✅ **LIVRÉE en s22** — Done technique CI à confirmer ; Done pédagogique **À VALIDER**
  (DEC-0019, ADR-0018).

---

## Idées / à venir (non planifiées — aucun sprint assigné)

> **Note (2026-07-22)** : IDEE-1 et IDEE-2 (ci-dessous) ont été **livrées en s19** (stories S19-1 et
> S19-2 ; DEC-0015). Elles sont conservées ici pour la traçabilité de leur origine (idées remontées
> après la validation s17 + s18).

### Story IDEE-1 — Jokers adaptés au type de jeu (memory : 6 cœurs)  ✅ **LIVRÉE (s19 — voir S19-1)**
**En tant qu'**enfant jouant au **memory**, **je veux** un capital de cœurs plus large (**6 cœurs**
au lieu de 3), **afin de** ne pas voir la partie s'arrêter trop vite alors que le memory génère
mécaniquement plus d'essais/erreurs qu'un jeu à clic.

- **Compétence programme visée** : transverse (posture d'apprentissage / droit à l'erreur) — même
  registre que DEC-0013, sans nouvelle compétence de contenu.
- **Justification** : depuis s18 le memory consomme un cœur par mauvaise paire (DEC-0013) ; or
  retourner des cartes produit naturellement plus de tentatives infructueuses qu'un simple clic.
  Un capital fixe de 3 est trop punitif pour cette mécanique. **Idée** : rendre le nombre de cœurs
  **dépendant de la mécanique / du jeu** (memory → 6) plutôt qu'une constante globale.
- **Critères d'acceptation (à affiner)** : le capital de cœurs par session dépend de la mécanique
  (ou est déclaré par le jeu) ; les jeux de type **memory** démarrent avec **6 cœurs** ; les autres
  mécaniques restent à 3 (comportement DEC-0013 inchangé) ; l'affichage `❤️ × jokers` reflète le
  capital réel ; non-régression de la fin de session à 0 cœur.
- **Lien décision** : `squad/decisions/produit/DEC-0013-jokers-trois-coeurs-retente.md` (réserve
  « rendre les cœurs réglables / dépendants du jeu » déjà laissée ouverte) ; livrée par DEC-0015.
- **Statut** : ✅ **LIVRÉE en s19** (story S19-1) — `SessionEngine(mechanicId, defaultJokers)`,
  `MemoryMechanic.JOKERS = 6`. Done pédagogique **À VALIDER**.

### Story IDEE-2 — Agrandir les dés du « Memory des quantités » CP  ✅ **LIVRÉE (s19 — voir S19-2)**
**En tant qu'**élève de CP, **je veux** des faces de dés **plus grandes** sur le memory des
quantités, **afin de** lire facilement la quantité malgré ma vue de jeune enfant.

- **Compétence programme visée** : Maths CP — associer une quantité à son chiffre (jeu
  `memory-quantite-cp`, Memory) — même compétence qu'en s17/s18, pas de nouvelle couverture.
- **Justification** : s18 a introduit les faces de dés (⚀⚁⚂⚃⚄⚅) pour `memory-quantite-cp`
  (DEC-0014) ; à la taille d'affichage actuelle des cartes, les points des faces restent petits
  pour de jeunes lecteurs. **Ajustement purement CSS** (agrandir `.memory-carte` / `.memory-face`
  pour ce jeu), sans changement de contenu ni de domaine.
- **Critères d'acceptation (à affiner)** : les faces de dés du jeu `memory-quantite-cp` sont
  affichées à une **taille nettement plus lisible** (via CSS `.memory-carte` / `.memory-face`) ;
  la mise en page du plateau memory reste correcte (pas de débordement) ; aucun impact sur les
  autres jeux memory.
- **Lien décision** : `squad/decisions/produit/DEC-0014-associer-classer-memory-jeux-externalises.md`
  (raffinement s18 : faces de dés du memory quantités CP) ; livrée par DEC-0015.
- **Statut** : ✅ **LIVRÉE en s19** (story S19-2) — variante CSS `.memory-des .memory-face`. Done
  pédagogique **À VALIDER**.

### Story IDEE-3 — Boutons de filtre (niveau / matière) dans les listes de jeux  ✅ **LIVRÉE (s22 — voir S22-1)**
**En tant qu'**enfant (ou accompagnant), **je veux** filtrer une liste de jeux par **niveau**
(CP / CM1) et/ou par **matière** grâce à des **boutons de sélection**, **afin de** réduire vite la
liste à ce qui m'intéresse sans changer d'écran.

- **Compétence programme visée** : transverse (ergonomie / navigation — pas de contenu).
- **Justification** : depuis s21, l'axe **« Par jeu »** liste **tous** les jeux à plat (format
  « Matière Niveau — Nom ») ; la liste est longue. Des filtres croisés évitent de repasser par un
  autre axe. Exemple demandé (utilisatrice, 2026-07-22) : *Par jeu → bouton **CM1** → sous-ensemble
  des jeux CM1* ; idem **CP** ; idem **par matière**. Les filtres se **combinent** (ex. CM1 +
  Mathématiques) et recoupent les axes existants (par niveau, par matière) qui restent disponibles.
- **Critères d'acceptation (à affiner)** :
  - Sur l'écran **« Par jeu »**, une barre de **boutons de filtre** : niveau (Tous / CP / CM1) et
    matière (Toutes / Mathématiques / Français / …) ; l'état actif est visible.
  - Cliquer un filtre affiche **le sous-ensemble** correspondant ; « Tous / Toutes » réinitialise.
  - Filtres **combinables** (niveau ET matière) ; un sous-ensemble vide affiche un message clair.
  - Filtrage **pur read-model / front** (htmx ou filtre côté serveur), **aucun changement de
    domaine** ; les libellés restent au format contextuel (cf. affichage cohérent des jeux, s21).
  - Idéalement mutualisé pour être réutilisable sur les autres listes (par niveau / par matière).
- **Statut** : ✅ **LIVRÉE en s22** (story S22-1) — filtrage **serveur par query params**
  (`/jeux?niveau=&matiere=`), boutons niveau / matière **combinables** et **réinitialisables**. Done
  pédagogique **À VALIDER** (DEC-0019).
