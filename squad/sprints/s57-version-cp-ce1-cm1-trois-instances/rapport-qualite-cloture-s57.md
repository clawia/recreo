# [Sprint s57] Rapport qualite de `yanis-qa` - cloture du sprint

- **Auteur** : `yanis-qa`. **Date** : 2026-10-29. **Arbre mesure** : `round-two` a `a3e05f13`, worktree `s57-cl-yanis-s57`, propre avant ce fichier (`git status --short` vide). Cloture decidee par l'utilisatrice le 2026-10-29 (sprint a paliers sans timebox, `planning.md` D3) ; la decision est relayee par l'agent lanceur, je ne l'ai pas lue de sa main.
- **Ce que ce fichier ne fait pas** : il ne prononce ni Done technique ni Done pedagogique, ne fusionne rien, ne corrige rien, ne tranche aucun obstacle. Le Done technique est celui de la CI sur le commit pousse, **que personne n'a lue** et que je n'ai pas lue. Le Done pedagogique est a `nadia-prof`.
- **Methode des chiffres** : chaque chiffre porte son perimetre dans la ligne ou le paragraphe ; un chiffre releve dans un autre fichier est dit relaye et cite sa source.

## Resume

| Code | Constat | Etat |
|---|---|---|
| F1 | `build`, `verify` complet sur `a3e05f13` : 3644 tests, 0 echec, 0 erreur, 0 ignore, JDK 21.0.10 | verte, constatee par moi |
| F2 | `chemins`, `prefixes`, `outils` : lances par moi sur `a3e05f13`, sorties 0 | vertes, constatees par moi |
| F3 | Etape "Package du fat jar" et "Verifier la presence du build Maven" : non lancees par moi | non jugees |
| F4 | CI GitHub : declenchee par le push de `a3e05f13`, **lue par personne** | inconnue |
| F5 | Tests du sprint : de 2498 (premier total que je lis) a 3644, +1146 [calcul] ; palier 5 : +412 | section 3 |
| F6 | Deux ecarts de comptage deja ecrits, non expliques : 3206 contre 3188 `@Test` (constant a 18 sur trois commits que j'ai mesures), et l'ecart constant de 26 contre 44 | section 3.3 |
| F7 | Le code n'est plus celui de `eaa3ef65` : 5 fichiers de code differents entre `eaa3ef65` et `d9313f9e` (ligne `-12`) ; `cloture-palier-5.md` ecrit l'inverse | section 1.3 |
| F8 | Aucune mutation lancee par moi sur les gardes du palier 5 ; aucun rapport de mutation du palier 5 dans le dossier | section 4 |
| F9 | Obstacles de qualite ouverts, routes a `sofia-sm` | section 5 |

## 1. Porte technique a la cloture

### 1.1 Jobs de `ci.yml`, lances sur `a3e05f13`

**Perimetre.** Arbre de `a3e05f13`, sans le present fichier. `TMPDIR` en forme POSIX (`mktemp -d`). JDK : celui que la machine virtuelle des tests rapporte, `java.version` lu dans les **338** fichiers `TEST-*.xml` (Surefire et Failsafe), une seule valeur, **21.0.10** ; la premiere ligne de l'enveloppe dit `Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10`.

| Job | Ce que j'ai lance | Sortie |
|---|---|---|
| `build`, "Verify" | `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, journal dans un fichier temporaire, code de sortie ecrit dans ce journal (`EXIT=0`) | `BUILD SUCCESS`, 7 min 47 s, six modules `SUCCESS` |
| `build`, "Package du fat jar" | **non lancee** (`mvn -B -pl bootstrap -am package`) | non jugee |
| `build`, "Verifier la presence du build Maven" | **non lancee** | non jugee |
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-garde-fou-accent-test.sh` ; `commit-atteignable.sh 077fae0b` ; `git log --no-merges --format=%s 077fae0b..HEAD` (175 sujets) dans `verifier-prefixes.sh` ; `verifier-garde-fou-accent.sh "077fae0b..HEAD"` | six sorties 0 ; *"Tous les commits portent un prefixe d'agent connu."* ; *"0 commit(s) le touchent dans la plage, tous [THEO]."* |
| `chemins` | `verifier-chemins-test.sh` ; `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh'` (3300 chemins) dans `verifier-chemins.sh`, entree standard reelle | sorties 0 et 0 ; *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | balayage de `ci.yml` rejoue a la main, interpreteur `python` : `outils/*-test.sh` 6, `squad/outils/*/*-test.sh` 2, `outils/*-test.py` 5 | 13 juges, sortie 0, aucun motif vide |

- `github.event.before` d'un push n'est pas connu de moi : `077fae0b` tient lieu de base de plage, comme dans `cloture-palier-5.md` section 10. Le compte de sujets (175) n'est pas celui de `cloture-palier-5.md` (160 a `fa137660`), la plage s'est allongee de commits poses depuis.
- `outils` : `python3` est un executable du Store qui echoue sur ce poste ; j'ai lance `python`, alors que `ci.yml` choisit `python3` des qu'il existe. La branche `python3` de `ci.yml` n'est pas celle que j'ai exercee.
- Le total de chemins de `chemins` (3300) est une valeur de mon arbre ; je ne le rapproche pas des 3293 de `cloture-palier-5.md` section 10, mesures a un autre commit.

### 1.2 Total du `verify` complet, par module

Somme des six lignes `Tests run` du reacteur, relues dans mon journal : 120 (domain) + 31 (application) + 523 (adapter-web) + 2569 (adapter-content) + 395 (bootstrap, Surefire) + 6 (bootstrap, Failsafe) = **3644** [calcul]. Recompte a part par l'attribut `tests` des 338 `TEST-*.xml` des modules : **3644**, `failures`, `errors` et `skipped` a 0 sur les 338. **Egal au chiffre de l'agent lanceur** (mesure a `d9313f9e`), ligne a ligne.

### 1.3 L'agent lanceur et le code entre `d9313f9e` et `a3e05f13`

- `git diff --quiet d9313f9e HEAD -- adapters bootstrap domain application outils .github pom.xml contenu-repris` : sortie **0**. `git diff --name-only d9313f9e HEAD` : quatre fichiers, `docs/backlog/backlog.md`, `DEC-0120`, `DEC-index.md`, `MATRICE-couverture.md`. Le verify de l'agent lanceur a `d9313f9e` et le mien a `a3e05f13` portent donc le meme code.
- **Ecart avec `cloture-palier-5.md`.** Cette cloture ecrit (section 1) que *"le code et le contenu servis de `HEAD` sont ceux de `eaa3ef65`"*. Entre `eaa3ef65` et `d9313f9e`, `git diff --stat eaa3ef65 HEAD -- adapters bootstrap domain application outils .github pom.xml` rend **5 fichiers, 60 ajouts, 31 retraits** : `catalogue.yaml` (+6, la ligne `-12`, 173 entrees `^  - id:` a `HEAD`), `ce1-qlm-generations.yaml`, `PaquetCe1QlmGenerationsTest.java`, `generations-fautives.yaml`, `TexteAccentueExposeParLePortEntrantTest.java`. Les verify releves a `eaa3ef65` et a `371425e4` ne couvrent donc pas ces cinq fichiers ; celui de `d9313f9e` (agent lanceur) et le mien les couvrent. Le total est le meme, 3644 : aucun `@Test` n'a ete ajoute (3618 a `eaa3ef65` et a `HEAD`, section 3.3).
- Ce fichier ne prononce pas le Done technique. Une mesure locale n'est pas la CI (`CLAUDE.md`, double Done).

### 1.4 CI GitHub

Le push de `a3e05f13` a declenche la CI. **Personne ne l'a lue** : ni l'agent lanceur, ni moi. Je ne la lis pas dans ce geste et je ne dis pas qu'elle est verte. Les fiches de `-03` et de `-10` conditionnent leur verdict a la CI complete a la cloture (`cloture-palier-5.md` section 3) ; la ligne `QLM-CE1-12` est passee a `couvert-valide` par `DEC-0120` A.292, **sur une mesure locale a `d9313f9e`, pas sur la CI GitHub** (sujet du commit `d2dd34ec`). Qui lit la CI, et quand, est a l'utilisatrice (`cloture-palier-5.md` section 6, question 1) : ouvert.

## 2. Recomptage par module

**Perimetre** : reacteur complet a `a3e05f13`, sans `-pl`, `bash outils/mvn-jdk21.sh -B clean verify`, mon journal. Comparaison avec le dernier total mesure a la fin de chaque palier (cloture de palier citee, **mesure de leur auteur, non rejouee par moi**).

| Module | Palier 4 (`cloture-palier-4.md` section 10.1, a `38a77bf9`) | Cloture s57 (mon `verify`) | Delta [calcul] |
|---|---|---|---|
| domain | 120 | 120 | 0 |
| application | 31 | 31 | 0 |
| adapter-web | 523 | 523 | 0 |
| adapter-content | 2171 | 2569 | +398 |
| bootstrap, Surefire | 381 | 395 | +14 |
| bootstrap, Failsafe | 6 | 6 | 0 |
| **Total** | **3232** | **3644** | **+412** |

`adapter-content` +398 = 392 (neuf lots `QLM-CE1` et le test des conditions transverses, section 3.2) + 6 (`PaquetCe1LireLHeureTest`) [calcul]. `bootstrap` +14 = `LesCadransMontrentCeQueLeursItemsAffirmentTest`. `adapter-web` : 0, ses deux fichiers de test touches par le palier n'ont pas gagne de `@Test`.

## 3. Comptage des tests par sprint

### 3.1 Du debut au palier 4

**Methode.** Chaque total est celui que la cloture de palier citee ecrit comme mesure de son auteur (`bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`) ; **je n'ai rejoue aucun de ces totaux**, sauf le dernier (section 1.2). Les deltas des paliers 1 a 4 sont repris tels qu'ecrits, la somme de leurs lignes est mon calcul.

| Palier | Total en fin de palier | Delta ecrit par la cloture | Chaine des fusions (cloture citee) |
|---|---|---|---|
| Point de depart lu | 2498 | - | `avis-faisabilite.md` (ligne 277) : `verify` complet, 120, 31, 486, 1484, 371, 6, base a `544159d9` ; `avis-theo-poste-8-art-cp-10.md` ligne 97, meme total. **Je ne sais pas si 2498 est le total d'ouverture de s57** : aucun fichier que j'ai lu ne l'ecrit comme tel |
| 1 | 2597 | +49 depuis 2548 (`c468c8dd`, mesure de mon `releve-yanis-palier-1.md`, section 1) ; +99 depuis 2498 [calcul] | 2520, 2541, 2542, 2548, puis cinq fusions de code non remesurees une a une, 2597 ; par module +6 adapter-web, +33 adapter-content, +10 bootstrap |
| 2 | 2669 | +72 | +2, +4, +37, +24, +5 (`cloture-palier-2.md` section 4) |
| 3 | 2855 | +186 | +170, +1, +8, +4, +2, +1 (`cloture-palier-3.md` section 4) |
| 4 | 3232 | +377 [calcul : 3232 - 2855] | 2895, 2980, 3020, 3077, 3151, 3158, 3232 (`cloture-palier-4.md` sections 1 et 2) |
| 5 | 3644 | +412 | section 3.2 |

- Palier 4 : les deltas de fusion sont, d'apres les totaux annonces, +40, +85, +40, +57, +74, +7, +74 = 377 [calcul]. **3151 et 3158 sont des deductions** (`cloture-palier-4.md` section 2 : *"Ces deux totaux sont des deductions, rien d'autre"*) ; les totaux de `LP2`, `LP4`, `LP1` et `LP3` sont ceux des messages de fusion, journal non relu par cette cloture-la.
- Palier 3 : l'ecart entre les chiffres annonces par plusieurs agents (2834, 2847) et le total est explique par `cloture-palier-3.md` section 4, pas par moi ; dans mon propre rapport de palier, 2834 n'etait pas le total du sommet.
- **Total cumule depuis 2498** : 3644 - 2498 = +1146 [calcul]. Il porte la reserve du point de depart ci-dessus.

### 3.2 Palier 5, par classe

**Methode.** `git grep -c '^\s*@Test'` sur chaque fichier cree depuis `f3b06989` (cloture du palier 4) a `HEAD`, `git diff --diff-filter=A` ; je ne lis que des `@Test`, aucun fichier `*Test.java` ne declare `@ParameterizedTest`, `@TestFactory` ni `@RepeatedTest` (`git grep -l`, vide).

| Classe nouvelle | Module | `@Test` |
|---|---|---|
| `ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest` | adapter-content | 18 |
| `PaquetCe1QlmEtapesDeDeveloppementTest` (`-01`) | adapter-content | 25 |
| `PaquetCe1QlmCeQueMangentLesAnimauxTest` (`-02`) | adapter-content | 24 |
| `PaquetCe1QlmBesoinsDeLaPlanteTest` (`-03`) | adapter-content | 27 |
| `PaquetCe1QlmFamillesDAlimentsTest` (`-04`) | adapter-content | 42 |
| `PaquetCe1QlmEauTroisEtatsTest` (`-05`) | adapter-content | 53 |
| `PaquetCe1QlmAimantTest` (`-06`) | adapter-content | 51 |
| `PaquetCe1QlmDangersElectriciteTest` (`-08`) | adapter-content | 54 |
| `PaquetCe1QlmPaysagesTest` (`-10`) | adapter-content | 58 |
| `PaquetCe1QlmGenerationsTest` (`-12`) | adapter-content | 40 |
| `PaquetCe1LireLHeureTest` (horloge) | adapter-content | 6 |
| `LesCadransMontrentCeQueLeursItemsAffirmentTest` (horloge) | bootstrap | 14 |
| **Total** | | **412** [calcul] |

Les 412 egalent le delta mesure de la section 2 (3232 vers 3644) et le delta de `@Test` par les sources (3206 vers 3618, section 3.3). Les trois fichiers de test modifies sans nouveau `@Test` (`git diff --numstat f3b06989 HEAD`) : `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (+50, -1), `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest` (+8, -1), `LIllustrationDeContenuResoutEtAucuneNeDortTest` (+8, -2).

Deltas par fusion, tels que `cloture-palier-5.md` les ecrit : 3232 au depart, **3624 mesure par `sofia-sm` a `1a810b79`** (+392), **3644** a `eaa3ef65` (+20, l'horloge : adapter-content +6, bootstrap +14). Mon calcul par classes : 18 + (25 + 24 + 27 + 42 + 53 + 51 + 54 + 58 + 40) = 392, puis 6 + 14 = 20 [calcul]. Les deux decoupages sont egaux. **Je n'ai pas de total mesure par fusion de lot** : la cloture n'en ecrit pas, et je n'en ai pas rejoue.

### 3.3 Ecarts non expliques deja ecrits

- **3206 contre 3188 `@Test` a `c11b18e1`.** `cloture-palier-5.md` section 2 : la cloture du palier 4 ecrit 3188, la commande de `sofia-sm` rend 3206, *"je n'explique pas la difference des deux commandes"*. **Je reproduis 3206** : `git grep -c '^\s*@Test' <commit> -- '*Test.java'`, somme des comptes par fichier, a `c11b18e1` (3206), `f3b06989` (3206), puis 2829 a `eb267ba7` et 2869 a `a3b6543e`, contre 2811 et 2851 dans `cloture-palier-4.md` section 2. **L'ecart avec la cloture du palier 4 est de 18 aux trois commits que j'ai mesures** (3206 - 3188, 2829 - 2811, 2869 - 2851). Je n'ai pas determine la commande de la cloture du palier 4, ni la cause. Je ne l'explique pas.
- **Ecart constant de 26** (3206 a 3232, 3598 a 3624, 3618 a 3644) entre mes `@Test` comptes et le total mesure : j'obtiens 3618 + 26 = 3644 a `HEAD`, comme `cloture-palier-5.md`. L'ecart de 44 de `cloture-palier-4.md` est egal a 26 + 18 [calcul]. **Je n'ai pas decompose l'ecart de 26**. C'est une coherence de comptage, pas une mesure de l'arbre.
- Mesure de total par fusion : 3151 et 3158 (palier 4) restent des deductions ; je ne les remesure pas.

## 4. Gardes ajoutes ou etendus pendant s57, et ce que personne ne garde

### 4.1 Palier 5, lu dans le code et dans les rapports

**Methode.** Liste de `git diff --name-status f3b06989 HEAD` sous `adapters`, `bootstrap`, `domain`, `application`, et lecture des rapports de `marc-dev` et de `lea-dev` du palier. **Je n'ai lance aucune mutation sur ces gardes pour cette cloture** ; les morsures citees sont celles des auteurs, **relayees**.

| Garde | Ou | Jeu fautif commite (`ADR-0013`, obligation 3) |
|---|---|---|
| Conditions transverses de `QLM-CE1` ecrites une fois pour les neuf lots : cles servies, nombre et distinction des propositions, famille de distracteurs, longueurs, quinze mots exclus, `TRV-CE1-1` (champs `texteSupport` et `texteAVoixHaute` refuses), `NRC-CE1-1` partie textuelle ; population derivee des descripteurs (`ADR-0079`) | `ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest` (18) | cinq YAML dans `adapters/adapter-content/src/test/resources/contreexemple/` (`cp/` 2, `depot/` 3) |
| Test de paquet par lot, un par paquet `ce1-qlm-*` (neuf) | les neuf classes `PaquetCe1Qlm...Test` | **jeu fautif commite par lot** d'apres les sujets de commit des lots (`jeu fautif` ou `jeux fautifs` dans les sujets de `a9b1480a`, `ebc274b1`, `7fe939a6`, `c8609344`, `c86148d1`, `e6694d47`, `7cc77996`, `de814b09`) ; je n'ai pas relu chaque jeu |
| Rang de la bonne reponse non predictible, etendue aux paquets du palier | `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (+50, -1) | non releve |
| Cadrans : angles egaux a l'heure affichee (grande aiguille 6 x minutes, petite 30 x heure + 0,5 x minutes), garde de paire (strategies `S1` a `S4`, au plus 6 items sur 12) | `LesCadransMontrentCeQueLeursItemsAffirmentTest` (bootstrap, 14), `PaquetCe1LireLHeureTest` (6) | `bootstrap/src/test/resources/contreexemple/LesCadransMontrentCeQueLeursItemsAffirmentTest/cadran-fautif/` ; **7 morsures sur le materiau reel** selon `rapport-lea-dev-palier-5-horloge-mat-ce1-11.md`, relayees ; le jeu est execute par `assertThatThrownBy`, ce que le premier essai de `lea-dev` avait manque (garde `LesGardeFousSontMisALEpreuveTest`, echec releve dans le meme rapport) |
| Geometrie et poids des illustrations, cliquet etendu aux douze cadrans | `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest`, `LIllustrationDeContenuResoutEtAucuneNeDortTest` | non releve |

- **Cliquet de poids** : le plafond passe de 69 203 a 96 873 octets, accorde par l'utilisatrice (`DEC-0120` A.284, relaye). C'est un cliquet relache a la demande de l'utilisatrice, pas un garde-fou neuf.
- **Ce que `ArchUnit` du palier 5 n'a pas** : `confirmation-archunit-palier-5.md` n'existe pas (`cloture-palier-5.md` section 11). Les regles sont dans le `verify` de la section 1 ; l'ecrit qui les confirme n'existe pas.
- Paliers 1 a 4, rappel (non relances) : mutations de mes rapports de palier, 14 lancements au palier 2 (7 rouges, 7 verts), 98 mutations valides au palier 3 (96 rouges, 2 verts : `DO2`, trou de garde, et `NE5`, vert voulu) ; palier 4, 26 mutations rejouees sur une classe dans `rapport-qualite-palier-4-lp6-sommet-cbfb152e.md`. **Palier 5 : aucun rapport de mutation de ma main dans le dossier** (`ls rapport-qualite*` : aucun fichier du palier 5).

### 4.2 Ce qu'aucun test ne couvre, tenu par lecture

Source : `Javadoc` de `ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest` (section "ce que ce test ne lit pas"), `FICHE-ce1-lire-l-heure.md` (reserves), `cloture-palier-5.md` sections 4 et 12. Je ne les ai pas mutees : un garde absent ne se mute pas.

| Propriete | Qui la tient | Etat |
|---|---|---|
| `TRV-CE1-5` au-dela de la forme (un indice est une methode, jamais une regle fausse), `TRV-CE1-6`, `QMT-CE1-2` (la source de chaque fait), `NRC-CE1-1` au-dela de sa partie textuelle | `nadia-prof`, lecture (`QC9`, dit dans le `Javadoc` du test) | non gardes par un test |
| Strategie par forme des minutes de l'horloge : *"aux heures pleines et aux quarts a 15, prendre l'heure d'apres ; aux demies et aux quarts a 45, l'heure d'avant"* gagne 10 items sur 12, plancher 8 avec trois items par forme (reserve `c` de `nadia-prof`, maintenue) | `nadia-prof` | la garde de paire (`S1` a `S4`, 6 sur 12) ne la lit pas ; la fiche ecrit *"Pas une exigence de ce lot"* |
| Proposition d'une autre forme de minutes toujours fausse, 12 sur 12 (reserve `a`) | `nadia-prof` | la barre de paire ne vaut que par l'equilibre de la paire |
| Reserves ecrites des verdicts `VALIDE SOUS RESERVE` de `-02`, `-03`, `-05`, `-06`, `-08`, `-10`, `-12` | `nadia-prof` | quatorze stories de reserves **non engagees** par decision de l'utilisatrice (`DEC-0120` A.286, relaye) : aucun test ne les garde |
| Titres et consignes des neuf entrees de catalogue **vus a l'ecran** | aucun agent | `rapport-marc-dev-palier-5-entrees-de-catalogue.md` section 6 : *"aucun rendu a l'ecran"* ; `nadia-prof` les a lus |
| Position de l'indice au-dessus de y = 551 a 744 de large, phrase de revelation dans sa boite, roles par genre, ordre des faits | `nadia-prof` | dernier etat ecrit : mon `rapport-qualite.md` section 5, 2026-10-16 ; **non relu a la cloture** |
| Trous de garde du palier 3 : `DO2` (la story dit enonce, le test lit le marqueur de consigne) ; `E3` (garde generique de `LON-CE1-3` a `-5`) | `camille-po` (reformulation), `theo-archi` | derniers etats ecrits : `cloture-palier-3.md` section 7 ; **non relus a la cloture** |
| Rendu de l'horloge et de la page de `-12` sur un navigateur | `nadia-prof`, audit a l'ecran | aucun test ne lit un rendu |

## 5. Obstacles de qualite ouverts, routes par `sofia-sm`

Je les nomme et ne les tranche pas. Etat lu dans `cloture-palier-5.md` section 12, que je n'ai pas amende.

| Code | Obstacle | Destinataire ecrit | Etat |
|---|---|---|---|
| `P6` | Le drapeau `-Dsurefire.failIfNoSpecifiedTests=false` est absent de `CLAUDE.md` et de `squad/README.md` (`git grep -n failIfNoSpecifiedTests -- CLAUDE.md squad/README.md` : aucune ligne, relu a `a3e05f13`). Les rapports du palier le passent aux mesures partielles par `-Dtest` (par exemple `rapport-lea-dev-palier-5-horloge-mat-ce1-11.md`, `cloture-palier-5.md` section 10) ; je n'ai pas rejoue la commande sans lui | `yanis-qa` ; `theo-archi` si c'est une regle d'architecture | **ouvert** : amendement ou refus ecrit, aucun des deux |
| `JAR` | Qui lance le `verify` de chaque lot et qui sert le jar a l'audit | `sofia-sm` | **ouvert** : l'agent lanceur l'a fait, rien n'est desormais ecrit |
| `P7` | Forme de lancement de `verifier-chemins.sh` et `verifier-prefixes.sh` : jamais `< /dev/null`, entree standard reelle | `sofia-sm`, amendement de `squad/README.md` | **ouvert, non fait** ; ma mesure de la section 1.1 suit la forme CI |
| CI GitHub | Une execution de la CI est-elle attendue avant la cloture, et qui la lit | utilisatrice | **ouvert** (section 1.4) |
| Ecarts de comptage | 18 `@Test` entre deux commandes (section 3.3) ; ecart de 26 non decompose | `sofia-sm`, `yanis-qa` | **ouvert**, non explique |
| `ArchUnit` du palier 5 | `confirmation-archunit-palier-5.md` absente | `theo-archi` | **ouvert** |
| Gardes du palier 5 non mutes | Aucun rapport de mutation du palier 5 (section 4.1) | `yanis-qa`, par `sofia-sm` | **ouvert** : nomme ici, non engage |
| `C2`, `C4` | Fusions serialisees d'un fichier a ajouts en fin de liste (registre) | non designe | **ouvert** |
| `pid.txt` | Fichier non suivi a la racine du checkout principal | inconnu | **ouvert**, non touche ; absent de ce worktree |
| `TMPDIR` mixte | `outils/mvn-jdk21-test.sh` rouge en forme mixte, avis de mon `rapport-qualite.md` section 4.1 ; je ne l'ai pas rejoue a la cloture | `theo-archi` ou `sofia-sm` | etat non relu |
| `node` et Python | `outils` depend de `node` (un juge) et d'un Python sans version declaree dans `ci.yml` ; avis de mon `rapport-qualite.md` section 4.2 | `theo-archi` ou `sofia-sm` | etat non relu |

## 6. Ce que je n'ai pas verifie

- La CI GitHub (section 1.4).
- Les etapes "Package du fat jar" et "Verifier la presence du build Maven" (section 1.1).
- La branche `python3` de l'etape `outils` (section 1.1).
- Les totaux des paliers 1 a 4 (section 3.1) : relayes, non rejoues.
- Les jeux fautifs des neuf lots : leur existence est lue dans les sujets de commit, pas dans chaque jeu ; leur morsure n'est pas mesuree par moi.
- L'origine de l'ecart de 18 et de l'ecart de 26 (section 3.3).
- Le point de depart 2498 comme total d'ouverture de s57 (section 3.1).

## 7. Verifications apres ecriture de ce fichier

Faites ce fichier indexe, avant le commit. Les tests de `bootstrap` ont lu le fichier avant l'ajout de la presente section, qui ne contient aucun tableau.

- `chemins` : `git ls-files` (moins le test du garde-fou) dans `verifier-chemins.sh`, entree standard reelle, ce fichier compris : sortie 0.
- `prefixes` : le sujet du commit passe dans `verifier-prefixes.sh` : sortie 0.
- Gardes de documentation : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest,AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest,ToutesLesReferencesDeDecisionResolventTest,ConcordanceDesTagsDAgentTest -Dsurefire.failIfNoSpecifiedTests=false`, `-am` present, perimetre `bootstrap` et ses amonts : `BUILD SUCCESS`, 19 tests, 0 echec, sortie 0. **Ce n'est pas `mvn verify`.**

## 8. Ce que ce rapport ne fait pas

Ne prononce aucun Done, technique ou pedagogique. Ne juge ni un texte, ni une cote. Ne fusionne rien, ne pousse rien. Ne decide rien : les obstacles de la section 5 sont routes par `sofia-sm`, qui les tranche ou les remonte.

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee.**
