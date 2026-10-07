# Review - s52 - cinq lignes CE1 fermees sur cinq engagees, une ligne ouverte en partie, deux corrections levees, deux paquets de sciences CM1 retires

- **Horodatage de debut** (`planning.md`) : 2026-10-06 20:51 (UTC+02:00), lu par `date` a l'ecriture du planning.
- **Horodatage de fin** : 2026-10-07 10:01 (UTC+02:00), lu par `date` (10:01:13) juste avant l'ecriture de ce fichier.
- **Duree effective** : 13 h 10 min.
- **Casquette** : `sofia-sm`, Scrum Master. Aucune story de developpement portee par moi a s52.
- **Verifie avant d'ecrire** : `git rev-parse --abbrev-ref HEAD` rend `round-two`, `git rev-parse --short HEAD` rend
  `509552f0`, `git status --short` est vide.
- **Arbre revu** : `round-two` a `509552f0`, 45 commits depuis `93aade92`, tete de `round-two` avant mon planning
  (`93aade92..509552f0` : 32 hors fusions, dont mon planning `b4ce7d93`, et 13 fusions de moi).
- **Lignee des branches de s52, verifiee par moi** : `git merge-base --is-ancestor b4ce7d93 <branche>` reussit pour
  les huit (`s52-retrait-sciences-cm1`, `s52-nadia-instance-a`, `s52-nadia-instance-c`, `s52-theo-a1-a2bis`,
  `s52-ines-contraintes`, `s52-camille-amendements`, `s52-theo-archunit`, `s52-yanis-qualite`) ; `git
  merge-base --is-ancestor ceffde66 s52-nadia-instance-c` reussit aussi (obstacle 4).

---

## 1. L'increment livre contre l'objectif

**Objectif gele** (`planning.md`, repris de `DEC-0111` section 1) : jusqu'a cinq lignes a `couvert-valide`
(`FRA-CE1-17`, `-25`, `-26`, `MAT-CE1-17`, `MAT-CE1-06`), `MAT-CE1-02` a `en cours`, `TPS-CE1-R1` et `ALP-CE1-R1`
levees, deux paquets de sciences CM1 retires du catalogue servi, aucune instance au-dela de 5,0 ; 127/182 au
depart, **132/182 au mieux**.

**Livre** : l'objectif est atteint dans son meilleur cas. **127/182 (69,8 %) a 132/182 (72,5 %).** Un refus a
l'audit (`FRA-CE1-17`, premier passage), rejoue et leve dans le sprint. Budget reel A 4,5 et C 4,5, plafond de 5,0
tenu (`DEC-0111` A.11, obstacle 9 pour la lecture du rejeu de C).

| Poste | Objectif | Livre |
|---|---|---|
| Retrait des deux paquets de sciences CM1 | hors capacite de `nadia-prof` | **atteint** : `2278bdfb` [MARC], fusion `789af8a9` ; `grep -c 'activite-humaine-cm1\|terre-cm1' adapters/adapter-content/src/main/resources/catalogue.yaml` rend 0 |
| `FRA-CE1-17` | `couvert-valide` | **atteint** (REFUSE au premier passage, VALIDE au second) |
| `FRA-CE1-25` | `couvert-valide` | **atteint** (VALIDE SOUS RESERVE) |
| `FRA-CE1-26` | `couvert-valide` | **atteint** (VALIDE) |
| `MAT-CE1-06` | `couvert-valide` | **atteint** (VALIDE SOUS RESERVE) |
| `MAT-CE1-17` | `couvert-valide` | **atteint** (VALIDE SOUS RESERVE, puis VALIDE au second passage) ; non engagee au planning, re-engagee par `DEC-0111` A.5 et A.6 |
| `MAT-CE1-02` | `en cours` (moitie textuelle) | **atteint** (VALIDE sur la moitie textuelle, ne ferme pas) |
| `TPS-CE1-R1`, `ALP-CE1-R1` | levees | **atteintes**, lignes `FRA-CE1-20` et `FRA-CE1-23` non rouvertes |

**Comptage de la couverture, refait par moi** avec la commande ecrite dans `squad/programme/MATRICE-couverture.md`
(section "Methode du compte", un `awk` sur la septieme cellule), lancee sur la matrice a `b4ce7d93` puis a
`509552f0` (`git show <sha>:squad/programme/MATRICE-couverture.md | awk -F'|' ...`, perimetre : la matrice
entiere) :

