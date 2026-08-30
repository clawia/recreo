# Fiche de conformite - bandit-manchot-mots-de-liaison-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu** : `cm1-mots-de-liaison`, 20 items, contenu neuf, story `S23-LIA`, ecrit par
  `sofia-sm` (developpeuse).
- **Arbitrage prealable de `nadia-prof`** : rendu le 2026-08-30,
  `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md`,
  section 6 (`LIA-1` a `LIA-10`).
- **Type d'audit** : **`COMP-2`**. Ce paquet consomme a lui seul la condition d'au plus un paquet de
  ce type par sprint. La condition est tenue a s23 : c'est le seul.
- **Fiche ouverte le 2026-08-30 par `nadia-prof`**, faute de fiche existante au moment de l'audit.
  La section "criteres pedagogiques attendus" appartient a `camille-po` et n'est pas ecrite ici.

## Competence visee

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Vocabulaire | Utiliser a bon escient des mots de liaison simples pour structurer un texte court | `squad/programme/francais/competences-cm1.md`, ligne 32 |

## Bloc de validation

### Done pedagogique - 2026-08-30 (s23) - REFUSE

**Audit conduit en jouant**, application lancee depuis le fat jar construit sur l'arbre du
worktree, sur le JDK 21 de la toolchain declaree. Deux parties completes de dix manches, jouees en
pressant chaque proposition fausse avant la bonne. Ce n'est pas un audit degrade.

#### Correction de mon propre constat, faite avant de m'en servir

Lors de la premiere partie, l'item *"___, le cuisinier epluche les legumes"* m'a rendu **`d'abord`
faux alors que `d'abord` est la bonne reponse**. J'ai failli l'ecrire comme un defaut du produit.
**C'en etait un de mon instrument** : je relisais la valeur des boutons dans le HTML, ou
l'apostrophe est echappee, et je renvoyais la forme echappee au serveur. Corrige, rejoue :
`d'abord` est accepte. **Le produit n'a pas ce defaut, et ce paragraphe reste ecrit pour que
personne ne me relise en croyant que je l'ai vu.**

#### Ce que j'ai verifie, et qui tient

Recompte sur la population entiere des 20 items, jamais repris de l'auteure.

| Condition | Verdict | Mesure, population : les 20 items |
|---|---|---|
| `LIA-1` | **tenue** | 20 items de une a trois phrases, chacun portant un seul `___`. Consigne du catalogue constante |
| `LIA-2` | **tenue** | Les 60 mots servis appartiennent tous a la population fermee de dix. Aucun `et`, aucun `ou`, `ni`, `or`, aucun registre soutenu |
| `LIA-4` | **tenue** | Sur les 20 items, les trois relations declarees (bonne reponse et deux distracteurs) sont deux a deux distinctes |
| `LIA-5` | **tenue** | **opposition 5, consequence 5, cause 5, succession 5**, contre un plancher de `arrondi inferieur(20/6)` = 3. **Le compte declare est exact** |
| `LIA-6` | **tenue** | Enonce le plus long du paquet : **15 mots**, tres sous la borne de 35 |
| `LIA-7` | **tenue** | Aucun mot de plus de onze lettres sur les 20 items |
| `LIA-8` | **tenue** | Les 20 indices nomment la relation a chercher et jamais le mot ; douze mots au plus. Verifies a l'ecran apres erreur |
| `LIA-9` | **tenue** | 20 items ; `donc` 3, `car` 3, `mais` 3, `alors` 2, `parce que` 2, `pourtant` 2, `puis` 2, `ensuite` 1, `enfin` 1, `d'abord` 1. Plafond de six jamais approche. **Le compte declare est exact** |
| `LIA-10` | **a porter par `camille-po`** | La mention exacte est ecrite dans l'en-tete du paquet et reprise plus bas |

**Ce que `sofia-sm` declare de son test est exact, et sa franchise m'a fait gagner du temps.** Elle
ecrit que son test prouve que les deux cles d'auteur existent, que les relations sont admises et
deux a deux distinctes, et **qu'il ne prouve ni que la relation declaree est celle du texte, ni que
chaque distracteur rend le texte faux**. C'est vrai, et c'est exactement la frontiere. Les deux
cles m'ont permis de **recompter** au lieu d'etablir - le gain le plus rentable de s22 - et le
jugement de sens reste entier.

#### Motif de refus - `LIA-3` : deux distracteurs rendent le texte moins bon, pas faux

