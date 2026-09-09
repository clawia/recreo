# Fiche de conformite - Regle, souhait ou loi (`bandit-manchot-regle-souhait-loi-cp`)

- **Mecanique** : `bandit-manchot`
- **Contenu(s)** : `cp-emc-regle-souhait-loi` (11 items)
- **Statut global** : **REFUSE** (prononce le 2026-09-07, **maintenu au reaudit du 2026-09-08 sur un
  seul motif**, **maintenu au troisieme passage du 2026-09-09 sur un motif entierement neuf** - les
  trois blocs de validation sont lisibles ci-dessous, dans cet ordre, et aucun n'est retouche)
- **Derniere mise a jour** : 2026-09-09

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

---

## Bloc de validation pedagogique - reaudit du 2026-09-08 - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. **Il s'ajoute au bloc du
> 2026-09-07 et ne le remplace pas.**

- **Verdict** : **REFUSE - maintenu**, sur **un seul** motif. **Le motif principal du 2026-09-07 -
  le fait de droit faux - est paye et ne se redemandera pas.**
- **Date** : 2026-09-08.
- **Arbre reaudite** : `round-two` a `60bde989`, worktree dedie `s31-nadia-A2`. Correctif de
  `marc-dev`.
- **Audit joue** : **oui, une partie complete de dix manches avec trois erreurs volontaires sur
  chacune**, donc **dix revelations**. Jar reconstruit depuis mon worktree, port 8092. Le tirage m'a
  servi dix items sur onze, **dont les cinq items de la tache B** ; le onzieme (le casque a velo,
  tache A inchangee) lu dans le fichier.
- **Audit degrade sur un point, et je le declare** : lecture du **HTML servi**, pas de la page
  rendue par un navigateur. **Le motif de ce refus ne repose sur aucun jugement visuel** : il se
  recompte sur le fichier, et je donne les deux comptes.
- **Porte technique** : rouge sur cet arbre, pour un motif etranger au contenu (`ZZZ-9` cite en
  preuve dans un rapport de ceremonie). **Verifie moi-meme** : `PaquetCpEmcRegleSouhaitLoiTest`,
  **7 tests, 0 echec** (`-pl adapters/adapter-content -am`) ; les deux garde-fous d'`ADR-0086`,
  **7 tests, 0 echec** (`-pl bootstrap -am`). **Ce paquet ne plante pas et je l'ai joue jusqu'a la
  fin de partie** : ce refus est pedagogique, pas technique.

### Ce qui est paye, acquis, et ne se redemandera pas

**1. Le fait de droit faux est corrige - c'etait mon motif principal.**
L'ancien item 11 (*"une loi interdit de traverser en dehors des passages prevus pour les pietons"*)
est **retire**. Son remplacant porte sur la vente d'alcool aux mineurs, **et je l'ai verifie contre
sa source avant de le crediter** : l'article L3342-1 du code de la sante publique interdit la vente
de boissons alcooliques aux mineurs, la majorite est a dix-huit ans, donc *"seulement les adultes"*
est la consequence exacte de l'interdiction que l'enonce cite. **Exact.**

**Les quatre autres faits de la tache B, reverifies un par un sur le materiau corrige** : arret au
feu rouge, ceinture pour le conducteur et tous les passagers, interdiction de fumer dans l'enceinte
d'un etablissement scolaire sans exception pour les adultes, avertisseur sonore limite au danger
immediat. **Exacts.** L'item 4 de la tache A (casque a velo avant douze ans) l'est aussi.

**2. `FAB-1a` est tenue, et le contre-exemple que je demandais existe.**
La tache B sert **cinq bonnes reponses entierement distinctes** la ou elle en servait une seule a un
mot pres : *tout le monde, y compris les adultes* / *le conducteur et tous les passagers* /
*personne, pas meme les adultes* / *tout conducteur, meme adulte* / *seulement les adultes*.
**L'item de l'alcool est un vrai progres pedagogique** : la loi n'y vise que les adultes, donc
*"seulement ..."* est vrai quelque part, ce que je reclamais pour que *"s'impose a tous"* s'apprenne
par contraste. **Je le porte au credit du correctif.**

**3. `FER-2` est tenue, recomptee plutot que declaree.** Tache A : 6 items, 3 bonnes reponses
distinctes, **6 comptables**. Tache B : 5 items, 5 bonnes reponses distinctes, **5 comptables**.
**Total 11, sur un plancher de dix.** Le compte de 7 comptables sur 10 qui refusait ce paquet le
2026-09-07 **n'est plus un motif**.

**4. `PLA-1` est payee sur l'item du klaxon** : *"seulement les enfants"*, qu'aucun enfant de six ans
ne peut envisager, cede la place a *"seulement les motards"* et *"seulement les chauffeurs de
taxi"*, deux categories d'usagers qui klaxonnent reellement.

**5. La tache A est inchangee, comme je l'avais demande, et elle reste bonne.** `FER-1`, `BIN-1`
(trois items binaires sur onze), `CAT-1`, `EMC-1`, `EMC-5`, `EMC-8`, `EMC-10` : tenues,
reverifiees. **`CAT-1` n'est pas ce qui refuse ce paquet, et ne l'a jamais ete.**

### Motif - un seul, `FAB-1c`, avec son compte

**Ma clause, ecrite le 2026-09-07 avant que les lots existent** : *"la bonne reponse ne se reconnait
pas a la **forme** de son texte - son amorce, sa longueur, son registre - sur **plus d'un tiers** des
items de la tache."* Cinq items : la barre autorise **un** item, pas deux.

**Ce que la tache B sert, releve a l'ecran dans l'ordre reel des propositions apres brassage** :

| Item | Bonne reponse | Les deux distracteurs | La bonne reponse est-elle la seule sans *seulement* ? |
|---|---|---|---|
| feu rouge | *tout le monde, y compris les adultes* | *seulement les enfants a velo*, *seulement les pietons* | **oui** |
| ceinture | *le conducteur et tous les passagers* | *seulement le conducteur*, *seulement les enfants a l'arriere* | **oui** |
| tabac a l'ecole | *personne, pas meme les adultes* | *les enseignants seulement*, *les visiteurs seulement* | **oui** |
| klaxon | *tout conducteur, meme adulte* | *seulement les motards*, *seulement les chauffeurs de taxi* | **oui** |
| alcool | *seulement les adultes* | *tout le monde, meme les enfants*, *seulement les enfants* | non - **le seul** |

**Deux comptes, parce que les deux lectures existent et que je ne veux pas qu'on me demande laquelle
je voulais dire :**

- **strictement sur l'amorce**, le premier mot : la bonne reponse est la seule proposition qui ne
  commence pas par *seulement* sur **3 items sur 5** (feu rouge, ceinture, klaxon) ;
- **sur la presence du marqueur restrictif, ou qu'il se trouve dans la proposition** : **4 items sur
  5**, l'item du tabac s'y ajoutant, ses deux distracteurs posant *seulement* en fin de proposition.

**Ma barre autorise un item. Le compte le plus favorable au paquet en donne trois.** Et l'ecart n'est
pas marginal : il faudrait que **quatre** items sur cinq cessent d'etre reconnaissables ; **un seul**
l'a cesse.

**Ce que cela fait a l'enfant, et c'est la raison d'etre de cette clause.** Un enfant qui ne sait
rien du droit applique *"j'evite le bouton qui commence par seulement"* et **gagne quatre manches sur
cinq sans lire l'enonce**. La seconde moitie du libelle - *reconnaitre qu'une loi s'impose a tous* -
reste donc gagnee **par la forme des boutons**. Ce n'est plus par une formule unique repetee, comme
au premier passage, mais c'est toujours **sans la competence** : `CON-1`. **C'est exactement le motif
que j'avais chiffre a 5 items sur 5 le 2026-09-07 ; il est aujourd'hui a 3 ou 4 sur 5, et ma barre
est a 1.**

**Ce qui fermerait la ligne - je dis la propriete et le compte, jamais le texte** :

> **Au plus un item de la tache B laisse la bonne reponse comme seule proposition depourvue du
> marqueur restrictif.** Les quatre autres l'obtiennent de l'une ou l'autre facon : la bonne reponse
> **est** elle-meme une restriction vraie - l'item de l'alcool montre que c'est faisable, et le droit
> en fournit d'autres, ce qui n'est permis qu'a partir d'un age, ce qui n'oblige qu'une categorie
> d'usagers - **ou** l'un des deux distracteurs est lui-meme une proposition universelle et fausse,
> de sorte que le marqueur ne trie plus rien. **La forme appartient au developpeur ; ce qui est
> opposable est le compte.**

### Ce que je corrige dans ma propre phrase, avant qu'on me l'oppose

**Mon refus du 2026-09-07, dans le paragraphe qui prescrivait la sortie, disait** : *"de sorte que
'seulement les conducteurs' soit vrai **quelque part**"*. **`marc-dev` a fait exactement cela** : la
restriction est vraie sur un item. **Ma phrase est satisfaite, et elle etait trop faible pour la
clause qu'elle etait censee servir** - *quelque part* dit un item, `FAB-1c` en exige quatre.

**C'est ma faute de redaction, et c'est la troisieme de la meme famille** : `EMC-3`, ou j'avais fige
une chaine de caracteres au lieu d'une categorie ; `SCI-4`, ou j'avais compte des propositions au
lieu d'exiger des candidats ; et cette phrase-ci, ou j'ai donne un exemple la ou il fallait un
compte.

**Ce que cela change au verdict : rien, et je dis pourquoi sans me menager.** `FAB-1c` est une clause
ecrite et datee **avant l'ouverture du sprint**, citee **nommement** dans le motif de mon refus
(*"C'est `FAB-1a` **et** `FAB-1c`, toutes deux ecrites avant l'ouverture du sprint"*), et son compte
n'a pas bouge d'un chiffre. **Je ne releve aucune barre : je recompte la meme clause, sur la meme
tache, avec le meme denominateur.** Ce que je remplace est ma **prescription**, plus faible que ma
clause, par le compte ci-dessus - pour que le prochain correctif n'ait rien a deviner.

**Et je nomme la direction de la pression, parce que c'est le meilleur test de l'honnetete d'un
verdict** : lever ce refus fermait une seconde ligne d'EMC, epargnait un aller-retour a l'equipe et
clotait le sprint plus haut. **Personne ne m'aurait demande ce comptage. C'est precisement pour cela
que je l'ai fait.**

### Reserves

**Aucune.** Un paquet refuse ne porte pas de reserves : son defaut est dans son motif, et il se paie
par la reecriture de la seule tache B.

### Mentions de couverture partielle

**Sans objet** : la ligne ne ferme pas. **`EMC-CP-07` reste `non couvert`**, et **elle ne fermera pas
a s31** : ma borne de 4,0 est atteinte exactement avec les deux rejeux du 2026-09-08, et j'avais
ecrit d'avance qu'*"une ligne ne ferme pas quand meme"* faute de budget. Le motif ci-dessus est ecrit
pour etre repris en story, pas pour etre rejoue aujourd'hui.

### Mentions de non-couverture opposables

**Sans objet** : `MNC-1` n'est due que sur un Done `VALIDE` ou `VALIDE SOUS RESERVE`.

### Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **`FAB-1c`** - la bonne reponse ne se reconnait pas a la forme de son texte | **aucun**, et c'est le fait le plus utile de ce reaudit : **les deux garde-fous d'`ADR-0086` sont verts sur ce paquet, mesure, et `FAB-1c` y est fausse sur 3 a 4 items sur 5.** **La clause 2 ne peut pas voir ce defaut-la** : elle compare le moule des **quatre premiers mots** de propositions de meme longueur ne differant qu'en **une** position, or les distracteurs fautifs font trois mots (*seulement les pietons*, *seulement le conducteur*, *seulement les motards*) ou different en plusieurs positions. **Le marqueur qui trie l'ecran pour l'enfant est un mot unique en tete, pas un moule de quatre mots.** Je ne prescris aucun seuil |
| **`FAB-1a`** | **mordue par paquet** depuis ce correctif (`fab1aLaTacheDePorteeNePorteJamaisUneReponseInvariante`). **Elle est verte, et elle ne dit rien de `FAB-1c`** : c'est exactement le cas ou l'equipe croira couvert ce qui ne l'est pas |
| **L'exactitude de chaque fait de droit cite** | **aucun.** Le fait faux est corrige ; **son remplacant, je l'ai verifie contre sa source avant de le crediter**, et aucun garde-fou ne l'aurait fait |
| **`PLA-1`** | **aucun**. Payee sur l'item du klaxon par une reecriture, pas par un test |
| **`CAT-1`**, **`EMC-5`** | **aucun**. Tenues sur ce paquet |

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`EMC-CP-07` reste `non couvert`** | `camille-po` | La ligne ne ferme pas, **et elle ne ferme pas a s31** : la borne est atteinte. Le compte de la matrice lui appartient |
| **Le motif unique, avec son compte et sa sortie** | `camille-po`, via `sofia-sm` | En faire une story de s32. **La tache A reste a conserver telle quelle, et les cinq faits de droit exacts aussi** : la story ne touche que la forme des propositions de la tache B |
| **Le marqueur restrictif en tete de proposition, non couvert par la clause 2 d'`ADR-0086`** | `theo-archi` | **Fait mesure, avec sa cause.** Je dis la propriete, jamais la forme du test, et je ne prescris aucun seuil |
| **La source d'un fait de droit dans le commentaire d'un paquet** | moi-meme, arbitrage prealable de s32 | Deja due depuis hier. **`marc-dev` a cite ses sources cette fois-ci, de lui-meme, et cela m'a fait gagner du temps** - raison de plus pour que la clause existe avant le prochain sprint plutot que pendant |


---

## Bloc de validation pedagogique - troisieme passage du 2026-09-09 - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. **Il s'ajoute aux deux blocs du
> 2026-09-07 et du 2026-09-08 et ne les remplace pas.**

- **Verdict** : **REFUSE - troisieme, sur un motif entierement neuf.** **`FAB-1c`, le motif unique
  du 2026-09-08, est paye, et il ne se redemandera pas.** Les cinq faits de droit, `FAB-1a` et
  `FER-2`, deja acquis au reaudit precedent, ne se redemandent pas non plus.
- **Date** : 2026-09-09.
- **Arbre audite** : `round-two` a `fd19d18f`, worktree dedie `s32-nadia-b`. Correctifs de
  `lea-dev` : `167a4421` (`S32-EMCCP07`) et `c0c62792` (partitif restitue).
- **Audit joue** : **oui, une partie complete de dix manches**, erreurs volontaires jusqu'a
  epuisement des propositions sur chaque manche dont la premiere reponse n'etait pas la bonne. Jar
  reconstruit depuis mon worktree, lance sur le JDK que designe la toolchain 21 (version rapportee
  par la machine virtuelle : 21.0.10), port local. **Le tirage m'a servi dix items sur onze** ; le
  onzieme (item 3, *"Dans la cour, il est interdit de pousser un camarade."*) lu dans le fichier.
  **Le releve des dix manches, propositions dans l'ordre reel apres brassage, est dans
  `squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/verdicts-nadia-instance-B.md`,
  section 3.** C'est la trace ecrite d'une partie jouee que `yanis-qa` a constatee absente du depot,
  et elle existe desormais pour les six paquets d'EMC CP de ce sprint.
- **Audit degrade sur un point, et je le declare** : lecture du **HTML servi**, pas de la page
  rendue par un navigateur. **Aucun des deux points de ce refus ne repose sur un jugement visuel** :
  les deux se recomptent sur le fichier, et je donne les comptes.
- **Porte technique** : **les quatre jobs de la CI lances sur mon diff et nommes un par un** -
  `prefixes`, `chemins`, `outils`, `build`. Chiffres et methode dans mon releve, section 9. Mon diff
  ne touche aucun fichier de contenu ni de code : il ajoute des blocs de fiche, un releve de sprint
  et sept allocations d'identifiant.

### Ce qui est paye, acquis, et ne se redemandera pas

**1. `FAB-1c` est payee, et je donne le compte plutot que l'impression.** Ma barre du 2026-09-08 :
*"au plus un item de la tache B laisse la bonne reponse comme seule proposition depourvue du marqueur
restrictif."* Releve item par item, sur les propositions telles que l'ecran les sert :

| Item | Bonne reponse | Distracteurs | La bonne reponse est-elle la seule sans marqueur restrictif ? |
|---|---|---|---|
| 7 - feu rouge | *tout le monde, y compris les adultes* | *les motards et les cyclistes*, *seulement les pietons* | non |
| 8 - ceinture | *le conducteur et tous les passagers* | *seulement le conducteur*, *les enfants et les adultes a l'arriere* | non |
| 9 - tabac | *personne, pas meme les adultes* | *les enseignants seulement*, *les adultes de l'ecole* | non |
| 10 - klaxon | *tout conducteur, meme adulte* | *seulement les motards*, *les chauffeurs de bus et de taxi* | non |
| 11 - alcool | *seulement les adultes* | *tout le monde, meme les enfants*, *seulement les enfants* | non - elle porte elle-meme le marqueur |

**Zero item sur cinq, la ou ma barre en autorise un et la ou j'en comptais trois a quatre le
2026-09-08.** La strategie *"j'evite le bouton qui commence par seulement"* ne gagne plus aucune
manche. **`FAB-1c` est tenue sur ce paquet, et c'est mieux que ce que ma barre demandait.**

**2. Les cinq faits de droit, reverifies un par un** - arret au feu rouge pour tous, ceinture pour le
conducteur et tous les passagers, interdiction de fumer dans l'enceinte scolaire sans exception pour
les adultes, avertisseur sonore limite au danger immediat, interdiction de vendre de l'alcool aux
mineurs. **Exacts.** Le fait de la tache A (casque a velo obligatoire avant douze ans) est exact
aussi. **L'item 11 dit desormais *"aux mineurs"*, le terme du code de la sante publique, et son
enonce est grammatical.**

**3. `LON-1` et `LON-2` sont tenues, mesurees sur les onze enonces** sous ma convention de comptage
(section 16.1 de mon arbitrage prealable de s32) : **de 12 a 15 mots, mediane 14, deux phrases au
plus, la seconde etant toujours la question.** Le paquet allait de **13 a 30 mots, mediane 24**,
quand j'ai pose la barre. **C'est le plus gros ecart de lisibilite paye sur un paquet de CP depuis
que je tiens cette porte, et je le porte au credit du correctif.**

**4. Le partitif de l'item 11 est restitue, et l'enonce y perd deux mots au lieu d'en gagner.**
*"vendre de l'alcool"*, 13 mots. **Ma barre avait produit du francais incorrect** - *"vendre
l'alcool"* - et c'est `relecteur-pedagogique` qui l'a trouve. `lea-dev` a ensuite trouve d'elle-meme
deux referents casses par la meme compression sur deux autres paquets. **Ma barre est necessaire,
jamais suffisante : je l'avais ecrit en section 16.6, et ce sprint vient de le mesurer contre moi.**

### Motif - deux points, et aucun des deux n'est celui du 2026-09-08

#### Point 1, principal - l'item 4 de la tache A note une distinction absente du libelle, et sa bonne reponse n'est pas la seule vraie

**Joue, manche 7 de ma partie.** Enonce servi : *"En France, le casque a velo est obligatoire avant
douze ans. Est-ce une regle ?"*. Trois boutons : *une loi*, *une regle qui vaut pour tous*, *un
simple souhait*. **La bonne reponse est *une loi*, et *une regle qui vaut pour tous* est marquee
fausse.**

**Ce que l'ecran dit alors a l'enfant est faux.** Une loi qui oblige tous les enfants de France a
porter un casque **est** une regle qui vaut pour tous. L'enfant qui le comprend et qui presse ce
bouton lit *"Ce n'est pas ca. Essaie encore !"*. **Ce n'est pas un distracteur plausible mais faux :
c'est une proposition vraie declaree fausse**, et elle est vraie dans les mots exacts du libelle de
la ligne.

**Et la distinction que l'item note n'est pas celle de la ligne.** `EMC-CP-07` dit : *"Distinguer une
regle qui vaut pour tous d'un simple souhait, et reconnaitre qu'une loi s'impose a tous."* Les deux
moities sont **regle contre souhait** et **une loi s'impose a tous**. **Distinguer une regle d'une
loi n'y figure pas** - c'est une troisieme discrimination, et c'est la seule que l'item 4 note. Le
critere reel (l'obligation vient de l'Etat et non de l'ecole) n'est ecrit que dans l'**indice**, qui
ne s'affiche qu'apres l'erreur : au moment de choisir, rien a l'ecran ne dit que *regle* veut dire
*regle de l'ecole*.

**Pourquoi ce point est un refus et non une reserve, et j'applique le test que j'ai deja applique sur
ce paquet.** Mon premier refus, le 2026-09-07, portait sur **un seul item** - un fait de droit faux.
Un item qui dit quelque chose de faux a un enfant est un motif de refus a lui seul, et ce paquet en
porte le precedent. **Refuser ici, c'est appliquer ma propre jurisprudence ; valider serait la
suspendre pour fermer une ligne.**

**Ce que je corrige dans mes propres constats, avant qu'on me l'oppose - et cette fois la faute est
entiere.** J'ai ecrit **deux textes le meme jour, le 2026-09-08, qui ne disaient pas la meme chose de
la tache A.** Le bloc de reaudit de cette fiche : *"La tache A est inchangee, comme je l'avais
demande, et elle reste bonne. `EMC-5` : tenue, reverifiee"*, puis, dans ce que je routais : *"La
tache A reste a conserver telle quelle."* Et la section 16.5 de mon arbitrage prealable, le meme
jour : *"`LON-1` mord sur le meme paquet, dans le meme geste, et supprimer l'enumeration de la
question retire d'un coup entre 9 et 11 mots sur chacun des onze items."* **`lea-dev` a suivi le
second, ce qui etait le choix raisonnable : une barre chiffree opposable pese plus qu'une phrase de
fiche.** La question enumeree *"Est-ce une regle qui vaut pour tous, un simple souhait, ou une
loi ?"* est devenue *"Est-ce une regle ?"*, et c'est cette compression qui met l'ambiguite de
l'item 4 en pleine lumiere.

**Deux choses a dire, et je ne me menage sur aucune des deux.** L'ambiguite **preexistait** a la
compression : avec l'enumeration, l'enfant comprenait qu'il choisissait parmi trois categories, et
c'est ce qui la masquait - **je l'ai creditee `EMC-5` deux fois, le 2026-09-07 et le 2026-09-08, sans
la voir.** Et la compression qui l'a mise a nu vient de mon propre texte. **Le defaut est donc a mon
debit deux fois : je ne l'ai pas vu, et j'ai prescrit le geste qui l'a rendu visible.** Cela ne rend
pas l'item servable ; cela veut dire que ce refus ne reproche rien a `lea-dev`, et je l'ecris ici
pour que personne ne le lise comme un correctif rate.

#### Point 2 - l'item 11 porte un distracteur mort, et c'est la chaine de caracteres que j'ai moi-meme condamnee la veille sur ce paquet

**Item 11** : *"Une loi interdit de vendre de l'alcool aux mineurs. Qui peut en acheter ?"* - bonne
reponse *seulement les adultes*, distracteurs *tout le monde, meme les enfants* et **`seulement les
enfants`**. Qu'il n'y ait que les enfants qui puissent acheter de l'alcool n'est envisageable par
aucun enfant de six ans : **l'item est binaire de fait** (`PLA-1`).

**Et voici ce que je dois dire sur moi.** Mon bloc du 2026-09-08 credite `PLA-1` en ces termes :
*"`PLA-1` est payee sur l'item du klaxon : **seulement les enfants**, qu'aucun enfant de six ans ne
peut envisager, cede la place a seulement les motards et seulement les chauffeurs de taxi."* **La
meme chaine survit sur l'item 11 du meme paquet, et je l'ai lue le meme jour sans la voir** - j'ai
meme porte cet item au credit du correctif (*"un vrai progres pedagogique"*), ce qu'il est par
ailleurs. **Ce n'est pas un poteau deplace, c'est un poteau que j'avais plante et devant lequel je
suis passee sans regarder.** C'est ma quatrieme correction de constat de la meme famille, apres
`EMC-3`, `SCI-4` et ma prescription trop faible de `FAB-1c`.

### Ce que j'ai verifie et qui ne refuse pas ce paquet - dit pour qu'on ne me le redemande pas, et pour qu'on ne le corrige pas par erreur

- **Le marqueur d'universalite en miroir : je le constate, je le mesure, et je ne le compte pas
  contre le paquet.** Sur quatre des cinq items de la tache B, la bonne reponse est la seule
  proposition qui porte un marqueur d'universalite (*tout le monde*, *tous les passagers*, *personne,
  pas meme*, *tout conducteur*). Une strategie *"je prends le bouton qui dit tout le monde"* gagne
  quatre manches sur cinq et perd la cinquieme, celle de l'alcool. **Je ne refuse pas sur ce point,
  pour deux raisons que je veux ecrites** : l'universalite **est** la substance de la seconde moitie
  du libelle - *reconnaitre qu'une loi s'impose a tous* -, donc l'exiger absente reviendrait a
  interdire a la bonne reponse de dire ce que la ligne enseigne ; et **ma propre prescription du
  2026-09-08 offrait deux routes**, dont celle que `lea-dev` a prise. **Elle a atteint la barre que
  j'avais ecrite. Je ne la releve pas apres coup** - c'est exactement la faute que je m'interdis.
  L'item 11 reste le contre-exemple qui empeche la strategie de gagner toujours, et c'est ce que je
  reclamais.
- **`CAT-1` est tenue sur ce paquet** : aucun item n'oppose une proposition polie a des propositions
  grossieres. Verifie sur les onze items, comme aux deux passages precedents.
- **La consigne du catalogue** (*"Lis bien, puis dis ce que c'est, ou qui doit la respecter."*) nomme
  les deux taches et reste juste apres la reecriture des questions. **Ce n'est pas un `EXC-R1` de
  plus**, et je le dis parce que le meme defaut existe a cote sur `exclusion`.
- **`FER-1`, `BIN-1`, `FER-2`, `EMC-1`, `EMC-8`, `EMC-10`** : tenues, recomptees sur le materiau
  servi.

### Ce qui fermerait la ligne - je dis les proprietes et leur compte, jamais le texte

> **1. Aucun item ne marque fausse une proposition vraie de la situation decrite** (`EMC-5`). Sur
> l'item 4, cela se paie de deux facons au moins, et **le choix appartient au developpeur** : soit la
> troisieme categorie de la tache A cesse d'etre en concurrence avec la bonne reponse (une categorie
> fausse pour la situation, du type *une regle de la classe*, n'est ni vraie ni absurde pour une
> obligation nationale), soit l'item quitte la tache A pour la tache B, ou la question porte sur
> **qui** doit respecter l'obligation et non sur ce qu'elle est. **Ce que je n'accepterai pas, c'est
> le retour de l'enumeration dans la question** : elle reenumere des propositions deja portees par
> les boutons et elle repasse `LON-1`. Les deux contraintes sont conjointement satisfaisables ; c'est
> la seule chose que j'affirme sur la forme.
>
> **2. Chaque proposition de la tache B est un candidat qu'un enfant de CP peut envisager**
> (`PLA-1`). Un seul item est en cause, l'item 11, et un seul de ses distracteurs.