| Arbre | `couvert-valide` | `en cours` | `non couvert` | `non couvrable en l'etat` | Total |
|---|---|---|---|---|---|
| `b4ce7d93` (planning) | 127 | 3 | 52 | 76 | 258 |
| `509552f0` (cloture) | **132** | 4 | 46 | 76 | 258 |

Denominateur atteignable 182 (258 moins 76). 127/182 = 69,78 %, 132/182 = 72,53 %. Le comptage concorde avec
`DEC-0111` A.9.

---

## 2. Done technique

**Juge : la CI, tous les jobs de `.github/workflows/ci.yml`** : `prefixes`, `chemins`, `outils`, `build`. **Rien
n'est pousse : la CI n'a rendu aucun verdict sur ces commits.**

### 2.1 Ce que le rapport qualite de `yanis-qa` prouve

`rapport-qualite.md`, arbre `40daa419` (fusion du retrait, de A, de C et des amendements), les quatre jobs lances
par `yanis-qa` :

| Job | Resultat lu dans `rapport-qualite.md` |
|---|---|
| `prefixes` | **vert** : deux scripts de test, puis `verifier-prefixes.sh` sur `b4ce7d93..40daa419` (23 sujets hors fusions) |
| `chemins` | **vert** : script de test, puis `verifier-chemins.sh` sur tous les fichiers suivis |
| `outils` | **vert** : 9 juges purs executes (5 + 2 + 2), 0 echec |
| `build` | **vert** : `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, `BUILD SUCCESS`, **2172 tests** (2166 unitaires et ArchUnit, 6 d'integration), 0 echec, 0 erreur ; JDK 21.0.10 rapporte par la machine virtuelle des tests. Avec le test de la question Q6 ajoute (`UnePartieOuverteSurUnJeuRetireNeServeAucunResultatTest`, 2 tests) : **2174**, `BUILD SUCCESS` |

Reserves ecrites par `yanis-qa` : le pas `package` du job `build` n'est pas rejoue ; le job `prefixes` n'a pas
exerce la branche `BASE` du workflow ; **le rapport ne dit rien de la porte pedagogique**.

### 2.2 Ce que le rapport qualite ne couvre pas : les commits posterieurs a `40daa419`

Le rapport porte sur `40daa419`. Huit commits hors fusions le suivent (`git log --no-merges 40daa419..509552f0`),
plus six fusions de moi. `git diff --stat 40daa419 509552f0` : 18 fichiers.

| Commit | Auteur | Nature |
|---|---|---|
| `337a96b5`, `8035f4de`, `afff61b0` | `[CAM]` | **texte seul** : `DEC-0111` (amendements A.9 a A.16 et du 2026-10-07), `DEC-index.md`, `MATRICE-couverture.md`, `backlog.md` |
| `077f2928` | `[THEO]` | **texte seul** : `confirmation-archunit.md`, `ADR-0080` amendement A2ter, `ADR-index.md`, correction de `confirmation-archunit.md` de s51 |
| `7e965956` | `[THEO]` | **texte seul** : `avis-faisabilite.md` (section 3.6, compte du lexique) |
| `e1999dec` | `[INES]` | **texte seul** : contrainte d'`ines-design` pour l'instance C (barre de refus de 15 unites) |
| `d9696d0e` | `[YAN]` | **un test neuf** (`UnePartieOuverteSurUnJeuRetireNeServeAucunResultatTest`, 2 tests), deja compte dans les 2174 de `yanis-qa`, et le rapport lui-meme |
| `d8ace7c1` | `[REL]` | **commentaires et Javadoc seulement**, dans trois fichiers YAML de paquet et trois fichiers de test (renvoi `DEC-0111` section 5.4 point 2, commentaire de `Q2`, en-tete de `MUL-CE1-4`), plus le compte de `rapport-qualite.md`. Verifie par moi : `git diff 40daa419 509552f0 -- '*.yaml'` ne rend **aucune ligne** ajoutee ou retiree qui ne soit pas un commentaire `#` ; aucun contenu servi a l'enfant n'a change |

Pour combler l'ecart, j'ai relance moi-meme les quatre jobs sur `509552f0`, **avant d'ecrire les fichiers de
cloture** :

