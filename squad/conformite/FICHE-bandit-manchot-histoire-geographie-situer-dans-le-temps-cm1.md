# Fiche de conformite - Situer dans le temps (`bandit-manchot-histoire-geographie-situer-dans-le-temps-cm1`)

> **Fiche ouverte par `nadia-prof` a s35** (`S35-CONS-4`), et non par `camille-po`. Cette entree de
> catalogue est neuve : elle est l'une des **quatre** issues de la scission de
> `bandit-manchot-histoire-geographie-cm1` au commit `b16bbbcb`. **Aucun de ses items n'est neuf** -
> les 25 items de ce paquet viennent tous du paquet d'origine, mot pour mot.
>
> **Toute l'histoire pedagogique de ces items, jusqu'a s35, vit dans
> `FICHE-bandit-manchot-histoire-geographie-cm1.md`** : cinq verdicts de `nadia-prof` de s27 a s33,
> et les reserves `HG-R*` ouvertes, qui **restent opposables** aux items de ce paquet. Cette fiche
> ne les recopie pas - un verdict recopie n'est plus une preuve - elle y renvoie. Les sections
> descriptives sont reprises de `catalogue.yaml`, du paquet et du referentiel, sans rien y ajouter ;
> **le bloc de validation est le mien, comme toujours.**

