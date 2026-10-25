# s57 - palier 5 : avis de theo-archi sur les neuf lots de questionner le monde CE1 (garde d'AIM-CE1-2, rang de la bonne reponse, faisabilite, tests touches)

- **Auteur** : `theo-archi`. Date : 2026-10-25, heure non lue (shell indisponible).
- **Arbre** : worktree `s57-p5-theo-garde`, base `570599ce` **annoncee par l'agent lanceur, non verifiee par `git`** (shell indisponible).
- **Mesure** : **aucune commande executee**, aucun JDK lance, aucune version a declarer. `[mesure]` signifie lu au disque par lecture de fichier ; `[calcul]` signifie applique a la main sur un texte lu ; `[suppose]` le reste.
- **Lu** : `docs/backlog/backlog.md` (criteres communs `QC1` a `QC9` et question routee, lignes 24061-24078 ; stories `-01` a `-12`, lignes 24080-24212) ; `reponse-nadia-palier-5-qlm-items-et-a229.md` (sections 2 et 3) ; `arbitrage-nadia-palier-5-qlm-9-lots.md` ; `confirmation-archunit-palier-4.md` ; les tests et paquets cites ci-dessous. `DEC-0120` A.237 a A.255 n'a pas ete relu ligne a ligne : ce que j'en dis vient des stories qui le citent.
- **Ce que ce fichier ne fait pas** : il n'ecrit ni code, ni test, ni regle, ni `ADR`, ni story. Il ne prononce aucun Done. Il ne decide rien de pedagogique : ce qui en releve est route en section 5.

## 0. Verdict

| Code | Question | Verdict |
|---|---|---|
| `T1` | Forme de la garde d'`AIM-CE1-2` | Une phrase est refusee quand elle porte **a la fois** une forme d'*attirer* et une **designation generale du metal** (les quatre de `nadia-prof`). Pas de liste de mots de quantite, pas d'exemption des questions. Les 7 sondes donnent le verdict attendu `[calcul]` (section 1) |
| `T2` | Un paquet sans proposition numerique exige-t-il une entree dans `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` ? | **Oui, toujours, et des le premier commit du paquet** : ce n'est pas un constat a faire au premier `verify` (section 2) |
| `T3` | Neuf paquets dans un palier | **Faisable en l'etat, sans `ADR`**, a deux conditions : fusions serialisees (trois fichiers partages) et **au moins 11 items par paquet** a dix manches, contre 10 dans `QC5` (section 3) |
| `T4` | Regle ArchUnit ou test rendu rouge | **Aucune regle ArchUnit.** Deux tests a amender a chaque lot, cinq tests qui rougissent sur un contenu non conforme (section 4) |

## 1. `T1` : la forme de la garde d'`AIM-CE1-2`

### 1.1 Ce qu'elle verifie

Champs lus : `enonce`, `indice` et, si l'item en porte une, `explication` (cle facultative d'`ADR-0024` `[suppose : nom de cle lu dans les tests, pas dans le chargeur]`). Chaque champ est coupe en phrases apres `.`, `?` ou `!`. **Une phrase est refusee si elle porte les deux motifs suivants.**

