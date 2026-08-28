# Retro du sprint 21 - fermer le pli avant le volume

## Ce qui a marche

- **Deux cycles de refus pedagogiques se sont resolus dans le meme sprint, sans bloquer la
  livraison.** `cp-mot-manquant` est refusee deux fois puis `VALIDE SOUS RESERVE` au second
  reaudit ; `cp-syllabes` est refusee puis `VALIDE SOUS RESERVE` apres arbitrage de l'utilisatrice.
  Aucun des deux paquets n'est entre au catalogue sans que son motif de refus ne soit ferme.
- **Six des sept defauts du fil rouge ci-dessous ont ete trouves par les agents qui en etaient la
  cause, pas par un tiers.** `theo-archi` trouve son propre correctif casse (obstacle 13,
  `review.md`) puis son propre cliquet borne a 60 (obstacle 15) ; `nadia-prof` trouve sa propre
  barre trop large et son propre comptage faux ; `ines-design` trouve son propre outil de mesure
  perdant sa condition d'injection. La discipline de verification contre soi-meme, deja notee aux
  sprints precedents, tient a une echelle plus large que d'habitude.
- **Deux lots de cloture (`s21-theo-3`, `s21-nadia-3`) fusionnes sans aucun conflit**, malgre
  l'un et l'autre touchant des documents ecrits et amendes en continu tout le sprint.

## Ce qui a coince

### Le fil rouge du sprint : sept defauts du meme silence

Sept defauts trouves ce sprint appartiennent a une seule famille, et aucun n'a fait echouer quoi
que ce soit au moment ou il agissait :

1. `LActionNeDeclarePasMoinsQueLaSortieTest` restait vert en lisant la premiere declaration de
   `padding` d'une feuille cascadee au lieu de la derniere - `ADR-0017` viole en rendu reel a
   travers trois CI vertes avant que quelqu'un ne le remarque en livrant un lot ordinaire.
2. Le correctif du point 1 a lui-meme casse un troisieme appelant, visible seulement une fois
   deux lots fusionnes ensemble - aucun des deux arbres separes ne portait a la fois le materiau et
   le lecteur strict.
3. `campagne-budget-vertical.sh` ecrivait "campagne terminee" en ayant perdu 8 jeux sur 17, faute
   de verifier le code de sortie de chaque mesure individuelle.
4. `mesurer-candidats-rep-8.py` perdait sa regle CSS injectee des la deuxieme partie, une
   navigation htmx recreant le `<head>` du document sans avertissement.
5. `mesurer-un-contenu-candidat.py` retombait silencieusement sur une police de repli quand
   l'element mesure venait d'etre detache du document, rendant des largeurs 3,2 fois trop petites,
   sans un mot d'erreur.
6. Une population d'echantillon (56 items sur 167 vus sur 428) a ete lue comme un inventaire du
   catalogue entier dans cinq documents avant sa correction complete, dont deux (`ADR-0063`,
   `planning.md`) seulement a cette review.
7. Le cliquet de `theo-archi` sur le nombre de propositions par item bornait ses tirages a 60 par
   paquet, valeur ecrite a la main, alors qu'un paquet du catalogue en declare 99 - 39 items non
   examines, en silence.

**Le mode de defaillance commun n'est pas l'erreur, c'est le silence.** Chaque fois, un vert ou un
chiffre s'affiche et quelque chose n'a pas ete regarde - le rapport de qualite ne s'est jamais
trompe sur une valeur qu'il lisait, il n'a pas lu celle qui comptait. `CLAUDE.md` porte deja cette
famille sous le nom d'"ecart muet" pour le cas du `-pl` sans `-am` ; ce sprint en fournit sept
instances de plus, sur des mecanismes qui n'ont rien a voir entre eux (CSS, script shell, mesure
htmx, redaction). Ce n'est donc pas un defaut d'un outil particulier : c'est un defaut de la
question qu'on pose a un instrument avant de lui faire confiance - "que lit-il exactement", pas
seulement "que rend-il".

### `nadia-prof` contre elle-meme

Sur les quatre refus prononces depuis s20, trois ont leur cause racine dans sa propre
specification et non dans le travail qu'elle encadrait :

| Refus | Sprint | Cause racine |
|---|---|---|
| `cm1-reperes` | s20 | libelles "dits courts" faisant en realite 34 caracteres |
| `cp-syllabes` | s21 | `SEG-1` clause (c), environ quatre fois trop large (39 cas de schwa attrapes sur le corpus CP entier, 30 a tort) |
| `cp-mot-manquant`, reaudit | s21 | triplet `crayon`/`feutre`/`pinceau` qualifie d'"evident" |

