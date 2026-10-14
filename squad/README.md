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

**Exception nommee, amendement s22 (retro de s22) : une ceremonie pas encore close peut recevoir
un ajout date.** La regle ci-dessus interdit de fabriquer une ceremonie retrospective une fois
close - pas de completer une ceremonie encore ouverte par un ajout qui en fait reellement partie.
`review.md` de s22 a recu cinq ajouts dates (`nadia-prof` deux fois, `sofia-sm` trois fois) pendant
qu'un cycle `REFUSE` -> correction -> reaudit restait en cours dans le meme sprint : chaque ajout
etait exact a sa date, aucun n'a efface ni modifie ce qui le precedait, et le fichier le dit
explicitement a chaque fois. Cet usage est permis **seulement** si les quatre conditions suivantes
sont toutes vraies : (1) la ceremonie n'est pas encore close - au moins une porte obligatoire
(technique ou pedagogique) reste non tenue au moment de l'ajout ; (2) l'ajout porte sa date et son
auteur en premiere ligne ; (3) aucun mot du texte precedent n'est modifie ni retire ; (4) l'ajout
qui prononce la cloture le dit en toutes lettres. Hors de ces quatre conditions, la regle
d'origine s'applique sans exception : un fichier de ceremonie close ne recoit plus rien, et une
ceremonie qui n'a pas eu lieu n'en fabrique pas un.

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

## 2 bis. Attribuer un enonce - les trois formes, et MASTER

**Amendement du 2026-08-31 (s24), du par `DEC-0062` a partir de son statut `Acceptee`.** La
convention y est decidee ; ce qui suit la transcrit pour qu'un agent la trouve sans avoir a lire la
`DEC`. En cas d'ecart, `DEC-0062` fait foi.

Tout chiffre et toute affirmation de fait ecrits dans ce depot portent leur provenance sous **l'une
des trois formes**, et il n'en existe pas de quatrieme.

| Forme | Quand | Ce qu'elle exige |
|---|---|---|
| **1 - un agent de la squad** | le fait est ecrit dans un fichier du depot | le nom de l'agent, **plus le fichier et la section ou il l'a ecrit**. Un renvoi qui ne pointe vers rien est de la meme famille de defaut qu'un chiffre sans methode |
| **2 - l'utilisatrice** | l'enonce vient d'elle | la mention **`de l'utilisatrice`**, **ses mots cites entre guillemets**, et la date |
| **3 - un enonce recu hors des neuf agents** | il vient du canal humain qui pilote la squad | la formule fixe **`recu de MASTER, sans source dans le depot`**, l'enonce **cite mot pour mot et jamais resume**, et la date de reception |

**Correction du 2026-09-01 (`sofia-sm`), et elle porte sur ma propre transcription.** Cette table
decrivait en forme 2 *"une mesure faite par l'auteur - la commande, son perimetre nomme, sa date"*,
la ou `DEC-0062` section 3.1 ecrit **`de l'utilisatrice`, ses mots cites, la date**. **Effet : un
arbitrage de l'utilisatrice n'avait plus de forme du tout** - c'est la moitie du trou que `DEC-0062`
existe pour fermer, rouverte dans le fichier cense la diffuser. Trouve par `relecteur-pedagogique`
**en verifiant la charte qu'il applique**, et corrige ici parce que ce fichier est a moi. *L'exigence
que j'y avais mise n'est pas fausse - elle est ailleurs : un chiffre porte sa methode et son
perimetre, section 4, et toute mesure partielle du reacteur porte `-am`. Ce n'est pas une forme
d'attribution, et la loger ici en chassait une.*

**Les deux bornes de la forme 3, et elles sont le prix de son existence :**

- **Un enonce recu de MASTER ne vaut jamais motif.** Il ne fonde aucune `DEC`, aucun `ADR`, aucun
  critere d'acceptation, aucun verdict. **Une consigne recue par ce canal et qui doit fonder quelque
  chose devra d'abord etre ecrite quelque part.** C'est contraignant pour MASTER autant que pour la
  squad, et cela a ete accepte en le sachant.
- **Il est cite mot pour mot.** Un enonce que son destinataire ne peut pas citer exactement se
  signale comme tel plutot que de se reconstituer : **choisir entre deux formulations reviendrait a
  fabriquer la citation que la convention protege.**

**La formule tolere l'accord au pluriel** (amendement du 2026-10-13, `sofia-sm`, preparation de s57).
*"recus de MASTER, sans source dans le depot"* pour plusieurs enonces cites mot pour mot avec leur date
est la formule fixe accordee, pas une quatrieme forme. Source : `DEC-0062`, amendement D
(`camille-po`, 2026-10-13, sur les mots de l'utilisatrice *"oui pour Sofia"*), et `DEC-0116` A.35. La
lecture est celle de `retro.md` de s56, section 4 (`relecture-cloture.md` P1 : un ecart de formule, pas
de provenance). **Aucune convention structurelle n'est introduite** : `Q-U7`, point 2, est sans reponse
de l'utilisatrice, et le refus de test de `theo-archi` tient (`avis-faisabilite.md` de s56, section 8 :
47 fichiers sur 65 rouges). Pas de balayage retroactif (`DEC-0062` section 7) : `DEC-0114` n'est pas
touchee.

**MASTER s'ecrit toujours en capitales** et n'apparait jamais seul, mais dans la formule fixe -
c'est ce qui le distingue a la lecture de *Scrum Master*. **MASTER n'ecrit jamais dans le depot** et
n'entre pas dans la table des prefixes de commit de la section 8 : un commit prefixe `[MASTER]` est
rejete par la CI, et c'est le comportement correct.

