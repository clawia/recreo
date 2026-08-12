# Fiche de conformite - bandit-manchot-complements-10-cp

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cp-complements-10.yaml` (a
  creer, `type: question`, au moins 9 items), story `S9-2` (`docs/backlog/backlog.md`). Contenu
  neuf, aucune matiere premiere identifiee dans `contenu-repris/` pour cette ligne.
- **Statut global** : en construction, deblocage acte par `DEC-0020` (nombreDeManches abaisse a 7,
  criteres corriges).
- **Derniere mise a jour** : 2026-08-12

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CP (cycle 2) | Nombres et calculs | Complements a 10 | `squad/programme/mathematiques/competences-cp.md` |

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque enonce demande le complement a 10 d'un entier compris entre 0 et 10 inclus, et la
  bonne reponse, ajoutee au nombre de l'enonce, fait exactement 10.
- [ ] Chaque valeur entiere de 0 a 10 apparait au moins une fois comme nombre de depart parmi les
  items (couverture complete de la table des complements a 10, pas seulement ses valeurs les plus
  faciles).
- [ ] Chaque item a exactement trois propositions (la bonne reponse, deux distracteurs numeriques
  distincts dans l'intervalle 0-10).
- [ ] Au moins un distracteur par item correspond a une erreur frequente et identifiable (par
  exemple le nombre de depart recopie, ou le complement a 9 plutot qu'a 10).
- [ ] Le paquet compte au moins 9 items, strictement plus que les 7 manches declarees pour ce jeu au
  catalogue (`ADR-0023`) - plancher different des autres paquets du sprint car l'espace de la forme
  canonique est fini a onze valeurs (0 a 10 inclus), voir `DEC-0020` pour le calcul.
- [ ] L'entree de ce jeu dans `catalogue.yaml` declare `nombreDeManches: 7` (`DEC-0020`).
- [ ] Chaque item porte un indice non vide, exact et comprehensible par un enfant de CP.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : <a renseigner par nadia-prof>
- **Date** : <a renseigner par nadia-prof>
- **Audit joue** : <a renseigner par nadia-prof>

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
