# s57 palier 4 - lecture de `theo-archi` : lignee, ArchUnit, fichiers partages et conflits attendus de `LP1`, `LP2`, `LP4`

- **Date** : 2026-10-22, 11:20 a 11:40 (UTC+02:00), heure lue par `date`.
- **Auteur** : `theo-archi`, transverse, ligne de la table d'isolation de `composition-palier-4.md` section 6.
- **Arbre** : `round-two` a `eb267ba7` (`git rev-parse --short round-two`, avant et apres les mesures). Sommets lus : `LP1` `s57-marc-fra-ce1-21` a `56e9fd1b`, `LP2` `s57-lea-mat-ce1-10` a `fc6ee0a1`, `LP4` `s57-sofia-emc-ce1-02` a `9f3fdd4b`.
- **Version** : JDK 21.0.10, propriete `java.version` des rapports `TEST-*.xml` de chaque mesure (19 rapports par sommet, une seule valeur) (`ADR-0071`).
- **Ce que ce fichier ne fait pas** : il ne fusionne rien, ne juge aucun contenu, ne prononce aucun Done, ne cree ni n'amende aucune `ADR` ni `DEC`. Il ne rejoue pas le `verify` complet (RF1, RF4 : c'est la porte de chaque fusion). Les reperes `L`, `A`, `P`, `C`, `R` n'appartiennent qu'a ce fichier.

## 0. Verdict

| Code | Point | Verdict |
|---|---|---|
| `L` | Lignee des trois branches | **les trois descendent de `eb267ba7`**, exit 0 chacune ; la branche d'A' (`LP4`, auteure `sofia-sm`) est lue ici, point 3 de 5.3 de `composition-palier-4.md` (section 1) |
| `A` | ArchUnit sur chaque sommet | **vert sur les trois** : 15 classes, 88 tests, 0 echec ; compte de regles inchange, 41 / 38 / 16 ; aucune classe de production ajoutee (section 2) |
| `P` | Fichiers partages | ajouts seuls, aucune assertion affaiblie. La seule ligne modifiee d'un garde partage (`LP1`, motif de `ce1-mots-invariables`) etait **imposee par le garde lui-meme** : mesure section 4.1 |
| `C` | Conflits de fusion attendus | `catalogue.yaml` sur les trois paires ; inventaire de `TexteAccentueExposeParLePortEntrantTest` sur la paire `LP1` x `LP4` seulement ; `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` se fusionne seul. Tous se resolvent par ajout des deux cotes (section 3) |
| `R` | Points routes | `LeRang...` non affaibli ; commentaire de `ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` faux depuis le palier 3, non bloquant ; ecart 39 **ne bloque aucune des trois branches** (section 4) |

## 1. Lignee

Commandes lancees depuis le checkout, en lecture seule. `merge-base --is-ancestor` rend 0 si `eb267ba7` est un ancetre du sommet.

| Lot | Commande | Code | `git log --oneline eb267ba7..<sommet>` |
|---|---|---|---|
| `LP1` | `git merge-base --is-ancestor eb267ba7 56e9fd1b` | **0** | `56e9fd1b`, `6554bf87`, `83035447`, `3d3d9382`, `c591a49b` : 5 commits `[MARC]` |
| `LP2` | `git merge-base --is-ancestor eb267ba7 fc6ee0a1` | **0** | `fc6ee0a1`, `37597642`, `7b2deccc`, `1221c2b2` : 4 commits `[LEA]` |
| `LP4` | `git merge-base --is-ancestor eb267ba7 9f3fdd4b` | **0** | `9f3fdd4b`, `28457159`, `c881172a` : 3 commits `[SOF]` |

En plus, lance dans chaque worktree de lot (lecture seule : `git rev-parse` et `git merge-base`) : `bash outils/verifier-lignee.sh` rend *"le worktree sur la branche '<branche>' descend de la lignee de round-two (ancetre commun trouve dans son historique)."*, **exit 0** pour les trois ; `git rev-parse --short HEAD` rend le sommet nomme ci-dessus ; `git status --short` ne rend aucune ligne dans aucun des trois. Ce n'est pas la lecture de RF10 : celle-ci se refait avant chaque fusion, sur le sommet fusionne.

**Pour `LP4`** : la lecture de lignee que 5.3 point 3 demande a `theo-archi` est celle-ci, exit 0. Le rapport qualite de `yanis-qa` et la lecture de `nadia-prof` restent les deux autres ecrits qu'elle attend avant fusion ; je n'en dis rien.

