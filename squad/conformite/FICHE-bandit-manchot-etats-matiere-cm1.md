# Fiche de conformite - bandit-manchot-etats-matiere-cm1

- **Mecanique** : bandit manchot.
- **Contenu** :
  `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-etats-matiere.yaml`.
- **Entree de catalogue** : `bandit-manchot-etats-matiere-cm1`, titre servi *"États et changements
  d'état"*, consigne servie *"Lis bien, puis dis dans quel état se trouve la matière, ou nomme le
  changement d'état."*, `nombreDeManches: 10`.
- **Statut global** : **VALIDE** (Done pedagogique **prononce le 2026-09-07** par `nadia-prof`,
  instance B, **au premier passage**, apres avoir joue le paquet). **`SCI-CM1-01` ferme** :
  `non couvert` -> **`couvert-valide`**, **sans mention de couverture partielle** et **sans
  reserve**.
- **Histoire des verdicts, aucun efface** : **2026-09-07, VALIDE**. Premier et seul verdict.
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md`,
  section 4.3 (ligne 1, *"le terrain le plus sur du lot"*) et section 5 (`FAB-1`, `PLA-1`,
  `BIN-1`), et `squad/programme/sciences-et-technologie/competences-cm1.md`.
- **Commit audite** : `3181b5e6` (`[LEA] s31: S31-3 et S31-4 - second passage de sciences CM1,
  quatre lignes de plus`), le 2026-09-07.
- **Derniere mise a jour** : 2026-09-07 (`nadia-prof`, s31, instance B).

## Competence visee

`Décrire les états de la matière et les changements d'état (solide, liquide, gaz ; fusion,
solidification, vaporisation, condensation).` (`SCI-CM1-01`, sciences et technologie, CM1,
*Matiere, mouvement, energie, information*).

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **VALIDE**
- **Date** : 2026-09-07
- **Audit joue** : **oui**

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `3181b5e6`, worktree dedie `s31-nadia-B`, jar reconstruit depuis
ce worktree sur le JDK de la toolchain (21.0.10, version rapportee par la machine virtuelle) et
lance sur le **port 8081**. **Partie complete de dix manches par appels HTTP reels, avec une erreur
volontaire sur chaque manche, poussee jusqu'a trois erreurs sur une manche pour atteindre la
revelation**, ecran de fin atteint, score 10. **Et j'ai lu les onze items**, y compris ceux que le
tirage a laisses de cote. **Ce n'est pas un audit degrade.**

**Ce que l'audit ne couvre pas** : j'ai lu le HTML servi, pas la page rendue par un navigateur.
Couleur, contraste, taille et position ne sont pas juges ici.

### Ce qui a ete verifie

- **Ancrage au programme** : ligne `SCI-CM1-01` declaree, libelle du descripteur **copie conforme**
  du referentiel, verifie contre `squad/programme/sciences-et-technologie/competences-cm1.md`.
  **`FER-1` : les sept elements de la parenthese occupent la position de bonne reponse** - *solide*
  (items 1, 4), *liquide* (2, 5), *gaz* (3), *la fusion* (6, 10), *la solidification* (7), *la
  vaporisation* (8, 11), *la condensation* (9). Aucun element du libelle n'est hors de portee, donc
  **aucune mention de couverture partielle n'est due** ; ma table du 2026-09-06 n'en exigeait
  d'avance que sur les lignes **6**, **14** et **20**, et la ligne 1 n'en fait pas partie. **Je n'en
  ajoute pas une apres coup.**
  **`FER-3` : deux taches distinctes par l'operation mentale** - *classer* un etat decrit (cinq
  items) d'un cote, *nommer* le changement d'etat qu'une situation decrit (six items) de l'autre.
  La moins servie en porte cinq, pour un plancher de trois. **`FER-2`** : onze items pour cette
  seule ligne, plancher de dix.
- **Lisibilite au niveau vise** : la consigne fait **une ligne**, elle nomme les deux taches, et
  c'est ce que `DEC-0078` demande. Les enonces font une phrase et decrivent tous une scene qu'un
  enfant de CM1 a deja vue : un glacon sur une table, une poele, un miroir de salle de bain, une
  flaque dans la cour. **Aucun mot hors du vocabulaire de CM1** dans les enonces.
  *Un point que j'ai regarde de pres avant de ne pas en faire une reserve* : l'indice de l'item 3
  dit *"Il n'a ni forme ni volume propres : il occupe tout l'espace disponible."* La formule *volume
  propre* n'est pas un mot d'enfant. Elle est **glosee par la seconde moitie de la meme phrase**,
  qui est la formulation de cycle 3, et l'enfant qui ne comprend pas la premiere moitie a la
  reponse dans la seconde. **Verifie a l'ecran, pas suppose.**
- **Charge cognitive** : **une seule propriete par item**, jamais deux. Verifie sur les onze : un
  item demande l'etat, ou le nom du changement, jamais les deux.
- **Progression** : la tache A monte d'un objet familier (le glacon) a une matiere invisible
  (l'air) ; la tache B monte du changement le plus visible (le glacon qui fond) au moins visible
  (la vapeur qui se condense sur un miroir). Le tirage melange les deux, ce qui est le
  fonctionnement de la mecanique et non un defaut du paquet.
- **Traitement de l'erreur** : **verifie a l'ecran, sur trois manches differentes**. L'indice
  s'affiche des la premiere erreur ; a la troisieme, la bonne reponse est revelee et l'enfant peut
  avancer. **Aucun blocage.**
- **Exactitude du contenu** : **les onze items sont exacts**, verifies un par un. Trois points que
  j'ai controles parce qu'ils sont les fautes usuelles de cette lecon, et que le paquet evite :
  1. l'indice de l'item 9 dit *"La vapeur d'eau, invisible dans l'air chaud, redevient liquide au
     contact du miroir froid."* - **la vapeur d'eau y est declaree invisible**, la ou beaucoup de
     supports appellent *vapeur* le nuage blanc au-dessus d'une casserole, qui est deja de l'eau
     liquide ;
  2. l'item 10 (le beurre qui fond) et l'item 11 (l'eau qui bout) nomment tous deux un changement
     d'etat exact, l'ebullition etant bien une vaporisation ;
  3. le paquet n'ecrit **nulle part** que l'eau *disparait*, ne parle jamais d'une eau *perdue* ou
     *partie*, et l'item 8 en fait le sujet meme de sa correction.
  **`SCI-3`** : **zero item numerique sur onze.**
- **Plausibilite des distracteurs (`PLA-1`, ma clause du 2026-09-07)** : **tenue sur les onze
  items, et c'est le seul des quatre paquets de ce lot dans ce cas.** Les deux taches sont des
  classements a categories fermees : les distracteurs sont les deux autres etats, ou deux autres
  noms de changement d'etat. **Chacun est une reponse qu'un enfant de CM1 donne vraiment** -
  confondre condensation et vaporisation, appeler *solidification* ce qui fond. Aucune proposition
  de remplissage, aucune proposition qui n'appartient a rien.
- **`SCI-1`, et c'est ici qu'il est a son point le plus exigeant** : l'item 8 porte *"la
  disparition"* en distracteur - la conception fausse la plus repandue de cette lecon - et **son
  indice la corrige nommement** : *"L'eau n'a pas disparu : elle est devenue un gaz invisible,
  présent dans l'air."* **Verifie a l'ecran** (manche jouee, erreur volontaire sur *"la
  disparition"*, retour lu). **`SCI-4`** : cet item porte bien trois propositions, et les trois
  restent des candidats - la barre de `SCI-4` et **l'objet** de cette barre sont tenus tous les
  deux, ce qui n'avait pas ete le cas des deux fois precedentes (`SIG-R2`, `MEL-R3`).
- **`FAB-1`, ma clause du 2026-09-07, les trois branches** :
  **(a)** la bonne reponse **varie** a l'interieur de chaque tache - trois valeurs differentes en
  tache A, quatre en tache B. *Lecture de ma propre clause, dite ici parce qu'elle est ambigue et
  que je ne veux pas qu'on l'applique de travers* : la premiere phrase de `FAB-1a` (*"deux items ne
  portent pas la meme bonne reponse"*) est **impossible a tenir** sur un classement a categories
  fermees de cinq items et trois categories, et ma propre note d'exception le dit explicitement
  (*"c'est licite des lors que la bonne reponse varie"*). **C'est l'exception qui porte le sens, et
  c'est elle que j'applique** ; la phrase litterale est plus large que ce que la clause veut, et je
  le corrige ici sans durcir ni relacher la barre ;
  **(b)** les distracteurs viennent de l'ensemble ferme des categories, ce que l'exception de
  `FAB-1` autorise nommement, et **cela n'ouvre aucune elimination** : pour ecarter *liquide* et
  *gaz*, il faut savoir ce qu'est un solide ;
  **(c)** aucune forme ne designe la bonne reponse - memes amorces (*la ...*), memes longueurs.
- **`BIN-1`** : sans objet. La tache principale classe sur **trois** categories, jamais deux, et
  **aucun item du paquet ne porte deux propositions** (verifie par comptage : 11 items, 3
  propositions chacun).
- **`CYC-1` - recouvrement CP / CM1, verifie et non declare.** J'ai lu `cp-qlm-etats-eau.yaml`
  (`QLM-CP-05`) : le CP enseigne *fondre* et *geler*, et ne connait que *solide* et *liquide*. Le
  CM1 nomme *fusion* et *solidification* **dans le meme sens**, et ajoute le gaz. **Progression,
  pas redite, et aucune contradiction.** J'ai lu aussi `cp-qlm-vivant-non-vivant.yaml` : sans objet
  ici, la condition y est vacante, comme le commentaire du paquet le declare.
- **La couture avec `cm1-sciences-melanges` (`SCI-CM1-02`), que j'avais nommee a s30 et qui mordait
  aujourd'hui.** Ma fiche du 2026-09-06 ecrivait : *"`CYC-1` vers `SCI-CM1-01`, non encore servie -
  elle mordra le jour ou les etats de la matiere seront servis."* **Ce jour est aujourd'hui, et je
  la juge.** J'ai lu le paquet `melanges` tel qu'il est servi apres `S31-5` : l'indice de l'item du
  sel dit *"Il reste caché dans l'eau tant que celle-ci garde sa forme liquide."*, et il laisse
  ouverte la question de ce que devient l'eau. **Ce paquet-ci la referme** : l'eau devient un gaz
  invisible present dans l'air. **Les deux paquets ne se contredisent sur aucun mot, et vivront
  cote a cote sans qu'un enfant apprenne dans l'un l'inverse de ce qu'il apprend dans l'autre.**
  *Je ne juge pas le paquet `melanges` lui-meme : il est du perimetre de l'instance A. Je juge la
  seule chose qui soit de mon cote de la couture - l'absence de contradiction.*
  *Un point de vocabulaire que j'ai regarde et qui n'est pas une contradiction* : `melanges` dit
  *"laisser l'eau s'évaporer"* et *"Les grains ont disparu à l'œil"*, ce paquet-ci dit *"la
  vaporisation"* et refuse *"la disparition"*. L'evaporation est une vaporisation, et *disparu a
  l'oeil* n'est pas *disparu*. **Les deux formulations sont justes ; elles ne se dementent pas.**
- **Biais et ton** : rien. Aucune personne nommee, aucun foyer, aucun stereotype, aucun ton
  condescendant. Les objets cites (congelateur, refrigerateur, salle de bain, casserole, poele)
  sont d'un quotidien tres largement partage et **ne sont jamais attribues a l'enfant qui joue** :
  les enonces disent *"un glacon"*, *"le miroir"*, jamais *"ton"*.

### Motif

**Rien a corriger. Je valide sans reserve, et le motif est positif.**

Ce paquet fait, sur onze items, ce que les trois autres paquets que j'ai audites aujourd'hui ne
font pas : **chacune de ses propositions est une reponse qu'un enfant peut vraiment donner.** Un
enfant qui ne sait pas ce qu'est la condensation ne peut rien eliminer et doit apprendre ; c'est
exactement ce qu'on demande a un exercice. Et son item de vaporisation est **l'item de sciences le
mieux fabrique que j'aie lu dans ce depot** : il place la conception fausse en distracteur, la
nomme, et la corrige dans le retour, ce que `SCI-1` demande depuis le 2026-09-03 sans l'avoir
jamais obtenu aussi nettement.

**Ce que je dis a l'equipe pour que ce ne soit pas perdu** : l'item 8 de ce paquet est la forme a
recopier. Les items refuses de `familles-materiaux` et les items sous reserve de
`regimes-alimentaires` se reparent en la prenant pour modele.

### Reserves

**Aucune.**

### Mentions de non-couverture opposables

> **`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire defiler la page, un element
> quelconque du jeu, y compris le retour qui suit sa reponse - verdict, indice, proposition. Cette
> propriete est sortie de la porte pedagogique par arbitrage de l'utilisatrice du 2026-08-28
> (`DEC-0053`) ; sa dette est ouverte, croissante et assumee. Aucune mesure ne l'a instruite sur ce
> paquet.

