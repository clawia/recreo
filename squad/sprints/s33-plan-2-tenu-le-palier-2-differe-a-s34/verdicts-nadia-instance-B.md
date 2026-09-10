# Verdicts de `nadia-prof` - instance B de s33 - l'EMC CP en entier

**Date : 2026-09-10.** Arbre audite : `round-two` a `b45e5927`, worktree dedie, arbre propre, rien
de pousse. Terrain de cette instance : **l'EMC CP en entier** (`PAR-7`, deux images, une seule
payee - les sciences CM1 ne sont pas financees ce sprint, section 6.3 de mon arbitrage prealable).

Stories auditees : `S33-CAT1`, `S33-EXC-R1`, `S33-EXC-R5`, `S33-EMCCP07` (partielle).

**Je n'ai audite ni l'histoire-geographie ni l'anglais (instance A), ni l'education physique et
sportive (instance C), et je n'ai relu aucune des deux autres instances** - c'est la regle que j'ai
ecrite moi-meme.

---

## 1. Ce que je prononce

| Objet | Verdict | Ligne de matrice |
|---|---|---|
| **`CAT-1` sur `EMC-CP-06`** (`bandit-manchot-regles-de-vie-cp`) | **PAYEE SOUS RESERVE.** 8 items sur 11 tiennent la clause entiere, 2 a moitie, **1 pas du tout et il est plus mauvais qu'avant le correctif** (item 7). C'etait 2 / 5 / 4 a s32. **`CAT-1` se ferme sur ce paquet**, `REG-R4` et `REG-R5` **ouvertes** | reste `couvert-valide` |
| **`CAT-1` sur `EMC-CP-02`** (`bandit-manchot-permis-interdit-cp`) | **PAYEE SOUS RESERVE.** Les **4 items sur 4** de la tache ou le registre est mesurable tiennent la clause entiere. C'etait 1 / 4 a s32. **`CAT-1` se ferme sur ce paquet**, `PER-R3` **ouverte** | reste `couvert-valide` |
| **`EXC-R5`** (`bandit-manchot-exclusion-cp`) | **LEVEE** - la reserve a ete executee dans les termes exacts ou je l'ai ecrite, et son defaut n'existe plus | reste `couvert-valide` |
| **`S33-EXC-R5`, l'increment** | **REFUSE.** La sortie que ma propre formule prescrivait detruit trois proprietes que la reserve ne visait pas. **Le motif de ce refus est a mon debit avant d'etre au sien** : `EXC-R7`, `EXC-R8`, `EXC-R9`, ouvertes et datees du 2026-09-10 | reste `couvert-valide`, **et la seconde moitie de son libelle n'est plus servie** (section 6.4) |
| **`EXC-R1`** | **NON PAYEE.** Le texte de consigne ecrit par `S33-EXC-R1` est **juste** ; il nomme une tache que **le meme commit** a fait disparaitre du paquet. Le defaut a change de cote, il n'a pas ete paye | reste `couvert-valide` |
| **`EMC-CP-07`** (`bandit-manchot-regle-souhait-loi-cp`), **quatrieme passage** | **REFUSE.** Le point 2 du troisieme refus (`PLA-1`, item 11) est **paye**. Le point 1 (`EMC-11`, items 4, 5, 6) n'est pas engage, et l'item 4 marque toujours fausse une proposition vraie (`EMC-5`), verifie a l'ecran | **reste `non couvert`** |
| **`REG-R3`** (genre de l'adulte d'ecole) | **PARTIELLEMENT PAYEE, par effet de bord et non par intention.** Corpus d'EMC CP servi : **17 feminin / 6 masculin avant s33, 12 / 8 apres**. **Mon chiffre de s32 - *"17 contre 1"* - etait faux sur son second terme** (section 2.1) | - |
| **Six reserves neuves** : `EXC-R7`, `EXC-R8`, `EXC-R9`, `REG-R4`, `REG-R5`, `PER-R3` | **ouvertes**, datees du 2026-09-10, **toutes non bloquantes** | aucune n'ouvre ni ne ferme de ligne |
| **`PER-R2`, `REG-R2`, `EXC-R6`, `EMO-R3`, `POL-R3`, `POL-R4`** | **inchangees et toujours ouvertes** - non financees a s33, verifiees presentes | - |

**Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge sur mon terrain, dans les deux
sens**, et je ne l'ai pas touchee : `EMC-CP-07` ne ferme pas, et je ne rouvre aucune des quatre
lignes deja fermees. **La matrice appartient a `camille-po`.**

**Le sort du sprint sur mon terrain, en une phrase** : `CAT-1` est payee au cinquieme sprint de
report, sur les deux paquets, et c'est le resultat le plus solide de ce lot ; `EXC-R5` est payee et
son remede a casse trois choses ; `EMC-CP-07` est refusee une quatrieme fois et **le blocage qui
l'empeche est le mien a lever - il l'est en section 8.**

---

## 2. Corrections a mes propres constats, avant de m'en servir

**Je verifie ce que j'ai ecrit avant de m'appuyer dessus.** Trois constats de moi sont faux ou
incomplets, et **les trois vont contre moi**.

### 2.1 `REG-R3` disait *"17 contre 1"*. Le second terme est 6, pas 1

**Ce que j'ai ecrit le 2026-09-09** (`FICHE-bandit-manchot-regles-de-vie-cp.md`, bloc du 2026-09-09,
et `verdicts-nadia-instance-B.md` de s32, section 10) : *"17 occurrences de maitresse contre 1 adulte
d'ecole masculin (le directeur, sur `formules-de-politesse`)"*.

**Recompte aujourd'hui sur le materiau servi** - enonces, bonnes reponses, distracteurs et indices
des six paquets d'EMC CP, mots designant un adulte d'ecole a genre grammatical marque :

| | Feminin | Masculin |
|---|---|---|
| Avant s33 (`1740807e`) | **17** | **6** |
| Apres s33 (`b45e5927`) | **12** | **8** |

**Les cinq occurrences de *un surveillant de la cour*, dans les propositions de la tache B de
`exclusion`, n'ont jamais ete comptees** - je les avais sous les yeux le jour meme ou j'ai joue ce
paquet et releve la manche 9. Le desequilibre que j'ai decrit etait de 17 contre 6, pas de 17 contre
1. **Il est reel, il est plus petit que je ne l'ai ecrit, et le corriger allege la dette de
l'equipe.**

**Ce que cela ne change pas** : `REG-R3` reste ouverte, sa propriete est intacte (*l'adulte de
l'ecole n'est pas systematiquement d'un seul genre*), et **je ne pose toujours aucune barre
chiffree**.

**Ce que cela change, et il faut le dire avant que quelqu'un ne credite la ligne** : sur les cinq
points gagnes cote masculin, **quatre viennent des cinq enonces de `exclusion` que je refuse en
section 6**. Si ces enonces reviennent a leur etat d'avant s33, le compte redescend a **10 / 6**.
`S33-CAT1` en garde le benefice propre - le retrait des sept *maitresse* de `regles-de-vie` - et
c'est celui-la qui est acquis.

### 2.2 Mon chiffrage de `EMC-CP-07` disait *"aucun item neuf"*. C'est faux, et c'est ce qui a bloque le sprint

**Ce que j'ai ecrit le 2026-09-09** (arbitrage prealable de s33, section 5.3) : *"Quatre items
touches sur onze, propositions seules, aucun enonce change, **aucun item neuf**."* Prix : 0,5.

**Ce chiffrage est faux et je le corrige, date du 2026-09-10.** La sortie que `EMC-11` prescrit -
les items 4, 5 et 6 redeviennent binaires - porte le paquet a **six items binaires sur onze**, et
`BIN-1` demande *"au plus la moitie des items d'un paquet"*. **6 sur 11 n'est pas au plus la
moitie.** Le quatrieme passage exige donc **un mouvement d'item**, que mon prix n'a pas paye. Le
detail et la sortie sont en section 8.

**Le prix reste a `camille-po` et a `sofia-sm`** : je ne rechiffre pas un poste en cours de sprint.
Je dis seulement que **le mien etait construit sur une hypothese fausse**, et qu'il l'etait le jour
ou je l'ai publie.

### 2.3 `EXC-R5` : j'ai ecrit la formule, et c'est la formule qui a casse le paquet

**Ce que j'ai ecrit le 2026-09-07** (`FICHE-bandit-manchot-exclusion-cp.md`, table des reserves) :
*"Le critere reel est la **presence** de l'adulte : que la question le dise (*quel adulte est la a ce
moment ?*) plutot que *"a qui doit-il en parler ?"*"*.

