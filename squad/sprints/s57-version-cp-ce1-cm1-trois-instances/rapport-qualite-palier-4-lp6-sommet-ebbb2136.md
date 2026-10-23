# Rapport qualite du palier 4, lot LP6 repris apres le REFUSE (sommet ebbb2136)

Auteur : `yanis-qa`, transverse. Date : 2026-10-23. `round-two` est a `a65341cb`.
LP6 repris : branche `s57-sofia-emc-ce1-04`, sommet `ebbb2136` (rapport de `sofia-sm`), code `ec2d572a`, paquet `ce1-emc-interet-commun` (story `S57-BR-EMC-CE1-04`). Mon rapport precedent, `rapport-qualite-palier-4-lp6-sommet-cbfb152e.md` (commit `6a663723`), porte sur `cbfb152e` et n'est pas reecrit.

Mesures faites dans deux worktrees detaches au sommet `ebbb2136`, propres avant chaque mesure : un pour le `verify`, le package et les jobs, un pour les mutations. Le worktree du lot n'a pas ete touche. Les mutations ont tourne pendant le `verify` et le package : les durees ne se comparent pas.

**Ce rapport ne prononce pas le Done pedagogique** (`nadia-prof`, seule). Le REFUSE de `nadia-prof` portait sur le fond (M1, M2) ; ce rapport ne dit rien de leur levee sur le fond. `INT-CE1-1` (une seule lecture defendable) n'est pas mesurable par un test.

## 1. Resultat

1. `bash outils/mvn-jdk21.sh -B clean verify` au sommet : **exit 0, 3227 tests, 0 echec, 0 erreur, 0 ignore**. Annonce de `sofia-sm` : 3227, adapter-content 2166, test du paquet 69 ; les trois sont confirmes.
2. Jobs de `ci.yml` lances, un par un : `build` etape Verify, `build` etape Package du fat jar, `prefixes`, `chemins`, `outils`. Tous verts (section 3). L'etape `build` "Verifier la presence du build Maven" (`test -f pom.xml`) est positive. Ce n'est pas la CI de GitHub, qui n'a pas tourne sur ce commit.
3. Mutations A et B de `sofia-sm`, rejouees telles qu'ecrites : **rouges, un seul echec chacune, le test annonce**. Le garde neuf `verifierQueLAppartenanceNeDecidePasDeLaPortee` mord sur ses donnees, sur sa boucle et sur ses sondes (section 4). Trois mutations du garde survivent (G4, G5, G11), nommees en section 5.
4. Graphies d'`INT-CE1-2` : "biens communs" et "bien-commun" **ne sont toujours pas gardees** (section 5). Inchange depuis `cbfb152e`.
5. **Total de la base `a65341cb` : non mesure**, deduit a 3158 (section 2).

## 2. `verify` complet, sous le jeton RF1

