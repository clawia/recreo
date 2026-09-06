# Fiche de conformite - bandit-manchot-fait-opinion-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-emc-fait-opinion.yaml`.
- **Entree de catalogue** : `bandit-manchot-fait-opinion-cm1`, titre servi *"Fait ou opinion"*,
  consigne servie *"Lis bien, puis dis si c'est un fait ou une opinion."*, `nombreDeManches: 10`.
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce le 2026-09-06** par
  `nadia-prof`, apres avoir joue la reprise `4dbf9b2`). **`EMC-CM1-11` ferme** : `non couvert` ->
  **`couvert-valide`**. Trois reserves nommees et datees, `FOP-R1` a `FOP-R3`, aucune bloquante.
- **Histoire des verdicts, aucun efface** : **2026-09-05, REFUSE** - la tache n'etait ecrite nulle
  part sur six items, et *"un mensonge"* melangeait deux axes en rendant deux items ambigus ; le
  bloc entier de ce refus, avec ses trois motifs, reste lisible ci-dessous. **2026-09-06, VALIDE
  SOUS RESERVE** apres reprise, second bloc en fin de fiche. **Un refus efface n'a aucune valeur de
  preuve.**
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`,
  section 4.4, et `squad/programme/enseignement-moral-et-civique/competences-cm1.md`.
- **Commits audites** : `ee3ef70` (`[LEA] s29: S29-5`) le 2026-09-05, puis `4dbf9b2` (`[LEA] s29:
  reprise des trois paquets d'EMC refuses par l'instance B`) le 2026-09-06.
- **Derniere mise a jour** : 2026-09-06 (**second verdict de `nadia-prof` apres avoir joue**, s29,
  instance B). **La consigne servie a change** : *"Lis bien, puis dis si c'est un fait ou une
  opinion."*

## Competence visee

`Distinguer un fait d'une opinion, et verifier l'origine d'une information avant de la partager.`
(`EMC-CM1-11`, enseignement moral et civique, CM1, *Construire une culture civique*).

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `ee3ef70`, jar reconstruit et relance, **partie complete de dix
manches par appels HTTP reels**, ecran de fin atteint. **Et j'ai lu les onze items.** Celui que le
tirage a laisse de cote est l'item 9 - *"Il a fait trois degres ce matin, d'apres le thermometre de
la classe ?"* - et c'est **le seul des six items de classement dont l'enonce nomme sa source**, donc
le seul que le motif de la section 3 n'atteint pas. **Un item de onze n'est jamais vu dans une
partie de dix manches, et c'est ce qui m'avait fait manquer un item a s27.**
**Ce n'est pas un audit degrade.**

**J'avais annonce d'avance que c'est ici que mon plafond casserait en premier** (arbitrage du
2026-09-05, section 6.3 : *"le lot d'EMC, quel que soit le niveau. C'est la premiere fermeture que
je perdrai"*). **Une prediction n'est pas un motif**, et je me suis relue sur ce point : les trois
motifs ci-dessous sont chacun verifiables par un tiers sur le fichier et sur l'ecran, sans passer
par moi.

### 1. Ce qui est juste dans ce paquet

- **`FER-1`** : les deux notions du libelle sont chacune en position de bonne reponse - *un fait*
  (items 1, 5, 9), *une opinion* (items 3, 7, 11). Tenue.
- **`FER-2`** : 11 items, au-dessus du plancher de dix. Tenue.
- **`EMC-1`, `EMC-8`** : aucun item n'interroge l'enfant sur lui-meme. Tenues.
- **`EMC-10`** (neuve du 2026-09-05, et c'est **la ligne qui l'a fait naitre**) : **tenue sans une
  faute**. Aucune personne reelle vivante, aucun parti, aucune organisation, aucun sujet
  d'actualite. Les exemples sont la meteo, un match de recreation, une rumeur de cour. **C'est
  exactement ce que la condition demandait, et c'etait le risque previsible de cette ligne.**
- **Le choix des exemples d'opinion est bon** : preferer un sport, trouver une histoire plus drole,
  preferer un moment de la journee. **Ce sont de vraies opinions**, sans piege moral.
