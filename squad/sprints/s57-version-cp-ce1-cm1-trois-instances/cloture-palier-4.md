# [Sprint s57] Cloture du palier 4

- **Statut : palier 4 livre et trie. Porte technique ROUGE a `ad7f8022` (section 8), `verify` complet VERT au sommet corrige (section 10.1, ce qui remplace le statut rouge pour cet arbre, pas la lecture de la CI distante). Le sprint s57 n'est pas clos.** Un palier 5 est a venir (`QP7`, section 6). Ce fichier est ecrit une seule fois, comme `cloture-palier-3.md`. **Aucun Done n'est prononce par `sofia-sm`** : le pedagogique est a `nadia-prof`, le technique est juge par la CI, que personne n'a lue, et que le `verify` de la section 8 contredit.
- **Horodatage de debut du palier** : 2026-10-21 22:13 (UTC+02:00), amendement d'ouverture du palier 4 dans `planning.md`, inscrit avant l'ouverture de tout worktree du palier.
- **Horodatage de fin du palier** : 2026-10-23 12:08 (UTC+02:00), lu par `date` a l'ecriture. **Duree effective : 37 h 55 min.** Palier 1 : 25 h 52 min, palier 2 : 25 h 11 min, palier 3 : 43 h 3 min.
- **Ecrit par** : `sofia-sm`, casquette Scrum Master. Arbre : `round-two` a `ad7f8022` (fusion de `s57-camille-p4-cloture`), checkout principal. Les deux dernieres fusions du palier (`806bc78a`, `ad7f8022`) sont de ce geste, chacune precedee de sa ligne dans `planning.md` (RF11, `77f0a4ec`, `625867d9`).
- **`RAPPORT.md` et `RAPPORT-tokens.md` ne sont pas regeneres.** Ce sont des fichiers de fin de sprint, le sprint s57 n'est pas clos. Aucune consommation de tokens n'est donc chiffree ici, et aucun chiffre de tokens n'est repris d'ailleurs. `review.md` et `retro.md` ne sont pas ecrits non plus : la retro du palier 4 n'etait pas dans la demande.

## 1. Fusions de lot : une ligne par fusion

Catalogue lu par `git show <fusion>:adapters/adapter-content/src/main/resources/catalogue.yaml`, lignes `^  - id:`, dans chaque commit de fusion et dans son premier parent, pas sur l'arbre de travail. Depart : 157 (`eb267ba7`, cloture du palier 3). **163 a `HEAD`**, soit les 164 annonces par `composition-palier-4.md` 5.2 moins un : `LP9` n'est pas entre.

| Fusion | Lot, paquet | Heure (2026-10-) | Avant | Entrees | Apres | Total de tests annonce | Base, commits apportes |
|---|---|---|---|---|---|---|---|
| `a3b6543e` | `LP2`, `S57-BR-MAT-CE1-10`, `ce1-masses-contenances` | 22 16:08 | 157 | 1 | 158 | 2895 | `eb267ba7`, 7 |
| `481673d1` | `LP4`, `S57-BR-EMC-CE1-02`, `ce1-emc-regle-sanction` | 22 16:22 | 158 | 1 | 159 | 2980 | `da6e81c4`, 9 |
| `611cce27` | `LP1`, `S57-BR-FRA-CE1-21`, `ce1-mots-invariables` | 22 16:32 | 159 | 1 | 160 | 3020 | `4e047773`, 11 |
| `1f7c91b5` | `LP3`, `S57-BR-MAT-CE1-11`, `ce1-calendrier-dates` | 22 22:03 | 160 | 1 | 161 | 3077 | `51a9d957`, 5 |
| `a4d8efb8` | `LP5`, `S57-BR-EMC-CE1-03`, `ce1-emc-droit-devoir` | 22 22:20 | 161 | 1 | 162 | 3151, non ecrit nulle part (section 2) | `1f7c91b5`, 8 |
| `f9f7f0fc` | `p4-10`, `S57-GARDE-NOMBRE-DE-MANCHES` | 22 22:37 | 162 | 0 | 162 | 3158, deduit (section 2) | `a4d8efb8`, 7 |
| `c11b18e1` | `LP6`, `S57-BR-EMC-CE1-04`, `ce1-emc-interet-commun` | 23 10:58 | 162 | 1 | 163 | 3232, mesure de `yanis-qa` | `a65341cb`, 7 |

