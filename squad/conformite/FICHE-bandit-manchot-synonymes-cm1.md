# Fiche de conformite - bandit-manchot-synonymes-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cm1-synonymes.yaml`
  (a creer, `type: question`, au moins 20 items), story `S17-3` (`docs/backlog/backlog.md`). Matiere
  premiere identifiee : `contenu-repris/associer/cm1-synonymes.yml` (12 paires mot/synonyme, forme
  d'association par paires - `gameKey: associer`). **Reformulation de forme requise avant reprise** :
  aucune mecanique d'association par paires n'existe dans `domain/mechanics` (seule
  `banditmanchot` y est implementee) ; en ouvrir une serait une question d'architecture hors de mon
  mandat. Chaque paire est donc reprise comme matiere (le mot et son synonyme), reformulee en
  question a choix multiple ("Quel mot veut dire la meme chose que `<mot>` ?") pour rester dans le
  schema `type: question` existant. Reprise = reprise de la matiere, pas de la structure
  (`contenu-repris/README.md`) : chaque item repasse devant `nadia-prof` comme un contenu neuf.
- **Statut global** : **en construction** - arbitrage prealable rendu le 2026-08-19 : proxy
  **ACCEPTE** sous condition de forme (SYN-1 a SYN-3), couverture partielle **ACCEPTEE** mais **ne
  comptera jamais dans le taux de couverture** sur les seuls synonymes (voir ci-dessous). Aucun code
  ecrit a ce jour.
- **Derniere mise a jour** : 2026-08-19 (arbitrage prealable de `nadia-prof`, `camille-po` en tire
  les consequences sur cette fiche).

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Vocabulaire | Utiliser des synonymes et antonymes pour preciser ou nuancer un propos | `squad/programme/francais/competences-cm1.md` |

**Couverture partielle assumee a l'ouverture, nommee pour ne pas etre decouverte a l'audit** : **ce
paquet ne porte que des synonymes**. Aucun antonyme n'y figure, faute de matiere premiere identifiee
dans `contenu-repris/` (verifie par recherche du mot "antonyme" et de ses derives sur l'ensemble du
dossier : aucun fichier n'en porte).

**Mise a jour du 2026-08-19, apres l'arbitrage prealable de `nadia-prof`**
(`ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md`, section 2.2). J'avais range cette
partialite avec les cinq deja acceptees sur ce produit (additions "jusqu'a 10 (puis 20)", homophones
"les principaux", conjugaison "un temps sur trois", comparer "sans ranger", tables de multiplication
"sans mesure d'automatisation"), dont l'une (tables) a fini par atteindre `couvert-valide` avec une
mention restrictive. `nadia-prof` marque une difference que je retiens : dans les cinq precedents,
la moitie manquante etait "un prolongement du meme geste" (des nombres plus grands, un temps de
plus) ; ici, **les antonymes ne sont pas des synonymes plus difficiles, ils sont l'autre operation**
- celle du contraste, par laquelle un enfant apprend justement a nuancer. **Consequence, que je
retiens telle qu'elle l'a ecrite : cette ligne reste `en cours` quel que soit le verdict sur le
paquet synonymes seul, et n'entre jamais dans le numerateur du taux de couverture tant que les
antonymes ne sont pas ajoutes** - un Done `VALIDE` sur ce paquet ne suffira pas, a la difference de
"tables de multiplication". Voir `squad/programme/MATRICE-couverture.md` pour la maniere dont ceci
est ecrit dans la matrice (prose sous le tableau francais CM1, meme mecanisme que la nuance
"comparer/ranger" de s09 - aucun statut neuf n'est invente).

Story compagne demandee par `nadia-prof` (SYN-4, non bloquante mais datee) : la moitie antonymes
doit exister comme story au backlog avant la cloture de s17, meme non affectee - voir `S17-5`,
`docs/backlog/backlog.md`.

## Criteres pedagogiques attendus - camille-po

**Mise a jour du 2026-08-19** : checklist reformulee pour reprendre au mot pres SYN-1 a SYN-3 de
l'arbitrage prealable de `nadia-prof`. Le mot n'est plus presente isole : chaque item devient une
phrase a trou de substitution, sur demande bloquante de `nadia-prof` (SYN-1).

- [ ] **SYN-1.** Chaque item presente le mot dans une phrase, jamais isole : "Dans cette phrase,
  quel mot peut remplacer le mot souligne sans changer le sens ?", pas "Quel mot veut dire la meme
  chose que `<mot>` ?".
- [ ] **SYN-2.** Pour chaque item, la bonne reponse est reellement substituable au mot souligne dans
  la phrase donnee, et aucun distracteur ne l'est - verifiable phrase par phrase.
- [ ] **SYN-3.** Aucun item ne presente comme interchangeables deux mots qu'un enseignant refuserait
  dans cette phrase precise (deux synonymes ne le sont jamais partout).
- [ ] Le paquet compte au moins 20 items.
- [ ] Chaque item porte un indice non vide, exact et comprehensible par un enfant de CM1, qui aide a
  retrouver le sens du mot sans jamais nommer la reponse (condition **M2** de l'arbitrage de
  mecanique en vigueur pour tout le catalogue, `squad/conformite/FICHE-bandit-manchot-additions-cp.md`).
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve, et sans mecanique d'association par paires.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : <a rendre>
- **Date** : <a rendre>
- **Audit joue** : <a rendre>

### Ce qui a ete verifie

- **Ancrage au programme** :
- **Lisibilite au niveau vise** :
- **Charge cognitive** :
- **Progression** :
- **Traitement de l'erreur** :
- **Exactitude du contenu** :
- **Biais et ton** :

### Motif

### Reserves

## Arbitrage prealable bloquant, avant tout premier commit de code

Pose par `camille-po` a l'ouverture de cette fiche, sur le modele impose depuis l'incident du "Bon
verbe" en s07 (`squad/README.md`, section 10). Deux questions, toutes deux avant tout code :

1. **La forme retenue (question a choix multiple sur un mot isole) est-elle un exercice valide
   d'"utiliser des synonymes... pour preciser ou nuancer un propos" ?**
2. **Le perimetre "synonymes seuls, antonymes non couverts" est-il acceptable comme premiere
   ouverture en couverture partielle nommee ?**

**Reponses rendues le 2026-08-19**
(`ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md`, section 2) :

1. **Le proxy tient, sous condition.** *"Pour choisir le mot qui veut dire la meme chose, il faut
   connaitre le sens des deux mots ; il n'y a pas de raccourci. Un enfant qui repond juste sait, et
   savoir est la condition d'utiliser."* Mais la forme d'ouverture (mot isole) est plus faible
   qu'elle n'a besoin de l'etre : *"un mot seul n'est pas un propos"*. **Condition bloquante SYN-1** :
   le mot est presente dans une phrase. Sans elle, son accord sur le proxy tombe. Ce que le proxy ne
   travaillera jamais, meme avec SYN-1 : l'emploi en production libre - la ligne ne pourra jamais
   etre pleinement couverte par ce jeu seul.
2. **La couverture partielle est acceptee**, mais avec un statut different des cinq precedentes :
   voir la mise a jour ci-dessus sous "Competences visees". Story compagne des antonymes demandee
   au backlog avant la cloture de s17 (SYN-4, non bloquante).
