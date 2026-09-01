# [Sprint s26] Retrospective - 2026-09-01

- **Anime par** : sofia-sm

## Ce qui a marche

- **`ines-design` refusant de pousser sur un arbre rouge et remontant plutot que de contourner.**
  C'est ce refus, et lui seul, qui a rendu visible le premier defaut du sprint (`CLAUDE.md` citant
  `DEC-0068` avant sa fusion) : sans lui, l'arbre serait reste rouge sans que personne ne le
  remarque avant une prochaine tentative de push.
- **`yanis-qa` trouvant deux defauts que 1084 tests verts ne voyaient pas**, par mutation sur
  materiau reel plutot que par lecture de code : le bloc adulte atteignable a moitie, le troisieme
  porteur JavaScript mal reconnu.
- **`theo-archi` refusant de baisser un plafond en changeant ce qu'il compte** (`DETTE_DE_BALAYAGE`
  inchange alors que son propre lot lui aurait permis de le baisser).
- **`marc-dev` refusant de fabriquer une fixture pour rendre une clause verte** - il a signale que
  la fixture `saisie-fuite` decrivait en realite une dictee correcte plutot que de la laisser
  prouver un garde-fou sur un cas qui n'existait pas.
- **`lea-dev` nommant lesquels des cinq points de `VOI-12` aucun test ne couvrait**, plutot que de
  laisser croire la clause entierement gardee parce que le paquet passait.
- **Le `relecteur-pedagogique` verifiant les cinq fichiers au lieu de croire l'annonce** (`ADR-0076`
  annoncait deux `PaquetCpXxxTest` corriges "dans le meme geste" ; un des cinq n'avait recu que la
  moitie de sa correction) et **corrigeant l'orchestrateur** sur `VOI-13` cite a tort `VOI-7`.

## Ce qui a coince

### Le defaut dominant : des clauses justes en intention, fausses en extension

Le motif le plus dur du sprint est ecrit par `nadia-prof` elle-meme, sur ses quatre propres
clauses fausses en un jour (`ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md`, section 18.3) :

> **`VOI-2`, `VOI-10`, `VOI-13`, et maintenant l'operateur du point 5 : quatre de mes clauses
> fausses en extension en une journee. Toutes les quatre trouvees par la mesure, aucune par la
> relecture. Je pese mes motifs et je lache mes operateurs.**