- **Lignee** : `git merge-base <premier parent> <second parent>` et `git rev-list --count <premier parent>..<second parent>` lus ce jour pour les sept fusions : chaque base est un sommet de `round-two` ou un commit ancetre, aucune fusion ne rapporte un historique etranger. Cette relecture est la mienne. **Je n'ai pas lance `outils/verifier-lignee.sh` pour elle** : il juge le worktree courant. Pour les deux fusions de ce geste, j'ai verifie avant de fusionner que `c08e49ab` est ancetre de chaque branche (`git merge-base --is-ancestor`, dans son worktree, arbre propre), et **je n'ai lance `outils/verifier-lignee.sh` qu'apres les fusions**, dans chacun des deux worktrees : exit 0 pour `s57-nadia-p4-lp6b` et pour `s57-camille-p4-cloture`. C'est un ecart a RF10 (le script devait preceder la fusion), sans effet sur le resultat.
- **`grep -c "paquet: <nom>$"`** sur `catalogue.yaml`, a `eb267ba7` puis a `HEAD` : 0 puis 1 pour chacun des six paquets entres (`ce1-masses-contenances`, `ce1-emc-regle-sanction`, `ce1-mots-invariables`, `ce1-calendrier-dates`, `ce1-emc-droit-devoir`, `ce1-emc-interet-commun`).
- **Fusions de fiches de conformite** (verdicts de `nadia-prof`, tous des ecrits seuls) : `LP2` `da6e81c4` VALIDE sur `c29d1d26` ; `LP4` `4e047773` VALIDE SOUS RESERVE sur `f36d83bc` ; `LP1` `8a9c4b88` VALIDE SOUS RESERVE sur `8c76b1c7` ; `LP3` `62588036` VALIDE SOUS RESERVE sur `f863fa7a` ; `LP5` `b8c437e1` VALIDE SOUS RESERVE sur `5b9bb648` ; `LP6` `9b5ee9c0` VALIDE SOUS RESERVE sur `c975a7b8`. **La reserve 3 de `LP6` est levee par `nadia-prof`** (`5d038278`, fusionne par `806bc78a`) sur la mesure de `ines-design` a `c975a7b8` (0 element sous le pli).
- **Fusion de cloture produit** : `ad7f8022`, `camille-po`, `0a51d59c`. `git diff --name-only` des deux branches de ce geste ne nomme que `squad/` et `docs/backlog/backlog.md` : aucun fichier sous `adapters`, `domain`, `application`, `bootstrap`, `outils`, `.github`, aucun paquet.

## 2. Total de tests

**Le total apres fusion de `LP6` est 3232, mesure par `yanis-qa` a `c975a7b8` : `verify` complet, exit 0, 0 echec, 0 erreur, 0 ignore** (`rapport-qualite-palier-4-lp6-sommet-c975a7b8.md`, section 1). `git diff --quiet c975a7b8 HEAD -- adapters domain application bootstrap pom.xml outils .github contenu-repris` sort 0 : **le code et le contenu servis de `HEAD` sont ceux de `c975a7b8`**. Je ne l'ai pas remesure comme un 3232 vert : mon propre `verify` de la section 8 s'arrete en echec dans `bootstrap`, apres les modules `domain`, `application`, `adapter-web` et `adapter-content`, et ne produit donc pas de total de reacteur.

**Recompte independant par les sources.** Aucun fichier `*Test.java` ne declare `@ParameterizedTest`, `@TestFactory` ni `@RepeatedTest` a `HEAD` (`git grep -l`, vide). `git grep -c "^\s*@Test"` sur `*Test.java`, somme par commit : `eb267ba7` 2811, `a3b6543e` 2851, `481673d1` 2936, `611cce27` 2976, `1f7c91b5` 3033, `a4d8efb8` 3107, `f9f7f0fc` 3114, `c11b18e1` 3188, `HEAD` 3188. **Chaque total annonce est egal a cette somme plus 44**, y compris la base de 2855 mesuree au palier 3 (2811 + 44). L'ecart constant de 44 est une propriete du compte (tests declares autrement que par une ligne commencant par `@Test`, et tests d'integration), que je n'ai pas decomposee. C'est une coherence, pas une mesure de l'arbre.

