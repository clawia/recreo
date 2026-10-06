# Arbitrage prealable de s52 - les instances de `nadia-prof` pour le CE1 : deux, pas trois ;
# s51 clot sans defaut de couture, la condition (i) et `PAR-2` bornent le reste

- **Auteur** : `nadia-prof`, professeure des ecoles, **seule autorite sur le Done pedagogique**.
- **Date** : 2026-10-06, apres la cloture de s51.
- **Arbre** : `round-two` a `85fa65aa`, arbre de travail propre (`git status --short` vide).
- **Instruction recue** (forme 3, `squad/README.md` section 2 bis, recue de MASTER, sans source dans le
  depot, citee mot pour mot) : *"créée plusieurs instances de nadia pour couvrir le CE1"*. **Elle ne
  fonde pas la borne.** La borne vient des conditions ecrites ci-dessous, pas du nombre demande.
- **Reponse, avant tout le reste** : **deux instances pour le CE1 a s52, pas plus** : A sur le francais
  CE1, C sur les mathematiques CE1. **Je refuse d'amender `PAR-2` pour partager le francais CE1 par
  domaine** (section 5), et **je refuse la borne a trois instances** : s51 clot sans occurrence des
  formes (a), (b) ou (c), `PAR-13` tient, mais la condition (i) n'est pas remplie (section 6).
- **Audit degrade, et je le declare** : aucun increment n'est en jeu, je n'ai rien joue. J'ai lu
  `DEC-0110` sections 14 a 17, la review, la retro, le `RAPPORT.md`, le `rapport-qualite.md` et l'avis de
  faisabilite de s51, l'historique Git de s51, la matrice, mes arbitrages s29 a s34, s43 (trois lots),
  s46, s48 et s51, le backlog de la section "Sprint 51", et **des transcriptions de sous-agents de s51
  conservees hors du depot** (section 2, liste exacte).

---

## 1. Trois corrections et une decision avant de m'en servir

### 1.1 J'ai omis `PAR-11` dans mon arbitrage de s51

Mon arbitrage de s51 (section 1.2) traitait l'ecart entre `PAR-9` et la pratique. Il ne disait rien de
**`PAR-11` amendee** (`ARBITRAGE-PREALABLE-s32...`, section 5.3) : *"0,5 par lot neuf audite, par
instance, plus 0,5 pour toute instance qui audite une matiere dont mes conditions n'ont jamais
rencontre de contenu reel"*. Je l'ai citee comme opposable a s33 et a s34 ; aucune `DEC` depuis s41 ne
l'applique (`grep -rln "PAR-11" squad/decisions/produit` : derniere occurrence `DEC-0085`, s33), et je
ne l'ai pas releve.

**Ce que s51 en montre, mesure.** B portait trois lots neufs avec 0,5 de marge (`DEC-0110` section 2) ;
`PAR-11` en demandait 1,5. B a eu deux refus au premier passage et n'a pu en rejouer qu'un :
`SCI-CM1-15` reste sans rejeu, faute de budget (`DEC-0110` 15.2). A, sur une matiere dont les
conditions avaient deja mordu du contenu, portait trois lots neufs et a consomme 0,5 (un rejeu).

**Ce que j'en fais.** Je ne fais pas de `PAR-11` une condition retroactive contre une pratique que j'ai
laissee courir dix sprints sans objection. Je chiffre chaque instance dans les deux lectures (section 4)
et je route le choix a `camille-po` : c'est la question 2 de `DEC-0110` 14.7, elargie a `PAR-11`. Le
verdict de la section 7 tient dans les deux.

### 1.2 Le supplement du lot illustre : je le demande pour `MAT-CE1-17` et `MAT-CE1-06`

`DEC-0110` section 2 m'a laisse la question : *"Je ne decide pas si `nadia-prof` le demandera sur une
designation inedite"*. Mon tarif est ecrit depuis s28 (`ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne...`,
section 4.3) : lot illustre, `REC-1`, **+0,5**. Je l'applique aux deux lots dessines de C : chaque dessin
se nomme propositions masquees, et sur la symetrie je reste seule a verifier qu'aucune zone ne contient un
segment d'une autre ligne (avis de faisabilite de s51, section 5.3). `S39-SOLIDES` a ferme a 1,0 sans ce
supplement ; je ne rouvre pas s39, et je n'en fais pas un precedent contre mon tarif.

### 1.3 Ma condition (i) se lit avec la reserve que j'ai datee du 2026-10-05

Arbitrage de s51, section 4 : *"je compterai l'excedent prenable par l'instance supplementaire sous
`PAR-1` et `PAR-2`, pas l'excedent brut"*. Je l'applique ici, sans autre changement de texte.