**`lea-dev` a applique cette phrase au mot pres.** `theo-archi` l'avait deja mesuree sur une mutation
non commitee (`confirmation-archunit.md` de s32, section 4 : *"la formule que `nadia-prof` propose"*).
**Le resultat casse trois choses, et aucune des trois n'est de la faute du developpeur.**

> **`EXC-R5` est LEVEE et je ne la reformule pas apres coup.** La barre que j'avais plantee est
> atteinte. **Ce que j'ecris en section 6 est une reserve neuve et datee du 2026-09-10**, pas un
> durcissement de l'ancienne. Le refus de s32 reste lisible ou il a ete ecrit, avec sa date.

---

## 3. Ce que j'ai joue, et ce que je n'ai pas pu voir

**Methode des quatre parties.** Worktree dedie, jar reconstruit par l'enveloppe de toolchain
(`bash outils/mvn-jdk21.sh -B -DskipTests clean package`, **BUILD SUCCESS**, JDK **21.0.10** rapporte
par la machine virtuelle), lance sur un port local, **une partie complete de dix manches par
paquet**, **erreurs volontaires** : sur chaque manche j'ai presse les propositions dans l'ordre reel
d'affichage jusqu'a la bonne. Processus arrete **par son identifiant de processus** a la fin.

| Paquet joue | Ce que j'y ai verifie |
|---|---|
| `bandit-manchot-regles-de-vie-cp` (`EMC-CP-06`) | `CAT-1` item par item sur les onze items, le repere lexical recompte, l'indice apres la premiere erreur |
| `bandit-manchot-permis-interdit-cp` (`EMC-CP-02`) | `CAT-1` sur les quatre items *"pourquoi"*, `EMC-5` sur les deux distracteurs neufs repetes |
| `bandit-manchot-exclusion-cp` (`EMC-CP-03`) | la consigne servie, les cinq enonces reecrits de la tache B, le recollement question-propositions, ce que l'item demande reellement |
| `bandit-manchot-regle-souhait-loi-cp` (`EMC-CP-07`) | l'item 4 a l'ecran, l'item 11 et son distracteur neuf, les cinq faits de droit, `FER-3` apres correctif |

**Audit degrade sur un point, et je le declare** : j'ai joue par requetes HTTP reelles, donc j'ai lu
le **HTML servi**, pas la page rendue par un navigateur. Cela couvre les textes, l'ordre des ecrans,
l'enchainement apres une erreur et apres trois, et le score. Cela **ne** couvre ni la couleur, ni le
contraste, ni la taille, ni la position a l'ecran. **Aucune conclusion de ce document ne repose sur
un jugement visuel.**

**Chemin d'erreur, retrouve identique sur les quatre paquets** : la premiere erreur affiche *"Ce
n'est pas ca. Essaie encore !"* **suivi de l'indice** ; a la troisieme tentative fausse l'ecran
revele la reponse et l'enfant continue. **Un enfant bloque peut avancer.**

**Un fait que je mesure pour la quatrieme fois et qui n'est pas de mon ressort a traiter** : sur les
quatre parties, **j'ai fini a 10 / 10 en me trompant sur 7, 6, 6 et 6 manches respectivement**. Le
score ne distingue pas celui qui savait de celui qui a elimine. C'est `SCO-1`, routee a `theo-archi`
(`D7`, `DEC-0083`), toujours sans remede choisi. **Je le compte, je ne le rejuge pas ici.**

---

## 4. `S33-CAT1` sur `bandit-manchot-regles-de-vie-cp` - PAYEE SOUS RESERVE

### 4.1 La partie jouee

| Manche | Item | Enonce servi | Propositions, dans l'ordre reel | Presse |
|---|---|---|---|---|
| 1 | 5 | *Dans les couloirs de l'ecole, comment doit-on se deplacer ?* | *en trainant les pieds* / *en marchant a reculons* / *en marchant* | deux faux, puis juste |
| 2 | 1 | *Avant de parler en classe, que doit faire un eleve ?* | *chuchoter a son voisin* / *lever la main* / *attendre en silence* | faux, puis juste |
| 3 | 10 | *Pourquoi fait-on la queue a la cantine ?* | *pour que tout le monde passe sans se bousculer* / *pour que le service soit plus rapide* / *pour montrer qu'on est patient* | juste |
| 4 | 3 | *Lea a fini de peindre. Que doit-elle faire de son materiel ?* | *le donner a un camarade pour qu'il l'utilise* / *le ranger* / *le laisser secher sur la table* | faux, puis juste |
| 5 | 11 | *Aya a emprunte des crayons de la classe. Que doit-elle faire en repartant ?* | *les garder pour elle* / *les remettre a leur place* / *les donner a un camarade qui n'en a pas* | faux, puis juste |
| 6 | 2 | *Pourquoi leve-t-on la main avant de parler en classe ?* | *pour montrer qu'on a compris* / *pour que chacun parle a son tour* / *pour demander la permission de sortir* | faux, puis juste |
| 7 | 7 | *Hugo veut parler alors qu'un camarade parle deja. Que doit-il faire ?* | *attendre son tour* / *lui dire de se taire* / *parler plus fort que lui* | juste |
| 8 | 9 | *A la cantine, plusieurs eleves veulent se servir en meme temps. Que doivent-ils faire ?* | *attendre que les autres soient partis* / *faire la queue* / *laisser les plus grands passer devant* | faux, puis juste |
| 9 | 4 | *Pourquoi range-t-on le materiel apres l'avoir utilise ?* | *pour que chacun retrouve ses affaires* / *pour que la salle soit plus jolie* / *pour qu'il dure plus longtemps* | juste |
| 10 | 6 | *Pourquoi marche-t-on dans les couloirs au lieu de courir ?* | *pour ne pas faire de bruit* / *pour que personne ne se blesse* / *pour garder de l'energie pour la recreation* | faux, puis juste |

**Item non tire** : l'item 8, lu dans le fichier.

### 4.2 Le repere lexical est parti, et je l'ai compte plutot que suppose

**C'est la condition que la retro de s32 a nommee et le planning de s33 a reprise en section 11**, et
c'est la premiere chose que j'ai mesuree.

| | *maitresse* dans les propositions | dans une bonne reponse |
|---|---|---|
| Avant `S32-CAT1` | 1 | 0 |
| Apres `S32-CAT1` (etat refuse le 2026-09-09) | **7**, sur 6 items de 11 | 0 |
| Apres `S33-CAT1` | **0** | **0** |

**Compte fait sur les items du fichier servi, pas sur le fichier** : les trois occurrences restantes
de la chaine dans `cp-emc-regles-de-vie.yaml` sont toutes dans les commentaires d'en-tete, aucune
n'atteint l'enfant. **Verifie plutot que suppose.**

**Le mot n'a pas ete redistribue, il a ete retire** - c'est ce que la clause demandait, et c'est le
choix couteux : il a fallu reecrire dix distracteurs au lieu d'en deplacer sept.

### 4.3 `CAT-1` item par item, sous la meme grille qu'a s32

**Ma clause demande les trois propositions du meme registre.** Je note **tenue** quand aucune des
trois n'est reconnaissable a son ton, **a moitie** quand une seule des deux fausses l'est, **non
tenue** quand la bonne reponse est la seule qui ne le soit pas.

| Item | Bonne reponse | Les deux distracteurs | Verdict |
|---|---|---|---|
| 1 | *lever la main* | *attendre en silence* (serieux) / *chuchoter a son voisin* (faute legere) | **a moitie** |
| 2 | *pour que chacun parle a son tour* | *pour montrer qu'on a compris* / *pour demander la permission de sortir* | **tenue** |
| 3 | *le ranger* | *le laisser secher sur la table* / *le donner a un camarade pour qu'il l'utilise* | **tenue** |
| 4 | *pour que chacun retrouve ses affaires* | *pour que la salle soit plus jolie* / *pour qu'il dure plus longtemps* | **tenue** |
| 5 | *en marchant* | *en marchant a reculons* / *en trainant les pieds* | **tenue** - les trois sont des facons de marcher |
| 6 | *pour que personne ne se blesse* | *pour ne pas faire de bruit* / *pour garder de l'energie pour la recreation* | **tenue** |
| 7 | *attendre son tour* | *parler plus fort que lui* (impoli) / *lui dire de se taire* (impoli) | **NON TENUE** |
| 8 | *pour comprendre ce qu'il dit* | *pour savoir quand ce sera son tour de parler* / *pour montrer qu'on est poli* | **tenue** |
| 9 | *faire la queue* | *attendre que les autres soient partis* / *laisser les plus grands passer devant* | **tenue** |
| 10 | *pour que tout le monde passe sans se bousculer* | *pour que le service soit plus rapide* / *pour montrer qu'on est patient* | **tenue** |
| 11 | *les remettre a leur place* | *les garder pour elle* (egoiste) / *les donner a un camarade qui n'en a pas* (genereux) | **a moitie** |

