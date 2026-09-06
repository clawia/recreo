# Verdicts de `nadia-prof` - s30, instance A

**Auteur** : `nadia-prof`, professeure des ecoles, **seule autorite sur le Done pedagogique**.
**Date** : 2026-09-06.
**Instance** : **A**, celle qui porte l'accumulation (`PAR-7`).
**Arbre audite** : `round-two`, HEAD `dbbfe129`, joue depuis le worktree `s30-nadia-A`, port 8082.
**Budget opposable** : **4,0** (`PAR-9`, clause que j'ai posee moi-meme a ce sprint).
**Consomme** : **3,50**. **Marge non consommee : 0,50.**

**Perimetre** : les blocs de reprise **A, B, C, E, F**, la passe transverse `S30-11`, `CONS-1` et
`REV-3`. **Le bloc D (`POS-R2`) et les quatre lots de sciences et technologie CM1 sont a l'instance
B, et je n'y touche pas.**

---

## 0. Le Done technique, mesure par moi et non reconduit

`bash outils/mvn-jdk21.sh -B clean verify` sur le **reacteur complet** (racine, pas de `-pl`),
**depuis mon propre worktree** comme la regle neuve du sprint l'exige : **BUILD SUCCESS**.

Comptage independant des rapports XML de tous les modules : **179 rapports, 1321 tests, 0 echec, 0
erreur, 0 ignore**, sur une machine virtuelle rapportant **21.0.10**. **Concorde avec `yanis-qa` et
`theo-archi`.**

*Note de methode, deuxieme sprint que je la vis : il faut arreter le jar en cours d'execution avant
de mesurer, sinon `maven-clean-plugin` echoue sur `recreo.jar`. Je l'arrete **par PID**, jamais par
nom d'image.*

**Sur la forme de mon audit.** J'ai joue par requetes HTTP, donc j'ai lu le **HTML servi** et non la
page rendue. **Audit degrade sur le seul point visuel**, et rien de ce document n'y repose. Les
conditions detaillees sont en section 0 de
`squad/conformite/PASSE-TRANSVERSE-s30-les-trois-familles-de-portee-depot.md`.

---

## 1. Tableau des verdicts

| Bloc | Reserves | Verdict | Prix |
|---|---|---|---|
| **A** - `cycle-de-vie-cp`, `vivant-non-vivant-cp` | `CYV-R1`, `VIV-R2` | **VALIDE** | 0,25 |
| **B** - `objet-technique-cp` | `OBJ-R1`, `OBJ-R2` | **VALIDE** | 0,50 |
| **C** - `histoire-geographie-cm1` | `HG-R6`, `HG-R7` | **VALIDE SOUS RESERVE** (`HG-R8`) | 0,50 |
| **E** - `fait-opinion-cm1` | `FOP-R1`, `FOP-R2`, `FOP-R3` | **VALIDE SOUS RESERVE** (`FOP-R4`) | 0,50 |
| **F** - `droit-devoir-cm1` | `DRO-R1`, `DRO-R2`, `DRO-R3` | **VALIDE SOUS RESERVE** (`DRO-R4`) | 0,50 |
| **`CONS-1`** (`S30-9`) | dix consignes reecrites | **VALIDE SOUS RESERVE** - **`CONS-1` n'est pas levee** | 0,50 |
| **`REV-3`** (`ADR-0084`) | l'ecran de fin | **VALIDE** - **`REV-3` levee** | 0,25 |
| **Passe transverse** (`S30-11`) | portee depot | document separe, deux reserves neuves | 0,50 |
| | | **Total** | **3,50 / 4,0** |

**Onze reserves levees sur les onze de mon perimetre. Cinq reserves neuves ouvertes, aucune
bloquante. `CONS-1` reste ouverte, tenue sur cinq entrees de dix.**

**Aucune ligne de la matrice ne bouge par mon fait, et je n'y touche pas** : les huit paquets de mon
perimetre sont tous sur des lignes deja `couvert-valide`. Je l'avais chiffre d'avance
(arbitrage prealable, section 3.2) et c'est verifie a l'arrivee.

---

## 2. Bloc A - **VALIDE**

**Joue** : `bandit-manchot-vivant-non-vivant-cp`, partie complete jusqu'a l'ecran de fin, erreurs
volontaires poussees jusqu'a la revelation sur le caillou et sur le sable ;
`bandit-manchot-cycle-de-vie-cp`, partie jouee volontairement mal. **Et j'ai lu les vingt-cinq items
des deux paquets.**

### `CYV-R1` - **LEVEE**

Texte livre : *"Une plante tres vieille perd ses feuilles et ne repousse plus, que fait-elle ?"*.
**C'est mon texte, mot pour mot**, aux accents pres qui appartiennent au `relecteur-pedagogique`.
Bonne reponse, distracteurs et indice inchanges, comme je l'avais exige.

Les deux defauts sont payes : *se dessecher* a disparu, et l'enonce **ne contient plus le mot de la
reponse** - *"ne repousse plus"* decrit une scene sans dire *mourir*. L'item mesure a nouveau ce
qu'il pretend mesurer.

### `VIV-R2` - **LEVEE**

Les trois items de matiere inerte sont livres **mot pour mot** dans la forme que j'avais prescrite :
*"Un caillou est-il un etre vivant ?"* / *"Le sable est-il un etre vivant ?"* / *"La neige est-elle
un etre vivant ?"*, bonne reponse `non`, distracteur `oui`, indices inchanges.

**Verifie a l'ecran, pas au diff** : les deux boutons sont *oui* et *non*, lexicalement disjoints,
discriminables d'un coup d'oeil - ce qui etait tout l'objet de la reserve.

**Et le second motif de `VIV-R2` tombe aussi** : la revelation ne donne plus *"C'est ce n'est pas un
etre vivant. Appuie sur ce n'est pas un etre vivant."*. Elle donne, verifie a l'ecran :

> *Ce n'est pas ca.* / *Appuie sur la bonne reponse :* / *non*

**Ce que j'avais exige de verifier, et que j'ai verifie :**

- **`FER-1` intacte** : l'opposition reste **binaire** et *non* reste du cote non-vivant. Les deux
  poles du libelle sont servis en bonne reponse - *un etre vivant* (4 items), *un objet* (4 items).
  **La fermeture de `QLM-CP-01` n'est pas remise en cause.**
- **`FER-2` intacte** : quatorze items, aucun retire.
- **`FER-3` intacte, et mieux que tenue** : trois taches au lieu de deux - categorisation (8),
  selection (3), verification (3). La condition demande deux taches d'au moins trois items ; elles
  sont trois.
- **Le cout que j'avais annonce est reel et il reste plus petit que le defaut qu'il paie** : le
  paquet porte deux formes de question la ou j'avais loue sa regularite a s27.

**Verdict du bloc A : VALIDE.** Done pedagogique prononce le 2026-09-06. Aucune reserve neuve.

---

## 3. Bloc B - **VALIDE**

**Joue** : `bandit-manchot-objet-technique-cp`, partie jouee volontairement mal, revelations
atteintes. **Et j'ai relu les onze items.**

### `OBJ-R1` - **LEVEE**

Titre servi : **"Les objets de tous les jours"**. **C'est mon texte, mot pour mot.**

**Une correction que je fais a mon propre motif, avant qu'on s'appuie dessus.** J'avais ecrit, en
troisieme argument d'`OBJ-R1`, que *"Fonction d'un objet"* etait *"le seul du catalogue a etre
nominal sans article"*. **C'est faux** : ils sont six. Le detail et la correction sont en section 2.1
de ma passe transverse. **Les deux autres motifs tenaient seuls** - *fonction* n'est pas au programme
de lecture du CP, et le titre promettait plus etroit que le contenu - et le titre livre repare les
deux. **La levee tient ; c'est mon argument accessoire qui etait faux, pas ma conclusion.**

### `OBJ-R2` - **LEVEE**, et le choix de l'equipe **tient**

**Ma barre, ecrite le 2026-09-06** : *"sur les cinq items de fonction, au moins deux doivent porter
un distracteur qui soit une fonction plausible pour l'objet nomme, et non la fonction d'un autre
objet du paquet."*

**Mesure sur les cinq items livres :**

| Item | Distracteurs | Plausible pour l'objet nomme ? |
|---|---|---|
| parapluie | *a couper*, *a ecrire* | non |
| **ciseaux** | ***a coudre***, *a balayer* | **oui** - les ciseaux appartiennent au necessaire de couture, et *coudre* n'est la fonction d'aucun autre objet du paquet |
| stylo | *a couper*, *a balayer* | non |
| balai | *a ecrire*, *a se proteger de la pluie* | non |
| **brosse a dents** | ***a se laver les cheveux***, *a ecrire* | **oui** - une brosse qui nettoie, qui ne differe que par ce qu'elle nettoie |

**Deux sur cinq. Ma barre disait *au moins deux*. Elle est atteinte, exactement au plancher, et je
la tiens telle que je l'ai ecrite.** L'equipe a choisi le minimum que j'autorisais ; c'etait son
droit, et **je ne releve pas une barre parce que je vois aujourd'hui qu'elle aurait pu etre plus
haute**. Une barre qu'on releve apres coup fait de ma porte un caprice.

**Ce que je dis quand meme, et qui n'est pas une reserve** : les trois autres items de fonction
restent gagnables sans connaitre l'objet. La ligne `QLM-CP-07` est fermee et le reste ; si
`camille-po` veut un jour renforcer les trois, c'est un enrichissement, pas une dette.

**Verdict du bloc B : VALIDE.** Done pedagogique prononce le 2026-09-06. Aucune reserve neuve.

---

## 4. Bloc C - **VALIDE SOUS RESERVE**

**Joue** : `bandit-manchot-histoire-geographie-cm1`, **huit parties completes** jusqu'a l'ecran de
fin, en pressant systematiquement la premiere proposition pour declencher la revelation. **Et j'ai
relu les deux items de nature et l'item de la Bastille dans le fichier**, puisqu'une partie de dix
manches sur cinquante-sept items en laisse quarante-sept que l'enfant ne voit pas.

### `HG-R7` - **LEVEE**, et c'etait l'erreur de fait

Texte livre : *"En 1789, des habitants de Paris prennent d'assaut une vieille prison qui servait
aussi a enfermer des opposants au roi. Sa chute est fetee dans toute la ville et, l'annee suivante,
une grande fete reunit la France entiere pour son anniversaire."* **C'est mon texte, mot pour mot.**

**Le produit n'enseigne plus que le 14 juillet est fete nationale depuis 1790.** La phrase decrit la
Fete de la Federation du 14 juillet 1790 sans lui donner un statut qu'elle n'avait pas.

**Et j'ai verifie ce que la correction pouvait casser ailleurs, parce qu'un fait corrige a un endroit
peut en contredire un autre.** L'item *"Quelle est la date precise de la prise de la Bastille ?"*
porte l'indice *"C'est la date que la France celebre chaque annee comme fete nationale."* : **vrai,
et desormais non contredit par le support.** Aucun autre item du paquet ne parle de la fete
nationale. **Coherence retablie sur les deux items.**

### `HG-R6` - **LEVEE**, aux deux conditions que j'avais posees

**Ma barre avait deux moities, et je les reprends une par une.**

1. *"Sur les deux items de nature, remplacer `une carte de France` par une forme de document ecrit
   que l'enfant doive ecarter en lisant."* **Fait sur les deux** : *un poeme* remplace la carte face
   au journal intime, *une recette de cuisine* la remplace face a la declaration de droits. Ce sont
   deux formes de document ecrit, et aucune ne s'ecarte sans lire le support.
2. *"Faire porter au moins un des deux items sur un support ou le mot de la reponse n'apparait pas."*
   **Fait sur l'item de la declaration** : le support ne dit plus *"vote une declaration"* mais
   *"votent un texte solennel qui affirme que tous les hommes naissent et demeurent libres et egaux
   en droits"*. **Le mot qui nomme le genre a disparu**, et l'enfant doit reconnaitre un genre au lieu
   de recopier un mot.

**Ce que je dis pour etre exacte, et qui n'entraine aucune demande** : le mot *droits* reste dans le
support et dans la reponse. Je ne l'exige pas parti - **une declaration de droits qui ne mentionnerait
jamais les droits ne serait plus reconnaissable**, et j'aurais alors demande l'impossible. Et sur
l'item du journal intime, le support commence toujours par *"Cher journal"* : **ma barre disait *au
moins un des deux*, elle est atteinte, et je ne recharge pas l'autre.**

### `HG-R8` - **reserve nouvelle, datee du 2026-09-06, non bloquante**

**Defaut cree par la correction, et c'est exactement ce que je devais chercher en rejouant.** Les
deux indices des items de nature ont ete reecrits en meme temps que les supports, et **tous deux
mettent l'enfant sur le mot plutot que sur le genre** :

| Item | Indice livre | Ce qu'il fait |
|---|---|---|
| journal intime | *"Repense a la facon dont ce texte commence."* | **designe *"Cher journal"***, c'est-a-dire precisement la recopie que `HG-R6` reprochait au support |
| declaration de droits | *"Repense a ce que ce texte affirme au sujet des droits de chaque etre humain."* | **prononce *droits***, moitie de la bonne reponse, quand aucun des deux distracteurs ne contient ce mot |

**C'est `M21`** - un indice qui nomme la reponse est une revelation deguisee. Sur un item a trois
propositions l'indice n'est lu qu'apres une erreur, ce qui borne le degat : c'est pour cela que la
reserve n'est pas bloquante.

> **`HG-R8`. Propriete a tenir** : sur les deux items de nature de `HG-CM1-14`, l'indice porte **ce
> qui caracterise le genre du document** - a qui il s'adresse, dans quel but il est ecrit - et jamais
> le mot que l'enfant a a trouver ni l'endroit du support ou le copier. **Je ne fournis pas les
> textes** : ecrire un indice de genre est le jugement meme qu'il faut faire ici. **Regime : rejeu
> borne, 0,5**, dit d'avance plutot que decouvert en facturant.

**Verdict du bloc C : VALIDE SOUS RESERVE.** Les deux reserves de s29 sont levees ; `HG-R8` est
ouverte. **Aucune ligne d'histoire-geographie ne bouge** - `HG-CM1-06`, `-07` et `-14` restent
`couvert-valide`, avec la mention de couverture partielle deja ecrite sur la ligne 14.

---

## 5. Bloc E - **VALIDE SOUS RESERVE**

**Joue** : `bandit-manchot-fait-opinion-cm1`, partie jouee volontairement mal, revelations atteintes.
**Et j'ai relu les onze items**, en particulier les dix distracteurs de la tache de verification, un
par un, comme je l'avais fait le 2026-09-06 pour ouvrir `FOP-R2`.

### `FOP-R2` - **LEVEE**, sur les deux moities de la reserve

**Premiere moitie - les cinq distracteurs.** Je les avais comptes un par un le 2026-09-06 : cinq
disaient une methode insuffisante, cinq disaient *repeter sans verifier*. **Les cinq sont reecrits, et
je les rejuge un par un :**

| Item | Ce qu'il disait | Ce qu'il dit |
|---|---|---|
| 2 | *"en la repetant a toute la classe parce que ca semble amusant a raconter"* | *"en la croyant parce que plusieurs camarades en parlent aussi, sans savoir ou eux-memes l'ont appris"* - **une corroboration reelle mais sans source**, et c'est le meilleur des cinq |
| 4 | *"en la repetant parce que le score est impressionnant"* | *"en la croyant parce que Malo raconte la meme histoire a chaque recreation"* - **la repetition prise pour une preuve** |
| 6 | *"en la repetant immediatement a tout le monde"* | *"en la croyant parce que le camarade qui la raconte a l'air tres sur de lui"* - **l'assurance prise pour une preuve** |
| 8 | *"en le repetant a tout le monde parce que ca lui semble inquietant"* | *"en le croyant parce qu'un grand de l'ecole l'a dit"* - **l'age pris pour une autorite** |
| 8 | *"en imaginant une explication supplementaire"* | *"en le croyant parce que personne ne l'a dementi"* - **l'absence de dementi prise pour une confirmation** |

**Les cinq sont desormais du meme registre que les cinq que j'avais moi-meme credites** : une raison
de croire, mauvaise, et non l'absence de verification en toutes lettres. **Aucun des dix ne se repere
plus a sa seule deraison.** C'est la barre que j'avais ecrite, jugee contre les exemples que j'avais
moi-meme declares conformes.

**Seconde moitie - la declaration du paquet.** J'avais ecrit : *"la declaration du paquet est corrigee
dans le meme geste ; elle affirme aujourd'hui que les dix le sont, ils sont cinq."* **Fait** : le
commentaire du paquet porte desormais l'amendement, il dit que le chiffre de *huit* etait faux, il dit
qui l'a trouve et comment, et il ne laisse pas la declaration d'origine passer pour vraie. **Une
declaration de conformite fausse dans un fichier commite se lit comme une preuve ; celle-ci ne l'est
plus.**

### `FOP-R3` - **LEVEE**, et le choix de l'equipe **tient**

**Ma reserve laissait deux issues** : soit la question redevient *"comment savoir si c'est vrai"*,
soit la bonne reponse remonte au camarade qui a parle. **`marc-dev` a pris la premiere, et son motif
est ecrit.**

**Le choix tient, et pour une raison plus forte que celle du diff minimal.** L'autre issue aurait
oblige a faire de *"le camarade"* la bonne reponse : le produit aurait enseigne a un enfant de CM1
que, pour verifier une rumeur sur la sante d'une maitresse, on retourne interroger le camarade qui
l'a lancee. **C'est la moins bonne des deux reponses pedagogiques, et l'equipe a eu raison de ne pas
l'ecrire pour satisfaire la lettre de ma reserve.**

**Ce que ce choix coute, et je le chiffre plutot que de le taire** : l'item 8 ne sert plus la moitie
*origine* du libelle `EMC-CM1-11`. **Quatre des cinq items de verification la servent encore** -
*"d'ou vient cette information"*, *"l'origine de cette information"*, *"l'origine de cette rumeur"*,
*"l'origine de ce mot"* - et c'est le compte que j'avais moi-meme declare suffisant le 2026-09-06.
**`FER-1` et `FER-3` restent tenues, verifiees item par item.**

### `FOP-R1` - **LEVEE**, et le choix de l'equipe **tient**

Consigne livree : *"Lis bien, puis dis si c'est un fait ou une opinion, ou comment verifier une
information."*

**Ma reserve laissait deux issues** : nommer les deux taches, ou revenir a une consigne generique
maintenant que chaque enonce nomme la sienne. **La seconde a ete fermee entre-temps** - par
`DEC-0078`, et par `CONS-1` que le sprint payait le meme jour. **`lea-dev` a pris la seule issue
restante, et elle l'a prise correctement.**

**Verifie a l'ecran sur les deux taches** : la consigne annonce le classement (six items) **et** la
verification (cinq items), l'ellipse *"ou [dis] comment verifier"* se lit sans effort au CM1, et
seize mots restent dans les moyens d'un enfant de CM1. **Aucune manche ne porte plus une consigne
qui decrit une tache qu'elle ne demande pas.**

### `FOP-R4` - **reserve nouvelle, datee du 2026-09-06, non bloquante**

**Defaut cree par la correction de `FOP-R2`, et il est la contrepartie exacte de sa reussite.** En
homogeneisant les dix distracteurs, la reprise a rendu leur amorce uniforme.

**Mesure, sur les cinq items de verification :**

| Fait | Compte |
|---|---|
| Distracteurs commencant par *"en (la/le) croyant parce que"* | **8 sur 10** |
| Bonnes reponses commencant ainsi | **0 sur 5** - elles commencent toutes par *en regardant*, *en demandant* ou *en verifiant* |
| Items dont **les deux** distracteurs commencent ainsi | **3 sur 5** (items 2, 4, 8) |

**Consequence** : sur ces trois items, un enfant qui n'a rien compris a la verification de l'origine
d'une information trouve la bonne reponse **en lisant les deux premiers mots de chaque bouton** -
*croire* est faux, *demander* est juste. Ce n'est plus la deraison de `FOP-R2`, c'est un indice de
forme, et il ne mesure pas davantage la competence.

**Ce n'est pas un durcissement de `FOP-R2`, et je tiens a le dire nettement.** `FOP-R2` demandait que
les distracteurs cessent d'etre des non-verifications ; ils l'ont cesse, et elle est levee sans
reserve. **Ce que je vois aujourd'hui, je ne pouvais pas le voir hier** : le motif uniforme n'existe
que depuis la reprise.

> **`FOP-R4`. Propriete a tenir** : sur **au moins deux** des cinq items de verification, l'un des
> deux distracteurs commence par un verbe de verification et decrit une verification qui porte a cote
> - demander a quelqu'un qui n'y etait pas, verifier une chose que personne ne conteste, chercher
> confirmation aupres de qui tient l'information du meme endroit. **L'amorce du bouton cesse alors de
> departager.** **Je ne fournis pas les textes** : le choix d'une verification mal dirigee est le
> jugement qu'il faut faire item par item. **Regime : rejeu borne, 0,5.**

**Verdict du bloc E : VALIDE SOUS RESERVE.** Les trois reserves de s29 sont levees ; `FOP-R4` est
ouverte. **`EMC-CM1-11` reste `couvert-valide`.**

---

## 6. Bloc F - **VALIDE SOUS RESERVE**

**Joue** : `bandit-manchot-droit-devoir-cm1`, partie jouee volontairement mal, revelations atteintes
sur trois manches. **Et j'ai relu les onze items**, indices compris.

### `DRO-R1` - **LEVEE**, et le choix de l'equipe **tient**

**Ma reserve laissait deux issues** : la seconde tache porte sur une situation que le classement ne
traite pas, ou son enonce ne prononce ni *droit* ni *devoir*. **`marc-dev` a pris la seconde**, qui
est celle dont j'avais donne l'exemple mot pour mot.

**Les trois enonces livres :**

| Item | Livre |
|---|---|
| 4 | *"A quoi sert, pour un eleve, le fait d'ecouter le maitre et de respecter les regles de la classe ?"* |
| 6 | *"A quoi sert, pour un enfant, le fait d'etre soigne quand il est malade ?"* |
| 8 | *"A quoi sert, pour un enfant, le fait de jouer et de se reposer ?"* |

**Aucun ne prononce plus *droit* ni *devoir*.** Les trois items de classement associes (1, 3, 9) ne
sont plus resolus par la lecture d'un enonce.

**Le choix tient, et le motif du diff minimal est le bon.** L'autre issue aurait oblige a inventer
trois situations neuves, donc trois faits institutionnels neufs a sourcer sous `HIS-3`, sur une ligne
ou les cinq droits cites sont exactement ceux de la Convention internationale des droits de l'enfant.
**Changer la formulation coutait trois lignes ; changer les situations rouvrait la verification de
source.** Sur une ligne deja fermee, c'est le mauvais echange.

### `DRO-R2` - **LEVEE**, et verifiee au-dela de ce que la reserve demandait

| Item | Indice avant | Indice livre |
|---|---|---|
| 1 | *"Ce droit protege des moments de repos, pas le travail scolaire."* | *"C'est quelque chose que chaque enfant peut reclamer pour lui-meme, sans que personne ne puisse le lui retirer."* |
| 5 | *"Personne n'a le droit de faire du mal a un enfant."* | *"Aucun adulte, ni personne d'autre, n'est autorise a faire du mal a un enfant."* |

**Les deux sont exactement ce que j'avais demande** : l'item 1 porte le critere - *ce qu'on peut
exiger pour soi et que personne ne peut retirer* - au lieu de la categorie ; l'item 5 n'emploie plus
le mot *droit* dans son second sens, et l'ambiguite est levee. **Les items 3, 7, 9 et 11 ont ete
verifies inchanges**, comme je l'avais constate moi-meme.

**Et j'ai mesure l'extension que j'avais posee moi-meme** - *"regle etendue a tout item binaire du
depot"* : **111 items binaires servis, 0 dont l'indice nomme la categorie.** La mesure complete est en
section 4 de ma passe transverse. **La regle est tenue partout, et je ne demande rien a personne sur
cet axe.**

### `DRO-R3` - **LEVEE**

Ma barre : *"une occurrence masculine suffit a l'equilibrer."* Livre : deux. Recompte aujourd'hui sur
les quatre paquets d'EMC - **quatre occurrences d'un adulte de classe, deux masculines
(`cm1-emc-droit-devoir`, items 3 et 4) et deux feminines (`cm1-emc-fait-opinion` item 8,
`cp-emc-regles-de-vie` item 2)**. La serie est equilibree.

### `DRO-R4` - **reserve nouvelle, datee du 2026-09-06, non bloquante**

**Vue a l'ecran avant d'etre verifiee dans le fichier**, sur deux manches de ma partie. **Le defaut
que `DRO-R1` nommait dans l'enonce survit une ligne plus bas, dans l'indice des trois memes items.**

| Indice livre | L'item de classement qu'il resout |
|---|---|
| item 4 : *"**Ce devoir** permet a tout le monde de travailler dans le calme."* | item 3, *"Ecouter le maitre..."* -> *un devoir* |
| item 6 : *"**Ce droit** concerne la sante de l'enfant, pas son ecole."* | item 9, *"Etre soigne quand on est malade..."* -> *un droit* |
| item 8 : *"**Ce droit** protege des moments de repos, pas l'absence de toute regle."* | item 1, *"Jouer et se reposer..."* -> *un droit* |

**Trois sur trois.** Un enfant qui se trompe une fois sur l'item 6 lit *"Ce droit"* et sait, pour tout
le reste de la partie, comment classer *etre soigne*.

**`DRO-R1` est levee et je ne la rouvre pas.** Ma reserve disait *"l'enonce d'un item de seconde tache
ne nomme pas la categorie"*, et **l'enonce est corrige, exactement comme demande**. Ce que j'ouvre est
une **observation neuve et datee**, sur une autre partie de l'item. **Je refuse de faire passer un
durcissement pour une reserve impayee** : le poteau que j'avais plante est atteint.

**La gravite est moindre que celle de `DRO-R1`**, et je le dis pour que la story soit dimensionnee :
l'enonce se lit a chaque tirage de l'item, l'indice seulement apres une erreur. **Mais `DRO-R2` ne
couvre pas ces trois-la** - elle porte sur les items **binaires**, et ces trois items en portent trois
propositions.

> **`DRO-R4`. Propriete a tenir** : sur les trois items de la seconde tache, l'indice ne prononce ni
> *droit* ni *devoir* a propos d'une situation qu'un item de classement demande de classer. Il dit
> **a quoi la chose sert**, ce qui est la tache de l'item. **Regime : mot pour mot une fois les trois
> textes proposes, 0,25** - le geste est de la meme nature que celui qui vient d'etre fait sur les
> enonces.

**Verdict du bloc F : VALIDE SOUS RESERVE.** Les trois reserves de s29 sont levees ; `DRO-R4` est
ouverte. **`EMC-CM1-07` reste `couvert-valide`.**

---

## 7. `CONS-1` - **VALIDE SOUS RESERVE**, et **`CONS-1` n'est pas levee**

**Ma barre, ecrite le 2026-09-06 dans la passe transverse de s29** : *"que chaque entree de catalogue
porte une consigne qui **nomme la tache de son jeu**, comme le font 44 des 54 entrees. Je ne dicte pas
dix consignes d'un coup - **une consigne se juge contre le paquet qu'elle introduit, et je les
rejouerai**."*

**Je les ai rejugees, une par une, contre le paquet qu'elle introduit. C'est le test que j'avais
annonce, et c'est celui que j'applique.**

### 7.1 Ce qui est acquis, et il ne faut pas le minimiser

**La consigne generique a disparu du catalogue.** Mesure refaite par moi sur
`adapters/adapter-content/src/main/resources/catalogue.yaml` : *"Lis bien la question, puis choisis la
bonne reponse."* apparait **0 fois** sur 58 entrees. Elle en occupait dix il y a un jour, cinq il y a
deux sprints. **Concorde avec `theo-archi` et `yanis-qa`.** Ce travail etait du et il est fait.

### 7.2 Les dix, jugees une par une

| Entree | Consigne livree | Verdict |
|---|---|---|
| `etats-eau-cp` | *"Lis bien, puis dis si l'eau est solide ou liquide, ou nomme le changement d'etat."* | **tient** - les deux taches du paquet (12 items de classement, 2 de nommage) |
| `cycle-de-vie-cp` | *"Lis bien, puis dis ce que fait l'etre vivant, ou reponds par oui ou par non."* | **tient** - les deux taches (5 items, 6 items) |
| `objet-technique-cp` | *"Lis bien, puis dis a quoi sert l'objet, ou nomme sa partie."* | **tient** - les deux taches (5, 6), et c'est la meilleure des dix |
| `regles-de-vie-cp` | *"Lis bien, puis dis ce qu'il faut faire, ou pourquoi."* | **tient** - les deux taches (6, 5) |
| `permis-interdit-cp` | *"Lis bien, puis dis ce que fait la personne, ou pourquoi c'est interdit."* | **tient** - les deux taches (7, 4) |
| `vivant-non-vivant-cp` | *"Lis bien, puis dis si c'est un etre vivant."* | **ne tient pas** - section 7.3 |
| `corps-sens-cp` | *"Lis bien, puis nomme la partie du corps ou le sens."* | **ne tient pas** - section 7.3 |
| `histoire-geographie-cm1` | *"Lis bien, puis reponds a la question d'histoire ou de geographie."* | **ne tient pas** - section 7.3 |
| `anglais-cm1` | *"Traduis le mot, ou reponds a la question de culture anglaise."* | **ne tient pas** - section 7.3 |
| `droit-devoir-cm1` | *"Lis bien, puis dis si c'est un droit ou un devoir."* | **ne tient pas** - section 7.3 |

**Cinq sur dix.** Ce n'est pas une impression : c'est le meme test applique dix fois.

### 7.3 Les cinq qui ne tiennent pas, avec le motif de chacune

**a. `corps-sens-cp` - la consigne nomme une tache que le paquet ne porte pas.**

*"Lis bien, puis nomme la partie du corps **ou le sens**."* **Les quatorze items de
`cp-qlm-corps-sens` ont tous une partie du corps en bonne reponse** - oreilles, yeux, nez, langue,
mains, jambes, main, tete, bras, pieds, dos, ventre, genou, peau. **Aucun n'a un sens en bonne
reponse**, y compris les cinq items qui partent d'un sens (*"Avec quoi entend-on ?"* -> *les
oreilles*).

