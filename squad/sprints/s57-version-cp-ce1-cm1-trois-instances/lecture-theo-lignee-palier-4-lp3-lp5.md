# s57 palier 4 - lecture de `theo-archi` : lignee, ArchUnit et conflits de `LP3` et `LP5`

- **Date** : 2026-10-22, 18:30 a 18:55 (UTC+02:00), heure lue par `date`.
- **Auteur** : `theo-archi`, transverse. La lecture de lignee de `LP5` est celle que `composition-palier-4.md` 5.3 point 3 demande pour une branche d'A', dont l'auteure fusionne.
- **Arbre** : `round-two` a `51a9d957` (`git rev-parse --short round-two`, avant et apres les mesures). Lus, sans rien ecrire dans les worktrees :
  - `LP3` `s57-lea-mat-ce1-11` a `c8404173` (code `8f25fd64`) ;
  - `LP5` `s57-sofia-emc-ce1-03` a `db5e0e6d` (code `edf2b03b`) ;
  - p4-10 `s57-marc-garde-nombre-de-manches`, dont `git rev-parse --short` rend **`fedd3ae9`** et non `08500102` : deux commits s'y sont ajoutes depuis ma lecture de p4-10, dont le litteral `src/main/` (`1d97832d`).
- **Version** : JDK 21.0.10, propriete `java.version` des rapports `TEST-*.xml` de chaque mesure, une seule valeur par mesure (`ADR-0071`).
- **Ce que ce fichier ne fait pas** : il ne fusionne rien, ne juge aucun contenu, ne prononce aucun Done et ne cree ni n'amende aucune `ADR`. Il ne decide pas des corrections de contenu que les dettes de `LP3` attendent (`nadia-prof`). Les reperes `L`, `A`, `D`, `C` n'appartiennent qu'a ce fichier.

## 0. Verdict

| Code | Point | Verdict |
|---|---|---|
| `L` | Lignee | les deux branches descendent de `51a9d957`, exit 0 ; le commit de code de chacune est un ancetre de son sommet |
| `A` | ArchUnit | **vert sur les deux sommets et sur l'arbre des trois fusions** : 15 classes, 88 tests, 0 echec ; compte de regles 41 / 38 / 16 inchange ; aucune classe de production |
| `D` | Points d'attention | `DETTE_DE_FAMILLE` et `DETTE_DE_FETES` de `LP3` : **conformes a `ADR-0013`**, et plus strictes que `DETTE_DE_RANG`. `DETTE_D_AMORCE` : **non touchee** par `LP5` (section 3) |
| `C` | Conflits | **aucun** sur les trois paires `LP3`, `LP5`, p4-10. L'arbre qui les fusionne toutes est vert sur les gardes partages (section 4) |

## 1. Lignee

| Lot | `git merge-base --is-ancestor 51a9d957 <sommet>` | commit de code ancetre du sommet | `git log --oneline 51a9d957..<sommet>` |
|---|---|---|---|
| `LP3` `c8404173` | **0** | 0 (`8f25fd64`) | 2 commits `[LEA]` : `c8404173` (rapport), `8f25fd64` (branchement et test) |
| `LP5` `db5e0e6d` | **0** | 0 (`edf2b03b`) | 2 commits `[SOF]` : `db5e0e6d` (rapport), `edf2b03b` (paquet ecrit et branche, test) |
| p4-10 `fedd3ae9` | **0** | - | 4 commits `[MARC]` : `fedd3ae9`, `1d97832d`, `08500102`, `fbe90f8b` |

## 2. ArchUnit et diffs

### 2.1 Diffs

`git diff --stat 51a9d957 <sommet>` :

- **`LP3`** : `catalogue.yaml` (+6), `PaquetCe1CalendrierDatesTest.java` (+1132), cinq fixtures de contre-exemple, l'inventaire de `TexteAccentueExposeParLePortEntrantTest` (+14) et le rapport. 1418 insertions, **0 suppression**. Le paquet `ce1-calendrier-dates.yaml` (s53) n'est pas modifie : c'est `K2`.
- **`LP5`** : `catalogue.yaml` (+6), le paquet neuf `ce1-emc-droit-devoir.yaml`, `PaquetCe1EmcDroitDevoirTest.java` (+1276), une entree dans `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` (+4), neuf fixtures, l'inventaire (+10) et le rapport. 2113 insertions, **0 suppression**.