**Ce que la forme 3 sert a empecher, en un exemple reel** : a s24, deux enonces recus par ce canal -
*"le lot 3 n'existe pas"* et *"donc le chemin C est ecarte"* - ont circule comme vrais sans avoir ete
mesures. Le second serait entre dans la porte pedagogique et y aurait referme 27 jeux sur un lot que
personne n'avait regarde. **Il a ete arrete parce que sa destinataire est allee verifier** au lieu de
s'y appuyer, la borne ayant ete acceptee le matin meme.

**Qui l'applique** : chaque agent a l'ecriture ; le `relecteur-pedagogique` la verifie sur les
livrables non executables, avec le critere binaire de `DEC-0062` section 4.

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
| Planning | `planning.md` | Horodatage de debut, objectif gele, stories avec competence visee, affectations nominatives, avis de faisabilite de Theo, verification poste/story (`PAR-16`, `DEC-0086` section 5) |
| Daily | **aucun** | Synchronisation de travail. Sofia collecte, classe et route ; elle n'ecrit pas de fichier |
| Review | `review.md` | Horodatage de fin, duree, rapport qualite de Yanis, confirmation ArchUnit de Theo, corrections du relecteur, tableau des obstacles, avis de Nadia et Done pedagogique |
| Retro | `retro.md` | Ce qui a marche, ce qui a coince, amendements a ce README |
| Cloture de palier (sprint a paliers seulement) | `cloture-palier-N.md` | Horodatage de fin du palier, verdict ecrit de Nadia ou report ecrit pour chaque poste engage, CI verte avec les jobs nommes sur l'arbre fusionne, total de tests recompte sur cet arbre, comptage du catalogue a chaque fusion, glissements, obstacles du palier, section `## Decisions prises` |

**Un sprint a paliers a une seule review et une seule retro, plus un fichier de cloture par palier**
(amendement du 2026-10-14, `sofia-sm`, planning de s57, `Q-S1`). Un palier est la fenetre d'audit de
Nadia qui tient seule les plafonds `PAR-8` et `PAR-9`, close pour toutes ses instances avant
l'ouverture de la suivante (`ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`, 11.3). Le
sprint garde un seul `planning.md`, un seul `review.md` et un seul `retro.md`, ecrits a l'ouverture et
a la cloture du sprint ; chaque palier clos ajoute un `cloture-palier-N.md`, ecrit une seule fois par
Sofia. Il n'est ni une review, ni une retro, ni un `RAPPORT.md` : le rapport de tokens reste un seul
par sprint (section 6), sur la fenetre qui va de l'horodatage de `planning.md` a celui de
`review.md`. Les obstacles d'un palier vont dans son fichier de cloture, puis dans le tableau de
`review.md` ; un palier suivant s'ouvre par un amendement date de `planning.md`, jamais par un fichier
de ceremonie reecrit. Un sprint sans palier n'a pas ce fichier. Motif : sans lui, un sprint long perd la
review et la retro entre deux fenetres d'audit, et la condition 3 de Nadia ne remplace que sa part de
la cloture. Mots de l'utilisatrice, 2026-10-14, forme 2 (relayes, non lus de sa main) : `Q-S1` = ok.

**Pourquoi le daily n'est pas journalise.** Sur la version precedente de `recreo`, le daily etait
une ceremonie documentee dont **aucun fichier n'a jamais ete ecrit en 25 sprints**. Plutot que de
maintenir une regle que personne ne suit, on acte la pratique reelle.

Consequence a tenir : le **tableau des obstacles** ne disparait pas avec le daily. Il remonte
dans `review.md` et dans `RAPPORT.md`. Sans lui, la chaine "obstacle route vers un agent,
decision ecrite, obstacle clos" serait perdue.

Regle de cloture : un fichier de ceremonie n'est complet que s'il contient une section
`## Decisions prises` **non vide**, ou l'enonce explicite qu'aucune decision n'a ete prise.

**Le point de controle poste/story (`PAR-16`, amendement s34, cloture du planning de s34,
`DEC-0086` section 5).** A la cloture de `planning.md`, Sofia verifie, poste par poste, la table de
composition de la decision de strategie en vigueur : chaque poste porte-t-il une story dans
`docs/backlog/backlog.md`, et cette story porte-t-elle une affectation nominative (instance,
developpeur) ? Un poste qui n'a ni l'un ni l'autre est ecrit explicitement sous l'une de deux
issues, jamais une troisieme silencieuse : soit une story lui est ecrite et affectee avant la
cloture du planning, soit il est ecrit **"non engage, budget libere"**, avec le montant libere et ou
il va. Fonde sur un trou mesure a s33 : trois postes finances par la composition (`cm1-anglais`, la
passe transverse, le lot de l'effort et du corps) avaient ete nommes comme risque des le planning
(section 10) sans qu'aucune story ne les rende engageables a temps, et deux postes de plus
(`cp-emc-emotions-autrui`, `cp-emc-formules-de-politesse`) n'avaient meme pas ete detectes comme
risque a ce moment-la. Nommer un poste sans story ne suffit pas si aucun controle ne revient
verifier, avant l'execution du sprint, qu'il a recu l'un ou l'autre. Un chiffre alloue a un poste
sans story n'achete rien et empeche d'acheter autre chose.

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

**Quand un commit paye plusieurs stories qui touchent le meme paquet de contenu, le paquet
resultant est relu comme un tout - la consigne contre l'ensemble des taches reellement servies -
avant de commiter, jamais story par story seulement** (amendement s33, retro de s33). Fonde sur
`S33-EXC-R1`/`S33-EXC-R5` : la premiere reecrit la consigne de `bandit-manchot-exclusion-cp` pour
nommer une tache ("savoir a quel adulte en parler") que la seconde retire du meme paquet, dans le
meme commit - chaque story respectait individuellement son propre critere d'acceptation, mais le
paquet assemble se contredisait avec lui-meme des le premier jour ou il a ete servi. Trouve par
`nadia-prof` en jouant, jamais par un garde-fou : `PaquetCpEmcExclusionTest` lit la tache d'un item
dans sa bonne reponse (`ADR-0089`), jamais dans l'enonce ni dans la consigne, et ne pouvait donc
pas voir la contradiction.

**Une mutation n'est lue qu'apres deux controles : le motif a ete trouve, et le nombre de tests lances
est superieur a zero** (amendement du 2026-10-12, `sofia-sm`, retro de s56, section 6, proposition
confiee a `yanis-qa` et `sofia-sm`). C'est la regle que `yanis-qa` ecrit dans sa methode, etendue a tout
agent qui mute, developpeurs compris. Fait mesure : trois faux verts de mutation en un sprint, chacun vu
par son auteur et aucun par un garde-fou - `bash` de WSL qui n'executait aucun test (`yanis-qa`, serie
entiere jetee), espace insecable non reconnue par le script de mutation (`marc-dev`, deux fois, 22 verts
puis 18) (`rapport-qualite.md` section 3 et `rapport-marc-dev.md` sections 3 et 16 de s56). Un script qui
applique une mutation sans refuser un motif absent produit un vert qui ne dit rien. La regle est aussi
dans `squad/lessons.md`.