La consigne annonce donc un espace de reponse qui n'existe nulle part dans le paquet. **Et *le sens*,
employe comme nom de categorie, n'est pas au programme de lecture du CP** : l'enfant qui le decode ne
sait pas ce qu'on lui demande de nommer. **Deux defauts sur la meme moitie de phrase.**

**b. `histoire-geographie-cm1` - la consigne generique avec le nom de la matiere.**

*"Lis bien, puis reponds a la question d'histoire ou de geographie."* **Elle ne nomme aucune tache** :
*reponds a la question* est ce que la forme de l'ecran dit deja, et *d'histoire ou de geographie* est
le titre du jeu (*Histoire et geographie*), que l'enfant vient de lire pour arriver la. **C'est la
consigne generique dans un autre costume**, et c'est precisement ce que `CONS-1` existe pour
supprimer.

**Je reconnais que c'est le paquet le plus difficile des dix** - cinquante-sept items, onze lignes de
referentiel, plusieurs taches. **Ce n'est pas une raison de ne rien nommer** : les taches reelles sont
identifiables (situer dans le temps, associer un roi a son fait, lire un document) et une consigne
peut en nommer deux.

**c. `anglais-cm1` - une inexactitude de fait dans un texte que l'enfant lit.**

*"Traduis le mot, ou reponds a la question de **culture anglaise**."*