### 1.4 Ce document est celui que j'ai annonce

Arbitrage de s51, section 8 : *"Si s51 clot sans aucune occurrence des formes (a), (b) ou (c), verifiee
par moi contre les trois formes, transcriptions comprises, la derivation a trois instances pour s52 se
fait dans un document neuf."* C'est ce document. Il ferme l'action A1 de la retro de s51 et le point R12
du planning.

---

## 2. Condition (ii) - s51 contre les formes (a), (b) et (c)

**Les trois formes, inchangees** (`ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp...`, definition
du defaut de couture) : (a) une story qui modifie un paquet et une story qui modifie sa consigne dans le
meme sprint, laissant la consigne fausse ; (b) un chiffre de sprint qu'aucune moitie ne peut verifier et
qu'un tiers trouve faux en recomptant ; (c) deux instances ecrivant dans une meme ressource partagee
sans le savoir - identifiants, fichier, scratchpad. *"Une seule occurrence de l'une des trois formes
suffit."* Je juge la forme (c) comme a s50 : elle se constate a l'ecriture concurrente d'une meme
ressource physique, pas a son effet.

**s51 est un sprint parallele.** Fenetres lues dans les transcriptions (UTC+02:00, premier et dernier
appel d'outil) :

| Couloir | Agent | Fenetre | Worktree | Repertoire temporaire |
|---|---|---|---|---|
| A | `lea-dev` | 12:11:12 - 14:44:21 | `recreo-s51-nadia-instance-a` | `<scratchpad>/instance-a` |
| A | `nadia-prof` (avis sur A1) | 13:47:54 - 13:52:00 | `recreo-s51-nadia-instance-a` | aucun |
| A | `nadia-prof` (audit, deux passages) | 14:14:36 - 14:50:25 | `recreo-s51-nadia-instance-a` | `<scratchpad>/instance-a` |
| B | `marc-dev` | 12:11:12 - 14:41:33 | `recreo-s51-nadia-instance-b` | `<scratchpad>/instance-b` |
| B | `nadia-prof` (audit, deux passages) | 14:09:41 - 14:45:02 | `recreo-s51-nadia-instance-b` | `<scratchpad>/instance-b` |

`<scratchpad>` designe le repertoire temporaire de la session de s51.

**Transcriptions lues**, hors du depot, sous
`$HOME/.claude/projects/<projet>/1dc17d75-1f5a-4b9f-af1c-ba3edf258c62/subagents/` :
`agent-a55fec0147e56e987` (`nadia-prof`, A), `agent-aec0272a3993e99f1` (`nadia-prof`, B),
`agent-a63b236aeba82a666` (`lea-dev`), `agent-acc1858ab6a6d92c9` (`marc-dev`),
`agent-a3f6c8cf84d0018fd` (`nadia-prof`, avis sur A1), et pour leurs seuls appels qui nomment le
scratchpad, `agent-ade891f7b41488f8c` (`theo-archi`, A1), `agent-a0ddc85a6740053eb` (`theo-archi`,
confirmation ArchUnit) et `agent-a37d23e01bb3ffc51` (`yanis-qa`). **Non lues** : la transcription de la
session principale et celles des autres agents au-dela de leurs appels sur le scratchpad.

**Methode.** Deux scripts de poste, dans mon propre sous-repertoire de scratchpad : le premier liste
chaque appel d'outil d'une transcription avec son horodatage et sa commande ; le second attribue chaque
fichier de `instance-a`, `instance-b` et de la racine du scratchpad aux agents dont un appel le nomme,
sur les 24 transcriptions declarees par leur `meta.json`. Les horodatages de `ls -la --time-style` sur
ces repertoires concordent.

### 2.1 Forme (a) - aucune occurrence

| Constat | Methode |
|---|---|
| Les six consignes servies de s51 sont ecrites une fois, dans le commit qui cree l'entree de catalogue, et jamais modifiees ensuite | boucle `git show --numstat --format= <sha> -- adapters/adapter-content/src/main/resources/catalogue.yaml` sur `git rev-list --no-merges e4d8ba3b..HEAD` : six commits, `6 0` chacun (`271058ec`, `e25a1cd3`, `24430ed3`, `0e9913c1`, `d88268d5`, `6971d069`) |
| Les trois corrections apres verdict ne touchent que leur propre paquet, et la consigne reste vraie sur le paquet livre | `git show --stat` : `df608669` (graphie, rejeu), `cc5686e0` (homophones), `5a4ca6d8` (activite humaine). Les seconds passages `7efcb400` et `81908b7d` jouent le paquet final sous sa consigne |
| `df608669` touche aussi `ce1-ordre-alphabetique.yaml`, une autre story du meme couloir : un commentaire YAML | `git show df608669 -- .../ce1-ordre-alphabetique.yaml` : une ligne d'en-tete, *"cinq items"* devient *"quatre items"* ; aucune consigne, aucun item |

### 2.2 Forme (b) - aucune occurrence trouvee

| Chiffre de sprint | Recompte | Methode |
|---|---|---|
| 127/182 a la cloture | **127 `couvert-valide`, 3 `en cours`, 52 `non couvert`, 76 `non couvrable`** | l'`awk` de mon arbitrage de s51 (section 2.2, filtre `NF>=8`) sur la matrice a `85fa65aa` |
| Registre : 919 identifiants alloues, 158 prefixes | **919 lignes en section 4** ; garde-fou vert | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest -Dsurefire.failIfNoSpecifiedTests=false` sur `85fa65aa` : *"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS` |
| 918 puis 923 *"lignes d'identifiant"* (`review.md`, obstacle 2) | **exact dans sa methode, autre perimetre** : le compte des lignes de tableau qui commencent par un identifiant, sur tout le fichier, rend 918 a `e4d8ba3b` et 923 a `HEAD` ; la section 4 seule rend 914 et 919 ; l'ecart de 4 est constant (2 lignes avant la section 3, 2 dans la section 3) | `git show <rev>:squad/decisions/REGISTRE-identifiants.md`, compte global et compte apres l'en-tete `## 4.` |
| Engage 8,0, consomme 9,0 (A 4,0, B 5,0) | coherent | `DEC-0110` 16.2 contre les verdicts et les deux rejeux (`df608669`, `5a4ca6d8`) |
| 29 commits depuis le planning, 4 fusions | coherent | `git rev-list --count e4d8ba3b..047b91c4` : 29 ; `--merges` : 4 |

**Non recompte par moi** : le total de 2076 tests. Il a ete mesure deux fois par deux tiers, sur deux
arbres (`yanis-qa` sur `ca4c934a`, `sofia-sm` sur `047b91c4`). Je ne l'ai pas relance.

**Deux chiffres pour un meme registre ne font pas une forme (b)** : aucun des deux n'est faux, chacun nomme
sa methode. Je note que la review en publie un que la CI ne verifie pas ; le chiffre qui fait foi est
celui de la ligne d'etat, 919, verifie par le garde-fou.

### 2.3 Forme (c) - aucune occurrence

| Ressource | Ce que les transcriptions et l'arbre montrent | Qualification |
|---|---|---|
| **Scratchpad** | Chaque agent de couloir n'a ecrit que dans le sous-repertoire de son couloir : `instance-a` (43 references chez `nadia-prof` A, 26 chez `lea-dev`), `instance-b` (25 chez `nadia-prof` B, 19 chez `marc-dev`), **aucune** reference a l'autre couloir ni a la racine. Les deux `jouer.py` sont ecrits a 14:26:38 (A, 4 101 octets) et 14:27:11 (B, 1 879 octets), dans deux repertoires : la situation de s50, sans l'ecrasement | **pas de forme (c)** |
| **Worktrees** | Ecritures de A dans le seul worktree A, de B dans le seul worktree B. Une exception, **en lecture** : `nadia-prof` B lit a 14:14:05 et 14:14:10 l'arbitrage `...fra-ce1-22-graphie-cedille.md` dans le worktree A | lecture, pas ecriture : **pas de forme (c)** |
| **Serveurs de jeu** | Ports 18451 et 18452 (`lea-dev`), 18751 et 18752 (`nadia-prof` A), 18651 (`marc-dev` a 13:44, arrete a 13:45:25, puis `nadia-prof` B a 14:17) : disjoints entre couloirs | **pas de forme (c)** |
| **`REGISTRE-identifiants.md`** | Conflit a la fusion `7716be0e`. A ecrit `TPS-CE1-R1` et `ALP-CE1-R1` dans son worktree (ligne d'etat 916/157), B ecrit `CRP-CM1-R1` a `-R3` dans le sien (917/158). **Chaque instance savait l'autre** : la note de B, ecrite dans le registre, dit *"L'instance A travaille en parallele sur le meme fichier : si elle alloue aussi, le compte se reverifie a la fusion"*. Aucun identifiant commun (`git show 7716be0e:...REGISTRE-identifiants.md`, cinq lignes distinctes), le prefixe `CRP` cherche avant d'ecrire. Ligne d'etat fusionnee 919/158, garde-fou vert (section 2.2) | deux copies dans deux worktrees, sans ecriture concurrente d'une meme ressource physique, et en le sachant. C'est la procedure de `DEC-0110` 14.4 qui a fonctionne : **pas de forme (c)** |
| **Fichiers partages par les deux couloirs** | `catalogue.yaml`, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java`, `TexteAccentueExposeParLePortEntrantTest.java` modifies des deux cotes, fusionnes sans conflit. **Aucune ligne ajoutee par un cote n'est perdue** dans `7716be0e` | `git diff <base> <cote> -- <fichier>`, puis chaque ligne ajoutee cherchee dans `git show 7716be0e:<fichier>` : zero manquante sur les trois fichiers, des deux cotes. **pas de forme (c)** |

**Deux observations hors de la forme (c), que j'ecris pour ne pas les avoir vues sans les dire** (s50,
section 1.1) :

1. **A l'interieur d'un couloir, le developpeur et l'auditrice partagent le sous-repertoire.** Dans
   `instance-b`, le fichier de cookies `cj` est ecrit par `marc-dev` (13:44 a 13:45) puis par
   `nadia-prof` B (14:17 a 14:18), qui ne le savait pas. Usages successifs, 32 minutes d'ecart, cookie
   d'un serveur arrete : aucun effet sur un verdict. Ce n'est pas deux instances ; mais c'est le geste de
   s50 en petit.
2. **La racine du scratchpad reste partagee par les agents hors couloir.** `verify.log` y est ecrit par
   `theo-archi` (A1, 13:41 a 13:46) puis par `yanis-qa` (14:53 a 14:58). Successifs, sans effet. La regle
   de `squad/README.md` section 9 ne vise que les instances.

**Reponse a l'action A1 de la retro de s51** : *un sous-repertoire du scratchpad de session suffit-il ?*
**Oui, pour la propriete que la forme (c) garde** : deux chemins disjoints, mesure a 33 secondes
d'ecart. **Reserve nouvelle, datee du 2026-10-06, qui ne conditionne pas s52** : un sous-repertoire
**par agent** et non par couloir (`instance-a/nadia`, `instance-a/dev`), aucune instance a la racine, un
port de serveur par agent. Elle ferme l'observation 1 ; elle n'est pas un durcissement de la condition
(ii), qui reste telle que je l'ai ecrite.

### 2.4 Verdict sur la condition (ii)

> **Remplie.** s51, dernier sprint parallele, clot sans occurrence des formes (a), (b) ou (c), verifiee
> le 2026-10-06 contre les trois formes, transcriptions des deux couloirs comprises.

---

## 3. Condition (iii) - `PAR-13` : **confirmee pour s52**

`PAR-13` (`ARBITRAGE-PREALABLE-s32...`, section 6.2) : *"Au plus une instance par sprint prend un terrain
dont mes conditions n'ont jamais rencontre de contenu reel."* Je la juge au niveau du terrain, comme a
s51.

| Instance | Terrain neuf ? | Preuve |
|---|---|---|
| A, francais CE1 | **non** | six lignes `couvert-valide` (`FRA-CE1-01`, `-02`, `-16`, `-20`, `-22`, `-23`), filtre francais/CE1 de l'`awk` de la section 2.2 ; `TRV-CE1-1` a `-7` et `LON-CE1-1`, `-2`, `-5` ont mordu six paquets, dont trois refus suivis de corrections |
| C, mathematiques CE1, designation et dessins | **oui** | neuf lignes fermees, mais `SYM-CE1-1` a `-3`, `MUL-CE1-4`, `MUL-CE1-5`, `ZON-13` sur des lignes qui se croisent et la designation au CE1 n'ont jamais rencontre de contenu |

Une seule instance sur un terrain neuf : `PAR-13` tient. C porte seule le premier paquet de designation
au CE1, sans autre terrain neuf (arbitrage de s51, section 6.2). A porte l'accumulation (`PAR-7`,
`PAR-12`) : le francais CE1 deja audite et ses deux reserves.

**Ce que la condition ne dit pas, et que je nomme sans en faire une barre** : `FRA-CE1-04` a `-07`
portent un `texteSupport` et le texte masque (`NAR-6` transpose), que je tiens seule et qui n'a jamais
rencontre de contenu au CE1 (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`, section 9, point 1). Le choix des
lignes de A est a `camille-po` ; si elle les prend a s52, le risque d'un premier passage refuse est celui
que `PAR-11` chiffre.

---

## 4. Ce qui est instruit et atteignable en s52, par instance

**Regles appliquees** : `PAR-8` (plafond 5,0 par instance, trois lots au plus, rejeu de 0,5 nomme),
`PAR-10` (correction 0,5 par paquet), mon tarif de s28 section 4.3 (1,0 matiere dont je suis la source,
+0,5 lot illustre), `DEC-0107` section 4 (une correction n'est pas un lot). **Deux lectures de la marge**
(section 1.1) : celle de la pratique depuis s41, et `PAR-11` amendee.

### 4.1 Instance A - francais CE1

**Vivier, recompte** (matrice a `85fa65aa`, tableau "Francais - CE1") : 6 `couvert-valide`,
20 `non couvert`, 5 `non couvrable en l'etat`. Des 20, **18 sont atteignables a `couvert-valide`** et 2
sont plafonnees `en cours` (`FRA-CE1-03`, `-21` ; `ARBITRAGE-PREALABLE-s48...`, section 12). Les 18 :
`FRA-CE1-04` a `-15`, `-17` a `-19`, `-24` a `-26`, toutes **ACCEPTEES SOUS CONDITIONS** a s48. Aucune n'a
encore de story ni d'avis de faisabilite (`DEC-0110` 16.10) : je n'en designe aucune.

| Poste | Tarif | Lecture pratique | Lecture `PAR-11` |
|---|---|---|---|
| trois lignes neuves de francais CE1, une par lot | 1,0 chacune | 3,0 | 3,0 |
| `TPS-CE1-R1` (`FRA-CE1-20`, correction differee, premier report) | 0,5 | 0,5 | report |
| `ALP-CE1-R1` (`FRA-CE1-23`, correction differee, premier report) | 0,5 | 0,5 | report |
| marge | | rejeu 0,5 | 3 lots x 0,5 = 1,5 |
| **Total / plafond** | | **4,5 / 5,0**, engage 4,0 | **4,5 / 5,0**, engage 3,0 |
| **Fermetures au mieux** | | **3** | **3** |

En lecture `PAR-11`, les deux corrections ne tiennent qu'en retirant une ligne : deux lignes, deux
corrections, marge 1,0, total 5,0, deux fermetures au mieux. Le choix est a `camille-po`.

### 4.2 Instance C - mathematiques CE1

**Vivier, recompte** (tableau "Mathematiques - CE1", 18 lignes) : 9 `couvert-valide`, 1 `en cours`
(`MAT-CE1-09`), 8 `non couvert`. Les huit, lues dans mes arbitrages de s43 :

| Ligne | Verdict (s43) | Plafond | En s52 |
|---|---|---|---|
| `MAT-CE1-17` symetrie | ACCEPTE SOUS CONDITIONS, `SYM-CE1-1` a `-3`, lot 3 section 3 | `couvert-valide`, avec la mention fixee d'avance | **prise** : designation, dessins |
| `MAT-CE1-06` sens de la multiplication | ACCEPTE SOUS CONDITIONS, lot 1 section 6 | `couvert-valide` **avec les dessins de `MUL-CE1-5`** ; `en cours` sur texte seul | **prise** : lot illustre |
| `MAT-CE1-02` comparer, ranger, encadrer | COUVERTURE PARTIELLE sur texte, lot 1 section 3 | `en cours` sur le paquet textuel ; `couvert-valide` seulement avec une ligne graduee dessinee | **moitie textuelle prise** : passe a `en cours`, **ne ferme pas** |
| `MAT-CE1-14` quadrillage | ACCEPTE SOUS CONDITIONS, lot 3 section 1 | `couvert-valide` sur deux moities, dont une designation | hors capacite : quatrieme lot, et A3 d'`ADR-0080` |
| `MAT-CE1-01`, `-10`, `-11`, `-15` | COUVERTURE PARTIELLE (lots 1, 2, 3) | **`en cours` au plus** | non comptees : aucune ne ferme |

| Poste | Tarif | Lecture pratique | Lecture `PAR-11` |
|---|---|---|---|
| `MAT-CE1-17` (`S51-MAT-SYMETRIE`) | 1,0 + 0,5 (`REC-1`) | 1,5 | 1,5 |
| `MAT-CE1-06` (`S51-MAT-MULTIPLICATION`) | 1,0 + 0,5 (`REC-1`) | 1,5 | 1,5 |
| `MAT-CE1-02`, moitie textuelle (`S51-MAT-COMPARER-RANGER`) | 1,0 | 1,0 | retiree |
| marge | | rejeu 0,5 | 2 lots x 0,5 + 0,5 terrain neuf = 1,5 |
| **Total / plafond** | | **4,5 / 5,0**, engage 4,0, 3 lots | **4,5 / 5,0**, engage 3,0, 2 lots |
| **Fermetures au mieux** | | **2** (`-17`, `-06`) ; `-02` passe a `en cours` | **2** |

**Ce qui conditionne C, et qui n'est pas a moi** (avis de faisabilite de s51, sections 2 et 7) :
`MAT-CE1-17` est faisable sous condition d'A2 (`ADR-0080`, `ZON-13` et rang de la bonne zone en
garde-fou, *"avant le premier paquet de designation de CE1"*) et du materiau ; `MAT-CE1-06` sous condition
du materiau ; Q3 (`ines-design`) et Q5 (`sofia-sm`) sont bloquantes pour les deux. **A `85fa65aa`, A2
n'est pas ecrit** (`grep -n "^## Amendement" squad/decisions/adr/ADR-0080*.md` : trois amendements, s32,
s34, s35) **et aucune contrainte de dessin de C n'existe** (`ls squad/sprints/*/ | grep -i contrainte`
rend deux fichiers, de s25 et de s39 ; aucun ne nomme la symetrie ni la multiplication).
`MAT-CE1-02` moitie textuelle est faisable en l'etat. Si A2 ou le materiau manquent a s52, C ne ferme
rien et seule `MAT-CE1-02` bouge.