| Job | Commande | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet | `BUILD SUCCESS`, 3 min 06 s, six modules `SUCCESS` ; **2174 tests** : domain 120, application 17, adapter-web 401, adapter-content 1318, bootstrap 312 surefire + 6 failsafe ; 0 echec, 0 erreur, 0 ignore, somme des attributs `tests` des rapports XML apres `clean` ; premiere ligne de l'enveloppe : verdict rendu sur le fichier de toolchains que Maven lit, JDK de la toolchain 21.0.10 |
| `prefixes` | les deux scripts de test, puis `git log --no-merges --format=%s b4ce7d93..HEAD \| bash .github/scripts/verifier-prefixes.sh` | vert, 31 sujets |
| `chemins` | script de test, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | vert |
| `outils` | boucle du workflow, trois motifs, interpreteur `python` (`python3` resout un lanceur inutilisable sur ce poste) | vert, **9 juges, 0 echec** |

Limites : les trois jobs `prefixes`, `chemins` et `outils` ont tourne sur l'arbre **avant** l'ajout de mes fichiers
de cloture. Apres leur ecriture, j'ai relance le job `build` (`bash outils/mvn-jdk21.sh -B clean verify`, arbre de
`509552f0` plus les fichiers de ce dossier) : `BUILD SUCCESS`, 5 min 01 s, **2174 tests**, 0 echec, 229 dans
`com.recreo.archi` (la suite qui lit `squad/`) ; `verifier-chemins.sh` sur les noms de ces fichiers : vert. Le sujet du
commit est verifie par `verifier-prefixes.sh` avant d'etre cree. Le pas `package` n'est pas rejoue non plus. Le build ne
tourne pas sur un arbre LF : voir obstacle 2.

**Total des tests, etabli sur l'arbre fusionne** : 2174, identique a la mesure de `yanis-qa` avec le test Q6. Origine
de l'ecart avec les annonces :

| Annonce | Valeur | Arbre | Origine de l'ecart avec 2174 |
|---|---|---|---|
| Avis de `theo-archi`, section 0 | 2082 | `7350fb07`, job `build` seul | +92 = retrait +2, instance A +41, instance C +47, test Q6 +2 |
| Rapport de `yanis-qa` | 2172 | `40daa419` | +2 : le test Q6, ajoute apres |
| Total de s51 (`ca4c934a`) | 2076 | s51 | +98 = +6 de `ADR-0080` A2 (`edf15763`, avant le planning) + les 92 ci-dessus |

Mes mesures de fusion, un reacteur complet par commit de fusion, **journaux conserves dans le scratchpad de session
(hors depot), sommes relues par moi aujourd'hui** : retrait **2084** (2082 + 2), U1 2084, instance A **2125**
(2084 + 41), instance C a la premiere fusion 2166 avec 1 echec (obstacle 2), puis **2172** apres `173b48f7`, tip de
cloture **2174**. Les bases 2084 et 2125 que `yanis-qa` reconstruit par arithmetique (section 3 de son rapport)
sont donc retrouvees par un build. Le decoupage par classe de test est celui de `yanis-qa` ; je ne l'ai pas
recompte annotation par annotation.

---

## 3. Confirmation ArchUnit - `theo-archi`

`confirmation-archunit.md`, arbre `40daa419`, mesure dans un worktree detache :

- **Commande** : `bash outils/mvn-jdk21.sh -B clean test -pl bootstrap -am '-Dtest=com/recreo/archi/**/*'
  -Dsurefire.failIfNoSpecifiedTests=false`. Perimetre : le seul paquetage `com.recreo.archi` ; ce n'est pas le
  reacteur complet.
- **Resultat** : **229 tests, 0 echec, 0 erreur, 0 ignore.** +13 depuis s51 (216) : deux classes neuves de `lea-dev`
  (`LesFiguresDeSymetrieMontrentCeQueLeursItemsAffirmentTest` 6, `LesReseauxDePointsMontrentCeQueLeursItemsAffirmentTest` 7),
  des tests JUnit, pas des `ArchRule`.
