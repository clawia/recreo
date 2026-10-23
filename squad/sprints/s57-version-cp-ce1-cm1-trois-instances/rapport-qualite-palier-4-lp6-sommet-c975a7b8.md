# Rapport qualite du palier 4, lot LP6, troisieme passe (sommet c975a7b8)

Auteur : `yanis-qa`, transverse. Date : 2026-10-23. `round-two` est a `a65341cb`.
LP6 : branche `s57-sofia-emc-ce1-04`, sommet `c975a7b8` (rapport de `sofia-sm`), code `53f98a74`, paquet `ce1-emc-interet-commun` (story `S57-BR-EMC-CE1-04`). Mes rapports precedents, `rapport-qualite-palier-4-lp6-sommet-cbfb152e.md` (`6a663723`) et `rapport-qualite-palier-4-lp6-sommet-ebbb2136.md` (`cbbb3e0b`), portent sur d'autres sommets et ne sont pas reecrits.

Mesures faites dans deux worktrees detaches propres a `c975a7b8` : un pour le `verify`, le package et les jobs, un pour les mutations. Le worktree du lot n'a pas ete touche. Les mutations ont tourne pendant le `verify` : les durees ne se comparent pas.

**Ce rapport ne prononce pas le Done pedagogique** (`nadia-prof`, seule). Le REFUSE sur M3 et les reserves 3, 4 et 5 portent sur le fond ; ce rapport ne dit rien de leur levee. Les scenes changees (section 11.1 du rapport de `sofia-sm`) n'ont pas de test qui mesure "une seule lecture defendable".

## 1. Resultat

1. `bash outils/mvn-jdk21.sh -B clean verify` au sommet : **exit 0, 3232 tests, 0 echec, 0 erreur, 0 ignore**. Annonce de `sofia-sm` : 3232, adapter-content 2171, test du paquet 74 ; les trois sont confirmes.
2. Jobs de `ci.yml` lances, un par un : `build` etape Verify, `build` etape Package, `build` etape "Verifier la presence du build Maven", `prefixes`, `chemins`, `outils`. Tous verts (section 3). Ce n'est pas la CI de GitHub, qui n'a pas tourne sur ce commit.
3. Mutations A a H de `sofia-sm` (section 11.5 de son rapport) : **les huit rouges**, avec le nombre d'echecs qu'elle annonce.
4. Les quatre sondes 46 a 49, isolees une par une : **chacune mord** (I46 a I49 rouges). La boucle qui s'arrete a la premiere qui echoue ne cache donc aucune sonde inerte. Reserve de diagnostic en section 5.
5. Survivants precedents et gardes : **G5 (UN_TIERS a 4) et G11 (filtre par portee) survivent encore**, et leurs jumeaux sur le garde symetrique du lieu (LG5, LG11) aussi. Le garde symetrique `verifierQueLeLieuNeDecidePasDeLaPortee` mord en marge nulle (A, LG6).
6. **Total de la base `a65341cb` : non mesure, deduit a 3158** (section 2).

## 2. `verify` complet, sous le jeton RF1

Commande : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, worktree detache propre. JDK 21.0.10 (propriete `java.version` des 326 `TEST-*.xml`). Jeton RF1 : repertoire `s57-p4-jeton-verify` absent de la racine du scratchpad au lancement, `mkdir` reussi, ecrit a 10:16:01, supprime par le script qui l'avait cree ; absent apres coup. `BUILD SUCCESS`, 10:16:01 a 10:28:53 (12:45 min).

| Module | Tests | Echecs / erreurs / ignores | Fichiers XML | Provenance |
|---|---|---|---|---|
| domain | 120 | 0 / 0 / 0 | 11 | surefire |
| application | 31 | 0 / 0 / 0 | 2 | surefire |
| adapter-web | 523 | 0 / 0 / 0 | 58 | surefire |
| adapter-content | 2171 | 0 / 0 / 0 | 191 | surefire |
| bootstrap | 381 | 0 / 0 / 0 | 60 | surefire |
| bootstrap | 6 | 0 / 0 / 0 | 4 | failsafe (integration) |
| **Total** | **3232** | | 326 | |

