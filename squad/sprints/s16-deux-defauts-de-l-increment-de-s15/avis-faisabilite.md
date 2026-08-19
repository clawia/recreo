# Avis de faisabilite - theo-archi, sprint 16

- **Auteur** : theo-archi. **Date** : 2026-08-19.
- **Branche** : `s16-theo`, worktree dedie, forkee de `round-two` a `4c7cd45` ; lignee verifiee par
  `outils/verifier-lignee.sh`.
- **Perimetre instruit** : `DEC-0037` (statut `Proposee`) et les six stories de la section sprint 16 de
  `docs/backlog/backlog.md`, lues dans le worktree de `camille-po` (`s16-camille`, tete `f73ad41`).
- **Etape 1 du planning** : cet avis precede tout commit de code, ce qui n'a pas eu lieu a s15. Il ne
  remplace aucune porte pedagogique.
- **Ce que je ne fais pas** : rediscuter `DEC-0036`, `DEC-0037` ni aucun arbitrage de `nadia-prof` ou de
  l'utilisatrice ; choisir une forme visuelle ; repriorise quoi que ce soit.
- **Aucun serveur lance, aucun chiffre de rendu revendique** : je n'ai pas de navigateur, et l'instance
  du port 8080 est celle de l'utilisatrice - je ne l'ai pas touchee. Les six tests d'integration
  prennent un port libre (`new ServerSocket(0)`, `WebEnvironment.RANDOM_PORT`), verifie **avant** de
  lancer `mvn verify` precisement pour cette raison.

## 0. Verdict d'ensemble

**Les six stories sont faisables en l'etat du decoupage.** Deux appellent un `ADR`, une appelle
l'amendement d'un `ADR` existant, trois n'appellent rien de neuf. **Sept criteres sur vingt-et-un sont a
reecrire**, dont deux qui demandent a un automate de faire passer le temps et un qui epingle un
effectif de catalogue.

| Story | Verdict | Decision |
|---|---|---|
| S16-1 | faisable moyennant un `ADR` | **`ADR-0047`** (`Propose`), regle due par le lot |
| S16-2 | faisable moyennant un `ADR` | **`ADR-0048`** (`Propose`), regle due par le lot |
| S16-3 | faisable, **debloquee** | amendement d'`ADR-0046`, correction due par le lot |
| S16-4 | faisable, **meme lot que S16-3** | idem - une seule cause, un seul garde-fou |
| S16-5 | faisable en l'etat | aucun `ADR` : application d'`ADR-0015` et d'`ADR-0039`, deja acceptes |
| S16-6 | faisable en l'etat | aucun `ADR` : une condition existante etendue a une route qui la manque |

**Pourquoi deux `ADR` au statut `Propose` et non `Accepte`.** Les deux decisions portent une regle dont
l'objet n'existe pas encore dans la production : ecrite aujourd'hui, chacune serait **rouge a
l'ouverture du sprint**, et une regle rouge est une regle qu'on desactive. Le statut `Propose` porte
cette attente au lieu de la cacher, et l'acceptation est datee du commit du lot. **C'est la difference
exacte avec `ADR-0042`**, accepte en s13 avec une regle confiee a un lot qui a ete annule et qui n'existe
dans aucun fichier Java deux sprints plus tard.

## 1. S16-1 - le message de partie interrompue

**Faisable, et le mecanisme que `camille-po` demande existe sans rien ajouter au depot.**

**Le defaut, lu et non suppose** : `PartieController.marquerPartieEnCours` pose le marqueur **sans
appeler `setMaxAge`**. Un cookie sans age maximal est un cookie de session **de navigateur** : il vit
tant que l'onglet vit et ne sait rien de la session HTTP. Et il n'y a **aucune duree a lire dans une
configuration** : `bootstrap/src/main/resources` ne contient que `META-INF`, aucun fichier de
proprietes - la duree d'inactivite est celle du conteneur par defaut, qu'aucun fichier du depot ne
nomme.

**La lecture demandee est possible et sa place est deja decidee par une regle existante.** La valeur est
`HttpSession.getMaxInactiveInterval()`, donc la duree reellement appliquee, quelle qu'elle soit. Et
`AccesALEtatDeSessionTest.unSeulTypeToucheLaSessionHttp` interdit a toute classe autre que
`EtatDePartieEnSession` de dependre de `HttpSession`, y compris via `getSession()` : **la lecture vit
donc dans `EtatDePartieEnSession`, et `ADR-0019` n'a pas a etre amende pour cela** - il repond a la
question. Le cookie reste construit par le controleur, ou il est a sa place : un detail d'adaptateur.
`application` et `domain` n'apprennent rien, et `DEC-0028` est tenue a la lettre - la seule chose que
ce changement fait est de faire **mourir** quelque chose plus tot.

