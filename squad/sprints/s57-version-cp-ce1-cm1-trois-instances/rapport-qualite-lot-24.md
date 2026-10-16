# [Sprint s57] Rapport qualite de `yanis-qa` - palier 2, relecture avant fusion du lot `S57-BR-FRA-CE1-24` avec `S57-FRA-FAMILLES-LIVRE`

- **Auteur** : `yanis-qa`. **Date** : 2026-10-16. **Arbre relu** : branche `s57-sofia-fra-ce1-24`, tete `6b70d7e5` (fusion de `round-two` `edcd3de9` dans la branche ; lot `1febd011`, rapport `e918f43e`). Aucun fichier de code ou de contenu n'est modifie par le commit de ce rapport : un seul fichier ajoute, celui-ci.
- **Ce que ce fichier ne fait pas** : il ne prononce ni Done technique ni Done pedagogique, ne fusionne rien, ne corrige rien. Le texte des corrections (item *chat*, indice de `FM6`, titre, consigne) est a `nadia-prof` ; l'orthotypographie est au `relecteur-pedagogique`.
- **Verdict** : **relecture faite, fusionnable**, avec quatre points nommes en section 6, dont aucun ne bloque la fusion et deux sont a corriger dans les ecrits de `sofia-sm` (`E1`, `E2`).

## Resume

| Code | Constat | Etat |
|---|---|---|
| F1 | `FM5-1` a `FM5-6` et les trois mesures de `FM6` rendent les valeurs attendues sur `6b70d7e5` ; `FM5-1`, `FM5-2` et `FM6` sont rouges sur `round-two` | **tenus** |
| F2 | 8 mutations sur une copie : 7 font rougir la classe concernee ; 1 (`chaton` remplace par `chatte`) reste **verte dans le reacteur** et rouge sur `FM5-2` | **un trou nomme (`E3`)** |
| F3 | Reacteur complet : `BUILD SUCCESS`, **2664 tests**, 0 echec, 0 erreur, 0 ignore, JDK 21.0.10 | **vert constate** |
| F4 | `K10` est leve sur l'arbre fusionne par l'amendement `A3` d'`ADR-0065` (hors de ce lot) : le garde-fou de l'accent est vert avec le libelle `(re-, dé-, -eur...)` | constate |
| F5 | `TMPDIR` du juge `mvn-jdk21-test.sh` : avis en section 4.1 | avis |
| F6 | `node` dans `ci.yml` : avis en section 4.2 | avis |

## 1. Criteres de la story, rejoues sur `6b70d7e5`

**Methode.** Commandes lancees depuis le worktree de la branche, avec `git show <ref>:<YAML>`, `git grep` et `git diff`, copiees de `docs/backlog/backlog.md` (annotation du 2026-10-16 10:45, `FM5`) ; `<YAML>` = `adapters/adapter-content/src/main/resources/contenu/ce1-familles-de-mots.yaml`. **Base de `FM5-6`** : `git merge-base round-two 6b70d7e5` = `edcd3de9` (`round-two` est a `9c271e14`, descendant de `edcd3de9`). Le meme chiffre sort avec `4004fab7`, la base du lot de `sofia-sm` (ancetre de `edcd3de9`) : le YAML n'a pas bouge entre les deux.

| Code | Attendu | `round-two` (`9c271e14`) | `6b70d7e5` |
|---|---|---|---|
| `FM5-1` | 0 | 1 | **0** |
| `FM5-2` | 4 | 0 | **4** |
| `FM5-3` | `  # FAM-CE1-2: a, b` | (non rejoue, la commande de la story donne vide avant) | **`  # FAM-CE1-2: a, b`** |
| `FM5-4` | 24 | 24 | **24** |
| `FM5-5` | 14 | 14 | **14** |
| `FM5-6` | 34 | 0 (aucun diff) | **34** (base `edcd3de9`) ; **34** (base `4004fab7`) |
| `FM6` a : `grep -c 'indice: "Le bon mot garde un morceau et parle de la même chose."'` | 14 | 0 | **14** |
| `FM6` b : `grep -cE 'indice: "(Cherche le mot qui parle de la même chose\.\|Un mot de la même famille garde un morceau du mot\.\|Compare les trois mots avec le mot de départ\.)"'` | 0 | 14 | **0** |
| `FM6` c : `git diff -U0 <base> 6b70d7e5 -- <YAML> \| grep -cE '^[-+] *indice:'` | 28 | 0 | **28** (les deux bases) |

