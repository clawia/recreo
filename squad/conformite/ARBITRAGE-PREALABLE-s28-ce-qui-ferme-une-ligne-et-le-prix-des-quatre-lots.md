# Arbitrage prealable de s28 - ce qui ferme une ligne, ce que valent vraiment les quatre lots,
# et pourquoi les quatorze lignes ouvertes ne peuvent pas fermer ensemble

- **Auteur** : `nadia-prof`. **Date** : 2026-09-03. **Sprint** : s28, avant planning, juste apres
  la cloture de s27.
- **Branche** : `round-two` a `c9a9e44` (cloture de s27, matrice a 35/75).
- **Perimetre arbitre par l'utilisatrice le 2026-09-03**, trace en `DEC-0074` par `camille-po` :
  quatre lots - second paquet de questionner le monde CP, second paquet d'histoire-geographie CM1,
  anglais CM1 (reporte de s27 par `DEC-0072`), un lot de mathematiques CM1 sur les deux lignes
  `non couvert` de "Nombres et calculs".
- **Ce que ce document est** : mon prix recompte, la regle de fermeture ecrite **avant** que le
  contenu ne soit produit, et les conditions opposables aux quatre lots. **Il ne prononce aucun
  Done pedagogique** : aucun increment de s28 n'existe. **Il n'ecrit ni la matrice ni le backlog**
  (`camille-po`) et **il ne priorise pas** (l'utilisatrice).
- **Audit degrade, et je le declare** : je n'ai joue aucun increment pour ce document. Je n'ai pas
  non plus rejoue les increments de s27 - je les ai joues avant-hier et mes verdicts tiennent. Ce
  que j'ai regarde ici : les quatre fichiers de contenu de s27, les quarante-cinq entrees du
  catalogue servi, les quatre referentiels neufs, les compteurs d'items des 44 paquets du depot,
  et le code du tirage des manches (`ListContentSource`). C'est une mesure sur des fichiers, pas
  une lecture de description - mais ce n'est pas un audit de jeu, et rien de ce qui suit n'est un
  verdict sur un increment.
- **Ce que je ne traite pas ici** : les dix referentiels des enseignements absents (autre tache,
  fichiers disjoints, `squad/programme/`) et l'ADR du palier 2 (`theo-archi`).

---

## 1. Trois corrections que je fais a mes propres constats avant de m'en servir

C'est ma deuxieme habitude et elle passe avant tout le reste : je verifie ce que j'ai ecrit avant
de m'appuyer dessus. Les trois corrections ci-dessous portent sur des faits que j'ai moi-meme
enregistres il y a moins de trente-six heures.

### 1.1 Mon motif de s27 pour ne pas fermer est epuise, et le vrai motif est ailleurs

**Ce que j'ai ecrit le 2026-09-02** (arbitrage prealable de s27, section 8.4) : *"Les paquets de
matiere neuve n'en fermeront aucune. [...] Il rencontre une barre que je n'ai jamais appliquee,
sur une matiere ou je ne suis pas la source, avec des conditions - `REC-*`, `BIA-*`, `HIS-*` -
qu'aucun paquet n'a encore eu a tenir. La probabilite qu'un premier paquet les tienne toutes du
premier coup est faible."*

**C'etait une probabilite, pas un fait, et l'audit l'a dementie.** A la porte de s27 : `HIS-1`
tenue et mesuree a 25,0 %, `HIS-2` tenue sur les items joues, `HIS-3` tenue, `BIA-5` tenue, aucune
erreur de fait sur onze faits verifies un par un, `BIA-4` tenue sur questionner le monde. Cinq
verdicts VALIDE et un VALIDE SOUS RESERVE non bloquante, levee le jour meme.

**Donc : si je maintiens les quatorze lignes ouvertes, ce ne peut pas etre sur ce motif-la.** Le
garder serait deplacer mes poteaux dans le sens qui m'arrange - refuser de fermer parce que
j'avais annonce que ca ne fermerait pas. Je ne le fais pas.

**Le motif qui tient, et je ne l'avais pas mesure a s27, alors que le chiffre etait sous mes
yeux** : `cm1-histoire-geographie.yaml` sert **onze lignes de referentiel avec vingt items**.
**1,8 item par competence.** Six des onze lignes sont servies par **exactement un item**. Une
competence servie par un item n'est pas couverte : elle est illustree une fois. **C'est cela le
motif, il est mesurable, et il ne depend d'aucune probabilite.** Je le tourne en regle en
section 2, pour qu'il soit opposable a moi comme aux autres.

### 1.2 La dette des paquets a voix touche six jeux, pas trois - et cinq indices, pas un

**Le backlog dit trois paquets** (`docs/backlog/backlog.md`, section "Reserve pour s28") :
`bandit-manchot-lettre-du-son-cp`, `bandit-manchot-digraphe-du-son-cp`,
`bandit-manchot-voyelle-du-son-cp`. **C'est ce que j'avais ecrit, et c'est faux par defaut.** Je
l'ai mesure sur le catalogue servi et sur les fichiers de contenu, entree par entree.

| Jeu | Consigne (catalogue) | Enonce (tous les items) | Redondant ? |
|---|---|---|---|
| `bandit-manchot-position-syllabe-cp` | *"Écoute l'adulte lire le mot, puis dis si le son cherché est au début, au milieu ou à la fin."* | *"Écoute l'adulte, puis choisis la bonne réponse."* | **oui** |
| `bandit-manchot-son-dans-mot-cp` | *"Écoute le mot que l'adulte va dire, puis réponds oui ou non."* | *"Écoute l'adulte, puis réponds oui ou non."* | **oui** |
| `bandit-manchot-lettre-du-son-cp` | *"Écoute l'adulte lire le mot, puis choisis la lettre qui fait le tout premier son."* | identique moins trois mots | **oui** |
| `bandit-manchot-digraphe-du-son-cp` | *"...puis choisis le groupe de lettres qui fait ce son."* | identique moins trois mots | **oui** |
| `bandit-manchot-voyelle-du-son-cp` | *"...puis choisis la lettre qui fait le tout premier son."* | identique moins trois mots | **oui** |
| `saisie-mot-dicte-cp` | *"Écoute le mot que l'adulte va dire, puis écris-le."* | *"Écris le mot que tu entends."* | **partiellement** |

**Six jeux, pas trois.** Et sur l'indice, le compte est le meme : cinq des six invitent l'adulte a
modifier son debit.

| Jeu | Indice servi, identique sur tous les items | Verdict |
|---|---|---|
| `cp-position-syllabe` | *"Demande à l'adulte de redire le mot doucement, puis compte les syllabes dans ta tête."* | **deux defauts**, ci-dessous |
| `cp-son-dans-mot` | *"Demande à l'adulte de redire le mot lentement, puis écoute bien le son qu'il te demande de chercher."* | **le pire des cinq** |
| `cp-lettre-du-son` | *"...de redire le mot doucement, puis écoute le tout premier son."* | *doucement* |
| `cp-digraphe-du-son` | *"...de redire le mot doucement, puis pense au son que tu entends."* | *doucement* |
| `cp-voyelle-du-son` | *"...de redire le mot doucement, puis écoute le tout premier son."* | *doucement* |
| `cp-mot-dicte` | *"Demande à l'adulte de redire le mot autant de fois que tu en as besoin."* | **conforme a `VOI-8`, aucun defaut** |

**`cp-son-dans-mot` dit *lentement*.** `VOI-3` exige que l'adulte dise le mot *"d'un seul tenant, a
vitesse normale"*. **Le produit demande donc a l'enfant de demander a l'adulte exactement ce que
`VOI-3` lui interdit**, en toutes lettres, sur un paquet dont la competence est d'entendre un son
dans un mot - c'est-a-dire la ou un mot ralenti donne la reponse. C'est plus grave que la reserve
que j'avais ecrite.

**`cp-position-syllabe` porte un second defaut, distinct** : son indice dit *"compte les syllabes
dans ta tête"* alors que la question porte sur la **position** d'un son (debut, milieu, fin).
L'indice ne nomme pas l'operation de l'item. Un indice qui envoie l'enfant sur une autre operation
que celle qu'on lui demande n'aide pas : il deroute.

**Ce que cette correction ne fait pas** : elle **ne revoque aucun Done**. Les six jeux restent
valides, les lignes de matrice concernees restent `couvert-valide`. C'est une reserve **nouvelle,
datee du 2026-09-03, elargie par ma propre mesure**, pas un durcissement d'une barre ancienne -
meme regime que ce que j'ai ecrit a la porte de s27 : *"je vois plus loin ; je l'ecris comme neuf"*.

### 1.3 Ce que mon audit de s27 n'a pas couvert, et personne ne l'a dit

**J'ai verifie l'exactitude de onze faits d'histoire "sur les items joues".** C'est ecrit tel quel
dans `FICHE-bandit-manchot-histoire-geographie-cm1.md`, section 1. Or une partie compte **dix
manches** et le paquet porte **vingt items**, tires **uniformement sans remise** parmi les items
non encore vus - verifie dans `ListContentSource.next`, pas suppose. **J'ai donc joue la moitie du
paquet et lu l'autre moitie.** L'exactitude, je l'ai bien controlee partout. **La plausibilite des
distracteurs, non** : je ne l'ai verifiee que sur ce que j'ai tire.

**Et il y a au moins un item ou elle ne tient pas**, trouve en relisant le fichier aujourd'hui :

> *"Dans un espace rural, quelle activité occupe le plus souvent les habitants qui y travaillent ?"*
> - bonne reponse *l'agriculture*, distracteurs *la banque et l'assurance*, *le tourisme de
> croisiere*.

**Les deux distracteurs sont absurdes dans un espace rural.** Un enfant qui ne sait pas ce qu'est
un espace rural elimine les deux et repond juste : l'item ne teste rien, c'est la contrefacon que
`CON-1` decrit, transposee au distracteur. **Et la bonne reponse reduit l'espace rural a un seul
trait**, ce qui est `BIA-2` transposee du pays au territoire - et ce qui est de surcroit fragile
sur le fond, la majorite des actifs vivant en espace rural en France ne travaillant pas dans
l'agriculture.

**Consequence de methode, et elle vaut pour tout s28** : sur un paquet de vingt items joue en dix
manches, **la moitie des items n'est jamais vue en jouant**. Mon audit doit les lire un par un
pour les proprietes qui se jugent a l'item - exactitude, plausibilite des distracteurs, longueur,
vocabulaire. **C'est un cout par item, pas par partie**, ce qui confirme l'unite de ma borne
arretee le 2026-09-03 et ce qui rend le prix de la section 4 incompressible.

---

## 2. Ce qui ferme une ligne - trois conditions, ecrites d'avance et opposables a moi

**C'est ce qui manquait, et l'absence est de moi.** J'ai prononce trente-cinq fermetures sans
jamais ecrire ce qui fermait. Tant que la regle n'existe pas, "la ligne ferme" veut dire "Nadia a
trouve que c'etait bien", ce qui n'est pas une porte, c'est une humeur. Les trois conditions
ci-dessous sont **nouvelles, datees du 2026-09-03**, et elles s'appliquent a partir de s28.

> **`FER-1` - extension.** Chaque element enumere par le libelle de la competence, et notamment
> par sa parenthese, est servi par **au moins un item ou il est en position de bonne reponse**. Un
> element hors de portee du palier 1 ne bloque pas la fermeture, mais la ligne ne ferme alors que
> sur sa part servie, **avec la mention de couverture partielle ecrite dans la matrice** - le
> regime deja applique aux fractions (`FRA-9`) et exige d'avance sur la frise et sur le document.
>
> **`FER-2` - densite. Au moins dix items servent la ligne**, tous paquets confondus.
> *Methode, et c'est un plancher que je lis chez moi, pas une barre que j'invente* : sur les
> trente-cinq lignes que j'ai fermees, **aucune n'a jamais partage son paquet avec une autre
> ligne**, et le plus petit paquet ayant ferme une ligne en porte **onze**
> (`cp-complements-10.yaml`) ; les suivants en portent douze, et la mediane du depot est de seize.
> **Je fixe la barre a dix, c'est-a-dire sous mon propre plancher mesure**, pour qu'on ne puisse
> pas me reprocher de l'avoir relevee. `FER-2` est **necessaire et non suffisante**.
> *Exception nommee* : un paquet **genere** (`cp-additions-jusqu-a-10.yaml`, `type:
> additions-bornees`) n'enumere pas d'items ; c'est l'espace engendre par ses parametres qui tient
> le role du compte. `FER-2` ne s'applique qu'aux paquets enumeres.
>
> **`FER-3` - deux taches.** Les items qui servent la ligne ne posent pas tous la meme tache. Au
> moins deux taches distinctes, la moins servie portant **au moins trois items**.
> *Precision, parce que je me contredirais sans elle* : j'ai loue a s27 la **regularite de la
> phrase** sur `cp-qlm-vivant-non-vivant` - *"l'enfant lit la forme une fois et ne lit plus ensuite
> que le mot qui change"* - et je maintiens. `FER-3` ne porte pas sur la forme de la phrase, elle
> porte sur **l'operation mentale demandee**. Reconnaitre et transformer sont deux taches ; changer
> de nom dans la meme phrase n'en est qu'une.

**Ce que ces trois conditions ne sont pas** : elles ne sont pas suffisantes. Un lot qui les tient
toutes peut etre refuse pour une erreur de fait, une consigne illisible, un distracteur absurde ou
un biais. **Elles disent quand une ligne *peut* fermer, jamais qu'elle *doit*.**

**Une chose qu'elles rendent possible et que je demande** : `FER-1` et `FER-2` sont **calculables**
- si, et seulement si, chaque item declare la ligne de referentiel qu'il sert. Aujourd'hui il ne la
declare pas : `cm1-histoire-geographie.yaml` porte une seule chaine de competence qui enumere onze
lignes, et **l'attribution item par ligne que j'utilise en section 3 est la mienne, faite a la
main, verifiee seulement par le fait que sa somme retombe sur vingt**. Personne d'autre que moi ne
peut la refaire. C'est `HIS-4`, section 5.1.

---

## 3. Les quatorze lignes ouvertes, une par une - ce que le second paquet doit apporter

**Reponse directe a la question qui decide du sprint : non, les quatorze ne peuvent pas fermer.
Six le peuvent, et je dis lesquelles.**

### 3.1 Questionner le monde CP - les trois lignes peuvent fermer

Les trois paquets portent **huit items chacun**. Chacun sert **une seule ligne** : le decoupage de
`lea-dev` est le bon, et il rend `FER-1` et `FER-2` lisibles sans outil.

| Ligne | Etat | Ce que le second paquet doit apporter pour que je ferme |
|---|---|---|
| **1** - *Distinguer les êtres vivants des objets et de la matière inerte* | 8 items : 4 vivants (chat, arbre, poisson, oiseau), 4 objets fabriques (chaise, ballon, voiture, stylo) | **`FER-1` : la matiere inerte n'est servie par aucun item.** Un caillou, du sable, l'eau, la neige ne sont ni des etres vivants ni des objets fabriques - c'est le tiers du libelle et il manque entierement. **Lecture de ma propre ligne, que je donne parce que j'en suis l'auteur : l'opposition reste binaire** - on distingue le vivant *du reste*, et la matiere inerte est une **famille d'exemples** du non-vivant, pas une troisieme etiquette. Aucun changement de mecanique n'est requis, et mon argument de s27 sur les deux propositions tient toujours. **`FER-2` : +6 items minimum**, dont au moins trois de matiere inerte. **`FER-3` : deux taches** - la categorisation existe, ajouter la selection (*"Lequel des trois est vivant ?"*) |
| **3** - *Nommer les principales parties du corps humain et les cinq sens* | 8 items : 5 sens (un organe par sens), 3 parties (jambes, main, tete) | **`FER-1` : la moitie "cinq sens" est servie et je la valide ; la moitie "principales parties du corps" tient sur trois items.** Il manque les parties les plus courantes, qui n'existent aujourd'hui **qu'en distracteur** : bras, pieds, dos, ventre, genou. **+5 items sur cette moitie**, pour equilibrer les deux moities du libelle. **Observation nouvelle et datee, non bloquante mais a traiter dans ce lot** : le toucher est servi par *"les mains"*. C'est l'entree usuelle au CP et je ne la retire pas, mais un enfant ne doit pas ressortir en croyant qu'on ne touche qu'avec les mains - **un item ou la reponse est *la peau*** repare cela sans rien casser |
| **5** - *Reconnaître les états de l'eau (solide, liquide) et les changements d'état observables* | 8 items : 6 de reconnaissance, 2 de changement d'etat (fondre, geler) | **`FER-1` : tenue** - les deux etats et les deux changements sont la. **`FER-3` est le point qui bloque** : la moitie "changements d'etat" tient sur deux items, et les deux posent la meme phrase (*"Quand X, l'eau est-elle solide ou liquide ?"*). **Porter cette moitie a quatre items au moins, et sous une seconde forme** - nommer le changement (*"Comment appelle-t-on le passage de la glace à l'eau liquide ?"*) plutot que classer son resultat. **`FER-2` : +5 items minimum.** **Refuse d'avance** : tout item qui introduirait l'etat gazeux, la vapeur ou l'evaporation. Ma ligne nomme deux etats ; le paquet de s27 a tenu ce perimetre au mot pres et le second doit le tenir aussi |

**Cout en items : +16 minimum, +18 confortable.** Un lot de vingt items y suffit **et n'ouvre alors
aucune ligne neuve de questionner le monde**. C'est le premier arbitrage a poser sur la table :
**fermer trois lignes ou en ouvrir de nouvelles, pas les deux dans le meme lot.** Ce n'est pas ma
decision.

**Deux points de produit que je route pour qu'ils ne reviennent pas en reserve** : `DEC-0073`
ratifie `nombreDeManches: 7` **sur le motif de huit items** ; un paquet porte a quatorze ou quinze
items revient au defaut de `10` (`DEC-0064`) et n'a plus besoin de derogation. Et aucune condition
de biais ne s'applique aux trois lignes ci-dessus - `BIA-1` a `BIA-4` portent sur les lignes 4, 15
et 16, qu'un lot de fermeture ne touche pas.

### 3.2 Histoire-geographie CM1 - trois lignes au mieux sur onze, et il faut choisir lesquelles

**Distribution reelle des vingt items sur les onze lignes** (attribution faite par moi, item par
item, sur les marqueurs et les enonces ; sa somme retombe exactement sur vingt) :

| Ligne | Items | Ce qui manque a `FER-1` |
|---|---|---|
| **6** - roi / realisation | **4** | rien : les quatre rois de la parenthese sont servis |
| **7** - Revolution | **3** | rien : les quatre evenements de la parenthese sont touches |
| **14** - comprendre un document | **3** | **la nature et l'auteur du document** : les trois items demandent la date et extraient une information, jamais *"quel est ce document ?"* ni *"qui l'a ecrit ?"* |
| **1** - grandes periodes | **2** | les cinq periodes ne sont pas toutes en position de bonne reponse |
| **11** - types d'espaces | **2** | **l'espace periurbain**, qui n'existe qu'en distracteur |
| **2** - siecle, millenaire | 1 | le millenaire n'est jamais demande |
| **3** - Gaulois, Grecs, Romains | 1 | **les Gaulois et les Grecs** : seuls les Romains sont servis |
| **4** - mouvements de populations | 1 | - |
| **8** - Revolution vers Empire | 1 | - |
| **12** - se loger, travailler, se cultiver, se deplacer | 1 | **trois des quatre verbes** du libelle |
| **13** - eau, energie, aliments | 1 | **l'eau et les aliments** : seule l'electricite est servie |

**L'arithmetique, et elle est sans appel.** Pour amener une ligne a dix items il faut ajouter entre
six et neuf items. Un lot de vingt items en ferme **trois, au plus**. Fermer les onze demanderait
**quatre-vingt-dix items neufs**, soit quatre lots et demi d'histoire-geographie - **6,75 contre ma
borne de 5, pour cette seule matiere**. Ce n'est pas un sprint, c'est trois.

**Deux compositions tiennent en vingt items, et elles s'excluent :**

- **Composition A - trois lignes d'histoire ferment.** Ligne 6 (+6), ligne 7 (+7), ligne 14 (+7) =
  20 items. `FER-1` sur la ligne 14 impose des items de **nature** et d'**auteur** de document, et
  **la mention que j'ai exigee d'avance a s27 doit etre ecrite quand la ligne bouge** : *"document
  textuel seul ; carte et image restent non couvertes"*.
- **Composition B - deux lignes de geographie ferment, et `HG-R2` est payee.** Ligne 11 (+8, dont
  le periurbain et la correction de l'item rural de 1.3), ligne 13 (+9, dont l'eau et les aliments)
  = 17 items. **C'est la composition qui repare le desequilibre que j'ai signale a la porte de s27**
  - seize items d'histoire pour quatre de geographie - et j'avais ecrit *"a rappeler quand la
  matiere sera etendue"*. C'est maintenant.

**Je ne choisis pas entre A et B** : c'est de la priorisation. **Je dis que le choix existe, qu'il
est exclusif, et que B est la seule qui repare une reserve deja ecrite.** Dans les deux cas,
**huit lignes au moins restent `en cours` a la fin de s28**, et personne ne doit s'en etonner en
review.

### 3.3 Le compte, sans arrondi

**Six des quatorze lignes ouvertes peuvent fermer en s28** : les trois de questionner le monde, et
trois d'histoire-geographie (composition A) ou deux (composition B). **Huit ou neuf restent
ouvertes.** Et ce compte suppose que **les deux lots soient integralement consacres a la
fermeture**, donc qu'ils **n'ouvrent aucune ligne neuve dans leur matiere**.

---

## 4. Le prix, recompte par moi - il est de 5,0 et non de 4,5

### 4.1 Ma borne ne bouge pas, son unite non plus

**Quatre a cinq lots de contenu par sprint sur une mecanique existante.** Inchangee depuis s22,
reconfirmee a s24, s25, s26, s27, **reconfirmee ici**. L'unite est celle arretee le 2026-09-03 :
**le lot de matiere et de niveau, jusqu'a vingt items, prorata au-dela ; le nombre de fichiers ne
compte pas.** Et le prix **ne descend pas** sous vingt items : jouer une partie entiere, atteindre
l'ecran de fin, se tromper trois fois, verifier les conditions de lot coutent la meme chose a huit
items qu'a vingt - les trois paquets de huit items de s27 l'ont montre.

### 4.2 La question posee - "connue" apres un seul passage ? - et sa reponse

**La question est la bonne et ma reponse n'est pas la meme pour les deux matieres.** Il faut pour
cela ouvrir ce qu'il y a dans mon supplement de 0,5, ce que je n'avais jamais fait.

**Ce supplement n'a jamais paye le referentiel.** Je l'ai ecrit noir sur blanc a s27 : *"le
referentiel, je viens de le payer [...] je ne le facturerai pas deux fois"* - il etait **deja
exclu** du 1,5. Le supplement paye **le fait que je ne suis pas la source**, que j'avais moi-meme
designe comme *"le cout principal"* : verifier chaque date, chaque nom, chaque attribution contre
autre chose que ma memoire.

> **Precision de mon tarif, nouvelle et datee du 2026-09-03.** Le critere n'est pas *neuve* contre
> *connue* : c'est **suis-je la source de cette matiere ?**. Ce cout **ne s'eteint pas** quand la
> matiere devient connue - il se paie a l'item, a chaque item neuf, pour toujours.

**Consequence, dans les deux sens :**

- **Histoire-geographie CM1 reste a 1,5 a son second lot.** Je ne serai jamais la source de cette
  matiere. Vingt faits neufs sont vingt faits neufs, que ce soit le premier lot ou le cinquieme.
  **C'est la correction qui fait passer le total annonce de 4,5 a 5,0**, et je la fais **avant le
  planning**, sur mon propre tarif, contre aucune equipe - personne ne s'est encore vu annoncer 1,0
  sur ce lot.
- **Questionner le monde CP descend a 1,0.** J'en suis la source - le vivant, le corps, les objets,
  je les enseigne - et je l'avais deja ecrit a s27 en la designant comme la moins chere des trois.
  Je l'avais facturee 1,5 quand meme, par simplicite de bareme. **Cette simplicite-la m'a fait
  surfacturer, et je corrige dans le sens qui me coute.**

**Ce que je refuse d'ajouter, et je dis pourquoi.** J'ai envisage un supplement pour la **premiere
application d'une condition que je n'ai jamais opposee a un contenu reel** - `BIA-1` a `BIA-4` sur
questionner le monde, `POS-1` a `POS-4` sur le calcul pose, `REC-5` et `REC-6` sur l'anglais.
**Je ne le prends pas** : je facturerais mes propres clauses, et chaque sprint en apporte. **Une
condition inedite ne fait pas monter mon prix : elle fait baisser mon plafond de fermeture.**
C'est en section 9 qu'elle apparait, pas ici.

### 4.3 Mon tarif complet, apres precision

| Famille de lot (jusqu'a 20 items d'une matiere et d'un niveau) | Prix |
|---|---|
| Matiere **dont je suis la source**, mecanique existante | **1,0** |
| Matiere **dont je ne suis pas la source** (neuve ou connue) | **1,5** |
| Lot **illustre** (`REC-1` : nommer chaque dessin propositions masquees) | **+0,5** |
| Rejeu borne a des proprietes nommees | **0,5** |
| **Correction reprise mot pour mot d'un texte que j'ai fourni** - *regime du mot pour mot*, ligne **nouvelle, datee du 2026-09-03* | **0,25** |

**Le regime du mot pour mot n'est pas une facilite, il est mesure sur s27.** Deux fois dans ce
sprint - l'item 5 d'histoire et `COR-R1` sur le corps humain - l'equipe a repris ma reformulation
**mot pour mot**, et ma reverification a coute **la lecture d'une phrase**, parce que je verifiais
une identite et non une pedagogie. Une correction que l'equipe reformule elle-meme coute 0,5 :
c'est un texte neuf, et un texte neuf se juge.

### 4.4 Le prix des quatre lots de s28

| Lot | Perimetre | Tarif applique | Prix |
|---|---|---|---|
| **Questionner le monde CP**, second lot | jusqu'a 20 items, fermeture des lignes 1, 3, 5 | je suis la source, matiere connue | **1,0** |
| **Histoire-geographie CM1**, second lot | jusqu'a 20 items | **je ne suis pas la source, et ca ne s'eteint pas** | **1,5** |
| **Anglais CM1**, premier lot | jusqu'a 20 items, **textuel** (mot francais -> anglais) | je ne suis pas la source | **1,5** |
| **Mathematiques CM1**, calcul pose | jusqu'a 20 items sur les deux lignes | je suis la source | **1,0** |
| **Total** | **80 items au plus** | | **5,0** |

> **5,0 sur 5. Le perimetre annonce tient exactement, au plafond, avec zero marge** - et il ne
> tient qu'a trois conditions, que je nomme parce qu'aucune n'est acquise :

| Condition | Si elle tombe | Total |
|---|---|---|
| L'anglais est **textuel** et non illustre | `REC-1` s'applique, +0,5 | **5,5 - je decline par ecrit** |
| Le lot de questionner le monde **n'ouvre pas** les lignes 15 ou 16 | premiere application de `BIA-1`, `BIA-2`, `BIA-3` sur du contenu reel, sur la ligne que j'ai designee comme la plus dangereuse du referentiel | je ne facture pas, mais **la fermeture des trois lignes tombe** : le lot ne peut pas a la fois fermer et ouvrir |
| La dette des paquets a voix **reste dehors** | +0,25 au regime du mot pour mot | **5,25 - ne tient pas** |

**Les compositions que je decline par ecrit**, sur le modele de s27 :

| Composition | Prix | Tient dans 5 ? |
|---|---|---|
| QLM 1,0 + HG 1,5 + anglais textuel 1,5 + maths 1,0 | **5,0** | **oui**, au plafond, zero marge |
| idem + dette des paquets a voix, mot pour mot (0,25) | **5,25** | **non. Je decline par ecrit** |
| QLM 1,0 + HG 1,5 + anglais **illustre** 2,0 + maths 1,0 | **5,5** | **non. Je decline par ecrit** |
| QLM 1,0 + HG 1,5 + anglais textuel 1,5 + dette 0,25 (**le lot de maths sort**) | **4,25** | **oui**, marge de 0,75 |
| QLM 1,0 + HG 1,5 + maths 1,0 + dette 0,25 (**l'anglais sort une seconde fois**) | **3,75** | **oui**, large marge |

**Ce que je ne fais pas** : je ne choisis pas ce qui sort. Ce que je dois dire, et que je dis :
**a 5,0 le sprint est plein, et la plus petite chose qui peut en sortir pour loger la dette est le
lot de mathematiques (1,0)** - c'est le seul lot dont le retrait ne referme aucune matiere entiere.
L'arbitrage appartient a l'utilisatrice, via `camille-po`.

---

## 5. Les conditions opposables aux quatre lots

### 5.1 Histoire-geographie CM1 - reconduites, deux precisees, deux nouvelles

- **`HIS-1` - reconduite, et precisee sur son perimetre de calcul.** Au plus un tiers d'items de
  restitution. Elle se calcule **sur le lot** (arbitrage du 2026-09-03) **et, en plus, sur tout
  fichier de dix items ou davantage pris seul** - parce que le fichier est ce que l'enfant joue, et
  qu'un ratio calcule sous dix items est du bruit. Sous dix items, seul le lot compte. *Precision
  d'extension, pas un durcissement : le test de `marc-dev` reste juste tel qu'il est ecrit.*
- **`HIS-2` - reconduite sans une virgule.** Aucun item n'a deux reponses defendables.
- **`HIS-3` - reconduite avec sa precision du 2026-09-03** : fait de manuel, famille (a), je signe ;
  fait date, dispute, ou dont la formulation engage une couverture, famille (b), une source ou une
  reformulation qui retire le besoin de source. **Avertissement d'avance sur la ligne 6, ou le lot
  de fermeture va chercher des realisations neuves** : *le chene de Saint Louis* et *la poule au
  pot d'Henri IV* sont des **traditions**, pas des faits. Famille (b), et je ne signerai pas.
- **`BIA-5` - reconduite** sur les lignes 5 a 8 : aucun jugement de valeur sur un personnage.
- **`BIA-2` - etendue a cette matiere, et c'est nouveau.** Je l'avais ecrite pour les pays ; elle
  vaut identiquement pour les **types d'espace**. Un espace rural, urbain ou touristique n'est
  jamais represente par un seul trait. **Motif : l'item de 1.3 la viole deja.**
- **`HIS-4` - nouvelle, datee du 2026-09-03. Chaque item declare la ligne de referentiel qu'il
  sert.** Motif, et il est operationnel : sans cette declaration, **`FER-1` et `FER-2` ne sont
  verifiables par personne d'autre que moi**, et mon attribution des vingt items de s27 aux onze
  lignes est faite a la main, sans controle. Avec elle, les deux se comptent mecaniquement.
  **Meme forme que le marqueur `HIS-1`, que `theo-archi` a deja rendu mordable.** Je dis la
  propriete ; la forme est a `theo-archi`.
- **`HG-R3` - nouvelle, datee du 2026-09-03, non bloquante pour s27, a corriger dans le lot de
  s28.** L'item de l'espace rural : deux distracteurs absurdes, et une reponse qui reduit l'espace
  rural a un seul trait. A reecrire avec des distracteurs plausibles - un espace rural ou l'activite
  dominante n'est pas agricole existe, et c'est precisement ce que la geographie de CM1 enseigne.
- **`HG-R2` - rappelee, comme je l'avais annonce** : seize items d'histoire pour quatre de
  geographie. La composition B de la section 3.2 la paie ; la composition A l'aggrave. **A dire
  avant de composer le lot, pas apres.**

### 5.2 Questionner le monde CP

- **`BIA-1`, `BIA-2`, `BIA-3`, `BIA-4` - reconduites sans changement**, sur les lignes qu'elles
  visent (4, 15, 16). **Aucune ne s'applique a un lot consacre a fermer les lignes 1, 3 et 5** : je
  le dis pour que personne ne les cherche la ou elles ne sont pas.
- **`BIA-2` s'evalue sur le lot entier, pas fichier par fichier** (arbitrage du 2026-09-03). Si le
  lot ouvre la ligne 16, un garde-fou qui l'evaluerait fichier par fichier serait **vert a tort**.
- **`FER-1`, `FER-2`, `FER-3`** telles qu'appliquees ligne par ligne en 3.1.
- **Perimetre au mot pres du referentiel** : la ligne 5 nomme deux etats, pas trois. Le premier
  paquet l'a tenu ; le second doit le tenir.

### 5.3 Anglais CM1

- **`ANG-S1` et `ANG-S2` - reconduites sans changement**, et `ANG-S1` a une consequence de
  calendrier que je tire en section 6.
- **`REC-1`, `REC-2`, `REC-3`, `REC-4` - ne s'appliquent qu'a un lot illustre.** Si le lot est
  textuel, elles ne le concernent pas, et je le dis pour qu'on ne facture pas au developpement des
  conditions qui ne s'appliquent pas.
- **`REC-5` et `REC-6` s'appliquent a tout lot d'anglais, illustre ou non - precision, pas
  durcissement.** Elles ne parlent pas de dessin, elles parlent du triplet : aucun mot transparent
  en bonne reponse (`REC-5`), trois propositions du meme champ lexical et de longueur voisine
  (`REC-6`). Un paquet mot a mot les subit exactement comme un paquet illustre.
- **Les trois sens de traduction, reconduits** : image -> anglais en priorite, mot francais ->
  anglais en second, **anglais -> francais jamais seul** et au plus un item sur quatre.
- **Aucun dispositif ou l'adulte prononcerait de l'anglais** - reserve du 2026-09-02, reconduite
  telle quelle.
- **`ANG-4` - nouvelle, datee du 2026-09-03, et elle rend `FER-1` applicable a la ligne 4.** Le
  libelle de la ligne dit *"dans les champs lexicaux du cycle 3"* sans les enumerer : sans
  declaration, `FER-1` y est infalsifiable. **Le lot declare ses champs lexicaux et en couvre au
  moins trois**, cinq mots chacun au minimum. J'en suis l'auteur, je peux donc le dire : les champs
  du cycle 3 sont la personne et la vie quotidienne, l'environnement geographique et urbain,
  l'imaginaire.
- **`ANG-5` - nouvelle, datee du 2026-09-03.** La reponse attendue est **un mot seul ou une
  expression figee**. Aucun item du premier lot ne demande une forme grammaticale que le lot
  n'enseigne pas - pluriel, conjugaison, determinant variable. Motif : la mecanique de choix
  rendrait la forme fausse indistinguable du mot faux, et l'enfant ne saurait pas ce qu'il a rate.

### 5.4 Mathematiques CM1 - le calcul pose, et la question qu'il pose vraiment

**La question de fond avant les conditions.** Les deux lignes disent *"Effectuer une multiplication
**posee**"* et *"Effectuer une division euclidienne simple (quotient et reste)"*. **Effectuer une
operation posee, c'est derouler une technique ecrite - disposition, retenues, produits partiels -
et la mecanique de choix ne voit rien de tout cela.** Elle voit un resultat.

**Je ne classe pas ces lignes hors de portee, et voici pourquoi** - c'est un raisonnement, pas une
commodite. Quatre paquets de calcul sont deja `couvert-valide` sur ce produit
(`cp-additions`, `cp-soustractions`, `cm1-tables-multiplication`,
`cm1-multiplications-divisions-10-100`) : **le resultat atteste le calcul**, c'est un precedent que
j'ai pose quatre fois et je ne le deplace pas. Et il y a mieux ici qu'ailleurs : **les erreurs
classiques de la technique posee font des distracteurs naturellement plausibles** - la retenue
oubliee, le produit partiel mal decale, le reste superieur au diviseur. C'est exactement ce qui
manque a l'histoire et ce que je reclamais. **Le calcul pose est la matiere ou le distracteur
enseigne le plus.**

**Ce que la mecanique ne fera jamais**, et qui doit etre ecrit **avant** que la ligne ne bouge :

> **`POS-1` - la couverture est partielle et sa mention est exigee d'avance**, comme sur les
> fractions et sur la frise : *"le resultat atteste le calcul ; la technique posee elle-meme -
> disposition, retenues, produits partiels - n'est ni montree ni verifiee par le produit."*
> **Si cette mention n'est pas dans la matrice, je ne ferme pas.**
>
> **`POS-2` - l'item ne doit pas se repondre sans poser l'operation.** Les trois propositions ont
> **le meme nombre de chiffres et le meme ordre de grandeur**, et **au moins un distracteur partage
> le chiffre des unites avec la bonne reponse**. Sinon l'enfant elimine par la longueur ou par le
> dernier chiffre, et l'item ne teste rien. C'est `CON-1` transposee au calcul : **en effacant la
> technique, la reponse reste-t-elle trouvable ?**
>
> **`POS-3` - chaque distracteur est une erreur classique nommee**, declaree dans le paquet :
> retenue oubliee, produit partiel non decale, reste superieur au diviseur, quotient donne sans
> reste. **Aucun distracteur tire au hasard.** Un distracteur absurde transforme l'exercice en
> devinette ; ici, l'erreur classique est le contenu.
>
> **`POS-4` - `FER-1` sur les deux parentheses, et elles sont precises.** Multiplication : les
> quatre combinaisons du libelle sont servies - 2 chiffres par 1, 2 par 2, 3 par 1, 3 par 2.
> Division : le diviseur a **un seul chiffre**, sans exception, et **le reste est demande** - un
> paquet qui ne demanderait que le quotient ne sert pas *"quotient et reste"*, il sert la moitie de
> la ligne. Au moins un item a reste nul et plusieurs a reste non nul.

**Un point de forme que je signale pour eviter une reserve** : deux fichiers de dix items - un par
ligne, selon le decoupage de `lea-dev` - portent `nombreDeManches: 10` sans derogation
(`DEC-0064`), la partie epuisant exactement le vivier sans repetition.

### 5.5 Une contrainte commune aux quatre lots, verifiee dans le code

**Les manches sont tirees uniformement sans remise parmi les items non encore vus**
(`ListContentSource.next`, lu et non suppose). **L'ordre des items dans le fichier n'a aucun effet
sur l'ordre joue.**

Deux consequences, et elles valent pour tout le monde :

1. **La progression de difficulte ne peut pas etre portee par l'ordre du fichier.** Chaque item
   doit etre a la portee de l'enfant seul. Un lot qui reserverait ses items durs "pour la fin" se
   trompe : la fin n'existe pas.
2. **Sur un paquet de vingt items joue en dix manches, la moitie n'est jamais vue par une partie
   donnee.** Ni par l'enfant, ni par moi si je me contente de jouer. C'est ce qui m'a fait manquer
   l'item de 1.3, et c'est pourquoi mon prix se compte a l'item.

---

## 6. L'anglais CM1 et le son - confirme, et le lot de s28 ne peut servir que deux lignes sur quatre

### 6.1 Les trois lignes `non couvrable en l'etat` : confirmees, sans changement

| Ligne | Statut | Motif reconduit |
|---|---|---|
| 1 - ecouter et comprendre | **`non couvrable en l'etat`** | aucun son. **Nature reversible** : une decision de l'utilisatrice sur le son la rouvre, et elle vient de la refermer |
| 2 - prendre part a une conversation | **`non couvrable en l'etat`** | aucun son **et** production libre jugee par un humain (`ADR-0008`). Deux motifs, chacun suffisant |
| 3 - parler en continu | **`non couvrable en l'etat`** | idem |

**Rien a reviser.** L'arbitrage de l'utilisatrice du 2026-09-02 - pas de son - n'a pas bouge, et
mon refus du 2026-09-02 de tout dispositif ou l'adulte prononcerait de l'anglais tient : un adulte
qui lit *th* comme *z* enseigne le mauvais phoneme, et rien dans le produit ne le sait.

### 6.2 Les quatre lignes `non couvert` : deux sont servables en s28, deux ne le sont pas

| Ligne | s28 ? | Motif |
|---|---|---|
| **4** - lire et comprendre des mots et expressions familiers | **oui** - c'est le coeur du lot | Choix, mot francais -> anglais, ou image -> anglais si le lot est illustre |
| **7** - decouverte culturelle | **oui**, en francais | Sous `BIA-1` et `BIA-2`, et **avec la mention que j'exige d'avance** : *"decouverte culturelle seule, en francais ; aucune competence linguistique n'est servie"*. Une ligne verte sur cette seule base ne doit pas laisser croire que le produit enseigne l'anglais |
| **5** - lire et comprendre un texte court | **non. Je refuse d'avance, motif ecrit** | 6.3 |
| **6** - ecrire des mots memorises | **non, impossible en s28 par `ANG-S1`** | 6.4 |

### 6.3 Ligne 5 - refusee d'avance pour s28

**Deux motifs, le premier suffit.**

**Motif 1 - un texte fait de vingt noms n'est pas un texte, c'est une liste.** La ligne demande
*"un texte court et simple (consigne, message, courte description)"*. Un texte suppose des verbes,
des determinants, des prepositions - une grammaire que le premier lot n'enseigne pas et que
`ANG-5` lui interdit meme d'evaluer. Construit sur le seul lexique du lot, l'objet obtenu porte le
nom de texte sans en etre un, et **la ligne serait declaree servie par une contrefacon**.

**Motif 2 - ma propre clause de s27 le disait deja** : *"il faut que le lexique de la ligne 4
existe d'abord"*. Il n'existera qu'a la porte de s28.

**Nature : reversible, et je dis par quoi.** Elle s'ouvre quand un lexique valide existe **sur plus
d'un paquet**, ce qui donne assez de matiere pour ecrire des phrases plutot que des enumerations.

**Une chose que je dois dire, parce qu'elle serait decouverte plus tard comme une contradiction** :
le produit ne conserve rien (`DEC-0028`). *"Deja servi en choix"* ne veut donc **jamais** dire
*"deja rencontre par cet enfant"* - le produit ne sait pas ce que l'enfant a joue. Ce que mes
clauses protegent, c'est que le vocabulaire evalue appartienne a un lexique **ferme, declare et
reellement enseigne quelque part dans le produit**, et non a de l'anglais arbitraire. C'est une
propriete du contenu, verifiable a ma porte. Elle ne devient jamais une propriete de l'enfant.

### 6.4 Ligne 6 - impossible en s28, et ce n'est pas un jugement sur le lot

**`ANG-S1`, mot pour mot** : *"Un paquet de saisie en anglais ne porte que des mots **deja servis
en choix dans un paquet valide**."* Le paquet de choix du meme sprint n'est valide qu'a ma porte,
c'est-a-dire **apres** que le paquet de saisie aurait du etre ecrit. **La ligne 6 s'ouvre a s29 au
plus tot**, et pas parce que je m'y oppose : parce que ma condition, ecrite le 2026-09-02, a un
ordre.

**Je ne l'assouplis pas**, et je dis pourquoi plutot que de le poser : la mecanique de saisie ne
tolere **aucune erreur de lettre**. Demander a un enfant d'ecrire un mot anglais dont le lexique
n'a pas encore passe une porte pedagogique, c'est fabriquer de l'echec sur une orthographe qu'on
n'a pas verifiee.

### 6.5 Ce que le lot d'anglais peut fermer

**Une ligne, la 4, et seulement si dix de ses items au moins la servent** (`FER-2`) **et si trois
champs lexicaux au moins sont couverts** (`ANG-4`). Un lot de vingt items partage en quatorze pour
la ligne 4 et six pour la ligne 7 ferme la ligne 4 et ouvre la ligne 7.

**Et je ne reintroduis pas "un premier paquet ne ferme jamais".** Ce serait le poteau que je viens
de retirer en 1.1 remis en place sous un autre nom. **Un premier paquet peut fermer une ligne** s'il
tient `FER-1`, `FER-2`, `FER-3` et l'audit. Il est simplement plus fragile, et cela se voit dans le
plafond de la section 9, pas dans le prix.

---

## 7. La dette des paquets a voix (E) - ce qu'elle est vraiment, ce que je fournis, ce qu'elle coute

### 7.1 Elle est plus large que ce que le backlog en dit

**Six jeux et cinq indices, pas trois paquets** : le detail mesure est en 1.2. Et **`cp-son-dans-mot`
est le cas le plus grave** : son indice demande a l'adulte de redire le mot *"lentement"*, quand
`VOI-3` exige *"a vitesse normale"* - sur un paquet ou la competence est d'entendre un son dans un
mot, c'est-a-dire la ou un mot ralenti donne la reponse.

### 7.2 Les textes, que je fournis ici pour que le regime du mot pour mot s'applique

**Le principe que j'applique** : la **consigne** (entree de catalogue) porte la tache ; l'**enonce**
(item) porte ce qui est propre a l'item. Quand tous les items d'un paquet portent le meme enonce,
l'enonce n'apporte rien et ne coute que de la lecture - a un enfant qui lit peu et lentement, deux
fois la meme phrase a l'ecran, c'est une phrase de trop.

**Enonces - a substituer, sans toucher aux consignes de catalogue :**

| Paquet | Enonce servi aujourd'hui | Enonce que je fournis |
|---|---|---|
| `cp-lettre-du-son`, `cp-voyelle-du-son` | *"Écoute l'adulte, puis choisis la lettre qui fait le tout premier son."* | *"Quelle lettre fait le premier son ?"* |
| `cp-digraphe-du-son` | *"Écoute l'adulte, puis choisis le groupe de lettres qui fait ce son."* | *"Quel groupe de lettres fait ce son ?"* |
| `cp-position-syllabe` | *"Écoute l'adulte, puis choisis la bonne réponse."* | *"Où entends-tu ce son ?"* |
| `cp-son-dans-mot` | *"Écoute l'adulte, puis réponds oui ou non."* | *"Entends-tu ce son ?"* |
| `cp-mot-dicte` | *"Écris le mot que tu entends."* | inchange - il ne repete pas la consigne, il la complete |

**Indices - a substituer :**

| Paquet | Indice servi aujourd'hui | Indice que je fournis |
|---|---|---|
| `cp-lettre-du-son`, `cp-voyelle-du-son` | *"...redire le mot **doucement**, puis écoute le tout premier son."* | *"Demande à l'adulte de redire le mot en entier, puis écoute le tout premier son."* |
| `cp-digraphe-du-son` | *"...redire le mot **doucement**, puis pense au son que tu entends."* | *"Demande à l'adulte de redire le mot en entier, puis pense au son que tu entends."* |
| `cp-son-dans-mot` | *"...redire le mot **lentement**, puis écoute bien le son qu'il te demande de chercher."* | *"Demande à l'adulte de redire le mot en entier, puis écoute bien le son qu'il te demande de chercher."* |
| `cp-position-syllabe` | *"...redire le mot **doucement**, puis compte les syllabes dans ta tête."* | *"Demande à l'adulte de redire le mot en entier, puis écoute si le son est au début, au milieu ou à la fin."* |
| `cp-mot-dicte` | *"...autant de fois que tu en as besoin."* | inchange - conforme a `VOI-8` |

**Deux precautions sur ces textes.** Ils ne nomment jamais le mot, donc `VOI-13` reste tenue. Et
**l'orthographe, l'accentuation et la ponctuation de ces textes appartiennent au
`relecteur-pedagogique`** (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`), y compris parce
qu'ils sont destines a l'enfant. Je fournis le fond, le niveau et le ton.

### 7.3 Ce qu'elle coute, et ce qui se passe si elle attend

**Prix : 0,25**, au regime du mot pour mot, **puisque je viens de fournir les textes**. Si l'equipe
les reformule, c'est 0,5.

**Elle ne tient pas dans 5,0.** Le perimetre annonce est deja au plafond. **Soit un lot sort, soit
la dette attend.**

**Ce qui se passe si elle attend, dit sans dramatiser et sans minimiser** : les six jeux concernes
servent des lignes qui sont **toutes `couvert-valide`**. Aucune ligne de matrice n'est en jeu, aucun
taux ne bouge, et **c'est exactement ce qui rend l'attente risquee** : une ligne fermee n'est plus
regardee. Un defaut sur une ligne fermee est de la famille de ceux qui restent. Pendant ce temps,
le produit **demande a l'adulte, par ecrit, un geste que j'interdis par ailleurs**, sur la
competence la plus fondatrice du CP, et **`VOI-3` n'a aucun garde-fou** : je suis la seule garde, et
je ne suis pas dans la piece quand l'enfant joue.

**Je ne priorise pas.** Je dis le prix, je dis que ca ne rentre pas, et je dis ce que coute
l'attente. `camille-po` en fait des stories, l'utilisatrice tranche.

---

## 8. Les sept conditions sans garde executable (F) - elles ne sont pas de la meme nature, et
## c'est ce qui repond a la question

**La question routee etait *"chantier propre en s28 ou reserve permanente ?"*. Elle n'a pas de
reponse unique parce que les sept conditions ne sont pas une famille.** Les traiter d'un bloc a
produit le blocage. Elles sont trois familles.

| Condition | Nature | Verdict |
|---|---|---|
| `VOI-3` - l'adulte dit le mot d'un seul tenant, a vitesse normale | **geste humain hors du produit** | **reserve permanente. Aucun chantier.** Aucun logiciel n'observera jamais ce qu'un adulte prononce dans une piece |
| `VOI-5` - l'adulte n'appuie pas le son cherche | idem | **reserve permanente** |
| `VOI-8` - l'adulte ne l'epelle pas, ne le decoupe pas | idem | **reserve permanente** |
| `HIS-2` - aucun item a deux reponses defendables | **jugement de sens** | **reserve permanente.** C'est la definition meme de ce qu'un automate ne fait pas |
| **L'exactitude factuelle** | idem, et c'est la plus lourde | **reserve permanente** |
| `COU-2` - meme reponse en niveaux de gris | **propriete d'un rendu** | **moitie calculable.** Candidat a un chantier, que je ne commande pas |
| `COU-3` - le chiffre grave reste lisible sur la surface remplie | idem | **moitie calculable** (contraste entre le trait et le remplissage) ; l'autre moitie est un oeil a taille reelle |

**Sur les cinq permanentes, il n'y a pas de chantier a ouvrir, et l'ouvrir serait pire que rien** :
un garde-fou qui pretendrait mordre `VOI-3` ne mordrait qu'un texte, et **l'equipe croirait couvert
ce qui ne l'est pas**. Ce que le produit peut faire, il le fait deja : `VOI-12` - dire a l'adulte ce
qu'il ne doit pas faire, la ou il lit son texte - **est cablee et je l'ai lue a l'ecran a s27**. La
part gardable est gardee ; la part restante ne l'est par nature pas.

**Sur `COU-2` et `COU-3`, je signale la propriete et je ne prescris pas la forme** : c'est
`theo-archi`. Je note seulement que `COU-2` est de la meme famille que les regles de couleur deja
executables sur les figures, donc que le terrain existe.

**Ce qu'il y a a construire en s28, et c'est ailleurs que dans les sept** : un garde-fou peut
exiger que **chaque item porte un marqueur de source non vide** (`HIS-3`). Il ne verifie aucun fait
- il rend **impossible qu'un fait sans source revendiquee entre dans un paquet**. C'est une garde
sur le procede, pas sur la verite, et je ne la fais pas passer pour autre chose. Elle est **cheap**
: `marc-dev` marque deja tous ses items, le garde ne ferait qu'empecher la regression.

**Et une chose qui ne coute rien et qui doit etre faite quel que soit l'arbitrage** : la matrice
**ecrit deja** que `HIS-2` et l'exactitude factuelle sont sans garde, sur les onze lignes
d'histoire-geographie. **La meme mention manque sur les lignes servies par les paquets a voix et sur
la ligne de la monnaie.** C'est une ecriture, pas un chantier, et c'est a `camille-po`.

---

## 9. Le plafond honnete de s28, en nombres, avec sa composition

**C'est la lecon de s26 appliquee pour la troisieme fois** : a l'ouverture de s26 j'avais laisse
annoncer 35 lignes, il en a fait 34, parce qu'une barre n'etait pas dans le compte. Je donne donc
le nombre **avec la composition qui le produit**, sans quoi il ne veut rien dire.

**Etat de depart, verifie** : 35 lignes `couvert-valide` sur 75 atteignables, **46,7 %**.

| Origine | Lignes | Condition |
|---|---|---|
| Questionner le monde, lignes 1, 3, 5 | **+3** | le lot de 20 items est **integralement** consacre a leur fermeture, et n'ouvre aucune ligne neuve |
| Histoire-geographie, composition A (lignes 6, 7, 14) | **+3** | idem. Composition B : **+2** seulement |
| Anglais CM1, ligne 4 | **+1** | 10 items au moins sur cette ligne, 3 champs lexicaux, lot textuel |
| Mathematiques CM1, les deux lignes de calcul pose | **+2** | 10 items chacune, `POS-1` a `POS-4`, **et la mention de couverture partielle ecrite** |
| **Plafond** | **+9, soit 44 / 75 = 58,7 %** | **les quatre conditions ci-dessus tenues ensemble** |

**Ce plafond ne se realisera pas tel quel, et je dis ou il cassera d'abord.** Les deux lots les plus
fragiles sont ceux qui appliquent pour la premiere fois une condition qui n'a jamais rencontre du
contenu reel : **le lot de mathematiques** (`POS-2` et `POS-3` sont exigeantes, et un jeu de
distracteurs par erreur classique se rate facilement) et **le lot d'anglais** (`REC-5` sur les mots
transparents, `REC-6` sur l'homogeneite du triplet, et une matiere dont je ne suis pas la source).
**Mon attente raisonnable est de six a sept lignes, pas neuf** - et je le dis avant, pas apres.

**Deux avertissements de denominateur, pour que personne ne decouvre un chiffre en review :**

1. **Si les dix referentiels des enseignements absents entrent dans la matrice pendant s28**, le
   denominateur quitte 75 et va vers **la centaine de lignes** que j'ai estimee le 2026-09-02.
   **44 lignes sur une centaine, c'est 44 %** - c'est-a-dire **moins qu'aujourd'hui, apres le
   meilleur sprint possible.** C'est le meme mouvement qu'a s27 : le chiffre baisse parce qu'il
   devient vrai. **A dire avant le sprint, pas apres.**
2. **Fermer une ligne n'ouvre rien.** Un sprint entierement consacre a la fermeture fait monter le
   numerateur et **n'ajoute aucune matiere neuve au produit**. C'est un arbitrage legitime, mais il
   doit etre pris en le sachant : **les quatorze lignes ouvertes et l'ouverture de lignes nouvelles
   se disputent exactement les memes vingt items.**

---

## 10. Les proprietes dont je reste la seule garde

Dit explicitement, comme a chaque fois, pour que l'equipe ne croie pas couvert ce qui ne l'est pas.

| Propriete | Qui la tient | Automatisable ? |
|---|---|---|
| `VOI-3`, `VOI-5`, `VOI-8` - ce que l'adulte fait de sa voix | **personne, pas meme moi** | **non, par nature** |
| `HIS-2` - aucun item a deux reponses defendables | **moi seule** | non |
| **L'exactitude factuelle** en histoire, geographie et anglais | **moi seule** | non - la plus lourde |
| `HIS-3` - chaque fait porte sa source | moi | **oui, sur le procede** : marqueur non vide exige. Ne verifie aucun fait |
| `BIA-1` a `BIA-5` - stereotype, jugement, presupposition de foyer | **moi seule** | non |
| `REC-5` - aucun mot transparent en bonne reponse | moi | **partiellement**, avec une liste declaree |
| `POS-2` - l'item ne se repond pas sans poser l'operation | **moi**, mais **calculable** : meme nombre de chiffres, chiffre des unites partage | **oui, si le paquet declare ses distracteurs** |
| `POS-3` - chaque distracteur est une erreur classique nommee | **moi seule** pour le jugement ; le marqueur est mordable | **partiellement** |
| `COU-2`, `COU-3` | moi | **moitie calculable** |
| `FER-1`, `FER-2`, `FER-3` | **moi**, aujourd'hui | **oui, si `HIS-4` existe** : sans declaration de ligne par item, elles ne sont verifiables que par moi, a la main |
| **Le fait qu'un item rare puisse n'etre jamais tire** (10 manches sur 20 items) | **moi**, en lisant le fichier et non en jouant | non - et c'est ce qui m'a fait manquer un item a s27 |

---

## 11. Ce que je route

| Fait | A qui | Ce qu'il en fait |
|---|---|---|
| **Le prix reel du perimetre annonce est 5,0 et non 4,5** ; l'ecart est le second lot d'histoire-geographie, qui reste a 1,5 (4.2, 4.4) | `camille-po`, `sofia-sm` | Le sprint est **au plafond, zero marge** |
| **Le critere de mon tarif n'est pas neuve/connue mais "suis-je la source ?", et ce cout ne s'eteint jamais** (4.2) | `camille-po`, `sofia-sm` | Calculable sans me le redemander. Questionner le monde descend a 1,0 |
| **Regime du mot pour mot : une correction reprise verbatim de ce que j'ecris me coute 0,25** (4.3) | `camille-po`, developpeurs | Mesure sur les deux corrections de s27 |
| **`FER-1`, `FER-2`, `FER-3` : ce qui ferme une ligne, ecrit d'avance** (2) | `camille-po`, developpeurs | Opposable a moi comme a eux, a partir de s28 |
| **Six des quatorze lignes ouvertes peuvent fermer, huit ou neuf restent ouvertes** (3) | `camille-po`, `sofia-sm`, l'utilisatrice | Fermer les onze lignes d'histoire-geographie couterait 6,75 contre ma borne de 5 |
| **Fermer et ouvrir se disputent les memes vingt items** (3.1, 9) | l'utilisatrice, via `camille-po` | Arbitrage a prendre en le sachant |
| **Histoire-geographie : composition A (3 lignes d'histoire) ou B (2 lignes de geographie, `HG-R2` payee), pas les deux** (3.2) | `camille-po` | Je ne choisis pas |
| **`HG-R3` - un item d'histoire-geographie a deux distracteurs absurdes et reduit l'espace rural a un seul trait** (1.3) | `marc-dev`, `camille-po` | Nouveau, date, **non bloquant pour s27** : le Done rendu tient |
| **Mon audit de s27 n'a verifie la plausibilite des distracteurs que sur les items tires** (1.3) | `sofia-sm`, l'utilisatrice | Ce que ma signature vaut, et ce qu'elle ne vaut pas |
| **`HIS-4` - chaque item declare la ligne de referentiel qu'il sert** (5.1) | `theo-archi`, `camille-po` | Sans elle, `FER-1` et `FER-2` ne sont verifiables que par moi. Je dis la propriete, pas la forme |
| **`HIS-1` se calcule sur le lot, et sur tout fichier de dix items ou plus** (5.1) | `theo-archi`, `marc-dev` | Precision d'extension ; le test existant reste juste |
| **`BIA-2` etendue aux types d'espace** (5.1) | `marc-dev`, `camille-po` | Nouveau, date |
| **`POS-1` a `POS-4` sur le calcul pose, dont la mention de couverture partielle exigee d'avance** (5.4) | `camille-po`, developpeurs | **Sans la mention, je ne ferme pas** |
| **`ANG-4` et `ANG-5` sur l'anglais** (5.3) ; `REC-5` et `REC-6` valent aussi sur un lot textuel | `camille-po`, developpeurs | Precision, pas durcissement |
| **Anglais CM1 : le lot de s28 sert deux lignes sur quatre.** Ligne 5 refusee d'avance, ligne 6 impossible par `ANG-S1` (6) | `camille-po`, l'utilisatrice | Les trois lignes `non couvrable` sont confirmees sans changement |
| **La dette des paquets a voix touche six jeux et cinq indices, pas trois paquets** ; textes fournis (7) | `camille-po`, `lea-dev` ou `marc-dev` | Corrige le perimetre inscrit au backlog. `cp-son-dans-mot` est le cas le plus grave |
| **Elle coute 0,25 et ne tient pas dans 5,0 : un lot doit sortir, ou elle attend** (7.3) | l'utilisatrice, via `camille-po` | Je ne priorise pas. Le seul lot dont le retrait ne referme aucune matiere est celui de mathematiques |
| **Les sept conditions sans garde sont trois familles : cinq permanentes par nature, deux a moitie calculables** (8) | `theo-archi`, `camille-po`, l'utilisatrice | **Pas de chantier sur les cinq**, et en ouvrir un serait pire que rien |
| **La mention "sans garde executable" manque sur les lignes a voix et sur la monnaie** (8) | `camille-po` | Une ecriture, pas un chantier |
| **Le tirage est uniforme sans remise ; la progression ne peut pas etre portee par l'ordre du fichier** (5.5) | developpeurs | Lu dans `ListContentSource`, pas suppose |
| **Si les dix referentiels absents entrent pendant s28, 44 lignes vaudront environ 44 %** (9) | `sofia-sm`, `camille-po`, l'utilisatrice | A dire avant le sprint |

---

## 12. Ce que ce document ne fait pas

- Il **ne prononce aucun Done pedagogique** : rien de s28 n'est construit.
- Il **ne revoque aucun Done de s27**. Les six verdicts du 2026-09-03 tiennent, y compris sur
  l'item que je critique en 1.3 et sur les six jeux a voix de 1.2. Ce que j'ajoute est **ajoute,
  date, et lisible comme nouveau**.
- Il **n'ecrit ni la matrice ni le backlog** : je donne des motifs, `camille-po` en fait des
  stories et des lignes.
- Il **ne priorise pas** : je dis que le perimetre annonce vaut exactement 5,0, que la dette n'y
  tient pas, et que six lignes peuvent fermer. Je ne dis pas ce qui doit sortir.
- Il **ne tranche aucun choix technique** : ni la forme du marqueur de ligne (`HIS-4`), ni celle du
  garde-fou de source, ni la maniere de rendre `COU-2` calculable. `theo-archi`.
- Il **ne rouvre pas la premisse de l'adulte accompagnant** et ne demande pas son extension.
- Il **ne revise aucune barre anterieure**. `V1`, `V2`, `V3`, `CON-1` a `CON-4`, `REC-1` a `REC-6`,
  `BIA-1` a `BIA-5`, `HIS-1` a `HIS-3`, `ANG-S1`, `ANG-S2`, `VOI-1` a `VOI-13`, la barre des
  graphemes du 2026-08-10 et le refus de la reconnaissance vocale sont reconduits sans une virgule
  de changement. **Les seules baisses de barre de ce document sont sur moi** : mon motif de s27 pour
  ne pas fermer, que je declare epuise (1.1), et le prix de questionner le monde, que je baisse
  (4.2).

---

## 13. Amendement du 2026-09-04 - deux ecarts remontes par les developpeurs, tranches a la porte

**Ajoute apres la livraison des quatre lots, a la porte pedagogique de s28. Rien au-dessus n'est
efface** : les sections 1 a 12 restent telles qu'elles ont ete ecrites le 2026-09-03, y compris la
phrase que je corrige ci-dessous. **Une prescription fausse qu'on efface ne s'est jamais vue ; une
prescription fausse qu'on date se lit.**

### 13.1 Ma prescription du calcul pose etait impossible telle qu'ecrite - **je l'accepte et je la
### corrige**

**Ce que j'avais ecrit** (section 5.4, dernier paragraphe) : *"deux fichiers de dix items - un par
ligne, selon le decoupage de `lea-dev` - portent `nombreDeManches: 10` sans derogation (`DEC-0064`),
la partie epuisant exactement le vivier sans repetition."*

**Ce que `marc-dev` a trouve, et il a raison** :
`ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` (`DEC-0064`) exige **strictement plus** d'items
que de manches - `assertThat(items).isGreaterThan(jeu.nombreDeManches())`. **L'egalite est refusee
par construction.** Ma phrase demandait donc, dans la meme parenthese, *"sans derogation"* et une
egalite que le garde-fou interdit : **les deux moities de ma prescription ne pouvaient pas etre
vraies ensemble.**

**Mon arbitrage** :

1. **J'accepte les onze items.** C'etait la seule issue compatible avec un garde-fou existant, et
   `marc-dev` a eu raison de ne pas inventer une derogation hors de son mandat pour honorer une
   phrase de moi.
2. **Je ne demande aucun changement au test.** Il a raison et ma phrase avait tort.
3. **Je retire l'exigence d'exhaustion.** *"La partie epuisant exactement le vivier sans
   repetition"* n'est **atteignable par aucun paquet de ce produit**, et je n'aurais pas du l'ecrire
   comme une condition de forme. La formule qui la remplace, a partir d'aujourd'hui : **un paquet qui
   sert N manches sans derogation porte au moins N+1 items.**
4. **Ce que cela coute, dit franchement, parce que c'est une propriete dont je reste la seule
   garde** : sur onze items joues en dix manches, **un item n'est jamais vu dans une partie donnee**,
   et lequel change a chaque partie. C'est exactement la propriete que j'ai enregistree a s27 apres
   avoir manque un item en jouant. **Je l'ai payee ici en lisant les vingt-deux items et en
   recalculant les vingt-deux resultats** - pas en jouant deux fois.

**Ce que ce point n'est pas** : ce n'est pas un defaut du lot, ce n'est pas une reserve, et cela ne
change aucun verdict. **C'est une erreur de ma part, trouvee par un developpeur, et corrigee ici.**

### 13.2 `REC-5` gardee sur la seule identite de graphie, et trois tables figees - **les deux limites
### me conviennent, et je dis ce qu'elles me laissent sur les bras**

**Ce que `lea-dev` a declare** : le test de `REC-5` compare les quinze paires francais/anglais par
**identite stricte de graphie** (`isNotEqualToIgnoringCase`) ; **la quasi-identite reste un jugement
de sens**, donc le mien. Et trois tables de classification (les trois champs lexicaux, les paires,
les pays) sont **figees par graphie** et doivent suivre tout changement de libelle.

**Ma reponse, en trois points** :

1. **La limite de `REC-5` est la bonne, et elle est correctement declaree.** Une heuristique de
   proximite - distance d'edition, prefixe commun - **laisserait passer ce qu'elle ne mesure pas et
   arreterait ce qui va bien** ; elle donnerait surtout a l'equipe le sentiment que `REC-5` est
   gardee. **Une garde etroite et dite est meilleure qu'une garde large et fausse.** Je prefere
   qu'un test dise *"je verifie l'identite de graphie"* et rien d'autre.
2. **Et je viens d'exercer ce qui me reste** : `mountain` / *montagne* est passe sous ce test, et
   c'est ma lecture qui l'a arrete (`ANG-R1`, fiche du jeu d'anglais). **La demonstration que la
   limite est reelle est faite le jour meme ou elle est declaree.**
3. **Les tables figees par graphie me conviennent, a une condition que je verifie et qui est
   tenue** : elles doivent **echouer sur l'inconnu**, pas l'ignorer. J'ai lu le test de `BIA-2` :
   un item dont la bonne reponse n'est pas dans la table **fait echouer le test par cle inconnue**.
   **C'est la bonne forme** - la charge de maintenance devient un rouge bruyant et non un silence.
   Une table qui aurait ignore l'inconnu aurait ete un faux vert, et je l'aurais refusee.

**Ce qui manquerait pour me retirer `REC-5` des mains, et je ne le commande pas** : une **liste de
mots transparents declaree dans le paquet**, que le test opposerait aux bonnes reponses. Je l'avais
deja ecrit le 2026-09-03 (section 10 : *"partiellement, avec une liste declaree"*). **La propriete
est de moi, la forme est a `theo-archi`, la priorite est a l'utilisatrice.**
