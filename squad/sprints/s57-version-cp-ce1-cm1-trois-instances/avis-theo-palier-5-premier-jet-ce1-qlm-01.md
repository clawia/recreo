# s57 - palier 5 : avis de theo-archi sur le premier jet de S57-BR-QLM-CE1-01 (garde de DVP-CE1-2, comptes de TRV-CE1-2 et TRV-CE1-5, garde de NRC-CE1-1)

- **Auteur** : `theo-archi`. Date : 2026-10-26, 10:51.
- **Arbre** : worktree `s57-p5-theo-p0`, base `77fc8f8e` (`git rev-parse`), qui porte le premier jet de `marc-dev` (`7c922786`).
- **Mesure** : `bash outils/mvn-jdk21.sh -B -q -pl adapters/adapter-content -am test -Dtest=PaquetCe1QlmEtapesDeDeveloppementTest,ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest -Dsurefire.failIfNoSpecifiedTests=false`. Perimetre : ces deux classes seules, pas le depot. JDK : `21.0.10`, propriete `java.version` du rapport XML de Surefire. Resultat lu dans `target/surefire-reports` : **15 tests, 0 echec** (transverse) et **26 tests, 0 echec** (paquet). Ce n'est pas une porte technique : aucun job de `ci.yml` n'a ete lance.
- **Lu** : mon avis `avis-theo-palier-5-neuf-lots-qlm-ce1.md` (3.3, 3.4, `Q6`) ; `docs/backlog/backlog.md` lignes 24055-24093 (criteres communs, story `-01`) ; les deux tests ci-dessus, `PaquetLu.java`, le paquet `ce1-qlm-etapes-de-developpement.yaml`, son jeu fautif `etapes-fautives.yaml` ; `ADR-0079` (contexte) ; le precedent `PaquetCe1EmcInteretCommunTest` lignes 617-643.
- **Ce que ce fichier ne fait pas** : il n'ecrit aucun test et ne prononce aucun Done. Les changements qu'il demande sont a `marc-dev`. Ce qui releve de la pedagogie est route en section 4.

## 0. Verdict

| Code | Question | Verdict |
|---|---|---|
| `V1` | Garde de `DVP-CE1-2` | **Validee telle qu'ecrite.** Un changement a cote : le format de declaration des familles doit passer dans le test transverse (section 1.3) |
| `V2` | `TRV-CE1-2` et `TRV-CE1-5` comptes par paquet | **Oui, c'est ce que voulait 3.4** : 3.4 fixait la population, pas l'unite de compte. Un doublon de `TRV-CE1-5` dans le test du paquet est a retirer (section 2) |
| `V3` | `NRC-CE1-1` gardee sur l'egalite des enonces avec deux voisins | **Insuffisant.** Sur `-01`, elle ne refuse rien que `DVP-CE1-1` ne refuse deja. Sa population omet 13 des 15 paquets de CP de questionner le monde. Elle passe dans le test transverse, et elle compare aussi les jeux de propositions (section 3) |

## 1. `V1` : la garde de `DVP-CE1-2`

### 1.1 Ce qu'elle refuse

`familleDuDistracteur` (`PaquetCe1QlmEtapesDeDeveloppementTest.java:316-336`) refuse un distracteur de la meme espece dont le rang est a moins de 2 de celui de la bonne reponse (`:325-328`). Pour une question *juste apres X*, la bonne reponse est au rang de X plus 1. Les deux rangs a distance 1 sont X lui-meme, deja refuse par `:321-324`, et **l'etape qui suit la bonne reponse dans le sens demande**. C'est exactement le cas de `nadia-prof` : apres le tetard, *grenouille* est a distance 1 de *jeune grenouille*. Le jeu fautif le pose (`etapes-fautives.yaml:33-40`) et le test le refuse sur le message attendu (`:553-561`).

### 1.2 Pourquoi je la valide

