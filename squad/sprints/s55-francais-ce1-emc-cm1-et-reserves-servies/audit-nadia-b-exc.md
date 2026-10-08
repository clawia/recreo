# Audit de `nadia-prof`, s55, instance B - rejeu de `S54-EXC-CATEGORIE` (`EXC-R15`) - resume

`nadia-prof`, instance B, le 2026-10-08. Worktree `s55-nadia-audit-b-exc` a `7e32138a` (`round-two`, qui contient
`d2b317a8`). Le verdict et son motif sont dans la fiche ; ce fichier les resume et ne les remplace pas.

## Verdict

| Story | Ligne | Fiche | Verdict | Mouvement de matrice |
|---|---|---|---|---|
| `S54-EXC-CATEGORIE` | `EMC-CP-03` | `squad/conformite/FICHE-bandit-manchot-exclusion-cp.md` | **VALIDE SOUS RESERVE** : `EXC-R15` levee, `EXC-R16` neuve | aucun : `EMC-CP-03` reste `couvert-valide` |

La matrice et le backlog sont a `camille-po`.

## Ce que j'ai fait

- `bash outils/mvn-jdk21.sh -B clean verify` sur la racine, JDK 21.0.10 : **BUILD FAILURE**. 2 echecs dans `bootstrap`,
  tous deux `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` (`ADR-0087`) sur `rapport-sofia-outils.md`,
  ligne 22, de ce sprint. Hors de l'increment ; `PaquetCpEmcExclusionTest` et `TexteAccentueExposeParLePortEntrantTest`
  verts. **Job `build` seul lance par moi**, rouge ; `prefixes`, `chemins`, `outils` non lances.
- Jar construit par `package -DskipTests` pour jouer ; ressources identiques a l'arbre (`cmp`). Port dedie, arret par PID.
- 30 parties jouees (300 manches), justes, avec une erreur, avec deux erreurs ; plus une sonde de 15 parties jusqu'a la
  revelation sur les items 1, 3 et 4. Rendu lu en HTML, pas vu au navigateur.

## Ce qui est tenu

- Les quatre lignes d'`EXC-R15` sont appliquees mot pour mot (`git show d2b317a8`).
- *une mise à l'écart qui se répète* est la bonne reponse servie dans les 30 parties ; items 1, 3, 4 vus 29, 26, 27 fois,
  tous jouables jusqu'a la revelation.
- La consigne neuve (14 mots, sans *moquerie*) est servie a chaque manche et couvre les deux taches.
- Le titre, affiche sur la liste des jeux seulement : pas de reserve.

## Reserve neuve

`EXC-R16`, a mon debit : l'indice de l'item 4 dit *"n'est pas de la moquerie"* alors qu'aucun bouton de l'item ne dit
*moquerie* depuis ma ligne 3. Ligne de remplacement mot pour mot dans la fiche, section 5. Prix : 0,25.

## Ce dont je reste la seule garde

Qu'une bonne reponse de classement nomme ce que la situation est ; qu'un indice ecarte un choix qui est a l'ecran ; la
plausibilite des distracteurs. Aucun garde-fou ne les porte.

## Capacite de l'instance B

`EXC-R15` : 0,25, mot pour mot. B passe de 3,75 a 4,0 sur 4,0. **Un seul rejeu ; aucun second rejeu n'est necessaire**
pour `EXC-R15`. `EXC-R16` se paie au sprint qui la sert.

## Identifiants

Un neuf, `EXC-R16`, alloue dans le meme diff. Compte du registre porte de 927 / 158 a 928 / 158, chiffre lu sur
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` sur mon diff.

**Signe** : `nadia-prof`, s55, instance B, 2026-10-08.