## 2. ArchUnit par sommet

**Methode.** Copie propre de chaque sommet par `git archive <sommet> | tar -x`, dans mon repertoire de scratchpad, hors de tout worktree de lot (RF7) ; aucun `target/` des lots n'est lu ni reconstruit. Commande, identique sur les trois copies, `-am` present :

```
bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,adapters/adapter-web,bootstrap -am test \
  -Dtest=<15 classes ArchUnit>,LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest,LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest,ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest,TexteAccentueExposeParLePortEntrantTest \
  -Dsurefire.failIfNoSpecifiedTests=false
```

**Perimetre** : les 15 classes porteuses de regles de `confirmation-archunit-palier-3.md` section 1 (`AssertionsWebTest` dans `adapter-web`, les 14 autres dans `bootstrap`), plus les quatre gardes partages de la section 3. Ces chiffres sont ceux des classes nommees, pas des modules. Comptes lus par l'attribut `tests` et `failures` des `TEST-*.xml`, pas par les lignes de console (un rapport texte ne garde qu'un des deux moteurs JUnit : la console dit 3 pour `LOrdreDesMatieresVientDeLaDonneeTest`, le rapport XML 4).

| Sommet | Duree | Code | ArchUnit, 15 classes | Gardes partages, 4 classes |
|---|---|---|---|---|
| `56e9fd1b` (`LP1`) | 11:22:30 a 11:25:11 | **0**, `BUILD SUCCESS` | **88 tests, 0 echec, 0 erreur, 0 ignore** | 56 tests, 0 echec (`LeRang...` 28, `LesFormesAcceptees...` 4, `ChaquePaquetDeMathematiques...` 14, `TexteAccentue...` 10) |
| `fc6ee0a1` (`LP2`) | 11:25:11 a 11:26:52 | **0**, `BUILD SUCCESS` | **88, 0, 0, 0** | 56, 0 |
| `9f3fdd4b` (`LP4`) | 11:26:53 a 11:28:25 | **0**, `BUILD SUCCESS` | **88, 0, 0, 0** | 56, 0 |

Detail ArchUnit, identique sur les trois sommets et classe par classe a `confirmation-archunit-palier-3.md:27` : `AssertionsWebTest` 2, `AbstractionsCiteesTest` 5, `AccesALEtatDeSessionTest` 2, `ArchitectureEnCouchesTest` 2, `ChargementDuContenuTest` 2, `DecouplageMecaniqueContenuTest` 13, `DifficulteTest` 3, `GestionDesErreursTest` 4, `LOrdreDesMatieresVientDeLaDonneeTest` 4, `LOrdreDesNiveauxResteUneDonneeTest` 5, `LeChoixDesJeuxResteDerriereLePortTest` 1, `LeSeulCookieEstLeMarqueurDePartieTest` 2, `LesReglesMordentTest` 39, `PureteDesCouchesTest` 2, `ScanDeComposantsTest` 2.