**8 tenues, 2 a moitie, 1 non tenue.** A s32 : **2, 5, 4**. Sur les cinq items *"pourquoi"* - la
tache ou le registre etait le plus mordant - la clause est tenue **5 fois sur 5**.

> **`CAT-1` est payee sur ce paquet.** Le controle se fait sur la tache entiere, jamais item par
> item, et **aucune des deux taches ne se gagne plus au registre**. C'est ce que ma clause demande,
> et je ne demande rien de plus apres coup.

### 4.4 `REG-R4` - l'item 7 est plus mauvais qu'avant le correctif

**C'est le seul endroit du lot ou une correction a fait reculer la propriete qu'elle payait.**

| | Distracteurs de l'item 7 |
|---|---|
| Avant `S33-CAT1` | *parler plus fort que lui* / **aller prevenir la maitresse tout de suite** |
| Apres `S33-CAT1` | *parler plus fort que lui* / **lui dire de se taire** |

**Le distracteur serieux a ete remplace par un distracteur impoli**, parce qu'il portait le mot
*maitresse* que la story devait retirer. **La bonne reponse redevient la seule proposition polie des
trois**, ce qui est mot pour mot la premiere phrase de `CAT-1`. En jouant, j'ai presse *attendre son
tour* du premier coup, sans avoir a lire les deux autres.

> **`REG-R4` - nouvelle, datee du 2026-09-10, non bloquante.** Sur l'item 7 de
> `cp-emc-regles-de-vie`, les deux distracteurs sont impolis et la bonne reponse est la seule qui ne
> le soit pas. **Le remede du repere lexical ne se paie pas par un retour du repere de registre sur
> l'item ou le mot etait retire** : c'est la meme famille de defaut, dans l'autre sens.

### 4.5 `REG-R5` - un gabarit de phrase a pris la place du mot

**Trois distracteurs partagent la meme amorce de cinq mots, et aucune bonne reponse ne la porte :**

| Item | Distracteur |
|---|---|
| 2 | *pour montrer qu'on a compris* |
| 8 | *pour montrer qu'on est poli* |
| 10 | *pour montrer qu'on est patient* |

**Trois des cinq items de la tache *"pourquoi"*.** La strategie *"je ne prends jamais le bouton qui
commence par pour montrer qu'on"* elimine un distracteur sur trois items - **elle ne donne pas la
reponse**, contrairement au *maitresse* de s32 qui la donnait d'emblee sur l'item 2 en portant les
**deux** distracteurs. C'est pourquoi c'est une reserve et non un refus de paiement.

> **`REG-R5` - nouvelle, datee du 2026-09-10, non bloquante.** Sur `cp-emc-regles-de-vie`, aucun
> gabarit de phrase repete ne se trouve dans les seuls distracteurs. **Je ne pose aucun seuil
> chiffre** : trois occurrences sur cinq items d'une tache est ce que j'ai mesure, ce n'est pas une
> barre.

### 4.6 Ce que j'ai verifie et qui ne fait pas reserve - pour qu'on ne me le redemande pas

- **Aucune bonne reponse, aucun enonce, aucun indice n'a change.** `FER-1`, `FER-2` (onze items),
  `FER-3`, `EMC-1`, `EMC-8`, `EMC-10` sont tenues a l'identique, recomptees sur le materiau servi.
- **`EMC-5`** : aucun item n'a deux reponses defendables. Verifie item par item, parce que c'est le
  risque propre de ce genre de correction - **rendre les distracteurs serieux les rapproche du
  vrai**. Les deux qui m'ont fait m'arreter : *attendre en silence* (item 1) n'est pas ce qu'on fait
  avant de parler, on leve la main ; *le laisser secher sur la table* (item 3) n'est pas ce qu'on
  fait *de son materiel* quand on a fini. Les deux sont faux pour la situation decrite.
- **`PLA-1`** : chaque distracteur est un candidat qu'un enfant de six ans peut serieusement
  envisager. *En marchant a reculons* et *en trainant les pieds* sont deux facons de marcher que les
  enfants pratiquent ; ce ne sont pas des remplissages.
- **`LON-1`, `LON-2`** : hors de ma garde depuis `ADR-0089`, qui les execute. **Je ne recompte pas a
  la main ce qu'un garde-fou compte mieux que moi.**
- **`REG-R2`** (deux distracteurs absurdes, items 4 et 6) : **payee au passage** - *pour que la salle
  soit plus jolie*, *pour qu'il dure plus longtemps*, *pour ne pas faire de bruit*, *pour garder de
  l'energie pour la recreation* sont quatre raisons plausibles. Je la leve.

---

## 5. `S33-CAT1` sur `bandit-manchot-permis-interdit-cp` - PAYEE SOUS RESERVE

### 5.1 La partie jouee

| Manche | Item | Enonce servi | Propositions, dans l'ordre reel | Presse |
|---|---|---|---|---|
| 1 | 7 | *Lea prete son crayon a Malo. Comment appelle-t-on ca ?* | *de la violence* / *une moquerie* / *un service* | deux faux, puis juste |
| 2 | 1 | *Hugo rit de la voix d'Aya. Comment appelle-t-on ca ?* | *une moquerie* / *une devinette* / *un compliment* | juste |
| 3 | 4 | *Pourquoi est-il interdit d'insulter un camarade ?* | *pour ne pas le faire souffrir* / *pour ne pas deranger la classe* / *pour ne pas se faire gronder* | juste |
| 4 | 8 | *Hugo explique calmement pourquoi il n'est pas d'accord. Comment appelle-t-on ca ?* | *une discussion* / *de la violence* / *une insulte* | juste |
| 5 | 5 | *Aya pousse Hugo tres fort, sans raison. Comment appelle-t-on ca ?* | *un exercice* / *un jeu* / *de la violence* | deux faux, puis juste |
| 6 | 10 | *Pourquoi est-il interdit d'insulter un camarade, meme pour rire ?* | *parce que ca derange la classe* / *parce que ca fait perdre du temps* / *parce que ca peut blesser malgre tout* | deux faux, puis juste |
| 7 | 6 | *Pourquoi est-il interdit de pousser ou de frapper ?* | *pour ne pas lui faire mal* / *pour ne pas se faire mal soi-meme* / *pour respecter le reglement de l'ecole* | juste |
| 8 | 3 | *Malo dit des mots mechants a Hugo. Comment appelle-t-on ca ?* | *un service* / *un cadeau* / *une insulte* | deux faux, puis juste |
| 9 | 2 | *Pourquoi est-il interdit de se moquer d'un camarade ?* | *pour ne pas etre puni* / *pour respecter le reglement de l'ecole* / *pour ne pas le blesser* | deux faux, puis juste |
| 10 | 11 | *Aya tire les cheveux de Malo pendant la recreation. Comment appelle-t-on ca ?* | *un jeu de recreation* / *de la violence* / *une blague sans consequence* | faux, puis juste |

**Item non tire** : l'item 9, lu dans le fichier.

### 5.2 `CAT-1` sur les quatre items ou le registre est mesurable

**La tache de qualification** (sept items : *"Comment appelle-t-on ca ?"*) ne porte pas d'axe de
registre : ses propositions sont des etiquettes de situation, pas des conduites. **Elle n'est pas
touchee et je ne la compte pas** - c'est la lecture que `lea-dev` ecrit dans le paquet et je la
confirme apres l'avoir jouee.

| Item | Bonne reponse | Les deux distracteurs | Verdict |
|---|---|---|---|
| 2 | *pour ne pas le blesser* | *pour ne pas etre puni* / *pour respecter le reglement de l'ecole* | **tenue** |
| 4 | *pour ne pas le faire souffrir* | *pour ne pas se faire gronder* / *pour ne pas deranger la classe* | **tenue** |
| 6 | *pour ne pas lui faire mal* | *pour ne pas se faire mal soi-meme* / *pour respecter le reglement de l'ecole* | **tenue** |
| 10 | *parce que ca peut blesser malgre tout* | *parce que ca derange la classe* / *parce que ca fait perdre du temps* | **tenue** |

**4 sur 4.** A s32 : **1 sur 4**.

> **`CAT-1` est payee sur ce paquet.** *Pour ne pas etre puni* et *pour ne pas se faire gronder* sont
> les deux meilleurs distracteurs du lot : ce sont des raisons qu'un enfant de six ans donne
> reellement, elles sont serieuses, et elles sont fausses parce qu'elles regardent celui qui agit au
> lieu de celui qui recoit. **Un enfant qui les ecarte a compris quelque chose de la ligne.**