**Mesure sur les cinq items de culture de `cm1-anglais`** : deux portent sur le Royaume-Uni (Londres,
Big Ben), **trois portent sur les Etats-Unis** - la capitale, le drapeau, Thanksgiving. **Appeler
*culture anglaise* la capitale des Etats-Unis et Thanksgiving est faux**, et le referentiel du produit
dit lui-meme autre chose : `LVE-CM1-07` porte sur *"quelques aspects culturels des **pays** de la
langue etudiee"*, au pluriel.

**Le paquet a raison et la consigne a tort** : servir les Etats-Unis a cote du Royaume-Uni est
exactement ce que la ligne demande. **C'est la consigne neuve qui reduit a l'Angleterre ce que le
paquet ouvre a plusieurs pays**, et elle le fait sur un enfant de CM1, c'est-a-dire a l'age precis ou
l'on apprend que l'anglais n'est pas la langue d'un seul pays.

*Pourquoi ce n'est pas un refus, alors qu'une erreur de fait est chez moi un motif de refus a elle
seule* : aucune reponse attendue ne depend de cette phrase, exactement comme pour `HG-R7` que j'ai
traite en reserve non bloquante le 2026-09-06. **Je m'applique mon propre precedent plutot que de
durcir parce que c'est le meme sprint.**

