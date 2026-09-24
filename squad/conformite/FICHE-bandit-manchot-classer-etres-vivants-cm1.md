# Fiche de conformite - bandit-manchot-classer-etres-vivants-cm1

- **Mecanique** : bandit manchot.
- **Contenu** :
  `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-classer-etres-vivants.yaml`
  (11 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-classer-etres-vivants-cm1`, titre servi *"Classer les
  etres vivants"*, consigne servie *"Lis bien, puis dis lequel des trois ne partage pas le meme
  caractere."*, `nombreDeManches: 10`.
- **Ligne declaree** : `SCI-CM1-06`.
- **Statut global** : **DONE PEDAGOGIQUE PRONONCE - VALIDE** le 2026-09-24 par `nadia-prof`
  (instance B), au second passage, apres correctif et nouvelles parties jouees. **Le refus du meme
  jour, premier passage, reste ecrit dans cette fiche et n'est pas efface** : les deux verdicts se
  lisent dans l'ordre ou ils ont ete prononces, bloc de validation puis section "Second passage".
  Fiche ouverte par `camille-po` a l'ouverture du bloc de construction du sprint s41 (paquet et
  catalogue livres et fusionnes dans `round-two`, commit `b5b07966`).
- **Story** : `S41-SCI-CLASSER` - Classer des etres vivants d'apres un caractere commun observable
  (`docs/backlog/backlog.md`).
- **Competence programme visee** : sciences et technologie, CM1 (cycle 3), domaine "Le vivant, sa
  diversite et les fonctions qui le caracterisent" -
  `squad/programme/sciences-et-technologie/competences-cm1.md`, `SCI-CM1-06`. Ligne `non couvert`
  dans `squad/programme/MATRICE-couverture.md` au moment d'ouverture de cette fiche, deja notee
  "jouable au choix textuel, item par item. Mention exigee d'avance : 'raisonnement sur un
  caractere, item par item ; le tri d'une collection entiere reste non couvert' (refuse au palier
  1 par `V3`)."
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage neuf
  a router** (`ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-
  borne.md`, section 4.3) : classee dans le rang "les suivantes", deuxieme rang de surete sur
  quatre parmi les lignes de sciences CM1 alors restantes - *"6 (classement, mention partielle
  due)"*. Le referentiel source precise, cite mot pour mot : *"'Lequel des trois n'a pas de
  plumes ?' travaille bien le caractere."*
- **`CYC-1` s'applique et se satisfait par construction** (`DEC-0095`, section 9.3 et 9.4) : cette
  ligne de cycle 3 porte sur le meme objet que `QLM-CP-01` (distinguer les etres vivants) et
  `QLM-CP-02` (caracteriser un etre vivant), deja `couvert-valide` et audites par la meme instance
  de `nadia-prof` (instance B). Aucun item de ce paquet ne doit contredire ce que ces deux paquets
  enseignent deja sur le meme objet.
- **Lien decision** : `DEC-0095`.
- **Derniere mise a jour** : 2026-09-24 (second passage, Done pedagogique VALIDE et refus leve,
  `nadia-prof` instance B ; refus du premier passage et ouverture le meme jour).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S41-SCI-CLASSER`)

- Etant donne un item, quand il s'affiche, alors il demande de reconnaitre lequel de trois etres
  vivants decrits ne partage pas un caractere observable commun aux deux autres (par exemple
  presence de plumes, nombre de pattes, milieu de vie), jamais de trier ou de deplacer une
  collection entiere (`V3`).
- Etant donne le paquet livre, quand on le relit dans son entier, alors plusieurs caracteres
  observables distincts sont mobilises d'un item a l'autre, pas un seul caractere repete.
