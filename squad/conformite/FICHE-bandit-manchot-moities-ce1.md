# Fiche de conformite - bandit-manchot-moities-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-moities.yaml`
  (21 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-moities-ce1`, titre servi *"La moitie"*, consigne
  servie *"Quelle est la moitie ?"*, `nombreDeManches: 10`.
- **Ligne declaree** : `MAT-CE1-03`, sous-partie *"moities"*.
- **Statut global** : **VALIDE**. Done pedagogique **prononce le 2026-09-22** par `nadia-prof`,
  **apres avoir joue le jar servi**. Aucune reserve ; une clause de mon propre arbitrage a ete
  amendee ce jour, voir section 3.
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-s38-soustractions-complements-dizaine-et-moities-ce1.md` (2026-09-22), section 3,
  clauses `MOI-CE1-1` et `MOI-CE1-2`, distracteurs section 3.4 **amendee ce jour** (voir section 3
  de cette fiche).
- **Derniere mise a jour** : 2026-09-22 (premier audit, s38).

---

## 1. Ce que j'ai joue, et comment

**Audit non degrade.** Reacteur construit (`mvn -B clean package -DskipTests` via
`outils/mvn-jdk21.sh`, `BUILD SUCCESS`), `bootstrap/target/recreo.jar` lance sur le JDK 21 designe
par la toolchain, jeu sur l'ecran reellement servi.

Une partie jouee manche par manche, erreurs volontaires sur plusieurs manches pour lire le
comportement apres une puis deux erreurs, puis les 21 items relus dans le fichier de contenu pour
recalculer chacun a la main (section 2).

**Ce que l'enfant voit sur une manche** : la consigne *"Quelle est la moitie ?"*, l'enonce
`Moitie de 58 = ?`, trois propositions. Sur une premiere erreur : *"Ce n'est pas ca. Essaie
encore !"*, l'indice affiche (*"58, c'est 50 et 8 : la moitie de 50 est 25, la moitie de 8 est 4,
donc 29."*). Sur une deuxieme erreur : meme message, meme indice, la proposition restante etant la
bonne reponse. Rendu correct, accents et espaces insecables compris.

## 2. Exactitude - les 21 items recalcules

J'ai recalcule les 21 moities a la main (`n / 2` pour les 21 valeurs de `n`, toutes paires, de 2 a
100) : **exactes sans exception**. Les distracteurs recalcules terme a terme contre la clause
amendee de la section 3.4 de mon arbitrage (voir section 3 ci-dessous) : conformes sans exception,
tous entiers positifs, tous distincts entre eux et de la bonne reponse.

## 3. Le distracteur "moitie moins deux" - une correction de mon propre arbitrage, pas une reserve

**Le fait, trouve en relisant le paquet livre avant de le jouer.** Sept des vingt et un items
(`8`, `14`, `18`, `40`, `80`, `34`, `74`) portent comme distracteurs `moitie - 1` et `moitie - 2` -
par exemple, pour `Moitie de 8 = ?` (bonne reponse `4`), les distracteurs sont `3` et `2`. Ma
clause d'origine (section 3.4 de mon arbitrage, avant amendement) n'autorisait que trois formes :
le double, la moitie plus un, la moitie moins un. `moitie moins deux` n'y figurait pas.

