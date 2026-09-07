# s31 - confirmation de la suite, `theo-archi`

**Un "je pense que c'est bon" ne vaut rien.** Ce fichier porte le resultat des commandes, leur
perimetre et leur date. Il remplace le fichier du meme nom ecrit a l'ouverture, dont les chiffres
portaient sur mon seul lot.

## 0. Ou et sur quoi

| | |
|---|---|
| Worktree | `s31-theo-verif`, branche `s31-theo-verif`, **cree pour cette verification** sur `3181b5e6` |
| Lignee | verifiee : `2ef583ba` est un ancetre de `3181b5e6`, arbre propre, onze commits de s31 presents |
| Commande | `bash outils/mvn-jdk21.sh -B clean verify` sur la racine |
| JDK | **21.0.10**, version rapportee par la machine virtuelle qui execute les tests, pas par l'entree de toolchain qui la designe (`ADR-0071`) |
| Date | 2026-09-07 |

**Aucune mesure de ce document n'est partielle** : le reacteur est complet par construction sur la
racine, donc la clause du `-am` de `CLAUDE.md` ne s'applique a aucune ligne ci-dessous. Les
worktrees des autres agents - dont celui de `yanis-qa`, qui travaille en parallele - sont des
repertoires **freres** du mien et ne sont jamais balayes.

**Aucun chiffre de ce document n'est obtenu au fil d'un tube.** C'est le correctif explicite du
defaut de s30, ou la decoration ajoutee par l'enveloppe de shell de ce poste avait ete comptee comme
une ligne de resultat. Tout comptage est fait dans un script qui lit les octets ou qui appelle `git`
en sous-processus, et qui imprime le nombre qu'il a calcule lui-meme.

## 1. Porte technique - **verte**

**`BUILD SUCCESS`**, **1385 tests, 0 echec, 0 erreur.**

| Module | Moteur | Ouverture `2ef583ba` | Assemblage `3181b5e6` |
|---|---|---|---|
| `recreo-domain` | surefire | 99 | 99 |
| `recreo-application` | surefire | 13 | 13 |
| `recreo-adapter-web` | surefire | 388 | 388 |
| `recreo-adapter-content` | surefire | 617 | **670** |
| `recreo-bootstrap` | surefire | 198 | **209** |
| `recreo-bootstrap` | failsafe | 6 | 6 |
| **Total** | | **1321** | **1385** |

*Methode du total : somme des six blocs `Results:` du journal, deux moteurs comptes separement - un
rapport texte n'en garde qu'un. Le chiffre d'ouverture a ete remesure par la meme commande a la
verification de mon lot, il n'est pas repris d'une annonce.*

**+64 tests, et le delta se ferme terme a terme** : **+11 dans `bootstrap`** (mes deux garde-fous,
4 + 7) et **+53 dans `adapter-content`** (les tests de paquet des quatre lots). Aucun autre module
ne bouge.

## 2. Compte des regles ArchUnit - **33, et le delta du sprint est zero**

**On m'annonce que j'en ai ajoute avec `ADR-0085` et `ADR-0086`. Je ne peux pas le confirmer :
c'est faux, et mes deux `ADR` l'ecrivaient d'avance.** Chacun porte, dans sa section *Regle
executable*, la phrase *"ce n'est pas une `ArchRule`, et le compte de regles ArchUnit du depot est
inchange, a 33"*, avec son motif : ArchUnit lit du bytecode, et ni un identifiant ecrit dans un
texte de gouvernance, ni la fabrication des propositions d'un paquet YAML n'y sont des proprietes
lisibles. Ce sont des **balayages de sources**, sur les precedents de
`ToutesLesReferencesDeDecisionResolventTest` et de
`ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest`.

**Le fait mesure passe avant l'annonce, y compris quand l'annonce m'est favorable.**

### Les trois voies, toutes les trois

*Perimetre : les fichiers `.java` du seul worktree `s31-theo-verif`. Exclusions declarees : tout
repertoire `target/`, et tout chemin ou nom de fichier portant le segment `contreexemple`.*