- **`CYC-1`** : verifiee par moi au perimetre du depot et non du lot du jour. Aucun paquet servi de
  cycle 2 ne porte sur le fait, l'opinion ou la verification d'une information. **Aucune
  contradiction possible, et je le confirme comme mon jugement, pas comme une case cochee.**

### 2. **Premier motif de refus - la tache n'est pas ecrite, sur six items sur onze**

**Ce que l'enfant a sous les yeux, releve sur l'ecran et non deduit du fichier :**

> consigne : *"Lis bien la question, puis choisis la bonne reponse."*
> enonce : *"Le foot est le sport le plus interessant du monde ?"*
> boutons : *un mensonge* | *une opinion* | *un fait*

**Il n'y a pas de question.** L'enonce est une **phrase affirmative** a laquelle on a ajoute un
point d'interrogation. Lue telle quelle, c'est une question fermee dont la reponse serait *oui* ou
*non* - et les trois boutons ne repondent pas a cette question-la. **La tache reelle - *dis si cette
phrase est un fait ou une opinion* - n'est ecrite nulle part** : ni dans la consigne, qui est
generique, ni dans l'enonce. **L'enfant doit deviner ce qu'on lui demande en regardant les boutons.**

C'est le contraire exact de ce que j'ai loue sur la division posee le 2026-09-04 : *"la consigne de
catalogue est la meilleure formulation du sprint - 'Pose l'operation, puis choisis le bon quotient
et le bon reste' - elle nomme les deux choses que la ligne demande."* Ici la consigne ne nomme rien.

**Ce n'est pas un point de ponctuation** - la ponctuation appartient au `relecteur-pedagogique`
(`DEC-0002`, `DEC-0056`), et je ne me prononce pas dessus. **C'est un point de niveau et de fond :
la consigne n'enonce pas la tache.** C'est ma porte.

> **Ce qu'il faut, et c'est le moins cher des trois correctifs de cette fiche** : une consigne de
> catalogue propre a ce jeu, qui nomme la tache. Elle ne coute qu'une ligne de `catalogue.yaml`. Et
> l'enonce des six items de classement se pose comme une phrase **citee** que l'on qualifie, pas
> comme une question. **Je donne la propriete, pas la formulation.**

### 3. **Second motif de refus - *un mensonge* n'est pas une troisieme categorie, et il rend deux items ambigus**

**Mesure** : *"un mensonge"* est propose sur **6 items sur 6** parmi les items de classement, et il
n'est **jamais** la bonne reponse. Deux consequences, et la seconde est la grave.

**La consequence de forme** : des le deuxieme item, l'enfant sait qu'il ne faut jamais presser ce
bouton. Les six items deviennent des choix entre deux. **Ce n'est pas ce qui me fait refuser** -
c'est un affaiblissement, pas une erreur.

**La consequence de fond, et elle est une erreur de contenu.** *Fait* et *opinion* se distinguent
par la **nature** de l'enonce : un fait se verifie, une opinion depend de celui qui parle. *Vrai* et
*faux* est un **autre axe**. **Un mensonge est un enonce de nature factuelle qui se trouve etre
faux** - il est donc verifiable, donc *"un fait"* au sens que le paquet enseigne lui-meme, puisque
son propre indice donne ce critere : *"on peut verifier ce genre d'information en regardant dehors
ou en demandant a quelqu'un qui etait present"*. **En mettant les deux axes dans le meme jeu de
trois boutons, le paquet enseigne une trichotomie fausse.**

**Et cela produit un item a deux reponses defendables, ce qu'`EMC-5` refuse sans discussion :**

> *"Hugo a marque trois buts pendant le match de la recreation ?"* -> keye **un fait**, avec **un
> mensonge** en proposition.

**Rien dans l'enonce ne dit que c'est vrai.** Un eleve de CM1 a qui l'on vient d'apprendre a se
mefier de ce qu'on lui rapporte peut parfaitement repondre *"un mensonge"* - et le produit lui dira
qu'il a tort. Meme structure sur l'item 1 (*"il a plu toute la matinee"*). **Le produit sanctionne
une reponse defendable : c'est une erreur au point du retour, pas seulement une maladresse de
conception.**