**Ce que j'ai verifie avant de statuer, plutot que de presumer le motif du developpeur.** J'ai
recalcule, sur les six combinaisons de deux distracteurs que quatre formes permettent, le rang que
prend la bonne reponse dans chacune (table complete dans l'arbitrage amende, section 3.4). Verdict :
avec les seules trois formes d'origine, **la bonne reponse ne peut jamais etre la plus grande des
trois propositions** - le double la depasse toujours, et la paire restante l'encadre sans jamais la
laisser en tete. Sur un paquet entier limite a ces trois formes, le rang "plus grande" resterait a
0 %, ce qui aurait fait rougir `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` des que la
proportion des deux autres rangs depasse la majorite - le meme garde-fou dont le docstring cite mon
propre REFUS de `S12-3` a s12, pour un defaut de la meme famille.

**Ma decision.** Ce n'est pas un debordement de la clause que je refuse : c'est un trou de ma
propre specification, du meme ordre que celui que j'avais deja trouve et corrige avant tout code
pour les complements a la dizaine (section 2.3 de l'arbitrage). Je l'ai omis pour les moities parce
que je n'avais pas refait, a ce moment-la, la meme verification a la main. Je corrige l'arbitrage
ce jour (section 3.4, amendement date), en ajoutant `moitie moins deux` comme quatrieme forme,
reservee a son unique usage utile : associee a `moitie moins un` pour porter la bonne reponse au
rang "plus grande". Sur le paquet livre : la repartition mesuree est **7 items plus petite (33,3
%), 7 intermediaire (33,3 %), 7 plus grande (33,3 %)** - je l'ai recomptee moi-meme sur les 21
items, elle concorde avec le commentaire du fichier de contenu.

**Ce que cela signifie pour ce Done.** L'increment livre est exact, exhaustivement recalcule, et
resout un probleme reel que ma propre clause n'avait pas anticipe, avec une erreur de decompte deja
acceptee ailleurs dans ce meme arbitrage (section 2.3) et au CP. Je ne l'ouvre pas comme reserve :
il n'y a rien a corriger dans le paquet, l'action a mener est deja faite - amender l'arbitrage, ce
que je fais dans ce meme commit.

## 4. `MOI-CE1-1` et `MOI-CE1-2` - tenues, et comptees par moi

- **`MOI-CE1-1`** (bornes 0-100, cas degenere `n = 0` au plus 5 %) : **0 item degenere sur 21
  (0 %)**, conforme, sans plancher a tenir.
- **`MOI-CE1-2`** (repartition par forme) : 9 items de repertoire de base (2-20, **42,9 %**, bande
  30-50 %), 5 items de nombre rond (30-100, **23,8 %**, bande 15-30 %), 7 items de deux chiffres
  general (**33,3 %**, bande 15-35 %). Les trois bandes sont tenues, comptees par moi sur la liste
  avant de relire le commentaire du fichier de contenu.

## 5. Ancrage sur `MAT-CE1-03` et non sur une ligne voisine

La moitie est une strategie nommee par la parenthese de `MAT-CE1-03` et absente de tout paquet de
CP (verifie a l'arbitrage, section 0 : ni "double" ni "moitie" n'apparaissent dans
`competences-cp.md`). Le paquet livre ne fait que ca, sur toute sa plage (2 a 100) : aucun item
n'est une addition, une soustraction ou un complement a la dizaine deguise.

## 6. Lisibilite, vocabulaire, ton

Consigne : *"Quelle est la moitie ?"*, quatre mots. Enonce : *"Moitie de 58 = ?"*, une ligne, forme
constante. Indices : une phrase qui decompose dizaine et unite pour les nombres a deux chiffres non
ronds, correcte sur les 21 items - recalculee. Aucun biais, aucun ton infantilisant.

## 7. Ce dont je reste la seule garde

`MOI-CE1-1` et `MOI-CE1-2` sont automatisables sur le patron `ADD-CE1-1`/`ADD-CE1-2` ; tant
qu'aucun garde-fou ne les execute, c'est moi qui les tiens, en comptant - fait ce jour, section 4.
Restent en ma seule main : que le paquet serve bien `MAT-CE1-03` (section 5), la lisibilite des
indices (section 6), l'exactitude arithmetique des 21 items (section 2), et - point neuf - **la
plausibilite du distracteur `moitie moins deux` pour un enfant reel**, qu'aucun garde-fou ne
distingue d'une erreur de calcul quelconque : je l'ai jugee plausible en section 3, et c'est un
jugement qui ne se recalcule pas.

---

## Bloc de validation

> **Done pedagogique : VALIDE.**
>
> **Prononce le 2026-09-22 par `nadia-prof`, apres avoir joue le jar servi.**
>
> **Ce que j'ai verifie** : les 21 items (exacts, moities recalculees a la main), `MOI-CE1-1`
> (0 % de cas degenere, conforme), `MOI-CE1-2` (trois bandes tenues, comptees par moi), l'ancrage
> sur `MAT-CE1-03`, la lisibilite de la consigne, de l'enonce et des indices, et le distracteur
> `moitie moins deux` sur les sept items qui le portent (plausible, motif en section 3).
>
> **Aucune reserve.** Une clause de mon propre arbitrage (section 3.4) a ete corrigee ce jour, dans
> le meme commit que ce Done, pour fermer un trou que je n'avais pas vu en l'ecrivant - ce n'est
> pas une reserve adressee a l'increment, qui l'a anticipee correctement.
>
> **Mouvement de ligne** : ce Done, combine a ceux des trois autres pieces de `MAT-CE1-03`, ouvre
> la voie a un passage de `MAT-CE1-03` a `couvert-valide`. Le mouvement lui-meme est ecrit par
> `camille-po`, pas par moi.