**Ecart de 3020 a 3077 : +57, et d'ou il vient.** `LP1` (3020) est fusionne avant `LP3`. `LP3` ajoute `PaquetCe1CalendrierDatesTest` : **57 `@Test`** a `1f7c91b5`, 50 a `c8404173`. `yanis-qa` a mesure `LP3` isole a `c8404173` : 3070, soit une base deduite de 3020 plus 50 (`rapport-qualite-palier-4-lp5-lp3.md`, section 2). `lea-dev` a ensuite repris `LP3` apres le refus de `nadia-prof` (`rapport-lea-dev-palier-4-lp3.md`, section 6 : *"Delta de tests de la reprise : +7"*), ce qui porte la classe de 50 a 57. 3020 + 57 = 3077. **Le 3070 de `yanis-qa` mesure donc le lot avant sa reprise, et le total apres fusion, 3077, le lot apres.** Les deux chiffres sont justes pour des arbres differents ; aucun n'est faux.

**Deux totaux ne sont pas mesures.** `a4d8efb8` (`LP5`) : le message de fusion ne porte pas de chiffre et **aucun fichier du depot n'ecrit 3151** (`git grep -n -E "\b3151\b"`, aucun resultat hors cette ligne). Le rapport de `LP5` donne 3089 pour le lot isole (+69), et la classe porte 74 `@Test` a la fusion : 3077 + 74 = 3151 par les sources. `f9f7f0fc` (`p4-10`) : `yanis-qa` ecrit *"non mesure, deduit a 3158"* et la somme des sources donne 3151 + 7 = 3158. **Ces deux totaux sont des deductions, rien d'autre.** Les messages de fusion de `LP2`, `LP4`, `LP1` et `LP3` portent *"verify vert sous jeton RF1"* avec leur chiffre ; **le journal du `verify` correspondant n'est pas relu ici**.

**ArchUnit.** Je n'ai pas relu de confirmation d'`ArchUnit` du palier 4 : `confirmation-archunit-palier-4.md` n'existe pas dans le dossier (`ls`). Les lectures de lignee de `theo-archi` (`lecture-theo-lignee-palier-4*.md`) existent pour chaque lot. **L'ecart est dit, pas comble.**

## 3. Matrice

La commande de la "Methode du compte" de `MATRICE-couverture.md`, lancee par moi sur `HEAD` : `cv 156`, `ec 7`, `nc 39`, `ncb 75`, total 277. Denominateur 277 - 75 = 202. **156 / 202 = 77,23 %**, egal a ce que `camille-po` ecrit (`arbitrage-camille-cloture-palier-4.md`, P6a, 154 / 202 avant). Le palier 3 se terminait a 152 / 200.

## 4. Ce qui est clos, ce qui est reporte

**La table est dans `arbitrage-camille-cloture-palier-4.md`, section 1 (points P1a a P9), avec pour chaque ligne CLOS ou REPORTE, la raison, le destinataire et le declencheur. Je ne la recopie pas.** `DEC-0120` A.171 a A.179 et `DEC-0125` la portent cote decision. Ce que je constate en la lisant :

- **Clos** : reserve 3 de `LP6` (P1a, levee par `nadia-prof`), plis de `LP4` et de `LP2` cote produit (P4e, P4h : a `nadia-prof` de lire et lever), `p4-10` (P8f), `REGISTRE-identifiants.md` (P7c), matrice et `DEC` (P6, P7). `S57-TYP1-TEXTES-DE-GABARIT` et `S57-MESSAGE-PARTIE-PERDUE` sont **retirees** par `DEC-0125` (P8e).
- **Reporte** : P1b, P1c, P2, P3a a P3c, P4a a P4d, P4f, P4g, P8a a P8d, P8g.
- **Un clos n'est pas un Done.** Aucune ligne de ce paragraphe ne prononce de Done pedagogique, et aucune reserve n'est levee par `sofia-sm`.