- **Verifie par moi** sur `509552f0` : les rapports XML de `bootstrap` donnent 229 tests, 0 echec dans les 32 classes
  de `com.recreo.archi` ; `git grep` sur les arbres de Git (methode d'`ADR-0104`) rend **39** `static final ArchRule`
  et **36** `@ArchTest`, contre-exemples compris, **37 / 36** exclus.
- **Regles ajoutees ce sprint** : **aucune.** Compte inchange depuis `ca4c934a` et `b4ce7d93`.
- **Clean Architecture** : aucun fichier de `domain` ou d'`application`, **0 ligne de `src/main/java`**, aucun
  `pom.xml`, gabarit, script de CI ou d'outil entre `b4ce7d93` et `40daa419` ; `adapter-web` ne recoit que 14 SVG ;
  deux lignes `springframework`, toutes deux dans un test de `bootstrap`.
- **Cliquets** (remesures par `theo-archi` hors test, non remesurees par moi, jugees par les tests verts de mon
  build) : poids total 69 203, fichiers d'illustration 56, items illustres 81, plafond de designation 3, dette de
  lisibilite 3.
- **`ADR-0080`** : amendements A2 (`edf15763`, avant le planning), A2bis (`ceffde66`) et **A2ter** (`077f2928`), qui
  met l'`ADR` en accord avec l'arbitrage de `DEC-0111` A.7 (barre de refus de 15 unites, 16 unites marge de dessin) ;
  aucune regle executable neuve, motif ecrit dans l'`ADR`.
- **`ADR-0104`** : statut `Propose`, inchange sur le disque ; `nadia-prof` a repondu a `C5` clause 1 le 2026-10-06
  (`ARBITRAGE-PREALABLE-s52-fra-ce1-questions-de-l-avis.md`, section 4.2) ; le statut est a `theo-archi`.

---

## 4. Relecture - `relecteur-pedagogique`

Je n'ai pas de liste de corrections ecrite du relecteur dans le depot ; je cite ce que les commits changent.

- **`d8ace7c1`** [REL] : trois renvois a `DEC-0111` section 5.4 "point 7" corriges en "point 2" (la section n'a que
  deux points), commentaire et Javadoc du paquet de synonymes alignes sur l'amendement de `nadia-prof` (l'explication
  de `Q2` n'est plus exigee), en-tete du paquet de multiplication (`MUL-CE1-4`, deux items de chaque reseau), compte
  de `rapport-qualite.md` (316 = 310 + 6). Message de commit : *"Aucun contenu servi a l'enfant modifie"* ; confirme par
  mon controle de la section 2.2.
- **Trois ecarts rendus au `[CAM]` et corriges dans `afff61b0`** (amendement du 2026-10-07 de `DEC-0111`) : section
  11 contredite par trois identifiants alloues en cours de sprint, forme 3 ecrite "recue" au lieu de "recu", renvoi de
  la cellule A.5 (ligne 205 du test, pas 201).
- **Compte du lexique corrige par `theo-archi`** (`7e965956`) : obstacle 6.
- **Points a trancher** : aucun n'est route par le relecteur. Les fichiers de ceremonie de cette cloture et
  l'amendement du planning sont relus **apres** leur ecriture, par une ligne du `relecteur-pedagogique` : je n'en
  fabrique pas.

---

## 5. Tableau des obstacles du sprint

| # | Obstacle | Categorie | Route a | Decision liee | Statut a la cloture |
|---|---|---|---|---|---|
| 1 | **Worktree du retrait impossible a creer sous le nom du planning** (`../recreo-s52-retrait-sciences-cm1`) : le chemin suivi le plus long du depot fait 219 caracteres (`git ls-files \| awk '{ print length($0) }'`) ; avec le nom du planning le prefixe du dossier de worktrees ajoute 41 caracteres, soit **260** ; avec `../recreo-s52-retrait`, 28, soit 247. Le worktree de l'instance C (`../recreo-s52-nadia-instance-c`) est a **256**, 4 caracteres de marge | Technique / poste Windows | `sofia-sm` (nommage) | - | **Contourne, constate.** Worktree `../recreo-s52-retrait`, branche `s52-retrait-sciences-cm1` (`git worktree list`). Le chiffre de 260 est mon recalcul ; je n'ai pas reproduit l'echec de `git worktree add` lui-meme. Aucun fichier de regle ne dit de verifier la longueur d'un nom de worktree : proposition dans la retro, non ecrite |
| 2 | **Test de la symetrie fragile aux fins de ligne CRLF**, trouve par l'orchestrateur a la fusion de C : `core.autocrlf=true`, les SVG sont commites en LF et extraits en CRLF (`git ls-files --eol` : `i/lf w/crlf`), un `replaceFirst` sur `\n` n'avait aucun effet, la mutation du test etait un no-op muet | Technique / qualite | `lea-dev` (test), `yanis-qa` (verification) | - | **Corrige et verifie.** Premiere fusion de C : 2166 tests, **1 echec** (`LesFiguresDeSymetrieMontrentCeQueLeursItemsAffirmentTest.uneCaseQuiManqueDeTropFaitEchouerLeGardeFou`), `BUILD FAILURE`. Correctif `173b48f7` [LEA] : dessins normalises en LF avant mutation, la mutation asserte qu'elle a change le dessin, dans les deux classes de dessins. Remesure : **2172, 0 echec**. `yanis-qa` (section 6 de son rapport) a mesure 2172 verts sur un arbre CRLF ; mon build a `509552f0` tourne dans
le checkout principal, ou `git ls-files --eol` rend `i/lf w/crlf` pour un SVG de symetrie : vert aussi. **Non mesure** : le sens LF partout (celui de la CI Linux), et la preuve sur copie CRLF de `lea-dev` est un journal du scratchpad, hors depot, que je n'ai pas relu. Le defaut de classe (un test qui lit un fichier du disque et suppose les fins de ligne) n'a pas de garde-fou : question a `theo-archi` dans la retro |
| 3 | **Conflits a la fusion de C** (`40daa419`) : `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (liste de paquets, A et C y ajoutent) et `REGISTRE-identifiants.md` (A et C ajoutent leurs identifiants) | Technique / fusion | `sofia-sm` | `ADR-0085` (garde-fou du registre) | **Resolu par moi, aucune assertion affaiblie.** Registre, meme commande que la CI et que `DEC-0111` (2026-10-07) : 919 au planning, 920 cote A (+1, `SYC-CE1-R1`), 921 cote C (+2, `MUL-CE1-R1` et `SYM-CE1-R1`), **922** apres fusion, 158 prefixes (ligne *"Compte courant"* du registre : 922 et 158) ; aucun marqueur de conflit ; le garde-fou du registre est vert dans mon build. Le test de rang : les ajouts des deux cotes sont presents (`ce1-categories`, `ce1-conjugaison-present`, `ce1-synonymes-contraires`, `ce1-comparer-ranger`, `ce1-symetrie`). **Correction d'un chiffre de s51** : la review de s51 (obstacle 2) ecrit 918 puis 923 ; la meme commande rend **914 a `e4d8ba3b` et 919 a `047b91c4`** (+2 et +3, deltas justes). Je ne sais pas quelle commande avait rendu 918 ; je n'edite pas les fichiers de s51, la correction est ici |
| 4 | **A1 integre sans recommit** : la correction du message du cliquet de designation (`ceffde66`, `[THEO]`) est arrivee sur `round-two` par fusion (`5b199825`), et la branche de C a ete ouverte depuis ce tip | Process / duplication de commit (s51, obstacle 4) | `theo-archi` | `ADR-0080` A2 et A2bis | **Evite, clos : les fichiers existent.** `git merge-base --is-ancestor ceffde66 s52-nadia-instance-c` reussit ; `git log --all` sur le test du cliquet ne rend qu'un commit pour A1 ; `ADR-0080` porte A2, A2bis et A2ter. Le prix de cet ordre : C n'a pu s'ouvrir qu'apres U1 |
| 5 | **Fichier ecrit hors scratchpad** : `c3`, 277 octets, cookies de `curl` d'apres sa taille et son nom (je ne l'ai pas lu), a la racine du worktree de C, 2026-10-06 23:06, **non suivi** (`git status --short` du worktree : `?? c3`), absent de `round-two` (`git ls-tree HEAD c3` : vide). Ni `lea-dev` ni `nadia-prof` ne l'a declare | Process / isolation (README section 9) | `lea-dev`, `nadia-prof` C | - | **Ouvert.** Auteur non etabli. Je ne l'ai pas supprime. Je n'en trouve **qu'un** pour s52 : les sept autres worktrees de s52 sont propres, et les quatre fichiers hors depot du dossier parent des worktrees datent du 2026-08-06 au 2026-09-06 |
| 6 | **Compte du lexique faux dans l'avis** : *266 mots*, qu'aucun des deux comptes ne reproduit | Redaction / chiffre | `theo-archi` | - | **Corrige par `theo-archi`** (`7e965956`) : 273 entrees, 268 mots distincts, methode ecrite ; l'avis le dit dans la ligne corrigee |
| 7 | **F1 de `yanis-qa`** : un coup sur un jeu retire (session conservee) est journalise en `ERROR` avec sa pile, pas en `INFO` comme `POST /parties/{inconnu}` | Technique / qualite | `theo-archi` | `ADR-0018` point 2 | **Resolu : pas un defaut.** Avis de `theo-archi`, section 3.6 (fichier existant) : `ERROR` est le bon niveau, la situation est inatteignable en production. Il l'a lu dans le code, **non execute** sur le cas de `S5-1`. **Reste a `yanis-qa`** : epingler le niveau `ERROR` dans son test (aujourd'hui il ne l'asserte pas) |
| 8 | **Ecart du poste entre le fichier de toolchains du shell et celui que Maven lit** (`HOME` du shell contre `user.home` de la machine virtuelle) : le premier declare un JDK 25 sous l'entree 21 | Technique / poste | `theo-archi` (verdict), utilisatrice (poste) | - | **Pas un defaut de script** (`confirmation-archunit.md` section 6). Reproduit : mon build imprime *"Verdict rendu sur le fichier de toolchains que Maven lit, qui n'est pas celui de ce shell"*, `verifier-jdk.sh` rend *"toolchains lu par Maven : oui (un autre que celui lu par ce script)"*, JDK de compilation 21.0.10. **Ouvert, hors depot** : le fichier de toolchains du shell est perime, a corriger sur le poste par l'utilisatrice |
| 9 | **Rejeu de la symetrie compte 0,5 par `PAR-10`** (lecture de `camille-po`, `DEC-0111` A.11) ; la lettre de `PAR-14` (arbitrage de s33) le ferait payer au tarif d'un lot, ce qui mettrait C a 5,5 | Produit / budget | `nadia-prof` | `DEC-0111` A.11, A.16 | **Ouvert.** `nadia-prof` n'a ecrit aucun prix dans la fiche (`grep` sur `FICHE-designation-symetrie-ce1.md` : aucune occurrence de `PAR-10`, `PAR-14` ni de prix). Elle confirme ou corrige, et amende `DEC-0111` si elle lit `PAR-14` a la lettre |
| 10 | **Item 16 de `FRA-CE1-25`** (*"Il arrive a l'ecole."*, reponse *part*) : observation de `nadia-prof`, ni reserve ni condition, a remplacer par *"Le train arrive."* ; **change le sens d'un item, donc re-audit** | Pedagogie / contenu | `camille-po` (story), `nadia-prof` (rejeu) | `DEC-0111` amendement du 2026-10-07 | **Route, non engage.** Ajoute aux criteres de la story `SYC-CE1-R1` du backlog (section "Cloture de s52"), a re-auditer avec les items 19 et 23 dans le meme rejeu. Premier report, s53 |
| 11 | **Trailers `Co-Authored-By`** : le planning citait 568 commits "Opus 5.5", somme de quatre variantes | Redaction / attribution | `sofia-sm` (constat) | `DEC-0110` 17.4, `squad/README.md` section 8 | **Resolu en constat.** Ventilation a `93aade92` (`git rev-list --count --grep='^Co-Authored-By: <valeur> <' 93aade92`) : 63 "Opus 5.5", 369 "Opus 5", 132 "Opus 5 (1M context)", 4 "Opus 5.5 (1M context)" = 568 ; le comparable (trailers "Opus 5.5") est 67. La regle de `DEC-0110` 17.4, transcrite a la section 8 du README par le planning, est appliquee : sur `93aade92..509552f0`, **0 ecart** (10 commits `[THEO]` et `[NAD]` en "Opus 5.5", 35 autres en "Sonnet 5.5" ; le rapport de tokens de s52 place `theo-archi` et `nadia-prof` sur `claude-opus-5-5`). La CI ne verifie pas le trailer : assume dans le README |
| 12 | **Mesure de fusion de cloture interrompue** : le premier reacteur complet de la fusion de cloture n'a pas termine (bootstrap `FAILURE` apres 08:46 h, fork termine par le code 143 dans `AucunTexteServiNEnchasseLaReponseDansUnePhraseTest`, 250 tests de `bootstrap` faits sur 312) | Qualite / poste | `yanis-qa` | - | **Cause non etablie.** Relance : 2174 tests, 0 echec, puis 2174 de nouveau, et mon build a `509552f0` aussi. Le journal est dans le scratchpad, hors depot. A cote du constat de `yanis-qa` (*"deux passages ne prouvent pas l'absence d'instabilite"*) : trois passages verts du meme test depuis |
| 13 | **Entete `Statut global` des six fiches de conformite de s52** : toutes portent encore *"en construction"* alors que leur verdict est ecrit (celles de `FRA-CE1-20` et `-23`, de s51, portent le statut du verdict) | Redaction / fiches | `camille-po`, `nadia-prof` | - | **Ouvert**, constate par `grep -n "Statut global"` sur les six fiches. Sans effet sur la matrice, que les verdicts pilotent. Je n'edite pas les fiches |
| 14 | **Points routes du planning (R1 a R17)** | divers | divers | voir ligne | **R1** (retrait et `LeRang...Test`) : clos, `DEC-0111` A.1. **R2** (plancher de zone) : clos, `ADR-0080` A2bis et A2ter. **R3** (condition (1)) : clos, `DEC-0111` A.2 et A.5. **R4** (six fiches) : clos, six fiches sur le disque. **R5, R6, R7** (`Q1`, `Q2`, `Q3`) : clos, `ARBITRAGE-PREALABLE-s52-fra-ce1-questions-de-l-avis.md` (sections 1 a 3). **R8** (`Q4`) : clos, `ARBITRAGE-PREALABLE-s52-instance-c-reponses.md` section 2. **R9** : clos par le planning. **R10** (`Q6`) : clos, test de `yanis-qa`. **R11** : aucun arbitrage de `theo-archi` n'est ecrit au depot sur la forme du test ; le test existe (`LesJeuxRetiresDuCatalogueNeSontPasServisTest`, `2278bdfb`), je ne juge pas sa forme. **R12** (10 x 10) : clos, admis jusqu'a 10 x 10 (reponses de `nadia-prof`, section 1). **R13** : `PAR-9` maintenue (section 4.3), `PAR-4` voir ligne 15. **R14** : `nadia-prof` a repondu a `C5` clause 1, `ADR-0104` reste `Propose`, voir ligne 15. **R15** (palier 2) : traite, `DEC-0111` A.15. **R16** : non engagee, voir ligne 15. **R17** : clos (`awk 'length($0)>150'` rend 0 ligne sur l'arbitrage de s51 ; `confirmation-archunit.md` de s51 corrigee) |
| 15 | **Reports et points ouverts a l'ouverture de s53** : reprises de sciences CM1 (`S52-SCI-*-REPRISE-APRES-RETRAIT`) et `CRP-CM1-R1` a `R3` (troisieme report), `MAT-CE1-02` moitie designation (troisieme), `PAR-4` (troisieme), `S51-OUTIL-JOURNAL-TOKENS` (troisieme), `ART-CP-10` et le statut d'`ADR-0104`, `SYC-CE1-R1` et `MUL-CE1-R1` (premier report, 0,5 chacune), `PAR-9` maintenue pour s53 | Produit | `camille-po`, `nadia-prof`, `theo-archi` | `DEC-0111` A.13, A.14, A.16 | **Ouverts.** Aucun n'est engage a s52 ; le perimetre de s53 attend l'utilisatrice (`DEC-0111` A.14) |
| 16 | **Verification de la condition (ii) de la borne a trois instances** (formes (a), (b), (c) de couture) pour le dernier sprint parallele : s52 est ce sprint | Process / pedagogie | `nadia-prof` | `ARBITRAGE-PREALABLE-s52-instances-ce1.md` section 6 | **Ouvert, a ecrire a la cloture de s52.** Aucun fichier de verification de s52 n'existe parmi les trois fichiers de `nadia-prof` de s52 (`ls squad/conformite`). Faits que je lui remets : obstacles 2, 3 (dont le chiffre de s51 corrige) et 5. Je ne les qualifie pas |

Rappel : un obstacle n'est clos que si le fichier de decision existe physiquement. Clos sur fichier existant : 4
(`ADR-0080`), 6 (`avis-faisabilite.md`), 7 (`avis-faisabilite.md` section 3.6), et les lignes R1 a R12 de la ligne 14.
Les autres sont resolus ou constates sans decision a ecrire, ou ouverts.

---

## 6. Done pedagogique - `nadia-prof`

Prononce **uniquement** par `nadia-prof`, apres avoir joue chaque paquet. Je rapporte les verdicts, lus dans chaque
fiche, je n'en rends aucun. Instance A : `s52-nadia-instance-a` ; instance C : `s52-nadia-instance-c`. Les huit
fiches citent `MNC-1` (`grep -c MNC-1` : au moins une occurrence chacune) ; je n'ai pas verifie que chaque occurrence
est le bloc de mention reproduit mot pour mot.

| Ligne | Fiche | Verdict final | Episode |
|---|---|---|---|
| `FRA-CE1-17` | `FICHE-bandit-manchot-conjugaison-present-ce1.md` | **VALIDE**, sans reserve, sans mention | Premier passage **REFUSE** (`4965fe7a`) : `TRV-CE1-5`, les indices des items 9 et 24 renvoient a l'ecoute la ou la bonne reponse et son distracteur se prononcent de meme. Rejeu `40989ba9` (`marc-dev`), 0,5. Second passage **VALIDE** (`ed90a523`) |
| `FRA-CE1-25` | `FICHE-bandit-manchot-synonymes-contraires-ce1.md` | **VALIDE SOUS RESERVE** | Reserve `SYC-CE1-R1` : deux activites servies du cote du stereotype (items 19 et 23). Pas de mention. Deux observations non reserve : item 16 (obstacle 10), elision devant un mot cite (route au `relecteur-pedagogique`) |
| `FRA-CE1-26` | `FICHE-bandit-manchot-categories-ce1.md` | **VALIDE**, sans reserve | Un passage (`4965fe7a`) |
| `FRA-CE1-20` | `FICHE-bandit-manchot-temps-ce1.md` | **`TPS-CE1-R1` levee** | Ligne deja `couvert-valide`, non rouverte ; quatre prenoms changes (`e1fcec51`) |
| `FRA-CE1-23` | `FICHE-bandit-manchot-ordre-alphabetique-ce1.md` | **`ALP-CE1-R1` levee** | Ligne deja `couvert-valide`, non rouverte ; trois indices changes (`3bc2e501`) ; levee jouee sur le jar construit a `befc4128` |
| `MAT-CE1-06` | `FICHE-bandit-manchot-sens-multiplication-ce1.md` | **VALIDE SOUS RESERVE** | Reserve `MUL-CE1-R1` : la premiere phrase des six enonces de reseau parle de "deux facons" avant de les nommer. Dispositions dessinees, `MUL-CE1-4` et `MUL-CE1-5` tenues : **aucune mention due** (`MUL-CE1-7`) (`a320cefe`) |
| `MAT-CE1-17` | `FICHE-designation-symetrie-ce1.md` | **VALIDE** au second passage, `SYM-CE1-R1` levee | Premier passage **VALIDE SOUS RESERVE** (`e7409b43`) : reserve `SYM-CE1-R1`, ordre de declaration des zones (haut en bas, gauche a droite). Correction `bc7cf6a0` (`lea-dev`) ; second passage `3c97a87a` : 11 items sur 11, memes zones, memes dessins, seul l'ordre de declaration change. Mention de couverture fixee d'avance (arbitrage de s43, lot 3, section 3) **mot pour mot**. `ZON-11` n'est pas encodee : `nadia-prof` seule garde |
| `MAT-CE1-02` | `FICHE-bandit-manchot-comparer-ranger-ce1.md` | **VALIDE** sur la moitie textuelle | `couvert-valide` impossible : la ligne passe a **`en cours`**, avec la mention d'`ORD-CE1-5` mot pour mot (`a320cefe`) |

Cinq fermetures, un refus au premier passage rejoue et leve, deux reserves neuves non engagees (`SYC-CE1-R1`,
`MUL-CE1-R1`), une reserve neuve levee dans le sprint (`SYM-CE1-R1`), deux reserves de s51 levees (`TPS-CE1-R1`,
`ALP-CE1-R1`). **Aucun refus final.** Les six fiches de s52 portent un verdict ; l'entete `Statut global` n'en rend pas
compte (obstacle 13). Audit joue pour les paquets de A et de C ; la fiche de `FRA-CE1-17` ecrit que le rendu n'a pas
ete vu au navigateur (appels HTTP reels, HTML servi lu) et que la porte technique n'a pas ete mesuree par `nadia-prof`.

---

## 7. La couverture du programme

**127/182 (69,8 %) a 132/182 (72,5 %)**, mesuree par la commande de la matrice (section 1). Cinq lignes fermees,
une ouverte (`MAT-CE1-02`, `non couvert` a `en cours`, ne compte pas au numerateur). `MAT-CE1-17` a attendu neuf
sprints de report (s43 a s51) et s'est fermee au dixieme (`DEC-0111` A.12).

---

## 8. Ce que cette review ne fait pas

- Elle ne prononce aucun Done pedagogique : les verdicts sont ceux des fiches.
- Elle n'annonce pas la porte technique **verte pour la CI** : `yanis-qa` et moi avons lance les quatre jobs en
  local, la CI n'a pas rendu de verdict, rien n'est pousse.
- Elle ne change aucun statut de la matrice et n'edite ni `DEC-0111`, ni le backlog, ni les fiches, ni les `ADR`, ni
  `REGISTRE-identifiants.md`.
- Elle n'ouvre pas s53 et ne propose pas son perimetre : c'est le role de `camille-po` (`DEC-0111` A.14).

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee par cette review.** Decisions du sprint, rapportees et non prises
ici (liste dans `RAPPORT.md`) : `DEC-0111` (creee, amendee quatre fois) et `ADR-0080` (amendements A2, A2bis, A2ter).
