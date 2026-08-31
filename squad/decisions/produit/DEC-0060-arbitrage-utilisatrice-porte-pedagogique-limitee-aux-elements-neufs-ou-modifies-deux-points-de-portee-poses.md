# DEC-0060 - Arbitrage de l'utilisatrice du 2026-08-31 : la porte pedagogique de `nadia-prof` porte
# sur les elements neufs ou modifies, les elements non modifies ne sont pas retestes. Deux points de
# portee instruits, poses et non tranches

- **Type** : arbitrage de l'utilisatrice, trace en decision produit
- **Date** : 2026-08-31
- **Auteur** : camille-po (redaction et instruction). **L'arbitrage est de l'utilisatrice, pas de moi.**
- **Sprint** : s24 (en cours)
- **Competence programme visee** : **transverse - pas de contenu pedagogique.** Cette decision porte
  sur le perimetre d'une porte de validation, pas sur un increment jouable.
- **Statut** : Acceptee

## 0. Ce que ce document est, et ce qu'il n'est pas

**Il est** : la trace ecrite d'un arbitrage de l'utilisatrice, ce qu'il dit, ce qu'il ne dit pas, ce
qu'il rend opposable, ce qu'il coute, et deux points de portee instruits.

**Il n'est pas** la clause opposable de la porte de `nadia-prof`. **Cette clause est a elle et a elle
seule** ; elle l'ecrira de son cote et elle lira ce document. Ce que je peux ecrire, c'est ce que la
regle change pour le perimetre, pour le backlog, pour les fiches de conformite et pour la matrice -
mon domaine. Le libelle de sa propre porte est le sien.

**Il ne prononce aucun Done pedagogique**, n'en anticipe aucun, ne rouvre aucun verdict, et ne
tranche aucune question d'architecture.

**Il ne rouvre pas le perimetre de s24**, gele par `DEC-0059`.

---

## 1. Les mots exacts, et pourquoi ils sont recopies tels quels

L'utilisatrice, le 2026-08-31 :

> *"quand nadia valide elle doit se limiter aux nouveaux elements, ceux qui ne sont pas modifier ne
> sont pas a re tester"*

**Ils sont recopies sans etre reformules, et c'est delibere.** `DEC-0053` a traduit un arbitrage de
l'utilisatrice sur le pli en une formulation plus large que ses mots ; `nadia-prof` l'a releve
(`squad/conformite/PORTE-PEDAGOGIQUE-apres-arbitrage-du-2026-08-28.md`, section 9.2), et il a fallu
reposer la question a l'utilisatrice pour que la portee soit tranchee (meme document, section 10).
Le cout de cet ecart n'a pas ete la reformulation elle-meme : il a ete qu'une porte de validation a
change de perimetre sans que celle qui la tient l'ait su. **Je ne recommence pas.** Partout ou ce
document depasse ces deux lignes, il le signale et il pose la question au lieu d'y repondre.

---

## 2. Ce que l'arbitrage dit

1. **L'unite de l'audit de `nadia-prof` n'est plus l'increment ni le jeu : c'est ce qui est neuf ou
   modifie dans l'increment.**
2. **Un element non modifie n'est pas rejoue et n'est pas rejuge.** Le verdict deja prononce dessus
   tient tel qu'il est ecrit dans sa fiche de conformite.
3. La regle porte sur **deux actes nommes** par l'utilisatrice : *valider* (prononcer un verdict) et
   *retester* (rejouer pour rejuger).
4. **Elle s'applique a partir du 2026-08-31, s24 compris**, et il n'y a pas de raison de la suspendre
   pour le sprint en cours : elle allege, elle ne contraint pas.

---

## 3. Ce que l'arbitrage ne dit pas

Sept choses, ecrites parce qu'un lecteur presse pourrait les y lire.

1. **Il ne dit pas qu'un Done deja prononce devient definitif.** Il dit qu'il n'est pas *reverifie*.
   Le jour ou l'element bouge, il rentre.
2. **Il ne dit pas qu'une reserve ouverte est fermee.** Un `VALIDE SOUS RESERVE` reste sous reserve,
   avec la reserve ecrite dans sa fiche. La regle ne solde rien.
