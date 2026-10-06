# Review - s51 - trois lignes de francais CE1 et une ligne de sciences CM1 fermees sur six engagees, une correction levee, deux refus de sciences CM1

- **Horodatage de debut** (`planning.md`) : 2026-10-05 20:51 (UTC+02:00), lu par `date` a l'ecriture du planning.
- **Horodatage de fin** : 2026-10-06 15:39 (UTC+02:00), lu par `date` (15:39:33) juste avant l'ecriture de ce fichier.
- **Duree effective** : 18 h 48 min.
- **Casquette** : `sofia-sm`, Scrum Master. Aucune story de developpement portee par moi a s51.
- **Verifie avant d'ecrire** : `git rev-parse --abbrev-ref HEAD` rend `round-two`, `git rev-parse --short HEAD`
  rend `047b91c4`, `git status --short` ne rend que la modification de `planning.md` (amendement de cloture).
- **Arbre revu** : `round-two` a `047b91c4`, 29 commits depuis le planning (`e4d8ba3b..047b91c4`), dont 4
  fusions de moi (`84d5b509`, `7716be0e`, `9e12bac7`, `00311e42`).
- **Lignee des branches de s51, verifiee par moi avant la cloture** : `git merge-base --is-ancestor e4d8ba3b
  <branche>` reussit pour les quatre (`s51-nadia-instance-a`, `s51-nadia-instance-b`, `s51-theo-archunit`,
  `s51-yanis-qualite`). Les deux branches de contenu avaient ete fusionnees par moi avant ; je n'ai pas relu
  leur lignee au moment de la fusion, je la constate sur leur etat final.

---

## 1. L'increment livre contre l'objectif

**Objectif gele** (`planning.md`, repris de `DEC-0110` section 14.3) : porter a `couvert-valide` jusqu'a six
lignes (`FRA-CE1-22`, `-20`, `-23` ; `SCI-CM1-10`, `-15`, `-18`) et lever `HOM-CE1-R1`, sans qu'aucune
instance de `nadia-prof` depasse 5,0 ; 123/182 au depart, **129/182 au mieux**.

**Livre** : quatre lignes sur six passent de `non couvert` a `couvert-valide` (`FRA-CE1-22`, `FRA-CE1-20`,
`FRA-CE1-23`, `SCI-CM1-10`) et la reserve `HOM-CE1-R1` est levee. Couverture : **123/182 (67,6 %) a
127/182 (69,8 %)**, soit deux lignes de moins que le meilleur cas. `SCI-CM1-15` et `SCI-CM1-18` restent
`non couvert` (refusees). Aucune instance n'a depasse 5,0 : A 4,0, B 5,0.

| Poste | Objectif | Livre |
|---|---|---|
| `FRA-CE1-22` | `couvert-valide` | **atteint** (REFUSE au premier passage, VALIDE au second) |
| `FRA-CE1-20` | `couvert-valide` | **atteint** (VALIDE SOUS RESERVE) |
| `FRA-CE1-23` | `couvert-valide` | **atteint** (VALIDE SOUS RESERVE) |
| `HOM-CE1-R1` | reserve levee | **atteint** (VALIDE, reserve levee) |
| `SCI-CM1-10` | `couvert-valide` | **atteint** (VALIDE SOUS RESERVE) |
| `SCI-CM1-15` | `couvert-valide` | **non atteint** (REFUSE, aucun rejeu finance) |
| `SCI-CM1-18` | `couvert-valide` | **non atteint** (REFUSE deux fois) |
| Instance C (`MAT-CE1-17`, `-06`, `-02` moitie textuelle) | non engagee | **non engagee**, report a s52 |

**Comptage de la couverture, refait par moi** avec la commande ecrite en tete de
`squad/programme/MATRICE-couverture.md` (note du 2026-10-06), lancee sur la matrice a `047b91c4` puis sur la
matrice a `e4d8ba3b` (`git show e4d8ba3b:squad/programme/MATRICE-couverture.md | awk ...`) :

| Arbre | `couvert-valide` | `en cours` | `non couvert` | `non couvrable en l'etat` | Total |
|---|---|---|---|---|---|
| `e4d8ba3b` (planning) | 123 | 3 | 56 | 76 | 258 |
| `047b91c4` (cloture) | **127** | 3 | 52 | 76 | 258 |

