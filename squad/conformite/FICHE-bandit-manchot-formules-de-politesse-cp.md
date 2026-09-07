# Fiche de conformite - Les mots de politesse (`bandit-manchot-formules-de-politesse-cp`)

- **Mecanique** : `bandit-manchot`
- **Contenu(s)** : `cp-emc-formules-de-politesse` (11 items)
- **Statut global** : **VALIDE SOUS RESERVE**
- **Derniere mise a jour** : 2026-09-07

> **Fiche ouverte par `nadia-prof` et non par `camille-po`** : le paquet est neuf et sa fiche
> n'existait pas. Sections descriptives reprises de la story `S31-1` et du referentiel ; **le bloc
> de validation est le mien**.

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| Enseignement moral et civique | CP (cycle 2) | Respecter autrui | Employer les formules de politesse et de respect dans les echanges (saluer, remercier, demander, ecouter sans couper) | `EMC-CP-05`, `squad/programme/enseignement-moral-et-civique/competences-cp.md` |

## Criteres pedagogiques attendus - camille-po

Repris de `docs/backlog/backlog.md`, story `S31-1`.

- [x] Onze items au moins, `nombreDeManches: 10` (`DEC-0064`)
- [x] Les quatre elements de la parenthese chacun en bonne reponse au moins une fois (`FER-1`)
- [x] Deux taches distinctes, la moins servie a trois items au moins (`FER-3`)
- [x] `EMC-1`, `EMC-5`, `EMC-8`, `EMC-10` tenues
- [x] `CAT-1`, `PLA-1`, `FAB-1` tenues
- [x] **Mention de couverture partielle exigee d'avance et non negociable** a la fermeture

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **VALIDE SOUS RESERVE**
- **Date** : 2026-09-07
- **Audit joue** : **oui.** Jar reconstruit depuis mon worktree `s31-nadia-A` a `3181b5e6`, port
  8082. **Une partie complete de dix manches avec trois erreurs volontaires sur chacune**, donc
  l'ecran de revelation atteint dix fois sur dix, plus un second passage partiel pour voir les deux
  items non tires.
- **Audit degrade sur un point, et je le declare** : lecture du **HTML servi**, pas de la page
  rendue par un navigateur. Textes, ordre des ecrans, enchainement apres erreur et tirage sont
  couverts ; **ni couleur, ni contraste, ni taille, ni position** ne le sont, et aucune conclusion
  de cette fiche n'en depend.

### Ce qui a ete verifie

- **Ancrage au programme** : la competence annoncee est celle travaillee, **sur sa moitie
  connaissance seulement**, ce que j'avais exige d'avance et que la mention de couverture partielle
  porte ci-dessous. Les six premiers items font choisir la formule qui convient a une situation ;
  les cinq suivants font nommer la regle d'echange qu'un comportement decrit ne respecte pas. **La
  seconde tache n'est pas une variante de la premiere** : reconnaitre une regle enfreinte a partir
  d'un comportement est l'operation inverse de choisir une formule a partir d'un besoin.
  **`FER-1` tenue** : *saluer* (bonjour, items 1, 4 ; dire bonjour, item 10), *remercier* (merci,
  items 2, 5 ; dire merci, item 9), *demander* (s'il te plait, items 3, 6 ; demander avant de
  prendre, item 8), *ecouter sans couper* (items 7, 11). Les quatre elements de la parenthese sont
  chacun en bonne reponse.
  **`FER-2` tenue** : onze items enumeres, aucun deflate par `FAB-1a`, ligne servie par ce seul
  paquet.
  **`FER-3` tenue** : deux taches, 6 et 5 items, la moins servie au-dessus du plancher de trois.