- **C'est la plus etroite qui generalise l'exemple.** Plus etroit, il ne reste que la liste litterale des paires interdites, une forme enumeree qui echoue par omission sur la paire suivante. Plus large, une distance de 3 refuserait des distracteurs *hors de sa place* que la condition autorise en toutes lettres.
- **La Javadoc la dit plus stricte que la condition (`:52-55`). Ce n'est pas le cas.** La condition interdit *deux etapes defendables* sans borner la distance. Sur une liste ordonnee, la seule etape defendable pour *juste apres* ou *juste avant* est celle qui est a distance 1. La phrase *"reste a confirmer"* peut tomber. La remplacer par *"forme confirmee, avis-theo-palier-5-premier-jet-ce1-qlm-01.md, V1"* est a `marc-dev`.
- **Elle a un prix, et le paquet le paie deja.** Pour la poule, qui a trois etapes, aucun distracteur de famille `a` n'est possible sur une question voisine : un tel distracteur est soit l'etape nommee, soit a distance 1. Le paquet sert la poule en `b, b` (`ce1-qlm-etapes-de-developpement.yaml:88-94`). C'est un effet de la liste fermee, pas un defaut de la garde.

### 1.3 Effet sur les huit lots suivants

- **La forme par distance ne se propage pas.** Elle suppose une liste fermee ordonnee, ce que `-01` a seul aujourd'hui. `GEN-CE1-1` (`-12`) ordonne des generations. Si sa story pose un jour une tache *juste avant/apres*, cette garde est le precedent a recopier. Aucune extraction en commun avant ce deuxieme usage.
- **Ce qui doit se propager est le format de declaration des familles, et c'est un changement.** Le test transverse accepte n'importe quel texte apres `TRV-CE1-2 : ` (`ChaquePaquet...Test.java:68` et `:152-155`). Le format reel et la presence de chaque lettre dans l'en-tete ne sont verifies que dans le test de `-01` (`PaquetCe1Qlm...Test.java:116`, `:118`, `:263-264`). Sur les huit autres lots, *"TRV-CE1-2 : x"* passe, et chaque lot inventera son format. `marc-dev` deplace dans le test transverse le motif `^TRV-CE1-2 : ([a-z]), ([a-z])$` et la verification "chaque lettre declaree a sa ligne `# famille X : ...` dans l'en-tete", avec un jeu fautif qui mord chacun des deux. Le classement calcule (`declarees` egal a `calculees`, `:297-300`) reste dans le test du paquet : il depend de la liste propre a la ligne.

## 2. `V2` : comptes de `TRV-CE1-2` et `TRV-CE1-5`

### 2.1 Ce que 3.4 voulait

3.4 fixait **la population** : le lot de matiere, derive des descripteurs, et non une liste de fichiers. Le test le fait (`ChaquePaquet...Test.java:89-109`). 3.4 ne changeait pas l'unite de compte d'une condition. Cette unite est celle de la lettre : les deux sondes de `QC6` (backlog, lignes 24070-24071) disent *"un paquet"*, et `TRV-CE1-5` dit *"pas le meme sur tout le paquet"*.

### 2.2 Pourquoi le compte par paquet n'est pas un vert a tort au sens d'`ADR-0079`

`ADR-0079` ferme un vert a tort : un compte par fichier, vert, alors que le lot depasse. Ce cas est impossible sur ces deux comptes, et le raisonnement de `marc-dev` (`ChaquePaquet...Test.java:43-46`) est juste :

- `TRV-CE1-2` : si chaque paquet a au plus la moitie de ses items ou la bonne reponse est strictement la plus longue, la somme des items concernes est au plus la moitie de la somme des items, donc le lot tient aussi.
- `TRV-CE1-5` : si chaque paquet porte au moins deux indices distincts, le lot en porte au moins deux.