3. **Il ne dit pas que les conditions de `nadia-prof` cessent de s'appliquer aux jeux existants.**
   `M1` a `M13`, `R1` a `R6` et les conditions de paquet continuent de decrire ce que le produit doit
   tenir. La regle dit qu'elle ne va pas les **reverifier** ; elle ne dit pas qu'elles cessent d'etre
   vraies.
4. **Il ne dit rien de la CI.** Il ne dit pas que la CI couvre ce que `nadia-prof` cesse de couvrir.
   Voir la section 5 : ce n'est pas le cas, et c'est le prix.
5. **Il ne change pas qui prononce le Done.** `nadia-prof` reste seule autorite sur la porte
   pedagogique. La regle borne son perimetre, pas son pouvoir.
6. **Il ne dit pas que `nadia-prof` cesse de pouvoir constater un fait sur un jeu existant.**
   Constater et router n'est ni valider ni retester. C'est ma lecture etroite, et la section 7.2
   l'instruit et la pose.
7. **Il ne definit pas "modifie".** L'utilisatrice a donne une regle, pas une definition. C'est la
   section 6.

---

## 4. Ce que l'arbitrage rend opposable, et a qui

| Qui | Ce qui devient opposable |
|---|---|
| `sofia-sm` au planning | La charge d'audit d'un sprint se compte en **paquets neufs ou modifies**. La borne de `nadia-prof` (4-5 paquets, plus l'audit d'une mecanique neuve) se mesure sur ce compte-la. Elle ne peut plus inscrire l'audit d'un jeu non modifie au plan d'un sprint |
| `camille-po` (moi) | **Je n'ouvre une fiche de conformite que pour un jeu neuf ou modifie**, et je ne resoumets pas un jeu existant a la porte. C'est immediat et c'est de ma main |
| `nadia-prof` | Elle peut **decliner** une demande d'audit portant sur un element non modifie, en citant cette decision. Elle n'a pas a en negocier le motif |
| un developpeur | Corriger un defaut sur un jeu existant **le rend modifie** : il repasse la porte. La regle ne cree pas de zone ou l'on corrige sans faire revalider |
| une cloture de sprint | Un `RAPPORT.md` ne peut plus compter comme une omission le fait qu'un jeu existant n'ait pas ete rejoue |

---

## 5. Ce que l'arbitrage coute

Une regle qui allege retire une garantie. Voici laquelle, nommee plutot que supposee.

**a) Sur un jeu non modifie, la CI devient le seul garde - et elle ne tient pas tout.** Ce n'est pas
mon jugement pedagogique, c'est une liste que `nadia-prof` a ecrite elle-meme le 2026-08-30, sous le
titre *"Sur quelles proprietes je reste la seule garde"*
(`squad/conformite/ARBITRAGE-PREALABLE-s24-borne-den-premier-paquet-de-saisie-et-deux-points-de-satisfaisabilite.md`,
section 8) : `LIA-3` integralement, la coherence indice/enonce sur 21 des 24 items d'`ETA`, `SAI-6`,
`SAI-7`, `SEUIL-2`, `R1` a `R6`, et le comportement d'un clavier virtuel de tablette. **Aucune de ces
proprietes n'est tenue par un garde-fou executable.** Je la cite, je ne la mesure pas : elle est
datee et signee.

**b) Un defaut present depuis l'origine sur un jeu existant ne sera plus trouve par la porte
pedagogique.** Il sera trouve par l'utilisatrice en jouant, ou pas du tout. Ce depot a le precedent :
le sprint 16 a ete ouvert par *"deux defauts trouves par l'utilisatrice en rejouant l'application"*
(`docs/backlog/backlog.md`, en-tete du bloc s16). La regle ne cree pas ce mode de decouverte, elle en
fait le seul.

**c) Le cout est cumulatif, et c'est sa propriete la plus desagreable.** 27 jeux sortent de la porte
aujourd'hui. Chaque sprint en ajoute et n'en fait jamais revenir. Dans dix sprints, la part du
catalogue qu'aucune porte pedagogique ne regarde plus aura grandi sans qu'aucune decision
supplementaire n'ait ete prise. **Ce n'est pas un motif de refuser l'arbitrage** - il est de
l'utilisatrice et il a une contrepartie evidente en debit - c'est un motif de l'ecrire ici pour que
la question puisse etre reposee un jour sur un fait et non sur une impression.

