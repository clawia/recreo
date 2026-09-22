# Fiche de conformite - bandit-manchot-energie-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-energie.yaml`
  (12 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-energie-cm1`, titre servi *"Les sources et les formes
  d'énergie"*, consigne servie *"Lis bien, puis dis quelle est la source ou la forme, ou comment
  l'énergie se transforme."*, `nombreDeManches: 10`.
- **Ligne declaree** : `SCI-CM1-04`.
- **Statut global** : **VALIDE**. Done pedagogique **prononce le 2026-09-22** par `nadia-prof`,
  apres avoir joue le jar servi.
- **Arbitrage prealable** : conditions opposables `FER-1` a `FER-3` et `SCI-3`
  (`ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md`),
  refus 3 (electricite jamais bonne reponse a "source ou forme ?").
- **Derniere mise a jour** : 2026-09-22 (premier audit, s39).

---

## 1. Ce que j'ai joue

Reacteur construit (`mvn -B clean verify` via `bash outils/mvn-jdk21.sh`, `BUILD SUCCESS`),
`bootstrap/target/recreo.jar` lance sur le JDK 21 designe par la toolchain, jeu sur l'ecran
reellement servi. Une manche de chaque tache jouee ("Quelle est la source d'énergie utilisée par un
panneau solaire ?" et "Quelle transformation d'énergie un moulin à vent traditionnel réalise-t-il ?"),
avec une erreur volontaire pour lire le traitement de l'erreur - identique aux autres paquets de
sciences deja valides.

## 2. Exactitude scientifique - les 12 items relus un a un

- Sources (items 1 a 4) : eolienne -> vent, panneau solaire -> soleil, barrage -> eau, centrale a
  charbon -> charbon. Exactes.
- Formes (items 5 et 6) : seche-cheveux -> chaleur, lampadaire -> lumiere. Exactes.
- Transformations (items 7 a 12) : eolienne (vent -> electrique), panneau solaire (lumiere du
  soleil -> electrique), barrage (mouvement de l'eau -> electrique), moulin a vent traditionnel
  (vent -> mecanique, distinct de l'eolienne moderne), lampe de poche (electrique -> lumineuse),
  grille-pain (electrique -> thermique). Toutes exactes et rattachees a un objet reel qui realise la
  transformation (`SCI-3`), jamais a une formule raccourcie.

## 3. `FER-1` a `FER-3` et refus 3 - verifies

- **`FER-1`** : source (items 1-4), forme (items 5-6) et conversion (items 7-12) sont bien servis
  chacun en bonne reponse d'au moins un item.
- **`FER-2`** : 12 items, au-dessus du plancher de 10.
- **`FER-3`** : deux taches distinctes exactement equilibrees, 6 items "source ou forme", 6 items
  "transformation" - la moins servie depasse largement le plancher de 3.
- **Refus 3** : aucun des 12 items ne demande si l'electricite est une source ou une forme ;
  l'electricite n'apparait qu'en resultat (items 7-9) ou en source d'une transformation inverse
  (items 10-12), jamais en bonne reponse a "source ou forme ?". Tenu.

## 4. Ancrage sur `SCI-CM1-04`

Chaque item porte explicitement sur une source, une forme ou une conversion d'energie, ancre a un
objet concret (eolienne, panneau solaire, barrage, centrale, seche-cheveux, lampadaire, moulin,
lampe de poche, grille-pain). Rien ne glisse vers une autre ligne du programme de sciences CM1
(aucune question sur les etats de la matiere ni sur les circuits electriques en tant que tels).

## 5. Lisibilite, vocabulaire, ton, biais

Enonces d'une phrase, vocabulaire scientifique du niveau CM1 (source, forme, transformation,
combustible), indices qui decrivent l'objet sans donner la reponse. Aucun biais, aucun ton
infantilisant - registre neutre et factuel adapte au cycle 3.

## 6. Ce dont je reste la seule garde

L'exactitude scientifique des 12 conversions (section 2) et le respect du refus 3 (section 3)
ne sont verifies par aucun garde-fou automatique - un garde-fou de contenu ne lit pas la physique
d'un enonce. C'est moi qui les tiens, fait ce jour.

---

## Bloc de validation

> **Done pedagogique : VALIDE.**
>
> **Prononce le 2026-09-22 par `nadia-prof`, apres avoir joue le jar servi.**
>
> **Ce que j'ai verifie** : les 12 items (exacts, `SCI-3` tenu), `FER-1` a `FER-3` (tenues), le
> refus 3 (respecte sur les 12 items), l'ancrage sur `SCI-CM1-04`, la lisibilite et l'absence de
> biais.
>
> **Mouvement de ligne** : ce Done ouvre la voie a `SCI-CM1-04` en `couvert-valide`. Le mouvement
> lui-meme est ecrit par `camille-po`, pas par moi.
