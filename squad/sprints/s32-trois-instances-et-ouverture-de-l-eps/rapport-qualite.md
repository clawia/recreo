# Rapport qualite - Sprint 32

**Auteur** : `yanis-qa`. **Arbre mesure** : `round-two` a `7c800261`, worktree principal, `git
status` propre avant et apres toute mesure. Aucune mutation de ce rapport n'est restee dans
l'arbre (section 6).

**JDK mesure** : 21.0.10, rapporte par la JVM des tests elle-meme (`Starting ... using Java
21.0.10`, logs Spring Boot des tests d'integration), pas par une entree de toolchain.

---

## 1. Porte technique : les quatre jobs, un par un, chacun relance par moi

`mvn verify` n'est que le contenu du job `build`. Les quatre jobs de `.github/workflows/ci.yml`
sont relances integralement, avec les commandes exactes du workflow.

| Job | Commande | Verdict |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` (racine, reacteur complet) | **VERT.** `BUILD SUCCESS`, six modules `SUCCESS`, 2 min 07 s |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **VERT.** *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | boucle du workflow sur `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py` | **VERT.** 9 juges purs executes, aucun motif vide |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis `git log --no-merges --format=%s -1 \| bash .github/scripts/verifier-prefixes.sh` sur le dernier commit | **VERT.** *"Tous les commits portent un prefixe d'agent connu."* |

### Compte de tests, methode dans le meme paragraphe

**Methode** : somme de l'attribut `tests` des fichiers `TEST-*.xml` produits par le `clean verify`
ci-dessus, dans `surefire-reports` et `failsafe-reports` de chaque module du reacteur, sur mon
propre arbre. Somme des `failures` et `errors` par la meme methode.

- **193 fichiers `TEST-*.xml`, 1 439 tests, 0 echec, 0 erreur.**

**Ce chiffre confirme, chiffre pour chiffre, celui de `sofia-sm` et de `theo-archi`** - trois
mesures independantes, aucune recopiee. Trois mesures concordantes ne dispensent pas de la
mienne ; je l'ai reprise, et j'obtiens le meme total.

**Aucun module sans test** (`find <module>/src/test -iname "*.java" | wc -l`) : `domain` 15,
`application` 3, `adapters/adapter-web` 49, `adapters/adapter-content` 96, `bootstrap` 84.

**36 declarations `ArchRule`** (`grep -rn "static final ArchRule "`, excluant `target/` et le faux
depot de test-ressources `RegleContreExemple.java` qui en declare une factice pour sa propre mise
a l'epreuve) : 35 regles reelles. En hausse depuis les 33 mesurees a la cloture de s31 - coherent
avec la clause 12 d'`ArchRule` qu'`ADR-0088` ajoute a `DecouplageMecaniqueContenuTest` (avis de
faisabilite du palier 2, fusionne avant l'ouverture de s32). Confirmation architecturale detaillee
: `theo-archi`, `confirmation-archunit.md`.

**Catalogue : 68 entrees** (`grep -c "^  - id:" catalogue.yaml`).

**Verdict du job `build` avant fusion, pour memoire** : `sofia-sm` rapporte (planning.md, section
14) un rouge herite de deux tests sur l'arbre fusionne intermediaire (`7ed471a7`), route a
`theo-archi` et paye par `63590b33`. Ce rouge n'existe plus sur `7c800261`, l'arbre que je mesure
: mon propre `clean verify` ne le reproduit pas, confirmant le paiement plutot que de le supposer.

---

## 2. Ce qui est couvert, ce qui ne l'est pas

### Le contenu neuf est couvert par des tests qui derivent leur perimetre, pas par des fichiers nommes un par un

- **Terrain A** (`HG-CM1-01` a `-04`, `LVE-CM1-07`) : `PaquetCm1HistoireGeographieTest` et
  `PaquetCm1AnglaisTest` balaient `itemsDeLaLigne(...)` sur le paquet charge par le meme code de
  production que le catalogue reel - pas une liste ecrite a la main. `NOMBRE_D_ITEMS_ATTENDU` est
  une egalite stricte (57 -> 92 items), pas un plancher : un item ajoute sans test le ferait
  rougir, pas passer inapercu.
- **Terrain C** (`EPS-CP-04`, `EPS-CP-08`) : les deux paquets neufs n'ajoutent aucune methode
  `@Test` - ils etendent des structures de donnees lues par des tests deja generiques
  (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`,
  `TexteAccentueExposeParLePortEntrantTest`), confirme par le delta de tests nul sur ce lot que
  `sofia-sm` a elle-meme mesure et signale (planning.md, section 14) plutot que de laisser
  l'annonce "+2 ou plus" faite avant mesure se propager sans correction. C'est le patron correct :
  un garde-fou qui balaie l'inventaire du catalogue mord sur tout paquet neuf sans etre reecrit.
- **`LON-1`/`LON-2`** (`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`) balaie tous les
  enonces de CP charges depuis le contenu servi, pas une liste de paquets : population ecrite
  (36 paquets, 528 enonces) plutot qu'esperee.