> **Propriete a tenir** : les trois propositions d'un item de classement portent sur **un seul
> axe**. Si l'axe est *fait / opinion*, la troisieme proposition est de la meme famille - par
> exemple une categorie que le programme nomme aussi - et **elle est la bonne reponse sur certains
> items**. Une proposition qui n'est jamais juste sur onze items n'est pas un distracteur, c'est un
> decor. **Les valeurs sont a `lea-dev`.**

### 4. **Troisieme motif - les cinq items de verification se repondent sans la competence**

C'est le **catechisme civique** que j'ai nomme d'avance, le 2026-09-03, dans le referentiel de la
matiere : *"des items ou l'enfant coche la reponse gentille sans que rien n'ait ete travaille"*.

**Mesure, sur les cinq items pairs, la totalite :**

| Item | Ce que la question demande | Les deux distracteurs |
|---|---|---|
| 2 | *comment verifier* | *"...sans rien verifier"* / *"en le croyant parce qu'un seul camarade l'a dit"* |
| 4 | *comment verifier* | *"...sans rien verifier"* / *"en la croyant parce que Malo l'a dit une seule fois"* |
| 6 | *que faire avant d'en parler* | *"la repeter immediatement"* / *"...sans rien verifier"* |
| 8 | *comment savoir si c'est vrai* | *"...sans verifier"* / *"en imaginant une explication supplementaire"* |
| 10 | *comment verifier* | *"en croyant le mot sans le verifier"* / *"sans le relire"* |

**Sur cinq items sur cinq, la question demande comment verifier et au moins un distracteur declare
litteralement qu'il ne verifie pas.** La bonne reponse est, les cinq fois, *demander a un adulte de
l'ecole* ou *demander a plusieurs temoins*. **L'enfant gagne les cinq items en cherchant le bouton
qui contient le mot que la question contient, ou celui qui parle d'un adulte.** Il n'a rien
appris de la verification d'une information : il a appris a reconnaitre la proposition
raisonnable.

**Ce motif seul aurait ete une reserve lourde, pas un refus** - la tache existe, elle est juste, et
`FER-3` est formellement tenue. **Je le nomme comme troisieme motif parce qu'il porte sur la moitie
de la ligne**, celle qui dit *"verifier l'origine d'une information"*, et parce qu'il est de la
meme famille que la regularite structurelle que je refuse le meme jour sur la division posee : **une
regle de fabrication uniforme qui rend la reponse trouvable sans la competence.**

**Une nuance que je dois a l'honnetete du dossier** : cette regularite-ci laisse quand meme
l'enfant lire la situation, et l'idee *"on verifie aupres de quelqu'un qui sait"* est juste. Elle
n'est pas fausse ; elle est gratuite.

**Et une seconde nuance, sur la moitie du libelle** : la ligne dit *"verifier **l'origine** d'une
information"*. Quatre des cinq items demandent comment savoir **si c'est vrai**, ce qui n'est pas
tout a fait la meme chose. Seul l'item 4 (plusieurs temoins) travaille reellement l'origine. **Ce
n'est pas un motif de refus a lui seul, c'est ce que la reprise doit corriger en meme temps que le
reste.**

### 5. Ce que je n'oppose pas

- **La longueur des enonces** : mediane 14 mots, maximum 26. **C'est du CM1, c'est acceptable**, et
  je ne l'oppose pas. Je le mesure pour qu'on ne croie pas que je refuse sur la longueur.
- **`HIS-3`** : le paquet declare qu'aucun fait institutionnel n'est en jeu et que les scenarios
  sont fictifs. **C'est exact**, je l'ai verifie item par item. Rien a sourcer ici.

### 6. Ce dont je reste la seule garde sur ce paquet

| Propriete | Qui la tient | Automatisable ? |
|---|---|---|
| **Que la consigne enonce la tache** | **moi seule.** Aucun test ne relie une consigne de catalogue a la forme des enonces de son paquet | non |
| **`EMC-5`** - aucun item a deux reponses defendables | **moi seule** | non |
| **Qu'une proposition ne soit pas un decor** (jamais juste sur tout un paquet) | **moi**, mais **c'est comptable** : un distracteur qui n'est jamais bonne reponse se compte | **oui** |
| **Qu'un jeu de trois propositions ne melange pas deux axes** | **moi seule** | non |
| **Le catechisme civique** - que la bonne reponse ne soit pas reconnaissable a sa seule vertu | **moi seule** | non |