- Etant donne un item, quand il porte sur la diversite des especes d'un milieu, alors il decrit ce
  milieu et les especes qui l'occupent sans jamais contredire ce que `QLM-CP-01` et `QLM-CP-02`
  enseignent deja sur la distinction du vivant (`CYC-1`).
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres etres vivants reels partageant un
  caractere different, jamais des elements sans rapport avec le vivant.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `SCI-CM1-06` et uniquement elle, avec la mention de couverture partielle deja fixee
  ("raisonnement sur un caractere, item par item ; le tri d'une collection entiere reste non
  couvert") portee par la matrice au Done pedagogique.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` (instance B) le joue, alors
  elle confirme qu'aucun item ne demande de trier une collection et qu'aucun item ne contredit
  `QLM-CP-01` ou `QLM-CP-02`.

## Ce que cette story ne fait pas

Elle ne construit aucune interaction de tri ou de deplacement d'une collection - hors de portee du
palier 1 (`V3`) ; elle ne fixe aucun contenu factuel precis (quels etres vivants, quels caracteres
retenir) - reste a l'auteur du paquet puis a l'audit de `nadia-prof`.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **REFUSE**
- **Date** : 2026-09-24
- **Auditrice** : `nadia-prof`, instance B (`DEC-0095`, section 9).
- **Audit joue** : **oui**

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `3f6b379f`, application construite depuis les modules de cet
arbre et lancee depuis leurs classes compilees sur le JDK de la toolchain (21.0.10 rapporte par la
machine virtuelle), sur un port dedie - le fat jar etait tenu ouvert par un autre processus.
**Deux parties completes de dix manches par appels HTTP reels**, la premiere avec trois erreurs
volontaires sur les manches 1 et 4, ecran de fin atteint - *"Tu as trouve, tout seul : 8 / Tu as
joue toutes les questions : 10"*. **Et j'ai lu les onze items du paquet**, plus
`cp-qlm-vivant-non-vivant.yaml`, `cp-qlm-cycle-de-vie.yaml` et
`cm1-sciences-etapes-developpement.yaml`.

**Ce n'est pas un audit degrade, et les deux motifs du refus sont des faits de partie jouee : les
items en cause m'ont ete servis, dans la meme partie, aux manches 1 et 9.**

**Ce que l'audit ne couvre pas** : j'ai lu le document servi, pas la page peinte par un navigateur.
Le refus n'en depend pas.

### Ce qui tient, et qu'il faut garder

Je commence par la, parce qu'un refus n'est utile que si l'equipe sait ce qu'elle garde. La forme
du lot est bonne et **huit items sur onze sont a garder tels quels**.

- **`V3` est respectee sans discussion.** Aucun item ne demande de trier ni de deplacer une
  collection : chaque manche pose trois etres vivants et demande lequel ne partage pas le caractere
  des deux autres. C'est exactement la forme que le referentiel citait - *"Lequel des trois n'a pas
  de plumes ?"* - et elle travaille bien le caractere.
- **Plusieurs caracteres observables distincts sont mobilises** : plumes (item 1), nombre de pattes
  (2, 7, 10), ecailles (4), poils (5), vol (6), milieu de vie (3, 8, 9, 11). Aucun caractere unique
  repete sur tout le paquet.
- **Les huit items qui ne portent pas sur un milieu sont exacts** : la chauve-souris est un
  mammifere volant sans plumes ; l'araignee porte huit pattes contre six a la fourmi et a l'abeille ;
  le lezard en porte quatre contre six a la mouche et a la coccinelle ; le chat porte des poils la
  ou le serpent et le lezard portent des ecailles ; le canard porte des plumes la ou le renard et
  l'ecureuil portent des poils ; le chien marche sur quatre pattes la ou le pigeon et la poule en
  ont deux. **Verifie un par un, aucune erreur.**
- **Les distracteurs sont toujours d'autres etres vivants reels**, jamais un objet, jamais un
  element hors du vivant. La bonne reponse figure exactement une fois.
- **`CYC-1`, verifiee et non prise sur parole.** J'ai relu `cp-qlm-vivant-non-vivant.yaml`
  (`QLM-CP-01`) et `cp-qlm-cycle-de-vie.yaml` (`QLM-CP-02`) : aucun des etres vivants nommes ici
  n'y est classe autrement, et aucun item d'ici ne contredit ce que le CP enseigne sur la
  distinction du vivant. **La declaration de l'en-tete du paquet est exacte sur ce point.**
- **Traitement de l'erreur** : l'indice s'affiche des la premiere erreur et apporte le caractere
  manquant ; la revelation debloque a la troisieme sans dispenser du geste.
- **Niveau et ton** : une phrase par item, vocabulaire de CM1, aucun biais, aucun animal traite sur
  un registre affectif.

### Premier motif du refus - trois enonces affirment, comme un fait, ce que leur propre question demande de nier

Les trois items qui portent sur un milieu sont ecrits sur le meme moule :

> *"**Dans une mare vivent** la grenouille, le tetard **et l'ecureuil** : lequel de ces trois etres
> vivants ne vit pas dans la mare ?"* (item 8)
>
> *"**Dans une foret vivent** le renard, l'ecureuil **et le poisson rouge** : lequel de ces trois
> etres vivants ne vit pas dans la foret ?"* (item 9)
>
> *"**Dans la savane vivent** le lion, la gazelle **et le poisson-clown** : lequel de ces trois
> etres vivants ne vit pas dans la savane ?"* (item 11)

**Chacune de ces phrases affirme d'abord ce que sa question demande ensuite de nier.** Le premier
membre de phrase n'est pas un decor : c'est une proposition declarative, et elle enseigne que
l'ecureuil vit dans la mare, que le poisson rouge vit dans une foret et que le poisson-clown vit
dans la savane. **Ces trois affirmations sont fausses.** L'enfant qui lit ce qu'on lui donne a lire
- et c'est ce qu'on lui demande de faire, la consigne dit *"Lis bien"* - se trouve devant un enonce
qui se contredit en une ligne. Celui qui repond juste est celui qui a appris a ne pas croire le
debut de la phrase.

**Ce n'est pas une maladresse de style, c'est le contenu lui-meme** : en sciences, la premisse d'un
raisonnement de classement est ce qu'on enseigne au meme titre que sa conclusion.

**Et cela porte precisement la moitie du libelle que le lot revendiquait.** Le libelle de
`SCI-CM1-06` a deux membres : *classer d'apres un caractere commun observable*, et *reperer la
diversite des especes d'un milieu*. Le second membre n'est servi que par ces trois items-la. **Les
huit items sains ne couvrent que la premiere moitie.**

**Correctif actionnable** : retirer la proposition declarative et poser les trois candidats dans la
question. Par exemple, mot pour mot et pret a coller :

> *"Lequel de ces trois etres vivants ne vit pas dans une mare : la grenouille, la libellule,
> l'ecureuil ?"*

La forme est deja employee, sans defaut, par les items 3 et 6 du meme paquet (*"La truite, le
brochet et le lapin : lequel de ces trois etres vivants ne vit pas dans l'eau ?"*). **Il s'agit
d'aligner trois items sur huit autres du meme paquet, pas de reecrire le lot.**

**Une part de ce defaut vient de la story, pas de la developpeuse**, et je l'ecris pour que la
correction se fasse aux deux endroits : le critere d'acceptation dit *"alors il decrit ce milieu et
les especes qui l'occupent"*, ce qui invite litteralement a annoncer l'intrus comme un occupant du
milieu. **Le critere est a reecrire en meme temps que les trois items**, sinon le defaut reviendra
au prochain paquet de milieu.

### Second motif du refus - l'item 8 fait du tetard et de la grenouille deux etres vivants differents, contre ce qu'un autre jeu de CM1 enseigne deja

L'item 8 pose *"la grenouille, le tetard et l'ecureuil"* et les appelle *"ces trois etres
vivants"*. **Le tetard n'est pas un autre etre vivant que la grenouille : c'est la grenouille
elle-meme, avant sa metamorphose.**

**Et ce produit l'enseigne deja, au meme niveau, dans un jeu servi et valide.**
`cm1-sciences-etapes-developpement.yaml` (`SCI-CM1-08`,
`FICHE-bandit-manchot-etapes-developpement-cm1.md`, Done pedagogique prononce) porte cet item :

> *"Un tetard grandit dans la mare et se transforme peu a peu en grenouille adulte, plus grosse et
> plus forte. Quelle etape suivra, une fois adulte ?"*

Un enfant de CM1 qui joue les deux jeux apprend dans l'un que le tetard devient la grenouille -
**un animal, deux etapes** - et dans l'autre qu'on peut les compter comme deux etres vivants d'une
mare. **C'est une contradiction interne au produit, sur le meme niveau et sur le meme objet.** Ce
n'est pas `CYC-1`, qui ne regarde que le CP contre le cycle 3 : c'est la coherence du CM1 avec
lui-meme, qu'aucune clause ecrite ne couvrait encore et qu'aucun automate ne peut voir.

**Correctif actionnable** : remplacer *"le tetard"* par un autre etre vivant de la mare qui ne soit
pas un stade d'un des deux autres - la libellule, le triton ou l'escargot d'eau conviennent, et la
libellule est deja nommee ailleurs dans le paquet.

### Ce qui n'est pas un motif de refus, et que je ne compte pas contre le lot

Pour que personne ne corrige ce que je n'ai pas demande :

- **La facilite des items par rapport au CM1.** Elle est reelle sur quelques items, mais **la barre
  est celle que j'ai moi-meme ecrite** - le referentiel cite *"Lequel des trois n'a pas de plumes ?
  travaille bien le caractere"*. **Je ne deplace pas mon poteau apres coup.**
- **La mention de couverture partielle** (*"raisonnement sur un caractere, item par item ; le tri
  d'une collection entiere reste non couvert"*). Elle reste due le jour ou cette ligne passera, et
  elle est tenue par le lot : aucun item ne demande de trier une collection.

### Reserve datee du 2026-09-24, a traiter avec le correctif et non apres

**Item 6, *"La libellule, le papillon et la fourmi : lequel de ces trois etres vivants ne vole
pas ?"*.** La reponse attendue est la fourmi, et l'indice dit *"Cet etre vivant marche et grimpe,
mais il ne vole pas"*. **C'est vrai de l'ouvriere, que l'enfant observe, et faux de l'espece** : les
fourmis reproductrices sont ailees et volent, et le vol nuptial d'un soir d'ete est un phenomene
que beaucoup d'enfants ont vu. L'item reste jouable et sa reponse reste la bonne ; **mais l'indice
enonce comme une propriete de la fourmi ce qui n'est vrai que de l'ouvriere.** A regler en visant
le caractere observable plutot que l'espece : *"Cette ouvriere marche et grimpe ; elle n'a pas
d'ailes."*

### Ce dont je reste la seule garde sur ce paquet

`BIN-1` est desormais mordue par un test et prouvee par mutation (`theo-archi`,
`squad/sprints/s41-fusion-emc-sciences-qlm/confirmation-archunit.md`) : je ne la recompte pas et je
la considere couverte. **Ce qui ne l'est par rien, et que ce refus vient de demontrer :**

- **Qu'un enonce ne se contredise pas lui-meme** - aucun automate ne lit une premisse.
- **Qu'un paquet ne contredise pas un autre paquet du meme niveau sur le meme objet du vivant** -
  aucun test ne compare deux paquets de deux lignes differentes.
- **L'exactitude zoologique de chaque caractere** - aucun garde-fou ne verifie qu'une araignee a
  huit pattes.

**Ces trois proprietes sont tenues par moi seule, en lisant. Tant qu'aucun test ne les mord,
l'equipe ne doit pas les croire couvertes par une CI verte** - et c'est bien ce qui s'est passe
ici : quatre jobs verts, 1701 tests, et trois enonces qui se dementent en une ligne.

### Consequence pour la matrice

**`camille-po` ne passe pas la ligne `SCI-CM1-06` a `couvert-valide`.** Elle reste `non couvert`.
Ce refus ne bloque pas le sprint suivant : il produit du backlog, et le correctif tient en trois
enonces reecrits, une substitution d'espece, un indice reformule et un critere d'acceptation
corrige. **Le lot revient devant moi avec ces cinq points, pas avec un paquet neuf.**

---

## Second passage - nadia-prof UNIQUEMENT, 2026-09-24

> **Le refus ci-dessus n'est pas efface et ne le sera jamais** : un refus efface n'a aucune valeur
> de preuve. Il est leve par le verdict qui suit, et l'histoire reste lisible dans l'ordre ou elle
> s'est produite.

- **Verdict** : **VALIDE** - **le refus du 2026-09-24 (premier passage) est leve.**
- **Date** : 2026-09-24, apres le correctif de `lea-dev` (`bddd8233`, fusionne par `bcbe3b5f`) et la
  correction du critere d'acceptation par `camille-po` (`2fe6846f`).
- **Auditrice** : `nadia-prof`, instance B.
- **Audit joue** : **oui, de nouveau.**

### Conditions du second audit

Arbre `round-two` a `bcbe3b5f`, modules reconstruits depuis cet arbre, application relancee depuis
leurs classes compilees sur le JDK de la toolchain (21.0.10 rapporte par la machine virtuelle), sur
un port dedie. **Trois parties completes de dix manches par appels HTTP reels**, avec erreurs
volontaires - dont une partie ou je me suis trompee a chaque manche, pour lire tous les indices
servis. **Les trois items que j'avais refuses m'ont ete servis en jeu**, ainsi que l'item de la
fourmi, et c'est a l'ecran que je les juge, pas dans le fichier.

### Les deux motifs du refus, repris un par un

**Premier motif - les trois enonces qui s'affirmaient puis se niaient : leve.** Les trois items de
milieu sont desormais servis sous cette forme, relevee a l'ecran :

> *"La grenouille, le triton et l'ecureuil : lequel de ces trois etres vivants ne vit pas dans la
> mare ?"*
>
> *"Le renard, l'ecureuil et le poisson rouge : lequel de ces trois etres vivants ne vit pas dans la
> foret ?"*
>
> *"Le lion, la gazelle et le poisson-clown : lequel de ces trois etres vivants ne vit pas dans la
> savane ?"*

**Plus aucune proposition declarative.** Aucun des trois n'affirme plus que l'intrus occupe le
milieu, et les trois sont maintenant sur le patron exact des items 3 et 6, qui etaient sains des le
premier passage. **C'est le correctif que j'avais ecrit, applique tel quel.** La moitie *"reperer la
diversite des especes d'un milieu"* du libelle est donc servie par trois items sains, la ou elle
l'etait par trois items defectueux.

**Second motif - le tetard compte comme un etre vivant distinct de la grenouille : leve.** Le tetard
est remplace par le triton. **Le triton est un autre animal, pas un stade d'un des deux autres** :
la contradiction avec `cm1-sciences-etapes-developpement.yaml` (`SCI-CM1-08`) n'existe plus.
J'ajoute que le choix est bon sur le fond, et pas seulement sur le defaut que je visais : le triton
est un habitant reel d'une mare, enseigne a ce niveau, et il partage avec la grenouille un
caractere que l'ecureuil n'a pas, ce qui est exactement ce que l'item demande de reperer. **Le
paquet reste coherent avec lui-meme** : l'ecureuil est l'intrus de la mare a l'item 8 et un
habitant legitime de la foret a l'item 9, sans se contredire d'un item a l'autre - verifie en jeu,
les deux items m'ont ete servis dans la meme partie.

**La reserve jointe - l'indice de la fourmi : levee.** L'indice servi est desormais *"La plupart des
fourmis n'ont pas d'ailes et se deplacent en marchant ou en grimpant."*, releve a l'ecran apres une
erreur volontaire sur cet item. **Il ne tranche plus sur l'espece entiere** et il vise le caractere
observable, ce que je demandais. La bonne reponse reste la bonne parmi la libellule et le papillon.

### Le critere d'acceptation corrige - je l'ai relu, il correspond

`camille-po` a reecrit le critere (`2fe6846f`). Il exige desormais que *"les trois etres vivants
soient poses dans la question elle-meme, sur le patron deja employe sans defaut par les items 3 et
6 du meme paquet"* et il interdit nommement *"une phrase declarative qui affirme comme un fait ce
que la question demande ensuite de nier"*, avec le contre-exemple ecrit en toutes lettres. **C'est
exactement ce que j'attendais, et c'est mieux que ce que j'avais demande** : le contre-exemple est
dans le critere, donc le prochain paquet de milieu ne pourra pas retomber dedans par lecture
distraite. Le defaut ne peut plus naitre de la story.

### Ce qui tenait au premier passage et qui tient toujours

Reverifie, pas suppose : `V3` (aucun tri d'une collection), plusieurs caracteres observables
distincts, distracteurs toujours d'autres etres vivants reels, bonne reponse une seule fois,
`CYC-1` contre `QLM-CP-01` et `QLM-CP-02`, traitement de l'erreur (indice des la premiere erreur,
revelation a la troisieme sans dispenser du geste), niveau et ton. **Les huit items que j'avais
demande de garder sont inchanges** - je l'ai verifie en les relisant, et non sur la foi du message
de commit.

### Ce que je n'ai pas corrige et qui reste a la charge d'un autre

**Le commentaire d'en-tete du paquet n'a pas suivi son propre contenu.** La section `HIS-3` affirme
encore *"les fourmis marchent et ne volent pas, a la difference des libellules et des papillons"* -
c'est la formulation que l'indice vient precisement d'abandonner. **Ce n'est pas un motif de
reserve** : ce texte n'est pas lu par l'enfant, et il ne change rien a ce qui est joue. Mais c'est
le texte qu'un futur auteur lira pour savoir ce que le paquet tient, et il dit aujourd'hui autre
chose que le paquet. A aligner par qui edite ce fichier ; ce n'est pas la porte pedagogique.

### Ce dont je reste la seule garde, apres correctif

**Aucun test n'a ete ajoute par ce correctif** (`bddd8233` ne touche que deux fichiers de contenu).
Les trois proprietes nommees au premier passage restent tenues par moi seule, et le passage du
refus a la validation ne les couvre pas :

- **Qu'un enonce ne se contredise pas lui-meme.** Ce lot l'a corrige item par item ; rien
  n'empechera le prochain paquet de recommencer, sauf le critere de la story et une lecture.
- **Qu'un paquet ne contredise pas un autre paquet du meme niveau sur le meme objet du vivant.**
- **L'exactitude zoologique de chaque caractere.**

### Consequence pour la matrice

**`camille-po` peut passer la ligne `SCI-CM1-06` a `couvert-valide`**, avec la mention de couverture
partielle exigee d'avance, reprise mot pour mot :

> *"raisonnement sur un caractere, item par item ; le tri d'une collection entiere reste non
> couvert"*