### 4.3 Le sprint

Lecture pratique : **engage 8,0** (A 4,0, C 4,0), exactement la borne a deux instances que `DEC-0098`
a verifiee et que s51 a tenue ; chaque instance sous 5,0 avec son rejeu. **Au mieux cinq fermetures,
127/182 a 132/182 (72,5 %)**, et `MAT-CE1-02` passe a `en cours`. Lecture `PAR-11` : engage 6,0,
cinq ou quatre fermetures. Ni l'une ni l'autre n'est une promesse : un refus reste un refus.

**`MAT-CE1-17`** : neuf sprints de report (s43 a s51, `DEC-0110` 14.5). S'il est engage a s52, le compte
s'arrete a neuf.

---

## 5. Amender `PAR-2` pour partager le francais CE1 par domaine : **refuse**

**Ce que la demande pourrait gagner.** `PAR-2` : *"Une matiere et un niveau ne sont jamais partages, ni
dans un sprint ni entre deux lots du meme sprint."* Le CE1 n'a que deux couples dans la matrice
(`awk` sur la colonne niveau : francais 31 lignes, mathematiques 18). Sans amendement, deux instances au
plus. Partager les mathematiques CE1 ne sert a rien : les seules lignes qui ferment sont des lots de
designation ou de dessin, qui vont a une seule instance (section 3), et le reste plafonne `en cours`.
Seul le francais CE1 pourrait porter une troisieme instance :

