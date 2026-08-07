# Fiche de conformite - bandit-manchot-conjugaison-present-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec
  `bandit-manchot-additions-cp`, `bandit-manchot-homophones-cm1` et
  `bandit-manchot-le-bon-son-cp`).
- **Contenu** : paquet a creer sous `adapters/adapter-content/src/main/resources/contenu/`
  (nom de fichier laisse a `theo-archi` / aux developpeurs, par exemple
  `cm1-conjugaison-present.yaml`), `type: question`. Matiere premiere issue de `contenu-repris/
  textes-trous/cm1-conjugaison.yml`, 6 items a l'etat de reserve, a completer et a repasser devant
  `nadia-prof` comme un contenu neuf (`contenu-repris/README.md`, `CLAUDE.md`).
- **Statut global** : **EN CONSTRUCTION.** Aucun audit de `nadia-prof` a ce jour ; l'increment
  n'est pas encore fusionne sur `round-two`, aucune ligne de catalogue ne le sert. Fiche ouverte
  a l'entree en construction du jeu, conformement au sprint 6 (`DEC-0013-perimetre-sprint-6.md`).
- **Ligne de matrice** : `en cours` a l'ouverture de cette fiche
  (`squad/programme/MATRICE-couverture.md`). Couverture assumee comme **partielle des l'ouverture**
  : le present de l'indicatif seulement ; le futur et l'imparfait, nommes par la meme ligne du
  referentiel, restent hors de ce jeu et resteront `non couvert` tant qu'aucune extension n'est
  livree.
- **Derniere mise a jour** : 2026-08-07

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Orthographe et grammaire | Conjuguer les verbes frequents au present, futur et imparfait de l'indicatif - **sur le present uniquement pour ce jeu** | `squad/programme/francais/competences-cm1.md` |

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque manche presente une phrase a trou dont la bonne reponse est un verbe frequent
      conjugue au present de l'indicatif, jamais a un autre temps.
- [ ] La bonne reponse figure exactement une fois parmi les propositions ; chaque distracteur est
      une autre forme conjuguee du meme verbe au present (jamais un verbe different, jamais une
      forme d'un autre temps).
- [ ] Chacune des six personnes grammaticales (je, tu, il/elle, nous, vous, ils/elles) est
      representee par au moins un item du paquet.
- [ ] Le paquet demarre une partie sans erreur avec la mecanique bandit manchot existante et sans
      qu'aucune ligne de `domain/mechanic` ni `domain/mechanics` n'ait ete modifiee pour
      l'accueillir (`ADR-0007`, `CatalogueChargeableIT`).
- [ ] Sur 20 manches jouees a la suite, les phrases proposees ne sont pas toutes identiques.
- [ ] Tout texte destine a l'enfant (consigne, retours, indices s'il en existe) est redige en
      francais correct et integralement accentue, orthographe et ponctuation irreprochables
      (exigence de categorie B ajoutee par l'amendement du 2026-08-04 a `DEC-0002`).
- [ ] Les phrases sont d'un niveau de vocabulaire et de longueur adapte a un eleve de CM1 ; les
      verbes retenus sont effectivement "frequents" au sens du referentiel.
- [ ] L'accueil mentionne le niveau vise (reserve commune deja nommee sur les deux autres jeux de
      francais, `docs/backlog/backlog.md`, "Backlog - sprints suivants"), sauf si `camille-po` la
      traite ailleurs avant l'audit de ce jeu.

Voir `docs/backlog/backlog.md`, story S6-3, pour la formulation complete de ces criteres en
entree/sortie observables.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

*(vide a l'ouverture de cette fiche)*