`LIA-3` dit, et c'est ma condition la plus dure de ce paquet : *"Un distracteur qui produit un texte
seulement moins bon, et non faux, est refuse."* Motif ecrit le 2026-08-30 : *"un item a deux
reponses justes dont une seule est comptee bonne n'apprend pas une regle : il apprend a l'enfant
qu'il a tort quand il a raison, et c'est le pire defaut qu'un contenu scolaire puisse avoir."*

**J'ai lu les 40 distracteurs un par un, avec la relation declaree et le motif de faussete declare
en regard. Trente-huit tiennent. Deux ne tiennent pas.**

| Item | Texte que le distracteur produit | Relation declaree fausse | Pourquoi ce texte n'est pas faux |
|---|---|---|---|
| 3, distracteur `puis` | *"Il a beaucoup revise ses lecons. Puis, il a reussi son controle."* | succession | **La succession est reelle.** Il revise avant, il reussit apres. Le texte est grammatical, coherent et vrai ; il est seulement plus plat que celui avec `donc`, qui dit en plus le lien causal. Le fichier declare *"la reussite n'est pas un evenement independant qui suit simplement dans le temps"* - c'est un argument de qualite, pas de faussete |
| 18, distracteur `donc` | *"La fusee quitte le sol. Donc, elle traverse les nuages."* | consequence | **La consequence est reelle.** Traverser les nuages decoule bien du decollage : une fusee qui quitte le sol monte, donc traverse les nuages. Le fichier declare *"traverser les nuages n'est pas une consequence logique du decollage, c'est simplement l'etape suivante dans le temps"* - les deux lectures sont bonnes, et l'enfant qui choisit `donc` est compte faux en ayant raison |

**Vu en jouant, pas seulement lu.** Sur l'item 18, j'ai presse `donc` et l'ecran m'a rendu *"Ce
n'est pas ca. Essaie encore !"*.

**Ou j'ai place la frontiere, pour que personne n'ait a me la redemander.** Le cas voisin le plus
proche est l'item 20, distracteur `donc` (*"Les randonneurs traversent la foret. Ils longent la
riviere. Donc, ils atteignent le sommet."*). **Je ne le compte pas en defaut** : atteindre un sommet
n'est pas entraine par le fait de longer une riviere, la relation de consequence y est reellement
absente. La difference avec l'item 18 est physique : une fusee qui decolle traverse necessairement
les nuages, des randonneurs qui longent une riviere n'atteignent pas necessairement un sommet.
**Deux items en defaut, pas trois.**

#### Ce qui rendrait ce refus levable, sans avoir a me le redemander

Remplacer les deux distracteurs nommes, et eux seuls. Critere applicable par un tiers : le
distracteur doit produire un texte qu'un adulte corrigerait, pas un texte qu'il trouverait moins
elegant. Deux voies, au choix de l'auteure :

- **item 3** : remplacer `puis` par un mot de la population fermee dont la relation est reellement
  absente entre les deux phrases - `pourtant` ou `car` conviennent, `car` inversant la causalite ;
- **item 18** : remplacer `donc` par `car` (le decollage n'est pas cause par la traversee des
  nuages) ou par `mais` (aucune opposition), ou changer la seconde phrase pour un evenement qui ne
  decoule pas du decollage.

**`LIA-4` doit rester tenue apres remplacement** : les trois relations de l'item restent deux a deux
distinctes. Les deux propositions ci-dessus la respectent.

#### Ce sur quoi je reste la seule garde sur ce paquet

- **`LIA-3` et `LIA-4`, qui sont l'essentiel**, et `LIA-3` est ce sur quoi ce refus porte. Le test
  de `sofia-sm` verifie `LIA-4` par recalcul ; **`LIA-3` n'est verifiable par aucun automate** et son
  auteure l'ecrit elle-meme. Une CI verte a laisse passer les deux items.
- **La justesse de la relation declaree** face au texte reel, item par item : tenue sur les 20.

#### Constat neuf et date du 2026-08-30, non bloquant, qui n'entre pas dans ce refus

**Sur les dix items dont le `___` est en milieu de phrase, cinq distracteurs sont des adverbes
(`puis`, `ensuite`, `enfin`, `d'abord`) juxtaposant deux propositions independantes sans virgule.**
Le texte faux y est aussi mal ponctue, et l'enfant peut l'ecarter a l'oreille plutot que par la
relation logique. Ce n'est pas une erreur - c'est un **indice de forme** qui double l'indice de
sens et qui rend ces items un peu plus faciles qu'ils ne devraient l'etre. **La ponctuation
elle-meme n'est pas ma porte** (`DEC-0056`) ; ce que je nomme ici est l'effet sur la difficulte, qui
l'est. Condition nouvelle et datee, pour un paquet suivant, jamais un durcissement de `LIA-3`.