| Domaine du referentiel (s48) | Lignes atteignables restantes | Plafonnees `en cours` |
|---|---|---|
| Lecture et comprehension (`FRA-CE1-01` a `-07`) | `-04`, `-05`, `-06`, `-07` | `-03` |
| Ecriture (`-08`, `-09`) | `-08`, `-09` | |
| Orthographe et grammaire (`-10` a `-22`) | `-10` a `-15`, `-17` a `-19` | `-21` |
| Vocabulaire (`-23` a `-26`) | `-24`, `-25`, `-26` | |

**Motif 1 - une frontiere de lignes traverse deux domaines.** La table de frontieres de mon arbitrage
de s48 (section 7) porte `FRA-CE1-08` / `-09` / `-10` : liste de mots, phrase fautive, groupes a
reconnaitre (`OMP-CE1-3`, `RLP-CE1-1`, `TPH-CE1-2`). `-08` et `-09` sont en Ecriture, `-10` en
Orthographe et grammaire. Et `RLP-CE1-1` fait reperer un *"accord oublie (sujet-verbe ou groupe
nominal)"*, c'est-a-dire le contenu de `FRA-CE1-14` et `-15`. Partagee entre deux instances, la propriete
*"chaque paquet travaille la ligne qu'il declare, pas la voisine"* (s48, section 9, point 7) serait tenue
par moitie : c'est la portee **L** ou **M** de `ARBITRAGE-PREALABLE-s29...` section 13.2, celle que `PAR-1`
et `PAR-2` existent pour proteger.