**Detail dans `ADR-0047`**, avec sa regle enoncee au mot et son unique alternative dangereuse ecartee
(recopier un nombre : deux sources de verite dont la divergence est muette).

### Ses criteres, un par un

| Critere | Verdict |
|---|---|
| 1 - la duree du marqueur n'excede jamais la duree d'inactivite de la session | **encodable tel quel**, et c'est la regle d'`ADR-0047`. Une precision de forme : elle doit etre **lue sur la session de la requete jugee**, jamais ecrite dans le test, sinon le garde-fou devient faux le jour ou la duree est configuree |
| 2 - une session disparue par la seule expiration : catalogue sans message | **refuse en l'etat** : il demande a un automate de faire passer la duree d'inactivite. **Reecrit** : *une requete qui ne porte pas le marqueur obtient le catalogue complet sans le message* - c'est la consequence observable de la meme propriete, et elle est encodable. Le scenario litteral reste a `nadia-prof`, et la story le lui demande deja |
| 3 - session detruite par un redemarrage, marqueur encore valide : le message s'affiche | **encodable tel quel** : une requete portant le marqueur sur une session neuve est exactement cet etat, et c'est ce que MockMvc construit sans effort |
| 4 - rechargement pendant que la session vit : la manche, aucun message | **encodable tel quel**, et deja tenu par `S1-4` - a garder comme non-regression |

**Ce que je refuse de laisser croire** : les criteres 2 et 3 ne se distinguent pas par la *cause* de la
disparition - un automate ne sait pas si un serveur a redemarre. Ils se distinguent par un **fait
observable** : le marqueur est-il encore la ? La reecriture du critere 2 dit cela, et c'est tout ce qui
est verifiable. Que la cause soit la bonne est le constat de `nadia-prof`.

## 2. S16-2 - l'adresse affichee

**Faisable, et la double responsabilite est preservee - a condition de ne pas la chercher dans le
declencheur.**

**La contrainte dure** : `hx-post="/retour"` ouvre l'ecran de confirmation quand une partie est en cours
(`DEC-0026`, `ADR-0038` point 5, `ADR-0042`). Un lien nu vers `/` la perdrait : une navigation complete
pendant une partie rend la manche par reprise, jamais la confirmation. **Le declencheur ne peut donc pas
cesser d'etre un echange htmx**, et l'adresse doit pourtant changer.

**Et l'asymetrie est de mon fait, il faut le dire avant de proposer quoi que ce soit** : c'est mon
arbitrage du 2026-08-18 qui a fait des puces des navigations completes pour qu'elles puissent porter un
etat actif, en laissant "Les jeux" en echange. `S16-2` est la facture de ce choix, et elle est plus
petite que le choix inverse.

**La forme retenue : une en-tete de reponse, decidee par le serveur.** La reponse de la route de retour
declare l'adresse du catalogue complet **si et seulement si** elle rend le catalogue complet, et ne
declare rien quand elle rend la confirmation. La branche qui choisit le fragment est deja ecrite : elle
choisit aussi l'en-tete, donc **il n'y a pas de second predicat a tenir en accord avec le premier**.

**Ce qu'elle ne casse pas, verifie et non espere** : le corps de la reponse est inchange, donc
l'assemblage d'`ADR-0020` est inchange, donc aucune clause qui lit un document assemble ne voit la
difference - ni les quatre d'`ADR-0038`, ni l'appariement d'`ADR-0017`, ni l'unicite de la sortie
visible. La requete rejouee et son effet sur la session sont les memes : l'arret reste non destructeur.

**`ADR-0044` n'est pas rouvert, et ce serait la troisieme fois si je me trompais** : son point 2 (le
point d'entree est une route) et son point 4 (le niveau ne vit que dans l'URL) sont ce sur quoi cette
decision s'appuie ; son point 3, deja amende le 2026-08-18, parle du mode de rendu de l'en-tete de page,
que rien ici ne touche. **La population de masquage ne bouge pas** : aucun element ne gagne ni ne perd
d'ecran. **Detail dans `ADR-0048`**, avec les quatre alternatives ecartees, dont la redirection cote
serveur - la seule voisine qui casserait vraiment quelque chose, et qu'il faut nommer pour qu'elle ne
soit pas essayee.

