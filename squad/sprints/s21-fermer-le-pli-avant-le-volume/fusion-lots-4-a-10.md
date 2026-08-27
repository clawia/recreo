# S21 - fusion de la seconde vague sur round-two (lots theo, ines/marc, marc-2, lea-2, sofia,
camille, nadia)

Sept branches fusionnees sur `round-two`, parti de `1219336` (etat apres `fusion-lots-1-a-3.md`).
Ceremonie de fusion, pas une review : le sprint n'est clos qu'a la review, qui verse ce journal.

## Verification de provenance

`squad/outils/sofia-sm/verifier-provenance-lots.sh`, tetes annoncees confirmees, ancetre commun
avec `round-two` trouve pour les sept :

```
branche 's21-theo' : lignee confirmee, un ancetre commun avec round-two existe.
branche 's21-ines-2' : lignee confirmee, un ancetre commun avec round-two existe.
branche 's21-marc-2' : lignee confirmee, un ancetre commun avec round-two existe.
branche 's21-lea-2' : lignee confirmee, un ancetre commun avec round-two existe.
branche 's21-sofia' : lignee confirmee, un ancetre commun avec round-two existe.
branche 's21-camille' : lignee confirmee, un ancetre commun avec round-two existe.
branche 's21-nadia' : lignee confirmee, un ancetre commun avec round-two existe.
tous les lots verifies descendent de round-two avec la tete annoncee.
```