### Une regression de fragilite fraiche, dans un test ecrit ce sprint, sur l'axe que le sprint vient de corriger ailleurs

`PaquetCpEmcExclusionTest` a ete corrige ce sprint (`ADR-0089`, clause E) precisement parce que
lire l'identite d'une tache dans une sous-chaine de l'enonce (`item.enonce().contains("À qui")`)
etait le seul obstacle technique a `EXC-R5` : la reecriture de l'enonce faisait basculer des items
d'une tache a l'autre sans changer leur nature. Le correctif lit desormais la tache dans la
`bonneReponse`.

**Le meme sprint reintroduit exactement ce risque, ailleurs, dans du code neuf.**
`PaquetCm1HistoireGeographieTest`, les deux tests `fer3AuMoinsDeuxTachesSurHgCm101...` et
`...SurHgCm102...` (lignes 300 et 320), lisent la tache d'un item par
`item.get("enonce").startsWith("Quelle grande période vient juste")` et
`.startsWith("Parmi ces trois")`. J'ai verifie que les deux chaines correspondent bien, caractere
pour caractere (memes points de code accentues, `é`/`è` precomposes des deux cotes - pas de la
famille "espace insecable contre espace ordinaire" citee en section 4), donc **ces deux tests ne
sont pas faux aujourd'hui**. Mais si `nadia-prof` demande de reformuler l'un de ces deux enonces -
exactement le geste qui a declenche `EXC-R5` - le partage entre les deux taches se deplacera en
silence, sans qu'aucune assertion ne le signale : les deux tests posent des planchers
(`isGreaterThan(0)`, plancher `MINIMUM_PAR_TACHE`) et non une partition exhaustive verifiee comme
`chaqueItemAppartientAExactementUneDesDeuxTaches` de `PaquetCpEmcExclusionTest` la pose desormais.
**Les deux memes tests, sur `HG-CM1-03` et `HG-CM1-04`, dans le meme fichier et ecrits le meme
jour, evitent le probleme** en lisant la tache sur `bonneReponse` contre un `Set` connu
(`PEUPLES_ANCIENS`, `mouvements`) plutot que sur l'enonce - c'est le patron a suivre, deja present
a cote du patron fragile.

Je route ce constat, je ne le corrige pas : deux tests sur des lignes neuves, methode fragile deja
identifiee par ce meme sprint comme cause reelle d'un defaut (pas seulement de style), a corriger
sur le meme patron que ses deux voisins immediats dans le meme fichier.

### La dette generale des 25 fichiers sur 95, et si elle touche du neuf

`theo-archi` chiffre a 25 sur 95 les fichiers de test de paquet qui lisent l'enonce par une
operation de chaine (majorant assume, `confirmation-archunit.md`). **Elle touche du code ecrit ce
sprint** : les deux `.startsWith(...)` ci-dessus, dans un fichier modifie ce sprint
(`PaquetCm1HistoireGeographieTest`), s'ajoutent au perimetre de cette dette plutot que de la
reduire - alors que le meme sprint en corrige une occurrence ailleurs (`PaquetCpEmcExclusionTest`).
Le solde net de la dette sur ce sprint n'est donc pas connu sans recompter les 95 fichiers, chiffre
que je n'ai pas refait moi-meme et que je ne cite pas au-dela de ce qui precede.

### Ce qui n'est pas couvert, nomme plutot que suppose

- `CAT-1`, `EMC-1`/`EMC-8`/`EMC-10`, la lecture semantique des enonces d'histoire-geographie et
  d'anglais (l'exactitude du terme "les Sarrasins" par exemple) : hors du perimetre de tout test
  structurel, comme chaque javadoc de test de matiere le declare - frontiere assumee, pas un trou
  de ce sprint.
- Le plancher de `FER-3` (3 contre 4 items minimum) reste une divergence entre la consigne donnee
  a `marc-dev` et la constante `MINIMUM_PAR_TACHE` du depot, routee a `nadia-prof` par `sofia-sm` -
  aucun test ne peut trancher laquelle des deux valeurs est la bonne, c'est une question de source.
- Aucune ligne de `squad/programme/MATRICE-couverture.md` ne porte encore les lignes de ce sprint :
  attendu, le Done pedagogique n'est pas prononce au moment de cette mesure.

---

## 3. Tests instables

**Aucun** identifie sur les executions menees ce jour : un `clean verify` complet, plusieurs
executions ciblees de `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` (mutee puis
restauree), toutes deterministes d'une execution a l'autre.

---

## 4. Un garde-fou cite comme fermant une dette, mis a l'epreuve par moi

**`theo-archi` rapporte le retrait de l'exemption `DETTE_DE_MARQUEUR`** (clause 3 de
`LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`), cite comme fermant le rouge herite de
`S32-EMCCP07`. Je ne le prends pas pour acquis.

