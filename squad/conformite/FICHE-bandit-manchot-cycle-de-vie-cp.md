# Fiche de conformite - bandit-manchot-cycle-de-vie-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-qlm-cycle-de-vie.yaml`.
- **Entree de catalogue** : `bandit-manchot-cycle-de-vie-cp`, titre servi *"Le cycle de la vie"*, `nombreDeManches: 10`.
- **Statut global s29** : **VALIDE SOUS RESERVE** (Done pedagogique prononce le **2026-09-06** sur l'increment de s29, story `S29-1`, commit `d6e554f`). **La ligne `QLM-CP-02` ferme** : `non couvert` -> **`couvert-valide`**. Deux reserves nommees, datees, non bloquantes : `CYV-R1` et `REV-1`.
- **Arbitrage prealable de `nadia-prof`** : `ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`, sections 4.1 et 5 ; `ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md`, section 2 (`FER-1`, `FER-2`, `FER-3`).
- **Instance d'audit** : Instance A (`PAR-7`, instance qui porte l'accumulation sur questionner le monde CP).
- **Derniere mise a jour** : 2026-09-06 (premier verdict de `nadia-prof` apres avoir joue, s29).

## Competence visee

`Identifier ce qui caractérise un être vivant : il naît, se nourrit, grandit, a des petits, meurt.`
(Questionner le monde, CP, Le vivant, la matiere et les objets - `QLM-CP-02`).

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `6b2edf1`, jar reconstruit et lance, **partie complete de dix
manches par appels HTTP reels**, **avec erreur volontaire sur six manches**, jusqu'a l'ecran de fin
(*"C'est fini !"*). **Et j'ai lu les onze items un par un**, distracteurs et indices compris,
puisqu'une partie de dix manches sur onze items en laisse toujours un que l'enfant ne voit pas.
C'est arrive : l'item *"Un caillou peut-il naitre ?"* n'a pas ete tire de ma partie.
**Ce n'est pas un audit degrade.**

**Done technique acquis, mesure par moi et non reconduit** : `bash outils/mvn-jdk21.sh -B clean
verify` sur le **reacteur complet** (racine, pas de `-pl`), **BUILD SUCCESS**. Comptage independant
des rapports XML de tous les modules : **169 rapports, 1259 tests, 0 echec, 0 erreur, 0 ignore**,
sur une machine virtuelle rapportant **21.0.10**.

*Note de methode, parce que je viens de la vivre : ma premiere mesure a rendu `BUILD FAILURE`, et la
cause etait mon propre jar en cours d'execution, qui empechait `maven-clean-plugin` d'effacer
`recreo.jar`. Un auditeur qui joue et qui mesure dans le meme arbre doit arreter l'application avant
de mesurer, sinon il impute au produit un echec qui est le sien. Je l'ecris pour que la prochaine
instance ne le redecouvre pas.*

### 1. `FER-1` - les cinq caracteres, chacun en bonne reponse

Le libelle enumere ; **la parenthese fait contrat** (precision de `FER-1` du 2026-09-05). Verifie
item par item :

| Caractere du libelle | Item qui le sert en **bonne reponse** |
|---|---|
| il nait | *"Un poussin sort de l'oeuf, que fait-il ?"* -> `il nait` |
| il se nourrit | *"Un chien mange sa gamelle tous les jours, que fait-il ?"* -> `il se nourrit` |
| il grandit | *"Un bebe devient peu a peu un enfant, que fait-il ?"* -> `il grandit` |
| il a des petits | *"Une chatte a des chatons, que fait-elle ?"* -> `elle a des petits` |
| il meurt | *"Une vieille plante se desseche et ne vit plus, que fait-elle ?"* -> `elle meurt` |

**Cinq sur cinq. `FER-1` tenue.**

### 2. `FER-2` - densite

**Onze items**, plancher a dix. **Tenue.**

### 3. `FER-3` - deux taches, et ce sont bien deux operations mentales

- **Tache A** - *reconnaitre le caractere decrit dans une situation* : **5 items**. L'enfant lit une
  scene et nomme ce qu'elle montre.