Methode : somme de l'attribut `tests` de chaque `TEST-*.xml` de `target/surefire-reports` et `target/failsafe-reports`, par module. Les six lignes `Tests run:` du journal Maven rendent les memes nombres. Chaque module a des tests.

**Delta de LP6 contre `a65341cb` : +74**, tous dans `PaquetCe1EmcInteretCommunTest`. `git diff a65341cb c975a7b8 -- '*Test.java'` : 74 lignes `@Test` ajoutees, 0 retiree, 0 ligne supprimee dans un fichier de test ; aucun `@ParameterizedTest`, `@TestFactory` ni `@RepeatedTest` dans les sources du sommet.

**Base `a65341cb` : non mesuree, et je ne peux pas l'etablir sans un second `verify` complet.** Deduction : 3232 - 74 = **3158**, valable tant qu'aucun test existant ne change de nombre ; les faits ci-dessus la soutiennent et elle concorde avec mes deux rapports precedents (3223 - 65, 3227 - 69). Ce n'est pas une mesure de l'arbre de base. `sofia-sm` etablit le total sur l'arbre fusionne.

## 3. Jobs de `.github/workflows/ci.yml` lances, sommet `c975a7b8`

| Job | Etapes | Resultat |
|---|---|---|
| `build` | "Verify" : `bash outils/mvn-jdk21.sh -B clean verify` | exit 0 (section 2) |
| `build` | "Package du fat jar" : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package`, tests inclus, sous un second jeton RF1 (ecrit a 10:37:46, supprime a la fin, absent apres coup ; l'attente eventuelle avant la prise n'est pas mesuree) | exit 0, `BUILD SUCCESS`, 10:37:46 a 10:47:36, `bootstrap/target/recreo.jar` present (21,5 Mo), 3226 tests rejoues (120 + 31 + 523 + 2171 + 381), 0 echec |
| `build` | "Verifier la presence du build Maven" | `pom.xml` present |
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `commit-atteignable.sh a65341cb`, `git log --no-merges --format=%s a65341cb..c975a7b8` vers `verifier-prefixes.sh` (6 commits), `verifier-garde-fou-accent-test.sh` (21 cas), `verifier-garde-fou-accent.sh a65341cb..c975a7b8` | exit 0 pour les six ; "0 commit(s) le touchent dans la plage, tous [THEO]" |
| `chemins` | `verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh'` vers `verifier-chemins.sh` | exit 0, "Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies." |
| `outils` | balayage `outils/*-test.sh` (6), `squad/outils/*/*-test.sh` (2), `outils/*-test.py` (5) | exit 0 pour chacun, **13** juges, 0 echec, aucun motif vide |

Ecarts a la CI : enveloppe de JDK du poste, `clean` ajoute au Verify, plage de `prefixes` bornee a `a65341cb..c975a7b8`, interpreteur Python du poste pour `outils`. Le commit de ce rapport n'est pas dans la plage mesuree : `prefixes` et `chemins` sont a relancer sur lui et sur le commit de fusion.

**Porte technique de LP6 au sommet `c975a7b8` : verte sur chacun des jobs nommes ci-dessus.**

## 4. Mutations

**Methode.** Une mutation d'un fichier du lot (classe de test ou paquet reel), appliquee par un script qui exige le nombre exact d'occurrences du motif attendu (sinon il refuse sans lancer Maven), puis :
`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am -Dtest=PaquetCe1EmcInteretCommunTest -Dsurefire.failIfNoSpecifiedTests=false test`
Perimetre : la classe seule, `-am` present, JDK 21.0.10. Base non mutee : exit 0, 74 sur 74. Verdict lu dans le `TEST-*.xml` de la classe, fichier restaure par `git checkout -- .`, `git status --short` vide apres chacune des 20 mutations. Fins de ligne CRLF respectees. Une seule execution par mutation, aucune relancee.

