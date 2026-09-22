# Fiche de conformite - bandit-manchot-complements-dizaine-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-complements-dizaine.yaml`
  (18 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-complements-dizaine-ce1`, titre servi *"Le compte
  jusqu'a la dizaine"*, consigne servie *"Quel nombre manque ?"*, `nombreDeManches: 10`.
- **Ligne declaree** : `MAT-CE1-03`, sous-partie *"complements a la dizaine pris pour eux-memes"*.
- **Statut global** : **VALIDE**. Done pedagogique **prononce le 2026-09-22** par `nadia-prof`,
  **apres avoir joue le jar servi**. Aucune reserve.
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-s38-soustractions-complements-dizaine-et-moities-ce1.md` (2026-09-22), section 2,
  clauses `CDZ-CE1-1` et `CDZ-CE1-2`, distracteurs section 2.3 (amendee le meme jour, avant tout
  code, sur un fait demontre par `theo-archi`).
- **Derniere mise a jour** : 2026-09-22 (premier audit, s38).

---

## 1. Ce que j'ai joue, et comment

**Audit non degrade.** J'ai construit le reacteur complet (`mvn -B clean package -DskipTests` via
`outils/mvn-jdk21.sh`, `BUILD SUCCESS`), lance `bootstrap/target/recreo.jar` sur le JDK 21 designe
par la toolchain, et joue sur l'ecran reellement servi.

Ce que j'ai fait sur cet ecran : une partie jouee manche par manche, en me trompant volontairement
sur plusieurs manches pour voir le comportement apres une erreur puis apres deux erreurs, et les 18
items du paquet relus dans le fichier de contenu pour recalculer chacun a la main (section 2).

**Ce que l'enfant voit sur une manche** : la consigne *"Quel nombre manque ?"*, l'enonce
`69 + ? = 70`, trois propositions numeriques. Sur une premiere erreur : *"Ce n'est pas ca. Essaie
encore !"*, l'indice affiche (*"Pars de 69 et avance jusqu'a 70, la dizaine suivante."*), la
proposition deja pressee restant cliquable. Sur une deuxieme erreur : meme message, meme indice.
La bonne reponse reste a presser ; aucune revelation forcee n'a ete necessaire sur les manches
jouees, l'enfant gardant toujours la main sur la derniere proposition restante.

## 2. Exactitude - les 18 items recalcules

J'ai recalcule chacun des 18 items a la main : `a`, `d` (le plus petit multiple de dix
strictement superieur a `a`), la bonne reponse (`d - a`) et les deux distracteurs, contre la table
de la section 2.3 de mon arbitrage (indexee sur `unite(a)`, chaque valeur de 1 a 9 apparaissant
deux fois dans le paquet).

**Les 18 items sont exacts, sans exception**, et les 18 paires de distracteurs correspondent
terme a terme a la table de la section 2.3, y compris les deux cas de collision qu'elle traite
explicitement (`unite(a) = 5` : premier distracteur remplace par `unite(a) - 1` ; `unite(a) = 9` :
second distracteur remplace par `d - a + 1`). Aucun distracteur n'est egal a la bonne reponse ni a
l'autre distracteur du meme item.

## 3. `CDZ-CE1-1` et `CDZ-CE1-2` - tenues, et comptees par moi

- **`CDZ-CE1-1`** (bornes de `a` et de `d`, `a` jamais multiple de dix, `d` le plus petit multiple
  de dix strictement superieur) : verifiee sur les 18 items, sans exception.
- **`CDZ-CE1-2`** (couverture de la table et repartition des dizaines) : les neuf valeurs de
  complement (1 a 9) apparaissent **chacune exactement deux fois** (18 items / 9 valeurs). Les
  neuf dizaines possibles (10-19 a 90-99) sont **toutes les neuf representees** - au-dela du
  plancher de cinq exige par la clause.

## 4. Ancrage sur `MAT-CE1-03` et non sur une ligne voisine

Chaque item pose `a + ? = d` avec `d` la dizaine immediatement superieure : c'est le complement a
la dizaine pris pour lui-meme, distinct de la forme deja servie par le paquet d'additions
(`a + b = ?` ou la somme tombe sur un multiple de dix) et distinct d'une soustraction generale
(aucun item n'autorise `d` au-dela de la premiere dizaine, conformement a `CDZ-CE1-1`). Le paquet
travaille bien `MAT-CE1-03` et pas `MAT-CE1-04`/`MAT-CE1-05`.

## 5. Lisibilite, vocabulaire, ton

Consigne : *"Quel nombre manque ?"*, quatre mots, vocabulaire de CE1. Enonce : une ligne, une
expression arithmetique (`21 + ? = 30`), aucun mot a lire. Indices : une phrase, forme constante
(*"Pars de X et avance jusqu'a Y, la dizaine suivante."*), correcte pour les 18 items - verifiee en
recalculant chaque `X` et chaque `Y` contre l'item. Aucun biais, aucun ton infantilisant.

## 6. Ce dont je reste la seule garde

`CDZ-CE1-1` et `CDZ-CE1-2` sont decidables sur la liste des items et automatisables sur le meme
patron que `ADD-CE1-1`/`ADD-CE1-2` ; tant qu'aucun garde-fou ne les execute, c'est moi qui les
tiens, en comptant - fait ce jour, section 3. Restent en ma seule main : que le paquet serve bien
`MAT-CE1-03` (section 4), la lisibilite et l'exactitude des indices (section 5), et l'exactitude
arithmetique des 18 items (section 2).

---

## Bloc de validation

> **Done pedagogique : VALIDE.**
>
> **Prononce le 2026-09-22 par `nadia-prof`, apres avoir joue le jar servi.**
>
> **Ce que j'ai verifie** : les 18 items (exacts, `a`, `d` et complement recalcules a la main),
> les 18 paires de distracteurs (conformes terme a terme a la table amendee de la section 2.3 de
> mon arbitrage, y compris ses deux cas de collision), `CDZ-CE1-1` (bornes de `a` et `d`, tenue
> sans exception), `CDZ-CE1-2` (neuf complements x2, neuf dizaines sur neuf representees),
> l'ancrage sur `MAT-CE1-03`, et la lisibilite de la consigne, de l'enonce et des indices.
>
> **Aucune reserve.**
>
> **Mouvement de ligne** : ce Done, combine a ceux des trois autres pieces de `MAT-CE1-03`, ouvre
> la voie a un passage de `MAT-CE1-03` a `couvert-valide` (voir le detail dans les trois autres
> fiches de ce meme audit). Le mouvement lui-meme est ecrit par `camille-po`, pas par moi.
