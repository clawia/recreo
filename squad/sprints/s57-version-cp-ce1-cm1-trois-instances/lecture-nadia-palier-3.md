# s57 - lecture complete des cinq lots du palier 3 (`nadia-prof`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-20, 08:54 (UTC+02:00), lu par `date`.
- **Arbre** : `round-two` a `5ee20db7` (fusion des cinq lots). Non suivi avant ecriture : `.claude/settings.local.json`.
- **Audit degrade, declare** : je n'ai pas lance le jar. Rien n'est joue. J'ai lu les cinq paquets YAML en entier, leurs entrees
  de `catalogue.yaml`, et les paquets de CP et de CM1 voisins de l'EMC (`NRC-CE1-1`).
- **Ce que ce document ne fait pas** : il ne prononce **aucun Done**. Mes propres conditions l'interdisent sur une lecture : 4.1
  point 5 (*"relus a l'ecran"*), 4.1 point 4 (le pli a 744 x 551), 4.2 (*"je joue chaque item texte masque"*). Il n'ouvre aucune
  fiche, ne modifie ni paquet, ni catalogue, ni matrice, n'ecrit ni n'alloue aucun identifiant de condition ou de reserve, et il
  n'est pas commite par moi.
- **Sources des conditions** : `arbitrage-nadia-preparation-palier-3-ce1.md` section 4 ; `reponses-nadia-questions-stories-palier-3.md`
  sections 1, 2, 4, 5, 7 ; `arbitrage-nadia-palier-2.md` 6.1 et 6.2 ; `ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` section 2 ;
  `ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md` sections 2 et 4.1 ; stories `S57-BR-*` et
  `S57-ARB-PALIER-3` (`docs/backlog/backlog.md`).

## Resume

| Lot | Paquet | Verdict de lecture | Items a corriger |
|---|---|---|---|
| `FRA-CE1-06` | `ce1-inference` | **a corriger** | 1 (item 19) |
| `FRA-CE1-07` | `ce1-texte-documentaire` | **a corriger** | 11 (items 1, 2, 3, 7, 9, 10, 12, 13, 14, 16, 18) |
| `FRA-CE1-11` | `ce1-negation` | **a corriger** | 10 indices (items 2, 5, 7, 10, 12, 15, 17, 20, 22, 24) |
| `MAT-CE1-01` | `ce1-nombres-jusqu-a-1000` | **a corriger** | 3 enonces (items 15, 16, 19) |
| `EMC-CE1-01` | `ce1-emc-biens-communs` | **a corriger** | tache 1 (items 1 a 12, propositions) ; items 14, 15, 17, 20, 21 |

Aucun refus : je n'ai lu aucun fait faux, aucune bonne reponse ecrite telle quelle dans un texte d'inference, aucune bonne
reponse seule presente dans un texte documentaire, aucune negation hors `ne... pas` ou hors du present, aucun *decomposer* lu
par l'enfant, aucun *ranger* ni *remettre a sa place* en bonne reponse, aucun item sur ce que l'enfant possede.

Les numeros d'item sont l'ordre du fichier, a partir de 1.

## Lecture complete (`S57-ARB-PALIER-3`, critere 2)

Comptes lus par `grep -c 'bonneReponse:'` sur `5ee20db7`, egaux aux comptes des stories.

- 2026-10-20, lecture complete : ce1-inference, 20 items lus
- 2026-10-20, lecture complete : ce1-texte-documentaire, 20 items lus
- 2026-10-20, lecture complete : ce1-negation, 24 items lus
- 2026-10-20, lecture complete : ce1-nombres-jusqu-a-1000, 22 items lus
- 2026-10-20, lecture complete : ce1-emc-biens-communs, 24 items lus

Un item corrige est relu sur le commit que je joue (4.1, point 6). Ces lignes ne valent pour `LC` que dans un commit ancetre de
celui du verdict ; je ne commite pas ce fichier.

---

## 1. `FRA-CE1-06`, `ce1-inference` : a corriger