**Une condition d'un arbitrage prealable dite "tenue" cite la cote mesuree sur le jar qui sert, pas sur
son vehicule** (amendement du 2026-10-12, `sofia-sm`, retro de s56, section 6, proposition confiee a
`nadia-prof` et `sofia-sm`). Meme famille que l'amendement de s14 (*"tenu par construction"* verifie sur
le document assemble, section 9, etape 7), pour un arbitrage prealable de la porte pedagogique. Fait
mesure : s56, *"Mesure : tenu avec O1 et le meuble"* ; l'entree de catalogue a ete ecrite contre cette
barre, la phrase de `M4` est a 626 a 656 pour un pli a 551 (`getBoundingClientRect()` a 744 x 551), 105 px
d'ecart, et le jeu a ete refuse (`FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md`, motif du refus).

**Pour tout chantier hors sprint, l'audit de `nadia-prof` precede la fusion sur `round-two`**
(amendement du 2026-10-12, `sofia-sm`, regle decidee par `DEC-0117` section 11 et demandee a
`sofia-sm` par `camille-po` dans le journal hors sprint, `squad/rapports/JOURNAL-gouvernance-hors-sprint.md`).
Mots de l'utilisatrice cites par `DEC-0117` section 11, recus par `camille-po` le 2026-10-12 : *"non
nadia passe avant la fusion comme ca on peut re corrgier si besoin"*. Un `REFUSE` se corrige donc avant
fusion, par une story corrective, et ne laisse rien en service. Le texte d'application est en section 9,
etape 6. En cas d'ecart, `DEC-0117` section 11 fait foi.

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

