# Gabarits des artefacts de la squad

Modeles vierges. Les placeholders sont entre chevrons : `<comme ceci>`.
Les conventions de nommage et les proprietaires sont dans [`README.md`](README.md) section 2.

---

## 1. Planning - `squad/sprints/sNN-<slug>/planning.md`

```markdown
# [Sprint sNN] Planning - <AAAA-MM-JJ>

- **Horodatage de debut** : <AAAA-MM-JJ HH:MM>
- **Anime par** : sofia-sm
- **Participants** : <agents invoques>

## Objectif du sprint

<Une phrase. Ce qui doit etre livrable a la fin. Gele : il ne change plus pendant le sprint.>

## Stories retenues

| Story | Titre | Competence programme visee | Decision liee | Affectee a |
|---|---|---|---|---|
| <S03-1> | <titre> | <matiere / niveau / competence, ou "transverse"> | <DEC-XXXX ou -> | <marc-dev> |

## Avis de faisabilite - theo-archi

| Story | Avis | ADR requis |
|---|---|---|
| <S03-1> | <faisable en l'etat / faisable moyennant ADR / infaisable sans revoir le decoupage> | <ADR-XXXX ou non> |

## Decisions prises

<Liste, ou "Aucune decision prise pendant ce planning.">

## Prochaines etapes

<Ce qui demarre immediatement, par agent.>
```

---

## 2. Review - `squad/sprints/sNN-<slug>/review.md`

```markdown
# [Sprint sNN] Review - <AAAA-MM-JJ>

- **Horodatage de debut** : <repris du planning>
- **Horodatage de fin** : <AAAA-MM-JJ HH:MM>
- **Duree effective** : <fin moins debut>
- **Anime par** : sofia-sm

## Increment livre

<Une phrase comprehensible sans contexte.>

## Rapport qualite - yanis-qa

- **Commande** : `<la commande exacte>`
- **Resultat** : <vert / rouge, avec le compte de tests>
- **Couvert** : <ce qui est couvert>
- **Non couvert** : <les trous assumes>
- **Tests instables** : <nommes, ou "aucun">

## Confirmation ArchUnit - theo-archi

- **Commande** : `<la commande exacte>`
- **Resultat** : <vert / rouge>
- **Regles ajoutees ce sprint** : <liste, ou "aucune">

## Relecture - relecteur-pedagogique

- **Corrections appliquees** : <liste courte>
- **Points a trancher** : <liste, avec l'agent vers qui ils partent>

## Obstacles du sprint

| Description | Categorie | Agent route | Decision liee | Statut |
|---|---|---|---|---|
| <description> | <produit / technique / qualite / pedagogie / redaction / capacite> | <agent> | <ADR-XXXX, DEC-XXXX ou -> | <ouvert / route / decide / clos> |

Rappel : un obstacle n'est `clos` que si le fichier de decision existe physiquement.

## Porte pedagogique - nadia-prof

- **Verdict** : <VALIDE / VALIDE SOUS RESERVE / REFUSE>
- **Fiche** : `squad/conformite/FICHE-<jeu>.md`
- **Motif** : <ce qui a ete verifie, ou ce qui bloque - actionnable>
- **Reserves** : <si VALIDE SOUS RESERVE, la liste nommee>

## Decisions prises

<Liste, ou "Aucune decision prise pendant cette review.">
```

---

## 3. Retrospective - `squad/sprints/sNN-<slug>/retro.md`

```markdown
# [Sprint sNN] Retrospective - <AAAA-MM-JJ>

- **Anime par** : sofia-sm

## Ce qui a marche

<Concret. Pas "bonne collaboration".>

## Ce qui a coince

<Concret. Avec le cout reel quand il est mesurable.>

## Amendements au process

| Section de squad/README.md | Changement | Raison |
|---|---|---|
| <sectionN> | <ce qui change> | <ce qui l'a declenche> |

<Ou : "Aucun amendement au process ce sprint.">

## Decisions prises

<Liste, ou "Aucune decision prise pendant cette retro.">
```

---

## 4. Synthese de sprint - `squad/sprints/sNN-<slug>/RAPPORT.md`

