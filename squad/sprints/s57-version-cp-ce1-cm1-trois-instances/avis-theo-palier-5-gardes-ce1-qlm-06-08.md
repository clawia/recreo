# s57 - palier 5 : avis de theo-archi sur la forme des gardes de S57-BR-QLM-CE1-06 et -08

- **Auteur** : `theo-archi`. Date : 2026-10-26 17:35 (UTC+01:00), lue par `date`.
- **Arbre** : worktree `s57-p5-theo-g0608`, base `round-two` a `eaac43a6` (`git rev-parse`). Ni `ce1-qlm-aimant` ni `ce1-qlm-dangers-electricite` n'existent a cette base.
- **Demande** : `arbitrage-nadia-premiers-jets-ce1-qlm-06-08.md`, sections 1.5 (`T06-1`), 1.6, 1.7 (dix gardes), 2.6, 2.7 (huit gardes) ; `T06-2` et `T08-1` a `T08-4` de l'annotation septieme du 2026-10-26 (`docs/backlog/backlog.md`, sections 5 et 6).
- **Lu** : l'arbitrage de `nadia-prof` en entier ; `avis-theo-palier-5-exemption-aim-ce1-2.md` ; `reponse-nadia-aim-ce1-2-q7.md` ; mes avis `avis-theo-palier-5-garde-fer-3-ce1-qlm-02.md` et `avis-theo-palier-5-garde-rotation-ce1-qlm-01.md` ; `ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest.java`, `PaquetLu.java`, `PaquetCe1QlmCeQueMangentLesAnimauxTest.java`, `PaquetCe1QlmBesoinsDeLaPlanteTest.java` en entier ; `PaquetCe1EmcInteretCommunTest.java` (`verifierLesChampsServis`) ; `QuestionContentSourceFactory.java` (lecture des cles d'un item) ; les stories `S57-BR-QLM-CE1-06` et `-08`, les reperes `QC1` a `QC9` et l'annotation septieme.
- **Ce que ce fichier ne fait pas** : il n'ecrit ni test, ni jeu fautif, ni contenu ; il ne cree aucun identifiant de condition, ne prononce aucun Done et ne tranche rien de pedagogique. Les reperes `g1`, `S1`, `Q1`... sont locaux a ce fichier. Les regles de `nadia-prof` font foi ; ou ma forme s'en ecarte ou les precise, la section 5 le dit.
- **Pas d'`ADR`, pas de regle ArchUnit** : l'objet garde est du texte YAML, pas du bytecode, et aucune dependance entre classes n'est touchee. Forme d'`ADR-0013` (jeu fautif commite) et des precedents `-01` a `-03`.

## 0. Verdict

| Code | Question | Reponse |
|---|---|---|
| `V1` | Lieu | **Le test de chaque paquet**, `PaquetCe1QlmAimantTest` et `PaquetCe1QlmDangersElectriciteTest` (noms a suivre de ceux que `marc-dev` donne aux paquets). Aucune garde dans le test transverse : *pile* est un mot admis de `-07` (`QMT-CE1-1`), *pièce*, *couvert*, *clé* sont des mots ordinaires ailleurs (section 4) ; dans le test transverse, ces gardes mordraient par inclusion. Chaque test ne lit que son `FICHIER_CONTENU` et son jeu fautif |
| `V2` | Executable | **Dix gardes sur dix pour `-06`, huit sur huit pour `-08`**, chacune dans sa partie lexicale ou calculable. Ce qu'elles ne lisent pas est en section 5 |
| `V3` | `T06-1` | **Pas de copie** des deux verifications de presence. Le test transverse les refuse deja sur `ce1-qlm-aimant` ; le test du paquet porte un **ensemble ferme de cles**, qui sert le perimetre de ses propres gardes et refuse les deux champs par consequence (1.4) |
| `V4` | Mesure | Motifs et sondes executes hors depot, JDK 21.0.10 (`java.version` imprime par le programme) : **toutes les sondes de `nadia-prof` rendent le verdict attendu** une fois corrige un ecart de ma premiere forme (`S1`, section 5) ; sur 190 fichiers et 15 901 champs reels, aucun des sept motifs mesures sur ce point ne mord sur un mot qui ne fait que porter ses lettres (section 4) |
| `V5` | Ecarts avec `nadia-prof` | Aucun refus contraire a ses regles. Six precisions a confirmer ou retirer (`Q1` a `Q6`, section 5) |

## 1. Ce qui est commun aux deux tests

### 1.1 La normalisation, une fois

`plat(t)` : `PaquetLu.sansAccent(t)` (minuscule, sans accent, `œ` en `oe`), puis apostrophe `U+2019` en apostrophe droite, puis toute suite de blancs, `U+00A0` et `U+202F` compris, ramenee a une espace, puis `strip`. Pour une **egalite** (objet, groupe, geste), on retire en plus un `.` ou un `!` final. Les motifs s'ecrivent sans accent, drapeaux `CASE_INSENSITIVE | UNICODE_CASE | UNICODE_CHARACTER_CLASS`, bornes de mot `(?<!\p{L})` et `(?!\p{L})` : c'est ce qui laisse passer *couvercle*, *baguette*, *orange*, *encore*, *empiler*. **Une seule exception** : la forme *blessé* de `-08` g7 se lit sur le texte en minuscule NFC **avec ses accents**, parce que *blesse* (present, generique) et *blessé* (participe, une blessure) ne different que par l'accent (2.7).