Le seul ecart possible est un **faux rouge visible** : un paquet au-dessus de la moitie dans un lot en dessous. Il se tranche alors chez `nadia-prof`, pas en assouplissant la garde. Sur `-01`, 5 items sur 15 ont la bonne reponse strictement la plus longue. Methode : longueur en caracteres de `bonneReponse` comparee a chaque distracteur, calculee par un script sur les 15 items du fichier, ce que fait `:165-166`.

### 2.3 La forme du comptage, pour un test unique qui sert neuf lots

1. La population est derivee une fois (`paquetsDuLot`). Aucune liste de fichiers.
2. Une condition par item se verifie item par item.
3. Une condition de compte se calcule **dans l'unite que sa lettre nomme**, et cette unite est ecrite dans la Javadoc du verificateur. Pour `TRV-CE1-2` et `TRV-CE1-5`, c'est le paquet.
4. Une assertion au niveau du lot ne s'ajoute **que si le verdict par paquet n'implique pas celui du lot** : un plafond absolu (*"au plus N items du lot"*), un plancher ou une couverture (*"au moins un item du lot"*, *"au moins k pour cent"*). Le compte se fait alors sur la reunion des items de `paquetsDuLot`, comme dans `UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest`. Pour les deux comptes en cause, une assertion de lot ne pourrait jamais rougir : elle serait decorative. Elle n'est pas a ecrire.

### 2.4 Ce qui doit changer : le doublon de `TRV-CE1-5`

`trv5LesIndicesDuPaquetSontTousDistincts` (`PaquetCe1Qlm...Test.java:499-504`) garde le meme identifiant que `ChaquePaquet...Test.java:201-205` avec un seuil different : *tous distincts* d'un cote, *pas tous identiques* de l'autre. C'est la divergence que 3.4 voulait empecher. `marc-dev` le retire du test du paquet. Si *"tous distincts"* doit valoir, c'est une condition neuve : `nadia-prof` la dit, et elle entre dans le test transverse pour les neuf lots.

## 3. `V3` : `NRC-CE1-1`

### 3.1 Ce que la garde actuelle refuse reellement sur `-01`

`verifierQueRienNEstReprisDUnPaquetVoisin` (`PaquetCe1Qlm...Test.java:398-412`) compare les enonces normalises de `-01` a ceux de `cp-qlm-cycle-de-vie` et de `cm1-sciences-etapes-developpement` (`:70-73`). Aucun enonce de ces deux paquets n'a la forme *"Dans la vie de..."* (`grep -c` : 0 et 0). Or `analyser` (`:181-193`) refuse tout enonce de `-01` qui n'a pas l'une des deux formes. **Un enonce copie d'un de ces voisins est donc refuse par `dvp1...` avant que `nrc1...` ait quoi que ce soit a dire.** Sur ce paquet, la garde ne refuse rien de plus. Sur un lot sans forme imposee, elle ne refuse qu'une copie a la lettre, et le moindre mot change la contourne.

### 3.2 Les deux defauts

- **La population est enumeree, et elle omet.** La lettre dit *"un paquet de CP"*. Le depot compte 15 paquets de CP de questionner le monde et 62 paquets de CP en tout. Methode : fichiers de `src/main/resources/contenu` avec `niveau: "CP"` et au moins un `- enonce:`, lus par un script, matiere comparee a la chaine `"Questionner le monde"`. La garde en lit 2, dont un de CM1. `cp-qlm-vivant-non-vivant` sert *"la fleur"* en bonne reponse (ligne 122) et n'est pas lu. `cm1-sciences-etapes-developpement` releve de `CYC-1`, qui reste a `nadia-prof` (`QC9`), et non de `NRC-CE1-1`.
- **Une reprise reformulee passe.** La lettre dit *"meme question, ou meme bonne reponse a la meme tache"*. La garde ne regarde que l'enonce.

### 3.3 Ce qui doit changer, et ce qu'il ne faut pas faire

