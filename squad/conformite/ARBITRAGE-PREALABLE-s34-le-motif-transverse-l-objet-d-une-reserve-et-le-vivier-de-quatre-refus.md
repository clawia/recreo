# Arbitrage prealable de s34 - le motif transverse et ce que je change a ma facon d'ecrire une
# reserve, les quatre arbitrages que je devais, et le vivier que quatre refus laissent a s34

- **Auteur** : `nadia-prof`, professeure des ecoles, **seule autorite sur le Done pedagogique**.
- **Date** : 2026-09-10.
- **Arbre** : `round-two` a `48635ba0`, s33 clos et commite, rien n'est pousse. Jar reconstruit
  depuis un worktree dedie, application lancee sur un port local, **arretee par son identifiant de
  processus** a la fin de mes parties - pas par un nom d'image, et pas sur le worktree partage
  (l'hygiene de poste que `yanis-qa` a signalee a s33).
- **Ce document est un arbitrage prealable.** Il ne prononce aucun Done pedagogique, ne rejuge aucun
  verdict de s33, n'ecrit pas la matrice, n'ouvre aucun perimetre, n'ecrit aucune story et n'affecte
  personne. Il dit ce qui fermerait, ce que cela coute, ce que je refuse d'avance, ce dont je reste
  la seule garde - et, cette fois, **comment j'ecris une reserve**.
- **Il ne redige pas le `DEC`.** `camille-po` le fera apres moi ; les chiffres de ce document sont a
  reprendre, pas a recalculer.
- **Le Plan 2 est acquis et n'est pas rouvert ici.** `DEC-0083` l'a retenu pour s32 a s34 et
  `DEC-0085` l'a reconduit sans reouverture. **s34 est le dernier sprint de sa fenetre**, et ce
  document l'execute plutot que de le rediscuter.
- **Son vrai sujet est la section 3.** Les sections 5 a 8 chiffrent un vivier ; la section 3 dit
  pourquoi le chiffre de s33 n'a pas ete atteint, et ce que je change chez moi pour que celui de s34
  le soit. **Toutes les reserves des sections 4 et 5 sont ecrites au format que la section 3
  etablit.**

---

## 0. Ce que j'ai joue aujourd'hui, et ce que je n'ai pas pu voir

**Aucun increment de s34 n'existe : le sprint n'est pas ouvert.** Ce que j'ai joue, c'est l'etat
servi par l'arbre de cloture de s33.

| Ce que j'ai joue | Ce que j'y ai verifie |
|---|---|
| `bandit-manchot-regles-securite-eps-cp`, **partie complete**, chaque proposition pressee dans l'ordre affiche | les deux items de `SEC-R4` a l'ecran ; les quatre indices de `SEC-R5` ; le contenu que la reprise a paye et que je ne redemanderai pas |
| `bandit-manchot-regles-jeu-collectif-eps-cp`, **partie complete**, plus le chemin d'echec complet sur l'item de l'epervier | `JEU-R9` a l'ecran : *"Malo change de role"* est bien marquee fausse ; l'indice servi apres le premier echec ; le fait que l'item 3 dit le contraire |
| `bandit-manchot-exclusion-cp`, **partie complete** | les cinq items de la tache B, un par un : `EXC-R7`, `EXC-R8` et `EXC-R9` **verifies a l'ecran et non lus dans le fichier** ; la consigne, servie sur les dix manches, qui annonce une tache que le paquet ne sert plus |
| `bandit-manchot-regle-souhait-loi-cp`, **partie complete** | la forme binaire de la tache A ; le point 2 du troisieme refus paye ; l'item 4 |
| `bandit-manchot-histoire-geographie-cm1`, **cinq parties completes**, 50 manches, **30 items distincts releves** | la consigne a cinq clauses servie sur chaque manche ; un item de `HG-CM1-12` et ses deux distracteurs ; le compte de la section 4.2 |
| `bandit-manchot-permis-interdit-cp`, `bandit-manchot-regles-de-vie-cp`, **une partie complete chacune** | la mesure de score de la section 4.1 |

**Audit degrade sur un point, et je le declare** : j'ai joue par requetes HTTP reelles, donc j'ai lu
le **HTML servi**, pas la page rendue par un navigateur. Cela couvre les textes, l'ordre des ecrans,
l'enchainement apres une erreur et apres trois, le score et l'ecran de fin. Cela **ne** couvre ni la
couleur, ni le contraste, ni la taille, ni la position a l'ecran. **Aucune conclusion de ce document
ne repose sur un jugement visuel.**

**Ce que je n'ai pas re-verifie et que je ne suppose pas** : la loi de tirage. Je donne des
proportions d'items servis, jamais une probabilite de tirage - j'ai deja enregistre un tirage comme
uniforme alors qu'il ne l'etait pas, et je ne recommence pas.

**Ce que je n'ai pas joue** : les soixante et une autres entrees du catalogue, et le lot de l'effort
et du corps, qui n'existe pas.

---

## 1. L'etat, repris et non recalcule

**69 `couvert-valide`, 1 `en cours`, 68 `non couvert`, 71 `non couvrable en l'etat`.** 209 lignes au
total, **denominateur atteignable 138**, **69 / 138 = 50,0 %**.

**Ce compte n'est pas de moi.** Il est recompte sur les dix-huit tableaux de
`squad/programme/MATRICE-couverture.md` par `sofia-sm` a la review de s33, et confirme par la
synthese de cloture. **Je ne le recalcule pas et je ne touche pas la matrice.**

**Les quatre lignes que s33 a refusees, et leur statut exact** - il faut le dire parce que trois
d'entre elles ne sont **pas** `en cours` :

| Ligne | Libelle | Statut | Motif bloquant |
|---|---|---|---|
| `HG-CM1-12` | Decrire comment on se loge, on travaille, on se cultive et on se deplace | **`en cours`** | `HG-R17` |
| `EMC-CP-07` | Distinguer une regle qui vaut pour tous d'un simple souhait, et reconnaitre qu'une loi s'impose a tous | **`non couvert`**, quatrieme refus | `EMC-11` |
| `EPS-CP-04` | Connaitre et respecter les regles de securite | **`non couvert`**, second refus | `SEC-R4` |
| `EPS-CP-08` | Connaitre et respecter les regles d'un jeu collectif | **`non couvert`**, second refus | `JEU-R9` |

**Les quatre paquets existent, sont servis, et sont jouables aujourd'hui.** C'est ce qui fait le
vivier de s34, et c'est aussi ce qui fait que ce vivier n'est pas une bonne nouvelle : **quatre
lignes sur quatre ont deja ete payees une fois, deux d'entre elles trois et quatre fois.**

---

## 2. Corrections a mes propres constats, avant de m'en servir

**Je verifie ce que j'ai ecrit avant de m'appuyer dessus.** Quatre constats de moi sont faux,
perimes ou incomplets. **Trois vont contre moi.**

### 2.1 `PAR-14`, que j'ai ecrite le 2026-09-09, est elle-meme un cas du defaut que ce document traite

`PAR-14` dit : *"une reprise dont les corrections touchent **plus de la moitie des items** d'un
paquet se paie au tarif d'un lot"*. **C'est un remede ecrit a la place d'un objet.** L'objet que
j'avais en tete, et que le paragraphe de motif dit d'ailleurs en toutes lettres, est : *"quand
l'audit precedent ne couvre plus rien, ce n'est pas un rejeu, c'est un second premier passage"*.

**Le comptage que j'ai mis a la place ne rend pas cet objet, et j'en ai la mesure.** Sur
`cp-emc-exclusion`, les trois reserves neuves de s33 demandent de reecrire **l'integralite de la
tache B** - les cinq enonces, leurs quinze propositions - **plus la consigne du catalogue**. Compte
sur le fichier servi : **5 items sur 11.** *Cinq sur onze n'est pas plus de la moitie de onze.* La
lettre de `PAR-14` dit 0,5 ; son objet dit qu'il ne reste rien de l'audit precedent sur cette tache.

> **Je ne durcis pas `PAR-14` et je ne la reinterprete pas.** Elle reste exactement ce qu'elle dit,
> elle reste non retroactive, et sur `cp-emc-exclusion` sa lettre ne mord pas. **J'ecris une clause
> neuve et datee, `PAR-15`** (section 6.1), qui porte le second declencheur. C'est la regle que la
> section 3 etablit, appliquee d'abord a moi.

### 2.2 Mon "0 a 1" a ete lu comme un pile ou face, et il ne l'est pas

**Ma table d'attente de s33** donnait `1` a deux lignes et `0 a 1` a cinq. **Resultat mesure** :
les deux `1` ont ferme, **une seule des cinq `0 a 1`** a ferme.

> **Je corrige la lecture de ma propre notation, datee du 2026-09-10.** *"0 a 1"* dans mes tables ne
> veut pas dire *"une chance sur deux"* : sur la seule population ou je l'ai mesuree, il vaut **une
> sur cinq**. Je continue de l'ecrire ainsi parce que je ne sais pas mieux quantifier une ligne
> unique, **mais je dis desormais de quel cote il penche**, et les sommes de la section 8 en tiennent
> compte.

### 2.3 Mon exemple du referentiel d'EPS CP etait fautif, et je l'ai corrige dans ce meme diff

L'instance C l'a etabli le 2026-09-10 : *"Ou regarde-t-on avant de traverser en trottinette ?"*, que
j'ai ecrit le 2026-09-03 dans `squad/programme/education-physique-et-sportive/competences-cp.md` et
repris mot pour mot dans `SEC-R1`, **pose le geste au mauvais endroit de la regle**. Un enfant de six
ans descend de sa trottinette et la pousse.

> **Corrige dans ce meme diff**, avec une note datee du 2026-09-10 qui garde l'ancien exemple
> lisible et dit ce qu'il a coute. **Le second exemple de la ligne 4 est desormais *"Pourquoi
> attend-on que le toboggan soit libre avant de glisser ?"***, et je dis la verification que j'ai
> faite avant de l'ecrire : le geste que la question nomme - attendre - **est le geste reel de la
> regle**, et il n'y en a pas un autre avant lui. **C'est `RES-2`, appliquee au premier texte que
> j'ecrivais apres l'avoir ecrite.**

### 2.4 Le second exemple du referentiel, ligne 10, porte un piege que je n'avais pas vu

Ligne 10 du meme referentiel, ecrite par moi le 2026-09-03 : *"Apres avoir couru longtemps, que
ressent-on ?"*. **Le lot de l'effort et du corps est le prochain a l'executer**, et je l'ai donc
verifie avant qu'il ne le fasse plutot qu'apres.

**L'exemple est exact et il tient `BIA-4` et `EPS-3`** - il interroge le corps en general, pas celui
de l'enfant, et la bonne reponse est une sensation, pas une conduite. **Ce qu'il porte de dangereux
n'est pas dans la question, c'est dans les distracteurs qu'il appelle** : *"on a froid"* est un
distracteur naturel **et il est vrai** - on a froid apres l'effort, quand la sueur s'evapore, pas
pendant. Un item qui ne fixe pas le moment marque fausse une proposition vraie, ce qui est le motif
qui a refuse `EMC-CP-07` trois fois.

> **Je ne retire pas l'exemple** : il est bon. **J'ecris le refus d'avance qu'il appelle**
> (section 10, refus 8) : *un item du lot du corps fixe le moment de l'effort dont il parle -
> pendant, juste apres, plus tard - et aucune de ses propositions n'est vraie a un autre moment.*
> **C'est un refus neuf et date, pas un durcissement d'`EPS-2` ni d'`EPS-3`.**

---

## 3. Le motif transverse - ce que je change a ma facon d'ecrire une reserve

**C'est le vrai sujet de cet arbitrage, et il porte sur moi.**

### 3.1 Le fait, mesure sur s33, et ce qui separe les corrections qui ont porte de celles qui n'ont pas porte

A s33, les trois instances ont chacune trouve un defaut dans une consigne que j'avais ecrite
moi-meme et qu'un developpeur a appliquee a la lettre. **Ce ne sont pas trois incidents : c'est un
motif, et il se lit sur la population entiere des reserves que s33 a payees.**

*Methode : les neuf reserves ou motifs dont s33 a mesure l'execution, relus un par un dans les trois
verdicts et verifies a l'ecran pour ceux des paquets que j'ai rejoues aujourd'hui. Ce sont les neuf
que je peux documenter, pas un echantillon.*

| Ce que j'avais ecrit | Forme de mon ecrit | Ce que l'execution a rendu |
|---|---|---|
| `SEC-R3` - *les trois propositions nomment un danger, une blessure ou une consequence, dont deux fausses* | **propriete** | **levee proprement**, aucun defaut neuf |
| `HG-R12` - *aucune bonne reponse ne designe un groupe par un exonyme qu'aucun groupe ne revendique*, deux directions nommees | **propriete, plus deux directions** | **levee proprement**, la ligne ferme |
| `JEU-R1` - *toucher un joueur perche n'est pas une infraction* | **propriete** | **levee**, et la correction a rendu **trois items** coherents entre eux |
| `JEU-R7` - *la tache A enseigne la regle avant que la tache B ne la juge* | **propriete** | **levee**, et c'est le seul geste de paquet du sprint |
| `SEC-R2` - la formulation de l'indice de l'item 1 | **texte dicte**, mais **ecrit pour l'item ou il allait** | **levee proprement.** C'est le contre-exemple, et il dit ou passe la frontiere |
| `HG-R11` - *reprendre la formulation que l'item 66 porte deja* | **texte dicte**, jamais recolle a la question d'arrivee | levee, **et `HG-R13` neuve** : une proposition verbale dans une liste de groupes nominaux |
| `EPS-7` - *aucun indice ne nomme le champ lexical qui distingue la bonne reponse* | **condition de retrait seul** | tenue a la lettre, **et `SEC-R5` neuve** : trois indices sur quatre n'enseignent plus rien |
| `SEC-R1` - l'exemple de la trottinette | **exemple non verifie** | applique tel quel, **et `SEC-R4`, refus bloquant** |
| `EXC-R5` - *que la question dise quel adulte est la* | **formule dictee**, jamais recollee a ses propositions | levee, **et `EXC-R7`, `EXC-R8`, `EXC-R9` neuves**, increment refuse |
| Mon chiffrage de `EMC-CP-07`, *"aucun item neuf"* | **perimetre mesure sur l'etat de depart** | a fait declarer infaisable une sortie qui ne l'etait que dans mon perimetre. **Un sprint perdu, quatrieme refus** |

**Ce que cette table montre, et ce n'est pas ce que j'attendais.** Ce n'est pas *"les developpeurs
executent mal"* - dans les dix cas l'execution est fidele. Ce n'est pas non plus *"il ne faut jamais
dicter un texte"* : `SEC-R2` est un texte dicte et il a porte. **Ce qui separe les deux moities de
la table est que j'aie ou non ecrit ce que la correction doit rendre a l'enfant** :

- quand j'ecris **la propriete que l'item doit tenir**, l'execution la tient et l'ajuste aux items
  qu'elle rencontre ;
- quand j'ecris **le geste** - ce mot-la, cet exemple-la, ces quatre items-la - l'execution fait le
  geste, exactement, et l'objet disparait. **La langue tient ; c'est le sens qui se vide.**

`SEC-R2` porte parce que le texte que j'ai dicte, je l'avais **ecrit pour la place qu'il allait
occuper** : je l'ai lu apres l'echec qu'il suit, dans l'item ou il vit. `HG-R11` et `EXC-R5` ne
portent pas parce que j'ai dicte un texte pris ailleurs, ou une formule generale, **sans jamais les
recoller a leur place**.

**Et le cas qui a coute le plus n'est pas un texte du tout** : *"aucun item neuf"* est un
**chiffrage**, mesure sur l'etat de depart du paquet et jamais sur son etat d'arrivee. La sortie que
je prescrivais faisait passer le paquet a six items binaires sur onze, ce que `BIN-1` refuse, **et
rien dans le depot ne verifie qu'un prix est compatible avec la condition qu'il paie.**

**Ce que cela coute, chiffre plutot qu'evoque** : un sprint entier sur `EMC-CP-07`, deux refus
bloquants (`SEC-R4`, `EXC-R9` par l'increment qu'elle refuse), une reserve neuve sur une ligne qui
venait de fermer (`HG-R13`), un exemple faux dans un referentiel. **A s33, au moins 4,0 sur 11,5 sont
restes inemployes ; ce n'est donc pas mon budget qui plafonne la velocite, c'est ceci.**

### 3.2 `RES-1` - ce qu'une reserve porte desormais, et dans cet ordre

> **`RES-1` - nouvelle, datee du 2026-09-10.** **Toute reserve, tout motif de refus et toute
> condition opposable que j'ecris porte cinq parties, dans cet ordre. Une reserve qui n'en porte que
> trois est incomplete, et c'est mon defaut, pas celui de qui la lit.**
>
> 1. **Le constat.** Ce que j'ai vu, ou, sur quel item, avec le texte exact que j'ai lu a l'ecran,
>    date. *(Deja fait, et bien fait. Rien ne change ici.)*
> 2. **L'objet.** **Ce que l'enfant doit pouvoir faire, ou cesser de pouvoir faire, une fois la
>    reserve payee** - ecrit en termes d'enfant devant l'ecran, jamais en termes de texte a changer.
>    **C'est la partie qui manquait, et c'est elle qui rend la reserve executable au bon niveau.**
> 3. **Le mode de defaillance.** **La correction qui satisferait ma phrase et manquerait mon objet,
>    nommee par moi, d'avance.** Je suis la seule a la connaitre, puisque c'est moi qui ai vu le
>    defaut. La taire et la reprocher ensuite serait deplacer un poteau.
> 4. **Le perimetre, mesure sur l'etat d'arrivee.** Combien d'items, lesquels, comptes sur le fichier
>    servi, **avec les conditions que le paquet devra encore satisfaire une fois la correction
>    faite** - et ce que je n'ai pas compte, dit comme non compte.
> 5. **Le regime et le prix.** Inchange.
>
> **Ce que la partie 3 n'est pas** : ce n'est pas une exigence de plus. **Nommer un mode de
> defaillance n'ajoute aucune condition** : une correction qui l'evite ne doit rien de plus, et une
> correction qui y tombe manque un objet qui etait deja ecrit au-dessus.

### 3.3 `RES-2` - ce que je m'interdis d'ecrire

> **`RES-2` - nouvelle, datee du 2026-09-10.** **Quatre interdits, chacun tire d'un cas mesure de
> s33 et non d'un principe.**
>
> 1. **Je ne dicte pas un texte que je n'ai pas recolle a la place qu'il occupera.** Recoller veut
>    dire : lire la question avec ce texte en proposition, ou lire cet indice apres l'echec qu'il
>    suit, ou lire cet enonce avec les trois propositions qu'il annonce. **Si je l'ai recolle, je le
>    dicte et je dis que je l'ai recolle. Sinon j'ecris l'objet et je ne fournis pas le texte.**
>    *(Fonde sur `HG-R11` et `EXC-R5` d'un cote, `SEC-R2` de l'autre.)*
> 2. **Je n'ecris aucun exemple que je n'ai pas verifie contre le geste ou le fait reel qu'il
>    decrit** - dans une reserve comme dans un referentiel. Si je ne peux pas le verifier, je nomme
>    la connaissance visee sans en fabriquer l'enonce. *(Fonde sur `SEC-R1`, et applique en
>    section 2.3.)*
> 3. **Je ne chiffre pas un perimetre sur l'etat de depart d'un paquet.** Un chiffrage nomme les
>    conditions que l'**etat d'arrivee** doit encore satisfaire, et il est mesure sur cet etat-la.
>    *(Fonde sur `EMC-CP-07`, ou `BIN-1` mesuree sur l'arrivee rendait le perimetre faux le jour de
>    sa publication.)*
> 4. **Je n'ecris pas de condition de retrait seul.** Une condition qui interdit qu'un element soit
>    la dit ce qui doit occuper sa place. *(Fonde sur `EPS-7`, dont `EPS-8` est deja la reparation -
>    et `EPS-8` est le patron de ce que `RES-2` generalise.)*

