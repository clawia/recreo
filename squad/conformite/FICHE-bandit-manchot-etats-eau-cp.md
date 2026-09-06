# Fiche de conformite - bandit-manchot-etats-eau-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-qlm-etats-eau.yaml`.
- **Entree de catalogue** : `bandit-manchot-etats-eau-cp`, titre servi *"L'eau solide ou liquide"*, `nombreDeManches: 10` depuis s28 (`7` a s27).
- **Statut global s28** : **VALIDE** (Done pedagogique prononce le **2026-09-04** sur l'increment de s28). **La ligne `QLM-CP-05` ferme** : `en cours` -> **`couvert-valide`**.
- **Statut s27, conserve tel quel** : **VALIDE** (Done pedagogique prononce le 2026-09-03). Ligne de matrice `non couvert` -> **`en cours`** - premier paquet d'une matiere neuve, la ligne ne se ferme pas.
- **Arbitrage prealable de `nadia-prof`** : `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, sections 4 (ligne 5) et 7.1.
- **Derniere mise a jour** : 2026-09-04 (**second verdict de `nadia-prof` apres avoir rejoue**, s28).

## Competence visee

`Reconnaître les états de l'eau (solide, liquide) et les changements d'état observables.`
(Questionner le monde, CP, Le vivant, la matière et les objets).

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `d259b5f`, jar reconstruit et lance, parties reelles par appels
HTTP contre l'application, **y compris en me trompant volontairement**. Ce n'est pas un audit
degrade.

### 1. Exactitude - huit items sur huit, et la ligne est servie en entier

glace, glacon, neige -> **solide** ; jus, eau du robinet, pluie -> **liquide** ; *"quand la glace
fond"* -> **liquide** ; *"quand l'eau liquide gele"* -> **solide**. **Quatre et quatre.**

**Et la ligne a deux verbes, tous les deux servis** : *reconnaitre les etats* (les six premiers
items) **et** *les changements d'etat observables* (les deux derniers, fondre et geler).
**Aucune mention de couverture partielle n'est due sur cette ligne** - je le dis explicitement,
parce que j'en ai exige sur d'autres et qu'il faut savoir dire quand il n'y en a pas.

### 2. Le troisieme etat absent - **je confirme le choix de `lea-dev`**

Le paquet ne parle ni de vapeur ni d'etat gazeux. **C'est correct** : la ligne du referentiel que
j'ai ecrite dit *"(solide, liquide)"* et n'en nomme que deux. Un paquet de CP qui ajouterait la
vapeur travaillerait une competence de CE2, pas celle-ci. **Servir exactement la ligne, ni plus ni
moins, est ce que je demande.**

### 3. Deux propositions - meme motif qu'a `cp-qlm-vivant-non-vivant`

*Solide* contre *liquide* est une opposition binaire par construction de la ligne. Precedent
mesure et valide par moi trois fois (`cp-nature-mot`, `cm1-homophones`, `cp-son-dans-mot`).
**Accepte.**

### 4. Lisibilite - mesuree

Les huit enonces font **6, 6, 6, 6, 7, 9, 9 et 9 mots**, mediane 6,5. **Aucun outlier**, aucun mot
hors niveau. Le vocabulaire (*glace*, *glacon*, *neige*, *pluie*, *robinet*, *fondre*, *geler*)
est entierement du quotidien d'un enfant de six ans.

### 5. Joue jusqu'au bout

Partie complete, **7 manches**, ecran de fin atteint. Les indices donnent le **critere physique**
et non la reponse : *"on peut la tenir dans la main sans qu'elle coule"*, *"il coule et prend la
forme du verre"*. **C'est la meilleure serie d'indices des trois paquets de cette matiere** : elle
enseigne la propriete qui definit l'etat, ce qui est exactement la competence.

### 6. Reserves non bloquantes

- **`EAU-R1` - l'indice de l'item *"quand la glace fond"* est abstrait.** *"Fondre, c'est passer du
  solide a ce deuxieme etat"* demande a l'enfant de tenir *"ce deuxieme etat"* comme un renvoi, ce
  qui est une charge de langage inutile ici. Piste : *"Quand la glace fond, elle coule."* - meme
  information, meme critere que les autres indices du paquet, et coherent avec eux.
- **`EAU-R2`** - meme reserve que `VIV-R1` sur `nombreDeManches: 7` sans decision ecrite.


---

## Second passage - increment de s28 (S28-1, `ad522a2`) - **VALIDE**

- **Verdict** : **VALIDE**, prononce le **2026-09-04** par `nadia-prof`, apres avoir joue. **Done
  pedagogique prononce, sans reserve nouvelle bloquante.**
- **Consequence de matrice** : **`QLM-CP-05` ferme.** `camille-po` peut passer la ligne a
  `couvert-valide`. **Aucune mention de couverture partielle n'est due** : la ligne nomme deux etats
  et deux changements, les quatre sont servis.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `aac5d88`, jar reconstruit et lance, **partie complete de dix
manches par appels HTTP reels**. **Et j'ai lu les quatorze items un par un**, distracteurs compris,
parce qu'une partie n'en montre jamais quatre. **Ce n'est pas un audit degrade.**

### 1. Le perimetre au mot pres - **tenu, et c'est ce que je surveillais le plus**

J'avais **refuse d'avance** tout item introduisant l'etat gazeux, la vapeur ou l'evaporation. **J'ai
verifie les quatorze enonces, les quatorze bonnes reponses et tous les distracteurs, un par un :
aucun des trois mots n'apparait nulle part.** L'espace de reponse reste ferme a *solide*, *liquide*,
*fondre*, *geler* et *couler*. **Le second paquet tient le perimetre comme le premier l'avait
tenu.**

### 2. `FER-1`, `FER-2`, `FER-3`

- **`FER-1`** : deja tenue a s27 - les deux etats et les deux changements sont en bonne reponse -
  et rien ne la degrade. **Tenue.**
- **`FER-2`** : **14 items**, contre un plancher de dix. **Tenue.**
- **`FER-3`** : c'etait **le point qui bloquait** et il est paye exactement comme je l'avais ecrit.
  La moitie *"changements d'etat"* passe de deux a **quatre items**, **sous deux formes** : classer
  le resultat du changement (*"Quand la glace fond, l'eau est-elle solide ou liquide ?"*) et
  **nommer** le changement (*"Comment appelle-t-on le passage de la glace a l'eau liquide ?"* ->
  *fondre*). **Tenue.**

### 3. **Une ambiguite de ma propre clause, que je tranche dans le sens de l'equipe et que j'ecris**

**Il faut la dire, sinon quelqu'un la trouvera plus tard comme une contradiction.** `FER-3`, dans sa
formulation generale (arbitrage de s28, section 2), demande *"au moins deux taches distinctes, la
moins servie portant au moins trois items"*. Si l'on decoupe **au grain le plus fin** - classer un
etat (douze items) contre **nommer** un changement (**deux** items) - la moins servie est a deux, et
la clause generale ne serait pas tenue.

**Je la tranche au grain que j'ai moi-meme employe en ecrivant la commande de ce lot**, section 3.1
du meme arbitrage : j'y ai compte *"la moitie changements d'etat"* comme une tache et demande de la
porter *"a quatre items au moins, et sous une seconde forme"*. **Quatre items, deux formes : c'est
mot pour mot ce qui est livre.** L'equipe a tenu la barre que je lui ai donnee, et **une barre qu'on
relit plus finement apres coup est un poteau deplace**. Je ne le fais pas.

**Observation nouvelle et datee du 2026-09-04, non bloquante** : la forme *"nommer le changement"*
ne porte que deux items. Si cette ligne recoit un jour un troisieme paquet, **c'est cette forme qui
doit monter a trois**, pas le nombre total d'items. Ecrit ici pour que ce soit une reserve neuve et
lisible comme telle, et non une relecture retroactive de `FER-3`.

### 4. Exactitude - quatorze items lus un par un

**Aucune erreur.** Les six items neufs sont justes : verglas et banquise **solides**, flaque et lait
**liquides**, *fondre* pour le passage glace -> eau liquide, *geler* pour le passage inverse.

**Les deux items de nommage sont bien construits** : le distracteur *couler* est **l'erreur reelle**
d'un enfant de six ans, qui confond fondre et couler parce que les deux se voient en meme temps.
**Un distracteur qui nomme la confusion de l'age est ce que je demande** et c'est rare.

**Vocabulaire** : *verglas* et *banquise* sont les deux seuls mots qui ne sont pas du quotidien
immediat. **Je les accepte tous les deux** - ils appartiennent au lexique de l'hiver et des animaux
polaires, tous deux au programme de CP, et **aucun des deux ne porte la reponse** : l'enfant qui ne
connait pas *banquise* n'a de toute facon que deux propositions et l'indice (*"On peut marcher
dessus"*) lui donne le critere physique, pas la reponse.

**Lisibilite mesuree** : les quatorze enonces vont de **7 a 11 mots**, mediane **8**. **Aucun
outlier** - c'est le meilleur profil des trois paquets de cette matiere, et il ne s'est pas degrade
en passant de huit a quatorze items.

### 5. Joue jusqu'au bout

Partie complete, **dix manches**, ecran de fin atteint (*"C'est fini !"*). Les indices donnent
toujours **le critere physique et non la reponse**, y compris les deux neufs, qui donnent une scene
observable plutot qu'une definition : *"C'est ce qui arrive a un glacon laisse dehors quand il fait
chaud."* **C'est la bonne facon d'aider un enfant de six ans.**

### 6. Reserves anterieures - etat au 2026-09-04

- **`EAU-R1`** (l'indice *"Fondre, c'est passer du solide a ce deuxieme etat"*, abstrait) :
  **toujours ouverte, inchangee, toujours non bloquante.** L'item n'a pas ete retouche, et les deux
  items de nommage ajoutes a s28 **rendent la reserve moins couteuse** : l'enfant rencontre
  desormais le mot *fondre* sous une forme ou il est la reponse et non un renvoi. Ma piste de s27
  (*"Quand la glace fond, elle coule."*) reste valable si l'item est retouche. Je ne la durcis pas.
- **`EAU-R2`** (`nombreDeManches: 7` sans decision ecrite) : **levee**. La valeur repasse a `10`,
  defaut de `DEC-0064` ; le motif de `DEC-0073` ne s'applique plus a quatorze items.

---

## Reverification de s29 (`S29-7`, `6b2edf1`) - **`EAU-R1` levee**

- **Verdict** : **`EAU-R1` levee**, prononce le **2026-09-06** par `nadia-prof`, **Instance A**.
  `QLM-CP-05` reste `couvert-valide`. **Aucune ligne de matrice ne bouge.**
- **Regime et prix** : mot pour mot, compris dans les **0,25** de `S29-7`.

### Ce que j'ai verifie

**Sur le contenu servi**, item de la glace qui fond :

> `indice` : *"La glace qui fond se met a couler."* *(mon texte du 2026-09-05, mot pour mot)*
> `enonce`, `bonneReponse` et `distracteurs` : **inchanges**, comme je l'avais demande.

L'indice cesse de demander a l'enfant de tenir *"ce deuxieme etat"* comme un renvoi et lui donne le
**critere observable**, exactement comme les autres indices de ce paquet - qui sont, je l'ai ecrit a
s27, la meilleure serie d'indices de la matiere. **`EAU-R1` levee.**

### Ce que j'ai verifie **en plus**, parce que la ligne est verte

- **Les quatorze items relus un par un.** Le perimetre du paquet reste ferme a *solide*, *liquide*,
  *fondre*, *geler*, *couler* : **aucune occurrence de vapeur, d'etat gazeux ni d'evaporation**, ce
  que j'avais refuse d'avance et verifie deja deux fois. Un lot de correction est exactement le moment
  ou un perimetre se relache sans que personne ne regarde. **Il ne s'est pas relache.**
- **Le point que j'ai instruit le plus longuement, parce qu'une correction verbatim est
  exactement la ou l'on casse une propriete voisine sans le vouloir** : mon indice emploie le mot
  *couler*, et `couler` est **distracteur** des deux items de nommage (*"Comment appelle-t-on le
  passage de la glace a l'eau liquide ?"* -> `fondre`, distracteurs `geler` et `couler`). **Je le
  retiens.** L'indice de l'item 7 ne dit pas que le changement s'appelle *couler* : il dit ce que
  l'enfant **observe**, et c'est le registre de tous les autres indices du paquet. La confusion entre
  *fondre* et *couler* est l'erreur reelle de cet age, je l'ai ecrite a s28, et le paquet la traite
  correctement : il la donne a observer d'un cote, il la fait nommer de l'autre. **Rien n'est casse,
  et je le dis apres verification, pas par presomption.**
- **`FER-1`, `FER-2`, `FER-3` intactes.**

### Etat des reserves de cette ligne au 2026-09-06

| Reserve | Etat |
|---|---|
| `EAU-R1` (indice abstrait sur *fondre*) | **levee le 2026-09-06** |
| `EAU-R2` (`nombreDeManches: 7` sans decision ecrite) | levee a s28 |

**Aucune reserve ouverte sur cette ligne.**
