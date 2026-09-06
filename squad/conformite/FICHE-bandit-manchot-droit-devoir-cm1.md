# Fiche de conformite - bandit-manchot-droit-devoir-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-emc-droit-devoir.yaml`.
- **Entree de catalogue** : `bandit-manchot-droit-devoir-cm1`, titre servi *"Droits et devoirs"*,
  consigne servie *"Lis bien la question, puis choisis la bonne reponse."*, `nombreDeManches: 10`.
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce le 2026-09-06** par
  `nadia-prof`, apres avoir joue la reprise `4dbf9b2`). **`EMC-CM1-07` ferme** : `non couvert` ->
  **`couvert-valide`**. Trois reserves nommees et datees, `DRO-R1` a `DRO-R3`, aucune bloquante.
- **Histoire des verdicts, aucun efface** : **2026-09-05, REFUSE** - l'item 1 avait deux reponses
  defendables sur l'axe meme de la ligne ; le bloc entier de ce refus, avec son motif et ses deux
  defauts supplementaires, reste lisible ci-dessous. **2026-09-06, VALIDE SOUS RESERVE** apres
  reprise, second bloc en fin de fiche. **Un refus efface n'a aucune valeur de preuve.**
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`,
  section 4.4, et `squad/programme/enseignement-moral-et-civique/competences-cm1.md`.
- **Commits audites** : `ee3ef70` (`[LEA] s29: S29-5`) le 2026-09-05, puis `4dbf9b2` (`[LEA] s29:
  reprise des trois paquets d'EMC refuses par l'instance B`) le 2026-09-06.
- **Derniere mise a jour** : 2026-09-06 (**second verdict de `nadia-prof` apres avoir joue**, s29,
  instance B).

## Competence visee

`Distinguer un droit d'un devoir, et connaitre quelques droits et devoirs de l'eleve et de
l'enfant.` (`EMC-CM1-07`, enseignement moral et civique, CM1, *Acquerir et partager les valeurs de
la Republique*).

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `ee3ef70`, jar reconstruit et relance, **partie complete de dix
manches par appels HTTP reels**, ecran de fin atteint, score 10. **Et j'ai lu les onze items** :
celui que le tirage a laisse de cote est l'item 7 (*"Faire ses devoirs et apprendre ses lecons"*),
et il compte dans le motif ci-dessous. **Ce n'est pas un audit degrade.**

**J'ai aussi verifie les sources**, parce que c'est ce que paie le tarif de 1,5 sur cette matiere :
je n'en suis pas la source, et la verification s'arrete a moi.

### 1. `HIS-3` - les cinq sources citees, verifiees une par une

Le paquet declare ses droits contre la Convention internationale des droits de l'enfant. **Je
confirme les cinq renvois** : article 28 (education), article 19 (protection contre toute forme de
violence), article 24 (sante), article 31 (repos, loisirs et jeu), article 12 (exprimer son opinion
et etre entendu). Adoption 1989, ratification par la France en 1990. **Exact, sans un ecart.**

**Et je valide la maniere dont le paquet traite le point ou il n'avait pas de source.** Il ecrit que
les devoirs cites ne viennent pas d'un texte unique, que ce sont ceux qu'on enseigne en symetrie des
droits, et il demande explicitement que je le verifie. **C'est la bonne facon de faire** : `HIS-3`
mord sur le procede, jamais sur la verite, et ce procede-la est irreprochable. **Je confirme le
fond** : ecouter, respecter les regles de la classe, faire son travail, prendre soin du materiel
collectif sont bien ce qu'un reglement interieur d'ecole porte et ce qu'on enseigne au cycle 3.

### 2. Ce qui est juste dans ce paquet

- **`FER-1`** : les deux notions du libelle sont chacune en bonne reponse - *un droit* (items 1, 5,
  9), *un devoir* (items 3, 7, 11). Tenue.
- **`FER-2`** : 11 items. Tenue.
- **`FER-3`** : deux taches - classer, et associer un droit a ce qu'il protege. Tenue, et la seconde
  tache est reelle : entre *"a lui laisser du temps pour grandir en dehors du travail scolaire"* et
  *"a le dispenser de toute regle"*, il faut avoir compris ce que le droit au repos protege.