### Ses criteres

| Critere | Verdict |
|---|---|
| 1 - l'adresse devient celle du catalogue complet | **encodable, sous sa forme serveur** : le garde-fou lit ce que le serveur declare, pas la barre d'adresse. **A reecrire en ce sens**, sinon il promet une propriete de navigateur qu'aucun automate du depot ne mesure |
| 2 - recharger cette adresse rend le catalogue complet | **encodable tel quel** : c'est une requete sur cette adresse, deja exercee par le corpus |
| 3 - partie en cours : la confirmation s'affiche exactement comme avant | **encodable tel quel**, et deja tenu ; a garder comme non-regression, plus **le sens negatif** de la regle d'`ADR-0048` - aucune adresse declaree sur cet ecran |
| 4 - apres confirmation : catalogue complet, partie abandonnee | **encodable tel quel**, deja tenu |

## 3. S16-3 et S16-4 - un seul lot, et je suis debloque

**Le blocage que j'avais pose est leve, et par le geste que j'avais demande.** J'avais refuse de
corriger ma derivation sans arbitrage, parce que le rouge previsible sur
`.proposition.deja-essayee` demandait une decision qui n'est pas la mienne. `nadia-prof` a tranche la
forme neutre le 2026-08-18, et son motif merite d'etre repris : la variante recommandee aurait detruit
la premisse **globale** sur laquelle elle avait accorde l'aplat actif quelques heures plus tot. Elle a
donc protege la coherence de l'ensemble, pas ma position.

**Ce que devient ma derivation** : un selecteur maison compose est un **sujet distinct**, pas un alias de
sa premiere classe. La cause du trou, lue dans le code : `elementDuCompose` coupe au premier
deux-points, donc `.chip-niveau.est-actif:hover` rend `.chip-niveau.est-actif` **entier**, une chaine que
`selecteursApplicables` ne produit jamais - la regle **sort du perimetre dans les deux sens**, ses etats
ne sont ni exiges ni comptes. Ce n'est pas un alias mal calcule, c'est une population incomplete. La
population se derive de la meme source que la clause E de s15 : les combinaisons de classes qu'un meme
element peut porter, litteraux du gabarit compris.

**Ce que la correction reclamera, mesure.** Methode : enumeration des selecteurs maison composes de deux
classes ou plus, commentaires retires, sur la feuille servie de `4c7cd45` - **six familles**.
`.signal.est-juste` et `.signal.est-faux` ne sont pas des pressables et restent hors population par
construction. `.chip-niveau.est-actif` declare deja ses deux etats : elle passera au vert **en etant
enfin verifiee**. Il reste **exactement un** cas qui rougira : `.proposition.deja-essayee`. Rien
d'autre - c'est la reponse chiffree a la question posee.

**Un seul lot, et ce n'est pas un choix d'organisation.** La correction de la derivation est
**indivisible** : le jour ou elle entre, les deux rouges apparaissent ensemble. Livrer `S16-3` seule
obligerait soit a laisser `.proposition.deja-essayee` rouge entre deux lots, soit a corriger la
derivation deux fois. **Une seule cause, un seul garde-fou, un seul lot** ; ce sont leurs regles CSS de
production qui sont distinctes.

### Leurs criteres

Les quatre criteres testables (deux par story) sont **encodables tels qu'ils sont ecrits**, et c'est le
seul endroit de cet avis ou je n'ai rien a reecrire. Motif : ils sont formules en **declarations
comparees** - les couleurs de texte, de fond et de bordure restent identiques a l'etat de repos - ce qui
est exactement ce que la derivation d'`ADR-0046` lit. Deux precisions de forme, gratuites :

- le critere de `S16-3` nomme `:focus-visible` en plus de `:hover` et `:active`. Les etats gardes par
  `ADR-0046` sont aujourd'hui ces deux derniers ; ajouter le troisieme est une **extension de perimetre
  du garde-fou**, pas un cas de plus a lister, et elle doit passer par la constante qui les enumere -
  sans quoi le critere serait vrai a l'ecrit et non garde ;
- le critere de `S16-4` parle des **quatre positions cycliques de fond**. Le garde-fou lit des
  declarations, donc il verra les quatre regles de position si elles existent ; il ne verra pas qu'un
  fond rendu est identique. La formulation est bonne, sa verification est declarative, et le constat sur
  les quatre positions reste celui de `nadia-prof` - la story le dit deja.