`marc-dev` deplace la partie textuelle de `NRC-CE1-1` dans le test transverse. **Ceci amende la derniere phrase de ma section 3.4, qui la laissait dans chaque test de paquet** au motif que les voisins different. Une fois la population derivee, il n'y a plus de voisin a nommer.

- **Population comparee** : tout paquet du repertoire dont le descripteur declare `niveau: "CP"`, derive comme `paquetsDuLot`.
- **Refus** : un item de questionner le monde CE1 dont l'enonce normalise est celui d'un item de CP, **ou** dont l'ensemble normalise des trois propositions est celui d'un item de CP. Un item de CP a deux propositions (*oui*, *non*) ne peut pas egaler un ensemble de trois : la clause ne fait pas de faux rouge sur les items binaires.
- **Mesure sur `-01`**, par le meme script sur les 62 paquets de CP (716 enonces, 533 bonnes reponses, 1175 propositions distinctes apres normalisation) : 0 enonce egal, 0 bonne reponse egale. La clause reste verte sur le paquet reel.
- **A ne pas reprendre** : le precedent EMC (`PaquetCe1EmcInteretCommunTest.java:617-643`) refuse toute proposition egale a une proposition voisine. Mesure sur `-01` : il refuserait le distracteur *"la fleur"* (`ce1-qlm-etapes-de-developpement.yaml:128`), parce que `cp-qlm-vivant-non-vivant` l'ecrit. C'est un faux rouge par inclusion : une etape de la plante n'est pas un item repris.
- **Jeux fautifs**, dans le meme commit : un item dont l'enonce est celui d'un paquet de CP **non nomme aujourd'hui**, par exemple `cp-qlm-vivant-non-vivant`, pour prouver que la derivation mord la ou la liste omettait ; un item reformule qui garde les trois propositions d'un item de CP ; un item qui partage une seule proposition avec un item de CP, et qui passe.
- `PAQUETS_VOISINS`, `verifierQueRienNEstReprisDUnPaquetVoisin` et `unEnonceCopieMotPourMotDUnPaquetVoisinEstRefuse` sortent du test de `-01`. La phrase de la Javadoc transverse *"ni NRC-CE1-1"* (`ChaquePaquet...Test.java:39`) devient *"NRC-CE1-1, partie textuelle seule"*. Le sujet de l'item reste a `nadia-prof` (`QC9`).

## 4. Actions et routage

| Code | Quoi | Qui |
|---|---|---|
| `A1` | Javadoc de `DVP-CE1-2` : *"plus stricte... reste a confirmer"* devient *"forme confirmee"* (`V1`) | `marc-dev` |
| `A2` | Format `TRV-CE1-2 : x, y` et lettres de l'en-tete verifies dans le test transverse, avec un jeu fautif par clause (`V1`, 1.3) | `marc-dev` |
| `A3` | Retirer `trv5LesIndicesDuPaquetSontTousDistincts` du test du paquet (`V2`, 2.4) | `marc-dev` |
| `A4` | `NRC-CE1-1` textuelle dans le test transverse : population CP derivee, egalite d'enonce ou de jeu de propositions, trois jeux fautifs (`V3`, 3.3) | `marc-dev` |
| `A5` | La famille `c` (*"les trois memes etapes, dans un autre ordre"*, `ce1-qlm-etapes-de-developpement.yaml:41`) n'est pas l'une des deux familles nommees par `DVP-CE1-2`. Est-elle une *"etape hors de sa place"* ? Question pedagogique, je ne la tranche pas | `nadia-prof`, par `sofia-sm` |

`A2` a `A4` touchent un test que les huit lots suivants lisent : ils sont a fusionner **avant** le premier jet de `-02`, sinon `-02` se calera sur le format d'aujourd'hui. Aucune regle ArchUnit n'est touchee, et aucun `ADR` n'est demande : ce sont des gardes de contenu sur du YAML, dans la forme d'`ADR-0013` et d'`ADR-0079`.

**Signe** : `theo-archi`, s57, palier 5, 2026-10-26.