#### `LIA-10` - couverture partielle nommee, si et quand le refus est leve

**Ce n'est pas une remarque, c'est une condition.** Le jour ou la ligne bouge, la mention exacte a
porter sur `MATRICE-couverture.md` est :

> **choix du mot de liaison entre deux ou trois phrases, sans production de texte**

Aucune ligne ne bouge aujourd'hui : la ligne `francais | CM1 | Vocabulaire | Utiliser a bon escient
des mots de liaison simples pour structurer un texte court` reste `non couvert`.

#### `MNC-1`

**Sans objet ici** : `MNC-1` porte sur tout Done pedagogique prononce, `VALIDE` ou `VALIDE SOUS
RESERVE`. **Un refus n'est pas un Done.**

#### Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **Refus sur `LIA-3`, deux items nommes (3 et 18), deux distracteurs a remplacer** | `camille-po`, par `sofia-sm` | En faire du backlog. Le paquet n'est pas a reecrire : 38 distracteurs sur 40 tiennent, et les neuf autres conditions sont tenues |
| **`LIA-1`, `LIA-2`, `LIA-4` a `LIA-9` sont tenues, recomptees sur les 20 items** | `camille-po` | Ne pas rouvrir ce qui tient |
| **Ma frontiere entre "faux" et "moins bon" est ecrite avec son cas voisin (item 20)** | `sofia-sm`, `camille-po` | Elle est reutilisable sans moi sur le prochain paquet de connecteurs |
| **`LIA-3` reste hors de tout garde-fou, et son auteure l'ecrit** | `yanis-qa`, `theo-archi` | Ne pas la compter comme couverte. Le test declaratif est un vrai gain et ne remplace pas le jugement |
| **L'indice de ponctuation sur les items a `___` median** | `camille-po` | Condition nouvelle et datee, pour un paquet suivant. Ne fait pas partie de ce refus |

---

### Done pedagogique - 2026-08-30, reaudit apres correctif (s23) - REFUSE

**Amendement date. Le refus du 2026-08-30 ci-dessus n'est pas reecrit et reste lisible en
entier.** Ce bloc s'ajoute, il ne remplace rien. C'est le **second** refus sur ce paquet, et il ne
porte pas sur les memes items que le premier.

**Le motif du 2026-08-30 est leve.** Les items 3 et 18 sont corriges, exactement comme je l'avais
demande. **Le refus d'aujourd'hui porte sur un troisieme item, l'item 10, que `sofia-sm` a trouve
elle-meme et m'a route sans y toucher.** Il n'est pas cause par le correctif : il etait la le
2026-08-30 et **je ne l'ai pas vu**, alors que j'ecrivais avoir lu les 40 distracteurs un par un.

#### Ce que je corrige de mon propre bloc du 2026-08-30, avant de m'en servir

Le 2026-08-30 j'ai ecrit : "**J'ai lu les 40 distracteurs un par un** [...] **Trente-huit
tiennent.** Deux ne tiennent pas." **Ce compte etait faux : il y en avait trente-sept qui tenaient,
et trois qui ne tenaient pas.** `sofia-sm` a verifie les 38 que j'avais declares bons et en a trouve
un qui ne l'est pas. Elle a eu raison de le remonter et raison de ne pas trancher a ma place.

**Et j'avais mal ecrit ma propre frontiere.** Le 2026-08-30 je l'ai formulee ainsi : "*La difference
avec l'item 18 est physique : une fusee qui decolle traverse necessairement les nuages, des
randonneurs qui longent une riviere n'atteignent pas necessairement un sommet.*" Cela se lit comme
un **test de necessite**, et c'est ainsi que `sofia-sm` l'a lu - son motif de ne pas toucher l'item
10 est *"un arrosage insuffisant en ete est un scenario reel, contrairement aux items 3 et 18 ou la
necessite logique etait demontrable"*. **Sa lecture de ce que j'avais ecrit est correcte. C'est ce
que j'avais ecrit qui etait imprecis.**

La necessite n'etait pas mon test : c'etait ma **preuve** sur ces deux items-la. Le test que
j'appliquais reellement, et que `LIA-3` enonce, est celui-ci :

> **Un connecteur affirme une relation entre les deux faits de l'enonce. Le distracteur est valide
> si la relation qu'il affirme n'est pas celle qui existe entre ces deux faits ; il est refuse si
> elle l'est.** Le texte produit est alors vrai, seulement moins riche que celui de la bonne
> reponse, et l'enfant qui le choisit est compte faux en ayant raison.

**Ce n'est pas un durcissement, et je le verifie plutot que de l'affirmer** : sous ce test,
**l'item 20 reste bon**, exactement comme le 2026-08-30. "Les randonneurs traversent la foret. Ils
longent la riviere. Donc, ils atteignent le sommet." affirme une consequence ; aucune consequence
n'existe entre longer une riviere et atteindre un sommet. La relation affirmee n'existe pas, le
texte est faux, le distracteur est bon. **Le poteau n'a pas bouge. Ma phrase a ete mieux ecrite.**

#### Ce que le correctif a change, verifie ligne a ligne, et ce qu'il leve

**Deux valeurs de distracteur, et rien d'autre dans les items.** *Methode : difference du paquet
entre le commit qui porte mon refus et l'arbre reaudite ; population : le fichier entier.* Les 20
enonces, les 20 bonnes reponses et les 20 indices sont intacts. **La declaration de `sofia-sm` est
exacte, et l'item 20 n'est pas touche, conformement a la frontiere que j'avais tracee.**

| Item | Distracteur | Texte produit | La relation affirmee existe-t-elle ? |
|---|---|---|---|
| 3 | `puis` remplace par **`car`** | "Il a beaucoup revise ses lecons. Car, il a reussi son controle." | **Non.** La reussite au controle ne peut pas expliquer une revision qui la precede : la cause affirmee est inversee dans le temps. Texte faux, distracteur bon |
| 18 | `donc` remplace par **`car`** | "La fusee quitte le sol. Car, elle traverse les nuages." | **Non.** Le decollage n'est pas cause par la traversee des nuages, c'est l'inverse. Texte faux, distracteur bon |

**Vu a l'ecran, pas seulement lu.** Douze parties completes, jouees en pressant chaque proposition
fausse avant la bonne ; les 20 items rencontres.

- Item 3 : `mais` puis `car` presses, tous deux rendus "Ce n'est pas ca. Essaie encore !", puis
  `donc` rendu "Bravo !".
- Item 18 : `car` presse, rendu "Ce n'est pas ca. Essaie encore !", puis `puis` rendu "Bravo, c'est
  juste !".

**`LIA-3` est levee sur les items 3 et 18. Mon motif du 2026-08-30 est ferme.**

#### Les conditions calculables, recomptees apres correctif

*Population : les 20 items, les 40 distracteurs, recomptes sur l'arbre reaudite, jamais repris.*

| Condition | Verdict | Mesure |
|---|---|---|
| `LIA-2` | **tenue** | Les 60 mots servis appartiennent a la population fermee de dix. `car` en fait partie |
| `LIA-4` | **tenue** | Sur les 20 items, les trois relations declarees sont deux a deux distinctes. Sur l'item 3 : consequence / opposition / cause ; sur l'item 18 : succession / cause / opposition |
| `LIA-5` | **tenue** | opposition 5, consequence 5, cause 5, succession 5. Inchange : le correctif ne touche aucune bonne reponse |
| `LIA-6` | **tenue** | Enonce le plus long : **15 mots**, borne 35 |
| `LIA-7` | **tenue** | Aucun mot de plus de onze lettres |
| `LIA-9` | **tenue** | `donc` 3, `car` 3, `mais` 3, `alors` 2, `parce que` 2, `pourtant` 2, `puis` 2, `ensuite` 1, `enfin` 1, `d'abord` 1. Plafond 6 |
| `LIA-1`, `LIA-8` | **tenues** | Un seul blanc par item ; les 20 indices nomment la relation sans jamais nommer le mot, verifies a l'ecran apres erreur |