- **Mecanique** : `bandit-manchot`
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-histoire-geographie-situer-dans-le-temps.yaml` (25 items)
- **Entree de catalogue** : `bandit-manchot-histoire-geographie-situer-dans-le-temps-cm1`, titre servi *"Situer dans le temps"*, `nombreDeManches: 10`
- **Fiche de l'ancetre** : `FICHE-bandit-manchot-histoire-geographie-cm1.md`
- **Statut global** : **VALIDE** (premier passage sur cette entree, 2026-09-11, s35). **`CONS-4` levee sur cette entree.** Une reserve non bloquante heritee de
  l'audit de la scission : aucune propre a ce paquet.
  **Sur les deux items neufs de `S42-HG-ROIS` : VALIDE SOUS RESERVE** (2026-09-24, s42,
  `nadia-prof` instance A), une reserve neuve opposable ici, `HG-R25`, non bloquante
- **Derniere mise a jour** : 2026-09-24 (verdict pedagogique sur les deux items neufs,
  `nadia-prof` instance A)
- **Amendement du 2026-09-24 (s42, `camille-po`)** : `S42-HG-ROIS` ajoute deux items neufs portant
  `HG-CM1-05`, non couverts par le verdict de 2026-09-11 ci-dessous. Voir la section "Ajout du
  2026-09-24" plus bas.

## Competences visees

| Matiere | Niveau | Domaine | Lignes de referentiel servies |
|---|---|---|---|
| Histoire-geographie | CM1 (cycle 3) | Reperes historiques et geographiques | `HG-CM1-01`, `HG-CM1-02`, `HG-CM1-07` |

`squad/programme/histoire-geographie/competences-cm1.md`. **Aucun statut de matrice ne bouge par
cette scission** : ces lignes gardent exactement l'etat que mes verdicts de s27 a s33 leur ont
donne, et les items qui les servent n'ont pas change.

## Criteres pedagogiques attendus - camille-po

Repris de `docs/backlog/backlog.md`, stories `S34-CONS-4` (criteres qui font foi) et `S35-CONS-4`,
plus les trois criteres ajoutes par `DEC-0088`.

- [x] Les items viennent du paquet d'origine, sans ajout, sans perte, sans texte change
- [x] La consigne de l'entree nomme la tache reellement servie par ce paquet (`CONS-4`)
- [x] Le descripteur `matiere` / `niveau` / `domaine` est identique a celui des trois autres paquets
      issus de la scission (`DEC-0088`), pour que `HIS-1` continue de se compter sur le lot de
      matiere entier
- [x] Plus d'items que de manches (`ADR-0023`) - 25 pour 10

## Ajout du 2026-09-24 (s42, `camille-po`) - `S42-HG-ROIS`

Deux items neufs ajoutes en fin de paquet (26e et 27e position) par `S42-HG-ROIS`
(`docs/backlog/backlog.md`), portant tous `HG-CM1-05` : ordonner les quatre rois du libelle
(Louis IX, Francois Ier, Henri IV, Louis XIV) les uns par rapport aux autres - jamais un jugement
de valeur sur le personnage (`BIA-5`). Cette story etend le paquet existant, elle n'ouvre pas une
forme neuve (`DEC-0077`, section 9). Livres et fusionnes dans `round-two` au commit `cdf0229f`
(contenu au commit `cbe35d0b`). Lien decision : `DEC-0098`.

`HG-CM1-05` est conjonctive ("nommer et situer dans le temps") : les criteres qui portent sur le
paquet livre dans son entier (`HIS-1`, les quatre rois chacun en bonne reponse au moins une fois)
se verifient sur les **cinq** items neufs de `S42-HG-ROIS` pris ensemble - les deux d'ici et les
trois de `cm1-histoire-geographie-nommer-en-histoire.yaml` (voir
`FICHE-bandit-manchot-histoire-geographie-nommer-en-histoire-cm1.md`), pas sur les deux d'ici
seuls.

**Le bloc de validation pedagogique ci-dessous porte sur les 25 items anterieurs a cet ajout
(verdict du 2026-09-11, s35) : il ne couvre pas ces deux items neufs.** Ils restent en attente
d'un Done pedagogique propre. `HG-CM1-05` reste `non couvert` dans `MATRICE-couverture.md` au
moment de cet ajout.

> **Note de `nadia-prof` du 2026-09-24 (s42, instance A)** : ce Done pedagogique propre a ete
> prononce le jour meme. Il est **plus bas dans cette fiche**, dans le bloc de validation date du
> 2026-09-24, et non dans le bloc du 2026-09-11 auquel le paragraphe ci-dessus renvoie. Verdict :
> **VALIDE SOUS RESERVE**, une reserve opposable ici, `HG-R25`, non bloquante.

Criteres d'acceptation de `S42-HG-ROIS` portant sur ces items, repris de la story :

- [x] Chaque item porte sur l'un des quatre rois nommes par le libelle, sa datation ou sa
      situation dans le temps par rapport a un autre roi du libelle - jamais un jugement de valeur
      sur le personnage (`BIA-5`). **Coche le 2026-09-24 par `nadia-prof`, item par item sur les
      cinq.**
- [x] Sur les cinq items neufs pris ensemble (les deux d'ici et les trois du paquet "nommer, en
      histoire"), au plus un tiers demande une date, un nom ou un chiffre a restituer seul ; les
      deux autres tiers demandent d'ordonner deux rois dans le temps, d'associer un roi a un fait,
      ou d'extraire une information d'un document (`HIS-1`). **Zero restitution pure sur les cinq.**
- [x] Une seule des trois propositions est defendable par item, aucune reponse discutable
      (`HIS-2`).
- [x] Chaque fait historique (date, nom, filiation) porte sa source dans le commentaire du
      paquet, et aucun fait n'est errone (`HIS-3`).
- [x] Sur les cinq items neufs pris ensemble, les quatre rois du libelle sont chacun en bonne
      reponse au moins une fois.
- [x] Chaque item declare `HG-CM1-05` et uniquement elle.

**Les six criteres sont tenus, et trois defauts sont passes entre eux** - `HG-R23`, `HG-R24` et
`HG-R25`. Les deux items d'ici n'en portent qu'un, `HG-R25`. Les trois reserves sont ecrites en
entier dans `FICHE-bandit-manchot-histoire-geographie-nommer-en-histoire-cm1.md`, section 3 du
verdict du 2026-09-24 : elles y vivent une seule fois, et cette fiche y renvoie plutot que de les
recopier.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **VALIDE**
- **Date** : 2026-09-11 (s35)
- **Auteur** : `nadia-prof`
- **Arbre** : `s35-integration` a `c1b326d4`, worktree `s35-nadia`
- **Audit joue** : **oui.** Jar reconstruit depuis mon propre worktree, lance sur le JDK `21.0.10`
  de la toolchain. **Une partie complete de 10 manches sur cette entree**, chaque manche
  jouee en pressant les propositions jusqu'a la bonne, pour lire les indices servis. **La consigne a
  ete lue a l'ecran, au-dessus de ses items, et non dans le catalogue.**
- **Mouvement de matrice** : **aucun.**
- **Portee de ce verdict** : il porte sur **ce que la scission fait a ces items** - leur fidelite au
  contenu d'origine et l'ajustement de la consigne neuve a ce paquet. **Il ne rejuge pas les items
  eux-memes** : ils ont deja recu cinq verdicts, et les reserves `HG-R*` encore ouvertes dans la
  fiche de l'ancetre **restent ouvertes sur ce paquet** et voyagent avec les items qui les portent.

### 1. Fidelite - verifiee, pas supposee

Mesure faite sur les quatre paquets a la fois, methode et chiffres complets dans le bloc de s35 de
`FICHE-bandit-manchot-histoire-geographie-cm1.md`, section 1 : **110 items a l'origine, 110 dans les
quatre paquets, zero enonce perdu, zero enonce neuf, zero ecart** sur un `enonce`, une
`bonneReponse`, un `distracteurs`, un `indice`, un `texteSupport` ou une `explication`.

**Ce paquet en porte 25, tous identiques a ce qu'ils etaient.**

### 2. La consigne devant les items de ce paquet

**Consigne servie, lue a l'ecran** : *"Situe le fait dans le temps, ou remets les faits dans l'ordre."* - 11 mots, contre les 28 de la consigne
fourre-tout qu'elle remplace.

**Exacte, avec un point lache que je nomme plutot que de le taire.** Vingt des vingt-cinq items
situent un fait dans le temps (*"A quelle periode cela correspond-il ?"*, *"En quel siecle cela se
passe-t-il ?"*, *"En quelle annee commence la Revolution francaise ?"*) ou ordonnent des faits
(*"Parmi ces trois faits, lequel a eu lieu le plus tot ?"*, *"Quel evenement a eu lieu juste apres
l'abolition des privileges ?"*). **La consigne les nomme tous les deux, et c'est juste.**

**Les cinq items *"Quelle grande periode vient juste apres X ?"* ordonnent des periodes, pas des
faits**, et la consigne dit *"les faits"*. **C'est un mot, pas un contresens** : un enfant de CM1
qui lit *"remets les faits dans l'ordre"* et voit trois grandes periodes fait exactement ce qu'on
attend de lui. **Je le note et je n'en fais pas une demande.**

### 3. Ce que j'ai verifie a l'ecran sur cette partie

- **Les dix manches se sont jouees de bout en bout**, aucun ecran casse, aucune repetition.
- **Les items *"Parmi ces trois faits..."* sont autonomes** : les trois propositions **portent leur
  date** (*"la prise de la Bastille, en 1789"*, *"la bataille d'Orleans menee par Jeanne d'Arc, en
  1429"*, *"Clovis devient roi des Francs, en 481"*). L'enfant compare trois nombres, ce qui **est**
  la competence de chronologie. **Rien a leur reprocher.**
- **Les indices** : exacts, a leur item. Celui du siecle de 1789 enseigne la methode (*"Compte les
  centaines d'annees. 1789 se situe entre 1701 et 1800, donc au XVIIIe siecle."*) - **c'est ce qu'un
  indice doit faire, et il donne la reponse apres l'avoir expliquee, ce qui est le bon ordre.**
- **`ADR-0023`** : 25 items pour 10 manches.
- **`HG-CM1-07` est aussi servie par *Nommer, en histoire***, ce que `theo-archi` avait nomme
  d'avance. **Rien a y opposer** : une ligne peut etre travaillee par deux taches, et c'est
  souhaitable.
- **L'ecran de fin de partie** : les deux nombres, justes. Verdict separe dans
  `PASSE-TRANSVERSE-s35-ecran-de-fin-de-partie.md`.

### 4. Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **Qu'une consigne corresponde aux items de son paquet** (`CONS-4`, `CONS-5`) | **aucun.** C'est moi, en jouant, texte devant items |
| **La fidelite d'une scission** | **aucun test ne compare un paquet a son ancetre.** Je l'ai fait a la main ce jour ; une scission prochaine repasserait sans rouge si un item etait perdu en route. Route a `theo-archi` comme symptome |
| **Les reserves `HG-R*` ouvertes** portees par les items de ce paquet | voir la fiche de l'ancetre - inchangees |

---

## Bloc de validation pedagogique du 2026-09-24 (s42) - les deux items neufs de `S42-HG-ROIS`

> Ce bloc n'annule ni ne modifie le verdict du 2026-09-11 ci-dessus, qui porte sur les 25 items
> anterieurs et qui reste lisible tel qu'il a ete prononce. Il porte **uniquement sur les deux items
> neufs** ajoutes par `S42-HG-ROIS` (26e et 27e position, ligne `HG-CM1-05`).

- **Verdict** : **VALIDE SOUS RESERVE**
- **Date** : 2026-09-24 (s42)
- **Auteur** : `nadia-prof`, **instance A** (`DEC-0098`, terrain 1)
- **Arbre** : `round-two` a `9817b4f0`
- **Audit joue** : **oui.** Jar reconstruit depuis cet arbre, lance sur le JDK `21.0.10` de la
  toolchain. **Une partie complete de 10 manches**, dans laquelle **l'item 27 est sorti a l'ecran**
  (*"...lequel a regne le dernier ?"*), joue en pressant les trois propositions dans l'ordre affiche
  pour lire l'indice servi apres chaque erreur ; puis des parties repetees pour voir l'item 26. Le
  YAML n'a servi qu'a verifier ensuite ce que l'ecran m'avait montre.
- **Mouvement de matrice** : `HG-CM1-05` peut passer a `couvert-valide`, **en tenant compte de
  `HG-R24`** (ecrite dans la fiche "Nommer, en histoire") sur ce que cette ligne apporte reellement.
  `camille-po` decide, pas moi.

### 1. `BIA-5` - ma garde, aucun automate ne la tient

`theo-archi` a signale que le test qui cite `BIA-5` dans sa Javadoc **ne verifie en realite que
l'appartenance de la bonne reponse a un ensemble de quatre noms de rois**. **Il ne me couvre pas.**
J'ai lu les cinq items neufs de `S42-HG-ROIS` un par un, ici et dans l'autre paquet. Le tableau
complet est dans `FICHE-bandit-manchot-histoire-geographie-nommer-en-histoire-cm1.md`, section 1 du
verdict du 2026-09-24 ; il n'est pas recopie ici, parce qu'un constat recopie pourrit a moitie.

**Pour les deux items d'ici, verifies a l'ecran** :

- Item 26 - *"Parmi ces quatre rois qui ont marque la construction du royaume, lequel a regne le
  premier ?"*, indice *"C'est le seul des quatre a avoir regne au Moyen Age."* - **un ordre de
  regne, aucune appreciation.**
- Item 27 - *"...lequel a regne le dernier ?"*, indice *"C'est le roi qui fait construire le chateau
  de Versailles."* - **un ordre de regne, aucune appreciation.**

**Aucun *bon roi*, aucun *grand roi*, aucun *mauvais roi*.** `BIA-5` tient.

### 2. Ce que ces deux items apportent, et c'est le meilleur du lot

**Ce sont les deux seuls items des cinq qui font ce que `HG-CM1-05` ajoute a `HG-CM1-06` :
ordonner les quatre rois les uns par rapport aux autres.** Les trois items de l'autre paquet
nomment un roi a partir d'un fait, ce que le paquet faisait deja sous `HG-CM1-06` (`HG-R24`). **Ici,
l'enfant range des regnes dans le temps, et c'est neuf.**

**Exactitude verifiee** : Louis IX (1226-1270) est bien le premier des quatre et **le seul a avoir
regne au Moyen Age**, ce que l'indice affirme ; Louis XIV (1643-1715) est bien le dernier. Francois
Ier (1515-1547) et Henri IV (1589-1610) s'intercalent dans cet ordre. **Aucune date fausse.**

**Un point que j'ai verifie plutot que de le supposer**, parce que `HG-R25` ci-dessous aurait pu le
gater : sur ces deux items, **la bonne reponse est la meme qu'on raisonne sur les trois rois
affiches ou sur les quatre du libelle**. Item 26, Louis IX est premier des trois affiches (Louis IX,
Francois Ier, Louis XIV) et premier des quatre. Item 27, Louis XIV est dernier des trois affiches
(Louis IX, Henri IV, Louis XIV) et dernier des quatre. **Aucun enfant n'est induit vers une mauvaise
reponse.** Ce n'est pas automatique : un item futur du meme patron pourrait ne pas avoir cette
propriete, et rien ne le verifierait.

**Le traitement de l'erreur** : sur l'item 27, j'ai presse *Louis IX*, puis *Henri IV*, puis *Louis
XIV*. A chaque erreur, *"Ce n'est pas ca. Essaie encore !"* suivi de l'indice. L'indice est exact et
il **separe bien les trois propositions** : Versailles ne designe ni Louis IX ni Henri IV.

### 3. La reserve opposable a ces deux items

**Une seule des trois reserves du 2026-09-24 les touche : `HG-R25`** - *"ces quatre rois"* annonce
devant trois propositions, sur les cinq enonces neufs. Elle est ecrite en entier dans
`FICHE-bandit-manchot-histoire-geographie-nommer-en-histoire-cm1.md`, section 3 du verdict du
2026-09-24, et elle **n'est pas bloquante**.

**Ce qui la rend plus voyante ici qu'ailleurs** : ce paquet porte deja la forme juste, et il la porte
huit fois. *"Parmi ces **trois** faits de l'histoire de France, lequel a eu lieu le plus tot ?"*,
*"Parmi ces **trois** evenements de la Revolution francaise, lequel a eu lieu le premier ?"* - trois
annonces, trois boutons, et j'ai valide cette forme le 2026-09-11 en la citant. **Les deux items
neufs sont les seuls de ce paquet a annoncer un nombre que l'ecran ne montre pas.** Ce n'est pas une
barre neuve de ma part : c'est la forme etablie de ce paquet-ci.

**`HG-R23` ne touche aucun de ces deux items** (elle porte sur l'item 47 de l'autre paquet).
**`HG-R24` les concerne indirectement** : ce sont eux qui empechent la ligne `HG-CM1-05` de se
reduire a trois relibellages, et c'est pour cela que je valide.

### 4. Ce dont je reste la seule garde sur ces deux items

| Propriete | Garde executable ? |
|---|---|
| **`BIA-5` - qu'aucun enonce ne porte un jugement de valeur sur un roi** | **aucun.** Le test qui cite `BIA-5` ne verifie que l'appartenance de la bonne reponse a quatre noms (`theo-archi`, s42). **Un enonce disant *"lequel fut le plus grand roi ?"* passerait vert.** C'est moi, item par item |
| **Que la bonne reponse d'un item d'ordre reste la bonne parmi les seules propositions affichees** | **aucun.** Verifie a la main sur les items 26 et 27 ; un item futur du meme patron ne serait pas couvert |
| **Qu'un enonce decrive l'ecran qu'il accompagne** (`HG-R25`) | **aucun.** Aucun test ne rapproche le mot *quatre* d'un enonce du nombre de boutons rendus |
| **Les reserves `HG-R*` anterieures** | inchangees, voir la fiche de l'ancetre et le bloc du 2026-09-11 |