- **`EMC-1`, `BIA-4`, `EMC-8`** : aucun item n'interroge l'enfant sur sa propre situation. Les onze
  portent sur *"un enfant"* ou *"un eleve"* au general. **Tenues, et sur cette ligne c'etait le
  piege evident** - un item sur le droit d'etre soigne qui aurait demande *"et toi ?"* aurait ete
  refuse d'office.
- **`EMC-6`** : la formule *"quelle que soit sa famille"* n'attribue rien a personne et n'exclut
  aucune configuration familiale. **Elle passe**, et c'est une bonne trouvaille sur une ligne ou la
  sante de l'enfant touche vite au foyer.
- **`EMC-10`** : aucune personne reelle, aucun parti, aucune organisation. Tenue.
- **`CYC-1`** : voir la section 5. **Tenue, et verifiee par moi.**

### 3. **Motif de refus - l'item 1 a deux reponses defendables, sur l'axe meme de la ligne**

> `enonce` : *"Aller a l'ecole pour apprendre a lire et a compter, qu'est-ce que c'est pour un
> enfant ?"*
> `bonneReponse` : *"un droit"* - `distracteurs` : *"un devoir"*, *"une interdiction"*

**L'education est un droit de l'enfant, et l'instruction est en France une obligation.** Les deux
sont enseignes au cycle 3, et ils sont enseignes ensemble : c'est meme l'exemple par lequel on fait
comprendre qu'un meme objet peut porter les deux. **Un eleve de CM1 correctement instruit peut
repondre *un devoir*, et le produit lui dira qu'il a tort.**

**Ce n'est pas une subtilite que j'importe de l'exterieur : le paquet la porte lui-meme.** Dans la
partie que j'ai jouee, l'enfant voit successivement :

> *"Ecouter la maitresse et respecter les regles de la classe"* -> **un devoir**
> *"Aller a l'ecole pour apprendre a lire et a compter"* -> **un droit**

**Les deux sont des obligations scolaires, et le jeu les classe dans deux categories opposees dans
la meme partie.** L'item 7, que le tirage n'avait pas sorti et que j'ai lu, aggrave encore la
confusion : *"faire ses devoirs et apprendre ses lecons"* -> **un devoir**. Aller a l'ecole serait
donc un droit, et y travailler un devoir. **Ce n'est pas une distinction qu'un enfant peut
reconstruire, et ce n'est pas celle que la ligne demande.**

**`EMC-5` est ma condition et elle est explicite** : *"une question dont la reponse se discute
n'entre pas dans un paquet a trois propositions"*. Et mon mandat l'est aussi : le produit qui
sanctionne une reponse defendable enseigne quelque chose de faux **au moment du retour**, c'est-a-
dire a l'endroit ou l'enfant apprend le plus. **Un item de ce type est un motif de refus a lui
seul.**

> **Ce qu'il faut, et c'est actionnable sans me redemander ce que je veux dire** : l'item 1 est
> **remplace**, pas supprime - `DEC-0064` exige strictement plus d'items que de manches, et dix
> items pour dix manches ne passeraient pas la porte technique. Le droit a mettre a sa place doit
> etre un droit **qui ne porte aucune obligation symetrique pour l'enfant** : etre protege, etre
> soigne, jouer et se reposer, donner son avis, tous les quatre deja servis par le paquet, ou un
> cinquieme droit de la Convention. **Je donne la propriete, pas la valeur.**

### 4. Deux defauts de plus, qui n'auraient pas suffi a refuser

Nommes ici pour que `camille-po` en fasse du backlog en une passe.

- **La troisieme proposition est un decor, sur 6 items de classement sur 6.** *"Une interdiction"*
  est offerte a chaque item de classement et n'est **jamais** la bonne reponse. Des le deuxieme
  item, l'enfant sait qu'il ne la pressera pas : les six items deviennent des choix entre deux.
  **Je le distingue expressement du motif que je refuse le meme jour sur la division posee**, ou la
  regularite donne **la** reponse : ici elle en retire une sur trois, et l'enfant doit toujours
  savoir distinguer un droit d'un devoir. **C'est un affaiblissement, pas une echappatoire.**
  Propriete a tenir : une proposition qui n'est jamais juste sur onze items n'est pas un
  distracteur.
