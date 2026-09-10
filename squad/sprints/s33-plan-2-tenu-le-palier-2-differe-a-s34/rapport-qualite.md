# Rapport qualite - Sprint 33

**Auteur** : `yanis-qa`. **Commit mesure** : `b45e5927` (tete de `round-two` a l'ouverture de
cette mesure, fusion de `s33-marc2`, huit stories payees sur onze).

**JDK mesure** : 21.0.10, rapporte par la machine virtuelle des tests elle-meme ("Starting ...
using Java 21.0.10", logs Spring Boot des tests d'integration), pas par une entree de toolchain.

**Methode de mesure, ecrite avant les chiffres qui en dependent.** Le worktree principal du depot
est partage en ecriture par plusieurs agents pendant cette mesure : `git status --short` y montre,
en cours de session, des fiches de conformite et des verdicts non commites (`nadia-prof`,
`camille-po`, `theo-archi`) qui n'appartiennent pas au commit `b45e5927`. Une premiere mesure sur
ce worktree partage a produit un echec reel mais hors perimetre (section 1). La mesure retenue est
donc faite sur un second worktree Git, cree a la racine du depot et positionne exactement sur
`b45e5927`, `git status --short` vide avant toute commande - l'equivalent du clone propre que la
CI checkoute a chaque execution. Ce worktree a ete supprime (`git worktree remove`) une fois la
mesure terminee ; rien n'y est reste.

---

## 1. Porte technique : les quatre jobs, un par un, chacun relance par moi

`mvn verify` n'est que le contenu du job `build`. Les quatre jobs de `.github/workflows/ci.yml`
sont relances integralement, avec les commandes exactes du workflow.

| Job | Commande | Verdict |
|---|---|---|
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, puis `git log --no-merges --format=%s 1740807e..HEAD \| verifier-prefixes.sh` | **VERT.** *"Tous les commits portent un prefixe d'agent connu."* (15 cas d'auto-test verifies un par un, puis le dernier commit et la plage complete depuis l'ouverture du sprint) |
| `chemins` | `verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| verifier-chemins.sh` | **VERT.** *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* (22 cas d'auto-test verifies un par un) |
| `outils` | boucle du workflow sur `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py` | **VERT.** *"9 juge(s) pur(s) execute(s)."*, aucun motif vide |
| `build` | `bash outils/mvn-jdk21.sh -B clean verify`, puis `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package` (racine, reacteur complet) | **VERT** sur le worktree isole (voir ci-dessous pour le premier essai, hors perimetre) |

### Le premier essai sur le worktree partage : un rouge reel, mais qui n'appartient pas a ce commit

Deux tentatives de `clean verify` sur le worktree principal ont d'abord echoue sur `Failed to
delete ... target\recreo*.jar : ... utilise par un autre processus` : des processus
`java -jar bootstrap/target/recreo.jar` restaient actifs, issus de sessions de jeu manuel menees
par au moins un autre agent en parallele (des scripts de partie et un journal de session en
attestent hors du depot). Route a `sofia-sm` : une session de jeu manuel sur le jar devrait
tourner dans un worktree dedie, jamais sur le worktree partage, et le serveur doit etre arrete a
la fin de la session - deux processus ont ete retrouves actifs sans lien avec une mesure en cours.

Une fois ces processus arretes, `mvn -B clean verify` a reussi sur le worktree partage (1443
tests, 0 echec - chiffre confirme independamment en section 2). Mais l'etape suivante,
`mvn -B -pl bootstrap -am package`, a echoue avec un **echec reel** :
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest.leDepotReelEstCoherent` rapporte onze
identifiants de condition ecrits dans des fichiers du disque (`FICHE-bandit-manchot-regles-de-
vie-cp.md`, `verdicts-nadia-instance-A.md`, `verdicts-nadia-instance-B.md`) sans allocation dans
`squad/decisions/REGISTRE-identifiants.md`. Ce garde-fou lit le disque, pas l'index Git : entre
les deux commandes, un ou plusieurs agents avaient deja ecrit ces fichiers, non commites, dans le
meme worktree. **Ce n'est pas un defaut de `b45e5927`** : `git status --short` au moment de
l'echec montre ces cinq fichiers en `M`/`??`, absents du commit mesure. La preuve : le meme job,
rejoue integralement sur le worktree isole positionne sur `b45e5927` (`git status --short` vide),
passe sans aucun echec (ci-dessous).

### Verdict retenu, sur le worktree isole a `b45e5927`

```
mvn -B clean verify   : BUILD SUCCESS, six modules SUCCESS, 04:42 min
mvn -B -pl bootstrap -am package : BUILD SUCCESS, six modules SUCCESS, 03:14 min
```

Aucun echec, aucune erreur, sur aucune des deux commandes. `build` est **VERT**.

---

## 2. Compte de tests, methode dans le meme paragraphe

**Methode** : somme de l'attribut `tests` des fichiers `TEST-*.xml` produits par le `clean verify`
ci-dessus, dans `surefire-reports` et `failsafe-reports` de chaque module, sur le worktree isole a
`b45e5927`. Somme des `failures` et `errors` par la meme methode. Mesure faite deux fois
(worktree partage avant l'echec de packaging, puis worktree isole) : les deux mesures convergent
sur le meme chiffre.

- **193 fichiers `TEST-*.xml`, 1443 tests, 0 echec, 0 erreur.**
- Repartition par module : `domain` 105, `application` 13, `adapter-web` 388, `adapter-content`
  693, `bootstrap` 244 - identique, poste par poste, a la mesure de `sofia-sm` (planning.md,
  sections 13 et 14).

Ce chiffre confirme celui du planning (1443 = 1439 + 4, l'attendu exact : `marc-dev` ajoute quatre
methodes `@Test` sur `S33-HG-08-12`, les trois autres branches n'en ajoutent aucune, `S33-HG-04`
n'en ajoute aucune non plus).

**Aucun module sans test** (`find <module>/src/test -iname "*.java" | wc -l`) : `domain` 15,
`application` 3, `adapters/adapter-web` 49, `adapters/adapter-content` 96, `bootstrap` 90.

**36 declarations `ArchRule`** (`grep -rn "static final ArchRule "`, hors `target/`) : inchange
depuis la cloture de s32. Coherent avec un sprint qui n'ouvre aucune regle d'architecture neuve -
le seul commit qui touche des tests d'architecture (`theo-archi`, `d85cc7b0`) ajoute une methode
utilitaire de pincement (section 3), pas une regle ArchUnit.

**Catalogue : 68 entrees** (`grep -c "^  - id:" catalogue.yaml`), inchange - aucun jeu neuf, ce
sprint ne portait que du remplissage et des reprises de contenu sur des jeux existants.

---

## 3. Tests ajoutes par s33 : est-ce qu'ils mordent reellement ?

### La regression de fragilite signalee dans le rapport de s32 est fermee - verifiee, pas supposee

Le rapport de s32 (section 2) nommait un risque precis : deux tests de
`PaquetCm1HistoireGeographieTest` (`fer3...SurHgCm101...`, `fer3...SurHgCm102...`) classent la
tache d'un item par une sous-chaine de son enonce (`.startsWith("Quelle grande periode vient
juste")`, `.startsWith("Parmi ces trois")`) et ne posaient qu'un plancher (`isGreaterThan(0)`) sur
chaque effectif - une reformulation de l'un des deux enonces deplacerait le partage entre les deux
taches sans qu'aucune assertion ne le remarque, exactement le defaut deja paye par `ADR-0089`
ailleurs dans le meme paquet.

`theo-archi` (`d85cc7b0`) ecrit une methode `partageDesTachesEpingle`, qui remplace le plancher par
une egalite exacte sur les deux effectifs (`isEqualTo`), sur les six lignes du paquet qui partagent
une tache en deux. Son commit cite explicitement le signalement de s32 comme motif.

**Mis a l'epreuve par moi, pas pris pour acquis.** Sur le worktree isole :

1. Sauvegarde de `cm1-histoire-geographie.yaml`.
2. Reformulation d'un seul enonce de `HG-CM1-01` (celui qui commence par "Quelle grande periode
   vient juste apres la Prehistoire") en "Apres la Prehistoire, quelle grande periode vient",
   sans toucher a la bonne reponse, aux distracteurs ni a l'indice - exactement le geste qui avait
   fait glisser le partage sans le signaler avant ce correctif.
3. `mvn -pl adapters/adapter-content -am test -Dtest=PaquetCm1HistoireGeographieTest` :
   **ROUGE**. `fer3AuMoinsDeuxTachesSurHgCm101LaMoinsServieAuMoinsTroisItems` echoue avec
   *"[HG-CM1-01 : la tache "ordre" porte exactement 5 items] expected: 5L but was: 4L"*.
4. Restauration du fichier, `git status --short` vide confirme.
5. Reexecution de la meme commande : **VERT**, 28/28.

Le garde-fou mord reellement sur le geste qu'il a ete ecrit pour attraper. Ce n'est pas une
fermeture totale de la fragilite : les deux methodes lisent toujours la tache d'un item par une
sous-chaine de son enonce plutot que sur sa `bonneReponse` (patron que les six autres lignes du
meme fichier suivent deja, `PEUPLES_ANCIENS`, `mouvements`, etc.). Le pincement des effectifs
detecte tout deplacement net du partage, mais pas un echange simultane de deux items en sens
inverse qui laisserait les deux effectifs inchanges - un cas rare, non couvert, a nommer plutot
qu'a corriger ici : le patron correct existe deja a cote, dans le meme fichier.

### `S33-HG-04` : l'enumeration ecrite a la main est mise a jour, pas remplacee par une lecture d'enonce

`marc-dev` (`30726ecb`) remplace "les Sarrasins" par "d'Espagne" dans la bonne reponse d'un item, et
met a jour a la main le `Set.of("les peuples germaniques", "les Vikings", "d'Espagne")` de
`PaquetCm1HistoireGeographieTest` - exigence de `theo-archi` (avis de faisabilite, section 6.2) :
un ensemble ecrit a la main rougit quand le contenu change, une lecture par sous-chaine passerait
au vert sans le remarquer. Diff verifie ligne a ligne : c'est bien ce geste qui a ete fait, et
aucun autre test du meme fichier ne cite la chaine remplacee.

### `S33-EXC-R1` : l'inventaire litteral est mis a jour dans le meme commit, comme la story l'exige

`TexteAccentueExposeParLePortEntrantTest` maintient une liste Java de toutes les consignes du
catalogue. `lea-dev` (`fbb370a7`) reecrit la consigne d'exclusion et met a jour la meme ligne de
l'inventaire dans le meme commit - verifie sur le diff, pas suppose. C'est le patron correct pour
un test qui ne peut pas deriver sa population du disque (l'inventaire *est* la liste des consignes
attendues, il n'y a rien d'autre a deriver).

### `S33-EPS-JEU` / `S33-EPS-SEC` : le message de test decrit la population reellement mesuree

`sofia-sm` (`556cad9f`) met a jour le commentaire-preuve de
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` sur le paquet EPS jeu collectif : "11
items sur 11 portent deux distracteurs (trois propositions)" et "33 propositions" - j'ai recompte
sur le paquet servi (`grep` des blocs `distracteurs:` du fichier) : 11 items a deux distracteurs
chacun, 33 propositions au total, aucune numerique. Le chiffre cite colle a la population reelle.

### `S33-CAT1` : un correctif de registre, toujours hors du perimetre de tout test structurel

Le correctif de `S32-CAT1` avait introduit un repere lexical ("maitresse", 1 occurrence avant, 7
apres) a la place du repere de registre qu'il devait payer - defaut nomme dans le commentaire du
paquet par `lea-dev` elle-meme, avant de le corriger. **Verifie, pas suppose** :
`grep -ic "maitresse"` sur `cp-emc-regles-de-vie.yaml` et `cp-emc-permis-interdit.yaml` rend
respectivement 3 et 0 - les 3 occurrences restantes sont dans les commentaires qui racontent
l'historique du defaut (s32) et le motif de la reprise (s33), aucune dans un item servi. Le
contenu servi ne porte plus le mot, dans une bonne reponse comme dans un distracteur.

Aucun test structurel ne verifie la clause elle-meme (les trois propositions du meme registre,
plutot que reconnaissables au ton) : c'est un jugement de sens, dans la meme famille que `HIS-2`
ou `BIA-5` deja nommee non testable par le rapport de s32. Ce n'est pas un trou ouvert par s33 -
c'est une frontiere deja actee, et le paquet la documente lui-meme dans son commentaire. La
deuxieme tentative de paiement de `CAT-1` reste donc verifiee par lecture, pas par un test qui
pourrait la faire regresser silencieusement si un mot recurrent revenait sous une autre forme.

---

## 4. Ce qui n'est pas couvert, nomme plutot que suppose

- **`S33-EMCCP07` reste partielle** : un item paye sur trois, les deux autres bloques par un
  conflit entre deux garde-fous opposables (`FER-1`, `BIN-1`) que l'arbitrage prealable de s33
  n'a pas amende. Aucune ligne de code ni de test n'est en jeu ici, seulement une decision
  pedagogique due de `nadia-prof` - deja routee dans le planning, section 14, je ne la re-route
  pas.
- **`S33-CONS-4` et `S33-SCO-1` restent bloquees**, sans code ni test touche par ce sprint sur ces
  deux stories.
- **Aucune trace, dans le depot Git, d'une partie jouee de bout en bout sur le jar** pour le
  contenu corrige ce sprint (histoire-geographie, EMC, EPS) - le meme trou que le rapport de s32
  avait signale et laisse ouvert. J'ai trouve, hors du depot, des indices qu'une session de jeu
  manuel etait en cours pendant ma propre mesure (section 1) ; le depot Git etant la seule
  memoire partagee de ce projet, je ne peux ni la citer ni la compter tant qu'elle n'y est pas
  ecrite.
- **Le jugement de sens sur `CAT-1`, `HIS-2`, `HIS-3`, `BIA-5` et la justesse factuelle du contenu
  d'histoire-geographie, d'EMC et d'EPS** restent hors du perimetre de tout automate de ce depot -
  frontiere deja actee, pas un trou de ce sprint.
- La dette de 25 fichiers sur 95 lisant l'enonce par operation de chaine, chiffree par
  `theo-archi` a s32, n'a pas ete recomptee par moi ce sprint ; les deux occurrences dans
  `PaquetCm1HistoireGeographieTest` restent dans cette famille (section 3), simplement pincees
  plus fort qu'avant.

---

## 5. Tests instables

**Aucun** identifie sur les executions menees ce jour : deux `clean verify` complets (worktree
partage puis worktree isole), un `package` complet, plusieurs executions ciblees de
`PaquetCm1HistoireGeographieTest` (mutee puis restauree), toutes deterministes d'une execution a
l'autre.

---

## 6. Mon avis sur la porte technique

**Verte**, mesuree sur `b45e5927` via un worktree isole : les quatre jobs de
`.github/workflows/ci.yml` passent (section 1), 1443 tests sans echec ni erreur (section 2), le
garde-fou cite comme fermant la fragilite signalee a s32 mord reellement quand je le mets a
l'epreuve (section 3).

**Un rouge reel a ete rencontre et ecarte du perimetre de ce verdict**, pas cache : il provenait
de fichiers non commites d'autres agents dans le worktree partage, absents du commit mesure - la
preuve de cet ecart est en section 1, avec la commande qui le confirme sur un checkout propre.

Cet avis porte uniquement le Done technique. Le Done pedagogique reste entier a `nadia-prof` -
rien dans ce rapport ne le prejuge, dans un sens ou dans l'autre. Huit stories sont payees sur
onze (planning.md, section 14) ; trois restent bloquees par une decision pedagogique ou un
arbitrage encore dus, pas par un obstacle technique.

---

## 7. Fichiers touches par cette review

Une seule mutation temporaire sur le worktree isole, restauree par copie du fichier sauvegarde et
verifiee par `git status --short` vide avant et apres :

- `adapters/adapter-content/src/main/resources/contenu/cm1-histoire-geographie.yaml` (section 3).

Le worktree isole cree pour cette mesure a ete supprime (`git worktree remove`) une fois la mesure
terminee. Aucune modification n'est restee dans le worktree principal du depot du fait de cette
review ; les fichiers non commites qui y apparaissent (section 1) appartiennent a d'autres agents
et ne sont ni le fait ni l'objet de cette review.