## 5. Compte des reports

Regles de `DEC-0120` A.66 a A.73, `composition-palier-4.md` section 7, et ecrit de `camille-po` (P8). Je compte, je ne tranche pas.

| Poste | Etat a la cloture | Compte |
|---|---|---|
| `LP1` a `LP5`, `LP6`, `p4-10` | livres et fusionnes | aucun report |
| `LP9` | non engage, aucune des trois conditions d'A.149 ecrite | **deuxieme ecartement** (P8a) ; un troisieme demande une `DEC` |
| `LP7`, `LP8` | hors composition | **deuxieme ecartement** (P8b, P8c). `LP8` n'a **aucune story** au backlog |
| `S57-TYP1-TEXTES-DE-GABARIT`, `S57-MESSAGE-PARTIE-PERDUE` | retirees | troisieme non-entree, `DEC-0125` |
| huit stories neuves ou reportees | non engagees | **report 1** chacune : `S57-EMC-CE1-04-R1`, `S57-GARDE-PAQUET-INTERET-COMMUN`, `S57-PLI-MOTS-INVARIABLES-REVELATION`, `S57-SAISIE-MOTS-PROPOSES-PAR-LE-CLAVIER`, `S57-PLI-LP3-LP5-MESURE`, `S57-EMC-CE1-02-R1`, `S57-EMC-CE1-03-R1`, `S57-GARDE-CE1-PROPOSITIONS-ET-SUPPORTS` |
| commentaires de `src/` (ecarts 33, 40 a 44) | sans titre au backlog | premier report, a la charge de `camille-po` (P8g) |

Un report au palier 5 est le second pour ces neuf postes : le troisieme est interdit sans `DEC`.

## 6. Ce qui a besoin de l'utilisatrice

Reprise de `arbitrage-camille-cloture-palier-4.md`, section 2, rien n'est decide par moi.