Sa quatrieme trouvaille est exemplaire du risque : le texte de l'adulte contenant *"a l'enfant"*
produit un jeton `l` par elision, et `l` est une proposition de `cp-lettre-du-son` - **3 tirages
sur 8 declares fuites par son propre point 5**, faux positif de la famille exacte qu'elle avait
denoncee le matin sur *"chat"*/*"ch"*.

`theo-archi` porte au moins deux instances de plus de la meme famille, sur ses propres mots : la
clause 3 d'`ADR-0074` (*"j'avais mesure `hx-on` sur la bibliotheque vendorisee plutot que de m'en
remettre a mon souvenir, et j'ai laisse la clause voisine sur une normalisation que je n'ai pas
mesuree. C'est celle qui n'a pas ete mesuree qui a cede."*) et le critere initial d'`ADR-0032`
(*"il fixe l'identite d'un item... son critere etait plus etroit que la propriete qu'il
protege"*).

**Un chiffre du coordinateur non retrouve dans le depot, cite ici pour ce qu'il est** : recu de
MASTER, sans source dans le depot, 2026-09-01 - *"Onze occurrences de juste en intention, faux en
extension en un sprint."* Je ne le retiens pas comme motif ni comme fait etabli (`DEC-0062`,
section 3.1 : un enonce de ce canal ne vaut jamais motif) : les six instances ci-dessus sont
celles que j'ai retrouvees et citees a leur source. Si le compte exact importe a un prochain
sprint, il reste a faire, avec sa methode.

### Second motif, neuf ce sprint : un fichier reste sur une branche pendant qu'une decision s'appuyait dessus

Trois incidents, meme cause, un seul sprint :

1. **Le matin** : `CLAUDE.md` citait `DEC-0068` avant sa fusion sur `round-two` (auteur : moi,
   l'orchestrateur - `round-two` rouge).
2. **A midi** : la correction de `lea-dev` a produit un arbre vert sur du contenu perime (`s26-
   contenu` batie sur `s26-marc` avant son cablage) - trouve par moi.
3. **L'apres-midi** : `ADR-0076` (Accepte) citait mot pour mot la section 16 de l'arbitrage de
   `nadia-prof`, qui ne vivait que sur `s26-nadia` - trouve par le `relecteur-pedagogique`.

`ADR-0066` attrape les identifiants `ADR-*` et `DEC-*` qui ne resolvent pas ; il n'attrape ni les
noms de classe cites par anticipation, ni les affirmations de fait sur un contenu non fusionne, ni
la section d'un fichier citee par son hash plutot que par son statut de fusion.

**C'est une dette instruite a proposer, pas une lecon a ecrire ici.** Je la route a `theo-archi` :
etendre `ToutesLesReferencesDeDecisionResolventTest`, ou un garde-fou voisin, a la famille plus
large "une decision Acceptee cite un fichier ou une section absente de l'arbre ou elle vit", est
une question de perimetre et de forme technique qui n'est pas la mienne a trancher.

### Troisieme motif, pris par l'autre bout : une exigence ecrite que personne n'a executee

Le refus pedagogique initial des cinq paquets (motif : la cinquieme phrase d'adulte manquante)
n'est pas la forme habituelle du defaut dominant. La section 14.3 de l'arbitrage de `nadia-prof`
exigeait cette phrase **avant que le contenu n'existe** : ce n'est pas une clause qui promettait
plus large que ce qu'elle verifiait, c'est **une exigence ecrite, dans le fichier que tous les
agents ont lu, qu'aucun test ne portait**. 1084 tests verts ne pouvaient pas la voir puisqu'aucun
d'eux ne la regardait.

### Ce que le vert ne prouve pas, sans l'adoucir

La liste des conditions que personne ne tient **ne diminue pas d'une unite** ce sprint : `VOI-3`,
`VOI-5`, `VOI-8`, la moitie non calculable de `VOI-13`, plus le point 2 et la moitie du point 3 de
`VOI-12`. Dans les mots de `nadia-prof` : *"la cinquieme phrase ne fait pas respecter `VOI-13` :
elle la dit. Un adulte qui laisse l'enfant lire la ligne fait echouer les cinq jeux, et rien ne le
saura."*

### Les quatre ecarts de l'orchestrateur

Trois deja journalises en cours de sprint, repris ici pour qu'ils ne se perdent pas a la cloture :

1. `CLAUDE.md` citant `DEC-0068` hors arbre, pousse sans lancer `verify`.
2. L'affectation des cinq paquets de contenu a `lea-dev` seule, sans relire la table
   d'affectation deja ecrite dans `planning.md`.
3. La consigne au `relecteur-pedagogique` citant `VOI-7` au lieu de `VOI-13` parmi les quatre
   conditions sans garde.

**Un quatrieme, nomme pour la premiere fois ici** : j'ai lance la correction de contenu de
`lea-dev` (`s26-refus`) en parallele de la fusion qui allait la doubler (`s26-theo-reco` +
`s26-marc`), ce qui a produit un moment ou l'arbre etait vert sur un contenu deja perime avant que
la jonction correcte ne soit refaite. Meme famille que le second motif ci-dessus (une branche
batie sur un etat qui a bouge sous elle), cette fois avec l'orchestrateur comme cause du
sequencement plutot que comme auteur du contenu.

## Amendements au process

Aucun amendement a `squad/README.md` ce sprint. Les deux dettes identifiees (l'extension d'`ADR-
0066` a la famille plus large des references non-`ADR`/`DEC`, et `P(bonne / presente)` non garde
par `adapter-content`) sont routees comme obstacles a instruire, pas actees comme regle de process
- la forme de leur remede n'est pas encore connue.

## Decisions prises

Aucune decision `ADR` ou `DEC` prise pendant cette retro. Voir `review.md` pour la liste complete
des decisions du sprint.
