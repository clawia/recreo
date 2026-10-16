# [Sprint s57] Palier 2 - lecture du diff de p2-7 (forme commune des propositions longues), condition de fusion `TP6`

- **Date** : 2026-10-16 10:03 (UTC+02:00), lu par `date`.
- **Auteur** : `theo-archi`. Branche `s57-theo-p2-trace`, worktree dedie, base `aa9d703c`, `git status --short` vide avant ecriture.
- **Objet** : `TP6` de `avis-theo-palier-2.md` (2.6), verifie par `sofia-sm` avant la fusion (`Q-T15`, `planning.md`, amendement du 2026-10-16 09:03, section 8).
- **Ce que ce fichier ne fait pas** : il ne juge aucune cote (`ines-design`), ne dit rien de ce que l'enfant voit (`nadia-prof`), ne prononce aucun Done, ne fusionne rien.

## 1. Commit lu

| Element | Valeur | Commande |
|---|---|---|
| Branche | `s57-lea-forme-commune` | `git log --oneline -4` dans son worktree |
| Base | `aa9d703c` | `git merge-base --is-ancestor aa9d703c dc6f423d` : sortie 0 |
| **Commit lu** | **`dc6f423d`** (code et test) | `git diff aa9d703c dc6f423d`, lu en entier |
| Tete de branche | `78fac33d` (rapport de `lea-dev`) | `git rev-parse HEAD` : `78fac33d62b2f83b2cd00836339a89e8f14db21f` |
| Delta entre le commit lu et la tete | **vide sur le code** | `git diff --stat dc6f423d 78fac33d -- adapters domain application bootstrap pom.xml outils` : aucune ligne. Sans filtre : un fichier, `rapport-lea-dev-palier-2.md`, 70 lignes ajoutees |

Fichiers de `dc6f423d` (`git diff --name-only aa9d703c dc6f423d`) : `recreo.css` (+34, -0), `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest.java` (+159) et ses trois feuilles fautives. Aucun fichier de `domain`, `application`, `bootstrap`, aucun gabarit, aucun paquet, aucune `ADR`.

**Si la branche recoit un commit de plus avant la fusion**, la commande de la derniere ligne du tableau se relance entre `dc6f423d` et la nouvelle tete ; une sortie non vide demande une relecture de ma main.

## 2. Ce que j'ai compare : `candidat-r3.css` et `recreo.css`

Methode : les lignes ajoutees de `recreo.css` entre `aa9d703c` et `dc6f423d` (`difflib`, contexte 0), et `candidat-r3.css` lu a `aa9d703c` (`git show`), commentaires retires (`/* ... */`) et blancs ramenes a une espace, compares par egalite. Script hors depot.

| Mesure | Sortie |
|---|---|
| Lignes ajoutees, lignes supprimees | 34 et 0 |
| Caracteres normalises, ajout et candidat | 1091 et 1091 |
| Egalite | **vraie** |
| U+00A0 dans les lignes ajoutees | 0 |

Le texte des regles verse est celui que j'ai lu en `PA5` ; seuls les commentaires different. Ce que dit le rapport de `lea-dev` (1091 de chaque cote) est confirme par une mesure independante.

## 3. Conformite