## 4. S16-5 - l'extension du perimetre d'accentuation

**Faisable, et le trou est reel : je l'ai lu.** `TexteAccentueDesGabaritsTest.remplaceAuRendu` **saute
tout sous-arbre place sous un `th:text`/`th:utext`**, et sa javadoc justifie ce saut ainsi : *"Ce
texte-la n'atteint jamais l'enfant : celui qu'il lit vient du modele, donc du contenu, dont les
garde-fous vivent dans adapter-content."*

**La premiere moitie de cette phrase est vraie, la seconde est incomplete, et c'est exactement la ou le
trou vit.** Le texte en clair sous un `th:text` n'atteint effectivement jamais l'enfant. Mais les
garde-fous de contenu couvrent les **paquets** ; les libelles de niveau viennent du **catalogue**, par
`PartieUseCase.niveauxDuCatalogue()`, et rien dans `adapter-content` ne juge l'accentuation d'un
`niveau`. Le perimetre n'est donc pas seulement aveugle : **son motif d'aveuglement est faux sur une
partie de son objet**, ce qui est pire, parce qu'il decourage de regarder.

**Ou le garde-fou doit vivre, et pourquoi c'est une derivation et non une enumeration.** `ADR-0015`
pose que le perimetre descend de **la source qui gouverne le rendu**. La source de ces libelles n'est
pas un gabarit, c'est une donnee servie. Donc :

> **Le perimetre est l'ensemble des chaines que le port entrant expose a une vue, derive des composantes
> des types que ce port rend** - pas une liste de cles YAML, pas une liste de gabarits.

Concretement, les composantes de `GameDefinition` et du descripteur sont enumerables **par reflexion**,
donc la population grandit d'elle-meme le jour ou un de ces types gagne un champ. C'est la propriete que
je veux : **le filtre par matiere passera par le meme chemin, et il y entrera sans que personne y
pense** - ce que `nadia-prof` demande nommement en exigeant que le trou soit ferme avant ce filtre.

**Ce que le garde-fou ne peut structurellement pas voir, dit avant qu'on le lui fasse dire** :

- **il ne sait pas quels mots portent un accent en francais.** Aucun dictionnaire n'entre dans ce depot,
  et un critere par lettre serait faux dans les deux sens. Le garde-fou existant contourne cela par un
  **inventaire de chaines exactes**, allow-list dont `ADR-0039` dit qu'elle est la bonne polarite pour
  une promesse universelle ; l'extension doit employer le meme moyen, avec pour chaque entree son motif
  - et l'exemption de "CP" et "CM1" que `nadia-prof` demande, avec sa **frontiere** ecrite : elle cesse
  de s'appliquer des qu'un niveau porte une lettre accentuable ;
- **il ne voit pas une chaine que le controleur fabrique** par concatenation, comme la phrase du filtre.
  Une moitie de celle-la est deja dans l'inventaire du texte en clair ; l'autre est la valeur du niveau,
  qui entre par le perimetre ci-dessus. Le dire evite de croire la couverture totale ;
- **il ne juge pas si un mot convient a un enfant.** C'est `nadia-prof`, et cette story ne le lui demande
  pas puisque aucun rendu ne change.

### Ses criteres

| Critere | Verdict |
|---|---|
| 1 - un libelle du modele mal accentue fait echouer la construction, en nommant le libelle | **a reecrire** : "qui devrait porter un accent" n'est pas decidable par un automate sans dictionnaire. Forme demandee : *toute chaine exposee par le port a une vue est dans l'inventaire, avec son motif ; une chaine hors inventaire fait echouer la construction en se nommant*. C'est la meme garantie que pour le texte en clair, et c'est celle qui est tenable |
| 2 - "CP" et "CM1" restent verts, avec exemption ecrite, sa raison et sa frontiere | **encodable tel quel**, et c'est le critere le mieux ecrit des vingt-et-un : il demande une exemption **datee, motivee et bornee**, ce qu'`ADR-0039` exige de toute enumeration assumee. Une seule precision : cette exemption doit etre **comptee** par le recensement de `LesGardeFousSontMisALEpreuveTest`, sinon elle grandira en silence |

## 5. S16-6 - la phrase du filtre apres un changement de niveau confirme