**d. `droit-devoir-cm1` - la consigne decrit une tache que cinq items sur onze ne demandent pas.**

*"Lis bien, puis dis si c'est un droit ou un devoir."* **Vu a l'ecran sur trois manches de ma
partie**, au-dessus d'items dont la question est *"A quoi sert, pour un eleve, le fait d'ecouter le
maitre... ?"*.

**C'est mot pour mot la propriete de `FOP-R1`**, que j'ai ecrite le 2026-09-06 : *"la consigne d'un
jeu ne decrit pas une tache que la moitie de ses items ne demandent pas."* **Et elle a ete payee sur
le paquet jumeau le meme jour** (commit `dbbfe12`, `fait-opinion-cm1`), sans l'etre ici. **Les deux
paquets d'EMC CM1 ont ete livres ensemble, ils portent la meme structure a deux taches, et ils
ressortent du sprint avec deux traitements differents.**

**e. `vivant-non-vivant-cp` - la consigne nomme deux taches sur trois, et amorce la mauvaise.**

*"Lis bien, puis dis si c'est un etre vivant."* Le paquet en porte **trois** depuis le bloc A de ce
meme sprint : categorisation (8 items), verification (3), **selection** (3 - *"Parmi ces trois choses,
laquelle est un etre vivant : le chien, la chaise, le caillou ?"*).

Deux consequences, et la seconde est celle qui me fait trancher :

1. **La tache de selection n'est pas introduite** - trois items sur quatorze.
2. **La consigne amorce une reponse par oui ou par non**, alors que **onze items sur quatorze ne se
   repondent pas ainsi** : ils se repondent par *un etre vivant*, *un objet* ou par le nom d'une chose.

**Et je dois dire ce que cela me coute a reconnaitre** : cette troisieme tache, c'est **moi** qui l'ai
creee, en prescrivant `VIV-R2` mot pour mot. Les deux stories ont ete livrees le meme jour par la
meme developpeuse, et **la consigne a ete ecrite contre le paquet d'avant**. Ce n'est pas une faute de
`lea-dev` seule : c'est une couture, et les coutures sont ce que j'ai annonce comme premier mode de
defaillance du sprint.

### 7.4 `CONS-2` - reserve nouvelle, datee du 2026-09-06, non bloquante

**Ce que la reecriture a coute aux deux consignes de CP les plus longues, mesure et non estime.**

| Entree | Mots | Etat |
|---|---|---|
| `cycle-de-vie-cp` | **16** | la plus longue consigne de CP que l'enfant lit lui-meme |
| `etats-eau-cp` | **15** | la deuxieme |

Les quatre consignes de CP plus longues ou aussi longues appartiennent toutes a des **paquets a
voix**, ou l'adulte lit (*"Ecoute l'adulte lire le mot..."*), et la premisse du produit les protege.
**Ces deux-la, l'enfant les decode seul, sur chacune des dix manches, avant l'enonce.** Elles en
faisaient neuf avant `CONS-1`.

**Ce n'est pas un durcissement de `CONS-1`** : les deux **tiennent** son critere, elles nomment leurs
deux taches, et je les ai comptees comme telles en 7.2. C'est un cout que la reecriture a paye et
qu'il faut nommer pour qu'il ne s'installe pas. *"Nomme la tache"* et *"reste lisible a six ans"* ne
sont pas en contradiction : *"Lis bien, puis dis a quoi sert l'objet, ou nomme sa partie."* fait douze
mots et nomme ses deux taches.

> **`CONS-2`. Propriete a tenir** : une consigne de catalogue de CP que l'enfant lit lui-meme nomme
> ses taches **sans depasser douze mots**, chiffre pris sur les consignes de CP qui y parviennent
> deja. **Regime : rejeu borne sur deux entrees, 0,25.**

### 7.5 Le verdict

> **`S30-9` : Done pedagogique VALIDE SOUS RESERVE**, prononce le 2026-09-06. L'increment est
> utilisable et il est meilleur que ce qu'il remplace : la consigne generique a disparu, et cinq
> entrees sur dix portent desormais une vraie consigne. **Aucune ligne de matrice ne bouge.**
>
> **`CONS-1` n'est pas levee.** Elle reste ouverte sur **cinq entrees**, nommees en 7.3 :
> `corps-sens-cp`, `histoire-geographie-cm1`, `anglais-cm1`, `droit-devoir-cm1`,
> `vivant-non-vivant-cp`. **Regime pour le reliquat : rejeu borne, 0,5** - je rejugerai chacune contre
> son paquet, comme celles-ci.
>
> **`ANG-R2`, reserve nouvelle et datee du 2026-09-06, non bloquante** : la consigne d'anglais dit
> *culture anglaise* la ou trois des cinq items de culture portent sur les Etats-Unis. **Elle est
> extraite de `CONS-1` et porte son propre code parce qu'elle n'est pas de la meme nature** : les
> quatre autres sont des defauts de designation de tache, celle-ci est une inexactitude de fait, et
> `camille-po` doit pouvoir la prioriser seule.

---

## 8. `REV-3` - **VALIDE**, la reserve est **LEVEE**

**Joue sur les deux mecaniques**, ce qui etait la condition de ma reverification de `REV-1` :

| Ecran de fin lu | Jeu |
|---|---|
| *"Le sable est-il un etre vivant ? / La bonne reponse : / non"* | `vivant-non-vivant-cp` |
| *"4 + ? = 10 / La bonne reponse : / 6"* | `saisie-complements-10-cp` |
| *"Ecris le mot que tu entends. / La bonne reponse : / jupe"* | `saisie-mot-dicte-cp` |
| *"En 1792, les rois cessent de gouverner la France... / La bonne reponse : / la fin de la monarchie"* | `histoire-geographie-cm1` |
| *"D'apres ce texte, que peut-on dire de la vie quotidienne de Louis XIV a Versailles ? / La bonne reponse : / elle suit des regles tres strictes et se deroule en public"* | `histoire-geographie-cm1` |

**Le dernier est celui qui compte le plus.** C'est **l'item exact** qui produisait, a s29,
*"C'est elle suit des regles tres strictes et se deroule en public."* - la seule occurrence
agrammaticale d'`histoire-geographie-cm1`. **Il se lit correctement.**

**Cinq formes de bonne reponse verifiees a l'ecran** : adverbe (*non*), nombre (*6*), nom (*jupe*),
groupe nominal (*la fin de la monarchie*), **proposition entiere** (*elle suit des regles...*). C'est
la population que `REV-1` couvrait.

**Les deux criteres de la reserve sont tenus** : le verdict porte **l'enonce de la manche qu'il
ferme**, et la reponse occupe **son propre element**, seule et entiere, a cote de la question.
**L'ecran de fin ne dement plus le dernier geste de l'enfant et ne le laisse plus commenter une
question invisible.**

**Le prix reste 0,25, et je dis pourquoi.** J'avais nomme d'avance la condition qui l'aurait double :
*"si le correctif fait apparaitre une liste de plusieurs reponses revelees en fin de partie, ce n'est
plus un rejeu mais un ecran neuf, et le prix est 0,5."* **Elle ne s'est pas realisee** : l'ecran porte
une question et une reponse, celles de la derniere manche. **Ce que j'avais annonce comme un
depassement possible n'a pas lieu**, et je facture le prix bas.

**Un arbitrage que `theo-archi` m'a explicitement laisse, et que je rends ici.** Le commentaire
d'`ADR-0084` note qu'un intitule (*"La question :"*) est un texte lu par un enfant, donc ma decision.
**Je le refuse, et le motif est le CP.** L'ecran porterait alors trois etiquettes a la suite -
*Tu n'as pas trouve.*, *La question :*, *La bonne reponse :* - pour deux valeurs. **La phrase se
termine deja par un point d'interrogation, qui dit a l'enfant que c'est une question ; une etiquette
de plus lui coute une ligne de lecture et ne lui apprend rien.**

**Une observation, qui n'est pas une reserve.** Sur `saisie-mot-dicte-cp`, l'enonce est invariant
(*"Ecris le mot que tu entends."*) : l'ecran de fin y ajoute donc une phrase qui n'identifie pas la
manche. **Ce n'est pas un defaut du correctif** - il rend fidelement l'enonce, qui est ce que j'avais
demande - **c'est une propriete de ce paquet-la**, ou le mot a ecrire n'a jamais ete visible puisque
l'adulte le prononce. La reponse, elle, est utile et elle est la.

---

## 9. Etat des reserves de mon perimetre au 2026-09-06

| Reserve | Etat |
|---|---|
| `CYV-R1` | **levee** - verbatim livre |
| `VIV-R2` | **levee** - verbatim livre, `QLM-CP-01` non remise en cause |
| `OBJ-R1` | **levee** - verbatim livre ; un de mes trois motifs etait faux et je le corrige |
| `OBJ-R2` | **levee** - deux items sur cinq, exactement ma barre |
| `HG-R6` | **levee** - les deux moities de ma barre |
| `HG-R7` | **levee** - l'erreur de fait est corrigee |
| `FOP-R1` | **levee** - choix de l'equipe, il tient |
| `FOP-R2` | **levee** - cinq distracteurs et la declaration du paquet |
| `FOP-R3` | **levee** - choix de l'equipe, il tient et il est le meilleur des deux |
| `DRO-R1` | **levee** - choix de l'equipe, il tient |
| `DRO-R2` | **levee**, et l'extension au depot mesuree : 111 items sur 111 |
| `DRO-R3` | **levee** |
| `REV-3` | **levee** - joue sur les deux mecaniques, cinq formes de reponse |
| **`CONS-1`** | **ouverte** - tenue sur 5 entrees de 10 ; reliquat nomme en 7.3 |
| `HG-R8` | **ouverte** le 2026-09-06 - les indices des deux items de nature |
| `FOP-R4` | **ouverte** le 2026-09-06 - l'amorce uniforme des huit distracteurs |
| `DRO-R4` | **ouverte** le 2026-09-06 - la categorie survit dans trois indices |
| `CONS-2` | **ouverte** le 2026-09-06 - deux consignes de CP a seize et quinze mots |
| `ANG-R2` | **ouverte** le 2026-09-06 - *culture anglaise* pour trois items sur les Etats-Unis |
| `SAI-R1`, `ERR-R1` | **ouvertes** le 2026-09-06 par ma passe transverse |
| `PER-R1`, `REG-R1` | **inchangees** - blocs G et H differes a s31, hors de ce sprint |

**Aucun verdict anterieur n'est efface.** Les refus du 2026-09-05 sur `fait-opinion-cm1` et
`droit-devoir-cm1` restent lisibles dans leurs fiches, avec leurs motifs : ils sont **leves, pas
retires**.

---

## 10. Mon cout reel, poste par poste

| Poste | Planifie | Consomme | Ecart |
|---|---|---|---|
| Bloc A - verbatim disperse | 0,25 | **0,25** | - |
| Bloc B - `objet-technique` | 0,50 | **0,50** | - |
| Bloc C - `histoire-geographie` | 0,50 | **0,50** | - |
| Bloc E - `fait-opinion` | 0,50 | **0,50** | - |
| Bloc F - `droit-devoir` | 0,50 | **0,50** | - |
| **Sous-total reprises** | **2,25** | **2,25** | **-** |
| `CONS-1` | 0,50 | **0,50** | - |
| `REV-3` | 0,25 | **0,25** | - |
| Passe transverse `S30-11` | 0,50 | **0,50** | - |
| **Total** | **3,50** | **3,50** | **-** |
| **Marge de rejeu** | 0,50 | **0,00** | **non consommee** |
| **Budget opposable (`PAR-9`)** | **4,00** | **3,50** | |

**Aucun depassement, aucun poste sous-estime, et la marge n'a pas servi.** C'est le premier sprint
depuis s28 ou mon cout tombe exactement sur mon plan. **Le motif n'est pas ma vertu : c'est que
`PAR-10` a fait ce pour quoi je l'avais ecrite** - quinze reserves reparties sur huit paquets ont
coute huit rejeux et non quinze.

**Deux travaux a cout nul, que je consigne pour qu'ils ne soient pas comptes ailleurs** : les deux
corrections de chiffres que le `relecteur-pedagogique` m'a routees (*des six* -> *des dix* dans le
referentiel de sciences ; *cinq dessins* -> *six* dans mon arbitrage de s29) sont faites dans ce
commit. **Ce sont mes propres comptages qui avaient derive**, et les corriger n'entame pas mon
budget.