**d) Ce que l'arbitrage achete, et il faut l'ecrire a cote du prix.** `nadia-prof` est la ressource
la plus contrainte du produit : sa borne est de 4 a 5 paquets par sprint, reconfirmee le 2026-08-30,
et l'audit d'une mecanique neuve n'y est pas compris. Tout ce que la regle lui retire de
reverification, elle le rend disponible pour de la couverture neuve. **A 24 lignes `couvert-valide`
sur 43 atteignables, c'est exactement la ressource qui manque.**

---

## 6. Point de portee 1 - ce que "modifie" veut dire

### 6.1 Le cas qui force la question, et il est dans le sprint en cours

Un item dont le texte change est modifie, sans discussion. Le cas dur est ailleurs.

**Les lots 1 et 3 de s24 retypent du code partage par les 27 jeux existants, et aucun contenu de ces
27 jeux ne bouge.** Faits, avec leur source :

- **Lot 1** touche `domain` (le type d'item, le type de mode, la couture scellee), `adapter-content`
  (la fabrique) et `bootstrap` ; **lot 3** retype `EtatDePartie` et `EtatDePartieEnSession` sur
  `GameSession` dans `adapter-web`, **cout mesure par `theo-archi` : 20 lignes dans 3 fichiers de
  production**, plus les tests du module qui nomment ces types
  (`squad/sprints/s24-trois-paquets-et-ouverture-de-la-saisie/avis-faisabilite.md`, lots 1 et 3).
  **Le chiffre est de lui, je ne l'ai pas reproduit.**
- **Aucun des 27 paquets de `contenu/` ni aucune des 27 entrees du catalogue ne change du fait de ces
  deux lots.** Verifie par moi sur l'arbre fusionne de `s24-sofia`, methode nommee : comparaison de
  la liste triee des valeurs de la cle `paquet:` de `catalogue.yaml` avec la liste triee des noms de
  fichiers de `contenu/` - **27 contre 27, difference nulle dans les deux sens.**

**Deux lectures existent, et elles rendent des reponses opposees :**

- **lecture par le contenu** - le contenu des 27 est intact, donc les 27 sont non modifies, donc ils
  sortent de la porte ;
- **lecture par le code** - le code qui produit leur ecran change, donc les 27 sont modifies, donc ils
  y rentrent tous, dans le sprint meme ou la regle est censee alleger.

### 6.2 Ce que je tranche, parce que c'est de mon perimetre

**L'unite de la porte pedagogique est ce que l'enfant recoit a jouer - un jeu du catalogue - jamais
un fichier source.** Je ne l'invente pas et je ne l'etends pas : c'est deja la maille de ce depot.
Une fiche de conformite porte un jeu (`FICHE-<jeu>.md`), un Done porte un jeu, une ligne de matrice
porte une competence servie par un ou plusieurs jeux. Aucun de ces trois artefacts n'a jamais eu de
maille "fichier Java".

**Le critere qui en decoule** : un jeu est *modifie* au sens de cet arbitrage **quand ce que l'enfant
en recoit change** - son contenu, son ecran, son comportement, ce qu'il lit apres avoir repondu, ou
son score. Il ne l'est pas quand seule la forme du code qui le produit change **et que ce qu'il rend
est inchange**.

> **RENVERSE le 2026-08-31 par la reponse de l'utilisatrice a `Q1` - voir l'amendement en fin de
> document.** Elle a choisi l'autre lecture : *"tout changement du code"*. Ce critere-ci n'est plus
> celui du produit. **Le texte reste ici, non efface** : il vaut comme la proposition que j'ai faite
> et comme la mesure de l'ecart entre ce que je proposais et ce qu'elle a voulu.

### 6.3 Ce que je ne tranche pas, et je m'arrete la

**Savoir si les lots 1 et 3 laissent ce que rendent les 27 jeux strictement inchange est un fait
technique.** Il appartient a `theo-archi` ; ce n'est pas a moi de l'affirmer, et je remarque que
personne ne l'a encore ecrit. Un retypage *est concu* pour ne rien changer au rendu ; ce depot a paye
plusieurs fois la difference entre concu pour et mesure.

**Ce que cela change pour s24 : rien, et je le dis pour qu'on ne l'attende pas.** L'arbitrage de
l'utilisatrice a ete maintenu apres qu'on lui a signale cette lecture. **Les 27 jeux existants
restent hors de la porte de `nadia-prof` a s24**, quelle que soit la reponse de `theo-archi`, et la
CI est alors leur seul garde - avec le trou nomme en 5.a, qui est le prix et non un desaccord.

> **CADUC le 2026-08-31.** Ce paragraphe enregistrait un echange anterieur a la reponse de
> l'utilisatrice a `Q1`. Sous *"tout changement du code"*, **les 27 jeux existants rentrent au
> contraire dans la porte de `nadia-prof` a la cloture de s24**, et le cout de cette entree est
> instruit par `DEC-0063`. Non efface, date, remplace.

**Ce que je demande, et ce n'est pas un lot de plus** : que la cloture de s24 dise **si** l'invariance
de rendu des 27 jeux a ete **mesuree** ou seulement **attendue**. Une phrase, avec sa methode ou avec
son absence. Route a `theo-archi` et `sofia-sm`, sans ouvrir de story et sans toucher au perimetre
gele.

---

## 7. Point de portee 2 - ce que la regle fait des reserves ouvertes de `nadia-prof`

### 7.1 Le cas, chiffres et methode a la source

`nadia-prof` a elargi sa **reserve 5** le 2026-08-30 : sur les 615 items enumeres des 27 paquets,
**534 portent trois propositions et 48 en portent deux, soit 582 - 94,6 pour cent** ; sur ces items,
un enfant qui presse au hasard sans represser une proposition deja barree **a necessairement raison
au dernier essai, compte comme une reussite**. Le chiffre, la population et la methode sont d'elle
(section 7.1 de son arbitrage prealable de s24) ; **je ne les ai pas reproduits.** Ce que j'ai
verifie moi-meme, dans `BanditManchotSession.java` sur l'arbre fusionne : `ESSAIS_AVANT_REVELATION`
vaut `3`, `jouer` teste la bonne reponse avant de compter l'essai, `resoudre(true)` incremente
`reussites` sans regarder le nombre d'essais deja depenses, et `propositionsEssayees` conserve les
propositions pressees. Le mecanisme qu'elle decrit est bien celui du code.

**Ces 582 items sont tous existants et tous non modifies.** La question est donc frontale : la regle
les met-elle hors de portee d'une reverification ?

### 7.2 Ma lecture, donnee comme la mienne

**Non, et sur deux motifs distincts qui tiennent tous les deux dans les mots de l'utilisatrice.**

- **L'acte de `nadia-prof` n'est ni *valider* ni *retester*.** Elle n'a prononce aucun verdict, n'en a
  retire aucun - elle l'ecrit : *"Je ne refuse rien et je ne retire aucun Done"* - et elle n'a rien
  rejoue. Elle a lu une classe du domaine et compte des propositions dans des fichiers YAML.
  L'arbitrage nomme deux actes ; celui-la n'en est aucun.
- **L'objet de la reserve 5 n'est pas le contenu de ces 582 items.** C'est une propriete de la
  mecanique face au nombre de propositions, et elle le dit dans ces termes : *"Ce n'est pas un defaut
  de contenu, c'est une propriete de `M5`"*. Une regle qui parle d'**elements** non modifies porte sur
  des elements ; `M5` n'est pas un element du catalogue, c'est la machine qui les joue.

**Consequence que je tire, et elle est la seule chose que je ferme ici** : `DEC-0060` **ne classe pas
sans suite** l'obstacle 2 du planning de s24. Il est tranche pour le produit par `DEC-0061`, ecrite
dans le meme geste que celle-ci. Que personne ne lise cette regle-ci comme l'enterrement de
celle-la.

### 7.3 L'ambiguite reelle, que je pose au lieu de la resoudre

Elle n'est pas dans le passe, elle est dans la suite, et elle est lourde.

**Si `M5` est corrige, le comportement des 27 jeux existants change sans qu'une seule ligne de leur
contenu bouge.** Sous le critere que je pose en 6.2 - ce que l'enfant recoit change - **ils
deviennent alors tous modifies en meme temps, et ils rentrent tous dans la porte de `nadia-prof` d'un
coup**. 27 jeux contre une borne de 4 a 5 paquets par sprint : **un facteur de cinq a sept**, sur une
ressource que le produit n'a pas.

**Trois issues sont possibles et aucune n'est de moi :**

1. les 27 rentrent, et la correction de `M5` s'etale sur cinq a sept sprints d'audit ;
2. les 27 ne rentrent pas, et une correction de mecanique est livree sans qu'aucun des jeux qu'elle
   change n'ait ete rejoue par la porte pedagogique ;
3. un regime intermediaire - un audit de mecanique et non de paquets, ou un echantillon nomme - qui
   n'existe nulle part dans ce depot aujourd'hui et qu'il faudrait ecrire.

**Je ne choisis pas.** L'issue 1 rendrait la correction impraticable, l'issue 2 viderait la porte de
son objet sur le geste le plus large que ce produit puisse faire, et l'issue 3 est une invention qui
demande l'accord de celle qui tient la porte. **Une decision qui coute cinq sprints d'audit ou qui
retire la porte d'un geste ne se prend pas dans une note de bas de page.**

---

## 8. Les trois questions posees a l'utilisatrice

Elles sont posees, pas resolues. Aucune ne bloque s24.

- **Q1 - "modifie" se lit sur quoi ?** Sur le contenu donne a l'enfant, ou sur tout changement du
  code qui produit son ecran ? *Ma proposition, section 6.2 : sur ce que l'enfant recoit.* Sous cette
  lecture, un retypage sort les jeux de la porte et la CI seule les garde, avec le trou nomme en 5.a.
  Sous l'autre, la regle n'allege presque rien : tout lot technique partage ramenerait les 27 jeux
  dans la porte.
- **Q2 - quand une correction partagee change le comportement des 27 jeux existants d'un coup,
  rentrent-ils dans la porte ?** *Section 7.3, les trois issues.* Si oui, la correction s'etale sur
  cinq a sept sprints. Si non, une correction de mecanique est livree sans etre jouee par
  `nadia-prof`.
- **Q3 - la regle porte-t-elle aussi sur ce que `nadia-prof` *constate* sur un jeu existant, ou
  seulement sur ce qu'elle *valide* et *reteste* ?** *Ma lecture, section 7.2 : seulement valider et
  retester ; constater et router reste ouvert.* Si l'utilisatrice voulait fermer aussi le constat, la
  reserve 5 elargie du 2026-08-30 n'aurait pas pu etre ecrite - et c'est elle qui a nomme le defaut
  de score que `DEC-0061` tranche.

---

## 9. Application et routage - qui porte quoi

**Une decision qui implique un changement sans proprietaire nomme n'est pas prise** (`DEC-0009`, s04,
et la lecon qui en est tiree). Voici les proprietaires.

