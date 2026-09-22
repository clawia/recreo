# Fiche de conformite - bandit-manchot-soustractions-ce1

- **Mecanique** : bandit manchot.
- **Contenu** :
  `adapters/adapter-content/src/main/resources/contenu/ce1-soustractions-jusqu-a-100.yaml`
  (32 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-soustractions-ce1`, titre servi *"Les grandes
  soustractions"*, consigne servie *"Combien ca fait ?"*, `nombreDeManches: 10`.
- **Ligne declaree** : `MAT-CE1-03`, sous-partie *"soustractions dans la limite de 100"*.
- **Statut global** : **VALIDE SOUS RESERVE**. Done pedagogique **prononce le 2026-09-22** par
  `nadia-prof`, **apres avoir joue le jar servi**. Une reserve nommee, `SOU-CE1-R1`,
  **non bloquante**.
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-s38-soustractions-complements-dizaine-et-moities-ce1.md` (2026-09-22), section 1,
  clauses `SOU-CE1-1` et `SOU-CE1-2`, distracteurs section 1.4.
- **Derniere mise a jour** : 2026-09-22 (premier audit, s38).

---

## 1. Ce que j'ai joue, et comment

**Audit non degrade.** Reacteur construit (`mvn -B clean package -DskipTests` via
`outils/mvn-jdk21.sh`, `BUILD SUCCESS`), `bootstrap/target/recreo.jar` lance sur le JDK 21 designe
par la toolchain, jeu sur l'ecran reellement servi.

Une partie jouee manche par manche, erreurs volontaires sur plusieurs manches pour lire le
comportement apres une puis deux erreurs, puis les 32 items relus dans le fichier de contenu pour
recalculer chacun a la main (section 2).

**Ce que l'enfant voit sur une manche** : la consigne *"Combien ca fait ?"*, l'enonce
`74 - 49 = ?`, trois propositions. Sur une erreur : *"Ce n'est pas ca."* (ou *"Essaie encore !"*
selon la forme, voir section 1 des ecrans joues), l'indice affiche (*"Recule de 4 pour atteindre
70, puis encore de 45."*), la proposition deja pressee restant cliquable. La revelation, quand elle
survient, nomme la bonne reponse en toutes lettres.

## 2. Exactitude - les 32 items recalcules

J'ai recalcule les 32 differences a la main : **exactes sans exception**. Les indices
*"recule/enleve/pars"* recalcules terme a terme contre chaque item : corrects sans exception, y
compris la forme composee des sept items avec emprunt (*"Recule de X pour atteindre Y, puis encore
de Z"*, ou `X = unite(a)`, `Y = a - unite(a)`, `Z = b - unite(a)`).

## 3. `SOU-CE1-1` et `SOU-CE1-2` - tenues, et comptees par moi

- **`SOU-CE1-1`** (soustracteur nul et resultat nul, au plus 5 % chacun, sans plancher) : **0 item
  de chaque sur 32 (0 %)**, conforme.
- **`SOU-CE1-2`** (sept bandes) : 6 differences faciles (18,75 %, bande 10-25 %), 5 soustractions
  d'un nombre rond (15,6 %, bande 10-20 %), 7 items "un chiffre soustrait" (21,9 %, bande 15-30 %),
  7 items deux chiffres sans emprunt (21,9 %, bande 10-30 %), 7 items deux chiffres avec emprunt
  (21,9 %, bande 15-30 %). **Les sept bandes sont tenues**, comptees par moi sur la liste avant de
  relire le commentaire du fichier de contenu - les deux comptages concordent.

## 4. `SOU-CE1-R1` - un distracteur non identifiable sur quatre items de la forme "un chiffre soustrait"

**Ouverte le 2026-09-22. Non bloquante.**

**Le fait.** La clause de mon arbitrage (section 1.4) n'autorise, pour les items hors forme 7
(avec emprunt), que deux formes de distracteur : la somme (`a + b`) et le second terme recopie
(`b`). Sur les sept items de la forme 5 (*"un chiffre soustrait"*, `b < 10`), trois items
(`84 - 7`, `91 - 8`, `99 - 9`) portent bien `b` et la somme. **Les quatre autres** (`45 - 3`,
`57 - 4`, `68 - 5`, `79 - 6`) portent `b` recopie, correct, mais leur second distracteur ne
correspond a aucune des deux formes autorisees :

| Item | Bonne reponse | Distracteurs | Second distracteur = somme (`a+b`) ? |
|---|---|---|---|
| `45 - 3` | 42 | `3`, **`20`** | Non (la somme vaut 48) |
| `57 - 4` | 53 | `4`, **`25`** | Non (la somme vaut 61) |
| `68 - 5` | 63 | `5`, **`30`** | Non (la somme vaut 73) |
| `79 - 6` | 73 | `6`, **`35`** | Non (la somme vaut 85) |

**Ce que j'ai identifie, en recalculant plutot qu'en supposant.** Les quatre valeurs (`20`, `25`,
`30`, `35`) correspondent a la moitie de la dizaine inferieure de `a` (`floor(a / 10) x 10 / 2`) :
`45` -> `40` -> `20` ; `57` -> `50` -> `25` ; `68` -> `60` -> `30` ; `79` -> `70` -> `35`. Ce n'est
ni la somme, ni `b` recopie, ni - la clause le reserve a la forme 7 - l'erreur d'emprunt oublie :
**c'est une forme qu'aucune clause de mon arbitrage n'autorise pour cette forme d'item, et je ne
lui trouve pas d'erreur de soustraction plausible qu'elle representerait.** Jouee a l'ecran
(`79 - 6 = ?`, propositions `73`, `6`, `35`) : un enfant qui estime l'ordre de grandeur ecarte vite
`35`, trop loin de `79`, ce qui reduit dans les faits le choix a `73` contre `6` sur ces quatre
manches - une manche qui enseigne moins que ses freres de la meme forme, pas une manche fausse.

**Pourquoi ce n'est pas bloquant.** L'exactitude de la bonne reponse n'est pas en cause, les trois
propositions restent distinctes et positives, et le defaut est confine a un seul champ (le second
distracteur) sur 4 items sur 32 (12,5 %) - meme ordre de grandeur que les deux reserves deja
ouvertes et levees sur le paquet d'additions. Le jeu se joue de bout en bout sans qu'aucun enfant
ne soit bloque ni induit en erreur sur un fait.

**Le correctif que je demande, actionnable sans discussion.** Remplacer le second distracteur des
quatre items par la somme `a + b` (`48`, `61`, `73`, `85`), exactement la forme deja utilisee sur
leurs trois freres de la meme bande (`84 - 7`, `91 - 8`, `99 - 9`) et sur toutes les autres bandes
non-emprunt du paquet (formes 3, 4 et 6). Aucune autre valeur n'est necessaire : la coherence
interne a la forme 5 suffit a fermer la reserve.

## 5. Ancrage sur `MAT-CE1-03` et non sur une ligne voisine

Le paquet couvre `a - b`, `0 <= b <= a <= 100`, avec les sept bandes de `SOU-CE1-2` qui font
apparaitre la soustraction facile, le nombre rond, le chiffre unique, et les deux formes a deux
chiffres (avec et sans emprunt). Aucun item ne demande un algorithme colonne par colonne : les
indices donnent tous une strategie de calcul mental (compter les pas, retrancher un nombre rond,
reculer jusqu'a la dizaine). Le paquet travaille bien `MAT-CE1-03`, pas `MAT-CE1-05` (la
soustraction posee).

## 6. Lisibilite, vocabulaire, ton

Consigne : *"Combien ca fait ?"*, quatre mots. Enonce : une ligne (`74 - 49 = ?`). Indices : une
phrase courte par forme, vocabulaire de CE1 (*"pars"*, *"recule"*, *"enleve d'un coup"*), tous
recalcules et corrects (section 2). Aucun biais, aucun ton infantilisant.

## 7. Ce dont je reste la seule garde

`SOU-CE1-1` et `SOU-CE1-2` sont automatisables sur le patron `ADD-CE1-1`/`ADD-CE1-2` ; tant
qu'aucun garde-fou ne les execute, c'est moi qui les tiens, en comptant - fait ce jour, section 3.
Restent en ma seule main : que le paquet serve bien `MAT-CE1-03` (section 5), la lisibilite des
indices (section 6), l'exactitude arithmetique des 32 items (section 2), et **la plausibilite de
chaque distracteur** - c'est exactement ce qui a produit `SOU-CE1-R1`, qu'aucun garde-fou de ce
depot ne lit.

---

## Bloc de validation

> **Done pedagogique : VALIDE SOUS RESERVE.**
>
> **Prononce le 2026-09-22 par `nadia-prof`, apres avoir joue le jar servi.**
>
> **Ce que j'ai verifie** : les 32 differences (exactes), `SOU-CE1-1` (0 % de chaque cas degenere,
> conforme), `SOU-CE1-2` (sept bandes tenues, comptees par moi), les indices (32 recalcules,
> corrects), l'ancrage sur `MAT-CE1-03`, et la plausibilite des distracteurs (defaut trouve sur
> quatre items, section 4).
>
> **Une reserve, non bloquante** : `SOU-CE1-R1` - sur `45 - 3`, `57 - 4`, `68 - 5` et `79 - 6`, le
> second distracteur (`20`, `25`, `30`, `35`) ne correspond a aucune des formes autorisees par ma
> clause (section 1.4) ni a une erreur de soustraction identifiable ; correctif donne en section 4
> (remplacer par la somme `a + b`, comme le font deja leurs trois freres de la meme bande).
>
> **Mouvement de ligne** : ce Done, combine a ceux des trois autres pieces de `MAT-CE1-03`, ouvre
> la voie a un passage de `MAT-CE1-03` a `couvert-valide` - une reserve non bloquante etant un
> Done pedagogique ouvert, pas un Done refuse. Le mouvement lui-meme est ecrit par `camille-po`,
> pas par moi.
