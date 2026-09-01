# DEC-0068 - La voix est celle d'un adulte present : le produit ne fournit que la distinction entre ce que l'adulte lit et ce que l'enfant lit

- **Statut** : Acceptee, **amendee le 2026-09-01** (meme jour) : la portee est bornee par l'utilisatrice aux seules lignes ou la voix est la competence - aucun paquet CP deja valide ne change - et `nadia-prof` ouvre quatre des cinq lignes, une seule refusee. Voir l'amendement en fin de document.
- **Date** : 2026-09-01
- **Auteur** : `camille-po`
- **Sprint** : s26 (ouverture)
- **Arbre de reference** : worktree `s26-camille`, ouvert sur `round-two` a `96a80b4`
- **Ce que cette decision fait** : elle acte un arbitrage de l'utilisatrice, ecrit ce qu'il dit et ce
  qu'il ne dit pas, nomme l'obligation qu'il cree pour le produit, et instruit sa consequence de
  perimetre.
- **Ce qu'elle ne fait pas** : elle **n'ouvre aucune ligne de couverture**. Les cinq lignes de la
  famille audio sont instruites en parallele par `nadia-prof`, et **c'est son verdict qui decide, pas
  cette decision.**

---

## 1. L'arbitrage, cite sous la forme 2 de `DEC-0062`

> **De l'utilisatrice, 2026-09-01**, ses mots cites entre guillemets :
>
> **"pour les exercices avec la voix, un adulte va faire la lecture a l'enfant de cp il faudra juste
> distinguer ce qui doit etre lu de la consigne de l'enfant"**

*`DEC-0062` section 3.1 pose trois formes de provenance et une seule convient ici : la forme 2,
**"`de l'utilisatrice` - avec ses mots cites, entre guillemets, et la date"**. C'est la premiere
application reelle de cette forme depuis que `sofia-sm` a corrige sa transcription dans la charte du
relecteur a la cloture de s25 ; je la cite telle qu'elle est ecrite dans la decision qui fait foi, et
non telle que je m'en souviens.*

---

## 2. Ce que l'arbitrage dit

**Il repond a une question ouverte depuis le 2026-08-24 et routee a chaque sprint depuis** : quelle
est la source de la voix ? `DEC-0042`, arbitrage 2, avait nomme **deux voies et deux seulement** -
un corpus enregistre par une voix humaine, embarque dans le fat jar ; ou une synthese vocale
embarquee - et etabli qu'aucune des deux ne tenait sans un cout que l'utilisatrice ne prenait pas.

> **La reponse n'est aucune des deux. C'est une troisieme voie que personne n'avait instruite : il
> n'y a pas de voix dans le produit du tout.** Un adulte est present pendant la partie et lit a
> l'enfant. Ce que le produit fournit est **la distinction entre ce que l'adulte prononce et ce que
> l'enfant lit lui-meme**.

**Trois consequences immediates, et elles sont de fait, pas d'interpretation :**

- **Aucun fichier son n'entre dans le depot.** L'obstacle de production de `DEC-0042` - *"ce n'est ni
  du code ni du contenu YAML : c'est un enregistrement"* - **disparait, il n'est pas contourne.**
- **Le fat jar ne grossit pas.** L'alternative de synthese embarquee, chiffree en *"dizaines a
  centaines de megaoctets"*, **cesse d'etre une cible du produit** au meme titre que la persistance
  l'a cesse a `DEC-0028`.
- **Le seul livrable est une distinction dans le contenu et dans le rendu.** C'est une propriete de
  ce que l'enfant voit a l'ecran, pas une capacite technique neuve.

---

## 3. Ce que l'arbitrage **ne** dit **pas**, et je m'y tiens mot pour mot

**`DEC-0053` a derive dans ce sens sur l'arbitrage du pli** - une lecture plus large que les mots
recus, qu'il a fallu remonter a l'utilisatrice pour faire trancher. **Je ne recommence pas.** Voici
ce que ces vingt-huit mots ne portent pas :