### 7. Ce que ce refus ne dit pas

- **Il ne dit pas que la ligne est mal choisie.** Je l'ai nommee *"la ligne la plus rentable de tout
  le perimetre de s29"* et je le maintiens : la forme est bonne, les exemples sont bien choisis,
  `EMC-10` est tenue.
- **Il ne demande pas un lot neuf.** Une consigne de catalogue, une troisieme proposition repensee
  sur six items, et cinq distracteurs de verification a reecrire. **C'est une reprise de textes.**
- **Il ne bloque pas le sprint suivant.** L'increment retourne a `camille-po`, qui en fait du
  backlog.

---

# Second audit - reaudit de la reprise (s29, 2026-09-06) - **VALIDE SOUS RESERVE**

- **Commit audite** : `4dbf9b2` (`[LEA] s29: reprise des trois paquets d'EMC refuses par
  l'instance B`), arbre `round-two` a `4dbf9b2`. La consigne de catalogue a change dans le meme
  commit : *"Lis bien, puis dis si c'est un fait ou une opinion."*
- **Verdict** : **VALIDE SOUS RESERVE**. **Done pedagogique prononce le 2026-09-06** par
  `nadia-prof`, apres avoir joue. **`EMC-CM1-11` ferme** : `non couvert` -> **`couvert-valide`**.
  Trois reserves nommees et datees, `FOP-R1` a `FOP-R3`, aucune bloquante.
- **Le refus du 2026-09-05 n'est pas efface** : son bloc entier reste au-dessus, avec ses trois
  motifs. **Il est leve sur les deux motifs qui l'avaient produit** ; le troisieme, que j'avais
  moi-meme qualifie de *"reserve lourde, pas un refus"*, est **a moitie paye** et devient `FOP-R2`.
- **Audite par** : `nadia-prof`, **instance B** de la parallelisation.

## Conditions de l'audit

