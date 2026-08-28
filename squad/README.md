# squad - le journal operationnel de recreo

Ce dossier est le journal de l'equipe qui construit `recreo`. Il est un **livrable de premiere
classe**, au meme titre que le code Java.

## Le principe qui fonde tout

**Le depot Git est la seule memoire partagee de la squad.**

Ce qui n'est pas ecrit ici n'existe pas :

- une ceremonie sans fichier dans `squad/sprints/` n'a pas eu lieu ;
- une decision qui n'est pas redigee en `ADR` ou en `DEC` n'a pas ete prise, meme si tout le
  monde l'applique ;
- un obstacle n'est clos que quand le fichier qui le tranche **existe physiquement** ;
- un test qui n'est pas ecrit ne couvre rien.

Les agents n'ont pas de memoire entre deux invocations. Ce fichier, et ceux qui l'entourent, sont
cette memoire.

## 1. L'equipe

Neuf agents, decrits dans [`EQUIPE.md`](EQUIPE.md), definis dans `.claude/agents/`.

Trois developpeurs codent : Marc, Lea, et Sofia sous sa casquette dev. Deux agents detiennent une
autorite exclusive : `theo-archi` sur l'architecture, `nadia-prof` sur la pedagogie. Un agent
propose sans trancher : `ines-design`, sur le design d'interface et l'accessibilite visuelle, sous
le veto pedagogique de `nadia-prof`.

## 2. Conventions de nommage

La regle generale : **le tri alphabetique d'un dossier doit donner le tri chronologique ou
logique.**

| Type | Chemin | Proprietaire |
|---|---|---|
| Sprint | `squad/sprints/sNN-<slug>/` | `sofia-sm` |
| Ceremonie | `<sprint>/planning.md`, `<sprint>/review.md`, `<sprint>/retro.md` | `sofia-sm` |
| Synthese de sprint | `<sprint>/RAPPORT.md` | `sofia-sm` |
| Consommation de tokens | `<sprint>/RAPPORT-tokens.md` | `sofia-sm` (genere) |
| ADR technique | `squad/decisions/adr/ADR-XXXX-<slug>.md` | `theo-archi` |
| Decision produit | `squad/decisions/produit/DEC-XXXX-<slug>.md` | `camille-po` |
| Fiche de conformite | `squad/conformite/FICHE-<jeu>.md` | `camille-po` ouvre, `nadia-prof` valide |
| Referentiel de competences | `squad/programme/<matiere>/competences-<niveau>.md` | repris, en lecture |
| Matrice de couverture | `squad/programme/MATRICE-couverture.md` | `camille-po` |
| Journal des tokens | `squad/rapports/JOURNAL-tokens.md` | `sofia-sm` (genere) |

Numerotation des `ADR` et des `DEC` : quatre chiffres, **jamais reutilisee**, meme quand une
decision est rejetee ou remplacee. Ca garde l'historique honnete. `ADR-index.md` et
`DEC-index.md` sont des tableaux recapitulatifs, une ligne par decision, tenus a jour par leur
proprietaire a chaque creation et a chaque changement de statut.

**Un fichier par ceremonie. On n'ajoute jamais a la suite d'un fichier existant** : diffs Git
propres, pas d'ecriture concurrente, tri par date automatique.

**Nomenclature des conditions et reserves pedagogiques de `nadia-prof` (amendement s16, retro de
s16).** Quatre conventions de numerotation coexistent sans jamais avoir ete reliees (`BARRE-n`,
`S15-n`, `R-BARRE-n`/lettre seule, `PORTE-n`), au point qu'une meme reserve porte deux noms dans
des documents differents et qu'un meme jeton (`S15-<n>`) designe selon le contexte une story ou
une condition - trouve par `relecteur-pedagogique` en s16, chaque reference verifiee exacte,
aucune fantome. `theo-archi` a etabli que ces jetons sont cites par onze fichiers du code, dont
la feuille de style servie et sept classes de garde-fou : les renommer casserait des references
que rien ne detecte, donc aucun renommage n'est fait ni exige par cet amendement. La regle : toute
famille de jetons neuve ou tout renommage introduit par `nadia-prof` est repris dans une **table
de correspondance unique**, portee par la fiche de conformite du jeu concerne (premier exemple :
`squad/conformite/FICHE-barre-entree-niveau.md`, section "Table de correspondance"), avec ses
regles de lecture quand un jeton est ambigu. Un garde-fou qui protege ces references contre un
renommage silencieux (sur le patron de `ConcordanceDesTagsDAgentTest`) ne peut etre ecrit qu'une
fois cette table existante - sans elle, il n'y a rien contre quoi comparer une reference trouvee
dans le code.

## 3. Pilotage par increment, pas par duree

Il n'y a **pas de timebox**. Un sprint est un **increment livrable**, quelle que soit sa duree
reelle.

`planning.md` porte l'horodatage de **debut**, `review.md` porte l'horodatage de **fin** et la
duree effective. C'est cette fenetre temporelle qui sert aussi a attribuer la consommation de
tokens au sprint (voir section 6).

## 4. Les ceremonies

Trois produisent un fichier, une non.

| Ceremonie | Fichier | Ce qu'elle contient obligatoirement |
|---|---|---|
| Planning | `planning.md` | Horodatage de debut, objectif gele, stories avec competence visee, affectations nominatives, avis de faisabilite de Theo |
| Daily | **aucun** | Synchronisation de travail. Sofia collecte, classe et route ; elle n'ecrit pas de fichier |
| Review | `review.md` | Horodatage de fin, duree, rapport qualite de Yanis, confirmation ArchUnit de Theo, corrections du relecteur, tableau des obstacles, avis de Nadia et Done pedagogique |
| Retro | `retro.md` | Ce qui a marche, ce qui a coince, amendements a ce README |

**Pourquoi le daily n'est pas journalise.** Sur la version precedente de `recreo`, le daily etait
une ceremonie documentee dont **aucun fichier n'a jamais ete ecrit en 25 sprints**. Plutot que de
maintenir une regle que personne ne suit, on acte la pratique reelle.

Consequence a tenir : le **tableau des obstacles** ne disparait pas avec le daily. Il remonte
dans `review.md` et dans `RAPPORT.md`. Sans lui, la chaine "obstacle route vers un agent,
decision ecrite, obstacle clos" serait perdue.

Regle de cloture : un fichier de ceremonie n'est complet que s'il contient une section
`## Decisions prises` **non vide**, ou l'enonce explicite qu'aucune decision n'a ete prise.

