# Rapport de marc-dev, palier 4, p4-10 : `S57-GARDE-NOMBRE-DE-MANCHES`

- **Auteur** : marc-dev (instance A), prefixe `[MARC]`.
- **Base de mesure** : `51a9d957` (sommet de `round-two` : LP1, LP2 et LP4 fusionnes).
- **Commit de code et de tests** : `fbe90f8b`. Ce rapport est un second commit, sans code.
- **Version mesuree** : la machine virtuelle des tests rapporte `java.version` 21.0.10 (propriete lue dans `adapters/adapter-content/target/surefire-reports/TEST-...DureDixManchesSaufDerogationNommeeTest.xml`) ; Maven a tourne sur le JDK de la toolchain `jdk 21`, 21.0.10 d'apres `outils/mvn-jdk21.sh`.

## 1. Ce qui est livre

Un garde, `ChaqueJeuDuCatalogueDureDixManchesSaufDerogationNommeeTest`, dans `adapters/adapter-content/src/test/java/com/recreo/adapter/content/`, et cinq catalogues fautifs sous `adapters/adapter-content/src/test/resources/contreexemple/ChaqueJeuDuCatalogueDureDixManchesSaufDerogationNommeeTest/`.

- Perimetre : `catalogue.yaml` lu la ou il vit (`src/main/resources`), comme `AucuneEntreeDuCatalogueNEnDoubleUneAutreTest`. Chaque entree produit une assertion, y compris une entree sans `nombreDeManches` (`ADR-0013` obligation 2).
- Liste : une constante `DEROGATIONS` du test, `record Derogation(int valeur, String decision)`, huit entrees (celles de `DEC-0121` section 3 et de l'amendement de la section 8, avec la valeur et la `DEC` de chacune). Aucune entree de plus, aucune de moins.
- Cinq assertions distinctes, chacune avec son message : entree sans valeur ; entree autre que 10 absente de la liste ; derogation dont la valeur change ; derogation qui porte 10 ; derogation qui ne designe plus aucune entree.
- Aucun fichier de production touche. `git diff 51a9d957 HEAD` ne liste que le test et ses cinq catalogues fautifs. `catalogue.yaml` et `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest.java` : diff vide (criteres 3 et 4). Aucun paquet n'est rougi.

## 2. Delta de tests

**+7 tests**, tous dans `ChaqueJeuDuCatalogueDureDixManchesSaufDerogationNommeeTest` (1 sur le catalogue de production, 5 jeux fautifs, 1 corrige), mesure sur la base `51a9d957`. Aucun test modifie ou retire. Le total sur l'arbre fusionne est a `sofia-sm`.

## 3. Morsures

Toutes les mesures partielles : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=ChaqueJeuDuCatalogueDureDixManchesSaufDerogationNommeeTest -Dsurefire.failIfNoSpecifiedTests=false`. Perimetre : reacteur `domain`, `application`, `adapter-web`, `adapter-content` ; 7 tests lances par execution. Le fichier mute est restaure apres chaque execution (`git status` ne montre plus `catalogue.yaml` ; le test restaure est identique par `cmp`).

Etat de depart, arbre non mute : 7 tests, 0 echec, plus `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` : 3 tests, 0 echec (10 tests lances ensemble).

| Mutation | Fichier | Critere | Resultat | Test rouge | Lances |
|---|---|---|---|---|---|
| M1 : `bandit-manchot-phrases-types-ce1` 10 vers 5 (equivalent de `C8`, qui visait `ce1-phrases-types`) | `catalogue.yaml` | 2 (a) | rouge, exit 1 | `chaqueJeuDuCatalogueDeProductionDureDixMancheSaufDerogationNommee` | 7, 1 echec |
| M2 : `saisie-mot-dicte-cp` 7 vers 6 | `catalogue.yaml` | 2 (b) | rouge, exit 1 | idem | 7, 1 echec |
| M3 : `designation-plan-de-la-classe` 5 vers 10 | `catalogue.yaml` | 2 (c), porte 10 | rouge, exit 1 | idem | 7, 1 echec |
| M4 : bloc `designation-symetrie-ce1` supprime | `catalogue.yaml` | 2 (c), n'existe plus | rouge, exit 1 | idem | 7, 1 echec |
| M5 : `nombreDeManches` supprime sur `designation-symetrie-ce1` | `catalogue.yaml` | derivation totale | rouge, exit 1 | idem | 7, 1 echec |
| M6 : liste du test, `designation-symetrie-ce1` 8 vers 9 | test | 2 (b) cote liste | rouge, exit 1 | idem | 7, 1 echec |
| M7 : `MANCHES_PAR_DEFAUT` 10 vers 9 | test | le garde lui-meme | rouge, exit 1 | 5 tests rouges sur 7 (production, `valeur-changee`, `derogation-a-dix`, derogation disparue, corrige) | 7, 5 echecs |

Jeux fautifs (`ADR-0013` obligation 3), absents de la production, executes par `assertThatThrownBy` sur `AssertionError` avec `hasMessageContaining` du nom de l'entree : `catalogue.yaml` (5 manches hors liste), `valeur-changee/`, `derogation-a-dix/`, `corrige/` avec une derogation de la liste que le catalogue ne porte pas, `sans-valeur/`. Le catalogue `corrige/` avec la liste fictive passe : c'est l'autre borne.

## 4. Gates lancees

Sur l'arbre propre au commit `fbe90f8b` (`git status --short` : 0 ligne), JDK 21.0.10.

| Job de `ci.yml` | Commande | Sortie |
|---|---|---|
| `build` (etape Verify) | `bash outils/mvn-jdk21.sh -B clean verify` a la racine | exit 0, `BUILD SUCCESS` ; reacteur : domain 120, application 31, adapter-web 523, adapter-content 1966, bootstrap 381 puis 6 (integration), 0 echec ; jeton `RF1` `s57-p4-jeton-verify` pris et supprime |
| `prefixes` | etapes de `ci.yml` rejouees sur `51a9d957..HEAD` : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-prefixes.sh`, `verifier-garde-fou-accent-test.sh`, `verifier-garde-fou-accent.sh` | tous exit 0 ; 0 commit touche le garde-fou de l'accent |
| `chemins` | `verifier-chemins-test.sh`, puis `git ls-files` filtre dans `verifier-chemins.sh` | exit 0 ; aucun chemin de poste de travail |
| `outils` | balayage des trois motifs de `ci.yml` | 13 juges lances (6 + 2 + 5), tous exit 0 |
| "Package du fat jar" | `mvn -B -pl bootstrap -am package` | **non lance** comme etape distincte. `verify` a produit `bootstrap/target/recreo.jar`, ce qui n'est pas la meme commande. |

Les jobs `prefixes`, `chemins` et `outils` ont ete lances avant l'ecriture de ce rapport ; ils sont a relancer sur le commit du rapport par qui annonce la porte (`chemins` lit ce fichier).

Aucun jar joue : aucun ecran, aucun item, aucune valeur du catalogue ne change.

## 5. Questions routees (je ne tranche pas)

- **Q1, `theo-archi` : la forme du garde.** J'ai cherche une forme ecrite dans `avis-theo-palier-4.md`, `lecture-theo-lignee-palier-4.md` et `arbitrage-nadia-palier-4.md` (recherches de `manches`, `p4-10`, `liste fermee`, `derogation` : aucun resultat sur le sujet). La story laisse la place de la liste, la lecture et le nom a `theo-archi` et au developpeur. J'ai pris la forme la plus proche du precedent voisin (`AucuneEntreeDuCatalogueNEnDoubleUneAutreTest`) : la liste en constante du test, le catalogue lu en `src/main`. Si la forme attendue est autre (liste dans un YAML de ressources de test, test dans `bootstrap`), le garde et ses cinq catalogues se deplacent sans changer de propriete.
- **Q2, `theo-archi` : `ADR-0013` obligation 1.** La liste des derogations n'est pas un perimetre, c'est une liste d'exemptions fermee des deux cotes ; son mode de defaillance est un rouge. Je n'ai ajoute le test ni a `DETTE_DE_BALAYAGE` ni a `CRITERES_ENUMERES_DECLARES` : il contient `src/main/`, donc le recensement de `LesGardeFousSontMisALEpreuveTest` le voit, et il a son jeu fautif et son `assertThatThrownBy`. A confirmer a la lecture de lignee.
- **Q3, `camille-po` : ce que le garde ne juge pas.** Il ne verifie pas que la `DEC` citee par une ligne existe, ni qu'elle nomme cette valeur. La colonne `decision` est une donnee relue par un humain, pas un invariant execute. Si le critere 1 ("le numero de la `DEC` qui nomme son motif") doit etre execute, c'est une clause de plus et un critere a ecrire.
- **Q4, `sofia-sm`.** La valeur `DEC-0121` portee par `designation-plan-de-la-classe`, `saisie-mot-dicte-cp` et `bandit-manchot-rapprocher-deux-oeuvres-cp` vient de la section 3, de la section 4 et de la section 8 de `DEC-0121`, telles que la story les cite. Je ne les ai pas rouvertes une par une dans les fiches de conformite.

## 6. Isolation

Repertoire de scratchpad : `s57-p4-marc-dev-a`, seul utilise (sorties de mesure, script de mutation, copie du test). Le repertoire de jeton `s57-p4-jeton-verify` a ete cree par `mkdir` puis supprime par `rm -r` apres le `verify`.

Controle de fin (racine du scratchpad, parent des worktrees) : dans le message de retour de marc-dev, avec commande et sortie.