| Ce qui doit exister | Proprietaire | Etat |
|---|---|---|
| **La clause opposable de la porte pedagogique**, dans les mots de celle qui la tient | `nadia-prof` | **Due.** Elle lit ce document et ecrit sa clause. Je ne la pre-redige pas et je ne l'anticipe pas |
| **Amendement de `squad/README.md`, section 7 (le double Done)** : le perimetre de la porte pedagogique y est decrit sans borne | `sofia-sm` | **Route par ce document.** C'est le fichier de process, il est a elle ; je ne reecris pas le document d'un autre agent |
| **Aucune fiche de conformite ouverte pour un jeu non modifie** | `camille-po` (moi) | **Applique des maintenant.** Aucune fiche n'est ouverte a s24 hors des trois paquets neufs et de `S24-SAI-2` |
| **La cloture de s24 dit si l'invariance de rendu des 27 jeux est mesuree ou attendue** | `theo-archi`, porte par `sofia-sm` | **Route.** Une phrase avec sa methode. Pas de lot, pas de story, perimetre inchange |
| **Le compte de charge d'audit d'un sprint se fait en elements neufs ou modifies** | `sofia-sm` | **Opposable des le planning de s25** |
| **L'obstacle 2 n'est pas classe sans suite par cette regle** | `camille-po` (moi) | **Fait** : `DEC-0061` |
| **Les trois questions de la section 8** | l'utilisatrice, par `sofia-sm` | **Posees.** Aucune ne bloque s24 |