**Ce que la story ne doit pas toucher, et c'est aussi opposable que le reste** : les cinq faits de
droit, la forme des propositions de la tache B acquise par `FAB-1c`, les onze longueurs sous
`LON-1`. **Trois choses payees en deux sprints ne se rejouent pas pour reparer un item.**

### Reserves

**Aucune.** Un paquet refuse ne porte pas de reserves : ses defauts sont dans son motif, et ils se
paient sur deux items nommes.

### Mentions de couverture partielle

**Sans objet** : la ligne ne ferme pas. **`EMC-CP-07` reste `non couvert`.** Le compte de la matrice
appartient a `camille-po` ; **rien ne bouge sur cette ligne.**

### Mentions de non-couverture opposables

**Sans objet** : `MNC-1` n'est due que sur un Done `VALIDE` ou `VALIDE SOUS RESERVE`.

### Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **Qu'aucune proposition vraie ne soit marquee fausse** (`EMC-5`, le point 1 de ce refus) | **aucun, et c'est le fait le plus utile de ce passage.** Les quatre jobs de la CI sont verts sur cet arbre, `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` et les deux garde-fous d'`ADR-0086` sont verts sur ce paquet, `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` l'est aussi - **et l'item 4 dit quelque chose de faux a l'enfant.** Aucun test ne peut savoir qu'une proposition est vraie ; **c'est moi, en jouant** |
| **`PLA-1`** - qu'une proposition soit un candidat envisageable | **aucun**, et je viens de manquer moi-meme, un jour sur deux, la survivance de la chaine que j'avais condamnee. **Un garde-fou qui epinglerait la reapparition d'une proposition retiree pour ce motif est concevable ; je dis la propriete, jamais la forme du test** |
| **Que la discrimination notee par un item soit dans le libelle de la ligne** | **aucun**, et c'est la meme famille que `CON-1` : la matrice ne voit pas ce qu'un item note reellement |
| **`FAB-1c`** | **aucun** (le marqueur en tete de proposition reste hors de portee de la clause 2 d'`ADR-0086`, mesure au reaudit precedent) - **mais elle est tenue aujourd'hui, comptee ci-dessus** |
| **L'exactitude de chaque fait de droit** | **aucun.** Reverifies un par un contre leur source |
| **`LON-1`, `LON-2`** | **mordues** depuis `ADR-0089` (`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`), a egalite, dette zero sur ce paquet. **Premiere de mes barres de contenu qui cesse d'etre tenue par moi en comptant** |

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`EMC-CP-07` reste `non couvert`** | `camille-po` | Troisieme passage, la ligne ne ferme pas. **Le motif du 2026-09-08 est paye et sort du perimetre** ; le nouveau porte sur **deux items**, l'item 4 de la tache A et un distracteur de l'item 11 |
| **Les deux proprietes ci-dessus, avec ce qui ne doit pas etre retouche** | `camille-po`, via `sofia-sm` | En faire une story. **C'est le plus petit perimetre des trois passages** : un item a re-poser, un distracteur a remplacer, et trois acquis a ne pas casser |
| **La contradiction entre mon bloc de reaudit et la section 16.5 de mon arbitrage prealable, tous deux du 2026-09-08** | moi-meme | **Ecrite ici, pas effacee la-bas.** Quand une de mes barres chiffrees mord un paquet dont je viens d'ecrire *"a conserver telle quelle"*, c'est a moi de dire lequel des deux textes gouverne, dans le geste ou je pose la barre |
| **Qu'un item note une discrimination absente du libelle de sa ligne** | `camille-po` | **Fait general, pas propre a ce paquet** : c'est la porte d'entree de `CON-1`. Je ne cree aucune clause en cours de sprint ; je la porterai dans mon prochain arbitrage prealable si elle se reproduit |