- **Lisibilite au niveau vise** : **c'est le defaut principal du paquet, et il porte `POL-R1`.**
  Mesure mecanique : les enonces vont de **15 a 26 mots**. Les cinq items de la tache B en portent
  **22, 20, 22, 23 et 26**, et quatre d'entre eux tiennent en **deux phrases**. Les deux paquets
  d'EMC CP que j'ai fermes le 2026-09-05 plafonnaient a 15 et 14 mots. **L'item 11 fait 26 mots :
  c'est le plus long enonce de CP que ce depot ait jamais servi.**
- **Charge cognitive** : basse sur la tache A (trois formules connues, une situation courte),
  **elevee sur la tache B** : l'enfant doit tenir une scene a deux personnages, identifier ce qui
  s'y passe de fautif, puis apparier ce fait a une regle formulee a l'infinitif. Trois choses en
  meme temps, dont une lecture de 25 mots. **Ce n'est pas la competence qui est trop dure, c'est
  la quantite de texte qui la porte.**
- **Progression** : la tache A precede la tache B dans le fichier, ce qui est le bon ordre. Le
  tirage etant aleatoire, l'enfant peut commencer par un item de 26 mots ; je l'ai constate en
  jouant (manche 4 de ma partie).
- **Traitement de l'erreur** : verifie a l'ecran, dix manches sur dix. L'indice s'affiche apres une
  erreur, la revelation apres trois, et l'enfant avance toujours. **Les onze indices sont courts (7
  a 12 mots), concrets, et aucun ne nomme la bonne reponse ni n'ecarte les deux distracteurs** -
  *"Tom vient pourtant de l'aider."*, *"Hugo n'a pas eu le temps de finir ce qu'il disait."*. **Ce
  sont les meilleurs indices des quatre paquets d'EMC de ce sprint**, et je le dis parce que je le
  reproche ailleurs.
- **Exactitude du contenu** : les onze appariements situation/formule sont justes. Aucun item n'a
  deux reponses defendables (`EMC-5`) : sur l'item 9 (aide recue, on repart sans un mot), *"dire
  bonjour"* et *"demander avant de prendre"* ne sont pas defendables ; sur l'item 10 (entrer en
  classe sans un mot), *"dire merci"* et *"ecouter sans couper"* non plus. **Un seul enonce me
  gene, et c'est `POL-R2`** : l'item 6 dit *"Lea voudrait un peu plus d'eau a la cantine, dans son
  verre deja vide"* - on ne demande pas *un peu plus* de ce qu'on n'a plus.
- **`CAT-1` - tenue, et tenue par construction.** Sur la tache A, les trois propositions sont
  toujours *bonjour*, *merci*, *s'il te plait* : trois formules polies, aucune grossierete. Sur la
  tache B, les trois propositions sont toujours trois regles d'echange formulees a l'identique
  (*dire bonjour*, *dire merci*, *demander avant de prendre*, *ecouter sans couper la parole*).
  **Ce que je redoutais le 2026-09-07 - la formule polie contre deux grossieretes - n'est nulle
  part.** C'est le refus n°2 de mon arbitrage prealable, nomme sur cette ligne precise ; il ne se
  realise pas.
- **`PLA-1` - tenue.** Chaque proposition est une formule ou une regle que le CP connait et pourrait
  vraiment donner. Aucun bouton de remplissage.
- **`FAB-1` - tenue sur ses trois branches.** (a) tache A : bonjour, merci, s'il te plait, bonjour,
  merci, s'il te plait - varie ; tache B : ecouter, demander, dire merci, dire bonjour, ecouter -
  varie. **Aucune tache n'est deflatee.** (b) le vivier est celui d'un classement a categories
  fermees, ce que `FAB-1` autorise, et la bonne reponse varie. (c) rien dans l'amorce, la longueur
  ni le registre ne distingue la bonne reponse.
