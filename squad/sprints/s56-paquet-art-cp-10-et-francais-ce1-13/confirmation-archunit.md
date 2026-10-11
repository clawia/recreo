# Confirmation ArchUnit - s56 (`theo-archi`)

- **Auteur** : `theo-archi`. **Date** : 2026-10-11 12:34 (UTC+02:00), lue par `date`.
- **Objet** : confirmer, pour la cloture de s56, que les regles ArchUnit sont vertes sur l'arbre fusionne, que leur compte n'a pas bouge
  et qu'aucune n'est affaiblie ; verifier par mutation que les garde-fous neufs de s56 que j'ai specifies mordent ; trancher la place du
  garde-fou `B8`.
- **Arbre juge** : `round-two` a **`80c06e64`** (fusion de `s56-lea-gardes`, tout le code de s56), worktree dedie, branche
  `s56-theo-confirmation`, `git status --short` vide avant ecriture. `bash outils/verifier-lignee.sh` : "descend de la lignee de round-two".
  Les mutations ont ete faites dans un worktree jetable en `--detach` sur le meme commit, supprime apres mesure.
- **Base** : debut de s56 = **`dfcec37c`** (fusion du planning de s56).
- **Version** : **JDK 21.0.10**, propriete `java.version` des rapports `TEST-*.xml` de Surefire (`ADR-0071`).

## 0. Verdict

| Question | Verdict |
|---|---|
| Les regles ArchUnit sont-elles vertes ? | **oui** : 248 tests de `com.recreo.archi`, 0 echec ; reacteur complet `BUILD SUCCESS` (section 1) |
| Le compte a-t-il change ? | **non** : 39 `static final ArchRule` et 36 `@ArchTest`, au debut et a la fin de s56 (section 2) |
| Une regle est-elle affaiblie ? | **non** : aucun fichier de regle ArchUnit touche ; trois garde-fous de `com.recreo.archi` relevent un cliquet de la taille exacte de ce qui est servi, deux sont neufs (section 3) |
| Les garde-fous neufs que j'ai specifies mordent-ils ? | **oui** pour le plafond et la marque ; **`B8` mord par accident** sur sa borne (F1, section 4) |
| `B8` dans `bootstrap/archi` : confirme ? | **oui** (section 5) |
| Un `ADR` neuf est-il necessaire ? | **non** ; trois `ADR` amendes pendant s56, tous avec leur ligne d'index (section 6) |

## 1. Commandes et resultats

| Mesure | Commande | Resultat |
|---|---|---|
| R1, reacteur complet (job `build`, etape 1) | `bash outils/mvn-jdk21.sh -B clean verify` sur la racine, sans `-pl` | `BUILD SUCCESS` en 5 min 9 s ; **2403 tests, 0 echec, 0 erreur, 0 ignore** : domain 120, application 18, adapter-web 442, adapter-content 1470, bootstrap 347, integration 6 |
| R2, fat jar (job `build`, etape 2) | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package`, sans `clean`, apres R1, comme la CI | `BUILD SUCCESS`, memes comptes Surefire par module que R1 |

Comptes : somme des attributs `tests`, `failures`, `errors`, `skipped` des rapports `TEST-*.xml` de `target/surefire-reports` et
`target/failsafe-reports` des cinq modules. Le debut de s56 comptait **2307** (mesure du planning, meme perimetre) : **+96** sur le sprint.
Perimetre ArchUnit : rapports des classes de `com.recreo.archi`, **248** contre 240 a la cloture de s55. Les +8 sont les deux classes
neuves, `LeFormatDUneOeuvreNeDesignePasLaBonneReponseTest` (5, `B5`) et `LEnonceDuPaquetCpRapprocherDeuxOeuvresEstUniqueEtCourtTest`
(3, `B8`). `DecouplageMecaniqueContenuTest` 13, `LesReglesMordentTest` 38 : inchanges.

## 2. Compte de regles

Methode d'`ADR-0104` : lignes des `*.java` suivis par Git qui correspondent a `^\s*(public |private |protected )?static final ArchRule `
d'une part, a `^\s*@ArchTest\s*$` d'autre part (`git ls-files` sur l'arbre, `git grep` sur la base ; les worktrees d'agents en sont exclus).

| Commit | `ArchRule` | `@ArchTest` |
|---|---|---|
| `dfcec37c` (debut de s56) | 39 | 36 |
| `80c06e64` (cloture) | **39** | **36** |

Aucun garde-fou de s56 n'est une `ArchRule` : leurs objets sont une feuille CSS, des paquets YAML, des JPEG et des ecrans rendus. C'est ce
que chacun de mes avis et amendements de s56 avait annonce.

## 3. Diff des tests d'architecture

`git diff --name-status dfcec37c 80c06e64` restreint a `bootstrap/src/test/java/com/recreo/archi`,
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/archi` et `bootstrap/src/test/java/com/recreo/domain` :