- **Tache B** - *dire si un etre donne possede ce caractere* : **6 items**. L'enfant part du
  caractere et juge un etre.

**Ce ne sont pas deux formulations d'une meme question** : A va de la scene au mot, B va du mot a
l'etre, et un enfant de six ans qui reussit l'une echoue souvent l'autre. La moins servie porte
**cinq** items, au-dessus du plancher de trois. **Tenue.**

### 4. Exactitude - onze items sur onze

**Aucune erreur.** Le seul item qui demande un arbitrage de fond est *"Une plante peut-elle avoir
des petits ?"* -> **oui**, indice *"Ses graines donneront de nouvelles plantes."* **Je le confirme,
et je dis pourquoi** : la ligne enumere cinq caracteres du **vivant**, une plante est vivante, elle
doit donc les posseder tous les cinq. C'est precisement l'item qui empeche l'enfant de reduire
*vivant* a *animal*, l'erreur la plus repandue de cet age. Il est **coherent avec ce que le produit
enseigne deja** sur `cp-qlm-vivant-non-vivant`, dont l'indice de l'arbre dit *"un arbre nait,
grandit et a des petits (ses graines)"*. **Verifie sur les deux paquets, pas suppose.**

Les six items de la tache B sont **justes dans les deux sens** : caillou, chaise et pierre en `non`
; enfant, plante et poisson en `oui`. Aucun distracteur absurde : une question fermee n'en a pas.

*Observation mineure, sans reserve* : *caillou* et *pierre* servent deux items distincts alors que
ce sont des quasi-synonymes pour un enfant de six ans. Le commentaire du paquet les compte comme
deux etres varies ; c'est une variete de facade. Sans consequence - les deux items sont justes et
les deux caracteres testes (naitre, mourir) sont differents.

### 5. Deux propositions sur six items - accepte, precedent tenu

Les six items de la tache B n'offrent que `oui` et `non`. Un item binaire se repond a 50 % au
hasard, et c'est une objection serieuse, que j'ai deja instruite sur le catalogue plutot que sur mon
impression (`cp-qlm-vivant-non-vivant`, `cp-nature-mot`, `cm1-homophones`, `cp-son-dans-mot`,
`cp-qlm-etats-eau`). **La forme de la question - *"X peut-il Y ?"* - n'admet pas trois reponses**, et
en fabriquer une troisieme serait pire. **Accepte.**

### 6. Lisibilite - mesuree

Les onze enonces vont de **5 a 10 mots**, mediane **7**. Aucun outlier. Le vocabulaire est celui du
quotidien d'un enfant de six ans - *poussin*, *gamelle*, *chatons*, *caillou*, *chaise*, *graines* -
**a une exception, qui fait la reserve `CYV-R1`**.

### 7. Traitement de l'erreur - joue, pas suppose

Sur une reponse fausse : *"Ce n'est pas ca. Essaie encore !"* **et l'indice apparait**. A la
troisieme tentative, la reponse est revelee et son bouton designe. **Un enfant bloque ne l'est
jamais plus de trois coups**, ce qui est ce que je demande. Les indices donnent le **critere** et
non le mot : *"Il a besoin de manger pour vivre."*, *"C'est le tout debut de sa vie."*, *"Ses
graines donneront de nouvelles plantes."* **C'est la bonne facon d'aider a cet age.**

### 8. `CYC-1` - **vacante aujourd'hui, et je le dis exactement**

`CYC-1` porte sur ce qu'un item de cycle 3 **deja servi** contredirait. **Aucun paquet de sciences et
technologie CM1 n'est servi par le produit** : la variante retenue pour s29 a laisse ce lot dehors.
`CYC-1` est donc **vacante** sur cette ligne, et non *tenue* - les deux mots ne disent pas la meme
chose et je refuse de faire passer l'un pour l'autre.

La verification que `lea-dev` a faite contre le **referentiel** de sciences CM1 est un bonus utile,
pas la clause. Je la lis et je la confirme : *naissance, croissance, reproduction, vieillissement,
mort* sont plus fins que le grain de CP sans le contredire, et le paquet a eu raison de ne pas
nommer le vieillissement, absent du libelle de CP. **`CYC-1` devra etre rejouee le jour ou la ligne
1 ou la ligne 9 des sciences CM1 sera servie**, et ce jour-la, ce sera sur du contenu, pas sur un
referentiel.