Le diff du YAML : `git diff --stat edcd3de9 6b70d7e5 -- <YAML>` rend *17 insertions, 17 deletions*. Clauses rejouees avec leurs commandes : `FM2` 14 enonces de famille et 10 d'affixe ; `FM3` (`grep -E` de la story) 0 ; `K3` : une entree de catalogue sert `ce1-familles-de-mots` (`grep -c "paquet: ce1-familles-de-mots$"` 1, id `bandit-manchot-familles-de-mots-ce1` 1), 152 entrees contre 151 sur `edcd3de9`.

**Les espaces.** `grep -cP 'chat\x{A0}»\x{A0}\?"'` sur le blob de l'item 12 rend 1 : chevrons et `?` en U+00A0, comme `camille-po` l'exige (annotation, section "Les espaces"). Aucune espace ordinaire dans les chevrons de cet item.

**Delta de code de `6b70d7e5` sur `edcd3de9`** (`git diff --name-only`, hors `squad/`) : 7 fichiers, ceux que le rapport de `sofia-sm` annonce (YAML, catalogue, test du paquet, trois fichiers de contre-exemple, inventaire de `bootstrap`). Un seul fichier de test neuf.

## 2. Tracabilite et ce que chaque test garde

La classe `PaquetCe1FamillesDeMotsTest` (24 tests) cite `S57-BR-FRA-CE1-24` dans sa Javadoc et porte les codes de condition dans les noms et les messages d'echec (`FAM-CE1-1`, `FM6`, `TRV-CE1-4`, ...).

| Critere | Test | Mord ? (section 3) |
|---|---|---|
| `FM1` | `trv7fm1LePaquetGardeAuMoinsVingtItemsEtChacunATroisPropositionsDistinctes` | non mute |
| `FM2` | `fam1LesProportionsSontRecomptees` + deux jeux fautifs | oui (`M6`) |
| `FM3` | `fam3LeNomDeLAffixeNEstJamaisLuParLEnfant`, `trv4...` + jeu fautif | oui (`M4`) |
| `FM4` | non garde, dit dans la Javadoc ; garde etroit `fam2AucuneReponseSavanteRefuseeNEstAttendue` | oui (`M2`) |
| `FM5` | **aucun test Java** ; gardes par les commandes de la section 1 seulement | non (`M5`, `E3`) |
| `FM6` | `fm6ChaqueItemDeFamillePorteLIndiceDeLaSection36` + jeu fautif | oui (`M1`) |
| entree de catalogue (`K3`) | `laConsigneDuJeuEstCelleDesEntreesExistantes` (une et une seule entree, consigne exacte) ; inventaire de `TexteAccentueExposeParLePortEntrantTest` (id, titre, libelle) | oui (`M3a`, `M3b`, `M3d`) |

## 3. Mutations (copie de l'arbre `6b70d7e5`)