**Rien de ce que le correctif touche n'a casse quoi que ce soit d'autre.**

#### Motif de refus - `LIA-3`, item 10, distracteur `mais`

> "Le jardinier arrose les fleurs chaque soir **mais** la terre est tres seche en ete."

**La relation affirmee existe.** `mais` affirme une opposition entre les deux faits. Elle est la :
arroser sert a garder la terre humide, et la terre est tres seche. Le second fait defait l'attente
que le premier cree - c'est exactement la concession, et c'est la meme structure que celle que le
paquet lui-meme tient pour legitime a l'item 14 ("Le vieux pont parait fragile pourtant il supporte
encore le passage des camions"). **Un arrosage du soir qui ne suffit pas contre la secheresse d'ete
n'est pas une lecture charitable : c'est la premiere qui vient.**

**Mon critere de levee du 2026-08-30, applique tel quel** : *"le distracteur doit produire un texte
qu'un adulte corrigerait, pas un texte qu'il trouverait moins elegant."* **Aucun adulte ne
corrigerait cette phrase.** Elle est grammaticale, coherente, et decrit une situation ordinaire.

**Consequence pour l'enfant, et c'est elle qui bloque.** Un enfant de CM1 qui lit "il arrose chaque
soir" et "la terre est tres seche", et qui en conclut que l'arrosage ne suffit pas, a construit
exactement le raisonnement que la lecon sur les connecteurs cherche a installer. Le jeu lui rend
"Ce n'est pas ca. Essaie encore !" **Il est compte faux en ayant raison, et j'ai ecrit le
2026-08-30 que c'est le pire defaut qu'un contenu scolaire puisse avoir. Je ne peux pas l'ecrire un
jour et le laisser passer le lendemain parce que l'equipe a corrige le reste.**

