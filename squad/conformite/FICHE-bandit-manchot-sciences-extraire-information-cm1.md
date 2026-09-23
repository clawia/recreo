# Fiche de conformite - bandit-manchot-sciences-extraire-information-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-extraire-
  information.yaml` (12 items, type `question`). **Premier paquet a utiliser `tableauSupport`
  (`ADR-0099`).**
- **Entree de catalogue** : `bandit-manchot-sciences-extraire-information-cm1`, titre servi *"Lis
  le document scientifique"*, consigne servie *"Lis le texte ou le tableau, puis reponds a la
  question."*, `nombreDeManches: 10`.
- **Ligne declaree** : `SCI-CM1-20`, **couverture partielle** (texte et tableau seuls).
- **Statut global** : **VALIDE**. Done pedagogique **prononce le 2026-09-23** par `nadia-prof`,
  apres avoir joue le jar servi.
- **Arbitrage prealable** : `ARBITRAGE-PREALABLE-s32-...-le-prix-d-une-ligne-marginale.md`,
  ligne 1033, mention exigee d'avance : *"document textuel et tableau seuls ; le schema et le
  graphique restent non couverts"*. Referentiel : `squad/programme/sciences-et-
  technologie/competences-cm1.md`, ligne 20.
- **Derniere mise a jour** : 2026-09-23 (premier audit, s40).

---

## 1. Ce que j'ai joue

Reacteur construit (`mvn -B clean package -DskipTests` via `bash outils/mvn-jdk21.sh`, worktree
`s40-nadia-2` a `652cafc8`), `bootstrap/target/recreo.jar` lance sur le JDK 21 designe par la
toolchain. Deux parties completes jouees pour couvrir les deux formes de document sur les dix
manches de chaque tirage (`nombreDeManches: 10` sur un pool de 12 items) : quatre items a tableau
et six items a texte vus a l'ecran au total entre les deux parties, sur les six de chaque type que
porte le paquet.

## 2. Verification demandee en priorite : le tableau ne fuit pas, et ses reponses en sont des cellules

**Rendu inspecte a la source HTML brute** (`curl`, avant tout traitement visuel), sur l'item
"regime alimentaire" avant toute reponse : la consigne, un `<table>` HTML reel (`<thead>` avec
`scope="col"`, `<tbody>` de trois `<tr>`), l'enonce, puis le formulaire de propositions - **rien
d'autre a l'ecran**. Aucun indice, aucune explication, aucune reponse revelee ne sont presents tant
qu'aucune reponse n'est soumise (`th:if` sur `message-jeu`, verifie dans le gabarit
`manche.html` et confirme par la sortie HTML reelle).

**Bonne reponse et distracteurs verifies comme cellules du meme tableau, item par item** :

- Tableau "Animal / Regime alimentaire" (vache-herbivore, loup-carnivore, ours brun-omnivore) :
  chaque item demande le regime d'un des trois animaux, et les deux distracteurs sont exactement
  les deux autres valeurs de la colonne "Regime alimentaire" - jamais une valeur hors du tableau.
- Tableau "Materiau / Etat a temperature ambiante" (fer-solide, eau-liquide, dioxygene-gazeux) :
  meme verification, les deux distracteurs sont les deux autres etats de la meme colonne.

**Aucune reponse ne fuit ailleurs sur l'ecran** : le tableau affiche les trois lignes completes (ce
qui est le but meme de l'exercice - lire un tableau pour en extraire une information), et rien en
dehors du tableau et des boutons de proposition ne nomme une valeur. Verifie a l'ecran et a la
source.

## 3. Exactitude scientifique des deux tableaux et des deux textes

- Regimes alimentaires : vache herbivore, loup carnivore, ours brun omnivore - exacts.
- Etats de la matiere a temperature ambiante : fer solide, eau liquide, dioxygene gazeux - exacts.
- Texte "atmosphere" : la fine couche d'air qui entoure la Terre protege des rayons dangereux du
  Soleil et retient la chaleur, ce qui permet a l'eau de rester liquide - simplification exacte au
  niveau CM1.
- Texte "abeilles" : les abeilles butinent pour recolter du nectar, transportent aussi du pollen,
  ce qui permet aux plantes de produire fruits et graines - exact.

## 4. Chaque reponse est un extrait recopiable, aucune deduction

Verifie sur les 12 items : la reponse attendue est toujours une valeur ou une expression presente
telle quelle (ou a l'identique au genre/nombre pres) dans le texte ou dans une cellule du tableau,
jamais un calcul ni une inference. Sur les textes, la phrase porteuse de la reponse varie d'un item
a l'autre du meme texte (`ADR-0072`/`ADR-0099` decision 4), evitant qu'une seule phrase ne
concentre la majorite des reponses.

**Deux distracteurs faibles releves, sans consequence sur le verdict** : sur l'item "que
transportent aussi les abeilles" (bonne reponse "du pollen"), le distracteur "des epines" est peu
plausible pour un enfant de CM1 (une abeille ne "transporte" pas des epines, l'idee est
immediatement ecartable par bon sens plutot que par lecture) ; de meme "de changer de couleur" sur
l'item suivant. Ce sont deux distracteurs sur douze items, les dix autres etant solides ; je le
note pour un prochain paquet plutot que d'en faire un motif de refus sur ce paquet-ci.

## 5. Lisibilite, vocabulaire, ton, biais

Enonces en deux phrases (consigne de lecture puis question), vocabulaire de cycle 3 (regime
alimentaire, temperature ambiante, atmosphere, nectar, pollen). Aucun biais, ton neutre et
factuel.

## 6. Ce dont je reste la seule garde

L'exactitude scientifique des quatre documents (section 3) et le jugement sur la solidite des
distracteurs (section 4) ne sont verifies par aucun garde-fou automatique. C'est moi qui les tiens,
fait ce jour. Le non-leakage du tableau (section 2) est verifie par construction du gabarit
(`manche.html`), garde d'architecture qui releve de `theo-archi`, pas de moi - je l'ai verifie a
l'ecran en plus de le lire.

---

## Bloc de validation

> **Done pedagogique : VALIDE.**
>
> **Prononce le 2026-09-23 par `nadia-prof`, apres avoir joue le jar servi.**
>
> **Ce que j'ai verifie** : les 12 items, le rendu isole du tableau sans fuite (section 2), les
> bonne reponse et distracteurs comme cellules du meme tableau, l'exactitude des quatre documents,
> l'absence de deduction, la lisibilite et l'absence de biais.
>
> **Mention obligatoire a reprendre par `camille-po` dans `MATRICE-couverture.md`** (arbitrage
> prealable s32, sans laquelle la ligne ne ferme pas) : *"document textuel et tableau seuls ; le
> schema et le graphique restent non couverts."*
>
> **Mouvement de ligne** : ce Done ouvre la voie a `SCI-CM1-20` en `couvert-valide` **avec cette
> mention**. Le mouvement lui-meme est ecrit par `camille-po`, pas par moi.
