# Fiche de conformite - bandit-manchot-grands-nombres-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : contenu neuf a creer (`type: question`, au moins 24 items), story `S13-1`
  (`docs/backlog/backlog.md`). Aucune matiere premiere de `contenu-repris/` identifiee pour cette
  ligne - le proxy de transcription chiffres/lettres qui la rend servable n'a jamais ete cable sur
  ce produit.
- **Statut global** : **ouverte, aucun increment livre a ce jour.** Rien n'a encore ete joue.
- **Arbitrage prealable rendu avant l'ouverture de cette fiche** :
  `squad/conformite/ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`, section 1 - `nadia-prof` a
  **accepte**, sans reserve de principe, le proxy de transcription entre l'ecriture chiffree et
  l'ecriture en toutes lettres d'un nombre, pour les verbes "lire" et "ecrire", sous deux conditions
  (GN-1, GN-2) reprises au mot pres dans les criteres d'acceptation de S13-1. Les verbes "decomposer"
  et "comparer" sont servables sans aucun proxy. **Cette ligne peut ouvrir en entier**, sans mention
  de couverture partielle.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, branche `s10-nadia-mecanique` et audits suivants) :
  conditions M1 a M13, dont deux ont ete bloquantes pour d'autres jeux du catalogue - M4 (le message
  d'erreur doit etre vu sans defiler a 360 x 640) et la clause 1 de M13 (apres une reponse, la
  tabulation ne detruit jamais la partie sans confirmation). Rien n'est mesure ici a l'ouverture de la
  fiche, faute d'increment a mesurer.
- **Derniere mise a jour** : 2026-08-15 (ouverture par `camille-po`, `DEC-0030`)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CM1 (cycle 3) | Nombres et calculs | Lire, ecrire, decomposer et comparer les nombres entiers jusqu'au million | `squad/programme/mathematiques/competences-cm1.md` |

**Perimetre exact, assume a l'ouverture** : les quatre verbes du referentiel (lire, ecrire,
decomposer, comparer) sont tous vises par ce paquet, sur le proxy accepte par `nadia-prof` pour
"lire" et "ecrire" (l'ecriture en toutes lettres tient lieu de designation orale, ce produit n'ayant
et n'aurant aucune modalite audio). Un Done pedagogique accorde sur un paquet qui couvre les quatre
verbes dans les proportions posees ci-dessous ferme cette ligne en entier, sans reste connu a ce
jour.

## Criteres pedagogiques attendus - camille-po

- [ ] Dans le sens chiffres vers lettres, chaque distracteur differe de la bonne reponse par la
  valeur qu'il designe (classe ou position confondue), jamais par la seule orthographe des
  mots-nombres (GN-1).
- [ ] Dans le sens lettres vers chiffres, la designation litterale de chaque enonce est ecrite sans
  faute, sans ambiguite, et de facon constante dans tout le paquet (GN-2).
- [ ] Chaque item "decomposer" propose un nombre entier jusqu'au million et demande sa decomposition
  exacte en valeurs de position.
- [ ] Chaque item "comparer" propose deux nombres entiers distincts jusqu'au million et demande le
  signe exact parmi <, > et =.
- [ ] Les quatre verbes (lire, ecrire, decomposer, comparer) sont chacun representes par au moins un
  quart du paquet.
- [ ] Aucune proposition n'est egale a une autre dans le meme item.
- [ ] Le paquet compte au moins 24 items, avec au moins 6 par verbe.
- [ ] Chaque item porte un indice non vide, exact et comprehensible par un enfant de CM1, qui ne
  paraphrase pas l'enonce.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue.