### 5.3 `PER-R3` - la meme raison sert deux fois, et elle est difficile a ecarter

*Pour respecter le reglement de l'ecole* est distracteur sur les items **2 et 6**. Je l'ai pressee en
jouant (manche 9) et le jeu l'a refusee.

**Elle est fausse** - le reglement enregistre l'interdit, il ne le motive pas - mais **cette
inversion est fine pour un enfant de six ans**, et c'est la seule formulation du paquet qui se repete
mot pour mot dans les distracteurs sans jamais etre bonne reponse : *reglement* et *l'ecole* y
comptent chacun 2 occurrences, 0 en bonne reponse.

> **`PER-R3` - nouvelle, datee du 2026-09-10, non bloquante.** Sur `cp-emc-permis-interdit`, une
> raison dont la faussete tient a une inversion entre la regle et ce qu'elle protege n'est pas servie
> deux fois comme distracteur. **Ce n'est pas un reproche au correctif** : rendre les distracteurs
> serieux les rapproche du vrai, c'est le prix de `CAT-1`, et il etait previsible. Je le nomme pour
> qu'il soit borne, pas pour revenir sur le paiement.

### 5.4 Ce que j'ai verifie et qui ne fait pas reserve

- **Aucune bonne reponse, aucun enonce, aucune situation ne change.** `FER-1`, `FER-2` (onze items),
  `FER-3`, `EMC-1`, `EMC-5`, `EMC-8`, `EMC-10` tenues a l'identique.
- **`PER-R1`** (recollement question / proposition) : reste levee. Les six *"Comment appelle-t-on
  ca ?"* s'enchainent correctement avec leurs qualifications, entendu sur les cinq manches de
  qualification tirees.
- **`PER-R2`** : **inchangee et toujours ouverte.** L'item 6 oppose toujours *lui faire mal* a *se
  faire mal soi-meme* sans que l'enonce nomme personne. `S33-CAT1` a remplace l'autre distracteur, pas
  celui-la. **Non financee a s33, je ne la compte pas contre le lot.**

---

## 6. `S33-EXC-R1` et `S33-EXC-R5` sur `bandit-manchot-exclusion-cp` - REFUSE

### 6.1 La partie jouee

| Manche | Item | Enonce servi | Propositions, dans l'ordre reel | Presse |
|---|---|---|---|---|
| 1 | 2 | *Presque tous les jours, Hugo se moque de la facon dont parle Tom. Qu'est-ce que c'est ?* | *une moquerie qui se repete* / *une blague entre amis* / *un accident isole* | juste |
| 2 | 5 | *Aya marche sur le pied de Hugo une seule fois, par accident. Qu'est-ce que c'est ?* | *un accident isole* / *une moquerie qui se repete* / *un jeu de recreation* | juste |
| 3 | 8 | *Aya voit une moquerie se repeter **devant la maitresse**. Quel adulte est la ?* | *a sa maitresse* / *a un surveillant de la cour* / *a l'adulte qui surveille la cantine* | juste, **en recopiant un mot de l'enonce** |
| 4 | 6 | *Malo fait tomber le dessin de Lea une fois, sans faire expres. Qu'est-ce que c'est ?* | *une moquerie qui se repete* / *un accident isole* / *une blague* | faux, puis juste |
| 5 | 1 | *Chaque midi cette semaine, Malo empeche Aya de s'asseoir avec le groupe. Qu'est-ce que c'est ?* | *une bousculade* / *un accident isole* / *une moquerie qui se repete* | deux faux, puis juste |
| 6 | 7 | *Hugo voit une moquerie se repeter **devant le surveillant**. Quel adulte est la ?* | *a sa maitresse* / *a l'adulte qui surveille la cantine* / *a un surveillant de la cour* | deux faux, puis juste |
| 7 | 3 | *Depuis plusieurs jours, Malo ecarte Tom du jeu a chaque recreation. Qu'est-ce que c'est ?* | *un jeu de recreation* / *une moquerie qui se repete* / *un accident isole* | faux, puis juste |
| 8 | 11 | *Lea voit une moquerie se repeter **devant sa maitresse**. Quel adulte est la ?* | *a un surveillant de la cour* / *a l'adulte qui surveille la cantine* / *a sa maitresse* | deux faux, puis juste |
| 9 | 10 | *Tom voit une moquerie se repeter **pres du surveillant**. Quel adulte est la ?* | *a l'adulte qui surveille la cantine* / *a un surveillant de la cour* / *a sa maitresse* | faux, puis juste |
| 10 | 4 | *En courant, Tom bouscule Malo une seule fois et s'excuse aussitot. Qu'est-ce que c'est ?* | *une bagarre* / *une moquerie qui se repete* / *un accident isole* | deux faux, puis juste |

**Item non tire** : l'item 9, lu dans le fichier - *"Malo voit une moquerie se repeter devant
l'adulte du repas. Quel adulte est la ?"*, meme forme que les quatre autres.

**Ce que la partie donne d'abord** : **la reserve est payee.** J'ai presse *a sa maitresse* sur la
manche 6 et le jeu l'a refusee - et cette fois **il a raison** : l'enonce dit que le surveillant est
la, pas la maitresse. **Aucun item ne fait plus d'un adulte reel une reponse fausse a une question a
laquelle il repondait bien.** `EXC-R5` est levee.

### 6.2 `EXC-R7` - la reponse est dans l'enonce

**Sur quatre des cinq items de la tache B, la bonne reponse est le mot que l'enonce vient
d'ecrire.**

| Item | Ce que l'enonce dit | Bonne reponse |
|---|---|---|
| 7 | *devant **le surveillant*** | *a un **surveillant** de la cour* |
| 8 | *devant **la maitresse*** | *a sa **maitresse*** |
| 10 | *pres du **surveillant*** | *a un **surveillant** de la cour* |
| 11 | *devant sa **maitresse*** | *a sa **maitresse*** |

Le cinquieme (item 9, *l'adulte du repas* -> *l'adulte qui surveille la cantine*) demande un pas de
plus : savoir que le repas se prend a la cantine.

**Un enfant de CP qui ne sait rien de la ligne gagne ces quatre items en appariant deux mots
identiques.** C'est `CON-1` a son degre maximal - l'item se gagne sans la competence visee - et c'est
**la meme famille que le repere lexical qui a refuse le paiement de `CAT-1` a s32**, en pire : la
ou *maitresse* eliminait, la recopie **designe**.

> **`EXC-R7` - nouvelle, datee du 2026-09-10, non bloquante.** Sur `cp-emc-exclusion`, aucune bonne
> reponse ne reprend un mot que son propre enonce vient d'ecrire.

### 6.3 `EXC-R8` - la question et ses propositions ne forment plus une phrase

**Recolle, l'item donne** : *"Quel adulte est la ? -> a sa maitresse."*

**Ce n'est pas du francais.** La question demande un sujet, les propositions repondent au datif. Les
trois propositions ont garde la forme *"a ..."* de la question supprimee, et personne ne les a
reprises quand la question a change.

**C'est exactement le defaut de `PER-R1` et de `POL-R4`, sur un troisieme paquet de la meme
matiere**, et c'est la propriete dont j'ai ecrit a s32 que j'en restais **la seule garde**. Cinq
items sur onze, lus a l'ecran sur cinq manches.

**C'est aussi la classe de defaut que la retro de s32 a nommee et que le planning de s33 reprend en
section 11** : *la compression qui casse la langue* - un texte remplace sans reconstruire la phrase
autour. Ici ce n'est pas une subordonnee retiree pour tenir une longueur, c'est **une question
remplacee sans reprendre les reponses**, et la cause est la meme.

> **`EXC-R8` - nouvelle, datee du 2026-09-10, non bloquante.** Sur `cp-emc-exclusion`, la question de
> la tache B et chacune de ses trois propositions forment une phrase correcte une fois recollees.

### 6.4 `EXC-R9` - la seconde moitie du libelle n'est plus servie, et la consigne annonce ce qui a disparu

**Le libelle de `EMC-CP-03`** : *"Reconnaitre une situation de moquerie repetee ou d'exclusion **et
savoir a quel adulte en parler**."*

**Aucun item du paquet ne demande plus a qui en parler.** Les cinq items de la tache B demandent
**qui est present**, ce que l'enonce a deja dit. Sous `FER-1`, chaque element enumere par le libelle
doit etre servi par au moins un item ou il est en position de bonne reponse : **la seconde moitie ne
l'est plus par aucun.**

**Et le paquet se contredit avec lui-meme dans le meme commit.** `S33-EXC-R1` a corrige la consigne
en *"Lis bien, puis dis si c'est une moquerie qui se repete, **ou a quel adulte en parler**."* -
c'est-a-dire qu'elle nomme la tache que `S33-EXC-R5` venait de retirer du paquet. **Les deux stories
du meme commit se defont l'une l'autre.** Je l'ai lu dix fois en dix manches.

