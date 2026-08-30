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