**Compte de regles** (methode d'`ADR-0104` : `git grep` au commit nomme, motifs `static final ArchRule` et `@ArchTest`, fichiers par `git grep -l`) : **41, 38, 16** a `eb267ba7`, `56e9fd1b`, `fc6ee0a1` et `9f3fdd4b`. Ecart nul ; 39 regles reelles dans 15 fichiers, meme lecture qu'au palier 3.

**Ce que ces mesures ne couvrent pas.** Les tests de lot (`PaquetCe1MotsInvariablesTest`, `PaquetCe1MassesContenancesTest`, `PaquetCe1EmcRegleSanctionTest`) et le reste du reacteur ne sont pas lances ici : c'est le `verify` complet de chaque fusion (RF4). Aucune mesure n'est faite sur un arbre fusionne : il n'en existe pas encore.

## 3. Lecture d'architecture des diffs

### 3.1 Aucune classe de production

`git diff --name-only eb267ba7 <sommet> -- '*/src/main/java/*'` ne rend **aucune ligne** pour les trois sommets. Sous `src/main`, seuls bougent `catalogue.yaml` (les trois), le paquet `ce1-mots-invariables.yaml` (`LP1`, reecrit en `type: saisie`) et le paquet neuf `ce1-emc-regle-sanction.yaml` (`LP4`). Le reste est test de lot, fixtures de contre-exemple et rapports. Conforme a `V1` d'`avis-theo-palier-4.md` : sans code de production, aucune regle ArchUnit n'est touchee.

Lignes ajoutees du diff `*.java` portant `@Disabled`, `@Ignore`, `ArchIgnore`, `allowEmptyShould` ou `assume` : **aucune**, sur les trois. Lignes supprimees du diff `*.java` : 4 pour `LP1` (lues en 4.1), 0 pour `LP2` et `LP4`.

### 3.2 Les fichiers partages

| Fichier | `LP1` | `LP2` | `LP4` |
|---|---|---|---|
| `catalogue.yaml` | +6, `saisie-mots-invariables-ce1` | +6, `bandit-manchot-masses-contenances-ce1` | +6, `bandit-manchot-regle-sanction-ce1` |
| inventaire de `TexteAccentueExposeParLePortEntrantTest` | +10 (identifiant, titre, consigne, competence) | +12 (idem) | +10 (idem) |
| `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` | motif d'une entree existante reecrit (+6 -3) | intact | +4, une entree neuve |
| `LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest` | une entree ajoutee a la liste en egalite (+4 -1), prevue par mon avis (`avis-theo-palier-4.md`, ligne `LP1`) | intact | intact |

`catalogue.yaml` : 157 entrees a `eb267ba7`, 158 a chaque sommet (`grep -c "^  - id:"`), **160 apres les trois fusions**.

### 3.3 Conflits attendus

**Methode** : `git merge-tree --write-tree --name-only --merge-base=eb267ba7 <a> <b>` sur chaque paire, git 2.47.1. Cette commande ecrit des objets non references dans la base d'objets, **aucune reference** : `round-two` reste a `eb267ba7`. Exit 1 = au moins un conflit.

| Paire | Code | En conflit | Fusionne seul |
|---|---|---|---|
| `LP1` x `LP2` | 1 | `catalogue.yaml` | inventaire de `TexteAccentue...` |
| `LP1` x `LP4` | 1 | `catalogue.yaml` ; inventaire de `TexteAccentue...` | `LeRangDeLaBonneReponse...` |
| `LP2` x `LP4` | 1 | `catalogue.yaml` | inventaire de `TexteAccentue...` |

**Cause, la meme partout** : chaque lot ajoute son entree en fin de `catalogue.yaml`, apres `ce1-emc-biens-communs` ; `LP1` et `LP4` ajoutent leur identifiant et leur titre au meme ancrage de l'inventaire (apres `bandit-manchot-biens-communs-ce1` et apres `"Les biens de tous"`), `LP2` a des ancrages voisins mais distincts. **Resolution : ajout des deux cotes, aucune ligne supprimee** (RF3). Qu'aucun garde ne juge l'ordre des entrees de ces deux fichiers est **non verifie** ici ; `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` est une `Map`, sans ordre juge. Ce que je n'ai pas fait : la fusion a trois en sequence, qui demande des commits ; les paires nomment tous les fichiers en jeu, puisque les trois diffs ne se recoupent que sur ces quatre fichiers. A verifier apres chaque resolution : `git diff-tree --cc <fusion>` ne montre aucune ligne retiree d'un cote ou de l'autre (`avis-theo-palier-4.md` 3.3).

## 4. Points routes

### 4.1 `LP1` et `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` : non affaibli

**Ce qui a change** : le seul texte du motif de l'entree `ce1-mots-invariables` de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`. Ni le code d'une assertion, ni la liste des cles, ni un seuil. L'ancien motif disait *"24 items sur 24 portent des propositions textuelles : aucune des 72 propositions ..."* ; le nouveau dit *"24 items sur 24 sont des items de saisie : ils ne presentent aucune proposition ... 24 items, aucun distracteur"*, sur le modele de `cp-mot-dicte` (`LeRang...Test.java:671-676` a `eb267ba7`).

**Le changement etait impose, mesure sur le materiau reel.** Copie de `56e9fd1b` ou ce seul fichier est remis a sa version de `eb267ba7` (`diff` : les seules lignes differentes sont celles du motif), `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest -Dsurefire.failIfNoSpecifiedTests=false`, 11:28:26 a 11:29:30 : **exit 1, 28 tests, 1 echec**, `chaqueCompteEcritDansUnMotifVautLeCompteMesureSurLePaquet`, message *"paquet ce1-mots-invariables : le motif d'exemption ecrit "72 propositions", le paquet servi en porte 0"*. Le paquet passe en saisie (`type: saisie`, 24 items, aucun `distracteurs`) ; un item de saisie n'est pas une `Question` et compte 0 proposition (`LeRang...Test.java:1842-1845`, numeros a `eb267ba7`, decales de 3 a `56e9fd1b`). Garder l'ancien texte etait rouge.

**Ce que le garde continue de refuser sur cette entree**, verifie par lecture de `verifierQuAucunPaquetNEstEcarteEnSilence` (`:1730-1785`, a `eb267ba7`) et par la mesure verte de la section 2 : un paquet ecarte du calcul sans declaration ; une declaration perimee si le paquet revient a des propositions numeriques ; une declaration fantome ; un motif vide ; un compte d'items faux (le nouveau motif ecrit *"24 items"* deux fois, et les deux sont recomptes). Ce qu'il ne compte plus pour cette entree : un nombre de propositions, puisqu'il n'y en a plus. C'est l'etat de `cp-mot-dicte` depuis s26. **Verdict : l'assertion n'est pas affaiblie.** L'ecart a la liste `K2` est un ecart de perimetre de story, a `camille-po` ; l'architecture n'a rien a y redire.

### 4.2 `ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` : commentaire faux, non bloquant

Le commentaire `:192-195` dit *"Les cinq paquets sont degrades et non branches au catalogue."* Il est **deja faux a `eb267ba7`** : `ce1-nombres-jusqu-a-1000` est branche depuis `bd99abcf` (palier 3, `git log -S"paquet: ce1-nombres-jusqu-a-1000" -- catalogue.yaml`). `LP2` le rend faux pour un deuxieme paquet (`ce1-masses-contenances` : 0 entree a `eb267ba7`, 1 a `fc6ee0a1`). Aucune assertion ne lit ce commentaire : les entrees de l'`enum` sont des donnees, et la classe est verte sur les trois sommets (14 tests). **Ne bloque aucune fusion.** Il est de la famille des commentaires de `src/` (OV7 de `composition-palier-4.md`) : un fait date qui pourrit au lot suivant. La correction qui ne pourrit pas date le fait (*"a s53, les cinq paquets etaient degrades et non branches"*) au lieu de le reporter a chaque branchement. Le fichier n'est dans le `K2` d'aucun lot du palier : le geste est a `sofia-sm` (engagement des commentaires de `src/`), pas a `lea-dev` dans `LP2`.

### 4.3 Ecart 39 (`verifier-chemins.sh`) : ne bloque pas les trois branches

- `git grep -n -E '[\\/][A-Za-z]--[0-9A-Za-z]' <sommet> -- <fichiers ajoutes ou modifies par le lot>` : **aucune ligne**, exit 1, pour les trois sommets.
- Job `chemins` de `ci.yml` rejoue sur chaque copie : `bash .github/scripts/verifier-chemins-test.sh` exit 0 (*"Tous les cas passent ..."*), puis `git ls-tree -r --name-only <sommet>` sans `verifier-chemins-test.sh`, passe a `bash .github/scripts/verifier-chemins.sh` : exit 0 (*"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*), sur les trois.
- La dette reste celle d'`avis-theo-palier-4.md` section 5 : `git grep -c -E '[\\/][A-Za-z]--[0-9A-Za-z]' eb267ba7` rend 2 lignes dans `releve-relecteur-cloture-palier-3.md`. Le commit (amendement d'`ADR-0010`, motif, cas de test sur la ligne reelle) reste a moi et n'est pas fait ici.

## 5. Jobs de `ci.yml` et mesures lances pour ce fichier

- **`build`, mesure partielle** : section 2 et 4.1, sur les sommets des lots. Sur ce fichier, dans mon worktree `s57-theo-p4` a `eb267ba7` plus ce seul fichier non suivi : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest,AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest,ToutesLesReferencesDeDecisionResolventTest,LesGardeFousSontMisALEpreuveTest -Dsurefire.failIfNoSpecifiedTests=false`, perimetre ces quatre classes, 11:33 a 11:35 : **28 tests, 0 echec**, exit 0. Seule cette section a ete retouchee apres.
- **`chemins`** : sur les trois sommets (4.3) ; sur ce fichier, `verifier-chemins.sh` exit 0, *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*
- **`build` complet, `package`, `prefixes`, `outils`** : non lances par moi pour les lots ; le `verify` complet est la porte de chaque fusion (RF4).
- **Le Done technique n'est pas prononce par moi.**