**Methode de comptage obligatoire (amendement s06, retro de s05).** Tout chiffre de comptage cite
dans `rapport-qualite.md` ou `confirmation-archunit.md` (nombre de tests, nombre de regles ArchUnit,
nombre d'items de contenu) porte sa methode dans le meme paragraphe que le chiffre : perimetre
parcouru, source de la liste, ce qui est explicitement exclu. Un chiffre sans methode ne se compare
a rien, meme quand il est juste - en s05, `theo-archi` a du corriger un meme chiffre trois fois
("29" puis "25" puis "27" regles ArchUnit) faute de l'avoir ecrite la premiere fois. **Le comptage
se fait apres un `clean`** : un rapport laisse par une execution mutee anterieure produit un total
juste avec des echecs faux, et `theo-archi` s'y est lui-meme repris une fois en s06 en le
verifiant. **Piege nomme pour un futur comptage de `ArchRule`** : compter les annotations
`@ArchTest` sous-estime le total d'une unite des lors qu'une regle est executee par un `.check()`
explicite plutot que par ce mecanisme (28 `ArchRule` pour 27 `@ArchTest` en s06,
`ScanDeComposantsTest`) - les deux nombres avaient coincide en s05, ce qui n'aurait rien signale.

**Methode de mesure de rendu obligatoire (amendement s06, retro de s06).** La meme exigence
s'etend a toute mesure de **rendu** (largeur affichee, debordement, contraste, position d'un
element) citee dans un rapport, une fiche de conformite ou un `ADR` : la commande exacte qui l'a
produite est citee dans le meme paragraphe que le resultat, jamais decrite en prose seule
("verifie a 360 px" sans dire comment). En s06, trois agents (`lea-dev`, `ines-design`,
`yanis-qa`) ont mesure la meme propriete de rendu par trois methodes distinctes et obtenu trois
resultats differents, sans qu'aucun n'ait menti - le depot ne portait aucune methode reproductible
sous 500 px (le plancher de `chrome --headless --window-size`) et aucune des deux mesures d'origine
n'avait laisse de commande citable. `outils/mesurer-rendu.sh` (`ADR-0025`) porte desormais cette
commande pour toute mesure sous ce plancher.

**Une verification annoncee sans sa methode est sanctionnee comme un chiffre sans sa methode**
(amendement s16, cloture de s16). Extension de l'exigence ci-dessus, proposee par `theo-archi`
contre lui-meme : dans son avis de faisabilite de s16, il avait ecrit avoir verifie qu'aucun
critere n'epinglait un effectif de catalogue, sans dire comment - en realite il avait trouve
l'effectif dans l'enonce du besoin et avait arrete de chercher, puis ecrit avoir cherche. Le
`relecteur-pedagogique` a trouve la faille par un simple `grep`, que la phrase aurait du citer la
premiere fois. Une affirmation de la forme "verifie", "cherche et rien trouve" ou "aucun ne le
fait" porte, dans le meme paragraphe, la commande ou la methode de lecture qui l'a produite -
sinon elle ne se distingue pas d'une affirmation non verifiee, et elle est pire : elle se
presente comme verifiee.