| Ce qu'on pourrait etre tente d'en tirer | Pourquoi je ne l'ecris pas |
|---|---|
| *"Les cinq lignes de la famille audio sont ouvertes"* | **Elle a arbitre un moyen, pas un resultat.** Ce que ce moyen achete en couverture est un **jugement pedagogique**, et il appartient a `nadia-prof`. Si les cinq lignes ne sont pas servies ainsi, **ce n'est pas ce qu'elle a dit qui change, c'est ce que cela achete** |
| *"Le seuil de `nadia-prof` du 2026-08-15 est leve"* | Son seuil demande un son *"net, **identique a chaque ecoute**, et reecoutable a la demande"*. **Une voix humaine vivante est reecoutable a volonte et n'est identique a aucune ecoute.** L'arbitrage ne dit rien de ce seuil ; **le trancher est a elle** |
| *"Le produit peut verifier ce que l'adulte dit"* | Rien dans ces mots ne le suppose, et le produit n'en a aucun moyen. **Ce que l'adulte prononce sort de la porte technique entierement** : aucun test ne le couvre, aucune CI ne le voit |
| *"La reconnaissance vocale rouvre"* | Section 5.3. **Elle porte sur la voix de l'enfant recue par l'application ; cet arbitrage porte sur la voix d'un adulte emise dans la piece.** Ce ne sont pas deux degres d'une meme chose, ce sont deux directions opposees |
| *"Tout le catalogue de CP peut etre lu par l'adulte"* | Section 5.2. **La question est ouverte et elle n'est pas de moi** |

**Et une chose qu'elle ne dit pas non plus, dans l'autre sens** : elle n'exclut pas qu'un adulte lise
autre chose que ces exercices. Elle dit ce que le produit doit **distinguer** ; elle ne borne pas ce
que l'adulte fait de sa presence.

---

## 4. Ce que l'arbitrage rend opposable - la seule obligation neuve pour le produit

> **Sur tout contenu concerne, le produit distingue ce qui doit etre lu a voix haute par l'adulte de
> ce que l'enfant lit lui-meme.** C'est la moitie executable de l'arbitrage, et c'est la seule.

**Ce que cela suppose, et que je nomme sans le concevoir** :

- **Un contenu qui declare la distinction**, plutot qu'un rendu qui la devine. C'est la forme que ce
  depot a tenue partout - `ADR-0062` pour la part designee d'un enonce, `ADR-0073` pour
  l'illustration d'un item. **Deviner porterait la distinction dans le gabarit**, ce que ce produit
  s'interdit.
- **Un rendu qui la porte a l'ecran**, pour que l'adulte sache quoi dire sans avoir a le supposer.
- **Un garde-fou**, faute de quoi ce sera la promesse de plus qui n'est verifiee par rien - la
  famille de defaut que ce depot a sanctionnee quatre fois en deux sprints.

**Ces trois points sont a `theo-archi` pour la forme et a `ines-design` pour le rendu. Je ne tranche
aucun des trois** et je ne prejuge pas qu'ils soient couteux : `ADR-0073` a etabli qu'une famille
d'items neuve n'est ni une forme d'item neuve ni une mecanique neuve, et le meme raisonnement
pourrait valoir ici. **Ce serait une mesure, et elle n'est pas de moi.**

---

## 5. La consequence de perimetre - trois questions, dont une a laquelle le depot repond deja

### 5.1 Le modele du produit change-t-il ? **Non. Cet arbitrage est le second de la meme premisse, et le premier a huit jours.**

**On me demande si un adulte present pendant la partie est un fait neuf a acter.** J'ai verifie dans
le depot plutot que de le supposer, **et il ne l'est pas.**

> **De l'utilisatrice, 2026-08-25**, ses mots cites entre guillemets, dans
> `DEC-0046`, section 3 :
>
> **"considere qu'un eleve de CP est toujours accompagne par un adulte"**

**Cet arbitrage-la est acte, absorbe et operationnel** : `nadia-prof` a leve `SAI-11` sur
l'alphabetique **le jour meme** (`ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md`, second amendement
du 2026-08-25), en s'y etant engagee d'avance et *"sans rien y ajouter"*. **L'accompagnement durable
d'un adulte au CP est de la doctrine de ce produit depuis huit jours, pas une hypothese implicite.**