1. **`QU2`** : `PAR-8` depasse en A' (5,50 sur 5,0, plus le prix du rejeu 2 de `LP6`). Laisser tel quel, ou consigner un relevement a 5,75 une fois le rejeu chiffre.
2. **`DEC-0125`** : deux stories de typographie retirees apres trois non-entrees. Une phrase de l'utilisatrice en garde une.
3. **`QP7`, palier 5** : `LP9`, `LP7`, `LP8` sont a leur deuxieme ecartement et n'ont **pas de report possible**. Leur composition au palier 5 est conditionnee a des ecrits qui n'existent pas (conditions d'A.149 pour `LP9`, story pour `LP8`).
4. **Pousser** : les 155 commits d'avance sur `origin/round-two`, ce fichier compris (section 8) ne sont pas pousses par ce geste, et la porte technique est rouge.

**Ce qui est a `nadia-prof`** : le **prix du rejeu 2 de `LP6` n'est pas chiffre** (`registre-prix-palier-4.txt`, ligne `Ap`, `0.00`, `non-chiffre`). Elle ecrit qu'elle ne l'estime pas ; ni `camille-po` ni moi ne le fixons. Tant que la ligne est a zero, le total du palier, 12,50 sur 20,0, est un minimum.

## 7. Decisions du palier

Liste de ce que `git diff --name-status 4f413e37 HEAD -- squad/decisions` rend (`4f413e37` : cloture du palier 3). **Aucune `ADR` n'est creee. Ce fichier ne cree ni n'amende aucune decision.**

| Decision | Cree ou modifiee | Statut |
|---|---|---|
| `DEC-0125` (retrait de `TYP1` et de `MESSAGE-PARTIE-PERDUE`) | **creee**, 2026-10-23, `camille-po` | `Acceptee`, reversible |
| `DEC-0120` (ouverture de s57) | modifiee : amendements 22 a 28 (`A.133` a `A.179`) | `Acceptee`, inchange |
| `DEC-0123` (six reponses au palier 3 CE1) | modifiee : 6e et 7e amendements (grille dessinee, composition) | `Acceptee` pour `Q-05` a `Q-08` et `Q-10`, `Q-09` portee par `DEC-0124` |
| `DEC-0124` (le clavier ne corrige pas) | modifiee : `689d8487` | `Acceptee` pour le sens ; sa mention *"non appliquee"* est perimee depuis `03de0f6d`, ecart 12 du palier 3 non repris |
| `DEC-0062` (attribution d'un enonce recu) | modifiee : amendements F et G (E5 ferme) | `Acceptee`, inchange |
| `ADR-0058` (la saisie normalise dans sa mecanique) | modifiee : correction du 2026-10-21 18:32 (`theo-archi`, ecarts 36 a 38 du releve), `50c74519` | `Accepte` pour ses clauses de conception, statut inchange |

`DEC-index.md` porte la ligne de `DEC-0125` et les amendements de `DEC-0120`.

## 8. Porte technique : jobs de `ci.yml`, lances et non lances

Chaque job est nomme. **Je ne deduis aucun job que je n'ai pas lance.**

**`build` complet : lance, ROUGE.** `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, sur un worktree propre detache a `ad7f8022`, sous le jeton RF1 (`mkdir s57-p4-jeton-verify` a la racine du scratchpad, libere apres), JDK 21 par l'enveloppe. **Exit 1, `BUILD FAILURE`, 11 min 06 s** (11:50 a 12:01). Reacteur : `domain`, `application`, `adapter-web` et `adapter-content` SUCCESS, **`bootstrap` FAILURE** (381 tests lances, **2 echecs**, 0 erreur). Les deux echecs sont dans `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` (`ADR-0087`) :

- `leDepotReelNAffichePasMoinsQuIlNEcrit` et `laDetteNommeExactementLesLignesEnSurplusQuiRestent`.
- **Une seule ligne en cause** : `DEC-0120`, ligne 2579, dans la section A.173 (`LP6` : les reserves 3, 6 et 7). La table a l'en-tete `Reserve`, `Decision`, `Raison`, `Declencheur` (4 colonnes) ; la ligne de la reserve 3 porte **5 cellules**, la cellule en trop est *"la fusion"*, que **aucun rendu markdown n'affiche**. La ligne vient de `0a51d59c` (`git blame`), c'est-a-dire de la branche `s57-camille-p4-cloture` que je viens de fusionner.
- **Ce que `camille-po` a lance** : une seule classe de ce module (`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, section 3 de son arbitrage), et elle ecrit que `mvn verify` appartient a `sofia-sm`. La classe qui juge les tables n'est pas dans cette liste.
- **Defaut de rédaction a `camille-po`** (redaction d'une `DEC`, categorie *Redaction, doc* du routage, a elle pour la correction). **Je ne corrige pas la ligne moi-meme** : `DEC-0120` est a elle. La correction attendue est une cellule de moins (ou une colonne `Destinataire` dans l'en-tete) a la ligne 2579, puis un `verify` complet.

**Derniers `verify` complets verts, tels que declares, pas relus** : `yanis-qa` a `c975a7b8`, 3232, exit 0 (section 2). **Ce `verify` ne couvre pas les ecrits de `squad/` ajoutes depuis**, et c'est precisement ce que le `verify` de ce fichier a trouve.

**`build`, etape "Package du fat jar"** : non lancee, la porte `Verify` etant rouge. **`build`, etape "Verifier la presence du build Maven"** : non lancee (teste `pom.xml`).

**`outils`** : lance par la logique de `ci.yml` (trois motifs `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`, interpreteur `python`), exit 0, **13 juges executes** (6, 2 et 5), 0 echec, aucun motif vide. `git ls-files` rend 13 fichiers pour ces motifs. **`yanis-qa` ecrit 24 juges** dans `rapport-qualite-palier-4-lp4-sommet-*.md` ; je trouve 13 et **je n'explique pas l'ecart**.

**`build`, mesure partielle sur l'arbre avec ce fichier present** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest,AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest,ToutesLesReferencesDeDecisionResolventTest -Dsurefire.failIfNoSpecifiedTests=false`, **`-am` present**, perimetre `bootstrap` et ses amonts, worktree detache a `ad7f8022` plus ce fichier non suivi : exit 1, 18 tests, 2 echecs, ceux de la ligne 2579 de `DEC-0120` et **aucun autre** (10 verts pour `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, 3 pour `ToutesLesReferencesDeDecisionResolventTest`). Ce fichier n'ajoute aucune ligne en surplus.

- **`chemins`** : `bash .github/scripts/verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) plus ce fichier, 3149 chemins, passent a `verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` (21 cas) sortent 0 ; `commit-atteignable.sh 892ab382` sort 0 ; `git log --no-merges --format=%s 892ab382..HEAD` (124 sujets) passe a `verifier-prefixes.sh` : *"Tous les commits portent un prefixe d'agent connu."*, sortie 0 ; `verifier-garde-fou-accent.sh "892ab382..HEAD"` : *"0 commit(s) le touchent dans la plage, tous [THEO]."*, sortie 0. `892ab382`, la reference locale de `origin/round-two`, tient lieu de `github.event.before`. Le sujet du commit de ce fichier n'est verifie qu'apres son commit (rapport final).

**La CI distante n'est lue par personne.** `git status -sb` : `round-two...origin/round-two [ahead N]`, voir le rapport final de ce geste pour N. `origin/round-two` pointe `892ab382` (ouverture du palier 4) : **ce n'est donc pas un premier push**. Je n'ai lance aucune commande `gh` et n'ai pas fetche : ce chiffre est celui de la reference locale. Aucun resultat de cette section n'est celui de la CI.

## 9. Obstacles du palier, etat a la cloture

Repris dans `review.md` a la cloture du sprint. **Un obstacle n'est clos que quand le fichier qui le tranche existe.**

| Obstacle | Categorie | Agent | Artefact | Etat |
|---|---|---|---|---|
| Ligne a 5 cellules dans `DEC-0120` A.173 : `verify` rouge | redaction, qualite | `camille-po` | correction de la ligne, `verify` vert | **ouvert** : aucun fichier ne le tranche |
| Reserve 3 de `LP6` (pli) | pedagogie | `nadia-prof` | fiche | **clos** : `5d038278` fusionne par `806bc78a` |
| Reserves 6 et 7 de `LP6`, defaut `T5`, survivants `G5`, `G11`, `LG5`, `LG11` | produit, qualite | `camille-po` | `S57-EMC-CE1-04-R1`, `S57-GARDE-PAQUET-INTERET-COMMUN` | **ouvert, reporte** (section 4) |
| Autres reserves et plis (`LP1`, `LP3`, `LP4`, `LP5`) | pedagogie, produit | `nadia-prof`, `camille-po`, `ines-design` | stories de la section 5 | **ouvert, reporte** |
| Prix du rejeu 2 de `LP6` | capacite | `nadia-prof` | ligne du registre | **ouvert** |
| `QU2`, `DEC-0125`, `QP7` | produit | utilisatrice | decisions | **ouvert** (section 6) |
| Conditions de `LP9`, story de `LP8` | produit, technique | `camille-po`, `nadia-prof`, `ines-design`, `theo-archi` | ecrits | **ouvert**, avant la composition du palier 5 |
| `confirmation-archunit-palier-4.md` absente | technique | `theo-archi` | confirmation | **ouvert** (section 2) |
| Ecart 24 juges (`yanis-qa`) contre 13 (job `outils`) | qualite | `yanis-qa` | explication | **ouvert** (section 8) |
| Rapport de tokens | capacite | `sofia-sm` | `RAPPORT-tokens.md` | **a la cloture du sprint** (en-tete) |

## 10. Amendement du 2026-10-23 (UTC+02:00) : le verify rouge de la section 8, sa cause, sa correction

Ecrit par `sofia-sm`, casquette Scrum Master. Il complete les sections 1, 8 et 9 sans les reecrire : ce qu'elles disent du sommet `ad7f8022` reste vrai de `ad7f8022`.

- **Le verify rouge.** Section 8 : `bash outils/mvn-jdk21.sh -B clean verify` a `ad7f8022`, exit 1, 2 echecs de `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`, tous deux sur la ligne 2579 de `DEC-0120`.
- **La cause.** La ligne de la reserve 3 de `LP6` (section A.173) portait 5 cellules sous un en-tete de 4 colonnes. La cellule en trop, *"la fusion"*, n'etait affichee par aucun rendu markdown. Defaut de redaction de `0a51d59c`, a `camille-po`.
- **La correction.** `camille-po`, branche `s57-camille-p4-cloture-b`, commit `d8ea2a2b` : un commit, une ligne (`git diff --name-only round-two...s57-camille-p4-cloture-b` ne nomme que `DEC-0120`). La cellule en trop est deplacee dans la cellule `Declencheur` (`la fusion` devient la suite de *"fusionner la branche (RF11), la fusion"*). Mesure de `camille-po` : 11 classes de `bootstrap`, 79 tests, verts, en partiel. Cette mesure est partielle, pas un `verify` complet.
- **La fusion.** `21fcb32f`, precedee de sa ligne RF11 dans `planning.md` (`9a100f04`). **RF10 respecte cette fois** : `bash outils/verifier-lignee.sh` lance dans le worktree de la branche avant la fusion, exit 0, arbre propre. L'ecart a RF10 note en section 1 pour `806bc78a` et `ad7f8022` reste dit : ces deux fusions ont eu leur script apres, pas avant.
- **L'obstacle de la section 9** (*ligne a 5 cellules dans `DEC-0120` A.173*) : le fichier qui le tranche existe (`d8ea2a2b`, fusionne par `21fcb32f`). Il n'est tenu pour clos qu'avec le `verify` complet de la section 10.1.

### 10.1 Porte technique au sommet corrige : jobs de `ci.yml` un par un

Arbre mesure : `38a77bf9` (sommet de `round-two` apres la fusion `21fcb32f` et l'amendement de la section 10), worktree propre detache. Chaque ligne vient d'une sortie lue ce jour.

- **`build`, "Verify"** : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, sous le jeton RF1 (`mkdir`, libere apres), JDK 21 par l'enveloppe. **Exit 0, `BUILD SUCCESS`, 12 min 18 s** (12:20 a 12:32). Reacteur : `recreo`, `domain`, `application`, `adapter-web`, `adapter-content`, `bootstrap` SUCCESS. Lignes `Tests run` : 120 + 31 + 523 + 2171 + 381 + 6 = **3232**, 0 echec, 0 erreur, 0 ignore. Somme des 326 fichiers XML de `target/surefire-reports` et `target/failsafe-reports` : **3232**, 0 echec, 0 erreur, 0 ignore. **Total mesure du reacteur : 3232**, egal au chiffre de `yanis-qa` a `c975a7b8`. L'ecart que la section 2 ne pouvait pas combler est comble : le code servi n'a pas bouge entre `c975a7b8` et ce sommet, et le total non plus.
- **`build`, "Package du fat jar"** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package`, **exit 0**. `bootstrap/target/recreo.jar` existe (22 510 455 octets) ; son horodatage (12:31) est celui du `verify`, pas de cette etape : l'etape n'a pas recree le jar, elle a fini en succes.
- **`build`, "Verifier la presence du build Maven"** : `[ -f pom.xml ]`, **exit 0** (`present=true`).
- **`prefixes`** : `verifier-prefixes-test.sh` exit 0 ; `commit-atteignable-test.sh` exit 0 ; `commit-atteignable.sh 892ab382` exit 0 ; `git log --no-merges --format=%s 892ab382..HEAD` (128 sujets) passe a `verifier-prefixes.sh` : exit 0 ; `verifier-garde-fou-accent-test.sh` (21 cas) exit 0 ; `verifier-garde-fou-accent.sh "892ab382..HEAD"` exit 0 (0 commit le touche dans la plage, tous [THEO]). `892ab382`, reference locale de `origin/round-two`, tient lieu de `github.event.before`.
- **`chemins`** : `verifier-chemins-test.sh` exit 0 ; `git ls-files` (moins le test du garde-fou), 3149 chemins, passent a `verifier-chemins.sh` : exit 0.
- **`outils`** : le balayage de `ci.yml` rejoue a la main : `outils/*-test.sh` 6, `squad/outils/*/*-test.sh` 2, `outils/*-test.py` 5, **13 juges purs, 0 rouge**. Ce chiffre est celui du job, pas celui des 24 de `yanis-qa` : l'ecart de la section 8 n'est pas explique ici.
- **Ce que ce geste ne couvre pas.** La mesure porte sur `38a77bf9`, pas sur le commit qui inscrit cette section : ce commit ne change qu'un fichier de `squad/`, et `prefixes` et `chemins` sont relances sur lui dans le rapport du geste. **Le `verify` complet n'est pas relance sur ce dernier commit.** La CI distante n'est lue par personne : aucun resultat ci-dessus n'est celui de GitHub.
- **L'obstacle de la section 9** (*ligne a 5 cellules dans `DEC-0120` A.173*) est **clos** : `d8ea2a2b` le tranche, et le `verify` complet vert ci-dessus le confirme.

## 11. Amendement du 2026-10-23 (UTC+02:00) : l'obstacle "confirmation ArchUnit absente" des sections 2 et 9 est clos

Ecrit par `sofia-sm`, casquette Scrum Master. Les sections 2 et 9 ci-dessus disent `confirmation-archunit-palier-4.md` absente et l'obstacle **ouvert** ; elles sont laissees telles quelles, c'est cet amendement qui les corrige.

**Le fichier existe.** `confirmation-archunit-palier-4.md`, ecrit par `theo-archi` sur la branche `s57-theo-p4-archunit` (commit `798de7b3`), fusionne dans `round-two` par `417bfc7c`. Verifie sur disque apres la fusion. **L'obstacle est clos par ce fichier.**

**Ce que le fichier mesure lui-meme.** `M1` : les 15 classes porteuses de regles ArchUnit au sommet `f3b06989`, mesure partielle avec `-am`, sortie 0, **88 tests, 0 echec, 0 erreur, 0 ignore**. Compte de regles par la methode d'`ADR-0104` : 41 declarations `static final ArchRule`, 38 `@ArchTest`, 16 fichiers, identiques a `eb267ba7` et a `f3b06989` (ecart nul sur le palier) ; 39 regles reelles dans 15 fichiers. Aucune regle ajoutee, modifiee ni affaiblie, aucun `ADR` change (`V1` a `V5`).

**Ce qu'il reprend d'un autre rapport, sans le relancer.** `M2` : le `verify` complet de **3232 tests, 0 echec**, mesure par moi a `38a77bf9` (section 8). `theo-archi` ne l'a pas relance et le dit ; il le rend valable pour `f3b06989` par `M3` (`git diff --name-only 38a77bf9 f3b06989` ne rend que `cloture-palier-4.md`). Son fichier ne remplace donc pas un `verify` complet du sommet.

**Dettes d'architecture qu'il nomme, aucune payee, aucune close par moi :**

| Code | Dette | Destinataire |
|---|---|---|
| `DA1` | garde generique `E3` non ecrit ; story `S57-GARDE-CE1-PROPOSITIONS-ET-SUPPORTS` reportee | `camille-po`, puis le developpeur engage |
| `DA6` | recensement d'`ADR-0013` aveugle aux chemins assembles par segments | `theo-archi`, amendement d'`ADR-0013` |
| `DA7` | `verifier-chemins.sh` ne voit pas la forme `X--...` (ecart 39) | `theo-archi`, amendement d'`ADR-0010` avec son cas de test |

Les fusions des trois ecrits du 2026-10-23 (`s57-theo-p4-archunit`, `s57-theo-p5-dessins`, `s57-camille-p5-proposition`) sont portees par `planning.md` (une ligne RF11 chacune). Les tables des fichiers fusionnes ont ete passees a un scanner de cellules naif (barres non echappees comptees, backticks ignores, hors depot) : 10 fichiers, 334 tables, 1885 lignes de corps, 0 ecart. Le `verify` complet du sommet final, ses etapes et les jobs `prefixes`, `chemins` et `outils` sont lances apres ce commit, sur un worktree detache ; leur resultat est dans le compte rendu de cette fusion et n'est pas recopie ici. Aucun Done n'est prononce par ce fichier.