| Code | Point | Constat | Methode |
|---|---|---|---|
| `TL1` | `C1'` point 1 | `R3-1` (sujet `.zone-jeu`), `R3-2` (`.score`, `.consigne`, `.enonce`), `R3-3` (`.propositions`) ne nomment ni `.message-jeu`, ni `.signal`, ni `.est-juste`, ni `.est-faux`, ni `data-oeuvre-revelee`, `:has(...)` compris. **Tenu** | lecture ; `grep -c -E 'est-juste\|est-faux\|data-oeuvre-revelee\|cabinet\|img\|display: *none\|visibility\|hidden\|grid-area\|grid-template-areas'` sur les lignes ajoutees : 0 |
| `TL2` | `C1'` point 2 | `R3-4` et `R3-5` ont pour sujet `.message-jeu` ou ses enfants, nomment `.signal`, aucun etat. **Tenu** | lecture, meme `grep` |
| `TL3` | `C1'` point 3 | La grille de `R3-4` est celle de `.message-jeu` : `grid-row: 1 / span 3` du signal couvre des rangees du message, pas de l'ecran. Hors `.ecran-deux-oeuvres`, `.ecran-de-jeu` n'est pas une grille (les seuls `display: grid` de la feuille de base sont `.ecran-deux-oeuvres`, son `.message-jeu`, `.matieres` et `.cartes`). Le message reste dans le flux, apres le meuble. **Tenu** | `grep -n "display: *grid"` sur `recreo.css` de `aa9d703c` : 4 lignes, lues |
| `TL4` | `C2` | Aucune regle ne vise une image de proposition ; le seul ecran a propositions en image est exclu par `:not(.ecran-deux-oeuvres)`. **Tenu** | meme `grep` (`img`) : 0 |
| `TL5` | `C3` | Aucune regle ne masque ; aucune ne nomme une classe en `cabinet`, le jeu fautif de `CabinetDeLaMachineTest` n'a pas a etre complete. **Tenu** | meme `grep` (`cabinet`, `display: none`, `visibility`, `hidden`) : 0 |
| `TL6` | `ADR-0054` | Aucun terme lateral neuf sur un element de la chaine de `.propositions` : `R3-3` ne change que `row-gap` ; `scrollbar-gutter` absent du diff (`TP5` tenu). La colonne de 15 rem de `R3-4` est une declaration distincte de celle de `L3` (lignes 1887 et 1923 de `recreo.css` de `dc6f423d`) : changer l'une ne change pas l'autre, comme `avis-theo-palier-2.md` 3.1 le demandait | `grep -c scrollbar` sur le diff : 0 ; `grep -n "grid-template-columns: 15rem"` : 2 lignes |
| `TL7` | `ADR-0067` D.4 | Aucun gabarit, aucun texte, aucune U+00A0 touches : la phrase de `M4` et son garde-fou sont hors du diff | section 2 ; liste des fichiers, section 1 |
| `TL8` | Critere 5 de la story | Aucune condition propre a un paquet | `git diff aa9d703c dc6f423d \| grep '^+' \| grep -c -E 'phrases-types\|accord-groupe-nominal\|ce1-verbe-sujet\|texte-narratif'` : 0 |

**Garde-fous nommes en 2.6 de mon avis, lances par moi** sur une copie de l'arbre de `dc6f423d` (`git archive`, hors worktree de `lea-dev`), `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=<les cinq classes> -Dsurefire.failIfNoSpecifiedTests=false` : `BUILD SUCCESS` ; `CabinetDeLaMachineTest` 9, `UnEcranNeGouvernePasLApparenceDUnAutreTest` 3, `LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest` 11, `LePlafondDeRenduDUneReproductionEnPropositionEstGardeTest` 11, `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest` 4 ; 0 echec. Perimetre : ces cinq classes, pas le module. JDK **21.0.10** (`java.version` des rapports Surefire d'`adapter-web`, une seule valeur).

