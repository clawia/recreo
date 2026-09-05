# Fiche de conformite - bandit-manchot-droit-devoir-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-emc-droit-devoir.yaml`.
- **Entree de catalogue** : `bandit-manchot-droit-devoir-cm1`, titre servi *"Droits et devoirs"*,
  consigne servie *"Lis bien la question, puis choisis la bonne reponse."*, `nombreDeManches: 10`.
- **Statut global** : **REFUSE** (Done pedagogique **refuse le 2026-09-05** par `nadia-prof`, apres
  avoir joue). **`EMC-CM1-07` ne ferme pas** : elle reste `non couvert`.
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`,
  section 4.4, et `squad/programme/enseignement-moral-et-civique/competences-cm1.md`.
- **Commit audite** : `ee3ef70` (`[LEA] s29: S29-5`).
- **Derniere mise a jour** : 2026-09-05 (**verdict de `nadia-prof` apres avoir joue**, s29,
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
