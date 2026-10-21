# Fiche de conformite - bandit-manchot-inference-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-inference.yaml` (20 items, type `question`, texte
  support).
- **Entree de catalogue** : `bandit-manchot-inference-ce1`, titre servi *"Ce que le texte ne dit pas"*, consigne servie *"Lis
  le petit texte, puis réponds à la question."*, `nombreDeManches: 10`.
- **Ligne declaree** : `FRA-CE1-06`. **Story** : `S57-BR-FRA-CE1-06`.
- **Competence programme visee** : `squad/programme/francais/competences-ce1.md`, `FRA-CE1-06`, *"Faire une inférence simple a
  partir d'un texte court (une information non écrite : un sentiment, un lieu, une cause)."*
- **Conditions opposables** : `INF-CE1-1` a `INF-CE1-3` (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`) ; communes de
  `arbitrage-nadia-preparation-palier-3-ce1.md` 4.1 et 4.2 ; corrections de `lecture-nadia-palier-3.md` (item 19 ; item 14 en
  S3).
- **Statut global** : **VALIDE SOUS RESERVE**, prononce le 2026-10-21 par `nadia-prof` (s57, palier 3). Fiche ouverte par ce
  verdict.
- **Derniere mise a jour** : 2026-10-21.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-21 (`nadia-prof`, s57, palier 3) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce** sur `round-two` a `8407f73f` (contenu de `78ab8a5b`). `camille-po` peut passer `FRA-CE1-06` a
`couvert-valide` et faire des stories des reserves ci-dessous, non bloquantes.

**Ce qui fonde le verdict** : `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/verdict-nadia-palier-3-ce1.md`, sections
1, 2 et 3.1. Audit en partie degrade, declare : jeu du pilote d'`ines-design` (`releve-ecran-ines-palier-3.md`, commit
`56ce720e`), 20 items sur 20, lu en texte DOM et sur 9 captures ; jar non lance par moi.

**Verifie** : lecture complete (`7301cbbc`) ; 20 bonnes reponses exactes et jamais ecrites dans le texte (`INF-CE1-2`) ;
sentiment 7, lieu 6, cause 7, au plus deux faits relies (`INF-CE1-1`) ; distracteurs des familles *a* et *b*, item 19
vraisemblable (`INF-CE1-3`) ; indice qui dit ou regarder ; revelation de la bonne reponse apres deux erreurs.

**Reserves, non bloquantes** :

1. `-06` 14 apres une erreur, trois rangees de propositions (2 ordres sur 6) : verdict et indice coupes au pli a 744 x 551
   (`veto-nadia-gabarit-pli.md`, section 3 et `V6` : *"a traiter"*).
2. A la revelation, reponse attendue sous le pli ou coupee, items 14, 16, 19, 20 (`V3`).
3. **Neuve, datee du 2026-10-21** : l'enonce de 14 se coupe dans *"au-dessus"* (*"au-"* en fin de ligne). Exigence : a
   744 x 551, aucun mot a trait d'union d'un enonce coupe en fin de ligne. Levier non choisi par moi.

**Je reste la seule garde** de la coupure d'un mot compose en fin de ligne et de ce qu'une police et un appareil reels changent
au pli : aucun test ne les lit, aucun appareil n'a ete joue.

**Signe** : `nadia-prof`, 2026-10-21.
