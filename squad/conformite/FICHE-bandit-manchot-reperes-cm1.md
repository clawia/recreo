# Fiche de conformite - bandit-manchot-reperes-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cm1-reperes.yaml`,
  livre, `type: question`, 16 items, story `S20-2` (`docs/backlog/backlog.md`). Aucune matiere
  premiere identifiee dans `contenu-repris/` sur ce repere precis : le paquet est integralement
  neuf.
- **Statut global** : **REFUSE par `nadia-prof` le 2026-08-26** (`hs-nadia-arbitrages`, commit
  `9c8aa7b` - fiche faisant foi, non encore fusionnee dans `round-two` a la date de cette
  correction), deux motifs : le pli (verdict et indice entierement sous le pli sur 16 items sur
  16, plus grand bouton a 161 px sur trois lignes) et `REP-4` non tenue (quatre libelles ne
  pouvaient pas porter quatre reperes plus le cas correct - il en fallait cinq). Les deux causes
  racines sont dans sa propre specification `B.3`, pas dans le travail livre ; les seize phrases ne
  sont pas en cause ("la matiere est saine"). **Correction deposee ci-dessous par `lea-dev` le
  meme jour**, sur un worktree reparti de `round-two` a jour. Le retrait du catalogue qu'impose
  `DEC-0045` section 3 sur un `REFUSE` releve de `camille-po`/`sofia-sm`, pas de ce commit :
  `catalogue.yaml` n'est pas touche ici.
- **Correction du 2026-08-26 (`lea-dev`)** : un cinquieme libelle, "Point d'interrogation",
  distinct de "Point final" - les items 4-6 et 10-12 montrent desormais les deux libelles de point
  ensemble, forcant la distinction que le refus a mesuree absente (`REP-4`). Chaque item ne montre
  plus que **trois** propositions (`bonneReponse` + deux distracteurs), jamais quatre : mesure a
  l'appui (`squad/outils/nadia-prof/auditer-paquet.py`, meme outil que `nadia-prof`), quatre
  propositions retombaient dans le repli a quatre colonnes (police 1,875rem, deux rangees), qui a
  lui seul pousse le bas du cabinet a 492 px sur 551 - hors de portee de tout raccourcissement de
  libelle (`ADR-0029`, decision de forme qui appartient a `ines-design`, pas a ce paquet). A trois
  propositions, mesure refaite : le bas des propositions descend a 453 px (14 items sur 16) ou
  501 px (les deux items aux enonces les plus longs, non modifiables sur instruction de
  `nadia-prof`) ; le **verdict** est desormais au-dessus du pli sur **14 items sur 16** (516,19 px,
  35 px de marge) et le depasse de 13,19 px sur les deux items restants (564,19 px) ; l'**indice**
  reste sous le pli sur **16 items sur 16** (595,19 px, 44,19 px sous le pli au mieux ; 643,19 px,
  92,19 px sous le pli sur les deux items aux longs enonces). **Ce residu n'est pas ferme par ce
  commit** : la police (2rem, en gras) et le rembourrage (0,75rem/1,25rem) de `.proposition` a
  moins de quatre colonnes sont une decision de forme (`ines-design`), et les deux enonces les plus
  longs ne peuvent pas etre raccourcis (instruction de `nadia-prof`, "la matiere est saine"). Route
  a `sofia-sm` avec ce chiffre. `REP-5` (deux indices nommaient "prenom", items 8 et 9) et `REP-6`
  (espace ordinaire avant le point d'interrogation de l'item 15) sont corriges dans le meme commit.
- **Repartition des seize items par repere, pour l'audit de `nadia-prof` (documentee, non
  recalculee par un test - voir la section suivante)** : items 1-3 majuscule initiale, 4-6 point
  final, 7-9 majuscule au nom propre, 10-12 point d'interrogation, 13-16 aucune erreur. Cinq
  libelles fixes existent desormais : "Majuscule initiale", "Point final", "Majuscule au prénom",
  "Point d'interrogation", "Phrase correcte" - chacun distinct d'un seul repere (ou du cas
  correct). Chaque item n'en montre que trois, jamais les cinq a la fois (voir le commentaire du
  paquet servi pour le detail des compositions retenues, coherentes avec chaque phrase) ;
  "Phrase correcte" figure sur les seize items sans exception.
