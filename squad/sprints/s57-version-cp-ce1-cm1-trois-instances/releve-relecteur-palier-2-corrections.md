# Releve du relecteur pedagogique - palier 2 de s57, corrections de marc-dev

Date : 2026-10-16. Perimetre : l'ecriture (orthographe, accents, ponctuation, espaces insecables)
des textes destines a l'enfant changes par deux commits de `marc-dev`, relue sur les commits, pas sur
le poste. Le niveau scolaire n'est pas juge (`nadia-prof`). Aucun fichier de contenu ni de code n'est
modifie. Suite de `releve-relecteur-palier-2.md`.

## 1. Verdict

| Texte | Commit | Verdict |
|---|---|---|
| `ce1-pronoms.yaml`, items 3, 9, 13, 16, 18 (et jeu fautif de test) | `42071c23` | correct |
| `ce1-texte-narratif.yaml`, trois jeux de propositions | `c77560f2` | correct |
| constantes de la phrase de `RDO-CP-R3` | `23222465` (branche `s57-lea-rdo-cp-r3`) | correct |

Aucune correction a appliquer. Reponse a `Q1` de `marc-dev` : U+00A0 est la bonne ecriture (section 4).
Le point ouvert du releve precedent n'est aggrave par aucun des deux commits (section 5).

## 2. La regle

- `DEC-0002` et son amendement du 2026-08-04 : francais correct, accentue, ponctuation irreprochable.
- `ADR-0060`, clause 2 : un guillemet francais ouvrant est suivi de `U+00A0`, un fermant en est precede.
- `ADR-0067`, amendement du 2026-10-14 : dans un texte servi, aucune espace ordinaire devant `?`, `!`,
  `;` ou `:`.
- Notation : `<N>` marque un `U+00A0` dans ce releve. Les controles ont ete lances sur le contenu des
  blobs (`git show <commit>:<fichier>`), pas sur le poste.

## 3. Commandes et sorties

### 3.1 `42071c23`, `ce1-pronoms.yaml` (items 3, 9, 13, 16, 18)

Diff des seules lignes changees (`git diff -U0 42071c23~1 42071c23`) : 9 lignes de
`ce1-pronoms.yaml`, 2 de `ce1-pronoms-fautif.yaml`, 3 du test. Aucune autre ligne du YAML ne bouge.

Textes ecrits, `<N>` la ou le fichier porte un `U+00A0` :

```
3  texteSupport  "Zoé prête son jeu à Paul. Elle ouvre la boîte."        distracteurs ["Paul", "son jeu"]
9  texteSupport  "Maëlle ouvre la porte à Bastien. Il entre dans la classe."
9  indice        "«<N>Il<N>» désigne un mot masculin<N>: cherche-le dans la phrase d'avant."
13 texteSupport  "Rose lit un livre à Léo. Elle tourne les pages."
16 texteSupport  "Mina et Omar regardent un chien. Le garçon lui donne de l'eau."   bonneReponse "Omar"
18 texteSupport  "Manon parle à Adrien. La fille lui pose une question."   distracteurs ["Adrien", "une question"]
```

Controles (script lu sur les lignes `enonce`, `indice`, `texteSupport`, `bonneReponse`,
`distracteurs`, commentaires exclus ; temoin negatif verifie : un enonce a chevrons et `?` a espaces
ordinaires leve 4 ecarts) :

- chevron ouvrant sans `U+00A0` : 0 ; chevron fermant sans `U+00A0` : 0 ;
- `?`, `:`, `;`, `!` sans `U+00A0` devant : 0 ; espace ordinaire devant : 0 ;
- guillemet ou apostrophe typographique, tiret cadratin, `U+202F`, double espace, espace de fin : 0 ;
- apostrophes droites (`l'eau`, `d'avant`) ; fichier en UTF-8 NFC, fin de ligne LF, sans BOM ;
- accents : `Zoé`, `prête`, `boîte`, `Maëlle`, `Léo`, `garçon`, `désigne`, tous precomposes ;
- ponctuation : un point final par phrase, majuscule apres chaque point, `Il` et `Elle` capitalises
  en tete de phrase et en tete d'indice ;
- `Hamza` : `git show 42071c23:<contenu>/ce1-pronoms.yaml | grep -c Hamza` rend 0 ; la table du test
  (`PaquetCe1PronomsTest`) et le jeu fautif disent `Omar`, rangé a sa place alphabetique entre `Noé`
  et `Paul`. Le commentaire de tete (vingt prenoms masculins, vingt et un feminins) reste exact :
  `Omar` remplace `Hamza`, tous deux masculins.

