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

## 6. Amendement du 2026-10-22, 13:25 (UTC+02:00) : relecture sur les sommets corriges

Les trois lots ont change de sommet apres leurs corrections (message de l'agent lanceur). **Les sections 1 a 5 restent vraies pour les anciens sommets et ne sont pas reecrites** ; cette section les remplace pour les nouveaux. Meme methode, meme isolation. `git rev-parse --short <branche>` rend les sommets ci-dessous ; `round-two` reste a `eb267ba7`.

### 6.1 Lignee

| Lot | Nouveau sommet | `git merge-base --is-ancestor eb267ba7 <sommet>` | `... <ancien sommet> <sommet>` | `git log --oneline eb267ba7..<sommet>` |
|---|---|---|---|---|
| `LP1` | `24a9315b` | **0** | 0 (`56e9fd1b`) | 7 commits `[MARC]` : `24a9315b`, `ea34c750`, puis les 5 de la section 1 |
| `LP2` | `c29d1d26` | **0** | 0 (`fc6ee0a1`) | 6 commits `[LEA]` : `c29d1d26`, `a733e582`, puis les 4 de la section 1 |
| `LP4` | `1152fdc9` | **0** | 0 (`9f3fdd4b`) | 4 commits `[SOF]` : `1152fdc9`, puis les 3 de la section 1 |

Les nouveaux sommets descendent des anciens : aucune reecriture d'historique.

### 6.2 Diffs depuis les anciens sommets

`git diff --name-status <ancien> <nouveau>` :

- `LP1` : le paquet `ce1-mots-invariables.yaml`, `PaquetCe1MotsInvariablesTest.java` (+91, 0 ligne Java supprimee), une fixture neuve `ce1-mots-invariables-fautif-espaces.yaml`, la fixture `-fautif.yaml`, son rapport.
- `LP2` : le paquet `ce1-masses-contenances.yaml`, `PaquetCe1MassesContenancesTest.java`, la fixture `ce1-masses-fautif.yaml`, `rapport-lea-dev-palier-3.md`.
- `LP4` : le paquet `ce1-emc-regle-sanction.yaml`, `PaquetCe1EmcRegleSanctionTest.java`, la fixture `ce1-emc-fautif.yaml`.

**Aucun fichier partage n'a bouge depuis les anciens sommets** : ni `catalogue.yaml`, ni l'inventaire, ni `LeRang...`, ni `LesFormesAcceptees...`. La section 3.2 vaut donc telle quelle. **Aucune classe de production** : `git diff --name-only eb267ba7 <sommet> -- '*/src/main/java/*'` est vide sur les trois. **Aucun marqueur** : pas de `@Disabled`, `@Ignore`, `ArchIgnore`, `allowEmptyShould` ni `assume` dans les lignes ajoutees depuis `eb267ba7`. **Aucun `@Test` supprime** depuis les anciens sommets.

Lignes Java supprimees depuis l'ancien sommet, lues une a une, toutes dans le test du lot :

- **`LP2`, 8 lignes** : la clause `MAS-CE1-6` sautait l'item du demi-kilogramme (`continue`) ; elle le juge maintenant, en admettant pour lui seul un distracteur au double (`demi && ecrit == 2 * bonne`). Les autres items gardent les memes formes admises. C'est une extension du perimetre, avec un test neuf qui la fait mordre sur le jeu fautif (`...DemiKilogrammeQuiNEstPasUneErreurDeFacteur`). Les autres ajouts sont des gardes neufs (`LON-CE1-1`, `LON-CE1-2`, `TYP-1`). **Non affaibli.**
- **`LP4`, 24 lignes**, deux natures :
  - **plus stricte** : le geste reparateur se lit dans la seule phrase de l'adulte, et non plus dans tout l'enonce, avec des racines ancrees en debut de mot (*"`rendr` ne se lit plus dans `prendre`"*). La liste des mots refuses s'allonge. Plusieurs gardes neufs s'ajoutent (la question reprend le geste, l'adulte de l'indice est celui de l'enonce, l'indice ne donne pas le critere du CP, seuils du pli).
  - **redefinie** : les familles de distracteurs. Les anciennes `(d)` *"changer ou supprimer la regle"* et `(e)` *"cacher l'acte"* sont retirees. Les familles deviennent `(d)` vengeance, `(e)` plaire a l'adulte, `(g)` laisser l'acte de cote, et la cible `faute` est neuve. Les paires admises par cible changent en consequence. Deux racines s'ajoutent aux gestes admis (`relir`, `pos(er|e)`). Le mecanisme reste le meme : un distracteur hors de toute famille rend `'?'` et le test echoue.
  - **Pas un affaiblissement d'architecture**, puisque c'est le test d'un lot qui suit son contenu corrige. **Que les familles redefinies soient celles du refus de `nadia-prof` (M1 a M5) n'est pas a moi** : c'est elle qui le lit.

### 6.3 ArchUnit par nouveau sommet

Copie propre par `git archive <sommet> | tar -x` dans mon repertoire de scratchpad. **Commande et perimetre identiques a la section 2** (`-pl adapters/adapter-content,adapters/adapter-web,bootstrap -am test`, 15 classes ArchUnit et les 4 gardes partages). Comptes lus dans les `TEST-*.xml`. JDK 21.0.10 (`java.version`, 19 rapports par sommet, une seule valeur).

| Sommet | Duree | Code | ArchUnit, 15 classes | Gardes partages, 4 classes |
|---|---|---|---|---|
| `24a9315b` (`LP1`) | 13:16:18 a 13:18:44 | **0**, `BUILD SUCCESS` | **88 tests, 0 echec, 0 erreur** | 56 tests, 0 echec (`LeRang...` 28, `LesFormesAcceptees...` 4, `ChaquePaquetDeMathematiques...` 14, `TexteAccentue...` 10) |
| `c29d1d26` (`LP2`) | 13:18:44 a 13:20:59 | **0**, `BUILD SUCCESS` | **88, 0, 0** | 56, 0 |
| `1152fdc9` (`LP4`) | 13:20:59 a 13:23:16 | **0**, `BUILD SUCCESS` | **88, 0, 0** | 56, 0 |

Compte de regles (methode d'`ADR-0104`) : **41, 38, 16** sur les trois nouveaux sommets, ecart nul. `LeRang...` est vert sur `1152fdc9` : le motif de `ce1-emc-regle-sanction` (*"24 items ... 72 propositions"*) vaut toujours le paquet corrige. Les tests de lot ne sont pas lances ici (RF4).

### 6.4 Conflits attendus

`git merge-tree --write-tree --name-only --merge-base=eb267ba7` sur les trois paires de nouveaux sommets donne le meme resultat que la section 3.3 :

| Paire | Code | En conflit | Fusionne seul |
|---|---|---|---|
| `24a9315b` x `c29d1d26` | 1 | `catalogue.yaml` | inventaire |
| `24a9315b` x `1152fdc9` | 1 | `catalogue.yaml` ; inventaire | `LeRang...` |
| `c29d1d26` x `1152fdc9` | 1 | `catalogue.yaml` | inventaire |

`catalogue.yaml` compte 158 entrees a chaque sommet, **160 apres les trois fusions**. La resolution reste l'ajout des deux cotes (RF3).

### 6.5 Points routes, sur les nouveaux sommets

- **4.1 (`LP1`, `LeRang...`)** : inchange, le fichier n'a pas bouge depuis `56e9fd1b`.
- **4.2** : inchange, le fichier n'a pas bouge.
- **4.3 (ecart 39)** : `git grep -E '[\/][A-Za-z]--[0-9A-Za-z]'` sur les fichiers ajoutes ou modifies de chaque lot depuis `eb267ba7` rend exit 1, aucune ligne. `verifier-chemins.sh` sur ces fichiers rend exit 0. **Ne bloque toujours aucune des trois branches.**

### 6.6 Jobs lances pour cet amendement

- **`build`, mesure partielle** : 6.3, puis les quatre gardes de `squad/` de la section 5 sur ce fichier amende (resultat dans le message de commit).
- **`chemins`** : ce fichier, avant commit.
- **`build` complet, `package`, `outils`** : non lances.

## 7. Amendement du 2026-10-22, 14:41 (UTC+02:00) : troisieme passage, `LP1` et `LP4` corriges, branche de `camille-po`

Nouveaux sommets annonces par l'agent lanceur : `LP1` `f8d5d875` (avant `24a9315b`), `LP4` `41203188` (avant `1152fdc9`). `LP2` reste a `c29d1d26`. Branche `s57-camille-p4` a `4a49c3f3`, lue sans rien ecrire. `git rev-parse --short` rend ces quatre sommets, et `round-two` reste a `eb267ba7`. Meme methode et meme isolation qu'en section 6. **Les sections 1 a 6 ne sont pas reecrites.**

### 7.1 Lignee

| Branche | Sommet | `merge-base --is-ancestor eb267ba7` | ancien sommet ancetre | `git log --oneline eb267ba7..<sommet>` |
|---|---|---|---|---|
| `LP1` | `f8d5d875` | **0** | 0 (`24a9315b`) | 9 commits `[MARC]` : `f8d5d875`, `8c76b1c7`, puis les 7 de 6.1 |
| `LP4` | `41203188` | **0** | 0 (`1152fdc9`) | 5 commits `[SOF]` : `41203188`, puis les 4 de 6.1 |
| `s57-camille-p4` | `4a49c3f3` | **0** | - | 1 commit `[CAM]` : `4a49c3f3` |

### 7.2 Diffs depuis les anciens sommets

- **`LP1`** (`git diff --stat 24a9315b f8d5d875`) : le paquet `ce1-mots-invariables.yaml` (item 12, +7 -7) et son rapport. Aucun fichier Java.
- **`LP4`** (`git diff --stat 1152fdc9 41203188`) : le paquet, `PaquetCe1EmcRegleSanctionTest.java` (26 lignes Java supprimees, aucun `@Test` supprime) et la fixture `ce1-emc-fautif.yaml`.

Sur les deux sommets, depuis `eb267ba7` : aucun fichier sous `src/main/java`, aucun marqueur ajoute (`@Disabled`, `@Ignore`, `ArchIgnore`, `allowEmptyShould`, `assume`), aucun fichier partage modifie depuis la section 6.

### 7.3 `LP4` : une garantie est perdue, et une seule

Je lis chaque changement du test du lot en me demandant ce qu'il interdisait et ce qu'il interdit maintenant.

| Changement | Avant | Apres | Garantie |
|---|---|---|---|
| Chevrons | presents si et seulement si la cible est `regle` | presents dans les 12 enonces de la tache 1, absents des 12 de la tache 2 (forme (a), decision de l'utilisatrice) | **deplacee, pas perdue.** Avant, le chevron designait la cible. Apres, c'est la question qui la designe : `verifierLaQuestionDeLaTache1` exige que la question reprenne la regle citee **si et seulement si** la cible est `regle`. Les deux sens mordent sur des jeux fautifs neufs (`Mila a arrache avec une autre regle`, `Tom a dessine avec la regle dans la question`). L'absence de chevrons en tache 2 reste gardee |
| Forme de la question de tache 1 | non gardee | `<element>, c'est quoi ?`, sans chevrons, identique pour les trois cibles | **neuve**, deux jeux fautifs |
| Melange de regles affirmatives et negatives | non garde | entre un quart et trois quarts de regles negatives | **neuve**, un jeu fautif |
| **Question de cible `sanction`** | le **premier mot entier** de la question est contenu dans la phrase de l'adulte (`verifierQueLaQuestionReprendLeGeste`, supprimee) | les **trois premieres lettres** du premier mot de l'element sont contenues, n'importe ou, dans la phrase de l'adulte | **affaiblie**, voir plus bas |
| Phrase de l'adulte | `Le maitre` ou `La maitresse`, avec la majuscule | les memes mots, sans tenir compte de la casse, n'importe ou dans l'enonce | elargie de facon necessaire : la tache 1 ecrit *"... : le maitre lui fait ..."* en minuscule |
| `GESTE_REPARATEUR` | contient la racine `poser` | racine retiree | **plus stricte**, un jeu fautif (`Lina a poser sans reparation`) |
| `CRITERE_DU_CP`, mots refuses d'avance | | listes allongees (`toutes`, `chaque`, `chacune`, `devant les autres`, `tape`, et d'autres) | **plus stricte**, cinq sondes fautives neuves |
| Pli de tache 1 | non garde | 112 caracteres au plus par enonce | **neuve**, un jeu fautif |
| `fautif(debut)` | `startsWith` | `contains` | **sans perte.** Lecture des selecteurs du test contre la fixture commitee : chacun designe le meme nombre d'items qu'avant, sauf ceux de tache 1, qui commencent par un chevron et que `startsWith` ne pouvait pas trouver. `Negatif` designe 4 items, et c'est le but de son test |

**La garantie perdue, mesuree sur le materiau reel.** Copie de `41203188` ou une seule ligne du paquet change (`diff` contre `git show 41203188:<paquet>` : la ligne 83) : *"... le maitre lui fait le recoller. **Recopier** le livre, c'est quoi ?"* au lieu de *"Recoller ..."*. C'est une question de cible `sanction` qui ne reprend pas le geste de l'adulte. Commande : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=PaquetCe1EmcRegleSanctionTest -Dsurefire.failIfNoSpecifiedTests=false`, 14:32:01 a 14:32:49. Resultat : **exit 0, 78 tests, 0 echec**. La racine `rec` se lit dans `recoller`. Sur `1152fdc9`, la clause supprimee comparait le mot entier `recopier` a la phrase de l'adulte et l'aurait refuse. C'est une **lecture du code supprime, non mesuree** : la forme de la question a change entre les deux sommets. La racine n'est en plus ancree a aucun debut de mot, si bien que `ran` se lit dans `grand`.

**Pourquoi trois lettres, et ce qui suffirait.** La cible `faute` reprend un acte conjugue dans l'enonce (`a copie`, puis *"Copier sur Zoe, c'est quoi ?"*) : un mot entier n'y passerait pas, une racine si. La cible `sanction` reprend un infinitif deja present a l'identique dans la phrase de l'adulte : les quatre enonces de cible `sanction` du paquet (`Recoller`, `S'excuser`, `Ramasser`, `Nettoyer`) repetent le mot entier de l'adulte (lecture du paquet a `41203188`). **La correction a le moins de pieces mobiles** : comparer le mot entier pour la cible `sanction`, comme le faisait la clause supprimee, et garder la racine pour `faute` seule, ancree en debut de mot. Elle vaut un jeu fautif, la mutation ci-dessus. **A `sofia-sm` (auteure de `LP4`), avant la fusion de `LP4`** : c'est une assertion de son lot, pas un garde partage, et je ne l'ecris pas dans sa branche.

### 7.4 ArchUnit

Copie propre par `git archive` dans mon repertoire. Commande et perimetre de la section 2, `-am` present. Pour `LP4`, j'ajoute `PaquetCe1EmcRegleSanctionTest` au perimetre. JDK 21.0.10 (`java.version`, une seule valeur dans les 19 et 20 rapports).

| Sommet | Duree | Code | ArchUnit, 15 classes | Gardes partages, 4 classes | Test du lot |
|---|---|---|---|---|---|
| `f8d5d875` (`LP1`) | 14:27:03 a 14:29:24 | **0** | **88 tests, 0 echec** | 56, 0 | non lance |
| `41203188` (`LP4`) | 14:29:24 a 14:32:01 | **0** | **88 tests, 0 echec** | 56, 0 | 78, 0 |

Compte de regles (`ADR-0104`) : **41, 38, 16** sur `f8d5d875`, `41203188` et `4a49c3f3`. `LP2` n'a pas bouge : la mesure de 6.3 vaut pour lui. Ecart 39 : sur les fichiers ajoutes ou modifies de `LP1`, de `LP4` et de `s57-camille-p4`, `git grep` sur le motif de la section 4.3 sort en exit 1. **Ne bloque rien.**

### 7.5 Conflits attendus

Commande : `git merge-tree --write-tree --name-only --merge-base=eb267ba7` sur chaque paire.

| Paire | Code | En conflit |
|---|---|---|
| `f8d5d875` x `c29d1d26` | 1 | `catalogue.yaml` |
| `f8d5d875` x `41203188` | 1 | `catalogue.yaml` ; inventaire de `TexteAccentue...` |
| `c29d1d26` x `41203188` | 1 | `catalogue.yaml` |
| `4a49c3f3` x chacun des trois lots | **0** | aucun |

Le resultat est identique a 3.3 et 6.4. La branche de `camille-po` ne touche aucun fichier des lots. Le catalogue compte 158 entrees sur chaque sommet de lot, 160 apres les trois fusions.

### 7.6 Branche `s57-camille-p4` (`4a49c3f3`)

**Fichiers** (`git diff --name-status eb267ba7 4a49c3f3`) : `docs/backlog/backlog.md` (+29), le fichier de `DEC-0120` (amendement A.153 a A.158, +87), `DEC-index.md` (+2), `MATRICE-couverture.md` (+2 -1 : la cellule de `MAT-CE1-10` reste `non couvert`, avec une mention), `registre-prix-palier-4.txt` (neuf, 15 lignes). Aucun fichier Java ni YAML.

**Coherence des gardes de `squad/`.** Copie propre de `4a49c3f3`, `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest,AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest,ToutesLesReferencesDeDecisionResolventTest,LesGardeFousSontMisALEpreuveTest -Dsurefire.failIfNoSpecifiedTests=false`, 14:32:49 a 14:35:16. Resultat : **exit 0, 28 tests, 0 echec**. Aucun identifiant de condition non alloue, aucune reference de decision qui ne resout pas, aucun tableau mal forme. La numerotation de `DEC-0120` suit la base : A.152 est le dernier titre a `eb267ba7`, et l'amendement ouvre A.153 a A.158 sans trou. Job `chemins` sur ses cinq fichiers : exit 0. `prefixes` sur `eb267ba7..4a49c3f3` : exit 0. Je ne lis ni les prix ni le registre de prix : ils sont a `camille-po` et `nadia-prof`.

### 7.7 Les codes `MP4-1` a `MP4-8` d'`ines-design` : rien a allouer, mais quatre autres identifiants le demandent

Le fichier qui les porte est `mesure-ines-palier-4-pli-744x551.md`, sur la branche `s57-ines-p4` a `2c9b2bb5` (descend de `eb267ba7`, exit 0). Il n'est sur aucune des branches lues plus haut.

- **`MP4-1` a `MP4-8` ne sont pas des identifiants de condition au sens du garde.** `IDENTIFIANT` (`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest.java:120-121`) veut un prefixe de **deux a cinq lettres majuscules** ; `MP4` porte un chiffre. Le garde ne les voit pas : ce sont des reperes de fichier, comme mes `V1` ou `L`. **Je ne les alloue pas** : une allocation au registre pour un repere que le garde ne lit pas serait une ligne qu'aucune regle ne verifie. Si quelqu'un veut en faire des identifiants de condition, il faut d'abord les renommer dans une forme que le garde lit, et c'est alors a l'auteure de la demander.
- **Ce qui rougit reellement, mesure.** Copie de `2c9b2bb5`, meme commande qu'en 7.6, 14:35:16 a 14:37:45. Resultat : **exit 1, 28 tests, 1 echec**, `leDepotReelEstCoherent`. Le message nomme **les quatre propositions de prefixe `PR`, numeros 1 a 4** (lignes 190 a 193 du fichier), et elles seules : *"ecrit l'identifiant <prefixe PR, numero 1>, que squad/decisions/REGISTRE-identifiants.md n'alloue pas"*. Je ne les recopie pas sous leur forme : ce fichier-ci rougissait le garde a son tour (mesure faite sur ma premiere version de cette section, 14:45, exit 1, ces quatre identifiants et eux seuls). Le prefixe `PR` est absent du registre a `eb267ba7` (`git grep` vide).
- **Deux issues, a `ines-design` et a moi.** (1) Ces quatre codes sont des propositions de son releve et non des conditions : elle les renomme en reperes que le garde ne lit pas, dans sa branche. (2) Elles doivent rester citables comme conditions : je les alloue, prefixe neuf `PR`, dans un commit sur un arbre qui contient son fichier. C'est RF8 : `sofia-sm` route l'allocation et attend mon commit avant de fusionner. Je n'alloue pas sur `s57-theo-p4` : le fichier n'y existe pas. Le garde exige que l'allocation resolve, et que cette allocation-la soit rouge sans le fichier est **non verifie**. Le choix entre (1) et (2) est a `ines-design`. Je ne decide pas de la nature de ses propositions.

### 7.8 Jobs lances pour cet amendement

- **`build`, mesures partielles** : 7.3, 7.4, 7.6 et 7.7, toutes avec `-am` et leur perimetre nomme. Puis les quatre gardes de `squad/` sur ce fichier amende (resultat dans le message de commit).
- **`chemins`** : sur ce fichier avant commit, et sur les fichiers de `s57-camille-p4`.
- **`prefixes`** : sur `s57-camille-p4`, et sur ma branche avant commit.
- **`build` complet, `package`, `outils`** : non lances.

## 8. Amendement du 2026-10-22, 15:20 (UTC+02:00) : `LP4` a `f36d83bc`, la garantie de 7.3 est restauree

Nouveau sommet de `LP4`, annonce par l'agent lanceur : `f36d83bc` (avant `41203188`, via `b7b25f42`). `git rev-parse --short` rend `f8d5d875` (`LP1`), `c29d1d26` (`LP2`), `f36d83bc` (`LP4`), et `round-two` reste a `eb267ba7`. Meme methode et meme isolation qu'aux sections 6 et 7. **Les sections 1 a 7 ne sont pas reecrites.**

### 8.1 Lignee

`git merge-base --is-ancestor eb267ba7 f36d83bc` : **0**. `git merge-base --is-ancestor 41203188 f36d83bc` : 0, donc pas de reecriture d'historique. `git log --oneline eb267ba7..f36d83bc` : 7 commits `[SOF]`, `f36d83bc`, `b7b25f42`, puis les 5 de 7.1.

### 8.2 Diff depuis `41203188`

`git diff --stat 41203188 f36d83bc` : le paquet `ce1-emc-regle-sanction.yaml` (+11 -7 : des enonces et un indice de tache 1 reecrits), `PaquetCe1EmcRegleSanctionTest.java` (+114 -7, `git diff --numstat`) et la fixture `ce1-emc-fautif.yaml` (+42). Aucun fichier sous `src/main/java` depuis `eb267ba7`, aucun marqueur ajoute, **aucun `@Test` supprime**, aucun fichier partage modifie.

Les 7 lignes Java supprimees sont toutes dans la clause de 7.3 :

| Point | `41203188` | `f36d83bc` | Garantie |
|---|---|---|---|
| Question de cible `sanction` | 3 premieres lettres, contenues n'importe ou dans la phrase de l'adulte | **le premier mot entier** de l'element, cherche dans la phrase de l'adulte et **ancre en debut de mot** | **restauree.** C'est au moins la clause supprimee de `1152fdc9`, qui faisait un `contains` sans ancrage. Limite qui demeure, et qui existait deja avant : la fin du mot n'est pas ancree, donc un mot de la question qui est le debut du mot de l'adulte passe |
| Question de cible `faute` | 3 lettres, n'importe ou | 3 lettres, **ancrees en debut de mot** | **plus stricte** : `ran` ne se lit plus dans `grand`. Un jeu fautif neuf (`Omar a copie avec une racine au milieu`) |
| Question negative | non traitee | `ne pas` ou `ne plus` retire avant de prendre le mot | sans perte : seul l'element est normalise. La phrase cherchee ne change pas |

Ajouts sans suppression : deux gardes neufs (*"la negation ne designe jamais la regle"* : les questions negatives de tache 1 portent au moins deux cibles ; *"l'acte de tache 1 est une faute lisible"* : un acte qui peut etre accidentel dit `expres`, et un geste ordinaire d'ecolier n'est pas une faute). Ils viennent avec cinq jeux fautifs neufs. Les selecteurs neufs designent chacun 1 item de la fixture, sauf `NegQ`, qui en designe 3, et c'est l'ensemble que son test juge. Que ces gardes lexicaux disent juste la pedagogie est a `nadia-prof` : leur Javadoc le dit elle-meme.

### 8.3 La mutation de 7.3, rejouee sur le paquet reel de `f36d83bc`

Copie de `f36d83bc` ou une seule ligne du paquet change (`diff` contre `git show f36d83bc:<paquet>` : la ligne 87, *"Recopier le livre, c'est quoi ?"* au lieu de *"Recoller ..."*). Commande : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=PaquetCe1EmcRegleSanctionTest -Dsurefire.failIfNoSpecifiedTests=false`, 15:17:14 a 15:18:13. Resultat : **exit 1, 85 tests, 1 echec**, `rsa1LaQuestionDeLaTache1ADeLaMemeFormePourLesTroisCibles`, avec le message *"... ne reprend pas le geste de l'adulte, mot entier (guillemets du message : Recopier le livre)"*. La meme mutation etait verte a `41203188` (7.3). **Ce que `sofia-sm` annonce est confirme sur le materiau reel**, pas seulement sur sa fixture.

### 8.4 ArchUnit

Copie propre de `f36d83bc` par `git archive`, `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,adapters/adapter-web,bootstrap -am test -Dtest=<15 classes ArchUnit>,<4 gardes partages>,PaquetCe1EmcRegleSanctionTest -Dsurefire.failIfNoSpecifiedTests=false`, 15:14:33 a 15:17:14 : **exit 0**. Le perimetre est celui de 7.4. Comptes lus dans les `TEST-*.xml`, JDK 21.0.10 (`java.version`, 20 rapports, une seule valeur).

| Perimetre | Resultat |
|---|---|
| ArchUnit, 15 classes | **88 tests, 0 echec** |
| Gardes partages, 4 classes | 56 tests, 0 echec (`LeRang...` 28, `LesFormesAcceptees...` 4, `ChaquePaquetDeMathematiques...` 14, `TexteAccentue...` 10) |
| `PaquetCe1EmcRegleSanctionTest` | 85 tests, 0 echec (78 a `41203188`) |

Compte de regles (`ADR-0104`) : **41, 38, 16**. Ecart 39 : `git grep` du motif de 4.3 sur les fichiers de `LP4` sort en exit 1, **ne bloque pas**.

### 8.5 Conflits attendus

`git merge-tree --write-tree --name-only --merge-base=eb267ba7` :

| Paire | Code | En conflit |
|---|---|---|
| `f8d5d875` x `c29d1d26` | 1 | `catalogue.yaml` |
| `f8d5d875` x `f36d83bc` | 1 | `catalogue.yaml` ; inventaire de `TexteAccentue...` |
| `c29d1d26` x `f36d83bc` | 1 | `catalogue.yaml` |

Le resultat est identique a 3.3, 6.4 et 7.5, et la resolution reste l'ajout des deux cotes (RF3). Le catalogue compte 158 entrees par sommet, 160 apres les trois fusions.

### 8.6 Jobs lances pour cet amendement

- **`build`, mesures partielles** : 8.3 et 8.4. Puis les quatre gardes de `squad/` sur ce fichier amende (resultat dans le message de commit).
- **`chemins`** : sur ce fichier, avant commit. **`prefixes`** : sur ma branche, avant commit.
- **`build` complet, `package`, `outils`** : non lances.