Attraction, toute forme du verbe (*attire*, *attirent*, *attirés*, *attirer*, *l'attire*) :

```
(?<!\p{L})attir\p{L}*
```

Designation generale du metal, les quatre formes de `nadia-prof` (section 3 de sa reponse : *le metal, les metaux, ce qui est en metal, les objets metalliques*), et rien d'autre :

```
(?<!\p{L})(le métal|métaux|métalliques|ce qui est (en métal|métallique)|(les|des) \p{L}+ en métal)(?!\p{L})
```

Drapeaux : `CASE_INSENSITIVE | UNICODE_CASE | UNICODE_CHARACTER_CLASS`, ceux de `PaquetCe1EmcInteretCommunTest` `[mesure, lignes 91-92]`. Les formes sans accent ne sont pas ecrites : *metal* et *metaux* sont deja refuses par `AucunMotServiNeSEcarteDeSonAccentTest`, dont le perimetre derive les paquets du repertoire `[mesure, Javadoc lignes 48-54]` `[suppose : *métal* et *métaux* sont dans son lexique]`. C'est le motif qu'ecrit le precedent `[mesure, PaquetCe1EmcInteretCommunTest:109-111]`.

### 1.2 Les sondes de `nadia-prof`, appliquees a la main `[calcul]`

| Sonde | Attraction | Designation generale | Verdict | Attendu |
|---|---|---|---|---|
| *L'aimant attire les métaux.* | *attire* | *métaux* | refusee | refusee |
| *Un aimant attire tout ce qui est en métal.* | *attire* | *ce qui est en métal* | refusee | refusee |
| *Les objets en métal sont attirés par l'aimant.* | *attirés* | *Les objets en métal* | refusee | refusee |
| *L'aimant attire-t-il le papier d'aluminium ?* | *attire* | aucune | passe | passe |
| *Le clou est en fer. L'aimant l'attire-t-il ?* | seconde phrase | aucune | passe | passe |
| *Quel objet l'aimant attire-t-il ?* | *attire* | aucune | passe | passe |
| *Ce trombone est en métal : l'aimant l'attire-t-il ?* | *attire* | aucune : *est en métal* n'est precede ni de *ce qui* ni d'un determinant pluriel | passe | passe |

**Le piege du trombone passe par la forme, pas par une exemption.** Une phrase interrogative n'est pas exemptee : *"Pourquoi l'aimant attire-t-il les métaux ?"* presuppose le fait faux, et elle est refusee.

### 1.3 Ce qu'elle interdirait reellement

Sur du materiau reel, les deux voisins que `nadia-prof` relit pour `-06` `[calcul]` : `cp-qlm-matiere` ecrit *métal* ou *le métal* dix fois dans ses champs lus, et aucune forme d'*attirer* : 0 refus. `cm1-sciences-melanges` ecrit *"Ce moyen attire un seul des deux produits du mélange, jamais le sable."* (ligne 95) : attraction, aucune designation generale, passe. **0 refus sur les deux paquets reels ; la garde mord sur les trois phrases fautives et sur rien d'ecrit aujourd'hui.**

Faux rouges connus, visibles et non muets : *"L'aimant n'attire pas tous les métaux."* (phrase juste, refusee : voir `Q1`) ; *"Lequel des objets en métal l'aimant attire-t-il ?"*. Faux verts connus, a `nadia-prof` (`QC9`) : une paraphrase sans le verbe (*colle*, *s'accroche*, *attrape*) ; une liste de metaux nommes presentee comme attiree (*"le fer, le cuivre et l'or"*), qui est une faute d'exactitude, pas une generalisation ; une bonne reponse *"les métaux"* a l'enonce *"Qu'attire un aimant ?"*, ou l'affirmation est coupee entre l'enonce et la proposition (voir `Q2`).

### 1.4 Ou elle vit

Dans le test du paquet, `PaquetCe1Qlm<Nom>Test` (`QC4`), `adapters/adapter-content/src/test/java/com/recreo/adapter/content/`, sur le modele de `PaquetCe1EmcInteretCommunTest` : un verificateur pur sur une chaine, applique aux champs reels du paquet, et mis a l'epreuve sur **les sept phrases de `nadia-prof` ecrites en litteraux**, trois rouges et quatre vertes. Pas de fixture YAML pour cette clause : les sondes sont deja des phrases, et elles sont de la main de `nadia-prof`, pas de l'auteur de la garde. Ce n'est pas une regle ArchUnit (l'objet est du texte YAML, pas du bytecode) et ce n'est pas une decision d'architecture : **aucun `ADR`**.

## 2. `T2` : la question de `camille-po` sur le rang de la bonne reponse

**Oui.** Le mecanisme `[mesure]` :

1. Le perimetre est le **repertoire** `src/main/resources/contenu`, tout fichier `.yaml`, branche au catalogue ou non (`paquets()`, lignes 1393-1408).
2. Un paquet dont **aucun** item ne porte trois propositions numeriques classe 0 item ; s'il n'est pas declare dans `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`, `aucunPaquetNEstEcarteEnSilenceDuCalculDeRang` est rouge (lignes 1745-1800, `ADR-0051`).
3. Le motif de l'entree ecrit ses totaux, et `chaqueCompteEcritDansUnMotifVautLeCompteMesureSurLePaquet` exige que tout *"N items"* et *"N propositions"* ecrit vaille le compte du paquet (lignes 1835-1876).

**Consequences.** Ce n'est pas un constat a faire au premier `verify`, c'est certain des que les propositions sont du texte, ce que les neuf stories demandent. L'entree s'ecrit **dans le commit qui depose le YAML**, premier jet compris (`P0`), sinon ce commit est rouge. **Toute correction qui ajoute ou retire un item** apres une lecture avant `verify` exige de recompter le motif dans le meme commit ; l'oubli est rouge, pas muet. Un paquet dont certains items portent trois nombres (des ages en *"X ans"* sur `-12`, par exemple) sort de ce cas : pas d'entree ici, le seuil de majorite s'applique, et `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` exige une declaration dans `PAQUETS_A_ITEMS_ECARTES` `[mesure, Javadoc lignes 64-68]`. La phrase du backlog (*"le developpeur le constate a son premier verify"*) est a reformuler par `camille-po` : *"le developpeur ecrit l'entree dans le commit du premier jet"*.

## 3. `T3` : neuf paquets dans un palier

### 3.1 Fichiers touches par paquet

Propres au lot, sans conflit possible : le paquet `contenu/<P>.yaml` ; son test `PaquetCe1Qlm<Nom>Test.java` ; ses jeux fautifs sous `src/test/resources/contreexemple/PaquetCe1Qlm<Nom>Test/` (precedent : quatre a sept fichiers par test EMC CE1 `[mesure, Glob]`).

Partages, tous **en ajout seul** :

| Fichier | Ce que chaque lot y ajoute | Moment | Lecture |
|---|---|---|---|
| `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java` | une entree de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` | **commit du premier jet** (section 2) | `[mesure]` la table se ferme par `));` sur sa derniere entree (ligne 1386) : ajouter en fin modifie la ligne de la precedente, donc **deux branches paralleles entrent toujours en conflit** |
| `catalogue.yaml` | une entree de jeu | branchement, apres le Done (`QC3`) | liste unique `jeux:` `[mesure]` ; deux ajouts en fin de fichier entrent en conflit |
| `TexteAccentueExposeParLePortEntrantTest.java` (`bootstrap`) | les chaines neuves exposees : identifiant, titre, consigne, competence | branchement | inventaire en egalite (ligne 88) ; precedent : +66 lignes pour six lots au palier 4 `[mesure, confirmation-archunit-palier-4.md:64]` |

`MATRICE-couverture.md` bouge au verdict et n'est pas ecrit par le developpeur.

### 3.2 La borne de 11 items

`ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` (`ADR-0023`) exige, pour toute entree du catalogue, **strictement plus** d'items que de manches (lignes 77-95, `isGreaterThan`) `[mesure]`. A dix manches (`DEC-0121`, asserte par le precedent), **un paquet de 10 items est rouge au branchement**. `QC5` (*"entre 10 et 20"*) et `FER-2` lu comme *"au moins 10"* donnent donc un plancher que la CI refuse : **11 items**, ou une derogation de manches nommee dans `ChaqueJeuDuCatalogueDureDixManchesSaufDerogationNommeeTest`. Choisir entre les deux n'est pas a moi (`Q3`). Le cas le plus serre de `nadia-prof`, `-04` a 9 items, tient dans 11.

### 3.3 Ce que cela impose a l'ordre et au parallelisme

L'ordre de `nadia-prof` (`P0` puis quatre paires) et `K1` (une instance, un developpeur) **serialisent deja le travail**. Ce que j'ajoute : **les fusions sont serialisees**. Chaque branche de lot part du sommet de `round-two` qui porte la fusion precedente, ou s'y rebase avant de fusionner. Une paire developpee sur deux branches en meme temps produit un conflit par paire sur la table du rang, et un par paire au branchement. Ces conflits se resolvent en gardant les deux entrees, et la clause des comptes reverifie le resultat ; ils coutent un `verify` de plus, pas une garde. Toute autre instance qui depose un paquet textuel dans le meme palier touche la meme table `[suppose : aucune n'est prevue, K2]`.

Ce que je ne chiffre pas, faute de mesure : la duree d'un `verify`, le nombre de reprises. Le seul compte lu est celui du palier 4, 3232 tests au `verify` complet (`confirmation-archunit-palier-4.md:26`, repris de `sofia-sm`) ; il ne dit rien de la duree. `QC8` demande un `verify` complet par lot : neuf au minimum, reprises non comptees.

### 3.4 Les conditions transverses, une fois pour neuf

`QC6` laisse la forme des gardes transverses a `theo-archi`. Recopier `TRV-CE1-1`, `-2`, `-5` et la liste des quinze mots de `QMT-CE1-1` dans neuf tests de paquet ferait neuf copies qui divergeront : c'est la dette 3 de `main`, sur des tests. **Forme retenue** : un seul test, ecrit dans le lot `-01`, dont la population est le **lot de matiere** (matiere et niveau lus dans le descripteur, mecanisme d'`ADR-0079`) et non une liste de fichiers. Les huit lots suivants y entrent **sans toucher le fichier**, ce qui retire un fichier partage au lieu d'en ajouter un. Restent dans chaque test de paquet les conditions de sa ligne et `NRC-CE1-1`, dont les voisins different d'un paquet a l'autre. L'usage est constate : neuf paquets, les memes conditions, une meme instruction. Ce qui me ferait changer d'avis : une condition transverse dont `nadia-prof` dirait qu'elle se juge paquet par paquet. Si la story reportee `S57-GARDE-CE1-PROPOSITIONS-ET-SUPPORTS` (`DA1`) est engagee plus tard, elle pourra absorber ce test.