**Faisable en l'etat, aucun `ADR`, et c'est la story la moins couteuse des six.** Elle applique a une
route qui la manque une condition que deux autres tiennent deja. Elle a de plus un effet que la story
ne revendique pas et qu'il vaut la peine de nommer : depuis mon arbitrage du 2026-08-18, l'apparence
active de la puce n'est declaree **que** sous `body:has(.filtre-niveau)`. Sur cette route, la phrase
manque, donc la marque manque, donc **la couleur de la puce est deja eteinte aujourd'hui** : ajouter la
phrase repare les deux symptomes d'un seul geste, et le couplage que `nadia-prof` a demande est ce qui
le garantit.

### Ses criteres

| Critere | Verdict |
|---|---|
| 1 - la phrase nomme le niveau sur l'accueil filtre issu de la confirmation | **encodable tel quel** ; le scenario est une requete de plus dans un corpus qui exerce deja cette route |
| 2 - la puce active et la phrase nomment le meme niveau, aucune sans l'autre | **encodable, et a renforcer gratuitement** : la formulation compare deux elements d'un ecran. La forme plus forte est *sur tout ecran assemble, le niveau nomme par la phrase et celui de la puce active sont le meme, et l'un n'apparait jamais sans l'autre* - derivee du corpus, sans nommer de route. Elle ferme `S15-D` **et** les routes futures |

## 6. Les trois dettes routees

### 6.1 Le garde-fou de provenance des classes amont - **entre au perimetre, petit lot**

**Recommandation : oui, et c'est celle des trois qui coute le moins pour ce qu'elle ferme.** L'enonce est
deja ecrit au mot (avis de faisabilite de s14, section 12) : un test verifie que les classes des modules
amont sont chargees depuis l'arbre du depot et non depuis le depot local, la provenance se lisant sur
l'URL du chargeur de classes et la racine se derivant du POM agregateur - donc aucun chemin de poste. Il
mord **dans les deux sens**, faux rouge comme faux vert, parce qu'il ne juge pas un verdict mais la
provenance de ce qui a ete mesure. **Sa preuve de morsure est gratuite** : la commande sans `-am` est le
cas fautif.

**Et j'ecris moi-meme dans `CLAUDE.md` que la phrase que j'y ai mise ne fermera pas ce mode de
defaillance.** Laisser cela un sprint de plus, en ayant redige la phrase et l'aveu, serait le genre de
souhait que ce depot sanctionne. **Ordre de grandeur : un petit lot de developpeur** - une clause par
module dependant d'un amont (trois), ou une clause partagee.

### 6.2 La migration du marqueur d'ecran de jeu vers un attribut - **son motif s'est sharpe, et je la recommande**

**Ma recommandation change, et par un fait mesurable.** En s14 je l'avais differee parce que le
selecteur `body:has()` avait besoin d'une classe. Verifie aujourd'hui sur la feuille servie :
`.ecran-de-jeu` **n'est nomme par aucun selecteur qui s'en serve** - il ne lui reste qu'une regle vide
pour satisfaire `HabillageDesEcransTest`. Mon propre arbitrage du 2026-08-18 a supprime son dernier
consommateur dans la feuille en remplacant la neutralisation par `body:has(.filtre-niveau)`.

**Et le depot porte maintenant trois marqueurs qui, eux, sont nommes par des selecteurs** -
`ecran-exclusif`, `ecran-porte-sa-sortie`, `filtre-niveau`. Ma crainte de s14 - un vocabulaire mixte -
etait fausse dans son sens : **le vocabulaire deviendrait trie par nature**, une classe quand une feuille
la selectionne, un attribut quand la declaration est purement d'etat. C'est exactement la distinction
qu'`ADR-0027` a ecrite pour lui-meme, et elle est desormais applicable sans exception.

**Ordre de grandeur : un lot petit a moyen**, et son cout est dans les fixtures : quatre gabarits de
production, `LEcranDeJeuDeclareCommentIlEstArriveTest`, la clause de completude du marqueur, et une
quinzaine de jeux fautifs sous `contreexemple/` dont **chaque preuve de morsure doit etre reverifiee**.
C'est ce dernier point qui domine, pas le renommage. **Recommandation : au perimetre s'il reste de la
place apres les six stories, jamais devant elles** - les six corrigent ce qu'un enfant voit.

### 6.3 Le predicat `estRemplacant` d'`ADR-0042` - **reste differe, et je propose une sortie plutot qu'un quatrieme report**

