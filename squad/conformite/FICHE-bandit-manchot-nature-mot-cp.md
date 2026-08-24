# Fiche de conformite - bandit-manchot-nature-mot-cp

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cp-nature-mot.yaml`
  livre, `type: question`, 16 items, story `S19-4` (`docs/backlog/backlog.md`).
  **Adaptation reelle requise, pas une reprise** : `contenu-repris/mots/cm1-nature-mot.yml` (16
  items) classe des mots isoles (hors phrase) en quatre natures (nom, verbe, adjectif,
  determinant), a un niveau CM1. La competence CP demande exactement deux natures (nom, verbe) et
  exige que le mot soit presente dans une phrase tres simple, jamais isole. Les deux ecarts (forme
  et perimetre) changent a la fois ; ce paquet repasse integralement devant `nadia-prof` comme un
  contenu neuf (`contenu-repris/README.md`).
- **Statut global** : **increment livre, Done pedagogique non encore prononce.** Rien n'a encore
  ete joue par `nadia-prof` sur cet increment : le Done pedagogique reste entier.
- **Arbitrage prealable de `nadia-prof` rendu le 2026-08-24**
  (`squad/conformite/ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md`, partie B) : **la
  forme du jeu est ACCEPTEE SOUS CONDITIONS ; les seize phrases du premier brouillon
  (`squad/sprints/s19-contenu-seul-et-conception-de-la-saisie/proposition-s19-4-lea-dev.md`) sont
  REFUSEES en l'etat**, motif mesure et non suppose - les huit noms proposes etaient tous precedes
  d'un determinant, aucun des huit verbes ne l'etait, et la seule regle "petit mot juste avant" donnait
  seize bonnes reponses sur seize sans lire aucun mot cible (`NAT-1`). Conditions bloquantes `NAT-1` a
  `NAT-5`, non bloquantes `NAT-6` a `NAT-8`, detaillees dans le fichier d'arbitrage. Le paquet servi
  sous `adapters/adapter-content/src/main/resources/contenu/cp-nature-mot.yaml` est la reecriture
  demandee ; sa conformite aux conditions reste a auditer par `nadia-prof` sur l'increment livre (elle
  n'a valide aucun contenu par ce seul depot de fichier).
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, conditions M1 a M13) et **forme deja validee pour un mot
  marque en contexte de phrase** (`FICHE-bandit-manchot-synonymes-cm1.md`, conditions SYN-1, SYN-6,
  SYN-9, reprises par analogie ci-dessous pour marquer le mot cible).
- **NAT-5 (renfort visuel du mot cible), non encore instruit** : les guillemets francais seuls ne
  suffisent pas a un enfant de CP qui ne les a pas appris (`nadia-prof`, partie B.6). Le renfort est a
  proposer par `ines-design` et a trancher par `nadia-prof`, jamais par la seule couleur ; ce paquet
  garde pour l'instant les guillemets francais a espaces insecables, identiques a `cm1-synonymes`.
- **Derniere mise a jour** : 2026-08-24 (`lea-dev`, depot du paquet reecrit selon `NAT-1` a `NAT-8`)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CP (cycle 2) | Orthographe et grammaire | Distinguer les principaux types de mots (nom, verbe) dans une phrase tres simple | `squad/programme/francais/competences-cp.md` |

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque item presente une phrase tres simple (sujet, verbe, et au plus un complement court -
  vocabulaire et longueur de niveau CP) avec le mot cible marque par une ponctuation francaise,
  jamais un mot isole.
- [ ] Les propositions de chaque item sont exactement deux : "nom" et "verbe" - aucune troisieme
  nature.
- [ ] Le paquet est classe par bonne reponse ; "nom" et "verbe" sont chacun representes par au
  moins un quart des items, aucune des deux natures dominante.
- [ ] Le vocabulaire employe (sujets, verbes, complements) est un vocabulaire courant de CP, deja
  rencontre ou plausible a ce niveau de lecture - pas le vocabulaire CM1 du paquet repris.
- [ ] Chaque item porte un indice qui aide a reconnaitre la nature du mot (par exemple : "un verbe
  dit ce qu'on fait") sans jamais nommer la reponse.
- [ ] Le paquet compte au moins 16 items.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue. L'arbitrage prealable demande ci-dessus (lisibilite des phrases
> pour un CP, absence d'indice de position) sera rendu ici ou dans un fichier d'arbitrage dedie,
> avant tout code.