Les mutations A a H sont celles de la section 11.5 de `sofia-sm`. Celles dont l'enonce laisse un choix de texte (B, D) sont ecrites par moi : B passe les items 6, 22 et 14 a un objet personnel ("Zoe s'assoit a sa table pour dessiner.", "Maya recopie sa phrase.", "Enzo boit a sa gourde.", declaration changee) ; D remplace l'indice de l'item 13 par "Qui protege le bien-commun ?".

| Id | Mutation | Resultat |
|---|---|---|
| A | item 13 : "sa clochette dans la salle pour ..." | rouge, 1 echec : `int1LeLieuNeDecidePasDeLaPorteeDansAucuneDesDeuxPortees`, "dans la portee tous, 4 scenes sur 13" (12 contre 13 exiges) |
| B | items 6, 22, 14 passes a un objet personnel | rouge, 2 echecs : le garde du lieu et `int1LAppartenanceDeLObjetNeDecidePasDeLaPorteeDansAucuneDesDeuxPortees`, tous deux "portee un, 3 scenes sur 11" |
| C | "Les biens communs n'y sont pas." dans l'en-tete | rouge, 1 echec : `int2AucuneExpressionInterdite...`, "le fichier porte, meme en commentaire" |
| D | indice de l'item 13 : "bien-commun" | rouge, 1 echec : `int2AucuneExpressionInterdite...` |
| E | motif de test remis a l'ancien | rouge, 2 echecs : la boucle des sondes 46 a 49 et le test de l'identifiant |
| F | identifiant non retire (`fichier.replace(...)` retire) | rouge, 2 echecs : le fichier reel et le test de l'identifiant |
| G | motif sans trait d'union (espace seul, deux branches) | rouge, 1 echec : la boucle des sondes |
| H | motif sans pluriel (`s?` retire des trois, `generaux` retire) | rouge, 2 echecs : la boucle des sondes et le test de l'identifiant |
| I46 | seul "biens" cesse d'etre lu (`biens?` devient `bien`) | rouge, 2 echecs : la boucle des sondes et le test de l'identifiant |
| I47 | seul "bien-commun" cesse d'etre lu (trait d'union retire de la branche `bien`) | rouge, 1 echec : la boucle des sondes |
| I48 | seul "interets generaux" cesse d'etre lu (`interets?` devient `interet`) | rouge, 1 echec : la boucle des sondes |
| I49 | seul "interet-general" cesse d'etre lu (trait d'union retire de la branche `interet`) | rouge, 1 echec : la boucle des sondes |
| **G5** | `UN_TIERS` 3 devient 4 | **VERT, 74 sur 74** |
| **G11** | garde du possessif : filtre par portee retire | **VERT** |
| **LG5** | garde du lieu seul : seuil 3 devient 4 | **VERT** |
| **LG11** | garde du lieu : filtre par portee retire | **VERT** |
| LG6 | garde du lieu seul : seuil 3 devient 2 (plus strict) | rouge, 1 echec : le garde du lieu, "portee tous, 5 scenes sur 13" (10 contre 13) |
| LG7 | garde du lieu : `isGreaterThanOrEqualTo` devient `isGreaterThan` | rouge, 1 echec : sonde 45, **par artefact** (voir note) |
| LG1 | garde du lieu neutralise (retour immediat) | rouge, 2 echecs : sondes 44 et 45 |

**Isolation des quatre sondes (I46 a I49).** Chaque mutation retire une seule forme, et une seule : je l'ai verifie en rejouant les quatre motifs mutes sur les quatre enonces avec `re` de Python (semantique proche de Java, non identique : controle de construction, pas mesure du code teste). Resultat : I46 manque la sonde 46 seule, I47 la 47, I48 la 48, I49 la 49.

