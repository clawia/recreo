# Templates `squad/`

Six gabarits Markdown vierges, à copier tels quels puis à remplir (placeholders entre `<...>`).
Voir `squad/README.md` pour les conventions de nommage et le déroulé de sprint qui les utilise.

---

## (a) Entrée de cérémonie

Fichier : `squad/ceremonies/AAAA-MM-JJ-sNN-<ceremonie>.md` avec `<ceremonie>` ∈ {`planning`,
`daily`, `review`, `retro`}.

```markdown
# Sprint <sNN> — <Planning|Daily|Review|Rétro> — <AAAA-MM-JJ>

- **Sprint** : <sNN>
- **Cérémonie** : <planning|daily|review|retro>
- **Horodatage début** : <AAAA-MM-JJ HH:MM> <!-- obligatoire sur le planning du sprint -->
- **Horodatage fin** : <AAAA-MM-JJ HH:MM> <!-- obligatoire sur la review du sprint -->
- **Durée effective** : <Xh Ymin> <!-- fin − début, reportée dans JOURNAL-durees.md -->
- **Animé par** : <agent, ex. sofia-sm>
- **Participants** : <liste des agents impliqués>

## Objectif / contenu du sprint

<objectif du sprint (planning) ou rappel bref (daily/review/retro)>

## Avancement

<ce qui a été fait depuis la dernière cérémonie ; pour un planning : stories retenues et
affectations ; pour une review : résultat CI, incrément livré, mise à jour matrice/fiche>

## Obstacles

<tableau rempli au daily (et, si besoin, aux autres cérémonies) ; vide si aucun obstacle>

| Description | Catégorie | Agent routé | Décision liée | Statut |
|---|---|---|---|---|
| <description de l'obstacle> | <produit/pédago \| technique \| qualité \| capacité> | <camille-po \| theo-archi \| yanis-qa \| sofia-sm> | <ADR-XXXX \| DEC-XXXX \| — > | <ouvert \| routé \| décidé \| clos> |

## Décisions prises pendant la cérémonie

<liste des ADR/DEC créés ou mis à jour pendant cette cérémonie, avec lien>

## Prochaines étapes

<ce qui doit se passer avant la prochaine cérémonie>
```

---

## (b) ADR technique

Fichier : `squad/decisions/adr/ADR-XXXX-<slug>.md`. Rédigé par `theo-archi`.

```markdown
# ADR-XXXX — <Titre court de la décision technique>

- **Statut** : <Proposé | Accepté | Rejeté | Remplacé par ADR-YYYY>
- **Date** : <AAAA-MM-JJ>
- **Auteur** : theo-archi
- **Sprint** : <sNN>

## Contexte

<quel problème/besoin technique motive cette décision ; contraintes du projet (Clean
Architecture, Java 21, Maven multi-module, htmx/Thymeleaf, fat jar, etc.)>

## Décision

<la décision retenue, formulée sans ambiguïté>

## Règle exécutable ArchUnit

<la règle ArchUnit (ou le test) qui matérialise cette décision et casse le build si elle est
violée ; chemin du test dans le dépôt>

```java
<extrait de règle ArchUnit, ex. noClasses()...should()...>
```

## Conséquences

<impacts positifs et négatifs, dette technique introduite ou évitée>

## Alternatives considérées

<autres options envisagées et pourquoi elles ont été écartées>
```

---

## (c) Décision produit/pédago (DEC)

Fichier : `squad/decisions/produit/DEC-XXXX-<slug>.md`. Rédigé par `camille-po`.

```markdown
# DEC-XXXX — <Titre court de la décision produit/pédago>

- **Type** : <story | contenu pédagogique | priorisation | portée matière/niveau>
- **Date** : <AAAA-MM-JJ>
- **Auteur** : camille-po
- **Sprint** : <sNN>
- **Compétence programme visée** : <matière — niveau (CP/CM1) — domaine — référence programme>
- **Statut de validation** : <À VALIDER | VALIDÉ (user, AAAA-MM-JJ) | REFUSÉ | REVU>

## Contexte

<pourquoi cette décision est nécessaire (story, obstacle produit routé par Sofia, retour de
démo...)>

## Décision

<la décision retenue>

## Justification pédagogique

<en quoi ça sert l'apprentissage de la compétence visée, pour le niveau concerné>

## Impact

<sur la matrice de couverture, sur le backlog, sur d'autres jeux/mécaniques, sur la fiche de
conformité>
```

---

## (d) Fiche de conformité

Fichier : `squad/conformite/FICHE-<jeu>.md`. Rédigée et tenue à jour par `camille-po`.

```markdown
# Fiche de conformité — <Nom du jeu>

- **Mécanique** : <ex. bandit manchot>
- **Contenu(s) pédagogique(s)** : <ex. calcul mental CP, calcul mental CM1>
- **Statut global** : <En cours | Couvert & validé | Suspendu>
- **Dernière mise à jour** : <AAAA-MM-JJ, par <agent>>

## Compétences programme couvertes

| Matière | Niveau | Domaine | Compétence (réf. programme) | Statut |
|---|---|---|---|---|
| <matière> | <CP/CM1> | <domaine> | <compétence> | <non couvert \| en cours \| couvert-validé> |

## Critères pédagogiques

<liste des critères qui font qu'on considère le contenu pédagogiquement solide : progressivité,
feedback immédiat, absence de piège hors-programme, adaptation au niveau, accessibilité
enfant...>

- [ ] <critère 1>
- [ ] <critère 2>

## Bloc validation utilisateur (Done pédagogique)

- **Démo jouée le** : <AAAA-MM-JJ>
- **Validée par l'utilisatrice** : <Oui/Non>
- **Date de validation** : <AAAA-MM-JJ ou —>
- **Commentaires** : <retours libres de l'utilisatrice>

## Bloc playtest enfants

- **Session tenue le** : <AAAA-MM-JJ ou — (à venir)>
- **Niveau(x) testé(s)** : <CP / CM1 / les deux>
- **Nombre d'enfants** : <n>
- **Observations** : <ce qui a fonctionné, ce qui a bloqué, ajustements suggérés>
- **Suites données** : <DEC / stories créées à la suite du playtest>
```

---

## (e) File d'attente

Fichier : `squad/validation/FILE-ATTENTE.md`.

```markdown
# File d'attente — validation pédagogique

> **Règle d'or** : jamais plus de **2-3 sprints** d'incréments non validés pédagogiquement dans
> cette file. Au-delà, le planning suivant déclenche un **GEL DU CONTENU** (priorité à la
> préparation de démo, pas de nouvelle story de contenu tant que la file n'est pas redescendue
> sous le seuil).

| Sprint | Incrément | Jeu / contenu | Compétence programme visée | Poussé en file le | Statut | Décidé le |
|---|---|---|---|---|---|---|
| <sNN> | <description courte> | <jeu> | <compétence> | <AAAA-MM-JJ> | <en cours \| À VALIDER \| VALIDÉ \| REFUSÉ> | <AAAA-MM-JJ ou —> |
```

---

## (f) Ligne de matrice de couverture

Fichier : `squad/programme/MATRICE-couverture.md`. Une ligne = une compétence programme.

```markdown
| <Matière> | <CP/CM1> | <Domaine> | <Compétence (réf. programme)> | <Jeu(x) couvrant> | <Mécanique> | <non couvert \| en cours \| couvert-validé> |
```
