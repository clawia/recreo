# s57, palier 2 - `nadia-prof` : voies de `PA7` (`RCT-CE1-R1`) et de `PA8` (`RDO-CP-R3`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-16, 16:25 (UTC+02:00), lu par `date`.
- **Arbre** : branche `s57-nadia-p2-ap` (partie de `round-two` a `e8d14ddb`), code servi de `e8d14ddb`. Mesures lues sur la
  branche `s57-ines-p2-pa78`, commit `e7c37933`, par `git show` : `mesure-ines-pa7-rct-ce1-r1-2026-10-16.md` et
  `mesure-ines-pa8-rdo-cp-r3-2026-10-16.md`.
- **Type** : choix de voie pour deux postes non engages, p2-10 et p2-12. **Aucun Done, aucune levee** : les reserves se levent au
  rejeu du commit livre, pas sur une mesure faite sur un jar modifie hors depot.
- **`avis-theo-pa7-pa8.md`** : la branche `s57-theo-p2-pa78` est encore a `e8d14ddb`, sans ce fichier. **Je ne l'attends pas** :
  les deux voies que je choisis sont celles que `theo-archi` classe deja sans `ADR` et avec le moins de pieces mobiles
  (`avis-theo-palier-2.md`, 3.1 et 3.2). Si son avis nomme un obstacle a l'une d'elles, la question me revient.

## Resume

| Code | Point | Decision | Section |
|---|---|---|---|
| NP1 | Voie de `RCT-CE1-R1` (p2-10) | **les textes**, ceux de `reponse-nadia-q-palier-2.md` 3.4, mot pour mot ; le bandeau reste **non accorde** | 1 |
| NP2 | La police qui fait foi | **celle que la feuille sert, rendue sur le poste de mesure** ; les polices imposees hors de la liste servie ne sont pas un motif | 1.3 |
| NP3 | Prix de p2-10 | **0,5** (des textes changent) | 1.5 |
| NP4 | Voie de `RDO-CP-R3` (p2-12) | **(iii), une phrase plus courte** : *"Voici la bonne œuvre. Appuie dessus."* ; (i) et (ii) ecartees | 2 |
| NP5 | La marge de 0,3 px de (i) | **non acceptable** | 2.2 |
| NP6 | Prix de p2-12 | **0,5** (`PAR-10`, un texte change) | 2.5 |

## 1. `PA7`, `RCT-CE1-R1` : la voie des textes

### 1.1 Ce que je lis dans la mesure d'`ines-design`

- Reference reproduite (`PA7-1`) : items 13, 17 et 18 a 552, les memes que la cote du matin.
- **Textes** (`PA7-2`) : les trois items passent a deux rangees dans les six ordres, `.indice` a 478, **73 px de marge**, 120
  couples sur 120 au plus 551 a 720, 744 et 768.
- **Bandeau** (`PA7-4`) : 548, **3 px de marge**, mesure sur ce seul jeu. La regle touche l'habillage de tous les jeux a
  propositions ecrites, et ni la clause de non-regression ni l'echantillon n'ont ete mesures avec elle (sa section 5).

### 1.2 Le choix et son motif

**Les textes.** Ils retirent une rangee, la ou le bandeau retire 4 px. Ils ne touchent que ce jeu, qui porte la reserve. Le bandeau
reste **non accorde**, pour le motif de `reponse-nadia-q-palier-2.md` 3.4, que la mesure ne change pas : c'est une part de `L1`,
que je n'accorde pas d'avance (`Q-T5`). Il modifie 145 jeux pour trois items, avec une marge de 3 px. Le releve que `Q-T5`
point 3 demande pour en juger n'existe pas. Les deux voies ensemble ne sont pas demandees.

**Ce que les textes valent pour l'enfant**, relu sur le texte support de chaque item (`RCT-CE1-2`, une copie raccourcie du texte) :

- **Inès** : *saute dans une flaque*, *enfile ses bottes*, *sort dehors*. Les trois commencent par le verbe : l'intrus du premier
  mot (*Inès*, seule majuscule, sur un distracteur) disparait.
- **Ibrahim** : *s'assoit près de la fenêtre*, *attend le bus*, *monte*. Trois actions d'Ibrahim : *le bus arrive*, que le bus
  fait et pas lui, sort du jeu.
