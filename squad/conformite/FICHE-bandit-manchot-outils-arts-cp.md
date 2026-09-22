# Fiche de conformite - bandit-manchot-outils-arts-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-arts-outils.yaml`
  (12 items, type `question`) - premier paquet d'arts plastiques du corpus servi.
- **Entree de catalogue** : `bandit-manchot-outils-arts-cp`, titre servi *"Les outils, supports et
  gestes"*, consigne servie *"Dis quel outil, quel support ou quel geste convient."*,
  `nombreDeManches: 10`.
- **Ligne declaree** : `ART-CP-11`.
- **Statut global** : **VALIDE**. Done pedagogique **prononce le 2026-09-22** par `nadia-prof`,
  apres avoir joue le jar servi.
- **Arbitrage prealable** : `ART-1` (aucune reponse portee par une couleur percue), `ART-2` (aucune
  question sur le ressenti) -
  `ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-
  ligne-marginale.md`, ligne 524.
- **Derniere mise a jour** : 2026-09-22 (premier audit, s39).

---

## 1. Ce que j'ai joue

Reacteur construit (`mvn -B clean verify` via `bash outils/mvn-jdk21.sh`, `BUILD SUCCESS`),
`bootstrap/target/recreo.jar` lance sur le JDK 21 designe par la toolchain, jeu sur l'ecran
reellement servi. Manche jouee : *"Avec quel outil dessine-t-on sur un tableau noir ?"*, propositions
*"la craie"*, *"le pinceau"*, *"les ciseaux"* - lisible d'un coup d'oeil, sans effort de lecture
excessif pour un CP. Une erreur volontaire pour lire le traitement de l'erreur, identique aux
autres paquets deja valides.

## 2. Exactitude et plausibilite - les 12 items relus

Trois familles fermees de quatre termes chacune, verifiees toutes les trois :

- **Outils** (pinceau, craie, ciseaux, rouleau) : chaque item nomme correctement l'usage reel de
  l'outil (peindre au pinceau, dessiner a la craie sur tableau noir, decouper aux ciseaux, etaler la
  peinture au rouleau).
- **Supports** (carton, argile, papier, mur) : usages exacts (coller sur du carton rigide, modeler
  l'argile, dessiner sur papier, peindre une fresque sur un mur).
- **Gestes** (esquisse, modelage, collage, decoupage) : definitions exactes et distinctes les unes
  des autres.

Distracteurs plausibles : toujours pris dans la meme famille que la bonne reponse (jamais un outil
propose face a un support), ce qui evite le distracteur absurde tout en maintenant une vraie
difficulte de choix.

## 3. `ART-1` et `ART-2` - verifies

- **`ART-1`** : aucun des 12 items ne nomme ni ne montre une couleur. Tenu.
- **`ART-2`** : chaque item porte sur une connaissance verifiable (quel outil, quel support, quel
  geste), jamais sur un ressenti ou une preference. Tenu.

## 4. `ADR-0089` (lisibilite CP) - verifie a l'ecran, pas seulement compte

Chaque enonce est une phrase unique de neuf a onze mots terminee par un point d'interrogation,
largement sous le plafond de quinze mots et deux phrases. A l'ecran, l'enonce tient sur une a deux
lignes courtes, lisible par un enfant de CP.

## 5. Ancrage sur `ART-CP-11`, premiere ligne d'arts plastiques

Les quatre termes de la parenthese du referentiel (pinceau, craie, argile, esquisse) sont chacun
servis en bonne reponse, conformement au jugement de faisabilite du 2026-09-03. Le vocabulaire est
concret, court, verifiable - aucune question ne demande un jugement esthetique.

## 6. Lisibilite, vocabulaire, ton, biais

Vocabulaire de CP, indices qui decrivent une propriete physique de l'objet sans le nommer ("Ce
petit baton blanc laisse une trace sur une surface sombre"). Aucun biais, aucun ton infantilisant.

## 7. Ce dont je reste la seule garde

La plausibilite des distracteurs (section 2) et le respect d'`ART-1`/`ART-2` (section 3) ne sont
verifies par aucun garde-fou automatique. C'est moi qui les tiens, fait ce jour.

---

## Bloc de validation

> **Done pedagogique : VALIDE.**
>
> **Prononce le 2026-09-22 par `nadia-prof`, apres avoir joue le jar servi.**
>
> **Ce que j'ai verifie** : les 12 items (exacts, distracteurs plausibles dans leur famille),
> `ART-1` et `ART-2` (tenues), `ADR-0089` verifie a l'ecran, l'ancrage sur `ART-CP-11`, la
> lisibilite et l'absence de biais.
>
> **Mouvement de ligne** : ce Done ouvre la voie a `ART-CP-11` en `couvert-valide`. Le mouvement
> lui-meme est ecrit par `camille-po`, pas par moi.