Denominateur atteignable 182 (258 moins 76). 127/182 = 69,8 %, identique a `DEC-0110` section 16.7.

---

## 2. Done technique

**Juge : la CI, tous les jobs de `.github/workflows/ci.yml`** : `prefixes`, `chemins`, `outils`, `build`.

### 2.1 Ce que le rapport qualite de `yanis-qa` prouve

`rapport-qualite.md`, arbre `ca4c934a`, les quatre jobs lances par `yanis-qa` :

| Job | Resultat lu dans `rapport-qualite.md` |
|---|---|
| `prefixes` | **vert** : 2 scripts de test, puis `verifier-prefixes.sh` sur `e4d8ba3b..ca4c934a` (18 sujets, 0 fusion) |
| `chemins` | **vert** : script de test, puis `verifier-chemins.sh` sur tous les fichiers suivis |
| `outils` | **vert** : 9 juges purs executes (5 + 2 + 2), 0 echec |
| `build` | **vert** : `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, `BUILD SUCCESS`, 3 min 55 s, **2076 tests** (2070 unitaires et ArchUnit, 6 d'integration), 0 echec, 0 erreur ; JDK 21.0.10 rapporte par la machine virtuelle des tests |

Reserve ecrite par `yanis-qa` : le second pas du job `build` (`mvn -B -pl bootstrap -am package`) n'est pas
rejoue ; `verify` a produit le fat jar et `JarRecreoIT` l'a exerce. Son verdict est celui de sa mesure
locale, le juge reste la CI sur le push.

### 2.2 Ce que le rapport qualite ne prouve pas : les commits posterieurs a `ca4c934a`

Le rapport porte sur `ca4c934a`. Sept commits le suivent sur `round-two` (`ca4c934a..047b91c4`) : `d96f8612`
[THEO], `3ba5101b` [YAN], `db1fc6ed` [REL], `3cd6ee83` [THEO], `01eb85fe`, `46c30a89` et `047b91c4` [CAM].
`git diff --stat ca4c934a 047b91c4` : 9 fichiers, dont **un seul groupe de code** :

- **trois commentaires Javadoc** de tests (`PaquetCm1SciencesActiviteHumaineTest`,
  `PaquetCm1SciencesCorpsTest`, `PaquetCm1SciencesTerreTest`), `db1fc6ed` : le nom complet de l'arbitrage s30
  remplace `...` ; 6 lignes ajoutees, 3 retirees, aucune ligne de code ;
- **des documents** : `DEC-0110` (sections 15 a 16.9) et `DEC-index.md`, `MATRICE-couverture.md`,
  `backlog.md`, et les deux fichiers de `yanis-qa` et de `theo-archi` eux-memes ;
- **un chiffre** : `3cd6ee83` corrige dans `confirmation-archunit.md` le compte de `LesReglesMordentTest`
  (37 tests, pas 38).

Le rapport qualite ne couvre donc ni ces trois Javadoc ni ces documents. Ce que j'ai moi-meme relance pour
combler l'ecart, sur `047b91c4`, **avant d'ecrire les fichiers de cloture** :

| Job | Commande | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet | `BUILD SUCCESS`, 3 min 02 s, code 0 ; **2076 tests** : domain 120, application 17, adapter-web 401, adapter-content 1237, bootstrap 295 surefire + 6 failsafe ; 0 echec, 0 erreur, somme des attributs des rapports XML |
| `prefixes` | les deux scripts de test, puis `git log --no-merges --format=%s e4d8ba3b..HEAD \| bash .github/scripts/verifier-prefixes.sh` | vert, 25 sujets |
| `chemins` | script de test, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | vert |
| `outils` | boucle du workflow (trois motifs), interpreteur `python` | vert, 9 juges, 0 echec |

Perimetre et limites de cette relance : les trois jobs `prefixes`, `chemins` et `outils` ont ete lances sur
l'arbre **avant** l'ajout de mes fichiers de cloture ; ensuite, `printf` des cinq fichiers de ce dossier dans `.github/scripts/verifier-chemins.sh` : vert, et le message de commit dans `verifier-prefixes.sh` : vert. Le pas
`package` n'est pas rejoue non plus. Le juge reste la CI sur le push. **Je n'ai pas push : la CI n'a donc pas
encore rendu de verdict sur ces commits.**

**Total des tests, etabli sur l'arbre fusionne** (consigne de ma casquette : un chiffre annonce se reverifie) :
2076, identique a `yanis-qa`. Origine de l'ecart avec la base de `theo-archi` (2004 a `f68458de`) :
+2 de `ADR-0104` (`3ade55cb`, avant le planning : regle 13 de `DecouplageMecaniqueContenuTest`, morsure de
`LesReglesMordentTest`) et **+70** de s51, tous dans `adapter-content`, dont 68 viennent de six classes de test
neuves (11 + 12 + 10 + 12 + 13 + 10) et 2 de classes existantes modifiees. `yanis-qa` n'a pas decompose ces 2 au-dela
du diff ; moi non plus (2004 + 2 + 70 = 2076).

---

## 3. Confirmation ArchUnit - `theo-archi`

`confirmation-archunit.md`, arbre `ca4c934a`, mesure dans un worktree detache :

- **Commande** : `bash outils/mvn-jdk21.sh -B clean test -pl bootstrap -am '-Dtest=com/recreo/archi/**/*'
  -Dsurefire.failIfNoSpecifiedTests=false`. Perimetre : le seul paquetage `com.recreo.archi` ; ce n'est pas le
  reacteur complet et elle n'annonce pas la porte technique.
- **Resultat** : **216 tests, 0 echec, 0 erreur, 0 ignore.** +2 depuis s50 (214), tous deux d'`ADR-0104`
  (`3ade55cb`) ; aucun commit de s51 ne touche `bootstrap/src/test/java/com/recreo/archi`.
- **Regles ajoutees ce sprint** : **aucune.** **39** `static final ArchRule` et **36** `@ArchTest` (methode
  d'`ADR-0104`, contre-exemples compris), sortie par fichier identique entre `e4d8ba3b` et `ca4c934a`.
  Methode de s50, contre-exemples exclus : 37 / 36.
- **Clean Architecture** : aucun fichier de `domain`, `application`, `adapter-web`, aucun `pom.xml`, aucun
  gabarit, aucun script, aucune ligne de `src/main/java` touches entre `e4d8ba3b` et `ca4c934a` ; 22 fichiers,
  tous sous `adapter-content` sauf un test de `bootstrap`. Aucune ligne ajoutee ne contient `springframework`.
- **Garde-fous de poids et d'illustration** verts, cliquets inchanges (176 474 octets, 8 reproductions ;
  64 items, 42 fichiers).
- **`ADR-0104`** : statut `Propose`, inchange ; son passage a `Accepte` attend la reponse de `nadia-prof` sur
  `C5` clause 1.

Compte de tests ArchUnit corrige par `theo-archi` apres coup (`3cd6ee83`) : `LesReglesMordentTest` compte
37 tests, pas 38. Je n'ai pas relance `com.recreo.archi` seul ; le `clean verify` complet ci-dessus l'inclut et rend 0 echec.

---

## 4. Relecture - `relecteur-pedagogique`

La relecture de cloture est dans `db1fc6ed` (renvois et statut de `DEC-0110`, nom complet de l'arbitrage s30
dans trois Javadoc). Je n'ai pas de liste de corrections ecrite du relecteur dans le depot : je cite ce que le
commit change et ce que la consigne de cloture m'a transmis, sans plus.

- **Corrections appliquees** : celles de `db1fc6ed`, plus les elements de `planning.md` que son amendement de
  cloture met a jour (objectif, tarif de B, `SCI-CM1-15`, `S51-FRA-GRAPHIE`, numeros de ligne du backlog).
- **Defaut residuel transmis, a trancher par son auteur** : la ligne 89 de
  `squad/conformite/ARBITRAGE-PREALABLE-s51-fra-ce1-22-graphie-cedille.md` depasse 100 caracteres.
  **Route a `nadia-prof`** (fichier a elle). Mesure par moi : 162 caracteres ; vingt autres lignes du meme
  fichier font entre 101 et 117 caracteres, je ne sais pas si le relecteur les tient pour des defauts.

---

## 5. Tableau des obstacles du sprint

| # | Obstacle | Categorie | Route a | Decision liee | Statut a la cloture |
|---|---|---|---|---|---|
| 1 | **Collision de scratchpad constatee a s50** (forme (c)) : deux instances de `nadia-prof` ont ecrit `jouer.py` au meme endroit a 21:31:21 et 21:31:34, non consignee a l'epoque ; consignee par amendement de la retro de s50 a l'ouverture de s51 | Process / ressource de travail partagee | `sofia-sm` (consigne), `nadia-prof` (verification) | `DEC-0110` 14.4 (la regle de `squad/README.md` section 9) | **Applique et constate a s51, verification de `nadia-prof` non ecrite.** Lu par moi dans le repertoire temporaire de la session : un sous-repertoire `instance-a` et un `instance-b`, chacun avec son propre `jouer.py` ; aucun `jouer.py` a la racine. Deux reserves : le "scratchpad dedie" prend la forme d'un sous-repertoire du scratchpad de session et non d'un repertoire temporaire distinct comme le planning l'ecrivait ; je n'ai pas lu les transcriptions des deux instances. **Ouvert** : verification des formes (a), (b), (c) (R12) absente du depot (`DEC-0110` 16.10) |
| 2 | **Conflit de fusion sur `REGISTRE-identifiants.md`** a la fusion de l'instance B (`7716be0e`) : les deux instances ajoutent leurs identifiants au meme tableau | Technique / fusion | `sofia-sm` | `ADR-0085` (le garde-fou) | **Resolu par moi.** Mesure : le registre compte 918 lignes d'identifiant a `e4d8ba3b`, 920 cote A (+2), 921 cote B (+3), **923** apres fusion (918 + 2 + 3), aucun marqueur de conflit. `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` : 10 tests, 0 echec dans mon `clean verify`. Les "919/158" de la consigne de cloture ne sont pas un chiffre que j'ai reproduit : je cite le mien |
| 3 | **`FRA-CE1-22` infaisable en l'etat** : le distracteur sans cedille de `GRL-CE1-2` etait refuse par `AucunMotServiNeSEcarteDeSonAccentTest` | Technique puis pedagogie | `theo-archi` (A1), `nadia-prof` (recevabilite) | `ADR-0065`, amendement A1 du 2026-10-06 ; `ARBITRAGE-PREALABLE-s51-fra-ce1-22-graphie-cedille.md` | **Clos : les deux fichiers existent au disque.** Le blocage etait la condition suspensive du planning (R1, R2), levee avant le commit du paquet (`0ce82262` 13:52, `271058ec` 14:13) |
| 4 | **A1 commite deux fois** : `c2802a32` (sur `round-two`) et `02ba9e80` (sur `s51-nadia-instance-a`), meme date d'auteur 13:47:16 | Process / duplication de commit | `theo-archi` | `ADR-0065` | **Constate, sans consequence de contenu.** Meme `git patch-id --stable` (`6147864909158817...`), la fusion `84d5b509` ne produit aucun conflit. Ecart a signaler : `confirmation-archunit.md` place `02ba9e80` sur "instance B" ; `git branch --contains` le place sur l'instance A et aucune des deux n'est sur `s51-nadia-instance-b`. Route a `theo-archi` pour correction |
| 5 | **Ecart de +2 tests entre la base de 2004 et 2006** | Qualite / compte de tests | `yanis-qa` | - | **Resolu** : `3ade55cb` (`ADR-0104`, regle 13 et sa morsure), explique par `yanis-qa` section 3 de son rapport ; total refait par moi : 2076 |
| 6 | **Trailers `Co-Authored-By` "Claude Opus 5.5"** sur huit commits d'agents (`[THEO]` : `c2802a32`, `02ba9e80`, `d96f8612`, `3cd6ee83` ; `[NAD]` : `c886a0fd`, `438f0d1e`, `81908b7d`, `7efcb400`) au lieu de "Claude Sonnet 5.5" ; `0ce82262` (`[NAD]`) porte "Sonnet 5.5" | Redaction / attribution | `sofia-sm` constate ; `camille-po` ou `theo-archi` pour la regle | aucune | **Constate, non corrige.** Aucune regle ecrite dans `CLAUDE.md`, `squad/README.md` ni dans un script de la CI ne fixe la valeur du trailer (`grep -rn Co-Authored-By`). Le rapport de tokens de s51 (`RAPPORT-tokens.md`, colonne Modele) place `theo-archi` et `nadia-prof` sur `claude-opus-5-5` et les sept autres agents sur `claude-sonnet-5-5` : le trailer des commits `[THEO]` et `[NAD]` correspond au modele qui a tourne, sauf `0ce82262` (`[NAD]`, "Sonnet 5.5"), que je n'explique pas. Pas de reecriture d'historique |
| 7 | **`HOM-CE1-R1` livree par `lea-dev`** (`cc5686e0`, `[LEA]`) alors que le planning l'affectait a `marc-dev` | Capacite / affectation | `sofia-sm` | - | **Constate.** Aucun fichier du depot n'ecrit ce changement d'affectation ni son motif (`grep -rn -i derog` sur le dossier du sprint, la fiche et `DEC-0110` : rien de pertinent). Ce qui est verifie : le commit est `[LEA]`, la correction est jouee et levee par `nadia-prof`. Je n'invente pas le motif |
| 8 | **`SCI-CM1-15` refusee** au premier passage (items 4 et 11 : un distracteur est une reponse juste) ; **aucun rejeu finance** : la marge de B (0,5) a servi au rejeu de `SCI-CM1-18` | Pedagogie / budget | `nadia-prof` (prix), `camille-po` (perimetre) | `DEC-0110` 15.3 et 16.5 | **Ouvert.** Story `S51-SCI-TERRE-CM1-REPRISE`, non engagee a s51, reportee a s52, 1,0 prevu (sept items sur douze, `PAR-14`), prix a fixer par `nadia-prof` |
| 9 | **`SCI-CM1-18` refusee deux fois**, motif de l'item 11 maintenu (*"la correction deplace le defaut sans le supprimer"*) ; le troisieme passage depasserait le plafond de B (5,0) | Pedagogie / budget | `nadia-prof`, `camille-po` | `DEC-0110` 16.4 | **Ouvert.** Story `S51-SCI-ACTIVITE-HUMAINE-CM1-REPRISE`, 0,5 prevu, reportee a s52. Question de `camille-po` a `nadia-prof`, sans reponse : retirer l'item 11 leve-t-il le motif ? |
| 10 | **Deux paquets refuses restent dans le catalogue servi** (`bandit-manchot-activite-humaine-cm1`, `bandit-manchot-terre-cm1`) | Produit / contenu servi a l'enfant | utilisatrice (decision), `camille-po` (ecriture) | `DEC-0110` 16.9 | **Decide par l'utilisatrice, risque accepte, exigence de s52.** Voir section 8 |
| 11 | **Instance C non engagee** : `nadia-prof` a refuse la borne a trois instances (arbitrage prealable s51, condition (ii) non remplie : forme (c) a s50) ; `MAT-CE1-17`, `MAT-CE1-06`, `MAT-CE1-02` moitie textuelle reportees | Produit / capacite | `camille-po`, `nadia-prof` | `DEC-0110` 14 | **Ouvert, report a s52.** `MAT-CE1-17` a neuf sprints de report (s43 a s51). La derivation a trois instances pour s52 demande un document de `nadia-prof` qui n'existe pas au depot (`DEC-0110` 16.10) |
| 12 | **Reserves non engagees** : `TPS-CE1-R1`, `ALP-CE1-R1` (A, 0,5 chacune), `CRP-CM1-R1` a `R3` (B, une story a 0,5) | Pedagogie / correction differee | `camille-po` | `DEC-0110` 16.3 et 16.6 | **Ouvert.** Stories ecrites au backlog, premier report, a s52. A tient dans 5,0 (4,0 + 0,5 + 0,5) mais `camille-po` ne les engage pas (`PAR-16`, mesure de cloture a refaire) |
| 13 | **Defaut residuel du relecteur** : ligne 89 de l'arbitrage `ARBITRAGE-PREALABLE-s51-fra-ce1-22-graphie-cedille.md` a 162 caracteres | Redaction | `nadia-prof` | - | **Route**, non corrige par moi (fichier de `nadia-prof`) |
| 14 | **Ecart "palier 2" de la matrice** (R10), **`PAR-4` a deux instances**, **ecart `PAR-9` / pratique** (R11) | Produit | `camille-po` | `DEC-0110` 14.7, 16.10 | **Ouverts, poses a s52** ; aucun n'a ete traite a s51 |
| 15 | **`ADR-0104` reste `Propose`** : `C5` clause 1 attend la reponse de `nadia-prof` ; `ART-CP-10` hors capacite de s51 (R5, R6) | Architecture / pedagogie | `nadia-prof` | `ADR-0104` | **Ouvert**, aucun budget avant sa reponse |
| 16 | **`outils/rapport-tokens.py` a efface des mesures anterieures au lancement de s51** : le journal cumulatif est regenere depuis les transcriptions encore presentes ; a s51 la regeneration remplace par "Aucune consommation mesuree" les sections de s23 a s27 (et la plupart de s22), qui portaient des chiffres dans `squad/rapports/JOURNAL-tokens.md` a `047b91c4` | Technique / outil de gouvernance | `theo-archi` | - | **Ouvert.** Je n'ai **pas commite** le journal regenere : il detruirait ces chiffres dans l'arbre de travail, et son en-tete dit "jamais edite a la main", donc je ne recolle pas non plus les anciennes sections. `RAPPORT-tokens.md` de s51 est commite ; la section de s51 manque au journal. A decider par l'utilisatrice et `theo-archi` |

Rappel : un obstacle n'est clos que si le fichier de decision existe physiquement. Seul l'obstacle 3 l'est ;
2, 4 et 5 sont resolus ou constates sans decision a ecrire.

---

## 6. Done pedagogique - `nadia-prof`

Prononce **uniquement** par `nadia-prof`, apres avoir joue chaque paquet. Je rapporte les verdicts, lus dans
chaque fiche, je n'en rends aucun. Instance A : worktree `s51-nadia-instance-a` ; instance B :
worktree `s51-nadia-instance-b`.

| Ligne | Fiche | Verdict final | Episode |
|---|---|---|---|
| `FRA-CE1-22` | `FICHE-bandit-manchot-graphie-ce1.md` | **VALIDE**, sans reserve | Premier passage **REFUSE** (`c886a0fd`) : deux items sur 24 (13 `pomme` et 16 `flamme`) qui ne travaillent pas la regle du `m` : les deux propositions ne se lisent pas pareil. Rejeu `df608669` (`lea-dev`), 0,5. Second passage `VALIDE` (`7efcb400`), 600 manches jouees, aucune anomalie |
| `FRA-CE1-20` | `FICHE-bandit-manchot-temps-ce1.md` | **VALIDE SOUS RESERVE** | Reserve `TPS-CE1-R1` : trois activites servies du cote du stereotype. Pas de mention de couverture |
| `FRA-CE1-23` | `FICHE-bandit-manchot-ordre-alphabetique-ce1.md` | **VALIDE SOUS RESERVE** | Reserve `ALP-CE1-R1` : trois indices montrent la premiere lettre la ou seule la deuxieme decide. Pas de mention de couverture |
| `HOM-CE1-R1` (reserve sur `FRA-CE1-16`) | `FICHE-bandit-manchot-homophones-ce1.md` | **VALIDE**, reserve levee | Quatre prenoms sur sept remplaces a genre egal (`cc5686e0`) ; la ligne reste `couvert-valide`, aucun statut ne bouge |
| `SCI-CM1-10` | `FICHE-bandit-manchot-corps-cm1.md` | **VALIDE SOUS RESERVE** | Reserves `CRP-CM1-R1` a `R3` (deux indices faux, deux formulations trop larges, distracteurs implausibles) ; aucune n'est un motif de refus. Sans mention de couverture |
| `SCI-CM1-15` | `FICHE-bandit-manchot-terre-cm1.md` | **REFUSE** | Items 4 et 11 : un distracteur est une reponse juste a la question posee. `nadia-prof` le lit comme une cle de reponse fausse. Quatre points non bloquants. Aucun rejeu a s51 |
| `SCI-CM1-18` | `FICHE-bandit-manchot-activite-humaine-cm1.md` | **REFUSE** deux fois | Premier passage (`438f0d1e`), second passage apres rejeu `5a4ca6d8` (`81908b7d`) : item 11 maintenu, deux propositions repondent a la question ecrite. Les deux points non bloquants du premier passage sont pris. `SCI-2` tenue sur les douze items |

Quatre fermetures, un rejeu de `FRA-CE1-22` reussi, un rejeu de `SCI-CM1-18` refuse, un refus sans rejeu
(`SCI-CM1-15`). **Zero refus final sur la partie francais ; deux refus finaux sur la partie sciences.**

---

## 7. La couverture du programme

**123/182 (67,6 %) a 127/182 (69,8 %)**, mesuree par la commande de la matrice (section 1). Quatre lignes
fermees, aucune mention de couverture partielle due (`DEC-0110` section 10). `MAT-CE1-02` ne passe pas a
`en cours` (l'instance C n'est pas engagee).

---

## 8. Decision de l'utilisatrice : les deux paquets refuses restent servis jusqu'a s52

**Forme 3** (`squad/README.md` section 2 bis) : enonce recu de MASTER, sans source dans le depot, le
2026-10-06. Je le cite mot pour mot ; **il ne fonde rien d'autre que cette decision**, pas un jugement sur la
gravite des defauts, qui reste celui de `nadia-prof`.

- **Question** : *"Retirer du catalogue servi les deux paquets refuses par nadia-prof (activite-humaine-cm1 et
  terre-cm1) ?"*
- **Reponse** : *"Garder jusqu'a s52"*.

**Consequence** : `bandit-manchot-activite-humaine-cm1` et `bandit-manchot-terre-cm1` restent dans
`catalogue.yaml` jusqu'a s52. Aucun code, catalogue ni test ne change ; `camille-po` a ecrit la decision et
son exigence dans `DEC-0110` section 16.9 et en section 16.10 (fichier existant, commit `047b91c4`) : la
`DEC` d'ouverture de s52 tranche, avant tout autre poste touchant ces deux paquets, entre le retrait et la
reprise, et la decision de l'utilisatrice n'est pas reconduite par defaut.

**Le risque pour l'enfant, deja ecrit par `nadia-prof` dans ses fiches, que cette decision accepte.**
Jusqu'a s52, un enfant qui lance l'application peut jouer ces deux paquets dans l'etat refuse, et recevoir
*"Ce n'est pas ça."* sur une reponse vraie ou acceptable :

- `FICHE-bandit-manchot-terre-cm1.md`, motif 1 (item 4), distracteur *"elle est plus proche du Soleil que le
  reste de la Terre"* : *"L'enfant qui la choisit recoit "Ce n'est pas ça." : il apprend comme faux un fait
  vrai."* Motif 2 (item 11), *"le printemps"* a d'aussi longs jours que *"l'été"* : meme effet.
- `FICHE-bandit-manchot-activite-humaine-cm1.md`, second passage, item 11 : *"Lu a l'ecran apres "plus de gaz
  naturel" : "Ce n'est pas ça.""* ; l'enfant qui raisonne juste sur la question ecrite recoit ce message.

Ce risque porte sur trois items nommes (items 4 et 11 de `terre-cm1`, item 11 de `activite-humaine-cm1`) et sur
aucun autre. **Ce n'est pas moi qui l'ai arbitre** : l'utilisatrice l'a fait, et `camille-po` l'a ecrit. Je
ne tire de cette decision aucune story : `DEC-0110` 16.9 precise que `sofia-sm` n'a rien a executer.

---

## 9. Ce que cette review ne fait pas

- Elle ne prononce aucun Done pedagogique : les verdicts sont ceux des fiches.
- Elle n'annonce pas la porte technique **verte pour la CI** : `yanis-qa` et moi avons lance les quatre jobs
  en local, la CI n'a pas rendu de verdict, rien n'est pousse.
- Elle ne change aucun statut de la matrice et n'edite ni `DEC-0110`, ni le backlog, ni les fiches, ni le
  registre, ni `squad/README.md`.
- Elle n'ouvre pas s52 et ne propose pas son perimetre : c'est le role de `camille-po` (`DEC-0110` 16.10).

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee par cette review.** Decisions du sprint, rapportees et non
prises ici (liste dans `RAPPORT.md`) : `DEC-0110` (sections 14, 15, 16 dont 16.9) et l'amendement A1 d'`ADR-0065`.