**Ce que l'arbitrage du 2026-09-01 ajoute** n'est donc pas la presence de l'adulte : c'est **un role
pour lui** - il lit - **et une obligation pour le produit** - distinguer. C'est un increment sur une
premisse posee, pas un changement de modele.

**Ce que je ne fais pas, et c'est un choix** : `CLAUDE.md` decrit *"une app web de revision scolaire
pour les enfants"* et ne mentionne l'adulte nulle part. **Deux arbitrages successifs le rendent
central au CP, et le fichier que tous les agents lisent ne le porte pas.** Je ne modifie pas
`CLAUDE.md` - ce n'est pas mon fichier et sa redaction ne m'appartient pas - **mais je route le
point plutot que de le laisser dans une decision que personne ne relit** : c'est exactement le motif
pour lequel `ADR-0010` a fini par etre contredit par une enumeration de `CLAUDE.md` qui avait derive.

### 5.2 Sur quelles lignes s'applique-t-il ? **Je ne le borne pas aux cinq, et je ne l'etends pas non plus.**

Ses mots disent *"pour les exercices avec la voix"*. **Cette formule designe au minimum les cinq
lignes de la famille audio ; elle ne dit pas qu'elle s'y arrete, et elle ne dit pas non plus qu'elle
va au-dela.**

**Le fait qui rend la question serieuse, et il est de `nadia-prof`, pas de moi** :

> *"Ma remarque la plus repetee sur ce produit depuis le sprint 0 est qu'un enfant de CP lit peu et
> lentement, et que chaque mot de consigne se paie. **Une application qui peut parler peut rendre
> lisible tout le catalogue CP, pas seulement trois lignes.**"*
> `AVIS-PRIORITE-PEDAGOGIQUE-s13-seconde-mecanique.md`, section 1.

**Elle l'avait ecrit d'une application qui parle ; un adulte qui lit le fait aussi, et gratuitement.**
Si la distinction devait valoir sur tout le catalogue CP, ce ne serait pas une ligne de couverture de
plus - **ce serait une propriete de chaque paquet de CP existant**, donc un cout porte par les onze
lignes deja vertes et par toutes les suivantes.

**Ce que je fais** : je pose la question, avec ce fait a cote, **et je ne tranche pas.** La portee
pedagogique est a `nadia-prof` ; l'extension eventuelle a l'existant est un arbitrage de
l'utilisatrice, parce qu'elle touche des jeux deja valides.

### 5.3 La reconnaissance vocale sort-elle de `non couvrable en l'etat` ? **Non, et je ne bouge rien.**

**Le critere de la matrice est ecrit et je l'applique plutot que de le contourner** : seule une
decision de l'utilisatrice **sur la contrainte fondatrice en cause**, ou un amendement du
referentiel, peut faire sortir une ligne de cet etat.

**La contrainte en cause, telle que `DEC-0042` section 3 l'ecrit, porte sur la voix de l'enfant
*recue* par l'application** : un fat jar qui appellerait un service distant ou embarquerait un modele
acoustique ; un verdict rendu par un moteur probabiliste, non testable cote serveur ; le pilotage
d'un micro loge dans le JavaScript client ; et une question de donnee personnelle sur la voix d'un
enfant.

> **L'arbitrage du 2026-09-01 ne touche aucun de ces quatre points.** Il porte sur une voix
> **emise** par un adulte dans la piece, que l'application ne capte pas, n'analyse pas et ne
> transporte pas. **Ce n'est pas une decision sur la contrainte fondatrice en cause. La ligne ne
> bouge pas.**

**Et l'adjacence que je nomme plutot que de la taire, parce qu'elle sautera aux yeux de quelqu'un
d'autre sinon** : le motif *"irreversible"* que j'avais ecrit sur cette ligne le 2026-08-24 dit
*"juger une lecture a voix haute demande un humain qui ecoute ; **aucune decision produit ne
debloquera jamais cette ligne**"*. **Un humain qui ecoute est desormais dans la piece.** Ce
*"jamais"* est moins evident qu'il ne l'etait.