**Garde symetrique `verifierQueLeLieuNeDecidePasDeLaPortee`, marge nulle en portee tous.** Sur le paquet : 5 scenes pour 5 exigees (3 x 5 = 15 contre 13). Une scene de moins (A) donne 12 contre 13 : rouge. Le seuil est vivant dans le sens strict (LG6, 10 contre 13). Dans le sens lache (LG5), il ne l'est pas : voir section 5.

**Note sur LG7.** Meme artefact que G7 au rapport precedent : les sondes 44 et 45 n'ont chacune qu'une portee ; la portee absente passe de `0 >= 0` a `0 > 0`, faux, et le garde echoue sur "portee un, 0 scenes sur 0" au lieu du message que la sonde 45 attend. La mutation ne mesure pas la frontiere : 3 x 6 = 18 contre 11 et 3 x 5 = 15 contre 13 ne l'atteignent pas, aucun produit de 3 n'egale 11 ou 13.

## 5. Ce qui n'est pas garde, nomme

1. **Seuil `UN_TIERS` dans le sens lache** (G5, LG5). Passer de 3 a 4 laisse la suite verte, pour le garde du possessif comme pour celui du lieu. Les sondes 42 a 45 ont 0 scene croisee et echouent pour tout seuil positif ; seules les donnees reelles fixent le seuil, et A et B le fixent dans le sens strict seulement. Une sonde a 1 croisee sur 3 (acceptee a 3, refusee a 4) le fixerait. Inchange depuis `ebbb2136`.
2. **"Dans chacune des deux portees"** (G11, LG11). Retirer le filtre par portee laisse la suite verte sur les deux gardes. Aucune sonde n'a une portee saine et l'autre malade avec un total global suffisant. Les mutations qui omettent une portee entiere (LG1, G1 au rapport precedent) mordent ; celles-ci non. Inchange depuis `ebbb2136`.
3. **Diagnostic de la boucle des sondes 46 a 49.** `leJeuFautifCommiteEstRefuseSurLePlurielLeTraitDUnionEtLesFormesFlechiesDesExpressionsInterdites` appelle `.as(marqueur)` apres `assertThatThrownBy(...)` : quand le verificateur ne leve rien, le message ("Expecting code to raise a throwable.") ne nomme pas la sonde fautive. Le test mord (section 4), mais il faut relire le code pour savoir laquelle. Defaut mineur, non corrige : le fichier est a `sofia-sm`.
4. **Le sens des scenes** (une seule lecture defendable des items 3, 6, 11, 13, 17, 22, 24), le seuil de "17 sur 24" de la regle de mots et le pli a 744 x 551 : hors du perimetre d'un test, a `nadia-prof` et a `ines-design`.
5. **Hors perimetre de ce rapport** : G4 (declaration contre texte), G10 ("robinet") et les listes fermees d'`INT-CE1-1` n'ont pas ete rejoues ; leur etat est celui du rapport sur `ebbb2136`.

## 6. Instabilite, isolation

- **Test instable : aucun constate.** Chaque mesure est une execution unique. La base non mutee (74 sur 74) a ete lancee une fois avant les mutations, dans le meme script.
- **Isolation.** Repertoire de scratchpad utilise : `s57-p4-yanis-qa-v`. Seule exception : le repertoire de jeton RF1 `s57-p4-jeton-verify`, pris deux fois (Verify, puis Package), supprime a chaque fois par le script qui l'avait cree ; aucun jeton d'un autre agent touche. Aucun jar lance, aucun port ouvert. Deux worktrees detaches crees au voisinage du checkout (`recreo-s57-yanis-lp6c` et `recreo-s57-yanis-lp6c-mut`), retires avant de rendre la main. Ni push, ni rebase, ni reset, ni fusion.