> **`EXC-R9` - nouvelle, datee du 2026-09-10, non bloquante mais la plus lourde des trois.** Sur
> `cp-emc-exclusion`, au moins un item sert *savoir a quel adulte en parler* en position de bonne
> reponse, et l'adulte a qui parler ne se lit pas dans l'enonce.
>
> **Ce que cette reserve n'est pas** : je **ne rouvre pas** `EMC-CP-03`, qui reste `couvert-valide`,
> et je ne demande pas a `camille-po` de bouger la matrice. **Je dis ce que j'ai mesure** : la ligne
> a ete fermee sur une tache qui n'existe plus dans le paquet, et tant que ce texte est servi, ce
> n'est plus le contenu qui l'a fermee qui est a l'ecran.

### 6.5 Ce que je dis a ma decharge et a la leur

**Le motif de ce refus n'est pas un travail mal fait.** `lea-dev` a applique ma phrase au mot pres,
`theo-archi` l'avait mesuree avant elle, et le resultat a traverse **quatre jobs verts et
`PaquetCpEmcExclusionTest` au vert** - ce test lit la tache d'un item **dans sa bonne reponse**
(`ADR-0089`, decision E), donc il ne voit ni l'enonce ni la question. **Il ne pouvait pas voir ce que
j'ai vu en jouant, et il n'a jamais pretendu le voir.**

**La sortie que je prescrivais etait fausse, et je le dis ici plutot que de le faire porter au
lot.** *"Que la question dise quel adulte est la"* rend la question inutile : si l'enonce nomme
l'adulte present, il n'y a plus rien a savoir. Le probleme que `EXC-R5` posait - *un enfant qui parle
a sa maitresse d'une moquerie de la cour fait ce qu'il faut* - **n'a pas de solution tant que les
trois propositions sont trois adultes de l'ecole dont deux sont faux.** C'est la propriete a
reprendre, et elle est pour `camille-po` a mettre en story, pas pour moi a ecrire en items.

---

## 7. `S33-EMCCP07` sur `bandit-manchot-regle-souhait-loi-cp` - REFUSE, quatrieme passage

### 7.1 La partie jouee

| Manche | Item | Enonce servi | Propositions, dans l'ordre reel | Presse |
|---|---|---|---|---|
| 1 | 3 | *Dans la cour, il est interdit de pousser un camarade. Est-ce une regle ?* | *un simple souhait* / *une regle qui vaut pour tous* | faux, puis juste |
| 2 | 4 | *En France, le casque a velo est obligatoire avant douze ans. Est-ce une regle ?* | *un simple souhait* / *une regle qui vaut pour tous* / *une loi* | **deux faux, puis juste - et c'est cette manche qui refuse la ligne** |
| 3 | 7 | *Le code de la route dit qu'il faut s'arreter au feu rouge. Qui doit s'arreter ?* | *tout le monde, y compris les adultes* / *les motards et les cyclistes* / *seulement les pietons* | juste |
| 4 | 5 | *A la cantine, chaque eleve doit attendre son tour pour se servir. Est-ce une regle ?* | *une regle qui vaut pour tous* / *une loi* / *un simple souhait* | juste |
| 5 | 6 | *Aya aimerait que la maitresse donne moins de devoirs le week-end. Est-ce une regle ?* | *un simple souhait* / *une loi* / *une regle qui vaut pour tous* | juste |
| 6 | 8 | *Une loi rend obligatoire le port de la ceinture en voiture. Qui doit l'attacher ?* | *seulement le conducteur* / *le conducteur et tous les passagers* / *les enfants et les adultes a l'arriere* | faux, puis juste |
| 7 | 2 | *La maitresse aimerait que la classe soit plus silencieuse. Est-ce une regle ?* | *un simple souhait* / *une regle qui vaut pour tous* | juste |
| 8 | 11 | *Une loi interdit de vendre de l'alcool aux mineurs. Qui peut en acheter ?* | *tout le monde, meme les enfants* / *seulement les parents* / *seulement les adultes* | deux faux, puis juste |
| 9 | 1 | *Avant la recreation, chaque eleve doit ranger sa chaise, sans exception. Est-ce une regle ?* | *un simple souhait* / *une regle qui vaut pour tous* | faux, puis juste |
| 10 | 9 | *Une loi interdit de fumer dans la cour d'une ecole. Qui peut y fumer ?* | *les adultes de l'ecole* / *les enseignants seulement* / *personne, pas meme les adultes* | deux faux, puis juste |

**Item non tire** : l'item 10 (klaxon), lu dans le fichier et inchange.

### 7.2 Ce qui est paye : le point 2 du troisieme refus

**`PLA-1` sur l'item 11 est payee.** Le distracteur mort *"seulement les enfants"* - la chaine meme
que j'avais condamnee sur l'item du klaxon le 2026-09-08 et laissee passer sur l'item 11 le meme jour
- est remplace par *"seulement les parents"*.

**C'est un bon distracteur, et pas seulement un distracteur non mort** : un enfant de six ans peut
serieusement croire que seuls les parents achetent de l'alcool, et pour l'ecarter il doit distinguer
*adulte* de *parent*, ce qui est exactement ce que la ligne travaille. Presse en jouant (manche 8),
refuse par le jeu. **`FAB-1c` reste tenue** : la bonne reponse porte elle-meme le marqueur
*seulement*, elle n'est pas la seule sans marqueur.

**`FER-3` recomptee apres correctif** : tache de classement 6 items, tache de portee 5 items, la
moins servie a 5, au-dessus du plancher de 3. **`FER-2`** : onze items. **Les cinq faits de droit
reverifies un par un** : feu rouge, ceinture pour le conducteur et tous les passagers, interdiction
de fumer dans l'enceinte scolaire sans exception pour les adultes, klaxon limite au danger immediat,
vente d'alcool interdite aux mineurs. **Aucun n'est faux.**

### 7.3 Ce qui n'est pas paye : `EMC-11`, et l'item 4 dit toujours quelque chose de faux

**Manche 2, lue a l'ecran** : *"En France, le casque a velo est obligatoire avant douze ans. Est-ce
une regle ?"* - j'ai presse *une regle qui vaut pour tous* et le jeu a repondu *"Ce n'est pas ca."*

**Le casque a velo obligatoire avant douze ans EST une regle qui vaut pour tous** : elle s'applique a
chaque enfant concerne, sans exception, exactement au sens ou l'item 1 du meme paquet appelle *"regle
qui vaut pour tous"* l'obligation de ranger sa chaise. **Le jeu marque fausse une proposition que la
ligne elle-meme apprend a tenir pour vraie.** `EMC-5` est violee, pour la troisieme fois de suite sur
le meme item.

**Et le defaut dort toujours dans les items 5 et 6**, qui portent le meme ternaire non disjoint et ne
sont sauves que par le hasard de leur bonne reponse.

> **`EMC-CP-07` est REFUSEE, quatrieme passage.** Motif : **`EMC-11` n'est pas engagee** et **`EMC-5`
> n'est pas tenue sur l'item 4**, verifie a l'ecran le 2026-09-10. La ligne **reste `non couvert`**.
>
> **Ce refus ne porte pas sur le travail livre** : le point 2 est paye proprement, et le point 1 n'a
> pas ete engage parce qu'il etait bloque par moi. **Le blocage est leve en section 8, et je le leve
> avant de rendre ce document, pas apres.**

---

## 8. Le blocage de `S33-EMCCP07` - quelle clause cede

**C'est le second travail de cette instance, et c'est celui que personne d'autre ne peut faire.**

### 8.1 Ce que `lea-dev` a mesure, et ce que j'en ai reverifie moi-meme

Elle a ecrit, dans le paquet et dans son commit, que la sortie prescrite par ma section 3.4 - la
tache A redevient binaire sur les items 4, 5 et 6 - **fait rougir deux garde-fous opposables**, et
qu'aucune troisieme proposition n'est constructible sans reprendre soit *une loi* (interdit par
`EMC-11`), soit un distracteur mort (interdit par `PLA-1`).

**J'ai repris la mesure item par item sur l'arbre que j'ai joue, sans la supposer.**

**Etat actuel de la tache A**, six items :

| Item | Bonne reponse | Distracteurs | Forme |
|---|---|---|---|
| 1 | *une regle qui vaut pour tous* | *un simple souhait* | binaire |
| 2 | *un simple souhait* | *une regle qui vaut pour tous* | binaire |
| 3 | *une regle qui vaut pour tous* | *un simple souhait* | binaire |
| 4 | *une loi* | *une regle qui vaut pour tous*, *un simple souhait* | ternaire |
| 5 | *une regle qui vaut pour tous* | *un simple souhait*, *une loi* | ternaire |
| 6 | *un simple souhait* | *une regle qui vaut pour tous*, *une loi* | ternaire |

