# Fiche de conformite - bandit-manchot-nutrition-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-nutrition.yaml`
  (11 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-nutrition-cm1`, titre servi *"Le corps qui se nourrit"*,
  consigne servie *"Lis bien, puis dis quel organe, ou quelle fonction du corps."*,
  `nombreDeManches: 10`.
- **Ligne declaree** : `SCI-CM1-09`.
- **Statut global** : **VALIDE**. Done pedagogique **prononce le 2026-09-23** par `nadia-prof`,
  apres avoir joue le jar servi.
- **Arbitrage prealable** : `ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-
  sans-image-et-le-prix-d-une-ligne-marginale.md`, section 17.2, ligne `SCI-CM1-09` - "designer un
  organe sur un schema serait du palier 2, non exige. Cette ligne ferme en texte, aujourd'hui, sans
  palier 2." Referentiel : `squad/programme/sciences-et-technologie/competences-cm1.md`, ligne 9.
- **Derniere mise a jour** : 2026-09-23 (premier audit, s40).

---

## 1. Ce que j'ai joue

Reacteur construit (`mvn -B clean package -DskipTests` via `bash outils/mvn-jdk21.sh`, worktree
`s40-nadia-2` a `652cafc8`), `bootstrap/target/recreo.jar` lance sur le JDK 21 designe par la
toolchain, jeu sur l'ecran reellement servi (route `/parties/bandit-manchot-nutrition-cm1`). Une
partie complete de dix manches jouee jusqu'a l'ecran de fin (10 reussites sur 10 questions jouees),
les deux taches vues a l'ecran (organe -> fonction et fonction -> organe), et une erreur volontaire
verifiee sur un item hors de cette partie (indice affiche correctement, pas de fuite de la reponse
avant tentative).

## 2. Exactitude scientifique - les 11 items relus un a un

Trois associations, chacune verifiee dans les deux sens (organe demande a partir de la fonction,
fonction demandee a partir de l'organe) : digestion -> estomac (items 1, 4, 7, 10), respiration ->
poumons (items 2, 5, 8), circulation -> coeur (items 3, 6, 9, 11). Les trois associations sont
exactes au niveau de simplification attendu en cycle 3 (l'estomac comme organe principal de la
digestion, les poumons de la respiration, le coeur de la circulation). Aucune valeur numerique
demandee (`SCI-3` sans objet ici, aucun chiffre dans le paquet).

## 3. `FER-1` a `FER-3` - verifies

- **`FER-1`** : les trois fonctions et les trois organes sont chacun en bonne reponse d'au moins
  deux items.
- **`FER-2`** : 11 items, au-dessus du plancher de 10 (`DEC-0064`).
- **`FER-3`** : deux taches distinctes et equilibrees - organe a partir de la fonction (items 1 a
  6), fonction a partir de l'organe (items 7 a 11).
- **`ADR-0086` clause 1** : les six bonnes reponses possibles comptent chacune moins de trois mots,
  mais la tache varie a chaque paire d'items plutot que le seul enonce, comme le commentaire du
  paquet le revendique et comme je l'ai verifie a l'ecran - aucun risque de bouton gagne au ton ou
  a la position.

## 4. Ancrage sur `SCI-CM1-09`

Chaque item porte explicitement sur l'association fonction/organe, jamais sur un autre fait du
corps humain (aucune question sur l'alimentation, le sommeil ou l'hygiene, qui relevent de
`SCI-CM1-10`). Aucune designation sur schema : conforme a l'arbitrage prealable, qui ferme la ligne
en texte seul.

## 5. Lisibilite, vocabulaire, ton, biais

Enonces d'une phrase, vocabulaire de cycle 3 (digestion, respiration, circulation, organe,
fonction), indices qui decrivent le role de l'organe sans jamais nommer la reponse. Aucun biais,
aucun ton infantilisant, aucune situation qui mette en scene l'enfant lui-meme.

## 6. Ce dont je reste la seule garde

L'exactitude scientifique des trois associations (section 2) n'est verifiee par aucun garde-fou
automatique. C'est moi qui la tiens, fait ce jour.

---

## Bloc de validation

> **Done pedagogique : VALIDE.**
>
> **Prononce le 2026-09-23 par `nadia-prof`, apres avoir joue le jar servi.**
>
> **Ce que j'ai verifie** : les 11 items (exacts), `FER-1` a `FER-3` (tenues), l'ancrage sur
> `SCI-CM1-09`, la lisibilite et l'absence de biais.
>
> **Mouvement de ligne** : ce Done ouvre la voie a `SCI-CM1-09` en `couvert-valide`. Le mouvement
> lui-meme est ecrit par `camille-po`, pas par moi.
