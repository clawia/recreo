# [Sprint s55] Rapport de lea-dev - S55-ART-CP-10-MECANIQUE (T2, T3) et S54-PONCT-GN-INDICES

- Branche `s55-lea-mecanique`, base `14455b56` (`git log -1 --format=%h` au depart, `round-two`). `bash outils/verifier-lignee.sh` : "descend de la lignee de `round-two`" (au depart et a la fin).
- Trois commits `[LEA]`, un par poste, dans l'ordre demande. Trailer `Co-Authored-By: Claude Sonnet 5.5` (modele execute, `DEC-0110` 17.4). Aucun rebase, aucune fusion, aucun push.
- **JDK rapporte par Surefire** (`java.version` des `TEST-*.xml`) : **21.0.10**. Enveloppe `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet.
- Aucun mot lu par l'enfant n'est de ma main : `Choisir cette œuvre` (M5) et `C'est cette œuvre qui va avec. Appuie dessus.` (M4) sont copies de `ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md` 3.2 ; `alt=""` est la decision de nadia-prof (Q-Y14, message du coordinateur). Aucun paquet branche, aucune entree de catalogue : **l'enfant ne voit rien**. `ART-CP-10` reste `non couvert`. Aucun Done prononce.

## Commits

| Poste | Commit | `clean verify` avant le commit |
|---|---|---|
| T2 | `dfd2f107` | BUILD SUCCESS |
| T3 | `363793e3` | BUILD SUCCESS |
| `S54-PONCT-GN-INDICES` | `08cb05fd` | BUILD SUCCESS |

**T3 est livree.** Elle l'est avec des ecarts a la proposition d'`ines-design` (section "Ecarts"), tous imposes par un garde-fou existant rouge, aucun garde-fou affaibli.

## Delta de tests (base `14455b56`, perimetre : reacteur complet, phase `test`)

| Module | Base | T2 | T3 | PONCT |
|---|---|---|---|---|
| domain | 120 | 0 | 0 | 0 |
| application | 18 | 0 | 0 | 0 |
| adapter-web | 402 | 0 | **+5** | 0 |
| adapter-content | 1415 | **+4** | 0 | 0 |
| bootstrap | 312 | **+11** | **+10** | 0 |
| **Delta par poste** | | **+15** | **+15** | 0 |

Delta cumule **+30** sur `14455b56` (2267 en phase `test` a la base, 2297 a `08cb05fd`) ; Failsafe : 6 avant, 6 apres. Ce sont des mesures dans mon worktree, pas un total d'arbre fusionne : `sofia-sm` etablit le total.

## Poste 1 - T2 (`dfd2f107`)

Fichiers : `LIllustrationDeContenuResoutEtAucuneNeDortTest` (garde-fou 1), `UnItemAPropositionsEnImageTientSesTroisInvariantsTest` (neuf, garde-fou 2), `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest` (garde-fou 3), trois jeux fautifs sous `contreexemple/`. Aucune ligne de production.