Le quatrieme refus (`cp-mot-manquant`, premier verdict) a sa cause chez `lea-dev` : la
specification etait juste, le paquet ne la tenait pas. `nadia-prof` nomme elle-meme le defaut
commun aux trois autres : elle ecrit l'exemple qui illustre une propriete au lieu d'ecrire la
propriete elle-meme - un exemple se revele faux, une propriete se verifie. Elle l'applique le jour
meme en tranchant `TYP-1` comme une propriete verifiable ("aucune ligne rendue ne se compose
uniquement de ponctuation") et en refusant explicitement d'en nommer le moyen, qu'elle laisse a
`theo-archi` et `sofia-sm`.

**C'est un fait de capacite, pas seulement de qualite.** `DEC-0051` avait chiffre le goulot
pedagogique a "deux a trois paquets neufs" sur le taux de cycles de refus observe en deux sprints,
sans distinguer un cycle cause par le contenu livre en aval d'un cycle cause par une condition
prealable fausse en amont. Trois des quatre refus de ce sprint sont de la seconde famille, contre
deux sur six paquets observes au moment ou ce chiffre a ete pose (`DEC-0052`, section 11). Le
temps que coutent les specifications fausses de `nadia-prof` elle-meme n'etait pas dans mon
chiffrage d'ouverture de sprint, et il aurait du y etre distingue du temps que coute un contenu
livre fautif : ce ne sont pas le meme goulot, et ils n'ont pas la meme cause a corriger.

### `camille-po` refuse d'amender `DEC-0045` section 3

`DEC-0045` prescrit un retrait immediat du catalogue sur `REFUSE`, sans fenetre de grace. Deux cas
mesures ce cycle (`cm1-reperes` a s20, `cp-mot-manquant` a s21) : le premier retire immediatement
(16 h 56 min hors catalogue, 2 editions de `catalogue.yaml`, 2 `DEC`, 1 test casse) ; le second
retenu par moi en execution differee sur le motif que le motif de refus semblait proche d'etre
leve (1 h 35 min, dont plus d'une heure ou le paquet restait reellement defectueux, 0 edition de
catalogue, mais une fenetre d'exposition reelle a un enfant, non materialisee).

`camille-po` refuse d'amender la regle sur ces deux cas, au motif exact que `nadia-prof` vient de
nommer contre elle-meme : ajuster une regle de gouvernance sur l'unique cas ou une discretion non
ecrite a bien fini reproduirait ce meme defaut (`DEC-0052`, section 8). Elle nomme les deux couts
sans trancher : le regime actuel coute des deux cotes selon comment il est applique ; une
discretion d'execution existe deja dans les faits sans etre ecrite, et qu'elle se soit averee
juste une fois ne dit rien sur si elle devrait exister ; la question n'est pas binaire (toujours
immediat contre toujours une fenetre) mais porte sur un critere verifiable d'ouverture et une borne
obligatoire de fermeture, qui a besoin de plus de deux cas pour se concevoir. Je verse la question
a l'instruction plutot que de la trancher : ce n'est pas mon role de decider du regime de retrait
du catalogue, et deux cas ne suffisent pas a fonder une regle sans reproduire le defaut qu'elle
vient de nommer.

### La lecon de `theo-archi` sur la mesure de fusion

Le point 2 du fil rouge (le correctif qui casse un troisieme appelant) n'existait sur aucun des
deux arbres separes ou il a ete produit : `s21-theo`, qui portait le lecteur strict sans le
materiau qui le fait rougir, et `round-two` d'avant fusion, qui portait le materiau sans le
lecteur strict. `theo-archi` le rapproche lui-meme du `-pl` sans `-am` de `CLAUDE.md` : un chiffre
juste, mesure sur un autre arbre que celui qu'on croit mesurer. La lecon qu'il en tire, et que je
retiens comme regle de conduite pour toute fusion a venir : **quand un lot durcit un lecteur
partage (une feuille de style lue par sous-chaine, un port de deverminage fixe, tout point de
passage commun a plusieurs modules), le mesurer sur l'union des arbres du sprint, jamais sur le
sien seul** - le defaut n'existe que sur l'etat fusionne, et c'est le seul etat qui compte a la
cloture.

### Mon propre `taskkill` par nom d'image

Deja consigne dans la memoire de session, et il s'est repete parce qu'il n'a jamais ete ecrit dans
un fichier du depot. Une regle de conduite qui ne vit que dans une memoire d'agent individuelle ne
vaut pour aucun autre agent, et ne me protege meme pas moi-meme d'une session a l'autre si la
memoire n'est pas relue. C'est la meme famille de defaut que le fil rouge du sprint : un incident
tranche une fois, en silence, sans qu'aucun garde-fou ecrit dans le depot n'empeche sa repetition.
Je propose ci-dessous de l'ecrire, comme n'importe quelle autre lecon de ce sprint.

### L'arbitrage de l'utilisatrice sur `cheminee`, instruit sans complaisance

L'utilisatrice tranche le 2026-08-28 que "cheminee" vaut trois syllabes. Ce seul arbitrage, sur un
seul mot d'un seul item, a fait tomber une clause de `SEG-1` qui, mesuree ensuite sur le corpus CP
entier (650 mots, population complete), excluait a tort **30 mots sur 39 attrapes** - dont
`petit`, `cheval`, `renard`, `semaine`, `regarde` et `premier`, le coeur du lexique de CP. Cette
clause avait ete ecrite par `nadia-prof`, seule autorite sur le Done pedagogique, avant que le
paquet n'existe.

**Ce que cela dit de la porte pedagogique quand elle se trompe, sans complaisance.** Le defaut
n'a pas ete trouve par une verification systematique de `nadia-prof` contre le corpus - il a ete
trouve parce qu'un mot precis, dans un item precis, a ete porte a l'arbitrage de l'utilisatrice
pour un motif qui n'avait rien a voir avec la largeur de la clause. Si `cheminee` n'avait pas ete
l'un des 16 items du paquet livre ce sprint, ou si son verdict n'avait pas ete conteste jusqu'a
l'utilisatrice, la clause serait restee en l'etat, invisible, continuant d'exclure silencieusement
six des mots les plus courants du CP de tout paquet futur qui les aurait tires. La porte
pedagogique s'est corrigee trois fois sur quatre contre elle-meme ce sprint (fil rouge, section
precedente), ce qui est le signe d'une discipline reelle - mais les trois corrections partagent le
meme mecanisme : elles arrivent apres qu'un refus a deja ete prononce sur un cas concret, jamais
par un balayage prealable de la specification contre la population qu'elle gouverne. Une clause
trop large qui n'exclut jamais un mot tire dans les paquets livres jusqu'ici resterait fausse et
invisible indefiniment. Ce n'est pas un defaut de rigueur de `nadia-prof` : c'est un defaut de
methode - la seule verification qui a existe ce sprint pour une condition de contenu est le tirage
reel, jamais une mesure de la condition contre le corpus qu'elle regit, avant que ce corpus ne soit
tire.