**Done technique** : CI verte, c'est-a-dire tous les jobs de `.github/workflows/ci.yml`
(`prefixes`, `chemins`, `outils`, `build` - ce dernier seul porte `mvn verify` : compilation,
tests unitaires, tests d'integration Spring Boot, ArchUnit). C'est **la CI qui juge**, pas un
agent. Yanis et Theo en sont les garants : ils rendent le jugement fiable et le rapportent
honnetement.

**Done pedagogique** : prononce par **`nadia-prof`**, et par elle seule, avec un motif ecrit dans
`squad/conformite/FICHE-<jeu>.md`. Trois verdicts : `VALIDE`, `VALIDE SOUS RESERVE`, `REFUSE`.

Les deux sont independants. Un increment techniquement irreprochable peut etre refuse
pedagogiquement, et c'est un fonctionnement normal, pas un incident. Un refus renvoie l'increment
a Camille, qui en fait du backlog ; il ne bloque pas le sprint suivant.

**Le perimetre de la porte pedagogique, borne le 2026-08-31 (amendement s24).** Le paragraphe
ci-dessus disait **qui** prononce et **avec quoi**, jamais **sur quoi** : il decrivait une porte sans
borne de perimetre. Elle en a une depuis le 2026-08-31, posee par deux sources qui existent sur le
disque. **Cet amendement les transcrit et n'en interprete aucune** ; en cas d'ecart, ce sont elles qui
font foi et c'est ce paragraphe-ci qui a derive.

- **`DEC-0060`** (acceptee, sur arbitrage de l'utilisatrice) : la porte pedagogique **porte sur les
  elements neufs ou modifies**, et *modifie* se lit sur **tout changement du code qui produit l'ecran
  de l'enfant** - l'effet de ce changement n'entre pas dans la qualification.
- **`squad/conformite/CLAUSE-PORTE-PEDAGOGIQUE-perimetre-apres-les-six-reponses-du-2026-08-31.md`**,
  redigee par `nadia-prof` dans ses propres mots parce que la porte est a elle : neuf clauses `C1` a
  `C9`, et **trois regimes d'audit** qui disent ce qu'une modification appelle - `R-A` audit de
  paquet (compte pour un paquet contre sa borne), `R-B` audit de mecanique (hors compte),
  `R-C` changement de code a rendu invariant (aucun rejeu, **sous condition d'une mesure qui remplit
  ses quatre exigences**, et sans laquelle rien n'est discharge).

**Ce que cette borne ne change pas** : `nadia-prof` reste **seule** a prononcer un Done pedagogique,
avec un motif ecrit ; les trois verdicts sont inchanges ; et un element qu'elle ne rejoue pas n'est
jamais exempte par le silence - `C8` lui impose de nommer, a chaque verdict, les proprietes dont elle
reste la seule garde.

**Ce que cette borne coute a qui la pose, et qui doit rester lisible** : `R-C` ne sort un changement
de sa porte que si quelqu'un d'autre produit la mesure qui le discharge. Si cette mesure n'existe pas,
les elements concernes **restent dans sa porte sans verdict** - un etat inconfortable, et voulu.

**Gouvernance d'une story absorbee en cours de route (amendement s06, retro de s05).** Une story ou
une decision de perimetre qu'un sprint absorbe en cours de route depuis un autre sprint -
deplacement d'une story planifiee ailleurs, execution anticipee d'un chantier prevu au sprint
suivant - est actee par un amendement ecrit a la decision d'origine, dans le meme geste que sa
fusion sur `round-two`, jamais seulement constatee a la review du sprint qui suit. En s05,
`DEC-0012` placait explicitement le rhabillage du gabarit au sprint 6 ; il a ete livre et audite
dans s05 sans qu'aucun amendement ecrit ne le dise au moment des faits, et l'anomalie n'a ete
constatee qu'a la cloture du sprint suivant.

**Un chiffre de capacite "consomme" nomme les postes qu'il additionne** (amendement du 2026-10-12,
`sofia-sm`, retro de s56, section 6, proposition confiee a `camille-po` et `nadia-prof`). Fait mesure :
a s56, `cloture-nadia.md` section 6 donne 5,75 consomme et omet des postes livres (les arbitrages 1,0, la
forme 0,25, le choix des oeuvres 0,25) ; 7,25 en les comptant. Les
deux sommes sont justes pour les postes qu'elles additionnent ; c'est la liste qui manquait, pas
l'arithmetique (retro de s56, section 4, avant-derniere puce).

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

Le trailer `Co-Authored-By` d'un commit d'agent nomme le modele que cet agent a execute, tel que le rapport
de tokens du sprint le donne (`claude-opus-5-5` s'ecrit "Claude Opus 5.5"). Un brief de lancement ne le
dicte pas. La CI ne le verifie pas : elle ne lit que le sujet. Un trailer errone n'est pas reecrit
(`DEC-0110` section 17.4).
Amendement du 2026-10-08 (`camille-po`, s54) : pour les 7 commits de s54 signes "Claude Opus 5.5", l'utilisatrice a
arbitre que la configuration de session impose "Claude Sonnet 5.5" a tous les agents ; ils ont ete reecrits (plage
non poussee). Les commits Opus deja pousses ne le sont pas. Annotation du 2026-10-08 : la portee future est tranchee, le texte
ci-dessus reste la regle (trailer = modele reellement execute) et la reecriture de s54 est un geste ponctuel ; un
agent dont la session impose un autre trailer le signale (`DEC-0110` section 17.4, annotation du 2026-10-08).
Amendement du 2026-10-06 (`sofia-sm`, s52) : ce paragraphe transcrit le texte que `DEC-0110` section 17.4
propose pour cette section et confie a `sofia-sm` (action A7 de la retro de s51). En cas d'ecart, la `DEC` fait
foi. Il ne ferme aucun garde-fou : une regle non executable est un souhait, celle-ci est assumee comme telle.

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

**La discipline de mesure par worktree dedie, jusque-la limitee a la premiere ecriture d'un lot,
s'etend a toute mesure du reacteur, complete ou partielle** (amendement s30, tranche au planning de
s30, confirme tenu a sa retro). A s29, trois incidents d'orchestration venaient de mesures
concurrentes sur un worktree partage : des rouges intermittents sur un `target/` en cours de
reconstruction par une autre mesure, une consigne de mesure limitee au module qui a laisse passer
un rouge que seul le reacteur complet voyait, et du travail d'un agent embarque dans le commit d'un
autre. `yanis-qa` avait deja adopte de lui-meme cette discipline depuis s28 ; elle n'avait jamais
ete generalisee aux autres agents. A s30, `marc-dev`, `lea-dev`, `theo-archi`, `yanis-qa` et les
deux instances de `nadia-prof` ont tous mesure depuis leur propre worktree pour toute la duree du
sprint, premiere mesure ou non, et l'assemblage n'a trouve aucun recouvrement de fichier sur
vingt-huit paires de branches verifiees a travers trois vagues de fusion - la regle a tenu a
l'epreuve d'un sprint entier.

**Le worktree dedie isole l'arbre Git et le repertoire de compilation d'un agent ; il n'isole pas
une ressource de travail temporaire partagee entre deux instances du meme agent** (amendement s30,
retro de s30). A s30, les deux instances de `nadia-prof` tournant en parallele partageaient le meme
scratchpad d'agent, hors du depot, et l'une a ecrase le fichier de l'autre pendant leurs reaudits -
sans consequence de contenu, l'Instance A ayant detecte la collision et verifie qu'aucun contenu de
l'autre instance n'avait contamine ses propres fichiers avant de rendre son verdict. **A partir de
s31, toute paire d'instances du meme agent tournant en parallele recoit un scratchpad aussi dedie
que son worktree** : la regle de ressource dediee ne se limite pas au depot Git, elle s'applique a
toute ressource de travail qu'une mesure ou un audit concurrent pourrait faire collisionner.

**Une instance de `nadia-prof` qui ecrit dans `squad/decisions/REGISTRE-identifiants.md` le fait
depuis son propre worktree, jamais dans le checkout partage** (amendement hors sprint, 2026-09-10,
route par `theo-archi` dans son avis de faisabilite de s34, section 3.3). Ce garde-fou balaie
l'arbre de travail : quand plusieurs instances allouent en parallele sur le checkout partage, il
juge un etat qui ne sera jamais un commit, sous deux modes distincts - un **faux rouge** (transitoire,
un fichier non commite d'une instance soeur cite un identifiant que le registre n'alloue pas encore,
mesure deux fois a s33) et un **faux vert**, plus grave et jamais nomme avant ce jour (l'allocation
non commitee d'une instance fait passer la citation d'une autre, qui rougit ensuite a la fusion, sur
son propre commit, pour des identifiants qui ne sont pas les siens - mesure par `theo-archi` sur son
propre document a l'ouverture de s34). Deux agents (`yanis-qa`, `theo-archi`) ont chacun du batir un
worktree isole ou jetable pour prouver, a la main, qu'un rouge ne venait pas de leur commit -
troisieme et quatrieme occurrences de ce cout. **Aucun test ne peut voir cette regle** : un fichier
ne porte pas le nom de qui l'ecrit. Ce que la CI garde reste intact - les deux modes se referment a
la fusion - ; ce qui est degrade est l'experience locale, et c'est de la que vient le risque reel :
un agent qui apprend a lire un rouge comme "ce n'est pas le mien" finit par le lire ainsi le jour ou
il l'est.

**Sofia ouvre elle-meme le worktree de l'agent qu'elle route, dans le meme geste que le routage,
plutot que de compter sur cet agent pour s'en souvenir** (amendement s39, retro de s39). Cinq
occurrences de la meme faute en trente-neuf sprints (s06, s18, s21/22, s38, s39), quatre tentatives
de texte deja portees par cette section - dont un refus explicite a s38, au motif, toujours valide,
que le texte ne manquait de rien - et un engagement personnel non ecrit de Sofia elle-meme a s38
qui n'a pas mieux tenu qu'un texte deja ecrit quatre fois. A s39, `camille-po` a de nouveau ecrit
directement sur `round-two` sans worktree ni fusion, et cette fois le retard correspondant a fait
rougir le reacteur avant d'etre corrige. Ce qui change n'est pas la regle de fond - elle reste
celle de s06 - mais l'acteur qui en repond : desormais Sofia cree le worktree au moment ou elle
route, de sorte que l'agent route trouve un worktree deja pret plutot que d'avoir a s'en souvenir.
Cela ne dispense aucun agent de la regle telle qu'ecrite depuis s06 : un agent qui recevrait un
routage sans worktree deja ouvert (defaillance de Sofia elle-meme) en reste tenu.

**Avant d'ouvrir le worktree de l'agent qu'elle route, Sofia verifie que sa propre ceremonie qui
produit ce routage est deja fusionnee sur `round-two`** (amendement s40, retro de s40). L'amendement
de s39 ci-dessus couvrait un sens de la dependance - un worktree route existe au moment ou l'agent
en a besoin - sans couvrir l'autre : ce worktree doit etre construit sur un socle qui porte deja
tout ce que la ceremonie qui le route vient d'ecrire. A s40, Sofia a ouvert son propre worktree
dedie pour ecrire `planning.md` (`a32fa316`), puis, au moment de router vers `camille-po`, a
constate elle-meme que ce commit n'etait pas encore fusionne sur `round-two` : un worktree ouvert a
cet instant depuis le tip de `round-two` n'aurait pas contenu le planning routant le travail de
`camille-po`. Constate avant tout travail en aval, corrige sans aucun cout - mais le seul precedent
comparable (`camille-po`, s39, section ci-dessus) avait coute un reacteur rouge pendant un retard.
Desormais, Sofia verifie que le commit portant sa propre ceremonie (planning, `DEC`, ou tout autre
document routant un travail) est deja sur `round-two` avant d'ouvrir, depuis son tip, le worktree
de l'agent qu'elle route.

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

**Une fois la `DEC` d'ouverture et l'avis de faisabilite ecrits, Sofia reconcilie le planning qui
les precedait avec eux avant la cloture du sprint - pas en l'editant, mais en le constatant dans
`review.md`** (amendement s48, retro de s48). A s48, les sept developpements du sprint ont tous ete
commites avant `DEC-0106` (qui gele le perimetre) et avant l'avis de faisabilite de `theo-archi` qui
en confirme la faisabilite technique - sans consequence reelle ce sprint, parce que six des sept
stories citaient deja, mot pour mot, la forme pedagogique attendue. `planning.md`, commite en
dernier, portait pourtant encore une phrase conditionnant l'affectation nominative a l'existence
future d'un avis de faisabilite deja ecrit une minute plus tot : aucun document du depot n'a fini
par porter cette affectation. La regle ne consiste pas a interdire un developpement engage avant sa
propre `DEC` - cela reste parfois legitime, section ci-dessus - mais a ce que la ceremonie de cloture
dise explicitement, dans `review.md`, si et comment le planning a ete rattrape par les decisions et
avis qui devaient le suivre, plutot que de laisser une phrase du planning parler d'un futur
entre-temps devenu le passe sans que personne ne le signale.

**Une porte de sortie ne depend pas d'un artefact que produit l'etape qu'elle precede** (amendement du
2026-10-12, `sofia-sm`, retro de s56, section 6, proposition confiee a `sofia-sm` et a l'utilisatrice).
La verification poste/story du planning (`PAR-16`, section 4) y ajoute une question : pour chaque porte,
l'artefact qu'elle exige existe-t-il **avant** l'etape qu'elle conditionne ? Fait mesure : le planning de
s56 demandait `Q7` avant l'entree de catalogue ; `Q7` se mesure sur le jar qui sert cette entree
(`Q7-releve-gabarit-livre.md` sections 0 et 1). Porte ecrite par `sofia-sm`, insatisfiable telle quelle
(retro de s56, constat C13).

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

**Une mesure partielle qui touche `catalogue.yaml`, `squad/decisions/REGISTRE-identifiants.md`, ou
tout autre fichier de contenu servi, inclut `bootstrap` dans son perimetre, meme mesure partielle**
(amendement s46, retro de s46). A s46, `marc-dev` a mesure son lot (`MAT-CE1-05`) avec
`-pl adapter-content -am`, qui construit les dependances amont du module mesure (`domain`,
`application`) mais jamais les modules avals qui dependent de lui, comme `bootstrap`.
`TexteAccentueExposeParLePortEntrantTest`, dans `bootstrap`, verifie que chaque chaine exposee par
le catalogue reel est inventoriee ; il n'a rougi qu'a la fusion complete des cinq lots du sprint,
trouve par `yanis-qa` puis corrige par `sofia-sm` avant la mesure de cloture (commit `346274c8`).
**`-am` seul ne protege pas de ce defaut** : il construit ce dont le module mesure a besoin, jamais
ce qui a besoin de lui - la regle deja ecrite dans `CLAUDE.md` ("toute mesure partielle du reacteur
porte `-am`") reste necessaire mais ne suffit pas ici, puisque le module qui aurait vu le rouge est
en aval, pas en amont, du module mesure. `bootstrap`, qui balaie le catalogue reel et plusieurs
registres partages, est le module aval le plus souvent concerne par ce defaut precis. Desormais,
toute mesure partielle d'un commit qui touche l'un de ces fichiers transverses inclut `bootstrap`
dans son perimetre (`-pl <module>,bootstrap -am`), en plus du module modifie.

**A la soumission d'une correction de contenu en reponse a un refus pedagogique, deux gestes
reduisent le risque qu'elle introduise un defaut neuf plutot que de lever le motif refuse**
(amendement s47, retro de s47). Deux refus en seconde instance du meme sprint venaient d'un
defaut cree par la correction elle-meme, pas de l'erreur d'origine : sur `EMC-CM1-10`, la
correction a paraphrase une formulation que `nadia-prof` avait pourtant ecrite mot pour mot comme
forme acceptee au passage precedent, et la paraphrase s'est revelee fausse pour une raison que la
formulation d'origine evitait - *"si la correction avait repris l'une des deux formes que j'avais
ecrites, je l'aurais acceptee"* ; sur `QLM-CP-15` (reserve `AUT-CP-R1`, non bloquante), la
correction d'un indice a reutilise le mot qui est la bonne reponse d'un autre item du meme paquet,
rouvrant une fuite de reponse deja fermee a un passage anterieur. Les deux ont ete vus uniquement
parce que `nadia-prof` rejoue le paquet entier a chaque passage, jamais seulement l'item corrige -
pratique deja en place, pas neuve ici. Desormais, avant de soumettre une correction de contenu :
**toute formulation que `nadia-prof` a ecrite comme forme acceptee ("exemple de forme") est
reprise verbatim, sauf motif ecrit explicite de s'en ecarter** ; et **toute correction d'un indice
ou d'un enonce est suivie d'une recherche du mot de la bonne reponse qu'elle vient d'y placer dans
le reste du fichier du paquet**, pour verifier qu'aucun autre item n'a ce mot pour bonne reponse.
Aucun des deux n'est un garde-fou automatisable a ce jour : la seule garde reste la discipline du
developpeur avant soumission, en plus du rejeu complet deja fait par `nadia-prof`.

**Avant tout `git push`, y compris d'un commit de contenu seul, l'agent qui pousse lance sur la racine
du depot `bash outils/mvn-jdk21.sh -B clean verify`, sans `-pl`, et les juges des jobs `prefixes`,
`chemins` et `outils` de `.github/workflows/ci.yml` ; il nomme dans son rapport les jobs lances et leur
code de sortie** (amendement s54, action A8 de la retro de s53, section 3). Motif : des garde-fous de
`bootstrap` (`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`,
`AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest`, `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`)
jugent tout paquet present dans `contenu/`, branche ou non ; `mvn -pl adapters/adapter-content test` ne les
charge pas, et c'est ainsi que la base de s53 est partie rouge. Une mesure partielle, meme avec `-am` et
`bootstrap` dans son perimetre, ne tient jamais lieu de cette commande. **Ce que rien n'execute** : aucun
crochet de pre-push n'est installe par le depot, et la CI juge apres le push. Cette regle est une
discipline ; le mecanisme automatique qui la rattrape est la CI elle-meme, apres coup.

**3. Daily.** Sofia collecte et route. Pas de fichier.

**A chaque daily, Sofia demande explicitement a chaque agent en cours de lot de confirmer que son
premier commit a bien ete ecrit dans un worktree dedie** (amendement s23, retro de s23), plutot que
de le verifier seulement a la fusion comme c'est deja sa pratique depuis s14. La regle de fond ne
change pas (section 9, ci-dessus, ecrite depuis s06 et deja amendee trois fois) ; ce qui change est
le moment ou elle est mise a l'epreuve. En s23, cinq occurrences de la meme faute sur un seul
sprint (deux agents a l'ouverture, trois de plus a mi-sprint dont Sofia elle-meme sous sa casquette
developpeuse) ont toutes ete autocorrigees par leur auteur avant tout commit sur la branche visee,
sans qu'aucune n'ait jamais ete detectee au daily : la regle relue au planning ne suffit pas seule,
et la verifier plus tot dans le cycle - avant que le travail ne s'accumule sur la mauvaise branche
- coute moins cher que de la verifier seulement a la fusion.

**Un glissement d'echeance est ecrit au moment ou il est constate, dans un fichier du dossier du
sprint** (amendement du 2026-10-12, `sofia-sm`, retro de s56, section 6, proposition confiee a
`sofia-sm` et a l'utilisatrice). Une ligne : l'echeance, le constat, a qui il a ete dit. Cette ligne
n'est pas un journal de daily : le daily reste sans fichier, et elle ne rapporte que l'ecart a une
echeance ecrite. Fait mesure : a s56, l'entree de catalogue est fusionnee 15 h 07 min apres son echeance
de 20:00, 23 h 49 min sans livraison entre `f37e33dd` (2026-10-10 11:17) et `38ba3d5c` (2026-10-11
11:06), et aucun fichier du depot ne trace que le glissement a ete dit (`review.md` de s56, section 3,
obstacle 6). Le planning de s56 exigeait de le dire le jour meme ; la regle n'avait pas de fichier ou
le consigner.

**4. Porte technique.** Les quatre jobs de la CI verts (`prefixes`, `chemins`, `outils`, `build`).
Yanis produit le rapport qualite, Theo confirme ArchUnit avec le resultat de la commande.
**Un garde-fou cite comme fermant un refus pedagogique ou une dette nommee n'est declare complet,
dans le `review.md` qui le cite, qu'une fois la preuve de morsure decrite explicitement**
(amendement s06, retro de s05) - mutation reelle du code, relecture
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

**Une relecture qui ne trouve rien a corriger ecrit tout de meme une ligne qui le dit** (amendement
s38, retro de s38). A s38, `relecteur-pedagogique` a ete invoque (72 tours, mesure par
`outils/rapport-tokens.py`) sans laisser aucun commit ni fichier dans le dossier du sprint. Rien
n'indique une faute - une relecture propre ne produit naturellement aucun diff -, mais rien
n'ecrit non plus qu'elle a eu lieu et sur quel perimetre : le seul artefact restant est un
compteur de tokens, qui atteste une consommation, jamais une conclusion. Desormais, quand la
relecture ne trouve rien a corriger, elle ecrit une ligne dans le dossier du sprint (date,
perimetre relu, "rien a corriger") - au meme titre qu'un rapport qualite qui confirme un Done
sans defaut trouve reste ecrit.

**6. Porte pedagogique.** Nadia joue l'increment, l'audite contre le referentiel, ecrit son
verdict dans la fiche de conformite. Si c'est `VALIDE`, Camille passe la ligne de matrice a
`couvert-valide`.

**Pour un chantier hors sprint, cette etape precede la fusion sur `round-two`** (amendement du
2026-10-12, `sofia-sm`, `DEC-0117` section 11). Rien n'est fusionne sans l'audit de `nadia-prof` : un
`REFUSE` ne laisse alors rien en service, et il se corrige avant fusion par une story corrective. Le
precedent hors sprint n'avait aucune regle ; la reponse de l'utilisatrice y est citee en section 4.

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

**Une defaillance d'infrastructure n'est pas une defaillance du travail** (amendement s34,
2026-09-10, ecrit pendant la fusion, avant la review et la retro - la regle qui precede impose de
ne pas attendre la prochaine ceremonie). Le 2026-09-10, pendant s34, le sous-agent `marc-dev` a ete
arrete en pleine tache par une
erreur de l'API, et non par une faute de son code : `invalid_request`, avec pour seul detail le mot
`[bio]`. Il editait du contenu YAML d'EMC et d'histoire-geographie pour des enfants de CP et de CM1,
ou aucun sujet biologique, medical ou dangereux n'apparait. Un classifieur a declenche a tort.
L'agent s'est interrompu entre deux stories, laissant trois fichiers modifies et non commites dans
son worktree, et aucun commit sur sa branche.

Ce que cet incident etablit, et qui vaut pour n'importe quel agent :

- **Un agent arrete par une erreur d'infrastructure laisse son worktree intact.** Avant de le
  relancer, on lit ce que la branche porte (`git log`) et ce que l'arbre porte (`git status`,
  `git diff`), et on le relance en lui disant de reprendre l'en-cours plutot que de repartir de
  zero. Jeter un worktree sans l'avoir lu perd du travail qu'aucun commit ne garde.
- **Un agent relance juge le diff qu'il trouve ; il ne le presume ni bon ni mauvais.** Il ne l'a
  pas ecrit lui-meme, et la consigne qui le relance doit le dire explicitement.
- **Le motif de l'arret est ecrit tel quel, sans etre reinterprete.** Une erreur d'API n'est pas un
  refus de l'agent et ne dit rien de la qualite du contenu qu'il editait. Conclure l'inverse - qu'un
  arret signale un probleme dans le contenu - ferait modifier du contenu juste pour une raison
  fausse, et c'est la seule facon dont cet incident pourrait couter quelque chose au produit.

Trace, pour qui voudrait rapprocher une autre occurrence de celle-ci : `invalid_request`, detail
`[bio]`, identifiant de requete `req_011Ceuz5K9VviaBoZE1sBDuT`, modele `claude-sonnet-5`. Une seule
occurrence a ce jour, non reproduite ; elle est ecrite ici parce qu'une seule occurrence suffit a
faire perdre un lot de travail a qui ne sait pas ou le retrouver.

**Un agent arrete par l'utilisatrice est ecrit par celui qui le relance : qui, quand, ce que sa branche
portait** (amendement du 2026-10-12, `sofia-sm`, retro de s56, section 6, proposition confiee a
`sofia-sm` et a l'utilisatrice). Les trois points ci-dessus couvrent l'arret par une erreur
d'infrastructure ; ils ne disent rien d'un arret voulu ni de qui l'ecrit. Fait mesure : a s56, deux
agents arretes par l'utilisatrice et remplaces et un watchdog sur `lea-dev` ; un seul fichier du depot
le dit (`cloture-nadia.md`, *"l'instance B arretee"*), le reste ne figure que dans un message de
lancement (retro de s56, constat C1 et section 4, derniere puce). Ce que l'ecriture contient : le nom de
l'agent, la date et l'heure de l'arret, le dernier commit de sa branche et l'etat de son arbre
(`git log`, `git status`). Un motif que celui qui relance ne connait pas est ecrit comme inconnu.

**Chiffrer un chantier contre un garde-fou existant verifie chaque clause opposable du garde-fou,
pas seulement la plus visible** (amendement s50, retro de s50). A s50, `camille-po` a engage `T6`
(arts plastiques CP) en sous-estimant son cout reel a deux reprises, sur deux clauses distinctes du
meme garde-fou et de la meme ligne de competence : une fois en lisant *"aucune reproduction ne
dort"* comme *"au moins un identifiant declare"*, quand le code boucle sur **chaque** reproduction
servie - decouvert par `theo-archi` en rendant son avis de faisabilite, pas avant ; une fois en
supposant que `ART-CP-10` reutilisait la forme d'item existante, quand elle exigeait en realite de
montrer deux oeuvres a la fois, une mecanique non engagee - decouvert par l'arbitrage prealable de
`nadia-prof`, pas avant. Les deux ecarts partagent un motif : l'estimation initiale s'est arretee a
la lecture la plus naturelle d'une regle ou d'un libelle, sans la confronter au code qui l'applique
(pour un garde-fou) ou a l'autorite qui l'a ecrite (pour une competence pedagogique). **Avant de
chiffrer un contenu contre un garde-fou technique, on relit le garde-fou dans le code, pas dans sa
paraphrase** - au meme titre qu'un chiffre de test se reverifie et ne se relaie pas
(`squad/README.md`, lecon deja ecrite pour les chiffres, etendue ici aux clauses d'un garde-fou).
Le cout de ce sprint est reste maitrisable (retrait d'un vivier restaurable a l'octet, reduction de
perimetre avant tout code) ; rien ne garantit qu'il le reste si la clause manquee touchait un
chantier deja code plutot qu'un registre de contenu.

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
- **Marc, Lea et Sofia (casquette dev) redigent les items d'un paquet de contenu (enonces,
  reponses, distracteurs) des lors que Camille a deja specifie la competence visee et les
  criteres d'acceptation** (amendement hors sprint, s22, 2026-08-29, arbitrage de
  l'utilisatrice). Camille ne redige pas les items elle-meme et Nadia reste seule autorite sur le
  Done pedagogique : ce que la redaction change, c'est qui met les mots sur le paquet, pas qui en
  fixe le cahier des charges ni qui en juge la conformite. Cet amendement corrige une
  contradiction constatee, pas introduite ce jour-la : la charte de Marc et celle de Lea
  disaient depuis leur premiere version l'inverse de la pratique reelle, deja en vigueur au moins
  depuis s21 (`31bcc9b` paquet cp-syllabes, `66ad4fb` paquet cp-mot-manquant, tous deux audites
  et prononces VALIDE SOUS RESERVE par Nadia sans qu'aucune charte ni aucun fichier de ce depot
  ne l'autorise par ecrit). Ni l'un ni l'autre des deux autres arbitrages possibles n'a ete
  retenu : faire rediger Camille en aurait fait un second goulot apres Nadia et aurait force la
  reprise de lots deja livres ; arbitrer paquet par paquet aurait laisse la frontiere non ecrite,
  donc rediscutable a chaque fois. Les chartes `.claude/agents/marc-dev.md` et
  `.claude/agents/lea-dev.md` sont corrigees dans le meme geste que cet amendement.
- **Aucun agent ne touche au worktree ni a la branche d'un autre agent - ni `reset`, ni
  `checkout`, ni `merge`, ni rien** (amendement s27, retro de s27, pose par l'orchestrateur apres
  un incident de coordination). Sur `round-two`, plusieurs sessions d'agents travaillent en
  parallele sur le meme clone local ; une branche qui bouge sans qu'on l'ait soi-meme fait n'est
  pas necessairement une corruption, elle peut etre une autre session en cours. Integrer le
  travail d'un autre agent se fait **en tirant** sa branche dans la sienne (`git merge
  <sa-branche>`), jamais en agissant dans son worktree. Si une branche parait incoherente, elle
  est signalee a l'orchestrateur, seul a savoir qui travaille encore - elle n'est pas corrigee
  unilateralement. Motif : `sofia-sm` a execute un `git reset --hard` sur la branche de
  `theo-archi` en la croyant corrompue alors qu'il s'agissait de sa propre session concurrente ;
  aucun commit n'a ete perdu, mais `theo-archi` a du refaire des modifications non commitees. Le
  cout vient de l'absence de cette regle, pas d'une faute d'agent.

## 11. Arborescence

```
squad/
  README.md               ce fichier - le process
  EQUIPE.md               les 8 agents, miroir humain de .claude/agents/
  TEMPLATES.md            gabarits vierges de tous les artefacts
  lessons.md              regles tirees des corrections - sofia-sm
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