**Ce que `C1'` tenue ne dit pas** : rien de l'ascenseur de la revelation (`avis-theo-palier-2.md` 2.3), et `C1'` n'a toujours aucune garde executable (`TP4`, section 5 ci-dessous).

## 4. Le test d'epinglage de `lea-dev`

### 4.1 Ce qu'il fait, lu

La population est derivee : toute regle maison dont le selecteur contient `:not(.ecran-deux-oeuvres)`, relue a chaque appel. Trois defauts rougissent : une regle de la population hors de `@media (min-width: 720px)` ; une regle de la population dont le selecteur n'est pas l'un des 9 selecteurs attendus ; une des **14 declarations** attendues absente ou de valeur differente. 14 = 1 (`R3-1`) + 3 (`R3-2`) + 1 (`R3-3`) + 8 (`R3-4` : 4 sur le message, 2 sur le signal, 1 sur les autres enfants, 1 sur le verdict) + 1 (`R3-5`), compte sur la liste `LA_FORME` et sur le bloc de `recreo.css`. Une population vide est un echec.

### 4.2 Morsures, de ma main, sur materiau reel

Copie de l'arbre de `dc6f423d` sous mon repertoire de scratchpad ; `recreo.css` de la copie mute, puis restaure par copie de reference (`cmp` : identique). Commande : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test -Dtest=LaFormeCommuneDesPropositionsLonguesEstEpingleeTest -Dsurefire.failIfNoSpecifiedTests=false`, perimetre `adapter-web` et ses amonts, une seule classe lancee. Chaque mutation a trouve son motif une fois exactement (sinon le script s'arrete : la premiere ecriture de `M1` a trouve 2 occurrences, `L3` comprise, et n'a pas ete lancee ; refaite avec le selecteur de `R3-4`).

| Code | Mutation de `recreo.css` | Tests | Sortie |
|---|---|---|---|
| `M0` | aucune | 4 | `BUILD SUCCESS`, 0 echec |
| `M1` | `R3-4` : `grid-template-columns: 15rem minmax(0, 1fr)` devient `14rem minmax(0, 1fr)` | 4 | **1 echec** : *"... > .message-jeu declare grid-template-columns a 14rem minmax(0, 1fr) : la forme a ete mesuree a 15rem minmax(0, 1fr)"* |
| `M2` | regle ajoutee que `C1'` interdit : `.ecran-de-jeu:has(.est-faux):not(.ecran-deux-oeuvres) .propositions { row-gap: 0; }` | 4 | **1 echec** : *"... n'est pas une regle de la forme mesuree"* |
| `M3` | declaration ajoutee dans une regle de la forme : `column-gap: 0.25rem` dans la regle de `R3-3` | 4 | **0 echec, vert** |
| `M4` | la regle de `M2` sans l'exclusion : `.ecran-de-jeu:has(.est-faux) .propositions { row-gap: 0; }` | 4 | **0 echec, vert** |

`M1` est la morsure demandee : une declaration modifiee rougit, avec un message qui nomme le selecteur et les deux valeurs. `M2` rougit, mais au titre de la liste fermee des selecteurs, pas de `C1'`.

### 4.3 Ecarts