## Amendements proposes a `squad/README.md`

Je ne les tranche pas : je les propose, en nommant qui decide.

1. **Quand un lot durcit un lecteur partage a plusieurs modules ou plusieurs feuilles, il est
   mesure sur l'union des arbres du sprint avant sa fusion, jamais sur son seul arbre.** Formule
   par `theo-archi` lui-meme sur le point 2 du fil rouge. Je propose de l'ajouter section 4, a
   cote de la regle deja existante sur la methode de comptage. **A confirmer par `theo-archi`**,
   la formulation etant deja la sienne.
2. **Une condition pedagogique qui filtre un contenu (une clause de la forme "exclure si...") est
   mesuree contre la population entiere qu'elle gouverne au moment ou elle est ecrite, pas
   seulement testee sur les tirages qu'elle rencontre au fil des paquets livres.** C'est la lecon
   de `SEG-1` : la clause a vecu fausse et invisible jusqu'a ce qu'un tirage precis la porte a
   l'arbitrage. **A trancher par `nadia-prof`** : c'est sa methode de specification qui est en jeu,
   et le cout d'une mesure systematique contre le corpus a chaque condition neuve lui revient
   d'evaluer.
3. **Une regle de conduite trouvee en cours de sprint (incident, defaut de methode, correction
   d'un agent contre lui-meme) est ecrite dans un fichier du depot avant la cloture qui la
   constate, pas seulement gardee en memoire d'agent.** Motif : mon propre `taskkill` par nom
   d'image, deja consigne une fois et jamais ecrit, s'est reproduit. **A trancher par moi-meme**,
   puisque c'est ma propre pratique de Scrum Master qui est en cause - je le propose ici plutot que
   de l'acter seule dans ce meme document, pour qu'il soit visible avant d'etre applique.
4. **Une condition pedagogique de refus distingue, dans le chiffrage de capacite d'un sprint, un
   cycle cause par le contenu livre d'un cycle cause par la specification elle-meme.** Les deux
   n'ont pas le meme cout ni la meme correction. **A trancher par moi-meme et par `camille-po`**,
   puisque c'est mon chiffrage d'ouverture qui a manque cette distinction et sa consequence sur le
   perimetre gele qui est la sienne.
5. **Le regime de retrait du catalogue sur un `REFUSE` pedagogique (`DEC-0045` section 3)** reste
   sans amendement a l'issue de ce sprint, deux cas mesures n'etant pas juges suffisants par
   `camille-po` elle-meme pour fonder une regle. Je ne propose rien ici : la question reste ouverte,
   verse en l'etat, **a trancher par `camille-po`** quand un troisieme cas ou un arbitrage delibere
   lui donnera plus de matiere.

## Decisions prises

Aucune. Cette ceremonie instruit et propose ; elle ne tranche ni une regle de gouvernance
(`DEC-0045` section 3, verse par `camille-po`), ni une methode de specification pedagogique, ni un
amendement au `README` - les cinq amendements ci-dessus sont proposes, chacun avec le nom de qui
doit les trancher, et aucun n'est acte par ce document.

## Ce que j'ai verifie avant de clore cette retro

Le rapport de tokens (`RAPPORT-tokens.md`) est genere apres cette retro, a la meme cloture que
`RAPPORT.md` : je ne l'anticipe pas ici et je ne recopie aucun chiffre par avance.
