# Fiche de conformite - bandit-manchot-comparer-nombres-cp

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cp-comparer-nombres.yaml`
  (a creer, `type: question`, au moins 20 items), story `S9-3` (`docs/backlog/backlog.md`). Contenu
  neuf, aucune matiere premiere identifiee dans `contenu-repris/` pour cette ligne (le fichier
  `contenu-repris/quiz/cp-comparer.yml` porte surtout des comparaisons de grandeurs physiques,
  hors perimetre de cette story).
- **Statut global** : en construction.
- **Derniere mise a jour** : 2026-08-12

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CP (cycle 2) | Nombres et calculs | Comparer et ranger des nombres jusqu'a 100 (<, >, =) | `squad/programme/mathematiques/competences-cp.md` |

**Perimetre partiel, assume a l'ouverture** : cette fiche ne couvre que "comparer" (choisir le bon
signe entre deux nombres). "Ranger" (ordonner une suite de trois nombres ou plus) est differe par
`DEC-0019`, motif : risque de largeur de proposition non verifie sur le rendu existant, pas une
impossibilite de principe. Un Done pedagogique accorde ici ne peut donc pas, a lui seul, faire
passer la ligne de matrice a `couvert-valide` sans une extension ulterieure sur "ranger".

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque enonce presente deux nombres entiers distincts compris entre 0 et 100 inclus, separes
  par un espace a completer, et les trois propositions sont toujours exactement les trois signes
  "<", ">", "=" (jamais un nombre en proposition).
- [ ] Chacun des trois signes est la bonne reponse d'au moins un tiers des items (repartition
  equilibree, pour eviter qu'un enfant reussisse en devinant toujours le meme signe).
- [ ] Les items dont la bonne reponse est "=" representent au moins 20 % du paquet (l'egalite entre
  deux ecritures d'un meme nombre est reellement travaillee, pas un cas limite anecdotique).
- [ ] Au moins un quart des items dont la bonne reponse est "<" ou ">" oppose deux nombres separes
  de 10 ou moins (la comparaison ne se reduit pas a "le nombre le plus long gagne").
- [ ] Le paquet compte au moins 20 items.
- [ ] Chaque item porte un indice non vide, exact et comprehensible par un enfant de CP.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve ; les propositions sont des signes a un seul caractere.

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