Egalite avec les textes de `nadia-prof` (`FICHE-bandit-manchot-pronoms-ce1.md`, section 5 du verdict
du 2026-10-16, lue sur la branche `s57-nadia-p2-a2`, commit `e49ca262` ; cette section n'est pas encore
sur `round-two`, voir section 6) : chacun des 9 champs, une fois `U+00A0` lu comme une espace, est egal au caractere pres a
sa cellule de la section 5 (`grep -cF` de chaque texte entre accents graves dans la fiche : 1 pour
chacun). Sur les 9 champs, **un seul** porte des `U+00A0` : l'indice de l'item 9 (3 : deux dans les
chevrons, un avant le deux-points). Les 8 autres sont en espaces ordinaires, comme le texte de
`nadia-prof`, parce qu'ils ne contiennent ni chevron ni `?` ni `:`. Les enonces, inchanges, portent
leurs `U+00A0` d'avant.

Verdict : correct.

### 3.2 `c77560f2`, `ce1-texte-narratif.yaml`

Diff (`git diff -U0 c77560f2~1 c77560f2`) : 5 lignes, `bonneReponse` et `distracteurs` des items
*Que fait Inès en dernier ?*, *Que fait Ibrahim en dernier ?*, et `distracteurs` de *Que se
passe-t-il à la fin ?*.

```
saute dans une flaque                   ["enfile ses bottes", "sort dehors"]
s'assoit près de la fenêtre             ["attend le bus", "monte"]
(inchangee) une fleur pousse            ["Chloé plante une graine", "elle l'arrose"]
```

- Aucune des cinq chaines ne contient de chevron, de `?`, de `:` ni de `U+00A0` ; le script de 3.1
  rend 0 ecart sur le fichier entier.
- Egalite avec `reponse-nadia-pa7-pa8.md`, section 1.4 (tableau de ce que ecrit `marc-dev`) : les
  cinq chaines sont egales au caractere pres (`grep -cF` de chacune dans le fichier : 1).
- Accents (`près`, `fenêtre`, `Chloé`) precomposes, apostrophe droite, aucune majuscule de phrase
  parasite. Les trois propositions de bonne reponse commencent par une minuscule, ce qui est la
  correction voulue (`reponse-nadia-pa7-pa8.md`, section 1.2).
- Les enonces voisins sont a `U+00A0` avant `?` (20 sur 20), inchanges.

Verdict : correct.

### 3.3 `23222465`, phrase de revelation de `RDO-CP-R3` (branche `s57-lea-rdo-cp-r3`)

`lea-dev` a livre la branche (un commit au-dessus de `0ea24709`) : applicable.

- Gabarit `fragments/manche.html`, ligne 214 :
  `Voici&nbsp;la&nbsp;bonne&nbsp;œuvre. Appuie&nbsp;dessus.` Trois `&nbsp;` dans la premiere phrase,
  un dans la seconde, une seule espace ordinaire entre les deux phrases. `grep -c -P '\x{A0}'` sur
  la ligne : 0 (le caractere n'est pas tape, il est ecrit en entite, comme l'exige `ADR-0067`,
  amendement du 2026-10-15).
- Texte lu (entites resolues) : `Voici la bonne œuvre. Appuie dessus.` Egal au caractere pres a la
  phrase de `reponse-nadia-pa7-pa8.md`, section 2.4 (ligne 122), et a celle du planning. Ligature `œ`
  conservee (comme dans `Choisir cette œuvre` du meme gabarit), majuscule apres le point, point
  final, aucun `?`, `!`, `:` ou `;` : aucune espace avant ponctuation haute n'est attendue.
- Constantes des quatre tests (`PHRASE_DE_M4`, `PREMIERE_PHRASE`, `PHRASE_DE_REVELATION`, entree de
  l'inventaire de `TexteAccentueDesGabaritsTest`, attente de
  `PartieControllerPropositionsEnImageTest`) : toutes egales a `Voici la bonne œuvre. Appuie dessus.`
  ou a sa premiere phrase `Voici la bonne œuvre.`