Commande : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`. JDK 21.0.10 (propriete `java.version` des 326 `TEST-*.xml`, et sortie de l'enveloppe). Jeton RF1 : `mkdir` du repertoire `s57-p4-jeton-verify` a la racine du scratchpad reussi du premier coup, sans attente, 09:03:27 ; repertoire supprime a la fin et absent de la racine apres coup. `BUILD SUCCESS`, 09:03:27 a 09:15:55.

| Module | Tests | Echecs / erreurs / ignores | Fichiers XML | Provenance |
|---|---|---|---|---|
| domain | 120 | 0 / 0 / 0 | 11 | surefire |
| application | 31 | 0 / 0 / 0 | 2 | surefire |
| adapter-web | 523 | 0 / 0 / 0 | 58 | surefire |
| adapter-content | 2166 | 0 / 0 / 0 | 191 | surefire |
| bootstrap | 381 | 0 / 0 / 0 | 60 | surefire |
| bootstrap | 6 | 0 / 0 / 0 | 4 | failsafe (integration) |
| **Total** | **3227** | | 326 | |

Methode : somme de l'attribut `tests` de chaque `TEST-*.xml` de `target/surefire-reports` et `target/failsafe-reports`, par module. Les six lignes `Tests run:` du journal Maven rendent les memes six nombres. Chaque module a des tests.

**Delta de LP6 contre `a65341cb` : +69**, tous dans `PaquetCe1EmcInteretCommunTest`. `git diff a65341cb ebbb2136 -- '*Test.java'` : 3 fichiers, 1398 lignes ajoutees, 0 retiree ; 69 occurrences de `@Test` ajoutees, 0 retiree. Contre `cbfb152e` (mon rapport precedent) : 3227 - 3223 = +4, soit 69 - 65.

**Base `a65341cb` : non mesuree, et je ne peux pas l'etablir sans un second `verify` complet.** Une deduction existe : 3227 - 69 = **3158**, valable si aucun test existant ne change de nombre. Elle repose sur des faits lus : aucun `@ParameterizedTest`, `@TestFactory` ni `@RepeatedTest` dans les sources du worktree ; les deux autres fichiers de test touches (`TexteAccentueExposeParLePortEntrantTest`, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`) n'ajoutent aucun `@Test` et ne retirent aucune ligne. Elle concorde avec la deduction de mon rapport sur `cbfb152e` (3223 - 65 = 3158). Ce n'est pas une mesure de l'arbre de base. `sofia-sm` etablit le total sur l'arbre fusionne.

## 3. Jobs de `.github/workflows/ci.yml` lances, sommet `ebbb2136`

| Job | Etapes | Resultat |
|---|---|---|
| `build` | "Verify" : `bash outils/mvn-jdk21.sh -B clean verify` | exit 0 (section 2) |
| `build` | "Package du fat jar" : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package`, tests inclus, sous un second jeton RF1 (pris du premier coup a 09:17:31, rendu a 09:27:02, absent apres coup) | exit 0, `BUILD SUCCESS`, `bootstrap/target/recreo.jar` present (21,5 Mo), 3221 tests rejoues (120 + 31 + 523 + 2166 + 381, sans les 6 d'integration), 0 echec |
| `build` | "Verifier la presence du build Maven" | `test -f pom.xml` : present |
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `commit-atteignable.sh a65341cb`, `git log --no-merges --format=%s a65341cb..ebbb2136` vers `verifier-prefixes.sh` (4 commits, tous `[SOF]`), `verifier-garde-fou-accent-test.sh` (21 cas), `verifier-garde-fou-accent.sh a65341cb..ebbb2136` | exit 0 pour les six ; "0 commit(s) le touchent dans la plage, tous [THEO]" |
| `chemins` | `verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh'` vers `verifier-chemins.sh` | exit 0, "Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies." |
| `outils` | balayage `outils/*-test.sh` (6), `squad/outils/*/*-test.sh` (2), `outils/*-test.py` (5) | exit 0 pour chacun, **13** juges, 0 echec, aucun motif vide |

Ecarts a la CI : enveloppe de JDK du poste, `clean` ajoute au Verify, plage de `prefixes` bornee a `a65341cb..ebbb2136`, interpreteur Python du poste pour `outils`. Le commit de ce rapport n'est pas dans la plage mesuree : `prefixes` et `chemins` sont a relancer sur lui et sur le commit de fusion.

**Porte technique de LP6 au sommet `ebbb2136` : verte sur chacun des jobs nommes ci-dessus.**

## 4. Mutations

**Methode.** Une mutation d'un fichier du lot (paquet reel, classe de test ou jeu fautif), appliquee par un script qui exige exactement une occurrence du motif (sinon il refuse sans lancer Maven), puis :
`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am -Dtest=PaquetCe1EmcInteretCommunTest -Dsurefire.failIfNoSpecifiedTests=false test`
Perimetre : la classe seule, `-am` present. Base non mutee : exit 0, 69 sur 69. Verdict lu dans le `TEST-*.xml` de la classe, fichier restaure par `git checkout -- .`, `git status --short` vide apres chacune des 22 mutations. Fins de ligne CRLF respectees.