**Ce que cela ne suffit pas a faire, et pourquoi je ne bouge toujours rien** : il faudrait que le
produit **sollicite et enregistre le jugement d'un adulte** comme verdict d'une manche - une
mecanique qui n'existe pas, qui n'a jamais ete instruite, et dont `nadia-prof` a ecrit qu'elle ne la
reclamerait pas : *"la fluidite de lecture s'evalue par un adulte qui ecoute, avec un jugement sur le
ton, les liaisons, le respect de la ponctuation [...] **Cette ligne-la, je ne la reclamerai pas.**"*

**Je route donc la question, avec cette phrase a cote, et je laisse le statut inchange.** Trois
personnes devraient parler avant qu'il bouge, et aucune ne l'a fait.

---

## 6. Ce que cela coute, et ce que cela peut rapporter - les deux lectures, aucune tranchee

**Le cout, dans l'unite de chacun :**

| Poste | Qui le porte | Ce que je peux en dire aujourd'hui |
|---|---|---|
| L'instruction pedagogique des cinq lignes sous ce moyen | `nadia-prof` | **En cours, en parallele de ce document.** Aucun chiffre |
| La declaration de la distinction dans le contenu, son rendu, son garde-fou | `theo-archi`, `ines-design` | **Non chiffre, et non demande a ce jour.** Je le route en section 7 |
| L'audit d'un premier paquet sous ce moyen | `nadia-prof` | Un paquet contre sa borne, si elle ouvre. **Sa borne de s26 n'est pas rendue** |
| Ce que ce moyen **ne** coute **pas** | - | Aucun fichier son, aucun grossissement du jar, aucune dependance reseau, aucune capacite technique neuve |

**Ce que cela peut rapporter - deux lectures, et je refuse d'ecrire un plafond avant le verdict de
`nadia-prof`** :

- **Lecture basse** : elle n'ouvre aucune des cinq. Le plafond de s26 reste celui de `DEC-0067` -
  **32 / 41 = 78,0 %**, par `LIS` seule, elle-meme conditionnee a `DV-4`. **Cet arbitrage n'aurait
  alors rien achete en couverture, et il resterait juste : elle a arbitre un moyen.**
- **Lecture haute** : elle ouvre tout ou partie des cinq. **Chaque ligne ouverte est une ligne
  au-dessus de 32.** *Methode du denominateur : 41, inchange - cet arbitrage ne fait sortir aucune
  ligne du statut `non couvrable en l'etat`, section 5.3.*

> **Je n'ecris aucun des deux comme le plafond de s26, et j'attends son verdict.** Ecrire un chiffre
> maintenant serait presenter comme acquis ce dont le depot a montre quatre fois ce sprint qu'une
> clause juste en intention peut etre fausse en extension.

**Une chose que je peux dire des maintenant sans prejuger d'elle** : trois des cinq lignes portent
des conditions deja ecrites - `SYL-1` a `SYL-4` pour *decoder par assemblage*, et la barre du
2026-08-10 pour les graphemes. **Elles ont ete ecrites en supposant des enregistrements.** Une voix
vivante les rencontre differemment : *"chaque proposition est reecoutable autant de fois que l'enfant
le veut"* devient trivialement tenu, *"identique a chaque ecoute"* ne l'est plus. **Ces deux
observations sont de forme, pas de fond, et elles ne valent pas verdict.**

---

