# s57 palier 4 - lecture de `theo-archi` : lignee et ArchUnit de `LP6`, relecture de lignee de `LP5` et `LP3` rejoues

- **Date** : 2026-10-22, 23:35 a 23:55 (UTC+02:00), heure lue par `date`.
- **Auteur** : `theo-archi`, transverse. Lecture de lignee que `composition-palier-4.md` 5.3 point 3 demande pour une branche d'A', dont l'auteure fusionne, et RF10.
- **Arbre** : `round-two` a `a65341cb` (`git rev-parse --short round-two`). `LP6` : branche `s57-sofia-emc-ce1-04`, sommet `cbfb152e`, `git status --short` du worktree vide. Rien n'est ecrit dans les worktrees des lots.
- **Version** : JDK 21.0.10, propriete `java.version` des 25 rapports `TEST-*.xml` de la mesure 2.2, une seule valeur (`ADR-0071`). git 2.47.1.
- **Ce que ce fichier ne fait pas** : il ne fusionne rien, ne juge aucun contenu, ne prononce aucun Done, ne cree ni n'amende aucune `ADR`. Les reperes `L`, `A`, `R`, `I` n'appartiennent qu'a ce fichier.

## 0. Verdict

| Code | Point | Verdict |
|---|---|---|
| `L` | Lignee de `LP6` | **saine** : `a65341cb` est ancetre de `cbfb152e` (exit 0), `verifier-lignee.sh` exit 0, deux commits `[SOF]` lineaires |
| `A` | ArchUnit et `ADR-0086` clause 1 | **vert** : 15 classes, 88 tests, 0 echec ; compte de regles 41 / 38 / 16 inchange ; aucune classe de production. La clause 1 est verte sur le paquet neuf et **mord** quand on le casse (section 3) |
| `I` | Identifiants | **aucun identifiant non alloue** : garde vert (10, 0) et lecture du diff (section 4) |
| `R` | Lignee de `LP5` `fdeff890` et `LP3` `003fd4fb` | **saine** : chaque sommet descend du sommet que j'avais lu, sans reecriture ; les commits du rejeu sont lineaires et du bon prefixe ; aucune assertion affaiblie (section 5) |

## 1. Lignee de `LP6`

Lancees dans le worktree de la branche, 23:35 :

| Commande | Sortie | Code |
|---|---|---|
| `git merge-base --is-ancestor a65341cb cbfb152e` | (aucune) | **0** |
| `bash outils/verifier-lignee.sh` | `le worktree sur la branche 's57-sofia-emc-ce1-04' descend de la lignee de round-two (ancetre commun trouve dans son historique).` | **0** |
| `git merge-base round-two HEAD` | `a65341cb7fdc77ee6fbad40c7801ce6eff767318` | 0 |

`git log --format='%h %p %s' a65341cb..cbfb152e` : deux commits, chacun d'un seul parent.

- `e85a93fe` (parent `a65341cb`) `[SOF]` : paquet ecrit et branche, `PaquetCe1EmcInteretCommunTest` ;
- `cbfb152e` (parent `e85a93fe`) `[SOF]` : rapport de `LP6`.

La base de merge est le sommet courant de `round-two` : la branche n'a pas a fusionner `round-two` avant son `verify` (RF4).

## 2. ArchUnit et diff

### 2.1 Diff

`git diff --numstat a65341cb cbfb152e` : 13 fichiers, **1986 insertions, 0 suppression**. Sous `src/main`, seuls `catalogue.yaml` (+6, `bandit-manchot-interet-commun-ce1`, `nombreDeManches: 10`) et le paquet neuf `ce1-emc-interet-commun.yaml` (+210). `git diff --name-only a65341cb cbfb152e -- '*/src/main/java/*'` : vide. Lignes ajoutees portant `@Disabled`, `@Ignore`, `ArchIgnore`, `allowEmptyShould` ou `assume` : aucune. Aucun fichier sous `bootstrap/src/test/java/com/recreo/archi/` n'est touche (`git log --oneline a65341cb..cbfb152e -- <ce repertoire>` : vide), donc ni `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` ni `DETTE_D_AMORCE`.

Fichiers partages : `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (+4, une entree neuve ; git presente l'ajout entre la cle `ce1-emc-droit-devoir` et sa valeur parce que les deux motifs sont identiques, l'effet est une entree de plus) ; inventaire de `TexteAccentueExposeParLePortEntrantTest` (+10 : identifiant, titre, competence). Le motif de l'entree neuve (24 items, 72 propositions, aucune numerique) est exact : 24 `bonneReponse:` et 24 `distracteurs:` de deux entrees dans le paquet, aucune proposition ne commence par un chiffre.

### 2.2 Mesure

Copie propre de `cbfb152e` par `git archive`, dans mon repertoire de scratchpad (RF7). 23:40:44 a 23:46:05 :

```
bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,adapters/adapter-web,bootstrap -am test \
  -Dtest=<perimetre> -Dsurefire.failIfNoSpecifiedTests=false