| Id | Cible | Mutation | Resultat |
|---|---|---|---|
| A | donnees, `sofia-sm` | "Aya prend un livre de la bibliotheque pour le lire." devient "Aya prend son livre pour le lire.", declaree objet personnel | rouge, 1 echec : `int1LAppartenanceDeLObjetNeDecidePasDeLaPorteeDansAucuneDesDeuxPortees`, "dans la portee un, 3 scenes sur 11" |
| B | donnees, `sofia-sm` | "Maya ecrit son annonce de la sortie au tableau." devient "Maya ecrit l'annonce ...", declaree objet partage | rouge, 1 echec, le meme test, "dans la portee tous, 4 scenes sur 13" |
| G1 | garde | `verifierQueLAppartenance...` neutralise (retour immediat) | rouge, 2 echecs : sondes 42 et 43 |
| G2 | garde | boucle sur la portee un seule | rouge : sonde 43 |
| G3 | garde | boucle sur la portee tous seule | rouge : sonde 42 |
| G6 | garde | `UN_TIERS` 3 devient 2 (plus strict) | rouge, meme test que A, "portee un, 4 scenes sur 11" : le seuil est vivant sur le paquet reel |
| G7 | garde | `isGreaterThanOrEqualTo` devient `isGreaterThan` | rouge : sonde 43 seule (voir note) |
| G8 | liste | "ses" retire de `POSSESSIFS` | rouge, 3 echecs : sonde 42, sonde 07, `int1LaPorteeDeclaree...` |
| G9 | liste | "bibliotheque" retire de `PARTAGES` | rouge : `int1LaPorteeDeclaree...` (la scene d'Aya) |
| S41 | sonde 41 | declaration d'objet ajoutee a l'item | rouge : `leJeuFautifCommiteEstRefuseSurUnItemSansDeclarationDAppartenanceDeLObjet` |
| S42 | sonde 42 | un item devient objet partage sans possessif (une scene croisee sur 3, 3 x 1 >= 3) | rouge : sonde 42 |
| S43 | sonde 43 | un item devient objet personnel avec possessif (une scene croisee sur 3) | rouge : sonde 43 |
| C-BC | INT-CE1-2, temoin | "bien commun" dans un indice | rouge : `int2AucuneExpressionInterdite...` (le canal de mutation mord) |
| I-GEN | INT-CE1-2, temoin | "interet general" accentue dans un indice | rouge : meme test |
| **I-BIENS** | INT-CE1-2 | "biens communs" dans un indice | **VERT, 69 sur 69** |
| **I-TRAIT** | INT-CE1-2 | "bien-commun" dans un indice | **VERT, 69 sur 69** |
| **G4** | garde | `porteeQueDonneLePossessif` lit la declaration d'objet au lieu du texte | **VERT** |
| **G5** | garde | `UN_TIERS` 3 devient 4 (plus lache) | **VERT** |
| **G10** | liste | "robinet" retire de `PARTAGES` | **VERT** |
| **G11** | garde | le filtre par portee de la boucle est retire (les deux tours comptent les 24 scenes) | **VERT** |

Note sur G7 : le rouge de la sonde 43 vient, par lecture du code et non par un test, de ce qu'une portee vide (portee un, dans le jeu de la sonde 43) passe de `0 >= 0` a `0 > 0` faux : le garde echoue alors sur "portee un" et non sur "portee tous", message que la sonde 43 n'attend pas. La mutation ne mesure donc pas la frontiere : sur le paquet reel, 3 x 4 = 12 > 11 et 3 x 5 = 15 > 13, la frontiere n'est pas atteinte.

**Purete des sondes 41 a 43.** Methode : une methode temporaire ajoutee a la classe, dans le worktree de mutation, appliquait a chaque sonde 14 verificateurs de la classe et listait ceux qui refusent ; fichier restaure, `git status` vide. Resultat :
- sonde 41 : refusee par `verifierLaPortee` (declaration d'objet manquante). Le garde d'appartenance la refuse aussi, mais sur un jeu d'un seul item, ce que le test de la sonde n'appelle pas.
- sondes 42 et 43 : refusees par `verifierQueLAppartenanceNeDecidePasDeLaPortee` et par lui seul parmi ceux que leur test appelle. `verifierLaPortee` les laisse passer : chaque sonde ne fait defaut que sur la propriete visee.
- `verifierLesEnonces` refuse les trois, a cause du marqueur "(sonde NN)" dans l'enonce (la question de l'item n'est plus "Pour qui est-ce utile ?"). Aucun test de sonde ne l'appelle ; c'est vrai de toutes les sondes de la classe.
- `verifierLesMotsInterdits` a ete passe avec un fichier vide : non mesure sur ces sondes.

## 5. Ce qui n'est pas garde, nomme

1. **Graphies d'`INT-CE1-2`** (I-BIENS, I-TRAIT). La regex lit `interet general` et `bien commun` au singulier, avec espaces. "biens communs" (nom du paquet voisin `ce1-emc-biens-communs`) et "bien-commun" passent. Question pour `nadia-prof` : le pluriel et le trait d'union sont-ils interdits aussi ? Si oui, la regex est trop etroite. Inchange depuis `cbfb152e` (Y13a, Y13b).
2. **Seuil du garde neuf** (G5). Passer `UN_TIERS` de 3 a 4 laisse la suite verte : les sondes 42 et 43 ont 0 scene croisee et ne fixent pas le seuil. Seules les donnees reelles le fixent, avec une scene de marge en portee un (A) et en portee tous (B). Une sonde a 1 croisee sur 3 et une autre a 0 sur 4 le fixeraient.
3. **"Dans chacune des deux portees"** (G11). Retirer le filtre par portee laisse la suite verte : le comptage devient global, et aucune sonde n'a une portee saine et l'autre malade avec un total global suffisant. G2 et G3 (une portee omise) mordent, G11 non.
4. **Declaration contre texte** (G4). Le garde lit le texte ; un garde qui lirait les declarations passe aussi, parce que `verifierLaPortee` impose declaration = texte sur chaque item. Aucune sonde ne fait mentir une declaration pour le garde seul. Equivalent tant que `verifierLaPortee` mord.
5. **"robinet"** (G10). Le mot ajoute a `PARTAGES` est redondant sur le paquet reel : la scene d'Enzo nomme aussi la cour.
6. **Listes fermees de `INT-CE1-1`** : inchangees depuis `cbfb152e` (Y07, Y08), "paresseux" et "au contraire" passent.
7. **Le sens des scenes** et le pli a 744 x 551 : hors du perimetre d'un test, a `nadia-prof` et a `ines-design`.

## 6. Instabilite, isolation

- **Test instable : aucun constate.** Chaque mesure est une execution unique. Les mutations ont ete lancees deux fois : le premier lancement a echoue entierement parce que mon script appelait le `bash` de WSL et non celui de Git (aucun resultat retenu, `git status` vide apres), et trois motifs (sondes 42 et 43, indice a espace insecable) ont ete refuses par le script avant tout lancement et corriges. Les resultats ci-dessus sont ceux du second lancement, aucun n'a ete relance pour obtenir un autre verdict.
- **Isolation.** Repertoire de scratchpad utilise : `s57-p4-yanis-qa-u`. Seule exception : le repertoire de jeton RF1 `s57-p4-jeton-verify`, pris deux fois (Verify, puis Package), supprime a chaque fois. Aucun jar lance, aucun port ouvert. Deux worktrees detaches crees au voisinage du checkout (`recreo-s57-yanis-lp6b` et `recreo-s57-yanis-lp6b-mut`), retires avant de rendre la main. Ni push, ni rebase, ni reset, ni fusion.