## 7. Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **Les cinq lignes de la famille audio sous ce moyen** - ouvre-t-il, et lesquelles ? | `nadia-prof` | **Son verdict decide, pas cette decision.** Avec, si elle ouvre, les conditions ecrites pour etre reprises telles quelles en criteres |
| **Son seuil du 2026-08-15** - *"net, identique a chaque ecoute, reecoutable a la demande"* face a une voix vivante | `nadia-prof` | Il est d'elle et il n'est pas touche par l'arbitrage. **Le trancher est a elle** |
| **La portee** - les cinq lignes seulement, ou tout contenu de CP ? (5.2) | `nadia-prof` pour la portee pedagogique ; **l'utilisatrice** si cela devait toucher des jeux deja valides | Avec le fait de `nadia-prof` du 2026-08-15 a cote : *"une application qui peut parler peut rendre lisible tout le catalogue CP"* |
| **La forme de la distinction** - declaree par le contenu, rendue a l'ecran, gardee par un test | `theo-archi`, `ines-design`, par `sofia-sm` | **Un ordre de grandeur, pas une conception.** Rien ne l'a encore demande formellement |
| **`CLAUDE.md` ne porte pas l'adulte accompagnant**, que deux arbitrages rendent central au CP (5.1) | `sofia-sm`, ou l'utilisatrice | Ce n'est pas mon fichier. **Une premisse qui vit dans deux `DEC` et pas dans le fichier que tous lisent finit par etre contredite par lui** |
| **La reconnaissance vocale reste `non couvrable en l'etat`**, et l'adjacence est nommee (5.3) | `nadia-prof`, puis l'utilisatrice | **Je ne bouge rien.** Trois personnes devraient parler avant que ce statut bouge, et aucune ne l'a fait |

---

## 8. Ce que cette decision ne fait pas

- Elle **n'ouvre aucune ligne**, n'en fait bouger aucune, et **ne touche pas la matrice** au-dela
  d'une note datee qui enregistre la levee de l'obstacle de production **sans changer un statut**.
- Elle **ne prononce aucun Done** et ne prejuge d'aucun verdict de `nadia-prof`.
- Elle **n'ecrit aucun plafond de couverture pour s26** : les deux lectures de la section 6 sont
  posees et aucune n'est retenue.
- Elle **n'elargit ni ne durcit** les mots recus, et la section 3 dit nommement les cinq elargissements
  qu'elle refuse.
- Elle **ne fait sortir aucune ligne de `non couvrable en l'etat`**, faute que le critere de la
  matrice soit rempli.
- Elle **ne tranche rien de technique** : ni la forme de la distinction, ni son cout, ni son garde-fou.
- Elle **ne modifie pas `CLAUDE.md`** et se contente de router le point.
- Elle **ne deplace aucune dette rangee** : le trou du JavaScript, le score parfait par elimination,
  le garde-fou *"un rang suffit"* et les trois identifiants que rien n'execute gardent le rang que
  `DEC-0067` leur a donne. **Aucun ne rapporte de ligne, l'objectif de l'utilisatrice est toujours la
  couverture, et ce conflit reste ecrit et non arbitre** - `DEC-0067`, section 5.

---

## Amendement du 2026-09-01 (meme jour) - la portee est bornee par l'utilisatrice, et `nadia-prof` ouvre quatre lignes sur cinq

**Statut de la decision apres cet amendement : Acceptee, amendee le 2026-09-01.**

**Les deux questions que la section 7 routait sont rendues.** Le texte d'origine n'est pas efface :
il posait les deux lectures sans en retenir aucune, et c'etait son objet.

### A.1 La portee - l'utilisatrice borne, et c'est la borne que je demandais

Je posais en section 5.2 : les cinq lignes seulement, ou tout contenu de CP ? La question a ete
transmise avec le fait de `nadia-prof` a cote - *"une application qui peut parler peut rendre lisible
tout le catalogue CP"* - et avec ce que j'en tirais : *"si cela valait sur tout le CP, ce ne serait
pas une ligne de plus, ce serait une propriete de chaque paquet existant"*.

> **De l'utilisatrice, 2026-09-01**, ses mots cites entre guillemets :
>
> **"Les cinq lignes seulement"**

**Ce que cette borne dit, et je l'ecris comme une regle opposable parce que c'est a cela qu'elle
sert :**

> **L'adulte lecteur est un moyen de jeu reserve aux exercices ou la voix est la competence
> elle-meme. Il n'est pas un moyen de lisibilite generale, et il ne s'etend a aucun paquet dont la
> competence visee n'est pas la voix.**

**Trois consequences, et la troisieme est celle qui compte :**

