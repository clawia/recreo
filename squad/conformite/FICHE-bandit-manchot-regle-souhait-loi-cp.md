# Fiche de conformite - Regle, souhait ou loi (`bandit-manchot-regle-souhait-loi-cp`)

- **Mecanique** : `bandit-manchot`
- **Contenu(s)** : `cp-emc-regle-souhait-loi` (11 items)
- **Statut global** : **REFUSE**
- **Derniere mise a jour** : 2026-09-07

> **Fiche ouverte par `nadia-prof` et non par `camille-po`** : le paquet est neuf et sa fiche
> n'existait pas. Sections descriptives reprises de la story `S31-2` et du referentiel ; **le bloc
> de validation est le mien**.

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| Enseignement moral et civique | CP (cycle 2) | Acquerir et partager les valeurs de la Republique | Distinguer une regle qui vaut pour tous d'un simple souhait, et reconnaitre qu'une loi s'impose a tous | `EMC-CP-07`, `squad/programme/enseignement-moral-et-civique/competences-cp.md` |

## Criteres pedagogiques attendus - camille-po

Repris de `docs/backlog/backlog.md`, story `S31-2`.

- [x] Onze items, `nombreDeManches: 10` (`DEC-0064`)
- [x] Les trois termes du libelle chacun en bonne reponse au moins une fois (`FER-1`)
- [ ] **Deux taches distinctes, la moins servie a trois items au moins (`FER-3`)** - *la tache B se
      reduit a un item par `FAB-1a`*
- [x] Le mot *loi* n'est jamais defini dans l'enonce
- [x] `BIN-1` : au plus la moitie des items de la tache de classement portent deux propositions
- [ ] **`FAB-1` : deux items d'une meme tache ne portent jamais la meme bonne reponse mot pour mot**
      - *non tenu sur la tache B*
- [ ] **Exactitude du contenu** - *un item est faux, voir le motif*
- [x] `EMC-1`, `EMC-5`, `EMC-8`, `EMC-10` tenues

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **REFUSE**
- **Date** : 2026-09-07
- **Audit joue** : **oui.** Jar reconstruit depuis mon worktree `s31-nadia-A` a `3181b5e6`, port
  8082. **Une partie complete de dix manches avec trois erreurs volontaires sur chacune**, donc
  l'ecran de revelation atteint dix fois sur dix. Le tirage m'a servi six items de la tache A et
  quatre de la tache B.
- **Audit degrade sur un point, et je le declare** : lecture du **HTML servi**, pas de la page
  rendue par un navigateur. **Aucun motif de ce refus ne repose sur un jugement visuel** : les deux
  se recomptent sur le fichier, et le premier se verifie contre le code de la route.

### Ce qui a ete verifie

- **Ancrage au programme** : la ligne est bien celle visee, et la tache A la travaille reellement.
  Le mot *loi* n'est jamais defini dans l'enonce, il s'emploie sur des exemples concrets - **c'est
  ce que j'avais exige, c'est tenu, et je le dis avant de dire ce qui ne va pas.**
  **`FER-1` tenue** : *une regle qui vaut pour tous* (items 1, 3, 5), *un simple souhait* (2, 6),
  *une loi* (4). Les trois termes du libelle sont chacun en bonne reponse.
  **`FER-3` - la tache A est une vraie tache** (six items, bonnes reponses : regle, souhait, regle,
  loi, regle, souhait - elle varie). **La tache B n'en est pas une** : voir ci-dessous.
- **`FAB-1a` - non tenue sur la tache B, et c'est flagrant.** Les cinq items 7 a 11 portent la meme
  bonne reponse : *"tout le monde, meme les adultes"* (items 7, 8, 9, 11) et *"tout le monde, meme
  les **conducteurs** adultes"* (item 10). **Un mot change sur cinq items ; la reponse est la
  meme.** Les dix distracteurs commencent tous par *"seulement ..."*.
  Ce que `FAB-1a` dit, ecrit le matin meme et avant que ce paquet existe : *"une tache dont tous les
  items ont la meme bonne reponse compte pour **un** item au titre de `FER-2` et de `FER-3`, quel
  que soit le nom, le lieu ou le nombre qui change dans l'enonce."*
- **`FAB-1c` - non tenue sur la tache B.** La bonne reponse se reconnait a **son amorce** sur **5
  items sur 5** : elle commence par *"tout le monde"*, les distracteurs par *"seulement"*. La barre
  de `FAB-1c` est *"plus d'un tiers des items de la tache"*. **Nous sommes a cinq cinquiemes.**
  **Constate en jouant** : sur ma partie, quatre items de la tache B ont ete tires ; des le second,
  la reponse se presse sans lire l'enonce.
- **`FER-2` - non tenue, une fois `FAB-1a` appliquee.** Tache A : six items comptables. Tache B :
  **compte pour 1**. **Total : 7 comptables, contre un plancher de dix.** La ligne ne peut pas
  fermer.