**Lu et tenu** : `INF-CE1-1` (trois sortes, chaque reponse relie au plus deux faits, aucune chaine de deductions) ;
`INF-CE1-2` (aucune bonne reponse n'est une suite de mots du texte, y compris en mots non contigus) ; `INF-CE1-3`, familles
*a* et *b* sauf l'item 19 ; exactitude des 20 reponses ; bonne reponse jamais seule a majuscule ; intrus du premier mot sur 5
items, jamais la bonne reponse, donc pas regulier ; indices qui disent ou regarder ; `TRV-CE1-6` (sentiments et roles non
distribues par genre).

**A corriger** :

| Item | Ce qui est ecrit | Motif | Correction |
|---|---|---|---|
| 19 (Nour) | distracteur *"pour laver la terre"* | `INF-CE1-3` famille *b* : *"une reponse vraisemblable en general que le texte contredit"*. Personne n'arrose pour laver la terre : un distracteur absurde fait de l'item une devinette | un distracteur vraisemblable sans le texte et que le texte contredit, par exemple *"pour jouer avec l'eau"* (*"chaque jour"*, la tige) |

**Ce que j'ai examine et que je ne demande pas** : item 20 (Zoe), *"parce qu'elle est fiere"* est hors des quatre sentiments que
le commentaire d'en-tete annonce, mais il est dans l'item de cause, et *fiere* est un mot de CE1. Le commentaire est inexact ;
c'est au `relecteur-pedagogique`. Le test d'equilibre des prenoms ecrit par le developpeur n'est pas une de mes conditions : il
ne m'engage pas et ne remplace pas ma lecture de `TRV-CE1-6`.

**Exige le jeu reel** : le pli a 744 x 551 sur les textes de 4 phrases (items 14, 20), apres une erreur ; la lecture des indices a
l'ecran ; la longueur des textes rendus.

## 2. `FRA-CE1-07`, `ce1-texte-documentaire` : a corriger

**Lu et tenu** : `DOC-CE1-1` (11 `documentaire`, 9 `consigne`, chaque consigne de 3 ou 4 instructions, chaque question de
consigne sur l'ordre ou le contenu d'une instruction) ; `DOC-CE1-2` (bonne reponse et au moins un distracteur dans chaque
texte) ; aucun nombre ; intrus du premier mot sur 8 items, bonne reponse sur 2 seulement (7, 14), pas regulier ; `TRV-CE1-6` :
aucun prenom, aucune situation qui suppose un logement ou un animal possede. **Exactitude** : aucun fait faux. Deux
generalisations, acceptees au CE1, que je relirai a l'ecran : *"Le pull se fabrique avec de la laine"* (item 5), *"elle ...
mange des insectes"* (item 9, vrai des chauves-souris de France).

**A corriger** :

| Item | Ce qui est ecrit | Motif | Correction |
|---|---|---|---|
| 10 (l'eau gele) | indice *"Relis la derniere phrase."* | la reponse est dans la **premiere** phrase (*"L'eau gele a zero degre."*) ; la derniere parle de la glace qui fond. L'indice envoie l'enfant au mauvais endroit | *"Relis la premiere phrase."* |
| 3 (crocodile) | distracteur *"pres de l'eau"* | deux reponses defendables (`TRV-CE1-2`, une seule acceptable) : le crocodile pond bien ses oeufs pres de l'eau, dans le sable des berges | remplacer la phrase du texte et le distracteur par un lieu ou il ne pond pas, par exemple texte *"Le crocodile nage dans la riviere."*, distracteur *"dans la riviere"* |
| 1, 2, 7, 9 | documentaires | **texte masque** (6.2) : j'ai lu l'enonce et les trois propositions sans le texte. Un enfant de CE1 repond par sa culture : le renard mange de la viande, la carotte pousse sous la terre, la chenille mange des feuilles, la chauve-souris sort la nuit | garder l'etre (`DOC-CE1-3`) et demander un fait que seul le texte donne, ou des distracteurs aussi vraisemblables que la bonne reponse sans le texte |
| 12, 13, 14, 16, 18 | consignes | **texte masque** : la logique du geste donne la reponse sans lire (on prend la feuille avant de la plier, on rince apres le savon, on ne pose ni un pot ni de l'eau sur la terre, la chaise va sous la table, on decoupe avec des ciseaux) | demander un detail que seule la consigne fixe, sur le modele des items 15 (couleur), 19 (lieu d'arret) et 20 (ordre eau et sirop), qui ne se devinent pas |

**Exige le jeu reel** : le jeu texte masque des 20 items, que ma lecture approche sans le remplacer. Les items 4, 5, 8, 10, 11, 17
sont des cas que je ne tranche qu'en jouant (fait connu d'une partie des enfants seulement). Le pli sur le texte de 4 phrases
(item 12).

## 3. `FRA-CE1-11`, `ce1-negation` : a corriger

**Lu et tenu** : `NEG-CE1-1` (14 vers la negative, 10 vers l'affirmative, `ne... pas` seul, **verbe au present dans les 24
items**) ; `NEG-CE1-2` (elision juste dans les 14 items) ; `NEG-CE1-3` (familles *a* a *d* conformes a chaque ligne de
commentaire, memes mots hors de la negation) ; `TRV-CE1-2` en caracteres : la bonne reponse n'est jamais strictement la plus
longue ; phrase citee entre chevrons dans chaque enonce ; bonne reponse jamais seule a majuscule ; aucun intrus du premier mot ;
`TRV-CE1-6` : prenoms equilibres. Un seul point de ton, non bloquant : item 20, *"Il n'ecoute pas la maitresse."*, le seul
eleve desobeissant du paquet est un garcon ; un item ne fait pas une distribution.

**A corriger** :

| Items | Ce qui est ecrit | Motif | Correction |
|---|---|---|---|
| 2, 7, 12, 17, 22 | indice *"Une phrase negative dit que quelque chose n'a pas lieu."* | point d'audit *"l'indice dit ou regarder"* : les trois propositions sont toutes des essais de negation, la definition n'en departage aucune. *Avoir lieu* n'est pas un mot de CE1 | un indice qui dit ou regarder, par exemple celui des items 1, 6, 11 : *"Cherche les deux petits mots qui entourent le verbe."* |
| 5, 10, 15, 20, 24 | indice *"Une phrase affirmative dit que quelque chose a lieu."* | meme motif | par exemple celui des items 4, 9, 14 : *"Enleve les petits mots qui disent non."* |

`TRV-CE1-5` reste tenue apres correction : trois indices distincts au moins, aucun identique sur tout le paquet.

**Exige le jeu reel** : les chevrons et les phrases longues a l'ecran, les 24 items relus a l'ecran (4.1, point 5).

## 4. `MAT-CE1-01`, `ce1-nombres-jusqu-a-1000` : a corriger

**Lu et tenu** : `NUM-CE1-1` (21 items a trois chiffres et l'item 1000, rien au-dessus) ; `NUM-CE1-3` (7 *lire*, 7 *ecrire*,
designations litterales justes et constantes, distracteurs distingues par la valeur) ; `NUM-CE1-4` (8 items, 5 avec centaines,
dizaines, unites, 8 sur trois chiffres) ; `NUM-CE1-5` : *decomposer* absent des enonces, propositions, indices, du titre et de la
consigne de catalogue (*"Lis bien les nombres, puis choisis la bonne reponse."*) ; `NUM-CE1-6` (zero intercalaire et tranche
60-99 servis) ; `NUM-CE1-7` (meme nombre de chiffres, ou de mots, dans chaque item). **Exactitude** : les 22 bonnes reponses et
les 44 distracteurs sont justes ou faux comme ils le doivent. Le paquet ne sert que *lire*, *ecrire* et *decomposer*.

**Le mot *decomposer* du descripteur** : le champ `competence` du descripteur le porte (*"Denombrer, lire, ecrire et
decomposer..."*). Je n'ai trouve aucune lecture de ce champ par `application` ni par `adapter-web` (`grep` de *competence* :
aucune occurrence dans le code Java ni dans les gabarits). L'enfant ne le lit donc pas, a ma lecture ; je le verifie a l'ecran.
Ce n'est pas le refus ecrit d'avance tant que je ne le vois pas.

**A corriger** :

| Item | Ce qui est ecrit | Motif | Correction |
|---|---|---|---|
| 15, 16, 19 | *"Quel nombre s'ecrit 3 centaines, 4 dizaines et 7 unites ?"* (et 6 centaines et 5 unites ; 4 centaines et 3 dizaines) | un nombre *s'ecrit* en chiffres ; *3 centaines, 4 dizaines et 7 unites* est sa decomposition. Le verbe confond les deux objets que le paquet apprend a distinguer | *"Quel nombre a 3 centaines, 4 dizaines et 7 unites ?"*, et de meme pour 16 et 19 |

**Condition du Done restante, a `camille-po`** : la mention de `NUM-CE1-2` mot pour mot dans la cellule ; plafond `en cours`.

**Exige le jeu reel** : que le descripteur n'apparaisse sur aucun ecran ; les 22 items a l'ecran.

## 5. `EMC-CE1-01`, `ce1-emc-biens-communs` : a corriger

**Lu et tenu** : `BCO-CE1-1` (deux taches, 12 items chacune ; les trois exemples en bonne reponse : jeux de la cour, livres de la
bibliotheque, ciseaux et crayons de la classe ; *ranger* et *remettre* absents des bonnes reponses) ; `BCO-CE1-2` (chaque bien
personnel est celui d'un personnage nomme, a l'ecole ; la mesure de ma section 4 rend 0 sur les champs que l'enfant lit, la
seule occurrence de *chez* est dans un commentaire ; aucun item ne compare des biens) ; `TRV-CE1-1` (aucun texte support,
aucune voix) ; familles *a* a *d* : **recevables**, chacune conforme a sa definition sauf l'item 21 ; `TRV-CE1-6` (7 prenoms
feminins, 8 masculins, gestes et objets non distribues par genre) ; `EMC-6` (aucun lieu de vie, commune ou type d'ecole).
`NRC-CE1-1` : j'ai lu `cp-emc-regles-de-vie`, `cp-emc-alerte` et `cm1-emc-regles-vie-collective` ; aucun item ne reprend une
question de CP, voir ci-dessous pour *prevenir un adulte*.

**A corriger** :

| Items | Ce qui est ecrit | Motif | Correction |
|---|---|---|---|
| 1 a 12 (tache 1) | *"les ballons de la cour"* contre *"la gourde de Jade"*, *"le cartable bleu de Tom"* ; a l'inverse *"la trousse de Lina"* contre deux *"les ..."* | **intrus du premier mot regulier sur la bonne reponse** dans 11 items sur 12 (seul l'item 6 y echappe) : le bien de tous est au pluriel avec *les*, le bien personnel au singulier avec *le* ou *la*. L'enfant repond en lisant l'article | dans chaque item de la tache 1, les trois propositions ont le meme nombre et le meme article (par exemple *"le ballon de la cour"*, *"la gourde de Jade"*, *"le cartable de Tom"*) |
| 14 (page dechiree) | distracteurs *"il arrache toute la page"*, *"il fait semblant de rien voir"* | le premier est absurde : avec lui l'item se resout par le reflexe *prevenir un adulte* de `cp-emc-alerte` (4 bonnes reponses de ce paquet), sans rien de la ligne de CE1 (`NRC-CE1-1`). Le second omet le *ne* : c'est la faute que `ce1-negation` apprend a reperer | deux gestes faux vraisemblables pour un livre de tous (par exemple *"il le rend sans rien dire"*) ; aucune negation sans *ne* |
| 15 (ballon creve) | distracteur *"elle le jette a la poubelle"* | jeter un ballon creve est defendable (`EMC-5`, une seule reponse defendable) | un geste qui fait d'un bien de tous le sien, par exemple *"elle le garde pour elle"* |
| 17 (Samir) | distracteurs *"il mange du chocolat"*, *"il frotte ses mains par terre"* | absurdes : devinette | deux gestes ordinaires qui laissent des traces, vraisemblables pour un enfant presse |
| 20 (Ines, pluie) | distracteur *"elle le pose dans une flaque"* | absurde | un geste vraisemblable qui laisse le livre a la pluie |
| 21 (Hugo) | *"il compte les pieces"* ; *"il joue sans les compter"* ; *"il prete une piece"* | compter ne protege pas d'une perte, et jouer sans compter n'est pas une faute : deux lectures (`EMC-5`). *Preter* en geste fautif porte un jugement sur le pret que `BCO-CE1-2` ne pose pas | par exemple *"Que fait-il quand la partie est finie ?"*, bonne reponse *"il verifie qu'il ne manque rien"*, distracteurs *"il laisse les pieces sur la table"* (*d*), *"il garde une piece pour lui"* (*c*) |