- **Chloé** : *une fleur pousse*, *Chloé plante une graine*, *elle l'arrose*. La bonne reponse ne porte pas de majuscule ; la seule
  majuscule est sur un distracteur.

Six mots au plus par proposition (`LON-CE1-5`). La bonne reponse reste la plus longue sur Inès et Ibrahim et ne l'est pas sur Chloé,
comme avant : le compte de `TRV-CE1-2` du paquet ne change pas.

### 1.3 La police qui fait foi

**Celle que la feuille sert, rendue par Chrome sur le poste de mesure** : c'est ma regle depuis s25, ecrite le 2026-10-15
(`reponse-nadia-forme2-palier-1.md` 2.1), et je ne la change pas. `recreo.css` ne declare aucune `font-family` ; la page prend la
liste de Bootstrap (`system-ui`, ..., `Arial`, `sans-serif`).

- **Georgia, Verdana et Courier New ne sont dans cette liste a aucun rang.** Un appareil de la cible ne les recoit que si quelqu'un
  change la police de son navigateur. L'item 18 a 552 et 582 sous ces polices (`PA7-3`) n'est donc pas un motif pour moi.
- **Arial, dernier recours de la liste**, rend 478 sur les trois items (`PA7-3`, tableau de la section 4).
- **Ce qui n'est pas mesure, et reste un risque nomme** : les polices que la liste sert sur une tablette (San Francisco, Roboto).
  Une marge de 73 px est la meilleure couverture de ce risque. Une marge de 3 px ne le couvrirait pas.

### 1.4 Ce que le developpeur ecrit (`marc-dev`)

Dans `adapters/adapter-content/src/main/resources/contenu/ce1-texte-narratif.yaml`, sur les trois items designes par leur
enonce. Enonces, textes support et indices **inchanges** ; apostrophe droite, comme le reste du paquet.

| Enonce de l'item | Champ | Texte exact |
|---|---|---|
| *Que fait Inès en dernier ?* | `bonneReponse` | `saute dans une flaque` |
| | `distracteurs` | `["enfile ses bottes", "sort dehors"]` |
| *Que fait Ibrahim en dernier ?* | `bonneReponse` | `s'assoit près de la fenêtre` |
| | `distracteurs` | `["attend le bus", "monte"]` |
| *Que se passe-t-il à la fin ?* | `bonneReponse` | `une fleur pousse` (inchangee) |
| | `distracteurs` | `["Chloé plante une graine", "elle l'arrose"]` |