```markdown
# [Sprint sNN] Rapport de sprint - <slug>

- **Debut** : <AAAA-MM-JJ HH:MM>
- **Fin** : <AAAA-MM-JJ HH:MM>
- **Duree effective** : <duree>

## Increment livre

<Une phrase comprehensible sans contexte, puis 3 a 5 lignes de detail.>

## Decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| <ADR-0002> | <titre> | Architecture | <Accepte> |
| <DEC-0004> | <titre> | Produit | <Acceptee> |

<Si aucune : "Ce sprint n'a produit aucune decision ADR ni DEC." - a ecrire explicitement,
ne pas laisser la section vide.>

## Etat des deux Done

| Done | Juge par | Etat | Reference |
|---|---|---|---|
| Technique | CI (`mvn verify`) | <vert / rouge> | <lien vers le rapport qualite> |
| Pedagogique | nadia-prof | <VALIDE / VALIDE SOUS RESERVE / REFUSE> | `squad/conformite/FICHE-<jeu>.md` |

## Obstacles

**Resolus** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|

## Couverture programme

<Lignes de MATRICE-couverture.md ayant change de statut ce sprint.>

## Consommation de tokens

<Tableau repris de RAPPORT-tokens.md, genere par outils/rapport-tokens.py.>
```

---

## 5. ADR - `squad/decisions/adr/ADR-XXXX-<slug>.md`

```markdown
# ADR-XXXX - <titre a l'imperatif>

- **Statut** : <Propose / Accepte / Rejete / Remplace par ADR-YYYY>
- **Date** : <AAAA-MM-JJ>
- **Auteur** : theo-archi
- **Sprint** : <sNN>

## Contexte

<Le probleme reel, pas la solution deguisee en probleme.>

## Decision

<Ce qu'on fait, a l'imperatif.>

## Regle executable ArchUnit

Fichier : `<chemin du test>`

```java
<le code de la regle>
```

<Cette section n'est jamais vide. Si la decision n'est pas encodable en ArchUnit, ecrire
pourquoi et quel autre mecanisme automatique la garde : test d'integration, verification de
build, etape CI.>

## Consequences

<Y compris les mauvaises.>

## Alternatives considerees

<Et pourquoi elles ont ete ecartees.>
```

---

## 6. Decision produit - `squad/decisions/produit/DEC-XXXX-<slug>.md`

```markdown
# DEC-XXXX - <titre>

- **Type** : <story / contenu pedagogique / priorisation / portee>
- **Date** : <AAAA-MM-JJ>
- **Auteur** : camille-po
- **Sprint** : <sNN>
- **Competence programme visee** : <matiere / niveau / competence, ou "transverse">
- **Statut** : <Proposee / Acceptee / Refusee / Remplacee par DEC-YYYY>

## Contexte

<Ce qui a amene la question.>

## Decision

<Ce qu'on fait.>

## Justification pedagogique

<Pourquoi c'est bon pour l'enfant vise. Si la decision est transverse, l'ecrire.>

## Impact

<Sur le backlog, sur la matrice de couverture, sur les jeux existants.>
```

---

## 7. Fiche de conformite - `squad/conformite/FICHE-<jeu>.md`

Ouverte par `camille-po`, qui remplit tout sauf le dernier bloc.
Le bloc de validation est rempli **exclusivement** par `nadia-prof`.

```markdown
# Fiche de conformite - <nom du jeu>

- **Mecanique** : <mecanique>
- **Contenu(s)** : <liste>
- **Statut global** : <en construction / VALIDE / VALIDE SOUS RESERVE / REFUSE>
- **Derniere mise a jour** : <AAAA-MM-JJ>

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|

## Criteres pedagogiques attendus - camille-po

- [ ] <critere observable>
- [ ] <critere observable>

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
```

---

## 8. Ligne de matrice - `squad/programme/MATRICE-couverture.md`

```markdown
| Matiere | Niveau | Domaine | Competence | Jeu(x) couvrant | Mecanique | Statut |
|---|---|---|---|---|---|---|
| <mathematiques> | <CP> | <nombres> | <competence> | <jeu ou -> | <mecanique ou -> | <non couvert / en cours / couvert-valide> |
```

Une ligne passe a `couvert-valide` **uniquement** apres un verdict `VALIDE` de `nadia-prof`.
Jamais sur la foi d'une CI verte.

---

## 9. Index - `ADR-index.md` et `DEC-index.md`

```markdown
| Reference | Titre | Sprint | Date | Statut |
|---|---|---|---|---|
```

Une ligne par decision, tenue a jour a chaque creation et a chaque changement de statut.
Les numeros ne sont **jamais reutilises**, meme pour une decision rejetee.