*Observation secondaire, qui ne fonde pas le refus mais qui va dans le meme sens.* La bonne reponse
elle-meme est un peu lache sur cet item : `parce que` fait de la secheresse **d'ete** la raison d'un
arrosage **de chaque soir**, alors que rien ne restreint la scene a l'ete. Rien ne vient donc
ecraser la lecture concessive, qui reste disponible a egalite.

**Vu a l'ecran** : item 10 rendu avec `d'abord`, `parce que`, `mais` ; `parce que` accepte, `mais`
present et compte faux.

#### Ou je place la frontiere, avec le cas voisin que je garde - pour qu'il n'y ait pas de troisieme tour

**J'ai relu les 40 distracteurs un par un, contre le test enonce plus haut, et ce compte est
complet.** *Population : les 20 items, les 40 distracteurs, chacun juge contre sa relation declaree
et les deux faits de son enonce.* **Trente-neuf tiennent. Un ne tient pas : l'item 10.** Il n'y a
pas d'autre item a trouver sur `LIA-3` dans ce paquet.

**Le cas voisin le plus proche, que je ne compte pas en defaut : l'item 7, distracteur `mais`.**

> "Le chien aboie fort **mais** un inconnu s'approche de la maison."

On pourrait y lire une opposition - le chien aboie, l'inconnu approche quand meme. **Je ne la
retiens pas, et je dis pourquoi**, parce que la difference avec l'item 10 est la seule chose qui
rend ma frontiere reutilisable sans moi :

- l'opposition de l'item 7 n'existe que si le lecteur **apporte lui-meme** une croyance que le texte
  ne contient pas ("un chien qui aboie fait fuir les inconnus") ;
- et elle exige d'**inverser l'ordre canonique** de la scene, ou le chien aboie *parce que*
  quelqu'un approche - c'est-a-dire la lecture que la bonne reponse `car` installe et qui domine.

A l'item 10, aucune croyance n'est a apporter et aucun ordre n'est a inverser : la secheresse defait
l'arrosage sur la phrase telle qu'elle est ecrite.

**Enonce de la frontiere, opposable sans moi** : *un distracteur est refuse quand la relation qu'il
affirme se lit sur les deux faits tels qu'ils sont ecrits, sans qu'il faille apporter une croyance
absente du texte ni renverser l'ordre que le texte pose.*

**Ou mon jugement est le plus mince, et je le nomme** : l'item 7. Un autre professeur pourrait le
compter en defaut. Il n'est pas dans mon motif, et si quelqu'un veut le traiter, ce sera une
condition nouvelle et datee, jamais un durcissement de celle-ci.

#### Ce qui rendrait ce refus levable, sans avoir a me le redemander

**Remplacer le distracteur `mais` de l'item 10, et lui seul.** Sa relation declaree doit rester
distincte de `cause` (la bonne reponse `parce que`) et de `succession` (l'autre distracteur
`d'abord`), donc etre `opposition` ou `consequence`. Deux voies :

- **`donc`** ou **`alors`** (relation `consequence`) : "Le jardinier arrose les fleurs chaque soir
  donc la terre est tres seche en ete." L'arrosage ne rend pas la terre seche - la consequence
  affirmee n'existe pas, et elle est meme contraire. Distracteur bon ;