- **Arbitrage prealable de `nadia-prof` deja rendu, avant tout code** (`squad/conformite/
  ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`, section B) : forme retenue et
  quatre conditions de composition du paquet, reprises ci-dessous. Aucun arbitrage supplementaire
  n'est du avant le premier increment.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, conditions M1 a M13).
- **Derniere mise a jour** : 2026-08-26 (`lea-dev`, correction du refus REP-4/pli du meme jour).

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Orthographe et grammaire | Reperer et corriger la majuscule et la ponctuation dans une phrase donnee (majuscule initiale, point final, majuscule au nom propre, point d'interrogation d'une question) | `squad/programme/francais/competences-cm1.md` |

**Origine de la competence** : detachee de "Relire et corriger son texte a l'aide de reperes
simples (majuscules, ponctuation)" et amendee au referentiel par `DEC-0046`, section 2, sur
proposition de `nadia-prof`. La ligne d'origine reste `non couvrable en l'etat` : ce paquet ne la
couvre pas, il couvre uniquement la connaissance des reperes sur une phrase donnee, jamais la
relecture par l'enfant de son propre texte.

**Forme retenue, dans les mots de `nadia-prof`** (`ARBITRAGE-PREALABLE-s19-quatre-proxys-en-
attente-depuis-s13.md`, section B.3) : une phrase unique par item, quatre propositions courtes qui
nomment ce qui manque - "il manque un point", "il manque une majuscule", "il manque une majuscule
au prenom", "cette phrase est correcte". Trois autres formes ont ete examinees et ecartees par
elle : quatre versions completes de la meme phrase (charge cognitive trop haute, rangees de
propositions trop larges - dette deja rencontree trois fois sur ce produit) ; un signe seul a
choisir en fin de phrase (glyphe d'un caractere dans un bouton prevu pour un mot, question de
lisibilite qui reviendrait a `ines-design` si elle etait retenue). Seule la forme B est retenue.

**Note du 2026-08-26 (`lea-dev`)** : les quatre libelles cites ci-dessus par `nadia-prof` sont ceux
de sa specification initiale, avant son propre refus du meme jour. Le paquet servi n'en porte plus
que le principe (des libelles courts qui nomment ce qui manque) ; le detail exact (cinq libelles,
dont "Point d'interrogation" distinct de "Point final") est dans la section "Correction du
2026-08-26" ci-dessus et dans le commentaire du paquet servi.

## Criteres pedagogiques attendus - camille-po

Repris des quatre conditions prealables de `nadia-prof` (`REP-1` a `REP-4`, memes references), plus
la checklist d'ouverture habituelle de ce produit :

- [ ] Chaque phrase est courte, de vocabulaire CM1, et ne contient aucune virgule (`REP-1`).
- [ ] Au moins un quart des items ne porte aucune erreur, et leur bonne reponse est "cette phrase
  est correcte" (`REP-2`).
- [ ] Un seul repere est en jeu par item portant une erreur - jamais une phrase qui cumule deux
  erreurs a la fois (`REP-3`).
- [ ] Les quatre reperes de l'intitule (majuscule initiale, point final, majuscule au nom propre,
  point d'interrogation d'une question) sont chacun representes, aucun ne depassant la moitie des
  items (`REP-4`).
- [ ] Chaque item porte un indice non vide qui aide a reperer le signe attendu, sans jamais nommer
  la reponse.
- [ ] Le paquet compte au moins 16 items.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.

## Ce que cette fiche ne couvre pas

`REP-1` a `REP-4` portent sur la composition du paquet (presence de virgules, part d'items sans
erreur, unicite du repere en jeu par item, equilibre des quatre reperes) : aucun test de ce depot
ne les verifie automatiquement, meme famille que `DEN-1` a `DEN-8` et `NAT-1` a `NAT-8`. `nadia-prof`
signale elle-meme que `REP-2` (le quart d'items sans erreur) est la plus facile a perdre, un paquet
ou toutes les phrases sont fautives se lisant tres bien a l'ecriture.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue.
