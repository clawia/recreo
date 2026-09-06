# Fiche de conformite - bandit-manchot-signal-information-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-signal-information.yaml`.
- **Entree de catalogue** : `bandit-manchot-signal-information-cm1`, titre servi *"Signal et
  information"*, consigne servie *"Lis bien, puis dis quel est le signal, ou ce qu'il veut dire."*,
  `nombreDeManches: 10`.
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce le 2026-09-06** par
  `nadia-prof`, instance B, apres avoir joue). **`SCI-CM1-05` ferme** : `non couvert` ->
  **`couvert-valide`**. Deux reserves nommees et datees, `SIG-R1` et `SIG-R2`, aucune bloquante.
- **Premiere ligne servie de sciences et technologie CM1**, matiere qui n'avait jamais recu un seul
  item avant ce sprint.
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md`,
  section 2.2 ligne 5, et `squad/programme/sciences-et-technologie/competences-cm1.md`.
- **Commit audite** : `712c293b` (`[LEA] s30: S30-1 - premier lot de sciences CM1,
  signal-information et chaine-alimentaire`).
- **Derniere mise a jour** : 2026-09-06.

## Competence visee

`Identifier un signal et une information dans quelques objets du quotidien (feu tricolore, panneau,
alarme, écran).` (`SCI-CM1-05`, sciences et technologie, CM1, *Matiere, mouvement, energie,
information*).

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **VALIDE SOUS RESERVE**
- **Date** : 2026-09-06
- **Audit joue** : **oui**

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `dbbfe129`, jar reconstruit depuis mon propre worktree et lance
sur le port 8081, **partie complete de dix manches par appels HTTP reels, avec une erreur volontaire
sur chacune des dix manches**, ecran de fin atteint, score 10. J'ai en outre pousse une manche
jusqu'a la **revelation** (trois erreurs) pour verifier ce que l'enfant lit quand il reste bloque.
**Et j'ai lu les onze items** : le tirage en a laisse un de cote, l'item 9 (*"Une alarme incendie
sonne dans l'ecole"*) a ete joue, celui qui manquait a ma partie est l'item de l'ambulance, que j'ai
joue separement en manche 7. **Ce n'est pas un audit degrade.**

**Ce que l'audit ne couvre pas, et je le declare** : j'ai lu le **HTML servi**, pas la page rendue
par un navigateur. Couleur, contraste, taille et position ne sont pas juges ici, et rien de ce que
j'ecris n'en depend.

### Ce qui a ete verifie

- **Ancrage au programme** : la ligne annoncee est bien `SCI-CM1-05`, le paquet la declare dans
  `lignesDeReferentiel`, et le libelle du descripteur est **copie conforme** du referentiel corrige.
  `FER-1` : les deux elements du libelle occupent la position de bonne reponse - *"le signal"* par
  les six items de la tache A (*"un signal visuel"* / *"un signal sonore"*), *"l'information"* par
  les cinq items de la tache B (*"il faut s'arreter"*, *"il faut sortir du batiment"*...). `FER-2` :
  onze items servent la ligne. `FER-3` : deux taches distinctes par l'operation mentale - classer un
  signal par son canal de perception d'un cote, decoder ce que le signal veut dire de l'autre - la
  moins servie a cinq items, au-dessus du plancher de trois.
- **Lisibilite au niveau vise** : consigne d'une ligne, qui **nomme les deux taches** au lieu de la
  formule generique que `DEC-0078` proscrit a partir de s30. Enonces d'une a deux lignes, phrases
  courtes, aucun mot hors de portee d'un CM1. *"tricolore"*, *"sirene"*, *"icone"*, *"pietons"* sont
  tous du vocabulaire de cycle 3.
- **Charge cognitive** : une seule chose a tenir en tete par item - l'objet decrit et ce qu'il
  emet. Aucun item n'en demande deux.
- **Progression** : **c'est le point faible du paquet**, et il fait l'objet de `SIG-R1`. La tache A
  ne monte jamais.
- **Traitement de l'erreur** : verifie a l'ecran. Premiere erreur -> *"Ce n'est pas ca. Essaie
  encore !"* **suivi de l'indice de l'item**. Troisieme erreur -> revelation, l'enfant peut avancer.
  Les onze indices sont non vides et **aucun n'est un commentaire du geste de l'enfant** : ils sont
  vrais qu'on les lise ou non, comme `SCI-1` l'exige.
- **Exactitude du contenu** : **verifiee item par item, et je n'en suis pas la source.** Le feu
  tricolore rouge (arret) et vert (passage), le panneau rond a bordure rouge du sens interdit, le
  panneau figurant un pieton au-dessus de bandes blanches, la consigne d'evacuation sur alarme
  incendie : tous exacts, tous conformes au code de la route francais et a la consigne generale
  enseignee a l'ecole. Aucun item ne demande une valeur numerique. **Aucune erreur de fait.**
- **Biais et ton** : aucune personne nommee, aucun foyer mis en cause, aucun stereotype de genre,
  d'origine ou de milieu. *"pour reveiller quelqu'un"* est volontairement impersonnel et c'est bien.
  Aucun item ne demande a l'enfant ce que lui ou sa famille fait.

### Les conditions opposables de sciences, une par une

| Condition | Etat sur ce paquet |
|---|---|
| **`SCI-1`** - l'indice dit pourquoi la conception fausse est fausse, sans nommer la reponse | **tenue.** Un seul item porte une conception fausse courante en distracteur, celui de l'ambulance : la croyance qu'une ambulance ne serait signalee que par sa lumiere. L'indice - *"Ici, ce sont les oreilles des conducteurs qui sont prevenues avant meme qu'ils regardent."* - tue cette croyance, ne prononce pas *"sonore"*, et se lit aussi bien par l'enfant qui a repondu juste |
| **`SCI-2`** | **sans objet** : ligne sans condition de biais (arbitrage, section 2.2) |
| **`SCI-3`** - au plus un item sur six demandant une valeur numerique | **tenue, et mesuree sur le lot** : **zero item numerique sur les 22 du lot 1** (ce paquet et `cm1-sciences-chaine-alimentaire`). `SCI-3` ne se verifie pas paquet par paquet ; je l'ai verifiee sur le lot, comme je l'avais ecrit |
| **`SCI-4`** - trois propositions au moins des qu'un distracteur porte une conception fausse | **tenue a la lettre** : l'item de l'ambulance en porte trois. Sa mise en oeuvre fait l'objet de `SIG-R2` |
| **`DRO-R2`** etendue a tout item binaire du depot - l'indice porte le critere, jamais la categorie | **tenue sur les cinq items binaires.** *"C'est avec les yeux qu'on percoit ce signal, pas avec les oreilles."* est **le critere**, pas la categorie : c'est la forme que j'avais nommee comme la bonne le 2026-09-06 sur `cm1-emc-droit-devoir`, items 3, 7 et 11 |
| **`CYC-1`** | **verifiee, et vacante** : aucun paquet de questionner le monde CP servi ne porte sur les signaux. J'ai lu `cp-qlm-objet-technique.yaml` et `cp-qlm-vivant-non-vivant.yaml` : aucune intersection, donc aucune contradiction possible |
| **`CON-1`** | **tenue sur la tache B**, et c'est ce que `SIG-R1` nuance sur la tache A |
| **`HIS-3`** - chaque fait porte sa source | **tenue** : le commentaire du paquet nomme le code de la route francais et la consigne scolaire d'evacuation, et distingue ces faits de ceux qui relevent du sens commun |
| **`ADR-0081` clause 5** - un item n'est compte que sur une ligne | **tenue** : aucun des onze items n'evalue une autre ligne |

### Motif du verdict

**La ligne est servie et elle ferme.** Onze items exacts, deux taches reellement distinctes, un
retour apres erreur qui enseigne, aucun fait faux, aucun biais. La tache B - decoder l'information
qu'un signal porte - est le meilleur travail du lot : cinq items varies, distracteurs plausibles
(*"il faut aller se cacher"* sur l'alarme incendie est une erreur d'enfant reelle, pas une
absurdite), indices qui donnent une raison et non une reponse.

**Ce qui m'a fait hesiter, et pourquoi je ne refuse pas.** Sur les six items de la tache A, **cinq
donnent le canal de perception dans l'enonce lui-meme** - *"les voitures **voient** une lumiere"*,
*"se met a **sonner**"*, *"un ecran **affiche** un symbole qui clignote"*. J'ai teste la question qui
decide : *un enfant qui n'a pas la competence gagne-t-il l'item ?* **Non.** Il lui faut savoir ce que
veulent dire *visuel* et *sonore*, et c'est exactement ce que la ligne demande d'apprendre a ce
niveau. Ce n'est pas la contrefacon d'`OBJ-R2`, ou les distracteurs etaient absurdes pour l'objet
nomme et ou l'ignorance totale suffisait : ici le distracteur est la categorie opposee, et il faut
discriminer. C'est **faible et sans progression**, pas creux. `SIG-R1`.

**Je nomme le seul item de la tache A qui fait ce que les six devraient faire** : l'item 3, *"un
panneau rond et rouge indique qu'il est interdit de passer"* - **aucun verbe de perception dans
l'enonce**. L'enfant doit savoir qu'un panneau se lit. C'est la forme attendue et elle existe deja
dans le paquet ; c'est ce qui rend `SIG-R1` bon marche.

### Reserves

#### `SIG-R1` - la tache A ne monte jamais, et cinq items sur six donnent leur reponse dans l'enonce

**Ouverte le 2026-09-06, non bloquante.**

**Mesure.** Sur les six items de classement du signal : cinq portent dans l'enonce un verbe ou un
nom qui nomme le canal de perception (*voient / lumiere*, *sonner*, *affiche / clignote*, *sonner*,
*retentir sa sirene*) ; un seul, l'item 3, ne le fait pas. Cinq sont **binaires**. En partie jouee,
le tirage m'a servi cinq de ces six items sur dix manches : **la moitie de la partie se joue a
difficulte constante et nulle**.

> **Propriete a tenir** : sur la tache A, **au moins deux items ne portent aucun verbe ni aucun nom
> de perception dans leur enonce** - l'objet et son signal y sont nommes, le canal reste a deduire,
> comme l'item 3 le fait deja - **et portent trois propositions dont la troisieme est un candidat
> qu'un enfant de CM1 peut serieusement envisager.** Les formulations sont a `lea-dev` ; je donne la
> propriete, je la rejouerai.

**Pourquoi ce n'est pas bloquant** : rien de faux n'est enseigne, et le vocabulaire *visuel /
sonore* est reellement acquis par ces items. L'enfant apprend moins qu'il ne pourrait, il n'apprend
pas de travers.

#### `SIG-R2` - la troisieme proposition de l'item de l'ambulance ne joue pas le role que `SCI-4` lui donne

**Ouverte le 2026-09-06, non bloquante. Reserve nouvelle et datee, ce n'est pas un durcissement de
`SCI-4`** - `SCI-4` demande *"au moins trois propositions"* et l'item en porte trois ; la barre que
j'ai ecrite est atteinte, et je ne la deplace pas.

**Ce que j'ai vu en jouant.** Le troisieme distracteur est *"un signal qui se sent"*. Le motif que
j'avais donne a `SCI-4` etait qu'*"ecarter la conception fausse laisse alors deux candidates"*. Ici
la conception fausse ecartee, il reste *"un signal sonore"* et *"un signal qui se sent"* - la
seconde n'est pas une candidate : la formule n'appartient a aucune categorie enseignee, elle est
ambigue en francais (*se sentir* : etre senti par l'odorat, ou etre eprouve), et **aucun enfant de
CM1 ne la choisira**. L'item redevient binaire de fait.

> **Propriete a tenir** : sur tout item de sciences ou `SCI-4` impose une troisieme proposition,
> **cette troisieme proposition est un candidat plausible** - une categorie que l'enfant connait, ou
> une reponse qu'il pourrait vraiment donner - **et non un bouton de remplissage**. Les formulations
> sont a `lea-dev`.

**Portee** : cette propriete vaut au-dela de ce paquet ; le meme defaut existe sur
`cm1-sciences-melanges` (*"un melange qui n'existe pas"*), ou il est nomme `MEL-R3`. **Je ne cree
pas de condition transverse en cours de sprint** - mes clauses se posent dans l'arbitrage prealable,
jamais pendant. Ce sont donc **deux reserves de paquet**, et c'est a `camille-po` de decider si elle
en fait une condition generale a s31.

### Mentions de couverture partielle

**Aucune n'est due sur cette ligne.** Mon arbitrage prealable (section 2.4) enumere les **trois**
lignes de sciences qui exigent une mention - les lignes 6, 14 et 20 - et `SCI-CM1-05` n'en fait pas
partie. La parenthese du libelle (*feu tricolore, panneau, alarme, ecran*) est **exemplative**, et
le paquet sert les quatre exemples. **La ligne ferme sans reserve de perimetre.**

### Mentions de non-couverture opposables

> **`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire defiler la page, un element
> quelconque du jeu, y compris le retour qui suit sa reponse - verdict, indice, proposition. Cette
> propriete est sortie de la porte pedagogique par arbitrage de l'utilisatrice du 2026-08-28
> (`DEC-0053`) ; sa dette est ouverte, croissante et assumee. Aucune mesure ne l'a instruite sur ce
> paquet.

### Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **L'exactitude des faits** - code de la route, consigne d'evacuation | **aucun.** Aucun test du depot ne sait qu'un enonce scolaire est vrai |
| **`SCI-1`** - que l'indice corrige une croyance | **aucun.** Un test peut verifier que l'indice est non vide, jamais qu'il corrige quelque chose |
| **`SCI-3`** sur le lot | **aucun**, faute d'une declaration de type par item. Route a `theo-archi`, sans prescrire la forme |
| **`SIG-R1`** - qu'un enonce ne donne pas sa reponse | **aucun** |
| **`SIG-R2`** - qu'une troisieme proposition soit plausible | **aucun**, et c'est la famille qui a produit le plus de reserves de s27 a s30 |
| **`FER-1`** et **`FER-3`** | **jugements** : ce qu'est *un element du libelle* et *une autre tache mentale* ne se calcule pas. `FER-2` est derivable depuis `ADR-0081` maintenant que `SCI-CM1-05` est allouee et declaree |

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`SCI-CM1-05` : `non couvert` -> `couvert-valide`**, sans mention de couverture partielle | `camille-po` | Le compte de la matrice lui appartient ; je ne la touche pas |
| **`SIG-R1` et `SIG-R2`** | `camille-po`, via `sofia-sm` | En faire des stories. Regime : **rejeu borne, 0,5 pour le paquet entier** (`PAR-10` : un rejeu se paie par paquet, pas par reserve) |
| **`SCI-3` et `SCI-4` deviennent mordables si le contenu declare le type de chaque item** | `theo-archi` | Je dis la propriete, jamais la forme. Deuxieme sprint de suite ou je la route |