***Prevenir un adulte* trois fois en bonne reponse** (items 14, 15, 22) : **je ne le refuse pas**. C'est mon propre exemple de
la tache 2 (instruction, 4.1 : *"prevenir un adulte quand un jeu de la cour est casse"*). Ce qui le rend reprise du CP, ce sont
des distracteurs absurdes ; l'item 22 (*"il s'en sert quand meme"*) montre la forme juste. Corriges 14 et 15, les trois tiennent.

**Correction de mon propre constat** : la tache 1 reste lisible par le prenom (bien personnel nomme, bien de tous non nomme),
et c'est l'effet de ma condition `BCO-CE1-2`, pas une faute du paquet. Je ne demande pas de la corriger. Piste au
developpeur, pas une condition : un bien de tous peut porter le prenom de qui s'en sert (*"le livre que lit Jade"*).

**Hors de mon controle** : le paquet n'a pas ete relu caractere par caractere ; l'orthographe, l'accentuation et la
ponctuation sont au `relecteur-pedagogique` (`DEC-0002`, `DEC-0056`), avant que l'enfant le lise.

**Exige le jeu reel** : les 24 items a l'ecran ; que les familles de distracteurs ne donnent pas la reponse a l'oeil apres
correction ; l'indice apres une erreur et le pli.