- **Les cinq items de la seconde tache s'ouvrent par la meme formule**, *"A quoi sert le droit
  de..."*, et portent tous les cinq sur un **droit** ; aucun ne demande a quoi sert un **devoir**.
  Le libelle dit *"quelques droits **et devoirs**"*. La moitie *devoir* n'est servie que par le
  classement, jamais par la raison d'etre. **A rattraper dans la meme reprise.**

### 5. `CYC-1` - la continuite cycle 2 / cycle 3, verifiee par moi et pas cochee

**C'est ma condition, donc mon jugement, et pas celui de qui a ecrit le lot.** J'ai fait ce que
`CYC-1` demande : lire les paquets **servis** de cycle 2 portant sur le meme objet, au perimetre du
**depot** - ce qui est plus large que ce que le paquet declare avoir verifie, a savoir le seul lot
de CP du meme jour.

**L'objet commun existe** : `cp-emc-regles-de-vie` (`EMC-CP-06`, ferme ce jour) enseigne les regles
de la classe et ce qu'elles protegent ; l'item 3 d'ici les classe comme **un devoir de l'eleve**.

**Verdict** : **aucune contradiction, et une vraie progression.** Le CP dit *ce que la regle
protege* - le tour de parole, le materiel, les corps. Le CM1 ajoute *la categorie* : respecter ces
regles est un devoir. **`CYC-1` autorise et attend exactement cet ajout** ; elle n'interdit que le
dementi. Aucun mot du CM1 ne dit autre chose que le CP sur ce que sont les regles ni sur ce a quoi
elles servent - j'ai compare les onze items de CP aux onze d'ici.

**Recherche etendue** : sur les trente paquets de CP servis, aucun autre ne porte sur les regles, le
droit ou le devoir. *"Une regle"* dans `cp-comparer` et `cp-texte-narratif` designe l'objet a
mesurer. **La lecture est close.**

**Et je note que la conclusion de `lea-dev` etait la bonne** - elle l'avait tiree d'un perimetre
plus etroit que celui que `CYC-1` prescrit ; le resultat ne change pas, la methode si. **La
prochaine fois, le perimetre est le depot.**

### 6. Un point de biais, ouvert et non impute a ce paquet

Sur les quatre paquets d'EMC de s29, l'enseignant est nomme trois fois et il est **trois fois une
femme** (*"ecouter la maitresse"* ici). Aucune occurrence masculine. **Constat de serie, date du
2026-09-05, pose pour que le prochain lot ne l'aggrave pas.** Il n'entre dans aucun de mes motifs
aujourd'hui.

### 7. Ce dont je reste la seule garde sur ce paquet

| Propriete | Qui la tient | Automatisable ? |
|---|---|---|
| **`EMC-5`** - aucun item a deux reponses defendables | **moi seule**, et l'item 1 le prouve | non |
| **L'exactitude institutionnelle** (les cinq articles de la Convention) | **moi**, contre source. Recalculable par quiconque a la source | non |
| **`CYC-1`** - qu'aucun item de cycle 3 ne dementisse un item de cycle 2 | **moi seule**, par lecture, et **au perimetre du depot** | non |
| **Qu'une proposition ne soit pas un decor** | **moi**, mais **c'est comptable** | **oui** |
| **Que les deux moities du libelle soient servies par les deux taches** | **moi seule** | non |

### 8. Ce que ce refus ne dit pas

- **Il ne dit pas que le paquet est a jeter.** Dix items sur onze sont exacts, bien sources, bien
  ecrits pour le niveau, et `FER-3` y est mieux tenue que sur les trois autres paquets d'EMC du
  sprint. **Ce qui est du est un item de remplacement et deux ajustements.**
- **Il ne revoque rien** et ne bloque pas le sprint suivant. L'increment retourne a `camille-po`.
- **Il ne dit rien du lot** : `cm1-emc-fait-opinion` est refuse par ailleurs, pour d'autres motifs.

---

# Second audit - reaudit de la reprise (s29, 2026-09-06) - **VALIDE SOUS RESERVE**

- **Commit audite** : `4dbf9b2` (`[LEA] s29: reprise des trois paquets d'EMC refuses par
  l'instance B`), arbre `round-two` a `4dbf9b2`.
