# Fiche de conformite - bandit-manchot-antonymes-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cm1-antonymes.yaml`
  (a creer, `type: question`, au moins 16 items), story `S19-1` (`docs/backlog/backlog.md`).
  **Aucune matiere premiere reutilisable** : `contenu-repris/associer/cm1-synonymes.yml` (12 paires
  mot/synonyme) ne contient aucun antonyme, verifie ligne par ligne et confirme par `DEC-0039`
  ("verifie par recherche du mot 'antonyme' et de ses derives dans `contenu-repris/` : aucun
  fichier"). Le paquet est integralement neuf.
- **Decision de forme - jeu distinct de "Les synonymes", pas une extension** : l'entree de
  catalogue `bandit-manchot-synonymes-cm1` porte, depuis `ADR-0052`, une consigne generique et
  fixe ("Quel mot peut remplacer le mot entre guillemets sans changer le sens ?") qui serait fausse
  pour un item d'antonyme. `nadia-prof` qualifie elle-meme les antonymes d'"autre operation", pas
  d'un prolongement du meme geste (`DEC-0039`, section 3). Detail complet du raisonnement :
  `docs/backlog/backlog.md`, story `S19-1`.
- **Statut global** : **ouverte, aucun increment livre a ce jour.** Rien n'a encore ete joue.
- **Arbitrage prealable de `nadia-prof` demande et non encore rendu** : condition posee par
  elle-meme des s17 - "la reponse rendue pour les synonymes ne vaut pas automatiquement pour
  l'operation inverse" (`S17-5`, `docs/backlog/backlog.md`). Les criteres ci-dessous sont ceux de
  `camille-po` et n'ont pas valeur de jugement pedagogique tant que `nadia-prof` ne les a pas
  confirmes ou corriges.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, conditions M1 a M13) et **forme deja validee pour un jeu
  voisin** (`FICHE-bandit-manchot-synonymes-cm1.md`, conditions SYN-1 a SYN-9, reprises par
  analogie ci-dessous).
- **Derniere mise a jour** : 2026-08-24 (ouverture par `camille-po`, `DEC-0042`)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Vocabulaire | Utiliser des synonymes et antonymes pour preciser ou nuancer un propos | `squad/programme/francais/competences-cm1.md` |

**Perimetre** : seconde moitie de cette ligne, deja ouverte pour sa moitie synonymes par
`bandit-manchot-synonymes-cm1` (`en cours`). Cette ligne ne passe a `couvert-valide` que le jour ou
les deux jeux sont chacun `VALIDE` par `nadia-prof` (`DEC-0039`, section 3).

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque item presente le mot cible entre guillemets francais a l'interieur d'une phrase
  complete, jamais isole (repris de `SYN-1`).
- [ ] La bonne reponse est un antonyme reellement oppose au mot cible **dans cette phrase precise**,
  pas un antonyme du mot pris hors contexte.
- [ ] Aucun distracteur n'est lui-meme un antonyme acceptable du mot cible dans cette phrase - un
  seul contraire juste par item.
- [ ] Les espaces a l'interieur des guillemets francais sont des espaces insecables (U+00A0),
  jamais ordinaires (repris de `SYN-9`).
- [ ] Le paquet compte au moins 16 items, aucun ne produisant trois rangees de propositions a la
  reference 744 x 551 (`DEC-0040`).
- [ ] Chaque item porte un indice qui aide a trouver l'antonyme sans le nommer.
- [ ] La profondeur des phrases est de niveau CM1, pas CE1/CE2 (meme exigence que `SYN-2`/`SYN-3`).
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve - reutilise le mecanisme de rendu deja etabli par `ADR-0052` pour "Les
  synonymes".

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue. L'arbitrage prealable demande ci-dessus (validite du proxy pour
> l'operation inverse, profondeur des phrases) sera rendu ici ou dans un fichier d'arbitrage dedie,
> avant tout code.