**Le declencheur que j'ai ecrit n'a toujours pas joue** : il faudrait un arret dont la requete demarre
une partie sur un ecran a partie en cours. Verifie sur les six stories : aucune n'en cree. `S16-2` ne
touche que l'adresse ; `S16-6` ajoute une phrase.

**Et le raisonnement de s14 tient encore** : cable dans les clauses, le predicat serait **vert sur une
population vide**, ce que l'obligation 3 d'`ADR-0013` refuse d'appeler un garde-fou. Le livrer pour
pouvoir cocher un `ADR` serait l'abstraction morte que ce depot s'interdit, avec un vernis d'executable.

**Mais c'est le troisieme sprint, et repeter le meme report est ce qui a produit le probleme.** Je
propose donc une sortie datee, et elle est de ma competence : **si le declencheur n'a pas joue a la
cloture de s16, `ADR-0042` doit changer de statut** - de `Accepte` a une forme qui dit la verite, sa
contrainte de forme restant valable et son predicat redevenant du le jour ou un ecran montre le
catalogue pendant une partie. Un `ADR` accepte dont la regle n'existe pas est ce que ce depot a paye ;
un `ADR` qui dit "cette decision attend son objet" ne coute rien a personne. **Ordre de grandeur du
predicat lui-meme, inchange : une dizaine de lignes, l'essentiel du cout etant la preuve de morsure.**

## 7. Les criteres que je refuse ou reecris - recapitulatif

| Story | Critere | Motif |
|---|---|---|
| S16-1 | 2 - session expiree par inactivite | demande a un automate de faire passer le temps. **Reecrit** sur le fait observable : aucune requete portant le marqueur, donc catalogue sans message |
| S16-2 | 1 - "l'adresse affichee par le navigateur devient..." | promet une propriete du navigateur. **Reecrit** sur ce que le serveur declare |
| S16-5 | 1 - "une lettre qui devrait porter un accent" | non decidable sans dictionnaire. **Reecrit** en inventaire de chaines exposees, meme moyen que le garde-fou existant |
| S16-6 | 2 - comparaison de deux elements d'un ecran | **renforce gratuitement** en propriete derivee sur tout ecran assemble, ce qui ferme aussi les routes futures |
| S16-3 | 1 - `:focus-visible` | encodable, mais seulement si l'etat entre dans la constante qui enumere les etats gardes : sinon le critere est vrai a l'ecrit et garde par rien |
| S16-4 | 1 - quatre positions cycliques | encodable en declaratif ; le constat sur les quatre positions rendues reste a `nadia-prof`, la story le dit deja |
| S16-5 | 2 - exemption de "CP"/"CM1" | encodable tel quel, **le mieux ecrit des vingt-et-un** ; une seule addition : l'exemption doit etre comptee par le recensement, sinon elle grandira en silence |

**Aucun critere n'epingle un effectif de catalogue cette fois** - je l'ai cherche, apres l'avoir refuse
deux fois. Les onze jeux sont cites dans `S16-2` comme un **repere de lecture** dans la phrase du besoin,
jamais dans un critere d'acceptation. C'est la bonne place, et il vaut de le noter.

## 8. Trois sprints sans mouvement de la matrice

`camille-po` le nomme et le motive plutot que de le laisser filer, ce qui est le contraire d'une derive
silencieuse. **Je n'ai aucun avis produit a rendre la-dessus** et je ne m'en donne pas un. Le seul fait
d'architecture a verser : les six stories de ce sprint sont toutes des **corrections de proprietes
transverses**, aucune n'ouvre ni ne ferme de ligne de programme, et **aucune ne touche `domain` ni
`application`** - a l'exception du port entrant, que `S16-5` lit par reflexion sans le modifier. Le
decoupage ne pese donc rien dans ce report ; s'il faut un motif, il est ailleurs, et il appartient a
`camille-po` et a `sofia-sm`.

## 9. Ce que cet avis ne dit pas

- **Si les six stories sont les bonnes**, ni dans quel ordre les affecter : `camille-po` et `sofia-sm`.
- **Aucun Done pedagogique**, ni son anticipation.
- **Aucune mesure de rendu** : la dependance a `nadia-prof` pour tout chiffre de pixels est inchangee, et
  `DEC-0037` la nomme. Ce que le depot peut faire pour la reduire est ecrit en s14, section 7.4 de
  `confirmation-archunit.md`, et n'a pas bouge : rendre la commande de mesure executable par quelqu'un
  qui ne l'a jamais lancee, sans jamais pretendre la rendre independante d'un poste equipe.