### 9. Reserves nouvelles, datees du 2026-09-06, non bloquantes

- **`CYV-R1` - un item est a la fois au-dessus du niveau et auto-repondant.**

  *"Une vieille plante se desseche et ne vit plus, que fait-elle ?"* -> `elle meurt`.

  **Deux defauts sur le meme item.** *Se dessecher* n'est pas un verbe de CP : un enfant qui decode
  bute dessus. Et surtout, *"ne vit plus"* **contient deja la reponse** : l'enfant apparie *ne vit
  plus* et *meurt* sans rien savoir du vivant. C'est la contrefacon que `CON-1` decrit - l'item ne
  mesure pas ce qu'il pretend mesurer.

  **Ce que je prescris, et je le fournis mot pour mot** (regime du mot pour mot, **0,25**) :

  > `enonce` : *"Une plante tres vieille perd ses feuilles et ne repousse plus, que fait-elle ?"*

  Il ne contient plus le mot de la reponse, il reste dans le lexique de CP, il decrit une scene
  observable comme les quatre autres items de la tache A, et l'indice servi (*"C'est la fin de sa
  vie."*) continue de porter le critere. **Bonne reponse et distracteurs inchanges.** L'orthographe,
  l'accentuation et la ponctuation de ce texte appartiennent au `relecteur-pedagogique`
  (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`).

  **Pourquoi ce n'est pas bloquant** : la reponse servie est **juste**, l'enfant n'apprend rien de
  faux, et les dix autres items suffisent tres largement au plancher de `FER-2`. Un item qui ne
  mesure pas n'est pas un item qui trompe, et je ne traite pas les deux de la meme facon.

  *Je prescris au lieu de nommer une piste, et c'est un changement de ma part.* A s28 j'ai ouvert
  `VIV-R2` en qualifiant mon remplacement de *"piste que je nomme sans la prescrire, parce que je ne
  l'ai pas jouee"* : la reserve est restee impayee un sprint entier, `marc-dev` ayant estime, avec
  raison, que l'implementer serait trancher a ma place. **Une reserve que je n'assortis pas d'un
  texte est une reserve que personne ne peut payer.**

- **`REV-1` - la phrase de revelation est agrammaticale sur les cinq items de la tache A.**

  Trouvee **en jouant**, invisible dans le fichier de contenu. Apres trois erreurs, l'ecran affiche :

  > *"C'est il se nourrit. Appuie sur il se nourrit."*

  **La cause n'est pas dans ce paquet** : elle est dans le cadre que la mecanique compose autour de
  la bonne reponse, qui ne se lit correctement que si celle-ci est un groupe nominal. **La propriete
  est de portee depot** et je la traite comme telle dans ma passe transverse `S29-3`
  (`PASSE-TRANSVERSE-s29-les-proprietes-de-portee-depot.md`, section 1). **Je decris le symptome ;
  la cause est technique et son traitement appartient a `theo-archi`.**

  Sur les six items de la tache B, la meme phrase donne *"C'est non. Appuie sur non."* - maladroit
  mais lisible. Ce sont les **cinq** items de la tache A qui sont fautifs.

  **Pourquoi ce n'est pas bloquant** : l'enfant qui arrive a cet ecran a le bon bouton **designe**
  et il le trouve - je l'ai fait. Il lit une phrase mal formee, ce qui est serieux pour un enfant qui
  apprend a lire, mais il n'apprend rien de faux et il n'est pas bloque.

### 10. Ce dont je reste la seule garde sur cette ligne

**Dit explicitement, pour que l'equipe ne croie pas couvert ce qui ne l'est pas.**
`PaquetCpQlmCycleDeVieTest` (6 tests) garde des chaines et des comptes que j'ai dictes ; il ne sait
pas qu'une plante a des petits. **Restent sans aucun garde-fou** : l'exactitude de chacun des onze
faits, la plausibilite des distracteurs, l'accessibilite du vocabulaire au CP, `FER-1` et `FER-3`
(qui sont des jugements sur une operation mentale), et **la phrase que la mecanique compose autour
d'une bonne reponse** - la propriete de `REV-1`, qu'aucun test du depot ne mord et qui a traverse
tous les sprints de contenu jusqu'ici.

### 11. Allocation d'identifiants faite ici (`PAR-5`)

Le prefixe `CYV` est **alloue par ce document** au paquet `cp-qlm-cycle-de-vie` ; il n'existait nulle
part dans le depot avant le 2026-09-06 (verifie par extraction sur `squad/`, `docs/`, `*.java`,
`*.yaml`). Le prefixe `REV` est alloue par la passe transverse `S29-3`. **Je l'ecris parce que
`PAR-5` demande qu'une allocation soit visible avant d'ecrire, et qu'aucun registre n'existe encore
pour le faire a ma place.**


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

## Verdict : **VALIDE** - `CYV-R1` levee

**J'ai joue** `bandit-manchot-cycle-de-vie-cp`, partie jouee volontairement mal jusqu'aux
revelations, **et j'ai relu les onze items**.

### `CYV-R1` - **LEVEE**

Texte livre : *"Une plante tres vieille perd ses feuilles et ne repousse plus, que fait-elle ?"*
**C'est mon texte, mot pour mot**, aux accents pres, qui appartiennent au `relecteur-pedagogique`.
Bonne reponse (`elle meurt`), distracteurs et indice (*"C'est la fin de sa vie."*) inchanges, comme
je l'avais exige.

**Les deux defauts sont payes, et je les reverifie separement** :

- *se dessecher*, qui n'est pas un verbe de CP, a disparu ;
- **l'enonce ne contient plus le mot de la reponse** : *"ne repousse plus"* decrit une scene
  observable sans dire *mourir*. L'enfant ne peut plus apparier deux formulations du meme mot.
  **L'item mesure a nouveau ce qu'il pretend mesurer**, et la contrefacon que `CON-1` decrit est
  supprimee.

Les cinq items de la tache A gardent la meme forme de scene observable, ce qui etait le motif pour
lequel j'avais choisi ce remplacement plutot qu'un autre.

### `REV-1` - **levee sur cet ecran**, par `REV-3`

L'autre reserve ouverte le 2026-09-06 sur cette fiche portait sur la phrase de revelation, *"C'est il
se nourrit. Appuie sur il se nourrit."* **Verifie a l'ecran aujourd'hui** : la revelation donne
desormais *"Ce n'est pas ca."*, puis *"Appuie sur la bonne reponse :"*, puis la reponse **seule dans
son element**. **La cause de portee depot est traitee, et son traitement est juge dans mes verdicts
de s30, section 8.**

### `CONS-2` - reserve nouvelle, datee du 2026-09-06, non bloquante

**Ce jeu porte la plus longue consigne de CP que l'enfant lit lui-meme** :

> *"Lis bien, puis dis ce que fait l'etre vivant, ou reponds par oui ou par non."* - **16 mots**

**Elle tient `CONS-1`** - elle nomme bien les deux taches du paquet, cinq items de scene et six items
binaires -, et je l'ai comptee comme tenue. **Ce que j'ouvre est son cout** : l'enfant la decode
seul, sur chacune des dix manches, avant l'enonce. Elle en faisait neuf avant la reecriture. Les
seules consignes de CP plus longues appartiennent a des paquets a voix, ou l'adulte lit.

> **`CONS-2`. Propriete a tenir** : une consigne de catalogue de CP que l'enfant lit lui-meme nomme
> ses taches **sans depasser douze mots** - chiffre pris sur les consignes de CP qui y parviennent
> deja, dont *"Lis bien, puis dis a quoi sert l'objet, ou nomme sa partie."* **Regime : rejeu borne
> sur deux entrees (celle-ci et `etats-eau-cp`), 0,25.**

**Pourquoi ce n'est pas bloquant** : la consigne est juste, elle nomme la tache, et l'enonce porte
tout ce qu'il faut. **`QLM-CP-02` reste `couvert-valide`, aucune ligne ne bouge.**