**Methode.** Copie de l'arbre par `git archive 6b70d7e5` dans un repertoire de travail de la session, hors de tout worktree du depot ; une mutation a la fois ; fichier restaure par copie de reference apres chaque mutation, `cmp` verifie. Chaque mutation est appliquee par un script qui **refuse** si le motif n'est pas trouve exactement le nombre de fois prevu ; le nombre de motifs trouves est rapporte, avec le nombre de tests lances. Commande de test : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=PaquetCe1FamillesDeMotsTest -Dsurefire.failIfNoSpecifiedTests=false` (avec `-am`, `CLAUDE.md`, section Prerequis de build) ; pour les mutations du catalogue, `-pl bootstrap -am` avec `-Dtest=PaquetCe1FamillesDeMotsTest,TexteAccentueExposeParLePortEntrantTest`. **Temoin vert** de la copie avant toute mutation : 24 tests, 0 echec, `BUILD SUCCESS`. JDK 21.0.10 (ligne d'enveloppe `Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10`).

| Code | Mutation | Motifs trouves | Tests lances | Resultat |
|---|---|---|---|---|
| `M1` | indice de l'item *dent* remplace par *"Compare les trois mots avec le mot de départ."* | 1 | 24 | **rouge** : `fm6ChaqueItemDeFamillePorteLIndiceDeLaSection36` |
| `M2` | item 12 remis a *livre* / *librairie* / *livrer*, *cahier* (indice de `FM6` conserve) | 1 | 24 | **rouge** : `fam2AucuneReponseSavanteRefuseeNEstAttendue` seul |
| `M3a` | entree de catalogue retiree (7 lignes) | 1 | 24 puis 10 | **rouge** : `laConsigneDuJeuEstCelleDesEntreesExistantes` ; et `chaqueChaineDeLInventaireEstEncoreExposeeParLeCatalogueReel` (bootstrap) |
| `M3b` | consigne de l'entree sans accent (*reponse*) | 1 | 24 | **rouge** : `laConsigneDuJeuEstCelleDesEntreesExistantes` |
| `M3d` | titre *"Les familles de mots CE1"* (niveau dans le titre, `ADR-0078`) | 1 | 24 puis 10 | classe du paquet **verte** (24) ; **rouge** dans l'inventaire de `bootstrap` (4 echecs sur 10, dont `touteChaineExposeeParLeCatalogueReelEstInventoriee`) |
| `M4` | les 5 indices d'affixe *"...au début du mot ?"* remplaces par *"Cherche le suffixe du mot"* | 5 | 24 | **rouge** : `fam3LeNomDeLAffixeNEstJamaisLuParLEnfant` et `trv4AucunMotDuLexiqueExcluNEstServi...` (2 echecs) |
| `M5` | `bonneReponse: "chaton"` remplace par `"chatte"` | 1 | 24 | **vert** (24 tests). `FM5-2` rejoue sur le fichier mute (meme commande, CR retires) rend **3** au lieu de 4 : **rouge sur la commande** |
| `M6` | enonce de l'item *dent* : *"Quel mot est de la même famille"* remplace par *"Trouve le mot de la même famille"* | 1 | 24 | **rouge** : `fam1LesProportionsSontRecomptees` (*"13 items de famille et 10 d'affixe sur 24, chaque item est de l'un des deux"*) |

**Deux faux verts evites pendant la mesure, nommes.** (a) Mon premier lot d'arguments de mutation rendait *"MOTIF TROUVE 0"* : mes arguments contenaient des caracteres accentues que le shell du poste ne transmet pas intacts, puis le fichier de la copie est en fins de ligne CRLF alors que mes motifs multi-lignes etaient en LF. Les deux fois la suite est restee verte et je ne l'ai pas lue comme un vert : j'ai corrige le script. (b) Une premiere version de la mutation `M4` portait un motif inexistant ; le script, parametre pour attendre 0 occurrence, a rendu `MOTIF TROUVE 0` et `BUILD SUCCESS` : c'est ce couple que le controle "motif trouve" existe pour demasquer. `M4` a ete refaite avec le vrai motif (5).

**Contre-exemples.** Les deux jeux fautifs et le catalogue fautif sont lus par les tests `leJeuFautif...` et `leCatalogueFautif...`. Ils ne sont pas mutes a part : ils appellent les memes verificateurs que les tests directs, que `M1`, `M2`, `M4` et `M6` ont fait rougir.

## 4. Les deux questions routees

### 4.1 `TMPDIR` et `mvn-jdk21-test.sh` : refuser ou normaliser

**Avis : normaliser, a l'endroit ou le chemin entre dans `PATH`, pas refuser.** Forme : `BAC="$(cd "$(mktemp -d)" && pwd)"` a la place de `BAC="$(mktemp -d)"`.

- **Mesure** (sonde sur un repertoire jetable, `TMPDIR` en forme mixte ; **le juge n'a pas ete lance**, conformement a la consigne) : `mktemp -d` rend `C:/.../tmp.XXXXXXXXXX` ; `cd "$a" && pwd` rend `/c/.../tmp.XXXXXXXXXX` ; avec `PATH="$a/bin:$PATH"`, le premier element de `PATH` vu par un sous-shell est **`C`** ; avec la forme `pwd`, c'est le chemin complet. Le mecanisme que `sofia-sm` decrit (section 4 de `planning.md`) est confirme sur ce point ; **je n'ai pas remesure ses 10 echecs et 4 ok**, ni explique pourquoi la forme a barres inverses passe.
- **Pourquoi normaliser** : le juge doit rendre le meme verdict quel que soit le `TMPDIR` de l'agent ; refuser transformerait un faux rouge (10 cas en echec pour un motif etranger a leur objet) en un refus, et ajouterait un chemin de code que la CI Linux n'executerait jamais. `cd && pwd` n'a pas de branche propre a un systeme : sur Linux elle rend le chemin tel quel, la CI l'execute a chaque passage.
- **Ce que cet avis ne garantit pas** : aucun cas du juge ne peut rejouer la forme mixte sur Linux, la correction n'aura donc pas de contre-exemple en CI. Sa preuve est manuelle et datee (`ADR-0009`, enveloppes), a faire une fois la modification ecrite : juge lance avec `TMPDIR` en forme mixte, 14 ok. Le script est a `theo-archi` ou `sofia-sm` pour la modification ; je ne l'ai pas touche.
- Un `TMPDIR` contenant un deux-points dans une autre position (non Windows) reste un cas que `cd && pwd` ne corrige pas : je ne l'ai pas mesure.

### 4.2 Declarer `node` dans le job `outils` de `ci.yml`

**Avis : le declarer, par `actions/setup-node` avec une version majeure fixee, dans le seul job `outils`. Ce n'est pas un blocage, et ce n'est pas un faux vert en l'etat.**

- **Lu** : `ci.yml` declare Java par `actions/setup-java` (job `build`) ; le job `outils` ne declare ni `node` ni Python (il choisit `python3` ou `python` s'il existe). `outils/mesurer-additions-cp-test.sh` lance `node` des sa ligne 36 et sort **1 avec `ECHEC node absent`** s'il manque : l'absence est donc rouge et bruyante, pas silencieuse.
- **Pourquoi declarer quand meme** : la regle que ce depot applique a Java (une dependance de la CI s'ecrit dans le workflow, pas dans l'image) ; sans declaration, une evolution de l'image `ubuntu-latest` change la version de `node` sous le juge sans qu'un commit du depot le dise. Les API utilisees par `mesurer-additions-cp.mjs` et par le juge (`node:fs`, `node:net`, `node:path`, `node:url`, `node:child_process`, `node:http`, modules ES) existent dans toute version de `node` maintenue : **le choix de la majeure est libre**, ce n'est pas une contrainte de compatibilite.
- **Non mesure** : la version de `node` que porte l'image de la CI (je n'ai pas lu la CI distante). Sur le poste, `node --version` rend `v24.12.0` ; ce n'est pas la valeur de la CI.
- **Meme famille** : Python est une dependance implicite du meme job. Si la squad declare `node`, la meme decision vaut pour Python ; sinon aucune des deux. `ci.yml` n'est pas modifie par ce rapport.

## 5. Reacteur complet

**Commande** : `bash outils/mvn-jdk21.sh -B clean verify`, depuis la racine du worktree de la branche, sans `-pl`, tete `6b70d7e5`, arbre propre (`git status --short` vide). Sortie : **`BUILD SUCCESS`**, `Total time: 05:22 min`, les six modules `SUCCESS`.

| Module | Tests (ligne `Tests run` Surefire ou Failsafe) | Echecs, erreurs, ignores |
|---|---|---|
| domain | 120 | 0, 0, 0 |
| application | 31 | 0, 0, 0 |
| adapter-web | 496 | 0, 0, 0 |
| adapter-content | 1630 | 0, 0, 0 |
| bootstrap (Surefire) | 381 | 0, 0, 0 |
| bootstrap (Failsafe) | 6 | 0, 0, 0 |
| **Total** | **2664** | 0, 0, 0 |

- **Methode** : somme des six lignes, recomptee a part par l'attribut `tests` des fichiers `TEST-*.xml` de chaque module (2664 aussi). **JDK 21.0.10**, valeur de `java.version` lue dans **239** rapports Surefire (la JVM des tests, pas l'entree de toolchain). Classe du lot : `PaquetCe1FamillesDeMotsTest`, 24 tests, 0 echec.
- **Delta** : +24 sur `edcd3de9`, **calcule par fichier** (un seul fichier de test neuf, 24 tests) ; **je n'ai pas remesure** le total de `edcd3de9` (2640, annonce par la consigne). Mon total vaut pour l'arbre `6b70d7e5`, pas pour l'arbre fusionne final : `sofia-sm` l'etablit apres fusion.
- **Etape non lancee** : *"Package du fat jar"* du job `build`.
- **Jobs de `ci.yml` lances par moi sur l'arbre** : `build` (ci-dessus, hors fat jar) ; `prefixes` : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-garde-fou-accent-test.sh` sortent 0, `verifier-prefixes.sh` sur les sujets de `edcd3de9..6b70d7e5` (hors fusions) sort 0, `verifier-garde-fou-accent.sh "edcd3de9..6b70d7e5"` sort 0 (*"0 commit(s) le touchent dans la plage, tous [THEO]"*) ; `chemins` : `verifier-chemins-test.sh` et `verifier-chemins.sh` sur `git ls-files` de l'arbre sortent 0. **Job `outils` : non lance par moi** (les 13 juges), et **la CI distante n'est pas lue** : je ne deduis pas leur etat.

