# [Sprint s57] Avis de `nadia-prof` sur les deux corrections de contenu de la clôture

- **Nature** : mesure à l'écran de deux corrections décidées par l'utilisatrice (`DEC-0120`, réponse `Q2`, `O1` et `O2`, relayée par l'agent lanceur, non lue de sa main) à partir des constats 2 et 3 de `avis-nadia-cloture-s57.md`. **Je n'écris dans aucune fiche par ce geste** et je n'alloue aucun identifiant. Aucun Done n'est prononcé ni rouvert ici : les verdicts de `-03` et de `-12` restent ceux de leurs fiches.
- **Arbre mesuré** : `e75e3f7e`, qui porte `29c11e3f` (`-03`, item 11) et `180889a4` (`-12`). La relecture orthographique `57a13d26` n'est pas dans cet arbre ; elle ne touche pas le contenu servi.

## 1. Méthode

- **Jar** : construit par moi à `e75e3f7e` avec `bash outils/mvn-jdk21.sh -B -DskipTests package` (JDK 21.0.10). `ce1-qlm-generations.yaml` et `ce1-qlm-besoins-de-la-plante.yaml` extraits du jar sont égaux à ceux de l'arbre (`sha256` après retrait des retours chariot, 2 sur 2).
- **Serveur** : `java -jar` sur un JDK 21, `server.port` libre, démarré à 15:35:13, arrêté **par son PID** à 15:51:59, port libre ensuite.
- **Jeu** : même parcours que `avis-nadia-cloture-s57.md` section 4.1 : page *Questionner le monde* du CE1, tuile par son titre, Chrome sans tête piloté par un script, quatre parties par taille où **chaque manche va jusqu'à la révélation** (deux erreurs, puis un nouvel appui sur une proposition barrée), puis la bonne réponse. `-12` à 744 x 551 et à 360 x 640, `-03` à 744 x 551. **Tous les items des deux paquets ont été servis** (12 sur 12, 14 sur 14). Coupures de ligne lues caractère par caractère ; cotes du bouton et du texte par `getBoundingClientRect()` ; police réellement rendue par `CSS.getPlatformFontsForNode` sur chaque proposition, l'énoncé et la réponse nommée ; captures regardées.
- **Ajout à 360** : trois parties de plus sur `-12`, avec capture centrée sur chaque proposition à *arrière-grands-parents* et sur la réponse nommée, une fois en émulation de bureau (barre de défilement classique, 345 px de mise en page) et une fois en émulation mobile (barre superposée, 360 px de mise en page, plus proche d'un téléphone).
- **Technique, périmètre partiel** : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test` à `e75e3f7e` : `Tests run: 2571, Failures: 0`, `BUILD SUCCESS`, pour le module `adapter-content` seul. Ce n'est pas la CI : je n'ai lancé ni `mvn -B clean verify` sur la racine ni les autres jobs de `.github/workflows/ci.yml`.

## 2. `-03`, item 11 : correction tenue

- **Avant** (constat 3) : *"De quoi d'autre a-"* en fin de ligne, *"t-elle besoin pour germer ?"* à la suivante, à 744 x 551.
- **Après** : *"Une graine a déjà de l'eau. Pour germer, elle a"* / *"aussi besoin de quoi ?"*. Plus aucun mot coupé. Après une erreur, retour et indice au-dessus du pli (bas de l'indice à 408) ; à la révélation, *"Ce n'est pas ça."*, *"Appuie sur la bonne réponse :"*, *"de la chaleur"* (bas à 410) et l'indice (494), tout à l'écran. La bonne proposition reste seule non barrée.
- **Fond** : la question par intonation, *"elle a aussi besoin de quoi ?"*, est une forme orale qu'un enfant de CE1 lit et emploie ; *aussi* porte le sens de *d'autre*. Bonne réponse et distracteurs inchangés, déjà jugés.
- **Verdict** : **tenue**. Le constat 3 est levé. La fiche de `-03` n'avait pas de réserve sur ce point : rien n'est à y lever. Le Done technique reste celui que la fiche exige, la CI, que je n'ai pas lue.

## 3. `-12`, propositions à trait d'union insécable : correction partiellement tenue

**Ce que la correction supprime, aux deux tailles.** Sur les 12 items et toutes leurs propositions, **aucune coupure de ligne à l'intérieur de *grands-parents* ni d'*arrière-grands-parents*** : les retours à la ligne tombent après une virgule ou une espace. À 744, item 8 : *"arrière-grands-parents, grands-parents,"* / *"parents"*, au lieu de *"grands-"* / *"parents"*. Le glyphe s'affiche comme un trait d'union, sans carré, et la seule police rendue est Segoe UI (aucune police de secours) sur ce poste.

**À 744 x 551, rien de nouveau.** Aucun texte ne dépasse son bouton. La bonne proposition à la révélation est non barrée, lisible, seule sans `line-through`. Ce qui reste est inchangé, et ce n'était pas l'objet de la correction : item 8, après une erreur, ni croix, ni message, ni indice à l'écran (bas du signal à 648, de l'indice à 630) ; à la révélation, réponse nommée sous le pli sur les items 3 (590), 8 (710), 9 (582) et 12 (590) ; le troisième bouton de l'item 8 descend à 556.

**À 360 de large, le risque R-c d'`ines-design` est réalisé.** *arrière-grands-parents*, qui ne peut plus se couper, est plus large que le bouton :

| Items | Bouton | Texte, émulation mobile | Texte, barre classique |
|---|---|---|---|
| 1, 4, 9 (*"celle des arrière-grands-parents"*) | 20 à 340 (mobile), 20 à 325 (classique) | 10 à 350 : 10 px de chaque côté hors du bouton | 2 à 343 : 18 px de chaque côté |
| 8 (les trois propositions) | idem | 5 à 355 : 15 px de chaque côté | -2 à 347 : **le *a* initial passe sous le bord gauche de l'écran**, la virgule sous la barre de défilement, et la page devient plus large que l'écran à la révélation (361) |

Ce que l'enfant voit : le mot entier, mais dont la première et la dernière lettre débordent du bouton clair et s'impriment sur le cadre sombre de la machine, texte sombre sur fond sombre. Avec une barre de défilement classique, sur l'item 8, il lit *"rrière-grands-parents,"* : la première lettre est rognée. La bonne réponse à la révélation reste non barrée et lisible au centre, avec le même débordement sur les items 1, 4, 8 et 9 ; la réponse nommée sous la machine tient dans l'écran à un pixel près (virgule de l'item 8 à 361 pour 360).

**Verdict** : **tenue à 744 x 551, à refaire à 360 de large**. Je ne refuse pas la correction : à la taille de mes audits elle supprime le défaut sans en créer, et à 360 elle remplace une coupure au trait d'union par un débordement du bouton. Le débordement relève de la mise en page, pas du contenu : je décris le symptôme, la cause est à `theo-archi` et `ines-design`, la story à `camille-po`. Le texte du paquet n'a pas à changer pour cela.

**Conséquence sur `V1`, sans déplacer mon poteau.** `V1` exige, à 744 x 551 et à 360 : (1) aucune proposition coupée à l'intérieur d'*arrière-grands-parents* ou de *grands-parents* ; (2) aucune réponse attendue sous le pli à la révélation. La clause 1 est **tenue** aux deux tailles. La clause 2 est **non tenue** à 744 (quatre items). `V1` est donc **partiellement tenue** et reste ouverte. Le débordement à 360 n'est pas dans la lettre de `V1` : je ne l'y ajoute pas, il s'écrira dans la fiche comme une réserve nouvelle et datée. Ces deux lignes de fiche restent à écrire par moi, dans un geste qui touche la fiche.

## 4. Ce que je n'ai pas vérifié

1. Le glyphe U+2011 sur Android, iOS, macOS et Linux : la pile de polices commence par `system-ui`, donc une autre police (Roboto, San Francisco) sur ces systèmes. Rien ne dit qu'elle porte ce glyphe ; je ne l'ai pas vu.
2. La largeur du mot avec ces autres polices, donc l'ampleur du débordement à 360 ailleurs que sous Segoe UI.
3. Une vraie tablette, un vrai téléphone, un doigt : tout est joué par un script dans un navigateur sans tête. À 360, la page défile seule : mes cotes à cette largeur ne disent rien du pli.
4. La lecture du U+2011 par un lecteur d'écran.
5. La CI complète sur `e75e3f7e` (section 1, dernier point).

**Ce dont je reste la seule garde** : la nouvelle garde de `180889a4` interdit le trait d'union ordinaire entre deux lettres dans une proposition ; elle ne mesure ni la coupure à l'écran ni le débordement d'un bouton. Aucun test ne tient ce que cette page mesure.

**Signé** : `nadia-prof`, s57, clôture, 2026-10-29.