**J'ai joue.** Jar reconstruit sur `4dbf9b2` et relance, **partie complete de dix manches par
appels HTTP reels**, en pressant d'abord les mauvaises propositions sur chaque manche pour lire tous
les indices. **Et j'ai lu les onze items** : celui que le tirage a laisse de cote est **l'item 9**
(*"Il a fait trois degres ce matin, d'apres le thermometre de la classe."*), **le meme qu'au
premier audit**, et c'est aussi celui que mon motif de refus n'atteignait pas. Je l'ai lu et
verifie : *un fait*, contre *une opinion*, exact, et son indice donne le critere (*"un thermometre
donne une mesure que l'on peut verifier"*). **Ce n'est pas un audit degrade.**

## 1. Premier motif - la tache n'etait ecrite nulle part - **paye**

**Ce que l'enfant a maintenant sous les yeux, releve a l'ecran :**

> consigne : *"Lis bien, puis dis si c'est un fait ou une opinion."*
> enonce : *"Le foot est le sport le plus interessant du monde." Fait ou opinion ?*
> boutons : *une opinion* | *un fait*

**La tache est ecrite deux fois : dans l'enonce de chacun des six items de classement, et dans la
consigne du catalogue.** La phrase a qualifier est **citee**, elle n'est plus une affirmation
deguisee en question. **C'est exactement la propriete que j'avais donnee**, et elle est tenue sur
les six. **Motif leve.**

## 2. Second motif - *"un mensonge"* melangeait deux axes et rendait deux items ambigus - **paye**

La troisieme proposition est **retiree**, et non remplacee par une categorie inventee.
**J'approuve ce choix et je le dis explicitement, parce qu'il repond a mon motif au lieu de le
contourner** : le referentiel ne nomme que **deux** categories (`EMC-CM1-11`, *"distinguer un fait
d'une opinion"*), et fabriquer une troisieme pour remplir un bouton aurait ete la faute meme que je
refusais. **Ma phrase du 2026-09-05 - *"une proposition qui n'est jamais juste sur onze items n'est
pas un distracteur, c'est un decor"* - est payee par le retrait du decor.**

**Et `EMC-5` est restauree sur les deux items que j'avais nommes** : *"Il a plu toute la matinee"*
et *"Hugo a marque trois buts"* sont des **faits** au sens de la ligne - des enonces verifiables -
que l'on sache ou non s'ils sont vrais. **Sans *"un mensonge"* dans les propositions, il n'y a plus
de seconde reponse defendable.** J'ai relu les six items de classement un par un : aucun n'a deux
reponses defendables. **Motif leve.**

## 3. **Deux propositions au lieu de trois : ma reponse, sur cette ligne**

**Cela me convient sur cette ligne, et voici mes quatre raisons, dans l'ordre de leur poids.**

1. **La distinction est binaire dans le referentiel lui-meme.** `EMC-CM1-11` nomme un fait et une
   opinion, rien d'autre. Une troisieme categorie devrait etre **inventee**, et c'est precisement ce
   que j'ai refuse le 2026-09-05. **Je ne peux pas refuser l'invention d'un axe et exiger ensuite un
   troisieme bouton qui l'obligerait.**
2. **Le depot sert deja des items a deux propositions, et j'en ai valide.** Sept paquets en portent
   hors ceux du jour (`cp-qlm-vivant-non-vivant`, `cp-qlm-etats-eau`, `cp-qlm-cycle-de-vie`,
   `cp-son-dans-mot`, `cp-nature-mot`, `cm1-homophones`, `cm1-accords-groupe-nominal`), et
   `cp-qlm-etats-eau` comme `cp-qlm-vivant-non-vivant` ont recu mon Done. **Refuser ici serait un
   poteau deplace contre mes propres verdicts.** La mecanique ne pose qu'un plafond de trois
   propositions, aucun plancher.
3. **Le paquet n'est pas binaire de bout en bout** : six items de classement a deux propositions,
   cinq items de verification a trois. Une partie de dix manches melange les deux, **et je l'ai
   verifie a l'ecran** - ma partie a tire six items a deux propositions et quatre a trois.
4. **Le prix de la chance sur deux est rendu par l'indice.** La mecanique garde l'enfant sur l'item
   apres une erreur. Sur un item binaire, la seconde reponse est connue par elimination : **ce qui
   se joue alors n'est plus le score, c'est ce que l'indice enseigne.** Sur les six items de
   classement, l'indice donne **le critere** et jamais la categorie : *"on peut verifier ce genre
   d'information en demandant a ceux qui ont joue"*, *"chacun peut preferer un sport different, sans
   que personne n'ait tort"*, *"preferer un moment de la journee a un autre est affaire de gout"*.
   **L'enfant qui se trompe apprend la regle, pas la reponse.** Je l'ai lu sur les six, a l'ecran.

> **La condition que j'attache, et que je surveillerai desormais partout ou un item est binaire** :
> **sur un item a deux propositions, l'indice porte le critere, jamais le nom de la categorie.**
> Apres une erreur, l'enfant connait deja la reponse ; l'indice est alors la seule chose qui reste
> pour enseigner. **Tenue ici sur les six.** Elle ne l'est pas sur `cm1-emc-droit-devoir`, et je
> l'ecris dans sa fiche le meme jour.

## 4. Troisieme motif - le catechisme civique - **paye a moitie**, devient `FOP-R2`

**Ce que le paquet declare de lui-meme** : *"Les dix distracteurs sont reecrits pour dire une
methode de verification reellement tentee mais insuffisante (une seule source, un indice trompeur),
jamais l'absence de verification en toutes lettres."*

**Ce que j'ai compte, un par un, sur les dix :**

| Distracteur | Ce qu'il est |
|---|---|
| *"en la croyant parce qu'un seul camarade l'a dit"* (item 2) | **une methode insuffisante** - une seule source |
| *"en la croyant parce que Malo l'a dit une seule fois"* (item 4) | **une methode insuffisante** |
| *"en en discutant avec un seul camarade qui a entendu la meme rumeur"* (item 6) | **une methode insuffisante**, et la meilleure des dix - deux personnes qui ont entendu la meme rumeur ne font pas deux sources |
| *"en le croyant parce qu'il est ecrit en grosses lettres"* (item 10) | **un indice trompeur** |
| *"en demandant a un seul camarade qui n'a pas lu le mot lui-meme"* (item 10) | **une methode insuffisante** |
| *"en la repetant a toute la classe parce que ca semble amusant a raconter"* (item 2) | **repeter sans verifier** |
| *"en la repetant parce que le score est impressionnant"* (item 4) | **repeter sans verifier** |
| *"en la repetant immediatement a tout le monde"* (item 6) | **repeter sans verifier** - reecrit en la forme *"en + gerondif"*, identique sur le fond a la version refusee |
| *"en le repetant a tout le monde parce que ca lui semble inquietant"* (item 8) | **repeter sans verifier** |
| *"en imaginant une explication supplementaire"* (item 8) | **ni verification ni repetition - inchange, mot pour mot** |

**Cinq sur dix sont ce que le paquet annonce ; cinq ne le sont pas.** *J'avais d'abord ecrit que
deux d'entre eux etaient inchanges ; je suis allee verifier avant de m'appuyer dessus, et c'est
inexact - un seul l'est mot pour mot (item 8), l'autre a ete reecrit dans sa forme sans changer de
fond (item 6). Je corrige mon propre constat plutot que de le laisser servir de motif.*
Repeter une information a toute la classe n'est pas *"une methode de verification reellement tentee
mais insuffisante"* : c'est le contraire d'une verification, dit sans les mots que j'avais cites.
**Le catechisme est reduit, il n'est pas supprime** : les cinq items gardent au moins une
proposition reconnaissable a sa seule deraison.

**Pourquoi cela ne refait pas un refus** : j'ai ecrit le 2026-09-05, en toutes lettres, que **ce
motif seul aurait ete une reserve lourde et pas un refus**. Il l'etait alors, il l'est encore, et il
est desormais **a moitie paye**. Le reprendre comme motif de refus aujourd'hui serait un poteau
deplace.

> **`FOP-R2`, ouverte le 2026-09-06, non bloquante.** Les cinq distracteurs du type *"repeter sans
> verifier"* deviennent, comme les cinq autres, **une methode de verification reellement tentee et
> insuffisante** : une source unique, une source qui n'a pas vu, un indice de forme pris pour une
> preuve. **Et la declaration du paquet est corrigee dans le meme geste** - elle affirme aujourd'hui
> que les dix le sont, ils sont cinq. **Une declaration de conformite fausse dans un fichier commite
> sera relue comme une preuve** ; c'est la meme famille qu'un chiffre sans methode, et j'avais deja
> releve cet ecart-la sur le paquet jumeau de CP le 2026-09-05.

## 5. **`FOP-R1`** - la consigne neuve decrit une tache que cinq items sur onze ne demandent pas

**Defaut cree par la correction, et c'est ce que je devais chercher.** La consigne de catalogue est
desormais *"Lis bien, puis dis si c'est un fait ou une opinion."* **Elle est servie sur les onze
manches**, y compris sur les cinq items de verification d'origine, ou la tache est tout autre :
*"Comment peut-elle verifier d'ou vient cette information avant de la repeter ?"*. **Je l'ai vu a
l'ecran sur cinq manches de ma partie.**

**Ce n'est pas grave, et je dis pourquoi** : sur ces cinq items, **l'enonce porte lui-meme sa
question**, entiere et explicite ; l'enfant lit l'enonce. La consigne est fausse pour eux, elle ne
les rend pas incomprehensibles.

> **`FOP-R1`, ouverte le 2026-09-06, non bloquante. Propriete a tenir** : la consigne d'un jeu ne
> decrit pas une tache que la moitie de ses items ne demandent pas. Deux issues valent : une
> consigne qui nomme **les deux** taches, ou le retour a une consigne generique **maintenant que
> chaque enonce nomme la sienne** - ce qui n'etait pas le cas le 2026-09-05 et qui l'est
> aujourd'hui. **Le choix est a `lea-dev` ; je donne la propriete.**

## 6. **`FOP-R3`** - sur un item, la question demande l'origine et la reponse donne la verite

**La nuance que j'avais posee le 2026-09-05 est payee sur les enonces** : les cinq items de
verification nomment desormais **l'origine** (*"d'ou vient cette information"*, *"l'origine de cette
information"*, *"l'origine de cette rumeur"*, *"l'origine de ce mot"*), la ou quatre d'entre eux
demandaient auparavant si c'etait vrai. C'est la moitie du libelle que je disais mal servie, et elle
est mieux servie.

**Mais la bonne reponse n'a pas suivi partout.** Sur l'item 8 - *"Aya a entendu un camarade dire que
sa maitresse etait malade. Comment peut-elle savoir d'ou vient cette information ?"* - la bonne
reponse est *"en demandant directement a un adulte de l'ecole"*. **On n'apprend pas d'ou vient une
information en demandant a quelqu'un d'autre si elle est vraie** : l'origine, ici, c'est le
camarade. **La reponse est bonne pour la question *est-ce vrai*, pas pour la question posee.**

**Je ne l'etends pas aux quatre autres, et je le dis pour que la reprise ne corrige pas trop** : sur
l'item 6 (l'ecole fermera demain) et l'item 10 (le mot affiche dans la cour), l'adulte de l'ecole
**est** la source legitime de l'information, donc y aller **est** remonter a l'origine ; sur
l'item 2 (la meteo) et l'item 4 (le score du match), consulter plusieurs previsions ou plusieurs
temoins qui ont vu le match **est** un travail de source. **Quatre sur cinq tiennent. Seul l'item 8
ne tient pas.**

> **`FOP-R3`, ouverte le 2026-09-06, non bloquante** : sur l'item 8, la question et la bonne reponse
> se rejoignent - soit la question redevient *"comment savoir si c'est vrai"*, soit la bonne reponse
> remonte au camarade qui a parle. **A `lea-dev`.**

## 7. Ce que j'ai verifie pour m'assurer que la reprise n'a rien casse

- **`FER-1`** : *un fait* (items 1, 5, 9), *une opinion* (items 3, 7, 11). Tenue.
- **`FER-2`** : 11 items. **`FER-3`** : deux taches distinctes, six et cinq items. Tenues.
- **`EMC-1`, `EMC-8`, `EMC-10`** : tenues, verifiees item par item. `EMC-10` reste sans une faute -
  aucune personne reelle, aucun parti, aucune organisation, aucun sujet d'actualite.
- **`EMC-5`** : plus aucun item a deux reponses defendables (section 2).
- **Exactitude des six classements** : trois faits (une pluie observee, des buts marques, une mesure
  de thermometre - tous verifiables) et trois opinions (le sport le plus interessant, une histoire
  plus drole, des recreations plus agreables - toutes affaire de gout). **Six sur six justes**, et
  les trois opinions restent sans piege moral.
- **Longueur des enonces**, mesuree par moi : `16, 17, 17, 18, 18, 19, 19, 21, 23, 24, 28` -
  **mediane 19, maximum 28**, contre mediane 14 et maximum 26 avant la reprise. **Je ne l'oppose
  pas** : j'ai ecrit le 2026-09-05 que la longueur est acceptable au CM1 sur ce paquet, et je ne
  fais pas de cette phrase le contraire aujourd'hui. La hausse est le prix des guillemets de
  citation et du mot *origine*, deux choses que j'ai moi-meme demandees. **Je note l'item 4 a
  28 mots** - *"par un score enorme"* n'y sert rien - **comme point a raboter dans la meme reprise
  que les trois reserves, sans en faire une quatrieme.**
- **Chemin d'erreur** : l'enfant reste sur l'item, l'indice arrive, il n'est jamais bloque. Verifie
  sur dix manches.

## 8. Ce dont je reste la seule garde sur ce paquet

| Propriete | Qui la tient | Automatisable ? |
|---|---|---|
| **Que la consigne du jeu decrive les taches que ses items demandent** (`FOP-R1`) | **moi seule.** Aucun test ne relie une consigne de catalogue a la forme des enonces de son paquet | non |
| **`EMC-5`** - aucun item a deux reponses defendables | **moi seule** | non |
| **Que la bonne reponse reponde a la question posee** (`FOP-R3`) | **moi seule** | non |
| **Le catechisme civique** (`FOP-R2`) - que la bonne reponse ne soit pas reconnaissable a sa seule vertu | **moi seule** | non |
| **Que l'indice d'un item binaire porte le critere et non la categorie** | **moi seule**, condition posee le 2026-09-06, section 3 | non |
| **Qu'une declaration de conformite ecrite dans le paquet soit vraie** | **moi seule** | non |
