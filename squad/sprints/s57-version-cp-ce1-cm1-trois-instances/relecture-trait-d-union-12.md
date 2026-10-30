# [Sprint s57] Relecture du trait d'union insecable de `ce1-qlm-generations` (`relecteur-pedagogique`)

- **Ecrit par** : `relecteur-pedagogique`, le 2026-10-29. Commit relu : `180889a4` (`marc-dev`, branche `s57-cl-marc-12nb`). Relecture dans `s57-cl-rel-12nb`.
- **Ce que ce fichier ne fait pas** : il ne juge ni le sens ni le niveau scolaire des propositions, ne prononce aucun Done, ne corrige ni le yaml ni le test.

## Verdict

**1 defaut, de forme 1 (renvoi qui ne resout pas).** Les trois autres controles sont sans defaut.

## Defaut

**D1 - `DEC-0120, Q2 O1` ne resout pas.** Le commit, la Javadoc et le message d'assertion citent `DEC-0120, Q2 O1` comme motif de la regle "trait d'union insecable dans une proposition".
- `PaquetCe1QlmGenerationsTest.java:223` (Javadoc), `:231` (message d'assertion), `:980` (`hasMessageContaining`), et le titre et le corps du commit `180889a4`.
- Verifie : `DEC-0120` (`squad/decisions/produit/DEC-0120-*.md`) ne contient aucune occurrence de "O1" associee a un trait d'union, une cesure ou une coupure, ni de `U+2011`. Les `Q2` du fichier (lignes 628, 822, 2419) sont d'autres questions. Aucun fichier de `squad/sprints/s57-*` ne porte non plus cette decision (la seule mention proche, `RS8`, `DEC-0120` ligne 4816, est une mesure de cesure a 744 x 551, pas un choix de caractere).
- Un renvoi qui pointe vers rien est un defaut au meme titre qu'un chiffre sans methode (`DEC-0062`, forme 1). Si la decision a ete prise de vive voix, elle n'existe pas dans le depot. A router vers `marc-dev` (qui a ecrit le renvoi) et `sofia-sm` : donner la section de `DEC-0120` (ou l'amendement a ecrire) qui porte "Q2 O1". Le message d'assertion et `hasMessageContaining` devront suivre la reference corrigee, ensemble, sinon le test fautif casse.

## Controles sans defaut

1. **Orthographe des propositions.** Le yaml relu apres le commit, une fois `‑` remplace par `-`, est **identique octet pour octet** au yaml de `1a31d632` (comparaison par script Python `-I`). Aucune lettre, aucun accent ("arriere" porte son accent grave), aucune virgule ni espace de suite n'a bouge. Les 10 lignes changees : 83, 84, 97, 98, 105, 119, 132, 133, 139, 140.
2. **Lecture par un parseur.** `yaml` n'est pas installe sur le poste. Lecture de substitution : chaque valeur de `bonneReponse` et de `distracteurs` est une chaine entre guillemets doubles, lue par `json.loads` (meme regle d'echappement `\uXXXX` que YAML double-quote). Resultat : 13 propositions contiennent U+2011 (`'grands‑parents'`), aucune ne contient la sequence litterale (barre oblique inverse puis `u`), aucune ne garde de trait d'union ordinaire entre deux lettres. Les enonces et indices ne portent aucun trait d'union, comme le dit le commit. Limite : un parseur YAML reel n'a pas ete lance ; la regle d'echappement des chaines entre guillemets doubles est la meme.
3. **Commentaires et messages ajoutes dans le test.** Javadoc de `plat()` (215) et de la garde (223-224) : claires, disent le pourquoi (coupure de ligne), ne paraphrasent pas le code. Tirets simples, guillemets droits, pas de chemin absolu. Le test ecrit `U+2011` dans les commentaires et `'‑'` seulement dans le code (ligne 218), jamais une sequence `\u` dans un commentaire. Les identifiants (`verifierQueAucunTraitDUnionOrdinaireNeCoupeUneProposition`, `aucunePropositionDuPaquetNePorteDeTraitDUnionOrdinaire`, `leJeuFautifEstRefuseQuandUnePropositionPorteUnTraitDUnionOrdinaire`) et le message de commit sont en ASCII (`git log -1 --format=%B` : 0 octet non ASCII hors le `Co-Authored-By`, lui aussi ASCII). Les seuls caracteres non ASCII ajoutes par le diff sont les accents des propositions du yaml et le `'’'` deja present ligne 218.
4. **Exactitude contre le code.** Le contre-exemple `contreexemple/PaquetCe1QlmGenerationsTest/contenu/generations-fautives.yaml` porte des "grands-parents" avec trait d'union ordinaire (lignes 23, 24, 43, 53, 73, 74, 78, 79) : le test fautif a bien de quoi lever l'erreur. La regex `\p{L}-\p{L}` rejette exactement ce que la Javadoc nomme.

## Remarque, non comptee en defaut

La Javadoc de la garde dit "jamais le trait d'union ordinaire" ; la regex ne cible que le trait entre deux lettres. Equivalent pour les propositions actuelles. Si une proposition future porte un trait d'union avant un chiffre, la garde ne la verra pas : a `theo-archi` si cela merite une garde plus large.
