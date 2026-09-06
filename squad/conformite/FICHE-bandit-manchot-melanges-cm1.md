# Fiche de conformite - bandit-manchot-melanges-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-melanges.yaml`.
- **Entree de catalogue** : `bandit-manchot-melanges-cm1`, titre servi *"Melanges et separations"*,
  consigne servie *"Lis bien, puis dis si le melange est homogene ou heterogene, ou choisis le bon
  moyen de le separer."*, `nombreDeManches: 10`.
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce le 2026-09-06** par
  `nadia-prof`, instance B, apres avoir joue). **`SCI-CM1-02` ferme** : `non couvert` ->
  **`couvert-valide`**. Trois reserves nommees et datees, `MEL-R1` a `MEL-R3`, aucune bloquante.
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md`,
  section 2.2 ligne 2, et `squad/programme/sciences-et-technologie/competences-cm1.md`.
- **Commit audite** : `5824e752` (`[LEA] s30: S30-2 - second lot de sciences CM1, melanges et objet
  technique`).
- **Derniere mise a jour** : 2026-09-06.

## Competence visee

`Distinguer un mélange homogène d'un mélange hétérogène et identifier un moyen de séparer les
constituants d'un mélange.` (`SCI-CM1-02`, sciences et technologie, CM1, *Matiere, mouvement,
energie, information*).

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **VALIDE SOUS RESERVE**
- **Date** : 2026-09-06
- **Audit joue** : **oui**

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `dbbfe129`, jar reconstruit depuis mon propre worktree et lance
sur le port 8081, **partie complete de dix manches par appels HTTP reels, avec une erreur volontaire
sur chacune des dix manches**, ecran de fin atteint, score 10. **Et j'ai lu les onze items**, dont
celui que le tirage a laisse de cote. **Ce n'est pas un audit degrade.**

**Ce que l'audit ne couvre pas** : j'ai lu le HTML servi, pas la page rendue par un navigateur.
Couleur, contraste, taille et position ne sont pas juges ici.

### Ce qui a ete verifie

- **Ancrage au programme** : ligne `SCI-CM1-02` declaree, libelle du descripteur **copie conforme**
  du referentiel. `FER-1` : les **trois** elements du libelle occupent la position de bonne reponse -
  *"un melange homogene"* (items 1, 4, 6), *"un melange heterogene"* (items 2, 3, 5), et **un moyen
  de separation** (items 7 a 11 : filtre, evaporation, aimant, decantation, passoire). Le libelle
  dit *"identifier **un** moyen"*, au singulier : un seul suffisait, le paquet en sert quatre.
  `FER-3` : deux taches distinctes par l'operation mentale - **classer** un melange decrit d'un
  cote, **choisir** l'outil qui separe de l'autre - six et cinq items.
- **Lisibilite au niveau vise** : la consigne fait **deux lignes** et c'est la plus longue des
  quatre entrees de sciences ; elle reste lisible par un CM1 et elle nomme les deux taches, ce qui
  est ce que `DEC-0078` demande. Les enonces sont courts et concrets : un verre, un bol, une carafe,
  des pates. **Le choix des exemples est bon** - tous sont des gestes qu'un enfant a deja vus.
- **Charge cognitive** : un seul critere par item, jamais deux. Verifie sur les onze.
- **Progression** : la tache A monte reellement - de *"on ne voit plus le sucre"* (immediat) a
  *"le melange semble uniforme, mais on voit vite deux couches se former"* (il faut attendre pour
  trancher). **C'est la meilleure progression des quatre paquets du lot.**
- **Traitement de l'erreur** : verifie a l'ecran. Les onze indices sont non vides, et **dix sur onze
  portent le critere sans nommer la categorie** - *"On distingue encore les deux produits, meme
  apres avoir remue."* C'est exactement la forme que `DRO-R2` demande, et elle est tenue sur les cinq
  items binaires. Le onzieme fait l'objet de `MEL-R1`.
