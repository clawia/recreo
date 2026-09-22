# Fiche de conformite - bandit-manchot-nombres-jusqu-a-100-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-nombres-jusqu-a-100.yaml`
  (21 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-nombres-jusqu-a-100-cp`, titre servi *"Les nombres
  jusqu'à 100"*, consigne servie *"Lis le nombre, écris-le, ou trouve ses dizaines et ses
  unités."*, `nombreDeManches: 10`.
- **Ligne declaree** : `MAT-CP-01`.
- **Statut global** : **VALIDE, mais uniquement sur la part textuelle livree (lire, ecrire,
  decomposer) - PAS sur la ligne entiere.** `DEN-2` ("denombrer") n'est pas servie par ce paquet et
  ne peut pas l'etre par une mecanique textuelle (mon arbitrage prealable du 2026-08-24, reconfirme
  le 2026-09-22, section 1). **La ligne `MAT-CP-01` reste `en cours` dans la matrice, quel que soit
  ce Done** : ce n'est pas une reserve de ma part, c'est une limite deja actee avant meme que ce
  paquet existe. `camille-po` ne peut pas faire passer cette ligne a `couvert-valide` sur la base de
  ce Done seul.
- **Arbitrage prealable** :
  `ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`, section 1.2 (`DEN-2`
  non negociable) ; `ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md`, section 1
  (confirmation, verifiee sur le code par `theo-archi`) ; conditions `DEN-1`, `DEN-4` a `DEN-8`,
  `GN-1`/`GN-2` (arbitrage de s12).
- **Derniere mise a jour** : 2026-09-22 (premier audit, s39).

---

## 1. Ce que j'ai joue

Reacteur construit (`mvn -B clean verify` via `bash outils/mvn-jdk21.sh`, `BUILD SUCCESS`),
`bootstrap/target/recreo.jar` lance sur le JDK 21 designe par la toolchain, jeu sur l'ecran
reellement servi. Manche jouee : *"Lis ce nombre : 60."*, propositions *"cinquante"*,
*"quarante"*, *"soixante"* - lisible d'un coup d'oeil. Comportement d'erreur identique aux autres
paquets textuels deja valides.

## 2. Exactitude - les 21 items relus

- **Verbe "lire"** (7 items, 72 a 100) : les sept designations litterales sont orthographiquement
  correctes et correspondent exactement au nombre chiffre.
- **Verbe "ecrire"** (7 items) : les sept ecritures chiffrees correspondent exactement a la
  designation litterale donnee.
- **Verbe "decomposer"**, dizaines/unites (4 items) et addition a trou (3 items) : les 7 reponses
  (nombre de dizaines, nombre d'unites, terme manquant) recalculees, toutes exactes.

## 3. `DEN-1`, `DEN-4` a `DEN-8`, `GN-1`/`GN-2` - verifiees, comptees par moi

- **`DEN-5`** : le mot "decompose"/"decomposer" n'apparait dans aucun enonce ni dans la consigne du
  catalogue - verifie sur les 21 items et sur la consigne servie.
- **`DEN-6`** (au moins un tiers des items sur un nombre entre 60 et 99) : recompte par moi, item
  par item, sur le nombre effectivement en jeu dans chaque item (pas seulement le champ
  `bonneReponse` litteral) : `72, 91, 60, 80` (lire), `92, 71, 60, 83` (ecrire), `83, 76`
  (decomposition dizaines/unites), `91` (addition a trou) - **11 items sur 21 (52 %)**, tenue avec
  marge.
- **`DEN-7`** : un nombre entre 11 et 16 en bonne reponse ("quinze") et des multiples de dix sans
  unite ("soixante", "quatre-vingts") sont bien presents. Tenue.
- **`DEN-4`** (au moins la moitie des items de decomposition avec vocabulaire dizaines/unites) :
  4 items sur 7 items de decomposition (57 %) emploient "dizaines"/"unites", les 3 autres completent
  une addition a trou. Tenue, exactement a la borne annoncee.
- **`DEN-8`** (meme nombre de chiffres ou longueur litterale comparable entre les trois
  propositions d'un item) : verifie sur les 21 items, aucune proposition ne se distingue par sa
  forme.
- **`GN-1`/`GN-2`** : dans le sens "lire", les distracteurs different par la valeur designee
  (confusions plausibles entre dizaines voisines, entre "soixante" et "quatre-vingt"), jamais par
  une faute d'orthographe du mot-nombre. Dans le sens "ecrire", la designation litterale donnee dans
  l'enonce est ecrite sans faute.

## 4. Ancrage - ce que ce paquet sert, et ce qu'il ne sert pas

Le paquet sert reellement "lire", "ecrire" et "decomposer" (en dizaines et unites) les nombres
jusqu'a 100. **Il ne sert pas "denombrer"** : aucun item ne presente une collection sur laquelle
l'enfant agit, conformement au constat verifie sur le code par `theo-archi`. Ce n'est pas un defaut
de ce paquet - c'est la limite de ce qu'une mecanique textuelle peut servir, deja actee avant son
ecriture.

## 5. Lisibilite, vocabulaire, ton, biais

Enonces tres courts ("Lis ce nombre : 72.", "58 = 50 + ?"), vocabulaire de CP, indices qui donnent
une methode sans donner la reponse. Aucun biais, aucun ton infantilisant.

## 6. Ce dont je reste la seule garde

L'exactitude des 21 items (section 2) et le comptage de `DEN-6`/`DEN-4` (section 3) ne sont
verifies par aucun garde-fou automatique. C'est moi qui les tiens, fait ce jour.

---

## Bloc de validation

> **Done pedagogique : VALIDE, limite a la part textuelle (lire, ecrire, decomposer).**
>
> **Prononce le 2026-09-22 par `nadia-prof`, apres avoir joue le jar servi.**
>
> **Ce que j'ai verifie** : les 21 items (exacts), `DEN-1`, `DEN-4` a `DEN-8`, `GN-1`/`GN-2`
> (toutes tenues et recomptees), la lisibilite et l'absence de biais.
>
> **Ce que ce Done ne couvre pas et ne peut pas ouvrir** : le verbe "denombrer" (`DEN-2`), non servi
> par ce paquet ni par aucune mecanique existante (verifie sur le code, section 1 de l'arbitrage
> prealable de s39). **La ligne `MAT-CP-01` reste `en cours`** quel que soit ce Done - ce n'est pas
> une reserve actionnable dans ce sprint, c'est une limite d'architecture deja nommee a
> `theo-archi` et `camille-po`.