**Motif 2 - l'amendement contredit `PAR-13` a s52.** Si le domaine devient l'unite d'affectation, il
devient aussi l'unite de jugement du terrain neuf. Les conditions de l'Ecriture (`OMP-CE1-*`,
`RLP-CE1-*`), du Vocabulaire restant (`FAM-CE1-*`, `SYC-CE1-*`, `CTG-CE1-*`) et de la Lecture restante
(`RCT-CE1-*`, `PRN-CE1-*`, `INF-CE1-*`, `DOC-CE1-*`) n'ont jamais rencontre de contenu. Une instance
detachee sur l'un de ces domaines serait un second terrain neuf a cote de C. Lu au niveau de la matiere,
comme en section 3, `PAR-13` tient precisement parce que le francais CE1 est un seul terrain : la meme
lecture interdit son partage.

**Motif 3 - une ressource propre au francais, deja amendee a s51.** `FRA-CE1-22` n'a ete faisable qu'au
prix de l'amendement A1 d'`ADR-0065` et d'une clause neuve de `AucunMotServiNeSEcarteDeSonAccentTest` ;
`ADR-0065` (ligne 295) ecrit qu'une autre marque *"appellerait un nouvel amendement, nommant cette
marque"*. Deux instances de francais en parallele peuvent avoir a amender le meme `ADR` et le meme
test : c'est la forme (c) sur un fichier, celle que s50 a produite. Les autres fichiers partages
(`catalogue.yaml`, les inventaires de tests) ne sont pas propres au francais et ont fusionne sans perte a
s51 : je ne les compte pas comme motif.