### Ce dont je reste la seule garde sur ce paquet

**Cette section existe pour qu'on ne croie pas couvert ce qui ne l'est pas.** La CI etait verte sur
les quatre paquets de ce lot, et deux sont refuses.

| Propriete | Garde executable ? |
|---|---|
| **L'exactitude de chacun des onze faits de physique** | **aucun.** Aucun automate ne lit un fait |
| **`SCI-1`** - que l'indice corrige la conception fausse qu'il rencontre | **aucun** |
| **`PLA-1`** - qu'une proposition soit un candidat et non un remplissage | **aucun**, et `ADR-0086` a mesure que le mot portant (*plausible*) n'est pas une chaine. C'est l'un des deux defauts qui ont produit un refus a s30, **rien n'en avertit la developpeuse**, et c'est ce paquet-ci qui montre a quoi ressemble la propriete tenue |
| **`FAB-1b`** - que les distracteurs ne soient pas systematiquement pris dans le vivier des bonnes reponses des autres items | **aucun.** Ici la propriete est **sans objet** : les categories sont fermees, et l'exception de `FAB-1` couvre ce cas |
| **`CYC-1`** - la coherence entre le CP et le CM1, et la couture avec `melanges` | **aucun.** Verifiee a la main, ici, par lecture des paquets de CP et du paquet `melanges` servi |

**Ce que les deux garde-fous neufs d'`ADR-0086` ont couvert ici**, et je le dis pour la raison
inverse : la clause 1 (un item servi plusieurs fois sous plusieurs noms) et la clause 2 (la
contrefacon par l'amorce) sont vertes sur ce paquet, et **je confirme leur verdict a la lecture** -
il n'y a ni item clone ni amorce contrefaite. **Sur ce paquet, elles ne me remplacent pas mais
elles ne me trompent pas non plus.**

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`SCI-CM1-01` : `non couvert` -> `couvert-valide`**, sans mention de couverture partielle et sans reserve | `camille-po` | Le compte de la matrice lui appartient ; **je ne la touche pas** |
| **L'item 8 comme modele de fabrication** pour les items a reparer sur `familles-materiaux` et `regimes-alimentaires` | `camille-po`, via `sofia-sm` | A citer dans les stories de reprise, pour que la correction ne se cherche pas une forme |
| **La couture `SCI-CM1-01` / `SCI-CM1-02` est refermee** | `camille-po` | La condition que j'avais posee a s30 sur `FICHE-bandit-manchot-melanges-cm1.md` est **levee** de mon cote |