---

## 10. Ce que cette decision ne fait pas

- Elle **ne prononce aucun Done pedagogique** et n'en anticipe aucun.
- Elle **ne rouvre aucun verdict** deja ecrit dans une fiche de conformite.
- Elle **ne ferme aucune reserve** de `nadia-prof`.
- Elle **ne tranche rien de technique** : le fait de savoir si un retypage laisse un rendu inchange
  est a `theo-archi`, et la correction de `M5` est a lui.
- Elle **ne rouvre pas le perimetre de s24**, gele par `DEC-0059`.
- Elle **ne durcit ni n'elargit** les deux lignes de l'utilisatrice. Partout ou elle a du choisir,
  elle a ecrit qu'elle choisissait, au nom de qui, et ce que l'autre lecture aurait donne.

---

## Amendement du 2026-08-31 (meme jour) - l'utilisatrice repond aux six questions, et l'une d'elles renverse mon critere

### A.1 Ses reponses, recopiees telles quelles

Six reponses, dans l'ordre ou elle les a ecrites. **Recopiees mot pour mot, sans reformulation** -
c'est la discipline que la section 1 de ce document s'impose, et elle vaut aussi pour ce qui me
donne tort.

| # | Sa reponse, verbatim | Ce qu'elle repond |
|---|---|---|
| `Q1` | *"tout changement du code"* | La maille de "modifie" (section 8 de ce document) |
| `Q2` | *"1 ils rentrent"* | Le sort des 27 jeux sous une correction partagee (section 8, issue 1 de la section 7.3) |
| `Q3` | *"constat est ouvert"* | Le constat est-il ferme comme la validation (section 8) |
| `Q4` | *"oui"* | Nommer le canal d'animation dans le depot (`DEC-0062`, section 6) |
| `Q5` | *"MASTER"* | Le nom du canal (`DEC-0062`, section 6) |
| `Q6` | *"oui"* | La borne *"un enonce de forme 3 ne vaut jamais motif"* (`DEC-0062`, section 6) |