## 6. Ecarts et points ouverts

| Code | Constat | A qui |
|---|---|---|
| `E1` | `rapport-sofia-dev-palier-2.md` ecrit *"non livrable en l'etat"* et *"rouge sur `K10`"* ; sur `6b70d7e5` le reacteur est vert (section 5). Le rapport decrit l'arbre `e918f43e`, pas la tete. L'historique de la branche porte aussi un sujet de commit *"NON LIVRE : rouge sur K10"* (`1febd011`) | `sofia-sm` : une ligne datee dans la cloture, a la fusion |
| `E2` | Le meme rapport classe `FM5` *"sans objet"* ; `FM5` a ete amende apres coup (annotation du 2026-10-16 10:45) et ses six commandes s'appliquent, rejouees vertes en section 1 | `sofia-sm`, meme ligne |
| `E3` | **`FM5-2` et `FM5-3` ne sont gardes par aucun test Java** : le texte de l'item 12 (*chat*, *chaton*, *château*, *souris*) peut changer sans que le reacteur rougisse (`M5`). Seule la commande de la story le garde. Ce n'est pas un defaut du lot (le texte est de `nadia-prof`, et copier son texte dans un test en ferait un second exemplaire a tenir), mais *"non garde : `FM5-2` et `FM5-3`"* doit etre dit | `sofia-sm` : decider entre le dire et un test d'egalite sur l'item 12 ; je n'ecris ni l'un ni l'autre |
| `E4` | `FM3` ecrit que le mot *préfixe* de `descripteur.competence` n'est pas servi par les gabarits : **confirme** par `git grep -n "competence"` sur `adapters/adapter-web/src/main/resources/templates` a `6b70d7e5` (0 ligne). Mais le libelle est inventorie par `TexteAccentueExposeParLePortEntrantTest` comme chaine exposee par le port entrant, et `fam3` ne lit que les quatre champs de l'enfant. L'amendement de `FM3` attendu par `camille-po` (annotation, point 3 de l'ecart `K10`) n'est pas dans ce lot | `camille-po`, `theo-archi` |

**Non mesure** : aucun jar lance, aucun ecran joue, aucune cote de pli ; l'orthotypographie ; l'exactitude pedagogique des 24 items (famille exacte, ordre des distracteurs, indice) ; `nombreDeManches: 10` n'a pas ete mute ici (palier 1, `C8` : valeur non gardee, a ne pas supposer corrigee).

## 7. Ce que ce rapport ne fait pas

Ne prononce aucun Done, technique ou pedagogique : le Done technique est celui de la CI, tous jobs. Ne juge ni le titre, ni la consigne, ni la place de l'entree, ni le choix de *chat* et *chaton*. Ne fusionne rien, ne pousse rien.

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee.**