## 4. `T4` : regles ArchUnit et tests touches

**Regles ArchUnit : aucune rouge, aucune a amender.** Les neuf stories demandent le bloc commun, aucune cle de schema ni mecanique neuve, donc aucune classe de production. Precedent mesure : au palier 4, six lots de paquets n'ont touche aucun fichier de regle ni aucun arbre de contre-exemple d'ArchUnit, et le compte (41 declarations, 38 `@ArchTest`) n'a pas bouge (`confirmation-archunit-palier-4.md:17` et `:55`). Une story qui demanderait une cle neuve sortirait de cet avis et demanderait un `ADR`.

| Test | Effet des neuf paquets | Quand |
|---|---|---|
| `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | **a amender** a chaque lot (section 2) ; rouge si l'entree manque ou si un compte de motif est perime | premier jet, puis chaque correction qui change le nombre d'items |
| `TexteAccentueExposeParLePortEntrantTest` | **a amender** a chaque branchement | apres le Done |
| `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` | rouge sur un paquet de 10 items a dix manches (section 3.2) | branchement |
| `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` | rouge si le descripteur n'ecrit pas exactement `"Questionner le monde"`, graphie de `cp-qlm-matiere` (ligne 5) `[mesure]` | des le premier jet |
| `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` | rouge si un test ou un commentaire ecrit un identifiant de condition absent du registre | des le premier jet |
| `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest` | rouge sur un enonce trop long (`LON-CE1-1`, `LON-CE1-2`) `[suppose : perimetre derive du repertoire]` | des le premier jet |

`LesGardeFousSontMisALEpreuveTest` recense les gardes qui lisent `src/main/` : un test de paquet construit comme le precedent (chemin litteral et repertoire de contre-exemple) y satisfait `[suppose]`.

## 5. Ce qui reste a trancher

| Code | Question | A qui |
|---|---|---|
| `Q1` | *"L'aimant n'attire pas tous les métaux."* est juste et refusee par la forme de `T1`. Doit-elle passer ? Si oui, elle devient une sonde verte et la forme gagne une clause de negation | `nadia-prof` |
| `Q2` | Une bonne reponse qui est une designation generale du metal (*"les métaux"*) doit-elle etre refusee ? Sa condition nomme enonce, indice et explication, pas les propositions | `nadia-prof`, consigne par `camille-po` |
| `Q3` | Plancher de `QC5` : 11 items, ou 10 items avec une derogation de manches nommee | `camille-po`, avec `nadia-prof` si la derogation touche une condition |
| `Q4` | Reformuler la question routee du backlog (ligne 24078) selon la section 2 | `camille-po` |
| `Q5` | Fusions serialisees dans `planning.md` (section 3.3) | `sofia-sm` |
| `Q6` | Non traites ici, faute de demande : la forme de `DVP-CE1-2` (`-01`), de *pile* et de `DEL-CE1-2` (`-08`), de `GEN-CE1-1` (`-12`), que les stories m'adressent aussi | `theo-archi`, sur demande de `sofia-sm` |

**Signe** : `theo-archi`, s57, palier 5, 2026-10-25.