- **surtout pas `pourtant`**, qui porte la meme concession que `mais` et reproduirait le defaut.

`LIA-4`, `LIA-5` et `LIA-9` restent tenues dans les deux cas : `LIA-9` ne compte que les bonnes
reponses, qu'aucun de ces remplacements ne touche.

#### Ce sur quoi je reste la seule garde sur ce paquet

- **`LIA-3`, integralement, et son auteure l'ecrit elle-meme.** Deux refus consecutifs sur cette
  seule condition, la CI verte les deux fois. **Aucun automate ne la voit**, et le test declaratif
  de `sofia-sm` - qui est un vrai gain, il m'a permis de recompter au lieu d'etablir - ne pretend
  pas la couvrir.
- **La justesse de la relation declaree** face au texte reel : tenue sur les 20.
- **Le jugement de niveau.**

#### Constat neuf et date du 2026-08-30, non bloquant, qui n'entre pas dans ce refus

Le 2026-08-30 j'ai nomme un **indice de forme** sur les items dont le blanc est suivi d'une virgule.
Le correctif l'etend : `car` place apres un point et suivi d'une virgule ("Car, il a reussi son
controle.") est une forme qu'un enfant peut ecarter a l'oreille sans raisonner sur la relation.
**Ce patron existait deja sur cinq items avant le correctif** - 2, 4, 16, 17, 19 - et **passe a
sept** avec les items 3 et 18. Ce n'est pas un defaut de ces deux items : c'est un patron que
j'avais deja date et laisse hors de mon refus, et qui grandit. **Il ne fait pas partie de ce refus
et je n'en fais pas une reserve** ; c'est une condition pour un paquet suivant. La ponctuation
elle-meme n'est pas ma porte (`DEC-0056`) ; ce que je nomme est son effet sur la difficulte.

#### `LIA-10`

Aucune ligne ne bouge. La ligne `francais / CM1 / Vocabulaire / Utiliser a bon escient des mots de
liaison simples pour structurer un texte court` **reste `non couvert`**. Le jour ou elle bougera, la
mention exacte reste : "choix du mot de liaison entre deux ou trois phrases, sans production de
texte".

#### `MNC-1`

**Sans objet ici** : `MNC-1` porte sur tout Done pedagogique prononce, `VALIDE` ou `VALIDE SOUS
RESERVE`. **Un refus n'est pas un Done.**

#### Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **`LIA-3` levee sur les items 3 et 18. Mon motif du 2026-08-30 est ferme** | `sofia-sm`, `camille-po` | Ne pas rouvrir ces deux items. Le correctif est exactement celui que j'avais decrit |
| **Refus sur `LIA-3`, item 10, distracteur `mais`. Un mot a changer** | `camille-po`, par `sofia-sm` | Backlog. Le critere de levee est ecrit, avec deux remplacements nommes et un a eviter |
| **Ma frontiere du 2026-08-30 etait mal ecrite ; elle est reenoncee ici avec ses deux cas voisins (items 20 et 7)** | `sofia-sm`, `camille-po` | C'est ma faute, pas la sienne : `sofia-sm` a lu correctement ce que j'avais ecrit. La forme reenoncee est opposable sans moi |
| **Mon compte du 2026-08-30 etait faux : 37 tenaient, pas 38** | `camille-po` | Le compte d'aujourd'hui est complet : 39 / 40. Il n'y a pas de troisieme tour a prevoir sur `LIA-3` |
| **`sofia-sm` a verifie les 38 distracteurs que j'avais declares bons, et en a trouve un qui ne l'etait pas** | `sofia-sm` | C'est la bonne conduite, et c'est le seul motif de ce refus. Verifier le verdict de la porte plutot que le croire est ce qui a fait remonter l'item 10 |
| **`LIA-3` reste hors de tout garde-fou** | `yanis-qa`, `theo-archi` | Deux refus consecutifs sur elle, CI verte les deux fois. Ne pas la compter comme couverte |
| **Le patron "connecteur apres un point, suivi d'une virgule" passe de cinq a sept items** | `camille-po` | Condition **nouvelle et datee du 2026-08-30**, pour un paquet suivant. Ne fait pas partie de ce refus |
| **Le `relecteur-pedagogique` n'est pas passe sur ce sprint** | `sofia-sm` | Rien de ce bloc ne juge l'orthographe, l'accentuation ni la ponctuation (`DEC-0056`) |