- **Aucun paquet de CP deja valide ne change. Aucun Done n'est rouvert.** Les onze lignes vertes du
  produit ne recoivent ni champ neuf ni obligation neuve de ce fait.
- **La lisibilite reste une exigence de contenu, entiere.** L'adulte ne dispense d'aucune des barres
  que `nadia-prof` tient depuis le sprint 0 - *"chaque mot de consigne se paie au CP"*. **Un paquet
  illisible ne devient pas acceptable parce qu'un adulte est dans la piece.**
- **C'est cette borne qui empeche l'elargissement silencieux que je craignais en section 5.2.** Un
  moyen qui aurait pu, de proche en proche, devenir la reponse a toute difficulte de lecture au CP
  est arrete par une phrase de quatre mots. **Elle a choisi en connaissance des deux autres options**
  - l'extension a tout le CP, et une instruction prealable du cout.

*Cette borne est desormais aussi dans `CLAUDE.md`, section "L'enfant de CP n'est jamais seul", ecrite
par le fil d'animation apres reponse de l'utilisatrice a la question que la section 7 routait. **Enonce
recu par le fil d'animation, sans source dans le depot au moment ou je l'ecris** (forme 3 de
`DEC-0062`) : "c'est ecrit par le fil d'animation, pas par un agent". Je le note comme tel, et le
fichier, lui, se lit directement.*

### A.2 Le verdict de `nadia-prof` - quatre lignes ouvertes, une refusee

`squad/conformite/ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md`, sections 3 et 4. **Une seule
regle trie les cinq lignes, et elle n'a pas ete taillee ligne par ligne :**

> **`VOI-1` - L'adulte prononce le stimulus ; il ne prononce jamais une proposition.**

| Ligne | Ce que l'adulte dirait | Verdict |
|---|---|---|
| Localiser une syllabe dans un mot | le mot, et la syllabe cherchee | **SERVIE** |
| Identifier un son (phoneme) dans un mot | le mot | **SERVIE** |
| Associer les graphemes aux phonemes (`en cours` depuis s03) | le mot ou le son | **SERVIE - la ligne peut se fermer** |
| Decoder un mot regulier par assemblage syllabique | **les trois propositions** | **REFUSEE** |
| Ecrire de maniere autonome un mot simple | le mot a ecrire | **SERVIE** |

**Le motif du refus, dans ses mots, et il est actionnable** : *"Trois propositions entendues.
Prononcees par qui ? Par l'adulte, qui lit l'ecran, **donc qui sait laquelle est la bonne**. [...]
Ce n'est pas un soupcon sur les adultes : c'est ce que fait n'importe qui qui connait la reponse."*
C'est la seule des cinq **dont les propositions sont des sons**.

**Elle ne ferme pas la porte et je reprends sa phrase telle quelle** : *"Si quelqu'un trouve une
forme sans proposition sonore, qu'il me la soumette : **je n'oppose pas un principe, je constate une
impasse.**"* Les deux voies qu'elle nomme sont un corpus enregistre - la voie de `DEC-0042`, toujours
fermee - ou une captation de la voix de l'enfant, *"que je n'ai jamais reclamee et que je ne reclame
pas"*.

### A.3 Le fait qui domine son arbitrage, et il est de nous tous

> **`DEC-0042` ne bloquait pas l'audio comme famille. Il bloquait les deux seules sources que la
> squad savait envisager - un corpus embarque, ou la synthese vocale. Un adulte n'est ni l'un ni
> l'autre.**

**Ces cinq lignes ont attendu six sprints derriere un obstacle qui n'a jamais ete pedagogique.**

**J'ecris ce fait dans ma decision plutot que de le laisser dans la sienne**, parce qu'il porte sur
la maniere dont j'instruis : ma section 2 disait deja *"la reponse n'est aucune des deux"*, mais
`DEC-0042`, que j'ai ecrite, presentait ces deux voies comme **l'espace des possibles** sans jamais
dire qu'il n'etait pas exhaustif. **Une enumeration qui ne se declare pas partielle se lit comme
complete**, et c'est ce qui a tenu cinq lignes fermees pendant six sprints.

