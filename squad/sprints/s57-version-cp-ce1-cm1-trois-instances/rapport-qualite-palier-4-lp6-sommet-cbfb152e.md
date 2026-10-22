# Rapport qualite du palier 4, lot LP6 (sommet cbfb152e)

Auteur : `yanis-qa`, transverse. Date : 2026-10-23 (mesures faites dans la nuit du 22 au 23). `round-two` est a `a65341cb`.
LP6 : branche `s57-sofia-emc-ce1-04`, sommet `cbfb152e`, code `e85a93fe`, paquet `ce1-emc-interet-commun` (story `S57-BR-EMC-CE1-04`, lot de `sofia-sm` : ce rapport qualite est le mien seul, `composition-palier-4.md` 5.3 point 3).

Mesures faites dans deux worktrees detaches au sommet `cbfb152e`, propres avant chaque mesure : un pour les `verify` et les jobs, un pour les mutations. Le worktree du lot n'a pas ete touche.

**Ce rapport ne prononce pas le Done pedagogique** (`nadia-prof`, seule). `INT-CE1-1` (une seule lecture defendable) n'est pas mesurable par un test : le garde l'a dit lui-meme, et ce rapport ne le comble pas.

## 1. Resultat

1. `bash outils/mvn-jdk21.sh -B clean verify` au sommet : **exit 0, 3223 tests, 0 echec, 0 erreur, 0 ignore** (annonce de `sofia-sm` : 3223).
2. Jobs de `ci.yml` lances : `build` (Verify et Package), `prefixes`, `chemins`, `outils`. Tous verts (section 3). Ce n'est pas la CI de GitHub, qui n'a pas tourne sur ce commit.
3. Mutations : 26 mutations rejouees sur la classe, dont 3 rejouees aussi dans `bootstrap`. **Les gardes de `INT-CE1-1` (portee, opposition, jugement), de `INT-CE1-2` (formules, expressions) et d'`ADR-0086` clause 1 mordent** (section 4). Le garde de la clause 1 est double : un dans la classe, un dans `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`.
4. **Ce qui ne mord pas** : quatre graphies de `INT-CE1-2` (trait d'union, deux pluriels, commentaire sans accent), deux mots de blame ou d'opposition hors des listes fermees. Aucune n'est un defaut de code, ce sont les bornes des listes ; elles sont a `nadia-prof` et a `theo-archi` (section 5).
5. **Total de la base `a65341cb` : non mesure**, deduit a 3158 (section 2).

## 2. `verify` complet, sous le jeton RF1

Commande : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`. JDK 21.0.10 (propriete `java.version` des `TEST-*.xml`). Jeton RF1 : `mkdir` a la racine du scratchpad reussi du premier coup a 23:36:28, repertoire supprime a la fin (`ls` de la racine ne le liste plus). `BUILD SUCCESS`, duree 23:36:28 a 23:48:25 (11 min 57 s) pendant que des mutations tournaient sur l'autre worktree : la duree ne se compare pas.

| Module | Tests | Echecs / erreurs / ignores | Fichiers XML | Provenance |
|---|---|---|---|---|
| domain | 120 | 0 / 0 / 0 | 11 | surefire |
| application | 31 | 0 / 0 / 0 | 2 | surefire |
| adapter-web | 523 | 0 / 0 / 0 | 58 | surefire |
| adapter-content | 2162 | 0 / 0 / 0 | 191 | surefire |
| bootstrap | 381 | 0 / 0 / 0 | 60 | surefire |
| bootstrap | 6 | 0 / 0 / 0 | 4 | failsafe (integration) |
| **Total** | **3223** | | 326 | |

Methode : attribut `tests` de chaque `TEST-*.xml` de `target/surefire-reports` et `target/failsafe-reports`, somme par module. Les six lignes `Tests run:` du journal Maven rendent les memes six nombres. Chaque module a des tests.

**Delta de LP6 : +65**, tous dans `PaquetCe1EmcInteretCommunTest` (65 tests, mesure isolee au point 4 : `-pl adapters/adapter-content -am`, 65 sur 65). `git diff a65341cb cbfb152e -- '*Test.java'` : 3 fichiers, 1314 lignes ajoutees, 0 retiree, 65 occurrences de `@Test` ajoutees.

**Base `a65341cb` : non mesuree.** Je n'ai pas lance un second `verify` complet. Deux deductions qui concordent, ni l'une ni l'autre n'est une mesure de l'arbre :
- 3223 - 65 = **3158**, valable si aucun test existant ne change de nombre. Aucune des 324 classes de test executees ne declare `@ParameterizedTest`, `@TestFactory` ni `@RepeatedTest` (balayage des sources de test du worktree, croise avec les XML).
- Mon rapport sur LP5 et LP3 deduisait une base `51a9d957` de 3020 ; `git diff 51a9d957 a65341cb -- '*Test.java'` ajoute 138 `@Test` (64 + 50 + 24 pour p4-10) et en retire 0 : 3020 + 138 = **3158**. Cette base de 3020 etait elle-meme deduite.

`sofia-sm` etablit le total sur l'arbre fusionne.

## 3. Jobs de `.github/workflows/ci.yml` lances, sommet `cbfb152e`

| Job | Etapes | Resultat |
|---|---|---|
| `build` | "Verify" : `bash outils/mvn-jdk21.sh -B clean verify` | exit 0 (section 2) |
| `build` | "Package du fat jar" : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package`, sous le jeton, tests inclus | exit 0, 23:49:14 a 23:58:47, `BUILD SUCCESS`, `bootstrap/target/recreo.jar` present (21,5 Mo), 3217 tests rejoues (120 + 31 + 523 + 2162 + 381, sans les 6 d'integration), 0 echec |
| `build` | "Verifier la presence du build Maven" | non lancee (teste `pom.xml`) |
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `commit-atteignable.sh a65341cb`, `verifier-prefixes.sh` sur `git log --no-merges --format=%s a65341cb..cbfb152e` (2 commits, tous `[SOF]`), `verifier-garde-fou-accent-test.sh`, `verifier-garde-fou-accent.sh a65341cb..cbfb152e` | exit 0 pour les six |
| `chemins` | `verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh'` vers `verifier-chemins.sh` | exit 0, "Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies." |
| `outils` | balayage `outils/*-test.sh` (6), `squad/outils/*/*-test.sh` (2), `outils/*-test.py` (5) | exit 0, **13** juges, 0 echec, aucun motif vide |

Ecarts a la CI : enveloppe de JDK du poste, `clean` ajoute au Verify, plage de `prefixes` celle du lot, interpreteur Python du poste pour `outils`. Mon rapport precedent annoncait 24 juges pour `outils` au sommet de LP5 ; `git ls-tree` rend 13 sur `db5e0e6d` comme sur `a65341cb`, et le balayage rend 13 ici. Je ne sais pas expliquer le 24 de ce rapport.

**Porte technique de LP6 au sommet `cbfb152e` : verte sur tout ce que j'ai lance**, soit tous les jobs sauf l'etape triviale de presence de `pom.xml`. `prefixes` et `chemins` sont a relancer sur le commit de ce rapport et sur le commit de fusion.

## 4. Mutations

**Methode.** Une mutation du paquet `ce1-emc-interet-commun.yaml`, de l'entree de catalogue ou de la classe de test, appliquee sur les donnees reelles, puis :
`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am -Dtest=PaquetCe1EmcInteretCommunTest -Dsurefire.failIfNoSpecifiedTests=false test`
(perimetre : classe seule, 65 tests, `-am` present ; base non mutee : 65 sur 65 verts, exit 0). Verdict lu dans le XML Surefire, fichier restaure par `git checkout -- .`, `git status --short` vide apres chaque execution. Trois mutations (Y13d, Y13e, Y16) sont rejouees dans `bootstrap -am` avec `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`, `TexteAccentueExposeParLePortEntrantTest`, `AucunMotServiNeSEcarteDeSonAccentTest` et la classe : `-Dtest=<ces quatre classes> -Dmaven.test.failure.ignore=true`, base non mutee verte (19 tests de `bootstrap`). Y07, Y08, Y13a, Y13b et Y13c ne sont mesurees que dans la classe.

Un defaut de ma propre procedure, trouve en cours de route et corrige : mon script tronquait le fichier avant d'evaluer la mutation, et six mutations dont le motif contenait une espace ordinaire avant `?` (le fichier porte une espace insecable, `TYP-1`) ont fait echouer le script et laisse un paquet vide a la suite. Les resultats de Y10, Y11, Y13a a Y13e, Y14 a Y18 ont ete **jetes et rejoues** apres correction ; les autres (Y01 a Y08, Y12, T01 a T05) precedent la panne et sont valides par leur etat de depart (`git status` vide, 65 tests).

| Id | Garde | Mutation | Resultat |
|---|---|---|---|
| Y01 | `INT-CE1-1` portee | "Leo taille son crayon" devient "taille le crayon" (portee un, plus de possessif) | rouge, `int1LaPorteeDeclareeEstCelleDeLaBonneReponseEtDeLaScene` |
| Y02 | `INT-CE1-1` portee | "Mila essuie le tableau" devient "son tableau" (portee tous avec possessif) | rouge, meme test |
| Y03 | `INT-CE1-1` portee | declaration du premier item passee de tous a un | rouge, meme test |
| Y06 | `INT-CE1-1` portee | scene de portee un qui nomme "la cour" | rouge, meme test |
| Y04 | `INT-CE1-1` opposition | "...ses chaussures mais pas pour tous." | rouge, `int1AucuneSceneNOpposeUnPersonnageAuGroupe...` |
| Y05 | `INT-CE1-1` jugement | "..., quel egoiste." | rouge, meme test |
| **Y07** | `INT-CE1-1` jugement | "..., quel paresseux." (mot de blame hors de la liste) | **VERT, 65 sur 65** : liste fermee, voir section 5 |
| **Y08** | `INT-CE1-1` opposition | "..., au contraire de Mila." (locution hors de la liste) | **VERT, 65 sur 65** : liste fermee |
| Y10 | `INT-CE1-2` | "interet general" (accentue) dans un indice | rouge, `int2AucuneExpressionInterdite...` |
| Y11 | `INT-CE1-2` | "bien commun" en commentaire du fichier | rouge, meme test |
| Y12 | `INT-CE1-2` | "bien commun" dans la consigne de l'entree de catalogue | rouge, `k3LEntreeDuCatalogue...` |
| **Y13a** | `INT-CE1-2` | "bien-commun" (trait d'union) dans un indice | **VERT, 65 sur 65** |
| **Y13b** | `INT-CE1-2` | "biens communs" (pluriel) dans un indice | **VERT, 65 sur 65** |
| **Y13c** | `INT-CE1-2` | "interets generaux" (pluriel, accentue) dans un indice | **VERT, 65 sur 65** |
| Y13d | `INT-CE1-2` | "interet general" sans accent dans un indice | vert dans la classe, **rouge dans `AucunMotServiNeSEcarteDeSonAccentTest`** (`chaqueMotServiEnProductionPorteSonAccent`) : ferme par un garde transverse |
| **Y13e** | `INT-CE1-2` | "interet general" sans accent en commentaire du fichier | **VERT** (65 de la classe, 7 du garde de l'accent, 9 et 10 de `bootstrap`, tous verts) : la regex du fichier entier cherche la graphie accentuee |
| Y14 | `INT-CE1-2` | une bonne reponse reecrite "pour tout le monde" | rouge, 3 tests (portee, `adr0086`, formules) : le garde est plus strict que l'arbitrage, qui admet "pour tout le monde" |
| Y15 | `INT-CE1-2` | une formule remplacee par un nombre dans les propositions | rouge, `int2ChaqueItemPorteLesDeuxFormules...` |
| **Y16** | `ADR-0086` clause 1 | tous les items servis avec "pour deux eleves" ont la meme bonne reponse (le correctif du paquet, la rotation des nombres, est defait) | **rouge**, `adr0086ChaqueNombreDEleveSertLesDeuxBonnesReponses` dans la classe, **et** rouge dans `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest.leContenuServiEstConforme` (rejouee dans `bootstrap -am`, 19 tests : 1 echec) |
| Y17 | `ADR-0086` clause 1 | Y16, plus le garde de la classe neutralise | classe : seul le jeu fautif (sonde 36) rougit ; le garde transverse reste, voir Y16. Le garde de la classe n'est donc pas le seul. |
| Y18 | catalogue | `nombreDeManches` 10 devient 5 | rouge, `k3LEntreeDuCatalogue...` |
| T01 | sondes de la portee | `verifierLaPortee` neutralisee (retour immediat) | rouge, 6 tests `leJeuFautifCommite...` (sondes de portee) |
| T02 | sonde 36, clause 1 | `verifierQueChaqueNombreSertLesDeuxReponses` neutralisee | rouge, `leJeuFautifCommiteEstRefuseSurUnNombreServiAvecUneSeuleBonneReponse` |
| T03 | sondes des mots | `verifierLesMotsInterdits` neutralisee | rouge, 2 tests (deux expressions ; commentaire du fichier) |
| T04 | sondes du blame | `verifierQueLaSceneNeBlameJamais` neutralisee | rouge, 2 tests (balance ; jugement) |
| T05 | sondes des formules | `verifierLesFormules` neutralisee | rouge, 4 tests |

Lecture des trois questions posees :
- **`INT-CE1-1`** : la portee declaree, le mot de la liste, l'opposition et le jugement mordent sur le paquet reel et chacun a sa sonde qui mord le verificateur neutralise (T01, T04). **Une seule lecture defendable** n'est pas gardee et n'est pas mordable (K8 du rapport de `sofia-sm`, point 7 de sa section 7).
- **`INT-CE1-2`** : mots interdits mordent sur les champs et le fichier (Y10 a Y12) ; les formules et leur famille mordent (Y14, Y15). Les graphies hors liste sont en section 5.
- **`ADR-0086` clause 1** : defaire le correctif (rotation des trois nombres) rougit deux gardes independants (Y16). La sonde 36 mord le verificateur de la classe (T02). Le correctif tient ; ce n'est pas un garde decoratif.

**Donnees des sondes.** Les sondes de la portee (04 a 07) et la sonde 36 sont des items du jeu fautif, et chacune mord son verificateur neutralise (T01, T02). Je n'ai pas relu les 44 items fautifs un par un pour verifier que chacun ne fait defaut que sur une seule propriete : T01 a T05 prouvent que le verificateur est sollicite, pas que le defaut du jeu est unique.

## 5. Ce qui n'est pas garde, nomme

1. **`INT-CE1-2` sur les graphies** (Y13a, Y13b, Y13c). La regex lit `interet general` et `bien commun` au singulier, separes par des espaces. "bien-commun", "biens communs" et "interets generaux" ne la declenchent pas. "biens communs" est le nom du paquet voisin `ce1-emc-biens-communs` (`EMC-CE1-01`) : si `nadia-prof` interdit aussi le pluriel, la regex est trop etroite ; si elle ne l'interdit que le singulier, elle est exacte. A elle de dire laquelle.
2. **`INT-CE1-2` en commentaire sans accent** (Y13e). La phrase du rapport de `sofia-sm` "commentaires compris" est vraie pour la graphie accentuee seulement ; un commentaire "interet general" passe. Aucun enfant ne lit un commentaire. Le garde transverse de l'accent ferme le meme ecart dans un champ lu (Y13d).
3. **Les listes fermees de `INT-CE1-1`** (Y07, Y08) : "paresseux" et "au contraire" passent, comme tout mot de blame ou d'opposition absent des listes ecrites dans le test. Le test prouve la coherence de la scene avec la liste, jamais que la liste est complete (le rapport de `sofia-sm` le dit en section 7, derniere ligne).
4. **Le sens des scenes** : non gardable, a `nadia-prof` (ses points J2 a J8).
5. **Pli a 744 x 551** : seuils de `LP4` repris (112 et 46 caracteres), non mesures sur ce paquet ; question `Q2` de `sofia-sm` a `ines-design`.

## 6. Instabilite, isolation

- **Test instable : aucun constate.** Chaque mesure est une execution unique ; aucune mutation n'a ete relancee pour obtenir un resultat, hors les rejeux dus a la panne de mon script (section 4).
- **Isolation.** Repertoire de scratchpad utilise : `s57-p4-yanis-qa-t` (journaux, scripts de mutation, resultats). Seule exception : le repertoire de jeton RF1 `s57-p4-jeton-verify`, pris deux fois (Verify puis Package), supprime a chaque fois ; `ls` de la racine apres coup ne le liste plus. Aucun jar lance, aucun port ouvert. Deux worktrees detaches crees au voisinage du checkout (`recreo-s57-yanis-lp6` et `recreo-s57-yanis-lp6-mut`), retires avant de rendre la main. Ni push, ni rebase, ni reset, ni fusion.
- **Racine : propre.** `find <racine> -maxdepth 1 -mindepth 1 -type f -newer <debut de mon premier verify>` rend 0 fichier (la racine en porte 16, aucun de moi) ; `-type d` rend 5 repertoires : `s57-p4-nadia-lp6`, `s57-p4-sofia-lp6`, `s57-p4-sofia-lp6-reprise`, `s57-p4-theo-archi-t`, `s57-p4-yanis-qa-t` (le mien) ; le repertoire de jeton est absent.