**Apres application de `EMC-11`** : six items binaires, bonnes reponses de la tache A reduites a
`{une regle qui vaut pour tous, un simple souhait}`.

| Ce qui rougit | Ce que le test fait | Ma verification |
|---|---|---|
| `PaquetCpEmcRegleSouhaitLoiTest.fer1LesTroisTermesSontEnBonneReponseDeLaTacheDeClassement` | `containsExactlyInAnyOrderElementsOf({une regle qui vaut pour tous, un simple souhait, une loi})` sur les bonnes reponses de la **tache A** | **exact.** *une loi* disparait de cet ensemble, le test rougit |
| `PaquetCpEmcRegleSouhaitLoiTest.bin1AuPlusLaMoitieDesItemsDeClassementSontBinaires` | `binaires <= classement.size() / 2`, soit `6 <= 3` | **exact.** Le test rougit |

**Les deux mesures de `lea-dev` sont justes. Ce ne sont pas les clauses qu'elles mesurent.**

### 8.2 Ce que chacune des quatre clauses dit reellement

| Clause | Texte, verifie a sa source | Est-elle violee par la sortie de `EMC-11` ? |
|---|---|---|
| **`FER-1`** (arbitrage s28) | *"Chaque element enumere par le libelle de la competence est servi par au moins un item ou il est en position de bonne reponse."* | **NON.** Le libelle est *"Distinguer une regle qui vaut pour tous d'un simple souhait, **et reconnaitre qu'une loi s'impose a tous**"*. Le troisieme element est une **propriete a reconnaitre**, servie en bonne reponse par les cinq items de la tache B (*tout le monde, y compris les adultes* ; *personne, pas meme les adultes* ; *tout conducteur, meme adulte*). **`FER-1` est tenue** |
| **`BIN-1`** (arbitrage s31) | *"Sur une ligne dont la tache principale est un classement a deux categories, **au plus la moitie des items d'un paquet** porte deux propositions."* | **OUI.** 6 items binaires sur **11** du paquet, et la moitie de 11 est 5,5. **Violee d'un item** |
| **`EMC-11`** (arbitrage s33) | *"La tache A porte le binaire que le libelle demande ; une loi n'y est pas une troisieme proposition"* - et *"ne met pas en concurrence deux categories que le libelle ne demande pas de distinguer"* | **c'est la clause qui prescrit la sortie** |
| **`PLA-1`** (arbitrage s31) | *"Chaque distracteur est une reponse qu'un enfant du niveau vise peut serieusement envisager."* | **NON**, et elle ferme la porte a toute troisieme categorie inventee (*une habitude*, *un conseil*) : celle-la serait candidate, mais `EMC-11` l'interdit comme categorie que le libelle ne demande pas de distinguer |

### 8.3 Ce que je tranche

> **Aucune de mes quatre clauses ne cede. Ce qui cede est le perimetre que j'ai chiffre moi-meme
> pour le quatrieme passage, et c'est ecrit ici avec sa date : 2026-09-10.**
>
> **1. `FER-1` ne cede pas parce qu'elle n'est pas violee.** Ce qui la contredit est
> `fer1LesTroisTermesSontEnBonneReponseDeLaTacheDeClassement`, qui **lit le troisieme element du
> libelle dans la tache A**, la ou le libelle ne le met pas. **Le test operationnalise ma clause plus
> etroitement qu'elle-meme**, et il contredit ce que j'ai ecrit le 2026-09-05 dans mon arbitrage
> prealable de s31, section 3.1, sur cette ligne exactement : *"`FER-1` : les trois termes. Deux
> taches : classer (est-ce une regle pour tous, ou un souhait ?) et reconnaitre qu'une loi s'impose a
> tous"*. **Le classement etait binaire dans ma propre phrase, quinze jours avant `EMC-11`.** Le test
> doit lire la propriete la ou le libelle la place : la distinction dans la tache A, la portee de la
> loi dans la tache B. **Symptome nomme, cause a `theo-archi`** - je ne dis ni la forme du test ni
> comment le reecrire.
>
> **2. `BIN-1` ne cede pas non plus, et elle est reellement violee - par un item.** Sa mesure est **le
> paquet**, pas la tache : *six items binaires sur onze* depasse la moitie de **0,5 item**. Elle est
> reparable sans toucher a une seule clause, et **c'est mon chiffrage qui interdisait la reparation**,
> pas ma barre. Deux sorties existent, verifiees toutes deux compatibles avec `FER-1`, `FER-2`,
> `FER-3`, `EMC-11`, `PLA-1` et `DEC-0064` :
>
> - **(a)** l'un des items 4, 5, 6 passe dans la tache B, reecrit sous la forme *"Une loi ... Qui doit
>   ... ?"* : tache A a 5 items binaires, tache B a 6, **5 sur 11 tient `BIN-1`** ;
> - **(b)** un douzieme item ternaire est ajoute a la tache B : **6 sur 12 tient `BIN-1`** a egalite.
>
> **Je ne choisis pas entre (a) et (b)** : le choix depend d'un fait de droit a verifier et de son
> cout, ce qui appartient a `camille-po` et au developpeur. **Ce que je tranche, c'est qu'aucune des
> deux ne demande a une clause de ceder**, et qu'il n'etait donc pas vrai que la sortie soit
> infaisable - **elle etait infaisable a l'interieur du perimetre que j'avais chiffre.**
>
> **3. `EMC-11` ne cede pas.** Elle n'est ni neuve ni surprenante : elle redit ce que j'avais ecrit le
> 2026-09-05, et le defaut qu'elle nomme est celui qui refuse la ligne pour la troisieme et la
> quatrieme fois - **le jeu marque fausse une proposition vraie**, ce qu'aucun automate de ce depot ne
> voit. **La retirer serait rendre `EMC-5` insoluble sur cet item.**
>
> **4. `PLA-1` ne cede pas** et n'a jamais ete en cause : aucune des deux sorties ne demande un
> distracteur mort.

### 8.4 Ce que devient la ligne `EMC-CP-07`

**`EMC-CP-07` reste `non couvert` a la cloture de s33**, quatrieme refus. **Elle est fermable a s34**
et je maintiens qu'elle reste le meilleur rapport du depot, **avec deux corrections a mon chiffrage
que je porte moi-meme** :

- **le perimetre n'est plus *"propositions seules, aucun item neuf"*** : il comprend, selon la sortie
  retenue, **un enonce reecrit ou un item ajoute** ;
- **il comprend une reprise de deux tests de paquet** (`fer1...`, `bin1...`), qui n'est pas du contenu
  pur et qui demande donc l'avis de `theo-archi` avant l'ouverture de la branche - **exactement le
  prealable que `S33-HG-04` a coute a `marc-dev` a ce sprint**, et qu'il est moins cher de demander
  d'avance.

**Le prix reste a `camille-po` et a `sofia-sm`.** Je ne rechiffre pas un poste en cours de sprint et
je n'ecris pas de story.

### 8.5 Ce que ce blocage m'apprend, et que je ne veux pas voir se reproduire

**Un developpeur a mesure, a refuse d'engager, et a ecrit le motif dans le paquet plutot que de
contourner.** C'est le bon comportement et il faut le dire : le contournement disponible - reprendre
*une loi* comme troisieme proposition et laisser l'item 4 mentir - aurait rendu quatre jobs verts.

**Ce qui a coute un sprint, c'est que ma condition et mon chiffrage etaient dans le meme document et
se contredisaient**, et que **personne d'autre que moi ne pouvait le voir** : `EMC-11` est une clause
de contenu, le *"aucun item neuf"* etait un chiffrage, et rien dans le depot ne verifie qu'un prix
est compatible avec la condition qu'il paie.

---

## 9. Les reserves neuves et leur allocation

**Six identifiants, tous sur des prefixes deja alloues, donc aucun prefixe neuf.**

| Identifiant | Paquet | Ce qu'il demande |
|---|---|---|
| `REG-R4` | `cp-emc-regles-de-vie` | Sur l'item 7, la bonne reponse n'est pas la seule proposition polie des trois |
| `REG-R5` | `cp-emc-regles-de-vie` | Aucun gabarit de phrase repete ne se trouve dans les seuls distracteurs |
| `PER-R3` | `cp-emc-permis-interdit` | Une raison dont la faussete tient a une inversion entre la regle et ce qu'elle protege n'est pas servie deux fois comme distracteur |
| `EXC-R7` | `cp-emc-exclusion` | Aucune bonne reponse ne reprend un mot que son propre enonce vient d'ecrire |
| `EXC-R8` | `cp-emc-exclusion` | La question de la tache B et chacune de ses trois propositions forment une phrase correcte recollees |
| `EXC-R9` | `cp-emc-exclusion` | Au moins un item sert *savoir a quel adulte en parler* en bonne reponse, et l'adulte ne se lit pas dans l'enonce |

