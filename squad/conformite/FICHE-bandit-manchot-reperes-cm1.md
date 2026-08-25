# Fiche de conformite - bandit-manchot-reperes-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cm1-reperes.yaml`,
  livre, `type: question`, 16 items, story `S20-2` (`docs/backlog/backlog.md`). Aucune matiere
  premiere identifiee dans `contenu-repris/` sur ce repere precis : le paquet est integralement
  neuf.
- **Statut global** : **increment livre, Done pedagogique non encore prononce.** Rien n'a encore
  ete joue par `nadia-prof` sur ce jeu : le Done pedagogique reste entier. Entree de catalogue
  `bandit-manchot-reperes-cm1` deposee dans le meme commit (titre "Majuscule ou point ?", consigne
  "Que manque-t-il à cette phrase ?"), suivant la convention deja tenue par les autres paquets de
  ce catalogue plutot que le mecanisme de retrait/retour de `DEC-0045` (ce mecanisme a servi a
  restaurer une entree retiree pour une reserve bloquante ; ce paquet-ci n'en porte aucune a ce
  stade, seul le Done pedagogique reste a prononcer).
- **Repartition des seize items par repere, pour l'audit de `nadia-prof` (documentee, non
  recalculee par un test - voir la section suivante)** : items 1-3 majuscule initiale, 4-6 point
  final, 7-9 majuscule au nom propre, 10-12 point d'interrogation, 13-16 aucune erreur. Les quatre
  propositions sont fixes et identiques sur chaque item (ordre du paquet) : "Il manque une
  majuscule.", "Il manque un point.", "Il manque une majuscule au prénom.", "Cette phrase est
  correcte." Le libelle "Il manque un point." sert a la fois au repere point final et au repere
  point d'interrogation : la forme retenue par `nadia-prof` (section B.3) ne porte pas de libelle
  distinct pour ce quatrieme repere, c'est la structure de la phrase (interrogative ou non) qui le
  porte.
- **Arbitrage prealable de `nadia-prof` deja rendu, avant tout code** (`squad/conformite/
  ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`, section B) : forme retenue et
  quatre conditions de composition du paquet, reprises ci-dessous. Aucun arbitrage supplementaire
  n'est du avant le premier increment.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, conditions M1 a M13).
- **Derniere mise a jour** : 2026-08-25 (`lea-dev`, depot du paquet `S20-2`).

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