```

**Code 0, `BUILD SUCCESS`.** Comptes lus dans les `TEST-*.xml` (attributs `tests`, `failures`, `errors`, `skipped`), pas dans la console.

| Perimetre | Tests | Echecs, erreurs, ignores |
|---|---|---|
| 15 classes ArchUnit (`lecture-theo-lignee-palier-4.md` section 2) | **88** (2, 5, 2, 2, 2, 13, 3, 4, 4, 5, 1, 2, 39, 2, 2) | 0, 0, 0 |
| Sept gardes partages : `LeRang...` 28, `LesFormesAcceptees...` 4, `ChaquePaquetDeMathematiquesDeCe1...` 14, `TexteAccentue...` 10, `LaFormeDesPropositions...` (`ADR-0086`) 9, `LesGardeFousSontMisALEpreuveTest` (`ADR-0013`) 10, `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` 3 | **78** | 0, 0, 0 |
| `ChaqueJeuDuCatalogueDureDixManchesSaufDerogationNommeeTest` | 7 | 0, 0, 0 |
| `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (RF8) | 10 | 0, 0, 0 |
| `PaquetCe1EmcInteretCommunTest` | 65 | 0, 0, 0 |

Compte de regles (methode d'`ADR-0104` : `git grep`, motifs `static final ArchRule` et `@ArchTest`, fichiers par `git grep -l`) : **41, 38, 16** a `a65341cb` et a `cbfb152e`, ecart nul.

Ce que cette mesure n'est pas : le `verify` complet de la fusion (RF4).

## 3. `ADR-0086` clause 1 sur le paquet neuf

**Pourquoi ce paquet est exactement la population de la clause.** Ses 24 bonnes reponses sont deux textes de quatre mots : *"pour toute la classe"* (13) et *"pour une seule personne"* (11). Chaque item porte les deux formules et un nombre d'eleves. Il n'existe donc que trois moules de propositions, et chacun regroupe huit items. La clause 1 interdit que les items d'un meme moule portent tous la meme bonne reponse.

**Ce qui est servi.** Regroupement des 24 items par ensemble de propositions et bonne reponse, lu sur le paquet a `cbfb152e` :

| Troisieme proposition | *pour toute la classe* | *pour une seule personne* |
|---|---|---|
| *pour deux eleves* | 4 | 4 |
| *pour quelques eleves* | 4 | 4 |
| *pour trois eleves* | 5 | 3 |

Chaque moule porte les deux bonnes reponses : la clause est tenue, et le garde est vert (9, 0, en 2.2).

**La clause mord ce paquet.** Sur une seconde copie de `cbfb152e`, les trois items *"pour trois eleves"* a reponse *"pour une seule personne"* (lignes 100, 136, 172) recoivent *"pour toute la classe"* en reponse, la formule echangee en distracteur ; `diff` ne rend que ces six lignes. `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest -Dsurefire.failIfNoSpecifiedTests=false`, 23:49:30 a 23:51:38 : **exit 1, 9 tests, 1 echec**, `leContenuServiEstConforme`, message *"ce1-emc-interet-commun.yaml : les items [3, 6, 9, 12, 15, 18, 21, 24] presentent le meme moule de propositions et portent tous la meme bonne reponse "pour toute la classe" ... (SCI-CM1-17 ... ; ADR-0086, clause 1)"*.

Le vert de 2.2 depend donc de la repartition des reponses dans chaque moule, pas d'un balayage qui manquerait le paquet. **Ce qui reste a `nadia-prof`** : qu'une tache a deux reponses possibles et trois moules soit assez variee, et que chaque scene n'ait qu'une lecture (`INT-CE1-1`, `EMC-5`). La clause ne juge ni l'un ni l'autre.

## 4. Identifiants

`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` est vert sur `cbfb152e` (10, 0, en 2.2). Lecture du diff en plus : identifiants de condition cites dans les lignes ajoutees (`git diff a65341cb cbfb152e`, lignes `+`, motif `[A-Z]{2,5}(-[A-Z0-9]{1,5})*-[0-9]+`, hors `ADR-` et `DEC-`), cherches dans `squad/decisions/REGISTRE-identifiants.md` a `cbfb152e`. Les 16 identifiants de condition y sont tous : `EMC-1`, `EMC-5`, `EMC-6`, `FER-1`, `FER-2`, `INT-CE1-1`, `INT-CE1-2`, `LON-CE1-1`, `LON-CE1-2`, `LON-CE1-5`, `NRC-CE1-1`, `TRV-CE1-1`, `TRV-CE1-2`, `TRV-CE1-5`, `TRV-CE1-6`, `TYP-1`. `INT-CE1-1` et `INT-CE1-2` sont alloues a `ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`, la source que cite le paquet. Les trois autres correspondances du motif ne sont pas des identifiants de condition : `EMC-CE1-01` et `EMC-CE1-04` sont des lignes du referentiel (`competences-ce1.md`), `BR-EMC-CE1-04` est un fragment de `S57-BR-EMC-CE1-04`. `DEC-0120` et `DEC-0121` existent sous `squad/decisions/produit/`.

## 5. Relecture : lignee de `LP5` et `LP3` apres rejeu

`planning.md` (amendement de la ligne de `LP5`) dit l'ecart : ma lecture `lecture-theo-lignee-palier-4-lp3-lp5.md` porte sur `db5e0e6d` et `c8404173`, les sommets fusionnes sont `fdeff890` et `003fd4fb`. Je relis ici la lignee de ces deux sommets, apres leur fusion.

| Commande | Code |
|---|---|
| `git merge-base --is-ancestor db5e0e6d fdeff890` | **0** |
| `git merge-base --is-ancestor c8404173 003fd4fb` | **0** |
| `git merge-base --is-ancestor 51a9d957 fdeff890` | 0 |
| `git merge-base --is-ancestor 51a9d957 003fd4fb` | 0 |
| `git merge-base --is-ancestor fdeff890 a65341cb` | 0 |
| `git merge-base --is-ancestor 003fd4fb a65341cb` | 0 |
| `bash outils/verifier-lignee.sh` dans le worktree de `s57-sofia-emc-ce1-03` (a `59ec3f2c`, `git status --short` vide) | **0**, *"... la branche 's57-sofia-emc-ce1-03' descend de la lignee de round-two ..."* |
| `bash outils/verifier-lignee.sh` dans le worktree de `s57-lea-mat-ce1-11` (a `8479e4ed`, `git status --short` vide) | **0**, *"... la branche 's57-lea-mat-ce1-11' descend de la lignee de round-two ..."* |

Les deux premieres lignes sont celles qui comptent : le sommet que j'avais lu est un ancetre du sommet fusionne. **Le rejeu s'est ajoute par-dessus, sans reecriture ni rebase.**

Commits du rejeu, chacun d'un seul parent :

- `LP5`, `db5e0e6d..fdeff890` : quatre commits `[SOF]` (`5b9bb648`, `ebdfa265`, `84064ce9`, `fdeff890`). C'est le compte que `planning.md` annonce ;
- `LP3`, `c8404173..003fd4fb` : deux commits `[LEA]` (`f863fa7a`, `003fd4fb`).

Les fusions dans `round-two` sont des commits a deux parents : `1f7c91b5` (`LP3`), `b9be0f99` (`round-two` fusionne dans `LP5`, premier parent `fdeff890`) et `a4d8efb8` (`LP5`).

**Ce que le rejeu change, lu pour la lignee et non pour le contenu.**

- `LP5` : 308 insertions, 11 suppressions. Les deux lignes Java retirees sont remplacees par des versions plus strictes : le motif `DROIT_CONDITIONNEL` gagne cinq alternatives, et le filtre d'une epreuve sur fixture exclut trois sondes neuves de plus.
- `LP3` : 162 insertions, 23 suppressions. Les 12 lignes Java retirees sont deux Javadoc reecrites (*"Dette"* devient *"Occurrence admise"*, sans retrait d'entree) et un `nombreEnTete` generalise en `valeurDe`. Elles s'accompagnent de deux juges neufs, chacun avec son epreuve sur fixture.

Pour les deux : aucun fichier sous `*/src/main/java/*` ; seul bouge le paquet du lot sous `src/main`. Aucun marqueur ajoute. **Aucune assertion n'est affaiblie.**

**Ce que cette relecture ne couvre pas.** Je n'ai pas relance ArchUnit ni les gardes partages sur `fdeff890` et `003fd4fb`. Le seul jugement technique de ces sommets reste le `verify` complet de leur fusion, que `planning.md` renvoie a `RAPPORT.md` et `cloture-palier-4.md`. Ma mesure 2.2 sur `cbfb152e` contient les deux sommets, puisque `a65341cb` les contient, et elle est verte sur son perimetre.

## 6. Jobs de `ci.yml` lances pour ce fichier

- **`build`, mesures partielles** : sections 2.2 et 3, avec `-am` et leur perimetre nomme.
- **`chemins`** : sur ce fichier, avant commit. **`prefixes`** : sur ma branche, avant commit.
- **`build` complet, `package`, `outils`** : non lances. Le `verify` complet est la porte de la fusion (RF4).
- **Le Done technique n'est pas prononce par moi.**
