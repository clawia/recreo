# DEC-0075 - Corpus de reproductions d'oeuvres et droits d'usage : question ouverte, non tranchee

- **Type** : question produit ouverte (sourcing de contenu, risque juridique), routee par `nadia-prof`.
- **Date** : 2026-09-03
- **Auteur** : `camille-po`
- **Sprint** : hors-sprint (ouverte a l'integration des dix referentiels absents, avant s28)
- **Competence programme visee** : transverse - pas de contenu pedagogique.
- **Statut** : Proposee
- **Lien** : `squad/programme/arts-plastiques/competences-cp.md` (lignes 9, 10),
  `squad/programme/arts-plastiques/competences-cm1.md` (lignes 7, 8, condition `ART-3`),
  `squad/programme/histoire-des-arts/competences-cm1.md` (lignes 1, 2, 3, 4, 6),
  `squad/programme/MATRICE-couverture.md` (integration du 2026-09-03), `DEC-0074`, `ADR-0073`.

---

## 1. Le constat, route par `nadia-prof`, dans ses propres mots

Sur les arts plastiques CP (ligne 9, "Decrire une oeuvre") :

> *"Signale a `camille-po` et non tranche par moi : reproduire une oeuvre existante pose une
> question de droits qui n'est pas de ma competence."*

Sur les arts plastiques CM1, en posant la condition `ART-3` :

> *"La question des droits d'usage des reproductions n'est pas de ma competence : je la signale a
> `camille-po` et a `theo-archi`, je ne la tranche pas."*

Sur l'histoire des arts CM1, dans son bilan chiffre :

> *"Les six autres attendent soit un corpus de reproductions d'oeuvres, qui n'existe pas dans le
> depot et dont les droits ne sont pas instruits (`ART-3`), soit le palier 2. Ce n'est pas un cout
> de mecanique, c'est un cout de contenu, et il est a `camille-po` et a `theo-archi` de le
> chiffrer - pas a moi."*

## 2. Le perimetre exact que cette question bloque

**Neuf lignes atteignables** (`non couvert`, jamais `non couvrable en l'etat` - `nadia-prof` est
explicite : l'obstacle est un contenu qui n'existe pas, pas une contrainte fondatrice du produit)
attendent un corpus de reproductions d'oeuvres d'art :

| Matiere / niveau | Lignes | Nombre |
|---|---|---|
| arts plastiques CP | 9 (decrire une oeuvre), 10 (reconnaitre des oeuvres) | 2 |
| arts plastiques CM1 | 7 (reperer les effets dans une oeuvre observee), 8 (decrire une oeuvre avec un lexique elementaire) | 2 |
| histoire des arts CM1 | 1 (decrire), 2 (degager les caracteristiques), 3 (identifier les materiaux), 4 (associer a une epoque), 6 (identifier des reperes du patrimoine) | 5 |
| **Total** | | **9** |

**Ce que la mecanique fournit deja** : le palier 1 visuel (image fixe, propositions en texte,
`ADR-0073`) existe et sert vingt-cinq illustrations - toutes des tracés geometriques (polygones,
cercles, chemins). **Ce qui manque n'est pas un mecanisme, c'est un contenu** : aucune reproduction
d'oeuvre d'art n'est servie par le depot, et personne n'a instruit sous quelle licence ou quelle
autorisation une telle reproduction pourrait l'etre.

## 3. Ce que je constate, sans trancher

Deux questions distinctes, toutes deux hors de ma competence sans arbitrage de l'utilisatrice :

- **La source** : ou le produit se procure-t-il des reproductions d'oeuvres (musees en licence
  ouverte, corpus du domaine public, banque d'images sous licence, autre) ?
- **Le risque** : quel niveau de garantie sur les droits d'usage le produit exige-t-il avant de
  servir une image d'oeuvre a un enfant, sachant que `recreo` est un depot public sur GitHub
  (`CLAUDE.md`, section conventions de redaction) ?

Je n'ai pas d'element pour choisir entre ces options a la place de l'utilisatrice : c'est un choix
de sourcing de contenu et de tolerance au risque juridique, pas un choix de mecanique (`theo-archi`
n'a rien a trancher ici tant que la source n'est pas choisie) ni un choix pedagogique
(`nadia-prof` l'a explicitement ecarte de sa competence).

## 4. Ce que j'ouvre formellement

**Cette decision reste `Proposee` jusqu'a l'arbitrage de l'utilisatrice.** Trois options existent,
et je ne recommande pas :

- **Option A** - ne pas constituer de corpus : les neuf lignes restent `non couvert` sans horizon,
  dette assumee et ecrite.
- **Option B** - constituer un corpus d'oeuvres du domaine public (identification, auteur, date,
  lieu de conservation exiges par `ART-3`), source a determiner par `theo-archi` si l'option est
  retenue.
- **Option C** - acquerir une licence sur un corpus prive, budget et fournisseur a determiner.

Tant qu'aucune de ces options n'est choisie, **les neuf lignes du tableau ci-dessus restent au
statut `non couvert`** dans `squad/programme/MATRICE-couverture.md`, exactement comme `nadia-prof`
les a classees.

## 5. Ce que cette decision ne fait pas

- Elle ne tranche aucune des trois options : c'est l'utilisatrice, sur retour de `camille-po`.
- Elle ne cree aucune story : aucun changement de code n'est encore decide. Si l'utilisatrice
  retient une option qui implique un changement (constitution d'un corpus, mecanisme d'attribution
  d'oeuvre), la story correspondante est ouverte dans le meme geste que l'acceptation, routee par
  `sofia-sm` pour tout ce qui touche `theo-archi`.
- Elle ne tranche aucune question technique ou d'architecture.
- Elle ne fait bouger aucune ligne de `squad/programme/MATRICE-couverture.md` : les neuf lignes
  concernees demeurent `non couvert`, au denominateur atteignable, sans jeu ni mecanique associes.
- Elle ne prononce aucun Done pedagogique.
