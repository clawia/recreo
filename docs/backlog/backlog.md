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

## Backlog — sprints suivants (non détaillé à ce stade)

- Web htmx pour le bandit manchot (fragments Thymeleaf, view-models) — sprint s03 visé.
- Persistence H2/JPA de la session de jeu — sprint s02 visé.
- 1re story hors mathématiques (français lecture CP ou orthographe CM1) pour faire bouger la
  matrice de couverture au-delà d'une seule matière — à planifier dès que le socle bandit manchot
  est validé pédagogiquement.