1. **Declarations de champ** - motif `ArchRule <nom>` en tete de declaration, perimetre et
   exclusions ci-dessus : **33**, reparties sur **12 fichiers** (11 dans
   `bootstrap/src/test/java/com/recreo/archi/`, 1 dans `AssertionsWebTest`).
2. **Toutes les occurrences, defalquees** : **35** lignes portent la sous-chaine `ArchRule ` hors
   contre-exemple, **3** de plus dans les arbres de contre-exemple. Moins **2** qui ne sont pas des
   declarations, nommees plutot que supposees - la constante `DECLARATION_DE_REGLE` et un texte de
   message, toutes deux dans `LesGardeFousSontMisALEpreuveTest`. 35 - 2 = **33**.
3. **Par les annotations, avec sa correction connue** : **32** `@ArchTest`, **plus 1** regle
   executee par un `.check()` explicite et donc non annotee, dans `ScanDeComposantsTest`.
   32 + 1 = **33**.

**Les trois convergent. Compter les `@ArchTest` seuls aurait annonce 32**, et le piege est verifie
sur cet arbre, pas suppose de memoire.

### Le delta, revision par revision

| Revision | Ce qu'elle est | `ArchRule` | `@ArchTest` |
|---|---|---|---|
| `2ef583ba` | cloture de s30, ouverture de s31 | 33 | 32 |
| `84198564` | mon lot (`ADR-0085`, `ADR-0086`) | 33 | 32 |
| `9808b2f9` | planning de s31, avant les lots | 33 | 32 |
| `3492cc0b` | apres les deux lots d'EMC CP | 33 | 32 |
| `3181b5e6` | arbre assemble de s31 | 33 | 32 |

*Methode : meme motif que la voie 1, applique aux fichiers `.java` de chaque arbre lus par
`git ls-tree` puis `git show <rev>:<fichier>` - jamais sur l'arbre de travail -, memes exclusions.*

**Delta du sprint : zero regle ArchUnit, par personne.** C'est conforme et non manquant. Les deux
`ADR` de s31 nomment l'autre mecanisme automatique qui les garde, et il tourne dans le `verify`
ci-dessus : `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` **4 tests, 0 echec**, et
`LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` **7 tests, 0 echec**. Les deux `ADR` sont
donc complets au sens de `CLAUDE.md`. **Un `ADR` qui aurait promis une regle ArchUnit et laisse ce
tableau a 33 serait, lui, incomplet - et c'est ce tableau qui permet de le voir.**

## 3. L'increment de contenu a-t-il coute du Java de production ? **Non - zero fichier**

*Methode, dans le meme paragraphe : `git diff --numstat -M 2ef583ba 3181b5e6` appele en
sous-processus depuis un script, sa sortie decoupee ligne par ligne dans le script, chaque chemin
classe par une fonction de famille ecrite dans ce script. **Aucun tube, aucun `wc`, aucun `grep -c`
sur une sortie decoree** - c'est precisement le defaut de s30 que cette methode ferme.*

**53 fichiers changes.**

| Famille | Fichiers | Lignes + | Lignes - |
|---|---|---|---|
| Document (`squad/`, `docs/`, `.md`) | 22 | 3 514 | 83 |
| Java sous `src/test/java` | 14 | 2 318 | 2 |
| Paquet de contenu YAML | 10 | 806 | 7 |
| Ressource de test | 6 | 173 | 0 |
| Catalogue YAML | 1 | 48 | 0 |
| **Java sous `src/main/java`** | **0** | **0** | **0** |
| Gabarit Thymeleaf, CSS, image, script, POM | **0** | **0** | **0** |

**Zero fichier de Java de production, et zero gabarit.** L'increment de contenu de s31 n'a coute
aucune ligne de code servi.

**Les trois chiffres qu'on me transmet sont confirmes par ma mesure, pas repris** : les 10 paquets
touches se decomposent en **8 ajouts** (`git diff --name-status`, statut `A`) et **2
modifications** (`cm1-sciences-melanges` et `cm1-sciences-objet-technique`, les reprises `S31-5` et
`S31-6`), et le catalogue passe de **58 a 66 entrees** - compte fait en chargeant le YAML aux deux
revisions, pas en comptant des lignes. **66 entrees pour 66 fichiers de contenu servis**, sans
orphelin d'aucun cote.