- **Exactitude du contenu - un item est faux, et cela suffit a refuser.** Item 11 : *"Une loi
  interdit de traverser en dehors des passages prevus pour les pietons."*
  **C'est inexact.** L'article R412-37 du code de la route n'oblige le pieton a emprunter le passage
  que *"lorsqu'une chaussee est bordee d'emplacements reserves aux pietons ou lorsqu'il existe a
  moins de 50 metres un passage prevu a leur intention"*. Hors de ce cas, traverser ailleurs est
  licite - l'article R412-39 prevoit d'ailleurs comment le faire (perpendiculairement a l'axe de la
  chaussee). **L'enonce enonce une interdiction generale qui n'existe pas.**
  Les quatre autres faits de la tache B sont exacts et je les ai verifies un par un : feu rouge
  (item 7), ceinture pour tous les occupants (item 8), interdiction de fumer dans l'enceinte d'un
  etablissement scolaire, espaces non couverts compris (item 9), usage de l'avertisseur sonore
  limite au danger immediat (item 10, R416-1). L'item 4 de la tache A - casque obligatoire a velo
  avant douze ans, conducteur comme passager - est exact lui aussi.
  **Aucune de ces cinq sources n'est citee dans le commentaire du paquet.** `EMC-7` n'exige la
  source que pour les numeros d'urgence ; **je ne l'etends pas retroactivement a tous les faits de
  droit** - mais je constate qu'un fait de droit faux a traverse la CI, la relecture et le
  commentaire d'un paquet qui declare tenir l'exactitude, et je route la question a mon arbitrage
  prealable de s32.
- **`PLA-1` - non tenue sur l'item 10.** *"Une loi interdit de klaxonner sans raison en ville. Qui
  doit la respecter ?"* - distracteur *"seulement les enfants"*. **Un enfant de six ans ne klaxonne
  pas.** Cette proposition n'est un candidat pour personne ; l'item redevient binaire de fait,
  exactement ce que `PLA-1` decrit.
- **`CAT-1` - tenue, et je le dis franchement.** Sur la tache A, les trois propositions sont trois
  categories institutionnelles ; sur la tache B, trois categories de personnes. **Aucune n'est
  formulee de facon grossiere ou egoiste, aucune ne se reconnait a son ton.** `CAT-1` n'est pas ce
  qui refuse ce paquet - c'est `FAB-1c` (la forme du texte), qui est une autre clause et un autre
  defaut.
- **`BIN-1` - tenue, et sur les deux lectures possibles.** Trois items sur onze portent deux
  propositions (1, 2, 3). Rapporte au paquet, comme ma clause l'ecrit (*"au plus la moitie des items
  d'un paquet"*), c'est 3 sur 11. Rapporte a la tache de classement, comme le commentaire du paquet
  et le critere du backlog le lisent, c'est 3 sur 6, soit exactement la moitie. **Tenue dans les
  deux cas ; je le note parce que les deux lectures existent et que je ne veux pas qu'on me la
  redemande.**
- **Lisibilite au niveau vise** : les enonces vont de **21 a 30 mots**, mais **la question est
  longue par construction** : *"Est-ce une regle qui vaut pour tous, un simple souhait, ou une
  loi ?"* fait a elle seule 14 mots et se repete a l'identique sur six items. **Je ne la compte pas
  comme un defaut** : c'est la question meme de la competence, elle ne se raccourcit pas sans se
  fausser, et sa repetition la rend lisible d'un coup d'oeil des le deuxieme item. La situation
  decrite, elle, tient en 12 a 16 mots partout. **Ce paquet est le plus lisible des quatre de ce
  sprint.**