**Lignee reelle, verifiee au-dela du script** (retro s18, point 3 : verifier meme quand l'agent dit
l'avoir fait). Le script confirme un ancetre commun, pas que cet ancetre est la tete de
`round-two` : `git merge-base round-two <branche>` donne, pour chacune, un point different de
`1219336` :

| Branche | `merge-base` avec `round-two` | Lecture |
|---|---|---|
| `s21-theo` | `caad2f0` (mon planning, avant la premiere vague) | En retard d'une vague, pas d'une lignee : `git merge` calcule le vrai diff a trois points, pas un diff tete-a-tete. Verifie plus bas sur `PaquetCm1ReperesTest.java`, qui semblait divergent en diff naif et fusionne sans conflit. |
| `s21-ines-2` | tete de `s21-marc` (`0e0bc4e`) | Conforme au brief : `s21-ines-2` porte `S21-2` de `marc-dev` avec elle. |
| `s21-marc-2`, `s21-lea-2`, `s21-sofia`, `s21-camille` | `1219336` | Parties directement de la tete courante. |
| `s21-nadia` | `16bf561` (son propre `S21-8`, deja fusionne) | Un seul commit neuf par-dessus son propre travail deja integre. |

**Un faux collision signale et ecarte avant fusion.** `s21-theo`, `s21-ines-2` et `s21-nadia`
touchent tous les trois `PaquetCm1ReperesTest.java`, en plus des trois collisions certaines
annoncees dans le brief. Diff `round-two..branche` naif : les trois semblent **revenir** sur la
correction Javadoc de `S21-4` (citation `ADR-0029`) deja fusionnee dans `1219336`. Verifie ligne a
ligne : le fichier est **identique** dans ces trois branches a leur base commune (`caad2f0` ou
`16bf561`), donc a la version **anterieure** a `S21-4` - aucune des trois n'a touche ce fichier
depuis. Un `merge` a trois points compare chaque cote a la vraie base, pas au `round-two` actuel :
seul le cote `round-two` a change, donc git resout au contenu de `round-two` sans conflit. Confirme
apres fusion (`git show round-two:...`, section suivante) : la version `S21-4` est bien celle qui
tient.

## Ordre de fusion, et pourquoi cet ordre

1. **`s21-camille`** (`DEC-0051` amendee, `S21-3` recadree au backlog) - aucun fichier de code,
   aucune dependance.
2. **`s21-nadia`** (`S21-3` refutee, `ADR-0017` retabli par la sortie) - texte et fiches de
   conformite uniquement, prealable de lecture aux lots suivants qui s'y referent.
3. **`s21-theo`** (`S21-1`, `S21-6`, `ADR-0063`, et le correctif de garde-fou `S21-12`) - fusionne
   avant `s21-ines-2` pour la raison ci-dessous.
4. **`s21-ines-2`** (qui porte `S21-2` de `marc-dev`) - fusionne apres `s21-theo` **deliberement**.
5. **`s21-marc-2`** (`S21-9`, `cp-syllabes`).
6. **`s21-lea-2`** (`S21-10`, `cp-mot-manquant`).
7. **`s21-sofia`** (`S21-11`, `cp-comparer`, mon propre lot dev).

**Le point d'ordre signale par `theo-archi` : je ne mesure aucun etat entre 3 et 4.** Son
correctif de garde-fou (`S21-12`) rend `LActionNeDeclarePasMoinsQueLaSortieTest` rouge des que
`.proposition` (l'action) declare moins que `.lien-accueil` (la sortie) - c'est le but du
correctif. Or `.proposition` a `0.5rem` (`S21-2`, dans `s21-ines-2`) n'existe pas encore dans
`round-two` apres l'etape 3 seule : je n'ai lance aucun `mvn verify` entre les deux, precisement
pour ne pas juger un etat que personne n'a livre comme final. Le premier `mvn verify` de cette
fusion a lieu apres les sept merges, sur l'arbre complet.

## Les trois collisions certaines, resolues

**1. `catalogue.yaml`.** Trois entrees ajoutees au meme point d'insertion (fin du fichier) par
`s21-marc-2`, `s21-lea-2`, `s21-sofia` : `bandit-manchot-syllabes-cp`, puis
`bandit-manchot-mot-manquant-cp`, puis `bandit-manchot-comparer-cp`. Resolu en juxtaposant les
trois blocs dans l'ordre de fusion, chacun avec son propre `nombreDeManches: 10` - aucune perte,
aucun doublon. 21 entrees au total (18 avant, 3 neuves), verifie par comptage de `- id:` apres la
derniere fusion.

**2. `TexteAccentueExposeParLePortEntrantTest`.** Quatre points d'insertion (identifiant, titre,
consigne, competence) touches par les trois memes branches, pour la meme raison que la collision
1 : ce test a deja casse deux fois sur des editions de catalogue (s20), les trois developpeurs
l'avaient donc chacun etendu. Resolu de la meme facon, blocs juxtaposes dans l'ordre de fusion.
Chacun des trois identifiants neufs apparait exactement une fois dans le catalogue et une fois
dans l'inventaire, verifie par comptage apres la derniere fusion.

**3. `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`.** `lea-dev` et moi avons chacune
converti `Map.of` (plafonne a dix paires) en `Map.ofEntries` pour ajouter une onzieme puis une
douzieme entree, la meme conversion faite deux fois independamment sur les dix memes paires. Git a
resolu les dix premieres paires sans conflit (identiques des deux cotes) ; le conflit portait sur
la fermeture de l'appel, chaque branche fermant `Map.ofEntries(...)` apres sa seule entree. Resolu
en portant **les deux** entrees (`cp-mot-manquant` de `lea-dev`, `cp-comparer` la mienne), pas
seulement la derniere fusionnee - c'est l'erreur que le brief nommait explicitement a eviter.

## Un quatrieme conflit reel, non annonce dans le brief : le commentaire au-dessus de
`.lien-accueil`

`s21-theo` et `s21-ines-2` corrigent independamment le **meme** commentaire fautif dans
`recreo.css` (il affirmait que `.lien-accueil` n'est plus confronte a `.proposition`, faux depuis
`S15-3`/`DEC-0034`) : deux redactions differentes du meme fait, non contradictoires. `theo-archi`
cite le nom du test qui l'exige et trace le parallele avec la mauvaise citation d'`ADR-0029`
trouvee le meme jour ; `ines-design`/`nadia-prof` citent `DEC-0034` et ajoutent une phrase propre
au correctif `S21-2`/`SOR-1` a `SOR-4` (absente de la version de `theo-archi`, qui ne connaissait
pas encore ce lot au moment d'ecrire la sienne - voir l'ordre de fusion ci-dessus). Resolu par
fusion textuelle des deux : la version de `theo-archi` porte la correction, la phrase d'ajout
`S21-2`/`SOR` d'`ines-design` la suit, sans repeter deux fois le meme fait corrige. Aucune
information perdue d'un cote ni de l'autre, verifie par relecture du fichier final.

## Etat des trois valeurs CSS apres fusion complete (verifie sur le fichier, pas suppose)

| Selecteur | Valeur | Origine |
|---|---|---|
| `.lien-accueil` | `padding: 0.5rem 1.1rem` | `SOR-1`/`SOR-2`, `ines-design` |
| `.retour-accueil` | `padding: 0.5rem 1.1rem` | `SOR-3`, `ines-design` |
| `.proposition` (regle propre, apres le bloc partage a `0.75rem 1.25rem`) | `padding: 0.5rem 1.25rem` | `S21-2`, `marc-dev` |
| `.score` | `margin: 0.25rem 0` | `S21-2`, `marc-dev` |

## Porte technique : ROUGE, et voici sa composition exacte

`bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet (six modules, aucun `-pl`), lance deux
fois de suite sur `round-two` apres la septieme fusion, le 2026-08-27, meme resultat les deux
fois : **`BUILD FAILURE`**, code de sortie 1.

| Module | Bloc `Results:` |
|---|---|
| `recreo-domain` | `Tests run: 49, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-application` | `Tests run: 13, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-adapter-web` | `Tests run: 332, Failures: 2, Errors: 0, Skipped: 0` |
| `recreo-adapter-content` | **non atteint, reacteur arrete a `adapter-web`** |
| `recreo-bootstrap` | **non atteint** |

Je ne donne aucun total : 49 + 13 + 332 = 394 est un decompte des seuls modules qui ont tourne, pas
un total de sprint - `adapter-content` et `bootstrap` n'ont pas ete mesures par cette commande.
Aucun total du sprint n'est calculable tant que le reacteur ne va pas au bout.

**Les deux echecs, meme message, meme cause :**

```
LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.unRembourrageLateralAjouteSurLaChaineFaitEchouerLeGardeFou
LeConteneurTierGardeSonRembourrageLateralTest.leConteneurTierGardeSonRembourrageLateralSurLaProduction
```

toutes deux : `ce corps declare padding 2 fois (0.75rem 1.25rem | 0.5rem 1.25rem) et la lecture
porte sur la declaration de rang 1, que la derniere ecrase [...] Lire derniereDeclaration(), ou
dire ici pourquoi la valeur ecrasee est celle qui compte`.

**Root cause, trouvee par lecture du code, pas devinee.** `S21-12` (`theo-archi`) rend
`valeurDeclaree` (`TailleDesTextesEtBoutonsTest.java`, ligne 350) strict : il refuse desormais de
rendre une valeur quand la position lue n'est pas la **derniere** declaration d'une propriete dans
un corps. Ce correctif met a jour les appelants qu'il connaissait au moment ou il l'a ecrit :
`LActionNeDeclarePasMoinsQueLaSortieTest` et les helpers internes de
`TailleDesTextesEtBoutonsTest` (`proprieteEnRem`, `proprieteEnRemSiExiste`), qui lisent tous deux
via `derniereDeclaration`. Il ne met **pas** a jour un troisieme appelant du meme point de
passage partage : `lateralDeclare` (`LeConteneurTierGardeSonRembourrageLateralTest.java`, ligne
145), qui lit encore via `debutDeDeclaration` (la premiere occurrence) pour le raccourci
`padding` comme pour `padding-left`/`padding-right` explicites.

Le defaut ne pouvait pas se voir avant cette fusion : il faut **a la fois** un corps qui declare
`padding` deux fois (`.proposition`, apporte par `S21-2`/`s21-ines-2`, fusionne a l'etape 4) **et**
la lecture stricte de `S21-12` (fusionnee a l'etape 3) sur ce meme corps. Ni `s21-theo` seul
(`.proposition` n'y declare `padding` qu'une fois) ni `s21-ines-2` seul (le correctif de lecture
n'y existe pas) ne le revelent. C'est exactement le mode de defaillance qu'`ADR-0029` et
`CLAUDE.md` nomment deja : une population non couverte par une regle, ici les appelants d'un point
de passage partage plutot que les colonnes d'un rouleau.

## Obstacle route

**`lateralDeclare` non mis a jour par `S21-12`, fait echouer `mvn verify` sur `round-two` fusionne**
- route a `theo-archi`, meme famille de decision que `S21-12` (le point de passage partage
`valeurDeclaree`). Je ne le corrige pas moi-meme : la question n'est pas seulement "quelle ligne
changer" (`derniereDeclaration` a la place de `debutDeDeclaration`, pattern deja pose par
`S21-12`), c'est de decider si d'autres appelants du meme point de passage partage sont dans le
meme cas, question d'architecture qui appartient a `theo-archi`. Detail et etat dans
`planning.md`, tableau des obstacles, amendement du 2026-08-27 (seconde vague).

## Incident de conduite a consigner (le mien)

Pendant mon lot dev (`S21-11`), j'ai lance `taskkill //IM chrome.exe //F` pour arreter un
navigateur de mesure - une commande qui tue **par nom d'image**, pas par PID, exactement le geste
qui a deja ferme les navigateurs personnels de l'utilisatrice (lecon consignee dans ma memoire
d'agent). Je l'ai signale moi-meme et j'ai corrige ma conduite ensuite dans le meme lot. Ce n'est
pas une faute cachee : c'est une regle de conduite qui **ne vit que dans la memoire d'un agent**,
donc ne vaut pour aucun autre - c'est exactement la faute que la gouvernance par depot de ce
projet existe pour empecher. Je ne l'ecris pas en amendement au `squad/README.md` moi-meme : je le
porte en **proposition** pour la retro de ce sprint, ou l'equipe (moi comprise, sous ma casquette
Scrum Master, mais pas seule) tranche l'amendement.

## Ce qui reste hors du perimetre de cette fusion

- Les 4 items sans levier connu et les 122 items sur 348 encore sous le pli : hors perimetre de
  s21, routes a `camille-po` (planning, decision 4).
- `S21-3` original (12 enonces sur une ligne) : refute et recadre par `nadia-prof`, absorbe dans
  `S21-11`/`REP-11` a `REP-14` de `s21-camille` - pas une story restant a livrer telle quelle.

## Etat de `round-two` apres cette fusion

`round-two` local est a la tete des sept merges de cette seconde vague, non pousse. Sept branches
fusionnees, trois collisions certaines et une collision non annoncee resolues, **`BUILD FAILURE`**
verifie deux fois de suite avec la meme cause, un obstacle technique route a `theo-archi`. Le
sprint n'est pas clos : la porte technique est rouge, `nadia-prof` n'a prononce aucun Done
pedagogique sur `cp-syllabes`, `cp-mot-manquant` ni `cp-comparer`, et l'amendement au
`squad/README.md` sur les commandes destructives reste une proposition, pas une decision.
