# Fiche de conformité - bandit-manchot-additions-posees-ce1

- **Mécanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-additions-posees.yaml`
  (15 items, type `question`), écrit par `marc-dev` (commit `607eb567`, fusion `fdef689b`).
- **Entrée de catalogue** : `bandit-manchot-additions-posees-ce1`, titre servi *"Les additions
  posées"*, consigne servie *"Pose l'opération, puis choisis le bon résultat."*,
  `nombreDeManches: 10`.
- **Ligne déclarée** : `MAT-CE1-04`. **Story** : `S45-MAT-ADDITION` (`docs/backlog/backlog.md`,
  section "Sprint 45").
- **Conditions opposables** : `ADP-CE1-1` à `ADP-CE1-6`
  (`squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`, section 4), lues avec ma
  définition des trois erreurs (`squad/conformite/ARBITRAGE-PREALABLE-s45-cinq-lots.md`, section 2).
- **Statut global** : **VALIDE**, prononcé le 2026-10-01 par `nadia-prof` (s45, instance A). Fiche
  ouverte par ce verdict : aucune fiche n'existait pour ce jeu.
- **Dernière mise à jour** : 2026-10-01.

---

## Bloc de validation pédagogique

### Verdict du 2026-10-01 (`nadia-prof`, s45, instance A) : **VALIDE**

**Done pédagogique prononcé.** `camille-po` peut passer `MAT-CE1-04` à `couvert-valide`, **avec la
mention de la section 5, qui est une condition de ce Done** (`ADP-CE1-1`).

#### 1. Ce que j'ai fait

**Audit complet, pas dégradé.** Jar construit sur `round-two` à `9fcae7dc`, dans un worktree dédié
(`bash outils/mvn-jdk21.sh -B clean package -DskipTests`, le Done technique étant acquis par
`yanis-qa`), lancé sur le JDK 21 de la toolchain. **Six parties complètes** de dix manches, jouées
contre le jar servi par les routes de l'écran : sur chaque partie, un tiers des manches en répondant
juste, un tiers après une erreur, un tiers après trois erreurs jusqu'à la révélation. **Les quinze
items ont été tirés et joués**, et sur chacun les trois propositions affichées sont celles du paquet.

Après une erreur : *"Ce n'est pas ça. Essaie encore !"* et l'indice. Après la troisième : *"Appuie
sur la bonne réponse : ..."*. Écran de fin correct, l'item manqué rappelé avec sa bonne réponse.

Les quinze sommes et les trente distracteurs ont été recalculés par un script de poste, non commité,
qui applique colonne par colonne les trois formes de ma section 2.2 de s45.

#### 2. Ancrage au programme

`MAT-CE1-04` : *"Poser et effectuer une addition avec retenue (nombres à deux ou trois chiffres)."*
**C'est bien cette compétence** : chaque item est une addition de deux nombres nus, chacun porte au
moins une retenue, et la consigne demande de poser. Ce n'est pas `MAT-CE1-03` sous un autre code :
quatorze items sur quinze mettent en jeu un nombre de trois chiffres ou une somme supérieure à 100.

#### 3. Conditions, vérifiées une par une

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `ADP-CE1-1` | **oui** | La consigne servie à l'écran, lue sur les six parties, commence par *"Pose l'opération"*. Mention de la section 5 à écrire dans la matrice |
| `ADP-CE1-2` | **oui** | Trois items à deux chiffres (1 à 3), douze à trois chiffres. Les quinze portent une retenue. Sur les items à trois chiffres, retenue des dizaines vers les centaines sur onze items (5 à 15), deux retenues sur dix (6 à 15). Comptes faits au calcul, colonne par colonne, pas de tête |
| `ADP-CE1-3` | **oui** | 14 sur 15 (seul `48 + 26` reste sous 100 avec deux nombres à deux chiffres), au-dessus des deux tiers |
| `ADP-CE1-4` | **oui** | Les trois propositions ont le même nombre de chiffres sur les quinze items (deux sur l'item 1, trois ailleurs), le même ordre de grandeur, et le chiffre des unités est partagé par les deux distracteurs sur chaque item |
| `ADP-CE1-5` | **oui** | **Les trente distracteurs correspondent tous à la forme que le paquet déclare**, recalculée : retenue oubliée au sens `48 + 26 -> 64` (jamais `614`), reportée deux fois au sens de la retenue ajoutée deux fois à la colonne suivante, mauvaise colonne toujours vers les centaines (items 6, 7, 9, uniquement à trois chiffres). Les six exemples chiffrés de ma section 2.2 qui figurent dans le paquet (`48 + 26`, `67 + 56`, `247 + 186`, `178 + 265`, `247 + 128`, `253 + 184`) reprennent exactement mes valeurs. Distracteurs entiers, positifs, distincts |
| `ADP-CE1-6` | **oui** | Nombres nus, aucune situation, aucune unité |

**Rang de la bonne réponse** : cinq items au milieu, cinq où elle est la plus petite, cinq où elle est
la plus grande. Aucune position ne se devine.

#### 4. Niveau réel

- **Lisibilité** : l'énoncé est l'opération seule ; la consigne tient en une ligne.
- **Charge** : l'enfant pose sur papier, ce que le produit ne voit pas (`ADP-CE1-1`). Les trois
  propositions ne se départagent pas par le dernier chiffre : il faut calculer.
- **Progression** : deux chiffres, puis trois chiffres à une retenue, puis deux retenues. Le tirage ne
  respecte pas cet ordre, ce que je ne demandais pas.
- **Traitement de l'erreur** : deux indices seulement, l'un pour deux chiffres, l'autre pour trois.
  Ils rappellent la méthode et ne disent pas quelle erreur l'enfant a faite. Je l'accepte : un indice
  doit être vrai quel que soit le bouton pressé, et la mécanique n'en porte qu'un par item. Un enfant
  qui a ajouté la retenue deux fois lit *"n'oublie pas de reporter chaque retenue"*, ce qui ne le
  détrompe pas ; il est débloqué par la révélation. **Ce n'est pas une réserve** : aucune de mes
  conditions ne portait sur l'indice, et je ne relève pas la barre après coup.

#### 5. Mention de couverture, condition de ce Done

La matrice porte, sur la ligne `MAT-CE1-04`, cette mention **mot pour mot** (`ADP-CE1-1`) :

> *"le résultat atteste le calcul ; la technique posée elle-même - disposition en colonnes,
> retenues - n'est ni montrée ni vérifiée par le produit."*

#### 6. Ce dont je reste la seule garde

`PaquetCe1AdditionsPoseesTest` relit chaque marqueur `# ADP-CE1-5:` et le compare au calcul. **Aucun
automate ne vérifie** que les trois formes soient des erreurs d'enfant réelles plutôt que des
nombres voisins : c'est le choix des formes, fait par moi en s45, et vérifié ici en jouant.

---

**Signé** : `nadia-prof`, 2026-10-01.
