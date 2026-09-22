# Fiche de conformite - bandit-manchot-monnaie-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-monnaie.yaml`
  (12 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-monnaie-ce1`, titre servi *"La monnaie et le rendu"*,
  consigne servie *"Compte la somme, ou trouve ce qui est rendu."*, `nombreDeManches: 10`.
- **Ligne declaree** : `MAT-CE1-13` (sous-partie "monnaie", `ADR-0098`).
- **Statut global** : **VALIDE**. Done pedagogique **prononce le 2026-09-22** par `nadia-prof`,
  apres avoir joue le jar servi.
- **Arbitrage prealable** : `ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md`, section 3
  (`MON-CE1-1` a `MON-CE1-4`).
- **Derniere mise a jour** : 2026-09-22 (premier audit, s39).

---

## 1. Ce que j'ai joue

Reacteur construit (`mvn -B clean verify` via `bash outils/mvn-jdk21.sh`, `BUILD SUCCESS`, six
modules verts), `bootstrap/target/recreo.jar` lance sur le JDK 21 designe par la toolchain, jeu sur
l'ecran reellement servi (fragments htmx recuperes et rendus avec la feuille de style reelle du
jar). Une manche de composition ("Léa a une pièce de 2 euros et une pièce de 50 centimes...") et
une manche de rendu ("Un crayon coûte 180 centimes...") jouees a l'ecran, avec une erreur
volontaire sur chacune pour lire le traitement de l'erreur : proposition fautive barree, indice
affiche, essai suivant permis. Comportement identique aux autres paquets de bandit manchot deja
valides - rien de neuf sur ce plan.

## 2. Exactitude - les 12 items recalcules

Recalcules a la main :

- Composition (6 items) : `250`, `150`, `450`, `550`, `30`, `80` centimes - toutes exactes.
- Rendu (6 items) : `200-180=20`, `500-350=150`, `1000-780=220`, `2000-1650=350`, `500-320=180`,
  `5000-4200=800` - toutes exactes.

## 3. `MON-CE1-1` a `MON-CE1-4` - verifiees une a une

- **`MON-CE1-1`** (montants 0,05 a 50 euros, centimes multiples de 5) : les 12 montants sont
  compris entre 30 centimes et 4200 centimes, tous multiples de 5 par construction (aucune piece de
  1 ou 2 centimes utilisee).
- **`MON-CE1-2`** (denominations reelles) : pieces de 10, 20, 50 centimes, 1 et 2 euros, billets de
  5, 10, 20 et 50 euros - aucune valeur inventee sur les 12 items.
- **`MON-CE1-3`** (au moins un tiers sur le rendu) : 6 items sur 12 (50 %) portent explicitement
  "Combien lui rend-on ?" - tenue avec marge.
- **`MON-CE1-4`** (distracteurs plausibles, jamais negatifs) : sur les 6 items de composition, les
  distracteurs sont une confusion d'echelle (`25`/`2500` pour `250`, `x10` ou `/10`) ou une erreur
  d'addition proche (`400` au lieu de `450`) ; sur les 6 items de rendu, des rendus par exces ou par
  defaut proches de la bonne reponse (`160`/`170` pour `150`, `210`/`230` pour `220`). Aucun
  distracteur negatif ni absurde.

## 4. Sur l'ecriture exclusive en centimes

Les 12 reponses sont exprimees uniquement en centimes ("250 centimes"), jamais en euros ni sous une
forme mixte ("2 euros 50"). C'est un choix technique deja identifie par `theo-archi` (avis de
faisabilite s39, section 21) comme l'une des trois formes servables sans rouvrir `ADR-0068`, laisse
a mon jugement. Je le juge acceptable : la competence demande de composer une somme "en euros et en
centimes", et l'enonce reste ancre en euros (les pieces et billets sont nommes en euros) pendant que
la reponse force explicitement la conversion 1 euro = 100 centimes, ce qui est exactement le savoir
vise. Ce n'est pas la forme la plus naturelle qu'un enfant dirait a voix haute, mais elle n'induit
aucune erreur de fond et elle est tenue avec une coherence stricte sur les 12 items (aucun melange
d'unite dans une meme proposition).

## 5. Ancrage sur `MAT-CE1-13`

Le paquet ne porte aucune reconnaissance visuelle de piece (le precedent `MON-1` du CP ne
s'applique pas, verifie par mon propre arbitrage prealable) : chaque item est une composition ou un
rendu chiffre, exactement les deux verbes de la ligne CE1. Rien ne glisse vers `MAT-CE1-03`
(addition/soustraction generique) : chaque enonce nomme explicitement une situation d'argent
(pieces, billets, achat, monnaie rendue).

## 6. Lisibilite, vocabulaire, ton, biais

Enonces courts, une phrase de contexte suivie d'une question. Prenoms varies (Léa, Sami, Nora,
Malo, Yanis, Inès) sans role marque par genre. Aucun ton infantilisant, aucun stereotype.

## 7. Ce dont je reste la seule garde

L'exactitude arithmetique des 12 items (section 2) et la plausibilite de chaque distracteur
(section 3, `MON-CE1-4`) ne sont verifiees par aucun garde-fou automatique - le garde-fou de
predictibilite du rang ne lit que la position, jamais la valeur. C'est moi qui les tiens, fait ce
jour.

---

## Bloc de validation

> **Done pedagogique : VALIDE.**
>
> **Prononce le 2026-09-22 par `nadia-prof`, apres avoir joue le jar servi.**
>
> **Ce que j'ai verifie** : les 12 sommes et rendus (exacts), `MON-CE1-1` a `MON-CE1-4` (tenues),
> l'ancrage sur `MAT-CE1-13`, la lisibilite et l'absence de biais. Le choix d'ecriture exclusive en
> centimes est juge acceptable (section 4), sans reserve.
>
> **Mouvement de ligne** : ce Done ouvre la voie a `MAT-CE1-13` en `couvert-valide`. Le mouvement
> lui-meme est ecrit par `camille-po`, pas par moi.
