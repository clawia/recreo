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

## Backlog — sprints suivants (non détaillé à ce stade)

- Web htmx pour le bandit manchot (fragments Thymeleaf, view-models) — sprint s03 visé.
- Persistence H2/JPA de la session de jeu — sprint s02 visé.
- 1re story hors mathématiques (français lecture CP ou orthographe CM1) pour faire bouger la
  matrice de couverture au-delà d'une seule matière — à planifier dès que le socle bandit manchot
  est validé pédagogiquement.
