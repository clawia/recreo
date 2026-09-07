# Review de s30 - sciences CM1 et le prix des reserves

- **Horodatage de fin** : 2026-09-07 10:01
- **Duree effective** : depuis l'ouverture (2026-09-06 17:45), environ 16h16.
- **Increment mesure** : `round-two` a `cff5949e`.

---

## Porte technique - confirmee independamment, trois fois

**Rapport qualite de `yanis-qa`** (`rapport-qualite.md`, mesure sur `dbbfe129`, avant les quatre
derniers commits de cloture) : `bash outils/mvn-jdk21.sh -B clean verify`, worktree dedie, reacteur
complet, **BUILD SUCCESS**, **179 rapports, 1321 tests, 0 echec, 0 erreur**. Deux garde-fous mordus
reellement, pas relayes : `AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest` (mutation du
gabarit, 58 griefs sur 58 ecrans - le catalogue est passe de 54 a 58 paquets depuis l'ecriture du
garde-fou, l'ecart est explique et non subi) et
`ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest` (suppression d'une declaration,
2 echecs). Aucune declaration de commentaire fausse trouvee sur les quatre paquets de sciences,
apres verification poste par poste.

**Confirmation ArchUnit de `theo-archi`** (`confirmation-archunit.md`, meme commit `dbbfe129`,
worktree dedie) : **33 regles `ArchRule`, zero ajoutee ce sprint**, confirme par trois methodes
convergentes (declarations de champ, occurrences defalquees, annotations plus la regle executee par
`.check()` explicite) - le piege de compter les seuls `@ArchTest` (32, pas 33) est nomme et evite.
**Zero fichier Java de production touche par les deux lots de contenu** (24 fichiers : 12 YAML, 12
tests), verifie en ecrivant la liste dans un fichier plutot qu'en la comptant au fil d'un tube -
`theo-archi` a lui-meme trouve un premier comptage fausse par la decoration de l'enveloppe de shell
de son poste (1 au lieu de 0) et l'a corrige avant de le publier. La dette de referentiels sans code
est corrigee a **dix/huit** (etat sur `dbbfe129`, apres l'allocation des vingt codes de sciences),
contre "sept"/"onze" initialement ecrit puis "neuf"/"neuf" a l'etat pre-sprint - trois versions du
meme chiffre, chacune datee et methodee.

**Ma propre execution sur l'arbre de cloture** (`cff5949e`, apres les quatre derniers commits :
deux reaudits de `nadia-prof`, la troisieme reecriture de la consigne d'histoire-geographie par
`lea-dev`, la cloture de `camille-po`) : `bash outils/mvn-jdk21.sh -B clean verify`, reacteur
complet, sans `-pl`, depuis mon propre worktree dedie. **BUILD SUCCESS.**

**Compte de tests, methode de l'amendement s11** : somme de l'attribut `tests="..."` de chaque
rapport XML apres `clean`, reacteur complet : **179 fichiers XML, 1321 tests, 0 echec, 0 erreur.**
Identique au chiffre de `yanis-qa` et de `theo-archi` sur `dbbfe129` : aucun des quatre commits de
cloture ne touche de fichier Java de test (trois portent des fiches et decisions en Markdown, un
modifie une seule ligne de `catalogue.yaml` sans ajouter de methode de test). Le total du sprint,
de l'entree (1289, mesure au planning) a la cloture, est **+32** : quatre tests de paquet de
sciences neufs et huit fichiers de reprise etendus, confirme poste par poste sur les deux lots de
dev et non recompose a rebours.

**Verdict : porte technique verte**, confirmee independamment par trois mesures distinctes sur trois
etats successifs de l'arbre (`dbbfe129` par deux agents, `cff5949e` par moi), toutes convergentes.

## Corrections du `relecteur-pedagogique`

`relecture.md` (worktree dedie, lecture croisee de deux fichiers non encore fusionnes a la date de
la relecture) : **rien a corriger** - aucune faute d'orthographe, d'accentuation ou de ponctuation,
aucun chemin absolu de poste de travail dans `round-two`, **aucune reference cassee par le
renommage du repertoire de sprint**, verifie exhaustivement (toute occurrence de `sprints/s30`
pointe vers le chemin complet avec slug). **Cinq points signales, aucun corrige de sa propre
initiative** (une figure d'arbitrage ou une mesure d'un autre agent n'est pas son mandat) :

1. `avis-faisabilite-theo.md` portait encore "sept"/"onze" dans `round-two` au moment de la
   relecture, le correctif etant deja pret sur une branche non fusionnee - ferme par cette fusion
   (l'onze devient huit, section ci-dessus).
2. Le referentiel de sciences de `nadia-prof` disait "des six" quand la matrice dit "des dix" - son
   propre chiffre, dans son propre document ; corrige depuis (verifie dans l'arbre de cloture).
3. Un chiffre perime de s29 (les cinq dessins pour six enumeres), deja route par `theo-archi` -
   hors-perimetre de s30, non traite ici.
4. Un chemin absolu de poste de travail dans `rapport-qualite.md` (worktree non fusionne) - corrige
   par `yanis-qa` lui-meme avant fusion (commit `1d7326a3`).
5. Une attribution qui ne resolvait a aucun document (`rapport-qualite.md` citait sofia-sm pour un
   chiffre que je n'ai jamais ecrit dans le depot) - corrigee par `yanis-qa` dans le meme commit,
   reattribuee a `confirmation-archunit.md` de `theo-archi`.

---

## Tableau des obstacles du sprint

| # | Obstacle | Categorie | Route vers | Resolution |
|---|---|---|---|---|
| 1 | `S30-7` (bloc E, `FOP-R1`) exigeait d'ecrire `catalogue.yaml`, reserve a `lea-dev` seule par ma consigne d'affectation - `marc-dev` n'y a pas touche et a remonte l'obstacle | Capacite/charge, ma propre consigne | `sofia-sm` (moi-meme) | Ferme : `FOP-R1` reassignee a `lea-dev`, ajoutee en dernier de sa serie sur `catalogue.yaml` (commit `cc0ef10f`) |
| 2 | Fichier de test ecrase par `Write` sans relecture prealable (`marc-dev`, `PaquetCm1EmcFaitOpinionTest.java`) - six tests d'origine perdus, une regression d'encodage sur un caractere insecable | Qualite/process, meme famille que les trois incidents de s29 | `sofia-sm` | Ferme le jour meme par `marc-dev` (original restaure, ses deux tests ajoutes par-dessus, verifie par `git diff --stat` et rebuild vert) ; route en retro comme recidive, pas comme fait isole |
| 3 | Deux paquets de sciences refuses au premier audit de l'Instance B (`SCI-CM1-17`, `SCI-CM1-12`) | Pedagogie | `lea-dev` (correction) | Ferme : corriges et reaudites dans le sprint (`DEC-0080`), tous deux `couvert-valide` au second passage |
| 4 | `CONS-1` non levee au premier reaudit de l'Instance A - cinq entrees sur dix ne nommaient pas la tache reelle de leur jeu | Pedagogie | `lea-dev` (correction) | Ferme en partie : cinq consignes rejugees et corrigees (`7320ebdb`), neuf entrees sur dix tiennent au reaudit ; la dixieme (histoire-geographie-cm1) reste ouverte, voir obstacle 12 |
| 5 | `OBT-R2` - un defaut fabrique par la correction d'`OBJ-R2` sur `objet-technique-cm1` (deux indices neufs eliminent les deux distracteurs a la fois, revelant la reponse) | Pedagogie, troisieme cas de cette famille en deux jours apres `REV-2` et `REV-3` | `nadia-prof` (constat), `theo-archi`/`yanis-qa` (garde-fou eventuel) | Ouvert, non bloquant, reserve versee au backlog de s31 (rejeu borne, 0,5) |
| 6 | La reprise des deux paquets refuses n'a ajoute aucun garde-fou sur les proprietes que les refus ont fait payer (`OBJ-R2`, `OBT-R1`, `OBT-R2`) - elles restent gardees par `nadia-prof` seule | Technique/qualite | `theo-archi`, `yanis-qa` | Ouvert, signale explicitement par `nadia-prof` pour qu'on ne les croie pas couvertes |
| 7 | `PAR-9` calculait la marge de rejeu par moitie egale du sprint alors que `PAR-2` concentre les deux lots de sciences (et leurs reaudits possibles) sur une seule instance | Gouvernance/pedagogie, defaut trouve par `nadia-prof` contre sa propre clause | `nadia-prof` (elle-meme) | Ferme : `PAR-11`, la marge se dimensionne desormais par instance sur son exposition reelle (0,5 par lot neuf audite), enregistree dans `DEC-0079` et `DEC-0080` |
| 8 | Registre `PAR-5` des identifiants de conditions toujours pas ecrit - troisieme sprint consecutif, l'Instance B y ajoute neuf identifiants de plus | Gouvernance/technique | `theo-archi` | Ouvert, troisieme routage ; c'est l'une des deux conditions qui bloquent la levee de la retenue de 1,0 sur la borne |
| 9 | Deux instances de `nadia-prof` partageaient le meme scratchpad d'agent ; l'une a ecrase le fichier de l'autre | Orchestration, defaut de ma propre organisation du sprint, pas d'un agent | `sofia-sm` (moi-meme) | Ferme sans consequence de contenu : l'Instance A a detecte la collision et verifie qu'aucun contenu n'avait contamine ses propres fichiers ; route en retro comme mon defaut |
| 10 | Quatre agents ont trouve une erreur dans leur propre mesure avant de la publier (voir retro) | Qualite/methode | chacun, lui-meme | Ferme dans les quatre cas, avant publication - aucun chiffre faux n'est entre dans le depot |
| 11 | Population de `DEC-0078` sous-comptee d'une entree (`bandit-manchot-semaine-cp` porte le meme defaut sous une autre chaine que celle recherchee) | Produit/qualite, trouve par `nadia-prof` en recomptant pendant le reaudit | `camille-po` | Ferme : `DEC-0078` amendee, population corrigee a 43/54, onzieme entree routee au backlog de s31 sous `CONS-3`, prix non chiffre |
| 12 | `CONS-1` reste ouverte sur une seule entree (`bandit-manchot-histoire-geographie-cm1`, 57 items, la plus lourde des dix) apres deux corrections insuffisantes le meme jour par la meme developpeuse - couture rompue une seconde fois | Pedagogie/process, meme famille que l'obstacle du planning (`VIV-R2`/`CONS-1` du bloc A) | `lea-dev` (troisieme reecriture), `sofia-sm` (constat de process) | Consigne corrigee et servie (`d90eb269`) mais non rejouee dans le sprint, par arbitrage de l'utilisatrice - reste ouvert, rejeu borne de 0,5 a s31 |
| 13 | Troisieme depassement consecutif de la borne du sprint (s28 +0,25, s29 +1,50, s30 +0,50) | Gouvernance | `camille-po`/utilisatrice | Reconnu et arbitre avant d'etre consomme (`DEC-0080`) - la tendance baisse d'un ordre de grandeur mais le fait se repete, porte en retro |

---

## Avis de `nadia-prof` et Done pedagogique

**Deux instances disjointes, chacune reauditant apres son propre refus, aucune ne relisant l'autre**
(Instance A : `dbbfe129` puis reaudit sur `a91070f8`, commit `5c39cfd4` ; Instance B : `dbbfe129`
puis reaudit sur `a91070f8`, commit `14bbe51c`) :

| Ligne | Jeu | Instance | Mouvement |
|---|---|---|---|
| `SCI-CM1-05` | `bandit-manchot-signal-information-cm1` | B | `non couvert` -> `couvert-valide` |
| `SCI-CM1-17` | `bandit-manchot-chaine-alimentaire-cm1` | B, reaudit apres refus | `non couvert` -> `couvert-valide` |
| `SCI-CM1-02` | `bandit-manchot-melanges-cm1` | B | `non couvert` -> `couvert-valide` |
| `SCI-CM1-12` | `bandit-manchot-objet-technique-cm1` | B, reaudit apres refus | `non couvert` -> `couvert-valide` |

**Quatre lignes ferment**, exactement le plafond arithmetique pose par `DEC-0079` avant que le
sprint soit joue (52 + 4 = 56/138 = 40,6 %). Sciences et technologie CM1 n'avait jamais recu un
seul item avant ce sprint.

**Onze reserves de s29 levees sur les onze de son perimetre par l'Instance A**, cinq reserves
neuves ouvertes (aucune bloquante). **`REV-3` validee.** **`CONS-1` n'est pas levee** : neuf entrees
sur dix tiennent au reaudit, la dixieme (histoire-geographie-cm1) reste ouverte et part a s31 en
rejeu borne (0,5), sa consigne corrigee et servie mais non rejouee dans ce sprint par arbitrage de
l'utilisatrice.

**Couts** : Instance A **4,00/4,00** (marge de 0,5 entierement consommee sur le reaudit de
`CONS-1` - premiere fois qu'une marge de rejeu sert a un rejeu reel dans ce depot). Instance B
**4,50/4,00**, depassement de 0,50 sous son plafond individuel dur de 5,0 (`PAR-8`), arbitre et
chiffre avant d'etre consomme (`DEC-0080`). **Sprint : 8,50/8,0**, troisieme depassement consecutif
de la borne.

**Neuf reserves de l'Instance B et sept entrees de l'Instance A (dont le reliquat de `CONS-1`)
partent au backlog de s31** : dix-sept au total, aucune perdue, aucune bloquante.

**Ce que la reprise n'a pas fait, et `nadia-prof` le dit pour qu'on ne le croie pas couvert** :
aucun garde-fou nouveau sur `OBJ-R2`, `OBT-R1`, `OBT-R2` - ces proprietes restent sous sa seule
garde, comme avant le sprint.

### Done pedagogique

**Prononce.** Quatre lignes ferment (`non couvert` -> `couvert-valide`), les deux paquets refuses
au premier passage corriges et valides au reaudit dans le sprint, avec leurs motifs de refus
lisibles entiers dans les fiches de conformite. `REV-3` validee. `CONS-1` partiellement levee (neuf
entrees sur dix), son reliquat route a s31 avec un prix deja chiffre.

### Done technique

**Prononce.** `mvn -B clean verify` vert sur le reacteur complet, `cff5949e`, **179 rapports, 1321
tests, 0 echec, 0 erreur** - confirme independamment par `yanis-qa` et `theo-archi` (sur `dbbfe129`,
avant les quatre derniers commits de cloture, memes chiffres) et par ma propre execution sur l'arbre
final. Zero `ArchRule` ajoutee ; zero fichier Java de production touche par les deux lots de
contenu.
