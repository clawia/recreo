# Fiche de conformite - bandit-manchot-longueurs-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-longueurs.yaml`
  (14 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-longueurs-ce1`, titre servi *"Les longueurs"*, consigne
  servie *"Compare les longueurs, ou convertis-les en centimètres et en mètres."*,
  `nombreDeManches: 10`.
- **Ligne declaree** : `MAT-CE1-09`.
- **Statut global** : **VALIDE, mais uniquement sur la part textuelle livree ("comparer" et
  "utiliser cm/m") - PAS sur la ligne entiere.** Le verbe "mesurer... avec une regle graduee" n'est
  pas servi par ce paquet et ne peut pas l'etre par du texte seul (mon arbitrage prealable du
  2026-09-22, section 2). **La ligne `MAT-CE1-09` reste `en cours` dans la matrice, quel que soit ce
  Done** : ce n'est pas une reserve de ma part, c'est une limite deja actee avant meme que ce paquet
  existe. `camille-po` ne peut pas faire passer cette ligne a `couvert-valide` sur la base de ce
  Done seul. Le titre et la consigne servis a l'enfant sont honnetes sur ce point : ni l'un ni
  l'autre ne mentionne une regle graduee.
- **Arbitrage prealable** : `ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md`, section 2 et
  son amendement du meme jour (`MES-CE1-1` a `MES-CE1-3`, version amendee).
- **Derniere mise a jour** : 2026-09-22 (premier audit, s39).

---

## 1. Ce que j'ai joue

Reacteur construit (`mvn -B clean verify` via `bash outils/mvn-jdk21.sh`, `BUILD SUCCESS`),
`bootstrap/target/recreo.jar` lance sur le JDK 21 designe par la toolchain, jeu sur l'ecran
reellement servi. Manche jouee : *"2 m, combien de cm ?"*, propositions *"2 cm"*, *"20 cm"*,
*"200 cm"* - lisible d'un coup d'oeil. Comportement d'erreur identique aux autres paquets textuels
deja valides.

## 2. Exactitude - les 14 items relus

- **Conversions cm/m** (7 items) : `100 cm = 1 m`, `300 cm = 3 m`, `2 m = 200 cm`, `4 m = 400 cm`,
  `500 cm = 5 m`, `1 m = 100 cm`, `200 cm = 2 m` - toutes exactes, toutes sur le seul cas simple
  (nombre de metres entier).
- **Comparaisons** (7 items) : les sept classements (plus longue/plus courte/ni l'une ni l'autre)
  recalcules sur les triplets d'objets donnes - tous corrects, et les objets choisis sont plausibles
  pour un enfant de CE1 (crayon, trousse, gomme, table, chaise, porte, stylo, regle, salle de
  classe, couloir, armoire, cahier, classeur, carte postale, tableau, fenetre, ardoise).

## 3. `MES-CE1-1` a `MES-CE1-3` (version amendee) - verifiees

- **`MES-CE1-1`** (1 cm a 500 cm, objets plausibles) : toutes les longueurs tirees sont comprises
  entre 5 cm et 5 m, sur des objets ou distances credibles pour un enfant de CE1. Tenue.
- **`MES-CE1-2`** (conversions au nombre de metres entier seulement) : verifie sur les 7 items de
  conversion, aucune fraction ni millimetre. Tenue.
- **`MES-CE1-3`** (version amendee du 2026-09-22) : les distracteurs de conversion sont des erreurs
  de facteur (`x10`, `/10`, ou absence de conversion), **jamais une unite differente de la bonne
  reponse dans la meme proposition** - verifie item par item, les trois propositions de chaque item
  de conversion sont dans la meme unite (les 7 items en `m` ont leurs trois propositions en `m`, ou
  inversement en `cm`). Tenue, exactement dans la forme que l'amendement exige apres la collision
  trouvee par `theo-archi` avec `ADR-0041`.

## 4. Ancrage - ce que ce paquet sert, et ce qu'il ne sert pas

Le paquet sert reellement "comparer des longueurs" et "utiliser le centimetre et le metre"
(convertir). **Il ne sert pas "mesurer avec une regle graduee"** : aucun item ne presente une image
de regle ni ne demande de lire une graduation - toutes les longueurs sont donnees en chiffres dans
l'enonce, ce qui revient a avoir deja mesure a la place de l'enfant sur ce seul verbe. Ce n'est pas
un defaut de ce paquet, c'est la limite deja actee d'un paquet purement textuel sur cette ligne.

## 5. Lisibilite, vocabulaire, ton, biais

Enonces courts (une phrase de contexte, une question), vocabulaire de CE1, comparaisons ancrees
dans des objets familiers de la classe. Aucun biais, aucun ton infantilisant.

## 6. Ce dont je reste la seule garde

L'exactitude des 14 conversions et comparaisons (section 2) et la plausibilite des objets choisis
(`MES-CE1-1`, section 3) ne sont verifiees par aucun garde-fou automatique. C'est moi qui les
tiens, fait ce jour.

---

## Bloc de validation

> **Done pedagogique : VALIDE, limite a la part textuelle ("comparer" et "utiliser cm/m").**
>
> **Prononce le 2026-09-22 par `nadia-prof`, apres avoir joue le jar servi.**
>
> **Ce que j'ai verifie** : les 14 items (exacts), `MES-CE1-1` a `MES-CE1-3` version amendee
> (toutes tenues, y compris la collision avec `ADR-0041` correctement evitee), la lisibilite et
> l'absence de biais.
>
> **Ce que ce Done ne couvre pas et ne peut pas ouvrir** : le verbe "mesurer avec une regle
> graduee", non servi par ce paquet et non servable par du texte seul. **La ligne `MAT-CE1-09` reste
> `en cours`** quel que soit ce Done - ce n'est pas une reserve actionnable dans ce sprint, c'est une
> limite deja nommee a `theo-archi` et `camille-po` (rendu visuel de regle graduee a construire).