**Les six sont alloues dans `squad/decisions/REGISTRE-identifiants.md` dans ce meme diff** (`PAR-5`,
`ADR-0085` clause 5). **Ligne d'etat courant portee de 588 / 97 a 594 / 97.** Methode : comptage des
lignes de la table de la section 4 a la forme `| identifiant | document |`, identifiants distincts et
prefixes distincts, **sur le fichier tel qu'il etait avant mon diff**, puis apres mes six
allocations. Aucun de mes six ne collisionne avec les six de l'instance A, qui portent toutes le
prefixe `HG`.

**Et ce que j'ai trouve en comptant, qui vaut plus que le chiffre.** J'attendais **582**, la ligne
que `camille-po` a portee a l'ouverture du sprint. **J'ai compte 588** : l'instance A avait deja
alloue quand je suis passee, et l'instance C a alloue apres moi - elle a compte 594 et porte la ligne
a **601**. **Les trois totaux sont justes et aucun n'est a reparer**, parce que chacune a compte les
lignes du fichier qu'elle avait sous les yeux au lieu d'additionner un chiffre repris. C'est ce que
la clause 5 d'`ADR-0085` demande, et **c'est la premiere fois depuis s31 que la limite structurelle
de `PAR-5` ne produit aucune reparation arithmetique a l'assemblage**.

---

## 10. Ce dont je reste la seule garde, apres ce sprint

**Dit explicitement pour que l'equipe ne croie pas couvert ce qui ne l'est pas.**

| Propriete | Qui la tient | Mordable par un automate ? |
|---|---|---|
| **`CAT-1`** - la bonne reponse ne se reconnait pas a son ton | **moi seule**, et **son paiement a reussi au troisieme essai** | **non.** Ce sprint ajoute un fait : **le remede du repere lexical peut ramener le repere de registre sur l'item qu'il touche** (`REG-R4`) |
| **`EXC-R7`** - la bonne reponse n'est pas dans l'enonce | **moi seule aujourd'hui**, et je la crois **mordable** : c'est une comparaison de mots entre l'enonce et la bonne reponse d'un meme item, du genre de la clause 2 d'`ADR-0086`. **Je dis la propriete, jamais la forme du test, et je ne pose aucun seuil** | **peut-etre**, a `theo-archi` |
| **`EXC-R8`** - question et propositions forment une phrase recollees | **moi seule.** **Troisieme occurrence en trois sprints** (`PER-R1`, `POL-R4`, `EXC-R8`), sur trois paquets de la meme matiere | **non** |
| **`EXC-R9`, `FER-1` lue sur le sens** - la tache servie est bien celle du libelle | **moi seule, en jouant.** `PaquetCpEmcExclusionTest` est **vert** sur un paquet dont la seconde moitie de libelle n'est plus servie : il lit la tache dans la bonne reponse, jamais la question posee | **non** |
| **`EMC-11`, `EMC-5`** - aucune proposition vraie n'est marquee fausse | **moi seule.** Quatre jobs verts sur l'arbre ou l'item 4 de `regle-souhait-loi` ment, **troisieme sprint consecutif** | **non** |
| **`PLA-1`** - chaque proposition est un candidat serieux | **moi seule** | **non** |
| **`BIN-1`** | `theo-archi` la mord deja, **mais sur la tache et non sur le paquet** : le test est **plus strict que ma clause**, et c'est ce qui a rendu la sortie infaisable en apparence (section 8.3) | **oui, et a corriger** |
| **`FER-1` sur `regle-souhait-loi`** | le test la mord **dans la mauvaise tache** (section 8.3) | **oui, et a corriger** |
| **`REG-R3`** - le genre de l'adulte d'ecole dans la serie des paquets d'une matiere | **moi**, par comptage. **Mon propre chiffre etait faux** (section 2.1) | **mesurable**, et je ne pose toujours aucune barre chiffree |
| **`SCO-1`** - le score distingue celui qui savait | **personne**, et c'est le probleme. **Mesure une quatrieme fois aujourd'hui, sur quatre parties sur quatre** | **oui, entierement**, et le remede est un choix pedagogique que je n'ai pas encore rendu |
| **`LON-1`, `LON-2`** | **`ADR-0089`. Je n'en suis plus la garde et je ne les ai pas recomptees** | **oui** |

---

## 11. La porte technique - les quatre jobs de la CI, sur mon diff, nommes un par un

**Je ne deduis aucun job d'un autre et je ne confonds pas la CI avec `mvn verify`** (`CLAUDE.md`,
amendement du 2026-09-08). **Chaque ligne ci-dessous est ecrite apres avoir lance le job.**