---

## 6. Conditions du Done restantes, communes

- Les corrections ci-dessus, ajoutees par `camille-po` a chaque story **avant** le worktree (critere "Correction demandee apres
  lecture"), seul le YAML du paquet modifie (`K2`).
- Le **jeu** de chaque lot, sur le commit des corrections : 4.1 points 4 et 5, et pour `-07` les 20 items texte masque.
- `LC` : les lignes ci-dessus dans un commit ancetre du verdict.
- `MAT-CE1-01` : la mention de `NUM-CE1-2` mot pour mot ; plafond `en cours`.

## 7. Ce dont je reste la seule garde

| Propriete | Pourquoi aucun test ne la tient |
|---|---|
| Qu'un item documentaire ou de consigne ne se reponde pas sans le texte | aucun test ne lit la culture d'un enfant |
| Qu'un indice designe l'endroit ou est la reponse | un test compte des mots, il ne situe pas une phrase |
| Que l'article ou le nombre ne donne pas la reponse en EMC | `TRV-CE1-2` est garde en longueur, pas en forme |
| Qu'aucun item d'EMC CE1 ne se resolve par un reflexe appris au CP (`NRC-CE1-1`) | aucun garde-fou ne compare deux paquets |

## 8. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | les corrections des sections 1 a 5, par amendement des stories ; la mention de `NUM-CE1-2` le jour du verdict |
| `sofia-sm` | les rejeux sur la marge de chaque instance, et le commit de ce fichier pour `LC` |
| `relecteur-pedagogique` | la relecture caractere par caractere de `ce1-emc-biens-communs` ; le commentaire d'en-tete de `ce1-inference` (sentiments) |
| moi | jouer les cinq lots sur le jar des corrections ; ouvrir les verdicts dans les fiches |

**Signe** : `nadia-prof`, s57, palier 3, 2026-10-20.

---

## Suite du 2026-10-20 : relecture des corrections, pli, langue

- **Date** : 2026-10-20, 10:01 (UTC+02:00), lu par `date`. **Arbre** : `round-two` a `4650aeab` (contient `c7dab42b`, `27a8a56a`).
- **Lu** : `git diff 5ee20db7 4650aeab` sur les cinq paquets, les items de `-07` et de l'EMC en entier ; la section *"Remesure du
  2026-10-20"* de `mesure-ines-palier-3-pli-744x551.md` (`42238bc5`) ; `releve-relecteur-palier-3.md` (non suivi).
- **Toujours un audit degrade** : je n'ai pas lance le jar. **Aucun Done n'est prononce ici**, pour le motif de l'en-tete
  (4.1 points 5, 4.2). Aucun paquet modifie, aucun identifiant ecrit ni alloue, rien de commite par moi.

### S1. Les corrections demandees : toutes faites

| Lot | Items demandes | Fait, lu sur `4650aeab` |
|---|---|---|
| `-06` | 19 | oui : *"pour jouer avec l'eau"* |
| `-07` | 1, 2, 3, 7, 9, 10, 12, 13, 14, 16, 18 | oui, les 11 : indice de 10 *"Relis la premiere phrase."* ; 3 reecrit (riviere / sable) ; 1, 2, 7, 9 avec un distracteur de culture (*"des carottes"*, *"du miel"*...) ; 12, 13, 14, 16, 18 sur un detail de la consigne |
| `-11` | indices 2, 5, 7, 10, 12, 15, 17, 20, 22, 24 | oui : 10 et 10 sur les deux indices que je proposais, 4 autres ; aucune *"a lieu"* restante ; `TRV-CE1-5` tenue (3 indices distincts) |
| `MAT-CE1-01` | 15, 16, 19 | oui : *"Quel nombre a ..."* |
| EMC | tache 1 ; 14, 15, 17, 20, 21 | oui : les 12 items de la tache 1 ont trois propositions au singulier et au meme article ; 14, 15, 17, 20 sans distracteur absurde ni negation sans *ne* ; 21 reecrit comme je le proposais |

La barre que j'avais ecrite est atteinte sur les cinq lots : ces corrections ne sont plus une condition. Ce qui suit est neuf, et
date du 2026-10-20.

### S2. Le pli : recevable comme reserve non bloquante, sur les cinq lots

Ma condition 4.1 point 4 dit qu'un indice sous le pli a 744 x 551 apres une erreur est une **reserve non bloquante** (mots de
l'utilisatrice). Je ne la durcis pas apres coup : **je ne refuse aucun lot pour le pli**. Cotes reprises de la remesure, non
rejouees par moi : `-07` 0 sur 20, `MAT-CE1-01` 0 sur 22, `-06` 5 sur 20, EMC 11 sur 24, `-11` 22 sur 24.

**Consequence pour `-11`, `-06`, EMC : un correctif de gabarit, pas de contenu.** La cause mesuree est le nombre de rangees de
propositions (section R2 de la remesure). Raccourcir les phrases de `-11` casserait `NEG-CE1-3` (memes mots hors de la negation) ;
raccourcir les textes de `-06` toucherait `INF-CE1-1`. Je ne demande donc aucune correction de contenu pour le pli. Demande a
`ines-design`, avec mon veto sur la solution. **J'exige** : a 744 x 551, apres une erreur, l'indice entier visible sans defiler sur
les items de ces trois paquets, en gardant (a) la taille du texte que lit l'enfant, (b) les trois propositions sur le meme ecran que
l'enonce, (c) l'ordre enonce, texte, propositions, indice. Une solution qui cache le texte support ou les propositions pour faire
place a l'indice, je la refuse. Reserve non bloquante tant que ce correctif n'est pas servi.

### S3. Langue de l'enfant et faute certaine

| Point | Verdict | Exigence |
|---|---|---|
| EMC item 14, indice *"Demande-toi qui doit savoir ... ?"* | **faute certaine, je donne mon accord** a la correction du relecteur, au domaine duquel elle appartient (`DEC-0056`) | *"Demande-toi : qui doit savoir que le livre est abime ?"* (forme des items 1 et 7), avant que l'enfant le lise |
| EMC item 9, *"lequel n'est qu'a un seul eleve ?"* | **a corriger** | *ne ... que* n'est pas une negation, et `ce1-negation` apprend au meme enfant a lire *ne* comme *non* : il peut lire *"n'est pas a un seul eleve"*. Un enonce sans *ne ... que* (par exemple *"lequel est a un seul eleve ?"*) |
| EMC item 8, *"Un seul de ces objets est a un seul eleve. Lequel ?"* | **a corriger** | un seul *seul* (par exemple *"Lequel de ces objets est a un seul eleve ?"*) |
| `-06` item 14, *"son frere. Il se met a pleuvoir."* | **a corriger** | le *Il* impersonnel suit *son frere* : un lecteur de CE1 le lit comme le frere, juste avant une question qui depend de *lui*. Une phrase de pluie qui ne commence pas par *Il* (par exemple *"La pluie commence a tomber."*) |
| `-07` items 4 et 8, *Soleil* / *soleil* | **garde** | l'astre a cote de la Lune et de la Terre (4), l'usage courant (8) : les deux sont justes |
| `MAT-CE1-01` item 18, *"Dans 745, combien de centaines ?"* | **garde** | forme courante des manuels de CE1, courte |

Les quatre *a corriger* sont des reserves nouvelles, pas un durcissement de S1 : corrigees avant mon jeu, elles disparaissent ;
sinon un Done ne pourra etre que *sous reserve* pour l'EMC et `-06`.

**Correction de mon propre constat** : en section 2 j'ai omis l'item 17 de `-07` (*"Que faut-il faire en dernier ?"*, *"Melange
bien"*) parmi les consignes que la logique du geste donne. Il releve du meme critere ; le jeu texte masque le tranchera, avec 9
(chauve-souris le jour), 13 (*eau claire*) et 14 (*un peu*). Nouveau aussi, a confirmer en jouant : EMC item 17, la question dit
*"avant de l'ouvrir"* et les deux distracteurs ne sont pas des gestes d'avant ; l'enfant peut eliminer par la forme.

### S4. Ce qui exige encore un jeu reel, et qui peut le faire

| Quoi | Qui |
|---|---|
| `-07`, les 20 items joues texte masque (4.2) | **moi seule** : c'est un jugement sur la culture d'un enfant, aucun pilote ne le rend |
| Chaque bonne reponse et distracteur relus a l'ecran, cinq lots (4.1 point 5) | moi, sur le jar des corrections de S3 |
| Que le descripteur (*decomposer*, `MAT-CE1-01`) n'apparaisse sur aucun ecran | moi ; `ines-design` peut l'appuyer d'un releve du texte rendu, sans le remplacer |
| Espaces insecables, chevrons, ponctuation rendus | `relecteur-pedagogique` sur l'ecran, ou `ines-design` par releve ; non verifie a ce jour |

`sofia-sm` me fournit le jar construit sur le commit qui porte S3 ; je joue ce commit-la (4.1 point 6).

### S5. Verdict par lot : aucun Done prononce

| Lot | Etat | Reste avant mon jeu | Reserve non bloquante |
|---|---|---|---|
| `-06` | a jouer | item 14 (S3) | pli, 5 items |
| `-07` | a jouer | rien | aucune ; jeu texte masque des 20 items, dont 9, 13, 14, 17 |
| `-11` | a jouer | rien | pli, 22 items |
| `MAT-CE1-01` | a jouer | mention `NUM-CE1-2` mot pour mot (`camille-po`) ; plafond `en cours` | aucune |
| EMC | a jouer | items 8, 9 (S3) ; indice 14 (relecteur, mon accord) | pli, 11 items ; item 17 (S3) |

**Je reste la seule garde**, en plus de la section 7 : qu'un mot de la question ou de l'indice ne se lise pas comme une
negation qu'il n'est pas (EMC 9), et qu'un pronom impersonnel ne soit pas pris pour un personnage (`-06` 14). Aucun test ne lit
une phrase comme un enfant de CE1.

**Signe** : `nadia-prof`, s57, palier 3, 2026-10-20, 10:01.