**Ce que la marge non consommee ne signifie pas.** Elle ne dit pas que 4,0 etait trop : elle dit que
**aucun des cinq blocs n'a du etre rejoue deux fois**, ce qui n'etait pas acquis - a s29, quatre
paquets sur cinq avaient ete refuses au premier passage. **Une marge dimensionnee sur le sprint
precedent et non consommee est une marge bien dimensionnee, pas une marge inutile.**

---

## 11. Ce dont je reste la seule garde, sur ce perimetre

Dit explicitement, sinon l'equipe croira couvert ce qui ne l'est pas.

| Propriete | Garde executable ? |
|---|---|
| **La contrefacon par tirage** - qu'un item se resolve grace a un autre item du meme paquet | **aucun**, et c'est la famille qui a produit **deux** des cinq reserves neuves de ce sprint (`DRO-R4`, et l'origine de `DRO-R1`). **Mordable en principe** : c'est une recherche de chaine. Route |
| **La plausibilite d'un distracteur** (`OBJ-R2`, `FOP-R2`, `FOP-R4`, `HG-R6`) | **aucun.** Quatrieme sprint consecutif ou c'est la propriete qui produit le plus de reserves |
| **Qu'une consigne nomme la tache de son paquet** (`CONS-1`) | **aucun.** Un test peut compter les occurrences d'une chaine interdite - il ne peut pas savoir qu'une consigne decrit ce que le paquet demande. `DEC-0078` ne change rien a cela |
| **L'exactitude d'un fait** (`HG-R7`, `ANG-R2`) | **aucun.** Aucun test du depot ne sait qu'un enonce scolaire est vrai |
| **`M21`** - qu'un indice ne nomme pas la reponse (`HG-R8`) | **aucun** |
| **`DRO-R2` etendue** - qu'un indice d'item binaire porte le critere | **aucun**, mais **mesuree aujourd'hui sur toute sa population** : 111 sur 111. C'est le seul axe du sprint ou je peux dire *tenu partout* |
| **La lisibilite d'une consigne au niveau vise** (`CONS-2`) | **aucun.** Un comptage de mots serait un proxy, pas un garde-fou |
| **La couture entre les deux instances** | **aucun, et elle a produit un defaut ce sprint** - la consigne de `vivant-non-vivant-cp` ecrite contre le paquet d'avant, section 7.3.e. **C'etait mon premier mode de defaillance annonce, et il s'est realise pour la seconde fois** |

**Et ce que la parallelisation ne change toujours pas** : deux instances sur des lots disjoints, ce
sont deux paires d'yeux, **pas une verification croisee**. Je n'ai relu aucun travail de l'instance B
et elle n'a relu aucun des miens.

---

## 12. Ce que je route

| Fait | A qui | Ce qu'il en fait |
|---|---|---|
| **`CONS-1` reste ouverte sur cinq entrees**, nommees et motivees en 7.3 | `camille-po` via `sofia-sm` | Cinq corrections de catalogue ; celle de `droit-devoir-cm1` est la moins chere - la propriete est deja ecrite et deja payee sur le paquet jumeau |
| **`ANG-R2`** - *culture anglaise* pour un contenu qui porte trois items sur les Etats-Unis | `camille-po` | Une inexactitude de fait, a prioriser separement des quatre autres |
| **`HG-R8`, `FOP-R4`, `DRO-R4`, `CONS-2`** | `camille-po` | Quatre stories, regimes chiffres dans chaque section |
| **`SAI-R1`, `ERR-R1`** | `theo-archi` et `camille-po` | Passe transverse, sections 1.3 et 2.3 |
| **La contrefacon par tirage est mordable en principe** et a produit deux reserves ce sprint | `theo-archi` | Je dis la propriete, jamais la forme |
| **Le registre d'identifiants de `PAR-5`** n'existe toujours pas ; aucune collision mesuree, mais mesuree a la main pour la deuxieme fois | `theo-archi` | **Deuxieme sprint consecutif ou je le route**, et c'est l'une des deux conditions qui bloquent la levee de ma retenue de 1,0 |
| **La couture s'est de nouveau rompue** : la consigne de `vivant-non-vivant-cp` a ete ecrite contre l'etat d'avant du paquet, alors que les deux stories sont du meme jour et de la meme developpeuse | `sofia-sm` | Fait de retrospective. **Ce n'est pas une faute individuelle** : c'est ce que produit un sprint qui modifie un paquet et son entree de catalogue dans deux stories separees |
| **Mon propre motif errone dans `OBJ-R1`**, corrige | pour information | Aucun verdict ne bouge |

---

## 13. Ce que ce document ne fait pas

- **Il ne touche pas a `squad/programme/MATRICE-couverture.md`.** Aucune ligne ne bouge par mon fait
  de toute facon : les huit paquets de mon perimetre sont deja `couvert-valide`.
- **Il n'ecrit aucune correction de contenu.** Je prescris, l'equipe applique. Les seuls fichiers de
  contenu que j'ai ouverts, je les ai **lus**.
- **Il ne se prononce sur rien du perimetre de l'instance B** - les quatre lots de sciences et
  technologie CM1 et `POS-R2`.
- **Il n'ecrit pas le backlog et ne repriorise pas.** Cinq reserves neuves, cinq motifs actionnables ;
  `camille-po` en fait des stories.
- **Il ne corrige ni l'orthographe, ni l'accentuation, ni la ponctuation** de quoi que ce soit :
  ce controle appartient au `relecteur-pedagogique` (`DEC-0002` amende le 2026-08-04 ; `DEC-0056`),
  y compris sur le texte destine a l'enfant.