**Une precision de provenance, et elle applique la convention que `DEC-0062` propose** : la
numerotation `Q4` a `Q6` n'est pas de moi - `DEC-0062`, section 6, posait trois points sans les
numeroter. Elle m'est parvenue **de MASTER, sans source dans le depot**. La correspondance
ci-dessus est celle que MASTER donne ; je la reprends parce qu'elle est univoque sur les trois
points de `DEC-0062`, et je le signale plutot que de laisser croire que j'avais numerote.

### A.2 Ce qui est renverse - `Q1`, et c'est mon propre critere

**J'avais propose que "modifie" se lise sur ce que l'enfant recoit. Elle a choisi l'autre lecture.**

> **Le critere du produit est desormais : un jeu est *modifie* quand le code qui produit son ecran
> change.** L'effet de ce changement sur ce que l'enfant voit n'entre pas dans la qualification.

**Ce que j'ai marque sans l'effacer** : la section 6.2 porte l'avertissement de renversement, et le
paragraphe de la section 6.3 qui affirmait que les 27 jeux restaient hors de la porte a s24 est
marque caduc. Les deux textes restent lisibles a leur place. **Un dossier ou l'on efface ce qui
avait ete propose avant d'etre corrige ne permet plus de mesurer l'ecart, et c'est l'ecart qui
enseigne.**

**Ce que je ne fais pas dire a sa reponse.** L'option qu'elle designe portait, dans `Q1`, le texte
complet *"tout changement du code qui produit son ecran"*. **Je la lis comme designant cette
option-la, et pas comme un "tout code, sans distinction".** Consequence : **le code de test ne
produit aucun ecran d'enfant** ; un garde-fou modifie dans `src/test` ne rend donc aucun jeu
modifie. Je resous cette residuelle dans le sens etroit **pour ne rien bloquer**, je la signale
plutot que de la taire, et si l'utilisatrice voulait dire "tout code y compris les tests", il suffit
d'un mot pour que la ligne bouge - le cout, lui, changerait beaucoup.