**Motif 4 - meme amende, la condition (i) n'est pas remplie.** Le seul domaine que la table de
frontieres isole, le Vocabulaire (frontiere interne `-24` / `-25`, et `-26` contre questionner le monde,
qui est une obligation de lecture), porte trois lignes : 3,0 de lots au plus, sous les 4,0 de la
condition (i). Aucun domaine ne peut d'ailleurs fournir plus de trois lots a une instance (`PAR-8`).

> **Verdict : REFUS d'amender `PAR-2` pour le francais CE1, pour s52 et sans fenetre.** Motif
> actionnable : la frontiere `FRA-CE1-08` / `-09` / `-10` et la dependance de `RLP-CE1-1` a `-14` et `-15`
> traversent les domaines (motif 1) ; l'amendement cree un second terrain neuf a cote de C (motif 2) ;
> `ADR-0065` et son garde-fou seraient amendes en parallele (motif 3). **Ce qui me ferait reexaminer** :
> une proposition ecrite de `camille-po` qui decoupe le francais CE1 sans couper aucune frontiere de la
> table de s48 section 7, sur un sprint ou aucune autre instance ne porte de terrain neuf, avec un avis
> de `theo-archi` qui dit comment deux instances n'amendent pas `ADR-0065` en meme temps. Je jugerai
> alors le decoupage, pas le nombre.

