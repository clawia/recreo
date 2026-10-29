# Lecture de `nadia-prof` - l'accord de *du plus âgé au plus jeune* dans `ce1-qlm-generations` (QLM-CE1-12)

Date : 2026-10-29. Story : `S57-QLM-CE1-12-ACCORD`, critere 3 (`docs/backlog/backlog.md`). Source lue en entier :
`releve-relecteur-palier-5-ce1-qlm-generations.md` (ce dossier), qui juge **fautif** l'accord des items 3 (`:96`) et 8 (`:131`)
de `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-generations.yaml` et **juste** celui de l'item 11 (`:152`). Ce
releve fait foi sur la grammaire : je ne la juge pas (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`). Je lis le sens, le
niveau et l'exactitude.

Ce fichier ne modifie ni le paquet, ni le test, ni le catalogue, ni la matrice, ni le backlog, ni mes arbitrages, ni
`FICHE-ce1-qlm-12.md`. Aucun Done nouveau n'y est prononce. Aucun identifiant n'y est alloue.

## 1. `R12-2`, B : ce que j'exige, et le texte que la garde lit apres l'accord

**Le sens exige.** Oui : ce que `R12-2` B exige d'un enonce de la tache ordre, c'est qu'il **ecrive le sens de l'ordre** - de la
plus ancienne generation (ou de la personne nee la premiere) a la plus jeune, ou du premier moment de la vie au dernier. La forme
grammaticale n'a jamais ete l'exigence : j'ai ecrit *du plus âgé au plus jeune* comme exemple du sens, et la garde `G12-3` l'a
lue a la lettre. Le releve montre que cet exemple est fautif quand l'enonce range des générations. Je corrige donc ma formule.

**Ce que je n'autorise pas.** Le sens ne s'ecrit pas "quelle que soit la forme" : une forme libre ouvrirait des formules que je
n'ai pas lues (*de la plus vieille*, *des plus anciens*, *dans l'ordre de l'age*). J'autorise **trois formes, mot pour mot, et
chacune n'est admise que pour son groupe de suite** :

| Ce que la suite range | Forme admise dans l'enonce, mot pour mot | Exemple au paquet |
|---|---|---|
| trois générations | *de la plus âgée à la plus jeune* | items 3 et 8, apres correction |
| trois prenoms de l'enonce, chacun d'une generation differente | *du plus âgé au plus jeune* | item 11, inchange |
| la suite d'evenements admise | *du premier au dernier* | item 12, inchange |

Une forme n'est pas admise pour un autre groupe : un enonce qui range des générations avec *du plus âgé au plus jeune* est refuse,
un enonce qui range des prenoms avec *de la plus âgée à la plus jeune* l'est aussi. Toute autre forme passe par moi avant
d'entrer dans un item, et par le `relecteur-pedagogique` pour sa grammaire.

**Texte de `R12-2`, B, a lire a partir du 2026-10-29** (il remplace, pour le sens de la tache ordre seulement, la derniere phrase
de `R12-2` B dans `arbitrage-nadia-contradictions-ce1-qlm-10-12.md`, section 2.1, et la phrase *"L'enonce ecrit le sens [...]"* de
`R12-2` B dans `arbitrage-nadia-premiers-jets-ce1-qlm-10-12.md`, section 2.1 ; le reste de `R12-2` est inchange) :

> **`R12-2`, B, le sens [corrige le 2026-10-29 par `nadia-prof`, `lecture-nadia-accord-ce1-qlm-generations.md`, section 1 ;
> texte d'origine : *"dans le sens du plus âgé au plus jeune"* et *"L'enonce ecrit le sens, du plus âgé au plus jeune
> (generations) ou du premier au dernier (une vie)"*].** L'enonce ecrit le sens de la suite, avec la forme de son groupe et
> elle seule : *de la plus âgée à la plus jeune* quand la suite range trois générations ; *du plus âgé au plus jeune* quand elle
> range trois prenoms de l'enonce ; *du premier au dernier* quand elle range la suite d'evenements admise.

**Ce que la garde doit refuser, a la place de la cellule de `G12-3`** (`arbitrage-nadia-premiers-jets-ce1-qlm-10-12.md`,
section 2.2, colonne *"Doit etre refuse"*, second cas) : un enonce B qui n'ecrit pas la forme de son groupe, y compris un enonce
qui ecrit la forme d'un autre groupe. Le premier cas (un distracteur inverse exact) et la colonne *"Doit passer"* sont
inchanges. La forme technique de la garde, constantes et normalisation comprises, est a `theo-archi` ; je ne la decide pas.

Les deux arbitrages ne sont pas reecrits dans ce geste (un seul fichier). Le commentaire d'en-tete du paquet (lignes 25 et 53)
cite l'ancienne formule : sa mise en accord suit la decision de `theo-archi` et n'est pas un texte servi a l'enfant.

## 2. Les trois enonces, lus comme un enfant de CE1 les lit

Textes lus : ceux de la section 3 du releve pour les items 3 et 8, celui du paquet pour l'item 11. Espace insecable avant *?*
dans les trois.

| Item | Enonce | Mots (decoupe par espaces) | Caracteres |
|---|---|---|---|
| 3 | "Dans la famille de Lina, quelle suite range les générations de la plus âgée à la plus jeune ?" | 18 (16 avant) | 93 (87 avant) |
| 8 | "Dans la famille de Samir, quelle suite range les générations de la plus âgée à la plus jeune ?" | 18 (16 avant) | 94 (88 avant) |
| 11 | "Omar a une fille, Julie. Julie a un fils, Noé. Quelle suite va du plus âgé au plus jeune ?" | 19, inchange | 90, inchange |

Mesure : `split(' ')` et `len` sur les chaines ci-dessus, sous Python, le 2026-10-29.

**Clarte.** *De la plus âgée à la plus jeune* est clair pour un CE1. Les mots ajoutes sont *la* et *à la*, que l'enfant decode
sans effort ; *âgée* est un mot de son vocabulaire (*une dame âgée*), et la terminaison *-ée* est deja lue dans le paquet
(*entrée*, dans les cinq items d'evenements). Les enonces restent dans la fourchette de mon verdict (13 a 20 mots), en une phrase.

**Difficulte.** Pas plus difficile que l'ancienne formule. L'accord rattache maintenant l'adjectif a *générations*, le seul nom
qu'il qualifie : la phrase dit mieux ce qu'on range. Un enfant qui lirait *la plus âgée* comme une personne de la famille (la
grand-mere, par exemple) range dans le meme sens, de la plus ancienne a la plus recente : aucune des deux lectures ne mene a une
autre reponse. Je ne demande pas d'autre mot.

**Exactitude, item par item.** Le sens de la suite ne change pas : seule la forme change.

- Item 3 : bonne reponse *grands-parents, parents, enfants*, juste. Distracteurs *parents, grands-parents, enfants* (echange de
  deux voisins) et *parents, enfants, grands-parents* (rotation), faux tous deux ; l'ordre inverse n'est pas propose.
- Item 8 : bonne reponse *arrière-grands-parents, grands-parents, parents*, juste. Distracteurs *arrière-grands-parents,
  parents, grands-parents* (echange de deux voisins) et *parents, arrière-grands-parents, grands-parents* (rotation), faux tous
  deux ; l'ordre inverse n'est pas propose.
- Item 11 : *Omar, Julie, Noé*, juste par les liens de l'enonce ; distracteurs *Julie, Omar, Noé* et *Noé, Omar, Julie*, faux ;
  inchange.

**Propositions inchangees.** Le releve ne propose de changer que le groupe de mots de l'enonce. Aucune proposition, aucune bonne
reponse, aucun indice ne change, et je n'en demande aucun changement. Les trois indices (`:99`, `:134`, `:155`) restent vrais
avec la nouvelle formule.

**Verdict de lecture** : les deux enonces corriges, tels que le releve les ecrit, sont **justes** pour le sens, le niveau et
l'exactitude ; l'item 11 reste juste.

## 3. Rang de cette lecture, et ce qui suit la correction de `marc-dev`

**Rang.** C'est la **troisieme lecture de `-12` avant `verify`** : apres la lecture du premier jet (`c577ca31`) et la relecture
des corrections (`de626e12`). Je n'en fixe pas le prix : la regle est `DEC-0120` A.272 (regle point 3, 0,50 hors marge pour une
troisieme lecture), et A.283 note qu'elle est ecrite pour une lecture avant `verify` sans dire si elle vaut apres un Done
pedagogique ; ce classement n'est pas a moi.

**Pas de relecture apres la correction.** Ma lecture du texte propose vaut, a une condition que je verifie moi-meme : je lis le
`git diff` du commit de `marc-dev` avant `verify`. Il doit montrer :

1. dans le paquet, les seules lignes `enonce:` des items 3 et 8 changees, et chacune egale, octet pour octet, au texte de la
   section 3 du releve (espace insecable avant *?* compris) ;
2. la ligne `enonce:` de l'item 11, toutes les propositions, toutes les bonnes reponses et tous les indices inchanges ;
3. hors du paquet, le test (et le commentaire d'en-tete du paquet, si `theo-archi` le demande), rien d'autre.

Si le diff est conforme, aucune autre lecture n'est due. S'il s'en ecarte - un autre mot, une autre ligne servie a l'enfant, une
proposition touchee -, la ligne qui s'ecarte repasse par ma lecture avant `verify` ; ce serait une quatrieme lecture, dont je ne
fixe pas non plus le prix.

## 4. Ce que cela change a mon Done de `-12`

- **Le Done reste VALIDE SOUS RESERVE**, prononce le 2026-10-27 (`FICHE-ce1-qlm-12.md`, bloc de validation). La reserve `V1`
  (rendu visuel non mesure) est inchangee. Je n'ajoute aucune reserve : l'enonce gagne 6 caracteres, et je ne releve pas ma
  barre apres coup.
- **Aucun verdict d'item n'est rouvert** : le sens, les bonnes reponses et les distracteurs des items 3, 8 et 11 sont ceux que
  j'ai joues et valides.
- **Ma section 3 de la fiche reste vraie a la date ou elle est ecrite** : le Done ne couvrait pas l'accord. L'accord n'etait pas
  une reserve de mon Done, il n'y a donc rien a lever. Quand j'aurai vu le diff (section 3 ci-dessus), j'ecrirai a la fiche, en
  ajout date sous mon bloc et sans le reecrire, que l'accord est corrige et que le diff est conforme. Rien de cela n'est ecrit
  ici : une levee ou un ajout a mon verdict ne s'ecrit qu'a la fiche.
- **Ce dont je reste la seule garde** : inchange (fiche, section 5). Le choix de la forme selon le groupe sera tenu par `G12-3`
  si `theo-archi` l'ecrit ainsi ; que la forme admise soit grammaticalement juste ne l'est par aucun test, et reste au
  `relecteur-pedagogique`.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-29.
