# Fiche de conformite - bandit-manchot-accord-sujet-verbe-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/
  cm1-accord-sujet-verbe.yaml` (a creer, `type: question`, au moins 20 items), story `S19-3`
  (`docs/backlog/backlog.md`). **Aucune matiere premiere reutilisable** :
  `contenu-repris/textes-trous/cm1-conjugaison.yml` (6 items) teste le choix de la bonne personne
  conjuguee au present, avec un sujet pronominal immediatement devant le verbe - c'est la matiere
  qui sert deja `cm1-conjugaison-present.yaml` (`couvert-valide`), pas l'accord avec un sujet
  eloigne, compose ou nominal que vise cette ligne. Le paquet est integralement neuf.
- **Distinction a garder de la ligne conjugaison deja couverte** : chaque item doit exiger de
  reperer le sujet avant de choisir la forme - jamais un sujet pronominal isole colle au verbe, qui
  ne ferait que redemander la conjugaison deja testee ailleurs.
- **Statut global** : **ouverte, aucun increment livre a ce jour.** Rien n'a encore ete joue.
- **Aucun arbitrage prealable de `nadia-prof` demande** : cette competence est directement servable
  par le schema `type: question` existant, sur le meme patron que la conjugaison deja couverte. Les
  criteres ci-dessous sont ceux de `camille-po`, verifies a l'audit.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, conditions M1 a M13). Rien n'est mesure ici a l'ouverture
  de la fiche, faute d'increment a mesurer.
- **Derniere mise a jour** : 2026-08-24 (ouverture par `camille-po`, `DEC-0042`)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Orthographe et grammaire | Accorder le verbe avec son sujet (cas frequents, temps simples : present, futur, imparfait) | `squad/programme/francais/competences-cm1.md` |

**Perimetre propose** : le present, le futur et l'imparfait sont chacun representes dans le paquet,
la competence du referentiel nommant les trois. Si les trois ne tiennent pas dans ce paquet a cout
raisonnable, la limite doit etre nommee par ecrit dans cette fiche des l'ouverture du
developpement, sur le modele de `DEC-0013` (conjugaison, un temps sur trois nomme d'emblee).

## Criteres pedagogiques attendus - camille-po

- [ ] Le sujet de chaque item n'est jamais un pronom personnel seul immediatement suivi du verbe -
  il est soit un groupe nominal ("les enfants du voisin ___"), soit un sujet compose ("mon frere et
  moi ___"), soit separe du verbe par un groupe de mots ("les enfants, malgre la pluie, ___").
- [ ] Sur chaque item, au moins un distracteur est la forme correcte d'un autre nombre ou d'une
  autre personne que celle du sujet reel - une erreur d'accord, pas une erreur de temps ni de verbe.
- [ ] Le paquet classe ses items par temps ; le present, le futur et l'imparfait sont chacun
  representes, sauf limite nommee explicitement dans cette fiche avant le premier audit.
- [ ] Le paquet compte au moins 20 items.
- [ ] Chaque item porte un indice non vide qui donne la methode d'accord (reperer le sujet, son
  nombre et sa personne), pas le resultat.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.
- [ ] Si les trois temps ne tiennent pas tous dans ce paquet, la limite de couverture est nommee
  explicitement dans cette fiche avant le premier audit, jamais decouverte pendant.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue.
