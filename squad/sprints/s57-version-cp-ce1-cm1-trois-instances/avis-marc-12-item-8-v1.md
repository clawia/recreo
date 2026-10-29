# [Sprint s57] Avis de `marc-dev` : item 8 de `ce1-qlm-generations`, reserve `V1` de `QLM-CE1-12`

Sources lues : `ce1-qlm-generations.yaml` (item 8, tache ordre, moitie generations), `PaquetCe1QlmGenerationsTest.java` (`verifierLesSuites`, `verifierLesGroupes`, `verifierLesDeuxMoitiesEnBonneReponse`, `verifierLesDeuxTachesEtLeursComptes`), constat 4.2 et 4.3 (points 1 et 2) de `avis-nadia-cloture-s57.md`. Aucune de ces options n'est lancee : les effets ci-dessous sont lus dans le code du test, pas mesures. Le yaml n'est pas modifie par ce geste.

## 1. Etat mesure

Item 8 : enonce de Samir, bonne reponse `arriere-grands-parents, grands-parents, parents` (47 caracteres), distracteurs de 47 et 47 caracteres. Les trois propositions de l'item sont les plus longues du paquet. Les autres propositions de la tache ordre : 32 (item 3), 16 (item 9), 43 (item 12). Nadia a mesure la coupure a 744 sur l'item 8 seul, et non sur l'item 3 (32) ni sur l'item 12 (43). Le seuil se situe donc entre 43 et 47 caracteres a cette largeur, au vu de ce qu'elle a joue ; ce n'est pas une mesure de seuil.

La suite a trois elements parmi quatre generations n'a que quatre formes. Longueurs de la bonne suite (les trois propositions ont la meme longueur, les elements etant les memes) :

| Suite | Longueur | Etat |
|---|---|---|
| arriere-grands-parents, grands-parents, parents | 47 | item 8 actuel |
| arriere-grands-parents, grands-parents, enfants | 47 | |
| arriere-grands-parents, parents, enfants | 40 | |
| grands-parents, parents, enfants | 32 | item 3 actuel |

Les noms ne sont pas raccourcis : `GENERATIONS` (liste fermee de `nadia-prof`) est inchangee dans toutes les options.

## 2. Options

### O1. Ne rien changer au contenu

Longueur 47. Aucun effet sur `G12-3`, sur les comptes 8/4, sur le test. La coupure `grands-` / `parents` et l'indice sous le pli restent, sauf si la mise en page (`ines-design`) les traite.

### O2. Suite de quatre generations sans `grands-parents` : `arriere-grands-parents, parents, enfants`

Longueur 40 (7 de moins). Elle contient encore `arriere-grands-parents` (22 caracteres, coupable aux deux traits d'union) et ne ramene la ligne qu'au niveau de l'item 12 (43), que nadia n'a pas vu couper a 744. A 360 de large, la coupure dans `arriere-grands-parents` reste.
- `G12-3` : tient. Suite triee par rang (0, 2, 3), trois elements distincts, distracteurs a choisir parmi les rotations et l'echange de voisins, jamais l'ordre inverse, jamais les deux echanges.
- Comptes voisin/ordre : inchanges (8/4), la tache est une fonction de la bonne reponse (une suite de trois elements).
- `FER-1` (`verifierLesDeuxMoitiesEnBonneReponse`) : ne lit que les items voisin, non touche.
- Garde du paquet : aucune garde n'exige `arriere-grands-parents` ni `grands-parents` dans une suite. `arriere-grands-parents` reste dans les distracteurs des items voisin 1, 4 et 8.
- Question de contenu, a `nadia-prof` : une frise de quatre generations dont on saute la deuxieme, est-ce encore la competence QLM-CE1-12 ? L'enonce "range les generations" pourrait ne plus dire la meme chose avec trois sur quatre, ce qui est vrai aussi de l'item 3.

### O3. Remplacer l'item 8 par un item de la meme tache (ordre) et de la meme moitie (generations), a suite de prenoms

Meme forme que l'item 9 (Omar, Julie, Noe) : enonce de deux phrases qui nomment trois prenoms de trois generations, suite de trois prenoms, de la plus ancienne generation a la plus jeune. Longueur d'une proposition : 14 a 18 caracteres (item 9 : 16). Enonce : 90 caracteres pour l'item 9, contre 94 pour l'actuel item 8, donc l'enonce ne gagne rien.
- `G12-3` : la suite de prenoms est lue par `groupeDeLaSuite(..., prenomsDe(enonce))` comme l'item 9 ; aucune garde ne lit l'ordre des generations d'une suite de prenoms (le tri par rang ne s'applique qu'au groupe `gen`). Le meme sens, "du plus age au plus jeune", est celui de l'item 9. Distracteurs : un echange de voisins et une rotation, comme l'item 9.
- Comptes voisin/ordre : inchanges (8/4), la tache reste ordre.
- Garde du paquet (`G12-2`) : les prenoms ne sont admis que dans une suite, jamais dans un item voisin, ce que respecte l'item. Les prenoms du nouvel item doivent etre absents de l'enonce voisin des autres items (variete des prenoms, `TRV-CE1-6`).
- Perte : plus aucune suite de la tache ordre ne porte `arriere-grands-parents` ; la generation reste servie par les items voisin 1, 4 et 8, ou elle est distracteur, jamais bonne reponse (c'est deja le cas aujourd'hui, aucune garde ne l'exige).
- Reserve : deux items ordre de forme identique (deux enonces "a une fille ... a un fils") dans un paquet de douze. A `nadia-prof` de dire si c'est trop proche.
- Le nouvel item est un item neuf : enonce, bonne reponse, distracteurs, indice repassent par `nadia-prof` et par le `relecteur-pedagogique`.

### O4. Remplacer l'item 8 par un second item ordre sur `grands-parents, parents, enfants`

Longueur 32. Meme bonne reponse que l'item 3 : seuls l'enonce (autre famille, autre prenom) et les distracteurs changent. Les distracteurs peuvent etre differents de ceux de l'item 3 (l'autre echange de voisins `grands-parents, enfants, parents` et l'autre rotation `enfants, grands-parents, parents`), sans les deux echanges de voisins ensemble. La bonne reponse, elle, est la meme que celle de l'item 3 : a `nadia-prof` de dire si deux items a la meme bonne reponse dans la meme tache sont admis. Je n'ai pas lu de garde du paquet qui le refuse.

## 3. Ce qu'aucune option de contenu ne traite

L'indice et la croix sous le pli, constat 1 de nadia, dependent du nombre de lignes des trois propositions et de la hauteur de la zone de jeu a 744 x 551. Les options O2 et O3 reduisent le nombre de lignes de l'item 8 seul. L'item 9 du meme jeu a deja, d'apres nadia, un indice sous le pli sans proposition coupee. Si la cause est la mise en page (`ines-design`), la correction de contenu est inutile pour 9 et partielle pour 8.

## 4. Decision attendue

`sofia-sm` route : `ines-design` d'abord (cause de mise en page), puis, si l'item 8 reste coupe, choix entre O2 et O3 par `nadia-prof`. `marc-dev` ne choisit pas. Aucun yaml touche. Si O3 ou O2 est retenue, l'effet sur les gardes ci-dessus est a verifier par un `mvn -B -pl adapters/adapter-content -am test`, qui n'a pas ete lance pour cet avis.