| Fichier | Statut | Ce qui change, lu dans le diff |
|---|---|---|
| `ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest` | M | `REPRODUCTIONS_SERVIES` 8 -> 12, `POIDS_TOTAL_MAXIMUM` 176 474 -> 275 163. Mesure : 12 fichiers sous `static/oeuvres/`, 275 163 octets (`wc -c`), 12 entrees de registre. Le cliquet vaut exactement la dette |
| `LIllustrationDeContenuResoutEtAucuneNeDortTest` | M | `ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION` 81 -> 87 (six items du paquet) ; les deux morsures `K3` recoivent desormais les propositions reelles du depot, au lieu d'une liste vide ou d'une seule proposition mutee. Aucune assertion retiree |
| `UnItemAPropositionsEnImageTientSesTroisInvariantsTest` | M | `ITEMS_A_PROPOSITIONS_EN_IMAGE` 0 -> 6, la taille exacte du paquet |
| `LeFormatDUneOeuvreNeDesignePasLaBonneReponseTest` | A | `B5`, neuf |
| `LEnonceDuPaquetCpRapprocherDeuxOeuvresEstUniqueEtCourtTest` | A | `B8`, neuf |

Aucun fichier de `com.recreo.domain` (contre-exemples ArchUnit) n'est touche.

## 4. Les garde-fous neufs de s56 mordent-ils ?

Trois mutations sur l'arbre fusionne, chacune dans le worktree jetable, fichier restaure ensuite. Commande :
`bash outils/mvn-jdk21.sh -B -q -pl <module> -am test -Dtest=<classe> -Dsurefire.failIfNoSpecifiedTests=false -Dmaven.test.failure.ignore=true`
(perimetre : la classe nommee, `-am`).