Sur les deux : `git diff --name-only 51a9d957 <sommet> -- '*/src/main/java/*'` vide ; aucun marqueur ajoute (`@Disabled`, `@Ignore`, `ArchIgnore`, `allowEmptyShould`, `assume`) ; 0 ligne Java supprimee. **Aucune assertion n'est affaiblie, puisque rien n'est retire.** Les deux tests de lot portent le litteral `src/main/` (`grep -c` : 2 et 4), ont leur dossier de jeu fautif et appellent `assertThatThrownBy` : le recensement d'`ADR-0013` les voit et les juge eprouves (`LesGardeFousSontMisALEpreuveTest` vert, 2.2). Ecart 39 : le motif est absent des fichiers des deux lots (`git grep`, exit 1).

### 2.2 Mesures

Copie propre de chaque sommet par `git archive`, dans mon repertoire de scratchpad. Commande : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,adapters/adapter-web,bootstrap -am test -Dtest=<perimetre> -Dsurefire.failIfNoSpecifiedTests=false`.

**Perimetre** :
- les 15 classes ArchUnit de `lecture-theo-lignee-palier-4.md` section 2 ;
- sept gardes partages : `LeRang...`, `LesFormesAcceptees...`, `ChaquePaquetDeMathematiquesDeCe1...`, `TexteAccentue...`, `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` (`ADR-0086`), `LesGardeFousSontMisALEpreuveTest` (`ADR-0013`) et `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` (`ADR-0023`) ;
- le test du lot.

Comptes lus dans les `TEST-*.xml`.

| Sommet | Duree | Code | ArchUnit, 15 classes | Gardes partages, 7 classes | Test du lot |
|---|---|---|---|---|---|
| `c8404173` (`LP3`) | 18:40:01 a 18:42:42 | **0** | **88 tests, 0 echec** | 78 tests, 0 echec (28, 4, 14, 10, 9, 10, 3) | `PaquetCe1CalendrierDatesTest` 50, 0 |
| `db5e0e6d` (`LP5`) | 18:42:42 a 18:45:14 | **0** | **88 tests, 0 echec** | 78 tests, 0 echec | `PaquetCe1EmcDroitDevoirTest` 64, 0 |

Compte de regles (methode d'`ADR-0104`) : **41, 38, 16** sur les deux sommets, ecart nul.

## 3. Les points d'attention

### 3.1 `LP3` : `DETTE_DE_FAMILLE` et `DETTE_DE_FETES`, conformes a `ADR-0013`

**Ce que les deux listes sont.** Chacune nomme des occurrences mesurees sur le paquet servi, au format `"<enonce> / <proposition>"`. Elle a un motif ecrit dans sa Javadoc (`CAL-CE1-5` : un distracteur hors des trois familles, une occurrence ; `CAL-CE1-4` : une date de fete en distracteur, deux occurrences), avec la correction que `nadia-prof` peut decider. Elles passent par un seul juge, `verifierLaDette`.

**Ce que `verifierLaDette` exige** (`PaquetCe1CalendrierDatesTest.java`, a `c8404173`) : l'ensemble des occurrences trouvees **egale** la dette. Une occurrence hors de la liste est rouge (*"hors de la dette nommee"*). Une entree que plus rien ne porte est rouge (*"dette perimee"*).

**Comparaison avec `DETTE_DE_RANG`.** Le modele porte un plafond et un `hasSizeLessThanOrEqualTo(PLAFOND_DE_DETTE)`. Ici il n'y a pas de plafond, mais l'egalite d'ensembles est plus stricte : la dette ne peut ni grandir sans un diff de la liste, ni pourrir, ni garder une place libre. C'est la propriete que l'amendement du 2026-08-15 d'`ADR-0013` demande a un cliquet (*"le plafond vaut exactement la taille de la dette"*), obtenue sans constante. **Il ne manque rien** : un plafond y serait une deuxieme ecriture de la meme borne.

**Les deux sens mordent.** Sur jeu fautif d'abord : *"dette perimee"* (`uneDetteDeFetesQuiNeDesigneePlusRienEstRefusee`, `uneDetteDeFamilleQuiNeDesigneePlusRienEstRefusee`), et occurrence nouvelle avec une dette vide. **Sur le paquet reel ensuite**, mesure ici. Copie de `c8404173` ou `DETTE_DE_FETES` perd une de ses deux entrees (`diff` : ces seules lignes), `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=PaquetCe1CalendrierDatesTest -Dsurefire.failIfNoSpecifiedTests=false`, 18:49 a 18:50. Resultat : **exit 1, 50 tests, 1 echec**, `cal4AucunAncrageEtAucuneDateDeFeteHorsDeLaDetteNommee`, message *"CAL-CE1-4 : une date de fete ou de fin d'aout hors de la dette nommee ... [Quelle est la veille du 1er aout ? / le 31 aout]"* (accents retires ici, presents dans le message).

**Ce que ces listes ne sont pas.** Ce ne sont ni des exemptions de balayage (`DETTE_DE_BALAYAGE`) ni des criteres enumeres verts par defaut (`CRITERES_ENUMERES_DECLARES`, `ADR-0039`) : elles n'ont rien a faire dans le recensement de `bootstrap`. **Elles se vident par la correction du paquet**, qui attend une decision de `nadia-prof` (`K2`). Tant qu'elle n'est pas prise, ce sont trois defauts de contenu servis, nommes et bornes, non corriges.

### 3.2 `LP5` : `DETTE_D_AMORCE` n'est pas touchee

- `git diff --stat 51a9d957 db5e0e6d -- bootstrap/src/test/java/com/recreo/archi/LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest.java` : **vide**. `git log --oneline 51a9d957..db5e0e6d -- <ce fichier>` : aucun commit.
- A `51a9d957`, la liste vaut `Set.of("cm1-emc-fait-opinion.yaml")` et `PLAFOND_DE_DETTE = 1` (`:142-144`) : une seule entree, inchangee.
- Le rouge du premier essai (clause 2, deux amorces repetees dans les distracteurs, rapport de `sofia-sm` lignes 67-69) a ete tenu **par le contenu** : deux formulations de plus dans une famille de distracteurs. Ni exemption, ni test modifie.
- La classe est **verte sur `db5e0e6d`** (9 tests, 0 echec, 2.2), donc sur le paquet neuf tel qu'il est servi.
- Ce que `sofia-sm` ecrit est juste et merite d'etre repris : le test du paquet seul (`-pl adapters/adapter-content -am`) etait vert, le rouge n'apparait que dans `bootstrap`. **Une mesure partielle d'un lot de contenu inclut `bootstrap`.**

## 4. Conflits attendus

`git merge-tree --write-tree --name-only --merge-base=51a9d957`, git 2.47.1 :

| Paire | Code | En conflit |
|---|---|---|
| `c8404173` x `db5e0e6d` | **0** | aucun |
| `c8404173` x `fedd3ae9` | **0** | aucun |
| `db5e0e6d` x `fedd3ae9` | **0** | aucun |

**Pourquoi `catalogue.yaml` ne se heurte pas cette fois.** `LP3` ajoute son entree en fin de fichier (hunk `@@ -968 +968,7`). `LP5` l'insere apres `bandit-manchot-regle-sanction-ce1`, avant `saisie-mots-invariables-ce1` (`@@ -962,2 +962,8`). Les ancrages sont distincts, et l'inventaire se fusionne seul. p4-10 ne touche aucun fichier des deux lots.

**L'arbre des trois fusions, mesure.** Un arbre sans conflit peut encore etre rouge. Le garde de p4-10 juge `nombreDeManches` sur le catalogue fusionne, et `LesGardeFousSontMisALEpreuveTest` voit ensemble les trois nouveaux balayages. J'ai donc construit l'arbre des trois fusions :
1. `git merge-tree --write-tree` de `LP3` et `LP5` ;
2. `git commit-tree` de cet arbre, avec les deux sommets pour parents. C'est un objet sans aucune reference, `git show-ref` ne le nomme pas ;
3. `git merge-tree --write-tree` de ce commit et de `fedd3ae9`, exit 0.

Copie par `git archive` de l'arbre resultant : 162 entrees au catalogue (160 + 2). Meme commande qu'en 2.2, perimetre augmente des deux tests de lot et de `ChaqueJeuDuCatalogueDureDixManchesSaufDerogationNommeeTest`, 18:45 a 18:47. Resultat : **exit 0**. ArchUnit 88 tests, 0 echec ; gardes partages 78, 0 ; `PaquetCe1CalendrierDatesTest` 50, 0 ; `PaquetCe1EmcDroitDevoirTest` 64, 0 ; garde de p4-10 7, 0. Les deux entrees neuves portent `nombreDeManches: 10`.

Ce que cette mesure n'est pas : le `verify` complet de chaque fusion (RF4), ni un arbre que `sofia-sm` fusionnera dans cet ordre.

## 5. Jobs de `ci.yml` lances pour ce fichier

- **`build`, mesures partielles** : sections 2.2, 3.1 et 4, toutes avec `-am` et leur perimetre nomme. Puis les quatre gardes de `squad/` sur ce fichier (resultat dans le message de commit).
- **`chemins`** : sur ce fichier, avant commit. **`prefixes`** : sur ma branche, avant commit.
- **`build` complet, `package`, `outils`** : non lances. Le `verify` complet est la porte de chaque fusion (RF4).
- **Le Done technique n'est pas prononce par moi.**
