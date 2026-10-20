# [Sprint s57] Rapport qualite de `yanis-qa` - palier 3, mutations de `recreo.css` (`CR39`, `S57-GARDE-PILE-DE-POLICES` criteres 1 et 2, `S57-GARDE-C1-VIRGULE-ET-IMBRIQUE` criteres 1 a 5)

- **Auteur** : `yanis-qa`. **Date** : 2026-10-20. **Arbre mesure** : branche `s57-yanis-mutations-css`, coupee de `s57-theo-garde-pile-de-polices` a `558dd887`, qui contient `78b260e3` (garde de la virgule et de l'imbrique) comme ancetre. Aucun test ni code produit modifie : le seul fichier ajoute est ce rapport.
- **Ce que ce fichier ne fait pas** : il ne prononce ni Done technique ni Done pedagogique. Il n'a lance que le job `build` en mesure partielle (voir Methode) ; `chemins`, `prefixes` et `outils` ne sont pas lances.
- **Verdict** : les quatre formes de la story et les cinq jetons de police (dont les deux variables derivees), les trois formes de la virgule et l'imbrique rougissent chacune sur la feuille reelle, avec le jeton dans le message. Les deux clauses vertes restent vertes. **Un trou trouve** (ME8, ME9) : un identifiant ecrit avec un echappement CSS passe le garde de polices.

## Methode

- **Commande, perimetre et JDK** : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test` (perimetre `adapter-web` et ses amonts, `-am` present). Machine virtuelle des tests : `java.version` 21.0.10, lu dans le rapport Surefire.
- **Base, sans mutation** : 120, 31 et 516 tests, 0 echec, `BUILD SUCCESS`.
- **Mutation** : une ligne ajoutee en fin de `adapters/adapter-web/src/main/resources/static/css/recreo.css`, une seule a la fois, puis `git checkout` du fichier. `git diff --stat` vide verifie apres chacune des 28 mutations.
- **Deux series** : les series MP et MC sont mesurees sur la **commande complete** (516 tests). La serie ME l'est sur **une classe** (`-Dtest=LaPileDePolicesServieNeChangePasEnSilenceTest -Dsurefire.failIfNoSpecifiedTests=false`, 8 tests) : elle dit ce que le garde de polices voit, pas ce que le module entier verrait.
- **Lecture des "2 echecs"** : sur la feuille reelle, un test de clause verte lit la meme feuille et rougit avec le test principal (`uneTailleUneGraisseEtUneVariableMaisonRestentVertes` pour les polices, `uneListeDeClassesDuMessageSousSonSommetResteVerte` pour la virgule). Deux echecs valent **une** detection ; ce ne sont pas deux detecteurs independants.
- **Tests instables** : aucun constate, aucun test relance pour obtenir un resultat. Chaque ligne ci-dessous est une mesure unique.

## 1. Garde de polices, commande complete (criteres 1 et 2)

Test principal attendu : `LaPileDePolicesServieNeChangePasEnSilenceTest.niLaFeuilleMaisonNiUnGabaritNeChangentLaPileDePolices`. Le message commence par la motivation de `DEC-0122` 3.5, puis `Expecting empty but was:` et la liste.

| Mutation | Regle ajoutee | Resultat (516 tests) | Jeton dans le message |
|---|---|---|---|
| MP1 (critere 1) | `body { font-family: Georgia, serif; }` | 2 echecs : le test principal et la clause verte | `recreo.css : body : declare font-family: Georgia, serif` |
| MP2 (critere 2) | `.consigne { font: 1rem Georgia, serif; }` | 2 echecs, memes tests | `.consigne : declare font: 1rem Georgia, serif` |
| MP3 (critere 2) | `:root { --bs-body-font-family: Georgia, serif; }` | 2 echecs, memes tests | `:root : declare --bs-body-font-family: Georgia, serif` |
| MP4 (critere 2) | `@font-face { font-family: Zz; src: local(Arial); }` | 2 echecs, memes tests | `recreo.css : declare une regle @font-face` |
| MP5 (variable derivee, hors story) | `:root { --bs-font-sans-serif: Georgia, serif; }` | 2 echecs, memes tests | `:root : declare --bs-font-sans-serif: Georgia, serif` |
| MP6 (clause verte) | `.consigne { font-size: 1.25rem; font-weight: 700; --teinte-font: red; }` | **0 echec** | sans objet |

La mesure de `camille-po` sur `78270b4c` portait sur les quatre formes ensemble (501, 0 echec) ; elles sont ici isolees une a une, et chacune rougit seule.

## 2. Garde de la virgule et de l'imbrique, commande complete

Test principal attendu pour la virgule : `UnEcranNeGouvernePasLApparenceDUnAutreTest.aucuneRegleDontLeSujetEstHorsDuMessageNeNommeCeQueSeulLeMessagePose` ; pour l'imbrique : le refus du lecteur partage `reglesDe`.

| Mutation | Regle ajoutee | Resultat (516 tests) | Message |
|---|---|---|---|
| MC1 (temoin sans virgule) | `.ecran-de-jeu:has(.est-faux) .propositions { row-gap: 0; }` | 2 echecs : test principal et clause verte | `le sujet de ce selecteur est hors du message de templates/fragments/manche.html ... [la classe "est-faux"]` |
| MC2 (critere 1) | `.ecran-de-jeu:has(.consigne, .est-faux) .propositions { row-gap: 0; }` | 2 echecs, memes tests | meme message, selecteur complet cite, classe `est-faux` |
| MC3 (critere 2) | `.ecran-de-jeu:is(.consigne, .est-faux) .propositions { row-gap: 0; }` | 2 echecs, memes tests | meme message, classe `est-faux` |
| MC4 (critere 3) | `:has(.consigne, .message-jeu)` | 2 echecs, memes tests | classe `message-jeu` |
| MC5 (critere 3) | `:has(.consigne, .signal)` | 2 echecs, memes tests | classe `signal` |
| MC6 (critere 3) | `:has(.consigne, .est-juste)` | 2 echecs, memes tests | classe `est-juste` |
| MC7 (critere 4) | `.ecran-de-jeu { &:has(.est-faux) .propositions { row-gap: 0; } }` | **49 echecs**, 11 classes de test | `regle imbriquee, non lue par les garde-fous de la feuille ... (ADR-0031, amendement du 2026-10-20)`, la regle et donc `.est-faux` citees |
| MC8 (critere 5 b, clause) | `.ecran-de-jeu .message-jeu:has(.est-faux, .est-juste) .indice { color: inherit; }` | **0 echec** | sans objet |
| MC9 (critere 5 c, amende) | `.ecran-de-jeu { & .message-jeu .signal.est-faux { color: inherit; } }` | **49 echecs**, memes 11 classes | meme refus, contient `regle imbriquee, non lue par les garde-fous de la feuille` |

MC4 a MC6 completent le critere 3 de la story (les quatre noms en seconde position) avec `est-faux` mesure en MC2.

## 3. Variantes d'ecriture, garde de polices seul (8 tests)

Test principal attendu : le meme que section 1. "2 echecs" = test principal et clause verte, comme ci-dessus.

| Mutation | Regle ajoutee | Resultat | Ce que ca dit |
|---|---|---|---|
| ME1 | `:root { --bs-btn-font-family: Georgia, serif; }` | 2 echecs, jeton cite | variable derivee tenue |
| ME2 | `code { --bs-font-monospace: Georgia, serif; }` | 2 echecs, jeton cite | variable derivee tenue |
| ME3 | `BODY { FONT-FAMILY: Georgia; }` | 2 echecs | casse tenue |
| ME4 | `@media (min-width: 1px) { body { font-family: Georgia; } }` | 2 echecs | regle sous `@media` lue |
| ME5 | `@media (min-width: 1px) { @font-face { font-family: Zz; src: local(Arial); } }` | 2 echecs | `@font-face` imbrique sous `@media` lu |
| ME6 | `body{font-family:Georgia}` | 2 echecs | sans espace ni point-virgule |
| ME7 | `body { font : 1rem Georgia; }` | 2 echecs | espace avant les deux points |
| ME8 | `body { \66ont-family: Georgia; }` | **0 echec, vert** | trou : voir section 4 |
| ME9 | `@\66ont-face { font-family: Zz; src: local(Arial); }` | **0 echec, vert** | trou : voir section 4 |
| ME10 | `button { font-family: inherit; }` | 2 echecs | rouge sur un idiome legitime : voir section 4 |
| ME11 | `.consigne { letter-spacing: 0.3em; word-spacing: 0.5em; font-stretch: 150%; font-size-adjust: 0.6; }` | 0 echec, vert | limite nommee par l'ADR, confirmee sur cette classe |
| ME12 | `@supports (display: grid) { body { font-family: Georgia; } }` | 2 echecs | regle sous `@supports` lue |
| ME13 | `:root { --bs-body-font-family: Georgia, serif; --bs-font-sans-serif: var(--bs-body-font-family); }` | 2 echecs, les deux variables citees | |

## 4. Ce que ces mutations montrent que `theo-archi` n'a pas mesure

1. **Un echappement CSS passe le garde de polices (ME8, ME9).** `\66` est `f` en CSS : l'identifiant `\66ont-family` designe `font-family`, et `@\66ont-face` designe `@font-face`. Le garde compare le texte de la propriete et de l'en-tete, pas leur forme decodee, donc les deux restent verts sur la classe. **Non mesure** : le rendu dans un navigateur (je n'ai pas lance Chrome ; l'equivalence vient de la grammaire des identifiants CSS, pas de mon observation) et la suite complete du module sur ces deux lignes. A trancher par `theo-archi` : decoder les echappements dans le lecteur partage, ou nommer la limite dans `ADR-0054` avec son declencheur. Aucun test n'existe aujourd'hui sur cette forme.
2. **Le garde refuse `font-family: inherit` (ME10).** C'est ce que l'`ADR` decide (toute declaration `font-family` ou `font`), mais `button { font-family: inherit; }` est l'ecriture usuelle pour qu'un bouton reprenne la pile du texte et ne change aucune pile. Le message ne le distingue pas d'une police declaree. Aucune regle de la feuille actuelle ne l'ecrit ; le cout tombe sur la premiere regle legitime de ce type.
3. **L'imbrique rougit tout le module, pas une classe (MC7, MC9).** `theo-archi` mesure `9 echecs` sur une classe de 14 tests (`V5`, `V7` d'`ADR-0031`). Sur `adapter-web` entier : 49 echecs dans 11 classes, parce que le refus est leve par le lecteur partage `reglesDe`. Les deux chiffres sont justes pour leur perimetre ; le second dit qu'une regle imbriquee ecrite pour un motif reel bloque 11 classes, dont `CabinetDeLaMachineTest` et `ViewportEtLargeurTelephoneTest`, et que le message est le meme partout.
4. **Les "2 echecs" ne sont pas deux detecteurs** (voir Methode). `V3` et `V4` d'`ADR-0031` disent "2 echecs" sans le dire ; le chiffre est exact, sa lecture "deux gardes independants" serait fausse.
5. **Rien de plus** sur les mutations de la story : les criteres 1 et 2 de `S57-GARDE-PILE-DE-POLICES` et 1 a 5 de `S57-GARDE-C1-VIRGULE-ET-IMBRIQUE` se reproduisent comme `theo-archi` et `camille-po` les ont ecrits.

## 5. Non mesure

- Le garde lui-meme n'a pas ete mute (le desactiver pour voir `501, 0 echec` redevenir vert) : la mesure de `camille-po` sur `78270b4c` l'etablit sans lui, je ne l'ai pas refaite.
- Les mutations de gabarit (critere 4 de la story de polices) : hors de ce que `CR39` m'attribue, couvertes par `unePoliceDansUnStyleDeGabaritFaitEchouerLeGardeFou` et `unePoliceDansUneBaliseStyleDeGabaritFaitEchouerLeGardeFou`, que je n'ai pas mutes.
- Les jobs `chemins`, `prefixes`, `outils` de `ci.yml`, et un `mvn -B clean verify` complet (d'autres agents compilent).
- Un navigateur, une tablette.

## Done

- **Done technique** : non prononce ici. Cette mesure est partielle.
- **Done pedagogique** : aucun propre a ces stories (transverses), et ce fichier n'y touche pas.
