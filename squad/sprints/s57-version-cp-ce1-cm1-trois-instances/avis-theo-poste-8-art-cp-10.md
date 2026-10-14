# [Sprint s57] Poste 8 (reprise d'`ART-CP-10`) - avis complementaire de theo-archi

- **Date** : 2026-10-14 21:38 (UTC+02:00), lu par `date`.
- **Auteur** : theo-archi. Branche `s57-theo-poste8`, worktree dedie, base `a457d2f3` (tip de `round-two`).
- **Lu** : `avis-faisabilite.md` (mes `C1` a `C4`, section 2.4, section 3) ; `squad/conformite/releves-s57/proposition-ines-poste-8-art-cp-10.md`, `candidat-poste-8.css`,
  `pilote-poste-8.mjs`, `agreger-poste-8.mjs` ; `reponse-nadia-forme-poste-8.md` ; `rejeu-nadia-palier-1.md` section 5 (`Q-C1`) ; `DEC-0120` A.79 a A.86 et A.65 ;
  `lecture-testabilite.md` (2.3, `Q-Y4`) ; le backlog (`S56-RDO-CP-R1`, `S56-RDO-CP-R2`) ; `recreo.css` lignes 1751 a 1872 ; `fragments/manche.html` lignes 183 a 216 ;
  `PartieController.retourModele`.
- **Ce commit n'ajoute que ce fichier.** Aucun `ADR` n'est modifie ici : section 3 dit pourquoi, et l'annexe A porte le texte des amendements.
- **Ce que cet avis ne fait pas** : il ne choisit ni la forme visuelle (`ines-design`), ni ce que l'ecran dit a l'enfant (`nadia-prof`), ni la table des ports (`sofia-sm`).

## 1. Decisions

| Code | Decision | Section |
|---|---|---|
| `D1` | `C1` tombe dans sa lettre, par inclusion. Elle est remplacee par `C1'`, ecrite sur la propriete et non sur des noms de classes. `L3` tient sous `C1'` | 2 |
| `D2` | La forme `L3` retenue respecte le veto de `nadia-prof` : le message occupe les colonnes du meuble et elles seules. La proposition d'`ines-design` est a corriger | 2.3 |
| `D3` | Amendements dus : `ADR-0104` (point 5 de la decision 4 remplace) et un renvoi dans `ADR-0084` et dans `ADR-0024`. `ADR-0106` n'est pas concerne. Texte fige en annexe A, verse dans le commit du code de la voie (b) | 3 |
| `D4` | Aucune regle ArchUnit : compte inchange, **41** `static final ArchRule` et **38** `@ArchTest` | 3.4 |
| `D5` | `Q-Y4`, seconde moitie : oui a un juge pur du releve, **dans le job `outils`, pas dans le reacteur** | 4 |
| `D6` | Forme du rejeu de B2 : une branche, un commit, quatre verifications avant le jeu | 5 |

## 2. `C1` pour `L3`

### 2.1 Pourquoi `C1` tombe

`C1` (`avis-faisabilite.md`, 2.3) interdisait a toute regle de la correction de lire `.message-jeu`, `.signal`, `.est-juste`, `.est-faux` ou `data-oeuvre-revelee`. Son motif est la
condition `C4` de `nadia-prof` : le meuble ne dit jamais le resultat. **J'ai ecrit le perimetre en enumerant des noms, et il echoue par inclusion** :

1. **`.message-jeu` n'est pas un etat, c'est une place.** `recreo.css` ligne 1871, en production depuis s55 et accepte : `.ecran-deux-oeuvres > .message-jeu { grid-area: msg; }`.
   Sous `C1` a la lettre, la regle qui pose le message sous le meuble serait interdite.