Chaque test porte son `plat`, trois lignes, comme `-02` porte son `normalise` : je ne propose pas d'extraction.

### 1.2 Les champs lus

Les gardes lexicales lisent `Item.champsLus()` : enonce, indice, bonne reponse, distracteurs. **Jamais les commentaires** : l'en-tete de `-08` ecrira *"aucune pile"*, celui de `-06` nommera les objets exclus, et une garde qui lirait les commentaires refuserait le paquet sur sa propre documentation.

### 1.3 L'ensemble ferme de cles

`PaquetLu.Item` n'expose pas `explication`, que `QuestionContentSourceFactory` sert pourtant ; une explication echapperait donc a toutes les gardes lexicales des deux lots, alors que `AIM-CE1-2` et la regle 5 de `-06` la nomment. Je ne choisis pas d'enumerer les champs a lire : **un perimetre enumere echoue par omission**. Je ferme l'autre cote. Chaque test du paquet asserte, pour chaque item :

```java
assertThat(item.cles()).as("TRV-CE1-1 : '%s' ne porte que les champs que ce test lit", item.enonce())
        .containsExactlyInAnyOrder("enonce", "bonneReponse", "distracteurs", "indice");
```

C'est la forme de `PaquetCe1EmcInteretCommunTest.verifierLesChampsServis`, et c'est exactement ce que portent les trois paquets `ce1-qlm-*` deja ecrits (mesure, section 4). Elle refuse aussi `partDesignee`, `illustration`, `illustrationTexteAlternatif`, `tableauSupport`, que la story exclut deja (*"sans texte support"*, *"aucune illustration"*, criteres communs). **Si `marc-dev` veut une explication**, il ajoute dans le meme commit le composant `explication` a `PaquetLu.Item` et la cle a cet ensemble ; les gardes la lisent alors. Ce n'est pas une condition neuve : c'est le perimetre du test, ferme pour que rien ne passe a cote.