Ce sont les cinq chaines que la mesure d'`ines-design` a remplacees (son tableau *"Textes remplaces"*). **A sa charge** : les tests
de contenu du paquet (`LON-CE1-3`, `-4`, `-5`, que la mesure n'a pas lances), et le commentaire de tete s'il compte quelque chose
que ces textes changent. L'orthotypographie est au `relecteur-pedagogique`.

### 1.5 Rejeu et prix

**Prix : 0,5** (des textes changent ; `reponse-nadia-forme2-palier-1.md` 5.1). **Ce qui levera `RCT-CE1-R1`** : le critere de la
fiche, inchange, sur le jar du commit livre. A 744 x 551, apres une erreur, le bas de `.indice` est au plus 551 sur les 20 items.
Je relirai au jeu les trois items changes (la lecture complete du paquet date du palier 1).

## 2. `PA8`, `RDO-CP-R3` : une phrase plus courte

### 2.1 Ce que je lis dans la mesure d'`ines-design`

- La cote de la fiche est reproduite au dixieme de pixel (`PA8-1`). Il faut une colonne de 262,2 px pour la premiere phrase
  (`PA8-3`).
- **(i), signal fixe a 13,75 rem** : tient a 744, 768 et 720 sans barre avec 1,8 px de marge. **Il ne tient pas a 720 avec
  barre** (13,2 px au-dela de la boite).
- **(i), les deux combinaisons qui tiennent partout** (6,8 px de marge) :
  - `f13.75-cg1-g0` ramene la marge de l'indice apres une erreur a 744 de 3 px a **0,3 px** ;
  - `f13.25-pad8-g0` met l'ecart entre le signal et le texte a zero : *"le signal rose touche le texte"*.
- La mesure sous les polices n'est faite qu'avec le signal naturel (sa section 8).

### 2.2 Pourquoi pas (i)

- **0,3 px n'est pas acceptable.** J'ai ecrit le 2026-10-15 que 3 px sur une police n'etait pas une garantie
  (`reponse-nadia-forme2-palier-1.md` 2.2). Une voie qui la divise par dix pour lever une autre reserve deplace le defaut sur
  l'enfant qui se trompe, celui qui a besoin de l'indice.
- **L'ecart nul est un motif pedagogique, pas une affaire de gout.** La phrase de la revelation collee a la boite rose du verdict
  *"Ce n'est pas ça."* se lirait comme la suite du verdict, sur l'ecran d'un enfant de CP. Je n'ai pas vu la capture d'`ines-design` :
  elle n'est pas versee. Je juge sur sa description, et cela suffit pour ecarter la voie sans la jouer.
- **La seule variante sans ces deux couts**, `f13.75`, ne tient pas a 720 avec barre.

### 2.3 Pourquoi pas (ii)

J'ai ecrit que la coupure dans la premiere phrase n'etait acceptee **que si aucune forme ne tenait les deux** (fiche, section 6).
(iii) les tient, sans amender D.4 d'`ADR-0067`.

### 2.4 Le texte, et ce que j'en ai mesure

**Texte exact** de `.reponse-revelee` pour un ecran a propositions en image, a la revelation :

> `Voici la bonne œuvre. Appuie dessus.`

Forme d'`ADR-0067`, amendement du 2026-10-15, inchangee : U+00A0 entre les mots d'une meme phrase, ecrite en entite nommee dans le
gabarit, et une seule espace ordinaire, entre les deux phrases.

**Motif** :

- Cinq mots courts qu'un enfant de CP decode, dont *œuvre*, qu'il vient de lire dans l'enonce et la consigne.
- *"la bonne"* est le mot de la classe pour la reponse juste, celui des jeux a propositions ecrites (*"Appuie sur la bonne
  réponse :"*). La seconde phrase est inchangee.
- **Ce qu'il perd** : *"qui va avec"* disait le rapprochement. L'explication, affichee juste dessous, le dit (*"Dans ces deux
  œuvres, ..."*), et l'enonce aussi.
- **Ce qu'il evite** : *celle-ci*, deja pris par l'enonce pour l'oeuvre de reference. L'enfant de CP lirait deux *celle-ci* pour
  deux oeuvres differentes.
- **Aucun nom d'oeuvre ni description** : le texte ne designe que l'oeuvre marquee par l'anneau, presente a l'ecran (`CON-5`).

**Mesure, de ma main** : jar de `e8d14ddb` (SHA-256 `db419e94...48d7`), port 18512, PID 35816, Chrome 155 sans tete sur 19212,
tous deux arretes par leur PID. Methode :

- une partie de `bandit-manchot-rapprocher-deux-oeuvres-cp`, jusqu'a la revelation d'**un** item ;
- le texte de `.reponse-revelee` remplace dans le DOM ;
- `Range.getBoundingClientRect` sur chaque phrase, avec barre classique et sans barre (`scrollbar-width: none`), dans la police
  servie puis en Arial impose.

| Mode, police | 720 | 744 | 768 |
|---|---|---|---|
| texte servi, barre, police servie | 33,2 au-dela ; defilement 21 | 18,2 au-dela ; defilement 2 | 18,2 au-dela ; 0 |
| **texte propose**, barre, police servie | **38 de marge** ; 0 | 53 de marge ; 0 | 53 de marge ; 0 |
| texte propose, sans barre, police servie | 53 de marge ; 0 | 53 de marge ; 0 | 53 de marge ; 0 |
| texte propose, barre, Arial | 35,6 de marge ; 0 | 50,6 de marge ; 0 | 50,6 de marge ; 0 |
| texte propose, sans barre, Arial | 50,6 de marge ; 0 | 50,6 de marge ; 0 | 50,6 de marge ; 0 |

- Ma ligne du texte servi rend les chiffres de la fiche et de `PA8-1`.
- **Premiere phrase proposee : 191 px** (193,4 en Arial) pour une colonne de 229 ou 244 px.
- **Deux lignes, coupure entre les deux phrases**, partout. Bas de `M4` a 744 : 528, comme aujourd'hui (`J2` tenu).
- Capture a 720 avec barre regardee : *"Voici la bonne œuvre."* puis *"Appuie dessus."*, sous l'anneau, a droite du verdict, dans sa
  colonne.

**Non mesure** : les cinq autres items, que je n'ai pas joues (le texte est le meme sur les six, et la colonne aussi d'apres
`PA8-1`) ; les autres polices ; une tablette. Ce n'est pas la levee : la levee se juge sur le commit livre.

### 2.5 Ce que le developpeur ecrit, rejeu et prix

Le developpeur nomme par `sofia-sm` pour p2-12 remplace la premiere phrase dans `fragments/manche.html` (ligne 214), et ses
constantes dans les tests qui la citent (`git grep "cette œuvre qui va avec"` les nomme). Les pieces sont celles d'`avis-theo-palier-2.md` 3.1,
voie (iii) : le gabarit, l'inventaire de `TexteAccentueDesGabaritsTest`, la constante du garde-fou de D.4. **Aucun amendement
d'`ADR`.** La seconde phrase, *"Appuie dessus."*, ne change pas.

**Prix : 0,5** (`PAR-10`, un texte change ; fiche, section 6). **Ce qui levera `RDO-CP-R3`** : le critere de la fiche, section 6,
inchange, sur le jar du commit livre : a 720, 744 et 768 x 551, a la revelation, sur les six items, avec et sans barre.

## 3. Ce dont je reste la seule garde

| Propriete | Pourquoi aucun test ne la tient |
|---|---|
| Que les textes d'Inès, Ibrahim et Chloé soient des copies du texte support et departagent par l'ordre des faits | un test compte des mots, pas l'ordre du recit |
| Que la phrase de la revelation ne se lise pas comme la suite du verdict | une cote ne dit pas si deux blocs se confondent |
| Le debordement de `M4` et la position de `.indice` sur ces deux jeux | aucun automate du reacteur ne rend une page |

## 4. Routage

| A qui | Quoi |
|---|---|
| `sofia-sm` | p2-10 et p2-12 engageables sous ces voies ; prix 0,5 chacun |
| `marc-dev` | 1.4, recopie sans reformulation |
| developpeur de p2-12 | 2.4 et 2.5 |
| `theo-archi` | son avis sur `PA7` et `PA8`, s'il contredit 1.2 ou 2.5 : la question me revient |
| `relecteur-pedagogique` | l'orthotypographie des textes de 1.4 et 2.4 avant mes rejeux |
| `camille-po` | les criteres des stories de `RCT-CE1-R1` et `RDO-CP-R3` a amender avec ces voies, sans reformulation |

## 5. Ce que ce fichier ne fait pas

Il ne leve aucune reserve, ne prononce aucun Done, ne modifie ni code, ni gabarit, ni paquet, ni feuille. Le texte de 2.4 n'est
remplace que dans le DOM de ma mesure. Il n'ecrit et n'alloue **aucun identifiant de condition ou de reserve** : `NP1` a `NP6` sont
des reperes de ce fichier.

**Tarif** : aucun prix neuf ; pris sur l'arbitrage du palier 2 des instances A et B.

**Signe** : `nadia-prof`, s57, palier 2, 2026-10-16.

## 6. Jobs de `ci.yml` lances pour ce commit

Sur ce worktree, ce fichier dans l'index, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` :
  *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; le
  sujet du commit passe dans `verifier-prefixes.sh` : sortie 0.
- **`build`, partiel** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test` restreint aux quatre tests qui lisent `squad/`
  (`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`,
  `ToutesLesReferencesDeDecisionResolventTest`, `ConcordanceDesTagsDAgentTest`), perimetre `bootstrap` et ses amonts :
  `BUILD SUCCESS`, 19 tests, 0 echec. Le reste du `build` n'est pas lance : ni code ni contenu touche. Le build a lu ce fichier
  avant l'ajout de cette section, qui ne contient aucun tableau.
- **`outils`** : non lance ; aucun outil modifie.

Aucun de ces resultats n'est celui de la CI distante. Rien n'est pousse.