**Une propriete visible seulement par un humain qui joue n'est pas gardable par l'outillage du
depot, et un verdict qui s'appuie sur elle le dit explicitement** (amendement s15, cloture de
s15). `ADR-0025` avait deja declare non gardable le debordement de mise en page ; s15 en nomme deux
instances de plus, chacune avec sa cause ecrite par `nadia-prof` : l'etat de survol reel d'un
pressable (le seul outil de mesure du depot pilote un navigateur en mode capture, rien n'y conduit
un navigateur, donc aucune image du depot n'a jamais montre un bouton sous le doigt) et un jugement
de comprehension d'enfant ("le plein se lit deja choisi") qui repose sur un raisonnement nomme,
jamais sur une mesure. Un verdict qui credite une telle propriete la declare degradee dans le meme
paragraphe, sur le meme registre que l'amendement de s12 pour les releves automatiques cites en
preuve - la difference etant que, ici, aucune commande future ne fera jamais disparaitre la
limite : elle est structurelle a l'outillage actuel, pas a l'execution d'une mesure donnee.

**Une limite de mesure declaree nomme l'etat exact qu'elle couvre, jamais une categorie plus
large qui l'englobe par analogie** (amendement s16, cloture de s16). `nadia-prof` a declare cinq
sprints de suite ne pas pouvoir mesurer l'etat de survol d'un pressable (cause reelle : l'outil de
mesure du depot pilote un navigateur en mode capture, rien n'y conduit un pointeur), et a laisse
cette limite couvrir aussi le focus clavier sans jamais l'avoir essaye. En s16 elle a distingue les
deux - le focus clavier est un etat que le document peut prendre lui-meme, sans pointeur - ecrit
une sonde, mesure, et leve la limite pour cet etat seul. Cette mesure neuve a immediatement rendu
verifiable un defaut jusque-la seulement declaratif (l'anneau de focus clavier tiers, `ADR-0046`)
et en a trouve un troisieme de la meme famille (reserve `S16-A`). Le cout de cinq sprints de
sur-extension silencieuse d'une limite est le motif de cet amendement : une limite couvre l'etat
qu'elle a effectivement essaye et echoue a mesurer, jamais un etat voisin qui n'a jamais ete tente.

**Un chiffre cite dans un document qui le reprend d'un autre document du depot est verifie contre
sa source primaire avant d'etre recopie, jamais suppose exact parce qu'il porte deja une reference**
(amendement s17, cloture de s17). Le chiffre de couverture programme de `DEC-0037` section 4 (23
lignes `couvert-valide` sur 74) a ete recopie sans reverification dans trois documents de plus
(`planning.md` et `RAPPORT.md` de s16, `planning.md` de s17), alors qu'il etait deja fautif dans
`DEC-0037` elle-meme des son premier commit. Distinct de la regle de s06 (un chiffre porte sa
methode) : ici la methode etait bien citee a chaque reprise, ce qui manquait est la verification
contre la source primaire, pas contre l'absence de methode.

**Un chiffre porte sa date dans la meme phrase que sa methode, jamais dans une parenthese a
cote qu'un lecteur presse saute** (amendement hors sprint, 2026-08-24, trouve par
`theo-archi` en relisant sa propre ligne d'index d'`ADR-0061`). Trois defauts distincts, tous
reels, sur le meme paragraphe : un chiffre recopie en se compressant ("rejoue en une seule
fois" devenu "une seule edition"), une justification empruntee au motif d'un `ADR` voisin
dont l'objet n'etait pourtant pas le meme, et une mesure juste au moment de l'ecrire rendue
fausse par un mot au present ("aujourd'hui") a cote d'un SHA epingle - auto-refutee des que
le geste que l'`ADR` lui-meme habilitait a ete execute. Aucun des trois n'est une erreur de
mesure : ce sont trois facons de faire vieillir un chiffre juste sans jamais le remesurer
faux. Distinct des amendements de s06 (un chiffre porte sa methode) et de s17 (verifie contre
sa source primaire) : ici la methode et la source etaient justes, ce qui manquait est une
date portee par la phrase elle-meme plutot que par le contexte d'ecriture.

**Quand un lot durcit un lecteur partage a plusieurs modules ou plusieurs feuilles de style
(une methode `static` importee ailleurs, un lecteur de feuille de style, un port de deverminage
fixe), il est mesure sur l'union des arbres du sprint avant sa fusion, jamais sur son seul arbre**
(amendement s21, retro de s21, confirme par `theo-archi` a l'ouverture de s22 avec trois
precisions, `squad/sprints/s22-couverture-maximale-du-catalogue/avis-faisabilite.md` section 5).
Formule par lui-meme sur l'obstacle 13 (un correctif casse un troisieme appelant, visible
seulement une fois deux lots fusionnes, alors qu'aucun des deux arbres separes ne portait a la
fois le materiau et le lecteur strict). **Ce qu'est un lecteur partage, de facon derivable** : un
point de passage dont un autre fichier depend, lisible dans un diff. **Ce que la regle achete** :
du temps et du contexte - le defaut se trouve pendant que son auteur y est encore, pas a la
cloture quand le coupable n'est plus identifiable en un diff ; elle ne garantit aucune couverture
neuve, la CI sur l'arbre fusionne l'aurait trouve de toute facon. **Ce n'est pas une regle
`ArchUnit`** : elle porte sur quand on mesure, pas sur ce que le code contient.

**Une condition pedagogique qui filtre un contenu (une clause de la forme "exclure si...") est
mesuree contre la population entiere qu'elle gouverne au moment ou elle est ecrite, pas seulement
testee sur les tirages qu'elle rencontre au fil des paquets livres** (amendement s21, retro de
s21, adopte par `nadia-prof` a l'ouverture de s22, `squad/conformite/
PORTE-PEDAGOGIQUE-apres-arbitrage-du-2026-08-28.md` section 4). Fonde sur `SEG-1` : une clause
qu'elle avait ecrite avant que le paquet n'existe excluait a tort 30 mots sur 39 attrapes sur le
corpus CP entier, et n'a ete decouverte que par un arbitrage sur un seul mot d'un seul paquet -
une clause trop large qui n'attrape jamais un mot tire ne se revele jamais. **Bornee aux
populations enumerables et deja existantes** (un corpus de mots, un catalogue, un paquet livre) :
elle ne s'applique pas telle quelle a une condition qui gouverne un contenu pas encore ecrit, ou
il n'y a rien a balayer. Pour ce second cas, l'obligation de remplacement est que la condition
soit ecrite comme une propriete decidable item par item, jamais comme un exemple ni comme une
liste fermee, avec la population qu'elle gouverne nommee - et sa non-enumerabilite dite
explicitement quand c'est le cas.

**Une regle de conduite trouvee en cours de sprint (incident, defaut de methode, correction d'un
agent contre lui-meme) est ecrite dans un fichier du depot avant la cloture qui la constate, pas
seulement gardee en memoire d'agent** (amendement s21, retro de s21, tranche par `sofia-sm` a
l'ouverture de s22). Motif : un incident de conduite deja consigne une fois en memoire de session
et jamais ecrit dans un fichier du depot s'est reproduit, faute d'un garde-fou ecrit pour
n'importe quel agent. **Un troisieme cas, trouve le jour meme ou cet amendement s'ecrit, confirme
pourquoi** : l'ouverture de s22 demandait trois branches dediees sans demander trois worktrees
dedies, alors que la section 9 impose deja un worktree par agent depuis s06 - les lots de
`nadia-prof` ont atterri sur la branche de `camille-po` plutot que sur un worktree propre, parce
que la consigne nommait la branche et pas le worktree. Voir la precision ajoutee section 9.

**Une condition pedagogique de refus distingue, dans le chiffrage de capacite d'un sprint, un
cycle cause par le contenu livre d'un cycle cause par la specification prealable elle-meme, parce
que les deux n'ont pas le meme cout ni la meme correction** (amendement s21, retro de s21,
applique par `camille-po` dans son rechiffrage d'ouverture de s22, `DEC-0053` section 3, et
confirme par `sofia-sm` comme methode desormais requise a chaque rechiffrage de capacite). Sur les
quatre refus prononces depuis s20, trois ont leur cause racine dans une specification prealable
fausse de `nadia-prof`, un seul dans le contenu livre - distinction absente du chiffrage de
`DEC-0051`. Elle ne change pas mecaniquement une borne deja posee (un cycle cause par une
specification fausse coute quand meme un cycle), mais elle est desormais nommee a chaque
rechiffrage plutot que noyee dans un total unique.

## 5. La synthese de sprint - `RAPPORT.md`

C'est le document qu'on lit en premier pour savoir ce qui s'est passe. Ecrit par Sofia a la
cloture, il agrege :

- l'increment livre, en une phrase comprehensible sans contexte ;
- **la liste complete des decisions** : chaque `ADR` et chaque `DEC` cree ou modifie, avec
  numero, titre et statut ;
- l'etat des deux Done ;
- les obstacles resolus et ceux encore ouverts ;
- le tableau de consommation de tokens.

## 6. Le rapport de consommation de tokens

`outils/rapport-tokens.py` agrege la consommation **par agent et par sprint** a partir des
transcriptions que Claude Code ecrit sous
`~/.claude/projects/<chemin du depot encode>/`. Chaque sous-agent y laisse un fichier
`subagents/agent-<id>.jsonl` et un `agent-<id>.meta.json` qui porte son `agentType`.

Sofia le lance a chaque review :

```
python outils/rapport-tokens.py --sprint sNN
```

Si `python` ne resout pas un interpreteur 3.x utilisable sur le poste, appeler l'interpreteur par
son chemin complet - un chemin local ne s'ecrit jamais dans un fichier du depot.

Il ecrit `squad/sprints/sNN-<slug>/RAPPORT-tokens.md` et met a jour
`squad/rapports/JOURNAL-tokens.md`.

L'attribution a un sprint se fait par **fenetre temporelle** : l'intervalle entre l'horodatage de
debut du `planning.md` et l'horodatage de fin du `review.md`. Ce qui tombe hors de toute fenetre
est reporte sur une ligne `hors sprint`, jamais reparti arbitrairement.

Les quatre compteurs (`input`, `output`, `cache cree`, `cache lu`) sont toujours affiches
**separement**. Les additionner en un chiffre unique donnerait un classement faux : le cache lu
depasse couramment l'output de deux ordres de grandeur. Le rapport ne convertit rien en euros :
ce sont des compteurs de tokens.

**Reprendre la meme instance d'un agent est un choix, pas un defaut a corriger par une economie**
(amendement hors sprint, 2026-08-28, arbitrage de l'utilisatrice sur un diagnostic de `sofia-sm`).
Sur s21, `nadia-prof` porte 694 tours - le double de tout autre agent - 511 133 tokens d'output et
176 583 704 tokens de cache lu, contre 115 602 611 pour `theo-archi` et 83 525 725 pour `sofia-sm`
(`squad/sprints/s21-fermer-le-pli-avant-le-volume/RAPPORT-tokens.md`, fenetre du 2026-08-27 13:45
au 2026-08-28 10:30 UTC). Rapporte a ses tours, c'est de l'ordre de 254 000 tokens de contexte relu
a chaque tour (176 583 704 / 694).

Trois causes, dans l'ordre de leur poids - la premiere est la seule sur laquelle cette regle agit :

1. **La conduite d'orchestration de `sofia-sm`.** Sur s21, la meme instance de `nadia-prof` a ete
   reprise six fois par message plutot que relancee (arbitrage prealable, Done pedagogique,
   reprise apres l'arbitrage de l'utilisatrice, reaudition, correction du comptage des refus,
   decision typographique) : chaque reprise rejoue l'integralite du transcript, et son contexte
   cumule est passe d'environ 271 000 a environ 489 000 tokens au fil des six (releve fait par
   l'orchestratrice sur le fil de session de s21 le 2026-08-28 ; a la difference des chiffres du
   paragraphe precedent, ce compte n'est pas recalculable depuis un fichier commite du depot).
2. **La nature de son travail** : elle joue le produit, construit le jar, pilote un navigateur et
   mesure item par item, la ou la plupart des autres agents lisent des fichiers.
3. **L'elargissement reel de son perimetre pendant s21** : 478 items du catalogue joues entiers
   (`avis-nadia-done-pedagogique-s21.md`, mesure du 2026-08-27 a 744x551), le corpus CP entier de
   650 mots (`SEG-1`, `retro.md`), et 511 occurrences typographiques sur 17 fichiers (`TYP-1`,
   commit `a78882c`, mesure du 2026-08-28).

Ce n'est pas une regle d'economie : les reprises de s21 ont produit ce qui a le plus de valeur du
sprint - trois auto-contradictions de `nadia-prof` contre sa propre specification anterieure (la
barre de pli, `SEG-1`, le comptage des refus), qu'une instance neuve, sans le contexte de la
mesure qui les avait produites, n'aurait pas pu voir. Le critere qui suit distingue, il n'economise
pas :

- **Reprendre la meme instance** quand la question s'appuie sur la mesure ou le jugement anterieur
  du meme agent - reaudition apres correction, reprise de verdict, contradiction a instruire. Le
  contexte accumule est la valeur recherchee.
- **Lancer une instance neuve** quand la question repart d'un materiau que l'agent n'a jamais lu.
  Deux reprises a tort en s21 : la decision typographique `TYP-1` (l'agent repartait de zero sur un
  corpus de 511 occurrences jamais ouvert) et la correction du comptage des refus (un `grep` sur
  des fiches de conformite) - aucune des deux n'avait besoin de l'audit deja en contexte de
  l'instance reprise.

Cette distinction ne tient que parce qu'un agent qui **commite ses releves** peut les relire dans
une instance neuve, la ou un agent qui les garde seulement en memoire de session oblige a la
reprise : `nadia-prof`, `theo-archi` et `ines-design` commitent deja les leurs sous
`squad/conformite/releves-sNN/` - c'est ce qui rend cette regle applicable sans alourdir aucune
ceremonie.

## 7. Le double Done

**Done technique** : `mvn verify` vert - compilation, tests unitaires, tests d'integration Spring
Boot, ArchUnit, et integration H2 une fois `adapter-persistence` cree. C'est **la CI qui juge**,
pas un agent. Yanis et Theo en sont les garants : ils rendent le jugement fiable et le rapportent
honnetement.

**Done pedagogique** : prononce par **`nadia-prof`**, et par elle seule, avec un motif ecrit dans
`squad/conformite/FICHE-<jeu>.md`. Trois verdicts : `VALIDE`, `VALIDE SOUS RESERVE`, `REFUSE`.

Les deux sont independants. Un increment techniquement irreprochable peut etre refuse
pedagogiquement, et c'est un fonctionnement normal, pas un incident. Un refus renvoie l'increment
a Camille, qui en fait du backlog ; il ne bloque pas le sprint suivant.

**Gouvernance d'une story absorbee en cours de route (amendement s06, retro de s05).** Une story ou
une decision de perimetre qu'un sprint absorbe en cours de route depuis un autre sprint -
deplacement d'une story planifiee ailleurs, execution anticipee d'un chantier prevu au sprint
suivant - est actee par un amendement ecrit a la decision d'origine, dans le meme geste que sa
fusion sur `round-two`, jamais seulement constatee a la review du sprint qui suit. En s05,
`DEC-0012` placait explicitement le rhabillage du gabarit au sprint 6 ; il a ete livre et audite
dans s05 sans qu'aucun amendement ecrit ne le dise au moment des faits, et l'anomalie n'a ete
constatee qu'a la cloture du sprint suivant.

## 8. Prefixes de commit par agent

Format : `[TAG] <resume>`.

| Tag | Agent |
|---|---|
| `[CAM]` | Camille, Product Owner |
| `[SOF]` | Sofia, Scrum Master et dev |
| `[THEO]` | Theo, architecte |
| `[MARC]` | Marc, dev |
| `[LEA]` | Lea, dev |
| `[YAN]` | Yanis, QA |
| `[REL]` | Relecteur pedagogique |
| `[NAD]` | Nadia, professeure des ecoles |
| `[INES]` | Ines, designer d'interface |

Ca permet d'isoler la production d'un agent : `git log --grep='^\[NAD\]'`.

Ce n'est pas une convention declarative : la CI **rejette** un commit dont le message ne commence
pas par un tag connu (voir `.github/workflows/ci.yml`). Sur la version precedente, ce garde-fou
etait annonce dans la documentation et n'existait pas dans le workflow.

## 9. Derouler un sprint

**1. Planning.** Camille ecrit ou affine les stories et met a jour la matrice. Theo donne un avis
de faisabilite ecrit sur chacune et ouvre un `ADR` si la story en appelle un. Sofia gele
l'objectif, affecte nominativement, ecrit `planning.md` avec l'horodatage de debut. **Avant tout
commit dans un worktree neuf, chaque agent verifie que sa branche descend de `round-two`**
(amendement s06, retro de s05) : `outils/verifier-lignee.sh`, plutot qu'une commande recopiee en
prose dans chaque `planning.md`. Tant que ce script n'est pas encore sur le disque, la verification
reste tenue a la main (`git rev-parse --abbrev-ref HEAD` puis
`git merge-base --is-ancestor round-two HEAD`). **Tout agent qui ecrit dans le depot ouvre un
worktree dedie avant sa premiere ecriture, sans exception** (amendement s06, retro de s06) : y
compris un agent qui ne produit que des fichiers de decision et jamais de code. En s06,
`camille-po` a travaille directement dans le depot principal, sous une consigne de fusion ambigue
("ne pousse rien, ne fusionne pas") lue defendablement comme "ne commite pas" ; son travail est
reste non commite pendant que `sofia-sm` fusionnait en parallele sous sa casquette dev dans ce
meme depot. Rien n'a ete perdu uniquement parce que les deux perimetres ne se recouvraient pas -
un nettoyage d'arbre aurait efface son travail sans qu'aucun commit n'en garde trace. La consigne
de non-fusion reste valide pour tous les autres agents parce que chacun travaillait deja dans son
propre worktree ; `camille-po` etait seule a ne pas en avoir ouvert.

**Cette regle a ete relue et pourtant recontournee** (amendement s18, retro de s18). En s18,
`camille-po` a de nouveau edite `squad/programme/MATRICE-couverture.md` dans le depot principal au
lieu d'un worktree dedie pour son lot de cloture - un agent different de celui de s06 aurait pu
commettre la meme faute, la regle etant deja ecrite depuis douze sprints. Detecte par elle-meme
avant tout commit, corrige, rien perdu : la regle n'a pas failli, c'est le reflexe de l'ouvrir en
premier geste qui a failli deux fois sur dix-huit sprints, chez deux agents differents. Une prose
relue au planning ne suffit pas a elle seule a empecher la recurrence ; `sofia-sm` verifie
desormais explicitement, a chaque fusion, que le lot recu n'a jamais existe que dans un worktree
distinct du depot principal (deja sa pratique de fait depuis s14, faite regle explicite ici).

**Une branche dediee nommee pour un agent implique un worktree dedie du meme nom, que la consigne
qui l'ouvre nomme le worktree explicitement ou non** (amendement s21, retro de s21, tranche par
`sofia-sm` a l'ouverture de s22, section 4). A l'ouverture de s22, trois branches dediees ont ete
demandees sans que trois worktrees dedies ne soient demandes avec elles : les commits de
`nadia-prof` ont ete ecrits dans le depot principal pendant qu'il portait la branche de
`camille-po`, produisant deux commits ancetres reels d'une branche qui n'etait pas la sienne et un
troisieme sous un identifiant different sur sa propre branche - constate et verifie par
`sofia-sm` a la fusion, sans consequence de contenu grace a la lecture faite avant de fusionner.
**Nommer seulement une branche n'exempte personne de la regle deja ecrite ci-dessus** : la premiere
ecriture d'un lot ouvre son propre worktree, quel que soit le vocabulaire de la consigne qui l'a
demande.

**Un sprint peut etre pilote par des arbitrages successifs de l'autorite produit plutot que par un
objectif gele au planning, et le process le nomme plutot que de le taire** (amendement s15, cloture
de s15). Dans ce mode, l'utilisatrice joue l'increment livre, rend un arbitrage, et ce rendu
declenche immediatement un lot de developpement, sans story instruite au sens normal du terme ni
avis de faisabilite ecrit avant coup. Deux exigences minimales restent dues meme sous ce mode, pour
que la gouvernance par le depot ne se degrade pas : **chaque arbitrage recoit sa trace `DEC`
(creation ou amendement) dans le meme geste que la fusion du lot qui l'implemente**, jamais reportee
a la cloture du sprint - en s15, trois arbitrages sur cinq sont restes sans aucun fichier `DEC`
pendant toute leur duree de vie en production, decouvert seulement en regularisant le planning a la
fin ; et **`planning.md` nomme ce mode en clair** au lieu de fabriquer, avec le recul, un objectif
gele qui n'a jamais existe.

**2. Developpement.** Marc, Lea et Sofia codent, tests dans le meme commit. Yanis traduit les
criteres d'acceptation en tests et fait tourner la suite. Tout obstacle va a Sofia, qui classe,
route, attend la decision **ecrite**, debloque.

**Un obstacle route a plus d'un agent sur le meme perimetre est nomme comme tel au moment du
routage** (amendement s19, retro de s19). En s19, le meme defaut d'encodage a ete route a
`theo-archi` et a `yanis-qa` en parallele, chacun sans savoir que l'autre l'ecrivait ; les deux ont
produit un garde-fou concurrent sur la meme famille de defaut, dans le meme module, decouvert
seulement a la fusion. La confrontation des deux a trouve une faute reelle dans l'un d'eux, mais
c'est un benefice accidentel, pas une methode : Sofia dit desormais explicitement, en routant, que
le meme obstacle part vers plus d'un destinataire et sur quel perimetre chacun ecrit, pour que la
collision soit anticipee plutot que decouverte.

**Une phrase candidate a devenir du contenu servi a l'enfant est redigee integralement accentuee
des le premier brouillon**, meme dans un document de travail par ailleurs redige sans accent
(amendement s19, retro de s19). Trouve par le `relecteur-pedagogique` sur deux drafts opposes du
meme sprint : l'un accentuait deja ses phrases candidates en respectant `DEC-0002`, l'autre non.
Une phrase qui va devenir du contenu servi gagne a etre ecrite une seule fois sous sa forme finale
plutot que reecrite a la validation.

**3. Daily.** Sofia collecte et route. Pas de fichier.

**4. Porte technique.** `mvn verify` vert. Yanis produit le rapport qualite, Theo confirme
ArchUnit avec le resultat de la commande. **Un garde-fou cite comme fermant un refus pedagogique ou
une dette nommee n'est declare complet, dans le `review.md` qui le cite, qu'une fois la preuve de
morsure decrite explicitement** (amendement s06, retro de s05) - mutation reelle du code, relecture
adverse de l'inegalite qu'il verifie, ou tout autre moyen nomme. "Il est vert" ne suffit jamais
comme preuve de morsure, y compris quand l'auteur lui-meme le croit.

**Le rapport de cloture de Yanis inclut systematiquement un tirage d'un ou deux garde-fous
recemment ecrits ou modifies, mutes sur le materiau de production reel plutot que sur leur
fixture, avant chaque `review.md`** (amendement s10, cloture de s10) - et non seulement quand une
decouverte fortuite l'y pousse. En s10, quatre garde-fous casses puis restaures de cette facon se
sont reveles verts sans avoir jamais rencontre l'objet qu'ils pretendaient garder ; cette etape
systematique est la reponse a ce constat, pas une reaction ponctuelle a lui seul.

**Le compte de tests autoritaire, a chaque cloture, est la somme des blocs `Results:` du reacteur
Maven apres un `clean` complet - ou, de facon equivalente, la somme de l'attribut `tests="..."` de
chaque rapport XML de surefire/failsafe - jamais la somme des lignes `Tests run:` des rapports
`.txt`** (amendement s11, cloture de s11). Etabli par `yanis-qa` : quatre classes de ce depot
melangent, dans une meme classe, un champ statique `@ArchTest ArchRule` (execute par le moteur
propre a `archunit-junit5`) et une methode `@Test` ordinaire (executee par Jupiter) ; le rapport
`.txt` de la classe n'ecrit alors que le compte du moteur Jupiter, sous-comptant de 7 sur ce
depot en s11. Le rapport XML, lui, agrege correctement les deux moteurs. Ce n'est pas une
parametrisation JUnit5 qui cause l'ecart, contrairement a ce qui avait ete suppose avant
verification.

**A chaque `critere enumere` auto-declare (au sens d'`ADR-0013`), le rapport de cloture de Yanis
verifie si au moins un membre de la liste est le nom d'un incident date et clos plutot qu'une
categorie qui generalise, et si oui, mute un objet neuf, absent de la liste, sur du materiau
reel, pour voir si le garde-fou peut seulement dire quelque chose de lui** (amendement s11,
cloture de s11). Trouve par `yanis-qa` en cherchant l'auto-declaration exigee par `ADR-0013` dans
tout le depot (quatre classes la portent) : `RessourcesTiercesTest` s'est revelee construite sur
une liste de noms d'incidents ("bootstrap") plutot que sur une categorie, et un fichier
JavaScript tiers reel, correctement trace sous un nom absent de la liste, est entre dans le depot
sans faire rougir aucun de ses seize tests. Le grep de l'auto-declaration trouve mecaniquement
tous les candidats ; juger si un membre de la liste generalise ou epingle un incident clos reste
une lecture, jamais un motif syntaxique.

**Un script livre avec sa propre suite de tests n'est declare "teste" qu'une fois qu'une etape de
`.github/workflows/ci.yml` l'execute reellement** (amendement s06, retro de s06) - la presence du
fichier `*-test.sh` dans le depot ne suffit pas. `outils/verifier-lignee-test.sh` et
`outils/verifier-jdk-test.sh` ont ete livres en s06 comme des garde-fous testes selon la doctrine
d'`ADR-0009`, et aucun automate ne les executait : seule la main d'un agent le faisait. Trouve par
`theo-archi` en ecrivant le sien (`outils/mesurer-rendu-test.sh`), qui a ajoute l'etape de CI
manquante, perimetre derive du repertoire `outils/*-test.sh` plutot qu'une liste de trois noms.

**A chaque verdict qui s'appuie sur un releve automatique cite comme preuve, le rapport de
cloture de Yanis nomme explicitement la propriete que ce releve peut structurellement voir et
celle qu'il ne peut pas, avant de le citer** (amendement s12, cloture de s12). Propose par
`yanis-qa`, sur le meme modele que son amendement de s11 pour le critere enumere. En s12, une
seconde instance de la meme famille - une mesure creditee pour une propriete qu'elle ne peut pas
voir - s'ajoute a celle deja connue depuis s0 (la position a l'ecran, creditee pour le rang par
valeur) : la clause 4 d'`ADR-0040` prouve qu'un point de focus est **declare** dans le document,
pas qu'il est **atteint** ni **visible** pour l'enfant. Contrairement au critere enumere,
`ADR-0013` n'impose ici aucun marqueur obligatoire equivalent a une auto-declaration : `yanis-qa`
ne peut donc pas enumerer mecaniquement les candidats, et ecrit lui-meme que sa recherche est une
lecture ciblee, pas une preuve d'exhaustivite.

**Un garde-fou qui tient uniquement par la divergence fortuite de deux fixtures ne tient pas**
(amendement s16, cloture de s16). Trouve par `yanis-qa` : `EtatDePartieEnSession.dureeInactiviteMaximale()`
n'est protegee contre une valeur recopiee dans le test qui la verifie que par le fait que deux
tests distincts (`PartieControllerTest`, `EtatDePartieEnSessionTest`) utilisent par hasard deux
duree differentes (1800 et 900) - jamais par une propriete de la regle elle-meme. Si l'un des deux
tests disparaissait ou changeait sa valeur pour coincider avec l'autre, une regression vers une
constante recopiee passerait inapercue sans qu'aucun test ne rougisse. Un garde-fou dont
l'invariant repose sur la non-coincidence accidentelle de deux fixtures ecrites separement n'est
pas un garde-fou tant qu'un test dedie ne fait pas varier explicitement la valeur pour prouver que
la regle la lit et ne la recopie pas.

**A chaque lot qui ajoute du materiau qu'un garde-fou existant est cense couvrir (contenu, entree
de catalogue, fichier neuf), le rapport de cloture de Yanis verifie si le compte de ce garde-fou a
bouge ; s'il ne bouge pas, ce n'est pas une bonne nouvelle par defaut et appelle une reponse ecrite
disant si le garde-fou a vu le materiau neuf ou non** (amendement s17, cloture de s17). Propose par
`theo-archi`, qui a trouve deux defauts sur trois de cette facon en un seul sprint (le garde-fou de
rang vide sur `cm1-synonymes`, la clause d'`ADR-0052` bornee a un jeu fictif alors qu'une entree
reelle venait d'arriver) sans ecrire une ligne de code. Meme famille que les amendements de s11
(critere enumere) et s12 (releve automatique cite en preuve) : un signal syntaxique mecanique (le
compte ne bouge pas) qui declenche une lecture, jamais une preuve d'exhaustivite en lui-meme.
`theo-archi` decline explicitement d'encoder un garde-fou automatique unique pour la famille
sous-jacente (rang, perimetre de derivation, population de test) : le manque n'est pas au meme
endroit dans les trois cas rencontres ce sprint, un automate unique aurait ete soit incomplet, soit
trop large.

**Un garde-fou deja accepte, avec sa propre preuve de morsure ecrite, n'a ete mis a l'epreuve que
par une tentative reelle de le defaire, jamais par une relecture de son code ou de son test**
(amendement s18, retro de s18). Trois defauts trouves le meme sprint, tous dans des regles deja
en place (`ADR-0054`, `ADR-0055`, le mecanisme initial de `S17-C`) : un seuil de largeur maison
evitable en nichant la declaration sous une regle `@media`, que le balayage ne parcourait pas ; une
clause textuelle sensible a la casse que l'orthographe la plus idiomatique de la faute contournait ;
et le retrait des trois attributs de gabarit qui defaisait le mecanisme de focus entier sans faire
rougir un seul test. Les trois ont ete trouves en appliquant reellement une variante ou un correctif
candidat concret puis en mesurant le resultat - jamais en relisant le texte d'une regle deja verte.
Une regle qu'un jeu fautif commis a l'avance rend verte n'a ete mise a l'epreuve que du cas que son
auteur avait imagine ; elle reste non mise a l'epreuve du premier cas reel qui la rencontre. **A
chaque candidat de mise en page ou de mecanisme propose par `ines-design` ou par un dev, le premier
geste avant de le retenir est de le faire passer reellement devant les garde-fous existants**, pas
de raisonner sur sa conformite.

**Une mesure de reference qui sert de plancher ou de plafond a une decision doit nommer, a cote de
sa valeur, l'hypothese de contexte sous laquelle elle a ete prise** (amendement s18, retro de s18).
L'inventaire d'appareils d'`ines-design` etablissait le plancher "ordinateur" a 1280 px sous une
methode de mesure "zoom a 100 %, fenetre maximisee" - raisonnable pour fixer une reference d'ecran,
mais jamais ecrite comme une hypothese qui bornait aussi tout usage futur de ce chiffre. Elle n'a
rencontre sa premiere consequence concrete qu'un sprint plus tard (`SYN-16`, `camille-po`) : une
fenetre non maximisee ou partagee produit une largeur dans une bande que l'inventaire ne couvrait
pas, sans que quiconque ait pu le voir en le relisant. Une valeur de reference sans l'hypothese qui
la borne est une limite implicite que quelqu'un redecouvre plus tard sans methode ecrite.

**Une ceremonie de relecture ou d'audit n'est lancee que sur un arbre qui porte deja tout ce
qu'elle doit lire** (amendement s19, retro de s19). En s19, Sofia a lance le
`relecteur-pedagogique` sur un `round-two` qui ne portait pas encore l'audit pedagogique en cours
de `nadia-prof` : il a remis a jour des fiches de conformite perimees pendant qu'elle y ecrivait
ses verdicts, ce qui a produit une collision de fusion reelle. Meme famille que l'amendement de s06
sur le worktree dedie (section 9, etape 1) : la lignee d'un arbre se verifie avant de lancer un
agent dessus, pas seulement avant de fusionner son lot.

**5. Relecture.** Le `relecteur-pedagogique` passe sur les livrables non executables. Il corrige
ce qui est sans ambiguite, signale le reste.

**6. Porte pedagogique.** Nadia joue l'increment, l'audite contre le referentiel, ecrit son
verdict dans la fiche de conformite. Si c'est `VALIDE`, Camille passe la ligne de matrice a
`couvert-valide`.

**7. Review.** Sofia ecrit `review.md` : horodatage de fin, duree, rapport de Yanis, confirmation
de Theo, corrections du relecteur, tableau des obstacles, verdict de Nadia.

**A la review, Sofia verifie physiquement chaque porte de sortie nommee au planning, pas
seulement les fichiers de ceremonie** (amendement s13, cloture de s13). Une porte de sortie qui
cite une action precise - "reverifie par Nadia sur `FICHE-<jeu>.md`", "jugee par Nadia" - est un
engagement au meme titre qu'un fichier de ceremonie : elle ne compte comme tenue que si l'action
existe physiquement sur le disque a la date de la review, jamais parce qu'elle a ete ecrite comme
attendue au planning. En s13, deux portes de sortie ecrites au planning (S13-4/S13-5, S12-5) n'ont
jamais ete franchies alors que le code correspondant etait fusionne et vert, et rien ni personne ne
l'avait signale avant que Sofia ne verifie chaque porte ligne par ligne a la cloture.

**Une condition declaree "tenue par construction" n'est pas une condition tenue tant qu'elle n'a
pas ete verifiee sur le document assemble** (amendement s14, cloture de s14). En s14, `marc-dev` a
declare deux conditions bloquantes "tenues par construction" en raisonnant sur le fragment qu'il
ecrivait ; l'en-tete permanent, lui, survivait a l'assemblage complet du document et les puces de
niveau avec lui, ce que `nadia-prof` a mesure et refuse. C'est la quatrieme instance nommee de la
meme famille qu'`ADR-0020` porte deja pour l'architecture ("l'unite de verification d'un ecran est
le document assemble") et que l'amendement de s12 porte deja pour les releves automatiques cites
en preuve : ce qui manquait est la meme regle pour une affirmation de raisonnement humaine, non
instrumentee. Toute affirmation "tenu par construction" citee dans une revue de code ou un accord
d'architecte est verifiee sur le document reellement rendu avant d'entrer dans un `review.md`, pas
seulement raisonnee sur le fragment source.

**Un engagement conditionnel ecrit dans un `ADR` ou une `DEC` ("je changerai ce statut ou cette
clause si X se realise") porte, au moment ou il est ecrit, qui verifie sa realisation et quand -
jamais suppose que son auteur le fera de lui-meme** (amendement s17, cloture de s17). Trois
instances de la meme famille en deux sprints (`ADR-0042`, `ADR-0047`, `ADR-0048`), chacune fermee
seulement parce qu'un tiers l'a remarquee plutot que par son propre auteur. La retro de s16 avait
juge la regle de s13 (verification physique de chaque porte de sortie) suffisante par une lecture
large ; deux instances de plus depuis montrent que cette lecture large ne s'est pas produite. La
regle de s13 porte sur les portes de sortie d'un tableau de stories, pas sur un engagement
conditionnel ecrit ailleurs dans un document de decision : la regle qui manquait est distincte, pas
une extension de la regle existante.

**8. Cloture.** Sofia lance le rapport de tokens et ecrit `RAPPORT.md`.

**9. Retro.** Sofia anime, ecrit `retro.md`, et amende ce README si le process doit evoluer.

**Outils permanents (amendement s06, retro de s05).** Deux scripts testes selon la doctrine
d'`ADR-0009` outillent les etapes 1 et 4 ci-dessus :

- `outils/verifier-lignee.sh` (etape 1) : verifie qu'un worktree descend de `round-two`, code de
  sortie non nul et message explicite nommant la branche fautive sinon, sans jamais imprimer de
  chemin absolu de poste.
- `outils/verifier-jdk.sh` (etape 4) : separe un diagnostic libre (sortie d'erreur, peut nommer un
  chemin de poste) d'un constat citable (sortie standard, jamais un chemin) sur le JDK utilise pour
  compiler. C'est ce second bloc qui se colle tel quel dans `rapport-qualite.md` ou
  `confirmation-archunit.md`, a la place d'une description en prose de la verification.

Ce paragraphe acte la regle ; ni l'un ni l'autre script n'existe encore sur le disque a l'ouverture
du sprint 6, les stories qui les livrent le font. Jusqu'a leur fusion, les etapes 1 et 4 restent
tenues par la commande recopiee a la main et par la prose du rapport qualite. Le besoin qui motive
`outils/verifier-jdk.sh` est reel et pas seulement une question de forme : verifie le 2026-08-07, un
JDK 21.0.10 est installe et compile sur un poste ou le fichier de toolchains Maven declare bien
trois JDK dont un 21 sous un nom que Maven ne lit pas - quatre agents avaient conclu a tort en s05
qu'aucun JDK 21 n'y etait disponible, une erreur de fond et non seulement de methode.

**Une regle de conduite trouvee en cours de sprint est ecrite dans un fichier du depot avant la
cloture qui la constate, pas seulement gardee en memoire d'agent** (amendement hors sprint,
2026-08-28, arbitrage de l'utilisatrice sur une proposition de `sofia-sm` a la retro de s21).
`sofia-sm` avait nomme cette regle et refuse de la trancher elle-meme ("a trancher par moi-meme...
je le propose ici plutot que de l'acter seule dans ce meme document, pour qu'il soit visible avant
d'etre applique", `retro.md` de s21) : ce n'est donc pas elle qui l'acte ici, c'est l'utilisatrice
- exactement le geste que la regle protege.

Motif, avec son cout reel : le 2026-08-27, dans son lot dev `S21-11`, `sofia-sm` a lance
`taskkill //IM chrome.exe //F` pour arreter un navigateur de mesure - une commande qui tue par nom
d'image et non par identifiant de processus (PID). Ce n'etait pas la premiere fois : le meme geste
avait deja ferme des navigateurs qui n'etaient pas les siens - les fenetres personnelles de
l'utilisatrice - et la regle qui l'interdit existait deja, mais seulement dans une memoire de
session, jamais ecrite dans un fichier du depot. C'est pour cela qu'elle s'est repetee : une regle
de conduite qui ne vit pas dans le depot ne vaut pour aucun agent, quelle que soit la discipline de
celui qui l'a apprise une fois.

La regle generale, au-dela de cet incident : toute regle de conduite trouvee en cours de sprint -
incident, defaut de methode, correction d'un agent contre lui-meme - est ecrite dans un fichier du
depot des qu'elle est trouvee, ou au plus tard proposee a la retro avec le nom de qui doit la
trancher, jamais laissee vivre seulement dans la memoire d'un agent jusqu'a la prochaine ceremonie.

Son instance est ecrite dans le meme geste : **un processus s'arrete par son identifiant de
processus (PID), jamais par nom d'image, et son identite est verifiee avant l'arret.**

## 10. Frontieres entre agents

- Camille ne decide pas la technique. Theo ne decide pas le produit ni la pedagogie.
- Marc, Lea et Sofia n'inventent aucune regle d'architecture : ils remontent a Sofia, qui route
  vers Theo, et ils attendent la decision ecrite.
- Yanis n'assouplit jamais le Done technique pour tenir un calendrier.
- Le relecteur ne modifie pas la logique du code ; il ne juge pas ce que lit un enfant.
- Ines propose le design d'interface et l'accessibilite visuelle ; elle ne tranche ni
  l'architecture ni le produit, ne juge pas le niveau scolaire d'un contenu, et ne reecrit pas la
  logique metier. Son design ne se valide jamais contre l'avis de Nadia.
- **Aucun agent, hors Nadia, ne prononce le Done pedagogique.**
- **Toute story qui modifierait un ecran, un texte ou un comportement deja accorde par Nadia est
  signalee pour un arbitrage prealable avant developpement, pas seulement auditee apres coup**
  (amendement s06, retro de s06). En s06, cinq stories ou constats auraient defait un acquis sans
  que personne ne l'ait relie a l'avance (S6-9 contre l'etat `sortie-pleine` de S3-6, S6-10 contre
  le fondu du favicon valide en s05, S6-14 contre `ADR-0010`, un epinglage de Theo devenu sans
  objet, les exemples de Nadia elle-meme contredits par son propre motif de refus) : aucune
  n'aurait ete vue par une CI verte. Deux arbitrages prealables (S6-9, S6-10) ont fonctionne
  exactement comme prevu ce sprint, avant que le code ne soit ecrit ; cette regle generalise la
  pratique plutot que de la laisser dependre de la memoire de qui route l'obstacle.

## 11. Arborescence

```
squad/
  README.md               ce fichier - le process
  EQUIPE.md               les 8 agents, miroir humain de .claude/agents/
  TEMPLATES.md            gabarits vierges de tous les artefacts
  sprints/
    sNN-<slug>/
      planning.md         sofia-sm
      review.md           sofia-sm
      retro.md            sofia-sm
      RAPPORT.md          sofia-sm
      RAPPORT-tokens.md   genere
  decisions/
    adr/                  theo-archi + ADR-index.md
    produit/              camille-po + DEC-index.md
  conformite/             FICHE-<jeu>.md - camille-po ouvre, nadia-prof valide
  programme/
    MATRICE-couverture.md camille-po
    mathematiques/        referentiels repris de la version precedente
    francais/             referentiels repris de la version precedente
  rapports/
    JOURNAL-tokens.md     cumul de la consommation, tous sprints
```
