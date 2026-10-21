# Fiche de conformite - bandit-manchot-negation-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-negation.yaml` (24 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-negation-ce1`, titre servi *"Des phrases qui disent non"*, consigne servie *"Lis
  bien, puis choisis la bonne réponse."*, `nombreDeManches: 10`.
- **Ligne declaree** : `FRA-CE1-11`. **Story** : `S57-BR-FRA-CE1-11`.
- **Competence programme visee** : `squad/programme/francais/competences-ce1.md`, `FRA-CE1-11`, *"Passer d'une phrase
  affirmative a une phrase negative (ne... pas) et inversement."*
- **Conditions opposables** : `NEG-CE1-1` a `NEG-CE1-3` (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`) ; communes de
  `arbitrage-nadia-preparation-palier-3-ce1.md` 4.1 et 4.2 ; corrections des dix indices de `lecture-nadia-palier-3.md`,
  section 3.
- **Statut global** : **VALIDE SOUS RESERVE**, prononce le 2026-10-21 par `nadia-prof` (s57, palier 3). Fiche ouverte par ce
  verdict.
- **Derniere mise a jour** : 2026-10-21.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-21 (`nadia-prof`, s57, palier 3) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce** sur `round-two` a `8407f73f`. `camille-po` peut passer `FRA-CE1-11` a `couvert-valide` et faire
une story de la reserve ci-dessous, non bloquante.

**Ce qui fonde le verdict** : `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/verdict-nadia-palier-3-ce1.md`, sections
1, 2 et 3.3. Audit en partie degrade, declare : jeu du pilote d'`ines-design` (`releve-ecran-ines-palier-3.md`, commit
`56ce720e`), 24 items sur 24, lu en texte DOM et sur une capture ; jar non lance par moi.

**Verifie** : lecture complete (`7301cbbc`) ; 14 items vers la negative, 10 vers l'affirmative, `ne ... pas` seul, present
partout (`NEG-CE1-1`) ; elision juste (`NEG-CE1-2`) ; distracteurs aux memes mots hors de la negation (`NEG-CE1-3`) ; phrase
citee entre chevrons, insecables justes ; trois indices qui disent ou regarder, plus aucun *"a lieu"* ; revelation de la bonne
reponse apres deux erreurs.

**Reserve, non bloquante** : a la revelation, reponse attendue coupee au pli, item 13 a 744 x 551 (*"Yanis ne range pas ses"*
visible, *"crayons."* dessous) ; item 18 a 720 avec barre (`V3` de `veto-nadia-gabarit-pli.md`). La bonne reponse reste
visible entiere parmi les propositions.

**Je reste la seule garde** de ce qu'une police et un appareil reels changent au pli.

**Signe** : `nadia-prof`, 2026-10-21.