- **Verdict** : **VALIDE SOUS RESERVE**. **Done pedagogique prononce le 2026-09-06** par
  `nadia-prof`, apres avoir joue. **`EMC-CM1-07` ferme** : `non couvert` -> **`couvert-valide`**.
  Trois reserves nommees et datees, `DRO-R1` a `DRO-R3`, aucune bloquante.
- **Le refus du 2026-09-05 n'est pas efface** : son bloc entier reste au-dessus, avec son motif et
  ses deux defauts supplementaires. **Il est leve, pas retire.**
- **Audite par** : `nadia-prof`, **instance B** de la parallelisation.

## Conditions de l'audit

**J'ai joue.** Jar reconstruit sur `4dbf9b2` et relance, **partie complete de dix manches par
appels HTTP reels**, en pressant les mauvaises propositions d'abord. **Et j'ai lu les onze items** :
celui que le tirage a laisse de cote est **l'item 1**, c'est-a-dire **precisement l'item de
remplacement** (*"Jouer et se reposer en dehors du travail scolaire, qu'est-ce que c'est pour un
enfant ?"*). Je l'ai lu, et il porte deux des reserves ci-dessous. **Sur les quatre paquets que
j'ai rejoues aujourd'hui, l'item que le tirage a laisse de cote etait deux fois celui qui comptait**
- ici l'item de remplacement, et sur `cm1-emc-fait-opinion` le seul item que mon motif de refus
n'atteignait pas. **Le tirage en laisse toujours un : lire les onze n'est pas une precaution, c'est
la methode.** **Ce n'est pas un audit degrade.**

## 1. Le motif de refus - l'item 1 avait deux reponses defendables - **paye**

L'item est **remplace et non supprime**, comme je l'avais demande en nommant `DEC-0064` :
*"Aller a l'ecole pour apprendre a lire et a compter"* -> *"Jouer et se reposer en dehors du travail
scolaire"*, bonne reponse *un droit*. **Le paquet reste a onze items.**

**Et la propriete que j'avais donnee est tenue, pas seulement la forme** : le droit au jeu et au
repos **ne porte aucune obligation symetrique pour l'enfant** - personne ne lui reproche de ne pas
se reposer. La confusion que je refusais - aller a l'ecole classe *droit*, y travailler classe
*devoir*, dans la meme partie - **n'existe plus** : les six items de classement se lisent
maintenant sans se contredire, et je les ai vus s'enchainer a l'ecran. **`EMC-5` est restauree.
Motif leve.**

## 2. Les deux defauts supplementaires - **payes**

- **Le decor** : *"une interdiction"*, offerte sur six items de classement sur six et jamais
  correcte, est **retiree**. Ma phrase - *"une proposition qui n'est jamais juste sur onze items
  n'est pas un distracteur"* - est payee par le retrait, et non par une categorie inventee.
  **Ma reponse sur le passage a deux propositions est ecrite dans la fiche de
  `bandit-manchot-fait-opinion-cm1`, section 3 du second audit, et elle vaut ici** : la distinction
  droit / devoir est binaire dans le libelle meme de `EMC-CM1-07`, sept paquets du depot servent
  deja des items a deux propositions, et j'en ai valide. **Je ne refuse pas ici ce que j'ai valide
  ailleurs.**
- **La seconde tache ne portait que sur des droits** : l'item 4 devient *"A quoi sert le devoir
  d'ecouter la maitresse et de respecter les regles de la classe ?"*, bonne reponse *"a ce que
  chacun puisse apprendre sans etre derange"*. **La moitie *devoir* du libelle est desormais servie
  par les deux taches**, et la reponse est juste : un devoir de classe protege le travail de tous,
  il ne sert ni a empecher de bouger ni a trier les eleves sages - les deux distracteurs sont bien
  choisis, ils disent ce qu'un enfant croit parfois. **Le droit que cet item servait - etre protege
  contre la violence - reste servi par l'item 5 en classement**, je l'ai verifie. Paye.

## 3. Ce que j'ai verifie pour m'assurer que la reprise n'a rien casse

- **`HIS-3`** : le retrait de l'item sur la protection contre la violence n'a fait tomber **aucune**
  des cinq sources. Les cinq droits cites restent ceux de la Convention internationale des droits de
  l'enfant - articles 28, 19, 24, 31 et 12 - et **le droit au jeu et au repos, qui entre par
  l'item 1, est l'article 31, deja cite par le paquet.** Aucun renvoi neuf, aucun renvoi orphelin.
  **Verifie une seconde fois contre la source.**
- **`FER-1`** : *un droit* (items 1, 5, 9), *un devoir* (items 3, 7, 11). Tenue. **`FER-2`** : 11
  items. **`FER-3`** : deux taches, six items de classement et cinq de raison d'etre. Tenues.
- **`EMC-1`, `EMC-5`, `EMC-8`, `EMC-10`, `BIA-4`, `EMC-6`** : tenues, verifiees item par item. La
  formule *"quelle que soit sa famille"* est conservee et reste bonne.
- **`CYC-1`** : rien de la reprise ne touche a la continuite avec `cp-emc-regles-de-vie` -
  l'item 3 classe toujours les regles de classe comme un devoir, et l'item 4 dit maintenant a quoi
  ce devoir sert, ce qui **prolonge** le CP au lieu de le dementir. **Verification refaite, meme
  conclusion qu'au 2026-09-05.**
- **Chemin d'erreur** : l'enfant reste sur l'item, l'indice arrive, il n'est jamais bloque.

## 4. **`DRO-R1`** - trois items donnent la reponse d'un autre item

**Observation neuve du 2026-09-06, vue a l'ecran avant d'etre verifiee dans le fichier.** Dans ma
partie, la manche 3 a servi *"A quoi sert le **devoir** d'ecouter la maitresse et de respecter les
regles de la classe ?"*, et la manche 7 a demande *"Ecouter la maitresse et respecter les regles de
la classe, qu'est-ce que c'est pour un eleve ?"*. **La reponse de la seconde etait ecrite en toutes
lettres dans l'enonce de la premiere.**

**Le fichier porte trois couples de cette forme :**

| L'enonce qui nomme la categorie | L'item de classement qu'il resout |
|---|---|
| item 4, *"le **devoir** d'ecouter la maitresse..."* | item 3, *"Ecouter la maitresse..."* -> *un devoir* |
| item 8, *"le **droit** de jouer et de se reposer"* | item 1, *"Jouer et se reposer..."* -> *un droit* |
| item 6, *"le **droit** d'etre soigne quand on est malade"* | item 9, *"Etre soigne quand on est malade..."* -> *un droit* |

**Trois des six items de classement se repondent sans distinguer quoi que ce soit**, pour peu que le
tirage ait sorti l'autre item du couple avant - et sur dix manches tirees parmi onze items, il le
fait presque toujours.

**Ce que je dois dire pour etre juste** : **deux de ces trois couples existaient avant la reprise**
et je ne les avais pas vus ; **la reprise en a cree un** (items 1 et 8) et en a transforme un autre
en sa version *devoir*. **Ce n'est donc pas un defaut de la reprise seule, et ce n'est pas un
durcissement de mon refus : c'est une observation neuve, datee, que j'ouvre comme reserve** parce
que je vois aujourd'hui ce que je n'ai pas vu hier.

> **`DRO-R1`, ouverte le 2026-09-06, non bloquante. Propriete a tenir** : l'enonce d'un item de
> seconde tache ne nomme pas la categorie d'une situation qu'un item de classement demande de
> classer. Deux issues : la seconde tache porte sur une situation que le classement ne traite pas,
> ou son enonce ne prononce ni *droit* ni *devoir* (par exemple *"A quoi sert le fait d'ecouter la
> maitresse ?"*). **Les formulations sont a `lea-dev`.**

## 5. **`DRO-R2`** - sur un item binaire, un indice nomme la categorie au lieu du critere

**La condition que je pose le meme jour sur `cm1-emc-fait-opinion`** (fiche jumelle, section 3 du
second audit) : sur un item a deux propositions, **une erreur revele la reponse par elimination**,
si bien que l'indice n'a plus de reponse a proteger - **il ne lui reste qu'a enseigner le critere**.

**Sur les six items de classement de ce paquet :**

- **item 1** : *"**Ce droit** protege des moments de repos, pas le travail scolaire."* - l'indice
  **prononce la categorie a trouver**. Il ne dit pas pourquoi jouer et se reposer est un droit et
  non un devoir ; il l'affirme.
- **item 5** : *"Personne n'a **le droit** de faire du mal a un enfant."* - le mot y est employe
  dans un autre sens (la permission), mais il est le mot de la bonne reponse et il est le seul mot
  de categorie de la phrase. **Signal ambigu.**
- **items 3, 7, 11** : *"C'est ce qu'un eleve **doit faire** pour..."* - **c'est le critere**, et
  c'est la bonne facon de faire : l'enfant apprend qu'un devoir est ce qu'on doit faire.
- **item 9** : *"Chaque enfant peut etre soigne, quelle que soit sa famille."* - **le critere**,
  sans nommer la categorie. La meilleure des six.

> **`DRO-R2`, ouverte le 2026-09-06, non bloquante** : reecrire l'indice de l'item 1 pour qu'il
> donne le critere (ce qu'on peut exiger pour soi et que personne ne peut retirer) plutot que la
> categorie, et lever l'ambiguite de l'item 5. **Les quatre autres montrent deja la forme
> attendue.**

## 6. **`DRO-R3`** - le constat de serie du 2026-09-05, qui n'a pas ete aggrave par un lot neuf mais par une reprise

Le 2026-09-05, j'ai ecrit : *"sur les quatre paquets d'EMC de s29, l'enseignant est nomme trois fois
et il est trois fois une femme. Aucune occurrence masculine. Constat de serie [...] pose pour que le
prochain lot ne l'aggrave pas."*

**Recompte du 2026-09-06** : **quatre occurrences**, toujours **zero masculine** - deux dans ce
paquet (items 3 et 4), une dans `cm1-emc-fait-opinion`, une dans `cp-emc-regles-de-vie`. **C'est
l'item 4 de la reprise qui a ajoute la quatrieme.**

**Ce constat n'entre dans aucun de mes motifs, ni le 2026-09-05 ni aujourd'hui, et il ne retarde
rien.** Je le date et je le laisse ouvert comme reserve nommee pour qu'il cesse d'etre un constat
sans destinataire : `camille-po` peut en faire une story d'une ligne - **une occurrence masculine
suffit a l'equilibrer**, et le cout est un mot.

## 7. Ce dont je reste la seule garde sur ce paquet

| Propriete | Qui la tient | Automatisable ? |
|---|---|---|
| **`EMC-5`** - aucun item a deux reponses defendables | **moi seule** | non |
| **L'exactitude institutionnelle** (les cinq articles de la Convention) | **moi**, contre source | non |
| **`CYC-1`** - qu'aucun item de cycle 3 ne dementisse un item de cycle 2 | **moi seule**, par lecture, au perimetre du depot | non |
| **`DRO-R1`** - qu'un item ne donne pas la reponse d'un autre item du meme paquet | **moi seule.** Aucun test ne lit un item a la lumiere des dix autres, et c'est en jouant que je l'ai vu | **oui, en principe** - c'est une recherche de chaine |
| **`DRO-R2`** - que l'indice d'un item binaire porte le critere | **moi seule** | non |
| **`DRO-R3`** - l'equilibre des figures adultes sur une serie de paquets | **moi seule** | non |


---

# s30 - audit de `nadia-prof`, **instance A**, 2026-09-06

- **Verdicts complets du sprint** :
  `squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/verdicts-nadia-instance-A.md`.
- **Passe transverse** : `squad/conformite/PASSE-TRANSVERSE-s30-les-trois-familles-de-portee-depot.md`.
- **Arbre audite** : `round-two`, HEAD `dbbfe129`, joue depuis le worktree `s30-nadia-A`, port 8082.
- **Done technique, mesure par moi depuis mon worktree** : `bash outils/mvn-jdk21.sh -B clean verify`
  sur le reacteur complet, **BUILD SUCCESS** ; **179 rapports, 1321 tests, 0 echec, 0 erreur,
  0 ignore**, machine virtuelle rapportant **21.0.10**.
- **Audit degrade sur un seul point, et je le nomme** : j'ai joue **par requetes HTTP**, donc j'ai lu
  le HTML servi et non la page rendue. Cela ne couvre ni la couleur, ni le contraste, ni la taille,
  ni la position ; **aucun jugement de ce bloc n'y repose**.
- **Aucun verdict anterieur n'est efface par ce bloc.**

## Verdict : **VALIDE SOUS RESERVE** - `DRO-R1`, `DRO-R2`, `DRO-R3` levees, `DRO-R4` ouverte

**J'ai joue** `bandit-manchot-droit-devoir-cm1`, partie jouee volontairement mal, **revelations
atteintes sur trois manches**, **et j'ai relu les onze items**, indices compris.

**Le refus du 2026-09-05 et la validation sous reserve du 2026-09-06 restent lisibles au-dessus.
Aucun n'est efface.**

### `DRO-R1` - **LEVEE**, et le choix de l'equipe **tient**

**Ma reserve laissait deux issues** : la seconde tache porte sur une situation que le classement ne
traite pas, ou **son enonce ne prononce ni *droit* ni *devoir***. **`marc-dev` a pris la seconde**,
qui est celle dont j'avais donne l'exemple mot pour mot (*"A quoi sert le fait d'ecouter la
maitresse ?"*).

| Item | Enonce livre |
|---|---|
| 4 | *"A quoi sert, pour un eleve, le fait d'ecouter le maitre et de respecter les regles de la classe ?"* |
| 6 | *"A quoi sert, pour un enfant, le fait d'etre soigne quand il est malade ?"* |
| 8 | *"A quoi sert, pour un enfant, le fait de jouer et de se reposer ?"* |

**Aucun ne prononce plus *droit* ni *devoir*.** Les trois items de classement associes - 3, 9 et 1 -
ne sont plus resolus par la lecture de l'enonce d'un autre item.

**Le choix tient, et le motif du diff minimal est le bon.** L'autre issue obligeait a inventer trois
situations neuves, donc trois faits institutionnels neufs a sourcer sous `HIS-3`, sur une ligne ou les
cinq droits cites sont exactement ceux de la Convention internationale des droits de l'enfant.
**Changer la formulation coutait trois lignes ; changer les situations rouvrait la verification de
source sur une ligne deja fermee.** C'etait le mauvais echange, et l'equipe ne l'a pas fait.

### `DRO-R2` - **LEVEE**, et verifiee au-dela de ce que la reserve demandait

| Item | Indice avant | Indice livre |
|---|---|---|
| 1 | *"**Ce droit** protege des moments de repos, pas le travail scolaire."* | *"C'est quelque chose que chaque enfant peut reclamer pour lui-meme, sans que personne ne puisse le lui retirer."* |
| 5 | *"Personne n'a **le droit** de faire du mal a un enfant."* | *"Aucun adulte, ni personne d'autre, n'est autorise a faire du mal a un enfant."* |

**Les deux sont exactement ce que j'avais demande.** L'item 1 porte desormais **le critere** - *ce
qu'on peut exiger pour soi et que personne ne peut retirer* -, au lieu d'affirmer la categorie ;
l'item 5 n'emploie plus le mot *droit* dans son second sens (la permission), et le signal ambigu
disparait. **Les items 3, 7, 9 et 11 ont ete verifies inchanges**, comme je l'avais constate moi-meme
- ils portaient deja le critere.

**Et j'ai mesure l'extension que j'avais posee moi-meme** le 2026-09-06 (*"regle etendue a tout item
binaire du depot"*), parce qu'une regle de portee depot que personne ne mesure est un souhait :

| Mesure sur les 58 paquets servis | Compte |
|---|---|
| Items binaires du depot | **111** |
| Indices qui nomment la categorie plutot que le critere | **0** |

Le detail et les trois faux positifs de ma recherche sont en section 4 de
`PASSE-TRANSVERSE-s30-les-trois-familles-de-portee-depot.md`. **La regle est tenue partout ; je ne
demande rien a personne sur cet axe.**

### `DRO-R3` - **LEVEE**

Ma barre : *"une occurrence masculine suffit a l'equilibrer."* **Deux sont livrees** - *le maitre*
aux items 3 et 4. Recompte aujourd'hui sur les quatre paquets d'EMC : **quatre occurrences d'un adulte
de classe, deux masculines** (ce paquet) **et deux feminines** (`cm1-emc-fait-opinion` item 8,
`cp-emc-regles-de-vie` item 2). **La serie est equilibree.**

### `DRO-R4` - **reserve nouvelle, datee du 2026-09-06, non bloquante**

**Vue a l'ecran avant d'etre verifiee dans le fichier**, sur deux manches de ma partie. **Le defaut
que `DRO-R1` nommait dans l'enonce survit une ligne plus bas, dans l'indice des trois memes items.**

| Indice livre | L'item de classement qu'il resout |
|---|---|
| item 4 : *"**Ce devoir** permet a tout le monde de travailler dans le calme."* | item 3, *"Ecouter le maitre et respecter les regles de la classe"* -> *un devoir* |
| item 6 : *"**Ce droit** concerne la sante de l'enfant, pas son ecole."* | item 9, *"Etre soigne quand on est malade ou blesse"* -> *un droit* |
| item 8 : *"**Ce droit** protege des moments de repos, pas l'absence de toute regle."* | item 1, *"Jouer et se reposer en dehors du travail scolaire"* -> *un droit* |

**Trois sur trois.** Un enfant qui se trompe une fois sur l'item 6 lit *"Ce droit"* et sait, pour tout
le reste de la partie, comment classer *etre soigne*.

**`DRO-R1` est levee et je ne la rouvre pas.** Ma reserve disait *"**l'enonce** d'un item de seconde
tache ne nomme pas la categorie"*, et **l'enonce est corrige, exactement comme demande**. Ce que
j'ouvre est une **observation neuve et datee, sur une autre partie de l'item**. **Je refuse de faire
passer un durcissement pour une reserve impayee** : le poteau que j'avais plante est atteint.

**Trois precisions pour que la story soit correctement dimensionnee** :

- **La gravite est moindre que celle de `DRO-R1`** : l'enonce se lit a chaque tirage de l'item,
  l'indice seulement apres une erreur.
- **`DRO-R2` ne couvre pas ces trois-la** : elle porte sur les items **binaires**, et ces trois items
  portent trois propositions.
- **Rien de faux n'est enseigne** : les trois indices sont exacts. Ils resolvent un autre item, ce qui
  est la contrefacon que `CON-1` decrit, pas une erreur.

> **`DRO-R4`. Propriete a tenir** : sur les trois items de la seconde tache, l'indice ne prononce ni
> *droit* ni *devoir* a propos d'une situation qu'un item de classement demande de classer. **Il dit
> a quoi la chose sert**, ce qui est la tache de l'item. **Regime : mot pour mot une fois les trois
> textes proposes, 0,25** - le geste est de la meme nature que celui qui vient d'etre fait sur les
> enonces.

### `CONS-1` - **non levee sur ce jeu**

Consigne servie : *"Lis bien, puis dis si c'est un droit ou un devoir."* **Vu a l'ecran sur trois
manches de ma partie**, au-dessus d'items dont la question est *"A quoi sert, pour un eleve, le fait
d'ecouter le maitre... ?"*.

**Elle nomme une des deux taches du paquet** - le classement, six items - **et laisse les cinq autres
sans consigne**. C'est **mot pour mot la propriete de `FOP-R1`**, que j'ai ecrite le 2026-09-06 :
*"la consigne d'un jeu ne decrit pas une tache que la moitie de ses items ne demandent pas."*

**Et elle a ete payee sur le paquet jumeau le meme jour** (commit `dbbfe12`, `fait-opinion-cm1`, dont
la consigne nomme desormais ses deux taches), **sans l'etre ici**. Les deux paquets d'EMC CM1 ont ete
livres ensemble, ils portent la meme structure a deux taches, et ils ressortent du sprint avec deux
traitements differents. **C'est la correction la moins chere des cinq restantes : la propriete est
deja ecrite, deja jugee, deja payee a cote.**

> **`CONS-1`, non levee sur cette entree. Regime : rejeu borne, compte dans le reliquat (0,5 pour les
> cinq entrees restantes).**

**Pourquoi ce n'est pas bloquant** : sur les cinq items de la seconde tache, l'enonce porte lui-meme
sa question, entiere et explicite. **`EMC-CM1-07` reste `couvert-valide`. Aucune ligne ne bouge.**