| Code | Ecart | Gravite |
|---|---|---|
| `TL9` | **La liste des declarations n'est pas fermee par selecteur** (`M3`). Une declaration ajoutee dans une regle mesuree invalide la cote exactement comme une valeur changee, et passe. La Javadoc dit garder la forme *"telle qu'elle a ete mesuree"* ; la derivation totale d'`ADR-0013` y est tenue pour les regles, pas pour les declarations d'une regle. Correction possible, a `lea-dev` : pour chaque regle de la population, l'ensemble de ses proprietes declarees egale celui de `LA_FORME` pour ce selecteur, avec une quatrieme feuille fautive | non bloquant pour la fusion (section 6) |
| `TL10` | Javadoc : *"mise a l'epreuve : deux feuilles fautives"* ; le code en porte trois (`regle-hors-seuil`, `valeur-changee`, `regle-ajoutee`) | redactionnel, non bloquant |
| `TL11` | `M4` : une regle du meme ecran sans le jeton d'exclusion echappe a la population. Ce n'est pas un ecart du test : sa Javadoc dit qu'il ne garde pas `C1'`, et c'est la dette `TP4` | constat, pas d'ecart |

### 4.4 Epinglage serre ou lache : je tranche, en architecte de test

**Serre.** Les valeurs restent epinglees. Motif : aucun automate du reacteur ne rend une page, la cote de reference n'existe que par la mesure d'`ines-design` sur ces valeurs, et un epinglage lache (population et seuil seulement) laisserait une valeur changer sans qu'aucun rouge dise que la cote ne vaut plus. Le cout que `lea-dev` nomme (toute retouche de la forme amende ce test dans le meme commit) est le but : le commit qui change une valeur est oblige de dire qu'il change ce qui a ete mesure. **Ce qui me ferait changer d'avis** : un automate du reacteur qui mesure le bas de `.indice` ; l'epinglage des valeurs deviendrait alors redondant avec lui. **Serre veut dire ferme** : `TL9` est la partie de l'epinglage qui ne l'est pas encore.

Ce n'est pas une decision d'`ADR` : le test ne pose aucune regle d'architecture, il epingle une mesure.

## 5. Regles ArchUnit

**Inchangees.** Methode d'`ADR-0104` : `git grep -hcE` des motifs `^\s*(public |private |protected )?static final ArchRule ` et `^\s*@ArchTest\s*$` sur les `*.java` suivis du commit nomme, sommes par `awk`, fichiers par `git grep -l`.

| Commit | `static final ArchRule` | `@ArchTest` | Fichiers |
|---|---|---|---|
| `aa9d703c` | 41 | 38 | 16 |
| `dc6f423d` | 41 | 38 | 16 |

`TP4` (`C1'` sans garde executable) reste ouverte, inchangee par ce diff : `M4` la montre une seconde fois. Son traitement est route a `camille-po` par `planning.md` (section 8) ; elle n'est pas une condition de cette fusion.

## 6. Verdict de fusion

**Fusionnable sur `dc6f423d`, tete `78fac33d`.** `C1'` points 1 a 3, `C2`, `C3`, `ADR-0054` et `ADR-0067` D.4 sont tenus sur le diff livre ; le texte verse est celui du candidat lu en `PA5` ; le delta entre le commit lu et la tete ne touche aucun code ; le compte ArchUnit est inchange ; le test d'epinglage mord sur une valeur modifiee.

`TL9` et `TL10` ne bloquent pas : ils portent sur un test qui va au-dela de ce que la story exige (critere 7 ne demande aucun epinglage), et le test tient deja la valeur et la liste des regles. **Route a `lea-dev`**, dans le prochain commit qui touche la forme, au plus tard celui de p2-10. **S'ils sont corriges avant la fusion**, la tete change : la relecture du delta est due (section 1, derniere ligne).

## 7. Jobs de `ci.yml` lances pour ce commit

Lances sur ce worktree, ce fichier indexe, avant le commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl` : `BUILD SUCCESS`, exit 0. **2597 tests, 0 echec** (120, 31, 492, 1567, 381 et 6 d'integration, lignes `Tests run` de la sortie Maven). JDK **21.0.10** (`java.version` des 60 rapports Surefire de `bootstrap`, une seule valeur). Le build a lu ce fichier avant l'ecriture de cette section, qui ne contient aucun tableau. Etape *"Package du fat jar"* non lancee.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` : sort 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; le sujet de ce commit passe dans `verifier-prefixes.sh` : sort 0. Aucun chemin du garde-fou de l'accent n'est touche.
- **`outils`** : trois balayages (5, 2 et 5 fichiers), 12 juges lances avec `python`. **11 sortent 0 ; `outils/mvn-jdk21-test.sh` sort 1** (10 cas, *"l'enveloppe ne refuse pas comme annonce"*), le meme echec que celui que `lea-dev` n'a pas explique. **Cause cernee, pas trouvee** : relance seule sur le meme arbre avec `TMPDIR` a sa valeur par defaut, elle sort 0 (*"Tous les cas passent"*) ; relancee avec `TMPDIR` dans mon repertoire de scratchpad, elle sort 1, deux fois. L'echec suit le repertoire temporaire du poste, pas l'arbre. A `sofia-sm`, proprietaire de la consigne de repertoire des agents ; je ne touche pas `outils/`.