- **Exactitude du contenu** : **verifiee item par item.** Les six classements sont exacts (sucre,
  sel et sirop dans l'eau : homogenes ; huile, sable et morceaux de fraise : heterogenes). Les cinq
  moyens sont exacts et correctement apparies : filtration pour le sable, evaporation pour le sel
  dissous, tri magnetique pour le fer, decantation pour la terre, egouttage pour les pates. **Aucune
  erreur de fait dans un enonce ni dans une bonne reponse.** Une formulation d'indice est fautive :
  `MEL-R1`.
- **Biais et ton** : aucune personne, aucun foyer, aucun stereotype. Le pronom indefini *"on"* est
  employe partout, ce qui est le bon choix.

### Les conditions opposables de sciences, une par une

| Condition | Etat sur ce paquet |
|---|---|
| **`SCI-1`** | **tenue.** Un seul item porte une conception fausse courante en distracteur - l'item eau/huile, ou l'on croit homogene ce qui semble uniforme juste apres avoir remue. L'indice - *"Meme remue, ce melange ne reste pas uniforme bien longtemps."* - la tue sans prononcer *"heterogene"* |
| **`SCI-2`** | **sans objet** : ligne sans condition de biais |
| **`SCI-3`** | **tenue, mesuree sur le lot** : **zero item numerique sur les 22 du lot 2** (ce paquet et `cm1-sciences-objet-technique`) |
| **`SCI-4`** | **tenue a la lettre** : l'item a conception fausse porte trois propositions. Sa mise en oeuvre fait l'objet de `MEL-R3` |
| **`DRO-R2`** etendue a tout item binaire | **tenue sur les cinq items binaires** de la tache A |
| **`CYC-1`** | **verifiee.** J'ai lu `cp-qlm-etats-eau.yaml` (`QLM-CP-05`) : il n'enseigne que solide et liquide, et rien de ce paquet ne le contredit. **Mais il ouvre une question que `MEL-R1` porte** : l'indice de l'item du sel dit que l'eau *"disparait"*, et `SCI-CM1-01` devra enseigner la vaporisation |
| **`CON-1`** | **tenue sur dix items sur onze.** Le onzieme fait l'objet de `MEL-R2` |
| **`HIS-3`** | **tenue** |
| **`ADR-0081` clause 5** | **tenue** : aucun item n'evalue une autre ligne |

### Motif du verdict

**La ligne est servie et elle ferme.** Six classements exacts sur des melanges familiers, cinq
moyens de separation reels et bien apparies, une vraie progression de difficulte, des indices qui
enseignent un critere au lieu de designer une categorie. **C'est le meilleur des quatre paquets de
sciences de s30**, et il l'est pour une raison qui merite d'etre dite a l'equipe : sur la tache A,
le distracteur est la categorie opposee et l'enfant doit trancher sur un critere observable ; sur la
tache B, les distracteurs sont d'autres moyens **reels**, pas des absurdites.

**Le compte tient meme en retirant l'item que `MEL-R2` conteste** : dix items servent encore la
ligne, ce qui est exactement le plancher de `FER-2`. **La fermeture ne repose pas sur l'item
defectueux**, et je le verifie explicitement parce que j'aurais refuse dans le cas contraire.

**J'ai envisage le refus sur l'indice de l'item du sel, et je dis pourquoi je ne le prononce pas.**
Ma regle est qu'une erreur factuelle dans un contenu scolaire est un motif de refus a elle seule. La
phrase *"Il faut faire disparaitre l'eau"* n'en est pas une au sens ou je l'applique ailleurs : ce
n'est ni un enonce, ni une bonne reponse, ni un fait affirme comme vrai - c'est une formulation
relachee dans un indice, dont **la premiere phrase est exacte et excellente**. Elle est neanmoins a
corriger, et `MEL-R1` dit pourquoi.

### Reserves

#### `MEL-R1` - l'indice de l'item du sel enseigne que l'eau disparait, et il nomme la reponse

**Ouverte le 2026-09-06, non bloquante.**

Item 8 : *"On veut recuperer le sel d'une eau salee, en gardant seulement le sel. Quel moyen
utiliser ?"* - bonne reponse *"laisser l'eau s'evaporer"*, indice *"Le sel dissous ne peut pas etre
retenu par un filtre. **Il faut faire disparaitre l'eau.**"*

**Deux defauts dans la meme phrase, et le premier compte plus que le second.**

1. **L'eau ne disparait pas, elle se vaporise.** C'est le point ou la conservation de la matiere se
   joue, et *"faire disparaitre"* est precisement la croyance que le cycle 3 doit defaire. Ce paquet
   est le premier de sciences CM1 ; `SCI-CM1-01` (etats de la matiere et changements d'etat)
   enseignera **la vaporisation et la condensation**, que `FER-1` y rend obligatoires. Un enfant qui
   aura lu ici que l'eau disparait aura a desapprendre avant d'apprendre.
2. **La phrase nomme la reponse** (`M21`) : *"faire disparaitre l'eau"* et *"laisser l'eau
   s'evaporer"* sont la meme proposition en deux mots differents.

> **Propriete a tenir** : l'indice de cet item **garde sa premiere phrase**, qui fait exactement ce
> que `SCI-1` demande, et **remplace la seconde par une phrase qui ne dit ni que l'eau disparait, ni
> ce qu'il faut faire**. La formulation est a `lea-dev`.

**Pourquoi ce n'est pas bloquant** : l'enonce et la bonne reponse sont justes, et l'enfant qui
repond juste ne lit jamais cet indice - il ne s'affiche qu'apres une erreur.

#### `MEL-R2` - l'item de la decantation ecrit sa reponse dans sa question

**Ouverte le 2026-09-06, non bloquante.**

Item 10 : *"Dans un verre d'eau trouble avec de la terre, **on laisse reposer sans y toucher pendant
longtemps**. Quel moyen a-t-on utilise pour separer la terre ?"* - bonne reponse **"laisser reposer
le melange"**.

**Constate en jouant, manche 2 de ma partie** : la reponse est ecrite dans la question, aux mots
pres. L'enfant n'a pas a savoir ce qu'est une decantation ; il lui suffit d'apparier deux fois les
memes mots. C'est la contrefacon que `CON-1` decrit, et le paquet declare pourtant *"`CON-1` : aucun
item ne se resout sans connaitre le melange qu'il decrit."*

**Ce n'est pas le meme defaut que celui qui a fait refuser `cm1-sciences-chaine-alimentaire`** : il
porte ici sur **un** item, la ligne ferme sans lui, et rien de faux n'est enseigne.

> **Propriete a tenir** : l'enonce **decrit le resultat observe** - l'eau redevenue claire, la terre
> deposee au fond - **sans nommer le geste que la bonne reponse nomme**. La formulation est a
> `lea-dev`.

#### `MEL-R3` - la troisieme proposition de l'item eau/huile ne joue pas le role que `SCI-4` lui donne

**Ouverte le 2026-09-06, non bloquante. Reserve nouvelle et datee, ce n'est pas un durcissement de
`SCI-4`** : `SCI-4` demande *"au moins trois propositions"*, l'item en porte trois, la barre que
j'ai ecrite est atteinte et je ne la deplace pas.

Le troisieme distracteur est *"un melange qui n'existe pas"*. Le motif que j'avais donne a `SCI-4`
etait qu'*"ecarter la conception fausse laisse alors deux candidates"* : ici, la conception fausse
ecartee, il reste *"un melange heterogene"* et une proposition qui n'appartient a aucune categorie
enseignee et que personne ne choisira. **L'item redevient binaire de fait**, sur le seul item du
paquet ou `SCI-4` devait proteger quelque chose.

> **Propriete a tenir** : la troisieme proposition est un **candidat plausible** - une categorie que
> l'enfant connait, ou une reponse qu'il pourrait vraiment donner - et non un bouton de remplissage.

**Meme defaut, meme jour, sur `cm1-sciences-signal-information`** (`SIG-R2`, *"un signal qui se
sent"*). **Je n'en fais pas une condition transverse** : mes clauses se posent dans l'arbitrage
prealable, jamais en cours de sprint. Ce sont deux reserves de paquet, et c'est a `camille-po` de
decider si elle en fait une condition generale a s31.

#### Trois points que je ne transforme pas en reserve, et je le dis pour qu'on ne me les redemande pas

- **L'indice de l'item du sirop de menthe parle d'une *carafe* quand l'enonce parle d'un *bol*.**
  L'enfant lit un recipient qui n'est pas dans sa question. C'est **du fond**, donc de mon ressort,
  et non de l'orthographe. **A corriger dans le meme geste que `MEL-R1`, au meme endroit et sans
  rejeu supplementaire** ; je le verifierai dans le rejeu deja paye, il ne cree pas de dette.
- **La consigne fait deux lignes** - la plus longue du lot. Elle est lisible au CM1 et elle nomme
  les deux taches, ce que je demande. Je ne la conteste pas.
- **Le paquet emploie *"un filtre"* (item 7) et *"une passoire"* (item 11) pour deux gestes de la
  meme famille.** Ce n'est pas une erreur : la passoire est bien le filtre du quotidien, et les deux
  mots meritent d'etre connus.

### Mentions de couverture partielle

**Aucune n'est due sur cette ligne.** Mon arbitrage prealable (section 2.4) enumere les **trois**
lignes de sciences qui exigent une mention - les lignes 6, 14 et 20 - et `SCI-CM1-02` n'en fait pas
partie. Le libelle dit *"identifier **un** moyen"* et le paquet en sert quatre. **La ligne ferme
sans reserve de perimetre**, et je n'en ajoute pas une apres coup.

### Mentions de non-couverture opposables

> **`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire defiler la page, un element
> quelconque du jeu, y compris le retour qui suit sa reponse - verdict, indice, proposition. Cette
> propriete est sortie de la porte pedagogique par arbitrage de l'utilisatrice du 2026-08-28
> (`DEC-0053`) ; sa dette est ouverte, croissante et assumee. Aucune mesure ne l'a instruite sur ce
> paquet.

### Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **L'exactitude de chaque classement et de chaque appariement melange / moyen** | **aucun** |
| **`SCI-1`** - que l'indice corrige une croyance | **aucun** |
| **`MEL-R2`** - qu'un enonce ne contienne pas sa reponse | **aucun**, et c'est pourtant une propriete textuelle : la bonne reponse d'un item apparait mot pour mot dans son enonce. **Route a `theo-archi`** - je dis la propriete, jamais la forme |
| **`MEL-R3`** - qu'une troisieme proposition soit plausible | **aucun** |
| **`CYC-1`** vers `SCI-CM1-01`, non encore servie | **aucun**, et elle mordra le jour ou les etats de la matiere seront servis. `MEL-R1` est ecrite pour que ce jour-la ne trouve pas une contradiction deja en place |

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`SCI-CM1-02` : `non couvert` -> `couvert-valide`**, sans mention de couverture partielle | `camille-po` | Le compte de la matrice lui appartient ; je ne la touche pas |
| **`MEL-R1`, `MEL-R2`, `MEL-R3`**, plus la correction *bol / carafe* qui voyage avec | `camille-po`, via `sofia-sm` | En faire des stories. Regime : **rejeu borne, 0,5 pour le paquet entier** (`PAR-10`) |
| **La bonne reponse d'un item apparait mot pour mot dans son enonce** | `theo-archi` | Propriete textuelle du YAML, donc mordable. Je dis la propriete, pas la forme |
| **`MEL-R1` conditionne la coherence future avec `SCI-CM1-01`** | `camille-po` | A payer avant qu'un paquet d'etats de la matiere soit ecrit, pas apres |