**Mutation** : sauvegarde du fichier vivant `adapters/adapter-content/src/main/resources/contenu/
cp-emc-regle-souhait-loi.yaml`, puis remplacement par l'etat fige que le depot conserve deja sous
`bootstrap/src/test/resources/contreexemple/.../etat-refuse-emc-cp-07/` - l'etat exact sur lequel
`nadia-prof` a maintenu le refus le 2026-09-08, avant que `S32-EMCCP07` ne le corrige.

`bash outils/mvn-jdk21.sh -pl bootstrap -am test
-Dtest=LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest
-Dsurefire.failIfNoSpecifiedTests=false`.

**Resultat : ROUGE.** `leContenuServiEstConforme` echoue avec le message exact attendu :
*"cp-emc-regle-souhait-loi.yaml : 4 items portent un marqueur de restriction (seulement) sur tous
leurs distracteurs et sur aucune part de leur bonne reponse - items 7, 8, 9, 10 [...]"* (8
tests/9, 1 echec).

**Restauration** (`cp` du fichier sauvegarde), `git status --short` vide confirme, reexecution de
la meme commande : **VERT**, 9/9, `BUILD SUCCESS` sur les six modules.

**Conclusion** : l'exemption est reellement retiree et la clause 3 mord reellement sur le contenu
vivant sans elle - ce garde-fou n'est pas decoratif.

---

## 5. Les points de qualite signales, instruits

### Le jeu est-il joue de bout en bout sur le jar ?

**Aucune trace dans le depot d'une partie jouee de bout en bout sur `recreo.jar`, ni pour l'EPS ni
pour l'EMC corrige.** J'ai lu les cinq messages de commit de contenu de s32 (`90b01fdd` marc-dev,
`b7a79e34`/`167a4421`/`ba2871ea` lea-dev, `559648c6` sofia-sm) et les deux fichiers du repertoire
de sprint (`planning.md`, `confirmation-archunit.md`) : aucun ne mentionne le jar, une partie
jouee ni le mot "jouable". Le depot Git est la seule memoire partagee de ce projet : ce qui n'y est
pas ecrit n'a pas eu lieu. Je ne peux ni confirmer ni infirmer que les paquets d'EPS et les
paquets d'EMC corriges sont reellement servis et jouables sur le jar - je constate qu'aucun lot ne
l'a verifie et ecrit, contrairement a l'affirmation qui m'a ete transmise. C'est un trou de
verification a router, pas une confirmation a rapporter.

### La famille de fausse mutation (recherche d'un caractere qui n'est pas celui du fichier)

`theo-archi` rapporte, sur sa propre premiere tentative de mutation d'`ADR-0089`, un remplacement
qui a echoue en silence : il cherchait une espace ordinaire devant un `?` final, le fichier
portait une espace insecable (U+00A0), servie par `TYP-1`. J'ai cherche cette meme famille -
un remplacement ou une comparaison qui echoue silencieusement faute de correspondre au caractere
reellement present - dans les huit fichiers Java touches ce sprint (section 2 ci-dessus liste les
tests neufs ou modifies). Je n'en ai trouve aucune autre occurrence : la seule verification de
caracteres exacts que j'ai reproduite (`.startsWith("Quelle grande période...")` face au contenu
YAML) correspond point de code pour point de code des deux cotes. Cette famille de defaut ne s'est
donc pas reproduite ailleurs dans ce sprint, sur ce que j'ai pu verifier - je n'ai pas rejoue
chacune des huit modifications par mutation, seulement celle nommee en section 4 et celle citee
ici.

### La dette de 25 fichiers sur 95 lisant l'enonce par operation de chaine

Traitee en section 2 : elle touche du code ecrit ce sprint (`PaquetCm1HistoireGeographieTest`,
deux occurrences neuves), et le meme fichier montre, deux lignes plus loin, le patron qui l'evite.

---

## 6. Mon avis sur la porte technique

**Verte**, sur ma propre mesure de `7c800261` : les quatre jobs de `.github/workflows/ci.yml`
passent (section 1), 1 439 tests sans echec ni erreur, aucun module sans test, le garde-fou cite
comme fermant le rouge herite de s32 mord reellement quand je le mets a l'epreuve (section 4).

Cet avis porte uniquement le Done technique. Le Done pedagogique reste entier a `nadia-prof` -
rien dans ce rapport ne le prejuge, dans un sens ou dans l'autre.

**Un point reste ouvert et n'est pas couvert par cette porte** : la section 5 constate qu'aucune
partie n'est rapportee comme jouee de bout en bout sur le jar pour les paquets d'EPS et d'EMC
corriges de ce sprint. Ce n'est pas un obstacle a la porte technique - `mvn verify` ne demande pas
cette preuve -, mais c'est une verification manquante que je signale plutot que de la deduire
close.

---

## 7. Fichiers touches par cette review (tous restaures, tree propre a la fin)

Une seule mutation temporaire, restauree par copie du fichier sauvegarde et verifiee par
`git status --short` vide avant et apres :

- `adapters/adapter-content/src/main/resources/contenu/cp-emc-regle-souhait-loi.yaml` (section 4).

Aucune autre modification n'est laissee dans l'arbre par cette review.
