# Fiche de conformite - bandit-manchot-regles-vie-collective-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-emc-regles-vie-
  collective.yaml` (11 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-regles-vie-collective-cm1`, titre servi *"Les regles de
  la vie collective"*, consigne servie *"Lis bien, puis dis ce qu'il faut faire a l'ecole, ou
  pourquoi cette regle existe."*, `nombreDeManches: 10`.
- **Ligne declaree** : `EMC-CM1-04`.
- **Statut global** : **VALIDE**. Done pedagogique **prononce le 2026-09-23** par `nadia-prof`,
  apres avoir joue le jar servi.
- **Arbitrage prealable** : `ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-
  plafond-a-huit-lignes.md`, ligne 427 - "la moitie 'a quoi elles servent' est ce qui distingue la
  ligne d'un reglement recite". Referentiel : `squad/programme/enseignement-moral-et-
  civique/competences-cm1.md`, ligne 4.
- **Derniere mise a jour** : 2026-09-23 (premier audit, s40).

---

## 1. Ce que j'ai joue

Reacteur construit (`mvn -B clean package -DskipTests` via `bash outils/mvn-jdk21.sh`, worktree
`s40-nadia-2` a `652cafc8`), `bootstrap/target/recreo.jar` lance sur le JDK 21 designe par la
toolchain. Partie complete de dix manches jouee jusqu'a l'ecran de fin (10 reussites sur 10), les
deux taches vues a l'ecran - "que doit faire un eleve" (six situations : bibliotheque, classe,
cantine, car, travail de groupe, sport) et "pourquoi" (les cinq memes situations justifiees).

## 2. Le risque que je verifie en priorite sur cette ligne : le reglement recite

L'arbitrage prealable pose que la moitie *"a quoi elles servent"* est ce qui distingue cette ligne
d'un reglement recite. Verifie item par item sur les cinq items de justification (7 a 11) : chacun
donne une **raison de fond**, jamais une reformulation de la regle elle-meme -
*"pour que chacun retrouve facilement ce qu'il cherche"*, *"pour que chacun comprenne la lecon"*,
*"pour que la cantine reste propre pour tous"*, *"pour etre protege en cas de freinage brusque"*,
*"pour que la classe suivante puisse s'en servir"*. Les distracteurs de cette tache sont des motifs
plausibles mais superficiels (*"pour que la bibliotheque paraisse plus grande"*, *"pour montrer
qu'on est sage"*) : ils tentent un enfant qui n'a pas reflechi a la fonction reelle de la regle,
sans jamais etre defendables comme une seconde bonne reponse. **La ligne sert bien la competence
telle qu'annoncee, pas une version affaiblie.**

## 3. `EMC-1`, `EMC-6`, `EMC-8`, `EMC-10` - verifies

- **`EMC-8`** : aucun item ne demande "que fais-tu si...". Chaque item porte sur "un eleve", jamais
  sur l'enfant qui joue - verifie sur les onze enonces.
- **`EMC-1`** : aucun item n'interroge l'enfant sur sa propre vie.
- **`EMC-6`** : toutes les situations se passent a l'ecole (bibliotheque, classe, cantine, sortie
  scolaire, cour), aucune ne suppose un lieu de vie ou une configuration familiale.
- **`EMC-10`** : aucun personnage nomme, uniquement "un eleve", "les eleves".

## 4. Ancrage sur `EMC-CM1-04`

Chaque item porte sur une regle de vie collective a l'ecole et sa raison d'etre, jamais sur une
autre ligne d'EMC (aucun droit ni devoir individuel, ce qui est `EMC-CM1-07`).

## 5. Lisibilite, vocabulaire, ton, biais

Enonces d'une a deux phrases, situations concretes et variees (bibliotheque, classe, cantine,
sortie scolaire, sport), vocabulaire accessible au CM1. Aucun biais de genre, d'origine ou de
milieu social : les situations sont generiques et interchangeables entre eleves.

## 6. Ce dont je reste la seule garde

Le caractere non recite des cinq items de justification (section 2) et le respect de `EMC-1`,
`EMC-6`, `EMC-8`, `EMC-10` (section 3) ne sont verifies par aucun garde-fou automatique. C'est moi
qui les tiens, fait ce jour.

---

## Bloc de validation

> **Done pedagogique : VALIDE.**
>
> **Prononce le 2026-09-23 par `nadia-prof`, apres avoir joue le jar servi.**
>
> **Ce que j'ai verifie** : les 11 items, l'absence de reglement recite sur la tache de
> justification, `EMC-1`, `EMC-6`, `EMC-8`, `EMC-10` (tenues), l'ancrage sur `EMC-CM1-04`, la
> lisibilite et l'absence de biais.
>
> **Mention a reprendre par `camille-po` dans `MATRICE-couverture.md`** (referentiel, section
> "Mention exigee d'avance si une ligne bouge") : *"connaissances civiques et reconnaissance de
> situations seules ; aucun comportement, aucun engagement et aucune participation a une decision
> collective ne sont servis par ce produit."*
>
> **Mouvement de ligne** : ce Done ouvre la voie a `EMC-CM1-04` en `couvert-valide`. Le mouvement
> lui-meme est ecrit par `camille-po`, pas par moi.