- **Garde-fou 1.** `verifier` gagne une surcharge a cinq arguments (l'ancienne delegue : aucune assertion existante modifiee). Une proposition `oeuvre-` est jugee comme une illustration (alphabet, resolution dans `static/oeuvres/`) et compte pour "aucune ne dort".
- **Garde-fou 2.** Trois clauses, perimetre derive (tout item des repertoires de contenu dont une proposition porte le prefixe). Population reelle **exactement 0** (`ITEMS_A_PROPOSITIONS_EN_IMAGE = 0`, forme d'`ADR-0051`), a relever dans le commit du premier paquet.
- **Garde-fou 3.** `textesDUneQuestion` (extrait de `textesServis`) declare `luParLEnfant = false` les propositions d'un item dont toutes les propositions portent le prefixe.
- Population de propositions declarees non epinglee a part : une seule constante (0 item) la couvre.

### Morsures T2 (rejouees par script, le fichier remis a l'etat apres chaque mutation, `git status --short` ne montre que les fichiers voulus)

Commande : `mvn -pl adapters/adapter-content,bootstrap -am test -Dtest=<3 classes>` par mutation. Ecart d'abord a lire : mon premier lancement du script, sans le chemin complet de `bash`, a pris le `bash` de WSL, a rendu une sortie vide et affiche "AUCUN" partout ; ce faux resultat est ecarte, les lignes ci-dessous sont celles du second lancement.

| Mutation (colonne "Changement" d'`ADR-0104`) | Rouge |
|---|---|
| G1a une proposition ne compte plus pour "aucune ne dort" | `LIllustrationDeContenuResoutEtAucuneNeDortTest.uneOeuvreServieSeulementEnPropositionNEstPasDormante` |
| G1b une proposition `oeuvre-inconnue` n'est plus rouge | `...unePropositionEnOeuvreInconnueFaitEchouerLeGardeFou`, `...leJeuFautifAPropositionsEnImageEstRefuse` |
| G1c alphabet d'une proposition non verifie | `...unePropositionQuiEstUnCheminFaitEchouerLeGardeFou`, `...leJeuFautifAPropositionsEnImageEstRefuse` |
| G2-1 "tout ou rien" retire | `UnItemAPropositionsEnImageTientSesTroisInvariantsTest.unePropositionEnTexteParmiDesReproductionsFaitEchouerLeGardeFou`, `...leJeuFautifCommiteEstRefuseSurSesTroisClauses` |
| G2-2 "la reference existe" retiree | `...uneReferenceAbsenteOuQuiNEstPasUneReproductionFaitEchouerLeGardeFou`, `...leJeuFautifCommite...` |
| G2-3 "reference hors du choix" retiree | `...uneReferenceQuiEstUnePropositionFaitEchouerLeGardeFou`, `...leJeuFautifCommite...` |
| G3 l'exemption `luParLEnfant` retiree | `LeTexteServi...Test.lesPropositionsDUnItemEnImageNeSontPasLuesParLEnfant`, `...unPaquetCommiteAPropositionsEnImagePasseLeGardeFouDAccent` |

G1 et G2 mutent un item **reel** (le premier item du depot qui montre une reproduction, derive, aujourd'hui de `cp-decrire-une-oeuvre`) en memoire. G3 : la morsure "un identifiant lu par l'enfant rougit le garde-fou d'accent sur `oeuvre-champ-de-ble`" est `unIdentifiantDOeuvreLuParLEnfantFaitRougirLeGardeFouDAccent` (elle appelle le garde-fou d'accent avec le booleen force a vrai ; elle ne rougit pas sous la mutation G3, qui est portee par les deux tests ci-dessus). Non mutee : le lexique du garde-fou d'accent lui-meme.

## Poste 2 - T3 (`363793e3`)

- `PartieController` : constante et fonction `propositionsEnImage(List<String>)` (vrai si et seulement si toutes les propositions portent le prefixe, liste non vide), attributs de modele `propositionsEnImage` et `mancheRevelee` (le booleen que `ines-design` a demande), `reponseRevelee` a `null` quand les propositions sont des images, `reponseAttendueVisible = !juste && !reponseEnImage` et `bonneReponse` a `null` a l'ecran de fin quand la reponse est une reproduction. La branche saisie (ligne 686) est inchangee.
- `manche.html` : une image par bouton (`alt=""`, source `'/oeuvres/' + ${proposition} + '.jpg'`), `aria-label="Choisir cette œuvre"` (M5), phrase M4 a la revelation, `ecran-deux-oeuvres` sur la racine, `propositions-oeuvres` sur la rangee, `data-oeuvre-revelee` sur la seule bonne oeuvre. Le gabarit ne lit jamais le prefixe d'une proposition.
- `recreo.css` : bloc "ADR-0104" (99 lignes) repris de la proposition d'`ines-design`, avec les ecarts ci-dessous.
- Tests : `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest` (bootstrap/rendu, garde-fou 4, 10 tests), `PartieControllerPropositionsEnImageTest` (adapter-web, 5 tests), `RessourcesTiercesTest` (`hasSize(3)` -> `hasSize(4)`), `TexteAccentueDesGabaritsTest` (inventaire : la phrase M4, avec sa reference).

### Criteres de la story

| Critere | Etat |
|---|---|
| 1 | `grep -c "oeuvres/' + \${proposition}" manche.html` : **0 -> 1**. `grep -c 'th:text="${proposition}"'` : 1 -> 1 (la branche des jeux en texte, sous `th:unless`). Le juge est le garde-fou 4 (page rendue), pas le `grep` |
| 2 | Quatre ecrans joues par le vrai controleur et le vrai gabarit : zero `oeuvre-` en noeud de texte, `alt`, `aria-label`, `title` ; aucun `.reponse-attendue` ; nom accessible non vide et egal a M5 |
| 3 | `grep -cE '^\s*@Test\s*$' PartieControllerTest.java` : 41 -> 41 (fichier non touche). `PartieControllerPropositionsEnImageTest` rejoue un jeu en texte (aucune marque du dispositif, `.reponse-attendue` toujours rendu a la revelation) |
| 4 | Garde-fous 1 a 3 : section T2. Garde-fou 4 : morsures ci-dessous |
| 5 | `RessourcesTiercesTest` : `hasSize(3)` 1 occurrence -> 0 ; `hasSize(4)` pour cette methode. **Ecart** : la methode s'appelait `troisReferences...`, je l'ai renommee `quatreReferences...` (un nom "trois" sur `hasSize(4)` mentirait) ; `sed -n '709,730p'` la trouve toujours |
| 6 | Mots et mise en page existent avant le rendu : `git grep -l "Choisir cette œuvre" -- squad/conformite` rend `ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md` ; `git merge-base --is-ancestor 4893771b HEAD` sort 0. Points 3 et 4 d'`ADR-0104` seuls |
| 7 | `git diff round-two --name-only` filtre `^(domain\|application)/\|adapter-content/src/main/java\|bootstrap/src/main\|pom.xml\|^.github/` : **aucune ligne**. `ArchRule` **39**, `@ArchTest` **36** (inchanges, methode d'`ADR-0104`) |
| 8 | Aucun `catalogue.yaml`, aucune `MATRICE-couverture.md`, aucun fichier de `adapters/adapter-content/src/main/resources/contenu/` dans T2 ni T3 (le seul fichier de contenu du lot est `cm1-grands-nombres.yaml`, poste `PONCT`) |
| 9 | `clean verify` vert sur la racine avant chaque commit, jobs nommes en fin de rapport |

### Morsures du garde-fou 4 et de T3 (rejouees : mutations de `PartieController`/`manche.html` par script ; celles du gabarit sont des tests, sur le gabarit reel mute en memoire par un resolveur de gabarit)

| Mutation | Rouge |
|---|---|
| gabarit : l'image remplacee par `th:text="${proposition}"` | `LesPropositionsEnImage...Test.leGabaritMuteEnThTextPropositionFaitEchouerLeGardeFou` ("ecrit dans un noeud de texte") |
| gabarit : `alt` = identifiant | `...leGabaritMuteAvecLIdentifiantEnAltFaitEchouerLeGardeFou` |
| **gabarit : `alt` qui decrit une oeuvre (Q-Y14)** | `...leGabaritMuteAvecUnAltQuiDecritUneOeuvreFaitEchouerLeGardeFou` ; l'`alt` vide est imposee par le juge (`defautsDe`), sur chaque image, chaque item, chaque ecran de manche |
| gabarit : nom accessible retire | `...leGabaritMuteSansNomAccessibleFaitEchouerLeGardeFou` |
| gabarit : `.reponse-attendue` rendu a la revelation | `...leGabaritMuteQuiRendLaReponseEnTexteALaRevelationFaitEchouerLeGardeFou` |
| gabarit : lecture du prefixe par proposition | `...leGabaritNeLitJamaisLePrefixeDUneProposition` (clause sur la source du gabarit reel) |
| controleur C1 : `reponseEnImage` toujours faux | `...chaqueEcranDeChaqueItem...`, `PartieControllerPropositionsEnImageTest.lEcranDeFinNeRendJamaisLIdentifiantDeLaBonneReponse` |
| C2 : la revelation pose la reponse en texte | `...chaqueEcranDeChaqueItem...`, `PartieControllerPropositionsEnImageTest.laRevelationNePoseAucuneReponseEnTexteEtMarqueLaBonneOeuvreSeule` |
| C3 : `mancheRevelee` toujours faux | idem |
| C4 : `allMatch` -> `anyMatch` | `PartieControllerPropositionsEnImageTest.uneRangeeMelangeeDeTexteEtDeReproductionsResteUneRangeeDeBoutonsTexte` seul (le jeu de preuve est homogene) |
| T1 : `ecran-deux-oeuvres` retiree | `...chaqueEcranDeChaqueItem...`, `PartieControllerPropositionsEnImageTest.uneMancheDontToutesLesPropositions...` |
| T2 : phrase M4 retiree | `...chaqueEcranDeChaqueItem...`, `PartieControllerPropositionsEnImageTest.laRevelationNePose...` |

Perimetre du garde-fou 4 : jeux reels du catalogue dont un item a toutes ses propositions en images (**vide a s55**, assertion exacte), plus un jeu de preuve de trois items **reels** (enonce, indice, explication, texte alternatif lus par le chargeur de production) dont la reference et les propositions sont mutees en memoire. Le vrai controleur, le vrai gabarit et la vraie traversee HTTP servent.

## Ecarts

Ce que j'ai fait a la place d'une chose demandee, et pourquoi.

1. **E1. Classes d'`ines-design`.** `.proposition-oeuvre` et `.oeuvre-revelee` ne sont pas posees. `LEcranDeJeuDeclareCommentIlEstArriveTest` n'admet qu'un litteral choisi selon `${propositionsEssayees}` sur le bouton, et `TailleDesTextesEtBoutonsTest` refuse toute concatenation de classes. Les regles se selectionnent par `.propositions.propositions-oeuvres .proposition` (meme specificite, posees apres les teintes cycliques) et la marque de revelation par `[data-oeuvre-revelee=true]`. `ines-design` en juge.
2. **E2. Anneau de la revelation.** La proposition posait un anneau blanc **hors de la case** (`outline` sur le bouton). Un `outline` ou un `border` declares sur `.proposition.deja-essayee` font naitre cinq exigences dans la dette d'etats d'`ADR-0046`, dont le plafond est vide et ne monte pas. L'anneau (encre, 0,25 rem) est pose **sur l'image**, dans le rembourrage de la case ; le blanc ne se verrait pas sur le fond clair de la case. Le cadre de la case est de meme porte par l'image.
3. **E3. Etat d'echec.** La proposition ajoutait un cadre en tirets. Meme cause qu'E2 : il est abandonne, **seule l'attenuation a 50 % reste**. Le canal de forme de `M3` est donc a decider (`ines-design`, `nadia-prof`).
4. **E4.** `:active` propre retire (`ADR-0053` : il recouvrait la couleur de position) ; `display: none` du voile remplace par `content: none` (`ADR-0026`) ; teinte blanche remplacee par `var(--recreo-accent-texte)` (palette nommee).
5. **E5. Grille "cote".** Reprise sans le `data-disposition` du prototype, sous `.ecran-deux-oeuvres`. La cellule du meuble est designee par `div:has(> .propositions-oeuvres)` et non `.cabinet`, parce que la chaine `.cabinet {` est l'ancrage de deux morsures de `CabinetDeLaMachineTest` et `LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest` (rouges a deux occurrences).
6. **E6. Meuble.** Conserve (non tranche : `theo-archi` et `nadia-prof`, proposition 2.5).
7. **E7. `alt`.** La decision de nadia-prof (Q-Y14) me vient du message du coordinateur, qui cite `FICHE-bandit-manchot-principes-republique-cm1.md` et `audit-nadia-b-emc.md` sur `round-two` `5f8c0510`. **Ces fichiers ne sont pas dans mon arbre (base `14455b56`) : je ne les ai pas lus.**
8. **E8.** Le critere 1 de la story est sur le gabarit ; un commentaire qui contiendrait la chaine le satisferait (`yanis-qa`, lecture de testabilite 2.8) : le juge est le test de rendu.
9. **E9. Critere insatisfiable.** Aucun rencontre sur les criteres 1 a 9 tels que corriges. Le critere 6 reste borne aux points 3 et 4 ; les points 2 et 5 se tranchent sur un paquet.

## Non verifie

- **Rien n'a ete joue sur le jar.** Aucun paquet n'est au catalogue : aucun ecran de ce dispositif ne peut etre rendu par l'application (`proposition-ines`, section 9). Le rendu a ete vu par jsoup sur le HTML, jamais a l'oeil : mise en page, marque de revelation, tenue a 176 px CSS, absence de defilement (`M1`), pli a 551 px, `X2`, `X3`, `X5` (second cas), `X6`.
- La grille "cote" : `.bloc-adulte`, `.texte-support`, `.tableau-support` (enfants de `.ecran-de-jeu` hors des zones nommees) ne sont pas places par la grille ; non mesure, aucun item d'`ART-CP-10` n'en porte.
- Le releve a dix largeurs de la proposition d'`ines-design` n'est pas refait : le prototype et le gabarit reel different par les ecarts E1 a E5.
- La lisibilite de la phrase M4 et du nom M5 par un lecteur d'ecran : non ecoute.
- Les mutations de `yanis-qa` : non rejouees par lui, il les rejoue.
- `S54-PONCT-GN-INDICES` : la relecture de ponctuation par `relecteur-pedagogique` n'est pas faite.

## Poste 3 - `S54-PONCT-GN-INDICES` (`08cb05fd`)

- Fichiers : `cm1-grands-nombres.yaml` (12 lignes), `AucunSigneDoubleNEstDetachableDeSonMotTest` (ligne `Map.entry("cm1-grands-nombres", 12)` retiree, `DETTE_TOTALE` 502 -> 490, commentaire de remesure). La version de la table est celle de **camille-po** (Q-Y3) : l'entree est retiree, pas mise a 0.
- `grep -cP '^ *indice: ".* [:;!?]'` sur le YAML : **12 -> 0**. Avec `\x{00A0}` a la place de l'espace : **0 -> 12**. Les deux avec le `grep` GNU (`/usr/bin/grep`).
- **Relecture caractere par caractere** : apres remplacement de U+00A0 par l'espace ordinaire dans les deux versions, `diff` de la version avant et apres est vide. Les accents des 12 lignes sont donc intacts. Un premier essai a perdu le signe de ponctuation (une erreur de mon gabarit de remplacement) ; je l'ai vu au `diff`, restaure depuis la copie, et refait ; seul le second est commite.
- Morsures, quatre etats rejoues (`AucunSigneDoubleNEstDetachableDeSonMotTest.leCliquetDeLaDetteDeTyp1EstExact`, 5 tests, `-pl adapters/adapter-content -am`) : table inchangee (12, 502) **rouge** ("la table declare 12 occurrences pour cm1-grands-nombres, qui n'en porte plus aucune") ; entree a 0 et total 490 **rouge** ("declare 0") ; entree retiree et total 502 **rouge** ("le total declare ne suit plus la table") ; une espace ordinaire remise dans un indice **rouge** ; entree retiree et total 490 : **vert**, 5 tests.

## Jobs de `.github/workflows/ci.yml`

| Job | Lance ? | Detail |
|---|---|---|
| `build` | **oui** | `bash outils/mvn-jdk21.sh -B clean verify`, racine, avant chaque commit : trois BUILD SUCCESS, JDK 21.0.10 |
| `chemins` | **oui** | `verifier-chemins-test.sh` (code 0), `verifier-chemins.sh` sur les fichiers de chaque commit et sur `git ls-files` (aucun chemin de poste) |
| `prefixes` | **oui** | `verifier-prefixes-test.sh` (0), `commit-atteignable-test.sh` (0), `verifier-prefixes.sh` sur les sujets des trois commits (0) |
| `outils` | **non** | aucun outil touche |

## Pour sofia-sm

- Le plancher d'`ADR-0051` : au premier paquet a propositions-images, relever `ITEMS_A_PROPOSITIONS_EN_IMAGE` (garde-fou 2) et le "vide" du garde-fou 4 dans le meme commit.
- `T4` (trois reproductions, constantes 8 -> 11 et 176 474 -> 250 915) : non touchee, voyage avec le paquet.
- Deux fichiers de gabarit/feuille et deux nouveaux `contreexemple/` sont a fusionner avec `ines-design` si elle ajuste E1 a E3.