- **Charge cognitive** : basse. Une situation courte, une question stable, trois categories.
- **Traitement de l'erreur** : correct. Indice apres une erreur, revelation apres trois, l'enfant
  avance toujours. Les six indices de la tache A donnent le critere (*"Personne n'a le droit d'y
  echapper."*, *"C'est ce qu'elle voudrait, pas ce qui est decide."*) sans nommer la reponse. **Les
  cinq indices de la tache B, eux, disent tous la meme chose que la bonne reponse** (*"Cette loi ne
  fait pas de difference selon l'age."*), ce qui est le corollaire du defaut `FAB-1a` : quand la
  reponse ne varie pas, l'indice ne peut pas varier non plus.
- **Biais et ton** : aucun stereotype, aucune condescendance. **`EMC-1` tenue** : aucun item
  n'interroge l'enfant sur lui-meme. **`EMC-6` tenue** : aucun item ne suppose la commune, l'ecole
  ni le foyer de l'enfant. **`EMC-10` tenue** : aucune personne reelle, aucun parti, aucune
  organisation.

### Motif

**Deux motifs, l'un et l'autre suffisants.**

**1. Un fait de droit est faux.** L'item 11 affirme qu'*"une loi interdit de traverser en dehors des
passages prevus pour les pietons"*. L'obligation d'emprunter le passage n'existe que s'il s'en
trouve un a moins de 50 metres (R412-37 du code de la route). **Une erreur factuelle dans un contenu
scolaire est un motif de refus a elle seule**, et celle-ci porte sur un contenu de securite routiere
que l'enfant est cense retenir comme un reflexe.

**2. La tache B porte cinq fois la meme bonne reponse, reconnaissable a son amorce.** *"Tout le monde,
meme les adultes"* contre *"seulement X"*, sur cinq items sur cinq. C'est `FAB-1a` **et** `FAB-1c`,
toutes deux ecrites avant l'ouverture du sprint. La tache compte pour un item ; le paquet en compte
sept comptables sur dix requis ; **`FER-2` echoue et la ligne ne peut pas fermer**. Et
pedagogiquement, c'est le point qui compte le plus : **la seconde moitie du libelle - *reconnaitre
qu'une loi s'impose a tous* - n'est pas apprise, elle est devinee des le deuxieme item.**

**Ce qui doit changer, et il n'y a pas de piege** :

> **La tache B fait varier sa bonne reponse.** Elle peut demander **a qui une loi s'applique** sur
> des exemples ou la reponse differe reellement - une loi qui vise tout le monde, une loi qui vise
> les conducteurs, une loi qui vise les adultes seuls (le tabac, l'alcool) - de sorte que
> *"seulement les conducteurs"* soit vrai quelque part. L'enfant apprend alors ce que *"s'impose a
> tous"* veut dire **par contraste**, ce qui est la seule facon de l'apprendre.
> **Ou** elle change d'operation : dire **ce qui arrive quand une loi n'est pas respectee**, ou
> **qui fait la loi**. La forme appartient au developpeur ; ce qui est opposable est que deux items
> d'une meme tache ne portent pas la meme bonne reponse.
>
> **Et le distracteur *"seulement les enfants"* disparait de l'item du klaxon** : ce n'est pas un
> candidat (`PLA-1`).
>
> **Et l'item 11 dit le droit tel qu'il est** - par exemple : *"Une loi demande de traverser sur le
> passage pieton quand il y en a un tout pres."* La formulation appartient au developpeur ; **le
> fait, lui, n'est pas negociable.**

### Ce que je tiens a porter au credit du paquet, parce qu'un refus qui ne dit que le mal est injuste

- **La tache A est bonne et je ne demande rien dessus.** Six items, trois bonnes reponses qui
  varient, un critere reel (l'obligation contre le souhait), des indices qui donnent le critere sans
  donner la reponse, et le mot *loi* jamais defini - exactement ce que j'avais exige le 2026-09-03
  et redit le 2026-09-07. **Elle est reutilisable telle quelle.**
- **`CAT-1` est tenue.** C'etait le refus n°2 de mon arbitrage prealable, et il ne se realise pas
  ici.
- **`BIN-1` est tenue**, et elle a manifestement ete travaillee : le commentaire du paquet la
  documente item par item.

### Reserves

**Aucune.** Un paquet refuse ne porte pas de reserves : ses defauts sont dans le motif.

### Mentions de couverture partielle

**Sans objet** : la ligne ne ferme pas. `EMC-CP-07` reste **`non couvert`**.

### Mentions de non-couverture opposables

**Sans objet** : `MNC-1` n'est due que sur un Done `VALIDE` ou `VALIDE SOUS RESERVE`.

### Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **L'exactitude de chaque fait de droit cite** | **aucun**, et c'est le motif principal de ce refus. Un fait faux a traverse une CI verte, une relecture et un commentaire de paquet qui declarait l'exactitude tenue |
| **`PLA-1`** - *"seulement les enfants"* n'est pas un candidat sur un item de klaxon | **aucun** |
| **`FAB-1a`** - pas de bonne reponse invariante dans une tache | **aucun a ce jour**. Route a `theo-archi` le 2026-09-07 |
| **`FAB-1c`** - la bonne reponse ne se reconnait pas a son amorce | **aucun a ce jour**, et **c'est la plus mecanique des quatre** : cinq bonnes reponses sur cinq commencent par les memes deux mots, aucun distracteur ne les partage. **Un comptage l'aurait vue** |
| **`EMC-5`** - aucun item a deux reponses defendables | **aucun** |

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`EMC-CP-07` reste `non couvert`** | `camille-po` | La ligne ne ferme pas ; le compte de la matrice lui appartient |
| **Les deux motifs de ce refus** | `camille-po`, via `sofia-sm` | En faire du backlog. **La tache A est a conserver telle quelle** : la story ne reecrit que la tache B et l'item 11 |
| **`FAB-1a` et `FAB-1c` mordables** | `theo-archi` | Deja routees le 2026-09-07. Ce paquet en est la demonstration la plus nette du sprint. Je dis la propriete, jamais la forme du test |
| **Faut-il exiger la source d'un fait de droit dans le commentaire du paquet, comme `HIS-3` l'exige en histoire et `EMC-7` pour les numeros d'urgence ?** | moi-meme, arbitrage prealable de s32 | **Je ne l'etends pas retroactivement.** Mais un fait de droit faux vient de passer toutes les portes sauf la mienne, et c'est une clause a poser avant un sprint, pas pendant |