**Ce que `nadia-prof` ajoute et que je verse sans l'attenuer** : *"le tort n'est de personne - la
question etait posee, ecrite et routee"*. Elle l'etait, a chaque sprint. **Ce n'est pas un defaut de
gouvernance, c'est un defaut d'imagination collective, et il n'a coute aucune ligne definitivement.**

### A.4 Ses deux barres tombent par leur motif, et elle invite a verifier sa constance

Je posais en section 3 que son seuil du 2026-08-15 - *"net, **identique a chaque ecoute**,
reecoutable a la demande"* - n'etait pas touche par l'arbitrage et restait a elle. **Elle l'a
tranche, et dans le sens que je n'avais pas presume.**

| Clause | Un adulte qui lit |
|---|---|
| net | **tenue, et mieux qu'un enregistrement** |
| reecoutable a la demande | **tenue, et mieux** - *"l'enfant demande, l'adulte redit. Aucun bouton n'obeit aussi bien"* |
| identique a chaque ecoute | **NON TENUE**, et **elle ne la maintient pas** |

**Son motif, et c'est la lecture par le motif et non par la lettre** : *"Ce que je craignais, c'est
l'enfant bloque - pas la variation en soi. [...] Un adulte est variable mais **repondant**, et c'est
exactement l'inverse du defaut vise."* Meme geste sur sa barre du 2026-08-10 sur les digraphes :
*"ce que j'exigeais, c'est qu'un son existe dans la piece - pas qu'un haut-parleur le produise"*.

**Elle demande qu'on verifie sa constance plutot que de la croire, et elle compte elle-meme** :
*"c'est la quatrieme fois que je tranche ainsi - `MON-1`, `FIG-2`, la prose de `CON-6` contre sa
clause, et ici. Un tiers peut verifier que je suis constante."* **Je le note pour que le controle
soit possible ; je ne le fais pas a sa place, ce n'est pas mon perimetre.**

**Et une de ses conditions prevoyait sa propre levee** : `SAI-11` interdisait la saisie alphabetique
au CP *"tant qu'aucun arbitrage de l'utilisatrice n'a repondu"*, et sa barre disait *"un enfant
accompagne peut taper un mot, un enfant seul devant une tablette non"*. **La condition etait
conditionnelle et sa condition est remplie.** Elle demande que ce ne soit pas lu comme un
assouplissement de sa part, et elle a raison de le demander.

### A.5 Ce que l'obligation opposable de la section 4 devient, maintenant qu'elle est instruite

Ma section 4 posait *"le produit distingue ce qui doit etre lu par l'adulte de ce que l'enfant lit"*
et disait que la forme etait a `theo-archi`. **`nadia-prof` la precise sans la concevoir : il faut
quatre textes, pas deux.**

| # | Texte | Ce qui arrive s'il se melange |
|---|---|---|
| 1 | ce que l'adulte dit a voix haute | rien : c'est le texte que l'utilisatrice nomme |
| 2 | ce que l'enfant lit ou voit | rien : c'est l'autre texte qu'elle nomme |
| 3 | **ce que l'adulte ne doit pas dire** | **la reponse fuit par la voix** |
| 4 | **ce que l'enfant ne doit pas voir** | le mot ecrit reapparait, et son refus du 2026-08-31 redevient valable |

**Et elle refuse la forme naive du troisieme, ce qui est le geste le plus utile de son document** :
pas de champ *"ce que l'adulte ne doit pas dire"* - *"une liste d'interdits est une liste qu'on
oublie de tenir a jour"* - mais **un champ clos** :

> **`VOI-10` - Le texte que l'adulte prononce est un champ distinct, et il ne contient jamais une
> proposition ni la reponse.** *Calculable : aucune chaine du champ 1 ne contient une chaine du jeu
> de propositions de son item.*

**Si le champ 1 ne peut pas contenir de proposition, il n'y a rien a interdire.** C'est le meme geste
qu'`ADR-0073` a fait sur l'illustration : fermer par construction plutot que par vigilance.