- **`BIN-1` - sans objet** : trois propositions sur les onze items.
- **Biais et ton** : aucun stereotype. Les roles fautifs et corrects sont repartis sur les cinq
  personnages sans clivage de genre - Malo coupe la parole et oublie de remercier, Lea entre sans
  dire bonjour, Aya prend un feutre sans demander, Hugo parle par-dessus Tom. **`EMC-1` tenue** :
  aucun item n'interroge l'enfant sur lui-meme. **`EMC-6` tenue** : le seul element de contexte
  familial (item 2, *"un livre offert par son oncle"*) est dans l'enonce et attribue a un
  personnage. **Aucun ton condescendant** : les items decrivent, ils ne sermonnent pas.

### Motif

**Le paquet travaille la moitie du libelle que ce produit peut travailler, et il la travaille
proprement.** `CAT-1` est tenue sans compromis sur les onze items, `FAB-1` sur ses trois branches,
`FER-1` sur les quatre elements, et les indices sont les meilleurs de ce lot.

**Ce qui empeche le VALIDE sec est la longueur des enonces de la tache B** : cinq items de 20 a 26
mots, en deux phrases, servis a des enfants de six ans dont c'est l'annee d'apprentissage de la
lecture. **La competence tient dans ces items ; le texte qui la porte ne tient pas au niveau.** Un
enfant qui echoue faute d'avoir pu lire n'a pas echoue sur les formules de politesse, et le produit
ne saura pas faire la difference.

**S'y ajoute un enonce incoherent sur onze** (`POL-R2`), qui n'enseigne rien de faux mais fait
buter la lecture.

**La ligne `EMC-CP-05` ferme, avec sa mention de couverture partielle, qui est due et que je
refuse de laisser tomber.**

### Reserves

#### `POL-R1` - les cinq enonces de la tache B sont trop longs pour le CP

**Ouverte le 2026-09-07, non bloquante.**

| Item | Mots | Phrases |
|---|---|---|
| 7 | 24 | 2 |
| 8 | 20 | 1 |
| 9 | 22 | 1 |
| 10 | 23 | 1 |
| 11 | **26** | 1, avec deux relatives enchainees |

**Ce n'est pas une barre chiffree que je pose ici**, et je le dis pour ne pas deplacer mes poteaux :
je n'ai jamais ecrit de plafond de mots pour l'EMC CP. Le chiffre auquel je compare est **observe**
sur ce que j'ai deja accepte - 15 et 14 mots sur les deux paquets d'EMC CP fermes le 2026-09-05.

> **Propriete a tenir** : chaque enonce de la tache B decrit le comportement fautif en **une
> proposition principale**, sans relative enchainee. Les cinq scenes sont racontables en une dizaine
> de mots (*"Malo coupe la parole a Hugo qui racontait son week-end."*). La formulation appartient
> au developpeur.

#### `POL-R2` - l'enonce de l'item 6 est incoherent

**Ouverte le 2026-09-07, non bloquante.**

*"Lea voudrait **un peu plus d'eau** a la cantine, dans **son verre deja vide**. Que doit-elle
dire ?"*

On ne demande pas *un peu plus* d'un verre vide. **Ce n'est pas de l'orthographe ni de la
ponctuation** - ce controle-la appartient au `relecteur-pedagogique` (`DEC-0002`, amendement du
2026-08-04 ; `DEC-0056`) - **c'est du fond** : la scene decrite ne se tient pas, et un enfant de CP
qui la relit deux fois pour la comprendre paie ce temps sur une question qui ne porte pas la-dessus.

> **Propriete a tenir** : l'enonce dit soit *"voudrait encore de l'eau"* avec un verre presque vide,
> soit *"voudrait de l'eau"* avec un verre vide. La formulation appartient au developpeur.

**Regime des deux reserves** : **rejeu borne, 0,5 pour le paquet entier** (`PAR-10`). `POL-R2`
voyage avec `POL-R1` sans cout supplementaire.

### Points que je ne transforme pas en reserve, et je le dis pour qu'on ne me les redemande pas