| Mutation | Classe | Resultat |
|---|---|---|
| `recreo.css` ligne 1800, `max-height: min(100%, 11rem)` -> `min(100%, 14rem)` (P1' de mon amendement) | `LePlafondDeRenduDUneReproductionEnPropositionEstGardeTest` (`adapter-web`, 11 tests) | **3 echecs**, dont `laFeuilleReelleTientLesTroisClauses` : *"clause 2 [...] declare max-height: min(100%, 14rem)"* |
| `pointer-events: none` retire de `.propositions-oeuvres .proposition.deja-essayee::before` (E3) | `LaMarqueDeLOeuvreDejaEssayeeNeDependPasDeLImageTest` (`adapter-web`, 23 tests) | **2 echecs**, dont `laFeuilleEtLeFichierReelsTiennentLesCriteres` : *"critere 3 [...] ne declare pas pointer-events: none"* |
| `MOTS_AU_PLUS` 10 -> 12 | `LEnonceDuPaquetCpRapprocherDeuxOeuvresEstUniqueEtCourtTest` (`bootstrap`, 3 tests) | 1 echec, **pour une raison etrangere a la borne** (F1) |

**F1. La borne de `B8` n'est pas epinglee a son bord.** Le test de morsure s'appelle `unEnonceDeOnzeMotsSurTousLesItemsFaitEchouerLeGardeFou`,
mais sa phrase compte **13** mots sous la convention du CP, et il l'affirme lui-meme par `hasSize(MOTS_AU_PLUS + 3)`. Sous la mutation, il
rougit parce que `13` n'egale plus `MOTS_AU_PLUS + 3 = 15`, non parce qu'un enonce trop long passe. A 11 ou 12 mots au plus, l'enonce de
13 mots reste refuse : **une derive de la borne jusqu'a 12 n'est vue que par cette arithmetique**, qui suit la constante qu'elle devrait
juger. Correction, une ligne de test : une phrase de **11** mots (la borne plus un) et `hasSize(MOTS_AU_PLUS + 1)` ; la mutation 10 -> 11
rougit alors pour la bonne raison. **A `lea-dev`**, auteure du test ; je ne le corrige pas dans ce commit, qui est une confirmation. Le
garde-fou n'est pas faux aujourd'hui : l'enonce servi compte 10 mots et la borne est 10.

## 5. `B8` dans `bootstrap/archi` : confirme

`lea-dev` a place `B8` dans `bootstrap/archi` pour appeler `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.mots`, qui y vit. **Je
confirme**, et je ne veux pas de copie :

- `ADR-0101` decision 2 l'ecrit pour le CE1 : *"Ne pas recopier la convention de comptage [...] deux barres qui divergeraient sur ce
  qu'est un mot seraient pires que pas de barre"*. `B8` est une troisieme barre sur la meme notion de mot ; la recopier dans
  `adapter-content` creerait exactement le second exemplaire que la decision refuse.
- `adapter-content` ne voit pas les tests de `bootstrap` (le module de test ne remonte pas la chaine de dependances), et `bootstrap` voit le
  chargeur reel d'`adapter-content` : c'est le seul module ou l'appel tient sans copie.
- Le prix, nomme : `B8` vit loin de `B2`, `B3`, `B7` (tests de paquet d'`adapter-content`). Un lecteur qui cherche les gardes du paquet
  doit regarder deux modules ; le rapport de `lea-dev` les liste ensemble.

**Ce qui me ferait changer d'avis** : un quatrieme usage de `mots`. `ADR-0101` decision 5 repousse l'abstraction (niveau, mots, phrases)
au troisieme usage ; `B8` en est un troisieme qui n'a besoin que de `mots`, pas d'une barre parametree. Au quatrieme, la convention sort
des tests du CP vers une classe de test partagee de `bootstrap`, par un amendement d'`ADR-0101`.

## 6. `ADR` de s56

Aucun `ADR` neuf. Trois `ADR` existants amendes pendant le sprint, chacun avec son amendement date dans son corps, sa ligne dans le tableau
des amendements d'`ADR-index.md`, et sa regle executable mordue sur materiau reel dans le meme commit :

| `ADR` | Commit | Objet |
|---|---|---|
| `ADR-0026` | `1de72f0f` | la clause 3 exclut un sujet pseudo-element decoratif (marque O1) |
| `ADR-0024` | `6f8895a4` | un paquet sans graphie porte `explication` sur tous ses items ou sur aucun |
| `ADR-0104` | `a44a9d5b` | ratification des exclusions des reponses en image (REV-3, `ADR-0083`) et oracle independant de leur test de non-exclusion |

Statuts inchanges (`Accepte`). `ADR-index.md` n'a rien de plus a changer.

## 7. Jobs de `.github/workflows/ci.yml`

| Job | Lance ? | Commande | Resultat |
|---|---|---|---|
| `build` | **oui**, ses deux etapes | R1 puis R2, sur `80c06e64` (ce commit n'ajoute qu'un fichier de `squad/`) | `BUILD SUCCESS` deux fois |
| `chemins` | **oui** | `bash .github/scripts/verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh'` plus ce fichier, passes a `verifier-chemins.sh` | rendu dans le compte rendu de la tache |
| `prefixes` | **oui** | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, puis le sujet de ce commit dans `verifier-prefixes.sh` | rendu dans le compte rendu de la tache |
| `outils` | **non** | aucun outil touche | - |

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.
