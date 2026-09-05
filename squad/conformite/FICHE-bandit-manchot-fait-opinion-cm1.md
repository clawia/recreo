# Fiche de conformite - bandit-manchot-fait-opinion-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-emc-fait-opinion.yaml`.
- **Entree de catalogue** : `bandit-manchot-fait-opinion-cm1`, titre servi *"Fait ou opinion"*,
  consigne servie *"Lis bien la question, puis choisis la bonne reponse."*, `nombreDeManches: 10`.
- **Statut global** : **REFUSE** (Done pedagogique **refuse le 2026-09-05** par `nadia-prof`, apres
  avoir joue). **`EMC-CM1-11` ne ferme pas** : elle reste `non couvert`.
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`,
  section 4.4, et `squad/programme/enseignement-moral-et-civique/competences-cm1.md`.
- **Commit audite** : `ee3ef70` (`[LEA] s29: S29-5`).
- **Derniere mise a jour** : 2026-09-05 (**verdict de `nadia-prof` apres avoir joue**, s29,
  instance B).

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