## 4. La couture des deux fichiers transverses - **coherente sur quatre axes**

Objet : `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (registre d'exemption
d'`ADR-0051`, clef = identifiant de **paquet**) et `TexteAccentueExposeParLePortEntrantTest`
(inventaire du texte expose, clef = identifiant d'**entree de catalogue**). `sofia-sm` a verifie
l'absence de recouvrement de fichier ; ce qui suit est autre chose.

**Le risque propre a cette paire, et il est structurel** : les deux surfaces ne partagent **aucune
clef**. L'une nomme des paquets, l'autre des entrees de catalogue, et le lien entre les deux vit
dans `catalogue.yaml`. Chaque test valide **son** cote. **Une inscription juste d'un cote et
decalee de l'autre laisserait les deux tests verts.** C'est exactement ce qu'une verification de
recouvrement de fichiers ne peut pas voir.

| Axe verifie | Resultat |
|---|---|
| Les 8 paquets neufs sont inscrits **des deux cotes**, par leur clef propre, le lien passant par le `paquet:` du catalogue | **8 sur 8** |
| Toute clef du registre d'exemption designe un paquet reellement servi | **51 clefs, aucune orpheline** |
| Tout identifiant de l'inventaire est une entree du catalogue | **66 pour 66, aucun hors catalogue** |
| Les deux inscriptions citent **les memes codes de ligne**, et ce sont ceux que les paquets declarent | **8 sur 8** (`EMC-CP-01`, `-03`, `-05`, `-07` ; `SCI-CM1-01`, `-07`, `-08`, `-11`) |

**Et les motifs ecrits sont vrais du contenu servi.** Le registre d'exemption d'`ADR-0051` ne vaut
que par ses motifs : chacun affirme un compte. Je les ai confrontes au contenu charge.

| Verification des huit motifs neufs | Resultat |
|---|---|
| Nombre d'items annonce contre nombre reel | **8 sur 8 exacts** (11 items chacun) |
| Nombre de propositions annonce contre nombre reel | **8 sur 8 exacts** - dont `cp-emc-regle-souhait-loi`, seul a annoncer **30** et non 33, parce qu'il porte trois items binaires : 3 x 2 + 8 x 3 = 30 |
| Propositions numeriques dans les huit paquets exemptes | **0** - une seule aurait rendu l'exemption fausse |

**Deux alertes de ma propre mesure, verifiees et levees**, parce qu'un defaut annonce sans etre
verifie est pire que pas de mesure :

- Mon premier controle a signale **3 consignes du catalogue absentes de l'inventaire**. Elles y
  sont, coupees sur deux lignes de source par une concatenation. **Faux positif de mon test de
  sous-chaine**, pas un defaut - la lecon de s05, cette fois sur mon propre outil de verification.
- Mon controle du partage a signale **2 paquets inscrits au registre d'exemption alors qu'ils
  portent des propositions commencant par un chiffre** : `cm1-division-posee` et
  `cp-ranger-nombres`. Leurs motifs ecrits expliquent exactement pourquoi - *quotient reste X* et
  *liste ordonnee de trois nombres* ne sont pas des valeurs scalaires, et `valeurDe` y rend `null`
  depuis `ADR-0068`. **Mon critere du premier chiffre n'est pas celui du garde-fou. Aucun defaut**,
  et les deux inscriptions sont anterieures a s31.

**Verdict : les deux inscriptions sont coherentes entre elles, avec le catalogue et avec le contenu
servi.** Aucune demande.

## 5. La limite structurelle du registre `PAR-5` - **l'attraper a la fusion est la reponse**

**Le fait, et il est arrive des le premier assemblage.** Le registre a ete construit dans le
worktree `s31-theo`, isole de `s31-nadia-arbitrage` par construction. `nadia-prof` a nomme quatre
clauses le meme jour dans cette branche soeur ; le registre ne pouvait pas les voir. A
l'assemblage, `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` **a fait rougir la CI**.
`sofia-sm` a ferme le rouge par une allocation mecanique de quatre lignes.

**Ma reponse : rien de plus n'est du, et le motif n'est pas la prudence.** Elle est ecrite au long
dans l'amendement du 2026-09-07 d'`ADR-0085`, en trois points :

1. **Ce n'est pas une limite du registre, c'est la regle de gouvernance qui parle.** Le depot est la
   seule memoire partagee ; une branche non fusionnee n'est pas encore le depot. Demander a ce
   registre de voir une allocation non fusionnee, c'est lui demander d'etre vrai d'une chose qui, au
   sens de `CLAUDE.md`, **n'existe pas encore**. La fusion est le seul instant ou la question est
   bien posee, et c'est la que la clause mord.
2. **Le mode de defaillance est le bon** : rouge bruyant, au plus tot possible, sur ce qui manque.
   **La propriete de `PAR-5` n'a jamais ete menacee** - la cecite produit une **ligne absente**, pas
   deux conditions sous un meme nom.
3. **Tout dispositif qui la supprimerait couterait plus qu'elle.** Faire lire les branches soeurs a
   un garde-fou, c'est balayer le travail non fusionne d'un autre agent - ce que l'amendement de
   `squad/README.md` interdit. Une pre-allocation ceremonielle au planning couterait a **chaque**
   sprint pour eviter un defaut qui a coute **un commit mecanique**.

**Ce que je ne minimise pas.** La cecite a produit ici le cas **bon marche**. Le cas que `PAR-5`
vise vraiment - deux instances allouant le meme identifiant a deux conditions differentes - rougit
aussi a la fusion, **mais son correctif n'est pas mecanique** : il faut renumeroter et reprendre
toutes les citations. **Le registre rend ce cas visible ; il ne le rend pas bon marche.**
Declencheur nomme : la **premiere** collision de sens constatee a une fusion, et pas avant.

### Un chiffre de la note d'assemblage, corrige

La note de `sofia-sm` annonce *"527 identifiants, sur **83** prefixes (aucun prefixe neuf : `BIN`,
`CAT`, `FAB` et `PLA` etaient deja **libres**)"*. **Les deux affirmations sont fausses, et le mot
*libres* le disait deja** - un prefixe libre qu'on prend devient un prefixe de plus.

*Methode : extraction des lignes d'allocation de la section 4 et denombrement des prefixes
distincts, a `84198564` puis a `3181b5e6`.* **83 prefixes avant, 87 apres ; les quatre prefixes
sont neufs.** Le compte d'identifiants, lui, etait juste. **Corrige dans le registre**, dans ce
commit ; aucune allocation et aucun sens ne changent. Le geste d'allocation de `sofia-sm` etait
exact, c'est son commentaire de comptage qui ne l'etait pas.

**Etat du registre a `3181b5e6`, verifie par ma propre lecture** : **527 lignes d'allocation, 527
identifiants distincts, aucun doublon, 87 prefixes**, aucun document allouant introuvable, aucun
document allouant muet. Les quatre identifiants de `nadia-prof` y sont.

## 6. `SAI-R1` - rien a reprendre

Non livre par arbitrage de `camille-po`, la composition saturant la borne. **Mon instruction tient
telle quelle pour s32** et n'est pas rejouee ici : cause a `fragments/saisie.html:120`, correctif de
douze caracteres, rayon mesure - 5 concatenations de texte servi dont **une seule** ajoute un
litteral apres une valeur du modele, 2 assertions, 0 regle CSS.

## 7. Verdict

**Porte technique verte.** `mvn -B clean verify` sur le reacteur complet, **1385 tests, 0 echec, 0
erreur**, `BUILD SUCCESS`, JDK 21.0.10, worktree `s31-theo-verif` a `3181b5e6`.

**Regles ArchUnit : 33 par les trois voies, delta du sprint zero**, contrairement a ce qui m'etait
annonce.

**Zero ligne de Java de production, zero gabarit** pour huit paquets neufs, deux reprises et huit
entrees de catalogue.

**Le Done pedagogique n'est pas prononce ici.** Il appartient a `nadia-prof`, et a elle seule.
