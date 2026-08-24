# Fiche de conformite - bandit-manchot-accords-groupe-nominal-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/
  cm1-accords-groupe-nominal.yaml` (a creer, `type: question`, au moins 20 items), story `S19-2`
  (`docs/backlog/backlog.md`). Matiere premiere partiellement reutilisable :
  `contenu-repris/textes-trous/cm1-accords.yml` (5 items) ne teste que l'accord de l'adjectif, le
  determinant et le nom etant deja fixes dans chaque phrase - verifie ligne par ligne. Le paquet
  doit etendre la couverture au determinant et au nom pour representer le groupe nominal complet
  que nomme la competence.
- **Statut global** : **ouverte, aucun increment livre a ce jour.** Rien n'a encore ete joue.
- **Aucun arbitrage prealable de `nadia-prof` demande** : cette competence ne souleve aucune
  question de validite de proxy - elle est directement servable par le schema `type: question`
  existant (fill-in-the-blank), sur le meme patron que l'accord de l'adjectif deja identifie par
  `DEC-0008`. Les criteres ci-dessous sont ceux de `camille-po`, verifies a l'audit comme toute
  autre story de contenu de ce produit.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, conditions M1 a M13). Rien n'est mesure ici a l'ouverture
  de la fiche, faute d'increment a mesurer.
- **Derniere mise a jour** : 2026-08-24 (ouverture par `camille-po`, `DEC-0042`)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Orthographe et grammaire | Accorder en genre et en nombre dans le groupe nominal (determinant/nom/adjectif) | `squad/programme/francais/competences-cm1.md` |

**Perimetre propose** : les trois composantes du groupe nominal (determinant, nom, adjectif) sont
chacune testees par le paquet, pas seulement l'adjectif comme le fait la matiere premiere. Si les
trois ne tiennent finalement pas dans ce paquet a cout raisonnable, la limite de couverture doit
etre nommee par ecrit dans cette fiche des l'ouverture du developpement, sur le modele de
`DEC-0013` (conjugaison, un temps sur trois nomme d'emblee) - jamais decouverte a l'audit.

## Criteres pedagogiques attendus - camille-po

- [ ] Le paquet classe ses items par composante testee (determinant, nom, adjectif) ; chacune des
  trois est representee par au moins un quart des items.
- [ ] Sur un item qui teste l'accord du nom, les distracteurs portent une erreur de nombre reelle
  sur le nom (par exemple un pluriel en `-s` manquant ou ajoute a tort), jamais une erreur d'un
  autre ordre.
- [ ] Sur un item qui teste l'accord du determinant, les distracteurs portent un determinant dont
  le genre ou le nombre ne correspond pas au nom qui suit, jamais un determinant inexistant en
  francais.
- [ ] Sur un item qui teste l'accord de l'adjectif, les distracteurs reprennent le patron deja
  valide sur `cm1-accords.yml` (les quatre formes possibles de l'adjectif, une seule accordee juste
  avec le reste du groupe).
- [ ] Le paquet compte au moins 20 items.
- [ ] Chaque item porte un indice non vide qui donne la methode d'accord, pas le resultat.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.
- [ ] Si les trois composantes ne tiennent pas toutes dans ce paquet, la limite de couverture est
  nommee explicitement dans cette fiche avant le premier audit, jamais decouverte pendant.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue.