- Coherence du compte : la Javadoc de `defauts` et le message d'echec disent "trois" `U+00A0` dans la
  premiere phrase ; `Voici~la~bonne~œuvre.` en porte trois. La seconde, `Appuie~dessus.`, en porte
  une, dite "une". Aucun "cinq" ni aucune trace de l'ancienne phrase dans `adapters/` et
  `bootstrap/` (`git grep -i "va avec"`, `"cette œuvre qui"` : seuls des enonces de contenu sans
  rapport, et `Choisir cette œuvre`, `aria-label` distinct).
- Commentaire du test : `{@code ~}` marque `U+00A0` ; la formulation est exacte.

Verdict : correct.

## 4. Q1 de `marc-dev` : U+00A0 dans les chevrons et avant le deux-points

Confirme, c'est la bonne ecriture. Trois motifs :

1. `ADR-0060`, clause 2, exige `U+00A0` dans un guillemet francais ouvrant et fermant. Une espace
   ordinaire dans l'indice de l'item 9 serait une violation de la clause.
2. `ADR-0067`, amendement du 2026-10-14, interdit l'espace ordinaire devant `:`.
3. Le critere `PN8-8` de `camille-po` (`docs/backlog/backlog.md`) fixe l'indice de l'item 9 avec
   `«\x{A0}Il\x{A0}»` et `masculin\x{A0}:` : l'ecart de `marc-dev` est exigé par le critere, pas
   toleré.

L'ecart avec `nadia-prof` n'est donc pas un ecart de texte : la fiche dit elle-meme, en section 5,
"Espaces ordinaires ici ; dans le paquet, U+00A0 dans les chevrons et devant `?` et `:`,
comme ailleurs. L'orthotypographie est au `relecteur-pedagogique`." Son texte et celui du paquet
sont egaux a `U+00A0` pres, et cette difference est celle qu'elle a ecrite.

Le meme motif vaut pour `c77560f2` : sans chevron ni ponctuation haute, il n'y a pas de `U+00A0` a
ecrire, et `marc-dev` n'en a pas ecrit.

## 5. Point ouvert du releve precedent : avant `?`

Mesure, sur `adapters/adapter-content/src/main/resources/contenu/*.yaml`, lignes `enonce`,
`indice`, `consigne`, `texteSupport`, `git grep -h -P` avec `\x{A0}\?` puis `[^ ] \?` :

| Commit | `U+00A0` avant `?` | espace ordinaire avant `?` | chevrons a `U+00A0` | chevrons a espace ordinaire |
|---|---|---|---|---|
| `0ea24709` (base) | 2377 | 224 | 567 | 3 |
| `42071c23` | 2377 | 224 | 568 | 3 |
| `c77560f2` | 2377 | 224 | 567 | 3 |

Le point reste ouvert, inchange : 2377 contre 224. Les deux commits n'y ajoutent aucun cas. Les 224
ne sont pas dans `ce1-pronoms.yaml` ni dans `ce1-texte-narratif.yaml` : la commande appliquee a ces
deux fichiers au sommet de `c77560f2` rend 0. Le seul mouvement est +1 chevron a `U+00A0` dans
`42071c23` (le nouvel indice de l'item 9, qui remplace un indice sans chevron). Les 3 chevrons a
espace ordinaire sont les 3 commentaires deja nommes dans le releve precedent.

A trancher, inchange : une decision de typographie pour un enfant, a `theo-archi` (l'`ADR`) et a
`camille-po` (le produit). Pas de nouveau point.

## 6. Points a trancher

Un point de fusion, pour `sofia-sm` : la section 5 du verdict de `nadia-prof` dans
`FICHE-bandit-manchot-pronoms-ce1.md` n'existe que sur la branche `s57-nadia-p2-a2` (`e49ca262`) ; sur
`round-two` (`0ea24709`) la fiche s'arrete avant le verdict. Le texte de reference de `42071c23` n'y
est donc pas encore lisible. Aucun autre point nouveau. Aucun ecart d'attribution (`DEC-0062`) : les citations de `nadia-prof` renvoient a
`FICHE-bandit-manchot-pronoms-ce1.md` section 5 et a `reponse-nadia-pa7-pa8.md` sections 1.2, 1.4 et 2.4,
qui resolvent. Les chiffres de la section 5 sont mesures le 2026-10-16 avec les commandes ci-dessus,
sur les trois commits nommes ; la mesure porte sur le dossier `contenu/` seul, pas sur le depot.
Aucun enonce recu de MASTER.