2. **Le meuble depend deja d'un etat, et c'est accepte.** Ligne 1817 : `.propositions-oeuvres:has(.proposition.deja-essayee) { padding-bottom: 3.25rem; }`. La rangee grandit
   apres une erreur pour loger la croix (`O1`, retenue par `nadia-prof`, sous `N6` : ni la case ni l'image ne bougent). `C1` ne disait pas cette exception.

Ce qui compte n'est pas le nom qu'un selecteur porte, c'est **ce que la regle fait bouger, et selon quoi**. `L3` dispose l'interieur du message, identique apres une bonne
reponse, une erreur et une revelation (mesure d'`ines-design`, section 5 : signal et texte aux memes abscisses dans les trois etats). Il ne touche pas le meuble.

### 2.2 `C1'`, qui remplace `C1`

**Aucune regle de la correction ne fait dependre du resultat la boite du meuble (`.cabinet` et ses descendants) ni celle des elements places avant lui (score, consigne,
reference, enonce).** Sur le diff, cela se lit en trois points :

1. Une regle dont le sujet est **hors** de `.message-jeu` ne nomme ni `.message-jeu`, ni `.signal`, ni `.est-juste`, ni `.est-faux`, ni `data-oeuvre-revelee`, y compris
   dans un `:has(...)`. La seule dependance d'etat du meuble reste celle de la ligne 1817 (`O1`) ; la correction ne l'etend pas.
2. Une regle dont le sujet est `.message-jeu` ou un de ses descendants peut nommer `.message-jeu` et `.signal`. Elle ne lit ni `.est-juste`, ni `.est-faux`, ni
   `data-oeuvre-revelee` : la disposition du message est la meme dans les trois etats.
3. **Aucun element ne s'etend a la fois sur la rangee du message et sur une rangee au-dessus.** Sinon la presence du message, qui n'existe qu'apres une reponse
   (`th:if="${message != null}"`), peut redimensionner les rangees du meuble. La rangee `msg` de la grille ne partage donc sa zone avec aucune autre (`.` dans les colonnes qu'elle n'occupe pas).

**Ce qui le garde.** Les points 1 a 3, a la lecture du diff, par moi avant la fusion de B1. **Et, nouveau par rapport a `C1`, l'effet est mesurable** : le juge pur de la section 4
(`J5`) compare la geometrie du meuble d'un etat a l'autre sur le releve du jar. `C1` ecrivait qu'aucun automate ne le verrait ; c'etait vrai du selecteur, pas de son effet.

### 2.3 La forme `L3` sous le veto de `nadia-prof`

`candidat-poste-8.css` place le message sur `"msg msg msg"` : les trois colonnes, reference comprise. Le signal tombe en colonne de gauche, sous la reference (x = 16,5 a 744,
mesure d'`ines-design`, section 5), ce que `nadia-prof` refuse (`reponse-nadia-forme-poste-8.md`, section 2). **Forme compatible avec le veto et avec `C1'`** : la zone du
message n'occupe que les colonnes du meuble, et la colonne de la reference est vide sur cette rangee, soit `". msg msg"` dans la grille de `L2`. Le detail (largeur des deux
colonnes internes, bandeau unique ou signal seul colore) reste a `ines-design`.

**A `ines-design`** : la proposition est a corriger sur ce point, puis a remesurer avec `pilote-poste-8.mjs`. Deux choses a dire dans la version corrigee :

- **Le bord du message par rapport au meuble**, a 744 et 992 : son bord gauche et son bord droit dans ceux de `.cabinet`. Si la zone `jeu` est plus large que le meuble, il faut le
  dire, parce que `J4` (section 4) juge contre `.cabinet`, pas contre la zone.
- **La condition 5 de `nadia-prof`** (la coupure du verdict tombe entre les deux phrases, *"sans dependre de la police"*). Le verdict est un seul `<p class="verdict">` dont le
  texte vient du modele (`manche.html` ligne 194). **Une largeur de colonne ne garantit pas une coupure independante de la police.** Si la forme exige une coupure structurelle,
  elle touche le balisage ou le texte du message, partages par tous les jeux `bandit-manchot` : c'est hors de `.ecran-deux-oeuvres`, et cela demande une proposition separee,
  pas une ligne de feuille. Je ne tranche pas la forme ; je dis seulement que la feuille seule ne tient pas cette condition.

**`C2`, `C3`, `C4` sur `P`** : tenues. `L2` et `L3` entrent dans le bloc `@media (min-width: 720px)` existant et ne visent aucune image de proposition (`C2`). `L1` masque
`.cabinet-bandeau` et `.cabinet-socle`, jamais `.cabinet` (`C3`). Les cotes se jugent sur le jar (`C4`, section 4).

## 3. Voie (b) de `RDO-CP-R2` : les amendements

### 3.1 Ce qui est amende, et ce qui ne l'est pas

- **`ADR-0104`, decision 4, point 5** : il retirait la reponse attendue de l'ecran de fin et y laissait la question rappelee et l'explication. Il est remplace (annexe A.1).
- **`ADR-0084`, clause 3** (la question rappelee a cote de la reponse) : renvoi date (A.2). Il n'y a plus ni reponse ni question sur cet ecran.
- **`ADR-0024`, decision 1** (l'explication *"s'affiche sur l'ecran de retour apres chaque reponse"*) : renvoi date (A.3). **`DEC-0120` A.82 ne le nommait pas** ; la voie (b)
  le contredit pour cet ecran, et la Javadoc de `retourModele` (*"Le controleur ne decide pas si elle merite d'etre affichee"*) le cite.
- **`ADR-0106` : non concerne.** Il porte sur l'accueil par matiere ; l'amendement que je lui dois (`avis-faisabilite.md`, section 9) appartient a `HS-ACCUEIL-APM-R2`, pas au poste 8.

### 3.2 Pourquoi l'amendement n'est pas commite ici

`DEC-0120` A.82 et le backlog (`S56-RDO-CP-R2`, point 8) veulent l'amendement **dans le commit du code**. La regle qu'il appelle est le test du critere `D7`, dont la forme
est a `yanis-qa` et au developpeur (backlog, point 2), et ce test est rouge sans les deux lignes du controleur. **Je ne peux donc pas le commiter seul** sans ecrire un `ADR`
dont la regle n'existe pas, ce que je refuse. Je ne prends pas non plus le code et le test : ils sont a `lea-dev` et a `yanis-qa`.

**Procedure** : le texte de l'annexe A est fige par ce commit. Le developpeur le verse tel quel dans les trois `ADR` et dans `ADR-index.md`, **dans le commit qui porte les deux
lignes du controleur et le test de `D7`**. Je verifie avant la fusion de B1 que le texte verse est celui de l'annexe, a la ligne pres, par `diff`.

### 3.3 Mesures

Arbre jetable en `--detach` sur `a457d2f3`, supprime apres. Enveloppe `bash outils/mvn-jdk21.sh`.

| Arbre | Commande | Resultat |
|---|---|---|
| base + voie (b) : `explication` non posee et `questionRappeleeVisible` faux quand `reponseEnImage` | `-B clean verify`, reacteur complet | **2498 tests, 0 echec** (120, 31, 486, 1484, 371, 6) |
| base + predicat elargi a tout jeu (question et explication jamais posees) | `-B -pl bootstrap -am test` (perimetre : `bootstrap` et ses dependances) | **3 echecs** : `AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest` 2, `LExplicationDUnItemReelAtteintLEcranDeRetourTest` 1 |
| base + voie (b) + simulation de B2 (section 5) | `-B clean verify`, reacteur complet | **2498 tests, 0 echec**, JDK **21.0.10** (`java.version` des 59 rapports de `bootstrap`, une valeur) |

**Ce que cela dit** : aucun garde-fou existant ne voit la voie (b), la regle de l'amendement est donc neuve et non une redite ; l'elargissement du predicat aux jeux a reponse
ecrite est deja garde sur les paquets reels ; et la voie (b) ne fait rougir rien quand l'entree revient. La version du JDK des deux premieres lignes n'a pas ete lue (meme
enveloppe, meme worktree ; les rapports ont ete ecrases par la troisieme).

### 3.4 Regle ArchUnit

**Aucune.** L'objet est un ecran rendu et une feuille de style, pas une dependance entre types. **Compte avant et apres : 41 `static final ArchRule`, 38 `@ArchTest`.**
Methode : lignes des fichiers `*.java` suivis (`git ls-files`, ce qui exclut les worktrees d'agents) qui correspondent a `^\s*(public |private |protected )?static final ArchRule `
d'une part, a `^\s*@ArchTest\s*$` d'autre part, sur `a457d2f3`. Identique au compte d'`avis-faisabilite.md`, section 10, sur `544159d9`.

## 4. `Q-Y4`, seconde moitie : le juge pur du releve

### 4.1 Oui, et pas dans le reacteur

**Pourquoi pas un test du job `build`** : le reacteur n'a pas de navigateur (`lecture-testabilite.md`, 2.3). Un test Java qui lirait un releve JSON commite jugerait **un
fichier, pas le jar** : il resterait vert sur un releve perime aussi longtemps qu'on ne le refait pas. Il serait decoratif.

**Pourquoi un juge quand meme** : aujourd'hui le verdict des cotes est fait a l'oeil sur la sortie d'`agreger-poste-8.mjs`, qui imprime des maximums sans rien juger. Un
juge pur fixe dans du code ce qui est juge, sur quoi, et quand le releve est incomplet. **Forme, precedent `outils/mesurer-accueil.py` (`HS-ACCUEIL-MESURE`, `ADR-0009`)** :

- **`outils/juger-pli-deux-oeuvres.py`** : fonctions pures qui lisent le JSON tel que `pilote-poste-8.mjs` l'ecrit, et une entree `python outils/juger-pli-deux-oeuvres.py <releve.json>`
  qui imprime un verdict par barre et sort 1 si une barre echoue ou si le releve est incomplet.
- **`outils/juger-pli-deux-oeuvres-test.py`** : entrees fabriquees, aucun navigateur. Le motif `outils/*-test.py` du job `outils` le lance sans changer `ci.yml`.
- **Le pilote reste un outil de poste** (Node, Chrome) : il n'entre pas dans la CI, sa preuve est manuelle (4.3).

Pas d'`ADR` : c'est un outil de poste de la forme d'`ADR-0009`, sur un precedent existant, et non une decision neuve.

### 4.2 Ce que le juge juge

| Code | Jugement | Source |
|---|---|---|
| `J1` | **Completude** : hauteur 551 sur toute mesure ; largeurs 744 et 992 presentes ; a chaque largeur, autant d'items que le paquet en porte (compte lu sur `cp-rapprocher-deux-oeuvres.yaml`, jamais ecrit dans le juge), chacun avec `fraiche`, `echec1` et `revelation`. Un releve incomplet sort 1, il ne passe pas vert | `ADR-0051` |
| `J2` | **Barre du refus** : a 744, `revelation`, bas de `m4` au plus 551 sur chaque item | `D5`, critere de `S56-ART-CP-10-REPRISE` |
| `J3` | **`RDO-CP-R1`** : a 744, bas de `indice` au plus 551 a chaque echec ; a 992, bas de chaque marque au plus 551 a chaque echec. Imprime **a part de `J2`** : un echec de `J3` seul est une reserve, pas un refus (`D5`) | `D5`, `DEC-0120` A.81 |
| `J4` | **Veto de `nadia-prof`** : a chaque largeur ou la grille s'applique (720 et plus), la boite de `messageJeu` commence sous le bas de `cabinet` et tient entre son bord gauche et son bord droit, a 1 px pres | `reponse-nadia-forme-poste-8.md`, section 2 ; `M1`, `M4` |
| `J5` | **`C1'` et `N6`** : pour chaque item, haut, largeur et hauteur des cases identiques dans tous les etats ; haut, gauche et largeur de `cabinet` identiques dans tous les etats ; hauteur de `cabinet` identique entre deux etats qui ont tous deux au moins une case essayee, ou tous deux aucune (la seule dependance acceptee, `O1`) | 2.2 ; `N6` |

**Ce que le juge ne juge pas, dit pour qu'on ne le lui prete pas** : la coupure du verdict (condition 5), la taille rendue des reproductions (regle 6), le decalage lateral des
cases a l'apparition d'une barre de defilement (`proposition-ines-poste-8-art-cp-10.md`, 8.5 : `J5` ne compare pas l'abscisse des cases, et ce n'est pas a moi d'en faire une
barre). Tout cela reste au rejeu de `nadia-prof`.

### 4.3 Preuves, sur materiau reel

Les tests du juge prouvent les verdicts sur des entrees fabriquees. **L'enveloppe et le juge, ensemble, se prouvent sur trois releves reels, cites avec leur commit** :

1. **Le jar de s56** (worktree jetable sur `13d00bfe^`, qui sert encore l'entree) : `J2` et `J3` doivent sortir rouges, sur les cotes 656, 676 et 568 du releve `Q7`.
2. **La forme `P` telle que mesuree** (`candidat-poste-8.css` injecte, `"msg msg msg"`) : `J4` doit sortir rouge, le signal commencant a x = 16,5 pour un meuble a 216,5.
3. **Le jar de B2** : le verdict cite par le developpeur dans son rapport, puis par `nadia-prof`.

Ce sont les deux premiers qui disent que le juge mord sur ce que le monde rend, pas sur ce que son auteur imagine.

**Qui, quand** : forme et cas de test a `yanis-qa` (`DEC-0120` A.85 : *"juge pur du releve avec `theo-archi`"*), cette section en est le cahier. **Avant le rejeu de
`nadia-prof`, pas avant B1** : B1 ne sert pas le jeu, et la mesure se fait sur B2.

## 5. Forme du rejeu de B2

| Element | Forme |
|---|---|
| Branche | `s57-b2-rejeu-art-cp-10`, issue de `round-two` **apres** la fusion de B1 : `git merge-base --is-ancestor <tete de B1> HEAD` sort 0, cite dans le rapport |
| Commit | **un seul**, prefixe du developpeur, qui ne touche que trois fichiers : `catalogue.yaml`, `TexteAccentueExposeParLePortEntrantTest.java`, `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest.java` |
| Entree | identifiant **`bandit-manchot-rapprocher-deux-oeuvres-cp`** (`DEC-0120` A.65), six lignes identiques a celles retirees par `13d00bfe` |
| Inventaire | **quatre chaines**, chacune dans sa liste : l'identifiant, le titre (*"Des œuvres faites de la même façon"*), la consigne, la competence (libelle d'`ART-CP-10`) |
| Garde-fou 4 | `isEmpty()` redevient `containsExactly("bandit-manchot-rapprocher-deux-oeuvres-cp")`, message d'avant le retrait |
| Hors du commit | `LEnonceDuPaquetCpRapprocherDeuxOeuvresEstUniqueEtCourtTest` et `LeFormatDUneOeuvreNeDesignePasLaBonneReponseTest` : ils lisent le fichier du paquet depuis `13d00bfe` et ne reviennent pas (`avis-faisabilite.md`, 3.2) |

**Le moyen le plus court, et sa verification** : depuis la base de B2, `git show 13d00bfe -- <les trois fichiers> | git apply -R`. **Mesure sur `a457d2f3`** : les quatre
hunks du test d'inventaire passent avec un decalage de -8 lignes, celui du catalogue et celui du garde-fou 4 passent ; `grep -c "^  - id:"` sur le catalogue passe de 146 a 147.
Si un hunk refuse a la base reelle de B2, le developpeur l'ecrit a la main et le dit ; le controle des six lignes ci-dessous reste le meme.

**Quatre verifications, dans le rapport du developpeur, avant que `nadia-prof` joue** :

1. `git diff --name-only <base de B2> HEAD` rend exactement les trois fichiers.
2. `git show 13d00bfe^:adapters/adapter-content/src/main/resources/catalogue.yaml | grep -A5 "id: bandit-manchot-rapprocher-deux-oeuvres-cp$"` et la meme commande sur la tete de B2
   rendent les memes six lignes.
3. **Les images et le paquet joues sont ceux du refus** : `git diff --stat 13d00bfe HEAD -- adapters/adapter-web/src/main/resources/static/oeuvres adapters/adapter-content/src/main/resources/contenu/cp-rapprocher-deux-oeuvres.yaml`
   est vide. Mesure sur `a457d2f3` : vide (aucun commit sur ces chemins depuis `25f0c1ee`, le 2026-10-09). Le paquet nomme 11 reproductions, le repertoire en porte 12 (la douzieme,
   `oeuvre-hippopotame`, est d'un autre paquet). Si la ligne n'est pas vide, ce qui a change est nomme a `nadia-prof` avant qu'elle joue.
4. `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl` : vert, nombre de tests et version du JDK cites. **Simulation sur `a457d2f3` plus la voie (b) plus ce
   commit : 2498 tests, 0 echec, JDK 21.0.10** (3.3).

`R1` et `R2` d'`avis-faisabilite.md` (3.3) gardent la fusion de B2 apres le verdict ; ils ne changent pas.

## 6. Conditions pour que `lea-dev` implemente le poste 8

### 6.1 Avant d'ouvrir B1

| # | Condition | A qui |
|---|---|---|
| `P1` | La forme corrigee d'`ines-design` est commitee (2.3 : `". msg msg"`, bords du message, et sa reponse sur la condition 5) | `ines-design` |
| `P2` | `planning.md` amende pour engager le poste (`P-6`) | `sofia-sm` |

### 6.2 B1 : la correction et la voie (b)

1. **Feuille** : les regles de la forme corrigee, telles que commitees par `ines-design`, sous `C1'` (2.2), `C2`, `C3`. Dans le meme commit que `L1`, le jeu fautif
   `contreexemple/CabinetDeLaMachineTest/dimension-en-pixels` recoit `.ecran-deux-oeuvres .cabinet-bandeau` et `.ecran-deux-oeuvres .cabinet-socle` (rouge mesure par
   `ines-design`, section 9).
2. **Voie (b), un commit** : les deux lignes de `retourModele` (`explication` et `questionRappeleeVisible`, sur le predicat `reponseEnImage` deja present) ; la Javadoc de
   `retourModele` et le commentaire de `fragments/retour.html` (ligne 99) qui disent l'explication posee a tout verdict, corriges ; le test de `D7` avec `Q-C1` (attributs) ;
   **le texte de l'annexe A verse tel quel** dans `ADR-0104`, `ADR-0084`, `ADR-0024` et `ADR-index.md`. Les trois morsures de l'annexe A.1, avec leurs nombres, dans le rapport.
3. **Tests nommes dans le rapport** (`C3`) : `CabinetDeLaMachineTest`, `AucunTexteNeVitDansUnSousArbreMasqueTest`, `LHabillageResteImmobileTest`,
   `UnEcranNeGouvernePasLApparenceDUnAutreTest`, `LePlafondDeRenduDUneReproductionEnPropositionEstGardeTest`, `PartieControllerPropositionsEnImageTest`.
4. **Porte** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, et les jobs `chemins`, `outils` et `prefixes` de `ci.yml`, chacun nomme.
5. **Fusion de B1** apres ma lecture du diff : `C1'` points 1 a 3, et le texte des `ADR` identique a l'annexe.

### 6.3 Mesure au jar (`C4`), entre B1 et le rejeu

Sur la tete de B2, jamais sur un jar sans l'entree : `pilote-poste-8.mjs` sans CSS injecte (`-`), largeurs 720, 744, 768, 992, 1200, fenetre de 551, port et repertoire du
planning (`avis-faisabilite.md`, I1 a I3). Le releve JSON est commite sous `squad/conformite/releves-s57/` avec le commit mesure, et juge par le juge pur de la section 4 des
qu'il existe. Le verdict sur le refus reste celui de `nadia-prof`.

### 6.4 B2

Section 5. Rejeu de `nadia-prof` sur la tete de B2, puis fusion selon `R1` et `R2`.

## 7. Questions

| Code | Question | A qui |
|---|---|---|
| `Q-T1` | La condition 5 (coupure entre les deux phrases, independante de la police) se tient-elle par la feuille, ou demande-t-elle une proposition sur le balisage du message, partage par tous les jeux `bandit-manchot` (2.3) ? | `ines-design` |
| `Q-T2` | Le juge pur de la section 4 est-il ecrit par `yanis-qa` avant le rejeu, et sur quelle part du palier ? | `sofia-sm`, `yanis-qa` |
| `Q-T3` | La procedure de 3.2 (texte fige ici, verse par le developpeur dans le commit du code) convient-elle a la regle des prefixes du depot ? Je n'en vois pas d'autre qui tienne a la fois *"dans le commit du code"* et la frontiere des roles | `sofia-sm` |

## 8. Jobs de `ci.yml` lances pour ce commit

Sur ce worktree, ce fichier indexe, avant commit :

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl` : `BUILD SUCCESS`, 2498 tests, 0 echec (120, 31, 486, 1484, 371, 6), JDK 21.0.10
  (`java.version` des 59 rapports de `bootstrap`). Le test des lignes de tableau a lu ce fichier. L'etape "Package du fat jar" n'est pas lancee.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` : sort 0.
- **`outils`** : les juges de `outils/*-test.sh`, `squad/outils/*/*-test.sh` et `outils/*-test.py` : 11 lances, 11 sortis 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-garde-fou-accent-test.sh` sortent 0 ; le sujet de ce commit passe dans
  `verifier-prefixes.sh`. Le controle du proprietaire du garde-fou de l'accent n'est pas lance : ce commit ne touche aucun de ses chemins.

Aucun n'est le resultat de la CI distante.

## Annexe A. Texte des amendements, a verser tel quel dans le commit du code de la voie (b)

Le developpeur ne change rien de ce texte. Quatre blocs, separes par une ligne `---` qui n'est pas a verser.

### A.1 A ajouter a la fin d'`ADR-0104`

```markdown
## Amendement du 2026-10-14 (s57, `theo-archi`) - l'ecran de fin d'un jeu a reponse en image ne rend aucun texte de l'item

**Ce que le point 5 de la decision 4 laissait passer.** Il retirait la reponse attendue de l'ecran de fin quand la bonne reponse est une
reproduction, et y laissait la question rappelee (fin revelee) et l'explication (toute fin). Ces deux textes designent des oeuvres que cet
ecran ne montre pas : reserve `RDO-CP-R2` de `nadia-prof` (`FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md`). Elle a choisi la voie (b)
(`D6`) et ecrit le critere en liste fermee (`D7`, `lecture-nadia-palier-1.md` de s57 ; attributs `alt`, `title` et `aria-label` compris,
`rejeu-nadia-palier-1.md` section 5). Elle a ecarte l'oeuvre a l'ecran de fin : la phrase *"une image de plus a cet ecran"* du point 5 n'est
pas retenue.

**Point 5 remplace.** Sur l'ecran de fin d'un jeu dont la bonne reponse de la derniere manche est une reproduction, le controleur ne pose ni la
question rappelee, ni la reponse attendue, ni l'explication, que la manche soit juste ou revelee. Le predicat est celui qui pose deja
`reponseAttendueVisible` dans `PartieController.retourModele` (prefixe `oeuvre-` de la bonne reponse, decision 3), et aucun autre ; le
controleur ne lit le texte d'aucun de ces champs. Le domaine ne change pas : le verdict porte toujours l'enonce (`ADR-0084`, clauses 1 et 2)
et l'explication (`ADR-0024`, decision 2). L'ecran de manche n'est pas touche.

**Ce que cela restreint ailleurs**, par un renvoi date dans chacun, sans reecrire leur texte : `ADR-0084` clause 3 (il n'y a sur cet ecran ni
reponse ni question a apparier ; REV-3 ecartait deja ces jeux, amendement du 2026-10-11) ; `ADR-0024` decision 1 (l'explication s'affiche
sur l'ecran de retour apres chaque reponse, sauf sur celui-ci).

**Regle executable.** Pas une `ArchRule` : l'objet est un ecran rendu, motif d'`ADR-0084` (section "Regle executable"). Les tests du critere
`D7` (backlog, `S56-RDO-CP-R2`, points 1 a 4), dans
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/PartieControllerPropositionsEnImageTest.java`, ecrits dans le meme commit que cet
amendement :

1. fin revelee et fin juste d'un jeu a reponse en image dont l'item porte une explication : tout noeud de texte et toute valeur d'`alt`,
   `title` ou `aria-label` du fragment `retour`, hors sous-arbre `aria-hidden="true"`, appartient a la liste de `D7` ou est vide ; aucun
   `.question-rappelee`, aucun `.explication` ;
2. non-regression sur un jeu a reponse ecrite : question rappelee et reponse attendue apres une fin revelee, explication apres une fin juste.

Trois morsures, mesurees dans ce commit et citees avec leurs nombres dans le rapport du developpeur, nombre de tests lances superieur a 0 :
remettre `questionRappeleeVisible` a `!verdict.juste()` rougit le point 1 ; poser l'explication a tout verdict rougit le point 1 ; etendre le
predicat a tout jeu rougit le point 2.

**Mesure prealable** (`theo-archi`, arbre jetable sur `a457d2f3` plus les deux lignes du controleur, reacteur complet, JDK 21.0.10) : 2498
tests, 0 echec. Aucun garde-fou existant ne voyait la voie (b) : cette regle est neuve. Le predicat etendu a tout jeu rougit deja 3 tests de
`bootstrap` sur les paquets reels (`AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest` 2, `LExplicationDUnItemReelAtteintLEcranDeRetourTest` 1) ;
le point 2 le garde aussi sur le jeu de preuve.

**Compte ArchUnit inchange** : 41 `static final ArchRule`, 38 `@ArchTest` (methode de la section "Regle executable", sur `a457d2f3`).

**Ce qui me ferait changer d'avis** : un ecran de fin qui montre a nouveau l'oeuvre attendue. La question rappelee et l'explication auraient
alors de nouveau leur objet a l'ecran, et ce point se rouvrirait avec l'exclusion de REV-3.
```

---

### A.2 A ajouter a la fin d'`ADR-0084`

```markdown
## Renvoi du 2026-10-14 (s57) - l'ecran de fin d'un jeu a reponse en image ne pose plus la question rappelee

`ADR-0104`, amendement du 2026-10-14 : sur l'ecran de fin d'un jeu dont la bonne reponse est une reproduction, ni la reponse, ni la question
rappelee, ni l'explication ne sont posees (voie (b) de `RDO-CP-R2`, choisie par `nadia-prof`). La clause 3 n'y a plus d'objet. Les clauses
1 et 2 ne changent pas : le verdict porte toujours l'enonce. Regle executable : celle de l'amendement d'`ADR-0104`.
```

---

### A.3 A ajouter a la fin d'`ADR-0024`

```markdown
## Renvoi du 2026-10-14 (s57) - l'explication n'est pas posee sur l'ecran de fin d'un jeu a reponse en image

`ADR-0104`, amendement du 2026-10-14 : sur l'ecran de fin d'un jeu dont la bonne reponse est une reproduction, l'explication n'est pas posee
(voie (b) de `RDO-CP-R2`, choisie par `nadia-prof`) ; elle designe des oeuvres que cet ecran ne montre pas. La decision 1 (*"s'affiche sur
l'ecran de retour apres chaque reponse a cet item"*) vaut partout ailleurs. Les decisions 2 et 3 ne changent pas : le verdict porte toujours
l'explication, et c'est l'adaptateur web qui ne la pose pas, sur le predicat de la population d'images, sans lire son texte. Regle
executable : celle de l'amendement d'`ADR-0104`.
```

---

### A.4 Ligne a ajouter a la table "Amendements" d'`ADR-index.md`, apres la derniere

```markdown
| ADR-0104 | 2026-10-14 (s57) | **son point 5 de la decision 4 laissait la question rappelee et l'explication sur l'ecran de fin d'un jeu a reponse en image**, ou elles designent des oeuvres absentes (`RDO-CP-R2`). Voie (b) de `nadia-prof` : ni question, ni reponse, ni explication sur cet ecran, sur le predicat existant. Renvois dans `ADR-0084` (clause 3) et `ADR-0024` (decision 1). Regle : les tests de `D7` dans `PartieControllerPropositionsEnImageTest`, trois morsures. Aucun garde-fou existant ne voyait la voie (b) (2498 tests verts avec elle). Compte ArchUnit inchange (41 declarations, 38 `@ArchTest`) |
```