### A.3 Le point que MASTER me demande d'instruire : une invariance de rendu mesuree changerait-elle quelque chose ?

**Sur la qualification : non, et la lecture de MASTER est la bonne.** Le critere retenu par
l'utilisatrice porte **sur le code, pas sur son effet**. Mesurer que les 27 jeux rendent exactement
la meme chose apres les lots 1 et 3 ne rend pas leur code inchange. **Les 27 sont modifies, mesure
ou pas.** C'est precisement ce qui distingue la lecture qu'elle a choisie de celle que je proposais :
la mienne interrogeait l'effet, la sienne constate le geste. Une regle qui constate un geste est plus
simple a tenir et ne se discute pas ; c'est probablement pourquoi elle l'a prise.

**Sur le cout : oui, et lourdement.** Une invariance de rendu mesuree ne change pas *si* les 27 jeux
rentrent dans la porte, elle change **ce que leur reaudit coute a `nadia-prof`**. Rejouer 27 jeux
sans rien savoir de ce qui a bouge n'est pas le meme travail que les rejouer avec la preuve qu'aucun
de leurs ecrans n'a change. **La phrase que j'avais routee a `theo-archi` comme une formalite de
cloture cesse d'en etre une** : elle devient l'entree qui dimensionne la file de reaudit.

**Je releve donc son rang, et je ne demande pas de travail neuf** : ce que je demande a
`theo-archi`, par `sofia-sm`, c'est de dire **si une telle mesure existe deja ou se derive de ce que
les lots produisent de toute facon** - et sinon, de le dire aussi. **Si l'etablir demandait un lot,
la decision de le prendre est de capacite, donc de `sofia-sm`, et je ne la prends pas.** Le
perimetre de s24 n'est pas rouvert par cette demande.

**Ce que je ne tranche pas non plus** : l'enumeration exacte des lots de s24 qui touchent du code
producteur d'ecran. Les lots 1 et 3 en sont, cela ne fait aucun doute - `domain`, `adapter-content`,
`bootstrap` et `adapter-web` en production. Pour les lots 2, 4 et 5b, la frontiere entre production
et test se lit dans l'arbre, pas dans ma tete : **l'enumeration est de `theo-archi`.** Le critere,
lui, est ci-dessus et il est ferme.

### A.4 Ce qui est confirme

- **`Q3` confirme ma lecture de la section 7.2** : *"constat est ouvert"*. `nadia-prof` peut
  constater et router un fait sur un jeu existant sans que ce soit valider ni retester. **C'est la
  reponse qui maintient en vie la reserve 5 elargie**, et donc `DEC-0061`.
- **`Q2` confirme l'issue 1 de la section 7.3** : *"1 ils rentrent"*. J'avais ecrit que cette issue
  *"rendrait la correction impraticable"* ; **elle l'a choisie en connaissance de cette phrase**, ce
  qui est exactement le regime que je demandais - un cout paye les yeux ouverts, pas decouvert.
  `DEC-0061` en tient compte par son amendement du meme jour.
- **`Q4`, `Q5` et `Q6`** ferment la section 6 de `DEC-0062`, qui passe de `Proposee` a `Acceptee`.

### A.5 Ce que cet amendement declenche, et qui le porte

**Une decision qui implique un changement sans proprietaire nomme n'est pas prise.** `Q1` implique un
changement lourd sur la cloture de s24 : **les 27 jeux du catalogue deviennent modifies**, contre une
borne d'audit de 4 a 5 paquets par sprint.

**Ce cout est instruit dans `DEC-0063`**, ecrite dans le meme geste que cet amendement, et route a
ses trois proprietaires - `camille-po` pour le perimetre et la matrice, `sofia-sm` pour le
sequencement et la capacite, `nadia-prof` pour ce qu'elle peut reellement porter. **Il n'est ni
tranche seul, ni laisse sans proprietaire.**

**Ce que cet amendement ne fait pas** : il ne rouvre pas le perimetre de s24, ne declare aucune
bascule, ne prononce aucun Done, ne touche aucune ligne de matrice par lui-meme, et ne demande a
aucun developpeur de s24 de changer quoi que ce soit a ce qu'il fait aujourd'hui.