**Une exigence bloquante, et c'est la seule du document** :

> **`VOI-12` - L'ecran dit a l'adulte ce qu'il ne doit pas faire, en une phrase, a l'endroit ou il lit
> son texte.** Pas dans une documentation, pas dans un `ADR`, pas dans un README.
>
> *"Sans `VOI-12`, je refuserai les paquets a voix. Non pas parce que le contenu serait mauvais, mais
> parce qu'un produit qui confie une condition a quelqu'un **sans la lui dire** ne l'a pas confiee :
> il l'a abandonnee."*

**Elle explique pourquoi elle y tient, et cela vaut d'etre lu par quiconque ecrira ces paquets** :
`VOI-3`, `VOI-5` et `VOI-8` interdisent des gestes qu'un adulte fait **naturellement** en aidant un
enfant de six ans - detacher les syllabes, appuyer le son cherche, epeler. *"Ce ne sont pas des
fautes d'inattention, c'est le reflexe d'un parent bienveillant."*

**Et le texte de `VOI-12` est du contenu, pas de l'interface** : elle le juge a sa porte comme le
reste. Il ne suit pas le registre de `DEC-0002` - il n'est pas destine a l'enfant - mais les
conventions de redaction du depot, ce qui est au `relecteur-pedagogique` de confirmer.

### A.6 Trois conditions que personne ne garde - une premiere sur ce depot, et je la porte

**`VOI-3`, `VOI-5` et `VOI-8` sont les premieres conditions de ce depot dont `nadia-prof` n'est pas
la garde.** Ses mots : *"Le manquement se produit dans la bouche d'un adulte, dans une cuisine, et il
ne laisse **aucune trace** - ni test, ni capture, ni journal, ni fichier. Je ne peux pas l'auditer,
meme en jouant."*

**Elle l'accepte, et son critere n'est pas le confort** : *"un adulte qui aide trop degrade
l'exercice. **Il n'enseigne rien de faux.** [...] Le pire cas est un exercice sans valeur, pas un
exercice nuisible"* - a comparer avec `cm1-substituts` a 94,8 pour cent, qui *"enseignait une
strategie fausse et renforcait l'enfant dedans"*. **Ce n'est pas la meme famille de degat.**

**Ce que j'en fais, et c'est de mon perimetre** : ces trois conditions doivent etre **ecrites comme
non gardees** partout ou elles apparaissent - dans la story, dans la fiche de conformite, dans le
rapport de sprint. **Une condition dont personne ne sait si elle est tenue et qu'on ecrit comme les
autres est exactement la promesse plus large que ce qu'elle verifie que ce depot a sanctionnee sept
fois en deux sprints.** Elle demande que `sofia-sm` le porte au rapport de sprint ; **je le porte
aussi dans les stories, ce qui est ma part.**

**Et ce qu'elle refuse d'avance, que je reprends sans le discuter** : *"Je ne demanderai jamais un
dispositif qui verifie ce que l'adulte a dit [...] Ce serait mettre un micro dans une cuisine pour
surveiller un parent qui aide son enfant, et **aucune ligne de couverture ne vaut cela.**"* **Cette
phrase vaut pour moi comme pour les autres**, y compris le jour ou un compteur de couverture y
gagnerait.

### A.7 Ce que cet amendement ne fait pas

- Il **ne fait bouger aucune ligne de la matrice** : aucun jeu n'existe, aucun Done n'est prononce.
- Il **ne fixe pas le perimetre de s26** : le rang et le plafond sont a `DEC-0067`, ou ils vivent.
- Il **n'etend la portee a rien** : la borne de A.1 est la seule lecture que ce document autorise.
- Il **ne reclasse pas la ligne refusee** : *"decoder un mot regulier par assemblage syllabique"*
  reste **`non couvert`**, pas `non couvrable en l'etat` - `nadia-prof` constate une impasse et non
  un principe, et deux voies sont nommees. **Le critere de la matrice n'est pas rempli.**
- Il **ne touche pas** au statut de la reconnaissance vocale, pour les motifs de la section 5.3.