---

## 6. La derivation a trois instances pour s52 : **refusee sur la condition (i)**

| Condition (`ARBITRAGE-PREALABLE-s46...`, section 5) | Etat |
|---|---|
| (i) une composition a deux instances remplit 8,0 et laisse dehors au moins 4,0 de lots instruits, **comptes comme prenables par la troisieme instance sous `PAR-1` et `PAR-2`** (reserve du 2026-10-05) | **non remplie** |
| (ii) le dernier sprint parallele clot sans forme (a), (b) ou (c) | **remplie** (section 2) |
| (iii) `PAR-13` | **confirmee** (section 3) |

**Ce qu'une troisieme instance pourrait prendre a s52.** Aucun document de planning de s52 n'existe ; je
compte sur les candidats de `DEC-0110` 16.10, le seul perimetre ecrit.

| Couple matiere/niveau libre sous `PAR-2` | Prenable | Lots neufs |
|---|---|---|
| francais CE1, mathematiques CE1 | **0** : deja a A et a C | - |
| sciences et technologie CM1 | `S51-SCI-ACTIVITE-HUMAINE-CM1-REPRISE` 0,5, `S51-SCI-TERRE-CM1-REPRISE` 1,0, `S51-SCI-CORPS-CM1-RESERVES` 0,5 : **2,0** | **0** (reprises et corrections) |
| les autres couples | aucun vivier instruit non ecarte (`DEC-0110` section 7, *"Pourquoi pas quatre"*, non dementi depuis) | - |

**2,0 au plus, aucun lot neuf, contre 4,0.** La condition (i) n'est pas remplie ; les trois conditions
sont cumulatives. **Je ne derive pas de borne** et je n'ecris pas de chiffre au-dela de 8,0 : la formule
de s32 n'est pas une borne sans les conditions qui l'autorisent.

**Ce que ce refus laisse, et que je dis.** A deux instances sur le CE1, aucune instance ne peut reprendre
les deux paquets de sciences CM1 refuses (`PAR-2`), et la decision de les garder servis court *"jusqu'a
s52"* (`DEC-0110` 16.9). **Ma position, de pedagogue** : un paquet refuse pour une cle de reponse fausse
dit a l'enfant *"Ce n'est pas ça."* sur une reponse vraie ; je l'ai fait a l'ecran. Sans reprise engagee
a s52, je demande son retrait du catalogue servi. La tranche entre retrait, reprise ou substitution d'une
instance de sciences a l'une des deux instances de CE1 est a `camille-po` et a l'utilisatrice.

---

## 7. Verdict

