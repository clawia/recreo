# Fiche de conformite - bandit-manchot-nombres-jusqu-a-1000-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-nombres-jusqu-a-1000.yaml` (22 items, type
  `question`).
- **Entree de catalogue** : `bandit-manchot-nombres-jusqu-a-1000-ce1`, titre servi *"Les nombres jusqu'à 1000"*, consigne
  servie *"Lis bien les nombres, puis choisis la bonne réponse."*, `nombreDeManches: 10`.
- **Ligne declaree** : `MAT-CE1-01`. **Story** : `S57-BR-MAT-CE1-01`.
- **Competence programme visee** : `squad/programme/mathematiques/competences-ce1.md`, `MAT-CE1-01`, *"Dénombrer, lire, écrire
  et décomposer les nombres jusqu'à 1000 (unités, dizaines, centaines)."*
- **Conditions opposables** : `NUM-CE1-1` a `NUM-CE1-7` (`ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`, 2.2) ;
  `arbitrage-nadia-preparation-palier-3-ce1.md` 4.1 et 4.3 ; correction des enonces 15, 16, 19 (`lecture-nadia-palier-3.md`,
  section 4).
- **Statut global** : **VALIDE**, plafond `en cours`, prononce le 2026-10-21 par `nadia-prof` (s57, palier 3). Fiche ouverte
  par ce verdict.
- **Derniere mise a jour** : 2026-10-21.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-21 (`nadia-prof`, s57, palier 3) : **VALIDE**, plafond `en cours`

**Done pedagogique prononce** sur `round-two` a `8407f73f`. `camille-po` peut passer `MAT-CE1-01` a `en cours`, jamais au-dela.
La cellule porte deja la mention de `NUM-CE1-2` mot pour mot (matrice, amendement du 2026-10-21) : *"le verbe 'denombrer',
premier de cette ligne, n'est travaille par aucun jeu ; seuls 'lire', 'ecrire' et 'decomposer' le sont"*.

**Ce qui fonde le verdict** : `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/verdict-nadia-palier-3-ce1.md`, sections
1, 2 et 3.4. Audit en partie degrade, declare : jeu du pilote d'`ines-design` (`releve-ecran-ines-palier-3.md`, commit
`56ce720e`), 22 items sur 22 et une partie complete jusqu'a l'ecran de fin ; jar non lance par moi.

**Verifie** : lecture complete (`7301cbbc`) ; 21 items a trois chiffres et l'item 1000 (`NUM-CE1-1`) ; 22 bonnes reponses et 44
distracteurs exacts, distingues par la valeur ; zero intercalaire et tranche 60 a 99 servis ; *"Quel nombre a ..."* en 15, 16,
19 ; **le mot *decomposer* lu par l'enfant : 0 occurrence sur 516 ecrans** (refus ecrit d'avance non atteint) ; 0 item sous le
pli apres une erreur.

**Aucune reserve.**

**Je reste la seule garde** de ce que le paquet ne serve pas plus que sa mention ne dit : un test voit un champ, pas un verbe
de la ligne.

**Signe** : `nadia-prof`, 2026-10-21.