### 3.4 `RES-3` - comment un developpeur ou un relecteur detecte qu'une correction a suivi la lettre et rate l'objet

**Ce controle n'est pas un test, et je le dis avant de le decrire pour que personne ne le prenne
pour un garde-fou.** Ce sont **trois lectures, sur le diff, avant de commiter**. Chacune a un cas de
s33 qui la justifie, et chacune aurait trouve ce cas.

> **`RES-3` - nouvelle, datee du 2026-09-10. Le controle d'objet, en trois lectures.**
>
> 1. **Relire l'objet, pas le remede.** Reprendre la partie *objet* de la reserve - jamais son
>    identifiant, jamais sa phrase de remede - et demander : *le diff rend-il cela a l'enfant ?*
>    **Si la reserve ne porte pas d'objet ecrit, la correction n'est pas engageable et la reserve me
>    revient** (`RES-4`). *Aurait trouve : `SEC-R5` - le mot interdit est parti, le fait qu'il
>    portait n'a pas ete remplace.*
> 2. **Recoller.** Chaque texte touche est relu **a sa place** : la question recollee a chacune de
>    ses trois propositions, l'indice lu apres l'echec qu'il suit, l'enonce lu avec les propositions
>    qu'il annonce. **Un texte juste isole peut etre faux recolle.** *Aurait trouve : `EXC-R8` (la
>    question a change, les propositions ont garde la forme de l'ancienne), `HG-R13`, et l'element 1
>    de `SEC-R4` (le mot ajoute a l'enonce, les propositions laissees a un pieton) - **trois des
>    quatre defauts que s33 a trouves a l'audit**.*
> 3. **Relire le paquet, pas l'item.** Tout autre item du paquet qui parle du meme objet - le meme
>    jeu, la meme regle, la meme categorie - est relu avec l'item corrige. *Aurait trouve : `JEU-R9`
>    (les items 3 et 8 disent le contraire du meme jeu, a cinq items d'intervalle) et `EXC-R9` (deux
>    stories du meme commit se defont l'une l'autre).* **C'est l'amendement que la retro de s33 a
>    porte a `squad/README.md`, section 4 ; je le reprends au lieu d'en ecrire un second.**

**Ce que ce controle coute** : trois lectures sur les seuls textes que le diff touche. Sur les cinq
reprises que s34 peut porter, cela represente une vingtaine d'items, pas un paquet.

### 3.5 `RES-4` - la seule part de tout ceci qui soit opposable et executable, et je la route

**Je dis d'abord ce qui n'est pas executable, parce que c'est la part la plus grande.** Comparer un
diff a une intention ecrite en prose n'est pas calculable : aucun automate ne lit un objet.
`yanis-qa` et `theo-archi` l'ont ecrit tous les deux a s33 et je le confirme. **Ce qui remplace un
garde-fou ici, ce sont les trois lectures de `RES-3`, faites par une personne.**

**Une seule chose est mecanique, et c'est celle qui mord sur moi.**

> **`RES-4` - nouvelle, datee du 2026-09-10. Une reserve sans objet ecrit n'est pas engageable.**
> Le developpeur qui la recoit ne la corrige pas et me la renvoie ; le sprint ne la compte pas comme
> due ; et le defaut est le mien, pas le sien.
>
> **La propriete que je route a `theo-archi`, sans dire la forme du test ni poser de seuil** : *dans
> une fiche de conformite, toute reserve porte un objet distinct de son remede.* Les fiches ont une
> forme reguliere, et le depot sait deja garder la forme d'un document - `ADR-0085` le fait sur le
> registre d'identifiants, `ADR-0088` sur la forme d'une allocation. **C'est le meme genre de
> garde-fou, et c'est le seul de tout ce depot qui rougirait sur un manquement de la porte
> pedagogique elle-meme.** Je le demande pour cette raison : une porte qui n'est gardee par rien
> finit par produire le defaut que ce document decrit.
>
> **Ce que je ne demande pas** : qu'un automate juge la qualite d'un objet. Il ne peut verifier que
> sa presence. **La presence est deja ce qui manquait dans les quatre cas de s33.**

### 3.6 Ce que ce changement ne fait pas

- **Il ne rejuge aucun verdict et ne leve aucune reserve.** `HG-R17`, `SEC-R4`, `JEU-R9`, `EMC-11`,
  `EXC-R7` a `EXC-R9`, `REG-R4`, `REG-R5`, `PER-R3` restent exactement ce que les trois instances ont
  ecrit le 2026-09-10.
- **Il n'est pas retroactif.** Les reserves ecrites avant le 2026-09-10 ne deviennent pas
  non engageables faute d'objet : `RES-4` vaut pour celles que j'ecris a partir d'aujourd'hui. **Ce
  que je fais pour les anciennes est en section 5 : je les reexprime au format, sans les durcir.**
- **Il ne durcit aucune barre de Done.** `RES-1` a `RES-4` portent sur **ce que j'ecris**, pas sur ce
  qu'un increment doit atteindre. Aucun lot ne devient plus difficile a valider ; c'est moi qui
  deviens plus difficile a satisfaire par un texte vide.
- **Il ne fait pas de moi une ecrivaine de contenu.** Le sens de `RES-2` est l'inverse : **j'ecris
  moins de texte qu'avant**, et plus d'objet.

---

## 4. Les quatre arbitrages que je devais, rendus ici

**Ce sont des dettes de ma propre porte, et trois d'entre elles bloquent quelqu'un d'autre.**

### 4.1 L'instruction 1 de `DEC-0061` - **famille A**, et voici la phrase que `theo-archi` attend

**Ouverte depuis neuf sprints, a mon seul debit. Je la rends aujourd'hui.**

**Ce que j'ai mesure moi-meme avant de trancher**, plutot que de reprendre un chiffre. *Methode :
cinq parties completes de dix manches sur cinq paquets differents, en pressant les propositions dans
l'ordre affiche et sans lire les enonces. Arbre `48635ba0`, jar de mon worktree.*

| Paquet | Essais depenses, manche par manche | Score lu par l'enfant a la fin |
|---|---|---|
| `histoire-geographie-cm1` | 3, 2, 2, 3, 3, 2, 3, 3, 2, 1 | **10** |
| `regle-souhait-loi-cp` | 1, 3, 2, 2, 2, 1, 3, 1, 2, 1 | **10** |
| `permis-interdit-cp` | 3, 3, 2, 3, 2, 1, 3, 2, 2, 2 | **10** |
| `regles-de-vie-cp` | 1, 1, 2, 1, 2, 3, 1, 2, 1, 3 | **10** |
| `regles-jeu-collectif-eps-cp` | 2, 3, 1, 2, 2, 1, 1, 1, 3, 2 | **10** |

> **Quinze manches sur cinquante sont trouvees du premier appui. L'enfant lit *"Score : 10"* cinq
> fois sur cinq.** C'est la cinquieme mesure de ce defaut et la premiere sur cinq paquets a la fois.

**Ma reponse, et c'est la phrase que `theo-archi` demande, ecrite pour etre citee telle quelle :**

> **Quand l'elimination est possible, l'enfant garde ses trois essais, et le score cesse de compter
> comme trouvee une manche qu'il n'a pas trouvee du premier coup.**
>
> **C'est la famille A. Datee du 2026-09-10, instruction 1 de `DEC-0061` rendue.**

**Quatre motifs, dans l'ordre de leur poids.**

1. **L'indice est le moment ou cette mecanique enseigne, et la famille B le rend inutilisable.**
   `M1` fait arriver l'indice **des le premier echec**. Je l'ai verifie aujourd'hui sur l'item de
   l'epervier : premier appui faux, l'indice s'affiche, et il reste deux appuis pour s'en servir.
   **Sous la famille B, sur les 114 items du catalogue a deux propositions** (compte de
   `theo-archi` sur `b45e5927`, que je reprends et ne recalcule pas), **l'enfant recevrait l'indice
   et n'aurait plus rien a en faire.** Une regle qui rend l'indice decoratif sur une partie du
   catalogue n'est pas un remede, c'est un second defaut - et elle le serait sur les items les plus
   simples, donc les plus frequents au CP.
2. **Ce qu'un enfant doit pouvoir faire d'une manche ne change pas : la finir en faisant lui-meme le
   geste juste.** C'est `M5` tel que je l'ai arbitre a s10, et **je ne le rouvre pas**. Une reponse
   revelee n'est pas un geste que l'enfant a fait, c'est un geste qu'il a regarde. A six ans comme a
   neuf, la manche doit se terminer sur son appui, pas sur une revelation.
3. **Ce qui doit changer est ce que le produit dit qu'il savait.** *Trois essais* veut dire : **un
   essai qui compte, et deux qui enseignent.** Le score rapporte les premiers ; les autres sont
   l'apprentissage, et l'apprentissage ne se note pas. C'est exactement ce qu'on fait en classe -
   on ne note pas la deuxieme tentative, et on la laisse quand meme avoir lieu.
4. **Et le corollaire que je dois, sans lequel le remede devient une punition.** Un enfant qui a
   trouve quatre questions seul et six apres une erreur **n'a pas *"4 sur 10"***. Ce qu'il doit lire
   a la fin, c'est **ce qu'il a trouve seul et le fait qu'il a fait les dix questions** - deux
   informations, pas une note reduite qui remplace tout. **Je refuse d'avance un ecran de fin qui
   n'afficherait que le nombre reduit** (section 10, refus 6).

**Ce que ma reponse ne demande pas, et je le dis parce que `theo-archi` a nomme le point.** Elle ne
demande **pas** de montrer a l'enfant ce que ses essais lui ont coute. Les deux nombres qu'il doit
lire - combien il a trouve seul, et qu'il a fait les dix questions - existent deja tous les deux.
**Ma decision ne force donc pas la variante `A-2`** ; laquelle des deux formes la sert est a
`theo-archi`, et je ne la choisis pas.

**Ce que je refuse expressement** :

- **la famille B**, pour le motif 1 ;
- **la troisieme voie** - afficher les essais a cote d'un score qui reste parfait. `theo-archi` a
  ecrit qu'elle ne paie pas `SCO-3` ; je le confirme du cote pedagogique, et pour un motif qui lui
  est propre : **un enfant qui lit *"10"* ne lit pas la ligne d'a cote.** Le chiffre du haut est le
  seul qu'il emporte ;
- **le remelange des propositions apres une erreur**, deja nomme par `theo-archi` : il ne defait
  qu'une strategie de position, et il couterait `M5`, `M9` et `M11` pour une propriete qu'il ne rend
  pas.

**Le cout d'audit de ce remede, pour ma porte, et il est petit - je le dis parce qu'un chiffre plus
gros circule.** L'amendement de `DEC-0061` du 2026-08-31 evoque *"cinq a sept sprints d'audit"* pour
les jeux existants. **Ce chiffre a ete ecrit avant que le remede ne soit cerne a trois methodes et
zero item.** Le changement est **identique sur les 66 entrees** et **ne touche aucun contenu** :
ce que j'ai a verifier est **la mecanique une fois**, pas le contenu 66 fois.

> **Mon prix : 0,5.** Ce qu'il achete : une partie complete sur un paquet a trois propositions, une
> sur un paquet portant des items a deux propositions, une sur une entree de `saisie` ; le chemin
> d'echec complet jusqu'a la revelation ; et **l'ecran de fin lu tel que l'enfant le lit** sur les
> trois. **Je ne rouvre ni `DEC-0060` ni l'arbitrage de l'utilisatrice sur le perimetre de la porte**
> - je dis ce que ce changement coute a **mon** budget, ce qui est a moi de dire.

**Ce dont je reste la seule garde ici** : **le texte de l'ecran de fin**, quand il sera ecrit. Le
remede est entierement mordable par un automate (`theo-archi` a la regle et sa population) ; **la
phrase que l'enfant lit ne l'est pas**, et elle revient devant moi.

### 4.2 Les trois questions de grain de `S33-CONS-4`

**Je reponds aux trois. Ma reponse converge sur le principe de `camille-po` - un decoupage par
tache - et corrige son enumeration.**

**Ce que j'ai mesure avant de repondre.** *Methode : cinq parties completes, 50 manches, **30 items
distincts** releves a l'ecran ; chacun classe par le geste que l'enfant fait, pas par la ligne de
referentiel qu'il declare. Puis relecture des 110 enonces du fichier servi pour compter les
populations. Les deux comptages sont de moi et je donne les cas discutables.*

- **Sur les 30 items distincts que j'ai joues, 11 portent une tache qu'aucune des cinq clauses de la
  consigne ne nomme.** Le compte le plus favorable a la consigne, en accordant deux items limites
  (*"Quel chateau de la Renaissance est construit sous Francois Ier ?"*, *"Quelle region porte encore
  leur nom ?"*) aux clauses les plus proches, en laisse **9 sur 30**. **Entre un tiers et trois
  dixiemes, dans les deux comptages.**
- **Les onze sont la meme tache**, sans exception : *l'enonce decrit quelque chose, l'enfant lui
  donne son nom* - le Code civil, le prefet, les lycees, l'aqueduc, le reseau de routes, la colonie,
  la Normandie, l'Empire, le premier consul, les mots de vocabulaire gaulois.

**Reponse a la question 1 - je corrige le regroupement.** *Les cinq familles que `camille-po` nomme
ne sont pas cinq taches : ce sont deux taches et trois sujets.* Pour un enfant de CM1, *"quel peuple
a construit cela ?"*, *"quel roi a fait construire Versailles ?"* et *"comment appelle-t-on ce type
d'espace ?"* sont **la meme question** : on lui decrit quelque chose, il le nomme. Il ne fait pas un
geste different selon que la reponse est un peuple, un roi ou un espace. **Trois consignes pour un
seul geste ne l'informent pas trois fois : elles l'informent une fois, en trois phrases.**

> **Les taches reellement distinctes, sur les 110 items, sont trois :**
>
> 1. **Situer un fait dans le temps, ou remettre des faits dans l'ordre** - le seul geste ou l'enfant
>    manipule une echelle de temps.
> 2. **Nommer ce que l'enonce decrit** - de loin la plus grosse, et celle qu'aucune clause ne nomme
>    aujourd'hui.
> 3. **Lire un document fourni et en tirer une reponse** - le seul geste ou la matiere est devant
>    l'enfant plutot que dans sa memoire.

**Et je propose une quatrieme entree, qui n'est pas une quatrieme tache et que je nomme comme telle
pour qu'on ne me lise pas de travers** : **couper la tache 2 en deux, l'histoire d'un cote, la
geographie de l'autre.** **Ce n'est pas une exigence de `CONS-1`** - une seule entree tiendrait la
propriete. C'est un jugement de professeure, et je le donne en le declarant tel : aujourd'hui une
partie enchaine les Merovingiens, un chateau d'eau et une station de ski. La tache est la meme,
mais l'enfant change de monde a chaque manche. **Je n'ai aucune mesure de ce que cela lui coute**, et
je ne la presente donc pas comme une condition.

**Le decoupage qui en resulte, avec sa population comptee sur le fichier servi :**

| Entree | Tache | Lignes de referentiel servies | Items |
|---|---|---|---|
| Situer dans le temps | 1 | `HG-CM1-01`, `-02`, plus 5 items de `-07` | **25** |
| Nommer, en histoire | 2 | `HG-CM1-03`, `-04`, `-06`, 5 items de `-07`, `-08` | **45** |
| Nommer, en geographie | 2 | `HG-CM1-11`, `-12`, `-13` | **30** |
| Lire un document | 3 | `HG-CM1-14` | **10** |
| | | **11 lignes** | **110** |

**Trois faits qui vont avec ce tableau, donnes parce qu'ils appartiennent a d'autres qu'a moi.**
Les quatre viviers sont **tous a dix items au moins**, donc `DEC-0064` tient a dix manches partout,
ce qui n'etait pas le cas du decoupage ligne par ligne. **Trois entrees de catalogue neuves**, donc
trois fiches et trois Done pedagogiques, ce qui reste dans la fourchette de quatre a cinq que
`camille-po` a chiffree. Et **la repartition d'une poignee d'items entre les entrees 1 et 2 est
discutable** - ceux de `HG-CM1-07` en particulier - **sans que cela change le grain** : les deux
viviers restent tres au-dessus de dix quel que soit l'arbitrage sur ces cinq items.

**Reponse a la question 2 - non, ils n'ouvrent pas de sixieme tache.** *Verifie sur les vingt items
neufs, un par un, dans le fichier servi.* Les dix de `HG-CM1-08` demandent tous de **nommer** ce que
l'enonce decrit (*comment appelle-t-on ce texte, ce representant, cette banque, ces ecoles, cette
prise de pouvoir, ce regime*). Les dix de `HG-CM1-12` aussi (*quel logement, quel transport, quel
appareil, quel hebergement, quel evenement, ou travaillent-ils*). **Vingt sur vingt rejoignent la
tache 2** : `HG-CM1-08` dans l'entree d'histoire, `HG-CM1-12` dans celle de geographie. **Un seul
item du paquet entier sort des trois taches** - *"la densite est-elle plus elevee ou plus faible ?"*,
qui est une comparaison - **et un item sur 110 n'ouvre pas une entree.**

**Reponse a la question 3 - non, je ne retiens la phrase unique sur aucun sous-ensemble.** Le
mecanisme qui a fait grossir la consigne de 12 a 22 puis a 31 mots est qu'elle enumerait des taches
qui n'etaient pas la meme tache. **Une fois les entrees coupees par tache, chaque consigne a une
seule clause et n'a plus de raison de grossir** - une ligne de referentiel neuve rejoint une entree
existante si elle porte sa tache, et l'ouverture d'une tache neuve est alors un evenement visible
qui se decide, au lieu d'une clause de plus. Une phrase unique sur un sous-ensemble reproduirait le
mecanisme en plus petit.

**Ce que je ne fais pas, et c'est `RES-2` appliquee tout de suite** : **je n'ecris aucune des quatre
consignes.** Je n'ai pas lu ces phrases a leur place, devant les items qu'elles annoncent. **Je nomme
la tache de chaque entree ; le texte revient devant moi quand il est ecrit, item devant lui.**

> **Mon prix pour ce decoupage : 1,0.** Ce qu'il achete : **une partie complete par entree neuve**,
> soit quatre, la verification que la consigne de chacune nomme la tache de toutes ses manches, et le
> recomptage que les 110 items sont tous encore servis et qu'aucune ligne de referentiel ne perd
> d'item. **Ce n'est pas 4 x 0,5** : la matiere est un seul paquet et les items ne changent pas.
> **Ce prix ne finance aucune ligne de matrice** : le decoupage n'en ferme aucune et n'en rouvre
> aucune.

### 4.3 Le grain du lot de l'effort et du corps - **confirme, un seul paquet**

`camille-po` a rendu sa position et elle rejoint la mienne. **Je confirme : `EPS-CP-10` et
`EPS-CP-11` sont servies par un seul paquet, a consigne de deux clauses.**

**Le motif est celui que j'avais chiffre et je n'en ajoute pas** : la moitie de dette differee, une
entree de catalogue au lieu de deux, une consigne au lieu de deux, et **aucune surface de couture**
entre deux moities qui portent le meme objet. **Deux lignes voisines sous une consigne a deux
clauses ne sont pas le cas de `CONS-4`** : le defaut d'`histoire-geographie` etait **cinq taches**,
pas deux lignes.

**Et la limite de cette confirmation, dite plutot que tue** : je n'ai toujours aucune mesure qui dise
a partir de combien de lignes une consigne commune cesse d'informer un enfant. **Je dis seulement que
deux n'est pas cinq**, et la section 4.2 vient de montrer que c'est bien le nombre de **taches**, non
de lignes, qui compte.

**Sous `ADR-0081`, la decision est donc close** : la position de `camille-po` du 2026-09-10 plus
cette confirmation. Ce qui reste a moi est le texte des deux clauses, quand il sera ecrit.

### 4.4 La decision de forme de la tache B de `cp-emc-exclusion` - ce que cette tache doit mettre entre les mains de l'enfant

**Elle m'etait due avec `camille-po`, sur le meme regime que `CONS-4`, et rien ne peut etre reecrit
sur ce paquet avant qu'elle soit rendue. Je rends ma moitie.**

**Le probleme, tel que je l'ai laisse le 2026-09-10** : *"un enfant qui parle a sa maitresse d'une
moquerie de la cour fait ce qu'il faut"* n'a pas de solution tant que les trois propositions sont
trois adultes de l'ecole, **parce qu'aucun des trois n'est un mauvais destinataire**. Ma formule de
s32 contournait le probleme en changeant la question, et elle a coute la tache.

**Ce que j'ai verifie aujourd'hui en jouant les cinq items** : le jeu demande *"Quel adulte est
la ?"* et marque fausse *"a sa maitresse"* quand l'enonce nomme le surveillant. **La question est
devenue factuelle, et l'enfant qui repond bien n'a rien appris sur ce qu'on fait d'une moquerie.**

> **Ma moitie de la decision, datee du 2026-09-10.** **La competence de la seconde moitie du libelle
> n'est pas *quel adulte*, c'est *qu'on en parle a un adulte*.** Ce qu'un enfant de six ans doit
> apprendre ici n'est pas de choisir entre la maitresse et le surveillant - les deux conviennent -
> **c'est de ne pas se taire, de ne pas rendre le coup, et de ne pas s'en remettre a un camarade.**
>
> **L'objet de la tache B** : *devant une moquerie qui se repete, l'enfant doit pouvoir reconnaitre,
> parmi trois conduites qu'un enfant de son age a reellement, celle qui consiste a en parler a un
> adulte.* Les deux autres propositions sont **ce qu'un enfant fait a la place** - et elles sont
> plausibles precisement parce qu'elles sont frequentes.
>
> **Le mode de defaillance que je nomme d'avance** : garder trois adultes de l'ecole et changer une
> troisieme fois la question. C'est la pente naturelle, elle a deja ete prise deux fois, et elle
> ramene au meme mur.
>
> **Ce que je ne fournis pas** : les enonces, les propositions et l'indice. **Je n'ai pas recolle ces
> textes a leur place et je ne les dicterai donc pas** (`RES-2`, interdit 1).
>
> **Ce qui reste a `camille-po`** : le nombre d'items de chaque tache et l'equilibre `FER-3`, qui
> sont a elle. **Ce qui reste a moi** : le texte, quand il existera, devant l'ecran.

---

## 5. Le vivier de s34, chaque poste au format de la section 3

**Cinq postes. Chacun porte son objet, son mode de defaillance, son perimetre mesure et son prix.**
Les reserves existantes ne sont **ni durcies ni relevees** : je les **reexprime**, et je le dis a
chaque fois que je le fais.

### 5.1 `HG-CM1-12` - `HG-R17` bloquante, plus cinq reserves neuves sur le meme paquet

- **Constat** : ecrit par l'instance A le 2026-09-10, verdict A section 5.1, et je ne le repete pas.
  **Reverifie a l'ecran aujourd'hui sur l'item que mes cinq parties ont servi** : *"Dans une station
  de montagne touristique, quel logement loue-t-on souvent aux vacanciers ?"* -> *"un chalet ou un
  appartement de location"*, contre *"un immeuble de bureaux"* et *"une ferme isolee"*. **Ni l'un ni
  l'autre n'est un logement de vacances.**
- **Objet** : *un enfant qui ne connait pas la reponse doit pouvoir se tromper serieusement.*
  Aujourd'hui il n'a pas a se tromper : il lui suffit d'ecarter tout ce qui parle de ferme, de
  champ, de peche ou de peniche, et il repond aux neuf items sans lire une seule question. **Ce que
  la ligne doit mettre a l'epreuve, c'est ce que l'enfant sait de la ville et du lieu touristique -
  pas sa capacite a reconnaitre un registre.**
- **Mode de defaillance** : **remplacer les mots ruraux et garder des distracteurs invraisemblables.**
  *"Un immeuble de bureaux"* n'est pas rural et n'est pas davantage un logement de vacances : la
  regle du registre disparaitrait, la ligne resterait gagnable sans lire. **Le test est celui-ci :
  chacun des deux distracteurs est-il une reponse qu'un enfant peut serieusement donner a cette
  question-la ?**
- **Perimetre, mesure sur le fichier servi et sur l'etat d'arrivee** : **les 18 distracteurs des 9
  items neufs de la ligne** ; **deux enonces** (celui qui enumere deux des trois propositions,
  `HG-R18`, et celui ou le mot de la bonne reponse est deja dans la question) ; **et rien d'autre**.
  Aucun item neuf, aucune cellule de plus. **Conditions que l'etat d'arrivee doit encore satisfaire,
  et que je reverifierai sans les rediscuter** : `FER-1` sur les quatre pratiques, `FER-2` a dix
  items, `FER-3`, `HIS-5`, `HG-R4` sur la mediane du paquet. **Elles sont tenues aujourd'hui et je ne
  les redemande pas.**
- **Ce qui voyage gratuitement sur le meme paquet** (`PAR-10`) : `HG-R13` (la proposition verbale
  dans une liste de groupes nominaux), `HG-R14` (l'enonce ou personne ne devient empereur), `HG-R15`
  (l'indice du Code civil), `HG-R16` (ce que la Banque de France est chargee de faire), `HG-R18`.
- **Regime et prix** : **rejeu, 0,5 pour le paquet entier.** `PAR-14` ne mord pas - 14 items touches
  sur 110 - et `PAR-15` non plus : aucune tache n'est reecrite en entier.
- **Attente de fermeture** : **1.** Motif : `HG-R17` est deja ecrite au format de la section 3 -
  elle porte sa propriete et son corollaire, elle ne fournit aucun texte - **et le meme developpeur,
  dans le meme commit, a ecrit dix items de `HG-CM1-08` dont les distracteurs sont serieux dix fois
  sur dix.** Le lot a montre qu'il sait faire ce qui est demande ici.

### 5.2 `EMC-CP-07` - quatrieme refus, cinquieme passage, et le perimetre que j'avais faux

- **Constat** : verdict B, sections 7.3 et 8. **L'item 4 marque fausse une proposition vraie**, et
  c'est le meme point qu'aux troisieme et quatrieme passages.
- **Objet** : *sur la tache de classement, l'enfant doit choisir entre les deux categories que le
  libelle lui demande de distinguer - une regle qui vaut pour tous, un simple souhait - et rien
  d'autre.* **Aujourd'hui le jeu lui presente une troisieme categorie que le libelle ne demande pas
  de distinguer, et il marque fausse une reponse qui est vraie.** La portee de la loi est la seconde
  moitie du libelle : elle se sert dans la tache B, ou elle est deja servie.
- **Mode de defaillance** : **corriger l'item 4 seul.** Les items 5 et 6 portent le meme ternaire et
  aucun motif de refus ne les a nommes ; une correction item par item laisserait le defaut dormir a
  cote. *(Ce mode etait deja nomme dans mon refus d'avance numero 6 de s33 ; je le remets ici, a sa
  place dans la reserve, ce qui est le geste de `RES-1` partie 3.)*
- **Perimetre, mesure sur l'etat d'arrivee - et c'est la correction de mon propre chiffrage** :
  **mon *"aucun item neuf"* etait faux le jour ou je l'ai publie**, et c'est lui, non le contenu, qui
  a fait declarer la sortie infaisable. Les items 4, 5 et 6 redevenus binaires portent le paquet a
  **six items binaires sur onze**, et `BIN-1` demande *au plus la moitie des items du paquet*.
  **Le perimetre reel comprend donc un mouvement d'item**, selon l'une des deux sorties que j'ai
  verifiees compatibles avec `FER-1`, `FER-2`, `FER-3`, `EMC-11`, `PLA-1` et `DEC-0064` : soit l'un
  des trois passe dans la tache B sous la forme *"Une loi ... Qui doit ... ?"* (5 binaires sur 11),
  soit un douzieme item ternaire s'ajoute a la tache B (6 sur 12, a egalite). **Plus le distracteur
  mort de l'item 11, deja paye.** **Je ne choisis pas entre les deux** : le choix depend d'un fait de
  droit a verifier et de son cout, qui ne sont pas de mon ressort.
- **Le prealable, et c'est ce qui a coute le sprint** : le perimetre comprend **la reprise de deux
  tests de paquet** (`fer1LesTroisTermesSontEnBonneReponseDeLaTacheDeClassement`, qui lit le
  troisieme element du libelle dans la mauvaise tache ; `bin1AuPlusLaMoitieDesItemsDeClassementSontBinaires`,
  qui mesure sur la tache et non sur le paquet). **Ce n'est pas du contenu pur, et l'avis de
  `theo-archi` est du avant l'ouverture de la branche, pas au milieu.** Symptome nomme, cause a lui.
- **Regime et prix** : **rejeu, 0,5**, et je nomme la condition qui le ferait passer a 1,0 : si la
  sortie retenue touchait plus de cinq items ou reecrivait les enonces de la tache B, `PAR-15`
  s'appliquerait. **Aucune des deux sorties que j'ai verifiees ne le fait.**
- **Attente de fermeture** : **1 si le prealable technique est obtenu avant l'ouverture de la
  branche, 0 sinon.** Ce n'est pas une figure de style : c'est exactement ce qui a distingue s33 -
  la sortie etait faisable, et la branche a ete ouverte sans savoir laquelle. **Zero sur quatre
  passages jusqu'ici ; la cause des deux derniers est identifiee et elle est chez moi.**

### 5.3 `EPS-CP-04` - `SEC-R4` bloquante, `SEC-R5` non bloquante

- **Constat** : verdict C, sections 5.2 et 5.3. **Reverifie a l'ecran aujourd'hui, partie complete** :
  *"Avant de traverser une rue en trottinette, que doit-on faire ?"* credite *"regarder des deux
  cotes"*, contre *"attendre qu'un camarade traverse d'abord"* et *"courir sans s'arreter"*.
- **Objet, en deux parties parce que le refus en a deux** :
  1. *Un enfant qui joue cet item doit en repartir avec la regle qui protege son corps.* Aujourd'hui
     il repart avec *"on traverse la rue sur sa trottinette apres avoir regarde"*, et le premier
     geste de la regle - **en descendre et la pousser** - manque. Le jeu n'enseigne pas des phrases,
     il enseigne la reponse qu'il credite.
  2. *La situation que l'enonce installe doit etre celle que les trois propositions decrivent.*
     Aujourd'hui l'enonce met l'enfant sur une trottinette et les trois propositions le decrivent a
     pied.
- **Mode de defaillance** : **ajouter le geste manquant a la bonne reponse et laisser les
  distracteurs decrire un pieton.** L'item resterait faux d'un cran plus loin : les trois
  propositions doivent toutes decrire un enfant sur une trottinette, ou aucune.
- **Perimetre, mesure sur l'etat d'arrivee** : **items 3 et 8** - enonce, bonne reponse et deux
  distracteurs chacun - **plus les indices des items 8, 10 et 11** (`SEC-R5` : trois indices sur
  quatre n'enseignent plus rien depuis que le mot interdit par `EPS-7` en a ete retire). **Cinq
  items touches sur onze.** **Conditions que l'etat d'arrivee doit encore satisfaire** : `FER-2` a
  onze items, `FER-3`, `EPS-1` a `EPS-6`, **`EPS-8`** - qui est desormais opposable et ne l'etait pas
  au lot precedent -, `PLA-1`, `FAB-1c`, et **la mention de couverture partielle**, pre-ecrite dans
  la matrice.
- **Deux voies, et je redis laquelle je prendrais sans l'imposer** : remplacer les deux items par
  deux items d'une activite d'EPS - la cour, le gymnase, un agres, l'eau - ce qui regle la regle
  **et** l'ancrage ; ou corriger la regle de la trottinette avec trois propositions qui decrivent
  toutes un enfant sur une trottinette. **Je prendrais la premiere.**
- **Regime et prix** : **rejeu, 0,5.** `PAR-14` ne mord pas - cinq sur onze - et `PAR-15` non plus :
  la tache B n'est pas reecrite en entier. **C'est le tarif que `camille-po` me demande de rendre
  (section 6.1).**
- **Attente de fermeture** : **0 a 1, et je penche vers 1.** Motif contre : troisieme passage, et
  c'est le **premier contact d'`EPS-8` avec du contenu reel** - `EPS-7` a echoue exactement la.
  Motif pour : les deux causes des deux echecs precedents sont identifiees et **elles sont toutes
  les deux chez moi** (l'exemple fautif, corrige dans ce diff ; la condition de retrait seul, deja
  reparee par `EPS-8`). **Tout le reste du paquet est verifie et bon**, l'instance C l'a etabli item
  par item.

### 5.4 `EPS-CP-08` - `JEU-R9` bloquante, `JEU-R10` a `JEU-R12` non bloquantes

- **Constat** : verdict C, sections 6.3 et 6.5. **Reverifie a l'ecran aujourd'hui, chemin d'echec
  complet** : *"A l'epervier, Malo traverse en sortant du couloir. Que se passe-t-il ?"* credite
  *"Malo est elimine"* et **marque fausse *"Malo change de role"***, alors que l'item 3 du meme
  paquet enseigne que *"un joueur touche devient epervier a son tour"*. L'indice servi apres le
  premier echec - *"Le couloir marque les limites que les joueurs doivent respecter"* - dit qu'il y a
  une limite, jamais ce qui arrive quand on la franchit.
- **Objet** : *un enfant qui a retenu ce que le paquet lui a appris cinq manches plus tot ne doit pas
  etre marque faux avec.* Le paquet doit dire **une seule chose** de l'epervier.
- **Mode de defaillance** : **corriger l'item 8 seul, une deuxieme fois.** C'est deja ce qui est
  arrive : `JEU-R4` a ete corrigee en reproduisant `JEU-R2`, dans le meme fichier, le meme commit,
  quatre items plus loin. **Le geste qui manque existe deja dans ce paquet** - la tache A enseigne la
  regle des demenageurs avant que la tache B ne la juge, et le chat perche est coherent sur trois
  items a la fois. **Il n'a pas ete applique a l'epervier.**
- **Perimetre, mesure sur l'etat d'arrivee** : **item 3 ou item 8** selon la voie retenue - soit
  l'item 3 enseigne la limite et sa consequence avant que l'item 8 ne la juge, soit l'item 8 credite
  la sanction que le paquet enseigne deja - **plus l'indice de l'item 8**, **plus l'item 10** (la
  bonne reponse ne bouge pas alors que les deux distracteurs ont ete changes, donc la forme designe
  toujours), **plus l'indice de l'item 6**. **Quatre items sur onze.** **Conditions que l'etat
  d'arrivee doit encore satisfaire** : `EPS-1`, `EPS-8`, `PLA-1`, `FER-2`, `FER-3`, **`COH-1`**
  (section 10), et la mention de couverture partielle.
- **`JEU-R12` ne fait pas partie du perimetre et je le dis pour qu'on ne l'y mette pas** : deux
  personnages masculins qui enfreignent une regle ne font pas une population, **je ne pose aucune
  barre chiffree**, et la seule chose que je demande est de ne pas aggraver en reecrivant.
- **Regime et prix** : **rejeu, 0,5.** `PAR-14` ne mord pas - quatre sur onze - alors qu'elle avait
  mordu a s33 sur ce meme paquet, ou sept items sur onze changeaient. **C'est le second tarif que
  `camille-po` me demande de rendre.**
- **Attente de fermeture** : **0 a 1, et je penche vers 1.** Motif : le geste de paquet que ce refus
  demande **a ete invente par ce lot, dans le commit meme qui a produit le defaut**. C'est le poste
  ou le remede est le plus proche de ce que l'equipe sait deja faire.

### 5.5 Les six reserves neuves d'EMC CP, plus `EXC-R1` toujours impayee

**Elles vivent dans trois paquets, et sous `PAR-10` elles se soldent par paquet.**

**`cp-emc-exclusion` - `EXC-R1`, `EXC-R6`, `EXC-R7`, `EXC-R8`, `EXC-R9`. C'est le poste le plus
lourd des trois et le seul que je ne chiffre pas a 0,5.**

- **Constat** : verdict B, sections 6.2 a 6.4, et `EXC-R1` en section correspondante de la fiche.
  **Reverifie a l'ecran aujourd'hui, partie complete** : sur les cinq items de la tache B, la bonne
  reponse reprend le mot que l'enonce vient d'ecrire quatre fois sur cinq ; recollee, la question
  donne *"Quel adulte est la ? -> a sa maitresse"* ; et la consigne annonce *"ou a quel adulte en
  parler"* sur les dix manches alors qu'aucun item ne le demande plus.
- **Objet** : celui de la section 4.4, plus deux : *l'enfant ne doit pas pouvoir gagner un item en
  appariant deux mots identiques* (`EXC-R7`), et *la question recollee a chacune de ses trois
  propositions doit former une phrase francaise* (`EXC-R8`). **`EXC-R1` n'a pas d'objet propre : sa
  condition est remplie des que `EXC-R9` l'est** - le texte de consigne ecrit a s33 est juste, c'est
  le paquet qui ne le sert pas.
- **Mode de defaillance** : celui de la section 4.4, plus un second, mesure a s33 : **payer les
  reserves une par une dans le meme commit sans relire le paquet assemble.** Deux stories de s33 se
  sont defaites l'une l'autre exactement ainsi.
- **Perimetre, mesure sur l'etat d'arrivee** : **les cinq items de la tache B en entier** - enonce,
  trois propositions et indice chacun - **plus l'item 2 de la tache A** (`EXC-R6`, le plus long) ;
  **la consigne du catalogue reste celle de s33 et ne bouge pas.** **Six items sur onze touches,
  dont une tache entiere.** **Conditions que l'etat d'arrivee doit encore satisfaire** : `FER-1` sur
  les deux moities du libelle - c'est l'objet meme -, `FER-2` a onze items, `FER-3`, `PLA-1`,
  `CAT-1`, `EMC-5`, `LON-1` et `LON-2` (executees par `ADR-0089`).
- **Regime et prix** : **tarif de lot, 1,0** (`PAR-15`, section 6.1). **Motif** : une tache entiere
  reecrite, plus une consigne dont la tache change de statut, ne laisse rien de l'audit precedent sur
  cette moitie du paquet. **Je dois rebalayer comme pour un premier passage.**
- **Ce que je ne demande pas** : je **ne rouvre pas `EMC-CP-03`**, qui reste `couvert-valide`, et je
  ne demande aucun mouvement de matrice. **Ce poste ne ferme aucune ligne** : il remet a l'ecran le
  contenu qui a ferme celle-la.
- **Attente** : sans objet, aucune ligne n'est en jeu.

**`cp-emc-regles-de-vie` - `REG-R3`, `REG-R4`, `REG-R5`.**

- **Objet de `REG-R4`** : *la bonne reponse ne doit pas etre la seule proposition polie des trois.*
  Le correctif de `CAT-1` a remplace un distracteur serieux (*aller prevenir la maitresse tout de
  suite*) par un distracteur impoli (*lui dire de se taire*), parce que le premier portait le mot que
  la story devait retirer. **J'ai presse la bonne reponse du premier coup sans lire les deux autres.**
- **Objet de `REG-R5`** : *aucun gabarit de phrase repete ne se trouve dans les seuls distracteurs.*
  Trois distracteurs sur trois items partagent l'amorce *"pour montrer qu'on..."*, qu'aucune bonne
  reponse ne porte. **Je ne pose aucun seuil chiffre** : trois occurrences est ce que j'ai mesure, ce
  n'est pas une barre.
- **Mode de defaillance, et c'est le plus important de ce poste** : **payer un repere lexical en
  ramenant un repere de registre sur l'item qu'on touche.** C'est exactement ce qui vient de se
  produire, et c'est la troisieme fois en trois sprints qu'un correctif reproduit la famille de
  defaut qu'il paie.
- **Perimetre** : les distracteurs des items 2, 7, 8 et 10. **Quatre items sur onze**, propositions
  seules. **Prix : rejeu, 0,5.**

**`cp-emc-permis-interdit` - `PER-R2`, `PER-R3`.**

- **Objet de `PER-R3`** : *une raison dont la faussete tient a une inversion entre la regle et ce
  qu'elle protege n'est pas servie deux fois comme distracteur.* *Pour respecter le reglement de
  l'ecole* est distracteur sur les items 2 et 6 ; l'inversion est fine pour un enfant de six ans, et
  c'est la seule formulation du paquet qui se repete dans les distracteurs sans jamais etre bonne
  reponse.
- **Ce que ce n'est pas** : **un reproche au correctif de `CAT-1`.** Rendre les distracteurs serieux
  les rapproche du vrai ; c'etait previsible et c'est le prix de `CAT-1`. **Je le borne, je ne
  reviens pas sur le paiement.**
- **Perimetre** : les distracteurs des items 2 et 6, plus ce que `PER-R2` demande. **Prix : rejeu,
  0,5.**

**`cp-emc-emotions-autrui` (`EMO-R3`) et `cp-emc-formules-de-politesse` (`POL-R3`, `POL-R4`)** :
jamais touches a s33, aucune story. **0,5 chacun**, inchange. **`POL-R4` est la troisieme occurrence
en trois sprints de la meme propriete qu'`EXC-R8` et `PER-R1`** - question et propositions qui ne
forment pas une phrase recollees - **sur trois paquets de la meme matiere**, et c'est un argument
pour les traiter ensemble.

### 5.6 Le lot de l'effort et du corps - `EPS-CP-10` et `EPS-CP-11`

**Un seul paquet (section 4.3), deux lignes, contenu neuf.**

- **Objet** : *l'enfant doit apprendre ce que l'effort fait a son corps, et ce que l'echauffement,
  la boisson et le repos font pour lui* - jamais ce qu'il doit faire, jamais ce qu'il ressent
  lui-meme, jamais ce qu'il vaut compare a un autre.
- **Modes de defaillance, quatre, nommes d'avance parce que ce sont les quatre pentes naturelles de
  ces deux lignes** : une performance chiffree presentee comme une norme (*"au bout de combien de
  minutes est-on essouffle ?"*, qui n'a pas de reponse) ; une conduite en bonne reponse (*"que
  dois-tu faire si tu as soif ?"*) ; un indice qui decrit l'effort par une image (*"le coeur
  s'emballe"*, *"les jambes deviennent lourdes"*, lus au premier degre a six ans) ; **et un item qui
  ne fixe pas le moment de l'effort dont il parle** (section 2.4).
- **Perimetre** : **vingt-deux items au moins** - `FER-2` demande dix par ligne, `DEC-0064` onze au
  moins dans un paquet de fermeture a dix manches -, une entree de catalogue, une consigne a deux
  clauses. **Conditions que l'etat d'arrivee doit satisfaire** : `FER-1`, `FER-2`, `FER-3`, `EPS-1`
  a `EPS-8`, `BIA-4`, `FAB-1c` sur les deux branches, `LON-1` et `LON-2` (executees), `PLA-1`, la
  frontiere corps-et-effort **ecrite dans la specification avant le premier item**, et la mention de
  couverture partielle.
- **Prix : 1,5.** Je ne suis pas la source du contenu et **chaque fait sur le corps a l'effort se
  verifie contre une source** : essoufflement, frequence cardiaque, chaleur, soif, delai de
  recuperation. **Lignes : +2.**
- **Attente de fermeture : 0 a 1 pour la paire**, et je penche vers 0. Motif, dit sans l'adoucir :
  c'est le **premier contact** d'`EPS-2`, d'`EPS-3` et de la frontiere corps-et-effort avec du
  contenu reel, et **le seul taux mesure de l'EPS au premier contact est zero sur deux.**

### 5.7 Ce que je recommande de ne pas reprendre a s34, et pourquoi

**Nommer ce qu'on ne prend pas est la moitie d'un arbitrage.**

| Poste | Ce que je recommande | Motif |
|---|---|---|
| **`REG-R3`** - le genre de l'adulte d'ecole | **ne pas la reprendre a s34 comme geste propre** | J'ai corrige mon propre chiffre : c'etait **17 contre 6**, pas 17 contre 1, et s33 l'a deja porte a **12 contre 8**. Le desequilibre est reel et plus petit que je ne l'avais ecrit. **Quatre des points gagnes vivent dans les enonces de `exclusion` que je demande de reecrire** : le compte va bouger tout seul. Depenser maintenant sur un chiffre qui va changer n'a pas de sens - je le recompterai apres |
| **`JEU-R12`** - le genre des personnages qui enfreignent | **ne pas en faire une story** | Deux occurrences. **La population est trop petite pour une barre et je n'en pose pas.** Elle voyage gratuitement avec `JEU-R9` si les items reecrits changent de prenom |
| **`MEL-R4`** sur `cm1-sciences-melanges` | **ne pas la reprendre** | Deja chiffree **0,0** : elle ne justifie pas un rejeu a elle seule, et rien n'a change |
| **Le groupe 2b** - `CONS-2`, `SAI-R1`, `ERR-R1`, 1,0 | **ne pas le reprendre a s34** | Il ne ferme aucune ligne, il est differe depuis s31, et **la decision de grain que je rends en section 4.2 change la forme de `CONS-2`** : le reprendre avant que le decoupage soit fait serait payer deux fois |
| **`CONS-3`** | **ne pas la reprendre** | **Toujours non chiffree, et je le dis comme un manque plutot que de l'estimer.** Je ne la chiffre pas dans ce document parce que je ne l'ai pas mesuree |
| **Le residu du groupe 2a** - cinq paquets, 2,5 | **le garder, mais en premier a sacrifier** | Il ne ferme aucune ligne, mais **c'est exactement ce que le Plan 2 a ete retenu pour payer**, et s34 est le dernier sprint de sa fenetre. **S'il faut arbitrer contre le palier 2, c'est lui qui cede** - et c'est a `camille-po`, pas a moi |

---

## 6. Les trois tarifs que `camille-po` me laisse, et le registre de dette a l'ouverture de s34

### 6.1 `PAR-15`, la clause que `cp-emc-exclusion` me fait ecrire

> **`PAR-15` - nouvelle, datee du 2026-09-10.** **Une reprise qui reecrit l'integralite d'une tache
> d'un paquet, ou qui change une consigne et la tache que cette consigne nomme, se paie au tarif d'un
> lot** - 1,0 quand je suis la source des motifs, 1,5 sinon - **et non au tarif d'un rejeu (0,5),
> meme lorsque le nombre d'items touches reste inferieur a la moitie du paquet.**
>
> **Motif, mesure sur le cas** : `cp-emc-exclusion` porte onze items, six en tache A et cinq en tache
> B. `EXC-R7`, `EXC-R8` et `EXC-R9` demandent la reecriture des cinq, enonces et propositions
> comprises, plus le retablissement de la tache que la consigne annonce. **Cinq sur onze : la lettre
> de `PAR-14` ne mord pas, et il ne reste pourtant rien de mon audit precedent sur cette moitie du
> paquet.**
>
> **Ce que cette clause est, et ce qu'elle n'est pas.** C'est **une clause neuve**, pas une lecture
> nouvelle de `PAR-14` : `PAR-14` reste exactement ce qu'elle dit, avec son seuil, et sur ce paquet
> elle ne s'applique pas. **Elle est non retroactive** - elle ne rechiffre ni s33, ni le groupe 2a,
> ni les neuf reserves de s31. **Elle ne releve la barre d'aucun increment** : c'est une regle de mon
> budget d'audit, et aucun des refus de ce document n'en depend.

> **`PAR-16` - nouvelle, datee du 2026-09-10.** **Je n'alloue contre ma borne aucun poste qui n'a pas
> de story a l'ouverture du sprint.** Un poste finance sans story ne consomme rien de mon budget : il
> reste disponible pour etre reaffecte pendant le sprint.
>
> **Motif, mesure** : a s33, **au moins 4,0 sur 11,5 sont restes totalement inemployes**, dont 1,5
> chez l'instance A et 2,0 chez l'instance C, sur des postes qui n'ont jamais recu de story - dont
> les deux lignes du lot du corps, financees a 1,5, et le paquet `cm1-anglais`. **Un chiffre alloue a
> un poste sans story n'achete rien et empeche d'acheter autre chose.**
>
> **Ce que cette clause ne fait pas** : elle ne dit a personne quelles stories ecrire ni quand. Elle
> dit comment **je** compte, ce qui est a moi. Et elle ne s'applique pas retroactivement a s33.

### 6.2 Les trois tarifs, rendus

`camille-po` a recalcule le registre de dette et arrete son chiffre a **7,5**, en nommant
explicitement les trois tarifs qu'elle ne rend pas a ma place. **Les voici. Je ne recalcule pas son
registre : je remplis les trois cases qu'elle m'a laissees.**

| Poste | Tarif que je rends | Motif |
|---|---|---|
| `cp-eps-regles-de-securite`, troisieme passage | **0,5** | Cinq items sur onze, aucune tache reecrite en entier. Ni `PAR-14` ni `PAR-15` ne mordent : c'est un rejeu (section 5.3) |
| `cp-eps-regles-jeu-collectif`, troisieme passage | **0,5** | Quatre items sur onze. Meme motif. **`PAR-14` avait mordu a s33 sur ce paquet ; a s34 elle ne mord pas**, et je ne la fais pas mordre par habitude (section 5.4) |
| `cp-emc-exclusion` | **1,0 au lieu de 0,5**, soit **+0,5** sur son chiffre | `PAR-15` (section 6.1) |

### 6.3 Le registre a l'ouverture de s34

| | Montant |
|---|---|
| Registre chiffrable etabli par `camille-po` | **7,5** |
| `cp-eps-regles-de-securite` | +0,5 |
| `cp-eps-regles-jeu-collectif` | +0,5 |
| `cp-emc-exclusion`, supplement `PAR-15` | +0,5 |
| **Registre a l'ouverture de s34** | **9,0**, plus `CONS-1`, `CONS-3` et `CONS-4` |

**Dont 2,0 seulement ferment une ligne** - les quatre reprises des sections 5.1 a 5.4 - **et 7,0 n'en
ferment aucune.**

> **Le registre etait de 7,5 a l'ouverture de s32, de 9,0 a l'ouverture de s33, et il est de 9,0 a
> l'ouverture de s34.** **s33 a ferme trois lignes et a laisse la dette exactement ou il l'avait
> trouvee.**
>
> **L'egalite des deux chiffres est une coincidence et je le dis pour qu'on ne la prenne pas pour une
> stabilite** : la composition a change - deux paquets d'EPS sont passes de 1,5 a 1,0 ensemble,
> `cp-emc-exclusion` de 0,5 a 1,0, l'histoire-geographie a solde son ancienne dette et en a rouvert
> une neuve du meme montant.
>
> **Et je nomme le mecanisme, que `camille-po` a mesure et que j'avais nomme sans en tirer les
> consequences** : **ce depot n'a encore jamais reaudite un paquet sans y trouver au moins une
> reserve neuve.** Les trois paquets qui ont integralement solde leur dette a s33 en ont ouvert une
> neuve dans le meme geste. **Toute projection de descente qui compte un paquet reaudite comme solde
> surestime la descente**, et c'est ma regle du Plan 2 qui ne dit rien de ce cas, pas son execution.
> **Je ne comble pas ce trou par une clause ici** - c'est une regle de gouvernance, elle depasse mon
> ressort - **et je le route a `camille-po` pour la deuxieme fois.**

---

## 7. Ma borne de s34, et ce qui plafonne reellement le sprint

**Je reprends 12,0 et je ne demande pas sa revision.** Trois instances a 4,0 (`PAR-9`), plafond
individuel dur 5,0 (`PAR-8`), `PAR-10`, `PAR-11` amendee, `PAR-12` a `PAR-16` opposables. **Rien de
ce document ne touche a ces clauses.**

**Et je dis ce que la borne n'est pas.** A s33, elle n'a pas ete ce qui a limite le sprint : **au
moins 4,0 sur 11,5 n'ont rien achete du tout**, et sur ce qui a ete engage, **seuls les 2,5 de
l'instance A ont achete les trois lignes fermees**. Demander une borne plus grande pour s34 serait
demander plus de ce qui n'a pas ete depense.

> **Ce qui plafonne s34 n'est pas mon budget : c'est le motif de la section 3.** Un audit refuse
> coute moins cher qu'un audit qui valide - un refus s'arrete au premier defaut suffisant - **et un
> sprint qui refuse quatre lignes sur sept depense donc moins que sa borne tout en n'achetant rien.**
> **La borne de 12,0 ne devient une contrainte que le jour ou les corrections portent.**

---

## 8. Plafond, attente raisonnable, et ma calibration corrigee

### 8.1 Le plafond arithmetique, dans la borne

| Poste | Prix | Lignes |
|---|---|---|
| `HG-CM1-12` (paquet d'histoire-geographie entier) | 0,5 | +1 |
| `EMC-CP-07` | 0,5 | +1 |
| `EPS-CP-04` | 0,5 | +1 |
| `EPS-CP-08` | 0,5 | +1 |
| Lot de l'effort et du corps, un paquet | 1,5 | +2 |
| **Sous-total, tout ce qui ferme une ligne** | **3,5** | **+6** |
| Le reste du registre (`exclusion` 1,0, cinq paquets d'EMC CP et d'anglais a 0,5, cinq paquets du residu 2a a 0,5, groupe 2b 1,0) | 7,0 | 0 |
| Decoupage de `CONS-4` (section 4.2) | 1,0 | 0 |
| Verification du remede de `SCO-1` (section 4.1) | 0,5 | 0 |
| **Total si tout est pris** | **12,0** | **+6** |

> **Plafond de s34 : 6 lignes, pour exactement 12,0.** **75 / 138 = 54,3 %.**
>
> **C'est serre au point de n'avoir aucune marge**, et deux choses n'y tiennent pas : le palier 2
> (section 9) et tout lot de matiere neuve. **La marge de `PAR-11` n'est pas dans ce tableau** : si
> elle est reservee, un poste de 0,5 en sort.

### 8.2 Ligne par ligne

| Poste | Plafond | Attente | Motif |
|---|---|---|---|
| `HG-CM1-12` | 1 | **1** | `HG-R17` porte deja sa propriete et son corollaire, et le meme lot a ecrit dix distracteurs serieux sur dix a cote |
| `EMC-CP-07` | 1 | **1 ou 0**, selon un fait nommable | 1 si l'avis technique sur les deux tests est obtenu **avant** l'ouverture de la branche ; 0 sinon. **Zero sur quatre jusqu'ici, et la cause des deux derniers est chez moi** |
| `EPS-CP-04` | 1 | **0 a 1**, penchant 1 | Les deux causes precedentes sont identifiees et reparees ; mais `EPS-8` rencontre son premier contenu, ce qui est exactement la ou `EPS-7` a echoue |
| `EPS-CP-08` | 1 | **0 a 1**, penchant 1 | Le geste demande a ete invente par ce lot dans le commit qui a produit le defaut |
| `EPS-CP-10`, `EPS-CP-11` | 2 | **0 a 1**, penchant 0 | Premier contact d'`EPS-2`, d'`EPS-3` et de la frontiere corps-et-effort. **Taux mesure de l'EPS au premier contact : 0 sur 2** |
| **Total** | **6** | | |

### 8.3 Le chiffre que je donne

> **Attente raisonnable : 2 a 4 lignes sur un plafond de 6.** **71 a 73 / 138, soit 51,4 % a
> 52,9 %.**

**Pourquoi 2 a 4, et comment je l'ai construit** - parce que ma table ci-dessus a l'air d'en
autoriser plus. **J'applique ma propre correction de la section 2.2** : *"0 a 1"* a valu **une sur
cinq** a s33, pas une sur deux. Sur les quatre postes en `0 a 1`, cette calibration seule rend
**un**. J'ajoute le `1` de `HG-CM1-12`, ce qui donne **2**. Je monte la borne haute a **4** parce que
trois des cinq postes portent des motifs deja ecrits au format de la section 3 - propriete, pas
texte - et que **c'est le premier sprint ou cela est vrai**.

> **Et je dis exactement ce que mesure l'ecart entre 2 et 4** : **il mesure si la discipline de la
> section 3 tient.** Si les corrections de s34 portent leur objet et pas seulement leur lettre, le
> sprint est a 4 ; sinon il est a 2. **C'est la premiere mesure de ce changement, et je m'y expose
> volontairement plutot que d'elargir la fourchette pour ne pas avoir tort.**

**Ce que je ne fais pas** : promettre que ma discipline marche. Elle a un sprint d'age et zero
mesure. **Ce que je peux dire, c'est que les trois causes de s33 sont chacune reparee a sa source** -
l'exemple fautif corrige dans ce diff, la condition de retrait remplacee par `EPS-8`, le chiffrage
refait sur l'etat d'arrivee - **et que c'est verifiable, pas une intention.**

---

## 9. Le palier 2 - ce que je peux en dire, et ce que je n'en dis pas

**Il est finance a son entree minimale par l'utilisatrice** (`DEC-0083`, section 13 : `QLM-CP-11` et
`QLM-CP-14`, 2,0 pour deux lignes) et **reporte a s34** par `DEC-0085`. **Je ne rouvre pas cet
arbitrage.**

**Ce que je constate, mesure sur l'arbre** : le catalogue sert **66 entrees de `bandit-manchot` et 2
de `saisie`**, et **zero de `designation`**. **Sixieme sprint sans qu'une seule entree de palier 2
soit jouable par qui que ce soit.**

**Ce qu'il peut rendre a s34 : zero ligne, selon ses propres prealables, et s33 a ajoute un fait.**
`S33-PAL2-1` porte cinq prealables bloquants - les lots `L-a`, `L-c` et `L-d`, plus les deux trous de
contrat -, et `L-c` est decrit par `theo-archi` comme **indivisible, un developpeur, un sprint
entier**. **Aucun n'a bouge a s33** : `theo-archi` a mesure que **zero ligne de code de production n'a
change pendant tout le sprint**. Le contenu ne peut donc etre ecrit qu'apres `L-c`, servi qu'apres
`L-d`, et audite qu'apres cela. **Je n'ai jamais vu ce depot enchainer trois maillons de cette
taille dans un sprint, et s33 vient d'ajouter un sprint entier ou la capacite de developpement est
allee entierement au contenu.**

> **Mon budget n'est pas ce qui bloque le palier 2 : je n'y depense rien tant qu'il n'y a rien a
> jouer.** Ce que je nomme, plutot que de le laisser decouvrir a la cloture, est **la consequence
> arithmetique** : **mon plafond de 6 lignes suppose trois producteurs de contenu pour trois
> instances d'audit. Sous `L-c`, il en reste deux, et les 3,5 qui achetent les 6 lignes ne peuvent
> plus etre produits.** C'est a `sofia-sm` de dire de combien le plafond descend ; je dis de quoi il
> depend.
>
> **Et je nomme le choix, parce que s34 est le sprint ou il devient reel** : **le palier 2 et le
> plafond de 6 lignes ne tiennent pas ensemble dans 12,0** - 3,5 + 7,0 + 1,0 + 0,5 remplissent deja
> la borne exactement, et le palier 2 en demande 2,0 de plus. **Ce qui cede est un arbitrage de
> `camille-po`**, et j'ai dit en section 5.7 lequel des postes je sacrifierais si c'etait a moi -
> ce qui ne l'est pas.

**Et ce que je ne sais toujours pas, dit comme un manque** : mon forfait de **+1,0 de supplement
image** pour un lot de designation. Je l'avais chiffre en doutant qu'il tienne a six dessins ; le lot
en porte trois. **Une passe jouee zone par zone se compte en zones, pas en dessins, et le nombre de
zones n'est ecrit nulle part.** *Je ne le sais toujours pas et je ne l'estime pas.*

---

## 10. Ce que je refuse d'avance, et ou cela cassera d'abord

**Chaque refus porte l'endroit ou il se realisera en premier, pour qu'il soit evitable plutot que
constate.**

| # | Ce que je refuse | Ou cela cassera d'abord |
|---|---|---|
| 1 | **Toute fermeture de `HG-CM1-12` dont les deux distracteurs d'un item ne sont pas du meme genre que sa bonne reponse et du meme espace que son enonce** (`HG-R17`) | Sur les items dont la bonne reponse est un lieu - *"dans un musee"*, *"dans un bureau"*. La pente est de remplacer *"dans une ferme"* par un autre lieu qui n'est toujours pas un lieu ou l'on voit des oeuvres. **Retirer le registre rural ne suffit pas** |
| 2 | **Tout quatrieme correctif de `EMC-CP-07` qui corrige l'item 4 sans retirer *une loi* des propositions des items 5 et 6** (`EMC-11`) | Sur les items 5 et 6, que le motif de refus ne nomme pas. **Refus reconduit de s33, ou il n'a pas mordu faute d'increment** |
| 3 | **Toute reprise de `EPS-CP-04` ou l'enonce installe une situation que les trois propositions ne decrivent pas** (`SEC-R4`, element 1) | Sur l'item 3. Ajouter *"on en descend et on la pousse"* en bonne reponse et garder deux propositions de pieton refait la faute d'un cran plus loin |
| 4 | **Tout indice d'EPS qui reformule son enonce au lieu d'apporter un fait que l'enfant ne peut pas deviner** (`EPS-8`) | Sur les indices des items 8 et 11 de `regles-securite`, deja vides, et sur les onze indices neufs du lot du corps. **`EPS-8` est datee du 2026-09-10 et opposable a partir du prochain lot** |
| 5 | **Tout item qui juge une regle qu'un autre item du meme paquet enonce autrement** (`COH-1`, ci-dessous) | Sur l'epervier, items 3 et 8, **ou c'est deja le motif du refus** ; et sur tout jeu que la reecriture introduirait sans relire le paquet |
| 6 | **Tout ecran de fin de partie qui, apres le remede de `SCO-1`, n'afficherait que le nombre reduit** | A la premiere partie jouee apres le remede. **Un enfant qui a fait les dix questions doit lire qu'il les a faites**, a cote de ce qu'il a trouve seul |
| 7 | **Toute fermeture d'une ligne d'EPS CP sans sa mention de couverture partielle**, reprise mot pour mot de la matrice | A la cloture, sur les quatre lignes d'EPS CP a la fois. **La mention est pre-ecrite et je refuse la ligne sans elle.** Refus reconduit de s33 |
| 8 | **Tout item du lot du corps qui ne fixe pas le moment de l'effort dont il parle - pendant, juste apres, plus tard - et dont une proposition serait vraie a un autre moment** | Sur *"apres avoir couru longtemps, que ressent-on ?"*, qui est **mon propre exemple du referentiel** : *"on a froid"* est un distracteur naturel et il est vrai apres l'effort. **Refus neuf, date du 2026-09-10** (section 2.4) |
| 9 | **Tout item du lot du corps qui compare des corps ou presente une performance chiffree comme une norme** (`EPS-2`), et **tout item dont la bonne reponse est une conduite** (`EPS-3`) | *"Au bout de combien de minutes est-on essouffle ?"* et *"que dois-tu faire si tu as soif ?"* sont les deux pentes naturelles de ces deux lignes. Refus reconduits de s33 |
| 10 | **Tout indice d'EPS qui decrit un effort par une image** (`EPS-6`) | *"Le coeur s'emballe"*, *"les jambes deviennent lourdes"*. **Au CP l'indice est lu au premier degre**, et c'est le seul texte que lit l'enfant qui vient d'echouer |
| 11 | **Toute fermeture d'une ligne d'EPS, de sciences CM1 ou de questionner le monde CP sur le corps et l'effort sans que la frontiere corps-et-effort soit ecrite dans la specification de son lot, avant le premier item** | **Sur le lot du corps, et c'est le second sprint ou ce refus attend son increment.** Pose le 2026-09-08, repris par `DEC-0083` section 9.2, jamais paye faute de lot |
| 12 | **Tout item de la tache B de `cp-emc-exclusion` dont les trois propositions sont trois adultes de l'ecole** (section 4.4) | Des la premiere reecriture. **C'est la pente qui a deja ete prise deux fois**, et elle ramene au meme mur : aucun adulte de l'ecole n'est un mauvais destinataire |
| 13 | **Tout item de sciences CM1 calculant une vitesse** compte sur la ligne du mouvement | Refus reconduit de s31, s32 et s33, inchange (`ADR-0081`, clause 5) |
| 14 | **Tout Done pedagogique sur un lot que je n'ai pas joue**, et tout reaudit compte comme gratuit hors de la marge | A la cloture, si un sprint deborde. **Une ligne ne ferme pas *quand meme*.** Refus reconduit de s31, s32 et s33 |
| 15 | **Tout enonce de CP de plus de 15 mots ou de plus de deux phrases** (`LON-1`, `LON-2`) | Sur les vingt-deux enonces neufs du lot du corps. **`ADR-0089` les execute et je n'en suis plus la garde** - ils restent un motif de refus, et **une reecriture faite pour tenir 15 mots a deja casse trois referents** |
| 16 | **Tout lot de designation audite avant que `DesignationVerdict` ne porte l'enonce de l'item joue**, et **tout ecran de fin de partie de designation qui montrerait la zone attendue** | A la fin de la premiere partie de designation jouee. Refus reconduit de s32 et s33, porte par deux stories |
| 17 | **Tout dispositif qui verifierait ce qu'un adulte a dit a l'enfant** | Refuse d'avance depuis le 2026-09-01, motif ecrit, **et le lot du corps le rendra tentant** : *"demande a un adulte de te montrer l'echauffement"* n'est pas un item |

**La condition neuve que le refus 5 porte :**

> **`COH-1` - nouvelle, datee du 2026-09-10. Deux items d'un meme paquet qui portent sur le meme
> objet - le meme jeu, la meme regle, la meme categorie - ne se contredisent pas.**
>
> **Motif, mesure en jouant** : l'item 3 de `regles-jeu-collectif` enseigne qu'un joueur pris a
> l'epervier change de role ; l'item 8, cinq manches plus loin, marque fausse *"Malo change de
> role"*. **L'enfant qui a retenu ce que le paquet lui a appris est marque faux avec.** Le meme
> paquet montre le contraire sur le chat perche, ou trois items disent la meme regle sous trois
> angles.
>
> **Ce que cette condition n'est pas** : ce n'est pas une exigence de plus sur un item pris seul, et
> elle ne relit rien de ce qui est deja ferme. **Elle est opposable a partir du prochain lot**, comme
> `EPS-8`, et **je ne pose aucune barre chiffree**.
>
> **Est-elle mordable ?** **Pas aujourd'hui.** Elle le serait en principe si un paquet declarait
> l'objet de chacun de ses items - il declare deja sa ligne de referentiel (`ADR-0081`, clause 4) et
> sa tache se lit dans sa bonne reponse (`ADR-0089`), jamais l'objet dont il parle. **Je dis la
> propriete, jamais la forme du test, et je la route a `theo-archi` sans en faire une demande de
> sprint.**

---

## 11. Ce dont je reste la seule garde, et ce que je route

**Dit explicitement pour que l'equipe ne croie pas couvert ce qui ne l'est pas.**

| Propriete | Qui la tient | Mordable par un automate ? |
|---|---|---|
| **L'objet d'une correction, contre sa lettre** (section 3) | **moi seule pour l'ecrire, une personne pour le controler** (`RES-3`) | **non**, et c'est la conclusion la plus importante de ce document. Comparer un diff a une intention en prose n'est pas calculable. **Seule la presence d'un objet dans une reserve l'est** (`RES-4`), et je la route |
| **`COH-1`** - un paquet ne se contredit pas avec lui-meme | **moi seule** | **non** aujourd'hui, **peut-etre** si un paquet declarait l'objet de ses items. Route a `theo-archi`, sans demande de sprint |
| **L'exactitude de chaque regle de jeu et de chaque regle de securite** | **moi seule** | **non.** **C'est le motif de deux des quatre refus de s33, et les quatre jobs sont verts sur l'arbre qui les porte** |
| **`EXA-1` et l'exactitude factuelle** en general | **moi seule** | **non.** `EXA-1` dit ce qu'on fait d'une inexactitude, pas comment on la trouve |
| **`EMC-11`, `EMC-5`** - aucune proposition vraie n'est marquee fausse | **moi seule.** **Quatrieme sprint consecutif** sur `regle-souhait-loi` | **non** |
| **`PLA-1`** - chaque distracteur est un candidat serieux | **moi seule.** **C'est le motif du refus de `HG-CM1-12`**, et le cas de `JEU-R9` en est l'inverse : le distracteur y est **defendable**, ce qui est pire | **non** |
| **`CAT-1`, `FAB-1c` branche registre** | **moi seule.** **Tenues aujourd'hui sur les deux paquets d'EPS et payees sur deux paquets d'EMC**, avec `REG-R4` comme prix | **non** |
| **`EXC-R7`** - la bonne reponse n'est pas dans l'enonce | **moi seule**, et je la crois **mordable** : c'est une comparaison de mots entre l'enonce et la bonne reponse d'un meme item. **Je dis la propriete, jamais la forme du test, et je ne pose aucun seuil** | **peut-etre**, route a `theo-archi` a s33, non paye |
| **Question et propositions qui forment une phrase recollees** | **moi seule.** **`PER-R1`, `POL-R4`, `EXC-R8` : trois occurrences, trois sprints, trois paquets de la meme matiere** | **non** |
| **La coherence entre la consigne d'une entree de catalogue et les taches que son paquet sert** (`EXC-R1`, `EXC-R9`, `CONS-1`) | **moi seule, en jouant.** `PaquetCpEmcExclusionTest` est vert sur un paquet dont la consigne annonce une tache absente | **partiellement.** Route a s32, non paye. **Le decoupage de la section 4.2 en reduit la surface sans la fermer** |
| **`FER-2`, `FER-3` sur les deux paquets d'EPS** | **moi, a la main.** Les tests de paquet a la `PaquetCm1EmcDroitDevoirTest` **n'existent toujours pas pour l'EPS** | **oui**, et je les ai comptes a la main pour la troisieme fois. **Route a `theo-archi` a s32 et a s33, non paye** |
| **`BIN-1` et `FER-1` sur `regle-souhait-loi`** | `theo-archi` les mord, **mais dans la mauvaise unite et la mauvaise tache** (verdict B, section 8.3) | **oui, et a corriger.** C'est un prealable de la reprise, pas une consequence |
| **`REG-R3`, `JEU-R12`** - le genre des personnages | **moi**, par comptage. **Mon propre chiffre etait faux** | **mesurable**, et **je ne pose toujours aucune barre chiffree** |
| **Le texte de l'ecran de fin de partie apres le remede de `SCO-1`** | **moi seule**, et c'est neuf a partir d'aujourd'hui | **non.** Le remede est entierement mordable ; la phrase que l'enfant lit ne l'est pas |
| **`SCO-1` a `SCO-7`** | **`theo-archi`, des aujourd'hui** : l'instruction 1 est rendue (section 4.1) | **oui, entierement**, et la regle qui refuse la strategie aveugle entre avec le remede |
| **`LON-1`, `LON-2`** | **`ADR-0089`. Je n'en suis plus la garde et je ne recompte pas** | **oui.** **Ma barre est necessaire, jamais suffisante** : elle n'a rien vu de `SEC-R4`, qui est un defaut a dix mots |
| **La couleur, le contraste, la taille, la position a l'ecran** | **personne sur mes terrains** (audit degrade, section 0) | hors de ma portee |

**Ce que je route, et a qui :**

| Ce que je route | A qui | Ce que je demande |
|---|---|---|
| **`RES-4`** - une reserve porte un objet distinct de son remede, verifiable sur la forme d'une fiche | `theo-archi` | **La propriete, jamais la forme du test.** C'est le seul garde-fou de ce depot qui rougirait sur un manquement de ma porte, et c'est pourquoi je le demande |
| **`COH-1`** - un paquet ne se contredit pas | `theo-archi` | Le symptome et la cause possible (un paquet ne declare pas l'objet de ses items). **Pas une demande de sprint** |
| **Les tests de paquet manquants pour l'EPS** (`FER-2`, `FER-3`, `BIN-1`, `FAB-1a`, `FAB-1b`) | `theo-archi` | **Troisieme routage.** Compter a la main sur vingt-deux items n'est pas une bonne facon de depenser une porte pedagogique |
| **`fer1...` et `bin1...` sur `regle-souhait-loi`**, qui operationnalisent mes clauses plus etroitement qu'elles | `theo-archi` | **Prealable a l'ouverture de la branche de `EMC-CP-07`**, pas une consequence |
| **L'instruction 1 de `DEC-0061`, rendue** | `theo-archi` | Sa phrase est en section 4.1, prete a etre citee. **L'`ADR` et sa regle lui appartiennent** |
| **Le trou de la regle du Plan 2** sur le reaudit d'un paquet ferme | `camille-po` | **Deuxieme routage**, et `camille-po` l'a mesure de son cote. Je nomme, je ne comble pas |
| **Le grain de `CONS-4`, rendu** ; **le grain du lot du corps, confirme** ; **la forme de la tache B de `exclusion`, rendue** | `camille-po` | Les trois decisions conjointes d'`ADR-0081` sont closes de mon cote |
| **Les trois tarifs du registre de dette** | `camille-po` | Section 6.2. **Son chiffre de 7,5 passe a 9,0 une fois les trois cases remplies** |

---

## 12. Ce que ce document ne fait pas

- **Il ne prononce aucun Done pedagogique.** Aucun increment de s34 n'existe.
- **Il ne rejuge aucun verdict de s33.** Les trois fermetures, les quatre refus, le paiement de
  `CAT-1` et le refus de `S33-EXC-R5` sont ceux que mes trois instances ont prononces le 2026-09-10.
  **Il ne rouvre aucune ligne `couvert-valide`**, y compris `EMC-CP-03`, sur laquelle il mesure une
  tache disparue.
- **Il ne durcit aucune reserve et n'en releve aucune barre.** `HG-R17`, `SEC-R4`, `SEC-R5`,
  `JEU-R9` a `JEU-R12`, `EMC-11`, `EXC-R6` a `EXC-R9`, `REG-R3` a `REG-R5`, `PER-R2`, `PER-R3`,
  `EMO-R3`, `POL-R3`, `POL-R4`, `ANG-R3` a `ANG-R6` sont **reexprimees**, jamais elargies. **Ce qui
  est neuf est neuf et date du 2026-09-10** : `RES-1` a `RES-4`, `COH-1`, `PAR-15`, `PAR-16`, le
  refus numero 8, et la correction de lecture de ma notation *"0 a 1"*.
- **Il n'efface aucun verdict et aucun refus.** L'exemple fautif du referentiel d'EPS CP est
  **remplace dans la cellule et conserve dans une note datee** qui dit ce qu'il etait et ce qu'il a
  coute.
- **`PAR-15` et `PAR-16` sont non retroactives** : elles ne rechiffrent ni s33, ni s32, ni le groupe
  2a, ni les neuf reserves de s31.
- **Il ne rouvre pas le Plan 2** (`DEC-0083`, `DEC-0085`) et ne chiffre aucune composition
  alternative.
- **Il ne touche pas a `squad/programme/MATRICE-couverture.md` ni a `docs/backlog/backlog.md`.** Il
  reprend les chiffres de la matrice tels qu'ils lui sont transmis - 69, 1, 68, 71, denominateur
  138 - **et ne les recalcule pas.**
- **Il ne recalcule pas le registre de dette de `camille-po`** : il remplit les trois cases qu'elle
  m'a explicitement laissees, et le total qui en resulte est le sien plus ces trois cases.
- **Il ne redige pas le `DEC`, ne priorise pas, n'ouvre aucun perimetre, n'ecrit aucune story et
  n'affecte personne.**
- **Il ne decide d'aucune question technique.** La forme du remede de `SCO-1`, le palier 2, les
  tests de paquet d'EPS, `RES-4` et `COH-1` sont des symptomes que je nomme et des causes que
  `theo-archi` traite. **Je dis la propriete, jamais la forme du test, et je ne pose aucun seuil.**
- **Il ne modifie aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/`.** J'ai
  joue onze parties completes sur sept paquets ; **je n'ai corrige aucun contenu.**
- **Il ne corrige ni la documentation technique, ni la Javadoc, ni le nommage, ni l'orthographe,
  l'accentuation ou la ponctuation du texte destine a l'enfant** : c'est le `relecteur-pedagogique`
  (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`).
- **Il ne dit rien de ce qu'il n'a pas mesure** : `CONS-3` reste **non chiffree**, le supplement
  d'image du palier 2 reste **incertain** faute de connaitre le nombre de zones, et le plafond de
  lignes sous `L-c` n'est pas de mon ressort.

---

## 13. Les identifiants alloues dans ce meme diff (`PAR-5`)

**Sept identifiants neufs, dont deux prefixes neufs** (`RES` et `COH`, verifies libres sur la table
de la section 4 du registre avant allocation). Ligne d'etat courant portee de **601 / 97 a 608 / 99**.

| Identifiant | Ce qu'il designe | Section |
|---|---|---|
| `RES-1` | Les cinq parties d'une reserve : constat, **objet**, **mode de defaillance**, perimetre mesure sur l'etat d'arrivee, regime | 3.2 |
| `RES-2` | Les quatre interdits : texte dicte non recolle, exemple non verifie, chiffrage mesure sur l'etat de depart, condition de retrait seul | 3.3 |
| `RES-3` | Le controle d'objet en trois lectures, sur le diff, avant de commiter | 3.4 |
| `RES-4` | Une reserve sans objet ecrit n'est pas engageable et revient a son auteur | 3.5 |
| `COH-1` | Deux items d'un meme paquet qui portent sur le meme objet ne se contredisent pas | 10 |
| `PAR-15` | Une reprise qui reecrit une tache entiere, ou qui change une consigne et la tache qu'elle nomme, se paie au tarif d'un lot. Non retroactive | 6.1 |
| `PAR-16` | Un poste sans story a l'ouverture du sprint n'est pas alloue contre ma borne. Non retroactive | 6.1 |

**Methode du comptage, a cote de son chiffre** : comptage des lignes de la table de la section 4 du
registre sous le motif du garde-fou lui-meme (`ALLOCATION`, identifiant et document entre accents
graves, `ADR-0085` clause 5), **avant** mon diff, sur l'arbre `48635ba0` - **601 identifiants
distincts, 97 prefixes distincts, 0 doublon**, ce qui confirme la ligne d'etat courant que
`sofia-sm` a portee a la review de s33. Plus mes sept allocations et mes deux prefixes neufs :
**608 / 99**.

**Ce que ce total est et ce qu'il n'est pas** : il est juste **sur mon worktree**, qui est le seul de
ce geste - je travaille seule, l'arbitrage prealable n'etant pas divise en instances. **La limite
structurelle de `PAR-5` que `theo-archi` a laissee nommee ne me concerne donc pas ici**, et il n'y
aura pas de reparation arithmetique a la fusion pour ce diff.

---

## 14. La porte technique sur mon propre diff - les quatre jobs, nommes un par un

**Je ne deduis aucun job d'un autre, et je ne confonds pas la CI avec `mvn verify`** (`CLAUDE.md`,
amendement du 2026-09-08). Lances sur l'arbre que je livre, **apres** l'ecriture de ce document et de
mes sept allocations.

| Job de `.github/workflows/ci.yml` | Ce que j'ai lance | Resultat |
|---|---|---|
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, puis **mon propre message de commit** passe a `verifier-prefixes.sh` | **vert** - *"Tous les cas passent : le garde-fou de prefixes mord, et sur le bon motif."*, *"Tous les cas passent : commit-atteignable.sh distingue un commit reel d'un commit disparu."*, *"Tous les commits portent un prefixe d'agent connu."* |
| `chemins` | `verifier-chemins-test.sh`, puis `verifier-chemins.sh` sur **l'union des fichiers suivis et non suivis**, avec l'exclusion que le job porte lui-meme (`.github/scripts/verifier-chemins-test.sh`, qui contient ses propres cas de test) | **vert** - *"Tous les cas passent : le garde-fou de chemins mord, et sur le bon motif."* puis *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* **Les fichiers non suivis sont inclus** : ce document et la correction du referentiel ne sont pas encore commites |
| `outils` | la boucle du job sur ses trois motifs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`), avec la regle du balayage vide | **vert** - **9 juges purs executes**, aucun motif vide |
| `build` | `mvn -B clean verify` sur la racine, reacteur complet a six modules, par l'enveloppe de toolchain | **vert** - `BUILD SUCCESS`, six modules `SUCCESS`, **1443 tests, 0 echec, 0 erreur, 0 ignore** (somme de l'attribut `tests` sur les **193** fichiers `TEST-*.xml` de `surefire-reports` et `failsafe-reports`, mesuree sur le disque apres ce `clean verify`). **JDK 21.0.10 rapporte par la machine virtuelle des tests elle-meme**, et non lu dans l'entree de toolchain (`ADR-0071`) |

**Le geste que ce depot a paye cher** - lancer la commande de la CI sur son propre diff avant de
commiter - **est fait ici, et les quatre jobs sont nommes plutot que deduits.** Le job `build` a ete
lance **apres** l'ecriture de mes sept allocations dans `squad/decisions/REGISTRE-identifiants.md` :
c'est donc le garde-fou d'`ADR-0085` qui a juge la ligne d'etat courant a **608 / 99**, pas moi.

**Une precision de methode, parce qu'elle a manque a s33.** Le compte de **1443** est identique a
celui de la cloture de s33 : **mon diff ne touche aucun fichier Java et n'ajoute donc aucun test**,
ce qui est exactement ce qu'on attend d'un arbitrage prealable. **Je le dis plutot que de laisser
deduire qu'un chiffre inchange serait une mesure oubliee.**

**Le garde-fou du registre, reverifie seul apres ma derniere retouche** :
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` rend **`Tests run: 7, Failures: 0,
Errors: 0`** sur l'arbre final, mesure partielle lancee **avec `-am`** comme `CLAUDE.md` l'exige.
**Et je dis a mon debit ce qui s'est passe au premier essai**, parce que c'est le meme piege qu'a
s33 : ma premiere commande a rendu `BUILD FAILURE` **a cause d'une de mes options** - Surefire
arrete le build quand un module amont ne porte pas le test nomme - **et non a cause de l'arbre**.
**Un `BUILD FAILURE` cause par mes propres options n'est pas une porte technique rouge**, mais il
peut etre lu comme tel par qui n'en voit que la derniere ligne. La mesure qui compte reste celle
du tableau : `mvn -B clean verify` sur la racine, reacteur complet, 1443 tests, 0 echec.

**Ce que je n'ai pas ecrit, et le motif** : je ne reprends pas de conclusion dans un `review.md`.
**s34 n'est pas ouvert et son repertoire de sprint n'existe pas** ; `review.md` est un fichier de
ceremonie de `sofia-sm`, et un arbitrage prealable n'a pas de review a alimenter. C'est le regime de
s32 et de s33. **Les conclusions de ce document sont reprises par `camille-po` dans le `DEC` de
strategie de s34, et par `sofia-sm` dans le planning ; ni l'un ni l'autre n'est mon fichier.**

**Hygiene de poste, puisque `yanis-qa` l'a signalee a s33 et que je suis concernee** : j'ai construit
le jar et joue mes onze parties **depuis un worktree dedie**, jamais sur le checkout partage, sur un
port local distinct, et **j'ai arrete le serveur par son identifiant de processus** - jamais par un
nom d'image. Le worktree est detache et sera retire ; le checkout partage n'a porte aucun serveur
pendant ce geste.