> **Deux instances de `nadia-prof` sont ouvrables a s52 pour le CE1, et pas plus.**
>
> - **A, francais CE1** : trois lignes neuves prises parmi les dix-huit instruites et atteignables de
>   s48 (choix et stories a `camille-po`), plus `TPS-CE1-R1` et `ALP-CE1-R1`. 4,0 engages, 4,5/5,0 avec
>   son rejeu. Trois fermetures au mieux.
> - **C, mathematiques CE1** : `MAT-CE1-17` (1,5), `MAT-CE1-06` (1,5), `MAT-CE1-02` moitie textuelle
>   (1,0). 4,0 engages, 4,5/5,0 avec son rejeu. Deux fermetures au mieux ; `MAT-CE1-02` passe a
>   `en cours`. `MAT-CE1-14` reste dehors (quatrieme lot) ; `MAT-CE1-01`, `-10`, `-11`, `-15` ne
>   ferment pas.
> - **Pas de troisieme instance** : `PAR-2` n'est pas amende (section 5) et la condition (i) n'est pas
>   remplie (section 6).
>
> En lecture `PAR-11`, C retire `MAT-CE1-02` et A retire soit ses deux corrections, soit une ligne ; le
> nombre d'instances ne change pas.

---

## 8. Ce que je route

| A qui | Quoi |
|---|---|
| `camille-po` | Ecrire la `DEC` d'ouverture de s52 sur deux instances de CE1 (section 7), en citant ce document. Y trancher la marge : pratique depuis s41 ou `PAR-11` amendee (section 1.1), avec la question 2 de `DEC-0110` 14.7 ; et la passe transverse a deux instances (question 1 de 14.7), que ce document ne tranche pas |
| `camille-po` | Choisir les trois lignes de francais CE1 parmi les dix-huit et en ecrire les stories ; porter le tarif de `S51-MAT-SYMETRIE` et de `S51-MAT-MULTIPLICATION` a 1,5 (section 1.2) ; consigner que `MAT-CE1-17` s'arrete a neuf sprints de report si elle est engagee |
| `camille-po` | Amender `DEC-0110` 16.10 : la ligne de l'instance C cite *"sous la condition de `nadia-prof`"* ; la condition (ii) est remplie et la borne a trois refusee (sections 2.4 et 6). Trancher, avant tout poste les touchant, retrait ou reprise des deux paquets de sciences refuses (section 6, `DEC-0110` 16.10) |
| `camille-po` | Rien a amender dans la matrice ni dans `REGISTRE-identifiants.md` au titre de ce document : il ne deplace aucun statut et ne cree aucun identifiant |
| `sofia-sm` | Ouvrir s52 a deux worktrees, deux branches, et un sous-repertoire de scratchpad **par agent** dans chaque couloir, un port de serveur par agent (reserve de la section 2.3). Clore R12 de s51 et l'action A1 de la retro par ce document |
| `theo-archi` | A2 d'`ADR-0080` avant le premier paquet de `MAT-CE1-17` (avis de s51, V8) ; A3 n'est pas du a s52 si `MAT-CE1-14` et la moitie designation de `MAT-CE1-02` restent dehors |
| `ines-design` | Contraintes de dessin des figures a lignes candidates, des quadrillages 6 x 6 et des reseaux de points (Q3 de l'avis de s51), avant tout dessin de C |

---

## 9. Ce dont je reste la seule garde

| Propriete | Qui la tient |
|---|---|
| La forme (c) sur un scratchpad, un port ou un fichier hors depot | **moi seule**, en lisant des transcriptions qui ne sont pas dans le depot |
| La forme (c) sur les identifiants | le garde-fou `PAR-5` pour les formes qu'il reconnait, moi pour les autres |
| Les formes (a) et (b) | moi seule |
| Qu'aucune zone d'un item de symetrie ne contienne un segment d'une autre ligne, et `ZON-9` | moi seule, en jouant ; A2 ne couvrira que le non-chevauchement des cadres |
| Les dispositions de `MUL-CE1-5` lues sur le dessin et non dans l'enonce | moi seule, en jouant |
| Le texte masque sur `FRA-CE1-04` a `-07`, si l'une est choisie | moi seule |

---

## 10. Ce que ce document ne fait pas

- Il ne prononce aucun Done pedagogique et ne touche a aucune fiche de jeu.
- Il ne modifie ni la matrice, ni le backlog, ni `DEC-0110`, ni le planning, ni le registre
  d'identifiants, et il ne cree aucun identifiant de condition ou de reserve.
- Il ne choisit pas les lignes de francais CE1 de A, et n'ouvre pas s52 : c'est la `DEC` de `camille-po`.
- Il ne tranche ni `PAR-9`, ni `PAR-11`, ni `PAR-4` a deux instances : il chiffre dans les deux lectures
  et route.
- Il ne change pas ma borne a deux instances (8,0) et n'efface rien de s46 ni de s51 : il en applique les
  conditions.