- **Les items 1 et 10 portent la meme scene** (Lea, la maitresse, le matin) dans les deux taches,
  avec deux indices quasi identiques. **Ce n'est pas un defaut** : c'est exactement ce que `FER-3`
  cherche, la meme connaissance interrogee par deux operations mentales differentes.
- **La tache A sert les trois memes propositions sur ses six items.** `FAB-1` l'autorise en toutes
  lettres pour un classement a categories fermees, des lors que la bonne reponse varie - elle varie.
- **La consigne du catalogue** (*"Lis bien, puis dis quel mot employer, ou quelle regle n'est pas
  respectee."*) nomme les deux taches et est juste sur le paquet servi. Je ne la conteste pas.

### Mentions de couverture partielle

**Une est due, elle etait exigee d'avance, et elle n'est pas negociable** (arbitrage prealable de
s31, section 3.1 ligne `EMC-CP-05` ; refus n°14 ; referentiel d'EMC CP, 2026-09-03). Reprise mot
pour mot depuis sa source :

> **connaissance des formules seule ; leur emploi en situation n'est pas servi**

**Motif** : le verbe du libelle est *employer*. Ce produit verifie ce qu'un enfant **sait**, jamais
ce qu'il **fait** (`DEC-0028` : rien n'est conserve ; et refus n°13 de mon arbitrage prealable :
**aucune ligne d'EMC n'est presentee comme attestant une conduite**). Un enfant qui coche *merci*
n'a pas remercie.

**Je refuse la ligne sans cette mention.** Si la matrice passe `EMC-CP-05` a `couvert-valide` sans
la porter, le Done de cette fiche ne vaut pas pour cette ligne-la.

### Mentions de non-couverture opposables

> **`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire defiler la page, un element
> quelconque du jeu, y compris le retour qui suit sa reponse - verdict, indice, proposition. Cette
> propriete est sortie de la porte pedagogique par arbitrage de l'utilisatrice du 2026-08-28
> (`DEC-0053`) ; sa dette est ouverte, croissante et assumee. Aucune mesure ne l'a instruite sur ce
> paquet.

**Et elle mord ici plus fort qu'ailleurs** : cinq enonces de 20 a 26 mots, suivis de trois
propositions, d'un verdict et d'un indice. **Si un ecran de ce depot depasse le pli, c'est
celui-la.** Aucune mesure ne l'a instruit ; je le nomme parce que `POL-R1` et `MNC-1` se
renforcent.

### Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **`CAT-1`** - la bonne reponse ne se reconnait pas a son ton | **aucun** |
| **`EMC-5`** - aucun item a deux reponses defendables | **aucun** |
| **`PLA-1`** - chaque proposition est un candidat serieux | **aucun** |
| **La justesse de chaque appariement situation / formule** | **aucun** |
| **La mention de couverture partielle** | **aucun**, et c'est une ligne de matrice, donc invisible depuis le paquet. **Route a `camille-po`** |
| **`POL-R1`** - la longueur d'un enonce au CP | **aucun**, et c'est la seule des six qui serait mordable si une barre chiffree existait. Elle n'existe pas ; je ne la cree pas en cours de sprint |
| **`FAB-1a`, `FAB-1b`, `FAB-1c`, `BIN-1`** | **aucun a ce jour** ; verifiees a la main sur ce paquet |

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`EMC-CP-05` : `non couvert` -> `couvert-valide`, avec la mention de couverture partielle citee mot pour mot** | `camille-po` | Le compte de la matrice lui appartient ; la mention, elle, est opposable |
| **`POL-R1` et `POL-R2`** | `camille-po`, via `sofia-sm` | En faire une story. Regime : **rejeu borne, 0,5 pour le paquet entier** (`PAR-10`) |
| **Un plafond chiffre de longueur d'enonce au CP** | moi-meme, arbitrage prealable de s32 | Meme routage que sur `EMO-R1` : c'est a moi de poser cette barre avant un sprint, pas pendant |