| Job de `.github/workflows/ci.yml` | Ce que j'ai lance | Resultat mesure |
|---|---|---|
| **`prefixes`** | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis le sujet de mon commit pipe dans `verifier-prefixes.sh` comme le job le fait | **VERT.** Les deux juges passent tous leurs cas ; *"Tous les commits portent un prefixe d'agent connu."*, code de sortie 0 |
| **`chemins`** | `bash .github/scripts/verifier-chemins-test.sh` (22 cas, tous passent), puis `verifier-chemins.sh` sur l'inventaire complet de `git ls-files`, **et une seconde fois sur la liste explicite de mes six fichiers**, celui que je cree n'etant pas encore suivi | **VERT** dans les deux passes. *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, code de sortie 0 |
| **`outils`** | les trois motifs du job (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`), chacun rendant au moins un fichier | **VERT. 9 juges purs executes**, aucun echec, aucun motif vide |
| **`build`** | `bash outils/mvn-jdk21.sh -B clean verify` sur la **racine** du reacteur, **sur mon diff isole** - reacteur complet, **jamais un `-pl` sans `-am`** | **VERT. BUILD SUCCESS**, six modules, **1443 tests**, 0 echec, 0 erreur, dont 6 tests d'integration Spring Boot. **JDK 21.0.10, version rapportee par la machine virtuelle des tests** (`ADR-0071`) |

**Compte de tests : 1443**, identique a celui de la fusion (planning, section 14) - mon diff n'ajoute
ni test ni ligne de code. Methode : somme de l'attribut `tests` sur les **193** fichiers `TEST-*.xml`
de `surefire-reports` et `failsafe-reports` de mon worktree - 105 `domain`, 13 `application`, 388
`adapter-web`, 693 `adapter-content`, 244 `bootstrap`.

**Le mot *isole* de la ligne `build` porte un fait que je ne cache pas.** J'ai d'abord lance ce meme
`verify` sur un arbre portant **mon diff plus le registre partage tel qu'il etait**, c'est-a-dire
avec les allocations de mes deux voisines mais sans leurs documents.
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` **rougit alors sur treize identifiants -
`HG-R13` a `HG-R18`, `EPS-8`, `JEU-R9` a `JEU-R12`, `SEC-R4`, `SEC-R5` - et aucun n'est de moi.**

**Ce que cela mesure** : la clause 3 d'`ADR-0085` demande qu'une allocation pointe vers un document
qui ecrit l'identifiant. **Elle rend le registre non isolable** : tant que trois instances ecrivent
la meme table le meme jour, **aucune ne peut rendre un job `build` vert sur son diff seul**, et le
vert n'existe qu'a l'assemblage. **Je l'ecris plutot que de me contenter du vert que j'ai obtenu en
isolant mes six lignes**, et je le route a `theo-archi` : ce n'est un defaut de personne, c'est une
consequence de la clause. **Mes six allocations, elles, resolvent** - le meme garde-fou passe ses
sept cas sur un arbre a 588 lignes portant mon diff et lui seul.

**Deux choses que je dis parce qu'elles sont vraies et non parce qu'elles m'arrangent** :

- **Mon diff ne touche aucun fichier de code ni de contenu.** Il ajoute ce releve, des blocs de fiche
  et six lignes au registre d'identifiants. **Un `build` vert sur mon diff ne prouve rien de
  pedagogique.**
- **Les quatre jobs sont verts sur l'arbre ou l'item 4 de `regle-souhait-loi` marque fausse une
  proposition vraie, ou quatre items de `exclusion` donnent leur reponse dans l'enonce, et ou une
  question de CP ne s'accorde pas avec ses propres boutons.** C'est le fait qui justifie que je joue.

---

## 12. Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`REG-R4`, `REG-R5`, `PER-R3`** | `camille-po`, via `sofia-sm` | En faire des stories. **`REG-R4` et `REG-R5` se paient dans le meme geste** sur `regles-de-vie` ; `PER-R3` seule sur `permis-interdit`. Regime `PAR-10` : rejeu borne, 0,5 par paquet |
| **`EXC-R7`, `EXC-R8`, `EXC-R9`, plus `EXC-R1` toujours impayee** | `camille-po`, via `sofia-sm` | **Une seule story**, sur la tache B de `exclusion`. **Les quatre ont une cause unique** et se defont ensemble ou pas du tout. **`EXC-R6` voyage avec elles** (`PAR-10`) |
| **La propriete a reprendre sur la tache B de `exclusion`** | `camille-po` et moi, ensemble | *Savoir a quel adulte en parler* ne se traite pas en opposant trois adultes de l'ecole dont deux seraient de mauvais destinataires : **aucun ne l'est**. C'est ce que `EXC-R5` disait, et c'est ce que ma propre formule n'a pas resolu. **Decision de forme due avant le premier item reecrit**, comme pour `CONS-4` |
| **`S33-EMCCP07`, quatrieme passage** | `camille-po` | **Blocage leve, section 8.** Perimetre corrige : propositions des items 4, 5, 6, **plus** un mouvement d'item (sortie (a) ou (b)), **plus** deux tests de paquet a reprendre. Ligne fermable a s34 |
| **`fer1LesTroisTermesSontEnBonneReponseDeLaTacheDeClassement` et `bin1AuPlusLaMoitieDesItemsDeClassementSontBinaires`** | `theo-archi` | **Symptome** : le premier lit le troisieme element du libelle dans une tache ou le libelle ne le met pas ; le second mesure sur la tache une clause qui se mesure sur le paquet. **Je decris ce que la propriete dit, jamais la forme du test** |
| **`EXC-R7` comme propriete mordable** | `theo-archi` | Comparaison de mots entre l'enonce d'un item et sa bonne reponse. **Fait mesure** : 4 items sur 11 de `cp-emc-exclusion` |
| **`SCO-1`, quatrieme mesure** | `theo-archi` d'abord, puis moi | Le remede technique est instruit et plafonne (`ADR-0063`, `ESSAIS_AVANT_REVELATION` a 3). **Le choix entre crediter moins et permettre moins est pedagogique et il est a moi** : je ne l'ai pas rendu, et je le dois |
| **`REG-R3`, chiffre corrige** | `camille-po`, via `sofia-sm` | **17 / 6 avant s33, 12 / 8 apres**, et non *"17 contre 1"*. La reserve reste ouverte ; **le gain repose pour moitie sur des enonces que je refuse** (section 2.1) |
| **La clause 3 d'`ADR-0085` rend le registre non isolable** | `theo-archi` | **Fait mesure** : sur mon worktree, le garde-fou d'allocation rougit sur 13 identifiants de mes deux voisines, aucun de moi, faute de leurs documents. **Aucune instance ne peut rendre un `build` vert sur son diff seul** tant que trois ecrivent la meme table. Ce n'est pas un defaut de leur travail ; je decris le symptome, la cause est a vous |

---

## 13. Ce que ce document ne fait pas

- **Il ne touche pas `squad/programme/MATRICE-couverture.md`.** Aucune ligne ne bouge dans les deux
  sens, et le compte appartient a `camille-po`.
- **Il ne rouvre aucune ligne `couvert-valide`**, y compris `EMC-CP-03` sur laquelle il mesure
  `EXC-R9`.
- **Il n'ecrit aucune story, ne priorise pas, n'ouvre aucun perimetre et ne rechiffre aucun poste.**
  Il corrige un chiffrage de moi qui reposait sur une hypothese fausse ; le prix reste a
  `camille-po` et a `sofia-sm`.
- **Il ne modifie aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/`.** J'ai
  lu quatre paquets, un catalogue et deux tests de paquet, et joue quatre parties completes ; je n'y
  ai rien ecrit. **Je ne corrige aucun contenu.**
- **Il ne decide pas de l'architecture** : les deux tests de la section 8, la mordabilite de
  `EXC-R7` et le remede de `SCO-1` sont des symptomes que je nomme, des causes que `theo-archi`
  traite.
- **Il ne corrige ni la documentation technique, ni la Javadoc, ni le nommage, ni l'orthographe,
  l'accentuation ou la ponctuation du texte destine a l'enfant** : c'est le `relecteur-pedagogique`
  (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`). **`EXC-R8` n'est pas de ce registre** : ce
  n'est pas une faute d'accord, c'est une question qui ne demande pas ce que ses boutons repondent.
- **Il n'efface aucun verdict et aucun refus.** `EXC-R5` est levee **au-dessus** du refus de s32, qui
  reste lisible ou il a ete ecrit ; les trois reserves neuves sont **ajoutees et datees**, pas
  substituees.
- **Il ne durcit aucune clause anterieure.** `CAT-1`, `PLA-1`, `BIN-1`, `FER-1` a `FER-3`, `EMC-1` a
  `EMC-11`, `EXA-1`, `PAR-0` a `PAR-14` sont intactes. **Ce qui est neuf est neuf et date du
  2026-09-10** : `REG-R4`, `REG-R5`, `PER-R3`, `EXC-R7`, `EXC-R8`, `EXC-R9`, plus les trois
  corrections a mes propres constats de la section 2.
- **Il ne dit rien de ce qu'il n'a pas mesure** : je n'ai pas rejoue `emotions-autrui` ni
  `formules-de-politesse` ce sprint, aucune de leurs reserves n'est financee, et **ce document ne dit
  rien d'elles** sauf le comptage de `REG-R3`, qui porte sur le corpus entier et dont la methode est
  ecrite a cote du chiffre.

---

## 14. A reprendre dans `review.md` - bloc pret a copier

> **Porte pedagogique, instance B de s33 (EMC CP), `nadia-prof`, 2026-09-10.**
>
> - **`CAT-1` est payee sur les deux paquets, au cinquieme sprint de report.**
>   `regles-de-vie` : 8 items sur 11 tiennent la clause entiere (2 a s32). `permis-interdit` : 4 sur 4
>   (1 a s32). **Le repere lexical de s32 est parti et il est compte, pas suppose** : *maitresse*
>   passe de 7 occurrences a **0** dans les propositions servies. **Deux reserves neuves**, `REG-R4`
>   (l'item 7 est plus mauvais qu'avant) et `REG-R5` (un gabarit de phrase a pris la place du mot),
>   plus `PER-R3`. **`REG-R2` levee au passage.**
> - **`EXC-R5` est LEVEE** - la reserve a ete executee dans les termes exacts ou je l'avais ecrite.
>   **L'increment `S33-EXC-R5` est REFUSE** : la sortie que ma propre formule prescrivait met la
>   reponse dans l'enonce sur 4 items (`EXC-R7`), casse le francais du recollement question-boutons
>   sur 5 (`EXC-R8`), et retire du paquet la seconde moitie du libelle de `EMC-CP-03` (`EXC-R9`).
>   **Le motif est a mon debit avant d'etre au sien.** `EXC-R1` **non payee** : la consigne corrigee
>   nomme la tache que le meme commit a fait disparaitre.
> - **`EMC-CP-07` est REFUSEE, quatrieme passage**, et **reste `non couvert`**. Le point 2 du
>   troisieme refus (`PLA-1`, item 11) est **paye**. Le point 1 n'est pas engage et l'item 4 marque
>   toujours fausse une proposition vraie (`EMC-5`), verifie a l'ecran.
> - **Le blocage de `S33-EMCCP07` est leve : aucune de mes quatre clauses ne cede.** `FER-1` n'est pas
>   violee - le test la lit dans la mauvaise tache, contre ce que j'avais ecrit moi-meme le
>   2026-09-05. `BIN-1` est violee d'**un item**, et se repare en deplacant un item de la tache A vers
>   la tache B, ou en ajoutant un douzieme item. `EMC-11` et `PLA-1` ne sont pas en cause.
>   **Ce qui cede est mon chiffrage** - *"aucun item neuf"* etait faux le jour ou je l'ai publie.
> - **Aucune ligne de matrice ne bouge sur ce terrain, dans les deux sens.** Le taux ne change pas de
>   mon fait.
> - **Ce dont je reste la seule garde et qui a traverse quatre jobs verts ce sprint** : une
>   proposition vraie marquee fausse, une reponse recopiable depuis l'enonce, une question de CP qui
>   ne s'accorde pas avec ses boutons, et une ligne fermee dont la moitie du libelle n'est plus
>   servie. **Aucune n'est mordue par un automate aujourd'hui.**