Sonde : **un seul** item fautif, qui porte une cle `explication` (le champ pour lequel l'ensemble existe), refuse avec *"ne porte que les champs"*. Elle tue le mutant `containsAll` a la place de `containsExactlyInAnyOrder`.

### 1.4 `T06-1`

**Reponse : pas de copie.** `nadia-prof` exige que la presence de `texteSupport` et de `texteAVoixHaute` soit refusee **sur ce paquet**, et laisse le lieu (1.5 de son arbitrage). Le test transverse le fait deja : sa population est derivee des descripteurs (`paquetsDuLot`), et `ce1-qlm-aimant` y entre parce que son descripteur declare *Questionner le monde* et *CE1*, ce que le test du paquet asserte lui-meme (`adr0081...`, precedent `-02`, `-03`) ; ses deux sondes existent (`champs-interdits.yaml`, `leJeuFautifCommiteEstRefuseSurUnTexteSupportEtSurUnTexteAVoixHaute`). Recopier ces deux verifications serait une seconde copie de la meme assertion, la dette 3 de `main` appliquee aux tests.

L'ensemble ferme de 1.3 les refuse **en plus**, localement, comme consequence d'une garde qui a son propre motif. Sa reponse de `Q7` tient donc deux fois. Ce qui la ferait tomber : un descripteur qui sortirait le paquet de la population transverse, et le test `adr0081` du paquet le refuse.

### 1.5 Jeux fautifs et sondes

- **Jeu fautif** : `adapters/adapter-content/src/test/resources/contreexemple/<classe>/contenu/<fichier>.yaml`, chaque sonde reperee par un commentaire `sonde NN` au-dessus de l'item, lue par `item.commentaires().contains("sonde NN")` (precedent `-02`, `-03`), avec `assertThat(sonde(...)).hasSize(1)` avant chaque refus, pour qu'une sonde introuvable ne passe pas en silence.
- **Sondes de paquet** (comptes, quart, `FER-1`, `FER-3`, en-tete) : construites **sur le paquet reel** par filtre ou mutation de l'en-tete, en-tete inchange quand le compte est en jeu (precedent `S1`, `S2`, `S5` de `-02`), ou par **repetition d'items fautifs** quand le paquet reel ne garantit pas les nombres (le quart, 2.4).
- **Litteraux accentues**, apostrophes droites et typographiques melangees dans le jeu fautif comme dans le contenu reel ; espace insecable avant `?` (TYP-1).

## 2. Lot `-06` (`ce1-qlm-aimant`), dix gardes

### 2.0 Ce que le paquet porte pour que les gardes mordent

1. **Deux formes d'enonce, fermees**, lues sur `plat(enonce)` comme `FORME_VOISINE` et `FORME_ORDRE` a `-01` (tout enonce hors de ces formes est refuse : g6) :

```
PREMISSE = (ces trois objets sont en metal|tous ces objets sont en metal)\.
FORME_A  = ^(?:PREMISSE )?lequel(?: de ces objets)? l'aimant (attire-t-il|n'attire-t-il pas) ?\?$
FORME_B  = ^tous ces objets sont en metal\. dans quel groupe l'aimant attire-t-il les trois objets ?\?$
```

   `FORME_A` rend la tache `objet` et la **polarite** (groupe 2), `FORME_B` la tache `groupe`. La premisse est la phrase sans *attirer* de la regle 1. Ces formes sont celles que la regle 2 ecrit entre guillemets ; *"de ces objets"* est facultatif parce que la sonde de la garde 4 de `nadia-prof` l'omet (`S1`, section 5).
2. **La liste fermee de la regle 6, dans le test**, nom normalise sans article vers classe : `FER` (clou, trombone, epingle, aiguille, agrafe, fil de fer), `METAL` (papier d'aluminium, barquette en aluminium, fil de cuivre), `SANS_METAL` (gomme, bouchon en liege, regle en plastique, bille en verre, feuille de papier, elastique, pate a modeler). Seize objets. La matiere d'un objet, pour g8 : `fer` pour `FER`, `aluminium` ou `cuivre` pour `METAL`, aucune pour `SANS_METAL`.
3. **Dans l'en-tete du paquet** : une ligne par objet employe, `#   classe <nom> : <attire|metal non attire|sans metal> ; source : <source>`, et deux lignes de compte `#   objet : <...> (N items)` et `#   groupe : <...> (N items)`.
4. **Une proposition de A** est un article de tete (`le `, `la `, `l'`) suivi d'un nom de la liste ; **une proposition de B** est trois noms de la liste, sans article, separes par des virgules (`split("\\s*,\\s*")`). Aucune declaration par item : la tache se lit sur l'enonce, comme a `-01`.

Les gardes tournent dans cet ordre : g6 (forme d'enonce), g1 (objets), puis les autres, qui supposent chaque objet classe.

### 2.1 Les gardes

| Garde | Forme | Doit refuser (contre-exemple) | Doit passer | Ne garde pas |
|---|---|---|---|---|
| **g1** liste fermee | Chaque proposition a la forme de sa tache (2.0 point 4) et chaque nom est une cle de la liste, **par egalite** apres `plat` et article retire. Chaque ligne `classe` de l'en-tete nomme un objet de la liste **avec la classe du test** et une source non vide qui ne nomme pas une classe de test (`Test` en fin de mot, regle 6 : *"Jamais le test du paquet"*). Chaque objet employe a sa ligne | *la vis* en A ; *"clou, vis, aiguille"* en B ; *"clou"* sans article en A ; l'en-tete reel dont on retire la ligne d'un objet employe ; une ligne `classe vis : attire` ajoutee a l'en-tete reel | *le clou* ; *l'épingle* ecrit avec `U+2019` ; *"clou, trombone, aiguille"* | l'exactitude de chaque classe contre sa source (`QMT-CE1-2`) |
| **g2** exclus | Sur `plat` de chaque champ lu : `(pieces?\|monnaies?\|canettes?\|cles?\|clefs?\|couverts?\|cuilleres?\|cuillers?\|fourchettes?\|couteaux?\|casseroles?\|bijoux?\|bagues?\|colliers?\|bracelets?)`, `or`, `aciers?`, chacun entre bornes de mot ; le message nomme le mot trouve | *"La cuillère est en métal. L'aimant l'attire-t-il ?"* ; *la pièce de monnaie* en distracteur ; l'indice *"Pense aux clés."* ; *"la bague en or"* ; *"un clou en acier"* | *le couvercle*, *la baguette*, *une orange*, *encore*, et *"Il y a encore une orange sous le couvercle."* | un objet exclu nomme par une periphrase (*"ce qu'on met au doigt"*) ; *doré* n'est pas refuse (`Q5`) |
| **g3** une seule acceptable (A) | Polarite lue sur l'enonce. *attire* : exactement une proposition `FER`, et c'est la bonne reponse. *n'attire pas* : exactement une proposition hors `FER`, et c'est la bonne reponse | *le clou* ; *le trombone*, *la gomme* (deux attires) ; *n'attire pas*, *la gomme* ; *la bille en verre*, *le clou* (deux non attires) | sonde 12 de l'avis : *le trombone* ; *le papier d'aluminium*, *la gomme* ; *n'attire pas*, *la gomme* ; *le clou*, *la bille en verre* | rien de plus : c'est un calcul sur la liste |
| **g4** le quart | `compte(item)` : la premisse est dans l'enonce **et** tous les objets de toutes les propositions sont `FER` ou `METAL` **et** au moins un est `METAL`. Une seule fonction pour A et B. Refus si `4 * comptes < items` | 2 items qui comptent et 10 qui ne comptent pas (12) ; 3 et 10 (13) | 3 et 9 (12) ; l'item de `camille-po` ne compte pas ; l'item de `nadia-prof` (*"Ces trois objets sont en métal. Lequel l'aimant n'attire-t-il pas ?"*, *le fil de cuivre* ; *le fil de fer*, *l'agrafe*) compte ; son exemple de 1.2 (bonne reponse *le fil de fer* ; *le fil de cuivre*, *le papier d'aluminium*) compte ; un item de B compte | qu'un enfant sache vraiment que l'aluminium est un metal (la regle le suppose dit par l'enonce) |
| **g5** `FER-1` | Parmi les items de A : au moins un dont la bonne reponse est `FER`, au moins un dont elle ne l'est pas | le paquet reel prive de ses items de A a bonne reponse `FER` ; prive de ceux dont elle ne l'est pas | - | - |
| **g6** `FER-3` | Tache lue par `FORME_A` / `FORME_B` ; un enonce hors des deux est refuse, son texte dans le message. Deux taches, la moins servie au moins 3 items, comptes **calcules sur les items** puis compares aux deux lignes de l'en-tete (`^#\s+(objet\|groupe) : .*\((\d+) items?`) | le paquet reel restreint a ses items de A et deux de B, en-tete inchange ; l'item *"Que prend-on pour ramasser des épingles ?"* (une tache ecartee par `nadia-prof`) ; l'en-tete reel a un compte change | - | que A et B soient deux operations, item par item (`QC9`) |
| **g7** composition de B | Bonne reponse : trois `FER`. Chaque distracteur : exactement deux `FER` et un `METAL` | distracteur *"clou, gomme, aiguille"* ; bonne reponse *"clou, fil de cuivre, aiguille"* ; distracteur *"clou, fil de cuivre, papier d'aluminium"* | *"clou, trombone, aiguille"* ; *"agrafe, fil de cuivre, épingle"* ; *"clou, papier d'aluminium, aiguille"* | le partage d'objets entre groupes (regle 3, seconde phrase) ; un objet repete dans un groupe |
| **g8** indice | Refuse si `plat(indice)` porte, entre bornes de mot, la matiere d'un objet de la bonne reponse (un objet en A, trois en B), ou `intrus`, ou `different(e\|s\|es)? des (deux )?autres` | *"Cherche l'objet en fer."* avec *le fil de fer* ; *"Cherche l'intrus."* ; *"Trouve l'objet différent des deux autres."* | *"Imagine l'aimant qu'on approche de chaque objet, un par un."* | une matiere non metallique (*verre*, *plastique*) ; la matiere des distracteurs, qui designe la bonne reponse par elimination (*"Pense au fer."* quand la bonne reponse est *la gomme*, mesure : passe) ; toute autre periphrase de l'intrus ; qu'un indice soit une methode |
| **g9** matiere partagee, partie lexicale | En A : l'ensemble des mots du nom de chaque distracteur (article et `de`, `d`, `en`, `a`, `du`, `au`, `l`, `le`, `la` retires) ; refus si l'intersection des deux, moins les mots de la bonne reponse, n'est pas vide | *le trombone* ; *le papier d'aluminium*, *la barquette en aluminium* ; et, de moi, *le clou* ; *le papier d'aluminium*, *la feuille de papier* (`Q2`) | *le fil de fer* ; *le fil de cuivre*, *le papier d'aluminium* | forme, taille, usage (*deux feuilles contre un objet pointu*) |
| **g10** `AIM-CE1-2` | **Inchangee**, section 1 de l'avis d'exemption, sur enonce, indice et paire enonce / bonne reponse | les sondes 1, 2, 3, 10, 11, `T-a`, `T-b`, `T-c` | les sondes 4 a 9 et 12, **plus les deux de `nadia-prof`** : *"Ces trois objets sont en métal. Lequel l'aimant n'attire-t-il pas ?"* ; *"Tous ces objets sont en métal. Dans quel groupe l'aimant attire-t-il les trois objets ?"*. Dix-sept sondes | les quatre cas de sa section 3 de `reponse-nadia-aim-ce1-2-q1-q2.md` et l'indice qui dit d'ecarter ce qui n'est pas en metal |

**Sur la liste fermee de g9.** Sur les seize objets, trois paires seulement partagent un mot de leur nom (mesure, section 4) : *papier d'aluminium* / *barquette en aluminium* (`aluminium`, la sonde de `nadia-prof`), *papier d'aluminium* / *feuille de papier* (`papier`), *fil de fer* / *fil de cuivre* (`fil`). La troisieme ne peut jamais etre une paire de distracteurs en A : en polarite *attire*, *le fil de fer* distracteur est refuse par g3 ; en polarite *n'attire pas*, *le fil de cuivre* distracteur l'est aussi. g9 refuse donc en pratique deux paires, et la seconde est ma consequence de son texte (`Q2`).

**Redondance dite.** Sur `-06`, un objet exclu en proposition est deja refuse par g1 (hors liste) et dans l'enonce par g6 (forme fermee). g2 est la seule garde qui lit l'indice, et elle donne le message d'`AIM-CE1-1` partout. Je la garde sur tous les champs : trois lignes, et elle survit a un elargissement des formes.

**Ce que chaque sonde du quart tue** (mutants executes, section 4) :

| Mutant de `compte` | Tue par |
|---|---|
| cible = bonne reponse seule | l'exemple de `nadia-prof` de 1.2 (bonne reponse *le fil de fer*) ; l'item de B |
| cible = toute proposition | l'item de `camille-po` ; ma sonde sans premisse |
| sans lire la premisse | **ma sonde seule** : *"Lequel de ces objets l'aimant attire-t-il ?"*, *le clou* ; *le fil de cuivre*, *le papier d'aluminium* (tous en metal, un en cuivre, l'enonce ne le dit pas) : ne compte pas. Tiree du texte de sa regle 1 (*"que l'enonce le dit"*), a confirmer (`Q3`) |

## 3. Lot `-08` (`ce1-qlm-dangers-electricite`), huit gardes

### 3.0 Ce que le paquet porte pour que les gardes mordent

1. **Deux declarations par item**, a cote de lui (regle 1 : *"Chaque item declare sa tache et sa situation a cote de lui"*), lues exactement une fois chacune comme a `-03` : `DEL-CE1-1 tache : (danger|geste)` et `DEL-CE1-1 situation : (prise|eau|fil)`.
2. **Les declarations sont verifiees, jamais crues** :
   - tache `geste` : la bonne reponse est une forme admise (g2) ; tache `danger` : elle n'en est pas une ;
   - la situation declaree a son mot repere dans **l'enonce** (tache `geste`) ou dans **la bonne reponse** (tache `danger`) : `prises?` ; `fils?` ; `eau|lavabos?|eviers?|mouille\p{L}*`. Ces mots sont ceux des regles 2 et 7 de `nadia-prof` (*près de l'eau*, *les mains mouillées*, *le lavabo*, *l'évier*). Une autre maniere de dire la situation est refusee, visible, et se soumet a elle.
3. **Les formes admises de la regle 2, dans le test**, forme normalisee vers les situations ou elle s'applique :

| Forme (`plat`, point final retire) | Situations |
|---|---|
| `ne pas toucher le fil`, `laisser le fil sans le toucher` | fil |
| `ne pas toucher la prise`, `ne rien mettre dans la prise` | prise |
| `garder l'appareil loin de l'eau`, `ne pas l'approcher de l'eau` | eau |
| `attendre d'avoir les mains seches`, `se secher d'abord les mains`, `ne pas brancher la lampe` | toute situation, **si l'enonce porte `mains mouillees`** (`Q4`) |
| `prevenir un adulte`, `appeler un adulte`, `le dire a un adulte` | prise, eau, fil |

4. **Dans l'en-tete** : `#   danger : <...> (N items)` et `#   geste : <...> (N items)`.

### 3.1 Les gardes

| Garde | Forme | Doit refuser (contre-exemple) | Doit passer | Ne garde pas |
|---|---|---|---|---|
| **g1** `FER-3`, `FER-1` (`T08-1`) | Comptes calcules sur les declarations verifiees (3.0 point 2), deux taches, la moins servie au moins 3, compares a l'en-tete. Chaque situation est celle d'au moins un item `danger` et d'au moins un item `geste` | le paquet reel restreint a ses items `geste` et deux items `danger`, en-tete inchange ; un item `danger` de bonne reponse *"prévenir un adulte"* ; le paquet reel prive de ses items `danger` de situation `fil`, puis de ses items `geste` de situation `prise` ; l'en-tete a un compte change ; et, de moi, un item qui declare `fil` et dont l'enonce ne nomme que la prise | - | qu'une bonne reponse de A decrive vraiment un etat dangereux : *"réparer le fil"* en bonne reponse d'un item `danger` porte le mot `fil` et passe ; que A et B soient deux operations (`QC9`) |
| **g2** gestes (`T08-2`) | Bonne reponse d'un item `geste` : **egalite** avec une forme du tableau, situation declaree dans ses situations, `mains mouillees` dans l'enonce pour les trois formes de la quatrieme ligne | *"réparer le fil"* ; *"débrancher l'appareil qui fume"* ; *"ne pas brancher les mains mouillées"* ; *"ne pas brancher la lampe"* sous *"Inès veut brancher la lampe. Que doit-elle faire ?"* ; *"ne pas toucher le fil"* sous une situation `prise` | chacune des douze formes ; *"Prévenir un adulte."* (casse, point) ; *"ne pas l'approcher de l'eau"* avec `U+2019` ; *"ne pas brancher la lampe"* sous *"Inès a les mains mouillées et veut brancher la lampe. Que doit-elle faire ?"* | une forme juste hors du tableau est refusee, pas jugee : elle se soumet a `nadia-prof` |
| **g3** un seul geste | Sur tout item : au plus une proposition egale a une forme du tableau | *"ne pas toucher le fil"* ; *"prévenir un adulte"*, *"réparer le fil avec du scotch"* | - | une abstention ou un geste juste ecrit hors du tableau, en distracteur (*"s'éloigner du fil"*) |
| **g4** grammaire | Sur tout item : refus si `^ne (pas\|rien\|jamais)(?!\p{L})` trouve dans `plat(bonneReponse)` et dans aucun distracteur | *"ne pas toucher le fil"* ; *"réparer le fil avec du scotch"*, *"toucher le fil pour voir"* | *"laisser le fil sans le toucher"*, memes distracteurs ; et, de moi, *"ne pas toucher la prise"* ; *"ne rien dire à un adulte"*, *"toucher la prise pour voir"* (`Q3`) | toute autre marque de grammaire ou de forme qui designerait la bonne reponse ; les traits partages en A |
| **g5** longueur | **Rien de neuf** : `LON-CE1-5` du test transverse, `PaquetLu.mots` | *"ne pas brancher avec les mains mouillées"* (7) | *"attendre d'avoir les mains sèches"* (5 : *d'avoir* est un segment) | - |
| **g6** pile (`T08-3`) | Sur `plat` de chaque champ lu : `(?<!\p{L})piles?(?!\p{L})` | *pile*, *piles*, *"On joue à pile ou face."*, *PILE*, *pile-poil* | *empiler*, *pilote*, *pilier* | - |
| **g7** `DEL-CE1-2` (`T08-4`) | Sur `plat` : `electrocut\p{L}*` ; `brul\p{L}*` ; `(mort\|morte\|morts\|mortes\|mortel\|mortelle\|mortels\|mortelles\|mourir\|mourra\|mourrait\|mourront\|meurt\|meurent\|meurs\|tue\|tues\|tuee\|tuees\|tuer\|tuent\|tuera\|tuerait)`, bornes de mot. Sur le texte NFC accentue : `bless(é\|ée\|és\|ées)` et `(s'est\|s'était\|se sont\|s'étaient) fait(e\|s\|es)? mal` | *"Lina s'est électrocutée en touchant la prise."* ; *"pour ne pas se brûler"* ; *"Un fil abîmé peut tuer."* ; *"On peut mourir en touchant un fil abîmé."* ; *"Sami s'est blessé à la main."* | *"C'est dangereux."* ; *"pour ne pas se faire mal"* ; *"pour ne pas se blesser"* ; *"un fil électrique abîmé"* ; *"L'électricité peut faire très mal."* | une scene d'accident sans ces mots ; *tu* (pronom) n'est jamais lu comme *tuer* ; *"on se blesse"* passe (`Q6`) |
| **g8** `TRV-CE1-6` | Sur `plat` : `(chez toi\|ta maison\|ta chambre\|baignoires?)`, bornes de mot | *"Dans ta chambre, ..."* ; *"chez toi"* ; *"près de la baignoire"* | *"Dans la cuisine, ..."* | un autre objet possede (*ton fil*, *ta lampe*) ; un logement suppose sans ces mots ; prenoms et roles (`TRV-CE1-6` au-dela des mots) |

**Ce que les sondes tuent** (mutants executes) : g6 en sous-chaine (`contains("pile")`) est tue par *empiler* seule, *pilote* et *pilier* ne portant pas la suite `pile` ; `-06` g2 en sous-chaine est tue par *couvercle*, *baguette*, *orange* et *encore* ; g7 en `bless\p{L}*` sur texte sans accent est tue par *"pour ne pas se blesser"* ; g4 ecrite *"refuse toute bonne reponse en ne pas"* passe les deux sondes de `nadia-prof` et n'est tuee que par ma sonde, d'ou sa presence.

## 4. Mesure

**Methode.** Programme Java a fichier unique, hors du depot et non commite, lance par le lanceur de sources du JDK 21.0.10 (propriete `java.version` imprimee par le programme), classpath SnakeYAML 2.6, version que l'avis de `-01` a lue par `dependency:build-classpath` pour `adapters/adapter-content` et que je n'ai pas relue. Il lit chaque fichier avec `new Yaml().load`, comme `PaquetLu`, recopie `PaquetLu.sansAccent`, et applique les formes de ce fichier telles qu'ecrites, ainsi que les trois motifs de l'avis d'exemption recopies. **Ce n'est pas le test** : la preuve dans le depot sera l'execution des deux classes sur leur jeu fautif commite.

**Perimetre.** Tout fichier `*.yaml` de `adapters/adapter-content/src/main/resources/contenu/` a `eaac43a6` : 190 fichiers, 189 avec une cle `items`, 3 182 items avec `bonneReponse`, **15 901 champs** (`enonce`, `indice`, `explication`, `bonneReponse`, chaque distracteur), 9 375 propositions. Exclus : les jeux fautifs de `src/test/resources`, les items sans `enonce`. Les motifs y sont appliques **quel que soit le lot** : le but est leur precision sur une langue ecrite par d'autres, pas un refus. **Aucun de ces resultats n'est un refus dans le depot** : chaque garde ne lit que son paquet, et aucun des deux n'existe ; aucun test existant ne change d'etat.

**Sondes.** Toutes les sondes des sections 2 et 3 rendent le verdict attendu, dont les dix-sept de g10 (`-06`) : refusees 1, 2, 3, 10, 11, `T-a`, `T-b`, `T-c` ; passent 4 a 9, 12 et les deux de `nadia-prof`. Une seule a d'abord rendu un ecart, sur ma forme et non sur un motif de `nadia-prof` : `S1`, section 5.

**Precision : les mots qui portent les lettres sans etre le mot** (mots distincts du corpus, sur `plat`) :

| Lettres | Mots porteurs | Refuses a tort | Exemples |
|---|---|---|---|
| `pile` | 1 | 0 | *empilés* |
| `cle` | 12 | 0 | *cercle*, *oncle*, *siècle*, *nucléaire*, *muscle* |
| `or` | 224 | 0 | *abord*, *alors*, *aéroport*, *adore* |
| `bague` | 2 | 0 | *baguette*, *baguettes* |
| `couvert` | 2 | 0 | *couverte*, *couvertures* |
| `tue` | 11 | 0 | *statue*, *tortue*, *situé*, *habituel* |
| `acier` | 1 | 0 | *glacière* |

**Ce que les motifs trouvent, au sens propre, dans le contenu des autres lots** (champs) : g2 exclus 68 champs dans 34 fichiers, dont *pièce* 33 (des pieces de monnaie, mais aussi *la pièce* d'une maison, *une pièce jointe*, *les pièces du jeu*) et *couvert* 4 (*l'espace couvert*, *sont couverts*) ; *or* 3 (*l'or*, *Toison d'or*) ; *acier* 0 ; g8 `intrus` 1 (*"différent des autres"*, `cm1-emc-respect-differences`) ; g6 *pile* 6 champs, tous dans `cm1-sciences-energie` et `cm1-sciences-objet-technique` ; g7 *électrocut-* 0, *brûl-* 13, famille *mort* 14, *blessé(es)* 5, toutes au sens propre ; g8 `TRV-CE1-6` 4 (*baignoire* 3, *chez toi* 1). Ces chiffres disent pourquoi aucune de ces gardes n'entre dans le test transverse (`V1`), et pourquoi *pièce* et *couvert* refusent plus que la lettre (`Q5`).

**Egalites** : sur 9 375 propositions, 2 egalent un objet de A (*la gomme* de `cp-pluriel` en est une), 0 un groupe de B, 3 une forme de geste (*"prévenir un adulte"*, trois bonnes reponses de `cp-emc-alerte`) ; 0 enonce n'a `FORME_A` ou `FORME_B` ; *mains mouillées* : 0. **Grammaire** : 4 bonnes reponses du corpus commencent par *ne pas*, *ne rien* ou *ne jamais*, et 3 d'entre elles, dans des paquets d'EMC et de sciences du CM1, seraient refusees par g4 : le piege que `nadia-prof` nomme existe dans le contenu deja ecrit. **Cles** : les trois paquets `ce1-qlm-*` portent exactement `bonneReponse`, `distracteurs`, `enonce`, `indice` (1.3).

## 5. Ecarts, precisions a confirmer, et ce qui reste a `nadia-prof`

### 5.1 L'ecart trouve par la mesure

`S1` : ma premiere `FORME_A` exigeait *"lequel de ces objets"*. La sonde de la garde 4 de `nadia-prof`, reprise en garde 10, ecrit *"Ces trois objets sont en métal. Lequel l'aimant n'attire-t-il pas ?"* : elle etait refusee comme *"ni A ni B"*, et l'item qu'elle donne pour compter au quart n'aurait pas pu etre ecrit. *"de ces objets"* est devenu facultatif ; sondes rejouees, toutes conformes. C'est le risque d'une forme fermee, et la raison de `Q1`.

### 5.2 Precisions a confirmer ou retirer, par `nadia-prof`

| Code | Point | Ce que la forme fait aujourd'hui |
|---|---|---|
| `Q1` | Les enonces de `-06` sont fermes aux formes de sa regle 2 (avec ou sans *"de ces objets"*, avec ou sans l'une des deux premisses) | toute autre formulation est refusee, visible ; elle entre dans le motif apres son accord, comme un objet dans la liste |
| `Q2` | g9 refuse aussi *le papier d'aluminium* et *la feuille de papier* comme paire de distracteurs quand la bonne reponse n'a pas *papier* : c'est sa regle 3 lue sur le mot, et son motif de 1.2 (*"pour lui, c'est du papier"*) | refuse |
| `Q3` | Trois sondes sont de moi, tirees de ses textes : la sonde du quart sans premisse (g4 de `-06`), la situation declaree que l'enonce ne nomme pas (g1 de `-08`), le distracteur negatif *"ne rien dire à un adulte"* (g4 de `-08`) | dans le jeu fautif, marquees comme miennes |
| `Q4` | Sa parenthese *"(quand l'enonce dit les mains mouillees)"* suit *"ne pas brancher la lampe"* ; je l'applique aux trois formes de la ligne. Et ces trois formes ne sont liees a aucune situation : laquelle un item des mains mouillees declare-t-il ? | `mains mouillees` exige pour les trois ; toute situation acceptee si son mot repere est dans l'enonce |
| `Q5` | `-06` g2 refuse *pièce*, *couvert* et *or* dans tout sens (en pratique dans l'indice seul, l'enonce et les propositions etant fermes) : plus strict que la lettre, le corpus montre ces mots dans d'autres sens. *doré* n'est pas refuse | refuse dans tout sens |
| `Q6` | `-08` g7 refuse *blessé(e)(s)* quel que soit le sujet, et *s'est fait mal* ; il laisse passer *"on se blesse"* (present generique) | comme dit |

### 5.3 Ce que ces formes ne lisent pas

**Sur `-06`** : *"non garde : `AIM-CE1-1`, `QMT-CE1-2`"* pour l'exactitude de chaque objet contre sa classe ; *"non garde : `AIM-CE1-1`"* pour forme, taille et usage partages en A, et pour le partage d'objets entre groupes de B (regle 3) ; *"non garde : `TRV-CE1-5`"* pour qu'un indice soit une methode, une matiere non metallique ou la matiere des distracteurs dans l'indice, et l'indice qui dit d'ecarter ce qui n'est pas en metal ; *"non garde : `AIM-CE1-2`"* pour les quatre cas de sa section 3 (paraphrases sans *attirer*, metaux nommes presentes comme attires, designation hors des quatre formes, negation fausse sans designation) ; *"non garde : `FER-3`"* pour l'operation mentale de A et de B. **Tout cela reste sa lecture, `QC9`, comme elle l'a ecrit.**

**Sur `-08`** : *"non garde : `DEL-CE1-1`"* pour la realite du danger en A et son absence dans les distracteurs, et pour une bonne reponse de A qui ne decrit pas un etat ; *"non garde : `TRV-CE1-2`"* pour la plausibilite et la faussete des distracteurs de B et les abstentions ecrites hors du tableau ; *"non garde : `TRV-CE1-5`"* pour la methode de chaque indice ; *"non garde : `DEL-CE1-2`"* au sens, une scene d'accident sans aucun mot de g7 ; *"non garde : `TRV-CE1-6`"* au-dela des quatre mots de g8 ; *"non garde : `FER-3`"* pour l'operation mentale.

**Encodable si elle le demande, non propose** : la partie lexicale de sa regle 4 de `-08` (les mots *toucher*, *approcher*, *brancher*, *sécher*, *prévenir*, *adulte* dans un indice de B ; *abîmé*, *cassé*, *eau* dans un indice de A) et la partie lexicale de sa regle 3 en A de `-08` (la fonction de g9 de `-06`) : elle ne les demande pas en 2.7 et les garde a sa lecture. Le possessif de deuxieme personne (*ton*, *ta*, *tes*) pour *"aucun objet n'appartient a l'enfant"* : encodable, mais il refuserait aussi *"tes mains"* ; je ne le propose pas sans elle.

**Information pour sa lecture, sans demande** : *"prévenir un adulte"* est trois fois la bonne reponse de `cp-emc-alerte`. La garde transverse de `NRC-CE1-1` ne compare que l'enonce et le trio de propositions ; la meme bonne reponse a la meme tache reste a elle (`QC9`).

## 6. Actions et routage

| Code | Quoi | Qui |
|---|---|---|
| `A1` | Les dix gardes de la section 2 dans `PaquetCe1QlmAimantTest`, l'ensemble ferme de cles (1.3), les dix-sept sondes de g10, et le jeu fautif ; en-tete du paquet au format de 2.0 ; tout dans le commit du premier jet | `marc-dev` |
| `A2` | Les huit gardes de la section 3 dans `PaquetCe1QlmDangersElectriciteTest`, l'ensemble ferme de cles, le jeu fautif ; declarations et en-tete au format de 3.0 ; tout dans le commit du premier jet | `marc-dev` |
| `A3` | Composer les groupes de B dans `LON-CE1-5` : *"fil de fer, fil de cuivre, clou"* fait 7 mots et le test transverse le refuse | `marc-dev` |
| `A4` | `Q1` a `Q6` | `nadia-prof`, par `sofia-sm` |
| `A5` | Dans `S57-BR-QLM-CE1-06` criteres 8 et 9 et `S57-BR-QLM-CE1-08` criteres 7 et 8, renvoyer `T06-1`, `T06-2`, `T08-1` a `T08-4` a ce fichier | `camille-po` |

**Signe** : `theo-archi`, s57, palier 5, 2026-10-26.
