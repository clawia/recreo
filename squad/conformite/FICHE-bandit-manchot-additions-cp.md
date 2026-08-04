# Fiche de conformite - bandit-manchot-additions-cp

- **Mecanique** : bandit manchot (mecanique generique - tirage d'une question, choix parmi
  plusieurs propositions dont une seule correcte, retour immediat juste/faux, enchainement de
  plusieurs manches avec score cumule).
- **Contenu(s)** : additions CP dont les deux operandes et la somme sont compris entre 0 et 10.
  Matiere premiere de reference (a repasser devant `nadia-prof` comme un contenu neuf, pas
  reprise telle quelle) : `contenu-repris/cp-calcul-mental.yml`.
- **Statut global** : en construction
- **Derniere mise a jour** : 2026-08-04

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CP (cycle 2) | Nombres et calculs | Calcul mental - additions dans la limite de 10 (sous-partie de la competence referentiel, qui couvre aussi "puis 20", hors perimetre de ce jeu pour l'instant) | `squad/programme/mathematiques/competences-cp.md` |

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque manche presente une addition dont les deux operandes et la somme sont des entiers
      compris entre 0 et 10 (jamais de somme superieure a 10).
- [ ] Chaque manche presente une unique bonne reponse parmi l'ensemble des propositions
      affichees.
- [ ] Les propositions fausses (distracteurs) sont des nombres entiers plausibles, proches de la
      bonne reponse, jamais negatifs et jamais non entiers.
- [ ] La consigne et les retours (juste/faux) sont formules en une phrase courte, en vocabulaire
      courant de CP (pas de terme comme "somme" ; une formulation du type "combien ca fait ?").
- [ ] Le retour juste/faux est accompagne d'un signal visuel (forme et/ou couleur) distinct du
      texte, sans emoji dans le gabarit (voir `DEC-0002`), pour un enfant qui ne dechiffre pas
      encore vite.
- [ ] Les additions proposees varient d'une manche a l'autre sur une session de jeu (pas de
      question figee qui se repete en boucle).
- [ ] Rien dans le code de la mecanique ne fait reference a "addition", "CP" ou aux bornes 0-10 :
      tout provient du contenu injecte (verifie par la regle ArchUnit de `theo-archi`, story
      S0-3 de `docs/backlog/backlog.md`).

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : <VALIDE / VALIDE SOUS RESERVE / REFUSE>
- **Date** : <AAAA-MM-JJ>
- **Audit joue** : <oui / non - si non, dire pourquoi, l'audit est degrade>

### Ce qui a ete verifie

- **Ancrage au programme** : <la competence annoncee est-elle bien celle travaillee ?>
- **Lisibilite au niveau vise** : <longueur des consignes, vocabulaire>
- **Charge cognitive** : <combien de choses a tenir en tete>
- **Progression** : <la difficulte monte-t-elle ?>
- **Traitement de l'erreur** : <l'enfant comprend-il ce qu'il a rate ? peut-il avancer ?>
- **Exactitude du contenu** : <enonces, reponses attendues, plausibilite des distracteurs>
- **Biais et ton** : <stereotypes, condescendance, presupposes de contexte>

### Motif

<Actionnable. Pas "pas au niveau" mais "la consigne fait 4 lignes et emploie un mot hors
programme de CP".>

### Reserves

<Si VALIDE SOUS RESERVE : la liste nommee, que camille-po transforme en stories.>
